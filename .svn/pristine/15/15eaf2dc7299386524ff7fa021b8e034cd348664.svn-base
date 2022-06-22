package geso.dms.center.beans.baocao.imp;

import java.sql.ResultSet;

import geso.dms.center.beans.baocao.IBCKhuyenMaiApGame;
import geso.dms.center.db.sql.dbutils;

public class BCKhuyenMaiApGame implements IBCKhuyenMaiApGame {
	
	String userId;
	String userTen;
	String nppId;
	String ddkdId;
	String tungay;
	String denngay;
	String msg;
	ResultSet rsNpp;
	ResultSet rsDdkd;
	dbutils db;
	
	public BCKhuyenMaiApGame(String userId, String nppId, String ddkdId, String tungay, String denngay) {
		
		this.userId = userId;
		this.userTen="";
		this.nppId = nppId;
		this.ddkdId = ddkdId;
		this.tungay = tungay;
		this.denngay = denngay;
		this.db = new dbutils();
	}
	
	public BCKhuyenMaiApGame() {
		
		this.userId = "";
		this.userTen="";
		this.nppId = "";
		this.ddkdId = "";
		this.tungay = "";
		this.denngay = "";
		this.msg="";
		this.db = new dbutils();
	}
	
	private void createNppRs() {
		
		String query = "select pk_seq, ten from nhaphanphoi where 1=1 ";
		
		if(this.userId.trim().length()>0)
		{
			query += " and pk_seq in (select npp_fk from phamvihoatdong where nhanvien_fk = "+this.userId+")";
		}
		
		this.rsNpp = this.db.get(query);
	}
	
	private void createDdkdRs() {
		
		String query = "select pk_seq,ten from daidienkinhdoanh where 1=1 ";
		
		if(this.nppId.trim().length()>0)
		{
			query +=" and npp_fk ="+this.nppId;
		}
		
		this.rsDdkd = this.db.get(query);
	}
	
	public void init() {
		
		createNppRs();
		createDdkdRs();
	}
	
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	
	public String getUserTen() {
		return userTen;
	}
	public void setUserTen(String userTen) {
		this.userTen = userTen;
	}
	
	public String getNppId() {
		return nppId;
	}
	public void setNppId(String nppId) {
		this.nppId = nppId;
	}
	
	public String getDdkdId() {
		return ddkdId;
	}
	public void setDdkdId(String ddkdId) {
		this.ddkdId = ddkdId;
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
	
	public ResultSet getRsNpp() {
		return rsNpp;
	}
	public void setRsNpp(ResultSet rsNpp) {
		this.rsNpp = rsNpp;
	}
	
	public ResultSet getRsDdkd() {
		return rsDdkd;
	}
	public void setRsDdkd(ResultSet rsDdkd) {
		this.rsDdkd = rsDdkd;
	}
	
	public dbutils getDb() {
		return db;
	}
	public void setDb(dbutils db) {
		this.db = db;
	}

	public String getMsg() {
		return msg;
	}
	public void setMsg(String msg) {
		this.msg = msg;
	}
	
	public void DBclose()
	{
		try
		{
			if (rsNpp != null) { rsNpp.close(); }
			if (rsDdkd != null){ rsDdkd.close(); }
			if (this.db != null) { this.db.shutDown(); }
		} catch (Exception e)
		{
			e.printStackTrace();
			System.out.println("khong the dong ket noi");
		}
	}
}
