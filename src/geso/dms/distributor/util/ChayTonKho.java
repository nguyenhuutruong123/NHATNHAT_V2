package geso.dms.distributor.util;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Enumeration;
import java.util.Hashtable;

import geso.dms.distributor.db.sql.dbutils;

public class ChayTonKho {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		ChayTonKho fix = new ChayTonKho();
		dbutils db=new dbutils();
		String sql=" select distinct a.PK_SEQ from NHAPHANPHOI a inner join KHOASOTHANG  b on a.PK_SEQ=b.NPP_FK where b.thangks=2 and b.nam=2016 and a.pk_seq in (106215,106219,106222,106223) ";
		ResultSet rs=db.get(sql);
		try {
			while(rs.next())
			{
				boolean flag=fix.kiemtraXNT(rs.getString("pk_seq"), db);
				if(flag)
				{
					db.getConnection().setAutoCommit(false);
					String kq=fix.capnhat_XNT(rs.getString("pk_seq"), db);
					System.out.println(kq);
					if(kq.length()>0)
					{
						db.getConnection().rollback();
						db.getConnection().setAutoCommit(true);
						db.update("insert LOG_RUN_TONKHO(msg) values ('KHONG THE CAP NHAT NPP : "+rs.getString("pk_seq")+"')");
					}
					else
					{
						db.getConnection().commit();
						db.getConnection().setAutoCommit(true);
					}
					
					
				}
				System.out.println("chay xong ràu" +rs.getString("pk_seq"));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	
		
	}
	
	private boolean kiemtraXNT(String npp,dbutils db)
	{
		
		String sql="select * from ufn_XNT_Total("+npp+") where xnt < 0 ";
		ResultSet rs = db.get(sql);
		try {
			
			while(rs.next())
			{
				sql=" insert into LOG_RUN_TONKHO (nppId,msg,thoidiem,tinhtrang,sanphamId) "+
					" values ( "+npp+",'xnt am',getdate(),'false',"+rs.getString("sanpham_fk")+" ) ";	
				if(!db.update(sql)){
					System.out.println(sql);
					return false;
				}
				
				return false;
				
			}
			rs.close();
			
			
			sql=	"	select  a.* from( "+
					"	select a.kho_fk,a.kbh_fk,a.sanpham_fk,a.npp_fk, sum(a.XNT) as soluongton "+
					"	from ufn_XNT_ChiTiet_NGAYNHAPKHO("+npp+") a  "+
					"	group by a.kho_fk,a.kbh_fk,a.sanpham_fk,a.npp_fk  "+
					"	) a   "+
					"	left join  ufn_XNT_Total("+npp+") b on a.npp_fk=b.npp_fk and a.kbh_fk=b.kbh_fk and a.kho_fk=b.kho_fk and a.sanpham_fk=b.sanpham_fk  "+
					"		where ROUND(a.soluongton,1) <> ROUND(b.XNT,1) ";
			
			rs=db.get(sql);
			if(rs.next())
			{
				return false;
			}
			rs.close();
			
		} catch (Exception e) {
			e.printStackTrace();
		
			return false;
		}
		
		return true;
		
	}
	private String capnhat_XNT(String npp,dbutils db)
	{
		
		//B1.1. CẬP NHẬT LẠI BOOKED
			String	query = "update kho set kho.BOOKED = round(isnull(Total_Booked, 0),1) "
						+ " from NHAPP_KHO kho left join ufn_Booked_Total ( " + npp + " ) book "
						+ "			on kho.NPP_FK = book.npp_fk and kho.KHO_FK = book.kho_fk and kho.KBH_FK = book.kbh_fk and kho.SANPHAM_FK = book.sanpham_fk "
						+ "where kho.NPP_FK = '" + npp + "' and round( kho.BOOKED ,1)!= round(ISNULL(book.Total_Booked, 0),1) ";
			
			if(!db.update(query))
			{
				return query;
				
			}
		//B2 cap nhat lai book chitiet, tam thoi bo di
			
	 	/*query = "	update a set a.BOOKED=round(ISNULL(b.booked_sql,0),1) from NHAPP_KHO_CHITIET a  \n"+
					" left join ufn_Booked_ChiTiet_nhapkho("+npp+",null) b \n"+
					" on a.NPP_FK=b.npp_fk and a.SANPHAM_FK=b.sanpham_fk and a.KBH_FK=b.kbh_fk \n"+
					" and a.KHO_FK=b.kho_fk and a.SOLO=b.solo and a.NGAYHETHAN=b.ngayhethan and a.NGAYNHAPKHO=b.ngaynhapkho \n"+
					" where a.NPP_FK="+npp+" and round(ISNULL(b.booked_sql,0),1)<>round(a.BOOKED,1) \n";
	 	if(!db.update(query))
		{
			System.out.println("loi cap nhat book chi tiet");
		}
	 	*/
	 	//B3 cap nhat xnt tong 
	 	
	 	query=" select a.NPP_FK,a.KBH_FK,a.KHO_FK,a.SANPHAM_FK,ROUND(ISNULL(a.SOLUONG,0),1) SOLUONG,ROUND(ISNULL(b.XNT ,0),1) xnt " +
	 		  " ,ROUND(ISNULL(a.booked ,0),1) as booked from NHAPP_KHO a left join ufn_XNT_Total("+npp+") b \n"+
			  "		 on a.NPP_FK=b.npp_fk and a.SANPHAM_FK=b.sanpham_fk and a.KBH_FK=b.kbh_fk \n"+
			  "		 and a.KHO_FK=b.kho_fk \n"+
			  "		 where a.NPP_FK="+npp+" and ISNULL(b.XNT,0)<> a.SOLUONG \n"+
			  "		 and ROUND(ISNULL(a.SOLUONG,0),1) <>  ROUND(ISNULL(b.XNT ,0),1)";
	 	ResultSet rs=db.get(query);
	 	try {
	 		 
					while(rs.next())
					{
						String npp_fk=rs.getString("npp_fk");
						String kbh_fk=rs.getString("kbh_fk");
						String kho_fk=rs.getString("kho_fk");
						String sanpham_fk=rs.getString("sanpham_fk");
						double soluong=rs.getDouble("soluong");
						double xnt=rs.getDouble("xnt");
						double booked=rs.getDouble("booked");
						
						if(xnt < booked)
						{
							String sql="  insert into LOG_RUN_TONKHO (nppId,msg,thoidiem,tinhtrang,sanphamId) "+
									"values ( "+npp+",'booked vuot qua so luong',getdate(),'false',"+sanpham_fk+" ) ";
							db.update(sql);
							sql="update nhapp_kho set soluong="+xnt+",AVAILABLE=0 where npp_fk='"+npp_fk+"' and kbh_fk='"+kbh_fk+"' and kho_fk='"+kho_fk+"' and sanpham_fk='"+sanpham_fk+"' ";
							if(db.updateReturnInt(sql)<=0)
							{
								return sql;
							}
						}
						else
						{
							String	sql="update nhapp_kho set soluong="+xnt+",AVAILABLE="+xnt+" - "+booked+" where npp_fk='"+npp_fk+"' and kbh_fk='"+kbh_fk+"' and kho_fk='"+kho_fk+"' and sanpham_fk='"+sanpham_fk+"' ";
							if(db.updateReturnInt(sql)<=0)
							{
								return sql;
							}
					
						}
					}
					rs.close();
			 
			
		} catch ( Exception e) {
			
			e.printStackTrace();
		}
	 	
		 
	 	//B4 cap nhat xnt chi tiet
	 	query=  " select  isnull (a.kho_fk,kho.kho_fk) kho_fk,isnull(a.kbh_fk,kho.KBH_FK) kbh_fk,isnull(a.sanpham_fk,kho.SANPHAM_FK) as sanpham_fk "+
	 			" ,isnull(a.npp_fk,kho.npp_fk) npp_fk,isnull(a.SoLo,kho.solo) solo,isnull(a.NgayHetHan,kho.ngayhethan) ngayhethan "+
	 			" ,isnull(a.NGAYNHAPKHO,kho.ngaynhapkho)ngaynhapkho,round(isnull(a.XNT,0),1) XNT,"+
	 			" round(isnull(kho.SOLUONG,0),1) as soluongkho,  round(isnull(kho.BOOKED,0),0) as BOOKED,round(isnull(kho.AVAILABLE,0),1) as AVAILABLE    "+ 
				" from ufn_XNT_ChiTiet_NGAYNHAPKHO("+npp+") a   "+ 
				" full outer  join NHAPP_KHO_CHITIET kho on kho.NPP_FK=a.npp_fk and kho.KBH_FK=a.kbh_fk  "+ 
				" and kho.SANPHAM_FK=a.sanpham_fk and kho.KHO_FK=a.kho_fk and kho.SOLO=a.SoLo  "+ 
				" and a.NGAYNHAPKHO=kho.ngaynhapkho and a.NgayHetHan=kho.NGAYHETHAN "+ 
				" where (ROUND(ISNULL(kho.SOLUONG,0),1) <>  ROUND(ISNULL(a.XNT ,0),1) or a.XNT<0)"+ 
				"  and isnull(kho.NPP_FK,a.npp_fk)= "+npp+" order by   round(isnull(a.XNT,0),1)  desc ";
	 	System.out.println("query "+query);
	 		rs=db.get(query);
		try {
			 
				while(rs.next())
				{
					String npp_fk=rs.getString("npp_fk");
					String kbh_fk=rs.getString("kbh_fk");
					String kho_fk=rs.getString("kho_fk");
					String sanpham_fk=rs.getString("sanpham_fk");
					String solo=rs.getString("solo");
					String ngayhethan=rs.getString("ngayhethan");
					String ngaynhapkho=rs.getString("ngaynhapkho");
					double soluong=rs.getDouble("soluongkho");
					double booked=rs.getDouble("BOOKED");
					double XNT=rs.getDouble("XNT");
					
					
					if(XNT < 0)
					{
						query=" select booked  from  nhapp_kho_chitiet where npp_fk="+npp_fk+" and kbh_fk="+kbh_fk+" and kho_fk="+kho_fk+"  " +
								" and sanpham_fk="+sanpham_fk+" and solo='"+solo+"' and ngayhethan='"+ngayhethan+"' and ngaynhapkho='"+ngaynhapkho+"' ";
						
						ResultSet rscheck=db.get(query);
						
						if(rscheck.next()){
						
								query="update nhapp_kho_chitiet set soluong="+XNT+"  where npp_fk="+npp_fk+" and kbh_fk="+kbh_fk+" and kho_fk="+kho_fk+" and sanpham_fk="+sanpham_fk+" and solo='"+solo+"' and ngayhethan='"+ngayhethan+"' and ngaynhapkho='"+ngaynhapkho+"' ";
								
								if(db.updateReturnInt(query)<=0)
								{
									System.out.println("loi  "+query);
									return query;
								}
						}else{
								query=" insert into NHAPP_KHO_CHITIET (KHO_FK,NPP_FK,SANPHAM_FK,KBH_FK,SOLO,NGAYHETHAN,soluong,BOOKED,AVAILABLE,ngaynhapkho,tonthoidiem) values " +
										"("+kho_fk+","+npp_fk+","+sanpham_fk+","+kbh_fk+",'"+solo+"','"+ngayhethan+"',"+XNT+",0,0,'"+ngaynhapkho+"',"+XNT+")";
								if(db.updateReturnInt(query)<=0)
								{
									return query;
								}
						 }
						
						String msg=DeXuatLoDoi(db, npp_fk, kho_fk, kbh_fk, sanpham_fk, solo, ngayhethan,ngaynhapkho, Math.abs(XNT)+booked );
						if(msg.length()>0)
							return msg;
					}
					else
					{   
						query=" select booked  from  nhapp_kho_chitiet where npp_fk="+npp_fk+" and kbh_fk="+kbh_fk+" and kho_fk="+kho_fk+"  " +
								" and sanpham_fk="+sanpham_fk+" and solo='"+solo+"' and ngayhethan='"+ngayhethan+"' and ngaynhapkho='"+ngaynhapkho+"' ";
						
						ResultSet rscheck=db.get(query);
						
						if(rscheck.next()){
							
							double booked_tmp=rscheck.getDouble("BOOKED");
							
							query=" update nhapp_kho_chitiet set soluong="+XNT+" ,   AVAILABLE= ( CASE WHEN  "+XNT+" <BOOKED THEN 0 ELSE "+XNT+" - booked END )  " +
									" where npp_fk="+npp_fk+" and kbh_fk="+kbh_fk+" and kho_fk="+kho_fk+" and sanpham_fk="+sanpham_fk+" and solo='"+solo+"' and ngayhethan='"+ngayhethan+"' and ngaynhapkho='"+ngaynhapkho+"' ";
							if(db.updateReturnInt(query)<=0)
							{
								 
									 
								return query;
							}
							
						}else{
							query=" insert into NHAPP_KHO_CHITIET (KHO_FK,NPP_FK,SANPHAM_FK,KBH_FK,SOLO,NGAYHETHAN,soluong,BOOKED,AVAILABLE,ngaynhapkho,tonthoidiem) values " +
									"("+kho_fk+","+npp_fk+","+sanpham_fk+","+kbh_fk+",'"+solo+"','"+ngayhethan+"',"+XNT+",0,"+XNT+",'"+ngaynhapkho+"',"+XNT+")";
							if(db.updateReturnInt(query)<=0)
							{
								return query;
							}
							
						}
					}
					
				}
				rs.close();
			 
			
		} catch ( Exception e) {
			// TODO Auto-generated catch block
			
			e.printStackTrace();
			return " loi"+e.getMessage();
		}
	 	return "";
			
	}
	
	
	private String DeXuatLoDoi(dbutils db, String nppId, String khoId, String kbhId, String spId, String solo, String ngayhethan,String ngaynhapkho, double soluongCANDOI ) 
	{
		//Vì kho tổng đã bằn kho CT, và cập nhật được kho tổng, nên do đó hàm này lúc nào cũng đề xuất được
		Hashtable<String, String> dexuat = new Hashtable<String, String>();
		
		String query = 	  " select ngaynhapkho,SOLO, NGAYHETHAN, SOLUONG - BOOKED as avai "
						+ " from NHAPP_KHO_CHITIET kho "
						+ " where kho.NPP_FK = '" + nppId + "' and kho.SANPHAM_FK = '" + spId + "' and kho.KBH_FK = '" + kbhId + "' and kho.KHO_FK = '" + khoId + "'   "
						+ "	  and ( SOLUONG - BOOKED ) > 0  and SOLO+ngaynhapkho+NGAYHETHAN <>'"+solo+ngaynhapkho+ngayhethan+"' "
						+ " order by kho.AVAILABLE desc";
		System.out.println("DE XUAT DOI LO:: " + query);
		ResultSet rs = db.get(query);
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
					
					
					String ngaynhapkhoDEXUAT = rs.getString("ngaynhapkho");
					if(ngaynhapkhoDEXUAT.trim().length() <= 0)
						ngaynhapkhoDEXUAT = " ";
					
					
					double avai = rs.getDouble("avai");
					
					totalDADEXUAT += avai;
					if(totalDADEXUAT >= soluongCANDOI )
					{
						double slgDEXUAT = avai - ( totalDADEXUAT - soluongCANDOI );
						dexuat.put(soloDEXUAT + "__" + ngayhethanDEXUAT +"__" + ngaynhapkhoDEXUAT, Double.toString(slgDEXUAT));
						
						break;
					}
					else
					{
						dexuat.put(soloDEXUAT + "__" + ngayhethanDEXUAT +"__" + ngaynhapkhoDEXUAT, Double.toString(avai));
					}
				}
				rs.close();
			} 
			catch (Exception e) {
				
				e.printStackTrace();
				return "1.5. Lỗi đổi số lô: " + e.getMessage();
			}
		 
		
		if(dexuat.size() > 0)
		{
			//Chèn đổi số lô
			query =   " insert ERP_DOISOLONPP( kho_fk, kbh_fk, npp_fk, trangthai, ngaydoi, nguoitao, nguoisua, ngaytao, ngaysua, hethongTAO, sanpham_fk ) "
					+ " values ( '" + khoId + "', '" + kbhId + "', '" + nppId + "', '1', '" + this.getDateTime() + "', '100002', '100002', '" + this.getDateTime() + "', '" + this.getDateTime() + "', '1', '" + spId + "' ) ";
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
				
				query = " insert ERP_DOISOLONPP_SANPHAM( doisolo_fk, sanpham_fk, solo, soloOLD, soluong, ngayhethanOLD, NgayHetHan,ngaynhapkho,ngaynhapkhoOLD ) "
						+ "select SCOPE_IDENTITY(), '" + spId + "', '" + solo + "', '" + keyARR[0].trim() + "', '" + dexuat.get(key) + "', '" + keyARR[1].trim() + "', '" + ngayhethan + "','"+ ngaynhapkho +"','"+  keyARR[2].trim() +"'  ";
				System.out.println("====== CHEN DOI SO LO - SP:: " + query );
				if(db.updateReturnInt(query)!=1)
				{
					return "1.7.Lỗi cập nhật ERP_DOISOLONPP " + query;
				}
				
				//Tăng giảm kho
				query = " update NHAPP_KHO_CHITIET set SOLUONG = SOLUONG + '" + dexuat.get(key) + "'  "
						+ "where NPP_FK = '" + nppId + "' and SANPHAM_FK = '" + spId + "' and KBH_FK = '" + kbhId + "' and KHO_FK = '" + khoId + "' and SOLO = '" + solo + "' and NGAYHETHAN = '" + ngayhethan + "' and ngaynhapkho='"+ ngaynhapkho +"' ";
				if(db.updateReturnInt(query)!=1)
				{
					return "1.8.Lỗi cập nhật ERP_DOISOLONPP " + query;
				}
				
				query = " update NHAPP_KHO_CHITIET set SOLUONG = SOLUONG - '" + dexuat.get(key) + "'  "
						+ "where NPP_FK = '" + nppId + "' and SANPHAM_FK = '" + spId + "' and KBH_FK = '" + kbhId + "' and KHO_FK = '" + khoId + "' and SOLO = '" + keyARR[0].trim() + "' and NGAYHETHAN = '" + keyARR[1].trim() + "' and ngaynhapkho='"+ keyARR[2].trim() +"' ";
				if(db.updateReturnInt(query)!=1)
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
