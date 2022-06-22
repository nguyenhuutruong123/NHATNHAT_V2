package geso.dms.distributor.beans.BacSi.imp;

import geso.dms.center.db.sql.Idbutils;
import geso.dms.distributor.beans.BacSi.IBacSi;
import geso.dms.distributor.db.sql.dbutils;
import geso.dms.distributor.util.Utility;

import java.io.Serializable;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Hashtable;

import javax.servlet.http.HttpServletRequest;

import org.apache.xmlbeans.impl.regex.REUtil;

public class BacSi implements IBacSi, Serializable
{
	private static final long serialVersionUID = -9217977546733610214L;

	
	String userId;
	String Ma;
	String Ten;
	String SDT;
	String Diachi;
	String email;
	String [] khachhang_fk;
	ResultSet rskhachhang;
	dbutils db;
	String nppid;
	String nppten;
	String msg;
	String trangthai;
	String khid;
	String id;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getKhid() {
		return khid;
	}
	public void setKhid(String khid) {
		this.khid = khid;
	}
	public String getTrangthai() {
		return trangthai;
	}
	public void setTrangthai(String trangthai) {
		this.trangthai = trangthai;
	}
	public String getMsg() {
		return msg;
	}
	public void setMsg(String msg) {
		this.msg = msg;
	}
	public String getNppid() {
		return nppid;
	}
	public void setNppid(String nppid) {
		this.nppid = nppid;
	}
	
	public String getNppten() {
		return nppten;
	}
	public void setNppten(String nppten) {
		this.nppten = nppten;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getMa() {
		return Ma;
	}
	public void setMa(String ma) {
		Ma = ma;
	}
	public String getTen() {
		return Ten;
	}
	public void setTen(String ten) {
		Ten = ten;
	}
	public String getSDT() {
		return SDT;
	}
	public void setSDT(String sDT) {
		SDT = sDT;
	}
	public String getDiachi() {
		return Diachi;
	}
	public void setDiachi(String diachi) {
		Diachi = diachi;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String[] getKhachhang_fk() {
		return khachhang_fk;
	}
	public void setKhachhang_fk(String[] khachhang_fk) {
		this.khachhang_fk = khachhang_fk;
	}
	public ResultSet getRskhachhang() {
		return rskhachhang;
	}
	public void setRskhachhang(ResultSet rskhachhang) {
		this.rskhachhang = rskhachhang;
	}
	
	public BacSi(String id) {
		this.Ma="";
		this.Ten="";
		this.SDT="";
		this.Diachi="";
		this.email="";
		this.msg="";
		this.trangthai="";
		this.khid="";
		this.id=id;
		this.db=new dbutils();
	}
	
	public void createRS()
	{
		this.getNppInfo();
		String quString="select pk_Seq,ten from khachhang  where KBH_FK=100052 and npp_fk="+this.nppid;
		System.out.println("get khachhang "+quString);
		this.rskhachhang=this.db.get(quString);
		
		if(this.id!=null && this.id.trim().length()>0)
		{
			 quString="select pk_seq,trangthai,isnull(ten,'') ten ,isnull(ma,'') as ma,npp_fk,isnull(diachi,'') diachi ,isnull(sodienthoai,'') sodienthoai,isnull(email,'') email from BacSi where pk_seq="+this.id;
			 ResultSet rs=db.get(quString);
			 try {
				rs.next();
				this.id=rs.getString("pk_seq");
				this.Ten=rs.getString("ten");
				this.Ma=rs.getString("ma");
				this.Diachi=rs.getString("diachi");
				this.SDT=rs.getString("sodienthoai");
				this.email=rs.getString("email");
				this.trangthai=rs.getString("trangthai");
				rs.close();
				
				
				 quString="select  KhachHang_fk,bacsi_fk  from KhachHang_BacSi where bacsi_fk="+this.id;
				  rs=db.get(quString);
				
					while(rs.next())
					{
						this.khid+=rs.getString("khachhang_fk")+",";
					}
					this.khid+="0";
					rs.close();
				
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
	}
	private void getNppInfo()
	{		
		geso.dms.distributor.util.Utility util=new geso.dms.distributor.util.Utility();	
			try
			{
				this.nppid=util.getIdNhapp(this.userId);
				this.nppten=util.getTenNhaPP();
			
			}
			catch(Exception ex){}
		
		
	}
	
	public boolean CreateBS(HttpServletRequest request) 
	{
		try {
			
			String quString="select count(*) sl from bacsi where Ma=N'"+this.Ma+"'";
			ResultSet rss=db.get(quString);
			rss.next();
			int sl=rss.getInt("sl");
			rss.close();
			if(sl>0)
			{
				this.msg = " Mã bác sĩ đã tồn tại ";
				return false;
			}
			
			this.db.getConnection().setAutoCommit(false);
			 quString="insert into BacSi (trangthai,ten,ma,npp_fk,diachi,sodienthoai,email)"+
					" select '"+trangthai+"',N'"+this.Ten+"',N'"+this.Ma+"',"+this.nppid+",N'"+this.Diachi+"',N'"+this.SDT+"',N'"+this.email+"' ";
			if(db.updateReturnInt(quString)<=0)
			{
				db.getConnection().rollback();
				this.msg = " lỗi trong quá trình lưu !!!";
				return false;		
			}
			String query = "select  SCOPE_IDENTITY() as khId";
			ResultSet rs = this.db.get(query);
			rs.next();
			this.id = rs.getString("khId");
			rs.close();
			
			if(this.khachhang_fk!=null)
			{
				for(int i=0;i<this.khachhang_fk.length;i++)
				{
					quString="insert into KhachHang_BacSi (bacsi_fk,KhachHang_fk)"+
							" select '"+id+"',N'"+this.khachhang_fk[i]+"' ";
					if(db.updateReturnInt(quString)<=0)
					{
						db.getConnection().rollback();
						this.msg = " lỗi trong quá trình lưu !!!";
						return false;		
					}
				}
			}
			this.db.getConnection().commit();
			this.db.getConnection().setAutoCommit(true);
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
		
		return true;
	}
	public boolean UpdateBS(HttpServletRequest request) 
	{
		try {
			String quString="select count(*) sl from bacsi where Ma=N'"+this.Ma+"' and pk_Seq<>"+this.id;
			ResultSet rss=db.get(quString);
			rss.next();
			int sl=rss.getInt("sl");
			rss.close();
			if(sl>0)
			{
				this.msg = " Mã bác sĩ đã tồn tại ";
				return false;
			}
			
			this.db.getConnection().setAutoCommit(false);
			 quString="update bacsi set"+
					" trangthai ='"+trangthai+"',ten=N'"+this.Ten+"',ma=N'"+this.Ma+"',diachi=N'"+this.Diachi+"',sodienthoai=N'"+this.SDT+"',email=N'"+this.email+"' where pk_seq= "+this.id;
			if(db.updateReturnInt(quString)<=0)
			{
				db.getConnection().rollback();
				this.msg = " lỗi trong quá trình lưu !!!";
				return false;		
			}
			quString="delete from KhachHang_BacSi where bacsi_fk= "+this.id;
			if(db.updateReturnInt(quString)<=0)
			{
				db.getConnection().rollback();
				this.msg = " lỗi trong quá trình lưu !!!";
				return false;		
			}
			
			if(this.khachhang_fk!=null)
			{
				for(int i=0;i<this.khachhang_fk.length;i++)
				{
					quString="insert into KhachHang_BacSi (bacsi_fk,KhachHang_fk)"+
							" select '"+id+"',N'"+this.khachhang_fk[i]+"' ";
					if(db.updateReturnInt(quString)<=0)
					{
						db.getConnection().rollback();
						this.msg = " lỗi trong quá trình lưu !!!";
						return false;		
					}
				}
			}
			this.db.getConnection().commit();
			this.db.getConnection().setAutoCommit(true);
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
		
		return true;
	}
}	

