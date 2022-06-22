package geso.dms.center.servlets.dieuchinhtonkho;

import geso.dms.center.beans.dieuchinhtonkho.IDieuchinhtonkho;
import geso.dms.center.beans.dieuchinhtonkho.imp.Dieuchinhtonkho;
import geso.dms.center.db.sql.dbutils;
import geso.dms.center.util.Utility;

import java.io.IOException;
import java.sql.ResultSet;


import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class DctkDisplaySvl extends HttpServlet
{
	private static final long serialVersionUID = 1L;

	public DctkDisplaySvl()
	{
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
	
		final HttpServletResponse response2=response;
		
		if(!cutil.check(userId, userTen, sum)){
			response.sendRedirect(request.getContextPath() + "/redirect.jsp");
			}else{ 
				String nextJSP;
				Utility util = new Utility();
		
				String querystring = request.getQueryString();
				userId = util.getUserId(querystring);
		
				if (userId.length() == 0)
					userId = request.getParameter("userId");
		
				String id = util.getId(querystring);
				String action = util.getAction(querystring);
		
				IDieuchinhtonkho dctkBean = (IDieuchinhtonkho) new Dieuchinhtonkho();
				dctkBean.setUserId(userId);
				String sql = "select ten from nhanvien where pk_seq=" + userId;
		
				dbutils db = new dbutils();
				ResultSet rs = db.get(sql);
				if (rs != null)
				{
					try
					{
						if (rs.next())
						{
							dctkBean.setUserTen(rs.getString("ten"));
						}
						rs.close();
					} catch (Exception e)
					{
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
		
				}
				db.shutDown();
				dctkBean.setId(id);
				dctkBean.initDisplay();
				session.setAttribute("dctkBean", dctkBean);
				nextJSP = request.getContextPath() + "/pages/Center/DieuChinhTonKhoDisplay.jsp";
				response.sendRedirect(nextJSP);
		}

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{

	}

}
