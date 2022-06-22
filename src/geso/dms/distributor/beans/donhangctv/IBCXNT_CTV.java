package geso.dms.distributor.beans.donhangctv;

import java.sql.ResultSet;

public interface IBCXNT_CTV
{
	public void setuserId(String userId);
	public String getuserId();

	public void setuserTen(String userTen);
	public String getuserTen();

	public void setnppId(String nppId);
	public String getnppId();

	public void setnppTen(String nppTen);
	public String getnppTen();

	public void setkhId(String khId);
	public String getkhId();

	public void setkhTen(String khTen);
	public String getkhTen();
	
	public void setRsKhachhang(ResultSet rsKhachhang);
	public ResultSet getRsKhachhang();
	
	public void setMsg(String msg);
	public String getMsg();

	public void setkhuvucId(String khuvucId);
	public String getkhuvucId();

	public void setRsKhuvuc(ResultSet rsKhuvuc);
	public ResultSet getRsKhuvuc();

	public void setMonth(String month);
	public String getMonth();

	public void setYear(String year);
	public String getYear();

	public ResultSet getRsTinhthanh();
	public void setRsTinhthanh(ResultSet rsTinhthanh);
	
	public String getTinhthanhid();
	public void setTinhthanhid(String tinhthanhid);
	
	public void setSpId(String spid);
	public String getSpId();
	
	public void setDataRs(String query);
	public ResultSet getDataRs();
	
	public void setIsDlpp(String isdlpp);
	public String getIsDlpp();
	
	public void init_dlpp_ctv(String sqlSelect);
}
