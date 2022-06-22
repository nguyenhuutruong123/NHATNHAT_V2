package geso.dms.center.servlets.hanmucdoanhthu;

import geso.dms.center.beans.hanmucdoanhthu.Ihanmucdoanhthu;
import geso.dms.center.beans.hanmucdoanhthu.imp.hanmucdoanhthu;




import java.io.IOException;
import java.util.Enumeration;
import java.util.Hashtable;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class hanmucdoanhthuUpdateSvl
 */
@WebServlet("/hanmucdoanhthuUpdateSvl")
public class hanmucdoanhthuUpdateSvl extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public hanmucdoanhthuUpdateSvl() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setCharacterEncoding("UTF-8");
	    response.setContentType("text/html; charset=UTF-8");
	    
	    HttpSession session = request.getSession();
	    
	    Ihanmucdoanhthu obj=new hanmucdoanhthu();
	    String userId = request.getParameter("userId");
	    String action = request.getParameter("action");
	    String update = request.getParameter("update");
	    if(update!=null )
	    {
	    	obj.setId(update);
	    	obj.init(update);
	    	session.setAttribute("obj", obj);
		    String nextJSP = request.getContextPath() + "/pages/Center/hanmucdoanhthuupdate.jsp";
			response.sendRedirect(nextJSP);
			return;
	    }
	    String delete = request.getParameter("delete");
	    if(delete!=null)
	    {
	    	obj.setId(delete);
	    	obj.delete();
	    	obj.init();
	    	session.setAttribute("obj", obj);
	    	 String nextJSP = request.getContextPath() + "/pages/Center/hanmucdoanhthu.jsp";
			response.sendRedirect(nextJSP);
			return;
	    }
	    String display = request.getParameter("display");
	    if(display!=null)
	    {
	    	obj.setId(display);
	    	obj.init(display);
	    	session.setAttribute("obj", obj);
		    String nextJSP = request.getContextPath() + "/pages/Center/hanmucdoanhthudisplay.jsp";
			response.sendRedirect(nextJSP);
			return;
	    }
	    String chot = request.getParameter("chot");
	    if(chot!=null)
	    {
	    	obj.setId(chot);
	    	obj.chot();
	    	obj.init();
	    	session.setAttribute("obj", obj);
	    	 String nextJSP = request.getContextPath() + "/pages/Center/hanmucdoanhthu.jsp";
			response.sendRedirect(nextJSP);
			return;
	    }
	    
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
	    response.setCharacterEncoding("UTF-8");
	    response.setContentType("text/html; charset=UTF-8");
	    
	    HttpSession session = request.getSession();
	    
	    Ihanmucdoanhthu obj=new hanmucdoanhthu();
	    String userId = request.getParameter("userId");
	    String action = request.getParameter("action");
	    String tungay= request.getParameter("tungay");
	    String id= request.getParameter("id");
	    if(id!=null)
	    	obj.setId(id);
	    if(tungay!=null)
	    	obj.setTungay(tungay);
	    String denngay= request.getParameter("denngay");
	    if(denngay!=null)
	    	obj.setDenngay(denngay);
	    String [] nppids=request.getParameterValues("npp");
	    String [] hanmucs=request.getParameterValues("hanmuc");
	    Hashtable<String, String> htb=new Hashtable<String, String>();
	    for(int i=0;i<nppids.length;i++)
	    {
	   // 	System.out.println("nppId :"+nppids[i]+" hanmuc:"+hanmucs[i]);
	    	htb.put(nppids[i], hanmucs[i]);
	    }
	    
	   
		
		obj.setHhanmuc(htb);
		if(action.equals("save"))
		{
			/*Enumeration e = htb.keys();
			while (e.hasMoreElements()) {
			      String key = (String) e.nextElement();
			      System.out.println(key + " : " + htb.get(key));
			    }*/
			if(obj.save()==true)
			{
				obj.init();
				session.setAttribute("obj", obj);
				 String nextJSP = request.getContextPath() + "/pages/Center/hanmucdoanhthu.jsp";
					response.sendRedirect(nextJSP);
					return;
			}
			else
			{
				obj.init();
				session.setAttribute("obj", obj);
				 String nextJSP = request.getContextPath() + "/pages/Center/hanmucdoanhthunew.jsp";
					response.sendRedirect(nextJSP);
					return;
			}
			
		}
		if(action.equals("updatehm"))
		{
			/*Enumeration e = htb.keys();
			while (e.hasMoreElements()) {
			      String key = (String) e.nextElement();
			      System.out.println(key + " : " + htb.get(key));
			    }*/
			if(obj.update()==true)
			{
				obj.init();
				session.setAttribute("obj", obj);
				System.out.println("id____________________________"+obj.getId());
				 String nextJSP = request.getContextPath() + "/pages/Center/hanmucdoanhthu.jsp";
					response.sendRedirect(nextJSP);
					return;
			}
			else
			{
				obj.init(obj.getId());
				session.setAttribute("obj", obj);
				 String nextJSP = request.getContextPath() + "/pages/Center/hanmucdoanhthuupdate.jsp";
					response.sendRedirect(nextJSP);
					return;
			}
			
		}
		
		session.setAttribute("obj", obj);
	    String nextJSP = request.getContextPath() + "/pages/Center/hanmucdoanhthunew.jsp";
		response.sendRedirect(nextJSP);
	    
	}

}
