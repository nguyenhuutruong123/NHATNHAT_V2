package geso.dms.erp.beans.lenhsanxuat;

import geso.dms.erp.beans.danhmucvattu.IDanhmucvattu_SP;

import java.sql.ResultSet;   
import java.util.List;

public interface IErpLenhsanxuat 
{
	public String getCongtyId();
	public void setCongtyId(String congtyId);
	
	public String getUserId();
	public void setUserId(String userId);
	
	public String getId();
	public void setId(String Id);

	public String getNgaytao();
	public void setNgaytao(String ngaytao);
	public String getNgaydukien();
	public void setNgaydukien(String ngaydk);
	
	public String getSpId();
	public void setSpId(String spId);
	public ResultSet getSpRs();
	public void setSpRs(ResultSet spRs);
	
	public String getKbsxId();
	public void setKbsxId(String kbsxId);
	public ResultSet getKbsxRs();
	public void setKbsxRs(ResultSet kbsxRs);
	
	public String getSoluong();
	public void setSoluong(String soluong);
	
	public String getPlaintOrder();
	public void setPlaintOrder(String PO);
	
	public String getKhottId();
	public void setKhottId(String khott);
	public ResultSet getKhoTTRs();
	public void setKhoTTRs(ResultSet khottRs);
	
	public String getNhamayId();
	public void setNhamayId(String nhamayId);
	public ResultSet getNhamayRs();
	public void setNhamayRs(ResultSet nhamayRs);
	
	
	public String getBomId();
	public void setBomId(String BomId);
	public ResultSet getrsBom();
	public void setrsBom(ResultSet rsBom);
	
	
	public ResultSet getChitietNLRs();
	public void setChitietNLRs(ResultSet nlRs);
	
	public String getTuan(String ngay);
	
	public String getSoluongchuan();
	public void setSoluongchuan(String slgchuan);
	public String getChophepTT();
	public void setChophepTT(String chophepTT);
	
	public void setListDanhMuc(List<IDanhmucvattu_SP> list);
	public List<IDanhmucvattu_SP> getListDanhMuc();
	
	public String getMsg();
	public void setMsg(String msg);
	public String getTrangthai();
	public void setTrangthai(String trangthai);
	
	public String getViewBom();
	public void setViewBom(String viewBom);
	
	public boolean createLsx();
	public boolean updateLsx();
	public boolean tieuhaoLsx(String khoTieuhao_fk);
	public boolean checkTieuHaoLsx();
	
	public void createRs();
	public void changeKbsx();
	
	public void init();
	public void initDisplay();
	public ResultSet getRsNhapKho();
	public void setRsNhapKho(ResultSet Rs);
	
	public String getNhapKhoId();
	public void setNhapkhoId(String nhapkhoId);
	
	public String getTieuHaoId();
	public void setTieuHaoId(String TieuhaoId);
	
	public String getSoLuongNhapKho();
	public void setSoLuongNhapKho(String soluongnhapkho);
	
 
	public String getKhoId();
	public void setKhoId(String KhoId);
	
	public String getNgaytieuhao();
	public void setNgaytieuhao(String Ngaytieuhao);
	
	public ResultSet getKhoRs();
	public void setKhoRs(ResultSet RsKho);
	
	
	public void DBclose();
	public String getKho();
	public String getSanPham();
}
