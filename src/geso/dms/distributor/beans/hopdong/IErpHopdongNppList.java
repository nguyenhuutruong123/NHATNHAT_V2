package geso.dms.distributor.beans.hopdong;

import geso.dms.center.util.IPhan_Trang;

import java.io.Serializable;
import java.sql.ResultSet;

public interface IErpHopdongNppList extends Serializable, IPhan_Trang
{
	
	public String getView();
	public void setView(String view) ;
	
	public String getUserId();
	public void setUserId(String userId);

	public String getMafast();
	public void setMafast(String mafast);
	
	public String getTungay();
	public void setTungay(String tungay);
	public String getDenngay();
	public void setDenngay(String denngay);
	
	public String getKhTen();
	public void setKhTen(String khTen);
	public ResultSet getKhRs();
	public void setKhRs(ResultSet khRs);
	
	public String getLoaiHD();
	public void setLoaiHD(String loaihd);
	
	public String getMaHD();
	public void setMaHD(String mahd);
	
	public ResultSet getLoaiHDRs();
	public void setLoaiHDRs(ResultSet loaihdRs);
	
	public ResultSet getDondathangRs();
	public void setDondathangRs(ResultSet ddhRs);
	
	public String getTrangthai();
	public void setTrangthai(String trangthai);
	
	public String getMsg();
	public void setMsg(String msg);
	
	public String getLoaidonhang();
	public void setLoaidonhang(String loaidonhang);
	
	public int getCurrentPage();
	public void setCurrentPage(int current);
	
	public int[] getListPages();
	public void setListPages(int[] listPages);
	
	public String getNppId();
	public void setNppId(String nppId);
	public String getNppTen();
	public void setNppTen(String nppTen);
	public String getSitecode();
	public void setSitecode(String sitecode);
	
	public void init(String search);
	public void DBclose();
}
