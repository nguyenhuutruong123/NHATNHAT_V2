package geso.dms.center.beans.hoadontaichinh.imp;

import java.sql.ResultSet;

import geso.dms.distributor.db.sql.dbutils;
import geso.dms.center.beans.hoadontaichinh.IErpHoadontaichinhList;
import geso.dms.center.util.IPhanTrang;
import geso.dms.center.util.PhanTrang;
import geso.dms.center.util.Phan_Trang;

public class ErpHoadontaichinhList extends Phan_Trang implements IErpHoadontaichinhList
{
	private static final long serialVersionUID = 1L;
	String userId;
	String tungay;
	String denngay;
	String trangthai;
	
	String sohoadon;
	String sodonhang;

	String nppTen;
	String msg;
	
	String nppId;
	ResultSet nppRs;
	ResultSet DondathangRs;
	
	String khTen;
	ResultSet khRs;
	ResultSet khRsETC;
	ResultSet khRsHopDong;
	
	String sohopdong="";
	
	

	String loaidonhang;
	private int num;
	private int[] listPages;
	private int currentPages;
	
	String loaikm;
	String noibo;
	
	dbutils db;
	
	public ErpHoadontaichinhList()
	{
		this.tungay = "";
		this.denngay = "";
		this.nppTen = "";
		this.trangthai = "";
		this.sohoadon="";
		this.sodonhang="";
		this.msg = "";
		this.loaidonhang = "";
	    this.khTen= "";
		currentPages = 1;
		num = 1;
		this.nppId ="";
		this.loaikm="";
		this.noibo = "0";
		this.db = new dbutils();
	}

	public String getnppId()
	{
		return this.nppId;
	}

	public void setnppId(String nppId) 
	{
		this.nppId = nppId;
	}
	
	public String getUserId()
	{
		return this.userId;
	}

	public void setUserId(String userId) 
	{
		this.userId = userId;
	}
	
	public String getLoaikm() {
		return loaikm;
	}

	public void setLoaikm(String loaikm) {
		this.loaikm = loaikm;
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

	public String getSohopdong() {
		return sohopdong;
	}

	public void setSohopdong(String sohopdong) {
		this.sohopdong = sohopdong;
	}
	
	public ResultSet getKhRsHopDong() {
		return khRsHopDong;
	}

	public void setKhRsHopDong(ResultSet khRsHopDong) {
		this.khRsHopDong = khRsHopDong;
	}

	
	public int getLastPage() 
	{
		this.getNppInfo();
		ResultSet rs = db.get("select count(*) as c from ERP_HOADON where npp_fk ='"+ this.nppId +"'");
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

	private void getNppInfo()
	{		
		//Phien ban moi
		geso.dms.distributor.util.Utility util=new geso.dms.distributor.util.Utility();
		this.nppId=util.getIdNhapp(this.userId);
	}
	
	double tongTruoc = 0 ;
	double tongCK = 0;
	double tongSau = 0;
	
	public void getSumBySearch(String sumqr) {
		System.out.println("câu ck: "+sumqr);
		if(isSearch){
		ResultSet rs = db.get(sumqr);
			try 
			{
				rs.next();
				this.tongTruoc = rs.getDouble("DOANHSO");
				this.tongCK = rs.getDouble("THUE");
				this.tongSau = rs.getDouble("DOANHTHU");
				rs.close();
			}
			catch(Exception e) {}
		}
		else{
			System.out.println("vàafsa ");			
			this.tongTruoc = 0;
			this.tongCK = 0;
			this.tongSau = 0;
		}
	}
	
	public void init(String search)
	{
		this.getNppInfo();
		String query = "";
        
		if(search.length() > 0)
			query = search;
		    //query = search + " and a.npp_fk ='"+ this.nppId +"'  ";
		else
		{
			if( this.noibo.equals("1") )
			{
				query = "select isnull(a.loaihd,0) loaihd ,a.PK_SEQ, a.trangthai, a.ngayxuatHD, a.sohoadon + a.kyhieu as sohoadon, NV.TEN as nguoitao, a.tongtienavat as tongtien,  " +
						" npp.TEN as khTEN, a.NGAYSUA, a.NGAYTAO, NV2.TEN as nguoisua  " +
						"from ERP_HOADON a " +
						" inner join ERP_HOADON_DDH ddh on ddh.HOADON_FK=a.PK_SEQ "+
						" inner join ERP_DONDATHANG dh on dh.PK_SEQ=ddh.DDH_FK "+
						" left join NHAPHANPHOI npp on a.NPP_FK=npp.PK_SEQ  " +
						" inner join NHANVIEN nv on a.NGUOITAO = nv.PK_SEQ   " +
						" inner join NHANVIEN nv2 on a.NGUOISUA = nv2.PK_SEQ "+
						"where  a.noibo = '1'   ";
			}
			else
			{
				System.out.println("loai hoa don la "+this.loaikm);
				if( !this.loaikm.equals("1") )
				{
					query = "select isnull(a.loaihd,0) loaihd ,a.PK_SEQ, a.trangthai, a.ngayxuatHD, a.sohoadon as sohoadon, a.kyhieu as kyhieu, NV.TEN as nguoitao, a.tongtienavat as tongtien,  " +
							" npp.TEN as khTEN, a.NGAYSUA, a.NGAYTAO, NV2.TEN as nguoisua  " +
							"from ERP_HOADON a " +
							" inner join ERP_HOADON_DDH ddh on ddh.HOADON_FK=a.PK_SEQ "+
							" inner join ERP_DONDATHANG dh on dh.PK_SEQ=ddh.DDH_FK "+
							" left join NHAPHANPHOI npp on a.NPP_FK=npp.PK_SEQ  " +
							" inner join NHANVIEN nv on a.NGUOITAO = nv.PK_SEQ   " +
							" inner join NHANVIEN nv2 on a.NGUOISUA = nv2.PK_SEQ "+
							"where a.noibo = '0' and loaihoadon != '1'  and  dh.isKM <> 1   ";
				}
				else
				{
					query = "select isnull(a.loaihd,0) loaihd, a.PK_SEQ, a.trangthai, a.ngayxuatHD, a.sohoadon as sohoadon, a.kyhieu as kyhieu, NV.TEN as nguoitao, a.tongtienavat as tongtien,   " +
							"	npp.TEN as khTEN, a.NGAYSUA, a.NGAYTAO, NV2.TEN as nguoisua  " +
							"from ERP_HOADON a " +
							" inner join ERP_HOADON_DDH ddh on ddh.HOADON_FK=a.PK_SEQ "+
							" inner join ERP_DONDATHANG dh on dh.PK_SEQ=ddh.DDH_FK "+
							" inner join NHAPHANPHOI npp on a.NPP_FK=npp.PK_SEQ  " +
							" inner join NHANVIEN nv on a.NGUOITAO = nv.PK_SEQ   " +
							" inner join NHANVIEN nv2 on a.NGUOISUA = nv2.PK_SEQ "+
							 "where a.noibo = '0' and ( loaihoadon = '1' or dh.isKM =1 )   ";
				}
			}
		} 
		
		System.out.println("ERP_HOADON1111: " + query);
		this.DondathangRs = createSplittingData(50, 10, " ngayxuatHD desc,trangthai asc, sohoadon desc ", query);
		
		query = "	select cast(PK_SEQ as nvarchar(20)) as PK_SEQ,  isnull(maFAST,'') + '-' + TEN as TEN  " +
				"	from NHAPHANPHOI where TRANGTHAI = '1'  ";
		System.out.println( " rs khach hang: "+query);
		this.khRs = db.get(query);
		
		query = "	select cast(PK_SEQ as nvarchar(20)) as PK_SEQ,  isnull(maFAST,'') + '-' + TEN as TEN  " +
		"	from NHAPHANPHOI where TRANGTHAI = '1' and isKHACHHANG=1 ";
		System.out.println( " rs khach hang etc: "+query);
		this.khRsETC = db.get(query);
		
		
		query ="	select a.PK_SEQ as PK_SEQ, a.trangthai , a.mahopdong as sohopdong "+    
			 "\n from ERP_HopDong a inner join NHAPHANPHOI c on a.NPP_FK = c.pk_seq   "+    
			 "\n	where a.pk_seq > 0   and c.iskhachhang <>0  order by  pk_seq";
		System.out.println( " rs hop dong: "+query);
		this.khRsHopDong = db.get(query);
		
		
		
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

	
	public String getNppTen() {
		
		return this.nppTen;
	}

	
	public void setNppTen(String nppTen) {
		
		this.nppTen = nppTen;
	}

	
	public ResultSet getNppRs() {
		
		return this.nppRs;
	}

	
	public void setNppRs(ResultSet nppRs) {
		
		this.nppRs = nppRs;
	}

	
	public String getLoaidonhang() {

		return this.loaidonhang;
	}


	public void setLoaidonhang(String loaidonhang) {
		
		this.loaidonhang = loaidonhang;
	}

	
	public String getKhTen() {
		return this.khTen;
	}

	
	public void setKhTen(String KhTen) {
		this.khTen = KhTen;
		
	}

	
	public ResultSet getKhRs() {
		return this.khRs;
	}

	
	public ResultSet getKhRsETC() {
		return khRsETC;
	}

	public void setKhRsETC(ResultSet khRsETC) {
		this.khRsETC = khRsETC;
	}

	public void setKhRs(ResultSet KhRs) {
		this.khRs = KhRs;
		
	}

	
	public String getSohoadon() {
		
		return this.sohoadon;
	}

	
	public void setSohoadon(String sohoadon) {
		
		this.sohoadon=sohoadon;
	}

	
	public String getSodonhang() {
		
		return this.sodonhang;
	}

	
	public void setSodonhang(String sodonhang) {
		
		this.sodonhang=sodonhang;
	}
	boolean isSearch = false;
	
	public boolean getIsSearch() {
		return this.isSearch;
	}

	
	public void setIsSearch(boolean search) {
		this.isSearch = search;
		
	}

	
	public double getTongTruoc() {
		return this.tongTruoc;
	}

	
	public double getTongCK() {
		return this.tongCK;
	}

	
	public double getTongSau() {
		return this.tongSau;
	}

	
	public String getNOIBO() {

		return this.noibo;
	}


	public void setNOIBO(String noibo) {
		
		this.noibo = noibo;
	}
	

}
