package geso.dms.erp.servlets.lenhsanxuat;

import geso.dms.erp.db.sql.dbutils;
import geso.dms.center.util.Utility;
import geso.dms.erp.beans.lenhsanxuat.IErpYeucaunguyenlieu;
import geso.dms.erp.beans.lenhsanxuat.IErpYeucaunguyenlieuList;
import geso.dms.erp.beans.lenhsanxuat.imp.ErpYeucaunguyenlieu;
import geso.dms.erp.beans.lenhsanxuat.imp.ErpYeucaunguyenlieuList;
import java.io.IOException;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.naming.NoInitialContextException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class ErpYeucaunguyenlieuSvl extends HttpServlet 
{
	private static final long serialVersionUID = 1L;
       
    public ErpYeucaunguyenlieuSvl() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		IErpYeucaunguyenlieuList obj;
		request.setCharacterEncoding("UTF-8");
	    response.setCharacterEncoding("UTF-8");
	    response.setContentType("text/html; charset=UTF-8");
	    	    
	    HttpSession session = request.getSession();	    

	    Utility util = new Utility();
	    	    
	    String querystring = request.getQueryString();
	    String userId = util.getUserId(querystring);
	    
	    if (userId.length()==0)
	    	userId = util.antiSQLInspection(request.getParameter("userId"));
	    
	   
	    
	    String action = util.getAction(querystring);
	    
	    String lsxId = util.getId(querystring);
	    
	    String loaiyeucau=util.getAction(querystring);
	    
	    
	   
	    obj = new ErpYeucaunguyenlieuList();
	    if (action.equals("delete"))
	    {	
	    	dbutils db = new dbutils();
	    	try {
	    		boolean flag = true;
	    		db.getConnection().setAutoCommit(false);
	    		
	    		if(flag && !db.update("delete ERP_YCCHUYENKHO_SANPHAM where ycchuyenkho_fk = '" + lsxId + "'")) {
	    			db.getConnection().rollback();
	    			flag = false;
	    		}
	    		if(flag && !db.update("delete ERP_YCCHUYENKHO_SANPHAM_CHITIET where ycchuyenkho_fk = '" + lsxId + "'")) {
	    			db.getConnection().rollback();
	    			flag = false;
	    		}
		    	if(flag && !db.update("delete ERP_YCCHUYENKHO where pk_seq = '" + lsxId + "'")) {
	    			db.getConnection().rollback();
	    			flag = false;
		    	}
		    	if(flag) {
			    	db.getConnection().commit();
		    		db.getConnection().setAutoCommit(true);
		    	}
	    	} catch(Exception e) {
	    		e.printStackTrace();
	    		try {
					db.getConnection().rollback();
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
	    	}
	    	db.shutDown();
	    }
	    else
	    {
	    	if(action.equals("chot"))
	    	{
	    		dbutils db = new dbutils();
		    	db.update("update ERP_YEUCAUNGUYENLIEU set trangthai = '1' where pk_seq = '" + lsxId + "'");
		    	db.shutDown();
	    	}
	    }
	    
	    String task = request.getParameter("task");
		if(task == null)
			task = "";
		if(task.equals("chuyenNL"))
			obj.setIschuyenNL("1");
		obj.setNoidungxuat(loaiyeucau);
	    obj.setUserId(userId);
	    obj.init("");
	    
		session.setAttribute("obj", obj);
		String nextJSP = "/SalesUp/pages/Erp/ErpYeuCauNguyenLieu.jsp";
		if(task.equals("chuyenNL"))
		{
			nextJSP = "/SalesUp/pages/Erp/ErpChuyenNguyenLieu.jsp";
		}
		response.sendRedirect(nextJSP);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		request.setCharacterEncoding("UTF-8");
	    response.setCharacterEncoding("UTF-8");
	    response.setContentType("text/html; charset=UTF-8");
	    
	    String action = request.getParameter("action");
	    if (action == null)
	    {
	    	action = "";
	    }
	    
	    
	    String noidungxuat = request.getParameter("noidungxuat");
	    if (noidungxuat == null)
	    {
	    	noidungxuat = "";
	    }
	    
	    
	  
		IErpYeucaunguyenlieuList obj = new ErpYeucaunguyenlieuList();
		obj.setNoidungxuat(noidungxuat);
	    String task = request.getParameter("task");
		if(task == null)
			task = "";
		if(task.equals("chuyenNL"))
			obj.setIschuyenNL("1");
	    
	    Utility util = new Utility();
	    
		HttpSession session = request.getSession();
	    String userId = util.antiSQLInspection(request.getParameter("userId")); 
	    
	    if(action.equals("Tao moi"))
	    {
	    	IErpYeucaunguyenlieu lsxBean = new ErpYeucaunguyenlieu();
	    	lsxBean.setNoidungxuat(noidungxuat);
	    	lsxBean.createRs();
	    	session.setAttribute("lsxBean", lsxBean);
	    	
    		String nextJSP = "/SalesUp/pages/Erp/ErpYeuCauNguyenLieuNew.jsp";
    		response.sendRedirect(nextJSP);
	    }
	    else
	    {
	    	if(action.equals("view") || action.equals("next") || action.equals("prev"))
	    	{
	    		//System.out.println("toi day");
		    	String search = getSearchQuery(request, obj);
		    	obj.setNxtApprSplitting(Integer.parseInt(request.getParameter("nxtApprSplitting")));
		    	obj.setUserId(userId);
		    	obj.init(search);
		    	obj.setAttribute(request, action, "list", "crrApprSplitting", "nxtApprSplitting");
		    	session.setAttribute("obj", obj);
		    	
		    	if(task.equals("chuyenNL"))
		    	{
		    		response.sendRedirect("/SalesUp/pages/Erp/ErpChuyenNguyenLieu.jsp");	
		    	}
		    	else
		    	{
		    		response.sendRedirect("/SalesUp/pages/Erp/ErpYeuCauNguyenLieu.jsp");	
		    	}
		    }
	    	else
	    	{
		    	String search = getSearchQuery(request, obj);
		    	obj.init(search);
				obj.setUserId(userId);
				
		    	session.setAttribute("obj", obj);  	
	    		session.setAttribute("userId", userId);
		
	    		if(task.equals("chuyenNL"))
		    	{
		    		response.sendRedirect("/SalesUp/pages/Erp/ErpChuyenNguyenLieu.jsp");	
		    	}
		    	else
		    	{
		    		response.sendRedirect("/SalesUp/pages/Erp/ErpYeuCauNguyenLieu.jsp");	
		    	}
	    	}
	    }
	}
	
	private String getSearchQuery(HttpServletRequest request, IErpYeucaunguyenlieuList obj)
	{
		String query = " select  isnull(MUAHANG_FK, isnull( (select top 1 lenhsanxuat_fk from ERP_YCCHUYENKHO_LSX where ycchuyenkho_fk=a.pk_seq),0) ) as Po_number " +
				" ,ISNULL(DACHUYENHANG,0) AS DACHUYENHANG   ,a.PK_SEQ, a.trangthai, " +
				" a.ngaychuyen as ngayyeucau, a.lydo, NV.TEN as nguoitao, a.NGAYSUA, a.NGAYTAO, NV2.TEN as nguoisua   " +
				" from ERP_YCCHUYENKHO a   " +
				" inner join NHANVIEN nv on a.NGUOITAO = nv.PK_SEQ   " +
				" inner join NHANVIEN nv2 on a.NGUOISUA = nv2.PK_SEQ  " +
				" where a.noidungxuat_fk = '" + obj.getNoidungxuat() + "'   ";		
		if(obj.getIschuyenNL().equals("1"))
			query += " and a.trangthai != 3 ";
		
		String tungaySX = request.getParameter("tungaySX");
		if(tungaySX == null)
			tungaySX = "";
		obj.setTungayTao(tungaySX);
		
		String denngaySX = request.getParameter("denngaySX");
		if(denngaySX == null)
			denngaySX = "";
		obj.setDenngayTao(denngaySX);
		
		String trangthai = request.getParameter("trangthai");
		if(trangthai == null)
			trangthai = "";
		obj.setTrangthai(trangthai);
		
		if(tungaySX.length() > 0)
			query += " and a.NgayChuyen >= '" + tungaySX + "'";
		
		if(denngaySX.length() > 0)
			query += " and a.NgayChuyen <= '" + denngaySX + "'";
		
		if(trangthai.length() > 0)
			query += " and a.TrangThai = '" + trangthai + "'";
		
		return query;
	}
		
	public String getDateTime()
	{
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date date = new Date();
        return dateFormat.format(date);	
	}
	
	
}
