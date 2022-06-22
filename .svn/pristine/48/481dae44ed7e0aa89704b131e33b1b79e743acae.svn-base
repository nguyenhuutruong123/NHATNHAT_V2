package geso.dms.center.beans.nhaphanphoi.imp;

import geso.dms.center.beans.nhaphanphoi.INhaphanphoiList;
import geso.dms.center.db.sql.dbutils;
import geso.dms.center.util.IPhanTrang;
import geso.dms.center.util.PhanTrang;
import geso.dms.center.util.Phan_Trang;

import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.http.HttpServletRequest;

public class NhaphanphoiList extends Phan_Trang implements INhaphanphoiList
{
	
	
	public dbutils getDb() {
		return db;
	}
	
	private static final long serialVersionUID = -9217977546733610214L;

	String userId;
	String ten;
	String sodienthoai,nppId;
	String trangthai;
	String isKH;
	
	ResultSet npplist,nppRs;
	
	String nccId;
	String dvkdId;	
	ResultSet khuvuc;
	String kvId;
	String Msg;
	dbutils db;
	double MucChietKhau=0;
	String DiaChi;
	String DiaChiXuatHD="";
	String MaSoThue;
	String kenhId;
	ResultSet rsKenh;
	ResultSet rsvung;
	
	//
	String diabanId;
	ResultSet diabanRs;
	
	public String getDiabanId() {
		return diabanId;
	}
	public void setDiabanId(String diabanId) {
		this.diabanId = diabanId;
	}
	public ResultSet getDiabanRs() {
		return diabanRs;
	}
	public void setDiabanRs(ResultSet diabanRs) {
		this.diabanRs = diabanRs;
	}
	
	public ResultSet getRsvung() {
		return rsvung;
	}

	public void setRsvung(ResultSet rsvung) {
		this.rsvung = rsvung;
	}

	ResultSet rsdiaban;
	
	public ResultSet getRsdiaban() {
		return rsdiaban;
	}

	public void setRsdiaban(ResultSet rsdiaban) {
		this.rsdiaban = rsdiaban;
	}

	String maFAST;

	String vung;
	public String getVung() {
		return vung;
	}

	public void setVung(String vung) {
		this.vung = vung;
	}

	String diaban;
	
	public String getDiaban() {
		return diaban;
	}

	public void setDiaban(String diaban) {
		this.diaban = diaban;
	}

	private int num;
	private int[] listPages;
	private int currentPages;

	private HttpServletRequest request;
	geso.dms.center.util.Utility util = new geso.dms.center.util.Utility();
	//Constructor
	public NhaphanphoiList(String[] param)
	{
		this.ten = param[0];
		this.sodienthoai = param[1];
		this.trangthai = param[2];
		this.nccId = param[3];
		this.kvId = param[4];
		this.dvkdId = param[5];
		this.kenhId="";
		this.nppId="";
		this.isKH = "";
		this.maFAST = "";
		this.vung="";
		this.diaban="";
		this.db = new dbutils();
	}
	
	public NhaphanphoiList()
	{
		this.ten = "";
		this.sodienthoai = "";
		this.trangthai = "2";
		this.nccId = "";
		this.kvId = "";
		this.dvkdId = "";
		this.Msg ="";
		this.DiaChi="";
		this.MaSoThue="";
		this.MucChietKhau=0;
		this.DiaChiXuatHD="";
		this.kenhId="";
		this.isKH = "";
		this.maFAST = "";
		this.vung="";
		this.diaban="";
		this.db = new dbutils();
		currentPages = 1;
				num = 1;
		
	}
	
	public String getMaFAST() {
		return maFAST;
	}

	public void setMaFAST(String maFAST) {
		this.maFAST = maFAST;
	}
	
	public String getNppId()
	{
		return nppId;
	}

	public void setNppId(String nppId)
	{
		this.nppId = nppId;
	}

	public ResultSet getNppRs()
	{
		return nppRs;
	}

	public void setNppRs(ResultSet nppRs)
	{
		this.nppRs = nppRs;
	}
	
	public HttpServletRequest getRequestObj() 
	{
		return this.request;
	}

	public void setRequestObj(HttpServletRequest request) 
	{
		this.request = request;
	}
	
	public String getUserId()
	{
		return this.userId;
	}

	public void setUserId(String userId) 
	{
		this.userId = userId;
	}
	
	public String getTen() 
	{
		return this.ten;
	}

	public void setTen(String ten) 
	{
		this.ten = ten;
	}

	public String getSodienthoai() 
	{
		return this.sodienthoai;
	}

	public void setSodienthoai(String sodienthoai) 
	{
		this.sodienthoai = sodienthoai;
	}

	public String getTrangthai() 
	{
		return this.trangthai;
	}

	public void setTrangthai(String trangthai) 
	{
		this.trangthai = trangthai;
	}

	public String getNccId() 
	{
		return this.nccId;
	}

	public void setNccId(String nccId) 
	{
		this.nccId = nccId;
	}

	public String getKvId() 
	{
		return this.kvId;
	}

	public void setKvId(String kvId) 
	{
		this.kvId = kvId;
	}

	public String getDvkdId() 
	{
		return this.dvkdId;
	}

	public void setDvkdId(String dvkdId) 
	{
		this.dvkdId = dvkdId;
	}
	
	public ResultSet getNppList() 
	{
		return this.npplist;
	}

	public void setNppList(ResultSet npplist) 
	{
		this.npplist = npplist;
	}

	public ResultSet getKhuvuc() 
	{
		return this.khuvuc;
	}

	public void setKhuvuc(ResultSet khuvuc) 
	{
		this.khuvuc = khuvuc;
	}
	
	public ResultSet getRsKenh()
	{
		return rsKenh;
	}

	public void setRsKenh(ResultSet rsKenh)
	{
		this.rsKenh = rsKenh;
	}

	public String getKenhId()
	{
		return kenhId;
	}

	public void setKenhId(String kenhId)
	{
		this.kenhId = kenhId;
	}
		
	public void createKvRS()
	{
		this.khuvuc = this.db.get("select ten+' - '+diengiai as kvTen, pk_seq as kvId from khuvuc order by ten");
	}
	
	public void creatediabanRS()
	{
		this.rsdiaban = this.db.get("select ten as tTen, pk_seq as tId from tinhthanh order by ten");
	}
	
	//
	public void CreateDiabanRs()
	{
		this.diabanRs = this.db.get("select distinct db.pk_seq, db.diengiai as ten \r\n" + 
				"from diaban db inner join khuvuc d on db.khuvuc_fk = d.pk_seq ");
		
		if (this.kvId.length() > 0) {
			String query = "";
			query = "select distinct db.pk_seq, db.diengiai as ten \r\n" + 
			"from diaban db inner join khuvuc d on db.khuvuc_fk = d.pk_seq where d.pk_seq = "+this.kvId;
			System.out.println("Query diabanRs theo kvId: "+query);
			this.diabanRs = this.db.get(query);
		}
	}
	
	public void createvungRS()
	{
		this.rsvung = this.db.get("select ten  as vTen, pk_seq as vId from vung order by ten");
	}
	
	public void createNppBeanList(String query)
	{
		System.out.println("Queryyyyy: "+query);
		ResultSet rs = createSplittingData(50, 10, " nppMa desc ", query);
		this.npplist = rs;
	}
	
	public void init(String search) 
	{
		String query;	
		if (search.length() == 0)
		{
			
			 query ="	select distinct isnull(a.maFast,'')as maFAST,  a.ma as nppMa, '' as KenhBanHang, a.pk_seq as id, a.ten as nppTen, isnull(a.diachi,'') as diachi, isnull(a.dienthoai,'') as dienthoai , isnull(d.ten+'--'+d.diengiai,'') as khuvuc, a.trangthai, a.ngaytao, \n"+  
					"			a.ngaysua, b.ten as nguoitao, c.ten as nguoisua ,a.TongThau_FK,isnull((select TEN from NHAPHANPHOI where PK_SEQ=a.TongThau_FK),'')  as TongThau,isnull(sitecode_conv.tennpptn,npptn.ten) as tennpptn, \n" +
					"  		isnull(lnpp.tenloai,'')	 as loaiNPP, \n" +
					"	ISNULL( ( select ten from TINHTHANH where pk_seq = tt.pk_seq ) , '' ) as tinhthanh		\n"+ 
					"	from nhaphanphoi a    \n"+
					"		inner join nhanvien b on b.pk_seq=a.nguoitao  \n"+
					"		inner join  nhanvien c on c.pk_seq=a.nguoisua   \n"+
					"		left join  khuvuc d  on a.khuvuc_fk=d.pk_seq \n" +
					"		left join diaban db on db.khuvuc_fk=d.pk_seq \n"+
					"		left join vung v on v.pk_seq=d.vung_fk \n"+
					"		left join tinhthanh tt on tt.pk_seq=a.TINHTHANH_FK \n"+
					"       left join sitecode_conv on sitecode_conv.convsitecode=a.sitecode \n" +
					"       left join nhaphanphoi npptn on npptn.pk_Seq=a.npptn_fk \n"+   
					"       left join LOAINHAPHANPHOI lnpp on lnpp.maloai=a.loainpp \n"+   
					"	 where 1=1 and a.isKHACHHANG = '" + isKH + "'  ";
			System.out.print("list do vao: " +query);
		}
		else
		{
			query = search;
			System.out.println("list do vao seeartc: " +query);
		}
		
		createNppBeanList(query);  
		createKvRS();
		creatediabanRS();
		CreateDiabanRs();
		createvungRS();
		query="select pk_Seq,ten from kenhbanhang ";
		this.rsKenh=this.db.get(query);
		query="select pk_Seq as nppId,ma as nppMa,ten as nppTen,isnull(diachi,'') as nppDiaChi  from nhaphanphoi where pk_seq in "+ util.quyen_npp(this.userId);
		System.out.println("List NPP: "+query);
		this.nppRs=this.db.get(query);
	}
	
	public void DBclose()
	{
		if(!(this.db == null))
		{
			this.db.shutDown();
		}
		try
		{
			if(nppRs!=null)this.nppRs.close();
			if(rsKenh!=null)this.rsKenh.close();
			if(this.khuvuc!=null)this.khuvuc.close();
		} catch (SQLException e)
		{
			e.printStackTrace();
		}
	}

	
	public void setMsg(String Msg) {
		this.Msg = Msg;		
	}
	public String getMsg() {
			return this.Msg;
	}

	@Override
	public void setMaSoThue(String masothue) {
		
		this.MaSoThue=masothue;
	}

	@Override
	public String getMaSoThue() {
		
		return this.MaSoThue;
	}

	@Override
	public void setDiaChi(String diachi) {
		
		this.DiaChi=diachi;
	}

	@Override
	public String getDiaChi() {
		
		return this.DiaChi;
	}

	@Override
	public void setMucChietKhau(double mucchietkhau) {
		
		this.MucChietKhau=mucchietkhau;
	}

	@Override
	public double getMucChietKhau() {
		
		return this.MucChietKhau;
	}

	@Override
	public void setDiaChiXuatHD(String diachixhd) {
		
		this.DiaChiXuatHD=diachixhd;
	}

	@Override
	public String getDiaChiXuatHD() {
		
		return this.DiaChiXuatHD;
	}
	
	public int getNum(){
		return this.num;
	}
	public void setNum(int num){
		this.num = num;
		listPages = PhanTrang.getListPages(num);

	}

	
	public int getCurrentPage() {
		
		return this.currentPages;
	}

	
	public void setCurrentPage(int current) {
		
		this.currentPages = current;
	}

	
	public int[] getListPages() {
		
		return this.listPages;
	}

	
	public void setListPages(int[] listPages) {
		
		this.listPages = listPages;
	}

	
	public int getLastPage() {
		
		ResultSet rs = db.get("select count(*) as c from nhaphanphoi");
		return PhanTrang.getLastPage(rs);
	}

	public int[] getNewPagesList(String action, int num, int currentPage, int theLastPage, String[] listPage) {
		
		IPhanTrang pt = new PhanTrang();
		return pt.getNewPagesList(action, num, currentPage, theLastPage, listPage);
	}

	public void setIsKhachhang(String isKH) {

		this.isKH = isKH;
	}

	public String getIsKhachhang() {

		return this.isKH;
	}		
}
