package geso.dms.distributor.servlets.Phanboquatang;



import geso.dms.center.util.Utility;
import geso.dms.distributor.beans.Phanboquatang.IPhanboquatang;
import geso.dms.distributor.beans.Phanboquatang.IPhanboquatangList;
import geso.dms.distributor.beans.Phanboquatang.imp.Phanboquatang;
import geso.dms.distributor.beans.Phanboquatang.imp.PhanboquatangList;
import geso.dms.distributor.beans.denghidathang.imp.Denghidathang;
import geso.dms.distributor.db.sql.dbutils;

import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.sql.ResultSet;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;




 public class PhanboquatangUpdateSvl extends javax.servlet.http.HttpServlet implements javax.servlet.Servlet {
   static final long serialVersionUID = 1L;
  

   public PhanboquatangUpdateSvl() {
		super();
	}   	
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
	    request.setCharacterEncoding("UTF-8");
	    response.setCharacterEncoding("UTF-8");
	    response.setContentType("text/html; charset=UTF-8");
	    PrintWriter out = response.getWriter();
	    
		HttpSession session = request.getSession();
		
		out = response.getWriter();
		Utility util = new Utility();
		
    	String querystring = request.getQueryString();
	    String userId = util.getUserId(querystring);
	    
	    
	    
	    out.println(userId);
	    
	    if (userId.length()==0)
	    	userId = util.antiSQLInspection(request.getParameter("userId"));
	    
	    String id = util.getId(querystring);  	
	    out.println(id);
	    
	    IPhanboquatang bgBean = new Phanboquatang();
	    
	     
        bgBean.setUserId(userId);
        bgBean.setId(id);
        bgBean.createRS();
        String nextJSP="";
        if(querystring.contains("display"))
        	nextJSP = request.getContextPath() + "/pages/Distributor/Phanboquatangdisplay.jsp";
        else
         nextJSP = request.getContextPath() + "/pages/Distributor/PhanboquatangUpdate.jsp";
        if(querystring.contains("copy"))
        {
        	nextJSP = request.getContextPath() + "/pages/Distributor/PhanboquatangNew.jsp";
        	 bgBean.setId(null);
        	 bgBean.setTrangthai("0");
        	 bgBean.setTungay(Utility.getNgayHienTai());
        }
        
        session.setAttribute("bgblcBean", bgBean);
        
        response.sendRedirect(nextJSP);

	}  	
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
	    request.setCharacterEncoding("UTF-8");
	    response.setCharacterEncoding("UTF-8");
	    response.setContentType("text/html; charset=UTF-8");
		HttpSession session = request.getSession();

		OutputStream out = response.getOutputStream();
		
		IPhanboquatang bgBean = new Phanboquatang();
	    Utility util = new Utility();
	    
		String id =  util.antiSQLInspection(request.getParameter("id"));
	    
	    if(id == null){  	
	    	id = "";
	    }
	    bgBean.setId(id);
	    
	    
		String userId = util.antiSQLInspection(request.getParameter("userId"));
		bgBean.setUserId(userId);
	    
    	String MA = request.getParameter("MA");
		if (MA == null)
			MA = "";				
    	bgBean.setMa(MA);

		String diengiai = util.antiSQLInspection(request.getParameter("diengiai"));
		if (diengiai == null)
			diengiai = "";
		bgBean.setDiengiai(diengiai);
		
		String tungay = util.antiSQLInspection(request.getParameter("tungay"));
		if (tungay == null)
			tungay = "";
		bgBean.setTungay(tungay);
		
		String denngay = util.antiSQLInspection(request.getParameter("denngay"));
		if (denngay == null)
			denngay = "";
		bgBean.setDenngay(denngay);
		
		
	        	
		String trangthai = util.antiSQLInspection(request.getParameter("trangthai"));
    	if (trangthai == null)
    		trangthai = "0";
    	else
    		trangthai = "1";
    	bgBean.setTrangthai(trangthai);
		
    	
    	String sanphamid = util.antiSQLInspection(request.getParameter("sanphamid"));
		if (sanphamid == null)
			sanphamid = "";
		bgBean.setSanphamid(sanphamid);
    	
    	
    	
    	
    	
    	
    	String[] ddkdIdArr = request.getParameterValues("ddkdIdArr");
    	bgBean.setDdkdidArr(ddkdIdArr);
    	String[] spsoluongArr = request.getParameterValues("spsoluongArr");
    	bgBean.setSoluongArr(spsoluongArr);
    	
    	
	
    	
		String ngaysua = getDateTime();
    	bgBean.setNgaysua(ngaysua);
    	bgBean.setNgaytao(ngaysua);
    	
		bgBean.setNguoitao(userId);
		bgBean.setNguoisua(userId);
    			
		boolean error = false;
				
		if (MA.trim().length()== 0){
			bgBean.setMsg("Vui lòng điền tên chương trình");
			error = true;
		}
		if (diengiai.trim().length()== 0){
			bgBean.setMsg("Vui lòng điền diễn giải chương trình");
			error = true;
		}
		if (tungay.trim().length()== 0){
			bgBean.setMsg("Vui lòng điền từ ngày");
			error = true;
		}
		if (denngay.trim().length()== 0){
			bgBean.setMsg("Vui lòng điền đến ngày");
			error = true;
		}
		
		if (sanphamid.trim().length()== 0){
			bgBean.setMsg("Vui lòng chọn sản phẩm");
			error = true;
		}

		String action = request.getParameter("action");
	   
		
		if(action.equals("save"))
		{
			if (id.length()==0)
			{
				if (!(bgBean.Createphanbo(request))){									
					bgBean.setUserId(userId);
					bgBean.createRS();
					session.setAttribute("bgblcBean", bgBean);
					String nextJSP = request.getContextPath() + "/pages/Distributor/PhanboquatangNew.jsp";
					response.sendRedirect(nextJSP);
				}else{
					IPhanboquatangList obj = new PhanboquatangList();
					obj.setUserId(userId);
					session.setAttribute("obj", obj);
					String nextJSP = request.getContextPath() + "/pages/Distributor/Phanboquatang.jsp";
					response.sendRedirect(nextJSP);			    			    									
				}
				
			}else{
				if (!(bgBean.Updatephanbo(request))){								
					bgBean.setUserId(userId);
					bgBean.createRS();
					session.setAttribute("bgblcBean", bgBean);
					String nextJSP = request.getContextPath() + "/pages/Distributor/PhanboquatangUpdate.jsp";
					response.sendRedirect(nextJSP);
				}
				else{
					IPhanboquatangList obj = new PhanboquatangList();
					obj.setUserId(userId);
					session.setAttribute("obj", obj);
						
					String nextJSP = request.getContextPath() + "/pages/Distributor/Phanboquatang.jsp";
					response.sendRedirect(nextJSP);			    			    									
				}
			}
		}else {
			System.out.println("id la "+id);
			String nextJSP;
			if (id.length()==0)
			{							
				bgBean.setUserId(userId);
				bgBean.createRS();
				session.setAttribute("bgblcBean", bgBean);
				nextJSP = request.getContextPath() + "/pages/Distributor/PhanboquatangNew.jsp";
			}
			else
			{
				bgBean.setUserId(userId);
				bgBean.setId(id);
				bgBean.createRS();
				session.setAttribute("bgblcBean", bgBean);
				
				nextJSP = request.getContextPath() + "/pages/Distributor/PhanboquatangUpdate.jsp";   						
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