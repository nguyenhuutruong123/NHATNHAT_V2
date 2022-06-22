package geso.dms.center.servlets.hopdong;

import geso.dms.center.beans.dondathang.IErpDondathang;
import geso.dms.center.beans.dondathang.IErpDondathangList;
import geso.dms.center.beans.dondathang.imp.ErpDondathang;
import geso.dms.center.beans.dondathang.imp.ErpDondathangList;
import geso.dms.center.db.sql.dbutils;
import geso.dms.center.util.Utility;

import java.io.IOException;
import java.net.URLDecoder;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.google.gson.Gson;

public class ErpDonhangETCSvl extends HttpServlet 
{
	private static final long serialVersionUID = 1L;
	
	public ErpDonhangETCSvl() {
		super();
	}
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		IErpDondathangList obj;
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");
		
		HttpSession session = request.getSession();	    
		
		Utility util = new Utility();
		
		String querystring = request.getQueryString();
		String userId = util.getUserId(querystring);
		
		if (userId.length()==0)
			userId = util.antiSQLInspection(request.getParameter("userId"));
		
		String action = util.getAction(querystring);
		
		String type = util.antiSQLInspection(request.getParameter("type")==null?"":request.getParameter("type"));
		
		if(type.equals("GetDonGia"))
		{
			NumberFormat formatter = new DecimalFormat("#,###,###");
			Gson gson = new Gson();
			
			String spMa = "";
			String dvdlId ="";
			String nppId = util.antiSQLInspection(request.getParameter("nppId"));
			String kbhId = util.antiSQLInspection(request.getParameter("kbhId"));
			String dvkdId = util.antiSQLInspection(request.getParameter("dvkdId"));
			
			String query = (String)request.getQueryString();
			spMa = new String(query.substring(query.indexOf("&spMa=") + 6, query.indexOf("&dvdlId=")).getBytes("UTF-8"), "UTF-8");
			spMa = URLDecoder.decode(spMa, "UTF-8").replace("+", " ");
			
			dvdlId = new String(query.substring(query.indexOf("&dvdlId=") + 8, query.indexOf("&nppId=")).getBytes("UTF-8"), "UTF-8");
			dvdlId = URLDecoder.decode(dvdlId, "UTF-8").replace("+", " ");
			
			dbutils db = new dbutils();
			
			query = " select a.DVDL_FK as dvCHUAN, ( select PK_SEQ from DONVIDOLUONG where DONVI = N'" + dvdlId + "' ) as dvNEW, " + 
					"	case when a.DVDL_FK =( select PK_SEQ from DONVIDOLUONG where DONVI = N'" + dvdlId + "' ) then 1  "+
					"	else ( select soluong1 / soluong2 from QUYCACH where SANPHAM_FK=a.PK_SEQ and DVDL1_FK = a.DVDL_FK and DVDL2_FK =  "+
					"		( select PK_SEQ from DONVIDOLUONG where DONVI = N'" + dvdlId + "' ) ) end as TyLe,  "+
					"	(select soluong1/soluong2 from QUYCACH where SANPHAM_FK=a.PK_SEQ and DVDL1_FK=a.DVDL_FK and DVDL2_FK=  "+
					"	( select PK_SEQ from DONVIDOLUONG where DONVI =  N'" + dvdlId + "' ) ) as QuyCach_THG ,a.TRONGLUONG,a.THETICH, " +  
					" 	  isnull( ( select GIAMUANPP * ( 1 - isnull( ( select chietkhau from BANGGIAMUANPP_NPP where banggiamuaNPP_FK = bg_sp.bgmuaNPP_FK and NPP_FK = '" + nppId + "' ), 0) / 100 ) " +
					"				from BGMUANPP_SANPHAM bg_sp " +
					"			    where SANPHAM_FK = a.pk_seq " +
					"					and BGMUANPP_FK in ( select top(1) PK_SEQ from BANGGIAMUANPP bg inner join BANGGIAMUANPP_NPP bg_npp on bg.PK_SEQ = bg_npp.BANGGIAMUANPP_FK where bg.TRANGTHAI = '1' and bg_npp.NPP_FK = '" + nppId + "' and bg.DVKD_FK = '" + dvkdId + "' and bg.KENH_FK = '" + kbhId + "' order by bg.TUNGAY desc ) ), 0) as giamua " + 
					" from SANPHAM a where a.MA = '" + spMa + "'  ";
			
			System.out.println("[Sql]: " + query);
			
			ResultSet rs = db.get(query);
			double TheTich =0;		
			double TrongLuong =0;
			double DonGia =0;		
			
			double QuyCach=0;
			double TyLe = 0;
			
			
			if(rs != null)
			{
				try 
				{
					if(rs.next())
					{
						TheTich=rs.getDouble("thetich");
						TrongLuong= rs.getDouble("trongluong");
						DonGia =rs.getDouble("giamua");
						QuyCach = rs.getDouble("QuyCach_THG");
						TyLe = rs.getDouble("TyLe");

						SanPham sp = new SanPham();
						sp.setDongia( formatter.format(DonGia*TyLe));
						sp.setTrongluong(formatter.format(TrongLuong*TyLe));
						sp.setThetich(formatter.format(TheTich*TyLe));
						sp.setQuycach(formatter.format(QuyCach) );
						response.setContentType("application/json");
						response.setCharacterEncoding("UTF-8");
						response.getWriter().write(gson.toJson(sp));
					}
					rs.close();
				} 
				catch (Exception e) 
				{
					e.printStackTrace();
				}
				finally
				{
					if(db!=null)db.shutDown();
				}
			}
		}
		else
		{
			String lsxId = util.getId(querystring);
			obj = new ErpDondathangList();
			
			String loaidonhang = request.getParameter("loaidonhang");
			if(loaidonhang == null)
				loaidonhang = "0";
			obj.setLoaidonhang(loaidonhang);
			System.out.println("---LOAI DON HANG: " + loaidonhang);
			
			if (action.equals("delete") )
			{	
				String msg = this.DeleteChuyenKho(lsxId);
				obj.setMsg(msg);
			}
			else
			{
				if(action.equals("chot"))
				{
					String msg = this.Chot(lsxId,userId);
					obj.setMsg(msg);
				}
			}
			
			obj.setUserId(userId);
			obj.init("");
			
			session.setAttribute("obj", obj);
			
			String nextJSP = request.getContextPath() + "/pages/Center/ErpDonHangETC.jsp";
			response.sendRedirect(nextJSP);
		}
	}
	
	private String Chot(String lsxId,String userId) 
	{
		dbutils db = new dbutils();
		String msg = "";
		try
		{
			db.getConnection().setAutoCommit(false);
			
			String query="select isnull(LoaiDonHang,0) as  LoaiDonHang From Erp_DonDatHang where pk_Seq="+lsxId+" ";
			System.out.println("__"+query);
			ResultSet rs =db.get(query);
			int loaidonhang=0;
			while(rs.next())
			{
				loaidonhang=rs.getInt("LoaiDonHang");	
			}

			if(msg.length()>0)
			{
				db.getConnection().rollback();
				return msg;
			}
			query = "update ERP_Dondathang set trangthai = '1',NgaySua='"+getDateTime()+"' where pk_seq = '" + lsxId + "' and TrangThai!=1  ";
			if(!db.update(query))
			{
				msg = "Khong the chot: " + query;
				db.getConnection().rollback();
				return msg;
			}
			db.getConnection().commit();
		}
		catch (Exception e) 
		{
			db.update("rollback");
			return "Exception: " + e.getMessage();
		}
		finally
		{
			db.shutDown();
		}
		return "";
	}
	

	private String DeleteChuyenKho(String lsxId)
	{
		dbutils db = new dbutils();
		String msg = "";
		try
		{
			db.getConnection().setAutoCommit(false);
			
			String query = "update ERP_Dondathang set trangthai = '3' where pk_seq = '" + lsxId + "'";
			if(!db.update(query))
			{
				msg = "1.Khong the xoa: " + query;
				db.getConnection().rollback();
				return msg;
			}
			
			db.getConnection().commit();
			db.shutDown();
		}
		catch (Exception e) 
		{
			db.update("rollback");
			db.shutDown();
			return "Exception: " + e.getMessage();
		}
		
		return "";
		
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");
		
		String action = request.getParameter("action");
		if (action == null)
		{
			action = "";
		}
		
		String loaidonhang = request.getParameter("loaidonhang");
		if(loaidonhang == null)
			loaidonhang = "0";
		
		IErpDondathangList obj = new ErpDondathangList();
		obj.setLoaidonhang(loaidonhang);
		
		Utility util = new Utility();
		
		HttpSession session = request.getSession();
		String userId = util.antiSQLInspection(request.getParameter("userId")); 
		obj.setUserId(userId);
		
		
		if(action.equals("Tao moi"))
		{
			IErpDondathang lsxBean = new ErpDondathang();
			
			lsxBean.setUserId(userId);
			
			lsxBean.setLoaidonhang(loaidonhang);
			
			lsxBean.createRs();
			session.setAttribute("dvkdId", "");
			session.setAttribute("kbhId", "");
			session.setAttribute("nppId", "");
			
			session.setAttribute("lsxBean", lsxBean);
			
			String nextJSP = request.getContextPath() + "/pages/Center/ErpDonHangETCNew.jsp";
			response.sendRedirect(nextJSP);
		}
		else
		{
			if(action.equals("view") || action.equals("next") || action.equals("prev"))
			{
				obj.setUserId(userId);
				String search = getSearchQuery(request, obj);
				obj.setNxtApprSplitting(Integer.parseInt(request.getParameter("nxtApprSplitting")));
				
				obj.init(search);
				obj.setAttribute(request, action, "list", "crrApprSplitting", "nxtApprSplitting");
				session.setAttribute("obj", obj);
				
				String nextJSP = request.getContextPath() + "/pages/Center/ErpDonHangETC.jsp";
				response.sendRedirect(nextJSP);
			}
			else
			{
				String search = getSearchQuery(request, obj);
				obj.setUserId(userId);
				obj.init(search);
				
				
				session.setAttribute("obj", obj);  	
				session.setAttribute("userId", userId);
				
				String nextJSP = request.getContextPath() + "/pages/Center/ErpDonHangETC.jsp";
				response.sendRedirect(nextJSP);
			}
		}
	}
	
	private String getSearchQuery(HttpServletRequest request, IErpDondathangList obj)
	{
		
		Utility util = new Utility();
		
		String query = "select isnull(a.ngaygiochot,a.ngaydonhang) as ngaygiochot,a.PK_SEQ, a.trangthai, a.ngaydonhang,TT.TEN as DIABAN ,c.ten as nppTEN, b.ten as khoTEN, NV.TEN as nguoitao, b.ten as khonhan, a.NGAYSUA, a.NGAYTAO, NV2.TEN as nguoisua, isnull(a.NOTE, '') as NOTE,   " +
				" case a.KBH_FK when 100052 then N'ETC' when 100025 then N'OTC' end as KenhBanHang, isnull(a.iskm,0) as iskm"+
				" from ERP_Dondathang a inner join ERP_KHOTT b on a.kho_fk = b.pk_seq inner join NHAPHANPHOI c on a.NPP_FK = c.pk_seq  " +
				" inner join NHANVIEN nv on a.NGUOITAO = nv.PK_SEQ   " +
				" inner join NHANVIEN nv2 on a.NGUOISUA = nv2.PK_SEQ "+
				" inner join TINHTHANh TT on c.TINHTHANH_FK = tt.PK_SEQ "+
				" where a.pk_seq > 0 " +
				"	and c.tructhuoc_fk = 1 and a.NPP_DACHOT = '1' and a.hopdong_fk is null AND A.NPP_FK IN  "+util.quyen_npp(obj.getUserId()) +"  ";
		
		String tungay = request.getParameter("tungay");
		if(tungay == null)
			tungay = "";
		obj.setTungay(tungay);
		
		String denngay = request.getParameter("denngay");
		if(denngay == null)
			denngay = "";
		obj.setDenngay(denngay);
		
		String trangthai = request.getParameter("trangthai");
		if(trangthai == null)
			trangthai = "";
		obj.setTrangthai(trangthai);
		
		String ctId = request.getParameter("chungtu");
		if(ctId == null)
			ctId = "";
		obj.setctId(ctId);
		
		String nppId = request.getParameter("nppId");
		if(nppId == null)
			nppId = "";
		obj.setNppTen(nppId);
		
		String iskm = util.antiSQLInspection(request.getParameter("iskm")==null?"0":request.getParameter("iskm"));
		obj.setIsKm(iskm);
		
		if(iskm.length() > 0)
			query += " and a.iskm = '" + iskm + "' ";
		
		if(tungay.length() > 0)
			query += " and a.ngaydonhang >= '" + tungay + "'";
		
		if(denngay.length() > 0)
			query += " and a.ngaydonhang <= '" + denngay + "'";
		
		if(trangthai.length() > 0)
			query += " and a.TrangThai = '" + trangthai + "'";
		
		if(ctId.length() > 0)
			query += " and a.PK_SEQ like '%" + ctId + "%'";
		
		if(nppId.length() > 0){
			query += " and a.NPP_FK= '" + nppId + "'";
		}
		
		System.out.print(query);
		return query;
	}
	
	public String getDateTime()
	{
		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		Date date = new Date();
		return dateFormat.format(date);	
	}
	
	class SanPham
	{
		String dongia;
		String quycach ;
		String trongluong;
		String thetich;
		
		public String getTrongluong()
		{
			return trongluong;
		}
		public void setTrongluong(String trongluong)
		{
			this.trongluong = trongluong;
		}
		public String getThetich()
		{
			return thetich;
		}
		public void setThetich(String thetich)
		{
			this.thetich = thetich;
		}
		public String getQuycach()
		{
			return quycach;
		}
		public void setQuycach(String quycach)
		{
			this.quycach = quycach;
		}
		public String getDongia() 
		{
			return dongia;
		}
		public void setDongia(String dongia) 
		{
			this.dongia = dongia;
		}
		
		
	}
	
}
