package geso.dms.center.beans.daidienkinhdoanh.imp;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import geso.dms.center.beans.daidienkinhdoanh.IDaidienkinhdoanh;
import geso.dms.center.db.sql.Idbutils;
import geso.dms.center.db.sql.dbutils;
import geso.dms.center.util.Utility;

public class Daidienkinhdoanh implements IDaidienkinhdoanh
{
	private static final long serialVersionUID = -9217977546733610214L;
	String isPG = "0";
	String userId;
	String id;
	String ten;
	String sodienthoai;
	String diachi;
	String trangthai;
	String ngaytao;
	String nguoitao;
	String ngaysua;
	String nguoisua;
	String msg;
	boolean isdelete;	
	String kenhbanhang;
	String nhaphanphoi;
	String Macty;
	String Vitri;
	String KhuvucTT;
	String Sonamlamviec;
	String SoDTCty;
	String NgayvaoCty;
	String HDLD;
	String LoaiHD;
	String NgaykyHD;
	String NgayketthucHD;
	String TeamLeader;
	String SoTaiKhoan;
	String Email;
	String GhiChu;
	String matkhau;
	ResultSet nppList;
	String nppId;
	
	ResultSet gsbhList;
	String gsbhId;
	
	ResultSet RsDDKD;
	String ddkdTn;
	dbutils db;
	String PhanTramChuyen;

	String nganhang, chinhanh, manhanvien, mathuviec, cothechonTN;
	
	String maFAST;
	String isNVTT;
	String diabanId;
	String maERP;
	ResultSet diabanRs;
	
	public Daidienkinhdoanh(String[] param)
	{
		this.db = new dbutils();
		this.id = param[0];
		this.ten = param[1];
		this.sodienthoai = param[2];
		this.diachi = param[3];
		this.trangthai = param[4];
		this.ngaytao = param[5];
		this.nguoitao = param[6];
		this.ngaysua = param[7];
		this.nguoisua = param[8];
		this.kenhbanhang = param[9];
		this.nhaphanphoi = param[11];
		this.isdelete = true;
		this.msg = "";
		this.maFAST = "";
		this.nppId = "";
		this.isNVTT = "";
		this.nganhang = "";
		this.chinhanh = "";
		this.manhanvien = "";
		this.mathuviec = "";
		this.matkhau = "";
		this.cothechonTN = "0";
		this.maERP ="";
	}
	
	public Daidienkinhdoanh(String id)
	{
		this.db = new dbutils();
		this.id = id;
		this.ten = "";
		this.sodienthoai = "";
		this.diachi = "";
		this.trangthai = "2";
		this.ngaytao = "";
		this.nguoitao = "";
		this.ngaysua = "";
		this.nguoisua ="";
		this.kenhbanhang = "";
		this.nhaphanphoi = "";
		this.nppId ="";
		this.msg = "";
		this.ddkdTn="";
		this.PhanTramChuyen="0";
		///////////////////////
		this.Macty="";
		this.Vitri="";
		this.KhuvucTT="";
		this.Sonamlamviec="0";
		this.SoDTCty="";
		this.NgayvaoCty="";
		this.HDLD="";
		this.LoaiHD="";
		this.NgaykyHD="";
		this.NgayketthucHD="";
		this.TeamLeader="";
		this.SoTaiKhoan="";
		this.Email="";
		this.GhiChu="";
		this.maFAST = "";
		this.isNVTT = "";	
		this.nganhang = "";
		this.chinhanh = "";
		this.manhanvien = "";
		this.mathuviec = "";
		this.matkhau = "";
		this.cothechonTN = "0";
		this.maERP ="";
		/////////////////////////
		this.isdelete = true;
		if (id.length() > 0)
			this.init();
		else
			this.createRS();
	}
	
	public String getMaFAST() {
		return maFAST;
	}

	public void setMaFAST(String maFAST) {
		this.maFAST = maFAST;
	}
	
	public String getMaERP() {
		return maERP;
	}

	public void setMaERP(String maERP) {
		this.maERP = maERP;
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
	
	public String getSodt()
	{
		return this.sodienthoai;
	}
	
	public void setSodt(String sodienthoai)
	{
		this.sodienthoai = sodienthoai;
	}
	
	public String getDiachi()
	{
		return this.diachi;
	}
	
	public void setDiachi(String diachi)
	{
		this.diachi = diachi;
	}
	
	public String getTrangthai() 
	{
		return this.trangthai;
	}

	public void setTrangthai(String trangthai) 
	{
		this.trangthai = trangthai;
	}
	
	public String getNppId() 
	{		
		return this.nppId;
	}

	public void setNppId(String nppId) 
	{
		this.nppId = nppId;
	}

	public String getNgaytao()
	{
		return this.ngaytao;
	}
	
	public void setNgaytao(String ngaytao)
	{
		this.ngaytao = ngaytao;
	}
	
	public String getNguoitao()
	{
		return this.nguoitao;
	}
	
	public void setNguoitao(String nguoitao)
	{
		this.nguoitao = nguoitao;
	}
	
	public String getNgaysua()
	{
		return this.ngaysua;
	}
	
	public void setNgaysua(String ngaysua)
	{
		this.ngaysua = ngaysua;
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
	
	public String getKenhbanhang() 
	{
		return this.kenhbanhang;
	}

	public void setKenhbanhang(String kenhbanhang)
	{
		this.kenhbanhang = kenhbanhang;
	}

	public String getNhaphanphoi() 
	{
		return this.nhaphanphoi;
	}

	public void setNhaphanphoi(String nhaphanphoi) 
	{
		this.nhaphanphoi = nhaphanphoi;
	}

	/*#####################Thêm mới##########################*/
	public void setMaCongTy(String macty)
	{
		this.Macty=macty;
	}
	public String getMaCongTy()
	{
		return this.Macty;
	}
	public void setViTri(String vitri)
	{
		this.Vitri=vitri;
	}
	public String getViTri()
	{
		return this.Vitri;
	}
	public void setKhuVucTT(String khuvuctt)
	{
		this.KhuvucTT=khuvuctt;
	}
	public String getKhuVucTT()
	{
		return this.KhuvucTT;
	}
	public void setSoNamLamViec(String nam)
	{
		this.Sonamlamviec=nam;
	}
	public String getSoNamLamViec()
	{
		return this.Sonamlamviec;
	}
	public void setSoDTCty(String sodt)
	{
		this.SoDTCty=sodt;
	}
	public String getSoDTCty()
	{
		return this.SoDTCty;
	}
	public void setNgayVaoCty(String ngay)
	{
		this.NgayvaoCty=ngay;
	}
	public String getNgayVaoCty()
	{
		return this.NgayvaoCty;
	}
	public void setHDLD(String hdld)
	{
		this.HDLD=hdld;
	}
	public String getHDLD()
	{
		return this.HDLD;
	}
	public void setLoaiHD(String loaihd)
	{
		this.LoaiHD=loaihd;
	}
	public String getLoaiHD()
	{
		return this.LoaiHD;
	}
	public void setNgayKyHD(String ngay)
	{
		this.NgaykyHD=ngay;
	}
	public String getNgayKyHD()
	{
		return this.NgaykyHD;
	}
	public void setNgayKetThucHD(String ngay)
	{
		this.NgayketthucHD=ngay;
	}
	public String getNgayKetThucHD()
	{
		return this.NgayketthucHD;
	}
	public void setTeamLeader(String teamleader)
	{
		this.TeamLeader=teamleader;
	}
	public String getTeamLeader()
	{
		return this.TeamLeader;
	}
	public void setSoTaiKhoan(String sotk)
	{
		this.SoTaiKhoan=sotk;
	}
	public String getSoTaiKhoan()
	{
		return this.SoTaiKhoan;
	}
	public void setEmail(String email)
	{
		this.Email=email;
	}
	public String getEmail()
	{
		return this.Email;
	}
	public void setGhiChu(String ghichu)
	{
		this.GhiChu=ghichu;
	}
	public String getGhiChu()
	{
		return this.GhiChu;
	}
	/*#######################################################*/
	
	public ResultSet getGsbhList() 
	{
		return this.gsbhList;
	}
	
	public void setGsbhList(ResultSet gsbhList) 
	{
		this.gsbhList = gsbhList;
	}

	public void setNppList(ResultSet npplist)
	{
		this.nppList = npplist;
	}
	
	public ResultSet getNppList() 
	{
		return this.nppList;
	}
	
	public boolean getIsDelete() 
	{
		return this.isdelete;
	}

	public void setIsDelete(boolean isDelete) 
	{
		this.isdelete = isDelete;
	}
	
	public void createNppList()
	{	
	    String query = "\n select a.pk_seq as ID, a.ten as nppTen, 2 as STT " +
		"\n from nhaphanphoi a where isKHACHHANG = '0' and trangthai = 1 and pk_seq != 1 ";
		this.nppList = this.db.get(query);
		System.out.println("Danh sach NPP lay duoc: " + query + "\n");
	}
	
	public void createGsbhList()
	{
		
		if ( this.kenhbanhang!=null && this.kenhbanhang.length()>0 &&this.nppId!=null && this.nppId.length()>0)
		{
			String query =  "\n select pk_seq as ID, TEN " +
							"\n from GiamSatBanHang a " +
							"\n where   a.trangthai = 1 and  a.kbh_fk =  "+ this.kenhbanhang +
							"\n  and a.pk_seq in (  select gsbh_fk from nhapp_giamsatbh  where npp_fk in (" + this.nppId + ")  )	";
							
			this.gsbhList  = db.get(query);
			System.out.println("Danh sach GSBH lay duoc: "+query);
		}
		
	}
	
	public void createRS() 
	{
		String query = "";
		
		query = "SELECT PK_SEQ, TEN FROM DIABAN WHERE TRANGTHAI = 1 ";
		this.diabanRs = db.get(query);

		createNppList();
		createGsbhList();
		CreateDDKDList();

		//CHECK CO THE CHON NHAN VIEN TIEN NHIEM HAY KHONG
		if (Utility.isValid(this.id))
		{
			query = "\n select COUNT(*) as soDong " +
			"\n from " +
			"\n ( " +
			"\n     select PK_SEQ, DDKDTIENNHIEM from DAIDIENKINHDOANH where PK_SEQ = '" + this.id + "' and DDKDTIENNHIEM is not null " +
			"\n     union all " +
			"\n     select PK_SEQ, DDKD_FK from DONHANG where DDKD_FK = '" + this.id + "' " +
			"\n ) TienNhiem ";
			System.out.println("___CHECK CO THE CHON TIEN NHIEM: " + query);
			ResultSet rsTN = db.get(query);
			try 
			{
				if (rsTN.next())
				{
					if (rsTN.getInt("soDong") <= 0)
					{
						this.cothechonTN = "1";
					}
				}
				rsTN.close();
			} 
			catch (Exception e) {
				e.printStackTrace();
			}
		}
	}	
	
	private void CreateDDKDList() {

		String query = "select pk_seq, ten+' - ' +manhanvien as Ten from daidienkinhdoanh where npp_fk = "+ this.nppId;
		if (Utility.isValid(this.nppId))
			this.RsDDKD = this.db.get(query);
	}

	public boolean CreateDdkd() 
	{
		try
		{
			this.db.getConnection().setAutoCommit(false);
			if (userId == null || userId == "")
			{
				this.db.getConnection().rollback();
				this.msg = "Tài khoản đã hết hạn, vui lòng đăng nhập lại";
				return false;
			}
			if(this.kenhbanhang == null || this.kenhbanhang.trim().length() <=0)
			{
				this.msg = "Vui lòng nhập Kênh bán hàng";
				this.db.getConnection().rollback();
				return false;
			}
			

			if(this.isPG != null && this.isPG.equals("1"))
				this.isNVTT = "0";
			
			if (this.isNVTT.equals("0") && this.nppId.trim().length() <= 0)
			{
				this.db.getConnection().rollback();
				this.msg = "Bạn phải chọn chi nhánh trực thuộc cho NVBH.";
				return false;
			}
			System.out.println("ketqua:: "+checkMaFast("", this.maFAST, db));
			if (!checkMaFast("", this.maFAST, db))
			{
				this.db.getConnection().rollback();
				this.msg = "Mã đã tồn tại, vui lòng nhập lại";
				return false;
			}

			this.ngaytao = getDateTime();
			this.nguoitao = this.userId;		

			String maGSBH = this.maFAST;
			String db_fk = "null";
			if (this.diabanId.trim().length() > 0)
				db_fk = this.diabanId;

			String query = "\n insert into daidienkinhdoanh(KBH_FK,isPG,diaban_fk, manhanvien, ten, dienthoai, diachi,  trangthai, nguoitao, nguoisua, ngaytao, ngaysua, ddkdtiennhiem, phantramchuyen, " +
			"\n macty, vitri, khuvuctt, sonamlamviec, sodtcongty, ngayvaocongty, hdld, loaihd,ngaykyhd, ngayketthuchd, teamleader, sotaikhoan, email, ghichu, nganhang, chinhanh,mathuviec, matkhau, tructhuoc_fk , mafast, maERP) " +
			"\n  values( "+this.kenhbanhang+","+this.isPG+","+ db_fk  +" ,'" + maGSBH + "', N'" + this.ten +"','" + this.sodienthoai + "',N'"+ this.diachi + "', '1', '" + this.nguoitao + "','" + this.nguoitao + "','" + this.ngaytao + "','" + this.ngaytao + "',"+(this.ddkdTn.trim().length()<=0?"NULL":this.ddkdTn)+",'0','"+this.Macty+"'," +
			"\n N'"+this.Vitri+"',N'"+this.KhuvucTT+"',N'"+this.Sonamlamviec+"','"+this.SoDTCty+"','"+this.NgayvaoCty+"',N'"+this.HDLD+"',N'"+this.LoaiHD+"','"+this.NgaykyHD+"','"+this.NgayketthucHD+"',N'"+this.TeamLeader+"','"+this.SoTaiKhoan+"','"+this.Email+"',N'"+this.GhiChu+"', N'"+this.nganhang+"', N'"+this.chinhanh+"',  N'"+this.mathuviec+"', PWDENCRYPT('"+this.matkhau+"'), '" + this.isNVTT + "', '"+ this.maFAST + "', N'"+this.maERP+"')";		
			if (!(this.db.update(query))){
				this.msg = "Exception 1";
				this.db.getConnection().rollback();
				return false;
			}
			
			query = "SELECT SCOPE_IDENTITY() as ddkdId";
			ResultSet rs = this.db.get(query);
			rs.next();
			this.id = rs.getString("ddkdId");			
			query = "update daidienkinhdoanh set smartid='"+this.id+"' where pk_seq=" + this.id;
			db.update(query);

			if (this.gsbhId != null && this.gsbhId.length() > 0)
			{		
				query = "insert into ddkd_gsbh values('"+ this.id + "','" + this.gsbhId + "')";
				if (!(this.db.update(query)))
				{
					this.msg = "Không thể tạo mới NVBH 2.";
					this.db.getConnection().rollback();
					return false;
				}
			}

			if (Utility.isValid(this.nppId))
			{		
				query = "\n insert DAIDIENKINHDOANH_NPP(ddkd_fk, npp_fk) select '" + this.id + "', pk_seq " +
				"\n from NHAPHANPHOI where pk_seq in ( " + this.nppId + " ) ";
				if (!(this.db.update(query)))
				{
					this.msg = "Không thể tạo mới NVBH 3.";
					this.db.getConnection().rollback();
					return false;
				}
			}
			rs.close();

			String command = " UPDATE DAIDIENKINHDOANH SET TIMKIEM =upper(dbo.ftBoDau(ten))+' '+UPPER(dbo.ftBoDau(diachi))+' '+UPPER(dbo.ftBoDau(manhanvien)) +' '+UPPER(dbo.ftBoDau(Email)) + ' '+DIENTHOAI where pk_seq='"+this.id+"'";
			if (!db.update(command))
			{
				this.msg = "Không thể tạo mới NVBH 4.";
				this.db.getConnection().rollback();
				return false;
			}

			if (this.isNVTT.equals("0")  )  //NVBH THUOC 1 NPP MOI TU TAO
			{
				query =  "\n INSERT INTO TUYENBANHANG(DIENGIAI, NGAYLAMVIEC, NGAYTAO, NGAYSUA, NGUOITAO, NGUOISUA, DDKD_FK, NPP_FK, NGAYID) "+
				"\n select 'T' + cast(ngay.ngayId  as varchar(10)) + '_' + a.TEN + '_' + npp.mafast ,  " +
				"\n     ngay.ngaylv AS NGAYLAMVIEC, '" + getDateTime() + "', '" + getDateTime() + "', '" + this.userId + "', '" + this.userId + "', A.PK_SEQ AS DDKD, B.NPP_FK, ngay.ngayId   " +
				"\n from DAIDIENKINHDOANH a inner join DAIDIENKINHDOANH_NPP b on a.pk_seq = b.ddkd_fk"+
				"\n inner join NHAPHANPHOI npp on npp.PK_SEQ = b.npp_fk, "+
				"\n " +
				"\n (  " +
				"\n	    select 2 as ngayId,N'Thứ hai' as ngaylv union " +
				"\n	    select 3 as ngayId ,N'Thứ ba' union " +
				"\n	    select 4 as ngayId ,N'Thứ tư' union " +
				"\n	    select 5 as ngayId,N'Thứ năm' union " +
				"\n	    select 6 as ngayId,N'Thứ sáu' union " +
				"\n	    select 7 as ngayId ,N'Thứ bảy' union " +
				"\n	    select 1 as ngayId ,N'Chủ nhật'  " +
				"\n	) AS ngay   " +
				"\n	where a.PK_SEQ = '" + this.id + "' and not exists (select 1 from TUYENBANHANG tbh where tbh.ddkd_fk = a.pk_Seq and tbh.npp_fk = b.npp_fk and tbh.ngayId = ngay.ngayId )   ";
				if (!db.update(query))
				{	
					this.db.getConnection().rollback();
					return false;
				}
			}

			this.db.getConnection().commit();
		}
		catch(Exception e)
		{
			e.printStackTrace();
			try { this.db.getConnection().rollback(); } catch (SQLException e1) { e1.printStackTrace(); };
			this.msg = "Lỗi ngoại lệ tạo mới: "+e.getMessage();
			return false;
		}
		finally{ try { this.db.getConnection().setAutoCommit(true); } catch (SQLException e) { e.printStackTrace(); } }
		return true;
	}

	public boolean UpdateDdkd() 
	{
		this.ngaysua = getDateTime();
		this.nguoisua = this.userId;
		
		try
		{
			this.db.getConnection().setAutoCommit(false);

			if (userId == null || userId == "")
			{
				this.db.getConnection().rollback();
				
				this.msg = "Tài khoản đã hết hạn, vui lòng đăng nhập lại";
				return false;
			}
			if(this.kenhbanhang == null || this.kenhbanhang.trim().length() <=0)
			{
				this.msg = "Vui lòng chọn kênh";
				return false;
			}

			
			if (!checkMaFast(this.id, this.maFAST, db))
			{
				this.db.getConnection().rollback();
				
				this.msg = "Mã DMS đã tồn tại, vui lòng nhập lại mã DMS khác.";
				return false;
			}

			String db_fk = "null";
			if (Utility.isValid(this.diabanId))
				db_fk = this.diabanId;

			String query = "\n update daidienkinhdoanh set kbh_fk = "+this.kenhbanhang+",diaban_fk = "+db_fk+", mafast = '"+this.maFAST+"', ten = N'" + this.ten + "', " +
			"\n dienthoai = '" + this.sodienthoai + "', diachi = N'"+ this.diachi + "', trangthai = '" + this.trangthai + "', " +
			"\n nguoisua = '" + this.userId + "', ngaysua = '" + this.ngaysua + "', " +
			"\n macty = '"+this.Macty+"', vitri = N'"+this.Vitri+"', khuvucTT = N'"+this.KhuvucTT+"', " +
			"\n sonamlamviec = N'"+this.Sonamlamviec+"', sodtcongty = '"+this.SoDTCty+"', " +
			"\n ngayvaocongty = '"+this.NgayvaoCty+"', hdld = N'"+this.HDLD+"', loaihd = N'"+this.LoaiHD+"', " +
			"\n ngaykyhd = '"+this.NgaykyHD+"', nganhang = N'"+this.nganhang+"', chinhanh = N'"+this.chinhanh+"', " +
			"\n mathuviec = N'"+this.mathuviec+"', ngayketthuchd = '"+this.NgayketthucHD+"', " +
			"\n teamleader = N'"+this.TeamLeader+"', sotaikhoan = '"+this.SoTaiKhoan+"', " +
			"\n email = '"+this.Email+"', ghichu = N'"+this.GhiChu+"', tructhuoc_fk = '" + this.isNVTT + "' , maERP = N'"+ this.maERP+"' ";
			
			if (Utility.isValid(this.matkhau)) 
			{
				query += ", matkhau = PWDENCRYPT('" + this.matkhau.trim() +"') ";
			}
			
			query += "\n where pk_seq = '" + this.id + "'" ;
			System.out.println("Update query: "+query);
			if (!this.db.update(query))
			{
				this.db.getConnection().rollback();
				
				this.msg = "Exception 1.";
				return false;
			}
			
			query = "delete ddkd_gsbh where ddkd_fk = '" + this.id + "'";
			if (!this.db.update(query))
			{
				this.db.getConnection().rollback();
				
				this.msg = "Exception 2.";
				return false;
			}

			if (this.gsbhId != null && this.gsbhId.length() > 0)
			{		
				query = "insert into ddkd_gsbh values('"+ this.id + "','" + this.gsbhId + "')";
				if (!(this.db.update(query)))
				{
					this.db.getConnection().rollback();
					
					this.msg = "Exception 3.";
					return false;
				}
			}

			query = "delete DAIDIENKINHDOANH_NPP where ddkd_fk = '" + this.id + "'";
			if (!this.db.update(query))
			{
				this.db.getConnection().rollback();
				
				this.msg = "Exception 4.";
				return false;
			}

			if (Utility.isValid(this.nppId))
			{		
				query = "\n insert DAIDIENKINHDOANH_NPP(ddkd_fk, npp_fk) " +
				"\n select '" + this.id + "', pk_seq " +
				"\n from NHAPHANPHOI where pk_seq in ( " + this.nppId + " ) ";
				System.out.println("---CHEN NPP: " + query );
				if (!(this.db.update(query)))
				{
					this.db.getConnection().rollback();
					this.msg = "Exception 5.";
					return false;
				}
			}

			query =  "\n INSERT INTO TUYENBANHANG(DIENGIAI, NGAYLAMVIEC, NGAYTAO, NGAYSUA, NGUOITAO, NGUOISUA, DDKD_FK, NPP_FK, NGAYID) "+
			"\n select 'T' + cast(ngay.ngayId  as varchar(10)) + '_' + a.TEN + '_' + npp.mafast ,  " +
			"\n     ngay.ngaylv AS NGAYLAMVIEC, '" + getDateTime() + "', '" + getDateTime() + "', '" + this.userId + "', '" + this.userId + "', A.PK_SEQ AS DDKD, B.NPP_FK, ngay.ngayId   " +
			"\n from DAIDIENKINHDOANH a inner join DAIDIENKINHDOANH_NPP b on a.pk_seq = b.ddkd_fk"+
			"\n inner join NHAPHANPHOI npp on npp.PK_SEQ = b.npp_fk, "+
			"\n " +
			"\n (  " +
			"\n	    select 2 as ngayId,N'Thứ hai' as ngaylv union " +
			"\n	    select 3 as ngayId ,N'Thứ ba' union " +
			"\n	    select 4 as ngayId ,N'Thứ tư' union " +
			"\n	    select 5 as ngayId,N'Thứ năm' union " +
			"\n	    select 6 as ngayId,N'Thứ sáu' union " +
			"\n	    select 7 as ngayId ,N'Thứ bảy' union " +
			"\n	    select 1 as ngayId ,N'Chủ nhật'  " +
			"\n	) AS ngay   " +
			"\n	where a.PK_SEQ = '" + this.id + "' and not exists (select 1 from TUYENBANHANG tbh where tbh.ddkd_fk = a.pk_Seq and tbh.npp_fk = b.npp_fk and tbh.ngayId = ngay.ngayId )   ";
			if (!db.update(query))
			{
				this.db.getConnection().rollback();
				this.msg = "Exception 6.";
				return false;
			}
			
			query = "update TUYENBANHANG set DIENGIAI =  cast(ngayId  as varchar(10)) + N'_' + N'" + this.ten + "' where ddkd_fk = '" + this.id + "'";
			if (!this.db.update(query))
			{
				this.db.getConnection().rollback();
				this.msg = "Exception 7.";
				return false;
			}

			String command = " UPDATE DAIDIENKINHDOANH SET TIMKIEM =upper(dbo.ftBoDau(ten))+' '+UPPER(dbo.ftBoDau(diachi))+' '+UPPER(dbo.ftBoDau(manhanvien)) +' '+UPPER(dbo.ftBoDau(Email)) + ' '+DIENTHOAI where pk_seq='"+this.id+"'";
			if (!db.update(command))
			{
				this.db.getConnection().rollback();
				this.msg = "Exception 8.";
				return false;
			}
			this.db.getConnection().commit();
		}
		catch(Exception e)
		{
			this.msg = "Exception: " + e.getMessage();
			e.printStackTrace();
			try { this.db.getConnection().rollback(); } catch (SQLException e1) { e1.printStackTrace(); }
			return false;
		} finally{ try { this.db.getConnection().setAutoCommit(true); } catch (SQLException e) { e.printStackTrace(); } }
		return true;
	}	
	
	private void init() 
	{
		String query = "\n select  a.kbh_fk,a.diaban_fk , isnull(a.mafast,'') as mafast, a.pk_seq as id, a.ten, a.dienthoai, a.diachi, a.trangthai, " +
		"\n ISNULL(a.macty,'') as macty, ISNULL(a.vitri,'') as vitri, ISNULL(a.khuvuctt,'') as khuvuctt, " +
		"\n ISNULL(a.sonamlamviec,0) as sonamlamviec, ISNULL(a.sodtcongty,'') as sodtcongty, ISNULL(a.ngayvaocongty,'') as ngayvaocongty, " +
		"\n ISNULL(a.hdld,'') as hdld, ISNULL(a.loaihd,'') as loaihd, ISNULL(a.ngaykyhd,'') as ngaykyhd, ISNULL(a.ngayketthuchd,'') as ngayketthuchd, " +
		"\n ISNULL(a.teamleader,'') as teamleader, ISNULL(a.sotaikhoan,'') as sotaikhoan, ISNULL(a.email,'') as email, ISNULL(a.ghichu,'') as ghichu, " +
		"\n ISNULL(a.NGANHANG,'') as nganhang, ISNULL(a.CHINHANH,'') as chinhanh, A.PK_SEQ as manhanvien, " +
		"\n ISNULL(a.MATHUVIEC,'') as mathuviec, a.DDKDTIENNHIEM, isnull(a.tructhuoc_fk, 0) as tructhuoc_fk, MaERP  " +
		"\n from daidienkinhdoanh a " + 
		"\n where a.pk_seq = "+this.id;
		System.out.println("Init: "+query);
		ResultSet rs = this.db.get(query);
		try
		{
			rs.next();    
			this.kenhbanhang = rs.getString("kbh_fk") == null ? "":  rs.getString("kbh_fk");
			this.id = rs.getString("id");
			this.ten = rs.getString("ten");
			this.sodienthoai = rs.getString("dienthoai");
			this.diachi = rs.getString("diachi");
			this.trangthai = rs.getString("trangthai");
			this.Macty = rs.getString("macty");
			this.Vitri = rs.getString("vitri");
			this.KhuvucTT = rs.getString("khuvuctt");
			this.Sonamlamviec = rs.getString("sonamlamviec");
			this.SoDTCty = rs.getString("sodtcongty");
			this.NgayvaoCty = rs.getString("ngayvaocongty");
			this.HDLD = rs.getString("hdld");
			this.LoaiHD = rs.getString("loaihd");
			this.NgaykyHD = rs.getString("ngaykyhd");
			this.NgayketthucHD = rs.getString("ngayketthuchd");
			this.TeamLeader = rs.getString("teamleader");
			this.SoTaiKhoan = rs.getString("sotaikhoan");
			this.Email = rs.getString("email");
			this.GhiChu = rs.getString("ghichu");
			this.isNVTT = rs.getString("tructhuoc_fk");
			this.nganhang = rs.getString("nganhang");
			this.chinhanh = rs.getString("chinhanh");
			this.manhanvien = rs.getString("manhanvien");
			this.mathuviec = rs.getString("mathuviec");
			this.maFAST = rs.getString("mafast");
			this.ddkdTn = rs.getString("DDKDTIENNHIEM") == null ? "" : rs.getString("DDKDTIENNHIEM");
			this.diabanId = rs.getString("diaban_fk") == null ? "" : rs.getString("diaban_fk");
			this.maERP = rs.getString("maERP");

			query = "select gsbh_fk as gsbhId from ddkd_gsbh where ddkd_fk = '" + this.id + "'";
			ResultSet rs2 = this.db.get(query);
			if (rs2 != null)
			{
				while(rs2.next())
				{
					this.gsbhId = rs2.getString("gsbhId");
				}
				rs2.close();
			}

			query = "select npp_fk from DAIDIENKINHDOANH_NPP where ddkd_fk = '" + this.id + "'";
			rs2 = this.db.get(query);
			if (rs2 != null)
			{
				while(rs2.next())
				{
					this.nppId += rs2.getString("npp_fk") + ",";
				}
				rs2.close();

				if (Utility.isValid(this.nppId))
				{
					this.nppId = this.nppId.substring(0, this.nppId.length() - 1);
				}
			}

		}
		catch(Exception e)
		{
			e.printStackTrace();
		}

		createRS(); 
	}
	
	private String getDateTime() 
	{
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date date = new Date();
        return dateFormat.format(date);	
	}
	
	public void DBClose() {
		
		try{
			if (nppList!=null){
				nppList.close();		
			}

			if (gsbhList!=null){
				gsbhList.close();

			}
			
			if (RsDDKD!=null){
				RsDDKD.close();
			}
			
			if (diabanRs!=null){
				diabanRs.close();
			}
			if (db!=null){
				db.shutDown();
			}
		}
		catch(Exception er){
			//er.printStackTrace();
		}
	}

	
	public ResultSet GetRsDDKDTienNhiem() {
		return this.RsDDKD;
	}

	
	public void setDDKDTn(String id) {
		this.ddkdTn=id;
	}

	
	public String getDDKDTn() {
		return this.ddkdTn;
	}

	
	public void setPhantramChuyen(String phantram) {
		this.PhanTramChuyen=phantram;
	}

	
	public String getPhanTramChuyen() {
		return this.PhanTramChuyen;
	}
	
	public void setNganHang(String nganhang) {
		this.nganhang = nganhang;
	}

	
	public String getNganHang() {
		return this.nganhang;
	}
	
	public void setChiNhanh(String chinhanh) {
		this.chinhanh = chinhanh;
	}
	
	public String getChiNhanh() {
		return this.chinhanh;
	}
	
	public void setMaNhanVien(String manhanvien) {
		this.manhanvien = manhanvien;
	}
	
	public String getMaNhanVien() {
		return this.manhanvien;
	}
	
	public void setMaThuViec(String mathuviec) {
		this.mathuviec = mathuviec;
	}
	
	public String getMaThuViec() {
		return this.mathuviec;
	}
	
	public void setMatkhau(String matkhau) {
		this.matkhau = matkhau;
	}
	
	public String getMatkhau() {
		return this.matkhau;
	}
	
	public void setCothechonTN(String mathuviec) {		
		this.cothechonTN = mathuviec;
	}
	
	public String getCothechonTN() {		
		return this.cothechonTN;
	}

	public String getGsbanhang() {
		return this.gsbhId;
	}

	public void setGsbanhang(String gsbanhang) {
		this.gsbhId = gsbanhang;
	}
	
	public String getIsNVTT() {
		return this.isNVTT;
	}

	public void setIsNVTT(String nvtt) {		
		this.isNVTT = nvtt;		
	}
	
	public String getDiabanId() {
		return diabanId;
	}
	
	public void setDiabanId(String diabanId) {
		this.diabanId = diabanId;
	}
	
	public ResultSet getDiabanRs() {
		return diabanRs;
	}	
	
	private boolean checkMaFast(String id, String mafast, Idbutils db) {
		String query = "\n select count(*) c from daidienkinhdoanh " +
		"\n where mafast = N'"+mafast+"'";
		
		  if (Utility.isValid(id)) { query += "\n and pk_seq != "+id; }
		 
		
		ResultSet rs = db.get(query);
		try {
			String check;
			rs.next();
			check = rs.getString("c");		
			
			rs.close();
			System.out.println("day ne: "+ check);
			if (!check.equals("0"))
				return false;		
		}
		catch (Exception e) {
			e.printStackTrace();
			return false;
		}
		
		return true;
	}
	public String getIsPG() {
		return isPG;
	}
	public void setIsPG(String isPG) {
		this.isPG = isPG;
	}
	public ResultSet getKbhRs()
	{
		String query = " select pk_seq,ten from kenhbanhang where trangthai = 1 ";
		return db.get(query);
	}
}