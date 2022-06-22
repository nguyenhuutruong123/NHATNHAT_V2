package geso.dms.center.servlets.dangkytrungbay;

import geso.dms.center.beans.dangkytrungbay.IDangkytrungbayTT;
import geso.dms.center.beans.dangkytrungbay.IDangkytrungbayTTList;
import geso.dms.center.beans.dangkytrungbay.imp.DangkytrungbayTT;
import geso.dms.center.beans.dangkytrungbay.imp.DangkytrungbayTTList;
import geso.dms.center.util.Utility;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class DangkytrungbayTTSvl extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
  
    public DangkytrungbayTTSvl() {
        super();
        
    }
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        
		HttpSession session = request.getSession();
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
		String userId = (String) session.getAttribute("userId");  
		String userTen = (String) session.getAttribute("userTen");  	
		String sum = (String) session.getAttribute("sum");
		geso.dms.center.util.Utility cutil = (geso.dms.center.util.Utility) session.getAttribute("util");
		if(!cutil.check(userId, userTen, sum)){
			response.sendRedirect("/SGPindex.jsp");
		}else{
		
		IDangkytrungbayTTList obj;
		PrintWriter out; 
		out = response.getWriter();
		Utility util = new Utility();
		
    	String querystring = request.getQueryString();
	    userId = util.getUserId(querystring);
	    
	    out.println(userId);
	    
	    if (userId.length()==0)
	    	userId = util.antiSQLInspection(request.getParameter("userId"));
	    
	    //String id = util.getId(querystring);  	
	    obj  = new DangkytrungbayTTList();
	    obj.setUserId(userId);
	    obj.init();
        session.setAttribute("obj",obj);
        String nextJSP = request.getContextPath() + "/pages/Center/DangKyTrungBayTT.jsp";
        response.sendRedirect(nextJSP);
		}
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
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
			response.sendRedirect("/SGPindex.jsp");
		}
		else{
			IDangkytrungbayTTList obj;
			PrintWriter out; 

			out = response.getWriter();
			Utility util = new Utility();
		  	obj  = new DangkytrungbayTTList();
			   
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
		    
		    String action = util.antiSQLInspection(request.getParameter("action"));
		    
		    obj.init();
		    System.out.println("Action :"+action);
		    if(action.equals("new"))
		    { 
		    	IDangkytrungbayTT objj = new DangkytrungbayTT();
		    	objj.setUserId(userId);
		    	objj.createRs();
	    	    session.setAttribute("obj",objj);
		        String nextJSP = request.getContextPath() + "/pages/Center/DangKyTrungBayTTNew.jsp";
		        response.sendRedirect(nextJSP);    	
		    }
		    else
		    {	
		    	session.setAttribute("obj",obj);
		    	
		    	String nextJSP = request.getContextPath() + "/pages/Center/DangKyTrungBayTT.jsp";
		    	response.sendRedirect(nextJSP);
		    }
			
	}}

}
