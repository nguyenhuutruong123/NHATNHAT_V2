package geso.dms.distributor.beans.BacSi.imp;

import geso.dms.center.util.Phan_Trang;
import geso.dms.distributor.beans.BacSi.IBacSiList;
import geso.dms.distributor.db.sql.*;

import java.io.Serializable;
import java.sql.ResultSet;

public class BacSiList extends Phan_Trang implements IBacSiList, Serializable
{
	private static final long serialVersionUID = -9217977546733610214L;

	String userId; //nppId
	String ten;
	String trangthai;
	String msg;
	String tungay;
	String denngay;
	String nppid;
	ResultSet bslist;
	String Ma;
	String khten;

	


	



	public String getKhten() {
		return khten;
	}

	public void setKhten(String khten) {
		this.khten = khten;
	}



	dbutils db;

	int currentPages;
	int[] listPages;

	String view = "";

	public BacSiList()
	{
		this.ten = "";
		this.trangthai = "";
		this.msg = "";
		this.Ma="";
		this.tungay="";
		this.denngay="";
		currentPages = 1;
		this.khten="";
		listPages = new int[]{1, 2 , 3 , 4, 5, 6, 7, 8, 9, 10};

		this.db = new dbutils();
		util = new geso.dms.distributor.util.Utility();
	}
	
	public String getMa() {
		return Ma;
	}

	public void setMa(String ma) {
		Ma = ma;
	}
	public ResultSet getBslist() {
		return bslist;
	}

	public void setBslist(ResultSet bslist) {
		this.bslist = bslist;
	}

	
	public String getNppid() {
		return nppid;
	}

	public void setNppid(String nppid) {
		this.nppid = nppid;
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

	public String getTrangthai() 
	{
		return this.trangthai;
	}

	public void setTrangthai(String trangthai) 
	{
		this.trangthai = trangthai;
	}

	


	public void createRS()
	{
		this.getNppInfo();
		
	}



	geso.dms.distributor.util.Utility util=new geso.dms.distributor.util.Utility();
	private void getNppInfo()
	{	
		
			this.nppid=util.getIdNhapp(this.userId);
			
		
	}
	public void init(String search) 
	{
		this.getNppInfo();
		String query;	
		if (search.length() == 0)
		{		
			query = " select pk_seq,ISNULL(ma,'') as MA,ISNULL(ten,'') as ten,ISNULL(sodienthoai,'') sodienthoai "+
					" ,case when trangthai=1 then N'Hoạt Động' else N'Ngưng hoạt động' end trangthai	,ISNULL(diachi,'') diachi,ISNULL(EMAIL,'') email from BacSi "+
				    " where npp_fk="+this.nppid+" ";
			
		}
		else
		{
			query = search;
		}

		System.out.println("chuoi:"+query );

		this.bslist =  super.createSplittingData(super.getItems(), super.getSplittings(), "ma desc", query);
		this.createRS();
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
		ResultSet rs = db.get("select count(*) as skh from khachhang");
		try 
		{
			rs.next();
			int count = Integer.parseInt(rs.getString("skh"));
			rs.close();
			return count;
		}
		catch(Exception e) {}
		finally{try {
			if(rs != null)
				rs.close();
		} catch (Exception e2) {

		}}

		return 0;
	}



	public String getTungay() {

		return this.tungay;
	}



	public void setTungay(String tungay) {
		this.tungay=tungay;

	}



	public String getDenngay() {

		return this.denngay;
	}



	public void setDenngay(String denngay) {
		this.denngay= denngay;

	}


}

