package geso.dms.distributor.servlets.reports;

import geso.dms.distributor.beans.reports.IBCCongNoTheoHD;
import geso.dms.distributor.beans.reports.ITomTatCongNoTrongKy;
import geso.dms.distributor.beans.reports.imp.BCCongNoTheoHD;
import geso.dms.distributor.beans.reports.imp.TomTatCongNoTrongKy;

import geso.dms.distributor.util.Utility;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.sql.ResultSet;
import java.text.DecimalFormat;
import java.text.NumberFormat;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;

import com.aspose.cells.Cell;
import com.aspose.cells.FileFormatType;
import com.aspose.cells.Style;
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


public class BCCongNoTheoHDSvl extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
       
    public BCCongNoTheoHDSvl() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");
	//	PrintWriter out = response.getWriter();
		HttpSession session = request.getSession();
		 
		IBCCongNoTheoHD bccn = new BCCongNoTheoHD();
		
		Utility util=new Utility();
		String userTen = (String)session.getAttribute("userTen");
		String querystring=request.getQueryString();
		String userId = util.getUserId(querystring);
		session.setAttribute("userTen", userTen);
		session.setAttribute("userId", userId);
    	session.setAttribute("loi", "");
		
    	bccn.setUserId(userId);
    	bccn.createRs();
    	
    	session.setAttribute("bccn",bccn);
    	
		String nextJSP = request.getContextPath() + "/pages/Distributor/BCCongNoTheoHD.jsp";
		response.sendRedirect(nextJSP);

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");

		HttpSession session = request.getSession();
		Utility util=new Utility();
		
        String tuNgay = util.antiSQLInspection(request.getParameter("tuNgay"));        
        String denNgay = util.antiSQLInspection(request.getParameter("denNgay"));
        
    	String _scheme_nvbh = "";
		String _scheme_nvgn = "";
		String _scheme_kh = "";
		String _scheme_dt = "";
        
        String[] nvbhIds = request.getParameterValues("nvbhId");
		if (nvbhIds != null)
		{
		
			for(int i = 0; i < nvbhIds.length; i++)
			{
				_scheme_nvbh += nvbhIds[i] + ",";
			}
			
			if(_scheme_nvbh.trim().length() > 0)
			{
				_scheme_nvbh = _scheme_nvbh.substring(0, _scheme_nvbh.length() - 1);
			}
		}
		 
        String[] nvgnIds = request.getParameterValues("nvgnId");
		if (nvgnIds != null)
		{

			for(int i = 0; i < nvgnIds.length; i++)
			{
				_scheme_nvgn += nvgnIds[i] + ",";
			}
			
			if(_scheme_nvgn.trim().length() > 0)
			{
				_scheme_nvgn = _scheme_nvgn.substring(0, _scheme_nvgn.length() - 1);
			}
		}
		
		  String[] khIds = request.getParameterValues("khId");
			if (khIds != null)
			{

				for(int i = 0; i < khIds.length; i++)
				{
					_scheme_kh += khIds[i] + ",";
				}
				
				if(_scheme_kh.trim().length() > 0)
				{
					_scheme_kh = _scheme_kh.substring(0, _scheme_kh.length() - 1);
				}
			}
			
			  String[] dtIds = request.getParameterValues("dtId");
				if (dtIds != null)
				{
			
					for(int i = 0; i < dtIds.length; i++)
					{
						_scheme_dt += dtIds[i] + ",";
					}
					
					if(_scheme_dt.trim().length() > 0)
					{
						_scheme_dt = _scheme_dt.substring(0, _scheme_dt.length() - 1);
					}
				}
        

        String action = request.getParameter("action");
		String userTen = (String)session.getAttribute("userTen");
		String userId =  util.antiSQLInspection(request.getParameter("userId"));
		
		System.out.println("T??? ng??y "+tuNgay);
	    
		  if(action.equals("excel"))
		  {
	        	OutputStream out = response.getOutputStream(); 
	        	response.setContentType("application/xlsm");
	        	response.setHeader("Content-Disposition", "attachment; filename=BCCongNoChiTiet.xlsm");
	        	CreatePivotTable(out,response,request, userId, userTen, tuNgay, denNgay, _scheme_nvbh,_scheme_nvgn,_scheme_kh ,_scheme_dt);
	       }	

 
	}

	private void CreatePivotTable(OutputStream out, HttpServletResponse response, HttpServletRequest request, String userId, String userName, String tuNgay, String denNgay, String nvbhId, String nvgnId, String khId, String dtId) throws IOException 
	{	
		FileInputStream fstream = null;		
		
		fstream = new FileInputStream(getServletContext().getInitParameter("path") + "\\BaoCaoCongNoTheoHoaDon.xls");
		Workbook workbook = new Workbook();		
		workbook.open(fstream);
		workbook.setFileFormatType(FileFormatType.EXCEL2007XLSM);
		
		IBCCongNoTheoHD obj = new BCCongNoTheoHD();
        obj.setUserId(userId);       
        obj.setTuNgay(tuNgay);
        obj.setDenNgay(denNgay);
        obj.setNvbhIds(nvbhId);
        obj.setNvgnIds(nvgnId);
        obj.setKhIds(khId);
        obj.setDtIds(dtId);
        obj.initExcel();
      //Buoc 1
	    
	  //Buoc2 tao khung
	    //ham tao khu du lieu
	     obj.createStaticHeader(workbook,obj);
	  //Buoc3 
	     // day du lieu vao
	     obj.createStaticData(workbook);

	     workbook.save(out);     
		
	}
	
	private void CreateReportPDF(Document document, HttpServletRequest request, ServletOutputStream outstream, String userId, String userTen, String tuNgay, String denNgay) throws IOException
	{
		try{
			IBCCongNoTheoHD obj = new BCCongNoTheoHD();
	        obj.setUserId(userId);
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
			 
			Paragraph mdcu = new Paragraph("C??ng N??? Theo T???ng H??a ????n", font);
			mdcu.setSpacingAfter(15);
			mdcu.setAlignment(Element.ALIGN_CENTER);
			document.add(mdcu);
			
			PdfPTable tableHead = new PdfPTable(2);
			tableHead.setWidthPercentage(50);
			tableHead.setHorizontalAlignment(Element.ALIGN_LEFT);
			tableHead.setSpacingAfter(10);
			float[] with = {15.0f, 20.0f}; //set chieu rong cac columns
			tableHead.setWidths(with);
			
			
			PdfPCell cell1 = new PdfPCell(new Paragraph("Nh?? ph??n ph???i ", font2));
			PdfPCell cell2 = new PdfPCell(new Paragraph(userTen, font2));
			cell1.setBorder(0);
			cell2.setBorder(0);
			tableHead.addCell(cell1);
			tableHead.addCell(cell2);
			
			PdfPCell cell3 = new PdfPCell(new Paragraph("T??nh ?????n ng??y kh??a s??? ", font2));
			PdfPCell cell4 = new PdfPCell(new Paragraph(obj.getNgayKS(), font2));
			cell3.setBorder(0);
			cell4.setBorder(0);
			tableHead.addCell(cell3);
			tableHead.addCell(cell4);
							
			document.add(tableHead);
			//Table Content
			PdfPTable table = new PdfPTable(6);
			table.setWidthPercentage(110);
			float[] widths = {20.0f, 8.0f, 8.0f, 10.0f, 10.0f, 10.0f}; //set chieu rong cac columns
	        table.setWidths(widths);
	        
			String[] th = new String[]{"S??? h??a ????n", "", "Ch???ng t??? thanh to??n",  "Ng??y h??a ????n",  "Ti???n h??a ????n", "Thanh to??n"};
			PdfPCell[] cell = new PdfPCell[9];
						
			for(int i=0; i <= 5 ; i++)
			{
				cell[i] = new PdfPCell(new Paragraph(th[i], font2));
				cell[i].setHorizontalAlignment(Element.ALIGN_CENTER);
				cell[i].setVerticalAlignment(Element.ALIGN_MIDDLE);
				cell[i].setPadding(2);
				cell[i].setBackgroundColor(BaseColor.WHITE);							
				table.addCell(cell[i]);
				
			}
			ResultSet rs = obj.getRS();
			int i = 9;
			String khId = "";

			double tongTienDH = 0;
			double tongTienTT = 0;
			double tmpttkh=0;
			double tmphdkh=0;
			String hoadonbk="";
			String soHD="";
			String tienHD="";
			String ctTT = "";
			String ngayHD = "";
			String tientt ="";

			Font font4 = new Font(bf, 7);			

			try{
				while(rs.next()){
					if(khId.length()==0 )
					{
						khId=rs.getString("smartid") +"-"+ rs.getString("khid") + " - " + rs.getString("tenkh");
					}
					
					if(!((rs.getString("smartid") +"-"+ rs.getString("khid") + " - " + rs.getString("tenkh")).equals(khId))){
			
						cell[0] = new PdfPCell(new Paragraph(khId, font4));		
						cell[0].setHorizontalAlignment(Element.ALIGN_LEFT);
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

						cell[3] = new PdfPCell(new Paragraph("", font4));		
						cell[3].setHorizontalAlignment(Element.ALIGN_CENTER);
						cell[3].setVerticalAlignment(Element.ALIGN_MIDDLE);
						cell[3].setPadding(2);
						table.addCell(cell[3]);				

						cell[4] = new PdfPCell(new Paragraph(formatter.format(tmphdkh), font4));		
						cell[4].setHorizontalAlignment(Element.ALIGN_RIGHT);
						cell[4].setVerticalAlignment(Element.ALIGN_MIDDLE);
						cell[4].setPadding(2);
						table.addCell(cell[4]);				

						cell[5] = new PdfPCell(new Paragraph(formatter.format(tmpttkh), font4));		
						cell[5].setHorizontalAlignment(Element.ALIGN_RIGHT);
						cell[5].setVerticalAlignment(Element.ALIGN_MIDDLE);
						cell[5].setPadding(2);
						table.addCell(cell[5]);				
						
						tmphdkh=0;
						tmpttkh=0;
						
						khId =rs.getString("smartid") +"-"+ rs.getString("khid") + " - " + rs.getString("tenkh");
						// tong tien thanh theo khach hang
	
									
						i++;
					}
					soHD = rs.getString("dhid");
					if(soHD.equals(hoadonbk)){
						tienHD ="0";							
					}else{
						tienHD = rs. getString("tiendh");
		    			tongTienDH += Double.parseDouble(tienHD);
		    			hoadonbk=soHD;
					}
						
					System.out.println(i +" -- "+tongTienDH);
						
	    			ctTT = rs.getString("SOCHUNGTU");
//	    			String ngayTT = rs.getString("ngaythanhtoan");;
	    			ngayHD = rs.getString("ngaydh");
	    				    				
	    			tientt = rs.getString("TIENTHANHTOAN");
	    			tongTienTT += Double.parseDouble(tientt);
					
					
					cell[0] = new PdfPCell(new Paragraph("", font4));		
					cell[0].setHorizontalAlignment(Element.ALIGN_CENTER);
					cell[0].setVerticalAlignment(Element.ALIGN_MIDDLE);
					cell[0].setPadding(2);
					table.addCell(cell[0]);				

					cell[1] = new PdfPCell(new Paragraph(soHD, font4));		
					cell[1].setHorizontalAlignment(Element.ALIGN_CENTER);
					cell[1].setVerticalAlignment(Element.ALIGN_MIDDLE);
					cell[1].setPadding(2);
					table.addCell(cell[1]);				
			
					if(ctTT.equals("0"))
						cell[2] = new PdfPCell(new Paragraph("", font4));
					else
						cell[2] = new PdfPCell(new Paragraph(ctTT, font4));						
					cell[2].setHorizontalAlignment(Element.ALIGN_CENTER);
					cell[2].setVerticalAlignment(Element.ALIGN_MIDDLE);
					cell[2].setPadding(2);
					table.addCell(cell[2]);				
					
					cell[3] = new PdfPCell(new Paragraph(ngayHD, font4));		
					cell[3].setHorizontalAlignment(Element.ALIGN_CENTER);
					cell[3].setVerticalAlignment(Element.ALIGN_MIDDLE);
					cell[3].setPadding(2);
					table.addCell(cell[3]);				

					cell[4] = new PdfPCell(new Paragraph(formatter.format(Math.round(Float.parseFloat(tienHD))), font4));		
					cell[4].setHorizontalAlignment(Element.ALIGN_RIGHT);
					cell[4].setVerticalAlignment(Element.ALIGN_MIDDLE);
					cell[4].setPadding(2);
					table.addCell(cell[4]);				

					cell[5] = new PdfPCell(new Paragraph(formatter.format(Math.round(Float.parseFloat(tientt))), font4));		
					cell[5].setHorizontalAlignment(Element.ALIGN_RIGHT);
					cell[5].setVerticalAlignment(Element.ALIGN_MIDDLE);
					cell[5].setPadding(2);
					table.addCell(cell[5]);				

					tmpttkh=tmpttkh+ Double.parseDouble(tientt);
					tmphdkh=tmphdkh+ Double.parseDouble(tienHD);

					i++;
				}

				cell[0] = new PdfPCell(new Paragraph(khId, font4));		
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

				cell[3] = new PdfPCell(new Paragraph("", font4));		
				cell[3].setHorizontalAlignment(Element.ALIGN_CENTER);
				cell[3].setVerticalAlignment(Element.ALIGN_MIDDLE);
				cell[3].setPadding(2);
				table.addCell(cell[3]);				

				cell[4] = new PdfPCell(new Paragraph(formatter.format(tmphdkh), font4));		
				cell[4].setHorizontalAlignment(Element.ALIGN_RIGHT);
				cell[4].setVerticalAlignment(Element.ALIGN_MIDDLE);
				cell[4].setPadding(2);
				table.addCell(cell[4]);				

				cell[5] = new PdfPCell(new Paragraph(formatter.format(tmpttkh), font4));		
				cell[5].setHorizontalAlignment(Element.ALIGN_RIGHT);
				cell[5].setVerticalAlignment(Element.ALIGN_MIDDLE);
				cell[5].setPadding(2);
				table.addCell(cell[5]);				
				
				
				font4 = new Font(bf, 7, Font.BOLD);
				cell[0] = new PdfPCell(new Paragraph("T???ng c???ng", font4));		
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
				
				cell[3] = new PdfPCell(new Paragraph("", font4));		
				cell[3].setHorizontalAlignment(Element.ALIGN_RIGHT);
				cell[3].setVerticalAlignment(Element.ALIGN_MIDDLE);
				cell[3].setPadding(3);
				table.addCell(cell[3]);				

				cell[4] = new PdfPCell(new Paragraph(formatter.format(tongTienDH), font4));		
				cell[4].setHorizontalAlignment(Element.ALIGN_RIGHT);
				cell[4].setVerticalAlignment(Element.ALIGN_MIDDLE);
				cell[4].setPadding(3);
				table.addCell(cell[4]);				

				cell[5] = new PdfPCell(new Paragraph(formatter.format(tongTienTT), font4));		
				cell[5].setHorizontalAlignment(Element.ALIGN_RIGHT);
				cell[5].setVerticalAlignment(Element.ALIGN_MIDDLE);
				cell[5].setPadding(3);
				table.addCell(cell[5]);				
				

			}catch(Exception e){}
					
			document.add(table);
			
			document.close(); 
		}
		catch(DocumentException e)
		{
			e.printStackTrace();
		}

	}

}
