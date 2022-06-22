package geso.dms.center.servlets.tieuchithuongkmdiem;

import geso.dms.center.beans.tieuchithuongkmdiem.ITieuchithuongKMDiem;
import geso.dms.center.beans.tieuchithuongkmdiem.ITieuchithuongKMDiemList;
import geso.dms.center.beans.tieuchithuongkmdiem.imp.TieuchithuongKMDiem;
import geso.dms.center.beans.tieuchithuongkmdiem.imp.TieuchithuongKMDiemList;
import geso.dms.center.db.sql.dbutils;
import geso.dms.center.util.Utility;

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

public class TieuchithuongKMDiemSvl extends HttpServlet 
{
	private static final long serialVersionUID = 1L;
	PrintWriter out;
   
    public TieuchithuongKMDiemSvl() {
        super();
    }
    
    private String getDateTime() 
	{
		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		Date date = new Date();
		return dateFormat.format(date);	
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		request.setCharacterEncoding("UTF-8");
	    response.setCharacterEncoding("UTF-8");
	    response.setContentType("text/html; charset=UTF-8");
	    this.out  = response.getWriter();
	    
	    HttpSession session = request.getSession();	
	    
	    Utility util = new Utility();
	    out = response.getWriter();
	    	    
	    String querystring = request.getQueryString();
	    String userId = util.getUserId(querystring);
	    out.println(userId);
	    
	    if (userId.length() == 0)
	    	userId = util.antiSQLInspection(request.getParameter("userId"));
	    	    
	    ITieuchithuongKMDiemList obj = new TieuchithuongKMDiemList();
	    obj.setUserId(userId);
	    
	    String action = util.getAction(querystring);
	    String ctskuId = util.getId(querystring);
	    
	    System.out.println("___Action: " + action + " -- Id la: " + ctskuId);
	    if(action.trim().equals("duyet"))
	    {
	    	Duyet(ctskuId,userId);
	    }
	    
	    if(action.trim().equals("delete"))
	    {
	    	XoaChiTieu(ctskuId);
	    }
	    
	    if(action.trim().equals("boduyet"))
	    {
	    	//BoDuyet(ctskuId,userId);
	    }

	    obj.init("");
		session.setAttribute("obj", obj);
	    
	    String nextJSP = request.getContextPath() + "/pages/Center/TieuChiThuongKMDiem.jsp";
		response.sendRedirect(nextJSP);
	}
	private String Duyet(String ctskuId,String userId )
	{
		dbutils db = new dbutils();
    	try
    	{
    		db.getConnection().setAutoCommit(false);	
    		db.update("update Ctkhuyenmai set trangthai = '1' where pk_seq = '" + ctskuId + "'");
    		
    	
    		
    		db.getConnection().commit();
    		db.shutDown();
    		return "Duyệt thành công";
    	
    	}
    	catch (Exception e) 
    	{
			db.update("rollback");
			db.shutDown();
			e.printStackTrace();
			return "ExceptionL" + e.getMessage();

		}
    	
	}
	
	private String BoDuyet(String ctskuId,String userId )
	{
		dbutils db = new dbutils();
    	try
    	{
    		db.getConnection().setAutoCommit(false);	
    		db.update("update tieuchithuongTL set trangthai = '0' where pk_seq = '" + ctskuId + "' and trangthai = '1' ");
    		
    		db.getConnection().commit();
    		db.shutDown();
    		return "Bỏ duyệt thành công";
    	
    	}
    	catch (Exception e) 
    	{
			db.update("rollback");
			db.shutDown();
			e.printStackTrace();
			return "ExceptionL" + e.getMessage();

		}
    	
	}

	private void XoaChiTieu(String ctskuId) 
	{
		dbutils db = new dbutils();
		ResultSet rs = db.get("select count(*) as num from donhang_ctkm_trakm where ctkmId ='" + ctskuId + "'");
		try
		{
			rs.next();
			if (!rs.getString("num").equals("0"))
			{
				rs.close();
				return;
			}
			rs.close();
			String trakmCurrent = "";
			String	query = 
					 " select a.TRAKHUYENMAI_FK  as trakmId from ctkm_trakm a inner join trakhuyenmai_sanpham b on a.TRAKHUYENMAI_FK = b.TRAKHUYENMAI_FK"
							+ " where a.CTKHUYENMAI_FK = "+ctskuId+"";
					ResultSet rsTrakm = db.get(query);						
					rsTrakm.next();
					trakmCurrent = rsTrakm.getString("trakmId");
					rsTrakm.close();
					
					String dkkkmCurrent = "";
					query = "select a.DKKHUYENMAI_FK  as dkkmId from ctkm_dkkm a inner join dieukienkm_sanpham b on a.DKKHUYENMAI_FK = b.dieukienkhuyenmai_fk"
							+ " where a.CTKHUYENMAI_FK = "+ctskuId+"";
					
					ResultSet rsDkkm = db.get(query);						
					rsDkkm.next();
					dkkkmCurrent = rsDkkm.getString("dkkmId");
					rsDkkm.close();
			db.getConnection().setAutoCommit(false);
			 query = "delete b from ctkm_dkkm a inner join dieukienkm_sanpham b on a.DKKHUYENMAI_FK = b.dieukienkhuyenmai_fk"
					+ " where a.CTKHUYENMAI_FK = "+ctskuId+"";
			if(!db.update(query))
			{
				db.update("rollback");
			}
			
			query = 
					 " delete b from ctkm_trakm a inner join trakhuyenmai_sanpham b on a.TRAKHUYENMAI_FK = b.TRAKHUYENMAI_FK"
							+ " where a.CTKHUYENMAI_FK = "+ctskuId+"";
			if(!db.update(query))
			{
				db.update("rollback");
			}
			
		
			
		
			// Xoa Cac Bang Con Truoc
			if(!db.update("delete from ctkm_dkkm where ctkhuyenmai_fk='" + ctskuId + "'"))
			{
				db.update("rollback");
			}
			if(!db.update("delete from ctkm_trakm where ctkhuyenmai_fk='" + ctskuId + "'"))
			{
				db.update("rollback");
			}
			if(!db.update("delete from ctkm_npp where ctkm_fk='" + ctskuId + "'"))
			{
				db.update("rollback");
			}
			query =  " delete  Dieukienkhuyenmai "
							+ " where pk_seq = "+trakmCurrent+"";
			if(!db.update(query))
			{
				db.update("rollback");
			}
			
			query =  " delete  trakhuyenmai"
							+ " where pk_seq = "+trakmCurrent+"";
				if(!db.update(query))
				{
					db.update("rollback");
				}
				// Xoa Bang Cha
			if(!db.update("delete from ctkhuyenmai where pk_seq = '" + ctskuId + "'"))
			{
					db.update("rollback");
			}
			db.update("commit");


		} catch (SQLException e)
		 {
			e.printStackTrace();
			db.update("rollback");
			
		}
    	
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		request.setCharacterEncoding("UTF-8");
	    response.setCharacterEncoding("UTF-8");
	    response.setContentType("text/html; charset=UTF-8");
	    this.out  = response.getWriter();
	    
	    HttpSession session = request.getSession();	
	    
	    out = response.getWriter();
	    Utility util = new Utility();
	    
	    String userId = util.antiSQLInspection(request.getParameter("userId"));     
	    ITieuchithuongKMDiemList obj;
	    
		String action = request.getParameter("action");
	    if (action == null){
	    	action = "";
	    }
	    
	    //AJAX
	    String type = request.getParameter("type");
	    if(type == null)
	    	type = "";
	    
	    if(type.equals("Ajax"))
	    {
	    	String vungId = request.getParameter("vungId");
	    	String khuvucId = request.getParameter("khuvucId");

	    }
	    else
	    {
		    if(action.equals("new"))
		    {
		    	ITieuchithuongKMDiem tctsku = new TieuchithuongKMDiem();
	    		tctsku.setUserId(userId);
	    		tctsku.createRs();
	    		
		    	session.setAttribute("tctskuBean", tctsku);  	
	    		session.setAttribute("userId", userId);
			
	    		response.sendRedirect(request.getContextPath() + "/pages/Center/TieuChiThuongKMDiemNew.jsp");
		    }
		    else
		    {
		    	obj = new TieuchithuongKMDiemList();
			    obj.setUserId(userId);
	
		    	String search = getSearchQuery(request, obj);
		    	
		    	obj.setUserId(userId);
		    	obj.init(search);
					
		    	session.setAttribute("obj", obj);  	
	    		session.setAttribute("userId", userId);
			
	    		response.sendRedirect(request.getContextPath() + "/pages/Center/TieuChiThuongKMDiem.jsp");	
		    }
	    }
	}
	
	private String getSearchQuery(HttpServletRequest request, ITieuchithuongKMDiemList obj) 
	{
		String thang = request.getParameter("thang");
		if(thang == null)
			thang = "";
		obj.setThang(thang);
		
		String nam = request.getParameter("nam");
		if(nam == null)
			nam = "";
		obj.setNam(nam);
		
		String diengiai = request.getParameter("diengiai");
		if(diengiai == null)
			diengiai = "";
		obj.setDiengiai(diengiai);
		
		String trangthai = request.getParameter("trangthai");
		if(trangthai == null)
			trangthai = "";
		obj.setTrangthai(trangthai);
		
		String sql = "select a.scheme, a.pk_seq, a.tungay thang, a.denngay nam, a.diengiai,isnull(a.trangthai,0) trangthai, a.ngaytao, a.ngaysua, b.ten as nguoitao, c.ten as nguoisua" +
				     ",'' as dktl  " +
					"from CTkhuyenmai a inner join NHANVIEN b on a.NGUOITAO = b.pk_seq " +
					"inner join NHANVIEN c on a.NGUOISUA = c.pk_seq where a.pk_seq > 0 ";
		if(thang.length() > 0)
			sql += " and a.tungay = '" + thang + "' ";
		if(nam.length() > 0)
			sql += " and a.denngay = '" + nam + "' ";
		if(diengiai.length() > 0)
			sql += " and (a.diengiai like N'%" + diengiai + "%' or a.scheme like N'%" + diengiai + "%') ";
		/*if(trangthai.length() > 0)
			sql += " and a.trangthai = '" + trangthai + "' ";*/
		
		sql += "order by tungay desc, denngay desc";
		
		return sql;
	}


}
