package geso.dms.center.servlets.reports;

import geso.dms.center.beans.bcchartdoanhsosp.IBcchartdoanhsosp;
import geso.dms.center.beans.bcchartdoanhsosp.imp.Bcchartdoanhsosp;
import geso.dms.center.beans.bcchartkhachhangmuahang.IBcchartkhachhangmuahang;
import geso.dms.center.beans.bcchartkhachhangmuahang.imp.Bcchartkhachhangmuahang;
import geso.dms.center.util.Utility;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class BCKhachHangMuaHangSvl extends HttpServlet {
	private static final long serialVersionUID = 1L;
	PrintWriter out;

	public BCKhachHangMuaHangSvl ()
	{
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
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

		if (userId.length() == 0)
			userId = util.antiSQLInspection(request.getParameter("userId"));

		IBcchartkhachhangmuahang obj = new Bcchartkhachhangmuahang();
		obj.setUserId(userId);

		String msg = "";
		obj.createRs();
		obj.init();
		obj.setMsg(msg);
		session.setAttribute("obj", obj);

		String nextJSP = request.getContextPath() + "/pages/Center/BCKhachHangMuaHang.jsp";
		response.sendRedirect(nextJSP);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");
		this.out = response.getWriter();

		HttpSession session = request.getSession();

		out = response.getWriter();
		Utility util = new Utility();

		String userId = util.antiSQLInspection(request.getParameter("userId"));
		
		IBcchartkhachhangmuahang obj;
		obj = new Bcchartkhachhangmuahang();
		String thangbatdau = request.getParameter("thang1");
		String thangketthuc = request.getParameter("thang2");
		String nam1= request.getParameter("nam1");
		String nam2= request.getParameter("nam2");
		if(thangbatdau == null) thangbatdau="";
		if(thangketthuc==null) thangketthuc="";
		if(nam1==null) nam1="";
		if(nam2==null) nam2="";
		
		obj.setThangbatdau(thangbatdau);
		obj.setThangketthuc(thangketthuc);
		obj.setNam1(nam1);
		obj.setNam2(nam2);
		
		System.out.println("\n thang bat dau:"+ thangbatdau);
		System.out.println("\n thang ket thuc:"+ thangketthuc);
		System.out.println("\n nam 1:"+ nam1);
		System.out.println("\n nam 2:"+ nam2);
		
		
		String[] skutest = request.getParameterValues("spId");
		String chuoi ="";
			if (skutest != null)
			{
				for (int i = 0; i < skutest.length; i++)
				{

					chuoi += skutest[i] + ",";
				}
			}	
			System.out.println("sp "+chuoi);
			if(chuoi.length() > 0)
				chuoi = chuoi.substring(0,chuoi.length() - 1);
			
			System.out.print("\n nhom id: "+ chuoi);
			
			obj.setSpid(chuoi);
			
			
			String nhId[] = request.getParameterValues("nhId");
			chuoi ="";
			if (nhId != null)
			{
				for (int i = 0; i < nhId.length; i++)
				{

					chuoi += nhId[i] + ",";
				}
			}	
			if(chuoi.length() > 0)
				chuoi = chuoi.substring(0,chuoi.length() - 1);
			System.out.print("\n nhom id: "+ chuoi);
			obj.setNhid(chuoi);
			
			
			
			String bmId[] = request.getParameterValues("bmId");
			chuoi ="";
			if (bmId != null)
			{
				for (int i = 0; i < bmId.length; i++)
				{

					chuoi += bmId[i] + ",";
				}
			}	
			if(chuoi.length() > 0)
				chuoi = chuoi.substring(0,chuoi.length() - 1);
			System.out.print("\n nhom id: "+ chuoi);
			obj.setBmid(chuoi);
			
			String gsbhId[] = request.getParameterValues("gsbhId");
			chuoi ="";
			if (gsbhId != null)
			{
				for (int i = 0; i < gsbhId.length; i++)
				{

					chuoi += gsbhId[i] + ",";
				}
			}	
			if(chuoi.length() > 0)
				chuoi = chuoi.substring(0,chuoi.length() - 1);
			System.out.print("\n nhom id: "+ chuoi);
			obj.setGsbhid(chuoi);
			
			
			String nspId[] = request.getParameterValues("nspId");
			chuoi ="";
			if (nspId != null)
			{
				for (int i = 0; i < nspId.length; i++)
				{

					chuoi += nspId[i] + ",";
				}
			}	
			if(chuoi.length() > 0)
				chuoi = chuoi.substring(0,chuoi.length() - 1);
			System.out.print("\n nhom id: "+ chuoi);
			
			obj.setNspid(chuoi);
			
			
			obj.createRs();
			obj.setUserId(userId);
			obj.init();
		
			
			session.setAttribute("obj", obj);
			session.setAttribute("userId", userId);

			response.sendRedirect(request.getContextPath() + "/pages/Center/BCKhachHangMuaHang.jsp");
		}
}
