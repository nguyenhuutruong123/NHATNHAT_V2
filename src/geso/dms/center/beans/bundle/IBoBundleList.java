package geso.dms.center.beans.bundle;

import geso.dms.center.util.IPhan_Trang;

import java.io.Serializable;
import java.sql.ResultSet;

public interface IBoBundleList extends IPhan_Trang, Serializable
{	
	public String getUserId(); 
	public void setUserId(String userId);
	
	
	public String getTrangthai();
	public void setTrangthai(String trangthai);
	
	public String getMafast();
	public void setMafast(String mafast);
	
	public String getTungay();
	public void setTungay(String tungay);
	public String getDenngay();
	public void setDenngay(String denngay);
	
	
	public String getNppId();
	public void setNppId(String nppId);

	public ResultSet getDonhangRs();
	public void setDonhangRs(ResultSet dhRs);
	
	public void init(String search);
	public void DBclose();
	
	public String getMsg();
	public void setMsg(String msg);
	
	public int getCurrentPage();
	public void setCurrentPage(int current);
	
	public int[] getListPages();
	public void setListPages(int[] listPages);
	public int getLastPage();
	
	
	public ResultSet getNppRs();
	public void setNppRs(ResultSet nppRs);
	
}

