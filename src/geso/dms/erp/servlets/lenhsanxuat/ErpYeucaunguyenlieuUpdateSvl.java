package geso.dms.erp.servlets.lenhsanxuat;

import geso.dms.distributor.util.Utility;
import geso.dms.erp.beans.lenhsanxuat.IErpYeucaunguyenlieu;
import geso.dms.erp.beans.lenhsanxuat.IErpYeucaunguyenlieuList;
import geso.dms.erp.beans.lenhsanxuat.IYeucau;
import geso.dms.erp.beans.lenhsanxuat.imp.ErpYeucaunguyenlieu;
import geso.dms.erp.beans.lenhsanxuat.imp.ErpYeucaunguyenlieuList;
import geso.dms.erp.beans.lenhsanxuat.imp.Yeucau;
import geso.dms.erp.beans.phieuxuatkho.ISpDetail;
import geso.dms.erp.beans.phieuxuatkho.imp.SpDetail;
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

public class ErpYeucaunguyenlieuUpdateSvl extends HttpServlet 
{
	private static final long serialVersionUID = 1L;
       
	PrintWriter out;
    public ErpYeucaunguyenlieuUpdateSvl() 
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
			response.sendRedirect("/SalesUp/index.jsp");
		}
		else
		{
			session.setMaxInactiveInterval(30000);
		
			this.out = response.getWriter();
			Utility util = new Utility();
			
	    	String querystring = request.getQueryString();
		    userId = util.getUserId(querystring);
		    
		    if (userId.length()==0)
		    	userId = util.antiSQLInspection(request.getParameter("userId")); 
		    
		    String id = util.getId(querystring);  	
		    IErpYeucaunguyenlieu lsxBean = new ErpYeucaunguyenlieu(id);
		    lsxBean.setUserId(userId); 
		    
		    String task = request.getParameter("task");
			if(task == null)
				task = "";
			if(task.equals("chuyenNL"))
				lsxBean.setIschuyenNL("1");
		    
			lsxBean.init();
			
	        String nextJSP = "";
	        if(task.equals("chuyenNL"))
	        {
		        if(request.getQueryString().indexOf("display") >= 0 ) 
		        {
		        	nextJSP = "/SalesUp/pages/Erp/ErpChuyenNguyenLieuDisplay.jsp";
		        }
		        else
		        {
		        	nextJSP = "/SalesUp/pages/Erp/ErpChuyenNguyenLieuUpdate.jsp";
		        }
	        }
	        else
	        {
	        	if(request.getQueryString().indexOf("display") >= 0 ) 
		        {
		        	nextJSP = "/SalesUp/pages/Erp/ErpYeuCauNguyenLieuDisplay.jsp";
		        }
		        else
		        {
					nextJSP = "/SalesUp/pages/Erp/ErpYeuCauNguyenLieuUpdate.jsp";
		        }
	        }
	        
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
			response.sendRedirect("/SalesUp/index.jsp");
		}
		else
		{
			request.setCharacterEncoding("UTF-8");
		    response.setCharacterEncoding("UTF-8");
		    response.setContentType("text/html; charset=UTF-8");
			
			session.setMaxInactiveInterval(30000);
			
			this.out = response.getWriter();
			IErpYeucaunguyenlieu lsxBean;
			
			Utility util = new Utility();	
			String id = util.antiSQLInspection(request.getParameter("id"));
		    if(id == null)
		    {  	
		    	lsxBean = new ErpYeucaunguyenlieu("");
		    }
		    else
		    {
		    	lsxBean = new ErpYeucaunguyenlieu(id);
		    }
	
		    lsxBean.setUserId(userId);
		    
		    String task = request.getParameter("task");
		    
		    System.out.println("Task nek ku :  "+task);
			if(task == null)
				task = "";
			if(task.equals("chuyenNL"))
				lsxBean.setIschuyenNL("1");
			
		    
		    String ngayyeucau = util.antiSQLInspection(request.getParameter("ngayyeucau"));
		    if(ngayyeucau == null || ngayyeucau.trim().length() <= 0)
		    	ngayyeucau = getDateTime();
		    lsxBean.setNgayyeucau(ngayyeucau);
		    
		    
		    String noidungxuat = util.antiSQLInspection(request.getParameter("noidungxuat"));
		    if(noidungxuat == null  )
		    	noidungxuat = "";
		    lsxBean.setNoidungxuat(noidungxuat);
		    
		    System.out.println(" NOi dung xuất :"+noidungxuat);
		    
		    String lydo = util.antiSQLInspection(request.getParameter("lydo"));
		    if(lydo == null)
		    	lydo = "";
		    lsxBean.setLydoyeucau(lydo);
		    
		    String nhamayId = util.antiSQLInspection(request.getParameter("nhamayId"));
			if (nhamayId == null)
				nhamayId = "";				
			lsxBean.setKhoXuatId(nhamayId);
			
		    String khoxuatId = util.antiSQLInspection(request.getParameter("khoxuatId"));
			if (khoxuatId == null)
				khoxuatId = "";				
			lsxBean.setKhoXuatId(khoxuatId);
	    	
			String khonhapId = util.antiSQLInspection(request.getParameter("khonhapId"));
			if (khonhapId == null)
				khonhapId = "";				
			lsxBean.setKhoNhapId(khonhapId);
			
			String trangthai = util.antiSQLInspection(request.getParameter("trangthai"));
			if (trangthai == null)
				trangthai = "";				
			lsxBean.setTrangthai(trangthai);
	    	
	    	String[] lsxIds = request.getParameterValues("lsxIds");
	    	if(lsxIds != null)
	    	{
	    		String lsx = "";
				for(int i = 0; i < lsxIds.length; i++)
				{
					lsx += lsxIds[i] + ",";
				}
				
				if(lsx.trim().length() > 0)
				{
					lsx = lsx.substring(0, lsx.length() - 1);
				}
	    		
				lsxBean.setLsxIds(lsx);
	    	}
	    	
			String[] manguyenlieu = request.getParameterValues("manguyenlieu");
			String[] tennguyenlieu = request.getParameterValues("tennguyenlieu");
			String[] soluongyeucau = request.getParameterValues("soluongyeucau");
			String[] soluongdanhan = request.getParameterValues("soluongdanhan");
			String[] soluongnhan = request.getParameterValues("soluongnhan"); 
			
			List<IYeucau> spList = new ArrayList<IYeucau>();
			if(tennguyenlieu != null)
			{	
				IYeucau yeucau = null;
				for(int m = 0; m < tennguyenlieu.length; m++)
				{	
					if(tennguyenlieu[m] != "")
					{	
						yeucau = new Yeucau();
						yeucau.setMa(manguyenlieu[m]);
						yeucau.setTen(tennguyenlieu[m]);
						yeucau.setSoluongYC(soluongyeucau[m]);
						
						if(soluongdanhan != null && soluongnhan != null )
						{
							yeucau.setSoluongDaNhan(soluongdanhan[m]);
							yeucau.setSoluongNhan(soluongnhan[m]);
							
							String[] solo = request.getParameterValues(manguyenlieu[m] + ".solo");
							String[] slg = request.getParameterValues(manguyenlieu[m] + ".soluong");
							String[] khu = request.getParameterValues(manguyenlieu[m] + ".khu");
							String[] vitri = request.getParameterValues(manguyenlieu[m] + ".vitri");
							String[] vitriId = request.getParameterValues(manguyenlieu[m] + ".vitriId");
							
							if(solo != null)
							{
								List<ISpDetail> spConList = new ArrayList<ISpDetail>();
								ISpDetail spCon = null;
								int n = 0;
								if(slg != null)
								{
									while(n < slg.length)
									{
										if(slg[n] != "")
										{
											if(vitri != null)
											{
												spCon = new SpDetail(manguyenlieu[m], solo[n], slg[n],"100000","100000", "100000");
											}
											else
											{
												spCon = new SpDetail(manguyenlieu[m], solo[n], slg[n],"100000","100000", "100000");
											}
											
											spConList.add(spCon);
											
										}
										n++;
									}
								}
								yeucau.setSpDetailList(spConList);	
							}
						}
						
						spList.add(yeucau);
					}				
				}
				
				lsxBean.setSpList(spList);
			}	
			
			
		    String action = request.getParameter("action");
		    
			if(action.equals("save"))
			{	
				if(id == null)
				{
					if(!lsxBean.createYcnl())
					{
						lsxBean.setNoidungxuat(noidungxuat);
						lsxBean.createRs();
						
	    		    	session.setAttribute("lsxBean", lsxBean);
	    				String nextJSP = "/SalesUp/pages/Erp/ErpYeuCauNguyenLieuNew.jsp";
	    				response.sendRedirect(nextJSP);
					}
					else
					{
						IErpYeucaunguyenlieuList obj = new ErpYeucaunguyenlieuList();
						obj.setUserId(userId);
						obj.setNoidungxuat(noidungxuat);
						obj.init("");  
				    	session.setAttribute("obj", obj);  	
			    		session.setAttribute("userId", userId);
				
			    		response.sendRedirect("/SalesUp/pages/Erp/ErpYeuCauNguyenLieu.jsp");
					}
				}
				else
				{
					if(lsxBean.getIschuyenNL().equals("0"))
					{
						if(!lsxBean.updateYcnl())
						{
							lsxBean.setNoidungxuat(noidungxuat);
							lsxBean.createRs();
							session.setAttribute("lsxBean", lsxBean);
		    				String nextJSP = "/SalesUp/pages/Erp/ErpYeuCauNguyenLieuUpdate.jsp";
		    				response.sendRedirect(nextJSP);
						}
						else
						{
							IErpYeucaunguyenlieuList obj = new ErpYeucaunguyenlieuList();
						    obj.setUserId(userId);
						    obj.setNoidungxuat(noidungxuat);
						    obj.init("");
							session.setAttribute("obj", obj);							
							String nextJSP = "/SalesUp/pages/Erp/ErpYeuCauNguyenLieu.jsp";
							response.sendRedirect(nextJSP);
						}
					}
					else
					{
						if(!lsxBean.chuyenNL())
						{
							lsxBean.createRs();
							session.setAttribute("lsxBean", lsxBean);
		    				String nextJSP = "/SalesUp/pages/Erp/ErpChuyenNguyenLieuUpdate.jsp";
		    				response.sendRedirect(nextJSP);
						}
						else
						{
							IErpYeucaunguyenlieuList obj = new ErpYeucaunguyenlieuList();
							obj.setIschuyenNL("1");
						    obj.setUserId(userId);
						    obj.setNoidungxuat(noidungxuat);
						    obj.init("");
							session.setAttribute("obj", obj);	
							
							String nextJSP = "/SalesUp/pages/Erp/ErpChuyenNguyenLieu.jsp";
							response.sendRedirect(nextJSP);
						}
					}
				}
			}
			else
			{
				if(action.equals("chotForm"))
				{
					if(!lsxBean.chotYcnl())
					{
						lsxBean.setNoidungxuat(noidungxuat);
						lsxBean.createRs();
						session.setAttribute("lsxBean", lsxBean);
						
	    				String nextJSP = "/SalesUp/pages/Erp/ErpYeuCauNguyenLieuDisplay.jsp";
	    				response.sendRedirect(nextJSP);
					}
					else
					{
						IErpYeucaunguyenlieuList obj = new ErpYeucaunguyenlieuList();
					    obj.setUserId(userId);
					    obj.setNoidungxuat(noidungxuat);
					    obj.init("");
						session.setAttribute("obj", obj);							
						String nextJSP = "/SalesUp/pages/Erp/ErpYeuCauNguyenLieu.jsp";
						response.sendRedirect(nextJSP);
					}
				}
				else
				{
					lsxBean.createRs();
					
					session.setAttribute("lsxBean", lsxBean);
					
					String nextJSP = "";
					
					if(lsxBean.getIschuyenNL().equals("0"))
					{
						nextJSP = "/SalesUp/pages/Erp/ErpYeuCauNguyenLieuNew.jsp";
						if(id != null)
							nextJSP = "/SalesUp/pages/Erp/ErpYeuCauNguyenLieuUpdate.jsp";
					}
					else
					{
						nextJSP = "/SalesUp/pages/Erp/ErpChuyenNguyenLieuUpdate.jsp";
					}
					
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
