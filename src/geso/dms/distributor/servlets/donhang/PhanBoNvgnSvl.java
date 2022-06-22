package geso.dms.distributor.servlets.donhang;


import geso.dms.distributor.beans.donhang.IDonhang;
import geso.dms.distributor.beans.donhang.IDonhangList;
import geso.dms.distributor.beans.donhang.IPhanBoNvgn;
import geso.dms.distributor.beans.donhang.imp.Donhang;
import geso.dms.distributor.beans.donhang.imp.DonhangList;
import geso.dms.distributor.beans.donhang.imp.PhanBoNvgn;
import geso.dms.distributor.beans.khachhang.IKhachhangList;
import geso.dms.distributor.db.sql.dbutils;
import geso.dms.distributor.util.Utility;

import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.extentech.formats.XLS.UsrExcl;

import jxl.format.Alignment;
import jxl.format.Border;
import jxl.format.BorderLineStyle;
import jxl.format.Colour;
import jxl.format.VerticalAlignment;
import jxl.write.Label;
import jxl.write.Number;
import jxl.write.NumberFormat;
import jxl.write.WritableCellFormat;
import jxl.write.WritableFont;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;


public class PhanBoNvgnSvl extends javax.servlet.http.HttpServlet implements javax.servlet.Servlet
{
	private static final long serialVersionUID = 1L;
	PrintWriter out;

	private int items = 50; 
	//String userId;
	//String nppId;

	private int splittings = 20;

	public PhanBoNvgnSvl() 
	{
		super();
	}

	private void settingPage(IDonhangList obj) {
		Utility util = new Utility();
		if(getServletContext().getInitParameter("items") != null){
			String i = getServletContext().getInitParameter("items").trim();
			if(util.isNumeric(i))
				items = Integer.parseInt(i);
		}

		if(getServletContext().getInitParameter("splittings") != null){
			String i = getServletContext().getInitParameter("splittings").trim();
			if(util.isNumeric(i))
				splittings = Integer.parseInt(i);
		}

		obj.setItems(items);
		obj.setSplittings(splittings);
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{

		HttpSession session = request.getSession();
		String userId = (String) session.getAttribute("userId");  
		String userTen = (String) session.getAttribute("userTen");  	
		String sum = (String) session.getAttribute("sum");
		geso.dms.center.util.Utility cutil = (geso.dms.center.util.Utility) session.getAttribute("util");
		if(!cutil.check(userId, userTen, sum)){
			response.sendRedirect(request.getContextPath() + "/redirect.jsp");
		}else{

			request.setCharacterEncoding("UTF-8");
			response.setCharacterEncoding("UTF-8");
			response.setContentType("text/html; charset=UTF-8");
			this.out  = response.getWriter();


			session.setMaxInactiveInterval(30000);

			Utility util = new Utility();
			out = response.getWriter();

			String querystring = request.getQueryString();
			userId = util.getUserId(querystring);
			//out.println(userId);
			System.out.println();
			Date date = new Date();
			System.out.println("DonhangSvl user :" + userId + "  ,sessionId: " + session.getId() );

			if (userId.length()==0)
				userId = request.getParameter("userId");
			String nppId;
			if(request.getParameter("nppId") != null)
				nppId = request.getParameter("nppId");


			//Lay Nha PP Theo Dang Nhap Moi
			nppId = util.getIdNhapp(userId);
			String action = "";
			String dhId = "";
			if(querystring.contains("&display="))
			{
				dhId =  request.getParameter("display");
				action = "display";
			}else
				if(querystring.contains("&update="))
				{
					dhId =  request.getParameter("update");
					action = "update";
				}else
					if(querystring.contains("&delete="))
					{
						dhId =  request.getParameter("delete");
						action = "delete";
					}else if(querystring.contains("&chot="))
					{
						dhId =  request.getParameter("chot");
						action = "chot";
					}
			
		
			System.out.println("dhId = " + dhId);
			IPhanBoNvgn pbBean = new PhanBoNvgn();
			
			if (action.equals("update")) {
				pbBean.setId(dhId);
				pbBean.setUserId(userId);
				pbBean.init(dhId);
				
				IDonhangList obj = new DonhangList();
				obj.setTrangthaipb("update"); //Set trangthaipb để load đơn hàng
				obj.setPbid(dhId); //Set pbid để load đơn hàng
				if (pbBean.getPBdonhang_fk() != null && pbBean.getPBdonhang_fk().length > 0)
					obj.setArrPBdonhang_fk(pbBean.getPBdonhang_fk());
				if (pbBean.get_PBdonhang_fk() != null && pbBean.get_PBdonhang_fk().length() > 0)
					obj.setPBdonhang_fk(pbBean.get_PBdonhang_fk());
				
				obj.setIsPhanBoNVGN("1");
				obj.setUserId(userId);
				settingPage(obj);
				obj.init("");
				session.setAttribute("khId", "");
				session.setAttribute("obj", obj);
				session.setAttribute("pbBean", pbBean);
				
				String nextJSP = request.getContextPath() + "/pages/Distributor/PhanBoNvgnUpdate.jsp";
				response.sendRedirect(nextJSP); 
			}
			else if (action.equals("delete")) {
				String msg = Delete(dhId);
				if (msg.length() > 0) {
					pbBean.setMsg(msg);
				}
				pbBean.setUserId(userId);
				pbBean.initList("");
				settingPage(pbBean);
				session.setAttribute("obj", pbBean);
				
				String nextJSP = request.getContextPath() + "/pages/Distributor/PhanBoNvgnList.jsp";
				response.sendRedirect(nextJSP); 
			}
			else if (action.equals("chot")) {
				String msg = Chot(dhId);
				if (msg.length() > 0) {
					pbBean.setMsg(msg);
				}
				pbBean.setUserId(userId);
				pbBean.initList("");
				settingPage(pbBean);
				session.setAttribute("obj", pbBean);
				
				String nextJSP = request.getContextPath() + "/pages/Distributor/PhanBoNvgnList.jsp";
				response.sendRedirect(nextJSP); 
			}
			
			else {
				if ( dhId != null && !dhId.equals("")) {
					pbBean = new PhanBoNvgn();
					pbBean.setUserId(userId);
					pbBean.setId(dhId);
					pbBean.initDHSP(dhId);
					session.setAttribute("obj", pbBean);
					String nextJSP = request.getContextPath() + "/pages/Distributor/PhanBoNvgnDisplay.jsp";
					response.sendRedirect(nextJSP);
				}
				else
				{
					String msg = "";
					
					//IDonhangList obj = new DonhangList();	
					//obj.setIsPhanBoNVGN("1");
					//obj.setUserId(userId);
					//settingPage(obj);
					//obj.init("");
					//obj.setMsg(msg);
					//session.setAttribute("khId", "");
					pbBean.setUserId(userId);
					pbBean.initList("");
					settingPage(pbBean);
					session.setAttribute("obj", pbBean);
					
					String nextJSP = request.getContextPath() + "/pages/Distributor/PhanBoNvgnList.jsp";
					response.sendRedirect(nextJSP); 
				}
			}
		}
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		HttpSession session = request.getSession();
		String userId = (String) session.getAttribute("userId");  
		String userTen = (String) session.getAttribute("userTen");  	
		String sum = (String) session.getAttribute("sum");
		
		geso.dms.center.util.Utility cutil = (geso.dms.center.util.Utility) session.getAttribute("util");
		if(!cutil.check(userId, userTen, sum)){
			response.sendRedirect(request.getContextPath() + "/redirect.jsp");
		}else{

			request.setCharacterEncoding("UTF-8");
			response.setCharacterEncoding("UTF-8");
			response.setContentType("text/html; charset=UTF-8");
			session.setMaxInactiveInterval(30000);
			userId = request.getParameter("userId");
			
			IPhanBoNvgn pbBean = new PhanBoNvgn();
			Utility util = new Utility();
			String[] chon = request.getParameterValues("chon");
			pbBean.setDonhang_fk(chon);
			String nvgn_fk = util.antiSQLInspection(request.getParameter("nvgn_fk"));
			if (nvgn_fk == null || nvgn_fk.equals("")) {
				nvgn_fk = null;
			}
			pbBean.setNvgn_fk(nvgn_fk);
			String ngayphanbo = util.antiSQLInspection(request.getParameter("ngayphanbo"));
			if (ngayphanbo == null) ngayphanbo = "";
			pbBean.setNgayphanbo(ngayphanbo);
			
			String dhId = util.antiSQLInspection(request.getParameter("dhId"));
			pbBean.setId(dhId);
			pbBean.setUserId(userId);
			String action = request.getParameter("action");
			if (action == null){
				action = "";
			}

			if (action.equals("newPB"))
			{
				if  (!pbBean.createPhanBo()) {
				String msg = pbBean.getMsg();
				pbBean.init("");
				IDonhangList obj = new DonhangList();
				obj.setTrangthaipb("new"); //Set trangthaipb để load đơn hàng
				if (pbBean.getPBdonhang_fk() != null && pbBean.getPBdonhang_fk().length > 0)
					obj.setArrPBdonhang_fk(pbBean.getPBdonhang_fk());
				if (pbBean.get_PBdonhang_fk() != null && pbBean.get_PBdonhang_fk().length() > 0)
					obj.setPBdonhang_fk(pbBean.get_PBdonhang_fk());
				obj.setIsPhanBoNVGN("1");
				obj.setUserId(userId);
				obj.setMsg(msg);	
				settingPage(obj);
				obj.init("");
				session.setAttribute("pbBean", pbBean);
				session.setAttribute("obj", obj);
				session.setAttribute("khId", "");
				
				String nextJSP = request.getContextPath() + "/pages/Distributor/PhanBoNvgnNew.jsp";
				response.sendRedirect(nextJSP);
				}
				else {
					pbBean.initList("");
					pbBean.setUserId(userId);
					pbBean.initList("");
					settingPage(pbBean);
					session.setAttribute("obj", pbBean);
					
					String nextJSP = request.getContextPath() + "/pages/Distributor/PhanBoNvgnList.jsp";
					response.sendRedirect(nextJSP); 
				}
			}
			else if (action.equals("updatePB")) {
				if (!pbBean.updatePhanBo()){			
					String msg = pbBean.getMsg();
					pbBean.init(dhId);
					IDonhangList obj = new DonhangList();
					obj.setTrangthaipb("update"); //Set trangthaipb để load đơn hàng
					obj.setPbid(dhId); //Set pbid để load đơn hàng
					if (pbBean.getPBdonhang_fk() != null && pbBean.getPBdonhang_fk().length > 0)
						obj.setArrPBdonhang_fk(pbBean.getPBdonhang_fk());
					if (pbBean.get_PBdonhang_fk() != null && pbBean.get_PBdonhang_fk().length() > 0)
						obj.setPBdonhang_fk(pbBean.get_PBdonhang_fk());
					obj.setIsPhanBoNVGN("1");
					obj.setUserId(userId);
					obj.setMsg(msg);	
					settingPage(obj);
					obj.init("");
					session.setAttribute("pbBean", pbBean);
					session.setAttribute("obj", obj);
					session.setAttribute("khId", "");
					
					String nextJSP = request.getContextPath() + "/pages/Distributor/PhanBoNvgnUpdate.jsp";
					response.sendRedirect(nextJSP);
				}
				else {
					System.out.println("pbBean.getMsg(): "+pbBean.getMsg());
					pbBean.initList("");
					pbBean.setUserId(userId);
					pbBean.initList("");
					settingPage(pbBean);
					session.setAttribute("obj", pbBean);
					
					String nextJSP = request.getContextPath() + "/pages/Distributor/PhanBoNvgnList.jsp";
					response.sendRedirect(nextJSP); 
				}
				
			}
			else if (action.equals("new")) {
				pbBean.setUserId(userId);
				pbBean.init("");
				IDonhangList obj = new DonhangList();
				obj.setTrangthaipb("new"); //Set trangthaipb để load đơn hàng
				if (pbBean.getPBdonhang_fk() != null && pbBean.getPBdonhang_fk().length > 0)
					obj.setArrPBdonhang_fk(pbBean.getPBdonhang_fk());
				if (pbBean.get_PBdonhang_fk() != null && pbBean.get_PBdonhang_fk().length() > 0)
					obj.setPBdonhang_fk(pbBean.get_PBdonhang_fk());
				
				obj.setIsPhanBoNVGN("1");
				obj.setUserId(userId);
				settingPage(obj);
				obj.init("");
				session.setAttribute("khId", "");
				session.setAttribute("obj", obj);
				session.setAttribute("pbBean", pbBean);
				
				String nextJSP = request.getContextPath() + "/pages/Distributor/PhanBoNvgnNew.jsp";
				response.sendRedirect(nextJSP); 
				
			}
			else
			{
				IDonhangList obj;
				obj = new DonhangList();
				settingPage(obj);

				obj.setUserId(userId);

				if(action.equals("searchnew"))
				{
					obj.setTrangthaipb("new");
					obj.setPbid(dhId);
					obj.setUserId(userId);
					String search = getSearchQuery(request, obj, pbBean);
					if(obj.getIsSearch())
					{
						String sumqr = getSumQuery(request, obj);
						obj.getSumBySearch(sumqr);
					}
					
					System.out.println("IS SEARCH: " + obj.getIsSearch() + " ---- cau lenh chay:"+ search);

					obj.init(search);
					pbBean.init("");
					session.setAttribute("userId", userId);
					session.setAttribute("obj", obj);
					session.setAttribute("pbBean", pbBean);

					response.sendRedirect(request.getContextPath() + "/pages/Distributor/PhanBoNvgnNew.jsp");	    		    	
				}
				else if (action.equals("searchupdate")) {
					obj.setTrangthaipb("update");
					obj.setPbid(dhId);
					obj.setUserId(userId);
					String search = getSearchQuery(request, obj, pbBean);

					System.out.println("IS SEARCH: " + obj.getIsSearch() + " ---- cau lenh chay:"+ search);

					obj.init(search);
					pbBean.init("");
					session.setAttribute("userId", userId);
					session.setAttribute("obj", obj);
					session.setAttribute("pbBean", pbBean);

					response.sendRedirect(request.getContextPath() + "/pages/Distributor/PhanBoNvgnUpdate.jsp");	 
				}
				else if (action.equals("searchPB")) {
					pbBean.setUserId(userId);
					String search = getSearchPbQuery(request, pbBean);
					pbBean.initList(search);
					session.setAttribute("userId", userId);
					session.setAttribute("obj", pbBean);

					response.sendRedirect(request.getContextPath() + "/pages/Distributor/PhanBoNvgnList.jsp");	  
				}
				else if(action.equals("view") || action.equals("next") || action.equals("prev"))
				{

					obj.setUserId(userId);
					System.out.println("____"+request.getParameter("nxtApprSplitting")+"____");
					String search = getSearchQuery(request, obj, pbBean);

					obj.setNxtApprSplitting(Integer.parseInt(request.getParameter("nxtApprSplitting")));

					obj.init(search);
					obj.setAttribute(request, action, "list", "crrApprSplitting", "nxtApprSplitting");
					session.setAttribute("obj", obj);
					response.sendRedirect(request.getContextPath() + "/pages/Distributor/PhanBoNvgnNew.jsp");
				}
				else if(action.equals("delete"))
				{}
				else if(action.equals("duyet"))  //DUYET PHAI DUNG DOPOST THI MOI GIU LAI DUOC CAC DK LOC
				{}
			}
		}
	}
	
	private String getSearchPbQuery(HttpServletRequest request, IPhanBoNvgn obj)
	{

		Utility util = new Utility();

		String tungay = request.getParameter("tungay");
		if (tungay == null)
			tungay = "";    	
		obj.setTungay(tungay);

		String denngay = request.getParameter("denngay");
		if (denngay == null)
			denngay = "";    	
		obj.setDenngay(denngay);
		
		String query = "\n select a.pk_seq,a.ngaytao,a.ngaysua,b.ten nguoitao,c.ten nguoisua,a.trangthai,a.ngayphanbo " +
		"\n from PhanBo_NVGN a inner join nhanvien b on a.nguoitao = b.pk_seq" +
		"\n inner join nhanvien c on c.pk_seq = a.nguoisua where 1=1";

		if (tungay.length() > 0)
		{
			query = query + " and a.ngayphanbo >= '" + tungay + "'";			
		}    	
		if (denngay.length() > 0)
		{
			query = query + " and a.ngayphanbo <= '" + denngay + "'";	
		}

		System.out.println("Query SearchPB trong SVL: " + query);
		return query;
	
	}
	
	private String getSearchQuery(HttpServletRequest request,IDonhangList obj, IPhanBoNvgn pbBean) 
	{
		Utility util = new Utility();

		String nppId = request.getParameter("nppId");
		if ( nppId == null)
			nppId = "";
		obj.setNppId(nppId);

		String ddkdId = request.getParameter("ddkdTen");
		if ( ddkdId == null)
			ddkdId = "";
		obj.setDdkdId(ddkdId);

		String trangthai = request.getParameter("trangthai");
		if (trangthai == null)
			trangthai = "";    	
		obj.setTrangthai(trangthai);
		System.out.println(" ---- TRANG THAI LA: " + trangthai);

		String tungay = request.getParameter("tungay");
		if (tungay == null)
			tungay = "";    	
		obj.setTungay(tungay);

		String denngay = request.getParameter("denngay");
		if (denngay == null)
			denngay = "";    	
		obj.setDenngay(denngay);

		String sodonhang = request.getParameter("sodonhang");
		if (sodonhang == null)
			sodonhang = "";    	
		obj.setSohoadon(sodonhang.trim());

		String khachhang = request.getParameter("khachhang");
		if (khachhang == null)
			khachhang = "";    	
		obj.setKhachhang(khachhang.trim());

		String mafast = request.getParameter("mafast");
		if(mafast==null)
			mafast="";
		obj.setMafast(mafast);
		
		String  quanhuyen=request.getParameter("qhId");
		if(quanhuyen==null)
			quanhuyen="";
		obj.setQhId(quanhuyen);
		
		String dienthoai = request.getParameter("dienthoai");
		if(dienthoai == null)
			dienthoai = "";
		obj.setDienthoai(dienthoai);
		
		obj.setTtId(util.antiSQLInspection(request.getParameter("ttId")) == null ? "" : util.antiSQLInspection(request.getParameter("ttId")));

		if ( ddkdId.trim().length() <= 0 && trangthai.trim().length() <= 0 && tungay.trim().length() <= 0 && denngay.trim().length() <= 0 
				&& sodonhang.trim().length() <= 0 && khachhang.trim().length() <= 0 && mafast.trim().length() <= 0 && quanhuyen.trim().length() <= 0     )
			obj.setIsSearch(false);
		else
			obj.setIsSearch(true);
		
		String[] chon = request.getParameterValues("chon");
		pbBean.setDonhang_fk(chon);
		
		obj.setArrPBdonhang_fk(chon);
		
		String query = "\n select * from (" +
		"\n select " +
		"\n		case " +
		"\n		 when exists (select 1 from PhanBo_NVGN_CT where donhang_fk = a.pk_seq and not exists (select 1 from PhanBo_NVGN_CTsp where donhang_fk = a.pk_seq) ) then -1 " +
		"\n		 when exists (select 1 from PhanBo_NVGN_CTSP where donhang_fk = a.pk_seq and exists ( " +
		"\n		 	select 1 from (  " +
		"\n				select sum(soluong) soluong,sanpham_fk,donhang_fk from PhanBo_NVGN_CTsp   " +
		"\n				where donhang_Fk = a.pk_seq " +
		"\n				group by sanpham_fk,donhang_fk ) x inner join   " +
		"\n				(select sanpham_fk, sum(soluong)soluong,donhang_fk from (  " +
		"\n				select sanpham_fk, soluong, donhang_fk from donhang_sanpham where donhang_Fk = a.pk_seq " +
		"\n				union all   " +
		"\n				select bbb.pk_seq,soluong,donhangid donhang_fk from donhang_ctkm_trakm aaa inner join sanpham bbb on aaa.spma = bbb.ma   " +
		"\n				where donhangid = a.pk_seq) bb  " +
		"\n				group by sanpham_fk,donhang_fk  " +
		"\n				) y  " +
		"\n				on x.sanpham_fk = y.sanpham_fk and x.donhang_fk = y.donhang_fk where x.soluong-y.soluong != 0  " +
		"\n		 	)) then 1   " +
		"\n when " +
		"\n exists (select 1 from PhanBo_NVGN_CTSP where donhang_fk = a.pk_seq) " +
		"\n and not exists (select 1 from PhanBo_NVGN_CTSP where donhang_fk = a.pk_seq and exists (  " +
		"\n select 1 from (   " +
		"\n 	select sum(soluong) soluong,sanpham_fk,donhang_fk from PhanBo_NVGN_CTsp    " +
		"\n 	where donhang_Fk = a.pk_seq  " +
		"\n 	group by sanpham_fk,donhang_fk ) x inner join    " +
		"\n 	(select sanpham_fk, sum(soluong)soluong,donhang_fk from (   " +
		"\n 	select sanpham_fk, soluong, donhang_fk from donhang_sanpham where donhang_Fk = a.pk_seq  " +
		"\n 	union all    " +
		"\n 	select bbb.pk_seq,soluong,donhangid donhang_fk from donhang_ctkm_trakm aaa inner join sanpham bbb on aaa.spma = bbb.ma    " +
		"\n 	where donhangid = a.pk_seq) bb   " +
		"\n 	group by sanpham_fk,donhang_fk   " +
		"\n 	) y   " +
		"\n 	on x.sanpham_fk = y.sanpham_fk and x.donhang_fk = y.donhang_fk where x.soluong-y.soluong != 0   " +
		"\n )) then 2  " +
		"\n		 	else 0 end trangthaigh,  " +
		"\n isnull((select top 1 phanbo_fk from PhanBo_NVGN_CT where donhang_fk = a.pk_seq),0)pbid, " +
		"\n isnull((select top 1 ten from nhanviengiaonhan where pk_seq = a.nvgn_fk),'') nvgnten1, " +
		"\n case when exists (select top 1 nvgn_fk from PhanBo_NVGN_CT where donhang_fk = a.pk_seq) " +
		"\n then isnull((select ten from nhanviengiaonhan where pk_seq = (select top 1 nvgn_fk from PhanBo_NVGN_CT where donhang_fk = a.pk_seq) ),'') else " +
		"\n 	isnull((select ten from nhanviengiaonhan where pk_seq = a.nvgn_fk),'') end nvgn, " +
		"\n 	d.diachigiaohang khdiachi, case when a.trangthai = 0  and a.synced != 1 then 1 when a.synced = 1 then 2 else 3 end tmp_sort,isnull(d.daduyet,0)daduyet, a.synced,isnull(a.ngaytaodh,a.ngaygio) as ngaygio,a.pk_seq as dhId, a.ngaynhap, a.trangthai, a.ngaytao, a.ngaysua, d.maFAST, isnull(a.DAXUATHOADON,0) as DAXUATHOADON , isnull(DAIN, '0') as DAINDH,     " +
		"\n 			c.ten as nguoisua, d.ten as khTen, d.pk_seq as khId, e.pk_seq as ddkdId, (select top (1)'T'+CAST(tb.NGAYID as nvarchar(2))+ '_'+ dd.ten from tuyenbanhang tb inner join khachhang_tuyenbh khtb on tb.pk_seq=khtb.tbh_fk "+ 
		"\n  inner join daidienkinhdoanh dd on dd.pk_seq = tb.ddkd_fk where khtb.khachhang_fk=d.pk_seq and dd.PK_SEQ = a.DDKD_FK ) as ddkdTen        " +
		"\n 		,	'' as nppTen, a.tonggiatri as tonggiatri, d.THANHTOAN, a.VAT " +
		"\n , ' ' as nguoitao, 0 as exitPXK ,     " +
		"\n  	isnull( a.sohoadon, '' ) AS SoHoaDon, a.npp_fk  "+
		"\n  from donhang a   " +
		"\n  inner  join nhanvien c on a.nguoisua = c.pk_seq inner join khachhang d on a.khachhang_fk = d.pk_seq        " +
		"\n  left join daidienkinhdoanh e on a.ddkd_fk = e.pk_seq  " +
		"\n  where a.npp_fk = '" + nppId + "' and a.trangthai != '2' and a.trangthai != 0 ) donhang " +
		"\n	 where 1=1 ";
		
		if (obj.getTrangthaipb() != null && obj.getTrangthaipb().equals("new")) {
			query += "\n and trangthaigh != -1 and trangthaigh != 2 " +
			"\n and not exists (select 1 from phanbo_nvgn_ct a where donhang_fk = donhang.dhid and not exists " +
			"\n	 (select 1 from phanbo_nvgn_ctsp where phanbo_fk = a.phanbo_fk and donhang_fk = a.donhang_fk)) ";
		}
		else if (obj.getTrangthaipb()!= null && obj.getTrangthaipb().equals("update")) {
			query += "\n and (not exists (select 1 from phanbo_nvgn_ct where phanbo_fk != "+obj.getPbid()+
					 "\n 	and donhang_fk = donhang.dhid and (trangthaigh = -1 or trangthaigh =2)) " +
					 "\n or exists (select 1 from phanbo_nvgn_ct where phanbo_fk = "+obj.getPbid()+
					 "\n	and donhang_fk = donhang.dhid and trangthaigh = 2) ) ";
		}
		
		if (tungay.length() > 0)
		{
			query = query + " and ngaynhap >= '" + tungay + "'";			
		}    	
		if (denngay.length() > 0)
		{
			query = query + " and ngaynhap <= '" + denngay + "'";	
		}

		System.out.println("Query Search trong SVL: " + query);
		return query;
	}

	public String getFormatDate(String date) 
	{
		String[] arr = date.split("-");

		return arr[2] + "/" + arr[1] + "/" + arr[0];
	}

	private String getDateTime()
	{
		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		Date date = new Date();
		return dateFormat.format(date);
	}
	
	private String getSumQuery(HttpServletRequest request, IDonhangList obj) 
	{return "";}
	
	public void settingPage(IPhanBoNvgn pbBean) {
		Utility util = new Utility();
		if(getServletContext().getInitParameter("items") != null){
			String i = getServletContext().getInitParameter("items").trim();
			if(util.isNumeric(i))
				items = Integer.parseInt(i);
		}

		if(getServletContext().getInitParameter("splittings") != null){
			String i = getServletContext().getInitParameter("splittings").trim();
			if(util.isNumeric(i))
				splittings = Integer.parseInt(i);
		}

		pbBean.setItems(items);
		pbBean.setSplittings(splittings);
	}
	
	private static String Chot(String id) {


		dbutils db = new dbutils();
	
		try 
		{
			String query="select trangthai from PhanBo_NVGN where pk_seq ='"+id+"'";
			ResultSet rs= db.get(query);
			int trangthai=-1;
			while(rs.next())
			{
				trangthai = rs.getInt("trangthai");
			}
			rs.close();
			if(trangthai == 1)
			{
				return "Phân bổ này đã chốt rồi";
			}
			
			db.getConnection().setAutoCommit(false);
			
			query = "update PhanBo_NVGN set trangthai = 1 where pk_seq = '" + id + "' and trangthai = 0 ";
			if(!db.update(query))
			{
				db.getConnection().rollback();
				return "Không thể chốt phân bổ này: "+query;
			}
			db.getConnection().commit();
			db.getConnection().setAutoCommit(true);
		}
		catch(Exception e) 
		{
			db.update("rollback");
			e.printStackTrace();
			return "Lỗi ngoại lệ: "+e.getMessage();
		}
		finally
		{
			db.shutDown();	
		}
		return "";
	
	}
	
	private static String Delete(String id) {

		dbutils db = new dbutils();
	
		try 
		{
			String query="select trangthai from PhanBo_NVGN where pk_seq ='"+id+"'";
			ResultSet rs= db.get(query);
			int trangthai=-1;
			while(rs.next())
			{
				trangthai = rs.getInt("trangthai");
			}
			rs.close();
			if(trangthai == 1)
			{
				return "Phân bổ đã chốt không thể xoá";
			}
			
			db.getConnection().setAutoCommit(false);
			
			int count = -1;
			query = "select count(*) c from PhanBo_NVGN_CTSP where phanbo_fk ="+id;
			rs = db.get(query);
			while (rs.next()) {
				count = rs.getInt("c");
			}
			
			if (count > 0)
			{
				db.getConnection().rollback();
				return "Không thể xoá phân bổ đã phát sinh dữ liệu xuống PDA";
			}
			
			query = "delete PhanBo_NVGN_CT where phanbo_fk ="+id;
			if(!db.update(query))
			{
				db.getConnection().rollback();
				return "Không thể xóa PhanBo_NVGN_CTSP";
			}
			
			query = "delete from PhanBo_NVGN where pk_seq = '" + id + "'  ";
			if(!db.update(query))
			{
				db.getConnection().rollback();
				return "Không thể xóa phân bổ này";
			}
			db.getConnection().commit();
			db.getConnection().setAutoCommit(true);
		}
		catch(Exception e) 
		{
			db.update("rollback");
			e.printStackTrace();
			return "Lỗi ngoại lệ: "+e.getMessage();
		}
		finally
		{
			db.shutDown();	
		}
		return "";
	
	}
}

