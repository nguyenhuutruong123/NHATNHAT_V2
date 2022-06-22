package geso.dms.center.beans.doisolo.imp;


import geso.dms.center.beans.doisolo.IErpDoisolo;
import geso.dms.center.db.sql.dbutils;

import java.io.Serializable;
import java.sql.ResultSet;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Enumeration;
import java.util.Hashtable;

import javax.servlet.http.HttpServletRequest;

public class ErpDoisolo implements IErpDoisolo, Serializable
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
	Hashtable<String, String> sp_ngaynhapkho;
	Hashtable<String, String> sp_ngayhethanOLD;

	
	
	
	String nppId;
	String nppTen;
	String sitecode;
		
	dbutils db;
	
	public ErpDoisolo()
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
		this.sp_ngaynhapkho = new Hashtable<String, String>();
		db = new dbutils();
	}
	
	public ErpDoisolo(String id)
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
		this.sp_ngayhethanOLD = new Hashtable<String, String>();
		this.sp_ngaynhapkho = new Hashtable<String, String>();
		db = new dbutils();
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
			db.getConnection().setAutoCommit(false);
			
			String query = "insert ERP_DOISOLO(kho_fk, ngaydoi, trangthai, ngaytao, nguoitao, ngaysua, nguoisua) " +
							"values( '" + this.khoId + "', '" + this.ngaychuyen + "', '0', '" + getDateTime() + "', '" + this.userId + "', '" + getDateTime() + "', '" + this.userId + "')";
			
			if(!db.update(query))
			{
				this.msg = "Không thể tạo mới đổi số lô: " + query;
				db.getConnection().rollback();
				return false;
			}
						
			String ckId = "";
			query = "select scope_identity() as ckId";
			ResultSet ckCurrent = db.get(query);
			if(ckCurrent != null)
			{
				if(ckCurrent.next())
				{
					ckId = ckCurrent.getString("ckId");
					ckCurrent.close();
				}
			}
			
			Enumeration<String> keys = this.sp_solo.keys();
			while(keys.hasMoreElements())
			{
				String key = keys.nextElement();
				String spID = key.split("__")[0];
				
				System.out.println("KEY____"+key);
				
				//CHECK TON KHO
				double avai = 0;
				String spTEN = "";
				
				String spNgayHetHanOLD="";
				
				
					query = "select available, ( select ten from SANPHAM where pk_seq = a.sanpham_fk ) as spTEN,NgayHetHan as spNgayhethan " +
						"from ERP_KHOTT_SP_CHITIET a " +
						"where  khott_fk = '" + this.khoId + "'  and sanpham_fk = '" + spID + "' and solo = '" + this.sp_soloOLD.get(key) + "' and NgayHetHan='"+this.sp_ngayhethanOLD.get(key)+"' ";

					System.out.println(":::"+query);
					ResultSet rsCHECK = db.get(query);
					if(rsCHECK.next())
					{
						avai = rsCHECK.getDouble("available");
						spTEN = rsCHECK.getString("spTEN");
						spNgayHetHanOLD=rsCHECK.getString("spNgayhethan");
					}
					rsCHECK.close();
				
				
				if(avai < this.sp_sl.get(key) )
				{
					this.msg = "Tồn kho của sản phẩm ( " + spTEN + " ) với số lô ( " + this.sp_soloOLD.get(key) + " ), số lượng ( " + this.sp_sl.get(key) + " ) còn tối đa ( " + avai + " ) ";
					db.getConnection().rollback();
					return false;
				}
				
				query = 
						"insert ERP_DOISOLO_SANPHAM(doisolo_fk, sanpham_fk, soloOLD, solo, soluong, ngayhethan,NgayHetHanOLD) " +
						"values('" + ckId + "', '" + spID + "', '" + this.sp_soloOLD.get(key) + "', '" + this.sp_solo.get(key) + "', '" + this.sp_sl.get(key) + "', '" + this.sp_ngayhethan.get(key) + "','"+sp_ngayhethanOLD.get(key)+"')";
				if(!db.update(query))
				{
					this.msg = "Không thể tạo mới ERP_DOISOLONPP_SANPHAM: " + query;
					db.getConnection().rollback();
					return false;
				}
				
				query = "update ERP_KHOTT_SP_CHITIET set available = available - '" + this.sp_sl.get(key) + "', booked = booked + '" + this.sp_sl.get(key) + "' " +
						"where   khott_fk = '" + this.khoId + "'  and sanpham_fk = '" + spID + "' and solo = '" + this.sp_soloOLD.get(key) + "' and ngayHetHan='"+sp_ngayhethanOLD.get(key)+"' ";
						
				if(db.updateReturnInt(query)!=1)
				{
					this.msg = "Không thể tạo mới ERP_KHOTT_SP_CHITIET: " + query;
					db.getConnection().rollback();
					return false;
				}
				
			}
			db.getConnection().commit();
			db.getConnection().setAutoCommit(true);
		} 
		catch (Exception e)
		{
			this.msg += "Lỗi khi tạo mới: " + e.getMessage();
			System.out.println("Exception; " + e.getMessage());
			e.printStackTrace();
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
			
			db.getConnection().setAutoCommit(false);
			
			//Cap nhat lai kho NPP truoc
			String query = "select a.kho_fk, b.sanpham_fk, b.soluong, b.soloOLD, b.solo,b.NgayHetHan,b.NgayHetHanOLD  " +
						   "from ERP_DOISOLO a inner join ERP_DOISOLO_SANPHAM b on a.pk_seq = b.doisolo_fk " +
						   "where a.pk_seq = '" + this.id + "'";
			
			ResultSet rsUpdate = db.get(query);
			if(rsUpdate != null)
			{
				while(rsUpdate.next())
				{
					query = "update ERP_KHOTT_SP_CHITIET set available = available + '" + rsUpdate.getInt("soluong") + "', booked = booked - '" + rsUpdate.getInt("soluong") + "' " +
							"where  khott_fk = '" + rsUpdate.getString("kho_fk") + "' and sanpham_fk = '" + rsUpdate.getString("sanpham_fk") + "' and solo = '" + rsUpdate.getString("soloOLD") + "' and NgayHetHan='"+rsUpdate.getString("NgayHetHanOLD")+"' ";
					
					if(db.updateReturnInt(query)!=1)
					{
						this.msg = "Không thể cập nhật đổi số lô: " + query;
						db.getConnection().rollback();
						return false;
					}
				}
				rsUpdate.close();
			}
			

			query = "update ERP_DOISOLO set  kho_fk = '" + this.khoId + "', ngaydoi = '" + this.ngaychuyen + "', " +
					" ngaysua = '" + getDateTime() + "', nguoisua = '" + this.userId + "' where pk_seq = '" + this.id + "' and trangthai=0 ";
					
			if(db.updateReturnInt(query)!=1)
			{
				this.msg = "Không thể tạo cập nhật ERP_DOISOLONPP: " + query;
				db.getConnection().rollback();
				return false;
			}
			
			query = "delete ERP_DOISOLO_SANPHAM where doisolo_fk = '" + this.id + "'";	
			if(!db.update(query))
			{
				this.msg = "Không thể tạo cập nhật ERP_DOISOLO_SANPHAM: " + query;
				db.getConnection().rollback();
				return false;
			}
			
			Enumeration<String> keys = this.sp_solo.keys();
			while(keys.hasMoreElements())
			{
				String key = keys.nextElement();
				String spID = key.split("__")[0];
				
				//CHECK TON KHO
				double avai = 0;
				String spTEN = "";
				
				query = "select available, ( select ten from SANPHAM where pk_seq = a.sanpham_fk ) as spTEN,a.NgayHetHan " +
						"from ERP_KHOTT_SP_CHITIET a " +
						"where  khoTT_fk = '" + this.khoId + "'  and sanpham_fk = '" + spID + "' and solo = '" + this.sp_soloOLD.get(key) + "' and NgayHetHan='"+this.sp_ngayhethanOLD.get(key)+"' ";
				ResultSet rsCHECK = db.get(query);
				if(rsCHECK.next())
				{
					avai = rsCHECK.getDouble("available");
					spTEN = rsCHECK.getString("spTEN");
				}
				rsCHECK.close();
				
				if(avai < this.sp_sl.get(key) )
				{
					this.msg = "Tồn kho của sản phẩm ( " + spTEN + " ) với số lô ( " + this.sp_soloOLD.get(key) + " ), số lượng ( " + this.sp_sl.get(key) + " ) còn tối đa ( " + avai + " ) ";
					db.getConnection().rollback();
					return false;
				}
				
				query = "insert ERP_DOISOLO_SANPHAM(doisolo_fk, sanpham_fk, soloOLD, solo, soluong, ngayhethan,NgayHetHanOLD) " +
						"values('" + this.id + "', '" + spID + "', '" + this.sp_soloOLD.get(key) + "', '" + this.sp_solo.get(key) + "', '" + this.sp_sl.get(key) + "','"+this.sp_ngayhethan.get(key)+"' ,'" + this.sp_ngayhethanOLD.get(key) + "')";
				if(!db.update(query))
				{
					this.msg = "Không thể tạo mới ERP_DOISOLO_SANPHAM: " + query;
					db.getConnection().rollback();
					return false;
				}
				
				query = "update ERP_KHOTT_SP_CHITIET set available = available - '" + this.sp_sl.get(key) + "', booked = booked + '" + this.sp_sl.get(key) + "' " +
						"where  khott_fk = '" + this.khoId + "'  and sanpham_fk = '" + spID + "' and solo = '" + this.sp_soloOLD.get(key) + "' and NgayHetHan='"+this.sp_ngayhethanOLD.get(key)+"' ";
						
				if(db.updateReturnInt(query)!=1)
				{
					this.msg = "Không thể tạo mới ERP_KHOTT_SP_CHITIET: " + query;
					db.getConnection().rollback();
					return false;
				}
			}
			
			db.getConnection().commit();
			db.getConnection().setAutoCommit(true);
		} 
		catch (Exception e)
		{
			this.msg += "Lỗi khi cập nhật: " + e.getMessage();
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
			db.getConnection().setAutoCommit(false);
			String query = "select  a.kho_fk, b.sanpham_fk, b.soluong, b.soloOLD, b.solo, b.ngayhethan,b.NgayHetHanOLD  " +
						   "from ERP_DOISOLO a inner join ERP_DOISOLO_SANPHAM b on a.pk_seq = b.doisolo_fk " +
						   "where a.pk_seq = '" + this.id + "' and TrangThai=0 ";
			ResultSet rsUpdate = db.get(query);
				while(rsUpdate.next())
				{
					query = "update ERP_KHOTT_SP_CHITIET set soluong = soluong - '" + rsUpdate.getInt("soluong") + "', booked = booked - '" + rsUpdate.getInt("soluong") + "' " +
									"where  khott_fk = '" + rsUpdate.getString("kho_fk") + "' and sanpham_fk = '" + rsUpdate.getString("sanpham_fk") + "' and solo = '" + rsUpdate.getString("soloOLD") + "' and NgayHetHan='"+rsUpdate.getString("NgayHetHanOLD")+"' ";
					if(db.updateReturnInt(query)!=1)
					{
						this.msg = "Không thể cập nhật đổi số lô: " + query;
						db.getConnection().rollback();
						rsUpdate.close();
						return false;
					}
					
					//TANG LO CHUYEN
					query = "select count(sanpham_fk) as sodong from ERP_KHOTT_SP_CHITIET " +
							"where  khott_fk = '" + rsUpdate.getString("kho_fk") + "' and  sanpham_fk = '" + rsUpdate.getString("sanpham_fk") + "' and SOLO = '" + rsUpdate.getString("solo") + "' and NgayHetHan='"+rsUpdate.getString("NgayHetHan")+"' ";
					
					System.out.println("check sodong co ton tai trong kho hay k: " + query);
					ResultSet rsSp = db.get(query);
					int sodong = 0;
					
					if(rsSp.next())
					{
						sodong = rsSp.getInt("sodong");
						rsSp.close();
					}
					if(sodong > 0) //Da co. Update
					{
						query = "update ERP_KHOTT_SP_CHITIET set soluong = soluong + '" + rsUpdate.getInt("soluong") + "', available = available + '" + rsUpdate.getInt("soluong") + "', ngayhethan = '" + rsUpdate.getString("ngayhethan") + "' " +
								"where  khott_fk = '" + rsUpdate.getString("kho_fk") + "'  and sanpham_fk = '" + rsUpdate.getString("sanpham_fk") + "' and SOLO = '" + rsUpdate.getString("solo") + "'  and NgayHetHan='"+rsUpdate.getString("NgayHetHan")+"' ";
								
						if(db.updateReturnInt(query)!=1)
						{
							this.msg = "Không thể chốt đổi số lô: " + query;
							db.getConnection().rollback();
							return false;
						}
					}
					else //Tao moi
					{
						query = "insert ERP_KHOTT_SP_CHITIET( khott_fk, sanpham_fk, solo, ngayhethan, soluong, booked, available) " +
								"values('" + rsUpdate.getString("kho_fk") + "', '" + rsUpdate.getString("sanpham_fk") + "', '" + rsUpdate.getString("solo") + "', '" + rsUpdate.getString("ngayhethan") + "', '" + rsUpdate.getDouble("soluong") + "', '0', '" + rsUpdate.getDouble("soluong") + "')";
						
						if(db.updateReturnInt(query)!=1)
						{
							this.msg = "Không thể chốt đổi số lô: " + query;
							db.getConnection().rollback();
							return false;
						}
					}
				}
				rsUpdate.close();
			
			query = "update ERP_DOISOLO set trangthai = '1', ngaysua = '" + getDateTime() + "', nguoisua = '" + this.userId + "',Modified_Date=getdate() where pk_seq = '" + this.id + "'  and TrangThai=0 ";					
			if(db.updateReturnInt(query)!=1)
			{
				this.msg = "Không thể chốt đổi số lô: " + query;
				db.getConnection().rollback();
				return false;
			}
			
			db.getConnection().commit();
			db.getConnection().setAutoCommit(true);
		} 
		catch (Exception e)
		{
			this.msg += "Lỗi khi cập nhật: " + e.getMessage();
			db.update("rollback");
			e.printStackTrace();
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
			db.getConnection().setAutoCommit(false);
			
			
			System.out.println(":::Xoa::");
			//Cap nhat lai kho NPP truoc
			String query = "select a.kho_fk, b.sanpham_fk, b.soluong, b.soloOLD, b.solo,b.NgayHetHan,b.NgayHetHanOLD  " +
						   "from ERP_DOISOLO a inner join ERP_DOISOLO_SANPHAM b on a.pk_seq = b.doisolo_fk " +
						   "where a.pk_seq = '" + this.id + "'";
			System.out.println(":::Xoa::"+query);
			ResultSet rsUpdate = db.get(query);
			{
				while(rsUpdate.next())
				{
					query = "update ERP_KHOTT_SP_CHITIET set available = available + '" + rsUpdate.getInt("soluong") + "', booked = booked - '" + rsUpdate.getInt("soluong") + "' " +
							"where  khott_fk = '" + rsUpdate.getString("kho_fk") + "' and sanpham_fk = '" + rsUpdate.getString("sanpham_fk") + "' and solo = '" + rsUpdate.getString("soloOLD") + "' and NgayHetHan='"+rsUpdate.getString("NgayHetHanOLD")+"' ";
					
					System.out.println(":::Xoa::"+query);
					if(db.updateReturnInt(query)!=1)
					{
						this.msg = "Không thể cập nhật đổi số lô: " + query;
						db.getConnection().rollback();
						return false;
					}
				}
				rsUpdate.close();
			}
			
			query = "update ERP_DOISOLO set trangthai = '2', ngaysua = '" + getDateTime() + "', nguoisua = '" + this.userId + "',Modified_Date=getdate()  " +
					"where pk_seq = '" + this.id + "' and TrangThai=0  ";
					
			System.out.println(":::Xoa::"+query);
			if(db.updateReturnInt(query)!=1)
			{
				this.msg = "Không thể tạo cập nhật đổi số lô: " + query;
				db.getConnection().rollback();
				return false;
			}
			db.getConnection().commit();
			db.getConnection().setAutoCommit(true);
		} 
		catch (Exception e)
		{
			this.msg += "Lỗi khi cập nhật: " + e.getMessage();
			System.out.println("Exception; " + e.getMessage());
			db.update("rollback");
			db.shutDown();
			e.printStackTrace();
			return false;
		}
		finally
		{
			if(db!=null)db.shutDown();
		}
		return true;
	}
	
	public void createRS() 
	{
		String query = "select pk_seq, MA + ', ' + TEN as khoTen from Erp_KhoTT where trangthai = '1' order by pk_seq asc ";
		this.khoRs = db.get(query);
		
		query = "select pk_seq, diengiai from KENHBANHANG where trangthai = '1' order by pk_seq asc ";
		this.kbhRs = db.get(query);
		
		if(this.khoId.trim().length() > 0)
		{
			if(this.id.trim().length() <= 0)
			{
				query = 
						" select b.pk_seq as spId, b.ma as spMa, b.ten as spTen, isnull(c.donvi, 'NA') as donvi, a.solo, a.soluong, a.booked, a.available ,a.NgayHetHan as spNgayHetHan " +
						" from ERP_KHOTT_SP_CHITIET a inner join sanpham b on a.sanpham_fk = b.pk_seq inner join donvidoluong c on b.dvdl_fk = c.pk_seq  " +
						"where   b.trangthai = '1' and a.khott_fk = '" + this.khoId + "' " +
								" and  a.available > 0 ";
			}
			else
			{
				query = "select b.pk_seq as spId, b.ma as spMa, b.ten as spTen, isnull(c.donvi, 'NA') as donvi, a.solo, a.soluong, a.booked, " +
						"	a.available + ISNULL ( ( select soluong from ERP_DOISOLO_SANPHAM where sanpham_fk = b.pk_seq and soloOLD = a.solo and doisolo_fk = '" + this.id + "' and NgayHetHanOLD=a.NgayHetHan ), 0 ) as available,a.NgayHetHan as spNgayHetHan, a.NGAYNHAPKHO as spNgayNhapKho " +
						"from ERP_KHOTT_SP_CHITIET a inner join sanpham b on a.sanpham_fk = b.pk_seq inner join donvidoluong c on b.dvdl_fk = c.pk_seq  " +
						"where b.trangthai = '1' and a.khott_fk = '" + this.khoId + "' " +
								" and  a.available + ISNULL ( ( select soluong from ERP_DOISOLO_SANPHAM ct where sanpham_fk = b.pk_seq and soloOLD = a.solo and ct.NgayHetHanOld=a.NgayHetHan and doisolo_fk = '" + this.id + "' ), 0 ) > 0 ";
			}
			
			query += " order by b.ten asc ";
			System.out.println("2.Khoi tao SP: " + query);
			this.spRs = db.get(query);
		}
	}
	
	public void createRS2() 
	{
this.getNppInfo();
		
		String query = "select pk_seq, ma + ', ' + ten as khoTen from Erp_Khott where trangthai = '1' order by pk_seq asc";
		this.khoRs = db.get(query);
		
		query = "select pk_seq, diengiai from KENHBANHANG where trangthai = '1' order by pk_seq asc ";
		this.kbhRs = db.get(query);
		
		if( this.khoId.trim().length() > 0)
		{
			if(this.id.trim().length() <= 0)
			{
				query = 
						" select b.pk_seq as spId, b.ma as spMa, b.ten as spTen, isnull(c.donvi, 'NA') as donvi, a.solo, a.soluong, a.booked, a.available ,a.NgayHetHan as spNgayHetHan " +
						" from ERP_KHOTT_SP_CHITIET a inner join sanpham b on a.sanpham_fk = b.pk_seq inner join donvidoluong c on b.dvdl_fk = c.pk_seq  " +
						"where  b.trangthai = '1' and kho_fk = '" + this.khoId + "' " +
								" and  a.available > 0 ";
			}
			else
			{
				query =
					"	select b.sanpham_fk as spId,c.MA as spMA,c.TEN as spTEN,d.DONVI,b.solo,b.soluong,0 as booked,0 as available,b.ngayhethan as spNgayHetHan,b.NgayHetHanOLD ,b.soloOLD "+
					"	 from ERP_DOISOLO a inner join ERP_DOISOLO_SANPHAM b on b.doisolo_fk=a.pk_seq "+
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
		String query = "select  ngaydoi, kho_fk, trangthai from ERP_DOISOLO where pk_seq = '" + this.id + "'";
		ResultSet rs = db.get(query);
		if(rs != null)
		{
			try
			{
				if(rs.next())
				{
					this.ngaychuyen = rs.getString("ngaydoi");
					this.khoId = rs.getString("kho_fk");
					this.trangthai = rs.getString("trangthai");
					
					rs.close();
				}
				
				query = "select sanpham_fk, soloOLD,NgayHetHanOLD, solo, soluong, ngayhethan,NgayHetHanOLD " +
						"from ERP_DOISOLO_SANPHAM where doisolo_fk = '" + this.id + "'";
				rs = db.get(query);
				if(rs != null)
				{
					this.sp_soloOLD = new Hashtable<String, String>();
					this.sp_solo = new Hashtable<String, String>();
					this.sp_sl = new Hashtable<String, Integer>();
					this.sp_ngayhethan = new Hashtable<String, String>();
					this.sp_ngayhethanOLD = new Hashtable<String, String>();
					
					while(rs.next())
					{
						this.sp_solo.put(rs.getString("sanpham_fk") + "__" + rs.getString("soloOLD")+ "__" + rs.getString("NgayHetHanOLD"),     rs.getString("solo"));
						this.sp_sl.put(rs.getString("sanpham_fk") + "__" + rs.getString("soloOLD")+ "__" + rs.getString("NgayHetHanOLD"),       rs.getInt("soluong"));
						this.sp_ngayhethan.put(rs.getString("sanpham_fk") + "__" + rs.getString("soloOLD")+"__" + rs.getString("NgayHetHanOLD") , rs.getString("ngayhethan"));
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
		String query = "select ngaydoi, kho_fk, trangthai from ERP_DOISOLO where pk_seq = '" + this.id + "'";
		ResultSet rs = db.get(query);
		if(rs != null)
		{
			try
			{
				if(rs.next())
				{
					this.ngaychuyen = rs.getString("ngaydoi");
					this.khoId = rs.getString("kho_fk");
					this.trangthai = rs.getString("trangthai");
					
					rs.close();
				}
				
				query = "select sanpham_fk, soloOLD,NgayHetHanOLD, solo, soluong, ngayhethan,NgayHetHanOLD " +
						"from ERP_DOISOLO_SANPHAM where doisolo_fk = '" + this.id + "'";
				rs = db.get(query);
				if(rs != null)
				{
					this.sp_soloOLD = new Hashtable<String, String>();
					this.sp_solo = new Hashtable<String, String>();
					this.sp_sl = new Hashtable<String, Integer>();
					this.sp_ngayhethan = new Hashtable<String, String>();
					this.sp_ngayhethanOLD = new Hashtable<String, String>();
					
					while(rs.next())
					{
						this.sp_solo.put(rs.getString("sanpham_fk") + "__" + rs.getString("soloOLD")+ "__" + rs.getString("NgayHetHanOLD"),     rs.getString("solo"));
						this.sp_sl.put(rs.getString("sanpham_fk") + "__" + rs.getString("soloOLD")+ "__" + rs.getString("NgayHetHanOLD"),       rs.getInt("soluong"));
						this.sp_ngayhethan.put(rs.getString("sanpham_fk") + "__" + rs.getString("soloOLD")+"__" + rs.getString("NgayHetHanOLD") , rs.getString("ngayhethan"));
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
		String query = "select AVAILABLE, SOLO, NGAYHETHAN from ERP_KHOTT_SP_CHITIET " +
					   "where  KHOTT_FK = '" + this.khoId + "' and SANPHAM_FK = '" + spIds + "'  order by NGAYHETHAN asc";
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
	

	
}
