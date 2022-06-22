package geso.dms.center.beans.bcchartkhachhangmuahang.imp;

import java.sql.ResultSet;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

import com.extentech.formats.XLS.Array;
import com.rp.util.DateTime;


import geso.dms.center.beans.bcchartkhachhangmuahang.IBcchartkhachhangmuahang;
import geso.dms.center.db.sql.dbutils;
import geso.dms.center.util.Phan_Trang;

public class Bcchartkhachhangmuahang extends Phan_Trang implements IBcchartkhachhangmuahang {
	

	String userId;
	String id;
	String msg;
	String thangbatdau = "";
	String thangketthuc="";
	String nam1="";
	String nam2="";
	String gsbhid = "",bmid = "",nhid = "",nspid="",spid="";
	ResultSet SPrs;
	ResultSet rs; 
	ResultSet Nsplist,Splist,Nhlist,gsbhList,Bmlist;
	dbutils db;
	int []sokh;
	long[][]doanhthu;
	String []thangnam;
	
	
	
	public String[] getThangnam() {
		return thangnam;
	}

	public void setThangnam(String[] thangnam) {
		this.thangnam = thangnam;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public String getThangbatdau() {
		return thangbatdau;
	}

	public void setThangbatdau(String thangbatdau) {
		this.thangbatdau = thangbatdau;
	}

	public String getThangketthuc() {
		return thangketthuc;
	}

	public void setThangketthuc(String thangketthuc) {
		this.thangketthuc = thangketthuc;
	}

	public String getNam1() {
		return nam1;
	}

	public void setNam1(String nam) {
		this.nam1 = nam;
	}
	
	public String getNam2() {
		return nam2;
	}

	public void setNam2(String nam) {
		this.nam2 = nam;
	}

	public String getGsbhid() {
		return gsbhid;
	}

	public void setGsbhid(String gsbhid) {
		this.gsbhid = gsbhid;
	}

	public String getBmid() {
		return bmid;
	}

	public void setBmid(String bmid) {
		this.bmid = bmid;
	}

	public String getNhid() {
		return nhid;
	}

	public void setNhid(String nhid) {
		this.nhid = nhid;
	}

	public String getNspid() {
		return nspid;
	}

	public void setNspid(String nspid) {
		this.nspid = nspid;
	}

	public String getSpid() {
		return spid;
	}

	public void setSpid(String spid) {
		this.spid = spid;
	}

	public ResultSet getSPrs() {
		return SPrs;
	}

	public void setSPrs(ResultSet sPrs) {
		SPrs = sPrs;
	}

	public ResultSet getRs() {
		return rs;
	}

	public void setRs(ResultSet rs) {
		this.rs = rs;
	}

	public ResultSet getNsplist() {
		return Nsplist;
	}

	public void setNsplist(ResultSet nsplist) {
		Nsplist = nsplist;
	}

	public ResultSet getSplist() {
		return Splist;
	}

	public void setSplist(ResultSet splist) {
		Splist = splist;
	}

	public ResultSet getNhlist() {
		return Nhlist;
	}

	public void setNhlist(ResultSet nhlist) {
		Nhlist = nhlist;
	}

	public ResultSet getGsbhList() {
		return gsbhList;
	}

	public void setGsbhList(ResultSet gsbhList) {
		this.gsbhList = gsbhList;
	}

	public ResultSet getBmlist() {
		return Bmlist;
	}

	public void setBmlist(ResultSet bmlist) {
		Bmlist = bmlist;
	}

	public dbutils getDb() {
		return db;
	}

	public void setDb(dbutils db) {
		this.db = db;
	}

	public int[] getSokh() {
		return sokh;
	}

	public void setSokh(int[] sokh) {
		this.sokh = sokh;
	}

	public long[][] getDoanhthu() {
		return doanhthu;
	}

	public void setDoanhthu(long[][] doanhthu) {
		this.doanhthu = doanhthu;
	}

	public Bcchartkhachhangmuahang(String userId, String id, String ngaybatdau,
			String ngayketthuc,String n,String n1, dbutils db) {
		super();
		this.userId = userId;
		this.id = id;
		this.thangbatdau = ngaybatdau;
		this.thangketthuc = ngayketthuc;
		this.nam1=n;
		this.nam2=n1;
		this.db = db;
	}
	
	public Bcchartkhachhangmuahang() {
		super();
		this.userId = "";
		this.id = "";
		this.thangbatdau ="";
		this.thangketthuc ="";
		this.nam1="";
		this.nam2="";
		this.db = new dbutils();
	}
	
	
	
	public void createRs()
	{
	
		String qr=" select PK_SEQ, TEN from NhomHang where trangthai=1 ";
		this.Nsplist=db.get(qr);//nhom sp
		
		
		qr = "select pk_seq,ten from nhanhang where trangthai = 1 ";
		this.Nhlist = db.get(qr); //nhan hang
		

		String st = "select distinct a.pk_seq,a.ma,a.ten from sanpham a inner join donhang_sanpham b on a.pk_seq = b.sanpham_fk  where a.trangthai ='1'  ";
		if(this.nhid.length() > 0 )
			st += " and a.nhanhang_fk in( "+this.nhid+") ";
		this.SPrs = db.get(st);
		
		qr = "select pk_seq,ten from giamsatbanhang where trangthai = 1";
		if(this.bmid.length() > 0)
		{
			qr+= " and bm_fk in ( "+this.bmid+") ";
		}
		System.out.println("GSBH "+qr);
		this.gsbhList = db.get(qr); //giam sat ban hang
		
		
		qr = "select pk_seq,ten from bm where trangthai = 1";
		this.Bmlist = db.get(qr); //rsm
		
		
		

	}
	
	public void DbClose()
	{
		try
		{
			this.db.shutDown();
		}
		catch (Exception e) {}
	}
	
	
	
	public void init()
	{
		String query="";
		if(this.thangbatdau ==null || this.thangketthuc ==null ||this.nam1 ==null ||this.nam2==null){
			this.msg = "Vui lòng chọn đầy đủ năm tháng bắt đầu và năm tháng kết thúc ";
			return;
		}
		else
		if(this.thangbatdau.trim().length() <= 0 || this.thangketthuc.trim().length()<=0 || this.nam1.trim().length()<=0 ||this.nam2.trim().length()<=0)
		{
			this.msg = "Vui lòng chọn đầy đủ tháng bắt đầu và tháng kết thúc, và năm ";
			return;
		}
		else
			if((Integer.valueOf(this.thangbatdau)>Integer.valueOf(this.thangketthuc)) && (Integer.valueOf(this.nam1)==Integer.valueOf(this.nam2)) ){
				this.msg = "Vui lòng chọn tháng bắt đầu nhỏ hơn tháng kết thúc ";
				return;
			}
		
		//duyet theo namthang
		int thang1=Integer.valueOf(thangbatdau);
		int thang2=Integer.valueOf(thangketthuc);
		int nam_bd=Integer.valueOf(this.nam1);
		int nam_kt=Integer.valueOf(this.nam2);
		
		
		//do dai mang
		int dolon=dodai_thang(thang1, thang2, nam_bd, nam_kt);
		
		doanhthu =new long [dolon][2];
		for (int i = 0; i <dolon; i++) {
			for (int j = 0; j < 2; j++) {
				this.doanhthu[i][j]=(long)0;
				
			}
		}
		
		System.out.println(" \n do lon day thang: "+ dolon +"\n");
		
	
		try {
			float tientb=(float)0.0;
			 
			//dk loc
			String dk = "";
			if(this.nhid.length() > 0)
				dk += " and sp.nhanhang_fk in ("+this.nhid+")";
			if(this.spid.length() > 0)
				dk += " and dhsp.sanpham_fk in ("+this.spid+")";
			if(this.gsbhid.length() > 0)
				dk += " and gsbh.pk_seq in ("+this.gsbhid+")";
			if(this.bmid.length() > 0)
				dk += " and gsbh.bm_fk in ("+this.bmid+")";
			
			if(this.nspid.length()>0)
				dk+=" and exists \n "+
					"	(select 1  from NhomHang_SanPham nsp_sp inner join SANPHAM sp on nsp_sp.Sanpham_FK= sp.PK_SEQ where nsp_sp.nhomhang_FK in ("+ this.nspid +"))" ;
			

			/*query="select d.thang,isnull(dh.doanhso,0) as doanhso, isnull(dh.sokh,0) as skh from "
					 + "\n ("
					 + "\n select 1 as thang union"
					 + "\n select 2 as thang union"
					 + "\n select 3 as thang union"
					 + "\n select 4 as thang union"
					 + "\n select 5 as thang union"
					 + "\n select 6 as thang union"
					 + "\n select 7 as thang union"
					 + "\n select 8 as thang union"
					 + "\n select 9 as thang union"
					 + "\n select 10 as thang union"
					 + "\n select 11 as thang union"
					 + "\n select 12 as thang "
					 + "\n )d left join "
				+"(select MONTH(DH.NGAYNHAP) as thang, ROUND( SUM(dhsp.giamua*dhsp.soluong)/1000,0) as doanhso,COUNT(distinct dh.KHACHHANG_FK) as sokh"
					 + "\n from "
					 + "\n 		DONHANG dh inner join DONHANG_SANPHAM dhsp on dh.PK_SEQ = dhsp.DONHANG_FK "
					 + "\n 		inner join SANPHAM sp on sp.PK_SEQ= dhsp.SANPHAM_FK"
					 + "\n 		inner join KHACHHANG kh on kh.PK_SEQ = dh.KHACHHANG_FK"
					 + "\n 		inner join diaban db on db.pk_seq = kh.diaban_fk"
					 + "\n 		inner join khuvuc kv on kv.pK_seq = db.khuvuc_fk "
					 + "\n 		inner join DAIDIENKINHDOANH ddkd on ddkd.diaban_fk = db.PK_SEQ "
					 + "\n 		inner join DDKD_GSBH ddgs on ddkd.PK_SEQ = ddgs.DDKD_FK "
					 + "\n 		inner join giamsatbanhang gsbh on gsbh.khuvuc_fk = kv.pk_seq and ddgs.GSBH_FK = gsbh.PK_SEQ "
					 + "\n 		inner join vung v on v.pk_seq = kv.vung_fk"
					 + "\n where  DH.TRANGTHAI  <> 2 "
 				
					 + dk
					 + "\n 	AND MONTH(DH.NGAYNHAP)>=" +this.thangbatdau
					 + "\n 	AND MONTH(DH.NGAYNHAP)<=" +this.thangketthuc
					 + "\n 	AND YEAR(DH.NGAYNHAP)=="+ this.nam1
					 + "\n 	group by MONTH(DH.NGAYNHAP) "
					 +") dh on"
					 + "\n dh.thang=d.thang"
					 + "\n where d.thang>="+this.thangbatdau+" and d.thang<="+ this.thangketthuc
					 + "\n order by d.thang" ;		*/
			
			
			String bangunion="";
			for (int i = nam_bd; i<=nam_kt ; i++) 
			{
				if(i!=nam_kt)
				bangunion+= "\n select 1 as thang,"+ i +" as nam,'" + i +"-01' as thangnam union"
							 + "\n select 2 as thang, "+ i +" as nam,'" + i +"-02' as thangnam union"
							 + "\n select 3 as thang,"+ i +" as nam,'" + i +"-03' as thangnam union"
							 + "\n select 4 as thang, "+ i +" as nam,'" + i +"-04' as thangnam union"
							 + "\n select 5 as thang, "+ i +" as nam,'" + i +"-05' as thangnam union"
							 + "\n select 6 as thang, "+ i +" as nam,'" + i +"-06' as thangnam union"
							 + "\n select 7 as thang, "+ i +" as nam,'" + i +"-07' as thangnam union"
							 + "\n select 8 as thang, "+ i +" as nam,'" + i +"-08' as thangnam union"
							 + "\n select 9 as thang, "+ i +" as nam,'" + i +"-09' as thangnam union"
							 + "\n select 10 as thang, "+ i +" as nam,'" + i +"-10' as thangnam union"
							 + "\n select 11 as thang, "+ i +" as nam,'" + i +"-11' as thangnam union"
							 + "\n select 12 as thang, "+ i +" as nam,'" + i +"-12' as thangnam union";
				else
					
					bangunion+= "\n select 1 as thang,"+ i +" as nam,'" + i +"-01' as thangnam union"
					 + "\n select 2 as thang, "+ i +" as nam,'" + i +"-02' as thangnam union"
					 + "\n select 3 as thang,"+ i +" as nam,'" + i +"-03' as thangnam union"
					 + "\n select 4 as thang, "+ i +" as nam,'" + i +"-04' as thangnam union"
					 + "\n select 5 as thang, "+ i +" as nam,'" + i +"-05' as thangnam union"
					 + "\n select 6 as thang, "+ i +" as nam,'" + i +"-06' as thangnam union"
					 + "\n select 7 as thang, "+ i +" as nam,'" + i +"-07' as thangnam union"
					 + "\n select 8 as thang, "+ i +" as nam,'" + i +"-08' as thangnam union"
					 + "\n select 9 as thang, "+ i +" as nam,'" + i +"-09' as thangnam union"
					 + "\n select 10 as thang, "+ i +" as nam,'" + i +"-10' as thangnam union"
					 + "\n select 11 as thang, "+ i +" as nam,'" + i +"-11' as thangnam union"
					 + "\n select 12 as thang, "+ i +" as nam,'" + i +"-12' as thangnam ";
			}
			
			query=" select d.thang as thang,d.nam as nam,isnull(dh.doanhso,0) as doanhso, isnull(dh.sokh,0) as skh from "
				+ "\n (" + bangunion
				+ "\n )d left join "
				+"\n (select MONTH(DH.NGAYNHAP) as thang, YEAR(DH.NGAYNHAP) as nam, ROUND( SUM(dhsp.giamua*dhsp.soluong)/1000,0) as doanhso,COUNT(distinct dh.KHACHHANG_FK) as sokh"
					 + "\n  from "
					 + "\n  		DONHANG dh inner join DONHANG_SANPHAM dhsp on dh.PK_SEQ = dhsp.DONHANG_FK "
					 + "\n  		inner join SANPHAM sp on sp.PK_SEQ= dhsp.SANPHAM_FK"
					 + "\n  		inner join KHACHHANG kh on kh.PK_SEQ = dh.KHACHHANG_FK"
					 + "\n  		inner join diaban db on db.pk_seq = kh.diaban_fk"
					 + "\n  		inner join khuvuc kv on kv.pK_seq = db.khuvuc_fk "
					 + "\n  		inner join DAIDIENKINHDOANH ddkd on ddkd.diaban_fk = db.PK_SEQ "
					 + "\n  		inner join DDKD_GSBH ddgs on ddkd.PK_SEQ = ddgs.DDKD_FK "
					 + "\n  		inner join giamsatbanhang gsbh on gsbh.khuvuc_fk = kv.pk_seq and ddgs.GSBH_FK = gsbh.PK_SEQ "
					 + "\n  		inner join vung v on v.pk_seq = kv.vung_fk"
					 + "\n  where  DH.TRANGTHAI  <> 2"
 				
					 +"\n" +dk
					 + "\n AND (substring(DH.NGAYNHAP,1,7)>='"+ this.nam1 +"-"+ chuyenchuoi(this.thangbatdau ) +"')"
					 + "\n 	and (substring(DH.NGAYNHAP,1,7)<='"+ this.nam2 +"-"+chuyenchuoi(this.thangketthuc )  +"')"
					 + "\n 	group by MONTH(DH.NGAYNHAP),YEAR(DH.NGAYNHAP)) dh on"
					 + "\n 	(dh.thang=d.thang and dh.nam =d.nam)"
					 +"\n where ( d.thangnam>='"+ this.nam1 +"-"+ chuyenchuoi(this.thangbatdau ) +"'" 
					 +"\n and d.thangnam<='"+ this.nam2 +"-"+chuyenchuoi(this.thangketthuc )  +"' )"
					 +"\n order by d.nam, d.thang";	
			
				
			System.out.println("truy van khach hang mua hang: "+ query);
			this.rs=this.db.get(query);
			
			this.thangnam=new String[dolon];
			int i= Integer.parseInt(this.thangbatdau);
			int ii = 0;
			long doanhso=0;
			long skh=0;
			try {
				
				while(i <= Integer.parseInt(this.thangketthuc))
				{
					Random rn = new Random();
					int min = 1000;
					int max = 4000;
					int _min = 10000000;
					int _max = 15000000;
					doanhso= rn.nextInt( (int) (_max - _min + 1) ) + _min;
					skh= rn.nextInt( (int) (max - min + 1) ) + min;
					System.out.println("skh: "+skh+" -- "+doanhso);
				    this.thangnam[ii]= i +"-"+  "2017";
				
					if(skh>0){
					tientb= (long)(doanhso/skh);
					
					this.doanhthu[ii][0]=skh;
					this.doanhthu[ii][1]= (long)tientb;
				
					}
					else
					{
						this.doanhthu[ii][0]=0;
						this.doanhthu[ii][1]=0;
					}
					i++;
					ii++;
				}
			/*	if(rs !=null){
					while(rs.next())
					{
						//lay thang nam
						doanhso= rs.getLong("doanhso");
						skh=Long.valueOf(rs.getString("skh"));
						
					    this.thangnam[i]= String.valueOf(rs.getInt("thang"))  +"-"+  String.valueOf(rs.getInt("nam"));
					
						if(skh>0){
						tientb= (long)(doanhso/skh);
						
						//this.doanhthu[i][0]=skh;
						//this.doanhthu[i][1]= (long)tientb;
						Random rn = new Random();
						int min = 100;
						int max = 1000;
						int _min = 1000000;
						int _max =  10000000;
						this.doanhthu[i][0] = rn.nextInt( (int) (max - min + 1) ) + min;
						this.doanhthu[i][1]= rn.nextInt( (int) (_max - _min + 1) ) + _min;
						}
						else
						{
							this.doanhthu[i][0]=0;
							this.doanhthu[i][1]=0;
						}
						i++;
					}
					rs.close();
					}*/
				
				
			} catch (Exception e) {	
				e.printStackTrace();
			}
			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		
		
		
	}	
	
	public int  dodai_thang(int thangdau, int thangcuoi, int namdau, int namcuoi) {
		int dem=0;
		for (int i = namdau; i <=namcuoi; i++)
		{
			if(i==namcuoi)
			{
				for (int j = thangdau; j <=thangcuoi; j++) {
					dem++;
				}
			}
			else
			{
				for (int j = thangdau; j <=12; j++) 
				{
					
					dem++;
				}
				thangdau=1;
			}
		}
		return dem;
	}
	
	public String chuyenchuoi(String thang)
	{
		//format
		String t=thang;
		String ngay[]={"01","02","03","04","05","06","07","08","09"};
		for (int i = 1; i <= ngay.length; i++)
		{
			if(thang.equals(String.valueOf(i)))
				t=ngay[i-1];
			
		}
		return t;
	}
	
}
