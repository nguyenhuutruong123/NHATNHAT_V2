package geso.dms.erp.beans.lenhsanxuat;

import geso.dms.erp.beans.nhapkho.IKhu_Vitri;

import java.sql.ResultSet;
import java.util.List;

public interface IErpYeucaunguyenlieu
{
	public String getUserId();
	public void setUserId(String userId);
	
	public String getId();
	public void setId(String Id);

	public String getNgayyeucau();
	public void setNgayyeucau(String ngayyeucau);
	
	public String getLydoyeucau();
	public void setLydoyeucau(String lydoyeucau);
	
	public String getNhamayId();
	public void setNhamayId(String nhamayId);
	public ResultSet getNhamayRs();
	public void setNhamayRs(ResultSet nhamayRs);
	
	public String getNoidungxuat();
	public void setNoidungxuat(String NoidungxuatId);
	
	
	public String getKhoXuatId();
	public void setKhoXuatId(String khoxuattt);
	public ResultSet getKhoXuatRs();
	public void setKhoXuatRs(ResultSet khoxuatRs);
	
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
	
	public String getIschuyenNL();
	public void setIschuyenNL(String ischuyenNL);
	
	public List<IKhu_Vitri> getKhuList();
	public void setKhuList(List<IKhu_Vitri> khuList);

	public List<IKhu_Vitri> getVitriList();
	public void setVitriList(List<IKhu_Vitri> vitriList);
	
	public boolean createYcnl();
	public boolean updateYcnl();
	public boolean chotYcnl();
	public boolean chuyenNL();
	public void createRs();

	public void init();
	public void initYeucauNLPdf();
	public void initChuyenNLPdf();
	
	public void DBclose();
}
