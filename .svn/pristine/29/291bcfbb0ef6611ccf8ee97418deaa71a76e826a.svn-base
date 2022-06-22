package geso.dms.distributor.beans.nganhangcongty.imp;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import geso.dms.center.db.sql.dbutils;
import geso.dms.distributor.beans.nganhangcongty.IErpNganHangCongTy;
public class ErpNganHangCongTy implements IErpNganHangCongTy
{
	String Id , CongTy , ctyId, SoTaiKhoan , ChuTaiKhoan , LoaiTien , ChiNhanh , NganHang , TaiKhoanKeToan , TrangThai , msg ,
	UserId;
	ResultSet rsNganHang , rsCongTy , rsLoaiTien , rsChiNhanh , rsTaiKhoan;
	dbutils db;
	String Masothue, nppId;
	
	public ErpNganHangCongTy( )
	{
		this.Id = "";
		this.ctyId = "";
		this.SoTaiKhoan = "";
		this.ChuTaiKhoan = "";
		this.LoaiTien = "";
		this.ChiNhanh = "";
		this.NganHang = "";
		this.Masothue= "";
		this.TaiKhoanKeToan = "";
		this.LoaiTien = "";
		this.TrangThai = "1";
		this.msg = "";
		this.UserId = "";
		this.nppId = "";
		this.CongTy = "";
		this.db = new dbutils();
	}
	
	public ErpNganHangCongTy( String id )
	{
		this.Id = id;
		this.ctyId = "";
		this.SoTaiKhoan = "";
		this.Masothue= "";
		this.ChuTaiKhoan = "";
		this.LoaiTien = "";
		this.ChiNhanh = "";
		this.NganHang = "";
		this.TaiKhoanKeToan = "";
		this.LoaiTien = "";
		this.TrangThai = "";
		this.msg = "";
		this.UserId = "";
		this.CongTy = "";
		this.nppId = "";
		db = new dbutils();
	}
	
	public String getId()
	{
		return this.Id;
	}
	
	public void setId(String id)
	{
		this.Id = id;
	}
	
	public String getMsg()
	{
		return this.msg;
	}
	
	public void setMsg(String msg)
	{
		this.msg = msg;
	}
	
	public String getCongTy()
	{
		return this.CongTy;
	}
	
	public void setCongTy(String congty)
	{
		this.CongTy = congty;
	}
	
	public String getCtyId()
	{
		return this.ctyId;
	}
	
	public void setCTyId(String ctyId)
	{
		this.ctyId = ctyId;
	}

	public String getChuTaiKhoan()
	{
		return this.ChuTaiKhoan;
	}
	
	public void setChuTaiKhoan(String chutaikhoan)
	{
		this.ChuTaiKhoan = chutaikhoan;
	}
	
	public String getLoaiTien()
	{
		return this.LoaiTien;
	}
	
	public void setLoaiTien(String loaitien)
	{
		this.LoaiTien = loaitien;
	}
	
	public String getNganHang()
	{
		return this.NganHang;
	}
	
	public void setNganHang(String nganhang)
	{
		this.NganHang = nganhang;
	}
	
	public String getSoTaiKhoan()
	{
		return this.SoTaiKhoan;
	}
	
	public void setSoTaiKhoan(String sotaikhoan)
	{
		this.SoTaiKhoan = sotaikhoan;
	}
	
	public String getChiNhanh()
	{
		return this.ChiNhanh;
	}
	
	public void setChiNhanh(String chinhanh)
	{
		this.ChiNhanh = chinhanh;
	}
	
	public void init()
	{
		this.getNppInfo();
		String query =
		" Select PK_SEQ,ISNULL(NganHang_FK,'0')NganHang,ISNULL(ChiNhanh_FK,'0') ChiNhanh," +
		" ISNULL(CongTy_FK,'0')CongTy,ISNULL(TaiKhoan_FK,'0')TaiKhoan," +
		" ISNULL(TienTe_FK,'0') TienTe,ISNULL(SoTaiKhoan,'')SoTaiKhoan,ChuTaiKhoan,TrangThai,isnull(masothue,'') as masothue " +
		" From Erp_NganHang_CongTy Where PK_SEQ=" + this.Id + "";
		System.out.println("Init " + query);
		ResultSet rs = this.db.get(query);
		try
		{
			if (rs != null) while (rs.next())
			{
				this.NganHang = rs.getString("NganHang");
				this.CongTy = rs.getString("CongTy");
				this.TaiKhoanKeToan = rs.getString("TaiKhoan");
				this.LoaiTien = rs.getString("TienTe");
				this.TrangThai = rs.getString("TrangThai");
				this.SoTaiKhoan = rs.getString("SoTaiKhoan");
				this.ChuTaiKhoan = rs.getString("ChuTaiKhoan");
				this.ChiNhanh = rs.getString("ChiNhanh");
				this.Masothue=rs.getString("masothue");
				
				System.out.println("ChiNhanh" + ChiNhanh);
				
			}
			rs.close();
		}
		catch (SQLException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void createaRs()
	{
		this.getNppInfo();
		
		String query = "Select PK_SEQ,Ma,Ten From Erp_NganHang Where TrangThai=1 ";
		this.rsNganHang = this.db.get(query);
		query = "Select PK_SEQ,Ma,Ten From Erp_TienTe ";
		this.rsLoaiTien = this.db.get(query);
		query = "Select PK_SEQ,Ma,Ten From ERP_CONGTY Where TrangThai=1";
		this.rsCongTy = this.db.get(query);
		query = "Select PK_SEQ,TenTaiKhoan Ten,SoHieuTaiKhoan As Ma From Erp_TaiKhoanKT Where TrangThai=1 AND NPP_FK = "+this.nppId+" ";
		this.rsTaiKhoan = this.db.get(query);
		 query ="Select PK_SEQ,Ten,Ma From Erp_ChiNhanh Where TrangThai=1 ";
		this.rsChiNhanh = this.db.get(query);
	}
	
	private void getNppInfo()
	{		
		//Phien ban moi
		geso.dms.distributor.util.Utility util=new geso.dms.distributor.util.Utility();
		this.nppId=util.getIdNhapp(this.UserId);
	}
	
	public boolean Update()
	{
		this.getNppInfo();
		String query =
				"Update Erp_NganHang_CongTy Set NPP_FK=" + this.nppId + " ,ChuTaiKhoan=N'" + this.ChuTaiKhoan +
				"',SoTaiKhoan='" + this.SoTaiKhoan + "',NganHang_FK=" + this.NganHang + ",ChiNhanh_FK=" + this.ChiNhanh +
				",TienTe_FK=" + this.LoaiTien + ",TaiKhoan_FK=" + this.TaiKhoanKeToan + ",TrangThai=" + this.TrangThai +
				",NguoiSua=" + this.UserId + ",NgaySua='" + getDateTime() + "' ,masothue=N'"+this.Masothue+"' Where PK_SEQ=" + this.Id + "";
				System.out.println("Update  Erp_NganHang_CongTy " + query);
				if (!this.db.update(query))
				{
					this.msg = "Không thể cập nhật " + query;
					return false;
				}
				else
					return true;
	}
	
	public boolean Create()
	{
		this.getNppInfo();
		String query =
		" Insert into Erp_NganHang_CongTy(NPP_FK,ChuTaiKhoan,SoTaiKhoan,NganHang_FK,ChiNhanh_FK,TaiKhoan_FK," +
		"NguoiTao,NguoiSua,NgayTao,NgaySua,TrangThai,TienTe_FK,MASOTHUE)"
		+ "" + "values(" +this.nppId +",N'" +this.ChuTaiKhoan +"','" +this.SoTaiKhoan +"'," +this.NganHang +
		"," +this.ChiNhanh +"," +this.TaiKhoanKeToan +"," +this.UserId +"," +"" +this.UserId +",'" +getDateTime() +
		"','" +	getDateTime() + "'," + this.TrangThai + "," + this.LoaiTien + ",N'"+this.Masothue+"')";
		
		System.out.println(query);
		if (this.db.update(query)) return true;
		else
		{
			this.msg = "Không thể tạo mới " + query;
			return false;
		}
	}
	
	public boolean Enable()
	{
		this.getNppInfo();
		String query = "Update Erp_NganHang_CongTy Set TrangThai=1 Where PK_SEQ ='" + this.Id + "'";
		System.out.println("Hoat dong " + query);
		System.out.print("Update" + query);
		if (!this.db.update(query)) return false;
		else
			return true;
	}
	
	public boolean Delete()
	{
		String query = "Delete From  Erp_NganHang_CongTy  Where PK_SEQ ='" + this.Id + "'";
		/*System.out.print("Update" + query);
		if (!this.db.update(query)) return false;
		else*/
			return true;
	}
	
	public ResultSet getCongTyRs()
	{
		return this.rsCongTy;
	}
	
	public void setCongTyRs(ResultSet congty)
	{
		this.rsCongTy = congty;
	}
	
	public ResultSet getLoaiTienRs()
	{
		return this.rsLoaiTien;
	}
	
	public void setLoaiTien(ResultSet loaitien)
	{
		this.rsLoaiTien = loaitien;
	}
	
	public ResultSet getChiNhanhRs()
	{
		return this.rsChiNhanh;
	}
	
	public void setNganHang(ResultSet nganhang)
	{
		this.rsNganHang = nganhang;
	}
	
	public ResultSet getNganHangRs()
	{
		return this.rsNganHang;
	}
	
	public void setChiNhanhRs(ResultSet chinhanh)
	{
		this.rsChiNhanh = chinhanh;
	}
	
	public void setTrangThai(String trangthai)
	{
		this.TrangThai = trangthai;
	}
	
	public String getTrangThai()
	{
		return this.TrangThai;
	}
	
	public String getTaiKhoanKeToan()
	{
		return this.TaiKhoanKeToan;
	}
	
	public void setTaiKhoanKeToan(String taikhoanketoan)
	{
		this.TaiKhoanKeToan = taikhoanketoan;
	}
	
	public ResultSet getTaiKhoanRs()
	{
		return this.rsTaiKhoan;
	}
	
	public void setTaiKhoanRs(ResultSet taikhoan)
	{
		this.rsTaiKhoan = taikhoan;
	}
	
	public void setUserId(String userId)
	{
		this.UserId = userId;
	}
	
	public String getUserId()
	{
		return this.UserId;
	}
	
	private String getDateTime()
	{
		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		Date date = new Date();
		return dateFormat.format(date);
	}
	
	public void closeDB(){
		try{
			if (rsNganHang != null)	rsNganHang.close();
			if (rsCongTy != null) rsCongTy.close();
			if (rsLoaiTien != null) rsLoaiTien.close();
			if (rsChiNhanh != null) rsChiNhanh.close();
			if (rsTaiKhoan != null) rsTaiKhoan.close();
		}catch(java.sql.SQLException e){}
	}

	
	public String getMasothue() {
		
		return this.Masothue;
	}

	
	public void setMasothue(String Masothue) {
		
		this.Masothue=Masothue;
	}
}
