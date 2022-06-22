package geso.dms.distributor.servlets.hoadontaichinhKM;

import geso.dms.center.beans.doctien.doctienrachu;
import geso.dms.distributor.beans.donhang.IDonhang;
import geso.dms.distributor.beans.donhang.ISanpham;
import geso.dms.distributor.beans.donhang.imp.Donhang;
import geso.dms.distributor.beans.hoadontaichinh.IHoadontaichinh;
import geso.dms.distributor.beans.hoadontaichinh.imp.Hoadontaichinh;
import geso.dms.distributor.beans.hoadontaichinhKM.IHoadontaichinhKM;
import geso.dms.distributor.beans.hoadontaichinhKM.imp.HoadontaichinhKM;
import geso.dms.distributor.db.sql.dbutils;
import geso.dms.distributor.util.Utility;

import java.io.IOException;

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

import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class HoadontaichinhKMPdfSvl extends HttpServlet
{
	private static final long serialVersionUID = 1L;

	public HoadontaichinhKMPdfSvl()
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
		String id = util.antiSQLInspection(request.getParameter("pdf"));
		System.out.println("id:"+ id);
		String nppId = util.antiSQLInspection(request.getParameter("nppId"));


		IHoadontaichinhKM lsxBean = new HoadontaichinhKM(id);
		lsxBean.setUserId(userId); 
		lsxBean.init();

		//=============khai bao doc
		response.setContentType("application/pdf");
		response.setHeader("Content-Disposition", " inline; filename=HoaDonTaiChinh.pdf");
		Document document = new Document();
		ServletOutputStream outstream = response.getOutputStream();

		//in hoa don khuyen mai
		this.CreatePxk(document, outstream,lsxBean,userId, nppId);
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
	private void form_hoadon(Document document,ServletOutputStream outstream,IHoadontaichinhKM pxkBean,
			String ctyTen,String cty_MST,String cty_Diachi,
			String cty_Sotaikhoan,String cty_Dienthoai,String cty_Fax,String Donvi,String kh_MST,
			String hinhthucTT,String ngayxuatHD,String chucuahieu,String kh_sotaikhoan, String kh_Diachi,
			String userid,dbutils db)
	{


		try
		{
			NumberFormat formatter = new DecimalFormat("#,###,###.##");
			NumberFormat formatter1 = new DecimalFormat("#,###,###");

			document.setPageSize(PageSize.A4);
			document.setMargins(1f*CONVERT, 1f*CONVERT, 0.5f*CONVERT, 0.5f*CONVERT); // L,R,T,B
			PdfWriter writer = PdfWriter.getInstance(document, outstream);
			PdfPCell cell ;
			Paragraph para;
			Chunk chunk;
			document.open() ;
			BaseFont bf = BaseFont.createFont("c:\\windows\\fonts\\times.ttf", BaseFont.IDENTITY_H, BaseFont.EMBEDDED);
			Font font = new Font(bf, 13, Font.BOLD);
			Font font2 = new Font(bf, 8, Font.BOLD);


			PdfPTable tableheader =new PdfPTable(1);
			tableheader.setWidthPercentage(100);			

			cell = new PdfPCell();
			cell.setBorder(0);
			cell.setPaddingTop(2.4f * CONVERT);
			cell.setPaddingLeft(2.6f * CONVERT);
			cell.setVerticalAlignment(Element.ALIGN_MIDDLE);

			//NGAY THANG NAM, VA SO HOA DON
			String [] ngayHD = ngayxuatHD.split("-");
			Paragraph pxk = new Paragraph("",new Font(bf, 8, Font.BOLDITALIC));
			chunk =new Chunk(ngayHD[2] + "                        " + ngayHD[1] +"                             " + ngayHD[0],new Font(bf, 8, Font.BOLDITALIC));
			pxk.add(chunk);
			pxk.setAlignment(Element.ALIGN_CENTER);
			pxk.setSpacingAfter(2);
			cell.addElement(pxk);

			tableheader.addCell(cell);
			document.add(tableheader);

			// ================================ADD INFO NHA PHAN PHOI
			PdfPTable tbl_npp =new PdfPTable(1);
			tbl_npp.setWidthPercentage(100);

			//--dong1 ten npp
			cell = new PdfPCell();
			cell.setBorder(0);
			cell.setPaddingTop(0.67f * CONVERT);
			cell.setVerticalAlignment(Element.ALIGN_LEFT);
			cell.setPaddingLeft(4.0f * CONVERT);
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
			para = new Paragraph( cty_Diachi, new Font(bf, 10, Font.BOLD));
			cell.addElement(para);
			tbl_npp.addCell(cell);

			//--dong5 Stk npp
			if(cty_Sotaikhoan.equals("0"))
			{
				cty_Sotaikhoan="";
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

			float[] tbl_withd = { 7.0f, 20.0f, 60f, 25f,   10f , 15.0f, 10.0f, 30f };

			PdfPTable tbl_sanpham = new PdfPTable(tbl_withd.length);
			tbl_sanpham.setSpacingBefore(30.0f );
			tbl_sanpham.setWidthPercentage(100);
			tbl_sanpham.setHorizontalAlignment(Element.ALIGN_LEFT);
			tbl_sanpham.setWidths(tbl_withd);
			PdfPCell cells;



			double tongtien = 0;
			String vat="";
			String tienvat= formatter1.format(pxkBean.getTienVAT()); //lay ben hoa don
			String tongtien_vat = formatter1.format(pxkBean.getTienSauCK()+ pxkBean.getTienKM());;
			int stt = 1;

			//danh sach san pham
			List<ISanpham> scheme_sanpham = (List<ISanpham>)pxkBean.getScheme_SpList();
			int count = 0;														
			if(scheme_sanpham.size() > 0)
			{
				ISanpham tkmSp = null;
				count = 0;
				while(count < scheme_sanpham.size())
				{
					tkmSp = scheme_sanpham.get(count); 
					String ma=tkmSp.getMasanpham() ;
					String ten=tkmSp.getTensanpham();
					String solo="";
					String ngayhethan="";
					String donvi=tkmSp.getDonvitinh();
					String soluong= formatter.format(Math.round(Double.parseDouble(tkmSp.getSoluong()))) ;
					String dongia=formatter1.format(Double.parseDouble(tkmSp.getDongia()));
					double thanhtien=Math.floor(Double.parseDouble(tkmSp.getSoluong()) * Double.parseDouble(tkmSp.getDongia())); //lam tron xuong
					tongtien+=thanhtien;
					String ghichu=tkmSp.getGhichu();
					
					//tim so lo va ngay het han
					ResultSet spRs = pxkBean.getSoloTheoSp(tkmSp.getId(),"",tkmSp.getSoluong()+"");  
            		if(spRs != null)
            		{
            			while(spRs.next())
            			{
            				String temID =tkmSp.getId() + "__" + spRs.getString("SOLO") + "__" + spRs.getString("NGAYHETHAN")+ "__" + spRs.getString("NGAYNHAPKHO");
            				solo=spRs.getString("SOLO");
            				ngayhethan=spRs.getString("NGAYHETHAN") ;
            				
            			}
            		}
					if(ngayhethan.length()>0){
						ngayhethan=ngayhethan.substring(5,7)+"/"+ngayhethan.substring(2,4);
						solo+= " - "+ ngayhethan;
					}
					
					

					String[] arr = new String[] { Integer.toString(count+1), ma, ten, solo,donvi,
							soluong,dongia, ghichu };


					for (int j = 0; j < tbl_withd.length; j++)
					{
						cells = new PdfPCell(new Paragraph(arr[j], new Font(bf, 10, Font.BOLD)));
						//canh format
						if(j==2 ||j==1){
							cells.setHorizontalAlignment(Element.ALIGN_LEFT);
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
					count++;
				}
			}


			document.add(tbl_sanpham);

			//lay top(1) vat %
			String qr_vat="select top(1) thuevat as vat from Donhang_SANPHAM  where DONHANG_fk='"+pxkBean.getDondathangId()+"'";
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
			tbl_vat.setSpacingBefore((22-stt)*0.6f*CONVERT); // so dong trong
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
			String tien = doctien.docTien( (long)(pxkBean.getTienSauCK()+ pxkBean.getTienKM()));
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



	private void CreatePxk(Document document, ServletOutputStream outstream, IHoadontaichinhKM pxkBean,String userid, String nppId) throws IOException
	{
		dbutils db = new dbutils();


		try
		{	

			//LAY THONG TIN NCC
			String kbh="";
			String ddh="";
			String npp_fk="";
			String khId="";
			double Vat= 0;

			String ctyTen="";
			String cty_MST ="";
			String cty_Diachi="";
			String cty_Sotaikhoan= "";
			String cty_Dienthoai= "";
			String cty_Fax= "";
			String khoxuat ="";
			String thukho = "";
			String sql ="";

			sql=" select npp.PK_SEQ, npp.TEN ,npp.DIACHI as DIACHI, npp.MASOTHUE,npp.DIENTHOAI, isnull( FAX,'') as FAX, "+     
			"\n  isnull( npp.SOTAIKHOAN,'') as SOTAIKHOAN,isnull(npp.XUATTAIKHO,' ') as XUATTAIKHO,  "+    
			"\n isnull(npp.nganhang,'') as nganhang from NHAPHANPHOI npp "+    
			"\n where PK_SEQ = (select npp_fk from HOADON where pk_seq = '"+ pxkBean.getId() +"') ";

			System.out.println("\n Lay TT CTY "+sql+"\n");
			ResultSet rsINFO = db.get(sql);
			if(rsINFO.next())
			{
				khoxuat = rsINFO.getString("XUATTAIKHO");
				ctyTen = rsINFO.getString("TEN");
				cty_MST = rsINFO.getString("MASOTHUE");
				cty_Diachi = rsINFO.getString("DIACHI");
				cty_Sotaikhoan = rsINFO.getString("SOTAIKHOAN")+ " tại "+rsINFO.getString("nganhang");
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

			sql="select kh.pk_seq, kh.TEN as donvi,isnull(kh.chucuahieu,'') as chucuahieu, kh.diachi as diachi ,isnull(masothue,'') as masothue, '' as taikhoan, dienthoai as dienthoai \n   "+      
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
			
			if(nppId.equals("100065") || nppId.equals("100087")) // mau danh cho vinh long va tien giang
				
			form_hoadon2(document, outstream, pxkBean,  ctyTen, cty_MST,
					cty_Diachi, cty_Sotaikhoan, cty_Dienthoai, cty_Fax,
					Donvi, kh_MST, hinhthucTT, ngayxuatHD, chucuahieu, 
					kh_sotaikhoan, kh_Diachi, userid, phieuxuatkho , db);

			//in hoa don cho cac chi nhanh khac
			else
			//in hoa don khuyen mai
			form_hoadon(document, outstream, pxkBean,  ctyTen, cty_MST,
					cty_Diachi, cty_Sotaikhoan, cty_Dienthoai, cty_Fax,
					Donvi, kh_MST, hinhthucTT, ngayxuatHD, chucuahieu, 
					kh_sotaikhoan, kh_Diachi, userid, db);


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

	
	

	private void form_hoadon2(Document document,ServletOutputStream outstream,
			IHoadontaichinhKM pxkBean,String ctyTen,String cty_MST,String cty_Diachi,
			String cty_Sotaikhoan,String cty_Dienthoai,String cty_Fax,String Donvi,String kh_MST,
			String hinhthucTT,String ngayxuatHD,String chucuahieu,String kh_sotaikhoan, String kh_Diachi,
			 String userid, String phieuxuatkho,dbutils db)
	{

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
			cell.setPaddingTop(-0.4f * CONVERT);
			cell.setVerticalAlignment(Element.ALIGN_LEFT);
			cell.setPaddingLeft(3.5f * CONVERT);
			para = new Paragraph( ctyTen , new Font(bf, 10, Font.BOLD));
			cell.addElement(para);
			tbl_npp.addCell(cell);


			//--dong2 ma dia chi npp
			cell = new PdfPCell();
			cell.setBorder(0);
			cell.setVerticalAlignment(Element.ALIGN_LEFT);
			cell.setPaddingLeft(2.0f * CONVERT);
			cell.setPaddingTop(-0.05f * CONVERT);
			para = new Paragraph( cty_Diachi, new Font(bf, 10, Font.BOLD));
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
			cell.setPaddingLeft(3.0f * CONVERT);
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
			cell.setPaddingLeft(5.3f * CONVERT);
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


			//--dong2 dia chi 
			cell = new PdfPCell();
			cell.setBorder(0);
			cell.setPaddingTop(-0.05f * CONVERT);
			cell.setVerticalAlignment(Element.ALIGN_LEFT);
			cell.setVerticalAlignment(Element.ALIGN_LEFT);
			cell.setPaddingLeft(3.0f * CONVERT);
			para = new Paragraph( kh_Diachi , new Font(bf, 10, Font.BOLD));
			cell.addElement(para);
			tbl_khachhang.addCell(cell);


			//--dong3  so tai khoan khach hang + noi
			cell = new PdfPCell();
			cell.setBorder(0);
			cell.setVerticalAlignment(Element.ALIGN_LEFT);
			cell.setVerticalAlignment(Element.ALIGN_LEFT);
			cell.setPaddingLeft(3.0f * CONVERT);
			if(kh_sotaikhoan.length()<=0){
				kh_sotaikhoan="           ";
			}
			para = new Paragraph( kh_sotaikhoan , new Font(bf, 10, Font.BOLD));
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
			
			String[] spId = pxkBean.getSpId();
			String[] spMa = pxkBean.getSpMa(); 
			String[] spTen = pxkBean.getSpTen();
			String[] spDonvi = pxkBean.getSpDonvi();
			String[] spSoluong = pxkBean.getSpSoluong();
			String[] spDongia = pxkBean.getSpDongia();
			
			float[] tbl_withd = { 5.0f,18.0f, 60f, 30f, 10f , 15f, 25.0f, 25f };

			PdfPTable tbl_sanpham = new PdfPTable(tbl_withd.length);
			tbl_sanpham.setSpacingBefore(2.0f*CONVERT);

			tbl_sanpham.setWidthPercentage(100);
			tbl_sanpham.setHorizontalAlignment(Element.ALIGN_LEFT);
			tbl_sanpham.setWidths(tbl_withd);
			PdfPCell cells;

			//LAY SAN PHAM BAN
		
			double tongtien = 0;
			String vat="";
			String tienvat= formatter1.format(pxkBean.getTienVAT()); //lay ben hoa don
			String tongtien_vat = formatter1.format(pxkBean.getTienSauCK()+ pxkBean.getTienKM());
			
			int stt = 0;
			int dongtang=1;
			try {
			
				String chuoi1="",chuoi2="",chuoicd="";
				int vitri1=0;
				
				//danh sach san pham
				List<ISanpham> scheme_sanpham = (List<ISanpham>)pxkBean.getScheme_SpList();												
				if(scheme_sanpham.size() > 0)
				{
					ISanpham tkmSp = null;
					while(stt < scheme_sanpham.size())
					{
						tkmSp = scheme_sanpham.get(stt); 
						String ma=tkmSp.getMasanpham() ;
						String ten=tkmSp.getTensanpham();
						String solo="";
						String donvi=tkmSp.getDonvitinh();
						String soluong= formatter.format(Math.round(Double.parseDouble(tkmSp.getSoluong()))) ;
						String dongia=formatter.format(Double.parseDouble(tkmSp.getDongia()));
						double thanhtien=Math.floor(Double.parseDouble(tkmSp.getSoluong()) * Double.parseDouble(tkmSp.getDongia())); //lam tron xuong
						tongtien+=thanhtien;
						String ghichu=tkmSp.getGhichu();
						
						//tim so lo va ngay het han
						ResultSet spRs = pxkBean.getSoloTheoSp(tkmSp.getId(),"",tkmSp.getSoluong()+"");  
	            		if(spRs != null)
	            		{
	            			while(spRs.next())
	            			{
	            				solo=spRs.getString("SOLO");      				
	            			}
	            			spRs.close();
	            		}
						
					
						String[] arr = new String[] { Integer.toString(stt+1), ma, ten, solo,donvi,
								soluong,dongia, ghichu };

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


						cells.setVerticalAlignment(Element.ALIGN_BOTTOM);
						cells.setFixedHeight(0.6f*CONVERT);
						cells.setBorder(0);
						tbl_sanpham.addCell(cells);

					}
					stt++; 
					dongtang++;
					
					if(arr[2].trim().length() > 35)
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


								cells.setVerticalAlignment(Element.ALIGN_BOTTOM);
								cells.setFixedHeight(0.6f*CONVERT);
								cells.setBorder(0);
								tbl_sanpham.addCell(cells);
							}
							dodaichuoi = chuoi2.length();

							dongtang++;

						}
					}
					
					//het if
					}
				}

			} catch (Exception e) {
				e.printStackTrace();
			}

			
			System.out.println(" so thu tu : "+ stt);
			

			int kk=0;
			while(kk <=19-dongtang)
			{
				String[] arr_bosung = new String[] { " ", " " , " ", " "," ", " "," "," " };

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
			
			//lay top(1) vat %
			String qr_vat="select top(1) thuevat as vat from Donhang_SANPHAM  where DONHANG_fk='"+pxkBean.getDondathangId()+"'";
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
			cellss = new PdfPCell(new Paragraph(DinhDangTraphacoDMS(formatter1.format(tongtien)) +"đ", new Font(bf, 10, Font.BOLD)));
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

			
			// Tien bang chu
			doctienrachu doctien = new doctienrachu();
			String  tien = doctien.docTien( (long)(pxkBean.getTienSauCK()+ pxkBean.getTienKM()));
			String TienIN = (tien.substring(0,1)).toUpperCase() + tien.substring(1); //Viết hoa ký tự đầu tiên
			cellss.setColspan(2);
			cellss.setPaddingTop(0.9f);
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

	String doikhoangtrang(String s){
		if(s.length()<=0){
			s=" ";
		}
		return s;
	}



}
