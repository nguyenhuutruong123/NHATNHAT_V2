package geso.dms.center.beans.bundle.imp;

import geso.dms.center.util.Phan_Trang;
import geso.dms.center.beans.bundle.*;
import geso.dms.center.db.sql.dbutils;

import java.io.Serializable;
import java.sql.ResultSet;
import java.util.List;


public class RaBundleList extends Phan_Trang implements IRaBundleList, Serializable
{
	private static final long serialVersionUID = -9217977546733610214L;

	String userId; //nppId
	String tungay;
	String denngay;
	String trangthai;
	String msg;
	String mafast;
	String nppId;	
	List<RaBundleList> dhlist;
	dbutils db;
	ResultSet dhRs;
	int currentPages;
	int[] listPages;


	//Constructor
	public RaBundleList(String[] param)
	{
		this.tungay = param[0];
		this.denngay = param[1];
		this.trangthai = param[2];
		this.msg = "";
		this.mafast="";

		currentPages = 1;
		listPages = new int[]{1, 2 , 3 , 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20};
		this.nppId="";
		this.db = new dbutils();
	}

	public RaBundleList()
	{
		this.tungay = "";
		this.denngay = "";
		this.trangthai = "";
	
		this.msg = "";
		this.mafast="";

		currentPages = 1;
		listPages = new int[]{1, 2 , 3 , 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20};
		this.nppId="";
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
		this.dhRs = super.createSplittingData(super.getItems(), super.getSplittings(), "ngaychungtu desc, trangthai asc", query); //db.get(query);
	}

	public void init(String search) 
	{
		
		String query;	
		if (search.length() == 0)
		{
			query = "\n select a.pk_seq, a.ngaychungtu, d.ten as spTen, ( select sum(SOluong) from erp_rabundle_chitiet where rabundle_fk = a.pk_seq )  soluong, a.trangthai, b.ten as nguoitao, a.ngaytao, c.ten as nguoisua, a.ngaysua    " +
			  		"\n from Erp_RABUNDLE a inner join NhanVien b on a.nguoitao = b.pk_seq    " +
			  		"\n inner join nhanvien c on a.nguoisua = c.pk_seq inner join SanPham d on a.sanpham_fk = d.pk_seq " ;

			
		}
		else
		{
			query = search ;
		}
		System.out.println("LAY DON HANG----------------: " + query);
		this.createDhBeanList(query); 

		
		query = "\n select pk_seq, ten from NHAPHANPHOI where trangthai = '1' " ;
		this.nppRs = this.db.get(query);
	}

	public void DBclose() 
	{		
		try 
		{
			if(dhRs!=null){
				dhRs.close();
			}

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

		return 0;
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


	public String getMafast() {

		return this.mafast;
	}


	public void setMafast(String mafast) {
		this.mafast=mafast;

	}	boolean isSearch = false;

	public boolean getIsSearch() {
		return this.isSearch;
	}


	public void setIsSearch(boolean search) {
		this.isSearch = search;
	}


	ResultSet nppRs;
	public ResultSet getNppRs()
	{
		return nppRs;
	}
	public void setNppRs(ResultSet nppRs)
	{
		this.nppRs = nppRs;
	}

}

