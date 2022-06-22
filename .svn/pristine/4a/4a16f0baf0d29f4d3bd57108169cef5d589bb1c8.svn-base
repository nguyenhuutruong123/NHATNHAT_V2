package geso.dms.distributor.servlets.nhaphangchinhanh;

import geso.dms.distributor.beans.nhaphangchinhanh.INhaphang;
import geso.dms.distributor.beans.nhaphangchinhanh.INhaphangList;
import geso.dms.distributor.beans.nhaphangchinhanh.imp.Lochitiet;
import geso.dms.distributor.beans.nhaphangchinhanh.imp.Nhaphang;
import geso.dms.distributor.beans.nhaphangchinhanh.imp.NhaphangList;
import geso.dms.distributor.beans.nhaphangchinhanh.imp.SpNhaphang;
import geso.dms.distributor.util.Utility;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class NhaphangchinhanhUpdateSvl extends HttpServlet 
{
	private static final long serialVersionUID = 1L;
	
	PrintWriter out;
    public NhaphangchinhanhUpdateSvl() 
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
    			nextJSP = request.getContextPath() + "/pages/Distributor/NhapHangCNUpdate.jsp";	
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
			 
			
			String[] spId = util.antiSQLInspection_Array( request.getParameterValues("spId"));
		 
			String[] spTen = util.antiSQLInspection_Array(request.getParameterValues("spTen"));
			String[] spMa = util.antiSQLInspection_Array(request.getParameterValues("spMa"));
			String[] spDonvi = util.antiSQLInspection_Array(request.getParameterValues("donvi"));
			  
			String[] soluongnhap = util.antiSQLInspection_Array(request.getParameterValues("soluongnhap"));
		 
			String[] spLoai = util.antiSQLInspection_Array(request.getParameterValues("spLoai"));
			String[] dongia = util.antiSQLInspection_Array(request.getParameterValues("dongia"));
			 
			String[] spScheme = util.antiSQLInspection_Array(request.getParameterValues("scheme"));
		   
			if(spId!=null){
				 List<SpNhaphang> list=new ArrayList<SpNhaphang>();
				 for(int i=0;i<spId.length;i++){
					 SpNhaphang sp=new SpNhaphang();
					 	sp.setSpPK_SEQ(spPK_SEQ[i]);
						sp.setSpMa(spMa[i]);
						sp.setSpId(spId[i]);
						sp.setSpTen(spTen[i]);
						sp.setSpDonvi(spDonvi[i]);
						sp.setSpLoai(spLoai[i]);
						sp.setSpSCheme(spScheme[i]);
						sp.setSpDongia(geso.dms.center.util.Utility.parseDouble(dongia[i]));
						
						sp.setSoluongnhap(geso.dms.center.util.Utility.parseDouble(soluongnhap[i]));
						
					 
						List<Lochitiet> listlo =new ArrayList<Lochitiet>();
						String[] solo = util.antiSQLInspection_Array(request.getParameterValues(i+"_solo"));
						String[] ngayhethan = util.antiSQLInspection_Array(request.getParameterValues(i+"_ngayhethan"));
						String[] soluong = util.antiSQLInspection_Array(request.getParameterValues(i+"_soluong"));
						
						if(solo!=null){
							for(int j=0;j<solo.length;j++){
								Lochitiet lo= new Lochitiet();
								lo.setSolo(solo[j]);
								lo.setNgayhethan(ngayhethan[j]);
								lo.setSoluong(geso.dms.center.util.Utility.parseDouble(soluong[j]));
								listlo.add(lo);
							}
						}
						sp.setList(listlo);
						
						list.add(sp);
						
				 }
				 
				 lsxBean.setListSp(list);
				 
			}
			
		  String action = request.getParameter("action");
		    
			if(action.equals("save"))
			{	
				 
				if(!lsxBean.update())
				{
					lsxBean.createRs();
					session.setAttribute("lsxBean", lsxBean);
    				String nextJSP = request.getContextPath() + "/pages/Distributor/NhapHangCNUpdate.jsp";
    				response.sendRedirect(nextJSP);
				}
				else
				{
					INhaphangList obj = new NhaphangList();
					
				    obj.setUserId(userId);
				    obj.createNhaphanglist("");;
					session.setAttribute("obj", obj);							
					String nextJSP = request.getContextPath() + "/pages/Distributor/NhapHangCN.jsp";
					response.sendRedirect(nextJSP);
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
	    				String nextJSP = request.getContextPath() + "/pages/Distributor/NhapHangCNUpdate.jsp";
	    				response.sendRedirect(nextJSP);
					}
					else
					{
						INhaphangList obj = new NhaphangList();
						
					    obj.setUserId(userId);
					    obj.createNhaphanglist("");
						session.setAttribute("obj", obj);							
						String nextJSP = request.getContextPath() + "/pages/Distributor/NhapHangCN.jsp";
						response.sendRedirect(nextJSP);
					}
				}
				else
				{
					lsxBean.createRs();
	
					session.setAttribute("lsxBean", lsxBean);
					
					String nextJSP = request.getContextPath() + "/pages/Distributor/NhapHangNew.jsp";
					if(id != null)
						nextJSP = request.getContextPath() + "/pages/Distributor/NhapHangCNUpdate.jsp";
					
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
