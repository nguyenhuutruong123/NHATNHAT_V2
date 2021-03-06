package geso.dms.center.beans.phuongxa.imp;

import geso.dms.center.beans.phuongxa.*;
import geso.dms.center.db.sql.*;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Phuongxa implements IPhuongxa
{
	private static final long serialVersionUID = -9217977546733610214L;

	String userId;
	String id;
	
	String pxId, diengiai, trangthai, ttId, qhId, msg, nguoitao, nguoisua, ngaytao, ngaysua;
	ResultSet ttlist, qhlist;
	
	dbutils db;
	
	public Phuongxa(String[] param)
	{
		/*this.id = param[0];
		this.ten = param[1];
		this.vungmien = param[2];
		this.trangthai = param[3];
		this.ngaytao = param[4];
		this.nguoitao = param[5];
		this.ngaysua = param[6];
		this.nguoisua = param[7];
		this.vmId = param[8];
		this.diengiai = param[9];*/
		this.msg = "";
		this.db = new dbutils();
	}
	
	public Phuongxa(String id)
	{
		this.id = id;

		this.ttId = "";
		this.qhId = "";
		
		this.diengiai = "";
		this.trangthai = "1";
		this.ngaytao = "";
		this.nguoitao = "";
		this.ngaysua = "";
		this.nguoisua = "";
		
		this.msg = "";
		this.db = new dbutils();
		if(id.length() > 0)
			this.init();
		else
			this.createRS();
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
	
	private void init()
	{	
		String query = 
			" SELECT PX.pk_Seq AS PXID, PX.Ten AS PXTEN, PX.TRANGTHAI, px.tinhthanh_fk AS TTID, px.quanhuyen_fk AS QHID FROM PhuongXa PX " +
			" WHERE PX.PK_SEQ = '"+ this.id +"' ";
		
		//System.out.println("px init : "+query);
        ResultSet rs =  this.db.get(query);
        try
        {
            rs.next();        	
        	this.id = rs.getString("PXID");
        	this.diengiai = rs.getString("PXTEN");
        	this.ttId = rs.getString("TTID");
        	this.qhId = rs.getString("QHID");
        	this.trangthai = rs.getString("TRANGTHAI");
       	}
        catch(Exception e){}
       	createRS(); 
       	
	}
	private String getDateTime() 
	{
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date date = new Date();
        return dateFormat.format(date);	
	}

	@Override
	public ResultSet getTinhthanhRs() {
		
		return this.ttlist;
	}

	@Override
	public void setTinhthanhRs(ResultSet ttRs) {
		
		this.ttlist = ttRs;
	}

	@Override
	public String getTinhthanhId() {
		
		return this.ttId;
	}

	@Override
	public void setTinhthanhId(String ttId) {
		
		this.ttId = ttId;
	}

	@Override
	public ResultSet getQuanhuyenRs() {
		
		return this.qhlist;
	}

	@Override
	public void setQuanhuyenRs(ResultSet qhRs) {
		
		this.qhlist = qhRs;
	}

	@Override
	public String getQuanhuyenId() {
		
		return this.qhId;
	}

	@Override
	public void setQuanhuyenId(String qhId) {
		
		this.qhId = qhId;
	}

	@Override
	public boolean CreatePx() {
		try
		{
			this.db.getConnection().setAutoCommit(false);
			this.ngaytao = getDateTime();
			this.nguoitao = this.userId;
			String command="";
			command = "INSERT INTO PHUONGXA (TEN, QUANHUYEN_FK, TINHTHANH_FK, NGAYTAO, NGAYSUA, NGUOITAO, NGUOISUA, TRANGTHAI) " +
		    "VALUES (N'" + this.diengiai + "', '"+ this.qhId +"', '"+ this.ttId +"', '" + this.ngaytao + "','" + this.ngaytao + "','" + this.nguoitao + "','" + this.nguoitao + "','" + this.trangthai + "')"; 
			if (!db.update(command))
			{
				this.msg = "Exception 2";
				this.db.getConnection().rollback();
				return false;
			}
		
			String id = "";
			ResultSet rsId = db.get("select scope_identity() as ID ");
			if(rsId.next())
			{
				id = rsId.getString("ID");
			}
			rsId.close();
			//System.out.println("id: "+id);
			command = "update PhuongXa set diengiai = cast(TinhThanh_FK as varchar) + '-' + cast(QuanHuyen_FK as varchar) + '-' + dbo.ftBoDau(Ten) where pk_seq = '"+ id +"'";
			if( this.db.updateReturnInt(command) != 1 ) 
			{
				this.msg =  "Exception 3";;
				this.db.getConnection().rollback();
				return false;
			}
		
			command = "SELECT diengiai AS DG FROM PHUONGXA WHERE PK_SEQ = '"+ id +"' ";
			//System.out.println("command : "+command);
			ResultSet rsspell = db.get(command);
			rsspell.next();
	
			Integer str = 0;
			String query =
			"select COUNT(*) as num from ( " +
			"select pk_seq from phuongxa where diengiai = '"+ rsspell.getString("DG") +"' and pk_seq != '"+ id +"' ) haha ";

			rsspell.close();
			//System.out.println("CHECK : "+query);
			ResultSet rscheck = db.get(query);
			if(rscheck != null)
			{
				rscheck.next();
				str = rscheck.getInt("num");
				//System.out.println("str : "+str);
				if(str > 0)
				{
					this.msg = "???? c?? th??ng tin ph?????ng x?? n??y trong h??? th???ng. Vui l??ng nh???p l???i !";
					this.db.getConnection().rollback();
					return false;
				}
			}
			rscheck.close();
			this.db.getConnection().commit();
		}catch(Exception er)
		{
			try { this.db.getConnection().rollback(); } catch (Exception e) { e.printStackTrace(); }
			this.msg="Khong the cap nhat lai bang nay,xay ra loi trong dong lenh sau "+ er.toString();
			return false;
		}
		finally{ try { this.db.getConnection().setAutoCommit(true); } catch (SQLException e) { e.printStackTrace(); } }
		return true;
	}

	@Override
	public boolean UpdatePx() 
	{
		try
		{
			this.db.getConnection().setAutoCommit(false);
			this.ngaysua = getDateTime();
			this.nguoisua = this.userId;
			String command="";
			Object[] data;
			command = "UPDATE PHUONGXA SET TEN = N'"+ this.diengiai +"', QUANHUYEN_FK = '"+ this.qhId +"', TINHTHANH_FK = '"+ this.ttId +"', " +
					  "TRANGTHAI = '"+ this.trangthai +"', NGAYSUA = '"+ this.ngaysua +"', NGUOISUA = '"+ this.nguoisua +"' WHERE PK_SEQ = '"+ this.id +"' ";
			if( this.db.updateReturnInt(command)!=1 ) 
			{
				this.msg = "L???i trong qu?? tr??nh l??u d??? li???u, vui l??ng li??n h??? admin ????? x??? l??";
				this.db.getConnection().rollback();
				return false;
			}
			
			command = "update PhuongXa set diengiai = cast(TinhThanh_FK as varchar) + '-' + cast(QuanHuyen_FK as varchar) + '-' + dbo.ftBoDau(Ten) where pk_seq = '"+ this.id +"'";
			if( this.db.updateReturnInt(command)!=1 ) 
			{
				this.msg = "L???i trong qu?? tr??nh l??u d??? li???u, vui l??ng li??n h??? admin ????? x??? l??";
				this.db.getConnection().rollback();
				return false;
			}
			
			command = "SELECT diengiai AS DG FROM PHUONGXA WHERE PK_SEQ = '"+ this.id +"' ";
			//System.out.println("command : "+command);
			ResultSet rsspell = db.get(command);
			rsspell.next();
	
			Integer str = 0;
			String query =
			"select COUNT(*) as num from ( " +
			"select pk_seq from phuongxa where diengiai = '"+ rsspell.getString("DG") +"' and pk_seq != '"+ this.id +"' ) haha ";
	
			rsspell.close();
			//System.out.println("CHECK : "+query);
			ResultSet rscheck = db.get(query);
			if(rscheck != null)
			{
				rscheck.next();
				str = rscheck.getInt("num");
				//System.out.println("str : "+str);
				if(str > 0)
				{
					this.msg = "???? c?? th??ng tin ph?????ng x?? n??y trong h??? th???ng. Vui l??ng nh???p l???i !";
					this.db.getConnection().rollback();
					return false;
				}
			}
			rscheck.close();
			this.db.getConnection().commit();
		}
		catch(Exception er)
		{
			try { this.db.getConnection().rollback(); } catch (SQLException e) { e.printStackTrace(); }
			this.msg="Khong the cap nhat lai bang nay,xay ra loi trong dong lenh sau "+ er.toString();
			return false;
		}
		finally{ try { this.db.getConnection().setAutoCommit(true); } catch (SQLException e) { e.printStackTrace(); } }
		return true;
	}

	@Override
	public void createRS() {
		
		this.ttlist = db.get("select * from tinhthanh");
		
		String sql = "select * from quanhuyen";
		if(!this.ttId.equals(""))
		{
			sql += " where TINHTHANH_FK = '"+ this.ttId +"' ";
		}
		this.qhlist = db.get(sql);
	}
	
	
}
