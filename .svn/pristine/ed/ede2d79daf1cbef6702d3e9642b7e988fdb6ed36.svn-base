package geso.dms.distributor.servlets.loaisanpham;
import geso.dms.distributor.util.Utility;
import geso.dms.distributor.beans.loaisanpham.IErpLoaiSanPham;
import geso.dms.distributor.beans.loaisanpham.imp.ErpLoaiSanPham;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
@WebServlet("/ErpLoaiSanPhamSvl")
public class ErpLoaiSanPhamSvl extends HttpServlet
{
	private static final long serialVersionUID = 1L;
	Utility util = new Utility();
	IErpLoaiSanPham lspList;
	
	public ErpLoaiSanPhamSvl( )
	{
		super();
	}
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		System.out.println("Do get ErpLoaiSanPhamSvl");
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");
		HttpSession session = request.getSession();
		Utility util = new Utility();
		String querystring = request.getQueryString();
		String userId = util.getUserId(querystring);
		if (userId.length() == 0) userId = util.antiSQLInspection(request.getParameter("userId"));
		String action = util.getAction(querystring);
		String id = util.getId(querystring);
		
		String msg = "";
		lspList = new ErpLoaiSanPham();
				
		if (action.equals("delete"))
		{
			IErpLoaiSanPham lspBean = new ErpLoaiSanPham(id);
			lspBean.Delete();
			msg = lspBean.getMsg();
		}
		
		this.lspList.setMsg(msg);
		this.lspList.Search();
		this.lspList.CreateRs();
		session.setAttribute("lspList", lspList);
		String nextJSP = request.getContextPath() + "/pages/Distributor/ErpLoaiSanPham.jsp";
		response.sendRedirect(nextJSP);
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		System.out.println("Do post ErpLoaiSanPhamSvl");
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");
		HttpSession session = request.getSession();
		// this.lspList = (IErpLoaiSanPham) session.getAttribute("lspList");
		this.lspList = new ErpLoaiSanPham();
		if (lspList == null)
		{
			System.out.println("Null mie roi");
		}
		String nextJSP = "";
		String action = request.getParameter("action");
		String ma = request.getParameter("ma");
		String ten = request.getParameter("ten");
		String taikhoan = request.getParameter("taikhoan");
		
		if (action == null) action = "";
		if (taikhoan == null) taikhoan = "";
		
		lspList.setTaiKhoan(taikhoan);
		lspList.setMa(ma);
		lspList.setTen(ten);
		if (action.equals("New"))
		{
			System.out.println("Vao trang tao moi");
			IErpLoaiSanPham lspBean = new ErpLoaiSanPham();
			lspBean.CreateRs();
			session.setAttribute("lspBean", lspBean);
			nextJSP = request.getContextPath() + "/pages/Distributor/ErpLoaiSanPhamNew.jsp";
		}
		else
		{
			System.out.println("Search------- ");
			lspList.Search();
			lspList.CreateRs();
			nextJSP = request.getContextPath() + "/pages/Distributor/ErpLoaiSanPham.jsp";
		}
		session.setAttribute("lspList", lspList);
		response.sendRedirect(nextJSP);
	}
}
