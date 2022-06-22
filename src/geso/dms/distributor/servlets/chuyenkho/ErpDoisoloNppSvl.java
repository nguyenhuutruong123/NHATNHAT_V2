package geso.dms.distributor.servlets.chuyenkho;

import geso.dms.center.util.Utility;
import geso.dms.distributor.beans.chuyenkho.IErpDoisoloNpp;
import geso.dms.distributor.beans.chuyenkho.IErpDoisoloNppList;
import geso.dms.distributor.beans.chuyenkho.imp.ErpDoisoloNpp;
import geso.dms.distributor.beans.chuyenkho.imp.ErpDoisoloNppList;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class ErpDoisoloNppSvl extends HttpServlet 
{
	private static final long serialVersionUID = 1L;
	PrintWriter out; 
	String userId;
	
    public ErpDoisoloNppSvl() 
    {
        super();
    }
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		request.setCharacterEncoding("UTF-8");
	    response.setCharacterEncoding("UTF-8");
	    response.setContentType("text/html; charset=UTF-8");
	    this.out = response.getWriter();
	    	    
	    HttpSession session = request.getSession();

	    Utility util = new Utility();
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
	    out = response.getWriter();
	    	    
	    String querystring = request.getQueryString();
	    userId = util.getUserId(querystring);
	    
	    if (userId.length() == 0 )
	    	userId = util.antiSQLInspection(request.getParameter("userId"));
	    
	    String action = util.getAction(querystring);
	    out.println(action);
	    
	    String pxkId = util.getId(querystring);

	    String msg = "";
	    if(action.equals("chotphieu"))
	    {
    		IErpDoisoloNpp ck = new ErpDoisoloNpp(pxkId);
    		ck.setUserId(userId);
    		if(!ck.ChotCk())
    		{
    			System.out.println(ck.getMessage());
    			msg = "Không thể chốt đổi số lô, vui lòng thử lại sau "+ck.getMessage();
    		}
	    }
	    else
	    {
	    	if(action.equals("delete"))
	    	{
		    	IErpDoisoloNpp ck = new ErpDoisoloNpp(pxkId);
		    	ck.setUserId(userId);
	    		if(!ck.DeleteCk())
	    		{
	    			ck.setMessage("Không thể xóa đổi số lô, vui lòng thử lại sau");
	    		}
	    	}
	    }
	   	    
	    IErpDoisoloNppList obj = new ErpDoisoloNppList();
	    obj.setUserId(userId);
	    
	    obj.init("");
	    obj.setMsg(msg);
	    
		session.setAttribute("obj", obj);
				
		String nextJSP = request.getContextPath() + "/pages/Distributor/ErpDoiSoLoNpp.jsp";
		response.sendRedirect(nextJSP);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		request.setCharacterEncoding("UTF-8");
	    response.setCharacterEncoding("UTF-8");
	    response.setContentType("text/html; charset=UTF-8");
	    this.out = response.getWriter();
	    
		HttpSession session = request.getSession();
		geso.dms.center.util.Csrf csdr=new geso.dms.center.util.Csrf(request,response,false,true ,false);
		Utility util = new Utility();
		if(!csdr.__validate_post())
		{
			response.sendRedirect(request.getContextPath() + "/redirect.jsp");
			return;
		}
	    userId = util.antiSQLInspection(request.getParameter("userId"));
	    
	    String action = util.antiSQLInspection(request.getParameter("action"));
	    if (action == null){
	    	action = "";
	    }
	    out.println("Action la: " + action); 
	    
	    if (action.equals("Tao moi"))
	    {
	    	// Empty Bean for distributor
	    	IErpDoisoloNpp pxkBean = (IErpDoisoloNpp) new ErpDoisoloNpp("");
	    	pxkBean.setUserId(userId);
	    	pxkBean.createRS();
	    	
	    	// Save Data into session
	    	session.setAttribute("pxkBean", pxkBean);
    		
    		String nextJSP = request.getContextPath() + "/pages/Distributor/ErpDoiSoLoNppNew.jsp";
    		response.sendRedirect(nextJSP);
    		
	    }
	    else
	    {
	    	IErpDoisoloNppList obj = new ErpDoisoloNppList();

	    	String search = getSearchQuery(request, obj);
	    	
	    	obj.setUserId(userId);
	    	obj.init(search);
				
	    	session.setAttribute("obj", obj);  	
    		session.setAttribute("userId", userId);
		
    		response.sendRedirect(request.getContextPath() + "/pages/Distributor/ErpDoiSoLoNpp.jsp");	    		    	
	    }
	    
	}
	
	private String getSearchQuery(HttpServletRequest request, IErpDoisoloNppList obj) 
	{	
		Utility util= new Utility();
		String nppId = util.antiSQLInspection(request.getParameter("nppId"));
    	if ( nppId == null)
    		nppId = "";
    	obj.setNppId(nppId);
    	
		String nvbhId = util.antiSQLInspection(request.getParameter("nvbhId"));
    	if ( nvbhId == null)
    		nvbhId = "";
    	obj.setNvbhId(nvbhId);
    	
    	String trangthai = util.antiSQLInspection(request.getParameter("trangthai"));
    	if ( trangthai == null)
    		trangthai = "";
    	obj.setTrangthai(trangthai);
    	
    	String tungay = util.antiSQLInspection(request.getParameter("tungay"));
    	if (tungay == null)
    		tungay = "";    	
    	obj.setTungay(tungay);
    	
    	String denngay = util.antiSQLInspection(request.getParameter("denngay"));
    	if (denngay == null)
    		denngay = "";    	
    	obj.setDenngay(denngay);
    	   	
    	String query = "select a.pk_seq, a.trangthai, a.ngaydoi, b.diengiai as kbhTen, e.ten as khoTEN, a.ngaytao, c.ten as nguoitao, a.ngaysua, d.ten as nguoisua  " +
							"from ERP_DOISOLONPP a inner join KenhBanHang b on a.kbh_fk = b.pk_seq  " +
							"inner join nhanvien c on a.nguoitao = c.pk_seq " +
							"inner join nhanvien d on a.nguoisua = d.pk_seq  " +
							"inner join kho e on a.kho_fk = e.pk_seq  " +
						"where a.npp_fk = '" + nppId + "'  ";
    	
    	if(nvbhId.trim().length() > 0)
    		query += " and b.pk_seq = '" + nvbhId + "' ";
    	
    	if(trangthai.trim().length() > 0)
    		query += " and a.trangthai = '" + trangthai + "' ";
    	
    	if(tungay.trim().length() > 0)
    		query += " and a.ngaydoi >= '" + tungay + "'  ";
    	
    	if(denngay.trim().length() > 0)
    		query += " and a.ngaydoi <= '" + denngay + "'  ";
						
    	query += " order by a.pk_seq desc ";
    	return query;
	}
	
}
