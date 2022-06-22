package geso.dms.erp.beans.thutien.imp;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import geso.dms.center.db.sql.dbutils;
import geso.dms.center.util.IDinhKhoanKeToan;
import geso.dms.center.util.IPhanTrang;
import geso.dms.center.util.PhanTrang;
import geso.dms.center.util.Phan_Trang;
import geso.dms.center.util.Utility;
import geso.dms.center.util.DinhKhoanKeToan;
import geso.dms.center.util.IThongTinHienThi;
import geso.dms.center.util.ThongTinHienThi;
import geso.dms.erp.beans.thutien.IErpThutienList;

public class ErpThutienList  extends Phan_Trang  implements IErpThutienList
{
	private static final long serialVersionUID = 1L;
	String userId;
	String tungay;
	String denngay;
	
	String nccId;
	ResultSet nppRs;
	String htttId;
	ResultSet htttRs;   
	String ctyId;
	String trangthai;
	String msg;
	String nguoisuaId;
	String nppdangnhap;
	
	ResultSet tthdRs;	
	ResultSet nguoisuaRs;
	
	List<IThongTinHienThi> hienthiList;
	
	private int num;
	private int[] listPages;
	private int currentPages;
	
	dbutils db;
	Utility util;
	
	String Sochungtu="";
	String Sohoadon="";
	String sotien = "";
	
	String khId;
	ResultSet khRs;
	
	String nvId;
	ResultSet nvRs;
	
	String kbhId;
	ResultSet kbhRs;
	
	String nhomkhId;
	ResultSet nhomkhRs;
	
	String khohangId;
	ResultSet khohangRs;
	
	String nvgnId;
	ResultSet nvgnRs;
	
	String ghichu;
	String sobangke;
	
	String ctyTen;
	String diachi;
	String masothue;
	
	public ErpThutienList()
	{
		this.tungay = "";
		this.denngay = "";
		this.nccId = "";
		this.htttId = "";
		this.trangthai = "";
		this.nguoisuaId = "";
		this.msg = "";
		this.ctyId = "";
		this.khId = "";
		this.nvId = "";
		this.sotien ="";
		this.kbhId = "";
		this.nhomkhId = "";
		this.nppdangnhap = "";
		this.khohangId = "";
		this.nvgnId = "";
		this.ghichu = "";	
		this.sobangke = "";
		this.ctyTen = "";
		this.diachi = "";
		this.masothue = "";
		currentPages = 1;
		num = 1;
		util=new Utility();
		this.db = new dbutils();
		
		this.hienthiList = new ArrayList<IThongTinHienThi>();
	}
	
	public String getUserId()
	{
		return this.userId;
	}

	public void setUserId(String userId) 
	{
		this.userId = userId;
	}

	public String getCtyId()
	{
		return this.ctyId;
	}

	public void setCtyId(String ctyId) 
	{
		this.ctyId = ctyId;
		
		String query = "SELECT TEN, DIACHI, MASOTHUE FROM ERP_CONGTY WHERE PK_SEQ = " + this.ctyId;
		
		ResultSet rs = this.db.get(query);
		
		try{
			if(rs != null) {
				rs.next();
				this.ctyTen = rs.getString("TEN");
				this.diachi = rs.getString("DIACHI");				
				this.masothue = rs.getString("MASOTHUE");
				
				rs.close();
			}
			
		}catch(java.sql.SQLException e){
			e.printStackTrace();
		}
	}

	public String getTungay()
	{
		return this.tungay;
	}

	public void setTungay(String tungay) 
	{
		this.tungay = tungay;
	}

	public String getDenngay()
	{
		return this.denngay;
	}

	public void setDenngay(String denngay) 
	{
		this.denngay = denngay;
	}

	public String getNccId() 
	{
		return this.nccId;
	}

	public void setNccId(String nccid) 
	{
		this.nccId = nccid;
	}

	public ResultSet getNccList() 
	{
		return this.nppRs;
	}

	public void setNccList(ResultSet ncclist) 
	{
		this.nppRs = ncclist;
	}

	public String getHtttId() 
	{
		return this.htttId;
	}

	public void setHtttId(String htttid) 
	{
		this.htttId = htttid;
	}

	public ResultSet getHtttList()
	{
		return this.htttRs;
	}

	public void setHtttList(ResultSet htttlist)
	{
		this.htttRs = htttlist;	
	}

	public String getTrangthai() 
	{
		return this.trangthai;
	}

	public void setTrangthai(String trangthai)
	{
		this.trangthai = trangthai;
	}

	public void setmsg(String msg)
	{
		this.msg = msg;
	}

	public String getmsg() 
	{
		return this.msg;
	}

	public ResultSet getTThoadonList() 
	{
		return this.tthdRs;
	}

	public void setTThoadonList(ResultSet tthdlist) 
	{
		this.tthdRs = tthdlist;	
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
		ResultSet rs = db.get("select count(*) as c from ERP_thutien");
		return PhanTrang.getLastPage(rs);
	}

	public int[] getNewPagesList(String action, int num, int currentPage, int theLastPage, String[] listPage)
	{
		IPhanTrang pt = new PhanTrang();
		return pt.getNewPagesList(action, num, currentPage, theLastPage, listPage);
	}
	
	public void init(String search)
	{
		//this.getNppInfo();   
		String query = "";
		
		this.htttRs = db.get("select pk_seq, ma, ten from ERP_HINHTHUCTHANHTOAN where trangthai = '1'");
		this.nguoisuaRs = db.get("select pk_seq, ten from nhanvien where trangthai=1");
		this.khRs = db.get("select pk_Seq, ma + ', '+ ten as khTen from ERP_NHACUNGCAP where trangthai = 1");
		this.nvRs = db.get("select pk_seq, ma + ', '+ ten as nvTen from ERP_NHANVIEN where trangthai = 1");
		this.kbhRs =  db.get("select pk_seq, diengiai from KENHBANHANG where trangthai = 1");
		this.khohangRs=db.get("select pk_seq ,ten from kho where 1 = 1 and PK_SEQ IN ( " + util.quyen_kho(this.userId)+ ") " );
		
		
		//this.nhomkhRs = db.get("select pk_seq,diengiai ma from NHOMKHACHHANG where trangthai = 1 AND CONGTY_FK = "+this.ctyId);
		
		
		if(search.length() <= 0)
		{
			query = " select a.sonetId,a.pk_seq  as tthdId,(isnull(a.prefix, 'PT') + cast(a.pk_seq as nvarchar(50))) sochungtu ,a.trangthai, a.ngaychungtu, a.ngayghiso, a.ngaytao, a.ngaysua, \n" +
					" case when b.ten is null and ncc.ten is null then '' \n" +
					" when b.ten is not null and ncc.ten is null then isnull(b.Ten,'') \n" +
					" when b.ten is null and ncc.ten is not null then isnull(ncc.ten,'') end as nppTen, \n" +
					"	c.ten as htttTen, \n" +
					" d.ten as nguoitao, e.ten as nguoisua, f.ten as noidungTen, ISNULL(a.ISKTTDUYET,0) ISKTTDUYET, \n" +
					"  a.SOTIENTT THUCTHU, isnull(a.machungtu, '') machungtu \n" +	
					" from ERP_THUTIEN a " +
					" INNER JOIN ERP_TIENTE TT ON TT.PK_SEQ = a.TIENTE_FK \n" +
					" LEFT JOIN KhachHang b on a.KHACHHANG_FK = b.pk_seq \n" +
					" LEFT JOIN ERP_NhaCungCap ncc on a.NCC_FK = ncc.pk_seq \n" +
					" inner join HINHTHUCTHANHTOAN c on a.HTTT_FK = c.pk_seq \n" +
					" inner join ERP_NOIDUNGTHUTIEN f on a.noidungtt_fk = f.pk_seq \n" +
					" inner join NHANVIEN d on a.nguoitao = d.pk_seq  \n" +
					" inner join NHANVIEN e on a.nguoisua = e.pk_seq  \n"+
					" where 1=1 ";
		}
		else
		{
			query = search;
		}
 
		//PHAN QUYEN
		//query += util.getPhanQuyen_TheoNhanVien("KHACHHANG", "b", "pk_seq", this.getLoainhanvien(), this.getDoituongId() );
		
		System.out.println("Init "+ query);
		String query_init = createSplittingData_ListNew(this.db, 25, 10, " trangthai asc, tthdId desc", query) ;
		
		this.tthdRs = db.get(query_init);
	}

	public void DBclose() 
	{		
		try {
			if(this.nppRs != null)
				this.nppRs.close();
			
			if(this.htttRs != null)
				this.htttRs.close();
			
			if(this.tthdRs != null)
				this.tthdRs.close();
			
			if(this.nguoisuaRs != null)
				this.nguoisuaRs.close();
			
			if(this.nvgnRs != null)
				this.nvgnRs.close();
			
			if(this.khohangRs != null)
				this.khohangRs.close();
			
			if(this.khRs != null)
				this.khRs.close();
			
			if(this.nhomkhRs != null)
				this.nhomkhRs.close();
			
			this.db.shutDown();		
			
		} catch (SQLException e) 
		{
			e.printStackTrace();
		}
	}

	
	
	public String getsochungtu() {
		
		return this.Sochungtu;
	}

	
	public void setsochungtu(String sochungtu) {
		
		this.Sochungtu=sochungtu;
	}

	
	public String getsohoadon() {
		
		return this.Sohoadon;
	}

	
	public void setsohoadon(String sohoadon) {
		
		this.Sohoadon=sohoadon;
	}

	public ResultSet getNguoisuaRs() 
	{
		return this.nguoisuaRs;
	}

	public void setNguoisuaRs(ResultSet nguoisuaRs) 
	{
		this.nguoisuaRs = nguoisuaRs;
	}

	public String getNguoisuaId()
	{
		return this.nguoisuaId;
	}

	public void setNguoisuaId(String nguoisuaId) 
	{
		this.nguoisuaId = nguoisuaId ;
	}

	public List<IThongTinHienThi> getHienthiList() 
	{
		return this.hienthiList;
	}

	public void setHienthiList(List<IThongTinHienThi> hienthiList) 
	{
		this.hienthiList = hienthiList;
	}

	
	public String getKhId() {
		
		return this.khId;
	}

	
	public void setKhId(String khid) {
		
		this.khId = khid;
	}

	
	public ResultSet getKhList() {
		
		return this.khRs;
	}

	
	public void setKhList(ResultSet khrs) {
		
		this.khRs = khrs; 
	}

	
	public String getNvId() {
		
		return this.nvId;
	}

	
	public void setNvId(String nvid) {
		
		this.nvId = nvid;
	}

	
	public ResultSet getNvList() {
		
		return this.nvRs;
	}

	
	public void setNvList(ResultSet nvrs) {
		
		this.nvRs = nvrs;
	}

	
	public String getSotien() {
		
		return this.sotien;
	}

	
	public void setSotien(String sotien) {
		
		this.sotien = sotien;
	}

	
	public String getKbhId() {
		
		return this.kbhId;
	}

	
	public void setKbhId(String kbhid) {
		
		this.kbhId = kbhid;
	}

	
	public ResultSet getKbhRs() {
		
		return this.kbhRs;
	}

	
	public void setKbhRs(ResultSet kbhrs) {
		
		this.kbhRs = kbhrs;
	}

	
	public String getNhomkhId() {
		
		return this.nhomkhId;
	}

	
	public void setNhomkhId(String nhomkhid) {
		
		this.nhomkhId = nhomkhid;
	}

	
	public ResultSet getNhomkhRs() {
		
		return this.nhomkhRs;
	}

	
	public void setNhomkhRs(ResultSet Nhomkhrs) {
		
		this.nhomkhRs = Nhomkhrs;
	}

	
	public String getnppdangnhap() {
		
		return this.nppdangnhap;
	}

	
	public void setnppdangnhap(String nppdangnhap) {
		
		this.nppdangnhap = nppdangnhap;
	}

	private void getNppInfo()
	{		
		//Phien ban moi
		geso.dms.distributor.util.Utility util=new geso.dms.distributor.util.Utility();
		this.nppdangnhap=util.getIdNhapp(this.userId);
	}	
	
	Object loainhanvien;
	Object doituongId;
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

	
	public String getKhohangId() {
		
		return this.khohangId;
	}

	
	public void setKhohangId(String khohangid) {
		
		this.khohangId = khohangid;
	}

	
	public ResultSet getKhohangRs() {
		
		return this.khohangRs;
	}

	
	public void setKhohangRs(ResultSet khohangrs) {
		
		this.khohangRs = khohangrs;
	}

	
	public String getNvgnId() {
		
		return this.nvgnId;
	}

	
	public void setNvgnId(String Nvgnid) {
		
		this.nvgnId = Nvgnid;
	}

	
	public ResultSet getNvgnRs() {
		
		return nvgnRs;
	}

	
	public void setNvgnRs(ResultSet nvgnrs) {
		
		this.nvgnRs = nvgnrs;
	}

	
	public String getGhichu() {
		
		return this.ghichu;
	}

	
	public void setGhichu(String ghichu) {
		this.ghichu = ghichu;
		
	}

	
	public String getsobangke() {
		
		return this.sobangke;
	}

	
	public void setsobangke(String sobangke) {
		
		this.sobangke = sobangke;
	}

	
	public String getCtyTen() {
	
		return this.ctyTen;
	}

	
	public String getDiachi() {
	
		return this.diachi;
	}

	
	public String getMasothue() {
	
		return this.masothue;
	}
	
}