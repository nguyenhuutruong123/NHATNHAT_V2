package geso.dms.erp.beans.lenhsanxuat;

import geso.dms.erp.beans.nhapkho.IKhu_Vitri;

import java.sql.ResultSet;
import java.util.List;

public interface IErpChuyenkhoSX
{
	public String getUserId();
	public void setUserId(String userId);
	
	public String getId();
	public void setId(String Id);

	public String getKyhieu();
	public void setKyhieu(String Kyhieu);

	public String getSochungtuin();
	public void setSochungtuin(String Sochungtuin);

	public String getMuahangid();
	public void setMuahangId(String muhangid);
	
	
	public String gettask();
	public void settask(String task);
	
	public String getNgaydenghi();
	public void setNgaydenghi(String ngayyeucau);
	
	
	public String getNgayyeucau();
	public void setNgayyeucau(String ngayyeucau);
	
	public String getLydoyeucau();
	public void setLydoyeucau(String lydoyeucau);
	
	public String getLenhdieudong();
	public void setLenhdieudong(String lenhdieudong);
	
	public String getNgaydieudong();
	public void setNgaydieudong(String ngaydieudong);
	
	public String getCua();
	public void setCua(String cua);
	
	public String getVeviec();
	public void setVeviec(String veviec);
	
	public String getSohopdong();
	public void setSohopdong(String sohopdong);
	
	public String getNguoichuyen();
	public void setNguoichuyen(String nguoichuyen);
	
	public String getPhuongtien();
	public void setPhuongtien(String phuongtien);
	
	
	public String getNdxId();
	public void setNdxId(String ndxId);
	public ResultSet getNdxList();
	public void setNdxList(ResultSet ndxList);
	
	
	public String getNhanVienId();
	public void setNhanVienId(String NvId);
	public ResultSet getNhanvienRs();
	public void setNhanvienRs(ResultSet rs);
	
	
	public String getPhongBanId();
	public void setPhongBanId(String PhongbanId);
	public ResultSet getPhongbanRs();
	public void setPhongbanRs(ResultSet rs);
	
	
	
	public String getKhoXuatId();
	public void setKhoXuatId(String khoxuattt);
	public ResultSet getKhoXuatRs();
	public void setKhoXuatRs(ResultSet khoxuatRs);
	
	// đối tượng
	public String getKhachhangId();
	public void setKhachhangId(String KhId);
	public ResultSet getKhachhangRs();
	public void setKhachhangRs(ResultSet rs);
	
	// khách hàng ký gửi
	
	public String getKhachhangKyguiId();
	public void setKhachhangKyguiId(String KhId);
	public ResultSet getKhachhangKyguiRs();
	public void setKhachhangKyguiRs(ResultSet rs);
	
	
	public String getNhanvienKyguiId();
	public void setNhanvienKyguiId(String nvid);
	public ResultSet getNhanVienKyguiRs();
	public void setNhanVienKyguiRs(ResultSet rs);
	
 
	public String getKhoNhapId();
	public void setKhoNhapId(String khonhaptt);
	public ResultSet getKhoNhapRs();
	public void setKhoNHapRs(ResultSet khonhapRs);
	
	public String getLsxIds();
	public void setLsxIds(String lsxIds);
	public ResultSet getLsxRs();
	public void setLsxRs(ResultSet lsxRs);
	
	public List<IYeucau> getSpList();
	public void setSpList(List<IYeucau> spList);
	
	public List<IYeucau> getSpChoNhapList();
	public void setSpChoNhapList(List<IYeucau> spchoNhapList);
	
	public String getMsg();
	public void setMsg(String msg);
	public String getTrangthai();
	public void setTrangthai(String trangthai);
	public String getPoLsx();
	
	public void setPoLsx(String PoLsx);
	
	
 
	
	public String getLoaidoituong();
	public void setLoaidoituong(String Loaidoituong);
	
	public String getIsnhanHang();
	public void setIsnhanHang(String ischuyenKho);
	
	public List<IKhu_Vitri> getKhuList();
	public void setKhuList(List<IKhu_Vitri> khuList);

	public List<IKhu_Vitri> getVitriList();
	public void setVitriList(List<IKhu_Vitri> vitriList);
	
	public String getTrangthaiSP();
	public void setTrangthaiSP(String trangthaisp);
	
	public boolean createCK();
	public boolean updateCK();
	public boolean nhanHang();
	public boolean xuatHang();
	
	public String getLoaiChuyenKyGui();
	public void setLoaiChuyenKyGui(String loaichuyenkygui);
	
	public String getLoaiNhanKyGui();
	public void setLoaiNhanKyGui(String loainhankygui);
	
	
	public String getCheckNhanHang();
	public void setCheckNhanHang(String CheckNhanHang);
	
	//Nếu nội dung xuất, nhận là ký gửi: loại chuyển là 0 thì nccId ở đây sẽ lưu nppId, nếu loại là 1 thì lưu nhanvienId
	public String getNccChuyenIds(); 
	public void setNccChuyenIds(String nccChuyenIds);
	public ResultSet getNccChuyenRs();
	public void setNccChuyenRs(ResultSet nccChuyenRs);
	
	public String getNccNhanIds();
	public void setNccNhanIds(String nccNhanIds);
	public ResultSet getNccNhanRs();
	public void setNccNhanRs(ResultSet nccNhanRs);

	public String getLoaiKhonhan();
	public void setLoaiKhonhan(String LoaiKhonhan);
	
	
	public boolean chuyenNL();
	public void createRs();

	public void init();
	public void initNhanhang();
	public void initXuathang();
	public void initView();
	
	public void initYeucauNLPdf();
	public void initChuyenNLPdf();
	
	public void DBclose();
	public void InitNew();
	public String CreateChuyenKho(String id);
	public void initViewShop();
	
	//28/12/2015 tổng thể tích và tổng khối lượng
	public Double getTongthetich();
	public void setTongthetich(Double tongthetich);
	public Double getTongkhoiluong();
	public void setTongkhoiluong(Double tongkhoiluong);
	
	
}
