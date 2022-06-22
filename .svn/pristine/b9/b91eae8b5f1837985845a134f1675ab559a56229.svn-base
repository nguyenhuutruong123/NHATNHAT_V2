package geso.dms.distributor.servlets.hopdong;

import geso.dms.center.util.Utility;
import geso.dms.distributor.beans.dondathang.IErpDuyetddhNppList;
import geso.dms.distributor.beans.dondathang.imp.ErpDuyetddhNppList;
import geso.dms.distributor.beans.hopdong.IErpDonhangNppETC;
import geso.dms.distributor.beans.hopdong.IErpDonhangNppETCList;
import geso.dms.distributor.beans.hopdong.imp.ErpDonhangNppETC;
import geso.dms.distributor.beans.hopdong.imp.ErpDonhangNppETCList;

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

public class ErpDonhangNppETCUpdateSvl extends HttpServlet 
{
	private static final long serialVersionUID = 1L;
	
	PrintWriter out;
    public ErpDonhangNppETCUpdateSvl() 
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
		    IErpDonhangNppETC lsxBean = new ErpDonhangNppETC(id);
		    lsxBean.setUserId(userId); 
		    
		    String nextJSP = "";
			//System.out.println("nha pp id :--------------------" + lsxBean.getNppId());
			lsxBean.init(request);

			session.setAttribute("dvkdId", lsxBean.getDvkdId());
			session.setAttribute("kbhId", lsxBean.getKbhId());
			session.setAttribute("nppId", lsxBean.getNppId());
			session.setAttribute("khoId", lsxBean.getKhoNhapId());
			session.setAttribute("khId", lsxBean.getKhId());
    		if(querystring.contains("display"))
    		{
    			nextJSP = request.getContextPath() + "/pages/Distributor/ErpDonHangNppETCDisplay.jsp";
    		}
    		else if(querystring.contains("duyet"))
    		{
    			nextJSP = request.getContextPath() + "/pages/Distributor/ErpDonHangNppETCDuyetDisplay.jsp";
    		}
    		else
    		{
    			nextJSP = request.getContextPath() + "/pages/Distributor/ErpDonHangNppETCUpdate.jsp";
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
			
			IErpDonhangNppETC lsxBean;
			
			Utility util = new Utility();	
			String id = util.antiSQLInspection(request.getParameter("id"));
		    if(id == null)
		    {  	
		    	lsxBean = new ErpDonhangNppETC("");
		    }
		    else
		    {
		    	lsxBean = new ErpDonhangNppETC(id);
		    }
	
		    lsxBean.setUserId(userId);
		    
		    String tungay = util.antiSQLInspection(request.getParameter("hopdong_tungay"));
		    if(tungay == null || tungay.trim().length() <= 0 )
		    	tungay = getDateTime();
		    lsxBean.setTungay(tungay);
		    
		    String denngay = util.antiSQLInspection(request.getParameter("hopdong_denngay"));
		    if(denngay == null || denngay.trim().length() <= 0)
		    	denngay = getDateTime();
		    lsxBean.setDenngay(denngay);
		    	    
		    String mahopdong = util.antiSQLInspection(request.getParameter("hdId"));
		    if(mahopdong == null)
		    	mahopdong = "";
		    lsxBean.setMahopdong(mahopdong);
		    
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
			
			String gsbhId = util.antiSQLInspection(request.getParameter("gsbhId"));
			if (gsbhId == null)
				gsbhId = "";				
			lsxBean.setGsbhId(gsbhId);
			
			String ddkdId = util.antiSQLInspection(request.getParameter("ddkdId"));
			if (ddkdId == null)
				ddkdId = "";				
			lsxBean.setDdkdId(ddkdId);
			
			String nppId = util.antiSQLInspection(request.getParameter("nppId"));
			if (nppId == null)
				nppId = "";				
			lsxBean.setNppId(nppId);
			
			String khId = util.antiSQLInspection(request.getParameter("khId"));
			if (khId == null)
				khId = "";				
			lsxBean.setKhId(khId);
			
			String vat = util.antiSQLInspection(request.getParameter("ptVat"));
			if (vat == null)
				vat = "";				
			lsxBean.setVat(vat);
			
			String ptChietkhau = util.antiSQLInspection(request.getParameter("ptChietkhau"));
			if (ptChietkhau == null)
				ptChietkhau = "0";				
			lsxBean.setChietkhau(ptChietkhau);
			
			String loaidonhang = util.antiSQLInspection(request.getParameter("loaidonhang"));
			if (loaidonhang == null)
				loaidonhang = "0";				
			lsxBean.setLoaidonhang(loaidonhang);
			
			String[] spStt = request.getParameterValues("spStt");
			lsxBean.setSpStt(spStt);
			
			String[] spMa = request.getParameterValues("spMa");
			lsxBean.setSpMa(spMa);
			
			String[] spTen = request.getParameterValues("spTen");
			lsxBean.setSpTen(spTen);
			
			String[] dvt = request.getParameterValues("donvi");
			lsxBean.setSpDonvi(dvt);
			
			String[] soluong = request.getParameterValues("soluong");
			lsxBean.setSpSoluong(soluong);
			
			String[] soluongton = request.getParameterValues("soluongton");
			lsxBean.setSpSoluongton(soluongton);

			String[] dongia = request.getParameterValues("dongia");
			lsxBean.setSpGianhap(dongia);
			
			String[] spQuyDoi = request.getParameterValues("spQuyDoi");
			lsxBean.setSpQuyDoi(spQuyDoi);
			
			String[] spChietkhau = request.getParameterValues("chietkhau");
			lsxBean.setSpChietkhau(spChietkhau);
			
			String[] spVat = request.getParameterValues("spvat");
			lsxBean.setSpVat(spVat);
			System.out.println("vat ne _____" + spVat);
			
			String[] trongluong = request.getParameterValues("spTrongLuong");
			lsxBean.setSpTrongluong(trongluong);
			
			String[] thetich = request.getParameterValues("spTheTich");
			lsxBean.setSpThetich(thetich);
			
			String[] spTungay = request.getParameterValues("tungay");
			lsxBean.setSpTungay(spTungay);
			
			String[] spDenngay = request.getParameterValues("denngay");
			lsxBean.setSpDenngay(spDenngay);
			
			//THEM CAC LOAI CHIET KHAU
			String[] dhCK_diengiai = request.getParameterValues("dhCK_diengiai");
			lsxBean.setDhck_Diengiai(dhCK_diengiai);
			String[] dhCK_giatri = request.getParameterValues("dhCK_giatri");
			lsxBean.setDhck_giatri(dhCK_giatri);
			String[] dhCK_loai = request.getParameterValues("dhCK_loai");
			lsxBean.setDhck_loai(dhCK_loai);
				
		    String action = request.getParameter("action");
			System.out.println(" action :" + action);

			if(action.equals("save"))
			{	
				System.out.println(" da vao save");
				if(id == null)
				{
					boolean kq = lsxBean.createNK(request);
					if(!kq)
					{
						System.out.println(" vao day 2.2");
						lsxBean.createRs();
	    		    	session.setAttribute("lsxBean", lsxBean);
	    				String nextJSP = request.getContextPath() + "/pages/Distributor/ErpDonHangNppETCNew.jsp";
	    				response.sendRedirect(nextJSP);
					}
					else
					{
						System.out.println(" vao day 2.3");
						IErpDonhangNppETCList obj = new ErpDonhangNppETCList();
						obj.setLoaidonhang(loaidonhang);
						
						obj.setUserId(userId);
						obj.init("");  
				    	session.setAttribute("obj", obj);  	
			    		session.setAttribute("userId", userId);
			    		
			    		String nextJSP = request.getContextPath() + "/pages/Distributor/ErpDonHangNppETC.jsp";	
			    		response.sendRedirect(nextJSP);
					}
				}
				else
				{
					boolean kq = lsxBean.updateNK("1", request);
					if(!kq)
					{
						lsxBean.createRs();
						session.setAttribute("lsxBean", lsxBean);
	    				String nextJSP = request.getContextPath() + "/pages/Distributor/ErpDonHangNppETCUpdate.jsp";
	    				response.sendRedirect(nextJSP);
					}
					else
					{
						/*IErpDonhangNppETCList obj = new ErpDonhangNppETCList();
						obj.setLoaidonhang(loaidonhang);
						
					    obj.setUserId(userId);
					    obj.init("");
						session.setAttribute("obj", obj);							
						String nextJSP = request.getContextPath() + "/pages/Distributor/ErpDonHangNppETC.jsp";
						response.sendRedirect(nextJSP);*/
						
						//Cap nhat thanh cong, giu lai man hinh thong tin vua sua
						lsxBean.createRs();
						session.setAttribute("lsxBean", lsxBean);
	    				String nextJSP = request.getContextPath() + "/pages/Distributor/ErpDonHangNppETCUpdate.jsp";
	    				response.sendRedirect(nextJSP);
					}
				}
			} 
			else if (action.equals("duyet") || action.equals("disp"))
			{
				Hashtable<String, String> sanpham_soluong = new Hashtable<String, String>();
				for(int i = 0; i < spMa.length; i++ )
				{
					//System.out.println("---SP MA LA: " + spMa[i]);
					String temID = spMa[i];
					
					String[] spSOLO = request.getParameterValues(temID + "_spSOLO");
					String[] soLUONGXUAT = request.getParameterValues(temID + "_spSOLUONG");
					
					String[] spNgayHetHan = request.getParameterValues(temID + "_spNGAYHETHAN");
					String[] spNgaynhap = request.getParameterValues(temID + "_spNGAYNHAP");
					
					if(soLUONGXUAT != null)
					{
						for(int j = 0; j < soLUONGXUAT.length; j++ )
						{
							if(soLUONGXUAT[j] != null && soLUONGXUAT[j].trim().length() > 0)
							{
								System.out.println(spMa[i] + "__"
										+ "" + spSOLO[j]+ "__"
												+ "" + spNgayHetHan[j] +"so luoong xuat"
												+ ""+soLUONGXUAT[j].replaceAll(",", "")+ "__"
														+ "" + spNgaynhap[j]  );
								sanpham_soluong.put(spMa[i] + "__" + spSOLO[j]+ "__" + spNgayHetHan[j]+ "__" + spNgaynhap[j], soLUONGXUAT[j].replaceAll(",", ""));								
							}
						}
					}
				}
				
				lsxBean.setSanpham_Soluong(sanpham_soluong);
				
				String dungchungkenh = request.getParameter("dungchungkenh");
				if(dungchungkenh == null)
					dungchungkenh = "0";
				lsxBean.setDungchungKenh(dungchungkenh);	
				
				if(action.equals("disp"))
				{
					lsxBean.init(request);
					lsxBean.setTungay(tungay);
					lsxBean.setSanpham_Soluong(sanpham_soluong);
					session.setAttribute("lsxBean", lsxBean);
    				String nextJSP = request.getContextPath() + "/pages/Distributor/ErpDonHangNppETCDuyetDisplay.jsp";
    				response.sendRedirect(nextJSP);
    				return;
				}
				
				if(!lsxBean.duyetETC())
				{
					lsxBean.init(request);
					lsxBean.setSanpham_Soluong(sanpham_soluong);
					session.setAttribute("lsxBean", lsxBean);
    				String nextJSP = request.getContextPath() + "/pages/Distributor/ErpDonHangNppETCDuyetDisplay.jsp";
    				response.sendRedirect(nextJSP);
				}
				else
				{
					IErpDuyetddhNppList obj = new ErpDuyetddhNppList();
					obj.setUserId(userId);
					obj.init("");
					session.setAttribute("obj", obj); 
					String nextJSP = request.getContextPath() + "/pages/Distributor/ErpDonDatHangNppDuyet.jsp" ;
					response.sendRedirect(nextJSP);
					return;
				/*	lsxBean = new ErpDonhangNppETC(id);
					lsxBean.setUserId(userId);
					lsxBean.init();
					lsxBean.setSanpham_Soluong(sanpham_soluong);
					session.setAttribute("lsxBean", lsxBean);
					String nextJSP = request.getContextPath() + "/pages/Distributor/ErpDonHangNppETCDuyetDisplay.jsp";
					response.sendRedirect(nextJSP);*/
				}
			}
			else
			{
				System.out.println("id : " + userId);
				lsxBean.createRs();
				if(action.equals("submit"))
				{
					lsxBean.initSANPHAM2();
				}
			
				session.setAttribute("dvkdId", lsxBean.getDvkdId());
				session.setAttribute("kbhId", lsxBean.getKbhId());
				session.setAttribute("khoId", lsxBean.getKhoNhapId());
				session.setAttribute("nppId", request.getParameter("nppId"));
				session.setAttribute("ngaydonhang", lsxBean.getTungay());
				session.setAttribute("khId", lsxBean.getKhId());
				session.setAttribute("lsxBean", lsxBean);
				System.out.println("tu ngay la "+ lsxBean.getTungay());
				String nextJSP = "";
				
				nextJSP = request.getContextPath() + "/pages/Distributor/ErpDonHangNppETCNew.jsp";
				if(id != null)
				{
					nextJSP = request.getContextPath() + "/pages/Distributor/ErpDonHangNppETCUpdate.jsp";
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
