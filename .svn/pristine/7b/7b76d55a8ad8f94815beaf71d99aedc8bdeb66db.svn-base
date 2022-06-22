package geso.dms.distributor.servlets.hoadontaichinhKM;

import geso.dms.distributor.beans.donhang.ISanpham;
import geso.dms.distributor.beans.donhang.imp.Sanpham;
import geso.dms.distributor.beans.hoadontaichinhKM.IHoadontaichinhKM;
import geso.dms.distributor.beans.hoadontaichinhKM.IHoadontaichinhKMList;
import geso.dms.distributor.beans.hoadontaichinhKM.imp.HoadontaichinhKM;
import geso.dms.distributor.beans.hoadontaichinhKM.imp.HoadontaichinhKMList;
import geso.dms.distributor.util.Utility;
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

public class HoadontaichinhKMUpdateSvl extends HttpServlet 
{
	private static final long serialVersionUID = 1L;
	
	PrintWriter out;
    public HoadontaichinhKMUpdateSvl() 
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
	
		final HttpServletResponse response2=response;
		
		if(!cutil.check(userId, userTen, sum)){
			response.sendRedirect(request.getContextPath() + "/redirect.jsp");
		}else{
			session.setMaxInactiveInterval(30000);
		
			Utility util = new Utility();
			
	    	String querystring = request.getQueryString();
		    userId = util.getUserId(querystring);
		    
		    if (userId.length()==0)
		    	userId = util.antiSQLInspection(request.getParameter("userId")); 
		    
		    String id = util.getId(querystring);  	
		    IHoadontaichinhKM lsxBean = new HoadontaichinhKM(id);
		    lsxBean.setUserId(userId); 
		    
		    String nextJSP = "";
		    
    		lsxBean.init();
    		
    		if(!querystring.contains("display"))
    		{
    			nextJSP = request.getContextPath() + "/pages/Distributor/HoaDonTaiChinhKMUpdate.jsp";	
    		}
    		else
    		{
    			nextJSP = request.getContextPath() + "/pages/Distributor/HoaDonTaiChinhKMDisplay.jsp";
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
		geso.dms.center.util.Csrf csdr=new geso.dms.center.util.Csrf(request,response,false,true ,false);
		if(!csdr.__validate_post())
		{
			response.sendRedirect(request.getContextPath() + "/redirect.jsp");
			return;
		}
		
		if (!cutil.check(userId, userTen, sum)) {
			response.sendRedirect(request.getContextPath() + "/redirect.jsp");
		} else {
			request.setCharacterEncoding("UTF-8");
		    response.setCharacterEncoding("UTF-8");
		    response.setContentType("text/html; charset=UTF-8");
			
			session.setMaxInactiveInterval(30000);
			
			this.out = response.getWriter();
			IHoadontaichinhKM lsxBean;
			
			Utility util = new Utility();	
			String id = util.antiSQLInspection(request.getParameter("id"));
		    if(id == null)
		    {  	
		    	lsxBean = new HoadontaichinhKM("");
		    }
		    else
		    {
		    	lsxBean = new HoadontaichinhKM(id);
		    }
	
		    lsxBean.setUserId(userId);
		    
		    String ngayxuatHD = util.antiSQLInspection(request.getParameter("ngayxuat"));
		    if(ngayxuatHD == null || ngayxuatHD.trim().length() <= 0)
		    	ngayxuatHD = getDateTime();
		    lsxBean.setNgayxuatHD(ngayxuatHD);
		    
		    
		    String ghichu = util.antiSQLInspection(request.getParameter("ghichu"));
		    if(ghichu == null)
		    	ghichu = "";
		    lsxBean.setGhichu(ghichu);
		    
		    String khId = util.antiSQLInspection(request.getParameter("khId"));
		    if(khId == null)
		    	khId = "";
		    lsxBean.setKhId(khId);
		    
		    String kyhieuhoadon = util.antiSQLInspection(request.getParameter("kyhieuhoadon"));
		    if(kyhieuhoadon == null)
		    	kyhieuhoadon = "";
		    lsxBean.setKyhieuhoadon(kyhieuhoadon);
		    
		    String sohoadon = util.antiSQLInspection(request.getParameter("sohoadon"));
		    if(sohoadon == null)
		    	sohoadon = "";
		    lsxBean.setSohoadon(sohoadon);
		    
		    String hinhthuctt = util.antiSQLInspection(request.getParameter("hinhthuctt"));
		    if(hinhthuctt == null)
		    	hinhthuctt = "";
		    lsxBean.setHinhthucTT(hinhthuctt);
		    
		    String nguoimua = util.antiSQLInspection(request.getParameter("nguoimuahang"));
		    if(nguoimua == null)
		    	nguoimua = "";
		    lsxBean.setNguoimua(nguoimua);
		    
		    String innguoimua = util.antiSQLInspection(request.getParameter("innguoimuahang"));
		    if(innguoimua == null || innguoimua.trim().length() <= 0)
		    {
		    	innguoimua = "0";
		    }else
		    {
		    	innguoimua = "1";
		    }
		    lsxBean.setInNguoimua(innguoimua);
		    
		    String tienck = util.antiSQLInspection(request.getParameter("tienck"));
		    if(tienck == null)
		    	tienck = "0";
		    lsxBean.setTienCK(Double.parseDouble(tienck.replace(",", "")));
		    
		    String tiensauCK = util.antiSQLInspection(request.getParameter("tiensauCK"));
		    if(tiensauCK == null)
		    	tiensauCK = "0";
		    lsxBean.setTienSauCK(Double.parseDouble(tiensauCK.replace(",", "")));
		    
		    String tienvat = util.antiSQLInspection(request.getParameter("tienvat"));
		    if(tienvat == null)
		    	tienvat = "0";
		    lsxBean.setTienVAT(Double.parseDouble(tienvat.replace(",", "")));
		    
		    
				String[] ddhid = util.antiSQLInspection_Array(request.getParameterValues("ddhid"));
				
				String ddh = "";
				if (ddhid != null) 
				{
					for(int i = 0; i < ddhid.length; i++)
					{
						ddh += ddhid[i] + ",";
					}
					
					if(ddh.trim().length() > 0)
					{
						ddh = ddh.substring(0, ddh.length() - 1);
						lsxBean.setDondathangId(ddh);
					}
				}
			
			String[] idspTrakm =  util.antiSQLInspection_Array(request.getParameterValues("idspTrakm"));
			String[] maspTrakm = util.antiSQLInspection_Array(request.getParameterValues("maspTrakm"));
			String[] tenspTraKm =  util.antiSQLInspection_Array(request.getParameterValues("tenspTraKm"));
			String[] dvtTrakm =  util.antiSQLInspection_Array(request.getParameterValues("dvtTrakm"));
			String[] slgTraKm =  util.antiSQLInspection_Array(request.getParameterValues("slgTraKm"));
			String[] dgTrakm =  util.antiSQLInspection_Array(request.getParameterValues("dgTrakm"));
			String[] trakmSpScheme =  util.antiSQLInspection_Array(request.getParameterValues("trakmSpScheme"));
			String[] vatTrakm =  util.antiSQLInspection_Array(request.getParameterValues("vatTrakm"));
			String[] ghichusp =  util.antiSQLInspection_Array(request.getParameterValues("ghichusp"));

			List<ISanpham>	spList = new ArrayList<ISanpham>();
			
			if(maspTrakm != null)
			{
				ISanpham sanpham = null;
				String[] param = new String[8];
				int m = 0;
				while(m < maspTrakm.length)
				{
				  if(maspTrakm[m] != "" & slgTraKm[m].length() > 0)
				  {
					  param[0]= idspTrakm[m];
					  param[1]= maspTrakm[m];
					  param[2]= tenspTraKm [m];
					  param[3]= slgTraKm[m];
					  param[4]= dvtTrakm[m];
					  param[5]= dgTrakm [m];
					  param[6]= trakmSpScheme[m];
					  param[7]= "0";
					  
					  sanpham = new Sanpham(param);
					  sanpham.setVat(vatTrakm[m]);
					  sanpham.setGhichu(ghichusp[m]);
					  spList.add(sanpham);	
					  
					  
				  }
					
					m++ ;
				}
			}
			lsxBean.setScheme_SpList(spList);
			
			String[] spId =  util.antiSQLInspection_Array(request.getParameterValues("idspTrakm"));
			lsxBean.setSpId(spId);
			
			String[] spMa =  util.antiSQLInspection_Array(request.getParameterValues("maspTrakm"));
			lsxBean.setSpMa(spMa);
			
			String[] spTen =  util.antiSQLInspection_Array(request.getParameterValues("tenspTraKm"));
			lsxBean.setSpTen(spTen);
			
			String[] dvt =  util.antiSQLInspection_Array(request.getParameterValues("dvtTrakm"));
			lsxBean.setSpDonvi(dvt);
			
			String[] spDongia =  util.antiSQLInspection_Array(request.getParameterValues("dgTrakm"));
			lsxBean.setSpDongia(spDongia);
						
			String[] spChietkhau =  util.antiSQLInspection_Array(request.getParameterValues("spChietkhau"));
			lsxBean.setSpChietkhau(spChietkhau);
			
			String[] soluong =  util.antiSQLInspection_Array(request.getParameterValues("slgTraKm"));
			lsxBean.setSpSoluong(soluong);
			
			String[] spLoai =  util.antiSQLInspection_Array(request.getParameterValues("spLoai"));
			lsxBean.setSpLoai(spLoai);
			
			String[] spScheme =  util.antiSQLInspection_Array(request.getParameterValues("trakmSpScheme"));
			lsxBean.setSpScheme(spScheme);	
			
			String[] spVat =  util.antiSQLInspection_Array(request.getParameterValues("vatTrakm"));
			lsxBean.setSpVat(spVat);	
			
			
			//Lưu lại trường hợp đổi số lô
			if(spMa != null)
			{
				Hashtable<String, String> sanpham_soluong = new Hashtable<String, String>();
				for(int i = 0; i < spMa.length; i++ )
				{
					//System.out.println("---SP MA LA: " + spMa[i]);
					String temID = spId[i];
					
					String[] spSOLO =  util.antiSQLInspection_Array(request.getParameterValues(temID + "_spSOLO"));
					String[] spNGAYHETHAN =  util.antiSQLInspection_Array(request.getParameterValues(temID + "_spNGAYHETHAN"));
					String[] spNGAYNHAPKHO =  util.antiSQLInspection_Array(request.getParameterValues(temID + "_spNGAYNHAPKHO"));
					String[] soLUONGXUAT =  util.antiSQLInspection_Array(request.getParameterValues(temID + "_spSOLUONG"));
					
					if(soLUONGXUAT != null)
					{
						for(int j = 0; j < soLUONGXUAT.length; j++ )
						{
							if(soLUONGXUAT[j] != null && soLUONGXUAT[j].trim().length() > 0)
							{
								//System.out.println("---KEY SVL: " + ( spId[i] + "__" + spLoai[i] + "__" + spScheme[i].trim() + "__" + spSOLO[j] )  + "   --- GIA TRI: " + soLUONGXUAT[j].replaceAll(",", "") );
								sanpham_soluong.put(spId[i] + "__" + spSOLO[j] + "__" + spNGAYHETHAN[j] + "__" + spNGAYNHAPKHO[j], soLUONGXUAT[j].replaceAll(",", "") );
							}
						}
					}
				}
				lsxBean.setSanpham_Soluong(sanpham_soluong);
			}
			
			
		    String action = request.getParameter("action");
		    
			if(action.equals("save"))
			{	
				if(id == null)
				{
					if(!lsxBean.create())
					{
						lsxBean.createRs();
						lsxBean.getMsg();
	    		    	session.setAttribute("lsxBean", lsxBean);
	    				String nextJSP = request.getContextPath() + "/pages/Distributor/HoaDonTaiChinhKMNew.jsp";
	    				
	    				response.sendRedirect(nextJSP);
					}
					else
					{
						IHoadontaichinhKMList obj = new HoadontaichinhKMList();
						
						obj.setUserId(userId);
						obj.init("");  
				    	session.setAttribute("obj", obj);  	
			    		session.setAttribute("userId", userId);
			    		
			    		String nextJSP = request.getContextPath() + "/pages/Distributor/HoaDonTaiChinhKM.jsp";
			    		response.sendRedirect(nextJSP);
					}
				}
				else
				{
					if(!lsxBean.update())
					{
						lsxBean.createRs();
						session.setAttribute("lsxBean", lsxBean);
	    				String nextJSP = request.getContextPath() + "/pages/Distributor/HoaDonTaiChinhKMUpdate.jsp";
	    				response.sendRedirect(nextJSP);
					}
					else
					{
						IHoadontaichinhKMList obj = new HoadontaichinhKMList();
						
					    obj.setUserId(userId);
					    obj.init("");
						session.setAttribute("obj", obj);							
						String nextJSP = request.getContextPath() + "/pages/Distributor/HoaDonTaiChinhKM.jsp";
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
	    				String nextJSP = request.getContextPath() + "/pages/Distributor/HoaDonTaiChinhKMUpdate.jsp";
	    				response.sendRedirect(nextJSP);
					}
					else
					{
						IHoadontaichinhKMList obj = new HoadontaichinhKMList();
						
					    obj.setUserId(userId);
					    obj.init("");
						session.setAttribute("obj", obj);							
						String nextJSP = request.getContextPath() + "/pages/Distributor/HoaDonTaiChinhKM.jsp";
						response.sendRedirect(nextJSP);
					}
				}
				else
				{
					String nextJSP ="";
					if(id == null )
					{
						lsxBean.createRs();	
						session.setAttribute("lsxBean", lsxBean);					
					    nextJSP = request.getContextPath() + "/pages/Distributor/HoaDonTaiChinhKMNew.jsp";
					}
					if(id != null)
					{
						lsxBean.init();
						lsxBean.setNgayxuatHD(ngayxuatHD);
						
						session.setAttribute("lsxBean", lsxBean);
						nextJSP = request.getContextPath() + "/pages/Distributor/HoaDonTaiChinhKMUpdate.jsp";
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
