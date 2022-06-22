package geso.dms.center.beans.bcchartsanpham.imp;


import geso.dms.center.beans.bcchartsanpham.IBcchartsanpham;
import geso.dms.center.db.sql.dbutils;
import geso.dms.center.util.Phan_Trang;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class Bcchartsanpham extends Phan_Trang implements IBcchartsanpham{

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
	Long doanhsosp[][];
	String loai = "0";

	dbutils db;

	public Bcchartsanpham()
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


	private String getDateTime()
	{
		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		Date date = new Date();
		return dateFormat.format(date);
	}

	public void init()
	{
		String query="";
		if(this.thang.trim().length() <= 0)
		{
			this.msg = "Vui lòng chọn tháng";
			return;
		}
//		String date;
//		if(Integer.parseInt(thang) < 10)
//			date = nam+"-0"+thang;
//		else
//			date = nam+"-"+thang;
		System.out.println("this.spid "+this.spid);

		
		int sosp = spid.split(",").length;
		String maSp[] = spid.split(",");
		this.doanhsosp = new Long [sosp][Integer.parseInt(thang.substring(5, 7))+1];
		for(int i = 0; i < doanhsosp.length; i++)
			for (int j = 0; j < doanhsosp[i].length; j++)
				doanhsosp[i][j] = (long) 0;
		System.out.println("do dai mang "+thang.substring(5, 7));

		int thangtruoc = Integer.parseInt(thang.substring(5, 7)) - 1;
		if(thangtruoc == 0)
			thangtruoc = 12;
		String lastmonth = "";
		if(thangtruoc < 10)
			lastmonth ="0"+thangtruoc; 
		else
			lastmonth = thangtruoc+"";
		
		System.out.println("Thang truoc: "+" "+thangtruoc+" "+lastmonth);
		String lyear = "";
		int namtruoc = Integer.parseInt(thang.substring(0, 4));
		System.out.println("namtruoc: "+" "+namtruoc+" "+lastmonth);
		if(lastmonth.equals("12"))
		{
			namtruoc = namtruoc - 1;
			
		}
		lyear = namtruoc+"";
		String today = thang.substring(8,10);
		
		if(this.spid.length() > 0)
		{
		
			// LẤY BÁO CÁO
			query = " select * from "+
					" ( "+
					" ( "+
					" select a.pk_seq, a.ten as ten1,Round(SUM(b.giamua*b.SOLUONG)/1000000.0,0)  as doanhso1 "+
					" from sanpham a  "+
					" inner join DONHANG_SANPHAM b on a.pk_seq = b.SANPHAM_FK  "+
					" inner join DONHANG c on c.PK_SEQ = b.DONHANG_FK "+
					" where a.trangthai ='1'  and SUBSTRING(c.ngaynhap,1,7) = '"+lyear+"-"+lastmonth+"' and a.PK_SEQ in ("+this.spid+") "+
					" group by a.ten,a.pk_seq "+
					" ) bang1 "+
					" full outer join " +
					" ( "+
					" select a.pk_seq, a.ten,Round(SUM(b.giamua*b.SOLUONG)/1000000.0,0)  as doanhso2 "+
					" from sanpham a "+
					" inner join DONHANG_SANPHAM b on a.pk_seq = b.SANPHAM_FK  "+
					" inner join DONHANG c on c.PK_SEQ = b.DONHANG_FK "+
					" where a.trangthai ='1'  and c.NGAYNHAP >= '"+lyear+"-"+lastmonth+"-01' and c.NGAYNHAP <= '"+lyear+"-"+lastmonth+"-"+today+"' and a.PK_SEQ in ("+this.spid+") "+
					" group by a.ten,a.pk_seq "+
					" ) bang2 on bang1.pk_seq = bang2.pk_seq " +
					" full outer join " +
					" ( " +
					" select a.pk_seq, a.ten,Round(SUM(b.giamua*b.SOLUONG)/1000000.0,0)  as doanhso3 " +
					" from sanpham a " +
					" inner join DONHANG_SANPHAM b on a.pk_seq = b.SANPHAM_FK  " +
					" inner join DONHANG c on c.PK_SEQ = b.DONHANG_FK " +
					" where a.trangthai ='1'  and c.ngaynhap  >= '"+this.thang.substring(0,7)+"-01' and c.ngaynhap  <= '"+this.thang+"' and a.PK_SEQ in ("+this.spid+") "+
					" group by a.ten,a.pk_seq " +
					" ) bang3 on bang3.pk_seq = bang2.pk_seq " +
					" ) "+
					" order by bang1.pk_seq ";
			System.out.println("Doanh so SP: "+query);
			this.rs=this.db.get(query);
		}

		if(this.spid.length() > 0)
		{
			String dk ="";
			if(this.thang.substring(0,4).equals("2015"))
				dk = " and Month(c.ngaynhap) >= 9 ";
			query = 
					" select SUBSTRING(c.ngaynhap,6,2) as thang, a.pk_seq, a.ten,Round(isnull(SUM(b.giamua*b.SOLUONG),0)/1000000.0,0)  as doanhso "+
							" from sanpham a  "+
							" inner join DONHANG_SANPHAM b on a.pk_seq = b.SANPHAM_FK  "+
							" inner join DONHANG c on c.PK_SEQ = b.DONHANG_FK "+
							" where a.trangthai ='1'  and c.ngaynhap  <= '"+this.thang+"' and SUBSTRING(c.ngaynhap,1,7) >= '"+this.thang.substring(0,4)+"-01' and a.PK_SEQ in ("+this.spid+")"+dk+
							" group by a.ten,a.pk_seq, SUBSTRING(c.ngaynhap,6,2) "+
							" order by a.pk_seq ";
			System.out.println("Doanh so sp theo thang "+query);
			ResultSet rs1 = db.get(query);
			try 
			{
				int month = 1;
				int i = 0;
				int j = 0;
				String sp[] = new String[10000];
				while(rs1.next())
				{
					System.out.println("Thang "+Integer.parseInt(rs1.getString("thang")));
					System.out.println("masp "+Integer.parseInt(rs1.getString("pk_seq")));
					System.out.println("doanhso "+rs1.getDouble("doanhso"));
					System.out.println("i-j "+i+"-"+j);
				
					sp[j] = rs1.getString("pk_seq");
				
					if(j > 0)
					{
						System.out.println("doanhso "+sp[j-1]);
						if(!rs1.getString("pk_seq").equals(sp[j-1]))
						{
							i++;
						}
					}
					doanhsosp[i][Integer.parseInt(rs1.getString("thang"))] = rs1.getLong("doanhso");
					j++;
					month++;

				}
			} catch (SQLException e) 
			{				
				e.printStackTrace();
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
		this.doanhsosp = new Long [sosp][2];
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
		String st = "select distinct a.pk_seq,a.ma,a.ten from sanpham a inner join donhang_sanpham b on a.pk_seq = b.sanpham_fk  where a.trangthai ='1'  ";
		this.SPrs = db.get(st);
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
		System.out.println("Thang bean "+thang);
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




	public Long[][] getDoanhsoSp() {

		return this.doanhsosp;
	}




	public String getLoai() {
		
		return this.loai;
	}




	public void setLoai(String loai) {
		
		this.loai = loai;
	}



}
