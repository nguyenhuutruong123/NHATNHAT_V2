package geso.dms.center.beans.hanmucdoanhthu;

import java.io.Serializable;
import java.sql.ResultSet;
import java.util.Hashtable;

public interface Ihanmucdoanhthu  extends Serializable {
	
	public String getTungay() ;

	public void setTungay(String tungay);

	public String getDenngay();

	public void setDenngay(String denngay);

	public String getNppid();

	public void setNppid(String nppid) ;

	public String getHanmuc();

	public void setHanmuc(String hanmuc) ;

	public Hashtable<String, String> getHhanmuc();

	public void setHhanmuc(Hashtable<String, String> hhanmuc);
	public ResultSet getRsnppid() ;
	public void setRsnppid(ResultSet rsnppid);
	public void init();
	public String getMsg();

	public void setMsg(String msg) ;
	public String getId();

	public void setId(String id);
	
	public ResultSet getRshanmuc();

	public void setRshanmuc(ResultSet rshanmuc);
	public boolean save();
	public boolean update();
	public boolean delete();
	public void init(String id);
	public boolean chot();

}
