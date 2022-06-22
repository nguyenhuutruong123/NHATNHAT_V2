package geso.dms.distributor.beans.Phanboquatang.imp;


import geso.dms.distributor.beans.Phanboquatang.IPhanboquatang;
import geso.dms.distributor.beans.Phanboquatang.IPhanboquatangList;
import geso.dms.distributor.beans.Phanboquatang.imp.PhanboquatangList;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import geso.dms.center.db.sql.dbutils;
import geso.dms.center.util.Utility;

public class PhanboquatangList implements IPhanboquatangList
{
	private static final long serialVersionUID = -927977546783610214L;
	
	String userId;
	String ten;
	String tungay;
	String denngay;
	String trangthai;
	ResultSet rslist;
	dbutils db;
	String msg = "";
	
	
	public String getTungay() {
		return tungay;
	}
	public void setTungay(String tungay) {
		this.tungay = tungay;
	}
	public String getDenngay() {
		return denngay;
	}
	public void setDenngay(String denngay) {
		this.denngay = denngay;
	}

	
	public ResultSet getRslist() {
		return rslist;
	}
	public void setRslist(ResultSet rslist) {
		this.rslist = rslist;
	}
	public String getMsg() {
		return msg;
	}
	public void setMsg(String msg) {
		this.msg = msg;
	}
	
	public PhanboquatangList()
	{
		this.ten= "";		
		this.tungay= "";
		this.denngay="";
		this.trangthai = "2";
		this.db = new dbutils();
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

	public String getTen() 
	{
		return this.ten;
	}

	public void setTen(String ten) 
	{
		this.ten = ten;
	}

	
	public String getTrangthai() 
	{
		return this.trangthai;
	}

	public void setTrangthai(String trangthai) 
	{
		this.trangthai = trangthai;
	}


	public boolean saveNewBgblc() 
	{
		return false;
	}

	public boolean UpdateBgblc() 
	{
		return false;
	}

	

	

	public void init(String search){

		String query;
		
		if (search.length()>0){
			query = search;
		}else{
			System.out.println("user id la "+this.userId);
			Utility util = new Utility();
			String nppid=util.getIdNhapp(this.userId);
			query =" select pk_seq,trangthai,tungay,denngay,Ma from phanbo_quatang where npp_fk= "+nppid;
			
		}
		rslist=this.db.get(query);
	}

	@Override
	public void DbClose() {
		try{
		
		if(this.db!=null){
			db.shutDown();
		}
		}catch(Exception er){
			
		}
	}

	
}

