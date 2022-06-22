package geso.dms.center.servlets.tieuchithuong;

import geso.dms.center.beans.tieuchithuong.ITieuchithuongTL;
import geso.dms.center.beans.tieuchithuong.ITieuchithuongTLList;
import geso.dms.center.beans.tieuchithuong.imp.TieuchithuongTL;
import geso.dms.center.beans.tieuchithuong.imp.TieuchithuongTLList;
import geso.dms.center.db.sql.dbutils;
import geso.dms.center.util.Utility;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.sql.ResultSet;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class TieuchithuongTLSvl extends HttpServlet 
{
	private static final long serialVersionUID = 1L;
	PrintWriter out;
   
    public TieuchithuongTLSvl() {
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
	    	    
	    ITieuchithuongTLList obj = new TieuchithuongTLList();
	    obj.setUserId(userId);
	    
	    String action = util.getAction(querystring);
	    String ctskuId = util.getId(querystring);
	    
	    System.out.println("___Action: " + action + " -- Id la: " + ctskuId);
	   /* if(action.trim().equals("duyet"))
	    {
	    	Duyet(ctskuId,userId);
	    }*/
	    
	    if(action.trim().equals("delete"))
	    {
	    	obj.setMsg(XoaChiTieu(ctskuId));
	    }
	    
	    if(action.trim().equals("boduyet"))
	    {
	    	BoDuyet(ctskuId,userId);
	    }

	    obj.init("");
		session.setAttribute("obj", obj);
	    
	    String nextJSP = request.getContextPath() + "/pages/Center/TieuChiThuongTL.jsp";
		response.sendRedirect(nextJSP);
	}
	private String Duyet(String ctskuId,String userId )
	{
		dbutils db = new dbutils();
    	try
    	{
    		db.getConnection().setAutoCommit(false);	
    		
    		int sodong = db.updateReturnInt("update tieuchithuongTL set trangthai = '1' where trangthai = 0 and pk_seq = '" + ctskuId + "'");
    		
    		db.getConnection().commit();
    		db.shutDown();
    		if(sodong > 0)
    			return "Duyệt thành công";
    		else
    			return "Tích lũy đã duyệt hoặc bị hủy";
    	}
    	catch (Exception e) 
    	{
			geso.dms.center.util.Utility.rollback_throw_exception(db);
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
    		//db.update("update tieuchithuongTL set trangthai = '0' where pk_seq = '" + ctskuId + "' and trangthai = '1' ");
    		String query = "select count(*) _c from  DUYETTRAKHUYENMAI x where CTKM_FK =  "+ ctskuId+"		 ";
    		ResultSet rs = db.get(query);
    		rs.next();
    		if(rs.getInt("_c")> 0 )
    		{
    			   
				    Utility.rollback_and_shutdown(db);
	    			return "Đã phát sinh duyệt trả không được mở chốt"; 
    		}
    		 query = "select count(*) _c from  DANGKYKM_TICHLUY_KHACHHANG x where thuongtl_fk =  " + ctskuId;
    		 rs = db.get(query);
    		rs.next();
    		if(rs.getInt("_c")> 0 )
    		{

    			Utility.rollback_and_shutdown(db);
    			return "Đã phát sinh khách hàng đăng ký không thể mở chốt!"; 
    		}
    		
    		query = " delete DANGKYKM_TICHLUY where thuongtl_fk =  " + ctskuId;
    		if(! db.update(query))
    		{
    			Utility.rollback_and_shutdown(db);
    			return "Đã phát sinh khách hàng đăng ký không thể mở chốt!"; 
    		}
    		query = " update tieuchithuongTL set trangthai = '0' where pk_seq = '" + ctskuId + "' and trangthai = '1'  ";
    		
    		if(db.updateReturnInt(query)!=1)
    		{
    			Utility.rollback_and_shutdown(db);
    			return "Đã phát sinh khách hàng đăng ký không thể mở chốt!"; 
    		}
    		
    		db.getConnection().commit();
    		db.shutDown();
    		return "Bỏ duyệt thành công";
    	
    	}
    	catch (Exception e) 
    	{
			geso.dms.center.util.Utility.rollback_throw_exception(db);
			db.shutDown();
			e.printStackTrace();
			return "ExceptionL" + e.getMessage();

		}
    	
	}

	private String XoaChiTieu(String ctskuId) 
	{
		dbutils db = new dbutils();
    	
    	try 
    	{
			db.getConnection().setAutoCommit(false);
			String query="SELECT COUNT(*) AS SL FROM DUYETTRAKHUYENMAI WHERE CTKM_FK= '" + ctskuId + "'";
			ResultSet checkRs=db.get(query);
			checkRs.next();
			if(checkRs.getInt("sl")>0) {
				db.getConnection().rollback();
				return "Scheme đã tồn tại trong duyệt trả không thể xóa";
			}
			if(!db.update("delete TIEUCHITHUONGTL_MUCTHUONG where thuongtl_fk = '" + ctskuId + "'"))
	    	{
	    		db.getConnection().rollback();
				return "Lỗi xóa";
	    	}
			
			if(!db.update("delete TIEUCHITHUONGTL_SANPHAM where thuongtl_fk = '" + ctskuId + "'"))
	    	{
	    		db.getConnection().rollback();
				return "Lỗi xóa";
	    	}
			
			if(!db.update("delete TIEUCHITHUONGTL_SPTRA where thuongtl_fk = '" + ctskuId + "'"))
	    	{
	    		db.getConnection().rollback();
				return "Lỗi xóa";
	    	}
			
			if(!db.update("delete TIEUCHITHUONGTL_TIEUCHI where thuongtl_fk = '" + ctskuId + "'"))
	    	{
	    		db.getConnection().rollback();
				return "Lỗi xóa";
	    	}
	    	
	    	if(db.updateReturnInt("delete TIEUCHITHUONGTL where trangthai=0 and pk_seq = '" + ctskuId + "'")!=1)
			{
				db.getConnection().rollback();
				return "Lỗi xóa";
			}
	    	db.getConnection().commit();
	    	db.shutDown();
		} 
    	catch (Exception e)
    	{
    		try 
    		{
				db.getConnection().rollback();
			} catch (SQLException e1) {}
    	}
    	return"";
    	
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		request.setCharacterEncoding("UTF-8");
	    response.setCharacterEncoding("UTF-8");
	    response.setContentType("text/html; charset=UTF-8");
	    this.out  = response.getWriter();
	    
	    HttpSession session = request.getSession();	
	    geso.dms.center.util.Csrf csdr=new geso.dms.center.util.Csrf(request,response,false,true ,false);
		if(!csdr.__validate_post())
		{
			response.sendRedirect(request.getContextPath() + "/redirect.jsp");
			return;
		}
	    out = response.getWriter();
	    Utility util = new Utility();
	    
	    String userId = util.antiSQLInspection(request.getParameter("userId"));     
	    ITieuchithuongTLList obj;
	    
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
	    	
	    	
	    	if(action.equals("Chot"))
		    {
	    		String ctskuId = util.antiSQLInspection(request.getParameter("IdXoa"));
				if (ctskuId == null)
					ctskuId = "";
				System.out.println("ctskuId = "+ ctskuId);
				
	    		String ms = Duyet(ctskuId, userId);
	    		obj = new TieuchithuongTLList();
			    obj.setUserId(userId);	
			    obj.setMsg(ms);
		    	String search = getSearchQuery(request, obj);		    	
		    	obj.setUserId(userId);
		    	obj.init(search);					
		    	session.setAttribute("obj", obj);  	
	    		session.setAttribute("userId", userId);		
	    		response.sendRedirect(request.getContextPath() + "/pages/Center/TieuChiThuongTL.jsp");	
		    }
	    	else
		    if(action.equals("new"))
		    {
	    		ITieuchithuongTL tctsku = new TieuchithuongTL();
	    		tctsku.setUserId(userId);
	    		tctsku.createRs();
	    		
		    	session.setAttribute("tctskuBean", tctsku);  	
	    		session.setAttribute("userId", userId);
			
	    		response.sendRedirect(request.getContextPath() + "/pages/Center/TieuChiThuongTLNew.jsp");
		    }
		    else
		    {
		    	obj = new TieuchithuongTLList();
			    obj.setUserId(userId);
	
		    	String search = getSearchQuery(request, obj);
		    	
		    	obj.setUserId(userId);
		    	obj.init(search);
					
		    	session.setAttribute("obj", obj);  	
	    		session.setAttribute("userId", userId);
			
	    		response.sendRedirect(request.getContextPath() + "/pages/Center/TieuChiThuongTL.jsp");	
		    }
	    }
	}
	
	private String getSearchQuery(HttpServletRequest request, ITieuchithuongTLList obj) 
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
		
		String sql = "select a.scheme, a.pk_seq, a.thang, a.nam, a.diengiai, a.trangthai, a.ngaytao, a.ngaysua, b.ten as nguoitao, c.ten as nguoisua" +
				     "\n,case when a.TRANGTHAI = '1' then isnull(cast(dktl.PK_SEQ as varchar), '1') else '0' end as dktl  " +
					"\nfrom TIEUCHITHUONGTL a inner join NHANVIEN b on a.NGUOITAO = b.pk_seq " +
					"\n left join DUYETTRAKHUYENMAI dktl on dktl.ctkm_fk = a.pk_seq and dktl.TRANGTHAI = 1 "+
					"\ninner join NHANVIEN c on a.NGUOISUA = c.pk_seq where a.pk_seq > 0 ";
		if(thang.length() > 0)
		{
			sql += "\n and SUBSTRING(a.thang,6,2) <= '" + (thang.length()>1?thang:"0"+thang) + "'  ";
			//sql += "\n and SUBSTRING(a.nam,6,2) <= '" + (thang.length()>1?thang:"0"+thang) + "' ";
		}
		if(nam.length() > 0)
		{
			sql += "\n and SUBSTRING(a.thang,1,4) <= '" + nam + "' ";
			//sql += "\n and SUBSTRING(a.nam,1,4) >='" + nam + "' ";
		}
			
		if(diengiai.length() > 0)
			sql += "\n and (a.diengiai like N'%" + diengiai + "%' or a.scheme like N'%" + diengiai + "%') ";
		if(trangthai.length() > 0)
			sql += "\n and a.trangthai = '" + trangthai + "' ";
		
		sql += "\n order by nam desc, thang desc";
		System.out.println("queryshearch: "+sql);
	
		return sql;
	}


}
