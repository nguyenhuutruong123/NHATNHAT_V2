package geso.dms.center.beans.nhaphanphoi;

import java.io.Serializable;
import java.sql.ResultSet;

import javax.servlet.http.HttpServletRequest;

public interface INhaphanphoi extends Serializable
{
	
	public String getQuanlySellOut() ;
	public void setQuanlySellOut(String quanlySellOut) ;
	public String getTyleOutIn() ;
	public void setTyleOutIn(String tyleOutIn);
	
	public String getUserId();
	public void setUserId(String userId);
	
	public String getId();
	public void setId(String id);
	
	public String getTen();
	public void setTen(String ten);
	
	public String getDiachi();
	public void setDiachi(String diachi);
	
	public String getTpId(); 
	public void setTpId(String tpId);
	
	public String getQhId(); 
	public void setQhId(String qhId);
	
	public String getSodienthoai();
	public void setSodienthoai(String sodienthoai);
	
	public String getTrangthai();
	public void setTrangthai(String trangthai);
	
	public String getMessage();
	public void setMessage(String msg);
	
	public ResultSet getTp(); 
	public void setTp(ResultSet tp);
	
	public ResultSet getQh(); 
	public void setQh(ResultSet qh);
	
	public ResultSet getKhuvuc();
	public void setKhuvuc(ResultSet khuvuc);
	
	public String getKvId();
	public void setKvId(String kvId);
		
	public void setMaSAP(String ma);
	public String getMaSAP();

	public String getPriSec(); 
	public void setPriSec(String prisec); 
	
	public boolean CreateNpp(HttpServletRequest request);
	public boolean UpdateNpp(HttpServletRequest request);
	public void createRS();
	public void DBclose();
	
	public void  setDiaChiXuatHoaDon(String diachixhd);
	public String getDiaChiXuatHoaDon();
	
	public void setMaSoThue(String masothue);
	public String getmaSoThue();
	
	public void setKhoTT(String khott);
	public String getKhoTT();
	
	public ResultSet getrs_khott();
	public ResultSet getrs_gsbh();
	public String getGsbhId();
	public void setGsbhId(String gbbhId);
	
	public ResultSet getrs_nvbh();
	public String getNvbhId();
	public void setNvbhId(String nvbhId);

	public void setTenNguoiDaiDien(String nguoidaidien);
	public String getTenNguoiDaiDien();
	
	public void setFAX(String fax);
	public String getFAX();
	
	public void setEmail(String email);
	public String getEmail();
	
	public void setHinhThucThanhToan(String httt);
	public String getHinhThucThanhToan();
	
	public void setSoTK(String sotk);
	public String getSoTK();
	
	public void setKyhieuHD(String kyhieuHD);
	public String getKyhieuHD();
	
	public void setSoHDTu(String SoHDTu);
	public String getSoHDTu();
	public void setSoHDDen(String SoHDDen);
	public String getSoHDDen();
	
	public void setNganHang(String nganhang);
	public String getNganHang();
	
	
	public void setLoaiNPP(String loaiNPP);
	public String getLoaiNPP();
	
	public ResultSet getTructhuoc(); 
	public void setTructhuoc(ResultSet tructhuocRs);
	public void setTructhuocId(String tructhuoc);
	public String getTructhuocId();
	
	public ResultSet getCapCn();
	public void setCapCn(ResultSet capcnRs);
	
	public void setCapCnId(String idcapcn);
	public String getCapCnId();

	
	
	public void setIsKhachhang(String isKH);
	public String getIsKhachhang();
	
	public void setChietkhau(String chietkhau);
	public String getChietkhau();
	
	public String getXuattaikho();
	public void setXuattaikho(String xuattaikho);
	
	public String getTuxuatkhoETC();
	public void setTuxuatkhoETC(String tuxuatETC);
	
	public String getTutaohoadonOTC();
	public void setTutaohoadonOTC(String tutaohoadonOTC);
	
	public String getDungchungkenh();
	public void setDungchungkenh(String dungchungkenh);
	
	
	public String getMaKho();
	public void setMaKho(String makho);
	
	public ResultSet getLoaiCNRs(); 
	public void setLoaiCNRs(ResultSet loaicnRs);
	public String getLoaiCN();
	public void setLoaiCN(String loaicn);
	
	public String getTenKyHd(); 
	public void setTenKyHd(String TenKyHd);
	
	public String getMaNX();
	public void setMaNX(String manx);
	
	public void setHanmucno(String hanmucno);
	public String getHanmucno();
	
	public void setSongayno(String songayno);
	public String getSongayno();
	public String getCMTND() ;

	public void setCMTND(String cMTND);

	
	/*
	 * 2.	Doanh thu của tháng phải đạt theo tiêu chí trong dữ liệu nền CN/Đối tác
		VD: Đối với Hà Nội doanh thu trong tháng phải > 1tr thì tháng sau mới trả ck tháng bằng biên bản bù trừ công nợ.
	 * 
	 */
	public String getHanmucdoanhthu();
	public void setHanmucdoanhthu(String hanmucdoanhthu);
	
	public String getThuKho();
	public void setThuKho(String thukho);
	public ResultSet getLoaiNppRs();
	
	

	public void setTonkhoAntoan(String tonkhoAT);
	public String getTonkhoAntoan();
	public void setTansuatDathang(String tansuatDH);
	public String getTansuatDathang();
	
	public String getTonkhotoida();
	public void setTonkhotoida(String tonkhotoida);
	public String getB2b();

	public void setB2b(String b2b);
	
	public void setPTChietkhau(String PTChietkhau);
	public String getPTChietkhau();
	
	
///////////////////// bổ sung Kh ETC
	public String getChucuahieu();
	public void setChucuahieu(String chucuahieu) ;
	public String getTencuahieu() ;
	public void setTencuahieu(String tencuahieu);
	
	public String getThanhthinongthonId() ;
	public void setThanhthinongthonId(String thanhthinongthonId);
	public ResultSet getThanhthinongthonRs();
	
	public ResultSet getHangcuahang();
	public void setHangcuahang(ResultSet hangcuahang);
	public ResultSet getKenhbanhang();
	public void setKenhbanhang(ResultSet kenhbanhang);
	public String getKbhId();
	public void setKbhId(String kbhId);
	public ResultSet getVtcuahang() ;
	public void setVtcuahang(ResultSet vtcuahang) ;
	
	public ResultSet getLoaicuahang();
	public void setLoaicuahang(ResultSet loaicuahang) ;
	public String getPhuongxa();
	public void setPhuongxa(String phuongxa);
	public String getDidong() ;
	public void setDidong(String didong) ;
	public String getXuatkhau();
	public void setXuatkhau(String xuatkhau);
	public String getHopdong();
	public void setHopdong(String hopdong);
	public String getNgaysinh();
	public void setNgaysinh(String ngaysinh);
	public String getHchId() ;
	public void setHchId(String hchId) ;
	public double getSotienno() ;
	public void setSotienno(double sotienno) ;
	public String getVtchId() ;
	public void setVtId(String vtchId) ;
	public String getLchId() ;
	public void setLchId(String lchId);
	public String getLoaicongno();
	public void setLoaicongno(String loaicongno);
	
	//Thêm địa bàn
	public String getDiabanId();
	public void setDiabanId(String diabanId);
	public ResultSet getDiabanRs();
	public void setDiabanRs(ResultSet diabanRs);
	public void CreateDiabanRs();
}
