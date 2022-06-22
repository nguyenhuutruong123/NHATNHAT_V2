package geso.dms.distributor.beans.dondathang;

import geso.dms.center.util.IPhan_Trang;

import java.io.Serializable;
import java.sql.ResultSet;

public interface IDondathangList extends  Serializable, IPhan_Trang
{
	public String getUserId();
	
	public void setUserId(String userId);
	
	public String getNppId();
	public void setNppId(String nppId);
	
	public String getNppTen();
	public void setNppTen(String nppTen);

	public ResultSet getDhList();
	public void setDhList(ResultSet dhList);
	
	public String getSKU();
	public void setSKU(String sku);
	
	public String getTungay();
	public void setTungay(String tungay);
	
	public String getDenngay();
	public void setDenngay(String denngay);
	
	public String getTrangthai();
	public void setTrangthai(String trangthai);

	public String getMalist();
	public void setMalist(String malist);
	
	public void createDdhlist(String querystr);
	public void DBclose();
}