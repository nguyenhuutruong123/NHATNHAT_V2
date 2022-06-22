package geso.dms.distributor.servlets.donhang;

import geso.dms.distributor.beans.donhang.*;
import geso.dms.distributor.beans.donhang.imp.*;
import geso.dms.distributor.db.sql.dbutils;
import geso.dms.distributor.util.Utility;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.ResultSet;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class DonhangUpdateSvl extends javax.servlet.http.HttpServlet implements javax.servlet.Servlet {
	private static final long serialVersionUID = 1L;

	PrintWriter out;

	public DonhangUpdateSvl() {
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
		if (!cutil.check(userId, userTen, sum)) {
			response.sendRedirect(request.getContextPath() + "/redirect.jsp");
		} else {
			session.setMaxInactiveInterval(30000);

			this.out = response.getWriter();
			Utility util = new Utility();
			String url = request.getHeader("referer");
			System.out.println("URL::::::::::::::"+url);
			System.out.println("URL2::::::::::::::"+request.getAttribute("javax.servlet.forward.request_uri"));
			
			String querystring =request.getQueryString();
			userId = util.getUserId(querystring);

			IDonhangList dhList = new DonhangList();

			String trangthai = util.antiSQLInspection(request.getParameter("trangthai"));
			if (trangthai == null)
				trangthai = "";
			dhList.setTrangthai(trangthai);
					
			String tungay = util.antiSQLInspection(request.getParameter("tungay"));
			if (tungay == null)
				tungay = "";
			dhList.setTungay(tungay);

			String denngay = util.antiSQLInspection(request.getParameter("denngay"));
			if (denngay == null)
				denngay = "";
			dhList.setDenngay(denngay);

			String sohoadon = util.antiSQLInspection(request.getParameter("sohoadon"));
			if (sohoadon == null)
				sohoadon = "";
			dhList.setSohoadon(sohoadon);

			String khachhang = util.antiSQLInspection(request.getParameter("khachhang"));
			if (khachhang == null)
				khachhang = "";
			dhList.setKhachhang(khachhang);

			String mafast = util.antiSQLInspection(request.getParameter("mafast"));
			if (mafast == null)
				mafast = "";
			dhList.setMafast(mafast);

			String tdvId =util.antiSQLInspection( request.getParameter("tdvId"));
			if (tdvId == null)
				tdvId = "";
			dhList.setDdkdId(tdvId);

			String nvgnId = util.antiSQLInspection(request.getParameter("nvgnId"));
			if (nvgnId == null)
				nvgnId = "";
			dhList.setnvgnId(nvgnId);
			
			session.setAttribute("dhList", dhList);

			out.println(userId);

			if (userId.length() == 0)
				userId = util.antiSQLInspection(request.getParameter("userId"));

			String copy = util.antiSQLInspection(request.getParameter("copy"));
			if (copy == null)
				copy = "";

			String id = util.getId(querystring);
			String msg = "";
			/*if (copy.trim().length() > 0)
			{
				msg = copyDONHANG(copy, userId);
				System.out.println("KET QUA COPY DON HANG: " + id);

				if (msg.trim().length() > 10) 
				{
					id = "-1"; // Copy đơn hàng không thành công
					msg = "Có lõi trong quá trình copy đơn hàng. Vui lòng thử copy lại, hoặc bấm nút BACK để tạo mới đơn hàng";
				} 
				else 
				{
					id = msg;
					msg = "";
				}
			} 
			else 
			{
				id = util.getId(querystring);
			}*/

			IDonhang dhBean = new Donhang(id);
			dhBean.setUserId(userId); // phai co UserId truoc khi Init			
			dhBean.init(request);
			//System.out.println("chie khau la "+dhBean.getChietkhau());
			dhBean.setMessage(msg);

			dhBean.setKhId(dhBean.getKhId());
			session.setAttribute("bgstId", dhBean.getBgstId());
			session.setAttribute("khoId", dhBean.getKhoId());

			String nextJSP;

			if(copy.length() > 0)
			{
				dhBean.setId("");
				dhBean.setAplaikhuyenmai(true);
				nextJSP = request.getContextPath() + "/pages/Distributor/DonHangNew.jsp";
			}
			else
			if (util.antiSQLInspection(request.getQueryString()).indexOf("display") >= 0) {
				// dhBean.CreateSpDisplay();
				nextJSP = request.getContextPath() + "/pages/Distributor/DonHangDisplay.jsp";
			} else {
				nextJSP = request.getContextPath() + "/pages/Distributor/DonHangUpdate.jsp";
			}

			session.setAttribute("dhBean", dhBean);
			session.setAttribute("donhangKhac", dhBean.getDonhangKhac());
			session.setAttribute("khId", dhBean.getKhId());
			session.setAttribute("nvgnId", dhBean.getnvgnId());
			session.setAttribute("ddkdId", dhBean.getDdkdId());
			session.setAttribute("ngaydonhang", dhBean.getNgaygiaodich());

			// bo sung them de do phai truy xuat csdl khi nhan sanpham
			session.setAttribute("nppId", dhBean.getNppId());

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
			//dbutils db = new dbutils();
			IDonhang dhBean;
			Utility util = new Utility();
			String id = util.antiSQLInspection(request.getParameter("id"));
			if (id == null || "".equals(id) ) {
				dhBean = new Donhang("");
			} else {
				dhBean = new Donhang(id);
			}
			System.out.println("dhId = "+id);
			
			String[][] action_msg =	dhBean.initDATA( request, session, util );
			System.out.println("chiet khau la "+dhBean.getChietkhau());
			
			IDonhang tmp = (IDonhang)session.getAttribute("dhBean");
			System.out.println("san pham lisl "+tmp);
			//XỬ LÝ ACTION
			String action = action_msg[0][0];		
			if (action.equals("submitKh"))
			{
				if (id == null || "".equals(id)) 
				{
					String nextJSP = request.getContextPath() + "/pages/Distributor/DonHangNew.jsp";
					response.sendRedirect(nextJSP);
				} 
				else 
				{
					String nextJSP = request.getContextPath() + "/pages/Distributor/DonHangUpdate.jsp";
					response.sendRedirect(nextJSP);
				}	
			} 
			else if (action.equals("save_donhang_va_khuyen_mai")) 
			{
				if(action_msg[0][1].trim().length() <=0)  // tạo mới hoặc câp nhật thành công
				{
					String nextJSP = request.getContextPath() + "/pages/Distributor/DonHangUpdate.jsp";
					response.sendRedirect(nextJSP);
				}
				else
				{
					String nextJSP = request.getContextPath() + "/pages/Distributor/DonHangNew.jsp";
					if (id != null && id.length() > 0) 
					{
						nextJSP = request.getContextPath() + "/pages/Distributor/DonHangUpdate.jsp";
					}
					response.sendRedirect(nextJSP);
				}
				
			} 
			else if (action.equals("save")) 
			{
				if (id == null) 
				{
					if (action_msg[0][1].equals("0")) //Tạo mới không thành công
					{
						String nextJSP = request.getContextPath() + "/pages/Distributor/DonHangNew.jsp";
						response.sendRedirect(nextJSP);
					} 
					else 
					{
						String nextJSP = request.getContextPath() + "/pages/Distributor/DonHangNew.jsp";
						response.sendRedirect(nextJSP);
					}
				} 
				else 
				{
					if(action_msg[0][1].equals("1"))  //Cập nhật không thành công
					{
						String nextJSP = request.getContextPath() + "/pages/Distributor/DonHangUpdate.jsp";
						response.sendRedirect(nextJSP);
					}
					else
					{
						String nextJSP = request.getContextPath() + "/pages/Distributor/DonHangNew.jsp";
						response.sendRedirect(nextJSP);
					}
				}
			} 
			else if (action.equals("dexuatlo"))// đề xuất lại lô cho đơn hàng chỉ có ở trang update 
			{
				// đúng sai gì cũng trả về trang update
				String nextJSP = request.getContextPath() + "/pages/Distributor/DonHangUpdate.jsp";
				response.sendRedirect(nextJSP);
			} 
			else if (action.equals("chotdonhang")) 
			{
				if(action_msg[0][1].equals("3") || action_msg[0][1].equals("4") )  //Chốt không thành công
				{
					String nextJSP = request.getContextPath() + "/pages/Distributor/DonHangUpdate.jsp";
					response.sendRedirect(nextJSP);
				}
				else //Chốt thành công, ra lại trang tổng đon hàng
				{
					IDonhangList obj = new DonhangList();
					obj.setUserId(userId);
					String search = getSearchQuery(request, obj);
					obj.init(search);
					session.setAttribute("obj", obj);
					session.setAttribute("ddkdId", "");

					String nextJSP = request.getContextPath() + "/pages/Distributor/DonHang.jsp";
					response.sendRedirect(nextJSP);
				}
			} 
			else if (action.equals("apkhuyenmai")) 
			{
				if(action_msg[0][1].equals("6.1"))  //Qua trang tạo mới
				{
					String nextJSP = request.getContextPath() + "/pages/Distributor/DonHangNew.jsp";
					response.sendRedirect(nextJSP);
				}
				else if(action_msg[0][1].equals("6.2")) //Qua trang cập nhật
				{
					String nextJSP = request.getContextPath() + "/pages/Distributor/DonHangUpdate.jsp";
					response.sendRedirect(nextJSP);
				}
				else if(action_msg[0][1].equals("6.3")) //Qua trang chọn khuyến mại
				{
					String nextJSP = request.getContextPath() + "/pages/Distributor/KhuyenMai.jsp";
					response.sendRedirect(nextJSP);
				}
				else if(action_msg[0][1].equals("6.4"))  //Qua trang chọn khuyến mại ĐXK
				{
					String nextJSP = request.getContextPath() + "/pages/Distributor/KhuyenMaiDxk.jsp";
					response.sendRedirect(nextJSP);
				}
			}else if (action.equals("apkhuyenmai_2")) 
			{
				if(action_msg[0][1].equals("6.1"))  //Qua trang tạo mới
				{
					String nextJSP = request.getContextPath() + "/pages/Distributor/DonHangNew.jsp";
					response.sendRedirect(nextJSP);
				}
				else if(action_msg[0][1].equals("6.2")) //Qua trang cập nhật
				{
					String nextJSP = request.getContextPath() + "/pages/Distributor/DonHangUpdate.jsp";
					response.sendRedirect(nextJSP);
				}
				else if(action_msg[0][1].equals("6.3")) //Qua trang chọn khuyến mại
				{
					String nextJSP = request.getContextPath() + "/pages/Distributor/KhuyenMai.jsp";
					response.sendRedirect(nextJSP);
				}
				else if(action_msg[0][1].equals("6.4"))  //Qua trang chọn khuyến mại ĐXK
				{
					String nextJSP = request.getContextPath() + "/pages/Distributor/KhuyenMaiDxk.jsp";
					response.sendRedirect(nextJSP);
				}
			}  
			else  
			{
				String nextJSP = request.getContextPath() + "/pages/Distributor/DonHangNew.jsp";
				if (id != null && !"".equals(id)) { nextJSP = request.getContextPath() + "/pages/Distributor/DonHangUpdate.jsp"; }
				response.sendRedirect(nextJSP);
			}
		}
	}

	private String getSearchQuery(HttpServletRequest request,IDonhangList obj) 
	{
    	String nppId = request.getParameter("nppId");
    	if ( nppId == null)
    		nppId = "";
    	obj.setNppId(nppId);
    	
    	/*String ddkdId = request.getParameter("ddkdTen");
    	if ( ddkdId == null)
    		ddkdId = "";
    	obj.setDdkdId(ddkdId);
    	
    	String trangthai = request.getParameter("trangthai");
    	if (trangthai == null)
    		trangthai = "";    	
    	obj.setTrangthai(trangthai);*/
    	
    	String tungay = request.getParameter("tungay");
    	if (tungay == null)
    		tungay = "";    	
    	obj.setTungay(tungay);
    	
    	String denngay = request.getParameter("denngay");
    	if (denngay == null)
    		denngay = "";    	
    	obj.setDenngay(denngay);
    	
    	String sodonhang = request.getParameter("sodonhang");
    	if (sodonhang == null)
    		sodonhang = "";    	
    	obj.setSohoadon(sodonhang.trim());
    	
    	String khachhang = request.getParameter("khachhang");
    	if (khachhang == null)
    		khachhang = "";    	
    	obj.setKhachhang(khachhang.trim());
    	
    	String mafast = request.getParameter("mafast");
    	if(mafast==null)
    		mafast="";
    	obj.setMafast(mafast);
    	
    	/*String query =
    				"select a.pk_seq as dhId, a.ngaynhap, a.trangthai, a.ngaytao, a.ngaysua, b.ten as nguoitao, d.maFAST as mafast, isnull(a.DAXUATHOADON,0) as DAXUATHOADON , isnull(a.DAIN, '0') as DAINDH,      "+
    					"			c.ten as nguoisua, d.ten as khTen, d.pk_seq as khId, e.pk_seq as ddkdId, e.ten as ddkdTen,       "+
    					"			f.ten as nppTen, (        "+
    					"					select cast( sum( (soluong * giamua -  ( soluong * giamua * cast( chietkhau * 100 / ( soluong * giamua ) as numeric(18, 0) ) / 100 ) ) * ( 1 + thueVAT / 100 ) ) as numeric(18, 0) )  as tienBvat         "+
    					"					from donhang_sanpham         "+
    					"					where donhang_fk = a.pk_seq        "+
    					"					group by donhang_fk ) - isnull( ( select sum(tonggiatri) as giatriKM from donhang_ctkm_trakm where donhangId = a.pk_seq and SPMA is null ), 0 ) as tonggiatri," +
    					"	isnull( ( select sum(thanhtoan) as giatriKMTL from DUYETTRAKHUYENMAI_DONHANG where donhang_fk = a.pk_seq )	, 0 )  as tonggiatriTL, d.THANHTOAN, a.VAT,      " +
    					" 	ISNULL( ( select count(*) from PHIEUXUATKHO_DONHANG where donhang_fk = a.pk_seq and PXK_FK in ( select pk_seq from PHIEUXUATKHO where NPP_FK = a.npp_fk and trangthai != '2' ) ), 0) as exitPXK     "+
    					"	,isnull((select REPLACE( "+
    							"		(SELECT RTRIM(hd.SOHOADON)+ ' ' + ISNULL(hd.KYHIEU,'') AS [data()] FROM " +
    							"		 HOADON hd inner join HOADON_DDH ddh on ddh.HOADON_FK = hd.PK_SEQ where (hd.TRANGTHAI = '2' or hd.TRANGTHAI = '4') and  ddh.DDH_FK = a.pk_seq " +
    							"		FOR XML PATH('p') " +
    							"	),' ',' ')  ),'') as SoHoadon " +
    					" from donhang a left join nhanvien b on a.nguoitao = b.pk_seq " +
    					"		left join nhanvien c on a.nguoisua = c.pk_seq inner join khachhang d on a.khachhang_fk = d.pk_seq       "+
    					"inner join daidienkinhdoanh e on a.ddkd_fk = e.pk_seq inner join nhaphanphoi f on a.npp_fk = f.pk_seq ";
    	query = query + " where a.pk_seq > 0 ";*/
    	
    	String query =  "select a.synced,isnull(a.ngaytaodh,a.ngaygio) as ngaygio,a.pk_seq as dhId, a.ngaynhap, a.trangthai, a.ngaytao, a.ngaysua, d.maFAST, isnull(a.DAXUATHOADON,0) as DAXUATHOADON , isnull(DAIN, '0') as DAINDH,     " +
						"			c.ten as nguoisua, d.ten as khTen, d.pk_seq as khId, e.pk_seq as ddkdId, (select top (1)'T'+CAST(tb.NGAYID as nvarchar(2))+ '_'+ dd.ten from tuyenbanhang tb inner join khachhang_tuyenbh khtb on tb.pk_seq=khtb.tbh_fk "+ 
					" inner join daidienkinhdoanh dd on dd.pk_seq = tb.ddkd_fk where khtb.khachhang_fk=d.pk_seq ) as ddkdTen "+
						"		,	'' as nppTen, a.tonggiatri, d.THANHTOAN, a.VAT, " +
						"	'' as SoHoadon , ' ' as nguoitao, 0 as exitPXK      " +
						" from donhang a   " +
						"		left join nhanvien c on a.nguoisua = c.pk_seq inner join khachhang d on a.khachhang_fk = d.pk_seq        " +
						"		inner join daidienkinhdoanh e on a.ddkd_fk = e.pk_seq  " +
						"where a.npp_fk = '" + nppId + "'  ";
    	
    	/*if (ddkdId.length() > 0)
    	{
			query = query + " and e.pk_seq = '" + ddkdId + "'";		
    	}    
    	
    	if (trangthai.length() > 0)
    	{
    		if(trangthai.equals("4"))
    		{
    			query += " and a.trangthai in (1,3) and a.DAXUATHOADON  = 1 ";
    		}
    		else if (trangthai.equals("7"))
    		{
    			query += " and  a.DAIN  = 1 ";
    		}
    		else
    		{
	    		if(trangthai.equals("1"))
	    		{
	    			query += " and a.trangthai ='1' and a.DAXUATHOADON  = 0 ";
	    		}
	    		else
	    		{
	    			query += " and a.trangthai = '" + trangthai + "'";
	    		}
    		}
    	}
    	else
    		query += " and a.trangthai != '2' ";*/
    	
    	if (tungay.length() > 0)
    	{
			query = query + " and a.ngaynhap >= '" + tungay + "'";			
    	}    	
    	if (denngay.length() > 0)
    	{
			query = query + " and a.ngaynhap <= '" + denngay + "'";	
    	}
    	if (sodonhang.length() > 0)
    	{
    		query = query + " and a.pk_seq like '%" + sodonhang + "%'";	
    	}
    	if (khachhang.length() > 0)
    	{
    		Utility util = new Utility();
    		query = query + " and (d.smartid like '%"+ util.replaceAEIOU(khachhang) +"%' or d.pk_seq like (N'%" + util.replaceAEIOU(khachhang) + "%') or [dbo].[fuConvertToUnsign1](lower(d.ten)) like lower(N'%" + util.replaceAEIOU(khachhang) + "%') )";	
    		//System.out.println("1/ bỏ dấu: " + util.replaceAEIOU(khachhang));
    	}
    	if (mafast.length() > 0)
    	{
    		query = query + " and d.maFAST like '%" + mafast + "%'";	
    	}
    	/*System.out.println("\nQuery cua ban: " + query);*/
    	return query;
	}

  
	/******************** CHUYEN XUONG BEAN CAC HAM XU LY KHUYEN MAI *************/

}
