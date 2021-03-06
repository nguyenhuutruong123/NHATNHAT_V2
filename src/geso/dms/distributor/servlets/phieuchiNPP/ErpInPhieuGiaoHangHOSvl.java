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

public class ErpInPhieuGiaoHangHOSvl extends HttpServlet{	
	private static final long serialVersionUID = 1L;

	public ErpInPhieuGiaoHangHOSvl()
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
				para=new Paragraph("M???u: 3HD15-CN",font10_normal);
				para.setAlignment(Element.ALIGN_RIGHT);
				cell.addElement(para);
				tableheader.addCell(cell);
				
				// dong 2 trai  dia chi
				cell = new PdfPCell();
				cell.setBorder(0);
				cell.setHorizontalAlignment(Element.ALIGN_LEFT);
				chunk=new Chunk("C??NG TY C??? PH???N D?????C PH???M C???U LONG",font10_normal);
				cell.addElement(chunk);
				tableheader.addCell(cell);
				
				//// dong 2 phai 
				cell = new PdfPCell();
				cell.setBorder(0);
				cell.setHorizontalAlignment(Element.ALIGN_CENTER);
				cell.setPaddingLeft(1.0f*CONVERT);
				para=new Paragraph("C???NG H??A X?? H???I CH??? NGH??A VI???T NAM",font10_normal);
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
				para=new Paragraph("?????c L???p - T??? Do - H???nh Ph??c",font10_normal);
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
				para=new Paragraph("BI??N B??N GIAO H??NG",new Font(bf,16,Font.BOLD));
				para.setAlignment(Element.ALIGN_CENTER);
				cell.addElement(para);
				tableheader.addCell(cell);
				
				// dong 5 hop dong
				cell = new PdfPCell();
				cell.setBorder(0);
				cell.setColspan(2);
				cell.setHorizontalAlignment(Element.ALIGN_CENTER);
				cell.setVerticalAlignment(Element.ALIGN_BOTTOM);
				para=new Paragraph("Theo h???p ?????ng s???:                      ng??y:            -?????t:",font10_ilatic);
				para.setAlignment(Element.ALIGN_CENTER);
				cell.addElement(para);
				tableheader.addCell(cell);
				
				
				// dong 6 hoa don
				cell = new PdfPCell();
				cell.setBorder(0);
				cell.setColspan(2);
				cell.setHorizontalAlignment(Element.ALIGN_CENTER);
				para=new Paragraph("Theo h??a ????n s???:             ng??y:               ",font10_ilatic);
				para.setAlignment(Element.ALIGN_CENTER);
				cell.addElement(para);
				tableheader.addCell(cell);
				
				// dong 7 dms
				cell = new PdfPCell();
				cell.setBorder(0);
				cell.setColspan(2);
				cell.setHorizontalAlignment(Element.ALIGN_CENTER);
				para=new Paragraph("S??? phi???u:              ng??y                ",font10_ilatic);
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
			para=new Paragraph("H??m nay, ng??y    /    /     Ch??ng t??i g???m c??:",font10_normal);
			para.setAlignment(Element.ALIGN_LEFT);
			cell.addElement(para);
			para=new Paragraph("B??N B??N (B??N A): C??NG TY C??? PH???N D?????C PH???M C???U LONG",font10_Bold);
			para.setAlignment(Element.ALIGN_LEFT);
			cell.addElement(para);
			tbl.addCell(cell);
			
			
			// thong tin ben a
			cell = new PdfPCell();
			cell.setBorder(0);
			cell.setPaddingLeft(1.5f*CONVERT);
			cell.setHorizontalAlignment(Element.ALIGN_LEFT);
			
			para=new Paragraph("- ?????a ch???: ",font10_normal);
			para.setAlignment(Element.ALIGN_LEFT);
			cell.addElement(para);
			
			para=new Paragraph("- M?? s??? thu???: ",font10_normal);
			para.setAlignment(Element.ALIGN_LEFT);
			cell.addElement(para);
			
			para=new Paragraph("- Do ??ng/b??:               L??m ?????i di???n",font10_normal);
			para.setAlignment(Element.ALIGN_LEFT);
			cell.addElement(para);
			
			para=new Paragraph("- ??i???n tho???i:",font10_normal);
			para.setAlignment(Element.ALIGN_LEFT);
			cell.addElement(para);
			tbl.addCell(cell);
			
			// dong 
			cell = new PdfPCell();
			cell.setBorder(0);
			cell.setHorizontalAlignment(Element.ALIGN_LEFT);
			para=new Paragraph("B??N MUA (B??N B): ",font10_Bold);
			para.setAlignment(Element.ALIGN_LEFT);
			cell.addElement(para);
			tbl.addCell(cell);
			
			// thong tin ben b
			cell = new PdfPCell();
			cell.setBorder(0);
			cell.setPaddingLeft(1.5f*CONVERT);
			cell.setHorizontalAlignment(Element.ALIGN_LEFT);
			
			para=new Paragraph("- ?????a ch???: ",font10_normal);
			para.setAlignment(Element.ALIGN_LEFT);
			cell.addElement(para);
			
			para=new Paragraph("- M?? s??? thu???: ",font10_normal);
			para.setAlignment(Element.ALIGN_LEFT);
			cell.addElement(para);
			
			para=new Paragraph("- Do ??ng/b??:               L??m ?????i di???n",font10_normal);
			para.setAlignment(Element.ALIGN_LEFT);
			cell.addElement(para);
			
			para=new Paragraph("- ??i???n tho???i:",font10_normal);
			para.setAlignment(Element.ALIGN_LEFT);
			cell.addElement(para);
			tbl.addCell(cell);
			
			// dong 
			cell = new PdfPCell();
			cell.setBorder(0);
			cell.setHorizontalAlignment(Element.ALIGN_LEFT);
			para=new Paragraph("         Hai b??n nh???t tr?? x??c nh???n b??n A ???? giao h??ng t???i kho c???a b??n B. "+
					"Hai b??n c??ng ti???n h??ng nghi???m thu m???t ph???n h???p ?????ng s???:    ng??y          . "+"" +
					"H??ng h??a ???? ???????c giao ????ng s??? l?????ng, c??? th??? nh?? sau",font10_normal);
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
			String[] tieude = new String[] { "Stt","T??n h??ng","??VT","????n gi??","S??? l?????ng","Th??nh ti???n","S??? l??, h???n d??ng","Ghi ch??"};
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
			para = new Paragraph("C???ng ti???n h??ng " , font10_normal);
			cell.addElement(para);
			tbl_sanpham.addCell(cell);
			
			cell = new PdfPCell();
			cell.setBorder(0);
			cell.setColspan(2);
			cell.setVerticalAlignment(Element.ALIGN_RIGHT);
			cell.setPaddingLeft(0.5f * CONVERT);
			para = new Paragraph(" ?????ng" , font10_normal);
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
			para = new Paragraph("Ti???n gi???m", font10_normal);
			cell.addElement(para);
			tbl_sanpham.addCell(cell);
			
			cell = new PdfPCell();
			cell.setBorder(0);
			cell.setColspan(2);
			cell.setVerticalAlignment(Element.ALIGN_RIGHT);
			cell.setPaddingLeft(0.5f * CONVERT);
			para = new Paragraph(" ?????ng" ,font10_normal);
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
			para = new Paragraph("Ti???n chi???t kh???u", font10_normal);
			cell.addElement(para);
			tbl_sanpham.addCell(cell);
			
			cell = new PdfPCell();
			cell.setBorder(0);
			cell.setColspan(2);
			cell.setVerticalAlignment(Element.ALIGN_RIGHT);
			cell.setPaddingLeft(0.5f * CONVERT);
			para = new Paragraph(" ?????ng" ,font10_normal);
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
			para = new Paragraph("Thu??? GTGT", font10_normal);
			cell.addElement(para);
			tbl_sanpham.addCell(cell);
			
			cell = new PdfPCell();
			cell.setBorder(0);
			cell.setColspan(2);
			cell.setVerticalAlignment(Element.ALIGN_RIGHT);
			cell.setPaddingLeft(0.5f * CONVERT);
			para = new Paragraph(" ?????ng" , font10_normal);
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
			para = new Paragraph("T???ng c???ng ",font10_normal);
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
			para = new Paragraph(" ?????ng" ,font10_normal);
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
			para = new Paragraph("T???ng c???ng  ..............  Kho???n. G???m  .................  th??ng (ki???n), ................... h???p (chai).", font10_normal);
			cell.addElement(para);
			tbl_footer.addCell(cell);
			
			
			cell = new PdfPCell();
			cell.setBorder(0);
			para = new Paragraph();
			para.add(new Chunk("T??nh tr???ng h??ng h??a:   ", font10_normal ));
			para.add(new Chunk( String.valueOf(unchecked), charfont ));
			para.add(new Chunk("  B??nh th?????ng, bao b?? nguy??n v???n, nguy??n ??ai nguy??n ki???n.", font10_normal ));
			cell.addElement(para);
			tbl_footer.addCell(cell);
			
			cell = new PdfPCell();
			cell.setBorder(0);
			cell.setPaddingLeft(3.3f*CONVERT);
			para = new Paragraph();
			para.add(new Chunk( String.valueOf(unchecked), charfont ));
			para.add(new Chunk("  Kh??c ..................................................................."
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
			para.add(new Chunk("Ch???ng t??? k??m theo:     ", font10_normal ));
			para.add(new Chunk( String.valueOf(unchecked), charfont ));
			para.add(new Chunk("   H??a ????n GTGT,      ", font10_normal ));
			para.add(new Chunk( String.valueOf(unchecked), charfont ));
			para.add(new Chunk(" Kh??c  .................................................................................", font10_normal ));
			cell.addElement(para);
			tbl_footer.addCell(cell);
			
			cell = new PdfPCell();
			cell.setBorder(0);
			para = new Paragraph("Bi??n b???n ???????c l???p th??nh   ...   b???n, m???i b??n gi???   ...  b???n v?? c?? gi?? tr??? nh?? nhau.", font10_normal);
			cell.addElement(para);
			tbl_footer.addCell(cell);
			
			cell = new PdfPCell();
			cell.setBorder(0);
			cell.setPaddingLeft(1.5f*CONVERT);
			para = new Paragraph("?????I DI???N B??N A"+
					"                                                                                         "+
					"?????I DI???N B??N B", font10_Bold);
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
				para=new Paragraph("C???NG H??A X?? H???I CH??? NGH??A VI???T NAM",font10_Bold);
				para.setAlignment(Element.ALIGN_CENTER);
				cell.addElement(para);
				tableheader.addCell(cell);
				
				// dong 2 
				cell = new PdfPCell();
				cell.setBorder(0);
				cell.setHorizontalAlignment(Element.ALIGN_LEFT);
				para=new Paragraph();
				para.setAlignment(Element.ALIGN_CENTER);
				chunk=new Chunk("?????c L???p - T??? Do - H???nh Ph??c",font10_Bold);
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
				para=new Paragraph("BI??N B???N NGHI???M THU ",new Font(bf,16,Font.BOLD));
				para.setAlignment(Element.ALIGN_CENTER);
				cell.addElement(para);
				tableheader.addCell(cell);
				
				//dong 4 5
				
				cell = new PdfPCell();
				cell.setBorder(0);
				cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
				para=new Paragraph("C??n c??? theo h???p ?????ng s???:  ",font10_ilatic);
				para.setAlignment(Element.ALIGN_CENTER);
				cell.addElement(para);
				para=new Paragraph("H??m nay, l??c  ... gi??? ...  ph??t, ng??y .... Th??ng ..... n??m 2016. , ch??ng t??i g???m c??:",font10_ilatic);
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
			chunk=new Chunk("B??N A (B??n nh???n)", font10_Bold);
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
			
			para=new Paragraph("?????a ch???: ",font10_normal);
			para.setAlignment(Element.ALIGN_LEFT);
			cell.addElement(para);
			
			para=new Paragraph("??i???n tho???i:",font10_normal); // kem fax
			para.setAlignment(Element.ALIGN_LEFT);
			cell.addElement(para);
			
			para=new Paragraph("M?? s??? thu???: ",font10_normal);
			para.setAlignment(Element.ALIGN_LEFT);
			cell.addElement(para);
			
			
			para=new Paragraph("T??i kho???n:  ",font10_normal); //tai ngan hang nao?
			para.setAlignment(Element.ALIGN_LEFT);
			cell.addElement(para);
			
			para=new Paragraph("?????i di???n:                                  Ch???c v???:",font10_normal);
			para.setAlignment(Element.ALIGN_LEFT);
			cell.addElement(para);
			tbl.addCell(cell);
			
			// ben giao
			cell = new PdfPCell();
			cell.setBorder(0);
			cell.setHorizontalAlignment(Element.ALIGN_LEFT);
			para=new Paragraph();
			para.setAlignment(Element.ALIGN_LEFT);
			chunk=new Chunk("B??N B (B??n giao)", font10_Bold);
			chunk.setUnderline(1f, -2);
			para.add(chunk);
			
			chunk=new Chunk(" : C??NG TY C??? PH???N D?????C PH???M C???U LONG", font10_Bold);
			para.add(chunk);
			cell.addElement(para);
			tbl.addCell(cell);
			
			// thong tin ben b
			cell = new PdfPCell();
			cell.setBorder(0);
			cell.setPaddingLeft(1.5f*CONVERT);
			cell.setHorizontalAlignment(Element.ALIGN_LEFT);
			
			para=new Paragraph("?????a ch???: 150, ???????ng 14/9, ph?????ng 5, TP V??nh Long, t???nh V??nh Long",font10_normal);
			para.setAlignment(Element.ALIGN_LEFT);
			cell.addElement(para);
			
			para=new Paragraph("??i???n tho???i: 0703.823.268  ??? Fax: 0703.823.710",font10_normal); // kem fax
			para.setAlignment(Element.ALIGN_LEFT);
			cell.addElement(para);
			
			para=new Paragraph("M?? s??? thu???: 1500202535",font10_normal);
			para.setAlignment(Element.ALIGN_LEFT);
			cell.addElement(para);
			
			
			para=new Paragraph("T??i kho???n: 73010000008023 ",font10_normal); //tai ngan hang nao?
			para.setAlignment(Element.ALIGN_LEFT);
			cell.addElement(para);
			
			para=new Paragraph("?????i di???n:                                  Ch???c v???:",font10_normal);
			para.setAlignment(Element.ALIGN_LEFT);
			cell.addElement(para);
			tbl.addCell(cell);
			
			// so hop dong
			cell = new PdfPCell();
			cell.setBorder(0);
			cell.setHorizontalAlignment(Element.ALIGN_LEFT);
			para=new Paragraph("      Hai b??n c??ng ti???n h??nh nghi???m thu m???t ph???n c???a  h???p ?????ng s???: sohopdong ng??y 08  th??ng 6 n??m 2015  nh?? sau:",font10_normal);
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
			String[] tieude = new String[] { "STT","T??n H??ng Ho??","??VT","S??? l?????ng","????n gi??","Th??nh ti???n \n (VN??)","S??? l??","H???n d??ng","N?????c SX ??? H??ng SX"};
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
			para = new Paragraph("C???ng " , new Font(bf, 10, Font.NORMAL));
			cell.addElement(para);
			tbl_sanpham.addCell(cell);

			cell = new PdfPCell();
			cell.setBorder(0);
			cell.setColspan(4);
			cell.setVerticalAlignment(Element.ALIGN_RIGHT);
			cell.setPaddingLeft(0.5f * CONVERT);
			para = new Paragraph(" ?????ng" , new Font(bf, 10, Font.NORMAL));
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
			para = new Paragraph("Thu??? VAT (5%)", new Font(bf, 10, Font.NORMAL));
			cell.addElement(para);
			tbl_sanpham.addCell(cell);
			
			cell = new PdfPCell();
			cell.setBorder(0);
			cell.setColspan(4);
			cell.setVerticalAlignment(Element.ALIGN_RIGHT);
			cell.setPaddingLeft(0.5f * CONVERT);
			para = new Paragraph(" ?????ng" , new Font(bf, 10, Font.NORMAL));
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
			para = new Paragraph("Gi?? tr??? thanh to??n ",new Font(bf, 10, Font.NORMAL));
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
			para = new Paragraph(" ?????ng" ,new Font(bf, 10, Font.NORMAL));
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
			para = new Paragraph("      S??? ti???n b???ng ch???:  ", new Font(bf,12,Font.BOLDITALIC)); //cong doc tine
			para.setAlignment(Element.ALIGN_JUSTIFIED);
			cell.addElement(para);
			
			para = new Paragraph("      Bi??n b???n giao h??ng k??m theo h??a ????n t??i ch??nh xu???t h??ng s???  45168  ng??y  06/01/2016  ", font10_normal); 
			para.setAlignment(Element.ALIGN_JUSTIFIED);
			cell.addElement(para);
			
			para = new Paragraph("      -  H??ng giao ????? s??? l?????ng, ????ng ch???ng l???ai, qui c??ch, h??m l?????ng, h???n d??ng theo h???p ?????ng ???? k?? k???t. ", font10_normal); 
			para.setAlignment(Element.ALIGN_JUSTIFIED);
			cell.addElement(para);
			
			para = new Paragraph("      -  C???m quan: m??u s???c, h??nh d???ng nguy??n v???n.", font10_normal); 
			cell.addElement(para);
			
			para = new Paragraph();
			para.setAlignment(Element.ALIGN_CENTER);
			para.add(new Chunk("?????t  ", font10_normal ));
			para.add(new Chunk( String.valueOf(unchecked), charfont ));
			para.add(new Chunk("                Kh??ng ?????t  ", font10_normal ));
			para.add(new Chunk( String.valueOf(unchecked), charfont ));
			cell.addElement(para);
			
			
			para = new Paragraph("      Hai b??n th???ng nh???t nghi???m thu h??ng h??a tr??n v?? c??ng k?? v??o bi??n b???n nghi???m thu.", font10_normal); 
			para.setAlignment(Element.ALIGN_JUSTIFIED);
			cell.addElement(para);
			
			para = new Paragraph("      Bi??n b???n l???p th??nh 04 b???n, b??n A nh???n 03 b???n, b??n B  nh???n 01 b???n, ?????u c?? gi?? tr??? nh?? nhau.", font10_normal); 
			para.setAlignment(Element.ALIGN_JUSTIFIED);
			cell.addElement(para);
			tbl_footer.addCell(cell);
			
			cell = new PdfPCell();
			cell.setBorder(0);
			cell.setPaddingLeft(1.5f*CONVERT);
			para = new Paragraph("?????I DI???N B??N A"+
					"                                                                               "+
					"?????I DI???N B??N B", font10_Bold);
			cell.addElement(para);
			tbl_footer.addCell(cell);
			
			cell = new PdfPCell();
			cell.setBorder(0);
			cell.setPaddingLeft(1.5f*CONVERT);
			para = new Paragraph("       GI??M ?????C                                              "+
					"                                                       TUQ. T???NG GI??M ?????C", new Font(bf,10,Font.BOLD));
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
				para=new Paragraph("C???NG H??A X?? H???I CH??? NGH??A VI???T NAM",font10_Bold);
				para.setAlignment(Element.ALIGN_CENTER);
				cell.addElement(para);
				tableheader.addCell(cell);
				
				// dong 2 
				cell = new PdfPCell();
				cell.setBorder(0);
				cell.setHorizontalAlignment(Element.ALIGN_LEFT);
				para=new Paragraph();
				para.setAlignment(Element.ALIGN_CENTER);
				chunk=new Chunk("?????c L???p - T??? Do - H???nh Ph??c",font10_Bold);
				chunk.setUnderline(1f, -2);
				para.add(chunk);
				cell.addElement(para);
				tableheader.addCell(cell);
				
				// dong 3 
				cell = new PdfPCell();
				cell.setBorder(0);
				cell.setPaddingTop(10);
				cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
				para=new Paragraph("BI??N B???N",new Font(bf,16,Font.BOLD));
				para.setAlignment(Element.ALIGN_CENTER);
				cell.addElement(para);
				tableheader.addCell(cell);
				
				cell = new PdfPCell();
				cell.setBorder(0);
				cell.setPaddingBottom(10);
				cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
				para=new Paragraph("NGHI????M THU THU???C, VTYTTH, H??A CH???T, D???CH TRUY???N",new Font(bf,16,Font.BOLD));
				para.setAlignment(Element.ALIGN_CENTER);
				cell.addElement(para);
				tableheader.addCell(cell);
				
				//dong 4 5
				
				cell = new PdfPCell();
				cell.setBorder(0);
				cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
				para=new Paragraph("C??n c??? H???p ?????ng s???:........./ ???...... ng??y........ th??ng....... n??m ........",font10_ilatic);
				para.setAlignment(Element.ALIGN_CENTER);
				cell.addElement(para);
				para=new Paragraph("H??m nay, l??c  ... gi??? ...  ph??t, ng??y .... Th??ng ..... n??m 2016 t???i ......... , ch??ng t??i g???m c??:",font10_ilatic);
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
			chunk=new Chunk("B??N NH???N (B??n A)", font10_Bold);
			chunk.setUnderline(1f, -2);
			para.add(chunk);
			chunk=new Chunk(" : TRUNG T??M Y T??? TH??NH PH??? SA ????C", font10_Bold);
			para.add(chunk);
			cell.addElement(para);
			tbl.addCell(cell);
			
			
			// thong tin ben a
			cell = new PdfPCell();
			cell.setBorder(0);
			cell.setPaddingLeft(1.5f*CONVERT);
			cell.setHorizontalAlignment(Element.ALIGN_LEFT);
			
			para=new Paragraph("?????a ch???: ",font10_normal);
			para.setAlignment(Element.ALIGN_LEFT);
			cell.addElement(para);
			
			para=new Paragraph("??i???n tho???i:",font10_normal); // kem fax
			para.setAlignment(Element.ALIGN_LEFT);
			cell.addElement(para);
			
			para=new Paragraph("M?? s??? thu???: ",font10_normal);
			para.setAlignment(Element.ALIGN_LEFT);
			cell.addElement(para);
			
			
			para=new Paragraph("T??i kho???n:  ",font10_normal); //tai ngan hang nao?
			para.setAlignment(Element.ALIGN_LEFT);
			cell.addElement(para);
			
			para=new Paragraph("?????i di???n:                                  Ch???c v???:",font10_normal);
			para.setAlignment(Element.ALIGN_LEFT);
			cell.addElement(para);
			tbl.addCell(cell);
			
			// ben giao
			cell = new PdfPCell();
			cell.setBorder(0);
			cell.setHorizontalAlignment(Element.ALIGN_LEFT);
			para=new Paragraph();
			para.setAlignment(Element.ALIGN_LEFT);
			chunk=new Chunk("B??N GIAO (B??n B)", font10_Bold);
			chunk.setUnderline(1f, -2);
			para.add(chunk);
			
			chunk=new Chunk(" : C??NG TY C??? PH???N D?????C PH???M C???U LONG", font10_Bold);
			para.add(chunk);
			cell.addElement(para);
			tbl.addCell(cell);
			
			// thong tin ben b
			cell = new PdfPCell();
			cell.setBorder(0);
			cell.setPaddingLeft(1.5f*CONVERT);
			cell.setHorizontalAlignment(Element.ALIGN_LEFT);
			
			para=new Paragraph("?????a ch???: 150, ???????ng 14/9, ph?????ng 5, TP V??nh Long, t???nh V??nh Long",font10_normal);
			para.setAlignment(Element.ALIGN_LEFT);
			cell.addElement(para);
			
			para=new Paragraph("??i???n tho???i: 0703.823.268  ??? Fax: 0703.823.710",font10_normal); // kem fax
			para.setAlignment(Element.ALIGN_LEFT);
			cell.addElement(para);
			
			para=new Paragraph("M?? s??? thu???: 1500202535",font10_normal);
			para.setAlignment(Element.ALIGN_LEFT);
			cell.addElement(para);
			
			
			para=new Paragraph("T??i kho???n: 73010000008023 ",font10_normal); //tai ngan hang nao?
			para.setAlignment(Element.ALIGN_LEFT);
			cell.addElement(para);
			
			para=new Paragraph("?????i di???n:                                  Ch???c v???:",font10_normal);
			para.setAlignment(Element.ALIGN_LEFT);
			cell.addElement(para);
			tbl.addCell(cell);
			
			//loi xac nhan
			cell = new PdfPCell();
			cell.setBorder(0);
			cell.setHorizontalAlignment(Element.ALIGN_LEFT);
			para=new Paragraph("      Hai b??n th???ng nh????t nghi????m thu va?? ba??n giao thu???c, VTYTTH, h??a ch???t, d???ch truy???n theo H???p ?????ng ???? k?? v???i chi ti???t nh?? sau:",font10_normal);
			para.setAlignment(Element.ALIGN_LEFT);
			cell.addElement(para);
			para=new Paragraph("N???i dung:",font10_Bold);
			para.setAlignment(Element.ALIGN_LEFT);
			cell.addElement(para);
			para=new Paragraph("      B??n B b??n giao cho b??n A thu???c, VTYTTH, h??a ch???t, d???ch truy???n theo h??a ????n:",font10_normal);
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
			String[] tieude = new String[] { "TT","S??? H??","Ng??y xu???t h??a ????n nghi???m thu","Th??nh ti???n \n (VN??)"};
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
			para = new Paragraph("T???NG C???NG " , new Font(bf, 10, Font.BOLD));
			para.setAlignment(Element.ALIGN_CENTER);
			cell.addElement(para);
			tbl_sanpham.addCell(cell);

			cell = new PdfPCell();
			cell.setVerticalAlignment(Element.ALIGN_RIGHT);
			cell.setPaddingBottom(7);
			para = new Paragraph(" ?????ng" , new Font(bf, 10, Font.BOLD));
			para.setAlignment(Element.ALIGN_RIGHT);
			cell.addElement(para);
			tbl_sanpham.addCell(cell);
			
			document.add(tbl_sanpham);

			//=========== footer
			
			PdfPTable tbl_footer =new PdfPTable(1);
			tbl_footer.setWidthPercentage(100);
			
			cell = new PdfPCell();
			cell.setBorder(0);
			para = new Paragraph("      S??? ti???n b???ng ch???:  ", new Font(bf,12,Font.NORMAL)); //cong doc tine
			para.setAlignment(Element.ALIGN_JUSTIFIED);
			cell.addElement(para);
			
			para = new Paragraph("K????t lu????n: ", new Font(bf,12,Font.BOLD)); //cong doc tine
			para.setAlignment(Element.ALIGN_JUSTIFIED);
			cell.addElement(para);
			
			para = new Paragraph("      - B??n A ???? ki???m tra ????ng ch???ng lo???i, h??m l?????ng, s??? l??, h???n d??ng,??????.. c???a h??ng h??a theo y??u c???u.", font10_normal); 
			para.setAlignment(Element.ALIGN_JUSTIFIED);
			cell.addElement(para);
			
			para = new Paragraph("      - B??n A ???? nh???n ?????y ????? s??? l?????ng h??ng h??a. ", font10_normal); 
			para.setAlignment(Element.ALIGN_JUSTIFIED);
			cell.addElement(para);
			
			para = new Paragraph("      - Trong th???i gian s??? d???ng n???u b??n A ph??t hi???n b??n trong c?? s??? thi???u h???t v??? s??? l?????ng ho???c k??m ch???t l?????ng ??????,"+
					" B??n A s??? th??ng b??o ngay cho B??n B b???ng c??ch ??i???n tho???i, fax ho???c b???ng v??n b???n ????? c??ng b??n b???c gi???i quy???t.", font10_normal); 
			para.setAlignment(Element.ALIGN_JUSTIFIED);
			cell.addElement(para);
			
			para = new Paragraph("      - B??n A th???ng nh???t nghi???m thu v?? thanh toa??n ti???n thu???c, h??a ch???t, VTYTTHcho b??n B theo ????ng H???p ?????ng ???? ky?? k????t.", font10_normal); 
			para.setAlignment(Element.ALIGN_JUSTIFIED);
			cell.addElement(para);
			
			
			
			para = new Paragraph("      Bi??n b???n l???p th??nh 04 b???n, b??n A nh???n 03 b???n, b??n B  nh???n 01 b???n, ?????u c?? gi?? tr??? nh?? nhau.", font10_normal); 
			para.setAlignment(Element.ALIGN_JUSTIFIED);
			cell.addElement(para);
			tbl_footer.addCell(cell);
			
			cell = new PdfPCell();
			cell.setBorder(0);
			cell.setPaddingLeft(1.5f*CONVERT);
			para = new Paragraph("?????I DI???N B??N A"+
					"                                                                               "+
					"?????I DI???N B??N B", font10_Bold);
			cell.addElement(para);
			tbl_footer.addCell(cell);
			
			cell = new PdfPCell();
			cell.setBorder(0);
			cell.setPaddingLeft(1.5f*CONVERT);
			para = new Paragraph("       GI??M ?????C                                              "+
					"                                                       TUQ. T???NG GI??M ?????C", new Font(bf,10,Font.BOLD));
			cell.addElement(para);
			tbl_footer.addCell(cell);
			
			document.add(tbl_footer);

			document.close();
			
		} catch (Exception e) {
			e.printStackTrace();

	}

	}

}
