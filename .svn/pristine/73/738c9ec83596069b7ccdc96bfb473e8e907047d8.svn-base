package geso.dms.center.beans.khuyenmaidacbiet.imp;

import geso.dms.center.util.Phan_Trang;
import geso.dms.center.util.Utility;
import geso.dms.center.beans.khuyenmaidacbiet.*;

import geso.dms.center.db.sql.dbutils;

import java.io.Serializable;
import java.sql.ResultSet;
import java.util.List;

import javax.servlet.http.HttpServletRequest;



public class KhuyenMaiDacBietList extends Phan_Trang implements IKhuyenMaiDacBietList, Serializable
{
	private static final long serialVersionUID = -9217977546733610214L;

	String userId; //nppId
	String tungay;
	String denngay;
	String trangthai;	
	String msg;
	String nppId;

	
	ResultSet dhRs;
	
	dbutils db;
	
	int currentPages;
	int[] listPages;
	
	

	
	public KhuyenMaiDacBietList()
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
		this.dhRs = super.createSplittingData(super.getItems(), super.getSplittings(), "ngaytao desc, trangthai asc", query); //db.get(query);
	}
	
	public void init(HttpServletRequest request) 
	{
		
		String query = "";	
		
			
		query = "\n select a.pk_seq , a.scheme, a.diengiai, a.tungay, a.denngay , a.trangthai, a.ngaytao, a.ngaysua   " +
				"\n		,	'' as nppTen, ngt.ten nguoitao, c.ten nguoisua " +
				"\n from ctkhuyenmai a   " +
				"\n inner  join nhanvien c on a.nguoisua = c.pk_seq       " +
				"\n inner  join nhanvien ngt on a.nguoitao = ngt.pk_seq       " +

				"\n where a.loaict= 9 ";
		if(request != null)
		{
			this.tungay = request.getParameter("tungay") == null  ? "" :  request.getParameter("tungay");
			this.denngay = request.getParameter("denngay") == null  ? "" :  request.getParameter("denngay");
			this.trangthai = request.getParameter("trangthai") == null  ? "" :  request.getParameter("trangthai");
			if(this.tungay.length() > 0)
				query += " and a.ngaytao >='"+this.tungay+"' ";
			if(this.denngay.length() > 0)
				query += " and a.ngaytao <='"+this.denngay+"' ";
			
			
		}
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
	
	

	
	

}

