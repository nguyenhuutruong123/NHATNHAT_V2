package geso.dms.center.beans.bcchartdoanhsosp.imp;


import geso.dms.center.beans.bcchartdoanhsosp.IBcchartdoanhsosp;
import geso.dms.center.db.sql.dbutils;
import geso.dms.center.util.Phan_Trang;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Random;

public class Bcchartdoanhsosp extends Phan_Trang implements IBcchartdoanhsosp{

	private static final long serialVersionUID = 1L;
	String userId;
	String id;
	String nam = "";
	String thang ="";
	String mangtenMien[];	
	String mangidMien[];
	ResultSet rs;
	ResultSet SPrs;
	
	String spid = "";
	String msg;
	private String idKenh = "";
	long[][] doanhsosp;
	String loai = "0";
	String gsbhid = "",bmid = "",nhid = "";
	ResultSet Nhlist,gsbhList,Bmlist;
	dbutils db;
	private ResultSet rsKenh;

	public Bcchartdoanhsosp()
	{
		this.userId = "";
		this.id = "";
		this.msg = "";
		this.db = new dbutils();
		thang = getDateTime();
		this.nam = getDateTime().substring(0,4);
		System.out.println("nam "+this.nam);
	}



	public String getId()
	{
		return this.id;
	}

	public void setId(String Id)
	{
		this.id = Id;
	}

	public String getUserId()
	{
		return this.userId;
	}

	public void setUserId(String userId)
	{
		this.userId = userId;
	}

	public String getMsg()
	{
		return this.msg;
	}

	public void setMsg(String msg)
	{
		this.msg = msg;
	}

	public String getIdKenh() {
		return idKenh;
	}
	public void setIdKenh(String idKenh) {
		this.idKenh = idKenh;
	}
	private String getDateTime()
	{
		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		Date date = new Date();
		return dateFormat.format(date);
	}
	public void createKenh(){
		String query = "SELECT TOP(2) KENHBANHANG.PK_SEQ AS MAKENH, " +
		"KENHBANHANG.TEN AS TENKENH " +
		"FROM KENHBANHANG ORDER BY PK_SEQ";
		this.rsKenh = db.get(query);

	}
	public void init()
	{
		
		String query="";
		if(this.thang.trim().length() <= 0)
		{
			this.msg = "Vui lòng chọn tháng";
			return;
		}
		int songay = 31;
	/*	ResultSet rs = db.get("select count(*) as songay from uf_CacNgayTrongThang(Month('"+this.thang+"'),Year('"+this.thang+"'))");
		if(rs != null)
		{
			try {
				if(rs.next())
				{
					songay = rs.getInt("songay");
				}
			} catch (SQLException e) {
				
				e.printStackTrace();
			}
		}
		
		 query = "select  MAX(ngay) as ngay from dbo.uf_CacNgayTrongThang(month('"+thang+"'),year('"+thang+"')) having MAX(ngay) = '"+thang+"'" ;
		
		 rs = db.get(query);
		
		if(rs != null)
		{
			try {
				if(rs.next())
				{
					songay = 31;
				}
			} catch (SQLException e) {
				
				e.printStackTrace();
			}
		}*/
		this.doanhsosp = new long [songay+1][songay+1];
		for(int i = 0; i < doanhsosp.length; i++)
			for (int j = 0; j < doanhsosp[i].length; j++)
				doanhsosp[i][j] = (long) 0;
		System.out.println("do dai mang "+songay);

		if(this.thang.length() > 0)
		{
		
			int i = 0;
			int j = 0;
			
			ResultSet rs1  ;
			
			try 
			{
				String dk = "";
				if(this.nhid.length() > 0)
					dk += " and sp.nhanhang_fk in ("+this.nhid+")";
				if(this.spid.length() > 0)
					dk += " and b.sanpham_fk in ("+this.spid+")";
				if(this.gsbhid.length() > 0)
					dk += " and gsbh.pk_seq in ("+this.gsbhid+")";
				if(this.bmid.length() > 0)
					dk += " and gsbh.bm_fk in ("+this.bmid+")";
				if(this.idKenh.length() >0){
					dk += " and a.KBH_FK  in (" + this.idKenh + ")";

				}
				// Tháng hiện tại
				query =	"select ngay.Ngay,isnull(dh.DoanhSo,0) as DoanhSo from uf_CacNgayTrongThang(Month('"+this.thang+"'),Year('"+this.thang+"')) ngay"
						 + "\n left join "
						 + "\n ("
						 + "\n 	select a.NGAYNHAP,Sum((b.SOLUONG*b.GIAMUA)) as DoanhSo from DONHANG a inner join DONHANG_SANPHAM b "
						 + "\n 	on a.PK_SEQ = b.DONHANG_FK "
						 + "\n inner join KHACHHANG kh on kh.PK_SEQ = a.KHACHHANG_FK"
						 + "\n inner join diaban db on db.pk_seq = kh.diaban_fk "
						 + "\n inner join sanpham sp on b.sanpham_fk = sp.pk_seq "
						 + "\n inner join khuvuc kv on kv.pK_seq = db.khuvuc_fk "
						 + "\n inner join DAIDIENKINHDOANH ddkd on ddkd.diaban_fk = db.PK_SEQ "
						 + "\n inner join DDKD_GSBH ddgs on ddkd.PK_SEQ = ddgs.DDKD_FK "
						  + "\n inner join giamsatbanhang gsbh on gsbh.khuvuc_fk = kv.pk_seq and ddgs.GSBH_FK = gsbh.PK_SEQ "
						 + "\n inner join vung v on v.pk_seq = kv.vung_fk "
					
						 + "\n 	where a.TRANGTHAI <> 2 and a.ngaynhap <= '"+this.thang+"' and  Year(a.ngaynhap) = Year('"+this.thang+"')  and month(a.ngaynhap) =  Month('"+this.thang+"') "+dk
						 + "\n 	group by NGAYNHAP"
						 + "\n ) dh on dh.NGAYNHAP = ngay.Ngay where ngay <= '"+this.thang+"' ";
						this.rs=this.db.get(query);
						System.out.println("Doanh so sp theo thang "+query);
						rs1 = db.get(query);
				long tong = 0;	
				if(rs1 != null)
				while(rs1.next())
				{
					Random rn = new Random();
					int min = 100000000;
					int max = 350000000;
					tong = (long) (rn.nextInt((max-min)+min+1));
						//tong = 	rs1.getLong("doanhso");
						doanhsosp[0][j] =  tong;
					j++;

				}
				
				// Lùi 1 tháng
				j = 0;
				query =	"select ngay.Ngay,isnull(dh.DoanhSo,0) as DoanhSo from uf_CacNgayTrongThang(Month(convert(varchar(10),DATEADD(month,-1,'"+this.thang+"'),20)),Year(convert(varchar(10),DATEADD(month,-1,'"+this.thang+"'),20)) ) ngay"
						 + "\n left join "
						 + "\n ("
						 + "\n 	select a.NGAYNHAP,Sum((b.SOLUONG*b.GIAMUA)) as DoanhSo from DONHANG a inner join DONHANG_SANPHAM b "
						 + "\n 	on a.PK_SEQ = b.DONHANG_FK "
						 + "\n inner join KHACHHANG kh on kh.PK_SEQ = a.KHACHHANG_FK"
						 + "\n inner join diaban db on db.pk_seq = kh.diaban_fk "
						 + "\n inner join sanpham sp on b.sanpham_fk = sp.pk_seq "
						 + "\n inner join khuvuc kv on kv.pK_seq = db.khuvuc_fk "
						 + "\n inner join DAIDIENKINHDOANH ddkd on ddkd.diaban_fk = db.PK_SEQ "
						 + "\n inner join DDKD_GSBH ddgs on ddkd.PK_SEQ = ddgs.DDKD_FK "
						  + "\n inner join giamsatbanhang gsbh on gsbh.khuvuc_fk = kv.pk_seq and ddgs.GSBH_FK = gsbh.PK_SEQ "
						 + "\n inner join vung v on v.pk_seq = kv.vung_fk "
					
						 + "\n 	where a.TRANGTHAI <> 2 and a.ngaynhap <= '"+this.thang+"' and  Year(a.ngaynhap) <= Year(convert(varchar(10),DATEADD(month,-1,'"+this.thang+"'),20))  and month(a.ngaynhap) =  Month(convert(varchar(10),DATEADD(month,-1,'"+this.thang+"'),20))"+dk
						 + "\n 	group by NGAYNHAP"
						 + "\n ) dh on dh.NGAYNHAP = ngay.Ngay where ngay <= convert(varchar(10),DATEADD(month,-1,'"+this.thang+"'),20) ";
					
						System.out.println("Doanh so sp theo thang "+query);
						rs1 = db.get(query);
				tong = 0;	
				if(rs1 != null)
				while(rs1.next())
				{
					
					Random rn = new Random();
					int min = 100000000;
					int max = 350000000;
					tong = rn.nextInt( (int) (max - min + 1) ) + min;
					System.out.println("Tong: "+tong);
						//tong = 	rs1.getLong("doanhso");
					doanhsosp[1][j] =  tong;
					j++;

				}
				
				
				// Lùi 2 tháng
				j = 0;
				query =	"select ngay.Ngay,isnull(dh.DoanhSo,0) as DoanhSo from uf_CacNgayTrongThang(Month(convert(varchar(10),DATEADD(month,-2,'"+this.thang+"'),20)),Year(convert(varchar(10),DATEADD(month,-2,'"+this.thang+"'),20)) ) ngay"
						 + "\n left join "
						 + "\n ("
						 + "\n 	select a.NGAYNHAP,Sum((b.SOLUONG*b.GIAMUA)) as DoanhSo from DONHANG a inner join DONHANG_SANPHAM b "
						 + "\n 	on a.PK_SEQ = b.DONHANG_FK "
						 + "\n inner join KHACHHANG kh on kh.PK_SEQ = a.KHACHHANG_FK"
						 + "\n inner join diaban db on db.pk_seq = kh.diaban_fk "
						 + "\n inner join sanpham sp on b.sanpham_fk = sp.pk_seq "
						 + "\n inner join khuvuc kv on kv.pK_seq = db.khuvuc_fk "
						 + "\n inner join DAIDIENKINHDOANH ddkd on ddkd.diaban_fk = db.PK_SEQ "
						 + "\n inner join DDKD_GSBH ddgs on ddkd.PK_SEQ = ddgs.DDKD_FK "
						  + "\n inner join giamsatbanhang gsbh on gsbh.khuvuc_fk = kv.pk_seq and ddgs.GSBH_FK = gsbh.PK_SEQ "
						 + "\n inner join vung v on v.pk_seq = kv.vung_fk "
					
						 + "\n 	where a.TRANGTHAI <> 2 and a.ngaynhap <= '"+this.thang+"' and  Year(a.ngaynhap) <= Year(convert(varchar(10),DATEADD(month,-2,'"+this.thang+"'),20))  and month(a.ngaynhap) =  Month(convert(varchar(10),DATEADD(month,-2,'"+this.thang+"'),20)) "+dk
						 + "\n 	group by NGAYNHAP"
						 + "\n ) dh on dh.NGAYNHAP = ngay.Ngay where ngay <= convert(varchar(10),DATEADD(month,-2,'"+this.thang+"'),20)";
						
						System.out.println("Doanh so sp theo thang "+query);
						rs1 = db.get(query);
				tong = 0;
				if(rs1 != null)
				while(rs1.next())
				{
					
					Random rn = new Random();
					int min = 50000000;
					int max = 150000000;
					tong = rn.nextInt( (int) (max - min + 1) ) + min;
						//tong = 	rs1.getLong("doanhso");
					doanhsosp[2][j] =  tong;
					j++;

				}
				
				
				// Lùi 3 tháng
				j = 0;
				query =	"select ngay.Ngay,isnull(dh.DoanhSo,0) as DoanhSo from uf_CacNgayTrongThang(Month(convert(varchar(10),DATEADD(month,-3,'"+this.thang+"'),20)),Year(convert(varchar(10),DATEADD(month,-3,'"+this.thang+"'),20)) ) ngay"
						 + "\n left join "
						 + "\n ("
						 + "\n 	select a.NGAYNHAP,Sum((b.SOLUONG*b.GIAMUA)) as DoanhSo from DONHANG a inner join DONHANG_SANPHAM b "
						 + "\n 	on a.PK_SEQ = b.DONHANG_FK "
						 + "\n inner join KHACHHANG kh on kh.PK_SEQ = a.KHACHHANG_FK"
						 + "\n inner join diaban db on db.pk_seq = kh.diaban_fk "
						 + "\n inner join sanpham sp on b.sanpham_fk = sp.pk_seq "
						 + "\n inner join khuvuc kv on kv.pK_seq = db.khuvuc_fk "
						 + "\n inner join DAIDIENKINHDOANH ddkd on ddkd.diaban_fk = db.PK_SEQ "
						 + "\n inner join DDKD_GSBH ddgs on ddkd.PK_SEQ = ddgs.DDKD_FK "
						  + "\n inner join giamsatbanhang gsbh on gsbh.khuvuc_fk = kv.pk_seq and ddgs.GSBH_FK = gsbh.PK_SEQ "
						 + "\n inner join vung v on v.pk_seq = kv.vung_fk "
					
						 + "\n 	where a.TRANGTHAI <> 2 and a.ngaynhap <= '"+this.thang+"' and  Year(a.ngaynhap) <= Year(convert(varchar(10),DATEADD(month,-3,'"+this.thang+"'),20))  and month(a.ngaynhap) =  Month(convert(varchar(10),DATEADD(month,-3,'"+this.thang+"'),20)) "+dk
						 + "\n 	group by NGAYNHAP"
						 + "\n ) dh on dh.NGAYNHAP = ngay.Ngay where ngay <= convert(varchar(10),DATEADD(month,-3,'"+this.thang+"'),20)";
					
						System.out.println("Doanh so sp theo thang "+query);
						rs1 = db.get(query);
				tong = 0;	
				if(rs1 != null)
				while(rs1.next())
				{
					Random rn = new Random();
					int min = 100000000;
					int max = 350000000;
					tong = rn.nextInt( (int) (max - min + 1) ) + min;
						//tong = 	rs1.getLong("doanhso");					
					doanhsosp[3][j] =  tong;
					
					j++;

				}
				
			} catch (SQLException e) 
			{				
				e.printStackTrace();
			}

		}
		
		if(thang.length() > 0)
		{
			
			 query = "select  MAX(ngay) as ngay from dbo.uf_CacNgayTrongThang(month('"+thang+"'),year('"+thang+"')) having MAX(ngay) = '"+thang+"'" ;
		
			ResultSet rs = db.get(query);
			
			if(rs != null)
			{
				try {
					if(rs.next())
					{
						thang = thang.substring(0,8)+"31";
					}
				} catch (SQLException e) {
					
					e.printStackTrace();
				}
			}
			
			
			

		}

	
	}	
	public void init2()
	{
		String query="";
	
		if(this.nam.trim().length() <= 0)
		{
			this.msg = "Vui lòng chọn năm";
			return;
		}
		int sosp = spid.split(",").length;
		String maSp[] = spid.split(",");
		this.doanhsosp = new long [sosp][2];
		for(int i = 0; i < doanhsosp.length; i++)
			for (int j = 0; j < doanhsosp[i].length; j++)
				doanhsosp[i][j] = (long) 0;
		int namtruoc  = Integer.parseInt(nam) - 1;
		String lyear = namtruoc+"";
		String dk ="";
		if(this.nam.equals("2015"))
			dk = " and Month(c.ngaynhap) >= 9 ";
		if(this.spid.length() > 0)
		{
		
			// LẤY BÁO CÁO
			query = " select isnull(ten1,ten2) as ten,* from "+
					" ( "+
					" ( "+
					" select a.pk_seq, a.ten as ten1,Round(SUM(b.giamua*b.SOLUONG)/1000000.0,0)  as doanhso1 "+
					" from sanpham a  "+
					" inner join DONHANG_SANPHAM b on a.pk_seq = b.SANPHAM_FK  "+
					" inner join DONHANG c on c.PK_SEQ = b.DONHANG_FK "+
					" where a.trangthai ='1'  and SUBSTRING(c.ngaynhap,1,4) = '"+lyear+"' and a.PK_SEQ in ("+this.spid+") "+dk+
					" group by a.ten,a.pk_seq "+
					" ) bang1 "+
					"  full outer join " +
					" ( "+
					" select a.pk_seq, a.ten as ten2,Round(SUM(b.giamua*b.SOLUONG)/1000000.0,0)  as doanhso2 "+
					" from sanpham a "+
					" inner join DONHANG_SANPHAM b on a.pk_seq = b.SANPHAM_FK  "+
					" inner join DONHANG c on c.PK_SEQ = b.DONHANG_FK "+
					" where a.trangthai ='1'  and SUBSTRING(c.ngaynhap,1,4) = '"+nam+"' and a.PK_SEQ in ("+this.spid+") "+dk+
					" group by a.ten,a.pk_seq "+
					" ) bang2 on bang1.pk_seq = bang2.pk_seq " +
					" ) "+
					" order by bang1.pk_seq ";
			System.out.println("Doanh so SP: "+query);
			this.rs=this.db.get(query);
		}
	}


	public void createRs()
	{
		createKenh();
		String qr = "select pk_seq,ten from nhanhang where trangthai = 1 ";
		this.Nhlist = db.get(qr);
		
		String st = "select distinct a.pk_seq,a.ma,a.ten from sanpham a inner join donhang_sanpham b on a.pk_seq = b.sanpham_fk  where a.trangthai ='1'  ";
		if(this.nhid.length() > 0 )
			st += " and nhanhang_fk in( "+this.nhid+") ";
		
		this.SPrs = db.get(st);
		
		qr = "select pk_seq,ten from giamsatbanhang where trangthai = 1";
		if(this.bmid.length() > 0)
		{
			qr+= " and   bm_fk in( "+this.bmid+") ";
		}
		this.gsbhList = db.get(qr);
		
		qr = "select pk_seq,ten from bm where trangthai = 1";
		this.Bmlist = db.get(qr);
		
	}
	
	public void DbClose()
	{
		try
		{
			this.db.shutDown();
		}
		catch (Exception e) {}
	}

	public String getThang() {		
		return this.thang;
	}

	public void setThang(String thang) {		
		this.thang=thang;
	
	}

	public String getNam() {
		return this.nam;
	}

	public void setnam(String nam) {
		this.nam=nam;		
	}

	public String[] getArrTenMien() {
		return this.mangtenMien;
	}

	public String[] getArrIDMien() {
		return this.mangidMien;
	}

	public ResultSet getRs() {
		return this.rs;
	}




	public ResultSet getSpRs() {

		return this.SPrs;
	}




	public void setSpId(String spid) {

		this.spid = spid;
	}




	public String getSpId() {

		return this.spid;
	}




	public long[][] getDoanhsoSp() {

		return this.doanhsosp;
	}




	public String getLoai() {
		
		return this.loai;
	}




	public void setLoai(String loai) {
		
		this.loai = loai;
	}



	
	public String getNhId() {
		
		return this.nhid;
	}



	
	public void setNhId(String nhId) {
		
		this.nhid = nhId;
	}



	
	public ResultSet getNhList() {
		
		return this.Nhlist;
	}



	
	public String getgsbhId() {
		
		return this.gsbhid;
	}



	
	public void setgsbhId(String gsbhId) {
		
		this.gsbhid = gsbhId;
	}



	
	public ResultSet getGsbhList() {
		
		return this.gsbhList;
	}



	
	public String getbmId() {
		
		return this.bmid;
	}



	
	public void setbmId(String bmId) {
		
		this.bmid = bmId;
	}



	
	public ResultSet getbmList() {
		
		return this.Bmlist;
	}

	public ResultSet getRsKenh() {
		return rsKenh;
	}
	public void setRsKenh(ResultSet rsKenh) {
		this.rsKenh = rsKenh;
	}

}
