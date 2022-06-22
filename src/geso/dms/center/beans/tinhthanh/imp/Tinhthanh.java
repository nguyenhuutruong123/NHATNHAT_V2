package geso.dms.center.beans.tinhthanh.imp;

import geso.dms.center.beans.tinhthanh.ITinhthanh;
import geso.dms.center.db.sql.Idbutils;
import geso.dms.center.db.sql.dbutils;
import geso.dms.center.util.Utility;

import java.io.Serializable;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Tinhthanh implements ITinhthanh, Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	String userId;
	String id;
	String ten;
	String vungId;
	String msg;
	String ma = "";
	String isDisplay = "0";
	ResultSet vungRs;
	
	dbutils db;

	public String getIsDisplay() {
		return isDisplay;
	}
	public void setIsDisplay(String isDisplay) {
		this.isDisplay = isDisplay;
	}
	
	public Tinhthanh(String userId) {
		this.userId = userId;
		this.id = "";
		this.ten = "";
		this.vungId = "";
		this.msg = "";
		this.db = new dbutils();
	}

	public String getMa() {
		return ma;
	}
	public void setMa(String ma) {
		this.ma = ma;
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

	public String getTen() {
		return ten;
	}

	public void setTen(String ten) {
		this.ten = ten;
	}

	public String getVungId() {
		return vungId;
	}

	public void setVungId(String vungId) {
		this.vungId = vungId;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public ResultSet getVungRs() {
		return vungRs;
	}

	public void setVungRs(ResultSet vungRs) {
		this.vungRs = vungRs;
	}

	public dbutils getDb() {
		return db;
	}
	
	public void init() {
		String query = "SELECT MA,TEN, isnull(khuvuc_fk,-1)  khuvuc_fk FROM dbo.TINHTHANH WHERE PK_SEQ = " + this.id;
		System.out.println("TINHTHANH: " + query);
		ResultSet rs = this.db.get(query);
		if (rs != null) {
			try {
				rs.next();
				this.ten = rs.getString("TEN");
				this.vungId = rs.getString("khuvuc_fk");
				this.ma = rs.getString("MA");
				rs.close();
			} catch (SQLException ex) {
				ex.printStackTrace();
			}
		}
	}
	
	public void createRs() {
		String query = "SELECT PK_SEQ, diengiai as TEN FROM dbo.khuvuc";
		this.vungRs = this.db.get(query);
		
	}
	
	
	public int checkdatontai (Idbutils db, String id ,String colum ,String value ) throws SQLException
	{
		String query = " select count(*) sd from tinhthanh where "+colum+" = N'"+value+"'  and pk_seq != " + id;
		ResultSet rs = db.get(query);
		rs.next();
		int kq = rs.getInt("sd");
		rs.close();
		return kq;
	}
	public boolean createTinh() {
		
		try
		{
			db.getConnection().setAutoCommit(false);
			
			String vung = "null";
			if(this.vungId == null || this.vungId.trim().length() == 0)
			{
				this.msg = "Vui lòng chọn khu vực";
				db.getConnection().rollback();
				return false;
			}
				
			
			if(this.ma == null || this.ma.trim().length() <=0)
			{
				this.msg = "Vui lòng Nhập Mã";
				db.getConnection().rollback();
				return false;
			}
			if(this.ten == null || this.ten.trim().length() <=0)
			{
				this.msg = "Vui lòng nhập Tên";
				db.getConnection().rollback();
				return false;
			}
			this.ma = this.ma.trim();	
			this.ten = this.ten.trim();
			
			String query = "";
			
			
			int checkMa =  checkdatontai ( db, "0" ,"ma",this.ma );
			if(checkMa > 0)
			{
				this.msg = "Mã tỉnh đã tồn tại!";
				db.getConnection().rollback();
				return false;
			}
			
			int checkTen =  checkdatontai ( db, "0" ,"ten",this.ten );
			if(checkTen > 0)
			{
				this.msg = "Tên tỉnh đã tồn tại!";
				db.getConnection().rollback();
				return false;
			}
			
			query =  "\n INSERT INTO dbo.TINHTHANH( ma,ten,ngaytao,ngaysua,nguoitao,nguoisua,khuvuc_fk,vung_fk  ) " +
							"\n VALUES (N'"+this.ma+"',N'" + this.ten + "',convert(char(10),dbo.getlocaldate(default),126),convert(char(10),dbo.getlocaldate(default),126), " + this.userId + ", " + this.userId + ","+this.vungId+",(select top(1) vung_fk from khuvuc where pk_seq="+this.vungId+" )) ";
				System.out.println("INSERT: " + query);
			if (db.updateReturnInt(query)<= 0 ) 
			{
				this.msg = "Lỗi không thể tạo tỉnh thành!";
				db.getConnection().rollback();
				return false;
			}
			query =  "\n insert Log_tinhThanh(tinhthanh_fk,ma,ten,thoidiem,nguoisua) " +
					 " select pk_seq,ma,ten,ngaysua,nguoisua from TINHTHANH where pk_seq = scope_identity() ";
			if (db.updateReturnInt(query)<= 0 ) 
			{
				this.msg = "Không thể thêm tỉnh thành!"+query;
				db.getConnection().rollback();
				return false;
			}
			
			db.getConnection().commit();
			db.getConnection().setAutoCommit(true);
			return true;
		}
		catch(Exception e)
		{
			Utility.rollback_throw_exception(db);
			this.msg = "Exception:" + e.getMessage();
			return false;
			
		}
		
		
	}
	
	public boolean updateTinh() {
		
		
		try
		{
			db.getConnection().setAutoCommit(false);
			
			String vung = "null";
			if(this.vungId == null || this.vungId.trim().length() == 0)
			{
				this.msg = "Vui lòng Nhập khu vực";
				db.getConnection().rollback();
				return false;
			}
			else
				vung=this.vungId;
				
			
			if(this.ma == null || this.ma.trim().length() <=0)
			{
				this.msg = "Vui lòng Nhập Mã";
				db.getConnection().rollback();
				return false;
			}
			if(this.ten == null || this.ten.trim().length() <=0)
			{
				this.msg = "Vui lòng nhập Tên";
				db.getConnection().rollback();
				return false;
			}
			this.ma = this.ma.trim();	
			this.ten = this.ten.trim();
			
			String query = "";
			
			
			int checkMa =  checkdatontai ( db, this.id,"ma",this.ma );
			if(checkMa > 0)
			{
				this.msg = "Mã tỉnh đã tồn tại!";
				db.getConnection().rollback();
				return false;
			}
			
			int checkTen =  checkdatontai ( db, this.id,"ten",this.ten );
			if(checkTen > 0)
			{
				this.msg = "Tên tỉnh đã tồn tại!";
				db.getConnection().rollback();
				return false;
			}
			
			query = "UPDATE dbo.TINHTHANH SET TEN = N'" + this.ten + "',ma = N'"+this.ma+"', khuvuc_fk="+vung+",VUNG_FK = (select top(1) vung_fk from khuvuc where pk_seq="+vung+" ), "
						+ "\n	NGAYSUA = convert(char(10),dbo.getlocaldate(default),126), NGUOISUA = " + this.userId + " "
						+ "\nWHERE PK_SEQ = " + this.id;

			if (db.updateReturnInt(query)<= 0 ) 
			{
				this.msg = "Lỗi không thể cập nhật tỉnh thành!";
				db.getConnection().rollback();
				return false;
			}
			query =  "\n insert Log_tinhThanh(tinhthanh_fk,ma,ten,thoidiem,nguoisua) " +
					 " select pk_seq,ma,ten,ngaysua,nguoisua from TINHTHANH where pk_seq = " + this.id;
			if (db.updateReturnInt(query)<= 0 ) 
			{
				this.msg = "Không thể ghi thêm tỉnh thành!";
				db.getConnection().rollback();
				return false;
			}
			
			db.getConnection().commit();
			db.getConnection().setAutoCommit(true);
			return true;
		}
		catch(Exception e)
		{
			Utility.rollback_throw_exception(db);
			this.msg = "Exception:" + e.getMessage();
			return false;
			
		}
		
		
	}
	
	private String getDate() {
	    DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
	    Date date = new Date();
	    return dateFormat.format(date);	
	}
}
