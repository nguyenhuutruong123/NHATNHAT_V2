package geso.dms.distributor.servlets.phieuchiNPP;

import geso.dms.center.beans.doctien.doctienrachu;
import geso.dms.distributor.beans.phieuchiNPP.IErpPhieuChiNPP;
import geso.dms.distributor.beans.phieuchiNPP.imp.ErpPhieuChiNPP;
import geso.dms.distributor.db.sql.dbutils;
import geso.dms.distributor.util.Utility;

import java.io.IOException;
import java.sql.ResultSet;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.itextpdf.text.Chunk;
import com.itextpdf.text.Document;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.Image;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.BaseFont;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

public class ErpInPhieuGiaoHangHOSvl1 extends HttpServlet{	
	private static final long serialVersionUID = 1L;

	public ErpInPhieuGiaoHangHOSvl1()
	{
		super();
	}


	float CONVERT = 28.346457f;  // =1cm
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		HttpSession session = request.getSession();
		String userId = (String) session.getAttribute("userId");
		String userTen = (String) session.getAttribute("userTen");
		Utility util = new Utility();
		if (userId.length() == 0)
			userId = request.getParameter("userId");
		String querystring = request.getQueryString();
		String id = util.antiSQLInspection(request.getParameter("id"));
		System.out.println("id:"+ id);
		String view = util.antiSQLInspection(request.getParameter("view"));
		if(view == null)
			view = "";

		/*IErpPhieuChiNPP pttBean = new ErpPhieuChiNPP(id);
		pttBean.setUserId(userId);
		pttBean.setView(view);
		pttBean.setnppId(userId);
		pttBean.Init();*/
		
		System.out.println(" \n user  id "+userId +"\n");
		String type = request.getParameter("type");
		System.out.println("11type:"+type);
		if(type == null)
			type = "";
		
		//=============khai bao doc
		response.setContentType("application/pdf");
		response.setHeader("Content-Disposition", " inline; filename=Phieugiaohang.pdf");
		Document document = new Document();
		ServletOutputStream outstream = response.getOutputStream();
		
	/*	this.CreatePxk(document, outstream, pttBean);*/
		this.CreatePxk(document, outstream);
	}


	private String getDate()
	{			
	    DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
	    Date date = new Date();
	    dateFormat.format(date);
		return dateFormat.format(date);
	}




	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		this.doGet(request, response);
		
		
	}


	private void form_phieuthu( Document document,ServletOutputStream outstream,dbutils db){
		
		try {
			//khai bao
			NumberFormat formatter = new DecimalFormat("#,###,###.##");
			NumberFormat formatter1 = new DecimalFormat("#,###,###");
			document.setPageSize(PageSize.A4);
			document.setMargins(1.5f*CONVERT, 1.5f*CONVERT, 0.5f*CONVERT, 0.5f*CONVERT); // L,R,T,B
			PdfWriter writer = PdfWriter.getInstance(document, outstream);
			PdfPCell cell ;
			Paragraph para;
			Chunk chunk;BaseFont charbase = BaseFont.createFont("C:\\windows\\fonts\\Wingding.ttf", BaseFont.IDENTITY_H, false);
			Font charfont = new Font(charbase, 11f, Font.NORMAL);
			
			char checked='\u00FE';
			char unchecked='\u00A8';
			
			
			BaseFont bf = BaseFont.createFont("c:\\windows\\fonts\\times.ttf", BaseFont.IDENTITY_H, BaseFont.EMBEDDED);
			Font font10_normal = new Font(bf, 10, Font.NORMAL);			
			Font font10_Bold = new Font(bf, 10, Font.BOLD);
			Font font10_ilatic = new Font(bf, 10, Font.ITALIC);
			
			Font font12_normal = new Font(bf, 12, Font.NORMAL);
			Font font12_Bold = new Font(bf, 12, Font.BOLD);
			Font font12_ilatic = new Font(bf, 12, Font.ITALIC);
			
			
			document.open() ;
			//================tao header1
			PdfPTable tableheader =new PdfPTable(2);
			tableheader.setWidthPercentage(100);
			tableheader.setHorizontalAlignment(Element.ALIGN_CENTER);
			float[] withd= {10f*CONVERT,12.0f*CONVERT};
			tableheader.setWidths(withd);
			
				// dong 1 logo 
				Image img = Image.getInstance(getServletContext().getInitParameter("pathPrint")+ "\\logo.gif");
				img.scalePercent(30);
				cell = new PdfPCell();
				cell.addElement(new Chunk(img,50,-10)); // vij tri dat anh 
				cell.setBorder(0);
				cell.setHorizontalAlignment(Element.ALIGN_CENTER);
				tableheader.addCell(cell);
			
				// dong 1 mau
				cell = new PdfPCell();
				cell.setBorder(0);
				cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
				para=new Paragraph("Mẫu: 3HD15-CN",font10_normal);
				para.setAlignment(Element.ALIGN_RIGHT);
				cell.addElement(para);
				tableheader.addCell(cell);
				
				// dong 2 trai  dia chi
				cell = new PdfPCell();
				cell.setBorder(0);
				cell.setHorizontalAlignment(Element.ALIGN_LEFT);
				chunk=new Chunk("CÔNG TY CỔ PHẦN DƯỢC PHẨM CỬU LONG",font10_normal);
				cell.addElement(chunk);
				tableheader.addCell(cell);
				
				//// dong 2 phai 
				cell = new PdfPCell();
				cell.setBorder(0);
				cell.setHorizontalAlignment(Element.ALIGN_CENTER);
				cell.setPaddingLeft(1.0f*CONVERT);
				para=new Paragraph("CỘNG HÒA XÃ HỘI CHỦ NGHĨA VIỆT NAM",font10_normal);
				para.setAlignment(Element.ALIGN_LEFT);
				cell.addElement(para);
				tableheader.addCell(cell);
				
				// dong 3 trai
				cell = new PdfPCell();
				cell.setBorder(0);
				cell.setPaddingLeft(3f*CONVERT);
				cell.setHorizontalAlignment(Element.ALIGN_CENTER);
				para=new Paragraph("---o0o---",font10_normal);
				para.setAlignment(Element.ALIGN_LEFT);
				cell.addElement(para);
				tableheader.addCell(cell);
				
				
				//dong 3 phai
				cell = new PdfPCell();
				cell.setBorder(0);
				cell.setHorizontalAlignment(Element.ALIGN_CENTER);
				para=new Paragraph("Độc Lập - Tự Do - Hạnh Phúc",font10_normal);
				para.setAlignment(Element.ALIGN_CENTER);
				cell.addElement(para);
				tableheader.addCell(cell);
				
				cell = new PdfPCell();
				cell.setBorder(0);
				cell.setHorizontalAlignment(Element.ALIGN_CENTER);
				para=new Paragraph("",font10_normal);
				para.setAlignment(Element.ALIGN_CENTER);
				cell.addElement(para);
				tableheader.addCell(cell);
				
				
				cell = new PdfPCell();
				cell.setBorder(0);
				cell.setHorizontalAlignment(Element.ALIGN_CENTER);
				para=new Paragraph("---o0o---",font10_normal);
				para.setAlignment(Element.ALIGN_CENTER);
				cell.addElement(para);
				tableheader.addCell(cell);
				
				
				
				// dong 4 tua de
				cell = new PdfPCell();
				cell.setBorder(0);
				cell.setColspan(2);
				cell.setHorizontalAlignment(Element.ALIGN_CENTER);
				para=new Paragraph("BIÊN BÁN GIAO HÀNG",new Font(bf,16,Font.BOLD));
				para.setAlignment(Element.ALIGN_CENTER);
				cell.addElement(para);
				tableheader.addCell(cell);
				
				// dong 5 hop dong
				cell = new PdfPCell();
				cell.setBorder(0);
				cell.setColspan(2);
				cell.setHorizontalAlignment(Element.ALIGN_CENTER);
				cell.setVerticalAlignment(Element.ALIGN_BOTTOM);
				para=new Paragraph("Theo hợp đồng số:                      ngày:            -Đợt:",font10_ilatic);
				para.setAlignment(Element.ALIGN_CENTER);
				cell.addElement(para);
				tableheader.addCell(cell);
				
				
				// dong 6 hoa don
				cell = new PdfPCell();
				cell.setBorder(0);
				cell.setColspan(2);
				cell.setHorizontalAlignment(Element.ALIGN_CENTER);
				para=new Paragraph("Theo hóa đơn số:             ngày:               ",font10_ilatic);
				para.setAlignment(Element.ALIGN_CENTER);
				cell.addElement(para);
				tableheader.addCell(cell);
				
				// dong 7 dms
				cell = new PdfPCell();
				cell.setBorder(0);
				cell.setColspan(2);
				cell.setHorizontalAlignment(Element.ALIGN_CENTER);
				para=new Paragraph("Số phiếu:              ngày                ",font10_ilatic);
				para.setAlignment(Element.ALIGN_CENTER);
				cell.addElement(para);
				tableheader.addCell(cell);
			
			document.add(tableheader);

			//== thong tin
			
			PdfPTable tbl =new PdfPTable(1);
			tbl.setWidthPercentage(100);
			
			// dong 1 2
			cell = new PdfPCell();
			cell.setBorder(0);
			cell.setHorizontalAlignment(Element.ALIGN_LEFT);
			para=new Paragraph("Hôm nay, ngày    /    /     Chúng tôi gồm có:",font10_normal);
			para.setAlignment(Element.ALIGN_LEFT);
			cell.addElement(para);
			para=new Paragraph("BÊN BÁN (BÊN A): CÔNG TY CỔ PHẦN DƯỢC PHẨM CỬU LONG",font10_Bold);
			para.setAlignment(Element.ALIGN_LEFT);
			cell.addElement(para);
			tbl.addCell(cell);
			
			
			// thong tin ben a
			cell = new PdfPCell();
			cell.setBorder(0);
			cell.setPaddingLeft(1.5f*CONVERT);
			cell.setHorizontalAlignment(Element.ALIGN_LEFT);
			
			para=new Paragraph("- Địa chỉ: ",font10_normal);
			para.setAlignment(Element.ALIGN_LEFT);
			cell.addElement(para);
			
			para=new Paragraph("- Mã số thuế: ",font10_normal);
			para.setAlignment(Element.ALIGN_LEFT);
			cell.addElement(para);
			
			para=new Paragraph("- Do ông/bà:               Làm đại diện",font10_normal);
			para.setAlignment(Element.ALIGN_LEFT);
			cell.addElement(para);
			
			para=new Paragraph("- Điện thoại:",font10_normal);
			para.setAlignment(Element.ALIGN_LEFT);
			cell.addElement(para);
			tbl.addCell(cell);
			
			// dong 
			cell = new PdfPCell();
			cell.setBorder(0);
			cell.setHorizontalAlignment(Element.ALIGN_LEFT);
			para=new Paragraph("BÊN MUA (BÊN B): ",font10_Bold);
			para.setAlignment(Element.ALIGN_LEFT);
			cell.addElement(para);
			tbl.addCell(cell);
			
			// thong tin ben b
			cell = new PdfPCell();
			cell.setBorder(0);
			cell.setPaddingLeft(1.5f*CONVERT);
			cell.setHorizontalAlignment(Element.ALIGN_LEFT);
			
			para=new Paragraph("- Địa chỉ: ",font10_normal);
			para.setAlignment(Element.ALIGN_LEFT);
			cell.addElement(para);
			
			para=new Paragraph("- Mã số thuế: ",font10_normal);
			para.setAlignment(Element.ALIGN_LEFT);
			cell.addElement(para);
			
			para=new Paragraph("- Do ông/bà:               Làm đại diện",font10_normal);
			para.setAlignment(Element.ALIGN_LEFT);
			cell.addElement(para);
			
			para=new Paragraph("- Điện thoại:",font10_normal);
			para.setAlignment(Element.ALIGN_LEFT);
			cell.addElement(para);
			tbl.addCell(cell);
			
			// dong 
			cell = new PdfPCell();
			cell.setBorder(0);
			cell.setHorizontalAlignment(Element.ALIGN_LEFT);
			para=new Paragraph("         Hai bên nhất trí xác nhận bên A đã giao hàng tại kho của bên B. "+
					"Hai bên cùng tiến hàng nghiệm thu một phần hợp đồng số:    ngày          . "+"" +
					"Hàng hóa đã được giao đúng số lượng, cụ thể như sau",font10_normal);
			para.setAlignment(Element.ALIGN_LEFT);
			cell.addElement(para);
			tbl.addCell(cell);
			document.add(tbl);
			
			
			//========bang danh sach san pham
			float[] tbl_withd = { 7.0f, 50f, 10f , 20.0f, 15.0f, 25f, 27f, 15.0f };
			PdfPTable tbl_sanpham = new PdfPTable(tbl_withd.length);
			tbl_sanpham.setWidthPercentage(100);
			tbl_sanpham.setSpacingBefore(0.3f * CONVERT);
			tbl_sanpham.setHorizontalAlignment(Element.ALIGN_LEFT);
			tbl_sanpham.setWidths(tbl_withd);
			PdfPCell cells;
			
			//add tieu de
			String[] tieude = new String[] { "Stt","Tên hàng","ĐVT","Đơn giá","Số lượng","Thành tiền","Số lô, hạn dùng","Ghi chú"};
			for (int j = 0; j < tbl_withd.length; j++)
			{
				cells = new PdfPCell(new Paragraph(tieude[j], new Font(bf, 10, Font.BOLD)));
				cells.setHorizontalAlignment(Element.ALIGN_CENTER);
				cells.setPaddingBottom(7);
				tbl_sanpham.addCell(cells);	
			}
			//danh sach
			
			//tong ket
			//cong tien hang 
			cell = new PdfPCell();
			cell.setBorderWidthBottom(0);
			cell.setBorderWidthRight(0);
			cell.setBorderWidthTop(0);
			cell.setColspan(4);
			cell.setVerticalAlignment(Element.ALIGN_LEFT);
			cell.setPaddingLeft(6.3f * CONVERT);
			para = new Paragraph("Cộng tiền hàng " , font10_normal);
			cell.addElement(para);
			tbl_sanpham.addCell(cell);
			
			cell = new PdfPCell();
			cell.setBorder(0);
			cell.setColspan(2);
			cell.setVerticalAlignment(Element.ALIGN_RIGHT);
			cell.setPaddingLeft(0.5f * CONVERT);
			para = new Paragraph(" đồng" , font10_normal);
			para.setAlignment(Element.ALIGN_RIGHT);
			cell.addElement(para);
			tbl_sanpham.addCell(cell);
			
			cell = new PdfPCell();
			cell.setBorderWidthBottom(0);
			cell.setBorderWidthLeft(0);
			cell.setBorderWidthTop(0);
			cell.setColspan(2);
			cell.setVerticalAlignment(Element.ALIGN_LEFT);
			para = new Paragraph("", font10_normal);
			cell.addElement(para);
			tbl_sanpham.addCell(cell);
			
			
			////tien giam
			cell = new PdfPCell();
			cell.setBorderWidthBottom(0);
			cell.setBorderWidthRight(0);
			cell.setBorderWidthTop(0);
			cell.setColspan(4);
			cell.setVerticalAlignment(Element.ALIGN_LEFT);
			cell.setPaddingLeft(6.3f * CONVERT);
			para = new Paragraph("Tiền giảm", font10_normal);
			cell.addElement(para);
			tbl_sanpham.addCell(cell);
			
			cell = new PdfPCell();
			cell.setBorder(0);
			cell.setColspan(2);
			cell.setVerticalAlignment(Element.ALIGN_RIGHT);
			cell.setPaddingLeft(0.5f * CONVERT);
			para = new Paragraph(" đồng" ,font10_normal);
			para.setAlignment(Element.ALIGN_RIGHT);
			cell.addElement(para);
			tbl_sanpham.addCell(cell);
			
			cell = new PdfPCell();
			cell.setBorderWidthBottom(0);
			cell.setBorderWidthTop(0);
			cell.setBorderWidthLeft(0);
			cell.setColspan(2);
			cell.setVerticalAlignment(Element.ALIGN_LEFT);
			para = new Paragraph("", font10_normal);
			cell.addElement(para);
			tbl_sanpham.addCell(cell);
			
			
			//chiet khau
			cell = new PdfPCell();
			cell.setBorderWidthBottom(0);
			cell.setBorderWidthRight(0);
			cell.setBorderWidthTop(0);
			cell.setColspan(4);
			cell.setVerticalAlignment(Element.ALIGN_LEFT);
			cell.setPaddingLeft(6.3f * CONVERT);
			para = new Paragraph("Tiền chiết khấu", font10_normal);
			cell.addElement(para);
			tbl_sanpham.addCell(cell);
			
			cell = new PdfPCell();
			cell.setBorder(0);
			cell.setColspan(2);
			cell.setVerticalAlignment(Element.ALIGN_RIGHT);
			cell.setPaddingLeft(0.5f * CONVERT);
			para = new Paragraph(" đồng" ,font10_normal);
			para.setAlignment(Element.ALIGN_RIGHT);
			cell.addElement(para);
			tbl_sanpham.addCell(cell);
			
			cell = new PdfPCell();
			cell.setBorderWidthBottom(0);
			cell.setBorderWidthTop(0);
			cell.setBorderWidthLeft(0);
			cell.setColspan(2);
			cell.setVerticalAlignment(Element.ALIGN_LEFT);
			para = new Paragraph("", font10_normal);
			cell.addElement(para);
			tbl_sanpham.addCell(cell);
			
			//thue vat
			cell = new PdfPCell();
			cell.setBorderWidthBottom(0);
			cell.setBorderWidthRight(0);
			cell.setBorderWidthTop(0);
			cell.setColspan(4);
			cell.setVerticalAlignment(Element.ALIGN_LEFT);
			cell.setPaddingLeft(6.3f * CONVERT);
			para = new Paragraph("Thuế GTGT", font10_normal);
			cell.addElement(para);
			tbl_sanpham.addCell(cell);
			
			cell = new PdfPCell();
			cell.setBorder(0);
			cell.setColspan(2);
			cell.setVerticalAlignment(Element.ALIGN_RIGHT);
			cell.setPaddingLeft(0.5f * CONVERT);
			para = new Paragraph(" đồng" , font10_normal);
			para.setAlignment(Element.ALIGN_RIGHT);
			cell.addElement(para);
			tbl_sanpham.addCell(cell);
			
			cell = new PdfPCell();
			cell.setBorderWidthBottom(0);
			cell.setBorderWidthTop(0);
			cell.setBorderWidthLeft(0);
			cell.setColspan(2);
			cell.setVerticalAlignment(Element.ALIGN_LEFT);
			para = new Paragraph("",font10_normal);
			cell.addElement(para);
			tbl_sanpham.addCell(cell);
			
			//tong cong
			cell = new PdfPCell();
			cell.setBorderWidthTop(0);
			cell.setBorderWidthRight(0);
			cell.setPaddingBottom(7);
			cell.setColspan(4);
			cell.setVerticalAlignment(Element.ALIGN_LEFT);
			cell.setPaddingLeft(6.3f * CONVERT);
			para = new Paragraph("Tổng cộng ",font10_normal);
			cell.addElement(para);
			tbl_sanpham.addCell(cell);
			
			cell = new PdfPCell();
			cell.setBorderWidthRight(0);
			cell.setBorderWidthTop(0);
			cell.setBorderWidthLeft(0);
			cell.setPaddingBottom(7);
			cell.setColspan(2);
			cell.setVerticalAlignment(Element.ALIGN_RIGHT);
			cell.setPaddingLeft(0.5f * CONVERT);
			para = new Paragraph(" đồng" ,font10_normal);
			para.setAlignment(Element.ALIGN_RIGHT);
			cell.addElement(para);
			tbl_sanpham.addCell(cell);
			
			cell = new PdfPCell();
			cell.setBorderWidthTop(0);
			cell.setBorderWidthLeft(0);
			cell.setPaddingBottom(7);
			cell.setColspan(2);
			cell.setVerticalAlignment(Element.ALIGN_LEFT);
			para = new Paragraph("", font10_normal);
			cell.addElement(para);
			tbl_sanpham.addCell(cell);
			document.add(tbl_sanpham);
			//=========== footer
			
			PdfPTable tbl_footer =new PdfPTable(1);
			tbl_footer.setWidthPercentage(90);
			
			cell = new PdfPCell();
			cell.setBorder(0);
			para = new Paragraph("Tổng cộng  ..............  Khoản. Gồm  .................  thùng (kiện), ................... hộp (chai).", font10_normal);
			cell.addElement(para);
			tbl_footer.addCell(cell);
			
			
			cell = new PdfPCell();
			cell.setBorder(0);
			para = new Paragraph();
			para.add(new Chunk("Tình trạng hàng hóa:   ", font10_normal ));
			para.add(new Chunk( String.valueOf(unchecked), charfont ));
			para.add(new Chunk("  Bình thường, bao bì nguyên vẹn, nguyên đai nguyên kiện.", font10_normal ));
			cell.addElement(para);
			tbl_footer.addCell(cell);
			
			cell = new PdfPCell();
			cell.setBorder(0);
			cell.setPaddingLeft(3.3f*CONVERT);
			para = new Paragraph();
			para.add(new Chunk( String.valueOf(unchecked), charfont ));
			para.add(new Chunk("  Khác ..................................................................."
					+"..............................................................", font10_normal ));
			cell.addElement(para);
			tbl_footer.addCell(cell);
			
			cell = new PdfPCell();
			cell.setBorder(0);
			para = new Paragraph("..................................................................................."+
					"..................................................................................................", font10_normal);
			cell.addElement(para);
			tbl_footer.addCell(cell);
			
			
			cell = new PdfPCell();
			cell.setBorder(0);
			para = new Paragraph();
			para.add(new Chunk("Chứng từ kèm theo:     ", font10_normal ));
			para.add(new Chunk( String.valueOf(unchecked), charfont ));
			para.add(new Chunk("   Hóa đơn GTGT,      ", font10_normal ));
			para.add(new Chunk( String.valueOf(unchecked), charfont ));
			para.add(new Chunk(" Khác  .................................................................................", font10_normal ));
			cell.addElement(para);
			tbl_footer.addCell(cell);
			
			cell = new PdfPCell();
			cell.setBorder(0);
			para = new Paragraph("Biên bản được lập thành   ...   bản, mỗi bên giữ   ...  bản và có giá trị như nhau.", font10_normal);
			cell.addElement(para);
			tbl_footer.addCell(cell);
			
			cell = new PdfPCell();
			cell.setBorder(0);
			cell.setPaddingLeft(1.5f*CONVERT);
			para = new Paragraph("ĐẠI DIỆN BÊN A"+
					"                                                                                         "+
					"ĐẠI DIỆN BÊN B", font10_Bold);
			cell.addElement(para);
			tbl_footer.addCell(cell);
			
			document.add(tbl_footer);
			
			
			document.close();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		
	}



	private void CreatePxk(Document document, ServletOutputStream outstream) throws IOException
	{
		dbutils db = new dbutils();

		try
		{			
			//LAY THONG TIN
			String sohopdong="sohopdong";
			String ngayhopdong="Ngayhopdong";
			String dothopdong="dot";
			String sohoadon="sohoadon";
			String ngayhoadon="ngayhoadon";
			
	   //in hoa don phieu thu
	 //  form_phieuthu(document, outstream, db);
	  // form_phieunghiemthu1(document, outstream, db);
	   form_phieunghiemthu2(document, outstream, db);

		} catch (Exception e)
		{
			System.out.println("115.Exception: " + e.getMessage());
			e.printStackTrace();
		}
	}


	private String DinhDangTraphacoDMS(String sotien)
	{
		sotien = sotien.replaceAll("\\.", "_");
		sotien = sotien.replaceAll(",", "\\.");
		sotien = sotien.replaceAll("_", ",");
		
		return sotien;
	}



	private void form_phieunghiemthu1( Document document,ServletOutputStream outstream,dbutils db){
		
		try {
			//khai bao
			NumberFormat formatter = new DecimalFormat("#,###,###.##");
			NumberFormat formatter1 = new DecimalFormat("#,###,###");
			document.setPageSize(PageSize.A4);
			document.setMargins(1.5f*CONVERT, 1.5f*CONVERT, 0.5f*CONVERT, 0.5f*CONVERT); // L,R,T,B
			PdfWriter writer = PdfWriter.getInstance(document, outstream);
			PdfPCell cell ;
			Paragraph para;
			Chunk chunk;BaseFont charbase = BaseFont.createFont("C:\\windows\\fonts\\Wingding.ttf", BaseFont.IDENTITY_H, false);
			Font charfont = new Font(charbase, 11f, Font.NORMAL);
			char checked='\u00FE';
			char unchecked='\u00A8';
			
			
			BaseFont bf = BaseFont.createFont("c:\\windows\\fonts\\times.ttf", BaseFont.IDENTITY_H, BaseFont.EMBEDDED);
			Font font10_normal = new Font(bf, 12, Font.NORMAL);			
			Font font10_Bold = new Font(bf, 12, Font.BOLD);
			Font font10_ilatic = new Font(bf, 12, Font.ITALIC);
			
			Font font12_normal = new Font(bf, 12, Font.NORMAL);
			Font font12_Bold = new Font(bf, 12, Font.BOLD);
			Font font12_ilatic = new Font(bf, 12, Font.ITALIC);
			
			
			document.open() ;
			//================tao header1
			PdfPTable tableheader =new PdfPTable(1);
			tableheader.setWidthPercentage(100);
			tableheader.setHorizontalAlignment(Element.ALIGN_CENTER);
			
				// dong 1 
				cell = new PdfPCell();
				cell.setBorder(0);
				cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
				para=new Paragraph("CỘNG HÒA XÃ HỘI CHỦ NGHĨA VIỆT NAM",font10_Bold);
				para.setAlignment(Element.ALIGN_CENTER);
				cell.addElement(para);
				tableheader.addCell(cell);
				
				// dong 2 
				cell = new PdfPCell();
				cell.setBorder(0);
				cell.setHorizontalAlignment(Element.ALIGN_LEFT);
				para=new Paragraph();
				para.setAlignment(Element.ALIGN_CENTER);
				chunk=new Chunk("Độc Lập - Tự Do - Hạnh Phúc",font10_Bold);
				chunk.setUnderline(1f, -2);
				para.add(chunk);
				cell.addElement(para);
				tableheader.addCell(cell);
				
				// dong 3 
				cell = new PdfPCell();
				cell.setBorder(0);
				cell.setPaddingTop(10);
				cell.setPaddingBottom(10);
				cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
				para=new Paragraph("BIÊN BẢN NGHIỆM THU ",new Font(bf,16,Font.BOLD));
				para.setAlignment(Element.ALIGN_CENTER);
				cell.addElement(para);
				tableheader.addCell(cell);
				
				//dong 4 5
				
				cell = new PdfPCell();
				cell.setBorder(0);
				cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
				para=new Paragraph("Căn cứ theo hợp đồng số:  ",font10_ilatic);
				para.setAlignment(Element.ALIGN_CENTER);
				cell.addElement(para);
				para=new Paragraph("Hôm nay, lúc  ... giờ ...  phút, ngày .... Tháng ..... năm 2016. , chúng tôi gồm có:",font10_ilatic);
				para.setAlignment(Element.ALIGN_CENTER);
				cell.addElement(para);
				tableheader.addCell(cell);
			document.add(tableheader);

			//== thong tin
			
			PdfPTable tbl =new PdfPTable(1);
			tbl.setWidthPercentage(100);
			
			// dong 1 2
			cell = new PdfPCell();
			cell.setBorder(0);
			cell.setHorizontalAlignment(Element.ALIGN_LEFT);
			para=new Paragraph();
			para.setAlignment(Element.ALIGN_LEFT);
			chunk=new Chunk("BÊN A (Bên nhận)", font10_Bold);
			chunk.setUnderline(1f, -2);
			para.add(chunk);
			chunk=new Chunk(" : TEN BEN NHAN", font10_Bold);
			para.add(chunk);
			cell.addElement(para);
			tbl.addCell(cell);
			
			
			// thong tin ben a
			cell = new PdfPCell();
			cell.setBorder(0);
			cell.setPaddingLeft(1.5f*CONVERT);
			cell.setHorizontalAlignment(Element.ALIGN_LEFT);
			
			para=new Paragraph("Địa chỉ: ",font10_normal);
			para.setAlignment(Element.ALIGN_LEFT);
			cell.addElement(para);
			
			para=new Paragraph("Điện thoại:",font10_normal); // kem fax
			para.setAlignment(Element.ALIGN_LEFT);
			cell.addElement(para);
			
			para=new Paragraph("Mã số thuế: ",font10_normal);
			para.setAlignment(Element.ALIGN_LEFT);
			cell.addElement(para);
			
			
			para=new Paragraph("Tài khoản:  ",font10_normal); //tai ngan hang nao?
			para.setAlignment(Element.ALIGN_LEFT);
			cell.addElement(para);
			
			para=new Paragraph("Đại diện:                                  Chức vụ:",font10_normal);
			para.setAlignment(Element.ALIGN_LEFT);
			cell.addElement(para);
			tbl.addCell(cell);
			
			// ben giao
			cell = new PdfPCell();
			cell.setBorder(0);
			cell.setHorizontalAlignment(Element.ALIGN_LEFT);
			para=new Paragraph();
			para.setAlignment(Element.ALIGN_LEFT);
			chunk=new Chunk("BÊN B (Bên giao)", font10_Bold);
			chunk.setUnderline(1f, -2);
			para.add(chunk);
			
			chunk=new Chunk(" : CÔNG TY CỔ PHẦN DƯỢC PHẨM CỬU LONG", font10_Bold);
			para.add(chunk);
			cell.addElement(para);
			tbl.addCell(cell);
			
			// thong tin ben b
			cell = new PdfPCell();
			cell.setBorder(0);
			cell.setPaddingLeft(1.5f*CONVERT);
			cell.setHorizontalAlignment(Element.ALIGN_LEFT);
			
			para=new Paragraph("Địa chỉ: 150, đường 14/9, phường 5, TP Vĩnh Long, tỉnh Vĩnh Long",font10_normal);
			para.setAlignment(Element.ALIGN_LEFT);
			cell.addElement(para);
			
			para=new Paragraph("Điện thoại: 0703.823.268  – Fax: 0703.823.710",font10_normal); // kem fax
			para.setAlignment(Element.ALIGN_LEFT);
			cell.addElement(para);
			
			para=new Paragraph("Mã số thuế: 1500202535",font10_normal);
			para.setAlignment(Element.ALIGN_LEFT);
			cell.addElement(para);
			
			
			para=new Paragraph("Tài khoản: 73010000008023 ",font10_normal); //tai ngan hang nao?
			para.setAlignment(Element.ALIGN_LEFT);
			cell.addElement(para);
			
			para=new Paragraph("Đại diện:                                  Chức vụ:",font10_normal);
			para.setAlignment(Element.ALIGN_LEFT);
			cell.addElement(para);
			tbl.addCell(cell);
			
			// so hop dong
			cell = new PdfPCell();
			cell.setBorder(0);
			cell.setHorizontalAlignment(Element.ALIGN_LEFT);
			para=new Paragraph("      Hai bên cùng tiến hành nghiệm thu một phần của  hợp đồng số: sohopdong ngày 08  tháng 6 năm 2015  như sau:",font10_normal);
			para.setAlignment(Element.ALIGN_LEFT);
			cell.addElement(para);
			tbl.addCell(cell);
			document.add(tbl);
			
			
			//========bang danh sach san pham
			float[] tbl_withd = { 7.0f, 50f, 10f , 20.0f, 15.0f, 25f, 15f,15f, 15.0f };
			PdfPTable tbl_sanpham = new PdfPTable(tbl_withd.length);
			tbl_sanpham.setWidthPercentage(100);
			tbl_sanpham.setSpacingBefore(0.3f * CONVERT);
			tbl_sanpham.setHorizontalAlignment(Element.ALIGN_LEFT);
			tbl_sanpham.setWidths(tbl_withd);
			PdfPCell cells;
			
			//add tieu de
			String[] tieude = new String[] { "STT","Tên Hàng Hoá","ĐVT","Số lượng","Đơn giá","Thành tiền \n (VNĐ)","Số lô","Hạn dùng","Nước SX – Hãng SX"};
			for (int j = 0; j < tbl_withd.length; j++)
			{
				cells = new PdfPCell(new Paragraph(tieude[j], new Font(bf, 10, Font.BOLD)));
				cells.setHorizontalAlignment(Element.ALIGN_CENTER);
				cells.setPaddingBottom(7);
				tbl_sanpham.addCell(cells);	
			}
			//danh sach
			
			
			//cong tien hang 
			cell = new PdfPCell();
			cell.setBorderWidthBottom(0);
			cell.setBorderWidthRight(0);
			cell.setBorderWidthTop(0);
			cell.setColspan(2);
			cell.setVerticalAlignment(Element.ALIGN_LEFT);
			cell.setPaddingLeft(1f*CONVERT);
			para = new Paragraph("Cộng " , new Font(bf, 10, Font.NORMAL));
			cell.addElement(para);
			tbl_sanpham.addCell(cell);

			cell = new PdfPCell();
			cell.setBorder(0);
			cell.setColspan(4);
			cell.setVerticalAlignment(Element.ALIGN_RIGHT);
			cell.setPaddingLeft(0.5f * CONVERT);
			para = new Paragraph(" đồng" , new Font(bf, 10, Font.NORMAL));
			para.setAlignment(Element.ALIGN_RIGHT);
			cell.addElement(para);
			tbl_sanpham.addCell(cell);
			
			cell = new PdfPCell();
			cell.setBorderWidthBottom(0);
			cell.setBorderWidthLeft(0);
			cell.setBorderWidthTop(0);
			cell.setColspan(3);
			cell.setVerticalAlignment(Element.ALIGN_LEFT);
			para = new Paragraph("", new Font(bf, 10, Font.NORMAL));
			cell.addElement(para);
			tbl_sanpham.addCell(cell);
			
			
			
			
			
			//thue vat
			cell = new PdfPCell();
			cell.setBorderWidthBottom(0);
			cell.setBorderWidthRight(0);
			cell.setBorderWidthTop(0);
			cell.setColspan(2);
			cell.setVerticalAlignment(Element.ALIGN_LEFT);
			cell.setPaddingLeft(1f*CONVERT);
			para = new Paragraph("Thuế VAT (5%)", new Font(bf, 10, Font.NORMAL));
			cell.addElement(para);
			tbl_sanpham.addCell(cell);
			
			cell = new PdfPCell();
			cell.setBorder(0);
			cell.setColspan(4);
			cell.setVerticalAlignment(Element.ALIGN_RIGHT);
			cell.setPaddingLeft(0.5f * CONVERT);
			para = new Paragraph(" đồng" , new Font(bf, 10, Font.NORMAL));
			para.setAlignment(Element.ALIGN_RIGHT);
			cell.addElement(para);
			tbl_sanpham.addCell(cell);
			
			cell = new PdfPCell();
			cell.setBorderWidthBottom(0);
			cell.setBorderWidthTop(0);
			cell.setBorderWidthLeft(0);
			cell.setColspan(3);
			cell.setVerticalAlignment(Element.ALIGN_LEFT);
			para = new Paragraph("",new Font(bf, 10, Font.NORMAL));
			cell.addElement(para);
			tbl_sanpham.addCell(cell);
			
			//tong cong
			cell = new PdfPCell();
			cell.setBorderWidthTop(0);
			cell.setBorderWidthRight(0);
			cell.setPaddingBottom(7);
			cell.setColspan(2);
			cell.setVerticalAlignment(Element.ALIGN_LEFT);
			cell.setPaddingLeft(1f*CONVERT);
			para = new Paragraph("Giá trị thanh toán ",new Font(bf, 10, Font.NORMAL));
			cell.addElement(para);
			tbl_sanpham.addCell(cell);
			
			cell = new PdfPCell();
			cell.setBorderWidthRight(0);
			cell.setBorderWidthTop(0);
			cell.setBorderWidthLeft(0);
			cell.setPaddingBottom(7);
			cell.setColspan(4);
			cell.setVerticalAlignment(Element.ALIGN_RIGHT);
			cell.setPaddingLeft(0.5f * CONVERT);
			para = new Paragraph(" đồng" ,new Font(bf, 10, Font.NORMAL));
			para.setAlignment(Element.ALIGN_RIGHT);
			cell.addElement(para);
			tbl_sanpham.addCell(cell);
			
			cell = new PdfPCell();
			cell.setBorderWidthTop(0);
			cell.setBorderWidthLeft(0);
			cell.setPaddingBottom(7);
			cell.setColspan(3);
			cell.setVerticalAlignment(Element.ALIGN_LEFT);
			para = new Paragraph("", new Font(bf, 10, Font.NORMAL));
			cell.addElement(para);
			tbl_sanpham.addCell(cell);
			document.add(tbl_sanpham);
			
			
			
			
			
			
			//=========== footer
			
			PdfPTable tbl_footer =new PdfPTable(1);
			tbl_footer.setWidthPercentage(100);
			
			cell = new PdfPCell();
			cell.setBorder(0);
			para = new Paragraph("      Số tiền bằng chữ:  ", new Font(bf,12,Font.BOLDITALIC)); //cong doc tine
			para.setAlignment(Element.ALIGN_JUSTIFIED);
			cell.addElement(para);
			
			para = new Paragraph("      Biên bản giao hàng kèm theo hóa đơn tài chính xuất hàng số  45168  ngày  06/01/2016  ", font10_normal); 
			para.setAlignment(Element.ALIGN_JUSTIFIED);
			cell.addElement(para);
			
			para = new Paragraph("      -  Hàng giao đủ số lượng, đúng chủng lọai, qui cách, hàm lượng, hạn dùng theo hợp đồng đã ký kết. ", font10_normal); 
			para.setAlignment(Element.ALIGN_JUSTIFIED);
			cell.addElement(para);
			
			para = new Paragraph("      -  Cảm quan: màu sắc, hình dạng nguyên vẹn.", font10_normal); 
			cell.addElement(para);
			
			para = new Paragraph();
			para.setAlignment(Element.ALIGN_CENTER);
			para.add(new Chunk("Đạt  ", font10_normal ));
			para.add(new Chunk( String.valueOf(unchecked), charfont ));
			para.add(new Chunk("                Không đạt  ", font10_normal ));
			para.add(new Chunk( String.valueOf(unchecked), charfont ));
			cell.addElement(para);
			
			
			para = new Paragraph("      Hai bên thống nhất nghiệm thu hàng hóa trên và cùng ký vào biên bản nghiệm thu.", font10_normal); 
			para.setAlignment(Element.ALIGN_JUSTIFIED);
			cell.addElement(para);
			
			para = new Paragraph("      Biên bản lập thành 04 bản, bên A nhận 03 bản, bên B  nhận 01 bản, đều có giá trị như nhau.", font10_normal); 
			para.setAlignment(Element.ALIGN_JUSTIFIED);
			cell.addElement(para);
			tbl_footer.addCell(cell);
			
			cell = new PdfPCell();
			cell.setBorder(0);
			cell.setPaddingLeft(1.5f*CONVERT);
			para = new Paragraph("ĐẠI DIỆN BÊN A"+
					"                                                                               "+
					"ĐẠI DIỆN BÊN B", font10_Bold);
			cell.addElement(para);
			tbl_footer.addCell(cell);
			
			cell = new PdfPCell();
			cell.setBorder(0);
			cell.setPaddingLeft(1.5f*CONVERT);
			para = new Paragraph("       GIÁM ĐỐC                                              "+
					"                                                       TUQ. TỔNG GIÁM ĐỐC", new Font(bf,10,Font.BOLD));
			cell.addElement(para);
			tbl_footer.addCell(cell);
			
			document.add(tbl_footer);

			document.close();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		
	}



	

	private void form_phieunghiemthu2( Document document,ServletOutputStream outstream,dbutils db){
		
		try {
			//khai bao
			NumberFormat formatter = new DecimalFormat("#,###,###.##");
			NumberFormat formatter1 = new DecimalFormat("#,###,###");
			document.setPageSize(PageSize.A4);
			document.setMargins(1.5f*CONVERT, 1.5f*CONVERT, 0.5f*CONVERT, 0.5f*CONVERT); // L,R,T,B
			PdfWriter writer = PdfWriter.getInstance(document, outstream);
			PdfPCell cell ;
			Paragraph para;
			Chunk chunk;BaseFont charbase = BaseFont.createFont("C:\\windows\\fonts\\Wingding.ttf", BaseFont.IDENTITY_H, false);
			Font charfont = new Font(charbase, 11f, Font.NORMAL);
			char checked='\u00FE';
			char unchecked='\u00A8';
			
			
			BaseFont bf = BaseFont.createFont("c:\\windows\\fonts\\times.ttf", BaseFont.IDENTITY_H, BaseFont.EMBEDDED);
			Font font10_normal = new Font(bf, 12, Font.NORMAL);			
			Font font10_Bold = new Font(bf, 12, Font.BOLD);
			Font font10_ilatic = new Font(bf, 12, Font.ITALIC);
			
			Font font12_normal = new Font(bf, 12, Font.NORMAL);
			Font font12_Bold = new Font(bf, 12, Font.BOLD);
			Font font12_ilatic = new Font(bf, 12, Font.ITALIC);
			
			
			document.open() ;
			//================tao header1
			PdfPTable tableheader =new PdfPTable(1);
			tableheader.setWidthPercentage(100);
			tableheader.setHorizontalAlignment(Element.ALIGN_CENTER);
			
				// dong 1 
				cell = new PdfPCell();
				cell.setBorder(0);
				cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
				para=new Paragraph("CỘNG HÒA XÃ HỘI CHỦ NGHĨA VIỆT NAM",font10_Bold);
				para.setAlignment(Element.ALIGN_CENTER);
				cell.addElement(para);
				tableheader.addCell(cell);
				
				// dong 2 
				cell = new PdfPCell();
				cell.setBorder(0);
				cell.setHorizontalAlignment(Element.ALIGN_LEFT);
				para=new Paragraph();
				para.setAlignment(Element.ALIGN_CENTER);
				chunk=new Chunk("Độc Lập - Tự Do - Hạnh Phúc",font10_Bold);
				chunk.setUnderline(1f, -2);
				para.add(chunk);
				cell.addElement(para);
				tableheader.addCell(cell);
				
				// dong 3 
				cell = new PdfPCell();
				cell.setBorder(0);
				cell.setPaddingTop(10);
				cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
				para=new Paragraph("BIÊN BẢN",new Font(bf,16,Font.BOLD));
				para.setAlignment(Element.ALIGN_CENTER);
				cell.addElement(para);
				tableheader.addCell(cell);
				
				cell = new PdfPCell();
				cell.setBorder(0);
				cell.setPaddingBottom(10);
				cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
				para=new Paragraph("NGHIỆM THU THUỐC, VTYTTH, HÓA CHẤT, DỊCH TRUYỀN",new Font(bf,16,Font.BOLD));
				para.setAlignment(Element.ALIGN_CENTER);
				cell.addElement(para);
				tableheader.addCell(cell);
				
				//dong 4 5
				
				cell = new PdfPCell();
				cell.setBorder(0);
				cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
				para=new Paragraph("Căn cứ Hợp đồng số:........./ …...... ngày........ tháng....... năm ........",font10_ilatic);
				para.setAlignment(Element.ALIGN_CENTER);
				cell.addElement(para);
				para=new Paragraph("Hôm nay, lúc  ... giờ ...  phút, ngày .... Tháng ..... năm 2016 tại ......... , chúng tôi gồm có:",font10_ilatic);
				para.setAlignment(Element.ALIGN_CENTER);
				cell.addElement(para);
				tableheader.addCell(cell);
				
				
			document.add(tableheader);

			//== thong tin
			
			PdfPTable tbl =new PdfPTable(1);
			tbl.setWidthPercentage(100);
			
			// dong 1 2
			cell = new PdfPCell();
			cell.setBorder(0);
			cell.setHorizontalAlignment(Element.ALIGN_LEFT);
			para=new Paragraph();
			para.setAlignment(Element.ALIGN_LEFT);
			chunk=new Chunk("BÊN NHẬN (Bên A)", font10_Bold);
			chunk.setUnderline(1f, -2);
			para.add(chunk);
			chunk=new Chunk(" : TRUNG TÂM Y TẾ THÀNH PHỐ SA ĐÉC", font10_Bold);
			para.add(chunk);
			cell.addElement(para);
			tbl.addCell(cell);
			
			
			// thong tin ben a
			cell = new PdfPCell();
			cell.setBorder(0);
			cell.setPaddingLeft(1.5f*CONVERT);
			cell.setHorizontalAlignment(Element.ALIGN_LEFT);
			
			para=new Paragraph("Địa chỉ: ",font10_normal);
			para.setAlignment(Element.ALIGN_LEFT);
			cell.addElement(para);
			
			para=new Paragraph("Điện thoại:",font10_normal); // kem fax
			para.setAlignment(Element.ALIGN_LEFT);
			cell.addElement(para);
			
			para=new Paragraph("Mã số thuế: ",font10_normal);
			para.setAlignment(Element.ALIGN_LEFT);
			cell.addElement(para);
			
			
			para=new Paragraph("Tài khoản:  ",font10_normal); //tai ngan hang nao?
			para.setAlignment(Element.ALIGN_LEFT);
			cell.addElement(para);
			
			para=new Paragraph("Đại diện:                                  Chức vụ:",font10_normal);
			para.setAlignment(Element.ALIGN_LEFT);
			cell.addElement(para);
			tbl.addCell(cell);
			
			// ben giao
			cell = new PdfPCell();
			cell.setBorder(0);
			cell.setHorizontalAlignment(Element.ALIGN_LEFT);
			para=new Paragraph();
			para.setAlignment(Element.ALIGN_LEFT);
			chunk=new Chunk("BÊN GIAO (Bên B)", font10_Bold);
			chunk.setUnderline(1f, -2);
			para.add(chunk);
			
			chunk=new Chunk(" : CÔNG TY CỔ PHẦN DƯỢC PHẨM CỬU LONG", font10_Bold);
			para.add(chunk);
			cell.addElement(para);
			tbl.addCell(cell);
			
			// thong tin ben b
			cell = new PdfPCell();
			cell.setBorder(0);
			cell.setPaddingLeft(1.5f*CONVERT);
			cell.setHorizontalAlignment(Element.ALIGN_LEFT);
			
			para=new Paragraph("Địa chỉ: 150, đường 14/9, phường 5, TP Vĩnh Long, tỉnh Vĩnh Long",font10_normal);
			para.setAlignment(Element.ALIGN_LEFT);
			cell.addElement(para);
			
			para=new Paragraph("Điện thoại: 0703.823.268  – Fax: 0703.823.710",font10_normal); // kem fax
			para.setAlignment(Element.ALIGN_LEFT);
			cell.addElement(para);
			
			para=new Paragraph("Mã số thuế: 1500202535",font10_normal);
			para.setAlignment(Element.ALIGN_LEFT);
			cell.addElement(para);
			
			
			para=new Paragraph("Tài khoản: 73010000008023 ",font10_normal); //tai ngan hang nao?
			para.setAlignment(Element.ALIGN_LEFT);
			cell.addElement(para);
			
			para=new Paragraph("Đại diện:                                  Chức vụ:",font10_normal);
			para.setAlignment(Element.ALIGN_LEFT);
			cell.addElement(para);
			tbl.addCell(cell);
			
			//loi xac nhan
			cell = new PdfPCell();
			cell.setBorder(0);
			cell.setHorizontalAlignment(Element.ALIGN_LEFT);
			para=new Paragraph("      Hai bên thống nhất nghiệm thu và bàn giao thuốc, VTYTTH, hóa chất, dịch truyền theo Hợp đồng đã ký với chi tiết như sau:",font10_normal);
			para.setAlignment(Element.ALIGN_LEFT);
			cell.addElement(para);
			para=new Paragraph("Nội dung:",font10_Bold);
			para.setAlignment(Element.ALIGN_LEFT);
			cell.addElement(para);
			para=new Paragraph("      Bên B bàn giao cho bên A thuốc, VTYTTH, hóa chất, dịch truyền theo hóa đơn:",font10_normal);
			para.setAlignment(Element.ALIGN_LEFT);
			cell.addElement(para);
			tbl.addCell(cell);
			
			document.add(tbl);
			
			
			//========bang danh sach hoa don
			float[] tbl_withd = { 10.0f, 50f, 40.0f, 35.0f };
			PdfPTable tbl_sanpham = new PdfPTable(tbl_withd.length);
			tbl_sanpham.setWidthPercentage(100);
			tbl_sanpham.setSpacingBefore(0.3f * CONVERT);
			tbl_sanpham.setHorizontalAlignment(Element.ALIGN_LEFT);
			tbl_sanpham.setWidths(tbl_withd);
			PdfPCell cells;
			
			//add tieu de
			String[] tieude = new String[] { "TT","Số HĐ","Ngày xuất hóa đơn nghiệm thu","Thành tiền \n (VNĐ)"};
			for (int j = 0; j < tbl_withd.length; j++)
			{
				cells = new PdfPCell(new Paragraph(tieude[j], new Font(bf, 10, Font.BOLD)));
				cells.setHorizontalAlignment(Element.ALIGN_CENTER);
				cells.setPaddingBottom(7);
				tbl_sanpham.addCell(cells);	
			}
			//danh sach
			
			
			//cong 
			cell = new PdfPCell();
			cell.setVerticalAlignment(Element.ALIGN_LEFT);
			para = new Paragraph("", new Font(bf, 10, Font.NORMAL));
			cell.addElement(para);
			tbl_sanpham.addCell(cell);
			
			cell = new PdfPCell();
			cell.setColspan(2);
			cell.setVerticalAlignment(Element.ALIGN_LEFT);
			cell.setPaddingBottom(7);
			para = new Paragraph("TỔNG CỘNG " , new Font(bf, 10, Font.BOLD));
			para.setAlignment(Element.ALIGN_CENTER);
			cell.addElement(para);
			tbl_sanpham.addCell(cell);

			cell = new PdfPCell();
			cell.setVerticalAlignment(Element.ALIGN_RIGHT);
			cell.setPaddingBottom(7);
			para = new Paragraph(" đồng" , new Font(bf, 10, Font.BOLD));
			para.setAlignment(Element.ALIGN_RIGHT);
			cell.addElement(para);
			tbl_sanpham.addCell(cell);
			
			document.add(tbl_sanpham);

			//=========== footer
			
			PdfPTable tbl_footer =new PdfPTable(1);
			tbl_footer.setWidthPercentage(100);
			
			cell = new PdfPCell();
			cell.setBorder(0);
			para = new Paragraph("      Số tiền bằng chữ:  ", new Font(bf,12,Font.NORMAL)); //cong doc tine
			para.setAlignment(Element.ALIGN_JUSTIFIED);
			cell.addElement(para);
			
			para = new Paragraph("Kết luận: ", new Font(bf,12,Font.BOLD)); //cong doc tine
			para.setAlignment(Element.ALIGN_JUSTIFIED);
			cell.addElement(para);
			
			para = new Paragraph("      - Bên A đã kiểm tra đúng chủng loại, hàm lượng, số lô, hạn dùng,…….. của hàng hóa theo yêu cầu.", font10_normal); 
			para.setAlignment(Element.ALIGN_JUSTIFIED);
			cell.addElement(para);
			
			para = new Paragraph("      - Bên A đã nhận đầy đủ số lượng hàng hóa. ", font10_normal); 
			para.setAlignment(Element.ALIGN_JUSTIFIED);
			cell.addElement(para);
			
			para = new Paragraph("      - Trong thời gian sử dụng nếu bên A phát hiện bên trong có sự thiếu hụt về số lượng hoặc kém chất lượng ……,"+
					" Bên A sẽ thông báo ngay cho Bên B bằng cách điện thoại, fax hoặc bằng văn bản để cùng bàn bạc giải quyết.", font10_normal); 
			para.setAlignment(Element.ALIGN_JUSTIFIED);
			cell.addElement(para);
			
			para = new Paragraph("      - Bên A thống nhất nghiệm thu và thanh toán tiền thuốc, hóa chất, VTYTTHcho bên B theo đúng Hợp đồng đã ký kết.", font10_normal); 
			para.setAlignment(Element.ALIGN_JUSTIFIED);
			cell.addElement(para);
			
			
			
			para = new Paragraph("      Biên bản lập thành 04 bản, bên A nhận 03 bản, bên B  nhận 01 bản, đều có giá trị như nhau.", font10_normal); 
			para.setAlignment(Element.ALIGN_JUSTIFIED);
			cell.addElement(para);
			tbl_footer.addCell(cell);
			
			cell = new PdfPCell();
			cell.setBorder(0);
			cell.setPaddingLeft(1.5f*CONVERT);
			para = new Paragraph("ĐẠI DIỆN BÊN A"+
					"                                                                               "+
					"ĐẠI DIỆN BÊN B", font10_Bold);
			cell.addElement(para);
			tbl_footer.addCell(cell);
			
			cell = new PdfPCell();
			cell.setBorder(0);
			cell.setPaddingLeft(1.5f*CONVERT);
			para = new Paragraph("       GIÁM ĐỐC                                              "+
					"                                                       TUQ. TỔNG GIÁM ĐỐC", new Font(bf,10,Font.BOLD));
			cell.addElement(para);
			tbl_footer.addCell(cell);
			
			document.add(tbl_footer);

			document.close();
			
		} catch (Exception e) {
			e.printStackTrace();

	}

	}

}
