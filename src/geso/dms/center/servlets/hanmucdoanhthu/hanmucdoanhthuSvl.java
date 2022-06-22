package geso.dms.center.servlets.hanmucdoanhthu;

import geso.dms.center.beans.hanmucdoanhthu.Ihanmucdoanhthu;
import geso.dms.center.beans.hanmucdoanhthu.imp.hanmucdoanhthu;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class hanmucdoanhthuSvl
 */
@WebServlet("/hanmucdoanhthuSvl")
public class hanmucdoanhthuSvl extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public hanmucdoanhthuSvl() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
	    response.setCharacterEncoding("UTF-8");
	    response.setContentType("text/html; charset=UTF-8"); 	    
	    HttpSession session = request.getSession();	    
	    Ihanmucdoanhthu obj=new hanmucdoanhthu();
		obj.init();
		session.setAttribute("obj", obj);
	    
	    String nextJSP = request.getContextPath() + "/pages/Center/hanmucdoanhthu.jsp";
		response.sendRedirect(nextJSP);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		request.setCharacterEncoding("UTF-8");
	    response.setCharacterEncoding("UTF-8");
	    response.setContentType("text/html; charset=UTF-8"); 	    
	    HttpSession session = request.getSession();	    
	    
		Ihanmucdoanhthu obj=new hanmucdoanhthu();
		obj.init();
		session.setAttribute("obj", obj);
	    String nextJSP = request.getContextPath() + "/pages/Center/hanmucdoanhthunew.jsp";
		response.sendRedirect(nextJSP);
	}

}
