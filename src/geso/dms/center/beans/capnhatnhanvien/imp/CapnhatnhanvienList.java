package geso.dms.center.beans.capnhatnhanvien.imp;

import java.sql.ResultSet;
import java.sql.SQLException;

import geso.dms.center.beans.capnhatnhanvien.ICapnhatnhanvienList;
import geso.dms.center.db.sql.dbutils;

public class CapnhatnhanvienList implements ICapnhatnhanvienList{
   String Ten;
   String Ngaysinh;
   String Tungay;
   String Denngay;
   ResultSet DSNV;
   String userId;
   String Trangthai;
   String Phanloai;
   String msg;
   dbutils db;
	public CapnhatnhanvienList()
	{   this.Ten = "";
	    this.Ngaysinh ="";
	    this.Tungay = "";
	    this.Denngay ="";
	    this.Trangthai="";
	    this.Phanloai ="";
	    this.msg ="";
	    db = new dbutils();
	}
	public void setTen(String Ten) {
		this.Ten = Ten;
		
	}

	public String getTen() {
			return this.Ten;
	}

	
	public void setNgaysinh(String Ngaysinh) {
		this.Ngaysinh = Ngaysinh;
		
	}

	public String getNgaysinh() {
		
		return this.Ngaysinh;
	}

	public void setTungay(String Tungay) {
		this.Tungay = Tungay;
		
	}

	public String getTungay() {
		
		return this.Tungay;
	}

	
	public void setDenngay(String Denngay) {
		
		this.Denngay = Denngay;
	}

	
	public String getDenngay() {
		
		return this.Denngay;
	}

	public ResultSet getDSNV() {
		
		return this.DSNV;
	}

	public void init(String st) {
		
		String sql="";
		if(st.length()>0)
		{
	    	sql = "select a.pk_seq,a.Ten,a.dangnhap,a.email,a.dienthoai,a.trangthai,a.phanloai,b.ten as nguoitao1,c.ten as nguoisua1,a.ngaytao,a.ngaysua " +
	    		  "from nhanvien a inner join nhanvien b on b.pk_seq = a.nguoitao inner join nhanvien c on c.pk_seq = a.nguoisua where "+ st;
		}
		else
			sql ="select a.pk_seq,a.Ten,a.dangnhap,a.email,a.dienthoai,a.trangthai,a.phanloai,b.ten as nguoitao1,c.ten as nguoisua1,a.ngaytao,a.ngaysua " +
				 "from nhanvien a inner join nhanvien b on b.pk_seq = a.nguoitao inner join nhanvien c on c.pk_seq = a.nguoisua ";
			// sql = "select a.pk_seq,a.Ten,a.dangnhap,a.email,a.dienthoai,a.trangthai,a.phanloai,b.ten as nguoitao1,c.ten as nguoisua1,a.ngaytao,a.ngaysua from nhanvien a,nhanvien b,nhanvien c where a.pk_seq = b.pk_seq and a.pk_seq = c.pk_seq and a.trangthai ='1' or a.trangthai ='0'";
		
			
		System.out.print("chuoi: "+ sql);
		this.DSNV = db.get(sql);
	}
	
	public void setuserId(String userId) {
	    this.userId = userId;
	}

	public String getuserId() {
		return this.userId;
	}
	
	public void setTrangthai(String Trangthai) {
		this.Trangthai = Trangthai;
		
	}
	public String getTrangthai() {
	
		return this.Trangthai;
	}
	public void setPhanloai(String Phanloai) {
		this.Phanloai = Phanloai;
	}
	
	public String getPhanloai() {
		
		return this.Phanloai;
	}
	
	public boolean xoa(String Id) {
		String sql ="select count(*) as num from donhang where nguoitao ='"+ Id +"' or  nguoisua ='"+ Id +"'";
		ResultSet rs = db.get(sql);
		try {
			rs.next();
			if(!rs.getString("num").equals("0"))
			{
			  this.msg ="khong the xoa duoc ";
			  return false;
			}
			rs.close();
			sql ="select count(*) as num from ctkhuyenmai where nguoitao ='"+ Id +"' or  nguoisua ='"+ Id +"'";
				ResultSet tg = db.get(sql);
				tg.next();
			if(!tg.getString("num").equals("0"))
			{
				  this.msg ="khong the xoa duoc ";
				  return false;
			}
			/*if(xoa1(Id))
			{
				sql ="delete from nhanvien where pk_seq ='"+ Id +"'";
				if(!db.update(sql))
				{
					  this.msg ="khong the xoa duoc ";
					  return false;
				}
			}*/
			sql ="update nhanvien set trangthai =0, nguoisua = null,ngaysua=convert(varchar(10),getdate(),120) where pk_seq ='"+ Id +"'";
			if(!db.update(sql))
			{
				  this.msg ="khong the xoa duoc ";
				  return false;
			}
			
			if(tg!=null){
				tg.close();
			}
		} catch(Exception e) {
			
			e.printStackTrace();
		}
		
		return false;
	}
	boolean xoa1(String Id)
	{
		try
		{
			this.db.getConnection().setAutoCommit(false);
			String sql = "delete from phanquyen where nhanvien_fk ='"+Id +"'";
			if(!db.update(sql))
			{
				this.msg = sql;
				this.db.getConnection().rollback();
				return false;
			}
			System.out.println(sql);
			sql ="delete from phamvihoatdong where nhanvien_fk ='"+ Id+"'";
			
			if(!db.update(sql))
			{
				this.msg = sql;
				this.db.getConnection().rollback();
				return false;
			}
			System.out.println(sql);
			sql ="delete from nhanvien_kenh where nhanvien_fk ='"+ Id +"'";
			
			if(!db.update(sql))
			{
				this.msg = sql;
				this.db.getConnection().rollback();
				return false;
			}
			System.out.println(sql);
			sql ="delete from nhanvien_sanpham where nhanvien_fk ='"+ Id +"'";
			
			if(!db.update(sql))
			{
				this.msg = sql;
				this.db.getConnection().rollback();
				return false;
			}
			System.out.println(sql);
			this.db.getConnection().commit();
			return true;
		}
		catch(Exception ex)
		{
			ex.printStackTrace();
			try { this.db.getConnection().rollback(); } 
			catch (SQLException e) { e.printStackTrace(); }
			return false;
		}
		finally{ try { this.db.getConnection().setAutoCommit(true); } catch (SQLException e) { e.printStackTrace(); } }
	}
	
	public void setmsg(String msg) {}
	
	public String getmsg() 
	{ 
		return null;
	}
	@Override
	public void DbClose() {
		// TODO Auto-generated method stub
	try{
		if(DSNV!=null)
			DSNV.close();
		db.shutDown();
	}catch(Exception er){
		
	}
	}

}
