package geso.dms.center.beans.quanhuyen;

import geso.dms.center.db.sql.dbutils;

import java.io.Serializable;
import java.sql.ResultSet;

public interface IQuanhuyenList extends Serializable {
	public String getTen();
	public void setTen(String ten);

	public String getTinhId();
	public void setTinhId(String tinhId);

	public ResultSet getQuanhuyenRs();
	public void setQuanhuyenRs(ResultSet quanhuyenRs);

	public ResultSet getTinhthanhRs();
	public void setTinhthanhRs(ResultSet tinhthanhRs);

	public dbutils getDb();
	public void init();
}
