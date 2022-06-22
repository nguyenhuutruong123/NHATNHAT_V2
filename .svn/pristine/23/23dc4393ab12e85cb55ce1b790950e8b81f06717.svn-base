package geso.dms.distributor.beans.nhaphangchinhanh;

import geso.dms.distributor.beans.nhaphangchinhanh.imp.SpNhaphang;

import java.sql.ResultSet;
import java.util.List;

public interface INhaphang
{
	public String getUserId();
	public void setUserId(String userId);
	
	public String getId();
	public void setId(String Id);
	
	public String getNppId();
	public void setNppId(String nppId);

	public String getNgaynhap();
	public void setNgaynhap(String ngaynhap);
	
	public String getNgayyeucau();
	public void setNgayyeucau(String ngayyeucau);

	public String getGhichu();
	public void setGhichu(String ghichu);
	
	public String getDondathangId();
	public void setDondathangId(String kbhId);
	public ResultSet getDondathangRs();
	public void setDondathangRs(ResultSet ddhRs);
	
	public String getKhonhanId();
	public void setKhonhanId(String khonhanId);
	public ResultSet getKhonhanRs();
	public void setKhonhanRs(ResultSet khonhanRs);
	
	public String getSochungtu();
	public void setSOchungtu(String sochungtu);
	 
  
	
	public String getMsg();
	public void setMsg(String msg);
	
	public boolean create();
	public boolean update();
	public boolean chot();
	
	public void createRs();
	public void init();
	
	public void DBclose();
	
	public String getLoaiDh();
	public void setLoaiDh(String loaidh);
	public void setListSp(List<SpNhaphang> list);
	public List<SpNhaphang> getListSp();
	
}
