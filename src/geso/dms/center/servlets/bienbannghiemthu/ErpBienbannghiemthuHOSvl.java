package geso.dms.center.servlets.bienbannghiemthu;

import geso.dms.center.beans.doctien.doctienrachu;
import geso.dms.center.beans.hoadontaichinh.IErpHoadontaichinh;
import geso.dms.center.beans.hoadontaichinh.IErpHoadontaichinhList;
import geso.dms.center.beans.hoadontaichinh.imp.ErpHoadontaichinh;
import geso.dms.center.beans.hoadontaichinh.imp.ErpHoadontaichinhList;
import geso.dms.center.servlets.report.ReportAPI;
import geso.dms.center.servlets.reports.TinhHinhThucHienCTNPPSvl;
import geso.dms.center.util.Utility;
import geso.dms.distributor.db.sql.dbutils;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.sql.ResultSet;
import java.sql.SQLException;
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
import com.itextpdf.text.Image;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.BaseFont;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;


public class ErpBienbannghiemthuHOSvl extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public ErpBienbannghiemthuHOSvl() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		IErpHoadontaichinhList obj;
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");

		OutputStream out = response.getOutputStream();
		HttpSession session = request.getSession();	    

		Utility util = new Utility();

		String querystring = request.getQueryString();
		String userId = util.getUserId(querystring);

		if (userId.length()==0)
			userId = util.antiSQLInspection(request.getParameter("userId"));

		String action = util.getAction(querystring);

		String lsxId = util.getId(querystring);
		obj = new ErpHoadontaichinhList();

		String nppId = util.getIdNhapp(userId);
		System.out.println(" npp id : "+ nppId);

		String noibo = request.getParameter("NOIBO") == null ? "0" : request.getParameter("NOIBO");
		obj.setNOIBO(noibo);

		String km = request.getParameter("km");
		if(km != null)
		{
			obj.setLoaikm(km);
		}
		System.out.println("loai khuyen mai "+km);

		String loaidonhang = request.getParameter("loaidonhang");
		if(loaidonhang == null)
			loaidonhang = "";
		obj.setLoaidonhang(loaidonhang);

		obj.setUserId(userId);	
		
		String query="";

		if( obj.getNOIBO().equals("1") )
		{
			query = "select isnull(a.loaihd,0) loaihd ,a.PK_SEQ, a.trangthai, a.ngayxuatHD, a.sohoadon as sohoadon, a.kyhieu as kyhieu, NV.TEN as nguoitao, a.tongtienavat as tongtien,  " +
			"\n npp.TEN as khTEN, a.NGAYSUA, a.NGAYTAO, NV2.TEN as nguoisua  " +
			" \n from ERP_HOADON a " +
			"\n inner join ERP_HOADON_DDH ddh on ddh.HOADON_FK=a.PK_SEQ "+
			"\n inner join ERP_DONDATHANG dh on dh.PK_SEQ=ddh.DDH_FK "+
			"\n left join NHAPHANPHOI npp on a.NPP_FK=npp.PK_SEQ  " +
			"\n inner join NHANVIEN nv on a.NGUOITAO = nv.PK_SEQ   " +
			"\n inner join NHANVIEN nv2 on a.NGUOISUA = nv2.PK_SEQ "+
			"\n where  a.noibo = '1' and  npp.iskhachhang=1 ";
		}
		else
		{
			if(!obj.getLoaikm().equals("1"))
			{
				query = "select isnull(a.loaihd,0) loaihd ,a.PK_SEQ, a.trangthai, a.ngayxuatHD,a.sohoadon as sohoadon, a.kyhieu as kyhieu, NV.TEN as nguoitao, a.tongtienavat as tongtien,  " +
				"\n npp.TEN as khTEN, a.NGAYSUA, a.NGAYTAO, NV2.TEN as nguoisua  " +
				"\n from ERP_HOADON a " +
				"\n inner join ERP_HOADON_DDH ddh on ddh.HOADON_FK=a.PK_SEQ "+
				"\n inner join ERP_DONDATHANG dh on dh.PK_SEQ=ddh.DDH_FK "+
				"\n left join erp_hopdong hdg on dh.HOPDONG_FK=hdg.pk_seq "+
				"\n left join NHAPHANPHOI npp on a.NPP_FK=npp.PK_SEQ  " +
				"\n inner join NHANVIEN nv on a.NGUOITAO = nv.PK_SEQ   " +
				"\n inner join NHANVIEN nv2 on a.NGUOISUA = nv2.PK_SEQ "+
				"\n where a.noibo = '0' and loaihoadon != '1'  and  dh.isKM <> 1  and npp.iskhachhang=1 ";
			}
			else
			{
				query = "select isnull(a.loaihd,0) loaihd, a.PK_SEQ, a.trangthai, a.ngayxuatHD, a.sohoadon as sohoadon, a.kyhieu as kyhieu, NV.TEN as nguoitao, a.tongtienavat as tongtien,   " +
				"\n	npp.TEN as khTEN, a.NGAYSUA, a.NGAYTAO, NV2.TEN as nguoisua  " +
				"\n from ERP_HOADON a " +
				"\n inner join ERP_HOADON_DDH ddh on ddh.HOADON_FK=a.PK_SEQ "+
				"\n inner join ERP_DONDATHANG dh on dh.PK_SEQ=ddh.DDH_FK "+
				"\n left join erp_hopdong hdg on dh.HOPDONG_FK=hdg.pk_seq "+
				"\n inner join NHAPHANPHOI npp on a.NPP_FK=npp.PK_SEQ  " +
				"\n inner join NHANVIEN nv on a.NGUOITAO = nv.PK_SEQ   " +
				"\n inner join NHANVIEN nv2 on a.NGUOISUA = nv2.PK_SEQ "+
				"\n where a.noibo = '0' and ( loaihoadon = '1' or dh.isKM =1 ) and npp.iskhachhang=1  ";
			}
		}
		
		
		System.out.println("danh sach: "+query);
		obj.init(query);
		session.setAttribute("obj", obj);
		String nextJSP = request.getContextPath() + "/pages/Center/ErpBienbannghiemthuHO.jsp";

		response.sendRedirect(nextJSP);

	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");

		String action = request.getParameter("action");
		if (action == null)
		{
			action = "";
		}

		String loaidonhang = request.getParameter("loaidonhang");
		if(loaidonhang == null)
			loaidonhang = "0";
		
		
		  String sohopdong = request.getParameter("khrssohopdong");
		    if(sohopdong == null)
		    	sohopdong = "";
		    

		IErpHoadontaichinhList obj = new ErpHoadontaichinhList();
		obj.setLoaidonhang(loaidonhang);

		String noibo = request.getParameter("NOIBO") == null ? "0" : request.getParameter("NOIBO");
		obj.setNOIBO(noibo);

		String km = request.getParameter("km");
		if(km!=null)
		{
			obj.setLoaikm(km);
		}

		Utility util = new Utility();

		HttpSession session = request.getSession();
		String userId = util.antiSQLInspection(request.getParameter("userId")); 
		obj.setUserId(userId);


		if(action.equals("view") || action.equals("next") || action.equals("prev"))
		{
			String search = getSearchQuery(request, obj);
			obj.setNxtApprSplitting(Integer.parseInt(request.getParameter("nxtApprSplitting")));
			obj.setUserId(userId);
			obj.init(search);
			obj.setAttribute(request, action, "list", "crrApprSplitting", "nxtApprSplitting");
			session.setAttribute("obj", obj);

			String nextJSP = request.getContextPath() + "/pages/Center/ErpBienbannghiemthuHO.jsp";
			response.sendRedirect(nextJSP);
		}
		else
		{
			
			String search = getSearchQuery(request, obj);
			obj.setUserId(userId);
			String sumqr = getSumQuery(request, obj);
			obj.getSumBySearch(sumqr);
			obj.init(search);
			
			
			
			//lay khach hang chon
			String khachhang = request.getParameter("khTen");

			if(action.equals("in2"))
			{
				response.setContentType("application/pdf");
				response.setHeader("Content-Disposition", " inline; filename=Bienbannghiemthu.pdf");
				Document document = new Document();
				ServletOutputStream outstream = response.getOutputStream();
				form_phieunghiemthu2(document, outstream, obj, khachhang,sohopdong);
			}
			else
				if(action.equals("in3")){
					response.setContentType("application/pdf");
					response.setHeader("Content-Disposition", " inline; filename=Bienbannghiemthu.pdf");
					Document document = new Document();
					ServletOutputStream outstream = response.getOutputStream();

					form_phieunghiemthu3(document, outstream, obj,khachhang, sohopdong);
				}

			session.setAttribute("obj", obj);  	
			session.setAttribute("userId", userId);		
			String nextJSP = request.getContextPath() + "/pages/Center/ErpBienbannghiemthuHO.jsp";
			response.sendRedirect(nextJSP);
		}

	}


	private String getSearchQuery(HttpServletRequest request, IErpHoadontaichinhList obj)
	{
		String query="";

		if( obj.getNOIBO().equals("1") )
		{
			query = "select isnull(a.loaihd,0) loaihd ,a.PK_SEQ, a.trangthai, a.ngayxuatHD, a.sohoadon as sohoadon, a.kyhieu as kyhieu, NV.TEN as nguoitao, a.tongtienavat as tongtien,  " +
			"\n npp.TEN as khTEN, a.NGAYSUA, a.NGAYTAO, NV2.TEN as nguoisua  " +
			" \n from ERP_HOADON a " +
			"\n inner join ERP_HOADON_DDH ddh on ddh.HOADON_FK=a.PK_SEQ "+
			"\n inner join ERP_DONDATHANG dh on dh.PK_SEQ=ddh.DDH_FK "+
			"\n left join NHAPHANPHOI npp on a.NPP_FK=npp.PK_SEQ  " +
			"\n inner join NHANVIEN nv on a.NGUOITAO = nv.PK_SEQ   " +
			"\n inner join NHANVIEN nv2 on a.NGUOISUA = nv2.PK_SEQ "+
			"\n where  a.noibo = '1'  and npp.iskhachhang=1  ";
		}
		else
		{
			if(!obj.getLoaikm().equals("1"))
			{
				query = "select isnull(a.loaihd,0) loaihd ,a.PK_SEQ, a.trangthai, a.ngayxuatHD,a.sohoadon as sohoadon, a.kyhieu as kyhieu, NV.TEN as nguoitao, a.tongtienavat as tongtien,  " +
				"\n npp.TEN as khTEN, a.NGAYSUA, a.NGAYTAO, NV2.TEN as nguoisua  " +
				"\n from ERP_HOADON a " +
				"\n inner join ERP_HOADON_DDH ddh on ddh.HOADON_FK=a.PK_SEQ "+
				"\n inner join ERP_DONDATHANG dh on dh.PK_SEQ=ddh.DDH_FK "+
				"\n left join erp_hopdong hdg on dh.HOPDONG_FK=hdg.pk_seq "+
				"\n left join NHAPHANPHOI npp on a.NPP_FK=npp.PK_SEQ  " +
				"\n inner join NHANVIEN nv on a.NGUOITAO = nv.PK_SEQ   " +
				"\n inner join NHANVIEN nv2 on a.NGUOISUA = nv2.PK_SEQ "+
				"\n where a.noibo = '0' and loaihoadon != '1'  and  dh.isKM <> 1 and npp.iskhachhang=1   ";
			}
			else
			{
				query = "select isnull(a.loaihd,0) loaihd, a.PK_SEQ, a.trangthai, a.ngayxuatHD, a.sohoadon as sohoadon, a.kyhieu as kyhieu, NV.TEN as nguoitao, a.tongtienavat as tongtien,   " +
				"\n	npp.TEN as khTEN, a.NGAYSUA, a.NGAYTAO, NV2.TEN as nguoisua  " +
				"\n from ERP_HOADON a " +
				"\n inner join ERP_HOADON_DDH ddh on ddh.HOADON_FK=a.PK_SEQ "+
				"\n inner join ERP_DONDATHANG dh on dh.PK_SEQ=ddh.DDH_FK "+
				"\n left join erp_hopdong hdg on dh.HOPDONG_FK=hdg.pk_seq "+
				"\n inner join NHAPHANPHOI npp on a.NPP_FK=npp.PK_SEQ  " +
				"\n inner join NHANVIEN nv on a.NGUOITAO = nv.PK_SEQ   " +
				"\n inner join NHANVIEN nv2 on a.NGUOISUA = nv2.PK_SEQ "+
				"\n where a.noibo = '0' and ( loaihoadon = '1' or dh.isKM =1 )  and npp.iskhachhang=1  ";
			}
		}

		String tungay = request.getParameter("tungay");
		if(tungay == null)
			tungay = "";
		obj.setTungay(tungay);

		String denngay = request.getParameter("denngay");
		if(denngay == null)
			denngay = "";
		obj.setDenngay(denngay);
	
		if(tungay.length() > 0)
			query += "\n and a.ngayxuatHD >= '" + tungay + "'";

		if(denngay.length() > 0)
			query += "\n and a.ngayxuatHD <= '" + denngay + "'";
		
		String khten = request.getParameter("khTen");
    	obj.setKhTen(khten);

		if(khten.length() > 0)
			query += "\n and ( a.khachhang_fk ='" + khten + "' or a.npp_fk = '" + khten + "' ) ";
		
		String sohopdong = request.getParameter("khrssohopdong");
		if(sohopdong == null)
			sohopdong = "";
		obj.setSohopdong(sohopdong);
		if(sohopdong.length() > 0)
			query += "\n and hdg.pk_seq = " + sohopdong ;
		
		
		System.out.println ("câu tìm kiếm: " + query + "\n");
		return query;
	}

	public String getDateTime()
	{
		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		Date date = new Date();
		return dateFormat.format(date);	
	}

	private String getSumQuery(HttpServletRequest request, IErpHoadontaichinhList obj) 
	{
		String nppId = request.getParameter("nppId");
		if ( nppId == null)
			nppId = "";
		obj.setnppId(nppId);

		String tungay = request.getParameter("tungay");
		if (tungay == null)
			tungay = "";    	
		obj.setTungay(tungay);

		String denngay = request.getParameter("denngay");
		if (denngay == null)
			denngay = "";    	
		obj.setDenngay(denngay);
		
		String khachhang = request.getParameter("khTen");
    	obj.setKhTen(khachhang);


		if ( nppId.length() == 0  && tungay.length() == 0 && denngay.length() == 0 && khachhang.length()==0 )
			obj.setIsSearch(false);
		else
			obj.setIsSearch(true);

		String query =
			" select " +
			" sum(hd_npp.tongtienbvat) as doanhso,  sum(hd_npp.vat) as thue, sum(tongtienbvat + vat - chietkhau) as doanhthu "+
			" from	ERP_HOADONNPP hd_npp inner join ERP_HOADONNPP_DDH hd_ddh on hd_npp.PK_SEQ = hd_ddh.HOADONNPP_FK "+
			" where	1=1 ";     	
		if (nppId.length() > 0)
		{
			query = query + " and hd_npp.NPP_FK = '" + nppId + "'";		
		} 


		if (tungay.length() > 0)
		{
			query = query + " and hd_npp.NGAYXUATHD >= '" + tungay + "'";			
		}    	
		if (denngay.length() > 0)
		{
			query = query + " and hd_npp.NGAYXUATHD <= '" + denngay + "'";	
		}
		
		
		if(khachhang.length() > 0)
			query += " and ( hd_npp.KHACHHANG_FK ='" + khachhang + "' or  hd_npp.NPP_DAT_FK = '" + khachhang + "' ) ";
		
		System.out.println("\n Query cua ban: " + query);
		return query;
	}




	private String getFormatDate(String date) 
	{
		try
		{
			String[] arr = date.split("-");

			return arr[2] + "/" + arr[1] + "/" + arr[0];	
		}
		catch(Exception ex)
		{
			return date;
		}
	}


	float CONVERT = 28.346457f;  // =1cm
	private void form_phieunghiemthu2( Document document,ServletOutputStream outstream, IErpHoadontaichinhList obj, String khachhang, String sohopdong){
		dbutils db = new dbutils();
		
		//lay thong tin hop dong
		String ngayhopdong="";
		String qr_hopdong="select ngaytao as ngayhopdong from erp_hopdong where PK_SEQ="+sohopdong;			
		System.out.println(" thong tin hop dong: "+ qr_hopdong);
		ResultSet rsHopDong = db.get(qr_hopdong);
		if(rsHopDong!=null){
			try {
				while(rsHopDong.next()){
					ngayhopdong=rsHopDong.getString("ngayhopdong");
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
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
		String qr_ho="select ten , diachi, dienthoai, fax, masothue,  SOTK AS TAIKHOAN, nganhang from nhacungcap where pk_seq=100001 ";   
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
	
			//ban doi tac + ban etc deu luu trong nhaphanphoi
				sql = "select kh.pk_seq, kh.TEN as donvi, '' as chucuahieu , kh.diachi as diachi,  kh.masothue , kh.sotaikhoan as taikhoan, "+
				"isnull(dienthoai,'') as dienthoai, isnull(fax,'') as fax , isnull(nganhang,'') as nganhang, "+
				"(select ten from tinhthanh where pk_seq=kh.tinhthanh_fk) as tinhthanh, "+    
				"(select ten from QUANHUYEN where pk_seq=kh.quanhuyen_fk) as quanhuyen "+    
				" from NHAPHANPHOI kh where kh.pk_seq="+khachhang;
				System.out.println("\n Lấy TT KH doi tac "+sql +"\n");

				ResultSet rsLayKH= db.get(sql);
				if(rsLayKH!=null){
					try {
						while(rsLayKH.next())
						{
							Donvi = rsLayKH.getString("DONVI");
							kh_MST = rsLayKH.getString("MASOTHUE");
							kh_Diachi = rsLayKH.getString("DIACHI")+", "+rsLayKH.getString("quanhuyen")+", "+rsLayKH.getString("tinhthanh");
							kh_sotaikhoan=rsLayKH.getString("TAIKHOAN") +" Tại " +rsLayKH.getString("NGANHANG");
							kh_fax=rsLayKH.getString("fax");
							kh_dienthoai=rsLayKH.getString("dienthoai");
							rsLayKH.close();

						}  
						rsLayKH.close();
					} catch (Exception e) {
						e.printStackTrace();
					}
				} 

		try {
			//khai bao
			NumberFormat formatter = new DecimalFormat("#,###,###.##");
			NumberFormat formatter1 = new DecimalFormat("#,###,###");
			document.setPageSize(PageSize.A4);
			document.setMargins(1.5f*CONVERT, 1.5f*CONVERT, 1.0f*CONVERT,1.0f*CONVERT); // L,R,T,B
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
			cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
			para=new Paragraph("BIÊN BẢN",new Font(bf,16,Font.BOLD));
			para.setAlignment(Element.ALIGN_CENTER);
			cell.addElement(para);
			tableheader.addCell(cell);

			cell = new PdfPCell();
			cell.setBorder(0);
			cell.setPaddingBottom(10);
			cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
			para=new Paragraph("NGHIỆM THU THUỐC, VTYTTH, HÓA CHẤT, DỊCH TRUYỀN",new Font(bf,16,Font.BOLD));
			para.setAlignment(Element.ALIGN_CENTER);
			cell.addElement(para);
			tableheader.addCell(cell);

			//dong 4 5

			cell = new PdfPCell();
			cell.setBorder(0);
			cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
			if(ngayhopdong.length() >0)
			{
				String [] ngayHD=ngayhopdong.split("-");
			para=new Paragraph("Căn cứ Hợp đồng số: "+ sohopdong+ " / …......"+" ngày "+ ngayHD[2]+" tháng "+ngayHD[1]+" năm "+ngayHD[0],font10_ilatic);
			}
			else
			{
				para=new Paragraph("Căn cứ Hợp đồng số: " +sohopdong+ " / …...... ngày........ tháng....... năm ........",font10_ilatic);
				
			}
			para.setAlignment(Element.ALIGN_CENTER);
			cell.addElement(para);
			String date=getDate();
			para=new Paragraph("Hôm nay, lúc  ... giờ ...  phút, ngày "+ date.substring(8)+" tháng "+ date.substring(5,7)+ " năm "+date.substring(0,4) + ", chúng tôi gồm có:",font10_ilatic);
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
			chunk=new Chunk("BÊN NHẬN (Bên A)", font10_Bold);
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

			para=new Paragraph("Địa chỉ: "+kh_Diachi,font10_normal);
			para.setAlignment(Element.ALIGN_LEFT);
			cell.addElement(para);

			para=new Paragraph("Điện thoại: " +kh_dienthoai         +"                 -Fax:"+kh_fax,font10_normal); // kem fax
			para.setAlignment(Element.ALIGN_LEFT);
			cell.addElement(para);

			para=new Paragraph("Mã số thuế: "+kh_MST,font10_normal);
			para.setAlignment(Element.ALIGN_LEFT);
			cell.addElement(para);


			para=new Paragraph("Tài khoản:  "+kh_sotaikhoan,font10_normal); //tai ngan hang nao?
			para.setAlignment(Element.ALIGN_LEFT);
			cell.addElement(para);

			para=new Paragraph("Đại diện:                                                                    Chức vụ:",font10_normal);
			para.setAlignment(Element.ALIGN_LEFT);
			cell.addElement(para);
			tbl.addCell(cell);

			

			// ben giao
			cell = new PdfPCell();
			cell.setBorder(0);
			cell.setHorizontalAlignment(Element.ALIGN_LEFT);
			para=new Paragraph();
			para.setAlignment(Element.ALIGN_LEFT);
			chunk=new Chunk("BÊN GIAO (Bên B)", font10_Bold);
			chunk.setUnderline(1f, -2);
			para.add(chunk);

			chunk=new Chunk(" : " +ctyTen.toUpperCase(), font10_Bold);
			para.add(chunk);
			cell.addElement(para);
			tbl.addCell(cell);

			// thong tin ben b
			cell = new PdfPCell();
			cell.setBorder(0);
			cell.setPaddingLeft(1.5f*CONVERT);
			cell.setHorizontalAlignment(Element.ALIGN_LEFT);

			para=new Paragraph("Địa chỉ: "+cty_Diachi,font10_normal);
			para.setAlignment(Element.ALIGN_LEFT);
			cell.addElement(para);

			para=new Paragraph("Điện thoại: "+ cty_Dienthoai +"  – Fax: "+cty_Fax,font10_normal); // kem fax
			para.setAlignment(Element.ALIGN_LEFT);
			cell.addElement(para);

			para=new Paragraph("Mã số thuế: "+cty_MST,font10_normal);
			para.setAlignment(Element.ALIGN_LEFT);
			cell.addElement(para);


			para=new Paragraph("Tài khoản: " +cty_Sotaikhoan + " tại " +cty_bank,font10_normal); //tai ngan hang nao?
			para.setAlignment(Element.ALIGN_LEFT);
			cell.addElement(para);

			para=new Paragraph("Đại diện:                                  Chức vụ:",font10_normal);
			para.setAlignment(Element.ALIGN_LEFT);
			cell.addElement(para);
			tbl.addCell(cell);

			//loi xac nhan
			cell = new PdfPCell();
			cell.setBorder(0);
			cell.setHorizontalAlignment(Element.ALIGN_LEFT);
			para=new Paragraph("      Hai bên thống nhất nghiệm thu và bàn giao thuốc, VTYTTH, hóa chất, dịch truyền theo Hợp đồng đã ký với chi tiết như sau:",font10_normal);
			para.setAlignment(Element.ALIGN_LEFT);
			cell.addElement(para);
			para=new Paragraph("Nội dung:",font10_Bold);
			para.setAlignment(Element.ALIGN_LEFT);
			cell.addElement(para);
			para=new Paragraph("      Bên B bàn giao cho bên A thuốc, VTYTTH, hóa chất, dịch truyền theo hóa đơn:",font10_normal);
			para.setAlignment(Element.ALIGN_LEFT);
			cell.addElement(para);
			tbl.addCell(cell);

			document.add(tbl);


			//========bang danh sach hoa don
			float[] tbl_withd = { 10.0f, 50f, 40.0f, 35.0f };
			PdfPTable tbl_sanpham = new PdfPTable(tbl_withd.length);
			tbl_sanpham.setWidthPercentage(100);
			tbl_sanpham.setSpacingBefore(0.3f * CONVERT);
			tbl_sanpham.setHorizontalAlignment(Element.ALIGN_LEFT);
			tbl_sanpham.setWidths(tbl_withd);
			PdfPCell cells;

			//add tieu de
			String[] tieude = new String[] { "TT","Số HĐ","Ngày xuất hóa đơn","Thành tiền \n (VNĐ)"};
			for (int j = 0; j < tbl_withd.length; j++)
			{
				cells = new PdfPCell(new Paragraph(tieude[j], new Font(bf, 10, Font.BOLD)));
				cells.setHorizontalAlignment(Element.ALIGN_CENTER);
				cells.setPaddingBottom(7);
				tbl_sanpham.addCell(cells);	
			}
			//danh sach
			int stt=1;
			double tongtien=0;
			ResultSet nhapkhoRs =  obj.getDondathangRs();
			try {

				//SAN PHAM BAN
				while(nhapkhoRs.next())
				{
					String[] arr = new String[] { Integer.toString(stt),nhapkhoRs.getString("SOHOADON"),
							dinhdangngay(nhapkhoRs.getString("NGAYXUATHD")),DinhDangTraphacoDMS(formatter1.format(nhapkhoRs.getDouble("TONGTIEN")))};
					String tt = nhapkhoRs.getString("trangthai");
					if(tt.equals("2")||tt.equals("4"))
					{
						tongtien+=nhapkhoRs.getDouble("TONGTIEN");
						for (int j = 0; j < tbl_withd.length; j++)
						{
							cells = new PdfPCell(new Paragraph(arr[j], new Font(bf, 10, Font.NORMAL)));
							if(j==3){
								cells.setHorizontalAlignment(Element.ALIGN_RIGHT);
							}
							else
								cells.setHorizontalAlignment(Element.ALIGN_CENTER);

							cells.setVerticalAlignment(Element.ALIGN_BOTTOM);
							cells.setPaddingBottom(7);
							tbl_sanpham.addCell(cells);	
						}
						stt++;
					}
				}
				nhapkhoRs.close();

			} catch (Exception e) {
				e.printStackTrace();
			}

			//cong 
			cell = new PdfPCell();
			cell.setVerticalAlignment(Element.ALIGN_LEFT);
			para = new Paragraph("", new Font(bf, 10, Font.NORMAL));
			cell.addElement(para);
			tbl_sanpham.addCell(cell);

			cell = new PdfPCell();
			cell.setColspan(2);
			cell.setVerticalAlignment(Element.ALIGN_LEFT);
			cell.setPaddingBottom(7);
			para = new Paragraph("TỔNG CỘNG " , new Font(bf, 10, Font.BOLD));
			para.setAlignment(Element.ALIGN_CENTER);
			cell.addElement(para);
			tbl_sanpham.addCell(cell);

			cell = new PdfPCell();
			cell.setVerticalAlignment(Element.ALIGN_RIGHT);
			cell.setPaddingBottom(7);
			para = new Paragraph(DinhDangTraphacoDMS(formatter1.format(tongtien)) , new Font(bf, 10, Font.BOLD));
			para.setAlignment(Element.ALIGN_RIGHT);
			cell.addElement(para);
			tbl_sanpham.addCell(cell);

			document.add(tbl_sanpham);

			//=========== footer

			PdfPTable tbl_footer =new PdfPTable(1);
			tbl_footer.setWidthPercentage(100);
			doctienrachu doctien = new doctienrachu();
			String tien = doctien.docTien((long)tongtien);
			String TienIN = (tien.substring(0,1)).toUpperCase() + tien.substring(1); //Viết hoa ký tự đầu tiên
			cell = new PdfPCell();
			cell.setBorder(0);
			para = new Paragraph("      Số tiền bằng chữ:  " +TienIN, new Font(bf,12,Font.NORMAL)); //cong doc tine
			para.setAlignment(Element.ALIGN_JUSTIFIED);
			cell.addElement(para);
			tbl_footer.addCell(cell);

			cell = new PdfPCell();
			cell.setBorder(0);
			para = new Paragraph("Kết luận: ", new Font(bf,12,Font.BOLD)); //cong doc tine
			para.setAlignment(Element.ALIGN_JUSTIFIED);
			cell.addElement(para);
			tbl_footer.addCell(cell);

			cell = new PdfPCell();
			cell.setBorder(0);
			para = new Paragraph("      - Bên A đã kiểm tra đúng chủng loại, hàm lượng, số lô, hạn dùng,…….. của hàng hóa theo yêu cầu.", font10_normal); 
			para.setAlignment(Element.ALIGN_JUSTIFIED);
			cell.addElement(para);
			tbl_footer.addCell(cell);

			cell = new PdfPCell();
			cell.setBorder(0);
			para = new Paragraph("      - Bên A đã nhận đầy đủ số lượng hàng hóa. ", font10_normal); 
			para.setAlignment(Element.ALIGN_JUSTIFIED);
			cell.addElement(para);
			tbl_footer.addCell(cell);

			cell = new PdfPCell();
			cell.setBorder(0);
			para = new Paragraph("      - Trong thời gian sử dụng nếu bên A phát hiện bên trong có sự thiếu hụt về số lượng hoặc kém chất lượng ……,"+
					" Bên A sẽ thông báo ngay cho Bên B bằng cách điện thoại, fax hoặc bằng văn bản để cùng bàn bạc giải quyết.", font10_normal); 
			para.setAlignment(Element.ALIGN_JUSTIFIED);
			cell.addElement(para);
			tbl_footer.addCell(cell);

			cell = new PdfPCell();
			cell.setBorder(0);
			para = new Paragraph("      - Bên A thống nhất nghiệm thu và thanh toán tiền thuốc, hóa chất, VTYTTH cho bên B theo đúng hợp đồng đã ký kết.", font10_normal); 
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

			/*cell = new PdfPCell();
			cell.setBorder(0);
			cell.setPaddingLeft(1.5f*CONVERT);
			para = new Paragraph("       GIÁM ĐỐC                                              "+
					"                                                       TUQ. TỔNG GIÁM ĐỐC", new Font(bf,10,Font.BOLD));
			cell.addElement(para);
			tbl_footer.addCell(cell);*/

			document.add(tbl_footer);

			document.close();

		} catch (Exception e) {
			e.printStackTrace();

		}

	}


	private void form_phieunghiemthu3( Document document,ServletOutputStream outstream, IErpHoadontaichinhList obj, String khachhang, String sohopdong){
		dbutils db = new dbutils();
		//lay thong tin hop dong
		String ngayhopdong="";
		String qr_hopdong="select ngaytao as ngayhopdong from erp_hopdong where PK_SEQ="+sohopdong;			
		System.out.println(" thong tin hop dong: "+ qr_hopdong);
		ResultSet rsHopDong = db.get(qr_hopdong);
		if(rsHopDong!=null){
			try {
				while(rsHopDong.next()){
					ngayhopdong=rsHopDong.getString("ngayhopdong");
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
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
		String qr_ho="select ten , diachi, dienthoai, fax, masothue,  SOTK AS TAIKHOAN, nganhang from nhacungcap where pk_seq=100001 ";   
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
	
			//ban doi tac + ban etc deu luu trong nhaphanphoi
				sql = "select kh.pk_seq, kh.TEN as donvi, '' as chucuahieu , kh.diachi as diachi,  kh.masothue , kh.sotaikhoan as taikhoan, "+
				"isnull(dienthoai,'') as dienthoai, isnull(fax,'') as fax , isnull(nganhang,'') as nganhang, "+
				"(select ten from tinhthanh where pk_seq=kh.tinhthanh_fk) as tinhthanh, "+    
				"(select ten from QUANHUYEN where pk_seq=kh.quanhuyen_fk) as quanhuyen "+    
				" from NHAPHANPHOI kh where kh.pk_seq="+khachhang;
				System.out.println("\n Lấy TT KH doi tac "+sql +"\n");

				ResultSet rsLayKH= db.get(sql);
				if(rsLayKH!=null){
					try {
						while(rsLayKH.next())
						{
							Donvi = rsLayKH.getString("DONVI");
							kh_MST = rsLayKH.getString("MASOTHUE");
							kh_Diachi = rsLayKH.getString("DIACHI")+", "+rsLayKH.getString("quanhuyen")+", "+rsLayKH.getString("tinhthanh");
							kh_sotaikhoan=rsLayKH.getString("TAIKHOAN") +" Tại " +rsLayKH.getString("NGANHANG");
							kh_fax=rsLayKH.getString("fax");
							kh_dienthoai=rsLayKH.getString("dienthoai");
							rsLayKH.close();

						}  
						rsLayKH.close();
					} catch (Exception e) {
						e.printStackTrace();
					}
				} 
		   
		try {
			//khai bao
			NumberFormat formatter = new DecimalFormat("#,###,###.##");
			NumberFormat formatter1 = new DecimalFormat("#,###,###");
			document.setPageSize(PageSize.A4);
			document.setMargins(1.5f*CONVERT, 1.5f*CONVERT, 1.0f*CONVERT, 1.0f*CONVERT); // L,R,T,B
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
			cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
			para=new Paragraph("BIÊN BẢN",new Font(bf,16,Font.BOLD));
			para.setAlignment(Element.ALIGN_CENTER);
			cell.addElement(para);
			tableheader.addCell(cell);

			cell = new PdfPCell();
			cell.setBorder(0);
			cell.setPaddingBottom(10);
			cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
			para=new Paragraph("NGHIỆM THU THUỐC, VTYTTH, HÓA CHẤT, DỊCH TRUYỀN",new Font(bf,16,Font.BOLD));
			para.setAlignment(Element.ALIGN_CENTER);
			cell.addElement(para);
			tableheader.addCell(cell);

			//dong 4 5

			cell = new PdfPCell();
			cell.setBorder(0);
			cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
			if(ngayhopdong.length() >0)
			{
				String [] ngayHD=ngayhopdong.split("-");
			para=new Paragraph("Căn cứ Hợp đồng số: "+ sohopdong+ " / …......"+" ngày "+ ngayHD[2]+" tháng "+ngayHD[1]+" năm "+ngayHD[0],font10_ilatic);
			}
			else
			{
				para=new Paragraph("Căn cứ Hợp đồng số: " +sohopdong+ " / …...... ngày........ tháng....... năm ........",font10_ilatic);
				
			}
			para.setAlignment(Element.ALIGN_CENTER);
			cell.addElement(para);
			String date=getDate();
			para=new Paragraph("Hôm nay, lúc  ... giờ ...  phút, ngày "+ date.substring(8)+" tháng "+ date.substring(5,7)+ " năm "+date.substring(0,4) + ", chúng tôi gồm có:",font10_ilatic);
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
			chunk=new Chunk("BÊN NHẬN (Bên A)", font10_Bold);
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

			para=new Paragraph("Địa chỉ: "+kh_Diachi,font10_normal);
			para.setAlignment(Element.ALIGN_LEFT);
			cell.addElement(para);

			para=new Paragraph("Điện thoại: " +kh_dienthoai         +"                 -Fax:"+kh_fax,font10_normal); // kem fax
			para.setAlignment(Element.ALIGN_LEFT);
			cell.addElement(para);

			para=new Paragraph("Mã số thuế: "+kh_MST,font10_normal);
			para.setAlignment(Element.ALIGN_LEFT);
			cell.addElement(para);


			para=new Paragraph("Tài khoản:  "+kh_sotaikhoan,font10_normal); //tai ngan hang nao?
			para.setAlignment(Element.ALIGN_LEFT);
			cell.addElement(para);

			para=new Paragraph("Đại diện:                                                                    Chức vụ:",font10_normal);
			para.setAlignment(Element.ALIGN_LEFT);
			cell.addElement(para);
			tbl.addCell(cell);

			

			// ben giao
			cell = new PdfPCell();
			cell.setBorder(0);
			cell.setHorizontalAlignment(Element.ALIGN_LEFT);
			para=new Paragraph();
			para.setAlignment(Element.ALIGN_LEFT);
			chunk=new Chunk("BÊN GIAO (Bên B)", font10_Bold);
			chunk.setUnderline(1f, -2);
			para.add(chunk);

			chunk=new Chunk(" : " +ctyTen.toUpperCase(), font10_Bold);
			para.add(chunk);
			cell.addElement(para);
			tbl.addCell(cell);

			// thong tin ben b
			cell = new PdfPCell();
			cell.setBorder(0);
			cell.setPaddingLeft(1.5f*CONVERT);
			cell.setHorizontalAlignment(Element.ALIGN_LEFT);

			para=new Paragraph("Địa chỉ: "+cty_Diachi,font10_normal);
			para.setAlignment(Element.ALIGN_LEFT);
			cell.addElement(para);

			para=new Paragraph("Điện thoại: "+ cty_Dienthoai +"  – Fax: "+cty_Fax,font10_normal); // kem fax
			para.setAlignment(Element.ALIGN_LEFT);
			cell.addElement(para);

			para=new Paragraph("Mã số thuế: "+cty_MST,font10_normal);
			para.setAlignment(Element.ALIGN_LEFT);
			cell.addElement(para);


			para=new Paragraph("Tài khoản: " +cty_Sotaikhoan + " tại " +cty_bank,font10_normal); //tai ngan hang nao?
			para.setAlignment(Element.ALIGN_LEFT);
			cell.addElement(para);

			para=new Paragraph("Đại diện:                                                                      Chức vụ:",font10_normal);
			para.setAlignment(Element.ALIGN_LEFT);
			cell.addElement(para);
			tbl.addCell(cell);

			//loi xac nhan
			cell = new PdfPCell();
			cell.setBorder(0);
			cell.setHorizontalAlignment(Element.ALIGN_LEFT);
			para=new Paragraph("      Hai bên thống nhất nghiệm thu và bàn giao thuốc, VTYTTH, hóa chất, dịch truyền theo Hợp đồng đã ký với chi tiết như sau:",font10_normal);
			para.setAlignment(Element.ALIGN_LEFT);
			cell.addElement(para);
			para=new Paragraph("Nội dung:",font10_Bold);
			para.setAlignment(Element.ALIGN_LEFT);
			cell.addElement(para);
			para=new Paragraph("      Bên B bàn giao cho bên A thuốc, VTYTTH, hóa chất, dịch truyền theo hóa đơn:",font10_normal);
			para.setAlignment(Element.ALIGN_LEFT);
			cell.addElement(para);
			tbl.addCell(cell);

			document.add(tbl);



			//========bang danh sach hoa don
			float[] tbl_withd = { 10.0f, 60f, 20.0f, 25.0f, 20.0f, 25.0f };
			PdfPTable tbl_sanpham = new PdfPTable(tbl_withd.length);
			tbl_sanpham.setWidthPercentage(100);
			tbl_sanpham.setSpacingBefore(0.3f * CONVERT);
			tbl_sanpham.setHorizontalAlignment(Element.ALIGN_LEFT);
			tbl_sanpham.setWidths(tbl_withd);
			PdfPCell cells;

			//add tieu de
			String[] tieude = new String[] { "TT"," Tên Hàng Hóa ","Đơn vị tính","Số lượng","Đơn giá \n (VAT)","Thành tiền \n (VNĐ)"};
			for (int j = 0; j < tbl_withd.length; j++)
			{
				cells = new PdfPCell(new Paragraph(tieude[j], new Font(bf, 10, Font.BOLD)));
				cells.setHorizontalAlignment(Element.ALIGN_CENTER);
				cells.setPaddingBottom(7);
				tbl_sanpham.addCell(cells);	
			}
			//danh sach hoa don
			int stt=1;
			double tongtien=0;
			double soluongtong=0;
			String dshoadon="";
			ResultSet nhapkhoRs =  obj.getDondathangRs();

			try {

				//SAN PHAM BAN
				while(nhapkhoRs.next())
				{
					String tt = nhapkhoRs.getString("trangthai"); // lay da chot
					if(tt.equals("2")||tt.equals("4"))
					{
						String hoadonid=nhapkhoRs.getString("PK_SEQ");
						dshoadon+=hoadonid +",";

					}
				}
				nhapkhoRs.close();

			} catch (Exception e) {
				e.printStackTrace();
			}

			if(dshoadon.length()>0){

				//bo dau "," cuoi
				dshoadon=dshoadon.substring(0,dshoadon.length()-1);
				System.out.println(" danh sach cac hoa don:"+ dshoadon);
				//lay san pham tung hoa don
				//thong tin san pham hang hoa
				String	 query_sp = " select ma as MA, ten as TEN,      "+    
				"isnull((SELECT DONVI FROM DONVIDOLUONG WHERE PK_SEQ= ERP_HOADON_SP_CHITIET.DVCHUAN),'') AS DONVI,  "+    
				"DONGIA AS DONGIA,sum( SOLUONG ) AS SOLUONG     "+    
				"from ERP_HOADON_SP_CHITIET where hoadon_fk in ("+ dshoadon+") "+    
				"group by  ma,DONGIA,ERP_HOADON_SP_CHITIET.DVCHUAN,ten";
				ResultSet rsSP = db.get(query_sp);

				try {

					//SAN PHAM BAN
					while(rsSP.next())
					{
						double soLUONG = rsSP.getDouble("soluong");
						double dongia = rsSP.getDouble("dongia");
						double thanhtien = soLUONG*dongia;

						tongtien +=thanhtien;
						soluongtong+=soLUONG;
						String[] arr = new String[] { Integer.toString(stt), rsSP.getString("TEN"), rsSP.getString("DONVI"),
								DinhDangTraphacoDMS(formatter1.format(soLUONG)), 
								DinhDangTraphacoDMS(formatter.format(dongia)),
								DinhDangTraphacoDMS(formatter1.format(thanhtien))};

						System.out.println("\n don vi tinh: "+ rsSP.getString("DONVI"));

						for (int j = 0; j < tbl_withd.length; j++)
						{
							cells = new PdfPCell(new Paragraph(arr[j], new Font(bf, 10, Font.NORMAL)));
							if(j==1){
								cells.setHorizontalAlignment(Element.ALIGN_LEFT);
							}
							else
								if(j==4 || j==5|| j==6){
									cells.setHorizontalAlignment(Element.ALIGN_RIGHT);

								}
								else
								{
									cells.setHorizontalAlignment(Element.ALIGN_CENTER);
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
			}
			//cong 
			cell = new PdfPCell();
			cell.setVerticalAlignment(Element.ALIGN_LEFT);
			para = new Paragraph("", new Font(bf, 10, Font.NORMAL));
			cell.addElement(para);
			tbl_sanpham.addCell(cell);

			cell = new PdfPCell();
			cell.setColspan(2);
			cell.setVerticalAlignment(Element.ALIGN_LEFT);
			cell.setPaddingBottom(7);
			para = new Paragraph("TỔNG CỘNG " , new Font(bf, 10, Font.BOLD));
			para.setAlignment(Element.ALIGN_CENTER);
			cell.addElement(para);
			tbl_sanpham.addCell(cell);

			cell = new PdfPCell();
			cell.setVerticalAlignment(Element.ALIGN_LEFT);
			cell.setPaddingBottom(7);
			para = new Paragraph(formatter1.format( soluongtong ), new Font(bf, 10, Font.BOLD));
			para.setAlignment(Element.ALIGN_CENTER);
			cell.addElement(para);
			tbl_sanpham.addCell(cell);

			cell = new PdfPCell();
			cell.setVerticalAlignment(Element.ALIGN_LEFT);
			para = new Paragraph("", new Font(bf, 10, Font.NORMAL));
			cell.addElement(para);
			tbl_sanpham.addCell(cell);

			cell = new PdfPCell();
			cell.setVerticalAlignment(Element.ALIGN_RIGHT);
			cell.setPaddingBottom(7);
			para = new Paragraph(DinhDangTraphacoDMS(formatter1.format( tongtien )) , new Font(bf, 10, Font.BOLD));
			para.setAlignment(Element.ALIGN_RIGHT);
			cell.addElement(para);
			tbl_sanpham.addCell(cell);

			document.add(tbl_sanpham);

			//=========== footer

			PdfPTable tbl_footer =new PdfPTable(1);
			tbl_footer.setWidthPercentage(100);


			doctienrachu doctien = new doctienrachu();
			String tien = doctien.docTien((long)tongtien);
			String TienIN = (tien.substring(0,1)).toUpperCase() + tien.substring(1); //Viết hoa ký tự đầu tiên
			cell = new PdfPCell();
			cell.setBorder(0);
			para = new Paragraph("      Số tiền bằng chữ:  " +TienIN, new Font(bf,12,Font.NORMAL)); //cong doc tine
			para.setAlignment(Element.ALIGN_JUSTIFIED);
			cell.addElement(para);
			tbl_footer.addCell(cell);

			cell = new PdfPCell();
			cell.setBorder(0);
			para = new Paragraph("Kết luận: ", new Font(bf,12,Font.BOLD)); //cong doc tine
			para.setAlignment(Element.ALIGN_JUSTIFIED);
			cell.addElement(para);
			tbl_footer.addCell(cell);

			cell = new PdfPCell();
			cell.setBorder(0);
			para = new Paragraph("      - Bên A đã kiểm tra đúng chủng loại, hàm lượng, số lô, hạn dùng,…….. của hàng hóa theo yêu cầu.", font10_normal); 
			para.setAlignment(Element.ALIGN_JUSTIFIED);
			cell.addElement(para);
			tbl_footer.addCell(cell);

			cell = new PdfPCell();
			cell.setBorder(0);
			para = new Paragraph("      - Bên A đã nhận đầy đủ số lượng hàng hóa. ", font10_normal); 
			para.setAlignment(Element.ALIGN_JUSTIFIED);
			cell.addElement(para);
			tbl_footer.addCell(cell);

			cell = new PdfPCell();
			cell.setBorder(0);
			para = new Paragraph("      - Trong thời gian sử dụng nếu bên A phát hiện bên trong có sự thiếu hụt về số lượng hoặc kém chất lượng ……,"+
					" Bên A sẽ thông báo ngay cho Bên B bằng cách điện thoại, fax hoặc bằng văn bản để cùng bàn bạc giải quyết.", font10_normal); 
			para.setAlignment(Element.ALIGN_JUSTIFIED);
			cell.addElement(para);
			tbl_footer.addCell(cell);

			cell = new PdfPCell();
			cell.setBorder(0);
			para = new Paragraph("      - Bên A thống nhất nghiệm thu và thanh toán tiền thuốc, hóa chất, VTYTTH cho bên B theo đúng hợp đồng đã ký kết.", font10_normal); 
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

			/*cell = new PdfPCell();
			cell.setBorder(0);
			cell.setPaddingLeft(1.5f*CONVERT);
			para = new Paragraph("       GIÁM ĐỐC                                              "+
					"                                                       TUQ. TỔNG GIÁM ĐỐC", new Font(bf,10,Font.BOLD));
			cell.addElement(para);
			tbl_footer.addCell(cell);*/

			document.add(tbl_footer);

			document.close();

		} catch (Exception e) {
			e.printStackTrace();

		}

	}



	private String dinhdangngay (String ngay) {
		String date="";
		if(ngay.length()>=8){
			date=ngay.substring(8)+"/"+ngay.substring(5,7)+"/"+ngay.substring(0,4);
		}
		return date;
	}

	private String DinhDangTraphacoDMS(String sotien)
	{
		sotien = sotien.replaceAll("\\.", "_");
		sotien = sotien.replaceAll(",", "\\.");
		sotien = sotien.replaceAll("_", ",");

		return sotien;
	}

	private String getDate()
	{			
		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		Date date = new Date();
		dateFormat.format(date);
		return dateFormat.format(date);
	}
}
