package geso.dms.distributor.beans.chucdanh.imp;

import java.sql.ResultSet;

import geso.dms.distributor.db.sql.dbutils;

import geso.dms.distributor.beans.chucdanh.IChucdanhList;

public class ChucdanhList implements IChucdanhList {
	private static final long serialVersionUID = -9217977546733610415L;
	
	String chucdanh;
	String ctyId;
	ResultSet ctylist;
	
	ResultSet cdlist;
	String cdId;

	ResultSet nvlist;
	String nvId;
	
	String tungay;
	String denngay;
	String trangthai;
	String userId;
	String nppId;
	String Msg;
	dbutils db;
	
	public ChucdanhList(String ctyId)
	{		
		this.chucdanh = "";
		this.ctyId = ctyId;
		this.cdId = "";
		this.nvId = "";
		
		this.tungay = "";
		this.denngay = "";
		this.trangthai = "";	
		this.Msg ="";
		this.userId = "";
		this.nppId = "";
		this.db = new dbutils();
		this.createCdList();
		this.createCtyList();
		this.createNvList();
		
	}

	public String getChucdanh()
	{
		return this.chucdanh;
	}

	public void setChucdanh(String chucdanh)
	{
		this.chucdanh = chucdanh;
	}
	
	public String getCdId()
	{
		return this.cdId;
	}

	public void setCdId(String cdId)
	{
		this.cdId = cdId;
	}

	public String getCtyId()
	{
		return this.ctyId;
	}

	public void setCtyId(String ctyId)
	{
		this.ctyId = ctyId;
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
	
	public String getTrangthai()
	{
		return this.trangthai;
	}

	public void setTrangthai(String trangthai)
	{
		this.trangthai = trangthai;
	}

	public void setCdlist(ResultSet cdlist)
	{
		this.cdlist = cdlist;
	}

	public ResultSet getCdlist()
	{
		return this.cdlist;
	}
	
	public void setCtyList(ResultSet ctylist)
	{
		this.ctylist = ctylist;
	}

	public ResultSet getCtyList()
	{
		return this.ctylist;
	}
		
	public void setNvList(ResultSet nvlist)
	{
		this.nvlist = nvlist;
	}

	public ResultSet getNvList()
	{
		return this.nvlist;
	}

	private void createCtyList(){		
		this.ctylist =  this.db.get("SELECT PK_SEQ , MA + ' - '+ TEN AS TEN FROM ERP_CONGTY WHERE TRANGTHAI = '1' ");
	}
	
	private void createCdList(){		
		String query = 				" SELECT '' CTYID, '' CTYTEN, \n" +
									" CHUCDANH.PK_SEQ AS CDID, CHUCDANH.DIENGIAI AS CHUCDANH, \n" +
			    					" NHANVIEN.PK_SEQ AS NVID, NHANVIEN.TEN, CHUCDANH.TRANGTHAI, '' TENCTY \n" +
									" FROM ERP_CHUCDANH CHUCDANH \n" +
									" INNER JOIN NHANVIEN NHANVIEN ON CHUCDANH.NHANVIEN_FK = NHANVIEN.PK_SEQ \n" +
									" INNER JOIN NHAPHANPHOI NPP ON NPP.PK_SEQ = CHUCDANH.NPP_FK  ";
		System.out.println(query);
		this.cdlist =  this.db.get(query);
	}

	private void createNvList(){		
		this.nvlist =  this.db.get("SELECT PK_SEQ AS NVID, TEN FROM NHANVIEN");
	}

	public void DBClose(){
		try{
			if(this.ctylist != null) this.ctylist.close();
			if(this.cdlist != null) this.cdlist.close();
			if(this.nvlist != null) this.nvlist.close();
			this.db.shutDown();
		}catch(java.sql.SQLException e){}
	}

	public void setMsg(String Msg) {
		this.Msg =Msg;
		
	}

	
	public String getMsg() {
		return this.Msg;
	}

	
	public String getUserId() {
		
		return this.userId;
	}

	
	public void setUserId(String userId) {
		
		this.userId = userId;
	}

	private void getNppInfo()
	{		
		//Phien ban moi
		geso.dms.distributor.util.Utility util=new geso.dms.distributor.util.Utility();
		this.nppId = util.getIdNhapp(this.userId);
	}
	
}
