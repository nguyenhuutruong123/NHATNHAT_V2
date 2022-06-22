package geso.dms.center.servlets.dondathang;

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

public class ErpDonhangnoiboSvl extends HttpServlet 
{
	private static final long serialVersionUID = 1L;
	
	public ErpDonhangnoiboSvl() {
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
						
						System.out.println("[TyLe]"+TyLe+"[QuyCach]"+QuyCach+"[DonGia]"+DonGia+"[TrongLuong]"+TrongLuong+"[TheTich]"+TheTich);
						
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
					System.out.println("[ErpDondathangSvl]___[Chot]___"+msg);
					obj.setMsg(msg);
				}
			}
			
			obj.setUserId(userId);
			obj.init("");
			
			session.setAttribute("obj", obj);
			
			String nextJSP = request.getContextPath() + "/pages/Center/ErpDonDatHang.jsp";
			if(loaidonhang.equals("4"))
				nextJSP = request.getContextPath() + "/pages/Center/ErpDonDatHangKhac.jsp";
			else if(loaidonhang.equals("2"))
				nextJSP = request.getContextPath() + "/pages/Center/ErpDonHangKMTichLuy.jsp";
			else if(loaidonhang.equals("1"))
				nextJSP = request.getContextPath() + "/pages/Center/ErpDonHangKMUngHang.jsp";
			else if(loaidonhang.equals("3"))
				nextJSP = request.getContextPath() + "/pages/Center/ErpDonHangTrungBay.jsp";
			
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
			if(loaidonhang==4)
			{
				msg=TaoDonHang(lsxId,userId,db);
				if(msg.length()>0)
				{
					db.getConnection().rollback();
					return msg;
				}
				/*	String ckId=" (select pk_seq From Erp_ChuyenKho Where  ddh_fk ="+lsxId+") ";
				msg=TaoNhapHang(ckId, userId,db);
				if(msg.length()>0)
				{
					db.getConnection().rollback();
					return msg;
				}*/
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
	
	private String TaoDonHang(String id,String userId,dbutils db) throws SQLException
	{
		String msg="";
		
		String	query = "select isnull(a.LoaiDonHang,0) as LoaiDonHang,b.loaiNPP, a.tructhuoc_fk, a.kho_fk, a.npp_fk, a.ngaydenghi, a.kbh_fk, a.kho_fk, a.dvkd_fk ,isnull(a.iskm,0) as isKm " +
				"from ERP_Dondathang a inner join NHAPHANPHOI b on a.npp_fk = b.pk_seq " +
				"where a.pk_seq = '" + id + "' ";
		ResultSet rs = db.get(query);
		
		System.out.println("TaoDonHang"+query);
		
		String loaiNPP = "";
		String tructhuoc_fk = "";
		String kho_fk = "";
		String LoaiDonHang = "";
		String isKm ="";
		
		if(rs.next())
		{
			loaiNPP = rs.getString("loaiNPP");
			tructhuoc_fk = rs.getString("tructhuoc_fk");
			kho_fk = rs.getString("kho_fk");
			LoaiDonHang= rs.getString("LoaiDonHang");
			isKm= rs.getString("isKm");
		}
		
		query = "select sp.PK_SEQ, sp.TEN, SUM(dathang.soluong) as soluongXUAT, ISNULL( ( select AVAILABLE from ERP_KHOTT_SANPHAM where khott_fk = '" + kho_fk + "' and sanpham_fk = sp.PK_SEQ ), 0) as tonkho " +
				"from    " +
				"(    " +
				"		select a.sanpham_fk, b.DVDL_FK as dvCHUAN,    " +
				"				case when a.dvdl_fk IS null then a.soluong     " +
				"					 when a.dvdl_fk = b.DVDL_FK then a.soluong    " +
				"					 else  a.soluong * ( select SOLUONG1 from QUYCACH where sanpham_fk = a.sanpham_fk and DVDL2_FK = a.dvdl_fk )     " +
				"									 / ( select SOLUONG2 from QUYCACH where sanpham_fk = a.sanpham_fk and DVDL2_FK = a.dvdl_fk )	 end as soluong, 0 as loai, ' ' as scheme   " +
				"		from ERP_DONDATHANG_SANPHAM a inner join SANPHAM b on a.sanpham_fk = b.PK_SEQ    " +
				"		where a.dondathang_fk in ( '" + id + "' )   " +
				"	union ALL  " +
				"		select b.PK_SEQ, b.DVDL_FK as dvCHUAN, a.soluong, 1 as loai, c.SCHEME   " +
				"		from ERP_DONDATHANG_CTKM_TRAKM a inner join SANPHAM b on a.SPMA = b.MA   " +
				"				inner join CTKHUYENMAI c on a.CTKMID = c.PK_SEQ   " +
				"		where a.DONDATHANGID in ( '" + id + "' )    " +
				")    " +
				"dathang inner join SANPHAM sp on dathang.sanpham_fk = sp.PK_SEQ  " +
				"group by sp.PK_SEQ, sp.TEN " +
				"having  SUM(dathang.soluong) > ISNULL( ( select AVAILABLE from ERP_KHOTT_SANPHAM where khott_fk = '" + kho_fk + "' and sanpham_fk = sp.PK_SEQ ), 0) " ;
		
		System.out.println("--CHECK TON KHO: " + query);
		
		ResultSet rsCHECK = db.get(query);
		{
			while(rsCHECK.next())
			{
				msg = "Sản phẩm ( " + rsCHECK.getString("TEN") + " ) với số lượng yêu cầu ( "+ rsCHECK.getString("soluongXUAT")+ " ) không đủ tồn kho ( "+ rsCHECK.getString("tonkho")+ " ). Vui lòng kiểm tra lại.";
				rsCHECK.close();
				return msg;
			}
			rsCHECK.close();
		}
		
		query = " insert ERP_CHUYENKHO(ngaychuyen, ghichu, trangthai, khoxuat_fk, kbh_fk, npp_fk, ddh_fk, ngaytao, nguoitao, ngaysua, nguoisua,tructhuoc_fk,LoaiDonHang,isKm) " +
				" values('"+ rs.getString("ngaydenghi")+ "', N'Đơn đặt hàng khác', '0', '"+ rs.getString("kho_fk")+ "', '"+ rs.getString("kbh_fk")+ "', "+ rs.getString("npp_fk")+ ", '"+ id+ "', '"+ getDateTime()+ "', '"+ userId+ "', '"+ getDateTime()+ "', '"+ userId+ "','"+tructhuoc_fk+"','"+LoaiDonHang+"','"+isKm+"' )";
		
		System.out.println("1.Insert CK: " + query);
		if(!db.update(query))
		{
			msg = "Lỗi khi chốt: " + query;
			rs.close();
			return msg;
		}
		
		String ckId="";
		
		query = "select scope_identity() as pxkId";
		rs = db.get(query);
		rs.next();
		ckId = rs.getString("pxkId");
		rs.close();
		
		query = "insert ERP_CHUYENKHO_SANPHAM( chuyenkho_fk, SANPHAM_FK, soluongchuyen, dongia, dvdl_fk ) " +
				"select "+ckId+", sanpham_fk, soluong, dongia, DVDL_FK " +
				"from ERP_DONDATHANG_SANPHAM where dondathang_fk = '" + id + "' ";
		
		System.out.println("3.Insert CK - SP: " + query);
		if(!db.update(query))
		{
			msg = "Khong the tao moi ERP_CHUYENKHO_SANPHAM: " + query;
			rs.close();
			return msg;
		}
		
		
		query = "select sp.PK_SEQ, sp.TEN, LOAI, SCHEME, SUM(dathang.soluong) as soluongXUAT " +
				"from    " +
				"(    " +
				"		select a.sanpham_fk, b.DVDL_FK as dvCHUAN,    " +
				"				case when a.dvdl_fk IS null then a.soluong     " +
				"					 when a.dvdl_fk = b.DVDL_FK then a.soluong    " +
				"					 else  a.soluong * ( select SOLUONG1 from QUYCACH where sanpham_fk = a.sanpham_fk and DVDL2_FK = a.dvdl_fk )     " +
				"									 / ( select SOLUONG2 from QUYCACH where sanpham_fk = a.sanpham_fk and DVDL2_FK = a.dvdl_fk )	 end as soluong, 0 as loai, ' ' as scheme   " +
				"		from ERP_DONDATHANG_SANPHAM a inner join SANPHAM b on a.sanpham_fk = b.PK_SEQ    " +
				"		where a.dondathang_fk in ( '" + id + "' )   " +
				"	union ALL  " +
				"		select b.PK_SEQ, b.DVDL_FK as dvCHUAN, a.soluong, 1 as loai, c.SCHEME   " +
				"		from ERP_DONDATHANG_CTKM_TRAKM a inner join SANPHAM b on a.SPMA = b.MA   " +
				"				inner join CTKHUYENMAI c on a.CTKMID = c.PK_SEQ   " +
				"		where a.DONDATHANGID in ( '" + id + "' )    " +
				")    " +
				"dathang inner join SANPHAM sp on dathang.sanpham_fk = sp.PK_SEQ  " +
				"group by sp.PK_SEQ, sp.TEN, LOAI, SCHEME ";
		System.out.println("--CHECK KHO CHI TIET: " + query);
		rsCHECK = db.get(query);
		{
			while(rsCHECK.next())
			{
				String sanpham_fk = rsCHECK.getString("PK_SEQ");
				String LOAI = rsCHECK.getString("LOAI");
				String SCHEME = rsCHECK.getString("SCHEME");
				double soLUONG = rsCHECK.getDouble("soluongXUAT");
				
				query = "Update ERP_KHOTT_SANPHAM set booked = booked + '" + soLUONG + "', AVAILABLE = AVAILABLE - '" + soLUONG + "' " +
						"where KHOTT_FK = '" + kho_fk + "' and SANPHAM_FK = '" + sanpham_fk + "'  ";
				if(!db.update(query))
				{
					msg = "Khong the cap nhat ERP_KHOTT_SANPHAM: " + query;
					
					return msg;
				}
				
				//CAP NHAT KHO CHI TIET
				query = "select AVAILABLE, SOLO from ERP_KHOTT_SP_CHITIET " +
						"where KHOTT_FK = '" + kho_fk + "'  and SANPHAM_FK = '" + sanpham_fk + "' order by ngayhethan asc ";
				
				ResultSet rsTK = db.get(query);
				double avai = 0;
				double totalXUAT = 0;
				while(rsTK.next())
				{
					double soluongCT = 0;
					String solo = rsTK.getString("SOLO");
					
					avai = rsTK.getDouble("AVAILABLE");
					totalXUAT += avai;
					
					if(totalXUAT <= soLUONG)
					{
						soluongCT = avai;
						
						query = "insert ERP_CHUYENKHO_SANPHAM_CHITIET( chuyenkho_fk, SANPHAM_FK, solo, soluong, loai, scheme ) " +
								"select "+ckId+", '" + sanpham_fk + "', N'" + solo + "', '" + soluongCT + "', '" + LOAI + "', '" + SCHEME + "' ";
						
						System.out.println("1.2.Insert CK - SP - CT: " + query);
						if(!db.update(query))
						{
							msg = "Khong the tao moi ERP_CHUYENKHO_SANPHAM_CHITIET: " + query;
							
							return msg;
						}
						
						
						query = "Update ERP_KHOTT_SP_CHITIET set booked = booked + '" + soluongCT + "', AVAILABLE = AVAILABLE - '" + soluongCT + "' " +
								"where KHOTT_FK = '" + kho_fk + "' and SOLO = '" + solo + "' and SANPHAM_FK = '" + sanpham_fk + "'  ";
						if(!db.update(query))
						{
							msg = "Khong the cap nhat ERP_KHOTT_SP_CHITIET: " + query;
							
							return msg;
						}
						
					}
					else
					{
						soluongCT = soLUONG - ( totalXUAT - avai );
						
						query = "insert ERP_CHUYENKHO_SANPHAM_CHITIET( chuyenkho_fk, SANPHAM_FK, solo, soluong, loai, scheme ) " +
								"select "+ckId+", '" + sanpham_fk + "', N'" + solo + "', '" + soluongCT + "', '" + LOAI + "', '" + SCHEME + "' ";
						System.out.println("1.2.Insert CK - SP - CT: " + query);
						if(!db.update(query))
						{
							msg = "Khong the tao moi ERP_CHUYENKHO_SANPHAM_CHITIET: " + query;
							
							return msg;
						}
						
						query = "Update ERP_KHOTT_SP_CHITIET set booked = booked + '" + soluongCT + "', AVAILABLE = AVAILABLE - '" + soluongCT + "' " +
								"where KHOTT_FK = '" + kho_fk + "' and SOLO = '" + solo + "' and SANPHAM_FK = '" + sanpham_fk + "'  ";
						if(!db.update(query))
						{
							msg = "Khong the cap nhat ERP_KHOTT_SP_CHITIET: " + query;
							
							return msg;
						}
						
						break;
					}
				}
				rsTK.close();
				
			}
			rsCHECK.close();
		}
		return msg;	
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
			
			String nextJSP = request.getContextPath() + "/pages/Center/ErpDonDatHangNew.jsp";
			if(loaidonhang.equals("4"))
				nextJSP = request.getContextPath() + "/pages/Center/ErpDonDatHangKhacNew.jsp";
			else if(loaidonhang.equals("2"))
				nextJSP = request.getContextPath() + "/pages/Center/ErpDonHangKMTichLuyNew.jsp";
			else if(loaidonhang.equals("1"))
				nextJSP = request.getContextPath() + "/pages/Center/ErpDonHangKMUngHangNew.jsp";
			else if(loaidonhang.equals("3"))
				nextJSP = request.getContextPath() + "/pages/Center/ErpDonHangTrungBayNew.jsp";
			
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
				
				String nextJSP = request.getContextPath() + "/pages/Center/ErpDonDatHang.jsp";
				if(loaidonhang.equals("4"))
					nextJSP = request.getContextPath() + "/pages/Center/ErpDonDatHangKhac.jsp";
				else if(loaidonhang.equals("2"))
					nextJSP = request.getContextPath() + "/pages/Center/ErpDonHangKMTichLuy.jsp";
				else if(loaidonhang.equals("1"))
					nextJSP = request.getContextPath() + "/pages/Center/ErpDonHangKMUngHang.jsp";
				else if(loaidonhang.equals("3"))
					nextJSP = request.getContextPath() + "/pages/Center/ErpDonHangTrungBay.jsp";
				
				response.sendRedirect(nextJSP);
			}
			else
			{
				String search = getSearchQuery(request, obj);
				obj.setUserId(userId);
				obj.init(search);
				
				
				session.setAttribute("obj", obj);  	
				session.setAttribute("userId", userId);
				
				String nextJSP = request.getContextPath() + "/pages/Center/ErpDonDatHang.jsp";
				if(loaidonhang.equals("4"))
					nextJSP = request.getContextPath() + "/pages/Center/ErpDonDatHangKhac.jsp";
				else if(loaidonhang.equals("2"))
					nextJSP = request.getContextPath() + "/pages/Center/ErpDonHangKMTichLuy.jsp";
				else if(loaidonhang.equals("1"))
					nextJSP = request.getContextPath() + "/pages/Center/ErpDonHangKMUngHang.jsp";
				else if(loaidonhang.equals("3"))
					nextJSP = request.getContextPath() + "/pages/Center/ErpDonHangTrungBay.jsp";
				
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
