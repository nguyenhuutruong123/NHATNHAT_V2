package geso.dms.center.servlets.thutien;

import geso.dms.center.beans.doctien.doctienrachu;
import geso.dms.distributor.db.sql.dbutils;
import geso.dms.distributor.util.Utility;
import geso.dms.erp.beans.thutien.IErpThutien;
import geso.dms.erp.beans.thutien.imp.ErpThutien;

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
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.BaseFont;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

public class ErpInPhieuThutienHOSvl extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public ErpInPhieuThutienHOSvl()
	{
		super();
	}


	float CONVERT = 28.346457f;  // =1cm
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		HttpSession session = request.getSession();
		String userId = (String) session.getAttribute("userId");
		String userTen = (String) session.getAttribute("userTen");
		String ctyId = (String) session.getAttribute("congtyId");
		if (ctyId == null)
			ctyId="";
		Utility util = new Utility();

		if (userId.length() == 0)
			userId = request.getParameter("userId");

		String querystring = request.getQueryString();
		String id = util.antiSQLInspection(request.getParameter("id"));
		System.out.println("id:"+ id);
		String view = util.antiSQLInspection(request.getParameter("view"));
		if(view == null)
			view = "";

		IErpThutien tthdBean = new ErpThutien(id);
		tthdBean.setCtyId(ctyId);
		tthdBean.setUserId(userId);
		tthdBean.init();

		System.out.println(" \n user  id "+userId +"\n");
		//=============khai bao doc
		response.setContentType("application/pdf");
		response.setHeader("Content-Disposition", " inline; filename=Phieuthutienkhachhang.pdf");
		Document document = new Document();
		ServletOutputStream outstream = response.getOutputStream();

		//xuat hoa don
		this.CreatePxk(document, outstream, tthdBean);
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


	private void form_phieuthu( Document document,ServletOutputStream outstream,IErpThutien pttBean,
			String ngayxuatphieu,String tennguoinop,String donvi,String diachi,
			String lydo, double tongtien, String tkco, String tkno,String machungtu,String nguoitao,dbutils db){

		try {
			//khai bao
			NumberFormat formatter = new DecimalFormat("#,###,###.##");
			NumberFormat formatter1 = new DecimalFormat("#,###,###");
			document.setPageSize(PageSize.A4);
			document.setMargins(2.0f*CONVERT, 2.0f*CONVERT, 0.5f*CONVERT, 0.5f*CONVERT); // L,R,T,B
			PdfWriter writer = PdfWriter.getInstance(document, outstream);
			PdfPCell cell ;
			Paragraph para;
			Chunk chunk;
			document.open() ;
			BaseFont bf = BaseFont.createFont("c:\\windows\\fonts\\times.ttf", BaseFont.IDENTITY_H, BaseFont.EMBEDDED);
			Font font10_normal = new Font(bf, 10, Font.NORMAL);			
			Font font10_Bold = new Font(bf, 10, Font.BOLD);
			Font font10_ilatic = new Font(bf, 10, Font.ITALIC);
			Font font12_normal = new Font(bf, 12, Font.NORMAL);
			Font font12_Bold = new Font(bf, 12, Font.BOLD);
			//tao header1
			PdfPTable tableheader =new PdfPTable(2);
			tableheader.setWidthPercentage(100);
			tableheader.setHorizontalAlignment(Element.ALIGN_CENTER);
			float[] withd= {13f*CONVERT,8.0f*CONVERT};
			tableheader.setWidths(withd);
			// trai
			cell = new PdfPCell();
			cell.setBorder(0);
			cell.setHorizontalAlignment(Element.ALIGN_LEFT);
			chunk=new Chunk("CÔNG TY CỔ PHẦN DƯỢC PHẨM CỬU LONG",font10_Bold);
			cell.addElement(chunk);
			tableheader.addCell(cell);
			//phai
			cell = new PdfPCell();
			cell.setBorder(0);
			cell.setHorizontalAlignment(Element.ALIGN_CENTER);
			cell.setPaddingLeft(2.0f*CONVERT);
			chunk=new Chunk("Mẫu số: 01-TT",font10_Bold);
			cell.addElement(chunk);
			tableheader.addCell(cell);
			// trai
			cell = new PdfPCell();
			cell.setBorder(0);
			cell.setHorizontalAlignment(Element.ALIGN_LEFT);
			chunk=new Chunk("150 Đường 14/9, Phường 5, TP Vĩnh Long, Tĩnh Vĩnh Long",font10_Bold);
			chunk.setTextRise(1f);
			cell.addElement(chunk);
			tableheader.addCell(cell);
			//phai
			cell = new PdfPCell();
			cell.setBorder(0);
			cell.setHorizontalAlignment(Element.ALIGN_CENTER);
			chunk=new Chunk("(Ban hành theo thông tư 200/2014/TT-BTC \n         ngày 22/12/2014 của Bộ Tài chính)",font10_ilatic);
			chunk.setTextRise(1f);
			cell.addElement(chunk);
			tableheader.addCell(cell);

			//tua de phieu thu
			cell = new PdfPCell();
			cell.setBorder(0);
			cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
			cell.setVerticalAlignment(Element.ALIGN_BOTTOM);
			cell.setPaddingLeft(6.5f*CONVERT);
			chunk=new Chunk("PHIẾU THU",new Font(bf,19,Font.BOLD));
			cell.addElement(chunk);
			tableheader.addCell(cell);

			//so phieu thu
			cell = new PdfPCell();
			cell.setBorder(0);
			cell.setHorizontalAlignment(Element.ALIGN_LEFT);
			cell.setVerticalAlignment(Element.ALIGN_BOTTOM);
			chunk=new Chunk("   Số: " +machungtu,new Font(bf,12,Font.BOLD));
			/*chunk.setTextRise(-2f);*/
			cell.addElement(chunk);
			tableheader.addCell(cell);

			document.add(tableheader);


			//============tao header1 noi dung: bang 2 cot
			PdfPTable tbl =new PdfPTable(2);
			tbl.setWidthPercentage(100);			
			float[] withd1= {20f*CONVERT,8.0f*CONVERT};
			tbl.setWidths(withd1);

			//cell trai


			cell = new PdfPCell();
			cell.setBorder(0);
			cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
			cell.setHorizontalAlignment(Element.ALIGN_LEFT);
			if(ngayxuatphieu.length()>8){
				chunk=new Chunk("                                                               Ngày: "
						+ngayxuatphieu.substring(8)
						+"/"+ngayxuatphieu.substring(5,7)
						+"/"+ngayxuatphieu.substring(0,4),font12_normal);
				cell.addElement(chunk);
			}
			
			chunk=new Chunk("Họ tên người nộp tiền: "+tennguoinop,font12_normal);
			chunk.setTextRise(-0.2f*CONVERT);
			cell.addElement(chunk);
			
			tbl.addCell(cell);

			//cell phai
			cell = new PdfPCell();
			cell.setBorder(0);
			cell.setVerticalAlignment(Element.ALIGN_TOP);
			cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
			//cell add tbl 2 cot
			PdfPTable tbl_tk =new PdfPTable(2);
			tbl_tk.setWidthPercentage(100);			
			float[] withd2= {3f*CONVERT,5f*CONVERT};
			tbl_tk.setWidths(withd2);
			PdfPCell cell1 ;

			cell1=new PdfPCell(new Paragraph("Tài khoản", font10_normal));
			cell1.setHorizontalAlignment(Element.ALIGN_CENTER);
			cell1.setBorderWidthRight(0);
			tbl_tk.addCell(cell1);

			cell1=new PdfPCell(new Paragraph("Tiền VNĐ", font10_normal));
			cell1.setHorizontalAlignment(Element.ALIGN_CENTER);
			cell1.setBorderWidthLeft(0);
			tbl_tk.addCell(cell1);

			//tk no
			cell1=new PdfPCell(new Paragraph("Nợ "+tkno, font10_normal));
			cell1.setHorizontalAlignment(Element.ALIGN_LEFT);
			cell1.setBorderWidthRight(0);
			tbl_tk.addCell(cell1);

			//tien no
			cell1=new PdfPCell(new Paragraph(DinhDangTraphacoDMS(formatter.format(tongtien)), font10_normal));
			cell1.setHorizontalAlignment(Element.ALIGN_RIGHT);
			cell1.setBorderWidthLeft(0);
			tbl_tk.addCell(cell1);

			// tk co
			cell1 = new PdfPCell(new Paragraph("Có " + tkco, font10_normal));
			cell1.setHorizontalAlignment(Element.ALIGN_LEFT);
			cell1.setBorderWidthRight(0);
			tbl_tk.addCell(cell1);


			//tien co
			cell1=new PdfPCell(new Paragraph( DinhDangTraphacoDMS(formatter.format(tongtien)), font10_normal));
			cell1.setHorizontalAlignment(Element.ALIGN_RIGHT);
			cell1.setBorderWidthLeft(0);
			tbl_tk.addCell(cell1);


			cell.addElement(tbl_tk);
			tbl.addCell(cell);
			
			document.add(tbl);

			
			
			//--add bang tien va dinh kem
			PdfPTable tbl_tien = new PdfPTable(1);
			tbl_tien.setWidthPercentage(100);
			tbl_tien.setHorizontalAlignment(Element.ALIGN_LEFT);
			tbl_tien.getDefaultCell().setBorder(0);
			
			cell = new PdfPCell();
			cell.setBorder(0);
			chunk=new Chunk("Đơn vị: "+donvi,font12_normal);
			cell.addElement(chunk);
			chunk=new Chunk("Địa chỉ: "+diachi,font12_normal);
			cell.addElement(chunk);
			chunk=new Chunk("Lý do nộp: "+lydo,font12_normal);
			cell.addElement(chunk);
			chunk=new Chunk("Số tiền: "+ DinhDangTraphacoDMS(formatter1.format(tongtien)) +" đồng",font12_Bold);
			cell.addElement(chunk);
			tbl_tien.addCell(cell);
			

			cell = new PdfPCell();
			cell.setBorder(0);
			cell.setVerticalAlignment(Element.ALIGN_JUSTIFIED);
			cell.setHorizontalAlignment(Element.ALIGN_LEFT);
			cell.setHorizontalAlignment(Element.ALIGN_JUSTIFIED);
			doctienrachu doctien=new doctienrachu();
			chunk=new Chunk("(Viết bằng chữ): "+doctien.docTien((long)tongtien),font12_normal);
			cell.addElement(chunk);
			chunk=new Chunk("Kèm theo: Chứng từ gốc",font12_normal);
			cell.addElement(chunk);
			tbl_tien.addCell(cell);
			document.add(tbl_tien);



			//--- tao bang chu ki
			//------------bang chu ki---------------------
			float[] wky = {10.0f*CONVERT, 12.0f*CONVERT, 9.0f*CONVERT };
			PdfPTable tbl_vat = new PdfPTable(wky.length);
			tbl_vat.setWidthPercentage(100);
			tbl_vat.setHorizontalAlignment(Element.ALIGN_CENTER);
			tbl_vat.getDefaultCell().setBorder(0);
			tbl_vat.setWidths(wky);

			cell = new PdfPCell();					
			para=new Paragraph("Ban giám đốc", font12_Bold);			
			para.setAlignment(Element.ALIGN_CENTER);
			cell.addElement(para);
			cell.setHorizontalAlignment(Element.ALIGN_CENTER);
			cell.setBorder(0);
			tbl_vat.addCell(cell);
			
			
			cell = new PdfPCell();				
			para=new Paragraph("Kế toán", font12_Bold);
			para.setAlignment(Element.ALIGN_CENTER);
			cell.addElement(para);
			cell.setHorizontalAlignment(Element.ALIGN_CENTER);
			cell.setBorder(0);
			tbl_vat.addCell(cell);
			
			
			cell = new PdfPCell();				
			para=new Paragraph("Người lập phiếu", font12_Bold);
			para.setAlignment(Element.ALIGN_CENTER);
			cell.addElement(para);
			cell.setHorizontalAlignment(Element.ALIGN_CENTER);
			cell.setBorder(0);
			tbl_vat.addCell(cell);
			
			
			//dong 2
			cell = new PdfPCell(new Paragraph("", font12_Bold));
			cell.setBorder(0);
			tbl_vat.addCell(cell);
			cell = new PdfPCell(new Paragraph("", font12_Bold));
			cell.setBorder(0);
			tbl_vat.addCell(cell);
			
			
			cell = new PdfPCell();				
			para=new Paragraph(nguoitao, font12_normal);
			para.setAlignment(Element.ALIGN_CENTER);
			cell.addElement(para);
			cell.setPaddingTop(1.3f*CONVERT);
			cell.setHorizontalAlignment(Element.ALIGN_CENTER);
			cell.setBorder(0);
			tbl_vat.addCell(cell);


			document.add(tbl_vat);
			//==========tbl footer


			//cell add tbl 2 cot
			PdfPTable tbl_fotter =new PdfPTable(2);
			tbl_fotter.setWidthPercentage(100);	
			tbl_fotter.setSpacingBefore(0.3f*CONVERT);
			tbl_fotter.setSpacingAfter(0.3f*CONVERT);
			float[] withd3= {18f*CONVERT,10f*CONVERT};
			tbl_fotter.setWidths(withd3);
			tbl_fotter.getDefaultCell().setBorder(0);
			tbl_fotter.setHorizontalAlignment(Element.ALIGN_CENTER);

			cell1=new PdfPCell(new Paragraph("Đã nhận đủ số tiền (viết bằng chữ): "+doctien.docTien((long)tongtien), font12_normal));
			cell1.setHorizontalAlignment(Element.ALIGN_LEFT);
			cell1.setHorizontalAlignment(Element.ALIGN_JUSTIFIED);
			cell1.setColspan(2);
			cell1.setBorder(0);
			tbl_fotter.addCell(cell1);

			cell1=new PdfPCell(new Paragraph(" ", font12_normal));
			cell1.setHorizontalAlignment(Element.ALIGN_CENTER);
			cell1.setBorder(0);
			tbl_fotter.addCell(cell1);

			cell1=new PdfPCell(new Paragraph("Ngày "+ngayxuatphieu.substring(8) +" tháng "+ngayxuatphieu.substring(5,7)+" năm "+ngayxuatphieu.substring(0,4), font12_normal));
			cell1.setHorizontalAlignment(Element.ALIGN_CENTER);
			cell1.setBorder(0);
			tbl_fotter.addCell(cell1);

			cell1=new PdfPCell(new Paragraph("            Người nộp", font12_Bold));
			cell1.setHorizontalAlignment(Element.ALIGN_LEFT);
			cell1.setBorder(0);
			tbl_fotter.addCell(cell1);

			cell1=new PdfPCell(new Paragraph("Thủ quỹ", font12_Bold));
			cell1.setHorizontalAlignment(Element.ALIGN_CENTER);
			cell1.setBorder(0);
			tbl_fotter.addCell(cell1);

			document.add(tbl_fotter);
			document.close();

		} catch (Exception e) {
			e.printStackTrace();
		}


	}



	private void CreatePxk(Document document, ServletOutputStream outstream, IErpThutien btthBean) throws IOException
	{
		dbutils db = new dbutils();

		try
		{			
			//LAY THONG phieu thu khac
			String ngayxuatphieu=btthBean.getNgaychungtu();
			String tennguoinop=btthBean.getNguoinoptien();
			String diachi="";
			String donvi=btthBean.getBpkinhdoanh();
			String lydo=btthBean.getNoidungtt();      
			Double tongtien=Double.parseDouble(btthBean.getThuduoc().replaceAll(",",""));
			String tkco="";
			String tkno="";
			String hinhthuctt="";
			String machungtu="";
			String qr="";

			tkco = "131";
			tkno = "1111";

			String nguoitao="";
			String qr_sonet="select nh.sonetId as sonetid,nv.ten as ten from ERP_THUTIEN nh  \n "+
			" left join nhanvien nv on nv.PK_SEQ= nh.nguoisua \n  WHERE nh.PK_SEQ="+btthBean.getId();
			String sonetid="";
			ResultSet rsSonet=db.get(qr_sonet);
			if(rsSonet!=null){
				try {
					while(rsSonet.next()){
						if(rsSonet.getString("sonetId")!=null){
							machungtu=rsSonet.getString("sonetId");
							nguoitao=rsSonet.getString("ten");
						}
					}
					rsSonet.close();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}

			//lay dia chi
			ResultSet nppList = btthBean.getNppRs(); 
			if(nppList != null)
        		{
				try {
					while(nppList.next())
	    			{  
						if( nppList.getString("pk_seq").equals(btthBean.getnppIdgoc())){
								donvi=nppList.getString("Ten");
								diachi=nppList.getString("diachi");	
							}
	    			}
				} catch (Exception e) {
					e.printStackTrace();
				}

			}
			
			//in hoa don phieu thu
			form_phieuthu(document, outstream, btthBean,ngayxuatphieu,tennguoinop,donvi,diachi,lydo,tongtien,tkco,tkno,machungtu,nguoitao, db);

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



}

