package geso.dms.center.servlets.reports;

import geso.dms.center.beans.bcchart.IBcchart;
import geso.dms.center.beans.bcchart.imp.Bcchart;
import geso.dms.center.util.Utility;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class BCChartDophuSvl extends HttpServlet
{
	private static final long serialVersionUID = 1L;
	PrintWriter out;

	public BCChartDophuSvl()
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

		IBcchart obj = new Bcchart();
		obj.setUserId(userId);

		String msg = "";
		
		//BÁO CÁO TRONG THÁNG HIỆN TẠI
		obj.initData("DoanhThu");
		
		obj.setMsg(msg);
		session.setAttribute("obj", obj);

		String nextJSP = request.getContextPath() + "/pages/Center/BCChartDoPhu.jsp";
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

		Bcchart obj;		
		obj = new Bcchart();
		
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
		
		String theomuc = request.getParameter("theomuc");
		if(theomuc == null)
			theomuc = "0";
		obj.setTheomuc(theomuc);
		
		//LẤY BÁO CÁO
		obj.initData("DoanhThu");

		session.setAttribute("obj", obj);
		session.setAttribute("userId", userId);

		response.sendRedirect(request.getContextPath() + "/pages/Center/BCChartDoPhu.jsp");
	}

}
