package geso.dms.center.servlets.khoasothang;

import geso.dms.center.util.Utility;
import geso.dms.center.beans.khoasothang.IErpKhoasothang;
import geso.dms.center.beans.khoasothang.imp.ErpKhoaSoThang;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class ErpKhoasothangSvl extends HttpServlet 
{
	private static final long serialVersionUID = 1L;
    
    public ErpKhoasothangSvl() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		IErpKhoasothang obj;
		request.setCharacterEncoding("UTF-8");
	    response.setCharacterEncoding("UTF-8");
	    response.setContentType("text/html; charset=UTF-8");
	    	    
	    HttpSession session = request.getSession();	    

	    Utility util = new Utility();
	    	    
	    String querystring = request.getQueryString();
	    String userId = util.getUserId(querystring);
	    
	    if (userId.length()==0)
	    	userId = util.antiSQLInspection(request.getParameter("userId"));
	   
	    obj = new ErpKhoaSoThang();
	    obj.setUserId(userId);
	    
	    String task = request.getParameter("task");
	    if(task == null)
	    	task = "";
	    
	    String nextJSP = "";
	    if(task.equals("kskt"))
	    {
	    	obj.initKSKT();
			nextJSP = request.getContextPath() + "/pages/Center/ErpKhoaSoKeToan.jsp";
	    }
	    else
	    {
		    obj.init();
			nextJSP = request.getContextPath() + "/pages/Center/ErpKhoaSoThang.jsp";
	    }
	    
	    session.setAttribute("obj", obj);
	    response.sendRedirect(nextJSP);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		IErpKhoasothang obj;
		request.setCharacterEncoding("UTF-8");
	    response.setCharacterEncoding("UTF-8");
	    response.setContentType("text/html; charset=UTF-8");
	    	    
	    HttpSession session = request.getSession();	    

	    Utility util = new Utility();
	    	    
	    String querystring = request.getQueryString();
	    String userId = util.getUserId(querystring);
	    
	    if (userId.length()==0)
	    	userId = util.antiSQLInspection(request.getParameter("userId"));
	   
	    obj = new ErpKhoaSoThang();
	    obj.setUserId(userId);
	    
	    String thang = util.antiSQLInspection(request.getParameter("thang"));
	    if(thang == null)
	    	thang = "";
	    obj.setThang(thang);
	    
	    String nam = util.antiSQLInspection(request.getParameter("nam"));
	    if(nam == null)
	    	nam = "";
	    obj.setNam(nam);
	    
	    String action = request.getParameter("action");
	    if(action == null)
	    	action = "";
	    
	    String msg = "";
	    String nextJSP = "";
	    
	    if(action.equals("kskt"))
	    {
	    	msg = obj.KhoaSoKeToan();
		    if(msg.trim().length() <= 0)
		    {
		    	msg = "Khóa sổ tháng: " + thang + ", năm: " + nam + " thành công.";
		    }
		    
	    	obj.initKSKT(); 
	    	obj.setMsg(msg);
	 		nextJSP = request.getContextPath() + "/pages/Center/ErpKhoaSoKeToan.jsp";
	    }
	    else
	    {
		    msg = obj.KhoaSoThang();
		    if(msg.trim().length() <= 0)
		    {
		    	msg = "Khóa sổ tháng: " + thang + ", năm: " + nam + " thành công.";
		    }
		    
	    	obj.init(); 
	    	obj.setMsg(msg);
	 		nextJSP = request.getContextPath() + "/pages/Center/ErpKhoaSoThang.jsp";
	    }
	    
	    session.setAttribute("obj", obj);
	    response.sendRedirect(nextJSP);
	   
	}

}
