package geso.dms.center.servlets.reports;

import geso.dms.center.beans.bcchartdoanhsodiaban.IBcchartdoanhsodiaban;
import geso.dms.center.beans.bcchartdoanhsodiaban.imp.Bcchartdoanhsodiaban;
import geso.dms.center.util.Utility;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class BCChartdoanhsodiabanSvl extends HttpServlet
{
	private static final long serialVersionUID = 1L;
	PrintWriter out;

	public BCChartdoanhsodiabanSvl()
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

		IBcchartdoanhsodiaban obj = new Bcchartdoanhsodiaban();
		obj.setUserId(userId);

		String msg = "";
		//BÁO CÁO TRONG THÁNG HIỆN TẠI
		obj.init();
		obj.setMsg(msg);
		session.setAttribute("obj", obj);

		String nextJSP = request.getContextPath() + "/pages/Center/Bcchartdoanhsodiaban.jsp";
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

		Bcchartdoanhsodiaban obj;		
		obj = new Bcchartdoanhsodiaban();
		
		String userId = util.antiSQLInspection(request.getParameter("userId"));

		obj.setUserId(userId);
		//LẤY PHẠM VI LẬP BÁO CÁO
		String thang = request.getParameter("thang");
		if(thang == null)
			thang = "";
		obj.setThang(thang);
		String nam = request.getParameter("nam");
		if(nam == null)
			nam = "";
		obj.setnam(nam);
		obj.init();

		session.setAttribute("obj", obj);
		session.setAttribute("userId", userId);

		response.sendRedirect(request.getContextPath() + "/pages/Center/Bcchartdoanhsodiaban.jsp");
		}
	}


