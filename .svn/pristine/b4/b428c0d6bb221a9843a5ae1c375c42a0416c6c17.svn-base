package geso.dms.distributor.servlets.phieuxuatkho;

import geso.dms.distributor.beans.phieuxuatkho.IPhieuxuatkho;
import geso.dms.distributor.beans.phieuxuatkho.IPhieuxuatkhoList;
import geso.dms.distributor.beans.phieuxuatkho.imp.Phieuxuatkho;
import geso.dms.distributor.beans.phieuxuatkho.imp.PhieuxuatkhoList;
import geso.dms.center.db.sql.dbutils;
import geso.dms.center.util.Utility;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.ResultSet;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class PhieuxuatkhoSvl extends HttpServlet 
{
	private static final long serialVersionUID = 1L;
	
    public PhieuxuatkhoSvl() 
    {
        super();
    }
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		IPhieuxuatkhoList obj;
		PrintWriter out = response.getWriter();; 
		String userId;
		
		request.setCharacterEncoding("UTF-8");
	    response.setCharacterEncoding("UTF-8");
	    response.setContentType("text/html; charset=UTF-8");
	    	    
	    HttpSession session = request.getSession();

	    Utility util = new Utility();
	    out = response.getWriter();
	    	    
	    String querystring = request.getQueryString();
	    userId = util.getUserId(querystring);
	    //out.println(userId);
	    
	    if (userId.length() == 0 )
	    	userId = request.getParameter("userId");
	    
	    String action = util.getAction(querystring);
	    out.println(action);
	    
	    String pxkId = util.getId(querystring);

	    String msg = "";
	    if(!geso.dms.distributor.util.Utility.val_doget(session, request, response))
		{
			session.setAttribute("flag",null);
			response.sendRedirect(request.getContextPath() + "/redirect.jsp");
			return;
		}
		else
		{
			session.setAttribute("flag",null);
		}
	    if(action.equals("chotphieu"))
	    {
	    	String nppId = util.antiSQLInspection(request.getParameter("nppId"));
	    	String ngaylap = util.antiSQLInspection(request.getParameter("ngaylap"));

    	
    		
			msg = this.Chotphieuxuat(pxkId, nppId, ngaylap);
			if(msg.length() > 0)
			{
				out.println("Khong the chot phieu xuat...\n");
				System.out.println("Error Mesege: " + msg + "\n");
			}
	    }
	    else
	    {
	    	if(action.equals("delete"))
	    	{
	    		DeletePxk(pxkId);
	    	}
	    }
	   	    
	    obj = new PhieuxuatkhoList();
	    obj.setRequestObj(request);
	    obj.setUserId(userId);
	    
	    obj.init("");
	    obj.setMsg(msg);
	    
		session.setAttribute("obj", obj);
				
		String nextJSP = request.getContextPath() + "/pages/Distributor/PhieuXuatKho.jsp";
		response.sendRedirect(nextJSP);
	}

	private String DeletePxk(String pxkId) 
	{
		dbutils db = new dbutils();
		
		String msg = "";
		Utility util = new Utility();
		msg= util.Check_Huy_NghiepVu_KhoaSo("PHIEUXUATKHO", pxkId, "ngaylapphieu", db);
		if(msg.length()>0)
		{
			db.shutDown();
			return msg;
		}
		
		try
		{
			db.getConnection().setAutoCommit(false);
			String query="delete phieuxuatkho_tienkm where pxk_fk = '" + pxkId + "'";
			if(!db.update(query)){
				db.getConnection().rollback();
				return query;
			}

			//System.out.println("delete phieuxuatkho_tienkm where pxk_fk = '" + pxkId + "'");
			query="delete phieuxuatkho_sanpham where pxk_fk = '" + pxkId + "'";
			System.out.println(query);
			if(! db.update(query)){
				db.getConnection().rollback();
				return query;
			}
			//System.out.println("delete phieuxuatkho_sanpham where pxk_fk = '" + pxkId + "'");
			query="delete phieuxuatkho_spkm where pxk_fk = '" + pxkId + "'";
			System.out.println(query);
			if(! db.update(query)){
				db.getConnection().rollback();
				return query;
			}
			query="delete PHIEUXUATKHO_DONHANG where pxk_fk='"+pxkId+"'";
			System.out.println(query);
			if(! db.update(query)){
				db.getConnection().rollback();
				return query;
			}
			//System.out.println("delete phieuxuatkho_spkm where pxk_fk = '" + pxkId + "'");
			query="delete phieuxuatkho where pk_seq = '" + pxkId + "'";
			System.out.println(query);
			if(! db.update(query)){
				db.getConnection().rollback();
				return query;
			}
			//System.out.println("delete phieuxuatkho where pk_seq = '" + pxkId + "'");

			db.getConnection().commit();
			db.getConnection().setAutoCommit(true);
			db.shutDown();

		}
		catch(Exception er)
		{
			try {
				db.getConnection().rollback();
				db.shutDown();
			} 
			catch (Exception e) { }

			er.printStackTrace();
			System.out.println(er);
		}

		return "";
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		IPhieuxuatkhoList obj = new PhieuxuatkhoList();
		PrintWriter out = response.getWriter();; 
		String userId;
		
		request.setCharacterEncoding("UTF-8");
	    response.setCharacterEncoding("UTF-8");
	    response.setContentType("text/html; charset=UTF-8");
	    
		HttpSession session = request.getSession();
	    userId = request.getParameter("userId");
	    geso.dms.center.util.Csrf csdr=new geso.dms.center.util.Csrf(request,response,false,true ,false);
		Utility util = new Utility();
		if(!csdr.__validate_post())
		{
			response.sendRedirect(request.getContextPath() + "/redirect.jsp");
			return;
		}
		String action = util.antiSQLInspection(request.getParameter("action"));
	    if (action == null){
	    	action = "";
	    }
	    out.println(action); 
	    obj.setRequestObj(request);
	    if (action.equals("Tao moi"))
	    {
	    	// Empty Bean for distributor
	    	IPhieuxuatkho pxkBean = (IPhieuxuatkho) new Phieuxuatkho("");
	    	pxkBean.setUserId(userId);
	    	pxkBean.createRS();
	    	
	    	// Save Data into session
	    	session.setAttribute("pxkBean", pxkBean);
    		
    		String nextJSP = request.getContextPath() + "/pages/Distributor/PhieuXuatKhoNew.jsp";
    		response.sendRedirect(nextJSP);
    		
	    }
	    else if(action.equals("clear"))
	    {
	    	obj.setUserId(userId);
	    	obj.init("");
	    	session.setAttribute("obj", obj);  	
    		session.setAttribute("userId", userId);
    		response.sendRedirect(request.getContextPath() + "/pages/Distributor/PhieuXuatKho.jsp");	 
	    }
	    
	    else if(action.equals("view") || action.equals("next") || action.equals("prev"))
	    {
	    	String search = getSearchQuery(request, obj);
	    	
	    	obj.setNxtApprSplitting(Integer.parseInt(request.getParameter("nxtApprSplitting")));
	    	obj.setUserId(userId);
	    	obj.init(search);
	    	obj.setAttribute(request, action, "list", "crrApprSplitting", "nxtApprSplitting");
	    	session.setAttribute("obj", obj);
	    	response.sendRedirect(request.getContextPath() + "/pages/Distributor/PhieuXuatKho.jsp");	
	    }
	    
	    else
	    {
	    	obj = new PhieuxuatkhoList();
	    	obj.setRequestObj(request);
	    	
	    	
	    	obj.setUserId(userId);
	    	String search = getSearchQuery(request, obj);
	    	
	    	
	    	obj.init(search);
				
	    	session.setAttribute("obj", obj);  	
    		session.setAttribute("userId", userId);
    		session.setAttribute("abc", search);
		
    		response.sendRedirect(request.getContextPath() + "/pages/Distributor/PhieuXuatKho.jsp");	    		    	
	    }
	    
	}
	
	private String getSearchQuery(HttpServletRequest request, IPhieuxuatkhoList obj) 
	{	
		String nppId = request.getParameter("nppId");
		geso.dms.center.util.Utility utilCenter = new geso.dms.center.util.Utility();
    	if (nppId == null)
    		nppId = "";
    	obj.setNppId(nppId);
		String nvgnId = utilCenter.antiSQLInspection(request.getParameter("nvgnTen"));
    	if (nvgnId == null)
    		nvgnId = "";
    	obj.setNvgnId(nvgnId);
		String trangthai = utilCenter.antiSQLInspection(request.getParameter("trangthai"));

    	if (trangthai == null)
    		trangthai = "";
    	obj.setTrangthai(trangthai);
		String tungay = utilCenter.antiSQLInspection(request.getParameter("tungay"));

    	if (tungay == null)
    		tungay = "";    	
    	obj.setTungay(tungay);
		String denngay = utilCenter.antiSQLInspection(request.getParameter("denngay"));

    	if (denngay == null)
    		denngay = "";
    	obj.setDenngay(denngay);
		String mapxk = utilCenter.antiSQLInspection(request.getParameter("mapxk"));

    	if (mapxk == null)
    		mapxk = "";
    	obj.setmaPXK(mapxk);
		String maFast = utilCenter.antiSQLInspection(request.getParameter("mafast"));

    	if(maFast == null)
    		maFast="";
    	obj.setmaFast(maFast);
		String maDonhang = utilCenter.antiSQLInspection(request.getParameter("madonhang"));

    	if(maDonhang == null)
    		maDonhang="";
    	obj.setmaDonhang(maDonhang);
		String khachhang = utilCenter.antiSQLInspection(request.getParameter("khId"));

    	if(khachhang == null)
    		khachhang="";
    	obj.setkhachHang(khachhang);
     		
    	String query = "\n select pxk.sonetId, pxk.pk_seq as pxkId, nvgn.pk_seq as nvgnId, nvgn.ten as nvgnTen, " +
		"\n pxk.trangthai, pxk.ngaylapphieu, pxk.ngaytao, pxk.ngaysua, nv1.ten as nguoitao, nv2.ten as nguoisua, " +
		"\n pxkDH.kh khachhang, pxkDH.dh donhang" +
		"\n from phieuxuatkho pxk " +
		"\n outer apply " +
		"\n ( " +
		"\n    select STUFF " +
		"\n    (( " +
		"\n        SELECT distinct ', ' + KH.TEN " +
		"\n        from PHIEUXUATKHO_DONHANG YCXK1 " +
		"\n        inner join HOADON HD on YCXK1.hoadon_fk = HD.PK_SEQ " +
		"\n        inner join KHACHHANG KH on HD.KHACHHANG_FK = KH.PK_SEQ " +
		"\n        where YCXK1.PXK_FK = pxk.pk_seq " +
		"\n        for XML PATH ('')" +
		"\n    ),1,2,'') kh, " +
		"\n    STUFF " +
		"\n    ((" +
		"\n        SELECT ', ' + convert(varchar,YCXK1.donhang_fk) " +
		"\n        from PHIEUXUATKHO_DONHANG YCXK1 " +
		"\n        inner join HOADON HD on YCXK1.hoadon_fk = HD.PK_SEQ " +
		"\n        inner join KHACHHANG KH on HD.KHACHHANG_FK = KH.PK_SEQ " +
		"\n        where YCXK1.PXK_FK = pxk.pk_seq " +
		"\n        for XML PATH ('') " +
		"\n    ),1,2,'') dh " +
		"\n ) pxkDH " +
		"\n inner join nhanviengiaonhan nvgn on pxk.nvgn_fk = nvgn.pk_seq " +
		"\n inner join nhanvien nv1 on pxk.nguoitao = nv1.pk_seq " +
		"\n inner join nhanvien nv2 on pxk.nguoisua = nv2.pk_seq " +
		"\n where pxk.npp_fk = '" + nppId + "' ";			
    	
    	if (nvgnId.length() > 0)
    	{
			query = query + " and nvgn.pk_seq='" + nvgnId + "'";			
    	}
    	
    	if (trangthai.length() > 0)
    	{
			query = query + " and pxk.trangthai='" + trangthai + "'";			
    	}
    	
    	if (tungay.length() > 0)
    	{
    		query = query + " and pxk.ngaytao >= '" + tungay + "'"; 
    	}
    	
    	if (denngay.length() > 0)
    	{
    		query = query + " and pxk.ngaytao <= '" + denngay + "'"; 
    	}
    	
    	if (mapxk.length() > 0)
    	{
    		query = query + " and pxk.pk_seq like'%" + mapxk + "%'"; 
    	}
    	
    	if(maFast.length() >0){
    		query = query + " and exists ( select 1 from khachhang where  maFAST like '%"+ maFast + "%' and pk_seq in ( select khachhang_fk from donhang where pk_seq in (select donhang_fk from PHIEUXUATKHO_DONHANG where PXK_FK = pxk.PK_SEQ) ) )";
    	}
    	if(maDonhang.length() >0){
    		query = query + " and exists ( select 1 from PHIEUXUATKHO_DONHANG where PXK_FK = pxk.PK_SEQ and donhang_fk ='"+ maDonhang + "' ) ";
    	}
    	
    	if(khachhang.length() >0){
    		Utility util = new Utility();
    		query = query + " and exists ( select 1 from khachhang where  timkiem  like N'%"+ util.replaceAEIOU(khachhang)  + "%' and pk_seq in ( select khachhang_fk from donhang where pk_seq in (select donhang_fk from PHIEUXUATKHO_DONHANG where PXK_FK = pxk.PK_SEQ) ) )";
    	}
    	
    	
    	
    	System.out.print("\n Search Query la: " + query + "\n");
    	
    	return query;
	}
	
	private String Chotphieuxuat(String pxkId, String nppId, String ngaylap) 
	{	
		dbutils db = new dbutils();
		String msg = "";
		Utility util = new Utility();
		msg= util.Check_Huy_NghiepVu_KhoaSo("PHIEUXUATKHO", pxkId, "ngaylaphphieu", db);
		if(msg.length()>0)
		{
			db.shutDown();
			return msg;
		}
		try
		{
			db.getConnection().setAutoCommit(false);
			
			String query = "";
			
			//HÀNG BÁN
			query = "  insert PHIEUXUATKHO_SANPHAM( PXK_FK, SANPHAM_FK, kbh_fk, kho_fk, SOLUONG )  " + 
					"  select  '" + pxkId + "', hd_sp.SANPHAM_FK, hd.KBH_FK, isnull(hd_sp.KHO_FK, hd.kho_fk), sum(hd_sp.SOLUONG) as SOLUONG " + 
					"  from HOADON hd inner join HOADON_SP hd_sp on hd.PK_SEQ = hd_sp.HOADON_FK " + 
					"  where hd.loaihoadon = 0 and hd.PK_SEQ in ( select hoadon_fk from PHIEUXUATKHO_DONHANG where PXK_FK = '" + pxkId + "' ) " + 
					"  group by hd_sp.SANPHAM_FK, hd.KBH_FK, isnull(hd_sp.KHO_FK, hd.kho_fk) ";
			System.out.println(":: CHEN HANG BAN: " + query);
			if( !db.update(query) )
			{
				msg = "Khong the cap nhat PHIEUXUATKHO_SANPHAM: " + query;
				db.getConnection().rollback();
				return msg;
			}
			
			query = "  insert PHIEUXUATKHO_SANPHAM_CHITIET( PXK_FK, KHO_FK, KBH_FK, SANPHAM_FK, SOLUONG, SOLO, NGAYHETHAN, NGAYNHAPKHO )  " + 
					"  select  '" + pxkId + "', isnull(hd_sp.KHO_FK, hd.kho_fk), hd.KBH_FK, sp.PK_SEQ as SANPHAM_FK, sum(hd_sp.SOLUONG) as SOLUONG, hd_sp.solo, hd_sp.NGAYHETHAN, hd_sp.NGAYNHAPKHO " + 
					"  from HOADON hd inner join HOADON_SP_CHITIET hd_sp on hd.PK_SEQ = hd_sp.HOADON_FK " + 
					"  		inner join SANPHAM sp on hd_sp.MA = sp.MA " + 
					"  where hd.loaihoadon = 0 and hd.PK_SEQ in ( select hoadon_fk from PHIEUXUATKHO_DONHANG where PXK_FK = '" + pxkId + "' ) " + 
					"  group by isnull(hd_sp.KHO_FK, hd.kho_fk), hd.KBH_FK, sp.PK_SEQ, hd_sp.solo, hd_sp.NGAYHETHAN, hd_sp.NGAYNHAPKHO ";
			System.out.println(":: CHEN HANG BAN CT: " + query);
			if( !db.update(query) )
			{
				msg = "Khong the cap nhat PHIEUXUATKHO_SANPHAM_CHITIET: " + query;
				db.getConnection().rollback();
				return msg;
			}
			
			//HÀNG KHUYẾN MẠI
			query = "  insert PHIEUXUATKHO_SPKM( PXK_FK, SANPHAM_FK, kbh_fk, kho_fk, scheme, SOLUONG )  " + 
					"  select  '" + pxkId + "', hd_sp.SANPHAM_FK, hd.KBH_FK, isnull(hd_sp.KHO_FK, hd.kho_fk), km.pk_seq, sum(hd_sp.SOLUONG) as SOLUONG  " + 
					"  from HOADON hd inner join HOADON_CTKM_TRAKM hd_sp on hd.PK_SEQ = hd_sp.HOADON_FK inner join CTKHUYENMAI km on hd_sp.CTKM = km.SCHEME     " + 
					"  where hd.loaihoadon != 0 and hd.PK_SEQ in ( select hoadon_fk from PHIEUXUATKHO_DONHANG where PXK_FK = '" + pxkId + "' )  " + 
					"  group by hd_sp.SANPHAM_FK, hd.KBH_FK, isnull(hd_sp.KHO_FK, hd.kho_fk), km.pk_seq ";
			System.out.println(":: CHEN HANG KM: " + query);
			if( !db.update(query) )
			{
				msg = "Khong the cap nhat PHIEUXUATKHO_SPKM: " + query;
				db.getConnection().rollback();
				return msg;
			}
			
			query = "  insert PHIEUXUATKHO_SPKM_CHITIET( PXK_FK, KHO_FK, KBH_FK, SANPHAM_FK, SOLUONG, SOLO, NGAYHETHAN, NGAYNHAPKHO, SCHEME )    " + 
					"  select  '" + pxkId + "', isnull(hd_sp.KHO_FK, hd.kho_fk), hd.KBH_FK, sp.PK_SEQ as SANPHAM_FK, sum(hd_sp.SOLUONG) as SOLUONG, hd_sp.solo, hd_sp.NGAYHETHAN, hd_sp.NGAYNHAPKHO, km.pk_seq   " + 
					"  from HOADON hd inner join HOADON_CTKM_TRAKM_CHITIET hd_sp on hd.PK_SEQ = hd_sp.HOADON_FK   " + 
					"   		inner join SANPHAM sp on hd_sp.sanpham_fk = sp.PK_SEQ inner join CTKHUYENMAI km on hd_sp.scheme = km.SCHEME " + 
					"  where hd.loaihoadon != 0 and hd.PK_SEQ in ( select hoadon_fk from PHIEUXUATKHO_DONHANG where PXK_FK = '" + pxkId + "' )   " + 
					"  group by isnull(hd_sp.KHO_FK, hd.kho_fk), hd.KBH_FK, sp.PK_SEQ, hd_sp.solo, hd_sp.NGAYHETHAN, hd_sp.NGAYNHAPKHO, km.pk_seq    ";
			System.out.println(":: CHEN HANG KM CT: " + query);
			if( !db.update(query) )
			{
				msg = "Khong the cap nhat PHIEUXUATKHO_SPKM_CHITIET: " + query;
				db.getConnection().rollback();
				return msg;
			}
			
			//Trừ booked + avai
			query = "  select c.npp_fk, case when isnull(d.dungchungkenh, 0) = 0 then a.kbh_fk else 100025 end as kbh_fk,    " + 
					"  	a.kho_fk, a.sanpham_fk, b.ten as TEN, a.soluong as soluongDAT, a.solo, a.ngayhethan,     " + 
					"  	a.soluong,  0 as loai, ' ' as scheme, a.ngaynhapkho       " + 
					"  from PHIEUXUATKHO_SANPHAM_CHITIET a inner join SANPHAM b on a.sanpham_fk = b.PK_SEQ      " + 
					"  	inner join PHIEUXUATKHO c on  a.pxk_fk = c.pk_seq inner join NHAPHANPHOI d on c.npp_fk = d.pk_seq    " + 
					"  where a.pxk_fk = '" + pxkId + "' ";
			
			System.out.println("--CAP NHAT TON KHO: " + query);
			ResultSet rs = db.get(query);
			if(rs != null)
			{
				Utility utility = new Utility();
				while(rs.next())
				{
					String khoID = rs.getString("kho_fk");
					String kbhID = rs.getString("kbh_fk");
					String nppID = rs.getString("npp_fk");
					String spID = rs.getString("sanpham_fk");
					
					double soluong = rs.getDouble("soluong");
					String solo = rs.getString("solo");
					String ngayhethan = rs.getString("ngayhethan");
					String ngaynhapkho=rs.getString("ngaynhapkho");
					
					//CAP NHAT KHO XUAT TONG
					query = "Update NHAPP_KHO set soluong = soluong - '" + soluong + "', BOOKED = BOOKED - '" + soluong + "' " +
							"where KHO_FK = '" + khoID + "' and KBH_FK = '" + kbhID + "' and NPP_FK = '" + nppID + "' and SANPHAM_FK = '" + spID + "' ";
					if(db.updateReturnInt(query)!=1)
					{
						msg = "Khong the cap nhat ERP_KHOTT_SANPHAM: " + query;
						db.getConnection().rollback();
						rs.close();
						return msg;
					}
					
					String kq1 = utility.Update_NPP_Kho_Sp_Chitiet(this.getDateTime(), "chot phieu xuat kho OTC - hàng bán ", db, khoID, spID, nppID, kbhID, solo, ngayhethan, ngaynhapkho,  (-1) * soluong,  (-1) * soluong, 0, 0, 0);
					if( kq1.length() > 0 )
					{
						msg = "Khong the cap nhat NHAPP_KHO_CHITIET: " + kq1;
						db.getConnection().rollback();
						rs.close();
						return msg;
					}					
				}
				rs.close();
			}
			
			//Trừ booked + avai
			query = "  select c.npp_fk, case when isnull(d.dungchungkenh, 0) = 0 then a.kbh_fk else 100025 end as kbh_fk,    " + 
					"  	a.kho_fk, a.sanpham_fk, b.ten as TEN, a.soluong as soluongDAT, a.solo, a.ngayhethan,     " + 
					"  	a.soluong,  0 as loai, ' ' as scheme, a.ngaynhapkho       " + 
					"  from PHIEUXUATKHO_SPKM_CHITIET a inner join SANPHAM b on a.sanpham_fk = b.PK_SEQ      " + 
					"  	inner join PHIEUXUATKHO c on  a.pxk_fk = c.pk_seq inner join NHAPHANPHOI d on c.npp_fk = d.pk_seq    " + 
					"  where a.pxk_fk = '" + pxkId + "' ";
			
			System.out.println("--CAP NHAT TON KHO: " + query);
			rs = db.get(query);
			if(rs != null)
			{
				Utility utility = new Utility();
				while(rs.next())
				{
					String khoID = rs.getString("kho_fk");
					String kbhID = rs.getString("kbh_fk");
					String nppID = rs.getString("npp_fk");
					String spID = rs.getString("sanpham_fk");
					
					double soluong = rs.getDouble("soluong");
					String solo = rs.getString("solo");
					String ngayhethan = rs.getString("ngayhethan");
					String ngaynhapkho=rs.getString("ngaynhapkho");
					
					//CAP NHAT KHO XUAT TONG
					query = "Update NHAPP_KHO set soluong = soluong - '" + soluong + "', BOOKED = BOOKED - '" + soluong + "' " +
							"where KHO_FK = '" + khoID + "' and KBH_FK = '" + kbhID + "' and NPP_FK = '" + nppID + "' and SANPHAM_FK = '" + spID + "' ";
					if(db.updateReturnInt(query)!=1)
					{
						msg = "Khong the cap nhat ERP_KHOTT_SANPHAM: " + query;
						db.getConnection().rollback();
						rs.close();
						return msg;
					}
					
					String kq1 = utility.Update_NPP_Kho_Sp_Chitiet(this.getDateTime(), "chot phieu xuat kho OTC - hàng KM ", db, khoID, spID, nppID, kbhID, solo, ngayhethan, ngaynhapkho,  (-1) * soluong,  (-1) * soluong, 0, 0, 0);
					if( kq1.length() > 0 )
					{
						
						msg = "Khong the cap nhat NHAPP_KHO_CHITIET: " + kq1;
						db.getConnection().rollback();
						rs.close();
						return msg;
					}					
				}
				rs.close();
			}
			
			
			//GOP CHUNG BUOC YC VA XUAT THANH 1
			query = "update PHIEUXUATKHO set trangthai = '1'  where pk_seq = '" + pxkId + "' and trangthai != 1 ";
			System.out.println("---CAP NHAT TRANG THAI: " + query);
			if(db.updateReturnInt(query) <= 0 )
			{
				msg = "Không thể cập nhật PHIEUXUATKHO " + query;
				db.getConnection().rollback();
				return msg;
			}
			
			//TU DONG HOAN TAT CAC HOA DON TU CU TOI MOI
			/*query = "select hoadon_fk, ( select xuatcho from ERP_YCXUATKHONPP where pk_seq = a.ycxk_fk ) as xuatcho " +
					"from ERP_YCXUATKHONPP_DDH a where ycxk_fk = '" + lsxId + "' order by hoadon_fk asc";
			System.out.println("---CAP NHAT TRANG THAI HOA DON: " + query);
			
			ResultSet rsDDH = db.get(query);
			String ddhID = "";
			String xuatCHO = "";
			if(rsDDH != null)
			{
				while(rsDDH.next())
				{
					ddhID = rsDDH.getString("hoadon_fk") + ",";
					xuatCHO = rsDDH.getString("xuatcho");
					
					query = "  select COUNT(*) as soDONG,   " +
							" 		(   select count(distinct sanpham_fk) as soSP      " +
							"   			from     " +
							"   			(     " +
							"   					select a.sanpham_fk " +
							"   					from ERP_HOADONNPP_SP a inner join SANPHAM b on a.sanpham_fk = b.PK_SEQ     " +
							"   					where a.hoadon_fk in (    " + ( ddhID.substring(0, ddhID.length() - 1) ) + "   )    " +
							"   			)     " +
							"   			dathang  )	 as soSP  " +
							"  from  " +
							"  (  " +
							"  	select sanpham_fk, sum(soluongXUAT) as soluongXUAT  " +
							"  	from ERP_YCXUATKHONPP_SANPHAM  " +
							" 	where ycxk_fk in ( select ycxk_fk from ERP_YCXUATKHONPP_DDH where hoadon_fk in ( " + ( ddhID.substring(0, ddhID.length() - 1) ) + " ) ) " +
							"  	group by sanpham_fk  " +
							"  )   " +
							"  XUAT inner join   " +
							"  (  " +
							"   	select dathang.sanpham_fk, SUM(dathang.soluong) as soluongDAT      " +
							"   	from     " +
							"   	(     " +
							"   			select a.sanpham_fk, b.DVDL_FK as dvCHUAN,     " +
							"   					a.soluong_chuan as soluong, 0 as loai, ' ' as scheme    " +
							"   			from ERP_HOADONNPP_SP a inner join SANPHAM b on a.sanpham_fk = b.PK_SEQ     " +
							"   			where a.hoadon_fk in ( " + ( ddhID.substring(0, ddhID.length() - 1) ) + " )    " +
							"   	)     " +
							"   	dathang   " +
							"   	group by dathang.sanpham_fk  " +
							"  )  " +
							"  DDH on XUAT.sanpham_fk = DDH.sanpham_fk  " +
							"  where XUAT.soluongXUAT >= DDH.soluongDAT ";
					
					System.out.println("CHECK HOAN TAT: " + query);
					ResultSet rsCHECK = db.get(query);
					if(rsCHECK.next())
					{
						String hoantat = "0";
						if(rsCHECK.getInt("soDONG") == rsCHECK.getInt("soSP"))
							hoantat = "1";  //HOAN TAT
						
						query = " UPDATE ERP_HOADONNPP set hoantat = '" + hoantat + "' where pk_seq in ( " + ( ddhID.substring(0, ddhID.length() - 1) ) + " ) ";
						if(!db.update(query))
						{
							msg = "Không thể chốt ERP_YCXUATKHO " + query;
							db.getConnection().rollback();
							return msg;
						}
					}
				}
				rsDDH.close();
			}*/
			
			db.getConnection().commit();
			db.getConnection().setAutoCommit(true);
		}
		catch (Exception e) 
		{
			e.printStackTrace();
			db.update("rollback");
			db.shutDown();
			return "Exception: " + e.getMessage();
		}
		
		return "";
	}
	
	public String getDateTime()
	{
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date date = new Date();
        return dateFormat.format(date);	
	}
	
}
