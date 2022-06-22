package geso.dms.center.servlets.thongbaodoanhso;


import geso.dms.center.beans.thongbao.imp.thongbaodoanhso;
import geso.dms.center.beans.thongbaodoanhso.Ithongbaodoanhso;
import geso.dms.center.util.Utility;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


public class thongbaodoanhsoSvl extends HttpServlet 
{
	private static final long serialVersionUID = 1L;
	
	PrintWriter out; 
	
    public thongbaodoanhsoSvl() 
    {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		
		Ithongbaodoanhso obj;
		
		request.setCharacterEncoding("UTF-8");
	    response.setCharacterEncoding("UTF-8");
	    response.setContentType("text/html; charset=UTF-8");
	    this.out  = response.getWriter();
	    	    
	    HttpSession session = request.getSession();	    

	    Utility util = new Utility();
	    out = response.getWriter();
	    	    
	    String querystring = request.getQueryString();
	    String userId = util.getUserId(querystring);
	    out.println(userId);
	    
	    if (userId.length()==0)
	    	userId = util.antiSQLInspection(request.getParameter("userId"));
	    
	    obj = new thongbaodoanhso();
	    obj.setUserId(userId);
	    obj.init();
		session.setAttribute("obj", obj);
				
		String nextJSP = request.getContextPath() + "/pages/Center/thongbaodoanhso.jsp";
		response.sendRedirect(nextJSP);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		
		Ithongbaodoanhso obj = new thongbaodoanhso();
		
		request.setCharacterEncoding("UTF-8");
	    response.setCharacterEncoding("UTF-8");
	    response.setContentType("text/html; charset=UTF-8");
	    this.out  = response.getWriter();
	    	    
	    HttpSession session = request.getSession();	    
	    
	    Utility util = new Utility();
	    
	    String userId = util.antiSQLInspection(request.getParameter("userId"));
		obj.setUserId(userId);
		
		String giosandoz = request.getParameter("giosandoz");
		obj.setGiosandoz(giosandoz);
		
		String phutsandoz = request.getParameter("phutsandoz");
		obj.setPhutsandoz(phutsandoz);
			
		String thoigiankhoasandoz = request.getParameter("thoigiankhoasandoz");
		obj.setNgaysandoz(thoigiankhoasandoz);
		
	    obj.setUserId(userId);
	    
	    
	    String[] email=request.getParameterValues("email");
	    ArrayList<String> arr=new ArrayList<String>();
	    
	    for(int i=0;i<email.length;i++)
	    {
	    	if(email[i].length()>0)
	    	arr.add(email[i]);
	    }
	    
	    	    
	    String action = request.getParameter("action");
		if(action.equals("save"))
		{ 
			if(arr.size()>0)
			obj.luu_email(arr);
			obj.init();
			session.setAttribute("obj", obj);
			String nextJSP = request.getContextPath() + "/pages/Center/thongbaodoanhso.jsp";
			response.sendRedirect(nextJSP);
		}
		
		
	}

}
