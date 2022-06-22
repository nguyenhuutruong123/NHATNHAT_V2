package geso.dms.center.beans.bundle.imp;

import geso.dms.center.beans.bundle.ISpDetail;

 
public class SpDetail implements ISpDetail
{
	String id = "";
	String ma = "";
	String ten = "";
	String solo = "";
	String soluong = "";
	String soluongton = "";
	String khu = "";
	String vitri = "";
	String vitriId = "";
	String dvt = "";
	double trongluong = 0;
	double thetich = 0;

	String ngaysanxuat = "";
	String ngayhethan = "";
	String ngaynhapkho = "";
	
	public String getNgaysanxuat() {
		return ngaysanxuat;
	}

	public void setNgaysanxuat(String ngaysanxuat) {
		this.ngaysanxuat = ngaysanxuat;
	}

	public String getNgayhethan() {
		return ngayhethan;
	}

	public void setNgayhethan(String ngayhethan) {
		this.ngayhethan = ngayhethan;
	}

	public SpDetail()
	{
		this.id = "";
		this.ma = "";
		this.ten = "";
		this.solo = "";
		this.soluong = "";
		this.khu = "";
		this.vitri = "";
		this.vitriId = "";
		this.dvt = "";
		this.soluongton="";
		this.ngayhethan="";
		this.ngaysanxuat="";
		this.ngaynhapkho = "";
			
	}

	public SpDetail(String ma, String solo, String soluong, String khu, String vitri, String vitriId)
	{
		this.ma = ma;
		this.solo = solo;
		this.soluong = soluong;
		this.khu = khu;
		this.vitri = vitri;
		this.vitriId = vitriId;
	}

	public SpDetail(String id, String ma, String solo, String soluong, String khu, String vitri, String vitriId)
	{
		this.id = id;
		this.ma = ma;
		this.solo = solo;
		this.soluong = soluong;
		this.khu = khu;
		this.vitri = vitri;
		this.vitriId = vitriId;
	}
	public SpDetail(String ma, String solo, String soluong,String donvi)
	{
		this.ma = ma;
		this.solo = solo;
		this.soluong = soluong;
		this.dvt=donvi;
	}
	public String getMa()
	{
		return this.ma;
	}

	public void setMa(String masp)
	{
		this.ma = masp;
	}

	public String getSolo()
	{
		return this.solo;
	}

	public void setSolo(String solo)
	{
		this.solo = solo;
	}

	public String getKhu()
	{
		return this.khu;
	}

	public void setKhu(String khu)
	{
		this.khu = khu;
	}

	public String getVitri()
	{
		return this.vitri;
	}

	public void setVitri(String vitri)
	{
		this.vitri = vitri;
	}

	public String getSoluong()
	{
		return this.soluong;
	}

	public void setSoluong(String soluong)
	{
		this.soluong = soluong;
	}

	public String getVitriId()
	{
		return this.vitriId;
	}

	public void setVitriId(String vitriId)
	{
		this.vitriId = vitriId;
	}

	public String getId()
	{

		return this.id;
	}

	public void setId(String id)
	{
		this.id = id;

	}

	public String getDvt()
	{

		return this.dvt;
	}

	public void setDvt(String dvt)
	{
		this.dvt = dvt;

	}

	public String getTen()
	{

		return this.ten;
	}

	public void setTen(String ten)
	{
		this.ten = ten;

	}

	@Override
	public String getSoluongton() {
		// TODO Auto-generated method stub
		return this.soluongton;
	}

	@Override
	public void setSoluongton(String soluongton) {
		// TODO Auto-generated method stub
		this.soluongton=soluongton;
	}

	@Override
	public double getTrongLuong() {
		return this.trongluong;
	}

	@Override
	public void setTrongLuong(double trongluong) {
		this.trongluong = trongluong;
	}

	@Override
	public double getTheTich() {
		return thetich;
	}

	@Override
	public void setTheTich(double thetich) {
		this.thetich = thetich;
	}

	public String getNgaynhapkho() {
		return ngaynhapkho;
	}
	public void setNgaynhapkho(String ngaynhapkho) {
		this.ngaynhapkho = ngaynhapkho;
	}
	
	
}
