package geso.dms.distributor.servlets.bienbannghiemthuNPP;

import geso.dms.center.beans.doctien.doctienrachu;
import geso.dms.distributor.beans.hoadontaichinh.IHoadontaichinh;
import geso.dms.distributor.beans.hoadontaichinh.imp.Hoadontaichinh;
import geso.dms.distributor.beans.hoadontaichinhNPP.IErpHoadontaichinhNPP;
import geso.dms.distributor.beans.hoadontaichinhNPP.imp.ErpHoadontaichinhNPP;
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
import com.itextpdf.text.pdf.BaseFont;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

public class ErpInBienbannghiemthuKHSvl extends HttpServlet {
	public ErpInBienbannghiemthuKHSvl()
	{
		super();
	}

	float CONVERT = 28.346457f;  // =1cm
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		dbutils db = new dbutils();
		HttpSession session = request.getSession();
		String userId = (String) session.getAttribute("userId");

		Utility util = new Utility();
		if (userId.length() == 0)
			userId = request.getParameter("userId");

		String querystring = request.getQueryString();
		String id = util.antiSQLInspection(request.getParameter("pdf"));
		String nppId = util.antiSQLInspection(request.getParameter("nppId"));

		IHoadontaichinh hdBean = new Hoadontaichinh(id);
		hdBean.setUserId(userId);
		hdBean.init();

		String type = request.getParameter("type");
		System.out.println(" loai type:"+type);
		if(type == null)
			type = "";

		//=============khai bao doc
		response.setContentType("application/pdf");
		response.setHeader("Content-Disposition", " inline; filename=Bienbannghiemthu.pdf");
		Document document = new Document();
		ServletOutputStream outstream = response.getOutputStream();

		form_phieunghiemthu1(document, outstream,hdBean);
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

	private String DinhDangTraphacoDMS(String sotien)
	{
		sotien = sotien.replaceAll("\\.", "_");
		sotien = sotien.replaceAll(",", "\\.");
		sotien = sotien.replaceAll("_", ",");

		return sotien;
	}


	private void form_phieunghiemthu1( Document document,ServletOutputStream outstream,IHoadontaichinh hdBean){

		dbutils db = new dbutils();
		// lay thong tin ben giao //cua duoc cuu long
		String npp_fk="";
		String khId="";
		String ctyTen="";
		String cty_MST ="";
		String cty_Diachi="";
		String cty_Sotaikhoan= "";
		String cty_Dienthoai= "";
		String cty_Fax= "";
		String cty_bank= "";
		//DON VI BAN: TONG CTY HO DCL
		String qr_ho="select ten , diachi, dienthoai, fax, masothue, taikhoan, nganhang from nhacungcap where pk_seq=100001 ";   
		ResultSet rsHo = db.get(qr_ho);
		if(rsHo!=null){
			try {
				if(rsHo.next())
				{
					ctyTen = rsHo.getString("TEN");
					cty_MST = rsHo.getString("MASOTHUE");
					cty_Diachi = rsHo.getString("DIACHI");
					cty_Sotaikhoan = rsHo.getString("taikhoan");
					cty_Dienthoai = rsHo.getString("dienthoai");
					cty_Fax = rsHo.getString("fax");
					cty_bank = rsHo.getString("nganhang");
					rsHo.close();   
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}


		//LAY THONG TIN KHACHHANG	
		String Donvi="";
		String kh_MST ="";
		String kh_dienthoai ="";
		String kh_Diachi="";
		String kh_sotaikhoan="";
		String kh_fax="";
		String sql="";		
		sql="select kh.pk_seq, kh.TEN as donvi,isnull(kh.chucuahieu,'') as chucuahieu, kh.diachi as diachi ,isnull(masothue,'') as masothue, '' as taikhoan, dienthoai as dienthoai,   "+    
		"(select ten from tinhthanh where pk_seq=kh.tinhthanh_fk) as tinhthanh, "+    
		"(select ten from QUANHUYEN where pk_seq=kh.quanhuyen_fk) as quanhuyen "+    
		" from KHACHHANG kh where kh.pk_seq=(select khachhang_fk from HOADON where pk_seq ="+hdBean.getId()+") ";
		System.out.println("\n Lay TT KH "+sql +"\n");
		ResultSet rsLayKH= db.get(sql);
		if(rsLayKH!=null){
			try {
				while(rsLayKH.next())
				{
					Donvi = rsLayKH.getString("donvi");
					kh_MST = rsLayKH.getString("MASOTHUE");
					kh_Diachi = rsLayKH.getString("DIACHI")+", "+rsLayKH.getString("quanhuyen")+", "+rsLayKH.getString("tinhthanh");
					kh_dienthoai=rsLayKH.getString("dienthoai");
					kh_sotaikhoan=rsLayKH.getString("TAIKHOAN");
				}   
				rsLayKH.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}



		//thong tin san pham hang hoa
		// 1. tim don hang cua hoa don==> 2. tim lai ma hoa don (khuyen mai  loaihoadon=1, hoa don sp ban loaihoadon =0)
		//3. sp ban map vs HOADON_SP_CHITIET 
		//4. sp khuyen mai map vs HOADON_CTKM_TRAKM_CHITIET
		//lay don hang
		String iddh="";
		String qr_dh=" select ddh_fk as madh from HOADON_DDH where hoadon_fk="+hdBean.getId();
		ResultSet rsDH=db.get(qr_dh);
		if(rsDH!=null){
			try {
				while(rsDH.next()){
					iddh=rsDH.getString("madh");
				}
				rsDH.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		//tim danh sach san pham  ban 
		String sqlIN_SANPHAM = " select MA, TEN, DONVI, DONGIA, SOLO, NGAYHETHAN, THUEVAT, sum( SOLUONG ) as soluong, CHIETKHAU, NGAYHETHAN AS HANDUNG from HOADON_SP_CHITIET  "+    
		" where hoadon_fk = "+    
		" ( select a.pk_seq as mahoadon from HOADON a  inner join HOADON_DDH  b "+    
		" on a.PK_SEQ=b.HOADON_FK where  a.loaihoadon='0' and b.ddh_fk= '" + iddh + "' and a.trangthai not in(3,5)) "+
		"group by MA, TEN, DONVI, DONGIA, SOLO, NGAYHETHAN, THUEVAT,CHIETKHAU";
		System.out.println("---IN SAN PHAM: " + sqlIN_SANPHAM );

		//danh sach san pham khuyen mai
		String sqlIN_SANPHAMKM =" select sp.MA as ma, sp.TEN as ten, dv.DONVI as donvi,km.DONGIA as dongia, km.SOLO as solo, km.NGAYHETHAN as HANDUNG,sum( km.SOLUONG ) as soluong, '' as CHIETKHAU    "+    
		"  from HOADON_CTKM_TRAKM_CHITIET km inner join sanpham sp on sp.pk_seq=km.sanpham_fk  inner join DONVIDOLUONG dv on dv.PK_SEQ=sp.DVDL_FK "+    
		"  where hoadon_fk= "+    
		"   ( select a.pk_seq as mahoadon from HOADON a  inner join HOADON_DDH  b "+    
		" on a.PK_SEQ=b.HOADON_FK where  a.loaihoadon='1' and b.ddh_fk='" + iddh + "' and a.trangthai not in(3,5)) "+
		" group by sp.MA, sp.TEN,dv.DONVI,km.DONGIA, km.SOLO,km.NGAYHETHAN";

		System.out.println("--- \n IN SAN PHAMKM: " + sqlIN_SANPHAMKM +"\n" );
		ResultSet rsSP = db.get(sqlIN_SANPHAM);
		ResultSet rsSPKM = db.get(sqlIN_SANPHAMKM);
		double tongtien = 0;
		String vat="";
		String tienvat= hdBean.getTongVAT();
		String tongtien_vat =hdBean.getTongtienAVAT();


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
			String date=getDate();
			para=new Paragraph("Hôm nay, lúc  ... giờ ...  phút, ngày "+ date.substring(8)+" tháng "+ date.substring(5,7)+ " năm "+date.substring(0,4) +" chúng tôi gồm có:",font10_ilatic);
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
			chunk=new Chunk(" : "+Donvi.toUpperCase(), font10_Bold);
			para.add(chunk);
			cell.addElement(para);
			tbl.addCell(cell);


			// thong tin ben a
			cell = new PdfPCell();
			cell.setBorder(0);
			cell.setPaddingLeft(1.5f*CONVERT);
			cell.setHorizontalAlignment(Element.ALIGN_LEFT);

			para=new Paragraph("Địa chỉ: " +kh_Diachi,font10_normal);
			para.setAlignment(Element.ALIGN_LEFT);
			cell.addElement(para);

			para=new Paragraph("Điện thoại: " +kh_dienthoai         +"                 -Fax:"+kh_fax,font10_normal); // kem fax
			para.setAlignment(Element.ALIGN_LEFT);
			cell.addElement(para);

			para=new Paragraph("Mã số thuế: "+kh_MST,font10_normal);
			para.setAlignment(Element.ALIGN_LEFT);
			cell.addElement(para);


			para=new Paragraph("Tài khoản: " +kh_sotaikhoan,font10_normal); //tai ngan hang nao?
			para.setAlignment(Element.ALIGN_LEFT);
			cell.addElement(para);

			para=new Paragraph("Đại diện:                                                                      Chức vụ:",font10_normal);
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

			chunk=new Chunk(" : "+ctyTen.toUpperCase(), font10_Bold);
			para.add(chunk);
			cell.addElement(para);
			tbl.addCell(cell);

			// thong tin ben b
			cell = new PdfPCell();
			cell.setBorder(0);
			cell.setPaddingLeft(1.5f*CONVERT);
			cell.setHorizontalAlignment(Element.ALIGN_LEFT);

			para=new Paragraph("Địa chỉ: "+ cty_Diachi,font10_normal);
			para.setAlignment(Element.ALIGN_LEFT);
			cell.addElement(para);

			para=new Paragraph("Điện thoại: "+ cty_Dienthoai +"                – Fax: "+ cty_Fax ,font10_normal); // kem fax
			para.setAlignment(Element.ALIGN_LEFT);
			cell.addElement(para);

			para=new Paragraph("Mã số thuế: "+cty_MST,font10_normal);
			para.setAlignment(Element.ALIGN_LEFT);
			cell.addElement(para);


			para=new Paragraph("Tài khoản:  "+ cty_Sotaikhoan + " tại " +cty_bank,font10_normal); //tai ngan hang nao?
			para.setAlignment(Element.ALIGN_LEFT);
			cell.addElement(para);

			para=new Paragraph("Đại diện:                                                                      Chức vụ:",font10_normal);
			para.setAlignment(Element.ALIGN_LEFT);
			cell.addElement(para);
			tbl.addCell(cell);

			// so hop dong
			cell = new PdfPCell();
			cell.setBorder(0);
			cell.setHorizontalAlignment(Element.ALIGN_LEFT);
			para=new Paragraph("      Hai bên cùng tiến hành nghiệm thu một phần của  hợp đồng số:              ngày     tháng      năm      như sau:",font10_normal);
			para.setAlignment(Element.ALIGN_LEFT);
			cell.addElement(para);
			tbl.addCell(cell);
			document.add(tbl);


			//========bang danh sach san pham
			float[] tbl_withd = { 7.0f, 50f, 10f , 15.0f, 15.0f, 20f, 15f,18f, 15.0f };
			PdfPTable tbl_sanpham = new PdfPTable(tbl_withd.length);
			tbl_sanpham.setWidthPercentage(100);
			tbl_sanpham.setSpacingBefore(0.3f * CONVERT);
			tbl_sanpham.setHorizontalAlignment(Element.ALIGN_LEFT);
			tbl_sanpham.setWidths(tbl_withd);
			PdfPCell cells;

			//add tieu de
			String[] tieude = new String[] { "TT","Tên Hàng Hoá","ĐVT","Số lượng","Đơn giá","Thành tiền \n (VNĐ)","Số lô","Hạn dùng","Nước SX – Hãng SX"};
			for (int j = 0; j < tbl_withd.length; j++)
			{
				cells = new PdfPCell(new Paragraph(tieude[j], new Font(bf, 10, Font.BOLD)));
				cells.setHorizontalAlignment(Element.ALIGN_CENTER);
				cells.setPaddingBottom(7);
				tbl_sanpham.addCell(cells);	
			}
			int stt=1;
			//danh sach
			try {

				//SAN PHAM BAN
				while(rsSP.next())
				{
					double soLUONG = rsSP.getDouble("soluong");
					double dongia = rsSP.getDouble("dongia");
					double thanhtien = soLUONG*dongia;

					tongtien +=thanhtien;
					String[] arr = new String[] { Integer.toString(stt), rsSP.getString("TEN"), rsSP.getString("DONVI"),
							DinhDangTraphacoDMS(formatter1.format(soLUONG)), 
							DinhDangTraphacoDMS(formatter.format(dongia)),
							DinhDangTraphacoDMS(formatter1.format(thanhtien)),rsSP.getString("solo"),dinhdangngay(rsSP.getString("handung")),"Việt Nam" };

					System.out.println("\n don vi tinh: "+ rsSP.getString("DONVI"));

					for (int j = 0; j < tbl_withd.length; j++)
					{
						cells = new PdfPCell(new Paragraph(arr[j], new Font(bf, 10, Font.NORMAL)));
						if(j==1){
							cells.setHorizontalAlignment(Element.ALIGN_LEFT);
						}
						else
							if(j==2 || j==6 || j==0){
								cells.setHorizontalAlignment(Element.ALIGN_CENTER);
							}
							else
							{
								cells.setHorizontalAlignment(Element.ALIGN_RIGHT);
							}
						cells.setVerticalAlignment(Element.ALIGN_BOTTOM);
						cells.setPaddingBottom(7);
						tbl_sanpham.addCell(cells);	
					}
					stt++;
				}
				rsSP.close();

			} catch (Exception e) {
				e.printStackTrace();
			}

			try{
				//SAN PHAM KHUYEN MAI
				while(rsSPKM.next())
				{
					double soLUONG = rsSPKM.getDouble("soluong");
					double dongia = 0.0;
					double thanhtien = soLUONG*dongia;
					tongtien +=thanhtien;
					String[] arr = new String[] { Integer.toString(stt), rsSPKM.getString("TEN"), rsSPKM.getString("DONVI"),
							DinhDangTraphacoDMS(formatter1.format(soLUONG)), 
							DinhDangTraphacoDMS(formatter.format(dongia)),
							DinhDangTraphacoDMS(formatter1.format(thanhtien)),rsSPKM.getString("solo"),dinhdangngay(rsSPKM.getString("handung")),"Việt Nam" };

					for (int j = 0; j < tbl_withd.length; j++)
					{
						cells = new PdfPCell(new Paragraph(arr[j], new Font(bf, 10, Font.NORMAL)));
						if(j==1){
							cells.setHorizontalAlignment(Element.ALIGN_LEFT);
						}
						else
							if(j==2 || j==6 || j==0){
								cells.setHorizontalAlignment(Element.ALIGN_CENTER);
							}
							else
							{
								cells.setHorizontalAlignment(Element.ALIGN_RIGHT);
							}
						cells.setVerticalAlignment(Element.ALIGN_BOTTOM);
						cells.setPaddingBottom(7);
						tbl_sanpham.addCell(cells);	
					}
					stt++;

					System.out.println(" so thu tu : "+ stt);
				}
				rsSPKM.close();
			} catch (Exception e) {
				e.printStackTrace();
			}


			//lay top(1) vat %
			String qr_vat="select top(1) vat as vat from HOADON_SP_CHITIET where hoadon_fk='"+hdBean.getId()+"'";
			ResultSet rs_vat=db.get(qr_vat);
			if(rs_vat!=null){
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
			para = new Paragraph(DinhDangTraphacoDMS(formatter1.format(tongtien)) +" đồng" , new Font(bf, 10, Font.NORMAL));
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
			para = new Paragraph("Thuế VAT ("+vat+"%)", new Font(bf, 10, Font.NORMAL));
			cell.addElement(para);
			tbl_sanpham.addCell(cell);

			cell = new PdfPCell();
			cell.setBorder(0);
			cell.setColspan(4);
			cell.setVerticalAlignment(Element.ALIGN_RIGHT);
			cell.setPaddingLeft(0.5f * CONVERT);
			para = new Paragraph(tienvat.replaceAll(",", ".") +" đồng" , new Font(bf, 10, Font.NORMAL));
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
			para = new Paragraph(tongtien_vat.replaceAll(",", ".") +" đồng" ,new Font(bf, 10, Font.NORMAL));
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

			doctienrachu doctien = new doctienrachu();
			String tien = doctien.docTien(Long.parseLong(hdBean.getTongtienAVAT().replaceAll(",", "")));
			String TienIN = (tien.substring(0,1)).toUpperCase() + tien.substring(1); //Viết hoa ký tự đầu tiên
			cell = new PdfPCell();
			cell.setBorder(0);
			para = new Paragraph("      Số tiền bằng chữ:  "+TienIN, new Font(bf,12,Font.BOLDITALIC)); //cong doc tine
			para.setAlignment(Element.ALIGN_JUSTIFIED);
			cell.addElement(para);
			tbl_footer.addCell(cell);

			cell = new PdfPCell();
			cell.setBorder(0);
			String ngay=hdBean.getNgayxuatHD();
			ngay=ngay.substring(8)+"/"+ ngay.substring(5,7)+"/"+ ngay.substring(0,4);
			para = new Paragraph("      Biên bản giao hàng kèm theo hóa đơn tài chính xuất hàng số "+ hdBean.getSohoadon().trim() +" ngày " + ngay, font10_normal); 
			para.setAlignment(Element.ALIGN_JUSTIFIED);
			cell.addElement(para);
			tbl_footer.addCell(cell);

			cell = new PdfPCell();
			cell.setBorder(0);
			para = new Paragraph("      -  Hàng giao đủ số lượng, đúng chủng lọai, qui cách, hàm lượng, hạn dùng theo hợp đồng đã ký kết. ", font10_normal); 
			para.setAlignment(Element.ALIGN_JUSTIFIED);
			cell.addElement(para);
			tbl_footer.addCell(cell);

			cell = new PdfPCell();
			cell.setBorder(0);
			para = new Paragraph("      -  Cảm quan: màu sắc, hình dạng nguyên vẹn.", font10_normal); 
			cell.addElement(para);
			tbl_footer.addCell(cell);

			cell = new PdfPCell();
			cell.setBorder(0);
			para = new Paragraph();
			para.setAlignment(Element.ALIGN_CENTER);
			para.add(new Chunk("Đạt  ", font10_normal ));
			para.add(new Chunk( String.valueOf(unchecked), charfont ));
			para.add(new Chunk("                Không đạt  ", font10_normal ));
			para.add(new Chunk( String.valueOf(unchecked), charfont ));
			cell.addElement(para);
			tbl_footer.addCell(cell);


			cell = new PdfPCell();
			cell.setBorder(0);
			para = new Paragraph("      Hai bên thống nhất nghiệm thu hàng hóa trên và cùng ký vào biên bản nghiệm thu.", font10_normal); 
			para.setAlignment(Element.ALIGN_JUSTIFIED);
			cell.addElement(para);
			tbl_footer.addCell(cell);


			cell = new PdfPCell();
			cell.setBorder(0);
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
	private String dinhdangngay(String ngay) {
		String date = "";
		if (ngay.length() >= 8) {
			date = ngay.substring(8) + "/" + ngay.substring(5, 7) + "/"
			+ ngay.substring(0, 4);
		}
		return date;
	}

}
