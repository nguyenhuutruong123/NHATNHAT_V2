package geso.dms.distributor.beans.noptiennpp.imp;

import geso.dms.center.util.Phan_Trang;
import geso.dms.center.util.Utility;
import geso.dms.distributor.beans.noptiennpp.*;

import geso.dms.center.db.sql.dbutils;

import java.io.Serializable;
import java.sql.ResultSet;
import java.util.List;

import javax.servlet.http.HttpServletRequest;



public class NopTienNPPList extends Phan_Trang implements INopTienNPPList, Serializable
{
	private static final long serialVersionUID = -9217977546733610214L;

	String userId = ""; //nppId
	String tungay = "";
	String denngay = "";
	String trangthai = "";	
	String msg = "";
	String nppId = "";
	String nppTen = "";
	String khachhangInfo = "";
	String sohoadon = "";
	String barcode = "";
	ResultSet dhRs;
	
	dbutils db;
	
	int currentPages;
	int[] listPages;
	
	Utility util = new Utility();

	
	public NopTienNPPList()
	{
		this.tungay = "";
		this.denngay = "";
		this.trangthai = "";
		
		this.msg = "";
	
		currentPages = 1;

		listPages = new int[]{1, 2 , 3 , 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20};
		
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
	
	public String getNppId() 
	{
		return this.nppId;
	}

	public void setNppId(String nppId) 
	{
		this.nppId = nppId;
	}
	
	
	
	
	public void createDhBeanList(String query)
	{
		this.dhRs = super.createSplittingData(super.getItems(), super.getSplittings(), "ngaynhap desc, trangthai asc", query); //db.get(query);
	}
	
	public void getNppInfo()
	{	
		geso.dms.distributor.util.Utility util = new geso.dms.distributor.util.Utility();
		setNppId(util.getIdNhapp(this.userId)); // dùng hàm để lấy 1 số thông tin từ npp
		this.nppTen = util.getTenNhaPP();
		
	}
	
	public void init(HttpServletRequest request) 
	{
		getNppInfo();
		String query = "";	
		
			
		query = "\n select a.doituong,a.pk_seq as dhId, a.ngaynhap, a.trangthai, a.ngaytao, a.ngaysua   " +
				"\n		,	e.ten as nppTen, '' tenkh,'' diachikh, ngt.ten nguoitao, c.ten nguoisua, '' sohoadon " +
				"\n from NopTienNPP a   " +
				"\n inner  join nhanvien c on a.nguoisua = c.pk_seq       " +
				"\n inner  join nhanvien ngt on a.nguoitao = ngt.pk_seq   " +
				"\n left join nhaphanphoi e on a.npp_fk = e.pk_seq  " +
				"\n where a.npp_Fk = " + this.nppId + "  ";
		
		if(request != null)
		{
			this.tungay = request.getParameter("tungay") == null  ? "" :  request.getParameter("tungay");
			this.denngay = request.getParameter("denngay") == null  ? "" :  request.getParameter("denngay");
			this.trangthai = request.getParameter("trangthai") == null  ? "" :  request.getParameter("trangthai");
			this.khachhangInfo = request.getParameter("khachhangInfo") == null  ? "" :  request.getParameter("khachhangInfo").trim();
			this.sohoadon = request.getParameter("sohoadon") == null  ? "" :  request.getParameter("sohoadon").trim();
			this.barcode = request.getParameter("doituong") == null  ? "" :  request.getParameter("doituong").trim();
			
			if(this.sohoadon.length() > 0)
				query += " and a.pk_seq ='"+this.sohoadon+"' ";
			if(this.barcode.length() > 0)
				query += " and a.doituong = '"+this.barcode+"' ";
			if(this.tungay.length() > 0)
				query += " and a.ngaynhap >='"+this.tungay+"' ";
			
			if(this.denngay.length() > 0)
				query += " and a.ngaynhap <='"+this.denngay+"' ";
			if(this.trangthai.length() > 0)
				query += " and a.trangthai  = '"+this.trangthai+"' ";
			else
				query += " and a.trangthai  !=2 ";
			
		}
		System.out.println("query = "+ query);
		this.createDhBeanList(query); 		
	}

	public void DBclose() 
	{		
		try 
		{
			if(db != null)
				this.db.shutDown();
			
		} 
		catch(Exception e) {}
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

	
	
	public ResultSet getDonhangRs() 
	{
		return this.dhRs;
	}

	public void setDonhangRs(ResultSet dhRs) 
	{
		this.dhRs = dhRs;
	}

	public String getMsg() 
	{
		return this.msg;
	}

	public void setMsg(String msg) 
	{
		this.msg = msg;
	}


	public dbutils getDb() {
		return db;
	}
	
	
	public String getKhachhangInfo() {
		return khachhangInfo;
	}
	public void setKhachhangInfo(String khachhangInfo) {
		this.khachhangInfo = khachhangInfo;
	}
	public String getSohoadon() {
		return sohoadon;
	}
	public void setSohoadon(String sohoadon) {
		this.sohoadon = sohoadon;
	}
	public String getBarcode() {
		return barcode;
	}
	public void setBarcode(String barcode) {
		this.barcode = barcode;
	}

}

