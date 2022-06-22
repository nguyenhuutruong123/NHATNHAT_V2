package geso.dms.distributor.servlets.BacSi;

import geso.dms.distributor.beans.BacSi.IBacSi;
import geso.dms.distributor.beans.BacSi.IBacSiList;
import geso.dms.distributor.beans.BacSi.imp.BacSi;
import geso.dms.distributor.beans.BacSi.imp.BacSiList;
import geso.dms.distributor.beans.khachhang.*;
import geso.dms.distributor.beans.khachhang.imp.*;
import geso.dms.distributor.beans.khachhangtt.IKhachhangTTList;
import geso.dms.distributor.beans.khachhangtt.imp.KhachhangTTList;
import geso.dms.distributor.util.Utility;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class BacSiUpdateSvl extends HttpServlet
{
	private static final long serialVersionUID = 1L;

	public BacSiUpdateSvl()
	{
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		HttpSession session = request.getSession();
		String userId = (String) session.getAttribute("userId");
		String userTen = (String) session.getAttribute("userTen");
		String sum = (String) session.getAttribute("sum");
		geso.dms.center.util.Utility cutil = (geso.dms.center.util.Utility) session.getAttribute("util");
		if (!cutil.check(userId, userTen, sum))
		{
			response.sendRedirect(request.getContextPath() + "/redirect.jsp");
		} 
		else
		{
			IBacSi khBean;
			PrintWriter out;

			out = response.getWriter();
			Utility util = new Utility();

			String querystring = request.getQueryString();
			userId = util.getUserId(querystring);

			out.println(userId);

			if (userId.length() == 0)
				userId = util.antiSQLInspection(request.getParameter("userId"));

			String id = util.getId(querystring);
			
			khBean = new BacSi(id);
			khBean.setUserId(userId);
			khBean.createRS();
			session.setAttribute("khBean", khBean);
			String nextJSP = request.getContextPath() + "/pages/Distributor/BacSiUD.jsp";
			response.sendRedirect(nextJSP);
		}

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		HttpSession session = request.getSession();
		String userId = (String) session.getAttribute("userId");
		String userTen = (String) session.getAttribute("userTen");
		String sum = (String) session.getAttribute("sum");
		geso.dms.center.util.Utility cutil = (geso.dms.center.util.Utility) session.getAttribute("util");
		if (!cutil.check(userId, userTen, sum))
		{
			response.sendRedirect(request.getContextPath() + "/redirect.jsp");
		} else
		{

			IBacSi khBean;
			PrintWriter out;

			request.setCharacterEncoding("UTF-8");
			response.setCharacterEncoding("UTF-8");
			response.setContentType("text/html; charset=UTF-8");

			out = response.getWriter();
			Utility util = new Utility();
			String id = util.antiSQLInspection(request.getParameter("id"));
			
			if (id == null)
			{
				khBean = new BacSi("");
			} else
			{
				khBean = new BacSi(id);
			}

				
			
			userId = util.antiSQLInspection(request.getParameter("userId"));
			khBean.setUserId(userId);

			String ma = util.antiSQLInspection(request.getParameter("ma"));
			if (ma==null)
				ma = "";
			khBean.setMa(ma);
			
			String ten = util.antiSQLInspection(request.getParameter("ten"));
			if (ten == null)
				ten = "";
			khBean.setTen(ten);
			
			String sodienthoai = util.antiSQLInspection(request.getParameter("sodienthoai"));
			if (sodienthoai == null)
				sodienthoai = "";
			khBean.setSDT(sodienthoai);
			
			String diachi = util.antiSQLInspection(request.getParameter("diachi"));
			if (diachi == null)
				diachi = "";
			khBean.setDiachi(diachi);
						
			String email = util.antiSQLInspection(request.getParameter("email"));
			if (email == null)
				email = "";
			khBean.setEmail(email);
			
			String trangthai = util.antiSQLInspection(request.getParameter("trangthai"));
			if (trangthai == null)
				trangthai = "0";
			khBean.setTrangthai(trangthai);
			
			String nppId = util.antiSQLInspection(request.getParameter("nppId"));
			if (nppId == null)
				nppId = "";
			khBean.setNppid(nppId);
			
			
			
		
			
			String[] khid = request.getParameterValues("khid");
			khBean.setKhachhang_fk(khid);
			
			
		
			boolean error = false;
		
			if (ma.trim().length() <= 0  )
			{
				khBean.setMsg("Vui lòng nhập tên khách hàng");
				error = true;
			}

			if (ten.trim().length() <= 0  )
			{
				khBean.setMsg("Vui lòng chọn tỉnh/thành phố");
				error = true;
			}
			String action = request.getParameter("action");
			
			
			if (!error)
			{
				if (action.equals("save"))
				{
					if (id == null)
					{						
						if (!(khBean.CreateBS(request)))
						{
							khBean.createRS();
							session.setAttribute("khBean", khBean);
							String nextJSP = request.getContextPath() + "/pages/Distributor/BacSiNew.jsp";
							response.sendRedirect(nextJSP);
						} else
						{
							IBacSiList obj = new BacSiList();
							
							
							
							obj.setUserId(userId);
							obj.init("");
							session.setAttribute("obj", obj);
							String nextJSP = request.getContextPath() + "/pages/Distributor/BacSi.jsp";
							response.sendRedirect(nextJSP);
						}
					} 
					else
					{
						if (!(khBean.UpdateBS(request)))
						{
							khBean.createRS();
							session.setAttribute("khBean", khBean);
							String nextJSP = request.getContextPath() + "/pages/Distributor/BacSiUD.jsp";
							response.sendRedirect(nextJSP);
						} 
						else
						{
							IBacSiList obj = new BacSiList();
							obj.setUserId(userId);
							obj.init("");
							session.setAttribute("obj", obj);
							String nextJSP = request.getContextPath() + "/pages/Distributor/BacSi.jsp";
							response.sendRedirect(nextJSP);
						}
					}
				} 
				else
				{
					khBean.setUserId(userId);
				
					khBean.createRS();
					
					session.setAttribute("khBean", khBean);

					String nextJSP;
					if (id == null)
					{
						nextJSP = request.getContextPath() + "/pages/Distributor/BacSiNew.jsp";
					} else
					{
						nextJSP = request.getContextPath() + "/pages/Distributor/BacSiUD.jsp";
					}
					response.sendRedirect(nextJSP);
				}
			} 
			else
			{
				khBean.setUserId(userId);
				
				khBean.createRS();
				
				session.setAttribute("khBean", khBean);

				String nextJSP;
				if (id == null)
				{
					nextJSP = request.getContextPath() + "/pages/Distributor/BacSiNew.jsp";
				} else
				{
					nextJSP = request.getContextPath() + "/pages/Distributor/BacSiUD.jsp";
				}
				response.sendRedirect(nextJSP);

			}
		}
	}

	private String getDateTime()
	{
		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		Date date = new Date();
		return dateFormat.format(date);
	}
	
	public String[] addThem1DongNull(String[]a )
	{
		if(a == null)
		{
			return  new String[1];
		}
		String[] kq = new String[(a.length +1)];
		for(int i = 0 ; i < a.length; i++)
		{
			kq[i] = a[i];
		}
		return kq;
	}
	public String[] xoa1Dong(String[]a,int dong )
	{
		ArrayList<String> result = new ArrayList<String>();
		
		for(int i = 0 ; i < a.length; i++)
		{
			if(i !=  dong)
				result.add( a[i] == null ? "" : a[i]);
		}
		
		
		String[] str = result.toArray(new String[result.size()]);
		
		return  str;
	}
}
