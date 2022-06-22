package geso.dms.center.servlets.hangtralai;

import geso.dms.center.util.Utility;
import geso.dms.center.beans.hangtralai.IErpHangTraLaiNpp;
import geso.dms.center.beans.hangtralai.IErpHangTraLaiNppList;
import geso.dms.center.beans.hangtralai.imp.ErpHangTraLaiNpp;
import geso.dms.center.beans.hangtralai.imp.ErpHangTraLaiNppList;

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

public class ErpHangTraLaiTTUpdateSvl extends HttpServlet
{
	private static final long serialVersionUID = 1L;
	
	PrintWriter out;
	public ErpHangTraLaiTTUpdateSvl()
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
				nextJSP = request.getContextPath() + "/pages/Center/ErpHangTraLaiNppUpdate.jsp";
			else
				nextJSP = request.getContextPath() + "/pages/Center/ErpHangTraLaiNppDisplay.jsp";
			
			session.setAttribute("lsxBean", lsxBean);
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
			
			String so = util.antiSQLInspection(request.getParameter("so"));
			if (so == null)
				so = "";
			lsxBean.setSo(so);
			
			String khoxuatId = util.antiSQLInspection(request.getParameter("khoxuatId"));
			if (khoxuatId == null)
				khoxuatId = "";
			lsxBean.setKhoXuatId(khoxuatId);
			session.setAttribute("khoXuatId", lsxBean.getKhoXuatId());
			
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
			
			String[] spMa = request.getParameterValues("spMa");
			lsxBean.setSpMa(spMa);
			
			String[] spTen = request.getParameterValues("spTen");
			lsxBean.setSpTen(spTen);
			
			String[] dvt = request.getParameterValues("donvi");
			lsxBean.setSpDonvi(dvt);
			
			String[] soluong = request.getParameterValues("soluong");
			lsxBean.setSpSoluong(soluong);
			
			String[] tonkho = request.getParameterValues("tonkho");
			lsxBean.setSpTonkho(tonkho);
			
			String[] dongia = request.getParameterValues("dongia");
			lsxBean.setSpGianhap(dongia);
			
			String[] solo = request.getParameterValues("solo");
			lsxBean.setSpSolo(solo);
			
			String[] ngayhethan = request.getParameterValues("ngayhethan");
			lsxBean.setSpNgayHetHan(ngayhethan);
			
			String[] spGhiChu = request.getParameterValues("spGhiChu");
			lsxBean.setSpGhiChu(spGhiChu);
			
			String[] spVat = request.getParameterValues("spVat");
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
						String nextJSP = request.getContextPath() + "/pages/Center/ErpHangTraLaiNppNew.jsp";
						response.sendRedirect(nextJSP);
					} else
					{
						IErpHangTraLaiNppList obj = new ErpHangTraLaiNppList();
						obj.setUserId(userId);
						obj.init("");
						session.setAttribute("obj", obj);
						session.setAttribute("userId", userId);
						
						response.sendRedirect(request.getContextPath() + "/pages/Center/ErpHangTraLaiNpp.jsp");
					}
				} else
				{
					if (!lsxBean.updateNK())
					{
						lsxBean.createRs();
						session.setAttribute("lsxBean", lsxBean);
						String nextJSP = request.getContextPath() + "/pages/Center/ErpHangTraLaiNppUpdate.jsp";
						response.sendRedirect(nextJSP);
					} else
					{
						IErpHangTraLaiNppList obj = new ErpHangTraLaiNppList();
						obj.setUserId(userId);
						obj.init("");
						session.setAttribute("obj", obj);
						String nextJSP = request.getContextPath() + "/pages/center/ErpHangTraLaiNpp.jsp";
						response.sendRedirect(nextJSP);
					}
				}
			} else
			{
				lsxBean.createRs();
				
				session.setAttribute("lsxBean", lsxBean);
				
				String nextJSP = "";
				
				nextJSP = request.getContextPath() + "/pages/Center/ErpHangTraLaiNppNew.jsp";
				if (id != null)
					nextJSP = request.getContextPath() + "/pages/Center/ErpHangTraLaiNppUpdate.jsp";
				
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
