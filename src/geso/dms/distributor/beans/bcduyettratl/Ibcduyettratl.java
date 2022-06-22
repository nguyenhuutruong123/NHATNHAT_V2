package geso.dms.distributor.beans.bcduyettratl;
import java.sql.ResultSet;
public interface Ibcduyettratl
{
	public String getUserId();
	public void setUserId(String userId);
	public void setCtkmId(String ctkmId);
	public String getCtkmId();
	public String getTrungtam();
	public void setTrungtam(String trungtam);
	public void DbClose();
	public String getMsg();
	public void setMsg(String msg);
	public void createRs();
	public void init();
	public void setCtkmRs(ResultSet ctkmRs);
	public ResultSet getCtkmRs();
	public String getTungay_ds() ;
	public void setTungay_ds(String tungay_ds);
	public String getDenngay_ds() ;
	public void setDenngay_ds(String denngay_ds) ;	
}
