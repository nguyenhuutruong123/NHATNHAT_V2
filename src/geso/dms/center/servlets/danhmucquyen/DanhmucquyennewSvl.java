package geso.dms.center.servlets.danhmucquyen;

import geso.dms.center.beans.danhmucquyen.IDanhmucquyen;
import geso.dms.center.beans.danhmucquyen.IDanhmucquyenList;
import geso.dms.center.beans.danhmucquyen.imp.Danhmucquyen;
import geso.dms.center.beans.danhmucquyen.imp.Danhmucquyenlist;
import geso.dms.center.util.Utility;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Hashtable;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class DanhmucquyennewSvl extends HttpServlet
{
	private static final long serialVersionUID = 1L;
	
	PrintWriter out;
	public DanhmucquyennewSvl()
	{
		super();
	}
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		IDanhmucquyen obj;
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");
		this.out = response.getWriter();
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
		Utility util = new Utility();
		out = response.getWriter();
		
		String querystring = request.getQueryString();
		
		String id = util.getUserId(querystring);
		
		System.out.println("[ID]"+id);
		
		obj = new Danhmucquyen(id);
		session.setAttribute("obj", obj);
		String nextJSP = request.getContextPath() + "/pages/Center/DanhMucQuyenNew.jsp";
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
		Utility util = new Utility();
		
		IDanhmucquyen obj;
		obj = new Danhmucquyen("");
		
		String id = util.antiSQLInspection(request.getParameter("id")==null?"":request.getParameter("id"));
		obj.setId(id);
		
		String userId = util.antiSQLInspection(request.getParameter("userId"));obj.setUserId(userId);
		
		
		System.out.println();
		String Ten = util.antiSQLInspection(request.getParameter("ten"));
		if (Ten == null)
			Ten = "";
		obj.setTen(Ten);
		String DienGiai = util.antiSQLInspection(request.getParameter("diengiai"));
		if (DienGiai == null)
			DienGiai = "";
		obj.setDiengiai(DienGiai);
		
		String TrangThai = util.antiSQLInspection(request.getParameter("trangthai"));
		if (TrangThai == null)
			TrangThai = "0";
		obj.setTrangthai(TrangThai);
		String loaiMenu = util.antiSQLInspection(request.getParameter("loaiMenu"));
		if (loaiMenu == null)
			loaiMenu = "0";
		obj.setLoaiMenu(loaiMenu);
		
		String action = request.getParameter("action");
	    if (action == null){
	    	action = "";
	    }
	    //Thay doi VIEW DANH SACH UNG DUNG
	    if(action.equals("loaiMenu"))
	    {
	    	System.out.println("Change menu");
	    	session.setAttribute("obj", obj);
			String nextJSP = request.getContextPath() + "/pages/Center/DanhMucQuyenNew.jsp";
			response.sendRedirect(nextJSP);			
	    }
	    else
	    {

	    String[] ungdungId = request.getParameterValues("ungdungId");
			String[] cbHienThi = request.getParameterValues("cbHienThi");
			String[] cbThem =   request.getParameterValues("cbThem");
			String[] cbXoa = request.getParameterValues("cbXoa");
			String[] cbSua = request.getParameterValues("cbSua");
			String[] cbXem = request.getParameterValues("cbXem");
			String[] cbChot =request.getParameterValues("cbChot");
			String[] cbHuychot = request.getParameterValues("cbHuyChot");
			
			obj.setUngdungIds(this.Doisangchuoi(ungdungId));
			obj.setCbHienThi(this.Doisangchuoi(cbHienThi));
			obj.setCbThem(this.Doisangchuoi(cbThem));
			obj.setCbXoa(this.Doisangchuoi(cbXoa));
			obj.setCbSua(this.Doisangchuoi(cbSua));
			obj.setCbXem(this.Doisangchuoi(cbXem));
			obj.setCbChot(this.Doisangchuoi(cbChot));			
			obj.setCbHuyChot(this.Doisangchuoi(cbHuychot));
			
			if (Ten.length() <= 0 || DienGiai.length() <= 0)
			{
				obj.setMsg("Ban phai nhap du thong tin");
				
				session.setAttribute("obj", obj);
				String nextJSP = request.getContextPath() + "/pages/Center/DanhMucQuyenNew.jsp";
				response.sendRedirect(nextJSP);
			} else if (id.length() > 0)
			{
				if (!obj.update(request))
				{
					
					session.setAttribute("obj", obj);
					String nextJSP = request.getContextPath() + "/pages/Center/DanhMucQuyenNew.jsp";
					response.sendRedirect(nextJSP);
				} else
				{
					
					IDanhmucquyenList objlist = new Danhmucquyenlist();
					objlist.setUserId(userId);
					session.setAttribute("obj", objlist);
					String nextJSP = request.getContextPath() + "/pages/Center/DanhMucQuyen.jsp";
					response.sendRedirect(nextJSP);
				}
			} else
			{
				if (!obj.save(request))
				{
					
					session.setAttribute("obj", obj);
					String nextJSP = request.getContextPath() + "/pages/Center/DanhMucQuyenNew.jsp";
					response.sendRedirect(nextJSP);
				} else
				{
					
					IDanhmucquyenList objlist = new Danhmucquyenlist();
					obj.setUserId(userId);
					session.setAttribute("obj", objlist);
					String nextJSP = request.getContextPath() + "/pages/Center/DanhMucQuyen.jsp";
					response.sendRedirect(nextJSP);
				}
			}
	    }
	}
	
	private String Doisangchuoi(String[] checknpp)
	{
		// TODO Auto-generated method stub
		String str = "";
		if (checknpp != null)
		{
			for (int i = 0; i < checknpp.length; i++)
			{
				if (i == 0)
				{
					str =  checknpp[i];
				} else
				{
					str = str + "," +checknpp[i];
				}
			}
		}
		return str;

	}
	
}
