package geso.dms.distributor.servlets.dondathang;

import geso.dms.distributor.db.sql.dbutils;
import geso.dms.center.util.Utility;
import geso.dms.distributor.beans.dondathang.IErpDondathangNpp;
import geso.dms.distributor.beans.dondathang.IErpDondathangNppList;
import geso.dms.distributor.beans.dondathang.imp.ErpDondathangNpp;
import geso.dms.distributor.beans.dondathang.imp.ErpDondathangNppList;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.URLDecoder;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.json.JSONObject;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

public class ErpDondathangNppSvl extends HttpServlet 
{
	private static final long serialVersionUID = 1L;
	
	public ErpDondathangNppSvl() {
		super();
	}
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		IErpDondathangNppList obj;
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");
		
		PrintWriter out = response.getWriter();
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
		
		Utility util = new Utility();
		
		String querystring = request.getQueryString();
		String userId = util.getUserId(querystring);
		
		if (userId.length()==0)
			userId = util.antiSQLInspection(request.getParameter("userId"));
		
		String action = util.getAction(querystring);
		
		String locgiaQUYDOI = util.antiSQLInspection(request.getParameter("locgiaQUYDOI"));
		if(locgiaQUYDOI == null)
			locgiaQUYDOI = "0";
		

		String ngaydh = util.antiSQLInspection(request.getParameter("ngaydh"));
    	if(ngaydh == null)
    		ngaydh = "";
		
		
		//System.out.println("ACTION LA: " + action );
		if(locgiaQUYDOI.equals("1"))
		{
			String spMa = util.antiSQLInspection(request.getParameter("spMa"));
			if(spMa == null)
				spMa = "";
			
			String dvt = util.antiSQLInspection(request.getParameter("dvt"));
			if(dvt == null)
				dvt = "";
			
			String dvkdId = "";
			if(session.getAttribute("dvkdId") != null )
				dvkdId = (String) session.getAttribute("dvkdId");
			
			String kbhId = "";
			if(session.getAttribute("kbhId") != null )
				kbhId = (String) session.getAttribute("kbhId");
			
			String nppId = "";
			if(session.getAttribute("nppId") != null )
				nppId = (String) session.getAttribute("nppId");
			
			
			
			String query = (String)request.getQueryString();
			spMa = new String(query.substring(query.indexOf("&spMa=") + 6, query.indexOf("&dvt=")).getBytes("UTF-8"), "UTF-8");
			spMa = URLDecoder.decode(spMa, "UTF-8").replace("+", " ");
			
			dvt = new String(query.substring(query.indexOf("&dvt=") + 5, query.indexOf("&locgiaQUYDOI")).getBytes("UTF-8"), "UTF-8");
			dvt = URLDecoder.decode(dvt, "UTF-8").replace("+", " ");
			
			ngaydh = new String(query.substring(query.indexOf("&locgiaQUYDOI=") + 9, query.indexOf("&ngaydh")).getBytes("UTF-8"), "UTF-8");
			ngaydh = URLDecoder.decode(ngaydh, "UTF-8").replace("+", " ");
			
			////System.out.println(" -- MA SP: " + spMa + " -- DVT: " + dvt );
			//spMa = URLDecoder.decode(spMa, "UTF-8").replace("+", " ");
			//dvt = URLDecoder.decode(dvt, "UTF-8").replace("+", " ");
			
			
			if(spMa.trim().length() > 0 && dvt.trim().length() > 0 )
			{
				dbutils db = new dbutils();
				
				String sql = " select tructhuoc_fk, loaiNPP from NHAPHANPHOI where pk_seq = '" + nppId + "' ";
				ResultSet rs = db.get(sql);
				String tructhuocId = "";
				String loaiNPP = "";
				if(rs != null)
				{
					try 
					{
						if(rs.next())
						{
							loaiNPP = rs.getString("loaiNPP");
							tructhuocId = rs.getString("tructhuoc_fk");
						}
						rs.close();
					} 
					catch (Exception e) { }
				}
				
				String command = "";
				
				if(!loaiNPP.equals("4") && !loaiNPP.equals("5") )
				{
					command = " select a.DVDL_FK as dvCHUAN, ( select PK_SEQ from DONVIDOLUONG where DONVI = N'" + dvt + "' ) as dvNEW, " + 
							"    isnull( ( select SOLUONG1 / SOLUONG2 from QUYCACH where SANPHAM_FK = a.pk_seq and DVDL1_FK = a.DVDL_FK and DVDL2_FK = '100018' ), 0 ) as quydoi, " +  
							" [dbo].[GiaMuanppSanpham]("+ dvkdId +"," + kbhId +  "," + nppId + ",a.pk_seq,0,'"+ ngaydh +"' ) as GiaMua "+ 
							" from SANPHAM a where a.MA = '" + spMa + "'  ";
				}
				else
				{
					command =  "select a.DVDL_FK as dvCHUAN, ( select PK_SEQ from DONVIDOLUONG where DONVI = N'" + dvt + "' ) as dvNEW,      " +
							"	isnull( ( select SOLUONG1 / SOLUONG2 from QUYCACH where SANPHAM_FK = a.pk_seq and DVDL1_FK = a.DVDL_FK and DVDL2_FK = ( select PK_SEQ from DONVIDOLUONG where DONVI = N'" + dvt + "' ) ), 0 ) as quydoi,  	  " +
							"	dbo.[GiaDoitacSanpham](100001,"+kbhId+","+nppId+",a.pk_seq,0,'"+ ngaydh +"')   " +
							"from SANPHAM a where a.MA = '" + spMa + "'  ";
				}
				
				//System.out.println("Lay don gia san pham: " + command);
				String kq  = "0";
				rs = db.get(command);
				try
				{
					if(rs.next())
					{
						String dvCHUAN = rs.getString("dvCHUAN");
						String dvNEW = rs.getString("dvNEW");
						double quydoi = rs.getDouble("quydoi");
						double dongia = rs.getDouble("giamua");
						
						////System.out.println("DON VI NEW: " + dvNEW);
						if(dvCHUAN.equals(dvNEW))
							kq = Double.toString(dongia);
						else
							kq = Double.toString(dongia * quydoi );
						
					}
					rs.close();
				} 
				catch (Exception e) { kq = "0"; }
				
				db.shutDown();
				
				//System.out.println("GIA: " + kq);
				out.write(kq);
			}
			else
			{
				out.write("0");
			}
			
			return;
		}
		else
		{
			String lsxId = util.getId(querystring);
			obj = new ErpDondathangNppList();
			
			String loaidonhang = request.getParameter("loaidonhang");
			if(loaidonhang == null)
				loaidonhang = "0";
			obj.setLoaidonhang(loaidonhang);
			//System.out.println("---LOAI DON HANG: " + loaidonhang);
			
			if (action.equals("delete") )
			{	
				String msg = this.DeleteChuyenKho(lsxId);
				obj.setMsg(msg);
			}
			else if(action.equals("chot"))
			{
				String msg = this.Chot(lsxId, userId);
				if(msg.trim().length() > 0)
				{
					this.moChot(lsxId, userId);
				}
				else
				{
					msg = "Đã  chốt đơn đặt hàng thành công";
				}
				obj.setMsg(msg);
			}
			
			obj.setUserId(userId);
			obj.init("");
			
			session.setAttribute("obj", obj);
			
			String nextJSP = request.getContextPath() + "/pages/Distributor/ErpDonDatHangNpp.jsp";
			if(loaidonhang.equals("4"))
				nextJSP = request.getContextPath() + "/pages/Distributor/ErpDonDatHangKhacNpp.jsp";
			else if(loaidonhang.equals("2"))
				nextJSP = request.getContextPath() + "/pages/Distributor/ErpDonHangKMTichLuyNpp.jsp";
			else if(loaidonhang.equals("1"))
				nextJSP = request.getContextPath() + "/pages/Distributor/ErpDonHangKMUngHangNpp.jsp";
			else if(loaidonhang.equals("3"))
				nextJSP = request.getContextPath() + "/pages/Distributor/ErpDonHangTrungBayNpp.jsp";
			
			response.sendRedirect(nextJSP);
		}
	}
	
 
	private String moChot(String lsxId, String userId) 
	{
		dbutils db = new dbutils();
		String msg = "";
		Utility util = new Utility();
		
		msg= util.Check_Huy_NghiepVu_KhoaSo("ERP_Dondathang", lsxId, "NgayDonHang", db);
		if(msg.length()>0)
			return msg;
		String dadongbo = "";
		try
		{
			
			db.getConnection().setAutoCommit(false);
			Object[] data = null;			
			data = dbutils.setObject(lsxId );
		
			String query = "update ERP_Dondathang set trangthai = '0', NPP_DACHOT = '0' where trangthai=0 and npp_dachot=1 and pk_seq = ? ";
			if(db.update_v2(query, data) !=1)
			{
				msg = "Khong the chot: " + query;
				db.getConnection().rollback();
				return msg;
			}
			
			 query = "update ERP_Dondathang set ngaygiochot = null where pk_seq = ? ";
			if(db.update_v2(query, data) !=1)
			{
				msg = "Khong the chot: " + query;
				db.getConnection().rollback();
				return msg;
			}
			
			geso.dms.center.util.Utility.commit_and_shutdown(db);
		}
		catch (Exception e) 
		{
			e.printStackTrace();
			geso.dms.center.util.Utility.rollback_and_shutdown(db);
			return "Exception: " + e.getMessage();
		}
		return msg;
	}
	
	private String Chot(String lsxId, String userId) 
	{

		dbutils db = new dbutils();
		String msg = "";
		Utility util = new Utility();
		
		msg= util.Check_Huy_NghiepVu_KhoaSo("ERP_Dondathang", lsxId, "NgayDonHang", db);
		if(msg.length()>0)
			return msg;
		String dadongbo = "";
		try
		{
			
			db.getConnection().setAutoCommit(false);
			Object[] data = null;			
			data = dbutils.setObject(lsxId );
		
			String query = "update ERP_Dondathang set trangthai = '0', NPP_DACHOT = '1' where trangthai=0 and npp_dachot=0 and pk_seq = ? ";
			if(db.update_v2(query, data) !=1)
			{
				msg = "Khong the chot: " + query;
				db.getConnection().rollback();
				return msg;
			}
			
			 query = "update ERP_Dondathang set ngaygiochot = (getdate()+'') where pk_seq = ? ";
			if(db.update_v2(query, data) !=1)
			{
				msg = "Khong the chot: " + query;
				db.getConnection().rollback();
				return msg;
			}
			
			db.getConnection().commit();
			//geso.dms.center.util.Utility.commit_and_shutdown(db);
		}
		catch (Exception e) 
		{
			e.printStackTrace();
			if(db!=null) { db.shutDown(); }
			//geso.dms.center.util.Utility.rollback_and_shutdown(db);
			return "Exception: " + e.getMessage();
		}
			if(Utility.IsDongBo){ 
				
			}
			if(db!=null) { db.shutDown(); }
			//geso.dms.center.util.Utility.commit_and_shutdown(db);
			return msg;
	}
	
 
	private String DeleteChuyenKho(String lsxId)
	{
		dbutils db = new dbutils();
		String msg = "";
		try
		{
			db.getConnection().setAutoCommit(false);
			Object[] data = null;
			
			data = dbutils.setObject(lsxId );
			String query = "update ERP_Dondathang set trangthai = '3' where trangthai=0 and npp_dachot=0 and pk_seq = ? ";
			if(db.update_v2(query, data) !=1)
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
		finally
		{
			db.shutDown();
		}
		return "";
		
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");
		geso.dms.center.util.Csrf csdr=new geso.dms.center.util.Csrf(request,response,false,true ,false);
		Utility util = new Utility();
		if(!csdr.__validate_post())
		{
			response.sendRedirect(request.getContextPath() + "/redirect.jsp");
			return;
		}
		String action = request.getParameter("action");
		if (action == null)
		{
			action = "";
		}
		
		String loaidonhang = util.antiSQLInspection(request.getParameter("loaidonhang"));
		if(loaidonhang == null)
			loaidonhang = "0";
		
		
		IErpDondathangNppList obj = new ErpDondathangNppList();
		obj.setLoaidonhang(loaidonhang);
		
		
		HttpSession session = request.getSession();
		String userId = util.antiSQLInspection(request.getParameter("userId")); 
		String nppId = "";
		
		if(session.getAttribute("nppId") != null )
			nppId = (String) session.getAttribute("nppId");
		obj.setNppId(nppId);
		
		if(action.equals("Tao moi"))
		{
			IErpDondathangNpp lsxBean = new ErpDondathangNpp();
			lsxBean.setLoaidonhang(loaidonhang);
			lsxBean.setUserId(userId);
			
			lsxBean.createRs();
			session.setAttribute("dvkdId", lsxBean.getDvkdId());
			session.setAttribute("kbhId", lsxBean.getKbhId());
			session.setAttribute("nppId", lsxBean.getNppId());
			session.setAttribute("ngaydh", lsxBean.getNgayyeucau());
			session.setAttribute("lsxBean", lsxBean);
			
			//String nextJSP = request.getContextPath() + "/pages/Distributor/ErpDonDatHangNppNew.jsp";
			
			String nextJSP = request.getContextPath() + "/pages/Distributor/ErpDonDatHangNppNew.jsp";
			if(loaidonhang.equals("4"))
				nextJSP = request.getContextPath() + "/pages/Distributor/ErpDonDatHangKhacNppNew.jsp";
			else if(loaidonhang.equals("2"))
				nextJSP = request.getContextPath() + "/pages/Distributor/ErpDonHangKMTichLuyNppNew.jsp";
			else if(loaidonhang.equals("1"))
				nextJSP = request.getContextPath() + "/pages/Distributor/ErpDonHangKMUngHangNppNew.jsp";
			else if(loaidonhang.equals("3"))
				nextJSP = request.getContextPath() + "/pages/Distributor/ErpDonHangTrungBayNppNew.jsp";
			
			
			
			
			response.sendRedirect(nextJSP);
		}
		else
		{
			if(action.equals("view") || action.equals("next") || action.equals("prev"))
			{
				String search = getSearchQuery(request, obj);
				obj.setNxtApprSplitting(Integer.parseInt(request.getParameter("nxtApprSplitting")));
				obj.setUserId(userId);
				obj.init(search);
				obj.setAttribute(request, action, "list", "crrApprSplitting", "nxtApprSplitting");
				session.setAttribute("obj", obj);
				
				String nextJSP = request.getContextPath() + "/pages/Distributor/ErpDonDatHangNpp.jsp";
				response.sendRedirect(nextJSP);
			}
			else
			{
				String search = getSearchQuery(request, obj);
				obj.setUserId(userId);
				obj.init(search);
				session.setAttribute("obj", obj);  	
				session.setAttribute("userId", userId);
				
				String nextJSP = request.getContextPath() + "/pages/Distributor/ErpDonDatHangNpp.jsp";
				response.sendRedirect(nextJSP);
			}
		}
	}
	
	private String getSearchQuery(HttpServletRequest request, IErpDondathangNppList obj)
	{
		Utility util = new Utility();
		
		String query = 
						" select a.PK_SEQ, a.trangthai, a.ngaydonhang, c.ten as nppTEN, b.ten as khoTEN, NV.TEN as nguoitao, b.ten as khonhan, a.NGAYSUA, a.NGAYTAO, NV2.TEN as nguoisua, a.NPP_DACHOT,isnull(a.isKm,0) as isKm,isnull(a.isdhkhac,0) as isdhkhac  " +
						"			,a.sotienthu  as tongtien  "+
						" from ERP_Dondathang a inner join NHAPHANPHOI c on a.NPP_FK = c.pk_seq " +
						"	left join ERP_KHOTT b on a.kho_fk = b.pk_seq  " +
						"	left join KHO d on a.kho_fk = d.pk_seq  " +
						" inner join NHANVIEN nv on a.NGUOITAO = nv.PK_SEQ   " +
						" inner join NHANVIEN nv2 on a.NGUOISUA = nv2.PK_SEQ where a.pk_seq > 0 ";
		
		String tungay = util.antiSQLInspection(request.getParameter("tungay"));
		if(tungay == null)
			tungay = "";
		obj.setTungay(tungay);
		
		String denngay = util.antiSQLInspection(request.getParameter("denngay"));
		if(denngay == null)
			denngay = "";
		obj.setDenngay(denngay);
		
		String trangthai =util.antiSQLInspection( request.getParameter("trangthai"));
		if(trangthai == null)
			trangthai = "";
		obj.setTrangthai(trangthai);
		
		String nppId = util.antiSQLInspection(request.getParameter("nppId"));
		if(nppId == null)
			nppId = "";
		obj.setNppId(nppId);
		
		String maddh = util.antiSQLInspection(request.getParameter("ddh"));
		if (maddh == null)
			maddh = "";
		obj.setMaDDH(maddh);
		
		String iskm = util.antiSQLInspection(request.getParameter("iskm")==null?"0":request.getParameter("iskm"));
		obj.setIsKm(iskm);

		
		if(tungay.length() > 0)
			query += " and a.ngaydonhang >= '" + tungay + "'";
		
		
		if(iskm.length() > 0)
			query += " and isnull(a.isdhkhac,0)  = '" + iskm + "' ";
		
		if(denngay.length() > 0)
			query += " and a.ngaydonhang <= '" + denngay + "'";
		
		if(trangthai.length() > 0){
			if(trangthai.equals("0"))
				query += " and (a.TrangThai = '0' and a.NPP_DACHOT = '0')";
			else if(trangthai.equals("1"))
				query += " and ((a.TrangThai = '1' and a.NPP_DACHOT = '1') or (a.TrangThai = '0' and a.NPP_DACHOT = '1'))";
			else
				query += " and a.TrangThai = '"+trangthai+"'";
		}
		
		if(nppId.length() > 0){
			query += " and a.NPP_FK= '" + nppId + "'";
		}
		if (maddh.length() > 0) {
			query += " and a.PK_SEQ like  '%" + maddh + "%'";
		}
		
		//System.out.println("Query: "+query);
		//System.out.println("\n"+trangthai+"\n");
		//System.out.println("NPP: "+nppId);
		return query;
	}
	
	public String getDateTime()
	{
		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		Date date = new Date();
		return dateFormat.format(date);	
	}
	
	
}
