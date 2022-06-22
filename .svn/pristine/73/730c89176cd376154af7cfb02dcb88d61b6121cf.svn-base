package geso.dms.distributor.servlets.khoasongay;

import geso.dms.distributor.beans.khoasongay.IKhoasongay;
import geso.dms.distributor.beans.khoasongay.imp.Khoasongay;
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


public class KhoasongaySvl extends HttpServlet 
{
	private static final long serialVersionUID = 1L;
    
	
    public KhoasongaySvl() 
    {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
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
			response.sendRedirect(request.getContextPath() + "/redirect.jsp");
		}else{
		IKhoasongay obj;

		PrintWriter out;
		
		request.setCharacterEncoding("UTF-8");
	    response.setCharacterEncoding("UTF-8");
	    response.setContentType("text/html; charset=UTF-8");
	    out = response.getWriter();

	    Utility util = new Utility();
	    
	    String querystring = request.getQueryString();
	   
		userId = util.getUserId(querystring);
	    out.println(userId);
	    
	    if (userId.length()==0)
	    	userId = util.antiSQLInspection(request.getParameter("userId"));
	    	    
	    obj = new Khoasongay();
	    obj.setUserId(userId);
	    obj.init();
	    
		session.setAttribute("obj", obj);
	    String nextJSP = request.getContextPath() + "/pages/Distributor/KhoaSoNgay.jsp";
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
		
			IKhoasongay obj;
	
			request.setCharacterEncoding("UTF-8");
		    response.setCharacterEncoding("UTF-8");
		    response.setContentType("text/html; charset=UTF-8");
			obj = new Khoasongay();
			Utility util = new Utility();
			
			userId = util.antiSQLInspection(request.getParameter("userId"));
			obj.setUserId(userId);
		    	    
			String nppId = util.antiSQLInspection(request.getParameter("nppId"));
			if (nppId == null)
				nppId = "";				
	    	obj.setNppId(nppId);
	    	
	    	String namks = util.antiSQLInspection(request.getParameter("namks"));
	    	if(namks == null)
	    		namks = "";
	    	obj.setNamks(namks);
	    	
	    	String thangks = util.antiSQLInspection(request.getParameter("thangks"));
	    	if(thangks == null)
	    		thangks = "";
	    	obj.setThangks(thangks);
	    	
			String action = request.getParameter("action");
			if(action.equals("save"))
			{
				if(obj.KhoaSoNgay(thangks))
				{
					//obj.createRs();
					obj.init();
					obj.setDksThanhCong("1");
					session.setAttribute("ngaykhoasonpp", thangks + " / " + namks );
					obj.setMessege("Khóa sổ tháng (" + thangks + ") thành công. Tháng khóa sổ tiếp theo: " + obj.getThangks());
					session.setAttribute("obj", obj);
					
					String nextJSP = request.getContextPath() + "/pages/Distributor/KhoaSoNgay.jsp";
					response.sendRedirect(nextJSP);	
				}
				else
				{
					obj.init();
					obj.setMessege(obj.getMessege());
					session.setAttribute("obj", obj);
					
					String nextJSP = request.getContextPath() + "/pages/Distributor/KhoaSoNgay.jsp";
					response.sendRedirect(nextJSP);	
				}
			}
			else  //submit
			{
				obj.createRs();
				session.setAttribute("obj", obj);
				
				String nextJSP = request.getContextPath() + "/pages/Distributor/KhoaSoNgay.jsp";
				response.sendRedirect(nextJSP);	
			}
		}
	}
	


}
