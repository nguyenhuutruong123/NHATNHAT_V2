package geso.dms.distributor.servlets.noptiennpp;

import geso.dms.distributor.beans.noptiennpp.ISanpham;
import geso.dms.distributor.beans.noptiennpp.INopTienNPP;
import geso.dms.distributor.beans.noptiennpp.INopTienNPPList;
import geso.dms.distributor.beans.noptiennpp.imp.NopTienNPP;
import geso.dms.distributor.beans.noptiennpp.imp.NopTienNPPList;
import geso.dms.distributor.beans.noptiennpp.imp.Sanpham;

import geso.dms.center.db.sql.dbutils;
import geso.dms.center.util.Utility;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.ResultSet;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.extentech.formats.XLS.Array;

public class NopTienNPPUpdateSvl extends javax.servlet.http.HttpServlet implements javax.servlet.Servlet {
	private static final long serialVersionUID = 1L;



	public NopTienNPPUpdateSvl() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		HttpSession session = request.getSession();
		if(!Utility.val_doget(session, request, response))
		{
			session.setAttribute("flag",null);
			response.sendRedirect(request.getContextPath() + "/redirect.jsp");
			return;
		}
		else
		{
			session.setAttribute("flag",null);
		}
		String userId = (String) session.getAttribute("userId");
		String userTen = (String) session.getAttribute("userTen");
		String sum = (String) session.getAttribute("sum");
		geso.dms.center.util.Utility cutil = (geso.dms.center.util.Utility) session.getAttribute("util");
		if (!cutil.check(userId, userTen, sum)) {
			response.sendRedirect(request.getContextPath() + "/redirect.jsp");
		} else {
			session.setMaxInactiveInterval(30000);

			Utility util = new Utility();

			String querystring = request.getQueryString();
			userId = util.getUserId(querystring);

			

			if (userId.length() == 0)
				userId = util.antiSQLInspection(request.getParameter("userId"));


			String id = util.antiSQLInspection(request.getParameter("id"));
			String msg = "";
			

			INopTienNPP dhBean = new NopTienNPP(id);
			dhBean.setUserId(userId); // phai co UserId truoc khi Init			
			dhBean.init();
			
			String nextJSP;
			nextJSP = request.getContextPath() + "/pages/Distributor/NopTienNPPUpdate.jsp";
			if ( Utility.parseInt( util.antiSQLInspection(request.getParameter("action")) ) == Utility.XEM  ) {
				
				dhBean.setIsDisplay(1);
			}

			session.setAttribute("dhBean", dhBean);
			response.sendRedirect(nextJSP);
		}
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		
		 
		HttpSession session = request.getSession();
		String userId = (String) session.getAttribute("userId");
		String userTen = (String) session.getAttribute("userTen");
		 geso.dms.center.util.Csrf csdr=new geso.dms.center.util.Csrf(request,response,false,true ,false);
			if(!csdr.__validate_post())
			{
				response.sendRedirect(request.getContextPath() + "/redirect.jsp");
				return;
			}
		String sum = (String) session.getAttribute("sum");
		Utility util = new Utility();
		
		if (!util.check(userId, userTen, sum)) {
			response.sendRedirect(request.getContextPath() + "/redirect.jsp");
		} else 
		{
			request.setCharacterEncoding("UTF-8");
			response.setCharacterEncoding("UTF-8");
			response.setContentType("text/html; charset=UTF-8");

			session.setMaxInactiveInterval(30000);

			
			INopTienNPP dhBean;
			userId = util.antiSQLInspection(request.getParameter("userId"));
			String id = util.antiSQLInspection(request.getParameter("id"));
			if(id ==  null)
				id = "";
			
			
			dhBean = new NopTienNPP(id);
			dhBean.setUserId(userId);
			dhBean.getNppInfo();
			System.out.println("dhId = "+id);
			
			
			dhBean.setNgaygiaodich(request.getParameter("ngaygiaodich") == null ? "":request.getParameter("ngaygiaodich"));
			dhBean.setDoituong(request.getParameter("doituong") == null ? "":request.getParameter("doituong"));			
			dhBean.setGhichu(request.getParameter("ghichu") == null ? "":request.getParameter("ghichu"));			
			dhBean.setNpp_sotien(request.getParameterValues("doituongIds"), request.getParameterValues("sotien"));
			
			
			
			String action = request.getParameter("action");
			if (action.equals("save"))
			{
				boolean kq = false;
				
				if(id.length() <=0)
					kq = dhBean.CreateXuatKho();
				else kq = dhBean.UpdateXuatKho();
				
				if(kq)
				{
					dhBean.DBclose();
					INopTienNPPList obj = new NopTienNPPList();	
					obj.setUserId(userId);
					obj.init(null);
					session.setAttribute("obj", obj);
					String nextJSP = request.getContextPath() + "/pages/Distributor/NopTienNPP.jsp";
					response.sendRedirect(nextJSP);
					return;
				}	
			}
			
			session.setAttribute("dhBean", dhBean);
			String nextJSP = request.getContextPath() + "/pages/Distributor/NopTienNPPUpdate.jsp";
			response.sendRedirect(nextJSP);
			return;
		}
	}

	
}
