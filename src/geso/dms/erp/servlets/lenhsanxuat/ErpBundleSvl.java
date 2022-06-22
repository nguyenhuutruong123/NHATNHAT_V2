package geso.dms.erp.servlets.lenhsanxuat;

import geso.dms.erp.beans.lenhsanxuat.*;
import geso.dms.erp.beans.lenhsanxuat.imp.*;
import geso.dms.center.db.sql.dbutils;
import geso.dms.center.util.Utility;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class ErpBundleSvl extends HttpServlet 
{
	private static final long serialVersionUID = 1L;
	PrintWriter out;
	
    public ErpBundleSvl() {
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
	    
	    IErpBundleList obj = new ErpBundleList();
	    String ctyId = (String)session.getAttribute("congtyId");
	    obj.setUserId(userId);
	    obj.setCongtyId(ctyId);
	    
	    String action = util.getAction(querystring);
	    String khlId = util.getId(querystring);
	    String msg = "";
	    
	    if(action.trim().equals("delete"))
	    {
	    	dbutils db = new dbutils();
	    	if(!db.update("update Erp_NguonGoc set trangthai = '0' where pk_seq = '" + khlId + "'"))
	    	{
	    		msg = "Không thể xóa Erp_NguonGoc";
	    	}
	    	db.shutDown();
	    }
	    
	    obj.init("");
	    obj.setMsg(msg);
		session.setAttribute("obj", obj);
	    
	    String nextJSP = request.getContextPath() + "/pages/Erp/ErpBundle.jsp";
		response.sendRedirect(nextJSP);
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
	    String ctyId = (String)session.getAttribute("congtyId");
	    
	    
	    IErpBundleList obj;
	    
		String action = request.getParameter("action");
	    if (action == null){
	    	action = "";
	    }
	    
	    if(action.equals("new"))
	    {
    		IErpBundle khl = new ErpBundle();
    		khl.setUserId(userId);
    		
    		khl.createRs();
    		
	    	session.setAttribute("csxBean", khl);  	
    		session.setAttribute("userId", userId);
		
    		response.sendRedirect(request.getContextPath() + "/pages/Erp/ErpBundleNew.jsp");
	    }
	    else
	    {
	    	obj = new ErpBundleList();
	    	obj.setCongtyId(ctyId);
		    obj.setUserId(userId);

	    	String search = getSearchQuery(request, obj);
	    	
	    	obj.setUserId(userId);
	    	obj.init(search);
				
	    	session.setAttribute("obj", obj);  	
    		session.setAttribute("userId", userId);
		
    		response.sendRedirect(request.getContextPath() + "/pages/Erp/ErpBundle.jsp");	
	    }
	}
	
	private String getSearchQuery(HttpServletRequest request, IErpBundleList obj) 
	{
		Utility util = new Utility();
		
		String sochungtu = request.getParameter("sochungtu");
		if(sochungtu == null)
			sochungtu = "";
		obj.setSochungtu(sochungtu);
		
		String diengiai = util.antiSQLInspection(request.getParameter("diengiai"));
		if(diengiai == null)
			diengiai = "";
		obj.setDiengiai(diengiai);
		
		String tungay = util.antiSQLInspection(request.getParameter("tungay"));
    	if (tungay == null)
    		tungay = "";    	
    	obj.setTungay(tungay);

    	String denngay = util.antiSQLInspection(request.getParameter("denngay"));
    	if (denngay == null)
    		denngay = "";    	
    	obj.setDenngay(denngay);
		
		
		String  sql = "select a.pk_seq, a.ngaythuchien, d.ten as spTen, a.soluong, a.trangthai, b.ten as nguoitao, a.ngaytao, c.ten as nguoisua, a.ngaysua    " +
		  				" from Erp_BUNDLE a " +
		  				" inner join NhanVien b on a.nguoitao = b.pk_seq    " +
		  				" inner join nhanvien c on a.nguoisua = c.pk_seq " +
		  				" inner join SanPham d on a.sanpham_fk = d.pk_seq " +
		  				" where a.pk_seq > 0   ";
		
		
		if(diengiai.length() > 0)
			//sql += " and a.diengiai like N'%" + diengiai + "%' ";
			sql += " AND UPPER(d.ten) LIKE UPPER(N'%" + diengiai + "%') OR UPPER(d.ma) LIKE UPPER(N'%" + diengiai + "%')";
		
		if (tungay.length()>0){
			sql = sql + " and a.ngaythuchien >= '" + tungay+ "'";
    	}

    	if (denngay.length()>0){
    		sql = sql + " and a.ngaythuchien <= '" + denngay+ "'";
    	}
		
    	if(sochungtu.length() > 0)
    		sql += " and a.pk_seq like N'%" + sochungtu + "%'";
    	
		sql += " order by a.ngaythuchien desc ";
		
		return sql;
	}
	
	

}
