package geso.dms.distributor.servlets.phieugiaohangkm;

import geso.dms.distributor.beans.phieugiaohangkm.IPhieugiaohangkm;
import geso.dms.distributor.beans.phieugiaohangkm.IPhieugiaohangkmList;
import geso.dms.distributor.beans.phieugiaohangkm.imp.PhieugiaohangkmList;

import geso.dms.distributor.util.Utility;
import java.io.IOException;
import java.io.PrintWriter;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class PhieugiaohangkmSvl extends HttpServlet 
{
	private static final long serialVersionUID = 1L;
       
    public PhieugiaohangkmSvl() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		HttpSession session = request.getSession();
		
		if(!Utility.val_doget(session, request, response))
		{
			session.setAttribute("flag",null);
			response.sendRedirect(request.getContextPath() + "/redirect.jsp");
			return;
		}
		else
		{
			session.setAttribute("flag",null);
		}
		String userId = (String) session.getAttribute("userId");  
		String userTen = (String) session.getAttribute("userTen");  	
		String sum = (String) session.getAttribute("sum");
		geso.dms.center.util.Utility cutil = (geso.dms.center.util.Utility) session.getAttribute("util");
	
		final HttpServletResponse response2=response;
		
		if(!cutil.check(userId, userTen, sum)){
			response.sendRedirect(request.getContextPath() + "/redirect.jsp");
		}else{
			IPhieugiaohangkmList obj;
			request.setCharacterEncoding("UTF-8");
		    response.setCharacterEncoding("UTF-8");
		    response.setContentType("text/html; charset=UTF-8");
		    
		    PrintWriter out = response.getWriter();    
	
		    Utility util = new Utility();
		    	    
		    String querystring = request.getQueryString();
		    userId = util.getUserId(querystring);
		    
		    if (userId.length()==0)
		    	userId = util.antiSQLInspection(request.getParameter("userId"));
		    
		    String action = util.getAction(querystring);
		    
		    
	    	String lsxId = util.getId(querystring);
		    obj = new PhieugiaohangkmList();
		    
		    obj.setUserId(userId);
		    obj.init("");
		    
			session.setAttribute("obj", obj);
			String nextJSP = request.getContextPath() + "/pages/Distributor/PhieuGiaoHangKM.jsp";
			response.sendRedirect(nextJSP);
		}
	    
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		HttpSession session = request.getSession();
		geso.dms.center.util.Csrf csdr=new geso.dms.center.util.Csrf(request,response,false,true ,false);
		if(!csdr.__validate_post())
		{
			response.sendRedirect(request.getContextPath() + "/redirect.jsp");
			return;
		} 
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
		    Utility util = new Utility();
		    String action = util.antiSQLInspection(request.getParameter("action"));
		    if (action == null)
		    {
		    	action = "";
		    }
		    	    
		    
			IPhieugiaohangkmList obj = new PhieugiaohangkmList();
	
			  String madonhang = util.antiSQLInspection(request.getParameter("madonhang"));
			     if(madonhang == null)
			      madonhang = "";
			     obj.setMadonhang(madonhang);
			     
			     String khTen = util.antiSQLInspection(request.getParameter("khTen"));
			     if(khTen == null)
			      khTen = "";
			     obj.setKhTen(khTen);
			     
			     String tungay = util.antiSQLInspection(request.getParameter("tungay"));
			     if(tungay == null)
			      tungay = "";
			     obj.setTungay(tungay);
			     
			     String denngay = util.antiSQLInspection(request.getParameter("denngay"));
			     if(denngay == null)
			      denngay = "";
			     obj.setDenngay(denngay);
			     
			     String trangthai = util.antiSQLInspection(request.getParameter("trangthai"));
			     if(trangthai == null)
			    	 trangthai = "";
			     obj.setTrangthai(trangthai);
			     
		  
		     
		    userId = util.antiSQLInspection(request.getParameter("userId")); 
	
	    	if(action.equals("view") || action.equals("next") || action.equals("prev"))
	    	{
		    	String search = getSearchQuery(request, obj);
		    	obj.setNxtApprSplitting(Integer.parseInt(util.antiSQLInspection(request.getParameter("nxtApprSplitting"))));
		    	obj.setUserId(userId);
		    	obj.init(search);
		    	obj.setAttribute(request, action, "list", "crrApprSplitting", "nxtApprSplitting");
		    	session.setAttribute("obj", obj);
		    	
		    	String nextJSP = request.getContextPath() + "/pages/Distributor/PhieuGiaoHangKM.jsp";
				response.sendRedirect(nextJSP);
		    }
	    	else
	    	{
	    		obj.setUserId(userId);
		    	String search = getSearchQuery(request, obj);
		    	session.setAttribute("obj", obj);  	
	    		session.setAttribute("userId", userId);
	    		
		    	obj.init(search);								
		    	
		
	    		String nextJSP = request.getContextPath() + "/pages/Distributor/PhieuGiaoHangKM.jsp";
	    		response.sendRedirect(nextJSP);
	    	}
		}
	    
	}
	
	private String getSearchQuery(HttpServletRequest request, IPhieugiaohangkmList obj)
	{
		Utility util = new Utility();
		String nppId = util.antiSQLInspection(request.getParameter("nppId"));
		if(nppId == null)
			nppId = "";
		obj.setNppId(nppId);
		
		String tungay = util.antiSQLInspection(request.getParameter("tungay"));
		if(tungay == null)
			tungay = "";
		obj.setTungay(tungay);
		
		String denngay = util.antiSQLInspection(request.getParameter("denngay"));
		if(denngay == null)
			denngay = "";
		obj.setDenngay(denngay);
		
		String madonhang = util.antiSQLInspection(request.getParameter("madonhang"));
		if(madonhang == null)
			madonhang = "";
		obj.setMadonhang(madonhang);
		
		String trangthai = util.antiSQLInspection(request.getParameter("trangthai"));
	     if(trangthai == null)
	    	 trangthai = "";
	     obj.setTrangthai(trangthai);
	     
	    String query =  
	    		"select  a.sohoadon, a.kyhieu ,  a.PK_SEQ as hdId, a.trangthai, a.ngayxuatHD as ngaynhap, NV.TEN as nguoitao, KH.TEN as TENKH, a.NGAYSUA, a.NGAYTAO, NV2.TEN as nguoisua," +
				"        a.tongtienavat as avat, case a.trangthai when 1 then 1 when 2 then 2 when 3 then 4 when 4 then 3 when 5 then 5 end as STT_SORT,isnull(d.InPhieuGiaoHangKM, 0) as DAINPGH,c.ddh_fk as pk_seq  " +
				"from HOADON a  " +
				"	inner join KHACHHANG KH on a.KHACHHANG_FK=KH.PK_SEQ  " +
				"	inner join NHANVIEN nv on a.NGUOITAO = nv.PK_SEQ   " +
				"	inner join NHANVIEN nv2 on a.NGUOISUA = nv2.PK_SEQ "+
				" inner join  hoadon_ddh c on c.hoadon_fk=a.pk_Seq  " +
				" inner join DONHANG  d on c.DDH_FK=d.PK_SEQ "+
				"where a.pk_seq > 0 and a.NPP_FK ="+ obj.getNppId() +" and isnull(loaihoadon,0) in (  2 ) " +
				"  and a.trangthai not in (1, 3, 5) and a.ngayxuatHD >= '2014-10-01' ";	

		
		String khten = util.antiSQLInspection(request.getParameter("khTen"));		
		if(khten == null)
			khten = "";
		obj.setKhTen(khten);
				
		
		if(tungay.length() > 0)
			query += " and a.ngayxuatHD >= '" + tungay + "'";
		
		if(denngay.length() > 0)
			query += " and a.ngayxuatHD <= '" + denngay + "'";
			
		if(khten.length() > 0)
			query += " and kh.pk_seq =" + khten + " ";
		
		if(madonhang.length() > 0)
			query += " and a.PK_SEQ in ( select hoadon_fk from HOADON_DDH where ddh_fk in ( select pk_seq from DONHANG where cast( pk_seq as varchar(10) ) like '%"+madonhang+"%'  ) ) ";
		
		if(trangthai.length() > 0)
			query += " and isnull(d.InPhieuGiaoHangKM,0) = '"+trangthai+"'";
				
		System.out.println("PHIEU GIAO HANG KM SEARCH: "+query);
		return query;
	}
		
	public String getDateTime()
	{
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date date = new Date();
        return dateFormat.format(date);	
	}
	
	
}
