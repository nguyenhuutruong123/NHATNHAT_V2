package geso.dms.center.servlets.tinhthanh;

import geso.dms.center.beans.tinhthanh.ITinhthanh;
import geso.dms.center.beans.tinhthanh.ITinhthanhList;
import geso.dms.center.beans.tinhthanh.imp.Tinhthanh;
import geso.dms.center.beans.tinhthanh.imp.TinhthanhList;
import geso.dms.center.util.Utility;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class TinhthanhUpdateSvl
 */
public class TinhthanhUpdateSvl extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public TinhthanhUpdateSvl() {
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
		Utility util = new Utility();
		String userId = util.antiSQLInspection(request.getParameter("userId"));
		
		ITinhthanh tinh = new Tinhthanh(userId);
		
		String id = util.antiSQLInspection(request.getParameter("update"));
		if (id == null) {
			id = "";
		}
		tinh.setId(id);
		
		String isDisplay = util.antiSQLInspection(request.getParameter("isDisplay"));
		if (isDisplay == null) {
			isDisplay = "0";
		}
		tinh.setIsDisplay(isDisplay);
		
		tinh.init();
		tinh.createRs();
		session.setAttribute("tinh", tinh);
		response.sendRedirect(request.getContextPath() + "/pages/Center/TinhthanhUpdate.jsp");
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
		if(!csdr.__validate_post())
		{
			response.sendRedirect(request.getContextPath() + "/redirect.jsp");
			return;
		}
		
		Utility util = new Utility();
		String userId = util.antiSQLInspection(request.getParameter("userId"));
		
		ITinhthanh tinh = new Tinhthanh(userId);
		
		String tinhthanhId = util.antiSQLInspection(request.getParameter("tinhthanhId"));
		if (tinhthanhId == null) {
			tinhthanhId = "";
		}
		tinh.setId(tinhthanhId);
		
		String ten = util.antiSQLInspection(request.getParameter("ten"));
		if (ten == null) {
			ten = "";
		}
		tinh.setTen(ten);
		
		
		String ma = util.antiSQLInspection(request.getParameter("ma"));
		if (ma == null) {
			ma = "";
		}
		tinh.setMa(ma);
		
		String vungId = util.antiSQLInspection(request.getParameter("vungId"));
		if (vungId == null) {
			vungId = "";
		}
		tinh.setVungId(vungId);
		
		String action = util.antiSQLInspection(request.getParameter("action"));
		
		if (action.equals("save")) {
			if (tinh.getId().equals("")) {
				if (tinh.createTinh()) {
					tinh.getDb().shutDown();
				    ITinhthanhList tinhList = new TinhthanhList();
				    tinhList.init();
				    
				    session.setAttribute("tinhList", tinhList);
					response.sendRedirect(request.getContextPath() + "/pages/Center/Tinhthanh.jsp");
				} else {
					tinh.init();
					tinh.createRs();
					session.setAttribute("tinh", tinh);
					response.sendRedirect(request.getContextPath() + "/pages/Center/TinhthanhUpdate.jsp");
				}
			} else {
				if (tinh.updateTinh()) {
					tinh.getDb().shutDown();
				    ITinhthanhList tinhList = new TinhthanhList();
				    tinhList.init();
				    
				    session.setAttribute("tinhList", tinhList);
					response.sendRedirect(request.getContextPath() + "/pages/Center/Tinhthanh.jsp");
				} else {
					tinh.init();
					tinh.createRs();
					session.setAttribute("tinh", tinh);
					response.sendRedirect(request.getContextPath() + "/pages/Center/TinhthanhUpdate.jsp");
				}
			}
		}
	}

}
