package geso.dms.distributor.beans.donhangctv;
import geso.dms.center.util.IPhan_Trang;

import java.io.Serializable;
import java.sql.ResultSet;

public interface IDonhangctvList extends Serializable, IPhan_Trang
{
	public String getUserId();
	public void setUserId(String userId);

	public String getTungayTao();
	public void setTungayTao(String tungay);
	public String getDenngayTao();
	public void setDenngayTao(String denngay);
	
	public String getSophieu();
	public void setSophieu(String sophieu);
	
	public String getDuyetSS();
	public void setDuyetSS(String duyetss);
	
	public ResultSet getNhapkhoRs();
	public void setNhapkhoRs(ResultSet nkRs);
	
	public ResultSet getKhRs();
	public void setKhRs(ResultSet khrs);
	
	public String getKhId();
	public void setKhId(String KhId);
	
	public String getTrangthai();
	public void setTrangthai(String trangthai);
	
	public String getMsg();
	public void setMsg(String msg);
	
	public String getNppId();
	public void setNppId(String nppId);
	public String getNppTen();
	public void setNppTen(String nppTen);
	public String getSitecode();
	public void setSitecode(String sitecode);
	
	public String getSochungtu();
	public void setSochungtu(String sochungtu);
	
	public String getTimkiem();
	public void setTimkiem(String timkiem);
	public void init(String search);
	public void DBclose();
	
	public String getLoaidonhang();
	public void setLoaidonhang(String loaidonhang);
	
	public String getTenNVBH();
	public void setTenNVBH(String tenNVBH);
	
	public String getTenSP();
	public void setTenSP(String tenSP);
	
	//PHÂN QUYỀN THEO LOẠI NHÂN VIÊN ĐĂNG NHẬP
	public String getLoainhanvien();
	public void setLoainhanvien(Object loainhanvien);
	public String getDoituongId();
	public void setDoituongId(Object doituongId);
	public ResultSet getRsxemctv();

	public void setRsxemctv(ResultSet rsxemctv);
	public void setRsxemCTV(String sql);
	
	
}
