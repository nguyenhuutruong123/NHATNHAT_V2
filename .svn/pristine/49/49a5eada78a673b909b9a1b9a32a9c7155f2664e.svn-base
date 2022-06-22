package geso.dms.distributor.servlets.Phanboquatang;


import geso.dms.center.db.sql.dbutils;
import geso.dms.center.util.Utility;
import geso.dms.distributor.beans.Phanboquatang.IPhanboquatang;
import geso.dms.distributor.beans.Phanboquatang.IPhanboquatangList;
import geso.dms.distributor.beans.Phanboquatang.imp.Phanboquatang;
import geso.dms.distributor.beans.Phanboquatang.imp.PhanboquatangList;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.ResultSet;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class PhanboquatangSvl extends javax.servlet.http.HttpServlet implements javax.servlet.Servlet
{
	static final long serialVersionUID = 1L;

	public PhanboquatangSvl()
	{
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");
		PrintWriter out = response.getWriter();

		HttpSession session = request.getSession();

		Utility util = new Utility();
		out = response.getWriter();

		String querystring = request.getQueryString();
		String userId = util.getUserId(querystring);
		out.println(userId);

		if (userId.length() == 0)
			userId = util.antiSQLInspection(request.getParameter("userId"));

		String action = util.getAction(querystring);
		out.println(action);

		String bgId = util.getId(querystring);

		String msg = "";
		if (action.equals("delete"))
		{
			msg =Delete(bgId);
			
		}else
		if (action.equals("chot"))
		{
			msg = Chot(bgId,userId);
		}
		IPhanboquatangList obj;

		obj = new PhanboquatangList();
		obj.setUserId(userId);
		obj.init("");
		
		obj.setMsg(msg);
		session.setAttribute("obj", obj);

		String nextJSP = request.getContextPath() + "/pages/Distributor/Phanboquatang.jsp";
		response.sendRedirect(nextJSP);

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");
		PrintWriter out = response.getWriter();
		IPhanboquatangList obj;
		obj = new PhanboquatangList();
		HttpSession session = request.getSession();
		Utility util = new Utility();
		String userId = util.antiSQLInspection(request.getParameter("userId"));
		obj.setUserId(userId);
		
		
		
		
		String action = request.getParameter("action");
		if (action == null)
		{
			action = "";
		}
		out.println(action);
		
		if (action.equals("new"))
		{

			IPhanboquatang bgBean = (IPhanboquatang) new Phanboquatang();
			bgBean.setUserId(userId);
			bgBean.createRS();
			session.setAttribute("bgblcBean", bgBean);

			String nextJSP = request.getContextPath() + "/pages/Distributor/PhanboquatangNew.jsp";
			response.sendRedirect(nextJSP);

		}
		if (action.equals("search"))
		{
			String search = getSearchQuery(request, obj);
			obj.setUserId(userId);
			obj.init(search);

			obj.setUserId(userId);

			session.setAttribute("obj", obj);

			response.sendRedirect(request.getContextPath() + "/pages/Distributor/Phanboquatang.jsp");

		}
	}

	private String getSearchQuery(HttpServletRequest request, IPhanboquatangList obj)
	{

		Utility util = new Utility();
		String querystring = request.getQueryString();
		String userId = util.getUserId(querystring);
		if (userId.length() == 0)
			userId = util.antiSQLInspection(request.getParameter("userId"));

		
		

		String nppid=util.getIdNhapp(userId);
		String tungay = util.antiSQLInspection(request.getParameter("tungay"));
		if ( tungay == null)
			tungay = "";
		obj.setTungay(tungay);;
		
		String denngay = util.antiSQLInspection(request.getParameter("denngay"));
		if ( denngay == null)
			denngay = "";
		obj.setDenngay(denngay);

		String trangthai = util.antiSQLInspection(request.getParameter("trangthai"));
		if ( trangthai == null)
			trangthai = "";
		if(trangthai.equals("2"))
			trangthai="";
		obj.setTrangthai(trangthai);
		
		String ten = util.antiSQLInspection(request.getParameter("ten"));
		if ( ten == null)
			ten = "";
		obj.setTen(ten);
		
		String query =" select pk_seq,trangthai,tungay,denngay,Ma from phanbo_quatang where npp_fk= "+nppid;
		
		if(tungay.trim().length()>0)
			query+=" and tungay >='"+tungay+"' ";
		if(denngay.trim().length()>0)
			query+=" and denngay <='"+denngay+"' ";
		if(trangthai.trim().length()>0)
			query+=" and trangthai ='"+trangthai+"' ";
		if(ten.trim().length()>0)
			query+=" and  (diengiai like N'%"+ten+"%' or Ma like N'%"+ten+"%' )   ";

		System.out.println("Query Search "+query);
		return query;
	
	}

	private String Chot(String id,String userId)
	{
		try {
			dbutils db=new dbutils();
			db.getConnection().setAutoCommit(false);
			String quer="update  phanbo_quatang set trangthai=1 where pk_seq="+id;
			if(db.updateReturnInt(quer)<=0)
			{
				db.getConnection().rollback();
				db.getConnection().setAutoCommit(true);
				return "Lỗi trong quá trình chốt";
			}
			
			db.getConnection().commit();
			db.getConnection().setAutoCommit(true);
			return "";
			
			
		} catch(Exception e)
		{
			
		}
		return "";
	}
	
	private String Delete(String id)
	{
		try {
			dbutils db=new dbutils();
			db.getConnection().setAutoCommit(false);
			String quer="delete from phanbo_quatang_ddkd where phanbo_fk="+id;
			if(!db.update(quer))
			{
				db.getConnection().rollback();
				db.getConnection().setAutoCommit(true);
				return "Lỗi trong quá trình xóa ";
			}
			
			 quer="delete from phanbo_quatang where pk_seq="+id;
			if(db.updateReturnInt(quer)<=0)
			{
				db.getConnection().rollback();
				db.getConnection().setAutoCommit(true);
				return "Lỗi trong quá trình xóa ";
			}
			
			db.getConnection().commit();
			db.getConnection().setAutoCommit(true);
			return "";
			
			
		} catch(Exception e)
		{
			
		}
		return "";
	}

}
