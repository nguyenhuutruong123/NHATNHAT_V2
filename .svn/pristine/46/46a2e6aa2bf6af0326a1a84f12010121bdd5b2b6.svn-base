package geso.dms.center.servlets.phuongxa;

import geso.dms.center.beans.phuongxa.*;
import geso.dms.center.beans.phuongxa.imp.Phuongxa;
import geso.dms.center.beans.phuongxa.imp.PhuongxaList;
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

public class PhuongxaUpdateSvl extends HttpServlet 
{
	private static final long serialVersionUID = 1L;
	private PrintWriter out;  

    public PhuongxaUpdateSvl() 
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
	    String action = util.getAction(querystring);
	    
	    out.println(userId);
	    
	    if (userId.length()==0)
	    	userId = util.antiSQLInspection(request.getParameter("userId"));
	    
	    String id = util.getId(querystring);  	

	    IPhuongxa kvBean = new Phuongxa(id);
	    
        kvBean.setUserId(userId);
        session.setAttribute("kvBean", kvBean);
        String nextJSP = request.getContextPath() + "/pages/Center/PhuongXaUpdate.jsp";
        if(action.equals("display")) {
        	nextJSP = request.getContextPath() + "/pages/Center/PhuongXaDisplay.jsp";
        }
        
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
		IPhuongxa kvBean;
		this.out = response.getWriter();
		
		Utility util = new Utility();
		
	    String id =  util.antiSQLInspection(request.getParameter("id"));
	    if(id == null){  	
	    	kvBean = new Phuongxa("");
	    }else{
	    	kvBean = new Phuongxa(id);
	    }
	    
	    
		String userId = util.antiSQLInspection(request.getParameter("userId"));
		kvBean.setUserId(userId);
	    
		String ttId = util.antiSQLInspection(request.getParameter("tinhthanh"));
		if (ttId == null)
			ttId = "";				
    	kvBean.setTinhthanhId(ttId);
    	
    	String qhId = util.antiSQLInspection(request.getParameter("quanhuyen"));
		if (qhId == null)
			qhId = "";				
    	kvBean.setQuanhuyenId(qhId);
    	
    	String diengiai = util.antiSQLInspection(request.getParameter("diengiai"));
    	System.out.println("Diengiai : "+diengiai);
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

 		String action = util.antiSQLInspection(request.getParameter("action"));
	    
	    	if(action.equals("save"))
	    	{
	    		if (!kiemtraNhap(ttId, qhId, diengiai, kvBean)){
		    		if ( id == null||id.length()==0){
		    			if (!(kvBean.CreatePx())){				
		    				session.setAttribute("kvBean", kvBean);
		    				kvBean.setUserId(userId);
		    				String nextJSP = request.getContextPath() + "/pages/Center/PhuongXaNew.jsp";
		    				response.sendRedirect(nextJSP);
		    			}else{
		    				IPhuongxaList obj = new PhuongxaList();
		    				obj.setUserId(userId);
		    				session.setAttribute("obj", obj);
							
		    				String nextJSP = request.getContextPath() + "/pages/Center/PhuongXa.jsp";
		    				response.sendRedirect(nextJSP);			    			    									
		    			}
					
		    		}else{
		    			if (!(kvBean.UpdatePx())){			
		    				session.setAttribute("kvBean", kvBean);
		    				String nextJSP = request.getContextPath() + "/pages/Center/PhuongXaUpdate.jsp";
		    				response.sendRedirect(nextJSP);
		    			}
		    			else{
		    				IPhuongxaList obj = new PhuongxaList();
		    				obj.setUserId(userId);
		    				session.setAttribute("obj", obj);
							
		    				String nextJSP = request.getContextPath() + "/pages/Center/PhuongXa.jsp";
		    				response.sendRedirect(nextJSP);			    			    									
		    			}
		    		}
	    	}else{
	    		kvBean.setUserId(userId);
	    		session.setAttribute("kvBean", kvBean);
			
	    		String nextJSP;
	    		if (id == null){			
	    			nextJSP = request.getContextPath() + "/pages/Center/PhuongXaNew.jsp";
	    		}else{
	    			nextJSP = request.getContextPath() + "/pages/Center/PhuongXaUpdate.jsp";   						
	    		}
	    		response.sendRedirect(nextJSP);
			
	    	}
	    }else{
    		kvBean.setUserId(userId);
    		kvBean.createRS();
    		session.setAttribute("kvBean", kvBean);
		
    		String nextJSP;
    		if (id == null){			
    			nextJSP = request.getContextPath() + "/pages/Center/PhuongXaNew.jsp";
    		}else{
    			nextJSP = request.getContextPath() + "/pages/Center/PhuongXaUpdate.jsp";   						
    		}
    		response.sendRedirect(nextJSP);
	    	
	    }
	}
	
	private String getDateTime() {
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date date = new Date();
        return dateFormat.format(date);	
	}
	
	private boolean kiemtraNhap(String ttId, String qhId, String diengiai, IPhuongxa kvBean)
	{
		boolean error = false;
		
		if (ttId.trim().length()== 0){
			kvBean.setMessage("Vui lòng chọn thông tin tỉnh thành.");
			error = true;
		}
		
		if (qhId.trim().length()== 0){
			kvBean.setMessage("Vui lòng chọn thông tin quận huyện.");
			error = true;
		}

		if (diengiai.trim().length()== 0){
			kvBean.setMessage("Vui lòng nhập đầy đủ thông tin.");
			error = true;
		}
		
		return error;
	}

}
