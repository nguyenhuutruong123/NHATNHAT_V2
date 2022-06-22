package geso.dms.center.beans.quanhuyen;

import geso.dms.center.db.sql.dbutils;

import java.io.Serializable;
import java.sql.ResultSet;

public interface IQuanhuyen extends Serializable {
	public String getUserId();
	public void setUserId(String userId);

	public String getId();
	public void setId(String id);

	public String getMa();
	public void setMa(String ma);

	public String getTen();
	public void setTen(String ten);

	public String getTinhId();
	public void setTinhId(String tinhId);

	public String getMsg();
	public void setMsg(String msg);

	public ResultSet getTinhRs();
	public void setTinhRs(ResultSet tinhRs);

	public dbutils getDb();
	
	public void init();
	
	public void createRs();
	
	public boolean createQuanhuyen();
	
	public boolean updateQuanhuyen();
	public String deleteQuanhuyen(String id) ;
	public String getType() ;

	public void setType(String type);
}
