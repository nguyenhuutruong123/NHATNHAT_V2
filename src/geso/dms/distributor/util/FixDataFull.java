package geso.dms.distributor.util;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Enumeration;
import java.util.Hashtable;

import geso.dms.distributor.db.sql.dbutils;

public class FixDataFull 
{
	public static void main(String[] arg) 
	{
		FixDataFull fixed = new FixDataFull();
		
		String nppId = "106162";
		String thang = "2";
		String nam = "2016";
		String tungay = "2016-02-01";
		String denngay = "2016-02-29";
		
		/*dbutils db = new dbutils();
		String query = "select top(1) thang, nam from KHOASOTHANG where NPP_FK = '" + nppId + "' order by nam desc, thang desc ";
		ResultSet rs = db.get(query);
		try 
		{
			if(rs.next())
			{
				thang = rs.getString("thang");
				nam = rs.getString("nam");
			}
			rs.close();
			
			//Hàm này sửa lại sẽ chạy từng tháng khóa sổ
			fixed.ProcessDATA(nppId, "", thang, nam, db, "0");
		} 
		catch (Exception e) 
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}*/
		
		//TIM NHUNG SP BI AM NXT CHI TIET
		dbutils db = new dbutils();
		//String query = " select distinct sanpham_fk, kbh_fk, kho_fk, solo, ngayhethan, NXT " + 
					   //" from ufn_XNT_Detail_FULL ( " + nppId + ", '" + tungay + "', '" + denngay + "' ) where NXT < 0  "; //and sanpham_fk = '100113' and kho_fk = '100000'
		
		String query = "delete NHAPP_KHO_CHITIET_DOISOLO_TEMP where NPP_FK = '" + nppId + "' and thang = '" + thang + "' and nam = '" + nam + "' ";
		db.update(query);
		
		query = "insert NHAPP_KHO_CHITIET_DOISOLO_TEMP( KHO_FK, NPP_FK, SANPHAM_FK, KBH_FK, SOLUONG, BOOKED, AVAILABLE, SOLO, NGAYHETHAN, THANG, NAM ) "
				+ " select KHO_FK, NPP_FK, SANPHAM_FK, KBH_FK, NXT as SOLUONG, 0 as BOOKED, NXT as AVAILABLE, SOLO, NGAYHETHAN, '" + thang + "', " + nam + " "
				+ " from ufn_XNT_Detail_FULL ( " + nppId + ", '" + tungay + "', '" + denngay + "' ) --and sanpham_fk=100195 ";
		System.out.println("::: CHEN KHO TONG: " + query);
		db.update(query);
		
		query = "delete NHAPP_KHO_DOISOLO_TEMP where NPP_FK = '" + nppId + "' and thang = '" + thang + "' and nam = '" + nam + "' ";
		db.update(query);
		
		query = "insert NHAPP_KHO_DOISOLO_TEMP( KHO_FK, NPP_FK, SANPHAM_FK, KBH_FK, SOLUONG, BOOKED, AVAILABLE, THANG, NAM ) "
				+ " select KHO_FK, NPP_FK, SANPHAM_FK, KBH_FK, NXT as SOLUONG, 0 as BOOKED, NXT as AVAILABLE, '" + thang + "', " + nam + " "
				+ " from ufn_XNT_Total_FULL ( '" + tungay + "', '" + denngay + "', " + nppId + ", null, null ) --and sanpham_fk=100195  ";
		System.out.println("::: CHEN KHO CT: " + query);
		db.update(query);
		
		query = "select KHO_FK, NPP_FK, SANPHAM_FK, KBH_FK, SOLO, NGAYHETHAN, SOLUONG from NHAPP_KHO_CHITIET_DOISOLO_TEMP where NPP_FK = '" + nppId + "' and SOLUONG < 0  ";
		System.out.println("++ LAY DS SAN PHAM AM:: " + query);
		ResultSet rs = db.get(query);
		try 
		{
			while(rs.next())
			{
				String spId = rs.getString("sanpham_fk");
				String kbhId = rs.getString("kbh_fk");
				String khoId = rs.getString("kho_fk");
				String solo = rs.getString("solo");
				String ngayhethan = rs.getString("ngayhethan");
				double soluongCANDOI = Math.abs( rs.getDouble("SOLUONG") );
				
				//Nếu kho tổng đủ hàng thì mới tiến hành đổi SỐ LÔ
				//query = " select soluong " + 
						//" from ufn_XNT_Total_FULL ( '" + tungay + "', '" + denngay + "', " + nppId + ", " + spId + ", " + khoId + ") where kbh_fk = '" + kbhId + "'  ";
				
				query = " select soluong " + 
						" from NHAPP_KHO_DOISOLO_TEMP where npp_fk = '" + nppId + "' and kho_fk = '" + khoId + "' and kbh_fk = '" + kbhId + "' and sanpham_fk = '" + spId + "' and thang = '" + thang + "' and nam = '" + nam + "' ";
				System.out.println("++ LAY DS SAN PHAM KHO TONG:: " + query);
				ResultSet rsSP = db.get(query);
				double soLUONG = 0;
				if(rsSP != null)
				{
					if(rsSP.next())
					{
						soLUONG = rsSP.getDouble("soluong");
					}
					rsSP.close();
				}
				
				if(soLUONG >= 0)
				{
					String msg = fixed.Process_Unit(db, thang, nam, tungay, denngay, nppId, khoId, kbhId, spId, solo, ngayhethan, soluongCANDOI, "0" );
					
					if(msg.trim().length() > 0)
					{
						query = "insert LOG_RUN_TONKHO( nppId, msg, tinhtrang, sanphamId  ) " + 
								" values ( '" + nppId + "', N'" + msg + "', 'NotOK-DoiSOLO', '" + spId + "' ) ";
						db.update(query);
					}
				}
			}
			rs.close();
		} 
		catch (Exception e) 
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		System.out.println("DONE...........");
	}

	private String Process_Unit(dbutils db, String thang, String nam, String tungay, String denngay, String nppId, String khoId, String kbhId, String spId, String solo, String ngayhethan, double soluongCANDOI, String sudung_db_trongTRANSACTION) 
	{
		String msg = "";
		try
		{
			if(sudung_db_trongTRANSACTION.equals("0"))
				db.getConnection().setAutoCommit(false);
			
			/*String query = "delete ERP_DOISOLONPP_SANPHAM where doisolo_fk = ( select pk_seq from ERP_DOISOLONPP where npp_fk = '" + nppId + "' and ngaydoi >= '" + tungay + "' and hethongTAO = '1' ) ";
			if(!db.update(query))
			{
				db.getConnection().rollback();
				return "1.Lỗi khi đổi số lô: " + query;
			}
			
			query = "delete ERP_DOISOLONPP where npp_fk = '" + nppId + "' and ngaydoi >= '" + tungay + "' and hethongTAO = '1' ";
			if(!db.update(query))
			{
				db.getConnection().rollback();
				return "2.Lỗi khi đổi số lô: " + query;
			}*/
			
			msg = this.DeXuatLoDoi(db, thang, nam, denngay, nppId, khoId, kbhId, spId, solo, ngayhethan, soluongCANDOI );
			if(msg.trim().length() > 0)
			{
				db.getConnection().rollback();
				return "3.Lỗi khi đổi số lô: " + msg;
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
	
	private String DeXuatLoDoi(dbutils db, String thang, String nam, String denngay, String nppId, String khoId, String kbhId, String spId, String solo, String ngayhethan, double soluongCANDOI ) 
	{
		//Vì kho tổng đã bằn kho CT, và cập nhật được kho tổng, nên do đó hàm này lúc nào cũng đề xuất được
		Hashtable<String, String> dexuat = new Hashtable<String, String>();
		
		String query = "select SOLO, NGAYHETHAN, SOLUONG - BOOKED as avai "
						+ "from NHAPP_KHO_CHITIET_DOISOLO_TEMP kho "
						+ "where thang = '" + thang + "' and nam = '" + nam + "' and kho.NPP_FK = '" + nppId + "' and kho.SANPHAM_FK = '" + spId + "' and kho.KBH_FK = '" + kbhId + "' and kho.KHO_FK = '" + khoId + "'  "
						+ "			and ( ( SOLO = '" + solo + "' and NGAYHETHAN != '" + ngayhethan + "' ) or ( SOLO != '" + solo + "' ) ) and ( SOLUONG - BOOKED ) > 0 "
						+ "order by kho.AVAILABLE desc";
		
		/*String query =  " select distinct sanpham_fk, kbh_fk, kho_fk, solo, ngayhethan, NXT " + 
				   		" from ufn_XNT_Detail_FULL ( " + nppId + ", '" + tungay + "', '" + denngay + "' ) " + 
				   		" where NXT > 0 and ( ( SOLO = '" + solo + "' and NGAYHETHAN != '" + ngayhethan + "' ) or ( SOLO != '" + solo + "' ) ) " + 
				   		"		 and SANPHAM_FK = '" + spId + "' and KBH_FK = '" + kbhId + "' and kho_fk = '" + khoId + "' order by NXT desc ";*/
		
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
					
					double avai = Math.abs( rs.getDouble("avai") );
					System.out.println("------ 0.AVAIIIIII : " + avai );
					
					totalDADEXUAT += avai;
					if(totalDADEXUAT >= soluongCANDOI )
					{
						double slgDEXUAT = avai - ( totalDADEXUAT - soluongCANDOI );
						dexuat.put(soloDEXUAT + "__" + ngayhethanDEXUAT, Double.toString(slgDEXUAT));
						
						System.out.println("------ 1.DE XUAT CHO NAY : " + slgDEXUAT );
						break;
					}
					else
					{
						System.out.println("------ 2.DE XUAT CHO NAY: " + Double.toString(avai) );
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
					+ "values ( '" + khoId + "', '" + kbhId + "', '" + nppId + "', '1', '" + denngay + "', '100002', '100002', '" + this.getDateTime() + "', '" + this.getDateTime() + "', '1', '" + spId + "' ) ";
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
				
				query = " insert ERP_DOISOLONPP_SANPHAM( doisolo_fk, sanpham_fk, solo, soloOLD, soluong, ngayhethan, NgayHetHanOLD ) "
						+ "select SCOPE_IDENTITY(), '" + spId + "', '" + solo + "', '" + keyARR[0].trim() + "', '" + dexuat.get(key) + "', '" + ngayhethan + "', '" + keyARR[1].trim() + "'  ";
				System.out.println("====== CHEN DOI SO LO - SP:: " + query );
				if(!db.update(query))
				{
					return "1.7.Lỗi cập nhật ERP_DOISOLONPP " + query;
				}
				
				//Tăng giảm kho
				query = " update NHAPP_KHO_CHITIET_DOISOLO_TEMP set SOLUONG = SOLUONG + '" + dexuat.get(key) + "'  "
						+ "where NPP_FK = '" + nppId + "' and SANPHAM_FK = '" + spId + "' and KBH_FK = '" + kbhId + "' and KHO_FK = '" + khoId + "' and SOLO = '" + solo + "' and NGAYHETHAN = '" + ngayhethan + "' and thang = '" + thang + "' and nam = '" + nam + "' ";
				if(!db.update(query))
				{
					return "1.8.Lỗi cập nhật NHAPP_KHO_DOISOLO_CHITIET_TEMP " + query;
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
	
	private String getDateTime() 
	{
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date date = new Date();
        return dateFormat.format(date);	
	}
	
	
}
