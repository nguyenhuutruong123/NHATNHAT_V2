package geso.dms.center.servlets.doisolo;

import geso.dms.center.util.Utility;
import geso.dms.center.beans.doisolo.IErpDoisolo;
import geso.dms.center.beans.doisolo.IErpDoisoloList;
import geso.dms.center.beans.doisolo.imp.ErpDoisolo;
import geso.dms.center.beans.doisolo.imp.ErpDoisoloList;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class ErpDoisoloSvl extends HttpServlet
{
	private static final long serialVersionUID = 1L;
	PrintWriter out; 
	String userId;
	
    public ErpDoisoloSvl() 
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
	    out = response.getWriter();
	    	    
	    String querystring = request.getQueryString();
	    userId = util.getUserId(querystring);
	    
	    if (userId.length() == 0 )
	    	userId = request.getParameter("userId");
	    
	    String action = util.getAction(querystring);
	    out.println(action);
	    
	    String pxkId = util.getId(querystring);

	    String msg = "";
	    if(action.equals("chotphieu"))
	    {
    		IErpDoisolo ck = new ErpDoisolo(pxkId);
    		ck.setUserId(userId);
    		if(!ck.ChotCk())
    		{
    			System.out.println("____"+ck.getMessage());
    			msg = "Không thể chốt đổi số lô, vui lòng thử lại sau"+ck.getMessage();
    		}
	    }
	    else
	    {
	    	if(action.equals("delete"))
	    	{
		    	IErpDoisolo ck = new ErpDoisolo(pxkId);
		    	ck.setUserId(userId);
	    		if(!ck.DeleteCk())
	    		{
	    			ck.setMessage("Không thể xóa đổi số lô, vui lòng thử lại sau");
	    		}
	    	}
	    }
	   	    
	    IErpDoisoloList obj = new ErpDoisoloList();
	    obj.setUserId(userId);
	    
	    obj.init("");
	    obj.setMsg(msg);
	    
		session.setAttribute("obj", obj);
				
		String nextJSP = request.getContextPath() + "/pages/Center/ErpDoisolo.jsp";
		response.sendRedirect(nextJSP);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		request.setCharacterEncoding("UTF-8");
	    response.setCharacterEncoding("UTF-8");
	    response.setContentType("text/html; charset=UTF-8");
	    this.out = response.getWriter();
	    
		HttpSession session = request.getSession();
	    userId = request.getParameter("userId");
	    
	    String action = request.getParameter("action");
	    if (action == null){
	    	action = "";
	    }
	    out.println("Action la: " + action); 
	    
	    if (action.equals("Tao moi"))
	    {
	    	// Empty Bean for Center
	    	IErpDoisolo pxkBean = (IErpDoisolo) new ErpDoisolo("");
	    	pxkBean.setUserId(userId);
	    	pxkBean.createRS();
	    	
	    	// Save Data into session
	    	session.setAttribute("pxkBean", pxkBean);
    		
    		String nextJSP = request.getContextPath() + "/pages/Center/ErpDoisoloNew.jsp";
    		response.sendRedirect(nextJSP);
    		
	    }
	    else
	    {
	    	IErpDoisoloList obj = new ErpDoisoloList();

	    	String search = getSearchQuery(request, obj);
	    	
	    	obj.setUserId(userId);
	    	obj.init(search);
				
	    	session.setAttribute("obj", obj);  	
    		session.setAttribute("userId", userId);
		
    		response.sendRedirect(request.getContextPath() + "/pages/Center/ErpDoisolo.jsp");	    		    	
	    }
	    
	}
	
	private String getSearchQuery(HttpServletRequest request, IErpDoisoloList obj) 
	{	
		String nppId = request.getParameter("nppId");
    	if ( nppId == null)
    		nppId = "";
    	obj.setNppId(nppId);
    	
		String nvbhId = request.getParameter("nvbhId");
    	if ( nvbhId == null)
    		nvbhId = "";
    	obj.setNvbhId(nvbhId);
    	
    	String trangthai = request.getParameter("trangthai");
    	if ( trangthai == null)
    		trangthai = "";
    	obj.setTrangthai(trangthai);
    	
    	String tungay = request.getParameter("tungay");
    	if (tungay == null)
    		tungay = "";    	
    	obj.setTungay(tungay);
    	
    	String denngay = request.getParameter("denngay");
    	if (denngay == null)
    		denngay = "";    	
    	obj.setDenngay(denngay);
    	   	
    	/*String query = "select a.pk_seq, a.trangthai, a.ngaydoi, b.diengiai as kbhTen, e.ten as khoTEN, a.ngaytao, c.ten as nguoitao, a.ngaysua, d.ten as nguoisua  " +
							"from ERP_DOISOLONPP a inner join KenhBanHang b on a.kbh_fk = b.pk_seq  " +
							"inner join nhanvien c on a.nguoitao = c.pk_seq " +
							"inner join nhanvien d on a.nguoisua = d.pk_seq  " +
							"inner join kho e on a.kho_fk = e.pk_seq  " +
							"where 1=1 ";*/
							/*"where a.npp_fk = " + nppId + "  ";*/
    	String query = "select a.pk_seq, a.trangthai, a.ngaydoi, e.ten as khoTEN, a.ngaytao, c.ten as nguoitao, a.ngaysua, d.ten as nguoisua  " +
				"from ERP_DOISOLO a  " +
					"inner join nhanvien c on a.nguoitao = c.pk_seq " +
					"inner join nhanvien d on a.nguoisua = d.pk_seq  " +
					"inner join Erp_KhoTT e on a.kho_fk = e.pk_seq  " +
				"where 1=1  ";
    	if(nppId.trim().length() > 0)
    		query += " and a.npp_fk = " + nppId + " ";
    	
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
