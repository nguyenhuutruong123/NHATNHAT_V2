package geso.dms.distributor.servlets.tuyenbanhang;

import geso.dms.distributor.beans.tuyenbanhang.*;
import geso.dms.distributor.beans.tuyenbanhang.imp.*;
import geso.dms.distributor.util.Utility;
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

public class TuyenbanhangUpdateSvl extends HttpServlet
{
	private static final long serialVersionUID = 1L;

	public TuyenbanhangUpdateSvl()
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
		if (!cutil.check(userId, userTen, sum))
		{
			response.sendRedirect(request.getContextPath() + "/redirect.jsp");
		} else
		{

			ITuyenbanhang tbhBean;
			PrintWriter out;

			out = response.getWriter();
			Utility util = new Utility();

			String querystring = request.getQueryString();
			userId = util.getUserId(querystring);

			userId=util.antiSQLInspection(userId);

			if (userId.length() == 0)
				userId = request.getParameter("userId");

			String id = util.getId(querystring);
			tbhBean = new Tuyenbanhang(id);
			tbhBean.setUserId(userId);
			tbhBean.init();		
			String nextJSP = request.getContextPath() + "/pages/Distributor/TuyenBanHangUpdate.jsp";
			String copy = request.getParameter("copy");
			if(copy != null)
			{			
				nextJSP = request.getContextPath() + "/pages/Distributor/TuyenBanHangNew.jsp";
				tbhBean.setDiengiai("");
				tbhBean.setId(null);
				tbhBean.setNgaylamviec("");
			}
			session.setAttribute("tbhBean", tbhBean);
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
		geso.dms.center.util.Utility cutil = (geso.dms.center.util.Utility) session.getAttribute("util");
		if (!cutil.check(userId, userTen, sum))
		{
			response.sendRedirect(request.getContextPath() + "/redirect.jsp");
		} else
		{

			ITuyenbanhang tbhBean;
			PrintWriter out;
			Utility util = new Utility();
			request.setCharacterEncoding("UTF-8");
			response.setCharacterEncoding("UTF-8");
			response.setContentType("text/html; charset=UTF-8");

			out = response.getWriter();

			String id = request.getParameter("id");
			if (id == null)
				tbhBean = new Tuyenbanhang("");
			else
				tbhBean = new Tuyenbanhang(id);

			userId = util.antiSQLInspection(request.getParameter("userId"));
			tbhBean.setUserId(userId);

			String nppId = util.antiSQLInspection(request.getParameter("nppId"));
			if (nppId == null)
				nppId = "";
			tbhBean.setNppId(nppId);

			String ddkdId =  util.antiSQLInspection(request.getParameter("ddkdTen"));
			if (ddkdId == null)
				ddkdId = "";
			tbhBean.setDdkdId(ddkdId);
			System.out.print("[ddkdId]"+ddkdId);
			String diengiai =  util.antiSQLInspection(request.getParameter("tbhTen"));
			if (diengiai == null)
				diengiai = "";
			tbhBean.setDiengiai(diengiai);

			String nlv =  util.antiSQLInspection(request.getParameter("ngaylamviec"));
			if (nlv == null)
				nlv = "";
			tbhBean.setNgaylamviec(nlv);
			System.out.println("nlv"+nlv +"-----"+tbhBean.getNgaylamviec());

			String khTen =  util.antiSQLInspection(request.getParameter("khTen"));
			if (khTen == null)
				khTen = "";
			tbhBean.setTenkhachhang(khTen);

			String diachi =  util.antiSQLInspection(request.getParameter("diachi"));
			if (diachi == null)
				diachi = "";
			tbhBean.setDiachi(diachi);

			String[] tansoIds =  util.antiSQLInspection_Array( request.getParameterValues("tansoList"));
			tbhBean.setTansoList(tansoIds);

			String[] kh_tbh_dptIds =  util.antiSQLInspection_Array(request.getParameterValues("kh_tbh_dptList"));
			tbhBean.setKh_Tbh_DptIds(kh_tbh_dptIds);

			String[] kh_tbh_cdptIds =  util.antiSQLInspection_Array(request.getParameterValues("kh_tbh_cdptList"));
			tbhBean.setKh_Tbh_CdptIds(kh_tbh_cdptIds);

			String[] thutu =  util.antiSQLInspection_Array(request.getParameterValues("thutu"));
			tbhBean.setSoTT(thutu);

			String ngaysua = getDateTime();
			tbhBean.setNgaysua(ngaysua);

			boolean error = false;

			if (ddkdId.trim().length() == 0)
			{
				tbhBean.setMessage("Vui lòng chọn đại diện kinh doanh");
				error = true;
			}

			if (diengiai.trim().length() == 0)
			{
				tbhBean.setMessage("Vui lòng nhập diễn giải tuyến bán hàng");
				error = true;
			}

			if (nlv.trim().length() == 0)
			{
				tbhBean.setMessage("Vui lòng chọn ngày làm việc");
				error = true;
			}

			String action = request.getParameter("action");

			if (action.equals("save"))
			{
				if (id == null)
				{
					System.out.println("Ban nhan nut tao moi ... \n");
					if (!(tbhBean.CreateTbh()))
					{
						tbhBean.setUserId(userId);
						tbhBean.createRS();
						session.setAttribute("tbhBean", tbhBean);
						String nextJSP = request.getContextPath() + "/pages/Distributor/TuyenBanHangNew.jsp";
						response.sendRedirect(nextJSP);
					} else
					{
						ITuyenbanhangList obj = new TuyenbanhangList();
						obj.setUserId(userId);
						obj.init("");
						session.setAttribute("obj", obj);
						String nextJSP = request.getContextPath() + "/pages/Distributor/TuyenBanHang.jsp";
						response.sendRedirect(nextJSP);
					}
				} else
				{
					if (!(tbhBean.UpdateTbh()))
					{
						tbhBean.setUserId(userId);
						tbhBean.init();
						session.setAttribute("tbhBean", tbhBean);
						String nextJSP = request.getContextPath() + "/pages/Distributor/TuyenBanHangUpdate.jsp";
						response.sendRedirect(nextJSP);
					} else
					{
						ITuyenbanhangList obj = new TuyenbanhangList();
						obj.setUserId(userId);
						obj.init("");
						session.setAttribute("obj", obj);
						String nextJSP = request.getContextPath() + "/pages/Distributor/TuyenBanHang.jsp";
						response.sendRedirect(nextJSP);
					}
				}
			} else
			{
				tbhBean.setUserId(userId);
				tbhBean.createRS();
				session.setAttribute("tbhBean", tbhBean);
				String nextJSP;
				if (id == null)
				{
					nextJSP = request.getContextPath() + "/pages/Distributor/TuyenBanHangNew.jsp";
				} else
				{
					nextJSP = request.getContextPath() + "/pages/Distributor/TuyenBanHangUpdate.jsp";
				}
				response.sendRedirect(nextJSP);
			}
		}
	}

	private String getDateTime()
	{
		DateFormat dateFormat = new SimpleDateFormat("dd/MMM/yyyy");
		Date date = new Date();
		return dateFormat.format(date);
	}

}
