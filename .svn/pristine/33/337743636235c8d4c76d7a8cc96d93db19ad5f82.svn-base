package geso.dms.distributor.beans.noptiennpp;

import geso.dms.center.db.sql.dbutils;
import geso.dms.center.util.Utility;

import java.io.Serializable;
import java.sql.ResultSet;
import java.util.Hashtable;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public interface INopTienNPP extends Serializable
{
	public String getDoituong() ;
	public void setDoituong(String doituong) ;
	public dbutils getDb();
	public void DBclose();
	public String getUserId();
	public void setUserId(String userId);
	public String getId() ;
	public void setId(String id) ;
	public String getNgaygiaodich() ;
	public void setNgaygiaodich(String ngaygiaodich) ;
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
	public ResultSet getKhachHangRs();
	public ResultSet getHoaDonRs();
	public String getKhId() ;
	public void setKhId(String khId) ;
	public void setDhIdList(String[] xxx);
	public List<String> getDhIdList();
	public void getNppInfo();
	public void Lay_SanPham_Theo_HoaDon();
	public ResultSet createKhInfoRs();
	public Hashtable<String, Double> getNpp_sotien() ;
	public void setNpp_sotien(String[]nppIds,String[]sotien) ;
}

