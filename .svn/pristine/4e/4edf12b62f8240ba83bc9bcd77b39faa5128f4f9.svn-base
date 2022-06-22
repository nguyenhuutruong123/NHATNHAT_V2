package geso.dms.distributor.beans.donhang;

import geso.dms.distributor.beans.ctkhuyenmai.ICtkhuyenmai;
import geso.dms.distributor.beans.ctkhuyenmai.imp.XLkhuyenmai;
import geso.dms.distributor.beans.ctkhuyenmai.imp.XLkhuyenmaiDonhangDXK;
import geso.dms.distributor.db.sql.dbutils;
import geso.dms.distributor.util.Utility;

import java.io.Serializable;
import java.sql.ResultSet;
import java.util.Hashtable;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public interface IDonhang extends Serializable
{
	public ResultSet getPhanboKM();
	public String getDieuchinh() ;
	public void setDieuchinh(String dieuchinh) ;
	public String getObj_ap_khuyen_mai();
	public void setObj_ap_khuyen_mai(String obj_ap_khuyen_mai);
	public String getDanh_sach_ctkm_Str() ;
	public void setDanh_sach_ctkm_Str(String danh_sach_ctkm_Str) ;
	public String getKqDuyet() ;
	public void setKqDuyet(String kqDuyet) ;
	//Cac thuoc tinh 
	public String getUserId();
	public void setUserId(String userId);
	public String getId();
	public void setId(String id);
		
	public String getNgaygiaodich();
	public void setNgaygiaodich(String ngaygiaodich);
	public String getNppTen();
	public void setNppTen(String nppTen);
	public String getDaidienkd();
	public void setDaidienkd(String daidienkd);			
	public String getTrangthai();
	public void setTrangthai(String trangthai);
	public String getNgaytao();
	public void setNgaytao(String ngaytao);
	public String getNguoitao();
	public void setNguoitao(String nguoitao);
	public String getNgaysua();
	public void setNgaysua(String ngaysua);	
	public String getNguoisua();
	public void setNguoisua(String nguoisua);
	public String getChietkhau();
	public void setChietkhau(String chietkhau);
	public String getVAT();
	public void setVAT(String vat);	
	public String getMessage();
	public void setMessage(String msg);
	public String getBgstId();
	public void setBgstId(String bgstId);
	public String getGhiChu();
	public void setGhiChu(String ghichu);
		
	public String getNppId();
	public void setNppId(String nppId);
	public String getSitecode();
	public void setSitecode(String sitecode);
	public String getLoaiNppId();
	public void setLoaiNppId(String nppId);
	
	//Cac phuong thuc Get, Set tra ve ResultSet tuong ung
	public String getKhId();
	public void setKhId(String khId);
	public ResultSet getKhList();
	public void setKhList(ResultSet khlist);
	public String getKhoId();
	public void setKhoId(String khoId);
	public ResultSet getKhoList();
	public void setKhoList(ResultSet kholist);
	
	public ResultSet getDdkdList();
	public void setDdkdList(ResultSet ddkdList);	
	public String getDdkdId();
	public void setDdkdId(String ddkdId);
	public String getGsbhId();
	public void setGsbhId(String gsbhId);
	
	public ResultSet getTbhList();
	public void setTbhList(ResultSet tbhList);	
	public String getTbhId();
	public void setTbhId(String tbhId);
	public void setMuctindung(String muctindung);
	public String getMuctindung();
	
	public String getnvgnId();
	public void setnvgnId(String nvgnId);
	public ResultSet getnvgnRs();
	public void setnvgnRs(ResultSet nvgnRs);
	
	public List<ISanpham> getSpList();
	public void setSpList(List<ISanpham> splist);
	
	//Tinh tong tien, chiet khau, tong tien VAT
	public String getTongtientruocVAT();
	public void setTongtientruocVAT(String tttvat);

	public float getTongtiensauCK();
	public void setTongtiensauCK(float ttsck);

	public String getTongtiensauVAT();
	public void setTongtiensauVAT(String ttsvat);
	
	public float getTongtienCKKM();
	public void setTongtienCKKM(float ttckkm);
	
	public float getTongtiensauCKKM();
	public void setTongtiensauCKKM(float ttsckkm);
	
	public Hashtable<String, Integer> getSpThieuList();
	public void setSpThieuList(Hashtable<String, Integer> spThieuList);
	
	//tra khuyen mai
	public Hashtable<String, Float> getScheme_Tongtien();
	public void setScheme_Tongtien(Hashtable<String, Float> scheme_tongtien);
	public Hashtable<String, Float> getScheme_Chietkhau();
	public void setScheme_Chietkhau(Hashtable<String, Float> scheme_chietkhau);
	public List<ISanpham> getScheme_SpList();
	public void setScheme_SpList(List<ISanpham> splist);
	
	public boolean CreateDh(HttpServletRequest request,List<ISanpham> splist);
	public boolean UpdateDh(HttpServletRequest request,List<ISanpham> splist, String action);
	
	public void init(HttpServletRequest request);
	public void createRS();
	//public void CreateSpList();
	public void DBclose();
	public boolean Muctindung();
	public ResultSet getgsbhs();
	
	public String getTongChietKhau();
	public void setTongChietKhau(String tck);
	public void CreateSpDisplay(HttpServletRequest request);
	public String getKHList();
	public String getSmartId(); 
	public void setSmartId(String smartId);
	public String getKhTen();
	public void setKhTen(String khTen);
	public String getChucuahieu();
	public void setChucuahieu(String chucuahieu);
	
	public boolean isAplaikhuyenmai(); //bien dung bat nguoi dung phai ap lai khuyen mai neu vao` sua don hang da co km
	public void setAplaikhuyenmai(boolean aplaikm);
	public boolean isCokhuyenmai(); //bien xet truong hop nguoi dung sau khi da ap lai khuyen mai, duoc khuyen mai, ma thay doi san pham ban --> bat ap lai km
	public void setCokhuyenmai(boolean cokm);
	public boolean isChuaApkhuyenmai(); //neu chua ap khuyen mai ma luu --> canh bao ap khuyen mai
	public void setIsChuaApkhuyenmai(boolean chuaApkm);
	public boolean isDamuahang(); //khach hang da mua hang, ---> canh bao
	public void setIsDamuahang(boolean damuahang);
	public boolean isDxkChuaChot(); //don hang da co trong phieuxuatkho, ma phieu xuat kho chua chot --> khong cho sua ngay
	public void setIsDxkChuaChot(boolean cokm);
	
	public void setNgayKs(String ngayks);
	public String getNgayKs();
	
	public void setCotrungbay(String cotrungbay);
	public String getCotrungbay();
	public int ApTrungBay(String dhId, String khId, String nppId, String ngaydh);
	public boolean isAplaitrungbay(); //bien dung bat nguoi dung phai ap lai trung bay neu vao` sua don hang da co trung bay
	public void setAplaitrugnbay(boolean aplaitb);
	
	public void setIsDonHangLe(String IsDonHangLe);
	public String getIsDonHangLe();
	
	public void setIsSampling(String IsSampling);
	public String getIsSampling();
	
	public void setIsDonquatang(String IsDonquatang);
	public String getIsDonquatang();
	
	public void setEnterKH(String enterKH);
	public String getEnterKH();
	
	//CHIET KHAU BO SUNG 
	public ResultSet getCkbosungList();
	public void setCkbosungList(ResultSet ckbsList);	
	public String getCkbosungIds();
	public void setCkbosungIds(String ckbsIds);
	public Hashtable<String, Float> getCkbosung_CK();
	public void setCkbosung_CK(Hashtable<String, Float> ckbs_ck);
	public String getCkbosung_VAT();
	public void setCkbosung_TVAT(String ckbs_vat);
	
	//Tra KM tich luy
	public String[] getTichLuy_Scheme();
	public void setTichLuy_Scheme(String[] tichluy_scheme);
	public String[] getTichLuy_Tongtien();
	public void setTichLuy_Tongtien(String[] tichluy_tongtien);
	public String[] getTichLuy_VAT();
	public void setTichLuy_TVAT(String[] tichluy_vat);
	public String[] getTichLuy_CoTheXoa();
	public void setTichLuy_CoTheXoa(String[] tichluy_xoa);
	
	//DON HANG DE NGHI
	public void setTieuchi(String tieuchi);
	public String getTieuchi();
	public void setDsTongNhomXanh(String dstNhomXanh);
	public String getDsTongNhomXanh();
	public void setDsTongNhomHHBG(String dstHHBG);
	public String getDsTongNhomHHBG();
	public void setDsTongNhomKHAC(String dstKHAC);
	public String getDsTongNhomKHAC();
	public void setDsTongNhomXanh_DeNghi(String dstDENGHI);
	public String getDsTongNhomXanh_DeNghi();
	public void setDsTongNhomHHBG_DeNghi(String dstHHBG_denghi);
	public String getDsTongNhomHHBG_DeNghi();
	
	//INIT SUBMIT
	public void initTichLuy();
	public void getTrakhuyenmai();
	
	public String createPth(String pxkId, dbutils db);
	public String getPxkId();
	public void setPxkId(String pxkId);
	public void createPxkId();
	public String DeleteDonHangDxk();
	
	public void setDonhangKhac(String donhangKhac);
	public String getDonhangKhac();
	
	public void setDenghitrackThang(String denghitrackThang);
	public String getDenghitrackThang();
	
	public String DuyetDonHang(String dhId, String vitriBAM, String userId, HttpServletRequest request);
	public String getChietkhaucu() ;
	
	public void setSotaikhoan(String sotaikhoan);
	public String getSotaikhoan() ;

	public void setChietkhaucu(String chietkhaucu);
	
	/** CHUYEN CAC HAM KM XUONG BEAN **/
	/** CAC HAM BEN DONHANGUPDATESVL **/
	public String CreateKhuyenmai(ICtkhuyenmai ctkm, String id, String nppId, String tungay, long tongGtridh, String khId, Hashtable<String, Integer> sp_sl, Hashtable<String, Float> sp_dg, dbutils db);	
	public String CreateKhuyenmaiDhDxk(List<ICtkhuyenmai> ctkmList, String id, String nppId, long tongGtridh, String khId, dbutils db);
	public String capNhatKM(String id, String nppId, String khId, String trangthai, dbutils db);
	public boolean CheckDonHangDKM(List<ISanpham> spList, String id, String khId, dbutils db);
	
	public XLkhuyenmai getXLkhuyenmai(String userId, String ngaygd, String khId, String donhangId, String IsDonHangLe);
	public XLkhuyenmaiDonhangDXK getXLkhuyenmaiDXK(String nppId, String id, String ngaygd, String[] ctkm, String khId);
	
	
	/** CAC HAM BEN KHUYENMAISVL **/
	public String saveTRAKHUYENMAI( IDonhang dhBean, HttpServletRequest request );
	
	//CHUYỂN TẤT CẢ CÁC BIẾN VÀO BEAN
	public String[][] initDATA(HttpServletRequest request, HttpSession session, Utility util);
	
	public  void Update_GiaTri_DonHang();
	public String getIngia() ;

	public void setIngia(String ingia);
	public String getChietkhauNNK();

	public void setChietkhauNNK(String chietkhauNNK);
	public String getHangkh();

	public void setHangkh(String hangkh);
	
	public ResultSet getRshangkh();

	public void setRshangkh(ResultSet rshangkh) ;

    public String getNgayhoadon() ;

	public void setNgayhoadon(String ngayhoadon);
	 public String getTiengiamtru();

	public void setTiengiamtru(String tiengiamtru);

	
	public double getDiemkm();
	public void setDiemkm(double diemkm);
	public String getCtkmDiemId();
	public void setCtkmDiemId(String ctkmDiemId);
	public ResultSet getCtkmDiemRs();
	public double getTienKmDiem();
	public void setTienKmDiem(double tienKmDiem);
	public String getKhDienThoai();
	public String getKhMaSoThue() ;
	
	public void setKhDienThoai(String khDienThoai) ;
	public void setKhMaSoThue(String khMaSoThue) ;
	
	public String getLoainpp(); 
	public void setLoainpp(String loaiNpp); 
	public String getMakho();
	public void setMakho(String makho);
	public String getMachinhanh() ;
	public void setMachinhanh(String machinhanh) ;
	public String getQuanlykho();
	public void setQuanlykho(String quanlykho);
	public dbutils getDb() ;
	
	public String MoChotDonHang (String dhId, String userId, dbutils db);
	public double getTien_KM_Sanpham(String spMa);
}
