package geso.dms.distributor.servlets.thutienNPP;

import geso.dms.distributor.util.Utility;
import geso.dms.distributor.beans.thutienNPP.*;
import geso.dms.distributor.beans.thutienNPP.imp.*;
import geso.dms.distributor.db.sql.dbutils;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Hashtable;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class ErpThutienKhNPPUpdateSvl extends HttpServlet 
{
	private static final long serialVersionUID = 1L;
    
	PrintWriter out;
	dbutils db;
	
    public ErpThutienKhNPPUpdateSvl() {
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
		
			this.out = response.getWriter();
			Utility util = new Utility();
			
	    	String querystring = request.getQueryString();
		    userId = util.getUserId(querystring);
		    String ctyId = (String)session.getAttribute("congtyId");
		    out.println(userId);
		    
		    if (userId.length()==0)
		    	userId = util.antiSQLInspection(request.getParameter("userId"));
		    
		    String id = util.getId(querystring);  	
			IErpThutienKhNPP tthdBean = new ErpThutienKhNPP(id);
	        tthdBean.setCtyId(ctyId);
			tthdBean.setUserId(userId);
	        
	        String nextJSP;
	        
	        if(request.getQueryString().indexOf("display") >= 0 ) 
	        {
	        	//tthdBean.createRsLoc();
	        	tthdBean.initDisplay();
	        	nextJSP = request.getContextPath() + "/pages/Distributor/ErpThuTienKhNPPDisplay.jsp";
	        }
	        else
	        {
	        	//tthdBean.createRsLoc();
	        	tthdBean.init();
	        	nextJSP = request.getContextPath() + "/pages/Distributor/ErpThuTienKhNPPUpdate.jsp";
	        }
	 
	        session.setAttribute("tthdBean", tthdBean);
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
			response.sendRedirect(request.getContextPath() + "/redirect.jsp");
		}
		else
		{
			request.setCharacterEncoding("UTF-8");
		    response.setCharacterEncoding("UTF-8");
		    response.setContentType("text/html; charset=UTF-8");
			
			session.setMaxInactiveInterval(30000);
			
			this.out = response.getWriter();
			String ctyId = (String)session.getAttribute("congtyId");
			IErpThutienKhNPP tthdBean;
			
			Utility util = new Utility();
			String id = util.antiSQLInspection(request.getParameter("id"));
			
	       if(id == null)
		    {  	
		    	tthdBean = new ErpThutienKhNPP("");
		    }
		    else
		    {
		    	tthdBean = new ErpThutienKhNPP(id);
		    }

			tthdBean.setCtyId(ctyId);
		    tthdBean.setUserId(userId);
			
		    String tungay = util.antiSQLInspection(request.getParameter("tungay"));
			if (tungay == null)
				tungay = ""	;		
	    	tthdBean.setTungay(tungay);
	    	
	    	  String denngay = util.antiSQLInspection(request.getParameter("denngay"));
				if (denngay == null)
					denngay = ""	;		
		    	tthdBean.setDenngay(denngay);
	    	
		    
		    String ngaychungtu = util.antiSQLInspection(request.getParameter("ngaychungtu"));
			if (ngaychungtu == null || ngaychungtu == "")
				ngaychungtu = this.getDateTime();				
	    	tthdBean.setNgaychungtu(ngaychungtu);
	    	
	    	String ngayghiso = util.antiSQLInspection(request.getParameter("ngayghiso"));
			if (ngayghiso == null || ngayghiso == "")
				ngayghiso = this.getDateTime();				
	    	tthdBean.setNgayghiso(ngayghiso);
	    		    
			String[] nppIds = request.getParameterValues("nppSearchId");
			
			if (nppIds != null)
			{
				String _scheme = "";
				for(int i = 0; i < nppIds.length; i++)
				{
					_scheme += nppIds[i] + ",";
				}
				
				if(_scheme.trim().length() > 0)
				{
					_scheme = _scheme.substring(0, _scheme.length() - 1);
					tthdBean.setNppIds(_scheme);
				}
			}
			
	    	String htttId = util.antiSQLInspection(request.getParameter("htttId"));
			if (htttId == null)
				htttId = "";				
	    	tthdBean.setHtttId(htttId);
	    	
	    	String noidungId = util.antiSQLInspection(request.getParameter("noidungId"));
			if (noidungId == null)
				noidungId = "";				
	    	tthdBean.setNoidungId(noidungId);
	    	
	    	String nganhangId = util.antiSQLInspection(request.getParameter("nganhangId"));
			if (nganhangId == null)
				nganhangId = "";				
	    	tthdBean.setNganhangId(nganhangId);
	    	
	    	String hdId = util.antiSQLInspection(request.getParameter("hdId"));
			if (hdId == null)
				hdId = "";				
	    	tthdBean.setHdId(hdId);
	    	
			String[] khIds = request.getParameterValues("khSearchId");
			if (khIds != null)
			{
				String _scheme = "";
				for(int i = 0; i < khIds.length; i++)
				{
					_scheme += khIds[i] + ",";
				}
				
				if(_scheme.trim().length() > 0)
				{
					_scheme = _scheme.substring(0, _scheme.length() - 1);
					tthdBean.setKhIds(_scheme);
				}
			}
			
			String[] nhanvienGNIds = request.getParameterValues("nvgnId");
			if (nhanvienGNIds != null)
			{
				String _scheme = "";
				for(int i = 0; i < nhanvienGNIds.length; i++)
				{
					_scheme += nhanvienGNIds[i] + ",";
				}
				
				if(_scheme.trim().length() > 0)
				{
					_scheme = _scheme.substring(0, _scheme.length() - 1);
					tthdBean.setNhanvienGNIds(_scheme);
				}
			}
			else
			{
				tthdBean.setNhanvienGNIds("");
			}
			
			String[] nhanvienBHIds = request.getParameterValues("nvbhId");
			if (nhanvienBHIds != null)
			{
				String _scheme = "";
				for(int i = 0; i < nhanvienBHIds.length; i++)
				{
					_scheme += nhanvienBHIds[i] + ",";
				}
				
				if(_scheme.trim().length() > 0)
				{
					_scheme = _scheme.substring(0, _scheme.length() - 1);
					tthdBean.setNhanvienBHIds(_scheme);
				}
				System.out.println("NVBH:"+_scheme);
			}
			else
			{
				tthdBean.setNhanvienBHIds("");
			}
			
			String[] noptienIds = request.getParameterValues("noptienId");
			if (noptienIds != null)
			{
				String _scheme = "";
				for(int i = 0; i < noptienIds.length; i++)
				{
					_scheme += noptienIds[i] + ",";
				}
				
				if(_scheme.trim().length() > 0)
				{
					_scheme = _scheme.substring(0, _scheme.length() - 1);
					tthdBean.setNoptienIds(_scheme);
				}
			}
			
	    	String nguoinoptien = util.antiSQLInspection(request.getParameter("nguoinoptien"));
	    	if(nguoinoptien == null)
	    		nguoinoptien = "";
	    	tthdBean.setNguoinoptien(nguoinoptien); 
	    	
	    	String hinhthuctt = util.antiSQLInspection(request.getParameter("hinhthuctt"));
	    	if(hinhthuctt == null)
	    		hinhthuctt = "";
	    	tthdBean.setHinhthucTT(hinhthuctt);
	    	
	    	String noidungthanhtoan = util.antiSQLInspection(request.getParameter("ghichu"));
	    	if(noidungthanhtoan == null)
	    		noidungthanhtoan = "";
	    	tthdBean.setNoidungtt(noidungthanhtoan);
	    	
	    	String thuduoc = util.antiSQLInspection(request.getParameter("sotienhd"));
	    	if(thuduoc == null)
	    		thuduoc = "0";
	    	tthdBean.setThuduoc(thuduoc.replaceAll(",", ""));


	    	String bpkinhdoanh = util.antiSQLInspection(request.getParameter("bpkinhdoanh"));
	    	if(bpkinhdoanh == null)
	    		bpkinhdoanh = "0";
	    	tthdBean.setBpkinhdoanh(bpkinhdoanh);

	    	String chenhlech = util.antiSQLInspection(request.getParameter("chenhlech"));
	    	if(chenhlech == null)
	    		chenhlech = "0";
	    	tthdBean.setChenhlech(chenhlech.replaceAll(",", ""));

	    	String tongVND = "" + (Double.parseDouble(thuduoc.replaceAll(",", "")));
	    	tthdBean.setTongVND(tongVND);

	    	String sotienthanhtoan = util.antiSQLInspection(request.getParameter("tongtien"));
	    	if(sotienthanhtoan == null)
	    		sotienthanhtoan = "0";
	    	tthdBean.setSotientt(sotienthanhtoan.replaceAll(",", ""));

	    	String sochungtu = util.antiSQLInspection(request.getParameter("sochungtu"));
	    	if(sochungtu == null)
	    		sochungtu = "";
	    	tthdBean.setSochungtu(sochungtu);
	    	
	    	String sotiendanop = util.antiSQLInspection(request.getParameter("sotiendanop"));
	    	if(sotiendanop == null)
	    		sotiendanop = "0";
	    	tthdBean.setSotiendanop(sotiendanop);
	    	    	
	    	String sotienthuthem = util.antiSQLInspection(request.getParameter("sotienthuthem"));
	    	if(sotienthuthem == null)
	    		sotienthuthem = "0";
	    	tthdBean.setSotienthuthem(sotienthuthem);
	    	
	    	String chonthutuHD = util.antiSQLInspection(request.getParameter("chonthuHD"));
	    	if(chonthutuHD == null)
	    		chonthutuHD = "0";
	    	else
	    		chonthutuHD = "1";
	    	tthdBean.setChonthutuHD(chonthutuHD);
	    	
    		String sotienttNT = util.antiSQLInspection(request.getParameter("sotienthanhtoanNT"));
    		if(sotienttNT == null) sotienttNT = "0";
	    	tthdBean.setSotienttNT(sotienttNT.replaceAll("",""));

	    	//Luu lai hoa don
			String[] avat = request.getParameterValues("avat");
			String[] thanhtoan = request.getParameterValues("thanhtoan");
			String[] nhappId = request.getParameterValues("nppId");
			String[] nppMa = request.getParameterValues("nppMa");
			String[] khId = request.getParameterValues("khId");
			String[] khMa = request.getParameterValues("khMa");
			
			List<IHoadonNPP> hdList =  new ArrayList<IHoadonNPP>();
	/*		Hashtable<String,Double> hsTHANHTOAN = new Hashtable<String, Double>();*/
			boolean error = false;					 
				if(khId != null)
				{	
					IHoadonNPP hoadon = null;
					int m = 0;
					while(m < khId.length)
					{							
						if(avat != null){
								hoadon = new HoadonNPP("", "","", "", "", avat[m], "0", thanhtoan[m], "", "", "");
							    hoadon.setNppId(nhappId[m]);
							    hoadon.setNppMa(nppMa[m]);
							    hoadon.setKhId(khId[m]);
							    hoadon.setKhMa(khMa[m]);
							    hoadon.setLoaihd("");
							 /*   
							    if(thanhtoan[m].trim().length() > 0)
							    	hsTHANHTOAN.put(idHd[m], Double.parseDouble(thanhtoan[m].replaceAll(",", "")));*/
						}	
						hdList.add(hoadon);
					
						m++;
					}	
				}
				tthdBean.setHoadonRs(hdList);
				//tthdBean.setNo_KhachHangList(hsTHANHTOAN);		
			
			String action = request.getParameter("action");
			
			if(action.equals("save") & error == false)
			{	
				if(id == null) //tao moi
				{
					if(!tthdBean.createTTHD())
					{
	    		    	tthdBean.createRs();
	    		    	tthdBean.createRsLoc();
	    		    
	    		    	session.setAttribute("tthdBean", tthdBean);
	    				String nextJSP = request.getContextPath() + "/pages/Distributor/ErpThuTienKhNPPNew.jsp";
	    				response.sendRedirect(nextJSP);
					}
					else
					{
						IErpThutienKhNPPList obj = new ErpThutienKhNPPList();
					    obj.setUserId(userId);
					    obj.init("");
					    
						session.setAttribute("obj", obj);
								
						String nextJSP = request.getContextPath() + "/pages/Distributor/ErpThuTienKhNPP.jsp";
						response.sendRedirect(nextJSP);
					}
				}
				else
				{
					if(!tthdBean.updateTTHD())
					{
						tthdBean.createRs();
						tthdBean.createRsLoc();
		    		    
						session.setAttribute("tthdBean", tthdBean);
	    				String nextJSP = request.getContextPath() + "/pages/Distributor/ErpThuTienKhNPPUpdate.jsp";
	    				response.sendRedirect(nextJSP);
					}
					else
					{						
						IErpThutienKhNPPList obj = new ErpThutienKhNPPList();
					    obj.setUserId(userId);
					    obj.init("");
					    
						session.setAttribute("obj", obj);
								
						String nextJSP = request.getContextPath() + "/pages/Distributor/ErpThuTienKhNPP.jsp";
						response.sendRedirect(nextJSP);
					}
				}
			}
			else if(action.equals("changeTT")){
		    	tthdBean.setThuduoc("0");
		    	tthdBean.setSotiendanop("0");
		    	tthdBean.setSotienthuthem("0");
		    	tthdBean.setSotientt("0");
				hdList.clear();
				tthdBean.setHoadonRs(hdList);
				tthdBean.createRs();
				tthdBean.createRsLoc();

				String nextJSP;
				
				if (id== null){
					nextJSP = request.getContextPath() + "/pages/Distributor/ErpThuTienKhNPPNew.jsp";
				}
				else{
					nextJSP = request.getContextPath() + "/pages/Distributor/ErpThuTienKhNPPUpdate.jsp";   						
				}
				
				session.setAttribute("tthdBean", tthdBean);
				response.sendRedirect(nextJSP);
				
			}
			else
			{	
				tthdBean.createRs();
				tthdBean.createRsLoc();
				String nextJSP;
				if ( id== null){
					nextJSP = request.getContextPath() + "/pages/Distributor/ErpThuTienKhNPPNew.jsp";
				}
				else{
					nextJSP = request.getContextPath() + "/pages/Distributor/ErpThuTienKhNPPUpdate.jsp";   						
				}
				
				session.setAttribute("tthdBean", tthdBean);
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
