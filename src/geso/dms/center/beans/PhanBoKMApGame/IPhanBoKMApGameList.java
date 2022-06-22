package geso.dms.center.beans.PhanBoKMApGame;

import geso.dms.center.util.IPhan_Trang;

import java.io.Serializable;
import java.sql.ResultSet;

import javax.servlet.http.HttpServletRequest;

public interface IPhanBoKMApGameList extends Serializable, IPhan_Trang {
	
	public HttpServletRequest getRequestObj() ;
	public void setRequestObj(HttpServletRequest request) ;
	
	public int getNum();
	public void setNum(int num);
	
	public String getUserId();
	public void setUserId(String userId);

	public String getDiengiai();
	public void setDiengiai(String diengiai);
	
	public String getTrangthai();
	public void setTrangthai(String trangthai);
	
	public String getvung();
	public void setvung(String vung);
	
	public String getnpp();
	public void setnpp(String npp);
	
	public String getkhuvuc();
	public void setkhuvuc(String khuvuc);
	
	public ResultSet getvungRs();
	public void setnppRs(ResultSet npprs);

	public ResultSet getnppRs();
	public void setvungRs(ResultSet vungrs);

	public ResultSet getkhuvucRs() ;
	public void setkhuvucRs(ResultSet khuvucrs);
	
	public String getTungay() ;
	public void setTungay(String tungay);

	public String getDenngay() ;
	public void setDenngay(String denngay) ;
	
	public ResultSet getCtkmList() ;
	public void setCtkmList(ResultSet ctkmlist);
	
	public void init(String search);

	public void DBclose() ;

	public int getCurrentPage();
	public void setCurrentPage(int current);

	public int[] getListPages();
	public void setListPages(int[] listPages);

	public int getLastPage();

	public int[] getNewPagesList(String action, int num, int currentPage, int theLastPage, String[] listPage);

	public String getMessage();
	public void setMessage(String msg);
}