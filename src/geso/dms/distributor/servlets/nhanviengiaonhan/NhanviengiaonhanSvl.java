package geso.dms.distributor.servlets.nhanviengiaonhan;

import geso.dms.distributor.beans.nhanviengiaonhan.INhanviengiaonhan;
import geso.dms.distributor.beans.nhanviengiaonhan.INhanviengiaonhanList;
import geso.dms.distributor.beans.nhanviengiaonhan.imp.Nhanviengiaonhan;
import geso.dms.distributor.beans.nhanviengiaonhan.imp.NhanviengiaonhanList;
import geso.dms.distributor.db.sql.dbutils;
import geso.dms.distributor.util.Utility;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class NhanviengiaonhanSvl extends HttpServlet 
{
	private static final long serialVersionUID = 1L;
	
	
    public NhanviengiaonhanSvl() 
    {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		
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
		String userId = (String) session.getAttribute("userId");  
		String userTen = (String) session.getAttribute("userTen");  	
		String sum = (String) session.getAttribute("sum");
		geso.dms.center.util.Utility cutil = (geso.dms.center.util.Utility) session.getAttribute("util");
		if(!cutil.check(userId, userTen, sum)){
			response.sendRedirect(request.getContextPath() + "/redirect.jsp");
		}else{
		
		INhanviengiaonhanList obj;
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
	    
	    String nvgnId = util.getId(querystring);

	    /*if (action.equals("delete")){	   		  	    	
	    	Delete(nvgnId);
	    	out.print(nvgnId);
	    }
	   	    
	    obj = new NhanviengiaonhanList();*/
	    
	    String msg = "";
	    if (action.equals("delete")){	   		  	    	
	    	msg=Delete(nvgnId);
	    	out.print(nvgnId);
	    }
	   	    
	    obj = new NhanviengiaonhanList();
	    if(msg.trim().length()>0){
	   		obj.setMsg(msg);
	   	}
	    obj.setUserId(userId);
	    obj.init("");
	    
		session.setAttribute("obj", obj);
				
		String nextJSP = request.getContextPath() + "/pages/Distributor/NhanVienGiaoNhan.jsp";
		response.sendRedirect(nextJSP);
		
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		
		INhanviengiaonhanList obj  = new NhanviengiaonhanList();
		PrintWriter out; 
		String userId;
		
		request.setCharacterEncoding("UTF-8");
	    response.setCharacterEncoding("UTF-8");
	    response.setContentType("text/html; charset=UTF-8");
	    out = response.getWriter();
	    Utility util = new Utility();
		HttpSession session = request.getSession();
		geso.dms.center.util.Csrf csdr=new geso.dms.center.util.Csrf(request,response,false,true ,false);
		if(!csdr.__validate_post())
		{
			response.sendRedirect(request.getContextPath() + "/redirect.jsp");
			return;
		}
		
	    userId = util.antiSQLInspection(request.getParameter("userId"));
	    
	    String action = request.getParameter("action");
	    if (action == null){
	    	action = "";
	    }
	    out.println(action); 
	          
	    if (action.equals("new"))
	    {
	    	// Empty Bean for distributor
	    	INhanviengiaonhan nvgnBean = (INhanviengiaonhan) new Nhanviengiaonhan("");
	    	nvgnBean.setUserId(userId);
	    	nvgnBean.createRS();
	    	
	    	// Save Data into session
	    	session.setAttribute("nvgnBean", nvgnBean);
    		
    		String nextJSP = request.getContextPath() + "/pages/Distributor/NhanVienGiaoNhanNew.jsp";
    		response.sendRedirect(nextJSP);
    		
	    }
	    else
	    {
	    	String search = this.getSearchQuery(request, obj);
	    	
	    	obj.setUserId(userId);
	    	obj.init(search);
				
	    	session.setAttribute("obj", obj);  	
    		session.setAttribute("userId", userId);
	    		
    		response.sendRedirect(request.getContextPath() + "/pages/Distributor/NhanVienGiaoNhan.jsp");	    		    	
	    }
	}
	
	private String getSearchQuery(HttpServletRequest request, INhanviengiaonhanList obj) 
	{	
		Utility util = new Utility();
		String nppId = util.antiSQLInspection(request.getParameter("nppId"));
    	if ( nppId == null)
    		nppId = "";
    	obj.setNppId(nppId);
    	
		String nvgnTen = util.antiSQLInspection(request.getParameter("nvgnTen"));
    	if ( nvgnTen == null)
    		nvgnTen = "";
    	obj.setTennv(nvgnTen);
    	
    	String ddkdId = util.antiSQLInspection(request.getParameter("ddkdTen"));
    	if (ddkdId == null)
    		ddkdId = "";    	
    	obj.setDdkdId(ddkdId);
    	
    	String trangthai = util.antiSQLInspection(request.getParameter("trangthai")); 	
    	if (trangthai == null)
    		trangthai = "";    		
    	if (trangthai.equals("2"))
    		trangthai = "";   	
    	obj.setTrangthai(trangthai);
    	    	
    	String query =  "select a.pk_seq as nvgnId, a.ten as nvgnTen, a.trangthai, a.diachi, a.npp_fk as nppId, a.ngaytao, a.ngaysua, b.ten as nguoitao, c.ten as nguoisua, a.dienthoai ";
		query = query + "from nhanviengiaonhan a inner join nhanvien b on a.nguoitao = b.pk_seq inner join nhanvien c on a.nguoisua = c.pk_seq where a.npp_fk='" + nppId + "' ";
    	if (nvgnTen.length() > 0)
    	{ 
			query = query + " and  upper(dbo.ftBoDau(a.ten)) like  upper(N'%" + util.replaceAEIOU(nvgnTen) + "%')";		
    	}
    	
    	if (ddkdId.length() > 0)
    	{
			//query = query + " and exists (select * from ddkd_nvgn b  where  b.ddkd_fk = '" + ddkdId + "' and b.nvgn_fk=a.pk_Seq )";		
			query=query +" and  exists (select * from TUYENBANHANG aa inner join KHACHHANG_TUYENBH bb on aa.PK_SEQ=bb.TBH_FK \n"+
					"	inner join NVGN_KH cc on cc.KHACHHANG_FK=bb.KHACHHANG_FK\n"+
					"	where aa.DDKD_FK='" + ddkdId + "' and cc.NVGN_FK=a.pk_Seq)";
    	}
    	
    	
    	
    	if (trangthai.length() > 0)
    	{
    		query = query + " and a.trangthai='" + trangthai + "'";
    	}
    	  	
    	query+=" order by a.pk_seq";
    	System.out.println("\nQuery search fina: "+query);		
    	
    	
    	return query;
	}

	/*private void Delete(String nvgnId) 
	{
		dbutils db = new dbutils(); 
		
		db.update("delete from NVGN__KH where nvgn_fk='" + nvgnId + "'");
		db.update("delete from nvgn_tinhthanh where nvgn_fk='" + nvgnId + "'");
		db.update("delete from nvgn_tuyenbanhang where nvgn_fk='" + nvgnId + "'");
		db.update("delete from nvgn_quanhuyen where nvgn_fk='" + nvgnId + "'");
		db.update("delete from ddkd_nvgn where nvgn_fk='" + nvgnId + "'");
		
		db.update("delete from nhanviengiaonhan where pk_seq='" + nvgnId + "'");
		db.update("commit");
		db.shutDown();
	}*/
	
	private String Delete(String nvgnId) 
	{
		dbutils db = new dbutils(); 
		try {
			db.getConnection().setAutoCommit(false);
			String msg = "";
			String query = "";
			
			query = "select count(*) as sodem from donhang where nvgn_fk = '" + nvgnId + "'";
			ResultSet rs = db.get(query);
			int sodem = 0;
			if(rs.next()){
				sodem = rs.getInt("sodem");
			}
			rs.close();
			if(sodem >0){
				db.getConnection().rollback(); 
				msg = "Nhân viên giao nhận có tồn tại đơn hàng chưa chốt, vui lòng kiểm tra lại trước khi xóa!";
				return msg; 
			}
			query = "delete from NVGN_KH where nvgn_fk='" + nvgnId + "'";
			if(!db.update(query) ){
				db.getConnection().rollback(); 
				msg = "1.Khong the xoa nhanviengiaonhan: " + query;
				return msg; 
			}
			query = "update nhanviengiaonhan set trangthai=0,nguoisua=null,ngaysua=convert(varchar(10),getdate(),120) where pk_seq='" + nvgnId + "'";
			if(!db.update(query) ){
				db.getConnection().rollback(); 
				msg = "6.Không thể xóa, hoặc nhân viên giao nhận này đã được xóa, vui lòng kiểm tra lại!";
				return msg; 
			}
			db.getConnection().commit();
		} catch (Exception e) {
			e.printStackTrace();
			try {
				db.getConnection().rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
			return "lỗi exception: " + e.getMessage();
		}finally{
			try {
				db.getConnection().setAutoCommit(true);
			} catch (SQLException e) {
				e.printStackTrace();
			}
			if(db!=null) db.shutDown();
		}
		return "";
	}

}
