package geso.dms.distributor.servlets.bienbannghiemthuNPP;

import geso.dms.center.beans.doctien.doctienrachu;
import geso.dms.center.beans.hoadontaichinh.IErpHoadontaichinhList;
import geso.dms.distributor.beans.hoadontaichinhNPP.IErpHoadontaichinhNPP;
import geso.dms.distributor.beans.hoadontaichinhNPP.IErpHoadontaichinhNPPList;
import geso.dms.distributor.beans.hoadontaichinhNPP.imp.ErpHoadontaichinhNPP;
import geso.dms.distributor.beans.hoadontaichinhNPP.imp.ErpHoadontaichinhNPPList;
import geso.dms.distributor.db.sql.dbutils;
import geso.dms.distributor.util.Utility;

import java.io.IOException;
import java.io.PrintWriter;
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
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.BaseFont;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

public class BienbannghiemthuNPPSvl extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public BienbannghiemthuNPPSvl() {
		super();
	}
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		IErpHoadontaichinhNPPList obj;
		request.setCharacterEncoding("UTF-8");
	    response.setCharacterEncoding("UTF-8");
	    response.setContentType("text/html; charset=UTF-8");
	    
	    PrintWriter out = response.getWriter();
	    HttpSession session = request.getSession();	    

	    Utility util = new Utility();
	    	    
	    String querystring = request.getQueryString();
	    String userId = util.getUserId(querystring);
	    
	    if (userId.length()==0)
	    	userId = util.antiSQLInspection(request.getParameter("userId"));
	    
	    String action = util.getAction(querystring);
	    	    
    	String lsxId = util.getId(querystring);
	    obj = new ErpHoadontaichinhNPPList();
	   
	    String nppId = util.getIdNhapp(userId);
	  
	    
	    String km="";
	    km=request.getParameter("km");
	    if(km!=null)
	    {
	    	obj.setLoaikm(km);
	    }
	    String loaidonhang = request.getParameter("loaidonhang");
	    if(loaidonhang == null)
	    	loaidonhang = "";
	    obj.setLoaidonhang(loaidonhang);
	    System.out.println("---LOAI DON HANG: " + loaidonhang);
	    
    	obj.setUserId(userId);	
    	String qr_int="";
    	if(!obj.getLoaikm().equals("1"))
		{
    		qr_int = "select isnull(a.loaihd,0) loaihd ,a.PK_SEQ, a.trangthai, a.ngayxuatHD,  RTRIM(LTRIM (a.sohoadon)) as sohoadon, a.kyhieu as kyhieu, NV.TEN as nguoitao, a.tongtienavat as tongtien ,isnull(kb.ten,'') as kenhbanhang,  " +
				"\n case when a.NPP_DAT_FK is not null then npp.TEN else KH.TEN end as khTEN, a.NGAYSUA, a.NGAYTAO, NV2.TEN as nguoisua  " +
				"\n from ERP_HOADONNPP a " +
				"\n inner join ERP_HOADONNPP_DDH ddh on ddh.HOADONNPP_FK=a.PK_SEQ "+
				"\n inner join ERP_DONDATHANGNPP dh on dh.PK_SEQ=ddh.DDH_FK "+
				"\n left join KHACHHANG KH on a.KHACHHANG_FK=KH.PK_SEQ  " +
				"\n left join NHAPHANPHOI npp on a.NPP_DAT_FK=npp.PK_SEQ  " +
				"\n inner join NHANVIEN nv on a.NGUOITAO = nv.PK_SEQ   " +
				"\n inner join NHANVIEN nv2 on a.NGUOISUA = nv2.PK_SEQ "+
				"\n left join kenhbanhang kb on kb.pk_seq=a.kbh_fk "+
				"\n where  (npp.iskhachhang =1 or npp.isKHACHHANG is null) and  isnull(loaihd,0)<>1 and    dh.isKM <>1 and a.pk_seq > 0  ";
		}
		else
		{
			qr_int =" select isnull(a.loaihd,0) loaihd, a.PK_SEQ, a.trangthai, a.ngayxuatHD, RTRIM(LTRIM (a.sohoadon)) as sohoadon, a.kyhieu as kyhieu, NV.TEN as nguoitao, a.tongtienavat as tongtien ,isnull(kb.ten,'') as kenhbanhang,  " +
					"\n case when a.NPP_DAT_FK is not null then npp.TEN else KH.TEN end as khTEN, a.NGAYSUA, a.NGAYTAO, NV2.TEN as nguoisua  " +
					"\n from ERP_HOADONNPP a " +
					"\n inner join ERP_HOADONNPP_DDH ddh on ddh.HOADONNPP_FK=a.PK_SEQ "+
					"\n inner join ERP_DONDATHANGNPP dh on dh.PK_SEQ=ddh.DDH_FK "+
					"\n left join KHACHHANG KH on a.KHACHHANG_FK=KH.PK_SEQ  " +
					"\n left join NHAPHANPHOI npp on a.NPP_DAT_FK=npp.PK_SEQ  " +
					"\n inner join NHANVIEN nv on a.NGUOITAO = nv.PK_SEQ   " +
					"\n inner join NHANVIEN nv2 on a.NGUOISUA = nv2.PK_SEQ "+
					"\n left join kenhbanhang kb on kb.pk_seq=a.kbh_fk "+
					"\n where  (npp.iskhachhang =1 or npp.isKHACHHANG is null) and (isnull(loaihd,0)=1 or    dh.isKM =1 ) and a.pk_seq > 0   ";
	
		}
    	
    	
    	System.out.println(qr_int +"\n");
    	
    	obj.init(qr_int);
		session.setAttribute("obj", obj);
		String nextJSP="";
			nextJSP= request.getContextPath() + "/pages/Distributor/BienbannghiemthuNPP.jsp";
		
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
	    
	    
		IErpHoadontaichinhNPPList obj = new ErpHoadontaichinhNPPList();
		obj.setLoaidonhang(loaidonhang);
		
		String km="";
	    km=request.getParameter("km");
	    if(km!=null)
	    {
	    	obj.setLoaikm(km);
	    }
		
	    Utility util = new Utility();
	    
		HttpSession session = request.getSession();
	    String userId = util.antiSQLInspection(request.getParameter("userId")); 
	    if(userId!=null)
    		obj.setUserId(userId);
	    System.out.println("user id la "+userId);
	    
	    	if(action.equals("view") || action.equals("next") || action.equals("prev"))
	    	{
	    		
		    	String search = getSearchQuery(request, obj);
		    	obj.setNxtApprSplitting(Integer.parseInt(request.getParameter("nxtApprSplitting")));
		    	obj.setUserId(userId);
		    	obj.init(search);
		    	obj.setAttribute(request, action, "list", "crrApprSplitting", "nxtApprSplitting");
		    	session.setAttribute("obj", obj);
		    	
		    	String nextJSP = request.getContextPath() + "/pages/Distributor/BienbannghiemthuNPP.jsp";
				response.sendRedirect(nextJSP);
		    }
	    	else
	    	{
		    	String search = getSearchQuery(request, obj);
		    	obj.setUserId(userId);
		    	String sumqr = getSumQuery(request, obj);
		    	obj.getSumBySearch(sumqr);
		    	obj.init(search);	
		    	
		    	//lay thong tin khach hang
		    	String khachhang = request.getParameter("khTen");
		    	String[] loai = khachhang.split("--"); 
		    	
		    	if(action.equals("in2"))
				{
					response.setContentType("application/pdf");
					response.setHeader("Content-Disposition", " inline; filename=Bienbannghiemthu.pdf");
					Document document = new Document();
					ServletOutputStream outstream = response.getOutputStream();
					
					form_phieunghiemthu2(document, outstream, obj, loai, sohopdong);
				}
				else
					if(action.equals("in3")){
						response.setContentType("application/pdf");
						response.setHeader("Content-Disposition", " inline; filename=Bienbannghiemthu.pdf");
						Document document = new Document();
						ServletOutputStream outstream = response.getOutputStream();
						form_phieunghiemthu3(document, outstream, obj, loai,sohopdong);
					}
		    	session.setAttribute("obj", obj);  	
	    		session.setAttribute("userId", userId);		
	    		String nextJSP = request.getContextPath() + "/pages/Distributor/BienbannghiemthuNPP.jsp";
	    		response.sendRedirect(nextJSP);
	    	}
	    
	}
	

	
	private String getSearchQuery(HttpServletRequest request, IErpHoadontaichinhNPPList obj)
	{
		String query="";
		//chi lay cua khach hang etc
		if(!obj.getLoaikm().equals("1"))
		{
		 query= "select distinct isnull(a.loaihd,0) loaihd, a.PK_SEQ, a.trangthai, a.ngayxuatHD,  RTRIM(LTRIM (a.sohoadon)) as sohoadon, a.kyhieu as kyhieu,isnull( kb.ten,'') as kenhbanhang, NV.TEN as nguoitao, a.tongtienavat as tongtien , " +
					"\n case when a.NPP_DAT_FK is not null then npp.TEN else KH.TEN end as khTEN, a.NGAYSUA, a.NGAYTAO, NV2.TEN as nguoisua ,a.LoaiHoaDon " +
					"\n from ERP_HOADONNPP a " +
					"\n inner join ERP_HOADONNPP_DDH ddh on ddh.HOADONNPP_FK=a.PK_SEQ "+
					"\n inner join ERP_DONDATHANGNPP dh on dh.PK_SEQ=ddh.DDH_FK "+
					"\n left join erp_hopdongnpp hdg on dh.HOPDONG_FK=hdg.pk_seq "+
					"\n left join KHACHHANG KH on a.KHACHHANG_FK=KH.PK_SEQ  " +
					"\n left join NHAPHANPHOI npp on a.NPP_DAT_FK=npp.PK_SEQ  " +
					"\n inner join NHANVIEN nv on a.NGUOITAO = nv.PK_SEQ   " +
					"\n inner join NHANVIEN nv2 on a.NGUOISUA = nv2.PK_SEQ " +
					"\n left join kenhbanhang kb on kb.pk_seq=a.kbh_fk "+
					"\n where (npp.iskhachhang =1 or npp.isKHACHHANG is null) and  isnull(loaihd,0)<>1    and dh.isKM <>1 and a.pk_seq > 0  ";
		}
		else
		{
			 query= "select distinct isnull(a.loaihd,0) loaihd, a.PK_SEQ, a.trangthai, a.ngayxuatHD,  RTRIM(LTRIM (a.sohoadon)) as sohoadon, a.kyhieu as kyhieu,isnull( kb.ten,'') as kenhbanhang, NV.TEN as nguoitao, a.tongtienavat as tongtien , " +
						"\n case when a.NPP_DAT_FK is not null then npp.TEN else KH.TEN end as khTEN, a.NGAYSUA, a.NGAYTAO, NV2.TEN as nguoisua ,a.LoaiHoaDon " +
						"\n from ERP_HOADONNPP a " +
						"\n inner join ERP_HOADONNPP_DDH ddh on ddh.HOADONNPP_FK=a.PK_SEQ "+
						"\n inner join ERP_DONDATHANGNPP dh on dh.PK_SEQ=ddh.DDH_FK "+
						"\n left join erp_hopdongnpp hdg on dh.HOPDONG_FK=hdg.pk_seq "+
						"\n left join KHACHHANG KH on a.KHACHHANG_FK=KH.PK_SEQ  " +
						"\n left join NHAPHANPHOI npp on a.NPP_DAT_FK=npp.PK_SEQ  " +
						"\n inner join NHANVIEN nv on a.NGUOITAO = nv.PK_SEQ   " +
						"\n inner join NHANVIEN nv2 on a.NGUOISUA = nv2.PK_SEQ " +
						"\n left join kenhbanhang kb on kb.pk_seq=a.kbh_fk "+
						"\n where  (npp.iskhachhang =1 or npp.isKHACHHANG is null)  and  (isnull(loaihd,0)=1 or    dh.isKM =1 ) and a.pk_seq > 0 and a.npp_fk =(select b.pk_seq from NHANVIEN a inner join NHAPHANPHOI b on a.CONVSITECODE=b.SITECODE where a.PK_SEQ= '"+obj.getUserId()+"') ";
		}
		String tungay = request.getParameter("tungay");
		if(tungay == null)
			tungay = "";
		obj.setTungay(tungay);
				
		String denngay = request.getParameter("denngay");
		if(denngay == null)
			denngay = "";
		obj.setDenngay(denngay);
		
			
		
		String khten = request.getParameter("khTen");
		String[] loai = khten.split("--");   	
    	obj.setKhTen(khten);
		
		if(tungay.length() > 0)
			query += " and a.ngayxuatHD >= '" + tungay + "'";
		
		if(denngay.length() > 0)
			query += " and a.ngayxuatHD <= '" + denngay + "'";
	
		
		if(khten.length() > 0)
			query += " and ( kh.pk_seq ='" + loai[1] + "' or a.npp_dat_fk = '" + loai[1] + "' ) ";
		
		
		String sohopdong = request.getParameter("khrssohopdong");
		if(sohopdong == null)
			sohopdong = "";
		obj.setSohopdong(sohopdong);
		if(sohopdong.length() > 0)
			query += "\n and hdg.pk_seq = " + sohopdong ;
		
		System.out.print("c??u t??m ki???m " + query);
		return query;
	}
		
	public String getDateTime()
	{
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date date = new Date();
        return dateFormat.format(date);	
	}
	
	private String getSumQuery(HttpServletRequest request, IErpHoadontaichinhNPPList obj) 
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
    	String[] loai = khachhang.split("--");   	
    	obj.setKhTen(khachhang);
    	    	
    	if ( nppId.length() == 0  && tungay.length() == 0 && denngay.length() == 0  && khachhang.length()==0)
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
    	
    	if (khachhang.length() > 0)
    	{
    		if(loai[0].equals("ETC")){
    			query = query + " and hd_npp.KHACHHANG_FK='"+loai[1]+"' ";
    		}
    		else{
    			query = query + " and hd_npp.NPP_DAT_FK='"+loai[1]+"' ";
    		}
    	}	
    	System.out.println("\nQuery cua ban: " + query);
    	return query;
	}
	
	
	
	float CONVERT = 28.346457f;  // =1cm
	private void form_phieunghiemthu2( Document document,ServletOutputStream outstream, IErpHoadontaichinhNPPList obj, String[] loai, String sohopdong){
		dbutils db = new dbutils();
		
		//lay thong tin hop dong
		String ngayhopdong="";
		String qr_hopdong="select ngaytao as ngayhopdong from erp_hopdongnpp where PK_SEQ="+sohopdong;			
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
		
		//lay thong khah hang
		String Donvi="";
		String kh_MST ="";
		String kh_dienthoai ="";
		String kh_Diachi="";
		String kh_sotaikhoan="";
		String kh_fax="";
		String sql="";	
		
		try {
			//etc
			if(loai[0].equals("ETC")){
				sql="select kh.pk_seq, kh.TEN as donvi,isnull(kh.chucuahieu,'') as chucuahieu, kh.diachi as diachi ,isnull(masothue,'') as masothue, '' as taikhoan,  "+      
				" isnull(dienthoai,'') as dienthoai, ''  as fax , ''  as nganhang "+    
				" from KHACHHANG kh where kh.pk_seq="+loai[1]; 

				System.out.println("\n L???y TT KH etc: "+sql +"\n");

				ResultSet rsLayKH= db.get(sql);
				if(rsLayKH!=null){
					try {
						while(rsLayKH.next())
						{
							Donvi = rsLayKH.getString("DONVI");
							//nguoimuahang=rsLayKH.getString("chucuahieu");
							kh_MST = rsLayKH.getString("MASOTHUE");
							kh_Diachi = rsLayKH.getString("DIACHI");
							kh_sotaikhoan=rsLayKH.getString("TAIKHOAN") ;
							kh_fax=rsLayKH.getString("fax");
							kh_dienthoai=rsLayKH.getString("dienthoai");
							rsLayKH.close();

						}  
						rsLayKH.close();
					} catch (Exception e) {
						e.printStackTrace();
					}
				} 
			

			}
			else
			{
				//doi tac (nha phan phoi)
				sql = "select kh.pk_seq, kh.TEN as donvi, '' as chucuahieu , kh.diachi as diachi,  kh.masothue , kh.sotaikhoan as taikhoan, "+
				"\n isnull(dienthoai,'') as dienthoai, isnull(fax,'') as fax , isnull(nganhang,'') as nganhang "+   
				"\n  from NHAPHANPHOI kh where kh.pk_seq="+loai[1];
				System.out.println("\n L???y TT KH doi tac "+sql +"\n");

				ResultSet rsLayKH= db.get(sql);
				if(rsLayKH!=null){
					try {
						while(rsLayKH.next())
						{
							Donvi = rsLayKH.getString("DONVI");
							kh_MST = rsLayKH.getString("MASOTHUE");
							kh_Diachi = rsLayKH.getString("DIACHI");
							kh_sotaikhoan=rsLayKH.getString("TAIKHOAN");
							if(rsLayKH.getString("NGANHANG").length()>0)
								{
								kh_sotaikhoan=kh_sotaikhoan+" T???i " +rsLayKH.getString("NGANHANG");
								}
							kh_fax=rsLayKH.getString("fax");
							kh_dienthoai=rsLayKH.getString("dienthoai");
							rsLayKH.close();

						}  
						rsLayKH.close();
					} catch (Exception e) {
						e.printStackTrace();
					}
				} 
				
			}
		} catch (Exception e) {
			e.printStackTrace();
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
			para=new Paragraph("C???NG H??A X?? H???I CH??? NGH??A VI???T NAM",font10_Bold);
			para.setAlignment(Element.ALIGN_CENTER);
			cell.addElement(para);
			tableheader.addCell(cell);

			// dong 2 
			cell = new PdfPCell();
			cell.setBorder(0);
			cell.setHorizontalAlignment(Element.ALIGN_LEFT);
			para=new Paragraph();
			para.setAlignment(Element.ALIGN_CENTER);
			chunk=new Chunk("?????c L???p - T??? Do - H???nh Ph??c",font10_Bold);
			chunk.setUnderline(1f, -2);
			para.add(chunk);
			cell.addElement(para);
			tableheader.addCell(cell);

			// dong 3 
			cell = new PdfPCell();
			cell.setBorder(0);
			cell.setPaddingTop(10);
			cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
			para=new Paragraph("BI??N B???N",new Font(bf,16,Font.BOLD));
			para.setAlignment(Element.ALIGN_CENTER);
			cell.addElement(para);
			tableheader.addCell(cell);

			cell = new PdfPCell();
			cell.setBorder(0);
			cell.setPaddingBottom(10);
			cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
			para=new Paragraph("NGHI????M THU THU???C, VTYTTH, H??A CH???T, D???CH TRUY???N",new Font(bf,16,Font.BOLD));
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
			para=new Paragraph("C??n c??? H???p ?????ng s???: "+ sohopdong+ " / ???......"+" ng??y "+ ngayHD[2]+" th??ng "+ngayHD[1]+" n??m "+ngayHD[0],font10_ilatic);
			}
			else
			{
				para=new Paragraph("C??n c??? H???p ?????ng s???: " +sohopdong+ " / ???...... ng??y........ th??ng....... n??m ........",font10_ilatic);
				
			}
			para.setAlignment(Element.ALIGN_CENTER);
			cell.addElement(para);
			String date=getDate();
			para=new Paragraph("H??m nay, l??c  ... gi??? ...  ph??t, ng??y "+ date.substring(8)+" th??ng "+ date.substring(5,7)+ " n??m "+date.substring(0,4) + ", ch??ng t??i g???m c??:",font10_ilatic);
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
			chunk=new Chunk("B??N NH???N (B??n A)", font10_Bold);
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

			para=new Paragraph("?????a ch???: "+kh_Diachi,font10_normal);
			para.setAlignment(Element.ALIGN_LEFT);
			cell.addElement(para);

			para=new Paragraph("??i???n tho???i: " +kh_dienthoai         +"                 -Fax:"+kh_fax,font10_normal); // kem fax
			para.setAlignment(Element.ALIGN_LEFT);
			cell.addElement(para);

			para=new Paragraph("M?? s??? thu???: "+kh_MST,font10_normal);
			para.setAlignment(Element.ALIGN_LEFT);
			cell.addElement(para);


			para=new Paragraph("T??i kho???n:  "+kh_sotaikhoan,font10_normal); //tai ngan hang nao?
			para.setAlignment(Element.ALIGN_LEFT);
			cell.addElement(para);

			para=new Paragraph("?????i di???n:                                                                    Ch???c v???:",font10_normal);
			para.setAlignment(Element.ALIGN_LEFT);
			cell.addElement(para);
			tbl.addCell(cell);

			// ben giao
			cell = new PdfPCell();
			cell.setBorder(0);
			cell.setHorizontalAlignment(Element.ALIGN_LEFT);
			para=new Paragraph();
			para.setAlignment(Element.ALIGN_LEFT);
			chunk=new Chunk("B??N GIAO (B??n B)", font10_Bold);
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

			para=new Paragraph("?????a ch???: "+cty_Diachi,font10_normal);
			para.setAlignment(Element.ALIGN_LEFT);
			cell.addElement(para);

			para=new Paragraph("??i???n tho???i: "+ cty_Dienthoai +"  ??? Fax: "+cty_Fax,font10_normal); // kem fax
			para.setAlignment(Element.ALIGN_LEFT);
			cell.addElement(para);

			para=new Paragraph("M?? s??? thu???: "+cty_MST,font10_normal);
			para.setAlignment(Element.ALIGN_LEFT);
			cell.addElement(para);


			para=new Paragraph("T??i kho???n: " +cty_Sotaikhoan + " t???i " +cty_bank,font10_normal); //tai ngan hang nao?
			para.setAlignment(Element.ALIGN_LEFT);
			cell.addElement(para);

			para=new Paragraph("?????i di???n:                                                                    Ch???c v???:",font10_normal);
			para.setAlignment(Element.ALIGN_LEFT);
			cell.addElement(para);
			tbl.addCell(cell);

			//loi xac nhan
			cell = new PdfPCell();
			cell.setBorder(0);
			cell.setHorizontalAlignment(Element.ALIGN_LEFT);
			para=new Paragraph("      Hai b??n th???ng nh????t nghi???m thu v?? b??n giao thu???c, VTYTTH, h??a ch???t, d???ch truy???n theo H???p ?????ng ???? k?? v???i chi ti???t nh?? sau:",font10_normal);
			para.setAlignment(Element.ALIGN_LEFT);
			cell.addElement(para);
			para=new Paragraph("N???i dung:",font10_Bold);
			para.setAlignment(Element.ALIGN_LEFT);
			cell.addElement(para);
			para=new Paragraph("      B??n B b??n giao cho b??n A thu???c, VTYTTH, h??a ch???t, d???ch truy???n theo h??a ????n:",font10_normal);
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
			String[] tieude = new String[] { "TT","S??? H??","Ng??y xu???t h??a ????n","Th??nh ti???n \n (VN??)"};
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
			para = new Paragraph("T???NG C???NG " , new Font(bf, 10, Font.BOLD));
			para.setAlignment(Element.ALIGN_CENTER);
			cell.addElement(para);
			tbl_sanpham.addCell(cell);

			cell = new PdfPCell();
			cell.setVerticalAlignment(Element.ALIGN_RIGHT);
			cell.setPaddingBottom(7);
			para = new Paragraph(DinhDangTraphacoDMS(formatter1.format(tongtien)), new Font(bf, 10, Font.BOLD));
			para.setAlignment(Element.ALIGN_RIGHT);
			cell.addElement(para);
			tbl_sanpham.addCell(cell);

			document.add(tbl_sanpham);

			//=========== footer

			PdfPTable tbl_footer =new PdfPTable(1);
			tbl_footer.setWidthPercentage(100);
			doctienrachu doctien = new doctienrachu();
			String tien = doctien.docTien((long)tongtien);
			String TienIN = (tien.substring(0,1)).toUpperCase() + tien.substring(1); //Vi???t hoa k?? t??? ?????u ti??n
			cell = new PdfPCell();
			cell.setBorder(0);
			para = new Paragraph("      S??? ti???n b???ng ch???:  " +TienIN, new Font(bf,12,Font.NORMAL)); //cong doc tine
			para.setAlignment(Element.ALIGN_JUSTIFIED);
			cell.addElement(para);
			tbl_footer.addCell(cell);

			cell = new PdfPCell();
			cell.setBorder(0);
			para = new Paragraph("K????t lu????n: ", new Font(bf,12,Font.BOLD)); //cong doc tine
			para.setAlignment(Element.ALIGN_JUSTIFIED);
			cell.addElement(para);
			tbl_footer.addCell(cell);

			cell = new PdfPCell();
			cell.setBorder(0);
			para = new Paragraph("      - B??n A ???? ki???m tra ????ng ch???ng lo???i, h??m l?????ng, s??? l??, h???n d??ng,??????.. c???a h??ng h??a theo y??u c???u.", font10_normal); 
			para.setAlignment(Element.ALIGN_JUSTIFIED);
			cell.addElement(para);
			tbl_footer.addCell(cell);

			cell = new PdfPCell();
			cell.setBorder(0);
			para = new Paragraph("      - B??n A ???? nh???n ?????y ????? s??? l?????ng h??ng h??a. ", font10_normal); 
			para.setAlignment(Element.ALIGN_JUSTIFIED);
			cell.addElement(para);
			tbl_footer.addCell(cell);

			cell = new PdfPCell();
			cell.setBorder(0);
			para = new Paragraph("      - Trong th???i gian s??? d???ng n???u b??n A ph??t hi???n b??n trong c?? s??? thi???u h???t v??? s??? l?????ng ho???c k??m ch???t l?????ng ??????,"+
					" B??n A s??? th??ng b??o ngay cho B??n B b???ng c??ch ??i???n tho???i, fax ho???c b???ng v??n b???n ????? c??ng b??n b???c gi???i quy???t.", font10_normal); 
			para.setAlignment(Element.ALIGN_JUSTIFIED);
			cell.addElement(para);
			tbl_footer.addCell(cell);

			cell = new PdfPCell();
			cell.setBorder(0);
			para = new Paragraph("      - B??n A th???ng nh???t nghi???m thu v?? thanh to??n ti???n thu???c, h??a ch???t, VTYTTHcho b??n B theo ????ng H???p ?????ng ???? k?? k????t.", font10_normal); 
			para.setAlignment(Element.ALIGN_JUSTIFIED);
			cell.addElement(para);
			tbl_footer.addCell(cell);

			cell = new PdfPCell();
			cell.setBorder(0);
			para = new Paragraph("      Bi??n b???n l???p th??nh 04 b???n, b??n A nh???n 03 b???n, b??n B  nh???n 01 b???n, ?????u c?? gi?? tr??? nh?? nhau.", font10_normal); 
			para.setAlignment(Element.ALIGN_JUSTIFIED);
			cell.addElement(para);
			tbl_footer.addCell(cell);

			cell = new PdfPCell();
			cell.setBorder(0);
			cell.setPaddingLeft(1.5f*CONVERT);
			para = new Paragraph("?????I DI???N B??N A"+
					"                                                                               "+
					"?????I DI???N B??N B", font10_Bold);
			cell.addElement(para);
			tbl_footer.addCell(cell);

		/*	cell = new PdfPCell();
			cell.setBorder(0);
			cell.setPaddingLeft(1.5f*CONVERT);
			para = new Paragraph("       GI??M ?????C                                              "+
					"                                                       TUQ. T???NG GI??M ?????C", new Font(bf,10,Font.BOLD));
			cell.addElement(para);
			tbl_footer.addCell(cell);*/

			document.add(tbl_footer);

			document.close();

		} catch (Exception e) {
			e.printStackTrace();

		}

	}


	private void form_phieunghiemthu3( Document document,ServletOutputStream outstream,  IErpHoadontaichinhNPPList obj, String[] loai, String sohopdong){
		dbutils db = new dbutils();
		//lay thong tin hop dong
		String ngayhopdong="";
		String qr_hopdong="select ngaytao as ngayhopdong from erp_hopdongnpp where PK_SEQ="+sohopdong;	
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
		
		
		//lay thong khah hang
		String Donvi="";
		String kh_MST ="";
		String kh_dienthoai ="";
		String kh_Diachi="";
		String kh_sotaikhoan="";
		String kh_fax="";
		String sql="";	
		
		try {
			//etc
			if(loai[0].equals("ETC")){
				sql="select kh.pk_seq, kh.TEN as donvi,isnull(kh.chucuahieu,'') as chucuahieu, kh.diachi as diachi ,isnull(masothue,'') as masothue, '' as taikhoan,  "+    
				" isnull(dienthoai,'') as dienthoai, ''  as fax , ''  as nganhang "+    
				" from KHACHHANG kh where kh.pk_seq="+loai[1]; 

				System.out.println("\n L???y TT KH etc: "+sql +"\n");

				ResultSet rsLayKH= db.get(sql);
				if(rsLayKH!=null){
					try {
						while(rsLayKH.next())
						{
							Donvi = rsLayKH.getString("DONVI");
							//nguoimuahang=rsLayKH.getString("chucuahieu");
							kh_MST = rsLayKH.getString("MASOTHUE");
							kh_Diachi = rsLayKH.getString("DIACHI");
							kh_sotaikhoan=rsLayKH.getString("TAIKHOAN") ;
							kh_fax=rsLayKH.getString("fax");
							kh_dienthoai=rsLayKH.getString("dienthoai");
							rsLayKH.close();

						}  
						rsLayKH.close();
					} catch (Exception e) {
						e.printStackTrace();
					}
				} 
			

			}
			else
			{
				//doi tac (nha phan phoi)
				sql = "select kh.pk_seq, kh.TEN as donvi, '' as chucuahieu , kh.diachi as diachi,  kh.masothue , kh.sotaikhoan as taikhoan, "+
				"\n isnull(dienthoai,'') as dienthoai, isnull(fax,'') as fax , isnull(nganhang,'') as nganhang "+    
				"\n from NHAPHANPHOI kh where kh.pk_seq="+loai[1];
				System.out.println("\n L???y TT KH doi tac "+sql +"\n");

				ResultSet rsLayKH= db.get(sql);
				if(rsLayKH!=null){
					try {
						while(rsLayKH.next())
						{
							Donvi = rsLayKH.getString("DONVI");
							kh_MST = rsLayKH.getString("MASOTHUE");
							kh_Diachi = rsLayKH.getString("DIACHI");
							kh_sotaikhoan=rsLayKH.getString("TAIKHOAN") ;
							if(rsLayKH.getString("NGANHANG").length()>0){
								kh_sotaikhoan=kh_sotaikhoan+" T???i " +rsLayKH.getString("NGANHANG");
							}
							kh_fax=rsLayKH.getString("fax");
							kh_dienthoai=rsLayKH.getString("dienthoai");
							rsLayKH.close();

						}  
						rsLayKH.close();
					} catch (Exception e) {
						e.printStackTrace();
					}
				} 
				
			}
		} catch (Exception e) {
			e.printStackTrace();
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
			para=new Paragraph("C???NG H??A X?? H???I CH??? NGH??A VI???T NAM",font10_Bold);
			para.setAlignment(Element.ALIGN_CENTER);
			cell.addElement(para);
			tableheader.addCell(cell);

			// dong 2 
			cell = new PdfPCell();
			cell.setBorder(0);
			cell.setHorizontalAlignment(Element.ALIGN_LEFT);
			para=new Paragraph();
			para.setAlignment(Element.ALIGN_CENTER);
			chunk=new Chunk("?????c L???p - T??? Do - H???nh Ph??c",font10_Bold);
			chunk.setUnderline(1f, -2);
			para.add(chunk);
			cell.addElement(para);
			tableheader.addCell(cell);

			// dong 3 
			cell = new PdfPCell();
			cell.setBorder(0);
			cell.setPaddingTop(10);
			cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
			para=new Paragraph("BI??N B???N",new Font(bf,16,Font.BOLD));
			para.setAlignment(Element.ALIGN_CENTER);
			cell.addElement(para);
			tableheader.addCell(cell);

			cell = new PdfPCell();
			cell.setBorder(0);
			cell.setPaddingBottom(10);
			cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
			para=new Paragraph("NGHI????M THU THU???C, VTYTTH, H??A CH???T, D???CH TRUY???N",new Font(bf,16,Font.BOLD));
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
			para=new Paragraph("C??n c??? H???p ?????ng s???: "+ sohopdong+ " / ???......"+" ng??y "+ ngayHD[2]+" th??ng "+ngayHD[1]+" n??m "+ngayHD[0],font10_ilatic);
			}
			else
			{
				para=new Paragraph("C??n c??? H???p ?????ng s???: " +sohopdong+ " / ???...... ng??y........ th??ng....... n??m ........",font10_ilatic);
				
			}
			para.setAlignment(Element.ALIGN_CENTER);
			cell.addElement(para);
			String date=getDate();
			para=new Paragraph("H??m nay, l??c  ... gi??? ...  ph??t, ng??y "+ date.substring(8)+" th??ng "+ date.substring(5,7)+ " n??m "+date.substring(0,4) + ", ch??ng t??i g???m c??:",font10_ilatic);
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
			chunk=new Chunk("B??N NH???N (B??n A)", font10_Bold);
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

			para=new Paragraph("?????a ch???: "+kh_Diachi,font10_normal);
			para.setAlignment(Element.ALIGN_LEFT);
			cell.addElement(para);

			para=new Paragraph("??i???n tho???i: " +kh_dienthoai         +"                 -Fax:"+kh_fax,font10_normal); // kem fax
			para.setAlignment(Element.ALIGN_LEFT);
			cell.addElement(para);

			para=new Paragraph("M?? s??? thu???: "+kh_MST,font10_normal);
			para.setAlignment(Element.ALIGN_LEFT);
			cell.addElement(para);


			para=new Paragraph("T??i kho???n:  "+kh_sotaikhoan,font10_normal); //tai ngan hang nao?
			para.setAlignment(Element.ALIGN_LEFT);
			cell.addElement(para);

			para=new Paragraph("?????i di???n:                                                                    Ch???c v???:",font10_normal);
			para.setAlignment(Element.ALIGN_LEFT);
			cell.addElement(para);
			tbl.addCell(cell);
			
			// ben giao
			cell = new PdfPCell();
			cell.setBorder(0);
			cell.setHorizontalAlignment(Element.ALIGN_LEFT);
			para=new Paragraph();
			para.setAlignment(Element.ALIGN_LEFT);
			chunk=new Chunk("B??N GIAO (B??n B)", font10_Bold);
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

			para=new Paragraph("?????a ch???: "+cty_Diachi,font10_normal);
			para.setAlignment(Element.ALIGN_LEFT);
			cell.addElement(para);

			para=new Paragraph("??i???n tho???i: "+ cty_Dienthoai +"  ??? Fax: "+cty_Fax,font10_normal); // kem fax
			para.setAlignment(Element.ALIGN_LEFT);
			cell.addElement(para);

			para=new Paragraph("M?? s??? thu???: "+cty_MST,font10_normal);
			para.setAlignment(Element.ALIGN_LEFT);
			cell.addElement(para);


			para=new Paragraph("T??i kho???n: " +cty_Sotaikhoan + " t???i " +cty_bank,font10_normal); //tai ngan hang nao?
			para.setAlignment(Element.ALIGN_LEFT);
			cell.addElement(para);

			para=new Paragraph("?????i di???n:                                                                      Ch???c v???:",font10_normal);
			para.setAlignment(Element.ALIGN_LEFT);
			cell.addElement(para);
			tbl.addCell(cell);

			//loi xac nhan
			cell = new PdfPCell();
			cell.setBorder(0);
			cell.setHorizontalAlignment(Element.ALIGN_LEFT);
			para=new Paragraph("      Hai b??n th???ng nh????t nghi???m thu v?? b??n giao thu???c, VTYTTH, h??a ch???t, d???ch truy???n theo H???p ?????ng ???? k?? v???i chi ti???t nh?? sau:",font10_normal);
			para.setAlignment(Element.ALIGN_LEFT);
			cell.addElement(para);
			para=new Paragraph("N???i dung:",font10_Bold);
			para.setAlignment(Element.ALIGN_LEFT);
			cell.addElement(para);
			para=new Paragraph("      B??n B b??n giao cho b??n A thu???c, VTYTTH, h??a ch???t, d???ch truy???n theo h??a ????n:",font10_normal);
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
			String[] tieude = new String[] { "TT"," T??n H??ng H??a ","????n v??? t??nh","S??? l?????ng","????n gi?? (VAT)","Th??nh ti???n \n (VN??)"};
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
				" DONVI as DONVI,  "+    
				"DONGIA AS DONGIA,sum( SOLUONG ) AS SOLUONG     "+    
				"from ERP_HOADONNPP_SP_CHITIET where hoadon_fk in ("+ dshoadon+") "+    
				"group by  ma,DONGIA,DONVI,ten";
			
				System.out.println(" qr lay san pham : "+query_sp);
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
			para = new Paragraph("T???NG C???NG " , new Font(bf, 10, Font.BOLD));
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
			String TienIN = (tien.substring(0,1)).toUpperCase() + tien.substring(1); //Vi???t hoa k?? t??? ?????u ti??n
			cell = new PdfPCell();
			cell.setBorder(0);
			para = new Paragraph("      S??? ti???n b???ng ch???:  " +TienIN, new Font(bf,12,Font.NORMAL)); //cong doc tine
			para.setAlignment(Element.ALIGN_JUSTIFIED);
			cell.addElement(para);
			tbl_footer.addCell(cell);

			cell = new PdfPCell();
			cell.setBorder(0);
			para = new Paragraph("K????t lu????n: ", new Font(bf,12,Font.BOLD)); //cong doc tine
			para.setAlignment(Element.ALIGN_JUSTIFIED);
			cell.addElement(para);
			tbl_footer.addCell(cell);

			cell = new PdfPCell();
			cell.setBorder(0);
			para = new Paragraph("      - B??n A ???? ki???m tra ????ng ch???ng lo???i, h??m l?????ng, s??? l??, h???n d??ng,??????.. c???a h??ng h??a theo y??u c???u.", font10_normal); 
			para.setAlignment(Element.ALIGN_JUSTIFIED);
			cell.addElement(para);
			tbl_footer.addCell(cell);

			cell = new PdfPCell();
			cell.setBorder(0);
			para = new Paragraph("      - B??n A ???? nh???n ?????y ????? s??? l?????ng h??ng h??a. ", font10_normal); 
			para.setAlignment(Element.ALIGN_JUSTIFIED);
			cell.addElement(para);
			tbl_footer.addCell(cell);

			cell = new PdfPCell();
			cell.setBorder(0);
			para = new Paragraph("      - Trong th???i gian s??? d???ng n???u b??n A ph??t hi???n b??n trong c?? s??? thi???u h???t v??? s??? l?????ng ho???c k??m ch???t l?????ng ??????,"+
					" B??n A s??? th??ng b??o ngay cho B??n B b???ng c??ch ??i???n tho???i, fax ho???c b???ng v??n b???n ????? c??ng b??n b???c gi???i quy???t.", font10_normal); 
			para.setAlignment(Element.ALIGN_JUSTIFIED);
			cell.addElement(para);
			tbl_footer.addCell(cell);

			cell = new PdfPCell();
			cell.setBorder(0);
			para = new Paragraph("      - B??n A th???ng nh???t nghi???m thu v?? thanh to??n ti???n thu???c, h??a ch???t, VTYTTHcho b??n B theo ????ng H???p ?????ng ???? k?? k????t.", font10_normal); 
			para.setAlignment(Element.ALIGN_JUSTIFIED);
			cell.addElement(para);
			tbl_footer.addCell(cell);

			cell = new PdfPCell();
			cell.setBorder(0);
			para = new Paragraph("      Bi??n b???n l???p th??nh 04 b???n, b??n A nh???n 03 b???n, b??n B  nh???n 01 b???n, ?????u c?? gi?? tr??? nh?? nhau.", font10_normal); 
			para.setAlignment(Element.ALIGN_JUSTIFIED);
			cell.addElement(para);
			tbl_footer.addCell(cell);

			cell = new PdfPCell();
			cell.setBorder(0);
			cell.setPaddingLeft(1.5f*CONVERT);
			para = new Paragraph("?????I DI???N B??N A"+
					"                                                                               "+
					"?????I DI???N B??N B", font10_Bold);
			cell.addElement(para);
			tbl_footer.addCell(cell);

			/*cell = new PdfPCell();
			cell.setBorder(0);
			cell.setPaddingLeft(1.5f*CONVERT);
			para = new Paragraph("       GI??M ?????C                                              "+
					"                                                       TUQ. T???NG GI??M ?????C", new Font(bf,10,Font.BOLD));
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


