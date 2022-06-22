package geso.dms.distributor.beans.ctchietkhau;

import java.io.Serializable;
import java.sql.ResultSet;
import java.util.Hashtable;

import javax.servlet.http.HttpServletRequest;

public interface ICtChietKhau extends Serializable
{
	
	public String getDisplay();
	public void setDisplay(String display);
	
	//Cac thuoc tinh 
	public String getUserId();
	public void setUserId(String userId);
	public String getId();
	public void setId(String id);
	public String getTen();
	public void setTen(String ten);
	public String getNgaytao();
	public void setNgaytao(String ngaytao);
	public String getNguoitao();
	public void setNguoitao(String nguoitao);
	public String getNgaysua();
	public void setNgaysua(String ngaysua);	
	public String getNguoisua();
	public void setNguoisua(String nguoisua);
	public String getMessage();
	public void setMessage(String msg);	
	
	public ResultSet getSanPhamList();
	public void setSanPhamList(ResultSet sanphamlist);
	public void setTrangthai(String trangthai);
	public String getTrangthai(); 
	public boolean CreateBgblc(HttpServletRequest request);
	public boolean UpdateBgblc(HttpServletRequest request);
	public void createRS();
	public void init();
	 
	public void DbClose();
	public Hashtable<String, Double> getSanpham_chietkhau();
	public void setSanpham_chietkhau(Hashtable<String, Double> sanpham_chietkhau) ;
	
	public String[] getNppArr();
	public void setNppArr(String[] nppArr);
	public String getDataupload();
	public void setDataupload(String dataupload);
	public String getAction();
	public void setAction(String action);
	public String getDataupload2();
	public void setDataupload2(String dataupload2);
	
	public String getTungay();
	public void setTungay(String tungay);
	public String getDenngay();
	public void setDenngay(String denngay);
	public String getKetthuc();
	public void setKetthuc(String ketthuc) ;
	public String getIsCopy();
	public void setIsCopy(String isCopy);
	
	
	public String getKhId() ;
	public void setKhId(String khId) ;
	public ResultSet getKhRs() ;
	public void setKhRs(ResultSet khRs) ;
	public ResultSet getBacsiRs() ;
	public void setBacsiRs(ResultSet bacsiRs) ;
	public ResultSet getHopdongRs() ;
	public void setHopdongRs(ResultSet hopdongRs) ;
	public String getHopdongId() ;
	public void setHopdongId(String hopdongId) ;
	public void getNppInfo();
}
