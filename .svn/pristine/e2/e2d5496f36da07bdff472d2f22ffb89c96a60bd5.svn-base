package geso.dms.erp.beans.lenhsanxuat.imp;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import geso.dms.center.db.sql.dbutils;
import geso.dms.erp.beans.lenhsanxuat.IErpBundle;

public class ErpBundle implements IErpBundle
{
	String userId;
	String id;

	String ngaythuchien;
	String diengiai;

	ResultSet spRs;
	String spId;
	String soluong;
	String dongia;
	String thanhtien;
	String avai;
	
	ResultSet khottRs;
	String khoId;
	
	ResultSet nppRs;
	String nppDatId= "";
	ResultSet kbhRs;
	String kbhId= "";
	
	
	ResultSet soloRs;
	String soloId;
	
	String[] spIds;
	String[] spMaIds;
	String[] spTenIds;
	String[] soluongIds;
	String[] dongiaIds;
	String[] thanhtienIds;
	String[] soloIds;
	String[] soloChonIds;
	
	String msg;
	
	dbutils db;
	
	public ErpBundle()
	{
		this.userId = "";
		this.id = "";
		this.diengiai = "";
		this.spId = "";
		this.soluong = "";
		this.dongia = "";
		this.thanhtien = "";
		this.msg = "";
		this.soloId = "";
		this.khoId = "";
		this.ngaythuchien = "";
		this.avai = "";
		this.soloChonIds = new String[5];
		this.db = new dbutils();
	}
	
	public ErpBundle(String id)
	{
		this.id = id;
		this.userId = "";
		this.diengiai = "";
		this.spId = "";
		this.soluong = "";
		this.dongia = "";
		this.thanhtien = "";
		this.msg = "";
		this.soloId = "";
		this.khoId = "";
		this.ngaythuchien = "";
		this.avai = "";
		this.soloChonIds = new String[5];
		this.db = new dbutils();
	}
	
	public String getId() 
	{
		return this.id;
	}

	public void setId(String Id) 
	{
		this.id = Id;
	}
	
	public String getUserId() 
	{
		return this.userId;
	}

	public void setUserId(String userId) 
	{
		this.userId = userId;	
	}

	public String getMsg() 
	{
		return this.msg;
	}

	public void setMsg(String msg) 
	{
		this.msg = msg;
	}

	public void init() 
	{
		String query = "select KHOTT_FK, ngaythuchien, diengiai, sanpham_fk, soluong, SOLO from Erp_BUNDLE " +
						"where PK_SEQ = '" + this.id + "'";
		ResultSet rs = db.get(query);
		if(rs != null)
		{
			try 
			{
				while(rs.next())
				{
					this.khoId = rs.getString("KHOTT_FK");
					this.ngaythuchien = rs.getString("ngaythuchien");
					this.diengiai = rs.getString("diengiai");
					this.spId = rs.getString("sanpham_fk");
					this.soluong = rs.getString("soluong");
					this.soloId = rs.getString("SOLO");
				}
				rs.close();
			} 
			catch (Exception e) 
			{
				System.out.println("__Exception: " + e.getMessage());
			}
		}
		
		//INIT SP CON
		this.soloChonIds = new String[]{"", "", "", "", ""};
		
		query = " select PK_SEQ,  TEN from Kho where trangthai = 1 ";
		this.khottRs = db.get(query);
		
		query = "select SanPham_fk, soluong, SOLO from ERP_BUNDLE_SANPHAM where Bundle_fk = '" + this.id + "'";
		rs = db.get(query);
		if(rs != null)
		{
			try 
			{
				String _spIds = "";
				String _soluongIds = "";
				String _giatonIds = "";
				String _soloIds = "";
				
				NumberFormat format = new DecimalFormat("#,###,###.###");
				
				while(rs.next())
				{
					_spIds += rs.getString("SanPham_fk") + "__";
					_soluongIds += format.format(rs.getDouble("soluong")) + "__";
					_giatonIds += "-" + "__";
					_soloIds += rs.getString("SoLo") + "__";
				}
				rs.close();
				
				if(_spIds.trim().length() > 0)
				{
					_spIds = _spIds.substring(0, _spIds.length() - 2);
					this.spIds = _spIds.split("__");

					_soluongIds = _soluongIds.substring(0, _soluongIds.length() - 2);
					this.soluongIds = _soluongIds.split("__");
					
					_giatonIds = _giatonIds.substring(0, _giatonIds.length() - 2);
					this.dongiaIds = _giatonIds.split("__");

					_soloIds = _soloIds.substring(0, _soloIds.length() - 2);
					this.soloIds = _soloIds.split("__");
					
					this.soloChonIds = this.soloIds;
				}
			} 
			catch (Exception e) 
			{
				System.out.println("__Exception: " + e.getMessage());
			}
		}
		
		
		if(this.khoId.trim().length() > 0)
		{
			query = "select PK_SEQ, ma + ', ' + TEN as TEN from SANPHAM where LOAISANPHAM_FK = '100005' " +
					"	and pk_seq in ( select sanpham_fk from ERP_KHOTT_SANPHAM where khott_fk = '" + this.khoId + "' ) " +
					"order by MA asc, TEN asc";
			this.spRs = db.getScrol(query);
		}
		//this.createRs();
		
	}
	
	public boolean createBundle()
	{	
		if(this.ngaythuchien.trim().length() <= 0)
		{
			this.msg = "Vui lòng chọn ngày thực hiện";
			return false;
		}
		
		if(this.khoId.trim().length() <= 0)
		{
			this.msg = "Vui lòng chọn kho thực hiện";
			return false;
		}
		
		if(this.soloId.trim().length() <= 0)
		{
			this.msg = "Vui lòng nhập số lô của BUNDLE";
			return false;
		}
		
		if(this.soluong.trim().length() <= 0)
		{
			this.msg = "Vui lòng nhập số lượng của BUNDLE";
			return false;
		}
		
		if(this.spIds == null || this.spIds.length <= 0 )
		{
			this.msg = "Vui lòng kiểm tra lại thông tin sản phẩm gộp thành BUNDLE";
			return false;
		}
		else
		{
			for(int i = 0; i < this.spIds.length; i++)
			{
				if(this.spIds[i].trim().length() > 0)
				{
					if( this.soloIds[i].trim().length() <= 0 || this.soluongIds[i].trim().length() <= 0  )
					{
						this.msg = "Vui lòng kiểm tra lại thông tin số lô, số lượng của sản phẩm tại dòng thứ ( " + (i + 1) + " ) ";
						return false;
					}
					
					//CHECK TON KHO
					
					String query = " select AVAILABLE from ERP_KHOTT_SP_CHITIET " +
								   " where khott_fk = '" + this.khoId + "' and sanpham_fk = '" + this.spIds[i] + "' " +
								   " and solo = '" + this.soloIds[i] + "' and BIN = '100000' ";
					ResultSet rsCheck = db.get(query);
					float AVAI = 0;
					if(rsCheck != null)
					{
						try 
						{
							if(rsCheck.next())
							{
								AVAI = rsCheck.getFloat("AVAILABLE");
							}
							rsCheck.close();
						} 
						catch (Exception e) { }
						
					}
					
					if(AVAI <  Float.parseFloat(this.soluongIds[i]) )
					{
						this.msg = "Tồn kho của sản phẩm ở dòng ( " + ( i + 1 ) + " ) với số lô bạn chọn không đủ để thực hiện BUNDLE ";
						return false;
					}
				}
			}
		}
		
		try 
		{
			db.getConnection().setAutoCommit(false);
			
			String query = "insert ERP_BUNDLE(NGAYTHUCHIEN, KHOTT_FK, SANPHAM_FK, SOLUONG, DIENGIAI, SOLO, CONGTY_FK, TRANGTHAI, NGUOITAO, NGAYTAO, NGUOISUA, NGAYSUA) " +
						   "values('" + this.ngaythuchien + "', '" + this.khoId + "', '" + this.spId + "', '" + this.soluong.replaceAll(",", "") + "', N'" + this.diengiai + "', N'" + this.soloId + "', '100005', '1', '" + this.userId + "', '" +  getDateTime() + "', '" + this.userId + "', '" + getDateTime() + "')";
			
			if(!db.update(query))
			{
				this.msg = "Không thể tạo mới ERP_BUNDLE " + query;
				db.getConnection().rollback();
				return false;
			}
			
			//CAP NHAT KHO CUA SP BUNDLE
			query = "Update ERP_KHOTT_SP_CHITIET set soluong = soluong + '" + this.soluong.replaceAll(",", "") + "', available = available + '" + this.soluong.replaceAll(",", "") + "' " +
					"where khott_fk = '" + this.khoId + "' and sanpham_fk = '" + this.spId + "' and solo = '" + this.soloId + "' ";
			System.out.println("1.Cap nhat kho: " + query);
			if(db.updateReturnInt(query) <= 0)
			{
				query = "insert ERP_KHOTT_SP_CHITIET(KHOTT_FK, SANPHAM_FK, SOLUONG, BOOKED, AVAILABLE, SOLO, NGAYHETHAN, BIN, NGAYSANXUAT, NGAYNHAPKHO)  " +
						"values('" + this.khoId + "', '" + this.spId + "', '" + this.soluong.replaceAll(",", "") + "', '0', '" + this.soluong.replaceAll(",", "") + "', '" + this.soloId + "', '', '100000', '', '" + getDateTime() + "')";
				System.out.println("4.Cap nhat kho: " + query);
				if(!db.update(query))
				{
					this.msg = "33.Không thể cập nhật ERP_KHOTT_SP_CHITIET " + query;
					db.getConnection().rollback();
					return false;
				}
			}
	

		/*	query = "UPDATE ERP_KHOTT_SANPHAM set soluong  = soluong + '" + this.soluong.replaceAll(",", "") + "', AVAILABLE = AVAILABLE + '" + this.soluong.replaceAll(",", "") + "' " +
					"where khott_fk = '" + this.khoId + "' and sanpham_fk = '" + this.spId + "' ";
			System.out.println("2.Cap nhat kho: " + query);
			if(!db.update(query))
			{
				this.msg = "22.Không thể cập nhật ERP_KHOTT_SANPHAM " + query;
				db.getConnection().rollback();
				return false;
			}*/
			
			String spid_= this.spId ;
			String khoit_cn=   this.khoId;
			double soluongct_=0;
			double booked_ct_=0;
			double avai_ct_=0;
			try{ soluongct_ = Double.parseDouble(this.soluong.replaceAll(",", ""));}catch(Exception err){}
			try{ avai_ct_ =  Double.parseDouble(this.soluong.replaceAll(",", ""));}catch(Exception err){}
			
			/*String msg1= Utility_Kho.Update_Kho_Sp_Provence(db, khoit_cn, spid_,  soluongct_,booked_ct_,avai_ct_, 0,this.id,"Erpbundle.java 333");
			
			if(msg1.length() >0 )
			{
				db.getConnection().rollback();
				this.msg= "Lỗi :"+msg1;
				return false;
			}
*/
			
			
			for(int i = 0; i < this.spIds.length; i++)
			{
				if(this.spIds[i].trim().length() > 0 && this.soloIds[i].length() > 0 && this.soluongIds[i].trim().length() > 0 )
				{
					query = "insert ERP_BUNDLE_SANPHAM(Bundle_fk, SanPham_fk, soluong, tonkho, SOLO) " +
							"select IDENT_CURRENT('ERP_BUNDLE'), '" + this.spIds[i] + "', '" + this.soluongIds[i].replaceAll(",", "") + "', '" + dongiaIds[i].replaceAll(",", "") + "', N'" + this.soloIds[i] + "' ";
					if(!db.update(query))
					{
						this.msg = "Không thể tạo mới ERP_BUNDLE_SANPHAM " + query;
						db.getConnection().rollback();
						return false;
					}
					
					//Cap nhat kho
					query = "Update ERP_KHOTT_SP_CHITIET set soluong = soluong - '" + this.soluongIds[i].replaceAll(",", "") + "', available = available - '" + this.soluongIds[i].replaceAll(",", "") + "' " +
							"where khott_fk = '" + this.khoId + "' and sanpham_fk = '" + this.spIds[i] + "' and solo = '" + this.soloIds[i] + "' ";
					System.out.println("4.Cap nhat kho: " + query);
					if(!db.update(query))
					{
						this.msg = "33.Không thể cập nhật ERP_KHOTT_SP_CHITIET " + query;
						db.getConnection().rollback();
						return false;
					}
					
					
					/*query = "UPDATE ERP_KHOTT_SANPHAM set soluong  = soluong - '" + this.soluongIds[i].replaceAll(",", "") + "', AVAILABLE = AVAILABLE - '" + this.soluongIds[i].replaceAll(",", "") + "' " +
							"where khott_fk = '" + this.khoId + "' and sanpham_fk = '" + this.spIds[i] + "' ";
					System.out.println("5.Cap nhat kho: " + query);
					if(!db.update(query))
					{
						this.msg = "44.Không thể cập nhật ERP_KHOTT_SANPHAM " + query;
						db.getConnection().rollback();
						return false;
					}*/
					  spid_= this.spIds[i];
					  khoit_cn=   this.khoId;
					  soluongct_=0;
					  booked_ct_=0;
					  avai_ct_=0;
					try{ soluongct_ = (-1)*Double.parseDouble(this.soluongIds[i].replaceAll(",", ""));}catch(Exception err){}
					try{ avai_ct_ = (-1)* Double.parseDouble(this.soluongIds[i].replaceAll(",", ""));}catch(Exception err){}
					
					/*  msg1= Utility_Kho.Update_Kho_Sp_Provence(db, khoit_cn, spid_,  soluongct_,booked_ct_,avai_ct_, 0,this.id,"Erpbundle.java 333");
					
					if(msg1.length() >0 )
					{
						db.getConnection().rollback();
						this.msg= "Lỗi :"+msg1;
						return false;
					}*/

					
					
				}
			}
			

			db.getConnection().commit();
			db.getConnection().setAutoCommit(true);
		} 
		catch (Exception e) 
		{
			this.msg = "Loi: " + e.getMessage();
			try 
			{
				db.getConnection().rollback();
			} 
			catch (SQLException e1) {}
			return false;
		}
		
		return true;
	}
	
	public boolean updateBundle() 
	{

		return true;
	}
	
	public String getDiengiai()
	{
		return this.diengiai;
	}

	public void setDiengiai(String diengiai)
	{
		this.diengiai = diengiai;
	}

	
	public void DbClose() 
	{
		try 
		{
			this.db.shutDown();
		} 
		catch (Exception e) {}
		
	}
	
	private String getDateTime()
	{
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date date = new Date();
        return dateFormat.format(date);	
	}

	
	public ResultSet getSpRs() {
		
		return this.spRs;
	}

	
	public void setSpRs(ResultSet spRs) {
		
		this.spRs = spRs;
	}

	
	public String getSpId() {
		
		return this.spId;
	}

	
	public void setSpId(String spId) {
		
		this.spId = spId;
	}

	
	public String getSoluong() {
		
		return this.soluong;
	}

	
	public void setSoluong(String soluong) {
		
		this.soluong = soluong;
	}

	
	public String getDongia() {
		
		return this.dongia;
	}

	
	public void setDongia(String dongia) {
		
		this.dongia = dongia;
	}

	
	public String[] getSpIds() {
		
		return this.spIds;
	}

	
	public void setSpIds(String[] spIds) {
		
		this.spIds = spIds;
	}

	
	public String[] getSoluongIds() {
		
		return this.soluongIds;
	}

	
	public void setSoluongIds(String[] soluongIds) {
		
		this.soluongIds = soluongIds;
	}

	
	public String[] getDongiaIds() {
		
		return this.dongiaIds;
	}

	
	public void setDongiaIds(String[] dongiaIds) {
		
		this.dongiaIds = dongiaIds;
	}


	public void createRs() 
	{
		String query = " select PK_SEQ,  TEN from Kho where trangthai = 1 ";
		this.khottRs = db.get(query);
		
		query = " select PK_SEQ,  TEN from Nhaphanphoi where trangthai = 1 and pk_seq !=1 and isnull(isKHACHHANG,0) = 0 ";
		this.nppRs = db.get(query);
		
		query = " select PK_SEQ,  TEN from KenhBanHang where trangthai = 1 ";
		this.kbhRs = db.get(query);
		
		this.soloChonIds = new String[]{"", "", "", "", ""};
		if(this.khoId.trim().length() > 0 && this.nppDatId.trim().length() > 0&& this.kbhId.trim().length() > 0)
		{
			query = " select PK_SEQ, ma + ', ' + TEN as TEN from SANPHAM where  " +
					" pk_seq in ( select sanpham_fk from Nhapp_kho where kho_fk = '" + this.khoId + "' and NPP_FK ="+this.nppDatId+" and KBH_FK ="+this.kbhId+" ) " +
					" order by MA asc, TEN asc";
			this.spRs = db.getScrol(query);
			
			NumberFormat format = new DecimalFormat("#,###,###.###");
			
			if( this.spIds != null && this.spIds.length > 0 )
			{
				System.out.println("---SO SAN PHAM: " + this.spIds.length);
				this.soloChonIds = new String[this.spIds.length];
				for(int i = 0; i < this.spIds.length; i++ )
				{
					//Lay so lo + ton kho
					if(this.spIds[i].trim().length() > 0)
					{
						query = " Select distinct ( Select cast(khoCT1.SOLO as varchar(10)) + '__' AS [text()] " +
								" From NHAPP_KHO_CHITIET khoCT1   " +
								" Where KHO_FK = khoCT.KHO_FK and SANPHAM_FK = khoCT.SANPHAM_FK and KBH_FK = khoCT.KBH_FK and NPP_FK = khoCT.NPP_FK " +
								" ORDER BY khoCT1.NGAYHETHAN asc " +
								" For XML PATH ('')) as SoLo  " +
								" From NHAPP_KHO_CHITIET khoCT  " +
								"where KHO_FK = '" + this.khoId + "' and SANPHAM_FK = '" + this.spIds[i] + "'  and  NPP_FK = '"+this.nppDatId+"' and KBH_FK ="+this.kbhId;
						
						System.out.println("___LAY LO: " + query);
						ResultSet loRs = db.get(query);
						try 
						{
							if(loRs.next())
							{
								String SL = loRs.getString("SoLo") == null ? "" : loRs.getString("SoLo");
								if(SL.trim().length() > 0 )
								{
									this.soloChonIds[i] = SL.substring(0, SL.length() - 2);
								}
								else
								{
									this.soloChonIds[i] = "";
								}
							}
							else
							{
								this.soloChonIds[i] = "";
							}
							
							System.out.println("----SO LO LAY DUOC (JAVA): " + this.soloIds[i]);
							
							loRs.close();
						} 
						catch (Exception e) {
							System.out.println("EXCEPTION SOLO: " + e.getMessage());
							this.soloChonIds[i] = "";
						}
					}
					else
					{
						this.soloChonIds[i] = "";
					}
					
					
					if(this.soloIds[i].trim().length() > 0 )
					{
						query = "select AVAILABLE from ERP_KHOTT_SP_CHITIET " +
								"where khott_fk = '" + this.khoId + "' and sanpham_fk = '" + this.spIds[i] + "' and SoLo = '" + this.soloIds[i] + "' and BIN = '100000' ";
						ResultSet rsAVAI = db.get(query);
						try 
						{
							if(rsAVAI.next())
							{
								this.dongiaIds[i] = format.format(rsAVAI.getDouble("AVAILABLE"));
							}
							rsAVAI.close();
						} 
						catch (Exception e) {
							this.avai = "0";
						}
					}
				}
			}
		}
		
	}

	

	
	public String[] getSpMaIds() {
		
		return this.spMaIds;
	}

	
	public void setSpMaIds(String[] spIds) {
		
		this.spMaIds = spIds;
	}

	
	public String[] getSpTenIds() {
		
		return this.spTenIds;
	}

	
	public void setSpTenIds(String[] spIds) {
		
		this.spTenIds = spIds;
	}

	
	public ResultSet getSoloRs() {
		
		return this.soloRs;
	}

	
	public void setSoloRs(ResultSet loRs) {
		
		this.soloRs = loRs;
	}

	
	public String getSoloId() {
		
		return this.soloId;
	}

	
	public void setSoloId(String soloId) {
		
		this.soloId = soloId;
	}

	
	public String[] getThanhtienIds() {
		
		return this.thanhtienIds;
	}

	
	public void setThanhtienIds(String[] thanhtienIds) {
		
		this.thanhtienIds = thanhtienIds;
	}

	
	public String[] getSoloIds() {
		
		return this.soloIds;
	}

	
	public void setSoloIds(String[] soloIds) {
		
		this.soloIds = soloIds;
	}

	public String getNgaythuchien() {

		return this.ngaythuchien;
	}

	public void setNgaythuchien(String ngay) {
		
		this.ngaythuchien = ngay;
	}

	public String getThanhtien() {

		return this.thanhtien;
	}

	public void setThanhtien(String thanhtien) {
		
		this.thanhtien = thanhtien;
	}

	
	public ResultSet getKhoTTRs() {
		
		return this.khottRs;
	}

	
	public void setKhoTTRs(ResultSet khottRs) {
		
		this.khottRs = khottRs;
	}

	
	public String getKhoTTId() {
		
		return this.khoId;
	}

	
	public void setKhoTTId(String khottId) {
		
		this.khoId = khottId;
	}

	public String getAvai() {
		
		return this.avai;
	}

	public void setAvai(String avai) {
		
		this.avai = avai;
	}


	public String[] getSoloChonIds() {

		return this.soloChonIds;
	}


	public void setSoloChonIds(String[] soloChonIds) {
	
		this.soloChonIds = soloChonIds;
	}

	public String getNppDatId() {
		return nppDatId;
	}
	public void setNppDatId(String nppDatId) {
		this.nppDatId = nppDatId;
	}
	public ResultSet getNppRs() {
		return nppRs;
	}
	
	public String getKbhId() {
		return kbhId;
	}
	public void setKbhId(String kbhId) {
		this.kbhId = kbhId;
	}
	public ResultSet getKbhRs() {
		return kbhRs;
	}
}
