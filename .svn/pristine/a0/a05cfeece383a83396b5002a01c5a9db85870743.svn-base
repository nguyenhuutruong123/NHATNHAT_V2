package geso.dms.center.beans.khuyenmaidacbiet;

import geso.dms.center.db.sql.dbutils;
import geso.dms.center.util.Utility;

import java.io.Serializable;
import java.sql.ResultSet;
import java.util.Hashtable;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public interface IKhuyenMaiDacBiet extends Serializable
{
	
	public List<String[]> getData();
	public void setData(List<String[]> data) ;
	public dbutils getDb();
	public void DBclose();
	public String getUserId();
	public void setUserId(String userId);
	public String getId() ;
	public void setId(String id) ;
	public String getTungay() ;
	public void setTungay(String ngaygiaodich) ;
	
	public String getDenngay() ;
	public void setDenngay(String denngay) ;
	
	public String getMessage() ;
	public void setMessage(String msg) ;
	public String getNppId() ;
	public void setNppId(String nppId) ;
	public void setSpList(List<ISanpham> splist) ;
	public List<ISanpham> getSpList();
	public boolean CreateXuatKho() ;
	public boolean UpdateXuatKho() ;
	public void init();
	public int getIsDisplay() ;
	public void setIsDisplay(int isDisplay) ;
	public ResultSet getSanphamRs();
	public ResultSet getNppRs();
	public String getGhichu() ;
	public void setGhichu(String ghichu) ;
	public String getScheme() ;
	public void setScheme(String scheme) ;
	
	public String getPt_chietkhau() ;
	public void setPt_chietkhau(String pt_chietkhau) ;
	public ResultSet getKhoRs();
	public String getKhoId() ;
	public void setKhoId(String khoId) ;
	public String getKhonhan_fk() ;

	public void setKhonhan_fk(String khonhan_fk);
}

