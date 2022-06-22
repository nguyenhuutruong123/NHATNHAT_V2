package geso.dms.distributor.servlets.BacSi;

import geso.dms.distributor.beans.BacSi.IBacSi;
import geso.dms.distributor.beans.BacSi.IBacSiList;
import geso.dms.distributor.beans.BacSi.imp.BacSi;
import geso.dms.distributor.beans.BacSi.imp.BacSiList;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import geso.dms.distributor.db.sql.dbutils;
import geso.dms.distributor.util.Utility;

import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class BacSiSvl extends HttpServlet 
{
	private static final long serialVersionUID = 1L;
	private int items = 50;
	private int splittings = 20;


	public BacSiSvl() 
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
		if(!cutil.check(userId, userTen, sum)) {
			response.sendRedirect(request.getContextPath() + "/redirect.jsp");
		} else {

			IBacSiList obj;
			PrintWriter out; 


			request.setCharacterEncoding("UTF-8");
			response.setCharacterEncoding("UTF-8");
			response.setContentType("text/html; charset=UTF-8");
			out  = response.getWriter();

			Utility util = new Utility();
			out = response.getWriter();

			String querystring = request.getQueryString();
			userId = util.getUserId(querystring);
			out.println(userId);

			if (userId.length()==0)
				userId = util.antiSQLInspection(request.getParameter("userId"));

			String action = util.getAction(querystring);
			out.println(action);

			String khId = util.getId(querystring);
			obj = new BacSiList();
			String msg="";
			settingPage(obj);
			obj.setUserId(userId);
			obj.init("");
			session.setAttribute("obj", obj);
			String nextJSP = request.getContextPath() + "/pages/Distributor/BacSi.jsp";
			response.sendRedirect(nextJSP);
		}
	}

	private void settingPage(IBacSiList obj) {
		Utility util = new Utility();
		if(getServletContext().getInitParameter("items") != null){
			String i = getServletContext().getInitParameter("items").trim();
			if(util.isNumeric(i))
				items = Integer.parseInt(i);
		}

		if(getServletContext().getInitParameter("splittings") != null){
			String i = getServletContext().getInitParameter("splittings").trim();
			if(util.isNumeric(i))
				splittings = Integer.parseInt(i);
		}

		obj.setItems(items);
		obj.setSplittings(splittings);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{

		HttpSession session = request.getSession();
		String userId = (String) session.getAttribute("userId");  
		String userTen = (String) session.getAttribute("userTen");  	
		String sum = (String) session.getAttribute("sum");

		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");

		geso.dms.center.util.Utility cutil = (geso.dms.center.util.Utility) session.getAttribute("util");
		if(!cutil.check(userId, userTen, sum)){
			response.sendRedirect(request.getContextPath() + "/redirect.jsp");
		}else{

			OutputStream out = response.getOutputStream();	

			IBacSiList obj = new BacSiList();


			request.setCharacterEncoding("UTF-8");
			response.setCharacterEncoding("UTF-8");
			response.setContentType("text/html; charset=UTF-8");

			Utility util = new Utility();
			userId = util.antiSQLInspection(request.getParameter("userId"));

			String action = request.getParameter("action");
			if (action == null){
				action = "";
			}

			if (action.equals("new"))
			{

				IBacSi khBean = (IBacSi) new BacSi("");
				khBean.setUserId(userId);
				khBean.createRS();
				util = new Utility();
				util.getIdNhapp(userId);		
				session.setAttribute("khBean", khBean);
				String nextJSP = request.getContextPath() + "/pages/Distributor/BacSiNew.jsp";
				response.sendRedirect(nextJSP);

			}

			settingPage(obj);

			if (action.equals("search"))
			{	    
				obj.setUserId(userId);
				String search = getSearchQuery(request, obj);

				obj.init(search);

				session.setAttribute("obj", obj);  	
				session.setAttribute("userId", userId);

				response.sendRedirect(request.getContextPath() + "/pages/Distributor/BacSi.jsp");	    		    	
			}
			else  if(action.equals("view") || action.equals("next") || action.equals("prev")){

				obj.setNxtApprSplitting(Integer.parseInt(request.getParameter("nxtApprSplitting")));
				obj.setUserId(userId);
				String search = getSearchQuery(request, obj);
				obj.init(search);
				System.out.println("Phan Trang: "+request.getParameter("nxtApprSplitting"));   		
				obj.setAttribute(request, action, "list", "crrApprSplitting", "nxtApprSplitting");
				session.setAttribute("obj", obj);
				response.sendRedirect(request.getContextPath() + "/pages/Distributor/BacSi.jsp");
			}


		}
	}

	private String getSearchQuery(HttpServletRequest request, IBacSiList obj)
	{		

		Utility util = new Utility();
		util.getIdNhapp(obj.getUserId());

		String bsTen = util.antiSQLInspection(request.getParameter("bsTen"));
		if ( bsTen == null)
			bsTen = "";
		obj.setTen(bsTen);
		
		String khTen = util.antiSQLInspection(request.getParameter("khTen"));
		if ( khTen == null)
			khTen = "";
		obj.setKhten(khTen);

		String nppId = util.antiSQLInspection(request.getParameter("nppId"));
		if ( nppId == null)
			nppId = "";
		obj.setNppid(nppId); 
		
		String query = " select pk_seq,ISNULL(ma,'') as MA,ISNULL(ten,'') as ten,ISNULL(sodienthoai,'') sodienthoai "+
				" ,case when trangthai=1 then N'Hoạt Động' else N'Ngưng hoạt động' end trangthai	,ISNULL(diachi,'') diachi,ISNULL(EMAIL,'') email from BacSi "+
			    " where npp_fk="+obj.getNppid()+" ";
		
		if(bsTen.trim().length()>0)
			query+=" and (ten like N'%"+bsTen+"%' or ma like N'%"+bsTen+"%' ) ";
		if(khTen.trim().length()>0)
			query+=" and pk_seq in ( select bacsi_fk from KhachHang_BacSi a inner join khachhang b on a.khachhang_fk=b.pk_seq where ten like N'%"+khTen+"%' or mafast like N'%"+khTen+"%' )   ";
		


		System.out.println("Query Search "+query);
		return query;
	}	

	private String Delete(String id, String userId,IBacSiList obj) throws SQLException
	{
		dbutils db = new dbutils();
		Utility util = new Utility();
		String nppId=util.getIdNhapp(userId);
		try 
		{
			db.getConnection().setAutoCommit(false);
			String	query = "delete from KhachHang_ where khachhang_fk = '"+ id +"'";
			if(!db.update(query))
			{
				db.getConnection().rollback();
				return "Không thể xóa Khách Hàng "+query;
			}

			query = "delete from khachhang_nkhachhang where khachhang_fk='" + id + "'";
			if(!db.update(query))
			{
				db.getConnection().rollback();
				return "Không thể xóa Khách Hàng "+query;
			}

			query = "delete from NHOMKHACHHANG_KHACHHANG where kh_fk='" + id + "'";
			if(!db.update(query))
			{
				db.getConnection().rollback();
				return "Không thể xóa Khách Hàng "+query;
			}

			query = "delete from khachhang_tuyenBH where khachhang_fk='" + id + "'";
			if(!db.update(query))
			{
				db.getConnection().rollback();
				return "Không thể xóa Khách Hàng "+query;
			}

			query = "delete from nvgn_kh where khachhang_fk = '"+ id +"'";
			if(!db.update(query))
			{
				db.getConnection().rollback();
				return "Không thể xóa Khách Hàng "+query;
			}

			query = "delete from KHACHHANG_TUYENBH where khachhang_fk = '"+ id +"'";

			if(!db.update(query))
			{
				db.getConnection().rollback();
				return "Không thể xóa Khách Hàng "+query;
			}

			query = "delete from KHACHHANG_ANHCHUP where khachhang_fk = '"+ id +"'";
			if(!db.update(query))
			{
				db.getConnection().rollback();
				return "Không thể xóa Khách Hàng "+query;
			}

			query = "delete from KHACHHANG_CONGNO where khachhang_fk = '"+ id +"'";
			if(!db.update(query))
			{
				db.getConnection().rollback();
				return "Không thể xóa Khách Hàng "+query;
			}

			query = "delete from KhachHang_DaiDienKinhDoanh where khachhang_fk = '"+ id +"'";
			if(!db.update(query))
			{
				db.getConnection().rollback();
				return "Không thể xóa Khách Hàng "+query;
			}

			query = "delete from KHACHHANG_KHODOITHU where KH_FK = '"+ id +"'";
			if(!db.update(query))
			{
				db.getConnection().rollback();
				return "Không thể xóa Khách Hàng "+query;
			}

			query = "delete from KHACHHANG_MUCTIEUNGAY where khachhang_fk = '"+ id +"'";
			if(!db.update(query))
			{
				db.getConnection().rollback();
				return "Không thể xóa Khách Hàng "+query;
			}

			query = "delete from KHACHHANG_TOADO_LOG where khachhang_fk = '"+ id +"'";
			if(!db.update(query))
			{
				db.getConnection().rollback();
				return "Không thể xóa Khách Hàng "+query;
			}

			query = "delete from KHACHHANG_YKIEN where khachhang_fk = '"+ id +"'";
			if(!db.update(query))
			{
				db.getConnection().rollback();
				return "Không thể xóa Khách Hàng "+query;
			}


			query = "delete from ddkd_khachhang where khachhang_fk = '"+ id +"'";
			if(!db.update(query))
			{
				db.getConnection().rollback();
				return "Không thể xóa Khách Hàng "+query;
			}

			query = "delete from DDKD_KHACHHANG_LOG where khachhang_fk = '"+ id +"'";
			if(!db.update(query))
			{
				db.getConnection().rollback();
				return "Không thể xóa Khách Hàng "+query;
			}

			query = "delete from makhachhang where khachhang_fk = '"+ id +"'";
			if(!db.update(query))
			{
				db.getConnection().rollback();
				return "Không thể xóa Khách Hàng "+query;
			}
			
			query = "delete from khachhang where pk_seq = '" + id + "'  ";
			if(!db.update(query))
			{
				db.getConnection().rollback();
				System.out.println("::::"+query);
				return "Không thể xóa Khách Hàng "+query;
			}
			db.getConnection().commit();
			db.getConnection().setAutoCommit(true);
		}
		catch(Exception e) 
		{
			db.getConnection().rollback();
			e.printStackTrace();
			return "Không thể xóa Khách Hàng,do đã phát sinh dữ liệu!Exception";
		}
		finally
		{
			db.shutDown();	
		}
		return "";
	}

	private String getQueryExcel(HttpServletRequest request, IBacSiList obj)
	{
		Utility util = new Utility();
		String ten = util.antiSQLInspection(request.getParameter("khTen"));
		if ( ten == null)
			ten = "";
		obj.setTen(ten);

		String nppId = util.antiSQLInspection(request.getParameter("nppId"));
		if ( nppId == null)
			nppId = "";
		obj.setNppid(nppId);

		String trangthai = util.antiSQLInspection(request.getParameter("trangthai")); 	
		if (trangthai == null)
			trangthai = "";    		
		obj.setTrangthai(trangthai);


		String query ="";
		

		


		return query;

	}







	private String getDateTime()
	{
		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		Date date = new Date();
		return dateFormat.format(date);
	}







	private String Duyet(String id) throws SQLException
	{
		dbutils db = new dbutils();

		try 
		{

			db.getConnection().setAutoCommit(false);

			String query = "update khachhang set daduyet =1 where isnull(daduyet,0) = 0 and pk_Seq ='" + id + "'";
			if(!db.update(query))
			{
				db.getConnection().rollback();
				return "Không thể duyệt Khách Hàng "+query;
			}

			db.getConnection().commit();
			db.getConnection().setAutoCommit(true);
			db.shutDown();
			return "Duyệt thành công!";
		}
		catch(Exception e) 
		{
			db.getConnection().rollback();
			e.printStackTrace();
			db.shutDown();
			return "Không thể xóa Khách Hàng,do đã phát sinh dữ liệu!Exception";
		}

	}
	private String DongBo(String id) throws SQLException
	{
		dbutils db = new dbutils();

		try 
		{

			db.getConnection().setAutoCommit(false);

			String query = "update khachhang set dongbo =1 where cast(pk_seq as varchar) = mafast  and isnull(daduyet,0) = 0  and isnull(dongbo,0) = 0 and pk_Seq ='" + id + "'";
			if(!db.update(query))
			{
				db.getConnection().rollback();
				return "Không thể duyệt Khách Hàng "+query;
			}

			db.getConnection().commit();
			db.getConnection().setAutoCommit(true);
			db.shutDown();
			return "Duyệt thành công!";
		}
		catch(Exception e) 
		{
			db.getConnection().rollback();
			e.printStackTrace();
			db.shutDown();
			return "Không thể xóa Khách Hàng,do đã phát sinh dữ liệu!Exception";
		}

	}

}
