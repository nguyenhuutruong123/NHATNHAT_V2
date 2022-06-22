package geso.dms.center.beans.bcchartdoanhthumien;

import geso.dms.center.util.IPhan_Trang;

import java.io.Serializable;
import java.sql.ResultSet;

public interface IBcchartdoanhthumien extends IPhan_Trang, Serializable
{
	public String getUserId();
	public void setUserId(String userId);

	public String getId();
	public void setId(String Id);
	public String getThang();
	public void setThang(String thang);
	public String getNam();
	public void setnam(String nam);
	public String getMsg();
	public void setMsg(String msg);
	public String[] getArrTenMien();
	public String[] getArrIDMien();
	public ResultSet getRs();
	public void init();

	public void DbClose();
}
