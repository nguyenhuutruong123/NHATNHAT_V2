package geso.dms.center.servlets.dieuchinhtonkho;

import geso.dms.center.beans.dieuchinhtonkho.IErpChuyenkhoTT;
import geso.dms.center.beans.dieuchinhtonkho.IErpChuyenkhoTTList;
import geso.dms.center.beans.dieuchinhtonkho.imp.ErpChuyenkhoTT;
import geso.dms.center.beans.dieuchinhtonkho.imp.ErpChuyenkhoTTList;
import geso.dms.center.db.sql.dbutils;
import geso.dms.center.util.Utility;

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

public class ErpChuyenkhoTTSvl extends HttpServlet 
{
	private static final long serialVersionUID = 1L;
       
    public ErpChuyenkhoTTSvl() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		IErpChuyenkhoTTList obj;
		request.setCharacterEncoding("UTF-8");
	    response.setCharacterEncoding("UTF-8");
	    response.setContentType("text/html; charset=UTF-8");
	    	    
	    HttpSession session = request.getSession();	    
	    String userId = (String) session.getAttribute("userId");  
		String userTen = (String) session.getAttribute("userTen");  	
		String sum = (String) session.getAttribute("sum"); 

	    Utility util = new Utility();
	    
	    
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
	    
	    geso.dms.center.util.Utility cutil = (geso.dms.center.util.Utility) session.getAttribute("util");
	    
	    if(!cutil.check(userId, userTen, sum)){
			response.sendRedirect(request.getContextPath() + "/redirect.jsp");
		}
	    else{
	    	String querystring = request.getQueryString();
		    userId = util.getUserId(querystring);
		    
		    if (userId.length()==0)
		    	userId = util.antiSQLInspection(request.getParameter("userId"));
		    
		    String action = util.getAction(querystring);
		   	   
		    String lsxId = util.getId(querystring);
		    obj = new ErpChuyenkhoTTList();
		    String loaidonhang = request.getParameter("loaidonhang");
		    if(loaidonhang == null)
		    	loaidonhang = "0";
		    obj.setLoaidonhang(loaidonhang);
		    System.out.println("---LOAI DON HANG: " + loaidonhang);
		    
		    obj.setUserId(userId);   
		    if (action.equals("delete") )
		    {	
		    	String msg = this.DeleteChuyenKho(lsxId, userId,loaidonhang);
				obj.setMsg(msg);
		    }
		    else if(action.equals("chot"))
	    	{
	    		String msg = this.Chot(lsxId, userId);
				obj.setMsg(msg);
	    	}
		    
		    obj.init("");
			session.setAttribute("obj", obj);
				
			String nextJSP = request.getContextPath() + "/pages/Center/ErpChuyenKhoTT.jsp";
			response.sendRedirect(nextJSP);
	    }
	    	    
	    
	}

	private String Chot(String id, String userId)
	{
		dbutils db = new dbutils();
		String msg = "";
		try
		{
			String SoChungTu="";
			String query="select COUNT(*) as sl from ERP_CHUYENKHO_SANPHAM where chuyenkho_fk="+id;
			ResultSet rschecksodong=db.get(query);
			rschecksodong.next();
			if(rschecksodong.getInt("sl")>18)
			{
				msg = "đơn hàng này vượt quá 18 dòng.";
				return msg;
			}
			rschecksodong.close();
			query = "select isnull(SoChungTu,'') as SoChungTu From Erp_ChuyenKho where pk_seq = '" + id + "'";
			ResultSet rs =db.get(query);
		
			while(rs.next())
			{
				SoChungTu=	rs.getString("SoChungTu");
			}
			if(SoChungTu.trim().length()<=0)
			{
				msg="Vui lòng nhập số chứng từ !";
			}
			if(msg.length()>0)
			{
				return msg;
			}
			
			db.getConnection().setAutoCommit(false);	
			
			/*query = "update b set b.ngayhethan = ( select (case when b.solo='NA' then '2030-12-31' else ngayhethan end) from ERP_KHOTT_SP_CHITIET where khott_fk = a.khoxuat_fk and sanpham_fk = b.sanpham_fk and solo = b.solo  and ngayhethan=(case when b.solo='NA' then '2030-12-31' else ngayhethan end) ) " +
					"from ERP_CHUYENKHO a inner join ERP_CHUYENKHO_SANPHAM_CHITIET b on a.pk_seq = b.chuyenkho_fk  " +
					"where chuyenkho_fk = '" + id + "'";
			if(!db.update(query))
			{
				System.out.println("Không thể cập nhật ERP_CHUYENKHO " + query);
				msg = "Không thể cập nhật ERP_CHUYENKHO " + query;
				db.getConnection().rollback();
				return msg;
			}*/
			
			
			//tru kho ne
			 query = " select CT.kho_fk,CT.SANPHAM_FK,CT.solo,CT.ngayhethan,CT.NGAYNHAPKHO,CT.tongxuat "+
					" from " +
					" ( " +
					" 	select a.khoxuat_fk as kho_fk, b.sanpham_fk, b.solo, SUM(b.soluong) as tongxuat,b.ngayhethan,b.ngaynhapkho  " +
					" 	from ERP_CHUYENKHO a inner join ERP_CHUYENKHO_SANPHAM_CHITIET b on a.pk_seq = b.chuyenkho_fk " +
					" 	where chuyenkho_fk = '" + id + "' " +
					" 	group by a.khoxuat_fk, b.solo, b.sanpham_fk,b.ngayhethan,b.ngaynhapkho " +
					" ) " +
					" CT inner join ERP_KHOTT_SP_CHITIET kho on CT.kho_fk = kho.KHOTT_FK  " +
					" 	and CT.sanpham_fk = kho.SANPHAM_FK and CT.solo = kho.SOLO and CT.ngayhethan=kho.ngayhethan and CT.ngaynhapkho=kho.ngaynhapkho";
			System.out.println("kho chi tiet "+query);
			 ResultSet rs1=db.get(query);
			 if(rs1!=null)
			 {
				 int check=0;
				 while(rs1.next())
				 {
					 check++;
					 String _kho_fk=rs1.getString("kho_fk");
					 String _SANPHAM_FK=rs1.getString("sanpham_fk");
					 String _solo = rs1.getString("solo");
					 String _ngayhethan= rs1.getString("ngayhethan");
					 String _NGAYNHAPKHO= rs1.getString("ngaynhapkho");
					 String _tongxuat=rs1.getString("tongxuat");
					 
					 query="select count(*) as kq from  ERP_KHOTT_SP_CHITIET where (soluong -  '" + _tongxuat + "'< 0 or AVAILABLE -   '" + _tongxuat + "'< 0   ) and KHOTT_FK = '" + _kho_fk + "' and SOLO = '" + _solo + "' and SANPHAM_FK = '" + _SANPHAM_FK + "'  and ngayhethan='"+_ngayhethan+"' and ngaynhapkho='"+_NGAYNHAPKHO+"'  ";
					 ResultSet rskhotong=db.get(query);
					 rskhotong.next();
					 if(rskhotong.getInt("kq")>0)
					 {
						 msg = "không đủ tồn để chốt sản phẩm vui lòng kiểm tra lại";
							System.out.println("query    "+query);
							rskhotong.close();
							db.getConnection().rollback();
							return msg;
					 }
					 rskhotong.close();
					 query = "Update ERP_KHOTT_SP_CHITIET set  soluong = soluong - '" + _tongxuat + "' , AVAILABLE=AVAILABLE-  '" + _tongxuat + "' " +
								"where KHOTT_FK = '" + _kho_fk + "' and SOLO = '" + _solo + "' and SANPHAM_FK = '" + _SANPHAM_FK + "'  and ngayhethan='"+_ngayhethan+"' and ngaynhapkho='"+_NGAYNHAPKHO+"'  ";
					 System.out.println("update kho chi tiet "+query);
					 if(db.updateReturnInt(query)!=1)
						{
						 msg = "Không thể cập nhật ERP_KHOTT_SP_CHITIET " + query;
							System.out.println("query    "+query);
							db.getConnection().rollback();
							return msg;
						}
					 
					 query = "Update ERP_KHOTT_SANPHAM set soluong = soluong - '" + _tongxuat + "' , AVAILABLE=AVAILABLE-  '" + _tongxuat + "' " +
								"where KHOTT_FK = '" + _kho_fk + "' and SANPHAM_FK = '" + _SANPHAM_FK + "'  ";
					 System.out.println("update kho  "+query);
					 if(!db.update(query))
						{
							 msg = "Không thể cập nhật ERP_KHOTT_SANPHAM " + query;
								System.out.println("query    "+query);
								db.getConnection().rollback();
								return msg;
						}
				 }
				 if(check==0)
				 {
					 msg = "sản phẩm có lô không tồn tại ";
						System.out.println("query    "+query);
						db.getConnection().rollback();
						return msg;
				 }
					 
			 }
			 else
			 {
				 msg = "Không thể cập nhật ERP_KHOTT_SP_CHITIET " + query;
					System.out.println("query    "+query);
					db.getConnection().rollback();
					return msg;
			 }
			
			
			
			
			
			
			//CAP NHAT LAI SOLO
			
			//XET XEM NHA PHAN PHOI NHAN CO DUNG CHUNG KENH HAY KHONG, NEU CO THI CHUYEN HANG VE OTC
			String dungchungKENH = "0";
			String sqlKENH = "";
			
			query = "select isnull(dungchungkenh, 0) as dungchungkenh from NHAPHANPHOI " +
					"where pk_seq = ( select npp_fk from ERP_CHUYENKHO where pk_seq = '" + id + "' )";
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
				sqlKENH = " kbh_fk ";
			
			query = " insert NHAPHANG(KHO_FK,NGAYNHAN, NGAYCHUNGTU, NPP_FK, NCC_FK, GSBH_FK, ASM_FK, BM_FK, DVKD_FK, KBH_FK, CHUYENKHO_FK, TRANGTHAI, NGUOITAO, NGAYTAO, NGUOISUA, NGAYSUA) " +
					"select distinct a.KhoXuat_FK,NgayChuyen, NgayChuyen, NPP_FK,   " +
					" 			( select top(1) NCC_FK from NHACUNGCAP_DVKD where PK_SEQ in ( select NCC_DVKD_FK from NHAPP_NHACC_DONVIKD where NPP_FK = a.NPP_FK ) ) as NCC_FK,  " +
					"			( select top(1) GSBH_FK from NHAPP_GIAMSATBH where NPP_FK = a.NPP_FK  ) as GSBH_FK,  " +
					"			( select top(1) ASM_FK from ASM_KHUVUC where KHUVUC_FK in ( select KHUVUC_FK from NHAPHANPHOI where PK_SEQ = a.NPP_FK )) as ASM_FK,  " +
					"			( select top(1) BM_FK from BM_CHINHANH where VUNG_FK in ( select VUNG_FK from KHUVUC where PK_SEQ in (  select KHUVUC_FK from NHAPHANPHOI where PK_SEQ = a.NPP_FK ) ) ) as BM_FK,  " +
					" 	   '100001' as DVKD_FK, " + sqlKENH + ", '" + id + "', '0', '" + userId + "', '" + getDateTime() + "', '" + userId + "', '" + getDateTime() + "'  " +
					" from ERP_CHUYENKHO a  " +
					" where a.PK_SEQ = '" + id + "' ";
			
			System.out.println("---INSERT NHAPHANG: " + query );
			if(!db.update(query))
			{
				msg = "Không tạo mới NHAPHANG " + query;
				db.getConnection().rollback();
				return msg;
			}
			/// tao chungws tu
			query = " select ngaychungtu,NPP_FK,PK_SEQ from NhapHang where CHUYENKHO_FK = " + id ;
			ResultSet rsNhapHang = db.get(query);
			if(rsNhapHang.next())
			{
			//ghi số chứng từ
				
				String _ngaychungtu = rsNhapHang.getString("ngaychungtu");
				String _NPP_FK = rsNhapHang.getString("NPP_FK");
				String _nhaphangId = rsNhapHang.getString("PK_SEQ");
				int sonetInt =  Utility.getChungTuSonet( db ,userId,_NPP_FK,"PhieuNhap_SoChungTu","month('"+_ngaychungtu+"')","year('"+_ngaychungtu+"')", _nhaphangId,"NHAPHANG" );
			    if(sonetInt <=0)
			    {
			    	db.getConnection().rollback();
					return "Không thể lấy số chứng từ tự động";
					
			    }
			    query = Utility.UpdateChungTuSoNet( "PhieuNhap_SoChungTu",_NPP_FK, _nhaphangId, _ngaychungtu,  sonetInt,  "NHAPHANG", "PN-"  );		
			    if(db.updateReturnInt(query)<=0)
				{
					db.getConnection().rollback();
					return "Số chứng từ ("+sonetInt+") đã phát sinh trong đơn gần nhất, vui lòng thử lại!";
				}
			    query = "\n UPDATE 	NHAPHANG set sonetId =  'PN-' + dbo.[PlusZero]("+sonetInt+",5)  " +
						"\n WHERE 	pk_seq = "+ _nhaphangId;
				if(!db.update(query))
				{
					
					db.getConnection().rollback();
					return "Không thể ghi nhận so chung tu " + query;
				}
				rsNhapHang.close();
			}
			else
			{
				rsNhapHang.close();
				db.getConnection().rollback();
				return "Không thể ghi nhận so chung tu phieu nhan " + query;
			}
			
			
			
			query = " insert NHAPHANG_SP(KHONHAN_FK,NHAPHANG_FK, SANPHAM_FK, SOLUONG, soluongNHAN, DONGIA, CHIETKHAU, DVDL_FK, LOAI, SCHEME, SOLO, NGAYHETHAN) " +
					" select  a.KhoXuat_FK,( select pk_seq from NHAPHANG where CHUYENKHO_FK = a.PK_SEQ  ),  " +
					" 		b.sanpham_fk, b.soluong, NULL, 0 as dongia, 0 as chietkhau, c.DVDL_FK, case when a.loaidonhang=4 then 1 else 0 end  as LOAI, '' as SCHEME, b.solo, b.ngayhethan " +
					" from ERP_CHUYENKHO a inner join ERP_CHUYENKHO_SANPHAM_CHITIET b on a.PK_SEQ = b.chuyenkho_fk " +
					" 		inner join SANPHAM c on b.sanpham_fk = c.PK_SEQ   " +
					" where a.PK_SEQ = '" + id + "' and b.soluong > 0 ";
			System.out.println("---INSERT NHAPHANG - SP: " + query );
			if(!db.update(query))
			{
				msg = "Không tạo mới NHAPHANG_SP " + query;
				db.getConnection().rollback();
				return msg;
			}
			
			query = "insert NHAPHANG_DDH(nhaphang_fk, ddh_fk)  " +
					"select ( select PK_SEQ from NHAPHANG where CHUYENKHO_FK = '" + id + "' ) as nhID, ddh_fk  " +
					"from ERP_CHUYENKHO where pk_seq = '" + id + "'";
			if(!db.update(query))
			{
				msg = "Không tạo mới NHAPHANG_DDH " + query;
				db.getConnection().rollback();
				return msg;
			}
			
			query = "update ERP_ChuyenKho set trangthai = '1' where pk_seq = '" + id + "'";
			if(!db.update(query))
			{
				msg = "3.Khong the chot: " + query;
				db.getConnection().rollback();
				return msg;
			}
			
			query = "update ERP_DONDATHANG set trangthai = '4' where pk_seq = ( select ddh_fk from ERP_CHUYENKHO where pk_seq = '" + id + "' ) ";
			if(!db.update(query))
			{
				msg = "3.Khong the chot: " + query;
				db.getConnection().rollback();
				return msg;
			}
			db.getConnection().commit();
			db.getConnection().setAutoCommit(true);
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

	private String DeleteChuyenKho(String lsxId, String userId,String loaidonhang)
	{
		dbutils db = new dbutils();
		String msg = "";
		try
		{
			db.getConnection().setAutoCommit(false);

			
			String query = " select CT.kho_fk,CT.SANPHAM_FK,CT.solo,CT.ngayhethan,CT.NGAYNHAPKHO,CT.tongxuat "+
						" from " +
						" ( " +
						" 	select a.khoxuat_fk as kho_fk, b.sanpham_fk, b.solo, SUM(b.soluong) as tongxuat,b.ngayhethan,b.ngaynhapkho  " +
						" 	from ERP_CHUYENKHO a inner join ERP_CHUYENKHO_SANPHAM_CHITIET b on a.pk_seq = b.chuyenkho_fk " +
						" 	where chuyenkho_fk = '" + lsxId + "' " +
						" 	group by a.khoxuat_fk, b.solo, b.sanpham_fk,b.ngayhethan,b.ngaynhapkho " +
						" ) " +
						" CT inner join ERP_KHOTT_SP_CHITIET kho on CT.kho_fk = kho.KHOTT_FK  " +
						" 	and CT.sanpham_fk = kho.SANPHAM_FK and CT.solo = kho.SOLO and CT.ngayhethan=kho.ngayhethan and CT.ngaynhapkho=kho.ngaynhapkho";
				
				 ResultSet rs1=db.get(query);
				 if(rs1!=null)
				 {
					 while(rs1.next())
					 {
						 String _kho_fk=rs1.getString("kho_fk");
						 String _SANPHAM_FK=rs1.getString("sanpham_fk");
						 String _solo = rs1.getString("solo");
						 String _ngayhethan= rs1.getString("ngayhethan");
						 String _NGAYNHAPKHO= rs1.getString("ngaynhapkho");
						 String _tongxuat=rs1.getString("tongxuat");
						/* query = "Update ERP_KHOTT_SP_CHITIET set booked = booked - '" + _tongxuat + "', AVAILABLE = AVAILABLE + '" + _tongxuat + "' " +
									"where KHOTT_FK = '" + _kho_fk + "' and SOLO = '" + _solo + "' and SANPHAM_FK = '" + _SANPHAM_FK + "'  and ngayhethan='"+_ngayhethan+"' and ngaynhapkho='"+_NGAYNHAPKHO+"'  ";
						 if(db.updateReturnInt(query)!=1)
							{
							 msg = "Không thể cập nhật ERP_KHOTT_SP_CHITIET " + query;
								System.out.println("query    "+query);
								db.getConnection().rollback();
								return msg;
							}*/
					 }
				 }
				 else
				 {
					 msg = "Không thể cập nhật ERP_KHOTT_SP_CHITIET " + query;
						System.out.println("query    "+query);
						db.getConnection().rollback();
						return msg;
				 }
				
				 
					/*query = " update kho set kho.AVAILABLE = kho.AVAILABLE + CT.tongxuat, " +
							" 			   kho.BOOKED = kho.BOOKED - CT.tongxuat " +
							" from " +
							" ( " +
							" 	select a.khoxuat_fk as kho_fk, b.sanpham_fk, SUM(b.soluong) as tongxuat  " +
							" 	from ERP_CHUYENKHO a inner join ERP_CHUYENKHO_SANPHAM_CHITIET b on a.pk_seq = b.chuyenkho_fk " +
							" 	where chuyenkho_fk = '" + lsxId + "' " +
							" 	group by a.khoxuat_fk, b.sanpham_fk " +
							" ) " +
							" CT inner join ERP_KHOTT_SANPHAM kho on CT.kho_fk = kho.KHOTT_FK  " +
							" 	and CT.sanpham_fk = kho.SANPHAM_FK   ";
					if(!db.update(query))
					{
						System.out.println("Không thể cập nhật ERP_KHOTT_SANPHAM " +query);
						msg = "Không thể cập nhật ERP_KHOTT_SANPHAM " + query;
						db.getConnection().rollback();
						return msg;
					}*/
			
			 query = "update  ERP_ChuyenKho set trangthai=2 where pk_seq = '" + lsxId + "'";
			if(!db.update(query))
			{
				msg = "1.Khong the xoa: " + query;
				db.getConnection().rollback();
				return msg;
			}
			if(Integer.parseInt(loaidonhang) == 4)// xuat kho khac
			{
				query  = "delete PhieuXuat_SoChungTu   where tableName = 'ERP_ChuyenKho' and Id_FK = '" + lsxId + "'";
				if(db.updateReturnInt(query)< 0)		
				{
					db.getConnection().rollback();
					db.getConnection().setAutoCommit(true);
					db.shutDown();
					return "Lỗi xóa chứng từ:"+ query;
				}
			}else if(Integer.parseInt(loaidonhang) == 0)
			{
				query  = "delete DieuChuyen_SoChungTu   where tableName = 'ERP_ChuyenKho' and Id_FK = '" + lsxId + "'";
				if(db.updateReturnInt(query)< 0)		
				{
					db.getConnection().rollback();
					db.getConnection().setAutoCommit(true);
					db.shutDown();
					return "Lỗi xóa chứng từ:"+ query;
				}
			}
			
			
			/*query = "delete ERP_CHUYENKHO_SANPHAM_CHITIET where chuyenkho_fk = '" + lsxId + "'";
			if(!db.update(query))
			{
				msg = "1.Khong the xoa: " + query;
				db.getConnection().rollback();
				return msg;
			}
			
			query = "delete ERP_ChuyenKho where pk_seq = '" + lsxId + "'";
			if(!db.update(query))
			{
				msg = "2.Khong the xoa: " + query;
				db.getConnection().rollback();
				return msg;
			}*/
			
			query = " update ERP_DONDATHANG set trangthai = '3', nguoisua = '" + userId + "', ngaysua = '" + getDateTime() + "' " +
					" where pk_seq = ( select ddh_fk from ERP_CHUYENKHO where pk_seq = '" + lsxId + "' ) ";
			if(!db.update(query))
			{
				msg = "3.Khong the xoa: " + query;
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
	    
	    HttpSession session = request.getSession();
	    String userId = (String) session.getAttribute("userId");  
		String userTen = (String) session.getAttribute("userTen");  	
		String sum = (String) session.getAttribute("sum");
	    
	    geso.dms.center.util.Csrf csdr=new geso.dms.center.util.Csrf(request,response,false,true ,false);
		if(!csdr.__validate_post())
		{
			response.sendRedirect(request.getContextPath() + "/redirect.jsp");
			return;
		}
		
		geso.dms.center.util.Utility cutil = (geso.dms.center.util.Utility) session.getAttribute("util");
		if(!cutil.check(userId, userTen, sum)){
			response.sendRedirect(request.getContextPath() + "/redirect.jsp");
		}else{
			String action = request.getParameter("action");
		    if (action == null)
		    {
		    	action = "";
		    }
		    
			IErpChuyenkhoTTList obj = new ErpChuyenkhoTTList();
		 
		    Utility util = new Utility();
		    
		    userId = util.antiSQLInspection(request.getParameter("userId")); 
		    
		    String loaidonhang = request.getParameter("loaidonhang");
		    if(loaidonhang == null)
		    	loaidonhang = "0";
		    obj.setLoaidonhang(loaidonhang);
		    System.out.println("---LOAI DON HANG: " + loaidonhang);
		    
		    if(action.equals("Tao moi"))
		    {
		    	IErpChuyenkhoTT lsxBean = new ErpChuyenkhoTT();
		    	lsxBean.setLoaidonhang(loaidonhang);
		    	lsxBean.createRs();
	    		
		    	session.setAttribute("lsxBean", lsxBean);
		    	
	    		String nextJSP = request.getContextPath() + "/pages/Center/ErpChuyenKhoTTNew.jsp";
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
			    	
			    	String nextJSP = request.getContextPath() + "/pages/Center/ErpChuyenKhoTT.jsp";
					response.sendRedirect(nextJSP);
			    }
		    	else
		    	{
		    		System.out.println("Vào search");
			    	String search = getSearchQuery(request, obj);
			    	System.out.println("Search SQL: "+search);
			    	obj.setUserId(userId);
			    	obj.init(search);
					obj.setUserId(userId);
					
			    	session.setAttribute("obj", obj);  	
		    		session.setAttribute("userId", userId);
			
		    		String nextJSP = request.getContextPath() + "/pages/Center/ErpChuyenKhoTT.jsp";
		    		response.sendRedirect(nextJSP);
		    		
		    	}
		    }
		}
	    
	    
	}
	
	private String getSearchQuery(HttpServletRequest request, IErpChuyenkhoTTList obj)
	{
		/*String query =  "select a.sonetId,isnull(a.LoaiDonHang,0) as LoaiDonHang,a.PK_SEQ, a.trangthai, a.ngaychuyen, a.ghichu as lydo, NV.TEN as nguoitao, b.ten as khoxuat,TT.TEN as DIABAN ,c.ten as nppTEN, a.NGAYSUA, a.NGAYTAO, NV2.TEN as nguoisua   " +
						" from ERP_CHUYENKHO a inner join ERP_KHOTT b on a.khoxuat_fk = b.pk_seq inner join NHAPHANPHOI c on a.npp_fk = c.pk_seq  " +
						" inner join NHANVIEN nv on a.NGUOITAO = nv.PK_SEQ   " +
						" inner join TINHTHANh TT on c.TINHTHANH_FK = tt.PK_SEQ " +
						" inner join NHANVIEN nv2 on a.NGUOISUA = nv2.PK_SEQ where a.pk_seq > 0 ";*/
		
		String query = "select ( select SUM(data.tongtien) as tongtien from  (   \n"+
					   "	select isnull(aa.dongia,0)*aa.soluongchuyen  as tongtien   \n"+
		               "  from ERP_CHUYENKHO_SANPHAM  aa where aa.chuyenkho_fk=a.pk_seq) as data) as tongtien, \n"+
						"		a.sonetId,isnull(a.LoaiDonHang,0) as LoaiDonHang,a.PK_SEQ, a.trangthai, a.ngaychuyen, a.ghichu as lydo,TT.TEN as DIABAN ,NV.TEN as nguoitao, b.ten as khoxuat, c.ten as nppTEN, a.NGAYSUA, a.NGAYTAO, NV2.TEN as nguoisua   " +
						"from ERP_CHUYENKHO a inner join ERP_KHOTT b on a.khoxuat_fk = b.pk_seq inner join NHAPHANPHOI c on a.npp_fk = c.pk_seq  " +
						"inner join NHANVIEN nv on a.NGUOITAO = nv.PK_SEQ   " +
						" inner join TINHTHANh TT on c.TINHTHANH_FK = tt.PK_SEQ "+
						"inner join NHANVIEN nv2 on a.NGUOISUA = nv2.PK_SEQ  where  c.loainpp!=4 and a.pk_seq > 0 and a.LoaiDonHang='"+ obj.getLoaidonhang()+"' and c.tructhuoc_fk='1' ";

		
		
		String tungaySX = Utility.antiSQLInspection(request.getParameter("tungay"));
		if(tungaySX == null)
			tungaySX = "";
		obj.setTungayTao(tungaySX);
		
		String denngaySX = Utility.antiSQLInspection(request.getParameter("denngay"));
		if(denngaySX == null)
			denngaySX = "";
		obj.setDenngayTao(denngaySX);
		
		String nppId = Utility.antiSQLInspection(request.getParameter("nppId"));
		if(nppId == null)
			nppId="";
		obj.setNppId(nppId);
		
		String trangthai = Utility.antiSQLInspection(request.getParameter("trangthai"));
		if(trangthai == null)
			trangthai = "";
		obj.setTrangthai(trangthai);
		
		String ctId = Utility.antiSQLInspection(request.getParameter("chungtu"));
		System.out.println("ma ne:" +ctId);
		
		if(ctId == null)
			ctId = "";
		obj.setctId(ctId);		

		if(tungaySX.length() > 0)
			query += " and a.ngaychuyen >= '" + tungaySX + "'";
		
		if(denngaySX.length() > 0)
			query += " and a.ngaychuyen <= '" + denngaySX + "'";
		if(nppId.length() > 0)
			query += " and c.pk_seq = '" + nppId + "'";	
		
		if(trangthai.length() > 0)
			query += " and a.TrangThai = '" + trangthai + "'";
		
		if(ctId.length() > 0)
			query += " and a.PK_SEQ like '%" + ctId + "%'";
		
		System.out.println("Search:" + query);
		return query;
	}
		
	public String getDateTime()
	{
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date date = new Date();
        return dateFormat.format(date);	
	}
	
	
}
