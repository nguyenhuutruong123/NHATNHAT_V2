package geso.dms.center.beans.kehoachpg;

import geso.dms.center.util.IPhanTrang;
import geso.dms.center.util.IPhan_Trang;

import java.io.Serializable;
import java.sql.ResultSet;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.oreilly.servlet.MultipartRequest;

public interface IKehoachPGList extends Serializable, IPhan_Trang
{	
	
	HttpServletRequest getRequestObj();
	void setRequestObj(HttpServletRequest request);
	
	public String getUserId();
	public void setUserId(String userId);
	
	
	public String getTen();
	public void setTen(String ten);
	public String getTrangthai();
	public void setTrangthai(String trangthai);
	
	public ResultSet getDdkdList();
	public void setDdkdList(ResultSet ddkdlist);
	
	public ResultSet getNhaphanphoi();
	public void setNhaphanphoi(ResultSet nhaphanphoi);
	public String getNppId();
	
	public void setMsg(String Msg);
	public String getMsg();
	public void setNppId(String nppId);
	
	public int getCurrentPage();
	public void setCurrentPage(int current);
	
	public int[] getListPages();
	public void setListPages(int[] listPages);
	public void init(String contentType,HttpServletRequest request,MultipartRequest multi) ;
	public void DbClose();
	public String getView();
	public void setView(String view);
	public List<Object> getDataSearch() ;
	public void setDataSearch(List<Object> dataSearch);
}
