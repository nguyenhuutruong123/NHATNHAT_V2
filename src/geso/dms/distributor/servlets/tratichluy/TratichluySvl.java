package geso.dms.distributor.servlets.tratichluy;

import geso.dms.distributor.beans.tratichluy.ITratichluyList;
import geso.dms.distributor.beans.tratichluy.imp.TratichluyList;
import geso.dms.center.db.sql.dbutils;
import geso.dms.center.util.Utility;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class TratichluySvl extends HttpServlet 
{
	private static final long serialVersionUID = 1L;
	PrintWriter out;
	
    public TratichluySvl() {
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
	    
	    ITratichluyList obj = new TratichluyList();
	    obj.setUserId(userId);
	    
	    String action = util.getAction(querystring);
	    String khlId = util.getId(querystring);
	    String msg = "";
	    
	    if(action.trim().equals("delete"))
	    {
	    	dbutils db = new dbutils();
	    	if(!db.update("Delete TraTichLuy_SANPHAM where tratichluy_fk = '" + khlId + "'"))
	    	{
	    		msg = "Không thể xóa TraTichLuy_SANPHAM";
	    	}
	    	else
	    	{
	    		db.update("Delete TraTichLuy where pk_seq = '" + khlId + "'");
	    	}
	    	db.shutDown();
	    }
	    
	    obj.init("");
	    obj.setMsg(msg);
		session.setAttribute("obj", obj);
	    
	    String nextJSP = request.getContextPath() + "/pages/Distributor/TraTichLuy.jsp";
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
	    
	    ITratichluyList obj;
	    
		String action = request.getParameter("action");
	    if (action == null){
	    	action = "";
	    }
	    
	    if(action.equals("new"))
	    {
    		/*ITratichluy khl = new Tratichluy();
    		khl.setCongtyId(ctyId);
    		khl.setUserId(userId);
    		khl.createRs();

	    	session.setAttribute("csxBean", khl);  	
    		session.setAttribute("userId", userId);
		
    		response.sendRedirect(request.getContextPath() + "/pages/Distributor/TraTichLuyNew.jsp");*/
	    }
	    else
	    {
	    	obj = new TratichluyList();
		    obj.setUserId(userId);

	    	String search = getSearchQuery(request, obj);
	    	
	    	obj.setUserId(userId);
	    	obj.init(search);
				
	    	session.setAttribute("obj", obj);  	
    		session.setAttribute("userId", userId);
		
    		response.sendRedirect(request.getContextPath() + "/pages/Distributor/TraTichLuy.jsp");	
	    }
	}
	
	private String getSearchQuery(HttpServletRequest request, ITratichluyList obj) 
	{
		String nppId = request.getParameter("nppId");
		if(nppId == null)
			nppId = "";
		obj.setNppId(nppId);
	
		String thang = request.getParameter("thang");
		if(thang == null)
			thang = "";
		obj.setThang(thang);
		
		String nam = request.getParameter("nam");
		if(nam == null)
			nam = "";
		obj.setNam(nam);
		
		String scheme = request.getParameter("dvkdId");
		if(scheme == null)
			scheme = "";
		obj.setDvkdId(scheme);
		
		String trangthai = request.getParameter("trangthai");
		if(trangthai == null)
			trangthai = "";
		obj.setTrangthai(trangthai);
		
		String sql = 	"select a.duyetkm_fk, c.pk_seq, c.SCHEME + ', ' + c.DIENGIAI as SCHEME, a.nppId, b.NGAYDUYET,  " +
						"case when SUM(a.trangthai) = COUNT(a.trangthai) then N'Hoàn tất' else N'Chưa hoàn tất' end as trangthai  " +
						  "from DuyetTraKhuyenMai_KhachHang a inner join DuyetTraKhuyenMai b on a.duyetkm_fk = b.PK_SEQ " +
						  	"inner join TIEUCHITHUONGTL c on b.CTKM_FK = c.PK_SEQ  " +
						  "where a.nppId = '" + obj.getNppId() + "' and a.donvi = '2' and a.thuong > 0 ";
						  
		
		if(thang.trim().length() > 0)
			sql += " and SUBSTRING(c.thang, 6, 2) like '%" + thang + "%' ";
		
		if(nam.trim().length() > 0)
			sql += " and SUBSTRING(c.nam, 0, 5) = '" + nam + "' ";
		
		if(scheme.length() > 0)
			sql += " and c.pk_seq = '" + scheme + "' ";
		
		sql += "group by a.duyetkm_fk, c.pk_seq, c.SCHEME + ', ' + c.DIENGIAI, a.nppId, b.NGAYDUYET  ";
		
		if(trangthai.length() > 0)
			sql += " having case when SUM(a.trangthai) = COUNT(a.trangthai) then N'1' else N'0' end = '" + trangthai + "' ";
		
		sql += " order by b.NGAYDUYET desc ";
		
		return sql;
	}
	
	

}
