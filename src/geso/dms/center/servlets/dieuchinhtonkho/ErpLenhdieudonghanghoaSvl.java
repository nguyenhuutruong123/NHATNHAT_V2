package geso.dms.center.servlets.dieuchinhtonkho;

import geso.dms.center.beans.dieuchinhtonkho.IErpChuyenkhoTT;
import geso.dms.center.beans.dieuchinhtonkho.imp.ErpChuyenkhoTT;
import geso.dms.center.beans.doctien.doctienrachu;
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

public class ErpLenhdieudonghanghoaSvl extends HttpServlet{
	private static final long serialVersionUID = 1L;

	public ErpLenhdieudonghanghoaSvl()
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

		//LAY THONG TIN
		IErpChuyenkhoTT lsxBean = new ErpChuyenkhoTT(id);
		String loaidonhang = request.getParameter("loaidonhang");
		if(loaidonhang == null)
			loaidonhang = "0";
		lsxBean.setLoaidonhang(loaidonhang);
		System.out.println("---LOAI DON HANG: " + loaidonhang);
		lsxBean.setUserId(userId); 
		lsxBean.init();

		//===========
		String task = request.getParameter("task");    

		response.setContentType("application/pdf");
		response.setHeader("Content-Disposition", " inline; filename=PhieuXuatKhoTT.pdf");

		Document document = new Document();
		ServletOutputStream outstream = response.getOutputStream();

		this.form_phieuxuat(document, outstream, lsxBean ,db);


	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		this.doGet(request, response);
	}

	private void form_phieuxuat(Document document,ServletOutputStream outstream,IErpChuyenkhoTT pxkBean, dbutils db)
	{
		document.open();

		//====LAY THONG TIN ve lenh chuyen noi bo
		String lenhdieudong=pxkBean.getLenhdieudong(); //pkxBean la phieu chuyen kho HO,
		String ngaydieudong=pxkBean.getNgaydieudong();
		String lddCua=pxkBean.getLDDcua();
		String veviec=pxkBean.getLDDveviec();
		String nguoivanchuyen=pxkBean.getNguoivanchuyen();
		String sohopdong=pxkBean.getSohopdong();
		String phuongtienvanchuyen=pxkBean.getPtvanchuyen();
		String khoxuatid=pxkBean.getKhoXuatId();
		String khonhapid=pxkBean.getKhoNhapId(); 
		String sodms=pxkBean.getId(); // pk_seq cua
		String noinhan="";
		String noidung="Xuất điều chỉnh kho";
		String tkno="tk no";
		String tkco="tk co";
		String sohoadon="";
		String masothue="";
		String noixuat="";
		String diachikinhdoanh="";
		String diachinhan="";
		String khoxuat="";
		String khonhap="";
		String nguoitao="";		


		//thong tin dv xuat HO DCL LAY TU NHA CUNG CAP
		String qr_xuat=" SELECT TEN, DIACHI, MASOTHUE, taikhoan, nganhang, DIENTHOAI, FAX FROM NHACUNGCAP WHERE PK_SEQ=100001 ";
		ResultSet rsDVxuat=db.get(qr_xuat);
		System.out.println(" noi xuat: "+qr_xuat);
		if( rsDVxuat!=null){
			try {
				while(rsDVxuat.next()){
					noixuat=rsDVxuat.getString("ten");
					masothue=rsDVxuat.getString("masothue");
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}



		// tim kho xuat 
		ResultSet khoxuatRs = pxkBean.getKhoXuatRs();
		if(khoxuatRs!=null){
			try {
				while(khoxuatRs.next()){
					if( khoxuatRs.getString("pk_seq").equals(khoxuatid))
					{
						khoxuat=khoxuatRs.getString("ten");
					}
				}
			} catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
			}
		}


		//tim ben nhan: lay tu nnp

		String npp_id=pxkBean.getNppId();
		String qr_npp="select isnull(XUATTAIKHO,'') as xuattaikho, ten as ten, isnull(diachi,'') as diachi from nhaphanphoi where pk_seq="+npp_id;
		ResultSet rsKhoxuat=db.get(qr_npp);
		if( rsKhoxuat!=null){
			try {
				while(rsKhoxuat.next()){
					//khonhap=rsKhoxuat.getString("xuattaikho");
					noinhan=rsKhoxuat.getString("ten");
					diachikinhdoanh=rsKhoxuat.getString("diachi");
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}


		//lay kho nhan, lay tu donhang_fk cua e r p chuyen kho

		String qr_khonhan=" select isnull(kho.ten,'') as tenkho "+    
		" from nhaphang nh inner join NHAPHANG_DDH nh_dh on nh.PK_SEQ =nh_dh.nhaphang_fk "+    
		" inner join kho kho on kho.pk_seq=nh.kho_fk "+    
		" where nh_dh.ddh_fk=(select ddh_fk from erp_chuyenkho where pk_seq="+ pxkBean.getId() +")";
		ResultSet rskhonhan=db.get(qr_khonhan);
		if( rskhonhan!=null){
			try {
				while(rskhonhan.next()){
					//khonhap=rsKhoxuat.getString("xuattaikho");
					khonhap=rskhonhan.getString("tenkho");
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}


		// LAY SO CHUNG TU(  sonetId RESET THEO THANG)
		String qr_sonet="select nh.sonetId as sonetid,nv.ten as ten from ERP_CHUYENKHO nh \n "+
		" left join nhanvien nv on nv.PK_SEQ= nh.nguoisua \n  WHERE nh.PK_SEQ="+pxkBean.getId();
		String sonetid="";
		System.out.println("lay nguoi sua:"+ qr_sonet);
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
			document.setMargins(0.0f*CONVERT, 0.5f*CONVERT, 2.0f*CONVERT, 0.0f*CONVERT); // L,R,T,B
			PdfWriter writer = PdfWriter.getInstance(document, outstream);

			PdfPCell cell ;
			Paragraph para;
			Chunk chunk;
			document.open() ;


			BaseFont bf = BaseFont.createFont("c:\\windows\\fonts\\times.ttf", BaseFont.IDENTITY_H, BaseFont.EMBEDDED);
			Font font = new Font(bf, 13, Font.BOLD);
			Font font2 = new Font(bf, 8, Font.BOLD);
			Font font3 = new Font(bf, 10, Font.BOLD);

			//header 1
			PdfPTable tableheader =new PdfPTable(1);
			tableheader.setWidthPercentage(100);			
			//add don vi noi xuat
			cell = new PdfPCell();
			cell.setBorder(0);
			cell.setPaddingTop(-0.2f*CONVERT);
			cell.setPaddingLeft(2.0f * CONVERT);
			cell.setVerticalAlignment(Element.ALIGN_LEFT);
			Paragraph pxk = new Paragraph(noixuat,new Font(bf, 8, Font.BOLD));
			pxk.setAlignment(Element.ALIGN_LEFT);
			pxk.setSpacingAfter(2);
			cell.addElement(pxk);	
			tableheader.addCell(cell);


			//ma so thue
			cell = new PdfPCell();
			cell.setBorder(0);			
			cell.setPaddingLeft(2.6f * CONVERT);
			cell.setVerticalAlignment(Element.ALIGN_LEFT);
			pxk = new Paragraph(masothue,new Font(bf, 8, Font.BOLD));
			pxk.setAlignment(Element.ALIGN_LEFT);
			pxk.setSpacingAfter(2);
			cell.addElement(pxk);	
			tableheader.addCell(cell);

			document.add(tableheader);

			//bang so hop dong dms

			PdfPTable tab_dms =new PdfPTable(1);
			tab_dms.setWidthPercentage(100);	
			cell = new PdfPCell();
			cell.setBorder(0);
			cell.setPaddingTop(2.7f * CONVERT);
			cell.setPaddingLeft(1.5f * CONVERT);
			cell.setVerticalAlignment(Element.ALIGN_RIGHT);

			/*pxk = new Paragraph( sohopdong,new Font(bf, 8, Font.BOLD));
			pxk.setAlignment(Element.ALIGN_RIGHT);
			pxk.setSpacingAfter(2);
			cell.addElement(pxk);	*/

			pxk = new Paragraph("Số PX: " + sonetid,new Font(bf, 8, Font.BOLD));
			pxk.setAlignment(Element.ALIGN_RIGHT);
			pxk.setSpacingAfter(2);
			cell.addElement(pxk);		
			tab_dms.addCell(cell);
			document.add(tab_dms);


			//============================= thong tin lenh dieu dong hang hoa
			//----------------------ADD INFO KHACH HANG : bang 1 cot -----------------------------
			PdfPTable tbl_khachhang =new PdfPTable(1);
			tbl_khachhang.setWidthPercentage(100);

			//--dong 0 noi nhan
			cell = new PdfPCell();
			cell.setBorder(0);
			cell.setFixedHeight(0.7f*CONVERT);
			cell.setVerticalAlignment(Element.ALIGN_LEFT);
			cell.setPaddingLeft(3.7f * CONVERT);
			para = new Paragraph(  " "+noinhan, new Font(bf, 10, Font.BOLD));
			cell.addElement(para);
			tbl_khachhang.addCell(cell);

			//--dong 1 dia chi kinh doanh noi nhan
			cell = new PdfPCell();
			cell.setBorder(0);
			cell.setFixedHeight(0.7f*CONVERT);
			cell.setVerticalAlignment(Element.ALIGN_LEFT);
			cell.setPaddingLeft(5.4f * CONVERT);
			para = new Paragraph(" "+diachikinhdoanh, new Font(bf, 10, Font.BOLD));
			cell.addElement(para);
			tbl_khachhang.addCell(cell);


			//--dong2 kho xuat
			cell = new PdfPCell();
			cell.setBorder(0);
			cell.setFixedHeight(0.7f*CONVERT);
			cell.setVerticalAlignment(Element.ALIGN_LEFT);
			cell.setPaddingLeft(6.7f * CONVERT);
			para = new Paragraph(" "+ khoxuat , new Font(bf, 10, Font.BOLD));
			cell.addElement(para);
			tbl_khachhang.addCell(cell);


			//--dong4 noi giao nhan
			cell = new PdfPCell();
			cell.setBorder(0);
			cell.setFixedHeight(0.7f*CONVERT);
			cell.setPaddingLeft(5.7f * CONVERT);
			cell.setVerticalAlignment(Element.ALIGN_LEFT); //add bang 2 cot
			para = new Paragraph(" "+khonhap, new Font(bf, 10, Font.BOLD));
			cell.addElement(para);
			tbl_khachhang.addCell(cell);


			//--dong5 dia chi roi xuong dong
			cell = new PdfPCell();
			cell.setBorder(0);	
			cell.setFixedHeight(0.7f*CONVERT);
			para = new Paragraph(" " , new Font(bf, 10, Font.BOLD));
			cell.addElement(para);
			tbl_khachhang.addCell(cell);	


			//--dong6  phuong tien van chuyen
			cell = new PdfPCell();
			cell.setBorder(0);
			cell.setFixedHeight(0.7f*CONVERT);
			cell.setVerticalAlignment(Element.ALIGN_LEFT);
			cell.setPaddingLeft(7.0f * CONVERT);
			para = new Paragraph(" " +phuongtienvanchuyen , new Font(bf, 10, Font.BOLD));
			cell.addElement(para);
			tbl_khachhang.addCell(cell);	


			//--dong7 rong
			cell = new PdfPCell();
			cell.setBorder(0);
			cell.setFixedHeight(0.7f*CONVERT);
			cell.setVerticalAlignment(Element.ALIGN_LEFT);
			cell.setPaddingLeft(9.0f * CONVERT);
			para = new Paragraph(" " , new Font(bf, 10, Font.BOLD));
			cell.addElement(para);
			tbl_khachhang.addCell(cell);	


			//--dong8 noidung
			cell = new PdfPCell();
			cell.setBorder(0);
			cell.setFixedHeight(0.7f*CONVERT);
			cell.setVerticalAlignment(Element.ALIGN_LEFT);
			cell.setPaddingLeft(3.5f * CONVERT);
			para = new Paragraph(" "+pxkBean.getGhichu() , new Font(bf, 10, Font.BOLD));
			cell.addElement(para);
			tbl_khachhang.addCell(cell);	



			document.add(tbl_khachhang);


			// Table Content---------------------------------bang du lieu----------------------------------


			// == thong tin danh sach san pham
			String[] spId = pxkBean.getSpId();
			String[] spMa = pxkBean.getSpMa();
			String[] spTen = pxkBean.getSpTen();
			String[] spDonvi = pxkBean.getSpDonvi();
			String[] spSoluong = pxkBean.getSpSoluong();
			String[] spGianhap = pxkBean.getSpGianhap();

			/*String qrsp="select sp.PK_SEQ as pk_seq, sp.MA as ma, sp.TEN as ten, a.solo as solo, a.soluong as soluong , "+
				"\n isnull( (select [dbo].[GiaCkBanLeNppSanPham]("+ 1+",null,sp.pk_seq,'"+ ngaydieudong +"' )) ,0) as dongia, " +
				 "\n (select donvi from DONVIDOLUONG where PK_SEQ = sp.DVDL_FK) as donvi, a.ngayhethan as handung  "+    
				 "\n from ERP_CHUYENKHO_SANPHAM_CHITIET  "+    
				 "\n a inner join sanpham sp on a.sanpham_fk=sp.PK_SEQ  "+    
				 "\n where a.chuyenkho_fk = "+pxkBean.getId();


				System.out.println (" qr san pham :"+qrsp);
				ResultSet rsSP=db.get(qrsp);*/
			//================do data

			float[] tbl_withd = { 7.0f, 55f , 10.0f, 28.0f, 43f, 29.0f };

			PdfPTable tbl_sanpham = new PdfPTable(tbl_withd.length);
			//tbl_sanpham.setSpacingBefore(1.8f*CONVERT );
			tbl_sanpham.setSpacingBefore(2.0f*CONVERT );
			tbl_sanpham.setWidthPercentage(100);
			tbl_sanpham.setHorizontalAlignment(Element.ALIGN_LEFT);
			tbl_sanpham.setWidths(tbl_withd);
			PdfPCell cells;

			double tongtien = 0;

			int stt=1;
			int dongtang=1;
			String chuoi1="",chuoi2="",chuoicd="";
			int vitri1=0;

			if(spMa.length>0){
				for (int x = 0; x < spMa.length; x++) {

					String tenhang=spTen[x];
					String dvtinh=spDonvi[x];
					String  idsp=spId[x];
					String  masp=spMa[x];
					double soLUONG =Double.parseDouble (spSoluong[x].replaceAll(",", ""));
					double dongia =Double.parseDouble(spGianhap[x].replaceAll(",", ""));
					double thanhtien = soLUONG*dongia;
					tongtien +=thanhtien;
					String ghibangchu="ghi bang chu";
					String quycach="";
					doctienrachu doc=new doctienrachu();
					ghibangchu = doc.docTien((long)soLUONG);
					String ghibangchuIN = (ghibangchu.substring(0,1)).toUpperCase() + ghibangchu.substring(1); //Viết hoa ký tự đầu tiên
					ghibangchuIN=ghibangchuIN.replaceAll("đồng", dvtinh.toLowerCase());
					ghibangchuIN=ghibangchuIN.replaceAll("nghìn", "ngàn");

					// do du lieu
					String[] arr = new String[] {stt+"",tenhang, dvtinh,
							DinhDangTraphacoDMS(formatter1.format(soLUONG)),
							ghibangchuIN,quycach};

					//in
					for (int j = 0; j < tbl_withd.length; j++)
					{

						// NẾU LÀ TÊN SẢN PHẨM
						if(j==1)
						{
							if(arr[1].length() <= 35)
								cells = new PdfPCell(new Paragraph(arr[j],new Font(bf, 10, Font.BOLD)));
							else
							{
								chuoi1 = arr[j].substring(0, 35);
								System.out.println("chuoi 1: " + chuoi1);
								//  System.out.println("vitri 1: " + vitri1);
								vitri1 = chuoi1.lastIndexOf(" ");
								if(vitri1>=0){
									chuoicd = chuoi1.substring(0, vitri1);
									System.out.println("chuoi cd: " + chuoicd);
									chuoi2 = arr[j].substring(vitri1+1, arr[j].length());
									System.out.println("chuoi 2: " + chuoi2);
									cells = new PdfPCell(new Paragraph(chuoicd, new Font(bf, 10, Font.BOLD)));  
								}
								else
								{
									chuoicd = chuoi1.substring(0, 35);
									chuoi2 = chuoi2.substring(35 + 1,arr[2].length());

									cells = new PdfPCell(new Paragraph(chuoicd, new Font(bf, 10, Font.BOLD)));  
								}

							}
						}
						else
							cells = new PdfPCell(new Paragraph(arr[j], new Font(bf, 10, Font.BOLD)));     


						if(j==0 ||j==1 ){
							cells.setHorizontalAlignment(Element.ALIGN_LEFT);
						}
						else
							if(j==2){
								cells.setHorizontalAlignment(Element.ALIGN_CENTER);

							}
							else
							{
								cells.setHorizontalAlignment(Element.ALIGN_RIGHT);
							}

						cells.setVerticalAlignment(Element.ALIGN_BOTTOM);
						cells.setBorder(0);
						cells.setFixedHeight(0.6f*CONVERT);
						tbl_sanpham.addCell(cells);	

					}
					stt++; 
					dongtang++;


					if(arr[1].length() > 35)
					{
						int dodaichuoi = chuoi2.length();
						System.out.println("chuoi222:"+chuoi2.length());
						while(dodaichuoi > 0)
						{
							int boiso=dodaichuoi/35;
							if(boiso > 0)
							{
								chuoi1 = chuoi2.substring(0,35);
								vitri1 = chuoi1.lastIndexOf(" ");
								if(vitri1>=0){
									System.out.println("chuoi 1: " + chuoi1);
									System.out.println("vitri 1: " + vitri1);
									chuoicd = chuoi1.substring(0, vitri1);
									System.out.println("chuoi cd: " + chuoicd);
									chuoi2 = chuoi2.substring(vitri1 + 1,dodaichuoi );
									System.out.println("chuoi 2: " + chuoi2);
								}
								else
								{
									chuoicd = chuoi1.substring(0, 35);
									chuoi2 = chuoi2.substring(35 + 1,dodaichuoi );
								}
							}
							else
							{
								chuoicd = chuoi2;
								chuoi2 = "";
							}

							for(int m = 0; m < arr.length; m++)
							{   
								if(m==1)
									cells = new PdfPCell(new Paragraph(chuoicd, new Font(bf, 10, Font.BOLD)));
								else
									cells = new PdfPCell(new Paragraph(" ", new Font(bf, 10, Font.BOLD)));

								if(m==0 ||m==1 ){
									cells.setHorizontalAlignment(Element.ALIGN_LEFT);
								}
								else
									if(m==2){
										cells.setHorizontalAlignment(Element.ALIGN_CENTER);

									}
									else
									{
										cells.setHorizontalAlignment(Element.ALIGN_RIGHT);
									}

								cells.setVerticalAlignment(Element.ALIGN_BOTTOM);
								cells.setBorder(0);
								cells.setFixedHeight(0.6f*CONVERT);
								tbl_sanpham.addCell(cells);	
							}
							dodaichuoi = chuoi2.length();

							dongtang++;

						}
					}
				
			}


			int kk=0;
			while(kk <=19-dongtang)
			{
				String[] arr_bosung = new String[] { " ", " " , " ", " ", " "," "};

				for (int j = 0; j < arr_bosung.length; j++)
				{
					cells = new PdfPCell(new Paragraph(arr_bosung[j], new Font(bf, 12, Font.BOLD)));
					cells.setVerticalAlignment(Element.ALIGN_BOTTOM);
					cells.setHorizontalAlignment(Element.ALIGN_CENTER);
					cells.setFixedHeight(0.6f*CONVERT);
					cells.setBorder(0);

					tbl_sanpham.addCell(cells);
				}

				kk++;
			}


			document.add(tbl_sanpham);

			//add bang ngay thang nam
			String [] ngayHD = ngaydieudong.split("-");	
			PdfPTable tbl_tongtien = new PdfPTable(1);
			tbl_tongtien.setWidthPercentage(100);
			tbl_tongtien.setHorizontalAlignment(Element.ALIGN_RIGHT);

			if(ngaydieudong.length()>0)
			{
				cells = new PdfPCell(new Paragraph(ngayHD[2]+"                       "+ngayHD[1]+"                     "+ngayHD[0], new Font(bf, 10, Font.BOLD)));
				cells.setVerticalAlignment(Element.ALIGN_LEFT);
				cells.setPaddingLeft(5f*CONVERT);
				cells.setPaddingTop(0.3f*CONVERT);
				cells.setHorizontalAlignment(Element.ALIGN_LEFT);
				cells.setBorder(0);
				tbl_tongtien.addCell(cells);
			}
			else

			{
				cells = new PdfPCell(new Paragraph(" ", new Font(bf, 10, Font.BOLD)));
				cells.setVerticalAlignment(Element.ALIGN_LEFT);
				cells.setPaddingLeft(2f*CONVERT);
				cells.setHorizontalAlignment(Element.ALIGN_LEFT);
				cells.setBorder(0);
				tbl_tongtien.addCell(cells);
			}

			cells = new PdfPCell(new Paragraph(nguoitao, new Font(bf, 10, Font.BOLD)));
			cells.setVerticalAlignment(Element.ALIGN_LEFT);
			cells.setPaddingTop(3.3f*CONVERT);
			cells.setPaddingLeft(2f*CONVERT);
			cells.setHorizontalAlignment(Element.ALIGN_LEFT);
			cells.setBorder(0);
			tbl_tongtien.addCell(cells);

			document.add(tbl_tongtien);

			// ten nguoi tao


		}	
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

}
