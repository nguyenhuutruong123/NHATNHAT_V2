package geso.dms.distributor.beans.duyetbandunggia;

import java.sql.ResultSet;

public interface IDuyetbandunggiaNppList 
{
	public String getUserId();
	public void setUserId(String userId);
	

	public String getNam();
	public void setNam(String nam);
	public String getTrangthai();
	public void setTrangthai(String trangthai);
	public String getMsg();
	public void setMsg(String msg);
	public String getDiengiai();
	public void setDiengiai(String diengiai);
	public String getTuthang();
	public void setTuthang(String tuthang);
	public String getDenthang();
	public void setDenthang(String denthang);
	
	public ResultSet getTieuchiSKUInRs();
	public void setTieuchiSKUInRs(ResultSet tieuchiSKU);
	
	public String getNppId();
	public void setNppId(String nppId);
	public String getNppTen();
	public void setNppTen(String nppTen);
	public String getSitecode();
	public void setSitecode(String sitecode);
	
	public void init(String query);
	public String getView() ;

	public void setView(String view);
	public String getNppidlist();

	public void setNppidlist(String nppidlist) ;

	public ResultSet getRsnppid() ;

	public void setRsnppid(ResultSet rsnppid) ;

	public String getMienid();

	public void setMienid(String mienid);

	public ResultSet getRsMienid() ;

	public void setRsMienid(ResultSet rsMienid);
	
}
