package geso.dms.center.servlets.hoadontaichinh;

import geso.dms.center.beans.hoadontaichinh.IErpHoadontaichinh;
import geso.dms.center.beans.hoadontaichinh.IErpHoadontaichinhList;
import geso.dms.center.beans.hoadontaichinh.imp.ErpHoadontaichinh;
import geso.dms.center.beans.hoadontaichinh.imp.ErpHoadontaichinhList;
import geso.dms.center.servlets.report.ReportAPI;
import geso.dms.distributor.db.sql.dbutils;
import geso.dms.center.util.Utility;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
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

import com.aspose.cells.BorderLineType;
import com.aspose.cells.BorderType;
import com.aspose.cells.Cell;
import com.aspose.cells.Cells;
import com.aspose.cells.Color;
import com.aspose.cells.FileFormatType;
import com.aspose.cells.Font;
import com.aspose.cells.HorizontalAlignmentType;
import com.aspose.cells.Style;
import com.aspose.cells.TextAlignmentType;
import com.aspose.cells.Workbook;
import com.aspose.cells.Worksheet;
import com.aspose.cells.Worksheets;

public class ErpHoadontaichinhSvl extends HttpServlet 
{
	private static final long serialVersionUID = 1L;
       
    public ErpHoadontaichinhSvl() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		IErpHoadontaichinhList obj;
	    response.setCharacterEncoding("UTF-8");
	    response.setContentType("text/html; charset=UTF-8");
	    
	    OutputStream out = response.getOutputStream();
	    HttpSession session = request.getSession();	    

	    Utility util = new Utility();
	   
	    	    
	    String querystring = request.getQueryString();
	    String userId = util.getUserId(querystring);
	    
	    if (userId.length()==0)
	    	userId = util.antiSQLInspection(request.getParameter("userId"));
	    
	    String action = util.getAction(querystring);
	    	    
    	String lsxId = util.getId(querystring);
	    obj = new ErpHoadontaichinhList();
	   
	    String nppId = util.getIdNhapp(userId);
	    
	    String noibo = request.getParameter("NOIBO") == null ? "0" : request.getParameter("NOIBO");
	    obj.setNOIBO(noibo);
	    
	    String km = request.getParameter("km");
	    if(km != null)
	    {
	    	obj.setLoaikm(km);
	    }
	    System.out.println("loai khuyen mai "+km);
	    
	    String loaidonhang = request.getParameter("loaidonhang");
	    if(loaidonhang == null)
	    	loaidonhang = "";
	    obj.setLoaidonhang(loaidonhang);
 
    	if(action.equals("chot"))
    	{
    		String msg = this.Chot(lsxId,userId);
			obj.setMsg(msg);
    	}
    	else if(action.equals("delete"))
    	{
    		String msg = this.Delete(lsxId, userId, nppId);
			obj.setMsg(msg);
    	}
    	else if(action.equals("huy"))
    	{
    		String msg = this.Huy(lsxId, userId, nppId);
			obj.setMsg(msg);
    	}
    	else if(action.equals("printBANGKE"))
    	{
    		String query = "select b.MA, b.TEN, a.DONVITINH as DONVI, a.SOLUONG, a.DONGIA, a.SOLUONG * a.DONGIA as doanhthu, " + 
    					   " a.SOLUONG * a.DONGIA * a.VAT / 100.0 as VAT, a.SOLUONG * a.DONGIA * ( 1 + ( a.VAT / 100.0 ) ) as AVAT " +
						   "from ERP_HOADON_SP a inner join SANPHAM b on a.SANPHAM_FK = b.PK_SEQ " +
						   "where a.HOADON_FK = '" + lsxId + "' " +
						   "order by b.TEN asc"; 
    		
    		System.out.println("::: QUERY BANG KE: " + query);
    		try
			{ 
				response.setContentType("application/xlsm");
				response.setHeader("Content-Disposition", "attachment; filename=BangKeHoaDonNoiBo.xlsm");
				FileInputStream fstream = new FileInputStream(getServletContext().getInitParameter("path") + "\\BCBangKeNoiBo.xlsm");
				
				Workbook workbook = new Workbook();
				workbook.open(fstream);
				workbook.setFileFormatType(FileFormatType.EXCEL2007XLSM);

				CreateStaticHeader(workbook, obj);

				CreateStaticData(workbook, obj, query);
						
				workbook.save(out);
				fstream.close();
			}
			catch (Exception ex)
			{
				ex.printStackTrace();
				obj.setMsg("Khong the tao pivot.");
			}
			obj.setUserId(userId);
			session.setAttribute("obj", obj);
			session.setAttribute("userId", userId);
			String nextJSP = request.getContextPath() + "/pages/Center/ErpHoaDonTaiChinh.jsp";
			response.sendRedirect(nextJSP); 
    		return;
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
		String nextJSP = request.getContextPath() + "/pages/Center/ErpHoaDonTaiChinh.jsp";
					
		response.sendRedirect(nextJSP);
	    
	}

	private String Huy(String lsxId, String userId, String nppId) 
	{
		dbutils db = new dbutils();
		String msg = "";

		Utility util = new Utility();
		/*msg= util.Check_Huy_NghiepVu_KhoaSo("ERP_HOADONNPP", lsxId, "ngayxuatHD", db);
		if(msg.length()>0)
		{
			db.shutDown();
			return msg;
		}*/

		try
		{	
			db.getConnection().setAutoCommit(false);
			String query = "";

			// Kiểm tra import =1 thì k cho hủy
			query = "select COUNT(*) as sodong from ERP_YCXUATKHO_DDH where hoadon_fk = '" + lsxId + "' ";
			System.out.println(query);

			ResultSet rsKT = db.get(query);
			int dacoXK = 0;

			if(rsKT!= null)
			{
				while(rsKT.next())
				{
					dacoXK =  rsKT.getInt("sodong");
				}
				rsKT.close();
			}

			if( dacoXK > 0 )
			{
				msg = "Hóa đơn đã có xuất kho. Bạn không thể xóa / hủy. ";
				db.getConnection().rollback();
				return msg;
			}

			//NHẢ LẠI TỒN FKHO
			//Tăng kho ngược lại trước khi xóa
			query = "  select b.NgayDonHang, b.Kho_FK, a.SanPham_fk, a.SoLo, a.NgayHetHan, a.ngaynhapkho, SUM( a.SoLuong ) as soluong  " + 
					"  from ERP_DONDATHANG_SANPHAM_CHITIET a inner join ERP_DONDATHANG b on a.DonDatHang_FK = b.PK_SEQ " + 
					"  where b.PK_SEQ = ( select DonDatHang_FK from ERP_HOADON where PK_SEQ = '" + lsxId + "' ) " + 
					"  group by b.NgayDonHang, b.Kho_FK, a.SanPham_fk, a.SoLo, a.NgayHetHan, a.ngaynhapkho ";
			ResultSet rs = db.get(query);
			if( rs != null )
			{
				while( rs.next() )
				{
					String khoId = rs.getString("Kho_FK");
					String spId = rs.getString("SanPham_fk");
					String solo = rs.getString("SoLo");
					String ngayhethan = rs.getString("NgayHetHan");
					String ngaynhapkho = rs.getString("ngaynhapkho");
					double soluong = rs.getDouble("soluong");

					/*msg = util.Update_KhoTT(rs.getString("NgayDonHang"), "HO / Xóa hóa đơn", db, khoId, spId, solo, ngayhethan, ngaynhapkho, 0, -1 * soluong, soluong);
					if( msg.trim().length() > 0 )
					{
						db.getConnection().rollback();
						return msg;
					}*/
				}
				rs.close();
			}

			query = "Update ERP_DONDATHANG set trangthai = '3' where pk_seq = ( select DonDatHang_FK from ERP_HOADON where PK_SEQ = '" + lsxId + "' ) ";
			if( db.updateReturnInt(query) < 1 )
			{
				msg = "Lỗi khi hủy: " + query;
				db.getConnection().rollback();
				return msg;
			}

			//XÓA TẤT CẢ CÁC HÓA ĐƠN CỦA ĐƠN HÀNG NÀY
			query = "update  ERP_HOADON set trangthai = '5' where DonDatHang_FK = ( select DonDatHang_FK from ERP_HOADON where pk_seq = '" + lsxId + "' ) ";
			if( db.updateReturnInt(query) < 1 )
			{
				msg = "Lỗi khi hủy: " + query;
				db.getConnection().rollback();
				return msg;
			}

			/*msg= util.Check_Kho_Tong_VS_KhoCT(nppId, db);
			if(msg.length()>0)
			{
				db.getConnection().rollback();
				return msg;
			}*/

			
			

			db.getConnection().commit();
			db.getConnection().setAutoCommit(true);
		}
		catch (Exception e) 
		{
			try
			{
				db.getConnection().rollback();
			} catch (SQLException e1)
			{
				e1.printStackTrace();
			}
			return "Exception: " + e.getMessage();
		}
		finally
		{
			db.shutDown();
		}
		return "";
	}
	
	private String Delete(String lsxId, String userId, String nppId) 
	{
		dbutils db = new dbutils();
		String msg = "";

		Utility util = new Utility();
		/*msg= util.Check_Huy_NghiepVu_KhoaSo("ERP_HOADONNPP", lsxId, "ngayxuatHD", db);
		if(msg.length()>0)
		{
			db.shutDown();
			return msg;
		}*/

		try
		{	
			db.getConnection().setAutoCommit(false);
			String query = "";

			// Kiểm tra import =1 thì k cho hủy
			query = "select COUNT(*) as sodong from ERP_YCXUATKHO_DDH where hoadon_fk = '" + lsxId + "' ";
			System.out.println(query);

			ResultSet rsKT = db.get(query);
			int dacoXK = 0;

			if(rsKT!= null)
			{
				while(rsKT.next())
				{
					dacoXK =  rsKT.getInt("sodong");
				}
				rsKT.close();
			}

			if( dacoXK > 0 )
			{
				msg = "Hóa đơn đã có xuất kho. Bạn không thể xóa / hủy. ";
				db.getConnection().rollback();
				return msg;
			}

			//NHẢ LẠI TỒN FKHO
			//Tăng kho ngược lại trước khi xóa
			query = "  select b.NgayDonHang, b.Kho_FK, a.SanPham_fk, a.SoLo, a.NgayHetHan, a.ngaynhapkho, SUM( a.SoLuong ) as soluong  " + 
					"  from ERP_DONDATHANG_SANPHAM_CHITIET a inner join ERP_DONDATHANG b on a.DonDatHang_FK = b.PK_SEQ " + 
					"  where b.PK_SEQ = ( select DonDatHang_FK from ERP_HOADON where PK_SEQ = '" + lsxId + "' ) " + 
					"  group by b.NgayDonHang, b.Kho_FK, a.SanPham_fk, a.SoLo, a.NgayHetHan, a.ngaynhapkho ";
			ResultSet rs = db.get(query);
			if( rs != null )
			{
				while( rs.next() )
				{
					String khoId = rs.getString("Kho_FK");
					String spId = rs.getString("SanPham_fk");
					String solo = rs.getString("SoLo");
					String ngayhethan = rs.getString("NgayHetHan");
					String ngaynhapkho = rs.getString("ngaynhapkho");
					double soluong = rs.getDouble("soluong");

					msg = util.Update_KhoTT(rs.getString("NgayDonHang"), "HO / Xóa hóa đơn", db, khoId, spId, solo, ngayhethan, ngaynhapkho, 0, -1 * soluong, soluong);
					if( msg.trim().length() > 0 )
					{
						db.getConnection().rollback();
						return msg;
					}
				}
				rs.close();
			}

			query = "Update ERP_DONDATHANG set trangthai = '3' where pk_seq = ( select DonDatHang_FK from ERP_HOADON where PK_SEQ = '" + lsxId + "' ) ";
			if( db.updateReturnInt(query) < 1 )
			{
				msg = "Lỗi khi hủy: " + query;
				db.getConnection().rollback();
				return msg;
			}

			//XÓA TẤT CẢ CÁC HÓA ĐƠN CỦA ĐƠN HÀNG NÀY
			query = "update  ERP_HOADON set trangthai = '3' where DonDatHang_FK = ( select DonDatHang_FK from ERP_HOADON where pk_seq = '" + lsxId + "' ) ";
			if( db.updateReturnInt(query) < 1 )
			{
				msg = "Lỗi khi hủy: " + query;
				db.getConnection().rollback();
				return msg;
			}

			/*msg= util.Check_Kho_Tong_VS_KhoCT(nppId, db);
			if(msg.length()>0)
			{
				db.getConnection().rollback();
				return msg;
			}*/

			db.getConnection().commit();
			db.getConnection().setAutoCommit(true);
		}
		catch (Exception e) 
		{
			try
			{
				db.getConnection().rollback();
			} catch (SQLException e1)
			{
				e1.printStackTrace();
			}
			return "Exception: " + e.getMessage();
		}
		finally
		{
			db.shutDown();
		}
		return "";
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
			
			
			query = "update ERP_HOADON set trangthai = '2',NgaySua='"+getDateTime()+"',created_Date=getdate()  where pk_seq = '" + lsxId + "' and trangthai in (0,1)  ";
			if(db.updateReturnInt(query)!=1)
			{
				msg = "Hóa đơn đã chuyển trạng thái ,vui lòng xem lại ";
				db.getConnection().rollback();
				return msg;
			}

			
			
			query = " select a.npp_fk, a.noibo, a.loaihoadon, dh.isKM  " +
					" from ERP_HOADON a " +
					" inner join ERP_HOADON_DDH ddh on ddh.HOADON_FK=a.PK_SEQ "+
					" inner join ERP_DONDATHANG dh on dh.PK_SEQ=ddh.DDH_FK "+
					" where a.pk_seq = "+lsxId;
			
			ResultSet rs=db.get(query);
			String npp="";
			int noibo = 0;
			int isKM = 0;
			
			if(rs.next())
			{
				npp = rs.getString("npp_fk");
				noibo = rs.getInt("NOIBO");
				isKM = rs.getInt("isKM");
			}
			rs.close();
			
	
			
			//CHECK BANG TONG PHAI BANG BANG CHI TIET
			query = "select count(*) as sodong  " +
					"from " +
					"( " +
					"	select b.pk_seq as sanpham_fk, sum(soluong) as soluong  " +
					"	from ERP_HOADON_SP a inner join SANPHAM b on a.sanpham_fk = b.pk_seq " +
					"	where a.hoadon_fk = '" + lsxId + "' " +
					"	group by b.pk_seq " +
					") " +
					"dh left join " +
					"( " +
					"	select b.pk_seq as sanpham_fk, sum(soluong) as soluong  " +
					"	from ERP_HOADON_SP_CHITIET a inner join SANPHAM b on a.MA = b.MA " +
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
				db.getConnection().rollback();
				return msg;
			}
			
			//CAP NHAT DVDL
			query = "  update a " + 
					"  	set a.DonVi_Chuan = ( select DONVI from DONVIDOLUONG where pk_seq = b.DVDL_FK), " +  
					"  		a.SoLuong_Chuan = a.SOLUONG * dbo.LayQuyCach(a.SANPHAM_FK, null, ( select PK_SEQ from DONVIDOLUONG where DONVI = a.DONVITINH ) ) " + 
					"  from ERP_HOADON_SP a  inner join SANPHAM b on a.SANPHAM_FK = b.PK_SEQ  " + 
					"  where a.HOADON_FK in ( '" + lsxId + "' )  ";
			System.out.println("---CAP NHAT HOA DON: " + query);
			if(!db.update(query))
			{
				msg = "Không thể cập nhật ERP_HOADONNPP_SP " + query;
				System.out.println("error "+query);
				db.getConnection().rollback();
				return msg;
			}
			
			
			

			
			if(noibo == 1 ) // HÓA ĐƠN NỘI BỘ
			{
				
				// NẾU LÀ ĐƠN NỘI BỘ THÌ TỰ ĐỘNG TẠO HÓA ĐƠN NCC DƯỚI CHI NHÁNH
					db.execProceduce2("CREATE_INVOICE_SUPPLIER", new String[] { lsxId } );
				
				
			}	
			
			query=" insert ERP_HOADON_CHUNGLOAI( hoadon_fk, chungloai_fk, sotienGOC, sotien ) "+
					  " select a.hoadon_fk, b.chungloai_fk, round( sum( a.soluong * a.dongia * (  1 + vat / 100.0 ) ), 0) as avat, round( sum( a.soluong * a.dongia * (  1 + vat / 100.0 ) ), 0)   "+
					  " from ERP_HOADON_SP a inner join SANPHAM b on a.sanpham_fk = b.pk_seq  "+
					  "	where a.hoadon_fk in ( select pk_seq from ERP_HOADON where trangthai in ( 2, 4 ) ) and a.hoadon_fk= "+ lsxId +
					  "  group by a.hoadon_fk, b.chungloai_fk ";

				if(!db.update(query))
				{
					msg = "Không thể cập nhật ERP_HOADONNPP_CHUNGLOAI " + query;
					db.getConnection().rollback();
					return msg;
				}
				
				query= " update a  "+
					   "	set a.chungloaiCN = isnull( (	Select cl.ten + ',' + cast(hd_cl.sotienGOC as varchar(10)) + ';' AS [text()]  "+
					   "							From ERP_HOADON_CHUNGLOAI hd_cl inner join CHUNGLOAI cl on hd_cl.chungloai_fk = cl.pk_seq "+
					   "							Where hd_cl.hoadon_fk = a.pk_seq   "+
					   "							For XML PATH ('') ) , '') "+
					   "	from ERP_HOADON a where a.pk_seq = '"+ lsxId +"' ";
				if(!db.update(query))
				{
					msg = "Không thể cập nhật chungloaiCN " + query;
					db.getConnection().rollback();
					return msg;
				}
				
				
				if(!createPxk(db, lsxId, userId))
				{
					msg = "lỗi tạo mới phiếu xuất kho" ;
					db.getConnection().rollback();
					return msg;
				}
				
			
				msg = util.Check_Kho_Tong_VS_KhoCT(npp, db);
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
	
	
	private String Chot_phieuxuatkho(dbutils db,String lsxId, String userId, String nppId) 
	{
		
		String msg = "";
		try
		{
			
			String query = "";
			

			query = "  select c.ngayyeucau, c.npp_fk,  c.kho_fk, a.sanpham_fk, b.ten as TEN, a.soluong as soluongDAT, a.solo, a.ngayhethan,   " + 
					"  		a.soluong,  0 as loai, ' ' as scheme, a.ngaynhapkho     " + 
					"  from ERP_YCXUATKHO_SANPHAM_CHITIET a inner join SANPHAM b on a.sanpham_fk = b.PK_SEQ    " + 
					"  		inner join ERP_YCXUATKHO c on  a.ycxk_fk = c.pk_seq inner join NHAPHANPHOI d on c.npp_fk = d.pk_seq  " + 
					"  where a.ycxk_fk in (  " + lsxId + " ) ";
			
			System.out.println("--CAP NHAT TON KHO: " + query);
			
			ResultSet rs = db.get(query);
			if(rs != null)
			{
				Utility utility = new Utility();
				while(rs.next())
				{
					String khoID = rs.getString("kho_fk");
					String nppID = rs.getString("npp_fk");
					String spID = rs.getString("sanpham_fk");
					
					double soluong = rs.getDouble("soluong");
					String solo = rs.getString("solo");
					String ngayhethan = rs.getString("ngayhethan");
					String ngaynhapkho=rs.getString("ngaynhapkho");
					
					String kq1 = utility.Update_KhoTT(rs.getString("ngayyeucau"), "HO / Duyệt XK", db, khoID, spID, solo, ngayhethan, ngaynhapkho, -1 * soluong, -1 * soluong, 0);
					if( kq1.length() > 0 )
					{
						msg = "Khong the cap nhat ERP_NHAPP: " + kq1;
						rs.close();
						return msg;
					}
					
					query = "update ERP_YCXUATKHO_SANPHAM_CHITIET set kho_fk = '" + khoID + "'  " +
							"where ycxk_fk = '" + lsxId + "' and sanpham_fk = '" + spID + "' and solo = N'" + solo + "' and ngayhethan = '" + ngayhethan + "' and ngaynhapkho = '" + ngaynhapkho + "' ";
					
					System.out.println("1.2.Insert YCXK - SP - CT: " + query);
					if(!db.update(query))
					{
						msg = "Khong the tao moi ERP_YCXUATKHO_SANPHAM_CHITIET: " + query;
						System.out.println("ERRor loi "+msg);
						rs.close();
						return msg;
					}							
				}
				rs.close();
			}
			
			//GOP CHUNG BUOC YC VA XUAT THANH 1
			query = "update ERP_YCXUATKHO set trangthai = '2'  where pk_seq = '" + lsxId + "' and trangthai != 2 ";
			System.out.println("---CAP NHAT TRANG THAI: " + query);
			if(db.updateReturnInt(query) <= 0 )
			{
				msg = "Không thể cập nhật ERP_YCXUATKHO " + query;
				return msg;
			}
			
			//TU DONG HOAN TAT CAC HOA DON TU CU TOI MOI
			query = "select hoadon_fk, ( select xuatcho from ERP_YCXUATKHO where pk_seq = a.ycxk_fk ) as xuatcho " +
					"from ERP_YCXUATKHO_DDH a where ycxk_fk = '" + lsxId + "' order by hoadon_fk asc";
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
							"   					from ERP_HOADON_SP a inner join SANPHAM b on a.sanpham_fk = b.PK_SEQ     " +
							"   					where a.hoadon_fk in (    " + ( ddhID.substring(0, ddhID.length() - 1) ) + "   )    " +
							"   			)     " +
							"   			dathang  )	 as soSP  " +
							"  from  " +
							"  (  " +
							"  	select sanpham_fk, sum(soluongXUAT) as soluongXUAT  " +
							"  	from ERP_YCXUATKHO_SANPHAM  " +
							" 	where ycxk_fk in ( select ycxk_fk from ERP_YCXUATKHO_DDH where hoadon_fk in ( " + ( ddhID.substring(0, ddhID.length() - 1) ) + " ) ) " +
							"  	group by sanpham_fk  " +
							"  )   " +
							"  XUAT inner join   " +
							"  (  " +
							"   	select dathang.sanpham_fk, SUM(dathang.soluong) as soluongDAT      " +
							"   	from     " +
							"   	(     " +
							"   			select a.sanpham_fk, b.DVDL_FK as dvCHUAN,     " +
							"   					isnull(a.soluong_chuan, a.soluong) as soluong, 0 as loai, ' ' as scheme    " +
							"   			from ERP_HOADON_SP a inner join SANPHAM b on a.sanpham_fk = b.PK_SEQ     " +
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
						
						query = " UPDATE ERP_HOADON set hoantat = '" + hoantat + "' where pk_seq in ( " + ( ddhID.substring(0, ddhID.length() - 1) ) + " ) ";
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
				query = " insert NHAPHANG(NGAYNHAN, NGAYCHUNGTU, NPP_FK, NCC_FK, GSBH_FK, ASM_FK, BM_FK, DVKD_FK, KBH_FK, YCXK_FK, TRANGTHAI, NGUOITAO, NGAYTAO, NGUOISUA, NGAYSUA) " +
						" select distinct NgayYeuCau, NgayYeuCau, NPP_FK,  " +
						" 			( select top(1) NCC_FK from NHACUNGCAP_DVKD where PK_SEQ in ( select NCC_DVKD_FK from NHAPP_NHACC_DONVIKD where NPP_FK = a.NPP_FK ) ), " +
						"			( select top(1) GSBH_FK from NHAPP_GIAMSATBH where NPP_FK = a.NPP_FK ), " +
						"			( select top(1) ASM_FK from ASM_KHUVUC where KHUVUC_FK in ( select KHUVUC_FK from NHAPHANPHOI where PK_SEQ = a.NPP_FK )), " +
						"			( select top(1) BM_FK from BM_CHINHANH where VUNG_FK in ( select VUNG_FK from KHUVUC where PK_SEQ in (  select KHUVUC_FK from NHAPHANPHOI where PK_SEQ = a.NPP_FK ) ) ), " +
						" 	   '100001' as DVKD_FK, ( select top(1) KBH_FK from NHAPP_KBH where NPP_FK = a.npp_fk ) as KBH_FK, '" + lsxId + "', '0', '" + userId + "', '" + getDateTime() + "', '" + userId + "', '" + getDateTime() + "' " +
						" from ERP_YCXUATKHO a inner join ERP_YCXUATKHO_SANPHAM b on a.PK_SEQ = b.ycxk_fk " +
						" where a.PK_SEQ = '" + lsxId + "' ";
				
				System.out.println("---INSERT NHAN HANG: " + query );
				if(!db.update(query))
				{
					msg = "Không tạo mới NHAPHANG " + query;
					db.getConnection().rollback();
					return msg;
				}
				
				
				
				query = " insert NHAPHANG_SP(NHAPHANG_FK, SANPHAM_FK, SOLUONG, soluongNHAN, DONGIA, CHIETKHAU, DVDL_FK, LOAI, SCHEME, SOLO, NGAYHETHAN) " +
						" select ( select pk_seq from NHAPHANG where YCXK_FK = a.PK_SEQ  ),  " +
						" 		b.sanpham_fk, b.soluong, NULL, b.dongia, 0 as chietkhau, c.DVDL_FK, b.LOAI, b.SCHEME, b.solo, b.ngayhethan " +
						" from ERP_YCXUATKHO a inner join ERP_YCXUATKHO_SANPHAM_CHITIET b on a.PK_SEQ = b.ycxk_fk " +
						" 		inner join SANPHAM c on b.sanpham_fk = c.PK_SEQ   " +
						" where a.PK_SEQ = '" + lsxId + "' and b.soluong > 0 ";
				System.out.println("---INSERT NHAN HANG - SP: " + query );
				if(db.updateReturnInt(query) < 1 )
				{
					msg = "Không tạo mới NHAPHANG_SP " + query;
					
					return msg;
				}
				
				query = "insert NHAPHANG_DDH(nhaphang_fk, hoadon_fk, isHO )  " +
						"select ( select PK_SEQ from NHAPHANG where YCXKNPP_FK = '" + lsxId + "' ) as nhID, hoadon_fk, 1  " +
						"from ERP_YCXUATKHO_DDH where ycxk_fk = '" + lsxId + "'";
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

	
	
	public boolean createPxk(dbutils db,String hoadon_fk,String userid) 
	{
		
		
		try
		{
				
			String khachhang_fk = "NULL";
			String npp_dat_fk = "NULL";
			String xuatcho="0";
			String iskh="0";
			String query="select (select isnull(isKHACHHANG,0) from NHAPHANPHOI where PK_SEQ=a.npp_fk) iskh,a.NGAYXUATHD,a.kho_fk,a.npp_fk from erp_hoadon a where PK_SEQ="+hoadon_fk;
			ResultSet rs=db.get(query);
			rs.next();
			String ngayxuathd=rs.getString("ngayxuathd");
			String kho_fk=rs.getString("kho_fk");
			 iskh=rs.getString("iskh");
			if(!iskh.equals("1"))
			{
				khachhang_fk = rs.getString("npp_fk");
			}
			else
			{
				khachhang_fk = rs.getString("npp_fk");
				xuatcho="1";
			}
			rs.close();
			
			 query = " insert ERP_YCXUATKHO(NgayYeuCau, ghichu, trangthai, npp_fk, kho_fk, xuatcho, ngaytao, nguoitao, ngaysua, nguoisua) " +
						   " select top(1) '" + ngayxuathd + "', N'', '0', '" + khachhang_fk + "', " + kho_fk + ", '" + xuatcho + "', " +
						   		" '" + getDateTime() + "', '" + userid + "', '" + getDateTime() + "', '" + userid + "' " +
						   " from ERP_HOADON where pk_seq = '" + hoadon_fk + "' ";
			
			System.out.println("1.Insert ERP_YCXUATKHO: " + query);
			if( db.updateReturnInt(query) <= 0 )
			{
				System.out.println("Không thể tạo mới ERP_YCXUATKHO " + query);
				return false;
			}

			
			query = "select SCOPE_IDENTITY() as btId";
			ResultSet rsBtId = db.get(query);	
			
			rsBtId.next();
		    String ycxkCurrentId = rsBtId.getString("btId");
		    rsBtId.close();
		    
		    
			query = "Insert ERP_YCXUATKHO_DDH(ycxk_fk, hoadon_fk) " +
					"select "+ycxkCurrentId+", pk_seq from ERP_HOADON where pk_seq in ( " + hoadon_fk + " )  ";
			System.out.println("2.chen ERP_YCXUATKHO: " + query);
			if( db.updateReturnInt(query) <= 0 )
			{
				System.out.println("Không thể tạo mới ERP_YCXUATKHO_DDH " + query);
				return false;
			}
			
			query = "insert ERP_YCXUATKHO_SANPHAM( ycxk_fk, sanpham_fk, soluongDAT, tonkho, daxuat, soluongXUAT, LOAI, SCHEME ) " +
						"\n select '"+ ycxkCurrentId +"',ddh.PK_SEQ, ISNULL(ddh.soluongDAT, 0) as xuat,      " +      
						"\n 	ISNULL( ( select AVAILABLE from ERP_KHOTT_SANPHAM where khott_fk = '"+ kho_fk +"' and sanpham_fk = ddh.PK_SEQ  ), 0) + ISNULL(ddh.soluongDAT, 0) as tonkho   " +      
						"\n 	,ISNULL( ddh.soluongDAT, 0) as daxuat,  ISNULL( ddh.soluongDAT, 0) as soluongXUAT,ddh.loai,ddh.scheme " +      
						"\n from    " +      
						"\n (    " +      
						"\n 	select sp.PK_SEQ, sp.MA, sp.TEN, dv.DONVI, scheme, loai, SUM(dathang.soluong) as soluongDAT     " +      
						"\n 	from    " +      
						"\n 	(    " +      
						"\n  		select a.sanpham_fk, b.DVDL_FK as dvCHUAN,     " +      
						"\n  			isnull(a.SoLuong_Chuan, a.SOLUONG) as soluong, 0 as loai, (select top(1) bb.SCHEME from ERP_HOADON_DDH aa inner join ERP_DONDATHANG_SANPHAM bb " +      
						"\n	on aa.DDH_FK=bb.dondathang_fk " +      
						"\n	where aa.HOADON_FK="+ hoadon_fk +"  and bb.sanpham_fk=a.SANPHAM_FK) as scheme    " +      
						"\n  		from ERP_HOADON_SP a inner join SANPHAM b on a.sanpham_fk = b.PK_SEQ     " +      
						"\n  		where a.HOADON_FK in (    "+ hoadon_fk +"    )    " +      
						"\n 	)    " +      
						"\n 	dathang inner join SANPHAM sp on dathang.sanpham_fk = sp.PK_SEQ    " +      
						"\n 			inner join DONVIDOLUONG dv on sp.DVDL_FK = dv.PK_SEQ    " +      
						"\n 	group by sp.PK_SEQ, sp.MA, sp.TEN, dv.DONVI, scheme, loai   " +      
						"\n )    " +      
						"\n ddh order by SCHEME asc   " ;
			if( db.updateReturnInt(query) <= 0 )
			{
				System.out.println("Không thể tạo mới ERP_YCXUATKHO_SANPHAM " + query);
				return false;
			}
			
			query = "insert ERP_YCXUATKHO_SANPHAM_CHITIET( ycxk_fk, SANPHAM_FK, solo, ngayhethan, ngaynhapkho, soluong, loai, scheme, kho_fk ) " +
					"\n		select '"+ycxkCurrentId+"',ddh.PK_SEQ,ddh.SOLO,ddh.NGAYHETHAN,ddh.NGAYNHAPKHO,ddh.soluongDAT ,ddh.loai,ddh.scheme,'"+kho_fk+"' as kho_fk  " +
					"\n		from   " +
					"\n		(   " +
					"\n		select sp.PK_SEQ, sp.MA, sp.TEN, dv.DONVI, scheme , loai, SUM(dathang.soluong) as soluongDAT,dathang.SOLO,dathang.NGAYHETHAN,dathang.NGAYNHAPKHO " +   
					"\n			from   " +
					"\n		(    " +
					"\n		select a.MA, b.DVDL_FK as dvCHUAN,      " +
					"\n			a.SoLuong_Chuan as soluong, 0 as loai, (select top(1) bb.SCHEME from ERP_HOADON_DDH aa inner join ERP_DONDATHANG_SANPHAM_CHITIET bb " +
					"\n	on aa.DDH_FK=bb.dondathang_fk " +
					"\n	where aa.HOADON_FK="+ hoadon_fk +" and bb.sanpham_fk=(select PK_SEQ from SANPHAM where a.MA=MA))as scheme  ,a.NGAYHETHAN,NGAYNHAPKHO,a.SOLO    " +
					"\n			from ERP_HOADON_SP_CHITIET a inner join SANPHAM b on a.ma = b.MA      " +
					"\n		where a.HOADON_FK in ( "+ hoadon_fk +" )    " +
					"\n		)    " +
					"\n			dathang inner join SANPHAM sp on dathang.MA = sp.MA    " +
					"\n			 			inner join DONVIDOLUONG dv on sp.DVDL_FK = dv.PK_SEQ   " +
					"\n			group by sp.PK_SEQ, sp.MA, sp.TEN, dv.DONVI, scheme, loai  ,dathang.SOLO,dathang.NGAYHETHAN,dathang.NGAYNHAPKHO " +
					"\n		)  ddh " ;
				

			if( db.updateReturnInt(query) <= 0 )
			{
				System.out.println("Không thể tạo mới ERP_YCXUATKHO_SANPHAM_CHITIET " + query);
				return false;
			}	
			
			String kq=Chot_phieuxuatkho(db,ycxkCurrentId,userid,khachhang_fk);
			if( kq.trim().length() > 0 )
			{
				System.out.println("Không thể chốt phiếu xuất kho " );
				return false;
			}	
		} 
		catch (Exception e) 
		{
			e.printStackTrace();
				
			System.out.println( "Exception: " + e.getMessage());
			return false;
		}
		
		return true;
	}
	
	
	private String HuyPhieuXuatKhoNPP(String ycxkId, String npp_dat_fk, dbutils db,String dondathang_fk1)
	{
		geso.dms.center.util.Utility util_kho=new geso.dms.center.util.Utility();
		String msg = "";
		try
		{			
			//CHECK KHOA SO THANG
			String query ="";
			//CHECK XEM DUOI NPP DA NHAN HANG CHUA
			if (npp_dat_fk.trim().length() > 0)
			{
				//Tu dong tao nhan hang
				query = " select count(*) as soDONG from NHAPHANG where YCXKNPP_FK = '" + ycxkId + "' and trangthai = '1' ";
				
				int soDONG = 0;
				ResultSet rs = db.get(query);
				{
					if(rs.next())
						soDONG = rs.getInt("soDONG");
					rs.close();
				}
				
				if(soDONG > 0)
				{
					msg = "Hóa đơn đã có nhận hàng, bạn không thể hủy / xóa ";
					return msg;
				}
				
				//XOA NHAN HANG
				query = " delete NHAPHANG_SP where nhaphang_fk in ( select pk_seq from NHAPHANG where YCXKNPP_FK = '" + ycxkId + "' )  ";
				if(!db.update(query))
				{
					msg = "Không thể cập nhật NHAPHANG_SP:  " + query;
					return msg;
				}
				
				query = " delete NHAPHANG where YCXKNPP_FK = '" + ycxkId + "'   ";
				if(!db.update(query))
				{
					msg = "Không thể cập nhật NHAPHANG_SP:  " + query;
					return msg;
				}
			}
			
			//MỞ LẠI TRẠNG THÁI ĐƠN HÀNG
			
			//MO LAI TRANG THAI DON HANG
			query = "update ERP_DONDATHANGNPP set trangthai = '0' ,checkkho='1' where pk_seq in  " +
					" ( select DDH_FK from ERP_YCXUATKHONPP_DDH where ycxk_fk = '" + ycxkId + "' ) and TrangThai!=0 ";
			if(db.updateReturnInt(query)!=1)
			{
				msg = "Đơn đặt hàng này đã mở trạng thái rồi "+query;
				return msg;
			}
			
			query = "Update ERP_YCXUATKHONPP set TRANGTHAI= '3' where pk_seq = '" + ycxkId + "' --and TrangThai!=3 ";
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
	    
		IErpHoadontaichinhList obj = new ErpHoadontaichinhList();
		obj.setLoaidonhang(loaidonhang);
		
		String noibo = request.getParameter("NOIBO") == null ? "0" : request.getParameter("NOIBO");
	    obj.setNOIBO(noibo);
	    
		String km = request.getParameter("km");
	    if(km!=null)
	    {
	    	obj.setLoaikm(km);
	    }
		
	    Utility util = new Utility();
	    
		HttpSession session = request.getSession();
	    String userId = util.antiSQLInspection(request.getParameter("userId")); 
    	obj.setUserId(userId);

	    if(action.equals("Tao moi"))
	    {
	    	IErpHoadontaichinh lsxBean = new ErpHoadontaichinh();
	    	lsxBean.setUserId(userId);
	    	lsxBean.createRs();
	    	session.setAttribute("dvkdId", "");
			session.setAttribute("kbhId", "");
			session.setAttribute("nppId", "");
    		
	    	session.setAttribute("lsxBean", lsxBean);
	    	
    		String nextJSP = request.getContextPath() + "/pages/Center/ErpHoaDonTaiChinhNew.jsp";
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
		    	
		    	String nextJSP = request.getContextPath() + "/pages/Center/ErpHoaDonTaiChinh.jsp";
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
	    		String nextJSP = request.getContextPath() + "/pages/Center/ErpHoaDonTaiChinh.jsp";
	    		response.sendRedirect(nextJSP);
	    	}
	    }
	}
	
	private String getSearchQuery(HttpServletRequest request, IErpHoadontaichinhList obj)
	{
		String query="";
		
		if( obj.getNOIBO().equals("1") )
		{
			query = "select isnull(a.loaihd,0) loaihd ,a.PK_SEQ, a.trangthai, a.ngayxuatHD, a.sohoadon as sohoadon, a.kyhieu as kyhieu, NV.TEN as nguoitao, a.tongtienavat as tongtien,  " +
					" npp.TEN as khTEN, a.NGAYSUA, a.NGAYTAO, NV2.TEN as nguoisua  " +
					"from ERP_HOADON a " +
					" inner join ERP_HOADON_DDH ddh on ddh.HOADON_FK=a.PK_SEQ "+
					" inner join ERP_DONDATHANG dh on dh.PK_SEQ=ddh.DDH_FK "+
					" left join NHAPHANPHOI npp on a.NPP_FK=npp.PK_SEQ  " +
					" inner join NHANVIEN nv on a.NGUOITAO = nv.PK_SEQ   " +
					" inner join NHANVIEN nv2 on a.NGUOISUA = nv2.PK_SEQ "+
					"where  a.noibo = '1'   ";
		}
		else
		{
			if(!obj.getLoaikm().equals("1"))
			{
				query = "select isnull(a.loaihd,0) loaihd ,a.PK_SEQ, a.trangthai, a.ngayxuatHD, a.sohoadon as sohoadon, a.kyhieu as kyhieu, NV.TEN as nguoitao, a.tongtienavat as tongtien,  " +
						" npp.TEN as khTEN, a.NGAYSUA, a.NGAYTAO, NV2.TEN as nguoisua  " +
						"from ERP_HOADON a " +
						" inner join ERP_HOADON_DDH ddh on ddh.HOADON_FK=a.PK_SEQ "+
						" inner join ERP_DONDATHANG dh on dh.PK_SEQ=ddh.DDH_FK "+
						" left join NHAPHANPHOI npp on a.NPP_FK=npp.PK_SEQ  " +
						" inner join NHANVIEN nv on a.NGUOITAO = nv.PK_SEQ   " +
						" inner join NHANVIEN nv2 on a.NGUOISUA = nv2.PK_SEQ "+
						"where a.noibo = '0' and loaihoadon != '1'  and  dh.isKM <> 1   ";
			}
			else
			{
				query = "select isnull(a.loaihd,0) loaihd, a.PK_SEQ, a.trangthai, a.ngayxuatHD, a.sohoadon as sohoadon, a.kyhieu as kyhieu, NV.TEN as nguoitao, a.tongtienavat as tongtien,   " +
						"	npp.TEN as khTEN, a.NGAYSUA, a.NGAYTAO, NV2.TEN as nguoisua  " +
						"from ERP_HOADON a " +
						" inner join ERP_HOADON_DDH ddh on ddh.HOADON_FK=a.PK_SEQ "+
						" inner join ERP_DONDATHANG dh on dh.PK_SEQ=ddh.DDH_FK "+
						" inner join NHAPHANPHOI npp on a.NPP_FK=npp.PK_SEQ  " +
						" inner join NHANVIEN nv on a.NGUOITAO = nv.PK_SEQ   " +
						" inner join NHANVIEN nv2 on a.NGUOISUA = nv2.PK_SEQ "+
						 "where a.noibo = '0' and ( loaihoadon = '1' or dh.isKM =1 )   ";
			}
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
	
	private String getSumQuery(HttpServletRequest request, IErpHoadontaichinhList obj) 
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
	
	private void CreateStaticHeader(Workbook workbook, IErpHoadontaichinhList obj) 
	{
		Worksheets worksheets = workbook.getWorksheets();

	    Worksheet worksheet = worksheets.getSheet(0);
	   	   
	    Cells cells = worksheet.getCells();
	    Style style;
	   	    
	    //LẤY THÔNG TIN HO
	    dbutils db = new dbutils();
	    String ten = "";
	    String diachi = "";
	    String dienthoai = "";
	    String fax = "";
	    
	    String query = "select ten, diachi, dienthoai, fax from NHAPHANPHOI where pk_seq = '1'";
	    ResultSet rs = db.get(query);
	    if(rs != null)
	    {
	    	try 
	    	{
	    		if(rs.next())
	    		{
		    		ten = rs.getString("ten");
		    		diachi = rs.getString("diachi");
		    		dienthoai = rs.getString("dienthoai");
		    		fax = rs.getString("fax");
	    		}
	    		
				rs.close();
			} 
	    	catch (Exception e) { e.printStackTrace(); }
	    }
	    
	    Cell cell = cells.getCell("D1"); 
	    cell.setValue(ten.toUpperCase());	    
	    style = cell.getStyle();
		Font font2 = new Font();	
		font2.setName("Times New Roman");
		font2.setSize(11);
		font2.setBold(false);
		style.setFont(font2);
		style.setHAlignment(TextAlignmentType.CENTER);					
		cell.setStyle(style);
		
		font2 = new Font();	
		font2.setName("Times New Roman");
		font2.setSize(12);
		font2.setBold(false);
		style.setFont(font2);
		style.setHAlignment(TextAlignmentType.CENTER);					
		cell.setStyle(style);
		
		cell = cells.getCell("D2");
	    cell.setValue("Địa chỉ: " + diachi);
	    style = cell.getStyle();
	    style.setFont(font2);
		cell.setStyle(style);
		
		font2 = new Font();	
		font2.setName("Times New Roman");
		font2.setSize(12);
		font2.setBold(false);
		style.setFont(font2);
		style.setHAlignment(TextAlignmentType.CENTER);					
		cell.setStyle(style);
		
		cell = cells.getCell("D3");
	    cell.setValue("Tel: " + dienthoai + " - Fax: " + fax );
	    style = cell.getStyle();
	    style.setFont(font2);
		cell.setStyle(style);
		
		font2 = new Font();	
		font2.setName("Times New Roman");
		font2.setBold(false);
		font2.setSize(12);
	   
	    cell = cells.getCell("A8");
	    cell.setValue("Từ ngày: " + getFormatDate(obj.getTungay()) + " đến ngày: " + getFormatDate(obj.getDenngay()) );
	    style = cell.getStyle();
	    style.setFont(font2);
		cell.setStyle(style);
	    
	    worksheet.setName("BangKe");
	}
	
	private void CreateStaticData(Workbook workbook, IErpHoadontaichinhList obj, String query ) 
	{
		Worksheets worksheets = workbook.getWorksheets();
		Worksheet worksheet = worksheets.getSheet(0);
		Cells cells = worksheet.getCells();

		dbutils db = new dbutils();
		ResultSet hdRs = db.get(query);

		Cell cell_mau = cells.getCell("A9");
		Cell cell = null;

		Style style;
		Font font2 = new Font();
		font2.setName("Times New Roman");				
		font2.setSize(8);

		Font font3 = new Font();
		font3.setName("Times New Roman");				
		font3.setSize(8);
		font3.setBold(true);

		int i = 10;
		if(hdRs != null)
		{
			try 
			{
				int stt = 1;
				double totalTRUOCVAT = 0;
				double totalVAT = 0;

				while(hdRs.next())
				{
					totalTRUOCVAT += Math.round(hdRs.getDouble("DOANHTHU"));
					totalVAT += hdRs.getDouble("VAT");

					cell = cells.getCell("A" + Integer.toString(i));	cell.setValue(stt); 			style = cell_mau.getStyle(); style.setFont(font2); cell.setStyle(style); setCellBorderStyle(cell, HorizontalAlignmentType.CENTRED);
					cell = cells.getCell("B" + Integer.toString(i));	cell.setValue(hdRs.getString("MA")); 		style = cell_mau.getStyle(); style.setFont(font2); cell.setStyle(style); setCellBorderStyle(cell, HorizontalAlignmentType.LEFT);
					cell = cells.getCell("C" + Integer.toString(i));	cell.setValue(hdRs.getString("TEN")); 		style = cell_mau.getStyle(); style.setFont(font2); cell.setStyle(style); setCellBorderStyle(cell, HorizontalAlignmentType.LEFT);
					cell = cells.getCell("D" + Integer.toString(i));	cell.setValue(hdRs.getString("DONVI")); 		style = cell_mau.getStyle(); style.setFont(font2); cell.setStyle(style); setCellBorderStyle(cell, HorizontalAlignmentType.LEFT);		
					cell = cells.getCell("E" + Integer.toString(i));	cell.setValue(hdRs.getDouble("SOLUONG")); 	style = cell_mau.getStyle(); style.setFont(font2); cell.setStyle(style); setCellBorderStyle(cell, HorizontalAlignmentType.RIGHT);
					ReportAPI.setCellBackground(cell, Color.WHITE, BorderLineType.THIN, false, 3);


					cell = cells.getCell("F" + Integer.toString(i));	cell.setValue(hdRs.getDouble("DONGIA")); 		style = cell_mau.getStyle(); style.setFont(font2); cell.setStyle(style); setCellBorderStyle(cell, HorizontalAlignmentType.RIGHT);
					style.setCustom("#,##0.0000");
					cell.setStyle(style);

					cell = cells.getCell("G" + Integer.toString(i));	cell.setValue(Math.round(hdRs.getDouble("DOANHTHU"))); 		style = cell_mau.getStyle(); style.setFont(font2); cell.setStyle(style); setCellBorderStyle(cell, HorizontalAlignmentType.RIGHT);
					ReportAPI.setCellBackground(cell, Color.WHITE, BorderLineType.THIN, false, 3);

					cell = cells.getCell("H" + Integer.toString(i));	cell.setValue(hdRs.getDouble("VAT")); 			style = cell_mau.getStyle(); style.setFont(font2); cell.setStyle(style); setCellBorderStyle(cell, HorizontalAlignmentType.RIGHT);
					ReportAPI.setCellBackground(cell, Color.WHITE, BorderLineType.THIN, false, 3);

					cell = cells.getCell("I" + Integer.toString(i));	cell.setValue( hdRs.getDouble("AVAT")  ); 		style = cell_mau.getStyle(); style.setFont(font2); cell.setStyle(style); setCellBorderStyle(cell, HorizontalAlignmentType.RIGHT);
					ReportAPI.setCellBackground(cell, Color.WHITE, BorderLineType.THIN, false, 3);


					i++;
					stt ++;
				}
				hdRs.close();

				/*query = "	select loai.ma as MaLoai,ck.diengiai,ck.thuevat, sum( ROUND( ck.ChietKhau , 0 ) ) as ChietKhau ,   "+
						"		sum( ROUND(  ck.ChietKhau * ( ck.thueVAT / 100.0 ) ,0 ) )   	as VAT,  "+
						"		sum( ROUND( ck.ChietKhau * ( 1 + ck.thueVAT / 100.0 ), 0 ) )   	as AVAT "+
						"	from hoadon_chietkhau ck left join loaick loai on loai.ma=ck.diengiai " +
						"   where isnull(ck.HienThi,0)=1 and hoadon_fk in  (select pk_seq from hoadon  a where   trangthai not in ( 1 , 3, 5 ) and isnull(loaihoadon, '0') = '0'   "+condition+" )   "+
						"	group by loai.ma,ck.diengiai,ck.thuevat  ";

				System.out.println("-----LAY CHIET KHAU: " + query);
				ResultSet rsCK = db.get(query);
				if(rsCK != null)
				{
					while(rsCK.next())
					{
						double ck_truocVAT = Math.round( -1 * rsCK.getDouble("chietkhau"));
						double ck_VAT = -1 * rsCK.getDouble("VAT");
						double ck_sauVAT =-1 * rsCK.getDouble("AVAT");

						totalTRUOCVAT += ck_truocVAT;
						totalVAT += ck_VAT;

						cell = cells.getCell("A" + Integer.toString(i));	cell.setValue(stt); 		style = cell_mau.getStyle(); style.setFont(font2); cell.setStyle(style); setCellBorderStyle(cell, HorizontalAlignmentType.CENTRED);
						cell = cells.getCell("B" + Integer.toString(i));	cell.setValue(rsCK.getString("maloai")); 		style = cell_mau.getStyle(); style.setFont(font2); cell.setStyle(style); setCellBorderStyle(cell, HorizontalAlignmentType.LEFT);
						cell = cells.getCell("C" + Integer.toString(i));	cell.setValue(rsCK.getString("diengiai")); 		style = cell_mau.getStyle(); style.setFont(font2); cell.setStyle(style); setCellBorderStyle(cell, HorizontalAlignmentType.LEFT);		
						cell = cells.getCell("D" + Integer.toString(i));	cell.setValue(" "); 	style = cell_mau.getStyle(); style.setFont(font2); cell.setStyle(style); setCellBorderStyle(cell, HorizontalAlignmentType.CENTRED);
						cell = cells.getCell("E" + Integer.toString(i));	cell.setValue(0); 		style = cell_mau.getStyle(); style.setFont(font2); cell.setStyle(style); setCellBorderStyle(cell, HorizontalAlignmentType.RIGHT);
						cell = cells.getCell("F" + Integer.toString(i));	cell.setValue(0); 		style = cell_mau.getStyle(); style.setFont(font2); cell.setStyle(style); setCellBorderStyle(cell, HorizontalAlignmentType.RIGHT);
						cell = cells.getCell("G" + Integer.toString(i));	cell.setValue(ck_truocVAT); 		style = cell_mau.getStyle(); style.setFont(font2); cell.setStyle(style); setCellBorderStyle(cell, HorizontalAlignmentType.RIGHT);
						ReportAPI.setCellBackground(cell, Color.WHITE, BorderLineType.THIN, false, 3);

						cell = cells.getCell("H" + Integer.toString(i));	cell.setValue(ck_VAT); 		style = cell_mau.getStyle(); style.setFont(font2); cell.setStyle(style); setCellBorderStyle(cell, HorizontalAlignmentType.RIGHT);
						ReportAPI.setCellBackground(cell, Color.WHITE, BorderLineType.THIN, false, 3);

						cell = cells.getCell("I" + Integer.toString(i));	cell.setValue(ck_sauVAT); 			style = cell_mau.getStyle(); style.setFont(font2); cell.setStyle(style); setCellBorderStyle(cell, HorizontalAlignmentType.RIGHT);
						ReportAPI.setCellBackground(cell, Color.WHITE, BorderLineType.THIN, false, 3);

						stt++;
						i++;
					}
					rsCK.close();
				}*/

				//THEM DONG TONG CONG
				cell = cells.getCell("A" + Integer.toString(i));	cell.setValue(""); 		style = cell_mau.getStyle(); style.setFont(font2); cell.setStyle(style); setCellBorderStyle(cell, HorizontalAlignmentType.LEFT);
				cell = cells.getCell("B" + Integer.toString(i));	cell.setValue(""); 		style = cell_mau.getStyle(); style.setFont(font2); cell.setStyle(style); setCellBorderStyle(cell, HorizontalAlignmentType.LEFT);
				cell = cells.getCell("C" + Integer.toString(i));	cell.setValue(""); 		style = cell_mau.getStyle(); style.setFont(font2); cell.setStyle(style); setCellBorderStyle(cell, HorizontalAlignmentType.LEFT);		
				cell = cells.getCell("D" + Integer.toString(i));	cell.setValue(""); 		style = cell_mau.getStyle(); style.setFont(font2); cell.setStyle(style); setCellBorderStyle(cell, HorizontalAlignmentType.CENTRED);
				cell = cells.getCell("E" + Integer.toString(i));	cell.setValue(""); 		style = cell_mau.getStyle(); style.setFont(font2); cell.setStyle(style); setCellBorderStyle(cell, HorizontalAlignmentType.LEFT);
				cell = cells.getCell("F" + Integer.toString(i));	cell.setValue(""); 		style = cell_mau.getStyle(); style.setFont(font2); cell.setStyle(style); setCellBorderStyle(cell, HorizontalAlignmentType.CENTRED);
				cell = cells.getCell("G" + Integer.toString(i));	cell.setValue(Math.round(totalTRUOCVAT)); 		style = cell_mau.getStyle(); style.setFont(font2); cell.setStyle(style); setCellBorderStyle(cell, HorizontalAlignmentType.RIGHT);
				ReportAPI.setCellBackground(cell, Color.WHITE, BorderLineType.THIN, false, 3);

				cell = cells.getCell("H" + Integer.toString(i));	cell.setValue( Math.round( totalVAT)); 		style = cell_mau.getStyle(); style.setFont(font2); cell.setStyle(style); setCellBorderStyle(cell, HorizontalAlignmentType.RIGHT);
				ReportAPI.setCellBackground(cell, Color.WHITE, BorderLineType.THIN, false, 3);

				cell = cells.getCell("I" + Integer.toString(i));	cell.setValue(Math.round( totalTRUOCVAT + totalVAT )); 			style = cell_mau.getStyle(); style.setFont(font2); cell.setStyle(style); setCellBorderStyle(cell, HorizontalAlignmentType.RIGHT);
				ReportAPI.setCellBackground(cell, Color.WHITE, BorderLineType.THIN, false, 3);

				i++;
				
				cell = cells.getCell("B" + Integer.toString(i)); 	cell.setValue(" "); 		style = cell_mau.getStyle(); style.setFont(font2); cell.setStyle(style); setCellBorderStyle2(cell);
				ReportAPI.mergeCells(worksheet, i, i, 1, 2);
				
				cell = cells.getCell("F" + Integer.toString(i));	cell.setValue(" "); 		style = cell_mau.getStyle(); style.setFont(font2); cell.setStyle(style); setCellBorderStyle2(cell);
				ReportAPI.mergeCells(worksheet, i, i, 5, 7);
				
				i++;
				ReportAPI.mergeCells(worksheet, i, i, 1, 2);
				cell = cells.getCell("B" + Integer.toString(i)); 	cell.setValue("Bên bán"); 		style = cell_mau.getStyle(); style.setFont(font2); cell.setStyle(style); setCellBorderStyle2(cell);
				
				ReportAPI.mergeCells(worksheet, i, i, 5, 7);
				cell = cells.getCell("F" + Integer.toString(i));	cell.setValue("Bên mua"); 		style = cell_mau.getStyle(); style.setFont(font2); cell.setStyle(style); setCellBorderStyle2(cell);
				
				i++;
				ReportAPI.mergeCells(worksheet, i, i, 1, 2);
				cell = cells.getCell("B" + Integer.toString(i));	cell.setValue("Ký tên, đóng dấu"); 		style = cell_mau.getStyle();  style.setFont(font2); cell.setStyle(style); setCellBorderStyle2(cell);
				
				ReportAPI.mergeCells(worksheet, i, i, 5, 7);
				cell = cells.getCell("F" + Integer.toString(i));	cell.setValue("Ký tên, đóng dấu"); 		style = cell_mau.getStyle(); style.setFont(font2); cell.setStyle(style); setCellBorderStyle2(cell);
			}
			catch (Exception e)
			{ 
				e.printStackTrace(); 
			}
		}
	}
	
	private void setCellBorderStyle(Cell cell, short alignment) 
	{
		Style style = cell.getStyle();
		//style.setHAlignment(HorizontalAlignmentType.CENTRED);
		style.setHAlignment(alignment);
		/*style.setBorderLine(BorderType.TOP, 1);
		style.setBorderLine(BorderType.RIGHT, 1);
		style.setBorderLine(BorderType.BOTTOM, 1);
		style.setBorderLine(BorderType.LEFT, 1);*/
		cell.setStyle(style);
	}
	
	private void setCellBorderStyle2(Cell cell) 
	{
		Style style = cell.getStyle();
		style.setHAlignment(HorizontalAlignmentType.CENTRED);
		style.setBorderLine(BorderType.TOP, 0);
		style.setBorderLine(BorderType.RIGHT, 0);
		style.setBorderLine(BorderType.BOTTOM, 0);
		style.setBorderLine(BorderType.LEFT, 0);
		cell.setStyle(style);
	}
	
	private String getFormatDate(String date) 
	{
		try
		{
			String[] arr = date.split("-");
	
	        return arr[2] + "/" + arr[1] + "/" + arr[0];	
		}
		catch(Exception ex)
		{
			return date;
		}
	}
	
	
}
