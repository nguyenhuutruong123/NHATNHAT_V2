package geso.dms.distributor.servlets.hangtralainpp;

import geso.dms.center.db.sql.dbutils;
import geso.dms.distributor.beans.hangtralai.IErpHangTraLaiNpp;
import geso.dms.distributor.beans.hangtralai.imp.ErpHangTraLaiNpp;
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

import com.itextpdf.text.BaseColor;
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

public class InPhieuNhapKhoHangTraLaiPdfSvl  extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public InPhieuNhapKhoHangTraLaiPdfSvl()
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
		
		String querystring=request.getQueryString();
		String id=util.getId(querystring);
		
		IErpHangTraLaiNpp lsxBean = new ErpHangTraLaiNpp(id);
		lsxBean.setUserId(userId);
		lsxBean.init();

		response.setContentType("application/pdf");
		response.setHeader("Content-Disposition", " inline; filename=PhieunhapkhoCN.pdf");
		Document document = new Document();
		ServletOutputStream outstream = response.getOutputStream();
		this.CreatePxk(document, outstream, lsxBean, userId);
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		this.doGet(request, response);
	}



	private void CreatePxk(Document document, ServletOutputStream outstream, IErpHangTraLaiNpp pxkBean, String userid) throws IOException
	{
		dbutils db=new dbutils();
		//lay thong tin noi nhap
		String tennoiin="";
		String diachiin="";
		String nguoitao="";
		String tkno="632/521/333";
		String tkco="131";
		/*String sophieu=pxkBean.getId();*/
		String ngayphap=pxkBean.getNgayyeucau();
		String noigiao="";
		String khonhap ="";
		String sohoadon=pxkBean.getSoHoaDon();
		String lydo=pxkBean.getGhichu();
		String [] ngayHD = ngayphap.split("-");
		
		String[] spId = pxkBean.getSpId();
		String[] spMa = pxkBean.getSpMa(); 
		String[] spTen = pxkBean.getSpTen();
		String[] spDonvi = pxkBean.getSpDonvi();
		String[] spSolo = pxkBean.getSpSolo();
		String[] spSoluong = pxkBean.getSpSoluong();
		String[] spDongia = pxkBean.getSpGianhap();
		String[] spVat = pxkBean.getSpVat();
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
					diachiin=rsNoiin.getString("diachi");
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		

		//lay noi giao hang
		String doituong = pxkBean.getDoituong();
		String sql="";
		if (doituong.equals("1")) // la khach hang
		{
			sql = "select   kh.pk_seq, kh.ten as ten ,kh.dienthoai as dienthoai,'' as taikhoan, '' as fax, '' as nganhang, "
				+ "isnull(kh.masothue,'') as masothue,  kh.diachi   as diachi , k.TEN as khoxuat "
				+ "from Erp_HangTraLaiNpp a inner join khachhang kh on  kh.PK_SEQ=a.khachhang_fk  left join kho k on k.PK_SEQ =kh.KHO_FK "
				+ "where kh.TRANGTHAI=1 and a.pk_seq=" + pxkBean.getId();
		} else // la doi tac
		{
			sql = "select   kh.pk_seq, kh.ten as ten ,kh.dienthoai as dienthoai,kh.SOTAIKHOAN as taikhoan, kh.FAX as fax, kh.NGANHANG as nganhang, "
				+ "isnull(kh.masothue,'') as masothue,  kh.diachi   as diachi ,xuattaikho as khoxuat "
				+ "from Erp_HangTraLaiNpp a inner join nhaphanphoi kh on  kh.PK_SEQ=a.khachhang_fk  "
				+ "where kh.TRANGTHAI=1 and a.pk_seq=" + pxkBean.getId();
		}
		ResultSet rsKH = db.get(sql);
		if (rsKH != null) {
			try {
				if (rsKH.next()) {
					noigiao = rsKH.getString("TEN");
					khonhap=rsKH.getString("khoxuat");
				}
				rsKH.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
		
		// LAY SO CHUNG TU(sonetId RESET THEO THANG)
		String qr_sonet="select nh.sonetId as sonetid,nv.ten as ten from Erp_HangTraLaiNpp nh  \n "+
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
			Font font12_BoldIlatic = new Font(bf, 12, Font.BOLDITALIC);
			
			
			//tao header1
			PdfPTable tableheader =new PdfPTable(2);
			float []withd={20f,4f};
			tableheader.setWidthPercentage(100);
			tableheader.setWidths(withd);
			tableheader.setHorizontalAlignment(Element.ALIGN_CENTER);

			// ten 
			cell = new PdfPCell();
			cell.setBorder(0);
			para=new Paragraph(tennoiin.toUpperCase(),font12_Bold);
			para.setAlignment(Element.ALIGN_LEFT);
			cell.addElement(para);
			tableheader.addCell(cell);
			
			cell = new PdfPCell();
			cell.setBorder(0);
			cell.addElement(new Paragraph(" ",font12_Bold));
			tableheader.addCell(cell);

			//diachi
			cell = new PdfPCell();
			cell.setPaddingTop(-0.2f*CONVERT);
			cell.setBorder(0);
			para=new Paragraph(diachiin,font12_normal);
			para.setAlignment(Element.ALIGN_LEFT);
			cell.addElement(para);
			tableheader.addCell(cell);
			
			cell = new PdfPCell();
			cell.setBorder(0);
			cell.addElement(new Paragraph(" ",font12_Bold));
			tableheader.addCell(cell);

			//tieu de +//so sonetid
			cell = new PdfPCell();
			cell.setBorder(0);
			cell.setPaddingLeft(2.5f*CONVERT);
			cell.setHorizontalAlignment(Element.ALIGN_CENTER);
			para=new Paragraph("PHIẾU NHẬP KHO",new Font(bf,16,font12_Bold.BOLD));
			para.setAlignment(Element.ALIGN_CENTER);
			cell.addElement(para);
			tableheader.addCell(cell);
			
			
			cell = new PdfPCell();
			cell.setBorder(0);
			cell.setPaddingTop(0.4f*CONVERT);
			cell.setHorizontalAlignment(Element.ALIGN_CENTER);
			para=new Paragraph();
			chunk=new Chunk("Số: ",font12_normal);
			para.add(chunk);
			chunk=new Chunk(sonetid,font12_Bold );
			para.add(chunk);
			para.setAlignment(Element.ALIGN_LEFT);
			cell.addElement(para);
			tableheader.addCell(cell);
		

			//ngay +//tk no
			cell = new PdfPCell();
			cell.setBorder(0);
			cell.setPaddingTop(-0.2f*CONVERT);
			cell.setPaddingLeft(2.5f*CONVERT);
			cell.setHorizontalAlignment(Element.ALIGN_CENTER);
			para=new Paragraph("Ngày "+ ngayHD[2] + " tháng " + ngayHD[1] +" năm "+ngayHD[0],font12_normal);
			para.setAlignment(Element.ALIGN_CENTER);
			cell.addElement(para);
			tableheader.addCell(cell);
			
			
			cell = new PdfPCell();
			cell.setBorder(0);
			cell.setPaddingTop(-0.2f*CONVERT);
			cell.setHorizontalAlignment(Element.ALIGN_CENTER);
			para=new Paragraph();
			chunk=new Chunk("Nợ: ",font12_normal);
			para.add(chunk);
			chunk=new Chunk( tkno ,font12_Bold );
			para.add(chunk);
			para.setAlignment(Element.ALIGN_LEFT);
			cell.addElement(para);
			tableheader.addCell(cell);
			
			
			//tk co
			
			cell = new PdfPCell();
			cell.setBorder(0);
			cell.addElement(new Paragraph(" ",font12_Bold));
			tableheader.addCell(cell);
			
			cell = new PdfPCell();
			cell.setBorder(0);
			cell.setPaddingTop(-0.2f*CONVERT);
			cell.setHorizontalAlignment(Element.ALIGN_CENTER);
			para=new Paragraph();
			chunk=new Chunk("Có: ",font12_normal);
			para.add(chunk);
			chunk=new Chunk( tkco ,font12_Bold );
			para.add(chunk);
			para.setAlignment(Element.ALIGN_LEFT);
			cell.addElement(para);
			tableheader.addCell(cell);
			
			document.add(tableheader);
			
			//------------ bang thong tin  khach hang
			
			tableheader =new PdfPTable(1);
			tableheader.setWidthPercentage(100);
			tableheader.setHorizontalAlignment(Element.ALIGN_CENTER);
			//noi giao
			cell = new PdfPCell();
			cell.setBorder(0);
			cell.setHorizontalAlignment(Element.ALIGN_LEFT);
			para=new Paragraph();
			chunk=new Chunk("Họ và tên khách hàng: ",font12_normal);
			para.add(chunk);
			chunk=new Chunk(noigiao,font12_BoldIlatic);
			para.add(chunk);
			para.setAlignment(Element.ALIGN_LEFT);
			cell.addElement(para);
			tableheader.addCell(cell);

			//so chung tu + ngay giao
			cell = new PdfPCell();
			cell.setBorder(0);
			cell.setHorizontalAlignment(Element.ALIGN_CENTER);
			para=new Paragraph();

			chunk=new Chunk("Theo số hóa đơn: " ,font12_normal);
			para.add(chunk);
			chunk=new Chunk(sohoadon,font12_BoldIlatic);
			para.add(chunk);
			
			chunk=new Chunk("             Ngày: " +ngayHD[2] + "/" + ngayHD[1] +"/"+ngayHD[0],font12_normal);
			para.add(chunk);
			cell.addElement(para);
			tableheader.addCell(cell);

			//nhap tu kho
			cell = new PdfPCell();
			cell.setBorder(0);
			cell.setHorizontalAlignment(Element.ALIGN_CENTER);
			para=new Paragraph();
			chunk=new Chunk("Nhập từ kho: " ,font12_normal);
			para.add(chunk);
			/*chunk=new Chunk( khonhap ,font12_BoldIlatic);*/
			chunk=new Chunk( " " ,font12_BoldIlatic);
			para.add(chunk);
			para.setAlignment(Element.ALIGN_LEFT);
			cell.addElement(para);
			tableheader.addCell(cell);
			
			
			//ly do
			cell = new PdfPCell();
			cell.setBorder(0);
			cell.setHorizontalAlignment(Element.ALIGN_CENTER);
			para=new Paragraph();
			chunk=new Chunk("Nội dung: "  ,font12_normal);
			para.add(chunk);
			chunk=new Chunk( lydo ,font12_BoldIlatic);
			para.add(chunk);
			para.setAlignment(Element.ALIGN_LEFT);
			cell.addElement(para);
			tableheader.addCell(cell);
			document.add(tableheader);


			//bang danh sach san pham
			float[] tbl_withd = { 7.0f, 50f, 25f ,10.0f, 15.0f, 17f,17f ,17f, 17f };

			PdfPTable tbl_sanpham = new PdfPTable(tbl_withd.length);
			tbl_sanpham.setSpacingBefore(10.0f );
			tbl_sanpham.setWidthPercentage(100);
			tbl_sanpham.setHorizontalAlignment(Element.ALIGN_LEFT);
			tbl_sanpham.setWidths(tbl_withd);
			PdfPCell cells;
			double tongtien = 0;
			//add tieu de
			String[] tieude = new String[] { "Stt","Tên hàng","Số lô","Đvt","Số lượng","Giá bán", "Giá vốn", "Tiền vốn","Tiền bán"};
			for (int j = 0; j < tbl_withd.length; j++)
			{
				cells = new PdfPCell(new Paragraph(tieude[j], font10_Bold));
				cells.setHorizontalAlignment(Element.ALIGN_CENTER);
				cells.setPaddingBottom(7);
				cells.setBorderColor(BaseColor.LIGHT_GRAY);
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
					
				/*	
					//tach lay so lo thui
					if(solo.contains("-")){
						String []sl=solo.split("-");
						solo=sl[0];
					}
				*/
					if(solo.toUpperCase().trim().equals("NA")){
						solo="";	
					}
					
					String[] sp = new String[] {i+1 +"",ten,solo,donvi,
							DinhDangTraphacoDMS(formatter1.format(Double.parseDouble (soluong.replaceAll(",","")))),
							DinhDangTraphacoDMS(formatter.format(Double.parseDouble(dongia.replaceAll(",","")))),
							DinhDangTraphacoDMS(formatter.format(Double.parseDouble(dongia.replaceAll(",","")))),
							DinhDangTraphacoDMS( formatter1.format(thanhtien)),
							DinhDangTraphacoDMS( formatter1.format(thanhtien))};

					for (int j = 0; j < tbl_withd.length; j++)
					{
						cells = new PdfPCell(new Paragraph(sp[j], font10_normal));

						if(j==1){
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
						cells.setBorderColor(BaseColor.LIGHT_GRAY);
						tbl_sanpham.addCell(cells);	
					}
				}
			}

			//tong tien
			cell = new PdfPCell();
			cell.setColspan(4);
			cell.setBorderColor(BaseColor.LIGHT_GRAY);
			cell.setPaddingBottom(7);
			cell.setVerticalAlignment(Element.ALIGN_CENTER);
			para = new Paragraph("Tổng cộng ",  font10_Bold);
			para.setAlignment(Element.ALIGN_CENTER);
			cell.addElement(para);
			tbl_sanpham.addCell(cell);

			cell = new PdfPCell();
			cell.setPaddingBottom(7);
			cell.setBorderColor(BaseColor.LIGHT_GRAY);
			/*cell.setBorderWidthRight(0);*/
			cell.setColspan(5);
			cell.setVerticalAlignment(Element.ALIGN_RIGHT);
			para = new Paragraph(DinhDangTraphacoDMS (formatter.format(tongtien)), font10_Bold);
			para.setAlignment(Element.ALIGN_RIGHT);
			cell.addElement(para);
			tbl_sanpham.addCell(cell);

			/*cell = new PdfPCell();
			cell.setPaddingBottom(7);
			cell.setBorderWidthLeft(0);
			cell.setColspan(2);
			cell.setVerticalAlignment(Element.ALIGN_CENTER);
			para = new Paragraph("", font10_Bold);
			para.setAlignment(Element.ALIGN_CENTER);
			cell.addElement(para);
			tbl_sanpham.addCell(cell);*/


			document.add(tbl_sanpham);

			//------------bang chu ki---------------------

			float[] wky = {6.0f*CONVERT, 6.0f*CONVERT, 6.0f*CONVERT, 5.0f*CONVERT,8.5f*CONVERT };
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
			chunk =new Chunk("        Kế toán trưởng", font12_normal);
			cellss.addElement(chunk);
			cellss.setHorizontalAlignment(Element.ALIGN_CENTER);
			cellss.setBorder(0);
			tbl_vat.addCell(cellss);

			cellss = new PdfPCell();
			chunk =new Chunk("\n", font12_normal);
			cellss.addElement(chunk);
			chunk =new Chunk("           Thủ kho", font12_normal);
			cellss.addElement(chunk);
			cellss.setHorizontalAlignment(Element.ALIGN_CENTER);
			cellss.setBorder(0);
			tbl_vat.addCell(cellss);

			cellss = new PdfPCell();
			chunk =new Chunk("\n",font12_normal);
			cellss.addElement(chunk);
			chunk =new Chunk("Người giao", font12_normal);
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
	}}
