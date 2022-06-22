
package geso.dms.distributor.servlets.hoadontaichinh;

import geso.dms.center.beans.chungloai.imp.Chungloai;
import geso.dms.center.beans.doctien.doctienrachu;
import geso.dms.center.beans.hoadontaichinh.IErpHoadontaichinh;
import geso.dms.center.servlets.reports.ThucDatVaChiTieuSKUIn;
import geso.dms.distributor.beans.donhang.IDonhang;
import geso.dms.distributor.beans.donhang.imp.Donhang;
import geso.dms.distributor.beans.hoadontaichinh.IHoadontaichinh;
import geso.dms.distributor.beans.hoadontaichinh.imp.Hoadontaichinh;
import geso.dms.distributor.beans.noptien.INoptien;
import geso.dms.distributor.db.sql.dbutils;
import geso.dms.distributor.util.Utility;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.io.StringWriter;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import net.sf.jasperreports.engine.DefaultJasperReportsContext;
import net.sf.jasperreports.engine.JRPropertiesUtil;
import net.sf.jasperreports.engine.JasperRunManager;

import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Chunk;
import com.itextpdf.text.Document;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.Font.FontFamily;
import com.itextpdf.text.Image;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Rectangle;
import com.itextpdf.text.pdf.BaseFont;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import com.itextpdf.text.PageSize;
import com.lowagie.text.pdf.PdfCell;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;

import java.util.Locale; 


public class HoadontaichinhPdfSvl extends HttpServlet

{
	private static final long serialVersionUID = 1L;

	public HoadontaichinhPdfSvl()
	{
		super();
	}


	/*Locale.setDefault(new Locale("en", "US"));*/

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
		String id = util.antiSQLInspection(request.getParameter("pdf"));
		System.out.println("id:"+ id);
		String nppId = util.antiSQLInspection(request.getParameter("nppId"));
		if(nppId==null)
			nppId="";

		IHoadontaichinh pxkBean = new Hoadontaichinh(id);
		pxkBean.setUserId(userId);
		pxkBean.init();

		dbutils db = new dbutils();
		String type = request.getParameter("type");
		System.out.println("11type:"+type);
		if(type == null)
			type = "";

		//=============khai bao doc

		pxkBean.getTongTienPDF();
		response.setContentType("application/pdf");
		response.setHeader("Content-Disposition", " inline; filename=HoaDonTaiChinh.pdf");
		Document document = new Document();
		ServletOutputStream outstream = response.getOutputStream();
		if(!type.equals("PGH"))
		{
			if (querystring.indexOf("pdf") > 0)
			{

				//xuat hoa don
				this.CreatePxk(document, outstream, pxkBean,userId,nppId,1);

			}
		}
		else 
		{
			//in phieu giao hang 
			this.CreatePxk(document, outstream, pxkBean,userId,nppId,2);
		}		
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


	//hoa don tai chinh mau 2 (cn/npp)
	private void form_hoadon(Document document,ServletOutputStream outstream,String sqlIN_SANPHAM,String sqlIN_SANPHAMKM,IHoadontaichinh pxkBean,String ctyTen,String cty_MST,String cty_Diachi,
			String cty_Sotaikhoan,String cty_Dienthoai,String cty_Fax,String Donvi,String kh_MST,
			String hinhthucTT,String ngayxuatHD,String chucuahieu,String kh_sotaikhoan, String kh_Diachi,
			String iddh, String userid, String phieuxuatkho,dbutils db)
	{

		// lay thong tin don hang ban
		IDonhang dhBean = new Donhang(iddh);
		dhBean.setUserId(userid); // phai co UserId truoc khi Init			
		dhBean.init();

		double tientruocvat= 0;
		double tienck=0;
		double tientanggiam=Double.parseDouble(dhBean.getTiengiamtru());
		double tienkm=0;
		double tongtienck=0; // tien ck = ck cung + ck khuyen mai
		double tiensauck=0;


		//tim tien km 
		String qr_km="select isnull( sum(tonggiatri),0) as tienkm from donhang_ctkm_trakm  where spma is null and donhangid="+iddh;
		ResultSet rsKM=db.get(qr_km);
		if(rsKM!=null){
			try {
				while(rsKM.next()){
					tienkm=rsKM.getDouble("tienkm");
				}
				rsKM.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		System.out.println(" tien km: "+ tienkm +"\n");



		//tien ck
		String qr_ck="select sum(soluong * dongia * (chietkhaungay/100)) as tienckngay from HOADON_SP where hoadon_fk="+pxkBean.getId();
		ResultSet rsCK=db.get(qr_ck);
		if(rsCK!=null){
			try {
				while(rsCK.next()){
					tienck=rsCK.getDouble("tienckngay");
				}
				rsCK.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		System.out.println(" tien chiet khau: "+ tienck +"\n");



		String mahoadon=pxkBean.getId();
		try
		{
			NumberFormat formatter = new DecimalFormat("#,###,###.##");
			NumberFormat formatter1 = new DecimalFormat("#,###,###");

			document.setPageSize(PageSize.A4);
			//document = new Document(PageSize.A4,2.0f*CONVERT,1.5f*CONVERT,1.7f*CONVERT, 0.0f*CONVERT);
			document.setMargins(1f*CONVERT, 1f*CONVERT, 0.5f*CONVERT, 0.5f*CONVERT); // L,R,T,B

			PdfWriter writer = PdfWriter.getInstance(document, outstream);
			PdfPCell cell ;
			Paragraph para;
			Chunk chunk;

			document.open() ;


			BaseFont bf = BaseFont.createFont("c:\\windows\\fonts\\times.ttf", BaseFont.IDENTITY_H, BaseFont.EMBEDDED);
			Font font = new Font(bf, 13, Font.BOLD);
			Font font2 = new Font(bf, 8, Font.BOLD);


			PdfPTable tableheader =new PdfPTable(2);
			float []wky={17.0f,2.0f};
			tableheader.setWidthPercentage(100);	
			tableheader.setWidths(wky);

			cell = new PdfPCell();
			cell.setBorder(0);
			cell.setPaddingTop(4.0f*CONVERT);
			cell.setPaddingLeft(1.7f*CONVERT);
			cell.setHorizontalAlignment(Element.ALIGN_CENTER);
			String [] ngayHD = ngayxuatHD.split("-");
			para= new Paragraph("",new Font(bf, 8, Font.BOLD));
			if(ngayHD.length>=3){
				chunk =new Chunk(ngayHD[2] + "                     " + ngayHD[1] +"                   " + ngayHD[0],new Font(bf, 8, Font.BOLDITALIC));
				chunk.setTextRise(0.5f*CONVERT);
				para.add(chunk);
				para.setAlignment(Element.ALIGN_CENTER);

			}
			cell.addElement(para);
			tableheader.addCell(cell);

			//phieu xuat kho
			cell = new PdfPCell();
			cell.setBorder(0);
			cell.setPaddingTop(4f*CONVERT);
			cell.setHorizontalAlignment(Element.ALIGN_CENTER);
			para=new Paragraph(phieuxuatkho,new Font(bf, 10, Font.BOLD));
			cell.addElement(para);
			tableheader.addCell(cell);

			document.add(tableheader);


			// ================================ADD INFO NHA PHAN PHOI
			PdfPTable tbl_npp =new PdfPTable(1);
			tbl_npp.setWidthPercentage(100);

			//--dong1 ten npp
			cell = new PdfPCell();
			cell.setBorder(0);
			cell.setPaddingTop(-0.4f * CONVERT);
			cell.setVerticalAlignment(Element.ALIGN_LEFT);
			cell.setPaddingLeft(3.5f * CONVERT);
			para = new Paragraph( ctyTen , new Font(bf, 10, Font.BOLD));

			cell.addElement(para);
			tbl_npp.addCell(cell);

			//--dong2 ma so thue npp
			cell = new PdfPCell();
			cell.setBorder(0);
			cell.setVerticalAlignment(Element.ALIGN_LEFT);
			cell.setPaddingLeft(3.5f * CONVERT);
			para = new Paragraph( cty_MST , new Font(bf, 10, Font.BOLD));
			cell.addElement(para);
			tbl_npp.addCell(cell);

			//--dong3 ma dia chi npp
			cell = new PdfPCell();
			cell.setBorder(0);
			cell.setVerticalAlignment(Element.ALIGN_LEFT);
			cell.setPaddingLeft(3.5f * CONVERT);
			para = new Paragraph( cty_Diachi +"\n", new Font(bf, 10, Font.BOLD));
			cell.addElement(para);
			tbl_npp.addCell(cell);

			//--dong5 Stk npp
			if(cty_Sotaikhoan.equals("0"))
			{
				cty_Sotaikhoan="  ";
			}
			cell = new PdfPCell();
			cell.setBorder(0);
			cell.setVerticalAlignment(Element.ALIGN_LEFT);
			cell.setPaddingLeft(3.5f * CONVERT);
			para = new Paragraph( cty_Sotaikhoan, new Font(bf, 10, Font.BOLD));
			cell.addElement(para);
			tbl_npp.addCell(cell);

			document.add(tbl_npp);

			//----------------------ADD INFO KHACH HANG-----------------------------
			PdfPTable tbl_khachhang =new PdfPTable(1);
			tbl_khachhang.setWidthPercentage(100);

			//--dong0 ten nguoi mua hang (CHU CUA HIEU)
			cell = new PdfPCell();
			cell.setBorder(0);
			cell.setVerticalAlignment(Element.ALIGN_LEFT);
			cell.setPaddingLeft(5.0f * CONVERT);
			para = new Paragraph(chucuahieu , new Font(bf, 10, Font.BOLD));
			cell.addElement(para);
			tbl_khachhang.addCell(cell);

			//--dong1 ten khach hang
			cell = new PdfPCell();
			cell.setBorder(0);
			cell.setVerticalAlignment(Element.ALIGN_LEFT);
			cell.setPaddingLeft(3.0f * CONVERT);
			para = new Paragraph( Donvi , new Font(bf, 10, Font.BOLD));
			cell.addElement(para);
			tbl_khachhang.addCell(cell);

			//--dong2 dia chi va so tai khoan khach hang
			cell = new PdfPCell();
			cell.setBorder(0);
			cell.setVerticalAlignment(Element.ALIGN_LEFT);

			//cell add bang 2 cot
			PdfPTable tbl_diachi_stk =new PdfPTable(2);
			float[] withd_tbl= {16.0f,8.0f};
			tbl_diachi_stk.setWidths(withd_tbl);
			tbl_diachi_stk.setWidthPercentage(100);
			//--dia chi
			PdfPCell cell1 = new PdfPCell();
			cell1.setBorder(0);
			cell1.setVerticalAlignment(Element.ALIGN_LEFT);
			cell1.setPaddingLeft(3.0f * CONVERT);
			para = new Paragraph(kh_Diachi, new Font(bf, 10, Font.BOLD));
			cell1.addElement(para);
			tbl_diachi_stk.addCell(cell1);
			//--so tai khoankhoan
			if(kh_sotaikhoan.equals("0")){
				kh_sotaikhoan="";
			}
			PdfPCell cell2 = new PdfPCell();
			cell2.setBorder(0);
			cell2.setVerticalAlignment(Element.ALIGN_LEFT);
			cell2.setPaddingLeft(2.5f * CONVERT);
			para = new Paragraph(kh_sotaikhoan, new Font(bf, 10, Font.BOLD));
			cell2.addElement(para);
			tbl_diachi_stk.addCell(cell2);

			cell.addElement(tbl_diachi_stk);
			tbl_khachhang.addCell(cell);

			//--dong3 hinh thuc thanh toan va ma so thue
			cell = new PdfPCell();
			cell.setBorder(0);
			cell.setVerticalAlignment(Element.ALIGN_LEFT);
			//cell add bang 2 cot
			PdfPTable tbl_thanhtoan_mst =new PdfPTable(2);
			tbl_thanhtoan_mst.setWidths(withd_tbl);
			tbl_thanhtoan_mst.setWidthPercentage(100);
			//--hinh thu thanh toan
			PdfPCell cell3 = new PdfPCell();
			cell3.setBorder(0);
			cell3.setVerticalAlignment(Element.ALIGN_LEFT);
			cell3.setPaddingLeft(4.0f * CONVERT);
			para = new Paragraph( hinhthucTT, new Font(bf, 10, Font.BOLD));
			cell3.addElement(para);
			tbl_thanhtoan_mst.addCell(cell3);

			//--ma so thue
			PdfPCell cell4 = new PdfPCell();
			cell4.setBorder(0);
			cell4.setVerticalAlignment(Element.ALIGN_LEFT);
			cell4.setPaddingLeft(2.5f * CONVERT);
			para = new Paragraph(kh_MST, new Font(bf, 10, Font.BOLD));

			cell4.addElement(para);
			tbl_thanhtoan_mst.addCell(cell4);
			cell.addElement(tbl_thanhtoan_mst);
			tbl_khachhang.addCell(cell);

			document.add(tbl_khachhang);

			// Table Content---------------------------------bang du lieu----------------------------------

			float[] tbl_withd ={ 5.0f,18.0f, 60f, 30f, 10f , 15f, 25.0f, 25f };

			PdfPTable tbl_sanpham = new PdfPTable(tbl_withd.length);
			tbl_sanpham.setSpacingBefore(30.0f );
			tbl_sanpham.setWidthPercentage(100);
			tbl_sanpham.setHorizontalAlignment(Element.ALIGN_LEFT);
			tbl_sanpham.setWidths(tbl_withd);
			PdfPCell cells;

			//LAY SAN PHAM BAN
			String query = sqlIN_SANPHAM;
			ResultSet rsSP = db.get(query);

			//LAY SAN PHAM KHUYEN MAI
			ResultSet rsSPKM = db.get(sqlIN_SANPHAMKM);

			double tongtien = 0;
			String vat="";
			String tienvat= pxkBean.getTongVAT(); //lay ben hoa don
			String tongtien_vat =pxkBean.getTongtienAVAT();
			int stt = 1;
			int dongtang=0;
			try {
				//SAN PHAM BAN
				while(rsSP.next())
				{
					double soLUONG = rsSP.getDouble("soluong");
					double dongia = rsSP.getDouble("dongia");
					double thanhtien = Math.floor(soLUONG*dongia); // lam tron xuong
					tongtien +=thanhtien;
					tientruocvat+= Math.floor(soLUONG*dongia);

					String solo=rsSP.getString("solo");
					if(solo.equals("NA") || solo.equals("na"))
						solo="";
					String ma=rsSP.getString("MA");
					String ten=rsSP.getString("TEN");

					String[] arr = new String[] { Integer.toString(stt), ma , ten, solo, 
							rsSP.getString("DONVI"),
							DinhDangTraphacoDMS(formatter1.format(soLUONG)), DinhDangTraphacoDMS(formatter.format(dongia)),
							DinhDangTraphacoDMS(formatter1.format(thanhtien))+"đ" };

					//thuc hien cuon dong, neu qua nhiu ki tu
					int [] tangkitu= new int []{ (ma.length() /8),  (ten.length()/ 35),
							(solo.length()/18)	, (String.valueOf(soLUONG).length() /6), 
							(String.valueOf(dongia).length() /12),(String.valueOf(thanhtien).length() /12)};
					int max=0;
					for (int i = 0; i < tangkitu.length; i++) {
						if(tangkitu[i]>max){
							max=tangkitu[i];
						}
					}
					dongtang +=max;
					System.out.println(" max dong them " +max);


					for (int j = 0; j < tbl_withd.length; j++)
					{
						cells = new PdfPCell(new Paragraph(arr[j], new Font(bf, 10, Font.BOLD)));
						//canh format
						if(j==2 ||j==1|| j==0|| j==3){
							cells.setHorizontalAlignment(Element.ALIGN_LEFT);
							if(j==0)
							{
								cells.setPaddingLeft(-0.5f*CONVERT);
							}
						}
						else
							if(j==5 ||j==6||j==7)
							{
								cells.setHorizontalAlignment(Element.ALIGN_RIGHT);
							}
							else
							{
								cells.setHorizontalAlignment(Element.ALIGN_CENTER);
							}


						cells.setVerticalAlignment(Element.ALIGN_BOTTOM);
						cells.setBorder(0);

						tbl_sanpham.addCell(cells);	
					}
					stt++;
				}
				rsSP.close();

				//SAN PHAM KHUYEN MAI
				while(rsSPKM.next())
				{
					double soLUONG = rsSPKM.getDouble("soluong");
					double dongia = 0.0;
					double thanhtien = soLUONG*dongia;
					tongtien +=thanhtien;

					String solo=rsSP.getString("solo");
					if(solo.equals("NA") || solo.equals("na"))
						solo="";

					String ma=rsSPKM.getString("MA");
					String ten=rsSPKM.getString("TEN");
					String[] arr = new String[] { Integer.toString(stt),ma, ten, solo, 
							rsSPKM.getString("DONVI"),
							DinhDangTraphacoDMS(formatter1.format(soLUONG)),
							DinhDangTraphacoDMS(formatter.format(dongia)),
							DinhDangTraphacoDMS(formatter1.format(thanhtien)) +"đ"};

					//thuc hien cuon dong, neu qua nhiu ki tu
					int [] tangkitu= new int []{ (ma.length() /8),  (ten.length()/ 35),
							(solo.length()/18)	, (String.valueOf(soLUONG).length() /6), 
							(String.valueOf(dongia).length() /12),(String.valueOf(thanhtien).length() /12)};
					int max=0;
					for (int i = 0; i < tangkitu.length; i++) {
						if(tangkitu[i]>max){
							max=tangkitu[i];
						}
					}
					dongtang +=max;
					System.out.println(" max dong them " +max);


					for (int j = 0; j < tbl_withd.length; j++)
					{
						cells = new PdfPCell(new Paragraph(arr[j], new Font(bf, 10, Font.BOLD)));
						//canh format
						if(j==2 ||j==1 ||j==0 || j==3){
							cells.setHorizontalAlignment(Element.ALIGN_LEFT);
							if(j==0)
							{
								cells.setPaddingLeft(-0.5f*CONVERT);
							}
						}
						else
							if(j==5 ||j==6||j==7)
							{
								cells.setHorizontalAlignment(Element.ALIGN_RIGHT);
							}
							else
							{
								cells.setHorizontalAlignment(Element.ALIGN_CENTER);
							}


						cells.setVerticalAlignment(Element.ALIGN_BOTTOM);
						cells.setBorder(0);

						tbl_sanpham.addCell(cells);	
					}
					stt++;

					System.out.println(" so thu tu : "+ stt);
				}
				rsSPKM.close();



			} catch (Exception e) {
				e.printStackTrace();
			}

			// tinh tien chiet khau
			tongtienck=  tienck+ tienkm;
			tiensauck= tientruocvat- tongtienck-tientanggiam ; //lay bvat cua hoa don

			//tien truoc vat
			cells = new PdfPCell(new Paragraph(DinhDangTraphacoDMS(formatter1.format( tientruocvat))+"đ", new Font(bf, 10, Font.BOLD)));
			cells.setBorder(0);
			cells.setColspan(8);
			cells.setHorizontalAlignment(Element.ALIGN_RIGHT);
			tbl_sanpham.addCell(cells);

			// tien ck
			cells = new PdfPCell(new Paragraph(DinhDangTraphacoDMS(formatter1.format(tongtienck))+"đ", new Font(bf, 10, Font.BOLD)));
			cells.setBorder(0);
			cells.setColspan(8);
			cells.setHorizontalAlignment(Element.ALIGN_RIGHT);
			tbl_sanpham.addCell(cells);

			//tien tang giam
			cells = new PdfPCell(new Paragraph(DinhDangTraphacoDMS(formatter1.format(tientanggiam))+"đ" , new Font(bf, 10, Font.BOLD)));
			cells.setBorder(0);
			cells.setColspan(8);
			cells.setHorizontalAlignment(Element.ALIGN_RIGHT);
			tbl_sanpham.addCell(cells);

			//tien sau ck
			cells = new PdfPCell(new Paragraph( DinhDangTraphacoDMS(formatter1.format(tiensauck))+"đ", new Font(bf, 10, Font.BOLD)));
			cells.setBorder(0);
			cells.setColspan(8);
			cells.setHorizontalAlignment(Element.ALIGN_RIGHT);
			tbl_sanpham.addCell(cells);
			document.add(tbl_sanpham);


			//lay top(1) vat %
			String qr_vat="select top(1) thuevat as vat from HOADON_SP_CHITIET where hoadon_fk='"+pxkBean.getId()+"'";
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

			//-------Tao bang vat, tong tien 
			PdfPTable tbl_vat = new PdfPTable(2);
			tbl_vat.setSpacingBefore((18-stt-dongtang)*0.6f*CONVERT); // so dong trong
			tbl_vat.setWidthPercentage(100);
			tbl_vat.setHorizontalAlignment(Element.ALIGN_LEFT);
			tbl_vat.getDefaultCell().setBorder(0);
			float[] cr = { 18.0f*CONVERT, 6.0f*CONVERT };
			tbl_vat.setWidths(cr);
			PdfPCell cellss;

			//rong
			cellss = new PdfPCell(new Paragraph(" ", new Font(bf, 10, Font.BOLD)));
			cellss.setBorder(0);
			tbl_vat.addCell(cellss);

			//tong tien don hang
			cellss = new PdfPCell(new Paragraph(DinhDangTraphacoDMS(formatter1.format(tongtien))+"đ", new Font(bf, 10, Font.BOLD)));
			cellss.setBorder(0);
			cellss.setHorizontalAlignment(Element.ALIGN_RIGHT);
			tbl_vat.addCell(cellss);

			//vat
			cellss = new PdfPCell(new Paragraph( vat, new Font(bf, 10, Font.BOLD)));
			cellss.setBorder(0);
			cellss.setPaddingLeft(3.0f * CONVERT);
			cellss.setHorizontalAlignment(Element.ALIGN_LEFT);
			tbl_vat.addCell(cellss);

			//tien vat
			cellss = new PdfPCell(new Paragraph(tienvat.replaceAll(",", ".")+"đ", new Font(bf, 10, Font.BOLD)));
			cellss.setHorizontalAlignment(Element.ALIGN_RIGHT);
			cellss.setBorder(0);
			tbl_vat.addCell(cellss);

			//rong
			cellss = new PdfPCell(new Paragraph(" ", new Font(bf, 10, Font.BOLD)));
			cellss.setBorder(0);
			tbl_vat.addCell(cellss);

			//tong tien sau vat
			cellss = new PdfPCell(new Paragraph(tongtien_vat.replaceAll(",", ".")+"đ", new Font(bf, 10, Font.BOLD)));
			cellss.setBorder(0);
			cellss.setHorizontalAlignment(Element.ALIGN_RIGHT);
			tbl_vat.addCell(cellss);

			//doc tien thanh chu
			// Tien bang chu
			doctienrachu doctien = new doctienrachu();
			String tien = doctien.docTien(Long.parseLong(pxkBean.getTongtienAVAT().replaceAll(",", "")));
			String TienIN = (tien.substring(0,1)).toUpperCase() + tien.substring(1); //Viết hoa ký tự đầu tiên
			cellss.setColspan(2);
			cellss.setPaddingTop(0.6f);
			cellss = new PdfPCell(new Paragraph(TienIN, new Font(bf, 10, Font.BOLDITALIC)));
			cellss.setHorizontalAlignment(Element.ALIGN_LEFT);
			cellss.setPaddingLeft(4.0f * CONVERT);

			cellss.setBorder(0);
			tbl_vat.addCell(cellss);

			//rong
			cellss = new PdfPCell(new Paragraph(" ", new Font(bf, 10, Font.BOLD)));
			cellss.setBorder(0);
			tbl_vat.addCell(cellss);

			document.add(tbl_vat);
			document.close();

		} catch (Exception e)
		{
			System.out.println("115.Exception: " + e.getMessage());
			e.printStackTrace();
		}
	}



	private void CreatePxk(Document document, ServletOutputStream outstream, IHoadontaichinh pxkBean,String userid,String nppId ,int loai) throws IOException
	{
		dbutils db = new dbutils();
		// 1. tim don hang cua hoa don==> 2. tim lai ma hoa don (khuyen mai  loaihoadon=1, hoa don sp ban loaihoadon =0)
		//3. sp ban map vs HOADON_SP_CHITIET 
		//4. sp khuyen mai map vs HOADON_CTKM_TRAKM_CHITIET
		//lay don hang
		String iddh="";
		String qr_dh=" select ddh_fk as madh from HOADON_DDH where hoadon_fk="+pxkBean.getId();
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
		"\n where hoadon_fk = "+    
		"\n ( select a.pk_seq as mahoadon from HOADON a  inner join HOADON_DDH  b "+    
		"\n on a.PK_SEQ=b.HOADON_FK where  a.loaihoadon='0' and b.ddh_fk= '" + iddh + "' and a.trangthai not in(3,5)) "+
		"\n group by MA, TEN, DONVI, DONGIA, SOLO, NGAYHETHAN, THUEVAT,CHIETKHAU";

		System.out.println("---IN SAN PHAM: " + sqlIN_SANPHAM  +"\n");

		//danh sach san pham khuyen mai
		String sqlIN_SANPHAMKM =" select sp.MA as ma, sp.TEN as ten, dv.DONVI as donvi,km.DONGIA as dongia, km.SOLO as solo, km.NGAYHETHAN as HANDUNG,sum( km.SOLUONG ) as soluong, '' as CHIETKHAU    "+    
		"  from HOADON_CTKM_TRAKM_CHITIET km inner join sanpham sp on sp.pk_seq=km.sanpham_fk  inner join DONVIDOLUONG dv on dv.PK_SEQ=sp.DVDL_FK "+    
		"\n  where hoadon_fk= "+    
		"\n   ( select a.pk_seq as mahoadon from HOADON a  inner join HOADON_DDH  b "+    
		"\n on a.PK_SEQ=b.HOADON_FK where  a.loaihoadon='1' and b.ddh_fk='" + iddh + "' and a.trangthai not in(3,5)) "+
		"\n  group by sp.MA, sp.TEN,dv.DONVI,km.DONGIA, km.SOLO,km.NGAYHETHAN";
		System.out.println("--- \n IN SAN PHAMKM: " + sqlIN_SANPHAMKM +"\n" );
		try
		{			
			//LAY THONG TIN NCC
			String kbh="";
			String ddh="";
			String npp_fk="";
			String khId="";
			double Vat= 0;

			String ctyTen=" ";
			String cty_MST =" ";
			String cty_Diachi=" ";
			String cty_Sotaikhoan= " ";
			String cty_Dienthoai= " ";
			String cty_Fax= " ";
			String khoxuat =" ";
			String thukho = " ";
			String sql ="";

			/*sql=" select npp.PK_SEQ, npp.TEN ,npp.DIACHI as DIACHI, npp.MASOTHUE,npp.DIENTHOAI, isnull( FAX,'') as FAX, "+    
			" (select ten from tinhthanh where pk_seq=npp.tinhthanh_fk) as tinhthanh, "+    
			" (select ten from QUANHUYEN where pk_seq=npp.quanhuyen_fk) as quanhuyen, "+    
			"  isnull( npp.SOTAIKHOAN,'') as SOTAIKHOAN,isnull(npp.XUATTAIKHO,' ') as XUATTAIKHO,  "+    
			" isnull(npp.nganhang,'') as nganhang from NHAPHANPHOI npp "+    
			"where PK_SEQ = (select npp_fk from HOADON where pk_seq = '"+ pxkBean.getId() +"') ";*/

			sql=" select npp.PK_SEQ, npp.TEN ,npp.DIACHI as DIACHI, npp.MASOTHUE,npp.DIENTHOAI, isnull( FAX,'') as FAX, "+       
			"  isnull( npp.SOTAIKHOAN,'') as SOTAIKHOAN,isnull(npp.XUATTAIKHO,' ') as XUATTAIKHO,  "+    
			" isnull(npp.nganhang,'') as nganhang from NHAPHANPHOI npp "+    
			"where PK_SEQ = (select npp_fk from HOADON where pk_seq = '"+ pxkBean.getId() +"') ";

			System.out.println("\n Lay TT CTY "+sql+"\n");
			ResultSet rsINFO = db.get(sql);
			if(rsINFO.next())
			{
				khoxuat = rsINFO.getString("XUATTAIKHO");
				ctyTen = rsINFO.getString("TEN");
				cty_MST = rsINFO.getString("MASOTHUE");
				cty_Diachi = rsINFO.getString("DIACHI");

				//cty_Diachi = rsINFO.getString("DIACHI")+", "+rsINFO.getString("quanhuyen")+", "+rsINFO.getString("tinhthanh");


				cty_Sotaikhoan = rsINFO.getString("SOTAIKHOAN");

				if(rsINFO.getString("nganhang").length()>0){
					cty_Sotaikhoan+=" , "+rsINFO.getString("nganhang");
				}
				cty_Dienthoai = rsINFO.getString("DIENTHOAI");
				cty_Fax = rsINFO.getString("FAX");

				rsINFO.close();

			}

			//LAY THONG TIN KHACHHANG  otc lay tu khach hang

			String Donvi=" ";
			String kh_MST =" ";
			String kh_Diachi=" ";
			String hinhthucTT= " ";
			String ngayxuatHD= " ";
			String chucuahieu = " ";
			String	kh_dienthoai=" ";

			/*sql="select kh.pk_seq, kh.TEN as donvi,isnull(kh.chucuahieu,'') as chucuahieu, kh.diachi as diachi ,isnull(masothue,'') as masothue, '' as taikhoan, dienthoai as dienthoai,   "+    
			"(select ten from tinhthanh where pk_seq=kh.tinhthanh_fk) as tinhthanh, "+    
			"(select ten from QUANHUYEN where pk_seq=kh.quanhuyen_fk) as quanhuyen "+    
			" from KHACHHANG kh where kh.pk_seq=(select khachhang_fk from HOADON where pk_seq ="+pxkBean.getId()+") ";

			 */
			sql="select kh.pk_seq, kh.TEN as donvi,isnull(kh.chucuahieu,'') as chucuahieu, kh.diachi as diachi ,isnull(masothue,'') as masothue, '' as taikhoan, dienthoai as dienthoai  \n "+    

			" from KHACHHANG kh where kh.pk_seq=(select khachhang_fk from HOADON where pk_seq ="+pxkBean.getId()+") ";


			String kh_sotaikhoan = "";
			System.out.println("\n Lay TT KH "+sql +"\n");
			ResultSet rsLayKH= db.get(sql);
			if(rsLayKH!=null){
				try {
					while(rsLayKH.next())
					{
						Donvi = rsLayKH.getString("donvi");
						kh_MST = rsLayKH.getString("MASOTHUE");
						kh_Diachi = rsLayKH.getString("DIACHI");
						//kh_Diachi = rsLayKH.getString("DIACHI")+", "+rsLayKH.getString("quanhuyen")+", "+rsLayKH.getString("tinhthanh");
						kh_dienthoai=rsLayKH.getString("dienthoai");
						kh_sotaikhoan=rsLayKH.getString("TAIKHOAN");
						chucuahieu = rsLayKH.getString("chucuahieu");
					}   
					rsLayKH.close();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}


			sql = "select nguoimua as nguoimua, hinhthuctt as hinhthuctt, ngayxuatHD as ngayxuatHD from hoadon where PK_SEQ="+pxkBean.getId();
			ResultSet rshoadon= db.get(sql);
			if(rshoadon!=null){
				try {
					while(rshoadon.next())
					{
						hinhthucTT = rshoadon.getString("hinhthuctt");	
						ngayxuatHD = rshoadon.getString("ngayxuatHD");
						//chucuahieu = rshoadon.getString("nguoimua"); // nguoi mua hang luu trong hoa don
					}   
					rshoadon.close();
				} catch (Exception e) {
					e.printStackTrace();

				}
			}

			System.out.println("\n  chu cua hieu " + sql);
			//lay thong tin phieuxuatkho
			String phieuxuatkho=" ";
			sql="select b.sonetid as sophieuxuat from PHIEUXUATKHO_DONHANG a "+    
			"\n inner join PHIEUXUATKHO b on a.pxk_fk =b.pk_seq "+    
			"\n where a.hoadon_fk=" +pxkBean.getId();

			System.out.println(" qr phieuxuat kho: "+sql);
			ResultSet rsSoxuatkho= db.get(sql);
			if(rsSoxuatkho!=null){
				try {
					while(rsSoxuatkho.next())
					{
						phieuxuatkho=rsSoxuatkho.getString("sophieuxuat");
					}  
					rsSoxuatkho.close();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}

			//doi khoang trang neu khong co data
			ctyTen=doikhoangtrang(ctyTen);
			cty_MST=doikhoangtrang(cty_MST);
			cty_Diachi=doikhoangtrang(cty_Diachi);
			cty_Sotaikhoan=doikhoangtrang(cty_Sotaikhoan);
			cty_Dienthoai=doikhoangtrang(cty_Dienthoai);
			cty_Fax=doikhoangtrang(cty_Fax);
			Donvi=doikhoangtrang(Donvi);
			kh_MST=doikhoangtrang(kh_MST);
			hinhthucTT=doikhoangtrang(hinhthucTT);
			chucuahieu=doikhoangtrang(chucuahieu);


			//====================================KHU VUC IN =====================================//

			System.out.println("\n  chu cua hieu :"+ chucuahieu +"\n");
			if(loai==1){
				if(nppId.equals("100065") || nppId.equals("100087")) // mau danh cho vinh long va tien giang
					form_hoadon2(document, outstream, sqlIN_SANPHAM,sqlIN_SANPHAMKM, pxkBean,  ctyTen, cty_MST,
							cty_Diachi, cty_Sotaikhoan, cty_Dienthoai, cty_Fax, Donvi, kh_MST, hinhthucTT, ngayxuatHD, chucuahieu, 
							kh_sotaikhoan, kh_Diachi, iddh,userid,phieuxuatkho, db);

				//in hoa don cho cac chi nhanh khac
				else
					form_hoadon(document, outstream, sqlIN_SANPHAM,sqlIN_SANPHAMKM, pxkBean,  ctyTen, cty_MST,
							cty_Diachi, cty_Sotaikhoan, cty_Dienthoai, cty_Fax, Donvi, kh_MST, hinhthucTT, ngayxuatHD, chucuahieu, 
							kh_sotaikhoan, kh_Diachi, iddh,userid,phieuxuatkho, db);

			}

			if(loai==2){
				//in phieu giao hang
				form_phieugiaohang(document, outstream, sqlIN_SANPHAM,sqlIN_SANPHAMKM, pxkBean, ctyTen, cty_MST,
						cty_Diachi, cty_Sotaikhoan, cty_Dienthoai, cty_Fax, Donvi, kh_MST,chucuahieu, 
						kh_sotaikhoan, kh_Diachi,kh_dienthoai,db);
			}


		} catch (Exception e)
		{
			System.out.println("115.Exception: " + e.getMessage());
			e.printStackTrace();
		}
	}


	private String catchuoi50(String chuoi){
		String s="";
		int dodai=chuoi.length();

		return s;
	}



	private String DinhDangTraphacoDMS(String sotien)
	{
		sotien = sotien.replaceAll("\\.", "_");
		sotien = sotien.replaceAll(",", "\\.");
		sotien = sotien.replaceAll("_", ",");

		return sotien;
	}




	private void form_phieugiaohang(Document document,ServletOutputStream outstream,String sqlIN_SANPHAM,String sqlIN_SANPHAMKM,
			IHoadontaichinh pxkBean,String ctyTen,String cty_MST,String cty_Diachi,
			String cty_Sotaikhoan,String cty_Dienthoai,String cty_Fax,String Donvi,String kh_MST,
			String chucuahieu,String kh_sotaikhoan, String kh_Diachi,String kh_dienthoai, dbutils db){

		//thong tin don hang 
		String sohopdong="             ";
		String ngayhopdong="                  ";
		String dothopdong="           ";
		String sohoadon=pxkBean.getSohoadon();
		String ngayhoadon=pxkBean.getNgayxuatHD();

		//LAY danh sach SAN PHAM BAN/ san pham khuyen mai
		ResultSet rsSP = db.get(sqlIN_SANPHAM);
		ResultSet rsSPKM = db.get(sqlIN_SANPHAMKM);
		double tongtien = 0;
		String vat="";
		String tienvat= pxkBean.getTongVAT();
		String tongtien_vat =pxkBean.getTongtienAVAT();
		double tienhang = Double.parseDouble(pxkBean.getTongtienAVAT().replaceAll(",", ""))-Double.parseDouble( pxkBean.getTongCK().replaceAll(",", ""));


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
			Image img = Image.getInstance(getServletContext().getInitParameter("path")+ "/dms.jpg");
			img.scalePercent(15);
			cell = new PdfPCell();
			cell.addElement(new Chunk(img,30,-10)); // vij tri dat anh 
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
			chunk=new Chunk(ctyTen,font10_normal);
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
			para=new Paragraph("BIÊN BẢN GIAO HÀNG",new Font(bf,16,Font.BOLD));
			para.setAlignment(Element.ALIGN_CENTER);
			cell.addElement(para);
			tableheader.addCell(cell);

			// dong 5 hop dong
			cell = new PdfPCell();
			cell.setBorder(0);
			cell.setColspan(2);
			cell.setHorizontalAlignment(Element.ALIGN_CENTER);
			cell.setVerticalAlignment(Element.ALIGN_BOTTOM);
			para=new Paragraph("Theo hợp đồng số: "+ sohopdong+ "  ngày: "+ngayhopdong +"    -Đợt:",font10_ilatic);
			para.setAlignment(Element.ALIGN_CENTER);
			cell.addElement(para);
			tableheader.addCell(cell);


			// dong 6 hoa don
			cell = new PdfPCell();
			cell.setBorder(0);
			cell.setColspan(2);
			cell.setHorizontalAlignment(Element.ALIGN_CENTER);
			para=new Paragraph("Theo hóa đơn số: "+sohoadon.trim()+"  ngày:  "+ngayhoadon.substring(8)+"/"+ngayhoadon.substring(5,7)+"/"+ngayhoadon.substring(0,4),font10_ilatic);
			para.setAlignment(Element.ALIGN_CENTER);
			cell.addElement(para);
			tableheader.addCell(cell);

			// dong 7 dms
			cell = new PdfPCell();
			cell.setBorder(0);
			cell.setColspan(2);
			cell.setHorizontalAlignment(Element.ALIGN_CENTER);
			para=new Paragraph("Số phiếu: "+pxkBean.getId()+"  ngày: "+ ngayhoadon.substring(8)+"/"+ngayhoadon.substring(5,7)+"/"+ngayhoadon.substring(0,4) ,font10_ilatic);
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
			para=new Paragraph("Hôm nay, ngày "+ getDate()+". Chúng tôi gồm có:",font10_normal);
			para.setAlignment(Element.ALIGN_LEFT);
			cell.addElement(para);
			para=new Paragraph("BÊN BÁN (BÊN A): "+ ctyTen,font10_Bold);
			para.setAlignment(Element.ALIGN_LEFT);
			cell.addElement(para);
			tbl.addCell(cell);


			// thong tin ben a
			cell = new PdfPCell();
			cell.setBorder(0);
			cell.setPaddingLeft(1.5f*CONVERT);
			cell.setHorizontalAlignment(Element.ALIGN_LEFT);

			para=new Paragraph("- Địa chỉ: "+cty_Diachi,font10_normal);
			para.setAlignment(Element.ALIGN_LEFT);
			cell.addElement(para);

			para=new Paragraph("- Mã số thuế: "+cty_MST,font10_normal);
			para.setAlignment(Element.ALIGN_LEFT);
			cell.addElement(para);

			para=new Paragraph("- Do ông/bà:                                                                                  Làm đại diện",font10_normal);
			para.setAlignment(Element.ALIGN_LEFT);
			cell.addElement(para);

			para=new Paragraph("- Điện thoại: "+cty_Dienthoai,font10_normal);
			para.setAlignment(Element.ALIGN_LEFT);
			cell.addElement(para);
			tbl.addCell(cell);

			// dong 
			cell = new PdfPCell();
			cell.setBorder(0);
			cell.setHorizontalAlignment(Element.ALIGN_LEFT);
			para=new Paragraph("BÊN MUA (BÊN B): "+Donvi.toUpperCase(),font10_Bold);
			para.setAlignment(Element.ALIGN_LEFT);
			cell.addElement(para);
			tbl.addCell(cell);

			// thong tin ben b
			cell = new PdfPCell();
			cell.setBorder(0);
			cell.setPaddingLeft(1.5f*CONVERT);
			cell.setHorizontalAlignment(Element.ALIGN_LEFT);

			para=new Paragraph("- Địa chỉ: "+kh_Diachi,font10_normal);
			para.setAlignment(Element.ALIGN_LEFT);
			cell.addElement(para);

			para=new Paragraph("- Mã số thuế: "+kh_MST,font10_normal);
			para.setAlignment(Element.ALIGN_LEFT);
			cell.addElement(para);

			para=new Paragraph("- Do ông/bà:                                                                                  Làm đại diện",font10_normal);
			para.setAlignment(Element.ALIGN_LEFT);
			cell.addElement(para);

			para=new Paragraph("- Điện thoại: "+ kh_dienthoai,font10_normal);
			para.setAlignment(Element.ALIGN_LEFT);
			cell.addElement(para);
			tbl.addCell(cell);

			// dong 
			cell = new PdfPCell();
			cell.setBorder(0);
			cell.setHorizontalAlignment(Element.ALIGN_LEFT);
			para=new Paragraph("         Hai bên nhất trí xác nhận bên A đã giao hàng tại kho của bên B. "+
					"Hai bên cùng tiến hàng nghiệm thu một phần hợp đồng số: "+ sohopdong +" ngày "+ ngayhopdong+
					". Hàng hóa đã được giao đúng số lượng, cụ thể như sau:",font10_normal);
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
			int stt = 1;
			try {
				while(rsSP.next())
				{
					double soLUONG = rsSP.getDouble("soluong");
					double dongia = rsSP.getDouble("dongia");
					double thanhtien = soLUONG*dongia;
					//lay han su dung theo mau MM/yy

					/*String handung="";
					if(rsSP.getString("handung").length()>0){
						handung=rsSP.getString("handung").substring(5,7)+"/"+rsSP.getString("handung").substring(2,4);
					}*/

					tongtien +=thanhtien;
					String[] arr = new String[] { Integer.toString(stt), rsSP.getString("TEN"), 
							rsSP.getString("DONVI"),DinhDangTraphacoDMS(formatter.format(dongia)),
							DinhDangTraphacoDMS(formatter1.format(soLUONG)), DinhDangTraphacoDMS(formatter1.format(thanhtien)),
							rsSP.getString("solo")," "};


					for (int j = 0; j < tbl_withd.length; j++)
					{
						cells = new PdfPCell(new Paragraph(arr[j], new Font(bf, 10, Font.NORMAL)));

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
						tbl_sanpham.addCell(cells);	
					}
					stt++;
				}
				rsSP.close();

				//SAN PHAM KHUYEN MAI
				while(rsSPKM.next())
				{
					double soLUONG = rsSPKM.getDouble("soluong");
					double dongia = 0.0;
					double thanhtien = soLUONG*dongia;
					tongtien +=thanhtien;
					String handung="";
					if(rsSPKM.getString("handung").length()>0){
						handung=rsSPKM.getString("handung").substring(5,7)+"/"+rsSPKM.getString("handung").substring(2,4);
					}
					String[] arr = new String[] { Integer.toString(stt) , rsSPKM.getString("TEN"), 
							rsSPKM.getString("DONVI"),DinhDangTraphacoDMS(formatter.format(dongia)),
							DinhDangTraphacoDMS(formatter1.format(soLUONG)), 
							DinhDangTraphacoDMS(formatter1.format(thanhtien)),
							rsSPKM.getString("solo")+"-"+handung, ""};


					for (int j = 0; j < tbl_withd.length; j++)
					{
						cells = new PdfPCell(new Paragraph(arr[j], new Font(bf, 10, Font.NORMAL)));

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
						cells.setVerticalAlignment(Element.ALIGN_BOTTOM);	
						tbl_sanpham.addCell(cells);
					}
					stt++;
				}
				rsSPKM.close();

			} catch (Exception e) {
				e.printStackTrace();
			}



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
			para = new Paragraph(DinhDangTraphacoDMS(formatter1.format(tongtien))+" đồng" , font10_normal);
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
			para = new Paragraph(pxkBean.getTongCK().replaceAll(",", ".") + " đồng" ,font10_normal);
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

			//tien hang
			cell = new PdfPCell();
			cell.setBorderWidthBottom(0);
			cell.setBorderWidthRight(0);
			cell.setBorderWidthTop(0);
			cell.setColspan(4);
			cell.setVerticalAlignment(Element.ALIGN_LEFT);
			cell.setPaddingLeft(6.3f * CONVERT);
			para = new Paragraph("Tiền hàng ", font10_normal);
			cell.addElement(para);
			tbl_sanpham.addCell(cell); 

			cell = new PdfPCell();
			cell.setBorder(0);
			cell.setColspan(2);
			cell.setVerticalAlignment(Element.ALIGN_RIGHT);
			cell.setPaddingLeft(0.5f * CONVERT);
			para = new Paragraph((formatter1.format(tienhang)).replaceAll(",", ".")+ " đồng" ,font10_normal);
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
			para = new Paragraph(tienvat.replaceAll(",", ".") +" đồng" , font10_normal);
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
			para = new Paragraph(tongtien_vat.replaceAll(",", ".")+" đồng" ,font10_normal);
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
			para = new Paragraph("Tổng cộng  "+ (stt-1) + "  Khoản. Gồm  .................  thùng (kiện), ................... hộp (chai).", font10_normal);
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
			para.add(new Chunk(" Khác  .........................................................................................", font10_normal ));
			cell.addElement(para);
			tbl_footer.addCell(cell);

			cell = new PdfPCell();
			cell.setBorder(0);
			para = new Paragraph("Biên bản được lập thành   ...   bản, mỗi bên giữ   ...   bản và có giá trị như nhau.", font10_normal);
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



	private void form_hoadon2(Document document,ServletOutputStream outstream,String sqlIN_SANPHAM,String sqlIN_SANPHAMKM,IHoadontaichinh pxkBean,String ctyTen,String cty_MST,String cty_Diachi,
			String cty_Sotaikhoan,String cty_Dienthoai,String cty_Fax,String Donvi,String kh_MST,
			String hinhthucTT,String ngayxuatHD,String chucuahieu,String kh_sotaikhoan, String kh_Diachi,
			String iddh, String userid, String phieuxuatkho,dbutils db)
	{

		// lay thong tin don hang ban
		IDonhang dhBean = new Donhang(iddh);
		dhBean.setUserId(userid); // phai co UserId truoc khi Init			
		dhBean.init();

		double tientruocvat= 0;
		double tienck=0;
		double tientanggiam=Double.parseDouble(dhBean.getTiengiamtru());
		double tienkm=0;
		double tongtienck=0; // tien ck = ck cung + ck khuyen mai
		double tiensauck=0;


		//tim tien km 
		String qr_km="select isnull( sum(tonggiatri),0) as tienkm from donhang_ctkm_trakm  where spma is null and donhangid="+iddh;
		ResultSet rsKM=db.get(qr_km);
		if(rsKM!=null){
			try {
				while(rsKM.next()){
					tienkm=rsKM.getDouble("tienkm");
				}
				rsKM.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		System.out.println(" tien km: "+ tienkm +"\n");

		//tien ck
		String qr_ck="select sum(soluong * dongia * (chietkhaungay/100)) as tienckngay from HOADON_SP where hoadon_fk="+pxkBean.getId();
		ResultSet rsCK=db.get(qr_ck);
		if(rsCK!=null){
			try {
				while(rsCK.next()){
					tienck=rsCK.getDouble("tienckngay");
				}
				rsCK.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		System.out.println(" tien chiet khau: "+ tienck +"\n");
		String mahoadon=pxkBean.getId();

		try
		{
			NumberFormat formatter = new DecimalFormat("#,###,###.##");
			NumberFormat formatter1 = new DecimalFormat("#,###,###");

			document.setPageSize(PageSize.A4);
			document.setMargins(1f*CONVERT, 1f*CONVERT, 0.8f*CONVERT, 0.5f*CONVERT); // L,R,T,B
			PdfWriter writer = PdfWriter.getInstance(document, outstream);

			PdfPCell cell ;
			Paragraph para;
			Chunk chunk;
			document.open() ;

			BaseFont bf = BaseFont.createFont("c:\\windows\\fonts\\times.ttf", BaseFont.IDENTITY_H, BaseFont.EMBEDDED);
			Font font = new Font(bf, 13, Font.BOLD);
			Font font2 = new Font(bf, 8, Font.BOLD);


			PdfPTable tableheader =new PdfPTable(2);
			float []wky={17.0f,2.0f};
			tableheader.setWidthPercentage(100);	
			tableheader.setWidths(wky);

			cell = new PdfPCell();
			cell.setBorder(0);
			cell.setPaddingTop(4.0f*CONVERT);
			cell.setPaddingLeft(1.7f*CONVERT);
			cell.setHorizontalAlignment(Element.ALIGN_CENTER);
			String [] ngayHD = ngayxuatHD.split("-");
			para= new Paragraph("",new Font(bf, 8, Font.BOLD));
			if(ngayHD.length>=3){
				chunk =new Chunk(ngayHD[2] + "                     " + ngayHD[1] +"                   " + ngayHD[0],new Font(bf, 8, Font.BOLDITALIC));
				chunk.setTextRise(0.5f*CONVERT);
				para.add(chunk);
				para.setAlignment(Element.ALIGN_CENTER);

			}
			cell.addElement(para);
			tableheader.addCell(cell);

			//phieu xuat kho
			cell = new PdfPCell();
			cell.setBorder(0);
			cell.setPaddingTop(4f*CONVERT);
			cell.setHorizontalAlignment(Element.ALIGN_CENTER);
			para=new Paragraph(phieuxuatkho,new Font(bf, 10, Font.BOLD));
			cell.addElement(para);
			tableheader.addCell(cell);

			document.add(tableheader);

			// ================================ADD INFO NHA PHAN PHOI
			PdfPTable tbl_npp =new PdfPTable(1);
			tbl_npp.setWidthPercentage(100);

			//--dong1 ten npp
			cell = new PdfPCell();
			cell.setBorder(0);
			cell.setPaddingTop(-0.8f * CONVERT);
			cell.setVerticalAlignment(Element.ALIGN_LEFT);
			cell.setPaddingLeft(3.0f * CONVERT);
			para = new Paragraph( ctyTen  +" ", new Font(bf, 10, Font.BOLD));
			cell.addElement(para);
			tbl_npp.addCell(cell);


			//--dong2 ma dia chi npp
			cell = new PdfPCell();
			cell.setBorder(0);
			cell.setVerticalAlignment(Element.ALIGN_LEFT);
			cell.setPaddingLeft(1.5f * CONVERT);
			cell.setPaddingTop(-0.3f * CONVERT);
			para = new Paragraph( cty_Diachi +" ", new Font(bf, 10, Font.BOLD));
			cell.addElement(para);
			tbl_npp.addCell(cell);

			//--dong3 Stk npp + ngan hang
			if(cty_Sotaikhoan.equals("0"))
			{
				cty_Sotaikhoan="";
			}
			cell = new PdfPCell();
			cell.setBorder(0);
			cell.setVerticalAlignment(Element.ALIGN_LEFT);
			cell.setPaddingLeft(2.5f * CONVERT);
			cell.setPaddingTop(-0.1f * CONVERT);
			para = new Paragraph( cty_Sotaikhoan +" ", new Font(bf, 10, Font.BOLD));
			cell.addElement(para);
			tbl_npp.addCell(cell);

			document.add(tbl_npp);

			//----------------------ADD INFO KHACH HANG-----------------------------
			PdfPTable tbl_khachhang =new PdfPTable(1);
			tbl_khachhang.setWidthPercentage(100);

			//--dong0 ten nguoi mua hang (CHU CUA HIEU)
			cell = new PdfPCell();
			cell.setBorder(0);
			cell.setVerticalAlignment(Element.ALIGN_LEFT);
			cell.setPaddingLeft(4.3f * CONVERT);
			para = new Paragraph(chucuahieu  +" ", new Font(bf, 10, Font.BOLD));
			cell.addElement(para);
			tbl_khachhang.addCell(cell);

			//--dong1 ten khach hang
			cell = new PdfPCell();
			cell.setBorder(0);
			cell.setVerticalAlignment(Element.ALIGN_LEFT);
			cell.setPaddingLeft(2.0f * CONVERT);
			para = new Paragraph( Donvi  +" ", new Font(bf, 10, Font.BOLD));
			cell.addElement(para);
			tbl_khachhang.addCell(cell);


			//--dong2 dia chi 
			cell = new PdfPCell();
			cell.setBorder(0);
			cell.setPaddingTop(-0.05f * CONVERT);
			cell.setVerticalAlignment(Element.ALIGN_LEFT);
			cell.setVerticalAlignment(Element.ALIGN_LEFT);
			cell.setPaddingLeft(2.0f * CONVERT);
			para = new Paragraph( kh_Diachi  +" ", new Font(bf, 10, Font.BOLD));
			cell.addElement(para);
			tbl_khachhang.addCell(cell);


			//--dong3  so tai khoan khach hang + noi
			cell = new PdfPCell();
			cell.setBorder(0);
			cell.setVerticalAlignment(Element.ALIGN_LEFT);
			cell.setVerticalAlignment(Element.ALIGN_LEFT);
			cell.setPaddingLeft(2.0f * CONVERT);
			if(kh_sotaikhoan.length()<=0){
				kh_sotaikhoan="           ";
			}
			para = new Paragraph( kh_sotaikhoan  +" ", new Font(bf, 10, Font.BOLD));
			cell.addElement(para);
			tbl_khachhang.addCell(cell);


			float[] withd_tbl= {16.0f,8.0f};
			//--dong4 hinh thuc thanh toan va ma so thue
			cell = new PdfPCell();
			cell.setBorder(0);
			cell.setPaddingTop(-0.4f * CONVERT);
			cell.setVerticalAlignment(Element.ALIGN_LEFT);
			//cell add bang 2 cot
			PdfPTable tbl_thanhtoan_mst =new PdfPTable(2);
			tbl_thanhtoan_mst.setWidths(withd_tbl);
			tbl_thanhtoan_mst.setWidthPercentage(100);
			//--hinh thu thanh toan
			PdfPCell cell3 = new PdfPCell();
			cell3.setBorder(0);
			cell3.setVerticalAlignment(Element.ALIGN_LEFT);
			cell3.setPaddingLeft(3.0f * CONVERT);
			para = new Paragraph( hinhthucTT  +" ", new Font(bf, 10, Font.BOLD));
			cell3.addElement(para);
			tbl_thanhtoan_mst.addCell(cell3);

			//--ma so thue
			PdfPCell cell4 = new PdfPCell();
			cell4.setBorder(0);
			cell4.setVerticalAlignment(Element.ALIGN_LEFT);
			cell4.setPaddingLeft(2.5f * CONVERT);
			para = new Paragraph(kh_MST +" ", new Font(bf, 10, Font.BOLD));

			cell4.addElement(para);
			tbl_thanhtoan_mst.addCell(cell4);
			cell.addElement(tbl_thanhtoan_mst);
			tbl_khachhang.addCell(cell); 

			document.add(tbl_khachhang);
			
			
			
			
			PdfPTable tbl_tong = new PdfPTable(1);
			tbl_tong.setSpacingBefore(2.0f*CONVERT);
			tbl_tong.setWidthPercentage(110f);
			cell = new PdfPCell();
			cell.setBorder(0);
			cell.setPaddingLeft(0.5f*CONVERT);
			
			// Table Content---------------------------------bang du lieu----------------------------------
			//float[] tbl_withd = { 5.0f,18.0f, 60f, 30f, 10f , 12f, 17.0f, 25f,10f };
			float[] tbl_withd = { 5.0f,18.0f, 60f, 25f, 10f , 10f, 17.0f, 35f,10f };
			PdfPTable tbl_sanpham = new PdfPTable(tbl_withd.length);
			tbl_sanpham.setSpacingBefore(2.0f*CONVERT);
			tbl_sanpham.setWidthPercentage(100f);
			/*tbl_sanpham.setWidthPercentage(108f);*/
			tbl_sanpham.setHorizontalAlignment(Element.ALIGN_LEFT);
			tbl_sanpham.setWidths(tbl_withd);
			PdfPCell cells;

			//LAY SAN PHAM BAN
			String query = sqlIN_SANPHAM;
			ResultSet rsSP = db.get(query);

			//LAY SAN PHAM KHUYEN MAI
			ResultSet rsSPKM = db.get(sqlIN_SANPHAMKM);

			double tongtien = 0;
			String vat="";
			String tienvat= pxkBean.getTongVAT(); //lay ben hoa don
			String tongtien_vat =pxkBean.getTongtienAVAT();
			int stt = 1;
			int dongtang=1;

			try {

				//==============

				String chuoi1="",chuoi2="",chuoicd="";
				int vitri1=0;

				while(rsSP.next())
				{
					double soLUONG = rsSP.getDouble("soluong");
					double dongia = rsSP.getDouble("dongia");
					double thanhtien = Math.floor(soLUONG*dongia); // lam tron xuong
					tongtien +=thanhtien;
					tientruocvat+= Math.floor(soLUONG*dongia);
					String solo=rsSP.getString("solo").trim();
					if(solo.equals("NA") || solo.equals("na"))
						solo="";
					String ma=rsSP.getString("MA");
					String ten=rsSP.getString("TEN");


					//-- tinh phan tram chiet khau khuyen mai cua hang ban
					String pt_ck=" ";
					String qr="select  SANPHAM_FK ,ma, SUM(CHIETKHAU) CHIETKHAU \n  "+    
					"from \n  "+    
					"( \n  "+    
					"select DISTINCT  hd.SANPHAM_FK,sp.ma, tkm.CHIETKHAU from HOADON_SP hd \n  "+    
					"inner join HOADON_DDH  x on hd.HOADON_FK = x.HOADON_FK \n  "+    
					"inner join DONHANG_CTKM_TRAKM dh on dh.DONHANGID = x.DDH_FK \n  "+    
					"inner join CTKHUYENMAI ctkm on ctkm.PK_SEQ = dh.CTKMID \n  "+    
					"inner join CTKM_DKKM xkm on xkm.CTKHUYENMAI_FK = dh.CTKMID \n  "+    
					"inner join DIEUKIENKM_SANPHAM dksp on dksp.DIEUKIENKHUYENMAI_FK = xkm.DKKHUYENMAI_FK and dksp.SANPHAM_FK = hd.SANPHAM_FK \n  "+    
					"inner join TRAKHUYENMAI tkm on dh.TRAKMID = tkm.PK_SEQ and tkm.LOAI = 2  \n  "+    
					"inner join sanpham sp on hd.SANPHAM_FK=sp.PK_SEQ  \n  "+    
					"where hd.HOADON_FK = "+     pxkBean.getId() +" \n  "+
					")kq \n  "+    
					"where ma='"+ma +"' \n"+ 
					"group by SANPHAM_FK, ma ";

					ResultSet rsck=db.get(qr);
					if(rsck!=null){
						try {
							while(rsck.next()){
								pt_ck =rsck.getString("CHIETKHAU") +"%";
							}
						} catch (Exception e) {
							e.printStackTrace();
						}

					}
					//-- 

					String[] arr = new String[] { Integer.toString(stt),ma , ten, solo, 
							rsSP.getString("DONVI"),
							DinhDangTraphacoDMS(formatter1.format(soLUONG)), DinhDangTraphacoDMS(formatter.format(dongia)),
							DinhDangTraphacoDMS(formatter1.format(thanhtien))+"đ", pt_ck };

					//in
					for (int j = 0; j < tbl_withd.length; j++)
					{

						// NẾU LÀ TÊN SẢN PHẨM
						if(j==2)
						{
							if(arr[2].length() <= 35)
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

						
						//dich vi tri 24/10
						/*if(j==1 || j==0 ){
							
							cells.setPaddingLeft(-0.5f*CONVERT);
						}
						else
						if(j==2 ){
							
							cells.setPaddingLeft(-0.7f*CONVERT);
						}
						else
							if(j==5 ||j==6){
								cells.setPaddingLeft(-1.0f*CONVERT);
							}
							else
								if( j==3 ||j==4 ){
									cells.setPaddingLeft(-0.5f*CONVERT);
								}*/
						
						
						
						

						if(j==2 ||j==1 || j==0 || j==3){
							cells.setHorizontalAlignment(Element.ALIGN_LEFT);
							
						}
						else
							if(j==4)
							{								
								cells.setHorizontalAlignment(Element.ALIGN_CENTER);
							}
							else
							{
								cells.setHorizontalAlignment(Element.ALIGN_RIGHT);
							}
						
						
						
						
						cells.setVerticalAlignment(Element.ALIGN_BOTTOM);
						cells.setFixedHeight(0.6f*CONVERT);
						cells.setBorder(0);
						tbl_sanpham.addCell(cells);

					}
					stt++; 
					dongtang++;



					if(arr[2].length() > 35)
					{
						System.out.println("dai hon 35 kt ");
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
								if(m==2)
									cells = new PdfPCell(new Paragraph(chuoicd, new Font(bf, 10, Font.BOLD)));
								else
									cells = new PdfPCell(new Paragraph("", new Font(bf, 10, Font.BOLD)));

								if(m==2 ||m==1 || m==0 || m==3){
									cells.setHorizontalAlignment(Element.ALIGN_LEFT);
									if(m==0){

										cells.setPaddingRight(-0.1f*CONVERT);
									}
								}
								else
									if(m==5 ||m==6||m==7)
									{
										cells.setHorizontalAlignment(Element.ALIGN_RIGHT);
										
									}
									else
									{
										cells.setHorizontalAlignment(Element.ALIGN_CENTER);
									}


								//dich vi tri 24/10
								/*if(m==2 ||m==1 || m==0 ){
									cells.setPaddingLeft(-1.0f*CONVERT);
								}
								else
									if(m==3 ||m==4 || m==5 ||m==6){
										cells.setPaddingLeft(-0.5f*CONVERT);
									}*/
								
								cells.setVerticalAlignment(Element.ALIGN_BOTTOM);
								cells.setFixedHeight(0.6f*CONVERT);
								cells.setBorder(0);
								tbl_sanpham.addCell(cells);
							}
							dodaichuoi = chuoi2.length();

							dongtang++;
							System.out.println(" nhay 1");

						}

					}

					//het if
				}
				rsSP.close();

				System.out.println(" so thu tu sau hang bang : "+ stt);
				//======================================

				//SAN PHAM KHUYEN MAI
				while(rsSPKM.next())
				{
					double soLUONG = rsSPKM.getDouble("soluong");
					double dongia = 0.0;
					double thanhtien = soLUONG*dongia;
					tongtien +=thanhtien;

					String solo=rsSPKM.getString("solo").trim();
					if(solo.equals("NA") || solo.equals("na"))
						solo="";
					String ma=rsSPKM.getString("MA");
					String ten=rsSPKM.getString("TEN");


					String[] arr = new String[] { Integer.toString(stt), ma , ten, solo, 
							rsSPKM.getString("DONVI"),
							DinhDangTraphacoDMS(formatter1.format(soLUONG)),
							DinhDangTraphacoDMS(formatter1.format(dongia)),
							DinhDangTraphacoDMS(formatter1.format(thanhtien)) +"đ", " "};

					//in
					for (int j = 0; j < tbl_withd.length; j++)
					{

						// NẾU LÀ TÊN SẢN PHẨM
						if(j==2)
						{
							if(arr[2].trim().length() <= 35)
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


						if(j==2 ||j==1 || j==0 || j==3){
							cells.setHorizontalAlignment(Element.ALIGN_LEFT);
							if(j==0){
								//cells.setPaddingLeft(-0.8f*CONVERT);
								cells.setPaddingRight(-0.1f*CONVERT);
							}
						}
						else
							if(j==5 ||j==6||j==7)
							{
								cells.setHorizontalAlignment(Element.ALIGN_RIGHT);
								
							}
							else
							{
								cells.setHorizontalAlignment(Element.ALIGN_CENTER);
							}

						
						//dich vi tri 24/10
						/*if(j==2 ||j==1 || j==0 ){
							cells.setPaddingLeft(-1.0f*CONVERT);
						}
						else
							if(j==3 ||j==4 || j==5 ||j==6){
								cells.setPaddingLeft(-0.5f*CONVERT);
							}*/

						cells.setVerticalAlignment(Element.ALIGN_BOTTOM);
						cells.setFixedHeight(0.6f*CONVERT);
						cells.setBorder(0);
						tbl_sanpham.addCell(cells);

					}
					stt++; 
					dongtang++;

					if(arr[2].trim().length() > 35)
					{
						System.out.println("dai hon 35 kt ");
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
								if(m==2)
									cells = new PdfPCell(new Paragraph(chuoicd, new Font(bf, 10, Font.BOLD)));
								else
									cells = new PdfPCell(new Paragraph("", new Font(bf, 10, Font.BOLD)));

								if(m==2 ||m==1 || m==0 || m==3){
									cells.setHorizontalAlignment(Element.ALIGN_LEFT);
									if(m==0){

										cells.setPaddingRight(-0.1f*CONVERT);
									}
								}
								else
									if(m==5 ||m==6||m==7)
									{
										cells.setHorizontalAlignment(Element.ALIGN_RIGHT);
										
									}
									else
									{
										cells.setHorizontalAlignment(Element.ALIGN_CENTER);
									}

								
								
								//dich vi tri 24/10
								/*if(m==2 ||m==1 || m==0 ){
									cells.setPaddingLeft(-1.0f*CONVERT);
								}
								else
									if(m==3 ||m==4 || m==5 ||m==6){
										cells.setPaddingLeft(-0.5f*CONVERT);
									}*/

								cells.setVerticalAlignment(Element.ALIGN_BOTTOM);
								cells.setFixedHeight(0.6f*CONVERT);
								cells.setBorder(0);
								tbl_sanpham.addCell(cells);
							}
							dodaichuoi = chuoi2.length();
							dongtang++;
							System.out.println(" nhay ");

						}
					}

				}
				rsSPKM.close();



			} catch (Exception e) {
				e.printStackTrace();
			}

			System.out.println(" so thu tu sau km : "+ stt);

			//1 dong trong
			String[] arr_bosung1 = new String[] {" ", " " , " ", " "," ", " "," "," " ," "};
			for (int j = 0; j < arr_bosung1.length; j++)
			{
				cells = new PdfPCell(new Paragraph(arr_bosung1[j], new Font(bf, 12, Font.BOLD)));
				cells.setVerticalAlignment(Element.ALIGN_BOTTOM);
				cells.setHorizontalAlignment(Element.ALIGN_CENTER);
				cells.setFixedHeight(0.6f*CONVERT);
				cells.setBorder(0);					
				tbl_sanpham.addCell(cells);
			}
			stt++;
			dongtang++;

			// tinh tien chiet khau
			tongtienck=  tienck+ tienkm;
			tiensauck= tientruocvat- tongtienck-tientanggiam ; //lay bvat cua hoa don

			//==========tien truoc vat

			cells = new PdfPCell(new Paragraph(" ", new Font(bf, 10, Font.BOLD)));
			cells.setBorder(0);
			cells.setColspan(2);
			cells.setFixedHeight(0.6f*CONVERT);
			tbl_sanpham.addCell(cells);

			cells = new PdfPCell(new Paragraph("Cộng tiền hàng", new Font(bf, 10, Font.BOLD)));
			cells.setBorder(0);
			cells.setFixedHeight(0.6f*CONVERT);
			cells.setHorizontalAlignment(Element.ALIGN_LEFT);
			tbl_sanpham.addCell(cells);

			cells = new PdfPCell(new Paragraph(DinhDangTraphacoDMS(formatter1.format( tientruocvat))+"đ", new Font(bf, 10, Font.BOLD)));
			cells.setBorder(0);
			cells.setColspan(5);
			cells.setFixedHeight(0.6f*CONVERT);
			cells.setHorizontalAlignment(Element.ALIGN_RIGHT);
			tbl_sanpham.addCell(cells);

			cells = new PdfPCell(new Paragraph(" ", new Font(bf, 10, Font.BOLD)));
			cells.setBorder(0);
			cells.setFixedHeight(0.6f*CONVERT);
			tbl_sanpham.addCell(cells);
			dongtang++;

			//================== tien ck

			cells = new PdfPCell(new Paragraph(" ", new Font(bf, 10, Font.BOLD)));
			cells.setBorder(0);
			cells.setColspan(2);
			cells.setFixedHeight(0.6f*CONVERT);
			tbl_sanpham.addCell(cells);

			cells = new PdfPCell(new Paragraph("Tiền chiết khấu", new Font(bf, 10, Font.BOLD)));
			cells.setBorder(0);
			cells.setFixedHeight(0.6f*CONVERT);
			cells.setHorizontalAlignment(Element.ALIGN_LEFT);
			tbl_sanpham.addCell(cells);


			cells = new PdfPCell(new Paragraph(DinhDangTraphacoDMS(formatter1.format(tongtienck))+"đ", new Font(bf, 10, Font.BOLD)));
			cells.setBorder(0);
			cells.setColspan(5);
			cells.setFixedHeight(0.6f*CONVERT);
			cells.setHorizontalAlignment(Element.ALIGN_RIGHT);
			tbl_sanpham.addCell(cells);

			cells = new PdfPCell(new Paragraph(" ", new Font(bf, 10, Font.BOLD)));
			cells.setBorder(0);
			cells.setFixedHeight(0.6f*CONVERT);
			tbl_sanpham.addCell(cells);
			dongtang++;
			//==============tien tang giam

			cells = new PdfPCell(new Paragraph(" ", new Font(bf, 10, Font.BOLD)));
			cells.setBorder(0);
			cells.setColspan(2);
			cells.setFixedHeight(0.6f*CONVERT);
			tbl_sanpham.addCell(cells);

			cells = new PdfPCell(new Paragraph("Tiền tăng giảm", new Font(bf, 10, Font.BOLD)));
			cells.setBorder(0);
			cells.setFixedHeight(0.6f*CONVERT);
			cells.setHorizontalAlignment(Element.ALIGN_LEFT);
			tbl_sanpham.addCell(cells);

			cells = new PdfPCell(new Paragraph(DinhDangTraphacoDMS(formatter1.format(tientanggiam))+"đ" , new Font(bf, 10, Font.BOLD)));
			cells.setBorder(0);
			cells.setColspan(5);
			cells.setFixedHeight(0.6f*CONVERT);
			cells.setHorizontalAlignment(Element.ALIGN_RIGHT);
			tbl_sanpham.addCell(cells);

			cells = new PdfPCell(new Paragraph(" ", new Font(bf, 10, Font.BOLD)));
			cells.setBorder(0);
			cells.setFixedHeight(0.6f*CONVERT);
			tbl_sanpham.addCell(cells);
			dongtang++;

			//tien sau ck
			/*cells = new PdfPCell(new Paragraph( DinhDangTraphacoDMS(formatter1.format(tiensauck))+"đ", new Font(bf, 10, Font.BOLD)));
			cells.setBorder(0);
			cells.setColspan(8);
			cells.setHorizontalAlignment(Element.ALIGN_RIGHT);
			tbl_sanpham.addCell(cells);*/

			System.out.println("stt , sodong :" +stt + "," +dongtang);
			//neu co cuon dong


			int kk=0;

			while(kk <= 19-dongtang)
			{
				String[] arr_bosung = new String[] {" ", " " , " ", " "," ", " "," "," "," " };

				for (int j = 0; j < arr_bosung.length; j++)
				{
					cells = new PdfPCell(new Paragraph(arr_bosung[j], new Font(bf, 8, Font.BOLD)));
					cells.setVerticalAlignment(Element.ALIGN_BOTTOM);
					cells.setHorizontalAlignment(Element.ALIGN_CENTER);
					cells.setFixedHeight(0.6f*CONVERT);
					cells.setBorder(0);

					tbl_sanpham.addCell(cells);
				}

				kk++;

			}

			
			//giam them 1 dong 24/10/2014
			for (int i = 0; i < 1; i++) {
				String[] arr_bosung = new String[] {" ", " " , " ", " "," ", " "," "," "," " };

				for (int j = 0; j < arr_bosung.length; j++)
				{
					cells = new PdfPCell(new Paragraph(arr_bosung[j], new Font(bf, 8, Font.BOLD)));
					cells.setVerticalAlignment(Element.ALIGN_BOTTOM);
					cells.setHorizontalAlignment(Element.ALIGN_CENTER);
					cells.setFixedHeight(0.6f*CONVERT);
					cells.setBorder(0);

					tbl_sanpham.addCell(cells);
				}

			}


			//document.add(tbl_sanpham);
			
			
			
			cell.addElement(tbl_sanpham);
			tbl_tong.addCell(cell);
			document.add(tbl_tong);

			//lay top(1) vat %
			String qr_vat="select top(1) thuevat as vat from HOADON_SP_CHITIET where hoadon_fk='"+pxkBean.getId()+"'";
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


			//-------Tao bang vat, tong tien 
			PdfPTable tbl_vat = new PdfPTable(2);
			//tbl_vat.setSpacingBefore(0.7f*CONVERT); // so dong trong
			tbl_vat.setWidthPercentage(100);
			tbl_vat.setHorizontalAlignment(Element.ALIGN_LEFT);
			tbl_vat.getDefaultCell().setBorder(0);
			float[] cr = { 18.0f*CONVERT, 6.0f*CONVERT };
			tbl_vat.setWidths(cr);
			PdfPCell cellss;
			//rong
			cellss = new PdfPCell(new Paragraph(" ", new Font(bf, 10, Font.BOLD)));
			cellss.setBorder(0);
			tbl_vat.addCell(cellss);

			//tong tien don hang
			/*	cellss = new PdfPCell(new Paragraph(DinhDangTraphacoDMS(formatter1.format(tongtien)) +"đ", new Font(bf, 10, Font.BOLD)));*/
			cellss = new PdfPCell(new Paragraph(DinhDangTraphacoDMS(formatter1.format(tiensauck)) +"đ", new Font(bf, 10, Font.BOLD)));
			cellss.setBorder(0);
			cellss.setPaddingTop(-0.5f*CONVERT);
			cellss.setHorizontalAlignment(Element.ALIGN_RIGHT);
			tbl_vat.addCell(cellss);

			//vat
			cellss = new PdfPCell(new Paragraph(vat, new Font(bf, 10, Font.BOLD)));
			cellss.setBorder(0);
			cellss.setPaddingTop(-0.3f*CONVERT);
			cellss.setPaddingLeft(3.0f * CONVERT);
			cellss.setHorizontalAlignment(Element.ALIGN_LEFT);
			tbl_vat.addCell(cellss);

			//tien vat
			cellss = new PdfPCell(new Paragraph(tienvat.replaceAll(",", ".")+"đ", new Font(bf, 10, Font.BOLD)));
			cellss.setBorder(0);
			cellss.setPaddingTop(-0.3f*CONVERT);
			cellss.setHorizontalAlignment(Element.ALIGN_RIGHT);
			tbl_vat.addCell(cellss);

			//rong
			cellss = new PdfPCell(new Paragraph(" ", new Font(bf, 10, Font.BOLD)));
			cellss.setBorder(0);
			tbl_vat.addCell(cellss);

			//tong tien sau vat
			cellss = new PdfPCell(new Paragraph(tongtien_vat.replaceAll(",", ".")+"đ", new Font(bf, 10, Font.BOLD)));
			cellss.setBorder(0);
			cellss.setPaddingTop(0.3f*CONVERT);
			cellss.setHorizontalAlignment(Element.ALIGN_RIGHT);
			tbl_vat.addCell(cellss);

			//doc tien thanh chu

			System.out.println( " doc tien: " + pxkBean.getTongtienAVAT());
			System.out.println( " doc tien: " + Long.parseLong (pxkBean.getTongtienAVAT().replaceAll(",", "") ));

			// Tien bang chu
			doctienrachu doctien = new doctienrachu();
			String tien = doctien.docTien(Long.parseLong (pxkBean.getTongtienAVAT().replaceAll(",", "") ));
			String TienIN = (tien.substring(0,1)).toUpperCase() + tien.substring(1); //Viết hoa ký tự đầu tiên
			cellss.setColspan(2);
			cellss.setPaddingTop(0.9f);
			cellss = new PdfPCell(new Paragraph(TienIN, new Font(bf, 10, Font.BOLDITALIC)));
			cellss.setHorizontalAlignment(Element.ALIGN_LEFT);
			cellss.setPaddingLeft(3.0f * CONVERT);

			cellss.setBorder(0);
			tbl_vat.addCell(cellss);

			//rong
			cellss = new PdfPCell(new Paragraph(" ", new Font(bf, 10, Font.BOLD)));
			cellss.setBorder(0);
			tbl_vat.addCell(cellss);

			document.add(tbl_vat);
			document.close();

		} catch (Exception e)
		{
			System.out.println("115.Exception: " + e.getMessage());
			e.printStackTrace();
		}
	}


	String doikhoangtrang(String s){
		if(s.length()<=0){
			s=" ";
		}
		return s;
	}


}
