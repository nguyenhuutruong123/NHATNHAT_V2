package geso.dms.distributor.servlets.hoadonphelieu;

import geso.dms.distributor.beans.hoadonphelieu.*;
import geso.dms.distributor.beans.hoadonphelieu.imp.*;
import geso.dms.distributor.util.Utility;
import geso.dms.distributor.db.sql.dbutils;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.ResultSet;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class ErpHoadonphelieuSvl extends HttpServlet 
{
	private static final long serialVersionUID = 1L;
	PrintWriter out;
	
    public ErpHoadonphelieuSvl() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		request.setCharacterEncoding("UTF-8");
	    response.setCharacterEncoding("UTF-8");
	    response.setContentType("text/html; charset=UTF-8");
	    this.out  = response.getWriter();
	    
	    HttpSession session = request.getSession();	
	    geso.dms.center.util.Utility cutil = (geso.dms.center.util.Utility) session.getAttribute("util");
	    
	    String userId = (String) session.getAttribute("userId");  
		String userTen = (String) session.getAttribute("userTen");  	
		String sum = (String) session.getAttribute("sum");
	    
	    if(!Utility.val_doget(session, request, response))
		{
			session.setAttribute("flag",null);
			response.sendRedirect(request.getContextPath() + "/redirect.jsp");
			return;
		}
		else
		{
			session.setAttribute("flag",null);
		}
	    
	    if(!cutil.check(userId, userTen, sum)){
			response.sendRedirect(request.getContextPath() + "/redirect.jsp");
		}
	    else{
	    	Utility util = new Utility();
		    out = response.getWriter();
		    	    
		    String querystring = request.getQueryString();
		    userId = util.getUserId(querystring);
		    out.println(userId);
		    
		    if (userId.length() == 0)
		    	userId = util.antiSQLInspection(request.getParameter("userId"));
		    
		    IErpHoadonphelieuList obj = new ErpHoadonphelieuList();
		    String ctyId = (String)session.getAttribute("congtyId");
		    obj.setUserId(userId);
		    obj.setCongtyId(ctyId);
		    
		    String action = util.getAction(querystring);
		    String khlId = util.getId(querystring);
		    String msg = "";
		    
		    if(action.trim().equals("delete"))
		    {
		    	dbutils db = new dbutils();
		    	if(!db.update("update erp_hoadonphelieu set trangthai = '2' where pk_seq = '" + khlId + "'"))
		    	{
		    		msg = "Không thể xóa erp_hoadonphelieu";
		    	}
		    	db.shutDown();
		    }
		    
		    if(action.trim().equals("chot"))
		    {
		    	msg = ChotHoaDon(khlId);
		    	
		    }
		    
		    obj.init("");
		    obj.setMsg(msg);
			session.setAttribute("obj", obj);
		    
		    String nextJSP = request.getContextPath() + "/pages/Distributor/ErpHoaDonPheLieu.jsp";
			response.sendRedirect(nextJSP);
	    }
	    
	    
	}

	private static String ChotHoaDon(String Id)
	{
		String msg = "";
		dbutils db = new dbutils();
		Utility util = new Utility();
		util.Check_Huy_NghiepVu_KhoaSo("ERP_HOADONPHELIEU", Id, "ngayghinhan", db);
		if(msg.length()>0)
		{
			db.shutDown();
			return msg;
		}
    	try 
    	{
				db.getConnection().setAutoCommit(false);			
				String query = "update erp_hoadonphelieu set trangthai = '1' where pk_seq = '" + Id + "'";
				if(!db.update(query))
				{
					db.getConnection().rollback();
					msg = "Không thể cập nhật erp_hoadonphelieu";
				  return msg;
				}
				db.getConnection().commit();
				db.shutDown();
    	} 
    	catch (Exception e) 
    	{
    		e.printStackTrace();
    		db.update("rollback");
    		return "Có lỗi phát sinh "+ e.getMessage();
    	}
    	finally
    	{
    		db.shutDown();
    	}
		return msg;
	}
	
	public  static void main(String[] args) {
		String[] ids = new String[] {"100001","100002","100003","100004","100005","100006","100007","100008","100010","100011","100012","100013","100014","100015","100016","100017","100018","100018","100020","100021","100022","100023","100024","100025","100026","100027","100028","100029","100030","100031","100032","100033","100034","100035","100036","100037","100038","100039","100040","100041","100042","100043","100044","100045","100046","100047","100048","100049","100050","100051","100052","100053","100054","100055","100056","100057","100058","100059","100060","100061","100062","100063","100064","100065","100066","100067","100068","100069","100070","100071"};
		
		for(int i = 0; i < ids.length; i++) {
			ChotHoaDon(ids[i]);
		}
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		request.setCharacterEncoding("UTF-8");
	    response.setCharacterEncoding("UTF-8");
	    response.setContentType("text/html; charset=UTF-8");
	    this.out  = response.getWriter();
	    
	    
	    HttpSession session = request.getSession();	
	    
	    String userId = (String) session.getAttribute("userId");  
		String userTen = (String) session.getAttribute("userTen");  	
		String sum = (String) session.getAttribute("sum");
	    
	    geso.dms.center.util.Csrf csdr=new geso.dms.center.util.Csrf(request,response,false,true ,false);
		if(!csdr.__validate_post())
		{
			response.sendRedirect(request.getContextPath() + "/redirect.jsp");
			return;
		}
		
		geso.dms.center.util.Utility cutil = (geso.dms.center.util.Utility) session.getAttribute("util");
		if(!cutil.check(userId, userTen, sum)){
			response.sendRedirect(request.getContextPath() + "/redirect.jsp");
		}else{
			out = response.getWriter();
		    Utility util = new Utility();
		    
		    userId = util.antiSQLInspection(request.getParameter("userId"));  
		    
		    
		    IErpHoadonphelieuList obj;
		    
			String action = request.getParameter("action");
		    if (action == null){
		    	action = "";
		    }
		    
		    if(action.equals("new"))
		    {
	    		IErpHoadonphelieu khl = new ErpHoadonphelieu();
	    		
	    		session.setAttribute("nppId", "");
	    		khl.setUserId(userId);
	    		khl.createRS();
	    		
		    	session.setAttribute("csxBean", khl);  	
	    		session.setAttribute("userId", userId);
			
	    		response.sendRedirect(request.getContextPath() + "/pages/Distributor/ErpHoaDonPheLieuNew.jsp");
		    }
		    else
		    {
		    	if(action.equals("view") || action.equals("next") || action.equals("prev"))
		    	{
		    		obj = new ErpHoadonphelieuList();
				    obj.setUserId(userId);

				    session.setAttribute("nppId", "");
				    
		    		obj.setNxtApprSplitting(Integer.parseInt(request.getParameter("nxtApprSplitting")));

		    		String search = getSearchQuery(request, obj);
			    	
			    	obj.init(search);

			    	
			    	obj.setAttribute(request, action, "list", "crrApprSplitting", "nxtApprSplitting");
			    	
			    	session.setAttribute("obj", obj);
			    	
			    	response.sendRedirect(request.getContextPath() + "/pages/Distributor/ErpHoaDonPheLieu.jsp");	
			    }
		    	else{
		    	
		    	obj = new ErpHoadonphelieuList();
			    obj.setUserId(userId);

		    	String search = getSearchQuery(request, obj);
		    	
		    	obj.setUserId(userId);
		    	session.setAttribute("nppId", "");
		    	
		    	obj.init(search);
					
		    	session.setAttribute("obj", obj);  	
	    		session.setAttribute("userId", userId);
			
	    		response.sendRedirect(request.getContextPath() + "/pages/Distributor/ErpHoaDonPheLieu.jsp");
		    	}
		    }
		}
	    
	    
	}
	
	private String getSearchQuery(HttpServletRequest request, IErpHoadonphelieuList obj) 
	{
		Utility util = new Utility();
		
		String nppId = util.antiSQLInspection(request.getParameter("nppId"));
		if(nppId == null)
			nppId = "";
		obj.setNppId(nppId);
		
		String tennguoitao = util.antiSQLInspection(request.getParameter("tennguoitao"));
		if(tennguoitao == null)
			tennguoitao = "";
		tennguoitao=tennguoitao.trim();
		obj.setTennguoitao(tennguoitao);
		
		
		String ma = util.antiSQLInspection(request.getParameter("mahoadon"));
		if(ma == null)
			ma = "";
		obj.setMa(ma);
		
		String diengiai = util.antiSQLInspection(request.getParameter("diengiai"));
		if(diengiai == null)
			diengiai = "";
		obj.setDiengiai(diengiai);
		
		String trangthai = util.antiSQLInspection(request.getParameter("trangthai"));
		if(trangthai == null)
			trangthai = "";
		obj.setTrangthai(trangthai);
		
		
		String sohoadon = util.antiSQLInspection(request.getParameter("sohoadon"));
		if(sohoadon == null)
			sohoadon = "";
		obj.setSohoadon(sohoadon);
		
		String khachhang = util.antiSQLInspection(request.getParameter("khachhang"));
		if(khachhang == null)
			khachhang = "";
		obj.setKhachhang(khachhang);
		
		
		
		String sql =  " select a.pk_seq, d.ten as khTen, a.trangthai, b.ten as nguoitao,  " +
					  "        a.ngaytao, c.ten as nguoisua, a.ngaysua ,a.sohoadon, a.ngayhoadon ,a.vat , a.avat as tongtien        " +
					  "from ERP_HoaDonPheLieu a inner join NhanVien b on a.nguoitao = b.pk_seq      " +
					  "		inner join nhanvien c on a.nguoisua = c.pk_seq inner join KhachHang d on a.khachhang_fk = d.pk_seq   " +
					  " where a.pk_seq > 0 and a.npp_fk = "+ nppId +" ";
		
		if(tennguoitao.length() > 0)
			sql += " and b.ten like N'%" + tennguoitao + "%' ";
		if(diengiai.length() > 0)
			sql += " and a.diengiai like N'%" + diengiai + "%' ";
		
		if(trangthai.length() > 0)
			sql += " and a.trangthai = '" + trangthai + "' ";
		
		if(sohoadon.length() > 0)
		{
			sql += " and a.sohoadon like N'%" + sohoadon + "%' ";
		}
		if(ma.length() > 0)
		{
			sql += " and a.pk_seq like N'%" + ma + "%' ";
		}
		if(khachhang.length() > 0)
		{
			sql += " and (dbo.ftBoDau(d.ten)) like N'%" + util.replaceAEIOU(khachhang) + "%'  " +
					"or  dbo.ftBoDau(d.mafast) like N'%"+ util.replaceAEIOU(khachhang) +"%' or dbo.ftBoDau(d.pk_seq) like N'%"+ util.replaceAEIOU(khachhang) +"%'";
			
		}
		
		return sql;
	}
	
	

}
