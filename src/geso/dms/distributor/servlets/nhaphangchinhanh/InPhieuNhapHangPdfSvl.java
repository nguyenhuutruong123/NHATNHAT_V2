package geso.dms.distributor.servlets.nhaphang;

import geso.dms.center.db.sql.dbutils;
import geso.dms.distributor.beans.nhaphang.INhaphang;
import geso.dms.distributor.beans.nhaphang.imp.Nhaphang;
import geso.dms.distributor.util.Utility;

import java.io.IOException;
import java.sql.ResultSet;
import java.text.DecimalFormat;
import java.text.NumberFormat;

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

public class InPhieuNhapHangPdfSvl extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public InPhieuNhapHangPdfSvl()
	{
		super();
	}
	float CONVERT = 28.346457f; // = 1cm
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		HttpSession session = request.getSession();
		String userId = (String) session.getAttribute("userId");
		if (userId.length() == 0)
			userId = request.getParameter("userId");

		Utility util=new Utility();
		String nppId = util.antiSQLInspection(request.getParameter("nppId"));

		System.out.println(nppId);
		INhaphang pxkBean = new Nhaphang();
		pxkBean.setNppId(nppId);
		String querystring=request.getQueryString();
		String id=util.getId(querystring);
		pxkBean.setId(id);
		pxkBean.setUserId(userId);
		pxkBean.init();


		response.setContentType("application/pdf");
		response.setHeader("Content-Disposition", " inline; filename=PhieunhapkhoCN.pdf");
		Document document = new Document();
		ServletOutputStream outstream = response.getOutputStream();
		this.CreatePxk(document, outstream, pxkBean, userId);
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		this.doGet(request, response);
	}



	private void CreatePxk(Document document, ServletOutputStream outstream, INhaphang pxkBean, String userid) throws IOException
	{
		dbutils db=new dbutils();
		//lay thong tin noi nhap
		String tennoiin="";
		String diachiin="";
		String nguoitao="";
		String sophieu=pxkBean.getId();
		String ngayphap=pxkBean.getNgaynhap();
		String noigiao="";
		String sochungtu=pxkBean.getSochungtu();
		String lydo=pxkBean.getGhichu();
		String [] ngayHD = ngayphap.split("-");
		String[] spId = pxkBean.getSpId();
		String[] spMa = pxkBean.getSpMa(); 
		String[] spTen = pxkBean.getSpTen();
		String[] spDonvi = pxkBean.getSpDonvi();
		String[] spSolo = pxkBean.getSpSolo();
		String[] spXuat = pxkBean.getSpXuat();
		String[] spSoluong = pxkBean.getSpSoluong();
		String[] spDongia = pxkBean.getSpDongia();
		String[] spVat = pxkBean.getSpvat();
		String[] spChietkhau = pxkBean.getSpchietkhau();
		String[] spLoai = pxkBean.getSpLoai();
		String[] spNGAYHETHAN = pxkBean.getSpNgayHetHan();

		//lay noi in
		String qr_noiin="select npp.ten as ten, npp.DIACHI as diachi "+    
		"from NHANVIEN nv inner join nhaphanphoi npp on nv.CONVSITECODE=npp.SITECODE "+    
		"where nv.PHANLOAI = 1 and nv.PK_SEQ=" +userid;
		ResultSet rsNoiin=db.get(qr_noiin);
		String loaiin="";
		if(rsNoiin!=null){
			try {
				while(rsNoiin.next()){
					loaiin="CN/NPP";
					tennoiin=rsNoiin.getString("ten");
					diachiin=rsNoiin.getString("diachi")+", "+rsNoiin.getString("quan") +", "+rsNoiin.getString("tinh");
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		//lay nguoi tao phieu
		/*String qr_nguoitao="select nv.ten from nhaphang nh  "+    
							 "inner join nhanvien nv on nv.PK_SEQ= nh.nguoisua "+    
							 "where nh.pk_seq= " +pxkBean.getId();
		ResultSet rsnguoitao=db.get(qr_nguoitao);
		if(rsnguoitao!=null){
			try {
				while(rsnguoitao.next()){

					nguoitao=rsnguoitao.getString("ten");
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}*/

		//lay noi giao hang
		String qrnoigiao="select a.YCXK_FK, a.YCXKNPP_FK, a.CHUYENKHO_FK,a.CHUYENKHONPP_FK, "+    
		"case  "+    
		"when A.YCXK_FK IS not null then (select ten from nhaphanphoi where pk_seq=1) "+    
		"when A.CHUYENKHO_FK IS not null then (select ten from nhaphanphoi where pk_seq=1) "+    
		"when A.YCXKNPP_FK IS not null then (select ten from nhaphanphoi where pk_seq= (select tructhuoc_fk from nhaphanphoi where pk_seq= a.NPP_FK)) "+    
		"when A.CHUYENKHONPP_FK IS not null then (select ten from nhaphanphoi where pk_seq= (select tructhuoc_fk from nhaphanphoi where pk_seq= a.NPP_FK) ) "+    
		"end as noigiao "+    
		"from NHAPHANG A WHERE A.PK_SEQ="+ pxkBean.getId();

		ResultSet rsNoigiao=db.get(qrnoigiao);
		if(rsNoigiao!=null){
			try {
				while(rsNoigiao.next()){

					noigiao=rsNoigiao.getString("noigiao");

				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}


		// LAY SO CHUNG TU(  sonetId RESET THEO THANG)
		String qr_sonet="select nh.sonetId as sonetid,nv.ten as ten from NHAPHANG nh  \n "+
		" left join nhanvien nv on nv.PK_SEQ= nh.nguoisua \n  WHERE nh.PK_SEQ="+pxkBean.getId();
		String sonetid="";
		ResultSet rsSonet=db.get(qr_sonet);
		if(rsSonet!=null){
			try {
				while(rsSonet.next()){
					if(rsSonet.getString("sonetId")!=null){
						sonetid=rsSonet.getString("sonetId");
						nguoitao=rsSonet.getString("ten");
					}
				}
				rsSonet.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		try
		{			

			//khai bao
			NumberFormat formatter = new DecimalFormat("#,###,###.##");
			NumberFormat formatter1 = new DecimalFormat("#,###,###");
			document.setPageSize(PageSize.A4);
			document.setMargins(1.0f*CONVERT, 1.0f*CONVERT, 0.5f*CONVERT, 0.5f*CONVERT); // L,R,T,B
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
			PdfPTable tableheader =new PdfPTable(1);
			tableheader.setWidthPercentage(100);
			tableheader.setHorizontalAlignment(Element.ALIGN_CENTER);

			// ten 
			cell = new PdfPCell();
			cell.setBorder(0);
			para=new Paragraph(tennoiin.toUpperCase(),font12_Bold);
			para.setAlignment(Element.ALIGN_LEFT);
			cell.addElement(para);
			tableheader.addCell(cell);

			//diachi
			cell = new PdfPCell();
			cell.setBorder(0);
			para=new Paragraph(diachiin,font12_Bold);
			para.setAlignment(Element.ALIGN_LEFT);
			cell.addElement(para);
			tableheader.addCell(cell);

			//tieu de
			cell = new PdfPCell();
			cell.setBorder(0);
			cell.setHorizontalAlignment(Element.ALIGN_CENTER);
			para=new Paragraph("PHIẾU NHẬP KHO",new Font(bf,19,font12_Bold.BOLD));
			para.setAlignment(Element.ALIGN_CENTER);
			cell.addElement(para);
			tableheader.addCell(cell);

			//tieu de
			cell = new PdfPCell();
			cell.setBorder(0);
			cell.setHorizontalAlignment(Element.ALIGN_CENTER);
			para=new Paragraph("(PHIẾU NHẬP KHO DƯỢC PHẨM)",font12_normal);
			para.setAlignment(Element.ALIGN_CENTER);
			cell.addElement(para);
			tableheader.addCell(cell);

			//tieu de
			cell = new PdfPCell();
			cell.setBorder(0);
			cell.setHorizontalAlignment(Element.ALIGN_CENTER);
			para=new Paragraph("Số: " +sonetid,font12_Bold);
			para.setAlignment(Element.ALIGN_CENTER);
			cell.addElement(para);
			tableheader.addCell(cell);

			//ngay
			cell = new PdfPCell();
			cell.setBorder(0);
			cell.setHorizontalAlignment(Element.ALIGN_CENTER);
			para=new Paragraph("Ngày: "+ ngayHD[2] + " tháng " + ngayHD[1] +" năm "+ngayHD[0],font12_Bold);
			para.setAlignment(Element.ALIGN_CENTER);
			cell.addElement(para);
			tableheader.addCell(cell);

			//noi giao
			cell = new PdfPCell();
			cell.setBorder(0);
			cell.setHorizontalAlignment(Element.ALIGN_LEFT);
			para=new Paragraph("- Nơi giao hàng: " +noigiao,font12_Bold);
			para.setAlignment(Element.ALIGN_LEFT);
			cell.addElement(para);
			tableheader.addCell(cell);

			//so chung tu + ngay giao
			cell = new PdfPCell();
			cell.setBorder(0);
			cell.setHorizontalAlignment(Element.ALIGN_CENTER);
			para=new Paragraph();

			chunk=new Chunk("- Chứng từ số : " +sochungtu,font12_normal);
			para.add(chunk);
			if(sochungtu.length()==0){
				chunk=new Chunk("                          " +sochungtu,font12_normal);
				para.add(chunk);
			}
			chunk=new Chunk("             Ngày: " +ngayHD[2] + "/" + ngayHD[1] +"/"+ngayHD[0],font12_normal);
			para.add(chunk);
			cell.addElement(para);
			tableheader.addCell(cell);

			//ly do
			cell = new PdfPCell();
			cell.setBorder(0);
			cell.setHorizontalAlignment(Element.ALIGN_CENTER);
			para=new Paragraph("- Lý do: " +lydo,font12_normal);
			para.setAlignment(Element.ALIGN_LEFT);
			cell.addElement(para);
			tableheader.addCell(cell);
			document.add(tableheader);


			//bang danh sach san pham
			float[] tbl_withd = { 7.0f, 60f, 10f , 15.0f, 15.0f, 25f, 20f,15.0f };

			PdfPTable tbl_sanpham = new PdfPTable(tbl_withd.length);
			tbl_sanpham.setSpacingBefore(10.0f );
			tbl_sanpham.setWidthPercentage(100);
			tbl_sanpham.setHorizontalAlignment(Element.ALIGN_LEFT);
			tbl_sanpham.setWidths(tbl_withd);
			PdfPCell cells;
			double tongtien = 0;
			//add tieu de
			String[] tieude = new String[] { "Stt","Tên","Đvt","Đơn giá","Số lượng","Thành tiền","Số lô","Ghi chú"};
			for (int j = 0; j < tbl_withd.length; j++)
			{
				cells = new PdfPCell(new Paragraph(tieude[j], font10_Bold));
				cells.setHorizontalAlignment(Element.ALIGN_CENTER);
				cells.setPaddingBottom(7);
				tbl_sanpham.addCell(cells);	
			}
			//dah sach san pham
			if(spMa != null)
			{
				for(int i = 0; i < spMa.length; i++)
				{
					String ten=  spTen [i] ;
					String donvi=  spDonvi[i] ;
					String solo= spSolo [i];
					String soluong= spSoluong [i];
					String dongia= spDongia [i];
					String handung="";
					/*if(spNGAYHETHAN[i].length()>0){
						handung= spNGAYHETHAN[i].substring(5,7)+"/"+spNGAYHETHAN[i].substring(2,4);
					}*/
					Double thanhtien=Double.parseDouble(soluong.replaceAll(",","")) * Double.parseDouble(dongia.replaceAll(",",""));
					tongtien+=thanhtien;

					String[] sp = new String[] {i+1 +"",ten,donvi,DinhDangTraphacoDMS(formatter.format(Double.parseDouble(dongia.replaceAll(",","")))),
							DinhDangTraphacoDMS(formatter1.format(Double.parseDouble (soluong.replaceAll(",","")))),
							DinhDangTraphacoDMS( formatter1.format(thanhtien)),
							solo,""};

					for (int j = 0; j < tbl_withd.length; j++)
					{
						cells = new PdfPCell(new Paragraph(sp[j], font10_normal));

						if(j==1||j==6){
							cells.setHorizontalAlignment(Element.ALIGN_LEFT);
						}
						else
							if(j==0 ||j==2){
								cells.setHorizontalAlignment(Element.ALIGN_CENTER);
							}
							else
							{
								cells.setHorizontalAlignment(Element.ALIGN_RIGHT);
							}

						cells.setPaddingBottom(7);
						tbl_sanpham.addCell(cells);	
					}
				}
			}

			//tong tien
			cell = new PdfPCell();
			cell.setColspan(4);
			cell.setPaddingBottom(7);
			cell.setVerticalAlignment(Element.ALIGN_CENTER);
			para = new Paragraph("Tổng cộng ",  font10_Bold);
			para.setAlignment(Element.ALIGN_CENTER);
			cell.addElement(para);
			tbl_sanpham.addCell(cell);

			cell = new PdfPCell();
			cell.setPaddingBottom(7);
			cell.setBorderWidthRight(0);
			cell.setColspan(2);
			cell.setVerticalAlignment(Element.ALIGN_RIGHT);
			para = new Paragraph(DinhDangTraphacoDMS (formatter.format(tongtien)), font10_Bold);
			para.setAlignment(Element.ALIGN_RIGHT);
			cell.addElement(para);
			tbl_sanpham.addCell(cell);

			cell = new PdfPCell();
			cell.setPaddingBottom(7);
			cell.setBorderWidthLeft(0);
			cell.setColspan(2);
			cell.setVerticalAlignment(Element.ALIGN_CENTER);
			para = new Paragraph("", font10_Bold);
			para.setAlignment(Element.ALIGN_CENTER);
			cell.addElement(para);
			tbl_sanpham.addCell(cell);


			document.add(tbl_sanpham);

			//------------bang chu ki---------------------

			float[] wky = {6.0f*CONVERT, 5.0f*CONVERT, 5.0f*CONVERT, 5.0f*CONVERT,8.5f*CONVERT };
			PdfPTable tbl_vat = new PdfPTable(wky.length);
			tbl_vat.setWidthPercentage(100);
			tbl_vat.setHorizontalAlignment(Element.ALIGN_CENTER);
			tbl_vat.getDefaultCell().setBorder(0);
			tbl_vat.setWidths(wky);

			PdfPCell cellss = new PdfPCell();
			chunk =new Chunk("\n",font12_normal);
			cellss.addElement(chunk);
			chunk =new Chunk("        TL.Thủ trưởng", font12_normal);
			cellss.addElement(chunk);
			cellss.setHorizontalAlignment(Element.ALIGN_CENTER);
			cellss.setBorder(0);
			tbl_vat.addCell(cellss);


			cellss = new PdfPCell(new Paragraph("", font12_normal));
			chunk =new Chunk("\n", new Font(bf, 12, Font.BOLD));
			cellss.addElement(chunk);
			chunk =new Chunk("        Kế toán", font12_normal);
			cellss.addElement(chunk);
			cellss.setHorizontalAlignment(Element.ALIGN_CENTER);
			cellss.setBorder(0);
			tbl_vat.addCell(cellss);

			cellss = new PdfPCell();
			chunk =new Chunk("\n", font12_normal);
			cellss.addElement(chunk);
			chunk =new Chunk("Thủ kho", font12_normal);
			cellss.addElement(chunk);
			cellss.setHorizontalAlignment(Element.ALIGN_CENTER);
			cellss.setBorder(0);
			tbl_vat.addCell(cellss);

			cellss = new PdfPCell();
			chunk =new Chunk("\n",font12_normal);
			cellss.addElement(chunk);
			chunk =new Chunk("Người nhận", font12_normal);
			cellss.addElement(chunk);
			cellss.setHorizontalAlignment(Element.ALIGN_CENTER);
			cellss.setBorder(0);
			tbl_vat.addCell(cellss);

			cellss = new PdfPCell();
			if(ngayHD.length>=3){
				chunk =new Chunk("Ngày "+ ngayHD[2] + " tháng " + ngayHD[1] +" năm " + ngayHD[0],font12_normal);
				cellss.addElement(chunk);
			}
			else
			{
				chunk =new Chunk("Ngày         Tháng      Năm     ", font12_normal);
				cellss.addElement(chunk);
			}
			chunk =new Chunk("                Lập phiếu", font12_normal);
			cellss.addElement(chunk);
			cellss.setHorizontalAlignment(Element.ALIGN_CENTER);
			cellss.setBorder(0);
			tbl_vat.addCell(cellss);

			
			
			//dong 2
			for (int i = 0; i < wky.length-1; i++) {
				cellss = new PdfPCell(new Paragraph("", font12_normal));
				cellss.setPaddingTop(1.3f*CONVERT);
				cellss.setBorder(0);
				tbl_vat.addCell(cellss);
			}
			//nguoi tao
			cellss = new PdfPCell();
			cellss.setHorizontalAlignment(Element.ALIGN_CENTER);
			para=new Paragraph(nguoitao, font12_normal);
			para.setAlignment(Element.ALIGN_CENTER);
			cellss.setBorder(0);
			cellss.setPaddingTop(1.3f*CONVERT);
			cellss.addElement(para);
			tbl_vat.addCell(cellss);
			
			document.add(tbl_vat);
			document.close();

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
