package geso.dms.center.servlets.tieuchithuongkmdiem;
import geso.dms.center.beans.tieuchithuongkmdiem.ITieuchithuongKMDiem;
import geso.dms.center.beans.tieuchithuongkmdiem.ITieuchithuongKMDiemList;
import geso.dms.center.beans.tieuchithuongkmdiem.imp.TieuchithuongKMDiem;
import geso.dms.center.beans.tieuchithuongkmdiem.imp.TieuchithuongKMDiemList;
import geso.dms.center.util.Utility;

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

public class TieuchithuongKMDiemUpdateSvl extends HttpServlet 
{
	private static final long serialVersionUID = 1L;
	PrintWriter out; 
	
    public TieuchithuongKMDiemUpdateSvl() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		HttpSession session = request.getSession();
		ITieuchithuongKMDiem tctskuBean;
		
		this.out = response.getWriter();
		Utility util = new Utility();
		
    	String querystring = request.getQueryString();
	    String userId = util.getUserId(querystring);
	    
	    out.println(userId);
	    
	    if (userId.length() == 0)
	    	userId = util.antiSQLInspection(request.getParameter("userId"));
	    	    
	    String id = util.getId(querystring);
	    
	    tctskuBean = new TieuchithuongKMDiem(id);
	    tctskuBean.setId(id);
	    tctskuBean.setUserId(userId);
	    
        session.setAttribute("tctskuBean", tctskuBean);
        
        String nextJSP = "";
        if(querystring.indexOf("display") >= 0)
        {
        	tctskuBean.init();
        	nextJSP = request.getContextPath() + "/pages/Center/TieuChiThuongKMDiemDisplay.jsp";
        }
        else if(querystring.indexOf("copy") >= 0)
        {
        	tctskuBean.init();
        	tctskuBean.setId("");
        	tctskuBean.setScheme("");
        	tctskuBean.setDiengiai("");
        	nextJSP = request.getContextPath() + "/pages/Center/TieuChiThuongKMDiemNew.jsp";
        }
        else
        {
        	tctskuBean.init();
        	nextJSP = request.getContextPath() + "/pages/Center/TieuChiThuongKMDiemUpdate.jsp";
        }
        
        response.sendRedirect(nextJSP);
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		
		ITieuchithuongKMDiem tctskuBean;
		request.setCharacterEncoding("UTF-8");
	    response.setCharacterEncoding("UTF-8");
	    response.setContentType("text/html; charset=UTF-8");
		HttpSession session = request.getSession();
		this.out = response.getWriter();
		Utility util = new Utility();
		
	   	String id = util.antiSQLInspection(request.getParameter("id"));
	   
	    if(id == null){  	
	    	tctskuBean = new TieuchithuongKMDiem("");
	    }else{
	    	tctskuBean = new TieuchithuongKMDiem(id);
	    }
	    
		String userId = util.antiSQLInspection(request.getParameter("userId"));
		if(userId == null)
			userId = "";
		System.out.println("userid "+userId);
		tctskuBean.setUserId(userId);
		
		String phanloai = request.getParameter("phanloai");
		if(phanloai == null)
			phanloai = "0";
		tctskuBean.setPhanloai(phanloai);
		
		String diengiai = util.antiSQLInspection(request.getParameter("diengiai"));
		if (diengiai == null)
			diengiai = "";
		tctskuBean.setDiengiai(diengiai);
		
		String scheme = util.antiSQLInspection(request.getParameter("scheme"));
		if (scheme == null)
			scheme = "";
		tctskuBean.setScheme(scheme);
		
		String thang = util.antiSQLInspection(request.getParameter("thang"));
		if (thang == null)
			thang = "";
		tctskuBean.setThang(thang);
		String ghichu = util.antiSQLInspection(request.getParameter("ghichu"));
		if (ghichu == null)
			ghichu= "";
		tctskuBean.setGhichu(ghichu);
		
		String nam = util.antiSQLInspection(request.getParameter("nam"));
		if (nam == null)
			nam = "";
		tctskuBean.setNam(nam);

		//Tra san pham
		String hinhthucTra = request.getParameter("hinhthuctra");
		tctskuBean.setHinhthuctra(hinhthucTra);
		
		String[] maspTra = request.getParameterValues("maspTra");
		tctskuBean.setMaspTra(maspTra);
		
		String[] tenspTra = request.getParameterValues("tenspTra");
		tctskuBean.setTenspTra(tenspTra);
				
		String[] soluongspTra = request.getParameterValues("soluongspTra");
		tctskuBean.setSoluongspTra(soluongspTra);
		
		String[] maspTraTT = request.getParameterValues("maspTraTT");
		String[] tenspTraTT = request.getParameterValues("tenspTraTT");
		String[] soluongTT = request.getParameterValues("soluongTT");
		String[] quydoiTT = request.getParameterValues("quydoiTT");
		Hashtable<String, String> htmaspTT = new Hashtable<String, String>();
		Hashtable<String, String> httenspTT = new Hashtable<String, String>();
		Hashtable<String, String> htslspTT = new Hashtable<String, String>();
		Hashtable<String, String> htqdspTT = new Hashtable<String, String>();
		String[] dongiaspTra = request.getParameterValues("dongiaspTra");
		tctskuBean.setDongiaspTra(dongiaspTra);
				

			if(maspTra != null)
			{
				Hashtable<String, String> htdieukien = new Hashtable<String, String>();
				Hashtable<String, String> htquydoi = new Hashtable<String, String>();

				//String spId = "";
				for(int i = 0; i < maspTra.length; i++)
				{
					htmaspTT.put(maspTra[i], maspTraTT[i]);
					httenspTT.put(maspTra[i], tenspTraTT[i]);
					htslspTT.put(maspTra[i], soluongTT[i]);
					htqdspTT.put(maspTra[i], quydoiTT[i]);
					tctskuBean.setTenspTra(tenspTra);
					
					
				}
				tctskuBean.setMaspTraTT(htmaspTT);
				tctskuBean.setTenspTraTT(httenspTT);
				tctskuBean.setSoluongTT(htslspTT);
				tctskuBean.setQuydoiTT(htqdspTT);
				tctskuBean.setDieukien(htdieukien);
				tctskuBean.setQuydoi(htquydoi);
			}
		
		String[] nppIds = request.getParameterValues("nppIds");
		if(nppIds != null)
		{
			String nppId = "";
			for(int i = 0; i < nppIds.length; i++)
			{
				if(nppIds[i].length() > 0)
					nppId += nppIds[i] + ",";
			}
			
			if(nppId.trim().length() > 0)
			{
				nppId = nppId.substring(0, nppId.length() - 1);
				tctskuBean.setNppIds(nppId);
			}
		}
		//L
		
		//VUNG 
		String[] vungId = request.getParameterValues("vungId");
		String str = "";
		if(vungId != null)
		{
			for(int i = 0; i < vungId.length; i++)
				str += vungId[i] + ",";
			if(str.length() > 0)
				str = str.substring(0, str.length() - 1);
		}
		tctskuBean.setVungIds(str);
	
		String[] khuvucId = request.getParameterValues("khuvucId");
		String str2 = "";
		if(khuvucId != null)
		{
			for(int i = 0; i < khuvucId.length; i++)
				str2 += khuvucId[i] + ",";
			if(str2.length() > 0)
				str2 = str2.substring(0, str2.length() - 1);
		}
		tctskuBean.setKhuvucIds(str2);
		
		String[] kbhId = request.getParameterValues("kenhId");
		String str3 = "";
		if(kbhId != null)
		{
			for(int i = 0; i < kbhId.length; i++)
				str3 += kbhId[i] + ",";
			if(str3.length() > 0)
				str3 = str3.substring(0, str3.length() - 1);
		}
		tctskuBean.setKenhIds(str3);
		
		String activeTab = request.getParameter("activeTab");
		if(activeTab == null)
			activeTab = "";
		System.out.println("Tab "+activeTab);
		tctskuBean.setActiveTab(activeTab);
		
		
		String nspId = request.getParameter("nspId");
		if(nspId == null)
			nspId = "";
		tctskuBean.setNhomsanphamIds(nspId);
		
 		String action = request.getParameter("action");	
 		if(action.equals("save"))
 		{    
    		if (id == null )
    		{
    			if (!tctskuBean.createTctSKU())
    			{
    		    	tctskuBean.setUserId(userId);
    		    	
    		    	tctskuBean.createRs();
    		    	session.setAttribute("userId", userId);
    				session.setAttribute("tctskuBean", tctskuBean);
    				
    				String nextJSP = request.getContextPath() + "/pages/Center/TieuChiThuongKMDiemNew.jsp";
    				response.sendRedirect(nextJSP);
    			}
    			else
    			{
    				ITieuchithuongKMDiemList obj = new TieuchithuongKMDiemList();
				    obj.setUserId(userId);
				    
				    obj.init("");
					session.setAttribute("obj", obj);
				    
				    String nextJSP = request.getContextPath() + "/pages/Center/TieuChiThuongKMDiem.jsp";
					response.sendRedirect(nextJSP);
    			}	
    		}
    		else
    		{
    			if (!(tctskuBean.updateTctSKU()))
    			{			
    		    	tctskuBean.setUserId(userId);
    		    	System.out.println("voday1");
    		    	tctskuBean.createRs();
    		    	session.setAttribute("userId", userId);
    				session.setAttribute("tctskuBean", tctskuBean);
    				
    				String nextJSP = request.getContextPath() + "/pages/Center/TieuChiThuongKMDiemUpdate.jsp";
    				response.sendRedirect(nextJSP);
    			}
				else
				{
					ITieuchithuongKMDiemList obj = new TieuchithuongKMDiemList();
				    obj.setUserId(userId);
				    System.out.println("voday2");
				    obj.init("");
					session.setAttribute("obj", obj);
				    
				    String nextJSP = request.getContextPath() + "/pages/Center/TieuChiThuongKMDiem.jsp";
					response.sendRedirect(nextJSP);
				}
    		}
	    }
		else
		{		
			tctskuBean.createRs();
			if(action.equals("loadSP_NHOM"))
			{
				tctskuBean.setMaspTra(null);
				tctskuBean.setTenspTra(null);
				
				tctskuBean.loadSP_NHOM();
			}
			
			session.setAttribute("userId", userId);
			session.setAttribute("tctskuBean", tctskuBean);
			
			String nextJSP;
			if (id == null){
				nextJSP = request.getContextPath() + "/pages/Center/TieuChiThuongKMDiemNew.jsp";
				System.out.println("bbbbbbbbbbbb");
			}
			else{
				System.out.println("aaaaaaaaaaaa");
				nextJSP = request.getContextPath() + "/pages/Center/TieuChiThuongKMDiemUpdate.jsp";   						
			}
			response.sendRedirect(nextJSP);

		}

	}

	
	public String getDateTime() 
	{
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date date = new Date();
        return dateFormat.format(date);	
	}

}
