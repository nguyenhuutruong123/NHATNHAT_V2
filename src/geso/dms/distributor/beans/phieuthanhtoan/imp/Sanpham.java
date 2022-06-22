package geso.dms.distributor.beans.phieuthanhtoan.imp;

import java.util.ArrayList;
import java.util.List;

import geso.dms.distributor.beans.phieuthanhtoan.IHoadon;
import geso.dms.distributor.beans.phieuthanhtoan.ISanpham;

public class Sanpham implements ISanpham
{
	private static final long serialVersionUID = -9217977546733610214L;
	String PK_SEQ;
	String id;
	String masp;
	String tensp;
	String tenXHD;
	String soluong;
	String tiente;
	String donvitinh;
	String dongia;
	String thanhtien;
	String nhomhang;
	String ngaynhan;
	String khonhan;
	String dungsai;
	String mnlId;
	/* 31-08-2012 Them TrungTamChiPhi_FK,Thue Nhap Khau */
	String TrungTamChiPhi_FK;
	String ThueNhapKhau;
	String PhanTramThue;
	float TyGiaQuyDoi;
	String Thanhtientruocthue;
	String Tienthue;
	
	String tenHd = "";
	List<IHoadon> hdList = new ArrayList<IHoadon>();
	
	/* 31-08-2012 */
	public Sanpham()
	{
		this.PK_SEQ="";
		this.id = "";
		this.masp = "";
		this.tensp = "";
		this.soluong = "";
		this.Tienthue="";
		this.tiente = ""; // 4
		this.donvitinh = "";
		this.dongia = "";
		this.thanhtien = "";
		this.nhomhang = "";
		this.ngaynhan = "";
		this.khonhan = "";
		this.dungsai = "0";
		this.TrungTamChiPhi_FK = "";
		this.ThueNhapKhau = "";
		this.PhanTramThue = "";
		this.tenXHD = "";
		this.mnlId = "";
		this.Thanhtientruocthue="0";
	}

	public Sanpham(String[] param)
	{
		this.id = param[0];
		this.masp = param[1];
		this.tensp = param[2];
		this.soluong = param[3];
		this.tiente = param[4];
		this.donvitinh = param[5];
		this.dongia = param[6];
		this.thanhtien = param[7];
		this.nhomhang = param[8];
		this.ngaynhan = param[9];
		this.khonhan = param[10];
		this.ThueNhapKhau = param[11];
		this.PhanTramThue = param[12];
		this.TrungTamChiPhi_FK = param[13];
		this.dungsai = "0";
		this.tenXHD = "";
	}

	public Sanpham(String spId, String spMa, String spTen, String soluong, String tiente, String dvt, String dongia, String thanhtien, String nhomhang, String ngaynhan,
		String khonhan, String TrungTamChiPhi_FK, String thuenhapkhau, String phantramthue, String mnlId)
	{
		this.id = spId;
		this.masp = spMa;
		this.tensp = spTen;
		this.soluong = soluong;
		this.tiente = tiente;
		this.donvitinh = dvt;
		this.dongia = dongia;
		this.thanhtien = thanhtien;
		this.nhomhang = nhomhang;
		this.ngaynhan = ngaynhan;
		this.khonhan = khonhan;
		this.dungsai = "0";
		this.TrungTamChiPhi_FK = TrungTamChiPhi_FK;
		this.ThueNhapKhau = thuenhapkhau;
		this.PhanTramThue = phantramthue;
		this.mnlId = mnlId;
	}

	public String getId()
	{
		return this.id;
	}

	public void setId(String id)
	{
		this.id = id;
	}

	public String getMasanpham()
	{
		return this.masp;
	}

	public void setMasanpham(String masp)
	{
		this.masp = masp;
	}

	public String getTensanpham()
	{
		return this.tensp;
	}

	public void setTensanpham(String tensp)
	{
		this.tensp = tensp;
	}

	public String getSoluong()
	{
		return this.soluong;
	}

	public void setSoluong(String soluong)
	{
		this.soluong = soluong;
	}

	public String getSoluong_bk()
	{
		return this.soluong;
	}

	public void setSoluong_bk(String soluong)
	{
		this.soluong = soluong;
	}

	public String getDonvitinh()
	{
		return this.donvitinh;
	}

	public void setDonvitinh(String donvitinh)
	{
		this.donvitinh = donvitinh;
	}

	public String getDongia()
	{
		return this.dongia;
	}

	public void setDongia(String dongia)
	{
		this.dongia = dongia;
	}

	public String getTiente()
	{
		return this.tiente;
	}

	public void setTiente(String tiente)
	{
		this.tiente = tiente;
	}

	public String getThanhtien()
	{
		return this.thanhtien;
	}

	public void setThanhtien(String thanhtien)
	{
		this.thanhtien = thanhtien;
	}

	public String getNhomhang()
	{
		return this.nhomhang;
	}

	public void setNhomhang(String nhomhang)
	{
		this.nhomhang = nhomhang;
	}

	public String getNgaynhan()
	{
		return this.ngaynhan;
	}

	public void setNgaynhan(String ngaynhan)
	{
		this.ngaynhan = ngaynhan;
	}

	public String getKhonhan()
	{
		return this.khonhan;
	}

	public void setKhonhan(String khonhan)
	{
		this.khonhan = khonhan;
	}

	public String getDungsai()
	{
		return this.dungsai;
	}

	public void setDungsai(String dungsai)
	{
		this.dungsai = dungsai;
	}

	public String getTrungTamChiPhi_FK()
	{
		return this.TrungTamChiPhi_FK;
	}

	public void setTrungTamChiPhi_FK(String TrungTamChiPhi_FK)
	{
		this.TrungTamChiPhi_FK = TrungTamChiPhi_FK;
	}

	public String getThueNhapKhau()
	{
		return this.ThueNhapKhau;
	}

	public void setThueNhapKhau(String thuenhapkhau)
	{
		this.ThueNhapKhau = thuenhapkhau;
	}

	public void setTyGiaQuyDoi(float tygiaquydoi)
	{
		this.TyGiaQuyDoi = tygiaquydoi;
	}

	public float getTyGiaQuyDoi()
	{
		return this.TyGiaQuyDoi;
	}

	public void setPhanTramThue(String phantramthue)
	{
		this.PhanTramThue = phantramthue;
	}

	public String getPhanTramThue()
	{
		return this.PhanTramThue;
	}


	public String getPK_SEQ()
	{
		return this.PK_SEQ;
	}

	
	public void setPK_SEQ(String pk_seq)
	{
		this.PK_SEQ=pk_seq;
	}

	public String getTenXHD() 
	{
		return this.tenXHD;
	}

	public void setTenXHD(String tenXHD) 
	{
		this.tenXHD = tenXHD;
	}

	public String getMNLId() 
	{
		return this.mnlId;
	}

	public void setMNLId(String mnlId) 
	{
		this.mnlId = mnlId;
	}

	@Override
	public String getTenHD() {
		return this.tenHd;
	}

	@Override
	public void setTenHD(String tenHD) {
		this.tenHd = tenHD;
	}

	@Override
	public void setThanhTienTruocThue(String thanhtientruocthue) {
		// TODO Auto-generated method stub
		this.Thanhtientruocthue=thanhtientruocthue;
	}

	@Override
	public String getThanhTienTruocThue() {
		// TODO Auto-generated method stub
		return this.Thanhtientruocthue;
	}

	@Override
	public void setTienThue(String TienThue) {
		// TODO Auto-generated method stub
		this.Tienthue=TienThue;
	}

	@Override
	public String getTienThue() {
		// TODO Auto-generated method stub
		return this.Tienthue;
	}

	@Override
	public List<IHoadon> getHoadonList() {
		// TODO Auto-generated method stub
		return this.hdList;
	}

	@Override
	public void setHoadonList(List<IHoadon> hdList) {
		// TODO Auto-generated method stub
		this.hdList=hdList;
	}

}
