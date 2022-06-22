package geso.dms.distributor.beans.BCdoanhsohopdong;

import java.sql.ResultSet;

public interface IBCdoanhsohopdong {
   
	public void setnppId(String nppId);
	
	public String getnppId();
	public ResultSet getnpp();
	
	
	public void init();
	public void DBclose();
	public void createNPP();
	public void setStatus(String status);
	public String getStatus();

	public String getTungay();
	public void setTungay(String tungay);
	public String getDenngay();
	public void setDenngay(String denngay);
	public String getUserId();
	public void setUserId(String userId);
 
	
	
}
