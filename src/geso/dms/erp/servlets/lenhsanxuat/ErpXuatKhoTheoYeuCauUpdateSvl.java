package geso.dms.erp.servlets.lenhsanxuat;

import geso.dms.distributor.util.Utility;
import geso.dms.erp.beans.lenhsanxuat.IErpChuyenkhoSX;
import geso.dms.erp.beans.lenhsanxuat.IErpChuyenkhoSXList;
import geso.dms.erp.beans.lenhsanxuat.IYeucau;
import geso.dms.erp.beans.lenhsanxuat.imp.ErpChuyenkhoSX;
import geso.dms.erp.beans.lenhsanxuat.imp.ErpChuyenkhoSXList;
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

public class ErpXuatKhoTheoYeuCauUpdateSvl extends HttpServlet 
{
	private static final long serialVersionUID = 1L;
       
	PrintWriter out;
    public ErpXuatKhoTheoYeuCauUpdateSvl() 
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
		    IErpChuyenkhoSX lsxBean = new ErpChuyenkhoSX(id);
		    lsxBean.setUserId(userId); 
		    
		    String task = request.getParameter("task");
			if(task == null)
				task = "0";
			lsxBean.setIsnhanHang(task);
		    
	        String nextJSP = "";
	        
	        if(request.getQueryString().indexOf("taochuyenkho") >=0 ){
	        		/*String msg= lsxBean.CreateChuyenKho(id);
	        		if(msg.length()==0){
		        		 lsxBean.initXuathang();
		        		 nextJSP = "/SalesUp/pages/Erp/ErpChuyenKhoSanXuatXuatHang.jsp";
	        		}else{
	        			
	        		}*/
	        }else if(request.getQueryString().indexOf("xacnhanhang") >=0 ) {
	        	lsxBean.initXuathang();
	        	nextJSP = "/SalesUp/pages/Erp/ErpChotnhanyeucauchuyenkho.jsp";
	        }else if(request.getQueryString().indexOf("xoack") >=0 ) {
	        	
	        	IErpChuyenkhoSXList obj = new ErpChuyenkhoSXList();
	        	String msg = obj.DeleteChuyenKho(id);
			    obj.setUserId(userId);
			    obj.setMsg(msg);
			    obj.init_xh("");
			    session.setAttribute("obj", obj);
	        	nextJSP = "/SalesUp/pages/Erp/ErpChuyenKho_XuatHangList.jsp";
	        	
	        }else{
	        	if(request.getQueryString().indexOf("display") >= 0 ) 
		        {
			        		lsxBean.initXuathang();
				        	nextJSP = "/SalesUp/pages/Erp/ErpChuyenKhoSanXuatDisplay.jsp";
		        }
		        else  
		        {
			        		lsxBean.initXuathang();
			        		nextJSP = "/SalesUp/pages/Erp/ErpChuyenKhoSanXuatXuatHang.jsp";
		        	 
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
			IErpChuyenkhoSX lsxBean;
			
			Utility util = new Utility();	
			String id = util.antiSQLInspection(request.getParameter("id"));
		    if(id == null)
		    {  	
		    	lsxBean = new ErpChuyenkhoSX("");
		    }
		    else
		    {
		    	lsxBean = new ErpChuyenkhoSX(id);
		    }
	
		    lsxBean.setUserId(userId);
		    
		    String task = request.getParameter("task");
			if(task == null)
				task = "0";
			lsxBean.setIsnhanHang(task);
		    
		    String ngayyeucau = util.antiSQLInspection(request.getParameter("ngaychuyen"));
		    if(ngayyeucau == null || ngayyeucau.trim().length() <= 0)
		    	ngayyeucau = getDateTime();
		    lsxBean.setNgayyeucau(ngayyeucau);
		    
		    String lydo = util.antiSQLInspection(request.getParameter("lydo"));
		    if(lydo == null)
		    	lydo = "";
		    lsxBean.setLydoyeucau(lydo);
		    
		    String ndxId = util.antiSQLInspection(request.getParameter("ndxId"));
			if (ndxId == null)
				ndxId = "";	
			lsxBean.setNdxId(ndxId);
	    	
		    String khoxuatId = util.antiSQLInspection(request.getParameter("khoxuatId"));
			if (khoxuatId == null)
				khoxuatId = "";				
			lsxBean.setKhoXuatId(khoxuatId);
			
			String trangthaisp = util.antiSQLInspection(request.getParameter("trangthaisp"));
			if(ndxId.equals("100015")) //Xuat huy, mac dinh la khong dat chat luong
			{
				trangthaisp = "-1";
			}
			else
			{
				if (trangthaisp == null)
				{
					trangthaisp = "1";		//dat chat luong
				}
			}
			lsxBean.setTrangthaiSP(trangthaisp);
	    	
			String khonhapId = util.antiSQLInspection(request.getParameter("khonhapId"));
			if (khonhapId == null)
				khonhapId = "";				
			lsxBean.setKhoNhapId(khonhapId); 
			String loaidoituong = util.antiSQLInspection(request.getParameter("loaidoituong"));
			if (loaidoituong == null)
				loaidoituong = "";				
			lsxBean.setLoaidoituong(loaidoituong); 
			
			
			String nhanvienid = util.antiSQLInspection(request.getParameter("nhanvienid"));
			if (nhanvienid == null)
				nhanvienid = "";				
			lsxBean.setNhanVienId(nhanvienid); 
			
			String phongbanid = util.antiSQLInspection(request.getParameter("phongbanid"));
			if (phongbanid == null)
				phongbanid = "";				
			lsxBean.setPhongBanId(phongbanid); 
			
			String checknhanhang = util.antiSQLInspection(request.getParameter("checknhanhang"));
			if (checknhanhang == null)
				checknhanhang = "0";				
			lsxBean.setCheckNhanHang(checknhanhang); 
			
			String loaichuyenkygui = util.antiSQLInspection(request.getParameter("loaichuyenkygui"));
			if (loaichuyenkygui == null) loaichuyenkygui = ""; else loaichuyenkygui = loaichuyenkygui.trim();				
			lsxBean.setLoaiChuyenKyGui(loaichuyenkygui);
			
			String loainhankygui = util.antiSQLInspection(request.getParameter("loainhankygui"));
			if (loainhankygui == null) loainhankygui = ""; else loainhankygui = loainhankygui.trim();			
			lsxBean.setLoaiNhanKyGui(loainhankygui);
			
			String nccchuyenId = util.antiSQLInspection(request.getParameter("nccchuyenId"));
			if (nccchuyenId == null)
				nccchuyenId = "";				
			lsxBean.setNccChuyenIds(nccchuyenId);
			
			String kyhieuct = util.antiSQLInspection(request.getParameter("kyhieuct"));
			if (kyhieuct == null)
				kyhieuct = "";				
			lsxBean.setKyhieu(kyhieuct);
			
			String khachhangid = util.antiSQLInspection(request.getParameter("khachhangid"));
			if (khachhangid == null)
				khachhangid = "";				
			lsxBean.setKhachhangId(khachhangid);
			
			
			String khKyguiId = util.antiSQLInspection(request.getParameter("khKyguiId"));
			if (khKyguiId == null)
				khKyguiId = "";				
			lsxBean.setKhachhangKyguiId(khKyguiId);
			
			String nhanvienkyguiid = util.antiSQLInspection(request.getParameter("nhanvienkyguiid"));
			if (nhanvienkyguiid == null)
				nhanvienkyguiid = "";				
			lsxBean.setNhanvienKyguiId(nhanvienkyguiid);
			 
			String nccnhanId = util.antiSQLInspection(request.getParameter("nccnhanId"));
			if (nccnhanId == null)
				nccnhanId = "";				
			lsxBean.setNccNhanIds(nccnhanId);
			
			
			String lenhdieudong = util.antiSQLInspection(request.getParameter("lenhdieudong"));
			if (lenhdieudong == null)
				lenhdieudong = "";				
			lsxBean.setLenhdieudong(lenhdieudong);
			

			String ngaydieudong = util.antiSQLInspection(request.getParameter("ngaydieudong"));
			if (ngaydieudong == null)
				ngaydieudong = "";				
			lsxBean.setNgaydieudong(ngaydieudong);
			
			
			String cua = util.antiSQLInspection(request.getParameter("cua"));
			if (cua == null)
				cua = "";				
			lsxBean.setCua(cua);
			
			String veviec = util.antiSQLInspection(request.getParameter("veviec"));
			if (veviec == null)
				veviec = "";				
			lsxBean.setVeviec(veviec);
			
			
			String nguoichuyen = util.antiSQLInspection(request.getParameter("nguoichuyen"));
			if (nguoichuyen == null)
				nguoichuyen = "";				
			lsxBean.setNguoichuyen(nguoichuyen);
			
			String phuongtien = util.antiSQLInspection(request.getParameter("phuongtien"));
			if (phuongtien == null)
				phuongtien = "";				
			lsxBean.setPhuongtien(phuongtien);
			
			String[] idsp = request.getParameterValues("idsp");
			String[] masp = request.getParameterValues("masp");
			String[] tensp = request.getParameterValues("tensp");
			String[] soluongyeucau = request.getParameterValues("soluongyeucau"); 
			List<IYeucau> spList = new ArrayList<IYeucau>();
			
				IYeucau yeucau = null;
				for(int m = 0; m < masp.length; m++)
				{	
					if(masp[m] != "")
					{	
						yeucau = new Yeucau();
						yeucau.setId(idsp[m]);
						yeucau.setMa(masp[m]);
						yeucau.setTen(tensp[m]);
						 
						yeucau.setSoluongYC(soluongyeucau[m]);
						
						List<ISpDetail> spConList = new ArrayList<ISpDetail>();
						ISpDetail spCon = null;
						
						String[] solo = request.getParameterValues(masp[m]+"solo");
						String[] slxuatlo = request.getParameterValues(masp[m]+"soluongxuat");
						String[] ngaysanxuat = request.getParameterValues(masp[m]+"ngaysanxuat");
						 
						
						double totalsoluongnhan=0;
						if(solo!=null){
							for(int k=0;k<solo.length ;k++){
							spCon = new  SpDetail();
							 
								double soluongxuatlo=0;
								try{
								  soluongxuatlo=Double.parseDouble(slxuatlo[k]);
								}catch(Exception err){
								}
								
								if(soluongxuatlo > 0) {
									spCon.setSolo(solo[k]);
									spCon.setSoluong(soluongxuatlo+"");
									spConList.add(spCon);
									totalsoluongnhan=totalsoluongnhan+soluongxuatlo;
								}
						 
							}
						}
						yeucau.setTongSoluongDaXuat( totalsoluongnhan +"");
						yeucau.setSpDetailList(spConList);	
						spList.add(yeucau);
					}				
				}
				
				lsxBean.setSpList(spList);
			 
			
			
		    String action = request.getParameter("action");
		    
		    //System.out.println("[ErpChuyenkhoSXUpdateSvl.doPost] action = " + action + ", spList.size = " + spList.size());
 
				 
					if(action.equals("xuathang"))
					{
						if(!lsxBean.xuatHang())
						{
							/*if(lsxBean.getMsg().trim().length()<=0)
							{*/
								lsxBean.initXuathang();
							//}
							session.setAttribute("lsxBean", lsxBean);
		    				String nextJSP = "/SalesUp/pages/Erp/ErpChuyenKhoSanXuatXuatHang.jsp";
		    				response.sendRedirect(nextJSP);
						}
						else
						{
							IErpChuyenkhoSXList obj = new ErpChuyenkhoSXList();
						    obj.setUserId(userId);
						    obj.setIsnhanHang("2");
						    obj.init_xh("");
							session.setAttribute("obj", obj);							
							String nextJSP = "/SalesUp/pages/Erp/ErpChuyenKho_XuatHangList.jsp";
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
