package geso.dms.distributor.servlets.hopdong;

import geso.dms.center.db.sql.dbutils;
import geso.dms.center.util.Utility;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Enumeration;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.oreilly.servlet.MultipartRequest;



public class AjaxGhiChuHopDongSvl extends HttpServlet 
{
	private static final long serialVersionUID = 1L;
       
    public AjaxGhiChuHopDongSvl() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		System.out.println("DOGET");
		
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		String contentType = request.getContentType();
		
		
		
		System.out.println("DOPOST");
		Utility util = new Utility();
		HttpSession session = request.getSession();
		
		String userId = (String)session.getAttribute("userId");
		
		String fullName= "";
		String id = "",loaighichu = "",ngayghichu = "",noidungghichu = "";
		if ((contentType != null) && (contentType.indexOf("multipart/form-data") >= 0))
		{
			System.out.println(" co upload file excel hahahaaahahaha");
			
			String folderupload= getServletContext().getInitParameter("ghichuPath");
			MultipartRequest multi = new MultipartRequest(request, folderupload, 20000000, "UTF-8");
			id=util.antiSQLInspection(multi.getParameter("id"));
			System.out.println("id =" + id);
			if (id == null || id.trim().length()==0)
				return ;
			
			loaighichu=util.antiSQLInspection(multi.getParameter("loaighichu"));
			System.out.println("loaighichu1 = " + loaighichu);
			if (loaighichu == null)
				loaighichu = "";
			
			ngayghichu=util.antiSQLInspection(multi.getParameter("ngayghichu"));
			if (ngayghichu == null)
				ngayghichu = "";
			noidungghichu=util.antiSQLInspection(multi.getParameter("noidungghichu"));
			if (noidungghichu == null)
				noidungghichu = "";
			noidungghichu  = noidungghichu.trim();
			 
			Enumeration files = multi.getFileNames();
			String filenameu = "";
			while (files.hasMoreElements())
			{
				String name = (String) files.nextElement();
				filenameu = multi.getFilesystemName(name);
				System.out.println("name  " + name);
				
				File f=new File(folderupload+filenameu);
				
				fullName = userId+"_"+id+"_"+getCurrentTimeStamp()+"___"+filenameu;
				f.renameTo(new File (fullName));
				
			}
			
		}
		else
		{
	   
		
			id=util.antiSQLInspection(request.getParameter("id"));
			System.out.println("id =" + id);
			if (id == null || id.trim().length()==0)
			{				
				return ;
			}
			 loaighichu=util.antiSQLInspection(request.getParameter("loaighichu"));
			if (loaighichu == null)
				loaighichu = "";
			 ngayghichu=util.antiSQLInspection(request.getParameter("ngayghichu"));
			if (ngayghichu == null)
				ngayghichu = "";
			 noidungghichu=util.antiSQLInspection(request.getParameter("noidungghichu"));
			if (noidungghichu == null)
				noidungghichu = "";
		}
		
		System.out.println("loaighichu " + loaighichu  + " -- ngayghichu "  + ngayghichu) ;
		System.out.println("userId " + userId  + " -- noidungghichu "  + noidungghichu) ;
		
		dbutils db = new dbutils();
		
		String msg = LuuData( db, id,loaighichu,  noidungghichu ,  ngayghichu,fullName );
		System.out.println("msg: " + msg  ) ;
		
		String query =  " select loaighichu,ghichu,ngay,isnull(linkFile,'')linkFile from erp_hopdongnpp_ghichu where hopdong_fk =   " + id + " order by thoidiem "; 
		
		ResultSet rs = db.get(query);
		
		JsonArray jsonArray = new JsonArray();
		NumberFormat formatter = new DecimalFormat("#,###,###.##");
		if(rs != null){
			try {
				while(rs.next()){
					JsonObject obj = new JsonObject();
					obj.addProperty("loaighichu", rs.getString("loaighichu"));
					obj.addProperty("ghichu", rs.getString("ghichu"));
					obj.addProperty("ngay", rs.getString("ngay"));
					obj.addProperty("linkFile",rs.getString("linkFile"));
					
					jsonArray.add(obj);
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		response.setContentType("application/json");
		response.setCharacterEncoding("UTF-8");
		PrintWriter out = response.getWriter();
	    out.print(jsonArray);
		
	}
	
	public String LuuData(dbutils db,String id,String LoaiGhiChu, String ghichuLine , String ngayghichu, String linkFile )
	{
		String query = "";
		try
		{
			db.getConnection().setAutoCommit(false);
			String ghichu = "";
			query = " insert erp_hopdongnpp_ghichu (linkFile,hopdong_fk ,LoaiGhiChu ,ghichu,ngay,stt) " +
			" select  N'"+linkFile+"',"+id+",N'"+LoaiGhiChu+"',N'"+ghichuLine+"', '"+ngayghichu+"', isnull( (select max(stt) from erp_hopdongnpp_ghichu where hopdong_fk = "+id+"  )  ,0) ";
			if(!db.update(query))
			{				
				db.getConnection().rollback();
				db.getConnection().setAutoCommit(true);
				return "Khong the tao moi erp_hopdongnpp_ghichu: " + query;
			}
			db.getConnection().commit();
			db.getConnection().setAutoCommit(true);
			return "";
			
			
		}
		catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			try {
				db.getConnection().rollback();
				db.getConnection().setAutoCommit(true);
				return "ex:" + e.getMessage(); 
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
				return "ex:" + e1.getMessage(); 
			}
			
		}
		
	}

	public String getCurrentTimeStamp() {
	    return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS").format(new Date()).replaceAll("-","").replaceAll(" ","").replaceAll(":","").replaceAll(".","");
	}
}