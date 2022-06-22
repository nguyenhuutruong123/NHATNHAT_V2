package geso.dms.center.beans.bienbandoichieucongnoHO;

import geso.dms.center.util.IPhan_Trang;

import java.io.Serializable;
import java.sql.ResultSet;

public interface IBienbandoichieucongnoHO extends Serializable, IPhan_Trang
{
	public String getUserId();
	public void setUserId(String userId);

	public String getTungay();
	public void setTungay(String tungay);
	
	public String getDenngay();
	public void setDenngay(String denngay);
	
	public String getnppId();
	public void setnppId(String nppId);
	
	public String getNppTen();
	public void setNppTen(String nppTen);
	
	public ResultSet getNppRs();
	public void setNppRs(ResultSet nppRs);
	
	public ResultSet getKhRs();
	public void setKhRs(ResultSet KhRs);
	
	public ResultSet getKhRsETC();
	public void setKhRsETC(ResultSet KhRsETC);

	public String getKhId() ;

	public void setKhId(String khId);

	public String getMsg();
	public void setMsg(String msg);
	
	public void init();
	public void DBclose();
}
