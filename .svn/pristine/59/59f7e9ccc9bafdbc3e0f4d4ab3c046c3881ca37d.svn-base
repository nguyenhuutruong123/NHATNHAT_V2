package geso.dms.erp.beans.lenhsanxuat.imp;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import geso.dms.center.util.Utility_Kho;
import geso.dms.erp.db.sql.dbutils;
import geso.dms.erp.beans.lenhsanxuat.*;
import geso.dms.erp.beans.nhapkho.IKhu_Vitri;
import geso.dms.erp.beans.nhapkho.imp.Khu_Vitri;
import geso.dms.erp.beans.phieuxuatkho.ISpDetail;
import geso.dms.erp.beans.phieuxuatkho.imp.SpDetail;

public class ErpYeucaunguyenlieu implements IErpYeucaunguyenlieu
{
	private static final long serialVersionUID = 1L;
	String userId;
	String id;
	String ngayyeucau;
	String lydoyeucau;

	String Noidungxuat;
	String msg;
	String ischuyenNL;
	String trangthai;
	
	String nhamayId;
	ResultSet nhamayRs;
	
	String khoXuatId;
	ResultSet khoXuatRs;
	
 
	String khoNhanId;
	ResultSet khoNhanRs;
	
	String lsxIds;
	ResultSet lsxRs;
	
	List<IYeucau> spList;
	List<IYeucau> spChoNhapList;
	
	List<IKhu_Vitri> khuList;
	List<IKhu_Vitri> vitriList;
	
	dbutils db;
	
	public ErpYeucaunguyenlieu()
	{
		this.id = "";
		this.ngayyeucau = getDateTime();
		this.lydoyeucau = "";
		this.khoXuatId = "";
		this.khoNhanId = "";
		this.lsxIds = "";
		this.msg = "";
		this.Noidungxuat="";
		this.ischuyenNL = "0";
		this.trangthai = "";
		this.nhamayId = "";

		this.spList = new ArrayList<IYeucau>();
		this.spChoNhapList = new ArrayList<IYeucau>();
		
		this.khuList = new ArrayList<IKhu_Vitri>();
		this.vitriList = new ArrayList<IKhu_Vitri>();
		
		this.db = new dbutils();
	}
	
	public ErpYeucaunguyenlieu(String id)
	{
		this.id = id;
		this.ngayyeucau = getDateTime();
		this.lydoyeucau = "";
		this.khoXuatId = "";
		this.khoNhanId = "";
		this.lsxIds = "";
		this.msg = "";
		this.ischuyenNL = "0";
		this.trangthai = "";
		this.Noidungxuat="";
		this.nhamayId = "";

		this.spList = new ArrayList<IYeucau>();
		this.spChoNhapList = new ArrayList<IYeucau>();
		
		this.khuList = new ArrayList<IKhu_Vitri>();
		this.vitriList = new ArrayList<IKhu_Vitri>();
		
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

	public String getId() 
	{
		return this.id;
	}

	public void setId(String Id) 
	{
		this.id = Id;
	}

	public String getNgayyeucau() 
	{
		return this.ngayyeucau;
	}

	public void setNgayyeucau(String ngayyeucau) 
	{
		this.ngayyeucau = ngayyeucau;
	}

	public String getLydoyeucau() 
	{
		return this.lydoyeucau;
	}

	public void setLydoyeucau(String lydoyeucau) 
	{
		this.lydoyeucau = lydoyeucau;
	}

	public String getKhoXuatId() 
	{
		return this.khoXuatId;
	}

	public void setKhoXuatId(String khoxuattt) 
	{
		this.khoXuatId = khoxuattt;
	}

	public ResultSet getKhoXuatRs()
	{
		return this.khoXuatRs;
	}

	public void setKhoXuatRs(ResultSet khoxuatRs) 
	{
		this.khoXuatRs = khoxuatRs;
	}

	public String getKhoNhapId()
	{
		return this.khoNhanId;
	}

	public void setKhoNhapId(String khonhaptt) 
	{
		this.khoNhanId = khonhaptt;
	}

	public ResultSet getKhoNhapRs() 
	{
		return this.khoNhanRs;
	}

	public void setKhoNHapRs(ResultSet khonhapRs) 
	{
		this.khoNhanRs = khonhapRs;
	}

	public List<IYeucau> getSpList()
	{
		return this.spList;
	}

	public void setSpList(List<IYeucau> spList) 
	{
		this.spList = spList;
	}

	public String getMsg() 
	{
		return this.msg;
	}

	public void setMsg(String msg) 
	{
		this.msg = msg;
	}

	
	public boolean createYcnl() 
	{
		if(this.msg.trim().length() > 0)
		{
			this.msg = "Vui lòng kiểm tra lại các thông tin: \n" + this.msg;
			return false;
		}
		 
		if(this.khoXuatId.trim().length() <= 0)
		{
			this.msg = "Vui lòng chọn kho trung tâm phân phối";
			return false;
		}
		
		String sql="SELECT PK_SEQ FROM ERP_KHOTT WHERE LOAI='1'  AND  TRUNGTAMPP_FK ="+this.khoXuatId+"";
		try{
		ResultSet rs=db.get(sql);
		int count=0;
		while (rs.next()){
			this.khoNhanId=rs.getString("pk_seq");
			count=count+1;
			}
		rs.close();
		if(count >1){
			this.msg="Vui lòng kiểm tra lại kho nhận  thuộc trung tâm  phí đang chọn của yêu cầu này. ";
			return false;
		}
		
		}catch(Exception err){
			this.msg=err.getMessage();
			err.printStackTrace();
			return false;
		}
		
		if(this.khoNhanId.trim().length() <= 0)
		{
			this.msg="Vui lòng kiểm tra lại kho nhận  thuộc trung tâm  phí đang chọn của yêu cầu này. ";
			return false;
		}
	
		if(this.ngayyeucau.trim().length() <= 0)
		{
			this.msg = "Vui lòng nhập ngày yêu cầu";
			return false;
		}
		
		if(this.spList.size() <= 0)
		{
			this.msg = "Không có sản phẩm nào được chọn";
			return false;
		}
		else
		{
			for(int i = 0; i < this.spList.size(); i++)
			{
				IYeucau yc = this.spList.get(i);
				
				String soluongYC = yc.getSoluongYC().trim().length() > 0 ? yc.getSoluongYC().trim() : "0" ;
				String soluongDN = yc.getSoluongDaNhan().trim().length() > 0 ? yc.getSoluongDaNhan().trim() : "0";
				String soluongN = yc.getSoluongNhan().trim().length() > 0 ? yc.getSoluongNhan().trim() : "0" ;
				
				if(Float.parseFloat(soluongN) > ( Float.parseFloat(soluongYC) - Float.parseFloat(soluongDN) ) )
				{
					this.msg = "Số lượng nhận không được phép vượt quá số lượng yêu cầu và số lượng đã nhập kho";
					return false;
				}
			}
		}
		
		try
		{
			db.getConnection().setAutoCommit(false);
			
			/*String query = "insert ERP_YCCHUYENKHO(ngayyeucau, lydo, trangthai, khoxuat_fk, khonhan_fk, ngaytao, nguoitao, ngaysua, nguoisua) " +
							"values('" + this.ngayyeucau + "', N'" + this.lydoyeucau + "', '0', '100000', '100005', '" + getDateTime() + "', '" + this.userId + "', '" + getDateTime() + "', '" + this.userId + "')";
			
			if(!db.update(query))
			{
				this.msg = "Không thể tạo mới ERP_YCCHUYENKHO " + query;
				db.getConnection().rollback();
				return false;
			}*/
			
			String query = " insert ERP_YCCHUYENKHO(NGAYCHUYEN, NGAYCHOT, LyDo, NOIDUNGXUAT_FK, KHOXUAT_FK, KHONHAN_FK, TRANGTHAI, NGAYTAO, NGUOITAO, NGAYSUA, NGUOISUA) " +
						   " values('" + this.ngayyeucau + "', '" + this.ngayyeucau + "', N'" + this.lydoyeucau + "', '100009', '"+this.khoXuatId+"', '"+this.khoNhanId+"', '0', '" + getDateTime() + "', '" + this.userId + "', '" + getDateTime() + "', '" + this.userId + "') ";
			
			if(!db.update(query))
			{
				this.msg = "Không thể tạo mới ERP_YCCHUYENKHO " + query;
				db.getConnection().rollback();
				return false;
			}
			
			String ycnlCurrent = "";
			query = "select IDENT_CURRENT('ERP_YCCHUYENKHO') as ycnlId";
			
			ResultSet rsPxk = db.get(query);						
			if(rsPxk.next())
			{
				ycnlCurrent = rsPxk.getString("ycnlId");
				rsPxk.close();
			}
			
			if(this.lsxIds.trim().length() > 0)
			{
				query = "Insert ERP_YCCHUYENKHO_LSX ( ycchuyenkho_fk, lenhsanxuat_fk ) " +
						" select '" + ycnlCurrent + "', pk_seq from Erp_LenhSanXuat where pk_seq in ( " + this.lsxIds + " ) ";
				
				if(!db.update(query))
				{
					this.msg = "Không thể tạo mới ERP_YCCHUYENKHO_LSX " + query;
					db.getConnection().rollback();
					return false;
				}
			}
			
			if(this.spList.size() > 0)
			{
				for(int i = 0; i < this.spList.size(); i++)
				{
					IYeucau sp = this.spList.get(i);
					query = "insert ERP_YCCHUYENKHO_SANPHAM(ycchuyenkho_fk, SANPHAM_FK, soluongdenghi, soluongxuat, soluongnhan) " +
							"select '" + ycnlCurrent + "', pk_seq, '" + sp.getSoluongYC() + "', '" + sp.getSoluongYC() + "', '0' " +
							"from SANPHAM where ten = N'" + sp.getTen() + "' ";
					
					if(!db.update(query))
					{
						this.msg = "Khong the tao moi ERP_YCCHUYENKHO_SANPHAM: " + query;
						db.getConnection().rollback();
						return false;
					}
				}
			}
			
			db.getConnection().commit();
			db.getConnection().setAutoCommit(true);
		} 
		catch (Exception e) 
		{
			db.update("rollback");
			this.msg = "Exception: " + e.getMessage();
			return false;
		}
		
		return true;
	}

	public boolean updateYcnl() 
	{
		if(this.msg.trim().length() > 0)
		{
			this.msg = "Vui lòng kiểm tra lại các thông tin: \n" + this.msg;
			return false;
		}
		
		if(this.khoXuatId.trim().length() <= 0)
		{
			this.msg = "Vui lòng chọn kho xuất";
			return false;
		}
		String sql="SELECT PK_SEQ FROM ERP_KHOTT WHERE LOAI='1'  AND  TRUNGTAMPP_FK ="+this.khoXuatId+"";
		try{
		ResultSet rs=db.get(sql);
		int count=0;
		while (rs.next()){
			this.khoNhanId=rs.getString("pk_seq");
			count=count+1;
			}
		rs.close();
		if(count >1){
			this.msg="Vui lòng kiểm tra lại kho sản xuất  thuộc trung tâm  phí đang chọn của yêu cầu này. ";
			return false;
		}
		
		}catch(Exception err){
			this.msg=err.getMessage();
			err.printStackTrace();
			return false;
		}
		
		if(this.khoNhanId.trim().length() <= 0)
		{
			this.msg = "Vui lòng chọn kho nhận";
			return false;
		}
	
		if(this.ngayyeucau.trim().length() <= 0)
		{
			this.msg = "Vui lòng nhập ngày yêu cầu";
			return false;
		}
		
		if(this.spList.size() <= 0)
		{
			this.msg = "Không có sản phẩm nào được chọn";
			return false;
		}
		else
		{
			for(int i = 0; i < this.spList.size(); i++)
			{
				IYeucau yc = this.spList.get(i);
				
				String soluongYC = yc.getSoluongYC().trim().length() > 0 ? yc.getSoluongYC().trim() : "0" ;
				String soluongDN = yc.getSoluongDaNhan().trim().length() > 0 ? yc.getSoluongDaNhan().trim() : "0";
				String soluongN = yc.getSoluongNhan().trim().length() > 0 ? yc.getSoluongNhan().trim() : "0" ;
				
				if(Float.parseFloat(soluongN) > ( Float.parseFloat(soluongYC) - Float.parseFloat(soluongDN) ) )
				{
					this.msg = "Số lượng nhận không được phép vượt quá số lượng yêu cầu và số lượng đã nhập kho";
					return false;
				}
			}
		}
		
		try
		{
			db.getConnection().setAutoCommit(false);
			
			String query = "Update ERP_YCCHUYENKHO set ngaychuyen = '" + this.ngayyeucau + "', ngaychot = '" + this.ngayyeucau + "', lydo = N'" + this.lydoyeucau + "', khoxuat_fk = '"+this.khoXuatId+"'," +
							" khonhan_fk = '"+this.khoNhanId+"', ngaysua = '" + getDateTime() + "', nguoisua = '" + this.userId + "' " +
							"where pk_seq = '" + this.id + "' ";
							
			if(!db.update(query))
			{
				this.msg = "Không thể cập nhật ERP_YCCHUYENKHO " + query;
				db.getConnection().rollback();
				return false;
			}
			
			query = "delete ERP_YCCHUYENKHO_LSX where ycchuyenkho_fk = '" + this.id + "' ";
			if(!db.update(query))
			{
				this.msg = "Không thể cập nhật ERP_YCCHUYENKHO_LSX " + query;
				db.getConnection().rollback();
				return false;
			}
			
			if(this.lsxIds.trim().length() > 0)
			{
				query = "Insert ERP_YCCHUYENKHO_LSX ( ycchuyenkho_fk, lenhsanxuat_fk ) " +
						"select '" + this.id + "', pk_seq from Erp_LenhSanXuat where pk_seq in ( " + this.lsxIds + " ) ";
				
				if(!db.update(query))
				{
					this.msg = "Không thể tạo mới ERP_YCCHUYENKHO_LSX " + query;
					db.getConnection().rollback();
					return false;
				}
			}
			
			query = "delete ERP_YCCHUYENKHO_SANPHAM where ycchuyenkho_fk = '" + this.id + "' ";
			if(!db.update(query))
			{
				this.msg = "Không thể cập nhật ERP_YCCHUYENKHO_SANPHAM " + query;
				db.getConnection().rollback();
				return false;
			}
			
			if(this.spList.size() > 0)
			{
				for(int i = 0; i < this.spList.size(); i++)
				{
					IYeucau sp = this.spList.get(i);
					query = "insert ERP_YCCHUYENKHO_SANPHAM(ycchuyenkho_fk, SANPHAM_FK, soluongdenghi, soluongxuat, soluongnhan) " +
							"select '" + this.id + "', pk_seq, '" + sp.getSoluongYC() + "', '" + sp.getSoluongYC() + "', '0' " +
							"from SANPHAM where ten = N'" + sp.getTen() + "' ";
					
					if(!db.update(query))
					{
						this.msg = "Khong the tao moi ERP_YCCHUYENKHO_SANPHAM: " + query;
						db.getConnection().rollback();
						return false;
					}
				}
			}

			db.getConnection().commit();
			db.getConnection().setAutoCommit(true);
		} 
		catch (Exception e) 
		{
			e.printStackTrace();
			db.update("rollback");
			this.msg = "Exception: " + e.getMessage();
			return false;
		}
		
		return true;
	}

	public boolean chotYcnl() 
	{
		if(this.msg.trim().length() > 0)
		{
			this.msg = "Vui lòng kiểm tra lại các thông tin: \n" + this.msg;
			return false;
		}
		
		if(this.khoXuatId.trim().length() <= 0)
		{
			this.msg = "Vui lòng chọn kho xuất";
			return false;
		}
		
		if(this.khoNhanId.trim().length() <= 0)
		{
			this.msg = "Vui lòng chọn kho nhận";
			return false;
		}
	
		if(this.ngayyeucau.trim().length() <= 0)
		{
			this.msg = "Vui lòng nhập ngày yêu cầu";
			return false;
		}
		
		if(this.spList.size() <= 0)
		{
			this.msg = "Không có sản phẩm nào được chọn";
			return false;
		}
		else
		{
			for(int i = 0; i < this.spList.size(); i++)
			{
				IYeucau yc = this.spList.get(i);
				
				String soluongYC = yc.getSoluongYC().trim().length() > 0 ? yc.getSoluongYC().trim() : "0" ;
				String soluongDN = yc.getSoluongDaNhan().trim().length() > 0 ? yc.getSoluongDaNhan().trim() : "0";
				String soluongN = yc.getSoluongNhan().trim().length() > 0 ? yc.getSoluongNhan().trim() : "0" ;
				
				if(Float.parseFloat(soluongN) > ( Float.parseFloat(soluongYC) - Float.parseFloat(soluongDN) ) )
				{
					this.msg = "Số lượng nhận không được phép vượt quá số lượng yêu cầu và số lượng đã nhập kho";
					return false;
				}
			}
		}
		
		//Nhap vao kho. Hoi lai cach tinh gia ton, ghi nhan but toan ke toan luc nay
		try
		{
			db.getConnection().setAutoCommit(false);
			
			for(int i = 0; i < this.spList.size(); i++)
			{
				IYeucau sp = this.spList.get(i);
				
				if( !sp.getSoluongNhan().equals("0"))
				{
					List<ISpDetail> detailList = sp.getSpDetailList();
					for(int j = 0; j < detailList.size(); j++)
					{
						ISpDetail detail = detailList.get(j);
						
						 
						  
						String 	query = " select count(*) as sodong from ERP_KHOTT_SP_CHITIET " +
								" where khott_fk = '" + this.khoNhanId + "' and sanpham_fk = ( select pk_seq from SANPHAM where ma = '" + sp.getMa() + "' ) " +
										"and solo = '" + detail.getSolo() + "' and bin in ( select pk_seq from erp_vitrikho where trangthai = '1' and khott_fk = '" + this.khoNhanId + "' ) ";
						
						ResultSet rsCheck = db.get(query);
						boolean flag = true;
						if(rsCheck != null)
						{
							if(rsCheck.next())
							{
								if(rsCheck.getString("sodong").equals("0"))
									flag = false;
								rsCheck.close();
							}
						}
						
						if(flag) //da ton tau, cap nhat booked, avail
						{
							query = 	" update ERP_KHOTT_SP_CHITIET set soluong = soluong + '" + detail.getSoluong() + "', AVAILABLE = AVAILABLE + '" + detail.getSoluong() + "' " +
										" where KHOTT_FK = '" + this.khoNhanId + "' and SANPHAM_FK = ( select pk_seq from SANPHAM where ma = '" + sp.getMa() + "' ) " +
										" and SOLO = '" + detail.getSolo() + "'  ";
						}
						else
						{
							//lay ngay sanxuat, ngayhethan tu kho chuyen di
							String ngaysanxuat = "";
							String ngayhethan = "";
							query = " select ngaysanxuat, ngayhethan  " +
									" from erp_khott_sp_chitiet where sanpham_fk = ( select pk_seq from SANPHAM where ma = '" + detail.getMa() + "' ) and khott_fk = '" + this.khoXuatId + "' " +
									" and solo = '" + detail.getSolo() + "'";
							
							//System.out.println("__Lay ngay SX + ngay HH: " + query);
							ResultSet rsNgay = db.get(query);
							 
								if(rsNgay.next())
								{
									ngaysanxuat = rsNgay.getString("ngaysanxuat");
									ngayhethan = rsNgay.getString("ngayhethan");
								}
								rsNgay.close();
							 
							
							query = "insert ERP_KHOTT_SP_CHITIET(KHOTT_FK, SANPHAM_FK, SOLUONG, BOOKED, AVAILABLE, SOLO, BIN, NGAYSANXUAT, NGAYHETHAN, NGAYNHAPKHO) " +
									"select '" + this.khoNhanId + "', a.pk_seq, '" + detail.getSoluong() + "', '0', '" + detail.getSoluong() + "', '" + detail.getSolo() + "', 100000, '" + ngaysanxuat + "', '" + ngayhethan + "', '" + getDateTime() + "' " +
									"from SANPHAM a " +
									"where a.ma = '" + detail.getMa() + "' ";
						}
						
						//System.out.println("1__Tang kho nhan chi tiet: " + query);
						if(this.db.updateReturnInt(query) < 1)
						{
							this.msg = "3.Khong the cap nhat ERP_KHOTT_SP_CHITIET: " + query;
							db.getConnection().rollback();
							return false;
						}
					/*	
						query = "update ERP_KHOTT_SANPHAM set soluong = soluong + '" + detail.getSoluong() + "', AVAILABLE = AVAILABLE + '" + detail.getSoluong() + "' " +
								"where KHOTT_FK = '" + this.khoNhanId + "' and SANPHAM_FK = ( select pk_seq from SANPHAM where ma = '" + sp.getMa() + "' ) ";
						
						 
						if(!db.update(query))
						{
							this.msg = "3.Khong the cap nhat ERP_KHOTT_SANPHAM: " + query;
							db.getConnection().rollback();
							return false;
						}*/
						String spid_= this.getIdSp( sp.getMa());
						String khoit_cn=  this.khoNhanId;
						double soluongct_=0;
						double booked_ct_=0;
						double avai_ct_=0;
						try{ soluongct_ = Double.parseDouble(detail.getSoluong().replaceAll(",", ""));}catch(Exception err){}
						try{ avai_ct_ = Double.parseDouble(detail.getSoluong().replaceAll(",", ""));}catch(Exception err){}
						
						String msg1= Utility_Kho.Update_Kho_Sp_Provence(db, khoit_cn, spid_,  soluongct_,booked_ct_,avai_ct_, 0,this.id,"erpyeucaunguyenlieu .java 632");
						
						if(msg1.length() >0 )
						{
							db.getConnection().rollback();
							this.msg= "Lỗi :"+msg1;
							return false;
						}

						
						
						query = " update ERP_YCCHUYENKHO_SANPHAM  set soluongtongnhan = isnull(soluongtongnhan,0)  + '" + detail.getSoluong() + "'  " +
								" where   SANPHAM_FK = ( select pk_seq from SANPHAM where ma = '" + sp.getMa() + "' ) and YCCHUYENKHO_FK="+this.id+" ";
							 
						if(!db.update(query))
						{
							this.msg = "3.Khong the cap nhat ERP_KHOTT_SANPHAM: " + query;
							db.getConnection().rollback();
							return false;
						}
						
						query = " update ERP_YCCHUYENKHO_SANPHAM_ChiTIet  set isdachuyen='1'  " +
						" where   SANPHAM_FK = ( select pk_seq from SANPHAM where ma = '" + sp.getMa() + "' ) and YCCHUYENKHO_FK="+this.id+" ";
					 
						if(!db.update(query))
						{
							this.msg = "3.Khong the cap nhat ERP_KHOTT_SANPHAM: " + query;
							db.getConnection().rollback();
							return false;
						}
						// cap nhat kho npp 
						
						if(this.Noidungxuat.equals("100008")){
							
							query="select NCC_NHAN_FK from ERP_YCCHUYENKHO  where pk_seq="+this.id;
							System.out.println("Ve day nha em : "+query);
							rsCheck = db.get(query);
							rsCheck.next();
							String NccNhanID=rsCheck.getString("NCC_NHAN_FK");
							rsCheck.close();
							
							// tu day
							query = " select count(*) as sodong from ERP_KHOTT_SP_CHITIET_NCC " +
									" where khott_fk = '" + khoNhanId + "' " +
									" and sanpham_fk = ( select pk_seq from SANPHAM where ma = '" + sp.getMa() + "' )  and solo = '" + detail.getSolo() + "' and bin = 100000 and ncc_fk = '" + NccNhanID + "' ";
					 
									rsCheck = db.get(query);
									flag = true;
									 
										if (rsCheck.next())
										{
											if (rsCheck.getString("sodong").equals("0"))
												flag = false;
											rsCheck.close();
										}
								 
					
									if(flag)
									{
										query = "update ERP_KHOTT_SP_CHITIET_NCC set soluong = soluong + '" + detail.getSoluong() + "', AVAILABLE = AVAILABLE + '" + detail.getSoluong() + "'  " +
												"where KHOTT_FK = '" + khoNhanId + "' and ncc_fk = '" + NccNhanID + "' " +
														"and SANPHAM_FK = ( select pk_seq from SANPHAM where ma = '" + sp.getMa() + "' )   and SOLO = '" + detail.getSolo() + "' and BIN =100000 ";
									}
									else
									{
										String ngaysanxuat = "";
										String ngayhethan = "";
										query = "select ngaysanxuat, ngayhethan  " +
												"from ERP_KHOTT_SP_CHITIET where sanpham_fk =( select pk_seq from SANPHAM where ma = '" + sp.getMa() + "' )  and khott_fk = '" + khoNhanId + "' " +
													" and solo = '" + detail.getSolo() + "' and bin =100000 ";
									
									 
									    ResultSet rsNgay = db.get(query);
										if(rsNgay != null)
										{
										if(rsNgay.next())
										{
											ngaysanxuat = rsNgay.getString("ngaysanxuat");
											ngayhethan = rsNgay.getString("ngayhethan");
										}
										rsNgay.close();
										}
									
										query = " insert ERP_KHOTT_SP_CHITIET_NCC(KHOTT_FK, SANPHAM_FK, NCC_FK, SOLUONG, BOOKED, AVAILABLE, SOLO, BIN, NGAYSANXUAT, NGAYHETHAN, NGAYNHAPKHO) " +
											    " select '" + khoNhanId + "',pk_seq , '" + NccNhanID + "', '" + detail.getSoluong() + "', '0', '" + detail.getSoluong() + "', '" + detail.getSoluong() + "',100000, '" + ngaysanxuat + "', '" + ngayhethan + "', '" + getDateTime() + "' from sanpham where ma='"+detail.getMa()+"' ";
										 
										if(db.updateReturnInt(query) <1)
										{
											this.msg=query;
											db.getConnection().rollback();
											return false;
											 
										}
									  }
										//doi day
									}
						
					}
				}
			}
			
			String query = " select count(sanpham_fk) as soDong from ERP_YCCHUYENKHO_SANPHAM where soluongdenghi - isnull(soluongtongnhan,0)   != 0 and ycchuyenkho_fk = '" + this.id + "'";
			ResultSet rsCheck = db.get(query);
			int sodong = 0;
			if(rsCheck.next())
			{
				sodong = rsCheck.getInt("soDong");
				rsCheck.close();
			}
 
			query = " Update ERP_YCCHUYENKHO set  Dachuyenhang='0' "+(sodong <= 0 ?", trangthai='3' " : "" )+"  where pk_seq = '" + this.id + "' ";
			if(!db.update(query))
			{
				this.msg = "4.Khong the cap nhat ERP_YCCHUYENKHO: " + query;
				db.getConnection().rollback();
				return false;
			}
			db.getConnection().commit();
			db.getConnection().setAutoCommit(true);
		}
		catch (Exception e) 
		{
			e.printStackTrace();
			db.update("rollback");
			this.msg = "1.Exception: " + e.getMessage();
			return false;
		}
		
		
		return true;
	}
	private String getIdSp(String maVatTu)
	{
		String Idvt="";
		try{
			String query="select pk_seq from SanPham where ma = '" + maVatTu + "'";
			ResultSet rs=db.get(query);
			 
			if(rs.next()){
				Idvt=rs.getString("pk_seq");
			}
			rs.close();
		}catch(Exception er){}
		return Idvt;
	}
	
	public boolean chuyenNL() 
	{
		if(this.msg.trim().length() > 0)
		{
			this.msg = "Vui lòng kiểm tra lại các thông tin: \n" + this.msg;
			return false;
		}
		
		if(this.khoXuatId.trim().length() <= 0)
		{
			this.msg = "Vui lòng chọn kho xuất";
			return false;
		}
		
		if(this.khoNhanId.trim().length() <= 0)
		{
			this.msg = "Vui lòng chọn kho nhận";
			return false;
		}
	
		if(this.ngayyeucau.trim().length() <= 0)
		{
			this.msg = "Vui lòng nhập ngày yêu cầu";
			return false;
		}
		
		if(this.spList.size() <= 0)
		{
			this.msg = "Không có sản phẩm nào được chọn";
			return false;
		}
		else
		{
			this.createChuyenNL();
			if(this.msg.trim().length() > 0)
			{
				return false;
			}
			
			boolean flag = false;
			
			for(int i = 0; i < this.spList.size(); i++)
			{
				IYeucau yc = this.spList.get(i);
				
				String soluongYC = yc.getSoluongYC().trim().length() > 0 ? yc.getSoluongYC().trim() : "0" ;
				String soluongDN = yc.getSoluongDaNhan().trim().length() > 0 ? yc.getSoluongDaNhan().trim() : "0";
				String soluongN = yc.getSoluongNhan().trim().length() > 0 ? yc.getSoluongNhan().trim() : "0" ;
				
				if(Integer.parseInt(soluongN) > ( Integer.parseInt(soluongYC) - Integer.parseInt(soluongDN) ) )
				{
					this.msg = "Số lượng chuyển không được phép vượt quá số lượng yêu cầu và số lượng đã chuyển";
					return false;
				}
				
				if(yc.getSoluongNhan().trim().length() > 0)
				{
					if(Integer.parseInt(yc.getSoluongNhan().trim()) > 0)
					{
						flag = true;
					}
					
					List<ISpDetail> detail = yc.getSpDetailList();
					
					int sum = 0;
					for(int j = 0; j < detail.size(); j++)
					{
						if(detail.get(j).getSoluong().trim().length() > 0)
							sum += Integer.parseInt(detail.get(j).getSoluong().trim());
					}
					
					if( Integer.parseInt(yc.getSoluongNhan()) != sum )
					{
						this.msg += "Vui lòng kiểm tra số lượng Bin / Lô hàng xuất của sản phẩm " + yc.getTen() + ", trước khi thực hiện thao tác.\n";
						flag = false;
						return false;
						
					}
				}
			}
			
			if(!flag)
			{
				this.msg = "Vui lòng kiểm tra Bin / Lô hàng xuất trước khi thực hiện thao tác.";
				return false;
			}
		}
		
		try 
		{
			db.getConnection().setAutoCommit(false);
			
			for(int i = 0; i < this.spList.size(); i++)
			{
				IYeucau sp = this.spList.get(i);
				String query =  "Update ERP_YCCHUYENKHO_SANPHAM set soluongnhan = soluongnhan + '" + sp.getSoluongNhan() + "' " +
								"where  ycchuyenkho_fk = '" + this.id + "' and  SANPHAM_FK = ( select pk_seq from SANPHAM where ma = '" + sp.getMa() + "') ";
				
				if(!db.update(query))
				{
					this.msg = "Khong the cap nhat ERP_YCCHUYENKHO_SANPHAM: " + query;
					db.getConnection().rollback();
					return false;
				}
				else
				{
					List<ISpDetail> spCon = sp.getSpDetailList();
					for(int j = 0; j < spCon.size(); j++)
					{
						ISpDetail detail = spCon.get(j);
						
						if( ! detail.equals("0"))
						{
							
							//Luu lai luong xuatkho
							query =  "insert ERP_YCCHUYENKHO_SP_XUATKHO(ycchuyenkho_fk, sanpham_fk, khott_fk, solo, soluong, bean) " +
									 "select '" + this.id + "', pk_seq, '" + this.khoXuatId + "', '" + detail.getSolo() + "', '" + detail.getSoluong() + "', '" + detail.getVitriId() + "' " +
									 "from SANPHAM where ma = '" + sp.getMa() + "' ";
							
							if(!db.update(query))
							{
								this.msg = "2.Khong the cap nhat ERP_YCCHUYENKHO_SP_XUATKHO: " + query;
								db.getConnection().rollback();
								return false;
							}
							
							
							query = "select count(*) as sodong from ERP_YCCHUYENKHO_SANPHAM_CHITIET " +
									"where ycchuyenkho_fk = '" + this.id + "' and SANPHAM_FK = ( select pk_seq from SANPHAM where ma = '" + detail.getMa() + "' ) and SOLO = '" + detail.getSolo() + "' and BEAN = '" + detail.getVitriId() + "'";
							
							ResultSet rsCheck = db.get(query);
							int sodong = 0;
							if(rsCheck != null)
							{
								if(rsCheck.next())
								{
									sodong = rsCheck.getInt("sodong");
								}
								rsCheck.close();
							}
							
							if(sodong <= 0)
							{
								query = "insert ERP_YCCHUYENKHO_SANPHAM_CHITIET(ycchuyenkho_fk, SANPHAM_FK, SOLO, SOLUONG, BEAN) " +
										"select '" + this.id + "', pk_seq, '" + detail.getSolo() + "', '" + detail.getSoluong() + "', '" + detail.getVitriId() + "' " +
										"from SANPHAM where ma = '" + detail.getMa() + "' ";
							}
							else
							{
								query = "update ERP_YCCHUYENKHO_SANPHAM_CHITIET set soluong = soluong + '" + detail.getSoluong() + "' " + 
										"where ycchuyenkho_fk = '" + this.id + "' and SANPHAM_FK = ( select pk_seq from SANPHAM where ma = '" + detail.getMa() + "' ) and SOLO = '" + detail.getSolo() + "' and BEAN = '" + detail.getVitriId() + "'";
							}
							
							System.out.println("1133____Insert yeucau NL: " + query);
							
							if(!db.update(query))
							{
								this.msg = "Khong the cap nhat ERP_YCCHUYENKHO_SANPHAM_CHITIET: " + query;
								db.getConnection().rollback();
								return false;
							}
						}
					}
				}
			}
			
			String query = "Update ERP_YCCHUYENKHO set khonhan_fk="+this.khoNhanId+",trangthai = '1', nguoisua = '" + this.userId + "', ngaysua = '" + getDateTime() + "' where pk_seq = '" + this.id + "'";
			if(!db.update(query))
			{
				this.msg = "Khong the cap nhat ERP_YCCHUYENKHO: " + query;
				db.getConnection().rollback();
				return false;
			}
			
			db.getConnection().commit();
			db.getConnection().setAutoCommit(true);
		}
		catch (Exception e) 
		{
			db.update("rollback");
			this.msg = "115.Exception: " + e.getMessage();
			return false;
		}
		
		return true;
		
	}
	
	
	
	public void createRs() 
	{
 
		this.khoNhanRs = db.get("select PK_SEQ, TEN from ERP_KHOTT where TrangThai = '1' and loai = '5' and congty_fk = '100005' ");
		
		this.khoXuatRs = db.get("select PK_SEQ, TEN from ERP_KHOTT where TrangThai = '1' and loai = '5' and congty_fk = '100005' ");

		if(this.ischuyenNL.equals("0"))
		{
			String query = 	"select a.PK_SEQ as lsxId, a.ngaybatdau, a.ngaydukienht, b.TEN as spTen  " +
							"from ERP_LENHSANXUAT a inner Join SANPHAM b on a.SANPHAM_FK = b.PK_SEQ " +
							"where a.kho_fk="+ (this.khoXuatId.length() >0?this.khoXuatId:"0")+"  and  a.trangthai in (1, 2) and a.pk_seq not in ( select lenhsanxuat_fk from ERP_YCCHUYENKHO_LSX where ycchuyenkho_fk in (select pk_seq from ERP_YCCHUYENKHO where trangthai != 3) )  ";
			
			if(this.id.trim().length() > 0)
			{
				query +=    " union ";
				query +=    " select b.PK_SEQ as lsxId, b.ngaybatdau, b.ngaydukienht, c.TEN as spTen  " +
							" from ERP_YCCHUYENKHO_LSX a inner join ERP_LENHSANXUAT b on a.lenhsanxuat_fk = b.PK_SEQ  " +
							" inner Join SANPHAM c on b.SANPHAM_FK = c.PK_SEQ  " +
							" where a.ycchuyenkho_fk = '" + this.id + "' and b.kho_fk="+(this.khoXuatId.length() >0?this.khoXuatId:"0")+" ";
			}
			System.out.println("Get Lenh San Xuat:  "+query);
			this.lsxRs = db.get(query);
		}
		
		if(this.khoXuatId.trim().length() >= 0 && this.khoNhanId.trim().length() >= 0 )
		{
			if(this.ischuyenNL.equals("0"))
			{
				if(this.lsxIds.trim().length() > 0)
				{
					this.createYeuCauNL();
				}
			}
			else
			{
				this.createChuyenNL();
			}
		}
		
	}

	
	private void createChuyenNL() 
	{
		for(int i = 0; i < this.spList.size(); i++)
		{
			IYeucau yeucau = this.spList.get(i);
			String soluongNhan = yeucau.getSoluongNhan().trim();
			
			if(soluongNhan.length() > 0)
			{
				List<ISpDetail> spDetail = new ArrayList<ISpDetail>();
				
				String query = "select SANPHAM_FK, isnull(AVAILABLE, 0) as AVAILABLE, SOLO, b.pk_seq as vtId, b.MA as vitri, c.diengiai as khu " +
								"from ERP_KHOTT_SP_CHITIET a inner join ERP_VITRIKHO b on a.BIN = b.PK_SEQ " +
									"inner join ERP_KHUVUCKHO c on b.KHU_FK = c.pk_seq " +
								"where a.sanpham_fk = ( select pk_seq from SANPHAM where ma = '" + yeucau.getMa() + "' ) and a.KHOTT_FK = '" + this.khoXuatId + "' " +
								"order by a.ngayhethan asc, a.AVAILABLE asc";
				
				System.out.println("112.Check soluong san pham: " + query);
				
				ResultSet rsSpDetail = db.get(query);
				
				if(rsSpDetail != null)
				{
					int tongluong = 0;
					
					try
					{
						while(rsSpDetail.next())
						{
							int avaiD = rsSpDetail.getInt("AVAILABLE");
							String maspD = yeucau.getMa();
							String soloD = rsSpDetail.getString("SOLO");
							String vitriD = rsSpDetail.getString("vitri");
							String vitriIdD = rsSpDetail.getString("vtId");
							String khuD = rsSpDetail.getString("khu");
							
							if(avaiD > 0)
							{
								tongluong += avaiD;
								if(tongluong < Integer.parseInt(soluongNhan))
								{
									ISpDetail spDetail2 = new SpDetail(maspD, soloD, Integer.toString(avaiD), khuD, vitriD, vitriIdD);
									spDetail.add(spDetail2);
								}
								else
								{
									int slg = Integer.parseInt(soluongNhan) - (tongluong - avaiD);
									ISpDetail spDetail2 = new SpDetail(maspD, soloD, Integer.toString(slg), khuD, vitriD, vitriIdD);
									spDetail.add(spDetail2);
									break;
								}
							}
						}
						
						rsSpDetail.close();
					}
					catch (Exception e) 
					{
						System.out.println("1.Exception: " + e.getMessage());
					}
	
				}
				
				this.spList.get(i).setSpDetailList(spDetail);
				
			}
		}
		
		System.out.println("Size la: " + this.spList.size());
		
		for(int i = 0; i < this.spList.size(); i++)
		{
			IYeucau sp = this.spList.get(i);
			
			if(sp.getSoluongNhan().trim().length() > 0)
			{
				List<ISpDetail> spDetail = sp.getSpDetailList();
				
				int sum = 0;
				for(int j = 0; j < spDetail.size(); j++)
				{
					sum += Integer.parseInt(spDetail.get(j).getSoluong());
				}
				
				if(sum < Integer.parseInt(sp.getSoluongNhan()))
				{
					this.msg += "+ Sản phẩm " + sp.getMa() + " - " + sp.getTen() + ", không đủ số lượng để xuất kho, vui lòng kiểm tra lại \n";
				}
			}
		}
	}

	private void createYeuCauNL() 
	{
		String query =  " select c.PK_SEQ as spId, c.MA as spMa, c.TEN as spTen, sum(b.SoLuong) as SoLuong  " +
						" from ERP_LENHSANXUAT a inner join ERP_LENHSANXUAT_BOM b on a.PK_SEQ = b.lenhsanxuat_fk  " +
						" inner Join SANPHAM c on b.VatTu_fk = c.PK_SEQ  " +
						" where a.PK_SEQ in ( " + this.lsxIds + " )  " +
						" group by c.PK_SEQ, c.MA,  c.TEN";
		
		ResultSet spRs = db.get(query);
		
		List<IYeucau> spList = new ArrayList<IYeucau>();
		
		if(spRs != null)
		{
			try 
			{
				IYeucau sp = null;
				while(spRs.next())
				{
					String spId = spRs.getString("spId");
					String spMa = spRs.getString("spMa");
					String spTen = spRs.getString("spTen");
					String soluong = spRs.getString("soluong");
					
					sp = new Yeucau();
					sp.setId(spId);
					sp.setMa(spMa);
					sp.setTen(spTen);
					sp.setSoluongYC(soluong);
									
					spList.add(sp);
				}
				spRs.close();
			} 
			catch (Exception e) 
			{
				System.out.println("1.Exception: " + e.getMessage());
			}
			
			this.spList = spList;
		}
	}

	public void init() 
	{
		String query = " select  NOIDUNGXUAT_FK ,ngaychuyen as ngayyeucau, lydo, khoxuat_fk, khonhan_fk, trangthai from ERP_YCCHUYENKHO where pk_seq = '" + this.id + "'";
		ResultSet rs = db.get(query);
		if(rs != null)
		{
			try 
			{
				if(rs.next())
				{
					this.ngayyeucau = rs.getString("ngayyeucau");
					this.lydoyeucau = rs.getString("lydo");
					this.khoXuatId = rs.getString("khoxuat_fk");
					this.khoNhanId = rs.getString("khonhan_fk");
					this.trangthai = rs.getString("trangthai");
					this.Noidungxuat=rs.getString("NOIDUNGXUAT_FK");
					
				}
				rs.close();
			} 
			catch (Exception e) {
				this.msg=e.getMessage();
				e.printStackTrace();
			}
		}
		
		query = " select lenhsanxuat_fk from ERP_YCCHUYENKHO_LSX where ycchuyenkho_fk = '" + this.id + "'";
		rs = db.get(query);
		if(rs != null)
		{
			try 
			{
				String lsxId = "";
				while(rs.next())
				{
					lsxId += rs.getString("lenhsanxuat_fk") + ",";
				}
				rs.close();
				
				if(lsxId.trim().length() > 0)
				{
					lsxId = lsxId.substring(0, lsxId.length() - 1);
				}
				
				this.lsxIds = lsxId;	
			} 
			catch (Exception e) {
				e.printStackTrace();
			}
		}
		
		System.out.println("___LAY LSX: " + this.lsxIds);
		this.initSanPham();
 
		//Kho xuat la kho nguyen lieu & thanh pham sau san xuat
		this.khoXuatRs = db.get("select PK_SEQ, TEN from ERP_KHOTT where TrangThai = '1' and loai = '5' and congty_fk = '100005' ");
		
		this.khoNhanRs = db.get("select PK_SEQ, TEN from ERP_KHOTT where TrangThai = '1' and loai = '5' and congty_fk = '100005' ");
		
		
		
		if(this.ischuyenNL.equals("0") && this.Noidungxuat.equals("100009"))
		{
			
			query = "select a.PK_SEQ as lsxId, a.ngaybatdau, a.ngaydukienht, b.TEN as spTen  " +
					"from ERP_LENHSANXUAT a inner Join SANPHAM b on a.SANPHAM_FK = b.PK_SEQ " +
					"where a.kho_fk="+(this.khoXuatId.length()>0? this.khoXuatId:"0")+" and a.trangthai in (1, 2) and a.pk_seq not in ( select lenhsanxuat_fk from ERP_YCCHUYENKHO_LSX where ycchuyenkho_fk in (select pk_seq from ERP_YCCHUYENKHO where trangthai != 3) )  ";
			
			if(this.id.trim().length() > 0)
			{
				query += " union ";
				query += " select b.PK_SEQ as lsxId, b.ngaybatdau, b.ngaydukienht, c.TEN as spTen  " +
						 " from ERP_YCCHUYENKHO_LSX a inner join ERP_LENHSANXUAT b on a.lenhsanxuat_fk = b.PK_SEQ  " +
						 " inner Join SANPHAM c on b.SANPHAM_FK = c.PK_SEQ  " +
						 " where  b.kho_fk="+(this.khoXuatId.length()>0? this.khoXuatId:"0")+"  and  a.ycchuyenkho_fk = '" + this.id + "'  ";
			}
			
			//System.out.println("1.Khoi tao LSX: " + query);
			this.lsxRs = db.get(query);
			 
			//init Bean Nhan
			ResultSet khuRs = db.get("select pk_seq, ten from ERP_KHUVUCKHO where khott_fk = '" + this.khoNhanId + "' order by pk_seq");
			this.khuList.clear();

			if (khuRs != null)
			{
				try
				{
					while (khuRs.next())
					{
						this.khuList.add(new Khu_Vitri(khuRs.getString("pk_seq"), khuRs.getString("ten")));
					}
					khuRs.close();
				} 
				catch (SQLException e){}
			}

			query = "select cast(b.pk_seq as nvarchar(10)) + ' - ' + cast(a.pk_seq as nvarchar(10)) as pk_seq, a.MA " +
					"from ERP_VITRIKHO a inner join ERP_KHUVUCKHO b on a.khu_fk = b.pk_seq where a.khott_fk = '" + this.khoNhanId + "' " +
					"order by a.khu_fk ASC";
			
			//System.out.println("___Khoi tao vi tri: " + query);
			
			ResultSet vitriRs = db.get(query);
			this.vitriList.clear();

			if (vitriRs != null)
			{
				try
				{
					while (vitriRs.next())
					{
						this.vitriList.add(new Khu_Vitri(vitriRs.getString("pk_seq"), vitriRs.getString("ma")));
					}
					vitriRs.close();
				} 
				catch (SQLException e){}
			}
			
		}
		
		//this.createRs();
	}

	
	private void initSanPham() 
	{
		String loaiten = "2";
		if(this.ischuyenNL.equals("1"))
			loaiten = "1";
		
		String query =  " select isnull(soluongtongxuat,0) as soluongtongxuat,b.PK_SEQ as spId " +
						" , b.MA as spMa, isnull(b.TEN" + loaiten + ", b.TEN) as spTen " +
						" , a.soluongdenghi  as SoLuongYC,  " +
						" isnull(soluongtongnhan,0) as soluongtongnhan ," +
						" (  select SUM(soluongxuat) as soluongxuat from   ERP_YCCHUYENKHO_SANPHAM_CHITIET  where YCCHUYENKHO_FK=a.YCCHUYENKHO_FK and SANPHAM_FK=a.sanpham_fk and isdachuyen=0 )    as soluongnhan " +
						" from ERP_YCCHUYENKHO_SANPHAM a    " + 
						" inner Join SANPHAM b on a.sanpham_fk = b.PK_SEQ    " +
						" where a.ycchuyenkho_fk  = '" + this.id + "' ";
	    System.out.println("1.Query khoi tao sp: " + query);
		ResultSet rs = db.get(query);
		if(rs != null)
		{
			List<IYeucau> spList = new ArrayList<IYeucau>();
			try 
			{
				IYeucau sanpham;
				IYeucau sanphamDN;
				while(rs.next())
				{
					String spId = rs.getString("spId");
					String spMa = rs.getString("spMa");
					String spTen = rs.getString("spTen");
					String soluongYC = rs.getString("SoLuongYC");
					String soLuongNhap = rs.getString("soluongNhan");
					String soluongNhan = rs.getString("soluongNhan");
					
					sanpham = new Yeucau();
					sanpham.setTongSoluongDaXuat(rs.getString("soluongtongxuat"));
					sanpham.setId(spId);
					sanpham.setSoluongDaNhan(rs.getString("soluongtongnhan"));
					sanpham.setMa(spMa);
					sanpham.setTen(spTen);
					sanphamDN = new Yeucau();
					sanphamDN.setId(spId);
					sanphamDN.setMa(spMa);
					sanphamDN.setTen(spTen);
					sanpham.setSoluongYC(soluongYC);
					sanpham.setSoluongNhan(soluongNhan);
					
					
					query =" select a.SOLO,   100000 as khuId, 100000 as khuTen, 100000 as vtId,  " +
							" 100000 as vitri, isnull(a.soluongXuat, 0) as soluongXuat   " +
							" from ERP_YCCHUYENKHO_SANPHAM_CHITIET a  "+
							" where isdachuyen=0 and  a.ycchuyenkho_fk = '" + this.id + "' and a.SANPHAM_FK = '" + spId + "'";
					
					System.out.println("2.San pham Detail: " + query);
					ResultSet rsSpDetail = db.get(query);
					
					List<ISpDetail> spConList = new ArrayList<ISpDetail>();
					ISpDetail spCon = null;
					 
						while(rsSpDetail.next())
						{
							String idhangmua = spId;
							String solo = rsSpDetail.getString("solo");
							String slg = Float.toString(rsSpDetail.getFloat("soluongxuat"));
							String khu = rsSpDetail.getString("khuTen");
							String vitri = rsSpDetail.getString("vitri");
							String vitriId = rsSpDetail.getString("vtId");
							spCon = new SpDetail(idhangmua, solo, slg, khu, vitri, vitriId);
							spConList.add(spCon);
						}
						rsSpDetail.close();
					 
					sanpham.setSpDetailList(spConList);	
					
					spList.add(sanpham);
				}
			
				rs.close();
			} 
			catch (Exception e) 
			{
				e.printStackTrace();
				System.out.println("3.Exception: " + e.getMessage());
			}
			
			this.spList = spList;
		}
	}

	public void DBclose() {
		
		try{
		
			
			if(khoXuatRs!=null){
				khoXuatRs.close();
			}
		
			if(khoNhanRs!=null){
				khoNhanRs.close();
			}
			
			
			if(lsxRs!=null){
				lsxRs.close();
			}
			
			if(spChoNhapList!=null){
				spChoNhapList.clear();
			}
			if(khuList!=null){
				khuList.clear();
			}
			
			if(vitriList!=null){
				vitriList.clear();
			}
			
			if(spList!=null){
				spList.clear();
			}
			
			if(nhamayRs!=null){
				nhamayRs.close();
			}
			this.db.shutDown();
			
		}catch(Exception er){
			
		}
	}
	
	public String getLsxIds()
	{
		return this.lsxIds;
	}

	public void setLsxIds(String lsxIds) 
	{
		this.lsxIds = lsxIds;
	}

	public ResultSet getLsxRs()
	{
		return this.lsxRs;
	}

	public void setLsxRs(ResultSet lsxRs) 
	{
		this.lsxRs = lsxRs;
	}
	
	private String getDateTime() 
	{
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date date = new Date();
        return dateFormat.format(date);	
	}

	public String getIschuyenNL() 
	{
		return this.ischuyenNL;
	}

	public void setIschuyenNL(String ischuyenNL)
	{
		this.ischuyenNL = ischuyenNL;
	}

	
	public List<IKhu_Vitri> getKhuList()
	{
		return this.khuList;
	}

	public void setKhuList(List<IKhu_Vitri> khuList) 
	{
		this.khuList = khuList;
	}

	public List<IKhu_Vitri> getVitriList() 
	{
		return this.vitriList;
	}

	public void setVitriList(List<IKhu_Vitri> vitriList)
	{
		this.vitriList = vitriList;
	}

	public List<IYeucau> getSpChoNhapList()
	{
		return this.spChoNhapList;
	}

	public void setSpChoNhapList(List<IYeucau> spchoNhapList) 
	{
		this.spChoNhapList = spchoNhapList;
	}


	public void initYeucauNLPdf() 
	{
		String query =  "select a.SANPHAM_FK, b.MA, b.Ten as Ten, a.SOLUONGYEUCAU as SoLuong " +
						"from ERP_YCCHUYENKHO_SANPHAM a " +
						"inner Join SANPHAM b on a.SANPHAM_FK = b.PK_SEQ where ycchuyenkho_fk = '" + this.id + "'";
		
		System.out.println("1.Query khoi tao sp: " + query);
		ResultSet rs = db.get(query);
		
		if(rs != null)
		{
			List<IYeucau> spList = new ArrayList<IYeucau>();
			
			try 
			{
				IYeucau yeucau;

				while(rs.next())
				{
					String spId = rs.getString("SANPHAM_FK");
					String spMa = rs.getString("MA");
					String spTen = rs.getString("TEN");
					String soluong = rs.getString("SOLUONG");

					yeucau = new Yeucau();
					yeucau.setId(spId);
					yeucau.setMa(spMa);
					yeucau.setTen(spTen);
					yeucau.setSoluongYC(soluong);
			
					query = " select b.MA, b.TEN, c.MA AS VITRI, d.DIENGIAI AS KHUTEN, e.DONVI as DONVIDOLUONG, nhapkho.solo, nhapkho.tongNhap, f.ma as khoTen  " +
						"from " +
						"( " +
							"select sanpham_fk, khott_fk, solo, bean, SUM(soluong) as tongNhap  " +
							"from ERP_YCCHUYENKHO_SP_NHAPKHO   " +
							"where ycchuyenkho_fk = '" + this.id + "' and sanpham_fk = '" + spId + "'  " +
							"group by sanpham_fk, khott_fk, solo, bean  " +
						")  " +
						"nhapkho inner Join SANPHAM b on nhapkho.sanpham_fk = b.PK_SEQ " +
						"INNER JOIN ERP_VITRIKHO c ON nhapkho.BEAN = c.PK_SEQ  " +
						"inner join ERP_KHUVUCKHO d on c.KHU_FK = d.PK_SEQ  " +
						"inner join DONVIDOLUONG e on b.DVDL_FK = e.PK_SEQ inner join ERP_KHOTT f on d.khott_fk = f.pk_seq ";
					
					System.out.println("1.San pham lay hang Detail: " + query);
					ResultSet rsSpDetail = db.get(query);
					
					List<ISpDetail> spConList = new ArrayList<ISpDetail>();
					ISpDetail spCon = null;
					
					boolean flag = false;
					if(rsSpDetail != null)
					{
						while(rsSpDetail.next())
						{
							String idhangmua = rsSpDetail.getString("DONVIDOLUONG"); //luu donvidoluong
							String solo = rsSpDetail.getString("solo");
							
							int conlai = Integer.parseInt(soluong) - rsSpDetail.getInt("tongNhap");
							String slg = soluong + " - " + rsSpDetail.getString("tongNhap") + " - " + Integer.toString(conlai);
							String khu = rsSpDetail.getString("KHUTEN");
							String vitri = rsSpDetail.getString("VITRI");
							String vitriId = rsSpDetail.getString("khoTen");
							
							spCon = new SpDetail(idhangmua, solo, slg, khu, vitri, vitriId);
							spConList.add(spCon);
							
							flag = true;
						}
						rsSpDetail.close();
					}
					
					if(!flag)
					{
						String slg = soluong + " - 0 - " + soluong;
						spCon = new SpDetail(" ", " ", slg, " ", " ", " ");
						spConList.add(spCon);
					}
					
					yeucau.setSpDetailList(spConList);	
					spList.add(yeucau);
				}
			
				rs.close();
			} 
			catch (Exception e) 
			{ 
				System.out.println("1.Exception: " + e.getMessage() + "\n");
			}
			
			this.spList = spList;
		}
	}

	public void initChuyenNLPdf() 
	{
		String query =  "select a.SANPHAM_FK, b.MA, b.Ten as Ten, a.SOLUONGYEUCAU as SoLuong " +
				"from ERP_YCCHUYENKHO_SANPHAM a " +
				"inner Join SANPHAM b on a.SANPHAM_FK = b.PK_SEQ where ycchuyenkho_fk = '" + this.id + "'";
		
		System.out.println("1.Query khoi tao sp: " + query);
		ResultSet rs = db.get(query);
		
		if(rs != null)
		{
		List<IYeucau> spList = new ArrayList<IYeucau>();
		
		try 
		{
		IYeucau yeucau;
		
		while(rs.next())
		{
			String spId = rs.getString("SANPHAM_FK");
			String spMa = rs.getString("MA");
			String spTen = rs.getString("TEN");
			String soluong = rs.getString("SOLUONG");
		
			yeucau = new Yeucau();
			yeucau.setId(spId);
			yeucau.setMa(spMa);
			yeucau.setTen(spTen);
			yeucau.setSoluongYC(soluong);
		
			query = " select b.MA, b.TEN, c.MA AS VITRI, d.DIENGIAI AS KHUTEN, e.DONVI as DONVIDOLUONG, nhapkho.solo, nhapkho.tongNhap, f.ma as khoTen  " +
				"from " +
				"( " +
					"select sanpham_fk, khott_fk, solo, bean, SUM(soluong) as tongNhap  " +
					"from ERP_YCCHUYENKHO_SP_XUATKHO   " +
					"where ycchuyenkho_fk = '" + this.id + "' and sanpham_fk = '" + spId + "'  " +
					"group by sanpham_fk, khott_fk, solo, bean  " +
				")  " +
				"nhapkho inner Join SANPHAM b on nhapkho.sanpham_fk = b.PK_SEQ " +
				"INNER JOIN ERP_VITRIKHO c ON nhapkho.BEAN = c.PK_SEQ  " +
				"inner join ERP_KHUVUCKHO d on c.KHU_FK = d.PK_SEQ  " +
				"inner join DONVIDOLUONG e on b.DVDL_FK = e.PK_SEQ inner join ERP_KHOTT f on d.khott_fk = f.pk_seq ";
			
			System.out.println("1.San pham lay hang Detail: " + query);
			ResultSet rsSpDetail = db.get(query);
			
			List<ISpDetail> spConList = new ArrayList<ISpDetail>();
			ISpDetail spCon = null;
			
			boolean flag = false;
			if(rsSpDetail != null)
			{
				while(rsSpDetail.next())
				{
					String idhangmua = rsSpDetail.getString("DONVIDOLUONG"); //luu donvidoluong
					String solo = rsSpDetail.getString("solo");
					
					int conlai = Integer.parseInt(soluong) - rsSpDetail.getInt("tongNhap");
					String slg = soluong + " - " + rsSpDetail.getString("tongNhap") + " - " + Integer.toString(conlai);
					String khu = rsSpDetail.getString("KHUTEN");
					String vitri = rsSpDetail.getString("VITRI");
					String vitriId = rsSpDetail.getString("khoTen");
					
					spCon = new SpDetail(idhangmua, solo, slg, khu, vitri, vitriId);
					spConList.add(spCon);
					
					flag = true;
				}
				rsSpDetail.close();
			}
			
			if(!flag)
			{
				String slg = soluong + " - 0 - " + soluong;
				spCon = new SpDetail(" ", " ", slg, " ", " ", " ");
				spConList.add(spCon);
			}
			
			yeucau.setSpDetailList(spConList);	
			spList.add(yeucau);
		}
		
		rs.close();
		} 
		catch (Exception e) 
		{ 
		System.out.println("1.Exception: " + e.getMessage() + "\n");
		}
		
		this.spList = spList;
		}
	}

	public String getTrangthai() 
	{
		return this.trangthai;
	}

	public void setTrangthai(String trangthai) 
	{
		this.trangthai = trangthai;
	}

	
	public String getNhamayId() 
	{
		return this.nhamayId;
	}

	public void setNhamayId(String nhamayId) 
	{
		this.nhamayId = nhamayId;
	}

	public ResultSet getNhamayRs()
	{
		return this.nhamayRs;
	}

	public void setNhamayRs(ResultSet nhamayRs) 
	{
		this.nhamayRs = nhamayRs;
	}

	@Override
	public String getNoidungxuat() {
		// TODO Auto-generated method stub
		return this.Noidungxuat;
	}

	@Override
	public void setNoidungxuat(String NoidungxuatId) {
		// TODO Auto-generated method stub
		this.Noidungxuat=NoidungxuatId;
	}


}
