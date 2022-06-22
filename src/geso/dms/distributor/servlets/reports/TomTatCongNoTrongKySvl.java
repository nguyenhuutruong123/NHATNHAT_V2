package geso.dms.distributor.servlets.reports;

import geso.dms.center.db.sql.dbutils;
import geso.dms.distributor.beans.reports.ITomTatCongNoTrongKy;
import geso.dms.distributor.beans.reports.imp.TomTatCongNoTrongKy;
import geso.dms.distributor.util.Utility;
import java.text.DecimalFormat;
import java.text.NumberFormat;

import java.io.IOException;
import java.io.OutputStream;
import java.sql.ResultSet;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.aspose.cells.Workbook;
import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.BaseFont;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;


public class TomTatCongNoTrongKySvl extends HttpServlet {
	private static final long serialVersionUID = 1L;
	Utility util=new Utility();
       
    public TomTatCongNoTrongKySvl() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");
	//	PrintWriter out = response.getWriter();
		HttpSession session = request.getSession();
		String userTen = (String)session.getAttribute("userTen");
		String querystring=request.getQueryString();
		String userId = util.getUserId(querystring);
		session.setAttribute("userTen", userTen);
		session.setAttribute("userId", userId);
		session.setAttribute("tungay", "");
 		session.setAttribute("denngay","");
    	session.setAttribute("loi", "");
		
		String nextJSP = request.getContextPath() + "/pages/Distributor/TomTatCongNoTrongKy.jsp";
		response.sendRedirect(nextJSP);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");

		HttpSession session = request.getSession();
        
        String tuNgay = util.antiSQLInspection(request.getParameter("tuNgay"));
        
        String denNgay = util.antiSQLInspection(request.getParameter("denNgay"));
        session.setAttribute("tungay", tuNgay);
        session.setAttribute("denngay", denNgay);
        String action = request.getParameter("action");
        String userTen = (String)session.getAttribute("userTen");
        String userId = (String) session.getAttribute("userId");
        
        if(action.equals("excel")){
        	OutputStream out = response.getOutputStream(); 
	    	response.setContentType("application/vnd.ms-excel");
	        response.setHeader("Content-Disposition", "attachment; filename=TomTatCongNoTrongKy.xls");
			CreatePivotTable(out,response,request, userId, userTen, tuNgay, denNgay);
        }

        if(action.equals("pdf")){
		    response.setContentType("application/pdf");
			Document document = new Document();
			ServletOutputStream outstream = response.getOutputStream();
			CreateReportPDF(document, request, outstream, userId, userTen, tuNgay, denNgay);			
        }
	}

	private void CreatePivotTable(OutputStream out,
			HttpServletResponse response, HttpServletRequest request,
			String userId, String userName, String tuNgay, String denNgay) throws IOException {
		// TODO Auto-generated method stub
		ITomTatCongNoTrongKy obj = new TomTatCongNoTrongKy();
        obj.setUserId(userId);
        obj.setUserName(userName);
        obj.setTuNgay(tuNgay);
        obj.setDenNgay(denNgay);
        
      //buoc 1
	     Workbook workbook = new Workbook();
	     //Buoc2 tao khung
	    //ham tao khu du lieu
	     obj.createStaticHeader(workbook);
	     obj.init();
	     //Buoc3 
	     // day du lieu vao
	     obj.createStaticData(workbook);

	     workbook.save(out);
	     
		
	}

	private void CreateReportPDF(Document document, HttpServletRequest request, ServletOutputStream outstream, String userId, String userTen, String tuNgay, String denNgay) throws IOException
	{
		try{
			ITomTatCongNoTrongKy obj = new TomTatCongNoTrongKy();
	        obj.setUserId(userId);
	        obj.setUserName(userTen);
	        obj.setTuNgay(tuNgay);
	        obj.setDenNgay(denNgay);
	        obj.init();
	        NumberFormat formatter = new DecimalFormat("#,###,###");
	        
		    PdfWriter.getInstance(document, outstream);
			document.open();
			
			//chi dinh BaseFont.IDENTITY_H de co the go tieng viet
			
			BaseFont bf = BaseFont.createFont("C:\\WINDOWS\\Fonts\\arial.ttf", BaseFont.IDENTITY_H, BaseFont.EMBEDDED);
			Font font = new Font(bf, 12, Font.BOLD);
			Font font2 = new Font(bf, 8, Font.BOLD);
			Font font3 = new Font(bf, 8, Font.UNDERLINE);
			 
			Paragraph mdcu = new Paragraph("Tóm Tắt Công Nợ Trong Kỳ", font);
			mdcu.setSpacingAfter(15);
			mdcu.setAlignment(Element.ALIGN_CENTER);
			document.add(mdcu);
			
			PdfPTable tableHead = new PdfPTable(2);
			tableHead.setWidthPercentage(50);
			tableHead.setHorizontalAlignment(Element.ALIGN_LEFT);
			tableHead.setSpacingAfter(10);
			float[] with = {15.0f, 20.0f}; //set chieu rong cac columns
			tableHead.setWidths(with);
			
			
			PdfPCell cell1 = new PdfPCell(new Paragraph("Nhà phân phối ", font2));
			PdfPCell cell2 = new PdfPCell(new Paragraph(userTen, font2));
			cell1.setBorder(0);
			cell2.setBorder(0);
			tableHead.addCell(cell1);
			tableHead.addCell(cell2);
			
			PdfPCell cell3 = new PdfPCell(new Paragraph("Thời gian báo cáo ", font2));
			PdfPCell cell4 = new PdfPCell(new Paragraph(obj.getTuNgay() + " - " + obj.getDenNgay(), font2));
			cell3.setBorder(0);
			cell4.setBorder(0);
			tableHead.addCell(cell3);
			tableHead.addCell(cell4);
							
			document.add(tableHead);
			//Table Content
			PdfPTable table = new PdfPTable(9);
			table.setWidthPercentage(110);
			float[] widths = {8.0f, 8.0f, 34.0f, 10.0f, 10.0f, 10.0f, 10.0f, 10.0f, 10.0f}; //set chieu rong cac columns
	        table.setWidths(widths);
	        
			String[] th = new String[]{"Nhân viên", "Mã KH", "Khách hàng", "Phải thu ĐK", "Hóa đơn","HĐ trả hàng", "Thanh toán", "Phải thu CK", "Giới hạn CN"};
			PdfPCell[] cell = new PdfPCell[12];
						
			for(int i=0; i <= 8 ; i++)
			{
				cell[i] = new PdfPCell(new Paragraph(th[i], font2));
				cell[i].setHorizontalAlignment(Element.ALIGN_CENTER);
				cell[i].setVerticalAlignment(Element.ALIGN_MIDDLE);
				cell[i].setPadding(2);
				cell[i].setBackgroundColor(BaseColor.WHITE);							
				table.addCell(cell[i]);
				
			}
			ResultSet rs = obj.getRS();
			float dauky = 0;
			float hoadon = 0;
			float thanhtoan = 0;
			float cuoiky = 0;
			float ghcn = 0;
			Font font4 = new Font(bf, 7);			

			try{
				while(rs.next()){
			
				cell[0] = new PdfPCell(new Paragraph("", font4));		
				cell[0].setHorizontalAlignment(Element.ALIGN_CENTER);
				cell[0].setVerticalAlignment(Element.ALIGN_MIDDLE);
				cell[0].setPadding(2);
				table.addCell(cell[0]);				

				cell[1] = new PdfPCell(new Paragraph(rs.getString("KHID"), font4));		
				cell[1].setHorizontalAlignment(Element.ALIGN_CENTER);
				cell[1].setVerticalAlignment(Element.ALIGN_MIDDLE);
				cell[1].setPadding(2);
				table.addCell(cell[1]);				
				
				cell[2] = new PdfPCell(new Paragraph(rs.getString("KH"), font4));		
				cell[2].setHorizontalAlignment(Element.ALIGN_LEFT);
				cell[2].setVerticalAlignment(Element.ALIGN_MIDDLE);
				cell[2].setPadding(2);
				table.addCell(cell[2]);				
				
				dauky = dauky + Float.parseFloat(rs.getString("NODAU"));
				cell[3] = new PdfPCell(new Paragraph(formatter.format(Math.round(Float.parseFloat(rs.getString("NODAU")))), font4));		
				cell[3].setHorizontalAlignment(Element.ALIGN_RIGHT);
				cell[3].setVerticalAlignment(Element.ALIGN_MIDDLE);
				cell[3].setPadding(2);
				table.addCell(cell[3]);				

				hoadon = hoadon + Float.parseFloat(rs.getString("HOADON"));
				cell[4] = new PdfPCell(new Paragraph(formatter.format(Math.round(Float.parseFloat(rs.getString("HOADON")))) , font4));		
				cell[4].setHorizontalAlignment(Element.ALIGN_RIGHT);
				cell[4].setVerticalAlignment(Element.ALIGN_MIDDLE);
				cell[4].setPadding(2);
				table.addCell(cell[4]);				
				
				cell[5] = new PdfPCell(new Paragraph("" , font4));		
				cell[5].setHorizontalAlignment(Element.ALIGN_CENTER);
				cell[5].setVerticalAlignment(Element.ALIGN_MIDDLE);
				cell[5].setPadding(2);
				table.addCell(cell[5]);				

				thanhtoan = thanhtoan + Float.parseFloat(rs.getString("THANHTOAN"));
				cell[6] = new PdfPCell(new Paragraph(formatter.format(Math.round(Float.parseFloat(rs.getString("THANHTOAN")))), font4));		
				cell[6].setHorizontalAlignment(Element.ALIGN_RIGHT);
				cell[6].setVerticalAlignment(Element.ALIGN_MIDDLE);
				cell[6].setPadding(2);
				table.addCell(cell[6]);				
				
				cuoiky = cuoiky + Float.parseFloat(rs.getString("PHAITHU"));
				cell[7] = new PdfPCell(new Paragraph(formatter.format(Math.round(Float.parseFloat(rs.getString("PHAITHU")))), font4));		
				cell[7].setHorizontalAlignment(Element.ALIGN_RIGHT);
				cell[7].setVerticalAlignment(Element.ALIGN_MIDDLE);
				cell[7].setPadding(2);
				table.addCell(cell[7]);				

				ghcn = ghcn + Float.parseFloat(rs.getString("GHCN"));
				cell[8] = new PdfPCell(new Paragraph(formatter.format(Math.round(Float.parseFloat(rs.getString("GHCN")))), font4));		
				cell[8].setHorizontalAlignment(Element.ALIGN_RIGHT);
				cell[8].setVerticalAlignment(Element.ALIGN_MIDDLE);
				cell[8].setPadding(2);
				table.addCell(cell[8]);				

				}
				
				font4 = new Font(bf, 7, Font.BOLD);
				cell[0] = new PdfPCell(new Paragraph("", font4));		
				cell[0].setHorizontalAlignment(Element.ALIGN_CENTER);
				cell[0].setVerticalAlignment(Element.ALIGN_MIDDLE);
				cell[0].setPadding(2);
				table.addCell(cell[0]);				

				cell[1] = new PdfPCell(new Paragraph("", font4));		
				cell[1].setHorizontalAlignment(Element.ALIGN_CENTER);
				cell[1].setVerticalAlignment(Element.ALIGN_MIDDLE);
				cell[1].setPadding(2);
				table.addCell(cell[1]);				

				cell[2] = new PdfPCell(new Paragraph("", font4));		
				cell[2].setHorizontalAlignment(Element.ALIGN_CENTER);
				cell[2].setVerticalAlignment(Element.ALIGN_MIDDLE);
				cell[2].setPadding(2);
				table.addCell(cell[2]);				
				
				cell[3] = new PdfPCell(new Paragraph(formatter.format(Math.round(dauky)), font4));		
				cell[3].setHorizontalAlignment(Element.ALIGN_RIGHT);
				cell[3].setVerticalAlignment(Element.ALIGN_MIDDLE);
				cell[3].setPadding(3);
				table.addCell(cell[3]);				

				cell[4] = new PdfPCell(new Paragraph(formatter.format(Math.round(hoadon)), font4));		
				cell[4].setHorizontalAlignment(Element.ALIGN_RIGHT);
				cell[4].setVerticalAlignment(Element.ALIGN_MIDDLE);
				cell[4].setPadding(3);
				table.addCell(cell[4]);				

				cell[5] = new PdfPCell(new Paragraph("", font4));		
				cell[5].setHorizontalAlignment(Element.ALIGN_RIGHT);
				cell[5].setVerticalAlignment(Element.ALIGN_MIDDLE);
				cell[5].setPadding(3);
				table.addCell(cell[5]);				

				cell[6] = new PdfPCell(new Paragraph(formatter.format(Math.round(thanhtoan)), font4));		
				cell[6].setHorizontalAlignment(Element.ALIGN_RIGHT);
				cell[6].setVerticalAlignment(Element.ALIGN_MIDDLE);
				cell[6].setPadding(3);
				table.addCell(cell[6]);				
				
				cell[7] = new PdfPCell(new Paragraph(formatter.format(Math.round(cuoiky)), font4));		
				cell[7].setHorizontalAlignment(Element.ALIGN_RIGHT);
				cell[7].setVerticalAlignment(Element.ALIGN_MIDDLE);
				cell[7].setPadding(3);
				table.addCell(cell[7]);
				
				cell[8] = new PdfPCell(new Paragraph(formatter.format(Math.round(ghcn)), font4));		
				cell[8].setHorizontalAlignment(Element.ALIGN_RIGHT);
				cell[8].setVerticalAlignment(Element.ALIGN_MIDDLE);
				cell[8].setPadding(3);
				table.addCell(cell[8]);				
				

			}catch(Exception e){}
					
			document.add(table);
			
			document.close(); 
		}
		catch(DocumentException e)
		{
			e.printStackTrace();
		}

	}
	
	private String getDateTime() {
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date date = new Date();
        return dateFormat.format(date);	
	}
	
}
