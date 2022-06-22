package geso.dms.distributor.servlets.chucdanh;

import geso.dms.distributor.beans.chucdanh.IChucdanhList;
import geso.dms.distributor.beans.chucdanh.imp.ChucdanhList;
import geso.dms.distributor.beans.chucdanh.IChucdanh;
import geso.dms.distributor.beans.chucdanh.imp.Chucdanh;
import geso.dms.center.db.sql.dbutils;
import geso.dms.center.util.Utility;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.ResultSet;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


public class Erp_ChucdanhSvl extends HttpServlet {
	   static final long serialVersionUID = 1L;
	   
	   public Erp_ChucdanhSvl() {
			super();
		}   	
		
		protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		    request.setCharacterEncoding("UTF-8");
		    response.setCharacterEncoding("UTF-8");
		    response.setContentType("text/html; charset=UTF-8");
		    
		    PrintWriter out = response.getWriter();
		    HttpSession session = request.getSession();		    
		    	
		    String querystring = request.getQueryString();
		    Utility util = new Utility();
		    
		    String userId = util.getUserId(querystring);
		    out.println(userId);
		    
		    if (userId.length()==0)
		    	userId = util.antiSQLInspection(request.getParameter("userId"));
		    
		    String action = util.getAction(querystring);
		    out.println(action);
		    	    
		    String Id = util.getId(querystring);
		    
		    if(Id.contains(";")){
		    	Id = Id.split(";")[1];
		    }
		    		  
		    out.println(Id);

		    if (action.equals("delete")){	   		
			 	Delete(Id);
		    }
		    
		    String ctyId = (String)session.getAttribute("congtyId");

		    IChucdanhList obj = (IChucdanhList) new ChucdanhList(ctyId);

		    session.setAttribute("obj", obj);
		    session.setAttribute("userId", userId);
				
		    String nextJSP = request.getContextPath() + "/pages/Distributor/Erp_Chucdanh.jsp";
		    response.sendRedirect(nextJSP);
			
		}  	

		protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		    request.setCharacterEncoding("UTF-8");
		    response.setCharacterEncoding("UTF-8");
		    response.setContentType("text/html; charset=UTF-8");
		    Utility util = new Utility();
		    
			PrintWriter out = response.getWriter();
		    HttpSession session = request.getSession();

		    String ctyId = (String)session.getAttribute("congtyId");
		    
		    IChucdanhList obj = (IChucdanhList) new ChucdanhList(ctyId);
		    String userId = util.antiSQLInspection(request.getParameter("userId"));
		    obj.setUserId(userId);
		    
		 		    
		    // Create a new Business Unit
		    if (request.getParameter("action").equals("new")){

		    	IChucdanh cdBean = new Chucdanh();
		    	cdBean.setCtyId(ctyId);
		    	cdBean.setUserId(userId);
		    	// Save Data into session
	    		session.setAttribute("cdBean", cdBean);
	    		session.setAttribute("userId", userId);
	    		cdBean.creaters();
		    	String nextJSP = request.getContextPath() + "/pages/Distributor/Erp_ChucdanhNew.jsp";
	    		response.sendRedirect(nextJSP);
		    
		    }
		 // Perform searching. Each Business Unit is saved into Donvikinhdoanh
		    else {   	

		    	session.setAttribute("obj", obj);
	    		session.setAttribute("userId", userId);
	    		
	    		response.sendRedirect("/TraphacoERP/pages/Erp/Erp_Chucdanh.jsp");
		    }

		}

		private void Delete(String id){
			dbutils db = new dbutils();
						
		    String sql = " UPDATE   ERP_CHUCDANH SET TRANGTHAI =0 WHERE PK_SEQ = '" + id + "'";
		    if(!db.update(sql)){
//		    	obj.setMsg("Chức danh này đã tham gia trong chính sách duyệt PO rồi, nên không xóa được");
		    }
		    
		    System.out.println(sql);
		    
		    db.shutDown();		    	
		}
		    
		
		private ResultSet getCdBeanList(String query){  	
			dbutils db = new dbutils();
			ResultSet rs =  db.get(query);
			return rs;
		}	
}
