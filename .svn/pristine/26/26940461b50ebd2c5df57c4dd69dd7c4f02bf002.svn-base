package geso.traphaco.erp.servlets.phieuthanhtoan;

import geso.traphaco.center.servlets.report.ReportAPI;
import geso.traphaco.center.util.*;
import geso.traphaco.erp.beans.hoadontaichinh.IErpHoaDonTaiChinh;
import geso.traphaco.erp.beans.phieuthanhtoan.IErpDonmuahang_Giay;
import geso.traphaco.erp.beans.phieuthanhtoan.imp.ErpDonmuahang_Giay;


import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;

import geso.traphaco.erp.beans.doctien.DocTien;

import geso.traphaco.center.db.sql.dbutils;

import java.sql.ResultSet;
import java.text.DecimalFormat;
import java.text.NumberFormat;

import javax.rmi.CORBA.Tie;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.itextpdf.text.Chunk;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.Image;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Rectangle;
import com.itextpdf.text.pdf.BaseFont;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

public class ErpPhieuThanhToanPdfSvl extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public ErpPhieuThanhToanPdfSvl() {
		super();

	}

	NumberFormat formatter = new DecimalFormat("#,###,###");
	NumberFormat formatter2 = new DecimalFormat("#,###,###.00");

    float CONVERT = 28.346457f;  // =1cm
	
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
    {
		HttpSession session = request.getSession();
		String userId = (String) session.getAttribute("userId");
		String userTen = (String) session.getAttribute("userTen");
		String sum = (String) session.getAttribute("sum");
		geso.traphaco.center.util.Utility cutil = (geso.traphaco.center.util.Utility) session.getAttribute("util");
		
		if (!cutil.check(userId, userTen, sum)) {
			response.sendRedirect("/TraphacoERP/index.jsp");
		} else {
			IErpDonmuahang_Giay obj = new ErpDonmuahang_Giay();
			Utility util = new Utility();
			String querystring = request.getQueryString();
			String ddhId = util.getId(querystring);
			userId = util.getUserId(querystring);
			if (userId.length() == 0)
				userId = util.antiSQLInspection(request.getParameter("userId"));
			
			obj.setUserId(userId);
			System.out.println("da set : " + obj.getUserId());
			
		/*	response.setContentType("application/xlsm");
			response.setHeader("Content-Disposition","attachment; filename=DeNghiTamUng.xlsm");
			OutputStream out = response.getOutputStream();*/
			String nextJSP = "";
		
			String dnttId= request.getParameter("ptt");
		
			obj.initPdf(dnttId);
			
			try {
				
				/*response.setContentType("application/pdf");
				response.setHeader("Content-Disposition"," inline; filename=PhieuDeNghiThanhToan.pdf");
				
				float CONVERT = 28.346457f;
				float PAGE_LEFT = 2.0f*CONVERT, PAGE_RIGHT = 2.0f*CONVERT, PAGE_TOP = 1.0f*CONVERT, PAGE_BOTTOM = 0.0f*CONVERT; //cm
				//Rectangle a5 = new Rectangle(PageSize.A4.getWidth(), PageSize.A4.getHeight()/2);
				Document document = new Document(PageSize.A4, PAGE_LEFT, PAGE_RIGHT, PAGE_TOP, PAGE_BOTTOM);
				
				ServletOutputStream outstream = response.getOutputStream();
				
				this.inPDF(document, outstream, obj);*/
				
				System.out.println("Print");
				response.setContentType("application/pdf");
				response.setHeader("Content-Disposition", " inline; filename=DeNghiThanhToan.pdf");
				
				Document document = new Document();
				ServletOutputStream outstream = response.getOutputStream();			
				
				this.CreateHdPdf(document, outstream,obj, dnttId);
				
				document.close();

			} catch (Exception e) {
				e.printStackTrace();
				session.setAttribute("tuBean", obj);
				nextJSP = "/TraphacoERP/pages/Erp/ErpPhieuThanhToanList.jsp";
				obj.setMsg("Khong the tao bao cao..." + e.getMessage());
				response.sendRedirect(nextJSP);
			}
		}
	
    }
    
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		this.doGet(request, response);
	}
	
	private void CreateHdPdf(Document document, ServletOutputStream outstream, IErpDonmuahang_Giay pxkBean, String hdId) throws IOException
	{
		try{
			dbutils db = new dbutils();
			
			//LẤY THÔNG TIN
			
			String query = 	" SELECT a.PK_SEQ as dmhId, a.HTTT, isnull(a.NguonGocHH ,'') as NguonGocHH, isnull(a.TienTe_FK, '100000') as TienTe_FK, \n" +
							" 		c.PREFIX + a.PREFIX + CAST(a.PK_SEQ as varchar(20)) as SOCHUNGTU, a.NGAYMUA, isnull(a.GhiChu,'') as GhiChu, \n " +
							" 		a.DONVITHUCHIEN_FK as dvthId, a.LOAIHANGHOA_FK, a.LOAISANPHAM_FK, b.loainhacungcap_fk as nccLoai,nv.pk_seq as nvid, \n" +
							"		b.pk_seq as nccId, isnull(b.ma + ', ' + b.TEN, '') as nccTen, isnull(a.ETD, '') as ETD, isnull(a.ETA, '') as ETA, \n" +
							" 		isnull(a.TONGTIENAVAT, '0') as TONGTIENAVAT, isnull(a.VAT, '0') as VAT, isnull(a.TONGTIENBVAT, 0) as TONGTIENBVAT, isnull(a.Dungsai, '0') as dungsai, " +
							"		a.TRANGTHAI, b.loainhacungcap_fk, b.khoNL_Nhan_GC, a.quanlycongno, isnull(sothamchieu, '') as sothamchieu, \n" +
							" 		isnull(b.noibo,0) as noibo, isnull(nv.TEN,'') as NGUOIDENGHI, ISNULL(a.TONGTIENCANTRU,0) as TONGTIENCT, ISNULL(a.TONGTIENCONLAI,0) as TONGTIENCONLAI \n " +
							" 		, isnull(b.noibo,0) as noibo, isnull(a.diadiemgiaohang, '') as diadiemgiaohang, c.TEN as dvthTen, isnull(a.LYDOTT,'') as lydo, a.SOPO,  \n" +
							"       ISNULL((select SUM(SOTIENAVAT) from ERP_DENGHITT_THANHTOANHOADON where DENGHITT_FK = a.PK_SEQ),0) AS DATAMUNG, CTY.TEN AS CTYTEN  "+ 
							" FROM 	ERP_MUAHANG a " +
							" left join ERP_NHACUNGCAP b on a.NHACUNGCAP_FK = b.PK_SEQ \n " +
							" LEFT JOIN ERP_NHANVIEN NV ON NV.PK_SEQ = A.NHANVIEN_FK \n "+
							" INNER JOIN ERP_DONVITHUCHIEN c on c.PK_SEQ = a.DONVITHUCHIEN_FK  \n " +
							" INNER JOIN ERP_CONGTY CTY ON CTY.PK_SEQ = a.CONGTY_FK \n " +
							" WHERE a.pk_seq = '" + hdId+ "' \n " ;
			
			System.out.println("in pdf : " + query);
			String NGUOIDENGHI = "";
			String BOPHAN = "";
			String NGAYDENGHI ="";
			String SOPHIEU ="";
			double TONGTIENAVAT= 0;
			double TONGTIENCT= 0;
			double TONGTIENCONLAI= 0;
			double DATAMUNG = 0;
			
			double chenhlech = 0;
			String ctyTen = "";
			ResultSet rs = db.get(query);
			if (rs != null)
			{
				try
				{
					while (rs.next())
					{
						NGUOIDENGHI = rs.getString("NGUOIDENGHI");
						BOPHAN = rs.getString("dvthTen");
						NGAYDENGHI = rs.getString("NGAYMUA");
						TONGTIENAVAT = rs.getDouble("TONGTIENAVAT");
						TONGTIENCT = rs.getDouble("TONGTIENCT");
						TONGTIENCONLAI = rs.getDouble("TONGTIENCONLAI");
						DATAMUNG = rs.getDouble("DATAMUNG");
						SOPHIEU = rs.getString("SOPO");
						ctyTen  = rs.getString("CTYTEN");
					}
					rs.close(); 
				}
				catch (Exception e)
				{
					System.out.println("__Exception: " + e.getMessage());
				}
			}
		
			
			NumberFormat formatter = new DecimalFormat("#,###,###.###");
			NumberFormat formatter1 = new DecimalFormat("#,###,###");
			//document.setPageSize(PageSize.A4.rotate()); CANH HÓA ĐƠN THEO CHIỀU DỌC
			document.setMargins(1.0f*CONVERT, 1.5f*CONVERT, 2.0f*CONVERT, 2.0f*CONVERT); // L,R, T, B
			PdfWriter writer = PdfWriter.getInstance(document, outstream);
			
			document.open();
			//document.setPageSize(new Rectangle(100.0f, 100.0f));
			//document.setPageSize(PageSize.A4.rotate());
		

			BaseFont bf = BaseFont.createFont("c:\\windows\\fonts\\times.ttf", BaseFont.IDENTITY_H, BaseFont.EMBEDDED);
			Font font = new Font(bf, 13, Font.BOLD);
			Font font2 = new Font(bf, 8, Font.BOLD);
			
			
			//GIẤY ĐỀ NGHỊ THANH TOÁN
			PdfPTable tableheader =new PdfPTable(1);
			tableheader.setWidthPercentage(100);			

			PdfPCell cell = new PdfPCell();		
			
			Paragraph hd = new Paragraph();
				new Paragraph("GIẤY ĐỀ NGHỊ THANH TOÁN" , new Font(bf, 16, Font.ITALIC));
			//p = new Paragraph();
			hd.add(new Chunk("GIẤY ĐỀ NGHỊ THANH TOÁN ", new Font(bf, 16, Font.ITALIC))); 
			hd.add(new Chunk("Số: "+SOPHIEU, new Font(bf, 10, Font.NORMAL))); 
			
			hd.setAlignment(Element.ALIGN_CENTER);	
			cell.setFixedHeight(1.3f*CONVERT);
			cell.addElement(hd);
			
			tableheader.addCell(cell);
			document.add(tableheader);
			
			//CÔNG TY
			
			PdfPTable table1 = new PdfPTable(2);
			table1.setWidthPercentage(100);
			float[] withs1 = {60f,100f};
			table1.setWidths(withs1);
					
			cell = new PdfPCell();	
			hd = new Paragraph("CÔNG TY " , new Font(bf, 12, Font.BOLD));//NGUOI MUA
			hd.setAlignment(Element.ALIGN_LEFT);
			cell.setFixedHeight(0.8f*CONVERT);	//hd.setSpacingAfter(4);			
			cell.setPaddingLeft(2.0f*CONVERT);
			cell.addElement(hd);
			cell.setBorder(0);	
			
			table1.addCell(cell);
			
			cell = new PdfPCell();	
			hd = new Paragraph(ctyTen + " " , new Font(bf, 12, Font.BOLD));//NGUOI MUA
			hd.setAlignment(Element.ALIGN_LEFT);
			cell.setFixedHeight(0.8f*CONVERT);
			cell.addElement(hd);
			cell.setBorder(0);	
			
			table1.addCell(cell);
			
			document.add(table1);
			
			//ĐƠN VỊ
			
			PdfPTable table2 = new PdfPTable(2);
			table2.setWidthPercentage(100);
			float[] withs2 = {60f,100f};
			table2.setWidths(withs2);
					
			cell = new PdfPCell();	
			hd = new Paragraph("NGƯỜI ĐỀ NGHỊ" , new Font(bf, 12, Font.BOLD));
			hd.setAlignment(Element.ALIGN_LEFT);
			cell.setFixedHeight(0.8f*CONVERT);	//hd.setSpacingAfter(4);			
			cell.setPaddingLeft(2.0f*CONVERT);
			cell.addElement(hd);
			cell.setBorder(0);	
			
			table2.addCell(cell);
			
			cell = new PdfPCell();	
			hd = new Paragraph(NGUOIDENGHI , new Font(bf, 12, Font.NORMAL));
			hd.setAlignment(Element.ALIGN_LEFT);
			cell.setFixedHeight(0.8f*CONVERT);
			cell.addElement(hd);
			cell.setBorder(0);	
			
			table2.addCell(cell);
						
			//BỘ PHẬN
			
			cell = new PdfPCell();	
			hd = new Paragraph( "BỘ PHẬN" , new Font(bf, 12, Font.BOLD));
			hd.setAlignment(Element.ALIGN_LEFT);
			cell.setFixedHeight(0.8f*CONVERT);
			cell.setPaddingLeft(2.0f*CONVERT);
			cell.addElement(hd);
			cell.setBorder(0);	
			
			table2.addCell(cell);
			
			cell = new PdfPCell();	
			hd = new Paragraph( BOPHAN, new Font(bf, 12, Font.NORMAL));
			hd.setAlignment(Element.ALIGN_LEFT);
			cell.setFixedHeight(0.8f*CONVERT);
			cell.addElement(hd);
			cell.setBorder(0);	
			
			table2.addCell(cell);
			
			//NGÀY ĐỀ NGHỊ
			
			cell = new PdfPCell();	
			hd = new Paragraph( "NGÀY" , new Font(bf, 12, Font.BOLD));
			hd.setAlignment(Element.ALIGN_LEFT);
			cell.setFixedHeight(0.8f*CONVERT);
			cell.setPaddingLeft(2.0f*CONVERT);
			cell.addElement(hd);
			cell.setBorder(0);	
			
			table2.addCell(cell);
			
			String [] ngayHD = NGAYDENGHI.split("-");
			cell = new PdfPCell();	
			hd = new Paragraph( ngayHD[2] + "/" + ngayHD[1] +  "/" + ngayHD[0], new Font(bf, 10, Font.NORMAL));
			hd.setAlignment(Element.ALIGN_LEFT);
			cell.setFixedHeight(0.8f*CONVERT);
			cell.addElement(hd);
			cell.setBorder(0);	
			
			table2.addCell(cell);
			
			document.add(table2);
			
			//HÌNH THỨC THANH TOÁN - MÃ SỐ THUẾ
			
			String nd = "SELECT NGAYNHAN, DIENGIAI, THANHTIEN FROM ERP_MUAHANG_SP WHERE MUAHANG_FK ='"+hdId+"'";
			
			System.out.println("in nd : " + query);
			String NGAYNHAN = "";
			String DIENGIAI = "";
			double THANHTIENVIET =0;
			
			int i =0;
			
			
			PdfPTable table4 =new PdfPTable(4);
			table4.setWidthPercentage(100);
			float[] withs4 = {100f,200f,100f, 100f};
			table4.setWidths(withs4);
						
			cell= new PdfPCell();	
			hd = new Paragraph( "Ngày" , new Font(bf, 10, Font.BOLD)); 
			hd.setAlignment(Element.ALIGN_CENTER);
			cell.setFixedHeight(1.0f*CONVERT);
			cell.addElement(hd);
			
			table4.addCell(cell);
			
			cell= new PdfPCell();	
			hd = new Paragraph( "NỘI DUNG THANH TOÁN" , new Font(bf, 10, Font.BOLD)); 
			hd.setAlignment(Element.ALIGN_CENTER);
			cell.setFixedHeight(1.0f*CONVERT);
			cell.addElement(hd);
			
			table4.addCell(cell);
			
			cell= new PdfPCell();	
			hd = new Paragraph( "SỐ TIỀN" , new Font(bf, 10, Font.BOLD)); 
			hd.setAlignment(Element.ALIGN_CENTER);
			cell.setFixedHeight(1.0f*CONVERT);
			cell.addElement(hd);
			
			table4.addCell(cell);
			
			cell= new PdfPCell();	
			hd = new Paragraph( "Ghi chú" , new Font(bf, 10, Font.BOLD)); 
			hd.setAlignment(Element.ALIGN_CENTER);
			cell.setFixedHeight(1.0f*CONVERT);
			cell.addElement(hd);
			
			table4.addCell(cell);
			
			rs = db.get(nd);
			if (rs != null)
			{
				try
				{
					while (rs.next())
					{
						NGAYNHAN = rs.getString("NGAYNHAN");
						DIENGIAI = rs.getString("DIENGIAI");
						THANHTIENVIET = rs.getDouble("THANHTIEN");	
						
						cell= new PdfPCell();					
						hd = new Paragraph( ngayHD[2] + "/" + ngayHD[1] +  "/" + ngayHD[0] , new Font(bf, 10, Font.NORMAL)); 
						hd.setAlignment(Element.ALIGN_CENTER);
						cell.setFixedHeight(1.0f*CONVERT);
						cell.addElement(hd);
						
						table4.addCell(cell);
						
						cell= new PdfPCell();	
						hd = new Paragraph( DIENGIAI , new Font(bf, 10, Font.NORMAL)); 
						hd.setAlignment(Element.ALIGN_LEFT);
						cell.setFixedHeight(1.0f*CONVERT);
						cell.addElement(hd);
						
						table4.addCell(cell);
						
						cell= new PdfPCell();	
						hd = new Paragraph(DinhDangTraphacoERP(formatter.format( THANHTIENVIET)) , new Font(bf, 10, Font.NORMAL)); 
						hd.setAlignment(Element.ALIGN_RIGHT);
						cell.setFixedHeight(1.0f*CONVERT);
						cell.addElement(hd);
						
						table4.addCell(cell);
						
						cell= new PdfPCell();	
						hd = new Paragraph( "" , new Font(bf, 10, Font.NORMAL)); 
						hd.setAlignment(Element.ALIGN_LEFT);
						cell.setFixedHeight(1.0f*CONVERT);
						cell.addElement(hd);
						
						table4.addCell(cell);
						
						i++;
					}
					rs.close(); 
				}
				catch (Exception e)
				{
					System.out.println("__Exception: " + e.getMessage());
				}
			}
			
			int dongtrong = 5-i;
			
			for(int k=0; k<dongtrong;k++){
				cell= new PdfPCell();	
				hd = new Paragraph( "" , new Font(bf, 10, Font.BOLD)); 
				hd.setAlignment(Element.ALIGN_CENTER);
				cell.setFixedHeight(1.0f*CONVERT);
				cell.addElement(hd);
				
				table4.addCell(cell);
				
				cell= new PdfPCell();	
				hd = new Paragraph( "" , new Font(bf, 10, Font.BOLD)); 
				hd.setAlignment(Element.ALIGN_CENTER);
				cell.setFixedHeight(1.0f*CONVERT);
				cell.addElement(hd);
				
				table4.addCell(cell);
				
				cell= new PdfPCell();	
				hd = new Paragraph("" , new Font(bf, 10, Font.BOLD)); 
				hd.setAlignment(Element.ALIGN_CENTER);
				cell.setFixedHeight(1.0f*CONVERT);
				cell.addElement(hd);
				
				table4.addCell(cell);
				
				cell= new PdfPCell();	
				hd = new Paragraph( "" , new Font(bf, 10, Font.BOLD)); 
				hd.setAlignment(Element.ALIGN_CENTER);
				cell.setFixedHeight(1.0f*CONVERT);
				cell.addElement(hd);
				
				table4.addCell(cell);
			}
			
			
			cell= new PdfPCell();	
			hd = new Paragraph( "" , new Font(bf, 10, Font.BOLD)); 
			hd.setAlignment(Element.ALIGN_CENTER);
			cell.setFixedHeight(0.5f*CONVERT);
			cell.setBorder(0);	
			cell.addElement(hd);
			
			table4.addCell(cell);
			
			cell= new PdfPCell();	
			hd = new Paragraph( "" , new Font(bf, 10, Font.BOLD)); 
			hd.setAlignment(Element.ALIGN_CENTER);
			cell.setFixedHeight(0.5f*CONVERT);
			cell.setBorder(0);	
			cell.addElement(hd);
			
			table4.addCell(cell);
			
			cell= new PdfPCell();	
			hd = new Paragraph("" , new Font(bf, 10, Font.BOLD)); 
			hd.setAlignment(Element.ALIGN_CENTER);
			cell.setFixedHeight(0.5f*CONVERT);
			cell.setBorder(0);	
			cell.addElement(hd);
			
			table4.addCell(cell);
			
			cell= new PdfPCell();	
			hd = new Paragraph( "" , new Font(bf, 10, Font.BOLD)); 
			hd.setAlignment(Element.ALIGN_CENTER);
			cell.setFixedHeight(0.5f*CONVERT);
			cell.setBorder(0);	
			cell.addElement(hd);
			
			table4.addCell(cell);
			
			document.add(table4);
			
			
			PdfPTable table6 = new PdfPTable(2);
			table6.setWidthPercentage(100);
			float[] withs6 = {170f,100f};
			table6.setWidths(withs6);
			
			cell = new PdfPCell();
			hd = new Paragraph("TỔNG CỘNG: "+DinhDangTraphacoERP(formatter.format( TONGTIENAVAT)) , new Font(bf, 12, Font.BOLD));//NGUOI MUA
			hd.setAlignment(Element.ALIGN_LEFT);
			cell.setFixedHeight(0.8f*CONVERT);	//hd.setSpacingAfter(4);			
			cell.setPaddingLeft(0.5f*CONVERT);
			cell.setBorderWidthRight(0);
			cell.setBorderWidthBottom(0);
			cell.addElement(hd);			
			table6.addCell(cell);
			
			cell = new PdfPCell();
			hd = new Paragraph("ĐÃ TẠM ỨNG: " +DinhDangTraphacoERP(formatter.format( DATAMUNG)) , new Font(bf, 12, Font.BOLD));//NGUOI MUA
			hd.setAlignment(Element.ALIGN_LEFT);
			cell.setFixedHeight(0.8f*CONVERT);	//hd.setSpacingAfter(4);			
			cell.setPaddingLeft(0.0f*CONVERT);
			cell.setBorderWidthBottom(0);	
			cell.setBorderWidthLeft(0);	
			cell.addElement(hd);				
			table6.addCell(cell);
			
			cell = new PdfPCell();
			hd = new Paragraph("TRỪ TẠM ỨNG: " + formatter.format(TONGTIENCT) , new Font(bf, 12, Font.BOLD));//NGUOI MUA
			hd.setAlignment(Element.ALIGN_LEFT);
			cell.setFixedHeight(0.8f*CONVERT);	//hd.setSpacingAfter(4);			
			cell.setPaddingLeft(0.5f*CONVERT);
			cell.setBorderWidthBottom(0);
			cell.setBorderWidthTop(0);
			cell.setBorderWidthRight(0);
			cell.addElement(hd);			
			table6.addCell(cell); 
			
			cell = new PdfPCell();
			hd = new Paragraph("" , new Font(bf, 12, Font.BOLD));//NGUOI MUA
			hd.setAlignment(Element.ALIGN_LEFT);
			cell.setFixedHeight(0.8f*CONVERT);	//hd.setSpacingAfter(4);			
			cell.setPaddingLeft(0.0f*CONVERT);
			cell.setBorderWidthBottom(0);
			cell.setBorderWidthTop(0);
			cell.setBorderWidthLeft(0);
			cell.addElement(hd);			
			table6.addCell(cell); 
			
			cell = new PdfPCell();
			hd = new Paragraph("SỐ TIỀN ĐỀ NGHỊ THANH TOÁN: " + formatter.format(TONGTIENCONLAI)  , new Font(bf, 12, Font.BOLD));//NGUOI MUA
			hd.setAlignment(Element.ALIGN_LEFT);
			cell.setFixedHeight(0.8f*CONVERT);	//hd.setSpacingAfter(4);			
			cell.setPaddingLeft(0.5f*CONVERT);
			cell.setBorderWidthBottom(0);
			cell.setBorderWidthTop(0);
			cell.setBorderWidthRight(0);
			cell.addElement(hd);
			table6.addCell(cell);
			
			cell = new PdfPCell();
			hd = new Paragraph("" , new Font(bf, 12, Font.BOLD));//NGUOI MUA
			hd.setAlignment(Element.ALIGN_LEFT);
			cell.setFixedHeight(0.8f*CONVERT);	//hd.setSpacingAfter(4);			
			cell.setPaddingLeft(0.0f*CONVERT);
			cell.setBorderWidthBottom(0);
			cell.setBorderWidthTop(0);
			cell.setBorderWidthLeft(0);
			cell.addElement(hd);		
			table6.addCell(cell); 
			
			//ĐỌC TIỀN RA CHỮ
			
			chenhlech = TONGTIENAVAT - DATAMUNG ;
			String tien = "";
			
			DocTien doctien = new DocTien();
			
			if(chenhlech > 0)  tien = "Thanh toán " + doctien.docTien(Math.round(chenhlech));
			else if (chenhlech == 0) tien =  doctien.docTien(Math.round(chenhlech));
			else tien = "Thu lại " + doctien.docTien(Math.round(Math.abs(chenhlech)));
						
			//String tien = doctien.docTien(Math.round(TONGTIENAVAT));
						
			//String tien = doctien.tranlate(tongtiencovat + "");
			//tien = tien.substring(0, 1).toUpperCase() + tien.substring(1, tien.length());
			
			if(tien.equals("Đồng"))
				 tien="Không Đồng";
			System.out.println(" Tien là :"+tien);
			
			cell = new PdfPCell();
			hd = new Paragraph("BẰNG CHỮ: " +tien, new Font(bf, 12, Font.BOLD));//NGUOI MUA
			hd.setAlignment(Element.ALIGN_LEFT);
			cell.setFixedHeight(0.8f*CONVERT);	
			hd.setSpacingAfter(0.3f*CONVERT);			
			cell.setPaddingLeft(0.5f*CONVERT);			
			cell.addElement(hd);
			cell.setBorderWidthTop(0);
			cell.setBorderWidthBottom(0);
			cell.setColspan(2);
			table6.addCell(cell);
			
						
			cell= new PdfPCell();	
			hd = new Paragraph("" , new Font(bf, 10, Font.BOLD)); 
			hd.setAlignment(Element.ALIGN_CENTER);
			cell.setFixedHeight(0.5f*CONVERT);
			cell.setBorderWidthTop(0);	
			cell.setColspan(2);
			cell.addElement(hd);
			table6.addCell(cell);
			
			cell= new PdfPCell();	
			hd = new Paragraph("" , new Font(bf, 10, Font.BOLD)); 
			hd.setAlignment(Element.ALIGN_CENTER);
			cell.setFixedHeight(0.5f*CONVERT);
			cell.setBorder(0);	
			cell.setColspan(2);
			cell.addElement(hd);
			table6.addCell(cell);
			
			document.add(table6);
			
			
			PdfPTable table7 = new PdfPTable(2);
			table7.setWidthPercentage(100);
			float[] withs7 = {100f, 100f};
			table7.setWidths(withs7);
			
			cell = new PdfPCell();
			hd = new Paragraph("NGƯỜI ĐỀ NGHỊ" , new Font(bf, 12, Font.BOLD));//NGUOI MUA
			hd.setAlignment(Element.ALIGN_CENTER);
			cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
			cell.setHorizontalAlignment(Element.ALIGN_MIDDLE);
			cell.setFixedHeight(1.63f*CONVERT);
			cell.addElement(hd);	
			
			table7.addCell(cell);
			
			cell = new PdfPCell();
			hd = new Paragraph(NGUOIDENGHI , new Font(bf, 12, Font.BOLD));//NGUOI MUA
			hd.setAlignment(Element.ALIGN_RIGHT);
			cell.setVerticalAlignment(Element.ALIGN_BOTTOM);
			cell.setHorizontalAlignment(Element.ALIGN_BOTTOM);
			cell.setFixedHeight(1.63f*CONVERT);
			cell.addElement(hd);	
			
			table7.addCell(cell);
			
			cell = new PdfPCell();
			hd = new Paragraph("TRƯỞNG BỘ PHẬN" , new Font(bf, 12, Font.BOLD));//NGUOI MUA
			hd.setAlignment(Element.ALIGN_CENTER);
			cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
			cell.setHorizontalAlignment(Element.ALIGN_MIDDLE);
			cell.setFixedHeight(1.63f*CONVERT);
			cell.addElement(hd);	
			
			table7.addCell(cell);
			
			cell = new PdfPCell();
			hd = new Paragraph("" , new Font(bf, 12, Font.BOLD));//NGUOI MUA
			hd.setAlignment(Element.ALIGN_RIGHT);
			cell.setVerticalAlignment(Element.ALIGN_BOTTOM);
			cell.setHorizontalAlignment(Element.ALIGN_BOTTOM);
			cell.setFixedHeight(1.63f*CONVERT);
			cell.addElement(hd);	
			
			table7.addCell(cell);
			
			cell = new PdfPCell();
			hd = new Paragraph("KẾ TOÁN TRƯỞNG" , new Font(bf, 12, Font.BOLD));//NGUOI MUA
			hd.setAlignment(Element.ALIGN_CENTER);
			cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
			cell.setHorizontalAlignment(Element.ALIGN_MIDDLE);
			cell.setFixedHeight(1.63f*CONVERT);
			cell.addElement(hd);	
			
			table7.addCell(cell);
			
			//LẤY KẾ TOÁN TRƯỞNG
			query = "select b.TEN from ERP_CHUCDANH a inner join NHANVIEN b on a.NHANVIEN_FK = b.PK_SEQ where a.PK_SEQ ='100001'";
			
			String ketoantruong = "";
			
			rs = db.get(query);
			try{
				if(rs != null){
					rs.next();
						ketoantruong = rs.getString("TEN");
					rs.close();
				}
			}catch(java.sql.SQLException e){
				System.out.println(e.toString());
			}
			
			cell = new PdfPCell();
			hd = new Paragraph(ketoantruong, new Font(bf, 12, Font.BOLD));//NGUOI MUA
			hd.setAlignment(Element.ALIGN_RIGHT);
			cell.setVerticalAlignment(Element.ALIGN_BOTTOM);
			cell.setHorizontalAlignment(Element.ALIGN_BOTTOM);
			cell.setFixedHeight(1.63f*CONVERT);
			cell.addElement(hd);	
			
			table7.addCell(cell);
			
			cell = new PdfPCell();
			hd = new Paragraph("TỔNG GIÁM ĐỐC" , new Font(bf, 12, Font.BOLD));//NGUOI MUA
			hd.setAlignment(Element.ALIGN_CENTER);
			cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
			cell.setHorizontalAlignment(Element.ALIGN_MIDDLE);
			cell.setFixedHeight(1.63f*CONVERT);
			cell.addElement(hd);	
			
			table7.addCell(cell);
			
			//LẤY KẾ TOÁN TRƯỞNG
			query = "select b.TEN from ERP_CHUCDANH a inner join NHANVIEN b on a.NHANVIEN_FK = b.PK_SEQ where a.PK_SEQ ='100000'";
			
			String tonggiamdoc = "";
			
			rs = db.get(query);
			try{
				if(rs != null){
					rs.next();
						tonggiamdoc = rs.getString("TEN");
					rs.close();
				}
			}catch(java.sql.SQLException e){
				System.out.println(e.toString());
			}
			
			cell = new PdfPCell();
			hd = new Paragraph(tonggiamdoc , new Font(bf, 12, Font.BOLD));//NGUOI MUA
			hd.setAlignment(Element.ALIGN_RIGHT);
			cell.setVerticalAlignment(Element.ALIGN_BOTTOM);
			cell.setHorizontalAlignment(Element.ALIGN_BOTTOM);
			cell.setFixedHeight(1.63f*CONVERT);
			cell.addElement(hd);	
					
			table7.addCell(cell);
			
			document.add(table7);
			
		}
		catch (Exception e)
		{
			System.out.println("115.Exception: " + e.getMessage());
			e.printStackTrace();
		}
		
	}

	


	private void inPDF(Document document, ServletOutputStream outstream, IErpDonmuahang_Giay obj) throws IOException {
		
		try
		{			
			NumberFormat formatter = new DecimalFormat("#,###,###"); 
			NumberFormat formatter2 = new DecimalFormat("#,###,##0.00000"); 
			NumberFormat formatter3 = new DecimalFormat("#,###,##0.000"); 
			
			PdfWriter.getInstance(document, outstream);
			document.open();
			//document.setPageSize(new Rectangle(210.0f, 297.0f));

			BaseFont bf = BaseFont.createFont("c:\\windows\\fonts\\times.ttf", BaseFont.IDENTITY_H, BaseFont.EMBEDDED);
			Font font = new Font(bf, 13, Font.BOLD);
			Font font2 = new Font(bf, 8, Font.BOLD);
			Font font9bold = new Font(bf, 9, Font.BOLD);
			Font font10bold = new Font(bf, 10, Font.BOLD);
			Font font11bold = new Font(bf, 11, Font.BOLD);
			Font font12bold = new Font(bf, 12, Font.BOLD);
			Font font13bold = new Font(bf, 13, Font.BOLD);
			Font font14bold = new Font(bf, 14, Font.BOLD);
			Font font15bold = new Font(bf, 15, Font.BOLD);
			Font font9 = new Font(bf, 9, Font.NORMAL);
			Font font10 = new Font(bf, 10, Font.NORMAL);
			Font font11 = new Font(bf, 11, Font.NORMAL);
			Font font12 = new Font(bf, 12, Font.NORMAL);
			Font font13 = new Font(bf, 13, Font.NORMAL);
			Font font14 = new Font(bf, 14, Font.NORMAL);
			
			//SIZE
			float CONVERT = 28.346457f; // = 1cm
			float[] TABLE_HEADER_WIDTHS = {3.5f * CONVERT, 8.1f * CONVERT, 5.0f * CONVERT };
			float[] TABLE_MIDDLE_WIDTHS = {3.5f * CONVERT, 13.1f * CONVERT};
			float[] TABLE_FOOTER_WIDTHS = {4.15f * CONVERT, 4.15f * CONVERT, 4.15f * CONVERT, 4.15f * CONVERT};			
			
			//Váº¼ khung header (Logo Picture | Header Titles)
			PdfPTable headerTable = new PdfPTable(TABLE_HEADER_WIDTHS.length);
			headerTable.setWidths(TABLE_HEADER_WIDTHS);
			headerTable.setWidthPercentage(100);
			
			PdfPCell cell;
			
			//header left
			PdfPTable headerLeftTable = new PdfPTable(1);
			headerLeftTable.setWidths(new float[] {TABLE_HEADER_WIDTHS[0]});
			headerLeftTable.setWidthPercentage(100);
			
			String[] imageSources = {
				"C:\\Program Files\\Apache Software Foundation\\8888\\webapps\\TraphacoERP\\pages\\images\\logoTraphacoERP.jpg",
				"C:\\Program Files (x86)\\Apache Software Foundation\\8888\\webapps\\TraphacoERP\\pages\\images\\logoTraphacoERP.jpg",
				"D:\\TOMCAT WEB SOURCE 2\\webapps\\TraphacoERP\\pages\\images\\logoTraphacoERP.jpg",
				"D:\\Project\\TraphacoERP\\TraphacoERP\\WebContent\\pages\\images\\logoTraphacoERP.jpg"
			}; 
			Image logoImage = null;
			
			for(int i = 0; i < imageSources.length; i++) {
				try {
					if(logoImage == null) {
						logoImage = Image.getInstance(imageSources[i]);
						System.out.println("[ErpPhieuxuatkhoPdfSvl.CreatePxk] imgSrc = " + imageSources[i]);
						break;
					}
				} catch (Exception e) {	}
			}
			if(logoImage != null) {
				System.out.println("[ErpPhieuxuatkhoPdfSvl.CreatePxk] Load Images Logo Thanh Cong....");
				logoImage.setBorder(0);
				logoImage.setAlignment(Element.ALIGN_CENTER);
				logoImage.scaleToFit(2.0f * CONVERT, 2.0f * CONVERT);
				//headerLeftTable.addCell(logoImage);
				logoImage.setAbsolutePosition(2.8f * CONVERT, PageSize.A4.getHeight() - 3.2f*CONVERT);
				
				document.add(logoImage);
				/**/
			} else {
				
			}
			System.out.println("[ErpPhieuxuatkhoPdfSvl.CreatePxk] Khong load duoc hinh anh logo");
			cell = new PdfPCell(new Paragraph(" ", new Font(bf, 8, Font.NORMAL)));
			cell.setBorder(Rectangle.NO_BORDER);
			cell.setFixedHeight(2.5f * CONVERT);
			headerLeftTable.addCell(cell);
			
			cell = new PdfPCell(new Paragraph("MPP: ", font9));
			cell.setBorderWidthTop(1);
			cell.setBorderWidthBottom(0);
			cell.setBorderWidthLeft(0);
			cell.setBorderWidthRight(0);
			cell.setFixedHeight(0.5f * CONVERT);
			headerLeftTable.addCell(cell);

			
			headerTable.addCell(headerLeftTable);
			
			//Header middle: PHIáº¾U Ä�á»€ NGHá»Š Táº M á»¨NG
			
			String tieude="PHIẾU ĐỀ NGHỊ THANH TOÁN";
			/*if(obj.getDoiTuongTamUng().equals("1"))
			{
				tieude = "PHIẾU ĐỀ NGHỊ THANH TOÁN";
			}
			else 
			{
				tieude = "PHIáº¾U Ä�á»€ NGHá»Š \n" +
						"THANH TOÃ�N TRÆ¯á»šC";
			}*/
			
			cell = new PdfPCell(new Paragraph(tieude, font15bold));
			cell.setFixedHeight(3.0f * CONVERT);
			cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
			cell.setHorizontalAlignment(Element.ALIGN_CENTER);
			headerTable.addCell(cell);
			
			//Header right: Table
			PdfPTable headerRightTable = new PdfPTable(2);
			headerRightTable.setWidths(new float[] {TABLE_HEADER_WIDTHS[2]*0.4f, TABLE_HEADER_WIDTHS[2]*0.6f});
			headerRightTable.setWidthPercentage(100);
			
			cell = new PdfPCell(new Paragraph("Loại", font10));
			cell.setBorder(Rectangle.NO_BORDER);
			cell.setFixedHeight(0.6f * CONVERT);
			cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
			headerRightTable.addCell(cell);
			
			cell = new PdfPCell(new Paragraph(": Biểu mẫu", font10));
			cell.setBorder(Rectangle.NO_BORDER);
			cell.setFixedHeight(0.6f * CONVERT);
			cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
			headerRightTable.addCell(cell);
			
			cell = new PdfPCell(new Paragraph("Mã số", font10));
			cell.setBorder(Rectangle.NO_BORDER);
			cell.setFixedHeight(0.6f * CONVERT);
			cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
			headerRightTable.addCell(cell);
			
			String maso=" : BM-KTTC-006";
		/*	if(obj.getDoiTuongTamUng().equals("1"))
			{
				maso = ": BM-KTTC-006";
			}
			else 
			{
				maso = ": BM-KTTC-012";
			}*/
			cell = new PdfPCell(new Paragraph(maso, font10));
			cell.setBorder(Rectangle.NO_BORDER);
			cell.setFixedHeight(0.6f * CONVERT);
			cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
			headerRightTable.addCell(cell);
			
			cell = new PdfPCell(new Paragraph("Soát xét", font10));
			cell.setBorder(Rectangle.NO_BORDER);
			cell.setFixedHeight(0.6f * CONVERT);
			cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
			headerRightTable.addCell(cell);
			
			cell = new PdfPCell(new Paragraph(": 01", font10));
			cell.setBorder(Rectangle.NO_BORDER);
			cell.setFixedHeight(0.6f * CONVERT);
			cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
			headerRightTable.addCell(cell);
			
			cell = new PdfPCell(new Paragraph("Điều chỉnh", font10));
			cell.setBorder(Rectangle.NO_BORDER);
			cell.setFixedHeight(0.6f * CONVERT);
			cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
			headerRightTable.addCell(cell);
			
			cell = new PdfPCell(new Paragraph(": 01", font10));
			cell.setBorder(Rectangle.NO_BORDER);
			cell.setFixedHeight(0.6f * CONVERT);
			cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
			headerRightTable.addCell(cell);
			
			cell = new PdfPCell(new Paragraph("Trang", font10));
			cell.setBorder(Rectangle.NO_BORDER);
			cell.setFixedHeight(0.6f * CONVERT);
			cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
			headerRightTable.addCell(cell);
			
			cell = new PdfPCell(new Paragraph(": 1/1", font10));
			cell.setBorder(Rectangle.NO_BORDER);
			cell.setFixedHeight(0.6f * CONVERT);
			cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
			headerRightTable.addCell(cell);
			
			headerTable.addCell(headerRightTable);
			
			document.add(headerTable);
			
			
			//Sá»‘
			Paragraph par = new Paragraph("Số: " + obj.getId(), font9);
			par.setAlignment(Element.ALIGN_CENTER);
			par.setSpacingBefore(0.5f * CONVERT);
			par.setSpacingAfter(0.2f * CONVERT);
			document.add(par);
			
			//Middle Table
			PdfPTable middleTable = new PdfPTable(TABLE_MIDDLE_WIDTHS.length);
			middleTable.setWidths(TABLE_MIDDLE_WIDTHS);
			middleTable.setWidthPercentage(100);
			
			
			cell = new PdfPCell(new Paragraph("Người yêu cầu ", font11));
			cell.setBorder(Rectangle.NO_BORDER);
			cell.setFixedHeight(0.7f * CONVERT);
			cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
			middleTable.addCell(cell);
			
		
			
			String hovaten= obj.getNvTen();
		/*	if(obj.getDoiTuongTamUng().equals("1"))
			{
				hovaten = obj.getTenHienThi();
			}
			else 
			{
				hovaten = obj.getNguoitao();
			}
			*/
			cell = new PdfPCell(new Paragraph(": " + hovaten, font11));
			cell.setBorder(Rectangle.NO_BORDER);
			cell.setFixedHeight(0.7f * CONVERT);
			cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
			middleTable.addCell(cell);
			
			cell = new PdfPCell(new Paragraph("Bộ phận", font11));
			cell.setBorder(Rectangle.NO_BORDER);
			cell.setFixedHeight(0.7f * CONVERT);
			cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
			middleTable.addCell(cell);
			
			cell = new PdfPCell(new Paragraph(": " + obj.getDvthId(), font11));
			cell.setBorder(Rectangle.NO_BORDER);
			cell.setFixedHeight(0.7f * CONVERT);
			cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
			middleTable.addCell(cell);
			
			cell = new PdfPCell(new Paragraph("Số tiền đề nghị ", font11));
			cell.setBorder(Rectangle.NO_BORDER);
			//cell.setFixedHeight(0.7f * CONVERT);
			cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
			middleTable.addCell(cell);
			
			String tienchu = "";
			try {		
				float sotien = Float.parseFloat( obj.getTongtiensauVat().replaceAll(",",""));			
				NumberFormat nf = new DecimalFormat("##########");
				long ltien = Long.parseLong(nf.format(sotien));
				tienchu = DocTien.docTien(ltien);
			} catch (Exception e) {
				System.out.println("Exception = " + e.getMessage());
			}
			
			cell = new PdfPCell(new Paragraph(": " + obj.getTongtiensauVat()  , font11));
			cell.setBorder(Rectangle.NO_BORDER);
			//cell.setFixedHeight(0.7f * CONVERT);
			cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
			middleTable.addCell(cell);
			
			
			
			 
			cell = new PdfPCell(new Paragraph("Bằng chữ", font11));
			cell.setBorder(Rectangle.NO_BORDER);
			cell.setFixedHeight(0.7f * CONVERT);
			cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
			middleTable.addCell(cell);
		
			cell = new PdfPCell(new Paragraph(": " + tienchu, font11));
			cell.setBorder(Rectangle.NO_BORDER);
			cell.setFixedHeight(0.7f * CONVERT);
			cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
			middleTable.addCell(cell);
			
			String lydo="Lý do thanh toán";
/*			if(obj.getDoiTuongTamUng().equals("1"))
			{
				lydo = "LÃ½ do táº¡m á»©ng";
			}
			else 
			{
				lydo = "LÃ½ do thanh toÃ¡n";
			}*/
			
			cell = new PdfPCell(new Paragraph(lydo, font11));
			cell.setBorder(Rectangle.NO_BORDER);
			cell.setFixedHeight(0.7f * CONVERT);
			cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
			middleTable.addCell(cell);
			
			cell = new PdfPCell(new Paragraph(": " + obj.getLydoTT(), font11));
			cell.setBorder(Rectangle.NO_BORDER);
			cell.setFixedHeight(0.7f * CONVERT);
			cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
			middleTable.addCell(cell);
			
			String ngay ="";
			String thang ="";
			String nam ="";
		/*	if(obj.getDoiTuongTamUng().equals("1"))
			{
				cell = new PdfPCell(new Paragraph("Thá»�i háº¡n thanh toÃ¡n", font11));
				cell.setBorder(Rectangle.NO_BORDER);
				cell.setFixedHeight(0.7f * CONVERT);
				cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
				middleTable.addCell(cell);
				
				ngay = obj.getThoiGianHoanUng().substring(8,10);
				thang = obj.getThoiGianHoanUng().substring(5,7);
				nam = obj.getThoiGianHoanUng().substring(0,4);
				cell = new PdfPCell(new Paragraph(": " + ngay + "-"+thang+"-" + nam, font11));
				cell.setBorder(Rectangle.NO_BORDER);
				cell.setFixedHeight(0.7f * CONVERT);
				cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
				middleTable.addCell(cell);
			}
			else 
			{*/
				cell = new PdfPCell(new Paragraph("Nhà cung cấp ", font11));
				cell.setBorder(Rectangle.NO_BORDER);
				cell.setFixedHeight(0.7f * CONVERT);
				cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
				middleTable.addCell(cell);
				
				cell = new PdfPCell(new Paragraph(": " +obj.getNccTen(), font11));
				cell.setBorder(Rectangle.NO_BORDER);
				cell.setFixedHeight(0.7f * CONVERT);
				cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
				middleTable.addCell(cell);
			/*}*/
			
			
			String duyet="";
		/*	if(obj.getDoiTuongTamUng().equals("1"))
			{
				duyet = "Duyá»‡t táº¡m á»©ng";
			}
			else 
			{
				duyet = "Duyá»‡t";
			}
			
			cell = new PdfPCell(new Paragraph(duyet, font11));
			cell.setBorder(Rectangle.NO_BORDER);
			cell.setFixedHeight(0.7f * CONVERT);
			cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
			middleTable.addCell(cell);
			
			cell = new PdfPCell(new Paragraph(": ", font11));// Ä�á»ƒ trá»‘ng
			cell.setBorder(Rectangle.NO_BORDER);
			cell.setFixedHeight(0.7f * CONVERT);
			cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
			middleTable.addCell(cell);*/
			
			document.add(middleTable);
			

			//NgÃ y thÃ¡ng nÄƒm
			ngay = obj.getNgaymuahang().substring(8,10);
			thang = obj.getNgaymuahang().substring(5,7);
			nam = obj.getNgaymuahang().substring(0,4);
			
			par = new Paragraph(" Ngày "+ngay+" tháng "+thang+" năm " + nam, font11);
			par.setAlignment(Element.ALIGN_RIGHT);
			par.setSpacingBefore(0.3f * CONVERT);
			par.setSpacingAfter(0.2f * CONVERT);
			document.add(par);
			
			//TABLE FOOTER
			PdfPTable tableFooter = new PdfPTable(TABLE_FOOTER_WIDTHS.length);
			tableFooter.setWidthPercentage(100);
			tableFooter.setHorizontalAlignment(Element.ALIGN_CENTER);
			tableFooter.setWidths(TABLE_FOOTER_WIDTHS);
			
			PdfPCell cell11 = new PdfPCell(new Paragraph("NGƯỜI ĐỀ NGHỊ", font9bold));
			cell11.setHorizontalAlignment(Element.ALIGN_LEFT);
			PdfPCell cell12 = new PdfPCell(new Paragraph("KẾ TOÁN TRƯỞNG ", font9bold));
			cell12.setHorizontalAlignment(Element.ALIGN_LEFT);
			PdfPCell cell13 = new PdfPCell(new Paragraph("T. BỘ PHẬN", font9bold));
			cell13.setHorizontalAlignment(Element.ALIGN_LEFT);
			PdfPCell cell14 = new PdfPCell(new Paragraph("BAN TỔNG GIÁM ĐỐC", font9bold));
			cell14.setHorizontalAlignment(Element.ALIGN_LEFT);
			cell11.setBorder(0);
			cell12.setBorder(0);
			cell13.setBorder(0);
			cell14.setBorder(0);
			tableFooter.addCell(cell11);
			tableFooter.addCell(cell12);
			tableFooter.addCell(cell13);
			tableFooter.addCell(cell14);
			
			
	/*		PdfPCell cell15 = new PdfPCell(new Paragraph( hovaten, font9));
			cell15.setBorder(Rectangle.NO_BORDER);
			cell15.setFixedHeight(2.0f * CONVERT);
			cell15.setVerticalAlignment(Element.ALIGN_BOTTOM);
			cell15.setColspan(4);
			tableFooter.addCell(cell15);*/
					
			
			
			document.add(tableFooter);
			document.close();
			
		}
		catch(DocumentException e)
		{
			e.printStackTrace();
		}
		
	}
	
	private String DinhDangTraphacoERP(String sotien)
	{
		sotien = sotien.replaceAll("\\.", "_");
		sotien = sotien.replaceAll(",", "\\.");
		sotien = sotien.replaceAll("_", ",");
		
		return sotien;
	}
}
