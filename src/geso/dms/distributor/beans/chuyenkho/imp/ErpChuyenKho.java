package geso.dms.distributor.beans.chuyenkho.imp;

import geso.dms.distributor.db.sql.dbutils;
import geso.dms.center.util.Utility;
import geso.dms.distributor.beans.chuyenkho.IErpChuyenKho;

import java.sql.ResultSet;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Enumeration;
import java.util.Hashtable;

public class ErpChuyenKho implements IErpChuyenKho
{
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
	
	String khId;
	ResultSet khRs;
	ResultSet dvtRs;
	
	String kbhId;
	ResultSet kbhRs;

	String lenhdieudong, lddcua, lddveviec, ngaylenhdieudong, sohopdong,ngayhopdong, nguoivanchuyen, ptvanchuyen;
	
	ResultSet spRs;
	
	
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
	
	String nppId;
	String nppTen;
	String sitecode;
	String sochungtu;
	String dungchung;
	Hashtable<String, String> sanpham_soluong;
	Utility util;
	dbutils db;
	
	public ErpChuyenKho()
	{
		this.id = "";
		this.ngayyeucau = getDateTime();
		this.ghichu = "";
		this.khoNhanId = "";
		this.khoXuatId = "";
		this.khId = "";
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
		
		this.sochungtu ="";
		this.dungchung = "";
		this.db = new dbutils();
		this.util= new Utility();
	}
	
	public ErpChuyenKho(String id)
	{
		this.id = id;
		this.ngayyeucau = getDateTime();
		this.ghichu = "";
		this.khoNhanId = "";
		this.khoXuatId = "";
		this.khId = "";
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
		
		this.sochungtu ="";
		this.dungchung = "";
		this.db = new dbutils();
		this.util= new Utility();
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
			this.msg = "Vui l??ng nh???p ng??y chuy???n";
			return false;
		}
		
		if( this.khoXuatId.trim().length() <= 0 )
		{
			this.msg = "Vui l??ng ch???n kho xu???t";
			return false;
		}
		
		if( this.khId.trim().length() <= 0 )
		{
			this.msg = "Vui l??ng ch???n ?????i t?????ng nh???n";
			return false;
		}
		
		if( this.kbhId.trim().length() <= 0 )
		{
			this.msg = "Vui l??ng ch???n k??nh b??n h??ng";
			return false;
		}
		
		if( this.sochungtu.trim().length() <= 0 )
		{
			this.msg = "Vui l??ng nh???p s??? ch???ng t???";
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
			this.msg = "Vui l??ng ki???m tra l???i danh s??ch s???n ph???m xu???t";
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
						this.msg = "S???n ph???m ( " + spTen[i] + " )  ???? b??? tr??ng.";
						return false;
					}
				}
			}
		}	
		
		if(this.sanpham_soluong == null)
		{
			this.msg = "Vui l??ng ki???m tra l???i s??? l?? xu???t";
			return false;
		}
		
		try
		{
			
			db.getConnection().setAutoCommit(false);
			String query = " insert ERP_CHUYENKHO(SoChungTu,ngaychuyen, ghichu, trangthai, khoxuat_fk, kbh_fk, tructhuoc_fk, npp_fk, ngaytao, nguoitao, ngaysua, nguoisua," +
			               " lenhdieudong, lddcua, lddveviec, ngaydieudong, sohopdong,ngayhopdong, nguoivanchuyen, ptvanchuyen,LoaiDonHang) " +
						   " values(?,?, ?, 9, ?, ?, ?,?, ?, ?,?,?," +
						   " ?, ?,?,?,?,?,?,?,4 )";
			Object[] data = null;
			data = dbutils.setObject(this.sochungtu,this.ngayyeucau,this.ghichu, this.khoXuatId, this.kbhId, this.nppId,
					this.khId,getDateTime(),this.userId,getDateTime(),this.userId,this.lenhdieudong, this.lddcua ,
					this.lddveviec,this.ngaylenhdieudong,this.sohopdong,this.ngayhopdong,this.nguoivanchuyen,this.ptvanchuyen);
			System.out.println("1.Insert CK: " + query);
			if(db.update_v2(query,data) !=1)
			{
				this.msg = "Kh??ng th??? t???o m???i ERP_CHUYENKHO " + query;
				db.getConnection().rollback();
				return false;
			}
			
			 
			this.id = this.db.getPk_seq();
			
			query =  "select isnull(dungchungkenh, 0) as dungchungkenh from NHAPHANPHOI where pk_seq = '" + this.nppId + "' ";
			ResultSet rsKENH = db.get(query);
			 this.dungchungkenh = "0";
			if(rsKENH != null)
			{
				try 
				{
					if(rsKENH.next())
					{
						dungchungkenh = rsKENH.getString("dungchungkenh");
					}
					rsKENH.close();
				} 
				catch (Exception e) {e.printStackTrace(); }
			}
			if(this.dungchungkenh.equals("1"))
			{
				 this.kbhId ="100025";
			}
			
			for(int i = 0; i < spMa.length; i++)
			{
				if(spMa[i].trim().length() > 0 && spSoluong[i].trim().length() > 0 )
				{
					
					query =    "select sp.ten, dv.donvi, case when sp.dvdl_fk != dv.pk_seq   " +
						   "	then ISNULL( ( select soluong2 / soluong1 from QUYCACH where SANPHAM_FK = sp.PK_SEQ and DVDL1_FK = sp.DVDL_FK and DVDL2_FK = dv.pk_seq ), -1 ) else 1 end as quycach   "  +
						   "from SANPHAM sp, DONVIDOLUONG dv " +
						   "where sp.MA = ? and dv.donvi = ?   ";
					data = null;
					data = dbutils.setObject(spMa[i].trim(),spDonvi[i].trim() );
					System.out.println("-----CHECK QUY CACH: " + query );
					ResultSet rs = db.get_v2(query,data);
					if(rs.next())
					{
						if(rs.getDouble("quycach") <= 0)
					{
							this.msg = "S???n ph???m ( " + rs.getString("ten") + " ) v???i ????n v??? ?????t ( " + rs.getString("donvi") + " ) ch??a thi???t l???p quy c??ch trong DLN ";
							rs.close();
							db.getConnection().rollback();
							return false;
						}
					}
					rs.close();
					
					
					query = "insert ERP_CHUYENKHO_SANPHAM( chuyenkho_fk, SANPHAM_FK, soluongchuyen, dongia, dvdl_fk ) " +
							"select ?, pk_seq, ?,?, " +
									" ISNULL( ( select pk_Seq from DONVIDOLUONG where donvi =? ), DVDL_FK ) " +
							"from SANPHAM where MA = ? ";
					data = null;
					data = dbutils.setObject(this.id,spSoluong[i].replaceAll(",", ""),spGianhap[i].replaceAll(",", ""),
							spDonvi[i].trim(),spMa[i].trim());
					System.out.println("3.Insert CK - SP: " + query);
					if(db.update_v2(query,data) !=1)
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
									query = "select AVAILABLE, NGAYHETHAN,NGAYNHAPKHO from NHAPP_KHO_CHITIET " +
											"where KHO_FK = ? and NPP_FK = ? " +
													" and KBH_FK = ? and SOLO = ? and NGAYHETHAN = ? and NGAYNHAPKHO = ? " +
											"	and SANPHAM_FK = ( select pk_seq from SANPHAM where ma = ? ) ";
									data = null;
									data = dbutils.setObject(this.khoXuatId,this.nppId,this.kbhId,
											_sp[1] ,_sp[2],_sp[3], spMa[i] );
									System.out.println("_____"+query);
									
									ResultSet rsTK = db.get_v2(query,data);
									double avai = 0;
									String ngayhethan = "";
									String ngaynhapkho = "";
									if(rsTK.next())
									{
										avai = rsTK.getDouble("AVAILABLE");
										ngayhethan = rsTK.getString("NGAYHETHAN");
										ngaynhapkho = rsTK.getString("NGAYNHAPKHO");
									}
									rsTK.close();
									
									if( Double.parseDouble(_soluongCT) > avai )
									{
										this.msg = "S???n ph???m (" + spTen[i] + ") v???i s??? l?? (" + _sp[1] + "), s??? l?????ng xu???t (" + _soluongCT + ") c??n t???i ??a (" + avai + "). ";
										db.getConnection().rollback();
										return false;
									}
									
									query = "insert ERP_CHUYENKHO_SANPHAM_CHITIET( chuyenkho_fk, SANPHAM_FK, solo, ngayhethan,ngaynhapkho, soluong ) " +
											"select ?, pk_seq, ?, ?, ?,? " +
											"from SANPHAM where MA = ? ";
									data = null;
									data = dbutils.setObject(this.id,_sp[1] ,ngayhethan,ngaynhapkho, _soluongCT.replaceAll(",", ""), spMa[i] );
									System.out.println("1.2.Insert YCCK - SP - CT: " + query);
									if(db.update_v2(query,data)!=1)
									{
										this.msg = "Khong the tao moi ERP_CHUYENKHO_SANPHAM_CHITIET: " + query;
										db.getConnection().rollback();
										return false;
									}
									
//									 
									query="select pk_seq from SANPHAM where MA = ? ";
									data = null;
									data = dbutils.setObject(spMa[i] );
									ResultSet spRs=db.get_v2(query,data);
									String spId="";
									while(spRs.next())
									{
										spId=spRs.getString("pk_seq");
									}
									this.msg=util.Update_NPP_Kho_Sp_Chitiet(this.ngayyeucau, "Xu???t kho kh??c", db, this.khoXuatId, spId, this.nppId, this.kbhId,
											_sp[1], ngayhethan, ngaynhapkho, 0.0, Double.parseDouble(_soluongCT.replaceAll(",", "")), (-1)*Double.parseDouble(_soluongCT.replaceAll(",", "")), (-1)*Double.parseDouble(_soluongCT.replaceAll(",", "")), 0.0);
									if(this.msg.length()>0)
									{
										db.getConnection().rollback();
										return false;
									}
									
									String ngaytaodh=getDateTime();
									geso.dms.distributor.util.Utility utility=new geso.dms.distributor.util.Utility();
									int		donhang_sau_ngay_03_2016 = utility.CompareDATE(ngaytaodh, "2016-03-04");
									if(donhang_sau_ngay_03_2016>=0)
									{
									
												String npp_fk=this.nppId;
												String kho_fk=this.khoXuatId;
												ResultSet rssp=db.get("select pk_seq from SANPHAM where MA = '" + spMa[i] + "'");
												rssp.next();
												String sanpham_fk=rssp.getString("pk_seq");
												rssp.close();
												String ngaynhap=this.ngayyeucau;
												double soluongcu=0;
												String checkkho=utility.kiemtra_xnt_denngay(npp_fk, kho_fk,  sanpham_fk, ngaynhap, db, this.kbhId,Double.parseDouble(_soluongCT.replaceAll(",", "")),"",soluongcu,this.userId);
												if(!checkkho.equals(""))
												{
													this.msg =checkkho;	
													db.getConnection().rollback();
													return false;
												}
												
									}
									
//									query = "Update NHAPP_KHO set booked = booked + '" + _soluongCT.replaceAll(",", "") + "', AVAILABLE = AVAILABLE - '" + _soluongCT.replaceAll(",", "") + "' " +
//											"where KHO_FK = '" + this.khoXuatId + "' and NPP_FK = '" + this.nppId + "' and KBH_FK = '" + this.kbhId + "' and SANPHAM_FK = ( select pk_seq from SANPHAM where MA = '" + spMa[i] + "' )  ";
//									System.out.println("---CAP NHAT BOOKED KHO TONG: " + query );
//									if(db.updateReturnInt(query)!=1)
//									{
//										this.msg = "Khong the cap nhat NHAPP_KHO: " + query;
//										db.getConnection().rollback();
//										return false;
//									}
									this.msg=util.Update_NPP_Kho_Sp(this.ngayyeucau, "Xu???t kho kh??c", db, this.khoXuatId, spId, this.nppId, this.kbhId , 0.0, Double.parseDouble(_soluongCT.replaceAll(",", "")), (-1)*Double.parseDouble(_soluongCT.replaceAll(",", "")), 0.0);
									if(this.msg.length()>0)
									{
										db.getConnection().rollback();
										return false;
									}
								}
								
							}
						}
						
						//NEU TONG SO LUONG CT MA KHONG BANG TONG LUONG XUAT THI KO CHO LUU
						if(totalCT != Double.parseDouble(spSoluong[i].replaceAll(",", "").trim()) )
						{
							this.msg = "T???ng xu???t theo l?? c???a s???n ph???m ( " + spTen[i] + " ) ( " + totalCT + " ) ph???i b???ng t???ng s??? l?????ng xu???t ( " + spSoluong[i] + " ) ";
							db.getConnection().rollback();
							return false;
						}
						
					}
					
				}
			}
			query = "Update ERP_CHUYENKHO set trangthai=0 " +
					"where pk_seq= "+this.id;
			if(!db.update(query))
			{
				msg = "Khong the cap nhat ERP_KHOTT_SANPHAM: " + query;
				db.getConnection().rollback(); 
				return false;
			}
			Utility util = new Utility();
			msg= util.Check_Huy_NghiepVu_KhoaSo("ERP_ChuyenKho", id, "NgayChuyen", nppId, db);
			if(msg.length()>0)
			{
				db.getConnection().rollback();
				return false;
			}
			msg= util.Check_Kho_Tong_VS_KhoCT(util.getIdNhapp(userId), db);
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
			this.msg = "Vui l??ng nh???p ng??y chuy???n";
			return false;
		}
		
		if( this.khoXuatId.trim().length() <= 0 )
		{
		this.msg = "Vui l??ng ch???n kho xu???t";
			return false;
		}
		
		if( this.khId.trim().length() <= 0 )
		{
			this.msg = "Vui l??ng ch???n ?????i t?????ng nh???n";
			return false;
		}
		
		if( this.kbhId.trim().length() <= 0 )
		{
			this.msg = "Vui l??ng ch???n k??nh b??n h??ng";
			return false;
		}
		
		if( this.sochungtu.trim().length() <= 0 )
		{
			this.msg = "Vui l??ng nh???p s??? ch???ng t???";
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
			this.msg = "Vui l??ng ki???m tra l???i danh s??ch s???n ph???m xu???t";
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
						this.msg = "S???n ph???m ( " + spTen[i] + " )  ???? b??? tr??ng.";
						return false;
					}
				}
			}
		}	
		
		if(this.sanpham_soluong == null)
		{
			this.msg = "Vui l??ng ki???m tra l???i s??? l?? xu???t";
			return false;
		}
		
		
		try
		{
			db.update("SET TRANSACTION ISOLATION LEVEL SNAPSHOT;");
			db.update("SET LOCK_TIMEOUT 60000;");
			db.getConnection().setAutoCommit(false);
		//TANG KHO NGUOC LAI
			
			
			
			String query=	" 	select a.ngaychuyen,b.ngaynhapkho,a.khoxuat_fk as kho_fk, a.tructhuoc_fk as npp_Fk, "+this.kbhId+" as kbh_fk, b.sanpham_fk, b.solo, SUM(b.soluong) as tongxuat,b.NgayHetHan  " +
			" 	from ERP_CHUYENKHO a inner join ERP_CHUYENKHO_SANPHAM_CHITIET b on a.pk_seq = b.chuyenkho_fk " +
			" 	where chuyenkho_fk = '" + this.id + "' " +
			" 	group by a.khoxuat_fk, a.tructhuoc_fk, a.kbh_fk, b.solo, b.sanpham_fk,b.NgayHetHan,a.ngaychuyen,b.ngaynhapkho " ;
			
			ResultSet nkRs= db.get(query);
		 	while (nkRs.next())
		 	{
		 		String kho_fk=nkRs.getString("kho_fk");
				String npp_fk=nkRs.getString("npp_fk");	
				String kbh_fk=nkRs.getString("kbh_fk");
				String sanpham_fk=nkRs.getString("sanpham_fk");
				String solo=nkRs.getString("solo");
				Double tongxuat=nkRs.getDouble("tongxuat");
				String NgayHetHan=nkRs.getString("NgayHetHan");
				String ngaynhapkho=nkRs.getString("ngaynhapkho");
				String ngaychuyen=nkRs.getString("ngaychuyen");
				
				msg=util.Update_NPP_Kho_Sp_Chitiet(ngaychuyen, "????n tr??? h??ng v??? NCC-TT duy???t", db, kho_fk, sanpham_fk, npp_fk, kbh_fk, solo, NgayHetHan, ngaynhapkho, 0.0, (-1)*tongxuat, tongxuat, tongxuat, 0.0);
				if(msg.length()>0)
				{
					db.getConnection().rollback();
					System.out.println("loi nay" +msg);
					return false;
				}
				msg=util.Update_NPP_Kho_Sp(ngaychuyen, "????n tr??? h??ng v??? NCC-UPDATE", db, kho_fk, sanpham_fk, npp_fk, kbh_fk, 0.0,  (-1)*tongxuat, tongxuat, 0.0);
				if(msg.length()>0)
				{
					db.getConnection().rollback();
					return false;
				}
		 	
	
		 	}
//			String
//			query = 
//					" update kho set kho.AVAILABLE = kho.AVAILABLE + CT.tongxuat, " +
//					" 			   kho.BOOKED = kho.BOOKED - CT.tongxuat " +
//					" from " +
//					" ( " +
//					" 	select a.khoxuat_fk as kho_fk, a.tructhuoc_fk as npp_Fk, "+this.kbhId+" as kbh_fk, b.sanpham_fk, b.solo, SUM(b.soluong) as tongxuat,b.NgayHetHan  " +
//					" 	from ERP_CHUYENKHO a inner join ERP_CHUYENKHO_SANPHAM_CHITIET b on a.pk_seq = b.chuyenkho_fk " +
//					" 	where chuyenkho_fk = '" + this.id + "' " +
//					" 	group by a.khoxuat_fk, a.tructhuoc_fk, a.kbh_fk, b.solo, b.sanpham_fk,b.NgayHetHan " +
//					" ) " +
//					" CT inner join NHAPP_KHO_CHITIET kho on CT.kho_fk = kho.KHO_FK  " +
//					" 	and CT.sanpham_fk = kho.SANPHAM_FK and CT.solo = kho.SOLO and CT.NPP_FK = kho.npp_fk and CT.KBH_FK = kho.kbh_fk  and ct.NgayHetHan=kho.NgayHetHan ";
//			System.out.println("---TANG KHO NGUOC LAI: " + query );
//			if(db.updateReturnInt(query)<=0)
//			{
//				this.msg = "Kh??ng th??? c???p nh???t NHAPP_KHO_CHITIET " + query;
//				db.getConnection().rollback();
//				return false;
//			}
//			
//			query = " update kho set kho.AVAILABLE = kho.AVAILABLE + CT.tongxuat, " +
//					" 			   kho.BOOKED = kho.BOOKED - CT.tongxuat " +
//					" from " +
//					" ( " +
//					" 	select a.khoxuat_fk as kho_fk, a.tructhuoc_fk as npp_fk, "+this.kbhId+" as kbh_fk , b.sanpham_fk, SUM(b.soluong) as tongxuat  " +
//					" 	from ERP_CHUYENKHO a inner join ERP_CHUYENKHO_SANPHAM_CHITIET b on a.pk_seq = b.chuyenkho_fk " +
//					" 	where chuyenkho_fk = '" + this.id + "' " +
//					" 	group by a.khoxuat_fk, a.tructhuoc_fk, a.kbh_fk, b.sanpham_fk " +
//					" ) " +
//					" CT inner join NHAPP_KHO kho on CT.kho_fk = kho.KHO_FK  " +
//					" 	and CT.sanpham_fk = kho.SANPHAM_FK and CT.NPP_FK = kho.npp_fk and CT.KBH_FK = kho.kbh_fk ";
//			System.out.println("---TANG KHO NGUOC LAI 2: " + query );
//			if(db.updateReturnInt(query)<=0)
//			{
//				this.msg = "Kh??ng th??? c???p nh???t NHAPP_KHO " + query;
//				db.getConnection().rollback();
//				return false;
//			}
			
			
			query =	" Update ERP_CHUYENKHO set trangthai=9,SoChungTu=?,ngaychuyen =?, ghichu = ?, " +
							" khoxuat_fk =?, kbh_fk = ?, npp_fk = ?, tructhuoc_fk = ?, ngaysua = ?, nguoisua =?, " + 
							" lenhdieudong = ?, lddcua= ?, lddveviec=?, ngaydieudong =? ," +
						    " sohopdong = ? ,ngayhopdong =?, nguoivanchuyen=? , ptvanchuyen=? " + 							
							" where pk_seq = ? ";
			Object[] data = null;
			data = dbutils.setObject(this.sochungtu,this.ngayyeucau,this.ghichu, this.khoXuatId,this.kbhId,
					this.khId,this.nppId,getDateTime(),this.userId,this.lenhdieudong, this.lddcua ,
					this.lddveviec,this.ngaylenhdieudong,this.sohopdong,this.ngayhopdong,this.nguoivanchuyen,this.ptvanchuyen,this.id);
			if(db.update_v2(query,data) !=1)
			{
				this.msg = "Kh??ng th??? c???p nh???t ERP_CHUYENKHO " + query;
				db.getConnection().rollback();
				return false;
			}
			
			query =  "select isnull(dungchungkenh, 0) as dungchungkenh from NHAPHANPHOI where pk_seq = '" + this.nppId + "' ";
			ResultSet rsKENH = db.get(query);
			 this.dungchungkenh = "0";
			if(rsKENH != null)
			{
				try 
				{
					if(rsKENH.next())
					{
						dungchungkenh = rsKENH.getString("dungchungkenh");
					}
					rsKENH.close();
				} 
				catch (Exception e) {e.printStackTrace(); }
			}
			if(this.dungchungkenh.equals("1"))
			{
				 this.kbhId ="100025";
			}
			
		
			query = "delete ERP_CHUYENKHO_SANPHAM where chuyenkho_fk = '" + this.id + "'";
			if(!db.update(query))
			{
				this.msg = "Kh??ng th??? c???p nh???t ERP_CHUYENKHO_SANPHAM " + query;
				db.getConnection().rollback();
				return false;
			}
			
			query = "delete ERP_CHUYENKHO_SANPHAM_CHITIET where chuyenkho_fk = '" + this.id + "'";
			if(!db.update(query))
			{
				this.msg = "Kh??ng th??? c???p nh???t ERP_CHUYENKHO_SANPHAM_CHITIET " + query;
				db.getConnection().rollback();
				return false;
			}
			
			for(int i = 0; i < spMa.length; i++)
			{
				if(spMa[i].trim().length() > 0 && spSoluong[i].trim().length() > 0 )
				{
					
					query =    "select sp.ten, dv.donvi, case when sp.dvdl_fk != dv.pk_seq   " +
						   "	then ISNULL( ( select soluong2 / soluong1 from QUYCACH where SANPHAM_FK = sp.PK_SEQ and DVDL1_FK = sp.DVDL_FK and DVDL2_FK = dv.pk_seq ), -1 ) else 1 end as quycach   "  +
						   "from SANPHAM sp, DONVIDOLUONG dv " +
						   "where sp.MA = ? and dv.donvi = ?   ";
				data = null;
				data = dbutils.setObject(spMa[i].trim(), spDonvi[i].trim() );
				System.out.println("-----CHECK QUY CACH: " + query );
				ResultSet rs = db.get_v2(query,data);
				if(rs.next())
				{
					if(rs.getDouble("quycach") <= 0)
					{
							this.msg = "S???n ph???m ( " + rs.getString("ten") + " ) v???i ????n v??? ?????t ( " + rs.getString("donvi") + " ) ch??a thi???t l???p quy c??ch trong DLN ";
						rs.close();
						db.getConnection().rollback();
						return false;
					}
				}
				rs.close();
					
					
					query = "insert ERP_CHUYENKHO_SANPHAM( chuyenkho_fk, SANPHAM_FK, soluongchuyen, dongia, dvdl_fk ) " +
							"select ?, pk_seq, ?, ?, ISNULL( ( select pk_Seq from DONVIDOLUONG where donvi =? ), DVDL_FK ) " +
							"from SANPHAM where MA = ? ";
					data = null;
					data = dbutils.setObject(this.id, spSoluong[i].replaceAll(",", ""),spGianhap[i].replaceAll(",", "") ,spDonvi[i].trim(),spMa[i].trim()  );
					System.out.println("3.Insert CK - SP: " + query);
					if(db.update_v2(query,data) !=1)
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
									query = "select AVAILABLE,NGAYNHAPKHO, NGAYHETHAN from NHAPP_KHO_CHITIET " +
											"where KHO_FK = '" + this.khoXuatId + "' and NPP_FK = '" + this.nppId + 
											"' and KBH_FK = '" + this.kbhId + "' and SOLO = ? and NGAYHETHAN = ? and NGAYNHAPKHO = ? " +
											"	and SANPHAM_FK = ( select pk_seq from SANPHAM where ma = ?) ";
									data = null;
									data = dbutils.setObject( _sp[1], _sp[2], _sp[3], spMa[i]);
									System.out.println("---CHECK TON KHO: " + query);
									ResultSet rsTK = db.get_v2(query,data);
									double avai = 0;
									String ngayhethan = "";
									String ngaynhapkho="";
									if(rsTK.next())
									{
										avai = rsTK.getDouble("AVAILABLE");
										ngayhethan = rsTK.getString("NGAYHETHAN");
										ngaynhapkho = rsTK.getString("NGAYNHAPKHO");
									}
									rsTK.close();
									
									if( Double.parseDouble(_soluongCT) > avai )
									{
										this.msg = "S???n ph???m (" + spTen[i] + ") v???i s??? l?? (" + _sp[1] + "), s??? l?????ng xu???t (" + _soluongCT + ") c??n t???i ??a (" + avai + "). ";
										db.getConnection().rollback();
										return false;
									}
									
									query = "insert ERP_CHUYENKHO_SANPHAM_CHITIET( chuyenkho_fk, SANPHAM_FK, solo, ngayhethan,ngaynhapkho, soluong ) " +
											"select '" + this.id + "', pk_seq, ?, ?, ?, ? " +
											"from SANPHAM where MA = ? ";
									data = null;
									data = dbutils.setObject( _sp[1], ngayhethan,ngaynhapkho,_soluongCT.replaceAll(",", ""), spMa[i]);
									System.out.println("1.2.Insert YCCK - SP - CT: " + query);
									
																		
									if(db.update_v2(query,data) !=1)
									{
										this.msg = "Khong the tao moi ERP_CHUYENKHO_SANPHAM_CHITIET: " + query;
										db.getConnection().rollback();
										return false;
									}
									
//									query = "Update NHAPP_KHO_CHITIET set booked = booked + '" + _soluongCT.replaceAll(",", "") + "', AVAILABLE = AVAILABLE - '" + _soluongCT.replaceAll(",", "") + "' " +
//											"where KHO_FK = '" + this.khoXuatId + "' and NPP_FK = '" + this.nppId + "' and KBH_FK = '" + this.kbhId + "' and SOLO = '" + _sp[1] + "' and SANPHAM_FK = ( select pk_seq from SANPHAM where MA = '" + spMa[i] + "' ) and NgayHetHan='"+ngayhethan+"' ";
//									System.out.println("---CAP NHAT BOOKED: " + query );
//									if(db.updateReturnInt(query)!=1)
//									{
//										this.msg = "Khong the cap nhat NHAPP_KHO_CHITIET: " + query;
//										db.getConnection().rollback();
//										return false;
//									}
									query="select pk_seq from SANPHAM where MA = ? ";
									data = null;
									data = dbutils.setObject( spMa[i]);
									String spId="";
									ResultSet spRs= db.get_v2(query,data);
									while (spRs.next())
									{
										spId=spRs.getString("pk_seq");
									}
									this.msg=util.Update_NPP_Kho_Sp_Chitiet(this.ngayyeucau, "Xu???t chuy???n kho kh??c - UPDATE", db, this.khoXuatId , spId, this.nppId, this.kbhId,
											_sp[1], ngayhethan, ngaynhapkho,0.0 , Double.parseDouble(_soluongCT.replaceAll(",", "")), (-1)*Double.parseDouble(_soluongCT.replaceAll(",", "")), (-1)*Double.parseDouble(_soluongCT.replaceAll(",", "")), 0.0);
									if(this.msg.length()>0)
									{
										db.getConnection().rollback();
										return false;
									}
									String sqldh="select ngaytao as NGAYGIO_TAO  from ERP_CHUYENKHO where pk_Seq="+this.id;
									String ngaytaodh="";
									ResultSet rsdh=db.get(sqldh);
									while (rsdh.next())
									{
										ngaytaodh=rsdh.getString("NGAYGIO_TAO");
									}
									rsdh.close();
									geso.dms.distributor.util.Utility utility=new geso.dms.distributor.util.Utility();
									int		donhang_sau_ngay_03_2016 = utility.CompareDATE(ngaytaodh, "2016-03-04");
									if(donhang_sau_ngay_03_2016>=0)
									{
									
												String npp_fk=this.nppId;
												String kho_fk=this.khoXuatId;
												query = "select pk_seq from SANPHAM where MA = ?";
												data = null;
												data = dbutils.setObject( spMa[i]);
												ResultSet rssp=db.get_v2(query,data);
												rssp.next();
												String sanpham_fk=rssp.getString("pk_seq");
												rssp.close();
												String ngaynhap=this.ngayyeucau;
												double soluongcu=0;
												String checkkho=utility.kiemtra_xnt_denngay(npp_fk, kho_fk,  sanpham_fk, ngaynhap, db, this.kbhId,Double.parseDouble(_soluongCT.replaceAll(",", "")),"",soluongcu,this.userId);
												if(!checkkho.equals(""))
												{
													this.msg =checkkho;	
													db.getConnection().rollback();
													return false;
												}
												
									}
//									query = "Update NHAPP_KHO set booked = booked + '" + _soluongCT.replaceAll(",", "") + "', AVAILABLE = AVAILABLE - '" + _soluongCT.replaceAll(",", "") + "' " +
//											"where KHO_FK = '" + this.khoXuatId + "' and NPP_FK = '" + this.nppId + "' and KBH_FK = '" + this.kbhId + "' and SANPHAM_FK = ( select pk_seq from SANPHAM where MA = '" + spMa[i] + "' )  ";
//									System.out.println("---CAP NHAT BOOKED KHO TONG: " + query );
//									if(db.updateReturnInt(query)!=1)
//									{
//										this.msg = "Khong the cap nhat NHAPP_KHO: " + query;
//										db.getConnection().rollback();
//										return false;
//									}
									this.msg=util.Update_NPP_Kho_Sp(this.ngayyeucau, "Xu???t kho kh??c -UPDATE", db, this.khoXuatId , 
											spId, this.nppId, this.kbhId, 0.0, Double.parseDouble(_soluongCT.replaceAll(",", "")), (-1)*Double.parseDouble(_soluongCT.replaceAll(",", "")), 0.0);
								
									if(this.msg.length()>0)
									{
										db.getConnection().rollback();
										return false;
										
									}
								}
								
							}
						}
						
						//NEU TONG SO LUONG CT MA KHONG BANG TONG LUONG XUAT THI KO CHO LUU
						if(totalCT != Double.parseDouble(spSoluong[i].replaceAll(",", "").trim()) )
						{
							this.msg = "T???ng xu???t theo l?? c???a s???n ph???m ( " + spTen[i] + " ) ( " + totalCT + " ) ph???i b???ng t???ng s??? l?????ng xu???t ( " + spSoluong[i] + " ) ";
							db.getConnection().rollback();
							return false;
						}
						
					}
					
				}				
			}
			query = "Update ERP_CHUYENKHO set trangthai=0 " +
					"where pk_seq= "+this.id;
			if(!db.update(query))
			{
				msg = "Khong the cap nhat ERP_KHOTT_SANPHAM: " + query;
				db.getConnection().rollback();
				return false;
			}
			
			Utility util = new Utility();
			msg = util.Check_Huy_NghiepVu_KhoaSo("ERP_ChuyenKho", id, "NgayChuyen",nppId, db);
			if(msg.length()>0)
			{
				db.getConnection().rollback();
				return false;
			}
			msg = util.Check_Kho_Tong_VS_KhoCT(util.getIdNhapp(userId), db);
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
			db.update("rollback");
			this.msg = "Exception: " + e.getMessage();
			e.printStackTrace();
			return false;
		}
		
		return true;
	}


	public void createRs() 
	{
		this.getNppInfo();
		System.out.println("---NPP ID: " + this.nppId);
		
		String query = "select PK_SEQ, TEN from KHO where trangthai = '1' and pk_seq in " + this.util.quyen_kho(this.userId)  ;
		this.khoXuatRs = db.get(query);
		
		/*
		 * HCM chuy???n h??ng cho CN/DT tr???c thu???c n??
		 * HCM chuy???n h??ng cho HO
		 * ?????ng Nai xu???t chuy???n h??ng ng?????c l???i cho HCM
		 */
		query = " select PK_SEQ,TEN from NHAPHANPHOI where TRANGTHAI=1 and tructhuoc_fk='"+this.nppId+"' ";
		
		System.out.println("___"+query);
		
		this.khRs = db.get(query);
		
		this.dvtRs = db.getScrol("select PK_SEQ, DONVI from DONVIDOLUONG where trangthai = '1' ");
		
		//CHECK DUNG CHUNG KENH HAY KHONG
		query =  "select isnull(dungchungkenh, 0) as dungchungkenh from NHAPHANPHOI where pk_seq = '" + this.nppId + "' ";
		ResultSet rsKENH = db.get(query);
		 this.dungchungkenh = "0";
		if(rsKENH != null)
		{
			try 
			{
				if(rsKENH.next())
				{
					dungchungkenh = rsKENH.getString("dungchungkenh");
				}
				rsKENH.close();
			} 
			catch (Exception e) {e.printStackTrace(); }
		}
		
	/*	if(dungchungkenh.equals("1"))
			this.kbhRs = db.getScrol("select PK_SEQ, TEN from KENHBANHANG where trangthai = '1' and pk_seq = '100025' ");
		else*/
		
			this.kbhRs = db.getScrol("select PK_SEQ, TEN from KENHBANHANG where trangthai = '1' ");
		
	}

	private void initSANPHAM() 
	{
		String kenhId="100025";
		if(!this.dungchungkenh.equals("1"))
		{
			 kenhId="(select KBH_FK from ERP_CHUYENKHO where PK_SEQ= '" + this.id + "')";
		}
		
		String query =  
					"select b.MA, b.TEN, DV.donvi, a.soluongchuyen as soluong, a.dongia, 0 as chietkhau, ISNULL(b.trongluong, 0) as trongluong, ISNULL(b.thetich, 0) as thetich ," +
					"       a.soluongchuyen + ISNULL( ( select sum(available) " +
					"                                   from NHAPP_KHO" +
					"                                   where KHO_FK = (select KHOXUAT_FK from ERP_CHUYENKHO where PK_SEQ= '" + this.id + "') and sanpham_fk = b.pk_seq " +
					"                                          and NPP_FK = (select NPP_FK from ERP_CHUYENKHO where PK_SEQ= '" + this.id + "') " +
					"                                          and KBH_FK ="+kenhId+"   ), 0 ) as avai " +
					" from ERP_CHUYENKHO_SANPHAM a inner Join SanPham b on a.SANPHAM_FK = b.PK_SEQ    " +
					" 		INNER JOIN DONVIDOLUONG DV ON DV.PK_SEQ = a.DVDL_FK       " +
					"where a.chuyenkho_FK = '" + this.id + "' ";
		
		System.out.println("---INIT SP: " + query);
		ResultSet spRs = db.get(query);
		
		NumberFormat formater = new DecimalFormat("##,###,###");
		if(spRs != null)
		{
			try 
			{
				String spMA = "";
				String spTEN = "";
				String spDONVI = "";
				String spSOLUONG = "";
				String spGIANHAP = "";
				String spTONKHO = "";
				
				
				while(spRs.next())
				{
					spMA += spRs.getString("MA") + "__";
					spTEN += spRs.getString("TEN") + "__";
					spDONVI += spRs.getString("DONVI") + "__";
					spSOLUONG += formater.format(spRs.getDouble("SOLUONG")) + "__";
					spGIANHAP += formater.format(spRs.getDouble("DONGIA")) + "__";
					spTONKHO += formater.format(spRs.getDouble("avai")) + "__";
				}
				spRs.close();
				
				if(spMA.trim().length() > 0)
				{
					spMA = spMA.substring(0, spMA.length() - 2);
					this.spMa = spMA.split("__");
					
					spTEN = spTEN.substring(0, spTEN.length() - 2);
					this.spTen = spTEN.split("__");
					
					spDONVI = spDONVI.substring(0, spDONVI.length() - 2);
					this.spDonvi = spDONVI.split("__");
					
					spSOLUONG = spSOLUONG.substring(0, spSOLUONG.length() - 2);
					this.spSoluong = spSOLUONG.split("__");
					
					spGIANHAP = spGIANHAP.substring(0, spGIANHAP.length() - 2);
					this.spGianhap = spGIANHAP.split("__");
					
					spTONKHO = spTONKHO.substring(0, spTONKHO.length() - 2);
					this.spTonkho = spTONKHO.split("__");
					
				}
			} 
			catch (Exception e) 
			{
				System.out.println("115.Exception: " + e.getMessage());
				e.printStackTrace();
			}
		}
		
	}

	public void init() 
	{
		String query =  "select SoChungTu,ngaychuyen, ISNULL(ghichu, '') as ghichu, khoxuat_fk, kbh_fk, tructhuoc_fk,npp_fk, trangthai, " +
						" lenhdieudong, lddcua, lddveviec, ngaydieudong, sohopdong,ngayhopdong, nguoivanchuyen, ptvanchuyen " +
						"from ERP_CHUYENKHO where pk_seq = '" + this.id + "'";
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
					this.khId = rs.getString("npp_Fk");
					this.kbhId = rs.getString("kbh_fk");
					this.trangthai = rs.getString("trangthai");
					this.lenhdieudong =rs.getString("lenhdieudong")== null ? "":rs.getString("lenhdieudong") ;
					this.lddcua =rs.getString("lddcua")== null ? "":rs.getString("lddcua");
					this.lddveviec =rs.getString("lddveviec")== null ? "":rs.getString("lddveviec");
					this.ngaylenhdieudong =rs.getString("ngaydieudong")== null ? "":rs.getString("ngaydieudong");
					this.sohopdong =rs.getString("sohopdong")== null ? "":rs.getString("sohopdong");
					this.ngayhopdong =rs.getString("ngayhopdong")== null ? "":rs.getString("ngayhopdong");
					this.nguoivanchuyen =rs.getString("nguoivanchuyen")== null ? "":rs.getString("nguoivanchuyen");
					this.ptvanchuyen =rs.getString("ptvanchuyen")== null ? "":rs.getString("ptvanchuyen");
					
					this.sochungtu =rs.getString("sochungtu")== null ? "":rs.getString("sochungtu");
				
				}
				rs.close();
				
				//INIT SO LUONG
				query = "select sanpham_fk, (select MA from SANPHAM where pk_seq = a.sanpham_fk ) as spMA,  solo, soluong,a.NgayHetHan,a.NGAYNHAPKHO " +
						"from ERP_CHUYENKHO_SANPHAM_CHITIET a where chuyenkho_fk = '" + this.id + "'";
				System.out.println("---INIT SP: " + query);
				rs = db.get(query);
				if(rs != null)
				{
					Hashtable<String, String> sp_soluong = new Hashtable<String, String>();
					while(rs.next())
					{
						//System.out.println("---KEY BEAN: " + rs.getString("sanpham_fk") + "__" + rs.getString("LOAI") + "__" + rs.getString("SCHEME") + "__" + rs.getString("solo"));
						sp_soluong.put(rs.getString("spMA") + "__" + rs.getString("solo") + "__" + rs.getString("NgayHetHan")+ "__" + rs.getString("NGAYNHAPKHO")  , rs.getString("soluong") );
					}
					rs.close();
					
					this.sanpham_soluong = sp_soluong;
				}
				
			} 
			catch (Exception e) 
			{
				System.out.println("---LOI INIT: " + e.getMessage());
				e.printStackTrace();
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
			
		}catch(Exception er)
		{
			er.printStackTrace();
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

	
	public String getKhId() {
		
		return this.khId;
	}

	
	public void setKhId(String khId) {
		
		this.khId = khId;
	}

	
	public ResultSet getKhRs() {
		
		return this.khRs;
	}

	
	public void setKhRs(ResultSet khRs) {
		
		this.khRs = khRs;
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
	
	private void getNppInfo()
	{		
		//Phien ban moi
		geso.dms.distributor.util.Utility util=new geso.dms.distributor.util.Utility();
		this.nppId=util.getIdNhapp(this.userId);
		this.nppTen=util.getTenNhaPP();
		//this.dangnhap = util.getDangNhap();
		this.sitecode=util.getSitecode();
	}

	
	public String getSoChungTu()
	{
		return sochungtu;
	}
	public void setSoChungTu(String sochungtu)
	{
		this.sochungtu=sochungtu;
	}

	String[] spNgayHetHan;

	public String[] getSpNgayHetHan()
	{
		return spNgayHetHan;
	}

	public void setSpNgayHetHan(String[] ngayHetHan)
    {
		this.spNgayHetHan = ngayHetHan;
    }
	
	
	public Hashtable<String, String> getSanpham_Soluong() {
		
		return this.sanpham_soluong;
	}

	
	public void setSanpham_Soluong(Hashtable<String, String> sp_soluong) {
		
		this.sanpham_soluong = sp_soluong;
	}
	
	public ResultSet getSoloTheoSp(String spMa, String tongluong)
	{
		if(this.nppId.trim().length() > 0 && this.kbhId.trim().length() > 0 )
		{
			tongluong = tongluong.replaceAll(",", "");
			
			String kenhId="100025";
			if(!this.dungchungkenh.equals("1"))
			{
				 kenhId=this.kbhId ;
			}
			
			//System.out.println("---TONG LUONG: " + tongluong );
			
			String query = 
							"select AVAILABLE + ISNULL( ( select sum(soluong) from ERP_CHUYENKHO_SANPHAM_CHITIET where chuyenkho_fk = '" + ( this.id.trim().length() > 0 ? this.id : "-1" ) + "' and sanpham_fk = ct.sanpham_fk and solo = ct.solo and ngaynhapkho=ct.ngaynhapkho  and NgayHetHan=ct.NgayHetHan ), 0 ) as AVAILABLE, NGAYHETHAN,NGAYNHAPKHO, SOLO " +
						   "from NHAPP_KHO_CHITIET ct " +
						   "where KHO_FK = '" + this.khoXuatId + "' and KBH_FK ="+kenhId+"  and NPP_FK = '" + this.nppId + "' and ngaynhapkho<='"+this.ngayyeucau+"' and SANPHAM_FK = ( select pk_seq from SANPHAM where ma = '" + spMa + "' ) " +
						   " and ( AVAILABLE + ISNULL( ( select sum(soluong) from ERP_CHUYENKHO_SANPHAM_CHITIET where chuyenkho_fk = '" + ( this.id.trim().length() > 0 ? this.id : "-1" ) + "' and sanpham_fk = ( select pk_seq from SANPHAM where ma = '" + spMa + "' ) and solo = ct.solo and ngaynhapkho=ct.ngaynhapkho  and NgayHetHan=ct.NgayHetHan   ), 0 ) ) > 0 " +
						   " order by NGAYHETHAN asc ";
			
			System.out.println("----LAY SO LO: " + query );
			String query2 = "";
			ResultSet rs = db.get(query);
			try 
			{
				double total = 0;
				
				while(rs.next())
				{
					double slg = 0;
					double avai = rs.getDouble("AVAILABLE");
					String ngaynhapkho= rs.getString("Ngaynhapkho");
					total += avai;
					
					if(total < Double.parseDouble(tongluong))
					{
						slg = avai;
					}
					else
					{
						slg =  Double.parseDouble(tongluong) - ( total - avai );
					}
						
					if(slg >= 0)
					{
						query2 += "select '" + ngaynhapkho + "' as ngaynhapkho,'" + avai + "' as AVAILABLE, '" + rs.getString("NGAYHETHAN") + "' as NGAYHETHAN, '" + rs.getString("SOLO") + "' as SOLO, '" + slg + "' as tuDEXUAT union ALL ";
					}
					else
					{
						query2 += "select '" + ngaynhapkho + "' as ngaynhapkho,'" + avai + "' as AVAILABLE, '" + rs.getString("NGAYHETHAN") + "' as NGAYHETHAN, '" + rs.getString("SOLO") + "' as SOLO, '' as tuDEXUAT union ALL ";
					}
					
				}
				rs.close();
			} 
			catch (Exception e) 
			{
				System.out.println("EXCEPTION INIT SOLO: " + e.getMessage());
				e.printStackTrace();
			}
			
			if(query2.trim().length() > 0)
			{
				query2 = query2.substring(0, query2.length() - 10);
				//System.out.println("---TU DONG DE XUAT BIN - LO: " + query2 );
				return db.get(query2);
			}
		}
		
		return null;
	}
	
	String dungchungkenh;

	public String getDungchungkenh()
  {
  	return dungchungkenh;
  }

	public void setDungchungkenh(String dungchungkenh)
  {
  	this.dungchungkenh = dungchungkenh;
  }
}
