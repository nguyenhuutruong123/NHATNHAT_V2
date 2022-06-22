package geso.dms.center.servlets.kenhbanhang;
import geso.dms.center.beans.kenhbanhang.imp.*;
import geso.dms.center.beans.kenhbanhang.*;
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

public class KenhbanhangUpdateSvl extends HttpServlet 
{
	private static final long serialVersionUID = 1L;
	private PrintWriter out;  

    public KenhbanhangUpdateSvl() 
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

	    IKenhbanhang kbhBean = new Kenhbanhang(id);	    
	    
        kbhBean.setUserId(userId);
        session.setAttribute("kbhBean", kbhBean);
        
         String update = util.antiSQLInspection(request.getParameter("update"));
	       if(update==null)
	    	   update="";


	       String display = util.antiSQLInspection(request.getParameter("display"));
	       if(display==null)
	    	   display="";
	     
	       String nextJSP = "";
	       if(update.trim().length()>0)
	    	   nextJSP = request.getContextPath() + "/pages/Center/KenhBanHangUpdate.jsp";
	       if(display.trim().length()>0)
	    	   nextJSP = request.getContextPath() + "/pages/Center/KenhBanHangDisplay.jsp";
		
        
       
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
		IKenhbanhang kbhBean;
		this.out = response.getWriter();
		
		Utility util = new Utility();
		
	    String id =  util.antiSQLInspection(request.getParameter("id"));
	    if(id == null){  	
	    	kbhBean = new Kenhbanhang("");
	    }else{
	    	kbhBean = new Kenhbanhang(id);
	    }
	    
	    
		String userId = util.antiSQLInspection(request.getParameter("userId"));
		kbhBean.setUserId(userId);
	    
    	String kenhbanhang = util.antiSQLInspection(request.getParameter("kenhbanhang"));
		if (kenhbanhang == null)
			kenhbanhang = "";				
    	kbhBean.setKenhbanhang(kenhbanhang);
    	
    	String diengiai = util.antiSQLInspection(request.getParameter("diengiai"));
		if (diengiai == null)
			diengiai = "";
		kbhBean.setDiengiai(diengiai);

		String trangthai = util.antiSQLInspection(request.getParameter("trangthai"));
    	if (trangthai == null)
    		trangthai = "0";
    	else
    		trangthai = "1";
    	kbhBean.setTrangthai(trangthai);

		String ngaysua = getDateTime();
    	kbhBean.setNgaysua(ngaysua);
		
		String nguoisua = userId;
		kbhBean.setNguoisua(nguoisua);
    		
		boolean error = false;
				
		if (kenhbanhang.trim().length()== 0){
			kbhBean.setMessage("Vui Long Nhap Ten Kenh Ban Hang");
			error = true;
		}

		if (diengiai.trim().length()== 0){
			kbhBean.setMessage("Vui Long Nhap Dien Giai Kenh Cua Hang");
			error = true;
		}
 		
 		String action = request.getParameter("action");
	    
		if(action.equals("save"))
		{
			if ( id == null){
				if (!(kbhBean.CreateKbh())){				
					session.setAttribute("kbhBean", kbhBean);
					kbhBean.setUserId(userId);
					String nextJSP = request.getContextPath() + "/pages/Center/KenhBanHangNew.jsp";
					response.sendRedirect(nextJSP);
				}else{
					IKenhbanhangList obj = new KenhbanhangList();
					obj.setUserId(userId);
					session.setAttribute("obj", obj);
						
					String nextJSP = request.getContextPath() + "/pages/Center/KenhBanHang.jsp";
					response.sendRedirect(nextJSP);			    			    									
				}
				
			}else{
				if (!(kbhBean.UpdateKbh())){			
					session.setAttribute("kbhBean", kbhBean);
					String nextJSP = request.getContextPath() + "/pages/Center/KenhBanHangUpdate.jsp";
					response.sendRedirect(nextJSP);
				}
				else{
					IKenhbanhangList obj = new KenhbanhangList();
					obj.setUserId(userId);
					session.setAttribute("obj", obj);
						
					String nextJSP = request.getContextPath() + "/pages/Center/KenhBanHang.jsp";
					response.sendRedirect(nextJSP);			    			    									
				}
			}
		}else{
			kbhBean.setUserId(userId);
			session.setAttribute("kbhBean", kbhBean);
			
			String nextJSP;
			if (id == null){			
				nextJSP = request.getContextPath() + "/pages/Center/KenhBanHangNew.jsp";
			}else{
				nextJSP = request.getContextPath() + "/pages/Center/KenhBanHangUpdate.jsp";   						
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
