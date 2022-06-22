package geso.dms.center.servlets.mochot;


import geso.dms.center.beans.mochot.Imochot;
import geso.dms.center.beans.mochot.imp.mochot;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class Chenck
 */
@WebServlet("/Chenck")
public class mochotSVL extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public mochotSVL() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

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
			Imochot obj=(Imochot) new mochot();
			obj.init();
			 session.setAttribute("obj", obj);
			
			
			
		String nextJSP = request.getContextPath() + "/pages/Center/mochot.jsp";
		response.sendRedirect(nextJSP);
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {


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
			
		Imochot obj=(Imochot) new mochot();
		String mahoadon=request.getParameter("mahoadon");
		if(mahoadon!=null)
			obj.setMahoadon(mahoadon);
		
		String mahopdong=request.getParameter("mahopdong");
		if(mahopdong!=null)
			obj.setMahopdong(mahopdong);
		
		String ishd=request.getParameter("ishd");
		if(ishd!=null)
			obj.setIshd(ishd);
		
		String nppid=request.getParameter("nppid");
		if(nppid!=null)
			obj.setNppid(nppid);
		 String action = request.getParameter("action");
			if(action.equals("chenck"))
			{
				if(ishd.equals("0"))
					obj.mohoadon(mahoadon, nppid);
				if(ishd.equals("1"))
					obj.mohopdong(mahopdong, nppid);
			}
		obj.init();
		session.setAttribute("obj", obj); 
		String nextJSP = request.getContextPath() + "/pages/Center/mochot.jsp";
		response.sendRedirect(nextJSP);
		
		}
		
	}

}
