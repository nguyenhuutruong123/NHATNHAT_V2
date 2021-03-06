package geso.dms.center.beans.loaicuahang.imp;

import geso.dms.center.beans.loaicuahang.ILoaicuahang;
import geso.dms.center.db.sql.*;

import java.sql.ResultSet;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Loaicuahang implements ILoaicuahang
{
	private static final long serialVersionUID = -9217977546733610214L;
	String userId;
	String id;
	String loaicuahang;
	String diengiai;
	String trangthai;
	String ngaytao;
	String nguoitao;
	String ngaysua;
	String nguoisua;
	String msg;
	
	dbutils db;
	
	public Loaicuahang(String[] param)
	{
		this.db = new dbutils();
		this.id = param[0];
		this.loaicuahang = param[1];
		this.diengiai = param[2];
		this.trangthai = param[3];
		this.ngaytao = param[4];
		this.nguoitao = param[5];
		this.ngaysua = param[6];
		this.nguoisua = param[7];
		this.msg = "";
	}
	
	public Loaicuahang(String id)
	{
		this.db = new dbutils();
		this.id = id;
		this.loaicuahang = "";
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
	
	public String getLoaicuahang() 
	{
		return this.loaicuahang;
	}

	public void setLoaicuahang(String loaicuahang) 
	{
		this.loaicuahang = loaicuahang;
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

	public boolean CreateLch()
	{
		try{
			this.ngaytao = getDateTime();
			this.nguoitao = this.userId;
			this.db.getConnection().setAutoCommit(false);
			String command = "insert into loaicuahang ( diengiai,trangthai,ngaytao,ngaysua,nguoitao,nguoisua,loai) "+
			" values(N'" + this.diengiai + "','" + this.trangthai + "','" + this.ngaytao + "','" + this.ngaytao + "','" + this.userId + "','" + this.userId + "',N'" + this.loaicuahang + "')"; 
		
			if (!db.update(command)){
			this.msg = "insert into loaicuahang ( diengiai,trangthai,ngaytao,ngaysua,nguoitao,nguoisua,loai) " + 
			" values(s_loaicuahang.nextval,'" + this.diengiai + "','" + this.trangthai + "','" + this.ngaytao + "','" + this.ngaytao + "','" + this.userId + "','" + this.userId + "',N'" + this.loaicuahang + "')";		
				db.update("rollback");
			return false;
			}
			/*
			 * thuc hien cap nhat lai smartid
			 * 
			 */
			String query_getid = "select IDENT_CURRENT('loaicuahang') as id";
			ResultSet rs_getpk_seq = this.db.get(query_getid);
			rs_getpk_seq.next();
			this.id = rs_getpk_seq.getString("id");
			try{
			String sql_update_smartid="update loaicuahang set smartid='"+this.id+"' where pk_seq=" + this.id;
			if(!db.update(sql_update_smartid)){
				db.update("rollback");
				System.out.println("Khong The Thuc Hien Luu Bang Nay,Vui Long Lien He Voi Admin De Sua Loi Nay");
				return false;
			}
			}catch(Exception er){
				
				db.update("rollback");
				return false;
			}

			this.db.getConnection().commit();
			this.db.getConnection().setAutoCommit(true);
		}
		catch(Exception er){
			this.msg="Exception";
			this.db.update("rollback");
			return false;
		}
		return true;
	}

	public boolean UpdateLch() 
	{
		
		this.ngaysua = getDateTime();
		this.nguoisua = this.userId;
		
		String command ="update loaicuahang set loai = N'" + this.loaicuahang + "', diengiai = N'" + this.diengiai + "', trangthai='" + this.trangthai + "', ngaysua = '" + this.ngaysua + "', nguoisua = '" + this.userId + "' where pk_seq = '" + this.id + "'";
		
		if (!db.update(command)){
			this.msg = "update loaicuahang set loai = N'" + this.loaicuahang + "', diengiai = N'" + this.diengiai + "', trangthai='" + this.trangthai + "', ngaysua = '" + this.ngaysua + "', nguoisua = '" + this.userId + "' where pk_seq = '" + this.id + "'";			
			db.update("rollback");
			return false;
		}

		return true; 
	}

	private void init()
	{	
		//String query = "select a.pk_seq as id, a.loai, a.diengiai, a.trangthai, a.ngaytao, b.ten as nguoitao, a.ngaysua, b.ten as nguoisua"; 
		//query = query + " from loaicuahang a inner join nhanvien b on a.nguoitao = b.pk_seq and a.nguoisua = b.pk_seq";
		String query = "select a.pk_seq as id, a.loai, a.diengiai, a.trangthai, a.ngaytao, b.ten as nguoitao, a.ngaysua, c.ten as nguoisua"; 
		query = query + " from loaicuahang a, nhanvien b, nhanvien c where a.nguoitao = b.pk_seq and a.nguoisua = c.pk_seq";
		query = query + " and a.pk_seq='"+ this.id + "'";
		//String query = "select * from loaicuahang where pk_seq ='"+ this.id +"'"; 
		System.out.println("loai cua hang:" + query);
        ResultSet rs =  db.get(query);
        try{
           while(rs.next())
           {
        	this.id = rs.getString("id");
        	this.loaicuahang = rs.getString("loai");
        	this.diengiai = rs.getString("diengiai");
        	System.out.println("loai hang"+ this.loaicuahang +" dien giai " + this.diengiai);
        	this.trangthai = rs.getString("trangthai");
        //	this.ngaytao = rs.getString("ngaytao");
        //	this.nguoitao = rs.getString("nguoitao");
        	//this.ngaysua = rs.getString("ngaysua");
        	//this.nguoisua = rs.getString("nguoisua");
           }  	
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
