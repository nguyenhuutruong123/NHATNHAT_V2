package geso.dms.center.servlets.doisolo;

import geso.dms.center.util.Utility;
import geso.dms.center.beans.doisolo.IErpDoisolo;
import geso.dms.center.beans.doisolo.IErpDoisoloList;
import geso.dms.center.beans.doisolo.imp.ErpDoisolo;
import geso.dms.center.beans.doisolo.imp.ErpDoisoloList;
import geso.dms.center.db.sql.dbutils;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Hashtable;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


public class ErpDoiSoLoUpdateSvl extends HttpServlet
{
	private static final long serialVersionUID = 1L;
	
	public ErpDoiSoLoUpdateSvl()
	{
		super();
		
	}
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		HttpSession session = request.getSession();
		
		IErpDoisolo pxkBean;
		PrintWriter out; 
		dbutils db;
		
		out = response.getWriter();
		Utility util = new Utility();
		
    	String querystring = request.getQueryString();
	    String userId = util.getUserId(querystring);
	    
	    out.println(userId);
	    
	    if (userId.length() == 0)
	    	userId = request.getParameter("userId");
	    	    
	    String id = util.getId(querystring);  	

	    pxkBean = new ErpDoisolo(id);
	    pxkBean.setUserId(userId);
	  
        
        String nextJSP = "";
        if(querystring.indexOf("display") > 0)
        {
          pxkBean.initDisplay();
        	nextJSP = request.getContextPath() + "/pages/Center/ErpDoisoloDisplay.jsp";
        }
        else
        {
          pxkBean.init();
        	nextJSP = request.getContextPath() + "/pages/Center/ErpDoisoloUpdate.jsp";
        }
        
        session.setAttribute("pxkBean", pxkBean);
        response.sendRedirect(nextJSP);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		request.setCharacterEncoding("UTF-8");
	    response.setCharacterEncoding("UTF-8");
	    response.setContentType("text/html; charset=UTF-8");
		HttpSession session = request.getSession();
		
		IErpDoisolo pxkBean;
		PrintWriter out; 
		dbutils db;
		
		out = response.getWriter();
		
	    String id = request.getParameter("id");	
	    if(id == null){  	
	    	pxkBean = new ErpDoisolo("");
	    }else{
	    	pxkBean = new ErpDoisolo(id);
	    }
	    	    
		String userId = request.getParameter("userId");
		pxkBean.setUserId(userId);
	        	
    	String nppId = request.getParameter("nppId");
		if (nppId == null)
			nppId = "";
		pxkBean.setNppId(nppId);
    	
		String ngaychuyen = request.getParameter("ngaychuyen");
		if (ngaychuyen == null || ngaychuyen.length() < 10)
			ngaychuyen = getDateTime();
		pxkBean.setNgaychuyen(ngaychuyen);
		
		String kbhId = request.getParameter("kbhId");
		if (kbhId == null)
			kbhId = "";
		pxkBean.setNvbhId(kbhId);
		
		String khoId = request.getParameter("khoId");
		if (khoId == null)
			khoId = "";
		pxkBean.setKhoId(khoId);
		
		String[] spIds = request.getParameterValues("spIds");
		
		String[] soloOLD = request.getParameterValues("soloOLD");
		String[] solo = request.getParameterValues("soloDOI");
		String[] soluong = request.getParameterValues("soluongDOI");
		String[] ngayhethan = request.getParameterValues("ngayhethanDOI");
		String[] ngayhethanOLD = request.getParameterValues("ngayhethanOLD");
		String[] ngaynhapkhoOLD = request.getParameterValues("ngaynhapkhoOLD");
		
		if(soluong != null)
		{
			Hashtable<String, String> sp_ngayhethanOLD = new Hashtable<String, String>();
			Hashtable<String, String> sp_soloOLD = new Hashtable<String, String>();
			Hashtable<String, String> sp_solo = new Hashtable<String, String>();
			Hashtable<String, Integer> sp_sl = new Hashtable<String, Integer>();
			Hashtable<String, String> sp_hansudung = new Hashtable<String, String>();
			Hashtable<String, String> sp_ngaynhapkho = new Hashtable<String, String>();
			
			for(int i = 0; i < soluong.length; i++)
			{
				if(soluong[i].trim().length() > 0 && solo[i].trim().length() > 0 && ngayhethan[i].trim().length() > 0 )
				{
					sp_ngayhethanOLD.put(spIds[i] + "__" + soloOLD[i]   +"__"  +   ngayhethanOLD[i]   , ngayhethanOLD[i]);
					sp_soloOLD.put(    spIds[i] + "__" + soloOLD[i]			+"__"  + ngayhethanOLD[i]     , soloOLD[i]);
					sp_solo.put(  spIds[i] + "__" + soloOLD[i] 					+"__"  + ngayhethanOLD[i]    			, solo[i]);
					sp_sl.put(   spIds[i] + "__" + soloOLD[i] 					+"__"  + ngayhethanOLD[i]  					, Integer.parseInt(soluong[i].replaceAll(",", "")) );
					sp_hansudung.put(spIds[i] + "__" + soloOLD[i] 			+"__"  + ngayhethanOLD[i]  			, ngayhethan[i]);
					sp_ngaynhapkho.put(spIds[i] + "__" + soloOLD[i] 			+"__"  + ngayhethanOLD[i]  			, ngaynhapkhoOLD[i]);
				}
			}
			
			pxkBean.setSp_ngayhethanOLD(sp_ngayhethanOLD);
			pxkBean.setSSp_SoloOLD(sp_soloOLD);
			pxkBean.setSSp_Solo(sp_solo);
			pxkBean.setSSp_Soluong(sp_sl);
			pxkBean.setSSp_Ngayhethan(sp_hansudung);
		}
		
 		String action = request.getParameter("action");
		if(action.equals("save"))
		{
			db = new dbutils();
			if (id == null)
			{
				if (!(pxkBean.CreateCk(request)))
				{	
					pxkBean.createRS();
					session.setAttribute("pxkBean", pxkBean);			
					String nextJSP = request.getContextPath() + "/pages/Center/ErpDoisoloNew.jsp";
					response.sendRedirect(nextJSP);
				}
				else{
					IErpDoisoloList obj = new ErpDoisoloList();
					obj.setUserId(userId);
					
					obj.init("");
					session.setAttribute("obj", obj);
						
					String nextJSP = request.getContextPath() + "/pages/Center/ErpDoisolo.jsp";
					response.sendRedirect(nextJSP);			    			    									
				}				
			}	
			else
			{
				if (!(pxkBean.UpdateCk(request)))
				{	
					System.out.println("___Khong the cap nhat: " + pxkBean.getMessage());
					pxkBean.createRS();
					session.setAttribute("pxkBean", pxkBean);			
					String nextJSP = request.getContextPath() + "/pages/Center/ErpDoisoloUpdate.jsp";
					response.sendRedirect(nextJSP);
				}
				else{
					IErpDoisoloList obj = new ErpDoisoloList();
					obj.setUserId(userId);
					
					obj.init("");
					session.setAttribute("obj", obj);
						
					String nextJSP = request.getContextPath() + "/pages/Center/ErpDoisolo.jsp";
					response.sendRedirect(nextJSP);			    			    									
				}	
			}
		}
		else
		{
			System.out.println("vao day ne :::::::");
			pxkBean.createRS();
			session.setAttribute("pxkBean", pxkBean);
			
			String nextJSP;
			if (id == null)
			{			
				nextJSP = request.getContextPath() + "/pages/Center/ErpDoisoloNew.jsp";
			}
			else
			{
				nextJSP = request.getContextPath() + "/pages/Center/ErpDoisoloUpdate.jsp";   						
			}
			response.sendRedirect(nextJSP);							
		}
	}
	
	private String getDateTime()
	{
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date date = new Date();
        return dateFormat.format(date);	
	}
	
	
}
