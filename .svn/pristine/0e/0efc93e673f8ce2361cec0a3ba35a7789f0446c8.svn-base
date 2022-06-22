package geso.dms.center.beans.thanhthinongthon.imp;

import geso.dms.center.beans.thanhthinongthon.IThanhthinongthon;
import geso.dms.center.db.sql.*;

import java.sql.ResultSet;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Thanhthinongthon implements IThanhthinongthon
{
	private static final long serialVersionUID = -9217977546733610214L;

	String userId;
	String id;
	String ten;
	String diengiai;
	String trangthai;
	String ngaytao;
	String nguoitao;
	String ngaysua;
	String nguoisua;
	String msg;

	
	dbutils db;
	
	
	
	public Thanhthinongthon(String[] param)
	{
		this.id = param[0];
		this.ten = param[1];
		this.trangthai = param[2];
		this.ngaytao = param[3];
		this.nguoitao = param[4];
		this.ngaysua = param[5];
		this.nguoisua = param[6];
		this.diengiai = param[7];
		this.msg = "";
		this.db = new dbutils();
	}
	
	public Thanhthinongthon(String id)
	{
		this.id = id;
		this.ten = "";
		this.diengiai = "";
		this.trangthai = "2";
		this.ngaytao = "";
		this.nguoitao = "";
		this.ngaysua = "";
		this.nguoisua = "";
		this.msg = "";
		this.db = new dbutils();
		if(id.trim().length() > 0)
			this.init();

	}
	
	
	
	public String getUserId() 
	{
		return this.userId;
	}

	public void setUserId(String userId) 
	{
		this.userId = userId;
	}
	
	public String getId() 
	{
		return this.id;
	}
	
	public void setId(String id) 
	{
		this.id = id;
	}

	public String getTen() 
	{
		return this.ten;
	}

	public void setTen(String ten) 
	{
		this.ten = ten;
	}

	
	public String getDiengiai()
	{
		return this.diengiai;
	}
	
	public void setDiengiai(String diengiai)
	{
		this.diengiai = diengiai;
	}
	
	public String getTrangthai() 
	{
		if (this.trangthai.equals("1")){
			return "Hoat dong";
		}
		return "Ngung hoat dong";
	}

	public void setTrangthai(String trangthai) 
	{
		this.trangthai = trangthai;
	}
	
	public String getNgaytao()
	{
		return this.ngaytao;
		
	}

	public void setNgaytao(String ngaytao)
	{
		this.ngaytao = ngaytao;
	}
	
	public String getNgaysua()
	{
		return this.ngaysua;
		
	}

	public void setNgaysua(String ngaysua)
	{
		this.ngaysua = ngaysua;
	}
	
	public String getNguoitao()
	{
		return this.nguoitao;
	}
	
	public void setNguoitao(String nguoitao)
	{
		this.nguoitao = nguoitao;
	}

	public String getNguoisua()
	{
		return this.nguoisua;
	}

	public void setNguoisua(String nguoisua)
	{
		this.nguoisua = nguoisua;
	}
	
	public String getMessage() 
	{
		return this.msg;
	}
	
	public void setMessage(String msg) 
	{
		this.msg = msg;
	}

	
	public boolean CreateThanhthinongthon() 
	{
		try{
			this.db.getConnection().setAutoCommit(false);
		this.ngaytao = getDateTime();
		this.nguoitao = this.userId;
		String command="";

		command = "insert into ThanhThiNongThon(ten, ngaytao, ngaysua, nguoitao, nguoisua,  trangthai, diengiai)";
		command = command + " values(N'" + this.ten + "','" + this.ngaytao + "','" + this.ngaytao + "','" + this.nguoitao + "','" + this.nguoitao + "',"+this.trangthai+", N'" + this.diengiai  +"')";
		
		if (!db.update(command)){
			this.msg = command;
			db.update("rollback");
			return false;
		}
		

		
		this.db.getConnection().commit();
		this.db.getConnection().setAutoCommit(true);
		}catch(Exception er){
			this.db.update("rollback");
			this.msg="Khong the cap nhat lai bang nay,xay ra loi trong dong lenh sau "+ er.toString();
			return false;
		}
		return true;
	}

	public boolean UpdateThanhthinongthon() 
	{
		this.ngaysua = getDateTime();
		this.nguoisua = this.userId;
		String command="";

		command ="update ThanhThiNongThon set ten = N'" + this.ten + "', diengiai=N'"+ this.diengiai + "', trangthai='" + this.trangthai + "', ngaysua = '" + this.ngaysua + "', nguoisua = '" + this.userId +  "' where pk_seq = '" + this.id + "'";
		
		if (!db.update(command)){
			this.msg = command;			
			db.update("rollback");
			return false;
		}

		return true; 
	}
	
	private void init()
	{	
		String query = " select a.pk_seq as id, a.ten as ttntTen, a.trangthai as trangthai,  c.ten as nguoitao, a.ngaytao as ngaytao, d.ten as nguoisua, a.ngaysua as ngaysua, a.diengiai";
		query = query + " from ThanhThiNongThon a, nhanvien c, nhanvien d";
		query = query + " where  a.nguoitao = c.pk_seq and a.nguoisua = d.pk_seq and a.pk_seq = '" + this.id + "'";
        ResultSet rs =  this.db.get(query);
        try{
            rs.next();        	
        	this.id = rs.getString("id");
        	this.ten = rs.getString("ttntTen");
        	this.trangthai = rs.getString("trangthai");
        	this.ngaytao = rs.getString("ngaytao");
        	this.nguoitao = rs.getString("nguoitao");
        	this.ngaysua = rs.getString("ngaysua");
        	this.nguoisua = rs.getString("nguoisua");
        	this.diengiai = rs.getString("diengiai");
        	     	
       	}catch(Exception e){
       		
       		e.printStackTrace();
       	}

       	
	}
	private String getDateTime() 
	{
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date date = new Date();
        return dateFormat.format(date);	
	}

	
	
	public void closeDB(){
		try {


		} catch(Exception e) {}
		if (this.db != null)
			this.db.shutDown();
	}


	
	
}
