package geso.dms.erp.beans.lenhsanxuat.imp;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import geso.dms.center.util.Utility_Kho;
import geso.dms.erp.db.sql.dbutils;
import geso.dms.erp.beans.lenhsanxuat.IErpRacoloc;

public class ErpRacoloc implements IErpRacoloc
{
	String userId;
	String congtyId;
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
	
	ResultSet bomRs;
	String bomId;
	
	String 	 tensp  ;
	String khottTen ;
	
	
	ResultSet soloRs;
	String soloId;
	
	String[] spIds;
	String[] spMaIds;
	String[] spTenIds;
	String[] soluongIds;
	String[] dongiaIds;
	String[] thanhtienIds;
	String[] soloIds;
	String[] ngaysxIds;
	String[] ngayHetHanIds;
	
	String msg;
	
	dbutils db;
	
	public ErpRacoloc()
	{
		this.userId = "";
		this.id = "";
		this.diengiai = "";
		this.spId = "";
		this.soluong = "";
		this.dongia = "";
		this.thanhtien = "";
		this.msg = "";
		this.bomId = "";
		this.soloId = "";
		this.khoId = "";
		this.ngaythuchien = "";
		this.avai = "";
		this.db = new dbutils();
	}
	
	public ErpRacoloc(String id)
	{
		this.id = id;
		this.userId = "";
		this.diengiai = "";
		this.spId = "";
		this.soluong = "";
		this.dongia = "";
		this.thanhtien = "";
		this.msg = "";
		this.bomId = "";
		this.soloId = "";
		this.khoId = "";
		this.ngaythuchien = "";
		this.avai = "";
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
		String query = "select KHOTT_FK, ngaythuchien, diengiai, sanpham_fk, soluong, dongia, SOLO, BOM_FK from Erp_RACOLOC " +
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
					this.dongia = rs.getString("dongia");
					this.bomId = rs.getString("BOM_FK");
					this.soloId = rs.getString("SOLO");
				}
				rs.close();
			} 
			catch (Exception e) 
			{
				System.out.println("__Exception: " + e.getMessage());
			}
		}
		
		this.createRs();
		
	}

	public void initPdf() 
	{
		String query = 
			" select rcl.KHOTT_FK, rcl.ngaythuchien, rcl.diengiai, rcl.sanpham_fk, rcl.soluong,  " +
			" rcl.dongia, rcl.SOLO, rcl.BOM_FK, isnull(sp.ma, '') + ', ' + isnull(sp.ten, '') as spTen, isnull(khott.ten, '') as khottTen  " +
			" from Erp_RACOLOC rcl " +
			" inner join sanpham sp on sp.pk_seq = rcl.sanpham_fk " +
			" inner join erp_khott khott on khott.pk_seq = rcl.KHOTT_FK " +
			" where rcl.PK_SEQ = '" + this.id + "'";
		ResultSet rs = db.get(query);
		if(rs != null)
		{
			try 
			{
				while(rs.next())
				{
					this.khoId = rs.getString("KHOTT_FK");
					this.tensp=rs.getString("spTen");
					this.khottTen=rs.getString("khottTen");
					
					this.ngaythuchien = rs.getString("ngaythuchien");
					this.diengiai = rs.getString("diengiai");
					this.spId = rs.getString("sanpham_fk");
					this.soluong = rs.getString("soluong");
					this.dongia = rs.getString("dongia");
					this.bomId = rs.getString("BOM_FK");
					this.soloId = rs.getString("SOLO");
				}
				rs.close();
			} 
			catch (Exception e) 
			{
				System.out.println("[ErpRacoloc.initPdf] Exception message: " + e.getMessage());
			}
		}

		this.createPdfRs();
	}
	
	public boolean createRacoloc()
	{	
		System.out.println("solo"+this.soloId);
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
		
		if(this.spId.trim().length() <= 0)
		{
			this.msg = "Vui lòng chọn sản phẩm thực hiện";
			return false;
		}
		
		if(this.bomId.trim().length() <= 0)
		{
			this.msg = "Vui lòng kiểm tra lại thông tin BOM của sản phẩm";
			return false;
		}
		
		if(this.soloId.trim().length() <= 0)
		{
			this.msg = "Vui lòng kiểm tra lại thông tin SOLO của sản phẩm";
			return false;
		}
		
		if(this.spIds == null || this.spIds.length <= 0 )
		{
			this.msg = "Vui lòng kiểm tra lại thông tin vật tư sau khi rã COLOC";
			return false;
		}
		
		try 
		{
			//CHECK TON KHO
			String khotam ="";
			if(this.khoId.equals("100000"))
			{
				khotam = "100014";
			}
			else if (this.khoId.equals("100001"))
			{
				khotam = "100017";
			}
			
			String query = " select AVAILABLE from ERP_KHOTT_SP_CHITIET " +
						   " where khott_fk = '" + this.khoId + "' and sanpham_fk = '" + this.spId + "' and solo = '" + this.soloId  + "' ";
			System.out.println("Cau query "+query);
			ResultSet rsCheck = db.get(query);
			float AVAI = 0;
			 
				if(rsCheck.next())
				{
					AVAI = rsCheck.getFloat("AVAILABLE");
				}
				rsCheck.close();
			 
			
			if(AVAI <  Float.parseFloat(this.soluong.replaceAll(",", "")) )
			{
				this.msg = "Tồn kho của sản phẩm với số lô bạn chọn không đủ để thực hiện rã COLOC ";
				return false;
			}
			
			 query = " select AVAILABLE,BOOKED from ERP_KHOTT_SANPHAM " +
			   " where khott_fk = '" + this.khoId + "' and sanpham_fk = '" + this.spId + "'";
			  rsCheck = db.get(query);
			  AVAI = 0;
			 float BOOKED=0;
				if(rsCheck.next())
				{
					AVAI = rsCheck.getFloat("AVAILABLE");
					BOOKED=rsCheck.getFloat("BOOKED");
				}
				rsCheck.close();
				
				if(AVAI <  Float.parseFloat(this.soluong.replaceAll(",", "")))
				{
					this.msg = "Tồn kho tổng của sản phẩm không còn đủ số lượng để rã COLOC,số lượng đang BOOKED của sản phẩm này là :"+ BOOKED;
					return false;
				}
			 
			db.getConnection().setAutoCommit(false);
			this.soloId=this.soloId.trim();
			// THÊM KHO TẠM LÀ KHO SẼ TĂNG TẠM THỜI ĐỐI VỚI CÁC SẢN PHẨM CHỜ KIỂM ĐỊNH
			query = " insert ERP_RACOLOC(NGAYTHUCHIEN, KHOTT_FK, SANPHAM_FK, SOLUONG, DONGIA, DIENGIAI, SOLO, BOM_FK, CONGTY_FK, TRANGTHAI, NGUOITAO, NGAYTAO, NGUOISUA, NGAYSUA,KHOTAM_FK) " +
					" values('" + this.ngaythuchien + "', '" + this.khoId + "', '" + this.spId + "', '" + this.soluong.replaceAll(",", "") + "', '" + this.dongia.replaceAll(",", "") +  "', N'" + this.diengiai + "', N'" + this.soloId + "', '" + this.bomId + "', '100005', '1', '" + this.userId + "', '" +  getDateTime() + "', '" + this.userId + "', '" + getDateTime() + "',"+khotam+")";
			
			if(!db.update(query))
			{
				this.msg = "Không thể tạo mới ERP_RACOLOC " + query;
				db.getConnection().rollback();
				return false;
			}
			
			//lay racolocid
			String rcId = "";
			query = "SELECT IDENT_CURRENT('ERP_RACOLOC') AS RCID";
			ResultSet rs1 = db.get(query);
			rs1.next();
			rcId = rs1.getString("RCID");
			System.out.println("rcId = " + rcId);
			
			
			//CAP NHAT KHO RA
			query = "Update ERP_KHOTT_SP_CHITIET set soluong = soluong - '" + this.soluong.replaceAll(",", "") + "', available = available - '" + this.soluong.replaceAll(",", "") + "' " +
					"where khott_fk = '" + this.khoId + "' and sanpham_fk = '" + this.spId + "' and solo = '" + this.soloId + "' ";
			//System.out.println("1.Cap nhat kho: " + query);
			if(!db.update(query))
			{
				this.msg = "11.Không thể cập nhật ERP_KHOTT_SP_CHITIET " + query;
				db.getConnection().rollback();
				return false;
			}
	

			/*query = "UPDATE ERP_KHOTT_SANPHAM set soluong  = soluong - '" + this.soluong.replaceAll(",", "") + "', AVAILABLE = AVAILABLE - '" + this.soluong.replaceAll(",", "") + "' " +
					"where khott_fk = '" + this.khoId + "' and sanpham_fk = '" + this.spId + "' ";
			//System.out.println("2.Cap nhat kho: " + query);
			if(!db.update(query))
			{
				this.msg = "22.Không thể cập nhật ERP_KHOTT_SANPHAM " + query;
				db.getConnection().rollback();
				return false;
			}*/
			String spid_= this.spId;
			String khoit_cn=  this.khoId;
			double soluongct_=0;
			double booked_ct_=0;
			double avai_ct_=0;
			try{ soluongct_ = (-1)*Double.parseDouble(this.soluong.replaceAll(",", ""));}catch(Exception err){}
			try{ avai_ct_ =(-1)* Double.parseDouble(this.soluong.replaceAll(",", ""));}catch(Exception err){}
			
			String msg1= Utility_Kho.Update_Kho_Sp_Provence(db, khoit_cn, spid_,  soluongct_,booked_ct_,avai_ct_, 0,this.id,"erpRacoloc.java 335");
			
			if(msg1.length() >0 )
			{
				db.getConnection().rollback();
				this.msg= "Lỗi :"+msg1;
				return false;
			}

			
			
			
			
			//Tao Yeu cau kiem dinh
			query= " insert into ERP_YeuCauKiemDinh (trangthai , ngaytao , nguoisua, ngaysua , ngaykiem , CongTy_FK , soluongchokiem, soluongkiemdinh , soluongchophep , soluongdat , dinhluong , dinhtinh , datchatluong, PREFIX , RACOLOC_FK ) " +
					" values ( '0', '" + getDateTime() + "', '" + this.userId + "', '" + getDateTime() + "', '" + getDateTime() + "', '" + this.congtyId + "', '0','0','0','0','0','0','0' , '200', '"+ rcId +"'  )";
			if(!db.update(query))
			{
				this.msg = "Không thể cập nhật ERP_YeuCauKiemDinh " + query;
				db.getConnection().rollback();
				return false;
			}
			
			//Lấy id phiếu yêu cầu
			String ycId = "";
			query = "SELECT IDENT_CURRENT('ERP_YEUCAUKIEMDINH') AS YCID";
			ResultSet rs2 = db.get(query);
			rs2.next();
			ycId = rs2.getString("YCID");
			System.out.println("ycId = " + ycId);
			
			String loaisanpham = "";
			double soluongchokiem = 0;
			
			for(int i = 0; i < this.spIds.length; i++)
			{
			   if(this.spIds[i].trim().length() > 0)
			   {
					this.soloIds[i]=this.soloIds[i].trim();
					if(this.soloIds[i].trim().length() <= 0 || this.dongiaIds[i].trim().length() <= 0 || this.soluongIds[i].trim().length() <= 0 )
					{
						db.getConnection().rollback();
						this.msg = "Vui lòng kiểm tra lại thông tin số lô, số lượng, đơn giá của sản phẩm (" + this.spTenIds[i] + ") sau khi rã";
						return false;
					}
					
					query = " insert ERP_RACOLOC_SANPHAM(RaColoc_fk, SanPham_fk, soluong, dongia, SOLO, NGAYSANXUAT, NGAYHETHAN) \n" +
							" select IDENT_CURRENT('ERP_RACOLOC'), '" + this.spIds[i] + "', '" + this.soluongIds[i].replaceAll(",", "") + "' \n" +
							", '" + dongiaIds[i].replaceAll(",", "") + "', N'" + this.soloIds[i] + "','" + this.ngaysxIds[i] + "','" + this.ngayHetHanIds[i] + "' \n";
					if(!db.update(query))
					{
						this.msg = "Không thể tạo mới ERP_RACOLOC_SANPHAM " + query;
						db.getConnection().rollback();
						return false;
					}
					
					//Cap nhat kho (bao bi khong tang kho)
					query = "select loaisanpham_fk from SANPHAM where pk_seq = '" + this.spIds[i] + "'";
					String loaisanpham_fk = "";
					ResultSet rsLsp = db.get(query);
					if(rsLsp.next())
					{
						loaisanpham_fk = rsLsp.getString("loaisanpham_fk");
					}
					rsLsp.close();
					
					if(!loaisanpham_fk.equals("100003"))  //100003 -- BAO BI
					{				
						loaisanpham = loaisanpham_fk;
					
						if(soluongchokiem < Double.parseDouble(this.soluongIds[i].replaceAll(",", "")) )
						{	
							soluongchokiem = Double.parseDouble(this.soluongIds[i].replaceAll(",", ""));
						} 
						
						query = " Update ERP_KHOTT_SP_CHITIET set soluong = soluong + '" + this.soluongIds[i].replaceAll(",", "") + "', available = available + '" + this.soluongIds[i].replaceAll(",", "") + "' " +
								" where khott_fk = '" + khotam + "' and sanpham_fk = '" + this.spIds[i] + "' and solo = '" + this.soloIds[i] + "' ";
						//System.out.println("3.Cap nhat kho: " + query);
						if(db.updateReturnInt(query) <= 0)  //CHUA CO THI INSERT VO
						{
							query = " insert ERP_KHOTT_SP_CHITIET(KHOTT_FK, SANPHAM_FK, SOLUONG, BOOKED, AVAILABLE, SOLO, NGAYHETHAN, BIN, NGAYSANXUAT, NGAYNHAPKHO)  \n" +
									" values('" + khotam + "', '" + this.spIds[i] + "', '" + this.soluongIds[i].replaceAll(",", "") + "', '0' \n" +
									", '" + this.soluongIds[i].replaceAll(",", "") + "', '" + this.soloIds[i] + "', '"+ this.ngayHetHanIds[i] +"', '100000', '"+ this.ngaysxIds[i] +"', '" + getDateTime() + "')";
							//System.out.println("4.Cap nhat kho: " + query);
							if(!db.update(query))
							{
								this.msg = "33.Không thể cập nhật ERP_KHOTT_SP_CHITIET " + query;
								db.getConnection().rollback();
								return false;
							}
						}
						
						/*query = "UPDATE ERP_KHOTT_SANPHAM set soluong  = soluong + '" + this.soluongIds[i].replaceAll(",", "") + "', AVAILABLE = AVAILABLE + '" + this.soluongIds[i].replaceAll(",", "") + "' " +
								"where khott_fk = '" + khotam + "' and sanpham_fk = '" + this.spIds[i] + "' ";
						//System.out.println("5.Cap nhat kho: " + query);
						if(!db.update(query))
						{
							this.msg = "44.Không thể cập nhật ERP_KHOTT_SANPHAM " + query;
							db.getConnection().rollback();
							return false;
						}*/
						
						  spid_= this.spIds[i];
						  khoit_cn=  khotam;
						  soluongct_=0;
						  booked_ct_=0;
						  avai_ct_=0;
						try{ soluongct_ = Double.parseDouble(this.soluongIds[i].replaceAll(",", ""));}catch(Exception err){}
						try{ avai_ct_ =  Double.parseDouble(this.soluongIds[i].replaceAll(",", ""));}catch(Exception err){}
						
						  msg1= Utility_Kho.Update_Kho_Sp_Provence(db, khoit_cn, spid_,  soluongct_,booked_ct_,avai_ct_, 0,this.id,"erpRacoloc.java 445");
						
						if(msg1.length() >0 )
						{
							db.getConnection().rollback();
							this.msg= "Lỗi :"+msg1;
							return false;
						}
						
						
						// them vao ERP_YEUCAUKIEMDINH_SANPHAM
						query = " INSERT INTO ERP_YEUCAUKIEMDINH_SANPHAM(YCKD_FK, SANPHAM_FK, SOLO, SOLUONG,SOLUONGDAT, ngaySanXuat, ngayHetHan) \n" +
						" values( '" + ycId + "', '" + this.spIds[i] + "', '" + this.soloIds[i] + "', '" + this.soluongIds[i].replaceAll(",","") + "' \n" +
						", '"+this.soluongIds[i].replaceAll(",","")+ "', '" + this.ngaysxIds[i] + "', '" + this.ngayHetHanIds[i] + "')";
						if(!db.update(query))
						{
							this.msg = "Không thể cập nhật ERP_YEUCAUKIEMDINH_SANPHAM " + query;
							db.getConnection().rollback();
							return false;
						}
					}
				
			   }
			}
			
			//update loaisanpham va soluongchokiem cho ERP_YeuCauKiemDinh
			query = " UPDATE ERP_YeuCauKiemDinh set loaisanpham_fk = '"+ loaisanpham + "', " +
					" soluongchokiem = (select SOLUONGKIEM from ERP_AQL where TUMUC <= "+ soluongchokiem +" and "+ soluongchokiem+ " <= DENMUC and loaisanpham_fk ='"+ loaisanpham +"'  ) " +
					" where pk_seq = '"+ ycId +"'";
			if(!db.update(query))
			{
				this.msg = "2.Không thể cập nhật ERP_YeuCauKiemDinh " + query;
				db.getConnection().rollback();
				return false;
			}			

			db.getConnection().commit();
			db.getConnection().setAutoCommit(true);
		} 
		catch (Exception e) 
		{
			e.printStackTrace();
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
	
	public boolean updateRacoloc() 
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
			if(spRs!=null) { spRs.close(); }
			if(khottRs!=null) { khottRs.close(); }
			if(bomRs!=null) { bomRs.close(); }
			if(soloRs!=null) { soloRs.close(); }
			
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

	public String getCongtyId() 
	{
		return this.congtyId;
	}

	public void setCongtyId(String congtyId) 
	{
		this.congtyId = congtyId;
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
		String query = "select PK_SEQ, MA + ', ' + TEN as TEN from ERP_KHOTT where LOAI = '5' ";
		this.khottRs = db.get(query);
		
		if(this.khoId.trim().length() > 0)
		{
			query = "   select PK_SEQ, ma + ', ' + TEN as TEN from SANPHAM where LOAISANPHAM_FK = '100005' " +
					"	and pk_seq in ( select sanpham_fk from ERP_KHOTT_SANPHAM where khott_fk = '" + this.khoId + "' ) " +
					"order by MA asc, TEN asc";
			this.spRs = db.getScrol(query);
			System.out.println("Kho "+query);
			
			NumberFormat format = new DecimalFormat("#,###,###.###");
			
			if(this.spId.trim().length() > 0)
			{
				//Lay don gia + thanh tien cua SP
				this.dongia = "0";
				this.thanhtien = "0";
				
				query = "select PK_SEQ, DIENGIAI + ' [ ' + HIEULUCTU + '-' + HIEULUCDEN + ' ]' as diengiai " +
						"from ERP_DANHMUCVATTU where SANPHAM_FK = '" + this.spId + "'";
				
				this.bomRs = db.get(query);
				
				//Lay so lo
				query = " select distinct SOLO from ERP_KHOTT_SP_CHITIET where SANPHAM_FK = '" + this.spId + "' and khott_fk = '" + this.khoId + "' ";
				System.out.println("___LAY LO: " + query);
				this.soloRs = db.get(query);
				String solo = "";
				int dem=0;
				
				ResultSet RsSL = db.get(query);
				try 
				{
					while(RsSL.next())
					{
						solo += RsSL.getString("SOLO");
						dem = dem + 1;;
						
					}
					RsSL.close();
				} 
				catch (Exception e) {
					solo = "";
				}
				
				if(dem ==1) this.soloId= solo;
				System.out.println("DEM "+dem);
				if(this.soloId.trim().length() > 0 )
				{
					query = "select AVAILABLE from ERP_KHOTT_SP_CHITIET " +
							"where khott_fk = '" + this.khoId + "' and sanpham_fk = '" + this.spId + "' and SOLO = '" + this.soloId.trim() + "' ";
					System.out.println("____CHECK AVAI: " + query );
					
					ResultSet rsAVAI = db.get(query);
					try 
					{
						if(rsAVAI.next())
						{
							this.avai = format.format(rsAVAI.getDouble("AVAILABLE"));
						}
						rsAVAI.close();
					} 
					catch (Exception e) {
						this.avai = "0";
					}
				}
				
				if(this.id.trim().length() > 0)
				{
					
					query = "select b.pk_seq, b.MA, b.TEN, a.SOLUONG as SoLuong, \n" +
								"isnull( a.DONGIA, '0' ) as GiaTon, \n" +
								"isnull(a.SOLO, ' ') as SoLo, isnull(a.NGAYSANXUAT,' ') as NGAYSANXUAT   \n" +
								", isnull(a.NGAYHETHAN,'') as NGAYHETHAN \n" +
							"from ERP_RACOLOC_SANPHAM a inner join SANPHAM b on a.SANPHAM_FK = b.PK_SEQ \n" +
							"where a.RACOLOC_FK = '" + this.id + "' \n";
					
					System.out.println("____LAY BOM: " + query);
					ResultSet rs = db.get(query);
					if(rs != null)
					{
						try 
						{
							String _spIds = "";
							String _spTenIds = "";
							String _spMaIds = "";
							String _soluongIds = "";
							String _giatonIds = "";
							String _thanhtienIds = "";
							String _soloIds = "";
							String _ngaysxIds = "";
							String _ngayHetHanIds = "";
							
							while(rs.next())
							{
								_spIds += rs.getString("pk_seq") + "__";
								_spTenIds += rs.getString("TEN") + "__";
								_spMaIds += rs.getString("MA") + "__";
								_soluongIds += format.format(rs.getDouble("SoLuong")) + "__";
								_giatonIds += format.format(rs.getDouble("GiaTon")) + "__";
								_thanhtienIds +=  format.format( ( rs.getDouble("GiaTon") * rs.getDouble("SoLuong") ) ) + "__";
								_soloIds += rs.getString("SoLo") + "__";
								_ngaysxIds +=  rs.getString("NGAYSANXUAT") + "__";
								_ngayHetHanIds +=  (rs.getString("NGAYHETHAN").length()==0?"NA":rs.getString("NGAYHETHAN")) + "__";
							}
							rs.close();
							
							
							if(_spIds.trim().length() > 0)
							{
								_spIds = _spIds.substring(0, _spIds.length() - 2);
								this.spIds = _spIds.split("__");
								
								_spTenIds = _spTenIds.substring(0, _spTenIds.length() - 2);
								this.spTenIds = _spTenIds.split("__");
								
								_spMaIds = _spMaIds.substring(0, _spMaIds.length() - 2);
								this.spMaIds = _spMaIds.split("__");
								
								_soluongIds = _soluongIds.substring(0, _soluongIds.length() - 2);
								this.soluongIds = _soluongIds.split("__");
								
								_giatonIds = _giatonIds.substring(0, _giatonIds.length() - 2);
								this.dongiaIds = _giatonIds.split("__");
								
								_thanhtienIds = _thanhtienIds.substring(0, _thanhtienIds.length() - 2);
								this.thanhtienIds = _thanhtienIds.split("__");
	
								_soloIds = _soloIds.substring(0, _soloIds.length() - 2);
								this.soloIds = _soloIds.split("__");
								
								_ngaysxIds = _ngaysxIds.substring(0, _ngaysxIds.length() - 2);
								_ngayHetHanIds = _ngayHetHanIds.substring(0, _ngayHetHanIds.length() - 2);
								this.ngaysxIds = _ngaysxIds.split("__");
								this.ngayHetHanIds = _ngayHetHanIds.split("__");
								for(int i=0;i<this.ngayHetHanIds.length;i++){
									if(this.ngayHetHanIds[i].equals("NA")){
										this.ngayHetHanIds[i]="";
									}
								}
							}
						} 
						catch (Exception e) 
						{
							e.printStackTrace();
							System.out.println("____Exception COLOC: " + e.getMessage());
						}
					}				
					
				
				}
				else
				{
					if( this.bomId.trim().length() > 0 && this.soluong.trim().length() > 0 && this.soloId.trim().length() > 0 && this.ngaythuchien.trim().length() > 0 )
					{
						//CHECK THANG KHOA SO CO HOP LE HAY KHONG ( CHI DUOC CHOT SAU THANG KHOA SO + 1 )
						query = "select THANGKS, NAM from ERP_KHOASOKETOAN order by NAM desc, THANGKS desc";
						String thangKS = "1";
						String namKS = "2013";
						ResultSet rsCheck = db.get(query);
						if(rsCheck != null)
						{
							try 
							{
								if(rsCheck.next())
								{
									thangKS = rsCheck.getString("THANGKS");
									namKS = rsCheck.getString("NAM");
								}
								rsCheck.close();
							} 
							catch (Exception e) {}
						}
						
						query = 	" select c.pk_seq, c.MA, c.TEN, CAST( b.SOLUONG * " + this.soluong + " / a.SOLUONGCHUAN as numeric(18, 3)) as SoLuong, " +
									" isnull( ( select AVG(giaton) from ERP_TONKHOTHANG where THANG = '" + thangKS + "' and NAM = '" + namKS + "' and SANPHAM_FK = c.PK_SEQ ), '0' ) as GiaTon, " +
									" isnull(RACOLOC.SOLO, ' ') as SoLo, isnull(RACOLOC.NGAYSANXUAT,'"+this.ngaythuchien+"') as NGAYSANXUAT , " +
									" convert(char(10), DATEADD(DAY,ISNULL(HANSUDUNG,0), ('"+this.ngaythuchien+"')),126)    as NGAYHETHAN  " +
									" from ERP_DANHMUCVATTU a inner join ERP_DANHMUCVATTU_VATTU b on a.PK_SEQ = b.DANHMUCVT_FK " +
									" inner join SANPHAM c on b.VATTU_FK = c.PK_SEQ " +
									" left join  " +
									" ( " +
									" select SanPham_fk, SOLO, isnull(NGAYSANXUAT,' ') as NGAYSANXUAT,isnull(NGAYHETHAN,' ') as NGAYHETHAN from ERP_RACOLOC_SANPHAM where RaColoc_fk = '" + ( this.id.trim().length() <= 0 ? "-1" : this.id ) + "' " +
									" ) " +
									" RACOLOC on c.PK_SEQ = RACOLOC.SanPham_fk " +
									" where a.PK_SEQ = '" + this.bomId + "'";
						
						//System.out.println("____LAY BOM: " + query);
						ResultSet rs = db.get(query);
					 
							try 
							{
								String _spIds = "";
								String _spTenIds = "";
								String _spMaIds = "";
								String _soluongIds = "";
								String _giatonIds = "";
								String _thanhtienIds = "";
								String _soloIds = "";
								String _ngaysxIds = "";
								String _ngayHetHanIds = "";
								
								while(rs.next())
								{
									_spIds += rs.getString("pk_seq") + "__";
									_spTenIds += rs.getString("TEN") + "__";
									_spMaIds += rs.getString("MA") + "__";
									_soluongIds += format.format(rs.getDouble("SoLuong")) + "__";
									_giatonIds += format.format(rs.getDouble("GiaTon")) + "__";
									_thanhtienIds +=  format.format( ( rs.getDouble("GiaTon") * rs.getDouble("SoLuong") ) ) + "__";
									_soloIds += rs.getString("SoLo") + "__";
									_ngaysxIds +=  rs.getString("NGAYSANXUAT") + "__";
									_ngayHetHanIds +=  (rs.getString("NGAYHETHAN").length()==0?"NA":rs.getString("NGAYHETHAN")) + "__";
								}
								rs.close();
								
								//System.out.println("SP: " + _spIds + "  -- Ten: " + _spTenIds + " -- Ma: " + _spMaIds);
								//System.out.println("SoLuong: " + _soluongIds + "  -- Thanh Tien: " + _thanhtienIds + " -- SoLo: " + _soloIds);
								if(_spIds.trim().length() > 0)
								{
									_spIds = _spIds.substring(0, _spIds.length() - 2);
									this.spIds = _spIds.split("__");
									
									_spTenIds = _spTenIds.substring(0, _spTenIds.length() - 2);
									this.spTenIds = _spTenIds.split("__");
									
									_spMaIds = _spMaIds.substring(0, _spMaIds.length() - 2);
									this.spMaIds = _spMaIds.split("__");
									
									_soluongIds = _soluongIds.substring(0, _soluongIds.length() - 2);
									this.soluongIds = _soluongIds.split("__");
									
									_giatonIds = _giatonIds.substring(0, _giatonIds.length() - 2);
									this.dongiaIds = _giatonIds.split("__");
									
									_thanhtienIds = _thanhtienIds.substring(0, _thanhtienIds.length() - 2);
									this.thanhtienIds = _thanhtienIds.split("__");
		
									_soloIds = _soloIds.substring(0, _soloIds.length() - 2);
									this.soloIds = _soloIds.split("__");
									
									_ngaysxIds = _ngaysxIds.substring(0, _ngaysxIds.length() - 2);
									this.ngaysxIds = _ngaysxIds.split("__");
									
									_ngayHetHanIds = _ngayHetHanIds.substring(0, _ngayHetHanIds.length() - 2);
									this.ngayHetHanIds = _ngayHetHanIds.split("__");
									for(int i=0;i<this.ngayHetHanIds.length;i++){
										if(this.ngayHetHanIds[i].equals("NA")){
											this.ngayHetHanIds[i]="";
										}
									}
									System.out.println("LENG:G "+this.ngayHetHanIds.length);
								}
							} 
							catch (Exception e) 
							{
								e.printStackTrace();
								System.out.println("____Exception COLOC: " + e.getMessage());
							}
						 
					}
				}
				
				
				
				
			}
		}
		
	}
	
	private void createPdfRs() 
	{
		String query = "";
		if(this.khoId.trim().length() > 0)
		{
			query = "   select PK_SEQ, ma + ', ' + TEN as TEN from SANPHAM where LOAISANPHAM_FK = '100005' " +
					"	and pk_seq in ( select sanpham_fk from ERP_KHOTT_SANPHAM where khott_fk = '" + this.khoId + "' ) " +
					"order by MA asc, TEN asc";
			this.spRs = db.getScrol(query);
			
			NumberFormat format = new DecimalFormat("#,###,###.###");
			
			if(this.spId.trim().length() > 0)
			{
				//Lay don gia + thanh tien cua SP
				this.dongia = "0";
				this.thanhtien = "0";
				
				if(this.soloId.trim().length() > 0 )
				{
					query = "select AVAILABLE from ERP_KHOTT_SP_CHITIET " +
							"where khott_fk = '" + this.khoId + "' and sanpham_fk = '" + this.spId + "' and SOLO = '" + this.soloId.trim() + "' ";
					System.out.println("____CHECK AVAI: " + query );
					
					ResultSet rsAVAI = db.get(query);
					try 
					{
						if(rsAVAI.next())
						{
							this.avai = format.format(rsAVAI.getDouble("AVAILABLE"));
						}
						rsAVAI.close();
					} 
					catch (Exception e) {
						this.avai = "0";
					}
				}
				
				if( this.bomId.trim().length() > 0 && this.soluong.trim().length() > 0 && this.soloId.trim().length() > 0 && this.ngaythuchien.trim().length() > 0 )
				{
					//CHECK THANG KHOA SO CO HOP LE HAY KHONG ( CHI DUOC CHOT SAU THANG KHOA SO + 1 )
					query = "select THANGKS, NAM from ERP_KHOASOKETOAN order by NAM desc, THANGKS desc";
					String thangKS = "1";
					String namKS = "2013";
					ResultSet rsCheck = db.get(query);
					if(rsCheck != null)
					{
						try 
						{
							if(rsCheck.next())
							{
								thangKS = rsCheck.getString("THANGKS");
								namKS = rsCheck.getString("NAM");
							}
							rsCheck.close();
						} 
						catch (Exception e) {}
					}
					
					/*query = "select c.pk_seq, c.MA, c.TEN, CAST( b.SOLUONG * " + this.soluong + " / a.SOLUONGCHUAN as numeric(18, 3)) as SoLuong, " +
								"isnull( ( select AVG(giaton) from ERP_TONKHOTHANG where THANG = '" + thangKS + "' and NAM = '" + namKS + "' and SANPHAM_FK = c.PK_SEQ ), '0' ) as GiaTon, " +
								"isnull(RACOLOC.SOLO, ' ') as SoLo , ISNULL(RACOLOC.NGAYSANXUAT, ' ') AS NGAYSANXUAT  " +
							"from ERP_DANHMUCVATTU a inner join ERP_DANHMUCVATTU_VATTU b on a.PK_SEQ = b.DANHMUCVT_FK " +
								"inner join SANPHAM c on b.VATTU_FK = c.PK_SEQ " +
								"left join  " +
								"( " +
									"select SanPham_fk, SOLO, ISNULL(NGAYSANXUAT, ' ') AS NGAYSANXUAT  from ERP_RACOLOC_SANPHAM where RaColoc_fk = '" + ( this.id.trim().length() <= 0 ? "-1" : this.id ) + "' " +
								") " +
								"RACOLOC on c.PK_SEQ = RACOLOC.SanPham_fk " +
							"where a.PK_SEQ = '" + this.bomId + "' and c.loaisanpham_fk != 100003 ";*/
					
					query =	"select c.pk_seq, c.MA, c.TEN, RACOLOC.soluong as SoLuong, "+
							"isnull( ( select AVG(giaton) from ERP_TONKHOTHANG where THANG = '" + thangKS + "' and NAM = '" + namKS + "' and SANPHAM_FK = c.PK_SEQ ), '0' ) as GiaTon, "+
							"isnull(RACOLOC.SOLO, ' ') as SoLo , ISNULL(RACOLOC.NGAYSANXUAT, ' ') AS NGAYSANXUAT , isnull(RACOLOC.ngayhethan ,'') as NGAYHETHAN  "+
							"from  SANPHAM c  "+
							" inner join  ( " +
							"    select SanPham_fk, SOLO, ISNULL(NGAYSANXUAT, ' ') AS NGAYSANXUAT, ISNULL(NGAYHETHAN,'') AS NGAYHETHAN , soluong  "+
							"			  from ERP_RACOLOC_SANPHAM  "+
							"			  where RaColoc_fk = '"+ this.id +"' ) RACOLOC on c.PK_SEQ = RACOLOC.SanPham_fk  "+
							 "where  c.loaisanpham_fk != 100003 ";
					
					System.out.println("____LAY BOM: " + query);
					ResultSet rs = db.get(query);
					if(rs != null)
					{
						try 
						{
							String _spIds = "";
							String _spTenIds = "";
							String _spMaIds = "";
							String _soluongIds = "";
							String _giatonIds = "";
							String _thanhtienIds = "";
							String _soloIds = "";
							String _ngaysxIds = "";
							String _ngayHetHanIds = "";
							
							while(rs.next())
							{
								_spIds += rs.getString("pk_seq") + "__";
								_spTenIds += rs.getString("TEN") + "__";
								_spMaIds += rs.getString("MA") + "__";
								_soluongIds += format.format(rs.getDouble("SoLuong")) + "__";
								_giatonIds += format.format(rs.getDouble("GiaTon")) + "__";
								_thanhtienIds +=  format.format( ( rs.getDouble("GiaTon") * rs.getDouble("SoLuong") ) ) + "__";
								_soloIds += rs.getString("SoLo") + "__";
								_ngaysxIds += rs.getString("NGAYSANXUAT") + "__";
								_ngayHetHanIds += (rs.getString("NGAYHETHAN").length()==0?"NA":rs.getString("NGAYHETHAN")) + "__";;
							}
							rs.close();
							
							//System.out.println("SP: " + _spIds + "  -- Ten: " + _spTenIds + " -- Ma: " + _spMaIds);
							//System.out.println("SoLuong: " + _soluongIds + "  -- Thanh Tien: " + _thanhtienIds + " -- SoLo: " + _soloIds);
							if(_spIds.trim().length() > 0)
							{
								_spIds = _spIds.substring(0, _spIds.length() - 2);
								this.spIds = _spIds.split("__");
								
								_spTenIds = _spTenIds.substring(0, _spTenIds.length() - 2);
								this.spTenIds = _spTenIds.split("__");
								
								_spMaIds = _spMaIds.substring(0, _spMaIds.length() - 2);
								this.spMaIds = _spMaIds.split("__");
								
								_soluongIds = _soluongIds.substring(0, _soluongIds.length() - 2);
								this.soluongIds = _soluongIds.split("__");
								
								_giatonIds = _giatonIds.substring(0, _giatonIds.length() - 2);
								this.dongiaIds = _giatonIds.split("__");
								
								_thanhtienIds = _thanhtienIds.substring(0, _thanhtienIds.length() - 2);
								this.thanhtienIds = _thanhtienIds.split("__");
	
								_soloIds = _soloIds.substring(0, _soloIds.length() - 2);
								this.soloIds = _soloIds.split("__");
								
								_ngaysxIds = _ngaysxIds.substring(0, _ngaysxIds.length() - 2);
								this.ngaysxIds = _ngaysxIds.split("__");
								
								_ngayHetHanIds = _ngayHetHanIds.substring(0, _ngayHetHanIds.length() - 2);
								this.ngayHetHanIds = _ngayHetHanIds.split("__");
								for(int i=0;i<this.ngayHetHanIds.length;i++){
									if(this.ngayHetHanIds[i].equals("NA")){
										this.ngayHetHanIds[i]="";
									}
								}
								
							}
						} 
						catch (Exception e) 
						{
							e.printStackTrace();
							System.out.println("____Exception COLOC: " + e.getMessage());
						}
					}
					
				}
				
			}
		}
		
	}

	
	public ResultSet getBomRs() {
		
		return this.bomRs;
	}

	
	public void setBomRs(ResultSet bomRs) {
		
		this.bomRs = bomRs;
	}

	
	public String getBomId() {
		
		return this.bomId;
	}

	
	public void setBomId(String bomId) {
		
		this.bomId = bomId;
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

	@Override
	public String[] getNgaysxIds() {
		// TODO Auto-generated method stub
		return this.ngaysxIds;
	}

	@Override
	public void setNgaysxIds(String[] ngaysxIds) {
		this.ngaysxIds =ngaysxIds;
		
	}

	public String[] getNgayHetHanIds() {
		return this.ngayHetHanIds;
	}

	public void setNgayHetHanIds(String[] ngayHetHanIds) {
		this.ngayHetHanIds =ngayHetHanIds;
	}

	@Override
	public String gettenSp()
	{
		// TODO Auto-generated method stub
		return this.tensp;
	}

	@Override
	public void settenSp(String tensp)
	{
		// TODO Auto-generated method stub
		this.tensp=tensp;
	}

	@Override
	public String gettenkho()
	{
		// TODO Auto-generated method stub
		return this.khottTen;
	}

	@Override
	public void settenkho(String tenkho)
	{
		// TODO Auto-generated method stub
		this.khottTen=tenkho;
	}
}
