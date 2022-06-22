package geso.dms.center.beans.kho.imp;

import geso.dms.center.beans.kho.IKho;
import geso.dms.center.db.sql.*;
import geso.dms.distributor.util.Utility;

import java.sql.ResultSet;
import java.sql.SQLException;


public class Kho implements IKho
{
	private static final long serialVersionUID = -9217977546733610415L;
	String id;
	String ten;
	String diengiai;
	String ngaytao;
	String ngaysua;
	String nguoitao;
	String nguoisua;
	String trangthai;
	String msg;

	dbutils db;
	
	public Kho(String[] param)
	{
		this.id = param[0];
		this.ten = param[1];	
		this.diengiai  = param[2];
		this.ngaytao = param[3];
		this.ngaysua = param[4];
		this.nguoitao = param[5];
		this.nguoisua = param[6];
		this.trangthai = param[7];	
		this.msg = "";
		//this.db = new dbutils();
	}
	
	public Kho()
	{
		this.id = "";
		this.ten = "";
		this.diengiai  = "";
		this.ngaytao = "";
		this.ngaysua = "";
		this.nguoitao = "";
		this.nguoisua = "";
		this.trangthai = "";	
		this.msg = "";
		this.db = new dbutils();
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
	
	int loaikho = 0;
	public int getLoaikho() {
		return loaikho;
	}
	public void setLoaikho(String loaikho) {
		this.loaikho = geso.dms.center.util.Utility.parseInt(loaikho);
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
	
	public String getTrangthai()
	{
		return this.trangthai;
	}

	public void setTrangthai(String trangthai)
	{
		this.trangthai = trangthai;
	}


	public String getMessage()
	{
		return this.msg;
	}

	public void setMessage(String msg)
	{
		this.msg = msg;
	}
	
	public static boolean checkMa(String id, String ma ,Idbutils db) throws SQLException
	{
		db.ClearParam();
		db.AddParam("@ma", ma);
		String query  = " select count(*) val from KHO where TEN =@ma ";
		if(id.length()> 0)
		{
			db.AddParam("@id", id);
			query +=" and pk_seq <> @id ";
		}
			
		ResultSet rs = db.get_with_param(query);
		rs.next();
		return !(rs.getInt("val") > 0);
	}
	
	public boolean CheckVal()
	{
		if(this.ten == null || this.ten.trim().length()<=0)
		{
			this.msg ="Vui lòng nhập tên kho";
			return false;
		}
		if(this.diengiai == null || this.diengiai.trim().length()<=0)
		{
			this.msg ="Vui lòng nhập diễn giải kho";
			return false;
		}
		return true;
	}
	
	public boolean saveNewKho()
	{

		try
		{
			
			this.db.getConnection().setAutoCommit(false);
			
			if(!this.CheckVal())
			{
				geso.dms.center.util.Utility.rollback_throw_exception(db);
				return false;		
			}
			if(!checkMa("", this.ten, this.db))
			{
				this.msg = " Tên kho đã tồn tại";
				geso.dms.center.util.Utility.rollback_throw_exception(db);
				return false;
			}
			
					String query ="insert into kho(ten,NGAYTAO,NGAYSUA,NGUOITAO,NGUOISUA,TRANGTHAI,DIENGIAI,loaikho) "
							+ "values (N'" + this.ten + "','"+ this.ngaytao + "','" + this.ngaysua + "','" + this.nguoitao + "','" + this.nguoisua + "','" + this.trangthai + "',N'" + this.diengiai + "','" + this.loaikho + "')";
					
					if (!this.db.update(query)){
						geso.dms.center.util.Utility.rollback_throw_exception(db);
						this.msg = "Khong the luu vao table 'Kho'" ;
						return false;			
					}
	
						
				this.db.getConnection().commit();
				this.db.getConnection().setAutoCommit(true);
				return true;
				
	        	
		}catch(Exception e){		
			geso.dms.center.util.Utility.rollback_throw_exception(db);
			this.msg="Ban Khong The Cap Nhat Kho ,Loi Dong Lenh Sau "+e.toString();
			return false;
		}
				
		
	}
	
	public boolean UpdateKho(){

		
		try
		{
			
			this.db.getConnection().setAutoCommit(false);
			
			if(!this.CheckVal())
			{
				geso.dms.center.util.Utility.rollback_throw_exception(db);
				return false;		
			}
			
			if(!checkMa(this.id, this.ten, this.db))
			{
				this.msg = " Tên kho đã tồn tại";
				geso.dms.center.util.Utility.rollback_throw_exception(db);
				return false;
			}
			
			
			String query = "update kho set ten =N'" + this.ten + "',  ngaysua = '" + this.ngaysua + "',  nguoisua = '" + this.nguoisua + "', trangthai = '" + this.trangthai + "', diengiai = N'" + this.diengiai + "' where pk_seq = '" + this.id + "'" ;

					if (!this.db.update(query)){
						geso.dms.center.util.Utility.rollback_throw_exception(db);
						this.msg = "Khong the luu vao table 'Kho'" ;
						return false;			
					}
	
						
				this.db.getConnection().commit();
				this.db.getConnection().setAutoCommit(true);
				return true;
				
	        	
		}catch(Exception e){		
			geso.dms.center.util.Utility.rollback_throw_exception(db);
			this.msg="Ban Khong The Cap Nhat Kho ,Loi Dong Lenh Sau "+e.toString();
			return false;
		}
		
		
	}

	@Override
	public void DBClose() 
	{		
		if(db!=null){
			db.shutDown();
		}
	}
		
		
}


