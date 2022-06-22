package geso.dms.distributor.servlets.nhaphang;

import geso.dms.distributor.beans.nhaphang.INhaphang;
import geso.dms.distributor.beans.nhaphang.INhaphangList;
import geso.dms.distributor.beans.nhaphang.imp.Nhaphang;
import geso.dms.distributor.beans.nhaphang.imp.NhaphangList;
import geso.dms.distributor.util.Utility;
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

public class NhaphangUpdateSvl extends HttpServlet 
{
	private static final long serialVersionUID = 1L;
	
	PrintWriter out;
    public NhaphangUpdateSvl() 
    {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		HttpSession session = request.getSession();
		if(!Utility.val_doget(session, request, response))
		{
			session.setAttribute("flag",null);
			response.sendRedirect(request.getContextPath() + "/redirect.jsp");
			return;
		}
		else
		{
			session.setAttribute("flag",null);
		}
		String userId = (String) session.getAttribute("userId");  
		String userTen = (String) session.getAttribute("userTen");  	
		String sum = (String) session.getAttribute("sum");
		geso.dms.center.util.Utility cutil = (geso.dms.center.util.Utility) session.getAttribute("util");
		if(!cutil.check(userId, userTen, sum)){
			response.sendRedirect(request.getContextPath() + "/redirect.jsp");
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
		    INhaphang lsxBean = new Nhaphang(id);
		    lsxBean.setUserId(userId); 
		    
		    String nextJSP = "";
		    
    		lsxBean.init();
    		
    		if(!querystring.contains("display"))
    		{
    			nextJSP = request.getContextPath() + "/pages/Distributor/NhapHangUpdate.jsp";	
    		}
    		else
    		{
    			nextJSP = request.getContextPath() + "/pages/Distributor/NhapHangDisplay.jsp";
    		}
    		
	        session.setAttribute("lsxBean", lsxBean);
	        response.sendRedirect(nextJSP);
		}		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		HttpSession session = request.getSession();
		
		geso.dms.center.util.Csrf csdr=new geso.dms.center.util.Csrf(request,response,false,true ,false);
		if(!csdr.__validate_post())
		{
			response.sendRedirect(request.getContextPath() + "/redirect.jsp");
			return;
		}
		
		
		String userId = (String) session.getAttribute("userId");  
		String userTen = (String) session.getAttribute("userTen"); 
		
		String sum = (String) session.getAttribute("sum");
		geso.dms.center.util.Utility cutil = (geso.dms.center.util.Utility) session.getAttribute("util");
		if(!cutil.check(userId, userTen, sum)){
			response.sendRedirect(request.getContextPath() + "/redirect.jsp");
		}
		else
		{
			request.setCharacterEncoding("UTF-8");
		    response.setCharacterEncoding("UTF-8");
		    response.setContentType("text/html; charset=UTF-8");
			
			session.setMaxInactiveInterval(30000);
			
			this.out = response.getWriter();
			INhaphang lsxBean;
			
			Utility util = new Utility();	
			String id = util.antiSQLInspection(request.getParameter("id"));
		    if(id == null)
		    {  	
		    	lsxBean = new Nhaphang("");
		    }
		    else
		    {
		    	lsxBean = new Nhaphang(id);
		    }
	
		    lsxBean.setUserId(userId);
		    
		    String ngayyeucau = util.antiSQLInspection(request.getParameter("ngaychuyen"));
		    if(ngayyeucau == null || ngayyeucau.trim().length() <= 0)
		    	ngayyeucau = getDateTime();
		    lsxBean.setNgayyeucau(ngayyeucau);
		    
		    String ngaynhan = util.antiSQLInspection(request.getParameter("ngaynhan"));
		    if(ngaynhan == null )
		    	ngaynhan = "";
		    lsxBean.setNgaynhap(ngaynhan);
		    
		    String sochungtu = util.antiSQLInspection(request.getParameter("sochungtu"));
		    if(sochungtu == null )
		    	sochungtu = "";
		    lsxBean.setSOchungtu(sochungtu);
		    
		    String ghichu = util.antiSQLInspection(request.getParameter("ghichu"));
		    if(ghichu == null)
		    	ghichu = "";
		    lsxBean.setGhichu(ghichu);
		    
		    String khonhanID = util.antiSQLInspection(request.getParameter("khonhanID"));
		    if(khonhanID == null || khonhanID.trim().length() <= 0 )
		    	khonhanID = "100000";
		    lsxBean.setKhonhanId(khonhanID);
		    System.out.println("kho nhan id"+khonhanID);
		    String nppId = util.antiSQLInspection(request.getParameter("nppId"));
			if (nppId == null)
				nppId = "";				
			lsxBean.setNppId(nppId);
			
			String[] spPK_SEQ = util.antiSQLInspection_Array( request.getParameterValues("spPK_SEQ"));
			lsxBean.setPK_SEQ(spPK_SEQ);
			
			String[] spId = util.antiSQLInspection_Array( request.getParameterValues("spId"));
			lsxBean.setSpId(spId);
			
			String[] spTen = util.antiSQLInspection_Array(request.getParameterValues("spTen"));
			lsxBean.setSpTen(spTen);
			
			String[] spSolo = util.antiSQLInspection_Array(request.getParameterValues("solo"));
			lsxBean.setSpSolo(spSolo);
			
			String[] soluongXUAT = util.antiSQLInspection_Array(request.getParameterValues("soluongXUAT"));
			lsxBean.setSpXuat(soluongXUAT);
			
			String[] soluong = util.antiSQLInspection_Array(request.getParameterValues("soluong"));
			lsxBean.setSpSoluong(soluong);
			
			String[] spLoai = util.antiSQLInspection_Array(request.getParameterValues("spLoai"));
			lsxBean.setSpLoai(spLoai);
			
			String[] spScheme = util.antiSQLInspection_Array(request.getParameterValues("scheme"));
			lsxBean.setSpScheme(spScheme);
			
			
			String[] spNgayHetHan = util.antiSQLInspection_Array(request.getParameterValues("spNgayHetHan"));
			lsxBean.setSpNgayHetHan(spNgayHetHan);
			
		  String action = request.getParameter("action");
		    
			if(action.equals("save"))
			{	
				if(id == null)
				{
					if(!lsxBean.create())
					{
						lsxBean.createRs();
	    		    	session.setAttribute("lsxBean", lsxBean);
	    				String nextJSP = request.getContextPath() + "/pages/Distributor/NhapHangNew.jsp";
	    				
	    				response.sendRedirect(nextJSP);
					}
					else
					{
						INhaphangList obj = new NhaphangList();
						
						obj.setUserId(userId);
						obj.createNhaphanglist("");;  
				    	session.setAttribute("obj", obj);  	
			    		session.setAttribute("userId", userId);
			    		
			    		String nextJSP = request.getContextPath() + "/pages/Distributor/NhapHang.jsp";
			    		response.sendRedirect(nextJSP);
					}
				}
				else
				{
					if(!lsxBean.update())
					{
						lsxBean.createRs();
						session.setAttribute("lsxBean", lsxBean);
	    				String nextJSP = request.getContextPath() + "/pages/Distributor/NhapHangUpdate.jsp";
	    				response.sendRedirect(nextJSP);
					}
					else
					{
						INhaphangList obj = new NhaphangList();
						
					    obj.setUserId(userId);
					    obj.createNhaphanglist("");;
						session.setAttribute("obj", obj);							
						String nextJSP = request.getContextPath() + "/pages/Distributor/NhapHang.jsp";
						response.sendRedirect(nextJSP);
					}
				}
			}
			else
			{
				if(action.equals("chot"))
				{
					if(!lsxBean.chot())
					{
						lsxBean.createRs();
						session.setAttribute("lsxBean", lsxBean);
	    				String nextJSP = request.getContextPath() + "/pages/Distributor/NhapHangUpdate.jsp";
	    				response.sendRedirect(nextJSP);
					}
					else
					{
						INhaphangList obj = new NhaphangList();
						
					    obj.setUserId(userId);
					    obj.createNhaphanglist("");
						session.setAttribute("obj", obj);							
						String nextJSP = request.getContextPath() + "/pages/Distributor/NhapHang.jsp";
						response.sendRedirect(nextJSP);
					}
				}
				else
				{
					lsxBean.createRs();
	
					session.setAttribute("lsxBean", lsxBean);
					
					String nextJSP = request.getContextPath() + "/pages/Distributor/NhapHangNew.jsp";
					if(id != null)
						nextJSP = request.getContextPath() + "/pages/Distributor/NhapHangUpdate.jsp";
					
					response.sendRedirect(nextJSP);
				}
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
