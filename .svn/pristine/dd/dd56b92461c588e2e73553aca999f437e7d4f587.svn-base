package geso.dms.center.servlets.quanhuyen;

import geso.dms.center.beans.quanhuyen.IQuanhuyen;
import geso.dms.center.beans.quanhuyen.IQuanhuyenList;
import geso.dms.center.beans.quanhuyen.imp.Quanhuyen;
import geso.dms.center.beans.quanhuyen.imp.QuanhuyenList;
import geso.dms.center.util.Utility;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class QuanhuyenUpdateSvl
 */
public class QuanhuyenUpdateSvl extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public QuanhuyenUpdateSvl() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");
		
		HttpSession session = request.getSession();
		/*
		 * if(!geso.dms.distributor.util.Utility.val_doget(session, request, response))
		 * { session.setAttribute("flag",null);
		 * response.sendRedirect(request.getContextPath() + "/redirect.jsp"); return; } else {
		 * session.setAttribute("flag",null); }
		 */
		Utility util = new Utility();
		String userId = util.antiSQLInspection(request.getParameter("userId"));
		
		IQuanhuyen quan = new Quanhuyen(userId);
		
		String type = request.getParameter("type");
		System.out.println("type la "+type);
		if (type == null) {
			type = "";
		}
		String id="";
		if(type.equals("update"))
		{
			 id = request.getParameter("update");
				if (id == null) {
					id = "";
				}
		}
		if(type.equals("display"))
		{
			 id = util.antiSQLInspection(request.getParameter("display"));
				if (id == null) {
					id = "";
				}
		}
	
		quan.setId(id);
		
		
		
		
		String delete = util.antiSQLInspection(request.getParameter("delete"));
		if (delete == null) {
			delete = "";
		}
		else
		{
			String msg=quan.deleteQuanhuyen(delete);
			IQuanhuyenList quanList = new QuanhuyenList();
		    quanList.init();		    
		    session.setAttribute("quanList", quanList);
			response.sendRedirect(request.getContextPath() + "/pages/Center/Quanhuyen.jsp");
			return;
		}
		
		quan.init();
		quan.createRs();
		quan.setType(type);
		
		session.setAttribute("quan", quan);
			response.sendRedirect(request.getContextPath() + "/pages/Center/QuanhuyenUpdate.jsp");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");
		
		HttpSession session = request.getSession();
		geso.dms.center.util.Csrf csdr=new geso.dms.center.util.Csrf(request,response,false,true ,false);
		/*if(!csdr.__validate_post())
		{
			response.sendRedirect(request.getContextPath() + "/redirect.jsp");
			return;
		}*/
		Utility util = new Utility();
		String userId = util.antiSQLInspection(request.getParameter("userId"));
		
		IQuanhuyen quan = new Quanhuyen(userId);
		
		String quanhuyenId = util.antiSQLInspection(request.getParameter("quanhuyenId"));
		if (quanhuyenId == null) {
			quanhuyenId = "";
		}
		quan.setId(quanhuyenId);
		
		String ten = util.antiSQLInspection(request.getParameter("ten"));
		if (ten == null) {
			ten = "";
		}
		quan.setTen(ten);
		
		String ma = util.antiSQLInspection(request.getParameter("ma"));
		if (ma == null) { ma = ""; }
		quan.setMa(ma);
		
		String tinhId = util.antiSQLInspection(request.getParameter("tinhId"));
		if (tinhId == null) {
			tinhId = "";
		}
		quan.setTinhId(tinhId);
		
		String action = util.antiSQLInspection(request.getParameter("action"));
		
		if (action.equals("save")) {
			if (quan.getId().equals("")) {
				if (!quan.createQuanhuyen()) {
					quan.init();
					quan.createRs();
					
					session.setAttribute("quan", quan);
					response.sendRedirect(request.getContextPath() + "/pages/Center/QuanhuyenUpdate.jsp");
				} else {
					quan.getDb().shutDown();
				    IQuanhuyenList quanList = new QuanhuyenList();
				    quanList.init();
				    
				    session.setAttribute("quanList", quanList);
					response.sendRedirect(request.getContextPath() + "/pages/Center/Quanhuyen.jsp");
				}
			} else {
				if (!quan.updateQuanhuyen()) {
					quan.init();
					quan.createRs();
					
					session.setAttribute("quan", quan);
					response.sendRedirect(request.getContextPath() + "/pages/Center/QuanhuyenUpdate.jsp");
				} else {
					quan.getDb().shutDown();
				    IQuanhuyenList quanList = new QuanhuyenList();
				    quanList.init();
				    
				    session.setAttribute("quanList", quanList);
					response.sendRedirect(request.getContextPath() + "/pages/Center/Quanhuyen.jsp");
				}
			}
		} else {
			quan.init();
			quan.createRs();
			
			session.setAttribute("quan", quan);
			response.sendRedirect(request.getContextPath() + "/pages/Center/QuanhuyenUpdate.jsp");
		}
	}

}
