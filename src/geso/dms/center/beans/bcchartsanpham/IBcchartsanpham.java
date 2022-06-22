package geso.dms.center.beans.bcchartsanpham;

import geso.dms.center.util.IPhan_Trang;

import java.io.Serializable;
import java.sql.ResultSet;

public interface IBcchartsanpham extends IPhan_Trang, Serializable
{
	public String getUserId();
	public void setUserId(String userId);

	public String getId();
	public void setId(String Id);
	
	public String getLoai();
	public void setLoai(String loai);
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
	public void init2();
	public void createRs();
	
	public ResultSet getSpRs();
	public void setSpId(String spid);
	public String getSpId();
	
	public Long[][] getDoanhsoSp();
	
	public void DbClose();
}
