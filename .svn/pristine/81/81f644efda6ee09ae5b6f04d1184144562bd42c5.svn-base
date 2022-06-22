package geso.dms.erp.servlets.lenhsanxuat;

import geso.dms.erp.beans.lenhsanxuat.IErpRacoloc;
import geso.dms.erp.beans.lenhsanxuat.IErpRacolocList;
import geso.dms.erp.beans.lenhsanxuat.imp.ErpRacoloc;
import geso.dms.erp.beans.lenhsanxuat.imp.ErpRacolocList;
import geso.dms.center.util.Utility;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class ErpRacolocUpdateSvl extends HttpServlet 
{
	private static final long serialVersionUID = 1L;
	PrintWriter out; 
       
    public ErpRacolocUpdateSvl() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		HttpSession session = request.getSession();
		IErpRacoloc csxBean;
		
		this.out = response.getWriter();
		Utility util = new Utility();
		
    	String querystring = request.getQueryString();
	    String userId = util.getUserId(querystring);
	    
	    out.println(userId);
	    
	    if (userId.length() == 0)
	    	userId = util.antiSQLInspection(request.getParameter("userId"));
	    	    
	    String id = util.getId(querystring);
	   
	    csxBean = new ErpRacoloc(id);
	    String ctyId = (String)session.getAttribute("congtyId");
	    
	    csxBean.setCongtyId(ctyId);
	    csxBean.setId(id);
	    csxBean.setUserId(userId);
	    
	    csxBean.init();
        session.setAttribute("csxBean", csxBean);
        
        String nextJSP = "/SalesUp/pages/Erp/ErpRaColocUpdate.jsp";
        if(querystring.indexOf("display") >= 0)
        {
        	nextJSP = "/SalesUp/pages/Erp/ErpRaColocDisplay.jsp";
        }
        
        response.sendRedirect(nextJSP);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		request.setCharacterEncoding("UTF-8");
	    response.setCharacterEncoding("UTF-8");
	    response.setContentType("text/html; charset=UTF-8");
		HttpSession session = request.getSession();
		IErpRacoloc csxBean;

		Utility util = new Utility();
	    String id = util.antiSQLInspection(request.getParameter("id"));	
	    if(id == null){  	
	    	csxBean = new ErpRacoloc();
	    }else{
	    	csxBean = new ErpRacoloc(id);
	    }
	    	    
		String userId = util.antiSQLInspection(request.getParameter("userId"));
		csxBean.setUserId(userId);	       
		
		String ctyId = (String)session.getAttribute("congtyId");
		csxBean.setCongtyId(ctyId);
		
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
		
		String spId = util.antiSQLInspection(request.getParameter("spId"));
		if (spId == null)
			spId = "";
		csxBean.setSpId(spId);
		
		String soloId = request.getParameter("soloId");
		if (soloId == null)
			soloId = "";
		csxBean.setSoloId(soloId);
		System.out.println("Solo new "+soloId);
		
		String tonkho = util.antiSQLInspection(request.getParameter("tonkho"));
		if (tonkho == null)
			tonkho = "";
		csxBean.setAvai(tonkho);
		
		String soluong = util.antiSQLInspection(request.getParameter("soluong"));
		if (soluong == null)
			soluong = "";
		csxBean.setSoluong(soluong);
		
		String dongia = util.antiSQLInspection(request.getParameter("dongia"));
		if (dongia == null)
			dongia = "";
		csxBean.setDongia(dongia);
		
		String thanhtien = util.antiSQLInspection(request.getParameter("thanhtien"));
		if (thanhtien == null)
			thanhtien = "";
		csxBean.setThanhtien(thanhtien);
		
		String bomId = util.antiSQLInspection(request.getParameter("bomId"));
		if (bomId == null)
			bomId = "";
		csxBean.setBomId(bomId);
		
		String[] nguyenlieu = request.getParameterValues("nguyenlieu");
		csxBean.setSpIds(nguyenlieu);
		
		String[] nguyenlieu_ma = request.getParameterValues("nguyenlieu_ma");
		csxBean.setSpMaIds(nguyenlieu_ma);
		
		String[] nguyenlieu_ten = request.getParameterValues("nguyenlieu_ten");
		csxBean.setSpTenIds(nguyenlieu_ten);
		
		String[] nguyenlieu_solo = request.getParameterValues("nguyenlieu_solo");
		csxBean.setSoloIds(nguyenlieu_solo);
		
		String[] nguyenlieu_soluong = request.getParameterValues("nguyenlieu_soluong");
		csxBean.setSoluongIds(nguyenlieu_soluong);
		
		String[] nguyenlieu_dongia = request.getParameterValues("nguyenlieu_dongia");
		csxBean.setDongiaIds(nguyenlieu_dongia);
		
		String[] nguyenlieu_ngaysx = request.getParameterValues("nguyenlieu_ngaysx");
		csxBean.setNgaysxIds(nguyenlieu_ngaysx);
		
		String[] nguyenlieu_ngayHetHan = request.getParameterValues("nguyenlieu_ngayHetHan");
		csxBean.setNgayHetHanIds(nguyenlieu_ngayHetHan);
		
		String[] nguyenlieu_thanhtien = request.getParameterValues("nguyenlieu_thanhtien");
		csxBean.setThanhtienIds(nguyenlieu_thanhtien);
		
 		String action = request.getParameter("action");
 		if(action.equals("save"))
 		{
 			if(id == null)
 			{
	 			if (!(csxBean.createRacoloc()))
				{
	 				//csxBean.createRs();
	 				
	 				session.setAttribute("csxBean", csxBean);  	
	 	    		session.setAttribute("userId", userId);
	 			
					String nextJSP = "/SalesUp/pages/Erp/ErpRaColocNew.jsp";
					response.sendRedirect(nextJSP);
				}
				else
				{
					IErpRacolocList obj = new ErpRacolocList();
					obj.setCongtyId(ctyId);
					obj.setUserId(userId);
					obj.init("");
	
					session.setAttribute("obj", obj);
					csxBean.DbClose();
				    String nextJSP = "/SalesUp/pages/Erp/ErpRaColoc.jsp";
					response.sendRedirect(nextJSP);
				}
 			}
 			else
 			{
 				if (!(csxBean.updateRacoloc()))
				{
 					//csxBean.createRs();
 					
	 				session.setAttribute("csxBean", csxBean);  	
	 	    		session.setAttribute("userId", userId);
	 			
					String nextJSP = "/SalesUp/pages/Erp/ErpRaColocUpdate.jsp";
					response.sendRedirect(nextJSP);
				}
				else
				{
					IErpRacolocList obj = new ErpRacolocList();
					obj.setCongtyId(ctyId);
					obj.setUserId(userId);
					obj.init("");
	
					session.setAttribute("obj", obj);
					csxBean.DbClose();
				    String nextJSP = "/SalesUp/pages/Erp/ErpRaColoc.jsp";
					response.sendRedirect(nextJSP);
				}
 			}
	    }
		else
		{
			csxBean.createRs();
			
			session.setAttribute("userId", userId);
			session.setAttribute("khoId", khoId);
			session.setAttribute("csxBean", csxBean);
			
			String nextJSP = "/SalesUp/pages/Erp/ErpRaColocNew.jsp";
			
			if( id != null )
			{
				 nextJSP = "/SalesUp/pages/Erp/ErpRaColocUpdate.jsp";
			}
			
			response.sendRedirect(nextJSP);
		}		
	}
	
	
}
