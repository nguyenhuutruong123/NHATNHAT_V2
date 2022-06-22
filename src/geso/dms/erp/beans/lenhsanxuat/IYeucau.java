package geso.dms.erp.beans.lenhsanxuat;

import geso.dms.erp.beans.phieuxuatkho.ISpDetail;

import java.util.List;

public interface IYeucau 
{
	public String getId();
	public void setId(String id);
	public String getMa();
	public void setMa(String masp);
	public String getTen();
	public void setTen(String tensp);
	
	
	public String getdonvi();
	public void setdonvi(String tensp);
 
	public String getSoluongYC();
	public void setSoluongYC(String soluongYC);
	public String getSoluongDaNhan();
	public void setSoluongDaNhan(String danhan);
	public String getSoluongThung() ;
	public void setSoluongThung(String soluongThung) ;
	
	public String getTongSoluongDaXuat();
	public void setTongSoluongDaXuat(String tong_sl_daxuat);
	public String getSoluongNhan();
	public void setSoluongNhan(String nhan);
	
	
	public String getNgayyeucau();
	public void setNgayyeucau(String ngayyeucau);
	
	//Them phan chuyen kho NL
	public String getTonhientai();
	public void setTonhientai(String tonkho);
	public String getSolo();
	public void setSolo(String solo);
	public String getVitriXuat();
	public void setVitriXuat(String vitri);
	public String getVitriNhan();
	public void setVitriNhan(String vitri);
	
	public List<ISpDetail> getSpDetailList();
	public void setSpDetailList(List<ISpDetail> spDetailList);
	
	
	public List<ISpDetail> getSpDetail2List();
	public void setSpDetail2List(List<ISpDetail> spDetailList);
	public String getTrongLuong();
	public String getTheTich();
	public void setTrongLuong(String trongluong);
	public void setTheTich(String thetich);
}
