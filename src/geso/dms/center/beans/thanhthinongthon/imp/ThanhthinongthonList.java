package geso.dms.center.beans.thanhthinongthon.imp;

import geso.dms.center.beans.thanhthinongthon.IThanhthinongthon;
import geso.dms.center.beans.thanhthinongthon.IThanhthinongthonList;
import geso.dms.center.db.sql.dbutils;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class ThanhthinongthonList implements IThanhthinongthonList 
{
	private static final long serialVersionUID = -9217977546733610214L;
	
	String userId;
	ResultSet khuvucRs;
	String khuvucId;
	String trangthai;
	String Msg;
	List<IThanhthinongthon> ttntlist;
	
	dbutils db;
	
	public ThanhthinongthonList(String[] param)
	{
		this.trangthai = param[0];
		db = new dbutils();
	}
	
	public ThanhthinongthonList()
	{
		this.trangthai = "";
		this.Msg ="";
		db = new dbutils();
		init("");
	}
	
	public String getUserId() 
	{
		return this.userId;
	}

	public void setUserId(String userId) 
	{
		this.userId = userId;
	}
	
	public ResultSet getKhuvucRs() 
	{
		return this.khuvucRs;
	}



	public String getKhuvucId() 
	{
		return this.khuvucId;
	}

	public void setKhuvucId(String vmId) 
	{
		this.khuvucId = vmId;
	}

	public String getTrangthai() 
	{
		return this.trangthai;
	}

	public void setTrangthai(String trangthai) 
	{
		this.trangthai = trangthai;
	}

	public List<IThanhthinongthon> getThanhthinongthonList() 
	{
		return this.ttntlist;
	}

	public void setThanhthinongthonList(List<IThanhthinongthon> kvlist)
	{
		this.ttntlist = kvlist;
	}

	

	public void createKvBeanList(String query)
	{  	  
		ResultSet rs =  db.get(query);
		List<IThanhthinongthon> dblist = new ArrayList<IThanhthinongthon>();
		if (rs != null){		
			IThanhthinongthon dbBean;
			String[] param = new String[8];
			try{
				while(rs.next()){
					param[0]= rs.getString("id");
					param[1]= rs.getString("ttntTen");
					param[2]= rs.getString("trangthai");
					param[3]= rs.getString("ngaytao");
					param[4]= rs.getString("nguoitao");
					param[5]= rs.getString("ngaysua");
					param[6]= rs.getString("nguoisua");
					param[7]= rs.getString("diengiai");

					dbBean = new Thanhthinongthon(param);
					dblist.add(dbBean);															
				}
			}catch(Exception e){
		
			}
		}
		
		this.ttntlist = dblist;
	}
	
	public void init(String search) 
	{
		String query;	
		if (search.length() == 0)
		{
			query = "select a.pk_seq as id, a.ten as ttntTen, a.trangthai as trangthai, c.ten as nguoitao, a.ngaytao as ngaytao, d.ten as nguoisua, a.ngaysua as ngaysua, a.diengiai";
			query = query + " from Thanhthinongthon a, nhanvien c, nhanvien d";
			query = query + " where  a.nguoitao = c.pk_seq and a.nguoisua = d.pk_seq";
		}
		else
		{
			query = search;
		}
		
		createKvBeanList(query);  
	}

	public void setMsg(String Msg) {
	     this.Msg = Msg;
		
	}

	
	public String getMsg() {
		return this.Msg;
	}

	public void closeDB(){
		if (this.db != null) {
			this.db.shutDown();
		}
	}
}
