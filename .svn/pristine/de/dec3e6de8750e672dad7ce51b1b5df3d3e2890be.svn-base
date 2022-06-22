package geso.dms.distributor.servlets.xoanoncc;

import geso.dms.center.db.sql.dbutils;
import geso.dms.center.util.Utility;
import geso.dms.distributor.beans.xoanoncc.IErpXoaNoNCC;
import geso.dms.distributor.beans.xoanoncc.IErpXoaNoNCCList;
import geso.dms.distributor.beans.xoanoncc.imp.ErpXoaNoNCC;
import geso.dms.distributor.beans.xoanoncc.imp.ErpXoaNoNCCList;
import java.io.IOException;
import java.sql.SQLException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class ErpXoaNoNCCSvl extends HttpServlet 
{
	private static final long serialVersionUID = 1L;
       
    public ErpXoaNoNCCSvl() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		IErpXoaNoNCCList obj;
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
	    
	    String tthdId = util.getId(querystring);
	    
	    obj = new ErpXoaNoNCCList();
	    
	    
	    if (action.equals("delete"))
	    {	
	    	String msg = Delete(tthdId);
	    	if(msg.length() > 0)
	    		obj.setmsg(msg);
	    }
	    else
	    {
	    	if(action.equals("chot"))
	    	{
	    		IErpXoaNoNCC tthd = new ErpXoaNoNCC(tthdId);
	    		if(!tthd.chotTTHD(userId))
	    		{
	    			obj.setmsg(tthd.getMsg());
	    		}
	    		tthd.DBclose();
	    	}
	    }
	    
	    obj.setUserId(userId);
	    obj.setCongtyId((String)session.getAttribute("congtyId"));
	    obj.setnppdangnhap(util.getIdNhapp(userId));
	    
	    obj.init("");
	    
		session.setAttribute("obj", obj);
				
		String nextJSP = request.getContextPath() + "/pages/Distributor/ErpXoaNoNCC.jsp";
		response.sendRedirect(nextJSP);
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		IErpXoaNoNCCList obj;
		request.setCharacterEncoding("UTF-8");
	    response.setCharacterEncoding("UTF-8");
	    response.setContentType("text/html; charset=UTF-8");
	    
	    String action = request.getParameter("action");
	    if (action == null){
	    	action = "";
	    }
	    
	    Utility util = new Utility();
	    
		HttpSession session = request.getSession();
	    String userId = util.antiSQLInspection(request.getParameter("userId")); 
	    
	    if(action.equals("Tao moi"))
	    {
	    	IErpXoaNoNCC tthdBean = new ErpXoaNoNCC();
	    	tthdBean.setUserId(userId);
	    	tthdBean.setCongtyId((String)session.getAttribute("congtyId"));
	    	tthdBean.setnppdangnhap(util.getIdNhapp(userId));
		    
	    	tthdBean.createRs();
    		
	    	session.setAttribute("tthdBean", tthdBean);

    		String nextJSP = request.getContextPath() + "/pages/Distributor/ErpXoaNoNCCNew.jsp";
    		response.sendRedirect(nextJSP);
	    }
	    else
	    {
	    	if(action.equals("view") || action.equals("next") || action.equals("prev"))
	    	{
	    		obj = new ErpXoaNoNCCList();
		    	
		    	
		    	obj.setNxtApprSplitting(Integer.parseInt(request.getParameter("nxtApprSplitting")));
		    	obj.setUserId(userId);
		    	obj.setCongtyId((String)session.getAttribute("congtyId"));
		    	obj.setnppdangnhap(util.getIdNhapp(userId));
		    	
		    	String search = getSearchQuery(request, obj);
		    	obj.init(search);
		    	obj.setAttribute(request, action, "list", "crrApprSplitting", "nxtApprSplitting");
		    	session.setAttribute("obj", obj);
		    	response.sendRedirect(request.getContextPath() + "/pages/Distributor/ErpXoaNoNCC.jsp");	
		    }
	    	else
	    	{
		    	obj = new ErpXoaNoNCCList();
		    	
		    	String search = getSearchQuery(request, obj);		    	
				obj.setUserId(userId);
				obj.setCongtyId((String)session.getAttribute("congtyId"));
		    	obj.setnppdangnhap(util.getIdNhapp(userId));
		    	obj.init(search);
		    	
		    	
		    	session.setAttribute("obj", obj);  	
	    		session.setAttribute("userId", userId);
		
	    		response.sendRedirect(request.getContextPath() + "/pages/Distributor/ErpXoaNoNCC.jsp");
	    	}
	    }
	}
	
	private String getSearchQuery(HttpServletRequest request, IErpXoaNoNCCList obj)
	{
		String 	query = "select a.pk_seq as tthdId, a.trangthai, a.ngaychungtu, a.ngayghiso, a.ngaytao, a.ngaysua," +
						" 		case when a.loaixoano = 0 then ncc.ten else nv.ten end  as tendoituong, " +
						" 		d.ten as nguoitao, e.ten as nguoisua " +
						" from ERP_XOANONCC a " +
						"     left join ERP_NHACUNGCAP ncc on a.NCC_FK= ncc.PK_SEQ  " +
						"     left join ERP_NHANVIEN nv on a.NHANVIEN_FK= nv.PK_SEQ  " +
						"     inner join NHANVIEN d on a.nguoitao = d.pk_seq inner join NHANVIEN e on a.nguoisua = e.pk_seq "+
						"where a.pk_seq > 0 and a.CONGTY_FK = "+obj.getCongtyId();
		
		String tungay = request.getParameter("tungay");
		if(tungay == null)
			tungay = "";
		obj.setTungay(tungay);
		
		String ngaychungtu = request.getParameter("ngaychungtu");
		if(ngaychungtu == null)
			ngaychungtu = "";
		obj.setNgayChungTu(ngaychungtu);
		
		String maphieu = request.getParameter("maphieu");
		if(maphieu == null)
			maphieu = "";
		obj.setMaPhieu(maphieu);
		
		String denngay = request.getParameter("denngay");
		if(denngay == null)
			denngay = "";
		obj.setDenngay(denngay);
		
		String nccId = request.getParameter("nccId");
		if(nccId == null)
			nccId = "";
		obj.setNccId(nccId);
		
		String nvId = request.getParameter("nvId");
		if(nvId == null)
			nvId = "";
		obj.setNvId(nvId);
		
		String trangthai = request.getParameter("trangthai");
		if(trangthai == null)
			trangthai = "";
		obj.setTrangthai(trangthai);
		
		
		if(tungay.length() > 0)
			query += " and a.ngaychungtu >= '" + tungay + "'";
		
		if(denngay.length() > 0)
			query += " and a.ngaychungtu <= '" + denngay + "'";
		
		if(ngaychungtu.length() > 0)
			query += " and a.ngaychungtu = '" + denngay + "'";
		
		if(maphieu.length() > 0)
			query += " and a.pk_seq like '%" + denngay + "%'";
		
		if(nccId.length() > 0)
			query += " and ncc.pk_seq = '" + nccId + "'";
		
		if(nvId.length() > 0)
			query += " and nv.pk_seq = '" + nvId + "'";
		
		if(trangthai.length() > 0)
			query += " and a.trangthai = '" + trangthai + "'";
		
		return query;
	}
	
	private String Delete(String tthdId)
	{
		dbutils db = new dbutils();
		
		try 
		{
			db.getConnection().setAutoCommit(false);
			
			db.update("update ERP_XOANONCC set trangthai = '2' where pk_seq = '" + tthdId + "'");
			
			db.getConnection().commit();
			db.getConnection().setAutoCommit(true);
			db.shutDown();
			
			return "";
		} 
		catch (SQLException e)
		{ 
			db.shutDown(); 
			return "Khong the xoa ERP_XOANONCC"; 
		}
		
	}

}
