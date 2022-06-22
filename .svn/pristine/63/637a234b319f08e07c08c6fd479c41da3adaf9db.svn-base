package geso.dms.distributor.servlets.donhangctv;

import geso.dms.center.util.Utility;
import geso.dms.distributor.beans.donhangctv.IDonhangctv;
import geso.dms.distributor.beans.donhangctv.IDonhangctvList;
import geso.dms.distributor.beans.donhangctv.imp.Donhangctv;
import geso.dms.distributor.beans.donhangctv.imp.DonhangctvList;
import geso.dms.distributor.db.sql.dbutils;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.URLDecoder;
import java.sql.ResultSet;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.aspose.cells.BorderType;
import com.aspose.cells.Cell;
import com.aspose.cells.Cells;
import com.aspose.cells.FileFormatType;
import com.aspose.cells.Font;
import com.aspose.cells.HorizontalAlignmentType;
import com.aspose.cells.Style;
import com.aspose.cells.Workbook;
import com.aspose.cells.Worksheet;
import com.aspose.cells.Worksheets;

public class DonhangctvSvl extends HttpServlet
{
	private static final long serialVersionUID = 1L;
	public DonhangctvSvl()
	{
		super();
	}
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		IDonhangctvList obj;
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");
		
		HttpSession session = request.getSession();
		
		Utility util = new Utility();
		
		String querystring = URLDecoder.decode(request.getQueryString(), "UTF-8");
		String userId = util.getUserId(querystring);
		
		if (userId.length() == 0)
			userId = util.antiSQLInspection(request.getParameter("userId"));
		
		String action = util.getAction(querystring);
		
		obj = new DonhangctvList();
		
		obj.setUserId(userId);
		
		obj.setLoainhanvien(session.getAttribute("loainhanvien"));
	    obj.setDoituongId(session.getAttribute("doituongId"));
		
		String loaidonhang = request.getParameter("loaidonhang");
	    if(loaidonhang == null)
	    	loaidonhang = "";
	    obj.setLoaidonhang(loaidonhang);
	    
		String duyetss = request.getParameter("duyet");
	    if(duyetss == null)
	    	duyetss = "";
	    obj.setDuyetSS(duyetss);
	    
		if (action.equals("delete"))
		{
			String lsxId = util.getId(querystring);
			String msg = this.DeleteChuyenKho(lsxId, userId);
			obj.setMsg(msg);	
		} 
		else if (action.equals("chot"))
		{
			String lsxId = util.getId(querystring);
			String msg = "";
			/*if(duyetss.length() > 0)*/
				msg = this.Chot(lsxId, userId, 1);
			/*else
				msg = this.Chot(lsxId, userId, 0);*/
			obj.setMsg(msg);
		} 
		else if (action.equals("UnChot"))
		{
			String lsxId = util.getId(querystring);
			String msg = this.MoChot(lsxId, userId, 1);
			obj.setMsg(msg);
		} 
	
		String view = request.getParameter("view");
	    if(view == null)
	    	view = "";
	
	    String search = "";
	    if (querystring.contains("startSearchList=true"))
	    	search = getSearchQuery(obj);
	    obj.init(search);
	    
		session.setAttribute("obj", obj);
		String nextJSP="";
		if(view.equals("CTV"))
			 nextJSP = request.getContextPath() + "/pages/Distributor/XemCTV.jsp";
		else
		 nextJSP = request.getContextPath() + "/pages/Distributor/DonHangCTV.jsp";
		response.sendRedirect(nextJSP);
	}
	
	private String Chot(String id, String userId, int loai)
	{
		dbutils db = new dbutils();
		String msg = "";

		try
		{
			db.getConnection().setAutoCommit(false);

			//CHECK TRANG THAI CO HOP LE DE THAO TAC TIEP HAY KHONG
			String query = " SELECT trangthai, NGAYNHAP, npp_fk FROM DONHANGCTV WHERE PK_SEQ = "+ id;
			ResultSet rsCheck = db.get(query);
			
			String trangthai = "";
			String ngayyeucau = "";
			String nppId = "";
			if(rsCheck != null)
			{
				if(rsCheck.next())
				{
					trangthai = rsCheck.getString("trangthai");
					ngayyeucau = rsCheck.getString("NGAYNHAP");
					nppId = rsCheck.getString("npp_fk");
				}
				rsCheck.close();
			}
			
			if( !trangthai.equals("0") )
			{
				msg = "Trạng thái đơn hàng không hợp lệ. Vui lòng liên hệ Admin để được xử lý.";
				db.getConnection().rollback();
				return msg;
			}

			/*geso.dms.distributor.util.Utility dutil = new geso.dms.distributor.util.Utility();
			int checkks = dutil.CheckKhoaSoCTV(db, nppId, "", "DONHANGCTV", "NGAYNHAP", id);
			if(checkks != -1 )
			{
				if(checkks == 0)
				{
					msg = "Ngày đơn hàng nằm trong tháng đã khóa sổ CTV. Vui lòng kiểm tra lại.";
					db.getConnection().rollback();
					return msg;
				}
			}*/
			
			// Trạng thái bằng 1, đã chốt, SS chưa duyệt, Trạng thái bằng 2, SS đã duyệt
			/*if(loai == 0)
				 query = "update donhangctv set trangthai = '1', nguoisua = '" + userId + "', Modified_Date=getdate() where pk_seq = '" + id + "' and trangthai = 0 ";
			else*/
				query = "update donhangctv set trangthai = '2', nguoisua = '" + userId + "', Modified_Date=getdate() " + 
						" where pk_seq = '" + id + "' and trangthai in ( 0, 1 ) ";
			
			System.out.println("----CAP NHAT: " + query);
			if(db.updateReturnInt(query)!=1)
			{
				msg = "2.Khong thể cập nhật trạng thái: " + query;
				db.getConnection().rollback();
				return msg;
			}
			
			//CHECK SAU KHI CHOT KHONG DUOC AM
			
			
			//PHAN BO SỐ LƯỢNG VÀO ĐƠN HÀNG CŨ HƠN
			query = " select a.ngaynhap, SUBSTRING(a.ngaynhap, 0, 8 ) + '-01' as dauthang, a.KHACHHANG_FK, a.ddkd_fk, b.ctv_fk, b.SANPHAM_FK, b.soluong, b.DONGIA, b.DONVI "+
					" from DONHANGCTV a inner join DONHANGCTV_SP b on a.PK_SEQ = b.DONHANGCTV_FK"+
					" where b.DONHANGCTV_FK = '" + id + "' " ;
					//" group by a.ngaynhap, a.KHACHHANG_FK, a.ddkd_fk, b.SANPHAM_FK, b.DONGIA, b.DONVI ";
			ResultSet rs = db.get(query);
			if( rs != null )
			{
				while( rs.next() )
				{
					String ngaynhap = rs.getString("dauthang");
					String khId = rs.getString("KHACHHANG_FK");
					String ddkdId = rs.getString("ddkd_fk");
					String ctvId = rs.getString("ctv_fk");
					String spId = rs.getString("SANPHAM_FK");
					String dongia = rs.getString("DONGIA");
					String donvi = rs.getString("DONVI");
					double soluongCAN = rs.getDouble("SOLUONG");
					
					query = " select DT.donhangId, DT.loaidonhang, DT.NgayDonHang, DT.soluong - ISNULL( DADUNG.SOLUONG, 0 ) as conlai "+
							" from"+
							" ("+
							"\n 	select e.PK_SEQ as donhangId, 0 as loaidonhang, e.NgayDonHang, SUM( c.soluong ) as soluong, d.DONVI, a.dongia as dongia "+
							" 		from ERP_DONDATHANGNPP_SANPHAM a inner join SANPHAM b on a.sanpham_fk = b.PK_SEQ "+
							" 			inner join ERP_DONDATHANGNPP_SANPHAM_CHITIET c on a.dondathang_fk = c.dondathang_fk and a.sanpham_fk = c.SANPHAM_FK and a.dvdl_fk = c.DVDL_FK"+
							" 			inner join DONVIDOLUONG d on c.DVDL_FK = d.PK_SEQ"+
							" 			inner join ERP_DONDATHANGNPP e on a.dondathang_fk = e.PK_SEQ"+
							//"			left join KHACHHANG_CTV_SANPHAM f on e.KHACHHANG_FK = f.khachhang_fk and a.sanpham_fk = f.sanpham_fk"+
							" 		where e.TRANGTHAI in ( 2, 4 ) and e.KHACHHANG_FK = '" + khId + "' "+
							" 			and a.sanpham_fk = '" + spId + "'	and a.dongia = '" + dongia + "' " + 
							" 			and e.NgayDonHang < '" + ngaynhap + "' "+
							//"			and e.ngaydonhang >= isnull(f.ngay, '')"+
							" 		group by d.DONVI, a.dongia, e.PK_SEQ, e.NgayDonHang"+	
							"\n		union all "+
							"\n		select e.PK_SEQ, 1 as loaidonhang, e.NGAYNHAP, SUM( a.soluong ) as soluong, d.DONVI, a.giamua as dongia "+ 
							"		from DONHANG_SANPHAM a inner join SANPHAM b on a.sanpham_fk = b.PK_SEQ "+
							"			inner join DONVIDOLUONG d on b.DVDL_FK = d.PK_SEQ "+
							"			inner join DONHANG e on a.DONHANG_FK = e.PK_SEQ "+
							"			inner join KHACHHANG f on e.KHACHHANG_FK = f.PK_SEQ "+
							"		where e.KHACHHANG_FK = '" + khId + "' and e.KBH_FK in (100052, 100059) "+
							" 			and a.sanpham_fk = '" + spId + "'	and a.giamua = '" + dongia + "' " +
							"			and e.NGAYNHAP < '" + ngaynhap + "' "+
							"			and (select COUNT(*) from HOADON_DDH hddh inner join HOADON hd on hddh.HOADON_FK = hd.PK_SEQ where hddh.DDH_FK = e.PK_SEQ and hd.trangthai in (2,4)) > 0 "+
							"		group by d.DONVI, a.giamua, e.PK_SEQ, e.NGAYNHAP "+
							"\n )"+
							" DT left join"+
							" ("+
							" 		select b.DONHANG_FK, sum(b.SOLUONG) as soluong, b.DONVI, b.DONGIA, b.loaidonhang "+
							" 		from DONHANGCTV a inner join DONHANGCTV_SP_CHITIET b on a.PK_SEQ = b.DONHANGCTV_FK"+
							" 		where a.KHACHHANG_FK = '" + khId + "'  and b.sanpham_fk = '" + spId + "' and b.dongia = '" + dongia + "' "+
							"		group by b.DONHANG_FK, b.DONVI, b.DONGIA, b.loaidonhang	" +
							" )"+
							" DADUNG on DT.donhangId = DADUNG.DONHANG_FK and DT.loaidonhang = DADUNG.loaidonhang "+
							"\n where  DT.soluong - ISNULL( DADUNG.SOLUONG, 0 ) > 0 " +
							"\n order by DT.NgayDonHang asc, DT.loaidonhang asc ";
					System.out.println("::: DE XUAT DON HANG: " + query );
					ResultSet rsDonhang = db.get(query);
					
					double totalSL = 0;
					boolean exit = false;
					if( rsDonhang != null )
					{
						while( rsDonhang.next() )
						{
							String donhangId = rsDonhang.getString("donhangId");
							String loaidonhang = rsDonhang.getString("loaidonhang");
							double conlai = rsDonhang.getDouble("conlai");
							double soluongDENGHI = 0;
							
							totalSL += conlai;
							
							if( totalSL < soluongCAN )
							{
								soluongDENGHI = conlai;
							}
							else
							{
								soluongDENGHI = soluongCAN - ( totalSL - conlai );
								exit = true;
							}
							
							//System.out.println("::: SO LUONG CAN: " + soluongCAN + " HIEN HUU: " + conlai + " DE XUAT: " + soluongDENGHI + " TOTAL: " + totalSL );
							if( soluongDENGHI > 0 )
							{
								String sql = "insert DONHANGCTV_SP_CHITIET( donhangctv_fk, donhang_fk, sanpham_fk, soluong, donvi, dongia, CTV_FK, loaidonhang ) " + 
											 "values( '" + id + "', '" + donhangId + "', '" + spId + "', '" + soluongDENGHI + "', N'" + donvi + "', '" + dongia + "', '" + ctvId + "', '" + loaidonhang + "' ) ";
								
								System.out.println("::: CHEN SAN PHAM: " + sql );
								if(db.updateReturnInt(sql) != 1)
								{
									msg = "2.Lỗi khi duyệt đơn hàng CTV: " + sql;
									rsDonhang.close();
									rs.close();
									db.getConnection().rollback();
									return msg;
								}
							}
							
							if( exit )
							{
								rsDonhang.close();
								break;
							}
						}
						rsDonhang.close();
					}
				}
				rs.close();
			}
			
			//CHECK TỔNG PHẢI BẰNG CHI TIẾT
			query = "  select COUNT( tong.DONHANGCTV_FK ) as sodong  " + 
					"  from DONHANGCTV_SP tong full join  " + 
					"  ( " + 
					"  	select DONHANGCTV_FK, SANPHAM_FK, CTV_FK, DONGIA, SUM(SOLUONG) as soluong  " + 
					"  	from DONHANGCTV_SP_CHITIET " + 
					"  	group by DONHANGCTV_FK, SANPHAM_FK, CTV_FK, DONGIA " + 
					"  ) " + 
					"  CT on tong.DONHANGCTV_FK = CT.DONHANGCTV_FK and tong.SANPHAM_FK = CT.SANPHAM_FK " + 
					"  		and tong.CTV_FK = CT.CTV_FK and tong.DONGIA = CT.DONGIA " + 
					"  where ISNULL(tong.soluong, 0) != ISNULL( CT.soluong, 0 )  " + 
					"  	and tong.DONHANGCTV_FK = '" + id + "' ";
			System.out.println("::: CHECK TONG VS CHI TIET: " + query);
			ResultSet rsCHECK = db.get(query);
			int sodong = 0;
			if( rsCHECK.next() )
			{
				sodong = rsCHECK.getInt("sodong");
				rsCHECK.close();
			}
			
			if( sodong > 0 )
			{
				msg = "Sản phẩm tự đề xuất trong hóa đơn không đủ. Vui lòng liên hệ Admin để xử lý";
				db.getConnection().rollback();
				return msg;
			}
			
			query = " update dh "+
				 " 	set dh.gsbh_fk = ( select top(1) gsbh_fk from DDKD_GSBH where DDKD_FK = dh.ddkd_fk ),"+
				 " 		dh.asm_fk = ( select ASM_FK from GIAMSATBANHANG where PK_SEQ in ( select top(1) gsbh_fk from DDKD_GSBH where DDKD_FK = dh.ddkd_fk ) )"+
				 " from DONHANGCTV dh where PK_SEQ = '" + id + "'";
			
			System.out.println("----CAP NHAT: " + query);
			if(db.updateReturnInt(query)!=1)
			{
				msg = "2.Khong thể cập nhật trạng thái: " + query;
				db.getConnection().rollback();
				return msg;
			}
			
			//CAP NHAT COT NPP
			query = "  update a  " + 
					"  	set a.npp_fk = case loaidonhang when 0 then ( select npp_fk from ERP_DONDATHANGNPP where PK_SEQ = a.DONHANG_FK ) " + 
					"  										 else ( select npp_fk from DONHANG where PK_SEQ = a.DONHANG_FK ) end " + 
					"  from DONHANGCTV_SP_CHITIET a where a.DONHANGCTV_FK = '" + id + "' ";
			System.out.println("----CAP NHAT NPP: " + query);
			if(!db.update(query))
			{
				msg = "2.Lỗi khi duyệt đơn hàng CTV: " + query;
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

	private String MoChot(String id, String userId, int loai)
	{
		dbutils db = new dbutils();
		String msg = "";

		try
		{
			db.getConnection().setAutoCommit(false);

			//CHECK TRANG THAI CO HOP LE DE THAO TAC TIEP HAY KHONG
			String query = " SELECT trangthai, NGAYNHAP, npp_fk FROM DONHANGCTV WHERE PK_SEQ = "+ id;
			ResultSet rsCheck = db.get(query);
			
			String trangthai = "";
			String ngayyeucau = "";
			String nppId = "";
			if(rsCheck != null)
			{
				if(rsCheck.next())
				{
					trangthai = rsCheck.getString("trangthai");
					ngayyeucau = rsCheck.getString("NGAYNHAP");
					nppId = rsCheck.getString("npp_fk");
				}
				rsCheck.close();
			}
			
			/*geso.dms.distributor.util.Utility dutil = new geso.dms.distributor.util.Utility();
			int checkks = dutil.CheckKhoaSoCTV(db, nppId, "", "DONHANGCTV", "NGAYNHAP", id);
			if(checkks != -1 )
			{
				if(checkks == 0)
				{
					msg = "Ngày đơn hàng nằm trong tháng đã khóa sổ CTV. Vui lòng kiểm tra lại.";
					db.getConnection().rollback();
					return msg;
				}
			}
			*/
			if( !trangthai.equals("2") )
			{
				msg = "Trạng thái đơn hàng không hợp lệ. Vui lòng liên hệ Admin để được xử lý.";
				db.getConnection().rollback();
				return msg;
			}
			
			query = "update donhangctv set trangthai = '0', nguoimoduyet = '" + userId + "', ngaymoduyet = getdate() where pk_seq = '" + id + "'  ";
			System.out.println("----CAP NHAT: " + query);
			if( !db.update(query) )
			{
				msg = "1.Khong thể cập nhật trạng thái: " + query;
				db.getConnection().rollback();
				return msg;
			}
			
			query = "delete DONHANGCTV_SP_CHITIET where donhangctv_fk = '" + id + "' ";
			System.out.println("----CAP NHAT: " + query);
			if( !db.update(query) )
			{
				msg = "2.Khong thể cập nhật trạng thái: " + query;
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
			db.shutDown();
			return "Exception: " + e.getMessage();
		}
		finally
		{
			if(db!=null)db.shutDown();
		}

		return "";
	}

	
	//CHAY LAI CHOT CHI TIET
	public static void main(String[] arg)
	{
		DonhangctvSvl dh = new DonhangctvSvl();
		
		dbutils db = new dbutils();
		//ResultSet rs = db.get("select PK_SEQ from DONHANGCTV where pk_seq not in ( select DONHANGCTV_FK from DONHANGCTV_SP_CHITIET  ) " + 
			//				" and TRANGTHAI = 2 and NGAYNHAP >= '2016-05-01' order by PK_SEQ asc");
		ResultSet rs = db.get("select PK_SEQ from DONHANGCTV where pk_seq = '101687' ");
		if( rs != null )
		{
			try 
			{
				while ( rs.next() )
				{
					String dhId = rs.getString("PK_SEQ");
					dh.ChotLai(dhId);
				}
				rs.close();
				
				System.out.println("::::: CHAY XONG............... ");
			} 
			catch (Exception e) {
				
				e.printStackTrace();
			}
		}
		db.shutDown();
	}
	
	private String ChotLai(String id)
	{
		dbutils db = new dbutils();
		String msg = "";

		try
		{
			db.getConnection().setAutoCommit(false);

			// Trạng thái bằng 1, đã chốt, SS chưa duyệt, Trạng thái bằng 2, SS đã duyệt
			String query = "";
			int loai = 0;
			
			//PHAN BO SỐ LƯỢNG VÀO ĐƠN HÀNG CŨ HƠN
			if( loai == 0 )
			{
				query = " select a.ngaynhap, SUBSTRING(a.ngaynhap, 0, 8 ) + '-01' as dauthang, a.KHACHHANG_FK, a.ddkd_fk, b.ctv_fk, b.SANPHAM_FK, b.soluong, b.DONGIA, b.DONVI "+
						" from DONHANGCTV a inner join DONHANGCTV_SP b on a.PK_SEQ = b.DONHANGCTV_FK"+
						" where b.DONHANGCTV_FK = '" + id + "'  " ;
						//" group by a.ngaynhap, a.KHACHHANG_FK, a.ddkd_fk, b.SANPHAM_FK, b.DONGIA, b.DONVI ";
				System.out.println("::: THONG TIN DON HANG: " + query);
				ResultSet rs = db.get(query);
				if( rs != null )
				{
					while( rs.next() )
					{
						String ngaynhap = rs.getString("dauthang");
						String khId = rs.getString("KHACHHANG_FK");
						String ddkdId = rs.getString("ddkd_fk");
						String ctvId = rs.getString("ctv_fk");
						String spId = rs.getString("SANPHAM_FK");
						long dongia = Math.round( rs.getDouble("DONGIA") );
						String donvi = rs.getString("DONVI");
						double soluongCAN = rs.getDouble("SOLUONG");
						
						//LAY CAC DON HANG CON CO THE PHAN BO
						
						query = " select DT.donhangId, DT.loaidonhang, DT.NgayDonHang, DT.soluong - ISNULL( DADUNG.SOLUONG, 0 ) as conlai "+
								" from"+
								" ("+
								"\n 	select e.PK_SEQ as donhangId, 0 as loaidonhang, e.NgayDonHang, SUM( c.soluong ) as soluong, d.DONVI, a.dongia as dongia "+
								" 		from ERP_DONDATHANGNPP_SANPHAM a inner join SANPHAM b on a.sanpham_fk = b.PK_SEQ "+
								" 			inner join ERP_DONDATHANGNPP_SANPHAM_CHITIET c on a.dondathang_fk = c.dondathang_fk and a.sanpham_fk = c.SANPHAM_FK and a.dvdl_fk = c.DVDL_FK"+
								" 			inner join DONVIDOLUONG d on c.DVDL_FK = d.PK_SEQ"+
								" 			inner join ERP_DONDATHANGNPP e on a.dondathang_fk = e.PK_SEQ"+
								" 		where e.KHACHHANG_FK = '" + khId + "' and e.TRANGTHAI in ( 2, 4 ) "+
								" 			and a.sanpham_fk = '" + spId + "'	and a.dongia = '" + dongia + "' " + 
								" 			and e.NgayDonHang < '" + ngaynhap + "' "+
								" 		group by d.DONVI, a.dongia, thueVAT, e.PK_SEQ, e.NgayDonHang"+
								"\n		union all "+
								"\n		select e.PK_SEQ, 1 as loaidonhang, e.NGAYNHAP, SUM( a.soluong ) as soluong, d.DONVI, a.dongia as dongia "+ 
								"		from DONHANG_SANPHAM a inner join SANPHAM b on a.sanpham_fk = b.PK_SEQ "+
								"			inner join DONVIDOLUONG d on b.DVDL_FK = d.PK_SEQ "+
								"			inner join DONHANG e on a.DONHANG_FK = e.PK_SEQ "+
								"		where e.KHACHHANG_FK = '" + khId + "' and e.KBH_FK in (100052, 100059) "+
								" 			and a.sanpham_fk = '" + spId + "'	and a.dongia = '" + dongia + "' " +
								"			and e.NGAYNHAP < '" + ngaynhap + "' "+
								"			and (select COUNT(*) from HOADON_DDH hddh inner join HOADON hd on hddh.HOADON_FK = hd.PK_SEQ where hddh.DDH_FK = e.PK_SEQ and hd.trangthai in (2,4)) "+
								"		group by d.DONVI, a.dongia, e.PK_SEQ, e.NGAYNHAP "+
								"\n )"+
								" DT left join"+
								" ("+
								" 		select b.DONHANG_FK, sum(b.SOLUONG) as soluong, b.DONVI, b.DONGIA, b.loaidonhang "+
								" 		from DONHANGCTV a inner join DONHANGCTV_SP_CHITIET b on a.PK_SEQ = b.DONHANGCTV_FK"+
								" 		where a.KHACHHANG_FK = '" + khId + "'  and b.sanpham_fk = '" + spId + "' and b.dongia = '" + dongia + "' "+
								"		group by b.DONHANG_FK, b.DONVI, b.DONGIA, b.loaidonhang	" +
								" )"+
								" DADUNG on DT.donhangId = DADUNG.DONHANG_FK and DT.loaidonhang = DADUNG.loaidonhang "+
								"\n where  DT.soluong - ISNULL( DADUNG.SOLUONG, 0 ) > 0 " +
								"\n order by DT.NgayDonHang asc, DT.loaidonhang asc ";
						
						System.out.println("::: DE XUAT DON HANG: " + query );
						ResultSet rsDonhang = db.get(query);
						
						double totalSL = 0;
						boolean exit = false;
						if( rsDonhang != null )
						{
							while( rsDonhang.next() )
							{
								String donhangId = rsDonhang.getString("donhangId");
								String loaidonhang = rsDonhang.getString("loaidonhang");
								double conlai = rsDonhang.getDouble("conlai");
								double soluongDENGHI = 0;
								
								totalSL += conlai;
								
								if( totalSL < soluongCAN )
								{
									soluongDENGHI = conlai;
								}
								else
								{
									soluongDENGHI = soluongCAN - ( totalSL - conlai );
									exit = true;
								}
								
								System.out.println("::: SO LUONG CAN: " + soluongCAN + " HIEN HUU: " + conlai + " DE XUAT: " + soluongDENGHI + " TOTAL: " + totalSL );
								if( soluongDENGHI > 0 )
								{
									String sql = "insert DONHANGCTV_SP_CHITIET( donhangctv_fk, donhang_fk, sanpham_fk, soluong, donvi, dongia, CTV_FK, loaidonhang ) " + 
												 "values( '" + id + "', '" + donhangId + "', '" + spId + "', '" + soluongDENGHI + "', N'" + donvi + "', '" + dongia + "', '" + ctvId + "', '" + loaidonhang + "' ) ";
									
									System.out.println("::: CHEN SAN PHAM: " + sql );
									if(db.updateReturnInt(sql) != 1)
									{
										msg = "2.Lỗi khi duyệt đơn hàng CTV: " + sql;
										rsDonhang.close();
										rs.close();
										db.getConnection().rollback();
										return msg;
									}
								}
								
								if( exit )
								{
									rsDonhang.close();
									break;
								}
							}
							rsDonhang.close();
						}
					}
					rs.close();
				}
			}
			
			//CHECK TỔNG PHẢI BẰNG CHI TIẾT
			/*query = "  select COUNT( tong.DONHANGCTV_FK ) as sodong  " + 
					"  from DONHANGCTV_SP tong full join  " + 
					"  ( " + 
					"  	select DONHANGCTV_FK, SANPHAM_FK, CTV_FK, DONGIA, SUM(SOLUONG) as soluong  " + 
					"  	from DONHANGCTV_SP_CHITIET " + 
					"  	group by DONHANGCTV_FK, SANPHAM_FK, CTV_FK, DONGIA " + 
					"  ) " + 
					"  CT on tong.DONHANGCTV_FK = CT.DONHANGCTV_FK and tong.SANPHAM_FK = CT.SANPHAM_FK " + 
					"  		and tong.CTV_FK = CT.CTV_FK and tong.DONGIA = CT.DONGIA " + 
					"  where ISNULL(tong.soluong, 0) != ISNULL( CT.soluong, 0 )  " + 
					"  	and tong.DONHANGCTV_FK = '" + id + "' ";
			System.out.println("::: CHECK TONG VS CHI TIET: " + query);
			ResultSet rsCHECK = db.get(query);
			int sodong = 0;
			if( rsCHECK.next() )
			{
				sodong = rsCHECK.getInt("sodong");
				rsCHECK.close();
			}
			
			if( sodong > 0 )
			{
				msg = "Sản phẩm tự đề xuất trong hóa đơn không đủ. Vui lòng liên hệ Admin để xử lý";
				db.getConnection().rollback();
				return msg;
			}*/
			
			//CAP NHAT COT NPP
			query = "  update a  " + 
					"  	set a.npp_fk = case loaidonhang when 0 then ( select npp_fk from ERP_DONDATHANGNPP where PK_SEQ = a.DONHANG_FK ) " + 
					"  										 else ( select npp_fk from DONHANG where PK_SEQ = a.DONHANG_FK ) end " + 
					"  from DONHANGCTV_SP_CHITIET a where a.DONHANGCTV_FK = '" + id + "' ";
			if(db.update(query))
			{
				msg = "2.Lỗi khi duyệt đơn hàng CTV: " + query;
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
	
	private String DeleteChuyenKho(String lsxId, String userId)
	{
		dbutils db = new dbutils();
		String msg = "";

		try
		{
			db.getConnection().setAutoCommit(false);

			String query = "delete from donhangctv_sp where donhangctv_fk = '"+lsxId+"'";
			System.out.println("----Xoa donhangctv_sp " + query);
			if(db.updateReturnInt(query) <= 0)
			{
				msg = "2.Khong the xoa: " + query;
				db.getConnection().rollback();
				return msg;
			}
			
			query = "delete from donhangctv_sp_chitiet where donhangctv_fk = '"+lsxId+"'";
			System.out.println("----Xoa donhangctv_sp_chitiet " + query);
			if(!db.update(query))
			{
				msg = "2.Khong the xoa: " + query;
				db.getConnection().rollback();
				return msg;
			}
			
			query = "delete  from donhangctv where  pk_seq = '"+lsxId+"'";
			System.out.println("----Xoa donhangctv " + query);
			if(db.updateReturnInt(query) <= 0)
			{
				msg = "2.Khong the xoa: " + query;
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

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");

		String action = request.getParameter("action");
		if (action == null) {
			action = "";
		}

		HttpSession session = request.getSession();
		IDonhangctvList obj = new DonhangctvList();
		
		obj.setLoainhanvien(session.getAttribute("loainhanvien"));
	    obj.setDoituongId(session.getAttribute("doituongId"));

		Utility util = new Utility();
	
		String userId = util.antiSQLInspection(request.getParameter("userId"));
		obj.setUserId(userId);

		String loaidonhang = request.getParameter("loaidonhang");
	    if(loaidonhang == null)
	    	loaidonhang = "";
	    obj.setLoaidonhang(loaidonhang);
	    
	    String duyetSS = request.getParameter("duyetSS");
	    if(duyetSS == null)
	    	duyetSS = "";
	    obj.setDuyetSS(duyetSS);
		
		if (action.equals("Tao moi")) {
			
			IDonhangctv lsxBean = new Donhangctv();
			
			getParameters(request, obj);
			
			lsxBean.setLoainhanvien(session.getAttribute("loainhanvien"));
			lsxBean.setDoituongId(session.getAttribute("doituongId"));
			obj.DBclose();
			lsxBean.setUserId(userId);
			lsxBean.setLoaidonhang(loaidonhang);
			lsxBean.createRs();

			session.setAttribute("lsxBean", lsxBean);

			String nextJSP = request.getContextPath() + "/pages/Distributor/DonHangCTVNew.jsp";
			response.sendRedirect(nextJSP);
		} 
		else if( action.equals("taobaocao") ) //MẪU NHÂN VIÊN BÁN HÀNG XEM
		{
			OutputStream out = response.getOutputStream();
			
			try
		    {
		    	/*response.setContentType("application/vnd.ms-excel");
		        response.setHeader("Content-Disposition", "attachment; filename=BCGuiHangTinh.xls");*/
		        
		        response.setContentType("application/xlsm");
				response.setHeader("Content-Disposition", "attachment; filename=BaoCaoCongTacVien.xlsm");
		        
		        Workbook workbook = new Workbook();
		    	
				FileInputStream fstream = null;
				String chuoi = getServletContext().getInitParameter("path") + "\\BCDonHangCTV_NVBH.xlsm";
				
				fstream = new FileInputStream(chuoi);		
				workbook.open(fstream);
				workbook.setFileFormatType(FileFormatType.EXCEL2007XLSM);
		        
				obj.init("");
			    String query = getSearchQuery_BaoCao(request, obj);
			   // String query =  getSearchQuery_trinhduocvien(request, obj);
			    CreateStaticData(workbook, query, obj);
			
			    //Saving the Excel file
			    workbook.save(out);
			    fstream.close();
		    }
		    catch (Exception ex){ ex.printStackTrace(); }
		}
		else if(action.equals("duyetALL"))
		{
			String msg = this.DuyetALL(request, userId);
			obj.setMsg(msg);
			
			String view = request.getParameter("view");
			String nextJSP="";
			if(view == null)
			    view = "";
			String search="";
			if(view.equals("CTV"))
			{
				 search = getSearchQuery_trinhduocvien(request, obj);
				 obj.setRsxemCTV(search);
				 nextJSP = request.getContextPath() + "/pages/Distributor/XemCTV.jsp";
			}
			else
			{
				 search = getSearchQuery(request, obj);
			 nextJSP = request.getContextPath() + "/pages/Distributor/DonHangCTV.jsp";
			}
			obj.init(search);
			obj.setUserId(userId);

			session.setAttribute("obj", obj);
			session.setAttribute("userId", userId);

			response.sendRedirect(nextJSP);	  
		}
		else 
		{
			if (  action.equals("view") || action.equals("next") || action.equals("prev")) {
				
				String nextJSP="";
				String view = request.getParameter("view");
			    if(view == null)
			    	view = "";
			    String search="";
			    if(view.equals("CTV"))
				{
					 search = getSearchQuery_trinhduocvien(request, obj);
					 obj.setRsxemCTV(search);
					 nextJSP = request.getContextPath() + "/pages/Distributor/XemCTV.jsp";
				}
				else
				{
					search = getSearchQuery(request, obj);
					nextJSP = request.getContextPath() + "/pages/Distributor/DonHangCTV.jsp";
				}
				obj.setNxtApprSplitting(Integer.parseInt(request.getParameter("nxtApprSplitting")));
				obj.setUserId(userId);
				obj.init(search);
				obj.setAttribute(request, action, "list", "crrApprSplitting", "nxtApprSplitting");
				session.setAttribute("obj", obj);
				
				response.sendRedirect(nextJSP);
			} 
			else 
			{
				String view = request.getParameter("view");
				String nextJSP="";
				if(view == null)
				    view = "";
				String search="";
				if(view.equals("CTV"))
				{
					 search = getSearchQuery_trinhduocvien(request, obj);
					 obj.setRsxemCTV(search);
					 nextJSP = request.getContextPath() + "/pages/Distributor/XemCTV.jsp";
				}
				else
				{
					 search = getSearchQuery(request, obj);
					 nextJSP = request.getContextPath() + "/pages/Distributor/DonHangCTV.jsp";
				}
				obj.init(search);
				obj.setUserId(userId);

				session.setAttribute("obj", obj);
				session.setAttribute("userId", userId);

				response.sendRedirect(nextJSP);
			}
		}
	}

	private String DuyetALL(HttpServletRequest request, String userId) 
	{
		String[] dhIds = request.getParameterValues("donhangIds");
		String msg = "";
		if(dhIds != null)
		{
			for(int i = 0; i < dhIds.length; i++)
			{
				msg = this.Chot(dhIds[i], userId, 1);
				if(msg.trim().length() > 0)
				{
					break;
				}
			}
		}
		
		return msg;
	}
	
	private String getSearchQuery(HttpServletRequest request, IDonhangctvList obj) 
	{
		getParameters(request, obj);
		
		String query = getSearchQuery(obj);
		System.out.print(query);
		return query;
	}
	
	private void getParameters(HttpServletRequest request, IDonhangctvList obj) 
	{
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
		
		String timkiem = request.getParameter("timkiem");
		if (timkiem == null)
			timkiem = "";
		obj.setTimkiem(timkiem);

		String nppId = request.getParameter("nppId");
		if (nppId == null)
			nppId = "";
		obj.setNppId(nppId);

		String khId = request.getParameter("khId");
		if (khId == null)
			khId = "";
		obj.setKhId(khId);
		
		String tenNVBH = request.getParameter("tenNVBH");
		if (tenNVBH == null)
			tenNVBH = "";
		obj.setTenNVBH(tenNVBH);
		
		String tenSP = request.getParameter("tenSP");
		if (tenSP == null)
			tenSP = "";
		System.out.println("tenSP: " + tenSP);
		obj.setTenSP(tenSP);
	}
	
	private String getSearchQuery(IDonhangctvList obj) 
	{
		Utility util = new Utility();
		
		String query = " select distinct  a.pk_Seq,b.MA as nppMa,b.TEN as nppTen,a.ngaynhap, f.ten as tenkh \n"
					+ ",c.TEN as nguoiTao,d.TEN as nguoiSua,a.TRANGTHAI,a.SOTIENBVAT,a.Modified_Date \n"
					+ ",a.created_date, isnull( a.tooltip, '' ) as tooltip, a.SOTIENAVAT, dd.ten as ddkdTen \n"
					+ "	 from donhangctv a inner join NHAPHANPHOI b on b.PK_SEQ=a.NPP_FK \n"
					+ "		inner join NHANVIEN c on c.PK_SEQ=a.NGUOITAO \n"
					+ "		inner join NHANVIEN d on d.PK_SEQ=a.NGUOISUA \n"
					+ "		inner join KHACHHANG f on f.pk_seq = a.khachhang_FK \n" 
					+ "		inner join DAIDIENKINHDOANH dd on a.ddkd_fk = dd.pk_seq \n" 
					+ "		inner join DONHANGCTV_SP dh_sp on a.pk_seq = dh_sp.donhangctv_fk \n" 
					+ " where a.npp_fk = '" + obj.getNppId() + "' \n";

		if (obj.getTungayTao().length() > 0)
			query += " and a.ngaynhap >= '" + obj.getTungayTao() + "' \n";

		if (obj.getDenngayTao().length() > 0)
			query += " and a.ngaynhap <= '" + obj.getDenngayTao() + "' \n";

		if (obj.getTrangthai().length() > 0)
		{
			if( obj.getTrangthai().equals("0") ) //Chưa duyệt
				query += " and a.TrangThai = '" + obj.getTrangthai() + "' \n";
			else //Đã duyệt
				query += " and a.TrangThai in ( 1, 2 ) \n";
		}

		if (obj.getSochungtu().length() > 0)
			query += " and cast(a.PK_SEQ as varchar(10)) like '%" + obj.getSochungtu() + "%' \n";
		
		if (obj.getTenNVBH().length() > 0)
			query += " and ( dd.timkiem like N'%" + util.replaceAEIOU( obj.getTenNVBH() ) + "%') \n";
		
		if (obj.getTenSP().length() > 0)
			query += " and ( dh_sp.sanpham_fk in ( select pk_seq from SANPHAM where timkiem like N'%" + util.replaceAEIOU( obj.getTenSP() ) + "%' ) ) \n";

		System.out.println("query tim kiem bc tcv: \n" + query + "\n=============================================");
		return query;
	}

	private String getSearchQuery_BaoCao(HttpServletRequest request, IDonhangctvList obj) 
	{
		HttpSession session = request.getSession();
		Utility util = new Utility();
		
		/*String query =  "select a.ngaynhap, e.maFAST as makhCAP1, e.TEN as tenkhCAP1, ' ' as makhCAP2, ' ' as tenkhCAP2, d.MA as maCTV, d.TEN as tenCTV, '' as chucvu, g.diengiai as kenh,  "+
						 "		c.MA_FAST as maSP, c.TEN as tenSP, b.DONGIA, b.SOLUONG, b.DONGIA * b.SOLUONG as doanhso, f.machungtu as sodonhang, f.NgayDonHang as ngaydonhang, "+
						 "		'' as ptCHI, '' as chi, '' as ptlythuyet, a.ghichu, '' as tt_gt "+
						 "from DONHANGCTV a inner join DONHANGCTV_SP_CHITIET b on a.PK_SEQ = b.DONHANGCTV_FK "+
						 "	inner join SANPHAM c on b.SANPHAM_FK = c.PK_SEQ "+
						 "	inner join CONGTACVIEN d on b.ctv_fk = d.PK_SEQ "+
						 "	inner join KHACHHANG e on a.KHACHHANG_FK = e.PK_SEQ "+
						 "	inner join ERP_DONDATHANGNPP f on b.donhang_fk = f.PK_SEQ "+
						 "	left join KENHBANHANG g on f.kbh_fk = g.PK_SEQ "+
						 "where a.TRANGTHAI in ( 1, 2 ) ";*/

		String query = "\n select asm.TEN as asmTen, gs.ten as gsbhTen, dd.TEN as ddkdTen, db.ten as dbTen, tt.TEN as tinhTen, a.ngaynhap, "+
				 "\n 	  case b.loaidonhang when 1 then npp.MaFAST else e.maFAST end as makhCAP1, "+
				 "\n 	  case b.loaidonhang when 1 then npp.TEN else e.TEN end as tenkhCAP1, "+
				 "\n 	  case b.loaidonhang when 1 then e.MaFAST else ' ' end as makhCAP2, "+
				 "\n 	  case b.loaidonhang when 1 then e.TEN else ' ' end as tenkhCAP2, "+
				 "\n 		 d.MA as maCTV, d.TEN as tenCTV, '' as chucvu,"+
				 "\n 		isnull(( select ten from HETHONGBANHANG where PK_SEQ = ( select htbh_fk from hethongbanhang_kenhbanhang where kbh_fk = ISNULL( g.PK_SEQ, kbh.PK_SEQ ) ) ), '') as htbh, isnull(isnull( g.diengiai, kbh.DIENGIAI), '') as kenh,  "+
				 "\n 		c.MA_FAST as maSP, c.TEN as tenSP, b.DONGIA, b.SOLUONG, b.DONGIA * b.SOLUONG as doanhso, isnull(f.machungtu, dh.machungtu) as sodonhang, isnull(f.NgayDonHang, dh.NGAYNHAP) as ngaydonhang, "+
				 "\n 		'' as ptCHI, '' as chi, '' as ptlythuyet, a.ghichu, '' as tt_gt,"+
				 "\n 	  ISNULL( (	Select hdnpp.sohoadon + ',' AS [text()]  "+
				 "\n 				From ERP_HOADONNPP_DDH hd_dh inner join ERP_HOADONNPP hdnpp on hd_dh.HOADONNPP_FK = hdnpp.PK_SEQ   "+
				 "\n 				Where  hd_dh.DDH_FK  = f.PK_SEQ and hdnpp.TRANGTHAI not in ( 3, 5 )"+
				 "\n 				For XML PATH ('') ), '' )  as sohoadon,"+
				 "\n 	  ISNULL( (	Select hdnpp.NGAYGHINHAN + ',' AS [text()]  "+
				 "\n 				From ERP_HOADONNPP_DDH hd_dh inner join ERP_HOADONNPP hdnpp on hd_dh.HOADONNPP_FK = hdnpp.PK_SEQ   "+
				 "\n 				Where  hd_dh.DDH_FK  = f.PK_SEQ and hdnpp.TRANGTHAI not in ( 3, 5 )"+
				 "\n 				For XML PATH ('') ), '' )  as ngayhoadon      "+
				 "\n from DONHANGCTV a inner join DONHANGCTV_SP_CHITIET b on a.PK_SEQ = b.DONHANGCTV_FK "+
				 "\n 	inner join SANPHAM c on b.SANPHAM_FK = c.PK_SEQ "+
				 "\n 	inner join CONGTACVIEN d on b.ctv_fk = d.PK_SEQ "+
				 "\n 	inner join KHACHHANG e on a.KHACHHANG_FK = e.PK_SEQ "+
				 "\n 	left join ERP_DONDATHANGNPP f on b.donhang_fk = f.PK_SEQ and b.loaidonhang = 0 "+
				 "\n 	left join KENHBANHANG g on f.kbh_fk = g.PK_SEQ"+
				 "\n 	left join DONHANG dh on b.donhang_fk = dh.PK_SEQ and b.loaidonhang = 1"+
				 "\n 	left join KENHBANHANG kbh on dh.kbh_fk = kbh.PK_SEQ"+
				 "\n 	left join NHAPHANPHOI npp on dh.NPP_FK = npp.PK_SEQ"+
				 "\n 	inner join DAIDIENKINHDOANH dd on a.ddkd_fk = dd.PK_SEQ "+
				 "\n 	left join GIAMSATBANHANG gs on a.gsbh_fk = gs.PK_SEQ"+
				 "\n 	left join ASM asm on a.asm_fk = asm.PK_SEQ"+
				 "\n 	left join DIABAN db on e.diaban = db.PK_SEQ"+
				 "\n 	left join TINHTHANH tt on e.TINHTHANH_FK = tt.PK_SEQ"+
				 "\n where a.TRANGTHAI in ( 1, 2 ) ";
		
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
		
		String timkiem = request.getParameter("timkiem");
		if (timkiem == null)
			timkiem = "";
		obj.setTimkiem(timkiem);

		String nppId = request.getParameter("nppId");
		if (nppId == null)
			nppId = "";
		obj.setNppId(nppId);

		String khId = request.getParameter("khId");
		if (khId == null)
			khId = "";
		obj.setKhId(khId);
		
		String tenNVBH = request.getParameter("tenNVBH");
		if (tenNVBH == null)
			tenNVBH = "";
		obj.setTenNVBH(tenNVBH);
		
		String tenSP = request.getParameter("tenSP");
		if (tenSP == null)
			tenSP = "";
		obj.setTenSP(tenSP);
		
		obj.setLoainhanvien(session.getAttribute("loainhanvien"));
	    obj.setDoituongId(session.getAttribute("doituongId"));

		if (nppId.length() > 0)
			query += " and a.npp_fk = '" + nppId + "'";
		
		if (tungaySX.length() > 0)
			query += " and a.ngaynhap >= '" + tungaySX + "'";

		if (denngaySX.length() > 0)
			query += " and a.ngaynhap <= '" + denngaySX + "'";

		if (trangthai.length() > 0)
			query += " and a.TrangThai = '" + trangthai + "'";

		if (sochungtu.length() > 0)
			query += " and a.sochungtu like '%" + sochungtu + "%'";
		
		if (timkiem.length() > 0)
			query += " and ( e.timkiem like N'%" + util.replaceAEIOU( timkiem ) + "%')";

		if (tenNVBH.length() > 0)
			query += " and ( dd.timkiem like N'%" + util.replaceAEIOU(tenNVBH) + "%')";
		
		if (tenSP.length() > 0)
			query += " and ( c.timkiem like N'%" + util.replaceAEIOU(tenSP) + "%')";
		
		if(!trangthai.equals("1"))
		{
			query +=
				"union ALL  \n"+
				"select asm.TEN as asmTen, gs.ten as gsbhTen, dd.TEN as ddkdTen, db.ten as dbTen, tt.TEN as tinhTen, a.ngaynhap, \n"+
				"e.maFAST makhCAP1, e.TEN tenkhCAP1, '' as makhCAP2, '' as tenkhCAP2, d.MA as maCTV, d.TEN as tenCTV, '' as chucvu, \n"+
				"'' as htbh, '' as kenh, c.MA_FAST as maSP, c.TEN as tenSP, b.DONGIA, b.SOLUONG, b.DONGIA * b.SOLUONG as doanhso, '' as sodonhang, \n"+ 
				"'' as ngaydonhang, '' as ptCHI, '' as chi, '' as ptlythuyet, a.ghichu, '' as tt_gt, '' as sohoadon, '' as ngayhoadon \n"+       
				"from DONHANGCTV a inner join DONHANGCTV_SP b on a.PK_SEQ = b.DONHANGCTV_FK \n"+  
				"inner join SANPHAM c on b.SANPHAM_FK = c.PK_SEQ \n"+
				"inner join CONGTACVIEN d on b.ctv_fk = d.PK_SEQ \n"+
				"inner join KHACHHANG e on a.KHACHHANG_FK = e.PK_SEQ \n"+
				"inner join DAIDIENKINHDOANH dd on a.ddkd_fk = dd.PK_SEQ \n"+
				"left join GIAMSATBANHANG gs on a.gsbh_fk = gs.PK_SEQ \n"+
				"left join ASM asm on a.asm_fk = asm.PK_SEQ 	left join DIABAN db on e.diaban = db.PK_SEQ \n"+ 	
				"left join TINHTHANH tt on e.TINHTHANH_FK = tt.PK_SEQ where a.TRANGTHAI = '0'";
			if (nppId.length() > 0)
				query += " and a.npp_fk = '" + nppId + "'";
			if (tungaySX.length() > 0)
				query += " and a.ngaynhap >= '" + tungaySX + "'";
			if (sochungtu.length() > 0)
				query += " and a.sochungtu like '%" + sochungtu + "%'";
			if (denngaySX.length() > 0)
				query += " and a.ngaynhap <= '" + denngaySX + "'";
			if (timkiem.length() > 0)
				query += " and ( e.timkiem like N'%" + util.replaceAEIOU( timkiem ) + "%')";
			if (tenNVBH.length() > 0)
				query += " and ( dd.timkiem like N'%" + util.replaceAEIOU(tenNVBH) + "%')";
			if (tenSP.length() > 0)
				query += " and ( c.timkiem like N'%" + util.replaceAEIOU(tenSP) + "%')";	
		}
		
		query = " select ddkdTen, dbTen, tinhTen, makhCAP1, tenkhCAP1, makhCAP2, tenkhCAP2, maCTV, tenCTV, chucvu, htbh, kenh, "+//asmTen, gsbhTen,   		"+
				 " 		maSP, tenSP, ghichu, DONGIA, sum(SOLUONG) as soluong, sum(doanhso) as doanhso	         "+
				 " from"+
				 " ("+
				 		query +
				 " )"+
				 " DT group by ddkdTen, dbTen, tinhTen, makhCAP1, tenkhCAP1, makhCAP2, tenkhCAP2, maCTV, tenCTV, chucvu, htbh, kenh,   		"+
				 " 			maSP, tenSP, DONGIA, ghichu";
		
		System.out.print(":: BAO CAO CTV MAU DDKD: " + query);
		return query;
	}

	private void CreateStaticData(Workbook workbook, String query, IDonhangctvList obj) 
	{
		Worksheets worksheets = workbook.getWorksheets();
	    Worksheet worksheet = worksheets.getSheet(0);
	    Cells cells = worksheet.getCells();
	    Cell cell = null;
	    Style style;
		Font font2 = new Font();
		//font2.setName("Calibri");				
		font2.setSize(11);
	    
	    cell = cells.getCell("A2"); cell.setValue( "Từ ngày: " + obj.getTungayTao() ); 
	    style = cell.getStyle(); style.setFont(font2); cell.setStyle(style);
	    
	    cell = cells.getCell("A3");	cell.setValue( "Đến ngày: " + obj.getDenngayTao() ); 
	    style = cell.getStyle(); style.setFont(font2); cell.setStyle(style);
	    
		dbutils db = new dbutils();
		
		ResultSet rs = db.get(query);

		int i = 6;
		if(rs != null)
		{
			try 
			{
				int stt = 1;
				while(rs.next())
				{
					//cell = cells.getCell("A" + Integer.toString(i));	cell.setValue( rs.getString("asmTen") ); 		style = cell.getStyle(); style.setFont(font2); cell.setStyle(style); setCellBorderStyle(cell);
					//cell = cells.getCell("B" + Integer.toString(i));	cell.setValue( rs.getString("gsbhTen") ); 		style = cell.getStyle(); style.setFont(font2); cell.setStyle(style); setCellBorderStyle(cell);
					cell = cells.getCell("A" + Integer.toString(i));	cell.setValue( rs.getString("ddkdTen") ); 		style = cell.getStyle(); style.setFont(font2); cell.setStyle(style); setCellBorderStyle(cell);
					cell = cells.getCell("B" + Integer.toString(i));	cell.setValue( rs.getString("dbTen") ); 		style = cell.getStyle(); style.setFont(font2); cell.setStyle(style); setCellBorderStyle(cell);
					cell = cells.getCell("C" + Integer.toString(i));	cell.setValue( rs.getString("tinhTen") ); 		style = cell.getStyle(); style.setFont(font2); cell.setStyle(style); setCellBorderStyle(cell);
					
					cell = cells.getCell("D" + Integer.toString(i));	cell.setValue( rs.getString("makhCAP1") ); 		style = cell.getStyle(); style.setFont(font2); cell.setStyle(style); setCellBorderStyle(cell);
					cell = cells.getCell("E" + Integer.toString(i));	cell.setValue( rs.getString("tenkhCAP1") ); 		style = cell.getStyle(); style.setFont(font2); cell.setStyle(style); setCellBorderStyle(cell);
					cell = cells.getCell("F" + Integer.toString(i));	cell.setValue( rs.getString("makhCAP2") ); 		style = cell.getStyle(); style.setFont(font2); cell.setStyle(style); setCellBorderStyle(cell);
					cell = cells.getCell("G" + Integer.toString(i));	cell.setValue( rs.getString("tenkhCAP2") ); 		style = cell.getStyle(); style.setFont(font2); cell.setStyle(style); setCellBorderStyle(cell);
					
					cell = cells.getCell("H" + Integer.toString(i));	cell.setValue( rs.getString("maCTV") ); 		style = cell.getStyle(); style.setFont(font2); cell.setStyle(style); setCellBorderStyle(cell);
					cell = cells.getCell("I" + Integer.toString(i));	cell.setValue( rs.getString("tenCTV") ); 			style = cell.getStyle(); style.setFont(font2); cell.setStyle(style); setCellBorderStyle(cell);
					cell = cells.getCell("J" + Integer.toString(i));	cell.setValue( rs.getString("chucvu") ); 		style = cell.getStyle(); style.setFont(font2); cell.setStyle(style); setCellBorderStyle(cell);
					
					cell = cells.getCell("K" + Integer.toString(i));	cell.setValue( rs.getString("htbh") ); 			style = cell.getStyle(); style.setFont(font2); cell.setStyle(style); setCellBorderStyle(cell);
					cell = cells.getCell("L" + Integer.toString(i));	cell.setValue( rs.getString("kenh") ); 			style = cell.getStyle(); style.setFont(font2); cell.setStyle(style); setCellBorderStyle(cell);
					
					cell = cells.getCell("M" + Integer.toString(i));	cell.setValue( rs.getString("maSP") ); 		style = cell.getStyle(); style.setFont(font2); cell.setStyle(style); setCellBorderStyle(cell);
					cell = cells.getCell("N" + Integer.toString(i));	cell.setValue( rs.getString("tenSP") ); 			style = cell.getStyle(); style.setFont(font2); cell.setStyle(style); setCellBorderStyle(cell);
					
					cell = cells.getCell("O" + Integer.toString(i));	cell.setValue( rs.getDouble("DONGIA") ); 			style = cell.getStyle(); style.setFont(font2); cell.setStyle(style); setCellBorderStyle(cell);
					cell = cells.getCell("P" + Integer.toString(i));	cell.setValue( rs.getDouble("SOLUONG") ); 		style = cell.getStyle(); style.setFont(font2); cell.setStyle(style); setCellBorderStyle(cell);
					cell = cells.getCell("Q" + Integer.toString(i));	cell.setValue( rs.getDouble("doanhso") );	style = cell.getStyle(); style.setFont(font2); cell.setStyle(style); setCellBorderStyle(cell);
					
					cell = cells.getCell("R" + Integer.toString(i));	cell.setValue( rs.getString("ghichu") ); 		style = cell.getStyle(); style.setFont(font2); cell.setStyle(style); setCellBorderStyle(cell);
					
					//cell = cells.getCell("T" + Integer.toString(i));	cell.setValue( rs.getString("sodonhang") ); 		style = cell.getStyle(); style.setFont(font2); cell.setStyle(style); setCellBorderStyle(cell);
					//cell = cells.getCell("U" + Integer.toString(i));	cell.setValue( rs.getString("ngaydonhang") ); 		style = cell.getStyle(); style.setFont(font2); cell.setStyle(style); setCellBorderStyle(cell);
					//cell = cells.getCell("V" + Integer.toString(i));	cell.setValue( rs.getString("sohoadon") ); 		style = cell.getStyle(); style.setFont(font2); cell.setStyle(style); setCellBorderStyle(cell);
					//cell = cells.getCell("W" + Integer.toString(i));	cell.setValue( rs.getString("ngayhoadon") ); 		style = cell.getStyle(); style.setFont(font2); cell.setStyle(style); setCellBorderStyle(cell);
					
					//cell = cells.getCell("R" + Integer.toString(i));	cell.setValue( rs.getString("ptCHI") ); 		style = cell.getStyle(); style.setFont(font2); cell.setStyle(style); setCellBorderStyle(cell);
					//cell = cells.getCell("S" + Integer.toString(i));	cell.setValue( rs.getString("chi") ); 		style = cell.getStyle(); style.setFont(font2); cell.setStyle(style); setCellBorderStyle(cell);
					//cell = cells.getCell("T" + Integer.toString(i));	cell.setValue( rs.getString("ptlythuyet") ); 		style = cell.getStyle(); style.setFont(font2); cell.setStyle(style); setCellBorderStyle(cell);
					//cell = cells.getCell("U" + Integer.toString(i));	cell.setValue( rs.getString("ghichu") ); 		style = cell.getStyle(); style.setFont(font2); cell.setStyle(style); setCellBorderStyle(cell);
					//cell = cells.getCell("V" + Integer.toString(i));	cell.setValue( rs.getString("tt_gt") ); 		style = cell.getStyle(); style.setFont(font2); cell.setStyle(style); setCellBorderStyle(cell);
					
					i++;
					stt ++;
				}
				rs.close();
			}
			catch (Exception e){ 
				db.shutDown();
				e.printStackTrace(); }
		}
		db.shutDown();
	}
	
	private void setCellBorderStyle(Cell cell) 
	{
		Style style = cell.getStyle();
		style.setHAlignment(HorizontalAlignmentType.CENTRED);
		style.setBorderLine(BorderType.TOP, 1);
		style.setBorderLine(BorderType.RIGHT, 1);
		style.setBorderLine(BorderType.BOTTOM, 1);
		style.setBorderLine(BorderType.LEFT, 1);
		cell.setStyle(style);
	}
	
	public String getDateTime() {
		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		Date date = new Date();
		return dateFormat.format(date);
	}
	
	private String getSearchQuery_trinhduocvien(HttpServletRequest request, IDonhangctvList obj) 
	{
		HttpSession session = request.getSession();
		
		/*String query =  "select a.ngaynhap, e.maFAST as makhCAP1, e.TEN as tenkhCAP1, ' ' as makhCAP2, ' ' as tenkhCAP2, d.MA as maCTV, d.TEN as tenCTV, '' as chucvu, g.diengiai as kenh,  "+
						 "		c.MA_FAST as maSP, c.TEN as tenSP, b.DONGIA, b.SOLUONG, b.DONGIA * b.SOLUONG as doanhso, f.machungtu as sodonhang, f.NgayDonHang as ngaydonhang, "+
						 "		'' as ptCHI, '' as chi, '' as ptlythuyet, a.ghichu, '' as tt_gt "+
						 "from DONHANGCTV a inner join DONHANGCTV_SP_CHITIET b on a.PK_SEQ = b.DONHANGCTV_FK "+
						 "	inner join SANPHAM c on b.SANPHAM_FK = c.PK_SEQ "+
						 "	inner join CONGTACVIEN d on b.ctv_fk = d.PK_SEQ "+
						 "	inner join KHACHHANG e on a.KHACHHANG_FK = e.PK_SEQ "+
						 "	inner join ERP_DONDATHANGNPP f on b.donhang_fk = f.PK_SEQ "+
						 "	left join KENHBANHANG g on f.kbh_fk = g.PK_SEQ "+
						 "where a.TRANGTHAI in ( 1, 2 ) ";*/

		String query = "\n select isnull((select dbo.getkhuvuc_ctv(e.PK_SEQ)),'') as tenkv,dd.maFAST,b.DONVI,asm.TEN as asmTen, gs.ten as gsbhTen, dd.TEN as ddkdTen, "+
					"\n		db.ten as dbTen, tt.TEN as tinhTen, a.ngaynhap, "+
					"\n 	case b.loaidonhang when 1 then npp.MaFAST else e.maFAST end as makhCAP1, "+
					"\n 	case b.loaidonhang when 1 then npp.TEN else e.TEN end as tenkhCAP1, "+
					"\n	  	case b.loaidonhang when 1 then e.MaFAST else ' ' end as makhCAP2, "+
					"\n  	case b.loaidonhang when 1 then e.TEN else ' ' end as tenkhCAP2, "+
					"\n		d.MA as maCTV, d.TEN as tenCTV, '' as chucvu,"+
					"\n		( select ten from HETHONGBANHANG where PK_SEQ = ( select htbh_fk from hethongbanhang_kenhbanhang where kbh_fk = ISNULL( g.PK_SEQ, kbh.PK_SEQ ) ) ) as htbh, isnull( g.diengiai, kbh.DIENGIAI) as kenh,  "+
					"\n		c.MA_FAST as maSP, c.TEN as tenSP, b.DONGIA, b.SOLUONG, b.DONGIA * b.SOLUONG as doanhso, isnull(f.machungtu, dh.machungtu) as sodonhang, isnull(f.NgayDonHang, dh.NGAYNHAP) as ngaydonhang, "+
					"\n		'' as ptCHI, '' as chi, '' as ptlythuyet, a.ghichu, '' as tt_gt,"+
					"\n  	ISNULL( (	Select hdnpp.sohoadon + ',' AS [text()]  "+
					"\n			From ERP_HOADONNPP_DDH hd_dh inner join ERP_HOADONNPP hdnpp on hd_dh.HOADONNPP_FK = hdnpp.PK_SEQ   "+
					"\n			Where  hd_dh.DDH_FK  = f.PK_SEQ and hdnpp.TRANGTHAI not in ( 3, 5 )"+
					"\n			For XML PATH ('') ), '' )  as sohoadon,"+
					"\n  	ISNULL( (	Select hdnpp.NGAYGHINHAN + ',' AS [text()]  "+
					"\n			From ERP_HOADONNPP_DDH hd_dh inner join ERP_HOADONNPP hdnpp on hd_dh.HOADONNPP_FK = hdnpp.PK_SEQ   "+
					"\n			Where  hd_dh.DDH_FK  = f.PK_SEQ and hdnpp.TRANGTHAI not in ( 3, 5 )"+
					"\n			For XML PATH ('') ), '' )  as ngayhoadon      "+
					"\n	from DONHANGCTV a inner join DONHANGCTV_SP_CHITIET b on a.PK_SEQ = b.DONHANGCTV_FK "+
					"\n 	inner join SANPHAM c on b.SANPHAM_FK = c.PK_SEQ "+
					"\n		inner join CONGTACVIEN d on b.ctv_fk = d.PK_SEQ "+
					"\n 	inner join KHACHHANG e on a.KHACHHANG_FK = e.PK_SEQ "+
					"\n		left join ERP_DONDATHANGNPP f on b.donhang_fk = f.PK_SEQ and b.loaidonhang = 0 "+
					"\n		left join KENHBANHANG g on f.kbh_fk = g.PK_SEQ"+
					"\n		left join DONHANG dh on b.donhang_fk = dh.PK_SEQ and b.loaidonhang = 1"+
					"\n 	left join KENHBANHANG kbh on dh.kbh_fk = kbh.PK_SEQ"+
					"\n		left join NHAPHANPHOI npp on dh.NPP_FK = npp.PK_SEQ"+
					"\n		inner join DAIDIENKINHDOANH dd on a.ddkd_fk = dd.PK_SEQ "+
					"\n		inner join GIAMSATBANHANG gs on a.gsbh_fk = gs.PK_SEQ"+
					"\n		inner join ASM asm on a.asm_fk = asm.PK_SEQ"+
					"\n		left join DIABAN db on e.diaban = db.PK_SEQ"+
					"\n		left join TINHTHANH tt on e.TINHTHANH_FK = tt.PK_SEQ"+
					"\n		left join VUNG v on v.QUOCGIA_FK=tt.QUOCGIA_FK "+
					"\n		left join KHUVUC kv on kv.VUNG_FK=v.PK_SEQ "+
					"\n where a.TRANGTHAI in ( 1, 2 ) ";
		
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
		
		String timkiem = request.getParameter("timkiem");
		if (timkiem == null)
			timkiem = "";
		obj.setTimkiem(timkiem);

		String nppId = request.getParameter("nppId");
		if (nppId == null)
			nppId = "";
		obj.setNppId(nppId);

		String khId = request.getParameter("khId");
		if (khId == null)
			khId = "";
		obj.setKhId(khId);
		
		String tenNVBH = request.getParameter("tenNVBH");
		if (tenNVBH == null)
			tenNVBH = "";
		obj.setTenNVBH(tenNVBH);
		
		String tenSP = request.getParameter("tenSP");
		if (tenSP == null)
			tenSP = "";
		obj.setTenSP(tenSP);
		
		obj.setLoainhanvien(session.getAttribute("loainhanvien"));
	    obj.setDoituongId(session.getAttribute("doituongId"));

	    Utility util = new Utility();
		if (nppId.length() > 0)
			query += " and a.npp_fk = '" + nppId + "'";
		
		if (tungaySX.length() > 0)
			query += " and a.ngaynhap >= '" + tungaySX + "'";

		if (denngaySX.length() > 0)
			query += " and a.ngaynhap <= '" + denngaySX + "'";

		if (trangthai.length() > 0)
			query += " and a.TrangThai = '" + trangthai + "'";

		if (sochungtu.length() > 0)
			query += " and a.sochungtu like '%" + sochungtu + "%'";
		
		if (timkiem.length() > 0)
			query += " and ( e.timkiem like N'%" + util.replaceAEIOU( timkiem ) + "%')";
		
		if (tenNVBH.length() > 0)
			query += " and ( dd.timkiem like N'%" + util.replaceAEIOU(tenNVBH) + "%')";
		
		if (tenSP.length() > 0)
			query += " and ( c.timkiem like N'%" + util.replaceAEIOU(tenSP) + "%')";

		query = "\n select * from (select  ROW_NUMBER() OVER (PARTITION BY data.mafast order by data.mafast  desc)as stt,*,SUM(doanhso) OVER (PARTITION BY mafast) as tongdoanhso,'1' as type from ( select tenkv,mafast,DONVI,asmTen, gsbhTen, ddkdTen, dbTen, tinhTen, makhCAP1, tenkhCAP1, makhCAP2, tenkhCAP2, maCTV, tenCTV, chucvu, htbh, kenh,   		"+
				 " \n		maSP, tenSP, ghichu, DONGIA, sum(SOLUONG) as soluong, sum(doanhso) as doanhso	         "+
				 "\n from"+
				 "\n ("+
				 		query +
				 "\n )"+
				 "\n DT group by tenkv,mafast,donvi,asmTen, gsbhTen, ddkdTen, dbTen, tinhTen, makhCAP1, tenkhCAP1, makhCAP2, tenkhCAP2, maCTV, tenCTV, chucvu, htbh, kenh,   		"+
				 " \n			maSP, tenSP, DONGIA, ghichu,DONVI) as data"+
				 "\n union all    "+
				"\n select  ROW_NUMBER() OVER (PARTITION BY data.kenh,data.mafast order by data.kenh,data.mafast  desc)as stt,*,SUM(doanhso) OVER (PARTITION BY data.kenh,data.mafast) as tongdoanhso,'2' as type from ( select tenkv,mafast,DONVI,asmTen, gsbhTen, ddkdTen, dbTen, tinhTen, makhCAP1, tenkhCAP1, makhCAP2, tenkhCAP2, maCTV, tenCTV, chucvu, htbh, kenh,   		"+
				 " \n		maSP, tenSP, ghichu, DONGIA, sum(SOLUONG) as soluong, sum(doanhso) as doanhso	         "+
				 "\n from"+
				 "\n ("+
				 		query +
				 "\n )"+
				 "\n DT group by tenkv,mafast,donvi,asmTen, gsbhTen, ddkdTen, dbTen, tinhTen, makhCAP1, tenkhCAP1, makhCAP2, tenkhCAP2, maCTV, tenCTV, chucvu, htbh, kenh,   		"+
				 " \n			maSP, tenSP, DONGIA, ghichu,DONVI) as data"+
				"\n  union all	"+
				 "\n select  ROW_NUMBER() OVER (PARTITION BY data.makhcap2,data.mafast order by data.makhcap2,data.mafast  desc)as stt,*,SUM(doanhso) OVER (PARTITION BY data.makhcap2,data.mafast) as tongdoanhso,'3' as type from ( select tenkv,mafast,DONVI,asmTen, gsbhTen, ddkdTen, dbTen, tinhTen, makhCAP1, tenkhCAP1, makhCAP2, tenkhCAP2, maCTV, tenCTV, chucvu, htbh, kenh,   		"+
				 " \n		maSP, tenSP, ghichu, DONGIA, sum(SOLUONG) as soluong, sum(doanhso) as doanhso	         "+
				 "\n from"+
				 "\n ("+
				 		query +
				 "\n )"+
				 "\n DT group by tenkv,mafast,donvi,asmTen, gsbhTen, ddkdTen, dbTen, tinhTen, makhCAP1, tenkhCAP1, makhCAP2, tenkhCAP2, maCTV, tenCTV, chucvu, htbh, kenh,   		"+
				 " \n			maSP, tenSP, DONGIA, ghichu,DONVI) as data"+
				"\n  union all	"+
				 "\n select  ROW_NUMBER() OVER (PARTITION BY data.mafast order by data.mafast  desc)as stt,*,SUM(doanhso) OVER (PARTITION BY mafast) as tongdoanhso,'4' as type from ( select tenkv,mafast,DONVI,asmTen, gsbhTen, ddkdTen, dbTen, tinhTen, makhCAP1, tenkhCAP1, makhCAP2, tenkhCAP2, maCTV, tenCTV, chucvu, htbh, kenh,   		"+
				 " \n		maSP, tenSP, ghichu, DONGIA, sum(SOLUONG) as soluong, sum(doanhso) as doanhso         "+
				 "\n from"+
				 "\n ("+
				 		query +
				 "\n )"+
				 "\n DT group by tenkv,mafast,donvi,asmTen, gsbhTen, ddkdTen, dbTen, tinhTen, makhCAP1, tenkhCAP1, makhCAP2, tenkhCAP2, maCTV, tenCTV, chucvu, htbh, kenh,   		"+
				 " \n			maSP, tenSP, DONGIA, ghichu,DONVI) as data) as dd order by maFAST,type,stt ASC";
		
		System.out.println(":: BAO CAO CTV MAU DDKD1111: " + query);
		return query;
	}
}