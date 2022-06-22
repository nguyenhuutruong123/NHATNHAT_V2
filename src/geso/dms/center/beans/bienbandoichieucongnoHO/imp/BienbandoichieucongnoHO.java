package geso.dms.center.beans.bienbandoichieucongnoHO.imp;

import java.sql.ResultSet;

import geso.dms.distributor.beans.bienbandoichieucongnoNPP.IBienbandoichieucongnoNPP;
import geso.dms.distributor.beans.hoadontaichinhNPP.IErpHoadontaichinhNPPList;
import geso.dms.distributor.db.sql.dbutils;
import geso.dms.center.beans.bienbandoichieucongnoHO.IBienbandoichieucongnoHO;
import geso.dms.center.util.IPhanTrang;
import geso.dms.center.util.PhanTrang;
import geso.dms.center.util.Phan_Trang;

public class BienbandoichieucongnoHO extends Phan_Trang implements IBienbandoichieucongnoHO
{
	private static final long serialVersionUID = 1L;
	String userId;
	String tungay;
	String denngay;
	String nppTen;
	String msg;
	String nppId;
	ResultSet nppRs;
	ResultSet khRs;
	ResultSet khRsETC;
	String khId;


	public String getKhId() {
		return khId;
	}

	public void setKhId(String khId) {
		this.khId = khId;
	}

	public ResultSet getKhRsETC() {
		return khRsETC;
	}

	public void setKhRsETC(ResultSet khRsETC) {
		this.khRsETC = khRsETC;
	}
	
	dbutils db;
	
	public BienbandoichieucongnoHO()
	{
		this.tungay = "";
		this.denngay = "";
		this.nppTen = "";	
		this.msg = "";	
		this.khId="";		
		this.nppId ="";
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
	
	
	
	public void init()
	{
		this.getNppInfo();
		String query="";
		
		/*query = "select 'ETC--' + cast(PK_SEQ as nvarchar(20)) as PK_SEQ,  N'ETC / ' + isnull(maFAST,'') + '-' + TEN as TEN " +
				"	from KHACHHANG where TRANGTHAI = '1' and KBH_FK = '100052' and npp_fk = '" + this.nppId + "' " +
				"\n union ALL " +
				"\n	select 'DT--' + cast(PK_SEQ as nvarchar(20)) as PK_SEQ,  N'CN, Đối tác / '+ isnull(maFAST,'') + '-' + TEN as TEN  " +
				"\n	from NHAPHANPHOI where TRANGTHAI = '1' and tructhuoc_fk = '" + this.nppId + "' ";*/
		
		query =	"	select cast(PK_SEQ as nvarchar(20)) as PK_SEQ,  isnull(maFAST,'') + '-' + TEN as TEN  " +
		"	from NHAPHANPHOI where TRANGTHAI = '1'  and pk_seq <>1 ";
		
		System.out.println(" qr danh sach khach hang etc/doitac/otc:  " + query +"\n");
		this.khRs = db.get(query);
		
		
		query = "select 'ETC--' + cast(PK_SEQ as nvarchar(20)) as PK_SEQ,  N'ETC / ' + isnull(maFAST,'') + '-' + TEN as TEN " +
		"	from KHACHHANG where TRANGTHAI = '1' and KBH_FK = '100052' and npp_fk = '" + this.nppId + "' " +
		"union ALL " +
		"	select 'DT--' + cast(PK_SEQ as nvarchar(20)) as PK_SEQ,  N'CN, Đối tác / '+ isnull(maFAST,'') + '-' + TEN as TEN  " +
		"	from NHAPHANPHOI where iskhachhang=1 and TRANGTHAI = '1' and tructhuoc_fk = '" + this.nppId + "' ";
		System.out.println(" qr danh sach khach hang ETC" + query);
		
		this.khRsETC = db.get(query);
			
		
	}
	
	public void DBclose() 
	{
		this.db.shutDown();
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

	
	
	
	
	public ResultSet getKhRs() {
		return this.khRs;
	}

	
	public void setKhRs(ResultSet KhRs) {
		this.khRs = KhRs;
		
	}


}

