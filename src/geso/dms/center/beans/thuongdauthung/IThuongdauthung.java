package geso.dms.center.beans.thuongdauthung;

import java.sql.ResultSet;
import java.util.List;

public interface IThuongdauthung {


	public String getDisplay();
	public void setDisplay(String display);
	
	public void setID(double id);
	public double getID();

	public void setNguoiTao(String nguoitao);
	public String getNguoiTao();
	public void setNguoiSua(String nguoisua);
	public String getNguoiSua();
	public void setThang(int thang);
	public int getThang();
	public void setNam(int nam);
	public int getNam();
	public void setMessage(String strmessage);
	public String getMessage();


	public void setNgayTao(String ngaytao);
	public String getNgayTao();

	public void setNgaySua(String nguoisua);
	public String getNgaySua();

	public void setUserId(String userid);
	public String getUserId();
	
	public String getNppId();
	public void setNppId(String nppId);

	public String getTructhuocId();
	public void setTructhuocId(String tructhuocId);

	public void setDienGiai(String diengiai);
	public String getDienGiai();


	public void setTrangThai(String trangthai);
	public String getTrangThai();


	public void closeDB();

	public void setLuongkhacRs(String luongkhacRs) ;
	public ResultSet getLuongkhacRs() ;

	public boolean CreateLuongKhac();
	public boolean UpdateLuongKhac();


	public boolean chotLuongKhac();
	public boolean UnchotLuongKhac();
	public boolean DeleteLuongKhac();

	//this.soluong = 0;
	public double getSoluong();
	public void setSoluong(double soluong) ;
	//this.isThung = "0";
	public String getLoaict();
	public void setLoaict(String isThung) ;
	//this.thuongSR = 0;
	public double getThuongSR() ;
	public void setThuongSR(double thuongSR) ;
	//this.thuongSS = 0;
	public double getThuongSS() ;
	public void setThuongSS(double thuongSS) ;
	//this.thuongASM = 0;
	public double getThuongASM() ;
	public void setThuongASM(double thuongASM);
	
	
	public String getTungay() ;
	public void setTungay(String tungay) ;
	public String getDenngay();
	public void setDenngay(String denngay) ;

	
	public void setNsp_fk(String nsp_fk) ;
	public String getNsp_fk() ;
	public ResultSet getNspRs() ;
	
	public List<ISanpham> getSpList();
	public void setSpList(List<ISanpham> spList);
	
	public String[] getSanpham();
	public void setSanpham(String[] sanpham);
	
	
	public void createSpList();
	public void createRs();
	
	public ResultSet getDataRs();
	public void setDataRs(ResultSet dataRs);
	
	public String getNhomchitieuId();
	public void setNhomchitieuId(String nhomchitieuId) ;
	public ResultSet getNhomchitieuRs() ;
	public String getTenChiTieu(String tctctId) ;
	
	public String[] getNhomCtSelected();
	public void setNhomCtSelected(String[] nhomCtSelected);
	public String[] getThuong_Selected();
	public void setThuong_Selected(String[] thuong_Selected) ;
	
}
