package geso.dms.center.servlets.nhapkho;

import geso.dms.center.db.sql.dbutils;
import geso.dms.center.util.Utility;
import geso.dms.center.beans.nhapkho.IErpNhapkho;
import geso.dms.center.beans.nhapkho.IErpNhapkhoList;
import geso.dms.center.beans.nhapkho.imp.ErpNhapkho;
import geso.dms.center.beans.nhapkho.imp.ErpNhapkhoList;

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

public class ErpNhapkhoSvl extends HttpServlet 
{
	private static final long serialVersionUID = 1L;
       
    public ErpNhapkhoSvl() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		IErpNhapkhoList obj;
		request.setCharacterEncoding("UTF-8");
	    response.setCharacterEncoding("UTF-8");
	    response.setContentType("text/html; charset=UTF-8");
	    
	    HttpSession session = request.getSession();	    
	    String userId = (String) session.getAttribute("userId");  
		String userTen = (String) session.getAttribute("userTen");  	
		String sum = (String) session.getAttribute("sum");
	    
	    geso.dms.center.util.Utility cutil = (geso.dms.center.util.Utility) session.getAttribute("util");
	    	    
	   
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
		    obj = new ErpNhapkhoList();
		    
		    if (action.equals("delete"))
		    {	
		    	String msg = this.DeleteChuyenKho(lsxId);
				obj.setMsg(msg);
		    }
		    else
		    {
		    	if(action.equals("chot"))
		    	{
		    		String msg = this.Chot(lsxId);
	    			obj.setMsg(msg);
		    	}
		    }
		    
		    obj.setUserId(userId);
		    obj.init("");
		    
			session.setAttribute("obj", obj);
				
			String nextJSP = request.getContextPath() + "/pages/Center/ErpNhapKho.jsp";
			response.sendRedirect(nextJSP);
	    }
	    
	    	    
	    
	}

	private String Chot(String lsxId) 
	{
		Utility util = new Utility();
		dbutils db = new dbutils();
		String msg = "";
		try
		{	
			//CHECK NGAY KHOA SO
			//THANG CHOT PHIEU XUAT KHO BUOC PHAI SAU THANG KS + 1
			/*String query = "select top(1) NAM as namMax, THANGKS as thangMax, " +
							"	( select ngaynhap from ERP_NHAPKHO where pk_seq = '" + lsxId + "' ) as ngaylapphieu " +
							"from ERP_KHOASOTHANG  " +
							"order by NAM desc, THANGKS desc ";
			System.out.println("1.Khoi tao thang: " + query);
			ResultSet rs = db.get(query);
			
			try
			{
				if(rs != null)
				{
					while(rs.next())
					{
						String thangHL = "";
						String namHL = "";
						
						String thangKs = rs.getString("thangMax");
						String namKs = rs.getString("namMax"); 
				
						String nam = rs.getString("ngaylapphieu").substring(0, 4);
						String thang = rs.getString("ngaylapphieu").substring(5, 7);
						
						if(thangKs.equals("12"))
						{
							thangHL = "1";
							namHL = Integer.toString(Integer.parseInt(namKs) + 1);
						}
						else
						{
							thangHL = Integer.toString(Integer.parseInt(thangKs) + 1);
							namHL = namKs;
						}
						
						if(thangHL.trim().length() < 2)
							thangHL = "0" + thangHL;
						
						if(	!thangHL.equals(thang) || !namHL.equals(nam) )
						{
							msg = "Bạn chỉ được phép chốt nhập kho sau tháng khóa sổ ( " + thangKs + " ) 1 tháng";
							rs.close();
							return msg;
						}
						
					}
					rs.close();
				}
			}
			catch (Exception e) 
			{
				e.printStackTrace();
				return "Lỗi khi chốt nhập kho " + e.getMessage();
			}
			*/
			
			
			db.getConnection().setAutoCommit(false);
			String dontrahang_fk="0";
		String	query =
			"	select b.ngaynhap,b.KhoNhap_FK,isnull(dontrahang_fk,0) as dontrahang_fk, sanpham_fk, a.soluong, a.solo, a.gianhap, a.ngaysanxuat, a.ngayhethan,   "+
			"		isnull( ( select COUNT(*) from ERP_KHOTT_SANPHAM where KHOTT_FK = b.KhoNhap_FK and sanpham_fk = a.sanpham_fk ), 0) as exitKHOTT ,  "+  
			"		isnull( ( select COUNT(*) from ERP_KHOTT_SP_CHITIET where KHOTT_FK = b.KhoNhap_FK and sanpham_fk = a.sanpham_fk and solo = a.solo ), 0) as exitKHOTT_CT,  "+  
			"	isnull  "+
			"	(  "+
			"		case when sp.DVDL_FK=a.DVDL_FK then 1 else  "+ 
			"		( select SOLUONG1 / isnull(nullif( SOLUONG2, 0), 1) from QUYCACH where sanpham_fk = a.sanpham_fk and DVDL2_FK = a.dvdl_fk  ) end, 0  "+
			"	) as quyCACH	   "+
			"	from ERP_NHAPKHO_SANPHAM a inner join ERP_NHAPKHO b on a.nhapkho_fk = b.PK_SEQ "+  
			"	inner join SANPHAM sp on sp.PK_SEQ=a.sanpham_fk "+
			"	where b.PK_SEQ = '"+lsxId+"' " ;
			System.out.println("---NHAP KHO: " + query );
			String ngaynhap="";
			ResultSet rs; 
			rs = db.get(query);
			if(rs != null)
			{
				while(rs.next())
				{
					dontrahang_fk=rs.getString("dontrahang_fk");
					String khoNHAP = rs.getString("KhoNhap_FK");
					String spIds = rs.getString("sanpham_fk");
					double soluong = rs.getDouble("soluong");
					String solo = rs.getString("solo");
					String gianhap = rs.getString("gianhap");
					String ngaysanxuat = rs.getString("ngaysanxuat");
					String ngayhethan = rs.getString("ngayhethan");
					int exitKHOTT = rs.getInt("exitKHOTT");
					int exitKHOTT_CT = rs.getInt("exitKHOTT_CT");
					double quyCACH = rs.getDouble("quyCACH");
					 ngaynhap=rs.getString("ngaynhap");
					
					if(quyCACH <= 0)
					{
						msg = "Sản phẩm ( " + rs.getString("spTEN") + " ) chưa thiết lập quy đổi ra thùng. Vui lòng kiểm tra lại. ";
						db.getConnection().rollback();
						return msg;
					}
					
					soluong = soluong * quyCACH;
					
					query="select isnull( ( select COUNT(*) from ERP_KHOTT_SANPHAM where KHOTT_FK = "+ khoNHAP +" and sanpham_fk = "+ spIds +" ), 0) as cokhotong ";
					System.out.println("cehck kho tong "+query);
					ResultSet rskhott=db.get(query);
					if(rskhott.next())
					{
						exitKHOTT=rskhott.getInt("cokhotong");
					}
					else
					{
						msg = "khong lấy dc sản phẩm " + query;
						db.getConnection().rollback();
						return msg;
					}
					rskhott.close();
					
					if(exitKHOTT > 0)
					{
						query = "UPDATE ERP_KHOTT_SANPHAM set soluong = soluong + '" + soluong + "', available = available + '" + soluong + "', " +
								"		giaton = ( ( ( giaton * soluong ) + ( " + gianhap + " * " + soluong + " ) ) / ( soluong + " + soluong + " ) ) " +
								"where khott_fk = '" + khoNHAP + "' and sanpham_fk = '" + spIds + "' ";
					}
					else
					{
						query = "insert ERP_KHOTT_SANPHAM(KHOTT_FK, SANPHAM_FK, GIATON, SOLUONG, BOOKED, AVAILABLE, THANHTIEN) " +
								"values( '" + khoNHAP + "', '" + spIds + "', '" + gianhap + "', '" + soluong + "', '0', '" + soluong + "', '0' )  ";
					}
					
					System.out.println("---KHO : " + query );
					if(!db.update(query))
					{
						msg = "Khong the cap nhat ERP_KHOTT_SANPHAM: " + query;
						db.getConnection().rollback();
						return msg;
					}
					
					//CHECK KHO CHI TIET DA CO CHUA
					query = "select COUNT(*) as soDONG from ERP_KHOTT_SP_CHITIET where KHOTT_FK = '" + khoNHAP + "' and sanpham_fk = '" + spIds + "' and solo = '" + solo.trim() + "' and ngayhethan='"+ngayhethan+"'  and ngaynhapkho='"+ngaynhap+"' ";
					ResultSet rsCHECK = db.get(query);
					if(rsCHECK.next())
					{
						exitKHOTT_CT = rsCHECK.getInt("soDONG");
					}
					rsCHECK.close();
					
					if(exitKHOTT_CT > 0)
					{
						query = "UPDATE ERP_KHOTT_SP_CHITIET set soluong = soluong + '" + soluong + "', available = available + '" + soluong + "' " +
								"where khott_fk = '" + khoNHAP + "' and sanpham_fk = '" + spIds + "' and SOLO = N'" + solo.trim() + "' and ngayhethan = '" + ngayhethan + "' and  ngaynhapkho='"+ngaynhap+"'";
					}
					else
					{
						query = "insert ERP_KHOTT_SP_CHITIET(KHOTT_FK, SANPHAM_FK, SOLO, SOLUONG, BOOKED, AVAILABLE, NGAYSANXUAT, NGAYHETHAN,ngaynhapkho) " +
								"values( '" + khoNHAP + "', '" + spIds + "', N'" + solo.trim() + "', '" + soluong + "', '0', '" + soluong + "', '" + ngaysanxuat + "', '" + ngayhethan + "' ,'"+ngaynhap+"')  ";
					}
					
					System.out.println("---KHO CHI TIET: " + query );
					if(!db.update(query))
					{
						msg = "Khong the cap nhat ERP_KHOTT_SP_CHITIET: " + query;
						db.getConnection().rollback();
						return msg;
					}
					
				}
				rs.close();
			}
			
			query = "update ERP_NHAPKHO set trangthai = '1' where pk_seq = '" + lsxId + "'";
			if(!db.update(query))
			{
				msg = "Khong the chot: " + query;
				db.getConnection().rollback();
				return msg;
			}
			query = "update ERP_CHUYENKHONPP set khonhan_fk=(select khonhap_fk from ERP_NHAPKHO where pk_seq="+lsxId+")  where pk_seq = (select chuyenkhonpp_fk from ERP_NHAPKHO where pk_seq="+lsxId+") ";
			if(!db.update(query))
			{
				msg = "Khong the chot: " + query;
				db.getConnection().rollback();
				return msg;
			}
			
			
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
				 
				 	query=	" 	select a.ngaytra,b.ngaynhapkho,a.kho_fk as kho_fk, a.npp_fk, "+sqlKENH+", b.sanpham_fk, b.solo, SUM(b.soluong) as tongxuat,b.NgayHetHan  " +
				 			" 	from DonTraHang a inner join DonTraHang_SP b on a.pk_seq = b.dontrahang_Fk " +
				 			" 	where dontrahang_Fk = '" + dontrahang_fk + "' " +
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
						
						msg=util.Update_NPP_Kho_Sp_Chitiet(ngaytra, "Đơn trả hàng về NCC-TT duyệt", db, kho_fk, sanpham_fk, npp_fk, kbh_fk, solo, NgayHetHan, ngaynhapkho, (-1)*tongxuat, (-1)*tongxuat, 0.0,0.0, 0.0);
						if(msg.length()>0)
						{
							db.getConnection().rollback();
							return msg;
						}
						msg=util.Update_NPP_Kho_Sp(ngaytra, "Đơn trả hàng về NCC-UPDATE", db, kho_fk, sanpham_fk, npp_fk, kbh_fk, (-1)*tongxuat,  (-1)*tongxuat, 0.0, 0.0);
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
			db.getConnection().setAutoCommit(true);
		}
		catch (Exception e) 
		{
			db.update("rollback");
			return "Exception: " + e.getMessage();
		}
		finally
		{
			if(db!=null) db.shutDown();
		}
		
		return "";
	}

	private String DeleteChuyenKho(String lsxId)
	{
		Utility util = new Utility();
		dbutils db = new dbutils();
		String msg = "";
		try
		{
			db.getConnection().setAutoCommit(false);
			String query="select isnull(import,0) import,isnull(dontrahang_fk,0) dontrahang_fk ,isnull(ChuyenKhoNpp_fk,0) ChuyenKhoNpp_fk  from ERP_NHAPKHO where pk_seq = '"+lsxId+"'  ";
			ResultSet dthRs=db.get(query);
			String dthId="";
			if(dthRs.next())
			{
					dthId=dthRs.getString("dontrahang_fk");
					String ChuyenKhoNpp_fk=dthRs.getString("ChuyenKhoNpp_fk");
					int _import=dthRs.getInt("import");
					if(_import==1)
					{
						System.out.println("vao import rau");
					}
					else
					{
						if(!dthId.equals("0"))
						{
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
						if(!ChuyenKhoNpp_fk.equals("0"))
						{
							query=" 	select a.ngaychuyen,b.ngaynhapkho,a.khoxuat_fk as kho_fk, a.npp_fk, (select case when  isnull(DUNGCHUNGKENH,0) =1 then 100025 else a.kbh_fk end kbh_fk  from NHAPHANPHOI where PK_SEQ=a.npp_fk ) kbh_fk, b.sanpham_fk, b.solo, SUM(b.soluong) as tongxuat,b.NgayHetHan  " +
									" 	from ERP_CHUYENKHONPP a inner join ERP_CHUYENKHONPP_SANPHAM_CHITIET b on a.pk_seq = b.chuyenkho_fk " +
									" 	where chuyenkho_fk = '" + ChuyenKhoNpp_fk + "' " +
									" 	group by a.khoxuat_fk, a.npp_fk, a.kbh_fk, b.solo, b.sanpham_fk,b.NgayHetHan,a.ngaychuyen,b.ngaynhapkho " ;
								
									ResultSet nkRs=db.get(query);
									while(nkRs.next()) 
									{
										String kho_fk=nkRs.getString("kho_fk");
										String nppId=nkRs.getString("npp_fk");
										String kbh_fk=nkRs.getString("kbh_fk");
										String sanpham_fk=nkRs.getString("sanpham_fk");
										String solo=nkRs.getString("solo");
										double soluong=nkRs.getDouble("tongxuat");
										String ngayhethan=nkRs.getString("ngayhethan");
										String ngaychuyen=nkRs.getString("ngaychuyen");
										String ngaynhapkho=nkRs.getString("ngaynhapkho");
										msg=util.Update_NPP_Kho_Sp_Chitiet(ngaychuyen, "Chuyển kênh NPP", db, kho_fk, sanpham_fk, nppId, kbh_fk, solo, ngayhethan, ngaynhapkho, soluong,0, soluong, 0.0, 0.0);
										if(msg.length()>0)
										{
											db.getConnection().rollback();
											return msg;
										}
										msg= util.Update_NPP_Kho_Sp(ngaychuyen, "Chuyển kênh NPP", db, kho_fk, sanpham_fk, nppId, kbh_fk, soluong, 0, soluong, 0.0);
										if(msg.length()>0)
										{
											db.getConnection().rollback();
											return msg;
										}
									}
									
									query="update ERP_CHUYENKHONPP set trangthai=2 where pk_seq="+ ChuyenKhoNpp_fk;
									if(db.updateReturnInt(query)!=1)
									{
										db.getConnection().rollback();
										return "không thể hủy  ERP_CHUYENKHONPP ";
									}
						}
						
					}
					
			}
			//
			query = "delete ERP_NHAPKHO_SANPHAM where nhapkho_fk = '" + lsxId + "' ";
			if(!db.update(query))
			{
				msg = "1.Khong the xoa: " + query;
				db.getConnection().rollback();
				return msg;
			}
			
			query = "delete ERP_NHAPKHO where pk_seq = '" + lsxId + "'";
			if(!db.update(query))
			{
				msg = "2.Khong the xoa: " + query;
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
		    
			IErpNhapkhoList obj = new ErpNhapkhoList();
		 
		    Utility util = new Utility();
		    
		    userId = util.antiSQLInspection(request.getParameter("userId")); 
		    obj.setUserId(userId);
		    
		    if(action.equals("Tao moi"))
		    {
		    	IErpNhapkho lsxBean = new ErpNhapkho();
		    	lsxBean.setUserId(userId);
		    	lsxBean.createRs();
	    		
		    	session.setAttribute("lsxBean", lsxBean);
		    	
	    		String nextJSP = request.getContextPath() + "/pages/Center/ErpNhapKhoNew.jsp";
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
			    	
			    	String nextJSP = request.getContextPath() + "/pages/Center/ErpNhapKho.jsp";
					response.sendRedirect(nextJSP);
			    }
		    	else
		    	{
			    	String search = getSearchQuery(request, obj);
			    	obj.init(search);
					
					
			    	session.setAttribute("obj", obj);  	
		    		session.setAttribute("userId", userId);
			
		    		String nextJSP = request.getContextPath() + "/pages/Center/ErpNhapKho.jsp";
		    		response.sendRedirect(nextJSP);
		    		
		    	}
		    }
		}
	    
	    
	}
	
	private String getSearchQuery(HttpServletRequest request, IErpNhapkhoList obj)
	{
		
		Utility util =new Utility(); 
		
		String query = "\n select a.PK_SEQ, a.trangthai, a.ngaynhap, a.lydo, NV.TEN as nguoitao, b.ten as khonhan, " +
		"\n a.NGAYSUA, a.NGAYTAO, NV2.TEN as nguoisua " +
		"\n from ERP_NHAPKHO a " +
		"\n inner join ERP_KHOTT b on a.khonhap_fk = b.pk_seq " +
		"\n inner join NHANVIEN nv on a.NGUOITAO = nv.PK_SEQ " +
		"\n inner join NHANVIEN nv2 on a.NGUOISUA = nv2.PK_SEQ " +
		"\n where a.pk_seq > 0 AND A.KHONHAP_FK IN " + util.quyen_khoTT(obj.getUserId(),util.KICHHOAT);
			
		String tungaySX = Utility.antiSQLInspection(request.getParameter("tungay"));
		if(tungaySX == null)
			tungaySX = "";
		obj.setTungayTao(tungaySX);
		
		String denngaySX = Utility.antiSQLInspection(request.getParameter("denngay"));
		if(denngaySX == null)
			denngaySX = "";
		obj.setDenngayTao(denngaySX);
		
		String trangthai = Utility.antiSQLInspection(request.getParameter("trangthai"));
		if(trangthai == null)
			trangthai = "";
		obj.setTrangthai(trangthai);
		

		if(tungaySX.length() > 0)
			query += " and a.ngaynhap >= '" + tungaySX + "'";
		
		if(denngaySX.length() > 0)
			query += " and a.ngaynhap <= '" + denngaySX + "'";
	
		if(trangthai.length() > 0)
			query += " and a.TrangThai = '" + trangthai + "'";
		
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
