package geso.dms.distributor.beans.donhang;

import geso.dms.center.util.IPhan_Trang;
import geso.dms.distributor.db.sql.dbutils;

import java.io.Serializable;
import java.sql.ResultSet;
import java.util.List;

public interface IDonhangList extends IPhan_Trang, Serializable
{	
	public String getUserId(); 
	public void setUserId(String userId);
	
	public ResultSet getDaidienkd();
	public void setDaidienkd(ResultSet daidienkd);	
	public String getDdkdId();
	public void setDdkdId(String ddkdId);
	
	public String getTrangthai();
	public void setTrangthai(String trangthai);
	
	public String getMafast();
	public void setMafast(String mafast);
	
	public String getTungay();
	public void setTungay(String tungay);
	public String getDenngay();
	public void setDenngay(String denngay);
	
	public String getSohoadon();
	public void setSohoadon(String sohoadon);
	public String getKhachhang();
	public void setKhachhang(String khachhang);
	
	public String getNppId();
	public void setNppId(String nppId);
	public String getNppTen();
	public void setNppTen(String nppTen);
	public String getSitecode();
	public void setSitecode(String sitecode);

	public ResultSet getDonhangRs();
	public void setDonhangRs(ResultSet dhRs);
	
	public void init(String search);
	public void DBclose();
	
	public String getMsg();
	public void setMsg(String msg);
	
	public int getCurrentPage();
	public void setCurrentPage(int current);
	
	public int[] getListPages();
	public void setListPages(int[] listPages);
	public int getLastPage();
	public void getSumBySearch(String sumqr);
	public double getTongTruoc();
	public double getTongCK();
	public double getTongSau();
	public boolean getIsSearch();
	public void setIsSearch(boolean search);
	
	public String getnvgnId();
	public void setnvgnId(String nvgnId);
	public ResultSet getnvgnRs();
	public void setnvgnRs(ResultSet nvgnRs);
	
	public String getQhId();
	public void setQhId(String qhId);
	
	public ResultSet getQhRs();
	public void setQhRs(ResultSet qhRs);
	
	public String getTtId();
	public void setTtId(String ttId);
	
	public ResultSet getTtRs();
	public void setTtRs(ResultSet ttRs);
	public String getNgaygiotao();

	public void setNgaygiotao(String ngaygiotao);
	
	public String getSort();
	public void setSort(String sort);
	public String getDienthoai();
	public void setDienthoai(String dienthoai);
	public String getIsPhanBoNVGN();
	public void setIsPhanBoNVGN(String isPhanBoNVGN);
	public String getPBdonhang_fk();
	public void setPBdonhang_fk(String pBdonhang_fk);
	public String[] getArrPBdonhang_fk();
	public void setArrPBdonhang_fk(String[] arrPBdonhang_fk);
	public String getTrangthaipb();
	public void setTrangthaipb(String trangthaipb);
	public String getPbid();
	public void setPbid(String pbid);
	public dbutils getDb();
}

