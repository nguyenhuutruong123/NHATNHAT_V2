package geso.dms.distributor.beans.loaisanpham.imp;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import geso.dms.distributor.db.sql.dbutils;
import geso.dms.distributor.beans.loaisanpham.IErpLoaiSanPham;
public class ErpLoaiSanPham implements IErpLoaiSanPham
{
	String Id , UserId , Msg , CongTy , TaiKhoan , TrangThai , Ten , Ma;
	String ctyId;
	ResultSet TaiKhoanRs , LoaiSanPhamRs;
	dbutils db;
	
	String chixem;
	
	public ErpLoaiSanPham( )
	{
		this.Id = "";
		this.UserId = "";
		this.Msg = "";
		this.CongTy = "";
		this.TaiKhoan = "";
		this.TrangThai = "1";
		this.Ten = "";
		this.Ma = "";
		this.ctyId = "";
		this.chixem = "0";
		this.db = new dbutils();
	}
	
	public ErpLoaiSanPham( String id )
	{
		this.Id = id;
		this.UserId = "";
		this.Msg = "";
		this.CongTy = "";
		this.TaiKhoan = "";
		this.TrangThai = "1";
		this.Ten = "";
		this.Ma = "";
		this.ctyId = "";
		this.chixem = "0";
		this.db = new dbutils();
	}
	
	public String getId()
	{
		return this.Id;
	}
	
	public void setId(String id)
	{
		this.Id = id;
	}
	
	public String getCtyId()
	{
		return this.ctyId;
	}
	
	public void setCtyId(String ctyId)
	{
		this.ctyId = ctyId;
	}

	public String getMsg()
	{
		return this.Msg;
	}
	
	public void setMsg(String msg)
	{
		this.Msg = msg;
	}
	
	public String getMa()
	{
		return this.Ma;
	}
	
	public void setMa(String ma)
	{
		this.Ma = ma;
	}
	
	public String getTen()
	{
		return this.Ten;
	}
	
	public void setTen(String ten)
	{
		this.Ten = ten;
	}
	
	public void setUserId(String userId)
	{
		this.UserId = userId;
	}
	
	public String getUserId()
	{
		return this.UserId;
	}
	
	public String getTaiKhoan()
	{
		return this.TaiKhoan;
	}
	
	public void setTaiKhoan(String taikhoan)
	{
		this.TaiKhoan = taikhoan;
	}
	
	public void setTaiKhoanRs(ResultSet taikhoan)
	{
		this.TaiKhoanRs = taikhoan;
	}
	
	public ResultSet getTaiKhoanRs()
	{
		return this.TaiKhoanRs;
	}
	
	public ResultSet getLoaiSanPhamRs()
	{
		return this.LoaiSanPhamRs;
	}
	
	public void setLoaiSanPhamRs(ResultSet loaisanpham)
	{
		this.LoaiSanPhamRs = loaisanpham;
	}
	
	public void CreateRs()
	{
		String query = "Select PK_SEQ,SoHieuTaiKhoan As Ma,TenTaiKhoan as Ten " +
					   "From Erp_TaiKhoanKT " +
					   "Where TrangThai=1 ORDER BY CAST(SoHieuTaiKhoan AS NUMERIC(18,0)) ASC "; // and CONGTY_FK = '" + this.ctyId + "' ";
		this.TaiKhoanRs = this.db.get(query);
	}
	
	public void Init()
	{
		String query =
		"Select PK_SEQ,Ma,Ten,TaiKhoanKT_FK,TrangThai From Erp_LoaiSanPham Where PK_SEQ =" + this.Id; // AND CONGTY_FK = '" + this.ctyId + "' ";
		System.out.println(query);
		ResultSet rs = this.db.get(query);
		if (rs != null)
		{
			try
			{
				while (rs.next())
				{
					this.Ma = rs.getString("Ma");
					this.Ten = rs.getString("Ten");
					this.TaiKhoan = rs.getString("TaiKhoanKT_FK") == null ? "" : rs.getString("TaiKhoanKT_FK");
					this.TrangThai = rs.getString("TrangThai") == null ? "" : rs.getString("TrangThai");
				}
				rs.close();
			}
			catch (SQLException e)
			{
				e.printStackTrace();
			}
		}
	}
	
	public boolean Search()
	{
		String query =
		"Select l.PK_SEQ,l.MA,l.TEN,ISNULL(t.TenTaiKhoan,'')TaiKhoan,ISNULL(l.NGAYSUA,'')NgaySua,"
		+ "ISNULL(l.NGAYTAO,'')NgayTao,ISNULL(ns.TEN,'')NguoiSua,ISNULL(nt.TEN,'')NguoiTao,ISNULL(l.TrangThai,0)TrangThai  "
		+ " From Erp_LoaiSanPham l " + " LEFT Join Erp_TaiKhoanKT t on l.TaiKhoanKT_FK=t.PK_SEQ "
		+ " LEFT JOIN NHANVIEN nt on nt.PK_SEQ=l.NGUOITAO "
		+ " LEFT JOIN NHANVIEN ns on ns.PK_SEQ=l.NGUOISUA  Where l.pk_seq > 0 "; // l.CONGTY_FK = '" + this.ctyId + "'  ";
		if (this.TaiKhoan.length() > 0) query += " And TaiKhoanKT_FK=" + this.TaiKhoan + "";
		if (this.Ma.length() > 0) query += " And l.Ma Like N'%" + this.Ma + "%'";
		if (this.Ten.length() > 0) query += " And l.Ten Like N'%" + this.Ten + "%'";
		
		this.LoaiSanPhamRs = this.db.get(query);
		System.out.println("Search " + query);
		
		return false;
	}
	
	public boolean Update()
	{
		if(!CheckUnique())
		{
			this.Msg="Mã loại sản phẩm này đã có,vui lòng chọn mã khác";
			return false;
		}
		String query =
		"Update Erp_LoaiSanPham set Ma='" + this.Ma + "',Ten=N'" + this.Ten + "'," + "TaiKhoanKT_FK=" +
		this.TaiKhoan + ",NgaySua='" + getDateTime() + "',NguoiSua=" + this.UserId + ",TrangThai=" + this.TrangThai +
		",CongTy_FK='"+this.ctyId+"' Where PK_SEQ =" + this.Id + "";
		
		System.out.println(query);
		
		if (!this.db.update(query))
		{
			this.Msg = "Không thể cập nhập loại sản phẩm " + query;
			return false;
		}
		else
		{
			return true;
		}
	}
	
	public boolean Create()
	{
		try
		{
			
		if(!CheckUnique())
		{
			this.Msg="Mã loại sản phẩm này đã có,vui lòng chọn mã khác";
			return false;
		}
		String query =	"Insert Into Erp_LoaiSanPham(Ma,Ten,TaiKhoanKT_FK,NgayTao,NgaySua,NguoiTao,NguoiSua,TrangThai,CongTy_FK) values(" +
						"'" + this.Ma + "','" + this.Ten + "'," + this.TaiKhoan + ",'" + getDateTime() + "','" + getDateTime() + "'," +
						this.UserId + "," + this.UserId + "," + this.TrangThai + ",'"+this.ctyId+"' )";
		System.out.println("Create" + query);
		
		String currentId = "";
		if (this.db.update(query)) 
		{
			query = "Select IDENT_CURRENT('Erp_LoaiSanPham') as currentId";
			ResultSet rsId = this.db.get(query);
			
			if (rsId != null)
			{
				while (rsId.next())
				{
					currentId = rsId.getString("currentId");
					System.out.println("ID" + currentId);
				}
				rsId.close();
			}
			
			return true;
		}
		else
			return false;
		}
		catch(Exception ex)
		{
			ex.printStackTrace();
			return false;
		}
	}
	
	private String getDateTime()
	{
		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		Date date = new Date();
		return dateFormat.format(date);
	}
	
	public void Close()
	{
		try
		{
			if (TaiKhoanRs != null) TaiKhoanRs.close();
			if (LoaiSanPhamRs != null) LoaiSanPhamRs.close();
		}
		catch (Exception e)
		{
			System.out.print("Exception  Close" + e.getMessage());
			e.printStackTrace();
		}
	}
	
	public boolean Delete()
	{
		String query ="";
		if(CheckReferences("LoaiSanPham_FK","SanPham"))
		{
			this.Msg="Loại sản phẩm này đã có sản phẩm bạn phải xóa sản phẩm trước  ";
			return false;
		} 
		if(CheckReferences("LoaiHangHoa_FK","Erp_MuaHang"))
		{
			this.Msg="Loại sản phẩm này đã có mua hàng bạn phải xóa mua hàng trước  ";
			return false;
		}
		if(CheckReferences("LOAISANPHAM_FK","ERP_CAUHINHDINHKHOANKETOAN"))
		{
			this.Msg="Loại sản phẩm này đã có cấu hình định khoản kế toán bạn phải xóa cấu hình định khoản kế toán trước  ";
			return false;
		}
		query = "Delete Erp_LoaiSanPham Where PK_SEQ =" + this.Id + "";
		if (!this.db.update(query))
		{
			this.Msg = "Không thể xóa loại sản phẩm này ";
			return false;
		}
				
		return true;
	}
	
	public boolean CheckUnique()
	{
		String query="";
		if(this.Id!=null)
			query = "Select count(*) as count From Erp_LoaiSanPham Where MA='" + this.Ma + "' AND PK_SEQ !='"+this.Id+"' AND CONGTY_FK = '" + this.ctyId + "' ";
		else 
			query="Select count(*) as count From Erp_LoaiSanPham Where MA='" + this.Ma + "' AND CONGTY_FK = '" + this.ctyId + "' ";
		System.out.println("____Kiem tra rang buoc_____ "+query);
		int count = 0;
		ResultSet rs = this.db.get(query);
		if (rs != null)
			try
			{
				while (rs.next())
				{
					count = rs.getInt("count");
				}
				rs.close();
				if (count > 0)
					return false;
			} catch (SQLException e)
			{
				return false;
			}
		return true;
	}
	
	public boolean CheckReferences(String column,String table)
	{
		String query="SELECT count("+column+") AS NUM  FROM "+table+" WHERE "+column+" ="+this.Id+""; 
		System.out.println("CheckReferences "+query);
    	ResultSet rs = db.get(query);
    	System.out.println("____Kiem tra rang buoc_____ "+query);
		try {//kiem tra ben san pham
		while(rs.next())
		{ if(rs.getString("num").equals("0"))
		   return false;
		}
		} catch (SQLException e) {
		
			e.printStackTrace();
		}
		 return true;
	}
	
	public String getTrangThai()
	{
		return this.TrangThai;
	}
	
	public void setTrangThai(String trangthai)
	{
		this.TrangThai = trangthai;
	}
	
	
	public void setChixem(String chixem) {
		
		this.chixem = chixem;
	}

	public String getChixem() {
		
		return this.chixem;
	}
	
}
