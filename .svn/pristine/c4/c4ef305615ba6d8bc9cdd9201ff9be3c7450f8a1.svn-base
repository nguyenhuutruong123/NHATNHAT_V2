package geso.dms.distributor.servlets.dangkykhuyenmaitichluy;

import geso.dms.distributor.beans.dangkykhuyenmaitichluy.IDangkykhuyenmaitichluy;
import geso.dms.distributor.beans.dangkykhuyenmaitichluy.IDangkykhuyenmaitichluyList;
import geso.dms.distributor.beans.dangkykhuyenmaitichluy.imp.Dangkykhuyenmaitichluy;
import geso.dms.distributor.beans.dangkykhuyenmaitichluy.imp.DangkykhuyenmaitichluyList;
import geso.dms.distributor.util.Utility;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class DangkykhuyenmaitichluyUpdateSvl extends HttpServlet 
{
	private static final long serialVersionUID = 1L;
	    
    public DangkykhuyenmaitichluyUpdateSvl() {
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
		}else{
	    
	    PrintWriter out; 
		
		IDangkykhuyenmaitichluy obj;
		
		out = response.getWriter();
		Utility util = new Utility();
		
    	String querystring = request.getQueryString();
	    userId = util.getUserId(querystring);
	    
	    out.println(userId);
	    
	    if (userId.length()==0)
	    	userId = request.getParameter("userId");
	    String action = util.getAction(querystring);
	    String id = util.getId(querystring);  	
	    obj = new Dangkykhuyenmaitichluy();
	    obj.setUserId(userId);
	    obj.setId(id);    
	    obj.init();
	    
	    session.setAttribute("obj", obj);  	
	    	if(action.equals("update"))
	    		response.sendRedirect(request.getContextPath() + "/pages/Distributor/DangKyKhuyenMaiTichLuyNew.jsp");
	    	else if(action.equals("dislay")){
	    		response.sendRedirect(request.getContextPath() + "/pages/Distributor/DangKyKhuyenMaiTichLuyDislay.jsp");
	    	}else if(action.equals("chot")){
	    		 obj.Chot();
	    		 DangkykhuyenmaitichluyList   obj1  = new DangkykhuyenmaitichluyList();
	    	     obj1.setUserId(userId);
	    	     obj1.init();
	             session.setAttribute("obj",obj1);
	             String nextJSP = request.getContextPath() + "/pages/Distributor/DangKyKhuyenMaiTichLuy.jsp";
	             response.sendRedirect(nextJSP);
	            
	    	}else{
	    		if(obj.Delete()){
	    			DangkykhuyenmaitichluyList   obj1  = new DangkykhuyenmaitichluyList();
		    	    obj1.setUserId(userId);
		    	    obj1.init();
		            session.setAttribute("obj",obj1);
		            String nextJSP = request.getContextPath() + "/pages/Distributor/DangKyKhuyenMaiTichLuy.jsp";
		            response.sendRedirect(nextJSP);
	    		}
	    	}
	    }
	    }

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		HttpSession session = request.getSession();
		String userId = (String) session.getAttribute("userId");  
		String userTen = (String) session.getAttribute("userTen");  	
		String sum = (String) session.getAttribute("sum");
		geso.dms.center.util.Utility cutil = (geso.dms.center.util.Utility) session.getAttribute("util");
		if(!cutil.check(userId, userTen, sum)){
			response.sendRedirect(request.getContextPath() + "/redirect.jsp");
		}else{
		PrintWriter out; 
		
		IDangkykhuyenmaitichluy obj;
		
		request.setCharacterEncoding("UTF-8");
	    response.setCharacterEncoding("UTF-8");
	    response.setContentType("text/html; charset=UTF-8");
	    out = response.getWriter();
	    obj = new Dangkykhuyenmaitichluy();
	    
	    userId = request.getParameter("userId");
	    obj.setUserId(userId);
	    
	    String ctkmId = request.getParameter("ctkmId");
	    obj.setCtkmId(ctkmId);
	    
	    String nppId = request.getParameter("nppId");
	    obj.setNppId(nppId);
	    
	    String id = request.getParameter("id");
	    obj.setId(id);
	    
	    String nppTen = request.getParameter("nppTen");
	    obj.setNppTen(nppTen);
	    
	    String nvbhId = request.getParameter("nvbhId");
	    if(nvbhId == null)
	    	nvbhId = "";
	    obj.setNvbhIds(nvbhId);
	    
	    String khachhang[] = request.getParameterValues("khIds");
	    if(khachhang != null)
	    {
	    	String kh = "";
	    	for(int i = 0; i < khachhang.length; i++ )
	    	{
	    		kh += khachhang[i] + ",";
	    	}
	    	if(kh.trim().length() > 0)
	    	{
	    		kh = kh.substring(0, kh.length() - 1);
	    		obj.setKhId(kh);
	    	}
	    }
	    
	    String action = request.getParameter("action");
	    
	    if(action.equals("submit"))
	    {   
	    	obj.init();
	    	obj.createRs();
	    	session.setAttribute("obj", obj);  	 		
	    	response.sendRedirect(request.getContextPath() + "/pages/Distributor/DangKyKhuyenMaiTichLuyNew.jsp");	
	    }
	    else
	    {
	    	if(obj.getId().length()>0)
	    	{
	    		if(obj.edit()){
		    		IDangkykhuyenmaitichluyList objl  = new DangkykhuyenmaitichluyList();
		    		objl.setUserId(userId);
		    		objl.init();
		    		session.setAttribute("obj",objl);
		    		String nextJSP = request.getContextPath() + "/pages/Distributor/DangKyKhuyenMaiTichLuy.jsp";
		    		response.sendRedirect(nextJSP);
		    	}else{
		    		obj.init();
			    	obj.createRs();
			    	session.setAttribute("obj", obj);  	 		
		    		response.sendRedirect(request.getContextPath() + "/pages/Distributor/DangKyKhuyenMaiTichLuyNew.jsp");	
		    	}	
	    	}else{
	    		if(obj.save()){
		    		IDangkykhuyenmaitichluyList objl  = new DangkykhuyenmaitichluyList();
		    		objl.setUserId(userId);
		    		objl.init();
		    		session.setAttribute("obj",objl);
		    		String nextJSP = request.getContextPath() + "/pages/Distributor/DangKyKhuyenMaiTichLuy.jsp";
		    		response.sendRedirect(nextJSP);
		    	}else{
		    		obj.init();
			    	obj.createRs();
			    	session.setAttribute("obj", obj);  	 		
		    		response.sendRedirect(request.getContextPath() + "/pages/Distributor/DangKyKhuyenMaiTichLuyNew.jsp");	
		    	}
	    	}
	    	
	    }
	    out.println(action); 

	}}

}
