package geso.dms.center.beans.hanmucdoanhthu.imp;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Enumeration;
import java.util.Hashtable;

import geso.dms.center.beans.hanmucdoanhthu.Ihanmucdoanhthu;
import geso.dms.center.db.sql.dbutils;

public class hanmucdoanhthu implements Ihanmucdoanhthu {

	private static final long serialVersionUID = -9217977546733610214L;
	String tungay;
	String denngay;
	String nppid;
	String hanmuc;
	String msg;
	String Id;

	

	Hashtable<String, String> Hhanmuc;
	ResultSet Rsnppid;
	ResultSet Rshanmuc;
	

	dbutils db;
	public hanmucdoanhthu(){
		this.tungay="";
		this.denngay="";
		this.nppid="";
		this.hanmuc="";
		this.db=new dbutils();
		this.msg="";
		this.Id="";
	}
	
	
	
	public void init()
	{
		String sql="select pk_seq ,ten from nhaphanphoi where pk_seq!=1";
		this.Rsnppid=db.get(sql);
		sql="select pk_seq , tungay,denngay,trangthai from hanmucdoanhthu";
		this.Rshanmuc=db.get(sql);
		System.out.println("sql :"+sql);
	}
	public void init(String id)
	{
		String sql="select a.pk_seq ,a.ten,b.tienhanmuc from nhaphanphoi a inner join hanmucdoanhthu_chitiet b on a.pk_seq=b.nppId  where loainpp!=0 and b.hanmuc_fk="+id;
		this.Rsnppid=db.get(sql);
		sql="select pk_seq , tungay,denngay,trangthai from hanmucdoanhthu where pk_seq="+this.Id;
		this.Rshanmuc=db.get(sql);
		try {
			if(this.Rshanmuc.next())
			{
				this.tungay=this.Rshanmuc.getString("tungay");
				this.denngay=this.Rshanmuc.getString("denngay");
			
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("sql :"+sql);
	}
	
	public boolean save()
	{
		try {
			this.db.getConnection().setAutoCommit(false);
			
			String sql="select count(*) as sl from hanmucdoanhthu where '"+this.tungay+"' <= denngay and '"+this.denngay+"' >= tungay ";
			ResultSet rssl=db.get(sql);
			if(rssl.next())
			{
				if(rssl.getInt("sl")>0)
				{
					this.msg="thời gian này đã được khai hạn mức ";
					this.db.getConnection().rollback();
					return false;
				}
			}
			 sql="insert into hanmucdoanhthu (tungay,denngay,trangthai) values ('"+this.tungay+"','"+this.denngay+"',0) ";
			 System.out.println("iiiiiiiiiiiiiii"+sql);
			if(db.updateReturnInt(sql)<=0)
			{
				this.msg="loi "+sql;
				this.db.getConnection().rollback();
				return false;
			}
		
			Enumeration e = this.Hhanmuc.keys();
			while (e.hasMoreElements())
			{
			      String key = (String) e.nextElement();
			      sql="insert into hanmucdoanhthu_chitiet (hanmuc_fk,nppId,tienhanmuc) values (IDENT_CURRENT('hanmucdoanhthu'),"+key+","+this.Hhanmuc.get(key)+") ";
			      if(db.updateReturnInt(sql)<=0)
					{
						this.msg="loi "+sql;
						this.db.getConnection().rollback();
						return false;
					}
			}
			db.getConnection().commit();
			db.getConnection().setAutoCommit(true);
			return true;
			
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
		
	}
	
	public boolean update()
	{
		try {
			this.db.getConnection().setAutoCommit(false);
			
			String sql="select count(*) as sl from hanmucdoanhthu where '"+this.tungay+"' <= denngay and '"+this.denngay+"' >= tungay and pk_seq!="+this.Id;
			System.out.println("SQL:"+sql);
			ResultSet rssl=db.get(sql);
			if(rssl.next())
			{
				if(rssl.getInt("sl")>0)
				{
					this.msg="thời gian này đã được khai hạn mức ";
					this.db.getConnection().rollback();
					return false;
				}
			}
			
			 sql="update  hanmucdoanhthu set tungay='"+this.tungay+"',denngay='"+this.denngay+"' where pk_seq="+this.Id;
			if(db.updateReturnInt(sql)<=0)
			{
				this.msg="loi "+sql;
				this.db.getConnection().rollback();
				return false;
			}
			sql="delete from hanmucdoanhthu_chitiet where hanmuc_fk="+this.Id;
			if(db.updateReturnInt(sql)<=0)
			{
				this.msg="loi "+sql;
				this.db.getConnection().rollback();
				return false;
			}
			Enumeration e = this.Hhanmuc.keys();
			while (e.hasMoreElements())
			{
			      String key = (String) e.nextElement();
			      sql="insert into hanmucdoanhthu_chitiet (hanmuc_fk,nppId,tienhanmuc) values ("+this.Id+","+key+","+this.Hhanmuc.get(key)+") ";
			      if(db.updateReturnInt(sql)<=0)
					{
						this.msg="loi "+sql;
						this.db.getConnection().rollback();
						return false;
					}
			}
			db.getConnection().commit();
			db.getConnection().setAutoCommit(true);
			return true;
			
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
		
	}
	
	
	public boolean delete()
	{
		try {
			this.db.getConnection().setAutoCommit(false);
			
		
		
			String sql="delete from hanmucdoanhthu_chitiet where hanmuc_fk="+this.Id;
			System.out.println("sql delete"+sql);
			
			if(db.updateReturnInt(sql)<=0)
			{
				this.msg="loi "+sql;
				this.db.getConnection().rollback();
				return false;
			}
			
			 sql="delete from hanmucdoanhthu where pk_seq="+this.Id;
			if(db.updateReturnInt(sql)<=0)
			{
				this.msg="loi "+sql;
				this.db.getConnection().rollback();
				return false;
			}
			
			db.getConnection().commit();
			db.getConnection().setAutoCommit(true);
			return true;
			
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
		
	}
	
	
	public boolean chot()
	{
		try {
			this.db.getConnection().setAutoCommit(false);
			
		
		
			
			String  sql="update  hanmucdoanhthu set trangthai=1 where pk_seq="+this.Id;
			if(db.updateReturnInt(sql)<=0)
			{
				this.msg="loi "+sql;
				this.db.getConnection().rollback();
				return false;
			}
			
			db.getConnection().commit();
			db.getConnection().setAutoCommit(true);
			return true;
			
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
		
	}
	
	public String getTungay() {
		return tungay;
	}

	public void setTungay(String tungay) {
		this.tungay = tungay;
	}

	public String getDenngay() {
		return denngay;
	}

	public void setDenngay(String denngay) {
		this.denngay = denngay;
	}

	public String getNppid() {
		return nppid;
	}

	public void setNppid(String nppid) {
		this.nppid = nppid;
	}

	public String getHanmuc() {
		return hanmuc;
	}

	public void setHanmuc(String hanmuc) {
		this.hanmuc = hanmuc;
	}

	public Hashtable<String, String> getHhanmuc() {
		return Hhanmuc;
	}

	public void setHhanmuc(Hashtable<String, String> hhanmuc) {
		Hhanmuc = hhanmuc;
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
	public String getId() {
		return Id;
	}

	public void setId(String id) {
		Id = id;
	}
	public ResultSet getRshanmuc() {
		return Rshanmuc;
	}

	public void setRshanmuc(ResultSet rshanmuc) {
		Rshanmuc = rshanmuc;
	}
}
