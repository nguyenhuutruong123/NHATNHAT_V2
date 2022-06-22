
package geso.dms.center.beans.mochot;

import geso.dms.distributor.db.sql.dbutils;

import java.sql.ResultSet;

public interface Imochot {

	public String getMahopdong();
	public void setMahopdong(String mahopdong);

	public String getNppid();

	public void setNppid(String nppid);

	public String getMahoadon() ;

	public void setMahoadon(String mahoadon);

	public ResultSet getRsnppid() ;

	public void setRsnppid(ResultSet rsnppid) ;

	public String getMsg();

	public void setMsg(String msg) ;

	public dbutils getDb() ;

	public void setDb(dbutils db);
	public String getIshd();

	public void setIshd(String ishd);
	public void init();
	public void mohopdong(String mahd,String npp);
	public void mohoadon(String mahd,String npp);
}
