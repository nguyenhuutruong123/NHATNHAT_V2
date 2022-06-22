package geso.dms.center.servlets.quanhuyen;

import geso.dms.center.beans.quanhuyen.imp.*;
import geso.dms.center.beans.quanhuyen.*;
import geso.dms.center.util.Utility;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class PhuongXaUpdateSvl extends HttpServlet
{
	private static final long serialVersionUID = 1L;
	private PrintWriter out;

	public PhuongXaUpdateSvl()
	{
		super();
	}

	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException
	{
		HttpSession session = request.getSession();

		this.out = response.getWriter();
		Utility util = new Utility();

		String querystring = request.getQueryString();
		String userId = util.getUserId(querystring);

		out.println(userId);

		if (userId.length() == 0)
			userId = util.antiSQLInspection(request.getParameter("userId"));

		String id = util.getId(querystring);

		IPhuongXa vmBean = new PhuongXa();
		vmBean.setId(id);
		vmBean.setUserId(userId);
		vmBean.Init();
		session.setAttribute("obj", vmBean);
		String nextJSP = "/Codupha/pages/Center/PhuongXaNew.jsp";
		response.sendRedirect(nextJSP);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");
		HttpSession session = request.getSession();

		IPhuongXa vmBean = new PhuongXa();
		this.out = response.getWriter();
		Utility util = new Utility();

		String id = util.antiSQLInspection(request.getParameter("Id"));
		if (id == null)
		{
			id = "";
		} 
		vmBean.setId(id);

		String userId = util.antiSQLInspection(request.getParameter("userId"));
		vmBean.setUserId(userId);

		String ttId = util.antiSQLInspection(request.getParameter("ttId"));
		if (ttId == null)
			ttId = "";
		vmBean.setTtId(ttId);

		String qhId = util.antiSQLInspection(request.getParameter("qhId"));
		if (qhId == null)
			qhId = "";
		vmBean.setQhId(qhId);

		String ma = util.antiSQLInspection(request.getParameter("ma"));
		if (ma == null)
			ma = "";
		vmBean.setMa(ma);

		String ten = util.antiSQLInspection(request.getParameter("ten"));
		if (ten == null)
			ten = "";
		vmBean.setTen(ten);
		String action = request.getParameter("action");

		if (action.equals("save"))
		{
			if (id.length() == 0)
			{
				if (!(vmBean.Create()))
				{
					vmBean.createRs();
					session.setAttribute("obj", vmBean);
					vmBean.setUserId(userId);
					String nextJSP = "/Codupha/pages/Center/PhuongXaNew.jsp";
					response.sendRedirect(nextJSP);
				} 
				else
				{
					IQuanhuyen obj = new Quanhuyen();
					obj.setQhId(qhId);
					obj.setTtId(ttId);
					obj.init("action");
				    session.setAttribute("obj", obj);
					String nextJSP = "/Codupha/pages/Center/Quanhuyen.jsp";
					response.sendRedirect(nextJSP);
					return;
				}

			} 
			else
			{
				if (!(vmBean.Update()))
				{
					vmBean.createRs();
					session.setAttribute("obj", vmBean);
					vmBean.setUserId(userId);
					String nextJSP = "/Codupha/pages/Center/PhuongXaNew.jsp";
					response.sendRedirect(nextJSP);
				} 
				else
				{
					IQuanhuyen obj = new Quanhuyen();
					obj.setQhId(qhId);
					obj.setTtId(ttId);
					obj.init("action");
				    session.setAttribute("obj", obj);
					String nextJSP = "/Codupha/pages/Center/Quanhuyen.jsp";
					response.sendRedirect(nextJSP);
					return;
				}
			}
		} 
		else
		{
			vmBean.setUserId(userId);
			vmBean.createRs();
			session.setAttribute("obj", vmBean);

			String nextJSP;
			nextJSP = "/Codupha/pages/Center/PhuongXaNew.jsp";
			response.sendRedirect(nextJSP);

		}
	}


}
