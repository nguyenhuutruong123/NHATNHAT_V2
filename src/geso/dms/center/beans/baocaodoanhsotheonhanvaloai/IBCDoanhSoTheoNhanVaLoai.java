package geso.dms.center.beans.baocaodoanhsotheonhanvaloai;

import geso.dms.center.db.sql.dbutils;
import geso.dms.center.util.IPhan_Trang;

import java.io.Serializable;
import java.sql.ResultSet;

public interface IBCDoanhSoTheoNhanVaLoai extends IPhan_Trang, Serializable {

	public String[] getMaTen();
	public void setMaTen(String[] maTen);
	public long[][] getDoanhSo();
	public void setDoanhSo(long[][] doanhSo);
	public String getThangTu();
	public void setThangTu(String thangTu);
	public String getThangDen();
	public void setThangDen(String thangDen);
	public String getNam();
	public void setNam(String nam);
	public ResultSet getASM();
	public void setASM(ResultSet aSM);
	public ResultSet getRSM();
	public void setRSM(ResultSet rSM);
	public String getMessage();
	public void setMessage(String message);
	public dbutils getDb();
	public void setDb(dbutils db);
	public ResultSet getRsKenh();
	public void setRsKenh(ResultSet rsKenh);
	public ResultSet getRsBrand();
	public void setRsBrand(ResultSet rsBrand);
	public String getUserID();
	public void setUserID(String userID);
	
	public void createASM();
	public void createRSM();
	
	public void dbClose();
	public String getIdKenh();
	public void setIdKenh(String idKenh);
	public String getIdBrand();
	public void setIdBrand(String idBrand);
	public String getIdASM();
	public void setIdASM(String idASM);
	public String getIdRSM();
	public void setIdRSM(String idRSM);
	public void createKenh();
	public void createBrand();
	
	public String[] getMangThang();
	public void setMangThang(String[] mangThang);
	public void init();
	public int getSocot();
	public void setSocot(int socot);
	public int getSodong();
	public void setSodong(int sodong);
	public String getNamDen();
	public void setNamDen(String namDen);
}
