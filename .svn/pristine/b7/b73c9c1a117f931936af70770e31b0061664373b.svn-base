package geso.dms.distributor.servlets.hopdong;

import geso.dms.center.db.sql.Idbutils;
import geso.dms.distributor.beans.hopdong.IErpDonhangNppETC;
import geso.dms.distributor.beans.hopdong.IErpHopdongNpp;
import geso.dms.distributor.beans.hopdong.IErpHopdongNppList;
import geso.dms.distributor.beans.hopdong.imp.ErpDonhangNppETC;
import geso.dms.distributor.beans.hopdong.imp.ErpHopdongNpp;
import geso.dms.distributor.beans.hopdong.imp.ErpHopdongNppList;
import geso.dms.distributor.db.sql.dbutils;
import geso.dms.distributor.util.Utility;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonPrimitive;

public class ErpHopdongNppUpdateSvl extends HttpServlet 
{
	private static final long serialVersionUID = 1L;
	
	PrintWriter out;
    public ErpHopdongNppUpdateSvl() 
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
		    IErpHopdongNpp lsxBean = new ErpHopdongNpp(id);
		    lsxBean.setUserId(userId); 
		    
		    String nextJSP = "";
		    
    		lsxBean.init();
    		
    		session.setAttribute("dvkdId", lsxBean.getDvkdId());
			session.setAttribute("kenhId", lsxBean.getKbhId());
			session.setAttribute("khoxuat", lsxBean.getKhoNhapId());
			session.setAttribute("nppId", lsxBean.getNppId());
    		
			if(querystring.contains("copyHopdong"))
    		{
				lsxBean.setId(null);
				lsxBean.setTrangthai("0");
    			nextJSP = request.getContextPath() + "/pages/Distributor/ErpHopDongNppNew.jsp";
    		}
			else if(querystring.contains("display"))
    		{
    			nextJSP = request.getContextPath() + "/pages/Distributor/ErpHopDongNppDisplay.jsp";
    		}
    		else if(querystring.contains("convert"))
    		{
    			nextJSP = request.getContextPath() + "/pages/Distributor/ErpHopDongNppDisplay.jsp";
    		}
    		else
    		{
    			nextJSP = request.getContextPath() + "/pages/Distributor/ErpHopDongNppUpdate.jsp";
    		}
    		
	        session.setAttribute("lsxBean", lsxBean);
	        response.sendRedirect(nextJSP);
		}		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		request.setCharacterEncoding("UTF-8");
		System.out.println("ajax--------------");
	    geso.dms.center.util.Csrf csdr=new geso.dms.center.util.Csrf(request,response,false,true ,false);
			if(!csdr.__validate_post())
			{
				response.sendRedirect(request.getContextPath() + "/redirect.jsp");
				return;
			}
		    
		
		String action =geso.dms.center.util.Utility.antiSQLInspection(request.getParameter("action"));
		if(action.equals("AUTOCOMPLETE_SP"))
	    {
	    	getSanphamAjax( request,  response);
	    	return;
		}
	    if(action.equals("GetDonVi"))
	    {
	    	getDonVi( request,  response);
	    	return;
		}
		

		
	    response.setCharacterEncoding("UTF-8");
	    response.setContentType("text/html; charset=UTF-8");
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
			
			IErpHopdongNpp lsxBean;
			
			Utility util = new Utility();	
			String id = util.antiSQLInspection(request.getParameter("id"));
			if(id == null) id = "";
		    if(id.length() <=0)
		    {  	
		    	lsxBean = new ErpHopdongNpp("");
		    }
		    else
		    {
		    	lsxBean = new ErpHopdongNpp(id);
		    }
	
		    lsxBean.setUserId(userId);
		    
		    
		    String NgayNopHoSoThau = util.antiSQLInspection(request.getParameter("NgayNopHoSoThau"));
		    if(NgayNopHoSoThau == null )
		    	NgayNopHoSoThau = "";
		    lsxBean.setNgayNopHoSoThau(NgayNopHoSoThau);
		    
		    String NgayMoThau = util.antiSQLInspection(request.getParameter("NgayMoThau"));
		    if(NgayMoThau == null )
		    	NgayMoThau = "";
		    lsxBean.setNgayMoThau(NgayMoThau);
		    
		    
		    String LyDoKhongTrungThau = util.antiSQLInspection(request.getParameter("LyDoKhongTrungThau"));
		    if(LyDoKhongTrungThau == null )
		    	LyDoKhongTrungThau = "";
		    lsxBean.setLyDoKhongTrungThau(LyDoKhongTrungThau);
		    
		    
		    
		    String tungay = util.antiSQLInspection(request.getParameter("hopdong_tungay"));
		    if(tungay == null || tungay.trim().length() <= 0 )
		    	tungay = getDateTime();
		    lsxBean.setTungay(tungay);
		    
		    String denngay = util.antiSQLInspection(request.getParameter("hopdong_denngay"));
		    if(denngay == null || denngay.trim().length() <= 0)
		    	denngay = getDateTime();
		    lsxBean.setDenngay(denngay);
		    	    
		    String mahopdong = util.antiSQLInspection(request.getParameter("mahopdong"));
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
			
			String khId = util.antiSQLInspection(request.getParameter("khId"));
			if (khId == null)
				khId = "";				
			lsxBean.setKhId(khId);
			
			String vat = util.antiSQLInspection(request.getParameter("ptVat"));
			if (vat == null)
				vat = "";				
			lsxBean.setVat(vat);
			
			String loaidonhang = request.getParameter("loaidonhang");
			if (loaidonhang == null)
				loaidonhang = "0";				
			lsxBean.setLoaidonhang(loaidonhang);
			
			String nppId = util.antiSQLInspection(request.getParameter("nppId"));
			if (nppId == null)
				nppId = "";				
			lsxBean.setNppId(nppId);
			
			String hopdongId = util.antiSQLInspection(request.getParameter("hopdongId"));
			if (hopdongId == null)
				hopdongId = "";				
			lsxBean.setHopdongId(hopdongId);
			
		
			
			
			
			String[] spMa = request.getParameterValues("spMa");
			lsxBean.setSpMa(spMa);
			
			String[] spStt = request.getParameterValues("spStt");
			lsxBean.setSpStt(spStt);
			
	
			
			String[] spTen = request.getParameterValues("spTen");
			lsxBean.setSpTen(spTen);
			
			String[] dvt = request.getParameterValues("donvi");
			lsxBean.setSpDonvi(dvt);
			
			String[] soluong = request.getParameterValues("soluong");
			lsxBean.setSpSoluong(soluong);
			
			String[] dongia = request.getParameterValues("dongia");
			lsxBean.setSpGianhap(dongia);
			
			String[] spQuyDoi = request.getParameterValues("spQuyDoi");
			lsxBean.setSpQuyDoi(spQuyDoi);
			
			String[] spChietkhau = request.getParameterValues("chietkhau");
			lsxBean.setSpChietkhau(spChietkhau);
			
			
			String[] trongluong = request.getParameterValues("spTrongLuong");
			lsxBean.setSpTrongluong(trongluong);
			
			String[] thetich = request.getParameterValues("spTheTich");
			lsxBean.setSpThetich(thetich);
			
			String[] spTungay = request.getParameterValues("tungay");
			lsxBean.setSpTungay(spTungay);
			
			String[] spDenngay = request.getParameterValues("denngay");
			lsxBean.setSpDenngay(spDenngay);
			
			String[] slton = request.getParameterValues("slton");
			lsxBean.setDadat(slton);
			
			String[] spVat = request.getParameterValues("spvat");
			lsxBean.setSpVat(spVat);
			
			String[] ckcskh = request.getParameterValues("ckcskh");
			lsxBean.setCkcskh(ckcskh);
			
			
			String[] khApdungId = request.getParameterValues("khApdungId");
			if(khApdungId != null)
			{
				String _khAP = "";
				for(int i = 0; i < khApdungId.length; i++)
					_khAP += khApdungId[i] + ",";
				
				if(_khAP.trim().length() > 0)
				{
					_khAP = _khAP.substring(0, _khAP.length() - 1);
					lsxBean.setKhApdungId(_khAP);
				}
				
				System.out.println("----KHACH HANG AP DUNG: " + _khAP );
			}
				
		    
			if(action.equals("save"))
			{	
				if(id == null || id.trim().length()==0)
				{
					boolean kq = lsxBean.createNK();
					if(!kq)
					{
						lsxBean.createRs();
	    		    	session.setAttribute("lsxBean", lsxBean);
	    				String nextJSP = request.getContextPath() + "/pages/Distributor/ErpHopDongNppNew.jsp";
	    				response.sendRedirect(nextJSP);
					}
					else
					{
						IErpHopdongNppList obj = new ErpHopdongNppList();
						obj.setLoaidonhang(loaidonhang);
						
						obj.setUserId(userId);
						obj.init("");  
				    	session.setAttribute("obj", obj);  	
			    		session.setAttribute("userId", userId);
			    		
			    		String nextJSP = request.getContextPath() + "/pages/Distributor/ErpHopDongNpp.jsp";	
			    		response.sendRedirect(nextJSP);
					}
				}
				else
				{
					boolean kq = lsxBean.updateNK("1");
					if(!kq)
					{
						lsxBean.createRs();
						session.setAttribute("lsxBean", lsxBean);
	    				String nextJSP = request.getContextPath() + "/pages/Distributor/ErpHopDongNppUpdate.jsp";
	    				response.sendRedirect(nextJSP);
					}
					else
					{
						IErpHopdongNppList obj = new ErpHopdongNppList();
						obj.setLoaidonhang(loaidonhang);
						
					    obj.setUserId(userId);
					    obj.init("");
						session.setAttribute("obj", obj);							
						String nextJSP = request.getContextPath() + "/pages/Distributor/ErpHopDongNpp.jsp";
						response.sendRedirect(nextJSP);
					}
				}
			}
			else
			{
				if(action.equals("convert"))
				{
					boolean kq = lsxBean.convertSO();
					String msg = lsxBean.getMsg();
					
					if(!kq)
					{
						lsxBean.init();
						lsxBean.setKhId(khId);
						session.setAttribute("lsxBean", lsxBean);
	    				String nextJSP = request.getContextPath() + "/pages/Distributor/ErpHopDongNppDisplay.jsp";
	    				response.sendRedirect(nextJSP);
					}
					else
					{
						/*IErpHopdongNppList obj = new ErpHopdongNppList();
						obj.setLoaidonhang(loaidonhang);
						
					    obj.setUserId(userId);
					    obj.init("");
						session.setAttribute("obj", obj);							
						String nextJSP = request.getContextPath() + "/pages/Distributor/ErpHopDongNpp.jsp";
						response.sendRedirect(nextJSP);*/
						
						//CHUYEN SANG TRANG CAP NHAT
						IErpDonhangNppETC lsxBean2 = new ErpDonhangNppETC(msg);
						lsxBean2.setUserId(userId); 
		    		    
		    		    String nextJSP = request.getContextPath() + "/pages/Distributor/ErpDonHangNppETCUpdate.jsp";
		    		    
		    		    lsxBean2.init(request);

		    			session.setAttribute("lsxBean", lsxBean2);
		    	        response.sendRedirect(nextJSP);
						
					}
				}
				else
				{
				
					if(action.equals("changePhuLuc"))
					{
						lsxBean.setSpMa(null);
					}
					
					lsxBean.createRs();
					
					session.setAttribute("dvkdId", lsxBean.getDvkdId());
					session.setAttribute("kenhId", lsxBean.getKbhId());
					session.setAttribute("khoxuat", lsxBean.getKhoNhapId());
					session.setAttribute("nppId", lsxBean.getNppId());
					
					session.setAttribute("lsxBean", lsxBean);
					
					String nextJSP = "";
					
					nextJSP = request.getContextPath() + "/pages/Distributor/ErpHopDongNppNew.jsp";
					if(id.length() > 0)
						nextJSP = request.getContextPath() + "/pages/Distributor/ErpHopDongNppUpdate.jsp";
					
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
	
	public void getSanphamAjax(HttpServletRequest request, HttpServletResponse response) throws IOException
	{
		
		response.setContentType("application/json; charset=UTF-8");
		response.setHeader("Cache-Control", "no-store");
		response.setCharacterEncoding("UTF-8");
		dbutils db = new dbutils();
		PrintWriter out = response.getWriter();		
		String term = request.getParameter("term");
		Utility util = new Utility();
		String khoId = request.getParameter("khonhapId");  
		String kenhETC  = "100052";  
		String nppId = request.getParameter("nppId");
		
		String hopdongId = util.antiSQLInspection(request.getParameter("hopdongId"));
		if (hopdongId == null)
			hopdongId = "0";				
		
		String command = "\n select top 20 a.ma, a.ten, b.donvi, isnull(a.hansudung, 0) as hansudung, isnull(a.PT_VAT,0) as thuexuat "
				+ 		 "\n		, ISNULL( k.avai, 0 ) as avai, isnull(hdc.isHdc,0) isHopdongChinh,  isnull(hdc.dongiaHopdongChinh,0) dongiaHopdongChinh, isnull(hdc.donviHopdongChinh,0) donviHopdongChinh" +
				 		 "\n from SANPHAM a inner join DONVIDOLUONG b on a.dvdl_fk = b.pk_seq " +
				 		 "\n inner join nhaphanphoi npp on npp.pk_seq=  " + nppId +
				 		 "\n outer apply " +
				 		 "\n (	" +
				 		 "\n  	select sum(available) avai from NHAPP_KHO where KHO_FK = '"+khoId+"' and sanpham_fk = a.pk_seq  and npp_fk = '"+ nppId +"'  " +
				 		 "\n  	and KBH_FK= case when npp.dungchungkenh = 1 then 100025 else "+kenhETC+" end 	" + 
				 		 "\n ) k" +
				 		"\n outer apply " +
				 		 "\n (	" +
				 		 "\n  	select 1 isHdc , x.dongia as dongiaHopdongChinh , dvhd.donvi as donviHopdongChinh  " +
				 		 "\n  	from ERP_HopDongNPP_SANPHAM x "+
				 		 "\n 	inner join DONVIDOLUONG dvhd on dvhd.PK_SEQ = x.dvdl_fk "+
				 		 "\n 	where x.sanpham_fk = a.pk_seq and x.HOPDONG_FK = '" + hopdongId + "'	" + 
				 		 "\n ) hdc " +
				 		 "\n where a.timkiem like N'%"+util.replaceAEIOU(term)+"%' ";
		ResultSet rs= db.get(command);
		String json = "";
		try 
		{
			JsonArray json_detail = new JsonArray();
			while(rs.next())
			{
				JsonObject jsonObject = new JsonObject();
				jsonObject.add("ma", new JsonPrimitive( rs.getString("ma") ));
				jsonObject.add("ten", new JsonPrimitive( rs.getString("ten") ));
				jsonObject.add("donvi", new JsonPrimitive( rs.getString("donvi") ));
				jsonObject.add("thuexuat", new JsonPrimitive( rs.getString("thuexuat") ));
				jsonObject.add("avai", new JsonPrimitive( rs.getString("avai") ));
				jsonObject.add("isHopdongChinh", new JsonPrimitive( rs.getString("isHopdongChinh") ));
				jsonObject.add("dongiaHopdongChinh", new JsonPrimitive( rs.getString("dongiaHopdongChinh") ));
				jsonObject.add("donviHopdongChinh", new JsonPrimitive( rs.getString("donviHopdongChinh") ));
				json_detail.add(jsonObject);
			}
			json = json_detail.toString();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		db.shutDown();
		out.print(json  );
		return;
	}
	
	public void getDonVi(HttpServletRequest request, HttpServletResponse response) throws IOException
	{
		
		response.setContentType("text/html; charset=UTF-8");
		response.setCharacterEncoding("UTF-8");
		dbutils db = new dbutils();
		PrintWriter out = response.getWriter();		
		String spMa = request.getParameter("spMaDonVi");

		
		ResultSet rs=  ErpHopdongNpp.getDonViTinhRs( db ,  spMa );
		String json = "";
		try 
		{
			String val = "";
			while(rs.next())
			{
				val += "<option value='"+rs.getString("donvi")+"'  >"+rs.getString("donvi")+"</option>" ;							
			}
			if(val.length() > 0)
			{
				json =  " <select name='donvi' style='width: 100%' > " + 
						val +
						"</select> ";
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		db.shutDown();
		out.print(json  );
		return;
	}
	
}
