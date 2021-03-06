package geso.dms.center.beans.khuvuc.imp;

import geso.dms.center.beans.khuvuc.IKhuvuc;
import geso.dms.center.db.sql.*;
import geso.dms.center.util.Utility;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Khuvuc implements IKhuvuc
{
	public dbutils getDb() {
		return db;
	}
	private static final long serialVersionUID = -9217977546733610214L;

	String userId;
	String id;
	String ten;
	String vungmien;
	String diengiai;
	String trangthai;
	String ngaytao;
	String nguoitao;
	String ngaysua;
	String nguoisua;
	String msg;
	String asm;
	ResultSet asms;
	String  ttId = "";
	String QhId = "";
	String Ma  = "";

	ResultSet ttRs,QHRs ;
	public String getQhId() {
		return QhId;
	}

	public void setQhId(String qhId) {
		QhId = qhId;
	}

	public ResultSet getQHRs() {
		return QHRs;
	}
	ResultSet vungmienlist;
	String vmId;

	dbutils db;

	public Khuvuc(String[] param)
	{
		this.id = param[0];
		this.ten = param[1];
		this.vungmien = param[2];
		this.trangthai = param[3];
		this.ngaytao = param[4];
		this.nguoitao = param[5];
		this.ngaysua = param[6];
		this.nguoisua = param[7];
		this.vmId = param[8];
		this.diengiai = param[9];
		this.Ma = param[10];
		this.msg = "";
		
	}

	public Khuvuc(String id)
	{
		this.id = id;
		this.ten = "";
		this.vungmien = "";
		this.diengiai = "";
		this.trangthai = "2";
		this.ngaytao = "";
		this.nguoitao = "";
		this.ngaysua = "";
		this.nguoisua = "";
		this.vmId = "";
		this.asm ="";
		this.msg = "";
		this.db = new dbutils();
		if(id.length() > 0)
			this.init();
		else
			this.createVmRS();
		creasms();
	}
	public String getMa() {
		return Ma;
	}

	public void setMa(String ma) {
		Ma = ma;
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

	public String getVungmien() 
	{
		return this.vungmien;
	}

	public void setVungmien(String vungmien) 
	{
		this.vungmien = vungmien;
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

	public String getVmId() 
	{
		return this.vmId;
	}

	public void setVmId(String vmId)
	{
		this.vmId = vmId;
	}

	public ResultSet getVungMienlist() 
	{
		return this.vungmienlist;
	}

	public void setVungmienlist(ResultSet vungmienlist) 
	{
		this.vungmienlist = vungmienlist;
	}

	public void createVmRS() 
	{
		ResultSet vmRS = this.db.get("select pk_seq as vmId, ten as vmTen from vung where trangthai='1' order by ten");

		this.vungmienlist = vmRS;

		ResultSet ttrs = this.db.get("select pk_seq, ten  from tinhthanh where trangthai='1' order by ten");

		this.ttRs = ttrs;
		/*		this.QHRs = this.db.get("select pk_seq, ten  from quanhuyen where trangthai='1' and tinhthanh_fk in (100049,100000) order by ten");*/	
		System.out.println("this.ttid lay dc: " + this.ttId);
		if(this.ttId.trim().length() >0 && (this.ttId.contains("100049")||this.ttId.contains("100000"))){
			String query = "select pk_seq, ten  from quanhuyen where trangthai='1' and tinhthanh_fk in ("+this.ttId+") order by ten";
			System.out.println("lay quan huyen: " + query);
			this.QHRs = this.db.get(query);
		}
	}

	public boolean CreateKv() 
	{
		try{
			this.db.getConnection().setAutoCommit(false);
			this.ngaytao = getDateTime();
			this.nguoitao = this.userId;
			String command = "";
			Object[] data;

			if(this.asm==null || this.asm.equals("")){
				command = "\n insert into khuvuc(ma,ten, ngaytao, ngaysua, nguoitao, nguoisua, vung_fk, trangthai, diengiai)";
				command = command + "\n values(N'"+Ma+"',N'" + this.ten + "','" + this.ngaytao + "','" + this.ngaytao + "',				'" + this.nguoitao + "','" + this.nguoitao + "','" + this.vmId + "','1', N'" + this.diengiai + "')";
				

			} else {
				command = "\n insert into khuvuc(ma,ten, ngaytao, ngaysua, nguoitao, nguoisua, vung_fk, trangthai, diengiai,asm_fk)";
				command = command + "\n values(N'"+Ma+"',N'" + this.ten + "','" + this.ngaytao + "','" + this.ngaytao + "','" + 
				this.nguoitao + "','" + this.nguoitao + "','" + this.vmId + "','1', N'" + this.diengiai + "','"+ this.asm +"')";
						}
			
			if( this.db.updateReturnInt(command)!=1 ) {
				//this.msg = command;
				this.msg="L???i trong qu?? tr??nh l??u d??? li???u, vui l??ng li??n h??? admin ????? x??? l??";
				geso.dms.center.util.Utility.rollback_throw_exception(db);
				return false;
			}
			ResultSet Rs = db.get("select scope_identity() as id");
			String id = "";
			try {
				if(Rs != null)
				{
					Rs.next();
					id = Rs.getString("id");
					Rs.close();
				}
			} catch (SQLException e) {

				e.printStackTrace();
			}

			if(this.ttId.length() > 0)
			{
				String query = "insert into khuvuc_quanhuyen(khuvuc_fk,quanhuyen_fk,TT_FK) "
					+ "\n select '"+id+"',pK_seq,tinhthanh_fk from quanhuyen where tinhthanh_fk in ("+this.ttId+") and tinhthanh_fk not in (100049,100000) ";
				if (!db.update(query)){
					//if( this.db.updateQueryByPreparedStatement(query, data)!=1 ) {
					this.msg ="L???i kh??ng th??m ???????c";
					geso.dms.center.util.Utility.rollback_throw_exception(db);
					return false;
				}
			}
			if(this.QhId.length() > 0)
			{
				String query = "insert into khuvuc_quanhuyen(khuvuc_fk,quanhuyen_fk,TT_FK) "
					+ "\n select  '"+id+"', pK_seq,tinhthanh_fk from quanhuyen where pk_seq in ("+this.QhId+")  ";
				if (!db.update(query)){
					this.msg ="2.L???i kh??ng th??m ???????c";
					geso.dms.center.util.Utility.rollback_throw_exception(db);
					return false;
				}
			}

			this.db.getConnection().commit();
			this.db.getConnection().setAutoCommit(true);
		}catch(Exception er){
			geso.dms.center.util.Utility.rollback_throw_exception(this.db);
			this.msg="Khong the cap nhat lai bang nay,xay ra loi trong dong lenh sau "+ er.toString();
			return false;
		}
		return true;
	}

	public boolean UpdateKv() 
	{
		this.ngaysua = getDateTime();
		this.nguoisua = this.userId;
		String command="";
		Object[] data;

		try {
			db.getConnection().setAutoCommit(false);

			String VUNG = "NULL";
			if(this.vmId !=null && this.vmId.trim().length() > 0)
				VUNG = this.vmId;
			String asmInsert = this.asm==null||this.asm.equals("") ? "NULL" : this.asm;
			
			command ="update khuvuc set  vung_fk = "+VUNG+",trangthai="+this.trangthai+", ngaysua ='"+this.ngaysua+"'" +
			"	, asm_fk ="+asmInsert+", nguoisua = "+this.userId+","
		+ " ma =N'"+this.Ma+"', ten=N'"+this.ten+"', diengiai=N'"+this.diengiai+"' where pk_seq ="+ this.id;
			if( this.db.updateReturnInt(command)!=1 ) {
				//this.msg = command;
				this.msg="L???i trong qu?? tr??nh l??u d??? li???u, vui l??ng li??n h??? admin ????? x??? l??";
				db.getConnection().rollback();
				return false;
			}
			command = "delete from khuvuc_quanhuyen where khuvuc_fk = '"+this.id+"'";
			if (!db.update(command)){
				//this.msg = command;
				this.msg="Vui l??ng li??n h??? admin";
				db.getConnection().rollback();
				return false;
			}


			if(this.ttId.length() > 0)
			{
				String query = "insert into khuvuc_quanhuyen(khuvuc_fk,quanhuyen_fk,TT_FK) "
					+ "\n select '"+this.id+"',pK_seq,tinhthanh_fk from quanhuyen where tinhthanh_fk in ("+this.ttId+") and tinhthanh_fk not in (100049,100000) ";
				System.out.println("Insert khuvuc_quanhuyen: "+query);
				if (!db.update(query)){		
					this.msg ="L???i kh??ng th??m ???????c";
					Utility.rollback_throw_exception(db);
					return false;
				}
			}
			if(this.QhId.length() > 0)
			{
				String query = "insert into khuvuc_quanhuyen(khuvuc_fk,quanhuyen_fk,TT_FK) "
					+ "\n select  '"+this.id+"', pK_seq,tinhthanh_fk from quanhuyen x where pk_seq in ("+this.QhId+")" +
					  "\n  and not exists (  select 1 from khuvuc_quanhuyen y where x.pk_seq  = y.quanhuyen_fk and y.khuvuc_fk = "+this.id+" ) ";

				if (!db.update(query)){	
					this.msg ="L???i kh??ng th??m ???????c";
					Utility.rollback_throw_exception(db);
					return false;
				}
			}
			db.getConnection().commit();
			return true; 
			
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			this.msg ="Exception";
			Utility.rollback_throw_exception(db);
			return false;
		}
	}

	private void init()
	{	
		String query = "select a.pk_seq as id,isnull(a.ma,'') as ma, a.ten as kvTen, a.trangthai as trangthai, b.pk_seq as vmId, b.ten as vmTen, c.ten as nguoitao, a.ngaytao as ngaytao, d.ten as nguoisua, a.ngaysua as ngaysua, a.diengiai,a.asm_fk";
		query = query + " from khuvuc a, vung b, nhanvien c, nhanvien d";
		query = query + " where a.vung_fk=b.pk_seq and a.nguoitao = c.pk_seq and a.nguoisua = d.pk_seq and a.pk_seq = '" + this.id + "'";
		ResultSet rs =  this.db.get(query);
		try{
			rs.next();        	
			this.id = rs.getString("id");
			this.ten = rs.getString("kvTen");
			this.vungmien = rs.getString("vmTen");
			this.trangthai = rs.getString("trangthai");
			this.ngaytao = rs.getString("ngaytao");
			this.nguoitao = rs.getString("nguoitao");
			this.ngaysua = rs.getString("ngaysua");
			this.nguoisua = rs.getString("nguoisua");
			this.vmId = rs.getString("vmId");
			this.diengiai = rs.getString("diengiai");
			this.asm = rs.getString("asm_fk");
			this.Ma =rs.getString("Ma");
		}catch(Exception e){}
		query = " select distinct b.tinhthanh_fk from khuvuc_quanhuyen a inner join quanhuyen b on a.quanhuyen_fk = b.pk_seq"
			+ " where khuvuc_fk = '"+this.id+"'";
		System.out.println("Init Tinh: "+query);
		rs = db.get(query);
		try{
			if(rs != null)
				while(rs.next())
				{
					this.ttId += rs.getString("tinhthanh_fk")+",";
				}

			if(this.ttId.length() > 0)
				this.ttId = this.ttId.substring(0,this.ttId.length() -1);

		}catch(Exception e){
			e.printStackTrace();
		}
		query = " select  b.pk_seq from khuvuc_quanhuyen a inner join quanhuyen b on a.quanhuyen_fk = b.pk_seq"
			+ " where khuvuc_fk = '"+this.id+"' and tinhthanh_fk in( 100049,100000) ";
		System.out.println("Init huyen: "+query);
		rs = db.get(query);
		try{
			if(rs != null)
				while(rs.next())
				{
					this.QhId += rs.getString("pk_seq")+",";
				}
			if(this.QhId.length() > 0)
				this.QhId = this.QhId.substring(0,this.QhId.length() -1);
		}catch(Exception e){
			e.printStackTrace();
		}
		createVmRS(); 

	}
	private String getDateTime() 
	{
		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		Date date = new Date();
		return dateFormat.format(date);	
	}


	public void setAsm(String asm) {

		this.asm = asm;
	}


	public String getAsm() {

		return this.asm;
	}


	public void setAsms(ResultSet asms) {

		this.asms = asms;
	}


	public ResultSet getAsms() {

		return this.asms;
	}
	public String getTtId() {
		return ttId;
	}

	public void setTtId(String ttId) {
		this.ttId = ttId;
	}

	public ResultSet getTtRs() {
		return ttRs;
	}
	void creasms()
	{
		String sql = "select * from asm ";
		asms = db.get(sql);
	}


}
