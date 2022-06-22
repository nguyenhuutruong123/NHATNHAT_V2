package geso.dms.distributor.servlets.hangtralainpp;

import geso.dms.center.util.Utility;
import geso.dms.distributor.beans.hangtralai.IErpHangTraLaiNpp;
import geso.dms.distributor.beans.hangtralai.IErpHangTraLaiNppList;
import geso.dms.distributor.beans.hangtralai.imp.ErpHangTraLaiNpp;
import geso.dms.distributor.beans.hangtralai.imp.ErpHangTraLaiNppList;

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

public class ErpHangTraLaiNppUpdateSvl extends HttpServlet
{
	private static final long serialVersionUID = 1L;
	
	PrintWriter out;
	public ErpHangTraLaiNppUpdateSvl()
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
			session.setMaxInactiveInterval(30000);
			
			Utility util = new Utility();
			
			String querystring = request.getQueryString();
			userId = util.getUserId(querystring);
			
			if (userId.length() == 0)
				userId = util.antiSQLInspection(request.getParameter("userId"));
			
			String id = util.getId(querystring);
			IErpHangTraLaiNpp lsxBean = new ErpHangTraLaiNpp(id);
			lsxBean.setUserId(userId);
			
			String nextJSP = "";
			
			lsxBean.init();
			session.setAttribute("khoxuat", lsxBean.getKhoXuatId());
			session.setAttribute("kenhId", lsxBean.getKbhId());
			session.setAttribute("nppId", lsxBean.getNppId());
			
			if (!querystring.contains("display"))
				nextJSP = request.getContextPath() + "/pages/Distributor/ErpHangTraLaiNppUpdate.jsp";
			else
				nextJSP = request.getContextPath() + "/pages/Distributor/ErpHangTraLaiNppDisplay.jsp";
			
			session.setAttribute("lsxBean", lsxBean);
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
			request.setCharacterEncoding("UTF-8");
			response.setCharacterEncoding("UTF-8");
			response.setContentType("text/html; charset=UTF-8");
			
			session.setMaxInactiveInterval(30000);
			
			this.out = response.getWriter();
			IErpHangTraLaiNpp lsxBean;
			
			Utility util = new Utility();
			String id = util.antiSQLInspection(request.getParameter("id"));
			if (id == null)
			{
				lsxBean = new ErpHangTraLaiNpp("");
			} else
			{
				
				lsxBean = new ErpHangTraLaiNpp(id);
			}
			
			lsxBean.setUserId(userId);
			
			String ngayyeucau = util.antiSQLInspection(request.getParameter("ngaychuyen"));
			if (ngayyeucau == null || ngayyeucau.trim().length() <= 0)
				ngayyeucau = getDateTime();
			lsxBean.setNgayyeucau(ngayyeucau);
			
			String ghichu = util.antiSQLInspection(request.getParameter("ghichu"));
			if (ghichu == null)
				ghichu = "";
			lsxBean.setGhichu(ghichu);
			
			
			String TraNguyenDon = util.antiSQLInspection(request.getParameter("TraNguyenDon"));
			if (TraNguyenDon == null)
				TraNguyenDon = "0";
			else
				TraNguyenDon ="1";
			lsxBean.setTraNguyenDon(TraNguyenDon);
			
			
			String donhangId = util.antiSQLInspection(request.getParameter("donhangId"));
			if (donhangId == null)
				donhangId = "";
			lsxBean.setDonhangId(donhangId);
			
			
			
			String so = util.antiSQLInspection(request.getParameter("so"));
			if (so == null)
				so = "";
			lsxBean.setSo(so);
			
			String ddkd = util.antiSQLInspection(request.getParameter("ddkdId"));
			if (ddkd == null)
				ddkd = "";
			lsxBean.setddkdId(ddkd);
			session.setAttribute("ddkd", lsxBean.getddkdId());
			
			String khoxuatId = util.antiSQLInspection(request.getParameter("khoxuatId"));
			if (khoxuatId == null)
				khoxuatId = "";
			lsxBean.setKhoXuatId(khoxuatId);
			session.setAttribute("khoxuat", lsxBean.getKhoXuatId());
			
			String nppId = util.antiSQLInspection(request.getParameter("nppId"));
			if (nppId == null)
				nppId = "";
			lsxBean.setNppId(nppId);
			
			String khId = util.antiSQLInspection(request.getParameter("khId"));
			if (khId == null)
				khId = "";
			lsxBean.setKhId(khId);
			
			String dtId = util.antiSQLInspection(request.getParameter("dtId") == null ? "" : request.getParameter("dtId"));
			lsxBean.setDtId(dtId);
			
			String kbhId = util.antiSQLInspection(request.getParameter("kbhId"));
			if (kbhId == null)
				kbhId = "";
			lsxBean.setKbhId(kbhId);
			session.setAttribute("kenhId", lsxBean.getKbhId());
			
			String doituong = util.antiSQLInspection(request.getParameter("doituong") == null ? "" : request.getParameter("doituong"));
			lsxBean.setDoituong(doituong);
			
			String sohoadon = util.antiSQLInspection(request.getParameter("sohoadon") == null ? "" : request.getParameter("sohoadon"));
			lsxBean.setSoHoaDon(sohoadon);
			
			String kyhieu = util.antiSQLInspection(request.getParameter("kyhieu") == null ? "" : request.getParameter("kyhieu"));
			lsxBean.setKyHieu(kyhieu);
			
			String ngayhoadon = util.antiSQLInspection(request.getParameter("ngayhoadon") == null ? "" : request.getParameter("ngayhoadon"));
			lsxBean.setNgayHoaDon(ngayhoadon);
			
			String sohs = util.antiSQLInspection(request.getParameter("so") == null ? "" : request.getParameter("so"));
			lsxBean.setSo(sohs);
			
			String[] spMa = util.antiSQLInspection_Array( request.getParameterValues("spMa"));
			lsxBean.setSpMa(spMa);
			
			String[] spTen = util.antiSQLInspection_Array(request.getParameterValues("spTen"));
			lsxBean.setSpTen(spTen);
			
			String[] dvt = util.antiSQLInspection_Array(request.getParameterValues("donvi"));
			lsxBean.setSpDonvi(dvt);
			
			String[] soluong = util.antiSQLInspection_Array(request.getParameterValues("soluong"));
			lsxBean.setSpSoluong(soluong);
			
			String[] tonkho = util.antiSQLInspection_Array(request.getParameterValues("tonkho"));
			lsxBean.setSpTonkho(tonkho);
			
			String[] dongia = util.antiSQLInspection_Array(request.getParameterValues("dongia"));
			lsxBean.setSpGianhap(dongia);
			
			String[] solo = util.antiSQLInspection_Array(request.getParameterValues("solo"));
			lsxBean.setSpSolo(solo);
			
			String[] ngayhethan =util.antiSQLInspection_Array(request.getParameterValues("ngayhethan"));
			lsxBean.setSpNgayHetHan(ngayhethan);
			
			String[] spGhiChu =util.antiSQLInspection_Array( request.getParameterValues("spGhiChu"));
			lsxBean.setSpGhiChu(spGhiChu);
			
			String[] spVat =util.antiSQLInspection_Array( request.getParameterValues("spVat"));
			lsxBean.setSpVat(spVat);
			
			String action = request.getParameter("action");
			
			if (action.equals("save"))
			{
				if (id == null)
				{
					if (!lsxBean.createNK())
					{
						lsxBean.createRs();
						session.setAttribute("lsxBean", lsxBean);
						String nextJSP = request.getContextPath() + "/pages/Distributor/ErpHangTraLaiNppNew.jsp";
						response.sendRedirect(nextJSP);
					} else
					{
						IErpHangTraLaiNppList obj = new ErpHangTraLaiNppList();
						obj.setUserId(userId);
						obj.init("");
						session.setAttribute("obj", obj);
						session.setAttribute("userId", userId);
						
						response.sendRedirect(request.getContextPath() + "/pages/Distributor/ErpHangTraLaiNpp.jsp");
					}
				} else
				{
					if (!lsxBean.updateNK())
					{
						lsxBean.createRs();
						session.setAttribute("lsxBean", lsxBean);
						String nextJSP = request.getContextPath() + "/pages/Distributor/ErpHangTraLaiNppUpdate.jsp";
						response.sendRedirect(nextJSP);
					} else
					{
						IErpHangTraLaiNppList obj = new ErpHangTraLaiNppList();
						obj.setUserId(userId);
						obj.init("");
						session.setAttribute("obj", obj);
						String nextJSP = request.getContextPath() + "/pages/Distributor/ErpHangTraLaiNpp.jsp";
						response.sendRedirect(nextJSP);
					}
				}
			} else
			{
				lsxBean.createRs();
				
				session.setAttribute("lsxBean", lsxBean);
				
				String nextJSP = "";
				
				nextJSP = request.getContextPath() + "/pages/Distributor/ErpHangTraLaiNppNew.jsp";
				if (id != null)
					nextJSP = request.getContextPath() + "/pages/Distributor/ErpHangTraLaiNppUpdate.jsp";
				
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
	
}
