package geso.dms.distributor.beans.khachhang.imp;

import geso.dms.center.db.sql.Idbutils;
import geso.dms.distributor.beans.khachhang.IKhachhang;
import geso.dms.distributor.db.sql.dbutils;
import geso.dms.distributor.util.Utility;

import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.Serializable;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Base64;
import java.util.Date;
import java.util.Hashtable;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;

import org.apache.xmlbeans.impl.regex.REUtil;

public class Khachhang implements IKhachhang, Serializable
{
	private static final long serialVersionUID = -9217977546733610214L;

	String shopkey;
	String matkhau;
	String userId;
	String id;
	String ten;
	String diachi;
	String tpId;
	String qhId;
	String smartid;

	String sodienthoai;
	String masothue;
	String trangthai;
	String ngaytao;
	String nguoitao;
	String ngaysua;
	String nguoisua;
	String msg;
	String kt = "";
	String nppId;
	String nppTen;
	String sitecode;
	String type="";
	String hch;
	String kbh;
	String bgst;
	String vtch;
	String lch;
	String nch;
	String mck;
	String ghcn;
	String tenkyhd;
	
	ResultSet tp;
	ResultSet qh = null;

	ResultSet hangcuahang;
	String hchId;
	ResultSet kenhbanhang;
	String kbhId;


	ResultSet vtcuahang;
	String vtchId;
	ResultSet loaicuahang;
	String lchId ="null";
	ResultSet nhomcuahang;
	String nchId;
	ResultSet mucchietkhau;
	String mckId;
	ResultSet ghcongno;
	String ghcnId;
	ResultSet nkh_khList;
	ResultSet nkh_khSelected;
	String[] nkh_khIds;

	ResultSet nvgnRs;
	String nvgnId;

	String maFAST;
	String kokyhd;
	String chucuahieu;

	dbutils db;
	geso.dms.center.util.Utility utilCenter;

	String thanhtoan;
	String thanhtoanQUY;

	String loaiNPP;
	String pt_chietkhau;
	String mauhd;
	String khoId;
	String cmnd;


	String phuongxaId = "null";
	String phuongxa;
	ResultSet phuongxaRs ;
	String didong = "";
	String thanhthinongthonId = db.NULLVAL;
	ResultSet thanhthinongthonRs;
	double songayno  = 0;
	double sotienno = 0;

	String khachhangId;
	ResultSet khachhangRs;

	String diachigiaohang ="";

	String nppBanChungId = ""; //NPp khác bán chung khách hàng này với NPp gốc(HC)
	ResultSet nppBanChungRs ;

	String diabanId = "";
	ResultSet diabanRs ;

	String tenAnhDaiDien = "";
	String gioiTinh ="";

	String macu = "";
	
	String view = "";
	String moiquanhe = "";
	String thoigiangap = "";
	String[] anhArr ;
	
	
	
	// chiet khau khach hahg
	int pageX= 0;
	int pageY = 0;

	int count = 0;
	ResultSet sanphamRs,ctckRs;
	String[]spIds;
	String[] chietkhauSp;
	String[] ctckIds;
	//	
	ResultSet khuvucRs;
	//Thêm trường vào dữ liệu nền
	String nguoimuahang, sonha, tenduong, apto, vochong, ngsinh_vochong, con1, con2, ngsinh_con1, ngsinh_con2;
	String ghichu, toado_lat, toado_long, taitro, ngaytaitro,tencuahieu,vitri,mahd,khuvucId;
	ResultSet khoSAP;
	String makhoSAP = "";	

	
	public String getAnh(int stt) {
		try
		{
			return anhArr[stt];
		}
		catch (Exception e) {
			// TODO: handle exception
			return "";
		}
		
	}
	public void setAnh(String tenANh,int stt) 
	{
		try
		{
			anhArr[stt]=tenANh;
		}
		catch (Exception e) {
			// TODO: handle exception
			
		}
	}
	
	public String getMoiquanhe() {
		return moiquanhe;
	}
	public void setMoiquanhe(String moiquanhe) {
		this.moiquanhe = moiquanhe;
	}
	public String getThoigiangap() {
		return thoigiangap;
	}
	public void setThoigiangap(String thoigiangap) {
		this.thoigiangap = thoigiangap;
	}
	public String getMakhoSAP() {
		return makhoSAP;
	}
	public void setMakhoSAP(String makhoSAP) {
		this.makhoSAP = makhoSAP;
	}
	public ResultSet getKhoSAP() {
		return khoSAP;
	}
	public void setKhoSAP(ResultSet khoSAP) {
		this.khoSAP = khoSAP;
	}
	public ResultSet getKhuvucRs() {
		return khuvucRs;
	}
	public void setKhuvucRs(ResultSet khuvucRs) {
		this.khuvucRs = khuvucRs;
	}
	public String getKhuvucId() {
		return khuvucId;
	}
	public void setKhuvucId(String khuvucId) {
		this.khuvucId = khuvucId;
	}
	public String getMahd() {
		return mahd;
	}
	public void setMahd(String mahd) {
		this.mahd = mahd;
	}
	public String getVitri() {
		return vitri;
	}
	public void setVitri(String vitri) {
		this.vitri = vitri;
	}
	public String getTencuahieu() {
		return tencuahieu;
	}
	public void setTencuahieu(String tencuahieu) {
		this.tencuahieu = tencuahieu;
	}
	public String getGhichu() {
		return ghichu;
	}
	public void setGhichu(String ghichu) {
		this.ghichu = ghichu;
	}
	public String getTenduong() {
		return tenduong;
	}
	public void setTenduong(String tenduong) {
		this.tenduong = tenduong;
	}
	public String getSonha() {
		return sonha;
	}
	public void setSonha(String sonha) {
		this.sonha = sonha;
	}
	public String getTaitro() {
		return taitro;
	}
	public void setTaitro(String taitro) {
		this.taitro = taitro;
	}
	public String getNgaytaitro() {
		return ngaytaitro;
	}
	public void setNgaytaitro(String ngaytaitro) {
		this.ngaytaitro = ngaytaitro;
	}
	public String getToado_lat() {
		return toado_lat;
	}
	public void setToado_lat(String toado_lat) {
		this.toado_lat = toado_lat;
	}
	public String getToado_long() {
		return toado_long;
	}
	public void setToado_long(String toado_long) {
		this.toado_long = toado_long;
	}
	public String getNgsinh_con2() {
		return ngsinh_con2;
	}
	public void setNgsinh_con2(String ngsinh_con2) {
		this.ngsinh_con2 = ngsinh_con2;
	}
	public String getNgsinh_con1() {
		return ngsinh_con1;
	}
	public void setNgsinh_con1(String ngsinh_con1) {
		this.ngsinh_con1 = ngsinh_con1;
	}
	public String getCon2() {
		return con2;
	}
	public void setCon2(String con2) {
		this.con2 = con2;
	}
	public String getCon1() {
		return con1;
	}
	public void setCon1(String con1) {
		this.con1 = con1;
	}
	public String getNgsinh_vochong() {
		return ngsinh_vochong;
	}
	public void setNgsinh_vochong(String ngsinh_vochong) {
		this.ngsinh_vochong = ngsinh_vochong;
	}
	public String getVochong() {
		return vochong;
	}
	public void setVochong(String vochong) {
		this.vochong = vochong;
	}
	public String getApto() {
		return apto;
	}
	public void setApto(String apto) {
		this.apto = apto;
	}
	public String getNguoimuahang() {
		return nguoimuahang;
	}
	public void setNguoimuahang(String nguoimuahang) {
		this.nguoimuahang = nguoimuahang;
	}
	
	public String getMacu() {
		return macu;
	}
	public void setMacu(String macu) {
		this.macu = macu;
	}
	public void setDiachigiaohang(String diachigiaohang) {
		this.diachigiaohang = diachigiaohang;
	}

	public String getDiachigiaohang() {
		return diachigiaohang;
	}

	public String getCmnd() {
		return cmnd;
	}

	public void setCmnd(String cmnd) {
		this.cmnd = cmnd;
	}


	ResultSet khoRs;

	public Khachhang()
	{
		this.id = "";
		this.ten = "";
		this.diachi = "";
		this.tpId = "";
		this.qhId = "";
		this.type="0";

		this.sodienthoai = "";
		this.masothue = "";
		this.trangthai = "1";
		this.ngaytao = "";
		this.nguoitao = "";
		this.ngaysua = "";
		this.nguoisua = "";

		this.hch = "";
		this.kbh = "";
		this.bgst = "";
		this.vtch = "";
		this.lch = "";
		this.nch = "";
		this.mck = "";
		this.ghcn = "";

		this.hchId = "";
		this.kbhId = "";

		this.vtchId = "";
		this.lchId = "";
		this.nchId = "";
		this.mckId = "";
		this.ghcnId = "";
		this.nvgnId = "";
		this.thanhtoan = "";
		this.thanhtoanQUY = "0";

		this.msg = "";
		this.diadiemId="";
		this.xuatkhau="0";
		this.maFAST = "";
		this.kokyhd = "0";
		this.chucuahieu = "";
		this.ddkdId="";
		this.tbhId="";
		this.hopdong="";
		this.pt_chietkhau = "";
		this.mauhd="";
		this.khoId="";
		this.dtId="";
		this.tenkyhd="";
		this.ngaysinh="";
		this.mst="";
		this.cmnd="";

		this.phuongxaId = "null";
		this.phuongxa = "";
		this.didong = "";
		this.thanhthinongthonId = "null";
		this.songayno  = 0;
		this.sotienno = 0;
		this.maFAST = "";

		this.khachhangId = "";

		this.nppBanChungId = "";
		this.hinhthucTT = "";
		this.db = new dbutils();
		this.utilCenter = new geso.dms.center.util.Utility();
		this.shopkey = "0"; 
		this.matkhau = "";
		//Thêm trường mới vào Constructor
		this.nguoimuahang = "";
		this.apto = "";
		this.vochong = "";
		this.ngsinh_vochong = "";
		this.con1 = "";
		this.ngsinh_con1 = "";
		this.con2 = "";
		this.ngsinh_con2 = "";
		this.toado_lat = "";
		this.toado_long = "";
		this.sonha = "";
		this.ghichu = "";
		this.tenduong = "";
		this.taitro = "";
		this.ngaytaitro = "";
		this.tencuahieu = "";
		this.mahd = "";
		this.vitri = "";
	}

	public Khachhang(String id)
	{
		this.id = id;
		this.shopkey = "";
		this.matkhau = "";
		this.ten = "";
		this.diachi = "";
		this.tpId = "";
		this.qhId = "";
		this.type="0";
		this.sodienthoai = "";
		this.masothue = "";
		this.trangthai = "1";
		this.ngaytao = "";
		this.nguoitao = "";
		this.ngaysua = "";
		this.nguoisua = "";

		this.hch = "";
		this.kbh = "";
		this.bgst = "";
		this.vtch = "";
		this.lch = "";
		this.nch = "";
		this.mck = "";
		this.ghcn = "";
		this.hinhthucTT = "";
		this.hchId = "";
		this.kbhId = "100025";

		this.vtchId = "";
		this.lchId = "";
		this.nchId = "";
		this.mckId = "";
		this.ghcnId = "";
		this.nvgnId = "";
		this.thanhtoan = "";
		this.thanhtoanQUY = "0";

		this.msg = "";
		this.diadiemId="";
		this.xuatkhau="0";
		this.maFAST = "";
		this.kokyhd = "0";
		this.chucuahieu = "";

		this.ddkdId="";
		this.tbhId="";
		this.hopdong="";
		this.pt_chietkhau = "";
		this.mauhd="";
		this.khoId="";
		this.dtId="";
		this.tenkyhd="";
		this.ngaysinh="";
		this.mst="";
		this.cmnd="";

		this.phuongxaId = "null";
		this.phuongxa = "";
		this.didong = "";
		this.thanhthinongthonId = "null";

		this.songayno  = 0;
		this.sotienno = 0;
		this.maFAST = "";

		this.khachhangId = "";

		this.nppBanChungId = "";
		this.db = new dbutils();
		this.utilCenter = new geso.dms.center.util.Utility();
		
		//Thêm trường mới vào Constructor
		this.nguoimuahang = "";
		this.apto = "";
		this.vochong = "";
		this.ngsinh_vochong = "";
		this.con1 = "";
		this.ngsinh_con1 = "";
		this.con2 = "";
		this.ngsinh_con2 = "";
		this.toado_lat = "";
		this.toado_long = "";
		this.sonha = "";
		this.ghichu = "";
		this.tenduong = "";
		this.taitro = "";
		this.ngaytaitro = "";
		this.tencuahieu = "";
		this.mahd = "";
		this.vitri = "";
	}


	public String getLoaiNPP() {
		return loaiNPP;
	}

	public void setLoaiNPP(String loaiNPP) {
		this.loaiNPP = loaiNPP;
	}

	public String getThanhtoan() {
		return thanhtoan;
	}

	public void setThanhtoan(String thanhtoan) {
		this.thanhtoan = thanhtoan;
	}

	public String getUserId()
	{
		return this.userId;
	}

	public void setUserId(String userId)
	{
		this.userId = userId;
	}

	public String getId() 
	{
		return this.id;
	}

	public void setId(String id) 
	{
		this.id = id;
	}

	public String getTen() 
	{
		return this.ten;
	}

	public void setTen(String ten) 
	{
		this.ten = ten;
	}

	public String getDiachi() 
	{
		return this.diachi;
	}

	public void setDiachi(String diachi) 
	{
		this.diachi = diachi;
	}

	public String getTpId() 
	{
		return this.tpId;
	}

	public void setTpId(String tpId) 
	{
		this.tpId = tpId;
	}

	public String getQhId() 
	{
		return this.qhId;
	}

	public void setQhId(String qhId) 
	{
		this.qhId = qhId;
	}

	public String getSodienthoai() 
	{
		return this.sodienthoai;
	}

	public void setSodienthoai(String sodienthoai) 
	{
		this.sodienthoai = sodienthoai;
	}

	public String getTrangthai() 
	{
		return this.trangthai;
	}
	
	public String getShopkey() {
		return shopkey;
	}
	
	public void setShopkey(String shopkey) {
		this.shopkey = shopkey;
	}

	public void setTrangthai(String trangthai) 
	{
		this.trangthai = trangthai;
	}

	public String getNgaytao()
	{
		return this.ngaytao;	
	}

	public void setNgaytao(String ngaytao)
	{
		this.ngaytao = ngaytao;
	}

	public String getNgaysua()
	{
		return this.ngaysua;	
	}

	public void setNgaysua(String ngaysua)
	{
		this.ngaysua = ngaysua;
	}

	public String getNguoitao()
	{
		return this.nguoitao;
	}

	public void setNguoitao(String nguoitao)
	{
		this.nguoitao = nguoitao;
	}

	public String getNguoisua()
	{
		return this.nguoisua;
	}

	public void setNguoisua(String nguoisua)
	{
		this.nguoisua = nguoisua;
	}
	
	
	String[] arrAnhupload=new String [5];
	
	public void setAnhuploai(String[] arranhupload)
	{
		this.arrAnhupload=arranhupload;
	}
	public String[] getAnhuploai()
	{
		return arrAnhupload;
	}
	

	public String getMessage() 
	{
		return this.msg;
	}

	public void setMessage(String msg) 
	{
		this.msg = msg;
	}

	public String getNppId() 
	{
		return this.nppId;
	}

	public void setNppId(String nppId) 
	{
		this.nppId = nppId;
	}

	public String getNppTen() 
	{
		return this.nppTen;
	}

	public void setNppTen(String nppTen) 
	{
		this.nppTen = nppTen;
	}

	public String getSitecode() 
	{
		return this.sitecode;
	}

	public void setSitecode(String sitecode) 
	{
		this.sitecode = sitecode;
	}

	public String getHch() 
	{
		return this.hch;
	}

	public void setHch(String hch)
	{
		this.hch = hch;		
	}

	public String getKbh() 
	{
		return this.kbh;
	}

	public void setKbh(String kbh) 
	{
		this.kbh = kbh;
	}

	public String getBgst() 
	{	
		return this.bgst;
	}

	public void setBgst(String bgst) 
	{
		this.bgst = bgst;
	}

	public String getVtch() 
	{
		return this.vtch;
	}

	public void setVtch(String vtch) 
	{
		this.vtch = vtch;	
	}

	public String getLch() 
	{	
		return this.lch;
	}

	public void setLch(String lch) 
	{
		this.lch = lch;	
	}

	public String getNch() 
	{	
		return this.nch;
	}

	public void setNch(String nch) 
	{		
		this.nch = nch;
	}

	public String getMck() 
	{	
		return this.mck;
	}

	public void setMck(String mck) 
	{
		this.mck = mck;	
	}

	public String getGhcn() 
	{
		return this.ghcn;
	}

	public void setGhcn(String ghcn) 
	{
		this.ghcn = ghcn;
	}

	public ResultSet getTp() 
	{
		return this.tp;
	}

	public void setTp(ResultSet tp) 
	{
		this.tp = tp;
	}

	public ResultSet getQh() 
	{
		return this.qh;
	}

	public void setQh(ResultSet qh) 
	{
		this.qh = qh;
	}

	public ResultSet getHangcuahang() 
	{
		return this.hangcuahang;
	}

	public void setHangcuahang(ResultSet hangcuahang) 
	{
		this.hangcuahang = hangcuahang;		
	}

	public ResultSet getKenhbanhang() 
	{
		return this.kenhbanhang;
	}

	public void setKenhbanhang(ResultSet kenhbanhang) 
	{
		this.kenhbanhang = kenhbanhang;	
	}


	public ResultSet getVtcuahang() 
	{
		return this.vtcuahang;
	}

	public void setVtcuahang(ResultSet vtcuahang) 
	{
		this.vtcuahang = vtcuahang;
	}

	public ResultSet getLoaicuahang() 
	{
		return this.loaicuahang;
	}

	public void setLoaicuahang(ResultSet loaicuahang) 
	{
		this.loaicuahang = loaicuahang;
	}

	public ResultSet getNhomcuahang() 
	{
		return this.nhomcuahang;
	}

	public void setNhomcuahang(ResultSet nhomcuahang) 
	{
		this.nhomcuahang = nhomcuahang;
	}

	public ResultSet getMucchietkhau() 
	{	
		return this.mucchietkhau;
	}

	public void setMucchietkhau(ResultSet mucchietkhau)
	{
		this.mucchietkhau = mucchietkhau;	
	}

	public ResultSet getGhcongno() 
	{
		return this.ghcongno;
	}

	public void setGhcongno(ResultSet ghcongno) 
	{
		this.ghcongno = ghcongno;	
	}

	public String getHchId() 
	{
		return this.hchId;
	}

	public void setHchId(String hchId) 
	{
		this.hchId = hchId;
	}

	public String getKbhId() 
	{
		return this.kbhId;
	}

	public void setKbhId(String kbhId) 
	{
		this.kbhId = kbhId;
	}



	public String getVtchId() 
	{
		return this.vtchId;
	}

	public void setVtId(String vtchId) 
	{
		this.vtchId = vtchId;
	}

	public String getLchId() 
	{	
		return this.lchId;
	}

	public void setLchId(String lchId) 
	{	
		this.lchId = lchId;
	}

	public String getNchId() 
	{	
		return this.nchId;
	}

	public void setNchId(String nchId) 
	{
		this.nchId = nchId;	
	}

	public String getMckId() 
	{	
		return this.mckId;
	}

	public void setMckId(String mckId_) 
	{
		this.mckId = mckId_;	
	}

	public String getGhcnId() 
	{	
		return this.ghcnId;
	}

	public void setGhcnId(String ghcnId)
	{
		this.ghcnId = ghcnId;	
	}

	public ResultSet getNkh_khList() 
	{
		return this.nkh_khList;
	}

	public void setNkh_khList(ResultSet nkh_khlist) 
	{
		this.nkh_khList = nkh_khlist;		
	}

	public ResultSet getNkh_KhSelected() 
	{
		return this.nkh_khSelected;
	}

	public void setNkh_KhSelected(ResultSet nkh_khselected) 
	{
		this.nkh_khSelected = nkh_khselected;		
	}

	public Hashtable<Integer, String> getNkh_KhIds() 
	{
		Hashtable<Integer, String> selected = new Hashtable<Integer, String>();
		if (this.nkh_khIds != null){
			int size = (this.nkh_khIds).length;
			int m = 0;
			while (m < size){
				selected.put(new Integer(m), this.nkh_khIds[m]) ;
				m++;
			}
		}else{
			selected.put(new Integer(0), "null");
		}
		return selected;
	}

	
	
	public void setNkh_KhIds(String[] nkh_khIds) 
	{		
		this.nkh_khIds = nkh_khIds;
	}
	
	public void createkhoSAP()
	{  	
		this.khoSAP = this.db.get("select distinct makhosap from khachhang where makhosap is not null ");
	}

	public void createTpRS()
	{  	
		this.tp = this.db.get("select ten as tpTen, pk_seq as tpId from tinhthanh order by ten");
	}

	public void createQhRS()
	{  	

		if (this.tpId != null && this.tpId.length() > 0){

			this.qh = this.db.get("select ten as qhTen, pk_seq as qhId from quanhuyen where tinhthanh_fk='"+ this.tpId +"' order by ten");
		}
		else
			this.qh = this.db.get("select ten as qhTen, pk_seq as qhId from quanhuyen order by ten");
	}

	public void createHchRS()
	{
		this.hangcuahang =  this.db.get("select hang as hchTen, pk_seq as hchId from hangcuahang where trangthai='1' order by hang");

	}

	public void createKbhRS()
	{
		this.kenhbanhang =  this.db.get("select diengiai as kbhTen, pk_seq as kbhId from kenhbanhang where trangthai='1' order by diengiai");
		this.nvgnRs = this.db.get("select pk_seq as nvgnId, ten as nvgnTen from nhanviengiaonhan where npp_fk = '" + this.nppId + "' and trangthai = '1'");
	}



	public void createVtchRS()
	{
		this.vtcuahang =  this.db.get("select vitri as vtchTen, pk_seq as vtchId from vitricuahang where trangthai='1' order by vitri");
	}

	public void createLchRS()
	{
		this.loaicuahang =  this.db.get("select loai + ' - ' + diengiai as lchTen, pk_seq as lchId from loaicuahang where trangthai='1' order by loai");
	}

	public void createNchRS()
	{
		this.nhomcuahang =  this.db.get("select diengiai as nchTen, pk_seq as nchId from nhomkhachhang where trangthai='1' order by diengiai");
	}

	public void createMckRS()
	{
		this.mucchietkhau =  this.db.get("select chietkhau as mckTen, pk_seq as mckId from mucchietkhau where npp_fk='" + this.nppId + "' order by chietkhau");
	}

	public void createGhcnRS()
	{
		this.ghcongno =  this.db.get("select diengiai as ghcnTen, pk_seq as ghcnId from gioihancongno where npp_fk='" + this.nppId + "' order by sotienno");
	}

	public void createKhoRS()
	{
		this.khoRs =  this.db.get("select PK_SEQ, ten from kho where TRANGTHAI=1 and pk_seq in "+utilCenter.quyen_kho(this.userId));
	}

	public void createNkh_KhList()
	{  
		/*if (this.id.length()>0)
		{
			String query="select b.diengiai as nkhTen, b.pk_seq as nkhId";
			query = query + " from khachhang_nkhachhang a inner join nhomkhachhang b on a.nkh_fk = b.pk_seq where a.khachhang_fk='" + this.id + "'";
			this.nkh_khSelected = db.get(query);

			String query2 = "select diengiai as nkhTen, pk_seq as nkhId from nhomkhachhang where pk_seq not in (select b.pk_seq as nkhId ";
			query2 = query2 + "from khachhang_nkhachhang a inner join nhomkhachhang b on a.nkh_fk = b.pk_seq where a.khachhang_fk='" + this.id + "')";
			this.nkh_khList = db.get(query2);
		}
		else
		{
			String query="select diengiai as nkhTen, pk_seq as nkhId from nhomkhachhang";
			//query = query + " from khachhang_nkhachhang a inner join nhomkhachhang b on a.nkh_fk = b.pk_seq ";
			this.nkh_khSelected = db.get(query);

			String query2 = "select diengiai as nkhTen, pk_seq as nkhId from nhomkhachhang ";
			this.nkh_khList = db.get(query2);
		}*/

		if (this.id.length() == 0)
			this.nkh_khList = db.get("select a.pk_seq as nkhId, a.ten as nkhTen , 0 as CHON from NHOMKHACHHANG a where trangthai = 1 ");
		else
			this.nkh_khList = db.get("select  a.pk_seq as nkhId, a.ten as nkhTen, case when pk_seq in (select nkh_fk from NHOMKHACHHANG_KHACHHANG where kh_fk = "+this.id+") then 1 else 0 end as CHON from NHOMKHACHHANG a where trangthai = 1");


	}


	geso.dms.distributor.util.Utility util=new geso.dms.distributor.util.Utility();
	private void getNppInfo()
	{		
		if (this.view.equals("TT"))
		{
			String sql="select NPP_FK from KHACHHANG where pk_Seq = '"+this.id+"'";
			ResultSet rs = this.db.get(sql);		
			try {
				rs.next();
				this.nppId = rs.getString("NPP_FK");
				rs.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		else
		{
			try
			{
				this.nppId = util.getIdNhapp(this.userId);
				this.nppTen = util.getTenNhaPP();
				this.sitecode = util.getSitecode();
				this.loaiNPP = util.getLoaiNpp();
			}
			catch(Exception ex){}
		}		
	}

	private boolean CheckNPP() 
	{

		String sql = "select npp_fk from khachhang where pk_seq = '"+this.id+"'";
		//System.out.println("Check NPP_FK "+sql);
		ResultSet rs = db.get(sql);
		try {
			if (rs != null)
			{
				if (rs.next())
				{
					String npp_fk = rs.getString("NPP_FK");
					if (npp_fk != null)
						return true;
				}
			}
		} catch (SQLException e) 
		{

			e.printStackTrace();
			return false;
		}
		return false;
	}
	public void createRS()
	{
		this.createkhoSAP();
		this.getNppInfo();
		this.createTpRS();
		this.createQhRS();
		this.createHchRS();
		this.createKbhRS();
		this.createLchRS();
		this.createNchRS();
		this.createVtchRS();		
		this.createMckRS();
		this.createGhcnRS();
		this.createNkh_KhList();
		this.createKhoRS();

		if (this.id.length() > 0)
			if (CheckNPP() == true)
				kt = "1";
		//System.out.println("KT "+kt);
		String query="select * from DiaDiem ";
		this.diadiemRs = this.db.get(query);

		String condition="";
		if (this.dtId!=null && this.dtId.length()>0)
		{
			condition+="and c.pk_seq='"+this.dtId+"' ";
		}


		//		query = "	select distinct a.MANHANVIEN ,a.PK_SEQ as ddkdId,a.TEN as ddkdTen  "+
		//		"	from DAIDIENKINHDOANH a inner join DAIDIENKINHDOANH_NPP b on b.ddkd_fk=a.PK_SEQ inner join NHAPHANPHOI c on c.PK_SEQ=b.npp_fk  "+
		//		"	where  a.trangthai=1 and b.npp_fk='"+this.nppId+"' "+condition+" "+
		//		"	union    "+
		//		"	select distinct a.MANHANVIEN ,a.PK_SEQ as ddkdId,a.TEN as ddkdTen  "+
		//		"	from DAIDIENKINHDOANH a inner join DAIDIENKINHDOANH_NPP b on b.ddkd_fk=a.PK_SEQ inner join NHAPHANPHOI c on c.PK_SEQ=b.npp_fk  "+
		//		"	where  a.trangthai=1 and  c.TRUCTHUOC_FK='"+nppId+"' "+condition+"  ";
		//		//query += " order by c.TEN,a.ten ";
		if (this.nppId.length() > 0)
		{
			query = "	    "+
			"	select distinct a.MANHANVIEN ,a.PK_SEQ as ddkdId,a.TEN as ddkdTen  "+
			"	from DAIDIENKINHDOANH a "+
			"	where  a.trangthai = '1' and a.ispg = '0' and a.pk_seq in (select DDKD_FK from DaiDienKinhDOanh_NPP where npp_fk = "+this.nppId+" )      ";
			//System.out.println("--DAI DIEN KINH DOANH: " + query);
			this.ddkdRs = this.db.get(query);
		}
		

		if (this.ddkdId.length()>0)
		{
			query = "	select tbh.PK_SEQ as tbhId,'T'+cast(tbh.NGAYID as varchar(2))+'_'+ ddkd.TEN as tbhTen,   "+
			"		isnull((select max(sott) from KHACHHANG_TUYENBH where TBH_FK=tbh.PK_SEQ ),0)+1 as SOTT,''as TanSo,ddkd.TEN as tdvTen,tbh.ngayId  "+ 
			"	from TUYENBANHANG tbh inner join DAIDIENKINHDOANH ddkd on ddkd.PK_SEQ=tbh.DDKD_FK  "+
			"	where tbh.npp_Fk='"+this.nppId+"' " ; 

			if (this.ddkdId.length()>0)
			{
				query+=" and tbh.ddkd_Fk in ("+this.ddkdId+") ";
			}

			if (this.id.length()>0)
			{
				query+=
					" and  tbh.PK_SEQ not in   "+
					"	(  "+ 
					"		select TBH_FK from KHACHHANG_TUYENBH   "+
					"		where KHACHHANG_FK='"+this.id+"'   "+
					"	)   "+
					"union all	" +
					"select TBH_FK,'T'+cast(b.NGAYID as varchar(2))+'_'+ c.TEN as tbhTen,SOTT,TANSO ,c.TEN as tdvTen,b.ngayId   "+ 
					" from KHACHHANG_TUYENBH a inner join TUYENBANHANG b on b.PK_SEQ=a.TBH_FK  "+
					" inner join DAIDIENKINHDOANH c on c.PK_SEQ=b.DDKD_FK  "+
					"	where a.KHACHHANG_FK='"+this.id+"'";
				if (this.nppBanChungId.trim().length() > 0)
					query +=" and b.npp_fk not in ("+ this.nppBanChungId +")";

				if (this.ddkdId.length()>0)
				{
					query+=" and b.ddkd_Fk in ("+this.ddkdId+") ";
				}
			}		
			query+=" order by tdvTen, ngayId ";

			//System.out.println("[tbhRs] 1: "+query);
			this.tbhRs = this.db.get(query);
		}
		query="select pk_seq,ten,diachi,ma from nhaphanphoi where tructhuoc_fk='"+this.nppId+"' ";
		//System.out.println("Get NPP "+query);
		this.dtRs=this.db.get(query);
	
		if (this.qhId.length() > 0) {
			query = "\n select pk_Seq, ten from phuongxa ";
			query += "\n where quanhuyen_fk = '"+qhId+"'";
			this.phuongxaRs =this.db.getScrol(query);
			
		}
		////System.out.println(query);
		

		this.thanhthinongthonRs = this.db.get("select pk_seq, ten from ThanhThiNongThon where trangthai = 1");

		query = "\n select pk_seq, ten from NHAPHANPHOI where trangthai = '1'  " +
		"\n and pk_Seq in  ( select npp_fk from phamvihoatdong where nhanvien_fk='"+this.userId+"')" ;
		//System.out.println("GetNPP "+query);
		this.nppRs = this.db.get(query);

		if (this.ddkdId.length()>0)
		{	
			query = " \n select pk_seq, ten from NHAPHANPHOI where trangthai =1 and loainpp <> 0   	and pk_Seq in  ( select npp_fk from DaiDienKinhDoanh_NPP where ddkd_fk in ("+this.ddkdId+")) and pk_seq <> "+this.nppId;
			this.nppBanChungRs = db.get(query);
			//System.out.println("rs NPP khác:" +query );
		}
		String sql = "";
		
/*		sql = "select pk_seq,ten from diaban where trangthai = 1 ";
		this.diabanRs = db.get(sql);
		
		query = "select pk_seq, diengiai,ten from khuvuc where trangthai=1";
		this.khuvucRs = db.get(query);*/
		
		query =   " select pk_seq ,ma, ten from sanpham where trangthai = 1 ";
		this.sanphamRs = db.getScrol(query);
		
		query =   " select pk_seq,scheme,diengiai ten from ctchietkhau ";
		this.ctckRs = db.getScrol(query);
		
	}

	public boolean CreateKh(HttpServletRequest request) 
	{		
		try
		{
			this.db.getConnection().setAutoCommit(false);
			this.ngaytao = getDateTime();
			this.nguoisua = this.userId;

			if (!Utility.isValid(mckId))
				this.mckId = null;

			if (!Utility.isValid(ghcnId))
				this.ghcnId = null;

			if (!Utility.isValid(hopdong))
				this.kokyhd = "1";
			else 
				this.kokyhd = "0";

			if (!Utility.isValid(makhoSAP))
				makhoSAP = null;

			String _ck = "NULL";
			if (Utility.isValid(this.pt_chietkhau))
				_ck = this.pt_chietkhau.replaceAll(",", "");

			if (!Utility.isValid(this.khoId))
				this.khoId = null;

			if (!Utility.isValid(this.mauhd))
				this.mauhd = null;

			if (!Utility.isValid(this.dtId))
				this.dtId = null;;

			if (!Utility.isValid(this.diabanId))
				this.diabanId = null;

			if (!Utility.isValid(this.lchId))
				this.lchId = null;

			if (!Utility.isValid(toado_lat))
				toado_lat = null;

			if (!Utility.isValid(toado_long))
				toado_long = null;

			if (Utility.isValid(apto))
				apto = apto.trim() + ", ";
			else
				apto = "";

			if(phuongxa == null || phuongxa.trim().length()<=0 )
			{
				/*
				 * db.getConnection().rollback(); this.msg = "Vui lòng chọn phường/xã"; return
				 * false;
				 */
				
				phuongxa = "NULL";
			}
				
				
			// KH OTC: KH CỦA CN HÀ NỘI DÙNG MẪU HÓA ĐƠN 2(HO), CÒN LẠI : MẪU HÓA ĐƠN 1(CN) 
			// KH ETC: KH CỦA CN HÀ NỘI DÙNG MẪU HÓA ĐƠN 2(HO), CN HCM : TÙY CHỌN TRONG DLN , CÒN LẠI: DÙNG MẪU 1
			if (this.kbhId.equals("100025") && this.nppId.equals("100002")) this.mauhd = "2" ;
			if (this.kbhId.equals("100025") && !this.nppId.equals("100002")) this.mauhd = "1" ;
			if (this.kbhId.equals("100052") && this.nppId.equals("100002")) this.mauhd = "2" ;
			if (this.kbhId.equals("100052") && !this.nppId.equals("100002") && !this.nppId.equals("106210")) this.mauhd = "1" ;

			int stt = Khachhang.MaKhachHang(db, this.qhId, this.tpId);
			if (stt < 0)
			{
				db.getConnection().rollback();
				this.msg = "Không thể lấy mã KH theo tỉnh thành, quận huyện.";
				return false;	
			}

			//cho chucuahieu = nguoimuahang
			this.chucuahieu = this.nguoimuahang;
			
		
			String prefixMa = Khachhang.Prefix_MaKhachHang(db,this.qhId,this.tpId);
			if(prefixMa.trim().length() <=0)
			{
				db.getConnection().rollback();
				this.msg = "Không lấy được thông tin để tạo mã khách hàng (1)";
				return false;
			}
			stt = Khachhang.MaKhachHang(db,this.qhId,this.tpId);
			if(stt <=0 )
			{
				db.getConnection().rollback();
				this.msg = "Không lấy được thông tin để tạo  mã khách hàng (2)";
				return false;
			}
			String	query = "select dbo.PlusZero("+stt+",4) ma";
			ResultSet rs = db.get(query);
			rs.next();
			this.maFAST = prefixMa  + rs.getString("ma");
			String temp_maFast = this.maFAST;
			rs.close();
			
			
			 query = "\n insert into khachhang(shopkey, daduyet, moiquanhe, thoigiangap, DiaChi, diachigiaohang, MaCu, phuongxa, " +
			"\n lch_fk, smartId, TrucThuoc_fk, ten, maFAST, chucuahieu, KhongKyHopDong, trangthai, ngaytao, " +
			"\n ngaysua, nguoitao, nguoisua, chietkhau_fk, kbh_fk, hch_fk, npp_fk, ghcn_fk, dienthoai, sonha, " +
			"\n TINHTHANH_FK, QUANHUYEN_FK, masothue, XuatKhau, thanhtoan, THANHTOANQUY, MaHD, PT_CHIETKHAU, " +
			"\n MAUHOADON, KHO_FK, TenKyHd, NgaySinh, MST_CaNhan, cmnd, gioitinh, nguoimuahang, " +
			"\n apto, vochong, ngsinh_vochong, con1, ngsinh_con1, con2,ngsinh_con2, lat, long, tenduong, ghichu, " +
			"\n taitro, ngaytaitro, tencuahieu, vtch_fk, thanhthinongthon_fk, didong) " +
			"\n select '"+ this.shopkey +"', 0, N'"+moiquanhe+"', '"+thoigiangap+"', " +
			"\n N'"+this.sonha+"' + ', ' + N'"+this.tenduong+"' + ', ' + N'"+this.apto+"' + (select ten from phuongxa where pk_seq = "+this.phuongxa.replaceAll(",", "")+")+', ' + qh.ten+ ', ' + tt.ten, " +
			"\n	N'"+this.diachigiaohang+"', N'"+this.macu+"',"+phuongxa+", "+this.lchId+", " +
			"\n '" + temp_maFast + "', "+dtId+", N'" + this.ten + "', '" + temp_maFast + "', " +
			"\n N'" + this.chucuahieu + "',  '" + this.kokyhd + "', '" + this.trangthai + "', '" + this.ngaytao + "', '" + this.ngaytao + "', " +
			"\n '" + this.nguoisua + "', '" + this.nguoisua + "', " + this.mckId + ", '" + this.kbhId + "', " + (!Utility.isValid(hchId) ? "NULL" : this.hchId)+ ", " +
			"\n '" + this.nppId + "', " + this.ghcnId + ", '" + this.sodienthoai + "', N'" + this.sonha + "', '"+ this.tpId + "', '"+ this.qhId + "', N'" + this.masothue + "', " +
			"\n "+this.xuatkhau+  ", " +(!Utility.isValid(thanhtoan) ? "null" : this.thanhtoan)+", " +
			"\n " + (!Utility.isValid(thanhtoanQUY) ? "null" : this.thanhtoanQUY) + ", '"+this.mahd+"', " + _ck + ", " +
			"\n "+this.mauhd+", "+this.khoId+", N'"+this.tenkyhd +"', '"+this.ngaysinh+"', '"+this.mst+"', '"+this.cmnd+"', " +
			"\n '"+this.gioiTinh+"', N'"+this.nguoimuahang +"', RTRIM(N'"+this.apto.replace(",","")+"'), " +
			"\n N'"+this.vochong+"', N'"+this.ngsinh_vochong+"', N'"+this.con1+"', '"+this.ngsinh_con1+"', N'"+this.con2+"', " +
			"\n '"+this.ngsinh_con2+"', "+this.toado_lat+", "+this.toado_long+", N'"+this.tenduong+"', N'"+this.ghichu+"', " +
			"\n N'"+this.taitro+"', '"+this.ngaytaitro+"', N'"+this.tencuahieu+"', "+this.vtchId+", "+this.thanhthinongthonId+", '"+this.didong+"'" +
			"\n from tinhthanh tt " +
			"\n inner join quanhuyen qh on qh.tinhthanh_fk = tt.pk_seq" +
			"\n where tt.pk_seq = "+this.tpId+" and qh.pk_seq = "+this.qhId+"  ";
			//System.out.println("Query Insert: "+ query);

			if (db.updateReturnInt(query) <= 0){
				db.getConnection().rollback();
				db.getConnection().setAutoCommit(true);
				this.msg = "Không thể tạo mới KH 1.";
				return false;			
			}

			query = "select SCOPE_IDENTITY() as khId";
			rs = this.db.get(query);
			rs.next();
			this.id = rs.getString("khId");
			rs.close();
			
			if(this.matkhau.trim().length() > 0)
			{
				query = "UPDATE KHACHHANG SET MATKHAU = PWDENCRYPT('"+ this.matkhau.trim() +"') WHERE PK_SEQ = '"+ this.id +"'";
				if (db.updateReturnInt(query) <= 0){
					db.getConnection().rollback();
					db.getConnection().setAutoCommit(true);
					this.msg = "Không thể tạo mới KH 1.";
					return false;			
				}
			}

			/*query = "update khachhang set mafast = '"+this.id+"' where pk_seq = "+this.id;	
			//System.out.println("Update mafast: "+query);
			if (!db.update(query))
			{
				db.getConnection().rollback();
				db.getConnection().setAutoCommit(true);
				this.msg = "Không thể tạo mới KH 2.";
				return false;	
			}*/			
			
			String kq = Log_MaKhachHang(db, this.id, stt);
			if (kq.trim().length() > 0)
			{
				db.getConnection().rollback();
				this.msg = kq;
				return false;		
			}			

			if (Utility.isValid(nvgnId))
			{
				query = "insert nvgn_kh(nvgn_fk, khachhang_fk) select pk_Seq as nvgnId,'"+this.id+"' as khId from nhanviengiaonhan where pk_Seq in ("+this.nvgnId+") ";
				if (!db.update(query))
				{
					db.getConnection().rollback();
					db.getConnection().setAutoCommit(true);
					this.msg = "Không thể tạo mới KH 3.";
					return false;	
				}
			}
			
			boolean check = checkNPP_NVGN(nppId, db);
			if (!check)
			{
				db.getConnection().rollback();
				db.getConnection().setAutoCommit(true);
				return false;			
			}

			query = "\n insert nvgn_kh(NVGN_FK,Khachhang_FK) " +
			"\n select (select top 1 pk_Seq from nhanviengiaonhan where npp_fk = khachhang.npp_fk), pk_seq " +
			"\n from khachhang where pk_seq = "+this.id+" and not exists (select 1 from nvgn_kh where khachhang_fk = pk_Seq) ";	
			if (!this.db.update(query))
			{
				db.getConnection().rollback();
				db.getConnection().setAutoCommit(true);
				this.msg = "Không thể tạo mới KH 8.";
				return false;			
			}

			query = "\n insert nvgn_kh select top 1 nvgn_fk, '"+this.id+"' " +
			"\n from " +
			"\n (  "+
			"\n     select nvgn_fk from NVGN_tinhthanh a inner join khachhang b on a.TinhThanh_FK = b.TINHTHANH_FK where b.pk_seq = "+this.id+
			"\n     union "+
			"\n     select nvgn_fk from NVGN_quanhuyen a inner join khachhang b on a.quanhuyen_fk = b.quanhuyen_fk where b.pk_seq = "+this.id+
			"\n     union "+
			"\n     select nvgn_fk from NVGN_tuyenbanhang a inner join khachhang b on a.TBH_FK in (select tbh_fk from khachhang_tuyenbh where khachhang_fk = b.pk_seq) "+
			"\n     where b.pk_seq =  "+this.id+
			"\n     union "+
			"\n     select nvgn_fk from ddkd_nvgn a inner join khachhang b on a.ddkd_fk in  "+
			"\n     (select ddkd_fk from tuyenbanhang tbh inner join khachhang_tuyenbh khtbh on tbh.pk_seq = khtbh.TBH_FK  "+
			"\n     where khtbh.KHACHHANG_FK = b.pk_seq ) where b.pk_seq =  "+this.id+
			"\n )a where not exists (select 1 from nvgn_kh where khachhang_fk = "+this.id+") ";
			//System.out.println("Query Insert NVGN: "+query);
			db.update(query);

			// Insert data set into table "Khachhang_nhomkhachhang"
			if (this.nkh_khIds != null)
			{
				int size = this.nkh_khIds.length;
				int m = 0;

				while (m < size )
				{
					query = "insert into NHOMKHACHHANG_KHACHHANG(NKH_FK, KH_FK) VALUES ("+this.nkh_khIds[m]+","+this.id+")";
					//query = "insert into khachhang_nkhachhang values('" + this.nkh_khIds[m] + "','" + this.id + "')"; 	
					if (!db.update(query)){
						db.getConnection().rollback();
						db.getConnection().setAutoCommit(true);
						this.msg = "Không thể tạo mới KH 4.";
						return false;	
					}
					m++;	
				}
			}

			/*
			 * 0 :Chi Nhanh cap 1 
			 * 1 :Chi Nhanh cap 2
			 * 2 : Quầy bán buôn
			 * 3 : Quầy OPV
			 * 4 : Doi tac
			 * 5: Chi nhanh doi tac
			 */
			Utility util = new Utility();

			String[] tanso = request.getParameterValues("tanso");
			String[] sott = request.getParameterValues("sott");
			String[] tuyenbhId = request.getParameterValues("tbhId");
			String[] tbhIdTmp = request.getParameterValues("tbhIdTmp");
			if (tuyenbhId != null)
			{
				String ts = "";
				for (int i = 0; i < tuyenbhId.length; i++)
				{
					if (i < tanso.length)
						ts	 = tanso[i];
					else
						ts ="F4";
					for (int k = 0; k < tbhIdTmp.length ; k++)
					{
						if (tbhIdTmp[k].equals(tuyenbhId[i]))
						{
							ts = tanso[k];
						}
					}						

					//System.out.println( "Tan so kh =  "+ ts);

					if (tuyenbhId[i] != null)
					{
						query= "\n insert into KHACHHANG_TUYENBH(KHACHHANG_FK,SOTT,TANSO,TBH_FK) " +
						"\n select "+this.id+", '"+sott[i]+"', '"+ts+"', '"+tuyenbhId[i]+"' " +
						"\n where NOT EXISTS " +
						"\n ( " +
						"\n     select 1 from KHACHHANG_TUYENBH where KHACHHANG_FK='"+this.id+"' " +
						"\n     and TBH_FK = '"+tuyenbhId[i]+"' and TANSO = '"+ts+"' AND SOTT = '"+sott[i]+"' " +
						"\n ) ";
						if (!db.update(query))
						{
							this.msg = "Không thể tạo tuyến KH1!";
							db.getConnection().rollback();
							return false;
						}

						if (this.nppBanChungId.trim().length() >0 && this.ddkdId.trim().length() > 0)
						{
							query = "\n select pk_seq from tuyenbanhang " +
							"\n where npp_fk in ( "+this.nppBanChungId+") and ddkd_fk in ("+this.ddkdId+") and ngayId =(select ngayId from tuyenbanhang where pk_Seq ="+tuyenbhId[i]+" )    ";
							//System.out.println("nppBanChungId sq l = "+query );
							ResultSet rsTuyenKhac = db.get(query);
							while (rsTuyenKhac.next())
							{
								String tk = rsTuyenKhac.getString("pk_Seq");
								query = "\n insert into KHACHHANG_TUYENBH(KHACHHANG_FK,SOTT,TANSO,TBH_FK) " +
								"\n select "+this.id+", isnull((select MAX(sott) from KHACHHANG_TUYENBH where tbh_fk ="+tk+" ),0) + 1, '"+ts+"',"+tk+
								"\n where NOT EXISTS " +
								"\n ( " +
								"\n     select 1 from KHACHHANG_TUYENBH where KHACHHANG_FK='"+this.id+"' " +
								"\n     and TBH_FK='"+tk+"'   "+
								"\n ) ";
								if (!db.update(query))
								{
									this.msg = "Không thể tạo tuyến KH 2!";
									db.getConnection().rollback();
									return false;
								}
							}
						}
					}
				}
			}

			query = "\n select KHACHHANG_FK,a.tbh_fk, " +
			"\n ( " +
			"\n     select count (*) from KHACHHANG_TUYENBH inner join TUYENBANHANG on TUYENBANHANG.PK_SEQ = KHACHHANG_TUYENBH.TBH_FK " +
			"\n     where KHACHHANG_TUYENBH.KHACHHANG_FK=a.KHACHHANG_FK  AND len(ltrim(rtrim(KHACHHANG_TUYENBH.TANSO))) = 0 and TUYENBANHANG.DDKD_FK = b.DDKD_FK " +
			"\n     group by KHACHHANG_FK, TUYENBANHANG.DDKD_FK " + 
			"\n ) as SoDong, " +
			"\n b.DDKD_FK, a.TANSO as TanSoOld, sum(b.NgayId) OVER (PARTITION BY DDKD_FK,KHACHHANG_FK) as TongNgay, b.NGAYID " +
			"\n from KHACHHANG_TUYENBH a inner join TUYENBANHANG b on b.PK_SEQ=a.TBH_FK " +
			"\n where a.KHACHHANG_FK='"+this.id+"' and len(ltrim(rtrim(a.TANSO)))=0 ";
			rs = db.get(query);
			while (rs.next())
			{
				int SoDong = rs.getInt("SoDong");
				String TanSoNew = "";
				if (SoDong == 1)
				{
					TanSoNew = "F4";
				}
				else if (SoDong == 2)
				{
					TanSoNew = "F8";
				}
				if (SoDong == 3)
				{
					if ((rs.getInt("TongNgay")%2) == 0 && (rs.getInt("NGAYID")%2) == 0)
						TanSoNew = "F12-2";
					else if ((rs.getInt("TongNgay")%2) !=0 && (rs.getInt("NGAYID")%2) != 0)
					{
						TanSoNew = "F12-1";
					}
					else 
					{
						this.msg = "Vui lòng chọn ngày viếng thăm hợp lệ!";
						db.getConnection().rollback();
						return false;
					}
				}
				String TanSoOld = rs.getString("TanSoOld");
				String tbhId = rs.getString("tbh_fk");
				query = "update KHACHHANG_TUYENBH set tanso='"+TanSoNew+"' where khachhang_fk='"+this.id+"' and tanso='"+TanSoOld+"' and tbh_fk='"+tbhId+"' ";
				if (!db.update(query))
				{
					this.msg = "Lỗi tạo mới tuyến KH 3.";
					db.getConnection().rollback();
					return false;
				}
			}				

			query = "\n update a " +
			"\n set a.isUpdatedFromMCP = 0, a.chucuahieu = nguoimuahang, a.smartId = a.mafast, " +
			"\n a.diachi = case when len (isnull(ltrim(rtrim(sonha)),'') ) = 0 then '' else sonha+', ' end + " +
			"\n	case when len (isnull(ltrim(rtrim(tenduong)),'') ) = 0 then '' else tenduong+', ' end + " +
			"\n	case when len (isnull(ltrim(rtrim(phuongxa)),'') ) = 0 then '' else px.ten+', ' end + " +
			"\n	qh.ten+', '+tt.ten, " +
			"\n a.timkiem = dbo.ftBoDau(a.maFAST + '-' + isnull(a.ten, '') + '-' + " +
			"\n     case when len (isnull(ltrim(rtrim(sonha)),'') ) = 0 then '' else sonha+', ' end + " +
			"\n     case when len (isnull(ltrim(rtrim(tenduong)),'') ) = 0 then '' else tenduong+', ' end + " +
			"\n     case when len (isnull(ltrim(rtrim(phuongxa)),'') ) = 0 then '' else px.ten+', ' end + " +
			"\n     qh.ten+', '+tt.ten  ) " +
			"\n from khachhang a left join phuongxa px on px.pk_seq = a.phuongxa " +
			"\n inner join quanhuyen qh on qh.pk_seq = a.QUANHUYEN_FK " +
			"\n inner join tinhthanh tt on tt.pk_seq = a.TINHTHANH_FK " +
			"\n where a.pk_seq = " + this.id;
			if (!db.update(query))
			{
				db.getConnection().rollback();
				db.getConnection().setAutoCommit(true);
				this.msg = "Không thể tạo mới KH 5.";
				return false;			
			}		

			query = "Insert KHACHHANG_NPP( khachhang_fk, npp_fk ) values ( '" + this.id + "', '" + this.nppId + "' ) ";
			//System.out.println("------CHEN DATA: " + query );
			if (!this.db.update(query))
			{
				db.getConnection().rollback();
				db.getConnection().setAutoCommit(true);
				this.msg = "Không thể tạo mới KH 6.";
				return false;			
			}
			
			if (Utility.isValid(nppBanChungId))
			{
				query = "Insert KHACHHANG_NPP( khachhang_fk, npp_fk ) select '" + this.id + "', pk_seq from nhaphanphoi where pk_seq in( " + this.nppBanChungId + " ) ";
				//System.out.println("------CHEN DATA: " + query );
				if (!this.db.update(query))
				{
					db.getConnection().rollback();
					db.getConnection().setAutoCommit(true);
					this.msg = "Không thể tạo mới KH 7.";
					return false;			
				}
			}

			if (spIds != null && spIds.length > 0)
			{
				for (int i = 0 ; i < spIds.length; i ++)
				{
					double ck = geso.dms.center.util.Utility.parseDouble(this.chietkhauSp[i].replace(",",""));
					if (ck <= 0)
					{
						db.getConnection().rollback();
						this.msg = "Bạn chưa nhập chiết khấu ở dòng ("+(i+1)+").";
						return false;
					}
					
					if (this.ctckIds[i].trim().length() <= 0)
					{
						db.getConnection().rollback();
						this.msg = "Bạn chưa chọn Program ở dòng ("+(i+1)+").";
						return false;
					}
					
					query = "\n insert KHACHHANG_ChietKhau(KhachHang_FK,SanPham_FK,ChietKhau,ctck_fk,scheme)" +
					"\n select "+this.id+", "+this.spIds[i]+", round("+ck+",2), pk_seq, scheme" +
					"\n from ctchietkhau " +
					"\n where pk_seq = "+this.ctckIds[i]+" " +
					"\n and not exists (select 1 from KHACHHANG_ChietKhau where KhachHang_FK = "+this.id+" and Sanpham_fk = "+this.spIds[i]+") " ;
					if (db.updateReturnInt(query)<= 0 )
					{
						this.msg = "CK Sản phẩm dòng ("+(i+1)+") đã được thiết lập.";
						db.getConnection().rollback();
						return false;
					}	
				}
			}

			query = "\n insert KhachHang_ChietKhau_Log( KhachHang_FK,SanPham_FK,ChietKhau,ctck_fk,scheme,userId) " +
			"\n select KhachHang_FK,SanPham_FK,ChietKhau,ctck_fk,scheme,"+this.userId+" " +
			"\n from KHACHHANG_ChietKhau where  KhachHang_FK = " + this.id; 
			if (!db.update(query) )
			{
				db.getConnection().rollback();
				db.getConnection().setAutoCommit(true);
				this.msg = "Không thể tạo mới KH 9.";
				return false;			
			}	

			this.db.getConnection().commit();
			this.db.getConnection().setAutoCommit(true);
		}
		catch(Exception e)
		{
			db.update("rollback");
			this.msg = "Lỗi ngoại lệ tạo mới: "+e.getMessage();
			e.printStackTrace();
			return false;
		}

		return true;
		}

	private boolean KiemTraKhachHangTrunngMa_Fast() {
		try{
			// ---- KIỂM TRA MÃ FAST + NPP_FK LÀ DUY NHẤT ---- //
			String sql="select pk_seq from khachhang where 	 MaFast=N'"+this.maFAST.trim()+"' and  NPP_FK='"+this.nppId+"'";   

			if (this.id.length() > 0)
			{
				sql=sql+ " and pk_seq <> "+this.id;
			}
			//System.out.println("2. nhapp:  "+ this.nppId);
			//System.out.println(" Query kiem tra trung ma fast: "+ sql);
			ResultSet rs=db.get(sql);
			int bien=0;
			if (rs.next()){
				bien=1;
			}
			rs.close();
			if (bien>0){
				return true;
			}
		}catch(Exception er){

			er.printStackTrace();
			return true;
		}

		return false;
	}

	public boolean UpdateKh(HttpServletRequest request) 
	{	
		this.ngaysua = getDateTime();
		this.nguoisua = this.userId;		
		
		if (!Utility.isValid(mckId))
			this.mckId = null;

		if (!Utility.isValid(ghcnId))
			this.ghcnId = null;

		if (!Utility.isValid(hopdong))
			this.kokyhd = "1";
		else 
			this.kokyhd = "0";

		if (!Utility.isValid(makhoSAP))
			makhoSAP = null;

		String _ck = "NULL";
		if (Utility.isValid(this.pt_chietkhau))
			_ck = this.pt_chietkhau.replaceAll(",", "");

		if (!Utility.isValid(this.khoId))
			this.khoId = null;

		if (!Utility.isValid(this.mauhd))
			this.mauhd = null;

		if (!Utility.isValid(this.dtId))
			this.dtId = null;;

		if (!Utility.isValid(this.diabanId))
			this.diabanId = null;

		if (!Utility.isValid(this.lchId))
			this.lchId = null;

		if (!Utility.isValid(toado_lat))
			toado_lat = null;

		if (!Utility.isValid(toado_long))
			toado_long = null;

		if (Utility.isValid(apto))
			apto = apto.trim() + ", ";
		
		if (!Utility.isValid(sodienthoai)) {
			sodienthoai = null;
		}
		if(!Utility.isValid(ngsinh_vochong)) {
			ngsinh_vochong = null;
		}
		else
			apto = "";
			
		String tpIdn;
		if (Utility.isValid(tpId))
			tpIdn = tpId.trim() + ", ";
		else 
			tpIdn = "";
		
		String qhIdn;
		if (Utility.isValid(qhId))
			qhIdn = qhId.trim() + ", ";
		else 
			qhIdn = "";
		
		
		try
		{
			ResultSet rs;
			this.db.getConnection().setAutoCommit(false);

			
			if(phuongxa == null || phuongxa.trim().length()<=2 )
			{
				/*
				 * db.getConnection().rollback(); this.msg = "Vui lòng chọn phường/xã"; return
				 * false;
				 */
				phuongxa = "NULL";
			}

			
			// KH OTC: KH CỦA CN HÀ NỘI DÙNG MẪU HÓA ĐƠN 2(HO), CÒN LẠI : MẪU HÓA ĐƠN 1(CN) 
			// KH ETC: KH CỦA CN HÀ NỘI DÙNG MẪU HÓA ĐƠN 2(HO), CN HCM : TÙY CHỌN TRONG DLN , CÒN LẠI: DÙNG MẪU 1
			if ( this.kbhId.equals("100025") && this.nppId.equals("100002")) this.mauhd = "2" ;
			if ( this.kbhId.equals("100025") && !this.nppId.equals("100002")) this.mauhd = "1" ;
			if ( this.kbhId.equals("100052") && this.nppId.equals("100002")) this.mauhd = "2" ;
			if ( this.kbhId.equals("100052") && !this.nppId.equals("100002") && !this.nppId.equals("106210")) this.mauhd = "1" ;

			//cho chucuahieu = nguoimuahang
			this.chucuahieu = this.nguoimuahang;
			
			Object[] data = null;
			data = dbutils.setObject(this.shopkey,moiquanhe,thoigiangap,this.sonha,this.tenduong,this.apto,this.phuongxa.replaceAll(",", ""),this.macu
					,this.diachigiaohang,this.sotienno,this.songayno,this.thanhthinongthonId,this.phuongxa,this.lchId
					,this.ngaysinh,this.vtchId,this.mst,this.dtId,this.mahd,this.chucuahieu,makhoSAP,this.tencuahieu
					,this.kokyhd,(this.diadiemId.trim().length() <= 0 ? "NULL" : this.diadiemId),this.xuatkhau
					,this.ten,this.sonha,this.tenduong,this.ghcnId,this.mckId,this.sodienthoai,this.didong
					,this.kbhId,(this.hchId.trim().length() <= 0 ? "NULL" : this.hchId),this.ngaysua,this.trangthai
					,this.nguoisua,this.masothue,(this.thanhtoan.trim().length()<=0?"NULL":this.thanhtoan)
					,(this.thanhtoan.trim().length() <= 0 ? "NULL" : this.thanhtoan),_ck,this.mauhd
					,this.khoId,this.tenkyhd,this.cmnd,this.gioiTinh,this.nguoimuahang,this.apto.replace(",",""),this.vochong
					,ngsinh_vochong,this.con1,this.ngsinh_con1,this.con2,this.ngsinh_con2,this.ghichu,this.taitro,this.ngaytaitro
					,this.tpId,this.qhId,this.id);
			
			String query = 
					"\n update khachhang set shopkey = ?, "
					+ "daduyet = 0, moiquanhe = ?, thoigiangap=?, " +
			"\n diachi = ? + ', ' + ? + ', ' + ? +(select ten from phuongxa where pk_Seq = ? ), "+
			"\n macu = ?, DiaChiGiaoHang = ?, sotienno=?, " +
			"\n songayno = ?, thanhthinongthon_fk = ?, "+
			"\n phuongxa =?, lch_fk = ?, NgaySinh = ?, " +
			"\n vtch_fk = ?, MST_CaNhan = ?, TrucThuoc_fk = ?, MaHD=?, " +
			"\n chucuahieu = ?, makhosap = ?, tencuahieu = ? ,KhongKyHopDong = ?, " +
			"\n DiaDiem_FK = ?, " +
			"\n XuatKhau = ?,ten = ?, sonha = ?, tenduong = ?, " +
			"\n ghcn_fk = ?, chietkhau_fk = ?, dienthoai = ?, " +" DiDong = ?, " +
			"\n kbh_fk = ?, hch_fk= ?, " +
			"\n ngaysua = ?, trangthai = ?, nguoisua=?, " +
			"\n masothue = ?, thanhtoan = ?, " +
			"\n THANHTOANQUY = ?, " +
			"\n PT_CHIETKHAU = ?, MAUHOADON =?, KHO_FK =?, TenKyHd =?, " +
			"\n cmnd = ?,gioitinh = ?, nguoimuahang = ?, " +
			"\n apto = RTRIM(?), vochong = ? ,Ngsinh_VoChong = ?, con1 = ?, " +
			"\n ngsinh_con1 = ?, con2 = ?, ngsinh_con2 = ?, " +
			"\n ghichu = ?, taitro = ?, ngaytaitro = ?," +
			"\n TINHTHANH_FK=?,QUANHUYEN_FK=?"+
			"\n from khachhang kh	" +
			//"\n inner join tinhthanh tt on tt.pk_seq = "+this.tpId+"  " +
			//"\n inner join quanhuyen qh on qh.tinhthanh_fk = tt.pk_seq and qh.pk_seq = "+this.qhId+" " +
			"\n where kh.pk_seq = ? ";			
			//System.out.println("update KH: "+ query);
			db.viewQuery(query, data);
			if (this.db.update_v2(query,data)<=0 )
			{
				this.db.getConnection().rollback();
				this.msg = "Lỗi cập nhật KH 1. ";
				return false; 
			}
			
			
			///
			String path=request.getServletContext().getInitParameter("pathhinh")+"AnhDaiDien\\";
			if(arrAnhupload[1]!=null && arrAnhupload[1].length()>0)
			{
				String fileName1="upload_bonanh1_"+this.id+"_"+this.getFulDateTime()+".jpg";
				String ms=this.saveImage(path, arrAnhupload[1], fileName1);
				if(ms.length()<=0)
				{
					query="update khachhang set img_create1='"+fileName1+"' where pk_seq="+this.id;
					if (this.db.updateReturnInt(query)<=0)
					{
						this.db.getConnection().rollback();
						this.msg = "Lỗi cập nhật ảnh upload KH 1.";
						return false; 
					}
				}
				else
				{
					this.db.getConnection().rollback();
					this.msg = "Lỗi upload ảnh 1."+ms;
					return false; 
				}
			}
			if(arrAnhupload[2]!=null && arrAnhupload[2].length()>0)
			{
				String fileName2="upload_bonanh2_"+this.id+"_"+this.getFulDateTime()+".jpg";
				String ms=this.saveImage(path, arrAnhupload[2], fileName2);
				if(ms.length()<=0)
				{
					query="update khachhang set img_create2='"+fileName2+"' where pk_seq="+this.id;
					if (this.db.updateReturnInt(query)<=0)
					{
						this.db.getConnection().rollback();
						this.msg = "Lỗi cập nhật ảnh upload KH 2.";
						return false; 
					}
				}
				else
				{
					this.db.getConnection().rollback();//
					this.msg = "Lỗi upload ảnh 2."+ms;
					return false; 
				}
			}
			if(arrAnhupload[3]!=null && arrAnhupload[3].length()>0)
			{
				String fileName3="upload_bonanh3_"+this.id+"_"+this.getFulDateTime()+".jpg";
				String ms=this.saveImage(path, arrAnhupload[3], fileName3);
				if(ms.length()<=0)
				{
					query="update khachhang set img_create3='"+fileName3+"' where pk_seq="+this.id;
					if (this.db.updateReturnInt(query)<=0)
					{
						this.db.getConnection().rollback();
						this.msg = "Lỗi cập nhật ảnh upload KH 3.";
						return false; 
					}
				}
				else
				{
					this.db.getConnection().rollback();
					this.msg = "Lỗi upload ảnh 3."+ms;
					return false; 
				}
			}
			if(arrAnhupload[4]!=null && arrAnhupload[4].length()>0)
			{
				String fileName4="upload_bonanh4_"+this.id+"_"+this.getFulDateTime()+".jpg";
				String ms=this.saveImage(path, arrAnhupload[4], fileName4);
				if(ms.length()<=0)
				{
					query="update khachhang set img_create4='"+fileName4+"' where pk_seq="+this.id;
					if (this.db.updateReturnInt(query)<=0)
					{
						this.db.getConnection().rollback();
						this.msg = "Lỗi cập nhật ảnh upload KH 4.";
						return false; 
					}
				}
				else
				{
					this.db.getConnection().rollback();
					this.msg = "Lỗi upload ảnh 4."+ms;
					return false; 
				}
			}
			
			
			
			
			
			
			if(this.matkhau.trim().length() > 0)
			{
				data = null;
				data = dbutils.setObject(this.matkhau.trim(),this.id);
				query = "UPDATE KHACHHANG SET MATKHAU = PWDENCRYPT(?) WHERE PK_SEQ = ?";
				//System.out.println("query mk : "+ query);
				if (db.update_v2(query,data) <= 0){
					db.getConnection().rollback();
					db.getConnection().setAutoCommit(true);
					this.msg = "Không thể tạo mới KH 1.";
					return false;			
				}
			}

			
			data = null;
			data = dbutils.setObject(this.id);
			query = "delete bgst_khachhang where khachhang_fk=?";
			if (this.db.update_v2(query,data) < 0)
			{
				this.db.getConnection().rollback();
				this.msg = "Lỗi cập nhật KH 2.";
				return false; 
			}

			query = "delete from NHOMKHACHHANG_KHACHHANG where KH_FK= '" + this.id + "'" ;
			if (!this.db.update(query))
			{
				this.db.getConnection().rollback();
				this.msg = "Lỗi cập nhật KH 3.";
				return false; 
			}

			if (this.nkh_khIds != null)
			{
				int size = this.nkh_khIds.length;
				int m = 0;
				while (m < size)
				{
					data = null;
					data = dbutils.setObject(this.nkh_khIds[m],this.id);
					query = "insert into NHOMKHACHHANG_KHACHHANG(NKH_FK, KH_FK) VALUES (?,?)";

					//System.out.println("NHOMKHACHHANG_KHACHHANG =   :" + query);
					if (this.db.update_v2(query,data) < 0)
					{
						this.db.getConnection().rollback();
						this.msg = "Lỗi cập nhật KH 4.";
						return false; 
					}

					m++;
				}
			}

			data = null;
			data = dbutils.setObject(this.id);
			query = "delete nvgn_kh where khachhang_fk = ?";
			if (this.db.update_v2(query,data) < 0)
			{
				this.db.getConnection().rollback();
				this.msg = "Lỗi cập nhật KH 5.";
				return false; 
			}
			
			if (Utility.isValid(this.nvgnId))
			{
				data = null;
				data = dbutils.setObject(this.id,this.nvgnId);
				query = "insert nvgn_kh(nvgn_fk, khachhang_fk) select pk_Seq as nvgnId, ? as khId from nhanviengiaonhan where pk_Seq in (?) ";
				if (db.update_v2(query,data) < 0)
				{
					this.db.getConnection().rollback();
					this.msg = "Lỗi cập nhật KH 6.";
					return false; 
				}
			}
			
			boolean check = checkNPP_NVGN(nppId, db);
			if (!check)
			{
				db.getConnection().rollback();
				db.getConnection().setAutoCommit(true);
				return false;			
			}

			data = null;
			data = dbutils.setObject(this.id);
			query = "\n insert nvgn_kh(NVGN_FK,Khachhang_FK) " +
			"\n select (select top 1 pk_Seq from nhanviengiaonhan where npp_fk = khachhang.npp_fk), pk_seq " +
			"\n from khachhang where pk_seq = ? and not exists (select 1 from nvgn_kh where khachhang_fk = pk_Seq) ";	
			if (db.update_v2(query,data) < 0)
			{
				db.getConnection().rollback();
				db.getConnection().setAutoCommit(true);
				this.msg = "Không thể tạo mới KH 5.1.";
				return false;			
			}

			
			data = null;
			data = dbutils.setObject(this.id,this.id,this.id,this.id,this.id,this.id);
			query = "\n insert nvgn_kh select top 1 nvgn_fk, ?" +
			"\n from " +
			"\n (  "+
			"\n     select nvgn_fk from NVGN_tinhthanh a inner join khachhang b on a.TinhThanh_FK = b.TINHTHANH_FK where b.pk_seq = ? " +
			"\n     union "+
			"\n     select nvgn_fk from NVGN_quanhuyen a inner join khachhang b on a.quanhuyen_fk = b.quanhuyen_fk where b.pk_seq = ? " +
			"\n     union "+
			"\n     select nvgn_fk from NVGN_tuyenbanhang a inner join khachhang b on a.TBH_FK in (select tbh_fk from khachhang_tuyenbh where khachhang_fk = b.pk_seq) "+
			"\n     where b.pk_seq =  ? " +
			"\n     union "+
			"\n     select nvgn_fk from ddkd_nvgn a inner join khachhang b on a.ddkd_fk in  "+
			"\n     (select ddkd_fk from tuyenbanhang tbh inner join khachhang_tuyenbh khtbh on tbh.pk_seq = khtbh.TBH_FK  "+
			"\n     where khtbh.KHACHHANG_FK = b.pk_seq ) where b.pk_seq =  ? " +
			"\n )a where not exists (select 1 from nvgn_kh where khachhang_fk = ?) ";
			//System.out.println("Query Insert NVGN: "+query);
			db.update_v2(query,data);

			data = null;
			data = dbutils.setObject(this.id);
			query = "delete from KHACHHANG_TUYENBH where KHACHHANG_FK=? ";
			if (db.update_v2(query,data) < 0)
			{
				this.db.getConnection().rollback();
				this.msg = "Lỗi cập nhật KH 7.";
				return false; 
			}

			String[] tanso = request.getParameterValues("tanso");
			String[] sott = request.getParameterValues("sott");
			String[] tuyenbhId = request.getParameterValues("tbhId");
			String[] tbhIdTmp = request.getParameterValues("tbhIdTmp");
			if (tuyenbhId != null)
			{
				String ts = "";
				for (int i = 0; i < tuyenbhId.length; i++)
				{
					if (i < tanso.length)
						ts	 = tanso[i];
					else
						ts ="F4";
					for (int k = 0; k < tbhIdTmp.length ; k ++)
					{
						if (tbhIdTmp[k].equals(tuyenbhId[i]))
						{
							ts = tanso[k];
						}
					}						

					//System.out.println( "Tan so kh =  "+ ts);

					if (tuyenbhId[i] != null)
					{
						data = null;
						data = dbutils.setObject(this.id,sott[i],ts,tuyenbhId[i],this.id,tuyenbhId[i],ts,sott[i]);
						query= "\n insert into KHACHHANG_TUYENBH(KHACHHANG_FK,SOTT,TANSO,TBH_FK) " +
						"\n select ?, ?, ?, ? " +
						"\n where NOT EXISTS " +
						"\n ( " +
						"\n     select 1 from KHACHHANG_TUYENBH where KHACHHANG_FK=? " +
						"\n     and TBH_FK = ? and TANSO = ? AND SOTT = ? " +
						"\n ) ";
						if (db.update_v2(query, data) < 0)
						{
							this.msg = "Không thể tạo tuyến KH 1!";
							db.getConnection().rollback();
							return false;
						}

						if (this.nppBanChungId.trim().length() >0 && this.ddkdId.trim().length() > 0)
						{
							data = null;
							data = dbutils.setObject(this.nppBanChungId,this.ddkdId,tuyenbhId[i]);
							
							query = "\n select pk_seq from tuyenbanhang " +
							"\n where npp_fk in ( "+data[0]+") and ddkd_fk in ("+data[1]+") and ngayId =(select ngayId from tuyenbanhang where pk_Seq ="+data[2]+" )    ";
							//System.out.println("nppBanChungId sq l = "+query );
							ResultSet rsTuyenKhac = db.get(query);
							while (rsTuyenKhac.next())
							{
								
								String tk = rsTuyenKhac.getString("pk_Seq");
								data = null;
								data = dbutils.setObject(this.id,tk,ts,tk,this.id,tk);
								query = "\n insert into KHACHHANG_TUYENBH(KHACHHANG_FK,SOTT,TANSO,TBH_FK) " +
								"\n select ?, isnull((select MAX(sott) from KHACHHANG_TUYENBH where tbh_fk =? ),0) + 1, ?,? " +
								"\n where NOT EXISTS " +
								"\n ( " +
								"\n     select 1 from KHACHHANG_TUYENBH where KHACHHANG_FK=? " +
								"\n     and TBH_FK=?   "+
								"\n ) ";
								if (db.update_v2(query,data) < 0)
								{
									this.msg = "Không thể tạo tuyến KH 2!";
									db.getConnection().rollback();
									return false;
								}
							}
						}
					}
				}
			}

			data = null;
			data = dbutils.setObject(this.id);
			query = "\n select KHACHHANG_FK,a.tbh_fk, " +
			"\n ( " +
			"\n     select count (*) from KHACHHANG_TUYENBH inner join TUYENBANHANG on TUYENBANHANG.PK_SEQ = KHACHHANG_TUYENBH.TBH_FK " +
			"\n     where KHACHHANG_TUYENBH.KHACHHANG_FK=a.KHACHHANG_FK  AND len(ltrim(rtrim(KHACHHANG_TUYENBH.TANSO))) = 0 and TUYENBANHANG.DDKD_FK = b.DDKD_FK " +
			"\n     group by KHACHHANG_FK, TUYENBANHANG.DDKD_FK " + 
			"\n ) as SoDong, " +
			"\n b.DDKD_FK, a.TANSO as TanSoOld, sum(b.NgayId) OVER (PARTITION BY DDKD_FK,KHACHHANG_FK) as TongNgay, b.NGAYID " +
			"\n from KHACHHANG_TUYENBH a inner join TUYENBANHANG b on b.PK_SEQ=a.TBH_FK " +
			"\n where a.KHACHHANG_FK=? and len(ltrim(rtrim(a.TANSO)))=0 ";
			rs = db.get_v2(query,data);
			while (rs.next())
			{
				int SoDong = rs.getInt("SoDong");
				String TanSoNew = "";
				if (SoDong == 1)
				{
					TanSoNew = "F4";
				}
				else if (SoDong == 2)
				{
					TanSoNew = "F8";
				}
				if (SoDong == 3)
				{
					if ((rs.getInt("TongNgay")%2) == 0 && (rs.getInt("NGAYID")%2) == 0)
						TanSoNew = "F12-2";
					else if ((rs.getInt("TongNgay")%2) !=0 && (rs.getInt("NGAYID")%2) != 0)
					{
						TanSoNew = "F12-1";
					}
					else 
					{
						this.msg = "Vui lòng chọn ngày viếng thăm hợp lệ!";
						db.getConnection().rollback();
						return false;
					}
				}
				String TanSoOld = rs.getString("TanSoOld");
				String tbhId = rs.getString("tbh_fk");
				data = null;
				data = dbutils.setObject(TanSoNew,this.id,TanSoOld,tbhId);
				query = "update KHACHHANG_TUYENBH set tanso=? where khachhang_fk=? and tanso=? and tbh_fk=? ";
				if (db.update_v2(query,data) < 0)
				{
					this.msg = "Lỗi tạo mới tuyến KH 3.";
					db.getConnection().rollback();
					return false;
				}
			}				
			//CHECK XEM DA CO DON HANG CHUA, NEU DA CO THI KHONG DUOC PHEP DOI LOAI KHACH HANG TRONG THANG HIEN TAI
			String dauthangHT = this.getDateTime().split("-")[0] + "-" + this.getDateTime().split("-")[1] + "-01";
			String cuoithangHT = this.getDateTime().split("-")[0] + "-" + this.getDateTime().split("-")[1] + "-" + this.getngayCUOITHANG(this.getDateTime());

			data = null;
			data = dbutils.setObject(this.id,dauthangHT,cuoithangHT);
			query = "select distinct loaikhachhang from DONHANG where khachhang_fk = ? " +
			"and trangthai != 2 and ngaynhap >= ? and ngaynhap <= ? ";	

			//System.out.println("__"+query);

			String loaiKHOLD = "";
			rs = db.get_v2(query,data);
			if (rs != null)
			{
				if (rs.next())
				{
					loaiKHOLD = rs.getString("loaikhachhang");
				}
				rs.close();
			}

			if (loaiKHOLD.trim().length() > 0)
			{
				if (!loaiKHOLD.equals(this.xuatkhau))
				{
					this.msg = "Khách hàng đã phát sinh đơn hàng trong tháng hiện tại. Bạn không thể đổi loại của khách hàng này.";
					db.getConnection().rollback();
					return false;
				}
			}

			
			data = null;
			data = dbutils.setObject(this.id);
			query = "\n update a " +
			"\n set a.isUpdatedFromMCP = '0', a.chucuahieu = nguoimuahang, a.smartId = a.mafast, " +
			"\n a.diachi = case when len (isnull(ltrim(rtrim(sonha)),'') ) = 0 then '' else sonha+', ' end + " +
			"\n	case when len (isnull(ltrim(rtrim(tenduong)),'') ) = 0 then '' else tenduong+', ' end + " +
			"\n	case when len (isnull(ltrim(rtrim(phuongxa)),'') ) = 0 then '' else px.ten+', ' end + " +
			"\n	qh.ten+', '+tt.ten, " +
			"\n a.timkiem = dbo.ftBoDau(a.maFAST + '-' + isnull(a.ten, '') + '-' + " +
			"\n     case when len (isnull(ltrim(rtrim(sonha)),'') ) = 0 then '' else sonha+', ' end + " +
			"\n     case when len (isnull(ltrim(rtrim(tenduong)),'') ) = 0 then '' else tenduong+', ' end + " +
			"\n     case when len (isnull(ltrim(rtrim(phuongxa)),'') ) = 0 then '' else px.ten+', ' end + " +
			"\n     qh.ten+', '+tt.ten  ) " +
			"\n from khachhang a left join phuongxa px on cast(px.pk_seq as nvarchar) = a.phuongxa " +
			"\n inner join quanhuyen qh on qh.pk_seq = a.QUANHUYEN_FK " +
			"\n inner join tinhthanh tt on tt.pk_seq = a.TINHTHANH_FK " +
			"\n where a.pk_seq =? ";
			//System.out.println("query update aaaa: " + query);
			if (db.update_v2(query,data) < 0)
			{
				this.db.getConnection().rollback();
				this.msg = "Lỗi cập nhật KH 8.";
				return false; 
			}		

			if (CheckNPP() == false)
			{

				data = null;
				data = dbutils.setObject(this.nppId,this.id);
				query = "update KHACHHANG set NPP_FK = ?  where pk_seq = ? ";
				if (db.update_v2(query,data) < 0)
				{
					this.db.getConnection().rollback();
					this.msg = "Lỗi cập nhật KH 9.";
					return false; 
				}	
			}
			data = null;
			data = dbutils.setObject(this.id);
			query  ="delete KHACHHANG_NPP where khachhang_fk =?";
			if (db.update_v2(query,data) < 0)
			{
				this.db.getConnection().rollback();
				this.msg = "Lỗi cập nhật KH 10.";
				return false; 
			}

			data = null;
			data = dbutils.setObject(this.id,this.nppId);
			query = "Insert KHACHHANG_NPP( khachhang_fk, npp_fk ) values ( ?, ? ) ";
			//System.out.println("------CHEN DATA: " + query );
			if (db.update_v2(query,data) < 0)
			{
				this.db.getConnection().rollback();
				this.msg = "Lỗi cập nhật KH 11.";
				return false; 
			}

			if (this.nppBanChungId.trim().length() >0)
			{
				data = null;
				data = dbutils.setObject(this.id,this.nppBanChungId);
				query = "Insert KHACHHANG_NPP( khachhang_fk, npp_fk ) select "+data[0]+", pk_seq from nhaphanphoi where pk_seq in( "+data[1]+" ) ";
				//System.out.println("------CHEN DATA: " + query );
				if (!db.update(query))
				{
					this.db.getConnection().rollback();
					this.msg = "Lỗi cập nhật KH 12.";
					return false; 
				}
			}
			
			data = null;
			data = dbutils.setObject(this.id);
			query = " delete KHACHHANG_ChietKhau where KhachHang_FK = ?";
			if (db.update_v2(query,data) < 0 )
			{
				this.db.getConnection().rollback();
				this.msg = "Lỗi cập nhật KH 13.";
				return false; 
			}	

			if (spIds != null && spIds.length > 0)
			{
				for (int i = 0 ; i < spIds.length; i ++)
				{
					double ck = geso.dms.center.util.Utility.parseDouble(this.chietkhauSp[i].replace(",",""));
					if (ck <= 0)
					{
						db.getConnection().rollback();
						this.msg = "Bạn chưa nhập chiết khấu ở dòng ("+(i+1)+").";
						return false;
					}
					
					if (this.ctckIds[i].trim().length() <= 0)
					{
						db.getConnection().rollback();
						this.msg = "Bạn chưa chọn Program ở dòng ("+(i+1)+").";
						return false;
					}
					
					data = null;
					data = dbutils.setObject(this.id,this.spIds[i],ck,this.ctckIds[i],this.id,this.spIds[i]);
					query = "\n insert KHACHHANG_ChietKhau( KhachHang_FK,SanPham_FK,ChietKhau,ctck_fk,scheme)" +
					"\n select ?, ?, round(?,2), pk_seq, scheme" +
					"\n from ctchietkhau " +
					"\n where pk_seq = ? " +
					"\n and not exists (select 1 from KHACHHANG_ChietKhau where KhachHang_FK = ? and Sanpham_fk = ?) " ;
					if (db.update_v2(query,data)<= 0 )
					{
						this.msg = "CK Sản phẩm dòng ("+(i+1)+") đã được thiết lập.";
						db.getConnection().rollback();
						return false;
					}	
				}
			}

			data = null;
			data = dbutils.setObject(this.userId,this.id);
			query = "\n insert KhachHang_ChietKhau_Log( KhachHang_FK,SanPham_FK,ChietKhau,ctck_fk,scheme,userId) " +
			"\n select KhachHang_FK,SanPham_FK,ChietKhau,ctck_fk,scheme,? " +
			"\n from  KHACHHANG_ChietKhau where  KhachHang_FK = ?"; 
			if (db.update_v2(query,data)<0 )
			{
				this.db.getConnection().rollback();
				this.msg = "Lỗi cập nhật KH 14.";
				return false; 
			}


			this.db.getConnection().commit();
			this.db.getConnection().setAutoCommit(true);
		}
		catch(Exception er)
		{
			this.msg = "Lỗi ngoại lệ cập nhật: "+er.getMessage();
			this.db.update("rollback");
			er.printStackTrace();
			return false;
		}
		
		return true;
	}

	
	public String saveImage(String UrlLocal,String imageString,String NameImage) {
		BufferedImage image = null;
		String blobString=imageString;
		if(imageString.contains("base64,"))
			blobString=imageString.split("base64,")[1];
		
		//System.out.println("blobString----- "+blobString);
		
		byte[] byteArray = Base64.getDecoder().decode(blobString);
		ByteArrayInputStream bis = new ByteArrayInputStream(byteArray);
		try {
		    image = ImageIO.read(bis);
		    File file = new File(UrlLocal+""+NameImage+"");
		    ImageIO.write(image, "jpeg", file);
		} catch (Exception e) {
		    e.printStackTrace();
		    return e.getMessage();
		}
		return "";
	}
	
	
	private String getngayCUOITHANG(String ngaygiaodich) 
	{
		String[] arr = ngaygiaodich.split("-");
		String ngaycuoithang = "";

		switch ( Integer.parseInt(arr[1]) ) {
		case 1:
		case 3:
		case 5:
		case 7:
		case 8:
		case 10:
		case 12:
			ngaycuoithang    = "31";
			break;
		case 4:
		case 6:
		case 9:
		case 11:
			ngaycuoithang    = "30";
			break;
		case 2:
			if ( Integer.parseInt(arr[0]) % 100 != 0 && Integer.parseInt(arr[0]) % 4 == 0 ) {
				ngaycuoithang    = "29";
			} else {
				ngaycuoithang    = "28";
			}
			break;
		default: ngaycuoithang    = "28";
		} 

		return ngaycuoithang;

	}


	public void init() 
	{
		this.getNppInfo();

		String query =  "\n select isnull(a.shopkey, '0') shopkey, isnull(a.moiquanhe,'')moiquanhe,isnull(a.thoigiangap,'')thoigiangap,isnull(makhosap,'')makhosap, isnull(a.sonha,'')sonha ,isnull(a.tenduong,'')tenduong,isnull(a.apto,'')apto,isnull(m.diengiai,'')vitri,isnull(a.cmnd,'')cmnd,isnull(a.didong,'')didong,isnull(a.mahd,'')mahd,isnull(a.masothue,'')masothue,isnull(a.lat,'0')lat,isnull(a.long,'0')long,isnull(convert(varchar,a.thanhthinongthon_fk),'')thanhthinongthon_fk,isnull(a.tencuahieu,'')tencuahieu,isnull(a.nguoimuahang,'')nguoimuahang,isnull(a.vochong,'')vochong, isnull(a.ngsinh_vochong,'')ngsinh_vochong,isnull(con1,'')con1,isnull(a.ngsinh_con1,'')ngsinh_con1,isnull(con2,'')con2,isnull(a.ngsinh_con2,'')ngsinh_con2,isnull(a.ghichu,'')ghichu,isnull(a.taitro,'')taitro,isnull(a.ngaytaitro,'')ngaytaitro,isnull(a.macu,'')macu ,isnull(a.ANHCUAHANG,'')ANHCUAHANG, isnull(a.PHUONGXA,0) AS PHUONGXA,isnull(a.songayno,0) as songayno,isnull(a.sotienno,0) as sotienno  " +
		"\n 	,a.maHD as HopDong,a.cmnd,isnull(a.thanhtoan,'0') as thanhtoan ,  a.pk_seq as khId, a.smartid,a.ten as khTen, a.trangthai, a.ngaytao, a.ngaysua, isnull(a.masothue, '') as masothue, b.ten as nguoitao, c.ten as nguoisua, d.chietkhau, d.pk_seq as mckId, isnull(a.tenkyhd,'') tenkyhd, "+
		"\n  	e.diengiai as kbhTen, e.pk_seq as kbhId, f.hang as hchTen, f.pk_seq as hchId, g.loai as lchTen, g.pk_seq as lchId, h.ten as nppTen, h.pk_seq as nppId, a.mauhoadon, "+
		"\n 		k.sotienno as ghcnTen, k.pk_seq as ghcnId, l.ten as bgstTen, l.pk_seq as bgstId, m.vitri as vtchTen, m.pk_seq as vtchId, a.dienthoai, a.diachi, isnull(a.diachigiaohang,'')diachigiaohang,a.tinhthanh_fk as tpId, a.quanhuyen_fk as qhId,a.DiaDiem_FK as DiaDiemId,isnull(a.XuatKhau,0) as XuatKhau, KhongKyHopDong,isnull(a.MaFast,'')  as MaFast, isnull(a.chucuahieu, '') as chucuahieu, THANHTOANQUY, PT_CHIETKHAU,a.TrucTHUOC_FK, a.kho_fk,a.gioitinh, "+
		"\n 	STUFF      "+
		"\n 	(    "+
		"\n 		(   "+
		"\n     select DISTINCT TOP 100 PERCENT ' , ' +cast( tbh.nvgn_fk as nvarchar(18) )   		"+
		"\n 			from nvgn_kh tbh "+
		"\n      where khachhang_fk=a.pk_seq                  "+ 
		"\n   ORDER BY ' , ' +cast( tbh.nvgn_fk as nvarchar(18) )   	"+
		"\n 			FOR XML PATH('')      "+
		"\n 		 ), 1, 2, ''   "+
		"\n 	) + ' '  AS nvgnId" +
		"\n 	,STUFF      "+
		"\n 	(    "+
		"\n 		(   "+
		"\n     		select DISTINCT TOP 100 PERCENT ',' +cast( npp.npp_fk as nvarchar(18) )   		"+
		"\n 				from khachhang_npp npp "+
		"\n     	 	where khachhang_fk=a.pk_seq and   npp.npp_fk <> a.npp_fk "+ 
		"\n   			ORDER BY ',' +cast( npp.npp_fk as nvarchar(18) )   	"+
		"\n 			FOR XML PATH('')      "+
		"\n 		 ), 1, 1, ''   "+
		"\n 	) + ''  AS nppBanChungId" +
		"\n 	,a.Mst_CaNHAN,A.NgaySinh, a.npp_fk as nppCHINH,a.diaban_fk "+
		"\n		,isnull(a.img_create1,'') ANH1,isnull(a.img_create2,'') ANH2"+
		"\n     ,isnull(a.img_create3,'') ANH3,isnull(a.img_create4,'') ANH4"+
		"\n from khachhang a inner join nhanvien b on a.nguoitao = b.pk_seq inner join nhanvien c on a.nguoisua = c.pk_seq left join mucchietkhau d on a.chietkhau_fk = d.pk_seq "+
		"\n left join kenhbanhang e on a.kbh_fk = e.pk_seq left join hangcuahang f on a.hch_fk = f.pk_seq left join loaicuahang g on a.lch_fk = g.pk_seq "+
		"\n left join nhaphanphoi h on a.npp_fk = h.pk_seq left join gioihancongno k on a.ghcn_fk = k.pk_seq left join banggiasieuthi l on a.bgst_fk = l.pk_seq "+
		"\n left join vitricuahang m on a.vtch_fk = m.pk_seq " +
		"\n where a.pk_seq = '" + this.id + "'";

		//System.out.println("---INIT KHACH HANG: " + query);

		ResultSet rs =  this.db.get(query);
		try
		{
			rs.next();
			
			this.anhArr=new String[4];
			
			anhArr[0]=rs.getString("ANH1");
			anhArr[1]=rs.getString("ANH2");
			anhArr[2]=rs.getString("ANH3");
			anhArr[3]=rs.getString("ANH4");
			
			moiquanhe = rs.getString("moiquanhe");
			thoigiangap = rs.getString("thoigiangap");
			this.tenAnhDaiDien  = rs.getString("ANHCUAHANG");
			this.makhoSAP = rs.getString("makhosap");
			this.id = rs.getString("khId");
			this.macu = rs.getString("macu");
			this.ten = rs.getString("khTen");
			this.sodienthoai = rs.getString("dienthoai")!=null ? rs.getString("dienthoai") :"";
			this.masothue = rs.getString("masothue");
			this.sonha = rs.getString("sonha");
			this.shopkey = rs.getString("shopkey");
			this.tenduong = rs.getString("tenduong");
			this.apto = rs.getString("apto");
			this.diachi = rs.getString("diachi");
			this.diachigiaohang = rs.getString("diachigiaohang");
			this.vochong = rs.getString("vochong");
			this.ngsinh_vochong = rs.getString("ngsinh_vochong");
			this.con1 = rs.getString("con1");
			this.ngsinh_con1 = rs.getString("ngsinh_con1");
			this.con2 = rs.getString("con2");
			this.ngsinh_con2 = rs.getString("ngsinh_con2");
			this.ghichu = rs.getString("ghichu");
			this.taitro = rs.getString("taitro");
			this.ngaytaitro = rs.getString("ngaytaitro");
			this.tencuahieu = rs.getString("tencuahieu");
			this.nguoimuahang = rs.getString("nguoimuahang");
			this.thanhthinongthonId = rs.getString("thanhthinongthon_fk");			
			this.mauhd = rs.getString("mauhoadon")==null?"": rs.getString("mauhoadon");
			this.tpId =  rs.getString("tpId")==null?"": rs.getString("tpId");
			this.qhId = rs.getString("qhId")==null?"": rs.getString("qhId");
			this.diabanId = rs.getString("diaban_fk")==null?"": rs.getString("diaban_fk");
			this.trangthai = rs.getString("trangthai");
			this.ngaytao = rs.getString("ngaytao");
			this.nguoitao = rs.getString("nguoitao");
			this.ngaysua = rs.getString("ngaysua");
			this.nguoisua = rs.getString("nguoisua");
			this.mck = rs.getString("chietkhau");
			this.mckId = rs.getString("mckId");
			this.tenkyhd = rs.getString("tenkyhd");
			this.kbh = rs.getString("kbhTen");
			this.kbhId = rs.getString("kbhId");
			this.hch = rs.getString("hchTen");
			this.hchId = rs.getString("hchId")==null?"":rs.getString("hchId");
			this.lch = rs.getString("lchTen");
			this.lchId = rs.getString("lchId");
			this.ghcn = rs.getString("ghcnTen");
			this.ghcnId = rs.getString("ghcnId");
			this.bgst = rs.getString("bgstTen");
			this.vtch = rs.getString("vtchTen");
			this.vtchId = rs.getString("vtchId");
			this.nvgnId = rs.getString("nvgnId")==null?"":rs.getString("nvgnId");
			this.diadiemId = rs.getString("diadiemId")==null?"":rs.getString("diadiemId");
			this.hopdong = rs.getString("hopdong")==null?"":rs.getString("hopdong");
			this.xuatkhau = rs.getString("xuatkhau")==null?"0":rs.getString("xuatkhau");
			this.pt_chietkhau = rs.getString("PT_CHIETKHAU")==null?"":rs.getString("PT_CHIETKHAU");
			this.dtId = rs.getString("tructhuoc_fk")==null?"":rs.getString("tructhuoc_fk");
			this.ngaysinh = rs.getString("ngaysinh")==null?"":rs.getString("ngaysinh");
			this.mst = rs.getString("Mst_CaNHAN")==null?"":rs.getString("Mst_CaNHAN");
			this.kokyhd = rs.getString("KhongKyHopDong");
			this.maFAST = rs.getString("maFAST");
			this.thanhtoan = rs.getString("thanhtoan")==null?"":rs.getString("thanhtoan");
			this.thanhtoanQUY = rs.getString("THANHTOANQUY")==null?"":rs.getString("THANHTOANQUY");
			this.chucuahieu = rs.getString("chucuahieu");
			this.khoId = rs.getString("kho_fk") == null ? "" : rs.getString("kho_fk");
			this.cmnd= rs.getString("cmnd") == null ? "" : rs.getString("cmnd");
			this.gioiTinh = rs.getString("gioitinh") == null ? "" : rs.getString("gioitinh");
			this.phuongxa = rs.getString("phuongxa");
			this.vitri = rs.getString("vtchid");
			this.didong = rs.getString("didong");
			this.mahd = rs.getString("mahd");
			this.sotienno = Double.parseDouble(rs.getString("sotienno") == null ? "0.0" : rs.getString("sotienno"));
			this.songayno = Double.parseDouble(rs.getString("songayno") == null ? "0.0" : rs.getString("songayno"));

			//NẾU LÀ KHÁCH HÀNG LẤY TỪ NPP KHÁC THÌ KHÔNG ĐƯỢC SỬA THÔNG TIN GÌ HẾT
			//System.out.println("---NPP CHINH: " + rs.getString("nppCHINH") + " -- NPP ID: " + this.nppId );
			if (rs.getString("nppCHINH") != null)

				this.nppBanChungId = rs.getString("nppBanChungId") == null ? "":  rs.getString("nppBanChungId");

			query = "\n select b.TBH_FK,b.KHACHHANG_FK,a.DDKD_FK " +
			"\n from TUYENBANHANG a inner join KHACHHANG_TUYENBH b on b.TBH_FK=a.PK_SEQ" +
			"\n inner join DaiDienkinhDoanh ddkd on ddkd.pk_seq = a.DDKD_FK and ddkd.trangthai = 1  "+
			"\n where b.KHACHHANG_FK='"+this.id+"' ";
			if (nppBanChungId.trim().length() >0)
				query += " and a.npp_fk not in (" + this.nppBanChungId + ")";// ko hiện tuyến của NPP bán chung(HC)

			//System.out.println("__________"+query);

			this.ddkdId="";
			this.tbhId="";

			rs=db.get(query);
			int i=0;
			while (rs.next())
			{
				if (i==0)
				{
					this.ddkdId +=rs.getString("DDKD_FK");
					this.tbhId +=rs.getString("TBH_FK");
				}
				else
				{
					this.ddkdId +=  "," +rs.getString("DDKD_FK");
					this.tbhId +="," + rs.getString("TBH_FK")+",";
				}
				i++;
			}
			//System.out.println("DDKD_ID = " + this.ddkdId );	

			String temp  ="";
			query ="select nkh_fk from NHOMKHACHHANG_KHACHHANG where kh_fk =  " + this.id;
			rs=db.get(query);
			while (rs.next()) {
				temp += rs.getString("nkh_fk") + ",";
			}
			if (temp.trim().length()>0)
			{
				//System.out.println("temp = " + temp  );
				temp = temp.substring(0, temp.length() - 1);
				this.nkh_khIds = temp.split(",");

			}
			
			
			
			rs = db.get(" select count(*)c from KhachHang_chietKhau where khachhang_fk =  "+this.id);
			if (rs.next())
				this.count = rs.getInt("c");
			
			this.spIds = new String[this.count];
			this.chietkhauSp = new String[this.count];
			this.ctckIds = new String[this.count];
			{
				int dem = 0;
				rs = db.get(" select sanpham_fk,chietkhau,ctck_fk from KhachHang_chietKhau where khachhang_fk =  "+this.id);
				while (rs.next())
				{
					this.spIds[dem] = rs.getString("sanpham_fk");
					this.chietkhauSp[dem] = rs.getString("chietkhau");
					this.ctckIds[dem] = rs.getString("ctck_fk");
					dem ++;
				}
			}
		}
		catch(Exception e)
		{
			e.printStackTrace();
			this.msg="Loi Trong Qua Trinh Lay Du Lieu ."+ e.toString();
		}
		finally
		{
			try 
			{
				if (rs != null)
					rs.close();
			} catch (Exception e2) 
			{

			}
		}


		createRS(); 
		//System.out.println("NPPid "+this.nppId);
	}

	private String getDateTime()
	{
		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		Date date = new Date();
		return dateFormat.format(date);	
	}
	
	private String getFulDateTime()
	{
		DateFormat dateFormat = new SimpleDateFormat("yyMMddHHmmss");
		Date date = new Date();
		return dateFormat.format(date);	
	}

	public String getMasothue() 
	{
		return this.masothue;
	}

	public void setMasothue(String masothue) 
	{
		this.masothue = masothue;
	}



	public void DBclose() {

		try {

			if (this.ghcongno != null)
				this.ghcongno.close();
			if (this.hangcuahang != null)
				this.hangcuahang.close();
			if (this.loaicuahang != null)
				this.loaicuahang.close();
			if (this.kenhbanhang != null)
				this.kenhbanhang.close();
			if (this.mucchietkhau != null)
				this.mucchietkhau.close();
			if (this.nhomcuahang != null)
				this.nhomcuahang.close();
			if (this.nkh_khSelected != null)
				this.nkh_khSelected.close();
			if (this.nkh_khList != null)
				this.nkh_khList.close();
			if (this.qh != null)
				this.qh.close();
			if (this.tp != null)
				this.tp.close();
			if (this.vtcuahang != null)
				this.vtcuahang.close();
			if (nvgnRs!=null){
				nvgnRs.close();
			}
			if (khoRs!=null){
				khoRs.close();
			}
			if (this.db != null)
				this.db.shutDown();

		} catch (Exception e) {
			// TODO: handle exception
		}

	}


	public ResultSet getNvgnRs() 
	{
		return this.nvgnRs;
	}

	public void setNvgnRs(ResultSet nvgnRs) 
	{
		this.nvgnRs = nvgnRs;
	}

	public String getNvgnId() 
	{
		return this.nvgnId;
	}

	public void setNvgnId(String nvgnId) 
	{
		this.nvgnId = nvgnId;
	}


	public String gettype() 
	{
		return this.type;
	}


	public void settype(String _type) 
	{
		this.type=_type;
	}


	String diadiemId,xuatkhau;
	public String getXuatkhau()
	{
		return xuatkhau;
	}

	public void setXuatkhau(String xuatkhau)
	{
		this.xuatkhau = xuatkhau;
	}

	public String getDiadiemId()
	{
		return diadiemId;
	}

	public void setDiadiemId(String diadiemId)
	{
		this.diadiemId = diadiemId;
	}

	public ResultSet getDiadiemRs()
	{
		return diadiemRs;
	}

	public void setDiadiemRs(ResultSet diadiemRs)
	{
		this.diadiemRs = diadiemRs;
	}
	ResultSet diadiemRs;


	public String getMaFAST() {

		return this.maFAST;
	}


	public void MaFAST(String maFAST) {

		this.maFAST = maFAST;
	}


	public String getKhongkyhd() {

		return this.kokyhd;
	}


	public void setKhongkyhd(String khongkyhd) {

		this.kokyhd = khongkyhd;
	}


	public String getChucuahieu() {

		return this.chucuahieu;
	}


	public void setChucuahieu(String chucuahieu) {

		this.chucuahieu = chucuahieu;
	}

	String ddkdId;

	public String getDdkdId()
	{
		return ddkdId;
	}
	public void setDdkdId(String ddkdId)
	{
		this.ddkdId=ddkdId;
	}
	ResultSet ddkdRs;

	public ResultSet getDdkdRs()
	{
		return this.ddkdRs;
	}
	public void setDdkdRs(ResultSet ddkdRs)
	{
		this.ddkdRs=ddkdRs;
	}

	String tbhId;
	public String getTbhId()
	{
		return this.tbhId;
	}
	public void setTbhId(String tbhId)
	{
		this.tbhId=tbhId;
	}

	ResultSet tbhRs;
	public ResultSet getTbhRs()
	{
		return this.tbhRs;
	}
	public void setTbhRs(ResultSet tbhRs)
	{
		this.tbhRs=tbhRs;

	}

	String hopdong;

	public String getHopdong()
	{
		return hopdong;
	}

	public void setHopdong(String hopdong)
	{
		this.hopdong = hopdong;
	}


	public String getThanhtoanQuy() {

		return this.thanhtoanQUY;
	}


	public void setThanhtoanQuy(String thanhtoanquy) {

		this.thanhtoanQUY = thanhtoanquy;
	}


	public String getPT_Chietkhau() {

		return this.pt_chietkhau;
	}


	public void setPT_Chietkhau(String ptCK) {

		this.pt_chietkhau = ptCK;
	}


	public String getmauhd() {

		return this.mauhd;
	}


	public void setmauhd(String mauhd) {
		this.mauhd=mauhd;

	}


	public String getkhoId() {

		return this.khoId;
	}


	public void setkhoId(String khoId) {

		this.khoId=khoId;
	}


	public ResultSet getKhoRs() {

		return this.khoRs;
	}


	public void setKhoRs(ResultSet khoRs) {

		this.khoRs=khoRs;
	}


	String dtId;
	ResultSet dtRs;

	public String getDtId()
	{
		return dtId;
	}

	public void setDtId(String dtId)
	{
		this.dtId = dtId;
	}

	public ResultSet getDtRs()
	{
		return dtRs;
	}
	public void setDtRs(ResultSet dtRs)
	{
		this.dtRs = dtRs;
	}


	public String getTenKyHd() {

		return this.tenkyhd;
	}


	public void setTenKyHd(String TenKyHd) {

		this.tenkyhd=TenKyHd;
	}

	String ngaysinh,mst;

	public String getNgaysinh()
	{
		return ngaysinh;
	}

	public void setNgaysinh(String ngaysinh)
	{
		this.ngaysinh = ngaysinh;
	}

	public String getMst()
	{
		return mst;
	}

	public void setMst(String mst)
	{
		this.mst = mst;
	}



	public String getPhuongxaId() {
		return phuongxaId;
	}
	public void setPhuongxaId(String phuongxaId) {
		this.phuongxaId = phuongxaId;
	}

	public ResultSet getPhuongxaRs() {
		return phuongxaRs;
	}

	public String getDidong() {
		return didong;
	}
	public void setDidong(String didong) {
		this.didong = didong;
	}
	//Sring thanhthinongthonId = "";
	public String getThanhthinongthonId() {
		return thanhthinongthonId;
	}
	public void setThanhthinongthonId(String thanhthinongthonId) {
		this.thanhthinongthonId = thanhthinongthonId;
	}
	//ResultSet thanhthinongthonRs;
	public ResultSet getThanhthinongthonRs() {
		return thanhthinongthonRs;
	}
	//double songayno  = 0;
	public double getSongayno() {
		return songayno;
	}
	public void setSongayno(double songayno) {
		this.songayno = songayno;
	}
	//double sotienno = 0;
	public double getSotienno() {
		return sotienno;
	}
	public void setSotienno(double sotienno) {
		this.sotienno = sotienno;
	}



	public String getKhachhangId() {

		return this.khachhangId;
	}


	public void setKhachhangId(String khachhangId) {

		this.khachhangId = khachhangId;
	}


	public ResultSet getKhachhangRs() {

		return this.khachhangRs;
	}


	public void setKhachhangRs(ResultSet khachhangRs) {

		this.khachhangRs = khachhangRs;
	}



	ResultSet nppRs;

	private String hinhthucTT;
	public ResultSet getNppRs()
	{
		return nppRs;
	}
	public void setNppRs(ResultSet nppRs)
	{
		this.nppRs = nppRs;
	}


	public String getHinhthucTT() {
		return this.hinhthucTT;
	}


	public String getPhuongxa() {
		return this.phuongxa;
	}


	public void setPhuongxa(String value) {
		this.phuongxa = value;
	}


	public void setHinhthucTT(String value) {
		this.hinhthucTT = value;
	}

	public String getNppBanChungId() {
		return nppBanChungId;
	}
	public void setNppBanChungId(String nppBanChungId) {
		this.nppBanChungId = nppBanChungId;
	}
	public ResultSet getNppBanChungRs() {
		return nppBanChungRs;
	}
	public void setNppBanChungRs(ResultSet nppBanChungRs) {
		this.nppBanChungRs = nppBanChungRs;
	}


	public String getKT() {

		return this.kt;
	}
	public String getDiabanId() {
		return diabanId;
	}
	public void setDiabanId(String diabanId) {
		this.diabanId = diabanId;
	}
	public ResultSet getDiabanRs() {
		return diabanRs;
	}
	public String getTenAnhDaiDien() {
		return tenAnhDaiDien;
	}
	public void setTenAnhDaiDien(String tenAnhDaiDien) {
		this.tenAnhDaiDien = tenAnhDaiDien;
	}

	public String XoaAnhDaiDien(String khId)
	{
		try
		{
			db.getConnection().setAutoCommit(false);

			String query =	" update KhachHang set AnhCuaHang = null,nguoisua ="+this.userId+",ngaysua ='"+geso.dms.center.util.Utility.getNgayHienTai()+"'" +
			" where AnhCuaHang is not null and pk_seq  = "+ khId ;
			if (db.updateReturnInt(query) <= 0 )
			{
				db.getConnection().rollback();
				db.getConnection().setAutoCommit(true);
				return "Không thể xóa ảnh đại diện Khách hàng";
			}
			db.getConnection().commit();
			db.getConnection().setAutoCommit(true);
			return "Xóa ảnh đại diện thành công!";
		}
		catch(Exception e)
		{
			db.update("rollback");
			return "Lỗi ngoại lệ:" + e.getMessage();

		}

	}

	

	public static String Prefix_MaKhachHang(Idbutils db,String qhId, String ttId)
	{
		String query ="\n select   tt.ma +'1' as ma from tinhthanh tt  where tt.PK_SEQ ="+ttId;
		System.out.println("query prefixMa : "+ query);
		String prefixMa = "";
		ResultSet rs;
		try 
		{
			
			rs = db.get(query);
			rs.next();
			prefixMa = rs.getString("ma");
			rs.close();
			return prefixMa;
		}
		catch (Exception e) {
			e.printStackTrace();
			return "";
		}
	}

	public static int MaKhachHang(Idbutils db,String qhId, String ttId)
	{
		try 
		{
			String query = "";
			int so = -1;
			query = "\n  	select isnull(max(stt),0)+1 max from MaKhachHang  where tinhthanh_fk = "+ttId+" ";
			System.out.println("makhachhang getSTT: " + query);
			ResultSet rs = db.get(query);
			
			System.out
			.println("dbma = "+ (db == null));
			
			if (rs.next())
			{
				so = rs.getInt("max");
				rs.close();
				
			}			
			else
			{
				rs.close();
				so = 1;
			}
			return so;

		} catch (SQLException e) {
			e.printStackTrace();
			return -1;
		}
			
	}

	public static String Log_MaKhachHang(Idbutils db,String khId,int stt)
	{	
		  String query = "\n insert makhachhang(KhachHang_FK,Ma,stt,tinhthanh_fk,matinh,quanhuyen_fk,maquan) " +
                  "\n	select kh.pk_seq, kh.mafast, " + stt + ",kh.tinhthanh_fk, tt.ma,kh.quanhuyen_fk,qh.ma " +
                  "\n from khachhang kh " +
                  "\n inner join tinhthanh tt on tt.pk_seq = kh.tinhthanh_fk " +
                  "\n left join quanhuyen qh on qh.pk_seq = kh.quanhuyen_fk" +
                  "\n where kh.pk_seq = " + khId;
		System.out.println("log_makh : "+ query);
		if (db.updateReturnInt(query) <=0)
		{
			return "Không thể lưu Log mã KH";
		}
		return "";
	}
	
	public String getGioiTinh() {
		
		return this.gioiTinh;
	}

	
	public void setGioiTinh(String GioiTinh) {
		
		this.gioiTinh = GioiTinh;
	}

	
	public String getView() {
		return view;
	}
	public void setView(String view) {
		this.view = view;
	}

	
	/*// chiet khau khach hahg
	int pageX= 0;
	int pageY = 0;

	int count = 0;
	ResultSet sanphamRs,ctckRs;
	String[]spIds;
	String[] chietkhauSp;
	String[] ctckIds;*/
	public int getCount() {
		return count;
	}
	public void setCount(int count) {
		this.count = count;
	}
	public int getPageX() {
		return pageX;
	}
	public int getPageY() {
		return pageY;
	}
	public ResultSet getSanphamRs() {
		return sanphamRs;
	}
	public ResultSet getCtckRs() {
		return ctckRs;
	}
	public String[] getSpIds() {
		return spIds;
	}
	public String[] getChietkhauSp() {
		return chietkhauSp;
	}
	public String[] getCtckIds() {
		return ctckIds;
	}
	public void setPageX(int pageX) {
		this.pageX = pageX;
	}
	public void setPageY(int pageY) {
		this.pageY = pageY;
	}
	public void setSanphamRs(ResultSet sanphamRs) {
		this.sanphamRs = sanphamRs;
	}
	public void setCtckRs(ResultSet ctckRs) {
		this.ctckRs = ctckRs;
	}
	public void setSpIds(String[] spIds) {
		this.spIds = spIds;
	}
	public void setCtckIds(String[] ctckIds) {
		this.ctckIds = ctckIds;
	}
	public void setChietkhauSp(String[] chietkhauSp) {
		this.chietkhauSp = chietkhauSp;
	}
	
	private boolean checkNPP_NVGN(String nppId, Idbutils db) {
		try {
			if (Utility.isValid(nppId)) {
				String query = "select count(*) c from nhanviengiaonhan where npp_fk = "+nppId;
				ResultSet rs = db.get(query);
				int check = 0;
				while (rs.next()) {
					check = rs.getInt("c");
				}
				rs.close();

				if (check <= 0) {
					this.msg = "Nhà phân phối chưa có Nhân viên giao nhận, vui lòng thiết lập.";
					return false;
				}
			}
		}
		catch (Exception e) {
			e.printStackTrace();
			return false;
		}

		return true;
	}
	@Override
	public String getMatkhau() {
		// TODO Auto-generated method stub
		return this.matkhau;
	}
	@Override
	public void setMatkhau(String matkhau) {
		// TODO Auto-generated method stub
		this.matkhau = matkhau;
	}
}	

