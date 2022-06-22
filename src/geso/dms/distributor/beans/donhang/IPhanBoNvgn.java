package geso.dms.distributor.beans.donhang;

import geso.dms.center.util.IPhan_Trang;
import geso.dms.distributor.db.sql.dbutils;
import geso.dms.distributor.util.Utility;

import java.io.Serializable;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public interface IPhanBoNvgn extends IPhan_Trang {
	public String getId();

	public void setId(String id);

	public String getUserId();

	public void setUserId(String userId);

	public String[] getDonhang_fk();

	public void setDonhang_fk(String[] donhang_fk);
	
	public boolean createPhanBo();
	public boolean updatePhanBo();
	
	public String getNguoitao();

	public void setNguoitao(String nguoitao);

	public String getNguoisua();

	public void setNguoisua(String nguoisua);

	public String getNgaytao();

	public void setNgaytao(String ngaytao);

	public String getNgaysua();

	public void setNgaysua(String ngaysua);
	
	public void initDHSP(String id);
	public void init(String id);
	public void initList(String search);
	public String getNppId();
	public void setNppId(String nppId);
	public String getNppTen();
	public void setNppTen(String nppTen);
	public ResultSet getPbList();
	public void setPbList(ResultSet pbList);
	public String getMsg();
	public void setMsg(String msg);
	public String getTungay();
	public void setTungay(String tungay);
	public String getDenngay();
	public void setDenngay(String denngay);
	public int getCurrentPages();
	public void setCurrentPages(int currentPages);
	public int[] getListPages();
	public void setListPages(int[] listPages);
	public dbutils getDb();
	
	public void DBclose();
	public ResultSet getDonhangRs();
	public ResultSet getDonhang_SanPhamRs(String dhId);
	public String getNvgn_ma();
	public void setNvgn_ma(String nvgn_ma);
	public String getNvgn_ten();
	public void setNvgn_ten(String nvgn_ten);
	public String[] getPBdonhang_fk();
	public void setPBdonhang_fk(String[] pBdonhang_fk);
	public String get_PBdonhang_fk();
	public void set_PBdonhang_fk(String _PBdonhang_fk);
	public ResultSet getNvgnRs();
	public void setNvgnRs(ResultSet nvgnRs);
	public String getNvgn_fk();
	public void setNvgn_fk(String nvgn_fk);
	public String getNgayphanbo();
	public void setNgayphanbo(String ngayphanbo);
}
