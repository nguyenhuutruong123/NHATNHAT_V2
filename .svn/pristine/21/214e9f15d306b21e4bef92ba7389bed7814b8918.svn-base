package geso.dms.distributor.beans.bcduyettratl.imp;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import geso.dms.center.db.sql.dbutils;
import geso.dms.center.util.Utility;
import geso.dms.distributor.beans.bcduyettratl.*;
public class bcduyettratl implements Ibcduyettratl
{
	ResultSet dangkyTichluyRs ;
	List<String> dangkyIds = new ArrayList<String>() ;
	String userId;
	ResultSet ctkmRs;
	String ctkmId;
	String tungay_ds = "";
	String denngay_ds = "";
	String diengiai;
	String trungtam;
	String msg;
	dbutils db;
	public bcduyettratl()
	{
		this.diengiai = "";
		this.msg = "";
		this.ctkmId = "";
		this.trungtam="";
		this.db = new dbutils();
	}

	public String getTrungtam() {
		return trungtam;
	}

	public void setTrungtam(String trungtam) {
		this.trungtam = trungtam;
	}
	
	public String getUserId()
	{
		return this.userId;
	}

	public void setUserId(String userId)
	{
		this.userId = userId;
	}

	public String getDiengiai()
	{
		return this.diengiai;
	}

	public void setDiengiai(String diengiai) 
	{
		this.diengiai = diengiai;
	}

	public String getMsg()
	{
		return this.msg;
	}

	public void setMsg(String msg) 
	{
		this.msg = msg;
	}

	public void init() 
	{
		createRs() ;
		
	}
	
	public void setCtkmRs(ResultSet ctkmRs) {

		this.ctkmRs = ctkmRs;
	}


	public ResultSet getCtkmRs() {

		return this.ctkmRs;
	}

	public void createRs() 
	{	
		try{
			String query = "";
			query = "select PK_SEQ as ctkmId, SCHEME + ', ' + DIENGIAI as SCHEME from TIEUCHITHUONGTL where trangthai = 1";
			System.out.println("QUERY LA: " + query);
			this.ctkmRs = db.get(query);
			
		}
		catch(Exception ex){
			ex.printStackTrace();
		}
		
		
		

	}


	public String getCtkmId() {

		return this.ctkmId;
	}


	public void setCtkmId(String ctkmId) {

		this.ctkmId = ctkmId;
	}




	/*public String[] getNppId() {

		return this.nppId;
	}


	public void setNppId(String[] nppId) {

		this.nppId = nppId;
	}*/


	public void DbClose() {
		this.db.shutDown();
	}



	public String getTungay_ds() {
		if(this.tungay_ds.trim().length() <=0 )
			this.tungay_ds = Utility.getNgayHienTai();

		return tungay_ds;
	}
	public void setTungay_ds(String tungay_ds) {
		this.tungay_ds = tungay_ds;
	}
	public String getDenngay_ds() {
		if(this.denngay_ds.trim().length() <=0 )
			this.denngay_ds = Utility.getNgayHienTai();

		return denngay_ds;
	}
	public void setDenngay_ds(String denngay_ds) {
		this.denngay_ds = denngay_ds;
	}


}
