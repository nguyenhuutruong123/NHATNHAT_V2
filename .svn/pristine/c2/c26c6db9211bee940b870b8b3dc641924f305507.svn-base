package geso.dms.distributor.servlets.nhaphang;

import geso.dms.distributor.beans.nhaphang.INhaphang;
import geso.dms.distributor.beans.nhaphang.imp.Nhaphang;
import geso.dms.distributor.util.Utility;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class NhaphangDisplaySvl extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public NhaphangDisplaySvl() {
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

		PrintWriter out = response.getWriter();
		String nextJSP;
		Utility util = new Utility();
		
    	String querystring = request.getQueryString();
	    userId = util.getUserId(querystring);
	    
	    out.println(userId);
	    
	    if (userId.length()==0)
	    	userId = util.antiSQLInspection(request.getParameter("userId"));
	    
	    String id = util.getId(querystring);  	
	    out.println(id);
	   
	    INhaphang nhaphang = (INhaphang) new Nhaphang();
	    nhaphang.setUserId(userId);
	    
    	nhaphang.setId(id);
    	nhaphang.init();
    	out.println(nhaphang.getMessage());
    	if(nhaphang.getNppId()==null){
    		nextJSP = request.getContextPath() + "/pages/index.jsp";
    	}else
    	{
	    nextJSP = request.getContextPath() + "/pages/Distributor/NhapHangDisplay.jsp";
    	}	    
		session.setAttribute("nhaphang", nhaphang);			
	
		response.sendRedirect(nextJSP);
		}

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

	}

}
