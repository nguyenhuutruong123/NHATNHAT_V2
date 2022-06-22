package geso.dms.erp.beans.lenhsanxuat.imp;

import java.util.ArrayList;
import java.util.List;

import geso.dms.erp.beans.lenhsanxuat.IYeucau;
import geso.dms.erp.beans.phieuxuatkho.ISpDetail;

public class Yeucau implements IYeucau 
{
	String id;
	String ma;
	String ten;
	String soluongYC;
	String soluongDN;
	String soluongNhan;
	String soluongThung;
	String Tongsoluongdaxuat;
	String donvi;
	String trongluong;
	String thetich;
	
	String Ngayyeucau;
	//Chuyen kho
	String tonhientai;
	String solo;
	String vitriXuat;
	String vitriNhan;
	
	List<ISpDetail> spDetailList;
	List<ISpDetail> spDetail2List;

	public Yeucau()
	{
		this.id = "";
		this.ma = "";
		this.ten = "";
		this.soluongYC = "";
		this.soluongDN = "";
		this.soluongNhan = "";
		this.Tongsoluongdaxuat="0";
		this.donvi="";
		this.tonhientai = "";
		this.Ngayyeucau="";
		this.solo = "";
		this.vitriNhan = "";
		this.vitriXuat = "";
		this.soluongThung = "";
		
		this.spDetailList = new ArrayList<ISpDetail>();
		this.spDetail2List = new ArrayList<ISpDetail>();
	}
	
	public Yeucau(String id, String ma, String ten, String soluongYC, String soluongDN, String soluongN)
	{
		this.id = id;
		this.ma = ma;
		this.ten = ten;
		this.soluongYC = soluongYC;
		this.soluongDN = soluongDN;
		this.soluongNhan = soluongN;
		this.Ngayyeucau="";
		this.donvi="";
		
		this.Tongsoluongdaxuat="0";
		this.tonhientai = "";
		this.solo = "";
		this.vitriNhan = "";
		this.vitriXuat = "";
		this.soluongThung = "";
		
		this.spDetailList = new ArrayList<ISpDetail>();
		this.spDetail2List = new ArrayList<ISpDetail>();
	}
	
	public String getId() 
	{
		return this.id;
	}

	public void setId(String id)
	{
		this.id = id;
	}

	public String getMa() 
	{
		return this.ma;
	}

	public void setMa(String masp) 
	{
		this.ma = masp;
	}

	public String getSoluongYC()
	{
		return this.soluongYC;
	}

	public void setSoluongYC(String soluongYC)
	{
		this.soluongYC = soluongYC;
	}

	public String getSoluongDaNhan() 
	{
		return this.soluongDN;
	}

	public void setSoluongDaNhan(String danhan) 
	{
		this.soluongDN = danhan;
	}

	public String getSoluongNhan()
	{
		return this.soluongNhan;
	}

	public void setSoluongNhan(String nhan) 
	{
		this.soluongNhan = nhan;	
	}

	public List<ISpDetail> getSpDetailList()
	{
		return this.spDetailList;
	}

	public void setSpDetailList(List<ISpDetail> spDetailList) 
	{
		this.spDetailList = spDetailList;
	}

	public String getTen()
	{
		return this.ten;
	}

	public void setTen(String tensp) 
	{
		this.ten = tensp;
	}

	
	public String getTonhientai() 
	{
		return this.tonhientai;
	}

	public void setTonhientai(String tonkho) 
	{
		this.tonhientai = tonkho;
	}

	public String getSolo() 
	{
		return this.solo;
	}

	public void setSolo(String solo)
	{
		this.solo = solo;
	}

	public String getVitriXuat() 
	{
		return this.vitriXuat;
	}

	public void setVitriXuat(String vitri) 
	{
		this.vitriXuat = vitri;
	}

	public String getVitriNhan()
	{
		return this.vitriNhan;
	}

	public void setVitriNhan(String vitri) 
	{
		this.vitriNhan = vitri;
	}

	public List<ISpDetail> getSpDetail2List() 
	{
		return this.spDetail2List;
	}

	public void setSpDetail2List(List<ISpDetail> spDetailList) 
	{
		this.spDetail2List = spDetailList;
	}
 
	public String getTongSoluongDaXuat() {
		return this.Tongsoluongdaxuat;
	}
 
	public void setTongSoluongDaXuat(String tong_sl_daxuat) {
		this.Tongsoluongdaxuat=tong_sl_daxuat;
	}

	@Override
	public String getdonvi() {
		// TODO Auto-generated method stub
		return this.donvi;
	}

	@Override
	public void setdonvi(String ten) {
		// TODO Auto-generated method stub
		this.donvi=ten;
	}

	@Override
	public String getNgayyeucau() {
		// TODO Auto-generated method stub
		return this.Ngayyeucau;
	}

	@Override
	public void setNgayyeucau(String ngayyeucau) {
		// TODO Auto-generated method stub
		this.Ngayyeucau=ngayyeucau;
	}

	@Override
	public String getTrongLuong() {
		// TODO Auto-generated method stub
		return this.trongluong;
	}

	@Override
	public String getTheTich() {
		// TODO Auto-generated method stub
		return this.thetich;
	}
	public void setTrongLuong(String trongluong){
		this.trongluong = trongluong;
	}
	public void setTheTich(String thetich){
		this.thetich = thetich;
	}
	
	public String getSoluongThung() 
	{
		return this.soluongThung;
	}

	public void setSoluongThung(String soluongThung) 
	{
		this.soluongThung = soluongThung;
	}
	
}
