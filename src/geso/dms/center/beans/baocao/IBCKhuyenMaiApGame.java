package geso.dms.center.beans.baocao;

import geso.dms.center.db.sql.dbutils;

import java.sql.ResultSet;

public interface IBCKhuyenMaiApGame {

	public String getUserId();
	public void setUserId(String userId);
	
	public String getNppId();
	public void setNppId(String nppId);
	
	public String getDdkdId();
	public void setDdkdId(String ddkdId);
	
	public String getTungay();
	public void setTungay(String tungay);
	
	public String getDenngay();
	public void setDenngay(String denngay);
	
	public ResultSet getRsNpp();
	public void setRsNpp(ResultSet rsNpp);
	
	public ResultSet getRsDdkd() ;
	public void setRsDdkd(ResultSet rsDdkd);
	
	public dbutils getDb();
	public void setDb(dbutils db);
	
	public String getMsg();
	public void setMsg(String msg);
	
	public String getUserTen();
	public void setUserTen(String userTen);
	
	public void init();
	
	public void DBclose();
}
