package geso.dms.distributor.servlets.donhangctv;

import geso.dms.distributor.beans.donhangctv.IDonhangctv;
import geso.dms.distributor.beans.donhangctv.IDonhangctvList;
import geso.dms.distributor.beans.donhangctv.imp.Donhangctv;
import geso.dms.distributor.beans.donhangctv.imp.DonhangctvList;
import geso.dms.distributor.util.Utility;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.URLDecoder;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class DonhangctvUpdateSvl extends HttpServlet
{
	private static final long serialVersionUID = 1L;
	PrintWriter out;
	
	public DonhangctvUpdateSvl()
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
			
			String querystring = URLDecoder.decode(request.getQueryString(), "UTF-8");
			userId = util.getUserId(querystring);
			
			if (userId.length() == 0)
				userId = util.antiSQLInspection(request.getParameter("userId"));
			
			String id = util.getId(querystring);
			IDonhangctv lsxBean = new Donhangctv(id);
						
			lsxBean.setUserId(userId);
			
			lsxBean.setLoainhanvien(session.getAttribute("loainhanvien"));
			lsxBean.setDoituongId(session.getAttribute("doituongId"));
			
			String duyetss = request.getParameter("duyet");
		    if(duyetss == null)
		    	duyetss = "";
		    lsxBean.setDuyetSS(duyetss);
			String nextJSP = "";
			
			if (!querystring.contains("display"))
			{
				lsxBean.init();
				nextJSP = request.getContextPath() + "/pages/Distributor/DonHangCTVUpdate.jsp";
			}
			else
			{
				lsxBean.initDisplay();
				nextJSP = request.getContextPath() + "/pages/Distributor/DonHangCTVDisplay.jsp";
			}
			
			session.setAttribute("lsxBean", lsxBean);
			session.setAttribute("nppId",lsxBean.getNppId() );
			
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
			IDonhangctv lsxBean;
			
			Utility util = new Utility();
			String id = util.antiSQLInspection(request.getParameter("id"));
			if (id == null)
			{
				lsxBean = new Donhangctv("");
			} else
			{
				
				lsxBean = new Donhangctv(id);
			}
			
			lsxBean.setUserId(userId);
			
			lsxBean.setLoainhanvien(session.getAttribute("loainhanvien"));
			lsxBean.setDoituongId(session.getAttribute("doituongId"));
			
			String loaidonhang = request.getParameter("loaidonhang");
		    if(loaidonhang == null)
		    	loaidonhang = "";
		    lsxBean.setLoaidonhang(loaidonhang);
		    
			String ngayyeucau = util.antiSQLInspection(request.getParameter("ngaychuyen"));
			if (ngayyeucau == null || ngayyeucau.trim().length() <= 0)
				ngayyeucau = getDateTime();
			lsxBean.setNgayyeucau(ngayyeucau);
			
			String ghichu = util.antiSQLInspection(request.getParameter("ghichu"));
			if (ghichu == null)
				ghichu = "";
			lsxBean.setGhichu(ghichu);
			
			String sochungtu = util.antiSQLInspection( request.getParameter("sochungtu")==null? "":request.getParameter("sochungtu") );
			lsxBean.setSoChungTu(sochungtu);

			String nppId = util.antiSQLInspection(request.getParameter("nppId"));
			if (nppId == null)
				nppId = "";
			lsxBean.setNppId(nppId);

			String khId = util.antiSQLInspection(request.getParameter("khId"));
			if (khId == null)
				khId = "";
			lsxBean.setKhId(khId);
			
			String tinhdoiid = util.antiSQLInspection(request.getParameter("tinhdoiid"));
			if (tinhdoiid == null)
				tinhdoiid = "";
			lsxBean.setTinhdoiId(tinhdoiid);
			
			String ddkdId = util.antiSQLInspection(request.getParameter("ddkdId"));
			if (ddkdId == null)
				ddkdId = "";
			lsxBean.setDdkdId(ddkdId);
			
			session.setAttribute("nppId", request.getParameter("nppId"));
			
			String[] spCtvId = request.getParameterValues("spCtvId");
			lsxBean.setSpCtvId(spCtvId);
			
			String[] spId = request.getParameterValues("spId");
			lsxBean.setSpId(spId);
			
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
						String nextJSP = request.getContextPath() + "/pages/Distributor/DonHangCTVNew.jsp";
						response.sendRedirect(nextJSP);
					} 
					else
					{
						IDonhangctvList obj = new DonhangctvList();
						obj.setUserId(userId);
						obj.setLoaidonhang(loaidonhang);
						lsxBean.DBclose();
						obj.setLoainhanvien(session.getAttribute("loainhanvien"));
					    obj.setDoituongId(session.getAttribute("doituongId"));
					    
						obj.init("");
						session.setAttribute("obj", obj);
						session.setAttribute("userId", userId);
						
						response.sendRedirect(request.getContextPath() + "/pages/Distributor/DonHangCTV.jsp");
					}
				} 
				else
				{
					if (!lsxBean.updateNK())
					{
						lsxBean.createRs();
						session.setAttribute("lsxBean", lsxBean);
						String nextJSP = request.getContextPath() + "/pages/Distributor/DonHangCTVUpdate.jsp";
						response.sendRedirect(nextJSP);
					} 
					else
					{
						IDonhangctvList obj = new DonhangctvList();
						obj.setUserId(userId);
						obj.setLoaidonhang(loaidonhang);
						lsxBean.DBclose();
						obj.setLoainhanvien(session.getAttribute("loainhanvien"));
					    obj.setDoituongId(session.getAttribute("doituongId"));
						
						obj.init("");
						session.setAttribute("obj", obj);
						String nextJSP = request.getContextPath() + "/pages/Distributor/DonHangCTV.jsp";
						response.sendRedirect(nextJSP);
					}
				}
			} 
			else
			{
				lsxBean.createRs();
				
				session.setAttribute("lsxBean", lsxBean);
				
				String nextJSP = "";
				
				nextJSP = request.getContextPath() + "/pages/Distributor/DonHangCTVNew.jsp";
				if (id != null)
					nextJSP = request.getContextPath() + "/pages/Distributor/DonHangCTVUpdate.jsp";
				
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
