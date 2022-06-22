package geso.dms.distributor.servlets.hoadontaichinhNPP;

import geso.dms.center.db.sql.Idbutils;
import geso.dms.center.util.UtilitySyn;
import geso.dms.distributor.beans.hoadontaichinhNPP.IErpHoadontaichinhNPP;
import geso.dms.distributor.beans.hoadontaichinhNPP.IErpHoadontaichinhNPPList;
import geso.dms.distributor.beans.hoadontaichinhNPP.imp.ErpHoadontaichinhNPP;
import geso.dms.distributor.beans.hoadontaichinhNPP.imp.ErpHoadontaichinhNPPList;
import geso.dms.distributor.db.sql.dbutils;
import geso.dms.distributor.servlets.dondathang.ErpDondathangDoitacSvl;
import geso.dms.distributor.util.Utility;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Enumeration;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class ErpHoadontaichinhNPPSvl extends HttpServlet 
{
	private static final long serialVersionUID = 1L;
       
    public ErpHoadontaichinhNPPSvl() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		IErpHoadontaichinhNPPList obj;
		request.setCharacterEncoding("UTF-8");
	    response.setCharacterEncoding("UTF-8");
	    response.setContentType("text/html; charset=UTF-8");
	    
	    PrintWriter out = response.getWriter();
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
	    Utility util = new Utility();
	    	    
	    String querystring = request.getQueryString();
	    String userId = util.getUserId(querystring);
	    
	    if (userId.length()==0)
	    	userId = util.antiSQLInspection(request.getParameter("userId"));
	    
	    String action = util.getAction(querystring);
	    	    
    	String lsxId = util.getId(querystring);
	    obj = new ErpHoadontaichinhNPPList();
	   
	    String nppId = util.getIdNhapp(userId);
	  
	    
	    String km="";
	    km=request.getParameter("km");
	    if(km!=null)
	    {
	    	obj.setLoaikm(km);
	    }
	    String loaidonhang = request.getParameter("loaidonhang");
	    if(loaidonhang == null)
	    	loaidonhang = "";
	    obj.setLoaidonhang(loaidonhang);
	    System.out.println("---LOAI DON HANG: " + loaidonhang);
	    
    	if(action.equals("chot"))
    	{
    		String msg = this.Chot(lsxId,userId);
			obj.setMsg(msg);
    	}
    	else if(action.equals("delete"))
    	{
    		String msg = this.Delete(lsxId,userId, nppId);
			obj.setMsg(msg);
    	}
    	else if(action.equals("huy"))
    	{
    		String msg = this.Huy(lsxId,userId, nppId);
			obj.setMsg(msg);
    	}
    	
    	//lenh in phieu giao hang
		String type = request.getParameter("type");
		System.out.println("type: "+type);
		if(type==null)
			type="";
		session.setAttribute("type", type);
    	
    	
    	obj.setUserId(userId);	    
    	obj.init("");
		session.setAttribute("obj", obj);
		String nextJSP="";
			nextJSP= request.getContextPath() + "/pages/Distributor/ErpHoaDonTaiChinhNPP.jsp";
		
			
		response.sendRedirect(nextJSP);
	    
	}

	private String Huy(String lsxId, String userId, String nppId) 
	{
		dbutils db = new dbutils();
		String msg = "";
		
		Utility util = new Utility();
		msg= util.Check_Huy_NghiepVu_KhoaSo("ERP_HOADONNPP", lsxId, "ngayxuatHD", db);
		if(msg.length()>0)
		{
			db.shutDown();
			return msg;
		}
		
		try
		{	
			db.getConnection().setAutoCommit(false);
			String query = "";
			
			// Kiểm tra import =1 thì k cho hủy
			query = "select isnull(import, 0) as import, " +
					"(  SELECT count(B.PK_SEQ) DEM \n" +
					"	FROM ERP_THUTIENNPP_HOADON A INNER JOIN ERP_THUTIENNPP B ON A.THUTIENNPP_FK = B.PK_SEQ \n" +
					"   INNER JOIN ERP_HOADONNPP C ON A.HOADONNPP_FK = C.PK_SEQ AND A.LOAIHD = 0 \n" +
					" 	LEFT JOIN NHAPHANPHOI NPP ON NPP.PK_SEQ = C.NPP_DAT_FK AND NPP.LOAINPP=4 and NPP.TRUCTHUOC_FK = '"+ nppId +"'  \n" +
					"   LEFT JOIN KHACHHANG KH ON KH.PK_SEQ = C.KHACHHANG_FK  \n" +
		 	   		" 	AND C.KHACHHANG_FK in \n"+
				    " 	(SELECT PK_SEQ from KHACHHANG WHERE KBH_FK=100052 and NPP_FK='" + nppId +"') \n"+
					"   WHERE B.TRANGTHAI IN (0,1) AND C.PK_SEQ = "+lsxId + " AND C.KHACHHANG_FK = A.KHACHHANG_FK  ) is_ThuTien, \n"+
					"	( select COUNT(*) from ERP_YCXUATKHONPP_DDH where hoadon_fk = a.pk_seq  ) as dacoXK " +
					"from ERP_DONDATHANGNPP where pk_seq in (select ddh_fk from ERP_HOADONNPP_DDH where HOADONNPP_FK = '" + lsxId + "')";
			
			System.out.println(query);
			
			ResultSet rsKT = db.get(query);
			int ktra = 0;
			int is_Thutien = 0;
			int dacoXK = 0;
			
			if(rsKT!= null)
			{
				while(rsKT.next())
				{
					ktra = rsKT.getInt("import");
					is_Thutien =  rsKT.getInt("is_ThuTien");
					dacoXK =  rsKT.getInt("dacoXK");
				}
				rsKT.close();
			}
			
			if( dacoXK > 0 )
			{
				msg = "Đơn hàng đã có xuất kho. Bạn không thể xóa / hủy. ";
				geso.dms.center.util.Utility.rollback_and_shutdown(db);
				return msg;
			}
			
			if(ktra == 1)
			{
				msg = "Đơn hàng này GESO import nên không hủy được. ";
				geso.dms.center.util.Utility.rollback_and_shutdown(db);
				return msg;
			}
			else
			{
				if(is_Thutien>0)
				{
					msg = "Hóa đơn này đã thu tiền. Bạn không được phép xóa/hủy hóa đơn ";
					geso.dms.center.util.Utility.rollback_and_shutdown(db);
					return msg;
				}
					
				
				query = "select ycxk_fk from ERP_YCXUATKHONPP_DDH where hoadon_fk="+ lsxId +"  ";
				ResultSet rs = db.get(query);
				rs.next();
				String pxkId=rs.getString("ycxk_fk");
				query = "select count(*) as sl from  ERP_YCXUATKHONPP  where pk_seq = " + pxkId + "  ";
				System.out.println("query la "+query);
				ResultSet rspxk=db.get(query);
				rspxk.next();
				if(rspxk.getInt("sl")!=0)
				{
					rspxk.close();
					msg = "Bạn không thể Hủy hóa đơn đã có phiếu xuất kho." ;
					geso.dms.center.util.Utility.rollback_and_shutdown(db);
					return msg;
				}
				rspxk.close();
				
				
				
				query = "update ERP_HOADONNPP set trangthai = '5',NgaySua='"+getDateTime()+"' where pk_seq = '" + lsxId + "' and TrangThai=1 ";
				System.out.println(query);
				if(db.updateReturnInt(query)!=1)
				{
					msg = "Hóa đơn đã hủy rồi "+query;
					geso.dms.center.util.Utility.rollback_and_shutdown(db);
					return msg;
				}
				
				// Lấy mã đơn đặt hàng

				// Lấy mã đơn đặt hàng
				query = "select DDH_FK,  " +
						"	( select top 1  YCXK_FK from ERP_YCXUATKHONPP_DDH where ddh_fk = a.DDH_FK order by ycxk_fk desc) as soPXK   " +
						"from ERP_HOADONNPP_DDH a where HOADONNPP_FK = '" + lsxId + "' ";
				ResultSet rsDDH = db.get(query);
				String dondathang_fk1 = "";
				String pxk_fk = "";
				{
									
					if(rsDDH.next())
					{
						dondathang_fk1 = rsDDH.getString("DDH_FK");	
						pxk_fk = rsDDH.getString("soPXK");	
					}
	
					query = "select khachhang_fk, a.kbh_fk, a.npp_fk, a.npp_dat_fk, " +
							"( select priandsecond from NHAPHANPHOI where pk_seq = a.npp_fk ) as tuxuatOTC,  " +
							"( select tuxuatETC from NHAPHANPHOI where pk_seq = a.npp_fk ) as tuxuatETC,  " +
							"( select loaiNPP from NHAPHANPHOI where pk_seq = a.npp_fk ) as loaiNPP, " +
							"( select tructhuoc_fk from NHAPHANPHOI where pk_seq = a.npp_fk ) as tructhuoc_fk,  " +
							" ISNULL( ( select dungchungkenh from NHAPHANPHOI where pk_seq = a.npp_fk ), 0 ) as dungchungkenh  " +
							"from ERP_DondathangNPP a where a.pk_seq = '" + dondathang_fk1 + "' order by pk_seq desc";
					
					System.out.println(dondathang_fk1);
					
					String khachhangID = "";
					String loaiNPP = "";
					String tructhuoc = "";
					String npp_dat_fk = "";
					String kbh_fk1 = "";
					
					String tuquanlyKHO_OTC = "0";
					String tuquanlyKHO_ETC = "0";			
					
					 rs = db.get(query);
					{
						if(rs.next())
						{
							if(rs.getString("khachhang_fk") != null)
								khachhangID = rs.getString("khachhang_fk");
							
							loaiNPP = rs.getString("loaiNPP");
							tructhuoc = rs.getString("tructhuoc_fk");
							nppId = rs.getString("npp_fk");
							
							if(rs.getString("npp_dat_fk") != null)
								npp_dat_fk = rs.getString("npp_dat_fk");
							
							if(rs.getString("dungchungkenh").equals("1"))
								kbh_fk1 = "100025";
							else
								kbh_fk1 = rs.getString("kbh_fk");
							
							if(rs.getString("tuxuatOTC") != null)
								tuquanlyKHO_OTC = rs.getString("tuxuatOTC");
							
							if(rs.getString("tuxuatETC") != null)
								tuquanlyKHO_ETC = rs.getString("tuxuatETC");
							
						}
						rs.close();
					}
					
				if( ( khachhangID.trim().length() > 0 && tuquanlyKHO_ETC.equals("0") )  )
					{ 
						msg = "Chương trình phiên bản chưa cập nhật hủy hóa đơn trong trường hợp nhà tự quản lý kho , vui lòng báo Admin để được trợ giúp.";
						geso.dms.center.util.Utility.rollback_and_shutdown(db);
						return msg;
					
					/*
						if (tructhuoc.trim().length() >= 5) // TRỰC THUỘC NPP
						{
							// check tồn kho
							msg = HuyPhieuXuatKho_CapTren_NPP(db,dondathang_fk1, tructhuoc);
							if(msg.trim().length() > 0)
							{
								System.out.println("msg weeeee"+msg);
								msg = "Khong the huy: " + msg;
								db.getConnection().rollback();
								return msg;
							}
						}
						else
						// PHIẾU YCXK của HO
						{
							msg = HuyPhieuXuatKho_CapTren_HO(dondathang_fk1, tructhuoc);
							if(msg.trim().length() > 0)
							{
								msg = "Khong the huy: " + msg;
								db.getConnection().rollback();
								return msg;
							}
						}
					*/}
				else
					// Tạo PXK cho NPP (NPP TU QUAN LY TON KHO)
					{
						msg = Mo_chot_erp_dondathangnpp( pxk_fk, npp_dat_fk, db ,dondathang_fk1);
						if(msg.trim().length() > 0)
						{
							msg = "Khong the huy: " + msg;
							geso.dms.center.util.Utility.rollback_and_shutdown(db);
							return msg;
						}
					}
				 } 
			}
			
			msg= util.Check_Kho_Tong_VS_KhoCT(nppId, db);
			if(msg.length()>0)
			{
				geso.dms.center.util.Utility.rollback_and_shutdown(db);
				return msg;
			}
			
			geso.dms.center.util.Utility.commit_and_shutdown(db);
			return "Xóa thành công!";
		}
		catch (Exception e) 
		{
			geso.dms.center.util.Utility.rollback_and_shutdown(db);
			return "Exception: " + e.getMessage();
		}
		
	}
	
	private String Delete(String lsxId, String userId, String nppId) 
	{
		dbutils db = new dbutils();
		String msg = "";
		
		Utility util = new Utility();
		
		msg= util.Check_Huy_NghiepVu_KhoaSo("ERP_HOADONNPP", lsxId, "ngayxuatHD", db);
		if(msg.length()>0)
		{
			db.shutDown();
			return msg;
		}
		
		try
		{	
			db.getConnection().setAutoCommit(false);
			String query = "";
			
			// Kiểm tra import =1 thì k cho hủy
			query = "select isnull(import, 0) as import, \n" +
					"(  SELECT count(B.PK_SEQ) DEM \n" +
					"	FROM ERP_THUTIENNPP_HOADON A INNER JOIN ERP_THUTIENNPP B ON A.THUTIENNPP_FK = B.PK_SEQ \n" +
					"   INNER JOIN ERP_HOADONNPP C ON A.HOADONNPP_FK = C.PK_SEQ AND A.LOAIHD = 0 \n" +
					" 	LEFT JOIN NHAPHANPHOI NPP ON NPP.PK_SEQ = C.NPP_DAT_FK AND NPP.LOAINPP=4 and NPP.TRUCTHUOC_FK = '"+ nppId +"'  \n" +
					"   LEFT JOIN KHACHHANG KH ON KH.PK_SEQ = C.KHACHHANG_FK  \n" +
		 	   		" 	AND C.KHACHHANG_FK in \n"+
				    " 	(SELECT PK_SEQ from KHACHHANG WHERE KBH_FK=100052 and NPP_FK='" + nppId +"') \n"+
					"   WHERE B.TRANGTHAI IN (0,1) AND C.PK_SEQ = "+lsxId + " AND C.KHACHHANG_FK = A.KHACHHANG_FK  ) is_ThuTien, \n"+
				    "	( select COUNT(*) from ERP_YCXUATKHONPP_DDH where hoadon_fk = a.pk_seq  ) as dacoXK " +
					"from ERP_DONDATHANGNPP a " +
					"where pk_seq in (select ddh_fk from ERP_HOADONNPP_DDH where HOADONNPP_FK = '" + lsxId + "')";
			
			System.out.println(query);
			ResultSet rsKT = db.get(query);
			int ktra = 0;
			int is_ThuTien = 0;
			int dacoXK = 0;
			
			if(rsKT!= null)
			{
				while(rsKT.next())
				{
					ktra = rsKT.getInt("import");
					is_ThuTien = rsKT.getInt("is_ThuTien");
					dacoXK =  rsKT.getInt("dacoXK");
				}
				rsKT.close();
			}
			
			if( dacoXK > 0 )
			{
				msg = "Hóa đơn đã có phiếu xuất kho. Bạn không thể xóa / hủy ";
				db.getConnection().rollback();
				return msg;
			}
			
			if(ktra == 1)
			{
				msg = "Đơn hàng này GESO import nên không hủy được. ";
				db.getConnection().rollback();
				return msg;
			}
			else
			{
				
				if(is_ThuTien > 0)
				{
					msg = "Hóa đơn đã được thu tiền. Bạn không được xóa/hủy hóa đơn. ";
					db.getConnection().rollback();
					return msg;
				}
				
				
				query = "select count(*) as sl from  ERP_YCXUATKHONPP  where pk_seq = (select ycxk_fk from ERP_YCXUATKHONPP_DDH where hoadon_fk="+ lsxId +" )  ";
				System.out.println("query la "+query);
				ResultSet rspxk=db.get(query);
				rspxk.next();
				if(rspxk.getInt("sl")!=0)
				{
					rspxk.close();
					msg = "Bạn không thể xóa hóa đơn đã có phiếu xuất kho." ;
					db.getConnection().rollback();
					return msg;
				}
				rspxk.close();
				
				
				query = "update ERP_HOADONNPP set trangthai = '3' where trangthai=1 and pk_seq = '" + lsxId + "' ";
				System.out.println(query);
				if(db.updateReturnInt(query) !=1)
				{
					msg = "Không thể hủy ERP_HOADONNPP " + query;
					db.getConnection().rollback();
					return msg;
				}
				
				// Lấy mã đơn đặt hàng
				query = " select DDH_FK  " +
						" from ERP_HOADONNPP_DDH a where HOADONNPP_FK = '" + lsxId + "' ";
				System.out.println("queye "+query);
				ResultSet rsDDH = db.get(query);
				String dondathang_fk1 = "";
				String pxk_fk = "";
				/*if(rsDDH != null)*/
				{
									
					if(rsDDH.next())
					{
						dondathang_fk1 = rsDDH.getString("DDH_FK");	
						
					}
	
					query = "select khachhang_fk, a.kbh_fk, a.npp_fk, a.npp_dat_fk, " +
							"( select priandsecond from NHAPHANPHOI where pk_seq = a.npp_fk ) as tuxuatOTC,  " +
							"( select tuxuatETC from NHAPHANPHOI where pk_seq = a.npp_fk ) as tuxuatETC,  " +
							"( select loaiNPP from NHAPHANPHOI where pk_seq = a.npp_fk ) as loaiNPP, " +
							"( select tructhuoc_fk from NHAPHANPHOI where pk_seq = a.npp_fk ) as tructhuoc_fk,  " +
							" ISNULL( ( select dungchungkenh from NHAPHANPHOI where pk_seq = a.npp_fk ), 0 ) as dungchungkenh  " +
							"from ERP_DondathangNPP a where a.pk_seq = '" + dondathang_fk1 + "' order by pk_seq desc";
					
					System.out.println(dondathang_fk1);
					
					String khachhangID = "";
					String loaiNPP = "";
					String tructhuoc = "";
					
					String npp_dat_fk = "";
					String kbh_fk1 = "";
					
					String tuquanlyKHO_OTC = "0";
					String tuquanlyKHO_ETC = "0";			
					
					ResultSet rs = db.get(query);
					/*if(rs != null)*/
					{
						if(rs.next())
						{
							if(rs.getString("khachhang_fk") != null)
								khachhangID = rs.getString("khachhang_fk");
							
							loaiNPP = rs.getString("loaiNPP");
							tructhuoc = rs.getString("tructhuoc_fk");
							nppId = rs.getString("npp_fk");
							
							if(rs.getString("npp_dat_fk") != null)
								npp_dat_fk = rs.getString("npp_dat_fk");
							
							if(rs.getString("dungchungkenh").equals("1"))
								kbh_fk1 = "100025";
							else
								kbh_fk1 = rs.getString("kbh_fk");
							
							if(rs.getString("tuxuatOTC") != null)
								tuquanlyKHO_OTC = rs.getString("tuxuatOTC");
							
							if(rs.getString("tuxuatETC") != null)
								tuquanlyKHO_ETC = rs.getString("tuxuatETC");
							
						}
						rs.close();
					}
					
				if( ( khachhangID.trim().length() > 0 && tuquanlyKHO_ETC.equals("0") )  )
					{
						if (tructhuoc.trim().length() >= 5) // TRỰC THUỘC NPP
						{
							// check tồn kho
							msg = HuyPhieuXuatKho_CapTren_NPP(db,dondathang_fk1, tructhuoc);
							if(msg.trim().length() > 0)
							{
								msg = "Khong the huy: " + msg;
								db.getConnection().rollback();
								return msg;
							}
						}
						else
						// PHIẾU YCXK của HO
						{
							msg = HuyPhieuXuatKho_CapTren_HO(dondathang_fk1, tructhuoc);
							if(msg.trim().length() > 0)
							{
								msg = "Khong the huy: " + msg;
								db.getConnection().rollback();
								return msg;
							}
						}
					}
				else
					// Tạo PXK cho NPP (NPP TU QUAN LY TON KHO)
					{
						msg = Mo_chot_erp_dondathangnpp( lsxId, npp_dat_fk, db ,dondathang_fk1 );
						if(msg.trim().length() > 0)
						{
							msg = "Khong the huy: " + msg;
							db.getConnection().rollback();
							return msg;
						}
					}
				 } 
			}
			
			msg= util.Check_Kho_Tong_VS_KhoCT(nppId, db);
			if(msg.length()>0)
			{
				db.getConnection().rollback();
				return msg;
			}
			
			db.getConnection().commit();
			db.getConnection().setAutoCommit(true);
		}
		catch (Exception e) 
		{
			db.update("rollback");
			db.shutDown();
			return "Exception: " + e.getMessage();
		}		
		
		return "";
	}
	
public static void main(String[] args) {
		
		dbutils db = new dbutils();
		try
		{
			db.getConnection().setAutoCommit(false);
			String kq =ERP_HOADONNPP_CHUNGLOAI(db, "1000000027");
			if(kq.trim().length() > 0)
			{
				db.getConnection().rollback();
			}
			else
				db.getConnection().commit();
			
			System.out.println("kq = "+ kq);
			
		}
		catch (Exception e) {
			db.update("rollback");
		}
		finally
		{
			db.shutDown();
		}
		
	}
	
	
	public static String ERP_HOADONNPP_CHUNGLOAI(Idbutils db , String hdId)
	{
		try
		{
			int dem = 0 ;
			
			String query = " delete ERP_HOADONNPP_CHUNGLOAI where hoadon_fk =" + hdId;
			if(!db.update(query))
			{
				
				return "Loi khong the chèn chủng loai: "+query; 
			}
			boolean DaInsert = false;
			query =   		 "\n WITH HDCH AS (select distinct  b.chungloai_fk, a.thuevat   " + 
							 "\n from ERP_HOADONNPP_SP_CHITIET a inner join SANPHAM b on a.ma = b.ma    " + 
							 "\n where a.hoadon_fk = "+hdId+")   " + 
							 "\n select chungloai_fk,thuevat, (select COUNT(*) from HDCH )sodong from HDCH ";
			System.out.println("query hoadon = "+ query);
			ResultSet rs = db.get(query);
			while(rs.next())
			{
				
				String chungloai_fk = rs.getString("chungloai_fk");
				double thuevat = rs.getDouble("thuevat");
				double sodong = rs.getDouble("sodong");
				dem ++;
				System.out.println("chungloai_fk( "+ chungloai_fk +"),thuevat = "+thuevat );
				
				if(dem != sodong)
				query=   "\n declare @sotien float = round ( ( select sum( round(  FLOOR(a.soluong * a.dongia) - isnull(a.chietkhau,0) - isnull(a.tonggiamtru,0) *(1 + "+thuevat+"/100.0)     ,0 )    ) " +
						 "\n							  from ERP_HOADONNPP_SP_CHITIET a inner join SANPHAM b on a.ma = b.ma  " +
						 "\n							 where a.hoadon_fk = '" +  hdId  + "' and b.chungloai_fk = "+ chungloai_fk + " and a.thuevat =  "+ thuevat +"	   ),0) " +	
						 "\n insert ERP_HOADONNPP_CHUNGLOAI( hoadon_fk, chungloai_fk, sotienGOC, sotien ) "+
						 "\n	select "+hdId+", "+chungloai_fk +",@sotien ,@sotien    " ;
				else
					query = "\n declare @sotien float =   (select tongtienAvat from ERP_HOADONNPP where PK_SEQ =" +  hdId  + ") - " +
							"\n 						(select isnull(SUM(Sotien),0) from ERP_HOADONNPP_CHUNGLOAI where hoadon_fk  = " +  hdId  + " and chungloai_fk != "+ chungloai_fk + ")  " +
							"\n insert ERP_HOADONNPP_CHUNGLOAI( hoadon_fk, chungloai_fk, sotienGOC, sotien ) " +
							"\n select  "+hdId+", "+chungloai_fk+" , @sotien,@sotien ";
				
				if(db.updateReturnInt(query)!= 1)
				{
					
					return "Loi khong the chèn chủng loai: "+query; 
				}
			
				DaInsert = true;
			}
			if(!DaInsert)
			{
				return "Chưa lưu hóa đơn chủng loại";
			}
			query = "\n  declare @tienavat float = isnull( (select tongtienAvat from ERP_HOADONNPP where PK_SEQ =" +  hdId  + "),0) " +
					"\n  declare @tienchungloai float = isnull( (select isnull(SUM(Sotien),0) from ERP_HOADONNPP_CHUNGLOAI where hoadon_fk  = " +  hdId + ") ,0)" +
					"\n  select count(1) sodong where @tienavat = @tienchungloai  ";
			rs = db.get(query);
			rs.next();
			if(rs.getInt("sodong") <=0 )
			{
				return "Số tiền  theo chủng loại khác tổng tiền avat";
			}
			return "";
		
		}catch (Exception e) {
			e.printStackTrace();
			return "Lỗi :" + e.getMessage();
		}
		 
	}
	

	
	private String Chot(String lsxId,String userId) 
	{
		dbutils db = new dbutils();
		String msg = "";
		
		Utility util = new Utility();
		msg = util.Check_Huy_NghiepVu_KhoaSo("ERP_HOADONNPP", lsxId, "ngayxuatHD", db);
		if(msg.length()>0)
		{
			db.shutDown();
			return msg;
		}
		
		try
		{
			db.getConnection().setAutoCommit(false);
			
			//CHECK KHOA SO THANG
			String query = "";
			System.out.println("1.Khoi tao thang: " + query);
			
			String checkhd=" select count(*) as sl from ERP_HOADONNPP where (sohoadon='' or kyhieu='' or ngayxuathd='') and pk_seq="+lsxId;
			System.out.println("cau query kiem tra la "+checkhd);
			ResultSet rshdcheck=db.get(checkhd);
			rshdcheck.next();
			if(rshdcheck.getInt("sl")>0)
			{
				msg = "Vui lòng điền đầy đủ thông tin ngày hóa đơn , ký hiệu ,số hóa đơn .";
				geso.dms.center.util.Utility.rollback_and_shutdown(db);
				rshdcheck.close();
				return msg;
			}
			
			
			query = "update ERP_HOADONNPP set trangthai = '2',NgaySua='"+getDateTime()+"',created_Date=getdate()  where pk_seq = '" + lsxId + "' and trangthai in (0,1)  ";
			if(db.updateReturnInt(query)!=1)
			{
				msg = "Hóa đơn đã chuyển trạng thái ,vui lòng xem lại ";
				geso.dms.center.util.Utility.rollback_and_shutdown(db);
				return msg;
			}

			
			
			query = "select npp_fk,(select quanlykho from nhaphanphoi where pk_seq = npp_fk)quanlykho from  ERP_HOADONNPP  where pk_seq = '" + lsxId + "' ";
			ResultSet rs=db.get(query);
			String npp="";
			String quanlykho = "";
			if(rs.next())
			{
				npp=rs.getString("npp_fk");
				quanlykho = rs.getString("quanlykho");
			}
			rs.close();
			
		
			
			//CHECK BANG TONG PHAI BANG BANG CHI TIET
			if (quanlykho.equals("1")) {
				query = "select count(*) as sodong  " +
						"from " +
						"( " +
						"	select b.pk_seq as sanpham_fk, sum(soluong) as soluong  " +
						"	from ERP_HOADONNPP_SP a inner join SANPHAM b on a.sanpham_fk = b.pk_seq " +
						"	where a.hoadon_fk = '" + lsxId + "' " +
						"	group by b.pk_seq " +
						") " +
						"dh left join " +
						"( " +
						"	select b.pk_seq as sanpham_fk, sum(soluong) as soluong  " +
						"	from ERP_HOADONNPP_SP_CHITIET a inner join SANPHAM b on a.MA = b.MA " +
						"	where a.hoadon_fk = '" + lsxId + "' " +
						"	group by b.pk_seq " +
						") " +
						"xk on dh.sanpham_fk = xk.sanpham_fk " +
						"where dh.soluong != isnull(xk.soluong, 0) ";
				System.out.println("---CHECK HOA DON: " + query);
				int soDONG = 0;
				ResultSet rsCHECK = db.get(query);
				if(rsCHECK != null)
				{
					if(rsCHECK.next())
					{
						soDONG = rsCHECK.getInt("sodong");
					}
					rsCHECK.close();
				}
				
				if(soDONG > 0)
				{
					msg = "3.Số lượng trong đơn hàng không khớp với hóa đơn. Vui lòng liên hệ Admin để xử lý ";
					geso.dms.center.util.Utility.rollback_and_shutdown(db);
					return msg;
				}			
			}
			
			//CAP NHAT DVDL
			query = "  update a " + 
					"  	set a.DonVi_Chuan = b.DVDL_FK, " + 
					"  		a.dvdl_fk = ( select PK_SEQ from DONVIDOLUONG where DONVI = a.DONVITINH ), " + 
					"  		a.SoLuong_Chuan = a.SOLUONG * dbo.LayQuyCach(a.SANPHAM_FK, null, ( select PK_SEQ from DONVIDOLUONG where DONVI = a.DONVITINH ) ) " + 
					"  from ERP_HOADONNPP_SP a  inner join SANPHAM b on a.SANPHAM_FK = b.PK_SEQ  " + 
					"  where a.HOADON_FK in ( '" + lsxId + "' )  ";
			System.out.println("---CAP NHAT HOA DON: " + query);
			if(!db.update(query))
			{
				msg = "Không thể cập nhật ERP_HOADONNPP_SP " + query;
				geso.dms.center.util.Utility.rollback_and_shutdown(db);
				return msg;
			}
			
			
			
			
			
			boolean kq=createPXK(db, lsxId, userId);
			if(!kq)
			{
				msg = "lỗi tạo phiếu xuất kho " + query;
				geso.dms.center.util.Utility.rollback_and_shutdown(db);
				return msg;
			}
			
			if (quanlykho.equals("1")) {
				msg = util.Check_Kho_Tong_VS_KhoCT(util.getIdNhapp(userId, db), db);
				if(msg.length()>0)
				{
					geso.dms.center.util.Utility.rollback_and_shutdown(db);
					return msg;
				}		
			}
			
			geso.dms.center.util.Utility.commit_and_shutdown(db);
			return "Chốt thành công!";
		}
		catch (Exception e) 
		{
			geso.dms.center.util.Utility.rollback_and_shutdown(db);
			return "Exception: " + e.getMessage();
		}
		
		
	}
	
	private String Mo_chot_erp_dondathangnpp(String ycxkId, String npp_dat_fk, dbutils db,String dondathang_fk1)
	{
		
		
		String msg = "";
		try
		{			
			msg = ErpDondathangDoitacSvl.Nha_book_erp_dondathangnpp(db, dondathang_fk1, "Xóa hóa đơn  ");
			if(msg.length() > 0)
			{				
				return msg;
			}
			
			//MO LAI TRANG THAI DON HANG
			String query = "update ERP_DONDATHANGNPP set trangthai = '0' ,checkkho='1' where pk_seq in  ( "+dondathang_fk1+" ) and TrangThai!=0 ";
			if(db.updateReturnInt(query)!=1)
			{
				msg = "Đơn đặt hàng này đã mở trạng thái rồi "+query;
				return msg;
			}
			
			
			
		}
		catch (Exception e) 
		{
			e.printStackTrace();
			return "Không thể hủy hóa đơn " + e.getMessage();
		}
		return "";
	}
	
	private String HuyPhieuXuatKho_CapTren_HO (String dondathang_fk1, String tructhuoc)
	{
		String msg="";
		dbutils db = new dbutils();
		try 
		{			
		
			String	query = "select sp.PK_SEQ, sp.TEN, LOAI, SCHEME, SUM(dathang.soluong) as soluongXUAT " +
				"from    " +
				"(    " +
				"		select a.sanpham_fk, b.DVDL_FK as dvCHUAN,    " +
				"				case when a.dvdl_fk IS null then a.soluong     " +
				"					 when a.dvdl_fk = b.DVDL_FK then a.soluong    " +
				"					 else  a.soluong * ( select SOLUONG1 from QUYCACH where sanpham_fk = a.sanpham_fk and DVDL2_FK = a.dvdl_fk )     " +
				"									 / ( select SOLUONG2 from QUYCACH where sanpham_fk = a.sanpham_fk and DVDL2_FK = a.dvdl_fk )	 end as soluong, 0 as loai, ' ' as scheme   " +
				"		from ERP_DONDATHANGNPP_SANPHAM a inner join SANPHAM b on a.sanpham_fk = b.PK_SEQ    " +
				"		where a.dondathang_fk in ( '" + dondathang_fk1 + "' )   " +
				")    " +
				"dathang inner join SANPHAM sp on dathang.sanpham_fk = sp.PK_SEQ  " +
				"group by sp.PK_SEQ, sp.TEN, LOAI, SCHEME ";
				System.out.println("--CHECK KHO CHI TIET: " + query);
				
				ResultSet rsCHECK = db.get(query);
				
				
					db.getConnection().setAutoCommit(false);
					
					if(rsCHECK != null)
					{
						while(rsCHECK.next())
						{
							String sanpham_fk = rsCHECK.getString("PK_SEQ");
							String LOAI = rsCHECK.getString("LOAI");
							String SCHEME = rsCHECK.getString("SCHEME");
							double soLUONG = rsCHECK.getDouble("soluongXUAT");
						
							query = "Update ERP_KHOTT_SANPHAM set soluong = soluong + '" + soLUONG + "', AVAILABLE = AVAILABLE + '" + soLUONG + "' " +
							"where KHOTT_FK = '" + 100001 + "' and SANPHAM_FK = '" + sanpham_fk + "'  ";
							
							//CAP NHAT KHO CHI TIET
							query = "select AVAILABLE, SOLO from ERP_KHOTT_SP_CHITIET " +
									"where KHOTT_FK = '" + 100001 + "'  and SANPHAM_FK = '" + sanpham_fk + "' order by ngayhethan asc ";
							
							ResultSet rsTK = db.get(query);
							double avai = 0;
							double totalXUAT = 0;
							while(rsTK.next())
							{
								double soluongCT = 0;
								String solo = rsTK.getString("SOLO");						
								avai = rsTK.getDouble("AVAILABLE");
								
								soluongCT = avai;
								
								query = "Update ERP_KHOTT_SP_CHITIET set soluong = soluong - '" + soluongCT + "', AVAILABLE = AVAILABLE - '" + soluongCT + "' " +
								"where KHOTT_FK = '" + 100001 + "' and SOLO = '" + solo + "' and SANPHAM_FK = '" + sanpham_fk + "'  ";
								
								if(!db.update(query))
								{
									msg = "Khong the cap nhat ERP_KHOTT_SP_CHITIET: " + query;
									//db.getConnection().rollback();
									return msg;
								}
							}
							
							rsTK.close();
		
						}
						rsCHECK.close();
					}
					
					query = "update ERP_DONDATHANGNPP set trangthai = '0' where pk_seq = '" + dondathang_fk1 + "' ";
					System.out.println(query);
					if(!db.update(query))
					{
				msg = "Không thể cập nhật trạng thái ERP_DONDATHANGNPP:  " + query;
						db.getConnection().rollback();
						return msg;
					}
					
					query = "select * from ERP_YCXUATKHONPP_DDH  WHERE ddh_fk='"+dondathang_fk1+"'";
					ResultSet RS_PhieuXUATKHO = db.get(query);			
					String PhieuXUATKHO="";
					if(RS_PhieuXUATKHO!=null)
						PhieuXUATKHO = RS_PhieuXUATKHO.getString("ycxk_fk"); 
					
			// hủy phiếu xuất kho
					query = "Update ERP_YCXUATKHONPP set TRANGTHAI= '3' where pk_seq = '" + PhieuXUATKHO + "' ";
					System.out.println(query);
					if(!db.update(query))
					{
				msg = "Không thể cập nhật trạng thái ERP_YCXUATKHONPP:  " + query;
						db.getConnection().rollback();
						return msg;
					}	
			
		}
		catch (Exception e) 
		{
			msg = "--LOI DUYET: " + e.getMessage();
			e.printStackTrace();
			return msg;
		}
				
		return "";
	}
	
	private String HuyPhieuXuatKho_CapTren_NPP(dbutils db ,String dondathang_fk1, String tructhuoc)
	{
		// check tồn kho
		String msg="";
		String query = "";
		query =  "select khoxuat_fk as kho_fk, npp_fk, kbh_fk, sp.PK_SEQ, sp.TEN, SUM(dathang.soluong) as soluongXUAT,  " +
				"	ISNULL( ( select AVAILABLE from NHAPP_KHO where kho_fk = dathang.khoxuat_fk and sanpham_fk = sp.PK_SEQ and kbh_fk = dathang.kbh_fk and npp_fk = '" + tructhuoc + "' ), 0) as tonkho  " +
				"from     " +
				"(     " +
				"	select c.kho_fk as khoxuat_fk, '" + tructhuoc + "' as npp_fk, c.kbh_fk, a.sanpham_fk, b.DVDL_FK as dvCHUAN,     " +
				"			case when a.dvdl_fk IS null then a.soluong      " +
				"				 when a.dvdl_fk = b.DVDL_FK then a.soluong     " +
				"				 else  a.soluong * ( select SOLUONG1 from QUYCACH where sanpham_fk = a.sanpham_fk and DVDL2_FK = a.dvdl_fk and dvdl1_fk = b.dvdl_fk )      " +
				"								 / ( select SOLUONG2 from QUYCACH where sanpham_fk = a.sanpham_fk and DVDL2_FK = a.dvdl_fk and dvdl1_fk = b.dvdl_fk )	 end as soluong   " +
				"	from ERP_DONDATHANGNPP_SANPHAM a inner join SANPHAM b on a.sanpham_fk = b.PK_SEQ  " +
				"			inner join ERP_DONDATHANGNPP c on a.dondathang_fk = c.pk_seq    " +
				"	where a.dondathang_fk in ( " + dondathang_fk1 + " )     " +
				")     " +
				"dathang inner join SANPHAM sp on dathang.sanpham_fk = sp.PK_SEQ   " +
				"group by khoxuat_fk, npp_fk, kbh_fk, sp.PK_SEQ, sp.TEN  ";		
		System.out.println("Câu query ở đây: " + query);
		ResultSet rs_tonkho = db.get(query);
		try
		{
			System.out.println("vao day: ");
			if(rs_tonkho != null)
			{
				System.out.println("vao day1: ");
				while(rs_tonkho.next())
				{
					System.out.println("vao day2: ");
					String npp_fk = rs_tonkho.getString("npp_fk");					
					String kbh_fk = rs_tonkho.getString("kbh_fk");
					String kho_fk = rs_tonkho.getString("kho_fk");
					String sanpham_fk = rs_tonkho.getString("PK_SEQ");
					
					double soluong = rs_tonkho.getDouble("soluongXUAT");
					double tonkho = rs_tonkho.getDouble("tonkho");							
					
					//CAP NHAT KHO XUAT TONG
					query = "Update NHAPP_KHO set soluong = soluong + '" + soluong + "', BOOKED = BOOKED + '" + soluong + "' " +
							"where KHO_FK = '"+kho_fk+"' and KBH_FK = '" + kbh_fk + "' and NPP_FK = '" + npp_fk + "' and SANPHAM_FK = '" + sanpham_fk + "' ";
					System.out.println(query);
					if(db.updateReturnInt(query)!=1)
					{
						msg = "Khong the cap nhat NHAPP_KHO: " + query;
						rs_tonkho.close();
						return msg;
					}
					
					//CAP NHAT KHO CHI TIET
					query = "select AVAILABLE, SOLO, ngayhethan from NHAPP_KHO_CHITIET " +
							"where AVAILABLE > 0 and KHO_FK = '" + kho_fk + "'  and SANPHAM_FK = '" + sanpham_fk  + "' and NPP_FK = '" + npp_fk + "' and KBH_FK = '" + kbh_fk + "' order by ngayhethan asc ";
					
					System.out.println(query);
					
					ResultSet rsTK = db.get(query);
					double avai = 0;
					double totalXUAT = 0;
					while(rsTK.next())
					{
						double soluongCT = 0;
						String solo = rsTK.getString("SOLO");
						String ngayhethan = rsTK.getString("ngayhethan");
						
						avai = rsTK.getDouble("AVAILABLE");
						//totalXUAT += avai;
						soluongCT = avai;
						query = "Update NHAPP_KHO_CHITIET set soluong = soluong + '" + soluongCT + "', AVAILABLE = AVAILABLE + '" + soluongCT + "' " +
						"where KHO_FK = '" + kho_fk + "' and SOLO = '" + solo + "' and SANPHAM_FK = '" + sanpham_fk + "' adn KBH_FK = '" + kbh_fk + "' and NPP_FK = '" + npp_fk + "' and NgayHetHan='"+ngayhethan+"' ";
						System.out.println(query);
						
						if(db.updateReturnInt(query)!=1)
						{
							msg = "Khong the cap nhat NHAPP_KHO_CHITIET: " + query;
							rsTK.close();
							return msg;
						}
					}
					rsTK.close();
				}
			rs_tonkho.close();
			
			
			query = "update ERP_DONDATHANGNPP set trangthai = '0' where pk_seq = '" + dondathang_fk1 + "' ";
			System.out.println(query);
			if(!db.update(query))
			{
					msg = "Không thể cập nhật trạng thái ERP_DONDATHANGNPP:  " + query;
				return msg;
			}
			
			query = "select * from ERP_YCXUATKHONPP_DDH  WHERE ddh_fk='"+dondathang_fk1+"'";
			ResultSet RS_PhieuXUATKHO = db.get(query);
			//----
			String PhieuXUATKHO="";
			if(RS_PhieuXUATKHO!=null)
				PhieuXUATKHO = RS_PhieuXUATKHO.getString("ycxk_fk"); //////////////////
			
				// hủy phiếu xuất kho
			query = "Update ERP_YCXUATKHONPP set TRANGTHAI= '3' where pk_seq = '" + PhieuXUATKHO + "' ";
			System.out.println("cap nhat huy phieu xuat kho"+query);
			if(!db.update(query))
			{
					msg = "Không thể cập nhật trạng thái ERP_YCXUATKHONPP:  " + query;
			}	
		}
		}
		catch (Exception e) 
		{
			e.printStackTrace();
			return "Exception  HuyPhieuXuatKho_CapTren_NPP ";
		}
		return msg;
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		request.setCharacterEncoding("UTF-8");
	    response.setCharacterEncoding("UTF-8");
	    response.setContentType("text/html; charset=UTF-8");
	    geso.dms.center.util.Csrf csdr=new geso.dms.center.util.Csrf(request,response,false,true ,false);
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

	    	
	    
	    String loaidonhang = request.getParameter("loaidonhang");
	    if(loaidonhang == null)
	    	loaidonhang = "0";
	    
	    
		IErpHoadontaichinhNPPList obj = new ErpHoadontaichinhNPPList();
		obj.setLoaidonhang(loaidonhang);
		
		String km="";
	    km=request.getParameter("km");
	    if(km!=null)
	    {
	    	obj.setLoaikm(km);
	    }
		
	    Utility util = new Utility();
	    
		HttpSession session = request.getSession();
	    String userId = util.antiSQLInspection(request.getParameter("userId")); 
	    if(userId!=null)
    		obj.setUserId(userId);
	    System.out.println("user id la "+userId);
	    if(action.equals("Tao moi"))
	    {
	    	IErpHoadontaichinhNPP lsxBean = new ErpHoadontaichinhNPP();
	    	lsxBean.setUserId(userId);
	    	lsxBean.createRs();
	    	session.setAttribute("dvkdId", "");
			session.setAttribute("kbhId", "");
			session.setAttribute("nppId", "");
    		
	    	session.setAttribute("lsxBean", lsxBean);
	    	
    		String nextJSP = request.getContextPath() + "/pages/Distributor/ErpHoaDonTaiChinhNPPNew.jsp";
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
		    	
		    	String nextJSP = request.getContextPath() + "/pages/Distributor/ErpHoaDonTaiChinhNPP.jsp";
				response.sendRedirect(nextJSP);
		    }
	    	else
	    	{
		    	String search = getSearchQuery(request, obj);
		    	obj.setUserId(userId);
		    	String sumqr = getSumQuery(request, obj);
		    	obj.getSumBySearch(sumqr);
		    	obj.init(search);		    	
		    	session.setAttribute("obj", obj);  	
	    		session.setAttribute("userId", userId);		
	    		String nextJSP = request.getContextPath() + "/pages/Distributor/ErpHoaDonTaiChinhNPP.jsp";
	    		response.sendRedirect(nextJSP);
	    	}
	    }
	}
	
	private String getSearchQuery(HttpServletRequest request, IErpHoadontaichinhNPPList obj)
	{
		String query="";
		if(!obj.getLoaikm().equals("1"))
		{
		 query= "select distinct isnull(a.loaihd,0) loaihd, a.PK_SEQ, a.trangthai, a.ngayxuatHD, RTRIM(LTRIM (a.sohoadon)) as sohoadon, a.kyhieu as kyhieu,isnull( kb.ten,'') as kenhbanhang, NV.TEN as nguoitao, a.tongtienavat as tongtien , " +
					" case when a.NPP_DAT_FK is not null then npp.TEN else KH.TEN end as khTEN, a.NGAYSUA, a.NGAYTAO, NV2.TEN as nguoisua ,a.LoaiHoaDon " +
					"from ERP_HOADONNPP a " +
					" inner join ERP_HOADONNPP_DDH ddh on ddh.HOADONNPP_FK=a.PK_SEQ "+
					" inner join ERP_DONDATHANGNPP dh on dh.PK_SEQ=ddh.DDH_FK "+
					"left join KHACHHANG KH on a.KHACHHANG_FK=KH.PK_SEQ  " +
					"left join NHAPHANPHOI npp on a.NPP_DAT_FK=npp.PK_SEQ  " +
					"inner join NHANVIEN nv on a.NGUOITAO = nv.PK_SEQ   " +
					"inner join NHANVIEN nv2 on a.NGUOISUA = nv2.PK_SEQ " +
					" left join kenhbanhang kb on kb.pk_seq=a.kbh_fk "+
					"where   isnull(loaihd,0)<>1    and dh.isKM <>1 and a.pk_seq > 0  ";
		}
		else
		{
			 query= "select distinct isnull(a.loaihd,0) loaihd, a.PK_SEQ, a.trangthai, a.ngayxuatHD,  RTRIM(LTRIM (a.sohoadon)) as sohoadon, a.kyhieu as kyhieu,isnull( kb.ten,'') as kenhbanhang, NV.TEN as nguoitao, a.tongtienavat as tongtien , " +
						" case when a.NPP_DAT_FK is not null then npp.TEN else KH.TEN end as khTEN, a.NGAYSUA, a.NGAYTAO, NV2.TEN as nguoisua ,a.LoaiHoaDon " +
						"from ERP_HOADONNPP a " +
						" inner join ERP_HOADONNPP_DDH ddh on ddh.HOADONNPP_FK=a.PK_SEQ "+
						" inner join ERP_DONDATHANGNPP dh on dh.PK_SEQ=ddh.DDH_FK "+
						"left join KHACHHANG KH on a.KHACHHANG_FK=KH.PK_SEQ  " +
						"left join NHAPHANPHOI npp on a.NPP_DAT_FK=npp.PK_SEQ  " +
						"inner join NHANVIEN nv on a.NGUOITAO = nv.PK_SEQ   " +
						"inner join NHANVIEN nv2 on a.NGUOISUA = nv2.PK_SEQ " +
						" left join kenhbanhang kb on kb.pk_seq=a.kbh_fk "+
						"where  (isnull(loaihd,0)=1 or    dh.isKM =1 ) and a.pk_seq > 0 and a.npp_fk =(select b.pk_seq from NHANVIEN a inner join NHAPHANPHOI b on a.CONVSITECODE=b.SITECODE where a.PK_SEQ= '"+obj.getUserId()+"') ";
		}
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
		
		String khten = request.getParameter("khTen");
		String[] loai = khten.split("--");   	
    	obj.setKhTen(khten);
		
		String sohoadon = request.getParameter("sohoadon");
		if(sohoadon == null)
			sohoadon = "";
		obj.setSohoadon(sohoadon);
		
		String sodonhang = request.getParameter("sodonhang");
		if(sodonhang == null)
			sodonhang = "";
		obj.setSodonhang(sodonhang);
		
		if(sohoadon.length()>0)
			query += " and a.sohoadon LIKE '%" + sohoadon + "%'";
		
		if(tungay.length() > 0)
			query += " and a.ngayxuatHD >= '" + tungay + "'";
		
		if(denngay.length() > 0)
			query += " and a.ngayxuatHD <= '" + denngay + "'";
	
		if(trangthai.length() > 0)
		{
			if(trangthai.equals("3")||trangthai.equals("5") )
				query += " and a.TrangThai in (3,5) ";
			else
				query += " and a.TrangThai = '" + trangthai + "'";
		}
		
		
		String loaihoadon = request.getParameter("loaidonhang")==null?"0":request.getParameter("loaidonhang");
		obj.setLoaidonhang(loaihoadon);
		
		if(loaihoadon.length() > 0)
			query += " and a.loaihoadon = '" + loaihoadon + "'";
		
		
		if(khten.length() > 0)
			query += " and ( kh.pk_seq ='" + loai[1] + "' or a.npp_dat_fk = '" + loai[1] + "' ) ";
		
		if(sodonhang.length()>0)
			query += " and a.pk_seq in ( select HOADONNPP_FK from Erp_HoaDonNPP_DDH WHERE Cast(DDH_fK as varchar(20))  LIKE '%"+sodonhang+"%' ) ";
		
		System.out.print("câu tìm kiếm " + query);
		return query;
	}
		
	public String getDateTime()
	{
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date date = new Date();
        return dateFormat.format(date);	
	}
	
	private String getSumQuery(HttpServletRequest request, IErpHoadontaichinhNPPList obj) 
	{
		String nppId = request.getParameter("nppId");
    	if ( nppId == null)
    		nppId = "";
    	obj.setnppId(nppId);
    	
    	String trangthai = request.getParameter("trangthai");
    	if (trangthai == null)
    		trangthai = "";    	
    	obj.setTrangthai(trangthai);
    	
    	String tungay = request.getParameter("tungay");
    	if (tungay == null)
    		tungay = "";    	
    	obj.setTungay(tungay);
    	
    	String denngay = request.getParameter("denngay");
    	if (denngay == null)
    		denngay = "";    	
    	obj.setDenngay(denngay);
    	
    	String sohoadon = request.getParameter("sohoadon");
		if(sohoadon == null)
			sohoadon = "";
		obj.setSohoadon(sohoadon);
		
		String sodonhang = request.getParameter("sodonhang");
		if(sodonhang == null)
			sodonhang = "";
		obj.setSodonhang(sodonhang);
    	
    	String khachhang = request.getParameter("khTen");
    	String[] loai = khachhang.split("--");   	
    	obj.setKhTen(khachhang);
    	    	
    	if ( nppId.length() == 0 && trangthai.length() == 0 && tungay.length() == 0 && denngay.length() == 0 && sodonhang.length() == 0 && khachhang.length()==0)
    		obj.setIsSearch(false);
    	else
    		obj.setIsSearch(true);
    	
    	String query =
    		" select " +
    		" sum(hd_npp.tongtienbvat) as doanhso,  sum(hd_npp.vat) as thue, sum(tongtienbvat + vat - chietkhau) as doanhthu "+
    		" from	ERP_HOADONNPP hd_npp inner join ERP_HOADONNPP_DDH hd_ddh on hd_npp.PK_SEQ = hd_ddh.HOADONNPP_FK "+
    		" where	1=1 ";     	
    	if (nppId.length() > 0)
    	{
			query = query + " and hd_npp.NPP_FK = '" + nppId + "'";		
    	} 
    	    	
    	if (trangthai.length() > 0)
    	{    		
    		if(trangthai.equals("3")||trangthai.equals("5") )
				query += " and hd_npp.TrangThai in (3,5) ";
			else
				query += " and hd_npp.TrangThai = '" + trangthai + "'";
    		
    	}
    	else
    		query += " and hd_npp.TRANGTHAI not in (3,5) ";
    	
    	if (tungay.length() > 0)
    	{
			query = query + " and hd_npp.NGAYXUATHD >= '" + tungay + "'";			
    	}    	
    	if (denngay.length() > 0)
    	{
			query = query + " and hd_npp.NGAYXUATHD <= '" + denngay + "'";	
    	}
    	if (sodonhang.length() > 0)
    	{
    		query = query + " and hd_ddh.DDH_FK like '%" + sodonhang + "%'";	
    	}
    	
    	if (khachhang.length() > 0)
    	{
    		if(loai[0].equals("ETC")){
    			query = query + " and hd_npp.KHACHHANG_FK='"+loai[1]+"' ";
    		}
    		else{
    			query = query + " and hd_npp.NPP_DAT_FK='"+loai[1]+"' ";
    		}
    	}
    	
    	if(sohoadon.length()>0)
    	{
    		query= query+ " and hd_npp.SOHOADON like '%"+sohoadon+"%' ";
    	}
    	    	
    	System.out.println("\nQuery cua ban: " + query);
    	return query;
	}
	
	
	public boolean createPXK(dbutils db,String hoadon_fk,String userid) 
	{
		

		try
		{
			String quanlykho = "";
			String khachhang_fk = "NULL";
			String npp_dat_fk = "NULL";
			String xuatcho="0";
			String query="select isnull(KHACHHANG_FK,'0') KHACHHANG_FK,isnull(NPP_DAT_FK,'0') NPP_DAT_FK,NGAYXUATHD, " +
					"npp_fk,(select quanlykho from nhaphanphoi where pk_seq = npp_fk)quanlykho,kho_fk from ERP_HOADONNPP where PK_SEQ="+hoadon_fk;
			ResultSet rs=db.get(query);
			rs.next();
			quanlykho = rs.getString("quanlykho");
			String ngayxuathd=rs.getString("ngayxuathd");
			String npp_fk=rs.getString("npp_fk");
			String kho_fk=rs.getString("kho_fk");
			if(!rs.getString("NPP_DAT_FK").trim().equals("0"))
			{
				npp_dat_fk = rs.getString("NPP_DAT_FK");
			}
			else
			{
				khachhang_fk = rs.getString("KHACHHANG_FK");
				xuatcho="1";
			}
			rs.close();
			 query = " insert ERP_YCXUATKHONPP(NgayYeuCau, ghichu, trangthai, npp_fk, kho_fk, xuatcho, NPP_DAT_FK, KHACHHANG_FK, KBH_FK, ngaytao, nguoitao, ngaysua, nguoisua) " +
						   " select top(1) '" + ngayxuathd + "', N'', '0', '" + npp_fk + "', " + kho_fk + ", '" + xuatcho + "', " + npp_dat_fk + ", " + khachhang_fk + ", kbh_fk, " +
						   		" '" + getDateTime() + "', '" + userid + "', '" + getDateTime() + "', '" + userid + "' " +
						   " from ERP_HOADONNPP where pk_seq = '" + hoadon_fk + "' ";
			
			System.out.println("1.Insert ERP_YCXUATKHONPP: " + query);
			if( db.updateReturnInt(query) <= 0 )
			{
				System.out.println("Không thể tạo mới ERP_YCXUATKHONPP " + query);
				return false;
			}
			
			query = "select SCOPE_IDENTITY() as btId";
			ResultSet rsBtId = db.get(query);	
			
			rsBtId.next();
		    String ycxkCurrentId = rsBtId.getString("btId");
		    rsBtId.close();
		    geso.dms.center.util.Utility uti=new geso.dms.center.util.Utility();
		    
		    
		    
		    
		    
			query = "Insert ERP_YCXUATKHONPP_DDH(ycxk_fk, hoadon_fk,ddh_fk) " +
					"select "+ ycxkCurrentId +", HOADONNPP_FK,DDH_FK from ERP_HOADONNPP_DDH  a inner join ERP_HOADONNPP b on a.HOADONNPP_FK=b.pk_seq  where a.HOADONNPP_FK in ( " + hoadon_fk + " )  and b.trangthai not in (3,5) ";
			System.out.println("2.chen ERP_YCXUATKHONPP: " + query);
			if( db.updateReturnInt(query) <= 0 )
			{
				System.out.println("Không thể tạo mới ERP_YCXUATKHONPP_DDH " + query);
				return false;
			}
			
			query = " insert ERP_YCXUATKHONPP_ChietKhau (YCXK_FK,diengiai,giatri,loai) " +
					" select "+ ycxkCurrentId +", diengiai,giatri,loai" +
					" from ERP_HOADONNPP_CHIETKHAU" +
					" where hoadon_fk ='" + hoadon_fk + "' "  ;
			if( db.updateReturnInt(query) < 0 )
			{
				System.out.println("Không thể tạo mới ERP_YCXUATKHONPP_ChietKhau " + query);
				return false;
			}
			
			query = "\n insert ERP_YCXUATKHONPP_SANPHAM(thuevat, kbh_fk,kho_fk,ycxk_fk, sanpham_fk, soluongDAT, tonkho, daxuat, soluongXUAT, LOAI, SCHEME ) " +
			"\n	select a.vat,a.kbh_fk,a.kho_fk,"+ ycxkCurrentId +" , a.sanpham_fk , soluong_chuan,null,soluong_chuan,soluong_chuan,0,''  " +
			"\n from ERP_HOADONNPP_SP a " +
			"\n where hoadon_fk  = '" + hoadon_fk + "'" +
			"\n union all  " +
			"\n	select a.vat,a.kbh_fk,a.kho_fk, "+ ycxkCurrentId +" , a.sanpham_fk , a.soluong,null, a.soluong, a.soluong,1, a.ctkm  " +
			"\n from ERP_HOADONNPP_ctkm_trakm a " +
			"\n where hoadon_fk  = '" + hoadon_fk + "'" ;
			if(db.updateReturnInt(query)<=0)
			{
				System.out.println( "Khong the tao moi ERP_YCXUATKHO_SANPHAM_CHITIET: " + query);
				return false;
			}
		
			if  (quanlykho.equals("1")) {
				query = "insert ERP_YCXUATKHONPP_SANPHAM_CHITIET(thuevat, ycxk_fk,DONGIA, SANPHAM_FK, solo, ngayhethan, ngaynhapkho, soluong, loai, scheme, kho_fk,kbh_fk ) " +
				"\n	select a.thuevat,"+ ycxkCurrentId +" ,a.DonGia_Chuan, a.sanpham_fk,a.solo, a.ngayhethan , a.ngaynhapkho , a.soluong_chuan,0,'',a.kho_fk,a.kbh_fk  " +
				"\n from ERP_HOADONNPP_SP_CHITIET a " +
				"\n where hoadon_fk  = '" + hoadon_fk + "'" +
				"\n union all  " +
				"\n	select a.thuevat,"+ ycxkCurrentId +", 0  , a.sanpham_fk,a.solo, a.ngayhethan , a.ngaynhapkho  , a.soluong,1, a.ctkm ,a.kho_fk,a.kbh_fk " +
				"\n from ERP_HOADONNPP_ctkm_trakm_chitiet a " +
				"\n where hoadon_fk  = '" + hoadon_fk + "'" ;
							
				System.out.println("1.2.Insert YCXK - SP - CT: " + query);
				if(db.updateReturnInt(query)<=0)
				{
					System.out.println( "Khong the tao moi ERP_YCXUATKHO_SANPHAM_CHITIET: " + query);
					return false;
				}
							
				
			}
			
			
			
			
			/*String kq= Chot_YCXK_NPP(db,ycxkCurrentId,userid,npp_fk);
			if(kq.trim().length()>0)
				return false;*/

		} 
		catch (Exception e) 
		{
			e.printStackTrace();
			return false;
		}
		
		return true;
	}

	public static String Chot_YCXK_NPP(Idbutils db,String lsxId, String userId, String nppId) 
	{
		
		String msg = "";
		try
		{
				
			String query = "";
			String quanlykho = "";
			
			//GOP CHUNG BUOC YC VA XUAT THANH 1
			query = "update ERP_YCXUATKHONPP set trangthai = '2'  where pk_seq = '" + lsxId + "' and trangthai != 2 ";
			System.out.println("---CAP NHAT TRANG THAI: " + query);
			if(db.updateReturnInt(query) <= 0 )
			{
				msg = "Không thể cập nhật ERP_YCXUATKHONPP " + query;
				return msg;
			}
			
			query = "select quanlykho from nhaphanphoi where pk_seq = "+nppId;
			ResultSet getQlk = db.get(query);
			while (getQlk.next())
			{
				quanlykho = getQlk.getString("quanlykho");
			}
			getQlk.close();
			if (quanlykho == null)
				quanlykho = "0";
			
			if (quanlykho.equals("1")) {
				query = "  select c.npp_fk, case when isnull(d.dungchungkenh, 0) = 0 then c.kbh_fk else 100025 end as kbh_fk,  " + 
						"  		a.kho_fk, a.sanpham_fk, b.ten as TEN, a.soluong as soluongDAT, a.solo, a.ngayhethan,   " + 
						"  		a.soluong,  0 as loai, ' ' as scheme, a.ngaynhapkho     " + 
						"  from ERP_YCXUATKHONPP_SANPHAM_CHITIET a inner join SANPHAM b on a.sanpham_fk = b.PK_SEQ    " + 
						"  		inner join ERP_YCXUATKHONPP c on  a.ycxk_fk = c.pk_seq inner join NHAPHANPHOI d on c.npp_fk = d.pk_seq  " + 
						"  where a.ycxk_fk in (  " + lsxId + " ) ";
				
				System.out.println("--CAP NHAT TON KHO: " + query);
				
				ResultSet rs = db.get(query);
				if(rs != null)
				{
					geso.dms.center.util.Utility utility = new geso.dms.center.util.Utility();
					while(rs.next())
					{
						String khoID = rs.getString("kho_fk");
						String kbhID = rs.getString("kbh_fk");
						String nppID = rs.getString("npp_fk");
						String spID = rs.getString("sanpham_fk");
						
						double soluong = rs.getDouble("soluong");
						String solo = rs.getString("solo");
						String ngayhethan = rs.getString("ngayhethan");
						String ngaynhapkho=rs.getString("ngaynhapkho");
						
						
						String msg1=utility.Update_NPP_Kho_Sp(Utility.getNgayHienTai(), "chot phieu xuat kho doi tac tong " 
								, db, khoID, spID, nppID, kbhID, (-1)* soluong, (-1)* soluong, 0, 0);
						if(msg1.length() >0){
							msg = "Khong the cap nhat ERP_KHOTT_SANPHAM: " + query;
							rs.close();
							return msg;
						}
						
						
						
						String kq1 = utility.Update_NPP_Kho_Sp_Chitiet(Utility.getNgayHienTai(), "chot phieu xuat kho ETC ", db, khoID, spID, nppID, kbhID, solo, ngayhethan, ngaynhapkho,  (-1) * soluong,  (-1) * soluong, 0, 0, 0);
						if( kq1.length() > 0 )
						{
							msg = "Khong the cap nhat NHAPP_KHO_CHITIET: " + kq1;
							rs.close();
							return msg;
						}
						
												
					}
					rs.close();
				}
				query ="\n update a set  kbh_fk = case when isnull(npp.DUNGCHUNGKENH,0)  = 1 then 100025 else npp.KBH_FK end " +
					"\n from ERP_YCXUATKHONPP_SANPHAM_CHITIET a  " +
					"\n outer apply " +
					"\n ( " +
					"\n 	select dungchungkenh , x.* " +
					"\n 	from NHAPHANPHOI npp  " +
					"\n 	inner join ERP_YCXUATKHONPP x on x.NPP_FK = npp.PK_SEQ " +
					"\n 	where x.pk_seq = a.ycxk_fk " +
					"\n )npp  where a.ycxk_fk in (  " + lsxId + " ) " ;
				if(!db.update(query)){
					msg = "Khong the cap nhat ERP_YCXUATKHONPP_SANPHAM_CHITIET : " + query;
					rs.close();
					return msg;
				}
			}
			
			//TU DONG HOAN TAT CAC HOA DON TU CU TOI MOI
			query = "\n select hoadon_fk, ( select xuatcho from ERP_YCXUATKHONPP where pk_seq = a.ycxk_fk ) as xuatcho " +
					"\n from ERP_YCXUATKHONPP_DDH a where ycxk_fk = '" + lsxId + "' order by hoadon_fk asc";
			System.out.println("---CAP NHAT TRANG THAI HOA DON: " + query);
			
			ResultSet rsDDH = db.get(query);
			String ddhID = "";
			String xuatCHO = "";
			if(rsDDH != null)
			{
				while(rsDDH.next())
				{
					ddhID = rsDDH.getString("hoadon_fk") + ",";
					xuatCHO = rsDDH.getString("xuatcho");
					
					query = "  select COUNT(*) as soDONG,   " +
							" 		(   select count(distinct sanpham_fk) as soSP      " +
							"   			from     " +
							"   			(     " +
							"   					select a.sanpham_fk " +
							"   					from ERP_HOADONNPP_SP a inner join SANPHAM b on a.sanpham_fk = b.PK_SEQ     " +
							"   					where a.hoadon_fk in (    " + ( ddhID.substring(0, ddhID.length() - 1) ) + "   )    " +
							"   			)     " +
							"   			dathang  )	 as soSP  " +
							"  from  " +
							"  (  " +
							"  	select sanpham_fk, sum(soluongXUAT) as soluongXUAT  " +
							"  	from ERP_YCXUATKHONPP_SANPHAM  " +
							" 	where ycxk_fk in ( select ycxk_fk from ERP_YCXUATKHONPP_DDH where hoadon_fk in ( " + ( ddhID.substring(0, ddhID.length() - 1) ) + " ) ) " +
							"  	group by sanpham_fk  " +
							"  )   " +
							"  XUAT inner join   " +
							"  (  " +
							"   	select dathang.sanpham_fk, SUM(dathang.soluong) as soluongDAT      " +
							"   	from     " +
							"   	(     " +
							"   			select a.sanpham_fk, b.DVDL_FK as dvCHUAN,     " +
							"   					a.soluong_chuan as soluong, 0 as loai, ' ' as scheme    " +
							"   			from ERP_HOADONNPP_SP a inner join SANPHAM b on a.sanpham_fk = b.PK_SEQ     " +
							"   			where a.hoadon_fk in ( " + ( ddhID.substring(0, ddhID.length() - 1) ) + " )    " +
							"   	)     " +
							"   	dathang   " +
							"   	group by dathang.sanpham_fk  " +
							"  )  " +
							"  DDH on XUAT.sanpham_fk = DDH.sanpham_fk  " +
							"  where XUAT.soluongXUAT >= DDH.soluongDAT ";
					
					System.out.println("CHECK HOAN TAT: " + query);
					ResultSet rsCHECK = db.get(query);
					if(rsCHECK.next())
					{
						String hoantat = "0";
						if(rsCHECK.getInt("soDONG") == rsCHECK.getInt("soSP"))
							hoantat = "1";  //HOAN TAT
						
						query = " UPDATE ERP_HOADONNPP set hoantat = '" + hoantat + "' where pk_seq in ( " + ( ddhID.substring(0, ddhID.length() - 1) ) + " ) ";
						if(!db.update(query))
						{
							msg = "Không thể chốt ERP_YCXUATKHO " + query;
							return msg;
						}
					}
				}
				rsDDH.close();
			}
			
			
			System.out.println("---XUAT CHO: " + xuatCHO);
			if(xuatCHO.equals("0"))  //XUẤT CHO ĐỐI TÁC THÌ TẠO NHẬP HÀNG CHO ĐỐI TÁC
			{
				query = " insert NHAPHANG(NGAYNHAN, NGAYCHUNGTU, NPP_FK, NCC_FK, GSBH_FK, ASM_FK, BM_FK, DVKD_FK, KBH_FK, YCXKNPP_FK, TRANGTHAI, NGUOITAO, NGAYTAO, NGUOISUA, NGAYSUA) " +
						" select distinct NgayYeuCau, NgayYeuCau, NPP_DAT_FK,  " +
						" 			( select top(1) NCC_FK from NHACUNGCAP_DVKD where PK_SEQ in ( select NCC_DVKD_FK from NHAPP_NHACC_DONVIKD where NPP_FK = a.NPP_DAT_FK ) ), " +
						"			( select top(1) GSBH_FK from NHAPP_GIAMSATBH where NPP_FK = a.NPP_DAT_FK ), " +
						"			( select top(1) ASM_FK from ASM_KHUVUC where KHUVUC_FK in ( select KHUVUC_FK from NHAPHANPHOI where PK_SEQ = a.NPP_DAT_FK )), " +
						"			( select top(1) BM_FK from BM_CHINHANH where VUNG_FK in ( select VUNG_FK from KHUVUC where PK_SEQ in (  select KHUVUC_FK from NHAPHANPHOI where PK_SEQ = a.NPP_DAT_FK ) ) ), " +
						" 	   '100001' as DVKD_FK, case when isnull(nppdat.DUNGCHUNGKENH,0)  = 1 then 100025 else a.KBH_FK end " +
						"	, '" + lsxId + "', '0', '" + userId + "', '" + Utility.getNgayHienTai() + "', '" + userId + "', '" +Utility.getNgayHienTai() + "' " +
						" from ERP_YCXUATKHONPP a inner join nhaphanphoi nppdat on nppdat.pk_seq = a.npp_dat_fk " +
						" where a.PK_SEQ = '" + lsxId + "' ";
				
				System.out.println("---INSERT NHAN HANG: " + query );
				if(!db.update(query))
				{
					msg = "Không tạo mới NHAPHANG " + query;
					return msg;
				}
				
				query = " insert NhapHang_ChietKhau (NHAPHANG_FK,diengiai,giatri,loai) " +
						" select b.PK_SEQ,a.DIENGIAI,a.GIATRI,a.LOAI " +
						" from ERP_YCXUATKHONPP_ChietKhau a " +
						" inner join NHAPHANG b on a.ycxk_fk = b.YCXKNPP_FK" +
						" where  b.YCXKNPP_FK =" +lsxId ;

				if( db.updateReturnInt(query) < 0 )
				{
					msg = "Không tạo mới NhapHang_ChietKhau " + query;
					return msg;
				}
				
				
				query = " select ngaychungtu,NPP_FK,PK_SEQ from NhapHang where YCXKNPP_FK = "+ lsxId;
				ResultSet rsNhapHang = db.get(query);
				if(rsNhapHang.next())
				{
				//ghi số chứng từ
					
					String _ngaychungtu = rsNhapHang.getString("ngaychungtu");
					String _NPP_FK = rsNhapHang.getString("NPP_FK");
					String _nhaphangId = rsNhapHang.getString("PK_SEQ");
					
					
				   
				    
					rsNhapHang.close();
				}
				else
				{
					rsNhapHang.close();
				
					return "Không thể ghi nhận so chung tu phieu nhan " + query;
				}
				
				if (quanlykho.equals("1"))
				{
				query = " insert NHAPHANG_SP(thuevat,khonhan_fk,kbh_fk,NHAPHANG_FK, SANPHAM_FK, SOLUONG, soluongNHAN, DONGIA, CHIETKHAU, DVDL_FK, LOAI, SCHEME, SOLO, NGAYHETHAN) " +
						" select b.thuevat,isnull(ctkm.khonhan_fk, b.kho_fk),b.kbh_fk,( select pk_seq from NHAPHANG where YCXKNPP_FK = a.PK_SEQ  ),  " +
						" 		b.sanpham_fk, b.soluong, NULL, b.dongia, 0 as chietkhau, c.DVDL_FK, b.LOAI, b.SCHEME, b.solo, b.ngayhethan " +
						" from ERP_YCXUATKHONPP a inner join ERP_YCXUATKHONPP_SANPHAM_CHITIET b on a.PK_SEQ = b.ycxk_fk " +
						" 		inner join SANPHAM c on b.sanpham_fk = c.PK_SEQ" +
						" left join ctkhuyenmai ctkm on ctkm.scheme =  b.SCHEME  " +
						" where a.PK_SEQ = '" + lsxId + "' and b.soluong > 0 ";
				}
				else
				{
					query = " insert NHAPHANG_SP(thuevat,kho_fk,kbh_fk,NHAPHANG_FK, SANPHAM_FK, SOLUONG, soluongNHAN, DONGIA, CHIETKHAU, DVDL_FK, LOAI, SCHEME, SOLO, NGAYHETHAN) " +
					" select b.thuevat, b.kho_fk,b.kbh_fk,( select pk_seq from NHAPHANG where YCXKNPP_FK = a.PK_SEQ  ),  " +
					" 		b.sanpham_fk, b.soluongxuat, NULL, null, 0 as chietkhau, c.DVDL_FK, b.LOAI, b.SCHEME, 'NA' solo, '2030-01-01' ngayhethan " +
					" from ERP_YCXUATKHONPP a inner join ERP_YCXUATKHONPP_SANPHAM b on a.PK_SEQ = b.ycxk_fk " +
					" 		inner join SANPHAM c on b.sanpham_fk = c.PK_SEQ   " +
					" where a.PK_SEQ = '" + lsxId + "' and b.soluongxuat > 0 ";
				}
				
				System.out.println("---INSERT NHAN HANG - SP: " + query );
				if(db.updateReturnInt(query) < 1 )
				{
					msg = "Không tạo mới NHAPHANG_SP " + query;
				
					return msg;
				}
				
				query = "insert NHAPHANG_DDH(nhaphang_fk, ddh_fk)  " +
						"select ( select PK_SEQ from NHAPHANG where YCXKNPP_FK = '" + lsxId + "' ) as nhID, ddh_fk  " +
						"from ERP_YCXUATKHONPP_DDH where ycxk_fk = '" + lsxId + "'";
				if(!db.update(query))
				{
					msg = "Không tạo mới NHAPHANG_DDH " + query;
				
					return msg;
				}
			}
			
			
		}
		catch (Exception e) 
		{
			e.printStackTrace();
			return "Exception: " + e.getMessage();
		}
		
		return "";
	}
	
}
