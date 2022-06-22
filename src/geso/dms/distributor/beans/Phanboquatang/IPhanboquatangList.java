package geso.dms.distributor.beans.Phanboquatang;

import java.io.Serializable;
import java.sql.ResultSet;
import java.util.List;

public interface IPhanboquatangList extends Serializable
{
	public String getUserId();
	public void setUserId(String userId);	
	public String getTen();
	public void setTen(String tenbanggia);	
	public String getTrangthai();
	public void setTrangthai(String trangthai);
	public void init(String search);
	public void DbClose();
	public String getMsg();
	public void setMsg(String msg);
	public ResultSet getRslist();
	public void setRslist(ResultSet rslist);
	public String getTungay();
	public void setTungay(String tungay);
	public String getDenngay();
	public void setDenngay(String denngay);

}
