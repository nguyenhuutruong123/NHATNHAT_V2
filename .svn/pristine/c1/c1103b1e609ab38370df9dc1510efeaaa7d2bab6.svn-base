package geso.dms.center.servlets.vung;

import geso.dms.center.beans.vung.imp.*;
import geso.dms.center.beans.vung.*;
import geso.dms.center.util.Utility;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class VungmienUpdateSvl extends HttpServlet 
{
	private static final long serialVersionUID = 1L;
	private PrintWriter out;  

    public VungmienUpdateSvl() 
    {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		HttpSession session = request.getSession();
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
		this.out = response.getWriter();
		Utility util = new Utility();
		
    	String querystring = request.getQueryString();
	    String userId = util.getUserId(querystring);
	    
	    out.println(userId);
	    
	    if (userId.length()==0)
	    	userId = util.antiSQLInspection(request.getParameter("userId"));
	    
	    String id = util.getId(querystring);  	

	    IVungmien vmBean = new Vungmien(id);
	    
        vmBean.setUserId(userId);
        session.setAttribute("vmBean", vmBean);
        
        String update = util.antiSQLInspection(request.getParameter("update"));
	       if(update==null)
	    	   update="";


	       String display = util.antiSQLInspection(request.getParameter("display"));
	       if(display==null)
	    	   display="";
	     
	       String nextJSP = "";
	       if(update.trim().length()>0)
	    	   nextJSP = request.getContextPath() + "/pages/Center/VungMienUpdate.jsp";
	       if(display.trim().length()>0)
	    	   nextJSP = request.getContextPath() + "/pages/Center/VungMienDisplay.jsp";
		
        
            response.sendRedirect(nextJSP);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		request.setCharacterEncoding("UTF-8");
	    response.setCharacterEncoding("UTF-8");
	    response.setContentType("text/html; charset=UTF-8");
		HttpSession session = request.getSession();
		geso.dms.center.util.Csrf csdr=new geso.dms.center.util.Csrf(request,response,false,true ,false);
		if(!csdr.__validate_post())
		{
			response.sendRedirect(request.getContextPath() + "/redirect.jsp");
			return;
		}
		IVungmien vmBean;
		this.out = response.getWriter();
		Utility util = new Utility();
		
	    String id =  util.antiSQLInspection(request.getParameter("id"));
	    if (id == null) {  	
	    	vmBean = new Vungmien("");
	    }
	    else{
	    	vmBean = new Vungmien(id);
	    }
	    	    
		String userId = util.antiSQLInspection(request.getParameter("userId"));
		vmBean.setUserId(userId);
	    
    	String vungmien = util.antiSQLInspection(request.getParameter("vungmien"));
		if (vungmien == null)
			vungmien = "";				
    	vmBean.setVungmien(vungmien);
    	
    	String bm = util.antiSQLInspection(request.getParameter("bm"));
    	if (bm == null)
    		bm ="";
    	vmBean.setBm(bm);
    	
    	String diengiai = util.antiSQLInspection(request.getParameter("diengiai"));
		if (diengiai == null)
			diengiai = "";
		vmBean.setDiengiai(diengiai);

		String trangthai = util.antiSQLInspection(request.getParameter("trangthai"));
    	if (trangthai == null)
    		trangthai = "0";
    	else
    		trangthai = "1";
    	vmBean.setTrangthai(trangthai);

		String ngaysua = getDateTime();
    	vmBean.setNgaysua(ngaysua);
		
		String nguoisua = userId;
		vmBean.setNguoisua(nguoisua);    	
		
		boolean error = false;
		
		if (!Utility.isValid(vungmien)) {
			error = true;
			vmBean.setMessage("Vui lòng nhập tên Vùng/miền.");
		}				

		if (!Utility.isValid(diengiai)) {
			error = true;
			vmBean.setMessage("Vui lòng nhập diễn giải Vùng/miền.");
		}
 		
 		String action = request.getParameter("action");
	    
		if (!error && action.equals("save"))
		{
			if (id == null) {
				if (!(vmBean.CreateVm())) {				
					session.setAttribute("vmBean", vmBean);
					vmBean.setUserId(userId);
					String nextJSP = request.getContextPath() + "/pages/Center/VungMienNew.jsp";
					response.sendRedirect(nextJSP);
				}else{
					IVungmienList obj = new VungmienList();
					obj.setUserId(userId);
					session.setAttribute("obj", obj);
						
					String nextJSP = request.getContextPath() + "/pages/Center/VungMien.jsp";
					response.sendRedirect(nextJSP);			    			    									
				}
				
			}else{
				if (!(vmBean.UpdateVm())) {			
					session.setAttribute("vmBean", vmBean);
					String nextJSP = request.getContextPath() + "/pages/Center/VungMienUpdate.jsp";
					response.sendRedirect(nextJSP);
				}
				else{
					IVungmienList obj = new VungmienList();
					obj.setUserId(userId);
					session.setAttribute("obj", obj);
						
					String nextJSP = request.getContextPath() + "/pages/Center/VungMien.jsp";
					response.sendRedirect(nextJSP);			    			    									
				}
			}
		}
		else{
			vmBean.setUserId(userId);
			session.setAttribute("vmBean", vmBean);
			
			String nextJSP;
			if (id == null) {			
				nextJSP = request.getContextPath() + "/pages/Center/VungMienNew.jsp";
			}
			else{
				nextJSP = request.getContextPath() + "/pages/Center/VungMienUpdate.jsp";   						
			}
			
			response.sendRedirect(nextJSP);			
		}
	}
	
	private String getDateTime() {
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date date = new Date();
        return dateFormat.format(date);	
	}

}
