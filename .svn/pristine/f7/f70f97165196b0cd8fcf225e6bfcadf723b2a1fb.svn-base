package geso.dms.distributor.servlets.buttoantonghop;

import geso.dms.distributor.db.sql.dbutils;
import geso.dms.distributor.util.Utility;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.ResultSet;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

import java.text.NumberFormat;
import java.text.DecimalFormat;

public class KeToanListAjaxSvl extends HttpServlet 
{
	private static final long serialVersionUID = 1L;

	public KeToanListAjaxSvl() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{


	}

	private String getDateTime()
	{
		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		Date date = new Date();
		return dateFormat.format(date);	
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{


		HttpSession session = request.getSession();
		Utility util = new Utility();
		String table = util.antiSQLInspection(request.getParameter("table"));
		
		System.out.println("------------KeToanListAjaxSvl--------------" + table);

		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");
		PrintWriter out = response.getWriter();
		NumberFormat formatter = new DecimalFormat("#,###,###");
		try 
		{
			
			
		} 
		catch(Exception e)
		{ 
			e.printStackTrace();
			out.write("Lỗi:" + e.getMessage());
		}

	}

}
