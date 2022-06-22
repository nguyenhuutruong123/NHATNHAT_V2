package geso.dms.center.beans.bcchartdoanhthursm;

import geso.dms.center.util.IPhan_Trang;

import java.io.Serializable;
import java.sql.ResultSet;

public interface IBcchartdoanhthursm extends IPhan_Trang, Serializable
{
	public String getUserId();
	public void setUserId(String userId);

	public String getId();
	public void setId(String Id);
	public String getThang();
	public void setThang(String thang);
	public String getNam();
	public void setnam(String nam);
	public String getMsg();
	public void setMsg(String msg);
	public String[] getArrTenNSP();
	public String[] getArrIDNSP();
	// tháng có dữ liệu
	public String[] getThangrms2DL();
	
	// tháng có dữ liệu trung bình
	public String[] getThangTBDL();
	
	public Long[] getSoKHRsm1();
	public Long[] getSoKHRsm2();
	
	public Long[] getDoanhthuTB();
	public Long[] getDoanhthuRsm1();
	public Long[] getDoanhthuRsm2();
	
	public ResultSet getRs();
	public ResultSet getRms2Rs();
	public ResultSet getDoanhthuTBRs();
	public ResultSet getDoanhthursm1Rs();
	public ResultSet getDoanhthursm2Rs();
	public void init();
	
	public ResultSet getRsmRs();
	public void setRsmRs(ResultSet RmsRs);
	public String getRsmId1();
	public void setRsmId1(String RmsId1);
	
	public String getRsmId2();
	public void setRsmId2(String RmsId2);
	
	public void DbClose();
}
