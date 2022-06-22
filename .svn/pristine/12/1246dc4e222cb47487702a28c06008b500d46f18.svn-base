package geso.dms.center.beans.thongbao.imp;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Hashtable;

import geso.dms.center.beans.thongbaodoanhso.Ithongbaodoanhso;
import geso.dms.center.db.sql.dbutils;

public class thongbaodoanhso implements Ithongbaodoanhso 
{
	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}



	public dbutils getDb() {
		return db;
	}

	public void setDb(dbutils db) {
		this.db = db;
	}

	String userId;
	String msg;
	
	
	public String getGiosandoz() {
		return giosandoz;
	}

	public void setGiosandoz(String giosandoz) {
		this.giosandoz = giosandoz;
	}

	public String getPhutsandoz() {
		return phutsandoz;
	}

	public void setPhutsandoz(String phutsandoz) {
		this.phutsandoz = phutsandoz;
	}

	public String getNgaysandoz() {
		return ngaysandoz;
	}

	public void setNgaysandoz(String ngaysandoz) {
		this.ngaysandoz = ngaysandoz;
	}

	String giosandoz;
	String phutsandoz;
	String ngaysandoz;
	String email;
	ResultSet rsemail;
	public ResultSet getRsemail() {
		return rsemail;
	}

	public void setRsemail(ResultSet rsemail) {
		this.rsemail = rsemail;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}


	dbutils db;
	
	public thongbaodoanhso()
	{
		msg = "";
		db = new dbutils();
		this.giosandoz="";
		this.phutsandoz="";
		this.ngaysandoz="";
		//this.init();
	}
	
	public void init ()
	{
		String sql="select email from TB_EMAIL";
		this.rsemail=db.get(sql);
		
	}
	
	public String luu_email(ArrayList<String> arrlist)
	{
		String thongbao="";
		try {
			db.getConnection().setAutoCommit(false);
			String sql="delete from TB_EMAIL  ";
			if(!db.update(sql))
			{
				db.getConnection().rollback();
				this.msg="không thể tao thông báo nhóm hàng sandoz";
				thongbao="không thể tao thông báo nhóm hàng sandoz";
				return thongbao;
			}
			
			for(int i=0;i<arrlist.size();i++)
			{
				 sql="insert into TB_EMAIL (email) values ('"+arrlist.get(i)+"')";
					if(!db.update(sql))
					{
						db.getConnection().rollback();
						this.msg="không thể tao thông báo nhóm hàng sandoz";
						thongbao="không thể tao thông báo nhóm hàng sandoz";
						return thongbao;
					}
			}

			db.getConnection().setAutoCommit(true);
			db.getConnection().commit();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	
		
		return thongbao;
		
		
	}
	
	public String luuthongbaodoanhso()
	{
		String thongbao="";
		if(this.giosandoz.length()==0)
		{
			this.msg="vui lòng nhập giờ nhóm hàng sandoz";
			thongbao="vui lòng nhập giờ nhóm hàng sandoz";
			return thongbao;
		}
		if(this.phutsandoz.length()==0)
		{
			this.msg="vui lòng nhập giờ phút nhóm hàng sandoz";
			thongbao="vui lòng nhập giờ phút nhóm hàng sandoz";
			return thongbao;
		}
		if(this.ngaysandoz.length()==0)
		{
			this.msg="vui lòng nhập giờ phút nhóm hàng sandoz";
			thongbao="vui lòng nhập giờ phút nhóm hàng sandoz";
			return thongbao;
		}
		try {
			db.getConnection().setAutoCommit(false);
			String sql="select count(*) as sl from thongbaodoanhso where  nhomid=100005 ";
			ResultSet rs=db.get(sql);
			rs.next();
			if(rs.getInt("sl")==0)
			{
				 sql="insert into thongbaodoanhso (gio,phut,thoigian,nhomid) values ("+this.giosandoz+","+this.phutsandoz+",'"+this.ngaysandoz+"','100005')";
				if(!db.update(sql))
				{
					db.getConnection().rollback();
					this.msg="không thể tao thông báo nhóm hàng sandoz";
					thongbao="không thể tao thông báo nhóm hàng sandoz";
					return thongbao;
				}
			}
			else
			{
				 sql="update  thongbaodoanhso  set gio="+this.giosandoz+",phut="+this.phutsandoz+",thoigian='"+this.ngaysandoz+"'where  nhomid=100005 ";
					if(!db.update(sql))
					{
						db.getConnection().rollback();
						this.msg="không thể cập nhật thông báo nhóm hàng sandoz";
						thongbao="không thể cập nhật thông báo nhóm hàng sandoz";
						return thongbao;
					}
			}
			db.getConnection().setAutoCommit(true);
			db.getConnection().commit();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	
		
		return thongbao;
		
		
	}
	public void DBclose() 
	{
		this.db.shutDown();
	}
	
}
