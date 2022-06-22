package geso.dms.distributor.servlets.dontrahang;

import geso.dms.center.util.Utility; 
import geso.dms.distributor.beans.dontrahang.IDontrahang;
import geso.dms.distributor.beans.dontrahang.IDontrahangList;
import geso.dms.distributor.beans.dontrahang.imp.Dontrahang;
import geso.dms.distributor.beans.dontrahang.imp.DontrahangList;
import geso.dms.distributor.db.sql.dbutils;

import java.io.IOException;
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

public class DontrahangSvl extends HttpServlet
{
	private static final long serialVersionUID = 1L;
	public DontrahangSvl()
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
			IDontrahangList obj;
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
			obj = new DontrahangList();
			obj.setUserId(userId);
			
			if (action.equals("delete"))
			{
				String msg = this.DeleteChuyenKho(lsxId, userId);
				System.out.println("___DeleteChuyenKho__"+msg);
				obj.setMsg(msg);
				
			} else if (action.equals("chot"))
			{
				String msg = this.Chot(lsxId, userId);
				obj.setMsg(msg);
				System.out.println("_Chot_"+msg);
				
			} else if (action.equals("UnChot"))
			{
				String msg = this.UnChot(lsxId, userId);
				System.out.println("UnChot"+msg);
				obj.setMsg(msg);
			}
			
			obj.init("");
			session.setAttribute("obj", obj);
			
			String nextJSP = request.getContextPath() + "/pages/Distributor/DonTraHang.jsp";
			response.sendRedirect(nextJSP);
		}
	}
	
	private String UnChot(String lsxId, String userId)
  {
		dbutils db = new dbutils();
		String msg = "";
		
		Utility util = new Utility();
		msg= util.Check_Huy_NghiepVu_KhoaSo("DonTraHang", lsxId, "NgayTra", db);
		if(msg.length()>0)
			return msg;

		
		try
		{
			db.getConnection().setAutoCommit(false);
			
			String query="select TrucThuoc_FK, pk_seq,npp_fk from DonTraHang where pk_seq='"+lsxId+"' and TrangThai=2";
			String TrucThuoc_FK ="";
			String npp_fk = "";
			ResultSet rs =db.get(query);
			
			while(rs.next())
			{
				TrucThuoc_FK = rs.getString("TrucThuoc_FK");
				npp_fk = rs.getString("npp_fk");
			}
			rs.close();
			
			int SoDong=0;
			if(!TrucThuoc_FK.equals("1"))
			{
				 query="select count(*) as SoDong from Erp_HangTraLaiNpp where DonTraHang_fk ='"+lsxId+"' and TRANGTHAI=1 and DonTraHang_fk is not null ";
				 
				 System.out.println("___"+query);
				 rs =db.get(query);
					while(rs.next())
					 {
						 SoDong=rs.getInt("SoDong");
					 }
					 if(rs!=null)rs.close();
					
					if(SoDong>0)
					{
						msg = "Không thể hủy Xuất chuyển nội bộ khi đã có nhận hàng !";
						db.getConnection().rollback();
						return msg;
					}
			}else 
			{
				query="select count(*) as SoDong from Erp_NhapKho where DonTraHang_fk ='"+lsxId+"' and TRANGTHAI=1 and DonTraHang_fk is not null ";
				System.out.println("check :::::;"+query);
				rs =db.get(query);
				while(rs.next())
				 {
					 SoDong=rs.getInt("SoDong");
				 }
				 if(rs!=null)rs.close();
				
				if(SoDong>0)
				{
					msg = "Không thể hủy Xuất chuyển nội bộ khi đã có nhận hàng !";
					db.getConnection().rollback();
					return msg;
				}
				
					query="delete from ERP_NHAPKHO_SANPHAM where nhapkho_fk in (select pk_Seq from Erp_NhapKho  where DonTraHang_fk ='"+lsxId+"' and TRANGTHAI=0 and DonTraHang_fk is not null  ) ";
					if(!db.update(query))
					{
						msg = "2.Khong the xoa: " + query;
						db.getConnection().rollback();
						return msg;
					}
					query="delete from Erp_NhapKho  where DonTraHang_fk ='"+lsxId+"' and TRANGTHAI=0 and DonTraHang_fk is not null   ";
					if(db.updateReturnInt(query)!=1)
					{
						msg = "2.Khong the xoa: " + query;
						db.getConnection().rollback();
						return msg;
					}
			}
			
			//CHECK DUNG CHUNG KENH HAY KHONG
			String dungchungKENH = "0";
			String sqlKENH = "";
			
			 query =  "select isnull(dungchungkenh, 0) as dungchungkenh from NHAPHANPHOI " +
							"where pk_seq = ( select npp_fk from DonTraHang where pk_seq = '" + lsxId + "' )";
			ResultSet rsKENH = db.get(query);
			if(rsKENH != null)
			{
				if(rsKENH.next())
				{
					dungchungKENH = rsKENH.getString("dungchungkenh");
				}
				rsKENH.close();
			}
			
			if(dungchungKENH.equals("1"))
				sqlKENH = " 100025 as kbh_fk "; //LAY KENH OTC
			else
				sqlKENH = " A.kbh_fk ";
			
			query=
					" 	select count(*)   as SoDong " +
					" 	from DonTraHang a inner join DonTraHang_SP b on a.pk_seq = b.dontrahang_Fk " +
					" 	where dontrahang_Fk = '" + lsxId + "' " ;
					
					System.out.println("::::_01"+query);
					 SoDong=0;
					 rs =db.get(query);
					 while(rs.next())
					 {
						 SoDong=rs.getInt("SoDong");
					 }
					 if(rs!=null)rs.close();
			
			
			//GIAM KHO XUAT
		/*	query = " update kho set kho.SOLUONG = kho.SOLUONG + CT.tongxuat, " +
					" 			   kho.BOOKED = kho.BOOKED + CT.tongxuat " +
					" from " +
					" ( " +
					" 	select a.kho_fk as kho_fk, a.npp_fk, "+sqlKENH+", b.sanpham_fk, b.solo, SUM(b.soluong) as tongxuat,b.NgayHetHan  " +
					" 	from DonTraHang a inner join DonTraHang_SP b on a.pk_seq = b.dontrahang_Fk " +
					" 	where dontrahang_Fk = '" + lsxId + "' " +
					" 	group by a.kho_fk, a.npp_fk, a.kbh_fk, b.solo, b.sanpham_fk,b.NgayHetHan " +
					" ) " +
					" CT inner join NHAPP_KHO_CHITIET kho on CT.kho_fk = kho.KHO_FK  " +
					" 	and CT.sanpham_fk = kho.SANPHAM_FK and CT.solo = kho.SOLO and CT.NPP_FK = kho.npp_fk and CT.KBH_FK = kho.kbh_fk and kho.NgayHetHan=ct.NgayHetHan  ";
			System.out.println("---TANG KHO NGUOC LAI: " + query );
			if(db.updateReturnInt(query)!=SoDong)
			{
				msg = "Không thể cập nhật NHAPP_KHO_CHITIET " + query;
				db.getConnection().rollback();
				return msg;
			}
			
			query = " update kho set kho.SOLUONG = kho.SOLUONG + CT.tongxuat, " +
					" 			   kho.BOOKED = kho.BOOKED + CT.tongxuat " +
					" from " +
					" ( " +
					" 	select a.kho_fk as kho_fk, a.npp_fk, "+sqlKENH+" , b.sanpham_fk, SUM(b.soluong) as tongxuat  " +
					" 	from DonTraHang a inner join DonTraHang_SP b on a.pk_seq = b.dontrahang_Fk " +
					" 	where dontrahang_Fk = '" + lsxId + "' " +
					" 	group by a.kho_fk, a.npp_fk, a.kbh_fk, b.sanpham_fk " +
					" ) " +
					" CT inner join NHAPP_KHO kho on CT.kho_fk = kho.KHO_FK  " +
					" 	and CT.sanpham_fk = kho.SANPHAM_FK and CT.NPP_FK = kho.npp_fk and CT.KBH_FK = kho.kbh_fk ";
			System.out.println("---TANG KHO NGUOC LAI 2: " + query );
			if(db.updateReturnInt(query)<=0)
			{
				msg = "Không thể cập nhật NHAPP_KHO " + query;
				db.getConnection().rollback();
				return msg;
			}*/
			query = "update DonTraHang set trangthai = '0',Modified_Date='"+getDateTime()+"' where pk_seq = '" + lsxId + "' and trangthai=2  ";
			System.out.println("DonTraHang ::::::::::"+query);
			if(db.updateReturnInt(query)!=1)
			{
				msg = "Phiếu trả hàng này đã chốt rồi !"+query;
				db.getConnection().rollback();
				return msg;
			}
			
		//	msg= util.Check_Kho_Tong_VS_KhoCT(npp_fk, db);
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
			e.printStackTrace();
			db.update("rollback");
			return "Exception: " + e.getMessage();
		}
		finally
		{
			if(db!=null)db.shutDown();
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
			msg= util.Check_Huy_NghiepVu_KhoaSo("DonTraHang", id, "NgayTra", db);
			if(msg.length()>0)
				return msg;

			
			db.getConnection().setAutoCommit(false);
			
			//CHECK DUNG CHUNG KENH HAY KHONG
			String dungchungKENH = "0";
			String sqlKENH = "";
			
			String TrucThuoc_FK = "";
			String npp_fk = "";
			String	query =
			" select count(*)   as SoDong,a.TrucThuoc_FK ,c.DUNGCHUNGKENH, a.npp_fk " +
			" from DonTraHang a inner join DonTraHang_SP b on a.pk_seq = b.dontrahang_Fk " +
			" 		inner join NHAPHANPHOI c on c.PK_SEQ=a.NPP_FK " +
			" where dontrahang_Fk = '" + id + "' and a.trangthai in (0,1) " +
			" group by a.TrucThuoc_FK, c.DUNGCHUNGKENH, a.npp_fk " ;
			
			System.out.println("::::_01"+query);
			int SoDong=0;
			ResultSet rs = db.get(query);
			 while(rs.next())
			 {
				 SoDong=rs.getInt("SoDong");
				 TrucThuoc_FK=rs.getString("TrucThuoc_FK");
				 dungchungKENH = rs.getString("dungchungkenh");
				 npp_fk=rs.getString("npp_fk");
			 }
			 if(rs!=null)rs.close();
			 
			query = "update DonTraHang set trangthai = '1',Modified_Date=getdate() where pk_seq = '" + id + "' and trangthai in (0)  ";
			if(db.updateReturnInt(query)!=1)
			{
				msg = "Phiếu trả hàng này đã chốt rồi !"+query;
				db.getConnection().rollback();
				return msg;
			}
					 
			if(dungchungKENH.equals("1"))
				sqlKENH = " 100025 as kbh_fk "; //LAY KENH OTC
			else
				sqlKENH = " A.kbh_fk ";
						
						
			//GIAM KHO XUAT
		/*	query = " update kho set kho.SOLUONG = kho.SOLUONG - CT.tongxuat, " +
					" 			   kho.BOOKED = kho.BOOKED - CT.tongxuat " +
					" from " +
					" ( " +
					" 	select a.kho_fk as kho_fk, a.npp_fk, "+sqlKENH+", b.sanpham_fk, b.solo, SUM(b.soluong) as tongxuat,b.NgayHetHan  " +
					" 	from DonTraHang a inner join DonTraHang_SP b on a.pk_seq = b.dontrahang_Fk " +
					" 	where dontrahang_Fk = '" + id + "' " +
					" 	group by a.kho_fk, a.npp_fk, a.kbh_fk, b.solo, b.sanpham_fk,b.NgayHetHan " +
					" ) " +
					" CT inner join NHAPP_KHO_CHITIET kho on CT.kho_fk = kho.KHO_FK  " +
					" 	and CT.sanpham_fk = kho.SANPHAM_FK and CT.solo = kho.SOLO and CT.NPP_FK = kho.npp_fk and CT.KBH_FK = kho.kbh_fk and kho.NgayHetHan=ct.NgayHetHan  ";
			System.out.println("---TANG KHO NGUOC LAI: " + query );
			if(db.updateReturnInt(query)!=SoDong)
			{
				msg = "Không thể cập nhật NHAPP_KHO_CHITIET " + query;
				db.getConnection().rollback();
				return msg;
			}
			
			
			query = " update kho set kho.SOLUONG = kho.SOLUONG - CT.tongxuat, " +
					" 			   kho.BOOKED = kho.BOOKED - CT.tongxuat " +
					" from " +
					" ( " +
					" 	select a.kho_fk as kho_fk, a.npp_fk, "+sqlKENH+" , b.sanpham_fk, SUM(b.soluong) as tongxuat  " +
					" 	from DonTraHang a inner join DonTraHang_SP b on a.pk_seq = b.dontrahang_Fk " +
					" 	where dontrahang_Fk = '" + id + "' " +
					" 	group by a.kho_fk, a.npp_fk, a.kbh_fk, b.sanpham_fk " +
					" ) " +
					" CT inner join NHAPP_KHO kho on CT.kho_fk = kho.KHO_FK  " +
					" 	and CT.sanpham_fk = kho.SANPHAM_FK and CT.NPP_FK = kho.npp_fk and CT.KBH_FK = kho.kbh_fk ";
			System.out.println("---TANG KHO NGUOC LAI 2: " + query );
			if(db.updateReturnInt(query)<=0)
			{
				msg = "Không thể cập nhật NHAPP_KHO " + query;
				db.getConnection().rollback();
				return msg;
			}
			*/
			if(!TrucThuoc_FK.equals("1"))
			{
				msg = TaoHangTraLai_CapTren(id,db,userId);
			}
			else 
			{
				msg = TaoHangTraLai_HO(id, userId, db);
			}
			
			if(msg.length()>0)
			{
				msg += "Không thể tạo XUẤT CHUYỂN NỘI BÔ";
				db.getConnection().rollback();
				return msg;
			}
			
			
			
			query = "select npp_fk from  DonTraHang  where pk_seq = '" + id + "' ";
			ResultSet rs1=db.get(query);
			String npp="";
			if(rs1.next())
			{
				npp=rs1.getString("npp_fk");
			}
			rs1.close();
			/*query="update a set a.isnhapkhau=0 from "+
				"\n	DONTRAHANG_SP a inner join ufn_sanpham("+npp+") b  "+
				"\n	on a.sanpham_fk=b.sanpham_fk "+
				"\n	where b.isnhapkhau=1";
			if(!db.update(query))
			{
				msg = "Không thể cập nhật DONTRAHANG_SP " + query;
				db.getConnection().rollback();
				return msg;
			}*/
			
			
//			msg= util.Check_Kho_Tong_VS_KhoCT(npp_fk, db);
//			System.out.println("Check_Kho_Tong_VS_KhoCT"+msg);
//			if(msg.length()>0)
//			{
//				db.getConnection().rollback();
//				return msg;
//			}

			db.getConnection().commit();
			db.getConnection().setAutoCommit(true);
		}
		catch (Exception e) 
		{
			e.printStackTrace();
			db.update("rollback");
			db.shutDown();
			return "Exception: " + e.getMessage();
		}
		finally
		{
			if(db!=null)db.shutDown();
		}
		
		return "";
	}

	private String TaoHangTraLai_CapTren(String id,dbutils db,String userId ) throws SQLException
	{
		
		String  msg="";
		try
		{
			String query="Select count(*) as SoDong from Erp_HangTraLaiNpp nk inner join Dontrahang th on nk.dontrahang_fk=th.pk_seq where dontrahang_fk= "+id +" and nk.trangthai not in (2) ";
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
		
		 
	//TAO NHAP HANG CHO DOI TUONG NHAP
		
		query = 
	
		"	insert into Erp_HangTraLaiNpp(npp_fk,ngaytra,ghichu,kbh_fk,kho_fk,NgayTao,NgaySua,NguoiTao,NguoiSua,TrangThai,TienTruocThue,TienSauThue,TienThue,NppTra_FK,dontrahang_fk,DoiTuong)  "+
		"	 select tructhuoc_fk,NGAYTRA,N'Chi nhánh trả hàng',KBH_FK,KHO_FK,'' as NgayTao,'' as NgaySua,NGUOITAO,NGUOISUA,0 ,SOTIENBVAT,SOTIENBVAT,VAT,NPP_FK,PK_SEQ,0  "+
		"	 from DONTRAHANG	   a  "+
		"	 where a.PK_SEQ = '"+id+"' and trangthai in (0,1) "; 
		
		System.out.println("---INSERT ErpHangTraLaiNpp: " + query );
		if(!db.update(query))
		{
			msg = "Không tạo mới NHAPHANG " + query;
			return msg;
		}
		
		query = "select scope_identity() as pxkId";
		ResultSet	rs = db.get(query);
		rs.next();
		String	pxkId = rs.getString("pxkId");
		rs.close();
		
		query = 		
		"	insert into Erp_HangTraLaiNpp_SanPham(HangTraLai_fk,Sanpham_fk,Dvdl_Fk,SoLuong,DonGia,SoLo,NgayHetHan,VAT, KBH_FK, KHO_FK) "+
		"	select "+pxkId+" ,SANPHAM_FK,b.DVDL_FK,a.SOLUONG,a.DONGIA,a.SoLo,a.NgayHetHan,ptVat, c.KBH_FK, c.KHO_FK "+
		"	from DONTRAHANG_SP a inner join sanpham b on b.PK_SEQ=a.SANPHAM_FK" +
		"    inner join  DONTRAHANG c on c.pk_Seq=a.dontrahang_fk "+
		" where  c.pk_Seq='"+id+"' and c.trangthai in (0,1) ";
		System.out.println("---INSERT Erp_HangTraLaiNpp_SanPham: " + query );
		if(!db.update(query))
		{
			msg = "Không tạo mới Erp_HangTraLaiNpp_SanPham " + query;
			return msg;
		}
		
		query=
				" UPDATE hd SET TienSauThue=data.AVAT,TienTruocThue=data.BVAT,TienThue=data.VAT  "+
				" from   "+
			  "	(  "+
				"	select sum(round( ROUND(SoLuong*DonGia,0)*(1+VAT/100 ),0)) as AVAT,SUM(round(SoLuong*DonGia,0)) as BVAT,SUM(round(ROUND(SoLuong*DonGia,0)*(VAT/100),0)) as VAT ,HangTraLai_fk from Erp_HangTraLaiNpp_SanPham  "+ 
				"	group by HangTraLai_fk   "+
				" ) as data inner join Erp_HangTraLaiNpp hd on hd.pk_seq=data.HangTraLai_fk " +
				"  where data.HangTraLai_fk='"+pxkId+"'   ";
		if(!db.update(query))
		{
			msg = "Không tạo mới Erp_HangTraLaiNpp_SanPham " + query;
			return msg;
		}
		}
		catch(Exception ex)
		{
			ex.printStackTrace();
			return "Xảy ra lỗi khi tạo đơn nhận hàng tự động";
		}
		return msg;
	}
	
	private String TaoHangTraLai_HO(String id,String userId,dbutils  db )
	{
		String msg="";
		try
		{
		String query="Select count(*) as soDong from ERP_NHAPKHO nk inner join Dontrahang th on nk.dontrahang_fk=th.pk_seq where dontrahang_fk= "+id +" and nk.trangthai not in (2) ";
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
		
		query=
			"	insert into ERP_NHAPKHO(NgayNhap,KhoNhap_FK,TRANGTHAI,NGAYTAO,NGAYSUA,NGUOITAO,NGUOISUA,GHICHU,DonTraHang_fk,SoChungTu ) "+
			"	select NGAYTRA,100001,0,'"+getDateTime()+"' as NgayTao,'"+getDateTime()+"' as nsua,NGUOITAO,NGUOISUA,N'Xuất trả Hàng',PK_SEQ,SoHoaDon "+
			"	from DonTraHang where TrucThuoc_FK =1 and pk_Seq='"+id +"' "; 
		if(!db.update(query))
		{
			msg = "Không tạo mới ERP_NHAPKHO " + query;
			return msg;
		}
		
		query=
		"insert ERP_NHAPKHO_SANPHAM( nhapkho_fk, SANPHAM_FK, DVDL_FK, soluong, gianhap, solo, ngaysanxuat, ngayhethan ) "+
		"select SCOPE_IDENTITY(),a.pk_Seq as spId,DVDL_FK,soluong as SoLuong,0 as GiaNhap,b.solo as SoLo,  "+
		"	b.ngayhethan as NgaySanXua,b.ngayhethan as NgayHetHan  "+
		"from SANPHAM a inner join DonTraHang_SP b on a.PK_SEQ=b.SANPHAM_FK "+ 
		" where b.dontrahang_Fk='"+id+"' ";
		
		System.out.println("_ERP_NHAPKHO_SANPHAM"+query);
		
		if(!db.update(query))
		{
			msg = "Không tạo mới ERP_NHAPKHO_SANPHAM " + query;
			return msg;
		}
		}
		catch(Exception ex)
		{
			ex.printStackTrace();
			return "Gặp ra lỗi khi tạo tự động đơn nhập hàng trên trung tâm";
		}
		return msg;
	}
	
	private String DeleteChuyenKho(String lsxId, String userId)
	{
		dbutils db = new dbutils();
		String msg = "";

		Utility util = new Utility();
		msg = util.Check_Huy_NghiepVu_KhoaSo("DonTraHang", lsxId, "NgayTra", db);
		if (msg.length() > 0)
			return msg;

		try
		{
			db.getConnection().setAutoCommit(false);
			
			String query = "\n update DonTraHang set trangthai = '3', nguoisua = '" + userId + "', Modified_Date=getdate() " +
			"\n where pk_seq = '" + lsxId + "' and trangthai = 0 ";
			if (db.updateReturnInt(query) != 1)
			{
				msg = "Không thể xoá đơn trả hàng.";
				db.getConnection().rollback();
				return msg;
			}

			String dungchungKENH = "0";
			String sqlKENH = "100025";
			String npp_fk = "";

			query = "\n select isnull(dungchungkenh, 0) as dungchungkenh, pk_seq from NHAPHANPHOI " +
			"\n where pk_seq = ( select npp_fk from DonTraHang where pk_seq = '" + lsxId + "' )";
			ResultSet rsKENH = db.get(query);
			if (rsKENH != null)
			{
				if (rsKENH.next())
				{
					dungchungKENH = rsKENH.getString("dungchungkenh");
					npp_fk = rsKENH.getString("pk_seq");
				}
				rsKENH.close();
			}

			if (dungchungKENH.equals("1"))
				sqlKENH = " 100025 as kbh_fk "; //LAY KENH OTC
			else
				sqlKENH = " A.kbh_fk ";

			//TANG KHO NGUOC LAI
			query = "\n select a.ngaytra, b.ngaynhapkho, a.kho_fk as kho_fk, a.npp_fk, "+sqlKENH+", " +
			"\n b.sanpham_fk, b.solo, SUM(b.soluong) as tongxuat, NgayHetHan  " +
			"\n from DonTraHang a inner join DonTraHang_SP b on a.pk_seq = b.dontrahang_Fk " +
			"\n where dontrahang_Fk = '" + lsxId + "' " +
			"\n group by a.kho_fk, a.npp_fk, a.kbh_fk, b.solo, b.sanpham_fk, NgayHetHan, a.ngaytra, b.ngaynhapkho " ;
			ResultSet ckRs = db.get(query);
			while (ckRs.next())
			{
				String kho_fk = ckRs.getString("kho_fk");
				String nppId = ckRs.getString("npp_fk");	
				String kbh_fk = ckRs.getString("kbh_fk");
				String sanpham_fk = ckRs.getString("sanpham_fk");
				String solo = ckRs.getString("solo");
				Double tongxuat = ckRs.getDouble("tongxuat");
				String NgayHetHan = ckRs.getString("NgayHetHan");
				String ngaynhapkho = ckRs.getString("ngaynhapkho");
				String ngaytra = ckRs.getString("ngaytra");

				msg = util.Update_NPP_Kho_Sp_Chitiet(ngaytra, "Đơn trả hàng về NCC-DELETE", db, kho_fk, sanpham_fk, nppId, kbh_fk, solo, NgayHetHan, ngaynhapkho, 0.0, (-1)*tongxuat, tongxuat, tongxuat, 0.0);
				if (msg.length() > 0)
				{
					db.getConnection().rollback();
					return msg;
				}
				
				msg = util.Update_NPP_Kho_Sp(ngaytra, "Đơn trả hàng về NCC-DELETE", db, kho_fk, sanpham_fk, nppId, kbh_fk, 0.0,  (-1)*tongxuat, tongxuat, 0.0);
				if (msg.length() > 0)
				{
					db.getConnection().rollback();
					return msg;
				}
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
		
		finally
		{
			if (db!=null) db.shutDown();
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
		    geso.dms.distributor.util.Utility util = new geso.dms.distributor.util.Utility();
		    String action = request.getParameter("action");
		    if (action == null)
		    {
		    	action = "";
		    }
		    
			IDontrahangList obj = new DontrahangList();
		 
		   
		    userId = util.antiSQLInspection(request.getParameter("userId")); 
		    obj.setUserId(userId);
		    
		    obj.setNppId(util.getIdNhapp(userId));
		    
		    if(action.equals("Tao moi"))
		    {
		    	IDontrahang lsxBean = new Dontrahang();
		    	lsxBean.setUserId(userId);
		    	lsxBean.createRs();
	    		
		    	session.setAttribute("lsxBean", lsxBean);	    	
		    	session.setAttribute("kenhId", "");
		    	session.setAttribute("khoxuat", "" );
		    	session.setAttribute("nppId", lsxBean.getNppId());
	    		
		    	
	    		String nextJSP = request.getContextPath() + "/pages/Distributor/DonTraHangNew.jsp";
	    		response.sendRedirect(nextJSP);
		    }
		    else
		    {
		    	if(action.equals("view") || action.equals("next") || action.equals("prev"))
		    	{
			    	String search = getSearchQuery(request, obj);
			    	obj.setNxtApprSplitting(Integer.parseInt(util.antiSQLInspection(request.getParameter("nxtApprSplitting"))));
			    	obj.setUserId(userId);
			    	obj.init(search);
			    	obj.setAttribute(request, action, "list", "crrApprSplitting", "nxtApprSplitting");
			    	session.setAttribute("obj", obj);
			    	
			    	String nextJSP = request.getContextPath() + "/pages/Distributor/DonTraHang.jsp";
					response.sendRedirect(nextJSP);
			    }
		    	else
		    	{
			    	String search = getSearchQuery(request, obj);
			    	obj.init(search);
					obj.setUserId(userId);
					
			    	session.setAttribute("obj", obj);  	
		    		session.setAttribute("userId", userId);
			
		    		String nextJSP = request.getContextPath() + "/pages/Distributor/DonTraHang.jsp";
		    		response.sendRedirect(nextJSP);
		    		
		    	}
		    }
		}
	}
	
	private String getSearchQuery(HttpServletRequest request, IDontrahangList obj)
	{
		geso.dms.distributor.util.Utility util = new geso.dms.distributor.util.Utility();
		String query = 
						"	select a.pk_Seq,b.MA as nppMa,b.TEN as nppTen,a.NGAYTRA,c.TEN as nguoiTao,d.TEN as nguoiSua,e.TEN as tructhuoc,a.TRANGTHAI,a.SOTIENBVAT,a.Modified_Date,a.created_date "+
						"		from DONTRAHANG a inner join NHAPHANPHOI b on b.PK_SEQ=a.NPP_FK "+
						"		inner join NHANVIEN c on c.PK_SEQ=a.NGUOITAO  "+
						"		inner join NHANVIEN d on d.PK_SEQ=a.NGUOISUA "+
						"		inner join NHAPHANPHOI e on e.PK_SEQ=a.tructhuoc_Fk " +
						" where a.npp_fk='"+obj.getNppId()+"' ";
		
		String tungaySX = util.antiSQLInspection(request.getParameter("tungay"));
		if(tungaySX == null)
			tungaySX = "";
		obj.setTungayTao(tungaySX);
		
		String denngaySX = util.antiSQLInspection(request.getParameter("denngay"));
		if(denngaySX == null)
			denngaySX = "";
		obj.setDenngayTao(denngaySX);
		
		String sochungtu = util.antiSQLInspection(request.getParameter("sochungtu"));
		if(sochungtu == null)
			sochungtu = "";
		obj.setSochungtu(sochungtu);
		
		String trangthai = util.antiSQLInspection(request.getParameter("trangthai"));
		if(trangthai == null)
			trangthai = "";
		obj.setTrangthai(trangthai);
		
		String nppId = util.antiSQLInspection(request.getParameter("nppId"));
		if(nppId == null)
			nppId = "";
		obj.setNppId(nppId);
		
		String khId = util.antiSQLInspection(request.getParameter("khId"));
		if(khId == null)
			khId = "";
		obj.setKhId(khId);
		

		if(tungaySX.length() > 0)
			query += " and a.ngaytra >= '" + tungaySX + "'";
		
		if(denngaySX.length() > 0)
			query += " and a.ngaytra <= '" + denngaySX + "'";
	
		if(trangthai.length() > 0)
			query += " and a.TrangThai = '" + trangthai + "'";
		
		if(sochungtu.length() > 0)
			query += " and a.sohoadon like '%" + sochungtu + "%'";
		
		if(khId.length() > 0)
			query += " and  a.tructhuoc_fk = '" + khId + "'";
		
		if(nppId.length() > 0)
			query += " and a.NPP_FK = '" + nppId + "'";
		
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