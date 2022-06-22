package geso.dms.distributor.beans.donhangctv.imp;

import geso.dms.distributor.beans.donhangctv.IBCXNT_CTV;
import geso.dms.distributor.db.sql.dbutils;
import geso.dms.distributor.util.Utility;

import java.sql.ResultSet;

public class BCXNT_CTV implements IBCXNT_CTV
{
	String userId, userTen, nppId, nppTen, month, year, khId, khTen, khuvucId, isdlpp, tinhthanhid, spid, msg;
	ResultSet rsKhachhang, rsKhuvuc, rsTinhthanh, dataRs;
	dbutils db;
	
	public BCXNT_CTV()
	{
		this.userId = "";
		this.userTen = "";
		this.nppId = "";
		this.nppTen = "";
		this.month = "";
		this.year = "";
		this.khId = "";
		this.khTen = "";
		this.khuvucId = "";
		this.isdlpp = "";
		this.tinhthanhid = "";
		this.spid = "";
		this.msg = "";
		
		db = new dbutils();
	}
	
	@Override
	public void setuserId(String userId) {
		
		this.userId = userId;
	}

	@Override
	public String getuserId() {
		
		return this.userId;
	}

	@Override
	public void setuserTen(String userTen) {
		
		this.userTen = userTen;
	}

	@Override
	public String getuserTen() {
		
		return this.userTen;
	}

	@Override
	public void setnppId(String nppId) {
		
		this.nppId = nppId;
	}

	@Override
	public String getnppId() {
		
		return this.nppId;
	}

	@Override
	public void setnppTen(String nppTen) {
		
		this.nppTen = nppTen;
	}

	@Override
	public String getnppTen() {
		
		return this.nppTen;
	}

	@Override
	public void setkhId(String khId) {
		
		this.khId = khId;
	}

	@Override
	public String getkhId() {
		
		return this.khId;
	}

	@Override
	public void setkhTen(String khTen) {
		
		this.khTen = khTen;
	}

	@Override
	public String getkhTen() {
		
		return this.khTen;
	}

	@Override
	public void setRsKhachhang(ResultSet rsKhachhang) {
		
		this.rsKhachhang = rsKhachhang;
	}

	@Override
	public ResultSet getRsKhachhang() {
		
		return this.rsKhachhang;
	}

	@Override
	public void setMsg(String msg) {
		
		this.msg = msg;
	}

	@Override
	public String getMsg() {
		
		return this.msg;
	}

	@Override
	public void setkhuvucId(String khuvucId) {
		
		this.khuvucId = khuvucId;
	}

	@Override
	public String getkhuvucId() {
		
		return this.khuvucId;
	}

	@Override
	public void setRsKhuvuc(ResultSet rsKhuvuc) {
		
		this.rsKhuvuc = rsKhuvuc;
	}

	@Override
	public ResultSet getRsKhuvuc() {
		
		return this.rsKhuvuc;
	}

	@Override
	public void setMonth(String month) {
		
		this.month = month;
	}

	@Override
	public String getMonth() {
		
		return this.month;
	}

	@Override
	public void setYear(String year) {
		
		this.year = year;
	}

	@Override
	public String getYear() {
		
		return this.year;
	}

	@Override
	public ResultSet getRsTinhthanh() {
		
		return this.rsTinhthanh;
	}

	@Override
	public void setRsTinhthanh(ResultSet rsTinhthanh) {
		
		this.rsTinhthanh = rsTinhthanh;
	}

	@Override
	public String getTinhthanhid() {
		
		return this.tinhthanhid;
	}

	@Override
	public void setTinhthanhid(String tinhthanhid) {
		
		this.tinhthanhid = tinhthanhid;
	}

	@Override
	public void setSpId(String spid) {
		
		this.spid = spid;
	}

	@Override
	public String getSpId() {
		
		return this.spid;
	}

	@Override
	public void setDataRs(String query) {
		
		this.dataRs = db.get(query);
	}

	@Override
	public ResultSet getDataRs() {
		
		return this.dataRs;
	}

	@Override
	public void setIsDlpp(String isdlpp) {
		
		this.isdlpp = isdlpp;
	}

	@Override
	public String getIsDlpp() {
		
		return this.isdlpp;
	}

	private void getNppInfo()
	{		
		Utility util = new Utility();
		this.nppId = util.getIdNhapp(this.userId);
		this.nppTen = util.getTenNhaPP();
	}
	
	@Override
	public void init_dlpp_ctv(String sqlSelect) {
		getNppInfo();
		
		this.rsTinhthanh = db.get("select pk_seq,ten from tinhthanh");
		
		String query_khuvuc = " select PK_SEQ, TEN from KHUVUC ";
		this.rsKhuvuc = db.get(query_khuvuc);
		
		if( sqlSelect.trim().length() > 0 )
		{
			this.dataRs = db.get(sqlSelect);
		}
		
		String query = "select pk_Seq, mafast+'-'+ten as ten from khachhang where trangthai = 1 and KBH_FK in (100052, 100054)";
		if(this.nppId.trim().length() > 0)
			query += " and npp_fk = "+this.nppId;
		this.rsKhachhang = db.get(query);
	}
}
