package geso.dms.center.beans.quanhuyen.imp;

import geso.dms.center.beans.quanhuyen.IQuanhuyenList;
import geso.dms.center.db.sql.dbutils;

import java.io.Serializable;
import java.sql.ResultSet;

public class QuanhuyenList implements IQuanhuyenList, Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4020793725477302436L;
	
	String ten;
	String tinhId;
	
	ResultSet quanhuyenRs;
	ResultSet tinhthanhRs;
	
	dbutils db;

	public QuanhuyenList() {
		this.ten = "";
		this.tinhId = "";
		this.db = new dbutils();
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

	public ResultSet getQuanhuyenRs() {
		return quanhuyenRs;
	}

	public void setQuanhuyenRs(ResultSet quanhuyenRs) {
		this.quanhuyenRs = quanhuyenRs;
	}

	public ResultSet getTinhthanhRs() {
		return tinhthanhRs;
	}

	public void setTinhthanhRs(ResultSet tinhthanhRs) {
		this.tinhthanhRs = tinhthanhRs;
	}

	public dbutils getDb() {
		return db;
	}
	
	public void init() {
		
		/*String query = "SELECT quan.PK_SEQ, isnull(QUAN.MA,'') MA, quan.TEN AS QUAN, TINH.PK_SEQ MAHTTINH, TINH.MA MATINH, tinh.TEN AS TINH, tao.TEN AS NGUOITAO, quan.NGAYTAO, "
			+ "\n	sua.TEN AS NGUOISUA, quan.NGAYSUA "
			+ "\nFROM dbo.QUANHUYEN quan "
			+ "\n	INNER JOIN dbo.TINHTHANH tinh ON tinh.PK_SEQ = quan.TINHTHANH_FK "
			+ "\n	INNER JOIN dbo.NHANVIEN tao ON tao.PK_SEQ = quan.NGUOITAO "
			+ "\n	INNER JOIN dbo.NHANVIEN sua ON sua.PK_SEQ = quan.NGUOISUA "
			+ "\nWHERE 1 = 1 ";
		if (!this.ten.equals("")) {
			query += "\n	AND quan.TEN LIKE N'%" + this.ten + "%' ";
		}
		if (!this.tinhId.equals("")) {
			query += "\n	AND tinh.PK_SEQ = " + this.tinhId + " ";
		}
		query += "\nORDER BY tinh.TEN, quan.TEN ";
		System.out.println("QUANHUYEN: " + query);
		
		this.quanhuyenRs = this.db.get(query);*/
		
		String query = "SELECT PK_SEQ, TEN FROM dbo.TINHTHANH ";
		this.tinhthanhRs = this.db.get(query);
	}
}
