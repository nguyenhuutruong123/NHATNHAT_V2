package geso.dms.center.servlets.nhapkho;

import geso.dms.center.util.Utility;
import geso.dms.center.beans.nhapkho.IErpPhanbodonhang;
import geso.dms.center.beans.nhapkho.imp.ErpPhanbodonhang;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Hashtable;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class ErpHangchophanboSvl extends HttpServlet 
{
	private static final long serialVersionUID = 1L;
	
	PrintWriter out;
    public ErpHangchophanboSvl() 
    {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		HttpSession session = request.getSession();
		String userId = (String) session.getAttribute("userId");  
		String userTen = (String) session.getAttribute("userTen");  	
		String sum = (String) session.getAttribute("sum");
		geso.dms.center.util.Utility cutil = (geso.dms.center.util.Utility) session.getAttribute("util");
		if(!cutil.check(userId, userTen, sum)){
			response.sendRedirect("/OneoOne/index.jsp");
		}
		else
		{
			session.setMaxInactiveInterval(30000);
		
			Utility util = new Utility();
			
	    	String querystring = request.getQueryString();
		    userId = util.getUserId(querystring);
		    
		    if (userId.length()==0)
		    	userId = util.antiSQLInspection(request.getParameter("userId")); 
		    
		    String id = util.getId(querystring);  	
		    IErpPhanbodonhang lsxBean = new ErpPhanbodonhang(id);
		    lsxBean.setUserId(userId); 
		    
		    String nextJSP = "";
		    
    		lsxBean.initChoPhanBo();
    		nextJSP = request.getContextPath() + "/pages/Center/ErpHangChoPhanBo.jsp";
    		
	        session.setAttribute("lsxBean", lsxBean);
	        response.sendRedirect(nextJSP);
		}		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		HttpSession session = request.getSession();
		String userId = (String) session.getAttribute("userId");  
		String userTen = (String) session.getAttribute("userTen"); 
		
		String sum = (String) session.getAttribute("sum");
		geso.dms.center.util.Utility cutil = (geso.dms.center.util.Utility) session.getAttribute("util");
		if(!cutil.check(userId, userTen, sum)){
			response.sendRedirect("/OneoOne/index.jsp");
		}
		else
		{
			request.setCharacterEncoding("UTF-8");
		    response.setCharacterEncoding("UTF-8");
		    response.setContentType("text/html; charset=UTF-8");
			
			session.setMaxInactiveInterval(30000);
			
			this.out = response.getWriter();
			IErpPhanbodonhang lsxBean;
			
			Utility util = new Utility();	
			String id = util.antiSQLInspection(request.getParameter("id"));
		    if(id == null)
		    {  	
		    	lsxBean = new ErpPhanbodonhang("");
		    }
		    else
		    {
		    	lsxBean = new ErpPhanbodonhang(id);
		    }
	
		    lsxBean.setUserId(userId);
		    	    
		    String ghichu = util.antiSQLInspection(request.getParameter("ghichu"));
		    if(ghichu == null)
		    	ghichu = "";
		    lsxBean.setGhichu(ghichu);
			
			String[] spMa = request.getParameterValues("spMa");
			lsxBean.setSpMa(spMa);
			
			String[] spHsd = request.getParameterValues("spHansd");
			lsxBean.setSpHansudung(spHsd);
			
			String[] spTen = request.getParameterValues("spTen");
			lsxBean.setSpTen(spTen);
			
			String[] dvt = request.getParameterValues("donvi");
			lsxBean.setSpDonvi(dvt);
			
			String[] soluong = request.getParameterValues("soluong");
			lsxBean.setSpSoluong(soluong);
			
			if(spMa != null)
			{
				Hashtable<String, String> sp_chitiet = new Hashtable<String, String>();
				for(int i = 0; i < spMa.length; i++)
				{
					String[] soluongCT = request.getParameterValues("dong" + i + "_spSOLUONG");
					String[] solo = request.getParameterValues("dong" + i + "_spSOLO");		
					
					for(int j = 0; j < solo.length; j++)
					{
						if( solo[j].trim().length() > 0  )
						{
							if( soluongCT[j].trim().length() <= 0 )
								soluongCT[j] = "0";
							
							String ct = sp_chitiet.get(spMa[i].trim());
							if(ct == null)
								ct = "";
							
							if(ct.trim().length() <= 0)
							{
								sp_chitiet.put(spMa[i], soluongCT[j].trim() + "__" + solo[j].trim() + "___");
							}
							else
							{
								ct += soluongCT[j].trim() + "__" + solo[j].trim() + "___";
								sp_chitiet.put(spMa[i], ct);
							}
						}
					}
				}
				lsxBean.setSp_Chitiet(sp_chitiet);
			}
			
		    String action = request.getParameter("action");
			if(action.equals("save"))
			{	
				lsxBean.updateCHOPB();
			}
			
			//lsxBean.initChoPhanBo();
			session.setAttribute("lsxBean", lsxBean);
			String nextJSP = request.getContextPath() + "/pages/Center/ErpHangChoPhanBo.jsp";
			
			response.sendRedirect(nextJSP);
		}
	}
	
	
}
