package geso.dms.distributor.beans.khachhang;

import java.io.Serializable;
import java.sql.ResultSet;
import java.util.Hashtable;

import javax.servlet.http.HttpServletRequest;

public interface IKhachhang extends Serializable
{
	//Cac thuoc tinh 
	public String getUserId();
	public void setUserId(String userId);
	public String getId();
	public void setId(String id);
	public String getTen();
	
	public String getKT();
	public void setTen(String ten);
	public String getDiachi();
	public void setDiachi(String diachi);
	public String getTpId(); 
	public void setTpId(String tpId); 
	public String getQhId(); 
	public void setQhId(String qhId); 	
	public String getSodienthoai();
	public void setSodienthoai(String sodienthoai);	
	public String getMasothue();
	public void setMasothue(String masothue);	
	public String getTrangthai();
	public void setTrangthai(String trangthai);
	public String getShopkey();
	public void setShopkey(String shopkey);
	public String getMatkhau();
	public void setMatkhau(String matkhau);
	public String getNgaytao();
	public void setNgaytao(String ngaytao);
	public String getNguoitao();
	public void setNguoitao(String nguoitao);
	public String getNgaysua();
	public void setNgaysua(String ngaysua);	
	public String getNguoisua();
	
	public void setAnhuploai(String[] arranhupload);	
	public String[] getAnhuploai();
	
	public void setNguoisua(String nguoisua);
	public String getMessage();
	public void setMessage(String msg);
	
	public String getHch();
	public void setHch(String hch);
	public String getKbh();
	public void setKbh(String kbh);
	
	public String getVtch();
	public void setVtch(String vtch);
	public String getLch();
	public void setLch(String lch);
	public String getNch();
	public void setNch(String nch);
	
	public String getMck();
	public void setMck(String mck);
	public String getGhcn();
	public void setGhcn(String ghcn);
	
	public String getNppId();
	public void setNppId(String nppId);
	public String getNppTen();
	public void setNppTen(String nppTen);
	public String getSitecode();
	public void setSitecode(String sitecode);
	
	//Cac phuong thuc Get, Set tra ve ResultSet tuong ung
	public ResultSet getTp(); 
	public void setTp(ResultSet tp); 
	public ResultSet getQh(); 
	public void setQh(ResultSet qh); 	
	public ResultSet getHangcuahang();
	public void setHangcuahang(ResultSet hangcuahang);	
	public ResultSet getKenhbanhang();
	public void setKenhbanhang(ResultSet kenhbanhang);
	
	public ResultSet getVtcuahang();
	public void setVtcuahang(ResultSet vtcuahang);
	public ResultSet getLoaicuahang();
	public void setLoaicuahang(ResultSet loaicuahang);
	public ResultSet getNhomcuahang();
	public void setNhomcuahang(ResultSet nhomcuahang);
	public ResultSet getMucchietkhau();
	public void setMucchietkhau(ResultSet mucchietkhau);
	public ResultSet getGhcongno();
	public void setGhcongno(ResultSet ghcongno);
	
	//Cac phuong thuc Get, Set cua thuoc tinh duoc chon
	public String getHchId();
	public void setHchId(String hchId);
	public String getKbhId();
	public void setKbhId(String kbhId);
	
	public String getVtchId();
	public void setVtId(String vtchId);
	public String getLchId();
	public void setLchId(String lchId);
	public String getNchId();
	public void setNchId(String nchId);
	public String getMckId();
	public void setMckId(String mckId);
	public String getGhcnId();
	public void setGhcnId(String ghcnId);
	
	public ResultSet getNkh_khList();
	public void setNkh_khList(ResultSet nkh_khlist);
	public ResultSet getNkh_KhSelected(); //cap dvkd_ncc duoc chon trong trang Update
	public void setNkh_KhSelected(ResultSet nkh_khselected);
	public Hashtable<Integer, String> getNkh_KhIds();
	public void setNkh_KhIds(String[] nkh_khIds);
	
	public ResultSet getNvgnRs();
	public void setNvgnRs(ResultSet nvgnRs);
	public String getNvgnId();
	public void setNvgnId(String nvgnId);
	
	public String gettype();
	public void settype(String type);
	
	public boolean CreateKh( HttpServletRequest request);
	public boolean UpdateKh( HttpServletRequest request);
	public void createRS();
	public void init();
	public void DBclose();
	
	public String getDiadiemId();
	public void setDiadiemId(String diadiemId);
	
	public ResultSet getDiadiemRs();
	public void setDiadiemRs(ResultSet diadiemRs);
	
	public String getMaFAST();
	public void MaFAST(String maFAST);
	
	public String getXuatkhau();
	public void setXuatkhau(String xuatkhau);
	
	public String getKhongkyhd();
	public void setKhongkyhd(String khongkyhd);
	
	public String getThanhtoan();
	public void setThanhtoan(String thanhtoan);
	
	public String getLoaiNPP();
	public void setLoaiNPP(String loaiNPP);
	
	public String getChucuahieu();
	public void setChucuahieu(String chucuahieu);
	
	
	public String getDdkdId();
	public void setDdkdId(String ddkdId);
	
	public ResultSet getDdkdRs();
	public void setDdkdRs(ResultSet ddkdRs);
	
	public String getTbhId();
	public void setTbhId(String tbhId);
	
	public ResultSet getTbhRs();
	public void setTbhRs(ResultSet tbhRs);	
	
	public String getHopdong();
	public void setHopdong(String hopdong);
	
	public String getThanhtoanQuy();
	public void setThanhtoanQuy(String thanhtoanquy);
	
	public String getPT_Chietkhau();
	public void setPT_Chietkhau(String ptCK);
	
	public String getmauhd();
	public void setmauhd(String mauhd);
	
	public String getkhoId();
	public void setkhoId(String khoId);
	
	public ResultSet getKhoRs();
	public void setKhoRs(ResultSet khoRs);
	
	public void createKhoRS();
	
	public String getDtId();
	public void setDtId(String dtId);
	
	public String getTenKyHd(); 
	public void setTenKyHd(String TenKyHd);
	
	public ResultSet getDtRs();
	public void setDtRs(ResultSet dtRs);

	
	public String getNgaysinh();
	public void setNgaysinh(String ngaysinh);
	
	public String getMst();
	public void setMst(String mst);
	public String getCmnd();

	public void setCmnd(String cmnd);
	
	//String phuongxaId = "";
	public String getPhuongxaId() ;
	public void setPhuongxaId(String phuongxaId) ;
	//ResultSet phuongxaRs ;
	public ResultSet getPhuongxaRs() ;
	
	//String didong = "";
	public String getDidong();
	public void setDidong(String didong) ;
	//Sring thanhthinongthonId = "";
	public String getThanhthinongthonId() ;
	public void setThanhthinongthonId(String thanhthinongthonId);
	//ResultSet thanhthinongthonRs;
	public ResultSet getThanhthinongthonRs();
	//double songayno  = 0;
	public double getSongayno() ;
	public void setSongayno(double songayno) ;
	//double sotienno = 0;
	public double getSotienno() ;
	public void setSotienno(double sotienno) ;
	
	
	
	public String getKhachhangId() ;
	public void setKhachhangId(String khachhangId) ;
	public ResultSet getKhachhangRs();
	public void setKhachhangRs(ResultSet khachhangRs) ;

	public void setDiachigiaohang(String diachigiaohang);
	
	public String getDiachigiaohang();

	
	public ResultSet getNppRs();
	public void setNppRs(ResultSet nppRs);
	
	public String getHinhthucTT();
	public void setHinhthucTT(String value);
	public String getPhuongxa();
	public void setPhuongxa(String value);
	
	public String getNppBanChungId();
	public void setNppBanChungId(String nppBanChungId) ;
	public ResultSet getNppBanChungRs() ;
	public void setNppBanChungRs(ResultSet nppBanChungRs);
	
	public String getDiabanId();
	public void setDiabanId(String diabanId) ;
	public ResultSet getDiabanRs() ;
	
	public String getTenAnhDaiDien();
	public void setTenAnhDaiDien(String tenAnhDaiDien);
	public String XoaAnhDaiDien(String khId);
	
	public String getGioiTinh();
	public void setGioiTinh(String GioiTinh) ;
	public String getMacu();
	public void setMacu(String macu);

	public String getView();
	public void setView(String view);
	
	//Thêm trường vào dữ liệu nền
	public String getGhichu();
	public void setGhichu(String ghichu);
	public String getSonha();
	public void setSonha(String sonha);
	public String getTenduong();
	public void setTenduong(String tenduong);
	public String getNgsinh_con2();
	public void setNgsinh_con2(String ngsinh_con2);
	public String getNgsinh_con1();
	public void setNgsinh_con1(String ngsinh_con1);
	public String getCon2();
	public void setCon2(String con2);
	public String getCon1();
	public void setCon1(String con1);
	public String getNgsinh_vochong();
	public void setNgsinh_vochong(String ngsinh_vochong);
	public String getVochong();
	public void setVochong(String vochong);
	public String getApto();
	public void setApto(String apto);
	public String getNguoimuahang();
	public void setNguoimuahang(String nguoimuahang);
	public String getToado_lat();
	public void setToado_lat(String toado_lat);
	public String getToado_long();
	public void setToado_long(String toado_long);
	public String getTaitro();
	public void setTaitro(String taitro);
	public String getNgaytaitro();
	public void setNgaytaitro(String ngaytaitro);
	
	
	public int getCount() ;
	public void setCount(int count) ;
	public int getPageX() ;
	public int getPageY() ;
	public ResultSet getSanphamRs() ;
	public ResultSet getCtckRs() ;
	public String[] getSpIds() ;
	public String[] getChietkhauSp() ;
	public String[] getCtckIds() ;
	public void setPageX(int pageX) ;
	public void setPageY(int pageY) ;
	public void setSanphamRs(ResultSet sanphamRs) ;
	public void setCtckRs(ResultSet ctckRs);
	public void setSpIds(String[] spIds);
	public void setCtckIds(String[] ctckIds);
	public void setChietkhauSp(String[] chietkhauSp);
	public String getTencuahieu();
	public void setTencuahieu(String tencuahieu);
	public String getVitri();
	public void setVitri(String vitri);
	public String getMahd();
	public void setMahd(String mahd);
	public String getKhuvucId();
	public void setKhuvucId(String khuvucId);
	public ResultSet getKhuvucRs();
	public void setKhuvucRs(ResultSet khuvucRs);
	public String getMakhoSAP();
	public void setMakhoSAP(String makhoSAP);
	public ResultSet getKhoSAP();
	public void setKhoSAP(ResultSet khoSAP);
	public String getMoiquanhe();
	public void setMoiquanhe(String moiquanhe) ;
	public String getThoigiangap();
	public void setThoigiangap(String thoigiangap);
	public String getAnh(int stt);
	public void setAnh(String tenANh,int stt) ;
}
