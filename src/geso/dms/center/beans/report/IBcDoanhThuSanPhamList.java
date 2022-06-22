package geso.dms.center.beans.report;

import geso.dms.center.util.IPhan_Trang;

import java.io.Serializable;
import java.sql.ResultSet;
import java.util.ArrayList;

public interface IBcDoanhThuSanPhamList extends IPhan_Trang, Serializable
{
	public String getTuNgay();
	public void setTuNgay(String denngay);
	
	public String getDenNgay();
	public void setDenNgay(String denngay);
	
	public String getNppId();
	public void setNppId(String nppId);
	
	public ResultSet getNppRs();
	public void setNppRs(ResultSet nppRs);
	
	public String getUserId();
	public void setUserId(String userId);
	
	public String getSpId();
	public void setSpId(String spId);
	
	public String getDdkdId();
	public void setDdkdId(String ddkdId);
	
	public String getKhId();
	public void setKhId(String khId);
	
	public ResultSet getSpRs();
	public void setSpRs(ResultSet spRs);
	
	public ResultSet getKhRs();
	public void setKhRs(ResultSet khRs);
	
	public ResultSet getDdkdRs();
	public void setDdkdRs(ResultSet ddkdRs);
	
	public ResultSet getHoadonRs();
	public void setHoadonRs(ResultSet hdRs);
	
	public String getView();
	public void setView(String view);
	
	public void closeDB();

	public void createRs();
	
	public String getMsg();
	public void setMsg(String msg);
	
	public String getKbhId();
	public void setKbhId(String kbhId);
	
	public ResultSet getKbhRs();
	public void setKbhRs(ResultSet kbhRs);	

	public void init(String search);
	public void init2(String search);
	public void xuatexcelNPP(String search);
	public void xuatexcelTT(String search);
	
	public String getQueryHd();
	public void setQueryHd(String query);
	
	public void setTotal_Query(String searchquery);
	public ResultSet getTotalRs();
	public void setTotalRs(ResultSet totalRs);
	
	public String getVungId();
	public void setVungId(String vungId);
	
	public ResultSet getVungRs();
	public void setVungRs(ResultSet vungRs);
	
	public String getKvId();
	public void setKvId(String kvId);
	
	public ResultSet getKvRs();
	public void setKvRs(ResultSet kvRs);
	
	public String getTtId();
	public void setTtId(String ttId);
	
	public ResultSet getTtRs();
	public void setTtRs(ResultSet ttRs);
	
	public String getNhomId();
	public void setNhomId(String nhomId);
	
	public ResultSet getNhomRs();
	public void setNhomRs(ResultSet nhomRs);
	
	public String getLoaiHoaDon();
	public void setLoaiHoaDon(String loaiHoaDon);
	
	public String get_Action();
	public void set_Action(String action);
	
	public int getCurrentPage();
	public void setCurrentPage(int current);
	
	public int[] getListPages();
	public void setListPages(int[] listPages);
	
	public int getxemtheo();
	public void setxemtheo(int so);
	
	public String getFlagnpp();
	public void setFlagnpp(String flagnpp);
	public String getidnpp(String userid);
	public String getLaynk();
	public void setLaynk(String laynk) ;
	public String getThangbd() ;
	public void setThangbd(String thangbd);
	public String getThangkt();
	public void setThangkt(String thangkt);
	public String getNambd();
	public void setNambd(String nambd) ;
	public String getNamkt() ;
	public void setNamkt(String namkt);
	public String getDSKHT() ;
	public void setDSKHT(String dSKHT);
	public void init3(String search);
	public ArrayList<String> getArrname() ;
	public void setArrname(ArrayList<String> arrname) ;
	public ArrayList<String> getArrname_colum();
	public void setArrname_colum(ArrayList<String> arrname_colum);
	public String getDSKHT_client() ;
	public void setDSKHT_client(String dSKHT_client);
}
