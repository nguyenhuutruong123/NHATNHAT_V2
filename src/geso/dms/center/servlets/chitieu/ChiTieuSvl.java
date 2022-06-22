package geso.dms.center.servlets.chitieu;

import geso.dms.center.beans.chitieu.IChiTieu;
import geso.dms.center.beans.chitieu.IChiTieuList;
import geso.dms.center.beans.chitieu.imp.ChiTieu;
import geso.dms.center.beans.chitieu.imp.ChiTieuList;
import geso.dms.center.util.Utility;
import geso.dms.distributor.db.sql.dbutils;

import java.io.IOException;

import java.io.PrintWriter;
import java.sql.SQLException;
import javax.servlet.ServletException;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class ChiTieuSvl extends HttpServlet
{
	private static final long serialVersionUID = 1L;
	
	public ChiTieuSvl()
	{
		super();
		
	}
	PrintWriter out;
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");
		this.out = response.getWriter();
		
		HttpSession session = request.getSession();
		
		Utility util = new Utility();
		out = response.getWriter();
		
		String querystring = request.getQueryString();
		String userId = util.getUserId(querystring);
		out.println(userId);
		
		if (userId.length() == 0)
			userId = util.antiSQLInspection(request.getParameter("userId"));
		
		IChiTieuList obj = new ChiTieuList();
		obj.setUserId(userId);
		
		String loaichitieu = request.getParameter("loaichitieu");
		if (loaichitieu == null)
			loaichitieu = "0";
		obj.setType(loaichitieu);
		
		String view = request.getParameter("view");
		if (view == null)
			view = "";
		obj.setView(view);
		
		String action = util.getAction(querystring);
		String ctskuId = util.getId(querystring);
		
		// System.out.println("___Action: " + action + " -- Id la: " + ctskuId);
		if (action.trim().equals("duyet"))
		{
			dbutils db = new dbutils();
			db.update("update ChiTieu set trangthai = '1' where pk_seq = '" + ctskuId + "'");
			db.shutDown();
		}
		if (action.trim().equals("delete"))
		{
			XoaChiTieu(ctskuId);
		}
		obj.init("");
		session.setAttribute("obj", obj);
		String nextJSP = request.getContextPath() + "/pages/Center/ChiTieu.jsp";
		response.sendRedirect(nextJSP);
	}
	
	private void XoaChiTieu(String ctskuId)
	{
		dbutils db = new dbutils();
		
		try
		{
			db.getConnection().setAutoCommit(false);
			
			if (!db.update("delete ChiTieu_SanPham where ChiTieu_fk = '" + ctskuId + "'"))
			{
				db.getConnection().rollback();
				return;
			}
			
			if (!db.update("delete ChiTieu_DoiTuong where ChiTieu_fk = '" + ctskuId + "'"))
			{
				db.getConnection().rollback();
				return;
			}
			
			if (!db.update("delete ChiTieu_TieuChi where ChiTieu_fk = '" + ctskuId + "'"))
			{
				db.getConnection().rollback();
				return;
			}
			if (!db.update("delete ChiTieu where pk_seq = '" + ctskuId + "'"))
			{
				db.getConnection().rollback();
				return;
			}
			db.getConnection().commit();
			db.shutDown();
		} catch (Exception e)
		{
			try
			{
				db.getConnection().rollback();
			} catch (SQLException e1)
			{
			}
		}
		
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");
		this.out = response.getWriter();
		
		HttpSession session = request.getSession();
		
		out = response.getWriter();
		Utility util = new Utility();
		
		String userId = util.antiSQLInspection(request.getParameter("userId"));
		IChiTieuList obj;
		
		String action = request.getParameter("action");
		if (action == null)
		{
			action = "";
		}
		
		String loaichitieu = request.getParameter("loaichitieu");
		if (loaichitieu == null)
			loaichitieu = "0";
		
		String view = request.getParameter("view");
		if (view == null)
			view = "";
	
		if (action.equals("new"))
		{
			IChiTieu tctsku = new ChiTieu();
			tctsku.setLoaichitieu(loaichitieu);
			tctsku.setView(view);
			tctsku.setUserId(userId);
			tctsku.createRs();
			session.setAttribute("tctskuBean", tctsku);
			session.setAttribute("userId", userId);
			response.sendRedirect(request.getContextPath() + "/pages/Center/ChiTieuNew.jsp");
		} else
		{
			obj = new ChiTieuList();
			obj.setUserId(userId);
			obj.setType(loaichitieu);
			obj.setView(view);
			String search = getSearchQuery(request, obj);
			obj.init(search);
			session.setAttribute("obj", obj);
			session.setAttribute("userId", userId);
			response.sendRedirect(request.getContextPath() + "/pages/Center/ChiTieu.jsp");
		}
	}
	
	private String getSearchQuery(HttpServletRequest request, IChiTieuList obj)
	{
		Utility util = new Utility();
		String thang = request.getParameter("thang");
		if (thang == null)
			thang = "";
		obj.setThang(thang);
		
		String nam = request.getParameter("nam");
		if (nam == null)
			nam = "";
		obj.setNam(nam);
		
		String nppId="";
		if(obj.getView().length()>0)
		{
			 nppId = util.antiSQLInspection(request.getParameter("nppId"));
			if (nppId == null)
					nppId = "";
				obj.setNppIds(nppId);
		}else
		{
			nppId=util.getIdNhapp(obj.getUserId());
			obj.setNppIds(nppId);
		}
		
		String sql = 
			"select a.ma, a.pk_seq, isnull(a.thang,a.Quy) as Thang, a.nam, a.ten , a.trangthai, a.ngaytao, a.ngaysua, b.ten as nguoitao, c.ten as nguoisua  " +
			"from ChiTieu a inner join NHANVIEN b on a.NGUOITAO = b.pk_seq inner join NHANVIEN c on a.NGUOISUA = c.pk_seq " + 
			" where 1=1 and a.LoaiChiTieu='" + obj .getType() + "'   ";
		if(obj.getView().length()>0)
		{
			sql+= " and tructhuoc_fk=1";
		}
		else 
		{
			sql+= " and tructhuoc_fk='"+nppId+"'";
		}
		
		if (thang.length() > 0)
			sql += " and a.thang = '" + thang + "' ";
		if (nam.length() > 0)
			sql += " and a.nam = '" + nam + "' ";
		
		sql += "order by nam desc, thang desc";
		
		return sql;
	}
}
