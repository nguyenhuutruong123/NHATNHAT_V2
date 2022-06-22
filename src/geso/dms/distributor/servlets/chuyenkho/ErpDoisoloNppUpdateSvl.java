package geso.dms.distributor.servlets.chuyenkho;

import geso.dms.center.util.Utility;
import geso.dms.center.beans.doisolo.IErpDoisolo;
import geso.dms.center.beans.doisolo.IErpDoisoloList;
import geso.dms.center.beans.doisolo.imp.ErpDoisolo;
import geso.dms.center.beans.doisolo.imp.ErpDoisoloList;
import geso.dms.center.db.sql.dbutils;
import geso.dms.distributor.beans.chuyenkho.IErpDoisoloNpp;
import geso.dms.distributor.beans.chuyenkho.IErpDoisoloNppList;
import geso.dms.distributor.beans.chuyenkho.imp.ErpDoisoloNpp;
import geso.dms.distributor.beans.chuyenkho.imp.ErpDoisoloNppList;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Hashtable;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class ErpDoisoloNppUpdateSvl extends HttpServlet 
{
	private static final long serialVersionUID = 1L;
	
    public ErpDoisoloNppUpdateSvl() 
    {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		HttpSession session = request.getSession();
		
		IErpDoisoloNpp pxkBean;
		PrintWriter out; 
		dbutils db;
		String userId = (String) session.getAttribute("userId");  
		String userTen = (String) session.getAttribute("userTen");  	
		String sum = (String) session.getAttribute("sum");
		out = response.getWriter();
		Utility util = new Utility();
		geso.dms.center.util.Utility cutil = (geso.dms.center.util.Utility) session.getAttribute("util");

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
		if(!cutil.check(userId, userTen, sum)){
			response.sendRedirect(request.getContextPath() + "/redirect.jsp");
		}
		else
		{
			session.setMaxInactiveInterval(30000);
    	String querystring = request.getQueryString();
	     userId = util.getUserId(querystring);
	    
	    out.println(userId);
	    
	    if (userId.length() == 0)
	    	userId = util.antiSQLInspection(request.getParameter("userId"));
	    	    
	    String id = util.getId(querystring);  	

	    pxkBean = new ErpDoisoloNpp(id);
	    pxkBean.setUserId(userId);
	  
        
        String nextJSP = "";
        if(querystring.indexOf("display") > 0)
        {
          pxkBean.initDisplay();
        	nextJSP = request.getContextPath() + "/pages/Distributor/ErpDoiSoLoNppDisplay.jsp";
        }
        else
        {
          pxkBean.init();
        	nextJSP = request.getContextPath() + "/pages/Distributor/ErpDoiSoLoNppUpdate.jsp";
        }
        
        session.setAttribute("pxkBean", pxkBean);
        response.sendRedirect(nextJSP);
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		request.setCharacterEncoding("UTF-8");
	    response.setCharacterEncoding("UTF-8");
	    response.setContentType("text/html; charset=UTF-8");
		HttpSession session = request.getSession();
		String userId = (String) session.getAttribute("userId");  
		String userTen = (String) session.getAttribute("userTen"); 

		String sum = (String) session.getAttribute("sum");
	geso.dms.center.util.Utility cutil = (geso.dms.center.util.Utility) session.getAttribute("util");
		
		geso.dms.center.util.Csrf csdr=new geso.dms.center.util.Csrf(request,response,false,true ,false);
		Utility util = new Utility();
		if(!csdr.__validate_post())
		{
			response.sendRedirect(request.getContextPath() + "/redirect.jsp");
			return;
		}
		
		if(!cutil.check(userId, userTen, sum)){
			response.sendRedirect(request.getContextPath() + "/redirect.jsp");
		}
		else
		{
			session.setMaxInactiveInterval(30000);
		
		IErpDoisoloNpp pxkBean;
		PrintWriter out; 
		dbutils db;
		
		out = response.getWriter();
		
	    String id = util.antiSQLInspection(request.getParameter("id"));	
	    if(id == null){  	
	    	pxkBean = new ErpDoisoloNpp("");
	    }else{
	    	pxkBean = new ErpDoisoloNpp(id);
	    }
	    	    
		 userId = util.antiSQLInspection(request.getParameter("userId"));
		pxkBean.setUserId(userId);
	        	
    	String nppId = util.antiSQLInspection(request.getParameter("nppId"));
		if (nppId == null)
			nppId = "";
		pxkBean.setNppId(nppId);
    	
		String ngaychuyen = util.antiSQLInspection(request.getParameter("ngaychuyen"));
		if (ngaychuyen == null || ngaychuyen.length() < 10)
			ngaychuyen = getDateTime();
		pxkBean.setNgaychuyen(ngaychuyen);
		
		String kbhId = util.antiSQLInspection(request.getParameter("kbhId"));
		if (kbhId == null)
			kbhId = "";
		pxkBean.setNvbhId(kbhId);
		
		String khoId = util.antiSQLInspection(request.getParameter("khoId"));
		if (khoId == null)
			khoId = "";
		pxkBean.setKhoId(khoId);
		
		String[] spIds = request.getParameterValues("spIds");
		
		String[] soloOLD = request.getParameterValues("soloOLD");
		String[] solo = request.getParameterValues("soloDOI");
		String[] soluong = request.getParameterValues("soluongDOI");
		String[] ngayhethan = request.getParameterValues("ngayhethanDOI");
		String[] ngayhethanOLD = request.getParameterValues("ngayhethanOLD");
		String[] ngaynhapkhoOLD= request.getParameterValues("ngaynhapkhoOLD");
		
		if(soluong != null)
		{
			Hashtable<String, String> sp_ngayhethanOLD = new Hashtable<String, String>();
			Hashtable<String, String> sp_soloOLD = new Hashtable<String, String>();
			Hashtable<String, String> sp_ngaynhapkhoOLD = new Hashtable<String, String>();
			Hashtable<String, String> sp_solo = new Hashtable<String, String>();
			Hashtable<String, Integer> sp_sl = new Hashtable<String, Integer>();
			Hashtable<String, String> sp_hansudung = new Hashtable<String, String>();
		
			for(int i = 0; i < soluong.length; i++)
			{
				if(soluong[i].trim().length() > 0 && solo[i].trim().length() > 0 && ngayhethan[i].trim().length() > 0)
				{
					sp_ngaynhapkhoOLD.put(spIds[i] + "__" + soloOLD[i]   +"__"  +   ngayhethanOLD[i]  +"__"  +   ngaynhapkhoOLD[i]  , ngaynhapkhoOLD[i]);
					System.out.println("NGYA NHAP KHO NAYYYYYYYYYYY +" +ngaynhapkhoOLD[i]);
					System.out.println("KEY NAYYYYYYYYYYYYYYYYY " +spIds[i] + "__" + soloOLD[i]   +"__"  +   ngayhethanOLD[i]  +"__"  +   ngaynhapkhoOLD[i]);
					sp_ngayhethanOLD.put(spIds[i] + "__" + soloOLD[i]   +"__"  +   ngayhethanOLD[i] +"__"  +   ngaynhapkhoOLD[i]  , ngayhethanOLD[i]);
					sp_soloOLD.put(    spIds[i] + "__" + soloOLD[i]			+"__"  + ngayhethanOLD[i]   +"__"  +   ngaynhapkhoOLD[i]  , soloOLD[i]);
					sp_solo.put(  spIds[i] + "__" + soloOLD[i] 					+"__"  + ngayhethanOLD[i]    +"__"  +   ngaynhapkhoOLD[i]			, solo[i]);
					sp_sl.put(   spIds[i] + "__" + soloOLD[i] 					+"__"  + ngayhethanOLD[i]  		+"__"  +   ngaynhapkhoOLD[i]			, Integer.parseInt(soluong[i].replaceAll(",", "")) );
					sp_hansudung.put(spIds[i] + "__" + soloOLD[i] 			+"__"  + ngayhethanOLD[i]  		+"__"  +   ngaynhapkhoOLD[i]	, ngayhethan[i]);
				
				}
			}
		
			pxkBean.setSp_ngaynhapkhoOLD(sp_ngaynhapkhoOLD);
			pxkBean.setSp_ngayhethanOLD(sp_ngayhethanOLD);
			pxkBean.setSSp_SoloOLD(sp_soloOLD);
			pxkBean.setSSp_Solo(sp_solo);
			pxkBean.setSSp_Soluong(sp_sl);
			pxkBean.setSSp_Ngayhethan(sp_hansudung);
		}
		
 		String action = util.antiSQLInspection(request.getParameter("action"));
		if(action.equals("save"))
		{
//			db = new dbutils();
			if (id == null)
			{
				if (!(pxkBean.CreateCk(request)))
				{	
					pxkBean.createRS();
					session.setAttribute("pxkBean", pxkBean);			
					String nextJSP = request.getContextPath() + "/pages/Distributor/ErpDoiSoLoNppNew.jsp";
					response.sendRedirect(nextJSP);
				}
				else{
					IErpDoisoloNppList obj = new ErpDoisoloNppList();
					obj.setUserId(userId);
					
					obj.init("");
					session.setAttribute("obj", obj);
						
					String nextJSP = request.getContextPath() + "/pages/Distributor/ErpDoiSoLoNpp.jsp";
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
					String nextJSP = request.getContextPath() + "/pages/Distributor/ErpDoiSoLoNppUpdate.jsp";
					response.sendRedirect(nextJSP);
				}
				else{
					IErpDoisoloNppList obj = new ErpDoisoloNppList();
					obj.setUserId(userId);
					
					obj.init("");
					session.setAttribute("obj", obj);
						
					String nextJSP = request.getContextPath() + "/pages/Distributor/ErpDoiSoLoNpp.jsp";
					response.sendRedirect(nextJSP);			    			    									
				}	
			}
		}
		else
		{
			pxkBean.createRS();
			session.setAttribute("pxkBean", pxkBean);
			
			String nextJSP;
			if (id == null)
			{			
				nextJSP = request.getContextPath() + "/pages/Distributor/ErpDoiSoLoNppNew.jsp";
			}
			else
			{
				nextJSP = request.getContextPath() + "/pages/Distributor/ErpDoiSoLoNppUpdate.jsp";   						
			}
			response.sendRedirect(nextJSP);							
		}
		}
	}
	
	private String getDateTime()
	{
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date date = new Date();
        return dateFormat.format(date);	
	}
	
}
