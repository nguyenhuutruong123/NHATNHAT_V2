package geso.dms.center.servlets.tieuchithuong;
import geso.dms.center.beans.tieuchithuong.imp.Tieuchithuong;
import geso.dms.center.beans.tieuchithuong.imp.TieuchithuongList;
import geso.dms.center.beans.tieuchithuong.ITieuchithuong;
import geso.dms.center.beans.tieuchithuong.ITieuchithuongList;
import geso.dms.center.db.sql.dbutils;
import geso.dms.center.util.Utility;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.TTCCLayout;

import java.sql.ResultSet;
import java.sql.SQLException;

public class TieuChiThuongSvl extends HttpServlet
{
	private static final long serialVersionUID = 1L;
	
	public TieuChiThuongSvl()
	{
		super();
	}
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		request.setCharacterEncoding("UTF-8");
	    response.setCharacterEncoding("UTF-8");
	    response.setContentType("text/html; charset=UTF-8");
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

	    String querystring = request.getQueryString();
	    String userId = request.getParameter("userId");
	    
	    System.out.println("user id la "+userId);

	    	    
	    ITieuchithuongList obj = new TieuchithuongList();
	    obj.setUserId(userId);

	    
	    String action = util.antiSQLInspection(util.getAction(querystring));
	    
	    String id = util.antiSQLInspection(util.getId(querystring));
	   
	   
	    if(action.equals("chot")){
		    id = util.antiSQLInspection(util.getId(querystring)).split(";")[0];
	    	obj.setMsg(Chot(id, util.antiSQLInspection(util.getId(querystring)).split(";")[1], userId));
	    }
	    
	    if(action.equals("mochot")){
		    id = util.antiSQLInspection(util.getId(querystring));
		    MoChot(id);
	    }
	    
	    if(action.equals("xoa")){
		    id = util.antiSQLInspection(util.getId(querystring)).split(";")[0];
		    String msg=Xoa(id);
		    System.out.println("msg=="+msg);
		    obj.setMsg(msg);
	    }
	    
	    obj.init();
	    
	    session.setAttribute("obj", obj);
	    
    	session.setAttribute("userId", userId);
		
    	response.sendRedirect(request.getContextPath() + "/pages/Center/TieuChiThuong.jsp");
		
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
	    ITieuchithuongList obj = new TieuchithuongList();
	    
	    String userId = util.antiSQLInspection(request.getParameter("userId"));
	    String dvkdId = util.antiSQLInspection(request.getParameter("dvkdId"));
	    String kbhId = util.antiSQLInspection(request.getParameter("kbhId"));
	    String month = util.antiSQLInspection(request.getParameter("month"));
	    String year = util.antiSQLInspection(request.getParameter("year"));
	    String action = util.antiSQLInspection(request.getParameter("action"));
	    String loai = util.antiSQLInspection(request.getParameter("loai"));
	    String loaithuong = util.antiSQLInspection(request.getParameter("loaithuong"));
	    obj.setLoaithuong(loaithuong);
	    obj.setDvkdId(dvkdId);
	    obj.setKbhId(kbhId==null?"":kbhId);
	    obj.setUserId(userId);
	    obj.setMonth(month);
	    obj.setYear(year);
	    obj.setLoai(loai);
	    System.out.println("Loai: "+loai);
	    System.out.println(action);
	    
	    if(action.equals("timkiem")){
		    obj.init();
		    
		    session.setAttribute("obj", obj);
		    
	    	session.setAttribute("userId", userId);
			
	    	response.sendRedirect(request.getContextPath() + "/pages/Center/TieuChiThuong.jsp");
	    	return;
	    }	    
	    else if(action.equals("taomoi")){
	    	ITieuchithuong tctBean = new Tieuchithuong();
	    	tctBean.setUserId(userId);
	    	tctBean.setAction(action);
	    	tctBean.setTctId("0");	
	    	tctBean.SetLoaiTieuChi(loai);
	    	tctBean.setLoaithuong(loaithuong);
	    	tctBean.init();
		    
	    	session.setAttribute("tctBean", tctBean);
	    	
	    	session.setAttribute("userId", userId);
			
	    	response.sendRedirect(request.getContextPath() + "/pages/Center/TieuChiThuongUpdate.jsp");
	    	return;
	    }
	}
	private void MoChot(String id){
		String query = "UPDATE TIEUCHITINHTHUONG SET TRANGTHAI ='0' WHERE PK_SEQ = '" + id + "'";
		System.out.println(query);
		
		dbutils db = new dbutils();
		db.update(query);
		db.shutDown();
	}
	private String Chot(String id,String loaithuong,String userid)
	{
		dbutils db = new dbutils();
		try {
			db.getConnection().setAutoCommit(false);
			String sqlchot="";
			
			sqlchot = "select 1 from TieuChiTinhThuong where thang = (select thang from TieuChiTinhThuong where pk_seq =  '" + id + "') "+
					  " and nam = (select nam from TieuChiTinhThuong where pk_seq =  '" + id + "') and trangthai = '1' AND LOAI = '"+ loaithuong +"' ";
		System.out.println("sqlchot : "+ sqlchot);
			ResultSet rs = db.get(sqlchot);
			if(rs != null && rs.next())
			{
				db.getConnection().rollback();
				db.getConnection().setAutoCommit(true);
				return  "Đã có công thức thưởng cùng tháng và năm đã chốt rồi. Không được phép chốt !";
					
			}
			sqlchot = "UPDATE TieuChiTinhThuong SET TRANGTHAI ='1' WHERE PK_SEQ = '" + id + "'";

			if(!db.update(sqlchot))
			{
				db.getConnection().rollback();
				db.getConnection().setAutoCommit(true);
				db.shutDown();
			}
			else
			{
				db.getConnection().setAutoCommit(true);
				db.shutDown();
				
			}
			System.out.println(sqlchot);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return "";
		
	}
	
	private String Xoa(String id)
	{
		dbutils db = new dbutils();
		String query;
		try{
			
			query =" select count(*) as sl from TIEUCHITHUONG_CHITIET where PK_SEQ in ( select distinct TCTCT_FK from ChiTieuNhanVien_DDKD) and TIEUCHITINHTHUONG_FK= "+id;
			ResultSet rs=db.get(query);
			rs.next();
			int sl=rs.getInt("sl");
			rs.close();
			
			if(sl>0)
			{			
				return "Đã áp chỉ tiêu cho nhân viên không được xóa !! ";
			}
			
			query =" select count(*) as sl from TIEUCHITHUONG_CHITIET where PK_SEQ in ( select distinct TCTCT_FK from ChiTieuNhanVien_NPP) and TIEUCHITINHTHUONG_FK= "+id;
			 rs=db.get(query);
			rs.next();
			 sl=rs.getInt("sl");
			rs.close();
			
			if(sl>0)
			{			
				return "Đã áp chỉ tiêu cho nhân viên không được xóa !! ";
			}
			
			
			query = "SELECT LOAI FROM TIEUCHITINHTHUONG WHERE PK_SEQ = '" + id + "'";
			 rs = db.get(query);
			rs.next();
			String loai = rs.getString("LOAI");
			rs.close();
			
			query = "SELECT COUNT(*) AS NUM FROM TIEUCHITINHTHUONG WHERE LOAI='" + loai + "'";
			System.out.println("vao xoa "+query);
			rs = db.get(query);
			rs.next();
			//if(!rs.getString("num").equals("1"))
			{
				db.getConnection().setAutoCommit(false);
				
				query = "DELETE TIEUCHITHUONG_CHITIET WHERE TIEUCHITINHTHUONG_FK = '" + id + "'";
				
				if(!db.update(query))
				{
					db.getConnection().rollback();
					return "Lỗi trong qua trình xóa liên hệ admin để xử lý";
				}
				query = "DELETE b from TIEUCHITHUONG_CHITIET a inner join TieuChiThuong_ChiTiet_MucThuong b on a.pk_seq=b.tctct_fk WHERE TIEUCHITINHTHUONG_FK = '" + id + "'";
				if(!db.update(query))
				{
					db.getConnection().rollback();
					return "Lỗi trong qua trình xóa liên hệ admin để xử lý";
				}
				query = "DELETE b from TIEUCHITHUONG_CHITIET a inner join TieuChiThuong_ChiTiet_MucThuong_npp b on a.pk_seq=b.tctct_fk WHERE TIEUCHITINHTHUONG_FK = '" + id + "'";
				if(!db.update(query))
				{
					db.getConnection().rollback();
					return "Lỗi trong qua trình xóa liên hệ admin để xử lý";
				}
				query = "DELETE b from TIEUCHITHUONG_CHITIET a inner join TieuChiThuong_ChiTiet_MucThuong_ddkd b on a.pk_seq=b.tctct_fk WHERE TIEUCHITINHTHUONG_FK = '" + id + "'";
				if(!db.update(query))
				{
					db.getConnection().rollback();
					return "Lỗi trong qua trình xóa liên hệ admin để xử lý";
				}
				
				System.out.println(query);
			
				query = "DELETE TIEUCHITINHTHUONG WHERE PK_SEQ = '" + id + "'";
				if(!db.update(query))
				{
					db.getConnection().rollback();
					return "Lỗi trong qua trình xóa liên hệ admin để xử lý";
				}
				db.getConnection().commit();
				db.getConnection().setAutoCommit(true);
			
				System.out.println(query);
			}

			db.shutDown();
		}catch(Exception e){
			return "Lỗi trong qua trình xóa liên hệ admin để xử lý";
		}
		return  "";
	}
	
}
