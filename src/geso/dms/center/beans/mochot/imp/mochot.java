package geso.dms.center.beans.mochot.imp;


import geso.dms.center.beans.mochot.Imochot;
import geso.dms.distributor.db.sql.dbutils;

import java.io.Serializable;
import java.sql.ResultSet;
import java.sql.SQLException;

public class mochot  implements Imochot,Serializable{

	private String mahopdong;



	private String nppid;
	private String mahoadon;
	private ResultSet Rsnppid;
	private String msg;
	private String ishd;

	

	dbutils db;
	public mochot(){
		this.mahoadon="";
		this.mahopdong="";
		this.ishd="0";
		this.nppid="";
		this.db = new dbutils();
	}
	
	public void init()
	{
		String sql="select ten,PK_SEQ from NHAPHANPHOI where pk_seq!=1";
		this.Rsnppid=this.db.get(sql);
	}
	
	public void mohopdong(String mahd,String npp)
	{
		try {
			db.getConnection().setAutoCommit(false);
			if(this.mahopdong.trim().length()>0)
			{
			String sql="update ERP_HOPDONGNPP set trangthai=0 where pk_seq="+mahd +" and npp_fk="+npp;
			System.out.println("mo hop don"+sql);
				if(db.updateReturnInt(sql)<=0)
				{
					db.getConnection().rollback();
					this.msg="Không thể mở hợp đồng số "+mahd;
					System.out.println("error hop dong" +sql);
					return;
				}
				 sql="insert ERP_HOPDONGNPP_LOG (pk_seq,npp_fk,thoigian) \n"+
						   " values ('"+mahd+"','"+npp+"',getdate()) ";
				System.out.println("cchen  boga"+sql);
					if(db.updateReturnInt(sql)<=0)
					{
						db.getConnection().rollback();
						this.msg="Không thể mở hợp đồng số "+mahd;
						System.out.println("error hop dong log" +sql);
						return;
					}
				
				
			}
			
			db.getConnection().commit();
			db.getConnection().setAutoCommit(true);
			this.msg="Mở hợp đồng số  "+mahd+" thành công.";
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			this.msg="Không thể mở hợp đồng số "+mahd;
			e.printStackTrace();
		}
		
		
		
		
	}
	
	
	public void mohoadon(String mahd,String npp)
	{
		
		try {
			db.getConnection().setAutoCommit(false);
		if(mahd.trim().length()>0)
		{
		
		String sql="select trangthai from hoadon where pk_seq="+mahd;
			ResultSet rs=db.get(sql);
			rs.next();
			int tranthai=rs.getInt("trangthai");
			if(tranthai==5)
			{
				sql="update hoadon set trangthai=3 where pk_Seq= "+mahd +" and npp_fk="+npp;
						if(db.updateReturnInt(sql)==0)
						{
							db.getConnection().rollback();
							this.msg="không thể chuyển trạng thái hóa đơn "+mahd;
							System.out.println("error haodon "+sql);
							return;
						}
				 sql="insert HOADON_LOG (pk_seq,npp_fk,thoigian) \n"+
						   "values ('"+mahd+"','"+npp+"',getdate())";
				  
						if(db.updateReturnInt(sql)<=0)
						{
							db.getConnection().rollback();
							this.msg="không thể chuyển trạng thái hóa đơn "+mahd;
							System.out.println("error haodon log "+sql);
							return;
						}
			}
					
				db.getConnection().commit();
				db.getConnection().setAutoCommit(true);
				this.msg=" chuyển trạng thái hóa đơn "+mahd+" thành công.";
		
		}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			this.msg="không thể chuyển trạng thái hóa đơn "+mahd;
			e.printStackTrace();
		}
		
	}
	
	
	public String getMahopdong() {
		return mahopdong;
	}

	public void setMahopdong(String mahopdong) {
		this.mahopdong = mahopdong;
	}

	public String getNppid() {
		return nppid;
	}

	public void setNppid(String nppid) {
		this.nppid = nppid;
	}

	public String getMahoadon() {
		return mahoadon;
	}

	public void setMahoadon(String mahoadon) {
		this.mahoadon = mahoadon;
	}

	public ResultSet getRsnppid() {
		return Rsnppid;
	}

	public void setRsnppid(ResultSet rsnppid) {
		Rsnppid = rsnppid;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public dbutils getDb() {
		return db;
	}

	public void setDb(dbutils db) {
		this.db = db;
	}
	public String getIshd() {
		return ishd;
	}

	public void setIshd(String ishd) {
		this.ishd = ishd;
	}
	
}
