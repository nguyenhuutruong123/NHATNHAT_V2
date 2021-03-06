package geso.dms.center.servlets.tratrungbay;

import geso.dms.center.beans.nhomsptrungbay.ISanpham;
import geso.dms.center.beans.nhomsptrungbay.imp.Sanpham;
import geso.dms.center.beans.tratrungbay.*;
import geso.dms.center.beans.tratrungbay.imp.*;
import geso.dms.center.db.sql.dbutils;
import geso.dms.center.util.Utility;

import java.io.IOException;
import java.io.PrintWriter;
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

public class TratrungbayUpdateSvl extends HttpServlet
{
	private static final long serialVersionUID = 1L;
	
	PrintWriter out; 

    public TratrungbayUpdateSvl() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		HttpSession session = request.getSession();
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
		ITratrungbay tratbBean;
		
		this.out = response.getWriter();
		Utility util = new Utility();
		
    	String querystring = request.getQueryString();
	    String userId = util.getUserId(querystring);
	    
	    out.println(userId);
	    
	    if (userId.length() == 0)
	    	userId = util.antiSQLInspection(request.getParameter("userId"));
	    	    
	    String id = util.getId(querystring);

	    tratbBean = new Tratrungbay(id);
	    tratbBean.setUserId(userId);
        tratbBean.init();
        
        session.setAttribute("tratbBean", tratbBean);
        String nextJSP = request.getContextPath() + "/pages/Center/TraTrungBayUpdate.jsp";
        response.sendRedirect(nextJSP);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		request.setCharacterEncoding("UTF-8");
	    response.setCharacterEncoding("UTF-8");
	    response.setContentType("text/html; charset=UTF-8");
		HttpSession session = request.getSession();
		geso.dms.center.util.Csrf csdr=new geso.dms.center.util.Csrf(request,response,false,true ,false);
		if(!csdr.__validate_post())
		{
			response.sendRedirect(request.getContextPath() + "/redirect.jsp");
			return;
		}
		dbutils db;
		ITratrungbay tratbBean;
		this.out = response.getWriter();
		Utility util = new Utility();
	    String id = util.antiSQLInspection(request.getParameter("id"));	
	    if(id == null){  	
	    	tratbBean = new Tratrungbay("");
	    }else{
	    	tratbBean = new Tratrungbay(id);
	    }
	    	    
		String userId = util.antiSQLInspection(request.getParameter("userId"));
		tratbBean.setUserId(userId);	       
    			
		String diengiai = util.antiSQLInspection(request.getParameter("diengiai"));
		if (diengiai == null)
			diengiai = "";
		tratbBean.setDiengiai(diengiai);
		
		String type = util.antiSQLInspection(request.getParameter("type"));
		if (type == null)
			type = "";
		tratbBean.setType(type);
				
		String tongtien = util.antiSQLInspection(request.getParameter("tongtien"));
		if (tongtien == null)
			tongtien = "";
		tratbBean.setTongtien(tongtien);
		
		String tongluong = util.antiSQLInspection(request.getParameter("tongluong"));
		if (tongluong == null)
			tongluong = "";
		tratbBean.setTongluong(tongluong);
		
		String hinhthuc = util.antiSQLInspection(request.getParameter("hinhthuc"));
		if (hinhthuc == null)
			hinhthuc = "";
		tratbBean.setHinhthuc(hinhthuc);
		
		String nhomspId = util.antiSQLInspection(request.getParameter("nhomsp"));
		if (nhomspId == null)
			nhomspId = "";
		tratbBean.setNhomspId(nhomspId);
		
		String ngaysua = getDateTime();
    	tratbBean.setNgaysua(ngaysua);
    	
    	String[] masp = util.antiSQLInspection_Array(request.getParameterValues("masp"));
		String[] tensp = util.antiSQLInspection_Array(request.getParameterValues("tensp"));
		String[] dongia = util.antiSQLInspection_Array(request.getParameterValues("dongia"));
		String[] soluong = util.antiSQLInspection_Array(request.getParameterValues("soluong"));
				
		Hashtable<String, Integer> sp_nhomSpIds = new Hashtable<String, Integer>();
		List<ISanpham> spSudunglist = new ArrayList<ISanpham>();
		
		if(nhomspId.length() > 0 )
		{
    		if(masp != null)
    		{
    			for(int i = 0; i < masp.length; i++)
    			{
    				if(soluong != null)
    				{
	    				if(soluong[i].length() > 0)
	    					sp_nhomSpIds.put(masp[i], Integer.parseInt(soluong[i]));
    				}
    			}
    		}
		}

		if(nhomspId.length() == 0)
		{
			if(masp != null)
    		{
    			for(int i = 0; i < masp.length; i++)
    			{
    				if(masp[i].length() > 0)
    				{
						Sanpham sp = new Sanpham("", masp[i], tensp[i], soluong[i], dongia[i].replaceAll(",", ""));
						spSudunglist.add(sp);
    				}
    			}
    		}
		}

		tratbBean.setSp_nhomspIds(sp_nhomSpIds);
		tratbBean.setSpSudungList(spSudunglist);
	
 		String action = request.getParameter("action");
 		if(action.equals("save"))
 		{
    		if (id == null )
    		{
    			if (!tratbBean.CreateTratb(masp, dongia, soluong)){
    		    	tratbBean.setUserId(userId);
    		    	tratbBean.createRS();
    		    	session.setAttribute("userId", userId);
    				session.setAttribute("tratbBean", tratbBean);
    				
    				String nextJSP = request.getContextPath() + "/pages/Center/TraTrungBayNew.jsp";
    				response.sendRedirect(nextJSP);
    			}
    			else{
    				ITratrungbayList obj = new TratrungbayList();
    				obj.setUserId(userId);
    				obj.init("");
				
    				session.setAttribute("obj", obj);
    				session.setAttribute("userId", userId);
		    		
    				response.sendRedirect(request.getContextPath() + "/pages/Center/TraTrungBay.jsp");	    	
    			}		
    		}else{
    			if (!(tratbBean.UpdateTratb(masp, dongia, soluong))){			
    		    	tratbBean.setUserId(userId);
    		    	tratbBean.createRS();
    		    	
    		    	session.setAttribute("userId", userId);
    				session.setAttribute("tratbBean", tratbBean);
    				
    				String nextJSP = request.getContextPath() + "/pages/Center/TraTrungBayUpdate.jsp";
    				response.sendRedirect(nextJSP);
    			}
    			else{
    				ITratrungbayList obj = new TratrungbayList();
    				obj.setUserId(userId);
    				obj.init("");
				
    				session.setAttribute("obj", obj);
    				session.setAttribute("userId", userId);
		    		
    				response.sendRedirect(request.getContextPath() + "/pages/Center/TraTrungBay.jsp");	    	
    			}
    		}
	    }
		else
		{
			tratbBean.createRS();		
			if(id != null && nhomspId.length() == 0)
				tratbBean.createTratbSpList();
			session.setAttribute("userId", userId);
			session.setAttribute("tratbBean", tratbBean);
			String nextJSP;
			if (id == null){
				nextJSP = request.getContextPath() + "/pages/Center/TraTrungBayNew.jsp";
			}
			else{
				nextJSP = request.getContextPath() + "/pages/Center/TraTrungBayUpdate.jsp";					
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
