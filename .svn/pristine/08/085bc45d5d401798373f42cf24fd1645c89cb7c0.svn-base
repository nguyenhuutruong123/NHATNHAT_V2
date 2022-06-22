package geso.dms.distributor.beans.donhang.imp;


import java.io.Serializable;

import geso.dms.center.util.Phan_Trang;
import geso.dms.distributor.beans.donhang.IPhanBoNvgn;
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

import sun.org.mozilla.javascript.internal.regexp.SubString;
import geso.dms.distributor.db.sql.*;
import geso.dms.distributor.util.*;

public class PhanBoNvgn extends Phan_Trang implements IPhanBoNvgn, Serializable{

	
	String id = "";
	String userId = "";
	String[] donhang_fk;
	String[] PBdonhang_fk;
	String _PBdonhang_fk = "";
	String nvgn_fk;	
	String nguoitao = "";
	String nguoisua = "";
	String ngaytao = "";
	String ngaysua = "";
	String nppId = "";
	String nppTen = "";
	String msg = "";
	String tungay = "";
	String denngay = "";
	ResultSet pbList;
	ResultSet nvgnRs;
	int currentPages;
	int[] listPages;
	dbutils db = new dbutils();
	String nvgn_ten = "";
	String nvgn_ma = "";
	String ngayphanbo = "";
	
	public PhanBoNvgn() {
		id = "";
		userId = "";
		String[] donhang_fk;
		String nvgn_fk = "";
	}
	
	public String getNgayphanbo() {
		return ngayphanbo;
	}
	public void setNgayphanbo(String ngayphanbo) {
		this.ngayphanbo = ngayphanbo;
	}
	public String getNvgn_fk() {
		return nvgn_fk;
	}
	public void setNvgn_fk(String nvgn_fk) {
		this.nvgn_fk = nvgn_fk;
	}
	public ResultSet getNvgnRs() {
		return nvgnRs;
	}
	public void setNvgnRs(ResultSet nvgnRs) {
		this.nvgnRs = nvgnRs;
	}
	public String get_PBdonhang_fk() {
		return _PBdonhang_fk;
	}
	public void set_PBdonhang_fk(String _PBdonhang_fk) {
		this._PBdonhang_fk = _PBdonhang_fk;
	}
	public String[] getPBdonhang_fk() {
		return PBdonhang_fk;
	}
	public void setPBdonhang_fk(String[] pBdonhang_fk) {
		PBdonhang_fk = pBdonhang_fk;
	}
	public String getNvgn_ma() {
		return nvgn_ma;
	}
	public void setNvgn_ma(String nvgn_ma) {
		this.nvgn_ma = nvgn_ma;
	}
	public String getNvgn_ten() {
		return nvgn_ten;
	}
	public void setNvgn_ten(String nvgn_ten) {
		this.nvgn_ten = nvgn_ten;
	}
	public dbutils getDb() {
		return db;
	}
	public int getCurrentPages() {
		return currentPages;
	}
	public void setCurrentPages(int currentPages) {
		this.currentPages = currentPages;
	}
	public int[] getListPages() {
		return listPages;
	}
	public void setListPages(int[] listPages) {
		this.listPages = listPages;
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
	public String getMsg() {
		return msg;
	}
	public void setMsg(String msg) {
		this.msg = msg;
	}
	public ResultSet getPbList() {
		return pbList;
	}
	public void setPbList(ResultSet pbList) {
		this.pbList = pbList;
	}
	public String getNppId() {
		return nppId;
	}
	public void setNppId(String nppId) {
		this.nppId = nppId;
	}
	public String getNppTen() {
		return nppTen;
	}
	public void setNppTen(String nppTen) {
		this.nppTen = nppTen;
	}
	public String getNguoitao() {
		return nguoitao;
	}

	public void setNguoitao(String nguoitao) {
		this.nguoitao = nguoitao;
	}

	public String getNguoisua() {
		return nguoisua;
	}

	public void setNguoisua(String nguoisua) {
		this.nguoisua = nguoisua;
	}

	public String getNgaytao() {
		return ngaytao;
	}

	public void setNgaytao(String ngaytao) {
		this.ngaytao = ngaytao;
	}

	public String getNgaysua() {
		return ngaysua;
	}

	public void setNgaysua(String ngaysua) {
		this.ngaysua = ngaysua;
	}

	
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String[] getDonhang_fk() {
		return donhang_fk;
	}

	public void setDonhang_fk(String[] donhang_fk) {
		this.donhang_fk = donhang_fk;
	}
	
	

	public void initDHSP(String id) {
		this.getNppInfo();
		String query = "\n select a.ngaytao,a.ngaysua,nv.ten nguoitao,nv2.ten nguoisua, a.ngayphanbo " +
				"\n from PhanBo_NVGN a inner join nhanvien nv on a.nguoitao = nv.pk_seq " +
				"\n inner join nhanvien nv2 on a.nguoisua = nv2.pk_seq " +
				"\n where a.pk_seq = "+this.id;
		ResultSet rs;
		rs = db.get(query);
		
		query = "\n select top 1 a.nvgn_fk, b.ten from PhanBo_NVGN_CT a inner join nhanviengiaonhan b on a.nvgn_fk = b.pk_seq " +
				"\n where phanbo_fk ="+this.id;
		ResultSet rs1;
		rs1 = db.get(query);
		
		try {
			while (rs.next()) {
				this.ngayphanbo = rs.getString("ngayphanbo");
				this.ngaytao = rs.getString("ngaytao");
				this.ngaysua = rs.getString("ngaysua");
				this.nguoitao = rs.getString("nguoitao");
				this.nguoisua = rs.getString("nguoisua");
			}
			rs.close();
			while (rs1.next()) {
				this.nvgn_ten = rs1.getString("ten");
			}
			rs1.close();
			
			
			query = "\n select a.donhang_fk, c.mafast +' - '+c.ten makh, c.diachigiaohang diachi from PhanBo_NVGN_CT a inner join donhang b on a.donhang_fk = b.pk_seq " +
					"\n inner join khachhang c on c.pk_seq = b.khachhang_fk " +
					"\n where phanbo_fk = "+this.id;
			donhangRs = db.get(query);
		}
		
		catch (Exception e) {
			e.printStackTrace();
		}
		
		
		
	}
	
	public void initList(String search) {
		this.getNppInfo();
		this.listPages = new int[]{1, 2 , 3 , 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20};
		this.currentPages = 1;
		String msg = "";
		String query = "";
		query = "\n select a.pk_seq,a.ngaytao,a.ngaysua,b.ten nguoitao,c.ten nguoisua,a.trangthai,a.ngayphanbo " +
		"\n from PhanBo_NVGN a inner join nhanvien b on a.nguoitao = b.pk_seq" +
		"\n inner join nhanvien c on c.pk_seq = a.nguoisua where 1=1";
		if (search.length() > 0)
			query = search;
		try {
			this.createDhBeanList(query);
		}
		catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void createDhBeanList(String query)
	{
			this.pbList = super.createSplittingData(super.getItems(), super.getSplittings(), "ngayphanbo desc", query); //db.get(query);
	System.out.println("Query Init List: "+query);
	}
	
	public boolean createPhanBo() {
		String _msg = "";
		String query = "";
		int check = 0;
		try {
			this.db.getConnection().setAutoCommit(false);
			
			query = "\n insert PhanBo_NVGN (Ngaytao,ngaysua,nguoitao,nguoisua,trangthai,ngayphanbo,nvgn_fk)" +
			"\n select convert(varchar(10),getdate(),21),convert(varchar(10),getdate(),21),"+this.userId+","+this.userId+",0,'"+this.ngayphanbo+"','"+this.nvgn_fk+"'";
			System.out.println("Query Insert: "+query);
			if (!this.db.update(query)) {
				this.db.getConnection().rollback();
				_msg = "Không thể tạo mới phân bổ!";
				return false;
			}
			
			String pk = "";
			query = "select scope_identity() pk";
			ResultSet rs = db.get(query);
			while (rs.next()) 
			{
				pk = rs.getString("pk");
				this.id = pk;
			}
			rs.close();
			
			String temp = "";
			if (this.donhang_fk != null) {
				for (int i = 0; i < donhang_fk.length; i ++) {
					/*query  = "select count (*) c from PhanBo_NVGN_CT where donhang_fk = "+this.donhang_fk[i];
					
					rs = db.get(query);
					while  (rs.next()) {
						check = rs.getInt("c");
						temp += this.donhang_fk[i];
					}
					rs.close();
					
					if (check > 0) {
						this.db.getConnection().rollback();
						_msg = "Các đơn hàng "+temp+" đã được phân bổ!";
						return false;
					}*/
					
					query = "\n insert PhanBo_NVGN_CT (PHANBO_FK,DONHANG_FK,NVGN_FK) " + 
							"\n select "+pk+","+this.donhang_fk[i]+","+this.nvgn_fk;
					System.out.println("Query Insert chi tiết: "+query);
					if (!this.db.update(query)) {
						this.db.getConnection().rollback();
						_msg = "Không thể phân bổ chi tiết "+this.donhang_fk[i]+"!";
						return false;
					}
					
					/*query = "\n insert PhanBo_NVGN_CTSP (PHANBO_FK,DONHANG_FK,SANPHAM_FK,SOLUONG)" +
							"\n select "+pk+","+this.donhang_fk[i]+",sanpham_fk,null from donhang_sanpham where donhang_fk ="+this.donhang_fk[i];
					if (!this.db.update(query)) {
						this.db.getConnection().rollback();
						msg += "Không thể phân bổ chi tiết sản phẩm "+this.donhang_fk[i]+"!";
						return msg;
					}*/
				}
			}
			
			this.db.getConnection().commit();
			this.db.getConnection().setAutoCommit(true);		
		}
		catch (Exception e) {
			e.printStackTrace();
			this.db.update("rollback");
			_msg = "Lỗi Exception: "+e.getMessage();
			return false;
		}
		
		this.msg = _msg;
		return true;
	}
	
	public boolean updatePhanBo() {
		String query = "";
		boolean flag = false;
		try {
			this.db.getConnection().setAutoCommit(false);
			
			if (this.donhang_fk == null || this.donhang_fk.length == 0) {
				this.db.getConnection().rollback();
				this.msg = "Vui lòng chọn ít nhất 1 đơn hàng để cập nhật phân bổ";
				return false;
			}
			
			query = "\n delete PhanBo_NVGN_CT where phanbo_fk ="+this.id+
					"\n and not exists " +
					"\n (select 1 from PhanBo_NVGN_CTsp where phanbo_fk = PhanBo_NVGN_CT.phanbo_fk " +
					"\n and donhang_fk = PhanBo_NVGN_CT.donhang_fk) ";
			if (!this.db.update(query)) {
				this.db.getConnection().rollback();
				this.msg = "Không thể xoá PhanBo_NVGN_CT!";
				return false;
			}
			
			String temp = "";
			query  = "select donhang_fk from PhanBo_NVGN_CTSP where phanbo_fk = "+this.id;	
			ResultSet rs = db.get(query);
			while  (rs.next()) {
				flag = true;
				if (temp.equals(""))
					temp = rs.getString("donhang_fk");
				else
					temp += ","+rs.getString("donhang_fk");
			}
			rs.close();
			
			if (this.donhang_fk != null) {
				for (int i = 0; i < donhang_fk.length; i ++) {
					System.out.println("indexOf: "+temp.indexOf(donhang_fk[i]));
					if (temp.indexOf(donhang_fk[i]) < 0) {					
						query = "\n insert PhanBo_NVGN_CT (PHANBO_FK,DONHANG_FK,NVGN_FK) " + 
								"\n select "+this.id+","+this.donhang_fk[i]+","+this.nvgn_fk;
						System.out.println("Query Insert chi tiết: "+query);
						if (!this.db.update(query)) {
							this.db.getConnection().rollback();
							this.msg = "Không thể phân bổ chi tiết "+this.donhang_fk[i]+"!";
							return false;
						}
					}
				}
			}
			
			query = "\n update PhanBo_NVGN " +
			"\n set ngaysua = convert(varchar(10),getdate(),21),nguoisua="+this.userId+",ngayphanbo='"+this.ngayphanbo+"',nvgn_fk='"+this.nvgn_fk+"' where pk_seq = "+this.id;
			System.out.println("Query Insert: "+query);
			if (!this.db.update(query)) {
				this.db.getConnection().rollback();
				this.msg = "Không thể cập nhật phân bổ!";
				return false;
			}
			
			if (flag)
			{
				this.msg = "\n Cập nhật thành công ngoại trừ đơn hàng "+temp+" đã phát sinh dữ liệu";
			}
			
			this.db.getConnection().commit();
			this.db.getConnection().setAutoCommit(true);
			
		}
		catch (Exception e) {
			e.printStackTrace();
			this.db.update("rollback");
			this.msg = "Lỗi Exception: "+e;
			return false;
		}
		
		return true;
	}
	
	public void createRs() {
		String query = "select pk_seq, ten from nhanviengiaonhan where npp_fk = "+this.nppId+" order by pk_seq desc";
		this.nvgnRs = db.getScrol(query);
	}
	
	public void init(String id) {
		this.getNppInfo();
		String query = "";
		int count = 0;
		try {
			if (this.id != null && this.id.length() > 0) {
				query = "\n select count(*) count, nvgn_fk, " +
						"\n (select top 1 ngayphanbo from PhanBo_NVGN where pk_seq = PhanBo_NVGN_CT.phanbo_fk)ngayphanbo " +
						"\n from PhanBo_NVGN_CT where phanbo_fk = "+this.id +" group by nvgn_fk,phanbo_fk";
				ResultSet rs = db.get(query);
				query = "select donhang_fk from PhanBo_NVGN_CT where phanbo_fk = "+this.id;
				ResultSet rs1 = db.get(query);	
				while (rs.next()) {
					count = rs.getInt("count");
					this.nvgn_fk = rs.getString("nvgn_fk");
					this.ngayphanbo = rs.getString("ngayphanbo");
				}
				rs.close();
				
				
				int c = 0;
				String[] PBdonhang_fk = new String[count];
				String __PBdonhang_fk = "";
				while (rs1.next()) {
					__PBdonhang_fk += ", "+rs1.getString("donhang_fk");
					if (c <= count)
						PBdonhang_fk[c] = rs1.getString("donhang_fk");
					c++;
				}
				rs1.close();
				this._PBdonhang_fk = __PBdonhang_fk.substring(2);
				this.PBdonhang_fk = PBdonhang_fk;
				System.out.println("ABC: "+this._PBdonhang_fk +" -- "+__PBdonhang_fk.substring(2));
				}
			
			createRs();
		}
		catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void getNppInfo()
	{
		geso.dms.distributor.util.Utility util = new geso.dms.distributor.util.Utility();
		this.nppId=util.getIdNhapp(this.userId,this.db);
		this.nppTen=util.getTenNhaPP();
		System.out.println("Nppten: "+this.nppTen);
	}
	
	ResultSet  donhangRs;
	
	public ResultSet getDonhangRs() {
		return donhangRs;
	}
	
	public ResultSet getDonhang_SanPhamRs(String dhId) {
		
		String query = "\n select b.pk_seq,b.ten, soluong from donhang_sanpham a inner join sanpham b on a.sanpham_fk = b.pk_seq" +
				"\n where donhang_fk = "+dhId+
				"\n union all " +
				"\n select b.pk_seq, b.ten +' (KM)', soluong from donhang_ctkm_trakm a inner join sanpham b on a.SPMA = b.ma " +
				"\n  where donhangid = "+dhId+" and spma is not null";


		return db.get(query);
		
	}
	
	
	public void DBclose() 
	{		
		try 
		{
			if(db != null)
				this.db.shutDown();
			
		} 
		catch(Exception e) {}
	}
}
