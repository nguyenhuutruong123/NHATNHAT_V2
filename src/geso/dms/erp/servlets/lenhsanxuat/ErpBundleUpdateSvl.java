package geso.dms.erp.servlets.lenhsanxuat;

import geso.dms.erp.beans.lenhsanxuat.IErpBundle;
import geso.dms.erp.beans.lenhsanxuat.IErpBundleList;
import geso.dms.erp.beans.lenhsanxuat.imp.ErpBundle;
import geso.dms.erp.beans.lenhsanxuat.imp.ErpBundleList;
import geso.dms.center.util.Utility;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class ErpBundleUpdateSvl extends HttpServlet 
{
	private static final long serialVersionUID = 1L;
	PrintWriter out; 
       
    public ErpBundleUpdateSvl() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		HttpSession session = request.getSession();
		IErpBundle csxBean;
		
		this.out = response.getWriter();
		Utility util = new Utility();
		
    	String querystring = request.getQueryString();
	    String userId = util.getUserId(querystring);
	    
	    out.println(userId);
	    
	    if (userId.length() == 0)
	    	userId = util.antiSQLInspection(request.getParameter("userId"));
	    	    
	    String id = util.getId(querystring);
	   
	    csxBean = new ErpBundle(id);
	    String ctyId = (String)session.getAttribute("congtyId");
	    
	    csxBean.setId(id);
	    csxBean.setUserId(userId);
	    
	    csxBean.init();
        session.setAttribute("csxBean", csxBean);
        
        String nextJSP = request.getContextPath() + "/pages/Erp/ErpBundleUpdate.jsp";
        if(querystring.indexOf("display") >= 0)
        {
        	nextJSP = request.getContextPath() + "/pages/Erp/ErpBundleDisplay.jsp";
        }
        
        response.sendRedirect(nextJSP);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		request.setCharacterEncoding("UTF-8");
	    response.setCharacterEncoding("UTF-8");
	    response.setContentType("text/html; charset=UTF-8");
		HttpSession session = request.getSession();
		IErpBundle csxBean;

		Utility util = new Utility();
	    String id = util.antiSQLInspection(request.getParameter("id"));	
	    if(id == null){  	
	    	csxBean = new ErpBundle();
	    }else{
	    	csxBean = new ErpBundle(id);
	    }
	    	    
		String userId = util.antiSQLInspection(request.getParameter("userId"));
		csxBean.setUserId(userId);	       
		
		String ngaythuchien = util.antiSQLInspection(request.getParameter("ngaythuchien"));
		if (ngaythuchien == null)
			ngaythuchien = "";
		csxBean.setNgaythuchien(ngaythuchien);
		
		String diengiai = util.antiSQLInspection(request.getParameter("diengiai"));
		if (diengiai == null)
			diengiai = "";
		csxBean.setDiengiai(diengiai);
		
		String khoId = util.antiSQLInspection(request.getParameter("khoId"));
		if (khoId == null)
			khoId = "";
		csxBean.setKhoTTId(khoId);
		
		
		String nppDatId = util.antiSQLInspection(request.getParameter("nppDatId"));
		if (nppDatId == null)
			nppDatId = "";
		csxBean.setNppDatId(nppDatId);
		

		String kbhId = util.antiSQLInspection(request.getParameter("kbhId"));
		if (kbhId == null)
			kbhId = "";
		csxBean.setKbhId(kbhId);
		
		String spId = util.antiSQLInspection(request.getParameter("spId"));
		if (spId == null)
			spId = "";
		csxBean.setSpId(spId);
		
		String solo = util.antiSQLInspection(request.getParameter("solo"));
		if (solo == null)
			solo = "";
		csxBean.setSoloId(solo);
		
		String tonkho = util.antiSQLInspection(request.getParameter("tonkho"));
		if (tonkho == null)
			tonkho = "";
		csxBean.setAvai(tonkho);
		
		String soluong = util.antiSQLInspection(request.getParameter("soluong"));
		if (soluong == null)
			soluong = "";
		csxBean.setSoluong(soluong);
		
		String[] spIds = request.getParameterValues("nguyenlieu_masanpham");
		csxBean.setSpIds(spIds);
		
		String[] nguyenlieu_solo = request.getParameterValues("nguyenlieu_solo");
		System.out.println("----LAY DUOC SOLO: " + nguyenlieu_solo.length);
		csxBean.setSoloIds(nguyenlieu_solo);
		
		String[] nguyenlieu_soluong = request.getParameterValues("nguyenlieu_soluong");
		csxBean.setSoluongIds(nguyenlieu_soluong);
		
		String[] nguyenlieu_tonkho = request.getParameterValues("nguyenlieu_tonkho");
		csxBean.setDongiaIds(nguyenlieu_tonkho);
		
 		String action = request.getParameter("action");
 		if(action.equals("save"))
 		{
 			if(id == null)
 			{
	 			if (!(csxBean.createBundle()))
				{
	 				csxBean.createRs();
	 				
	 				session.setAttribute("csxBean", csxBean);  	
	 	    		session.setAttribute("userId", userId);
	 			
					String nextJSP = request.getContextPath() + "/pages/Erp/ErpBundleNew.jsp";
					response.sendRedirect(nextJSP);
				}
				else
				{
					IErpBundleList obj = new ErpBundleList();
					obj.setUserId(userId);
					obj.init("");
	
					session.setAttribute("obj", obj);
					csxBean.DbClose();
				    String nextJSP = request.getContextPath() + "/pages/Erp/ErpBundle.jsp";
					response.sendRedirect(nextJSP);
				}
 			}
 			else
 			{
 				if (!(csxBean.updateBundle()))
				{
 					csxBean.createRs();
 					
	 				session.setAttribute("csxBean", csxBean);  	
	 	    		session.setAttribute("userId", userId);
	 			
					String nextJSP = request.getContextPath() + "/pages/Erp/ErpBundleUpdate.jsp";
					response.sendRedirect(nextJSP);
				}
				else
				{
					IErpBundleList obj = new ErpBundleList();
					obj.setUserId(userId);
					obj.init("");
	
					session.setAttribute("obj", obj);
					csxBean.DbClose();
				    String nextJSP = request.getContextPath() + "/pages/Erp/ErpBundle.jsp";
					response.sendRedirect(nextJSP);
				}
 			}
	    }
		else
		{
			csxBean.createRs();
			
			session.setAttribute("userId", userId);
			session.setAttribute("csxBean", csxBean);
			
			String nextJSP = request.getContextPath() + "/pages/Erp/ErpBundleNew.jsp";
			
			if( id != null )
			{
				 nextJSP = request.getContextPath() + "/pages/Erp/ErpBundleUpdate.jsp";
			}
			
			response.sendRedirect(nextJSP);
		}		
	}
	
	
}
