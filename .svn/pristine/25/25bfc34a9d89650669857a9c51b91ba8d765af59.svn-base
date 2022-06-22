package geso.dms.distributor.servlets.duyetbandunggia;

import geso.dms.center.db.sql.dbutils;
import geso.dms.center.util.Utility;
import geso.dms.distributor.beans.duyetbandunggia.IDuyetbandunggiaNpp;
import geso.dms.distributor.beans.duyetbandunggia.IDuyetbandunggiaNppList;
import geso.dms.distributor.beans.duyetbandunggia.imp.DuyetbandunggiaNpp;
import geso.dms.distributor.beans.duyetbandunggia.imp.DuyetbandunggiaNppList;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class DuyetbandunggiaNppSvl extends HttpServlet 
{
	private static final long serialVersionUID = 1L;
	PrintWriter out;
   
    public DuyetbandunggiaNppSvl() {
        super();
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
	    
	   
	    IDuyetbandunggiaNppList obj = new DuyetbandunggiaNppList();
	    obj.setUserId(userId);
	    String View="";
	    View=request.getParameter("view");
	    if(View!=null && View.length()>0)
	    	obj.setView(View);
	    String action = util.getAction(querystring);
	    String ctskuId = util.getId(querystring);
	    
	    //System.out.println("___Action: " + action + " -- Id la: " + ctskuId);
	    if(action.trim().equals("duyet"))
	    {
	    	dbutils db = new dbutils();
	    	db.update("update DUYETBANDUNGGIA set trangthai = '1' where pk_seq = '" + ctskuId + "'");
	    	db.shutDown();
	    }
	    else if(action.equals("unduyet"))
	    {
	    	dbutils db = new dbutils();
	    	db.update("update DUYETBANDUNGGIA set trangthai = '0' where pk_seq = '" + ctskuId + "'");
	    	db.update("insert into  DUYETBANDUNGGIA_log select *,getdate() from duyetbandunggia where pk_seq = '" + ctskuId + "'");
	    	db.shutDown();
	    }
	    else if(action.trim().equals("delete"))
	    {
	    	XoaChiTieu(ctskuId);
	    }
	   
	   
	    	obj.init("");
	    
		session.setAttribute("obj", obj);
	    
	    String nextJSP = request.getContextPath() + "/pages/Distributor/DuyetBanDungGiaNpp.jsp";
		response.sendRedirect(nextJSP);
	}

	private void XoaChiTieu(String ctskuId) 
	{
		dbutils db = new dbutils();
    	
    	try 
    	{
			db.getConnection().setAutoCommit(false);
			
			if(!db.update("delete DUYETBANDUNGGIA_KHACHHANG where duyet_fk=  '" + ctskuId + "'"))
	    	{
	    		db.getConnection().rollback();
				return;
	    	}
			if(!db.update("delete DUYETBANDUNGGIA_KHACHHANG_CKUNGHO where duyet_fk = '" + ctskuId + "'"))
	    	{
	    		db.getConnection().rollback();
				return;
	    	}
			
	    	if(!db.update("delete DUYETBANDUNGGIA_KHACHHANG_HUONGQUY where duyet_fk = '" + ctskuId + "'"))
			{
				db.getConnection().rollback();
				return;
			}
	    	if(!db.update("delete DUYETBANDUNGGIA where pk_seq = '" + ctskuId + "'"))
			{
				db.getConnection().rollback();
				return;
			}
	    	db.getConnection().commit();
	    	db.shutDown();
		} 
    	catch (Exception e)
    	{
    		try 
    		{
				db.getConnection().rollback();
			} 
    		catch (SQLException e1) {}
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
	    IDuyetbandunggiaNppList obj;
	    
		String action = request.getParameter("action");
	    if (action == null){
	    	action = "";
	    }
	    
	    String type = request.getParameter("type");
	    if(type == null)
	    	type = "";
	   String View="";
	    View=request.getParameter("view");
	   
	    if(action.equals("new"))
	    {
    		IDuyetbandunggiaNpp tctsku = new DuyetbandunggiaNpp();
    		tctsku.setUserId(userId);
    		 if(View!=null && View.length()>0)
    			 tctsku.setView(View);
    		tctsku.createRs();
    		
	    	session.setAttribute("tctskuBean", tctsku);  	
    		session.setAttribute("userId", userId);
		
    		response.sendRedirect(request.getContextPath() + "/pages/Distributor/DuyetBanDungGiaNppNew.jsp");
	    }
	    else
	    {
	    	obj = new DuyetbandunggiaNppList();
		    obj.setUserId(userId);
		   
	    	String search = getSearchQuery(request, obj);
	    	
	    	obj.setUserId(userId);
	    	obj.init(search);
				
	    	session.setAttribute("obj", obj);  	
    		session.setAttribute("userId", userId);
		
    		response.sendRedirect(request.getContextPath() + "/pages/Distributor/DuyetBanDungGiaNpp.jsp");	
	    }
	    
	}
	
	private String getSearchQuery(HttpServletRequest request, IDuyetbandunggiaNppList obj) 
	{
		String tuthang = request.getParameter("tuthang");
		if(tuthang == null)
			tuthang = "";
		obj.setTuthang(tuthang);
		
		String denthang = request.getParameter("denthang");
		if(denthang == null)
			denthang = "";
		obj.setDenthang(denthang);
		
		String view = request.getParameter("view");
		if(view == null)
			view = "";
		obj.setView(view);
		
		String nam = request.getParameter("nam");
		if(nam == null)
			nam = "";
		obj.setNam(nam);
		

		String mien= request.getParameter("mien");
		if(mien == null)
			mien = "";
		obj.setMienid(mien);
		
		String nppId="";
		if(view.length()>0)
		{
		nppId = request.getParameter("npplist");
		if(nppId == null)
			nppId = "";
		obj.setNppidlist(nppId);
		}
		else
		{
			nppId = request.getParameter("nppId");
			if(nppId == null)
				nppId = "";
			obj.setNppId(nppId);
			
		}
		
		String sql="";
		if(obj.getView().length()>0)
		{
			String condition="";
			if(nppId.length()>0)
				condition+=" and a.npp_fk="+nppId;
			if(mien.length()>0)
			{
				condition+="   and a.npp_fk in (select na.pk_seq from NHAPHANPHOI na inner join TINHTHANH tta on na.TINHTHANH_FK=tta.PK_SEQ \n"+
							" inner join VUNG va on va.PK_SEQ=tta.VUNG_FK \n"+
							 " where va.PK_SEQ='"+mien+"') \n";
			}
			 sql = "select npp.ten,npp.pk_seq as nppid, a.pk_seq, a.thang, a.nam, diengiai, a.trangthai, a.ngaytao, a.ngaysua, b.ten as nguoitao, c.ten as nguoisua  " +
					"from DUYETBANDUNGGIA a inner join NHANVIEN b on a.NGUOITAO = b.pk_seq inner join nhaphanphoi npp on npp.pk_seq =a.npp_fk " +
					"inner join NHANVIEN c on a.NGUOISUA = c.pk_seq where 1=1 "+condition;
			 
		}
		else
		{
		 sql = "select a.pk_seq, a.thang, a.nam, diengiai, a.trangthai, a.ngaytao, a.ngaysua, b.ten as nguoitao, c.ten as nguoisua  " +
					"from DUYETBANDUNGGIA a inner join NHANVIEN b on a.NGUOITAO = b.pk_seq " +
					"inner join NHANVIEN c on a.NGUOISUA = c.pk_seq where a.npp_fk = '" + nppId + "' ";
		}
		
		if(tuthang.length() > 0 && denthang.length()>0)
			sql += " and (a.thang BETWEEN " + tuthang + " and  " + denthang + ") ";
		if(nam.length() > 0)
			sql += " and a.nam = '" + nam + "' ";
		
		
		sql += "order by nam desc, thang desc";
		
		System.out.println("vao search query "+sql);
		return sql;
	}

}
