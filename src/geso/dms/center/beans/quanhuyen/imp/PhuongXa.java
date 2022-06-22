package geso.dms.center.beans.quanhuyen.imp;

import geso.dms.center.beans.quanhuyen.IPhuongXa;
import geso.dms.center.db.sql.*;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class PhuongXa implements IPhuongXa
{
	private static final long serialVersionUID = -9217977546733610214L;
	String userId;
	String id;
	String ma;
	String ten;
	String ttId;
	String qhId;
	ResultSet ttRs;
	ResultSet qhRs;
	
	dbutils db;
	private String msg;
	
	public PhuongXa()
	{
		this.db = new dbutils();
		this.id = "";
		this.ma = "";
		this.ten = "";
		this.ttId = "";
		this.qhId = "";
		this.msg = "";
	}

	@Override
	public String getUserId() {
		// TODO Auto-generated method stub
		return this.userId;
	}

	@Override
	public void setUserId(String userId) {
		this.userId = userId;
	}

	@Override
	public String getId() {
		// TODO Auto-generated method stub
		return this.id;
	}

	@Override
	public void setId(String id) {
		this.id = id;
	}

	@Override
	public String getMa() {
		// TODO Auto-generated method stub
		return this.ma;
	}

	@Override
	public void setMa(String value) {
		this.ma = value;
	}

	@Override
	public String getTen() {
		// TODO Auto-generated method stub
		return this.ten;
	}

	@Override
	public void setTen(String value) {
		this.ten = value;
	}

	@Override
	public String getMessage() {
		// TODO Auto-generated method stub
		return this.msg;
	}

	@Override
	public void setMessage(String msg) {
		this.msg = msg;
	}

	@Override
	public ResultSet getTtRs() {
		// TODO Auto-generated method stub
		return this.ttRs;
	}

	@Override
	public void setTtId(String value) {
		this.ttId = value;
	}

	@Override
	public String getTtId() {
		// TODO Auto-generated method stub
		return this.ttId;
	}

	@Override
	public ResultSet getQhRs() {
		// TODO Auto-generated method stub
		return this.qhRs;
	}

	@Override
	public void setQhId(String value) {
		this.qhId = value;
	}

	@Override
	public String getQhId() {
		// TODO Auto-generated method stub
		return this.qhId;
	}

	@Override
	public boolean Create() {
		if(this.qhId.length() == 0){
			this.msg = "Vui lòng chọn tỉnh thành / quận huyện";
			return false;
		}
		if(this.ma.length() == 0){
			this.msg = "Vui lòng nhập mã phường xã";
			return false;
		}
		if(this.ten.length() == 0){
			this.msg = "Vui lòng nhập tên phường xã";
			return false;
		}
		try {
			db.getConnection().setAutoCommit(false);
		
		String query = "Insert PhuongXa(MA, TEN, QUANHUYEN_FK) VALUES('"+this.ma+"', N'"+this.ten+"', '"+this.qhId+"')";
		if(!this.db.update(query)){
			this.msg = "Lỗi thêm phường xã";
			db.getConnection().rollback();
			return false;
		}
		query = " insert PHUONGXA_SMARTID(PHUONGXA_FK, MAPX, SMARTID) " +
				" select a.pk_seq,a.Ma,a.Ma + b.code from PhuongXa a inner join dbo.ufn_Code999() b on 1=1 " +
				" where a.pk_seq =  (select  pk_Seq from phuongxa where ma = '"+this.ma+"' and QUANHUYEN_FK = "+ this.qhId  +" ) " +
				" and not exists (select 1 from PHUONGXA_SMARTID x where x.PHUONGXA_FK = a.pk_seq)";
		if(!this.db.update(query)){
			this.msg = "Lỗi chen ma cuon ";
			db.getConnection().rollback();
			return false;
		}
		
		db.getConnection().commit();
		return true;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			db.update("rollback");
			e.printStackTrace();
			return false;
		}
		
		
		
	}

	@Override
	public boolean Update() {
		if(this.qhId.length() == 0){
			this.msg = "Vui lòng chọn tỉnh thành / quận huyện";
			return false;
		}
		if(this.ma.length() == 0){
			this.msg = "Vui lòng nhập mã phường xã";
			return false;
		}
		if(this.ten.length() == 0){
			this.msg = "Vui lòng nhập tên phường xã";
			return false;
		}
		try {
			db.getConnection().setAutoCommit(false);
		
			String query = "Update PhuongXa set TEN = N'"+this.ten+"' where PK_SEQ = " + this.id;
			if(!this.db.update(query)){
				this.msg = "Lỗi cập nhật phường xã";
				return false;
			}
			query = " insert PHUONGXA_SMARTID(PHUONGXA_FK, MAPX, SMARTID)" +
			" select a.pk_seq, a.Ma, a.Ma + b.code from PhuongXa a inner join dbo.ufn_Code999() b on 1=1 " +
			" where a.pk_seq =  " + this.id +
			"  			and not exists (select 1 from PHUONGXA_SMARTID x where x.PHUONGXA_FK = a.pk_seq) ";
			if(!this.db.update(query)){
				this.msg = "Lỗi chen ma cuon ";
				db.getConnection().rollback();
				return false;
			}
		
		db.getConnection().commit();
		return true;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			db.update("rollback");
			e.printStackTrace();
			return false;
		}
	}

	@Override
	public void closeDB() {
			try {
				if(qhRs != null)
				qhRs.close();
				if(ttRs != null)
					ttRs.close();
				db.shutDown();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	}

	@Override
	public void createRs() {
		try{
			String query = "select * from TINHTHANH where 1= 1";
			if(id.length() != 0)
				query += " and PK_SEQ = (SELECT TINHTHANH_FK FROM QUANHUYEN WHERE PK_SEQ = (SELECT QUANHUYEN_FK FROM PHUONGXA WHERE PK_SEQ = '"+this.id+"'))";
	
			this.ttRs = this.db.get(query);
			query = "select * from QUANHUYEN where 1= 1";
			if(this.ttId.length() > 0)
				query += " and TINHTHANH_FK = " + this.ttId;
			if(id.length() != 0)
				query += " and PK_SEQ = (SELECT QUANHUYEN_FK FROM PHUONGXA WHERE PK_SEQ = '"+this.id+"')";
			
			this.qhRs = this.db.get(query);
			
			if(this.qhId.length() > 0 && this.id.length() == 0){
				query = "select max(Ma) as Ma from PhuongXa where quanhuyen_fk = '"+this.qhId+"'";
				ResultSet rs = this.db.get(query);
				try {
					rs.next();
					int _ma = rs.getInt("Ma") + 1;
					this.ma = _ma + "";
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		catch (Exception e) {
			e.printStackTrace();
		}
	}

	private String getDateTime()
	{
		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		Date date = new Date();
		return dateFormat.format(date);
	}

	@Override
	public void Init() {
		String query = "select x.*, (select tinhthanh_fk from quanhuyen where pk_seq = x.quanhuyen_fk) as ttId from PHUONGXA x WHERE PK_sEQ = " + this.id;
		ResultSet rs = this.db.get(query);
		try {
			rs.next();
			this.ma = rs.getString("ma");
			this.ten = rs.getString("ten");
			this.qhId = rs.getString("quanhuyen_fk");
			this.ttId = rs.getString("ttId");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		createRs();
	}
}

