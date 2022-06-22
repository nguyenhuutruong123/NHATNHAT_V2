package geso.dms.distributor.servlets.chuyenkho;

import geso.dms.distributor.db.sql.dbutils;
import geso.dms.center.util.Utility;
import geso.dms.distributor.beans.chuyenkho.IErpChuyenKho;
import geso.dms.distributor.beans.chuyenkho.IErpChuyenKhoList;
import geso.dms.distributor.beans.chuyenkho.imp.ErpChuyenKho;
import geso.dms.distributor.beans.chuyenkho.imp.ErpChuyenKhoList;

import java.io.IOException;
import java.sql.ResultSet;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/ErpChuyenKhoSvl")
public class ErpChuyenKhoSvl extends HttpServlet
{
	private static final long serialVersionUID = 1L;
	
	public ErpChuyenKhoSvl()
	{
		super();
		
	}
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
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
		String userId = (String) session.getAttribute("userId");  
		String userTen = (String) session.getAttribute("userTen");  	
		String sum = (String) session.getAttribute("sum");
		geso.dms.center.util.Utility cutil = (geso.dms.center.util.Utility) session.getAttribute("util");
	
		final HttpServletResponse response2=response;
		
		if(!cutil.check(userId, userTen, sum)){
			response.sendRedirect(request.getContextPath() + "/redirect.jsp");
		}else{
			IErpChuyenKhoList obj;
			request.setCharacterEncoding("UTF-8");
			response.setCharacterEncoding("UTF-8");
			response.setContentType("text/html; charset=UTF-8"); 
			
			Utility util = new Utility();
			
			String querystring = request.getQueryString();
			userId = util.getUserId(querystring);
			
			if (userId.length() == 0)
				userId = util.antiSQLInspection(request.getParameter("userId"));
			
			String action = util.getAction(querystring);
			
			String lsxId = util.getId(querystring);
			obj = new ErpChuyenKhoList();
			obj.setUserId(userId);
			
			String type = request.getParameter("type");
			if (type == null)
				type = "";
			obj.setType(type);
			
			if (action.equals("delete"))
			{
				String msg = this.DeleteChuyenKho(lsxId, userId);
				obj.setMsg(msg);
			} else if (action.equals("chot"))
			{
				String msg = this.Chot(lsxId, userId);
				obj.setMsg(msg);
			} else if (action.equals("UnChot"))
			{
				String msg = this.UnChot(lsxId, userId);
				obj.setMsg(msg);
			}
			
			obj.init("");
			session.setAttribute("obj", obj);
			
		
			
			String nextJSP = request.getContextPath() + "/pages/Distributor/ErpChuyenKho.jsp";
			response.sendRedirect(nextJSP);
		}
	}
	
	private String UnChot(String lsxId, String userId)
	{
		dbutils db = new dbutils();
		String msg = "";
		
		Utility util = new Utility();
	
		msg= util.Check_Huy_NghiepVu_KhoaSo("ERP_ChuyenKho", lsxId, "NgayChuyen",util.getIdNhapp(userId), db);
		if(msg.length()>0)
			return msg;

		try
		{
			db.getConnection().setAutoCommit(false);
			
			String query = "select npp_fk, tructhuoc_fk,ngaychuyen from ERP_CHUYENKHO where pk_seq='" + lsxId + "' and TrangThai=1";
			String npp_dat_fk = "";
			String tructhuoc_fk = "";
			String ngaychuyen="";
			ResultSet rs = db.get(query);
			
			while (rs.next())
			{
				npp_dat_fk = rs.getString("npp_fk");
				tructhuoc_fk = rs.getString("tructhuoc_fk");
				ngaychuyen =rs.getString("ngaychuyen");
				
			}
			rs.close();
			
			int SoDong = 0;
			if (!npp_dat_fk.equals("1"))
			{
				query = "select count(*) as SoDong from NHAPHANG where CHUYENKHO_FK ='" + lsxId + "' and TRANGTHAI=1 and npp_fk is not null ";
				rs = db.get(query);
				while (rs.next())
				{
					SoDong = rs.getInt("SoDong");
				}
				if (rs != null)
					rs.close();
				
				if (SoDong > 0)
				{
					msg = "Không thể hủy Xuất chuyển nội bộ khi đã có nhận hàng !";
					db.getConnection().rollback();
					return msg;
				}
				
				query = "delete from nhaphang_sp where nhaphang_fk in (select pk_Seq from nhaphang  where CHUYENKHO_FK ='" + lsxId + "' and TRANGTHAI=0 and CHUYENKHO_FK is not null  ) ";
				if (!db.update(query))
				{
					msg = "2.Khong the xoa: " + query;
					db.getConnection().rollback();
					return msg;
				}
				query = "delete from nhaphang  where CHUYENKHO_FK ='" + lsxId + "' and TRANGTHAI=0 and CHUYENKHO_FK is not null   ";
				if (db.updateReturnInt(query) != 1)
				{
					msg = "2.Khong the xoa: " + query;
					db.getConnection().rollback();
					return msg;
				}
			}
			else
			{
				query = "select count(*) as SoDong from Erp_NhapKho where CHUYENKHO_FK ='" + lsxId + "' and TRANGTHAI=1 and CHUYENKHo_FK is not null ";
				rs = db.get(query);
				while (rs.next())
				{
					SoDong = rs.getInt("SoDong");
				}
				if (rs != null)
					rs.close();
				
				if (SoDong > 0)
				{
					msg = "Không thể hủy Xuất chuyển nội bộ khi đã có nhận hàng !";
					db.getConnection().rollback();
					return msg;
				}
				
				query = "delete from ERP_NHAPKHO_SANPHAM where nhapkho_fk in (select pk_Seq from Erp_NhapKho  where CHUYENKHO_FK ='" + lsxId + "' and TRANGTHAI=0 and CHUYENKHO_FK is not null  ) ";
				if (!db.update(query))
				{
					msg = "2.Khong the xoa: " + query;
					db.getConnection().rollback();
					return msg;
				}
				query = "delete from Erp_NhapKho  where CHUYENKHO_FK ='" + lsxId + "' and TRANGTHAI=0 and CHUYENKHO_FK is not null   ";
				if (db.updateReturnInt(query) != 1)
				{
					msg = "2.Khong the xoa: " + query;
					db.getConnection().rollback();
					return msg;
				}
			}
			
			// CHECK DUNG CHUNG KENH HAY KHONG
			String dungchungKENH = "0";
			String sqlKENH = "";
			
			query =  "select isnull(dungchungkenh, 0) as dungchungkenh from NHAPHANPHOI " + 
					"where pk_seq = ( select tructhuoc_fk from ERP_CHUYENKHO where pk_seq = '" + lsxId + "' )";
			ResultSet rsKENH = db.get(query);
			if (rsKENH != null)
			{
				if (rsKENH.next())
				{
					dungchungKENH = rsKENH.getString("dungchungkenh");
				}
				rsKENH.close();
			}
			
			if (dungchungKENH.equals("1"))
				sqlKENH = " 100025 as kbh_fk "; // LAY KENH OTC
			else
				sqlKENH = " A.kbh_fk ";
			
			query = 
			" 	select count(*)   as SoDong " + 
			" 	from ERP_CHUYENKHO a inner join ERP_CHUYENKHO_SANPHAM_CHITIET b on a.pk_seq = b.chuyenkho_fk " + 
			" 	where chuyenkho_fk = '" + lsxId + "' ";
			
			System.out.println("::::_01" + query);
			SoDong = 0;
			rs = db.get(query);
			while (rs.next())
			{
				SoDong = rs.getInt("SoDong");
			}
			if (rs != null)
				rs.close();
			
			/*// GIAM KHO XUAT
			query = 
					" update kho set kho.SOLUONG = kho.SOLUONG + CT.tongxuat, " + 
					" 			   kho.BOOKED = kho.BOOKED + CT.tongxuat " + " from " + 
					" ( " + 
					" 	select a.khoxuat_fk as kho_fk, a.tructhuoc_Fk as npp_fk, " + sqlKENH + ", b.sanpham_fk, b.solo, SUM(b.soluong) as tongxuat,b.NgayHetHan  " + 
					" 	from ERP_CHUYENKHO a inner join ERP_CHUYENKHO_SANPHAM_CHITIET b on a.pk_seq = b.chuyenkho_fk " + 
					" 	where chuyenkho_fk = '" + lsxId + "' " + 
					" 	group by a.khoxuat_fk, a.tructhuoc_Fk, a.kbh_fk, b.solo, b.sanpham_fk,b.NgayHetHan " + " ) " + 
					" CT inner join NHAPP_KHO_CHITIET kho on CT.kho_fk = kho.KHO_FK  " + 
					" 	and CT.sanpham_fk = kho.SANPHAM_FK and CT.solo = kho.SOLO and CT.NPP_FK = kho.npp_fk and CT.KBH_FK = kho.kbh_fk and kho.NgayHetHan=ct.NgayHetHan  ";
			System.out.println("---TANG KHO NGUOC LAI: " + query);
			if (db.updateReturnInt(query) != SoDong)
			{
				msg = "Không thể cập nhật NHAPP_KHO_CHITIET " + query;
				db.getConnection().rollback();
				return msg;
			}
			
			query = 
			" update kho set kho.SOLUONG = kho.SOLUONG + CT.tongxuat, " + 
			" 			   kho.BOOKED = kho.BOOKED + CT.tongxuat " + " from " + " ( " + 
			" 	select a.khoxuat_fk as kho_fk, a.tructhuoc_Fk as npp_fk, " + sqlKENH + " , b.sanpham_fk, SUM(b.soluong) as tongxuat  " + 
			" 	from ERP_CHUYENKHO a inner join ERP_CHUYENKHO_SANPHAM_CHITIET b on a.pk_seq = b.chuyenkho_fk " + 
			" 	where chuyenkho_fk = '" + lsxId + "' " + 
			" 	group by a.khoxuat_fk, a.tructhuoc_Fk, a.kbh_fk, b.sanpham_fk " + " ) " + 
			" CT inner join NHAPP_KHO kho on CT.kho_fk = kho.KHO_FK  " + 
			" 	and CT.sanpham_fk = kho.SANPHAM_FK and CT.NPP_FK = kho.npp_fk and CT.KBH_FK = kho.kbh_fk ";
			System.out.println("---TANG KHO NGUOC LAI 2: " + query);
			if (db.updateReturnInt(query) <= 0)
			{
				msg = "Không thể cập nhật NHAPP_KHO " + query;
				db.getConnection().rollback();
				return msg;
			}*/
			geso.dms.center.util.Utility util_kho = new geso.dms.center.util.Utility();
			
			query=	" 	select  a.khoxuat_fk as kho_fk, a.tructhuoc_Fk as npp_fk, " + sqlKENH + "   , b.sanpham_fk, b.solo, SUM(b.soluong) as tongxuat,b.NgayHetHan,b.ngaynhapkho  " + 
					" 	from ERP_CHUYENKHO a inner join ERP_CHUYENKHO_SANPHAM_CHITIET b on a.pk_seq = b.chuyenkho_fk " + 
					" 	where chuyenkho_fk = '" + lsxId + "' " + 
					" 	group by a.khoxuat_fk, a.tructhuoc_Fk, a.kbh_fk, b.solo, b.sanpham_fk,b.NgayHetHan,b.ngaynhapkho ";
			
			ResultSet rssp = db.get(query);
			while (rssp.next()) {
				String _spid = rssp.getString("sanpham_fk");
				String _nppid = rssp.getString("NPP_FK");
				String _khoid = rssp.getString("kho_fk");
				String _kbhid = rssp.getString("KBH_FK");
				double _SOLUONG = rssp.getDouble("tongxuat");
				String _solo = rssp.getString("solo");
				String _ngaynhapkho = rssp.getString("ngaynhapkho");
				String _ngayhethan = rssp.getString("ngayhethan");

				 String msg1= util_kho.Update_NPP_Kho_Sp (ngaychuyen,
						 " 9448. donhang.java CHỐT  đơn hàng___" +lsxId , db, _khoid, _spid, _nppid,
						  _kbhid ,   _SOLUONG ,  _SOLUONG, 0,
						   0); // giảm booked, tăng avail lại
						 
						  if (msg1.length() > 0){ db.getConnection().rollback(); return "Error :" +
						   msg1; }
						  
						  
				    msg1= util_kho.Update_NPP_Kho_Sp_Chitiet(ngaychuyen,
				 " 9448. donhang.java CHỐT  đơn hàng___" +lsxId , db, _khoid, _spid, _nppid,
				  _kbhid, _solo, _ngayhethan, _ngaynhapkho,  _SOLUONG , _SOLUONG, 0,
				  0, 0); // giảm booked, tăng avail lại
				 
				  if (msg1.length() > 0){ db.getConnection().rollback(); 
				  return "Error :" +
				   msg1; }
			 
			}
			rssp.close();
			
			
					 
			query = "update ERP_CHUYENKHO set trangthai = '0' where pk_seq = '" + lsxId + "' and trangthai=1  ";
			if (db.updateReturnInt(query) != 1)
			{
				msg = "Phiếu chuyển kho này đã chốt rồi !" + query;
				db.getConnection().rollback();
				return msg;
			}
			
			msg= util.Check_Kho_Tong_VS_KhoCT(tructhuoc_fk, db);
			if(msg.length()>0)
			{
				db.getConnection().rollback();
				return msg;
			}

			
			db.getConnection().commit();
			db.getConnection().setAutoCommit(true);
		} catch (Exception e)
		{
			e.printStackTrace();
			db.update("rollback");
			return "Exception: " + e.getMessage();
		} finally
		{
			if (db != null)
				db.shutDown();
		}
		return "";
	}
	
	private String Chot(String id, String userId)
	{
		dbutils db = new dbutils();
		
		
		try
		{
			
			String msg = "";

			Utility util = new Utility();
			msg= util.Check_Huy_NghiepVu_KhoaSo("ERP_ChuyenKho", id, "NgayChuyen",util.getIdNhapp(userId), db);
			if(msg.length()>0)
				return msg;

			
			db.getConnection().setAutoCommit(false);
			
			// CHECK DUNG CHUNG KENH HAY KHONG
			String dungchungKENH = "0";
			String sqlKENH = "";
			
			String npp_dat_fk = "";
			String query = 
			" select count(*)   as SoDong,a.npp_fk ,c.DUNGCHUNGKENH " + 
			" 	from ERP_CHUYENKHO a inner join ERP_CHUYENKHO_SANPHAM_CHITIET b on a.pk_seq = b.chuyenkho_fk " + 
			" inner join NHAPHANPHOI c on c.PK_SEQ=a.tructhuoc_Fk " + 
			" 	where chuyenkho_fk = '" + id + "' " + 
			" group by a.npp_fk,c.DUNGCHUNGKENH ";
			
			System.out.println("::::_01" + query);
			int SoDong = 0;
			ResultSet rs = db.get(query);
			while (rs.next())
			{
				SoDong = rs.getInt("SoDong");
				npp_dat_fk = rs.getString("npp_fk");
				dungchungKENH = rs.getString("dungchungkenh");
			}
			if (rs != null)
				rs.close();
			
			if (dungchungKENH.equals("1"))
				sqlKENH = " 100025 as kbh_fk "; // LAY KENH OTC
			else
				sqlKENH = " A.kbh_fk ";
			
			// GIAM KHO XUAT
			query=" 	select a.ngaychuyen,b.ngaynhapkho,a.khoxuat_fk as kho_fk,a.TrucThuoc_FK as npp_fk, " + sqlKENH + " , b.sanpham_fk, b.solo, SUM(b.soluong) as tongxuat,NgayHetHan  " + 
			" 	from ERP_CHUYENKHO a inner join ERP_CHUYENKHO_SANPHAM_CHITIET b on a.pk_seq = b.chuyenkho_fk " + 
			" 	where chuyenkho_fk = '" + id + "' and a.TrangThai=0  " + 
			" 	group by a.khoxuat_fk, a.TrucThuoc_FK, a.kbh_fk, b.solo, b.sanpham_fk,NgayHetHan,a.ngaychuyen,b.ngaynhapkho "  ;
			
			ResultSet nkRs= db.get(query);
		 	while (nkRs.next())
		 	{
		 		String kho_fk=nkRs.getString("kho_fk");
				String npp_fk=nkRs.getString("npp_fk");	
				String kbh_fk=nkRs.getString("kbh_fk");
				String sanpham_fk=nkRs.getString("sanpham_fk");
				String solo=nkRs.getString("solo");
				Double tongxuat=nkRs.getDouble("tongxuat");
				String NgayHetHan=nkRs.getString("NgayHetHan");
				String ngaynhapkho=nkRs.getString("ngaynhapkho");
				String ngaychuyen=nkRs.getString("ngaychuyen");
				
				msg=util.Update_NPP_Kho_Sp_Chitiet(ngaychuyen, "Xuất chuyển kho khác - CHốt", db, kho_fk, sanpham_fk, npp_fk, kbh_fk, solo, NgayHetHan, ngaynhapkho, (-1)*tongxuat, (-1)*tongxuat, 0.0, 0.0, 0.0);
				if(msg.length()>0)
				{
					db.getConnection().rollback();
					System.out.println("loi nay" +msg);
					return msg;
				}
				msg=util.Update_NPP_Kho_Sp(ngaychuyen, "Xuất chuyển kho khác - CHốt", db, kho_fk, sanpham_fk, npp_fk, kbh_fk,(-1)*tongxuat,  (-1)*tongxuat, 0.0, 0.0);
				if(msg.length()>0)
				{
					db.getConnection().rollback();
					return msg;
				}
		 	
	
		 	}
			
			
//			query = 
//					" update kho set kho.SOLUONG = kho.SOLUONG - CT.tongxuat, " + 
//					" 			   kho.BOOKED = kho.BOOKED - CT.tongxuat " + " from " + 
//					" ( " + 
//					" 	select a.khoxuat_fk as kho_fk, a.tructhuoc_fk as npp_fk, " + sqlKENH + ", b.sanpham_fk, b.solo, SUM(b.soluong) as tongxuat,b.NgayHetHan  " + 
//					" 	from ERP_CHUYENKHO a inner join ERP_CHUYENKHO_SANPHAM_CHITIET b on a.pk_seq = b.chuyenkho_fk " + 
//					" 	where chuyenkho_fk = '" + id + "' " + 
//					" 	group by a.khoxuat_fk, a.tructhuoc_fk, a.kbh_fk, b.solo, b.sanpham_fk,b.NgayHetHan " + " ) " + 
//					" CT inner join NHAPP_KHO_CHITIET kho on CT.kho_fk = kho.KHO_FK  " + 
//					" 	and CT.sanpham_fk = kho.SANPHAM_FK and CT.solo = kho.SOLO and CT.NPP_FK = kho.npp_fk and CT.KBH_FK = kho.kbh_fk and kho.NgayHetHan=ct.NgayHetHan  ";
//			System.out.println("---TANG KHO NGUOC LAI: " + query);
//			if (db.updateReturnInt(query) != SoDong)
//			{
//				msg = "Không thể cập nhật NHAPP_KHO_CHITIET " + query;
//				db.getConnection().rollback();
//				return msg;
//			}
//			
//			query = 
//				" update kho set kho.SOLUONG = kho.SOLUONG - CT.tongxuat, " + 
//				" 			   kho.BOOKED = kho.BOOKED - CT.tongxuat " + " from " + 
//				" ( " + 
//				" 	select a.khoxuat_fk as kho_fk, a.tructhuoc_fk as npp_fk , " + sqlKENH + " , b.sanpham_fk, SUM(b.soluong) as tongxuat  " + 
//				" 	from ERP_CHUYENKHO a inner join ERP_CHUYENKHO_SANPHAM_CHITIET b on a.pk_seq = b.chuyenkho_fk " + 
//				" 	where chuyenkho_fk = '" + id + "' " + 
//				" 	group by a.khoxuat_fk, a.tructhuoc_fk, a.kbh_fk, b.sanpham_fk " + " ) " + 
//				" CT inner join NHAPP_KHO kho on CT.kho_fk = kho.KHO_FK  " + 
//				" 	and CT.sanpham_fk = kho.SANPHAM_FK and CT.NPP_FK = kho.npp_fk and CT.KBH_FK = kho.kbh_fk ";
//			System.out.println("---TANG KHO NGUOC LAI 2: " + query);
//			if (db.updateReturnInt(query) <= 0)
//			{
//				msg = "Không thể cập nhật NHAPP_KHO " + query;
//				System.out.println( "Không thể cập nhật NHAPP_KHO " + query);
//				db.getConnection().rollback();
//				return msg;
//			}
			
			if (!npp_dat_fk.equals("1"))
			{
				msg = TaoNhapHang_Npp(id, db, userId);
			} else
			{
				msg = TaoNhapKho_HO(id, userId, db);
			}
			
			if (msg.length() > 0)
			{
				msg += "Không thể tạo XUẤT KHO KHÁC";
				db.getConnection().rollback();
				return msg;
			}
			
			query = "update ERP_CHUYENKHO set trangthai = '1',Modified_Date=getdate(),NguoiSua='"+userId+"' where pk_seq = '" + id + "'  ";
			if (db.updateReturnInt(query) != 1)
			{
				msg = "Phiếu chuyển kho này đã chốt rồi !" + query;
				db.getConnection().rollback();
				return msg;
			}
			
			query = "update ERP_Dondathang set trangthai = '4',NgUOISua='"+userId+"' where pk_seq = ( select ddh_fk from ERP_CHUYENKHO where pk_seq = '" + id + "' ) ";
			if (!db.update(query))
			{
				msg = "1.Khong the xoa: " + query;
				db.getConnection().rollback();
				return msg;
			}
			
			
			System.out.println("___" + msg);
			
			db.getConnection().commit();
			db.getConnection().setAutoCommit(true);
		} catch (Exception e)
		{
			db.update("rollback");
			db.shutDown();
			return "Exception: " + e.getMessage();
		} finally
		{
			if (db != null)
				db.shutDown();
		}
		
		return "";
	}
	
	private String TaoNhapHang_Npp(String id, dbutils db, String userId)
	{
		String msg = "";
		try
		{
		// TAO NHAP HANG CHO DOI TUONG NHAP
			
			//RÀNG K CHO TẠO DOUBLE
		String query="Select count(*) as soDong from NHAPHANG nk inner join ERP_CHUYENKHO ck on nk.CHUYENKHO_FK=ck.pk_seq where CHUYENKHO_FK= "+id +" and nk.trangthai not in (2) ";
		System.out.println("query lay nhap kho " +query);
		ResultSet checkRs= db.get(query);
		{
			if(checkRs.next())
			{
				int count= checkRs.getInt("soDong");
				if(count>0)
				{
				System.out.println("DA VAO DAY");
				return "";
				}
			}
		}
		query = " insert NHAPHANG(NGAYNHAN, NGAYCHUNGTU, NPP_FK, NCC_FK, GSBH_FK, ASM_FK, BM_FK, DVKD_FK, KBH_FK, CHUYENKHO_FK, TRANGTHAI, NGUOITAO, NGAYTAO, NGUOISUA, NGAYSUA) " +
					"select distinct NgayChuyen, NgayChuyen, npp_fk,   " + 
					" 			( select top(1) NCC_FK from NHACUNGCAP_DVKD where PK_SEQ in ( select NCC_DVKD_FK from NHAPP_NHACC_DONVIKD where NPP_FK = a.NPP_FK ) ) as NCC_FK,  " + 
					"			( select top(1) GSBH_FK from NHAPP_GIAMSATBH where NPP_FK = a.npp_fk  ) as GSBH_FK,  " + 
					"			( select top(1) ASM_FK from ASM_KHUVUC where KHUVUC_FK in ( select KHUVUC_FK from NHAPHANPHOI where PK_SEQ = a.NPP_FK )) as ASM_FK,  " + 
					"			( select top(1) BM_FK from BM_CHINHANH where VUNG_FK in ( select VUNG_FK from KHUVUC where PK_SEQ in (  select KHUVUC_FK from NHAPHANPHOI where PK_SEQ = a.NPP_FK ) ) ) as BM_FK,  " + 
					" 	   '100001' as DVKD_FK, case when DDH_FK is not null then   (select KBH_FK from ERP_DONDATHANG  where PK_SEQ=ddh_fk) else  a.KBH_FK end as kbh_fk, '" + id + "', '0', '" + userId + "', '" + getDateTime() + "', '" + userId + "', '" + getDateTime() + "'  " + 
					" from ERP_CHUYENKHO a inner join ERP_CHUYENKHO_SANPHAM b on a.PK_SEQ = b.chuyenkho_fk  " + 
					" where a.PK_SEQ = '" + id + "' ";
		
		System.out.println("---INSERT NHAPHANG: " + query);
		if (!db.update(query))
		{
			msg = "Không tạo mới NHAPHANG " + query;
			return msg;
		}
		
		query = 
		" insert NHAPHANG_SP(NHAPHANG_FK, SANPHAM_FK, SOLUONG, soluongNHAN, DONGIA, CHIETKHAU, DVDL_FK, LOAI, SCHEME, SOLO, NGAYHETHAN,KHONHAN_FK) " + 
		" select ( select pk_seq from NHAPHANG where CHUYENKHO_FK = a.PK_SEQ  ),  " + 
		" 		b.sanpham_fk, b.soluong, NULL, 0 as dongia, 0 as chietkhau, c.DVDL_FK, 1 as LOAI, '' as SCHEME, b.solo, b.ngayhethan,100001 " + 
		" from ERP_CHUYENKHO a inner join ERP_CHUYENKHO_SANPHAM_CHITIET b on a.PK_SEQ = b.chuyenkho_fk " + 
		" 		inner join SANPHAM c on b.sanpham_fk = c.PK_SEQ   " + 
		" where a.PK_SEQ = '" + id + "' and b.soluong > 0 and a.TrangThai=0 ";
		System.out.println("---INSERT NHAPHANG - SP: " + query);
		if (!db.update(query))
		{
			msg = "Không tạo mới NHAPHANG_SP " + query;
			return msg;
		}
		
		query = 
				"insert NHAPHANG_DDH(nhaphang_fk, ddh_fk)  " + 
				"select ( select PK_SEQ from NHAPHANG where CHUYENKHO_FK = '" + id + "' ) as nhID, ddh_fk  " +
				"from ERP_CHUYENKHO where pk_seq = '" + id + "'";
		if (!db.update(query))
		{
			msg = "Không tạo mới NHAPHANG_DDH " + query;
			return msg;
		}
		}
		catch (Exception ex)
		{
			ex.printStackTrace();
			return "Xảy ra lỗi khi tự tạo nhận hàng ";
		}
		return msg;
	}
	
	private String TaoNhapKho_HO(String id, String userId, dbutils db)
	{
		String msg = "";
		String query="Select count(*) as soDong from ERP_NHAPKHO nk inner join ERP_CHUYENKHO ck on nk.ChuyenKhoNpp_fk=ck.pk_seq where nk.ChuyenKhoNpp_fk= "+id +" and nk.trangthai not in (2) ";
		System.out.println("query lay nhap kho " +query);
		
		try
		{
		ResultSet checkRs= db.get(query);
		
		{
			if(checkRs.next())
			{
				int count= checkRs.getInt("soDong");
				if(count>0)
				{
				System.out.println("DA VAO DAY");
				return "";
				}
			}
		}
		 query = "	insert into ERP_NHAPKHO(NgayNhap,KhoNhap_FK,TRANGTHAI,NGAYTAO,NGAYSUA,NGUOITAO,NGUOISUA,GHICHU,ChuyenKhoNpp_fk,SoChungTu ) " + "	select NgayChuyen,100001,0,'" + getDateTime() + "' as NgayTao,'" + getDateTime() + "' as nsua,NGUOITAO,NGUOISUA,N'CN chuyển HO',PK_SEQ,SoChungTu " + "	from ERP_CHUYENKHO where NPP_DAT_FK =1 and pk_Seq='" + id + "' ";
		if (!db.update(query))
		{
			msg = "Không tạo mới ERP_NHAPKHO " + query;
			return msg;
		}
		
		query = "insert ERP_NHAPKHO_SANPHAM( nhapkho_fk, SANPHAM_FK, DVDL_FK, soluong, gianhap, solo, ngaysanxuat, ngayhethan ) " + "select SCOPE_IDENTITY(),a.pk_Seq as spId,DVDL_FK,soluong as SoLuong,0 as GiaNhap,b.solo as SoLo,  " + "	b.ngayhethan as NgaySanXua,b.ngayhethan as NgayHetHan  " + "from SANPHAM a inner join ERP_CHUYENKHO_SANPHAM_CHITIET b on a.PK_SEQ=b.SANPHAM_FK " + " where b.chuyenkho_fk='" + id + "' ";
		
		System.out.println("_ERP_NHAPKHO_SANPHAM" + query);
		
		if (!db.update(query))
		{
			msg = "Không tạo mới ERP_NHAPKHO_SANPHAM " + query;
			return msg;
		}
		
		}
		catch (Exception e) {
			e.printStackTrace();
			// TODO: handle exception
			return "Đã xảy ra lỗi khi tạo đơn tự động";
		}
		return msg;
	}
	
	private String DeleteChuyenKho(String id, String userId)
	{
		dbutils db = new dbutils();
		String msg = "";
		Utility util = new Utility();
		msg= util.Check_Huy_NghiepVu_KhoaSo("ERP_ChuyenKho", id, "NgayChuyen", util.getIdNhapp(userId), db);
		if(msg.length()>0)
			return msg;

		try
		{
			db.getConnection().setAutoCommit(false);
			
			String dungchungKENH = "0";
			String sqlKENH = "100025";
			
			String query = 
					"select isnull(dungchungkenh, 0) as dungchungkenh, pk_seq from NHAPHANPHOI " + 
				  " where pk_seq = ( select tructhuoc_fk from ERP_CHUYENKHO where pk_seq = '" + id + "' )";
			ResultSet rsKENH = db.get(query);
			String tructhuoc_fk = "";
			if (rsKENH != null)
			{
				if (rsKENH.next())
				{
					dungchungKENH = rsKENH.getString("dungchungkenh");
					tructhuoc_fk = rsKENH.getString("pk_seq");
				}
				rsKENH.close();
			}
			
			if (dungchungKENH.equals("1"))
				sqlKENH = " 100025 as kbh_fk "; // LAY KENH OTC
			else
				sqlKENH = " A.kbh_fk ";
			
			query = 
					" 	select count(*)   as SoDong " + 
					" 	from ERP_CHUYENKHO a inner join ERP_CHUYENKHO_SANPHAM_CHITIET b on a.pk_seq = b.chuyenkho_fk " + 
					" 	where chuyenkho_fk = '" + id + "' and a.TrangThai=0 ";
			
			System.out.println("::::_01" + query);
			int SoDong = 0;
			ResultSet rs = db.get(query);
			while (rs.next())
			{
				SoDong = rs.getInt("SoDong");
			}
			if (rs != null)
				rs.close();
			
			// TANG KHO NGUOC LAI
			query=" 	select a.ngaychuyen,b.ngaynhapkho,a.khoxuat_fk as kho_fk,a.TrucThuoc_FK as npp_fk, " + sqlKENH + " , b.sanpham_fk, b.solo, SUM(b.soluong) as tongxuat,NgayHetHan  " + 
			" 	from ERP_CHUYENKHO a inner join ERP_CHUYENKHO_SANPHAM_CHITIET b on a.pk_seq = b.chuyenkho_fk " + 
			" 	where chuyenkho_fk = '" + id + "' and a.TrangThai=0  " + 
			" 	group by a.khoxuat_fk, a.TrucThuoc_FK, a.kbh_fk, b.solo, b.sanpham_fk,NgayHetHan,a.ngaychuyen,b.ngaynhapkho "  ;
			
			ResultSet nkRs= db.get(query);
		 	while (nkRs.next())
		 	{
		 		String kho_fk=nkRs.getString("kho_fk");
				String npp_fk=nkRs.getString("npp_fk");	
				String kbh_fk=nkRs.getString("kbh_fk");
				String sanpham_fk=nkRs.getString("sanpham_fk");
				String solo=nkRs.getString("solo");
				Double tongxuat=nkRs.getDouble("tongxuat");
				String NgayHetHan=nkRs.getString("NgayHetHan");
				String ngaynhapkho=nkRs.getString("ngaynhapkho");
				String ngaychuyen=nkRs.getString("ngaychuyen");
				
				msg=util.Update_NPP_Kho_Sp_Chitiet(ngaychuyen, "Xuất chuyển kho khác - Xoá", db, kho_fk, sanpham_fk, npp_fk, kbh_fk, solo, NgayHetHan, ngaynhapkho, 0.0, (-1)*tongxuat, tongxuat, tongxuat, 0.0);
				if(msg.length()>0)
				{
					db.getConnection().rollback();
					System.out.println("loi nay" +msg);
					return msg;
				}
				msg=util.Update_NPP_Kho_Sp(ngaychuyen, "Xuất chuyển kho khác - Xoá", db, kho_fk, sanpham_fk, npp_fk, kbh_fk, 0.0,  (-1)*tongxuat, tongxuat, 0.0);
				if(msg.length()>0)
				{
					db.getConnection().rollback();
					return msg;
				}
		 	
	
		 		}
			
//			query = 
//			" update kho set kho.AVAILABLE = kho.AVAILABLE + CT.tongxuat, " + 
//			" 			   kho.BOOKED = kho.BOOKED - CT.tongxuat " + 
//			" from " + 
//			" ( " + 
//			" 	select a.khoxuat_fk as kho_fk,a.TrucThuoc_FK as npp_fk, " + sqlKENH + " , b.sanpham_fk, b.solo, SUM(b.soluong) as tongxuat,NgayHetHan  " + 
//			" 	from ERP_CHUYENKHO a inner join ERP_CHUYENKHO_SANPHAM_CHITIET b on a.pk_seq = b.chuyenkho_fk " + 
//			" 	where chuyenkho_fk = '" + id + "' and a.TrangThai=0  " + 
//			" 	group by a.khoxuat_fk, a.TrucThuoc_FK, a.kbh_fk, b.solo, b.sanpham_fk,NgayHetHan " + " ) " + 
//			" CT inner join NHAPP_KHO_CHITIET kho on CT.kho_fk = kho.KHO_FK  " + 
//			" 	and CT.sanpham_fk = kho.SANPHAM_FK and CT.solo = kho.SOLO and CT.NPP_FK = kho.npp_fk and CT.KBH_FK = kho.kbh_fk and ct.NgayHetHan=kho.NgayHetHan ";
//			
//			System.out.println("---TANG KHO NGUOC LAI: " + query);
//			
//			if (db.updateReturnInt(query) != SoDong)
//			{
//				msg = "Không thể cập nhật NHAPP_KHO_CHITIET " + query;
//				db.getConnection().rollback();
//				return msg;
//			}
//			
//			query = 
//					" update kho set kho.AVAILABLE = kho.AVAILABLE + CT.tongxuat, " + 
//					" 			   kho.BOOKED = kho.BOOKED - CT.tongxuat " + 
//					" from " + " ( " + 
//					" 	select a.khoxuat_fk as kho_fk, a.TrucThuoc_FK as npp_fk, " + sqlKENH + "  , b.sanpham_fk, SUM(b.soluong) as tongxuat  " + 
//					" 	from ERP_CHUYENKHO a inner join ERP_CHUYENKHO_SANPHAM_CHITIET b on a.pk_seq = b.chuyenkho_fk " + 
//					" 	where chuyenkho_fk = '" + id + "'  and a.TrangThai=0  " + 
//					" 	group by a.khoxuat_fk, a.TrucThuoc_FK, a.kbh_fk, b.sanpham_fk " + " ) " + 
//					" CT inner join NHAPP_KHO kho on CT.kho_fk = kho.KHO_FK  " + 
//					" 	and CT.sanpham_fk = kho.SANPHAM_FK and CT.NPP_FK = kho.npp_fk and CT.KBH_FK = kho.kbh_fk ";
//			System.out.println("---TANG KHO NGUOC LAI 2: " + query);
//			if (db.updateReturnInt(query) <= 0)
//			{
//				msg = "Không thể cập nhật NHAPP_KHO " + query;
//				db.getConnection().rollback();
//				return msg;
//			}
			
			// PHAI HUY DON DUOI chi nhánh trực thuộc đặt lên nếu có (trường hợp không
			// phải tự tao mới)
			query = "update ERP_Dondathang set trangthai = '3', GHICHU = N'Cấp trên không duyệt' where pk_seq = ( select ddh_Fk from ERP_CHUYENKHO where pk_seq = '" + id + "' ) ";
			if (!db.update(query))
			{
				msg = "1.Khong the xoa: " + query;
				db.getConnection().rollback();
				return msg;
			}
			
			query = "update ERP_CHUYENKHO set trangthai = '2', nguoisua = '" + userId + "', ngaysua = '" + getDateTime() + "',Modified_Date=getdate() where pk_seq = '" + id + "' and trangthai =0 ";
			if (db.updateReturnInt(query) != 1)
			{
				msg = "2.Khong the xoa: " + query;
				db.getConnection().rollback();
				return msg;
			}
			

			msg= util.Check_Kho_Tong_VS_KhoCT(tructhuoc_fk, db);
			if(msg.length()>0)
			{
				db.getConnection().rollback();
				return msg;
			}

			
			db.getConnection().commit();
			db.getConnection().setAutoCommit(true);
		} catch (Exception e)
		{
			db.update("rollback");
			db.shutDown();
			return "Exception: " + e.getMessage();
		} finally
		{
			if (db != null)
				db.shutDown();
		}
		
		return "";
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
		}else{
			request.setCharacterEncoding("UTF-8");
			response.setCharacterEncoding("UTF-8");
			response.setContentType("text/html; charset=UTF-8");
			
			String action = request.getParameter("action");
			if (action == null)
			{
				action = "";
			}
			
			IErpChuyenKhoList obj = new ErpChuyenKhoList();
			
			Utility util = new Utility();
			 
			userId = util.antiSQLInspection(request.getParameter("userId"));
			obj.setUserId(userId);
			
			if (action.equals("Tao moi"))
			{
				IErpChuyenKho lsxBean = new ErpChuyenKho();
				lsxBean.setUserId(userId);
				lsxBean.createRs();
				
				session.setAttribute("lsxBean", lsxBean);
				session.setAttribute("kenhId", "");
				session.setAttribute("khoxuat", "");
				session.setAttribute("nppId", lsxBean.getNppId());
				
				String nextJSP = request.getContextPath() + "/pages/Distributor/ErpChuyenKhoNew.jsp";
				response.sendRedirect(nextJSP);
			} else
			{
				if (action.equals("view") || action.equals("next") || action.equals("prev"))
				{
					String search = getSearchQuery(request, obj);
					obj.setNxtApprSplitting(Integer.parseInt(request.getParameter("nxtApprSplitting")));
					obj.setUserId(userId);
					obj.init(search);
					obj.setAttribute(request, action, "list", "crrApprSplitting", "nxtApprSplitting");
					session.setAttribute("obj", obj);
					
					String nextJSP = request.getContextPath() + "/pages/Distributor/ErpChuyenKho.jsp";
					response.sendRedirect(nextJSP);
				} else
				{
					String search = getSearchQuery(request, obj);
					obj.init(search);
					obj.setUserId(userId);
					
					session.setAttribute("obj", obj);
					session.setAttribute("userId", userId);
					
					String nextJSP = request.getContextPath() + "/pages/Distributor/ErpChuyenKho.jsp";
					response.sendRedirect(nextJSP);
					
				}
			}
		}
	}
	
	private String getSearchQuery(HttpServletRequest request, IErpChuyenKhoList obj)
	{
		String query = "select a.PK_SEQ, a.trangthai, isnull( a.sochungtu,'')as SOCHUNGTU ,a.ngaychuyen, a.ghichu as lydo, NV.TEN as nguoitao, b.ten as khoxuat,c.ten as nppTEN, a.NGAYSUA, a.NGAYTAO, NV2.TEN as nguoisua   " + "from ERP_CHUYENKHO a inner join KHO b on a.khoxuat_fk = b.pk_seq inner join NHAPHANPHOI c on a.npp_fk = c.pk_seq  " + "inner join NHANVIEN nv on a.NGUOITAO = nv.PK_SEQ   " + "inner join NHANVIEN nv2 on a.NGUOISUA = nv2.PK_SEQ where a.pk_seq > 0 ";
		
		String tungaySX = request.getParameter("tungay");
		if (tungaySX == null)
			tungaySX = "";
		obj.setTungayTao(tungaySX);
		
		String denngaySX = request.getParameter("denngay");
		if (denngaySX == null)
			denngaySX = "";
		obj.setDenngayTao(denngaySX);
		
		String sochungtu = request.getParameter("sochungtu");
		if (sochungtu == null)
			sochungtu = "";
		obj.setSochungtu(sochungtu);
		
		String trangthai = request.getParameter("trangthai");
		if (trangthai == null)
			trangthai = "";
		obj.setTrangthai(trangthai);
		
		String nppId = request.getParameter("nppId");
		if (nppId == null)
			nppId = "";
		obj.setNppId(nppId);
		
		String khId = request.getParameter("khId");
		if (khId == null)
			khId = "";
		obj.setKhId(khId);
		
		if (tungaySX.length() > 0)
			query += " and a.ngaychuyen >= '" + tungaySX + "'";
		
		if (denngaySX.length() > 0)
			query += " and a.ngaychuyen <= '" + denngaySX + "'";
		
		if (trangthai.length() > 0)
			query += " and a.TrangThai = '" + trangthai + "'";
		
		if (sochungtu.length() > 0)
			query += " and a.sochungtu like '%" + sochungtu + "%'";
		
		if (khId.length() > 0)
			query += " and  c.pk_seq = '" + khId + "'";
		
		if (nppId.length() > 0)
			query += " and a.tructhuoc_fk = '" + nppId + "'";
		
		System.out.print(query);
		return query;
		
	}
	
	public String getDateTime()
	{
		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		Date date = new Date();
		return dateFormat.format(date);
	}
}
