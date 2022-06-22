package geso.dms.distributor.beans.ctkhuyenmai.imp;

import geso.dms.distributor.beans.ctkhuyenmai.ISanpham;

public class Sanpham implements ISanpham, Comparable<Object>
{
	private static final long serialVersionUID = -9217977546733610214L;
	
	private String masp;
	private String tensp;
	private double soluongcan;
	private float dongia;
	private double thanhtien;
	private double slAvaiable;
	private float trongso;
	
	private double slThungCan;
	private Double slThungAvai;
	private boolean comprareTongluong;
	
	public Sanpham()
	{
		this.masp = "";
		this.tensp = "";
		this.soluongcan = 0;
		this.dongia = 0;
		this.thanhtien = 0;
		this.slAvaiable = 0;
		this.trongso = 0;
		
		this.slThungCan = 0;
		this.slThungAvai = (double) 0;
		this.comprareTongluong = false; //compareTo theo tongtien
	}
	
	public Sanpham(String masp, String tensp, double soluongsudung, float dongia, double slgconlai, boolean comprareTongluong)
	{
		this.masp = masp;
		this.tensp = tensp;
		this.soluongcan = soluongsudung;
		this.dongia = dongia;
		this.slAvaiable = slgconlai;
		this.thanhtien = this.dongia * this.slAvaiable;
		
		this.trongso = 0;
		this.slThungCan = 0;
		this.slThungAvai = (double) 0;
		this.comprareTongluong = false;
	}
	
	public void setMasp(String maSp)
	{
		this.masp = maSp;		
	}

	public String getMasp() 
	{	
		return this.masp;
	}
	
	public void setTensp(String tenSp)
	{
		this.tensp = tenSp;
	}

	public String getTensp() 
	{
		return this.tensp;
	}
	
	public void setSoluongcan(double soluongcan)
	{
		this.soluongcan = soluongcan;		
	}
	
	public double getSoluongcan() 
	{	
		return this.soluongcan;
	}
	
	public void setDongia(float dongia)
	{
		this.dongia = dongia;	
	}
	
	public float getDongia() 
	{		
		return this.dongia;
	}
	
	public void setThanhtien(double thanhtien)
	{
		this.thanhtien = thanhtien;		
	}
	
	public double getThanhtien() 
	{		
		return this.thanhtien;
	}
	
	public void setSoluongAvaiable(double slAvaiable) 
	{
		this.slAvaiable = slAvaiable;		
	}
	
	public double getSoluongAvaiable()
	{		
		return this.slAvaiable;
	}

	public int compareTo(Object obj) 
	{
		double thanhtien = ((Sanpham)obj).getThanhtien();
		double soluong = ((Sanpham)obj).getSoluongcan();
		
		float trongso = ((Sanpham)obj).getTrongso();
		
		if(this.getTrongso() < trongso)
			return 1;
		
		if(this.getTrongso() > trongso)
			return -1;
		
		if(this.getTrongso() == trongso)
		{
			if(this.comprareTongluong == false) 						//CompareTo tongtien
			{
				if(this.getThanhtien() < thanhtien)
					return -1;
				if(this.getThanhtien() > thanhtien)
					return 1;
			}
			else							//CompareTo tongluong
			{
				if(this.getSoluongcan() < soluong)
					return -1;
				if(this.getSoluongcan() > soluong)
					return 1;
			}
			
		}
		
		return 0;
	}

	public void setSoluongThungCan(double soluongcan2)
	{
		this.slThungCan = soluongcan2;
	}

	public double getSoluongThungCan() 
	{
		return this.slThungCan;
	}

	public void setSoluongThungAvaiable(Double slThungAvai2) 
	{
		this.slThungAvai = slThungAvai2;
	}

	public Double getSoluongThungAvaiable() 
	{
		return this.slThungAvai;
	}
	
	public void setTrongso(float trongso) 
	{
		this.trongso = trongso;
	}

	public float getTrongso() 
	{
		return this.trongso;
	}

	

	

}
