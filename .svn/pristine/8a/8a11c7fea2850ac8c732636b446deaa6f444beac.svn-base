package geso.dms.distributor.servlets.tratichluy;

import geso.dms.distributor.beans.tratichluy.ITratichluy;
import geso.dms.distributor.beans.tratichluy.ITratichluyList;
import geso.dms.distributor.beans.tratichluy.imp.Tratichluy;
import geso.dms.distributor.beans.tratichluy.imp.TratichluyList;
import geso.dms.center.util.Utility;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Hashtable;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class TratichluyUpdateSvl extends HttpServlet 
{
	private static final long serialVersionUID = 1L;
	PrintWriter out; 
       
    public TratichluyUpdateSvl() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		HttpSession session = request.getSession();
		ITratichluy csxBean;
		
		this.out = response.getWriter();
		Utility util = new Utility();
		
    	String querystring = request.getQueryString();
	    String userId = util.getUserId(querystring);
	    
	    out.println(userId);
	    
	    if (userId.length() == 0)
	    	userId = util.antiSQLInspection(request.getParameter("userId"));
	    	    
	    String id = util.getId(querystring);
	    //String id = request.getParameter("display");
	    //String nppId = request.getParameter("nppId");
	    System.out.println("__ID la: " + id);
	    
	    csxBean = new Tratichluy(id);
	    
	    csxBean.setId(id);
	    csxBean.setUserId(userId);
	    
	    csxBean.init();
        session.setAttribute("csxBean", csxBean);
        
        String nextJSP = request.getContextPath() + "/pages/Distributor/TraTichLuyUpdate.jsp";
        if(querystring.indexOf("display") >= 0)
        {
        	nextJSP = request.getContextPath() + "/pages/Distributor/TraTichLuyDisplay.jsp";
        }
        
        response.sendRedirect(nextJSP);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		request.setCharacterEncoding("UTF-8");
	    response.setCharacterEncoding("UTF-8");
	    response.setContentType("text/html; charset=UTF-8");
		HttpSession session = request.getSession();
		ITratichluy csxBean;

		Utility util = new Utility();
	    String id = util.antiSQLInspection(request.getParameter("id"));	
	    if(id == null){  	
	    	csxBean = new Tratichluy();
	    }else{
	    	csxBean = new Tratichluy(id);
	    }
	    	    
		String userId = util.antiSQLInspection(request.getParameter("userId"));
		csxBean.setUserId(userId);	       
		
		String nppId = request.getParameter("nppId");
		csxBean.setNppId(nppId);
		
		String[] khIds = request.getParameterValues("khachhangIds");
		String khachhangIds = "";
		if(khIds != null)
		{
			for(int i = 0; i < khIds.length; i++)
			{
				khachhangIds += khIds[i] + ",";
			}
		}
		csxBean.setKhIds(khachhangIds);
		
		String[] idKhachHang = request.getParameterValues("idkhachhang");
		csxBean.setKhachhangId(idKhachHang);
		
		String[] tenKhachHang = request.getParameterValues("tenkhachhang");
		csxBean.setKhachhangTen(tenKhachHang);
		
		String[] tongluong = request.getParameterValues("tongluong");
		csxBean.setSoxuat(tongluong);
		
		if(idKhachHang != null)
		{
			Hashtable<String, String> kh_spTra = new Hashtable<String, String>();
			
			for(int i = 0; i < idKhachHang.length; i++ )
			{
				System.out.println("KH ID: " + idKhachHang[i]);
				
				//if(khachhangIds.contains(idKhachHang[i]))
				//{
					String[] spId = request.getParameterValues(idKhachHang[i] + "_spId");
					String[] spSoLuong = request.getParameterValues(idKhachHang[i] + "_SoLuong");
					
					String spTra = "";
					if(spId != null)
					{
						for(int j = 0; j < spId.length; j++ )
						{
							if(spSoLuong[j] != null && spSoLuong[j] != "" )
							{
								spTra += spId[j] + "-" + spSoLuong[j] + ";";
							}
						}
					}
					
					if(spTra.trim().length() > 0)
					{
						spTra = spTra.substring(0, spTra.length() - 1);
						
						System.out.println("___Khach hang: " + idKhachHang[i] + "  -- SP Tra: " + spTra );
						kh_spTra.put(idKhachHang[i], spTra);
					}
				//}
			}
			
			csxBean.setKh_SPTra(kh_spTra);
		}
		
 		String action = request.getParameter("action");
 		if(action.equals("save"))
 		{
			if (!(csxBean.updateTratichluy()))
			{
				csxBean.init();
 				session.setAttribute("csxBean", csxBean);  	
 	    		session.setAttribute("userId", userId);
 			
				String nextJSP = request.getContextPath() + "/pages/Distributor/TraTichLuyDisplay.jsp";
				response.sendRedirect(nextJSP);
			}
			else
			{
				ITratichluyList obj = new TratichluyList();
				obj.setUserId(userId);
				obj.init("");

				session.setAttribute("obj", obj);
			    
			    String nextJSP = request.getContextPath() + "/pages/Distributor/TraTichLuy.jsp";
				response.sendRedirect(nextJSP);
			}
 			
	    }
		else
		{
			csxBean.init();
			
			session.setAttribute("userId", userId);
			session.setAttribute("csxBean", csxBean);
			
			String nextJSP = request.getContextPath() + "/pages/Distributor/TraTichLuyNew.jsp";
			if( id != null )
			{
				 nextJSP = request.getContextPath() + "/pages/Distributor/TraTichLuyDisplay.jsp";
			}
			
			response.sendRedirect(nextJSP);
		}		
	}
	
	
}
