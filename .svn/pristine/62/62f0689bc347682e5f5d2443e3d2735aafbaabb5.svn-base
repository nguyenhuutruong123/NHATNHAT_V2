package geso.dms.center.beans.bundle.imp;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
 
import geso.dms.center.beans.bundle.ISanpham;
import geso.dms.center.beans.bundle.ISpDetail;


public class Sanpham implements ISanpham, Serializable
{	
	private static final long serialVersionUID = -9217977546733610214L;
	String id;
	String masp;
	String tensp;
	String soluong;
	String tonhientai;
	String donvitinh;
	String dongia;
	String ctkm;
	String chietkhau;
	String barcode;
	String quicach;
	String vat;
	
	String soluong1;
	String soluong2;
	String soluongThung;
	String SpId;
	String Ngayhethan = "";
	String SoLo;
	String Khoten;
	String KenhTen;
	String khoNVBH;
	String dongiaold;
	String ckkhuyenmai;
	String Ctkmid;
	String TrakmId;
	
	String ngaynhapkho ="";
	
	String nppId ="";
	String kbhId ="";
	String khoId ="";
	String spId ="";
	
	List<ISpDetail> spDetailList;
	
	public String getDongiaold() {
		return dongiaold;
	}

	public void setDongiaold(String dongiaold) {
		this.dongiaold = dongiaold;
	}

	public Sanpham()
	{
		this.id = "";
		this.masp = "";
		this.tensp = "";
		this.soluong = "";
		this.donvitinh = "";
		this.dongia = "";
		this.ctkm = "";
		this.chietkhau = "";
		this.tonhientai = "";
		this.barcode="";
		
		this.soluong1 = "";
		this.soluong2 = "";
		this.soluongThung = "";
		this.khoNVBH = "";
		this.vat= "";
		this.SoLo = "";
		this.Ngayhethan = "";
		this.dongiaold="";
		this.ckkhuyenmai = "0";
	}
	
	public Sanpham(String[] param)
	{
		this.id = param[0];
		this.masp = param[1];
		this.tensp = param[2];
		this.soluong = param[3];
		this.donvitinh = param[4];
		this.dongia = param[5];
		this.ctkm = param[6];
		this.chietkhau = param[7];
		this.tonhientai = "";
		if(param.length >8){
			this.barcode=param[8];
			System.out.println("qui cach la:"+param[9]);
			this.quicach=param[9];
		}
		
		this.soluong1 = "";
		this.soluong2 = "";
		this.soluongThung = "";
		this.khoNVBH = "";
		this.vat= "";
		this.SoLo = "";
		this.Ngayhethan = "";
		this.ckkhuyenmai = "0";
		/*this.dongiaold=param[10];*/
	}
	
	public Sanpham(String spId, String spMa, String spTen, String soluong, String dvt, String dongia, String ctkm, String chietkhau)
	{
		this.id = spId;
		this.masp = spMa;
		this.tensp = spTen;
		this.soluong = soluong;
		this.donvitinh = dvt;
		this.dongia = dongia;
		this.ctkm = ctkm;
		this.chietkhau = chietkhau;
		this.tonhientai = "";
		this.soluong1 = "";
		this.soluong2 = "";
		this.soluongThung = "";
		this.vat= "";
		this.SoLo = "";
		this.Ngayhethan = "";
		this.ckkhuyenmai = "0";
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
	
	public String getDonvitinh() 
	{
		return this.donvitinh;
	}
	
	public void setDonvitinh(String donvitinh) 
	{
		this.donvitinh = donvitinh;		
	}

	public String getCTKM() 
	{
		return this.ctkm;
	}

	public void setCTKM(String ctkm) 
	{
		this.ctkm = ctkm;
	}

	public String getDongia() 
	{
		return this.dongia;
	}

	public void setDongia(String dongia) 
	{
		this.dongia = dongia;
	}

	public String getChietkhau()
	{
		return this.chietkhau;
	}

	public void setChietkhau(String chietkhau) 
	{
		this.chietkhau = chietkhau;
	}

	public String getTonhientai() 
	{
		return this.tonhientai;
	}

	public void setTonhientai(String tonhientai) 
	{
		this.tonhientai = tonhientai;
	}


	public String getBarcode() {

		return this.barcode;
	}

	public void setBarcode(String barcode_) {

		this.barcode=barcode_;
	}


	public void setQuicach(String quicach) {

		this.quicach=quicach;
	}


	public String getQuicach() {

		return this.quicach;
	}

	
	public void setSoluong1(String soluong1) {
		
		this.soluong1 = soluong1;
	}

	
	public String getSoluong1() {
		
		return this.soluong1;
	}

	
	public void setSoluong2(String soluong2) {
		
		this.soluong2 = soluong2;
	}

	
	public String getSoluong2() {
		
		return this.soluong2;
	}

	
	public String getSoluongThung() {
		
		return this.soluongThung;
	}

	
	public void setSoluongThung(String soluongThung) {
		
		this.soluongThung = soluongThung;
	}

	
	public void setSPId(String spid) {
		
		this.SpId= spid;
	}

	
	public String getSPId() {
		
		return this.SpId;
	}

	
	public void setSolo(String solo) {
		
		this.SoLo=solo.trim();
	}

	
	public String getSOLo() {
		
		return this.SoLo;
	}

	
	public void setNgayHetHan(String ngayhethan) {
		
		this.Ngayhethan= ngayhethan.trim();
	}

	
	public String getNgayHetHan() {
		
		return this.Ngayhethan;
	}

	
	public void setKhoTen(String KhoTen) {
		
		this.Khoten=KhoTen;
	}

	
	public String getKhoTen() {
		
		return this.Khoten;
	}

	
	public void setKenhTen(String KenhTen) {
		
		this.KenhTen=KenhTen;
	}

	
	public String getKenhTen() {
		
		return this.KenhTen;
	}

	public void setKhoNVBH(String khoNVBH) {
		
		this.khoNVBH = khoNVBH;
	}


	public String getKhoNVBH() {
		
		return this.khoNVBH;
	}

	public String getVat() 
	{	
		return this.vat;
	}

	public void setVat(String vat) 
	{
		this.vat = vat;	
	}

	@Override
	public void setCKKhuyenmai(String string) {
		this.ckkhuyenmai = string;
	}

	@Override
	public String getCKKhuyenmai() {
		return this.ckkhuyenmai;
	}
	

	public List<ISpDetail> getSpDetailList() 
	{
		return this.spDetailList;
	}

	public void setSpDetailList(List<ISpDetail> spDetailList) 
	{
		this.spDetailList = spDetailList;
	}
	
	@Override
	public String getCTKMID()
	{
		// TODO Auto-generated method stub
		return this.Ctkmid;
	}

	@Override
	public void setCTKMID(String ctkmID)
	{
		// TODO Auto-generated method stub
		this.Ctkmid=ctkmID;
	}

	@Override
	public String getTRAKMID()
	{
		// TODO Auto-generated method stub
		return this.TrakmId;
	}

	@Override
	public void setTRAKMID(String TRAKMID)
	{
		// TODO Auto-generated method stub
		this.TrakmId= TRAKMID;
	}
	public String getNgaynhapkho() {
		return ngaynhapkho;
	}
	public void setNgaynhapkho(String ngaynhapkho) {
		this.ngaynhapkho = ngaynhapkho;
	}
	public String getKbhId() {
		return kbhId;
	}
	public void setKbhId(String kbhId) {
		this.kbhId = kbhId;
	}
	public String getKhoId() {
		return khoId;
	}
	public void setKhoId(String khoId) {
		this.khoId = khoId;
	}
	public String getNppId() {
		return nppId;
	}
	public void setNppId(String nppId) {
		this.nppId = nppId;
	}
	
	
}
