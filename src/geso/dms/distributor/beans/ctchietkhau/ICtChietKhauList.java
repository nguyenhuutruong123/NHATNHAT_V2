package geso.dms.distributor.beans.ctchietkhau;

import java.io.Serializable;
import java.sql.ResultSet;
import java.util.List;

public interface ICtChietKhauList extends Serializable
{
	public void getNppInfo();
	public String getUserId();
	public void setUserId(String userId);	
	public String getTen();
	public void setTen(String tenbanggia);	
	public ResultSet getDvkd();
	public void setDvkd(ResultSet dvkd);
	public String getDvkdId();
	public void setDvkdId(String dvkdId);
	public String getTrangthai();
	public void setTrangthai(String trangthai);
		
	public List<ICtChietKhau> getBgblcList();
	public void setBgblcList(List<ICtChietKhau> bgblclist);
	public void init(String search);
	public void DbClose();
	public String getMsg();
	public void setMsg(String msg);
	public String getNppId();
}
