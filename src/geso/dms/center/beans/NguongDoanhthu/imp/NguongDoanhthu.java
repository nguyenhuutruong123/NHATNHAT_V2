
package geso.dms.center.beans.NguongDoanhthu.imp;

import java.sql.ResultSet;

import geso.dms.center.beans.NguongDoanhthu.INguongDoanhthu;
import geso.dms.center.db.sql.dbutils;

public class NguongDoanhthu implements INguongDoanhthu {
	private static final long serialVersionUID = -9217977546733610214L;
	
	String userid;
	String Message;
	String nppid;
	

	ResultSet rsnppid;
	String tungay;
	String denngay;
	dbutils db;
	public String getNppid() {
		return nppid;
	}

	public void setNppid(String nppid) {
		this.nppid = nppid;
	}

	public ResultSet getRsnppid() {
		return rsnppid;
	}

	public void setRsnppid(ResultSet rsnppid) {
		this.rsnppid = rsnppid;
	}

	public String getTungay() {
		return tungay;
	}

	public void setTungay(String tungay) {
		this.tungay = tungay;
	}

	public String getDenngay() {
		return denngay;
	}

	public void setDenngay(String denngay) {
		this.denngay = denngay;
	}
	
	public String getMessage() {
		return Message;
	}

	public void setMessage(String message) {
		Message = message;
	}

	public String getUserid() {
		return userid;
	}

	public void setUserid(String userid) {
		this.userid = userid;
	}

	public NguongDoanhthu() {
		this.tungay="";
		this.denngay="";
		this.nppid="";
			this.db=new dbutils();	
	}
	public void init()
	{
		this.rsnppid=db.get("select * from nhaphanphoi where trangthai=1 ");
	}
	
	
}