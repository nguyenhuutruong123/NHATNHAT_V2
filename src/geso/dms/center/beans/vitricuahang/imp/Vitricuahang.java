package geso.dms.center.beans.vitricuahang.imp;

import geso.dms.center.beans.vitricuahang.IVitricuahang;
import geso.dms.center.db.sql.*;

import java.sql.ResultSet;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Vitricuahang implements IVitricuahang
{
	private static final long serialVersionUID = -9217977546733610214L;
	String userId;
	String id;
	String vitricuahang;
	String diengiai;
	String trangthai;
	String ngaytao;
	String nguoitao;
	String ngaysua;
	String nguoisua;
	String msg;
	
	dbutils db;
	
	public Vitricuahang(String[] param)
	{
		this.db = new dbutils();
		this.id = param[0];
		this.vitricuahang = param[1];
		this.diengiai = param[2];
		this.trangthai = param[3];
		this.ngaytao = param[4];
		this.nguoitao = param[5];
		this.ngaysua = param[6];
		this.nguoisua = param[7];
		this.msg = "";
	}
	
	public Vitricuahang(String id)
	{
		this.db = new dbutils();
		this.id = id;
		this.vitricuahang = "";
		this.diengiai = "";
		this.trangthai = "";
		this.ngaytao = "";
		this.nguoitao = "";
		this.ngaysua = "";
		this.nguoisua = "";
		this.msg = "";
		if(id.length() > 0)
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
	
	public String getVitricuahang() 
	{
		return this.vitricuahang;
	}

	public void setVitricuahang(String vitricuahang) 
	{
		this.vitricuahang = vitricuahang;
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
		return this.trangthai;
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

	public String getNguoitao() 
	{
		return this.nguoitao;
	}

	public void setNguoitao(String nguoitao) 
	{
		this.nguoitao = nguoitao;
	}

	public String getNgaysua() 
	{
		return this.ngaysua;
	}

	public void setNgaysua(String ngaysua) 
	{
		this.ngaysua = ngaysua;
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

	public boolean CreateVtch()
	{
		try{
			this.db.getConnection().setAutoCommit(false);
		this.ngaytao = getDateTime();
		this.nguoitao = this.userId;
		
		String command = "insert into vitricuahang(diengiai,ngaytao,ngaysua,nguoitao,nguoisua,trangthai,vitri) values(N'" + this.diengiai + "','" + this.ngaytao + "','" + this.ngaytao + "','" + this.userId + "','" + this.userId + "','" + this.trangthai + "',N'" + this.vitricuahang + "')"; 
		
		if (!this.db.update(command)){
			this.msg = "Exception";		
			this.db.update("rollback");
			return false;
		}
		/*
		 * thuc hien cap nhat lai smartid
		 * 
		 */
		
		String query_getid = "select SCOPE_IDENTITY() as id";
		ResultSet rs_getpk_seq = this.db.get(query_getid);
		rs_getpk_seq.next();
		this.id = rs_getpk_seq.getString("id");
		try{
		String sql_update_smartid="update vitricuahang set smartid='"+this.id+"' where pk_seq=" + this.id;
		if(!db.update(sql_update_smartid)){
			db.update("rollback");
		System.out.println("Exception");
			return false;
		}
		}catch(Exception er){
			
		}


		
		this.db.getConnection().commit();
		this.db.getConnection().setAutoCommit(true);
		
		}catch(Exception er){
			this.db.update("rollback");
			this.msg="Exception ";
		
		}
		return true;
	}

	public boolean UpdateVtch() 
	{
		this.ngaysua = getDateTime();
		this.nguoisua = this.userId;
		
		String command ="update vitricuahang set vitri =N'" + this.vitricuahang + "', diengiai = N'" + this.diengiai + "', trangthai='" + this.trangthai + "', ngaysua = '" + this.ngaysua + "', nguoisua = '" + this.userId + "' where pk_seq = '" + this.id + "'";
		
		if (!db.update(command)){
			this.msg = "Exception";			
			db.update("rollback");
			return false;
		}

		return true; 
	}

	private void init()
	{	
		String query = "select a.pk_seq as id, a.vitri, a.diengiai, a.trangthai, a.ngaytao, b.ten as nguoitao, a.ngaysua, b.ten as nguoisua"; 
		query = query + " from vitricuahang a, nhanvien b, nhanvien c where a.nguoitao = b.pk_seq and a.nguoisua = c.pk_seq";
		query = query + " and a.pk_seq='"+ this.id + "'";
        ResultSet rs =  db.get(query);
        try{
            rs.next();        	
        	this.id = rs.getString("id");
        	this.vitricuahang = rs.getString("vitri");
        	this.diengiai = rs.getString("diengiai");
        	this.trangthai = rs.getString("trangthai");
        	this.ngaytao = rs.getString("ngaytao");
        	this.nguoitao = rs.getString("nguoitao");
        	this.ngaysua = rs.getString("ngaysua");
        	this.nguoisua = rs.getString("nguoisua");
        	  	
       	}catch(Exception e){}
	}
	private String getDateTime() 
	{
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date date = new Date();
        return dateFormat.format(date);	
	}	

	public void closeDB(){
		if(this.db != null)
			this.db.shutDown();
	}	
}

