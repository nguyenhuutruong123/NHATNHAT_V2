package geso.dms.distributor.beans.hoadontaichinhKM.imp;

import java.sql.ResultSet;

import geso.dms.distributor.beans.hoadontaichinhKM.IHoadontaichinhKMList;
import geso.dms.distributor.db.sql.dbutils;
import geso.dms.center.util.IPhanTrang;
import geso.dms.center.util.PhanTrang;
import geso.dms.center.util.Phan_Trang;
import geso.dms.center.util.Utility;

public class HoadontaichinhKMList extends Phan_Trang implements IHoadontaichinhKMList
{
	private static final long serialVersionUID = 1L;
	String userId;
	String tungay;
	String denngay;
	String trangthai;

	String nppTen;
	String msg;
	
	ResultSet nppRs;
	ResultSet DondathangRs;
	
	String madonhang;
	
	String khTen;
	ResultSet khRs;
	String nppId;
	
	String loaidonhang;
	
	private int num;
	private int[] listPages;
	private int currentPages;
	
	dbutils db;
	
	public HoadontaichinhKMList()
	{
		this.tungay = "";
		this.denngay = "";
		this.nppTen = "";
		this.trangthai = "";
		this.msg = "";
		this.loaidonhang = "0";
	    this.khTen= "";
	    this.nppId="";
	    this.madonhang = "";
		currentPages = 1;
		this.sohoadon="";
		this.hoadonId="";
		num = 1;
		
		this.db = new dbutils();
	}

	public String getNppId()
	{
		return this.nppId;
	}

	public void setNppId(String nppId) 
	{
		this.nppId = nppId;
	}
	
	public String getMadonhang()
	{
		return this.madonhang;
	}

	public void setMadonhang(String madonhang) 
	{
		this.madonhang = madonhang;
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
		ResultSet rs = db.get("select count(*) as c from HOADON ");
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
	
	public void init(String search)
	{
		this.getNppInfo();
		Utility util = new Utility();
		String query = "";
        
		if(search.length() > 0)
			query = search ;
		else
		{
			query = "select  distinct a.sohoadon, a.kyhieu , a.PK_SEQ, a.trangthai, a.ngayxuatHD, NV.TEN as nguoitao, KH.TEN as nppTEN, a.NGAYSUA, a.NGAYTAO, NV2.TEN as nguoisua,case a.trangthai when 1 then 1 when 2 then 2 when 3 then 4 when 4 then 3 when 5 then 5 end as STT_SORT   " +
					",case a.sohoadon when 'NA' then 9999999999 else cast(sohoadon as float) end as SORT_SOHOADON"+
					" from HOADON a  " +
					" inner join KHACHHANG KH on a.KHACHHANG_FK=KH.PK_SEQ  " +
					" inner join NHANVIEN nv on a.NGUOITAO = nv.PK_SEQ   " +
					" inner join NHANVIEN nv2 on a.NGUOISUA = nv2.PK_SEQ "+
					"  left join HOADON_CTKM_TRAKM b on a.PK_SEQ=b.hoadonID  "+
					"  left join CTKHUYENMAI c on c.SCHEME=b.CTKM  "+
					" where a.DonHangKhac = 1 and a.pk_seq > 0 and a.NPP_FK ="+ this.nppId +"  and a.LOAIHOADON = '1' and a.nguoitao = "+ this.userId +"     and isnull(c.inchung,0)=0 ";
			
	
			if(nppId.equals("100002")||nppId.equals("106174"))
			{
					if(userId.equals("100164")|| userId.equals("100163"))
					{
							query = "select distinct  a.sohoadon, a.kyhieu , a.PK_SEQ, a.trangthai,case a.trangthai when 1 then 1 when 2 then 2 when 3 then 4 when 4 then 3 when 5 then 5 end as STT_SORT, a.ngayxuatHD, NV.TEN as nguoitao, KH.TEN as nppTEN, a.NGAYSUA, a.NGAYTAO, NV2.TEN as nguoisua   " +
									",case a.sohoadon when 'NA' then 9999999999 else cast(sohoadon as float) end as SORT_SOHOADON"+
									" from HOADON a  " +
									"inner join KHACHHANG KH on a.KHACHHANG_FK=KH.PK_SEQ  " +
									"inner join NHANVIEN nv on a.NGUOITAO = nv.PK_SEQ   " +
									"inner join NHANVIEN nv2 on a.NGUOISUA = nv2.PK_SEQ" +
									"  left join HOADON_CTKM_TRAKM b on a.PK_SEQ=b.hoadonID  "+
									"  left join CTKHUYENMAI c on c.SCHEME=b.CTKM  "+
								     " where a.pk_seq > 0 and a.NPP_FK ="+ this.nppId +"  and a.LOAIHOADON = '1'  and isnull(c.inchung,0)=0 ";
								
					}
			}
			
			
		} 
		query+= " and a.kho_fk in "+util.quyen_kho(this.userId);
		System.out.println("___HOADON: " + query);
		this.DondathangRs = createSplittingData(50, 10, "  ngayxuatHD desc,  STT_SORT asc, SORT_SOHOADON desc ", query);
		
		this.khRs = db.get("select PK_SEQ, maFAST + ' - ' + TEN AS TEN from KHACHHANG where TRANGTHAI = '1' and NPP_FK= "+ this.nppId +" and KBH_FK=100025 ");
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

	@Override
	public String getKhTen() {
		return this.khTen;
	}

	@Override
	public void setKhTen(String KhTen) {
		this.khTen = KhTen;
		
	}

	@Override
	public ResultSet getKhRs() {
		return this.khRs;
	}

	@Override
	public void setKhRs(ResultSet KhRs) {
		this.khRs = KhRs;
		
	}
	
	String sohoadon;
	public String getSoHoaDon()
	{
		return this.sohoadon;
	}
	public void setSoHoaDon(String sohoadon)
	{
		this.sohoadon =sohoadon;
	}

	String hoadonId;

	public String getHoadonId()
  {
  	return hoadonId;
  }

	public void setHoadonId(String hoadonId)
  {
  	this.hoadonId = hoadonId;
  }
	
}
