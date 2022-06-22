package geso.dms.center.beans.thanhthinongthon;

import geso.dms.center.beans.thanhthinongthon.IThanhthinongthon;
import java.io.Serializable;
import java.sql.ResultSet;
import java.util.List;

public interface IThanhthinongthonList extends Serializable
{
	public String getUserId();
	public void setUserId(String userId);
	

	
	public String getTrangthai();
	public void setTrangthai(String trangthai);
	
	public List<IThanhthinongthon> getThanhthinongthonList();
	public void setThanhthinongthonList(List<IThanhthinongthon> dblist);
	
	public void init(String search);
	public void setMsg(String Msg);
	public String getMsg();
	
	public void closeDB();
}
