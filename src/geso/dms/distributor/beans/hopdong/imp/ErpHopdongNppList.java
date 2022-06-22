package geso.dms.distributor.beans.hopdong.imp;

import java.sql.ResultSet;

import geso.dms.distributor.beans.hopdong.IErpHopdongNppList;
import geso.dms.distributor.db.sql.dbutils;
import geso.dms.center.util.IPhanTrang;
import geso.dms.center.util.PhanTrang;
import geso.dms.center.util.Phan_Trang;
import geso.dms.center.util.Utility;

public class ErpHopdongNppList extends Phan_Trang implements IErpHopdongNppList
{
	String view = ""; // view = "" là  hợp đồng, view ="convert" là chuyển HĐ thành ĐH
	
	private static final long serialVersionUID = 1L;
	String userId;
	String tungay;
	String denngay;
	String trangthai;
	String mafast;
	String khTen;
	String loaihd;
	String mahd;
	String msg;
	
	ResultSet khRs;
	ResultSet loaihdRs;
	ResultSet DondathangRs;
	
	String loaidonhang;
	
	String nppId;
	String nppTen;
	String sitecode;
	
	private int num;
	private int[] listPages;
	private int currentPages;
	
	dbutils db;
	Utility util;
	
	public ErpHopdongNppList()
	{
		this.tungay = "";
		this.loaihd="";
		this.denngay = "";
		this.nppTen = "";
		this.trangthai = "";
		this.msg = "";
		this.loaidonhang = "0";
		this.mahd = "";
		this.mafast="";
		currentPages = 1;
		num = 1;
		this.util = new Utility();
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
	
	public int getNum()
	{
		return this.num;
	}
	
	public void setNum(int num)
	{
		this.num = num;
		listPages = PhanTrang.getListPages(num);
	}

	public int getCurrentPage()
	{
		return this.currentPages;
	}

	public void setCurrentPage(int current) 
	{
		this.currentPages = current;
	}

	public int[] getListPages() 
	{
		return this.listPages;
	}

	public void setListPages(int[] listPages) 
	{
		this.listPages = listPages;
	}

	public int getLastPage() 
	{
		ResultSet rs = db.get("select count(*) as c from ERP_YEUCAUNGUYENLIEU");
		return PhanTrang.getLastPage(rs);
	}

	public int[] getNewPagesList(String action, int num, int currentPage, int theLastPage, String[] listPage)
	{
		IPhanTrang pt = new PhanTrang();
		return pt.getNewPagesList(action, num, currentPage, theLastPage, listPage);
	}

	public String getMsg() 
	{
		return this.msg;
	}

	public void setMsg(String msg) 
	{
		this.msg = msg;
	}
	
	public void CreateLoaiHD()
	{
		String query="select distinct LoaiDonHang, (CASE LoaiDonHang " +
						"WHEN '0' THEN N'Bình thường' " +
						"WHEN '1' THEN N'Phụ lục' " +
						"WHEN '2' THEN N'Nguyên tắc'" +
						"ELSE N'Hợp đồng chung' " +
						"END) AS  TenLoaiHD " +
						"from ERP_HOPDONGNPP ";
		
		this.loaihdRs = db.get(query);
		
	}
	
	public void init(String search)
	{
		this.getNppInfo();
		//this.nppId=this.userId;
		
		String query = "";
		
		//Utility util = new Utility();
        
		if(search.length() > 0)
			query = search;
		else
		{
			query = "select a.PK_SEQ, a.trangthai, a.mahopdong, a.loaidonhang, a.tungay, a.denngay, isnull(c.ten, '') as nppTEN, b.ten as khoTEN, NV.TEN as nguoitao, b.ten as khonhan, a.NGAYSUA, a.NGAYTAO, NV2.TEN as nguoisua, isnull(a.NOTE, '') as NOTE   " +
					"from ERP_HOPDONGNPP a inner join KHO b on a.kho_fk = b.pk_seq left join KHACHHANG c on a.khachhang_FK = c.pk_seq  " +
					"inner join NHANVIEN nv on a.NGUOITAO = nv.PK_SEQ   " +
					"inner join NHANVIEN nv2 on a.NGUOISUA = nv2.PK_SEQ where a.npp_fk = '" + this.nppId + "'  and a.kho_fk in "+this.util.quyen_kho(this.userId);
		} 
		
		System.out.println("___CHUYEN KHO: " + query);
		this.DondathangRs = createSplittingData(50, 10, "tungay desc, trangthai asc ", query);
		
		this.khRs = db.get("select PK_SEQ, MAFAST + ', ' + TEN as TEN from KHACHHANG where TRANGTHAI = '1' and KBH_FK = '100052' and NPP_FK = '" + this.nppId + "' ");
		CreateLoaiHD();
		System.out.println("---- KHACH HANG: " + "select PK_SEQ, MA + ' - ' + TEN as TEN from KHACHHANG where TRANGTHAI = '1' and KBH_FK = '100052' and NPP_FK = '" + this.nppId + "' ");
	}
	
	public void DBclose() 
	{
		this.db.shutDown();
	}

	public ResultSet getDondathangRs() 
	{
		return this.DondathangRs;
	}

	public void setDondathangRs(ResultSet nkRs) 
	{
		this.DondathangRs = nkRs;
	}

	
	public String getTungay() {
		
		return this.tungay;
	}

	
	public void setTungay(String tungay) {
		
		this.tungay = tungay;
	}

	
	public String getDenngay() {
		
		return this.denngay;
	}

	
	public void setDenngay(String denngay) {
		
		this.denngay = denngay;
	}
	
	public String getLoaidonhang() {

		return this.loaidonhang;
	}

	public void setLoaidonhang(String loaidonhang) {
		
		this.loaidonhang = loaidonhang;
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
		//Phien ban moi
		geso.dms.distributor.util.Utility util=new geso.dms.distributor.util.Utility();
		this.nppId=util.getIdNhapp(this.userId);
		System.out.println("nha phan phoi id"+this.nppId);
		this.nppTen=util.getTenNhaPP();
		//this.dangnhap = util.getDangNhap();
		this.sitecode=util.getSitecode();
	}
	

	
	public String getKhTen() {
		
		return this.khTen;
	}

	
	public void setKhTen(String khTen) {
		
		this.khTen = khTen;
	}

	
	public ResultSet getKhRs() {
		
		return this.khRs;
	}

	
	public void setKhRs(ResultSet khRs) {
		
		this.khRs = khRs;
	}

	
	public String getMafast() {
		return this.mafast;
	}

	
	public void setMafast(String mafast) {
		this.mafast=mafast;
	}

	
	public String getLoaiHD() {
		
		return this.loaihd;
	}

	
	public void setLoaiHD(String loaihd) {
		
		this.loaihd=loaihd;
	}

	
	public ResultSet getLoaiHDRs() {
		
		return this.loaihdRs;
	}

	
	public void setLoaiHDRs(ResultSet loaihdRs) {
		
		this.loaihdRs=loaihdRs;
	}

	public String getMaHD() {
		return this.mahd;
	}

	public void setMaHD(String mahd) {
		this.mahd=mahd;		
	}

	public String getView() {
		return view;
	}
	public void setView(String view) {
		this.view = view;
	}
	
}
