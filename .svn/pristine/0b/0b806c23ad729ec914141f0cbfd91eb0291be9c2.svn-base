package geso.dms.center.servlets.nhomhang;


import geso.dms.center.beans.nhomhang.INhomHang;
import geso.dms.center.beans.nhomhang.INhomHangList;
import geso.dms.center.beans.nhomhang.imp.NhomHang;
import geso.dms.center.beans.nhomhang.imp.NhomHangList;
import geso.dms.center.util.Utility;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/NhomHangUpdateSvl")
public class NhomHangUpdateSvl extends HttpServlet
{
	private static final long serialVersionUID = 1L;
	PrintWriter out;
	public NhomHangUpdateSvl()
	{
		super();
		
	}
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{

		INhomHang nhBean;

		Utility util = new Utility();
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
		String querystring = request.getQueryString();
		String userId = util.getUserId(querystring);
		System.out.println(userId);
		if (userId.length() == 0)
			userId = util.antiSQLInspection(request.getParameter("userId"));
		String id = util.getId(querystring);
		nhBean = new NhomHang(id);
		nhBean.init();
		nhBean.setUserId(userId);

		session.setAttribute("nhBean", nhBean);
		String nextJSP = request.getContextPath() + "/pages/Center/NhomHangUpdate.jsp";
		if(querystring.indexOf("display") > 0)
	    {
	    	nextJSP = request.getContextPath() + "/pages/Center/NhomHangDisplay.jsp";
	    }
		response.sendRedirect(nextJSP);

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");
		HttpSession session = request.getSession();
		geso.dms.center.util.Csrf csdr=new geso.dms.center.util.Csrf(request,response,false,true ,false);
		if(!csdr.__validate_post())
		{
			response.sendRedirect(request.getContextPath() + "/redirect.jsp");
			return;
		}
		INhomHang nhBean;
		this.out = response.getWriter();
		Utility util = new Utility();

		String id = util.antiSQLInspection(request.getParameter("id"));
		if (id == null)
		{
			nhBean = new NhomHang("");
		} else
		{
			nhBean = new NhomHang(id);
		}

		String userId = util.antiSQLInspection(request.getParameter("userId"));
		nhBean.setUserId(userId);

		String ten = util.antiSQLInspection(request.getParameter("ten"));
		if (ten == null)
			ten = "";
		nhBean.setTen(ten);

		String ma = util.antiSQLInspection(request.getParameter("ma"));
		if (ma == null)
			ma = "";
		nhBean.setMa(ma);
		
		String spId="";
		
		String[] spIds = request.getParameterValues("spId");
		if (spIds != null)
		{
			int size = spIds.length;
			for (int i = 0; i < size; i++)
			{
				spId += spIds[i] + ",";
			}
			if (spId.length() > 0)
			{
				spId = spId.substring(0, spId.length() - 1);
			}
		}
		nhBean.setSpId(spId);
		
		
		
		boolean error = false;

		if (ma.trim().length() == 0)
		{
			nhBean.setMsg("Vui lòng nhập mã nhóm");
			error = true;
		}

		if (ten.trim().length() == 0)
		{
			nhBean.setMsg("Vui lòng nhập tên nhóm");
			error = true;
		}

		
		String action = request.getParameter("action");
		String nextJSP = "";
		if (!error)
		{
			if (action.equals("save"))
			{
				INhomHangList obj = new NhomHangList();
				if (id == null)
				{
					if (!nhBean.save())
					{
						session.setAttribute("nhBean", nhBean);
						nhBean.setUserId(userId);
						nhBean.createRs();
						nextJSP = request.getContextPath() + "/pages/Center/NhomHangNew.jsp";
						response.sendRedirect(nextJSP);
					} else
					{
						obj.setUserId(userId);
						obj.init();
						session.setAttribute("obj", obj);
						nextJSP = request.getContextPath() + "/pages/Center/NhomHang.jsp";
						response.sendRedirect(nextJSP);
					}
				} else
				{
					if (!nhBean.edit())
					{
						session.setAttribute("nhBean", nhBean);
						nhBean.setUserId(userId);
						nhBean.createRs();
						nextJSP = request.getContextPath() + "/pages/Center/NhomHangUpdate.jsp";
						response.sendRedirect(nextJSP);
					} else
					{
						obj.setUserId(userId);
						obj.init();
						session.setAttribute("obj", obj);
						nextJSP = request.getContextPath() + "/pages/Center/NhomHang.jsp";
						response.sendRedirect(nextJSP);
					}
				}
			} else
			{
				nhBean.createRs();
				session.setAttribute("nhBean", nhBean);
				if (id == null)
				{
					nextJSP = request.getContextPath() + "/pages/Center/NhomHangNew.jsp";
				} else
				{
					nextJSP = request.getContextPath() + "/pages/Center/NhomHangUpdate.jsp";
				}
				response.sendRedirect(nextJSP);
			}

		} else
		{
			nhBean.createRs();
			session.setAttribute("nhBean", nhBean);
			if (id == null)
			{
				nextJSP = request.getContextPath() + "/pages/Center/NhomHangNew.jsp";
			} else
			{
				nextJSP = request.getContextPath() + "/pages/Center/NhomHangUpdate.jsp";
			}
			response.sendRedirect(nextJSP);
		}
	}
	
}
