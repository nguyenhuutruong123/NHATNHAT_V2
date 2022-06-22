package geso.dms.center.servlets.khuyenmaidacbiet;

import geso.dms.center.beans.khuyenmaidacbiet.ISanpham;
import geso.dms.center.beans.khuyenmaidacbiet.IKhuyenMaiDacBiet;
import geso.dms.center.beans.khuyenmaidacbiet.IKhuyenMaiDacBietList;
import geso.dms.center.beans.khuyenmaidacbiet.imp.Sanpham;
import geso.dms.center.beans.khuyenmaidacbiet.imp.KhuyenMaiDacBiet;
import geso.dms.center.beans.khuyenmaidacbiet.imp.KhuyenMaiDacBietList;
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

public class KhuyenMaiDacBietUpdateSvl extends javax.servlet.http.HttpServlet implements javax.servlet.Servlet {
	private static final long serialVersionUID = 1L;



	public KhuyenMaiDacBietUpdateSvl() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		HttpSession session = request.getSession();
		if(!geso.dms.distributor.util.Utility.val_doget(session, request, response))
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
			

			IKhuyenMaiDacBiet dhBean = new KhuyenMaiDacBiet(id);
			dhBean.setUserId(userId); // phai co UserId truoc khi Init			
			dhBean.init();
			
			String nextJSP;
			nextJSP = request.getContextPath() + "/pages/Center/KhuyenMaiDacBietUpdate.jsp";
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
		geso.dms.center.util.Csrf csdr=new geso.dms.center.util.Csrf(request,response,false,true ,false);
		if(!csdr.__validate_post())
		{
			response.sendRedirect(request.getContextPath() + "/redirect.jsp");
			return;
		}
		String userId = (String) session.getAttribute("userId");
		String userTen = (String) session.getAttribute("userTen");

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

			
			IKhuyenMaiDacBiet dhBean;
			userId = util.antiSQLInspection(request.getParameter("userId"));
			String id = util.antiSQLInspection(request.getParameter("id"));
			if(id ==  null)
				id = "";
			
			
			dhBean = new KhuyenMaiDacBiet(id);
			dhBean.setUserId(userId);
			System.out.println("dhId = "+id);
			
			dhBean.setKhoId(request.getParameter("khoId") == null ? "":request.getParameter("khoId"));
			dhBean.setTungay(request.getParameter("tungay") == null ? "":request.getParameter("tungay"));
			dhBean.setDenngay(request.getParameter("denngay") == null ? "":request.getParameter("denngay"));
			dhBean.setNppId(request.getParameter("nppId") == null ? "":request.getParameter("nppId"));
			dhBean.setGhichu(request.getParameter("diengiai") == null ? "":request.getParameter("diengiai"));
			dhBean.setScheme(request.getParameter("scheme") == null ? "":request.getParameter("scheme"));
			dhBean.setPt_chietkhau(request.getParameter("ptck") == null ? "":request.getParameter("ptck"));
			dhBean.setKhonhan_fk(request.getParameter("khonhanId") == null ? "":request.getParameter("khonhanId"));
			
			
			String[] nppId = request.getParameterValues("nppId");
			String[] spId = request.getParameterValues("spId");
			String[] chonName = request.getParameterValues("chonName");
			dhBean.getData().clear();
			if(chonName != null)
			{
				for(int i = 0; i < chonName.length ; i ++)
				{
					if(chonName[i].equals("1"))
					{
						
						dhBean.getData().add( new String[] { nppId[i], spId[i] } );
					}
				}
			}
			
			
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
					IKhuyenMaiDacBietList obj = new KhuyenMaiDacBietList();	
					obj.setUserId(userId);
					obj.init(null);
					session.setAttribute("obj", obj);
					String nextJSP = request.getContextPath() + "/pages/Center/KhuyenMaiDacBiet.jsp";
					response.sendRedirect(nextJSP);
					return;
				}	
			}
			
			session.setAttribute("dhBean", dhBean);
			String nextJSP = request.getContextPath() + "/pages/Center/KhuyenMaiDacBietUpdate.jsp";
			response.sendRedirect(nextJSP);
			return;
		}
	}

	
}
