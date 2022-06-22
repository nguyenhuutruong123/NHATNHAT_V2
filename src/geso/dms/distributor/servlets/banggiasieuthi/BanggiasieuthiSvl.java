package geso.dms.distributor.servlets.banggiasieuthi;

import geso.dms.distributor.beans.banggiasieuthi.*;
import geso.dms.distributor.beans.banggiasieuthi.imp.*;
import geso.dms.distributor.db.sql.dbutils;
import geso.dms.distributor.util.Utility;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


public class BanggiasieuthiSvl extends HttpServlet 
{
	private static final long serialVersionUID = 1L;
	
	
    
    public BanggiasieuthiSvl() 
    {
        super();
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
		}else
		{
		
		IBanggiasieuthiList obj;
		PrintWriter out; 
		
		
		request.setCharacterEncoding("UTF-8");
	    response.setCharacterEncoding("UTF-8");
	    response.setContentType("text/html; charset=UTF-8");
	    out  = response.getWriter();
	    	    
	    

	    Utility util = new Utility();
	    out = response.getWriter();
	    	    
	    String querystring = request.getQueryString();
	    userId = util.getUserId(querystring);
	    out.println(userId);
	    
	    if (userId.length()==0)
	    	userId = util.antiSQLInspection(request.getParameter("userId"));
	    
	    String action = util.getAction(querystring);
	    out.println(action);
	    
	    String bgstId = util.getId(querystring);
	    obj = new BanggiasieuthiList();
	    if (action.equals("delete"))
	    {	   		  	    	
	    	Delete(bgstId);
	    	 obj = new BanggiasieuthiList();
	 	    obj.setUserId(userId);
	 	    obj.init("");
	 	    
	 		session.setAttribute("obj", obj);
	 				
	 		String nextJSP = request.getContextPath() + "/pages/Distributor/BangGiaSieuThi.jsp";
	 			response.sendRedirect(nextJSP);
	    }else if(action.equals("assign")){
	    	IBanggiasieuthi assign = new Banggiasieuthi(bgstId);
	    	assign.setId(bgstId);
	    	assign.setUserId(userId);
	    	assign.init();
	    	assign.CreateRsSearch();
	    
	    	session.setAttribute("assign", assign);
	    	String nextJSP = request.getContextPath() + "/pages/Distributor/BangGiaSieuThiAsign.jsp";
	    	response.sendRedirect(nextJSP);
	    }
	    else
	    {
	   	    
	    obj = new BanggiasieuthiList();
	    obj.setUserId(userId);
	    obj.init("");
	    
		session.setAttribute("obj", obj);
				
		String nextJSP = request.getContextPath() + "/pages/Distributor/BangGiaSieuThi.jsp";
			response.sendRedirect(nextJSP);
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
		
		IBanggiasieuthiList obj = new BanggiasieuthiList();;
		PrintWriter out; 
		Utility util = new Utility();
		
		request.setCharacterEncoding("UTF-8");
	    response.setCharacterEncoding("UTF-8");
	    response.setContentType("text/html; charset=UTF-8");
	   out = response.getWriter();
	    
		
	    userId = util.antiSQLInspection(request.getParameter("userId"));
	    
	    String action = request.getParameter("action");
	    if (action == null){
	    	action = "";
	    }
	    out.println(action); 
	          
	    if (action.equals("new"))
	    {
	    	// Empty Bean for distributor
	    	IBanggiasieuthi bgstBean = (IBanggiasieuthi) new Banggiasieuthi("");
	    	bgstBean.setUserId(userId);
	    	bgstBean.createRS();
	    	// Save Data into session
	    	session.setAttribute("bgstBean", bgstBean);
    		
    		String nextJSP = request.getContextPath() + "/pages/Distributor/BangGiaSieuThiNew.jsp";
    		response.sendRedirect(nextJSP);
    		
	    }
	    
	    else if (action.equals("search")){
	    	String search = getSearchQuery(request, obj);
	    	
	    	//obj = new KhachhangList(search);
	    	obj.setUserId(userId);
	    	obj.init(search);
				
	    	session.setAttribute("obj", obj);  	
    		session.setAttribute("userId", userId);
	    		
    		response.sendRedirect(request.getContextPath() + "/pages/Distributor/BangGiaSieuThi.jsp");	    		    	
	    }else if(action.equals("savebgkh")){
	    	
	    	IBanggiasieuthi assign = new Banggiasieuthi(util.antiSQLInspection(request.getParameter("id")));
	    	
	    	assign.setId(request.getParameter("id"));

	    	assign.setUserId(userId);
	    	
	    	assign.init();
	    	System.out.println("action :"+action );
	    	if(! assign.update_bg_kh(request)){
		    	assign.init();
		    	session.setAttribute("assign", assign);
		    	String nextJSP = request.getContextPath() + "/pages/Distributor/BangGiaSieuThiAsign.jsp";
		    	response.sendRedirect(nextJSP);
	    	}else{
	    		 obj = new BanggiasieuthiList();
	    		    obj.setUserId(userId);
	    		    obj.init("");
	    		    
	    			session.setAttribute("obj", obj);
	    					
	    			String nextJSP = request.getContextPath() + "/pages/Distributor/BangGiaSieuThi.jsp";
	    				response.sendRedirect(nextJSP);
	    	}
	    }else if(action.equals("searchkh")){
	    	IBanggiasieuthi assign = new Banggiasieuthi(request.getParameter("id"));
	    	    assign.setId(request.getParameter("id"));
	    	    
	        	assign.setUserId(userId);
	        	
	        	util.getIdNhapp(userId);
	        	assign.setNppTen(util.getTenNhaPP());
	        	Searchkhachhang(request,assign);
	        	assign.CreateRsSearch();
		    	session.setAttribute("assign", assign);
		    	String nextJSP = request.getContextPath() + "/pages/Distributor/BangGiaSieuThiAsign.jsp";
		    	response.sendRedirect(nextJSP);
	    	
	    }}
	    
	}
	private void Searchkhachhang(HttpServletRequest request,IBanggiasieuthi obj)
	{
		
		String nppId = request.getParameter("nppId");
    	if ( nppId == null)
    		nppId = "";
    	obj.setNppId(nppId);
    	
    	
    	String sql_search="select pk_Seq,ten from khachhang where trangthai='1' and npp_fk='"+nppId+"' and kbh_fk='100002'";
    	
		String bgstTen = request.getParameter("bgTen");
		System.out.println("bgstTen"+bgstTen);
    	if (bgstTen.equals("")){
    		bgstTen = "";
    	}
    	obj.setTenbanggia(bgstTen);
    	
    	
		String hchid = request.getParameter("hchid");
		if ( !hchid.equals("")){
    	
    		sql_search=sql_search +" and hch_fk='"+hchid+"'";
    	}
    	obj.setHangCuaHang(hchid);
    	
		String lchid = request.getParameter("lchid");
    	if ( !lchid.equals("")){
    	
    		sql_search=sql_search +" and lch_fk='"+lchid+"'";
    	}
    	obj.setLoaiCuahang(lchid);
    	   	
    	String quanhuyenid = request.getParameter("quanhuyenid");
    	if (!quanhuyenid.equals(""))
    		{
    		sql_search=sql_search +" and quanhuyen_fk='"+quanhuyenid+"'";
    	}
    	obj.setquanhuyen(quanhuyenid);
    	
    	String tinhthanhid = request.getParameter("tinhthanhid");
    	if (!tinhthanhid.equals("")){
    		
    		sql_search=sql_search +" and tinhthanh_fk='"+tinhthanhid+"'";
    	}
    	obj.setTinhThanh(tinhthanhid);
    	
    	
    	String vtchid = request.getParameter("vtchid");
    	if (!vtchid.equals("")){
    		sql_search=sql_search +" and vtch_fk='"+vtchid+"'";
    	}
  
    	obj.setViTriCuahang(vtchid);
    	
    	String ddkdid = request.getParameter("ddkdid");
    
    	if (!ddkdid.equals("")){
    		sql_search=sql_search +" and pk_seq in (select khachhang_fk from KHACHHANG_TUYENBH kh "+
    		" inner join tuyenbanhang tbh on kh.tbh_fk=tbh.pk_seq where tbh.ddkd_fk='"+ddkdid+"' and tbh.npp_fk='"+nppId+"' )";
    		
    	}
    	sql_search=sql_search +" order by pk_seq";
    	obj.setddkdid(ddkdid);
    	
    	String sql="select pk_seq,ten from khachhang where npp_fk='"+nppId+"' and trangthai='1'";
    	System.out.println(sql);
    	dbutils db=new dbutils();
    	ResultSet rs=db.get(sql);
    	List<IKhachHang_Bgst> listkh=new ArrayList<IKhachHang_Bgst>();		
    	IKhachHang_Bgst kh;
    	String npplist="";
    	//Luu 1 bang hastable de luu nhung khachhang da chon
    	Hashtable<String,String> htablkhachhang=new Hashtable<String, String>();
    	try{
    	while(rs.next()){
						if (request.getParameter("chbox" + rs.getString("pk_Seq")) != null ){
							 kh=new KhachHang_Bgst();
							kh.setCheck("1");
							kh.setIdKh(rs.getString("pk_seq"));
							kh.setTenKh(rs.getString("ten"));
							listkh.add(kh);
							htablkhachhang.put(rs.getString("pk_Seq"), rs.getString("pk_Seq"));
							if (npplist.length()==0){
								npplist = npplist + "'" + rs.getString("pk_Seq") + "'";
							}else{
								npplist = npplist + ",'" +  rs.getString("pk_seq") + "'";
							}
					}					
    		}
    	
    	System.out.println("npplist npplist :"+npplist);
    	rs=db.get(sql_search);
    	
    	while(rs.next()){
    		if (!htablkhachhang.containsKey(rs.getString("pk_seq"))){
    		 kh=new KhachHang_Bgst();
    		 kh.setCheck("0");
    		 kh.setIdKh(rs.getString("pk_seq"));
    		 kh.setTenKh(rs.getString("ten"));
    		 listkh.add(kh);
    		 	if (npplist.length()==0){
					npplist = npplist + "'" + rs.getString("pk_Seq") + "'";
				}else{
					npplist = npplist + ",'" +  rs.getString("pk_seq") + "'";
				}
    		}
    	}
    	if(rs!=null){
    	rs.close();
    	}
    	if(db!=null){
    	db.shutDown();
    	}
    	}catch(Exception err){
    		System.out.println("Error here 252 :"+err);
    	}
    	
    	obj.setKhList(listkh);
    	obj.setkhachhangString(npplist);
    	
    	}
	private String getSearchQuery(HttpServletRequest request, IBanggiasieuthiList obj)
	{
		
		String nppId = request.getParameter("nppId");
    	if ( nppId == null)
    		nppId = "";
    	obj.setNppId(nppId);
    	
		String bgstTen = request.getParameter("bgstTen");
    	if ( bgstTen == null)
    		bgstTen = "";
    	obj.setTenbanggia(bgstTen);
    	   	
    	String dvkdId = request.getParameter("dvkdTen");
    	if (dvkdId == null)
    		dvkdId = "";    	
    	obj.setDvkdId(dvkdId);
    		
    	String query = "select * from vwbanggiasieuthi where nppId='" + nppId + "' ";
    	Utility util = new Utility();
    	if (bgstTen.length()>0)
    	{
			query = query + " and upper(dbo.ftBoDau(bgstTen)) like upper(N'%" + util.replaceAEIOU(bgstTen) + "%')";			
    	}
    	  	
    	if (dvkdId.length()>0){
			query = query + " and dvkdId ='" + dvkdId + "'";			
    	}
    	
    	query = query + "  order by bgstTen";
    	System.out.println("tim kiem : "+query);
    	return query;
	}
    	
	private void Delete(String bgstId) 
	{
		dbutils db = new dbutils();
		db.update("delete from banggiast_sanpham where bgst_fk='" + bgstId + "'");
		//Xoa Bang Cha
		db.update("delete from banggiasieuthi where pk_seq = '" + bgstId + "'");
		db.shutDown();		
	}

}
