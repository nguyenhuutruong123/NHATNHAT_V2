package geso.dms.center.servlets.reports;

import geso.dms.center.beans.bcchartdoanhthursm.IBcchartdoanhthursm;
import geso.dms.center.beans.bcchartdoanhthursm.imp.Bcchartdoanhthursm;
import geso.dms.center.util.Utility;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class BCChartdoanhthursmSvl extends HttpServlet
{
	private static final long serialVersionUID = 1L;
	PrintWriter out;

	public BCChartdoanhthursmSvl()
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

		IBcchartdoanhthursm obj = new Bcchartdoanhthursm();
		obj.setUserId(userId);

		String msg = "";

		obj.init();
		obj.setMsg(msg);
		session.setAttribute("obj", obj);

		String nextJSP = request.getContextPath() + "/pages/Center/Bcchartdoanhthursm.jsp";
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

		Bcchartdoanhthursm obj;		
		obj = new Bcchartdoanhthursm();
		
		String userId = util.antiSQLInspection(request.getParameter("userId"));

		obj.setUserId(userId);
		String thang = request.getParameter("thang");
		if(thang == null)
			thang = "";
		obj.setThang(thang);
		String nam = request.getParameter("nam");
		if(nam == null)
			nam = "";
		obj.setnam(nam);
		
		String rsm1 = request.getParameter("rsm1");
		if(rsm1 == null)
			rsm1 = "";
		obj.setRsmId1(rsm1);
		
		String rsm2 = request.getParameter("rsm2");
		if(rsm2 == null)
			rsm2 = "";
		obj.setRsmId2(rsm2);
		
		obj.init();

		session.setAttribute("obj", obj);
		session.setAttribute("userId", userId);

		response.sendRedirect(request.getContextPath() + "/pages/Center/Bcchartdoanhthursm.jsp");
		}
	}


