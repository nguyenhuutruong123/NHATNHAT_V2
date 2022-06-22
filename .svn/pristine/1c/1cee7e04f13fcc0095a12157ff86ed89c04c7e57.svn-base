package geso.dms.center.servlets.reports;

import geso.dms.center.beans.bcchartdoanhsosp.IBcchartdoanhsosp;
import geso.dms.center.beans.bcchartdoanhsosp.imp.Bcchartdoanhsosp;
import geso.dms.center.beans.bcchartsanpham.IBcchartsanpham;
import geso.dms.center.beans.bcchartsanpham.imp.Bcchartsanpham;
import geso.dms.center.db.sql.dbutils;
import geso.dms.center.util.Utility;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class BCChartDoanhSoSPSvl extends HttpServlet
{
	private static final long serialVersionUID = 1L;
	PrintWriter out;

	public BCChartDoanhSoSPSvl()
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

		IBcchartdoanhsosp obj = new Bcchartdoanhsosp();
		obj.setUserId(userId);

		String msg = "";
		obj.createRs();
		obj.init();
		
		session.setAttribute("obj", obj);

		String nextJSP = request.getContextPath() + "/pages/Center/Bcchartdoanhsosanpham.jsp";
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
		
		Bcchartdoanhsosp obj;
		obj = new Bcchartdoanhsosp();
		String thang = request.getParameter("thang");
			if(thang == null)
				thang = "";
		
		obj.setThang(thang);
		String[] idKenh = request.getParameterValues("kenhId");
		String chuoi ="";
		if (idKenh != null)
		{
			for (int i = 0; i < idKenh.length; i++)
			{

				chuoi += idKenh[i] + ",";
			}
		}	
		if(chuoi.length() > 0)
			chuoi = chuoi.substring(0,chuoi.length() - 1);
		obj.setIdKenh(chuoi);
		System.out.println(chuoi);
		String[] skutest = request.getParameterValues("spId");
		 chuoi ="";
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
			obj.setSpId(chuoi);
			
			String loai = request.getParameter("loai");
			if(loai == null)
				loai = "0";
			obj.setLoai(loai);
			
			System.out.println("Loại init"+loai);
			
			String nam = request.getParameter("nam");
			if(nam == null)
				nam = "";
			obj.setnam(nam);
			
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
			obj.setNhId(chuoi);
			
			
			
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
			obj.setbmId(chuoi);
			
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
			obj.setgsbhId(chuoi);
			
			obj.createRs();
			obj.setUserId(userId);
			obj.init();
		
			
			session.setAttribute("obj", obj);
			session.setAttribute("userId", userId);

			response.sendRedirect(request.getContextPath() + "/pages/Center/Bcchartdoanhsosanpham.jsp");
		}
	}


