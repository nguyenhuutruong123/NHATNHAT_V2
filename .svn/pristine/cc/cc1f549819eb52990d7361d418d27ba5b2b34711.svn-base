package geso.dms.center.beans.khuyenmaidacbiet.imp;
import java.io.Serializable;

import geso.dms.center.beans.ctkhuyenmai.imp.Ctkhuyenmai;
import geso.dms.center.beans.khuyenmaidacbiet.*;
import geso.dms.center.db.sql.Idbutils;

import geso.dms.center.db.sql.*;
import geso.dms.center.util.*;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Hashtable;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.JsonPrimitive;


public class KhuyenMaiDacBiet implements IKhuyenMaiDacBiet, Serializable
{	
	private static final long serialVersionUID = -9217977546733610214L;
	
	int isDisplay = 0;
	
	public int getIsDisplay() {
		return isDisplay;
	}
	
	public void setIsDisplay(int isDisplay) {
		this.isDisplay = isDisplay;
	}
	
	String id = "";
	String msg = "";
	String userId = "";
	String nppId = "";
	String tungay = "";
	String denngay = "";
	String khoId = "";
	dbutils db;
	String khonhan_fk;
	

	List<ISanpham> splist;
	Utility util = new Utility();

	List<String[]> data = new ArrayList<String[]>();
	// 0 : nppId
	// 1 : spId 
	// 2 : ma sp
	// 3 : ten sp
	
	
	public String getKhonhan_fk() {
		return khonhan_fk;
	}

	public void setKhonhan_fk(String khonhan_fk) {
		this.khonhan_fk = khonhan_fk;
	}
	
	public List<String[]> getData() {
		return data;
	}
	public void setData(List<String[]> data) {
		this.data = data;
	}
	
	public dbutils getDb() {
		return db;
	}

	
	public KhuyenMaiDacBiet(String id)
	{
		this.id = id;
		this.db = new dbutils();	
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

	

	public String getTungay() 
	{	
		return this.tungay;
	}

	public void setTungay(String ngaygiaodich) 
	{
		this.tungay = ngaygiaodich;		
	}
	
	public String getDenngay() {
		return denngay;
	}
	public void setDenngay(String denngay) {
		this.denngay = denngay;
	}

	
	
	public String getMessage() 
	{	
		return this.msg;
	}

	public void setMessage(String msg) 
	{
		this.msg = msg;		
	}

	public String getNppId() 
	{
		return this.nppId;
	}

	public void setNppId(String nppId) 
	{
		this.nppId = nppId;
		
	}

	

	public void setSpList(List<ISanpham> splist) 
	{
		this.splist = splist;
	}

	public List<ISanpham> getSpList() 
	{
		return this.splist;
	}

	

	

	public boolean CreateXuatKho() 
	{
		try
		{
			db.getConnection().setAutoCommit(false);
			
			if(this.pt_chietkhau.length()<=0 || Utility.parseDouble(this.pt_chietkhau) <=0)
			{
				db.getConnection().rollback();
				this.msg = "Vui lòng nhập % chiết khấu";
				return false;
			}
			if(this.tungay.length()<=0)
			{
				db.getConnection().rollback();
				this.msg = "Vui lòng chọn từ ngày";
				return false;
			}
			if(this.denngay.length()<=0)
			{
				db.getConnection().rollback();
				this.msg = "Vui lòng chọn đến ngày";
				return false;
			}
			if(this.scheme.length()<=0)
			{
				db.getConnection().rollback();
				this.msg = "Vui lòng nhập mã CTKM";
				return false;
			}
			if(this.ghichu.length()<=0)
			{
				db.getConnection().rollback();
				this.msg = "Vui lòng diễn giải CTKM";
				return false;
			}
			
			if(this.data == null || this.data.size() <=0)
			{
				db.getConnection().rollback();
				this.msg = "Vui lòng chọn ít nhất 1 NPP tham gia";
				return false;
			}
			
			if(!Ctkhuyenmai.kiemtra_scheme( db,this.scheme ,""))
			{		
				this.msg = "Scheme khuyến mại đã tồn tại, vui lòng nhập lại";
				return false;
			}
			
			if(this.khonhan_fk!=null && (this.khonhan_fk.equals("-1") || this.khonhan_fk.equals("")))
			{		
				this.msg = "Vui lòng chọn kho nhận";
				return false;
			}
			
			String query  ="";
			String today = Utility.getNgayHienTai();
			String loaict = "9";
			query = " Insert into Ctkhuyenmai(khonhan_fk,pt_ChietKhau,trangthai,ap_dung_npp,scheme, diengiai, tungay, denngay, loaict, ngansach, ngaytao, nguoitao, ngaysua, nguoisua,kho_fk, loaingansach, tilevoiprimary, ApDUNGCHODHLE,PHANBOTHEODONHANG,SoXuatToiDa,inchung, sanphamdautien) " +
					" values("+this.khonhan_fk+","+Utility.parseDouble(this.pt_chietkhau) +",0,1,'" + this.scheme + "', N'" + this.ghichu + "', '" + this.tungay + "' , '" + this.denngay + "' , "+loaict+" , 0, " +
					" '" + today + "', '" + this.userId + "', '" + today + "', '" + this.userId + "','"+ this.khoId +"', 0, 0, 0,0,0,0,0 )";

			System.out.println(query);
			if(this.db.updateReturnInt(query)<=0)
			{
				this.db.getConnection().rollback();
				this.msg = "Không thể tạo mới chương trình khuyến mãi";
				return false; 
			}
	
			query  = " select scope_identity() xxx ";
			ResultSet rs = db.get(query); rs.next();
			String idTMp = rs.getString("xxx");
			for(int i = 0 ; i < data.size(); i++ )
			{
				
				String npp = data.get(i)[0];
				String sp = data.get(i)[1];
				
				query = " insert CTKM_NPP_SANPHAM ( CTKM_FK,NPP_FK,sanpham_fk) " +
						" select "+idTMp+",npp.pk_seq, sp.pk_seq" +
						" from nhaphanphoi npp , sanpham sp where npp.pk_seq = "+npp+" and sp.pk_seq =   " + sp;
				if(db.updateReturnInt(query)<=0)
				{
					System.out.println(query);
					db.getConnection().rollback();
					this.msg = "NPP hoặc sản phẩm không tồn tại";
					return false;
				}
			}
			db.getConnection().commit();
			return true;
			
		}
		catch (Exception e) {
			e.printStackTrace();
			Utility.rollback_and_shutdown(db); 
			this.msg ="Exception: ";
			return false;
		}
		
	}

	public boolean UpdateXuatKho() 
	{
		try
		{
			db.getConnection().setAutoCommit(false);
			if(this.pt_chietkhau.length()<=0 || Utility.parseDouble(this.pt_chietkhau) <=0)
			{
				db.getConnection().rollback();
				this.msg = "Vui lòng nhập % chiết khấu";
				return false;
			}
			if(this.tungay.length()<=0)
			{
				db.getConnection().rollback();
				this.msg = "Vui lòng chọn từ ngày";
				return false;
			}
			if(this.denngay.length()<=0)
			{
				db.getConnection().rollback();
				this.msg = "Vui lòng chọn đến ngày";
				return false;
			}
			if(this.scheme.length()<=0)
			{
				db.getConnection().rollback();
				this.msg = "Vui lòng nhập mã CTKM";
				return false;
			}
			if(this.ghichu.length()<=0)
			{
				db.getConnection().rollback();
				this.msg = "Vui lòng diễn giải CTKM";
				return false;
			}
			
			if(this.data == null || this.data.size() <=0)
			{
				db.getConnection().rollback();
				this.msg = "Vui lòng chọn ít nhất 1 NPP tham gia";
				return false;
			}
			
			if(Ctkhuyenmai.CTKM_phat_sinh_DH(this.db, this.id)  )
			{
				db.getConnection().rollback();
				this.msg = "CTKM đã phát sinh đơn không thể sửa";
				return false;
			}
			if(this.khonhan_fk!=null && (this.khonhan_fk.equals("-1") || this.khonhan_fk.equals("")))
			{		
				this.msg = "Vui lòng chọn kho nhận";
				return false;
			}
			String query  ="";
			String today = Utility.getNgayHienTai();
			query =  	" Update Ctkhuyenmai set khonhan_fk="+this.khonhan_fk+", pt_ChietKhau = "+Utility.parseDouble(this.pt_chietkhau)+" , scheme = '" + this.scheme + "', diengiai = N'" + this.ghichu + "', tungay = '" + this.tungay + "', " +
						" denngay = '" + this.denngay + "', ngaysua = '" + today + "', nguoisua = '" + this.userId + "', kho_fk ='"+ this.khoId +"'  " +
						" where pk_seq = '" + this.id + "'";
			if(db.updateReturnInt(query)<=0)
			{
				System.out.println(query);
				db.getConnection().rollback();
				this.msg = "Lỗi lưu CTKM";
				return false;
			}
			query = " delete CTKM_NPP_SANPHAM where CTKM_FK =  "+ this.id;
			db.update(query);
			
			for(int i = 0 ; i < data.size(); i++ )
			{
				
				String npp = data.get(i)[0];
				String sp = data.get(i)[1];
				
				query = " insert CTKM_NPP_SANPHAM ( CTKM_FK,NPP_FK,sanpham_fk) " +
						" select "+this.id+",npp.pk_seq, sp.pk_seq" +
						" from nhaphanphoi npp , sanpham sp where npp.pk_seq = "+npp+" and sp.pk_seq =   " + sp;
				if(db.updateReturnInt(query)<=0)
				{
					System.out.println(query);
					db.getConnection().rollback();
					this.msg = "NPP hoặc sản phẩm không tồn tại";
					return false;
				}
			}
			db.getConnection().commit();
			return true;
			
		}
		catch (Exception e) {
			e.printStackTrace();
			Utility.rollback_throw_exception(db);
			this.msg ="Exception";
			return false;
		}
	}

	


	
	public void init()
	{	
		String query  ="";
		
		query = "\n select  a.pt_ChietKhau,a.kho_fk,isnull(a.khonhan_fk,-1) khonhan_fk,a.pk_seq , a.scheme, a.diengiai,a.trangthai, a.tungay, a.denngay " +
				"\n from ctkhuyenmai a " +
				"\n where a.pk_seq = '" + this.id + "' ";
		System.out.println("1.Init don hang: " + query);
		ResultSet rs = db.get(query);

		try
		{
			rs.next();
			this.pt_chietkhau = rs.getString("pt_ChietKhau");
			this.khoId = rs.getString("kho_fk");
			this.scheme = rs.getString("scheme");
			this.ghichu = rs.getString("diengiai");
			this.tungay = rs.getString("tungay");
			this.denngay = rs.getString("denngay");
			this.khoId= rs.getString("kho_fk");
			this.khonhan_fk=rs.getString("khonhan_fk");
			query  = "\n select NPP_FK,Sanpham_fk from CTKM_NPP_SANPHAM " +
					"\n  where ctkm_fk = "+ this.id + "" ;
			this.data.clear();
			rs = db.get(query);
			while(rs.next())
			{
				this.data.add( new String [] { rs.getString("NPP_FK"),rs.getString("Sanpham_fk")  } );
			}
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}

		



	}

	public ResultSet getSanphamRs()
	{
		String query = " select pk_seq, ma ,ten,TIMKIEM  from sanpham where trangthai =  1  order by ma ";
		return db.get(query);
	}
	public ResultSet getNppRs()
	{
		String dataSql = "";
		String nppIds = "1";
		for(int i = 0; i < this.data.size() ; i ++)
		{
			String npp = this.data.get(i)[0];
			String sp = this.data.get(i)[1];
			
			String kq = " select npp.pk_seq, npp.mafast , npp.ten , sp.pk_seq spId  , sp.ma spMa , sp.ten SpTen ,1 chon  from nhaphanphoi npp ,sanpham sp where npp.pk_seq = " + npp + " and sp.pk_seq =  "+sp;
			dataSql += kq + " union  \n" ;
			nppIds += ", " + npp ;
		}
		
			
		String query  = dataSql+
						" select npp.pk_seq, npp.mafast , npp.ten , 0 spId  , '' spMa , '' SpTen , 0 chon " +
						" from nhaphanphoi npp where npp.loainpp <> 0 and trangthai = 1 and pk_seq in "+util.quyen_npp(this.userId)+"   " +
						" 	and pk_seq not in ("+nppIds+") ";
		return db.get(query);
	}
	public ResultSet getKhoRs()
	{
		String query  =" select pk_seq, ten from kho where trangthai = 1 ";
		return db.get(query);
	}

	private String getDateTime()
	{
		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		Date date = new Date();
		return dateFormat.format(date);	
	}



	public void DBclose()
	{
		try 
		{
			

			this.db.shutDown();
		} 
		catch (Exception e) {}
	}

	
	String ghichu ="";
	public String getGhichu() {
		return ghichu;
	}
	public void setGhichu(String ghichu) {
		this.ghichu = ghichu;
	}
	String scheme = "";
	public String getScheme() {
		return scheme;
	}
	public void setScheme(String scheme) {
		this.scheme = scheme;
	}

	NumberFormat format = new DecimalFormat("#,###,####");

	String pt_chietkhau ="";
	public String getPt_chietkhau() {
		return pt_chietkhau;
	}
	public void setPt_chietkhau(String pt_chietkhau) {
		this.pt_chietkhau = pt_chietkhau;
	}
	public String getKhoId() {
		return khoId;
	}
	public void setKhoId(String khoId) {
		this.khoId = khoId;
	}
	

}
