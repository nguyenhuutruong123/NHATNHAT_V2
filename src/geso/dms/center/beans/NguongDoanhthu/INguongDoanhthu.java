package geso.dms.center.beans.NguongDoanhthu;

import java.io.Serializable;
import java.sql.ResultSet;

public interface INguongDoanhthu extends Serializable {
	public String getUserid();

	public void setUserid(String userid);
	public String getMessage();

	public void setMessage(String message);
	public String getNppid();

	public void setNppid(String nppid);

	public ResultSet getRsnppid();

	public void setRsnppid(ResultSet rsnppid);

	public String getTungay();

	public void setTungay(String tungay) ;

	public String getDenngay() ;

	public void setDenngay(String denngay) ;
	public void init();

}
