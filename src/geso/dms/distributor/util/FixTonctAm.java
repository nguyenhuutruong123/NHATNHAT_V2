package geso.dms.distributor.util;

import java.sql.ResultSet;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Enumeration;
import java.util.Hashtable;

import geso.dms.distributor.db.sql.dbutils;

public class FixTonctAm
{
	public static void main(String[] arg) 
	{
		FixTonctAm fixed = new FixTonctAm();
		String sql="";
	
		/*fixed.ProcessDATA("100002", "100110", new dbutils(), "0");
		System.out.println("DONE...........");*/
	}
	
	private void insert_f_tempt(String npp,String tungay,String denngay, dbutils db)
	{
		String query="insert into TONKHOTHANG_TEMP( kho_fk,npp_fk,kbh_fk,sanpham_fk,thang,Nam,BOOKED,available,dongia)"+
				"select kho_fk,npp_fk,kbh_fk,sanpham_fk, month('"+tungay+"') thang,year('"+tungay+"') Nam,xnt,null,null,null from ufn_XNT_tungay_denngay_Total(106171,'2015-03-01','2015-05-12')";
		
		
	}
	private void insert_tempt(String npp,String tungay,String denngay, dbutils db)
	{
		String query="insert into TONKHOTHANG_TEMP( kho_fk,npp_fk,kbh_fk,sanpham_fk,thang,Nam,BOOKED,available,dongia)"+
				"select kho_fk,npp_fk,kbh_fk,sanpham_fk, month('"+tungay+"') thang,year('"+tungay+"') Nam,xnt,null,null,null from ufn_XNT_tungay_denngay_Total(106171,'2015-03-01','2015-05-12')";
		
		if(!db.update(query))
			{
				System.out.println(":::::::::::::::::: loi insert "+query);
			}
		 query="insert into TONKHOTHANG_TEMP( kho_fk,npp_fk,kbh_fk,sanpham_fk,thang,Nam,SOLO,NGAYHETHAN,SOLUONG,,BOOKED,available)"+
				"select kho_fk,npp_fk,kbh_fk,sanpham_fk, month('"+tungay+"') thang,year('"+tungay+"') Nam,SOLO,NGAYHETHAN,XNT,null,NULL from ufn_XNT_Detail(106171,'2015-03-01','2015-05-12')";
			 if(!db.update(query))
			{
				System.out.println(":::::::::::::::::: loi insert "+query);
			}
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
	
	private String getDateTime() 
	{
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date date = new Date();
        return dateFormat.format(date);	
	}
	
}