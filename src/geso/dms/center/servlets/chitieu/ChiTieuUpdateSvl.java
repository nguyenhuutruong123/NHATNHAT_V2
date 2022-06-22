package geso.dms.center.servlets.chitieu;

import geso.dms.center.beans.chitieu.IChiTieu;

import geso.dms.center.beans.chitieu.IChiTieuList;
import geso.dms.center.beans.chitieu.imp.ChiTieu;
import geso.dms.center.beans.chitieu.imp.ChiTieuList;
import geso.dms.center.util.Utility;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Hashtable;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class ChiTieuUpdateSvl extends HttpServlet
{
	private static final long serialVersionUID = 1L;
	PrintWriter out;
	public ChiTieuUpdateSvl()
	{
		super();
		
	}
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		HttpSession session = request.getSession();
		IChiTieu tctskuBean;
		
		this.out = response.getWriter();
		Utility util = new Utility();
		
		String querystring = request.getQueryString();
		String userId = util.getUserId(querystring);
		
		out.println(userId);
		
		if (userId.length() == 0)
			userId = util.antiSQLInspection(request.getParameter("userId"));
		
		String id = util.getId(querystring);
		
		tctskuBean = new ChiTieu(id);
		tctskuBean.setId(id);
		tctskuBean.setUserId(userId);
		
		tctskuBean.init();
		session.setAttribute("tctskuBean", tctskuBean);
		
		String nextJSP = request.getContextPath() + "/pages/Center/ChiTieuUpdate.jsp";
		if (querystring.indexOf("display") >= 0)
			nextJSP = request.getContextPath() + "/pages/Center/ChiTieuDisplay.jsp";
		
		response.sendRedirect(nextJSP);
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		IChiTieu tctskuBean;
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");
		HttpSession session = request.getSession();
		this.out = response.getWriter();
		Utility util = new Utility();
		
		String id = util.antiSQLInspection(request.getParameter("id"));
		if (id == null)
		{
			tctskuBean = new ChiTieu("");
		} else
		{
			tctskuBean = new ChiTieu(id);
		}
		
		String userId = util.antiSQLInspection(request.getParameter("userId"));
		tctskuBean.setUserId(userId);
		
		String diengiai = util.antiSQLInspection(request.getParameter("diengiai"));
		if (diengiai == null)
			diengiai = "";
		tctskuBean.setDiengiai(diengiai);
		
		String scheme = util.antiSQLInspection(request.getParameter("scheme"));
		if (scheme == null)
			scheme = "";
		tctskuBean.setScheme(scheme);
		
		String thang = util.antiSQLInspection(request.getParameter("thang"));
		if (thang == null)
			thang = "";
		tctskuBean.setThang(thang);
		
		String nam = util.antiSQLInspection(request.getParameter("nam"));
		if (nam == null)
			nam = "";
		tctskuBean.setNam(nam);
		
		String quy = util.antiSQLInspection(request.getParameter("quy") == null ? "" : request.getParameter("quy"));
		tctskuBean.setQuy(quy);
		
		String apdung = util.antiSQLInspection(request.getParameter("apdung") == null ? "" : request.getParameter("apdung"));
		tctskuBean.setApdung(apdung);
		
		String loaichitieu = util.antiSQLInspection(request.getParameter("loaichitieu") == null ? "0" : request.getParameter("loaichitieu"));
		tctskuBean.setLoaichitieu(loaichitieu);
		
		String view = util.antiSQLInspection(request.getParameter("view") == null ? "" : request.getParameter("view"));
		tctskuBean.setView(view);
		
		String tructhuocId = util.antiSQLInspection(request.getParameter("tructhuocId") == null ? "" : request.getParameter("tructhuocId"));
		tctskuBean.setTructhuocId(tructhuocId);
		
		String[] spIds = request.getParameterValues("spIds");
		if (spIds != null)
		{
			String spId = "";
			for (int i = 0; i < spIds.length; i++)
			{
				spId += spIds[i] + ",";
			}
			
			if (spId.trim().length() > 0)
			{
				spId = spId.substring(0, spId.length() - 1);
				tctskuBean.setSpIds(spId);
			}
		}
		
		String[] nppIds = request.getParameterValues("nppIds");
		String[] npp_chitieu = request.getParameterValues("npp_chitieu");
		String[] npp_donvi_chitieu = request.getParameterValues("npp_donvi_chitieu");
		if (nppIds != null)
		{
			
			Hashtable<String, String> htb_npp_chitieu = new Hashtable<String, String>();
			Hashtable<String, String> htb_npp_donvi_chitieu = new Hashtable<String, String>();
			
			String nppId = "";
			for (int i = 0; i < nppIds.length; i++)
			{
				htb_npp_chitieu.put(nppIds[i], npp_chitieu[i].replaceAll(",", ""));
				htb_npp_donvi_chitieu.put(nppIds[i], npp_donvi_chitieu[i]);
				nppId += nppIds[i] + ",";
			}
			tctskuBean.setNpp_chitieu(htb_npp_chitieu);
			tctskuBean.setNpp_donvi_chitieu(htb_npp_donvi_chitieu);
			
			if (nppId.trim().length() > 0)
			{
				nppId = nppId.substring(0, nppId.length() - 1);
				tctskuBean.setNppIds(nppId);
			}
		}
		
		String[] tdvIds = request.getParameterValues("tdvIds");
		String[] tdv_chitieu = request.getParameterValues("tdv_chitieu");
		String[] tdv_donvi_chitieu = request.getParameterValues("tdv_donvi_chitieu");
		if (tdvIds != null)
		{
			
			Hashtable<String, String> htb_tdv_chitieu = new Hashtable<String, String>();
			Hashtable<String, String> htb_tdv_donvi_chitieu = new Hashtable<String, String>();
			
			String tdvId = "";
			for (int i = 0; i < tdvIds.length; i++)
			{
				htb_tdv_chitieu.put(tdvIds[i], tdv_chitieu[i].replaceAll(",", ""));
				htb_tdv_donvi_chitieu.put(tdvIds[i], tdv_donvi_chitieu[i]);
				tdvId += tdvIds[i] + ",";
			}
			tctskuBean.setTdv_chitieu(htb_tdv_chitieu);
			tctskuBean.setTdv_donvi_chitieu(htb_tdv_donvi_chitieu);
			if (tdvId.trim().length() > 0)
			{
				tdvId = tdvId.substring(0, tdvId.length() - 1);
				tctskuBean.setTdvIds(tdvId);
			}
		}
		
		String[] tumuc = request.getParameterValues("tumuc");
		tctskuBean.setTumuc(tumuc);
		
		String[] denmuc = request.getParameterValues("denmuc");
		tctskuBean.setDenmuc(denmuc);
		
		String[] donvi_tinh_ds = request.getParameterValues("donvi_tinh_ds");
		tctskuBean.setDonvi_tinh_ds(donvi_tinh_ds);
		
		String[] thuong = request.getParameterValues("thuong");
		tctskuBean.setThuong(thuong);
		
		String[] donvi_tinh_thuong = request.getParameterValues("donvi_tinh_thuong");
		tctskuBean.setDonvi_tinh_thuong(donvi_tinh_thuong);
		
		// VUNG
		String vungId = request.getParameter("vungId");
		if (vungId == null)
			vungId = "";
		tctskuBean.setVungIds(vungId);
		
		String kvId = request.getParameter("khuvucId");
		if (kvId == null)
			kvId = "";
		tctskuBean.setKhuvucIds(kvId);
		
		String action = request.getParameter("action");
		System.out.println("Action la: " + action);
		
		if (action.equals("save"))
		{
			if (id == null)
			{
				if (!tctskuBean.save())
				{
					tctskuBean.setUserId(userId);
					tctskuBean.createRs();
					session.setAttribute("userId", userId);
					session.setAttribute("tctskuBean", tctskuBean);
					String nextJSP = request.getContextPath() + "/pages/Center/ChiTieuNew.jsp";
					response.sendRedirect(nextJSP);
				} else
				{
					IChiTieuList obj = new ChiTieuList();
					obj.setUserId(userId);
					obj.setType(loaichitieu);
					obj.setView(view);
					obj.init("");
					session.setAttribute("obj", obj);
					String nextJSP = request.getContextPath() + "/pages/Center/ChiTieu.jsp";
					response.sendRedirect(nextJSP);
				}
			} else
			{
				if (!(tctskuBean.edit()))
				{
					tctskuBean.setUserId(userId);
					tctskuBean.createRs();
					session.setAttribute("userId", userId);
					session.setAttribute("tctskuBean", tctskuBean);
					
					String nextJSP = request.getContextPath() + "/pages/Center/ChiTieuUpdate.jsp";
					response.sendRedirect(nextJSP);
				} else
				{
					IChiTieuList obj = new ChiTieuList();
					obj.setUserId(userId);
					obj.setType(loaichitieu);
					obj.setView(view);
					obj.init("");
					session.setAttribute("obj", obj);
					String nextJSP = request.getContextPath() + "/pages/Center/ChiTieu.jsp";
					response.sendRedirect(nextJSP);
				}
			}
		} else
		{
			tctskuBean.createRs();
			session.setAttribute("userId", userId);
			session.setAttribute("tctskuBean", tctskuBean);
			
			String nextJSP;
			if (id == null)
			{
				nextJSP = request.getContextPath() + "/pages/Center/ChiTieuNew.jsp";
			} else
			{
				nextJSP = request.getContextPath() + "/pages/Center/ChiTieuUpdate.jsp";
			}
			response.sendRedirect(nextJSP);
		}
	}
	
	public String getDateTime()
	{
		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		Date date = new Date();
		return dateFormat.format(date);
	}
	
}
