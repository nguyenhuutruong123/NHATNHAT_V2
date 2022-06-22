package geso.dms.center.servlets.Chenck;

import geso.dms.center.beans.Chenck.Ichenck;
import geso.dms.center.beans.Chenck.imp.chietkhau;

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
public class Chenck extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Chenck() {
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
			Ichenck obj=(Ichenck) new chietkhau();
			String thang=request.getParameter("thang");
			if(thang!=null)
				obj.setThang(thang);
			String nam=request.getParameter("nam");
			if(nam!=null)
				obj.setNam(nam);
			String quy=request.getParameter("quy");
			if(quy!=null)
				obj.setQuy(quy);
			String boga=request.getParameter("boga");
			if(boga!=null)
				obj.setBoga(boga);
			
			String xanh=request.getParameter("xanh");
			if(boga!=null)
				obj.setBoga(xanh);
			obj.init();
			 session.setAttribute("obj", obj);
			
			
			
		String nextJSP = request.getContextPath() + "/pages/Center/chenck.jsp";
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
			
		Ichenck obj=(Ichenck) new chietkhau();
		String thang=request.getParameter("thang");
		if(thang!=null)
			obj.setThang(thang);
		System.out.println("thang"+thang);
		String nam=request.getParameter("nam");
		if(nam!=null)
			obj.setNam(nam);
		System.out.println("nam"+nam);
		String quy=request.getParameter("quy");
		if(quy!=null)
			obj.setQuy(quy);
		System.out.println("quy"+quy);
		String boga=request.getParameter("boga");
		if(boga!=null)
			obj.setBoga(boga);
		System.out.println("boga"+boga);
		String xanh=request.getParameter("xanh");
		if(boga!=null)
			obj.setXanh(xanh);
		
		System.out.println("xanh"+xanh);
		String isduno=request.getParameter("isdn");
		if(isduno!=null)
			obj.setIschenduno(isduno);
		
		String isckthang=request.getParameter("isck");
		if(isckthang!=null)
			obj.setIschenckthang(isckthang);
		System.out.println("ck "+isckthang);
		
		String dh=request.getParameter("dh");
		if(dh!=null)
			obj.setDh(dh);
		System.out.println("dh "+dh);
		
		
		String nppid=request.getParameter("nppid");
		if(nppid!=null)
			obj.setNppid(nppid);
		System.out.println("nppid "+nppid);
		
		obj.init();
		 String action = request.getParameter("action");
		if(action.equals("chenck"))
		{
		if(isckthang.equals("1"))
			obj.chenchietkhauthang(dh, nppid);
		
		if(isckthang.equals("0"))
			obj.chenchietkhauquy(dh, nppid);
		}
		
		
		
		session.setAttribute("obj", obj);
		 
		 
		 
		String nextJSP = request.getContextPath() + "/pages/Center/chenck.jsp";
		response.sendRedirect(nextJSP);
		
		}
		
	}

}
