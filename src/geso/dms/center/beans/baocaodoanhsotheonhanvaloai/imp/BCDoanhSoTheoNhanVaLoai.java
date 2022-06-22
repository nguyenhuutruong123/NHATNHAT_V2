package geso.dms.center.beans.baocaodoanhsotheonhanvaloai.imp;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Enumeration;
import java.util.Hashtable;

import com.cete.dynamicpdf.pageelements.List;

import geso.dms.center.beans.baocaodoanhsotheonhanvaloai.IBCDoanhSoTheoNhanVaLoai;
import geso.dms.center.db.sql.dbutils;
import geso.dms.center.util.Phan_Trang;

public class BCDoanhSoTheoNhanVaLoai extends Phan_Trang implements IBCDoanhSoTheoNhanVaLoai{
	private String thangTu;
	private String thangDen;
	private String nam;
	private ResultSet ASM;
	private ResultSet RSM;
	private String message;
	private dbutils db;
	private ResultSet rsKenh;
	private ResultSet rsBrand;
	private String userID;
	private String[] maTen;
	private long[][] doanhSo;
	private String idKenh;
	private String idBrand;
	private String idASM;
	private String idRSM;
	private String[] mangThang;
	private int sodong;
	private int socot;
	private String namDen;
	
	
	
	public String getNamDen() {
		return namDen;
	}
	public void setNamDen(String namDen) {
		this.namDen = namDen;
	}
	public int getSodong() {
		return sodong;
	}
	public void setSodong(int sodong) {
		this.sodong = sodong;
	}
	public int getSocot() {
		return socot;
	}
	public void setSocot(int socot) {
		this.socot = socot;
	}
	public String[] getMangThang() {
		return mangThang;
	}
	public void setMangThang(String[] mangThang) {
		this.mangThang = mangThang;
	}
	public String getThangTu() {
		return thangTu;
	}
	public void setThangTu(String thangTu) {
		this.thangTu = thangTu;
	}
	public String getThangDen() {
		return thangDen;
	}
	public void setThangDen(String thangDen) {
		this.thangDen = thangDen;
	}
	public String getNam() {
		return nam;
	}
	public void setNam(String nam) {
		this.nam = nam;
	}
	public String getIdKenh() {
		return idKenh;
	}
	public void setIdKenh(String idKenh) {
		this.idKenh = idKenh;
	}
	public String getIdBrand() {
		return idBrand;
	}
	public void setIdBrand(String idBrand) {
		this.idBrand = idBrand;
	}
	public String getIdASM() {
		return idASM;
	}
	public void setIdASM(String idASM) {
		this.idASM = idASM;
	}
	public String getIdRSM() {
		return idRSM;
	}
	public void setIdRSM(String idRSM) {
		this.idRSM = idRSM;
	}
	public String[] getMaTen() {
		return maTen;
	}
	public void setMaTen(String[] maTen) {
		this.maTen = maTen;
	}
	public long[][] getDoanhSo() {
		return doanhSo;
	}
	public void setDoanhSo(long[][] doanhSo) {
		this.doanhSo = doanhSo;
	}
	
	public ResultSet getASM() {
		return ASM;
	}
	public void setASM(ResultSet aSM) {
		ASM = aSM;
	}
	public ResultSet getRSM() {
		return RSM;
	}
	public void setRSM(ResultSet rSM) {
		RSM = rSM;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public dbutils getDb() {
		return db;
	}
	public void setDb(dbutils db) {
		this.db = db;
	}
	public ResultSet getRsKenh() {
		return rsKenh;
	}
	public void setRsKenh(ResultSet rsKenh) {
		this.rsKenh = rsKenh;
	}
	public ResultSet getRsBrand() {
		return rsBrand;
	}
	public void setRsBrand(ResultSet rsBrand) {
		this.rsBrand = rsBrand;
	}
	public String getUserID() {
		return userID;
	}
	public void setUserID(String userID) {
		this.userID = userID;
	}
	
	
	public BCDoanhSoTheoNhanVaLoai(String ngayTu, String ngayDen,
			ResultSet aSM, ResultSet rSM, String message, dbutils db,
			ResultSet rsKenh, ResultSet rsBrand, String userID, String[] maTen,
			long[][] doanhSo) {
		super();
		
		ASM = aSM;
		RSM = rSM;
		this.message = message;
		this.db = db;
		this.rsKenh = rsKenh;
		this.rsBrand = rsBrand;
		this.userID = userID;
		this.maTen = maTen;
		this.doanhSo = doanhSo;
	}
	public BCDoanhSoTheoNhanVaLoai() {
		super();
		this.thangDen = "";
		this.thangTu = "";
		this.nam = "";
		this.idASM = "";
		this.idBrand = "";
		this.idKenh = "";
		this.idRSM = "";
		this.message = "";
		this.db = new dbutils();
		this.maTen = new String[100];
		this.doanhSo = new long[100][100];
		this.userID = "";
		this.socot = 0;
		this.sodong = 0;
		this.namDen ="";
		this.mangThang = new String[36];
	}
	public void dbClose(){
		this.db.shutDown();
	}
	
	public void createKenh(){
		String query = "SELECT  PK_SEQ AS MAKENH,Ma, " +
		" TEN AS TENKENH " +
		"FROM nhomhang ORDER BY PK_SEQ";
		this.rsKenh = db.get(query);

	}
	public void createBrand(){
		String query = "select NHANHANG.PK_SEQ AS MANHANHANG, NHANHANG.TEN AS TENNHANHANG " +
				"FROM NHANHANG";
		this.rsBrand = db.get(query);
	}
	public void createASM(){
		String query = "Select GIAMSATBANHANG.PK_SEQ AS MAASM, " +
				"GIAMSATBANHANG.TEN AS TENASM " +
				"FROM GIAMSATBANHANG WHERE TRANGTHAI =1";
		if(this.idRSM.length() > 0)
		{
			query+= " and   bm_fk in( "+this.idRSM+") ";
		}
		this.ASM = db.get(query);
	}
	public void createRSM(){
		String query = "SELECT BM.PK_SEQ AS MARSM, BM.TEN AS TENRSM " +
				"FROM BM WHERE TRANGTHAI = 1 ";
		this.RSM = db.get(query);
	}
	public String chuoiNam(){
		String chuoi = "";
		int nam = Integer.parseInt(this.nam);
		int namDen = Integer.parseInt(this.namDen);
		for(int i = nam; i< namDen; i++){
			
			chuoi += "\n select 1 as thang," + i + " as nam union "
			 + "\n  select 2 as thang," + i + " as nam union "
			 + "\n  select 3 as thang," + i + " as nam union "
			 + "\n  select 4 as thang," + i + " as nam union "
			 + "\n select 5 as thang," + i + " as nam union "
			 + "\n select 6 as thang," + i + " as nam union "
			 + "\n  select 7 as thang," + i + " as nam union "
			 + "\n select 8 as thang," + i + " as nam union "
			 + "\n select 9 as thang," + i + " as nam union "
			 + "\n select 10 as thang," + i + " as nam union "
			 + "\n select 11 as thang," + i + " as nam union "
			 + "\n select 12 as thang," + i + " as nam union ";
		}
		//trường hợp i = năm đến
		chuoi += "\n select 1 as thang," + this.namDen + " as nam union "
		 + "\n  select 2 as thang," + this.namDen + " as nam union "
		 + "\n  select 3 as thang," + this.namDen + " as nam union "
		 + "\n  select 4 as thang," + this.namDen + " as nam union "
		 + "\n select 5 as thang," + this.namDen + " as nam union "
		 + "\n select 6 as thang," + this.namDen + " as nam union "
		 + "\n  select 7 as thang," + this.namDen + " as nam union "
		 + "\n select 8 as thang," + this.namDen + " as nam union "
		 + "\n select 9 as thang," + this.namDen + " as nam union "
		 + "\n select 10 as thang," + this.namDen + " as nam union "
		 + "\n select 11 as thang," + this.namDen + " as nam union "
		 + "\n select 12 as thang," + this.namDen + " as nam  ";
		
		return chuoi;
	}
	public void innit_no_kenh_no_nhan(){
		
		//xet điều kiện
		String query = "";
		
		String query_dieukien = "";

		String query_Groupby = "";		
		//nếu không chọn gì thì biểu diễn theo nhưng kênh đã có
				
		this.socot =0;
		this.sodong =0;
		if(this.idBrand.trim().equals("") && this.idKenh.trim().equals("")){
			this.mangThang = new String[(Integer.parseInt(this.namDen)- Integer.parseInt(this.nam)+1)*12];
			query = "select d.thang, d.nam, isnull(dh.doanhso,0) doanhso from "
				 + "\n ("
				 + chuoiNam()
				 + "\n  ) d left join "
				 + "\n ( select  ROUND( SUM(dhsp.giamua*dhsp.soluong)/1000,0) as doanhso, "
				 + "\n		  kbh.PK_SEQ as makenh, kbh.TEN as tenkenh, MONTH(dh.NGAYNHAP) as thang, YEAR(NGAYNHAP) AS nam "
				 + "\n from DONHANG dh inner join KENHBANHANG kbh on kbh.PK_SEQ = dh.KBH_FK "
				 + "\n				inner join DONHANG_SANPHAM dhsp on dh.PK_SEQ = dhsp.DONHANG_FK "
				 + "\n				inner join SANPHAM sp ON sp.PK_SEQ = dhsp.SANPHAM_FK 			"
				 + "\n				inner join KHACHHANG kh on kh.PK_SEQ = dh.KHACHHANG_FK "
				 + "\n				inner join DIABAN db on kh.DIABAN_FK =  db.PK_SEQ "
				 + "\n				inner join DAIDIENKINHDOANH ddkd on ddkd.diaban_fk = db.PK_SEQ "
				 + "\n				inner join DDKD_GSBH ddgs on ddgs.DDKD_FK = ddkd.PK_SEQ "
				 + "\n				inner join GIAMSATBANHANG gsbh on gsbh.PK_SEQ = ddgs.GSBH_FK  "
				 + "\n where dh.TRANGTHAI  <> '2' and dh.KHACHHANG_FK IS NOT NULL "
				 + "\n		and (YEAR(NGAYNHAP) > "+ this.nam +" AND YEAR(NGAYNHAP) < "+ this.namDen +" "
				 + "\n OR YEAR(NGAYNHAP) = "+ this.nam +" AND MONTH(NGAYNHAP) >= "+this.thangTu+" AND YEAR(NGAYNHAP) < "+ this.namDen +" "
				 + "\n OR YEAR(NGAYNHAP) > "+ this.nam +" AND YEAR(NGAYNHAP) = "+ this.namDen +" AND MONTH(NGAYNHAP) <= "+this.thangDen+" "
				 + "\n OR YEAR(NGAYNHAP) = "+ this.nam +" AND MONTH(NGAYNHAP) >= "+this.thangTu+" AND YEAR(NGAYNHAP) = "+ this.namDen +" AND MONTH(NGAYNHAP) <= "+this.thangDen+") ";
				query_dieukien = "";
				if(this.idASM.length() > 0) {
					query_dieukien += " AND gsbh.PK_SEQ in (" + this.idASM + ")";
				}
				if(this.idRSM.length() > 0){
					query_dieukien += " AND gsbh.BM_FK in (" + this.idRSM + ")";
				}
				query_Groupby = " group by  kbh.PK_SEQ , kbh.TEN, MONTH(dh.NGAYNHAP), YEAR(NGAYNHAP)" +
				") dh on d.thang = dh.thang where " +
				"d.nam > "+this.nam+" and d.nam < "+this.namDen+""
				+ "\n or d.nam = "+this.nam+" and d.thang >= "+this.thangTu+" and d.nam < "+this.namDen+""
				+ "\n 	or d.nam > "+this.nam+" and d.nam = "+this.namDen+" and d.thang <= "+this.thangDen+""
				+ "\n 	or d.nam = "+this.nam+" and d.thang >= "+this.thangTu+" and d.nam = "+this.namDen+" and d.thang <= "+this.thangDen+" " +
				"	order by d.nam, d.thang"; 
			
				createKenh();
				ResultSet rsKenh = this.rsKenh;
				
				if(rsKenh != null){
				try {
					int indexCot = 0;
					int indexDong = 0;
					while(rsKenh.next()){
						indexDong = 0;
						
						query_dieukien = "";
						if(this.idASM.length() > 0) {
							query_dieukien += " AND gsbh.PK_SEQ in (" + this.idASM + ")";
						}
						if(this.idRSM.length() > 0){
							query_dieukien += " AND gsbh.BM_FK in (" + this.idRSM + ")";
						}
						//query_dieukien += " AND kbh.PK_SEQ = '" + rsKenh.getString("MAKENH") + "'";
					
						query_dieukien += " and sp.pk_seq  in (select sanpham_fk from NhomHang_SanPham where nhomhang_fk in( " + rsKenh.getString("MAKENH") + "))";

						
						query_Groupby = " group by  kbh.PK_SEQ , kbh.TEN, MONTH(dh.NGAYNHAP), YEAR(NGAYNHAP)" +
						") dh on d.thang = dh.thang where " +
						"d.nam > "+this.nam+" and d.nam < "+this.namDen+""
						+ "\n or d.nam = "+this.nam+" and d.thang >= "+this.thangTu+" and d.nam < "+this.namDen+""
						+ "\n 	or d.nam > "+this.nam+" and d.nam = "+this.namDen+" and d.thang <= "+this.thangDen+""
						+ "\n 	or d.nam = "+this.nam+" and d.thang >= "+this.thangTu+" and d.nam = "+this.namDen+" and d.thang <= "+this.thangDen+" " +
						"	order by d.nam, d.thang"; 
						
						
						String truyvan = query + query_dieukien + query_Groupby ;
						
						System.out.println("truy vaans1: " + truyvan);
						ResultSet rskq = db.get(truyvan);
						if(rskq != null){
							try {
								
								while (rskq.next()){
									this.doanhSo[indexCot][indexDong] = rskq.getLong("doanhso");
									this.mangThang[indexDong] = rskq.getString("thang")+ "-" + rskq.getString("nam") ;	
									indexDong ++;
								}
								rskq.close();
								this.sodong = indexDong;
							} catch (Exception e) {
								// TODO: handle exception
								e.printStackTrace();
							}
						}
						
						this.maTen[indexCot] = rsKenh.getString("TENKENH");
						indexCot ++;
						
					}
					this.socot = indexDong-1;
					this.sodong = indexCot;
					this.rsKenh.close();
					System.out.println(this.socot);
					System.out.println(this.sodong);
					for(int i= 0; i< this.sodong; i++){
						System.out.println();
						for(int j =0; j< this.socot; j++){
							System.out.print(this.doanhSo[i][j]+ " ");
						}
					}
				} catch (Exception e) {
					// TODO: handle exception
					e.printStackTrace();
				}
			}
		}
		
		
		
	}
	public void init_cokenh_khongnhan(){
		this.socot = 0;
		this.sodong = 0;
		String[] makenh = this.idKenh.split(",");
		if(makenh.length > 0){
			//xet điều kiện
			String query = "";
			
			String query_dieukien = "";
			Hashtable<String, String> nhanhang = new Hashtable<String,String>();
			String query_Groupby = "";		
			//nếu không chọn gì thì biểu diễn theo nhưng kênh đã có
			if(this.idKenh.length() >0 && this.idBrand.trim().equals("")){
				this.mangThang = new String[(Integer.parseInt(this.namDen)- Integer.parseInt(this.nam)+1)*12];
				
				query_dieukien = "";
				if(this.idASM.length() > 0) {
					query_dieukien += " AND gsbh.PK_SEQ in (" + this.idASM + ")";
				}
				if(this.idRSM.length() > 0){
					query_dieukien += " AND gsbh.BM_FK in (" + this.idRSM + ")";
				}
				if(this.idKenh.length() >0){
					query_dieukien += " and sp.pk_seq  in (select sanpham_fk from NhomHang_SanPham where nhomhang_fk in(" + this.idKenh + "))";

				}
				
				query = "select nh.manh,nh.tennh, d.thang, d.nam, isnull(dh.doanhso,0) doanhso from "
					 + "\n ("
					 + chuoiNam()
					 + "\n  ) d left join "
					 + "\n ( select   "
					 + "\n		  nh.PK_SEQ as manh, nh.TEN as tennh "
					 + "\n from DONHANG dh inner join KENHBANHANG kbh on kbh.PK_SEQ = dh.KBH_FK "
					 + "\n				inner join DONHANG_SANPHAM dhsp on dh.PK_SEQ = dhsp.DONHANG_FK "
					 + "\n				inner join SANPHAM sp ON sp.PK_SEQ = dhsp.SANPHAM_FK 			"
					 + "\n				inner join nhanhang nh ON nh.PK_SEQ = sp.nhanhang_FK 			"
					 + "\n				inner join KHACHHANG kh on kh.PK_SEQ = dh.KHACHHANG_FK "
					 + "\n				inner join DIABAN db on kh.DIABAN_FK =  db.PK_SEQ "
					 + "\n				inner join DAIDIENKINHDOANH ddkd on ddkd.diaban_fk = db.PK_SEQ "
					 + "\n				inner join DDKD_GSBH ddgs on ddgs.DDKD_FK = ddkd.PK_SEQ "
					 + "\n				inner join GIAMSATBANHANG gsbh on gsbh.PK_SEQ = ddgs.GSBH_FK  "
					 + "\n where dh.TRANGTHAI  <> '2' and dh.KHACHHANG_FK IS NOT NULL "
					 + "\n	and (YEAR(NGAYNHAP) > "+ this.nam +" AND YEAR(NGAYNHAP) < "+ this.namDen +" "
					 + "\n OR YEAR(NGAYNHAP) = "+ this.nam +" AND MONTH(NGAYNHAP) >= "+this.thangTu+" AND YEAR(NGAYNHAP) < "+ this.namDen +" "
					 + "\n OR YEAR(NGAYNHAP) > "+ this.nam +" AND YEAR(NGAYNHAP) = "+ this.namDen +" AND MONTH(NGAYNHAP) <= "+this.thangDen+" "
					 + "\n OR YEAR(NGAYNHAP) = "+ this.nam +" AND MONTH(NGAYNHAP) >= "+this.thangTu+" AND YEAR(NGAYNHAP) = "+ this.namDen +" AND MONTH(NGAYNHAP) <= "+this.thangDen+" )"+query_dieukien
					 
					 
					 		+ "\n group by  nh.PK_SEQ , nh.TEN"
					 		+ ") nh on 1 = 1 left join"
					 + "\n ( select  ROUND( SUM(dhsp.giamua*dhsp.soluong)/1000,0) as doanhso, "
					 + "\n		  nh.PK_SEQ as manh, nh.TEN as tennh, MONTH(dh.NGAYNHAP) as thang, YEAR(NGAYNHAP) AS nam "
					 + "\n from DONHANG dh inner join KENHBANHANG kbh on kbh.PK_SEQ = dh.KBH_FK "
					 + "\n				inner join DONHANG_SANPHAM dhsp on dh.PK_SEQ = dhsp.DONHANG_FK "
					 + "\n				inner join SANPHAM sp ON sp.PK_SEQ = dhsp.SANPHAM_FK 			"
					 + "\n				inner join nhanhang nh ON nh.PK_SEQ = sp.nhanhang_FK 			"
					 + "\n				inner join KHACHHANG kh on kh.PK_SEQ = dh.KHACHHANG_FK "
					 + "\n				inner join DIABAN db on kh.DIABAN_FK =  db.PK_SEQ "
					 + "\n				inner join DAIDIENKINHDOANH ddkd on ddkd.diaban_fk = db.PK_SEQ "
					 + "\n				inner join DDKD_GSBH ddgs on ddgs.DDKD_FK = ddkd.PK_SEQ "
					 + "\n				inner join GIAMSATBANHANG gsbh on gsbh.PK_SEQ = ddgs.GSBH_FK  "
					 + "\n where dh.TRANGTHAI  <> '2' and dh.KHACHHANG_FK IS NOT NULL "
					 + "\n	and (YEAR(NGAYNHAP) > "+ this.nam +" AND YEAR(NGAYNHAP) < "+ this.namDen +" "
					 + "\n OR YEAR(NGAYNHAP) = "+ this.nam +" AND MONTH(NGAYNHAP) >= "+this.thangTu+" AND YEAR(NGAYNHAP) < "+ this.namDen +" "
					 + "\n OR YEAR(NGAYNHAP) > "+ this.nam +" AND YEAR(NGAYNHAP) = "+ this.namDen +" AND MONTH(NGAYNHAP) <= "+this.thangDen+" "
					 + "\n OR YEAR(NGAYNHAP) = "+ this.nam +" AND MONTH(NGAYNHAP) >= "+this.thangTu+" AND YEAR(NGAYNHAP) = "+ this.namDen +" AND MONTH(NGAYNHAP) <= "+this.thangDen+") ";
				int indexCot = 0;
				for(int i=0; i< makenh.length; i++ ){
					int indexDong = 0;
					
					query_dieukien = "";
					if(this.idASM.length() > 0) {
						query_dieukien += " AND gsbh.PK_SEQ in (" + this.idASM + ")";
					}
					if(this.idRSM.length() > 0){
						query_dieukien += " AND gsbh.BM_FK in (" + this.idRSM + ")";
					}
					if(this.idKenh.length() >0){
						query_dieukien += " and sp.pk_seq  in (select sanpham_fk from NhomHang_SanPham where nhomhang_fk in(" + this.idKenh + "))";

					}
					
					query_Groupby = " group by  nh.PK_SEQ , nh.TEN, MONTH(dh.NGAYNHAP), YEAR(NGAYNHAP)" +
					") dh on d.thang = dh.thang and nh.manh = dh.manh where " +
					"d.nam > "+this.nam+" and d.nam < "+this.namDen+""
					+ "\n or d.nam = "+this.nam+" and d.thang >= "+this.thangTu+" and d.nam < "+this.namDen+""
					+ "\n 	or d.nam > "+this.nam+" and d.nam = "+this.namDen+" and d.thang <= "+this.thangDen+""
					+ "\n 	or d.nam = "+this.nam+" and d.thang >= "+this.thangTu+" and d.nam = "+this.namDen+" and d.thang <= "+this.thangDen+" " +
					"	order by d.nam, d.thang"; 
					
					String truyvan = query + query_dieukien + query_Groupby ;
					
					System.out.println("truy vaans2: " + truyvan);
					String value = "";
					ResultSet rskq = db.get(truyvan);
					if(rskq != null){
						try {
							
							while (rskq.next()){
								
							
								value =  rskq.getString("thang")+ "-" + rskq.getString("nam") ;
								
								if(indexDong == 0)
									this.mangThang[indexCot] = rskq.getString("thang")+ "-" + rskq.getString("nam") ;
								
								if(!this.mangThang[indexCot].equals(value))
								{
									
									indexCot ++;
									this.mangThang[indexCot] = rskq.getString("thang")+ "-" + rskq.getString("nam") ;
									indexDong = 0;
									
								}
						
								if(!nhanhang.containsKey(rskq.getString("manh")))
								{
									nhanhang.put(rskq.getString("manh"),rskq.getString("tennh"));
									this.maTen[indexDong] = rskq.getString("tennh");
								}
								
								this.doanhSo[indexDong][indexCot] = rskq.getLong("doanhso");
								indexDong++;
								
								
							
							}
							rskq.close();
							
							this.sodong = indexDong;
						} catch (Exception e) {
							// TODO: handle exception
							e.printStackTrace();
						}
					}
					
					/*Enumeration<String> keyList = nhanhang.keys();
					int i = 0;
					while(keyList.hasMoreElements())
					{
						String key = keyList.nextElement();
					
					
						
					}*/
					
					
				}
				this.socot = indexCot;
			//	this.sodong = Integer.parseInt(this.thangDen) - Integer.parseInt(this.thangTu) + 1;
				
				System.out.println(this.socot);
				System.out.println(this.sodong);
			}
		}
		
	}
	public String layTenKenh(String maKenh_input){
		String ten = "";
		String query = " select TEN from  KENHBANHANG where PK_SEQ = " + maKenh_input;
		ResultSet rs = db.get(query);
		try {
			if(rs != null){
				if(rs.next()){
					ten = rs.getString("TEN");
				}
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			
		}
		return ten;
	}
	
	public void init_theo_nhan(){
		this.mangThang = new String[(Integer.parseInt(this.namDen)- Integer.parseInt(this.nam)+1)*12];
		String maNhan[] = this.idBrand.split(",");
		ResultSet rssp = db.get("select pk_seq from sanpham where nhanhang_fk in ("+this.idBrand+")");
		System.out.println("QRSP: "+"select pk_seq from sanpham where nhanhang_fk in ("+this.idBrand+")");
		if(rssp != null)
		{
			String masp  = "";
			try {
				while(rssp.next())
				{
					masp += rssp.getString("pk_seq")+",";
				}
				if(masp.length() > 0)
				{
					masp = masp.substring(0,masp.length() - 1);
					maNhan = masp.split(",");
				}
				
				
			} catch (SQLException e) {
			
				e.printStackTrace();
			}
		}
		if(maNhan.length > 0){
			//xet điều kiện
			String query = "";
			
			String query_dieukien = "";

			String query_Groupby = "";
			query = "select d.thang, d.nam, isnull(dh.doanhso,0) doanhso from "
				 + "\n ("
				 + chuoiNam()
				 + "\n  ) d left join "
			 + "\n ( select  ROUND( SUM(dhsp.giamua*dhsp.soluong)/1000,0) as doanhso, "
			 + "\n 		 nh.TEN as tennhanhang, nh.PK_SEQ as manhanhang, MONTH(dh.NGAYNHAP) as thang, YEAR(dh.NGAYNHAP) as nam"
			 + "\n from DONHANG dh inner join KENHBANHANG kbh on kbh.PK_SEQ = dh.KBH_FK"
			 + "\n 				inner join DONHANG_SANPHAM dhsp on dh.PK_SEQ = dhsp.DONHANG_FK"
			 + "\n 				inner join SANPHAM sp ON sp.PK_SEQ = dhsp.SANPHAM_FK"
			 + "\n 				inner join NHANHANG nh ON nh.PK_SEQ = sp.NHANHANG_FK"
			 + "\n 				inner join KHACHHANG kh on kh.PK_SEQ = dh.KHACHHANG_FK"
			 + "\n 				inner join DIABAN db on kh.DIABAN_FK =  db.PK_SEQ"
			 + "\n 				inner join DAIDIENKINHDOANH ddkd on ddkd.diaban_fk = db.PK_SEQ"
			 + "\n 				inner join DDKD_GSBH ddgs on ddgs.DDKD_FK = ddkd.PK_SEQ"
			 + "\n 				inner join GIAMSATBANHANG gsbh on gsbh.PK_SEQ = ddgs.GSBH_FK  "
			 + "\n where dh.TRANGTHAI  <> '2' and dh.KHACHHANG_FK IS NOT NULL "
			 + "\n	and (YEAR(NGAYNHAP) > "+ this.nam +" AND YEAR(NGAYNHAP) < "+ this.namDen +" "
			 + "\n OR YEAR(NGAYNHAP) = "+ this.nam +" AND MONTH(NGAYNHAP) >= "+this.thangTu+" AND YEAR(NGAYNHAP) < "+ this.namDen +" "
			 + "\n OR YEAR(NGAYNHAP) > "+ this.nam +" AND YEAR(NGAYNHAP) = "+ this.namDen +" AND MONTH(NGAYNHAP) <= "+this.thangDen+" "
			 + "\n OR YEAR(NGAYNHAP) = "+ this.nam +" AND MONTH(NGAYNHAP) >= "+this.thangTu+" AND YEAR(NGAYNHAP) = "+ this.namDen +" AND MONTH(NGAYNHAP) <= "+this.thangDen+") ";
			int indexCot = 0;
			for(int i = 0; i< maNhan.length ; i++){
				int indexDong = 0;
				query_dieukien = "";
				if(this.idASM.length() > 0) {
					query_dieukien += " AND gsbh.PK_SEQ in (" + this.idASM + ")";
				}
				if(this.idRSM.length() > 0){
					query_dieukien += " AND gsbh.BM_FK in (" + this.idRSM + ")";
				}
				if(this.idKenh.length() >0){
					query_dieukien += " and sp.pk_seq  in (select sanpham_fk from NhomHang_SanPham where nhomhang_fk in(" + this.idKenh + "))";

				}
				query_dieukien += " AND sp.PK_SEQ = '" + maNhan[i] + "'";
				query_Groupby = " group by  nh.TEN , nh.PK_SEQ, MONTH(dh.NGAYNHAP), YEAR(dh.NGAYNHAP)" +
				") dh on d.thang = dh.thang where " +
				"d.nam > "+this.nam+" and d.nam < "+this.namDen+""
				+ "\n or d.nam = "+this.nam+" and d.thang >= "+this.thangTu+" and d.nam < "+this.namDen+""
				+ "\n 	or d.nam > "+this.nam+" and d.nam = "+this.namDen+" and d.thang <= "+this.thangDen+""
				+ "\n 	or d.nam = "+this.nam+" and d.thang >= "+this.thangTu+" and d.nam = "+this.namDen+" and d.thang <= "+this.thangDen+" " +
				"	order by d.nam, d.thang"; 
				String truyvan = query + query_dieukien + query_Groupby ;
				System.out.println("truy vaans3: " + truyvan);
				
				ResultSet rskq = db.get(truyvan);
				if(rskq != null){
					try {
						while(rskq.next()){
							this.doanhSo[indexDong][indexCot] = rskq.getLong("doanhso");
							this.mangThang[indexDong] = rskq.getString("thang")+ "-" + rskq.getString("nam");		
							indexDong ++;
						}
						rskq.close();
						this.sodong = indexDong;
					} catch (Exception e) {
						// TODO: handle exception
						e.printStackTrace();
					}
					
				}
				this.maTen[indexCot] = layTenNhan(maNhan[i]);
				indexCot ++;
			}
			this.socot = indexCot;
			//this.sodong = Integer.parseInt(this.thangDen) - Integer.parseInt(this.thangTu) + 1;
			
			System.out.println(this.socot);
			System.out.println(this.sodong);
		}
	}
	public String layTenNhan(String maNhan){
		String ten = "";
		// Sửa đổi nhãn hàng thành sản phẩm
		String query = "SELECT TEN FROM sanpham WHERE PK_SEQ = " + maNhan;
		ResultSet rs = db.get(query);
		if(rs != null){
			try {
				if(rs.next()){
					ten = rs.getString("TEN");
				}
			} catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
			}
		}
		return ten;
	}
	public void init(){
		if(this.idKenh.trim().equals("") && this.idBrand.trim().equals("")){
			innit_no_kenh_no_nhan();
		}
		else if(this.idKenh.length() > 0 && this.idBrand.trim().equals("")){
			init_cokenh_khongnhan();
		}
		else if(this.idBrand.length() > 0){
			init_theo_nhan();
		}
	}
}
