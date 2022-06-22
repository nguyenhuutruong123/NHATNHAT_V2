package geso.dms.distributor.beans.donhangctv.imp;

import geso.dms.center.util.Phan_Trang;
import geso.dms.center.util.Utility;
import geso.dms.distributor.beans.donhangctv.IDonhangctvList;
import geso.dms.distributor.db.sql.dbutils;

import java.io.Serializable;
import java.sql.ResultSet;

public class DonhangctvList extends Phan_Trang  implements IDonhangctvList, Serializable
{
	private static final long serialVersionUID = 1L;
	String userId;
	String tungay;
	String denngay;
	String trangthai;

	String sophieu;
	String lydo;
	String msg;
	
	ResultSet nhapkhoRs;
	ResultSet khRs;
	String khId;
	
	String nppId;
	String nppTen;
	String sitecode;
	String sochungtu;
	String timkiem = "";
	String loaidonhang;
	String duyetss = "";
	dbutils db;
	ResultSet rsxemctv;
	
	String tenNVBH;
	String tenSP;
	String tdv_dangnhap_id;
	String npp_duocchon_id;
	
	Object loainhanvien;
	Object doituongId;
	
	public String createSearchParameters()
	{
		String result = null;
		
		result = "&startSearchList=true&tungay=" + this.tungay + "&denngay=" + this.denngay 
		+ "&sochungtu=" + this.sochungtu + "&trangthai=" + this.trangthai
		+ "&tenNVBH=" + this.tenNVBH + "&tenSP=" + this.tenSP
		+ "&timkiem=" + this.timkiem
		+ "&nppId=" + this.nppId
		; 
		
		return result;
	}
	
	public DonhangctvList()
	{
		this.tungay = "";
		this.denngay = "";
		this.trangthai = "";
		this.sophieu="";
		this.sochungtu="";
		this.lydo = "";
		this.msg = "";
		this.loaidonhang = "";
		this.tenNVBH = "";
		this.tenSP = "";
		this.tdv_dangnhap_id = "";
		this.npp_duocchon_id = "";

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
	
	public String getTrangthai()
	{
		return this.trangthai;
	}

	public void setTrangthai(String trangthai) 
	{
		this.trangthai = trangthai;
	}


	public String getMsg() 
	{
		return this.msg;
	}

	public void setMsg(String msg) 
	{
		this.msg = msg;
	}

	public void init(String search)
	{
		this.getNppInfo();

		String query = "";
        
		if(search.length() > 0)
			query = search;
		else
		{
			query =	"	select a.pk_Seq, b.MA as nppMa,b.TEN as nppTen,a.ngaynhap, f.ten as tenkh, c.TEN as nguoiTao, d.TEN as nguoiSua, a.TRANGTHAI, "+
					"	a.SOTIENBVAT,CONVERT(varchar(10), a.Modified_Date,126) as Modified_Date, CONVERT(varchar(10), a.created_date,126) as created_date, "+
					"	isnull( a.tooltip, '' ) as tooltip, a.SOTIENAVAT, dd.ten as ddkdTen "+
					"		from donhangctv a inner join NHAPHANPHOI b on b.PK_SEQ = a.NPP_FK "+
					"		inner join NHANVIEN c on c.PK_SEQ=a.NGUOITAO  "+
					"		inner join NHANVIEN d on d.PK_SEQ=a.NGUOISUA "+
					"		inner join KHACHHANG f on f.pk_seq = a.khachhang_FK " +
					"		inner join DAIDIENKINHDOANH dd on a.ddkd_fk = dd.pk_seq "+	
					" where a.npp_fk = '" + this.nppId + "'";
		} 
		
		if(this.duyetss.length() > 0 )
		{
			//" where a.npp_fk = '" + this.nppId + "' and a.trangthai in(1,2) ";
		}
		
		//PHAN QUYEN
		//Utility util = new Utility();
		//query += "\n and f.pk_seq in (select distinct khachhang_fk from khachhang_kenhbanhang where kbh_fk in " + util.quyen_kenh(this.userId) + ") ";		
		System.out.println("::: LAY DON HANG: " + query);
		
		this.nhapkhoRs = createSplittingData(50, 10, "pk_seq desc, ngaynhap desc, TRANGTHAI asc ", query);
	}
	
	public void DBclose() 
	{
		try
		{
			if(nhapkhoRs!=null)nhapkhoRs.close();
			if(khRs!=null)khRs.close();
			if(rsxemctv!=null)rsxemctv.close();
		}
		catch (Exception e) {
			
		}
		this.db.shutDown();
	}

	public String getSophieu()
	{
		return sophieu;
	}

	public void setSophieu(String sophieu) 
	{
		this.sophieu = sophieu;
	}

	public String getLydo() 
	{
		return lydo;
	}

	public void setLydo(String lydo) 
	{
		this.lydo = lydo;
	}

	public String getTungayTao() 
	{
		return this.tungay;
	}

	public void setTungayTao(String tungay) 
	{
		this.tungay =tungay;	
	}

	public String getDenngayTao() 
	{
		return this.denngay;
	}

	public void setDenngayTao(String denngay) 
	{
		this.denngay = denngay;
	}

	public ResultSet getNhapkhoRs() 
	{
		return this.nhapkhoRs;
	}

	public void setNhapkhoRs(ResultSet nkRs) 
	{
		this.nhapkhoRs = nkRs;
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
	
	private void getNppInfo()
	{		
		geso.dms.distributor.util.Utility util = new geso.dms.distributor.util.Utility();
		this.nppId = util.getIdNhapp(this.userId);
		this.nppTen = util.getTenNhaPP();
		this.sitecode = util.getSitecode();
	}

	public String getSochungtu() {
		return this.sochungtu;
	}

	public void setSochungtu(String sochungtu) {
		this.sochungtu=sochungtu;
	}

	public ResultSet getKhRs() {
		return this.khRs;
	}

	public void setKhRs(ResultSet khrs) {
		this.khRs=khrs;
		
	}

	public String getKhId() {
		return this.khId;
	}

	public void setKhId(String KhId) {
		this.khId=KhId;
		
	}

	
	public String getLoaidonhang() {
		
		return this.loaidonhang;
	}

	
	public void setLoaidonhang(String loaidonhang) {
		
		this.loaidonhang = loaidonhang;
	}


	public String getDuyetSS() {
	
		return this.duyetss;
	}


	public void setDuyetSS(String duyetss) {
	
		this.duyetss = duyetss;
	}


	public String getTimkiem() {
	
		return this.timkiem;
	}


	public void setTimkiem(String timkiem) {
	
		this.timkiem = timkiem;
	}
	
	public String getLoainhanvien() 
	{
		if( this.loainhanvien == null )
			return "";
		
		return this.loainhanvien.toString();
	}

	public void setLoainhanvien(Object loainhanvien) 
	{
		this.loainhanvien = loainhanvien;
	}
	
	public String getDoituongId() 
	{
		if( this.doituongId == null )
			return "";
		
		return this.doituongId.toString();
	}

	public void setDoituongId(Object doituongId) 
	{
		this.doituongId = doituongId;
	}
	
	public ResultSet getRsxemctv() {
		return rsxemctv;
	}

	public void setRsxemctv(ResultSet rsxemctv) {
		this.rsxemctv = rsxemctv;
	}
	public void setRsxemCTV(String sql) {
		this.rsxemctv=db.get(sql);
		
	}

	
	public String getTenNVBH() {
		
		return this.tenNVBH;
	}

	
	public void setTenNVBH(String tenNVBH) {
		
		this.tenNVBH = tenNVBH;
	}

	
	public String getTenSP() {
		
		return this.tenSP;
	}

	
	public void setTenSP(String tenSP) {
		
		this.tenSP = tenSP;
	}
	
}