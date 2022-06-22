package geso.dms.center.beans.dieuchinhtonkho.imp;

import geso.dms.center.beans.dieuchinhtonkho.IErpChuyenkhoTT;
import geso.dms.center.db.sql.dbutils;
import geso.dms.center.util.Utility;

import java.sql.ResultSet;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Enumeration;
import java.util.Hashtable;

public class ErpChuyenkhoTT implements IErpChuyenkhoTT
{
	
	
	
	private static final long serialVersionUID = 1L;
	String userId;
	String id;
	
	String ngayyeucau;
	String ghichu;

	String msg;
	String trangthai;
	
	String khoXuatId;
	ResultSet khoXuatRs;
	
	String khoNhanId;
	ResultSet khoNhanRs;
	
	String nppId;
	ResultSet nppRs;
	ResultSet dvtRs;
	
	String maphieu;
	String kbhId;
	ResultSet kbhRs;

	ResultSet spRs;
	
	String lenhdieudong, lddcua, lddveviec, ngaylenhdieudong, sohopdong,ngayhopdong, nguoivanchuyen, ptvanchuyen;
	
	Hashtable<String, String> sanpham_soluong;
	
	String[] spId;
	String[] spMa;
	String[] spTen;
	String[] spDonvi;
	String[] spSoluong;
	String[] spGianhap;
	String[] spSolo;
	String[] spTonkho;
	String[] spBooked;
	String[] spNgaysanxuat;
	String[] spNgayhethan;
	
	dbutils db;
	

	int pageX= 0;

	int pageY = 0;
	
	
	
	public ErpChuyenkhoTT()
	{
		this.id = "";
		this.ngayyeucau = getDateTime();
		this.ghichu = "";
		this.khoNhanId = "";
		this.khoXuatId = "";
		this.nppId = "";
		this.kbhId = "";
		this.msg = "";
		this.trangthai = "0";
		
		this.lenhdieudong="";
		this.lddcua="";
		this.lddveviec="";
		this.ngaylenhdieudong="";
		this.sohopdong="";
		this.ngayhopdong="";
		this.nguoivanchuyen="";
		this.ptvanchuyen="";
		this.sochungtu="";
		
		this.sanpham_soluong = new Hashtable<String, String>();
		this.loaidonhang="0";
		this.db = new dbutils();
	}
	
	public ErpChuyenkhoTT(String id)
	{
		this.id = id;
		this.ngayyeucau = getDateTime();
		this.ghichu = "";
		this.khoNhanId = "";
		this.khoXuatId = "";
		this.nppId = "";
		this.kbhId = "";
		this.msg = "";
		this.trangthai = "0";
		
		this.lenhdieudong="";
		this.lddcua="";
		this.lddveviec="";
		this.ngaylenhdieudong="";
		this.sohopdong="";
		this.ngayhopdong="";
		this.nguoivanchuyen="";
		this.ptvanchuyen="";
		
		this.sanpham_soluong = new Hashtable<String, String>();
		this.sochungtu="";
		this.loaidonhang="0";
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
	
	public String getMsg() 
	{
		return this.msg;
	}

	public void setMsg(String msg) 
	{
		this.msg = msg;
	}

	
	public boolean createNK() 
	{
		if(this.ngayyeucau.trim().length() <= 0)
		{
			this.msg = "Vui lòng nhập ngày chuyển";
			return false;
		}
		
		if( this.khoXuatId.trim().length() <= 0 )
		{
			this.msg = "Vui lòng chọn kho xuất";
			return false;
		}
		
		if( this.nppId.trim().length() <= 0 )
		{
			this.msg = "Vui lòng chọn đối tượng nhận";
			return false;
		}
		
		if( this.kbhId.trim().length() <= 0 )
		{
			this.msg = "Vui lòng chọn kênh bán hàng";
			return false;
		}
		
		if( this.sochungtu.trim().length() <= 0 )
		{
			this.msg = "Vui lòng nhập số chứng từ";
			return false;
		}
		
		boolean coSP = false;
		for(int i = 0; i < spMa.length; i++)
		{
			if(spMa[i].trim().length() > 0 && spSoluong[i].trim().length() > 0  )
			{
				if( spSoluong[i].trim().length() > 0 )
				{
					coSP = true;
				}
			}
		}
		
		if(!coSP)
		{
			this.msg = "Vui lòng kiểm tra lại danh sách sản phẩm xuất";
			return false;
		}
		
		//CHECK TRUNG MA 
		for(int i = 0; i < spMa.length - 1; i++)
		{
			for(int j = i + 1; j < spMa.length; j++)
			{
				if(spMa[i].trim().length() > 0 && spMa[j].trim().length() > 0 )
				{
					if( spMa[i].trim().equals(spMa[j].trim()) )
					{
						this.msg = "Sản phẩm ( " + spTen[i] + " )  đã bị trùng.";
						return false;
					}
				}
			}
		}	
		
		try
		{
			db.getConnection().setAutoCommit(false);
		
			
			Object[] data = null;
			data = dbutils.setObject(this.sochungtu.trim());
		
			String query=" select count(*) as sl from  ERP_CHUYENKHO where sochungtu=? ";
			ResultSet rs=db.get_v2(query,data);
			rs.next();
			if(rs.getInt("sl")!=0)
			{
				this.msg = " số chứng từ này đã tồn tại " ;
				db.getConnection().rollback();
				return false;
			}
			rs.close();
			
			data = null;
			data = dbutils.setObject(this.sochungtu,this.ngayyeucau,this.ghichu,this.khoXuatId,this.kbhId,this.nppId
					,getDateTime(),this.userId,getDateTime(),this.userId,this.lenhdieudong,this.lddcua,this.lddveviec,this.ngaylenhdieudong
					,this.sohopdong,this.ngayhopdong,this.nguoivanchuyen,this.ptvanchuyen,loaidonhang);
			 query = " insert ERP_CHUYENKHO(SoChungTu,ngaychuyen, ghichu, trangthai, khoxuat_fk, kbh_fk, npp_fk, ngaytao, nguoitao, ngaysua, nguoisua," +
					       "        lenhdieudong, lddcua, lddveviec, ngaydieudong, sohopdong,ngayhopdong, nguoivanchuyen, ptvanchuyen,LoaiDonHang,tructhuoc_Fk) " +
						   " values(?,?, ?, '0', ?, ?, ?, ?, ?, ?, ?," +
						   "        ?, ?,?,?,?,?,?, ?,?,1 )";
			
			System.out.println("1.Insert CK: " + query);
			if(db.update_v2(query,data) < 0)
			{
				this.msg = "Không thể tạo mới ERP_CHUYENKHO " + query;
				db.getConnection().rollback();
				return false;
			}
			
			/*query = "select SCOPE_IDENTITY() as btId";
			ResultSet rsBtId = db.get(query);	
			
			rsBtId.next();*/
		    String ckCurrentId = db.getPk_seq();
		    //rsBtId.close();
			
		    if(Integer.parseInt(loaidonhang) == 0)
		    {
				int sonetInt =  Utility.getChungTuSonet(db,this.userId,"1" ,"DieuChuyen_SoChungTu","month('"+this.ngayyeucau+"')","year('"+this.ngayyeucau+"')",ckCurrentId,"ERP_CHUYENKHO" );
			    if(sonetInt <=0)
			    {
			    	this.db.getConnection().rollback();
					this.msg = "Không thể lấy số chứng từ tự động";
					return false;
			    }
			    
			    query = Utility.UpdateChungTuSoNet( "DieuChuyen_SoChungTu","1", ckCurrentId,this.ngayyeucau,  sonetInt,  "ERP_CHUYENKHO", "DC-"  );
			    
			    if(this.db.updateReturnInt(query)<=0)
				{
					this.db.getConnection().rollback();
					this.msg = "Số chứng từ ("+sonetInt+") đã phát sinh trong đơn gần nhất, vui lòng thử lại!";
					return false;
				}
			    
			    data = null;
				data = dbutils.setObject(sonetInt,ckCurrentId);
			    query = "\n update ERP_CHUYENKHO set sonetId ='DC-' + dbo.[PlusZero](?,5)  where pk_seq =   ?";
			    if( db.update_v2(query,data) <= 0 )
				{
					this.msg = "Không thể tạo mới ERP_YCXUATKHO " + query;
					db.getConnection().rollback();
					return false;
				}
		    }
		    else   if(Integer.parseInt(loaidonhang) == 4)// xuat kho khac
		    {
		    	int sonetInt =  Utility.getChungTuSonet(db,this.userId,"1" ,"PhieuXuat_SoChungTu","month('"+this.ngayyeucau+"')","year('"+this.ngayyeucau+"')",ckCurrentId,"ERP_CHUYENKHO" );
			    if(sonetInt <=0)
			    {
			    	this.db.getConnection().rollback();
					this.msg = "Không thể lấy số chứng từ tự động";
					return false;
			    }
			    
			    query = Utility.UpdateChungTuSoNet( "PhieuXuat_SoChungTu","1", ckCurrentId,this.ngayyeucau,  sonetInt,  "ERP_CHUYENKHO", "PX-"  );
			    if(this.db.updateReturnInt(query)<=0)
				{
					this.db.getConnection().rollback();
					this.msg = "Số chứng từ ("+sonetInt+") đã phát sinh trong đơn gần nhất, vui lòng thử lại!";
					return false;
				}
			    
			    data = null;
				data = dbutils.setObject(sonetInt,ckCurrentId);
			    query = "\n update ERP_CHUYENKHO set sonetId ='PX-' + dbo.[PlusZero](?,5)  where pk_seq =   ?";;
			    if( db.update_v2(query,data) <= 0 )
				{
					this.msg = "Không thể tạo mới ERP_YCXUATKHO " + query;
					db.getConnection().rollback();
					return false;
				}
		    }
			
			for(int i = 0; i < spMa.length; i++)
			{
				if(spMa[i].trim().length() > 0 && spSoluong[i].trim().length() > 0 )
				{
					data = null;
					data = dbutils.setObject(ckCurrentId,spSoluong[i].replaceAll(",", ""),spGianhap[i].replaceAll(",", "")
							,spDonvi[i].trim(),spMa[i].trim());
					query = "insert ERP_CHUYENKHO_SANPHAM( chuyenkho_fk, SANPHAM_FK, soluongchuyen, dongia, dvdl_fk ) " +
							"select ?, pk_seq, ?, ?, ISNULL( ( select pk_Seq from DONVIDOLUONG where donvi = ? ), DVDL_FK ) " +
							"from SANPHAM where MA = ? ";
					
					System.out.println("3.Insert CK - SP: " + query);
					if(db.update_v2(query,data) < 0)
					{
						this.msg = "Khong the tao moi ERP_CHUYENKHO_SANPHAM: " + query;
						db.getConnection().rollback();
						return false;
					}
					
					if(this.sanpham_soluong != null)
					{
						Enumeration<String> keys = this.sanpham_soluong.keys();
						double totalCT = 0;
						
						while(keys.hasMoreElements())
						{
							String key = keys.nextElement();
							
							if(key.startsWith( spMa[i] + "__" ) )
							{
								String[] _sp = key.split("__");
								
								String _soluongCT = "0"; 
								if(this.sanpham_soluong.get(key) != null)
								{
									_soluongCT = this.sanpham_soluong.get(key).replaceAll(",", "");
								}
								
								totalCT += Double.parseDouble(_soluongCT);
								
								if(!_soluongCT.equals("0"))
								{
									//CHECK TON KHO
									
									data = null;
									data = dbutils.setObject(this.khoXuatId,_sp[1],_sp[2],_sp[3],spMa[i]);
									query = "select AVAILABLE from ERP_KHOTT_SP_CHITIET where KHOTT_FK = ? and SOLO = ? and ngayhethan=? and ngaynhapkho=? " +
											"	and SANPHAM_FK = ( select pk_seq from SANPHAM where ma = ? ) ";
									ResultSet rsTK = db.get_v2(query,data);
									double avai = 0;
									if(rsTK.next())
									{
										avai = rsTK.getDouble("AVAILABLE");
									}
									rsTK.close();
									
									if( Double.parseDouble(_soluongCT) > avai )
									{
										this.msg = "Sản phẩm (" + spTen[i] + ") với số lô (" + _sp[1] + "), số lượng xuất (" + _soluongCT + ") còn tối đa (" + avai + "). ";
										db.getConnection().rollback();
										return false;
									}
									
									data = null;
									data = dbutils.setObject(ckCurrentId,_sp[1],_soluongCT.replaceAll(",", ""),_sp[2],_sp[3],spMa[i]);
									query = "insert ERP_CHUYENKHO_SANPHAM_CHITIET( chuyenkho_fk, SANPHAM_FK, solo, soluong,ngayhethan,ngaynhapkho ) " +
											"select ?, pk_seq, ?, ? ,?,? " +
											"from SANPHAM where MA = ? ";
									
									System.out.println("1.2.Insert YCXK - SP - CT: " + query);
									if(db.update_v2(query, data)<0)
									{
										this.msg = "Khong the tao moi ERP_CHUYENKHO_SANPHAM_CHITIET: " + query;
										db.getConnection().rollback();
										return false;
									}
									
									
									/*query = "Update ERP_KHOTT_SP_CHITIET set booked = booked + '" + _soluongCT.replaceAll(",", "") + "', AVAILABLE = AVAILABLE - '" + _soluongCT.replaceAll(",", "") + "' " +
											"where KHOTT_FK = '" + this.khoXuatId + "' and SOLO = '" + _sp[1] + "' and SANPHAM_FK = ( select pk_seq from SANPHAM where MA = '" + spMa[i] + "' ) and ngayhethan='"+_sp[2]+"' and ngaynhapkho='"+_sp[3]+"' ";
									if(!db.update(query))
									{
										this.msg = "Khong the cap nhat ERP_KHOTT_SP_CHITIET: " + query;
										db.getConnection().rollback();
										return false;
									}
									
									
									query = "Update ERP_KHOTT_SANPHAM set booked = booked + '" + _soluongCT.replaceAll(",", "") + "', AVAILABLE = AVAILABLE - '" + _soluongCT.replaceAll(",", "") + "' " +
											"where KHOTT_FK = '" + this.khoXuatId + "' and SANPHAM_FK = ( select pk_seq from SANPHAM where MA = '" + spMa[i] + "' )  ";
									if(!db.update(query))
									{
										this.msg = "Khong the cap nhat ERP_KHOTT_SANPHAM: " + query;
										db.getConnection().rollback();
										return false;
									}*/
								}
								
							}
						}
						
						//NEU TONG SO LUONG CT MA KHONG BANG TONG LUONG XUAT THI KO CHO LUU
						if(totalCT != Double.parseDouble(spSoluong[i].replaceAll(",", "").trim()) )
						{
							this.msg = "Tổng xuất theo lô của sản phẩm ( " + spTen[i] + " ) ( " + totalCT + " ) phải bằng tổng số lượng xuất ( " + spSoluong[i] + " ) ";
							db.getConnection().rollback();
							return false;
						}
						
					}
					
				}
			}
			
			db.getConnection().commit();
			db.getConnection().setAutoCommit(true);
		} 
		catch (Exception e) 
		{
			db.update("rollback");
			e.printStackTrace();
			this.msg = "Exception: " + e.getMessage();
			return false;
		}
		
		return true;
	}

	public boolean updateNK() 
	{
		if(this.ngayyeucau.trim().length() <= 0)
		{
			this.msg = "Vui lòng nhập ngày chuyển";
			return false;
		}
		
		if( this.khoXuatId.trim().length() <= 0 )
		{
			this.msg = "Vui lòng chọn kho xuất";
			return false;
		}
		
		if( this.nppId.trim().length() <= 0 )
		{
			this.msg = "Vui lòng chọn đối tượng nhận";
			return false;
		}
		
		if( this.kbhId.trim().length() <= 0 )
		{
			this.msg = "Vui lòng chọn kênh bán hàng";
			return false;
		}
		
		if( this.sochungtu.trim().length() <= 0 )
		{
			this.msg = "Vui lòng nhập số chứng từ";
			return false;
		}
		
		boolean coSP = false;
		for(int i = 0; i < spMa.length; i++)
		{
			if(spMa[i].trim().length() > 0 && spSoluong[i].trim().length() > 0  )
			{
				if( spSoluong[i].trim().length() > 0 )
				{
					coSP = true;
				}
			}
		}
		
		if(!coSP)
		{
			this.msg = "Vui lòng kiểm tra lại danh sách sản phẩm xuất";
			return false;
		}
		
		//CHECK TRUNG MA 
		for(int i = 0; i < spMa.length - 1; i++)
		{
			for(int j = i + 1; j < spMa.length; j++)
			{
				if(spMa[i].trim().length() > 0 && spMa[j].trim().length() > 0 )
				{
					if( spMa[i].trim().equals(spMa[j].trim()) )
					{
						this.msg = "Sản phẩm ( " + spTen[i] + " )  đã bị trùng.";
						return false;
					}
				}
			}
		}	
		
		try
		{
			db.getConnection().setAutoCommit(false);
			
			 if(Integer.parseInt(loaidonhang) == 0)
			 {
				if(!Utility.KiemTraNgayChungTuSoNet(db,this.userId,"1","DieuChuyen_SoChungTu",this.ngayyeucau, this.id,"ERP_CHUYENKHO" ))
				{
					this.db.getConnection().rollback();
					this.msg="Không được đổi ngày khác tháng với số chứng từ đã phát sinh!";
					return false;
				}
			 }
			 else if(Integer.parseInt(loaidonhang) == 4)// xuat kho khac
			 {
				 if(!Utility.KiemTraNgayChungTuSoNet(db,this.userId,"1","PhieuXuat_SoChungTu",this.ngayyeucau, this.id,"ERP_CHUYENKHO" ))
					{
						this.db.getConnection().rollback();
						this.msg="Không được đổi ngày khác tháng với số chứng từ đã phát sinh!";
						return false;
					}
			 }
			
			 
			 Object[] data = null;
				data = dbutils.setObject(this.sochungtu.trim(),this.id);
			
			 String query=" select count(*) as sl from  ERP_CHUYENKHO where sochungtu=? and pk_seq<> ?";
				ResultSet rs=db.get_v2(query,data);
				rs.next();
				if(rs.getInt("sl")!=0)
				{
					this.msg = " số chứng từ này đã tồn tại " ;
					db.getConnection().rollback();
					return false;
				}
				rs.close();
			
				data = null;
				data = dbutils.setObject(this.sochungtu,this.ngayyeucau,this.ghichu,this.khoXuatId,this.kbhId
						,this.nppId,getDateTime(),this.userId,this.lenhdieudong,this.lddcua,this.lddveviec,this.ngaylenhdieudong
						,this.sohopdong,this.ngayhopdong,this.nguoivanchuyen,this.ptvanchuyen,this.id);
			 query =	" Update ERP_CHUYENKHO set SoChungTu=?,ngaychuyen = ?, ghichu = ?, " +
							" khoxuat_fk = ?, kbh_fk = ?, npp_fk = ?, ngaysua = ?, nguoisua = ?," +
						    " lenhdieudong = ?, lddcua= ?, lddveviec=?, ngaydieudong =? ," +
						    " sohopdong = ? ,ngayhopdong =?, nguoivanchuyen=? , ptvanchuyen=?" + 
							" where pk_seq = ? ";
		
			if(db.update_v2(query,data) < 0)
			{
				this.msg = "Không thể cập nhật ERP_CHUYENKHO " + query;
				db.getConnection().rollback();
				return false;
			}
			// ko book kho nua 		
			/*//TANG KHO NGUOC LAI
			query = " update kho set kho.AVAILABLE = kho.AVAILABLE + CT.tongxuat, " +
					" 			   kho.BOOKED = kho.BOOKED - CT.tongxuat " +
					" from " +
					" ( " +
					" 	select a.khoxuat_fk as kho_fk, b.sanpham_fk, b.solo, SUM(b.soluong) as tongxuat,b.ngayhethan,b.ngaynhapkho  " +
					" 	from ERP_CHUYENKHO a inner join ERP_CHUYENKHO_SANPHAM_CHITIET b on a.pk_seq = b.chuyenkho_fk " +
					" 	where chuyenkho_fk = '" + this.id + "' " +
					" 	group by a.khoxuat_fk, b.solo, b.sanpham_fk,b.ngayhethan,b.ngaynhapkho " +
					" ) " +
					" CT inner join ERP_KHOTT_SP_CHITIET kho on CT.kho_fk = kho.KHOTT_FK  " +
					" 	and CT.sanpham_fk = kho.SANPHAM_FK and CT.solo = kho.SOLO and kho.ngayhethan=CT.ngayhethan and kho.ngaynhapkho=CT.ngaynhapkho ";
			if(!db.update(query))
			{
				this.msg = "Không thể cập nhật ERP_KHOTT_SP_CHITIET " + query;
				db.getConnection().rollback();
				return false;
			}*/
			
			/*query = " update kho set kho.AVAILABLE = kho.AVAILABLE + CT.tongxuat, " +
					" 			   kho.BOOKED = kho.BOOKED - CT.tongxuat " +
					" from " +
					" ( " +
					" 	select a.khoxuat_fk as kho_fk, b.sanpham_fk, SUM(b.soluong) as tongxuat  " +
					" 	from ERP_CHUYENKHO a inner join ERP_CHUYENKHO_SANPHAM_CHITIET b on a.pk_seq = b.chuyenkho_fk " +
					" 	where chuyenkho_fk = '" + this.id + "' " +
					" 	group by a.khoxuat_fk, b.sanpham_fk " +
					" ) " +
					" CT inner join ERP_KHOTT_SANPHAM kho on CT.kho_fk = kho.KHOTT_FK  " +
					" 	and CT.sanpham_fk = kho.SANPHAM_FK  ";
			if(!db.update(query))
			{
				this.msg = "Không thể cập nhật ERP_KHOTT_SANPHAM " + query;
				db.getConnection().rollback();
				return false;
			}*/
			
			data = null;
			data = dbutils.setObject(this.id);
			query = "delete ERP_CHUYENKHO_SANPHAM where chuyenkho_fk = ?";
			if(db.update_v2(query,data) < 0)
			{
				this.msg = "Không thể cập nhật ERP_CHUYENKHO_SANPHAM " + query;
				db.getConnection().rollback();
				return false;
			}
			
			data = null;
			data = dbutils.setObject(this.id);
			query = "delete ERP_CHUYENKHO_SANPHAM_CHITIET where chuyenkho_fk = ?";
			if(db.update_v2(query,data) < 0)
			{
				this.msg = "Không thể cập nhật ERP_CHUYENKHO_SANPHAM_CHITIET " + query;
				db.getConnection().rollback();
				return false;
			}
			
			for(int i = 0; i < spMa.length; i++)
			{
				if(spMa[i].trim().length() > 0 && spSoluong[i].trim().length() > 0 )
				{
					data = null;
					data = dbutils.setObject(this.id,spSoluong[i].replaceAll(",", ""),spGianhap[i].replaceAll(",", ""),spDonvi[i].trim(),spMa[i].trim());
					query = "insert ERP_CHUYENKHO_SANPHAM( chuyenkho_fk, SANPHAM_FK, soluongchuyen, dongia, dvdl_fk ) " +
							"select ?, pk_seq, ?, ?, ISNULL( ( select pk_Seq from DONVIDOLUONG where donvi = ? ), DVDL_FK ) " +
							"from SANPHAM where MA = ? ";
					
					System.out.println("3.Insert CK - SP: " + query);
					if(db.update_v2(query,data) < 0)
					{
						this.msg = "Khong the tao moi ERP_CHUYENKHO_SANPHAM: " + query;
						db.getConnection().rollback();
						return false;
					}
					
					if(this.sanpham_soluong != null)
					{
						Enumeration<String> keys = this.sanpham_soluong.keys();
						double totalCT = 0;
						
						while(keys.hasMoreElements())
						{
							String key = keys.nextElement();
							
							if(key.startsWith( spMa[i] + "__" ) )
							{
								String[] _sp = key.split("__");
								
								String _soluongCT = "0"; 
								if(this.sanpham_soluong.get(key) != null)
								{
									_soluongCT = this.sanpham_soluong.get(key).replaceAll(",", "");
								}
								
								if(!_soluongCT.equals("0"))
								{
									totalCT += Double.parseDouble(_soluongCT);
									
									/*//CHECK TON KHO
									query = "select AVAILABLE from ERP_KHOTT_SP_CHITIET where KHOTT_FK = '" + this.khoXuatId + "' and SOLO = '" + _sp[1] + "' and ngayhethan='"+_sp[2]+"' and ngaynhapkho='"+_sp[3]+"' " +
											"	and SANPHAM_FK = ( select pk_seq from SANPHAM where ma = '" + spMa[i] + "' ) ";
									System.out.println("so lo la "+query);
									ResultSet rsTK = db.get(query);
									double avai = 0;
									if(rsTK.next())
									{
										avai = rsTK.getDouble("AVAILABLE");
									}
									rsTK.close();
									
									if( Double.parseDouble(_soluongCT) > avai )
									{
										this.msg = "Sản phẩm (" + spTen[i] + ") với số lô (" + _sp[1] + "), số lượng xuất (" + _soluongCT + ") còn tối đa (" + avai + "). ";
										db.getConnection().rollback();
										return false;
									}*/
									
									data = null;
									data = dbutils.setObject(this.id,_sp[1]
											,_soluongCT.replaceAll(",", ""),_sp[2],_sp[3],spMa[i]);
									query = "insert ERP_CHUYENKHO_SANPHAM_CHITIET( chuyenkho_fk, SANPHAM_FK, solo, soluong,ngayhethan,ngaynhapkho ) " +
											"select ?, pk_seq, ?, ?,?,? " +
											"from SANPHAM where MA = ? ";
									
									System.out.println("1.2.Insert YCXK - SP - CT: " + query);
									if(db.update_v2(query,data) < 0)
									{
										this.msg = "Khong the tao moi ERP_CHUYENKHO_SANPHAM_CHITIET: " + query;
										db.getConnection().rollback();
										return false;
									}
									
									
									/*query = "Update ERP_KHOTT_SP_CHITIET set booked = booked + '" + _soluongCT.replaceAll(",", "") + "', AVAILABLE = AVAILABLE - '" + _soluongCT.replaceAll(",", "") + "' " +
											"where KHOTT_FK = '" + this.khoXuatId + "' and SOLO = '" + _sp[1] + "' and SANPHAM_FK = ( select pk_seq from SANPHAM where MA = '" + spMa[i] + "' ) and ngayhethan='"+_sp[2]+"' and ngaynhapkho='"+_sp[3]+"'  ";
									if(!db.update(query))
									{
										this.msg = "Khong the cap nhat ERP_KHOTT_SP_CHITIET: " + query;
										db.getConnection().rollback();
										return false;
									}*/
									
									
									/*query = "Update ERP_KHOTT_SANPHAM set booked = booked + '" + _soluongCT.replaceAll(",", "") + "', AVAILABLE = AVAILABLE - '" + _soluongCT.replaceAll(",", "") + "' " +
											"where KHOTT_FK = '" + this.khoXuatId + "' and SANPHAM_FK = ( select pk_seq from SANPHAM where MA = '" + spMa[i] + "' )  ";
									if(!db.update(query))
									{
										this.msg = "Khong the cap nhat ERP_KHOTT_SANPHAM: " + query;
										db.getConnection().rollback();
										return false;
									}*/
								}
								
							}
						}
						System.out.println("so luong la  ojaojaojoa "+ Double.parseDouble(spSoluong[i].replaceAll(",", "").trim()) +"------"+Float.parseFloat(spSoluong[i].replaceAll(",", "").trim()));
						//NEU TONG SO LUONG CT MA KHONG BANG TONG LUONG XUAT THI KO CHO LUU
						if(totalCT != Double.parseDouble(spSoluong[i].replaceAll(",", "").trim()) )
						{
							this.msg = "Tổng xuất theo lô của sản phẩm ( " + spTen[i] + " ) ( " + totalCT + " ) phải bằng tổng số lượng xuất ( " + spSoluong[i] + " ) ";
							db.getConnection().rollback();
							return false;
						}
						
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


	public void createRs() 
	{
		String query = "select PK_SEQ, TEN from ERP_KHOTT where trangthai = '1' ";
		this.khoXuatRs = db.get(query);
		
		query = "select PK_SEQ, TEN from NHAPHANPHOI where trangthai = '1' and tructhuoc_fk='1' and tructhuoc_fk='1' and loainpp <> 4 and pk_seq <>1 and isKHACHHANG=0 ";
		this.nppRs = db.get(query);
		
		this.dvtRs = db.getScrol("select PK_SEQ, DONVI from DONVIDOLUONG where trangthai = '1' ");
		this.kbhRs = db.getScrol("select PK_SEQ, TEN from KENHBANHANG where trangthai = '1' ");
		
	}

	private void initSANPHAM() 
	{
		String query =  
					"\n select b.pk_seq as pk_seq, b.MA as MA, b.TEN as TEN, DV.donvi AS DONVI, a.soluongchuyen as soluong, 0 as chietkhau, ISNULL(b.trongluong, 0) as trongluong, ISNULL(b.thetich, 0) as thetich, " +
					" \n 	ISNULL( ( select sum(available) from ERP_KHOTT_SANPHAM where KHOTT_FK = '"+ this.khoXuatId +"' and sanpham_fk = b.pk_seq ), 0 ) as avai,    " +
					//"	isnull((select [dbo].[GiaCkBanLeNppSanPham](1,null,b.pk_seq,'"+this.ngayyeucau+"')), 0) dongia"+
					"\n  a.dongia  "+
					"\n from ERP_CHUYENKHO_SANPHAM a inner Join SanPham b on a.SANPHAM_FK = b.PK_SEQ    " +
					"\n 		INNER JOIN DONVIDOLUONG DV ON DV.PK_SEQ = a.DVDL_FK       " +
					"\n where a.chuyenkho_FK = '" + this.id + "' ";
		
		System.out.println("---INIT SP TRONG INT SAN PHAM: " + query);
		ResultSet spRs = db.get(query);
		
		NumberFormat formater = new DecimalFormat("##,###,###");
		if(spRs != null)
		{
			try 
			{
				String spId = "";
				String spMA = "";
				String spTEN = "";
				String spDONVI = "";
				String spSOLUONG = "";
				String spTONKHO = "";
				String spGIANHAP = "";
				
				
				while(spRs.next())
				{
					spId += spRs.getString("PK_SEQ") + "__";
					spMA += spRs.getString("MA") + "__";
					spTEN += spRs.getString("TEN") + "__";
					spDONVI += spRs.getString("DONVI") + "__";
					spSOLUONG += formater.format(spRs.getDouble("SOLUONG")) + "__";
					spTONKHO += formater.format(spRs.getDouble("avai")) + "__";
					spGIANHAP += formater.format(spRs.getDouble("DONGIA")) + "__";
				}
				spRs.close();
				
				if(spMA.trim().length() > 0)
				{
					spId = spId.substring(0, spId.length() - 2);
					this.spId = spId.split("__");
					
					spMA = spMA.substring(0, spMA.length() - 2);
					this.spMa = spMA.split("__");
					
					spTEN = spTEN.substring(0, spTEN.length() - 2);
					this.spTen = spTEN.split("__");
					
					spDONVI = spDONVI.substring(0, spDONVI.length() - 2);
					this.spDonvi = spDONVI.split("__");
					
					spSOLUONG = spSOLUONG.substring(0, spSOLUONG.length() - 2);
					this.spSoluong = spSOLUONG.split("__");
					
					spTONKHO = spTONKHO.substring(0, spTONKHO.length() - 2);
					this.spTonkho = spTONKHO.split("__");
					
					spGIANHAP = spGIANHAP.substring(0, spGIANHAP.length() - 2);
					this.spGianhap = spGIANHAP.split("__");
					
				}
			} 
			catch (Exception e) 
			{
				System.out.println("115.Exception: " + e.getMessage());
			}
		}
		
	}

	public void init() 
	{
		String query =  " select isnull(sonetid, '')sonetid,loaidonhang,SoChungTu,ngaychuyen, ISNULL(ghichu, '') as ghichu, khoxuat_fk, kbh_fk, npp_fk, trangthai," +
						" lenhdieudong, lddcua, lddveviec, ngaydieudong, sohopdong,ngayhopdong, nguoivanchuyen, ptvanchuyen" +
						" from ERP_CHUYENKHO where pk_seq = '" + this.id + "'";
		System.out.println("____INIT NHAP KHO: " + query);
		ResultSet rs = db.get(query);
		if(rs != null)
		{
			try 
			{
				if(rs.next())
				{
					this.ngayyeucau = rs.getString("ngaychuyen");
					this.ghichu = rs.getString("ghichu");
					this.khoXuatId = rs.getString("khoxuat_fk");
					this.nppId = rs.getString("npp_fk");
					this.kbhId = rs.getString("kbh_fk");
					this.trangthai = rs.getString("trangthai");
					this.lenhdieudong =rs.getString("lenhdieudong")== null ? "":rs.getString("lenhdieudong") ;
					this.lddcua =rs.getString("lddcua")== null ? "":rs.getString("lddcua");
					this.lddveviec =rs.getString("lddveviec")== null ? "":rs.getString("lddveviec");
					this.ngaylenhdieudong =rs.getString("ngaydieudong")== null ? "":rs.getString("ngaydieudong");
					this.sohopdong =rs.getString("sohopdong")== null ? "":rs.getString("sohopdong");
					this.ngayhopdong =rs.getString("ngayhopdong")== null ? "":rs.getString("ngayhopdong");
					this.nguoivanchuyen =rs.getString("nguoivanchuyen")== null ? "":rs.getString("nguoivanchuyen");
					this.ptvanchuyen = rs.getString("ptvanchuyen")== null ? "":rs.getString("ptvanchuyen");
					this.sochungtu  = rs.getString("sochungtu")== null ? "":rs.getString("sochungtu");
					this.loaidonhang  = rs.getString("loaidonhang")== null ? "":rs.getString("loaidonhang");
					this.maphieu  = rs.getString("sonetid")== null ? "":rs.getString("sonetid");
				}
				rs.close();
				
				
				//INIT SO LUONG
				query = "select sanpham_fk, (select MA from SANPHAM where pk_seq = a.sanpham_fk ) as spMA,  solo, soluong,ngayhethan,ngaynhapkho " +
						"from ERP_CHUYENKHO_SANPHAM_CHITIET a where chuyenkho_fk = '" + this.id + "'";
				System.out.println("---INIT SP: " + query);
				rs = db.get(query);
				if(rs != null)
				{
					Hashtable<String, String> sp_soluong = new Hashtable<String, String>();
					while(rs.next())
					{
						System.out.println("---KEY BEAN: " +rs.getString("spMA") + "__" + rs.getString("solo")+ "__" + rs.getString("ngayhethan")+ "__" + rs.getString("ngaynhapkho"));
						sp_soluong.put(rs.getString("spMA") + "__" + rs.getString("solo")+ "__" + rs.getString("ngayhethan")+ "__" + rs.getString("ngaynhapkho"), rs.getString("soluong") );
					}
					rs.close();
					
					this.sanpham_soluong = sp_soluong;
				}
				
			} 
			catch (Exception e) 
			{
				System.out.println("---LOI INIT: " + e.getMessage());
			}
		}
		
		System.out.println("---KHO XUAT: " + this.khoXuatId);
		this.createRs();
		
		this.initSANPHAM();
		
	}

	public void DBclose() {
		
		try{
			
			if(khoNhanRs!=null){
				khoNhanRs.close();
			}
			
			this.db.shutDown();
			
		}catch(Exception er){
			
		}
	}
	
	public String getDateTime() 
	{
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date date = new Date();
        return dateFormat.format(date);	
	}

	public String getTrangthai() 
	{
		return this.trangthai;
	}

	public void setTrangthai(String trangthai) 
	{
		this.trangthai = trangthai;
	}

	public String getGhichu() {
		
		return this.ghichu;
	}

	public void setGhichu(String ghichu) {
		
		this.ghichu = ghichu;
	}

	
	public String[] getSpId() {
		
		return this.spId;
	}

	
	public void setSpId(String[] spId) {
		
		this.spId = spId;
	}

	
	public String[] getSpMa() {
		
		return this.spMa;
	}

	
	public void setSpMa(String[] spMa) {
		
		this.spMa = spMa;
	}

	
	public String[] getSpTen() {
		
		return this.spTen;
	}

	
	public void setSpTen(String[] spTen) {
		
		this.spTen = spTen;
	}

	
	public String[] getSpDonvi() {
		
		return this.spDonvi;
	}

	
	public void setSpDonvi(String[] spDonvi) {
		
		this.spDonvi = spDonvi;
	}

	
	public String[] getSpSoluong() {
		
		return this.spSoluong;
	}

	
	public void setSpSoluong(String[] spSoluong) {
		
		this.spSoluong = spSoluong;
	}

	
	public String[] getSpGianhap() {
		
		return this.spGianhap;
	}

	
	public void setSpGianhap(String[] spGianhap) {
		
		this.spGianhap = spGianhap;
	}

	
	public String[] getSpSolo() {
		
		return this.spSolo;
	}

	
	public void setSpSolo(String[] spSolo) {
		
		this.spSolo = spSolo;
	}

	
	public String[] getSpNgaysanxuat() {
		
		return this.spNgaysanxuat;
	}

	
	public void setSpNgaysanxuat(String[] spNgaysanxuat) {
		
		this.spNgaysanxuat = spNgaysanxuat;
	}

	
	public String[] getSpNgayhethan() {
		
		return this.spNgayhethan;
	}

	
	public void setSpNgayhethan(String[] spNgayhethan) {
		
		this.spNgayhethan = spNgayhethan;
	}

	
	public String[] getSpTonkho() {

		return this.spTonkho;
	}


	public void setSpTonkho(String[] spTonkho) {
		
		this.spTonkho = spTonkho;
	}

	
	public String[] getSpBooked() {

		return this.spBooked;
	}


	public void setSpBooked(String[] spBooked) {
		
		this.spBooked = spBooked;
	}

	
	public ResultSet getSanphamRs() {

		return this.spRs;
	}


	public void setSanphamRs(ResultSet spRs) {
		
		this.spRs = spRs;
	}

	
	public String getKhoXuatId() {
		
		return this.khoXuatId;
	}

	
	public void setKhoXuatId(String khoxuattt) {
		
		this.khoXuatId = khoxuattt;
	}

	
	public ResultSet getKhoXuatRs() {
		
		return this.khoXuatRs;
	}

	
	public void setKhoXuatRs(ResultSet khoxuatRs) {
		
		this.khoXuatRs = khoxuatRs;
	}

	
	public String getNppId() {
		
		return this.nppId;
	}

	
	public void setNppId(String nppId) {
		
		this.nppId = nppId;
	}

	
	public ResultSet getNppRs() {
		
		return this.nppRs;
	}

	
	public void setNppRs(ResultSet nppRs) {
		
		this.nppRs = nppRs;
	}

	
	public ResultSet getDvtRs() {

		return this.dvtRs;
	}


	public void setDvtRs(ResultSet dvtRs) {

		this.dvtRs = dvtRs;
	}

	
	public String getKbhId() {
		
		return this.kbhId;
	}

	
	public void setKbhId(String kbhId) {
		
		this.kbhId = kbhId;
	}

	
	public ResultSet getKbhRs() {
		
		return this.kbhRs;
	}

	
	public void setKbhRs(ResultSet kbhRs) {
		
		this.kbhRs = kbhRs;
	}

	public String getLenhdieudong() 
	{
		return this.lenhdieudong;
	}

	public void setLenhdieudong(String lenhdieudong) 
	{
		this.lenhdieudong =lenhdieudong;
		
	}

	public String getLDDcua() 
	{
		return this.lddcua;
	}

	public void setLDDcua(String LDDcua) 
	{
		this.lddcua= LDDcua;
		
	}

	public String getLDDveviec() 
	{
		return this.lddveviec;
	}

	public void setLDDveviec(String LDDveviec) 
	{
		this.lddveviec= LDDveviec;
		
	}

	public String getNgaydieudong() 
	{
		return this.ngaylenhdieudong;
	}

	public void setNgaydieudong(String ngaydieudong) 
	{
		this.ngaylenhdieudong =ngaydieudong;
		
	}

	public String getNguoivanchuyen() 
	{
		return this.nguoivanchuyen;
	}

	public void setNguoivanchuyen(String nguoivanchuyen) 
	{
		this.nguoivanchuyen = nguoivanchuyen;
		
	}

	public String getPtvanchuyen() 
	{
		return this.ptvanchuyen;
	}

	public void setPtvanchuyen(String ptvanchuyen) 
	{
		this.ptvanchuyen = ptvanchuyen;
		
	}

	public String getSohopdong() 
	{
		return this.sohopdong;
	}

	public void setSohopdong(String sohopdong) 
	{
		this.sohopdong = sohopdong;
		
	}

	public String getNgayhopdong() 
	{
		return this.ngayhopdong;
	}

	public void setNgayhopdong(String ngayhopdong) 
	{
		this.ngayhopdong = ngayhopdong;
		
	}
	
	public ResultSet getSoloTheoSp(String spMa, String tongluong,String ngayyeucau)
	{
		tongluong = tongluong.replaceAll(",", "");
		//System.out.println("---TONG LUONG: " + tongluong );
		/*
		String query = "select AVAILABLE + ISNULL( ( select sum(soluong) from ERP_CHUYENKHO_SANPHAM_CHITIET where chuyenkho_fk = '" + ( this.id.trim().length() > 0 ? this.id : "-1" ) + "' and sanpham_fk = ct.sanpham_fk and solo = ct.solo  ), 0 ) as AVAILABLE, NGAYHETHAN, SOLO,ngaynhapkho " +
					   "from ERP_KHOTT_SP_CHITIET ct " +
					   "where KHOTT_FK = '" + this.khoXuatId + "' and SANPHAM_FK = ( select pk_seq from SANPHAM where ma = '" + spMa + "' ) " +
					   		" and ( AVAILABLE + ISNULL( ( select sum(soluong) from ERP_CHUYENKHO_SANPHAM_CHITIET where chuyenkho_fk = '" + ( this.id.trim().length() > 0 ? this.id : "-1" ) + "' and sanpham_fk = ( select pk_seq from SANPHAM where ma = '" + spMa + "' ) and solo = ct.solo  ), 0 ) ) > 0 " +
					   " and ct.ngaynhapkho <='"+ngayyeucau+"'  order by NGAYHETHAN asc ";
*/		
		String query = "select AVAILABLE as AVAILABLE, NGAYHETHAN, SOLO,ngaynhapkho " +
				   "from ERP_KHOTT_SP_CHITIET ct " +
				   "where KHOTT_FK = '" + this.khoXuatId + "' and SANPHAM_FK = ( select pk_seq from SANPHAM where ma = '" + spMa + "' ) " +
				   		" and ( AVAILABLE + ISNULL( ( select sum(soluong) from ERP_CHUYENKHO_SANPHAM_CHITIET where chuyenkho_fk = '" + ( this.id.trim().length() > 0 ? this.id : "-1" ) + "' and sanpham_fk = ( select pk_seq from SANPHAM where ma = '" + spMa + "' ) and solo = ct.solo  ), 0 ) ) > 0 " +
				   " and ct.ngaynhapkho <='"+ngayyeucau+"'  order by NGAYHETHAN asc ";
	
		
		System.out.println("----LAY SO LO: " + query );
		String query2 = "";
		ResultSet rs = db.get(query);
		try 
		{
			double total = 0;
			boolean exit = false;
			while(rs.next())
			{
				double slg = 0;
				double avai = rs.getDouble("AVAILABLE");
				
				total += avai;
				
				if(total < Double.parseDouble(tongluong))
				{
					slg = avai;
				}
				else
				{
					slg =  Double.parseDouble(tongluong) - ( total - avai );
					exit = true;
				}
					
				if(slg >= 0)
				{
					query2 += "\n select '" + avai + "' as AVAILABLE, '" + rs.getString("NGAYHETHAN") + "' as NGAYHETHAN, '" + rs.getString("SOLO") + "' as SOLO, '" + slg + "' as tuDEXUAT,'" + rs.getString("ngaynhapkho") + "' as ngaynhapkho union ALL ";
					
				}
				else
				{
					query2 += "\n select '" + avai + "' as AVAILABLE, '" + rs.getString("NGAYHETHAN") + "' as NGAYHETHAN, '" + rs.getString("SOLO") + "' as SOLO, '' as tuDEXUAT,'" + rs.getString("ngaynhapkho") + "' as ngaynhapkho union ALL ";
				}
				if(exit)
					break;
			}
			rs.close();
		} 
		catch (Exception e) 
		{
			System.out.println("EXCEPTION INIT SOLO: " + e.getMessage());
		}
		
		if(query2.trim().length() > 0)
		{
			query2 = query2.substring(0, query2.length() - 10);
			/*String query3 =query2+ "\n union all "+
					  " select AVAILABLE + ISNULL( ( select sum(soluong) from ERP_CHUYENKHO_SANPHAM_CHITIET where chuyenkho_fk = '" + ( this.id.trim().length() > 0 ? this.id : "-1" ) + "' and sanpham_fk = ct.sanpham_fk and solo = ct.solo  ), 0 ) as AVAILABLE, NGAYHETHAN, SOLO,'' as tudexuat,ngaynhapkho " +
					   " from ERP_KHOTT_SP_CHITIET ct " +
					   " where KHOTT_FK = '" + this.khoXuatId + "' and SANPHAM_FK = ( select pk_seq from SANPHAM where ma = '" + spMa + "' ) " +
					   		" and ( AVAILABLE + ISNULL( ( select sum(soluong) from ERP_CHUYENKHO_SANPHAM_CHITIET where chuyenkho_fk = '" + ( this.id.trim().length() > 0 ? this.id : "-1" ) + "' and sanpham_fk = ( select pk_seq from SANPHAM where ma = '" + spMa + "' ) and solo = ct.solo  ), 0 ) ) > 0 " +
					   " and ct.ngaynhapkho <='"+ngayyeucau+"'  "+
					   " and SOLO+'_'+NGAYHETHAN+'_'+NGAYNHAPKHO  not in ( select SOLO+'_'+NGAYHETHAN+'_'+ngaynhapkho from ("+query2 +") as data)";
		*/
			String query3 =query2+ "\n union all "+
					  " select AVAILABLE as AVAILABLE, NGAYHETHAN, SOLO,'' as tudexuat,ngaynhapkho " +
					   " from ERP_KHOTT_SP_CHITIET ct " +
					   " where KHOTT_FK = '" + this.khoXuatId + "' and SANPHAM_FK = ( select pk_seq from SANPHAM where ma = '" + spMa + "' ) " +
					   		" and ( AVAILABLE + ISNULL( ( select sum(soluong) from ERP_CHUYENKHO_SANPHAM_CHITIET where chuyenkho_fk = '" + ( this.id.trim().length() > 0 ? this.id : "-1" ) + "' and sanpham_fk = ( select pk_seq from SANPHAM where ma = '" + spMa + "' ) and solo = ct.solo  ), 0 ) ) > 0 " +
					   " and ct.ngaynhapkho <='"+ngayyeucau+"'  "+
					   " and SOLO+'_'+NGAYHETHAN+'_'+NGAYNHAPKHO  not in ( select SOLO+'_'+NGAYHETHAN+'_'+ngaynhapkho from ("+query2 +") as data)";
		
			System.out.println("---TU DONG DE XUAT BIN - LO:1 " + query2 );
			return db.get(query3);
		}
		
		return null;
	}
	
	public Hashtable<String, String> getSanpham_Soluong() {
		
		return this.sanpham_soluong;
	}

	
	public void setSanpham_Soluong(Hashtable<String, String> sp_soluong) {
		
		this.sanpham_soluong = sp_soluong;
	}
	
	String sochungtu;
	public String getSoChungTu()
	{
		return sochungtu;
	}
	
	public String getMaphieu()
	{
		return maphieu;
	}
	
	public void setSoChungTu(String sochungtu)
	{
		this.sochungtu=sochungtu;
	}
	String loaidonhang;

	public String getLoaidonhang() {
		return loaidonhang;
	}

	public void setLoaidonhang(String loaidonhang) {
		this.loaidonhang = loaidonhang;
	}
	public int getPageX() {
		return pageX;
	}

	public void setPageX(int pageX) {
		this.pageX = pageX;
	}

	public int getPageY() {
		return pageY;
	}

	public void setPageY(int pageY) {
		this.pageY = pageY;
	}

}
