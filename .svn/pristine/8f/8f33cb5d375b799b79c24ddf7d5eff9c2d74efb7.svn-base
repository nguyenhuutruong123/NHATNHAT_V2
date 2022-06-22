package geso.dms.distributor.servlets.hoadontaichinh;

import geso.dms.center.beans.doctien.doctienrachu;
import geso.dms.distributor.beans.donhang.IDonhang;
import geso.dms.distributor.beans.donhang.ISanpham;
import geso.dms.distributor.beans.donhang.imp.Donhang;
import geso.dms.distributor.beans.donhang.imp.Sanpham;
import geso.dms.distributor.beans.hoadontaichinh.IHoadontaichinh;
import geso.dms.distributor.beans.hoadontaichinh.imp.Hoadontaichinh;
import geso.dms.distributor.beans.phieuxuatkho.IPhieuxuatkho;
import geso.dms.distributor.beans.phieuxuatkho.imp.Phieuxuatkho;
import geso.dms.distributor.beans.xuatkho.IErpYeucauxuatkhoNpp;
import geso.dms.distributor.beans.xuatkho.imp.ErpYeucauxuatkhoNpp;
import geso.dms.distributor.db.sql.dbutils;
import geso.dms.distributor.util.Utility;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Enumeration;
import java.util.Hashtable;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.itextpdf.text.BaseColor;
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

public class InPhieuxuatkhoPdf_Svl extends HttpServlet {

	private static final long serialVersionUID = 1L;

	public InPhieuxuatkhoPdf_Svl()
	{
		super();
	}

	float CONVERT = 28.346457f;  // =1cm
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
			id = util.antiSQLInspection(request.getParameter("id"));  // 

		String type = request.getParameter("type");
		System.out.println(" lay nguon in tu hoa don : "+type);

		String task = request.getParameter("task");    
		response.setContentType("application/pdf");
		response.setHeader("Content-Disposition", " inline; filename=PhieuXuatKho.pdf");
		Document document = new Document();
		ServletOutputStream outstream = response.getOutputStream();		
		
		// in tu hoa don tai chinh ==> chuyen ve id phieu xuat
		if (type.equals("HD_PXK"))
		{
			String qr = "select PXK_FK as maphieuxuat from PHIEUXUATKHO_DONHANG where hoadon_fk = " +id; 
			System.out.println("qr = "+ qr);
			ResultSet rsPXK = db.get(qr);
			if (rsPXK != null){
				try {
					while (rsPXK.next()){
					id=rsPXK.getString("maphieuxuat");
					}
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
     
		IPhieuxuatkho pxkBean =  new Phieuxuatkho(id);
		pxkBean.setUserId(userId); 
		pxkBean.init3();
		try
		{
		this.form_phieuxuat(document, outstream, pxkBean ,userId,db);
		pxkBean.DBclose();
		}
		catch (Exception e) {
			e.printStackTrace();
		}
	}

	private void form_phieuxuat(Document document,ServletOutputStream outstream,IPhieuxuatkho pxkBean,String userid, dbutils db) throws Exception
	{
		document.open();

		String Sodms = pxkBean.getId(); //pkxBean la phieu xuat kho, sodms la so pk_seq cua  hoa don, iddh
		String tk_no = "632/131";//tai khoan no co dinh
		String tk_co = "";
		String diengia = "";
		String khoxuat = "";
		String chietkhau = "";
		String giamgia = "giam gia";
		String sohoadon = "";
		String kihieu = "";
		String ngayxuatHD = ""; //lay ngay hien tai
		String tienvat = "";
		String tongtien_vat = "";
		String vat = "";
		String nguoitao = "";

		//don vi mua
		String npp_fk="";
		String khId="";
		String tenkh = "";
		String chucuahieu = "";
		String diachikh = "";
		//======================

		//ma hoa don san pham ban
		String mahoadon = "";
		String q = "\n select b.pk_seq as mahoadon "+    
		"\n from PHIEUXUATKHO_DONHANG a inner join hoadon b on a.hoadon_fk = b.PK_SEQ "+    
		"\n where b.LOAIHOADON = '0' and b.trangthai not in (3,5) and a.PXK_FK = "+pxkBean.getId();
		
		System.out.println( "\n ma hoa don: "+q + " \n");
		ResultSet rsMahoadon=db.get(q);
		if (rsMahoadon!=null){
			try {
				while (rsMahoadon.next()){
					mahoadon=rsMahoadon.getString("MAHOADON");
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		System.out.println("---ma hoa don1: " + mahoadon );

		//tim danh sach san pham  ban  
		String sqlIN_SANPHAM = "\n select MA, TEN, DONVI, DONGIA, SOLO, NGAYHETHAN, THUEVAT, sum( SOLUONG ) as soluong, CHIETKHAU " +
		"\n from HOADON_SP_CHITIET " +    
		"\n where hoadon_fk = " +    
		"\n ( " +
		"\n      select b.pk_seq as mahoadon " +    
		"\n      from PHIEUXUATKHO_DONHANG a  inner join hoadon b on a.hoadon_fk = b.PK_SEQ "+    
		"\n      where  b.LOAIHOADON='0' and b.trangthai not in (3,5) and a.PXK_FK = "+pxkBean.getId()+
		"\n ) "+
		" group by MA, TEN, DONVI, DONGIA, SOLO, NGAYHETHAN, THUEVAT, CHIETKHAU ";
		System.out.println("---IN SAN PHAM: " + sqlIN_SANPHAM );
		
		//danh sach san pham khuyen mai
		String sqlIN_SANPHAMKM = "\n select sp.MA as ma, sp.TEN as ten, dv.DONVI as donvi,km.DONGIA as dongia, km.SOLO as solo, km.NGAYHETHAN as NGAYHETHAN,sum( km.SOLUONG ) as soluong, '' as CHIETKHAU    "+    
		"\n from HOADON_CTKM_TRAKM_CHITIET km inner join sanpham sp on sp.pk_seq=km.sanpham_fk  inner join DONVIDOLUONG dv on dv.PK_SEQ=sp.DVDL_FK "+    
		"\n where hoadon_fk= "+    
		"\n ( " +
		"\n     select b.pk_seq as mahoadon "+    
		"\n     from PHIEUXUATKHO_DONHANG a  inner join hoadon b on a.hoadon_fk = b.PK_SEQ "+    
		"\n     where b.LOAIHOADON='1' and b.trangthai not in (3,5) and a.PXK_FK = "+pxkBean.getId()+
		"\n ) "+
		"\n group by sp.MA, sp.TEN, dv.DONVI, km.DONGIA, km.SOLO, km.NGAYHETHAN";
		System.out.println("--- \n IN SAN PHAMKM: " + sqlIN_SANPHAMKM +"\n" );

		//LAY THONG TIN KHACH HANG NHAN
		String sql = "\n select kh.pk_seq, kh.TEN as donvi,isnull(kh.chucuahieu,'') as chucuahieu, kh.diachi as diachi, " +
		"\n isnull(masothue,'') as masothue, '' as taikhoan "+    
		"\n from KHACHHANG kh " +
		"\n where kh.pk_seq = (select khachhang_fk from HOADON where pk_seq = "+mahoadon+") ";
		System.out.println("\n Lấy DON VI MUA HANG: "+sql +"\n");
		ResultSet rsLayKH = db.get(sql);
		if (rsLayKH != null){
			try {
				while (rsLayKH.next())
				{
					tenkh = rsLayKH.getString("DONVI");
					chucuahieu = rsLayKH.getString("chucuahieu");
					diachikh = rsLayKH.getString("DIACHI");
					System.out.println("\n Lấy dia chi DON VI MUA HANG: "+diachikh +"\n");
				}  
				rsLayKH.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		} 

		//LAY THONG TIN HOA DON ( SO HOA DON,NGAY XUAT HOA DON, GHI CHU)
		sql = "\n SELECT nguoimua, NGAYXUATHD, GHICHU, SOHOADON , GHICHU , KYHIEU, tongtienavat, vat, chietkhau " +
		"\n FROM HOADON WHERE PK_SEQ = "+mahoadon;
		System.out.println("\n Lay thong tin hoa don: "+sql);
		ResultSet rsLayhoadon = db.get(sql);
		if (rsLayhoadon.next())
		{
			diengia = rsLayhoadon.getString("GHICHU");
			ngayxuatHD = rsLayhoadon.getString("NGAYXUATHD");
			sohoadon = rsLayhoadon.getString("SOHOADON");
			tienvat = rsLayhoadon.getString("vat");
			tongtien_vat = rsLayhoadon.getString("tongtienavat");
			kihieu = rsLayhoadon.getString("KYHIEU");
			chietkhau = rsLayhoadon.getString("chietkhau");
			/*chucuahieu=rsLayhoadon.getString("nguoimua");*/
		}  
		rsLayhoadon.close();
		System.out.println(" chiet khau: "+ chietkhau);
		
		sql = "\n select sum(tonggiatri) chietkhau " +
		"\n from donhang_ctkm_trakm " +
		"\n where spma is null and donhangid in (select donhang_fk from phieuxuatkho_donhang where pxk_fk = "+pxkBean.getId()+")";
		ResultSet rs = db.get(sql);
		try {
			while (rs.next()) {
				chietkhau = rs.getString("chietkhau");
			}
			rs.close();
		} catch (SQLException e1) {
			e1.printStackTrace();
		}

		//lay top(1) vat %
		String qr_vat = "select top(1) vat as vat from HOADON_SP where hoadon_fk = '"+mahoadon+"'";
		ResultSet rs_vat = db.get(qr_vat);
		if (rs_vat != null){
			try {
				while (rs_vat.next())
				{
					vat=rs_vat.getString("vat") ;
				}
				rs_vat.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		List<ISanpham> spList = (List<ISanpham>) pxkBean.getPxk_spList();
		String []masp = new String [spList.size()];
		for(int i = 0; i < spList.size(); i ++)
		{
			Sanpham sp = (Sanpham)spList.get(i);
			masp[i]=sp.getMasanpham();
		}
		tk_co = taikhoanco(masp);

		//LAY SAN PHAM BAN
		ResultSet rsSP = db.get(sqlIN_SANPHAM);

		//LAY SAN PHAM KHUYEN MAI
		ResultSet rsSPKM = db.get(sqlIN_SANPHAMKM);
		List<ISanpham> spkmList = (List<ISanpham>) pxkBean.getPxk_spkmList();
		if (spkmList.size()>0){
			tk_no+="/521";
		}

		//lay kho xuat
		sql="select isnull(xuattaikho,'') as khoxuat from nhaphanphoi where pk_seq="+ pxkBean.getNppId();
		ResultSet rsKho=db.get(sql);
		if (rsKho!=null){
			try {
				while (rsKho.next()){
					khoxuat=rsKho.getString("khoxuat");
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}


		//lay noi xuat phieu O CHI NHANH 
		String qr_noiin="select npp.ten as ten, npp.DIACHI as diachi "+       
		"\n from NHANVIEN nv inner join nhaphanphoi npp on nv.CONVSITECODE=npp.SITECODE "+    
		"\n where nv.PHANLOAI = 1 and nv.PK_SEQ=" +userid;
		String tennoiin="";
		String diachiin="";
		System.out.println(" noi in :" +qr_noiin);
		ResultSet rsNoiin=db.get(qr_noiin);
		if (rsNoiin!=null){
			try {
				while (rsNoiin.next()){
					tennoiin=rsNoiin.getString("ten");
					diachiin=rsNoiin.getString("diachi");
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		// LAY SO CHUNG TU(  sonetId RESET THEO THANG)	
		String qr_sonet="select nh.sonetId as sonetid,nv.ten as ten from phieuxuatkho nh  \n "+
		" left join nhanvien nv on nv.PK_SEQ= nh.nguoisua \n  WHERE nh.PK_SEQ="+pxkBean.getId();
		String sonetid="";
		ResultSet rsSonet=db.get(qr_sonet);
		if (rsSonet!=null){
			try {
				while (rsSonet.next()){
					if (rsSonet.getString("sonetId")!=null){
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
		/*	para=new Paragraph("(BÁN HÀNG DƯỢC PHẨM)",font12_normal);
			para.setAlignment(Element.ALIGN_CENTER);
			cell.addElement(para);*/
			System.out.println("ngayxuatHD = "+ ngayxuatHD);
			String [] ngayHD = ngayxuatHD.split("-");
			if (ngayHD.length>2){
			para = new Paragraph("Ngày "+ ngayHD[2] + " Tháng " + ngayHD[1] +" Năm " + ngayHD[0],new Font(bf, 12, Font.BOLD));
			para.setAlignment(Element.ALIGN_CENTER);
			cell.addElement(para);	
			tableheader.addCell(cell);
			}
			document.add(tableheader);


			//----------------------ADD INFO KHACH HANG : bang hai cot -----------------------------

			PdfPTable tbl_khachhang =new PdfPTable(2);
			float kh_withd []={13.0f*CONVERT,4.0f*CONVERT};
			tbl_khachhang.setWidths(kh_withd);
			tbl_khachhang.setWidthPercentage(100);
			tbl_khachhang.getDefaultCell().setBorder(PdfPCell.NO_BORDER);

			//--dong0 ten nguoi mua hang (CHU CUA HIEU) + so dms
			cell = new PdfPCell();
			cell.setBorder(0);
			cell.setVerticalAlignment(Element.ALIGN_LEFT);
			para = new Paragraph("Họ và Tên: "+chucuahieu, font12_normal);
			cell.addElement(para);
			tbl_khachhang.addCell(cell);

			//so dms
			cell = new PdfPCell();
			/*cell.setBorder(0);*/
			cell.setBorder(Rectangle.NO_BORDER);
			cell.setVerticalAlignment(Element.ALIGN_BOTTOM);
			float withd_so []={0.7f*CONVERT,3.3f*CONVERT};
			PdfPTable tbl=new PdfPTable(2);
			tbl.getDefaultCell().setBorder(PdfPCell.NO_BORDER);
			tbl.setWidths(withd_so);
			tbl.setWidthPercentage(100);
			PdfPCell cellx=new PdfPCell( new Paragraph("Số: ", font12_normal));
			/*cellx.setBorder(0);*/
			cellx.setBorder(Rectangle.NO_BORDER);
			cellx.setVerticalAlignment(Element.ALIGN_MIDDLE);
			cellx.setPaddingTop(7);
			tbl.addCell(cellx);
			cellx=new PdfPCell( new Paragraph(sonetid, new Font(bf, 12, Font.BOLD)));
			//cellx.setBorder(0);
			cellx.setBorder(Rectangle.NO_BORDER);
			cellx.setVerticalAlignment(Element.ALIGN_MIDDLE);
			cellx.setPaddingTop(7);
			tbl.addCell(cellx);	
			cell.addElement(tbl);
			tbl_khachhang.addCell(cell);


			//ten dv mua hang
			cell = new PdfPCell();
			cell.setBorder(0);
			cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
			cell.setHorizontalAlignment(Element.ALIGN_LEFT);
			cell.setPaddingLeft(-1);
			float withd_so1 []={2.5f*CONVERT,11f*CONVERT};
			tbl=new PdfPTable(2);
			tbl.setWidths(withd_so1);
			tbl.getDefaultCell().setBorder(PdfPCell.NO_BORDER);
			tbl.setWidthPercentage(100);
			cellx=new PdfPCell( new Paragraph("Khách hàng: ", font12_normal));
			cellx.setBorder(0);
			cellx.setHorizontalAlignment(Element.ALIGN_LEFT);
			cellx.setVerticalAlignment(Element.ALIGN_MIDDLE);
			cellx.setPaddingTop(7);
			tbl.addCell(cellx);

			cellx=new PdfPCell( new Paragraph(tenkh, new Font(bf, 12, Font.BOLD)));
			cellx.setBorder(0);
			cellx.setVerticalAlignment(Element.ALIGN_MIDDLE);
			cellx.setHorizontalAlignment(Element.ALIGN_LEFT);
			cellx.setPaddingTop(7);
			tbl.addCell(cellx);	
			/*para = new Paragraph("Khách hàng: "+ tenkh , font12_normal);*/
			cell.addElement(tbl);
			tbl_khachhang.addCell(cell);


			//tk no
			cell = new PdfPCell();
			cell.setBorder(0);
			cell.setVerticalAlignment(Element.ALIGN_LEFT);
			para = new Paragraph("", font12_normal);
			cell.addElement(para);
			tbl_khachhang.addCell(cell);

			//dia chi
			cell = new PdfPCell();
			cell.setBorder(0);
			cell.setVerticalAlignment(Element.ALIGN_LEFT);
			para = new Paragraph("Địa chỉ: "+ diachikh , font12_normal);
			cell.addElement(para);
			tbl_khachhang.addCell(cell);


			//tk co
			cell = new PdfPCell();
			cell.setBorder(0);
			cell.setVerticalAlignment(Element.ALIGN_LEFT);
			para = new Paragraph("", font12_normal);
			cell.addElement(para);
			tbl_khachhang.addCell(cell);


			//--dong3 so hoa don + ngay xuat hoa don 	
			cell = new PdfPCell();
			cell.setBorder(0);
			cell.setPaddingLeft(-1);
			cell.setVerticalAlignment(Element.ALIGN_BOTTOM); //add bang 2 cot
			PdfPTable tbl_shd_ngay =new PdfPTable(2);
			tbl_shd_ngay.getDefaultCell().setBorder(PdfPCell.NO_BORDER);
			float withd_shd []={12.0f*CONVERT,8.0f*CONVERT};
			tbl_shd_ngay.setWidths(withd_shd);
			tbl_shd_ngay.setWidthPercentage(100);
			PdfPCell cell_shd = new PdfPCell(); //ad so hoa don
			cell_shd.setBorder(0);
			cell_shd.setVerticalAlignment(Element.ALIGN_LEFT);
			para = new Paragraph("Theo hóa đơn số: "+sohoadon.trim() +kihieu, font12_normal);
			cell_shd.addElement(para);
			tbl_shd_ngay.addCell(cell_shd);

			PdfPCell cell_ngay = new PdfPCell(); //ad so hoa don
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
			para = new Paragraph("Diễn giải: "+ diengia , font12_normal);
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
			float[] tbl_withd = { 7.0f, 60f, 10f , 15.0f, 15.0f, 25f, 20f, 7.0f, 15.0f };
			PdfPTable tbl_sanpham = new PdfPTable(tbl_withd.length);
			tbl_sanpham.setSpacingBefore(30.0f );
			tbl_sanpham.setWidthPercentage(100);
			tbl_sanpham.setHorizontalAlignment(Element.ALIGN_LEFT);
			tbl_sanpham.setWidths(tbl_withd);
			PdfPCell cells;
			double tongtien = 0;
			//add tieu de
			String[] tieude = new String[] { "Stt","Tên hàng","ĐVT","Đơn giá","Số lượng","Thành tiền","Số lô","CK","Ghi chú"};
			for (int j = 0; j < tbl_withd.length; j++)
			{
				cells = new PdfPCell(new Paragraph(tieude[j], new Font(bf, 10, Font.BOLD)));
				cells.setHorizontalAlignment(Element.ALIGN_CENTER);
				cells.setPaddingBottom(7);
				tbl_sanpham.addCell(cells);	
			}

			//==============

			int stt = 1;
			try {
				//SAN PHAM BAN
				while (rsSP.next())
				{
					double soLUONG = rsSP.getDouble("soluong");
					double dongia = rsSP.getDouble("dongia");
					double thanhtien = Math.floor(soLUONG*dongia);
					String quydoi=quydoi(rsSP.getString("MA"), db, soLUONG);
					tongtien +=thanhtien;
									
					String solo=rsSP.getString("solo").trim();
					if (solo.equals("NA")||solo.equals("na")){
						solo="";
					}
					
					//bang
					String[] arr = new String[] { Integer.toString(stt),rsSP.getString("TEN"),
							rsSP.getString("DONVI"),
							DinhDangTraphacoDMS(formatter.format(dongia)),
							DinhDangTraphacoDMS(formatter1.format(soLUONG)),
							DinhDangTraphacoDMS(formatter1.format(thanhtien)),
							solo, rsSP.getString("CHIETKHAU"),quydoi};


					for (int j = 0; j < tbl_withd.length; j++)
					{
						cells = new PdfPCell(new Paragraph(arr[j], new Font(bf, 10, Font.NORMAL)));
						cells.setPaddingBottom(7);
						if (j==1 ||j==6){
							cells.setHorizontalAlignment(Element.ALIGN_LEFT);
						}

						else
							if (j==4 ||j==3||j==5){
								cells.setHorizontalAlignment(Element.ALIGN_RIGHT);
							}
							else
							{
								cells.setHorizontalAlignment(Element.ALIGN_CENTER);
							}
						tbl_sanpham.addCell(cells);	
					}
					stt++;
				}
				rsSP.close();



				//SAN PHAM KHUYEN MAI
				while (rsSPKM.next())	
				{
					System.out.println(" da vao sp km ");
					double soLUONG = rsSPKM.getDouble("soluong");
					double dongia = 0;
					double thanhtien = Math.floor(soLUONG*dongia);
					String quydoi=quydoi(rsSPKM.getString("MA"), db, soLUONG);
					tongtien +=thanhtien;
					//bang
					
					String solo=rsSPKM.getString("solo").trim();
					if (solo.equals("NA")||solo.equals("na")){
						solo="";
					}
					
					String[] arr = new String[] { Integer.toString(stt),rsSPKM.getString("TEN"),
							rsSPKM.getString("DONVI"),
							DinhDangTraphacoDMS(formatter.format(dongia)),
							DinhDangTraphacoDMS(formatter1.format(soLUONG)),
							DinhDangTraphacoDMS(formatter1.format(thanhtien)),
							solo, rsSPKM.getString("CHIETKHAU"),quydoi};

					for (int j = 0; j < tbl_withd.length; j++)
					{
						cells = new PdfPCell(new Paragraph(arr[j], new Font(bf, 10, Font.NORMAL)));
						cells.setPaddingBottom(7);
						if (j==1 ||j==6){
							cells.setHorizontalAlignment(Element.ALIGN_LEFT);
						}

						else
							if (j==4 ||j==3||j==5){
								cells.setHorizontalAlignment(Element.ALIGN_RIGHT);
							}
							else
							{
								cells.setHorizontalAlignment(Element.ALIGN_CENTER);
							}
						tbl_sanpham.addCell(cells);	
					}
					stt++;
				}
				rsSPKM.close();

			} catch (Exception e) {
				e.printStackTrace();
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
			para = new Paragraph( DinhDangTraphacoDMS(formatter.format(geso.dms.center.util.Utility.parseDouble(chietkhau)))+" đồng" , font12_normal);
			System.out.println(" \n xem chiet khau: "+chietkhau);
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
			para = new Paragraph( DinhDangTraphacoDMS (formatter.format(Double.parseDouble(tienvat)))  +" đồng" , font12_normal);
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


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		this.doGet(request, response);
	}

	private String DinhDangTraphacoDMS(String sotien)
	{
		sotien = sotien.replaceAll("\\.", "_");
		sotien = sotien.replaceAll(",", "\\.");
		sotien = sotien.replaceAll("_", ",");

		return sotien;
	}

	private String quydoi(String masp, dbutils db, double soLUONG)
	{
		NumberFormat formatter = new DecimalFormat("#,###,###.##");
		NumberFormat formatter1 = new DecimalFormat("#,###,###");
		// quy doi thung 100019
		String qr_quydoi=" select sp.pk_seq, qc.soluong1  , qc.soluong2  "+    
		"	from SANPHAM sp inner join QUYCACH qc  "+    
		" 	on sp.DVDL_FK = qc.DVDL1_FK and qc.DVDL2_FK = 100019 and qc.SANPHAM_FK = sp.PK_SEQ  "+    
		"	where MA='"+  masp +"'"; 
		String  quydoi="";
		System.out.print("\n -----------qr so thung quy doi:"+qr_quydoi);
		ResultSet rs_quydoi=db.get(qr_quydoi);

		if (rs_quydoi!=null){
			try {
				while (rs_quydoi.next())
				{
					System.out.print("\n -----------so luong tong :"+soLUONG);

					int sl1= rs_quydoi.getInt("soluong1") ; //dvdl cua sp
					System.out.print("\n -----------qr so thung quy doi sl 1:"+sl1);

					int sl2= rs_quydoi.getInt("soluong2") ; // dvdl cua thung
					System.out.print("\n -----------qr so thung quy doi sl2:"+sl2);
					if (sl1!=0)
					{
						quydoi = String.valueOf(((soLUONG * sl2)/ sl1));
						if (quydoi.contains(".")){
							String[] qd = quydoi.split("\\.");
							quydoi = qd[0] ;
						}
						quydoi=formatter1.format(Double.parseDouble(quydoi)) + "T";
						if ((soLUONG*sl2)%sl1 !=0){
							quydoi += " " + String.valueOf(formatter1.format((soLUONG*sl2)%sl1));
						}
						System.out.print("\n ----------- so thung quy doi:"+quydoi);
					}

				}
				rs_quydoi.close();	
			} catch (Exception e) {
				// TODO: handle exception

			}
		}
		return quydoi;
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
				if (masp[j].substring(0, 1).toUpperCase().equals(mang155[i])){
					tkco1="155";
				}
		}

		for(int i=0; i<mang156.length;i++)
		{
			for(int j=0; j<masp.length;j++)
			{
				String sp=masp[j].substring(0, 1).toUpperCase();
				if (sp.equals(mang156[i])){
					tkco2="156";
				}
			}
		}

		if (tkco1.length()>0 && tkco2.length()>0)
		{
			tkco=tkco1+"/"+ tkco2;
		}

		if (tkco1.length()<=0 && tkco2.length()>0)
		{
			tkco=tkco2;
		}

		if (tkco1.length()>0 && tkco2.length()<=0)
		{
			tkco=tkco1;
		}

		if (tkco1.length() <=0 && tkco2.length() <=0)
			tkco="511/131/333";
		else
			tkco=	tkco+"/511/131/333";

		return tkco;

	}



}
