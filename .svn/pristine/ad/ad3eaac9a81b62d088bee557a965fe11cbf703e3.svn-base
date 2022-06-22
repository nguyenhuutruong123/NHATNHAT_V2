package geso.dms.center.beans.quanhuyen.imp;

import geso.dms.center.beans.quanhuyen.IQuanhuyen;
import geso.dms.center.db.sql.dbutils;

import java.io.Serializable;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Quanhuyen implements IQuanhuyen, Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 762664216672694598L;

	String userId;
	String id;
	String ten;
	String ma;
	String tinhId;
	String msg;
	String type;
	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	ResultSet tinhRs;
	
	dbutils db;

	public Quanhuyen(String userId) {
		this.userId = userId;
		this.id = "";
		this.ten = "";
		this.ma = "";
		this.tinhId = "";
		this.msg = "";
		this.type="";
		this.db = new dbutils();
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

	public String getTinhId() {
		return tinhId;
	}

	public void setTinhId(String tinhId) {
		this.tinhId = tinhId;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public ResultSet getTinhRs() {
		return tinhRs;
	}

	public void setTinhRs(ResultSet tinhRs) {
		this.tinhRs = tinhRs;
	}

	public dbutils getDb() {
		return db;
	}
	
	public void init() {
		String query = "SELECT PK_SEQ, ISNULL(MA, '') MA, TEN, TINHTHANH_FK FROM dbo.QUANHUYEN WHERE PK_SEQ = " + this.id;
		System.out.println("quanhuyen: " + query);
		ResultSet rs = this.db.get(query);
		if (rs != null) {
			try {
				rs.next();
				this.ma = rs.getString("MA");
				this.ten = rs.getString("TEN");
				this.tinhId = rs.getString("TINHTHANH_FK");
				rs.close();
			} catch (SQLException ex) {
				ex.printStackTrace();
			}
		}
	}
	
	public void createRs() {
		String query = "SELECT PK_SEQ, TEN FROM dbo.TINHTHANH ";
		System.out.println("tinh: " + query);
		this.tinhRs = this.db.get(query);
	}
	
	public boolean createQuanhuyen() {
		String query = "INSERT INTO QUANHUYEN (MA, TEN, TINHTHANH_FK, NGAYTAO, NGAYSUA, NGUOITAO, NGUOISUA) "
			+ "\nVALUES('"+ this.ma +"', N'" + this.ten + "', " + this.tinhId + ", '" + getDate() + "', '" + getDate() + "', "
			+ "\n	" + this.userId + ", " + this.userId + ") ";
		System.out.println("INSERT: " + query);
		if (!this.db.update(query)) {
			this.msg = "Exception";
			return false;
		}
		String id = "";
		ResultSet rs = this.db.get(" SELECT SCOPE_IDENTITY() AS ID ");
		try 
		{
			rs.next();
			id = rs.getString("ID");
			rs.close();
		} catch (SQLException e) { e.printStackTrace(); }
		
		query = "UPDATE QUANHUYEN SET TIMKIEM =UPPER(DBO.FTBODAU(ISNULL(MA,'')))+' '+UPPER(DBO.FTBODAU(ISNULL(TEN,''))) WHERE PK_SEQ = '"+ id +"'";
		if (!this.db.update(query)) {
			this.msg = "Exception";
			return false;
		}
		return true;
	}
	
	public boolean updateQuanhuyen() {
		String query = "UPDATE dbo.QUANHUYEN "
			+ "\n	SET MA = '"+ this.ma +"', TEN = N'" + this.ten + "', TINHTHANH_FK = " + this.tinhId + ", "
			+ "\n	NGAYSUA = '" + getDate() + "', "
			+ "\n	NGUOISUA = " + this.userId + ""
			+ "\nWHERE PK_SEQ = " + this.id + " ";
		System.out.println("UPDATE: " + query);
		if (!this.db.update(query)) {
			this.msg = "Không thể cập nhật.";
			return false;
		}
		
		query = "UPDATE QUANHUYEN SET TIMKIEM =UPPER(DBO.FTBODAU(ISNULL(MA,'')))+' '+UPPER(DBO.FTBODAU(ISNULL(TEN,''))) WHERE PK_SEQ = '"+ this.id +"'";
		if (!this.db.update(query)) {
			this.msg = "Exception.";
			return false;
		}
		
		return true;
	}
	
	public String deleteQuanhuyen(String id) {
		
		
		String qu=" select count(*) as sl from PhuongXa where quanhuyen_fk= "+id;
		ResultSet rs=db.get(qu);
		try {
			rs.next();
			int sl=rs.getInt("sl");
			rs.close();
			if(sl>0)
			{
				this.msg = "Đã tồn tại Phường xã không thể xóa";
				return this.msg;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		String query = "delete from QUANHUYEN where pk_seq="+id;
		if (!this.db.update(query)) {
			this.msg = "Không thể cập nhật.";
			return this.msg;
		}
		
		
		
		return "";
	}
	
	private String getDate() {
	    DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
	    Date date = new Date();
	    return dateFormat.format(date);	
	}

	@Override
	public String getMa() {
		// TODO Auto-generated method stub
		return this.ma;
	}

	@Override
	public void setMa(String ma) {
		// TODO Auto-generated method stub
		this.ma = ma;
	}
}
