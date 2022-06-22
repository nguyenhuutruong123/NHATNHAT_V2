package geso.dms.distributor.beans.duyetbandunggia.imp;

import java.sql.ResultSet;

import geso.dms.center.db.sql.dbutils;
import geso.dms.distributor.beans.duyetbandunggia.IDuyetbandunggiaNppList;

public class DuyetbandunggiaNppList implements IDuyetbandunggiaNppList 
{
	String userId;
	
	String tuthang;
	String denthang;
	String nam;
	String trangthai;
	String msg;
	String diengiai;
	
	ResultSet rsTieuchi;
	
	String nppId;
	String nppTen;
	String sitecode;
	String view;
	String nppidlist;
	String Mienid;
	

	ResultSet rsnppid;

	ResultSet rsMienid;
	


	dbutils db;
	
	public DuyetbandunggiaNppList()
	{
		this.tuthang = "";
		this.denthang="";
		this.nam = "";
		this.trangthai = "";
		this.msg = "";
		this.diengiai = "";
		Mienid="";
		this.view="";
		this.db = new dbutils();
	}
	
	public String getUserId()
	{
		return this.userId;
	}

	public void setUserId(String userId)
	{
		this.userId = userId;
	}


	
	public String getNam() 
	{
		return this.nam;
	}
	
	public void setNam(String nam)
	{
		this.nam = nam;
	}

	public String getTrangthai() 
	{
		return this.trangthai;
	}

	public void setTrangthai(String trangthai) 
	{
		this.trangthai = trangthai;
	}


	public void init(String query)
	{
		if(this.view.length()==0)
		this.getNppInfo();
		
		String sql = "";
		if(query.length() > 0)
			sql = query;
		else
		{
			if(this.view.length()==0)
			{
			sql = "select a.pk_seq, a.thang, a.nam, diengiai, a.trangthai, a.ngaytao, a.ngaysua, b.ten as nguoitao, c.ten as nguoisua  " +
			
				"from DUYETBANDUNGGIA a inner join NHANVIEN b on a.NGUOITAO = b.pk_seq inner join NHANVIEN c on a.NGUOISUA = c.pk_seq " +
				"where a.npp_fk = '" + this.nppId + "'   " +
				"order by nam desc, thang desc";
			
			}
			else
			{
				sql = "select npp.ten,npp.pk_seq as nppid,a.pk_seq, a.thang, a.nam, diengiai, a.trangthai, a.ngaytao, a.ngaysua, b.ten as nguoitao, c.ten as nguoisua  " +
						
				"from DUYETBANDUNGGIA a inner join NHANVIEN b on a.NGUOITAO = b.pk_seq inner join NHANVIEN c on a.NGUOISUA = c.pk_seq inner join nhaphanphoi npp on npp.pk_seq =a.npp_fk " +
				"where a.npp_fk in  (select pk_seq from nhaphanphoi where loainpp=4)  " +
				"order by npp.pk_seq,nam desc, thang desc";
				
			}
		}
		if(this.view.length()==0)
		{
		this.rsnppid=db.get("select pk_seq ,ten from nhaphanphoi where pk_seq="+this.nppId);
		}
		else
		{
			
			if(Mienid.length()>0)
			{
			this.rsnppid=db.get("select a.pk_seq ,a.ten from nhaphanphoi a inner join tinhthanh tt on tt.pk_seq=a.tinhthanh_fk inner join vung v on v.pk_seq=tt.vung_fk  where a.loainpp=4 and v.pk_seq="+Mienid);
			}
			else
			{
				this.rsnppid=db.get("select a.pk_seq ,a.ten from nhaphanphoi a  where trangthai=1 ");

			}
			this.rsMienid=db.get("select pk_seq,ten from VUNG");
		}
			System.out.println("1.Khoi tao chi tieu: " + sql);
		this.rsTieuchi = db.get(sql);
		
	}

	public String getMsg()
	{
		return this.msg;
	}

	public void setMsg(String msg) 
	{
		this.msg = msg;
	}

	public String getDiengiai() 
	{
		return this.diengiai;
	}

	public void setDiengiai(String diengiai) 
	{
		this.diengiai = diengiai;
	}

	public ResultSet getTieuchiSKUInRs()
	{
		return this.rsTieuchi;
	}

	public void setTieuchiSKUInRs(ResultSet tieuchiSKU) 
	{
		this.rsTieuchi = tieuchiSKU;
	}

	
	public String getTuthang() {
		return tuthang;
	}

	
	public void setTuthang(String tuthang) {
		this.tuthang=tuthang;
	}

	
	public String getDenthang() {
		return denthang;
	}

	
	public void setDenthang(String denthang) {
		this.denthang=denthang;
	}

	
	private void getNppInfo()
	{		
		geso.dms.distributor.util.Utility util=new geso.dms.distributor.util.Utility();
		this.nppId=util.getIdNhapp(this.userId);
		this.nppTen=util.getTenNhaPP();
		this.sitecode=util.getSitecode();
	}
	
	public String getNppId() 
	{
		return this.nppId;
	}

	public void setNppId(String nppId) 
	{
		this.nppId = nppId;
	}
	
	public String getNppTen() 
	{
		return this.nppTen;
	}
	
	public void setNppTen(String nppTen) 
	{
		this.nppTen = nppTen;
	}
	
	public String getSitecode() 
	{
		return this.sitecode;
	}

	public void setSitecode(String sitecode) 
	{
		this.sitecode = sitecode;
	}
	public String getView() {
		return view;
	}

	public void setView(String view) {
		this.view = view;
	}
	public String getNppidlist() {
		return nppidlist;
	}

	public void setNppidlist(String nppidlist) {
		this.nppidlist = nppidlist;
	}

	public ResultSet getRsnppid() {
		return rsnppid;
	}

	public void setRsnppid(ResultSet rsnppid) {
		this.rsnppid = rsnppid;
	}
	public String getMienid() {
		return Mienid;
	}

	public void setMienid(String mienid) {
		Mienid = mienid;
	}

	public ResultSet getRsMienid() {
		return rsMienid;
	}

	public void setRsMienid(ResultSet rsMienid) {
		this.rsMienid = rsMienid;
	}
}
