package geso.dms.distributor.beans.noptiennpp.imp;
import java.io.Serializable;

import geso.dms.distributor.beans.noptiennpp.*;
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
import java.util.Enumeration;
import java.util.List;
import java.util.Hashtable;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.JsonPrimitive;


public class NopTienNPP implements INopTienNPP, Serializable
{	
	private static final long serialVersionUID = -9217977546733610214L;
	
	int isDisplay = 0;
	
	public int getIsDisplay() {
		return isDisplay;
	}
	
	public void setIsDisplay(int isDisplay) {
		this.isDisplay = isDisplay;
	}
	String doituong = "0";
	String id = "";
	String msg = "";
	String userId = "";
	String nppId = "";
	String ngaygiaodich = "";
	String khId = "";
	List<String> dhIdList= new ArrayList<String>();
	String dhIds= "";
	
	dbutils db;
	List<ISanpham> splist= new ArrayList<ISanpham>();
	Utility util = new Utility();

	Hashtable<String, Double> npp_sotien = new Hashtable<String, Double>();
	
	public Hashtable<String, Double> getNpp_sotien() {
		return npp_sotien;
	}
	public void setNpp_sotien(String[]nppIds,String[]sotien)
	{
		
		if(nppIds != null && sotien!= null)
		{
			for(int i = 0 ; i < sotien.length ; i++)
			{
				double st = Utility.parseDouble( sotien[i].replace(",", "")  );
				if(st > 0)
					npp_sotien.put(nppIds[i],st);
			}
		}
	}
	
	public String getDoituong() {
		return doituong;
	}
	public void setDoituong(String doituong) {
		this.doituong = doituong;
	}
	
	
	public dbutils getDb() {
		return db;
	}
	
	

	
	public NopTienNPP(String id)
	{
		this.id = id;
		this.db = new dbutils();	
	}

	public void getNppInfo()
	{	
		geso.dms.distributor.util.Utility util = new geso.dms.distributor.util.Utility();
		setNppId(util.getIdNhapp(this.userId)); // dùng hàm để lấy 1 số thông tin từ npp
		
		
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

	

	public String getNgaygiaodich() 
	{	
		return this.ngaygiaodich;
	}

	public void setNgaygiaodich(String ngaygiaodich) 
	{
		this.ngaygiaodich = ngaygiaodich;		
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

	
	public void Lay_SanPham_Theo_HoaDon()
	{
		this.splist.clear();
		try
		{
			if(this.khId.length() > 0 && this.dhIds.length() > 0)
			{
				String query  = "\n select sp.pk_seq, sp.ten, sp.ma, a.soluong  " +
				"\n  from sanpham sp " +
				"\n  inner join hoadon_sp a on a.sanpham_fk = sp.pk_seq " +
				"\n  inner join hoadon hd on hd.pk_seq = a.hoadon_fk " +
				"\n  where hd.pk_seq in ( "+ this.dhIds+ ") and hd.khachhang_fk = " + this.khId +
				"\n  order by sp.ma ";
				ResultSet rs = db.get(query);
				while(rs.next())
				{
					String spId = rs.getString("pk_seq");
					String spma = rs.getString("ma");
					String spten = rs.getString("ten");
					int soluong =(int) Math.round(rs.getDouble("soluong"));
					for(int i = 0; i < soluong; i++)
					{
						ISanpham sp = new Sanpham();
						sp.setId(spId);
						sp.setMa(spma);
						sp.setTen(spten);
						sp.setStt( (i+1) + "" );
						sp.setBarcode("");
						this.splist.add(sp);
					}
					
				}
			}
			
		}
		catch (Exception e) {
			this.msg = " Lỗi lấy sản phẩm theo hóa đơn  " + e.getMessage();
		}
	}
	
	

	public boolean CreateXuatKho() 
	{
		try
		{
			
			db.getConnection().setAutoCommit(false);
			getNppInfo();
			if(this.nppId.length()<=0)
			{
				db.getConnection().rollback();
				this.msg = "Vuil lòng chọn Chi nhánh/NPP";
				return false;
			}
			
			
			if(this.ngaygiaodich.length()<=0)
			{
				db.getConnection().rollback();
				this.msg = "Vui lòng chọn lại ngày chứng từ";
				return false;
			}
			
			String query  ="";
			
			query = " insert NopTienNPP ( npp_fk,doituong,trangthai,nguoitao,nguoisua,ngaytao,ngaysua,ngaynhap,ghichu) " +
					" select "+this.nppId+","+this.doituong+",0,"+this.userId+","+this.userId+",getdate(),getdate(),'"+this.ngaygiaodich+"',N'"+this.ghichu+"' ";
			if(db.updateReturnInt(query)<=0)
			{
				System.out.println(query);
				db.getConnection().rollback();
				this.msg = "Lỗi lưu chứng từ (1)";
				return false;
			}
			query  = " select scope_identity() xxx ";
			ResultSet rs = db.get(query); rs.next();
			String idTMp = rs.getString("xxx");
	
			boolean codata= false;
			Enumeration<String> keys = this.npp_sotien.keys();
			while(keys.hasMoreElements())
			{
				String key = keys.nextElement();
				double sotien = this.npp_sotien.get(key);
				String npp = this.doituong.equals("0") ? key :"NULL";
				String kh = this.doituong.equals("1") ? key :"NULL";
				if(sotien > 0)
				{
					query = " insert NopTienNPP_ChiTiet ( noptien_fk,npp_dat_fk,khachhang_fk,sotien) " +
					" select "+idTMp+", "+npp+","+kh+","+ sotien ;
					if(db.updateReturnInt(query)<=0)
					{
						System.out.println(query);
						db.getConnection().rollback();
						this.msg = "Lỗi lưu chứng từ (2)";
						return false;
					}
					codata = true;
				}
			}
			if(!codata)
			{
				db.getConnection().rollback();
				this.msg = "Chưa nhập tiền nộp";
				return false;
			}
			
			
			db.getConnection().commit();
			return true;
			
		}
		catch (Exception e) {
			e.printStackTrace();
			Utility.rollback_throw_exception(db);
			this.msg ="Exception: " + e.getMessage();
			return false;
		}
		
	}

	
		
	
	public boolean UpdateXuatKho() 
	{
		try
		{
			db.getConnection().setAutoCommit(false);
			if(this.nppId.length()<=0)
			{
				db.getConnection().rollback();
				this.msg = "Vuil lòng chọn Chi nhánh/NPP";
				return false;
			}
			
			
			if(this.ngaygiaodich.length()<=0)
			{
				db.getConnection().rollback();
				this.msg = "Vui lòng chọn lại ngày chứng từ";
				return false;
			}
			String query  ="";
			
			query = " update NopTienNPP  set  nguoisua = "+this.userId+" ,ngaysua = getdate() ,ngaynhap = '"+this.ngaygiaodich+"' ,ghichu= N'"+this.ghichu+"' " +
					" where  trangthai = 0 and pk_seq =" + this.id;
			if(db.updateReturnInt(query)<=0)
			{
				System.out.println(query);
				db.getConnection().rollback();
				this.msg = "Lỗi lưu chứng từ (1)";
				return false;
			}
			
			query = " delete NopTienNPP_ChiTiet where noptien_fk = "+ this.id;
			db.update(query);
			
			boolean codata= false;
			Enumeration<String> keys = this.npp_sotien.keys();
			while(keys.hasMoreElements())
			{
				String key = keys.nextElement();
				double sotien = this.npp_sotien.get(key);
				String npp = this.doituong.equals("0") ? key :"NULL";
				String kh = this.doituong.equals("1") ? key :"NULL";
				if(sotien > 0)
				{
					query = " insert NopTienNPP_ChiTiet ( noptien_fk,npp_dat_fk,khachhang_fk,sotien) " +
					" select "+this.id+", "+npp+","+kh+","+ sotien ;
					if(db.updateReturnInt(query)<=0)
					{
						System.out.println(query);
						db.getConnection().rollback();
						this.msg = "Lỗi lưu chứng từ (2)";
						return false;
					}
					codata = true;
				}
			}
			if(!codata)
			{
				db.getConnection().rollback();
				this.msg = "Chưa nhập tiền nộp";
				return false;
			}
			
			db.getConnection().commit();
			return true;
			
		}
		catch (Exception e) {
			e.printStackTrace();
			Utility.rollback_throw_exception(db);
			this.msg ="Exception: " + e.getMessage();
			return false;
		}
	}

	


	
	public void init()
	{	
		getNppInfo();
		String query  ="";
		
		query = "\n select  a.doituong,a.pk_seq , a.ghichu, a.npp_fk,a.trangthai, a.ngaynhap " +
				"\n from NopTienNPP a " +
				"\n where a.pk_seq = '" + this.id + "' ";
		System.out.println("1.Init don hang: " + query);
		ResultSet rs = db.get(query);

		try
		{
			rs.next();
			this.ghichu = rs.getString("ghichu");
			this.nppId = rs.getString("npp_fk");
			this.ngaygiaodich = rs.getString("ngaynhap");
			this.doituong  = rs.getString("doituong");
			query = " select isnull(khachhang_fk,npp_dat_fk) doituong_fk, sotien from NopTienNPP_ChiTiet where [noptien_fk] =  " + this.id;
			this.dhIds = "";
			rs= db.get(query);
			while(rs.next())
			{
				this.npp_sotien.put(rs.getString("doituong_fk"),rs.getDouble("sotien")  );
			}
			
			
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}

		



	}

	public ResultSet getSanphamRs()
	{
		String query = " select pk_seq, ma ,ten,TIMKIEM  from sanpham where trangthai =  1 order by ma ";
		return db.get(query);
	}
	public ResultSet getNppRs()
	{
		String doituongIds = "1";
		if(this.doituong.equals("0"))//npp
		{
			if( this.id != null && this.id.trim().length() > 0)
				doituongIds = " select npp_dat_fk from NopTienNPP_ChiTiet where noptien_fk =  " + this.id;
			String query  = 
				"\n select npp.pk_seq, npp.mafast , npp.ten  " +
				"\n from nhaphanphoi npp " +
				"\n	where tructhuoc_fk ="+this.nppId+" " +
				"\n		and ( npp.trangthai = 1 or npp.pk_seq in ("+doituongIds+") )	";
			return db.get(query);
		}
		if(this.doituong.equals("1")) // kh
		{
			if( this.id != null && this.id.trim().length() > 0)
				doituongIds = " select khachhang_fk from NopTienNPP_ChiTiet where noptien_fk =  " + this.id;
			String query  = 
				"\n select kh.pk_seq, kh.mafast , kh.ten  " +
				"\n from khachhang kh " +
				"\n	where kh.npp_fk ="+this.nppId+" " +
				"\n		and ( kh.trangthai = 1 or kh.pk_seq in ("+doituongIds+") )	";
			return db.get(query);
		}
			
		return null;
		
			
		
	}
	
	public ResultSet getKhachHangRs()
	{
		String sqlId = "0";
		if(this.id != null && this.id.length() > 0)
			sqlId = this.id;
		
		String query  = "\n select kh.pk_seq, kh.mafast , kh.ten " +
						"\n from khachhang kh " +
						"\n where kh.npp_fk  ="+this.nppId+" " +
						"\n  and exists ( 	select 1 " +
						"\n					from hoadon hd " +
						"\n					where hd.trangthai in ( 2,4) and hd.khachhang_fk = kh.pk_seq " +
						"\n						and not exists ( select 1 from NopTienNPP_HoaDon x where x.hoadon_fk = hd.pk_seq and xuatkho_fk != "+sqlId+")" +
						"\n				)	 ";
		return db.get(query);
	}
	public ResultSet getHoaDonRs()
	{
		if(this.khId != null && this.khId.length() > 0)
		{
			String sqlId = "0";
			if(this.id != null && this.id.length() > 0)
				sqlId = this.id;
			
			String query  ="\n select pk_seq, sochungtu , sohoadon " +
						   "\n from hoadon hd where trangthai in ( 2,4) " +
						   "\n		and khachhang_fk = "+this.khId+" and npp_fk =  " + this.nppId +
						   "\n		and not exists ( select 1 from NopTienNPP_HoaDon x where x.hoadon_fk = hd.pk_seq and xuatkho_fk != "+sqlId+") ";
			return db.get(query);
		}
		return null;
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

	public String getKhId() {
		return khId;
	}
	public void setKhId(String khId) {
		this.khId = khId;
	}
	
	public void setDhIdList(String[] xxx) {
		this.dhIds = "";
		if(xxx != null)
		{
			
			for(int i = 0; i < xxx.length ; i++)
			{
				this.dhIdList.add(xxx[i]);
				this.dhIds = this.dhIds.length() > 0 ? "," + xxx[i] :  xxx[i]; 
			}
		}
	}
	public List<String> getDhIdList() {
		return this.dhIdList;
	}
	
	NumberFormat format = new DecimalFormat("#,###,####");

	public ResultSet createKhInfoRs()
	{
		if(this.khId != null && this.khId.length() > 0)
		{
			String query = " select pk_seq, mafast, ten, didong, dienthoai,diachi,diachigiaohang from khachhang where pk_seq =  " + this.khId;
			return db.get(query);
		}
		return null;
	}


}
