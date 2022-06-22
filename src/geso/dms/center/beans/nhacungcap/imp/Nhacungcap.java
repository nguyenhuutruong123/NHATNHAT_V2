package geso.dms.center.beans.nhacungcap.imp;
import geso.dms.center.beans.nhacungcap.INhacungcap;
import geso.dms.center.db.sql.*;
import java.sql.ResultSet;

public class Nhacungcap implements INhacungcap
{
	private static final long serialVersionUID = -9217977546733610214L;
	String id;
	String ten;
	String diachi;
	String masothue;
	String tenviettat;
	String trangthai;
	String ngaytao;
	String ngaysua;
	String nguoitao;
	String nguoisua;
	String msg;
	String email="";
	String nganhang="";
	String fax="";
	
	public String getFax() {
		return fax;
	}

	public void setFax(String fax) {
		this.fax = fax;
	}

	public String getNganhang() {
		return nganhang;
	}

	public void setNganhang(String nganhang) {
		this.nganhang = nganhang;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	String kyhieuhoadon;
	String sohoadontu;
	String sohoadonden;
	dbutils db;
	String Sotk = "";
	
	public Nhacungcap(String[] param)
	{
		this.id = param[0];
		this.ten = param[1];
		this.diachi = param[2];
		this.masothue = param[3];
		this.tenviettat = param[4];
		this.trangthai = param[5];
		this.ngaytao = param[6];
		this.nguoitao = param[7];
		this.ngaysua = param[8];
		this.nguoisua = param[9];
		this.msg = "";
		this.db = new dbutils();
	}
	
	public Nhacungcap()
	{
		this.id = "";
		this.ten = "";
		this.tenviettat = "";
		this.diachi = "";
		this.masothue = "";
		this.tenviettat = "";
		this.trangthai = "";
		this.ngaytao = "";
		this.nguoitao = "";
		this.ngaysua = "";
		this.nguoisua = "";
		this.sohoadontu="";
		this.sohoadonden="";
		this.kyhieuhoadon="";
		this.email="";
		this.msg = "";
		this.fax="";
		this.nganhang="";
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
	
	public String getDiachi()
	{
		return this.diachi;
	}
	
	public void setDiachi(String diachi)
	{
		this.diachi = diachi;
	}
	
	public String getMasothue()
	{
		return this.masothue;
	}
	
	public void setMasothue(String masothue)
	{
		this.masothue = masothue;
	}
	
	public String getTenviettat()
	{
		return this.tenviettat;
	}

	public void setTenviettat(String tenviettat)
	{
		this.tenviettat = tenviettat;
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
	boolean kiemtraten()
	{ String sql ="select * from NHACUNGCAP where ten =N'"+ this.ten +"'";
	  ResultSet rs = db.get(sql);
	  try {
		if(rs.next())
			  return true;
	if(rs!=null) rs.close();    	
	} catch(Exception e) {		
		e.printStackTrace();
	}
	 	return false;
	}
	public boolean saveNewNcc(){
		String query;

		
		
		try {
			
			db.getConnection().setAutoCommit(false);
			
			if(kiemtraten()) 
			{
				db.getConnection().rollback();
				return false;
			}
			
			query = "\n insert into NHACUNGCAP(TEN,DIACHI,MASOTHUE,NGAYTAO,NGAYSUA,NGUOITAO,NGUOISUA,TENVIETTAT,TRANGTHAI,sohoadontu,sohoadonden,kyhieuhoadon,SOTK,email, nganhang, fax) " +
					"\n values(N'" + this.ten + "',N'"+ this.diachi + "','" + this.masothue + "','" + this.ngaytao + "','" + this.ngaysua + "','" + this.nguoitao + "','" + this.nguoisua 
					+ "',N'" + this.tenviettat  + "','" + this.trangthai + "','"+ sohoadontu +"','" 
					+ sohoadonden +"','"+ kyhieuhoadon +"','"+ this.Sotk +"','"+ this.email+"','"+  this.nganhang +"','" + this.fax +"' )";
			System.out.print(" chen them "+query);
			if (db.updateReturnInt(query)!=1){
				db.getConnection().rollback();
				return false;			
			}
			db.getConnection().commit();
			db.getConnection().setAutoCommit(true);
				  
		} catch(Exception e) {
			
			e.printStackTrace();
		}
		
		return true;
		
	}
	
	public boolean UpdateNcc(){
		String query;
	//	if(kiemtraten()) return false;
		
		query = "update NHACUNGCAP set ten=N'" + this.ten + "', diachi= N'" + this.diachi + "', masothue = '" + this.masothue + "', tenviettat = N'"+ this.tenviettat + "', trangthai = '" + this.trangthai + "', ngaysua = '" + this.ngaysua + "', nguoisua = '" + this.nguoisua  + "' ," +
				" kyhieuhoadon = '"+ this.kyhieuhoadon +"', sohoadontu = '"+ this.sohoadontu +"', sohoadonden= '"+ this.sohoadonden +"',sotk = '"+this.Sotk+"', email= '" +this.email +"',nganhang='"+ this.nganhang +"', fax='"+this.fax +"' "+
				" where pk_seq = '" + this.id + "'" ;
		System.out.print(query);
		if(db.update(query)){
			return true;	
		}else{
			db.update("rollback");		
			return false;
		}	    
		
		
	}
	
	public void init(){
		String query = "select a.pk_seq, a.kyhieuhoadon, a.sohoadontu,a.email,a.nganhang,a.fax,  a.sohoadonden , a.ten, a.diachi, a.masothue, a.tenviettat, a.trangthai, a.ngaytao, a.ngaysua, "+
		"  b.ten as nguoitao, c.ten as nguoisua,isnull(a.sotk,'') sotk  from NHACUNGCAP a, nhanvien b, nhanvien c where a.nguoitao = b.pk_seq and a.nguoisua = c.pk_seq and a.pk_seq="+ this.id +" order by ngaytao";
   		ResultSet ncc = this.db.get(query);
   		
   		try{
   			ncc.next();
   			this.ten = ncc.getString("ten");
   			this.diachi = ncc.getString("diachi");
   			this.masothue = ncc.getString("masothue");
   			this.tenviettat = ncc.getString("tenviettat");
   			this.trangthai = ncc.getString("trangthai");
   			this.ngaytao = ncc.getString("ngaytao");
   			this.ngaysua = ncc.getString("ngaysua");
   			this.nguoitao = ncc.getString("nguoitao");
   			this.nguoisua = ncc.getString("nguoisua");
   			this.sohoadontu =  ncc.getString("sohoadontu");
   			this.sohoadonden =  ncc.getString("sohoadonden");
   			this.kyhieuhoadon =  ncc.getString("kyhieuhoadon");
   			this.nganhang=ncc.getString("nganhang");
   			this.email=ncc.getString("email");
   			this.fax=ncc.getString("fax");
   			this.Sotk =  ncc.getString("Sotk");
   			if(ncc!=null) ncc.close();   			        	       
   		}catch(Exception e){}
	}
	
	public void DBClose(){				
		this.db.shutDown();
	}

	@Override
	public String getSohoadontu() {
		// TODO Auto-generated method stub
		return this.sohoadontu;
	}

	@Override
	public void setSohoadontu(String sohoadontu) {
		this.sohoadontu= sohoadontu;
		
	}

	@Override
	public String getSohoadonden() {
		// TODO Auto-generated method stub
		return this.sohoadonden;
	}

	@Override
	public void setSohoadonden(String sohoadonden) {
		this.sohoadonden= sohoadonden;
		
	}

	@Override
	public String getKyhieuhoadon() {
		// TODO Auto-generated method stub
		return this.kyhieuhoadon;
	}

	@Override
	public void setKyhieuhoadon(String kyhieuhoadon) {
		this.kyhieuhoadon = kyhieuhoadon;
		
	}
	public String getSotk() {
		return Sotk;
	}

	public void setSotk(String sotk) {
		Sotk = sotk;
	}

}
