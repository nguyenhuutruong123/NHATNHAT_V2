package geso.dms.distributor.servlets.doihang;

import geso.dms.center.util.Utility;
import geso.dms.distributor.beans.doihang.IErpDenghidoihangNpp;
import geso.dms.distributor.beans.doihang.IErpDenghidoihangNppList;
import geso.dms.distributor.beans.doihang.imp.ErpDenghidoihangNpp;
import geso.dms.distributor.beans.doihang.imp.ErpDenghidoihangNppList;

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

public class ErpDenghidoihangNppUpdateSvl extends HttpServlet 
{
	private static final long serialVersionUID = 1L;
	
	PrintWriter out;
    public ErpDenghidoihangNppUpdateSvl() 
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
			response.sendRedirect("/SalesUpERP/index.jsp");
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
		    IErpDenghidoihangNpp lsxBean = new ErpDenghidoihangNpp(id);
		    lsxBean.setUserId(userId); 
		    
		    String nextJSP = "";
		    
    		lsxBean.init();
    		
    		if(!querystring.contains("display"))
    		{
    			nextJSP = request.getContextPath() + "/pages/Distributor/ErpDeNghiDoiHangNppUpdate.jsp";
    		}
    		else
    		{
    			nextJSP = request.getContextPath() + "/pages/Distributor/ErpDeNghiDoiHangNppDisplay.jsp";
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
			response.sendRedirect("/SalesUpERP/index.jsp");
		}
		else
		{
			request.setCharacterEncoding("UTF-8");
		    response.setCharacterEncoding("UTF-8");
		    response.setContentType("text/html; charset=UTF-8");
			
			session.setMaxInactiveInterval(30000);
			
			this.out = response.getWriter();
			IErpDenghidoihangNpp lsxBean;
			
			Utility util = new Utility();	
			String id = util.antiSQLInspection(request.getParameter("id"));
		    if(id == null)
		    {  	
		    	lsxBean = new ErpDenghidoihangNpp("");
		    }
		    else
		    {
		    	lsxBean = new ErpDenghidoihangNpp(id);
		    }
	
		    lsxBean.setUserId(userId);
		    
		    String ngayyeucau = util.antiSQLInspection(request.getParameter("ngaychuyen"));
		    if(ngayyeucau == null || ngayyeucau.trim().length() <= 0)
		    	ngayyeucau = getDateTime();
		    lsxBean.setNgayyeucau(ngayyeucau);
		    
		    String ngaydenghi = util.antiSQLInspection(request.getParameter("ngaydenghi"));
		    if(ngaydenghi == null || ngaydenghi.trim().length() <= 0)
		    	ngaydenghi = "";
		    lsxBean.setNgaydenghi(ngaydenghi);
		    	    
		    String ghichu = util.antiSQLInspection(request.getParameter("ghichu"));
		    if(ghichu == null)
		    	ghichu = "";
		    lsxBean.setGhichu(ghichu);
		    
			String khonhapId = util.antiSQLInspection(request.getParameter("khonhapId"));
			if (khonhapId == null)
				khonhapId = "";				
			lsxBean.setKhoNhapId(khonhapId);
			
			String dvkdId = util.antiSQLInspection(request.getParameter("dvkdId"));
			if (dvkdId == null)
				dvkdId = "";				
			lsxBean.setDvkdId(dvkdId);
			
			String kbhId = util.antiSQLInspection(request.getParameter("kbhId"));
			if (kbhId == null)
				kbhId = "";				
			lsxBean.setKbhId(kbhId);
			
			String nppId = util.antiSQLInspection(request.getParameter("nppId"));
			if (nppId == null)
				nppId = "";				
			lsxBean.setNppId(nppId);
			
			String chietkhau = util.antiSQLInspection(request.getParameter("ptChietkhau"));
			if (chietkhau == null)
				chietkhau = "";				
			lsxBean.setChietkhau(chietkhau);
			
			String vat = util.antiSQLInspection(request.getParameter("ptVat"));
			if (vat == null)
				vat = "";				
			lsxBean.setVat(vat);
			
			String loaidonhang = util.antiSQLInspection(request.getParameter("loaidonhang"));
			if (loaidonhang == null)
				loaidonhang = "";				
			lsxBean.setLoaidonhang(loaidonhang); 
			
			String[] schemeIds = request.getParameterValues("schemeIds");
			if (schemeIds != null)
			{
				String _scheme = "";
				for(int i = 0; i < schemeIds.length; i++)
				{
					_scheme += schemeIds[i] + ",";
				}
				
				if(_scheme.trim().length() > 0)
				{
					_scheme = _scheme.substring(0, _scheme.length() - 1);
					lsxBean.setSchemeId(_scheme);
				}
			}
			
			String[] spId = request.getParameterValues("spID");
			String[] soluong = request.getParameterValues("spSOLUONG");
			String[] solo = request.getParameterValues("spSOLO");
			
			if(spId != null)
			{
				Hashtable<String, String> sp_soluong = new Hashtable<String, String>();
				Hashtable<String, String> sp_solo = new Hashtable<String, String>();
				
				for(int i = 0; i < spId.length; i++ )
				{
					if(soluong[i].trim().length() > 0 && solo[i].trim().length() > 0 )
					{
						sp_soluong.put(spId[i], soluong[i].replaceAll(",", "") );
						sp_solo.put(spId[i], solo[i]);
					}
				}
				
				lsxBean.setSanpham_soluong(sp_soluong);
				lsxBean.setSanpham_solo(sp_solo);
			}
			
		    String action = request.getParameter("action");
			if(action.equals("save"))
			{	
				if(id == null)
				{
					boolean kq = lsxBean.createNK(request);
					
					if(!kq)
					{
						lsxBean.createRs();
	    		    	session.setAttribute("lsxBean", lsxBean);
	    				String nextJSP = request.getContextPath() + "/pages/Distributor/ErpDeNghiDoiHangNppNew.jsp";
	    				response.sendRedirect(nextJSP);
					}
					else
					{
						IErpDenghidoihangNppList obj = new ErpDenghidoihangNppList();
						obj.setLoaidonhang(loaidonhang);
						
						obj.setUserId(userId);
						obj.init("");  
				    	session.setAttribute("obj", obj);  	
			    		session.setAttribute("userId", userId);
			    		
			    		String nextJSP = request.getContextPath() + "/pages/Distributor/ErpDeNghiDoiHangNpp.jsp";
			    		response.sendRedirect(nextJSP);
					}
				}
				else
				{
					boolean kq = lsxBean.updateNK(request);
					
					if(!kq)
					{
						lsxBean.createRs();
						session.setAttribute("lsxBean", lsxBean);
	    				String nextJSP = request.getContextPath() + "/pages/Distributor/ErpDeNghiDoiHangNppUpdate.jsp";
	    				response.sendRedirect(nextJSP);
					}
					else
					{
						IErpDenghidoihangNppList obj = new ErpDenghidoihangNppList();
						obj.setLoaidonhang(loaidonhang);
						
					    obj.setUserId(userId);
					    obj.init("");
						session.setAttribute("obj", obj);							
						String nextJSP = request.getContextPath() + "/pages/Distributor/ErpDeNghiDoiHangNpp.jsp";
						response.sendRedirect(nextJSP);
					}
				}
			}
			else
			{
				lsxBean.createRs();
				
				session.setAttribute("dvkdId", lsxBean.getDvkdId());
				session.setAttribute("kbhId", lsxBean.getKbhId());
				session.setAttribute("nppId", lsxBean.getNppId());
				
				session.setAttribute("lsxBean", lsxBean);
				
				String nextJSP = "";
				
				nextJSP = request.getContextPath() + "/pages/Distributor/ErpDeNghiDoiHangNppNew.jsp";
				if(id != null)
				{
					nextJSP = request.getContextPath() + "/pages/Distributor/ErpDeNghiDoiHangNppUpdate.jsp";
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
