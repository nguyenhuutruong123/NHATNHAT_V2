package geso.dms.distributor.beans.chuyenkho.imp;

import geso.dms.center.util.Utility;
import geso.dms.distributor.beans.chuyenkho.IErpDoisoloNpp;
import geso.dms.distributor.db.sql.dbutils;

import java.io.Serializable;
import java.sql.ResultSet;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Enumeration;
import java.util.Hashtable;

import javax.servlet.http.HttpServletRequest;

public class ErpDoisoloNpp implements IErpDoisoloNpp, Serializable
{
	private static final long serialVersionUID = -9217977546733610214L;

	String userId;
	String id;
	String ngaychuyen;
	String trangthai;
	
	String msg;
	
	ResultSet kbhRs;
	String kbhId;
	ResultSet khoRs;
	String khoId;
	
	ResultSet spRs;
	String spIds;
	
	Hashtable<String, String> sp_soloOLD;
	Hashtable<String, String> sp_solo;
	Hashtable<String, Integer> sp_sl;
	Hashtable<String, String> sp_ngayhethan;
	Hashtable<String, String> sp_ngayhethanOLD;
	Hashtable<String, String> sp_ngaynhapkho;
	Hashtable<String, String> sp_ngaynhapkhoOLD;
	String nppId;
	String nppTen;
	String sitecode;
		
	dbutils db;
	Utility util;
	
	public ErpDoisoloNpp()
	{
		this.id = "";
		this.ngaychuyen = "";
		this.kbhId = "";
		this.khoId = "";
		this.spIds = "";
		this.trangthai = "";
		this.msg = "";
		
		this.sp_soloOLD = new Hashtable<String, String>();
		this.sp_solo = new Hashtable<String, String>();
		this.sp_sl = new Hashtable<String, Integer>();
		this.sp_ngayhethan = new Hashtable<String, String>();
		this.sp_ngayhethanOLD = new Hashtable<String, String>();
		this.sp_ngaynhapkho= new Hashtable<String, String>();
		this.sp_ngaynhapkhoOLD = new Hashtable<String, String>();
		db = new dbutils();
		util = new Utility();
	}
	
	public ErpDoisoloNpp(String id)
	{
		this.id = id;
		this.ngaychuyen = "";
		this.kbhId = "";
		this.khoId = "";
		this.spIds = "";
		this.trangthai = "";
		this.msg = "";
		
		this.sp_soloOLD = new Hashtable<String, String>();
		this.sp_solo = new Hashtable<String, String>();
		this.sp_sl = new Hashtable<String, Integer>();
		this.sp_ngayhethan = new Hashtable<String, String>();
		this.sp_ngaynhapkho= new Hashtable<String, String>();
		this.sp_ngayhethanOLD = new Hashtable<String, String>();
		this.sp_ngaynhapkhoOLD = new Hashtable<String, String>();
		
		db = new dbutils();
		util = new Utility();
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
	
	public String getNppTen() 
	{
		return this.nppTen;
	}

	public void setNppTen(String nppTen) 
	{
		this.nppTen = nppTen;
	}
	
	public String getSitecode() 
	{
		return this.sitecode;
	}

	public void setSitecode(String sitecode) 
	{
		this.sitecode = sitecode;
	}

	public String getNgaychuyen() 
	{
		return this.ngaychuyen;
	}

	public void setNgaychuyen(String ngaychuyen) 
	{
		this.ngaychuyen = ngaychuyen;
	}

	public ResultSet getNvBanhang() 
	{
		return this.kbhRs;
	}

	public void setNvBanhang(ResultSet nvbanhang)
	{
		this.kbhRs = nvbanhang;
	}

	public String getNvbhId() 
	{
		return this.kbhId;
	}

	public void setNvbhId(String kbhId)
	{
		this.kbhId = kbhId;
	}

	public ResultSet getKhoRs() 
	{
		return this.khoRs;
	}

	public void setKhoRs(ResultSet khoRs) 
	{
		this.khoRs = khoRs;
	}

	public String getKhoId() 
	{
		return this.khoId;
	}

	public void setKhoId(String khoId) 
	{
		this.khoId = khoId;
	}

	public ResultSet getSpRs() 
	{
		return this.spRs;
	}

	public void setSpRs(ResultSet spRs) 
	{
		this.spRs = spRs;
	}

	public String getSpIds()
	{
		return this.spIds;
	}

	public void setSpIds(String spIds)
	{
		this.spIds = spIds;
	}

	public boolean CreateCk( HttpServletRequest request )
	{
		if(this.ngaychuyen.trim().length() <= 0)
		{
			this.msg = "Vui lòng chọn ngày chuyển";
			return false;
		}
		
		if(this.kbhId.trim().length() <= 0)
		{
			this.msg = "Vui lòng chọn kênh đổi hàng";
			return false;
		}
		
		if(this.khoId.trim().length() <= 0)
		{
			this.msg = "Vui lòng chọn kho đổi hàng";
			return false;
		}
		
		if(this.sp_sl == null || this.sp_sl.size() <= 0 )
		{
			this.msg = "Không có sản phẩm nào để đổi số lô";
			return false;
		}
		
		try 
		{
			
			db.update("SET TRANSACTION ISOLATION LEVEL SNAPSHOT;");
			db.update("SET LOCK_TIMEOUT 60000;");
			db.getConnection().setAutoCommit(false);
			Object[] data = null;
			data = dbutils.setObject(this.kbhId,this.khoId,this.ngaychuyen ,this.nppId, getDateTime(), this.userId,getDateTime(), this.userId );
			
			String query = "insert ERP_DOISOLONPP(kbh_fk, kho_fk, ngaydoi, trangthai, npp_fk, ngaytao, nguoitao, ngaysua, nguoisua) " +
							"values(?, ?,?, '0', ?, ?, ?, ?, ?)";
			
			if(db.update_v2(query, data)!=1)
			{
				this.msg = "Không thể tạo mới đổi số lô: " + query;
				db.getConnection().rollback();
				return false;
			}
						
		
					this.id = db.getPk_seq();
						
			
			Enumeration<String> keys = this.sp_solo.keys();
			while(keys.hasMoreElements())
			{
				String key = keys.nextElement();
				String spID = key.split("__")[0];
				
				System.out.println("KEY____ :"+key);
				System.out.println("ngay nhap kho : "+this.sp_ngaynhapkhoOLD.get(key));
				
				//CHECK TON KHO
				int avai = 0;
				String spTEN = "";
				
				String spNgayHetHanOLD="";
				String spNgayNhapKhoOLD="";
				
					query = "select available, ( select ten from SANPHAM where pk_seq = a.sanpham_fk ) as spTEN,NgayHetHan as spNgayhethan,NgayNhapKho as spNgayNhapKho " +
						"from NHAPP_KHO_CHITIET a " +
						"where npp_fk = '" + this.nppId + "' and kho_fk = '" + this.khoId + "' and kbh_fk = '" + this.kbhId + "' and sanpham_fk = '" + spID + "' and solo = '" + this.sp_soloOLD.get(key) + "' and NgayHetHan='"+this.sp_ngayhethanOLD.get(key)+"' and Ngaynhapkho='"+this.sp_ngaynhapkhoOLD.get(key)+"'";

					System.out.println(":::"+query);
					ResultSet rsCHECK = db.get(query);
					if(rsCHECK.next())
					{
						avai = rsCHECK.getInt("available");
						spTEN = rsCHECK.getString("spTEN");
						spNgayHetHanOLD=rsCHECK.getString("spNgayhethan");
						spNgayNhapKhoOLD=rsCHECK.getString("spNgayNhapKho");
					}
					rsCHECK.close();
				
				
				if(avai < this.sp_sl.get(key) )
				{
					this.msg = "Tồn kho của sản phẩm ( " + spTEN + " ) với số lô ( " + this.sp_soloOLD.get(key) + " ), số lượng ( " + this.sp_sl.get(key) + " ) còn tối đa ( " + avai + " ) ";
					db.getConnection().rollback();
					return false;
				}
				
				if (this.sp_soloOLD.get(key).equals(this.sp_solo.get(key)))
				{
					this.msg = "không thể đổi 2 số lô trùng nhau vui lòng kiểm tra lại !! " + query;
					db.getConnection().rollback();
					return false;
				}
				 String dateFormat = "yyyy-MM-dd";
				 if(!isValidDate( this.sp_ngayhethan.get(key),dateFormat) || !isValidDate(sp_ngayhethanOLD.get(key),dateFormat) || !isValidDate(sp_ngaynhapkhoOLD.get(key),dateFormat)|| !isValidDate(this.ngaychuyen,dateFormat) )
				 {
					 this.msg="ngày không hợp lệ vui lòng chỉnh sửa lại ngày";
					 db.getConnection().rollback();
					 return false;
				 }

				
				if(this.sp_solo.get(key).equals("NA"))
				{
					this.sp_ngayhethan.put(key, "2030-12-31");
				}
				data = null;
				data = dbutils.setObject(this.id,spID,this.sp_soloOLD.get(key) ,this.sp_solo.get(key), this.sp_sl.get(key),this.sp_ngayhethan.get(key),sp_ngayhethanOLD.get(key), sp_ngaynhapkhoOLD.get(key),this.ngaychuyen );
				
				query = 
						"insert ERP_DOISOLONPP_SANPHAM(doisolo_fk, sanpham_fk, soloOLD, solo, soluong, ngayhethan,NgayHetHanOLD,ngaynhapkhoOLD,ngaynhapkho) " +
						"values(?, ?, ?, ?,?, ?,? ,? ,? )";
				if(db.update_v2(query, data)!=1)
				{
					this.msg = "Không thể tạo mới ERP_DOISOLONPP_SANPHAM: " + query;
					db.getConnection().rollback();
					return false;
				}
//				
//				query = "update NHAPP_KHO_CHITIET set available = available - '" + this.sp_sl.get(key) + "', booked = booked + '" + this.sp_sl.get(key) + "' " +
//						"where npp_fk = '" + this.nppId + "' and kho_fk = '" + this.khoId + "' and kbh_fk = '" + this.kbhId + "' and sanpham_fk = '" + spID + "' and solo = '" + this.sp_soloOLD.get(key) + "' and ngayHetHan='"+sp_ngayhethanOLD.get(key)+"' ";
//						
//				if(db.updateReturnInt(query)!=1)
//				{
//					this.msg = "Không thể tạo mới ERP_DOISOLONPP_SANPHAM: " + query;
//					db.getConnection().rollback();
//					return false;
//				}
				this.msg=util.Update_NPP_Kho_Sp_Chitiet(ngaychuyen, "Đổi số lô NPP", db, khoId, spID, nppId, kbhId, sp_soloOLD.get(key), sp_ngayhethanOLD.get(key), sp_ngaynhapkhoOLD.get(key), 0.0, this.sp_sl.get(key), (-1)*this.sp_sl.get(key), (-1)*this.sp_sl.get(key), 0.0);
				if(this.msg.length()>0)
				{
					db.getConnection().rollback();
					return false;
				}
			}
			
			Utility util = new Utility();
			msg= util.Check_Huy_NghiepVu_KhoaSo("ERP_DOISOLONPP", id, "ngaydoi", db);
			if(msg.length()>0)
			{
				db.getConnection().rollback();
				return false;
			}
			
			msg= util.Check_Kho_Tong_VS_KhoCT(this.nppId, db);
			if(msg.length()>0)
			{
				db.getConnection().rollback();
				return false;
			}

			
			db.getConnection().commit();
			db.getConnection().setAutoCommit(true);
		} 
		catch (Exception e)
		{
			this.msg = "Lỗi khi tạo mới: " + e.getMessage();
			System.out.println("Exception; " + e.getMessage());
			db.update("rollback");
			
			return false;
		}
		
		return true;
	}

	public boolean UpdateCk( HttpServletRequest request ) 
	{
		if(this.ngaychuyen.trim().length() <= 0)
		{
			this.msg = "Vui lòng chọn ngày chuyển";
			return false;
		}
		
		if(this.kbhId.trim().length() <= 0)
		{
			this.msg = "Vui lòng chọn kênh đổi hàng";
			return false;
		}
		
		if(this.khoId.trim().length() <= 0)
		{
			this.msg = "Vui lòng chọn kho đổi hàng";
			return false;
		}
		
		if(this.sp_sl == null || this.sp_sl.size() <= 0 )
		{
			this.msg = "Không có sản phẩm nào để đổi số lô";
			return false;
		}
		
		try 
		{
			db.update("SET TRANSACTION ISOLATION LEVEL SNAPSHOT;");
			db.update("SET LOCK_TIMEOUT 60000;");
			db.getConnection().setAutoCommit(false);
			
			//Cap nhat lai kho NPP truoc
			String query = "select a.npp_fk, a.kho_fk, a.kbh_fk, b.sanpham_fk, b.soluong, b.soloOLD, b.solo,b.NgayHetHan,b.NgayHetHanOLD,b.ngaynhapkho,b.ngaynhapkhoOLD  " +
						   "from ERP_DOISOLONPP a inner join ERP_DOISOLONPP_SANPHAM b on a.pk_seq = b.doisolo_fk " +
						   "where a.pk_seq = '" + this.id + "'";
			
			ResultSet rsUpdate = db.get(query);
			if(rsUpdate != null)
			{
				while(rsUpdate.next())
				{
//					query = "update nhapp_kho_chitiet set available = available + '" + rsUpdate.getInt("soluong") + "', booked = booked - '" + rsUpdate.getInt("soluong") + "' " +
//							"where npp_fk = '" + rsUpdate.getString("npp_fk") + "' and kho_fk = '" + rsUpdate.getString("kho_fk") + "' and kbh_fk = '" + rsUpdate.getString("kbh_fk") + "' and sanpham_fk = '" + rsUpdate.getString("sanpham_fk") + "' and solo = '" + rsUpdate.getString("soloOLD") + "' and NgayHetHan='"+rsUpdate.getString("NgayHetHanOLD")+"' ";
//					
//					if(db.updateReturnInt(query)!=1)
//					{
//						this.msg = "Không thể cập nhật đổi số lô: " + query;
//						db.getConnection().rollback();
//						return false;
//					}
//			
					this.msg=util.Update_NPP_Kho_Sp_Chitiet(this.ngaychuyen, "Đổi số lô", db, rsUpdate.getString("kho_fk"), rsUpdate.getString("sanpham_fk"), 
							rsUpdate.getString("npp_fk"), rsUpdate.getString("kbh_fk"), rsUpdate.getString("soloOLD"), rsUpdate.getString("NgayHetHanOLD"), rsUpdate.getString("ngaynhapkhoOLD"), 0.0, (-1)*rsUpdate.getDouble("soluong"), rsUpdate.getDouble("soluong"), rsUpdate.getDouble("soluong"), 0.0);
					/*query = "update nhapp_kho set available = available + '" + rsUpdate.getInt("soluong") + "', booked = booked - '" + rsUpdate.getInt("soluong") + "' " +
							"where npp_fk = '" + rsUpdate.getString("npp_fk") + "' and kho_fk = '" + rsUpdate.getString("kho_fk") + "' and kbh_fk = '" + rsUpdate.getString("kbh_fk") + "' and sanpham_fk = '" + rsUpdate.getString("sanpham_fk") + "' ";
					
					if(db.updateReturnInt(query)!=1)
					{
						this.msg = "Không thể cập nhật đổi số lô: " + query;
						db.getConnection().rollback();
						return false;
					}*/
					if(this.msg.length()>0)
					{
						db.getConnection().rollback();
						return false;
					}
				}
				rsUpdate.close();
			}
			
			Object[] data = null;
			data = dbutils.setObject(this.kbhId,this.khoId,this.ngaychuyen ,this.nppId, getDateTime(), this.userId,getDateTime(), this.userId, this.id );
			
			query = "update ERP_DOISOLONPP set kbh_fk = ?, kho_fk = ?, ngaydoi = ?, " +
					"ngaysua = ?, nguoisua = ? where pk_seq = ? and trangthai=0 ";
					
			if(db.update_v2(query, data)!=1)
			{
				this.msg = "Không thể tạo cập nhật ERP_DOISOLONPP: " + query;
				db.getConnection().rollback();
				return false;
			}
			
			query = "delete ERP_DOISOLONPP_SANPHAM where doisolo_fk = '" + this.id + "'";	
			if(!db.update(query))
			{
				this.msg = "Không thể tạo cập nhật ERP_DOISOLONPP_SANPHAM: " + query;
				db.getConnection().rollback();
				return false;
			}
			
			Enumeration<String> keys = this.sp_solo.keys();
			while(keys.hasMoreElements())
			{
				String key = keys.nextElement();
				String spID = key.split("__")[0];
				
				//CHECK TON KHO
				int avai = 0;
				String spTEN = "";
				
				query = "select available, ( select ten from SANPHAM where pk_seq = a.sanpham_fk ) as spTEN,a.NgayHetHan,a.ngaynhapkho " +
						"from NHAPP_KHO_CHITIET a " +
						"where npp_fk = '" + this.nppId + "' and kho_fk = '" + this.khoId + "' and kbh_fk = '" + this.kbhId + "' and sanpham_fk = '" + spID + "' and solo = '" + this.sp_soloOLD.get(key) + "' and NgayHetHan='"+this.sp_ngayhethanOLD.get(key)+"'  and Ngaynhapkho='"+this.sp_ngaynhapkhoOLD.get(key)+"' ";
				ResultSet rsCHECK = db.get(query);
				if(rsCHECK.next())
				{
					avai = rsCHECK.getInt("available");
					spTEN = rsCHECK.getString("spTEN");
				}
				rsCHECK.close();
				
				if(avai < this.sp_sl.get(key) )
				{
					this.msg = "Tồn kho của sản phẩm ( " + spTEN + " ) với số lô ( " + this.sp_soloOLD.get(key) + " ), số lượng ( " + this.sp_sl.get(key) + " ) còn tối đa ( " + avai + " ) ";
					db.getConnection().rollback();
					return false;
				}
				
				if (this.sp_soloOLD.get(key).equals(this.sp_solo.get(key)))
				{
					this.msg = "không thể đổi 2 số lô trùng nhau vui lòng kiểm tra lại !! " + query;
					db.getConnection().rollback();
					return false;
				}
				if(this.sp_solo.get(key).equals("NA"))
				{
					this.sp_ngayhethan.put(key, "2030-12-31");
				}
				
				 String dateFormat = "yyyy-MM-dd";
				 if(!isValidDate( this.sp_ngayhethan.get(key),dateFormat) || !isValidDate(sp_ngayhethanOLD.get(key),dateFormat) || !isValidDate(sp_ngaynhapkhoOLD.get(key),dateFormat)|| !isValidDate(this.ngaychuyen,dateFormat) )
				 {
					 this.msg="ngày không hợp lệ vui lòng chỉnh sửa lại ngày";
					 db.getConnection().rollback();
					 return false;
				 }
					data = null;
					data = dbutils.setObject(this.id,spID,this.sp_soloOLD.get(key) ,this.sp_solo.get(key), this.sp_sl.get(key),this.sp_ngayhethan.get(key),sp_ngayhethanOLD.get(key), this.ngaychuyen ,sp_ngaynhapkhoOLD.get(key));
					
				query = "insert ERP_DOISOLONPP_SANPHAM(doisolo_fk, sanpham_fk, soloOLD, solo, soluong, ngayhethan,NgayHetHanOLD,ngaynhapkho,ngaynhapkhoOLD) " +
						"values(?, ?, ?,?, ?,? ,?, ?,?)";
				if(db.update_v2(query, data)!=1)
				{
					this.msg = "Không thể tạo mới ERP_DOISOLONPP_SANPHAM: " + query;
					db.getConnection().rollback();
					return false;
				}
				
//				query = "update NHAPP_KHO_CHITIET set available = available - '" + this.sp_sl.get(key) + "', booked = booked + '" + this.sp_sl.get(key) + "' " +
//						"where npp_fk = '" + this.nppId + "' and kho_fk = '" + this.khoId + "' and kbh_fk = '" + this.kbhId + "' and sanpham_fk = '" + spID + "' and solo = '" + this.sp_soloOLD.get(key) + "' and NgayHetHan='"+this.sp_ngayhethanOLD.get(key)+"' ";
//						
//				if(db.updateReturnInt(query)!=1)
//				{
//					this.msg = "Không thể tạo mới ERP_DOISOLONPP_SANPHAM: " + query;
//					db.getConnection().rollback();
//					return false;
//				}
				this.msg=util.Update_NPP_Kho_Sp_Chitiet(this.ngaychuyen, "Đổi số lô", db, this.khoId, spID, this.nppId, this.kbhId , this.sp_soloOLD.get(key), this.sp_ngayhethanOLD.get(key),  this.sp_ngaynhapkhoOLD.get(key), 0.0, this.sp_sl.get(key), (-1)*this.sp_sl.get(key), (-1)*this.sp_sl.get(key),0.0);
				if(this.msg.length()>0)
				{
					db.getConnection().rollback();
					return false;
				}
			}
			
			Utility util = new Utility();
			msg= util.Check_Huy_NghiepVu_KhoaSo("ERP_DOISOLONPP", id, "ngaydoi", db);
			if(msg.length()>0)
			{
				db.getConnection().rollback();
				return false;
			}
			
			msg= util.Check_Kho_Tong_VS_KhoCT(this.nppId, db);
			if(msg.length()>0)
			{
				db.getConnection().rollback();
				return false;
			}
			
			db.getConnection().commit();
			db.getConnection().setAutoCommit(true);
		} 
		catch (Exception e)
		{
			this.msg = "Lỗi khi cập nhật: " + e.getMessage();
			System.out.println("Exception; " + e.getMessage());
			db.update("rollback");
			
			return false;
		}
		
		return true;
	}

	public Hashtable<String, String> getSp_ngayhethanOLD()
  {
  	return sp_ngayhethanOLD;
  }

	public void setSp_ngayhethanOLD(Hashtable<String, String> sp_ngayhethanOLD)
  {
  	this.sp_ngayhethanOLD = sp_ngayhethanOLD;
  }

	public boolean ChotCk() 
	{
		try 
		{
			getNppInfo();
			
			db.update("SET TRANSACTION ISOLATION LEVEL SNAPSHOT;");
			db.update("SET LOCK_TIMEOUT 60000;");
			db.getConnection().setAutoCommit(false);
			
			//Cap nhat lai kho NPP truoc
			String query = "select a.npp_fk,a.ngaydoi, a.kho_fk, a.kbh_fk, b.sanpham_fk, b.soluong, b.soloOLD, b.solo, b.ngayhethan,b.NgayHetHanOLD,b.ngaynhapkho,b.NgaynhapkhoOLD " +
						   "from ERP_DOISOLONPP a inner join ERP_DOISOLONPP_SANPHAM b on a.pk_seq = b.doisolo_fk " +
						   "where a.pk_seq = '" + this.id + "'";
			
			ResultSet rsUpdate = db.get(query);
			{
				while(rsUpdate.next())
				{
//					query = "update nhapp_kho_chitiet set soluong = soluong - '" + rsUpdate.getInt("soluong") + "', booked = booked - '" + rsUpdate.getInt("soluong") + "' " +
//							"where npp_fk = '" + rsUpdate.getString("npp_fk") + "' and kho_fk = '" + rsUpdate.getString("kho_fk") + "' and kbh_fk = '" + rsUpdate.getString("kbh_fk") + "' and sanpham_fk = '" + rsUpdate.getString("sanpham_fk") + "' and solo = '" + rsUpdate.getString("soloOLD") + "' and NgayHetHan='"+rsUpdate.getString("NgayHetHanOLD")+"' ";
//					
//					if(db.updateReturnInt(query)!=1)
//					{
//						this.msg = "Không thể cập nhật đổi số lô: " + query;
//						db.getConnection().rollback();
//						rsUpdate.close();
//						return false;
//					}
					this.msg=util.Update_NPP_Kho_Sp_Chitiet(rsUpdate.getString("ngaydoi"), "Đổi số lô NPP", db, rsUpdate.getString("kho_fk"), rsUpdate.getString("sanpham_fk"), rsUpdate.getString("npp_fk"), rsUpdate.getString("kbh_fk"), rsUpdate.getString("soloOLD"), rsUpdate.getString("NgayHetHanOLD"), rsUpdate.getString("NgayNhapKhoOLD"), (-1)*rsUpdate.getDouble("soluong"), (-1)*rsUpdate.getDouble("soluong"), 0.0, 0.0, 0.0);
					if(this.msg.length()>0)
					{
						db.getConnection().rollback();
						rsUpdate.close();
						return false;
					}
					
					
					//TANG LO CHUYEN
//					query = "select count(sanpham_fk) as sodong from nhapp_kho_chitiet " +
//							"where NPP_FK = '" + rsUpdate.getString("npp_fk")  + "' and kho_fk = '" + rsUpdate.getString("kho_fk") + "' and kbh_fk = '" + rsUpdate.getString("kbh_fk") + "' and sanpham_fk = '" + rsUpdate.getString("sanpham_fk") + "' and SOLO = '" + rsUpdate.getString("solo") + "' and NgayHetHan='"+rsUpdate.getString("NgayHetHan")+"' ";
//			
//					ResultSet rsSp = db.get(query);
//					int sodong = 0;
//					
//					if(rsSp.next())
//					{
//						sodong = rsSp.getInt("sodong");
//						rsSp.close();
//					}
//					
//					if(sodong > 0) //Da co. Update
//					{
//						query = "update NHAPP_KHO_CHITIET set soluong = soluong + '" + rsUpdate.getInt("soluong") + "', available = available + '" + rsUpdate.getInt("soluong") + "', ngayhethan = '" + rsUpdate.getString("ngayhethan") + "' " +
//								"where npp_fk = '" + rsUpdate.getString("npp_fk") + "' and kho_fk = '" + rsUpdate.getString("kho_fk") + "' and kbh_fk = '" + rsUpdate.getString("kbh_fk") + "' and sanpham_fk = '" + rsUpdate.getString("sanpham_fk") + "' and SOLO = '" + rsUpdate.getString("solo") + "'  and NgayHetHan='"+rsUpdate.getString("NgayHetHan")+"' ";
//								
//						if(db.updateReturnInt(query)!=1)
//						{
//							this.msg = "Không thể chốt đổi số lô: " + query;
//							db.getConnection().rollback();
//							return false;
//						}
//						
//					}
//					else //Tao moi
//					{
//						query = "insert NHAPP_KHO_CHITIET(npp_fk, kho_fk, kbh_fk, sanpham_fk, solo, ngayhethan, soluong, booked, available) " +
//								"values('" + rsUpdate.getString("npp_fk") + "', '" + rsUpdate.getString("kho_fk") + "', '" + rsUpdate.getString("kbh_fk") + "', '" + rsUpdate.getString("sanpham_fk") + "', '" + rsUpdate.getString("solo") + "', '" + rsUpdate.getString("ngayhethan") + "', '" + rsUpdate.getInt("soluong") + "', '0', '" + rsUpdate.getInt("soluong") + "')";
//						
//						if(db.updateReturnInt(query)!=1)
//						{
//							this.msg = "Không thể chốt đổi số lô: " + query;
//							db.getConnection().rollback();
//							return false;
//						}
//						
//					}
					/// TĂNG KHO NHẬN + SỐ LƯỢNG + AVAI + TỒN THỜI ĐIỂM
					this.msg=util.Update_NPP_Kho_Sp_Chitiet(rsUpdate.getString("ngaydoi"), "Đổi số lô NPP", db, rsUpdate.getString("kho_fk"), rsUpdate.getString("sanpham_fk"), rsUpdate.getString("npp_fk"), rsUpdate.getString("kbh_fk"), rsUpdate.getString("solo"), rsUpdate.getString("ngayhethan"), rsUpdate.getString("ngaydoi"), rsUpdate.getDouble("soluong"), 0.0, rsUpdate.getDouble("soluong"), rsUpdate.getDouble("soluong"), 0.0);
					if(this.msg.length()>0)
					{
						db.getConnection().rollback();
						rsUpdate.close();
						return false;
					}
				}
				rsUpdate.close();
			}
			
			query = "update ERP_DOISOLONPP set trangthai = '1', ngaysua = '" + getDateTime() + "', nguoisua = '" + this.userId + "',Modified_Date=getdate() " +
					"where pk_seq = '" + this.id + "'  and TrangThai=0 ";
					
			System.out.println("____"+query);
			if(db.updateReturnInt(query)!=1)
			{
				this.msg = "Không thể chốt đổi số lô: " + query;
				db.getConnection().rollback();
				return false;
			}
			
			Utility util = new Utility();
			msg= util.Check_Huy_NghiepVu_KhoaSo("ERP_DOISOLONPP", id, "ngaydoi", db);
			if(msg.length()>0)
			{
				db.getConnection().rollback();
				return false;
			}
			
			msg= util.Check_Kho_Tong_VS_KhoCT(this.nppId, db);
			if(msg.length()>0)
			{
				db.getConnection().rollback();
				return false;
			}
			
			db.getConnection().commit();
			db.getConnection().setAutoCommit(true);
		} 
		catch (Exception e)
		{
			e.printStackTrace();
			this.msg = "Lỗi khi cập nhật: " + e.getMessage();
			System.out.println("Exception; " + e.getMessage());
			db.update("rollback");
			
			return false;
		}
		finally
		{
			if(db!=null)db.shutDown();
		}
		
		return true;
	}
	

	public boolean DeleteCk() 
	{
		try 
		{
			
			getNppInfo();
			
			db.update("SET TRANSACTION ISOLATION LEVEL SNAPSHOT;");
			db.update("SET LOCK_TIMEOUT 60000;");
			db.getConnection().setAutoCommit(false);
			
			
			System.out.println(":::Xoa::");
			//Cap nhat lai kho NPP truoc
			String query = "select a.ngaydoi,a.npp_fk, a.kho_fk, a.kbh_fk, b.sanpham_fk, b.soluong, b.soloOLD, b.solo,b.NgayHetHan,b.NgayHetHanOLD,b.ngaynhapkho,b.ngaynhapkhoOLD  " +
						   "from ERP_DOISOLONPP a inner join ERP_DOISOLONPP_SANPHAM b on a.pk_seq = b.doisolo_fk " +
						   "where a.pk_seq = '" + this.id + "'";
			
			System.out.println(":::Xoa::"+query);
			
			ResultSet rsUpdate = db.get(query);
			if(rsUpdate != null)
			{
				while(rsUpdate.next())
				{
//					query = "update nhapp_kho_chitiet set available = available + '" + rsUpdate.getInt("soluong") + "', booked = booked - '" + rsUpdate.getInt("soluong") + "' " +
//							"where npp_fk = '" + rsUpdate.getString("npp_fk") + "' and kho_fk = '" + rsUpdate.getString("kho_fk") + "' and kbh_fk = '" + rsUpdate.getString("kbh_fk") + "' and sanpham_fk = '" + rsUpdate.getString("sanpham_fk") + "' and solo = '" + rsUpdate.getString("soloOLD") + "' and NgayHetHan='"+rsUpdate.getString("NgayHetHanOLD")+"' ";
//					
//					System.out.println(":::Xoa::"+query);
//					
//					if(db.updateReturnInt(query)!=1)
//					{
//						this.msg = "Không thể cập nhật đổi số lô: " + query;
//						db.getConnection().rollback();
//						return false;
//					}
					
					this.msg= util.Update_NPP_Kho_Sp_Chitiet(rsUpdate.getString("ngaydoi"), "Đổi số lô", db, rsUpdate.getString("kho_fk"), rsUpdate.getString("sanpham_fk"), rsUpdate.getString("npp_fk"), rsUpdate.getString("kbh_fk"), rsUpdate.getString("soloOLD"), rsUpdate.getString("NgayHetHanOLD"),  rsUpdate.getString("ngaynhapkhoOLD"), 0.0, (-1)*rsUpdate.getInt("soluong"), rsUpdate.getInt("soluong"), rsUpdate.getInt("soluong"), 0.0);
					if(this.msg.length()>0)
					{
						db.getConnection().rollback();
						return false;
					}
				}
				rsUpdate.close();
			}
			
			query = "update ERP_DOISOLONPP set trangthai = '2', ngaysua = '" + getDateTime() + "', nguoisua = '" + this.userId + "'  ,Modified_Date=getdate() " +
					"where pk_seq = '" + this.id + "' and TrangThai=0  ";
					
			System.out.println(":::Xoa::"+query);
			
			if(db.updateReturnInt(query)!=1)
			{
				this.msg = "Không thể tạo cập nhật đổi số lô: " + query;
				db.getConnection().rollback();
				return false;
			}
			
			Utility util = new Utility();
			msg= util.Check_Huy_NghiepVu_KhoaSo("ERP_DOISOLONPP", id, "ngaydoi", db);
			if(msg.length()>0)
			{
				db.getConnection().rollback();
				return false;
			}
			
			msg= util.Check_Kho_Tong_VS_KhoCT(this.nppId, db);
			if(msg.length()>0)
			{
				db.getConnection().rollback();
				return false;
			}
			
			db.getConnection().commit();
			db.getConnection().setAutoCommit(true);
		} 
		catch (Exception e)
		{
			this.msg = "Lỗi khi cập nhật: " + e.getMessage();
			System.out.println("Exception; " + e.getMessage());
			db.update("rollback");
			db.shutDown();
			e.printStackTrace();
			return false;
		}
		finally
		{
			db.shutDown();
		}
		
		return true;
	}
	
	public void createRS() 
	{
		this.getNppInfo();
		
		String query = "select pk_seq, ten + ', ' + diengiai as khoTen from kho where trangthai = '1' and pk_seq in "+util.quyen_kho(this.userId) +" order by pk_seq asc";
		this.khoRs = db.get(query);
		
		query = "select pk_seq, diengiai from KENHBANHANG where trangthai = '1' order by pk_seq asc ";
		this.kbhRs = db.get(query);
		
		if(this.kbhId.trim().length() > 0 && this.khoId.trim().length() > 0)
		{
			if(this.id.trim().length() <= 0)
			{
				query = 
						" select a.ngaynhapkho as spNgayNhapKho,b.pk_seq as spId, b.ma as spMa, b.ten as spTen, isnull(c.donvi, 'NA') as donvi, a.solo, a.soluong, a.booked, a.available ,a.NgayHetHan as spNgayHetHan " +
						" from nhapp_kho_chitiet a inner join sanpham b on a.sanpham_fk = b.pk_seq inner join donvidoluong c on b.dvdl_fk = c.pk_seq  " +
						"where npp_fk = '" + this.nppId + "' and b.trangthai = '1' and kho_fk = '" + this.khoId + "' " +
								"and kbh_fk = '" + this.kbhId + "' and  a.available > 0 and a.ngaynhapkho <= '"+this.ngaychuyen +"' ";
			}
			else
			{
				query = "select a.ngaynhapkho as spNgayNhapKho,b.pk_seq as spId, b.ma as spMa, b.ten as spTen, isnull(c.donvi, 'NA') as donvi, a.solo, a.soluong, a.booked, " +
						"	a.available + ISNULL ( ( select soluong from ERP_DOISOLONPP_SANPHAM where sanpham_fk = b.pk_seq and soloOLD = a.solo and ngaynhapkhoOLD=a.ngaynhapkho and doisolo_fk = '" + this.id + "' and NgayHetHanOLD=a.NgayHetHan ), 0 ) as available,a.NgayHetHan as spNgayHetHan " +
						"from nhapp_kho_chitiet a inner join sanpham b on a.sanpham_fk = b.pk_seq inner join donvidoluong c on b.dvdl_fk = c.pk_seq  " +
						"where npp_fk = '" + this.nppId + "' and b.trangthai = '1' and kho_fk = '" + this.khoId + "' " +
								"and a.ngaynhapkho <= '"+this.ngaychuyen +"' and kbh_fk = '" + this.kbhId + "' and  a.available + ISNULL ( ( select soluong from ERP_DOISOLONPP_SANPHAM where sanpham_fk = b.pk_seq and soloOLD = a.solo and ngaynhapkhoOLD=a.ngaynhapkho and doisolo_fk = '" + this.id + "' and NgayHetHanOLD=a.NgayHetHan ), 0 ) > 0 ";
			}
			
			query += " order by b.ten asc ";
			System.out.println("2.Khoi tao SP: " + query);
			this.spRs = db.get(query);
		}
	}
	
	public void createRS2() 
	{
		this.getNppInfo();
		
		String query = "select pk_seq, ten + ', ' + diengiai as khoTen from kho where trangthai = '1' order by pk_seq asc";
		this.khoRs = db.get(query);
		
		query = "select pk_seq, diengiai from KENHBANHANG where trangthai = '1' order by pk_seq asc ";
		this.kbhRs = db.get(query);
		
		if(this.kbhId.trim().length() > 0 && this.khoId.trim().length() > 0)
		{
			if(this.id.trim().length() <= 0)
			{
				query = 
						" select b.pk_seq as spId, b.ma as spMa, b.ten as spTen, isnull(c.donvi, 'NA') as donvi, a.solo, a.soluong, a.booked, a.available ,a.ngaynhapkho,a.NgayHetHan as spNgayHetHan " +
						" from nhapp_kho_chitiet a inner join sanpham b on a.sanpham_fk = b.pk_seq inner join donvidoluong c on b.dvdl_fk = c.pk_seq  " +
						"where npp_fk = '" + this.nppId + "' and b.trangthai = '1' and kho_fk = '" + this.khoId + "' " +
								"and kbh_fk = '" + this.kbhId + "' and  a.available > 0 and a.ngaynhapkho <= '"+this.ngaychuyen +"' ";
			}
			else
			{
				query =
					"	select b.sanpham_fk as spId,c.MA as spMA,c.TEN as spTEN,d.DONVI,b.solo,b.soluong,0 as booked,0 as available,b.ngayhethan as spNgayHetHan,b.ngaynhapkhoOLD,b.ngaynhapkho as spNgayNhapKho,b.NgayHetHanOLD  as spNgayNhapKhoOLD,b.soloOLD "+
					"	 from ERP_DOISOLONPP a inner join ERP_DOISOLONPP_SANPHAM b on b.doisolo_fk=a.pk_seq "+
					"		inner join SANPHAM c on c.PK_SEQ=b.sanpham_fk  "+
					"		inner join DONVIDOLUONG d on d.PK_SEQ=c.DVDL_FK " +
					" where b.doisolo_fk='"+this.id+"'  ";
			}
			
			query += " order by c.ten asc ";
			System.out.println("2.Khoi tao SP: " + query);
			this.spRs = db.get(query);
		}
	}

	
	public void init()
	{
		String query = "select kbh_fk, ngaydoi, kho_fk, trangthai from ERP_DOISOLONPP where pk_seq = '" + this.id + "'";
		ResultSet rs = db.get(query);
		if(rs != null)
		{
			try
			{
				if(rs.next())
				{
					this.ngaychuyen = rs.getString("ngaydoi");
					this.kbhId = rs.getString("kbh_fk");
					this.khoId = rs.getString("kho_fk");
					this.trangthai = rs.getString("trangthai");
					
					rs.close();
				}
				
				query = "select sanpham_fk,ngaynhapkho,ngaynhapkhoOLD, soloOLD,NgayHetHanOLD, solo, soluong, ngayhethan,NgayHetHanOLD " +
						"from ERP_DOISOLONPP_SANPHAM where doisolo_fk = '" + this.id + "'";
				rs = db.get(query);
				if(rs != null)
				{
					this.sp_soloOLD = new Hashtable<String, String>();
					this.sp_solo = new Hashtable<String, String>();
					this.sp_sl = new Hashtable<String, Integer>();
					this.sp_ngayhethan = new Hashtable<String, String>();
					this.sp_ngayhethanOLD = new Hashtable<String, String>();
					this.sp_ngaynhapkho = new Hashtable<String, String>();
					this.sp_ngaynhapkhoOLD = new Hashtable<String, String>();

					
					while(rs.next())
					{
						this.sp_solo.put(rs.getString("sanpham_fk") + "__" + rs.getString("soloOLD")+ "__" + rs.getString("NgayHetHanOLD")+ "__" + rs.getString("NgayNhapKhoOLD"),     rs.getString("solo"));
						this.sp_sl.put(rs.getString("sanpham_fk") + "__" + rs.getString("soloOLD")+ "__" + rs.getString("NgayHetHanOLD")+ "__" + rs.getString("NgayNhapKhoOLD"),       rs.getInt("soluong"));
						this.sp_ngayhethan.put(rs.getString("sanpham_fk") + "__" + rs.getString("soloOLD")+"__" + rs.getString("NgayHetHanOLD")+ "__" + rs.getString("NgayNhapKhoOLD") , rs.getString("ngayhethan"));
						this.sp_ngaynhapkho.put(rs.getString("sanpham_fk") + "__" + rs.getString("soloOLD")+"__" + rs.getString("NgayHetHanOLD")+"__" + rs.getString("ngaynhapkhoOLD"),rs.getString("ngaynhapkho"));
					}
					rs.close();
				}
			} 
			catch (Exception e) 
			{
				e.printStackTrace();
			}
		}
		
		this.createRS();
		
	}
	
	public void initDisplay() 
	{
		String query = "select kbh_fk, ngaydoi, kho_fk, trangthai from ERP_DOISOLONPP where pk_seq = '" + this.id + "'";
		ResultSet rs = db.get(query);
		if(rs != null)
		{
			try
			{
				if(rs.next())
				{
					this.ngaychuyen = rs.getString("ngaydoi");
					this.kbhId = rs.getString("kbh_fk");
					this.khoId = rs.getString("kho_fk");
					this.trangthai = rs.getString("trangthai");
					
					rs.close();
				}
				
				query = "select sanpham_fk,ngaynhapkhoOLD,ngaynhapkho, soloOLD,NgayHetHanOLD, solo, soluong, ngayhethan,NgayHetHanOLD " +
						"from ERP_DOISOLONPP_SANPHAM where doisolo_fk = '" + this.id + "'";
				rs = db.get(query);
				if(rs != null)
				{
					this.sp_soloOLD = new Hashtable<String, String>();
					this.sp_solo = new Hashtable<String, String>();
					this.sp_sl = new Hashtable<String, Integer>();
					this.sp_ngayhethan = new Hashtable<String, String>();
					this.sp_ngayhethanOLD = new Hashtable<String, String>();
					this.sp_ngaynhapkho = new Hashtable<String, String>();
					this.sp_ngaynhapkhoOLD = new Hashtable<String, String>();
					while(rs.next())
					{
						this.sp_solo.put(rs.getString("sanpham_fk") + "__" + rs.getString("soloOLD")+ "__" + rs.getString("NgayHetHanOLD"),     rs.getString("solo"));
						this.sp_sl.put(rs.getString("sanpham_fk") + "__" + rs.getString("soloOLD")+ "__" + rs.getString("NgayHetHanOLD"),       rs.getInt("soluong"));
						this.sp_ngayhethan.put(rs.getString("sanpham_fk") + "__" + rs.getString("soloOLD")+"__" + rs.getString("NgayHetHanOLD") , rs.getString("ngayhethan"));
						this.sp_ngaynhapkho.put(rs.getString("sanpham_fk") + "__" + rs.getString("soloOLD")+"__" + rs.getString("NgayHetHanOLD")+"__" +  rs.getString("ngaynhapkhoOLD"),rs.getString("ngaynhapkho"));
					}
					rs.close();
				}
			} 
			catch (Exception e) 
			{
				e.printStackTrace();
			}
		}
		
		
		this.createRS2();
	}

	private void getNppInfo()
	{		
		geso.dms.distributor.util.Utility util=new geso.dms.distributor.util.Utility();
		this.nppId=util.getIdNhapp(this.userId);
		this.nppTen=util.getTenNhaPP();
		this.sitecode=util.getSitecode();
	}
	
	public void DBclose()
	{
		try 
		{
			if(this.kbhRs != null)
				this.kbhRs.close();
			
			if(this.khoRs != null)
				this.khoRs.close();
			if(this.spRs != null)
				this.spRs.close();
			
			db.shutDown();
		} 
		catch (Exception e) {}
		
		
	}

	public Hashtable<String, Integer> getSp_Soluong() 
	{
		return this.sp_sl;
	}

	public void setSSp_Soluong(Hashtable<String, Integer> sp_sl) 
	{
		this.sp_sl = sp_sl;
	}
	
	private String getDateTime()
	{
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date date = new Date();
        return dateFormat.format(date);	
	}

	
	public ResultSet getSoloTheoSp(String spIds, String tongluong)
	{
		String query = "select AVAILABLE, SOLO, NGAYHETHAN from NHAPP_KHO_CHITIET " +
					   "where NPP_FK = '" + this.nppId + "' and KHO_FK = '" + this.khoId + "' and SANPHAM_FK = '" + spIds + "' and KBH_FK = '100025' order by NGAYHETHAN asc";
		
		return db.get(query);
	}

	
	public Hashtable<String, String> getSp_Solo() {
		
		return this.sp_solo;
	}

	
	public void setSSp_Solo(Hashtable<String, String> sp_solo) {
		
		this.sp_solo = sp_solo;
	}

	
	public Hashtable<String, String> getSp_Ngayhethan() {
		
		return this.sp_ngayhethan;
	}

	
	public void setSSp_Ngayhethan(Hashtable<String, String> sp_ngayhethan) {
		
		this.sp_ngayhethan = sp_ngayhethan;
	}

	
	public Hashtable<String, String> getSp_SoloOLD() {

		return this.sp_soloOLD;
	}


	public void setSSp_SoloOLD(Hashtable<String, String> sp_soloOLD) {
		
		this.sp_soloOLD = sp_soloOLD;
	}

	public Hashtable<String, String> getSp_ngaynhapkho() {
		return sp_ngaynhapkho;
	}

	public void setSp_ngaynhapkho(Hashtable<String, String> sp_ngaynhapkho) {
		this.sp_ngaynhapkho = sp_ngaynhapkho;
	}

	public Hashtable<String, String> getSp_ngaynhapkhoOLD() {
		return sp_ngaynhapkhoOLD;
	}

	public void setSp_ngaynhapkhoOLD(Hashtable<String, String> sp_ngaynhapkhoOLD) {
		this.sp_ngaynhapkhoOLD = sp_ngaynhapkhoOLD;
	}
	

	 public static boolean isValidDate(String dateToValidate, String dateFormat)
	    {
	    
	         
	     if(dateToValidate == null)
	        {
	            return false;
	        }
	        
	        SimpleDateFormat sdf = new SimpleDateFormat(dateFormat);
	         sdf.setLenient(false);
	        
	        try 
	        {
	            //If not valid, it will to throw ParseException.
	            //Trường hợp không hợp lệ, nó sẽ chuyển sang xử lý ngoại lệ.
	            Date date = sdf.parse(dateToValidate);
	            //System.out.println(date);
	        
	        } 
	        catch (ParseException e) 
	        {        
	            //e.printStackTrace();
	            return false;
	        }
	        
	        return true;
	    }
	    
	 
	 public static void main (String agrs[])
	 {
		 String dateFormat = "yyyy-MM-dd";
		 System.out.println("ngay nay la  "+isValidDate("2016-02-01",dateFormat));
	 }
	
}
