package geso.dms.center.beans.ctkhuyenmai;

import geso.dms.center.util.IPhan_Trang;
import geso.dms.center.util.Utility;
import geso.dms.distributor.db.sql.dbutils;

import java.io.Serializable;
import java.sql.ResultSet;

import javax.servlet.http.HttpServletRequest;

public interface ICtkhuyenmaiList extends Serializable, IPhan_Trang
{
	public String getNppTaoId() ;
	public String getPhanloai() ;
	public dbutils getDb() ;
	HttpServletRequest getRequestObj();
	void setRequestObj(HttpServletRequest request);
	
	public String getUserId();
	public void setUserId(String userId);

	public String getDiengiai();
	public void setDiengiai(String diengiai);
	
	public String getTungay();
	public void setTungay(String tungay);
	
	public String getDenngay();
	public void setDenngay(String denngay);
	
	public String getTrangthai();
	public void setTrangthai(String trangthai);
	
	public String getMessage();
	public void setMessage(String msg);
	
	public String getVungId();
	public void setVungId(String vungId);
	
	public String getKhuvucId();
	public void setKhuvucId(String khuvucId);
	
	public String getNppId();
	public void setNppId(String nppId);
	
	public String getPhanbo();
	public void setPhanbo(String phanbo);
	
	public ResultSet getNppRs();
	public void setNppRs(ResultSet nppRs);
	
	public void setVungRs(ResultSet vungRs);
	public ResultSet getVungRs();
	
	public ResultSet getKhuvucRs();
	public void setKhuvucRs(ResultSet khuvucRs);
	
	public ResultSet getCtkmList();
	public void setCtkmList(ResultSet ctkmlist);

	public void init(String search);
	public void DBclose();
	public void init_user();
	public String getView() ;
	public void setView(String view) ;
	public void setNpp(String npp) ;

	public String getvung();
	public void setvung(String vung);
	public String getkhuvuc();
	public void setkhuvuc(String khuvuc);
}
