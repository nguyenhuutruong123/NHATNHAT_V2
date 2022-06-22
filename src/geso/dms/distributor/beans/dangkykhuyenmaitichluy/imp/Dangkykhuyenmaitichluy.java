package geso.dms.distributor.beans.dangkykhuyenmaitichluy.imp;

import java.io.Serializable;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import geso.dms.distributor.beans.dangkykhuyenmaitichluy.IDangkykhuyenmaitichluy;
import geso.dms.distributor.db.sql.dbutils;

public class Dangkykhuyenmaitichluy implements IDangkykhuyenmaitichluy, Serializable
{
	private static final long serialVersionUID = 1L;
	
	String Id;
	String userId;
    String nppId;
    String nppTen;
    String tungay;
    String denngay;
    
    ResultSet ctkmRs;
    String ctkmId;
    
    ResultSet nvbhRS;
	String nvbhIds;
	
    ResultSet khRs;
	String khId;

	String Msg;
	
	dbutils db;
	
	public Dangkykhuyenmaitichluy()
	{
		 this.userId="";
	     this.nppId="";
	     this.nppTen="";
	     this.tungay="";
	     this.denngay="";
		 this.ctkmId ="";
		 this.nvbhIds = "";
		 this.khId = "";
		 this.Msg ="";
		 this.Id = "";
		 this.db = new dbutils();
		
	}
	public String getUserId() {
		
		return this.userId;
	}
	
	public void setUserId(String userId) {
		
		this.userId = userId;
	}

	
	public String getNppId() {
		
		return this.nppId;
	}

	
    public void setNppId(String nppId) {
		
	   this.nppId = nppId;	
	}

	
	public void setTungay(String tungay) {
		
		this.tungay = tungay;
	}

	
	public String getTungay() {
		
		return this.tungay;
	}

	
	public void setDenngay(String denngay) {
		
		this.denngay = denngay;
	}

	
	public String getDenngay() {
		
		return this.denngay;
	}

	private void getNppInfo(){
		geso.dms.distributor.util.Utility util=new geso.dms.distributor.util.Utility();
		this.nppId=util.getIdNhapp(this.userId);
		this.nppTen=util.getTenNhaPP();
	
	}
	
	
	public String getNppTen() {
		
		return this.nppTen;
	}
	
	public void init() 
	{
		getNppInfo();
		
		if( this.Id.length() > 0 )
		{
			String sqlgetinfo = "select * from DANGKYKM_TICHLUY where pk_seq = " + this.Id;
			
			ResultSet rs = db.get(sqlgetinfo);
			if(rs != null)
			{
				try
				{
					if(rs.next())
					{
						this.ctkmId = rs.getString("tieuchiTL_fk");
						this.nppId = rs.getString("npp_fk");
					}
					rs.close();
					
					//
					String query = "select khachhang_fk from DANGKYKM_TICHLUY_KHACHHANG where dkkmtichluy_fk = '" + this.Id + "' ";
					ResultSet rsKh = db.get(query);
					String khId = "";
					while(rsKh.next())
					{
						khId += rsKh.getString("khachhang_fk") + ",";
					}
					rsKh.close();
					
					if(khId.trim().length() > 0)
					{
						khId = khId.substring(0, khId.length() - 1);
						this.khId = khId;
					}
				}
				catch(Exception er){
					System.out.println("Error :"+er.toString());
				}
			}
		}
		
		
		
		this.createRs();
	}
	
	public void setMessage(String Msg) {
		
		this.Msg = Msg;
	}
	
	public String getMessage() {
		
		return this.Msg;
	}

	public void setctkmId(String ctkmId) {
	
		this.ctkmId = ctkmId;
	}
	
	public String getctkmId() {
		
		return this.ctkmId;
	}

	
	
	public boolean save() 
	{
		try
		{
			db.getConnection().setAutoCommit(false);
			String sql_checkexit="select pk_seq from DANGKYKM_TICHLUY where tieuchiTL_fk="+this.ctkmId +" " +
					"	and  npp_fk="+ this.nppId + " and trangthai <> '2'";
			System.out.println(sql_checkexit);
			ResultSet rs_check=db.get(sql_checkexit);
			if(rs_check!=null){
				if(rs_check.next()){
					this.Msg="Da Dang Ky Khuyen Mai Tich Luy Cho Nha Phan Phoi Nay,Vui Long Chon CT Khac.";
					db.update("rollback");
				    return false;
				}
			}
			
			String insert_="insert into DANGKYKM_TICHLUY (tieuchiTL_fk, npp_fk, trangthai,ngaytao,nguoitao,ngaysua,nguoisua) " +
							" values("+this.ctkmId+","+this.nppId+",'0','"+this.getDateTime()+"',"+this.userId+",'"+this.getDateTime()+"',"+this.userId+")";
				
			if(!db.update(insert_)){
				
				    this.Msg="Khong The Thuc Hien Insert ,Vui Long Thu Lai. Loi "+ insert_;
					db.update("rollback");
				    return false;
				    
			}
			 
			if(this.khId.trim().length() > 0)
			{
				String query = "insert DANGKYKM_TICHLUY_KHACHHANG(DKKMTICHLUY_FK, KHACHHANG_FK) " +
								"select IDENT_CURRENT('DANGKYKM_TICHLUY'), pk_seq from KhachHang where pk_seq in (" + this.khId + ") ";
				if(!db.update(query))
				{
				    this.Msg="Khong The Thuc Hien Update ,Vui Long Thu Lai. Loi "+ query;
					db.update("rollback");
				    return false;
				}
			}
			
			db.getConnection().commit();
			db.getConnection().setAutoCommit(true);
		}
		catch(Exception er)
		{
			this.Msg="Khong The Thuc Hien Insert ,Vui Long Thu Lai. Loi "+ er.toString();
			db.update("rollback");
			return false;
		}
		return true;
	}
	
	private String getDateTime()
	{
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date date = new Date();
        return dateFormat.format(date);	
	}
	
	public void DBclose() 
	{	
		try {
			
		} catch(Exception e) {
			e.printStackTrace();
		}
		
		if(db!=null)
		db.shutDown();
	}
	public void setId(String Id) {
		
		this.Id = Id;
	}
	
	public String getId() {
		
		return this.Id;
	}

	public boolean edit() 
	{
		try{
			db.getConnection().setAutoCommit(false);
			
			String sql_checkexit="select pk_seq from DANGKYKM_TICHLUY where tieuchiTL_fk="+this.ctkmId +" and  npp_fk="+ this.nppId + " and trangthai <> '2' and pk_seq <>"+ this.Id;
			ResultSet rs_check=db.get(sql_checkexit);
			if(rs_check!=null){
				if(rs_check.next()){
					this.Msg="Da Dang Ky Khuyen Mai Tich Luy Cho Nha Phan Phoi Nay,Vui Long Chon CT Khac.";
					db.update("rollback");
				    return false;
				}
			}
			
			String query = "delete DANGKYKM_TICHLUY_KHACHHANG where dkkmtichluy_fk = '" + this.Id + "' ";
			if(!db.update(query))
			{
			    this.Msg="Khong The Thuc Hien Update ,Vui Long Thu Lai. Loi "+ query;
				db.update("rollback");
			    return false;
			}
			
			String insert_="update DANGKYKM_TICHLUY set tieuchiTL_fk="+this.ctkmId+" ,npp_fk="+this.nppId+" ,ngaysua="+this.getDateTime()+",nguoisua="+this.userId+
					" where pk_seq="+ this.Id;		
			if(!db.update(insert_)){
				    this.Msg="Khong The Thuc Hien Update ,Vui Long Thu Lai. Loi "+ insert_;
					db.update("rollback");
				    return false;
			}
			
			if(this.khId.trim().length() > 0)
			{
				query = "insert DANGKYKM_TICHLUY_KHACHHANG(DKKMTICHLUY_FK, KHACHHANG_FK) " +
								"select IDENT_CURRENT('DANGKYKM_TICHLUY'), pk_seq from KhachHang where pk_seq in (" + this.khId + ") ";
				if(!db.update(query))
				{
				    this.Msg="Khong The Thuc Hien Update ,Vui Long Thu Lai. Loi "+ query;
					db.update("rollback");
				    return false;
				}
			}
			
			db.getConnection().commit();
			db.getConnection().setAutoCommit(true);
		}
		catch(Exception er)
		{
			this.Msg="Khong The Thuc Hien Insert ,Vui Long Thu Lai. Loi "+ er.toString();
			db.update("rollback");
			return false;
		}
		return true;
	}

	public boolean Chot() 
	{

		try{
			db.getConnection().setAutoCommit(false);
			String insert_="update DANGKYKM_TICHLUY set trangthai=1 ,ngaysua="+this.getDateTime()+",nguoisua="+this.userId+
					" where pk_seq="+ this.Id;		
			if(!db.update(insert_)){
				    this.Msg="Khong The Thuc Hien Update ,Vui Long Thu Lai. Loi "+ insert_;
					db.update("rollback");
				    return false;
			}

			db.getConnection().commit();
			db.getConnection().setAutoCommit(true);
		}catch(Exception er){
			this.Msg="Khong The Thuc Hien Insert ,Vui Long Thu Lai. Loi "+ er.toString();
			db.update("rollback");
			return false;
		}
		return true;
	}

	public boolean Delete() {

		try{
			db.getConnection().setAutoCommit(false);
			String insert_="update DANGKYKM_TICHLUY set trangthai=2 ,ngaysua="+this.getDateTime()+",nguoisua="+this.userId+
					" where pk_seq="+ this.Id;		
			if(!db.update(insert_)){
				    this.Msg="Khong The Thuc Hien Huy  ,Vui Long Thu Lai. Loi "+ insert_;
					db.update("rollback");
				    return false;
			}

			db.getConnection().commit();
			db.getConnection().setAutoCommit(true);
		}catch(Exception er){
			this.Msg="Khong The Thuc Hien Huy ,Vui Long Thu Lai. Loi "+ er.toString();
			db.update("rollback");
			return false;
		}
		return true;
	}
	
	public void setNppTen(String nppTen) {
		
		this.nppTen = nppTen;
	}
	
	public void setctkmIds(ResultSet ctkmIds) {
		
		this.ctkmRs = ctkmIds;
	}
	
	public ResultSet getctkmIds() {
		
		return this.ctkmRs;
	}
	
	public String getNvbhIds() {
		
		return this.nvbhIds;
	}
	
	public void setNvbhIds(String nvbdIds) {
		
		this.nvbhIds = nvbdIds;
	}
	
	public String getKhId() {
		
		return this.khId;
	}
	
	public void setKhId(String khId) {
		
		this.khId = khId;
	}
	
	public void createRs() 
	{
		this.getNppInfo();
		
		//Lay MAX ngay khoa so
		String query = "select isnull(max(NGAYKS), '" + getDateTime() + "') as ngayKS from KHOASONGAY where NPP_FK = '" + this.nppId + "'";
		ResultSet rsKsn = db.get(query);
		String ngayKS = "";
		try 
		{
			if(rsKsn.next())
			{
				ngayKS = rsKsn.getString("ngayKS");
			}
			rsKsn.close();
		} 
		catch (Exception e) {}
		
		
		query = "select pk_seq, scheme  " +
				"from TIEUCHITHUONGTL where '" + ngayKS + "' >= THANG and '" + ngayKS + "' <= nam and trangthai = '1' " +
					   "and PK_SEQ in ( select thuongtl_fk from TIEUCHITHUONGTL_NPP where npp_fk = '" + this.nppId + "' ) " +
					   "and pk_seq not in ( select TIEUCHITL_FK from DANGKYKM_TICHLUY where npp_fk = '" + this.nppId + "' and trangthai != 2 ) ";
		
		if(this.Id.trim().length() > 0)
		{
			query += " union " +
					"select pk_seq, scheme from TIEUCHITHUONGTL where pk_seq = ( select TIEUCHITL_FK from DANGKYKM_TICHLUY where pk_seq = '" + this.Id + "' ) ";
		}
		
		System.out.println("__LAY SCHEME: " + query);
		this.ctkmRs = db.get(query);
		
		if(this.ctkmId.trim().length() > 0)
		{
			query = "select thang, nam from TIEUCHITHUONGTL where pk_seq = '" + this.ctkmId + "' ";
			
			ResultSet rsTL = db.get(query);
			try 
			{
				if(rsTL.next())
				{
					this.tungay = rsTL.getString("thang");
					this.denngay = rsTL.getString("nam");
				}
				rsTL.close();
			} 
			catch (Exception e) {}
			
			//Get NVBH
			query = "select PK_SEQ, SMARTID, TEN, isnull(MANHANVIEN, '') as maNV from DAIDIENKINHDOANH where NPP_FK = '" + this.nppId + "' and TRANGTHAI = '1'";
			this.nvbhRS = db.get(query);
			
			query = "select PK_SEQ, SMARTID, TEN, DIACHI from KHACHHANG where NPP_FK = '" + this.nppId + "' and TRANGTHAI = '1'";
			if(this.nvbhIds.trim().length() > 0)
			{
				query += " and pk_seq in ( select KHACHHANG_FK from KHACHHANG_TUYENBH where TBH_FK in ( select PK_SEQ from TUYENBANHANG where DDKD_FK = '" + this.nvbhIds + "' ) ) ";
			}
			
			query += " order by SMARTID asc ";
			
			this.khRs = db.get(query);
			
		}
		
		
	}
	
	public void setCtkmRs(ResultSet ctkmIds) {
		
		this.ctkmRs = ctkmIds;
	}
	
	public ResultSet getCtkmRs() {
		
		return this.ctkmRs;
	}
	
	public void setCtkmId(String ctkmId) {
		
		this.ctkmId = ctkmId;
	}
	
	public ResultSet getNvBanhang() {
		
		return this.nvbhRS;
	}
	
	public void setNvBanhang(ResultSet nvbanhang) {
		
		this.nvbhRS = nvbanhang;
	}
	
	public void setKhList(ResultSet KhList) {
		
		this.khRs = KhList;
	}
	
	public ResultSet getKhList() {
		
		return this.khRs;
	}
	
	
}
