package geso.dms.center.beans.thanhthinongthon;

import java.io.Serializable;
import java.sql.ResultSet;

public interface IThanhthinongthon extends Serializable 
{
	public String getUserId();
	public void setUserId(String userId);
	public String getId();
	public void setId(String id);
	public String getTen();
	public void setTen(String tenkhuvuc);
	public String getDiengiai();
	public void setDiengiai(String diengiai);
	public String getTrangthai();
	public void setTrangthai(String trangthai);
	public String getNgaytao();
	public void setNgaytao(String ngaytao);
	public String getNguoitao();
	public void setNguoitao(String nguoitao);
	public String getNgaysua();
	public void setNgaysua(String ngaysua);	
	public String getNguoisua();
	public void setNguoisua(String nguoisua);
	public String getMessage();
	public void setMessage(String msg);
	
	
	public boolean CreateThanhthinongthon();
	public boolean UpdateThanhthinongthon();

	
	
	
	public void closeDB();
}
