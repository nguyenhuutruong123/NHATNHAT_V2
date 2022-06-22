package geso.dms.center.beans.tinhthanh.imp;

import geso.dms.center.beans.tinhthanh.ITinhthanhList;
import geso.dms.center.db.sql.dbutils;

import java.io.Serializable;
import java.sql.ResultSet;

public class TinhthanhList implements ITinhthanhList, Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -3637244443565896113L;
	String ma="";
	String ten;
	String vung;
	String msg = "";
	ResultSet tinhthanhRs;
	ResultSet vungRs;
	
	dbutils db;

	public TinhthanhList() {
		this.ten = "";
		this.vung = "";
		
		this.db = new dbutils();
	}
	
	public String getMsg() {
		return msg;
	}
	public void setMsg(String msg) {
		this.msg = msg;
	}
	
	
	public String getMa() {
		return ma;
	}
	public void setMa(String ma) {
		this.ma = ma;
	}
	public String getTen() {
		return ten;
	}

	public void setTen(String ten) {
		this.ten = ten;
	}

	public String getVung() {
		return vung;
	}

	public void setVung(String vung) {
		this.vung = vung;
	}

	public ResultSet getTinhthanhRs() {
		return tinhthanhRs;
	}

	public void setTinhthanhRs(ResultSet tinhthanhRs) {
		this.tinhthanhRs = tinhthanhRs;
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
		String query = "SELECT tinh.PK_SEQ MAHETHONG,tinh.MA, tinh.TEN AS TINH,isnull( vung.TEN,'') AS VUNG, "
			+ "\n	tinh.NGAYTAO, tao.TEN AS NGUOITAO, tinh.NGAYSUA, sua.TEN AS NGUOISUA "
			+ "\nFROM dbo.TINHTHANH tinh "
			+ "\n	left JOIN dbo.VUNG vung ON vung.PK_SEQ = tinh.VUNG_FK "
			+ "\n	INNER JOIN dbo.NHANVIEN tao ON tao.PK_SEQ = tinh.NGUOITAO "
			+ "\n	INNER JOIN dbo.NHANVIEN sua ON sua.PK_SEQ = tinh.NGUOISUA "
			+ "\nWHERE 1 = 1 ";
		
		if (!this.ten.trim().equals("")) {
			query += "\n	AND tinh.TEN LIKE N'%" + this.ten.trim() + "%' ";
		}
		if (!this.ma.trim().equals("")) {
			query += "\n	AND tinh.Ma LIKE N'%" + this.ma.trim() + "%' ";
		}
		
		if (!this.vung.equals("")) {
			query += "\n	AND vung.PK_SEQ = " + this.vung + " ";
		}
		
		query += "\nORDER BY tinh.TEN ";
		this.tinhthanhRs = this.db.get(query);
		
		query = "SELECT PK_SEQ, TEN FROM dbo.VUNG";
		this.vungRs = this.db.get(query);
	}
}
