package geso.dms.center.servlets.dieuchinhtonkho;

import geso.dms.center.beans.dieuchinhtonkho.IErpChuyenkhoTT;
import geso.dms.center.beans.dieuchinhtonkho.imp.ErpChuyenkhoTT;
import geso.dms.distributor.db.sql.dbutils;
import geso.dms.distributor.util.Utility;

import java.io.IOException;
import java.sql.ResultSet;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Hashtable;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import oracle.jdbc.driver.DMSFactory;

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

public class ErpInphieudieukhoSvl extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public ErpInphieudieukhoSvl() {
		super();
	}

	float CONVERT = 28.346457f; // = 1cm

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		dbutils db = new dbutils();
		HttpSession session = request.getSession();
		String userId = (String) session.getAttribute("userId");
		// String userTen = (String) session.getAttribute("userTen");

		Utility util = new Utility();
		if (userId.length() == 0)
			userId = request.getParameter("userId");

		String querystring = request.getQueryString();

		String id = util.getId(querystring);
		if (id.length() == 0)
			id = util.antiSQLInspection(request.getParameter("id"));

		// LAY THONG TIN
		IErpChuyenkhoTT lsxBean = new ErpChuyenkhoTT(id);
		String loaidonhang = request.getParameter("loaidonhang");
		if (loaidonhang == null)
			loaidonhang = "0";
		lsxBean.setLoaidonhang(loaidonhang);
		System.out.println("---LOAI DON HANG: " + loaidonhang);
		lsxBean.setUserId(userId);
		lsxBean.init();


		// ===========
		String task = request.getParameter("task");
		/*
		 * if (querystring.indexOf("pdf") > 0) {
		 */

		response.setContentType("application/pdf");
		response.setHeader("Content-Disposition",
		" inline; filename=PhieuDieuKho.pdf");

		Document document = new Document();
		ServletOutputStream outstream = response.getOutputStream();

		//this.form_phieuxuat(document, outstream, lsxBean, db);
		this.form_phieuxuatnguyen(document, outstream, lsxBean, db);
		
		/* } */

	}

	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		this.doGet(request, response);
	}

	
	//mau in thung
	private void form_phieuxuat(Document document,
			ServletOutputStream outstream, IErpChuyenkhoTT pxkBean, dbutils db) {
		document.open();

		// ====LAY THONG TIN ve lenh chuyen noi bo
		String lenhdieudong = pxkBean.getLenhdieudong(); // pkxBean la phieu// chuyen kho HO,
		//String ngaydieudong = pxkBean.getNgaydieudong();
		String ngaydieudong = pxkBean.getNgayyeucau();
		String lddCua = pxkBean.getLDDcua();
		String veviec = pxkBean.getLDDveviec();
		String nguoivanchuyen = pxkBean.getNguoivanchuyen();
		String sohopdong = pxkBean.getSoChungTu(); //laays so chung tu
		String phuongtienvanchuyen = pxkBean.getPtvanchuyen();
		String khoxuatid = pxkBean.getKhoXuatId();
		String khonhapid = pxkBean.getKhoNhapId();
		String sodms = pxkBean.getId(); // pk_seq cua
		String noinhan = "";
		String noidung = "Xuất điều chỉnh kho";
		String tkno = ""; // lay giong tk co
		String tkco = "";
		String khoxuat = "";
		String khonhap = "";
		String nguoitao="";

		// tim kho xuat
		ResultSet khoxuatRs = pxkBean.getKhoXuatRs();
		if (khoxuatRs != null) {
			try {
				while (khoxuatRs.next()) {
					if (khoxuatRs.getString("pk_seq").equals(khoxuatid)) {
						khoxuat = khoxuatRs.getString("ten");
					}
				}
			} catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
			}
		}

		// tim ben nhan: lay tu nnp

		String npp_id = pxkBean.getNppId();
		String qr_npp = "select isnull(XUATTAIKHO,'') as xuattaikho, ten as ten from nhaphanphoi where pk_seq="
			+ npp_id;
		ResultSet rsKhoxuat = db.get(qr_npp);
		if (rsKhoxuat != null) {
			try {
				while (rsKhoxuat.next()) {
					noinhan = rsKhoxuat.getString("ten");
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		// lay kho nhan, lay tu donhang_fk cua e r p chuyen kho

		String qr_khonhan = " select isnull(kho.ten,'') as tenkho "
			+ " from nhaphang nh inner join NHAPHANG_DDH nh_dh on nh.PK_SEQ =nh_dh.nhaphang_fk "
			+ " inner join kho kho on kho.pk_seq=nh.kho_fk "
			+ " where nh_dh.ddh_fk=(select ddh_fk from erp_chuyenkho where pk_seq="
			+ pxkBean.getId() + ")";
		ResultSet rskhonhan = db.get(qr_khonhan);
		if (rskhonhan != null) {
			try {
				while (rskhonhan.next()) {
					// khonhap=rsKhoxuat.getString("xuattaikho");
					khonhap = rskhonhan.getString("tenkho");
				}
			} catch (Exception e) {
				// TODO: handle exception

				e.printStackTrace();
			}
		}


		// == thong tin danh sach san pham
		String[] spId = pxkBean.getSpId();
		String[] spMa = pxkBean.getSpMa();
		String[] spTen = pxkBean.getSpTen();
		String[] spDonvi = pxkBean.getSpDonvi();
		String[] spSoluong = pxkBean.getSpSoluong();
		String[] spGianhap = pxkBean.getSpGianhap();

		Hashtable<String, String> sanpham_soluong = pxkBean.getSanpham_Soluong();

		//===== tk co 
		tkco=taikhoanco(spMa);
		tkno=tkco;

		// =====================do du lieu
		try {
			// format
			NumberFormat formatter = new DecimalFormat("#,###,###.##");
			NumberFormat formatter1 = new DecimalFormat("#,###,###");

			document.setPageSize(PageSize.A4);
			document.setMargins(0.5f * CONVERT, 0.5f * CONVERT, 0.5f * CONVERT,
					0.5f * CONVERT); // L,R,T,B
			PdfWriter writer = PdfWriter.getInstance(document, outstream);

			PdfPCell cell;
			Paragraph para;
			Chunk chunk;
			document.open();

			BaseFont bf = BaseFont.createFont("c:\\windows\\fonts\\times.ttf",
					BaseFont.IDENTITY_H, BaseFont.EMBEDDED);
			Font font = new Font(bf, 13, Font.BOLD);
			Font font2 = new Font(bf, 8, Font.BOLD);
			Font font3 = new Font(bf, 10, Font.BOLD);

			PdfPTable tableheader = new PdfPTable(1);
			tableheader.setWidthPercentage(100);

			cell = new PdfPCell();
			cell.setBorder(0);
			cell.setPaddingTop(1.5f * CONVERT);
			cell.setPaddingLeft(4f * CONVERT);
			cell.setVerticalAlignment(Element.ALIGN_MIDDLE);

			// NGAY THANG NAM
			if (ngaydieudong.length() >0) {


				String[] ngayHD = ngaydieudong.split("-");
				Paragraph pxk = new Paragraph("", new Font(bf, 8, Font.BOLDITALIC));
				chunk = new Chunk(ngayHD[2] + "/" + ngayHD[1] + "/" + ngayHD[0],
						new Font(bf, 8, Font.BOLDITALIC));
				pxk.add(chunk);
				pxk.setAlignment(Element.ALIGN_CENTER);
				pxk.setSpacingAfter(2);
				cell.addElement(pxk);

				tableheader.addCell(cell);
				document.add(tableheader);

			}
			// ============================= thong tin phieu chuyen kho noi bo
			// ----------------------ADD INFO KHACH HANG : bang 1 cot
			PdfPTable tbl_khachhang = new PdfPTable(2);
			float kh_withd[] = { 13.0f * CONVERT, 2.0f * CONVERT };
			tbl_khachhang.setWidths(kh_withd);
			tbl_khachhang.setWidthPercentage(100);

			// --dong 1 noi nhan
			cell = new PdfPCell();
			cell.setBorder(0);
			cell.setVerticalAlignment(Element.ALIGN_LEFT);
			cell.setPaddingLeft(3.0f * CONVERT);
			para = new Paragraph(noinhan, new Font(bf, 10, Font.BOLD));
			cell.addElement(para);
			tbl_khachhang.addCell(cell);

			// --dong 1 dms
			cell = new PdfPCell();
			cell.setBorder(0);
			cell.setVerticalAlignment(Element.ALIGN_LEFT);
			cell.setPaddingLeft(0.0f * CONVERT);
			para = new Paragraph(sodms, new Font(bf, 10, Font.BOLD));
			cell.addElement(para);
			tbl_khachhang.addCell(cell);

			// --dong2 noi dung
			cell = new PdfPCell();
			cell.setBorder(0);
			cell.setVerticalAlignment(Element.ALIGN_LEFT);
			cell.setPaddingLeft(3.0f * CONVERT);
			para = new Paragraph(noidung, new Font(bf, 10, Font.BOLD));
			cell.addElement(para);
			tbl_khachhang.addCell(cell);

			// --dong2 so hop dong
			cell = new PdfPCell();
			cell.setBorder(0);
			cell.setVerticalAlignment(Element.ALIGN_LEFT);
			cell.setPaddingLeft(0.0f * CONVERT);
			para = new Paragraph(sohopdong, new Font(bf, 10, Font.BOLD));
			cell.addElement(para);
			tbl_khachhang.addCell(cell);

			// --dong3 xuat tai kho
			cell = new PdfPCell();
			cell.setBorder(0);
			cell.setVerticalAlignment(Element.ALIGN_LEFT);
			cell.setPaddingLeft(4.0f * CONVERT);
			para = new Paragraph(khoxuat, new Font(bf, 10, Font.BOLD));
			cell.addElement(para);
			tbl_khachhang.addCell(cell);

			// --dong3 tk no
			cell = new PdfPCell();
			cell.setBorder(0);
			cell.setVerticalAlignment(Element.ALIGN_LEFT);
			cell.setPaddingLeft(0.0f * CONVERT);
			para = new Paragraph(tkno, new Font(bf, 10, Font.BOLD));
			cell.addElement(para);
			tbl_khachhang.addCell(cell);

			// --dong4 nhap tai kho
			cell = new PdfPCell();
			cell.setBorder(0);
			cell.setVerticalAlignment(Element.ALIGN_LEFT);
			cell.setPaddingLeft(4.0f * CONVERT);
			para = new Paragraph(khonhap, new Font(bf, 10, Font.BOLD));
			cell.addElement(para);
			tbl_khachhang.addCell(cell);

			// --dong4 tk co
			cell = new PdfPCell();
			cell.setBorder(0);
			cell.setVerticalAlignment(Element.ALIGN_LEFT);
			cell.setPaddingLeft(0.0f * CONVERT);
			para = new Paragraph(tkco, new Font(bf, 10, Font.BOLD));
			cell.addElement(para);
			tbl_khachhang.addCell(cell);

			// ========ad bang lon
			document.add(tbl_khachhang);

			// Table Content---------------------------------bang du
			
			
			
			

			float[] tbl_withd = { 7.0f, 60f, 15.0f, 20.0f, 10f, 25.0f, 20.0f,15.0f };

			PdfPTable tbl_sanpham = new PdfPTable(tbl_withd.length);
			tbl_sanpham.setSpacingBefore(30.0f);
			tbl_sanpham.setWidthPercentage(100);
			tbl_sanpham.setHorizontalAlignment(Element.ALIGN_LEFT);
			tbl_sanpham.setWidths(tbl_withd);
			PdfPCell cells;

			double tongtien = 0;

			int stt = 1;
			
			
			if(spMa.length>0){
				for (int x = 0; x < spMa.length; x++) {
					
					String tenhang=spTen[x];
					String dvtinh=spDonvi[x];
					String  idsp=spId[x];
					String  masp=spMa[x];

					/*String qrsp="select sp.PK_SEQ as pk_seq, sp.MA as ma, sp.TEN as ten, a.solo as solo, a.soluong as soluong , "+
					" (select isnull(dongia,0) as dongia from ERP_CHUYENKHO_SANPHAM where chuyenkho_fk="+pxkBean.getId()+"  and sanpham_fk =sp.PK_SEQ )  as dongia,  "+    
					"(select donvi from DONVIDOLUONG where PK_SEQ = sp.DVDL_FK) as donvi, a.ngayhethan as handung  "+    
					"from ERP_CHUYENKHO_SANPHAM_CHITIET  "+    
					"a inner join sanpham sp on a.sanpham_fk=sp.PK_SEQ  "+    
					"where a.chuyenkho_fk = "+pxkBean.getId();*/
					
					String qrsp=" select sp.PK_SEQ as pk_seq, sp.MA as ma, sp.TEN as ten, a.solo as solo, a.soluong as soluong    \n"+    
					 "   from ERP_CHUYENKHO_SANPHAM_CHITIET    \n"+    
					 "   a inner join sanpham sp on a.sanpham_fk=sp.PK_SEQ    \n"+    
					 "   where a.chuyenkho_fk ="+pxkBean.getId() +" and sp.PK_SEQ="+ idsp;
					
					System.out.println (" qr san pham : \n "+qrsp);
					ResultSet rsSP=db.get(qrsp);
					if(rsSP != null)
					{ 
						try {
							while(rsSP.next())
							{
								/*String tenhang=rsSP.getString("ten");
								String dvtinh=rsSP.getString("donvi");
								String  idsp=rsSP.getString("pk_seq");
								double soLUONG =rsSP.getDouble("soluong");
								String  masp=rsSP.getString("ma");
								String solo=rsSP.getString("solo");*/
								
								double soLUONG =rsSP.getDouble("soluong");
								String solo=rsSP.getString("solo");
								
								/*double dongia =rsSP.getDouble("dongia");*/
								double dongia =Double.parseDouble(spGianhap[x].replaceAll(",", ""));
								double thanhtien = soLUONG*dongia;
								tongtien +=thanhtien;

								String quydoi=quydoi(idsp, db, soLUONG);

								// do du lieu
								String[] arr = new String[] {stt + "",tenhang,dvtinh,
										DinhDangTraphacoDMS(formatter.format(dongia)),
										DinhDangTraphacoDMS(formatter1.format(soLUONG)),
										DinhDangTraphacoDMS(formatter1.format(thanhtien)), solo,quydoi };
								for (int j = 0; j < tbl_withd.length; j++) {
									cells = new PdfPCell(new Paragraph(arr[j],
											new Font(bf, 10, Font.BOLD)));
									// canh format
									if (j == 1) {
										cells.setHorizontalAlignment(Element.ALIGN_LEFT);
									} else if (j == 0 || j == 2) {
										cells.setHorizontalAlignment(Element.ALIGN_CENTER);

									} else {
										cells.setHorizontalAlignment(Element.ALIGN_RIGHT);
									}

									cells.setVerticalAlignment(Element.ALIGN_BOTTOM);
									cells.setBorder(0);

									tbl_sanpham.addCell(cells);
								}
								stt++;
							}
						} catch (Exception e) {
							e.printStackTrace();
						}

					}	
				}
				

				
			}
			
			
			
			

			String[] arr = new String[] { "", "", "", "", "",DinhDangTraphacoDMS(formatter1.format(tongtien)), "","" };
			// add tong tien
			for (int j = 0; j < tbl_withd.length; j++) {
				cells = new PdfPCell(new Paragraph("", new Font(bf, 10,Font.BOLD)));
				cells.setPaddingTop(5.0f);
				cells.setVerticalAlignment(Element.ALIGN_BOTTOM);
				cells.setHorizontalAlignment(Element.ALIGN_RIGHT);
				cells.setBorder(0);
				tbl_sanpham.addCell(cells);
			}
			for (int j = 0; j < tbl_withd.length; j++) {
				cells = new PdfPCell(new Paragraph(arr[j], new Font(bf, 10,Font.BOLD)));
				cells.setPaddingTop(5.0f);
				cells.setVerticalAlignment(Element.ALIGN_BOTTOM);
				cells.setHorizontalAlignment(Element.ALIGN_RIGHT);
				cells.setBorder(0);
				tbl_sanpham.addCell(cells);
			}

			document.add(tbl_sanpham);


			document.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

    //mau in nguyen bang
	private void form_phieuxuatnguyen(Document document,ServletOutputStream outstream, IErpChuyenkhoTT pxkBean, dbutils db)
	{
		// ====LAY THONG TIN ve lenh chuyen noi bo
		String lenhdieudong = pxkBean.getLenhdieudong(); // pkxBean la phieu// chuyen kho HO,
		//String ngaydieudong = pxkBean.getNgaydieudong();
		String ngaydieudong = pxkBean.getNgayyeucau();
		String lddCua = pxkBean.getLDDcua();
		String veviec = pxkBean.getLDDveviec();
		String nguoivanchuyen = pxkBean.getNguoivanchuyen();
		String sohopdong = pxkBean.getSoChungTu(); //laays so chung tu
		String phuongtienvanchuyen = pxkBean.getPtvanchuyen();
		String khoxuatid = pxkBean.getKhoXuatId();
		String khonhapid = pxkBean.getKhoNhapId();
		String sodms = pxkBean.getId(); // pk_seq cua
		String noinhan = "";
		String noidung = "Xuất điều chỉnh kho";
		String tkno = ""; // lay giong tk co
		String tkco = "";
		String khoxuat = "";
		String khonhap = "";
		String nguoitao="";

		// tim kho xuat
		ResultSet khoxuatRs = pxkBean.getKhoXuatRs();
		if (khoxuatRs != null) {
			try {
				while (khoxuatRs.next()) {
					if (khoxuatRs.getString("pk_seq").equals(khoxuatid)) {
						khoxuat = khoxuatRs.getString("ten");
					}
				}
			} catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
			}
		}

		// tim ben nhan: lay tu nnp

		String npp_id = pxkBean.getNppId();
		String qr_npp = "select isnull(XUATTAIKHO,'') as xuattaikho, ten as ten from nhaphanphoi where pk_seq="
			+ npp_id;
		ResultSet rsKhoxuat = db.get(qr_npp);
		if (rsKhoxuat != null) {
			try {
				while (rsKhoxuat.next()) {
					noinhan = rsKhoxuat.getString("ten");
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		// lay kho nhan, lay tu donhang_fk cua e r p chuyen kho

		String qr_khonhan = " select isnull(kho.ten,'') as tenkho "
			+ " from nhaphang nh inner join NHAPHANG_DDH nh_dh on nh.PK_SEQ =nh_dh.nhaphang_fk "
			+ " inner join kho kho on kho.pk_seq=nh.kho_fk "
			+ " where nh_dh.ddh_fk=(select ddh_fk from erp_chuyenkho where pk_seq="
			+ pxkBean.getId() + ")";
		ResultSet rskhonhan = db.get(qr_khonhan);
		if (rskhonhan != null) {
			try {
				while (rskhonhan.next()) {
					// khonhap=rsKhoxuat.getString("xuattaikho");
					khonhap = rskhonhan.getString("tenkho");
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
		
		// LAY SO CHUNG TU(  sonetId RESET THEO THANG)
		
		
		String qr_sonet="select nh.sonetId as sonetid,nv.ten as ten from ERP_CHUYENKHO nh \n "+
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


		// == thong tin danh sach san pham
		String[] spId = pxkBean.getSpId();
		String[] spMa = pxkBean.getSpMa();
		String[] spTen = pxkBean.getSpTen();
		String[] spDonvi = pxkBean.getSpDonvi();
		String[] spSoluong = pxkBean.getSpSoluong();
		String[] spGianhap = pxkBean.getSpGianhap();

		Hashtable<String, String> sanpham_soluong = pxkBean.getSanpham_Soluong();

		//===== tk co 
		tkco=taikhoanco(spMa);
		tkno=tkco;

		
		
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
		
			Font font10_bold = new Font(bf, 10, Font.BOLD);
			Font font10_normal = new Font(bf, 10, Font.NORMAL);
			Font font12_bold = new Font(bf, 12, Font.BOLD);
			Font font12_normal = new Font(bf, 12, Font.NORMAL);
			
			// them header1
			PdfPTable tableheader =new PdfPTable(1);
			tableheader.setWidthPercentage(100);	
			//dia chi dcl
			cell = new PdfPCell();
			cell.setBorder(0);
			cell.setHorizontalAlignment(Element.ALIGN_LEFT);
			
			para=new Paragraph("CÔNG TY CỔ PHẦN DƯỢC PHẨM CỬU LONG",new Font(bf, 9, Font.BOLD));
			para.setAlignment(Element.ALIGN_LEFT);
			cell.addElement(para);
			
			para=new Paragraph("150 Đường 14/9, Phường 5, TP Vĩnh Long, Tỉnh Vĩnh Long",new Font(bf, 9, Font.BOLD));
			para.setAlignment(Element.ALIGN_LEFT);
			cell.addElement(para);
		
			tableheader.addCell(cell);
			
			//add tua de
			cell = new PdfPCell();
			cell.setBorder(0);
			cell.setHorizontalAlignment(Element.ALIGN_CENTER);
			para=new Paragraph("PHIẾU ĐIỀU KHO",new Font(bf, 16, Font.BOLD));
			para.setAlignment(Element.ALIGN_CENTER);
			cell.addElement(para);
			tableheader.addCell(cell);
			
			
			//NGAY THANG NAM
			cell = new PdfPCell();
			cell.setBorder(0);
			cell.setHorizontalAlignment(Element.ALIGN_CENTER);
			String [] ngayHD = ngaydieudong.split("-");
			para = new Paragraph("Ngày: "+ ngayHD[2] + "/" + ngayHD[1] +"/" + ngayHD[0],font10_bold);
			para.setAlignment(Element.ALIGN_CENTER);
			cell.addElement(para);
			cell.setPaddingBottom(10);
			tableheader.addCell(cell);
			
			document.add(tableheader);
			
			
			
			//----------------------ADD INFO KHACH HANG : bang 3 cot -----------------------------\

			PdfPTable tbl_khachhang =new PdfPTable(3);
			float kh_withd []={16.0f, 3.0f,3.0f};
			tbl_khachhang.setWidths(kh_withd);
			tbl_khachhang.setWidthPercentage(100);
			tbl_khachhang.getDefaultCell().setBorder(PdfPCell.NO_BORDER);

			//--dong 1 noi nhan + so ct 
			cell = new PdfPCell();
			cell.setBorder(0);
			cell.setPaddingLeft(0.5f*CONVERT);
			cell.setVerticalAlignment(Element.ALIGN_LEFT);
			para = new Paragraph();
			para.add(new Chunk("Nơi nhận: ", font10_normal));
			para.add(new Chunk(noinhan, font10_bold));
			cell.addElement(para);
			tbl_khachhang.addCell(cell);
			
			cell = new PdfPCell();
			cell.setBorder(0);
			cell.setVerticalAlignment(Element.ALIGN_RIGHT);
			para = new Paragraph("Số CT: ", font10_bold);
			para.setAlignment(Element.ALIGN_RIGHT);
			cell.addElement(para);
			tbl_khachhang.addCell(cell);
			
			cell = new PdfPCell();
			cell.setBorder(0);
			cell.setVerticalAlignment(Element.ALIGN_LEFT);
			para = new Paragraph(sonetid, font10_bold);
			para.setAlignment(Element.ALIGN_LEFT);
			cell.addElement(para);
			tbl_khachhang.addCell(cell);
			
			
			// dong 2
			cell = new PdfPCell();
			cell.setBorder(0);
			cell.setPaddingLeft(0.5f*CONVERT);
			cell.setVerticalAlignment(Element.ALIGN_LEFT);
			para = new Paragraph();
			para.add(new Chunk("Nội dung: ", font10_normal));
			para.add(new Chunk(noidung, font10_bold));
			cell.addElement(para);
			tbl_khachhang.addCell(cell);
			
			cell = new PdfPCell();
			cell.setBorder(0);
			cell.setVerticalAlignment(Element.ALIGN_RIGHT);
			para = new Paragraph("Số HĐ: ", font10_bold);
			para.setAlignment(Element.ALIGN_RIGHT);
			cell.addElement(para);
			tbl_khachhang.addCell(cell);
			
			cell = new PdfPCell();
			cell.setBorder(0);
			cell.setVerticalAlignment(Element.ALIGN_LEFT);
			para = new Paragraph(sohopdong, font10_bold);
			para.setAlignment(Element.ALIGN_LEFT);
			cell.addElement(para);
			tbl_khachhang.addCell(cell);
			
			// dong 3
			cell = new PdfPCell();
			cell.setBorder(0);
			cell.setPaddingLeft(0.5f*CONVERT);
			cell.setVerticalAlignment(Element.ALIGN_LEFT);
			para = new Paragraph();
			para.add(new Chunk("Xuất tại kho: ", font10_normal));
			para.add(new Chunk(khoxuat, font10_bold));
			cell.addElement(para);
			tbl_khachhang.addCell(cell);
			
			cell = new PdfPCell();
			cell.setBorder(0);
			cell.setVerticalAlignment(Element.ALIGN_RIGHT);
			para = new Paragraph("Nợ: ", font10_bold);
			para.setAlignment(Element.ALIGN_RIGHT);
			cell.addElement(para);
			tbl_khachhang.addCell(cell);
			
			cell = new PdfPCell();
			cell.setBorder(0);
			cell.setVerticalAlignment(Element.ALIGN_LEFT);
			para = new Paragraph(tkno, font10_bold);
			para.setAlignment(Element.ALIGN_LEFT);
			cell.addElement(para);
			tbl_khachhang.addCell(cell);
		
			
			//dong 4
			cell = new PdfPCell();
			cell.setBorder(0);
			cell.setPaddingLeft(0.5f*CONVERT);
			cell.setVerticalAlignment(Element.ALIGN_LEFT);
			para = new Paragraph();
			para.add(new Chunk("Nhập tại kho: ", font10_normal));
			para.add(new Chunk(khonhap, font10_bold));
			cell.addElement(para);
			tbl_khachhang.addCell(cell);
			
			cell = new PdfPCell();
			cell.setBorder(0);
			cell.setVerticalAlignment(Element.ALIGN_RIGHT);
			para = new Paragraph("Có: ", font10_bold);
			para.setAlignment(Element.ALIGN_RIGHT);
			cell.addElement(para);
			tbl_khachhang.addCell(cell);
			
			cell = new PdfPCell();
			cell.setBorder(0);
			cell.setVerticalAlignment(Element.ALIGN_LEFT);
			para = new Paragraph(tkco, font10_bold);
			para.setAlignment(Element.ALIGN_LEFT);
			cell.addElement(para);
			tbl_khachhang.addCell(cell);
			
			document.add(tbl_khachhang);

			
			// Table Content---------------------------------bang du lieu----------------------------------
			
			
			/*String qrsp="select sp.PK_SEQ as pk_seq, sp.MA as ma, sp.TEN as ten, a.solo as solo, a.soluong as soluong , "+
			"\n isnull( (select [dbo].[GiaCkBanLeNppSanPham]("+ 1+",null,sp.pk_seq,'"+ ngaydieudong +"' )) ,0) as dongia, " +
			 "\n (select donvi from DONVIDOLUONG where PK_SEQ = sp.DVDL_FK) as donvi, a.ngayhethan as handung  "+    
			 "\n from ERP_CHUYENKHO_SANPHAM_CHITIET  "+    
			 "\n a inner join sanpham sp on a.sanpham_fk=sp.PK_SEQ  "+    
			 "\n where a.chuyenkho_fk = "+pxkBean.getId();*/
		
			
			float[] tbl_withd = { 7.0f, 60f, 12.0f,18.0f, 18f, 22.0f, 28.0f,15.0f };
			PdfPTable tbl_sanpham = new PdfPTable(tbl_withd.length);
			tbl_sanpham.setWidthPercentage(100);
			tbl_sanpham.setSpacingBefore(0.3f * CONVERT);
			tbl_sanpham.setHorizontalAlignment(Element.ALIGN_LEFT);
			tbl_sanpham.setWidths(tbl_withd);
			PdfPCell cells;
			double tongtien = 0;
			
			//add tieu de
			String[] tieude = new String[] { "Stt","Tên hàng","ĐVT","Đơn giá","Số lượng","Thành tiền","Số lô","Ghi chú"};
			for (int j = 0; j < tbl_withd.length; j++)
			{
				cells = new PdfPCell(new Paragraph(tieude[j], font10_bold));
				cells.setHorizontalAlignment(Element.ALIGN_CENTER);
				cells.setPaddingBottom(7);
				cells.setBorderColor(BaseColor.LIGHT_GRAY);
				tbl_sanpham.addCell(cells);	
			}
			
			int stt = 1;
			
			
			
			
			
			if(spMa.length>0){
				for (int x = 0; x < spMa.length; x++) {
					
					String tenhang=spTen[x];
					String dvtinh=spDonvi[x];
					String  idsp=spId[x];
					String  masp=spMa[x];

					/*String qrsp="select sp.PK_SEQ as pk_seq, sp.MA as ma, sp.TEN as ten, a.solo as solo, a.soluong as soluong , "+
					" (select isnull(dongia,0) as dongia from ERP_CHUYENKHO_SANPHAM where chuyenkho_fk="+pxkBean.getId()+"  and sanpham_fk =sp.PK_SEQ )  as dongia,  "+    
					"(select donvi from DONVIDOLUONG where PK_SEQ = sp.DVDL_FK) as donvi, a.ngayhethan as handung  "+    
					"from ERP_CHUYENKHO_SANPHAM_CHITIET  "+    
					"a inner join sanpham sp on a.sanpham_fk=sp.PK_SEQ  "+    
					"where a.chuyenkho_fk = "+pxkBean.getId();*/
					
					String qrsp=" select sp.PK_SEQ as pk_seq, sp.MA as ma, sp.TEN as ten, a.solo as solo, a.soluong as soluong    \n"+    
					 "   from ERP_CHUYENKHO_SANPHAM_CHITIET    \n"+    
					 "   a inner join sanpham sp on a.sanpham_fk=sp.PK_SEQ    \n"+    
					 "   where a.chuyenkho_fk ="+pxkBean.getId() +" and sp.PK_SEQ="+ idsp;
					
					System.out.println (" qr san pham : \n "+qrsp);
					ResultSet rsSP=db.get(qrsp);
					
					
					if(rsSP != null)
					{ 
						try {
							while(rsSP.next())
							{
								/*String tenhang=rsSP.getString("ten");
								String dvtinh=rsSP.getString("donvi");
								String  idsp=rsSP.getString("pk_seq");
								double soLUONG =rsSP.getDouble("soluong");
								String  masp=rsSP.getString("ma");
								String solo=rsSP.getString("solo");*/
								
								double soLUONG =rsSP.getDouble("soluong");
								String solo=rsSP.getString("solo");
								
								/*double dongia =rsSP.getDouble("dongia");*/
								double dongia =Double.parseDouble(spGianhap[x].replaceAll(",", ""));
								double thanhtien = soLUONG*dongia;
								tongtien +=thanhtien;

								String quydoi=quydoi(idsp, db, soLUONG);

								// do du lieu
								String[] arr = new String[] {stt + "",tenhang,dvtinh,
										DinhDangTraphacoDMS(formatter.format(dongia)),
										DinhDangTraphacoDMS(formatter1.format(soLUONG)),
										DinhDangTraphacoDMS(formatter1.format(thanhtien)), solo,quydoi };
								
								
								for (int j = 0; j < tbl_withd.length; j++) {
									cells = new PdfPCell(new Paragraph(arr[j],font10_normal));
									cells.setBorderColor(BaseColor.LIGHT_GRAY);
									// canh format
									
									if (j == 1) {
										cells.setHorizontalAlignment(Element.ALIGN_LEFT);
									} else if (j == 0 || j == 2) {
										cells.setHorizontalAlignment(Element.ALIGN_CENTER);

									} else {
										cells.setHorizontalAlignment(Element.ALIGN_RIGHT);
									}

									cells.setVerticalAlignment(Element.ALIGN_BOTTOM);
									cells.setPaddingBottom(7);
									tbl_sanpham.addCell(cells);
								}
								stt++;
							}
						} catch (Exception e) {
							e.printStackTrace();
						}

					}
					
				}
				
			}
			
			
			
			
			
	
			//tong cong
			cell = new PdfPCell();
			cell.setBorderWidthTop(0);
			cell.setBorderWidthRight(0);
			cell.setPaddingBottom(7);
			cell.setColspan(2);
			cell.setBorderColor(BaseColor.LIGHT_GRAY);
			para = new Paragraph("Cộng ", font10_bold);
			para.setAlignment(Element.ALIGN_CENTER);
			cell.addElement(para);
			tbl_sanpham.addCell(cell);
			
			cell = new PdfPCell();
			cell.setBorderWidthRight(0);
			cell.setBorderWidthTop(0);
			cell.setBorderWidthLeft(0);
			cell.setPaddingBottom(7);
			cell.setBorderColor(BaseColor.LIGHT_GRAY);
		    cell.setColspan(4);
			cell.setVerticalAlignment(Element.ALIGN_RIGHT);
			para = new Paragraph(DinhDangTraphacoDMS(formatter1.format(tongtien)), font10_bold);
			para.setAlignment(Element.ALIGN_RIGHT);
			cell.addElement(para);
			tbl_sanpham.addCell(cell);
			
			cell = new PdfPCell();
			cell.setBorderColor(BaseColor.LIGHT_GRAY);
			cell.setBorderWidthTop(0);
			cell.setBorderWidthLeft(0);
			cell.setPaddingBottom(7);
			cell.setColspan(2);
			cell.addElement(new Paragraph("", font10_bold));
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
			para=new Paragraph("\n TL.Thủ trưởng ",font10_normal);
			para.setAlignment(Element.ALIGN_CENTER);
			cellss.addElement(para);
			cellss.setHorizontalAlignment(Element.ALIGN_CENTER);
			cellss.setBorder(0);
			tbl_vat.addCell(cellss);
			
			
			cellss = new PdfPCell(new Paragraph());
			para=new Paragraph("\n Kế toán ",font10_normal);
			para.setAlignment(Element.ALIGN_CENTER);
			cellss.addElement(para);
			cellss.setHorizontalAlignment(Element.ALIGN_CENTER);
			cellss.setBorder(0);
			tbl_vat.addCell(cellss);
			
			cellss = new PdfPCell();
			para=new Paragraph("\n Thủ kho ",font10_normal);
			para.setAlignment(Element.ALIGN_CENTER);
			cellss.addElement(para);
			cellss.setHorizontalAlignment(Element.ALIGN_CENTER);
			cellss.setBorder(0);
			tbl_vat.addCell(cellss);
			
			cellss = new PdfPCell();
			para=new Paragraph("\n Người nhận ",font10_normal);
			para.setAlignment(Element.ALIGN_CENTER);
			cellss.addElement(para);
			cellss.setHorizontalAlignment(Element.ALIGN_CENTER);
			cellss.setBorder(0);
			tbl_vat.addCell(cellss);
			
			cellss = new PdfPCell();
			para=new Paragraph("Ngày "+ ngayHD[2] + " Tháng " + ngayHD[1] +" Năm " + ngayHD[0]+ "\n Lập phiếu",font10_normal);
			para.setAlignment(Element.ALIGN_CENTER);
			cellss.addElement(para);
			cellss.setHorizontalAlignment(Element.ALIGN_CENTER);
			cellss.setBorder(0);
			tbl_vat.addCell(cellss);
			
			
			//dong 2
			for (int i = 0; i < wky.length-1; i++) {
				cellss = new PdfPCell(new Paragraph("",font10_normal));
				cellss.setPaddingTop(1.3f*CONVERT);
				cellss.setBorder(0);
				tbl_vat.addCell(cellss);
			}
			//nguoi tao
			cellss = new PdfPCell();
			cellss.setHorizontalAlignment(Element.ALIGN_CENTER);
			para=new Paragraph(nguoitao,font10_normal);
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
	

	private String quydoi(String idsp, dbutils db, double soLUONG){

		NumberFormat formatter = new DecimalFormat("#,###,###.##");
		NumberFormat formatter1 = new DecimalFormat("#,###,###");
		//quy doi thung
		String qr_quydoi = " select soluong1 as soluong1, dvdl1_fk as dvdl1, soluong2 as soluong2, dvdl2_fk as dvdl2, "+
		"\n (select donvi from DONVIDOLUONG where pk_seq=dvdl1_fk) as donvi1 from quycach where  dvdl2_fk=100018 and sanpham_fk = '" + idsp + "'";
		String quydoi = "";
		System.out.print("\n -----------qr so thung quy doi:"+ qr_quydoi);
		ResultSet rs_quydoi = db.get(qr_quydoi);
		String donvi1="";
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

						//lay chu dau don vi nho
						donvi1=rs_quydoi.getString("donvi1");
						if(donvi1.length()>0){
							donvi1=donvi1.substring(0, 1);
						}

						//doi ra thung
						quydoi = String.valueOf(((soLUONG * sl2)/ sl1));
						if(quydoi.contains(".")){
							String[] qd = quydoi.split("\\.");
							quydoi = qd[0] ;
						}

						// khong dc 1 thung thi hk hien
						if(!quydoi.equals("0")){
							quydoi=formatter1.format(Double.parseDouble(quydoi)) + "T";
						}
						else
							quydoi="";

						//tinh so le
						if ((soLUONG * sl2) % sl1 != 0) {
							quydoi += " "+ String.valueOf(formatter1.format((soLUONG * sl2)% sl1)) +donvi1;
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




	
	/*
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
	
*/
	private String getDate() {
		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		Date date = new Date();
		dateFormat.format(date);
		return dateFormat.format(date);
	}

	private String DinhDangTraphacoDMS(String sotien) {
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

		/*if(tkco1.length() <=0 && tkco2.length() <=0)
			tkco="511/131/333";
			else
				tkco=	tkco+"/511/131/333";*/

		return tkco;

	}

}
