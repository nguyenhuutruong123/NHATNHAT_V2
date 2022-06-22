package geso.dms.center.servlets.duyettratrungbay;

import geso.dms.distributor.db.sql.dbutils;
import geso.dms.distributor.util.Utility;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.text.NumberFormat;
import java.text.DecimalFormat;

public class DuyetAnhTrungBayAjaxSvl extends HttpServlet 
{
	private static final long serialVersionUID = 1L;
       
    public DuyetAnhTrungBayAjaxSvl() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");
		this.doPost(request, response);
	}
	
	private String getDateTime()
	{
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date date = new Date();
        return dateFormat.format(date);	
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		PrintWriter out = response.getWriter();
		System.out.println("DuyetAnhTrungBayAjaxSvl vo");
		HttpSession session = request.getSession();
		Utility util = new Utility();
		String khId = util.antiSQLInspection(request.getParameter("khId"));
		String duyetId = util.antiSQLInspection(request.getParameter("duyetId"));
		String loai =util.antiSQLInspection(request.getParameter("loai")); 
		String userId = util.antiSQLInspection(request.getParameter("userId")); 
		System.out.println("ddkdId = "+ util.antiSQLInspection(request.getParameter("ddkdId"))  );
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");
		NumberFormat formatter = new DecimalFormat("#,###,###");
		dbutils db = new dbutils();
		String query = "";
		try 
		{
			db.getConnection().setAutoCommit(false);
			if(khId != null && duyetId != null && loai != null)
			{
				query = "UPDATE DENGHITRATRUNGBAY SET TRANGTHAI = '0' WHERE TRANGTHAI = '0' AND PK_SEQ = '"+ duyetId +"' ";
				if(db.updateReturnInt(query)!=1)
				{
					db.getConnection().rollback();
					out.write("Vui lòng kiểm tra trạng thái duyệt trả");
					return;

				}
				query = " UPDATE DENGHITRATB_KHACHHANG SET DUYETANH = "+loai+", THOIDIEMDUYETANH = dbo.GetLocalDate(DEFAULT), NGUOIDUYETANH = '"+ userId +"' " +
						" WHERE ISNULL(DUYETANH, 0) = '0' AND KHACHHANG_FK = '"+ khId +"' AND DENGHITRATB_FK = '"+ duyetId +"'";
				if(db.updateReturnInt(query)!=1)
				{
					db.getConnection().rollback();
					out.write("Lỗi duyệt ảnh KH");
					return;
				}
				query = " INSERT DENGHITRATB_KHACHHANG_DUYETANH(DUYETANH,DENGHITRATB_FK,KHACHHANG_FK,NGUOITAO) " +
						" SELECT "+loai+","+duyetId+","+khId+", '"+ userId +"' ";
				if(db.updateReturnInt(query)!=1){					
					db.getConnection().rollback();
					out.write("Lỗi duyệt ảnh KH");
					return ;

				}
				db.getConnection().commit();
				return;
			}
			else
			{
				db.getConnection().rollback();
				out.write("Lỗi dữ liệu!");
				return;
			}
					
		} 
		catch(Exception e)
		{ 
			geso.dms.center.util.Utility.rollback_throw_exception(db);
			out.write("Lỗi:" + e.getMessage());
			return;
		}
		finally{ try { db.getConnection().setAutoCommit(true); if(db != null) { db.shutDown(); } } catch (Exception ex) { ex.printStackTrace(); } }
	}

}
