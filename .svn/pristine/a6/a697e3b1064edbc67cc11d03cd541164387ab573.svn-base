package geso.dms.distributor.servlets.loaitaikhoan;

import geso.dms.distributor.util.Utility;
import geso.dms.distributor.beans.loaitaikhoan.IErpLoaiTaiKhoan;
import geso.dms.distributor.beans.loaitaikhoan.imp.ErpLoaiTaiKhoan;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/ErpLoaiTaiKhoanSvl")
public class ErpLoaiTaiKhoanSvl extends HttpServlet
{
	private static final long serialVersionUID = 1L;
	Utility util = new Utility();
	

	public ErpLoaiTaiKhoanSvl()
	{
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		// logFilter.doFilter(request,response,this.)
		System.out.println("Do get ErpLoaiTaiKhoanSvl");
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");
		HttpSession session = request.getSession();
		Utility util = new Utility();
		String querystring = request.getQueryString();
		String userId = util.getUserId(querystring);
		
		if (userId.length() == 0)
			userId = util.antiSQLInspection(request.getParameter("userId"));
		String action = util.getAction(querystring);
		String Id = util.getId(querystring);
		
		IErpLoaiTaiKhoan ltk = new ErpLoaiTaiKhoan();
		
		String nppId = util.getIdNhapp(userId);
	    if(nppId == null)
	    	nppId = "";
	    ltk.setnppId(nppId);    
	    		
		if (action.equals("delete"))
		{
			ltk = new ErpLoaiTaiKhoan(Id);
			ltk.DeleteLtk();
			System.out.println("Ngung hoat dong nhom tai khoan");
		}
		ltk.setUserId(userId);
		ltk.search();
		session.setAttribute("ltk", ltk);
		String nextJSP = request.getContextPath() + "/pages/Distributor/ErpLoaiTaiKhoan.jsp";
		response.sendRedirect(nextJSP);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		System.out.println("Do post ErpLoaiTaiKhoanUpdateSvl");
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");
		HttpSession session = request.getSession();
		String nextJSP = "";
		String ten = util.antiSQLInspection(request.getParameter("Ten"));
		String ma = util.antiSQLInspection(request.getParameter("Ma"));
		String action = request.getParameter("action");
		
		IErpLoaiTaiKhoan ltk = new ErpLoaiTaiKhoan(); //(ErpLoaiTaiKhoan) session.getAttribute("ltk");
		String userId = util.antiSQLInspection(request.getParameter("userId"));
		String nppId = util.getIdNhapp(userId);
	    if(nppId == null)
	    	nppId = "";
	    ltk.setnppId(nppId);
	    	    
		String chixem = request.getParameter("chixem");
		ltk.setChixem(chixem);
		
		if (ten == null)
		{
			ten = "";
		}
		ltk.setTen(ten);
		if (ma == null)
		{
			ma = "";
		}
		ltk.setMa(ma);
		System.out.println("Action : " + action);
		if (action == null)
		{
			action = "";
		}
		if (action.equals("New"))
		{
			System.out.println("Vao trang tao moi");
			IErpLoaiTaiKhoan ltkBean = new ErpLoaiTaiKhoan();
			ltkBean.setnppId(nppId);  
			
			session.setAttribute("ltkBean", ltkBean);
			nextJSP = request.getContextPath() + "/pages/Distributor/ErpLoaiTaiKhoanNew.jsp";
		}
		if (action.equals("Search"))
		{
			System.out.println("Search ");
			ltk.search();
			session.setAttribute("ltk", ltk);
			nextJSP = "/TraphacoERP/pages/Erp/ErpLoaiTaiKhoan.jsp";
		}
		response.sendRedirect(nextJSP);
	}
}
