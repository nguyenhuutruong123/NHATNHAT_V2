package geso.dms.distributor.util;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Enumeration;
import java.util.Hashtable;

import geso.dms.distributor.db.sql.dbutils;

public class FixData 
{
	public static void main(String[] arg) 
	{
		FixData fixed = new FixData();
		String sql="select distinct NPP_FK"+
		 " from ( "+
		 "select NPP_FK,Max(cast (NAM as nvarchar(5))+ CAST (THANGKS as nvarchar(2)) ) as thangnam "+
		 "from khoasothang  where thangks is not null --and npp_fk not in (select npp_fk from KHOASOTHANG where THANGKS=2 and NAM=2016)\n "+
	    "	group by NPP_FK) as data ";
		fixed.ProcessDATA("106223", "", new dbutils(), "0"); 
	/*	dbutils db=new dbutils();
		ResultSet rs=db.get(sql);
		try {
			while (rs.next())
			{
			fixed.khoasongay(rs.getString("npp_fk"));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			rs.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		db.shutDown();*/
		
		//fixed.khoasongay("106182");
		
		System.out.println("DONE...........");
	}

	public void khoasongay(String nppid) 
	{
		String tungay="2016-02-01";
		String denngay="2016-02-29";
		String thang="2";
		String nam="2016";
		dbutils db=new dbutils();
		try {
		
		String check="select COUNT(*) as sl from DONHANG where NPP_FK="+nppid+" and NGAYNHAP>= '"+tungay+"' and NGAYNHAP <='"+denngay+"' and TRANGTHAI=0";
		ResultSet rsc=db.get(check);
		rsc=db.get(check);
		if(rsc.next())
		{
			if(rsc.getInt("sl")>0)
			{
				
				db.update("insert into LOG_RUN_TONKHO (nppid,msg) values ('"+nppid+"',N'co don hang chua chot')");
				return ;
			}
		}
		rsc.close();
		 check="select COUNT(*) as sl from HOADON where NPP_FK="+nppid+" and NGAYXUATHD>= '"+tungay+"' and NGAYXUATHD <='"+denngay+"' and TRANGTHAI=0";
		 rsc=db.get(check);
		if(rsc.next())
		{
			if(rsc.getInt("sl")>0)
			{
				db.update("insert into LOG_RUN_TONKHO (nppid,msg) values ('"+nppid+"',N'co hoa don chua chot')");
				return ;
			}
		}
		rsc.close();
		String sql="select COUNT(*) as sl from ufn_XNT_Total_FULL('"+tungay+"','"+denngay+"',"+nppid+",null,null) where NXT<0 ";
		System.out.println("::::::::::::::::::-"+sql);
		ResultSet rs=db.get(sql);
		if(rs.next())
		{
			if(rs.getInt("sl")>0)
			{
				db.update("insert into LOG_RUN_TONKHO (nppid,msg) values ('"+nppid+"',N'xnt tong am')");
				return ;
			}
		}
		
		sql="select COUNT(*) as sl from ufn_XNT_Detail_FULL("+nppid+",'"+tungay+"','"+denngay+"') where NXT<0 ";
		System.out.println("::::::::::::::::::-"+sql);
		rs=db.get(sql);
		if(rs.next())
		{
			if(rs.getInt("sl")>0)
			{
				db.update("insert into LOG_RUN_TONKHO (nppid,msg) values ('"+nppid+"',N'xnt chi tiet am')");
				return ;
			}
		}
		db.getConnection().setAutoCommit(false);
		String query="INSERT INTO TONKHOTHANG(NPP_FK,KBH_FK,KHO_FK,SANPHAM_FK,SOLUONG,THANG,NAM)"+
				"select  npp_fk,kbh_fk,kho_fk,sanpham_fk,NXT,MONTH('"+tungay+"'),YEAR('"+tungay+"')  from ufn_XNT_Total_FULL('"+tungay+"','"+denngay+"',"+nppid+",null,null) where NXT<>0 ";
		System.out.println("____________"+query);
		
		if(db.updateReturnInt(query)<=0)
		{
			db.getConnection().rollback();
			db.getConnection().setAutoCommit(true);
			db.update("insert into LOG_RUN_TONKHO (nppid,msg) values ('"+nppid+"',N' loi insert tonkhothang  ')");
			return;
		}
		query="insert into TONKHOTHANG_CHITIET(NPP_FK,KBH_FK,KHO_FK,SANPHAM_FK,SOLO,NGAYHETHAN,THANG,NAM,SOLUONG)"+
				"select  NPP_FK,KBH_FK,KHO_FK,SANPHAM_FK,SOLO,NGAYHETHAN,MONTH('"+tungay+"'),YEAR('"+denngay+"'),NXT  from ufn_XNT_Detail_FULL("+nppid+",'"+tungay+"','"+denngay+"') where NXT<>0 ";
	
		System.out.println("XNT_CT_"+query);
		
		if(db.updateReturnInt(query)<=0)
		{
			db.getConnection().rollback();
			db.getConnection().setAutoCommit(true);
			db.update("insert into LOG_RUN_TONKHO (nppid,msg) values ('"+nppid+"',N'loi insert tonkhothangchitiet')");
			return;
		} 
		
		
		query = "insert into khoasothang(thangks, nam, ngaytao, nguoitao, npp_fk,ngaythangnam) " +
				"values('" + thang + "', '" + nam + "', '" + this.getDateTime() + "', '100002', '" + nppid+ "','"+tungay+"')";
		System.out.println("Query khoa so thang la: " + query);
		if(!db.update(query))
		{
			db.getConnection().rollback();
			db.getConnection().setAutoCommit(true);
			db.update("insert into LOG_RUN_TONKHO (nppid,msg) values ('"+nppid+"',N'loi insert khoasothang')");
			return;
		}	
		
		query=
				"	select isnull(a.sanpham_fk,b.sanpham_fk) as spfk,d.MA as nppMA,d.TEN as nppTEN,c.MA as spMa,c.TEN as spTEN,round( ISNULL(a.SoLuong,0),1,0) as Tong, round(isnull(b.SoLuong,0),1,0) as CT  "+
				"	from  "+
				"	(  "+
				"		select a.NPP_FK,a.SANPHAM_FK,SUM(SOLUONG) as SoLuong,KHO_FK  "+
				"		from TONKHOTHANG a " +
				"   where a.npp_fk='"+nppid+"' and thang='"+thang+"' and nam='"+nam+"'        "+
				"		group by NPP_FK,SANPHAM_FK,KHO_FK "+
				"	)as a full outer join  "+ 
				"	(  "+
				"		select a.NPP_FK,a.SANPHAM_FK,SUM(SOLUONG) as SoLuong,KHO_FK  "+
				"		from TONKHOTHANG_CHITIET a   "+
				"   where a.npp_fk='"+nppid+"' and thang='"+thang+"' and nam='"+nam+"'        "+
				"		group by NPP_FK,SANPHAM_FK,KHO_FK   "+
				"	)as b on a.NPP_FK=b.NPP_FK and a.SANPHAM_FK=b.SANPHAM_FK  and a.KHO_FK=b.KHO_FK  "+
				"	left join SANPHAM c on c.PK_SEQ=ISNULL(a.SANPHAM_FK,b.SANPHAM_FK)  "+ 
				"	LEFT join NHAPHANPHOI d on d.PK_SEQ=ISNULL(a.NPP_FK,b.NPP_FK)  "+
				" where round( ISNULL(a.SoLuong,0),1,0)  <> round(isnull(b.SoLuong,0),1,0) ";
					
					System.out.println("___Query__"+query);
					String msg="";
					 rs =db.get(query);
					while(rs.next())
					{
						msg+=""+rs.getString("spMa") +" - "+rs.getString("tong")+"-- "+rs.getString("CT")+" \n";
						System.out.println(""+rs.getString("spfk") +" - "+rs.getString("tong")+"-- "+rs.getString("CT")+" \n");
					}
					rs.close();
					if(msg.length()>0)
					{
						db.getConnection().rollback();
						db.getConnection().setAutoCommit(true);
						db.update("insert into LOG_RUN_TONKHO (nppid,msg) values ('"+nppid+"',N'loi lech xnt sp "+msg+" ')");
						return;
					}
					db.getConnection().commit();
					db.getConnection().setAutoCommit(true);
					
				
		
		}catch( Exception e)
		{
			e.printStackTrace();
			try {
				db.getConnection().rollback();
				db.getConnection().setAutoCommit(true);
				db.update("insert into LOG_RUN_TONKHO (nppid,msg) values ('"+nppid+"',N'loi ks ')");
			
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			
		}
		finally
		{
			if(db!=null)db.shutDown();
		}
		
		
	}
	
	
	public String ProcessDATA(String nppIds, String spIds, dbutils db, String sudung_db_trongTRANSACTION) 
	{
		//dbutils db = new dbutils();
		
		//B1. Lấy những NPP sẽ chạy tồn kho
		String query = "select pk_seq as nppId, 1 as codungLO " +
					   "from NHAPHANPHOI a where a.pk_seq in ( " + nppIds + " )  ";
		System.out.println("________"+query);
		ResultSet rsNPP = db.get(query);
		String nppId = "";
		String msg = "";
		try 
		{
			while(rsNPP.next())
			{
				nppId = rsNPP.getString("nppId");
				String codungLO = rsNPP.getString("codungLO");
				
				//CHECK XEM NXT THEO TỔNG VÀ NXT CHI TIẾT CÓ KHỚP NHAU KHÔNG, NẾU LỆCH THÌ PHẢI XỬ LÝ NHƯNG TRƯỜNG HỢP NÀY RIÊNG
				
				System.out.println("Đang xử lý NPP: " + nppId + " -- Có dùng lô: " + codungLO );
				String error = this.ProcessCO_DUNG_LO(db, nppId, spIds, sudung_db_trongTRANSACTION);
				
				if(error.trim().length() > 0)
				{
					query = "Insert LOG_RUN_TONKHO ( nppId, msg, tinhtrang ) values( '" + nppId + "', N'" + error + "', N'NotOK' ) ";
					db.update(query);
				}
				else
				{
					query = "Insert LOG_RUN_TONKHO ( nppId, msg, tinhtrang ) values( '" + nppId + "', N'" + error + "', 'OK' ) ";
					db.update(query);
				}
				
				msg += error + "  ";
			}
			rsNPP.close();	
		} 
		catch (Exception e) 
		{
			e.printStackTrace();
			msg += e.getMessage() + "  ";
			
			query = "Insert LOG_RUN_TONKHO ( nppId, msg ) values( '" + nppId + "', N'" + e.getMessage() + "' ) ";
			db.update(query);
			
			return e.getMessage();
		}
		
		if(msg.trim().length() > 0)
		{
			query = "Insert LOG_RUN_TONKHO ( nppId, msg, tinhtrang ) values( '" + nppId + "', N'" + msg + "', N'NotOK' ) ";
			db.update(query);
		}
		
		if(sudung_db_trongTRANSACTION.equals("0") )
			db.shutDown();
		
		return "";
	}

	private String ProcessCO_DUNG_LO(dbutils db, String nppId, String spIds, String sudung_db_trongTRANSACTION) 
	{
		String msg = "";
		
		String query="";
	
		//B0. XOA HẾT NHỮNG ĐỔI SỐ LÔ HỆ THỐNG TỰ TẠO TRONG VÒNG 1 NGÀY
		query = "delete ERP_DOISOLONPP where npp_fk = '" + nppId + "' and hethongTAO = '1' "
				+ " and isnull( DATEDIFF( dd, create_DATE, GETDATE()), 0 ) <= 1 and ngaydoi >= '" + this.getDateTime() + "' ";
		if(spIds.trim().length() > 0)
			query += " and sanpham_fk in ( " + spIds + " ) ";
		if(!db.update(query))
		{
			return "1.0.Lỗi cập nhật ERP_DOISOLONPP: " + query;
		}
		
		query = "delete ERP_DOISOLONPP_SANPHAM where doisolo_fk not in ( select pk_seq from ERP_DOISOLONPP )  ";
		if(!db.update(query))
		{
			return "1.1.Lỗi cập nhật ERP_DOISOLONPP: " + query;
		}
		
		//B1. RESET LẠI
		query = "update NHAPP_KHO set daxulyamkho = 1, soluongNXT = '0', soluongNHAP_SAUNXT = '0' where NPP_FK = '" + nppId + "' ";
		if(spIds.trim().length() > 0)
			query += " and sanpham_fk in ( " + spIds + " ) ";

		if(!db.update(query))
		{
			return "1.Lỗi cập nhật NHAPP_KHO " + query;
		}
		
		//insert nhung lo co trong xnt ma ko co trong nhapp_kho_chitiet 
		////Cái này chỉ xử lý khi chạy bằng tay, chạy tự động không dùng
		/*query = " insert into NHAPP_KHO_CHITIET (KHO_FK,NPP_FK,SANPHAM_FK,KBH_FK,SOLO,NGAYHETHAN,SOLUONG,BOOKED,AVAILABLE,GIAMUA,soluongNXT)"+
			    " select kho_fk,npp_fk,sanpham_fk,kbh_fk,SoLo,isnull(NgayHetHan,''),0,0,0,null,null from  ufn_XNT_ChiTiet (" + nppId + ") b where  "+
			    "  not exists (  "+
			    "  select  a.kho_fk,a.npp_fk,a.sanpham_fk,a.kbh_fk,a.SoLo,a.NgayHetHan  "+
			    "  from NHAPP_KHO_CHITIET a   "+
			    " 	 where a.NPP_FK=b.npp_fk and a.SANPHAM_FK=b.sanpham_fk and a.KHO_FK=b.kho_fk and a.SOLO=b.SOLO   "+
			    "		and a.KBH_FK=b.kbh_fk and a.NGAYHETHAN=isnull(b.NgayHetHan,''))  ";  
		if(!db.update(query))
		{
			System.out.println("loi "+ query);
			return "1.4.Lỗi cập nhật insert lo " + query;
		}*/
		
		//B1.1. CẬP NHẬT LẠI BOOKED
		query = "update kho set kho.BOOKED = isnull(Total_Booked, 0) "
				+ " from NHAPP_KHO kho left join ufn_Booked_Total ( " + nppId + " ) book "
				+ "			on kho.NPP_FK = book.npp_fk and kho.KHO_FK = book.kho_fk and kho.KBH_FK = book.kbh_fk and kho.SANPHAM_FK = book.sanpham_fk "
				+ "where kho.NPP_FK = '" + nppId + "' and kho.BOOKED != ISNULL(book.Total_Booked, 0) ";
		if(spIds.trim().length() > 0)
			query += " and kho.sanpham_fk in ( " + spIds + " ) ";
		
		if(!db.update(query))
		{
			return "1.1.Lỗi cập nhật NHAPP_KHO " + query;
		}

		//B1.2. CẬP NHẬT LẠI BOOKED CHI TIẾT
	/*	query = "update kho set kho.BOOKED = isnull(Total_Booked, 0), AVAILABLE = SOLUONG - isnull(Total_Booked, 0) "
				+ "from NHAPP_KHO_CHITIET kho left join ufn_Booked_ChiTiet ( " + nppId + " ) book "
				+ "		on kho.NPP_FK = book.npp_fk and kho.KHO_FK = book.kho_fk and kho.KBH_FK = book.kbh_fk and kho.SANPHAM_FK = book.sanpham_fk  "
				+ "			and kho.SOLO = book.SoLo and kho.NGAYHETHAN =isnull(book.NgayHetHan,'') "
				+ "where kho.NPP_FK = '" + nppId + "' ";
		if(spIds.trim().length() > 0)
			query += " and kho.sanpham_fk in ( " + spIds + " ) ";
		System.out.println("query"+query);
		if(!db.update(query))
		{
			return "1.2.Lỗi cập nhật NHAPP_KHO_CHITIET " + query;
		}*/
		
		//B2. TIM NHUNG SP BI LECH KHO TỔNG
		query =  "select kho_fk, sanpham_fk, npp_fk, kbh_fk, soluong, booked, available,  " + 
				 "	  ( isnull " + 
				 "			( ( " + 
				 "			  select xnt " + 
				 "			  from dbo.ufn_XNT_Total(  " + nppId + "  ) data " + 
				 "			  where data.npp_fk = kho.NPP_FK and data.sanpham_fk = kho.SANPHAM_FK " + 
				 "						and data.kbh_fk = kho.KBH_FK and kho.KHO_FK = data.KHO_FK " + 
				 "			),0 ) ) as soluongNXT " + 
				 " from NHAPP_KHO kho "+
				 " where ( kho.AVAILABLE + kho.BOOKED + kho.SOLUONG !=   "+
				 "	2 *  ( isnull  "+
				 "			( (  "+
				 "			  select xnt "+  
				 "			  from dbo.ufn_XNT_Total(   " + nppId + "   ) data   "+ 
				 "			  where data.npp_fk = kho.NPP_FK and data.sanpham_fk = kho.SANPHAM_FK   "+ 
				 "					and data.kbh_fk = kho.KBH_FK and kho.KHO_FK = data.KHO_FK  "+ 
				 "			),0) ) and kho.NPP_FK =   " + nppId + "  "+ 
				 "  or isnull( ( select SUM( AVAILABLE + BOOKED + SOLUONG ) from NHAPP_KHO_CHITIET where NPP_FK = kho.NPP_FK and KBH_FK = kho.KBH_FK and SANPHAM_FK = kho.SANPHAM_FK and KHO_FK = kho.KHO_FK ), 0 )  != ( kho.AVAILABLE + kho.BOOKED + kho.SOLUONG ) )	";		
	
		if(nppId.trim().length() > 0)
			query += " and kho.npp_fk in ( " + nppId + " ) ";
		
		if(spIds.trim().length() > 0)
			query += " and kho.sanpham_fk in ( " + spIds + " ) ";
		
		System.out.println("---LAY SP BI LECH: " + query);
		ResultSet rsTK = db.get(query);
		if(rsTK != null)
		{
			try 
			{
				while(rsTK.next())
				{
					String khoId = rsTK.getString("kho_fk");
					String spId = rsTK.getString("sanpham_fk");
					String kbhId = rsTK.getString("kbh_fk");
					
					System.out.println("+++SANPHAM: " + spId + " -- SO NXT: " + rsTK.getString("soluongNXT"));
					double soluongNXT = rsTK.getDouble("soluongNXT");
					double booked = rsTK.getDouble("booked");

					//Xu ly duoc san pham nao thi FIX SP do, khong duoc thi bo qua
					String error = this.Process_Unit(db, nppId, khoId, spId, kbhId, soluongNXT, booked, sudung_db_trongTRANSACTION);
					if(error.trim().length() > 0)
					{
						msg += error + "  ";
						
						query = "insert LOG_RUN_TONKHO( nppId, msg, tinhtrang, sanphamId  ) values ( '" + nppId + "', N'" + error + "', 'NotOK', '" + spId + "' ) ";
						db.update(query);
					}
				}
				rsTK.close();
			} 
			catch (Exception e) 
			{
				e.printStackTrace();
				msg += e.getMessage() + "  ";
			}
		}
		
		return msg;
	}

	private String Process_Unit(dbutils db, String nppId, String khoId, String spId, String kbhId, double soluongNXT, double booked, String sudung_db_trongTRANSACTION) 
	{
		String msg = "";
		String query = "";
		
		try
		{
			if(sudung_db_trongTRANSACTION.equals("0"))
				db.getConnection().setAutoCommit(false);
			
			boolean secapnhat_khoCT = false;   //Chỉ chạy cho những SP cập nhật được kho tổng
			double luongTANG = 0;
		
			if(soluongNXT >= 0)
			{
				//TRUONG HOP BOOKED VUOT QUA AVAI
				if( soluongNXT >= booked  )
				{
					query = "Update NHAPP_KHO set soluong = '" + soluongNXT + "', available = '" + soluongNXT + "' - booked " +
							"where npp_fk = '" + nppId + "' and kho_fk = '" + khoId + "' and sanpham_fk = '" + spId + "' and kbh_fk = '" + kbhId + "' ";
					
					secapnhat_khoCT = true;
				}
				else
				{
					//CAC TRUONG HOP NAY PHAI NOTE LAI DE HUY BOT DON HANG DA BOOKED
					//LUA CHON: CHO SO LUONG VE = BOOKED, CHO NHAP HANG DE XU LY TON KHO
					
					query = "Update NHAPP_KHO set soluong = booked, available = 0, soluongNXT = '" + ( soluongNXT - booked ) + "', soluongNHAP_SAUNXT = 0, daxulyAMKHO = 0 " +
							"where npp_fk = '" + nppId + "' and kho_fk = '" + khoId + "' and sanpham_fk = '" + spId + "' and kbh_fk = '" + kbhId + "' ";
					
					luongTANG = booked - soluongNXT;
				}
			}
			else
			{
					query = "Update NHAPP_KHO set soluong = booked, available = 0, soluongNXT = '" + ( soluongNXT - booked ) + "', soluongNHAP_SAUNXT = 0, daxulyAMKHO = 0 " +
							"where npp_fk = '" + nppId + "' and kho_fk = '" + khoId + "' and sanpham_fk = '" + spId + "' and kbh_fk = '" + kbhId + "' ";
					
					luongTANG = booked - soluongNXT;
			}
			
			System.out.println("---CAP NHAT NXT: " + query);
			if(!db.update(query))
			{
				db.getConnection().rollback();
				return "1.3.Lỗi cập nhật NHAPP_KHO " + query;
			}
			
			if(secapnhat_khoCT)
			{
				msg = this.XuLyKhoChiTiet(db, nppId, khoId, kbhId, spId);
				if(msg.trim().length() > 0)
				{
					db.getConnection().rollback();
					return msg;
				}
			}
			else if(luongTANG > 0)  //Phân bổ lượng tăng tạm thời vào kho chi tiết cho những sản phẩm đã bán lố tồn kho
			{
				msg = this.XuLyKhoChiTiet_Tang(db, nppId, khoId, kbhId, spId, luongTANG);
				if(msg.trim().length() > 0)
				{
					db.getConnection().rollback();
					return msg;
				}
			}
			
			//SAU KHI DOI SO LO, VAN CO THE CON LO AM, NEN PHAI CHUYEN NHUNG LO DUONG QUA
			query = "select kho.SOLO, kho.NGAYHETHAN, kho.soluong, kho.booked, kho.booked - kho.soluong as soluongCANDOI "
					+ "from NHAPP_KHO_CHITIET kho "
					+ "where kho.NPP_FK = '" + nppId + "' and kho.SANPHAM_FK = '" + spId + "' and kho.KBH_FK = '" + kbhId + "' and kho.KHO_FK = '" + khoId + "' and SOLUONG < BOOKED " + 
					" order by ( kho.booked - kho.soluong ) desc ";
			System.out.println("DE XUAT:: " + query);
			ResultSet rs = db.get(query);
			if(rs != null)
			{
				
				try 
				{
					while(rs.next())
					{
						String solo = rs.getString("SOLO");
						String ngayhethan = rs.getString("NGAYHETHAN");
						double soluongCANDOI = rs.getDouble("soluongCANDOI");
						
						if(soluongCANDOI > 0)
						{
							msg = DeXuatLoDoi(db, nppId, khoId, kbhId, spId, solo, ngayhethan, soluongCANDOI );
							if(msg.trim().length() > 0)
								return msg;
						}
					}
					rs.close();
				} 
				catch (Exception e)
				{
					e.printStackTrace();
					return "1.9.Lỗi xử lý kho chi tiết: " + e.getMessage();
				}
			}
			
			
			query = " update NHAPP_KHO_CHITIET set AVAILABLE = SOLUONG - BOOKED  "
					+ "where NPP_FK = '" + nppId + "' and SANPHAM_FK = '" + spId + "' and KBH_FK = '" + kbhId + "' and KHO_FK = '" + khoId + "'  ";
			if(!db.update(query))
			{
				return "1.9.Lỗi cập nhật ERP_DOISOLONPP " + query;
			}
			
			if(sudung_db_trongTRANSACTION.equals("0"))
			{
				db.getConnection().commit();
				db.getConnection().setAutoCommit(true);
			}
		}
		catch(Exception ex)
		{
			db.update("rollback");
			msg = "Lỗi khi xử lý UNIT: " + ex.getMessage();
			return ex.getMessage();
		}
		
		return msg;
		
	}

	private String XuLyKhoChiTiet_Tang(dbutils db, String nppId, String khoId, String kbhId, String spId, double luongTANG) 
	{
		//Phân bổ lượng tăng vào kho chi tiết
		String msg = "";
		String query = "";
		//KHÔNG ĐƯỢC RESET BOOKED TẠI ĐÂY
		/*query =  "update NHAPP_KHO_CHITIET set soluong=0,booked=0,available=0 "
				+ "where NPP_FK = '" + nppId + "' and SANPHAM_FK = '" + spId + "' and KBH_FK = '" + kbhId + "' and KHO_FK = '" + khoId + "' ";*/
		query =  "update NHAPP_KHO_CHITIET set soluong = 0, available = 0 "
				+ "where NPP_FK = '" + nppId + "' and SANPHAM_FK = '" + spId + "' and KBH_FK = '" + kbhId + "' and KHO_FK = '" + khoId + "' ";
		System.out.println("___" + query);
		if(!db.update(query))
		{
			return "1.4.Lỗi cập nhật NHAPP_KHO_CHITIET " + query;
		}
		
		//Cập nhật số lượng của sản phảm theo NXT chi tiết
		query = " update k  \n"+
				 " set k.SOLUONG = a.soluong  \n"+
				 "  from nhapp_kho_chitiet k inner join "+
				 " (select kho.SANPHAM_FK,kho.NPP_FK,kho.SOLO,kho.NGAYHETHAN,kho.KBH_FK,kho.KHO_FK,SUM(ton.XNT) as soluong"+
				 " from NHAPP_KHO_CHITIET kho inner join ufn_XNT_ChiTiet ( " + nppId + " ) ton \n"+
				 "		on kho.NPP_FK = ton.npp_fk and kho.KHO_FK = ton.kho_fk and kho.KBH_FK = ton.kbh_fk  \n"+
				 "	and kho.SANPHAM_FK = ton.sanpham_fk and kho.SOLO = ton.SoLo and kho.NGAYHETHAN = ton.NgayHetHan \n"+
				 " where kho.NPP_FK = '" + nppId + "' and kho.SANPHAM_FK = '" + spId + "' and kho.KBH_FK = '" + kbhId + "' and kho.KHO_FK = '" + khoId + "'     "+
 				 "  group by kho.npp_fk,kho.sanpham_fk,kho.kbh_fk,kho.kho_fk,kho.SoLo,kho.NgayHetHan ) a \n"+
				 "  on a.KBH_FK=k.KBH_FK and a.KHO_FK=k.KHO_FK and a.NPP_FK=k.NPP_FK and a.SANPHAM_FK=k.SANPHAM_FK and a.NGAYHETHAN=k.NGAYHETHAN and a.SOLO=k.SOLO ";

		System.out.println("___"+query);
		if(!db.update(query))
		{
			 System.out.println("loi_________"+query);
			return "1.4.Lỗi cập nhật NHAPP_KHO_CHITIET " + query;
		}
		
		query = "select kho.SOLO, kho.NGAYHETHAN, kho.soluong "
				+ "from NHAPP_KHO_CHITIET kho "
				+ "where kho.NPP_FK = '" + nppId + "' and kho.SANPHAM_FK = '" + spId + "' and kho.KBH_FK = '" + kbhId + "' and kho.KHO_FK = '" + khoId + "' "
				+ "order by soluong asc ";
		ResultSet rs = db.get(query);
		String solodefault="";
		String ngayhethandefault="";
		if(rs != null)
		{
			try 
			{
				double totalTANG = 0;
				boolean exit = false;
				while(rs.next())
				{
					String solo = rs.getString("SOLO");
					String ngayhethan = rs.getString("NGAYHETHAN");
					double soluong = Math.abs(rs.getDouble("soluong"));
					solodefault=solo;
					ngayhethandefault=ngayhethan;
					totalTANG += soluong;
										
					if(totalTANG <= luongTANG )
					{
						query = " update NHAPP_KHO_CHITIET set SOLUONG = SOLUONG + '" + soluong + "'  "
								+ "where NPP_FK = '" + nppId + "' and SANPHAM_FK = '" + spId + "' and KBH_FK = '" + kbhId + "' and KHO_FK = '" + khoId + "' and solo = '" + solo + "' and ngayhethan = '" + ngayhethan + "' ";
					}
					else
					{
						double soTANG = luongTANG - ( totalTANG - soluong );
						query = " update NHAPP_KHO_CHITIET set SOLUONG = SOLUONG + '" + soTANG + "'  "
								+ "where NPP_FK = '" + nppId + "' and SANPHAM_FK = '" + spId + "' and KBH_FK = '" + kbhId + "' and KHO_FK = '" + khoId + "' and solo = '" + solo + "' and ngayhethan = '" + ngayhethan + "' ";
						exit = true;
					}
					
					if(!db.update(query))
					{
						return "1.10.Lỗi cập nhật NHAPP_KHO_CHITIET " + query;
					}
					
					if(exit)
						break;
				}
				if(totalTANG < luongTANG )
				{
					query = " update NHAPP_KHO_CHITIET set SOLUONG = SOLUONG + '" + (luongTANG-totalTANG) + "'  "
							+ "where NPP_FK = '" + nppId + "' and SANPHAM_FK = '" + spId + "' and KBH_FK = '" + kbhId + "' and KHO_FK = '" + khoId + "' and solo = '" + solodefault + "' and ngayhethan = '" + ngayhethandefault + "' ";
					if(!db.update(query))
					{
						return "1.10.Lỗi cập nhật NHAPP_KHO_CHITIET " + query;
					}
				}
			
				rs.close();
			} 
			catch (Exception e)
			{
				e.printStackTrace();
				return "1.10.Lỗi xử lý kho chi tiết: " + e.getMessage();
			}
		}

		return msg;
	}

	
	
	private String XuLyKhoChiTiet(dbutils db, String nppId, String khoId, String kbhId, String spId) 
	{
		String msg = "";
		String query="";
		
		//RESET KHO CHI TIẾT
		/*query = "update NHAPP_KHO_CHITIET set soluong=0,booked=0,available=0 "
				+ "where NPP_FK = '" + nppId + "' and SANPHAM_FK = '" + spId + "' and KBH_FK = '" + kbhId + "' and KHO_FK = '" + khoId + "' ";*/
		query = "update NHAPP_KHO_CHITIET set soluong = 0, available = 0 "
				+ "where NPP_FK = '" + nppId + "' and SANPHAM_FK = '" + spId + "' and KBH_FK = '" + kbhId + "' and KHO_FK = '" + khoId + "' ";
		if(!db.update(query))
		{
			return "1.4.Lỗi cập nhật NHAPP_KHO_CHITIET " + query;
		}
			
		//Cập nhật số lượng của sản phảm theo NXT chi tiết
		query = " update k  \n"+
				 " set k.SOLUONG = a.soluong  \n"+
				 "  from nhapp_kho_chitiet k inner join "+
				 " (select kho.SANPHAM_FK,kho.NPP_FK,kho.SOLO,kho.NGAYHETHAN,kho.KBH_FK,kho.KHO_FK,SUM(ton.XNT) as soluong"+
				 " from NHAPP_KHO_CHITIET kho inner join ufn_XNT_ChiTiet ( " + nppId + " ) ton \n"+
				 "		on kho.NPP_FK = ton.npp_fk and kho.KHO_FK = ton.kho_fk and kho.KBH_FK = ton.kbh_fk  \n"+
				 "	and kho.SANPHAM_FK = ton.sanpham_fk and kho.SOLO = ton.SoLo and kho.NGAYHETHAN =isnull( ton.NgayHetHan,'') \n"+
				 " where kho.NPP_FK = '" + nppId + "' and kho.SANPHAM_FK = '" + spId + "' and kho.KBH_FK = '" + kbhId + "' and kho.KHO_FK = '" + khoId + "'     "+
 				 "  group by kho.npp_fk,kho.sanpham_fk,kho.kbh_fk,kho.kho_fk,kho.SoLo,kho.NgayHetHan ) a \n"+
				 "   on a.KBH_FK=k.KBH_FK and a.KHO_FK=k.KHO_FK and a.NPP_FK=k.NPP_FK and a.SANPHAM_FK=k.SANPHAM_FK and a.NGAYHETHAN=k.NGAYHETHAN and a.SOLO=k.SOLO ";

		System.out.println("_________"+query);
		if(!db.update(query))
		{
			System.out.println("loi_________"+query);
			return "1.4.Lỗi cập nhật NHAPP_KHO_CHITIET " + query;
		}
		
		//Đổi số lô nếu có trường hợp âm kho chi tiết
		query = "select kho.SOLO, kho.NGAYHETHAN, kho.soluong, kho.booked, kho.booked - kho.soluong as soluongCANDOI "
				+ "from NHAPP_KHO_CHITIET kho "
				+ "where kho.NPP_FK = '" + nppId + "' and kho.SANPHAM_FK = '" + spId + "' and kho.KBH_FK = '" + kbhId + "' and kho.KHO_FK = '" + khoId + "' and SOLUONG < BOOKED " + 
				" order by ( kho.booked - kho.soluong ) desc ";
		System.out.println("DE XUAT:: " + query);
		ResultSet rs = db.get(query);
		if(rs != null)
		{
			try 
			{
				while(rs.next())
				{
					String solo = rs.getString("SOLO");
					String ngayhethan = rs.getString("NGAYHETHAN");
					double soluongCANDOI = rs.getDouble("soluongCANDOI");
					
					if(soluongCANDOI > 0)
					{
						msg = DeXuatLoDoi(db, nppId, khoId, kbhId, spId, solo, ngayhethan, soluongCANDOI );
						if(msg.trim().length() > 0)
							return msg;
					}
				}
				rs.close();
			} 
			catch (Exception e)
			{
				e.printStackTrace();
				return "1.8.Lỗi xử lý kho chi tiết: " + e.getMessage();
			}
		}
		
		return msg;
	}

	private String DeXuatLoDoi(dbutils db, String nppId, String khoId, String kbhId, String spId, String solo, String ngayhethan, double soluongCANDOI ) 
	{
		//Vì kho tổng đã bằn kho CT, và cập nhật được kho tổng, nên do đó hàm này lúc nào cũng đề xuất được
		Hashtable<String, String> dexuat = new Hashtable<String, String>();
		
		String query = "select SOLO, NGAYHETHAN, SOLUONG - BOOKED as avai "
						+ "from NHAPP_KHO_CHITIET kho "
						+ "where kho.NPP_FK = '" + nppId + "' and kho.SANPHAM_FK = '" + spId + "' and kho.KBH_FK = '" + kbhId + "' and kho.KHO_FK = '" + khoId + "'  "
						+ "			and ( ( SOLO = '" + solo + "' and NGAYHETHAN != '" + ngayhethan + "' ) or ( SOLO != '" + solo + "' ) ) and ( SOLUONG - BOOKED ) > 0 "
						+ "order by kho.AVAILABLE desc";
		System.out.println("DE XUAT DOI LO:: " + query);
		ResultSet rs = db.get(query);
		if(rs != null)
		{
			try 
			{
				double totalDADEXUAT = 0;
				while(rs.next())
				{
					String soloDEXUAT = rs.getString("SOLO");
					if(soloDEXUAT.trim().length() <= 0)
						soloDEXUAT = " ";
					
					String ngayhethanDEXUAT = rs.getString("NGAYHETHAN");
					if(ngayhethanDEXUAT.trim().length() <= 0)
						ngayhethanDEXUAT = " ";
					
					double avai = rs.getDouble("avai");
					
					totalDADEXUAT += avai;
					if(totalDADEXUAT >= soluongCANDOI )
					{
						double slgDEXUAT = avai - ( totalDADEXUAT - soluongCANDOI );
						dexuat.put(soloDEXUAT + "__" + ngayhethanDEXUAT, Double.toString(slgDEXUAT));
						
						break;
					}
					else
					{
						dexuat.put(soloDEXUAT + "__" + ngayhethanDEXUAT, Double.toString(avai));
					}
				}
				rs.close();
			} 
			catch (Exception e) {
				
				e.printStackTrace();
				return "1.5. Lỗi đổi số lô: " + e.getMessage();
			}
		}
		
		if(dexuat.size() > 0)
		{
			//Chèn đổi số lô
			query = "insert ERP_DOISOLONPP( kho_fk, kbh_fk, npp_fk, trangthai, ngaydoi, nguoitao, nguoisua, ngaytao, ngaysua, hethongTAO, sanpham_fk ) "
					+ "values ( '" + khoId + "', '" + kbhId + "', '" + nppId + "', '1', '" + this.getDateTime() + "', '100002', '100002', '" + this.getDateTime() + "', '" + this.getDateTime() + "', '1', '" + spId + "' ) ";
			System.out.println("====== CHEN DOI SO LO:: " + query );
			if(!db.update(query))
			{
				return "1.6.Lỗi cập nhật ERP_DOISOLONPP " + query;
			}
			
			Enumeration<String> keys = dexuat.keys();
			while(keys.hasMoreElements())
			{
				String key = keys.nextElement();
				
				String[] keyARR = key.split("__");
				
				query = " insert ERP_DOISOLONPP_SANPHAM( doisolo_fk, sanpham_fk, soloOLD, solo, soluong, ngayhethan, NgayHetHanOLD ) "
						+ "select SCOPE_IDENTITY(), '" + spId + "', '" + solo + "', '" + keyARR[0].trim() + "', '" + dexuat.get(key) + "', '" + keyARR[1].trim() + "', '" + ngayhethan + "'  ";
				System.out.println("====== CHEN DOI SO LO - SP:: " + query );
				if(!db.update(query))
				{
					return "1.7.Lỗi cập nhật ERP_DOISOLONPP " + query;
				}
				
				//Tăng giảm kho
				query = " update NHAPP_KHO_CHITIET set SOLUONG = SOLUONG + '" + dexuat.get(key) + "'  "
						+ "where NPP_FK = '" + nppId + "' and SANPHAM_FK = '" + spId + "' and KBH_FK = '" + kbhId + "' and KHO_FK = '" + khoId + "' and SOLO = '" + solo + "' and NGAYHETHAN = '" + ngayhethan + "' ";
				if(!db.update(query))
				{
					return "1.8.Lỗi cập nhật ERP_DOISOLONPP " + query;
				}
				
				query = " update NHAPP_KHO_CHITIET set SOLUONG = SOLUONG - '" + dexuat.get(key) + "'  "
						+ "where NPP_FK = '" + nppId + "' and SANPHAM_FK = '" + spId + "' and KBH_FK = '" + kbhId + "' and KHO_FK = '" + khoId + "' and SOLO = '" + keyARR[0].trim() + "' and NGAYHETHAN = '" + keyARR[1].trim() + "' ";
				if(!db.update(query))
				{
					return "1.9.Lỗi cập nhật ERP_DOISOLONPP " + query;
				}
			}
		}
		
		return "";
		
	}
	
	public String ProcessBOOKED(String nppId, String spIds, dbutils db)
	{
		String msg = "";
		
		//B1. CẬP NHẬT LẠI BOOKED CHI TIẾT
		/*String query = "update kho set kho.BOOKED = isnull(Total_Booked, 0), kho.AVAILABLE = case when kho.SOLUONG >= isnull(Total_Booked, 0) then kho.SOLUONG - isnull(Total_Booked, 0) else 0 end "
					+ "from NHAPP_KHO_CHITIET kho left join ufn_Booked_ChiTiet ( " + nppId + " ) book "
					+ "		on kho.NPP_FK = book.npp_fk and kho.KHO_FK = book.kho_fk and kho.KBH_FK = book.kbh_fk and kho.SANPHAM_FK = book.sanpham_fk  "
					+ "			and kho.SOLO = book.SoLo and kho.NGAYHETHAN = book.NgayHetHan "
					+ "where kho.NPP_FK = '" + nppId + "' and kho.BOOKED != ISNULL(book.Total_Booked, 0) ";
		if(spIds.trim().length() > 0)
			query += " and kho.sanpham_fk in ( " + spIds + " ) ";
		
		System.out.println("---FIX BOOKED CT:: " + query);
		if(!db.update(query))
		{
			return "1.1.Lỗi cập nhật NHAPP_KHO_CHITIET " + query;
		}*/
				
		//B1.1. CẬP NHẬT LẠI BOOKED TỔNG
	 String	query = "update kho set kho.BOOKED = isnull(Total_Booked, 0), kho.AVAILABLE = case when kho.SOLUONG >= isnull(Total_Booked, 0) then kho.SOLUONG - isnull(Total_Booked, 0) else 0 end "
				+ " from NHAPP_KHO kho left join ufn_Booked_Total ( " + nppId + " ) book "
				+ "			on kho.NPP_FK = book.npp_fk and kho.KHO_FK = book.kho_fk and kho.KBH_FK = book.kbh_fk and kho.SANPHAM_FK = book.sanpham_fk "
				+ "where kho.NPP_FK = '" + nppId + "' and kho.BOOKED != ISNULL(book.Total_Booked, 0) ";
		if(spIds.trim().length() > 0)
			query += " and kho.sanpham_fk in ( " + spIds + " ) ";
		
		System.out.println("---FIX BOOKED TONG:: " + query);
		if(!db.update(query))
		{
			return "1.2.Lỗi cập nhật NHAPP_KHO " + query;
		}
		
		return msg;
	}
	
	public String ProcessBOOKED_DANGNHAP(String userId)
	{
		/*String msg="";
		dbutils db = new dbutils();
		try {
			db.getConnection().setAutoCommit(false);
			
			String query = "   select npp.pk_seq " + 
						   "   from nhanvien nv inner join nhaphanphoi npp on nv.convsitecode = sitecode   "+
						   "   where nv.pk_seq = '" + userId + "' and nv.trangthai = '1' and nv.PHANLOAI = 1 and npp.isKHACHHANG = '0'  ";
			ResultSet rs = db.get(query);
			String nppId = "";
			if( rs != null )
			{
				try 
				{
					if( rs.next() )
					{
						nppId = rs.getString("pk_seq");
					}
					rs.close();
				} 
				catch (Exception e) { }
			}
			
			//B1. CẬP NHẬT LẠI BOOKED CHI TIẾT
			query = "update kho set kho.BOOKED = isnull(Total_Booked, 0), kho.AVAILABLE = case when kho.SOLUONG >= isnull(Total_Booked, 0) then kho.SOLUONG - isnull(Total_Booked, 0) else 0 end "
					+ "from NHAPP_KHO_CHITIET kho left join ufn_Booked_ChiTiet ( " + nppId + " ) book "
					+ "		on kho.NPP_FK = book.npp_fk and kho.KHO_FK = book.kho_fk and kho.KBH_FK = book.kbh_fk and kho.SANPHAM_FK = book.sanpham_fk  "
					+ "			and kho.SOLO = book.SoLo and kho.NGAYHETHAN = book.NgayHetHan "
					+ "where kho.NPP_FK in (" + nppId + ") and kho.BOOKED != ISNULL(book.Total_Booked, 0)   or kho.SOLUONG-isnull(Total_Booked,0)<>kho.AVAILABLE ";
			
			System.out.println("---FIX BOOKED CT:: " + query);
			if(!db.update(query))
			{
				db.getConnection().rollback();
				db.getConnection().setAutoCommit(true);
				db.shutDown();
				return "1.1.Lỗi cập nhật NHAPP_KHO_CHITIET " + query;
			}
					
			//B1.1. CẬP NHẬT LẠI BOOKED TỔNG
			query = "update kho set kho.BOOKED = isnull(Total_Booked, 0), kho.AVAILABLE = case when kho.SOLUONG >= isnull(Total_Booked, 0) then kho.SOLUONG - isnull(Total_Booked, 0) else 0 end "
					+ " from NHAPP_KHO kho left join ufn_Booked_Total ( " + nppId + " ) book "
					+ "			on kho.NPP_FK = book.npp_fk and kho.KHO_FK = book.kho_fk and kho.KBH_FK = book.kbh_fk and kho.SANPHAM_FK = book.sanpham_fk "
					+ "where kho.NPP_FK in (" + nppId + ") and kho.BOOKED != ISNULL(book.Total_Booked, 0) ";
			
			System.out.println("---FIX BOOKED TONG:: " + query);
			if(!db.update(query))
			{
				db.getConnection().rollback();
				db.getConnection().setAutoCommit(true);
				db.shutDown();
				return "1.2.Lỗi cập nhật NHAPP_KHO " + query;
			}
			db.getConnection().commit();
			db.getConnection().setAutoCommit(true);
			db.shutDown();
		} 
		catch (SQLException e1)
		{
			try {
				db.getConnection().rollback();
				db.getConnection().setAutoCommit(true);
				db.shutDown();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			e1.printStackTrace();
		}
		*/
		return "";
	}
	
	public String ProcessBOOKED_DONHANG(String nppId, String spIds, String dhId, dbutils db, String sudung_db_ngoai)
	{
		String msg = "";
	
		/*try
		{
			if( sudung_db_ngoai.equals("1") )
			{
				db.getConnection().setAutoCommit(false);
			}
			
			//B1. CẬP NHẬT LẠI BOOKED CHI TIẾT
			String query = "update kho set kho.BOOKED = isnull(Total_Booked, 0), kho.AVAILABLE = case when kho.SOLUONG >= isnull(Total_Booked, 0) then kho.SOLUONG - isnull(Total_Booked, 0) else 0 end "
						+ "from NHAPP_KHO_CHITIET kho left join ufn_Booked_ChiTiet ( " + nppId + " ) book "
						+ "		on kho.NPP_FK = book.npp_fk and kho.KHO_FK = book.kho_fk and kho.KBH_FK = book.kbh_fk and kho.SANPHAM_FK = book.sanpham_fk  "
						+ "			and kho.SOLO = book.SoLo and kho.NGAYHETHAN = book.NgayHetHan "
						+ "where kho.NPP_FK = '" + nppId + "' and kho.BOOKED != ISNULL(book.Total_Booked, 0)  or kho.SOLUONG-isnull(Total_Booked,0)<>kho.AVAILABLE ";
			if(spIds.trim().length() > 0 )
				query += " and kho.sanpham_fk in ( " + spIds + " ) ";
			if( dhId.trim().length() > 0 )
				query += " and kho.sanpham_fk in ( select sanpham_fk from DONHANG_SANPHAM where DONHANG_FK = '" + dhId + "' union select b.PK_SEQ from DONHANG_CTKM_TRAKM a inner join SANPHAM b on a.SPMA = b.MA where a.DONHANGID = '" + dhId + "' ) ";
			
			System.out.println("---FIX BOOKED CT:: " + query);
			if(!db.update(query))
			{
				db.getConnection().rollback();
				db.getConnection().setAutoCommit(true);
				return "1.1.Lỗi cập nhật NHAPP_KHO_CHITIET " + query;
			}
					
			//B1.1. CẬP NHẬT LẠI BOOKED TỔNG
			query = "update kho set kho.BOOKED = isnull(Total_Booked, 0), kho.AVAILABLE = case when kho.SOLUONG >= isnull(Total_Booked, 0) then kho.SOLUONG - isnull(Total_Booked, 0) else 0 end "
					+ " from NHAPP_KHO kho left join ufn_Booked_Total ( " + nppId + " ) book "
					+ "			on kho.NPP_FK = book.npp_fk and kho.KHO_FK = book.kho_fk and kho.KBH_FK = book.kbh_fk and kho.SANPHAM_FK = book.sanpham_fk "
					+ "where kho.NPP_FK = '" + nppId + "' and kho.BOOKED != ISNULL(book.Total_Booked, 0) ";
			if(spIds.trim().length() > 0)
				query += " and kho.sanpham_fk in ( " + spIds + " ) ";
			if( dhId.trim().length() > 0 )
				query += " and kho.sanpham_fk in ( select sanpham_fk from DONHANG_SANPHAM where DONHANG_FK = '" + dhId + "' union select b.PK_SEQ from DONHANG_CTKM_TRAKM a inner join SANPHAM b on a.SPMA = b.MA where a.DONHANGID = '" + dhId + "' ) ";
			
			System.out.println("---FIX BOOKED TONG:: " + query);
			if(!db.update(query))
			{
				db.getConnection().rollback();
				db.getConnection().setAutoCommit(true);
				return "1.2.Lỗi cập nhật NHAPP_KHO " + query;
			}
			
			if( sudung_db_ngoai.equals("1") )
			{
				db.getConnection().commit();
				db.getConnection().setAutoCommit(true);
			}
		}
		catch(Exception ex)
		{
			ex.printStackTrace();
			
			try 
			{
				db.getConnection().rollback();
				db.getConnection().setAutoCommit(true);
			} 
			catch (SQLException e) { }
			
		}*/
		
		return msg;
	}
	
	
	private String getDateTime() 
	{
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date date = new Date();
        return dateFormat.format(date);	
	}
	
	
}
