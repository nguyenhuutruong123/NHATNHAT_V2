package geso.dms.distributor.servlets.ctchietkhau;

import geso.dms.distributor.beans.ctchietkhau.*;
import geso.dms.distributor.beans.ctchietkhau.imp.*;
import geso.dms.center.db.sql.dbutils;
import geso.dms.center.util.Utility;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.ResultSet;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class CtChietKhauSvl extends javax.servlet.http.HttpServlet implements javax.servlet.Servlet
{
	static final long serialVersionUID = 1L;

	public CtChietKhauSvl()
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
		ICtChietKhauList obj;

		obj = new CtChietKhauList(userId);
		obj.setMsg(msg);
		session.setAttribute("obj", obj);

		String nextJSP = request.getContextPath() + "/pages/Distributor/CtChietKhau.jsp";
		response.sendRedirect(nextJSP);

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");
		PrintWriter out = response.getWriter();
		ICtChietKhauList obj;
		Utility util = new Utility();
		String userId = util.antiSQLInspection(request.getParameter("userId"));
		obj = new CtChietKhauList(userId);
		
		
		
		HttpSession session = request.getSession();
	
		
		
		
		String action = request.getParameter("action");
		if (action == null)
		{
			action = "";
		}
		out.println(action);

		if (action.equals("new"))
		{

			ICtChietKhau bgBean = (ICtChietKhau) new CtChietKhau();
			bgBean.setUserId(userId);
			bgBean.createRS();
			session.setAttribute("bgblcBean", bgBean);

			String nextJSP = request.getContextPath() + "/pages/Distributor/CtChietKhauNew.jsp";
			response.sendRedirect(nextJSP);

		}
		if (action.equals("search"))
		{
			String search = getSearchQuery(request, obj);

			obj.init(search);

			

			session.setAttribute("obj", obj);

			response.sendRedirect(request.getContextPath() + "/pages/Distributor/CtChietKhau.jsp");

		}
	}

	private String getSearchQuery(HttpServletRequest request, ICtChietKhauList obj)
	{
		// PrintWriter out = response.getWriter();

		// ICtChietKhauList obj = new CtChietKhauList();

		
		
		Utility util = new Utility();

		String bgTen = util.antiSQLInspection(request.getParameter("bgTen"));
		if (bgTen == null)
			bgTen = "";
		obj.setTen(bgTen);

		String dvkdId = util.antiSQLInspection(request.getParameter("dvkdId"));
		if (dvkdId == null)
			dvkdId = "";
		obj.setDvkdId(dvkdId);

		String trangthai = util.antiSQLInspection(request.getParameter("trangthai"));
		if (trangthai == null)
			trangthai = "";
		obj.setTrangthai(trangthai);

		String query = "\n  select * from (" +
		"\n  select a.trangthai, a.pk_seq as id, a.diengiai, c.ten as nguoitao, a.ngaytao, d.ten as nguoisua  " + 
		 "\n 		,a.ngaysua as ngaysua , kh.mafast makh, kh.ten tenkh, hd.mahopdong   " + 
		 "\n  from ChietkhauBacSi a  " + 
		 "\n  inner join khachhang kh on kh.pk_seq = a.khachhang_fk  " + 
		 "\n  inner join erp_hopdongnpp hd on hd.pk_seq = a.hopdong_fk   " + 
		 "\n  inner join nhanvien c on a.nguoitao = c.pk_seq  " + 
		 "\n  inner join nhanvien d on a.nguoisua = d.pk_seq  " + 
		 "\n  where   a.npp_fk = " + obj.getNppId() +
			"\n )tmp where 1=1 ";
		
		if (bgTen.length() > 0)
		{
			//query = query + " and upper(dbo.ftBoDau(tmp.diengiai)) like upper(N'%" + util.replaceAEIOU(bgTen) + "%')";
			query = query + " and upper(dbo.ftBoDau(tmp.diengiai)) like upper(dbo.ftBoDau(N'"+bgTen+"'))";
		}


		if (trangthai.length() > 0)
		{
			query = query + " and tmp.trangthai = '" + trangthai + "'";

		}
		//query = query + "  order by ten";
		System.out.println("Query search: "+query);
		return query;
	}

	private String Chot(String id,String userId)
	{
		dbutils db = new dbutils();
		try
		{
				db.getConnection().setAutoCommit(false);
								
				String command = " update ChietkhauBacSi set   trangthai = 1, nguoisua ="+userId+" where trangthai = 0 and pk_Seq =   " + id;
				if (db.updateReturnInt(command) <=0)
				{
					db.getConnection().rollback();			
					db.shutDown();
					return "Chot lối 3 : "+command;		
				}
				db.getConnection().commit();			
				db.shutDown();
				return "";	
			
		}catch (Exception e) {
			e.printStackTrace();
			db.update("rollback");
			db.shutDown();
			return "Exception:" + e.getMessage();
		}
		
	}
	
	private String Delete(String id)
	{
		
		dbutils db = new dbutils();
		try
		{
			db.getConnection().setAutoCommit(false);
				String command=
					"delete from [ChietkhauBacSi_BacSi_SanPham] where ChietkhauBacSi='" + id + "'";
				if (db.updateReturnInt(command) <=0)
				{
					db.getConnection().rollback();			
					db.shutDown();
					return "Chot lối 2 : "+command;		
				}
				
				command = "delete from ChietkhauBacSi where trangthai = 0 and pk_seq = '" + id + "'";
				if (db.updateReturnInt(command) <=0)
				{
					db.getConnection().rollback();			
					db.shutDown();
					return "Chot lối 3 : "+command;		
				}
				db.getConnection().commit();			
				db.shutDown();
				return "";	
			
		}catch (Exception e) {
			e.printStackTrace();
			db.update("rollback");
			db.shutDown();
			return "Exception:" + e.getMessage();
		}
		
		

	}

}
