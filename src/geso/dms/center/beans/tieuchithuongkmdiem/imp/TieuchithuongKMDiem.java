package geso.dms.center.beans.tieuchithuongkmdiem.imp;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Enumeration;
import java.util.Hashtable;

import geso.dms.center.beans.tieuchithuong.ITieuchithuongTL;
import geso.dms.center.beans.tieuchithuongkmdiem.ITieuchithuongKMDiem;
import geso.dms.center.db.sql.dbutils;

public class TieuchithuongKMDiem implements ITieuchithuongKMDiem
{
	String userId;
	String id;
	String scheme;
	String thang;
	String nam;
	String diengiai;
	String kt = "0";
	ResultSet sanphamRs;
	String spIds;
	ResultSet nppRs;
	String nppIds;
	String nppIds1 = "";
	String nppIds2 = "";
	String nppIds3 = "";
	String nppIds4 = "";
	String nppIds5 = "";
	String active = "0";
	ResultSet kenhRs;
	String kenhIds;
	ResultSet vungRs;
	String vungIds;
	ResultSet kvRs;
	String kvIds;
	String mucnpp = "0";
	ResultSet nhomsanphamRs;
	String nhomspIds;
	
	String[] diengiaiMuc;
	String ghighu = "";
	String [] httt1;
	String[] tumuc;
	String[] denmuc;
	String[] thuongSR;
	String[] thuongTDSR;
	String[] thuongSS;
	String[] thuongTDSS;
	String[] thuongASM;
	String[] thuongTDASM;
	
	String[] diengiaiMuc3;
	String[] tumuc3;
	String[] denmuc3;
	String[] thuongSR3;
	String[] thuongTDSR3;
	String[] thuongSS3;
	String[] thuongTDSS3;
	String[] thuongASM3;
	String[] thuongTDASM3;
	
	String mucvuot;
	String ckMucvuot;
	String dvMucvuot;
	
	
	String hinhthucTra;
	String[] maspTra;
	String[] tenspTra;
	String[] isspTra;
	String[] soluongspTra;
	String[] dongiaspTra;
	
	Hashtable<String, String> maspTraTT = new Hashtable<String, String>();;
	Hashtable<String, String> tenspTraTT = new Hashtable<String, String>();;
	Hashtable<String, String> soluongTT = new Hashtable<String, String>();
	Hashtable<String,String>  quydoiTT = new Hashtable<String, String>();
	String doanhsotheoThung;
	String msg;

	String httt;
	String ptchietkhau;
	
	Hashtable<String, String> muc_tiendo;
	Hashtable<String, String> muc_spTRA;
	Hashtable<String, String> phanbo;
	Hashtable<String, String> phanbo1 = new Hashtable<String, String>();
	Hashtable<String, String> phanbo2 = new Hashtable<String, String>();
	Hashtable<String, String> phanbo3 = new Hashtable<String, String>();
	Hashtable<String, String> phanbo4 = new Hashtable<String, String>();
	Hashtable<String, String> phanbo5 = new Hashtable<String, String>();
	
	Hashtable<String, String> dieukien;
	Hashtable<String, String> quydoi;
	
	String tungay;
	String dengay;
	String khoId;
	ResultSet khoRs;
	
	String phanloai;
	
	dbutils db;
	
	String[] DKKMTICHLUY_KHACHHANG_Id;	
	String[] khDcDuyet;
	String[] khDcDuyetDisplay;
	ResultSet khDangKyRs;
	
	String ngaytb_tungay;
	String ngaytb_dengay;
	
	String phaidangky;
	String tinhtheosl;
	
	public TieuchithuongKMDiem()
	{
		this.id = "";
		this.thang = "";
		this.nam = "";
		this.diengiai = "";
		this.msg = "";
		this.scheme = "";
		this.spIds = "";
		this.nppIds = "";
		
		this.mucvuot = "";
		this.ckMucvuot = "";
		this.dvMucvuot = "";
		
		this.vungIds = "";
		this.kvIds = "";
		this.kenhIds = "";
		this.hinhthucTra = "0";		
		
		this.doanhsotheoThung = "0";
		this.httt = "0";
		this.ptchietkhau = "0";
	
		this.muc_tiendo = new Hashtable<String, String>();
		this.muc_spTRA = new Hashtable<String, String>();
		this.phanbo = new Hashtable<String, String>();
		this.phanbo1 = new Hashtable<String, String>();
		this.phanbo2 = new Hashtable<String, String>();
		this.phanbo3 = new Hashtable<String, String>();
		this.phanbo4 = new Hashtable<String, String>();
		this.phanbo5 = new Hashtable<String, String>();
		
		this.dieukien = new Hashtable<String, String>();
		this.quydoi = new Hashtable<String, String>();
		 nppIds1 = "";
		 nppIds2 = "";
		 nppIds3 = "";
		 nppIds4 = "";
		 nppIds5 = "";
		this.tungay = "";
		this.dengay = "";
		this.khoId = "";
		this.phanloai = "0";
		this.nhomspIds = "";
		this.mucnpp = "0";
		this.phaidangky = "0";
		
		this.ngaytb_tungay = "";
		this.ngaytb_dengay = "";
		this.tinhtheosl = "0";
		this.db = new dbutils();
	}
	
	public TieuchithuongKMDiem(String id)
	{
		this.id = id;
		this.thang = "";
		this.nam = "";
		this.diengiai = "";
		this.msg = "";
		this.scheme = "";
		this.spIds = "";
		this.nppIds = "";
		nppIds1 = "";
		nppIds2 = "";
		nppIds3 = "";
		nppIds4 = "";
		nppIds5 = "";
		this.mucvuot = "";
		this.ckMucvuot = "";
		this.dvMucvuot = "";
		
		this.vungIds = "";
		this.kvIds = "";
		this.kenhIds = "";
		this.hinhthucTra = "0";	
		
		this.doanhsotheoThung = "0";
		this.httt = "0";
		this.ptchietkhau = "0";
		
		this.muc_tiendo = new Hashtable<String, String>();
		this.muc_spTRA = new Hashtable<String, String>();
		this.phanbo = new Hashtable<String, String>();
		this.phanbo1 = new Hashtable<String, String>();
		this.phanbo2 = new Hashtable<String, String>();
		this.phanbo3 = new Hashtable<String, String>();
		this.phanbo4 = new Hashtable<String, String>();
		this.phanbo5 = new Hashtable<String, String>();
		this.mucnpp = "0";
		this.dieukien = new Hashtable<String, String>();
		this.quydoi = new Hashtable<String, String>();
		
		this.tungay = "";
		this.dengay = "";
		this.khoId = "";
		this.phanloai = "0";
		this.nhomspIds = "";
		this.phaidangky = "0";
		this.tinhtheosl = "0";
		this.ngaytb_tungay = "";
		this.ngaytb_dengay = "";
		
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

	public String getThang() 
	{
		return this.thang;
	}
	
	public void setThang(String thang) 
	{
		this.thang = thang;
	}
	
	public String getNam() 
	{
		return this.nam;
	}
	
	public void setNam(String nam)
	{
		this.nam = nam;
	}

	public String getId() 
	{
		return this.id;
	}

	public void setId(String id) 
	{
		this.id = id;
	}

	public String getDiengiai()
	{
		return this.diengiai;
	}

	public void setDiengiai(String diengiai) 
	{
		this.diengiai = diengiai;
	}

	public String getMsg()
	{
		return this.msg;
	}

	public void setMsg(String msg) 
	{
		this.msg = msg;
	}

	
	public boolean createTctSKU( ) 
	{
		try
		{
			if(this.thang.trim().length() <= 0)
			{
				this.msg = "Vui lòng chọn ngày bắt đầu";
				return false;
			}
			
			if(this.nam.trim().length() <= 0)
			{
				this.msg = "Vui lòng chọn ngày kết thúc";
				return false;
			}
			
			if(this.scheme.trim().length() <= 0)
			{
				this.msg = "Vui lòng scheme";
				return false;
			}
			
			//Check Scheme
			String query = "select count(*) as sodong from Ctkhuyenmai where scheme = N'" + this.scheme + "'";
			ResultSet rs = db.get(query);
			if(rs != null)
			{
				int count = 0;
				if(rs.next())
				{
					count = rs.getInt("sodong");
					if(count > 0)
					{
						this.msg = "Scheme: " + this.scheme + " đã tồn tại, vui lòng nhập scheme khác";
						rs.close();
						return false;
					}
				}
				rs.close();
			}
				if(this.maspTra.length <= 0)
				{
					this.msg = "Vui lòng chọn sản phẩm trong điều kiện";
					return false;
				}
			db.getConnection().setAutoCommit(false);
			
			query = "Insert into Ctkhuyenmai(scheme, diengiai, tungay, denngay, loaict, ngaytao, nguoitao, ngaysua, nguoisua,kho_fk) " +
					"values(N'" + this.scheme + "', N'" + this.diengiai + "','" + this.thang + "', '" + this.nam + "', '4', " +
					"'" + this.getDateTime() + "', '" + this.userId + "', '" + this.getDateTime() + "', '" + this.userId + "', 100000)";
			System.out.println("1.Insert: " + query);
			if(!db.update(query))
			{
				this.msg = "Không thể tạo mới CTKM theo điểm: " + query;
				db.getConnection().rollback();
				return false;
			}
			
			query = "select IDENT_CURRENT('Ctkhuyenmai') as tctId";
			rs = db.get(query);
			rs.next();
			this.id = rs.getString("tctId");
			rs.close();
			
			 query = "Insert into Dieukienkhuyenmai(diengiai, loai, ngaytao, nguoitao, ngaysua, nguoisua) " +
							"values(N'" + this.diengiai + "',2,'" + this.getDateTime() + "', '" + this.userId + "', '" + this.getDateTime() + "', '" + this.userId + "')";
			
			System.out.println("1.Insert Dieukienkhuyenmai: " + query);
			
			if(!db.update(query))
			{
				db.update("rollback");
				this.msg = "Không thể tạo mới CTKM theo điểm: " + query;
				return false; 
			}
			
			//lay dkkm current
			String dkkkmCurrent = "";
			query = "select IDENT_CURRENT('Dieukienkhuyenmai') as dkkmId";
			
			ResultSet rsDkkm = this.db.get(query);						
			rsDkkm.next();
			dkkkmCurrent = rsDkkm.getString("dkkmId");
			rsDkkm.close();
			
			
			query = "Insert into ctkm_dkkm(ctkhuyenmai_fk, dkkhuyenmai_fk, pheptoan, thutudieukien) "
					+ " values('" + this.id + "', '" + dkkkmCurrent + "',null,null)";
			if(!db.update(query))
			{
				db.getConnection().rollback();
				this.msg = "Không thể tạo mới CTKM theo điểm: " + query;
				return false; 
			}
					for(int i = 0; i < this.maspTra.length; i ++)
					{
						String masptt = "";
						String slsptt = "";
						String qdsptt = "";
						if(this.maspTra[i].trim().length() > 0)
						{
							if(this.maspTraTT.get(this.maspTra[i]) != null )
							{
								masptt = this.maspTraTT.get(this.maspTra[i]);
								slsptt = this.soluongTT.get(this.maspTra[i]);
							}
							if(this.soluongTT.get(this.maspTra[i])!= null)
								slsptt = this.soluongTT.get(this.maspTra[i]);
							else
								slsptt = "0";
							
							if(this.quydoiTT.get(this.maspTra[i])!= null)
								qdsptt = this.quydoiTT.get(this.maspTra[i]);
							else
								qdsptt = "0";
							
							if( (slsptt.equals("0")|| qdsptt.equals("0") ) && ( !slsptt.equals("0")|| !qdsptt.equals("0") ) )
							{
								this.msg = "Vui long thiet lap quy doi";
								db.getConnection().rollback();
								return false;
							}
							query = "Insert into dieukienkm_sanpham(dieukienkhuyenmai_fk, sanpham_fk, soluong, quydoi) " +
									"select '" + dkkkmCurrent + "', pk_seq, " + slsptt + ", " + qdsptt + " " +
									"from SanPham where ma = N'" + this.maspTra[i] + "'";
							
							System.out.println("2.Insert: " + query);
							
							if(!db.update(query))
							{
								db.update("rollback");
								this.msg = "Loi khi them moi bang dieukienkm_sanpham, " + query;
								return false; 
							}
						}
					}
				
			// chen phan bo nha phan phoi theo muc
			if(this.nppIds.trim().length() > 0)
			{
				query = "Insert ctkm_npp(ctkm_fk, npp_fk,chon,nguoitao,nguoisua,ngaytao,ngaysua) " +
						"select '" + this.id + "', pk_seq,'1',"+this.userId+",'"+this.userId+"','"+this.getDateTime()+"','"+this.getDateTime()+"'"
								+ " from NHAPHANPHOI where pk_seq in (" + this.nppIds + ") ";
				if(!db.update(query))
				{
					db.update("rollback");
					this.msg = "Không thể tạo mới CTKM theo điểm: " + query;
					return false; 
				}
			}
			query = "Insert into Trakhuyenmai(diengiai,loai, hinhthuc, ngaytao, nguoitao, ngaysua, nguoisua) values(";
			query = query + "N'" + this.diengiai + "', 1,2,'" + this.getDateTime() + "', '" + this.userId + "', '" + this.getDateTime() + "', '" + this.userId + "')";
			
			if(!db.update(query))
			{
				db.update("rollback");
				this.msg = "Khong the tao moi 'Trakhuyenmai', " + query;
				return false; 
			}
			
			String trakmCurrent = "";
			query = "select IDENT_CURRENT('Trakhuyenmai') as trakmId";
			
			ResultSet rsTrakm = this.db.get(query);						
			rsTrakm.next();
			trakmCurrent = rsTrakm.getString("trakmId");
			rsTrakm.close();
			
			query = "Insert into ctkm_trakm(ctkhuyenmai_fk, TRAKHUYENMAI_FK, pheptoan, thutu) "
					+ " values('" + this.id + "', '" + trakmCurrent + "',null,null)";
			if(!db.update(query))
			{
				db.getConnection().rollback();
				this.msg = "Không thể tạo mới CTKM theo điểm: " + query;
				return false; 
			}
			
			for(int i = 0; i < maspTra.length; i++)
			{
				if(maspTra[i].length() > 0)
				{
					 rs = db.get("select pk_seq from sanpham where ma='" + maspTra[i].trim() + "'");
					String pk_seq = "";
					if(rs != null) 
					{
						try
						{
							rs.next();
							pk_seq = rs.getString("pk_seq");
							rs.close();
						}
						catch (SQLException e) {}
					} 

					if(pk_seq != "")
						query = "Insert into trakhuyenmai_sanpham(trakhuyenmai_fk, sanpham_fk) values('" + trakmCurrent + "', '" + pk_seq + "')";
					if(!db.update(query))
					{
						db.update("rollback");
						this.msg = "Không thể tạo mới CTKM theo điểm: " + query;
						return false; 
					}
				}					
			}
			db.getConnection().commit();
			db.getConnection().setAutoCommit(true);
		} 
		catch (Exception e)
		{
			try 
			{
				db.getConnection().rollback();
				System.out.println("__EXCEPTION UPDATE: " + e.getMessage());
				e.printStackTrace();
				this.msg = "Lỗi khi cập CTKM theo điểm: " + e.getMessage();
			} 
			catch (SQLException e1) {}
			
			return false;
		}
		
		return true;
	}

	public boolean updateTctSKU()
	{
		try
		{
			if(this.thang.trim().length() <= 0)
			{
				this.msg = "Vui lòng chọn ngày bắt đầu";
				return false;
			}
			
			if(this.nam.trim().length() <= 0)
			{
				this.msg = "Vui lòng chọn ngày kết thúc";
				return false;
			}
			
			if(this.scheme.trim().length() <= 0)
			{
				this.msg = "Vui lòng điền scheme";
				return false;
			}
			
			//Check Scheme
			String query = "select count(*) as sodong from Ctkhuyenmai where scheme = N'" + this.scheme + "' and pk_seq != '" + this.id + "'";
			ResultSet rs = db.get(query);
			if(rs != null)
			{
				int count = 0;
				if(rs.next())
				{
					count = rs.getInt("sodong");
					if(count > 0)
					{
						this.msg = "Scheme: " + this.scheme + " đã tồn tại, vui lòng nhập scheme khác";
						rs.close();
						return false;
					}
				}
				rs.close();
			}

			if(this.maspTra.length <= 0)
				{
					this.msg = "Vui lòng chọn sản phẩm trong điều kiện";
					return false;
				}
			db.getConnection().setAutoCommit(false);
			
			
			query = "update CTKhuyenMai set scheme = N'" + this.scheme + "', tungay = '" + this.thang + "', denngay = '" + this.nam + "', diengiai = N'" + this.diengiai + "', " +
						" ngaysua = '" + this.getDateTime() + "', nguoisua = '" + this.userId + "' "+
					"where pk_seq = '" + this.id + "'";
					
			System.out.println("1.Update: " + query);
			if(!db.update(query))
			{
				this.msg = "Khong the cap nhat CTKhuyenMai: " + query;
				db.getConnection().rollback();
				return false;
			}
			String dkkkmCurrent = "";
			query = "select a.DKKHUYENMAI_FK  as dkkmId from ctkm_dkkm a inner join dieukienkm_sanpham b on a.DKKHUYENMAI_FK = b.dieukienkhuyenmai_fk"
					+ " where a.CTKHUYENMAI_FK = "+this.id;
			
			ResultSet rsDkkm = this.db.get(query);						
			rsDkkm.next();
			dkkkmCurrent = rsDkkm.getString("dkkmId");
			rsDkkm.close();
			query = "delete dieukienkm_sanpham where DIEUKIENKHUYENMAI_FK ="+dkkkmCurrent+" ";
			if(!db.update(query))
			{
				db.update("rollback");
				this.msg = "Không thể thêm sản phẩm vào CTKM theo điểm" + query;
				return false; 
			}
			for(int i = 0; i < this.maspTra.length; i ++)
			{
				String masptt = "";
				String slsptt = "";
				String qdsptt = "";
				if(this.maspTra[i].trim().length() > 0)
				{
					if(this.maspTraTT.get(this.maspTra[i]) != null )
					{
						masptt = this.maspTraTT.get(this.maspTra[i]);
						slsptt = this.soluongTT.get(this.maspTra[i]);
					}
					if(this.soluongTT.get(this.maspTra[i])!= null)
						slsptt = this.soluongTT.get(this.maspTra[i]);
					else
						slsptt = "0";
					
					if(this.quydoiTT.get(this.maspTra[i])!= null)
						qdsptt = this.quydoiTT.get(this.maspTra[i]);
					else
						qdsptt = "0";
					
					if( (slsptt.equals("0")|| qdsptt.equals("0") ) && ( !slsptt.equals("0")|| !qdsptt.equals("0") ) )
					{
						this.msg = "Vui long thiet lap quy doi";
						db.getConnection().rollback();
						return false;
					}
					query = "Insert into dieukienkm_sanpham(dieukienkhuyenmai_fk, sanpham_fk, soluong, quydoi) " +
							"select '" + dkkkmCurrent + "', pk_seq, " + slsptt + ", " + qdsptt + " " +
							"from SanPham where ma = N'" + this.maspTra[i] + "'";
					
					System.out.println("2.Insert: " + query);
					
					if(!db.update(query))
					{
						db.update("rollback");
						this.msg = "Loi khi them moi bang dieukienkm_sanpham, " + query;
						return false; 
					}
				}
			}
		
			// chen phan bo nha phan phoi theo muc
			if(this.nppIds.trim().length() > 0)
			{
				query = "delete ctkm_npp where ctkm_fk ="+this.id+" ";
				if(!db.update(query))
				{
					db.update("rollback");
					this.msg = "Không thể thêm NPP vào CTKM theo điểm" + query;
					return false; 
				}
				
				query = "Insert ctkm_npp(ctkm_fk, npp_fk,chon,nguoitao,nguoisua,ngaytao,ngaysua) " +
						"select '" + this.id + "', pk_seq,'1',"+this.userId+",'"+this.userId+"','"+this.getDateTime()+"','"+this.getDateTime()+"'"
								+ " from NHAPHANPHOI where pk_seq in (" + this.nppIds + ") ";
				if(!db.update(query))
				{
					db.update("rollback");
					this.msg = "Không thể thêm NPP vào CTKM theo điểm"+ query;
					return false; 
				}
			}
			String trakmCurrent = "";
			query = 
			 " select a.TRAKHUYENMAI_FK  as trakmId from ctkm_trakm a inner join trakhuyenmai_sanpham b on a.TRAKHUYENMAI_FK = b.TRAKHUYENMAI_FK"
					+ " where a.CTKHUYENMAI_FK = "+this.id;
			ResultSet rsTrakm = this.db.get(query);						
			rsTrakm.next();
			trakmCurrent = rsTrakm.getString("trakmId");
			rsTrakm.close();
			query = "delete trakhuyenmai_sanpham where TRAKHUYENMAI_FK ="+trakmCurrent+" ";
			if(!db.update(query))
			{
				db.update("rollback");
				this.msg = "Không thể thêm sản phẩm vào CTKM theo điểm" + query;
				return false; 
			}
			for(int i = 0; i < maspTra.length; i++)
			{
				if(maspTra[i].length() > 0)
				{
					 rs = db.get("select pk_seq from sanpham where ma='" + maspTra[i].trim() + "'");
					String pk_seq = "";
					if(rs != null) 
					{
						try
						{
							rs.next();
							pk_seq = rs.getString("pk_seq");
							rs.close();
						}
						catch (SQLException e) {}
					} 

					if(pk_seq != "")
						query = "Insert into trakhuyenmai_sanpham(trakhuyenmai_fk, sanpham_fk) values('" + trakmCurrent + "', '" + pk_seq + "')";
					if(!db.update(query))
					{
						db.update("rollback");
						this.msg = "Loi khi them moi bang trakhuyenmai_sanpham, " + query;
						return false; 
					}
				}					
			}
				
			db.getConnection().commit();
			db.getConnection().setAutoCommit(true);
		} 
		catch (Exception e)
		{
			try 
			{
				db.getConnection().rollback();
				System.out.println("__EXCEPTION UPDATE: " + e.getMessage());
				e.printStackTrace();
				
				this.msg = "Lỗi cập nhật: " + e.getMessage();
			} 
			catch (SQLException e1) {}
			
			return false;
		}
		
		return true;
	}

	public void init() 
	{
		String query = "select scheme,tungay as thang,denngay as nam, diengiai" +
					   " from ctkhuyenmai where pk_seq = '" + this.id + "' and loaict = '4' ";
		
		System.out.println("__Khoi tao tieu chi thuong: " + query);
		
		ResultSet rs = db.get(query);
		if(rs != null)
		{
			try 
			{
				NumberFormat format = new DecimalFormat("#,###,###");
				while(rs.next())
				{
					this.scheme = rs.getString("scheme");
					this.thang = rs.getString("thang");
					this.nam = rs.getString("nam");					
					this.diengiai= rs.getString("diengiai");
				}
				rs.close();
			} 
			catch (Exception e)
			{
				System.out.println("115.Error Meg: " + e.getMessage());
				e.printStackTrace();
			}
		}
		
		this.createNdk();
		this.createRs();
		
		this.createKhachHangDK();
	}
	
	private void createKhachHangDK()
	{}
	
	private void createNdk() 
	{}

	private String getDateTime() 
	{
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date date = new Date();
        return dateFormat.format(date);	
	}

	public String getScheme()
	{
		return this.scheme;
	}

	public void setScheme(String scheme) 
	{
		this.scheme = scheme;
	}
	
	public String[] getDiengiaiMuc() {
		
		return this.diengiaiMuc;
	}

	
	public void setDiengiaiMuc(String[] diengiai) {
		
		this.diengiaiMuc = diengiai;
	}

	
	public String[] getTumuc() {
		
		return this.tumuc;
	}

	
	public void setTumuc(String[] tumuc) {
		
		this.tumuc = tumuc;
	}

	
	public String[] getDenmuc() {
		
		return this.denmuc;
	}

	
	public void setDenmuc(String[] denmuc) {
		
		this.denmuc = denmuc;
	}

	
	public String[] getThuongSR() {
		
		return this.thuongSR;
	}

	
	public void setThuongSR(String[] thuongSR) {
		
		this.thuongSR = thuongSR;
	}

	
	public String[] getThuongTDSR() {
		
		return this.thuongTDSR;
	}

	
	public void setThuongTDSR(String[] thuongTDSR) {
		
		this.thuongTDSR = thuongTDSR;
	}

	
	public String[] getThuongSS() {
		
		return this.thuongSS;
	}

	
	public void setThuongSS(String[] thuongSS) {
		
		this.thuongSS = thuongSS;
	}

	
	public String[] getThuongTDSS() {
		
		return this.thuongTDSS;
	}

	
	public void setThuongTDSS(String[] thuongTDSS) {
		
		this.thuongTDSS = thuongTDSS;
	}

	
	public String[] getThuongASM() {
		
		return this.thuongASM;
	}

	
	public void setThuongASM(String[] thuongASM) {
		
		this.thuongASM = thuongASM;
	}

	
	public String[] getThuongTDASM() {
		
		return this.thuongTDASM;
	}

	
	public void setThuongTDASM(String[] thuongTDASM) {
		
		this.thuongTDASM = thuongTDASM;
	}

	public void createRs() {
		
		String query = "select PK_SEQ, MA, TEN, TRANGTHAI, NHANHANG_FK, CHUNGLOAI_FK  from SanPham where trangthai = '1' ";
		
		if(this.id.trim().length() > 0)
		{
			query += " union select PK_SEQ, MA, TEN, TRANGTHAI, NHANHANG_FK, CHUNGLOAI_FK from SanPham where pk_seq in ( select sanpham_fk from CTKM_DKKM a inner join  DieuKienKM_SANPHAM b on a.DKKhuyenMai_FK = b.DieuKienKhuyenMai_FK where a.CTKhuyenMai_fk = '" + this.id + "' ) ";
		}
		
		query += " order by TRANGTHAI desc, NHANHANG_FK asc, CHUNGLOAI_FK asc ";
		System.out.println("SP "+query);
		this.sanphamRs = db.getScrol(query);
		
		
		this.vungRs = db.get("select pk_seq, ten from VUNG where trangthai = '1'");
		
		query = "select pk_seq, ten from KHUVUC where trangthai = '1'";
		if(this.vungIds.trim().length() > 1)
			query += " and vung_fk in ( " + this.vungIds + " ) "; 
		this.kvRs = db.get(query);
		
		
		query = "select pk_seq, ten from KENHBANHANG where trangthai = '1'";
		this.kenhRs = db.get(query);
		
		query = "select PK_SEQ, MA, TEN  from NhaPhanPhoi where pk_seq != 1 and trangthai = '1'  ";
		if(this.kvIds.trim().length() > 0)
		{
			System.out.println("1111111");
			if(this.kvIds.trim().length() > 1)
				query += " and khuvuc_fk in ( " +  this.kvIds + " ) ";
			this.kt = "1";
			
		}
		if(this.vungIds.trim().length() > 0)
		{
			this.kt = "1";
			if(this.vungIds.trim().length() > 1)
				query += " and khuvuc_fk in ( select pk_seq from KhuVuc where trangthai = '1' and vung_fk in ( " + this.vungIds + " ) ) ";
		}
		if(this.kenhIds.trim().length() > 0)
		{
			this.kt = "1";
			if(this.kenhIds.trim().length() > 1)
				query += " and pk_seq in ( select NPP_FK from NHAPP_KBH where KBH_FK in ( " + this.kenhIds + " ) ) ";
			
		}
		System.out.println("Muc npp "+this.mucnpp );
		if(this.id.trim().length() > 0)
		{
			query += " union select PK_SEQ, MA, TEN from NhaPhanPhoi where pk_seq in ( select npp_fk from CTKM_NPP where CTKM_fk = '" + this.id + "' ) ";
		
			
				try {
					ResultSet rs  = db.get("select PK_SEQ, MA, TEN from NhaPhanPhoi where pk_seq in ( select npp_fk from CTKM_NPP where CTKM_fk = '" + this.id + "')");
					if(rs != null)
					while(rs.next())
					{
						nppIds+= rs.getString("PK_SEQ")+",";
					}
				} catch (SQLException e) {
					
					e.printStackTrace();
				}
			
			
		
		
			
		}
		query += " order by PK_SEQ desc ";
		System.out.println("nppRs = " + query);
		this.nppRs = db.getScrol(query);
		query = "select pk_seq, ten from KHO where trangthai = '1'";
		this.khoRs = db.get(query);
		
		query = "select pk_seq, ten from NHOMSANPHAM where trangthai = '1'";
		this.nhomsanphamRs = db.get(query);
		
		if(this.id != null && this.id.length() > 0)
		{
		query = "select c.MA, c.TEN, '' matt, '' as tentt, b.soluong as soluongquydoi,b.quydoi " +
				"from CTKM_DKKM a inner join  DieuKienKM_SANPHAM b on a.DKKhuyenMai_FK = b.DieuKienKhuyenMai_FK inner join SANPHAM c on b.sanpham_fk = c.PK_SEQ " +
				"where a.CTKhuyenMai_fk  = '" + this.id + "'";
		
		System.out.println("__LAY SP TRA: " + query);
		ResultSet rs = db.get(query);
		if(rs != null)
		{
			try 
			{
				String spMa = "";
				String spTen = "";
				Hashtable<String, String> sp_maspTraTT = new Hashtable<String, String>();
				Hashtable<String, String> sp_tenspTraTT = new Hashtable<String, String>();
				Hashtable<String, String> sp_soluongspTraTT = new Hashtable<String, String>();
				Hashtable<String, String> sp_quydoispTraTT = new Hashtable<String, String>();
				while(rs.next())
				{
					System.out.println("---TEN SP: " + rs.getString("TEN") + "  -- MA SP: " + rs.getString("MA"));
					spMa += rs.getString("MA") + ",,";
					spTen += rs.getString("TEN") + ",,";
					sp_maspTraTT.put(rs.getString("MA"), rs.getString("MAtt")==null?"":rs.getString("MAtt"));
					sp_tenspTraTT.put(rs.getString("MA"), rs.getString("tentt")==null?"":rs.getString("tentt"));
					sp_soluongspTraTT.put(rs.getString("MA"), rs.getString("soluongquydoi")==null?"":rs.getString("soluongquydoi"));
					sp_quydoispTraTT.put(rs.getString("MA"), rs.getString("quydoi")==null?"":rs.getString("quydoi"));
				}
				rs.close();
				
				this.maspTraTT = sp_maspTraTT;
				this.tenspTraTT = sp_tenspTraTT;
				this.soluongTT = sp_soluongspTraTT;
				this.quydoiTT = sp_quydoispTraTT;
				System.out.println("---TEN SP: " + spTen + "  -- MA SP: " + spMa);
				if(spMa.trim().length() > 0)
				{
					spMa = spMa.substring(0, spMa.length() - 2);
					this.maspTra = spMa.split(",,");
					
					spTen = spTen.substring(0, spTen.length() - 2);
					this.tenspTra = spTen.split(",,");
				}
			} 
			catch (Exception e) { System.out.println("__EXCEPTION INIT: " + e.getMessage());  }		
		}
		}
		
	
		
	}
	
	public String[] getDiengiaiMuc3() {
		
		return this.diengiaiMuc3;
	}

	
	public void setDiengiaiMuc3(String[] diengiai) {
		
		this.diengiaiMuc3 = diengiai;
	}

	
	public String[] getTumuc3() {
		
		return this.tumuc3;
	}

	
	public void setTumuc3(String[] tumuc) {
		
		this.tumuc3 = tumuc;
	}

	
	public String[] getDenmuc3() {
		
		return this.denmuc3;
	}

	
	public void setDenmuc3(String[] denmuc) {
		
		this.denmuc3 = denmuc;
	}
	
	
	public String[] getThuongSR3() {
		
		return this.thuongSR3;
	}

	
	public void setThuongSR3(String[] thuongSR) {
		
		this.thuongSR3 = thuongSR;
	}

	
	public String[] getThuongTDSR3() {
		
		return this.thuongTDSR3;
	}

	
	public void setThuongTDSR3(String[] thuongTDSR) {
		
		this.thuongTDSR3 = thuongTDSR;
	}

	
	public String[] getThuongSS3() {
		
		return this.thuongSS3;
	}

	
	public void setThuongSS3(String[] thuongSS) {
		
		this.thuongSS3 = thuongSS;
	}

	
	public String[] getThuongTDSS3() {
		
		return this.thuongTDSS3;
	}

	
	public void setThuongTDSS3(String[] thuongTDSS) {
		
		this.thuongTDSS3 = thuongTDSS;
	}

	
	public String[] getThuongASM3() {
		
		return this.thuongASM3;
	}

	
	public void setThuongASM3(String[] thuongASM) {
		
		this.thuongASM3 = thuongASM;
	}

	
	public String[] getThuongTDASM3() {
		
		return this.thuongTDASM3;
	}

	
	public void setThuongTDASM3(String[] thuongTDASM) {
		
		this.thuongTDASM3 = thuongTDASM;
	}

	
	public void setSanphamRs(ResultSet spRs) {
		
		this.sanphamRs = spRs;
	}

	
	public ResultSet getSanphamRs() {
		
		return this.sanphamRs;
	}

	
	public String getSpIds() {
		
		return this.spIds;
	}

	
	public void setSpIds(String spIds) {
		
		this.spIds = spIds;
	}

	
	public void setNppRs(ResultSet nppRs) {
		
		this.nppRs = nppRs;
	}

	
	public ResultSet getNppRs() {
		
		return this.nppRs;
	}

	
	public String getNppIds() {
		
		return this.nppIds;
	}

	
	public void setNppIds1(String nppIds1) {
		
		this.nppIds1 = nppIds1;
	}

	public String getNppIds1() {
		
		return this.nppIds1;
	}


	public String getNppIds2() {
		
		return this.nppIds2;
	}

	
	public void setNppIds2(String nppIds2) {
		
		this.nppIds2 = nppIds2;
	}

	public String getNppIds3() {
		
		return this.nppIds3;
	}

	
	public void setNppIds3(String nppIds3) {
		
		this.nppIds3 = nppIds3;
	}

	public String getNppIds4() {
		
		return this.nppIds4;
	}

	
	public void setNppIds4(String nppIds4) {
		
		this.nppIds4 = nppIds4;
	}

	public String getNppIds5() {
		
		return this.nppIds5;
	}

	
	public void setNppIds5(String nppIds5) {
		
		this.nppIds5 = nppIds5;
	}

	public void setNppIds(String nppIds) {
		
		this.nppIds = nppIds;
	}

	
	public String getMucvuot() {
		
		return this.mucvuot;
	}

	
	public void setMucvuot(String mucvuot) {
		
		this.mucvuot = mucvuot;
	}

	
	public String getChietkhauMucvuot() {
		
		return this.ckMucvuot;
	}

	
	public void setChietkhauMucvuot(String ckMv) {
		
		this.ckMucvuot = ckMv;
	}

	
	public String getDonviMucvuot() {
		
		return this.dvMucvuot;
	}

	
	public void setDonviMucvuot(String dvMv) {
		
		this.dvMucvuot = dvMv;
	}

	
	public void setVungRs(ResultSet vungRs) {
		
		this.vungRs = vungRs;
	}

	
	public ResultSet getVungRs() {
		
		return this.vungRs;
	}

	
	public String getVungIds() {
		
		return this.vungIds;
	}

	
	public void setVungIds(String vungIds) {
		
		this.vungIds = vungIds;
	}

	
	public void setKhuvucRs(ResultSet kvRs) {
		
		this.kvRs = kvRs;
	}

	
	public ResultSet getKhuvucRs() {
		
		return this.kvRs;
	}

	
	public String getKhuvucIds() {
		
		return this.kvIds;
	}

	
	public void setKhuvucIds(String kvIds) {
		
		this.kvIds = kvIds;
	}

	
	public String getHinhthuctra() {
		
		return this.hinhthucTra;
	}

	
	public void setHinhthuctra(String htTra) {
		
		this.hinhthucTra = htTra;
	}

	
	public String[] getMaspTra() {
		
		return this.maspTra;
	}

	
	public void setMaspTra(String[] maspTra) {
		
		this.maspTra = maspTra;
	}

	
	public String[] getTenspTra() {
		
		return this.tenspTra;
	}

	
	public void setTenspTra(String[] tenspTra) {
		
		this.tenspTra = tenspTra;
	}

	
	public String[] getSoluongspTra() {
		
		return this.soluongspTra;
	}

	
	public void setSoluongspTra(String[] soluongspTra) {
		
		this.soluongspTra = soluongspTra;
	}

	
	public String[] getIdspTra() {
		
		return this.isspTra;
	}

	
	public void setIdspTra(String[] idspTra) {
		
		this.isspTra = idspTra;
	}

	
	public String getDoanhsotheoThung() {
		
		return this.doanhsotheoThung;
	}

	
	public void setDoanhsotheoThung(String isThung) {
		
		this.doanhsotheoThung = isThung;
	}

	
	public String getHTTT() {
		
		return this.httt;
	}

	
	public void setHTTT(String httt) {
		
		this.httt = httt;
	}

	
	public String getPT_TRACK() {
		
		return this.ptchietkhau;
	}

	
	public void setPT_TRACK(String ptTRACK) {
		
		this.ptchietkhau = ptTRACK;
	}


	public String[] getDongiaspTra() {

		return this.dongiaspTra;
	}


	public void setDongiaspTra(String[] dongiaspTra) {
		
		this.dongiaspTra = dongiaspTra;
	}

	
	public Hashtable<String, String> getMuc_Tiendo() {
		
		return this.muc_tiendo;
	}

	
	public void setMuc_Tiendo(Hashtable<String, String> tiendo) {
		
		this.muc_tiendo = tiendo;
	}

	
	public Hashtable<String, String> getMuc_SpTra() {
		
		return this.muc_spTRA;
	}

	
	public void setMuc_SpTra(Hashtable<String, String> spTRA) {
		
		this.muc_spTRA = spTRA;
	}

	
	public String getNgayDS_Tungay() {
		
		return this.tungay;
	}

	
	public void setNgayDS_Tungay(String tungay) {
		
		this.tungay = tungay;
	}

	
	public String getNgayDS_Denngay() {
		
		return this.dengay;
	}

	
	public void setNgayDS_Denngay(String denngay) {
		
		this.dengay = denngay;
	}

	
	public void setKhoRs(ResultSet khoRs) {
		
		this.khoRs = khoRs;
	}

	
	public ResultSet getKhoRs() {
		
		return this.khoRs;
	}

	
	public String getKhoIds() {
		
		return this.khoId;
	}

	
	public void setKhoIds(String khoIds) {
		
		this.khoId = khoIds;
	}

	
	public Hashtable<String, String> getPhanbo() {

		return this.phanbo;
	}


	public void setPhanbo(Hashtable<String, String> phanbo) {

		this.phanbo = phanbo;
	}


	public void initDisplay() 
	{
		String query = "select scheme, thang, nam,isnull(ghichu,'') as ghichu, diengiai, mucvuot, chietkhauMucVuot, donviMucVuot, hinhthuctra, DOANHSOTHEOTHUNG, HTTT, PT_TRATL, ngayds_tungay, ngayds_denngay, khoId, phanloai, ngaytb_tungay, ngaytb_denngay, batbuocdangky  " +
				   "from TieuchithuongTL where pk_seq = '" + this.id + "'";
		
		System.out.println("__Khoi tao tieu chi thuong: " + query);
		
		ResultSet rs = db.get(query);
		if(rs != null)
		{
			try 
			{
				NumberFormat format = new DecimalFormat("#,###,###");
				while(rs.next())
				{
					this.scheme = rs.getString("scheme");
					this.thang = rs.getString("thang");
					this.nam = rs.getString("nam");					
					this.diengiai= rs.getString("diengiai");
					
					this.mucvuot = rs.getString("mucvuot") != null ? format.format(rs.getDouble("mucvuot")) : "";
					this.ckMucvuot = rs.getString("chietkhauMucVuot") != null ? format.format(rs.getDouble("chietkhauMucVuot")) : "";
					this.dvMucvuot = rs.getString("donviMucVuot");
					this.hinhthucTra = rs.getString("hinhthuctra");
					this.doanhsotheoThung = rs.getString("DOANHSOTHEOTHUNG");
					
					this.httt = rs.getString("HTTT");
					this.ptchietkhau = rs.getString("PT_TRATL");
					this.ghighu = rs.getString("ghichu");
					this.tungay = rs.getString("ngayds_tungay");
					this.dengay = rs.getString("ngayds_denngay");
					this.khoId = rs.getString("khoId");
					
					this.phanloai = rs.getString("phanloai");
					
					this.ngaytb_tungay = rs.getString("ngaytb_tungay");
					this.ngaytb_dengay = rs.getString("ngaytb_denngay");
					this.phaidangky = rs.getString("batbuocdangky");
				}
				rs.close();
			} 
			catch (Exception e)
			{
				System.out.println("115.Error Meg: " + e.getMessage());
				e.printStackTrace();
			}
		}
		
		this.createNdk();
		this.createRs();
		this.createKhachHangDK();
	}

	public String getPhanloai() {

		return this.phanloai;
	}


	public void setPhanloai(String phanloai) {

		this.phanloai = phanloai;
	}
	
	public Hashtable<String, String> getDieukien() {
		
		return this.dieukien;
	}

	
	public void setDieukien(Hashtable<String, String> dieukien) {
		
		this.dieukien = dieukien;
	}

	
	public Hashtable<String, String> getQuydoi() {
		
		return this.quydoi;
	}

	
	public void setQuydoi(Hashtable<String, String> quydoi) {
		
		this.quydoi = quydoi;
	}
	
	public void setNhomsanphamRs(ResultSet spRs) {
		
		this.nhomsanphamRs = spRs;
	}
	
	public ResultSet getNhomsanphamRs() {
		
		return this.nhomsanphamRs;
	}
	
	public String getNhomsanphamIds() {
		
		return this.nhomspIds;
	}
	
	public void setNhomsanphamIds(String nhomspIds) {
		
		this.nhomspIds = nhomspIds;
	}
	
	public void loadSP_NHOM() 
	{
		if(this.nhomspIds.trim().length() > 0)
		{
			String query = "select b.MA, b.TEN " + 
						   " from NHOMSANPHAM_SANPHAM a inner join SANPHAM b on a.SP_FK = b.PK_SEQ " +
						   " where a.NSP_FK = '" + this.nhomspIds + "' ";
			ResultSet rs = db.get(query);
			if(rs != null )
			{
				try 
				{
					String spMa = "";
					String spTen = "";

					while(rs.next())
					{
						spMa += rs.getString("MA") + ",,";
						spTen += rs.getString("TEN") + ",,";	
					}
					rs.close();
					
					//System.out.println("---TEN SP: " + spTen + "  -- MA SP: " + spMa);
					if(spMa.trim().length() > 0)
					{
						spMa = spMa.substring(0, spMa.length() - 2);
						this.maspTra = spMa.split(",,");
						
						spTen = spTen.substring(0, spTen.length() - 2);
						this.tenspTra = spTen.split(",,");
					}
				} 
				catch (Exception e) 
				{
					e.printStackTrace();
				}
			}
		}
	}
	
	public void setKenhRs(ResultSet kenhRs) {
		
		this.kenhRs = kenhRs;
	}
	
	public ResultSet getKenhRs() {
		
		return this.kenhRs;
	}
	
	public String getKenhIds() {
		
		return this.kenhIds;
	}
	
	public void setKenhIds(String kenhIds) {
		
		this.kenhIds = kenhIds;
	}
	
	public String[] gethttt1() {
		
		return this.httt1;
	}
	
	public void sethttt1(String[] httt1) {
		
		this.httt1 = httt1;
	}
	
	public String getGhichu() {
		
		return this.ghighu;
	}
	
	public void setGhichu(String ghichu) {
		
		this.ghighu = ghichu;
	}
	
	public String getKT() {
		
		return this.kt;
	}
	
	public void setKT(String kt) {
		
		this.kt = kt;
	}
	
	public String getActiveTab() {
		
		return this.active;
	}
	
	public void setActiveTab(String active) {
		
		this.active = active;
	}
	
	public String getMucNPP() {
		
		return this.mucnpp;
	}
	
	public void setMucNPP(String MucNpp) {
		
		this.mucnpp = MucNpp;
	}
	
	public Hashtable<String, String> getPhanboTheoMucNPP1() {
		
		return this.phanbo1;
	}
	
	public void setPhanboTheoMucNPP1(Hashtable<String, String> phanbo1) {
		
		this.phanbo1 = phanbo1;
	}
	
	public Hashtable<String, String> getPhanboTheoMucNPP2() {
		
		return this.phanbo2;
	}
	
	public void setPhanboTheoMucNPP2(Hashtable<String, String> phanbo2) {
		
		this.phanbo2 = phanbo2;
	}
	
	public Hashtable<String, String> getPhanboTheoMucNPP3() {
		
		return this.phanbo3;
	}
	
	public void setPhanboTheoMucNPP3(Hashtable<String, String> phanbo3) {
		
		this.phanbo3 = phanbo3;
	}
	
	public Hashtable<String, String> getPhanboTheoMucNPP4() {
		
		return this.phanbo4;
	}
	
	public void setPhanboTheoMucNPP4(Hashtable<String, String> phanbo4) {
		
		this.phanbo4 = phanbo4;
	}
	
	public Hashtable<String, String> getPhanboTheoMucNPP5() {
		
		return this.phanbo5;
	}
	
	public void setPhanboTheoMucNPP5(Hashtable<String, String> phanbo5) {
		
		this.phanbo5 = phanbo5;
	}
	
	public String[] getDKKMTICHLUY_KHACHHANG_Id() {
		return DKKMTICHLUY_KHACHHANG_Id;
	}
	public void setDKKMTICHLUY_KHACHHANG_Id(String[] dKKMTICHLUY_KHACHHANG_Id) {
		DKKMTICHLUY_KHACHHANG_Id = dKKMTICHLUY_KHACHHANG_Id;
	}
	public ResultSet getKhDangKyRs() {
		return khDangKyRs;
	}
	public String[] getKhDcDuyet() {
		return khDcDuyet;
	}
	public void setKhDcDuyet(String[] khDcDuyet) {
		this.khDcDuyet = khDcDuyet;
	}
	public String[] getKhDcDuyetDisplay() {
		return khDcDuyetDisplay;
	}
	public void setKhDcDuyetDisplay(String[] khDcDuyetDisplay) {
		this.khDcDuyetDisplay = khDcDuyetDisplay;
	}

	
	public String getNgayTB_Tungay() {
		
		return this.ngaytb_tungay;
	}

	
	public void setNgayTB_Tungay(String tungay) {
		
		this.ngaytb_tungay = tungay;
	}

	
	public String getNgayTB_Denngay() {
		
		return this.ngaytb_dengay;
	}

	
	public void setNgayTB_Denngay(String denngay) {
		
		this.ngaytb_dengay = denngay;
	}

	
	public String getPhaidangky() {
		
		return this.phaidangky;
	}

	
	public void setPhandangky(String phaidangky) {
		
		this.phaidangky = phaidangky;
	}

	@Override
	public Hashtable<String, String> getMaspTraTT() {
		return this.maspTraTT;
	}

	@Override
	public void setMaspTraTT(Hashtable<String, String> maspTraTT) {
		this.maspTraTT = maspTraTT;
	}

	@Override
	public Hashtable<String, String> getTenspTraTT() {
		return this.tenspTraTT;
	}

	@Override
	public void setTenspTraTT(Hashtable<String, String> tenspTraTT) {
		this.tenspTraTT = tenspTraTT;
	}

	@Override
	public Hashtable<String, String> getSoluongTT() {
		return this.soluongTT;
	}

	@Override
	public void setSoluongTT(Hashtable<String, String> soluongTT) {
		this.soluongTT = soluongTT;
	}
	
	public Hashtable<String, String> getQuydoiTT() {
		return quydoiTT;
	}
	public void setQuydoiTT(Hashtable<String, String> quydoiTT) {
		this.quydoiTT = quydoiTT;
	}

	@Override
	public String getTinhtheoSl() {
		// TODO Auto-generated method stub
		return this.tinhtheosl;
	}

	@Override
	public void setTinhtheoSl(String value) {
		this.tinhtheosl = value;
	}



	

}
