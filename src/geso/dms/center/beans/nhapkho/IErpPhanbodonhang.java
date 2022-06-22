package geso.dms.center.beans.nhapkho;

import java.sql.ResultSet;
import java.util.Hashtable;

public interface IErpPhanbodonhang
{
	public String getUserId();
	public void setUserId(String userId);
	
	public String getId();
	public void setId(String Id);

	public String getTungay();
	public void setTungay(String tungay);
	public String getDenngay();
	public void setDenngay(String denngay);

	public String getGhichu();
	public void setGhichu(String ghichu);
	
	public String getKhoNhapId();
	public void setKhoNhapId(String khonhaptt);
	public ResultSet getKhoNhapRs();
	public void setKhoNHapRs(ResultSet khonhapRs);
	
	public ResultSet getDvtRs();
	public void setDvtRs(ResultSet dvtRs);
	
	public String[] getSpId();
	public void setSpId(String[] spId);
	public String[] getSpMa();
	public void setSpMa(String[] spMa);
	public String[] getSpTen();
	public void setSpTen(String[] spTen);
	public String[] getSpDonvi();
	public void setSpDonvi(String[] spDonvi);
	public String[] getSpSoluong();
	public void setSpSoluong(String[] spSoluong);
	public String[] getSpGianhap();
	public void setSpGianhap(String[] spGianhap);
	public String[] getSpSolo();
	public void setSpSolo(String[] spSolo);
	public String[] getSpHansudung();
	public void setSpHansudung(String[] spHansudung);
	public String[] getSpNgaysanxuat();
	public void setSpNgaysanxuat(String[] spNgaysanxuat);
	public String[] getSpNgayhethan();
	public void setSpNgayhethan(String[] spNgayhethan);
	
	public Hashtable<String, String> getSp_Chitiet();
	public void setSp_Chitiet(Hashtable<String, String> sp_chitiet);
	
	public String getMsg();
	public void setMsg(String msg);
	
	public boolean createNK();
	public boolean updateNK();
	public boolean updateCHOPB();

	public String getDateTime();
	
	public void createRs();
	public void init();
	public void initChoPhanBo();
	
	public String getXuatcho();
	public void setXuatcho(String xuatcho);
	
	public String getKhId();
	public void setKhId(String khId);
	public ResultSet getKhRs();
	public void setKhRs(ResultSet khRs);
	
	
	public void DBclose();
	
}
