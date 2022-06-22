package geso.dms.center.beans.kehoachpg;

import geso.dms.center.db.sql.dbutils;
import geso.dms.center.util.Utility;

import java.io.Serializable;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Hashtable;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.oreilly.servlet.MultipartRequest;

public interface IKehoachPG extends Serializable
{
	
	
	public dbutils getDb();
	
	public String getView();
	public void setView(String view);
	

	public String getUserId();

	public void setUserId(String userId);

	public String getId() ;

	public void setId(String id) ;

	public String getTen();

	public void setTen(String ten);

	

	public String getTrangthai() ;

	public void setTrangthai(String trangthai) ;

	public String getNppId() ;

	public void setNppId(String nppId) ;

	public String getNgaytao();
	public void setNgaytao(String ngaytao);

	public String getNguoitao();

	public void setNguoitao(String nguoitao);

	public String getNgaysua();

	public void setNgaysua(String ngaysua);

	public String getNguoisua();

	public void setNguoisua(String nguoisua);

	public String getMessage() ;

	public void setMessage(String msg) ;

	

	


	public void setNppList(ResultSet npplist);

	public ResultSet getNppList() ;

	public boolean getIsDelete() ;

	public void setIsDelete(boolean isDelete) ;

	public void createNppList();

	

	
	
	public void createRS() ;
	
	
	

	public boolean CreateKehoach(String contentType,MultipartRequest multi,HttpServletRequest request) ;
	
	public boolean UpdateKehoach(String contentType,MultipartRequest multi,HttpServletRequest request) ;

	public void init() ;

	public void DBClose() ;
	public String getTuan() ;
	public void setTuan(String tuan) ;
	public String getNam() ;
	public void setNam(String nam) ;
	public String getTungay() ;
	public String getDenngay() ;
}