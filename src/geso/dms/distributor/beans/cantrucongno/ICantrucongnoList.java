package geso.dms.distributor.beans.cantrucongno;

import geso.dms.center.util.IPhan_Trang;

import java.io.Serializable;
import java.sql.ResultSet;

public interface ICantrucongnoList extends Serializable, IPhan_Trang
{
	public String getUserId();
	public void setUserId(String userId);
	
	public String getTungay();
	public void setTungay(String tungay);
	public String getDenngay();
	public void setDenngay(String denngay);
	
	public String getSohoadon();
	public void setSohoadon(String sohoadon);
	
	public String getTrangthai();
	public void setTrangthai(String trangthai);
	
	public ResultSet getDdkd();
	public void setDdkd(ResultSet ddkd);
	public String getDdkdId();
	public void setDdkdId(String ddkdId);
	
	public String getNppId();
	public void setNppId(String nppId);
	public String getNppTen();
	public void setNppTen(String nppTen);
	public String getSitecode();
	public void setSitecode(String sitecode);
	
	public ResultSet getBtcnList();
	public void setBtcnList(ResultSet btcnlist);
	
	public void init(String search);
	public void DBclose();
}