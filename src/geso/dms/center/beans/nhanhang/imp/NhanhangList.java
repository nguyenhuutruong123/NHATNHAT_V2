package geso.dms.center.beans.nhanhang.imp;

import geso.dms.center.beans.nhanhang.INhanhangList;
import geso.dms.center.db.sql.dbutils;

import java.sql.ResultSet;

public class NhanhangList implements INhanhangList
{
	private static final long serialVersionUID = -9217977546733610415L;
	
	String nhanhang;
	String dvkdId;
	ResultSet dvkdlist;
	String tungay;
	String denngay;
	String trangthai;


	

	String Msg;
	ResultSet nhlist;
	dbutils db;
	
	public NhanhangList(String[] param)
	{
		this.nhanhang = param[0];
		this.dvkdId = param[1];
		this.tungay = param[2];
		this.denngay = param[3];
		this.trangthai = param[4];
		this.db = new dbutils();
		createDvkdList();
	}
	public dbutils getDb() {
		return db;
	}
	public NhanhangList()
	{		
		this.nhanhang = "";
		this.dvkdId = "";
		this.tungay = "";
		this.denngay = "";
		this.trangthai = "";	
		this.Msg ="";

		this.db = new dbutils();
		createDvkdList();
	}

	public String getNhanhang()
	{
		return this.nhanhang;
	}

	public void setNhanhang(String nhanhang)
	{
		this.nhanhang = nhanhang;
	}

	public String getDvkdId()
	{
		return this.dvkdId;
	}

	public void setDvkdId(String dvkdId)
	{
		this.dvkdId = dvkdId;
	}
	
	public String getTungay()
	{
		return this.tungay;
		
	}

	public void setTungay(String tungay)
	{
		this.tungay = tungay;
	}
	
	public String getDenngay()
	{
		return this.denngay;
		
	}

	public void setDenngay(String denngay)
	{
		this.denngay = denngay;
	}
	
	public String getTrangthai()
	{
		return this.trangthai;
	}

	public void setTrangthai(String trangthai)
	{
		this.trangthai = trangthai;
	}

	public void setNhlist(ResultSet nhlist)
	{
		this.nhlist = nhlist;
	}

	public ResultSet getNhlist()
	{
		return this.nhlist;
	}
	
	public void setDvkdList(ResultSet dvkdlist)
	{
		this.dvkdlist = dvkdlist;
	}

	public ResultSet getDvkdList()
	{
		return this.dvkdlist;
	}
		
	private void createDvkdList(){		
		//this.dvkdlist =  this.db.get("select distinct a.pk_seq, a.donvikinhdoanh from donvikinhdoanh a, nhanhang b where b.dvkd_fk = a.pk_seq order by donvikinhdoanh");
		/*this.dvkdlist =  this.db.get("select distinct a.pk_seq, a.donvikinhdoanh from donvikinhdoanh a,nhacungcap_dvkd b where a.pk_seq = b.DVKD_fk  and a.trangthai= '1' and b.checked = '1' order by a.donvikinhdoanh");*/
		this.dvkdlist =  this.db.get("select distinct a.pk_seq, a.donvikinhdoanh from donvikinhdoanh a,nhacungcap_dvkd b where a.pk_seq = b.DVKD_fk  and a.trangthai= '1' order by a.donvikinhdoanh");
	}
	
	public void DBClose(){
		try{
			if(this.nhlist != null) this.nhlist.close();
			if(this.dvkdlist != null) this.dvkdlist.close();
			this.db.shutDown();
		}catch(Exception e){}
	}

	public void setMsg(String Msg) {
		this.Msg =Msg;
		
	}

	
	public String getMsg() {
		return this.Msg;
	}
}


