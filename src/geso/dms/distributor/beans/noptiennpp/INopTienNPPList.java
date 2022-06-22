package geso.dms.distributor.beans.noptiennpp;

import geso.dms.center.db.sql.dbutils;
import geso.dms.center.util.IPhan_Trang;

import java.io.Serializable;
import java.sql.ResultSet;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

public interface INopTienNPPList extends IPhan_Trang, Serializable
{	
	public String getBarcode() ;
	public void setBarcode(String barcode) ;
	public String getKhachhangInfo() ;
	public void setKhachhangInfo(String khachhangInfo) ;
	public void getNppInfo();
	public String getUserId(); 
	public void setUserId(String userId);
	public dbutils getDb() ;
	
	
	public String getTrangthai();
	public void setTrangthai(String trangthai);
	
	public String getTungay();
	public void setTungay(String tungay);
	public String getDenngay();
	public void setDenngay(String denngay);
	
	
	public String getNppId();
	public void setNppId(String nppId);
	
	public ResultSet getDonhangRs();
	public void setDonhangRs(ResultSet dhRs);
	
	public void init(HttpServletRequest request);
	public void DBclose();
	
	public String getMsg();
	public void setMsg(String msg);
	
	public int getCurrentPage();
	public void setCurrentPage(int current);
	
	public int[] getListPages();
	public void setListPages(int[] listPages);
	
	public String getSohoadon() ;
	public void setSohoadon(String sohoadon) ;
	
	
}

