package geso.dms.center.beans.bcchartdoanhthursm.imp;

import geso.dms.center.beans.bcchartdoanhthursm.IBcchartdoanhthursm;
import geso.dms.center.db.sql.dbutils;
import geso.dms.center.util.Phan_Trang;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Random;

import org.eclipse.jdt.internal.compiler.ast.MagicLiteral;


public class Bcchartdoanhthursm extends Phan_Trang implements IBcchartdoanhthursm{
	private static final long serialVersionUID = 1L;
	String userId;
	String id;
	String nam;
	String thang;
	String mangtenNSP[];	
	String mangidNSP[];
	String Thangrsm2dl[];
	String ThangTbDL[];
	Long DoanhthuTB[];
	Long DoanhthuRsm1[];
	Long DoanhthuRsm2[];
	Long Sokhrsm1[];
	Long Sokhrsm2[];
	
	ResultSet rs;
	ResultSet Rsm2rs;
	ResultSet Doanhthutbrs;
	String msg;
	ResultSet RsmRs;
	String rsmId1 = "";
	String rsmId2 = "";
	ResultSet rs1;
	ResultSet rs2;
	dbutils db;

	public Bcchartdoanhthursm()
	{
		this.userId = "";
		this.id = "";
		this.msg = "";
		Calendar cal = Calendar.getInstance();
		int year_ = cal.get(Calendar.YEAR);
		this.nam=year_+"";
		int thang_=cal.get(Calendar.MONTH)+1;		
		this.thang=thang_+"";
		this.db = new dbutils();
		createRs();
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



	public void init()
	{
		String query="";
		if(this.nam.trim().length() <= 0)
		{
			this.msg = "Vui lòng chọn năm";
			return;
		}
		
		if(this.thang.trim().length() <= 0)
		{
			this.msg = "Vui lòng chọn tháng";
			return;
		}
		String date;
		if(Integer.parseInt(thang)<10)
			date = nam+"-0"+thang;
		else
			date = nam+"-"+thang;
		
		// LẤY ARR NHÓM SẢN PHẦM
//		query="SELECT distinct PK_SEQ,TEN  FROM NHOMSANPHAM";
//		
//		rs=this.db.get(query);		
		int ngay = Integer.parseInt(getDateTime().substring(8,10));
		int thang = Integer.parseInt(getDateTime().substring(5,7));
		int nam = Integer.parseInt(getDateTime().substring(0,4));
		System.out.println("ngay "+ngay +" "+thang +" "+nam);			
		if(this.thang.length() > 0)
		if(Integer.parseInt(this.thang) > thang && Integer.parseInt(this.nam) > nam)
		{
			this.msg = "Tháng chưa có số liệu ! Vui lòng chọn lại !";
			return ;
		}
		else
		{	if(  Integer.parseInt(this.nam) <= nam)
			if(this.thang.equals("1") || this.thang.equals("3") || this.thang.equals("5") || this.thang.equals("7") || this.thang.equals("8") || this.thang.equals("10") || this.thang.equals("12"))
			{
				ngay = 31;
			}
			else
				if(this.thang.equals("4") || this.thang.equals("6") || this.thang.equals("9") || this.thang.equals("11"))
				{
					ngay = 30;
				}
				else
				{
					if(this.thang.equals("2"))
					if(( Integer.parseInt(this.nam)%4==0 &&  Integer.parseInt(this.nam)%100 != 0)||( Integer.parseInt(this.nam)%400 == 0))
					{
						ngay = 29;
					}
					else
						ngay = 28;
				}
		}
		System.out.println("ngay trong thang: "+ngay);
		ngay = ngay + 1;
		
		mangidNSP=new String[ngay];
		mangtenNSP=new String[ngay];
		Thangrsm2dl =  new String[ngay];
		DoanhthuRsm1 = new Long[ngay];
		DoanhthuRsm2 = new Long[ngay];
		DoanhthuTB = new Long[ngay];
		Sokhrsm1 = new Long[ngay];
		Sokhrsm2 = new Long[ngay];
		
		System.out.println("Ngay "+ngay);
		for(int i = 1; i < ngay ; i++)
		{
			mangtenNSP[i] = i+"";
			DoanhthuRsm1[i] = (long) 0;
			DoanhthuRsm2[i] = (long) 0;
		}
	
		// LẤY BÁO CÁO
		if(this.rsmId1.length() > 0)
		{
			query  = "select a.NGAYNHAP,count( DISTINCT a.KHACHHANG_fk) as sokh, Round(sum(b.SOLUONG * b.GIAMUA)/1000000.0,0) as tongdoanhthu "+
					" from DONHANG a inner join DONHANG_SANPHAM b on a.PK_SEQ = b.DONHANG_FK "+
					" where BM_FK = '"+this.rsmId1+"' and SUBSTRING(a.NGAYNHAP,1,7) = '"+date+"' and a.trangthai <> '2' "+
					" group by NGAYNHAP "+
					" order by NGAYNHAP ";
			System.out.println("Doanh thu theo rsm 1: "+query);
			String sql = "select N'Doanh thu Rsm1' as type, ";
			this.rs = db.get(query);
			try {
				int i = 1;
				//Random
				while( i < 13)
				{
					// chuyển cột thành dòng
					
					mangidNSP[i] = i+"";
					
					// số kh trong 1 ngày của RSM
					System.out.println("mang "+mangidNSP[i]);
					Random rn = new Random();
					Sokhrsm1[i] = (long)(rn.nextInt(300))+1;

					// lưu lại doanh thu của RSM1 dùng để tính giá trị tb doanh thu
					DoanhthuRsm1[i] = (long)(rn.nextInt(200))+1;;
					i++;
				}
				/*while(rs.next())
				{
					// chuyển cột thành dòng
					
					mangidNSP[i] = Integer.parseInt(rs.getString("ngaynhap").substring(8,10))+"";
					
					// số kh trong 1 ngày của RSM
					System.out.println("mang "+mangidNSP[i]);
					Sokhrsm1[Integer.parseInt(rs.getString("ngaynhap").substring(8,10))] = rs.getLong("sokh");
					query = ""+rs.getLong("tongdoanhthu")+" as '"+mangidNSP[i]+"' , ";
					sql += query;

					// lưu lại doanh thu của RSM1 dùng để tính giá trị tb doanh thu
					DoanhthuRsm1[Integer.parseInt(rs.getString("ngaynhap").substring(8,10))] = rs.getLong("tongdoanhthu");
					System.out.println("Thang "+mangidNSP[i]+" doanh thu: "+rs.getLong("tongdoanhthu"));
					i++;
				}*/
				if(sql.length() > 30)
					sql = sql.substring(0,sql.length() - 2);
				System.out.println("Doanh thu rsm1 "+sql);
				if(rs != null) rs.close();
			} catch (SQLException e) {
				
				e.printStackTrace();
			}
			if(sql.length() > 30)
			{
				this.rs=this.db.getScrol(sql);
			}
			
		}
		
		if(this.rsmId2.length() > 0)
		{
			query  = "select a.NGAYNHAP,count( DISTINCT a.KHACHHANG_fk) as sokh,  Round(sum(b.SOLUONG * b.GIAMUA)/1000000.0,0) as tongdoanhthu "+
					" from DONHANG a inner join DONHANG_SANPHAM b on a.PK_SEQ = b.DONHANG_FK "+
					" where BM_FK = '"+this.rsmId2+"' and SUBSTRING(a.NGAYNHAP,1,7) = '"+date+"'and a.trangthai <> '2' "+
					" group by NGAYNHAP "+
					" order by NGAYNHAP ";
			System.out.println("Doanh thu theo rsm 2: "+query);
			String sql = "select N'Doanh thu Rsm2' as type, ";
			this.Rsm2rs = db.get(query);
			try {
				int i = 1;
				while( i < 13)
				{
					Random rn = new Random();
					Sokhrsm2[i] = (long)(rn.nextInt(300))+1;

					// lưu lại doanh thu của RSM1 dùng để tính giá trị tb doanh thu
					DoanhthuRsm2[i] = (long)(rn.nextInt(200))+1;;
					i++;
				}
				/*while(Rsm2rs.next())
				{
					
					// số kh trong 1 ngày của RSM
					Sokhrsm2[Integer.parseInt(Rsm2rs.getString("ngaynhap").substring(8,10))] = Rsm2rs.getLong("sokh");
					
					// chuyển cột thành dòng
					Thangrsm2dl[i] = Integer.parseInt(Rsm2rs.getString("ngaynhap").substring(8,10))+"";
					query = ""+Rsm2rs.getLong("tongdoanhthu")+" as '"+Thangrsm2dl[i]+"' , ";
					sql += query;
					// đánh dấu các ngày có doanh thu
					
					
					// lưu lại doanh thu rsm2 để tính giá trị trung bình
					DoanhthuRsm2[Integer.parseInt(Rsm2rs.getString("ngaynhap").substring(8,10))] = Rsm2rs.getLong("tongdoanhthu");
					System.out.println("Thang "+Thangrsm2dl[i]+" doanh thu: "+Rsm2rs.getLong("tongdoanhthu"));
					i++;
				}*/
				if(sql.length() > 30)
					sql = sql.substring(0,sql.length() - 2);
				System.out.println("Doanh thu rsm2 "+sql);
				if(Rsm2rs != null) Rsm2rs.close();
			} catch (SQLException e) {
				
				e.printStackTrace();
			}
			if(sql.length() > 30)
			{
				this.Rsm2rs=this.db.getScrol(sql);
			}
			
		}
//		// Tính giá trị trung bình doanh thu 2 Rsm
//		for(int i = 1; i < ngay; i++)
//		{
//			DoanhthuTB[i] = (DoanhthuRsm1[i]+DoanhthuRsm2[i])/2;
// 		}
		
	}

	public void createRs()
	{
		this.RsmRs = db.getScrol("select * from bm");	
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

	public String[] getArrTenNSP() {
		return this.mangtenNSP;
	}

	public String[] getArrIDNSP() {
		return this.mangidNSP;
	}

	public ResultSet getRs() {
		return this.rs;
	}




	public ResultSet getRsmRs() {

		return this.RsmRs;
	}




	public void setRsmRs(ResultSet RmsRs) {

		this.RsmRs = RmsRs;
	}




	public String getRsmId1() {

		return this.rsmId1;
	}




	public void setRsmId1(String RmsId1) {

		this.rsmId1 = RmsId1;
	}




	public String getRsmId2() {

		return this.rsmId2;
	}




	public void setRsmId2(String RmsId2) {

		this.rsmId2 = RmsId2;
	}
	private String getDateTime() {
		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		Date date = new Date();
		return dateFormat.format(date);
	}




	public ResultSet getDoanhthursm1Rs() {
	
		return this.rs1;
	}




	public ResultSet getDoanhthursm2Rs() {
	
		return this.rs2;
	}




	public String[] getThangrms2DL() {
	
		return this.Thangrsm2dl;
	}




	public String[] getThangTBDL() {
	
		return this.ThangTbDL;
	}



	
	public ResultSet getRms2Rs() {
	
		return this.Rsm2rs;
	}



	
	public ResultSet getDoanhthuTBRs() {
	
		return this.Doanhthutbrs;
	}







	public Long[] getDoanhthuTB() {
		
		return this.DoanhthuTB;
	}




	public Long[] getDoanhthuRsm1() {
		
		return this.DoanhthuRsm1;
	}




	public Long[] getDoanhthuRsm2() {
		
		return this.DoanhthuRsm2;
	}




	public Long[] getSoKHRsm1() {
		
		return this.Sokhrsm1;
	}




	public Long[] getSoKHRsm2() {
		
		return this.Sokhrsm2;
	}
	

}
