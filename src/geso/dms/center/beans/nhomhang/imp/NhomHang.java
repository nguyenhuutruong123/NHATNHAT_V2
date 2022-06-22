package geso.dms.center.beans.nhomhang.imp;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import geso.dms.center.beans.nhomhang.INhomHang;
import geso.dms.center.db.sql.dbutils;

public class NhomHang implements INhomHang
{
	
	public NhomHang(String id)
	{
		super();
		this.id = id;
		this.userId = "";
		this.ma = "";
		this.ten = "";
		this.loainhom = "";
		this.msg = "";
		this.spId="";
		this.db = new dbutils();
	}

	public NhomHang()
	{
		this.id = "";
		this.ma = "";
		this.ten = "";
		this.userId = "";
		this.loainhom = "2";
		this.msg = "";
		this.spId="";
		this.db = new dbutils();
	}

	

	String dvdlId,id, userId, ma, ten, loainhom, msg,nganhHangId,nhanHangId;
	ResultSet nganhHangRs,nhanhangRs,dvdlRs;
	
	public String getDvdlId()
  {
  	return dvdlId;
  }

	public void setDvdlId(String dvdlId)
  {
  	this.dvdlId = dvdlId;
  }

	public ResultSet getDvdlRs()
  {
  	return dvdlRs;
  }

	public void setDvdlRs(ResultSet dvdlRs)
  {
  	this.dvdlRs = dvdlRs;
  }

	public String getNganhHangId()
  {
  	return nganhHangId;
  }

	public void setNganhHangId(String nganhHangId)
  {
  	this.nganhHangId = nganhHangId;
  }







	dbutils db;
	
	public String getId()
	{
		return id;
	}

	public void setId(String id)
	{
		this.id = id;
	}



	public String getUserId()
	{
		return userId;
	}

	public void setUserId(String userId)
	{
		this.userId = userId;
	}

	public String getMa()
	{
		return ma;
	}

	public void setMa(String ma)
	{
		this.ma = ma;
	}

	public String getTen()
	{
		return ten;
	}

	public void setTen(String ten)
	{
		this.ten = ten;
	}

	@Override
	public String getMsg()
	{

		return this.msg;
	}

	@Override
	public void setMsg(String msg)
	{
		this.msg = msg;
	}

	@Override
	public boolean save()
	{
		try
		{
			this.db.getConnection().setAutoCommit(false);
			ResultSet rsCheck = db.get("select COUNT(PK_SEQ) as sodong from NhomHang where  upper(ma) = upper('" + this.ma + "')");
			boolean chk = true;
			if(rsCheck != null)
			{
				if(rsCheck.next())
				{
					if(rsCheck.getInt("sodong") > 0)
					{
						chk = false;
					}
					rsCheck.close();
				}
			}
			if(!chk)
			{
				this.msg = "Mã (" + this.ma + ") bạn muốn cập nhật đã tồn tại trong hệ thống, vui lòng nhập mã khác.";
				this.db.getConnection().rollback();
				return false;
			}
			
			String query=
				"insert into NhomHang(ma,ten,trangthai,nguoitao,ngaytao,nguoisua,ngaysua)" +
				" select N'"+this.ma+"',N'"+this.ten+"',0,'"+this.userId+"','"+this.getDateTime()+"','"+this.userId+"','"+this.getDateTime()+"'  ";
			
			if(!this.db.update(query))
			{
				this.msg="Exception";
				this.db.getConnection().rollback();
				return false;
			}
			query = "select SCOPE_IDENTITY() as nhomId";
			ResultSet rs=this.db.get(query);
			while(rs.next()) {	this.id=rs.getString("nhomId"); }
			rs.close();
			
			if(this.spId.length()>0)
			{
				query="insert into nhomhang_sanpham(nhomhang_fk,sanpham_fk)" +
						" select '"+this.id+"',pk_seq" +
						" from sanpham where pk_seq in ("+this.spId+")  ";
				if(!this.db.update(query))
				{
					this.msg="Exception";
					this.db.getConnection().rollback();
					return false;
				}
			}
			else
			{
				this.msg="Vui lòng chọn sản phẩm.";
				this.db.getConnection().rollback();
				return false;
			}
			this.db.getConnection().commit();
		} catch (Exception e) {  try { this.db.getConnection().rollback();  e.printStackTrace();  } catch (SQLException e1) { e1.printStackTrace(); } }
		finally{ try { this.db.getConnection().setAutoCommit(true); } catch (SQLException e) { e.printStackTrace(); } }
		return true;
	}

	@Override
	public boolean edit()
	{
		try
		{
			this.db.getConnection().setAutoCommit(false);
			ResultSet rsCheck = db.get("select COUNT(PK_SEQ) as sodong from NhomHang where pk_seq != '" + this.id + "' and upper(ma) = upper('" + this.ma + "')");
			boolean chk = true;
			if(rsCheck != null)
			{
				if(rsCheck.next())
				{
					if(rsCheck.getInt("sodong") > 0)
					{
						chk = false;
					}
					rsCheck.close();
				}
			}
			if(!chk)
			{
				this.msg = "Mã đã tồn tại, vui lòng nhập lại";
				this.db.getConnection().rollback();
				return false;
			}
			
			String query="update NhomHang  set Ma=N'"+this.ma+"', Ten=N'"+this.ten+"',NguoiSua='"+this.userId+"',NgaySua='"+getDateTime()+"' where pk_Seq='"+this.id+"'" ;
			if(!this.db.update(query))
			{
				this.msg="Exception";
				this.db.getConnection().rollback();
				return false;
			}
			
			if(this.spId.length()>0)
			{
				query="delete from nhomhang_sanpham where nhomhang_fk='"+this.id+"'";
				if(!this.db.update(query))
				{
					this.msg="Exception";;
					this.db.getConnection().rollback();
					return false;
				}
				
				query="insert into nhomhang_sanpham(nhomhang_fk,sanpham_fk)" +
						" select '"+this.id+"',pk_seq" +
						" from sanpham where pk_seq in ("+this.spId+")  ";
				if(!this.db.update(query))
				{
					this.msg="Exception";
					this.db.getConnection().rollback();
					return false;
				}
			}
			
			else
			{
				this.msg="Vui lòng chọn sản phẩm.";
				this.db.getConnection().rollback();
				return false;
			}
			this.db.getConnection().commit();
		} catch (Exception e) {  try { this.db.getConnection().rollback();  e.printStackTrace();  } catch (SQLException e1) { e1.printStackTrace(); } }
		finally{ try { this.db.getConnection().setAutoCommit(true); } catch (SQLException e) { e.printStackTrace(); } }
		return true;
	}

	@Override
	public void closeDB()
	{

	}

	@Override
	public void init()
	{
		String query="select ma,ten,trangthai from NhomHang where pk_seq='"+this.id+"'";
		ResultSet rs=this.db.get(query);
		try
		{
			while(rs.next())
			{
				this.ma=rs.getString("ma");
				this.ten=rs.getString("ten");
				this.loainhom="2";
			}rs.close();
			createRs();
			query="select nhomhang_fk,sanpham_fk from nhomhang_sanpham where  nhomhang_fk='"+this.id+"'";
			
			rs=this.db.get(query);
			while(rs.next())
			{
				this.spId+=rs.getString("sanpham_fk")+",";
			}
			if(spId.length()>0)
			{
				spId=spId.substring(0,spId.length()-1);
			}
			
		} catch (SQLException e)
		{
			e.printStackTrace();
		}		
	}

	public void createRs()
	{
		//System.out.println("createRs");
		String query=
				" select a.PK_SEQ as spId,a.MA as spMA,a.TEN as spTEN,b.DONVI as spDonVi " +
				" from sanpham a left join DONVIDOLUONG b on b.PK_SEQ = a.DVDL_FK order by a.ten ";
		this.spRs=this.db.get(query);
		
		if(this.id.length() > 0)
		{
			query =
				" select a.PK_SEQ as spId,a.MA as spMA,a.TEN as spTEN,b.DONVI as spDonVi " +
				" from sanpham a left join DONVIDOLUONG b on b.PK_SEQ = a.DVDL_FK " +
				" where exists ( select 1 from nhomhang_sanpham where nhomhang_fk = '"+ this.id +"' and sanpham_fk = a.pk_seq ) " +
				" order by a.ten ";
			this.spRsDisplay=this.db.get(query);
		}
	}
	private String getDateTime() 
	{
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date date = new Date();
        return dateFormat.format(date);	
	}
	
	public String getLoainhom()
  {
  	return loainhom;
  }

	public void setLoainhom(String loainhom)
  {
  	this.loainhom = loainhom;
  }

	
	public String getNhanHangId()
  {
  	return nhanHangId;
  }

	public void setNhanHangId(String nhanHangId)
  {
  	this.nhanHangId = nhanHangId;
  }

	public ResultSet getNganhHangRs()
  {
  	return nganhHangRs;
  }

	public void setNganhHangRs(ResultSet nganhHangRs)
  {
  	this.nganhHangRs = nganhHangRs;
  }

	public ResultSet getNhanhangRs()
  {
  	return nhanhangRs;
  }

	public void setNhanhangRs(ResultSet nhanhangRs)
  {
  	this.nhanhangRs = nhanhangRs;
  }
	
	String spId;

	public String getSpId()
  {
  	return spId;
  }

	public void setSpId(String spId)
  {
  	this.spId = spId;
  }
	
	ResultSet spRs;
	public ResultSet getSpRs()
	{
		return this.spRs;
	}

	public void setSpRs(ResultSet spRs)
	{
		this.spRs = spRs;
	}

	ResultSet spRsDisplay;
	@Override
	public ResultSet getSpRsDisplay() {
		return this.spRsDisplay;
	}

	@Override
	public void setSpRsDisplay(ResultSet spRsdisplay) {
		this.spRsDisplay = spRsdisplay;
	}
}
