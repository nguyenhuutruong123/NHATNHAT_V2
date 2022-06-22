package geso.dms.center.servlets.hangtralai;

import geso.dms.center.db.sql.dbutils;
import geso.dms.center.util.Utility;
import geso.dms.center.beans.hangtralai.IErpHangTraLaiNpp;
import geso.dms.center.beans.hangtralai.IErpHangTraLaiNppList;
import geso.dms.center.beans.hangtralai.imp.ErpHangTraLaiNpp;
import geso.dms.center.beans.hangtralai.imp.ErpHangTraLaiNppList;

import java.io.IOException;
import java.sql.ResultSet;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class ErpHangTraLaiTTSvl extends HttpServlet 
{
	private static final long serialVersionUID = 1L;
       
    public ErpHangTraLaiTTSvl() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		IErpHangTraLaiNppList obj;
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
		   
	    String lsxId = util.getId(querystring);
	    obj = new ErpHangTraLaiNppList();
	    obj.setUserId(userId);
	    
	    if (action.equals("delete") )
	    {	
	    	String msg = this.DeleteChuyenKho(lsxId, userId);
			obj.setMsg(msg);
	    }
	    else if(action.equals("chot"))
    	{
    		String msg = this.Chot(lsxId, userId);
			obj.setMsg(msg);
    	}
	    
	    obj.init("");
		session.setAttribute("obj", obj);
			
		String nextJSP = request.getContextPath() + "/pages/Center/ErpHangTraLaiNpp.jsp";
		response.sendRedirect(nextJSP);
	}

	private String Chot(String id, String userId)
	{
		dbutils db = new dbutils();
		String msg = "";
		
		Utility util = new Utility();
		
		msg= util.Check_Huy_NghiepVu_KhoaSo("Erp_HangTraLaiNpp", id, "NgayTra", db);
		if(msg.length()>0)
			return msg;
		String dontrahang_fk="0";
		try
		{
			db.getConnection().setAutoCommit(false);
			String 	query=
			"			select isnull(dathang.dontrahang_fk,0) as dontrahang_fk ,dathang.ngaytra,khoxuat_fk as kho_fk, npp_fk, kbh_fk, sp.PK_SEQ as spId, sp.TEN as spTEN , SUM(dathang.soluong) as soluongXUAT,   "+   
			"			SoLo as spSoLo,NgayHetHan  as spNgayHetHan  "+
			"		from        "+
			"		(        "+
			"			select c.ngaytra,c.dontrahang_fk,c.kho_fk as khoxuat_fk, c.npp_fk, c.kbh_fk, a.sanpham_fk, b.DVDL_FK as dvCHUAN,  "+      
			"					case when a.dvdl_fk IS null then a.soluong         "+
			"						 when a.dvdl_fk = b.DVDL_FK then a.soluong        "+  
			"						 else  a.soluong * ( select SOLUONG1 from QUYCACH "+
			"							where sanpham_fk = a.sanpham_fk and DVDL2_FK = a.dvdl_fk and dvdl1_fk = b.dvdl_fk )  "+       
			"										 / ( select SOLUONG2 from QUYCACH where sanpham_fk = a.sanpham_fk  "+
			"										  and DVDL2_FK = a.dvdl_fk and dvdl1_fk = b.dvdl_fk )	 end as soluong ,  "+
			"										  SoLo,a.NgayHetHan     "+
			"			from Erp_HangTraLaiNpp_sanpham a inner join SANPHAM b on a.sanpham_fk = b.PK_SEQ  "+   
			"					inner join Erp_HangTraLaiNpp c on a.HangTraLai_fk = c.pk_seq       "+
			"			where a.HangTraLai_fk in (   '"+id+"'   )      and a.SoLuong>0 "+
			"		)       "+
			"		dathang inner join SANPHAM sp on dathang.sanpham_fk = sp.PK_SEQ "+    
			"		group by khoxuat_fk, npp_fk, kbh_fk, sp.PK_SEQ, sp.TEN,SoLo,NgayHetHan,dathang.DonTraHang_FK,ngaytra  " ;
				System.out.println(":::::::::::::::::::::::::::::::"+query);		
			ResultSet rs =db.get(query);
			String nppId = "";
			while(rs.next())
			{
				String spId = rs.getString("spId");
				String kbhId = rs.getString("kbh_fk");
				String khoId = rs.getString("kho_fk");
				nppId = rs.getString("npp_fk");
				String spTen = rs.getString("spTEN");
				String ngaytra = rs.getString("ngaytra");
				String spSoLo = rs.getString("spSoLo");
				String spNgayHetHan = rs.getString("spNgayHetHan");
				 dontrahang_fk= rs.getString("dontrahang_fk");
				double SoLuong = rs.getDouble("soluongXUAT");
				
				if(SoLuong==0)
				{
					msg="Sản phẩm "+spTen +" chưa khai báo quy cách !"; 
					db.getConnection().rollback();
					return msg;
				}
				
				
//				query = "update NHAPP_KHO set SOLUONG = SOLUONG + '" + SoLuong + "', AVAILABLE = AVAILABLE + '" + SoLuong + "' " +
//						"where npp_fk='"+nppId+"' and kbh_fk='"+kbhId+"' and kho_fk='"+khoId+"' and sanpham_fk='"+spId+"' ";
//				if(db.updateReturnInt(query)!=1)
//				{
//					msg="Lỗi phát sinh khi cập nhật ! "+query; 
//					db.getConnection().rollback();
//					return msg;
//				}
//				
				// LẤY NGÀY TRẢ LÀ NGÀY NHẬP KHO
				query="Update Erp_HangTraLaiNpp_sanpham set ngaynhapkho= '" +ngaytra+"' where HangTraLai_fk in ('"+id +"') ";
				if(db.updateReturnInt(query)<=0)
					{
						msg="Lỗi phát sinh khi cập nhật ! "+query; 
						db.getConnection().rollback();
						return msg;
					}
				msg=util.Update_NPP_Kho_Sp(ngaytra, "Nhận hàng trả lại", db, khoId, spId, nppId, kbhId, SoLuong, 0.0, SoLuong, 0.0);
				if(msg.length()>0)
				{
					db.getConnection().rollback();
					return msg;
				}
				
				msg= util.Update_NPP_Kho_Sp_Chitiet(ngaytra, "Nhận hàng trả lại", db, khoId, spId, nppId, kbhId, spSoLo, spNgayHetHan, ngaytra, SoLuong, 0.0, SoLuong, SoLuong, 0.0);
				if(msg.length()>0)
				{
					db.getConnection().rollback();
					return msg;
				}
				
//				query = 
//						"select COUNT(*) as soDONG " +
//						"from NHAPP_KHO_CHITIET where npp_fk='"+nppId+"' and kbh_fk='"+kbhId+"' and  Kho_fk='"+khoId+"' and sanpham_fk='"+spId+"' and SoLo='"+spSoLo +"' and NgayHetHan='"+spNgayHetHan+"' ";
//				System.out.println("CHECK EXITS: " + query);
//				
//				ResultSet rsCHECK = db.get(query);
//				int exits = 0;
//				if(rsCHECK != null)
//				{
//					if(rsCHECK.next())
//					{
//						exits = rsCHECK.getInt("soDONG");
//					}
//					rsCHECK.close();
//				}
//				
//				if(exits <= 0)
//				{
//					query = 
//							"insert NHAPP_KHO_CHITIET(KHO_FK, NPP_FK, SANPHAM_FK, SOLUONG, BOOKED, AVAILABLE, KBH_FK, SOLO, NGAYHETHAN) " +
//							"select '"+khoId+"','"+nppId+"','"+spId+"','"+SoLuong+"',0,'"+SoLuong+"','"+kbhId+"','"+spSoLo+"','"+spNgayHetHan+"' ";
//				}
//				else
//				{
//					query = " update CT  " +
//									" set CT.SOLUONG = CT.SOLUONG + NH.soluongNHAN, " +
//									" 			  CT.AVAILABLE = CT.AVAILABLE + NH.soluongNHAN, " +
//									" 			  CT.NGAYHETHAN = NH.NGAYHETHAN " +
//									" from " +
//									" ( " +
//									" select  '"+khoId+"' as kho_fk, '"+nppId+"' as   NPP_FK, '"+spId+"' as  SANPHAM_FK,'"+SoLuong+"' as  soluongNHAN, '"+kbhId+"' as   KBH_FK, '"+spSoLo+"'  as SOLO, '"+spNgayHetHan+"' as   NGAYHETHAN " +
//									" ) " +
//									" NH inner join NHAPP_KHO_CHITIET CT on NH.kho_fk = CT.KHO_FK and NH.NPP_FK = CT.NPP_FK and NH.KBH_FK = CT.KBH_FK and NH.SOLO = CT.SOLO and NH.SANPHAM_FK = CT.sanpham_fk and ct.NgayHetHan=NH.NgayHetHan ";
//				}	
//				
//				if(db.updateReturnInt(query)!=1)
//				{
//					msg="Lỗi phát sinh khi cập nhật "+query; 
//					db.getConnection().rollback();
//					return msg;
//				}
			}
			query = "update Erp_HangTraLaiNpp set trangthai = '1' where pk_seq = '" + id + "' and TrangThai=0 ";
			if(db.updateReturnInt(query)!=1)
			{
				msg="Lỗi phát sinh khi cập nhật "+query; 
				db.getConnection().rollback();
				return msg;
			}
			
			
			/*msg= util.Check_Kho_Tong_VS_KhoCT(nppId, db);
			if(msg.length()>0)
			{
				db.getConnection().rollback();
				return msg;
			}*/
			
			
			if(!dontrahang_fk.equals("0"))
			{
				System.out.println("vao day rùi................"+dontrahang_fk);
				query =
						" select count(*)   as SoDong,a.TrucThuoc_FK ,c.DUNGCHUNGKENH, a.npp_fk " +
						" from DonTraHang a inner join DonTraHang_SP b on a.pk_seq = b.dontrahang_Fk " +
						" 		inner join NHAPHANPHOI c on c.PK_SEQ=a.NPP_FK " +
						" where dontrahang_Fk = '" + dontrahang_fk + "' and a.trangthai in (0,1) " +
						" group by a.TrucThuoc_FK, c.DUNGCHUNGKENH, a.npp_fk " ;
				
				ResultSet rs1 = db.get(query);
				String dungchungKENH = "0";
				 while(rs1.next())
				 {
					
					 dungchungKENH = rs1.getString("dungchungkenh");
				
				 }
				 String sqlKENH = "";
				 if(dungchungKENH.equals("1"))
						sqlKENH = " 100025 as kbh_fk "; //LAY KENH OTC
					else
						sqlKENH = " A.kbh_fk ";
				 
				 	query=" 	select a.ngaytra,b.ngaynhapkho,a.kho_fk as kho_fk, a.npp_fk, "+sqlKENH+", b.sanpham_fk, b.solo, SUM(b.soluong) as tongxuat,b.NgayHetHan  " +
					" 	from DonTraHang a inner join DonTraHang_SP b on a.pk_seq = b.dontrahang_Fk " +
					" 	where dontrahang_Fk = '" + dontrahang_fk + "' " +
					" 	group by a.kho_fk, a.npp_fk, a.kbh_fk, b.solo, b.sanpham_fk,b.NgayHetHan,a.ngaytra,b.ngaynhapkho " ;
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
						String ngaytra=nkRs.getString("ngaytra");
						
						msg=util.Update_NPP_Kho_Sp_Chitiet(ngaytra, "Nhập hàng trả lại", db, kho_fk, sanpham_fk, npp_fk, kbh_fk, solo, NgayHetHan, ngaynhapkho, (-1)*tongxuat, (-1)*tongxuat, 0.0, 0.0, 0.0);
						if(msg.length()>0)
						{
							db.getConnection().rollback();
							System.out.println("loi nay" +msg);
							return msg;
						}
						msg=util.Update_NPP_Kho_Sp(ngaytra, "Nhập hàng trả lại", db, kho_fk, sanpham_fk, npp_fk, kbh_fk, (-1)*tongxuat,  (-1)*tongxuat, 0.0, 0.0);
						if(msg.length()>0)
						{
							db.getConnection().rollback();
							return msg;
						}
				 	
			
				    }
				 	
//					query = " update kho set kho.SOLUONG = kho.SOLUONG - CT.tongxuat, " +
//							" 			   kho.BOOKED = kho.BOOKED - CT.tongxuat " +
//							" from " +
//							" ( " +
//							" 	select a.kho_fk as kho_fk, a.npp_fk, "+sqlKENH+", b.sanpham_fk, b.solo, SUM(b.soluong) as tongxuat,b.NgayHetHan  " +
//							" 	from DonTraHang a inner join DonTraHang_SP b on a.pk_seq = b.dontrahang_Fk " +
//							" 	where dontrahang_Fk = '" + dontrahang_fk + "' " +
//							" 	group by a.kho_fk, a.npp_fk, a.kbh_fk, b.solo, b.sanpham_fk,b.NgayHetHan " +
//							" ) " +
//							" CT inner join NHAPP_KHO_CHITIET kho on CT.kho_fk = kho.KHO_FK  " +
//							" 	and CT.sanpham_fk = kho.SANPHAM_FK and CT.solo = kho.SOLO and CT.NPP_FK = kho.npp_fk and CT.KBH_FK = kho.kbh_fk and kho.NgayHetHan=ct.NgayHetHan  ";
//					System.out.println("---TANG KHO NGUOC LAI: " + query );
//					if(db.updateReturnInt(query)<=0)
//					{
//						msg = "Không thể cập nhật NHAPP_KHO_CHITIET " + query;
//						db.getConnection().rollback();
//						return msg;
//					}
//					
//					
//					query = " update kho set kho.SOLUONG = kho.SOLUONG - CT.tongxuat, " +
//							" 			   kho.BOOKED = kho.BOOKED - CT.tongxuat " +
//							" from " +
//							" ( " +
//							" 	select a.kho_fk as kho_fk, a.npp_fk, "+sqlKENH+" , b.sanpham_fk, SUM(b.soluong) as tongxuat  " +
//							" 	from DonTraHang a inner join DonTraHang_SP b on a.pk_seq = b.dontrahang_Fk " +
//							" 	where dontrahang_Fk = '" + dontrahang_fk + "' " +
//							" 	group by a.kho_fk, a.npp_fk, a.kbh_fk, b.sanpham_fk " +
//							" ) " +
//							" CT inner join NHAPP_KHO kho on CT.kho_fk = kho.KHO_FK  " +
//							" 	and CT.sanpham_fk = kho.SANPHAM_FK and CT.NPP_FK = kho.npp_fk and CT.KBH_FK = kho.kbh_fk ";
//					System.out.println("---TANG KHO NGUOC LAI 2: " + query );
//					if(db.updateReturnInt(query)<=0)
//					{
//						msg = "Không thể cập nhật NHAPP_KHO " + query;
//						db.getConnection().rollback();
//						return msg;
//					}
			}
			
			query = "update DonTraHang set trangthai = '2' where pk_seq = '" + dontrahang_fk + "'";
			if(!db.update(query))
			{
				msg = "Khong the chot: " + query;
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

	private String DeleteChuyenKho(String lsxId, String userId)
	{ 
		Utility util = new Utility();
		dbutils db = new dbutils();
		String msg = "";
		try
		{
			
			
			db.getConnection().setAutoCommit(false);
			
			String query="select dontrahang_fk from Erp_HangTraLaiNpp where pk_seq ='"+lsxId+"' ";
			ResultSet dthRs=db.get(query);
			String dthId="";
			if(dthRs.next())
			{
					dthId=dthRs.getString("dontrahang_fk");
				
					System.out.println("vao day rùi................"+dthId);
					query =
							" select count(*)   as SoDong,a.TrucThuoc_FK ,c.DUNGCHUNGKENH, a.npp_fk " +
							" from DonTraHang a inner join DonTraHang_SP b on a.pk_seq = b.dontrahang_Fk " +
							" 		inner join NHAPHANPHOI c on c.PK_SEQ=a.NPP_FK " +
							" where dontrahang_Fk = '" + dthId + "' and a.trangthai in (0,1) " +
							" group by a.TrucThuoc_FK, c.DUNGCHUNGKENH, a.npp_fk " ;
					
					ResultSet rs1 = db.get(query);
					String dungchungKENH = "0";
					 while(rs1.next())
					 {
						
						 dungchungKENH = rs1.getString("dungchungkenh");
					
					 }
					 String sqlKENH = "";
					 if(dungchungKENH.equals("1"))
							sqlKENH = " 100025 as kbh_fk "; //LAY KENH OTC
						else
							sqlKENH = " A.kbh_fk ";
					 
					 	query=	" 	select a.ngaytra,b.ngaynhapkho,a.kho_fk as kho_fk, a.npp_fk, "+sqlKENH+", b.sanpham_fk, b.solo, SUM(b.soluong) as tongxuat,b.NgayHetHan  " +
					 			" 	from DonTraHang a inner join DonTraHang_SP b on a.pk_seq = b.dontrahang_Fk " +
					 			" 	where dontrahang_Fk = '" + dthId + "' " +
					 			" 	group by a.kho_fk, a.npp_fk, a.kbh_fk, b.solo, b.sanpham_fk,b.NgayHetHan, a.ngaytra,b.ngaynhapkho " ;
					 	
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
							String ngaytra=nkRs.getString("ngaytra");
							
							msg=util.Update_NPP_Kho_Sp_Chitiet(ngaytra, "Đơn trả hàng về NCC-TT duyệt", db, kho_fk, sanpham_fk, npp_fk, kbh_fk, solo, NgayHetHan, ngaynhapkho, 0.0, (-1)*tongxuat, tongxuat, tongxuat, 0.0);
							if(msg.length()>0)
							{
								db.getConnection().rollback();
								System.out.println("loi nay" +msg);
								return msg;
							}
							msg=util.Update_NPP_Kho_Sp(ngaytra, "Đơn trả hàng về NCC-UPDATE", db, kho_fk, sanpham_fk, npp_fk, kbh_fk, 0.0,  (-1)*tongxuat, tongxuat, 0.0);
							if(msg.length()>0)
							{
								db.getConnection().rollback();
								return msg;
							}
					 	
				
					 		}
			}
			
			
			query = "delete from [TraHang_SoChungTu] where [Id_FK]='"+lsxId+"' and [tableName] = 'Erp_HangTraLaiNpp' ";
			if(!db.update(query))
			{
				msg = "Không thể xóa " + query;
				db.getConnection().rollback();
				return msg;
			}
			
			query = "delete from Erp_HangTraLaiNpp_sanpham where HangTraLai_fk='"+lsxId+"' ";
			if(!db.update(query))
			{
				msg = "Không thể xóa " + query;
				db.getConnection().rollback();
				return msg;
			}
						
			query = "delete from Erp_HangTraLaiNpp where pk_seq='"+lsxId+"' and Trangthai=0 ";
			if(db.updateReturnInt(query)!=1)
			{
				msg = "Không thể cập nhật Erp_HangTraLaiNpp " + query;
				db.getConnection().rollback();
				return msg;
			}			
			db.getConnection().commit();
			db.getConnection().setAutoCommit(true);
			db.shutDown();
		}
		catch (Exception e) 
		{
			db.update("rollback");
			db.shutDown();
			e.printStackTrace();
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
	    
	    String action = request.getParameter("action");
	    if (action == null)
	    {
	    	action = "";
	    }
	    
		IErpHangTraLaiNppList obj = new ErpHangTraLaiNppList();
	 
	    Utility util = new Utility();
	    
		HttpSession session = request.getSession();
	    String userId = util.antiSQLInspection(request.getParameter("userId")); 
	    
	    if(action.equals("Tao moi"))
	    {
	    	IErpHangTraLaiNpp lsxBean = new ErpHangTraLaiNpp();
	    	lsxBean.setUserId(userId);
	    	lsxBean.createRs();
	    	session.setAttribute("lsxBean", lsxBean);	    	
	    	session.setAttribute("kenhId", "");
	    	session.setAttribute("khoxuat", "" );
	    	session.setAttribute("nppId", lsxBean.getNppId());
    		String nextJSP = request.getContextPath() + "/pages/Center/ErpHangTraLaiNppNew.jsp";
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
		    	
		    	String nextJSP = request.getContextPath() + "/pages/Center/ErpHangTraLaiNpp.jsp";
				response.sendRedirect(nextJSP);
		    }
	    	else
	    	{
		    	String search = getSearchQuery(request, obj);
		    	obj.init(search);
				obj.setUserId(userId);
				
		    	session.setAttribute("obj", obj);  	
	    		session.setAttribute("userId", userId);
		
	    		String nextJSP = request.getContextPath() + "/pages/Center/ErpHangTraLaiNpp.jsp";
	    		response.sendRedirect(nextJSP);
	    		
	    	}
	    }
	}
	
	private String getSearchQuery(HttpServletRequest request, IErpHangTraLaiNppList obj)
	{
		String query =  "select a.sonetId,a.PK_SEQ, a.trangthai, a.ngaychuyen, a.ghichu as lydo, NV.TEN as nguoitao, b.ten as khoxuat, c.ten as nppTEN, a.NGAYSUA, a.NGAYTAO, NV2.TEN as nguoisua   " +
						"from ERP_CHUYENKHONPP a inner join KHO b on a.khoxuat_fk = b.pk_seq inner join NHAPHANPHOI c on a.npp_dat_fk = c.pk_seq  " +
						"inner join NHANVIEN nv on a.NGUOITAO = nv.PK_SEQ   " +
						"inner join NHANVIEN nv2 on a.NGUOISUA = nv2.PK_SEQ where a.pk_seq > 0 ";
		
		String tungaySX = request.getParameter("tungay");
		if(tungaySX == null)
			tungaySX = "";
		obj.setTungayTao(tungaySX);
		
		String denngaySX = request.getParameter("denngay");
		if(denngaySX == null)
			denngaySX = "";
		obj.setDenngayTao(denngaySX);
		
		String trangthai = request.getParameter("trangthai");
		if(trangthai == null)
			trangthai = "";
		obj.setTrangthai(trangthai);
		
		String nppId = request.getParameter("nppId");
		if(nppId == null)
			nppId = "";
		obj.setNppId(nppId);
		

		if(tungaySX.length() > 0)
			query += " and a.ngaychuyen >= '" + tungaySX + "'";
		
		if(denngaySX.length() > 0)
			query += " and a.ngaychuyen <= '" + denngaySX + "'";
	
		if(trangthai.length() > 0)
			query += " and a.TrangThai = '" + trangthai + "'";
		
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
