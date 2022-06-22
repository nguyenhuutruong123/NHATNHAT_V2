package geso.dms.center.servlets.thanhthinongthon;

import geso.dms.center.beans.thanhthinongthon.imp.*;
import geso.dms.center.beans.thanhthinongthon.*;
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

public class ThanhthinongthonUpdateSvl extends HttpServlet 
{
	private static final long serialVersionUID = 1L;
	private PrintWriter out;  

    public ThanhthinongthonUpdateSvl() 
    {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		HttpSession session = request.getSession();
		
		this.out = response.getWriter();
		Utility util = new Utility();
		
    	String querystring = request.getQueryString();
	    String userId = util.getUserId(querystring);
	    
	    out.println(userId);
	    
	    if (userId.length()==0)
	    	userId = util.antiSQLInspection(request.getParameter("userId"));
	    
	    String id = util.getId(querystring);  	
	    
	    System.out.println("id = " + id);
	    IThanhthinongthon kvBean = new Thanhthinongthon(id);
	    
        kvBean.setUserId(userId);
        session.setAttribute("kvBean", kvBean);
        String nextJSP = request.getContextPath() + "/pages/Center/ThanhthinongthonUpdate.jsp";
        response.sendRedirect(nextJSP);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		request.setCharacterEncoding("UTF-8");
	    response.setCharacterEncoding("UTF-8");
	    response.setContentType("text/html; charset=UTF-8");
		HttpSession session = request.getSession();
		
		IThanhthinongthon kvBean;
		this.out = response.getWriter();
		
		Utility util = new Utility();
		
	    String id =  util.antiSQLInspection(request.getParameter("id"));
	    if(id == null){  	
	    	id ="";
	    	kvBean = new Thanhthinongthon("");
	    }else{
	    	kvBean = new Thanhthinongthon(id);
	    }
	    
	    
		String userId = util.antiSQLInspection(request.getParameter("userId"));
		kvBean.setUserId(userId);
	    
		String ttnt = util.antiSQLInspection(request.getParameter("ttnt"));
		if (ttnt == null)
			ttnt = "";				
    	kvBean.setTen(ttnt);
    	
    	

    	
    	String diengiai = util.antiSQLInspection(request.getParameter("diengiai"));
		if (diengiai == null)
			diengiai = "";
		kvBean.setDiengiai(diengiai);

		String trangthai = util.antiSQLInspection(request.getParameter("trangthai"));
    	if (trangthai == null)
    		trangthai = "0";
    	else
    		trangthai = "1";
    	kvBean.setTrangthai(trangthai);

		String ngaysua = getDateTime();
    	kvBean.setNgaysua(ngaysua);
		
		String nguoisua = userId;
		kvBean.setNguoisua(nguoisua);
    	
		
		boolean error = false;
				
		if (ttnt.trim().length()== 0){
			kvBean.setMessage("Bạn chưa nhập Thành thị nông thôn");
			error = true;
		}


		if (diengiai.trim().length()== 0){
			kvBean.setMessage("Bạn chưa nhập Diễn giải");
			error = true;
		}
 		
 		String action = request.getParameter("action");
	    if (!error){
	    	if(action.equals("save"))
	    	{
	    		if ( id == null || id.trim().length()<=0){
	    			if (!(kvBean.CreateThanhthinongthon())){				
	    				session.setAttribute("kvBean", kvBean);
	    				kvBean.setUserId(userId);
	    				String nextJSP = request.getContextPath() + "/pages/Center/ThanhthinongthonUpdate.jsp";
	    				response.sendRedirect(nextJSP);
	    			}else{
	    				IThanhthinongthonList obj = new ThanhthinongthonList();
	    				obj.setUserId(userId);
	    				session.setAttribute("obj", obj);
						
	    				String nextJSP = request.getContextPath() + "/pages/Center/Thanhthinongthon.jsp";
	    				response.sendRedirect(nextJSP);			    			    									
	    			}
				
	    		}else{
	    			if (!(kvBean.UpdateThanhthinongthon())){			
	    				session.setAttribute("kvBean", kvBean);
	    				String nextJSP = request.getContextPath() + "/pages/Center/ThanhthinongthonUpdate.jsp";
	    				response.sendRedirect(nextJSP);
	    			}
	    			else{
	    				IThanhthinongthonList obj = new ThanhthinongthonList();
	    				obj.setUserId(userId);
	    				session.setAttribute("obj", obj);
						
	    				String nextJSP = request.getContextPath() + "/pages/Center/Thanhthinongthon.jsp";
	    				response.sendRedirect(nextJSP);			    			    									
	    			}
	    		}
	    	}else{
	    		kvBean.setUserId(userId);
	    		session.setAttribute("kvBean", kvBean);
			
	    		String nextJSP;
	    		nextJSP = request.getContextPath() + "/pages/Center/ThanhthinongthonUpdate.jsp";   
	    		response.sendRedirect(nextJSP);
			
	    	}
	    }else{
    		kvBean.setUserId(userId);
    		session.setAttribute("kvBean", kvBean);
		
    		String nextJSP;
    		nextJSP = request.getContextPath() + "/pages/Center/ThanhthinongthonUpdate.jsp";   
    		response.sendRedirect(nextJSP);
	    	
	    }
	}
	
	private String getDateTime() {
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date date = new Date();
        return dateFormat.format(date);	
	}

}
