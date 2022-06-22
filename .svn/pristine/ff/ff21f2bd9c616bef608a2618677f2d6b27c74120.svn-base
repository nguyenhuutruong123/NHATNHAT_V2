package geso.dms.erp.servlets.thutien;

import geso.dms.center.db.sql.dbutils;
import geso.dms.distributor.util.Utility;
import geso.dms.erp.beans.thutien.IDinhkhoanco;
import geso.dms.erp.beans.thutien.IErpThutien;
import geso.dms.erp.beans.thutien.IErpThutienList;
import geso.dms.erp.beans.thutien.IHoadon;
import geso.dms.erp.beans.thutien.imp.Dinhkhoanco;
import geso.dms.erp.beans.thutien.imp.ErpThutien;
import geso.dms.erp.beans.thutien.imp.ErpThutienList;
import geso.dms.erp.beans.thutien.imp.Hoadon;

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

public class ErpThutienUpdateSvl extends HttpServlet 
{
	private static final long serialVersionUID = 1L;
    
	PrintWriter out;
	dbutils db;
	
    public ErpThutienUpdateSvl() {
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
			response.sendRedirect(request.getContextPath() + "/redirect.jsp");
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
			IErpThutien tthdBean = new ErpThutien(id);
	        tthdBean.setCtyId(ctyId);
			tthdBean.setUserId(userId);			
			tthdBean.setnppdangnhap(util.getIdNhapp(userId));
			
			tthdBean.setLoainhanvien(session.getAttribute("loainhanvien"));
			tthdBean.setDoituongIddn(session.getAttribute("doituongId"));
			    
	        
	        String nextJSP;
	        
	        if(request.getQueryString().indexOf("display") >= 0 ) 
	        {
	        	//tthdBean.initDisplay();
	        	tthdBean.init();
	        	nextJSP = request.getContextPath() + "/pages/Erp/ErpThuTienDisplay.jsp";
	        }
	        else
	        {
	        	tthdBean.init();
	        	nextJSP = request.getContextPath() + "/pages/Erp/ErpThuTienUpdate.jsp";
	        }
	 
	        session.setAttribute("nvgnId", tthdBean.getNvgnId());
	        
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
			IErpThutien tthdBean;
			
			Utility util = new Utility();
			String id = util.antiSQLInspection(request.getParameter("id"));
			
	       if(id == null)
		    {  	
		    	tthdBean = new ErpThutien("");
		    }
		    else
		    {
		    	tthdBean = new ErpThutien(id);
		    }

			//tthdBean = new ErpThutien(id);
			tthdBean.setCtyId(ctyId);
		    tthdBean.setUserId(userId);
		    tthdBean.setnppdangnhap(util.getIdNhapp(userId));
		    tthdBean.setLoainhanvien(session.getAttribute("loainhanvien"));
			tthdBean.setDoituongIddn(session.getAttribute("doituongId"));
			
		    String nhomkhtt = util.antiSQLInspection(request.getParameter("nhomkhtt"));
		    if (nhomkhtt == null)
		    	nhomkhtt = "0";
			else
				nhomkhtt = "1";
		    tthdBean.setNhomkhtt(nhomkhtt);
		    
		    String nhomkhttId = util.antiSQLInspection(request.getParameter("nhomkhttId"));
			if (nhomkhttId == null)
				nhomkhttId = "";				
	    	tthdBean.setNhomkhttId(nhomkhttId);
	    	
		    String ngaychungtu = util.antiSQLInspection(request.getParameter("ngaychungtu"));
			if (ngaychungtu == null || ngaychungtu == "")
				ngaychungtu = this.getDateTime();				
	    	tthdBean.setNgaychungtu(ngaychungtu);
	    	
	    	String ngayghiso = util.antiSQLInspection(request.getParameter("ngayghiso"));
			if (ngayghiso == null || ngayghiso == "")
				ngayghiso = this.getDateTime();				
	    	tthdBean.setNgayghiso(ngayghiso);
	    	
	    	String nppIdgoc = util.antiSQLInspection(request.getParameter("nppId"));
			if (nppIdgoc == null)
				nppIdgoc = "";				
	    	tthdBean.setnppIdgoc(nppIdgoc);
	    	
	    	
	    	
	    	
	    	
	    	System.out.println("nppIdgoc:"+nppIdgoc);
	    		    	
	    	if(nppIdgoc.length() > 0)
	    	{
	    		String[] nppId = nppIdgoc.split(" -- ");
	    		
	    		tthdBean.setNppId(nppId[0]);
	    		tthdBean.setisNPP(nppId[1]);
	    	}
	    	
	  /*  	String nppId = util.antiSQLInspection(request.getParameter("nppId"));
			if (nppId == null)
				nppId = "";				
	    	tthdBean.setNppId(nppId);*/
	    	
	    	String htttId = util.antiSQLInspection(request.getParameter("htttId"));
			if (htttId == null)
				htttId = "";				
	    	tthdBean.setHtttId(htttId);
	    	
	    	String bophankinhdoanh = util.antiSQLInspection(request.getParameter("bpkinhdoanh"));
			if (bophankinhdoanh == null)
				bophankinhdoanh = "";				
	    	tthdBean.setBpkinhdoanh(bophankinhdoanh);
	    	
	    	
	    	String noidungId = util.antiSQLInspection(request.getParameter("noidungId"));
			if (noidungId == null)
				noidungId = "";				
	    	tthdBean.setNoidungId(noidungId);
	    	
	    	String nganhangId = util.antiSQLInspection(request.getParameter("nganhangId"));
			if (nganhangId == null)
				nganhangId = "";				
	    	tthdBean.setNganhangId(nganhangId);
	    	
	    	String chinhanhId = util.antiSQLInspection(request.getParameter("chinhanhId"));
			if (chinhanhId == null)
				chinhanhId = "";				
	    	tthdBean.setNPPChinhanhId(chinhanhId);
	    	
	    	String sotaikhoan = util.antiSQLInspection(request.getParameter("sotaikhoan"));
	    	if(sotaikhoan == null)
	    		sotaikhoan = "";
	    	tthdBean.setSotaikhoan(sotaikhoan);
	    	
	    	String noidungthanhtoan = util.antiSQLInspection(request.getParameter("ghichu"));
	    	if(noidungthanhtoan == null)
	    		noidungthanhtoan = "";
	    	tthdBean.setNoidungtt(noidungthanhtoan);
	    	
	    	String ctkemtheo = util.antiSQLInspection(request.getParameter("ctkemtheo"));
	    	if(ctkemtheo == null)
	    		ctkemtheo = "";
	    	tthdBean.setChungTuKemTheo(ctkemtheo);
	    	
	    	String doituong = util.antiSQLInspection(request.getParameter("dtId"));
	    	if(doituong == null||doituong == "")
	    		doituong = "1";
	    	tthdBean.setDoituongId(doituong);
	    	session.setAttribute("dtId", tthdBean.getDoituongId());
	    	
	    	String nhomkenhId = util.antiSQLInspection(request.getParameter("nhomkenhId"));
	    	if(nhomkenhId == null)
	    		nhomkenhId = "";
	    	tthdBean.setNhomkenhId(nhomkenhId);
	    	session.setAttribute("nhomkenhId", tthdBean.getNhomkenhId());
	    		    	
	    	String thuduoc = util.antiSQLInspection(request.getParameter("thuduoc"));
	    	if(thuduoc == null)
	    		thuduoc = "0";
	    	tthdBean.setThuduoc(thuduoc.replaceAll(",", ""));

	    	String thuduocNT = util.antiSQLInspection(request.getParameter("thuduocNT"));
	    	if(thuduocNT == null)
	    		thuduocNT = "0";
	    	tthdBean.setThuduocNT(thuduocNT.replaceAll(",", ""));

	    	String cpnganhang = util.antiSQLInspection(request.getParameter("cpnganhang"));
	    	if(cpnganhang == null)
	    		cpnganhang = "0";
	    	tthdBean.setChiphinganhang(cpnganhang.replaceAll(",", ""));

	    	String cpnganhangNT = util.antiSQLInspection(request.getParameter("cpnganhangNT"));
	    	if(cpnganhangNT == null)
	    		cpnganhangNT = "0";
	    	tthdBean.setChiphinganhangNT(cpnganhangNT.replaceAll(",", ""));

	    	String chenhlech = util.antiSQLInspection(request.getParameter("chenhlech"));
	    	if(chenhlech == null)
	    		chenhlech = "0";
	    	tthdBean.setChenhlech(chenhlech.replaceAll(",", ""));

	    	if(noidungId.equals("100001")){
	    		String tongVND = "" + (Double.parseDouble(thuduoc.replaceAll(",", "")) + Double.parseDouble(cpnganhang.replaceAll(",", "")));
	    		tthdBean.setTongVND(tongVND);
	    		
	    		String tongNT = "" + (Double.parseDouble(thuduocNT.replaceAll(",", "")) + Double.parseDouble(cpnganhangNT.replaceAll(",", "")));
	    		tthdBean.setTongNT(tongNT);
	    	}
	    	
	    	String sotienthanhtoan = util.antiSQLInspection(request.getParameter("sotienthanhtoan"));
	    	if(sotienthanhtoan == null)
	    		sotienthanhtoan = "0";
	    	tthdBean.setSotientt(sotienthanhtoan.replaceAll(",", ""));

	    	String sochungtu = util.antiSQLInspection(request.getParameter("sochungtu"));
	    	if(sochungtu == null)
	    		sochungtu = "";
	    	tthdBean.setSochungtu(sochungtu);
	    	
	    	String tienteId = util.antiSQLInspection(request.getParameter("tienteId"));
	    	if(tienteId == null)
	    		tienteId = "";
	    	tthdBean.setTienteId(tienteId);
	    	
	    	String tigia = util.antiSQLInspection(request.getParameter("tigia"));
	    	
	    	if(tigia == null)
	    		tigia = "";
	    	tthdBean.setTigia(tigia.replaceAll("",""));

	    	String chietkhau = util.antiSQLInspection(request.getParameter("chietkhau"));
	    	if(chietkhau == null)
	    		chietkhau = "0";
	    	tthdBean.setChietkhau(chietkhau);
	    	
	    	String kenhbanhang = util.antiSQLInspection(request.getParameter("kbhId"));
	    	if(kenhbanhang == null)
	    		kenhbanhang = "0";
	    	tthdBean.setKbhId(kenhbanhang);
	    	
	    	String chietkhauNT = util.antiSQLInspection(request.getParameter("chietkhauNT"));
	    	if(chietkhauNT == null)
	    		chietkhauNT = "0";
	    	tthdBean.setChietkhauNT(chietkhauNT);
	    	
	    	String nguoinoptien = util.antiSQLInspection(request.getParameter("nguoinoptien"));
	    	if(nguoinoptien == null)
	    		nguoinoptien = "";
	    	tthdBean.setNguoinoptien(nguoinoptien);
	    	
	    	String diachi = util.antiSQLInspection(request.getParameter("diachi"));
	    	if(diachi == null)
	    		diachi = "";
	    	tthdBean.setDiachi(diachi);
	    	
	    	String kbhId = util.antiSQLInspection(request.getParameter("kbhId"));
			if (kbhId == null)
				kbhId = "";				
	    	tthdBean.setKbhId(kbhId);
	    	
	    	String ischuyenCN = util.antiSQLInspection(request.getParameter("ischuyenCN"));
			if (ischuyenCN == null)
				ischuyenCN = "0";	
			else
				ischuyenCN = "1";	
			
			System.out.println("ischuyenCN:"+ischuyenCN);
	    	tthdBean.setIsChuyenCN(ischuyenCN);
	    	
	    	String isthuhoCN = util.antiSQLInspection(request.getParameter("isthuhoCN"));
			if (isthuhoCN == null)
				isthuhoCN = "0";	
			else
				isthuhoCN = "1";	
			
			System.out.println("isthuhoCN:"+isthuhoCN);
	    	tthdBean.setIsthuhoCN(isthuhoCN);
	    	
	    	String nvgnId = util.antiSQLInspection(request.getParameter("nvgnId"));
	    	if(nvgnId == null)
	    		nvgnId = "";
	    	tthdBean.setNvgnId(nvgnId);
	    	session.setAttribute("nvgnId", tthdBean.getNvgnId());
	    		    		    	
	    	String matendoituongdinhkhoan = request.getParameter("doituongdinhkhoan");
	    	if (matendoituongdinhkhoan == null)
	    		matendoituongdinhkhoan = "";
	    	tthdBean.setMaTenDoiTuongDinhKhoan(matendoituongdinhkhoan);
	    	
	    	matendoituongdinhkhoan = matendoituongdinhkhoan.split("--")[0];
	    	tthdBean.setDoituongdinhkhoanId(matendoituongdinhkhoan);
	    	
	    	String doituongtamung = request.getParameter("doituongtamung");
	    	if(doituongtamung == null)
	    		doituongtamung = "1";
	    	tthdBean.setDoiTuongTamUng(doituongtamung);
	    	
	    	String nccId = util.antiSQLInspection(request.getParameter("nccId"));
			if (nccId == null)
				nccId = "";				
	    	tthdBean.setNccId(nccId);
	    	
	    	String nvtuId = util.antiSQLInspection(request.getParameter("nvtuId"));
			if (nvtuId == null)
				nvtuId = "";				
	    	tthdBean.setNvtuId(nvtuId);
	    	
	    	session.setAttribute("doituongdinhkhoan",tthdBean.getDoiTuongDinhKhoan());
	    	    	
    		String sotienttNT = util.antiSQLInspection(request.getParameter("sotienthanhtoanNT"));
    		if(sotienttNT == null) sotienttNT = "0";
	    	tthdBean.setSotienttNT(sotienttNT.replaceAll("",""));
	    	
	    	String xoakhttId = util.antiSQLInspection(request.getParameter("xoakhttId"));
			if (xoakhttId == null)
				xoakhttId = "";				
	    	tthdBean.setXoakhtratruocId(xoakhttId);	    	
	    	
	    	
	    	// Lưu lại Định khoản có
	    	String[] taikhoanId = request.getParameterValues("taikhoanId");
//	    	String[] doituongdinhkhoan = request.getParameterValues("doituongdinhkhoan");
	    	String[] doituongId = request.getParameterValues("doituongId");
	    	String[] sotienco = request.getParameterValues("sotienco");
	    	
	    	
	    	List<IDinhkhoanco> dinhkhoanc =  new ArrayList<IDinhkhoanco>();
	    	if(taikhoanId != null)
	    	{
	    		IDinhkhoanco dkc = null ;
	    		int n = 0;

		    		while(n < taikhoanId.length )
		    		{		    			
			    			dkc = new Dinhkhoanco();	    			
			    			dkc.setTaikhoanId(taikhoanId[n]);
			    			
			    		    dkc.setDoituongdinhkhoan(dkc.CheckDoiTuongDinhKhoan(taikhoanId[n]));
			    				
			    			dkc.setDoituongId(doituongId[n]); 			    			
			    			
			    			if(sotienco[n] == null) sotienco[n] = "0" ;			    			
			    			dkc.setSotien(sotienco[n]);
			    			
			    			if(taikhoanId[n].trim().length() > 0)
			    			{
			    				dinhkhoanc.add(dkc);
			    				
			    			}
			    			
			    			n ++;
		    			
		    		}	    		
	    	}
	    	
	    	tthdBean.setDinhkhoancoRs(dinhkhoanc);
	    	
	    	boolean error = false;
	    	//Luu lai hoa don
	    	String[] idHd = request.getParameterValues("idHd");
	    	String[] mahd = request.getParameterValues("mahd");
	    	String[] kyhieuhd = request.getParameterValues("kyhieuhd");
			String[] sohd = request.getParameterValues("sohd");
			String[] ngayhd = request.getParameterValues("ngayhd");
			String[] avat = request.getParameterValues("avat");
			String[] sotienNT = request.getParameterValues("sotienNT");
			String[] thanhtoan = request.getParameterValues("thanhtoan");
			String[] loaihd = request.getParameterValues("loaihd");
			String[] tigiahd = request.getParameterValues("tigiaHd");
			if(loaihd==null && kyhieuhd!=null){
				loaihd=new String[kyhieuhd.length];
			}
			
	//		String[] conlai = request.getParameterValues("conlai");
	//		String[] tygiahd = request.getParameterValues("tygiahd");
			List<IHoadon> hdList =  new ArrayList<IHoadon>();
			
				if(kyhieuhd != null)
				{		
					IHoadon hoadon = null;
					int m = 0;
					while(m < kyhieuhd.length)
					{
						//String tmp = "" + (Double.parseDouble(avat[m].replaceAll(",",""))/Double.parseDouble(tigia.replaceAll(",","")));
						if(!tienteId.equals("100000"))
						{
							if(sotienNT != null){
								if(avat != null){
									if(tthdBean.getNoidungId().equals("100000"))// THU TIEN BAN HANG
									{
										hoadon = new Hoadon(idHd[m], mahd[m], kyhieuhd[m], sohd[m], ngayhd[m], avat[m], sotienNT[m], thanhtoan[m], tienteId,"",tigiahd[m]);
										hoadon.setLoaihd(loaihd[m]);
									}else{ // THU HOI TAM UNG
										hoadon = new Hoadon(idHd[m], "", kyhieuhd[m], sohd[m], ngayhd[m], avat[m], sotienNT[m], thanhtoan[m], tienteId,"","");
									}
									
								}else {
									if(tthdBean.getNoidungId().equals("100000"))// THU TIEN BAN HANG
									{
										hoadon = new Hoadon(idHd[m], mahd[m], kyhieuhd[m], sohd[m], ngayhd[m], "0", sotienNT[m], thanhtoan[m], tienteId,"",tigiahd[m]);
										hoadon.setLoaihd(loaihd[m]);
									}
									else{ // THU HOI TAM UNG
										hoadon = new Hoadon(idHd[m], "", kyhieuhd[m], sohd[m], ngayhd[m], "0", sotienNT[m], thanhtoan[m], tienteId,"","");
									}
								}
								
							}else{
								if(tthdBean.getNoidungId().equals("100000"))// THU TIEN BAN HANG
								{
									hoadon = new Hoadon(idHd[m], mahd[m], kyhieuhd[m], sohd[m], ngayhd[m], avat[m], "0", thanhtoan[m], tienteId, "", tigiahd[m]);
									hoadon.setLoaihd(loaihd[m]);
								}else{
									hoadon = new Hoadon(idHd[m], "", kyhieuhd[m], sohd[m], ngayhd[m], avat[m], "0", thanhtoan[m], tienteId, "", "");
								}
							}
						}
						else{
							
							if(avat != null){
								if(tthdBean.getNoidungId().equals("100000"))// THU TIEN BAN HANG
								{
									hoadon = new Hoadon(idHd[m], mahd[m], kyhieuhd[m], sohd[m], ngayhd[m], avat[m], "0", thanhtoan[m], tienteId, "", tigiahd[m]);
									hoadon.setLoaihd(loaihd[m]);
								}else{// THU HOI TAM UNG									
									hoadon = new Hoadon(idHd[m], "", kyhieuhd[m], sohd[m], ngayhd[m], avat[m], "0", thanhtoan[m], tienteId,"","");
								}
							}
						}	
						hdList.add(hoadon);
					
						m++;
					}	
				}
				tthdBean.setHoadonRs(hdList);
			
			String action = request.getParameter("action");
			
			if(noidungId.trim().equals("100001") || noidungId.trim().equals("100002") ){
				tthdBean.setSotientt(tthdBean.getThuduoc());
				tthdBean.setSotienttNT(tthdBean.getThuduocNT());
			}
			
			//LUU LAI CHUNG LOAI THANH TOAN
			if( idHd != null  )  //LUU LAI THONG TIN NGUOI DUNG NHAP
			{
				Hashtable<String, String> chungloai_thanhtoan = new Hashtable<String, String>();
				for(int i = 0; i < idHd.length; i++ )
				{
					String temID = idHd[i];
					//System.out.println("---TEMP ID: " + temID);
					
					String[] clTEN = request.getParameterValues(temID + "_clTen");
					String[] clSotien = request.getParameterValues(temID + "_clSotien");
					String[] clThanhtoan = request.getParameterValues(temID + "_clThanhtoan");

					if(clTEN != null)
					{
						for(int j = 0; j < clTEN.length; j++ )
						{
							if(clThanhtoan[j] != null && clThanhtoan[j].trim().length() > 0  )
							{
								String key = idHd[i] + "__" + clTEN[j] + "__" + clSotien[j];
								chungloai_thanhtoan.put(key, clThanhtoan[j].replaceAll(",", "") );
								
								System.out.println("::: KEY SVL: " + key);
							}
						}
					}
				}
				
				tthdBean.setHoadon_chungloai(chungloai_thanhtoan);
			}
						
			if(action.equals("save") & error == false)
			{	
				if(id == null) //tao moi
				{
					
					if(!tthdBean.createTTHD())
					{
	    		    	tthdBean.createRs();
	    		    
	    		    	session.setAttribute("tthdBean", tthdBean);
	    				String nextJSP = request.getContextPath() + "/pages/Erp/ErpThuTienNew.jsp";
	    				response.sendRedirect(nextJSP);
					}
					else
					{
						//tthdBean.updateDonmuahang(tthdBean.getDonmuahangId());
						
						IErpThutienList obj = new ErpThutienList();
						obj.setUserId(userId);
					    obj.setCtyId(ctyId);		
					    obj.setnppdangnhap(util.getIdNhapp(userId));
					    obj.setLoainhanvien(session.getAttribute("loainhanvien"));
					    obj.setDoituongId(session.getAttribute("doituongId"));
						
					    obj.init("");
					    
						session.setAttribute("obj", obj);
								
						String nextJSP = request.getContextPath() + "/pages/Erp/ErpThuTien.jsp";
						response.sendRedirect(nextJSP);
					}
				}
				else
				{
				
					if(!tthdBean.updateTTHD())
					{
						tthdBean.createRs();
		    		    
						session.setAttribute("tthdBean", tthdBean);
	    				String nextJSP = request.getContextPath() + "/pages/Erp/ErpThuTienUpdate.jsp";
	    				response.sendRedirect(nextJSP);
					}
					else
					{
						//tthdBean.updateDonmuahang(tthdBean.getDonmuahangId());
						
						IErpThutienList obj = new ErpThutienList();
					    obj.setUserId(userId);
					    obj.setCtyId(ctyId);		
					    obj.setnppdangnhap(util.getIdNhapp(userId));
					    obj.setLoainhanvien(session.getAttribute("loainhanvien"));
					    obj.setDoituongId(session.getAttribute("doituongId"));
					    obj.init("");
					    
						session.setAttribute("obj", obj);
								
						String nextJSP = request.getContextPath() + "/pages/Erp/ErpThuTien.jsp";
						response.sendRedirect(nextJSP);
					}
				}
			}
			else if(action.equals("changeTT")){				
				
			tthdBean.setThuduoc("0");
			
			tthdBean.setThuduocNT("0");
			
			tthdBean.setChiphinganhang("0");
			
			tthdBean.setChiphinganhangNT("0");
			
			tthdBean.setTigia("");
					    	
			tthdBean.setChenhlech("0");
			
			tthdBean.setTongNT("0");		    	
			
			tthdBean.setSotientt("0");
			
			tthdBean.setSotienttNT("0");
			
			tthdBean.setChenhlechNT("0");
				
			hdList.clear();
			tthdBean.setHoadonRs(hdList);
			
			dinhkhoanc.clear();
			tthdBean.setDinhkhoancoRs(dinhkhoanc);				
			
			tthdBean.createRs();
			
			String nextJSP;
			System.out.println("So tien:"+ id);
			if (id== null){
				nextJSP = request.getContextPath() + "/pages/Erp/ErpThuTienNew.jsp";
			}
			else{
				nextJSP = request.getContextPath() + "/pages/Erp/ErpThuTienUpdate.jsp";   						
			}
			
			session.setAttribute("tthdBean", tthdBean);
			response.sendRedirect(nextJSP);

			}else
			{

				tthdBean.createRs();
				String nextJSP;
				if ( id== null){
					nextJSP = request.getContextPath() + "/pages/Erp/ErpThuTienNew.jsp";
				}
				else{
					nextJSP = request.getContextPath() + "/pages/Erp/ErpThuTienUpdate.jsp";   						
				}
				
				session.setAttribute("tthdBean", tthdBean);
				response.sendRedirect(nextJSP);
			}
		}
	}
	
	private String CheckDoiTuongDinhKhoan(String dinhkhoanco) {
		db = new dbutils();
		
		String doituongdinhkhoan= "";
		String dungchokho = "";
		String dungchonganhang = "";
		String dungchoncc = "";
		String dungchotaisan = "";
		String dungchokhachhang = "";
		String dungchonhanvien = "";
		
		String query= "";
		query = "Select * from ERP_TAIKHOANKT where  SOHIEUTAIKHOAN = '" + dinhkhoanco +"'";
		System.out.println(query);
		ResultSet dtrs = this.db.get(query);
		try {
			while(dtrs.next())
			{
				dungchokho = dtrs.getString("dungchokho");
				dungchonganhang = dtrs.getString("dungchonganhang");
				dungchoncc = dtrs.getString("dungchoncc");
				dungchotaisan = dtrs.getString("dungchotaisan");
				dungchokhachhang = dtrs.getString("dungchokhachhang");
				dungchonhanvien = dtrs.getString("dungchonhanvien");				
			}
			dtrs.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		if(dungchokho.equals("1"))
		{
			doituongdinhkhoan = "1";
		}
		else if(dungchonganhang.equals("1"))
		{
			doituongdinhkhoan = "2";
		}
		else if(dungchoncc.equals("1"))
		{
			doituongdinhkhoan = "3";
		}
		else if(dungchotaisan.equals("1"))
		{
			doituongdinhkhoan = "4";
		}
		else if(dungchokhachhang.equals("1"))
		{
			doituongdinhkhoan = "5";
		}
		else if(dungchonhanvien.equals("1"))
		{
			doituongdinhkhoan = "6";
		}
		
		return doituongdinhkhoan;
	}
	
	private String getDateTime() 
	{
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date date = new Date();
        return dateFormat.format(date);	
	}

	
}