package geso.dms.distributor.servlets.hoadontaichinhNPP;

import geso.dms.center.beans.xuatkho.IErpYeucauxuatkho;
import geso.dms.center.beans.xuatkho.imp.ErpYeucauxuatkho;
import geso.dms.distributor.beans.hoadontaichinhNPP.IErpHoadontaichinhNPP;
import geso.dms.distributor.beans.hoadontaichinhNPP.imp.ErpHoadontaichinhNPP;
import geso.dms.distributor.beans.xuatkho.IErpYeucauxuatkhoNpp;
import geso.dms.distributor.beans.xuatkho.imp.ErpYeucauxuatkhoNpp;
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
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Rectangle;
import com.itextpdf.text.pdf.BaseFont;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

public class ErpInPhieuxuatkhoNPPPdfSvl extends HttpServlet{

	private static final long serialVersionUID = 1L;

	public ErpInPhieuxuatkhoNPPPdfSvl()
	{
		super();
	}
	float CONVERT = 28.346457f; // = 1cm

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		dbutils db = new dbutils();
		HttpSession session = request.getSession();
		String userId = (String) session.getAttribute("userId");
		// String userTen = (String) session.getAttribute("userTen");

		Utility util = new Utility();
		if (userId.length() == 0)
			userId = request.getParameter("userId");

		String querystring = request.getQueryString();

		String id = util.getId(querystring); 
		if (id.length()==0)
			id = util.antiSQLInspection(request.getParameter("id")); 
		
		String type = request.getParameter("type");
		System.out.println(" lay nguon in tu hoa don : "+type);
		
		// in tu hoa don tai chinh ==> chuyen ve id phieu xuat
		if(type.equals("HD_PXK"))
		{
			String qr="select ycxk_fk as maphieuxuat from ERP_YCXUATKHONPP_DDH where hoadon_fk=" +id; 
			ResultSet rsPXK=db.get(qr);
			if(rsPXK!=null){
				try {
					while(rsPXK.next()){
					id=rsPXK.getString("maphieuxuat");
					}
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}


		IErpYeucauxuatkhoNpp lsxBean = new ErpYeucauxuatkhoNpp(id);
		lsxBean.setUserId(userId); 
		lsxBean.init();


		String task = request.getParameter("task");    
		/*if (querystring.indexOf("pdf") > 0)
		{*/

		response.setContentType("application/pdf");
		response.setHeader("Content-Disposition", " inline; filename=PhieuXuatKhoTT.pdf");

		Document document = new Document();
		ServletOutputStream outstream = response.getOutputStream();

		this.form_phieuxuat(document, outstream, lsxBean ,userId, db);

		document.close();

		/*} */

	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		this.doGet(request, response);
	}


	private void form_phieuxuat(Document document,ServletOutputStream outstream,IErpYeucauxuatkhoNpp pxkBean,String userid, dbutils db)
	{
		document.open();

		String Sodms=pxkBean.getId(); //pkxBean la phieu xuat kho, sodms la so pk_seq cua  hoa don, iddh
		String tk_no="632/131";//tai khoan no co dinh
		String tk_co="";
		String diengia="";
		String khoxuat="";
		String chietkhau="";
		String giamgia="giam gia";
		String sohoadon="";
		String ngayxuatHD=""; //lay ngay hien tai
		String tienvat="";
		String tongtien_vat="";
		String vat="";
		String kyhieu="";
		String nguoitao="";


		//don vi mua
		String npp_fk="";
		String khId="";
		String tenkh = "";
		String chucuahieu = "";
		String diachikh = "";
		String hoadonid= "";
		String diachigiaohang = "";
		String mafast = "";
		String dienthoai = "";
		String masothue = "";
		String nguoilienhe = "";
		String ghichu = "";
		String tongkhuyenmai = "0";

		//lay thong tin khach hang
		//thong tin kho xuat

		ResultSet khonhapRs = pxkBean.getKhoNhapRs();
		if(khonhapRs!=null){
			try {
				while(khonhapRs.next()){
					if(khonhapRs.getString("PK_SEQ").equals(pxkBean.getKhoNhapId())){
						khoxuat=khonhapRs.getString("TEN");
						System.out.print(" \n kho xuat:"+ khoxuat);
					}
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}


		//================ lay thong tin tu hoa don map ERP_YCXUATKHONPP_DDH vs ERP_HOADONNPP

		String qr_hoadon="\n select hd.PK_SEQ as idhd,hd.SOHOADON as sohoadon, hd.NGAYXUATHD as ngayxuathd, hd.GHICHU as ghichu, hd.chietkhau as chietkhau,hd.KYHIEU AS KYHIEU, "+    
		"\n hd.vat as tienvat, hd.tongtienavat as tongtienavat, ISNULL(hd.NPP_FK,0) as NPP_FK , ISNULL(HD.KhachHang_FK,0) AS KHACHHANG_FK,"+  
		"\n (select Ten from ERP_KHOTT where PK_SEQ = hd.kho_fk) as XUATTAIKHO "+
		"\n from ERP_HOADONNPP HD  "+    
		"\n INNER JOIN ERP_YCXUATKHONPP_DDH kho_hd on hd.PK_SEQ=kho_hd.hoadon_fk "+    
		"\n where hd.pk_seq not in (3,5) and kho_hd.ycxk_fk='"+pxkBean.getId()+"'";

		System.out.println("\n thong tin hoa don: "+qr_hoadon +"\n");

		ResultSet rs_hoadon=db.get(qr_hoadon);
		if(rs_hoadon!=null){
			try {
				while(rs_hoadon.next())
				{
					ghichu=rs_hoadon.getString("ghichu");
					hoadonid=rs_hoadon.getString("idhd");
					diengia=rs_hoadon.getString("ghichu");
					sohoadon=rs_hoadon.getString("sohoadon");
					ngayxuatHD=rs_hoadon.getString("ngayxuathd");
					chietkhau=rs_hoadon.getString("chietkhau");
					kyhieu=rs_hoadon.getString("KYHIEU");
					tienvat=rs_hoadon.getString("tienvat");
					tongtien_vat=rs_hoadon.getString("tongtienavat");
					npp_fk=rs_hoadon.getString("NPP_FK");
					khId=rs_hoadon.getString("KHACHHANG_FK");
					//khoxuat=rs_hoadon.getString("XUATTAIKHO");
				}
			} catch (Exception e) {
				e.printStackTrace();
			}

		}

		//====================lay thong tin khach hang

		//LAY THONG TIN KHACHHANG	nhu display
		String sql = "select isnull(kh.chucuahieu,'')nguoilienhe,isnull(kh.masothue,'NA')masothue,isnull(kh.dienthoai,kh.didong)dienthoai," +
				"\n isnull(kh.diachi,'')diachigiaohang,kh.mafast,kh.pk_seq, kh.TEN as donvi,isnull(kh.chucuahieu,'') as chucuahieu, " +
				"\n kh.diachi as diachi ,isnull(masothue,'') as masothue,kh.pk_seq, kh.TEN as donvi, kh.TENNGUOIDAIDIEN as chucuahieu , kh.diachi as diachi  "+    
		"\n  from NHAPHANPHOI kh where kh.TRANGTHAI = '1'  AND kh.PK_SEQ = '" + pxkBean.getKhId() + "' and kh.loaiNPP = '4' ";
		if(pxkBean.getXuatcho().equals("1"))
			sql="select kh.pk_seq, kh.TEN as donvi,isnull(kh.chucuahieu,'') as chucuahieu, kh.diachi as diachi   "+     
			"\n from KHACHHANG kh where kh.trangthai = '1' and kh.KBH_FK = '100052' and kh.NPP_FK ="+	pxkBean.getNppId();

		System.out.println("\n Lấy DON VI MUA HANG: "+sql +"\n");
		ResultSet rsLayKH= db.get(sql);
		if(rsLayKH!=null){
			try {
				while(rsLayKH.next())
				{
					nguoilienhe = rsLayKH.getString("nguoilienhe");
					dienthoai = rsLayKH.getString("dienthoai");
					masothue = rsLayKH.getString("masothue");
					mafast = rsLayKH.getString("mafast");
					diachigiaohang = rsLayKH.getString("diachigiaohang");
					tenkh = rsLayKH.getString("DONVI");
					chucuahieu=rsLayKH.getString("chucuahieu");
					diachikh = rsLayKH.getString("DIACHI");
					System.out.println("\n Lấy dia chi DON VI MUA HANG: "+diachikh +"\n");
				}  
				rsLayKH.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		} 
		
		//lay thong tin danh sach san pham
		String[] spId = pxkBean.getSpId();
		String[] spMa = pxkBean.getSpMa(); 
		String[] spTen = pxkBean.getSpTen();
		String[] spDonvi = pxkBean.getSpDonvi();
		String[] spSoluong = pxkBean.getSpSoluong();
		
		if(spMa!=null){
		tk_co=taikhoanco(spMa);
		}
		
		//lay top(1) vat %
		String qr_vat="select top(1) vat as vat from ERP_HOADONNPP_SP where hoadon_fk='"+hoadonid+"'";
		ResultSet rs_vat=db.get(qr_vat);
		if(rs_vat!=null){
			try {
				while (rs_vat.next())
				{
					vat=rs_vat.getString("vat") ;
				}
				rs_vat.close();
			} catch (Exception e) {
				// TODO: handle exception
			}
		}

		
		//lay noi xuat phieu O CHI NHANH 
		String qr_noiin="select npp.ten as ten, npp.DIACHI as diachi \n "+       
						 "from NHANVIEN nv inner join nhaphanphoi npp on nv.CONVSITECODE=npp.SITECODE \n"+    
						 "where nv.PHANLOAI = 1 and nv.PK_SEQ=" +userid;
		String tennoiin="";
		String diachiin="";
		ResultSet rsNoiin=db.get(qr_noiin);
		if(rsNoiin!=null){
			try {
				while(rsNoiin.next()){
					tennoiin=rsNoiin.getString("ten");
					diachiin=rsNoiin.getString("diachi");
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		

		// LAY SO CHUNG TU(  sonetId RESET THEO THANG)
		String qr_sonet="select nh.sonetId as sonetid,nv.ten as ten from ERP_YCXUATKHONPP nh  \n "+
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

		
		//=====================do du lieu


		try {
			//format
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
			Font font = new Font(bf, 13, Font.BOLD);
			Font font2 = new Font(bf, 8, Font.BOLD);
			Font font3 = new Font(bf, 10, Font.BOLD);
			Font font12_normal= new Font(bf, 12, Font.NORMAL);


			// them header1
			PdfPTable tableheader =new PdfPTable(1);
			tableheader.setWidthPercentage(100);	
			//dia chi dcl
			cell = new PdfPCell();
			cell.setBorder(0);
			cell.setHorizontalAlignment(Element.ALIGN_LEFT);
			
			para=new Paragraph(tennoiin.toUpperCase(),new Font(bf, 12, Font.BOLD));
			para.setAlignment(Element.ALIGN_LEFT);
			cell.addElement(para);
			
			para=new Paragraph(diachiin,new Font(bf, 12, Font.BOLD));
			para.setAlignment(Element.ALIGN_LEFT);
			cell.addElement(para);
		
			tableheader.addCell(cell);
			
			//add tua de
			cell = new PdfPCell();
			cell.setBorder(0);
			cell.setHorizontalAlignment(Element.ALIGN_CENTER);
			para=new Paragraph("PHIẾU XUẤT KHO",new Font(bf, 19, Font.BOLD));
			para.setAlignment(Element.ALIGN_CENTER);
			cell.addElement(para);
			tableheader.addCell(cell);
			
			
			//NGAY THANG NAM
			cell = new PdfPCell();
			cell.setBorder(0);
			cell.setHorizontalAlignment(Element.ALIGN_CENTER);
			/*para=new Paragraph("(BÁN HÀNG DƯỢC PHẨM)",font12_normal);
			para.setAlignment(Element.ALIGN_CENTER);
			cell.addElement(para);*/
			
			String [] ngayHD = ngayxuatHD.split("-");
			para = new Paragraph("Ngày "+ ngayHD[2] + " Tháng " + ngayHD[1] +" Năm " + ngayHD[0],new Font(bf, 12, Font.BOLD));
			para.setAlignment(Element.ALIGN_CENTER);
			cell.addElement(para);
			tableheader.addCell(cell);
			
			document.add(tableheader);

			//----------------------ADD INFO KHACH HANG : bang hai cot -----------------------------
			PdfPTable tbl_khachhang =new PdfPTable(2);
			float kh_withd []={13.0f*CONVERT,4.0f*CONVERT};
			tbl_khachhang.setWidths(kh_withd);
			tbl_khachhang.setWidthPercentage(100);
			tbl_khachhang.getDefaultCell().setBorder(PdfPCell.NO_BORDER);
			
			cell = new PdfPCell();
			cell.setBorder(0);
			cell.setColspan(2);
			cell.setVerticalAlignment(Element.ALIGN_LEFT);
			para = new Paragraph("Mã KH: "+ mafast , font12_normal);
			cell.addElement(para);
			tbl_khachhang.addCell(cell);
			
			cell = new PdfPCell();
			cell.setBorder(0);
			cell.setColspan(2);
			cell.setVerticalAlignment(Element.ALIGN_LEFT);
			para = new Paragraph("Khách hàng: "+ tenkh , font12_normal);
			cell.addElement(para);
			tbl_khachhang.addCell(cell);
			
			cell = new PdfPCell();
			cell.setBorder(0);
			cell.setColspan(2);
			cell.setVerticalAlignment(Element.ALIGN_LEFT);
			para = new Paragraph("Địa chỉ: "+ diachikh , font12_normal);
			cell.addElement(para);
			tbl_khachhang.addCell(cell);
			
			cell = new PdfPCell();
			cell.setBorder(0);
			cell.setColspan(2);
			cell.setVerticalAlignment(Element.ALIGN_LEFT);
			para = new Paragraph("Địa chỉ giao hàng: "+ diachigiaohang , font12_normal);
			cell.addElement(para);
			tbl_khachhang.addCell(cell);
			
			cell = new PdfPCell();
			cell.setBorder(0);
			cell.setPaddingLeft(-1);
			cell.setVerticalAlignment(Element.ALIGN_BOTTOM); //add bang 2 cot
			
			PdfPTable tbl_shd_ngay =new PdfPTable(2);
			tbl_shd_ngay.getDefaultCell().setBorder(PdfPCell.NO_BORDER);
			float withd_shd []={15.0f*CONVERT,8.0f*CONVERT};
			tbl_shd_ngay.setWidths(withd_shd);
			tbl_shd_ngay.setWidthPercentage(100);
			PdfPCell cell_shd = new PdfPCell(); //ad so hoa don
			cell_shd.setBorder(0);
			cell_shd.setVerticalAlignment(Element.ALIGN_LEFT);
			para = new Paragraph("Đại diện (Họ và tên KH): "+chucuahieu, font12_normal);
			cell_shd.addElement(para);
			tbl_shd_ngay.addCell(cell_shd);

			PdfPCell cell_ngay = new PdfPCell(); //ad so hoa don
			cell_ngay.setBorder(0);
			cell_ngay.setVerticalAlignment(Element.ALIGN_LEFT);
			para = new Paragraph("Mã số thuế: "+masothue, font12_normal);
			cell_ngay.addElement(para);
			tbl_shd_ngay.addCell(cell_ngay);
			cell.addElement(tbl_shd_ngay);
			tbl_khachhang.addCell(cell);
			
			cell=new PdfPCell(new Paragraph("",font2));
			cell.setBorder(0);
			cell.setPaddingLeft(-1);
			tbl_khachhang.addCell(cell);
			
			tbl_shd_ngay =new PdfPTable(2);
			tbl_shd_ngay.getDefaultCell().setBorder(PdfPCell.NO_BORDER);
			withd_shd[0] = 15.0f*CONVERT;
			tbl_shd_ngay.setWidths(withd_shd);
			tbl_shd_ngay.setWidthPercentage(100);
			cell_shd = new PdfPCell(); //ad so hoa don
			cell_shd.setBorder(0);
			cell_shd.setVerticalAlignment(Element.ALIGN_LEFT);
			para = new Paragraph("Người liên hệ: "+nguoilienhe, font12_normal);
			cell_shd.addElement(para);
			tbl_shd_ngay.addCell(cell_shd);

			cell_ngay = new PdfPCell(); //ad so hoa don
			cell_ngay.setBorder(0);
			cell_ngay.setVerticalAlignment(Element.ALIGN_LEFT);
			para = new Paragraph("Số điện thoại: "+dienthoai, font12_normal);
			cell_ngay.addElement(para);
			tbl_shd_ngay.addCell(cell_ngay);
			cell.addElement(tbl_shd_ngay);
			tbl_khachhang.addCell(cell);

			//rong
			cell=new PdfPCell(new Paragraph("",font2));
			cell.setBorder(0);
			cell.setPaddingLeft(-1);
			tbl_khachhang.addCell(cell);
			
			tbl_shd_ngay =new PdfPTable(2);
			tbl_shd_ngay.getDefaultCell().setBorder(PdfPCell.NO_BORDER);
			withd_shd[0] = 15.0f*CONVERT;
			tbl_shd_ngay.setWidths(withd_shd);
			tbl_shd_ngay.setWidthPercentage(100);
			cell_shd = new PdfPCell(); //ad so hoa don
			cell_shd.setBorder(0);
			cell_shd.setVerticalAlignment(Element.ALIGN_LEFT);
			para = new Paragraph("Theo hóa đơn số: "+sohoadon.trim() +kyhieu, font12_normal);
			cell_shd.addElement(para);
			tbl_shd_ngay.addCell(cell_shd);

			cell_ngay = new PdfPCell(); //ad so hoa don
			cell_ngay.setBorder(0);
			cell_ngay.setVerticalAlignment(Element.ALIGN_LEFT);
			para = new Paragraph("Ngày: "+ngayHD[2]+"/"+ngayHD[1]+"/"+ngayHD[0], font12_normal);
			cell_ngay.addElement(para);
			tbl_shd_ngay.addCell(cell_ngay);
			cell.addElement(tbl_shd_ngay);
			tbl_khachhang.addCell(cell);

			//rong
			cell=new PdfPCell(new Paragraph("",font2));
			cell.setBorder(0);
			tbl_khachhang.addCell(cell);
			
			//dong 4 dien giai
			cell = new PdfPCell();
			cell.setBorder(0);
			cell.setColspan(2);
			cell.setVerticalAlignment(Element.ALIGN_LEFT);
			para = new Paragraph("Ghi chú: "+ ghichu , font12_normal);
			cell.addElement(para);
			tbl_khachhang.addCell(cell);

			//dong 5 kho xuat
			cell = new PdfPCell();
			cell.setBorder(0);
			cell.setColspan(2);
			cell.setVerticalAlignment(Element.ALIGN_LEFT);
			para = new Paragraph("Xuất từ kho: "+ khoxuat , font12_normal);
			cell.addElement(para);
			tbl_khachhang.addCell(cell);

			//rong
			cell=new PdfPCell(new Paragraph("",font2));
			cell.setBorder(0);
			tbl_khachhang.addCell(cell);

			document.add(tbl_khachhang);

			// Table Content---------------------------------bang du lieu----------------------------------

			float[] tbl_withd = { 7.0f, 60f, 10f , 15.0f, 15.0f, 15f, 20f, 15.0f, 15.0f };

			PdfPTable tbl_sanpham = new PdfPTable(tbl_withd.length);
			tbl_sanpham.setSpacingBefore(10.0f );
			tbl_sanpham.setWidthPercentage(100);
			tbl_sanpham.setHorizontalAlignment(Element.ALIGN_LEFT);
			tbl_sanpham.setWidths(tbl_withd);
			PdfPCell cells;
			double tongtien = 0;
			//add tieu de
			String[] tieude = new String[] { "Stt","Tên hàng","ĐVT","Đơn giá","Giá chiết khấu","Số lượng","Thành tiền","CK","Ghi chú"};
			for (int j = 0; j < tbl_withd.length; j++)
			{
				cells = new PdfPCell(new Paragraph(tieude[j], new Font(bf, 10, Font.BOLD)));
				cells.setHorizontalAlignment(Element.ALIGN_CENTER);
				cells.setPaddingBottom(7);
				tbl_sanpham.addCell(cells);	
			}


			if(spId != null)
			{
				for(int i = 0; i < spId.length; i++)
				{
					String tenhang=spTen[i];
					//String dvtinh=spDonvi[i];
					String dvtinh="";
					double soLUONG =Double.valueOf(spSoluong[i]) ;
					String  masp=spMa[i];
					String solo="";
					String ck="";
					double dongia = 0;
					double dongiagoc = 0;

					//tim solo, chietkhau, don gia thong qua masp va hoadonid

					/*String  qr_solo="select solo, CHIETKHAU ,DONGIA ,ngayhethan  from ERP_HOADONNPP_SP_CHITIET  "+    
					"where hoadon_fk=" +hoadonid +"and ma='"+masp+"'";*/
					
					String  qr_solo="select [dbo].[GiaBanLeNppSanPham](100025,hdx.npp_fk,sp.pk_seq,hdx.ngayxuathd ) dongiagoc, dh.solo as solo, dh.CHIETKHAU as CHIETKHAU ,dh.DONGIA as DONGIA ,dh.ngayhethan as ngayhethan, "+    
					 "\n (select dv.DONVI from DONVIDOLUONG dv where dv.pk_seq=dh.DVCHUAN) as donvi "+    
					 "\n  from ERP_HOADONNPP_SP_CHITIET dh " +
					 "\n inner join ERP_HOADONNPP hdx on hdx.pk_seq = dh.hoadon_fk " +
					 "\n inner join sanpham sp on sp.ma = dh.ma " +
					 "\n  where dh.hoadon_fk=" +hoadonid +" and sp.ma='"+masp+"'";
					
					System.out.print("\n  qr so lo:"+qr_solo);
					ResultSet rs_solo=db.get(qr_solo);
					if(rs_solo!=null){
						try {
							while(rs_solo.next())
							{
								solo=rs_solo.getString("solo");
								dongia=Double.valueOf(rs_solo.getString("DONGIA")) ;
								dongiagoc=Double.valueOf(rs_solo.getString("DONGIAgoc")) ;
								ck=rs_solo.getString("CHIETKHAU");
								dvtinh=rs_solo.getString("donvi");
							}
						} catch (Exception e) {
							e.printStackTrace();
						}
					}
					
					if(solo.equals("NA")||solo.equals("na")){
						solo="";
					}

					double thanhtien = Math.floor(soLUONG*dongia);
					tongtien +=thanhtien;
					
					if(solo.equals("NA")||solo.equals("na")){
						solo="";
					}

					//============tinh quy doi ra thung cho san pham
					
					String  quydoi=quydoi(spId[i], db, soLUONG);
					
					String[] arr = new String[] { Integer.toString(i+1),tenhang,dvtinh,
							DinhDangTraphacoDMS(formatter.format(dongia)),DinhDangTraphacoDMS(formatter.format(dongiagoc)),
							DinhDangTraphacoDMS(formatter1.format(soLUONG)),
							DinhDangTraphacoDMS(formatter1.format(thanhtien)),
							DinhDangTraphacoDMS(formatter.format((dongiagoc-dongia)*soLUONG)),
							quydoi };


					for (int j = 0; j < tbl_withd.length; j++)
					{
						cells = new PdfPCell(new Paragraph(arr[j], new Font(bf, 10, Font.NORMAL)));
						cells.setPaddingBottom(7);
						if(j==1||j==6){
							cells.setHorizontalAlignment(Element.ALIGN_LEFT);
						}
						
						else
							if(j==4 ||j==3||j==5){
								cells.setHorizontalAlignment(Element.ALIGN_RIGHT);
							}
							else
						{
							cells.setHorizontalAlignment(Element.ALIGN_CENTER);
						}
						tbl_sanpham.addCell(cells);	
					}


				}//cua for splist

			}	

			//cong tien hang 
			cell = new PdfPCell();
			cell.setBorderWidthBottom(0);
			cell.setBorderWidthRight(0);
			cell.setBorderWidthTop(0);
			cell.setColspan(5);
			cell.setVerticalAlignment(Element.ALIGN_LEFT);
			cell.setPaddingLeft(7.5f * CONVERT);
			para = new Paragraph("Cộng tiền hàng " , font12_normal);
			cell.addElement(para);
			tbl_sanpham.addCell(cell);
			
			cell = new PdfPCell();
			cell.setBorder(0);
			cell.setColspan(2);
			cell.setVerticalAlignment(Element.ALIGN_RIGHT);
			cell.setPaddingLeft(0.5f * CONVERT);
			para = new Paragraph(DinhDangTraphacoDMS(formatter1.format(tongtien))+" đồng" , font12_normal);
			para.setAlignment(Element.ALIGN_RIGHT);
			cell.addElement(para);
			tbl_sanpham.addCell(cell);
			
			cell = new PdfPCell();
			cell.setBorderWidthBottom(0);
			cell.setBorderWidthLeft(0);
			cell.setBorderWidthTop(0);
			cell.setColspan(2);
			cell.setVerticalAlignment(Element.ALIGN_LEFT);
			para = new Paragraph("", font12_normal);
			cell.addElement(para);
			tbl_sanpham.addCell(cell);
			
			
			//chiet khau
			cell = new PdfPCell();
			cell.setBorderWidthBottom(0);
			cell.setBorderWidthRight(0);
			cell.setBorderWidthTop(0);
			cell.setColspan(5);
			cell.setVerticalAlignment(Element.ALIGN_LEFT);
			cell.setPaddingLeft(7.5f * CONVERT);
			para = new Paragraph("Chiết khấu         %", font12_normal);
			cell.addElement(para);
			tbl_sanpham.addCell(cell);
			
			cell = new PdfPCell();
			cell.setBorder(0);
			cell.setColspan(2);
			cell.setVerticalAlignment(Element.ALIGN_RIGHT);
			cell.setPaddingLeft(0.5f * CONVERT);
			para = new Paragraph( DinhDangTraphacoDMS (formatter.format(Math.round( Double.parseDouble(chietkhau))))+" đồng" , font12_normal);
			para.setAlignment(Element.ALIGN_RIGHT);
			cell.addElement(para);
			tbl_sanpham.addCell(cell);
			
			cell = new PdfPCell();
			cell.setBorderWidthBottom(0);
			cell.setBorderWidthTop(0);
			cell.setBorderWidthLeft(0);
			cell.setColspan(2);
			cell.setVerticalAlignment(Element.ALIGN_LEFT);
			para = new Paragraph("", font12_normal);
			cell.addElement(para);
			tbl_sanpham.addCell(cell);
			
			//giam gia Gi ảm giá hàng bán: (bo roi)
			
			//thue vat
			cell = new PdfPCell();
			cell.setBorderWidthBottom(0);
			cell.setBorderWidthRight(0);
			cell.setBorderWidthTop(0);
			cell.setColspan(5);
			cell.setVerticalAlignment(Element.ALIGN_LEFT);
			cell.setPaddingLeft(7.5f * CONVERT);
			para = new Paragraph("Thuế GTGT ("+ vat+ " % )", font12_normal);
			cell.addElement(para);
			tbl_sanpham.addCell(cell);
			
			cell = new PdfPCell();
			cell.setBorder(0);
			cell.setColspan(2);
			cell.setVerticalAlignment(Element.ALIGN_RIGHT);
			cell.setPaddingLeft(0.5f * CONVERT);
			para = new Paragraph(DinhDangTraphacoDMS (formatter.format(Math.round(Double.parseDouble(tienvat))))+" đồng" , font12_normal);
			para.setAlignment(Element.ALIGN_RIGHT);
			cell.addElement(para);
			tbl_sanpham.addCell(cell);
			
			cell = new PdfPCell();
			cell.setBorderWidthBottom(0);
			cell.setBorderWidthTop(0);
			cell.setBorderWidthLeft(0);
			cell.setColspan(2);
			cell.setVerticalAlignment(Element.ALIGN_LEFT);
			para = new Paragraph("", font12_normal);
			cell.addElement(para);
			tbl_sanpham.addCell(cell);
			
			//tong cong
			cell = new PdfPCell();
			cell.setBorderWidthTop(0);
			cell.setBorderWidthRight(0);
			cell.setPaddingBottom(7);
			cell.setColspan(5);
			cell.setVerticalAlignment(Element.ALIGN_LEFT);
			cell.setPaddingLeft(7.5f * CONVERT);
			para = new Paragraph("Tổng cộng ", font12_normal);
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
			para = new Paragraph(DinhDangTraphacoDMS (formatter.format(Double.parseDouble(tongtien_vat)))+" đồng" , font12_normal);
			para.setAlignment(Element.ALIGN_RIGHT);
			cell.addElement(para);
			tbl_sanpham.addCell(cell);
			
			cell = new PdfPCell();
			cell.setBorderWidthTop(0);
			cell.setBorderWidthLeft(0);
			cell.setPaddingBottom(7);
			cell.setColspan(2);
			cell.setVerticalAlignment(Element.ALIGN_LEFT);
			para = new Paragraph("", font12_normal);
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
			para=new Paragraph("\n TL.Thủ trưởng ",font12_normal);
			para.setAlignment(Element.ALIGN_CENTER);
			cellss.addElement(para);
			cellss.setHorizontalAlignment(Element.ALIGN_CENTER);
			cellss.setBorder(0);
			tbl_vat.addCell(cellss);
			
			
			cellss = new PdfPCell(new Paragraph());
			para=new Paragraph("\n Kế toán ",font12_normal);
			para.setAlignment(Element.ALIGN_CENTER);
			cellss.addElement(para);
			cellss.setHorizontalAlignment(Element.ALIGN_CENTER);
			cellss.setBorder(0);
			tbl_vat.addCell(cellss);
			
			cellss = new PdfPCell();
			para=new Paragraph("\n Thủ kho ",font12_normal);
			para.setAlignment(Element.ALIGN_CENTER);
			cellss.addElement(para);
			cellss.setHorizontalAlignment(Element.ALIGN_CENTER);
			cellss.setBorder(0);
			tbl_vat.addCell(cellss);
			
			cellss = new PdfPCell();
			para=new Paragraph("\n Người nhận ",font12_normal);
			para.setAlignment(Element.ALIGN_CENTER);
			cellss.addElement(para);
			cellss.setHorizontalAlignment(Element.ALIGN_CENTER);
			cellss.setBorder(0);
			tbl_vat.addCell(cellss);
			
			cellss = new PdfPCell();
			para=new Paragraph("Ngày "+ ngayHD[2] + " Tháng " + ngayHD[1] +" Năm " + ngayHD[0]+ "\n Lập phiếu",font12_normal);
			para.setAlignment(Element.ALIGN_CENTER);
			cellss.addElement(para);
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
			e.printStackTrace();
		}
	}

	private String getDate()
	{			
		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		Date date = new Date();
		dateFormat.format(date);
		return dateFormat.format(date);
	}
	
	private String DinhDangTraphacoDMS(String sotien)
	{
		sotien = sotien.replaceAll("\\.", "_");
		sotien = sotien.replaceAll(",", "\\.");
		sotien = sotien.replaceAll("_", ",");

		return sotien;
	}

	private String taikhoanco(String [] masp)
	{
		String tkco="";
		String tkco1="";
		String tkco2="";
		String []mang155={"2","4","7","E","N","Z"};
		String []mang156={"5","A","C","F","G","H","L","M","Q","Y"};

		for(int i=0; i<mang155.length;i++){
			for(int j=0; j<masp.length;j++)
				if(masp[j].substring(0, 1).toUpperCase().equals(mang155[i])){
					tkco1="155";
				}
		}

		for(int i=0; i<mang156.length;i++)
		{
			for(int j=0; j<masp.length;j++)
			{
				String sp=masp[j].substring(0, 1).toUpperCase();
				if(sp.equals(mang156[i])){
					tkco2="156";
				}
			}
		}

		if(tkco1.length()>0 && tkco2.length()>0)
		{
			tkco=tkco1+"/"+ tkco2;
		}

		if(tkco1.length()<=0 && tkco2.length()>0)
		{
			tkco=tkco2;
		}

		if(tkco1.length()>0 && tkco2.length()<=0)
		{
			tkco=tkco1;
		}

		if(tkco1.length() <=0 && tkco2.length() <=0)
			tkco="511/131/333";
		else
			tkco=	tkco+"/511/131/333";

		return tkco;

	}
	

	private String quydoi(String idsp, dbutils db, double soLUONG){
		
		NumberFormat formatter = new DecimalFormat("#,###,###.##");
		NumberFormat formatter1 = new DecimalFormat("#,###,###");
		//quy doi thung
		String qr_quydoi = " select soluong1 as soluong1, dvdl1_fk as dvdl1, soluong2 as soluong2, dvdl2_fk as dvdl2 "+
					" from quycach where  dvdl2_fk=100018 and sanpham_fk = '" + idsp + "'";
		String quydoi = "";
		System.out.print("\n -----------qr so thung quy doi:"+ qr_quydoi);
		ResultSet rs_quydoi = db.get(qr_quydoi);
	
		if (rs_quydoi != null) {
			System.out.print("\n -----------rs_quy doi khong null nhe:");
			try {
				while (rs_quydoi.next()) {
					System.out.print("\n -----------so luong tong :"+ soLUONG);
					double sl1 = rs_quydoi.getInt("soluong1"); // dvdl
					System.out.print("\n -----------qr so thung quy doi sl 1:"+ sl1);
					double sl2 = rs_quydoi.getInt("soluong2"); // dvdl
					System.out.print("\n -----------qr so thung quy doi sl2:"+ sl2);
					if (sl1 != 0) {
						quydoi = String.valueOf(((soLUONG * sl2)/ sl1));
						if(quydoi.contains(".")){
							String[] qd = quydoi.split("\\.");
							quydoi = qd[0] ;
						}
						quydoi=formatter1.format(Double.parseDouble(quydoi)) + "T";
						if ((soLUONG * sl2) % sl1 != 0) {
							quydoi += " "+ String.valueOf(formatter1.format((soLUONG * sl2)% sl1));
						}
						System.out.print("\n ----------- so thung quy doi:"+ quydoi);
					}
	
				}
				rs_quydoi.close();
			} catch (Exception e) {
				e.printStackTrace();
	
			}
		}
		return quydoi;
	}
	


}
