package geso.dms.center.servlets.thanhthinongthon;

import geso.dms.center.beans.thanhthinongthon.*;
import geso.dms.center.beans.thanhthinongthon.imp.*;

import geso.dms.center.db.sql.dbutils;
import geso.dms.center.util.Utility;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import jxl.format.Alignment;
import jxl.format.Border;
import jxl.format.BorderLineStyle;
import jxl.format.Colour;
import jxl.format.VerticalAlignment;
import jxl.write.Label;
import jxl.write.Number;
import jxl.write.WritableCellFormat;
import jxl.write.WritableFont;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;


public class ThanhthinongthonSvl extends HttpServlet 
{
	private static final long serialVersionUID = 1L;
	
	//PrintWriter out;
	
    public ThanhthinongthonSvl()
    {
        super();
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		
		IThanhthinongthonList obj;
		
		request.setCharacterEncoding("UTF-8");
	    response.setCharacterEncoding("UTF-8");
	    response.setContentType("text/html; charset=UTF-8");
	    //this.out  = response.getWriter();
	    	    
	    HttpSession session = request.getSession();	    
	    obj = new ThanhthinongthonList();
	    Utility util = new Utility();
	    //out = response.getWriter();
	    	    
	    String querystring = request.getQueryString();
	    String userId = util.getUserId(querystring);
	    //out.println(userId);
	    
	    if (userId.length()==0)
	    	userId = util.antiSQLInspection(request.getParameter("userId"));
	    
	    String action = util.getAction(querystring);
	    //out.println(action);
	    
	    String kvId = util.getId(querystring);

	    if (action.equals("delete")){
	    	System.out.println(kvId);	   		  	    	
	    	Delete(kvId, obj);
	    }
	   	
	   
	    obj.setUserId(userId);
	    obj.init("");
		session.setAttribute("obj", obj);
				
		String nextJSP = request.getContextPath() + "/pages/Center/Thanhthinongthon.jsp";
		response.sendRedirect(nextJSP);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		
		IThanhthinongthonList obj = new ThanhthinongthonList();
		Utility util = new Utility();
		
		request.setCharacterEncoding("UTF-8");
	    response.setCharacterEncoding("UTF-8");
	    response.setContentType("text/html; charset=UTF-8");
	    //this.out = response.getWriter();
	    
		HttpSession session = request.getSession();
	    String userId = util.antiSQLInspection(request.getParameter("userId"));
	    
	    String action = request.getParameter("action");
	    if (action == null){
	    	action = "";
	    }
	    //out.println(action); 
	        
	    
	    if (action.equals("new")){
	    	// Empty Bean for distributor
	    	IThanhthinongthon kvBean = (IThanhthinongthon) new Thanhthinongthon("");
	    	kvBean.setUserId(userId);
	    	// Save Data into session
	    	session.setAttribute("kvBean", kvBean);
    		
    		String nextJSP = request.getContextPath() + "/pages/Center/ThanhthinongthonUpdate.jsp";
    		response.sendRedirect(nextJSP);
    		
	    }

		/*else if (action.equals("excel")) {
			obj.init(getSearchQuery(request,obj));
			ToExcel(response, obj);
		} */
	    
	    else if (action.equals("search")){
	    	String search = getSearchQuery(request,obj);
	    	
	    	//obj = new KhuvucList(search);
	    	obj.init(search);
			obj.setUserId(userId);
			
	    	session.setAttribute("obj", obj);  	
    		session.setAttribute("userId", userId);
    		session.setAttribute("abc", search);
	    		
    		response.sendRedirect(request.getContextPath() + "/pages/Center/Thanhthinongthon.jsp");	    	
	    	
	    }
	    else if (action.equals("refresh"))
	    {
	    	obj.init("");
			obj.setUserId(userId);
			
	    	session.setAttribute("obj", obj);  	
    		session.setAttribute("userId", userId);
    		session.setAttribute("abc", "");
	    		
    		response.sendRedirect(request.getContextPath() + "/pages/Center/Thanhthinongthon.jsp");
	    }
	    
	}
	
	private String getSearchQuery(HttpServletRequest request,IThanhthinongthonList obj){
		   // PrintWriter out = response.getWriter();
			
		//IKhuvucList obj = new KhuvucList();
		
		Utility util = new Utility();

	    	
	    	String trangthai = util.antiSQLInspection(request.getParameter("TrangThai"));   	
	    	if (trangthai == null)
	    		trangthai = "";    	
		
	   if (trangthai.equals("2"))
	  	trangthai = "";
	    	
	    	obj.setTrangthai(trangthai);

			
			String query = "select a.pk_seq as id, a.ten as ttntTen, a.trangthai as trangthai, c.ten as nguoitao, a.ngaytao as ngaytao, d.ten as nguoisua, a.ngaysua as ngaysua, a.diengiai";
			query = query + " from ThanhThiNongThon a, nhanvien c, nhanvien d";
			query = query + " where a.nguoitao = c.pk_seq and a.nguoisua = d.pk_seq";

	  
	    	if(trangthai.length() > 0){
	    		query = query + " and a.trangthai = '" + trangthai + "'";	
	    	}
	    	query = query + " order by a.ten";
	    	System.out.println("cau lenh:"+ query);
	    	return query;
		}	
	boolean kiemtra(String sql)
	{dbutils db =new dbutils();
    	ResultSet rs = db.get(sql);
		try {//kiem tra ben san pham
		while(rs.next())
		{ if(rs.getString("num").equals("0"))
		   return true;
		}
		} catch(Exception e) {
		
			e.printStackTrace();
		}
		 return false;
	}

	private void Delete(String thanhthinongthon_fk, IThanhthinongthonList obj)
	{
		try
		{
			if(thanhthinongthon_fk==null || thanhthinongthon_fk.trim().length()<0)
			{
				obj.setMsg("Không có mã thành thị nông thôn nào để xóa");
				return;
			}

			dbutils db = new dbutils();
			String sql = "select COUNT(*) as num from KHACHHANG where thanhthinongthon_fk='" + thanhthinongthon_fk + "' ";
			System.out.println("ThanhthinongthonSvl-Delete(): " + sql);
			ResultSet rs = db.get("num");
			if(rs!=null){
				rs.next();
				if(rs.getInt("num") > 0)
				{
					obj.setMsg("Tồn tại khách hàng ứng với thành thị nông thôn này");
					return;
				}
					
			}
			
			sql = "delete from ThanhThiNongThon where PK_SEQ='" + thanhthinongthon_fk + "' ";
			System.out.println("ThanhthinongthonSvl-Delete(): " + sql);
			if(!db.update(sql))
			{
				obj.setMsg("Không thể xóa được thành thị nông thôn này");
				return;
			}
			
			rs.close();
			db.shutDown();
		}
		catch(Exception e)
		{}
		
	}




	private String getDateTime()
	{
		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		Date date = new Date();
		return dateFormat.format(date);
	}
}
