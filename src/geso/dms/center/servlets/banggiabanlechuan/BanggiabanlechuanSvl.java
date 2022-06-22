package geso.dms.center.servlets.banggiabanlechuan;

import geso.dms.center.beans.banggiablc.*;
import geso.dms.center.beans.banggiablc.imp.*;
import geso.dms.center.db.sql.dbutils;
import geso.dms.center.util.Utility;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.ResultSet;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class BanggiabanlechuanSvl extends javax.servlet.http.HttpServlet implements javax.servlet.Servlet
{
	static final long serialVersionUID = 1L;

	public BanggiabanlechuanSvl()
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
		IBanggiablcList obj;

		obj = new BanggiablcList();
		obj.setUserId(userId);
		obj.setMsg(msg);
		session.setAttribute("obj", obj);

		String nextJSP = request.getContextPath() + "/pages/Center/BangGiaBanLeChuan.jsp";
		response.sendRedirect(nextJSP);

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");
		PrintWriter out = response.getWriter();
		IBanggiablcList obj;
		obj = new BanggiablcList();
		HttpSession session = request.getSession();
		geso.dms.center.util.Csrf csdr=new geso.dms.center.util.Csrf(request,response,false,true ,false);
		if(!csdr.__validate_post())
		{
			response.sendRedirect(request.getContextPath() + "/redirect.jsp");
			return;
		}
		Utility util = new Utility();
		String userId = util.antiSQLInspection(request.getParameter("userId"));

		String action = request.getParameter("action");
		if (action == null)
		{
			action = "";
		}
		out.println(action);

		if (action.equals("new"))
		{

			IBanggiablc bgBean = (IBanggiablc) new Banggiablc();
			bgBean.setUserId(userId);

			session.setAttribute("bgblcBean", bgBean);

			String nextJSP = request.getContextPath() + "/pages/Center/BangGiaBanLeChuanNew.jsp";
			response.sendRedirect(nextJSP);

		}
		if (action.equals("search"))
		{
			String search = getSearchQuery(request, obj);

			obj.init(search);

			obj.setUserId(userId);

			session.setAttribute("obj", obj);

			response.sendRedirect(request.getContextPath() + "/pages/Center/BangGiaBanLeChuan.jsp");

		}
	}

	private String getSearchQuery(HttpServletRequest request, IBanggiablcList obj)
	{
		// PrintWriter out = response.getWriter();

		// IBanggiablcList obj = new BanggiablcList();

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

		if (trangthai.equals("2"))
			trangthai = "";

		obj.setTrangthai(trangthai);

		String query = "select a.pk_seq as id, a.ten as ten, a.trangthai as trangthai, c.ten as nguoitao, a.ngaytao as ngaytao, d.ten as nguoisua, a.ngaysua as ngaysua, b.donvikinhdoanh as dvkd, b.pk_seq as dvkdId from banggiabanlechuan a, donvikinhdoanh b, nhanvien c, nhanvien d where a.dvkd_fk=b.pk_seq and a.nguoitao = c.pk_seq and a.nguoisua = d.pk_seq ";

		if (bgTen.length() > 0)
		{
			query = query + " and upper(dbo.ftBoDau(a.ten)) like upper(N'%" + util.replaceAEIOU(bgTen) + "%')";
		}

		if (dvkdId.length() > 0)
		{
			query = query + " and b.pk_seq = '" + dvkdId + "'";

		}

		if (trangthai.length() > 0)
		{
			query = query + " and a.trangthai = '" + trangthai + "'";

		}
		query = query + "  order by ten";
		System.out.println("tim kiem bang gia "+ query );
		return query;
	}

	private String Chot(String id,String userId)
	{
		dbutils db = new dbutils();
		try
		{
				db.getConnection().setAutoCommit(false);
				String sql_getnpp=	 	"\n select distinct pk_seq as manpp  " +
								 		"\n from nhaphanphoi npp " +
								 		"\n where trangthai = 1 and isnull(npp.iskhachhang,0) = 0 and pk_seq !=1 ";
				System.out.println ( "Lay Ra Nhung Nha PP Theo DVDK :"+ sql_getnpp );
				ResultSet rs_getnpp=db.get(sql_getnpp);

				System.out.println ( "Lay Ra Nhung Nha PP Theo DVDK789 :"+ sql_getnpp );
				while(rs_getnpp.next())
				{
					String sql_insertbangia="\n insert into BANGGIABANLENPP(kbh_fk,ngaybatdau,ten,ngaytao,ngaysua,nguoitao,nguoisua,dvkd_fk,npp_fk,BANGGIABANLECHUAN_FK) "+
											"\n  select kbh_fk ,tungay ,ten,'"+Utility.getNgayHienTai()+"','"+Utility.getNgayHienTai()+"',"+userId+","+userId+",dvkd_fk,"+rs_getnpp.getString("manpp")+",pk_seq " +
											"\n from banggiabanlechuan where pk_Seq =  "+ id;
					System.out.println("4. BANGGIABANLENPP : "+sql_insertbangia);
					if (db.updateReturnInt(sql_insertbangia) <=0)
					{
						
						db.getConnection().rollback();			
						db.shutDown();
						return "Chot lối 1 : "+sql_insertbangia;		
					}
				}
				
				
				String command=
				"\n INSERT INTO BGBANLENPP_SANPHAM(BGBANLENPP_FK,SANPHAM_FK,GIABANLENPP,GIABANLECHUAN) "+
				"\n SELECT    B.PK_SEQ as BGBANLENPP_FK ,A.SANPHAM_FK,A.GIABANLECHUAN,A.GIABANLECHUAN "+
				"\n  FROM BANGGIABLC_SANPHAM A "+ 
				"\n INNER JOIN "+  
				"\n ( "+
				"\n 	 SELECT PK_SEQ, BANGGIABANLECHUAN_FK FROM BANGGIABANLENPP "+
				"\n  ) B ON a.BGBLC_FK = b.BANGGIABANLECHUAN_FK AND a.BGBLC_FK = "+id+" ";
				System.out.println("[BGBANLENPP_SANPHAM] "+command);
				if (db.updateReturnInt(command) <=0)
				{
					db.getConnection().rollback();			
					db.shutDown();
					return "Chot lối 2 : "+command;		
				}
				
				command = " update BANGGIABANLECHUAN set trangthai = 1, nguoiduyet ="+userId+" where trangthai = 0 and pk_Seq =   " + id;
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
					"delete from banggiablc_sanpham where bgblc_fk='" + id + "'";
				if (db.updateReturnInt(command) <=0)
				{
					db.getConnection().rollback();			
					db.shutDown();
					return "Chot lối 2 : "+command;		
				}
				
				command = "delete from banggiabanlechuan where trangthai = 0 and pk_seq = '" + id + "'";
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
