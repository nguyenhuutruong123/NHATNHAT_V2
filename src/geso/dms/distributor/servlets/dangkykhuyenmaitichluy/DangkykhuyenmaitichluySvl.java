package geso.dms.distributor.servlets.dangkykhuyenmaitichluy;

import geso.dms.distributor.beans.dangkykhuyenmaitichluy.IDangkykhuyenmaitichluy;
import geso.dms.distributor.beans.dangkykhuyenmaitichluy.IDangkykhuyenmaitichluyList;
import geso.dms.distributor.beans.dangkykhuyenmaitichluy.imp.Dangkykhuyenmaitichluy;
import geso.dms.distributor.beans.dangkykhuyenmaitichluy.imp.DangkykhuyenmaitichluyList;
import geso.dms.distributor.beans.tuyenbanhang.imp.Tuyenbanhang;
import geso.dms.distributor.util.Utility;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class DangkykhuyenmaitichluySvl extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
  
    public DangkykhuyenmaitichluySvl() {
        super();
        
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        
		HttpSession session = request.getSession();
		String userId = (String) session.getAttribute("userId");  
		String userTen = (String) session.getAttribute("userTen");  	
		String sum = (String) session.getAttribute("sum");
		geso.dms.center.util.Utility cutil = (geso.dms.center.util.Utility) session.getAttribute("util");
		if(!cutil.check(userId, userTen, sum)){
			response.sendRedirect(request.getContextPath() + "/redirect.jsp");
		}else{
		
		IDangkykhuyenmaitichluyList obj;
		  PrintWriter out; 
			
		
		
		
		out = response.getWriter();
		Utility util = new Utility();
		
    	String querystring = request.getQueryString();
	    userId = util.getUserId(querystring);
	    
	    out.println(userId);
	    
	    if (userId.length()==0)
	    	userId = util.antiSQLInspection(request.getParameter("userId"));
	    
	    String id = util.getId(querystring);  	
	    obj  = new DangkykhuyenmaitichluyList();
	    obj.setUserId(userId);
	    obj.init();
        session.setAttribute("obj",obj);
        String nextJSP = request.getContextPath() + "/pages/Distributor/DangKyKhuyenMaiTichLuy.jsp";
        response.sendRedirect(nextJSP);
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
		IDangkykhuyenmaitichluyList obj;
		  PrintWriter out; 
			
		
			
			out = response.getWriter();
			Utility util = new Utility();
			  obj  = new DangkykhuyenmaitichluyList();
			   
			  obj.setUserId(userId);
			    
			    userId= util.antiSQLInspection(request.getParameter("userId"));
			    obj.setUserId(userId);
			    
			    String diengiai = util.antiSQLInspection(request.getParameter("diengiai"));
			    if(diengiai == null)
			    	diengiai ="";
			    obj.setDiengiai(diengiai);
			    
			    String tungay = util.antiSQLInspection(request.getParameter("tungay"));
			    if(tungay == null)
			    	tungay ="";
			    obj.setTungay(tungay);
			    String denngay = util.antiSQLInspection(request.getParameter("denngay"));
			    if(denngay == null)
			    	denngay ="";
			    obj.setDenngay(denngay);
			    
			    String action = request.getParameter("action");
			    
			    obj.init();
			    System.out.println("Action :"+action);
			    if(action.equals("new"))
			    { 
			    	IDangkykhuyenmaitichluy objj = new Dangkykhuyenmaitichluy();
			    	objj.setUserId(userId);
			    	objj.createRs();
		    	    session.setAttribute("obj",objj);
			        String nextJSP = request.getContextPath() + "/pages/Distributor/DangKyKhuyenMaiTichLuyNew.jsp";
			        response.sendRedirect(nextJSP);    	
			    }
			    else
			    {
			    	
			    	session.setAttribute("obj",obj);
			    	
			    	String nextJSP = request.getContextPath() + "/pages/Distributor/DangKyKhuyenMaiTichLuy.jsp";
			    	response.sendRedirect(nextJSP);
			    }
			
	}}

}
