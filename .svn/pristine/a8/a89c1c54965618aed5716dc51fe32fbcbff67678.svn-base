package geso.dms.distributor.beans.hangtralai.imp;

import geso.dms.distributor.beans.hangtralai.IErpHangTraLaiNpp;
import geso.dms.center.db.sql.dbutils;
import geso.dms.center.util.Utility;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Hashtable;



public class ErpHangTraLaiNpp implements IErpHangTraLaiNpp
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
	String tienvat;
	
	String TraNguyenDon  ="0";
	String donhangId = "";
	ResultSet donhangRs;
	ResultSet infoRs;
	String tonggiatridonhang = "";
	public String getTienvat() {
		return tienvat;
	}

	public void setTienvat(String tienvat) {
		this.tienvat = tienvat;
	}

	String so;

	public String getSo() {
		return so;
	}

	public void setSo(String so) {
		this.so = so;
	}

	String kbhId;
	ResultSet kbhRs;
	ResultSet ddkdRs;

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


	String nppId;
	String nppTen;
	String sitecode;
	String sochungtu;

	Hashtable<String, String> sanpham_soluong;

	dbutils db;
	Utility util;

	public ErpHangTraLaiNpp()
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
		this.so="";
		this.lenhdieudong="";
		this.lddcua="";
		this.lddveviec="";
		this.ngaylenhdieudong="";
		this.sohopdong="";
		this.ngayhopdong="";
		this.nguoivanchuyen="";
		this.ptvanchuyen="";
		this.tienvat="";

		this.sanpham_soluong = new Hashtable<String, String>();

		this.sochungtu ="";
		this.doituong ="";
		this.NgayHoaDon="";
		this.KyHieu="";
		this.SoHoaDon="";
		this.ddkdid ="";
		this.dtId="";
		this.db = new dbutils();
		this.util = new Utility();
	}

	public ErpHangTraLaiNpp(String id)
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
		this.doituong ="";
		this.NgayHoaDon="";
		this.KyHieu="";
		this.SoHoaDon="";
		this.ddkdid ="";
		this.dtId="";
		this.db = new dbutils();
		this.util = new Utility();
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

	public boolean createTraNguyenDon()
	{
		try
		{
			db.getConnection().setAutoCommit(false);

			String query ="\n insert into Erp_HangTraLaiNpp(GSBH_FK,DDKD_FK,TrangThai,npp_fk,khachhang_fk,npptra_fk,doituong,ngaytra,ghichu,kbh_fk,kho_fk,SoHoaDon,KyHieu,NgayTao,NgaySua,NguoiTao,NguoiSua,TienTruocThue,TienSauThue,TienThue,NgayHoaDon,so,donhang_fk)  " +
						  "\n select GSBH_FK,DDKD_FK,0,'"+this.nppId+"',khachhang_fk,null,1,'"+this.ngayyeucau+"',N'"+this.ghichu+"',KBH_FK,KHO_FK,'"+this.SoHoaDon+"','"+this.KyHieu+"','"+this.getDateTime()+"','"+this.getDateTime()+"','"+this.userId+"','"+this.userId+"',tonggiatri,tonggiatri,0,'"+this.NgayHoaDon+"',' "+this.so+"',pk_seq" +
						  "\n from donhang dh " +
						  "\n where dh.pk_seq = "+this.donhangId+" and  dh.npp_fk = "+this.nppId+"  and dh.trangthai = 1  and exists ( select 1 from hoadon_DDH x where DDH_FK = dh.pk_seq and exists (select 1 from hoadon where trangthai in (2,4) and loaihoadon=0 and pk_seq = x.hoadon_fk )) " +
						  "\n 	and not exists (select 1 from erp_hangtralainpp where trangthai !=2 and donhang_fk = dh.pk_seq  )	";

						  ;
			System.out.println("vao day :"+query);
			if(db.updateReturnInt(query)<=0)
			{
				this.msg = " Đơn hàng chưa chốt hóa đơn tài chính hoặc không tồn tại: " + query;
				db.getConnection().rollback();
				return false;
			}

			query = "select SCOPE_IDENTITY() as hdId";
			ResultSet rs1 = db.get(query);
			rs1.next();
			this.id = rs1.getString("hdId");
			rs1.close();
			
			//Check khoá sổ
			geso.dms.distributor.util.Utility dutil = new geso.dms.distributor.util.Utility();
			this.msg = util.Check_Huy_NghiepVu_KhoaSo("Erp_HangTraLaiNpp", this.id, "ngaytra", db);
			if( this.msg.length() > 0)
			{
				db.getConnection().rollback();
				//db.getConnection().setAutoCommit(true);
				return false;
			}
			
			String queryngay = "select count(*) dem from donhang a inner join erp_hangtralainpp b on a.pk_Seq=b.donhang_fk where a.ngaynhap > b.ngaytra and a.pk_seq ="+this.donhangId;
			ResultSet rsngay = db.get(queryngay);
			int checkngay=0;
			rsngay.next();
			checkngay = rsngay.getInt("dem");
			rsngay.close();
			if(checkngay != 0){
				this.msg = "Ngày nhập trả không được trước ngày đơn hàng!";
				db.getConnection().rollback();
				return false;
			}

			/*this.msg = dutil.Check_Huy_NghiepVu_KhoaSo("Erp_HangTraLaiNpp", this.id, "ngaytra", db);
			if( this.msg.length() > 0)
			{
				db.getConnection().rollback();
				db.getConnection().setAutoCommit(true);
				return false;
			}*/

			query = " select quanlykho from NHAPHANPHOI where PK_SEQ =(select NPP_FK from Erp_HangTraLaiNpp where pk_seq = "+this.id+" )  "  ;
			
			 rs1 = db.get(query);
			 rs1.next();
			 String quanlykho = rs1.getString("quanlykho");
			 rs1.close();
			 
			 if(quanlykho.equals("1"))
			 {
					query = 
						 "	insert into Erp_HangTraLaiNpp_SanPham(HangTraLai_fk,kho_fk,kbh_fk,Sanpham_fk,Dvdl_Fk,SoLuong,DonGia,SoLo,NgayHetHan,VAT) "+
						 "\n select "+this.id +",kho_fk,kbh_fk,sanpham_fk,sp.dvdl_fk,sum(soluong)soluong, giamua,solo,ngayhethan,max(thuevat)thuevat " +
						 "\n from  " + 
						 "\n (  " + 
						 "\n 	select dhsp.kho_fk,case npp.DUNGCHUNGKENH  when 1 then 100025 else dh.kbh_fk end kbh_fk,dhsp.sanpham_fk,dhsp.solo,dhsp.ngayhethan,gia.giamua,gia.thuevat,dhsp.soluong  " + 
						 "\n 	from donhang_sanpham_chitiet dhsp  " + 
						 "\n 	inner join donhang_sanpham gia on dhsp.donhang_fk = gia.donhang_fk and dhsp.sanpham_fk = gia.sanpham_fk  " + 
						 "\n 	inner join donhang dh on dh.pk_seq = dhsp.donhang_fk  " + 
						 "\n 	inner join nhaphanphoi npp on npp.pk_seq = dh.npp_fk  " + 
						 "\n 	where dhsp.donhang_fk =  " + this.donhangId +
						 "\n 	union all  " + 
						 "\n 	select dhsp.kho_fk, case npp.DUNGCHUNGKENH  when 1 then 100025 else dh.kbh_fk end KBH_FK,dhsp.sanpham_fk,dhsp.solo,dhsp.ngayhethan,0 thue,(select top 1 thuevat from donhang_sanpham where donhang_fk = dh.pk_seq) thue,dhsp.soluong  " + 
						 "\n 	from donhang_ctkm_trakm_chitiet dhsp  " + 
						 "\n 	inner join donhang dh on dh.pk_seq = dhsp.donhang_fk  " + 
						 "\n 	inner join nhaphanphoi npp on npp.pk_seq = dh.npp_fk  " + 
						 "\n 	where dhsp.donhang_fk =" + this.donhangId +
						 "\n )kq inner join sanpham sp on sp.pk_seq = kq.sanpham_fk  " + 
						 "\n group by   kho_fk,kbh_fk,sanpham_fk,solo,ngayhethan, giamua,sp.dvdl_fk  " ;
			 }
			 else
			 {
				 query = 
					 "	insert into Erp_HangTraLaiNpp_SanPham(HangTraLai_fk,kho_fk,kbh_fk,Sanpham_fk,Dvdl_Fk,SoLuong,DonGia,SoLo,NgayHetHan,VAT) "+
					 "\n select "+this.id +",kho_fk,kbh_fk,sanpham_fk,sp.dvdl_fk,sum(soluong)soluong, giamua,solo,ngayhethan,max(thuevat)thuevat " +
					 "\n from  " + 
					 "\n (  " + 
					 "\n 	select dh.kho_fk,case npp.DUNGCHUNGKENH  when 1 then 100025 else dh.kbh_fk end kbh_fk " +
					 "\n		,dhsp.sanpham_fk,'NA'Solo,'2030-12-31' ngayhethan ,dhsp.giamua,dhsp.thuevat,dhsp.soluong  " + 
					 "\n 	from donhang_sanpham dhsp  " + 
					 "\n 	inner join donhang dh on dh.pk_seq = dhsp.donhang_fk  " + 
					 "\n 	inner join nhaphanphoi npp on npp.pk_seq = dh.npp_fk  " + 
					 "\n 	where dhsp.donhang_fk =  " + this.donhangId +
					 "\n 	union all  " + 
					 "\n 	select dhsp.kho_fk, case npp.DUNGCHUNGKENH  when 1 then 100025 else dh.kbh_fk end KBH_FK,sp.pk_seq sanpham_fk" +
					 "\n		,'NA'Solo,'2030-12-31' ngayhethan,0 gia,ISNULL( (select top 1 thuevat from DONHANG_SANPHAM where DONHANG_FK = dhsp.DONHANGID ),sp.PT_VAT),dhsp.soluong  " + 
					 "\n 	from donhang_ctkm_trakm dhsp " +
					 "\n	inner join sanpham sp on sp.ma = dhsp.spma  " + 
					 "\n 	inner join donhang dh on dh.pk_seq = dhsp.donhangid  " + 
					 "\n 	inner join nhaphanphoi npp on npp.pk_seq = dh.npp_fk  " + 
					 "\n 	where dhsp.donhangid =" + this.donhangId +
					 "\n )kq inner join sanpham sp on sp.pk_seq = kq.sanpham_fk  " + 
					 "\n group by   kho_fk,kbh_fk,sanpham_fk,solo,ngayhethan, giamua,sp.dvdl_fk  " ;
			 }
			System.out.println("3.Insert CK - SP: " + query);
			if(!db.update(query))
			{
				this.msg = "Khong the tao moi Erp_HangTraLaiNpp_SanPham: " + query;
				db.getConnection().rollback();
				return false;
			}

			String kqVat = geso.dms.center.util.Utility.CheckVat( db , "Erp_HangTraLaiNpp_SanPham","VAT", "HangTraLai_fk", this.id );
			if(kqVat.trim().length() > 0)
			{
				this.msg = kqVat;
				db.getConnection().rollback();
				db.getConnection().setAutoCommit(true);
				return false;
			}

		


			db.getConnection().commit();
			db.getConnection().setAutoCommit(true);
			return true;
		} 
		catch (Exception e) 
		{
			db.update("rollback");
			e.printStackTrace();
			this.msg = "Exception: " + e.getMessage();
			return false;
		}
	}
	public boolean createNK() 
	{
		
		if (!this.TraNguyenDon.equals("1")) {
			if(this.ddkdid == null || this.ddkdid.trim().length() <= 0)
			{
				this.msg = "Vui lòng chọn NVBH";
				return false;
			}
		}
		
		if(this.ngayyeucau.trim().length() <= 0)
		{
			this.msg = "Vui lòng nhập ngày chuyển";
			return false;
		}

		if(this.TraNguyenDon.equals("1"))
		{
			return createTraNguyenDon();
		}
		

		if( this.khoXuatId.trim().length() <= 0 )
		{
			this.msg = "Vui lòng chọn kho xuất";
			return false;
		}

		if( this.doituong.trim().length() <= 0 )
		{
			this.msg = "Vui lòng chọn đối tượng";
			return false;
		}


		if( this.khId.trim().length() <= 0 && this.dtId.length()<=0 )
		{
			this.msg = "Vui lòng chọn đối tượng nhận";
			return false;
		}

		if( this.kbhId.trim().length() <= 0 )
		{
			this.msg = "Vui lòng chọn kênh bán hàng";
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

				if( spSolo[i].trim().length() > 0 &&  spNgayHetHan[i].trim().length() > 0  )
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
					if( spMa[i].trim().equals(spMa[j].trim()) &&  spSolo[i].trim().equals(spSolo[j].trim()) && spNgayHetHan[i].trim().equals(spNgayHetHan[j].trim()  ) )
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
			data = dbutils.setObject(this.nppId,(this.khId.length()<=0?"NULL":this.khId),(this.dtId.length()<=0?"NULL":this.dtId)
					,this.doituong,this.ngayyeucau,this.ghichu,this.kbhId,this.khoXuatId,this.SoHoaDon,this.KyHieu,this.NgayHoaDon,this.so);
			String query ="	insert into Erp_HangTraLaiNpp(TrangThai,npp_fk,khachhang_fk,npptra_fk,doituong,ngaytra,ghichu,kbh_fk,kho_fk,SoHoaDon,KyHieu,NgayTao,NgaySua,NguoiTao,NguoiSua,TienTruocThue,TienSauThue,TienThue,NgayHoaDon,so)  " +
						  " select 0,?,?,?,?,?,?,?,?,?,?,'"+this.getDateTime()+"','"+this.getDateTime()+"','"+this.userId+"','"+this.userId+"',0,0,0,?, ? ";
			System.out.println("vao day :"+query);
			if(db.update_v2(query, data)!=1)
			{
				this.msg = "Không thể tạo mới Erp_HangTraLaiNpp " + query;
				db.getConnection().rollback();
				return false;
			}
			
		
			this.id = db.getPk_seq();
			
			msg = util.Check_Huy_NghiepVu_KhoaSo("ERP_hangtralainpp", this.id, "ngaytra", db);
			if (msg.length() > 0)
			{
				db.getConnection().rollback();
				db.getConnection().setAutoCommit(true);
				return false;
			}
			
			//Check khoá sổ
			//geso.dms.distributor.util.Utility dutil = new geso.dms.distributor.util.Utility();

			/*this.msg = dutil.Check_Huy_NghiepVu_KhoaSo("Erp_HangTraLaiNpp", this.id, "ngaytra", db);
			if( this.msg.length() > 0)
			{
				db.getConnection().rollback();
				db.getConnection().setAutoCommit(true);
				return false;
			}*/


			query = "\n update erp_hangtralainpp set ddkd_fk = ( select top 1 DDKD_FK from tuyenbanhang a where erp_hangtralainpp.npp_fk = a.npp_fk and a.pk_seq in (select tbh_fk from khachhang_tuyenbh where khachhang_fk = erp_hangtralainpp.khachhang_fk ) ) " +
					"\n where khachhang_Fk is not null and pk_seq = "+ this.id;
			if(!db.update(query))
			{
				this.msg = "Không thể tạo mới Erp_HangTraLaiNpp " + query;
				db.getConnection().rollback();
				return false;
			}
			
			query = "\n update erp_hangtralainpp set ddkd_fk = "+this.ddkdid+" " +
			"\n where khachhang_Fk is not null and pk_seq = "+ this.id;
			if(!db.update(query))
			{
				this.msg = "Không thể tạo mới Erp_HangTraLaiNpp " + query;
				db.getConnection().rollback();
				return false;
			}
			
			query = "\n update erp_hangtralainpp set GSBH_FK = ( select top 1 GSBH_FK from DDKD_GSBH where  DDKD_FK = erp_hangtralainpp.DDKD_FK  ) " +
					"\n where ddkd_fk is not null and pk_seq = "+ this.id;
			if(!db.update(query))
			{
				this.msg = "Không thể tạo mới Erp_HangTraLaiNpp " + query;
				db.getConnection().rollback();
				return false;
			}


			for(int i = 0; i < spMa.length; i++)
			{


				if(spMa[i].trim().length() > 0 && spSoluong[i].trim().length() > 0 )
				{

					if(spSolo[i].trim().length()<=0||spNgayHetHan[i].length()<=0)
					{
						this.msg = "Vui lòng nhập Số Lô/Ngày hết hạn của sản phẩm "+spMa[i]+ ""+spTen[i]+" ";
						db.getConnection().rollback();
						return false;
					}

					if(spSolo[i].toUpperCase().trim().equals("NA"))
				     {
				      spSolo[i]="NA";
				      spNgayHetHan[i]="2030-12-31";
				     }
					
					query = 
						"\n	insert into Erp_HangTraLaiNpp_SanPham(HangTraLai_fk,Sanpham_fk,Dvdl_Fk,SoLuong,DonGia,SoLo,NgayHetHan,VAT,GhiChu,kbh_fk, kho_fk) "+
						"\n	select '"+this.id+"' as id,sp.pk_seq as sp,  "+
						"\n		ISNULL( ( select pk_Seq from DONVIDOLUONG where donvi = N'" + spDonvi[i].trim() + "' ), DVDL_FK ), '" + spSoluong[i].replaceAll(",", "") + "' as SoLuong,'" + spGianhap[i].replaceAll(",", "") + "' as DonGia,'"+spSolo[i].trim()+"' as SoLo,'"+spNgayHetHan[i].trim()+"' as NgayHetHan,'"+spVat[i]+"','"+spGhiChu[i]+"' " +
						"\n			,'"+this.kbhId+"','"+this.khoXuatId+"' "+
						"\n	from SANPHAM sp left join NGANHHANG nh on nh.PK_SEQ=sp.NGANHHANG_FK " +
						"\n where sp.ma='"+spMa[i]+"'   ";

					System.out.println("3.Insert CK - SP: " + query);
					if(!db.update(query))
					{
						this.msg = "Khong the tao moi Erp_HangTraLaiNpp_SanPham: " + query;
						db.getConnection().rollback();
						return false;
					}
				}
			}

			String kqVat = geso.dms.center.util.Utility.CheckVat( db , "Erp_HangTraLaiNpp_SanPham","VAT", "HangTraLai_fk", this.id );
			if(kqVat.trim().length() > 0)
			{
				this.msg = kqVat;
				db.getConnection().rollback();
				db.getConnection().setAutoCommit(true);
				return false;
			}

			query=
				"\n UPDATE hd SET TienSauThue= tongtien + round((tongtien*data.vat/100),0),  TienTruocThue= tongtien, TienThue= round(tongtien*data.vat/100,0)  " + 
				"\n   --select  tongtien + round((tongtien*data.vat/100),0)   ,tongtien, round(tongtien*data.vat/100,0)  " + 
				"\n  from   " + 
				"\n (    " + 
				"\n 	select  HangTraLai_fk,VAT, SUM( floor(SoLuong*DonGia)) as tongtien  " + 
				"\n 	from Erp_HangTraLaiNpp_SanPham  where HangTraLai_fk=  " + this.id + 
				"\n 	group by HangTraLai_fk  ,VAT   " + 
				"\n  ) as data inner join Erp_HangTraLaiNpp hd on hd.pk_seq=data.HangTraLai_fk ";

			System.out.println("___"+query);

			if(!db.update(query))
			{
				this.msg = "Khong the tao moi Erp_HangTraLaiNpp_SanPham: " + query;
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
		if(this.ddkdid == null || this.ddkdid.trim().length() <= 0)
		{
			this.msg = "Vui lòng chọn NVBH";
			return false;
		}
		
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

		if( this.doituong.trim().length() <= 0 )
		{
			this.msg = "Vui lòng chọn đối tượng";
			return false;
		}


		if( this.khId.trim().length() <= 0 && this.dtId.length()<=0 )
		{
			this.msg = "Vui lòng chọn đối tượng";
			return false;
		}

		if( this.kbhId.trim().length() <= 0 )
		{
			this.msg = "Vui lòng chọn kênh bán hàng";
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

				if( spSolo[i].trim().length() > 0 &&  spNgayHetHan[i].trim().length() > 0  )
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
					if( spMa[i].trim().equals(spMa[j].trim()) &&  spSolo[i].trim().equals(spSolo[j].trim()) && spNgayHetHan[i].trim().equals(spNgayHetHan[j].trim()  ) )
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


			//Check khoá sổ
			//geso.dms.distributor.util.Utility dutil = new geso.dms.distributor.util.Utility();

			/*this.msg = dutil.Check_Huy_NghiepVu_KhoaSo("Erp_HangTraLaiNpp", this.id, "ngaytra", db);
			if( this.msg.length() > 0)
			{
				db.getConnection().rollback();
				db.getConnection().setAutoCommit(true);
				return false;
			}*/

			Object[] data = null;
			data = dbutils.setObject((this.khId.length()<=0?"NULL":this.khId),(this.dtId.length()<=0?"NULL":this.dtId),this.doituong
					,this.ngayyeucau,this.ghichu,this.kbhId,this.khoXuatId,this.SoHoaDon,this.KyHieu,this.NgayHoaDon,this.so);
			String query =	
				" Update Erp_HangTraLaiNpp set KHACHHANG_FK=?,npptra_fk=?,doituong=?,NgayTra=?,GhiChu=?,KBH_FK=?,KHO_FK=?," +
				" SoHoaDon=?,KyHieu=?,NgayHoaDon=?,NgaySua='"+getDateTime()+"',NguoiSua='"+this.userId+"' ,so=?	 where pk_seq = '" + this.id + "' and trangthai=0 ";			
			if(db.update_v2(query, data)!=1)
			{
				this.msg = "Không thể cập nhật Erp_HangTraLaiNpp " + query;
				db.getConnection().rollback();
				return false;
			}
			
			//Check khoá sổ
			msg = util.Check_Huy_NghiepVu_KhoaSo("ERP_hangtralainpp", this.id, "ngaytra", db);
			if (msg.length() > 0)
			{
				db.getConnection().rollback();
				db.getConnection().setAutoCommit(true);
				return false;
			}

			query = "\n update erp_hangtralainpp set ddkd_fk = "+this.ddkdid+" " +
					"\n where pk_seq = "+ this.id;
			if(!db.update(query))
			{
				this.msg = "Không thể tạo mới Erp_HangTraLaiNpp " + query;
				db.getConnection().rollback();
				return false;
			}
			
			query = "\n update erp_hangtralainpp set GSBH_FK = ( select top 1 GSBH_FK from DDKD_GSBH where  DDKD_FK = erp_hangtralainpp.DDKD_FK  ) " +
					"\n where pk_seq = "+ this.id;
			if(!db.update(query))
			{
				this.msg = "Không thể tạo mới Erp_HangTraLaiNpp " + query;
				db.getConnection().rollback();
				return false;
			}
			
			
			query="delete from Erp_HangTraLaiNpp_SanPham where HangTraLai_fk='"+this.id+"' ";

			int SoDong=0;
			SoDong=db.updateReturnInt(query);
			System.out.println(SoDong+"SoDong__Erp_HangTraLaiNpp_SanPham__"+query);
			if(SoDong<=0)
			{
				this.msg = "Không thể cập nhật Erp_HangTraLaiNpp_SanPham " + query;
				db.getConnection().rollback();
				return false;
			}

			for(int i = 0; i < spMa.length; i++)
			{
				if(spMa[i].trim().length() > 0 && spSoluong[i].trim().length() > 0 )
				{
					if(spSolo[i].trim().length()<=0||spNgayHetHan[i].length()<=0)
					{
						this.msg = "Vui lòng nhập Số Lô/Ngày hết hạn của sản phẩm "+spMa[i]+ " "+spTen[i]+" ";
						db.getConnection().rollback();
						return false;
					}
					
					if(spSolo[i].toUpperCase().trim().equals("NA"))
				    {
				      spSolo[i]="NA";
				      spNgayHetHan[i]="2030-12-31";
				    }
					
					query = 
						"\n		insert into Erp_HangTraLaiNpp_SanPham(HangTraLai_fk,Sanpham_fk,Dvdl_Fk,SoLuong,DonGia,SoLo,NgayHetHan,VAT,GhiChu, kbh_fk, kho_fk) "+
						"\n		select '"+this.id+"' as id,sp.pk_seq as sp,  "+
						"\n			ISNULL( ( select pk_Seq from DONVIDOLUONG where donvi = N'" + spDonvi[i].trim() + "' ), DVDL_FK ), '" + spSoluong[i].replaceAll(",", "") + "' as SoLuong,'" + spGianhap[i].replaceAll(",", "") + "' as DonGia,'"+spSolo[i].trim()+"' as SoLo,'"+spNgayHetHan[i].trim()+"' as NgayHetHan,'"+spVat[i]+"','"+spGhiChu[i]+"' "+
						"\n			,'"+this.kbhId+"','"+this.khoXuatId+"' "+
						"\n		from SANPHAM sp left join NGANHHANG nh on nh.PK_SEQ=sp.NGANHHANG_FK " +
						"\n	 where sp.ma='"+spMa[i]+"'   ";

					System.out.println(i+"__3.Insert CK - SP: " + query);
					if(db.updateReturnInt(query)!=1)
					{
						this.msg = "Khong the tao moi Erp_HangTraLaiNpp_SanPham: " + query;
						db.getConnection().rollback();
						return false;
					}
				}
			}

			String kqVat = geso.dms.center.util.Utility.CheckVat( db , "Erp_HangTraLaiNpp_SanPham","VAT", "HangTraLai_fk", this.id );
			if(kqVat.trim().length() > 0)
			{
				this.msg = kqVat;
				db.getConnection().rollback();
				db.getConnection().setAutoCommit(true);
				return false;
			}

			query=
				"\n UPDATE hd SET TienSauThue= tongtien + round((tongtien*data.vat/100),0),  TienTruocThue= tongtien, TienThue= round(tongtien*data.vat/100,0)  " + 
				"\n   --select  tongtien + round((tongtien*data.vat/100),0)   ,tongtien, round(tongtien*data.vat/100,0)  " + 
				"\n  from   " + 
				"\n (    " + 
				"\n 	select  HangTraLai_fk,VAT, SUM( floor(SoLuong*DonGia)) as tongtien  " + 
				"\n 	from Erp_HangTraLaiNpp_SanPham  where HangTraLai_fk=  " + this.id + 
				"\n 	group by HangTraLai_fk  ,VAT   " + 
				"\n  ) as data inner join Erp_HangTraLaiNpp hd on hd.pk_seq=data.HangTraLai_fk ";
			if(!db.update(query))
			{
				this.msg = "Khong the tao moi Erp_HangTraLaiNpp_SanPham: " + query;
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
		if(this.TraNguyenDon.equals("1"))
			query +=" and pk_seq = 100000  ";
		this.khoXuatRs = db.get(query);

		query = "\n select PK_SEQ, MAFAST + '-' + TEN as TEN from " +
				"\n KHACHHANG where TRANGTHAI = '1' " +
				"\n and  pk_seq in ( select khachhang_fk from khachhang_npp where  npp_fk ='"+ this.nppId +"') " ;
		if(this.TraNguyenDon.equals("1"))
			query +=" and KBH_FK  != 100052  ";
		
		this.khRs = db.get(query);

		query = " select a.pk_seq, a.ten from daidienkinhdoanh a " +
				"inner join daidienkinhdoanh_npp b on a.pk_seq = b.ddkd_fk where b.npp_fk ='"+ this.nppId +"' ";
		System.out.println("+_+_+ tdv" + query);
		this.ddkdRs = db.get(query);
		
		query = "select PK_SEQ, MAFAST + '-' + TEN as TEN from NHAPHANPHOI where TRANGTHAI = '1' and  tructhuoc_fk ='"+ this.nppId +"' " ;
		System.out.println("Nha pp : "+query);
		this.dtRs = db.get(query);

		this.dvtRs = db.getScrol("select PK_SEQ, DONVI from DONVIDOLUONG where trangthai = '1' ");
		
		//CHECK DUNG CHUNG KENH HAY KHONG
		query =  "select isnull(dungchungkenh, 0) as dungchungkenh from NHAPHANPHOI " +
		"where pk_seq = ( select npp_fk from ERP_CHUYENKHONPP where pk_seq = '" + this.nppId + "' )";
		ResultSet rsKENH = db.get(query);
		String dungchungKENH = "0";
		if(rsKENH != null)
		{
			try 
			{
				if(rsKENH.next())
				{
					dungchungKENH = rsKENH.getString("dungchungkenh");
				}
				rsKENH.close();
			} 
			catch (Exception e) {e.printStackTrace(); }
		}

		if(dungchungKENH.equals("1") || this.TraNguyenDon.equals("1") )
			this.kbhRs = db.getScrol("select PK_SEQ, TEN from KENHBANHANG where trangthai = '1' and pk_seq = '100025' ");
		else
			this.kbhRs = db.getScrol("select PK_SEQ, TEN from KENHBANHANG where trangthai = '1' ");

		
		if(this.TraNguyenDon.equals("1") && this.khId.trim().length() >0)
		{
			
			query = "\n select pk_seq , cast(pk_seq as varchar) + '(' + ngaynhap +')' diengiai " +
					"\n from donhang dh " +
					"\n where dh.npp_fk = "+this.nppId+"  and dh.trangthai = 1 and dh.khachhang_fk = "+this.khId+" and exists ( select 1 from hoadon_DDH x where DDH_FK = dh.pk_seq and exists (select 1 from hoadon where trangthai in (2,4) and loaihoadon=0 and pk_seq = x.hoadon_fk )) " +
					"\n 	and not exists (select 1 from erp_hangtralainpp where trangthai !=2 and donhang_fk = dh.pk_seq " + ( this.id != null && this.id.trim().length() > 0 ? " and pk_seq !="+this.id:"" ) + " )	";
			this.donhangRs = db.get(query);
		}
		if(this.TraNguyenDon.equals("1") && this.donhangId.trim().length() > 0 )
		{
			query =  "\n if (select COUNT(*) from NHAPHANPHOI where PK_SEQ = 100428 and quanlykho = 1) >0  " + 
					 "\n 	 select k.ten kho, sp.ten sanpham,dhsp.solo,dhsp.ngayhethan,dhsp.ngaynhapkho,dhsp.soluong,gia.giamua,dhsp.soluong*gia.giamua thanhtien,''ctkm    " + 
					 "\n 	 from donhang_sanpham_chitiet dhsp    " + 
					 "\n 	 inner join donhang_sanpham gia on dhsp.donhang_fk = gia.donhang_fk and dhsp.sanpham_fk = gia.sanpham_fk    " + 
					 "\n 	 inner join sanpham sp on sp.pk_Seq =dhsp.sanpham_fk    " + 
					 "\n 	 inner join kho k on k.pk_seq = dhsp.kho_fk   " + 
					 "\n 	 inner join donhang dh on dh.pk_seq = dhsp.donhang_fk and dh.npp_fk =  " + this.nppId+  
					 "\n 	 where dhsp.donhang_fk =   " + this.donhangId+
					 "\n 	 union all    " + 
					 "\n 	 select k.ten kho, sp.ten sanpham,dhsp.solo,dhsp.ngayhethan,dhsp.ngaynhapkho,dhsp.soluong, 0 giamua, 0 thanhtien,ctkm.scheme    " + 
					 "\n 	 from donhang_ctkm_trakm_chitiet dhsp    " + 
					 "\n 	 inner join sanpham sp on sp.pk_Seq =dhsp.sanpham_fk    " + 
					 "\n 	 inner join kho k on k.pk_seq = dhsp.kho_fk    " + 
					 "\n 	 inner join ctkhuyenmai ctkm on ctkm.pk_seq = dhsp.ctkm_fk    " + 
					 "\n 	 inner join donhang dh on dh.pk_seq = dhsp.donhang_fk and dh.npp_fk =  " + this.nppId+  
					 "\n 	 where dhsp.donhang_fk =   " + this.donhangId+
					 "\n 	 union all    " + 
					 "\n 	 select '' kho, '' sanpham,'','' ngayhethan,''ngaynhapkho,0 soluong, 0 giamua, dhsp.tonggiatri thanhtien,ctkm.scheme    " + 
					 "\n 	 from donhang_ctkm_trakm dhsp    " + 
					 "\n 	 inner join ctkhuyenmai ctkm on ctkm.pk_seq = dhsp.ctkmid    " + 
					 "\n 	 inner join donhang dh on dh.pk_seq = dhsp.donhangId and dh.npp_fk =  " + this.nppId+  
					 "\n 	 where dhsp.donhangId =" + this.donhangId + " and dhsp.spma is null    " + 
			 
					 "\n 	 else  " + 
			 
			 
					 "\n 	 select k.ten kho, sp.ten sanpham,'' solo,''ngayhethan,'' ngaynhapkho,dhsp.soluong,gia.giamua,dhsp.soluong*gia.giamua thanhtien,''ctkm    " + 
					 "\n 	 from DONHANG_SANPHAM dhsp    " + 
					 "\n 	 inner join donhang_sanpham gia on dhsp.donhang_fk = gia.donhang_fk and dhsp.sanpham_fk = gia.sanpham_fk    " + 
					 "\n 	 inner join sanpham sp on sp.pk_Seq =dhsp.sanpham_fk    " + 
					 "\n 	 inner join kho k on k.pk_seq = dhsp.kho_fk   " + 
					 "\n 	 inner join donhang dh on dh.pk_seq = dhsp.donhang_fk and dh.npp_fk =  " + this.nppId+  
					 "\n 	 where dhsp.donhang_fk =   " + this.donhangId+
					 "\n  union all    " + 
					 "\n 	 select k.ten kho, sp.ten sanpham,''solo,''ngayhethan,'' ngaynhapkho,dhsp.soluong, 0 giamua, 0 thanhtien,ctkm.scheme    " + 
					 "\n 	 from donhang_ctkm_trakm dhsp    " + 
					 "\n 	 inner join sanpham sp on sp.ma =dhsp.SPMA    " + 
					 "\n 	 inner join kho k on k.pk_seq = dhsp.kho_fk    " + 
					 "\n 	 inner join ctkhuyenmai ctkm on ctkm.pk_seq = dhsp.CTKMID    " + 
					 "\n 	 inner join donhang dh on dh.pk_seq = dhsp.DONHANGID and dh.npp_fk =  " + this.nppId+  
					 "\n 	 where dhsp.DONHANGID =   " + this.donhangId+
					 "\n 	 union all    " + 
					 "\n 	 select '' kho, '' sanpham,'','' ngayhethan,''ngaynhapkho,0 soluong, 0 giamua, dhsp.tonggiatri thanhtien,ctkm.scheme    " + 
					 "\n 	 from donhang_ctkm_trakm dhsp    " + 
					 "\n 	 inner join ctkhuyenmai ctkm on ctkm.pk_seq = dhsp.ctkmid    " + 
					 "\n 	 inner join donhang dh on dh.pk_seq = dhsp.donhangId and dh.npp_fk =  " + this.nppId+  
					 "\n 	 where dhsp.donhangId =" + this.donhangId + " and dhsp.spma is null  ";
			
			
			
			System.out.println(" info rs =  "+ query);
			this.infoRs = db.get(query);
			
			query = "\n select pk_seq ,tonggiatri " +
					"\n from donhang dh " +
					"\n where dh.pk_seq = "+this.donhangId+" and  dh.npp_fk = "+this.nppId+"  and dh.trangthai = 1  and exists ( select 1 from hoadon_DDH x where DDH_FK = dh.pk_seq and exists (select 1 from hoadon where trangthai in (2,4) and loaihoadon=0 and pk_seq = x.hoadon_fk )) " +
					"\n 	and not exists (select 1 from erp_hangtralainpp where trangthai !=2 and donhang_fk = dh.pk_seq " + ( this.id != null && this.id.trim().length() > 0 ? " and pk_seq !="+this.id:"" ) + " )	";

			ResultSet rs=  db.get(query);
			try {
				if(rs.next())
				{
					NumberFormat formatter = new DecimalFormat("#,###,###");
					this.tonggiatridonhang = formatter.format(rs.getDouble("tonggiatri"));
				}
				else
				{
					this.msg =" Đơn hàng chưa chốt hóa đơn tài chính hoặc không tồn tại ";
					System.out.println(" info tonggiatri =  "+ query);
				}
				rs.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
		
	}

	private void initSANPHAM() 
	{
		String query =  
			"\n	select c.MA as spMa,c.TEN as spTEN,d.DIENGIAI as spDonVi,b.SoLuong,b.SoLo as spSoLo,b.ngayhethan as spNGAYHETHAN ,round(b.SoLuong*b.DonGia,0)/b.SoLuong as dongia,isnull(b.GhiChu,'') as spGhiChu,b.vat as spVat  "+
			"\n		from Erp_HangTraLaiNpp a inner join Erp_HangTraLaiNpp_SanPham b on b.HangTraLai_fk=a.pk_seq  "+
			"\n			inner join SANPHAM c on c.PK_SEQ=b.Sanpham_fk    "+
			"\n			inner join DONVIDOLUONG d on d.PK_SEQ=b.Dvdl_Fk   "+
			"\n		where a.pk_seq='"+this.id+"' ";

		System.out.println("___"+query);
		ResultSet spRs = db.get(query);
		NumberFormat formater = new DecimalFormat("##,###,###.####");
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

				String spSOLO = "";
				String spNGAYHETHAN = "";
				String spGHICHU = "";
				String spVAT = "";
				while(spRs.next())
				{
					spMA += spRs.getString("spMa") + "__";
					spTEN += spRs.getString("spTEN") + "__";
					spDONVI += spRs.getString("spDonVi") + "__";
					spSOLUONG += formater.format(spRs.getDouble("SOLUONG")) + "__";
					spGIANHAP += formater.format(spRs.getDouble("DONGIA")) + "__";
					System.out.println("don gia "+spRs.getDouble("DONGIA"));
					spSOLO += spRs.getString("spSOLO") + "__";
					spNGAYHETHAN += spRs.getString("spNGAYHETHAN") + "__";
					spGHICHU += spRs.getString("spGHICHU") + "__";
					spVAT += spRs.getString("spVat") + "__";

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

					spSOLO = spSOLO.substring(0, spSOLO.length() - 2);
					this.spSolo = spSOLO.split("__");

					spNGAYHETHAN = spNGAYHETHAN.substring(0, spNGAYHETHAN.length() - 2);
					this.spNgayHetHan = spNGAYHETHAN.split("__");

					spGHICHU = spGHICHU.substring(0, spGHICHU.length() - 2);
					this.spGhiChu = spGHICHU.split("__");

					spVAT = spVAT.substring(0, spVAT.length() - 2);
					this.spVat = spVAT.split("__");

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
		String query =  
			"\n	select isnull(donhang_fk,0) donhangId ,ngaytra,pk_seq,npp_fk,khachhang_fk,npptra_fk,doituong,ngaytra,ghichu,kbh_fk,kho_fk,SoHoaDon,KYHIEU,TrangThai,TienTruocThue,TienSauThue,TienThue,NgayHoaDon,so, ddkd_fk  "+
			"\n	from Erp_HangTraLaiNpp  "+
			"\n	where pk_seq='"+this.id+"' ";
		System.out.println("____INIT NHAP KHO: " + query);
		ResultSet rs = db.get(query);
		if(rs != null)
		{
			try 
			{
				if(rs.next())
				{		
					if(rs.getDouble("donhangId") > 0.0)
					{
						this.TraNguyenDon = "1";
						this.donhangId= rs.getString("donhangId");
					}
					this.ghichu = rs.getString("ghichu");
					this.khoXuatId = rs.getString("kho_fk");

					this.kbhId = rs.getString("kbh_fk");
					this.trangthai = rs.getString("trangthai");
					this.SoHoaDon =rs.getString("SoHoaDon")== null ? "":rs.getString("SoHoaDon");
					this.KyHieu =rs.getString("KyHieu")== null ? "":rs.getString("KyHieu");
					this.NgayHoaDon =rs.getString("NgayHoaDon")== null ? "":rs.getString("NgayHoaDon");
					this.ngayyeucau =rs.getString("ngaytra")== null ? "":rs.getString("ngaytra");
					this.doituong =rs.getString("doituong")== null ? "0":rs.getString("doituong");
					this.ddkdid =rs.getString("ddkd_fk");
					this.khId = rs.getString("khachhang_fk")==null?"":rs.getString("khachhang_fk");
					this.dtId = rs.getString("npptra_fk")==null?"":rs.getString("npptra_fk");
					this.so=rs.getString("so")==null?"":rs.getString("so");
					this.tienvat=rs.getString("tienthue")==null?"":rs.getString("tienthue");
					System.out.println("so----------"+this.so +"--------------"+this.tienvat);
				}
				rs.close();
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

	
	public ResultSet getddkdRs() {

		return this.ddkdRs;
	}


	public void setddkdRs(ResultSet ddkdRs) {

		this.ddkdRs = ddkdRs;
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
			//System.out.println("---TONG LUONG: " + tongluong );

			String query = "select AVAILABLE + ISNULL( ( select sum(soluong) from ERP_CHUYENKHONPP_SANPHAM_CHITIET where chuyenkho_fk = '" + ( this.id.trim().length() > 0 ? this.id : "-1" ) + "' and sanpham_fk = ct.sanpham_fk and solo = ct.solo  and NgayHetHan=ct.NgayHetHan ), 0 ) as AVAILABLE, NGAYHETHAN, SOLO " +
			"from NHAPP_KHO_CHITIET ct " +
			"where KHO_FK = '" + this.khoXuatId + "' and KBH_FK = '" + this.kbhId + "' and NPP_FK = '" + this.nppId + "' and SANPHAM_FK = ( select pk_seq from SANPHAM where ma = '" + spMa + "' ) " +
			" and ( AVAILABLE + ISNULL( ( select sum(soluong) from ERP_CHUYENKHONPP_SANPHAM_CHITIET where chuyenkho_fk = '" + ( this.id.trim().length() > 0 ? this.id : "-1" ) + "' and sanpham_fk = ( select pk_seq from SANPHAM where ma = '" + spMa + "' ) and solo = ct.solo  and NgayHetHan=ct.NgayHetHan   ), 0 ) ) > 0 " +
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
						query2 += "select '" + avai + "' as AVAILABLE, '" + rs.getString("NGAYHETHAN") + "' as NGAYHETHAN, '" + rs.getString("SOLO") + "' as SOLO, '" + slg + "' as tuDEXUAT union ALL ";
					}
					else
					{
						query2 += "select '" + avai + "' as AVAILABLE, '" + rs.getString("NGAYHETHAN") + "' as NGAYHETHAN, '" + rs.getString("SOLO") + "' as SOLO, '' as tuDEXUAT union ALL ";
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


	String doituong,SoHoaDon,NgayHoaDon,KyHieu, ddkdid;

	public String getSoHoaDon()
	{
		return SoHoaDon;
	}

	public void setSoHoaDon(String soHoaDon)
	{
		SoHoaDon = soHoaDon;
	}

	public String getddkdId()
	{
		return ddkdid;
	}

	public void setddkdId(String ddkdid)
	{
		this.ddkdid = ddkdid;
	}
	
	public String getNgayHoaDon()
	{
		return NgayHoaDon;
	}

	public void setNgayHoaDon(String ngayHoaDon)
	{
		NgayHoaDon = ngayHoaDon;
	}

	public String getKyHieu()
	{
		return KyHieu;
	}

	public void setKyHieu(String kyHieu)
	{
		KyHieu = kyHieu;
	}

	public String getDoituong()
	{
		return doituong;
	}

	public void setDoituong(String doituong)
	{
		this.doituong = doituong;
	}

	ResultSet nppRs;
	public ResultSet getNppRs() {

		return this.nppRs;
	}


	public void setNppRs(ResultSet nppRs) {

		this.nppRs = nppRs;
	}

	String[] spGhiChu;

	public String[] getSpGhiChu()
	{
		return spGhiChu;
	}

	public void setSpGhiChu(String[] spGhiChu)
	{
		this.spGhiChu = spGhiChu;
	}

	String dtId;
	ResultSet dtRs;

	@Override
	public ResultSet getDtRs()
	{
		return this.dtRs;
	}

	@Override
	public void setDtRs(ResultSet dtRs)
	{
		this.dtRs=nppRs; 
	}

	@Override
	public String getDtId()
	{
		return dtId;
	}

	@Override
	public void setDtId(String dtId)
	{
		this.dtId=dtId; 
	}
	String[] spVat;

	public String[] getSpVat()
	{
		return spVat;
	}

	public void setSpVat(String[] spVat)
	{
		this.spVat = spVat;
	}

	public String getTraNguyenDon() {
		return TraNguyenDon;
	}
	public void setTraNguyenDon(String traNguyenDon) {
		TraNguyenDon = traNguyenDon;
	}
	public ResultSet getDonhangRs() {
		return donhangRs;
	}
	public void setDonhangRs(ResultSet donhangRs) {
		this.donhangRs = donhangRs;
	}
	public String getDonhangId() {
		return donhangId;
	}
	public void setDonhangId(String donhangId) {
		this.donhangId = donhangId;
	}
	public ResultSet getInfoRs() {
		return infoRs;
	}
	public void setInfoRs(ResultSet infoRs) {
		this.infoRs = infoRs;
	}
	public String getTonggiatridonhang() {
		return tonggiatridonhang;
	}
	public void setTonggiatridonhang(String tonggiatridonhang) {
		this.tonggiatridonhang = tonggiatridonhang;
	}
}
