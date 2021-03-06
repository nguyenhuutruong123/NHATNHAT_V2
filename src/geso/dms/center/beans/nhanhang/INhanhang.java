package geso.dms.center.beans.nhanhang;

import java.io.Serializable;
import java.sql.ResultSet;

public interface INhanhang extends Serializable {
	
	public String getMa() ;
	public void setMa(String ma) ;
	public String getId();

	public void setId(String id);
	
	public String getNhanhang();
	
	public void setNhanhang(String dvkd);
	
	public String getTrangthai();
	
	public void setTrangthai(String trangthai);
	
	public String getNgaytao();
	
	public void setNgaytao(String ngaytao);
	
	public String getNgaysua();
	
	public void setNgaysua(String ngaysua);
	
	public String getNguoitao();
	
	public void setNguoitao(String nguoitao);
	
	public String getNguoisua();
	
	public void setNguoisua(String nguoisua);
	
	public String getDvkdId();
	
	public void setDvkdId(String dvkdId);

	public String getDvkd();
	
	public void setDvkd(String dvkd);

	public String getMessage();

	public void setDvkdList(ResultSet dvkdlist);
	
	public ResultSet getDvkdList();  
	
	public void setMessage(String msg);
	
	public boolean saveNewNhanhang();
	
	public boolean UpdateNhanhang();
   
	public boolean AllowtoChangeDvkd();
	
	public void DBClose();
}