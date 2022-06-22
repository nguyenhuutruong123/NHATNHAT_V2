package geso.dms.center.beans.duyettratrungbay;

import java.io.Serializable;
import java.sql.ResultSet;
import java.util.Hashtable;

import javax.servlet.http.HttpServletRequest;

public interface IDuyetAnhTrungBay extends Serializable
{
	public String getUserId();
	public void setUserId(String UserId);

	public String getSchemeId();
	public void setSchemeId(String schemeId);

	public String getScheme();
	public void setScheme(String scheme);

	public int getSolantt();
	public void setSolantt(int solantt);

	public int getLantt();
	public void setLantt(int lantt);

	public String getNppId();
	public void setNppId(String nppId);
	
	public String getDdkdId();
	public void setDdkdId(String value);

	public ResultSet getKh();

	public String[] getKhIds();
	public void setKhIds(String[] khIds);

	public String getTrangthai();

	public String getMessage();
	public void setMessage(String msg);

	public ResultSet getSchemeRS();

	public ResultSet getVung();

	public String getVungId();
	public void setVungId(String vungId);

	public ResultSet getKv();

	public String getKvId();
	public void setKvId(String kvId);

	public ResultSet getNpp();
	
	public ResultSet getDdkdRs();
	
	public ResultSet getAnhtrungbayRs();
	public void setAnhtrungbayRs(String query);

	public Hashtable<String, String> getusedPro();
	public void setusedPro(Hashtable<String, String> usedPro);

	public void Luutratb(HttpServletRequest request);
	public boolean Chot(HttpServletRequest request);
	
	public String getDaduyet();
	public void setDaduyet(String daduyet);

	public void init();

	public void getLanthanhtoan();
	
	public void closeDB();
	
	public Hashtable<String, String> getHashAnh();
	
	public void setUserTen(String userTen);
	public String getUserTen();
	
	
}
