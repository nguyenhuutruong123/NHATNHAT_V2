package geso.dms.distributor.beans.dondathang.imp;

import geso.dms.center.db.sql.dbutils;
import geso.dms.center.db.sql.Idbutils;
import geso.dms.center.util.Utility;
import geso.dms.distributor.beans.dondathang.IErpDondathangNpp;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.Hashtable;

import javax.servlet.http.HttpServletRequest;





import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

public class ErpDondathangNpp implements IErpDondathangNpp
{
	private static final long serialVersionUID = 1L;
	String userId;
	String id;

	String ngayyeucau;
	String ngaydenghi;
	String dieuchinh;
	String data, data_save;
	String ghichu;

	String msg;
	String trangthai;

	String loaidonhang;  //0 đơn đặt hàng, 1 đơn hàng khuyến mại ứng hàng, 3 đơn hàng khuyến mại tích lũy, 4 đơn hàng trưng bày, 5 đơn hàng khác
	String chietkhau;
	String vat;

	String khoNhanId;
	ResultSet khoNhanRs;

	String nppId;
	ResultSet nppRs;

	String nppTen;
	String sitecode;

	String dvkdId;
	ResultSet dvkdRs;

	String kbhId;
	ResultSet kbhRs;

	ResultSet dvtRs;

	String schemeId;
	ResultSet schemeRs;
	String View = "";
	Hashtable<String, String> scheme_soluong;

	String[] spId;
	String[] spMa;
	String[] spTen;
	String[] spDonvi;
	String[] spSoluong;
	String[] spGianhap;
	String[] spChietkhau;
	String[] spSCheme;
	String[] spVAT;

	String[] dhCk_diengiai;
	String[] dhCk_giatri;
	String[] dhCk_loai;

	ResultSet congnoRs;

	String loaiNPP;
	dbutils db;
	Utility util;

	String isdhKhac ="0";

	public ErpDondathangNpp()
	{
		this.id = "";
		this.ngayyeucau = getDateTime();
		this.ngaydenghi = getDateTime();
		this.ghichu = "";
		this.khoNhanId = "";
		this.nppId = "";
		this.msg = "";
		this.loaidonhang = "0";
		this.trangthai = "0";
		this.chietkhau = "0";
		this.vat = "10";
		this.dvkdId = "";
		this.kbhId = "";
		this.schemeId = "";
		this.loaiNPP = "";
		this.iskm="0";
		this.dieuchinh = "0";
		this.data = "";
		this.data_save = "";
		this.scheme_soluong = new Hashtable<String, String>();

		this.db = new dbutils();
		this.util = new Utility();
	}

	public ErpDondathangNpp(String id)
	{
		this.id = id;
		this.ngayyeucau = getDateTime();
		this.ngaydenghi = getDateTime();
		this.ghichu = "";
		this.khoNhanId = "";
		this.nppId = "";
		this.msg = "";
		this.loaidonhang = "0";
		this.trangthai = "0";
		this.chietkhau = "0";
		this.vat = "10";
		this.dvkdId = "";
		this.kbhId = "";
		this.schemeId = "";
		this.loaiNPP = "";
		this.dieuchinh = "0";
		this.data = "";
		this.data_save = "";
		this.scheme_soluong = new Hashtable<String, String>();
		this.iskm="0";
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


	public String CheckSXTD(String dhid,dbutils db) {
		try {
			
			String query 
				= " select sum(b.soxuat) as soxuat,b.ctkmid,km.soxuattoida,km.scheme 														  "+
						"\n from erp_dondathang a inner join ERP_DONDATHANG_CTKM_TRAKM b on a.pk_Seq=b.DONDATHANGID 						  "+
						"\n inner join ctkhuyenmai km on km.pk_seq=b.ctkmid where a.pk_Seq != "+dhid+" and isnull(km.ap_dung_npp,0)=1			  "+
						"\n and km.pk_seq in (select ctkmid from ERP_DONDATHANG_CTKM_TRAKM where DONDATHANGID="+dhid+") and a.trangthai !=3 	  "+
						"\n and km.soxuattoida<>0																								  "+
						"\n and a.NPP_FK  = (select NPP_FK from erp_dondathang where pk_seq="+dhid+" ) 								  "+
						"\n group by b.ctkmid,km.soxuattoida,km.scheme" ;	

			System.out.println("---2.CHECK SO SUAT TOI DA: " + query);
			ResultSet rs = db.get(query);
			if (rs != null)
			{
				while(rs.next())
				{
					int check = rs.getInt("soxuat")-rs.getInt("soxuattoida");
					if (check>=0)
					{
						msg = "CTKM "+rs.getString("scheme")+" đã hết số suất tối đa. Vui lòng kiểm tra lại "; 
						//db.getConnection().rollback();
						return msg;
					}
				}
				rs.close();
			}
			return "";
			
		}catch (Exception e) {
			e.printStackTrace();
			return "Loi he thong:";
		}
	}



	public boolean createNK(HttpServletRequest request) 
	{
		if(this.ngayyeucau.trim().length() < 10)
		{
			this.msg = "Vui lòng nhập ngày nhập kho";
			return false;
		}

		if(this.ngaydenghi.trim().length() < 10)
		{
			this.msg = "Vui lòng nhập ngày đề nghị giao hàng";
			return false;
		}

		if( this.dvkdId.trim().length() <= 0 )
		{
			this.msg = "Vui lòng chọn đơn vị kinh doanh";
			return false;
		}

		if( this.kbhId.trim().length() <= 0 )
		{
			this.msg = "Vui lòng chọn kênh bán hàng";
			return false;
		}

		if( this.nppId.trim().length() <= 0 )
		{
			this.msg = "Vui lòng chọn nhà phân phối đặt hàng";
			return false;
		}

		if( this.khoNhanId.trim().length() <= 0 )
		{
			this.msg = "Vui lòng chọn kho đặt hàng";
			return false;
		}

		int countsp = 0;
		if(spMa == null)
		{
			this.msg = "Vui lòng kiểm tra lại danh sách sản phẩm đặt hàng";
			return false;
		}
		else
		{
			boolean coSP = false;
			for(int i = 0; i < spMa.length; i++)
			{
				if( spMa[i].trim().length() > 0 )
				{
					if(spSoluong[i].trim().length() <= 0)
					{
						this.msg = "Bạn phải nhập số lượng của sản phẩm ( " + spTen[i] + " )";
						return false;
					}

					if(spGianhap[i].trim().length() <= 0)
					{
						this.msg = "Bạn phải nhập đơn giá của sản phẩm ( " + spTen[i] + " )";
						return false;
					}

					if(spDonvi[i].trim().length() <= 0)
					{
						this.msg = "Bạn phải nhập đơn vị  của sản phẩm ( " + spTen[i] + " )";
						return false;
					}

					coSP = true;
				}
			}

			if(!coSP)
			{
				this.msg = "Vui lòng kiểm tra lại danh sách sản phẩm";
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
		}

		try
		{
			db.getConnection().setAutoCommit(false);

			String khonhan_fk = this.khoNhanId.trim().length() <= 0 ? "null" : this.khoNhanId.trim();
			String chietkhau = this.chietkhau.trim().length() <= 0 ? "0" : this.chietkhau.replaceAll(",", "").trim();
			String vat = this.vat.trim().length() <= 0 ? "0" : this.vat.replaceAll(",", "").trim();
			Object[] data = null;
			data = dbutils.setObject(this.ngayyeucau,this.ngaydenghi,this.loaidonhang ,this.ghichu,dvkdId,kbhId,nppId,khonhan_fk,chietkhau,vat, getDateTime(), this.userId,getDateTime(), this.userId ,this.iskm,this.isdhKhac,this.nppId);
			String query =  " insert ERP_Dondathang(ngaydonhang, ngaydenghi, loaidonhang, ghichu, trangthai, dvkd_fk, kbh_fk, npp_fk, kho_fk, chietkhau, vat, ngaytao, nguoitao, ngaysua, nguoisua, NPP_DACHOT, TRUCTHUOC_FK,IsKM,isdhKhac) " +
			" select ?, ?, ?, ?, '0', ?, ?, ?,?, ?, ?, ?, ?,?, ?, '0', tructhuoc_fk,?,? " +
			" from NHAPHANPHOI where pk_seq = ? ";

			System.out.println("1.Insert DDH: " + query);
			if(db.update_v2(query, data)!=1)
			{
				db.viewQuery(query, data);
				this.msg = "Không thể tạo mới ERP_Dondathang " + query;
				db.getConnection().rollback();
				return false;
			}

			//LAY ID
		
				this.id = db.getPk_seq();
			
				msg = util.Check_Huy_NghiepVu_KhoaSo("erp_dondathang", this.id, "ngaydonhang", db);
				if (msg.length() > 0)
				{
					db.getConnection().rollback();
					//db.getConnection().setAutoCommit(true);
					return false;
				}
		
			String kbh_fk = this.kbhId;
			query = "select dungchungkenh from NHAPHANPHOI where PK_SEQ = '" + this.nppId + "' ";
			ResultSet rs = db.get(query);
			if(rs.next())
			{
				if(rs.getString("dungchungkenh").equals("1"))
					kbh_fk = "100025";
			}
			
			if(this.loaidonhang.equals("0"))  //DON DAT HANG CHI LUU NHUNG SP KO CO SCHEME
			{
				for(int i = 0; i < spMa.length; i++)
				{
					if(spMa[i].trim().length() > 0 && spSCheme[i].trim().length() <= 0 )
					{
						//CHECK SP NAY DA CO KHAI QUY CACH CHUA?
						query =    "select sp.ten, dv.donvi, case when sp.dvdl_fk != dv.pk_seq   " +
						"	then ISNULL( ( select soluong2 / soluong1 from QUYCACH where SANPHAM_FK = sp.PK_SEQ and DVDL1_FK = sp.DVDL_FK and DVDL2_FK = dv.pk_seq ), -1 ) else 1 end as quycach   "  +
						"from SANPHAM sp, DONVIDOLUONG dv " +
						"where sp.MA = '" + util.antiSQLInspection(spMa[i].trim()) + "' and dv.donvi = N'" + util.antiSQLInspection( spDonvi[i].trim()) + "'   ";

						System.out.println("-----CHECK QUY CACH: " + query );
						 rs = db.get(query);
						if(rs.next())
						{
							if(rs.getDouble("quycach") <= 0)
							{
								this.msg = "Sản phẩm ( " + rs.getString("ten") + " ) với đơn vị đặt ( " + rs.getString("donvi") + " ) chưa thiết lập quy cách trong DLN ";
								rs.close();
								db.getConnection().rollback();
								return false;
							}
						}
						rs.close();

						 data = null;
						data = dbutils.setObject(khonhan_fk,kbh_fk,this.id,util.antiSQLInspection( spSoluong[i].replaceAll(",", "")),util.antiSQLInspection(spGianhap[i].replaceAll(",", "")),util.antiSQLInspection(spVAT[i].replaceAll(",", "")), util.antiSQLInspection(spMa[i].trim()));
						
						
						query = "insert ERP_Dondathang_SANPHAM( kho_fk,kbh_fk,Dondathang_fk, SANPHAM_FK, soluong, dongia, thueVAT, dvdl_fk ) " +
						"select ?,?,?, pk_seq, ?, ?, ?, ( select pk_Seq from DONVIDOLUONG where donvi = N'" + util.antiSQLInspection(spDonvi[i].trim())  + "' ) " +
						"from SANPHAM where MA = ? ";

						System.out.println("1.Insert NK - SP: " + query);
						if(db.update_v2(query, data)<=0)
						{
							this.msg = "Khong the tao moi ERP_Dondathang_SANPHAM: " + query;
							db.getConnection().rollback();
							return false;
						}
						countsp++;
					}					
				}
			}
			else
			{
				for(int i = 0; i < spMa.length; i++)
				{
					if(spMa[i].trim().length() > 0)
					{
						//CHECK SP NAY DA CO KHAI QUY CACH CHUA?
						query =    "select sp.ten, dv.donvi, case when sp.dvdl_fk != dv.pk_seq   " +
						"	then ISNULL( ( select soluong2 / soluong1 from QUYCACH where SANPHAM_FK = sp.PK_SEQ and DVDL1_FK = sp.DVDL_FK and DVDL2_FK = dv.pk_seq ), -1 ) else 1 end as quycach   "  +
						"from SANPHAM sp, DONVIDOLUONG dv " +
						"where sp.MA = '" + util.antiSQLInspection(spMa[i].trim()) + "' and dv.donvi = N'" + util.antiSQLInspection( spDonvi[i].trim()) + "'   ";

						System.out.println("-----CHECK QUY CACH: " + query );
						 rs = db.get(query);
						if(rs.next())
						{
							if(rs.getDouble("quycach") <= 0)
							{
								this.msg = "Sản phẩm ( " + rs.getString("ten") + " ) với đơn vị đặt ( " + rs.getString("donvi") + " ) chưa thiết lập quy cách trong DLN ";
								rs.close();
								db.getConnection().rollback();
								return false;
							}
						}
						rs.close();

						
						 data = null;
						data = dbutils.setObject(khonhan_fk,kbh_fk,this.id,util.antiSQLInspection( spSoluong[i].replaceAll(",", "")),util.antiSQLInspection(spGianhap[i].replaceAll(",", "")),util.antiSQLInspection(spVAT[i].replaceAll(",", "")), util.antiSQLInspection(spMa[i].trim()));
							
							
						query = "insert ERP_Dondathang_SANPHAM( kho_fk,kbh_fk,Dondathang_fk, SANPHAM_FK, soluong, dongia, thueVAT, dvdl_fk ) " +
						"select  ?,?, ?, pk_seq, ?, ?, ?, ( select pk_Seq from DONVIDOLUONG where donvi = N'" + spDonvi[i].trim() + "' ) " +
						"from SANPHAM where MA = ? ";

						System.out.println("1.Insert NK - SP: " + query);
						if(db.update_v2(query, data)<=0)
						{
							this.msg = "Khong the tao moi ERP_Dondathang_SANPHAM: " + query;
							db.getConnection().rollback();
							return false;
						}
						countsp++;
					}
				}
			}

			String msgA = Ap_Khuyen_Mai_SellIn_Dac_Biet( db , this.id, kbh_fk );
			if(msgA.length() > 0 )
			{
				this.msg = msgA;
				db.getConnection().rollback();
				return false;
			}
			
			String msgB = LuuKhuyenMai(db, this.id, 0, request,kbh_fk);
			if(msgB.length() > 0 )
			{
				this.msg = msgB;
				db.getConnection().rollback();
				return false;
			}
			
			String sxtd = CheckSXTD(this.id,db);
			if(sxtd.length()>0){
				this.msg = sxtd;
				db.getConnection().rollback();
				return false;
			}
			
			String msgC = update_giatri_erp_dondathang( db ,this.id );
			if(msgC.length() > 0 )
			{
				this.msg = msgB;
				db.getConnection().rollback();
				return false;
			}
			if(countsp-1>18)//Nếu số dòng vượt quá 10 thì k cho lưu
			{
				this.msg = "TỔNG SỐ DÒNG CỦA ĐƠN ĐẶT HÀNG KHÔNG ĐƯỢC PHÉP QUÁ 10 DÒNG.";
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
			return false;
		}

		return true;
	}
	
	
	public static String update_giatri_erp_dondathang(Idbutils db ,String id)
	{
		String query = 
						"\n update dh set SOTIENTHU = isnull(sp.tiensp,0) -  isnull(km.tiensp,0) " +
			"\n from ERP_DONDATHANG dh " +
			"\n outer apply " +
			"\n ( " +
			"\n 	select sum(  round(soluong * dongia * ( 1 + thueVAT/100.0 ),0) ) tiensp from ERP_DONDATHANG_SANPHAM sp where sp.dondathang_fk  = dh.PK_SEQ " +
			"\n )sp " +
			"\n outer apply " +
			"\n ( " +
			"\n 	select sum( tonggiatri ) tiensp from ERP_DONDATHANG_CTKM_TRAKM sp where sp.dondathangId  = dh.PK_SEQ and sp.sanpham_fk is  null " +
			"\n )km " +
			"\n where dh.PK_SEQ = "+id+" and  isnull(sp.tiensp,0) -  isnull(km.tiensp,0) >= 0";
		int sd = db.updateReturnInt(query);
		if(sd == 0)
			return " Giá trị đơn hàng < 0 ";
		if(sd == 1) return "";
		
		return "Lỗi cập nhật đơn hàng";
		
	}
	
	public static String Ap_Khuyen_Mai_SellIn_Dac_Biet(Idbutils db ,String dhId,String kbh_fk )
	{
		try
		{
			
			
			String query =  "\n insert ERP_DONDATHANG_CTKM_TRAKM(kbh_fk,DONDATHANGID,CTKMID,TRAKMID,SOXUAT,TONGGIATRI,SPMA,SOLUONG,CHIETKHAU,sanpham_fk,kho_fk) " +
							"\n select "+kbh_fk+",dh.PK_SEQ,ctkm.PK_SEQ ctkmId, tkm.PK_SEQ tkmId, round((tt.tongtien * ctkm.pt_ChietKhau/100.0)/bg.giamua  ,0) " +
							"\n			,0 ,sp.ma, round((tt.tongtien * ctkm.pt_ChietKhau/100.0)/bg.giamua  ,0), ctkm.pt_ChietKhau, ct.sanpham_fk, ctkm.KHO_FK " +
							"\n from  ERP_DONDATHANG dh " +
							"\n inner join CTKHUYENMAI ctkm on  ctkm.TUNGAY <=dh.NgayDonHang and ctkm.DENNGAY >=dh.NgayDonHang and loaict = 9 " +
							"\n inner join CTKM_NPP_SANPHAM ct on ct.CTKM_FK = ctkm.PK_SEQ and ct.NPP_FK =dh.NPP_FK" +
							"\n	inner join sanpham sp on sp.pk_seq = ct.sanpham_fk " +
							"\n outer apply " +
							"\n ( " +
							"\n 	select  sum(round (soluong * dongia * ( 1 + thuevat/100.0),0))tongtien from ERP_DONDATHANG_SANPHAM where  dondathang_fk =dh.pk_seq " +
							"\n )tt " +
							"\n outer apply " +
							"\n ( " +
							"\n 	select dbo.[GiaDoitacSanpham](100001,dh.KBH_FK,dh.NPP_FK,ct.sanpham_fk,0,dh.ngaydonhang) giamua " +
							"\n )bg " +
							"\n cross apply " +
							"\n ( " +
							"\n 	select top 1 pk_seq from TRAKHUYENMAI where istichluy = 1 and LOAI =3 and HINHTHUC = 1 " +
							"\n )tkm " +
							"\n  where isnull(ctkm.trangthai,0) = 1 and round((tt.tongtien * ctkm.pt_ChietKhau/100.0)/bg.giamua  ,0) > 0 and isnull(dh.iskm,0) = 0 and isnull(dh.isdhKhac,0) = 0 and   dh.PK_SEQ ="+dhId+"  and bg.giamua > 0 ";
			System.out.println("query km sl " + query);
			if(!db.update(query))
			{
				System.out.println(query);
				return "Lỗi áp khuyến mãi đặc biệt";
			}
			return "";
		}
		catch (Exception e) {
			e.printStackTrace();
			return "Exception áp km:" + e.getMessage();
		}
	}
	
	public boolean updateNK(HttpServletRequest request) 
	{
		if(this.ngayyeucau.trim().length() < 10)
		{
			this.msg = "Vui lòng nhập ngày nhập kho";
			return false;
		}

		if(this.ngaydenghi.trim().length() < 10)
		{
			this.msg = "Vui lòng nhập ngày đề nghị giao hàng";
			return false;
		}

		if( this.dvkdId.trim().length() <= 0 )
		{
			this.msg = "Vui lòng chọn đơn vị kinh doanh";
			return false;
		}

		if( this.kbhId.trim().length() <= 0 )
		{
			this.msg = "Vui lòng chọn kênh bán hàng";
			return false;
		}

		if( this.nppId.trim().length() <= 0 )
		{
			this.msg = "Vui lòng chọn nhà phân phối đặt hàng";
			return false;
		}

		if( this.khoNhanId.trim().length() <= 0 )
		{
			this.msg = "Vui lòng chọn kho đặt hàng";
			return false;
		}

		int countsp = 0;
		String sqlCHeck = "";
	
		if(spMa == null)
		{
			this.msg = "Vui lòng kiểm tra lại danh sách sản phẩm đặt hàng";
			return false;
		}
		else
		{
			
			boolean coSP = false;
			
			for(int i = 0; i < spMa.length; i++)
			{
				if( spMa[i].trim().length() > 0 && spSCheme[i].trim().length() <= 0 )
				{
					if(spSoluong[i].trim().length() <= 0 ||  spSoluong[i].equals("0"))
					{
						this.msg = "Bạn phải nhập số lượng của sản phẩm ( " + spTen[i] + " )";
						return false;
					}

					if(spGianhap[i].trim().length() <= 0)
					{
						this.msg = "Bạn phải nhập đơn giá của sản phẩm ( " + spTen[i] + " )";
						return false;
					}

					if(spDonvi[i].trim().length() <= 0)
					{
						this.msg = "Bạn phải nhập đơn vị  của sản phẩm ( " + spTen[i] + " )";
						return false;
					}

					coSP = true;
					
					
					String str = " select pk_seq as sanpham_fk, "+ Utility.parseDouble(spSoluong[i].replace(",", ""))+" as soluong " +
					" from SANPHAM where ma = '"+spMa[i]+"' ";
					 sqlCHeck += sqlCHeck.length() > 0 ? " union all  "  + str : str;
				}
			}

			if(!coSP)
			{
				this.msg = "Vui lòng kiểm tra lại danh sách sản phẩm";
				return false;
			}

			//CHECK TRUNG MA 
			for(int i = 0; i < spMa.length - 1; i++)
			{
				for(int j = i + 1; j < spMa.length; j++)
				{
					if(spMa[i].trim().length() > 0 && spMa[j].trim().length() > 0  )
					{
						if( spMa[i].trim().equals(spMa[j].trim()) && spSCheme[i].trim().length() <= 0 && spSCheme[j].trim().length() <= 0 )
						{
							this.msg = "Sản phẩm ( " + spTen[i] + " )  đã bị trùng.";
							return false;
						}
					}
				}
			}
			
			
			
			
		}
		try
		{
			String query  = "";
			ResultSet rs;
			db.getConnection().setAutoCommit(false);
			String action = request.getParameter("action");
			
			
			
			
			
			String khonhan_fk = this.khoNhanId.trim().length() <= 0 ? "null" : this.khoNhanId.trim();
			String chietkhau = this.chietkhau.trim().length() <= 0 ? "0" : this.chietkhau.replaceAll(",", "").trim();
			String vat = this.vat.trim().length() <= 0 ? "0" : this.vat.replaceAll(",", "").trim();
			Object[] data = null;
			data = dbutils.setObject(this.ngayyeucau, this.ngaydenghi,this.ghichu,this.dvkdId ,this.kbhId , this.nppId,khonhan_fk,getDateTime() ,this.userId,chietkhau,vat,this.loaidonhang,this.iskm,this.isdhKhac, this.id);
			query =	" Update ERP_Dondathang set  ngaydonhang = ?, ngaydenghi = ?, ghichu = ?, " +
			" 	dvkd_fk = ?, kbh_fk = ?, npp_fk = ?, kho_fk =?, ngaysua = ?, nguoisua = ?, chietkhau = ?, vat = ?,LoaiDonHang=?,IsKm=? ,isdhKhac=? " + 
			" where trangthai=0 and npp_dachot = 0 and pk_seq = ? ";

			if(db.update_v2(query, data)!=1)
			{
				this.msg = "Không thể cập nhật ERP_Dondathang " + query;
				db.getConnection().rollback();
				return false;
			}
			
			msg = util.Check_Huy_NghiepVu_KhoaSo("erp_dondathang", this.id, "ngaydonhang", db);
			if (msg.length() > 0)
			{
				db.getConnection().rollback();
				//db.getConnection().setAutoCommit(true);
				return false;
			}
			
			
			if(action.equals("save"))
			{
				query ="\n select scheme from CTKHUYENMAI ctkm " +
				"\n inner join ERP_DONDATHANG_CTKM_TRAKM dhkm on dhkm.CTKMID = ctkm.PK_SEQ  " +
				"\n where dhkm.DONDATHANGID = " + this.id +
				"\n 	and not exists ( select 1 from ERP_Dondathang dh where dh.PK_SEQ = dhkm.DONDATHANGID and dh.ngaydonhang >= ctkm.TUNGAY and dh.ngaydonhang <= ctkm.DENNGAY) ";
				rs = db.get(query);
				if(rs.next())
				{
					this.msg = "Phát sinh CTKM đã hết hạn do thay đổi ngày chứng từ , vui lòng áp lại KM! ";
					db.getConnection().rollback();
					db.getConnection().setAutoCommit(true);
					return false;
		
				}
				
				sqlCHeck =  " select sanpham_fk, sum(soluong)soluong " +
				" from  ("+sqlCHeck+") a " +
				" group by sanpham_fk " ;	
				//Đối lại check 1 loạt sản phẩm của đơn hàng luôn, không cấn check từng SP
				query = "select count(dh.sanpham_fk) as sodong  " +
				"from ERP_Dondathang_SANPHAM dh " +
				"left join  ( " + sqlCHeck + " ) dh_sp on dh.sanpham_fk = dh_sp.sanpham_fk " +
				"where Dondathang_fk = '" + this.id + "' and dh.soluong != dh_sp.soluong" +
				" 	and exists ( select 1 from  ERP_DONDATHANG_CTKM_TRAKM x where x.DONDATHANGID = "+this.id+"  ) ";
				rs = db.get(query);
				rs.next();
				int soDONG = rs.getInt("soDONG");
				if (soDONG > 0)
				{
					db.getConnection().rollback();
					this.msg = "Khi thay đổi thông tin sản phẩm, số lượng trong đơn hàng, bạn phải bấm áp lại khuyến mại.";
					return false;
				}
			}

			query = "delete ERP_Dondathang_SANPHAM where Dondathang_fk = '" + this.id + "'";
			if(!db.update(query))
			{
				this.msg = "Không thể cập nhật ERP_Dondathang_SANPHAM " + query;
				db.getConnection().rollback();
				return false;
			}

			String kbh_fk = this.kbhId;
			query = "select dungchungkenh from NHAPHANPHOI where PK_SEQ = '" + this.nppId + "' ";
			 rs = db.get(query);
			if(rs.next())
			{
				if(rs.getString("dungchungkenh").equals("1"))
					kbh_fk = "100025";
			}
			
			if(this.loaidonhang.equals("0"))  //DON DAT HANG CHI LUU NHUNG SP KO CO SCHEME
			{
				for(int i = 0; i < spMa.length; i++)
				{
					if(spMa[i].trim().length() > 0 && spSCheme[i].trim().length() <= 0 )
					{
						//CHECK SP NAY DA CO KHAI QUY CACH CHUA?
						query =    "select sp.ten, dv.donvi, case when sp.dvdl_fk != dv.pk_seq   " +
						"	then ISNULL( ( select soluong2 / soluong1 from QUYCACH where SANPHAM_FK = sp.PK_SEQ and DVDL1_FK = sp.DVDL_FK and DVDL2_FK = dv.pk_seq ), -1 ) else 1 end as quycach   "  +
						"from SANPHAM sp, DONVIDOLUONG dv " +
						"where sp.MA = '" + util.antiSQLInspection( spMa[i].trim()) + "' and dv.donvi = N'" + util.antiSQLInspection(spDonvi[i].trim()) + "'   ";

						System.out.println("-----CHECK QUY CACH: " + query );
						rs = db.get(query);
						if(rs.next())
						{
							if(rs.getDouble("quycach") <= 0)
							{
								this.msg = "Sản phẩm ( " + rs.getString("ten") + " ) với đơn vị đặt ( " + rs.getString("donvi") + " ) chưa thiết lập quy cách trong DLN ";
								rs.close();
								db.getConnection().rollback();
								return false;
							}
						}
						rs.close();

						 data = null;
						data = dbutils.setObject(khonhan_fk,kbh_fk , this.id,spSoluong[i].replaceAll(",", ""),spGianhap[i].replaceAll(",", ""),spVAT[i].replaceAll(",", ""),spMa[i].trim() );
						query = "insert ERP_Dondathang_SANPHAM(  kho_fk,kbh_fk,Dondathang_fk, SANPHAM_FK, soluong, dongia, thueVAT, dvdl_fk ) " +
						"select ?,?,?, pk_seq,?, ?, ?, ( select pk_Seq from DONVIDOLUONG where donvi = N'" + util.antiSQLInspection( spDonvi[i].trim()) + "' ) " +
						"from SANPHAM where MA = ? ";

						System.out.println("1.Insert NK - SP: " + query);
						if(db.update_v2(query, data)<=0)
						{
							this.msg = "Khong the tao moi ERP_Dondathang_SANPHAM: " + query;
							db.getConnection().rollback();
							return false;
						}
						countsp++;
					}
				}
			}
			else
			{
				for(int i = 0; i < spMa.length; i++)
				{
					if(spMa[i].trim().length() > 0)
					{
						//CHECK SP NAY DA CO KHAI QUY CACH CHUA?
						query =    "select sp.ten, dv.donvi, case when sp.dvdl_fk != dv.pk_seq   " +
						"	then ISNULL( ( select soluong2 / soluong1 from QUYCACH where SANPHAM_FK = sp.PK_SEQ and DVDL1_FK = sp.DVDL_FK and DVDL2_FK = dv.pk_seq ), -1 ) else 1 end as quycach   "  +
						"from SANPHAM sp, DONVIDOLUONG dv " +
						"where sp.MA = '" + util.antiSQLInspection( spMa[i].trim()) + "' and dv.donvi = N'" + util.antiSQLInspection(spDonvi[i].trim()) + "'   ";

						System.out.println("-----CHECK QUY CACH: " + query );
						 rs = db.get(query);
						if(rs.next())
						{
							if(rs.getDouble("quycach") <= 0)
							{
								this.msg = "Sản phẩm ( " + rs.getString("ten") + " ) với đơn vị đặt ( " + rs.getString("donvi") + " ) chưa thiết lập quy cách trong DLN ";
								rs.close();
								db.getConnection().rollback();
								return false;
							}
						}
						rs.close();

						 data = null;
							data = dbutils.setObject(khonhan_fk,kbh_fk , this.id,spSoluong[i].replaceAll(",", ""),spGianhap[i].replaceAll(",", ""),spVAT[i].replaceAll(",", ""),spMa[i].trim() );
							
						query = "insert ERP_Dondathang_SANPHAM(  kho_fk,kbh_fk,Dondathang_fk, SANPHAM_FK, soluong, dongia, thueVAT, dvdl_fk ) " +
						"select ?,?,?, pk_seq, ?, ?, ?, ( select pk_Seq from DONVIDOLUONG where donvi = N'" + spDonvi[i].trim() + "' ) " +
						"from SANPHAM where MA = ? ";

						System.out.println("1.Insert NK - SP: " + query);
						if(db.update_v2(query, data)<=0)
						{
							this.msg = "Khong the tao moi ERP_Dondathang_SANPHAM: " + query;
							db.getConnection().rollback();
							return false;
						}
						countsp++;
					}
				}
			}
			
			
			if(!action.equals("save"))
			{
				query = "DELETE FROM ERP_DONDATHANG_CTKM_TRAKM WHERE DONDATHANGID = '"+ this.id +"'";
				if(!db.update(query))
				{
					this.msg = "Áp khuyến mãi có lỗi.";
					db.getConnection().rollback();
					return false;
				}
				String msgA = Ap_Khuyen_Mai_SellIn_Dac_Biet( db , this.id ,kbh_fk);
				if(msgA.length() > 0 )
				{
					this.msg = msgA;
					db.getConnection().rollback();
					return false;
				}
				
				
				String msgB = LuuKhuyenMai(db, this.id, 0, request,kbh_fk);
				if(msgB.length() > 0 )
				{
					this.msg = msgB;
					db.getConnection().rollback();
					return false;
				} 
			}
			
			String sxtd = CheckSXTD(this.id,db);
			if(sxtd.length()>0){
				this.msg = sxtd;
				db.getConnection().rollback();
				return false;
			}
			

			String msgC = update_giatri_erp_dondathang( db ,this.id );
			if(msgC.length() > 0 )
			{
				this.msg = msgC;
				db.getConnection().rollback();
				return false;
			}
			
			if(countsp - 1 >18)//Nếu số dòng vượt quá 10 thì k cho lưu
			{
				this.msg = "TỔNG SỐ DÒNG CỦA ĐƠN ĐẶT HÀNG KHÔNG ĐƯỢC PHÉP QUÁ 10 DÒNG.";
				db.getConnection().rollback();
				return false;
			}

			db.getConnection().commit();
			db.getConnection().setAutoCommit(true);
		} 
		catch (Exception e) 
		{
			Utility.rollback_throw_exception(db);
			
			this.msg = "Exception: " + e.getMessage();
			return false;
		}

		return true;
	}


	public void createRs() 
	{
		this.getNppInfo();

		//LẤY trực thuộc và loại nhà phân phối, nếu loại là đối tác thì được phép chọn đơn vị tính lúc đặt hàng
		String query = " select tructhuoc_fk, loaiNPP from NHAPHANPHOI where pk_seq = '" + this.nppId + "' ";
		ResultSet rs = db.get(query);
		String tructhuocId = "";
		if(rs != null)
		{
			try 
			{
				if(rs.next())
				{
					this.loaiNPP = rs.getString("loaiNPP");
					tructhuocId = rs.getString("tructhuoc_fk");
				}
				rs.close();
			} 
			catch (Exception e) { }
		}
		System.out.println(" sitecoude = "+ this.sitecode);
		if(this.loaiNPP.equals("0")) //Đặt trực tiếp HO
			this.khoNhanRs = db.get("select PK_SEQ, TEN from erp_khott where trangthai = '1'  ");
		else
		{
			String q= "select PK_SEQ, TEN from KHO where trangthai = '1' and pk_seq in " + this.util.quyen_kho_dathang(this.userId, tructhuocId)+"  order by DIENGIAI";
			this.khoNhanRs = db.get(q);
			System.out.println("sqle ______________:" + q );
			//mặc định là đặt kho sỉ
			if(this.khoNhanId.trim().length() <= 0 )
			{

				this.khoNhanId = "100000";
			}
		}

		this.dvtRs = db.getScrol("select PK_SEQ, DONVI from DONVIDOLUONG where trangthai = '1' ");

		this.dvkdRs = db.get("select PK_SEQ, DONVIKINHDOANH as TEN from DONVIKINHDOANH where TRANGTHAI = '1' ");
		this.kbhRs = db.get("select PK_SEQ, DIENGIAI as TEN from KENHBANHANG where TRANGTHAI = '1' and PK_SEQ in ( select KBH_FK from NHAPP_KBH where NPP_FK = '" + this.nppId + "' ) ");

		query = "select PK_SEQ, MA + ' - ' + TEN as TEN from NHAPHANPHOI where TRANGTHAI = '1' and pk_seq = '" + this.nppId + "' ";
		this.nppRs = db.get(query);

		if(this.nppId.trim().length() > 0)
		{
			query = "select a.PK_SEQ as nppId, d.DVKD_FK, b.KBH_FK  " +
			"From NhaPhanPhoi a left join nhapp_kbh b on b.NPP_FK = a.PK_SEQ left join NHAPP_NHACC_DONVIKD c on c.NPP_FK = b.NPP_FK  " +
			"	left join NHACUNGCAP_DVKD d on d.PK_SEQ = c.NCC_DVKD_FK   " +
			"where a.pk_Seq = '" + this.nppId + "' ";
			ResultSet rsInfo = db.get(query);
			if(rsInfo != null)
			{
				try 
				{
					if(rsInfo.next())
					{
						if(this.dvkdId.trim().length() <= 0)
							this.dvkdId = rsInfo.getString("DVKD_FK");
						if(this.kbhId.trim().length() <= 0 )
							this.kbhId = rsInfo.getString("KBH_FK");
					}
					rsInfo.close();
				} 
				catch (Exception e) {}
			}

			//INIT CONG NO
			/*query = " select a.MA, a.TEN, b.DONVI, ISNULL(HANGMUA.noHANGMUA, 0) as noHANGMUA, ISNULL(KM.noKHUYENMAI, 0) as noKHUYENMAI, " +
					" 			ISNULL(TL.noTICHLUY, 0) as noTICHLUY, ISNULL(TB.noTRUNGBAY, 0) as noTRUNGBAY, 0 as noDOIHANG " +
					" from SANPHAM a inner join DONVIDOLUONG b on a.DVDL_FK = b.PK_SEQ left join " +
					" (	 " +
					" 	 select ddh.PK_SEQ as sanpham_fk, ddh.soluongDAT - ISNULL( daxuat.soluongDAXUAT, 0) as noHANGMUA " +
					" 	 from    " +
					" 	 (    " +
					"  		select sp.PK_SEQ, SUM(dathang.soluong) as soluongDAT     " +
					"  		from    " +
					"  		(    " +
					"  				select a.sanpham_fk,   " +
					"  						case when a.dvdl_fk IS null then a.soluong     " +
					"  							 when a.dvdl_fk = b.DVDL_FK then a.soluong    " +
					"  							 else  a.soluong * ( select SOLUONG1 from QUYCACH where sanpham_fk = a.sanpham_fk and DVDL2_FK = a.dvdl_fk )     " +
					"  											 / ( select SOLUONG2 from QUYCACH where sanpham_fk = a.sanpham_fk and DVDL2_FK = a.dvdl_fk )	 end as soluong  " +
					"  				from ERP_DONDATHANG_SANPHAM a inner join SANPHAM b on a.sanpham_fk = b.PK_SEQ    " +
					"  				where a.dondathang_fk in (    select PK_SEQ from ERP_DONDATHANG where TRANGTHAI = '2' and NPP_FK = '" + this.nppId + "'  and LoaiDonHang = '0'  )   " +
					"  			union ALL  " +
					"  				select b.PK_SEQ,  a.soluong   " +
					"  				from ERP_DONDATHANG_CTKM_TRAKM a inner join SANPHAM b on a.SPMA = b.MA   " +
					"  						inner join CTKHUYENMAI c on a.CTKMID = c.PK_SEQ   " +
					"  				where a.DONDATHANGID in (    select PK_SEQ from ERP_DONDATHANG where TRANGTHAI = '2' and NPP_FK = '" + this.nppId + "' and LoaiDonHang = '0'   )    " +
					"  		)    " +
					"  		dathang inner join SANPHAM sp on dathang.sanpham_fk = sp.PK_SEQ    " +
					"  		group by sp.PK_SEQ   " +
					" 	 )    " +
					" 	 ddh left join     " +
					" 	 (    " +
					"  		select b.sanpham_fk, SUM( b.soluongXUAT ) as soluongDAXUAT    " +
					"  		from ERP_YCXUATKHO a inner join ERP_YCXUATKHO_SANPHAM b on a.PK_SEQ = b.ycxk_fk   " +
					"  		where a.TRANGTHAI in (2)    " +
					"  			and a.PK_SEQ in ( select ycxk_fk from  ERP_YCXUATKHO_DDH where ddh_fk in (  select PK_SEQ from ERP_DONDATHANG where TRANGTHAI = '2' and NPP_FK = '" + this.nppId + "' and LoaiDonHang = '0'  ) )   " +
					"  		group by b.sanpham_fk, b.LOAI, b.SCHEME   " +
					" 	 )   " +
					" 	 daxuat on ddh.PK_SEQ = daxuat.sanpham_fk	 " +
					" ) " +
					" HANGMUA on a.PK_SEQ = HANGMUA.sanpham_fk left join  " +
					" ( " +
					" 	 select ddh.sanpham_fk, ddh.soluongDAT - ISNULL( daxuat.soluongDAXUAT, 0) as noKHUYENMAI " +
					" 	 from    " +
					" 	 (    " +
					" 		select a.sanpham_fk, sum(a.soluong) as soluongDAT  " +
					" 		from ERP_DONDATHANG_SANPHAM a inner join SANPHAM b on a.sanpham_fk = b.PK_SEQ    " +
					" 		where a.dondathang_fk in (    select PK_SEQ from ERP_DONDATHANG where TRANGTHAI = '2' and NPP_FK = '" + this.nppId + "' and LoaiDonHang = '1'   )  " +
					" 		group by  a.sanpham_fk " +
					" 	 )    " +
					" 	 ddh left join     " +
					" 	 (    " +
					"  		select b.sanpham_fk, SUM( b.soluongXUAT ) as soluongDAXUAT    " +
					"  		from ERP_YCXUATKHO a inner join ERP_YCXUATKHO_SANPHAM b on a.PK_SEQ = b.ycxk_fk   " +
					"  		where a.TRANGTHAI in (2)    " +
					"  			and a.PK_SEQ in ( select ycxk_fk from  ERP_YCXUATKHO_DDH where ddh_fk in (  select PK_SEQ from ERP_DONDATHANG where TRANGTHAI = '2' and NPP_FK = '" + this.nppId + "' and LoaiDonHang = '1'  ) )   " +
					"  		group by b.sanpham_fk, b.LOAI, b.SCHEME   " +
					" 	 )   " +
					" 	 daxuat on ddh.sanpham_fk = daxuat.sanpham_fk	 " +
					" ) " +
					" KM on a.PK_SEQ = KM.sanpham_fk left join  " +
					" ( " +
					" 	 select ddh.sanpham_fk, ddh.soluongDAT - ISNULL( daxuat.soluongDAXUAT, 0) as noTICHLUY " +
					" 	 from    " +
					" 	 (    " +
					" 		select a.sanpham_fk, sum(a.soluong) as soluongDAT  " +
					" 		from ERP_DONDATHANG_SANPHAM a inner join SANPHAM b on a.sanpham_fk = b.PK_SEQ    " +
					" 		where a.dondathang_fk in (    select PK_SEQ from ERP_DONDATHANG where TRANGTHAI = '2' and NPP_FK = '" + this.nppId + "' and LoaiDonHang = '2'   )  " +
					" 		group by  a.sanpham_fk " +
					" 	 )    " +
					" 	 ddh left join     " +
					" 	 (    " +
					"  		select b.sanpham_fk, SUM( b.soluongXUAT ) as soluongDAXUAT    " +
					"  		from ERP_YCXUATKHO a inner join ERP_YCXUATKHO_SANPHAM b on a.PK_SEQ = b.ycxk_fk   " +
					"  		where a.TRANGTHAI in (2)    " +
					"  			and a.PK_SEQ in ( select ycxk_fk from  ERP_YCXUATKHO_DDH where ddh_fk in (  select PK_SEQ from ERP_DONDATHANG where TRANGTHAI = '2' and NPP_FK = '" + this.nppId + "' and LoaiDonHang = '2'  ) )   " +
					"  		group by b.sanpham_fk, b.LOAI, b.SCHEME   " +
					" 	 )   " +
					" 	 daxuat on ddh.sanpham_fk = daxuat.sanpham_fk	 " +
					" ) " +
					" TL on a.PK_SEQ = TL.sanpham_fk left join  " +
					" ( " +
					" 	select ddh.sanpham_fk, ddh.soluongDAT - ISNULL( daxuat.soluongDAXUAT, 0) as noTRUNGBAY " +
					" 	 from    " +
					" 	 (    " +
					" 		select a.sanpham_fk, sum(a.soluong) as soluongDAT  " +
					" 		from ERP_DONDATHANG_SANPHAM a inner join SANPHAM b on a.sanpham_fk = b.PK_SEQ    " +
					" 		where a.dondathang_fk in (    select PK_SEQ from ERP_DONDATHANG where TRANGTHAI = '2' and NPP_FK = '" + this.nppId + "' and LoaiDonHang = '3'   )  " +
					" 		group by  a.sanpham_fk " +
					" 	 )    " +
					" 	 ddh left join     " +
					" 	 (    " +
					"  		select b.sanpham_fk, SUM( b.soluongXUAT ) as soluongDAXUAT    " +
					"  		from ERP_YCXUATKHO a inner join ERP_YCXUATKHO_SANPHAM b on a.PK_SEQ = b.ycxk_fk   " +
					"  		where a.TRANGTHAI in (2)    " +
					"  			and a.PK_SEQ in ( select ycxk_fk from  ERP_YCXUATKHO_DDH where ddh_fk in (  select PK_SEQ from ERP_DONDATHANG where TRANGTHAI = '2' and NPP_FK = '" + this.nppId + "' and LoaiDonHang = '3'  ) )   " +
					"  		group by b.sanpham_fk, b.LOAI, b.SCHEME   " +
					" 	 )   " +
					" 	 daxuat on ddh.sanpham_fk = daxuat.sanpham_fk " +
					" ) " +
					" TB on a.PK_SEQ = TB.sanpham_fk ";

			System.out.println("CONG NO: " + query);
			this.congnoRs = db.get(query);*/

		}

	}


	private String LuuKhuyenMai(Idbutils db ,String dhId, int loai, HttpServletRequest request, String kbh_fk)
	{
		String query = "";
		System.out.println("danh_sach_ctkm_Str ne : "+ this.danh_sach_ctkm_Str);
		try 
		{
			if(this.danh_sach_ctkm_Str.trim().length() > 0)
			{
				JsonArray  jsonCTKM = (JsonArray) new JsonParser().parse(this.danh_sach_ctkm_Str);   
				for( int i = 0 ; i < jsonCTKM.size() ; i++)
				{
					JsonObject oCTKM = (JsonObject) jsonCTKM.get(i);
					String ctkmId = oCTKM.get("id").getAsString();
					String scheme = oCTKM.get("scheme").getAsString();
					String sosuat = oCTKM.get("sosuat").getAsString();
					String tra =  oCTKM.get("trakhuyenmai").getAsString();
					System.out.println(tra);
					JsonArray  jsonTraKM = (JsonArray) new JsonParser().parse(tra);   
					for(int x = 0; x < jsonTraKM.size(); x++ )
					{
						JsonObject oTra = (JsonObject) jsonTraKM.get(x);
						String tkmId = oTra.get("id").getAsString();
						String loaiTKM  = oTra.get("loai").getAsString();
						String hinhthucTKM  = oTra.get("hinhthuc").getAsString();
						System.out.println("tkmId : "+ tkmId+" - loaiTKM : "+ loaiTKM);
						if(loaiTKM.equals("1") || loaiTKM.equals("2")  )
						{
							String tongtien = oTra.get("tongtien").getAsString();
							// lưu tổng tiền
							query = "\n insert erp_dondathang_ctkm_trakm(dondathangId,ctkmId,TRAKMID,SOXUAT,TONGGIATRI) " +
									"\n select  "+dhId+", ctkm.pk_seq, "+tkmId+", "+sosuat+","+tongtien+"" +
									"\n from ctkhuyenmai ctkm " +
									"\n where ctkm.pk_seq = "+ctkmId;
							if(!db.update(query))
							{
								return "Lỗi lưu tổng tiền.";
							}
						}
						else  if(loaiTKM.equals("3")  ) //trả sp
						{
							if(hinhthucTKM.equals("1"))// lưu cố định sp
							{
								String spTra = oTra.get("spTra").getAsString();
								JsonArray  json_spTra = (JsonArray) new JsonParser().parse(spTra);  
								for( int i_trasp = 0 ; i_trasp < json_spTra.size(); i_trasp ++ )
								{
									JsonObject oTraSP = (JsonObject) json_spTra.get(i_trasp);
									String spId = oTraSP.get("spId").getAsString();
									String SpMa = oTraSP.get("MA").getAsString(); // để gửi thông báo ko đủ tồn kho hoặc cái quằn gì đó
									String sl = oTraSP.get("SOLUONG").getAsString();
									query = "\n insert erp_dondathang_ctkm_trakm(kbh_fk,dondathangId,ctkmId,TRAKMID,SOXUAT,TONGGIATRI,SPMA,SOLUONG,sanpham_fk,kho_fk) " +
											"\n select  "+kbh_fk+","+dhId+", ctkm.pk_seq, "+tkmId+", "+sosuat+",0, sp.ma, "+sl+", sp.pk_seq, ctkm.kho_fk" +
											"\n from ctkhuyenmai ctkm , sanpham sp" +
											"\n where ctkm.pk_seq = "+ctkmId+" and sp.pk_seq = "+ spId;
									if(!db.update(query))
									{
										return "Lỗi lưu sản phẩm cố định.";
									}
								}
							}
							else if(hinhthucTKM.equals("2")) // bất kì trong
							{
								String spTra = oTra.get("spTra").getAsString();
								double tongluong =  Utility.parseDouble( oTra.get("tongluong").getAsString()) * Utility.parseDouble( sosuat );
								if(tongluong <=0) {	return scheme +" không lấy được tổng lượng trả theo bất kì trong "; }
								double datra = 0;
								JsonArray  json_spTra = (JsonArray) new JsonParser().parse(spTra);  
								for( int i_trasp = 0 ; i_trasp < json_spTra.size(); i_trasp ++ )
								{
									JsonObject oTraSP = (JsonObject) json_spTra.get(i_trasp);
									String spId = oTraSP.get("spId").getAsString();
									String SpMa = oTraSP.get("MA").getAsString(); // để gửi thông báo ko đủ tồn kho hoặc cái quằn gì đó
									String _sl = request.getParameter(ctkmId+"__" + tkmId +"__"+spId);
									System.out.println("ctkmId__tkmId__spId : "+ ctkmId+"__" + tkmId +"__"+spId);
									System.out.println("_sl : "+ _sl);
									if(_sl == null) _sl=  ""; _sl = _sl.replace(",","");
									double sl = Utility.parseDouble(  _sl);
									if(sl > 0)
									{
										query = "\n insert erp_dondathang_ctkm_trakm(kbh_fk,dondathangId,ctkmId,TRAKMID,SOXUAT,TONGGIATRI,SPMA,SOLUONG,sanpham_fk,kho_fk) " +
												"\n select  "+kbh_fk+","+dhId+", ctkm.pk_seq, "+tkmId+", "+sosuat+",0, sp.ma, "+sl+", sp.pk_seq, ctkm.kho_fk" +
												"\n from ctkhuyenmai ctkm , sanpham sp" +
												"\n where ctkm.pk_seq = "+ctkmId+" and sp.pk_seq =   "+ spId;
										System.out.println("query : "+ query);
										if(!db.update(query))
										{
											return "Lỗi lưu sản phẩm bất kỳ trong.";
										}
										datra +=sl;
									}
								}
								if(datra != tongluong)
								{
									return scheme +" tổng lượng nhập ("+datra+") vượt tổng lượng cho phép ("+tongluong+") ";
								}
							}
						}
						else
						{
							return "Có lỗi trong khi áp khuyến mãi.";
						}
					}
				}
				return ""; 
			}
			else return "";
		} catch (Exception e1) { e1.printStackTrace(); return "Exception:" + e1.getMessage(); }
	}
	
	public ResultSet getKhuyenMaiRs()
	{
		if( this.id != null && this.id.length() > 0)
		{
			String query =  "\n select isnull(b.MA, ' ') as MA, isnull(b.TEN, ' ') as TEN, isnull(c.DONVI, ' ') as donvi, d.SCHEME, isnull(a.soluong, 0) as soluong, a.tonggiatri " +
							"\n from ERP_DONDATHANG_CTKM_TRAKM a inner join SANPHAM b on a.SPMA = b.MA  " +
							"\n left join DONVIDOLUONG c on b.DVDL_FK = c.PK_SEQ  " +
							"\n inner join CTKHUYENMAI d on a.ctkmID = d.PK_SEQ " +
							"\n where a.dondathangID = '" + this.id + "'  " +
							"\n union all " +
							"\n select N'', N'Trả tiền' TEN, '' as donvi, d.SCHEME, 1 , a.tonggiatri " +
							"\n from ERP_DONDATHANG_CTKM_TRAKM a  " +
							"\n inner join CTKHUYENMAI d on a.ctkmID = d.PK_SEQ " +
							"\n where a.sanpham_fk is null and  a.dondathangID = '" + this.id + "'  " ;
			System.out.println("--getKMRS: " + query);
			return db.get(query);
		}
		return null;
	}
	private void initSANPHAM() 
	{
		String query =  "select b.MA, b.TEN, DV.donvi, a.soluong, a.dongia, a.thueVAT    " +
		" from ERP_Dondathang_SANPHAM a inner Join SanPham b on a.SANPHAM_FK = b.PK_SEQ    " +
		" 		INNER JOIN DONVIDOLUONG DV ON DV.PK_SEQ = a.DVDL_FK       " +
		"where a.Dondathang_FK = '" + this.id + "' ";

		System.out.println("--INIT SP: " + query);
		ResultSet spRs = db.get(query);

		NumberFormat formater = new DecimalFormat("##,###,###");
		NumberFormat formater2 = new DecimalFormat("##,###,###.####");
		if(spRs != null)
		{
			try 
			{
				String spMA = "";
				String spTEN = "";
				String spDONVI = "";
				String spSOLUONG = "";
				String spGIANHAP = "";
				String spVAT = "";
				String spSCHEME = "";

				while(spRs.next())
				{
					spMA += spRs.getString("MA") + "__";
					spTEN += spRs.getString("TEN") + "__";
					spDONVI += spRs.getString("DONVI") + "__";
					spSOLUONG += formater.format(spRs.getDouble("SOLUONG")) + "__";
					//spGIANHAP += formater.format(spRs.getDouble("DONGIA")) + "__";
					spGIANHAP += formater2.format(spRs.getDouble("DONGIA")) + "__";
					spVAT += spRs.getDouble("thueVAT") + "__";
					spSCHEME += " __";
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

					spVAT = spVAT.substring(0, spVAT.length() - 2);
					this.spVAT = spVAT.split("__");

					spSCHEME = spSCHEME.substring(0, spSCHEME.length() - 2);
					this.spSCheme = spSCHEME.split("__");

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
		String query = "select isdhKhac,ngaydonhang, ngaydenghi, ISNULL(ghichu, '') as ghichu, dvkd_fk, kbh_fk, npp_fk, kho_fk, chietkhau, vat, loaidonhang,isKm " +
		"from ERP_Dondathang where pk_seq = '" + this.id + "'";
		System.out.println("____INIT NHAP KHO: " + query);
		ResultSet rs = db.get(query);
		if(rs != null)
		{
			try 
			{
				if(rs.next())
				{
					this.ngayyeucau = rs.getString("ngaydonhang");
					this.ngaydenghi = rs.getString("ngaydenghi");
					this.ghichu = rs.getString("ghichu");
					this.dvkdId = rs.getString("dvkd_fk");
					this.kbhId = rs.getString("kbh_fk");
					this.nppId = rs.getString("npp_fk");
					this.khoNhanId = rs.getString("kho_fk");
					this.loaidonhang = rs.getString("loaidonhang");
					this.chietkhau = rs.getString("chietkhau");
					this.vat = rs.getString("vat");
					this.iskm = rs.getString("isKm")==null?"0": rs.getString("isKm");
					this.isdhKhac = rs.getString("isdhKhac")==null?"0": rs.getString("isdhKhac");
				}
				rs.close();
			} 
			catch (Exception e) 
			{
				System.out.println("---LOI INIT: " + e.getMessage());
			}
		}

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

	private String getDateTime() 
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


	public String getLoaidonhang() {

		return this.loaidonhang;
	}


	public void setLoaidonhang(String loaidonhang) {

		this.loaidonhang = loaidonhang;
	}


	public String getChietkhau() {

		return this.chietkhau;
	}


	public void setChietkhau(String chietkhau) {

		this.chietkhau = chietkhau;
	}


	public String getVat() {

		return this.vat;
	}


	public void setVat(String vat) {

		this.vat = vat;
	}


	public String getDvkdId() {

		return this.dvkdId;
	}


	public void setDvkdId(String dvkdId) {

		this.dvkdId = dvkdId;
	}


	public ResultSet getDvkdRs() {

		return this.dvkdRs;
	}


	public void setDvkdRs(ResultSet dvkdRs) {

		this.dvkdRs = dvkdRs;
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

	public String getNgaydenghi() {

		return this.ngaydenghi;
	}

	public void setNgaydenghi(String ngaydenghi) {

		this.ngaydenghi = ngaydenghi;
	}

	public ResultSet getDvtRs() {

		return this.dvtRs;
	}


	public void setDvtRs(ResultSet dvtRs) {

		this.dvtRs = dvtRs;
	}


	public String getSchemeId() {

		return this.schemeId;
	}


	public void setSchemeId(String kbhId) {

		this.schemeId = kbhId;
	}


	public ResultSet getSchemeRs() {

		return this.schemeRs;
	}


	public void setSchemeRs(ResultSet schemeRs) {

		this.schemeRs = schemeRs;
	}


	public String[] getSpScheme() {

		return this.spSCheme;
	}


	public void setSpScheme(String[] spScheme) {

		this.spSCheme = spScheme;
	}

	public ResultSet getCongnoRs() {

		return this.congnoRs;
	}


	public void setCongnoRs(ResultSet congnoRs) {

		this.congnoRs = congnoRs;
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

	private void getNppInfo()
	{		
		//Phien ban moi
		geso.dms.distributor.util.Utility util=new geso.dms.distributor.util.Utility();
		this.nppId=util.getIdNhapp(this.userId);
		this.nppTen=util.getTenNhaPP();
		//this.dangnhap = util.getDangNhap();
		this.sitecode=util.getSitecode();
	}


	public String[] getSpChietkhau() {

		return this.spChietkhau;
	}


	public void setSpChietkhau(String[] spChietkhau) {

		this.spChietkhau = spChietkhau;
	}

	public String[] getSpVat() {

		return this.spVAT;
	}


	public void setSpVat(String[] spVat) {

		this.spVAT = spVat;
	}

	public String[] getDhck_diengiai() {

		return this.dhCk_diengiai;
	}


	public void setDhck_Diengiai(String[] obj) {

		this.dhCk_diengiai = obj;
	}


	public String[] getDhck_giatri() {

		return this.dhCk_giatri;
	}


	public void setDhck_giatri(String[] obj) {

		this.dhCk_giatri = obj;
	}


	public String[] getDhck_loai() {

		return this.dhCk_loai;
	}


	public void setDhck_loai(String[] obj) {

		this.dhCk_loai = obj;
	}


	public String getLoainpp() {

		return this.loaiNPP;
	}


	public void setLoainpp(String loainpp) {

		this.loaiNPP = loainpp;
	}

	String iskm;
	public String getIsKm()
	{
		return iskm;
	}

	public void setIsKm(String iskm)
	{
		this.iskm = iskm;
	}


	public String getIsdhKhac() {
		return isdhKhac;
	}
	public void setIsdhKhac(String isdhKhac) {
		this.isdhKhac = isdhKhac;
	}

	public String getView() {
		return View;
	}
	public void setView(String view) {
		View = view;
	}

	@Override
	public String getData() {
		// TODO Auto-generated method stub
		return this.data;
	}

	@Override
	public void setData(String data) {
		// TODO Auto-generated method stub
		this.data = data;
	}

	@Override
	public String getDieuchinh() {
		// TODO Auto-generated method stub
		return this.dieuchinh;
	}

	@Override
	public void setDieuchinh(String dc) {
		// TODO Auto-generated method stub
		this.dieuchinh = dc;
	}

	@Override
	public String getData_Save() {
		// TODO Auto-generated method stub
		return this.data_save;
	}

	@Override
	public void setData_Save(String datasave) {
		// TODO Auto-generated method stub
		this.data_save = datasave;
	}
	String danh_sach_ctkm_Str = "";
	public String getDanh_sach_ctkm_Str() {
		return danh_sach_ctkm_Str;
	}
	public void setDanh_sach_ctkm_Str(String danh_sach_ctkm_Str) {
		this.danh_sach_ctkm_Str = danh_sach_ctkm_Str;
	}
	private Object[] appendObjectArrayValue(Object[] obj, Object[] newObj) {
		ArrayList<Object> temp = new ArrayList<Object>(Arrays.asList(obj));
		for (int i = 0; i < newObj.length; i++) {
			temp.add(newObj[i]);
		}
		return temp.toArray();
	}

}
