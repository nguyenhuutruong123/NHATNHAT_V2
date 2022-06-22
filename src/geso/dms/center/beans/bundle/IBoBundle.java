package  geso.dms.center.beans.bundle;

import geso.dms.center.db.sql.dbutils;

import java.io.Serializable;
import java.sql.ResultSet;
import java.util.Hashtable;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

public interface IBoBundle extends Serializable
{
	//Cac thuoc tinh 
	public String getUserId();
	public void setUserId(String userId);
	public String getId();
	public void setId(String id);
		
	public String getNgaygiaodich();
	public void setNgaygiaodich(String ngaygiaodich);		
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
	public String getMessage();
	public void setMessage(String msg);

	public String getGhiChu();
	public void setGhiChu(String ghichu);
		
	public String getNppId();
	public void setNppId(String nppId);
	
	

	public String getKhoId();
	public void setKhoId(String khoId);
	public ResultSet getKhoList();
	public void setKhoList(ResultSet kholist);
	public String getKbhId() ;
	public void setKbhId(String kbhId);
	
	public List<ISanpham> getSpList();
	public void setSpList(List<ISanpham> splist);

	
	public Hashtable<String, Integer> getSpThieuList();
	public void setSpThieuList(Hashtable<String, Integer> spThieuList);

	public boolean CreateDh(List<ISanpham> splist);
	public boolean UpdateDh(List<ISanpham> splist, String action);

	public void init();
	public void createRS();
	
	public void DBclose();
	

	
	
	public ResultSet getNppRs();
	public void setNppRs(ResultSet nppRs);
	//public String capNhatKMChietkhau(String id, dbutils db);	
	
	public ResultSet getKbhRs();
	public void setKbhRs(ResultSet kbhRs);
	
	public ResultSet getDvkdRs();
	public dbutils getDb();
	public String getDvkdId();
	public void setDvkdId(String dvkdId);
	
	public String getSolo();
	public void setSolo(String solo);
	public double getSoluong();
	public void setSoluong(double soluong);
	public String getNgayhethan();
	public void setNgayhethan(String ngayhethan);
	public String getSpId();
	public void setSpId(String spId);
	public ResultSet getSpRs();
}
