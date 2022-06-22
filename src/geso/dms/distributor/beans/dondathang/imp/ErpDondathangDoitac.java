package geso.dms.distributor.beans.dondathang.imp;

import geso.dms.distributor.db.sql.dbutils;
import geso.dms.center.db.sql.Idbutils;
import geso.dms.center.util.Utility;
import geso.dms.distributor.beans.dondathang.IErpDondathangDoitac;
import geso.dms.distributor.beans.hoadontaichinhNPP.imp.ErpHoadontaichinhNPP;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Enumeration;
import java.util.Hashtable;

import javax.servlet.http.HttpServletRequest;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

public class ErpDondathangDoitac implements IErpDondathangDoitac
{

	String userId;
	String id;

	String ngayyeucau;
	String ngaydenghi;
	String ghichu;

	String msg;
	String trangthai;

	String loaidonhang; // 0 đơn đặt hàng, 1 đơn hàng khuyến mại ứng hàng, 3 đơn
	// hàng khuyến mại tích lũy, 4 đơn hàng trưng bày, 5 đơn
	// hàng khác
	String chietkhau;
	String vat;

	String khoNhanId;
	ResultSet khoNhanRs;

	String dungchungKENH;
	String khId;
	ResultSet khRs;

	String nppId;
	String nppTen;
	String sitecode;

	String congno;
	String maddh;

	int ngay_Chenhlech;
	String quanlykho = "";
	public String getQuanlykho() {
		return quanlykho;
	}
	public void setQuanlykho(String quanlykho) {
		this.quanlykho = quanlykho;
	}

	NumberFormat formater_1sole = new DecimalFormat("########.#");

	public String getMaddh() {
		return maddh;
	}

	public void setMaddh(String maddh) {
		this.maddh = maddh;
	}

	String dvkdId;
	ResultSet dvkdRs;

	String kbhId;
	ResultSet kbhRs;
	String isdhk;
	public String getIsdhk() {
		return isdhk;
	}

	public void setIsdhk(String isdhk) {
		this.isdhk = isdhk;
	}

	String isgia;
	public String getIsgia() {
		return isgia;
	}

	public void setIsgia(String isgia) {
		this.isgia = isgia;
	}

	ResultSet dvtRs;

	String[] spId;
	String[] spMa;
	String[] spTen;
	String[] spDonvi;
	String[] spSoluong;
	String[] spGianhap;
	String[] spChietkhau;
	String[] spVAT;
	String[] spSCheme;
	String[] spSoluongton;
	String[] spGiagoc;
	String[] sptonkhocn;



	public String[] getSpSoluongton()
	{
		return spSoluongton;
	}

	public void setSpSoluongton(String[] spSoluongton)
	{
		this.spSoluongton = spSoluongton;
	}

	String[] dhCk_diengiai;
	String[] dhCk_giatri;
	String[] dhCk_loai;

	ResultSet congnoRs;
	Hashtable<String, String> sanpham_soluong;

	dbutils db;
	Utility util;

	public ErpDondathangDoitac()
	{
		this.id = "";
		this.ngayyeucau = getDateTime();
		this.ngaydenghi = "";
		this.ghichu = "";
		this.khoNhanId = "";
		this.khId = "";
		this.msg = "";
		this.loaidonhang = "0";
		this.trangthai = "0";
		this.chietkhau = "0";
		this.vat = "10";
		this.dvkdId = "";
		this.kbhId = "";
		this.dungchungKENH = "0";
		this.congno = "0";
		this.iskm="0";
		this.sanpham_soluong = new Hashtable<String, String>();
		this.util = new Utility();
		this.db = new dbutils();
		this.isdhk="0";
		this.isgia="0";
	}

	public ErpDondathangDoitac(String id)
	{
		this.id = id;
		this.ngayyeucau = getDateTime();
		this.ngaydenghi = "";
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
		this.dungchungKENH = "0";
		this.iskm="0";
		this.sanpham_soluong = new Hashtable<String, String>();
		this.util = new Utility();
		this.db = new dbutils();
		this.isdhk="0";
		this.isgia="0";
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
						"\n from erp_dondathangnpp a inner join ERP_DONDATHANGNPP_CTKM_TRAKM b on a.pk_Seq=b.DONDATHANGID 						  "+
						"\n inner join ctkhuyenmai km on km.pk_seq=b.ctkmid where a.pk_Seq != "+dhid+" and isnull(km.ap_dung_npp,0)=1			  "+
						"\n and km.pk_seq in (select ctkmid from ERP_DONDATHANGNPP_CTKM_TRAKM where DONDATHANGID="+dhid+") and a.trangthai !=3 	  "+
						"\n and km.soxuattoida<>0																								  "+
						"\n and a.NPP_DAT_FK  = (select NPP_DAT_FK from erp_dondathangnpp where pk_seq="+dhid+" ) 								  "+
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


		Utility uilt_kho=new Utility();

		String ngayhoadon_ =uilt_kho.getngayhoadon(this.userId,db,this.ngayyeucau,this.khId,1);

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

		if( this.khId.trim().length() <= 0 )
		{
			this.msg = "Vui lòng chọn đối tác đặt hàng";
			return false;
		}

		if( this.khoNhanId.trim().length() <= 0 )
		{
			this.msg = "Vui lòng chọn kho đặt hàng";
			return false;
		}

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

			String query = 
				" insert ERP_DondathangNPP(ngaydonhang, ngaydenghi, loaidonhang, ghichu, trangthai, dvkd_fk, kbh_fk, npp_fk, npp_dat_fk, kho_fk, chietkhau, vat, ngaytao, nguoitao, ngaysua, nguoisua, NPP_DACHOT,isKM,isdhkhac,isingia,CHECKKHO, ngaygiochot) " +
				" values('" + this.ngayyeucau + "', '" + this.ngaydenghi + "', '" + this.loaidonhang + "', N'" + this.ghichu + "', 99, '" + dvkdId + "', '" + kbhId + "', '" + nppId + "', '" + this.khId + "', " + khonhan_fk + ", '" + chietkhau + "', '" + vat + "', '" + getDateTime() + "', '" + this.userId + "', '" + 
				getDateTime() + "', '" + this.userId + "', '0','"+this.iskm+"',"+this.isdhk+","+this.isgia+" ,'1','"+ getDateTime1()+"')";

			System.out.println("1.Insert DDH: " + query);
			if(!db.update(query))
			{
				this.msg = "Không thể tạo mới ERP_DondathangNPP " + query;
				db.getConnection().rollback();
				return false;
			}

			//LAY ID
			ResultSet rsDDH = db.get("select SCOPE_IDENTITY() as ID ");
			if(rsDDH.next())
			{
				this.id = rsDDH.getString("ID");
			}
			rsDDH.close();

			String kbh_fk = this.kbhId;
			query = "select dungchungkenh from NHAPHANPHOI where PK_SEQ = '" + this.nppId + "' ";
			ResultSet rs = db.get(query);
			if(rs.next())
			{
				if(rs.getString("dungchungkenh").equals("1"))
					kbh_fk = "100025";
			}

			System.out.println("DDH ID: " + this.id);
			for(int i = 0; i < spMa.length; i++)
			{
				if(spMa[i].trim().length() > 0 && spSCheme[i].trim().length() <= 0 )
				{
					//CHECK SP NAY DA CO KHAI QUY CACH CHUA?
					query =    "select sp.ten, dv.donvi, case when sp.dvdl_fk != dv.pk_seq   " +
					"	then ISNULL( ( select soluong2 / soluong1 from QUYCACH where SANPHAM_FK = sp.PK_SEQ and DVDL1_FK = sp.DVDL_FK and DVDL2_FK = dv.pk_seq ), -1 ) else 1 end as quycach   "  +
					"from SANPHAM sp, DONVIDOLUONG dv " +
					"where sp.MA = '" + spMa[i].trim() + "' and dv.donvi = N'" + spDonvi[i].trim() + "'   ";

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

					String ck = "0";
					if(spChietkhau[i].trim().length() > 0)
						ck = spChietkhau[i].trim().replaceAll(",", "");

					String thueVAT = this.spVAT[i].trim().replaceAll(",", "");
					if(thueVAT.trim().length() < 0)
						thueVAT = "0";

					query = "insert ERP_DondathangNPP_SANPHAM( kho_fk,kbh_fk,Dondathang_fk, SANPHAM_FK, soluong, dongia, chietkhau, thueVAT, dvdl_fk, dongiagoc ) " +
					"select "+khonhan_fk+","+kbh_fk+",'" + this.id + "', pk_seq, '" + spSoluong[i].replaceAll(",", "") + "', '" + spGianhap[i].replaceAll(",", "") + "', '" + ck + "', '" + thueVAT + "', ( select pk_Seq from DONVIDOLUONG where donvi = N'" + spDonvi[i].trim() + "' ), "+ spGiagoc[i].replaceAll(",", "")  +" " +
					"from SANPHAM where MA = '" + spMa[i].trim() + "' ";

					System.out.println("1.Insert NK - SP: " + query);
					if(!db.update(query))
					{
						this.msg = "Khong the tao moi ERP_Dondathang_SANPHAM: " + query;
						db.getConnection().rollback();
						return false;
					}

				}
			}

			String sql = "DELETE FROM ERP_DONDATHANGNPP_CTKM_TRAKM WHERE DONDATHANGID = '"+ this.id +"'";
			if(!db.update(sql))
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
			
			
			String msgB = LuuKhuyenMai(db, this.id, 0, request, kbh_fk);
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
			
			String kqVat = geso.dms.center.util.Utility.CheckVat( db , "ERP_DondathangNPP_SANPHAM","thueVAT", "Dondathang_fk", this.id );
			if(kqVat.trim().length() > 0)
			{
				this.msg = kqVat;
				db.getConnection().rollback();
				db.getConnection().setAutoCommit(true);
				return false;
			}

			


			query = "Update ERP_DondathangNPP set trangthai=0 " +
			"where pk_seq= "+this.id;
			if(!db.update(query))
			{
				msg = "Khong the cap nhat ERP_KHOTT_SANPHAM: " + query;
				db.getConnection().rollback();
				rs.close();
				return false;
			}

			String msgC = update_giatri_erp_dondathangnpp( db ,this.id );
			if(msgC.length() > 0 )
			{
				this.msg = msgB;
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

	public boolean chot_new()
	{

		Utility uilt_kho=new Utility();
		String ngayhoadon_=uilt_kho.getngayhoadon(this.userId, db,this.ngayyeucau,this.khId,1);

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

		if( this.khId.trim().length() <= 0 )
		{
			this.msg = "Vui lòng chọn đối tác đặt hàng";
			return false;
		}

		if( this.khoNhanId.trim().length() <= 0 )
		{
			this.msg = "Vui lòng chọn kho đặt hàng";
			return false;
		}

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

			String query = 
				" insert ERP_DondathangNPP(ngaydonhang, ngaydenghi, loaidonhang, ghichu, trangthai, dvkd_fk, kbh_fk, npp_fk, npp_dat_fk, kho_fk, chietkhau, vat, ngaytao, nguoitao, ngaysua, nguoisua, NPP_DACHOT,isKM,isdhkhac,isingia, ngaygiochot) " +
				" values('" + this.ngayyeucau + "', '" + this.ngaydenghi + "', '" + this.loaidonhang + "', N'" + this.ghichu + "', '0', '" + dvkdId + "', '" + kbhId + "', '" + nppId + "', '" + this.khId + "', " + khonhan_fk + ", '" + chietkhau + "', '" + vat + "', '" + getDateTime() + "', '" + this.userId + "', '" + getDateTime1() + "', '" 
				+ this.userId + "', '0','"+this.iskm+"',"+this.isdhk+","+this.isgia+" ,'"+ getDateTime()+"')";

			System.out.println("1.Insert DDH: " + query);
			if(!db.update(query))
			{
				this.msg = "Không thể tạo mới ERP_DondathangNPP " + query;
				db.getConnection().rollback();
				return false;
			}

			//LAY ID
			ResultSet rsDDH = db.get("select SCOPE_IDENTITY() as ID ");
			if(rsDDH.next())
			{
				this.id = rsDDH.getString("ID");
			}
			rsDDH.close();

			String kbh_fk = this.kbhId;
			query = "select dungchungkenh from NHAPHANPHOI where PK_SEQ = '" + this.nppId + "' ";
			ResultSet rs = db.get(query);
			if(rs.next())
			{
				if(rs.getString("dungchungkenh").equals("1"))
					kbh_fk = "100025";
			}

			System.out.println("DDH ID: " + this.id);
			for(int i = 0; i < spMa.length; i++)
			{
				if(spMa[i].trim().length() > 0 && spSCheme[i].trim().length() <= 0 )
				{
					//CHECK SP NAY DA CO KHAI QUY CACH CHUA?
					query =    "select sp.ten, dv.donvi, case when sp.dvdl_fk != dv.pk_seq   " +
					"	then ISNULL( ( select soluong2 / soluong1 from QUYCACH where SANPHAM_FK = sp.PK_SEQ and DVDL1_FK = sp.DVDL_FK and DVDL2_FK = dv.pk_seq ), -1 ) else 1 end as quycach   "  +
					"from SANPHAM sp, DONVIDOLUONG dv " +
					"where sp.MA = '" + spMa[i].trim() + "' and dv.donvi = N'" + spDonvi[i].trim() + "'   ";

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

					String ck = "0";
					if(spChietkhau[i].trim().length() > 0)
						ck = spChietkhau[i].trim().replaceAll(",", "");

					String thueVAT = this.spVAT[i].trim().replaceAll(",", "");
					if(thueVAT.trim().length() < 0)
						thueVAT = "0";

					query = "insert ERP_DondathangNPP_SANPHAM( Dondathang_fk, SANPHAM_FK, soluong, dongia, chietkhau, thueVAT, dvdl_fk, dongiagoc ) " +
					"select '" + this.id + "', pk_seq, '" + spSoluong[i].replaceAll(",", "") + "', '" + spGianhap[i].replaceAll(",", "") + "', '" + ck + "', '" + thueVAT + "', ( select pk_Seq from DONVIDOLUONG where donvi = N'" + spDonvi[i].trim() + "' ), "+ spGiagoc[i].replaceAll(",", "")  +" " +
					"from SANPHAM where MA = '" + spMa[i].trim() + "' ";

					System.out.println("1.Insert NK - SP: " + query);
					if(!db.update(query))
					{
						this.msg = "Khong the tao moi ERP_Dondathang_SANPHAM: " + query;
						db.getConnection().rollback();
						return false;
					}

				}
			}

			//CHECK BOOKED THEO DV CHUAN
			query =  "select sp.dvdl_fk,dvCHUAN, khoxuat_fk as kho_fk, npp_fk, kbh_fk, sp.PK_SEQ, sp.TEN, SUM(dathang.soluong) as soluongXUAT,  " +
			"	ISNULL( ( select AVAILABLE from NHAPP_KHO where kho_fk = dathang.khoxuat_fk and sanpham_fk = sp.PK_SEQ and kbh_fk = dathang.kbh_fk and npp_fk = dathang.npp_fk ), 0) as tonkho  " +
			" from     " +
			" (     " +
			"	select c.kho_fk as khoxuat_fk, c.npp_fk, '" + kbh_fk + "' kbh_fk, a.sanpham_fk,a.DVDL_FK as dvCHUAN,     " +
			"			case when a.dvdl_fk IS null then a.soluong      " +
			"				 when a.dvdl_fk = b.DVDL_FK then a.soluong     " +
			"				 else  a.soluong * ( select SOLUONG1 from QUYCACH where sanpham_fk = a.sanpham_fk and DVDL2_FK = a.dvdl_fk and dvdl1_fk = b.dvdl_fk )      " +
			"								 / ( select SOLUONG2 from QUYCACH where sanpham_fk = a.sanpham_fk and DVDL2_FK = a.dvdl_fk and dvdl1_fk = b.dvdl_fk )	 end as soluong   " +
			"	from ERP_DONDATHANGNPP_SANPHAM a inner join SANPHAM b on a.sanpham_fk = b.PK_SEQ  " +
			"			inner join ERP_DONDATHANGNPP c on a.dondathang_fk = c.pk_seq    " +
			"	where a.dondathang_fk in ( " + this.id + " )     " +
			")     " +
			"dathang inner join SANPHAM sp on dathang.sanpham_fk = sp.PK_SEQ   " +
			"group by khoxuat_fk, npp_fk, kbh_fk, sp.PK_SEQ, sp.TEN ,sp.dvdl_fk,dvCHUAN ";

			System.out.println("--CHECK TON KHO: " + query);

			rs = db.get(query);

			while(rs.next())
			{
				String khoID = rs.getString("kho_fk");
				String kbhID = rs.getString("kbh_fk");
				String nppID = rs.getString("npp_fk");
				String spID = rs.getString("PK_SEQ");
				String dvCHUAN=rs.getString("dvCHUAN");
				String DVDL_FK=rs.getString("DVDL_FK");
				double soluong = rs.getDouble("soluongXUAT");
				double tonkho = rs.getDouble("tonkho");

				String spten=rs.getString("ten");

				if(soluong > tonkho)
				{
					msg = "Sản phẩm ( " + rs.getString("TEN") + " ) với số lượng yêu cầu ( " + rs.getString("soluongXUAT") + " ) không đủ tồn kho ( " + rs.getString("tonkho") + " ). Vui lòng kiểm tra lại.";
					db.getConnection().rollback();
					rs.close();
					return false;
				}

				String sqldh="select convert(char(10),Created_Date,126) NGAYGIO_TAO from ERP_DONDATHANGNPP where pk_Seq="+this.id;
				String ngaytaodh="";
				ResultSet rsdh=db.get(sqldh);
				while (rsdh.next())
				{
					ngaytaodh=rsdh.getString("NGAYGIO_TAO");
				}
				rsdh.close();


				soluong =Double.parseDouble(formater_1sole.format(soluong));

				String msg1=uilt_kho.Update_NPP_Kho_Sp(ngayyeucau, "Tạo mới đơn hàng đối tác ErpDondathangDoitac.java 485 " 
						, db, khoID, spID, nppID, kbhID, 0, soluong, (-1)* soluong, 0);
				if(msg1.length() >0){
					msg =msg1;
					db.getConnection().rollback();
					rs.close();
					return false;
				}

				// đề xuất lô để booked ngay 

				query=		" select KHO_FK,SANPHAM_FK,KBH_FK,SOLO,NGAYHETHAN,NGAYNHAPKHO,available  from NHAPP_KHO_CHITIET "+  
				" where NPP_FK ="+nppID+" and KBH_FK= " +kbhID +
				" and KHO_FK="+khoID+"  and SANPHAM_FK =  "+ spID +
				" AND AVAILABLE >0  and NGAYNHAPKHO<='"+ngayhoadon_+"'"+
				" order by NGAYHETHAN ,NGAYNHAPKHO,AVAILABLE ";
				ResultSet rssp=db.get(query);
				double soluongdenghi=soluong ;

				while(rssp.next() && soluongdenghi >0){
					double soluong_avai= rssp.getDouble("AVAILABLE");
					double soluongcapnhat=0;
					if(soluong_avai >soluongdenghi){
						soluongcapnhat= soluongdenghi;
						soluongdenghi=0;
					}else{
						soluongcapnhat =soluong_avai;
						soluongdenghi =soluongdenghi - soluong_avai;
					}
					String solo=rssp.getString("SOLO");
					String ngaynhapkho=rssp.getString("ngaynhapkho");
					String ngayhethan=rssp.getString("ngayhethan");
					String _khoid=rssp.getString("kho_fk");
					String _kbhid=rssp.getString("KBH_FK");
					// cập nhật vào bảng đơn hàng sp _chitiet
					double soluongcapnhat_quydoi ;
					if(dvCHUAN.equals(DVDL_FK)){
						// nếu là đơn vị giống nhau
						soluongcapnhat_quydoi= soluongcapnhat;

					}else{
						query=" SELECT qc.SOLUONG1,qc.SOLUONG2 FROM QUYCACH qc WHERE SANPHAM_FK="+spID+" AND DVDL1_FK="+DVDL_FK+"  and qc.DVDL2_FK="+dvCHUAN;
						ResultSet rs1=db.get(query);
						if(rs1.next()){
							soluongcapnhat_quydoi = soluongcapnhat * rs1.getDouble("SOLUONG2")/ rs1.getDouble("SOLUONG1");

						}else{
							this.msg="Không thể xác định quy đổi của sản phẩm : "+rs.getString("ten");;
							db.getConnection().rollback();
							return false;
						}
					}





					query = " Insert into ERP_DONDATHANGNPP_SANPHAM_CHITIET(donDAThang_fk ,   sanpham_fk,solo,ngaynhapkho,ngayhethan, soluong,dvdl_fk) "
						+ " values('" + id + "', '" +spID + "','" +solo+ "','"+ngaynhapkho+"','"+ngayhethan+"',"+soluongcapnhat_quydoi+" ,"+dvCHUAN+" )";

					if (!db.update(query)) 
					{
						this.msg="Không thể cập nhật : "+query;
						db.getConnection().rollback();
						rs.close();
						return false;
					}

					soluongcapnhat =Double.parseDouble(formater_1sole.format(soluongcapnhat));

					msg1=uilt_kho.Update_NPP_Kho_Sp_Chitiet(ngayhoadon_, "8989 ErpDondathangDoiTac.java :566  DHID: "+this.id,  db, _khoid, spID, nppId, _kbhid, solo, ngayhethan, ngaynhapkho, 0,soluongcapnhat,(-1)* soluongcapnhat, (-1)* soluongcapnhat, 0);
					if (msg1.length()> 0) 
					{
						this.msg=msg1;
						db.getConnection().rollback();
						rs.close();
						return false;
					}
				}
				rssp.close();

				if(soluongdenghi!=0){


					this.msg=  "Số lượng đề xuất trong lô chi tiết của sản phẩm "+spten+"   tới ngày (ngày cấu hình hóa đơn)"+ngayhoadon_+" không còn đủ, " +
					" vui lòng kiểm tra báo cáo ( xuất nhập tồn,tồn hiện tại) theo lô để biết thêm chi tiết ";


					db.getConnection().rollback();
					rs.close();
					return false;

				}



			}
			rs.close();

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

	public boolean updateNK(HttpServletRequest request) 
	{


		Utility  uilt_kho =new Utility();

		String ngayhoadon_=uilt_kho.getngayhoadon(this.userId, db,this.ngayyeucau,this.khId,1);

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

		if( this.khId.trim().length() <= 0 )
		{
			this.msg = "Vui lòng chọn đối tác đặt hàng";
			return false;
		}

		if( this.khoNhanId.trim().length() <= 0 )
		{
			this.msg = "Vui lòng chọn kho đặt hàng";
			return false;
		}
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
					String str = " select pk_seq as sanpham_fk, " +spSoluong[i] +  " as soluong " +
					" from SANPHAM where ma = '" +spMa[i] + "'  ";
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
			String action = request.getParameter("action");
			
			
			String khonhan_fk = this.khoNhanId.trim().length() <= 0 ? "null" : this.khoNhanId.trim();
			String chietkhau = this.chietkhau.trim().length() <= 0 ? "0" : this.chietkhau.replaceAll(",", "").trim();
			String vat = this.vat.trim().length() <= 0 ? "0" : this.vat.replaceAll(",", "").trim();

			String query = "";




			query = "select dungchungkenh from NHAPHANPHOI where PK_SEQ =  (SELECT NPP_FK FROM ERP_DONDATHANGNPP WHERE PK_sEQ="+this.id+" )";
			ResultSet rs = db.get(query);
			boolean dungchungkenh=false;
			if(rs.next())
			{
				if(rs.getString("dungchungkenh").equals("1")){
					dungchungkenh=true;
				}
			}
			rs.close();




			// PHAI CẬP NHẬT KHO SAU TRONG TRƯỜNG HỢP ĐỔI KHO KHÁC
			query =	" Update ERP_DondathangNPP set ngaydonhang = '" + this.ngayyeucau + "', ngaydenghi = '" + this.ngaydenghi + "', ghichu = N'" + this.ghichu + "', " +
			" 	dvkd_fk = '" + this.dvkdId + "', kbh_fk = '" + this.kbhId + "', npp_fk = '" +
			this.nppId + "', kho_fk = " + khonhan_fk + ", ngaysua = '" + getDateTime() +
			"', nguoisua = '" + this.userId + "', chietkhau = '" + chietkhau + "', vat = '" +
			vat + "',isKM='"+this.iskm+"',isdhkhac="+this.isdhk+",isingia="+this.isgia+" " + 
			", ngaygiochot='" +getDateTime1() +"'  "+
			" where trangthai =0 and pk_seq = '" + this.id + "' ";

			if(db.updateReturnInt(query)!=1)
			{
				this.msg = "Không thể cập nhật ERP_DondathangNPP " + query;
				db.getConnection().rollback();
				return false;
			}
			
			if(action.equals("save"))
			{
				query ="\n select scheme from CTKHUYENMAI ctkm " +
				"\n inner join ERP_DondathangNPP_CTKM_TRAKM dhkm on dhkm.CTKMID = ctkm.PK_SEQ  " +
				"\n where dhkm.DONDATHANGID = " + this.id +
				"\n 	and not exists ( select 1 from ERP_DondathangNPP dh where dh.PK_SEQ = dhkm.DONDATHANGID and dh.ngaydonhang >= ctkm.TUNGAY and dh.ngaydonhang <= ctkm.DENNGAY) ";
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
				query = " select count(dh.sanpham_fk) as sodong  " +
				" from ERP_DondathangNPP_SANPHAM dh " +
				" left join  ( " + sqlCHeck + " ) dh_sp on dh.sanpham_fk = dh_sp.sanpham_fk " +
				" where Dondathang_fk = '" + this.id + "' and dh.soluong != dh_sp.soluong " +
				" and exists ( select 1 from  ERP_DONDATHANGNPP_CTKM_TRAKM x where x.DONDATHANGID = "+this.id+"  )  ";
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
			
			

			query = "delete ERP_DondathangNPP_SANPHAM where Dondathang_fk = '" + this.id + "'";
			if(!db.update(query))
			{
				this.msg = "Không thể cập nhật ERP_DondathangNPP_SANPHAM " + query;
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

			for(int i = 0; i < spMa.length; i++)
			{
				if(spMa[i].trim().length() > 0 && spSCheme[i].trim().length() <= 0 )
				{
					//CHECK SP NAY DA CO KHAI QUY CACH CHUA?
					query =    "select sp.ten, dv.donvi, case when sp.dvdl_fk != dv.pk_seq   " +
					"	then ISNULL( ( select soluong2 / soluong1 from QUYCACH where SANPHAM_FK = sp.PK_SEQ and DVDL1_FK = sp.DVDL_FK and DVDL2_FK = dv.pk_seq ), -1 ) else 1 end as quycach   "  +
					"from SANPHAM sp, DONVIDOLUONG dv " +
					"where sp.MA = '" + spMa[i].trim() + "' and dv.donvi = N'" + spDonvi[i].trim() + "'   ";

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


					String ck = "0";
					if(spChietkhau[i].trim().length() > 0)
						ck = spChietkhau[i].trim().replaceAll(",", "");

					String thueVAT = this.spVAT[i].trim().replaceAll(",", "");
					if(thueVAT.trim().length() < 0)
						thueVAT = "0";

					query = "insert ERP_DondathangNPP_SANPHAM(kho_fk,kbh_fk, Dondathang_fk, SANPHAM_FK, soluong, dongia, chietkhau, thueVAT, dvdl_fk, dongiagoc ) " +
					"select "+khonhan_fk+","+kbh_fk+",'" + this.id + "', pk_seq, '" + spSoluong[i].replaceAll(",", "") + "', '" + spGianhap[i].replaceAll(",", "") + "', '" + ck + "', '" + thueVAT + "', ( select pk_Seq from DONVIDOLUONG where donvi = N'" + spDonvi[i].trim() + "' ), "+ spGiagoc[i].replaceAll(",", "")  +" " +
					"from SANPHAM where MA = '" + spMa[i].trim() + "' ";

					System.out.println("1.Insert NK - SP: " + query);
					if(!db.update(query))
					{
						this.msg = "Khong the tao moi ERP_Dondathang_SANPHAM: " + query;
						db.getConnection().rollback();
						return false;
					}

				}
			}
			if(!action.equals("save"))
			{
				String sql = "DELETE FROM ERP_DONDATHANGNPP_CTKM_TRAKM WHERE DONDATHANGID = '"+ this.id +"'";
				if(!db.update(sql))
				{
					this.msg = "Áp khuyến mãi có lỗi.";
					db.getConnection().rollback();
					return false;
				}
				String msgA = Ap_Khuyen_Mai_SellIn_Dac_Biet( db , this.id,kbh_fk );
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
				
				String kqVat = geso.dms.center.util.Utility.CheckVat( db , "ERP_DondathangNPP_SANPHAM","thueVAT", "Dondathang_fk", this.id );
				if(kqVat.trim().length() > 0)
				{
					this.msg = kqVat;
					db.getConnection().rollback();
					db.getConnection().setAutoCommit(true);
					return false;
				}
			}
			

			/*query = "Update ERP_DondathangNPP set trangthai=0 where pk_seq= "+this.id;
			if(!db.update(query))
			{
				msg = "Khong the cap nhat ERP_KHOTT_SANPHAM: " + query;
				db.getConnection().rollback();
				rs.close();
				return false;
			}*/

			String msgC = update_giatri_erp_dondathangnpp( db ,this.id );
			if(msgC.length() > 0 )
			{
				this.msg = msgC;
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

		this.khoNhanRs = db.get("select PK_SEQ, TEN from KHO where trangthai = '1' and pk_seq in " + this.util.quyen_kho(this.userId)  );
		if(this.khoNhanId.trim().length() <= 0)
		{
			if(this.nppId.equals("106210"))
				this.khoNhanId = "100002";
			else
				this.khoNhanId = "100000";
		}

		this.dvtRs = db.getScrol("select PK_SEQ, DONVI from DONVIDOLUONG where trangthai = '1' ");

		this.dvkdRs = db.get("select PK_SEQ, DONVIKINHDOANH as TEN from DONVIKINHDOANH where TRANGTHAI = '1' ");
		this.kbhRs = db.get("select PK_SEQ, DIENGIAI as TEN from KENHBANHANG where TRANGTHAI = '1' and PK_SEQ in ( select KBH_FK from NHAPP_KBH where NPP_FK = '" + this.nppId + "' ) ");

		String query = "select PK_SEQ, ISNULL(MAFAST, '') + ' - ' + TEN as TEN from NHAPHANPHOI where TRANGTHAI = '1' and loaiNPP = '4' and tructhuoc_fk = '" + this.nppId + "' ";
		this.khRs = db.get(query);

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
				catch (Exception e) {e.printStackTrace();}
			}

			//INIT CONG NO
			query = "\n SELECT  HOADON.NPPID, HOADON.MANPP, HOADON.TENNPP, HOADON.PK_SEQ as SOHOADON, HOADON.KYHIEU, HOADON.SOHOADON, HOADON.NGAYHOADON,   " +
					"\n 			ISNULL(HOADON.TONGTIENAVAT, 0) AS SOTIENVND, ISNULL(DATHANHTOAN.DATHANHTOAN, '0') as DATHANHTOAN, " +
					"\n 			ISNULL(HOADON.TONGTIENAVAT, 0) - ISNULL(DATHANHTOAN.DATHANHTOAN, '0') as CONLAI " +
					"\n FROM  " +
					"\n (  	 " +
					"\n 	SELECT NPP.PK_SEQ as NPPID, NPP.MA as MANPP, NPP.TEN as TENNPP, HD.PK_SEQ, HD.KYHIEU, HD.SOHOADON, HD.NGAYXUATHD AS NGAYHOADON, HD.TONGTIENAVAT  		 " +
					"\n 	FROM ERP_HOADON HD 	inner join NHAPHANPHOI NPP on HD.NPP_FK= NPP.PK_SEQ  		 " +
					"\n 	WHERE  HD.TRANGTHAI = '2'  AND NPP.PK_SEQ = '" + this.nppId + "'  " +
					"\n )  " +
					"\n HOADON  LEFT JOIN  " +
					"\n (  	 " +
					"\n 	SELECT HOADON_FK, SUM(ISNULL(DATHANHTOAN, 0)) AS DATHANHTOAN    	 " +
					"\n 	FROM   	 " +
					"\n 	( 	 					 " +
					"\n 		SELECT TTHD.HOADON_FK , SUM(TTHD.SOTIENTT) AS DATHANHTOAN     		 " +
					"\n 		FROM ERP_THUTIEN_HOADON TTHD   " +
					"\n 			INNER JOIN ERP_THUTIEN TT ON TTHD.THUTIEN_FK = TT.PK_SEQ    		 " +
					"\n 		WHERE  TT.TRANGTHAI NOT IN (2)	  " +
					"\n  " +
					"\n  		GROUP BY HOADON_FK    	 " +
					"\n 	)  " +
					"\n 	HOADONDATT  GROUP BY HOADON_FK   " +
					"\n ) " +
					"\n DATHANHTOAN ON HOADON.PK_SEQ = DATHANHTOAN.HOADON_FK   " +
					"\n WHERE HOADON.TONGTIENAVAT - ISNULL(DATHANHTOAN.DATHANHTOAN, '0') > 0 ";

			System.out.println("\n CONG NO: " + query);
			this.congnoRs = db.get(query);

		}

	}

	private void initSANPHAM(HttpServletRequest request) 
	{
		String LINKSERVER_DB = request.getServletContext().getInitParameter("LINKSERVER_DB");
		String query = "";
		if(this.quanlykho.trim().equals("0"))
		{
			query = 
				"IF OBJECT_ID('tempdb.dbo.#kho') IS NOT NULL DROP TABLE #kho select * into #kho from "+
			 	" ( "+
				" 	 SELECT SP_E.MA SPMA, NPP_E.MA NPPMA, SUM(AVAILABLE) AVAILABLE "+
				" 	 FROM "+ LINKSERVER_DB +".[dbo].[ERP_KHOTT_SP_CHITIET] K   "+
				" 	 INNER JOIN "+ LINKSERVER_DB +".[dbo].NHAPHANPHOI NPP_E ON NPP_E.PK_SEQ = K.NPP_FK   "+
				" 	 INNER JOIN "+ LINKSERVER_DB +".[dbo].ERP_SANPHAM SP_E ON SP_E.PK_SEQ = K.SANPHAM_FK    "+
				" 	 WHERE K.NGAYNHAPKHO <= '"+ this.ngayyeucau +"' AND NPP_E.MA = ( SELECT DISTINCT MA FROM NHAPHANPHOI WHERE PK_SEQ = ( select NPP_FK from ERP_DONDATHANGNPP where  PK_SEQ = '"+ this.id +"' )) "+
				" 	 GROUP BY SP_E.MA, NPP_E.MA "+
				" ) AS K "+
				
				" select b.MA, K.AVAILABLE as SOLUONGTON, "+
				" (select kho.soluong from nhapp_kho kho where kho.sanpham_fk=b.pk_seq and kho.KHO_FK= 100000 and NPP_FK in (select NPP_DAT_FK from ERP_DONDATHANGNPP where  PK_SEQ=a.dondathang_fk) and kho.KBH_FK= "
				+ (this.dungchungKENH.equals("1") ? "100025" : this.kbhId) + " )as tonkhocn, "+
				" b.TEN, DV.donvi, a.soluong, a.dongia, a.chietkhau, a.thueVAT, isnull(a.dongiagoc,0) as dongiagoc   " +
				" from ERP_DondathangNPP_SANPHAM a "
			  + " inner Join SanPham b on a.SANPHAM_FK = b.PK_SEQ " +
			    " OUTER APPLY ( SELECT * FROM #KHO WHERE SPMA = b.MA ) AS K "+
				" INNER JOIN DONVIDOLUONG DV ON DV.PK_SEQ = a.DVDL_FK " +
				"where a.Dondathang_FK = '" + this.id + "' ";
		}
		else
		{
			query = 
				"select b.MA,(select kho.available from nhapp_kho kho where kho.sanpham_fk=b.pk_seq and kho.KHO_FK= " + this.getKhoNhapId() + " and NPP_FK in(select NPP_FK from ERP_DONDATHANGNPP where  PK_SEQ=a.dondathang_fk) and kho.KBH_FK="
				+ (this.dungchungKENH.equals("1") ? "100025" : this.kbhId) + " )as soluongton," +
				"(select kho.soluong from nhapp_kho kho where kho.sanpham_fk=b.pk_seq and kho.KHO_FK= 100000 and NPP_FK in(select NPP_DAT_FK from ERP_DONDATHANGNPP where  PK_SEQ=a.dondathang_fk) and kho.KBH_FK="
				+ (this.dungchungKENH.equals("1") ? "100025" : this.kbhId) + " )as tonkhocn, "+
				" b.TEN, DV.donvi, a.soluong, a.dongia, a.chietkhau, a.thueVAT, isnull(a.dongiagoc,0) as dongiagoc   " +
				" from ERP_DondathangNPP_SANPHAM a inner Join SanPham b on a.SANPHAM_FK = b.PK_SEQ    " +
				" 		INNER JOIN DONVIDOLUONG DV ON DV.PK_SEQ = a.DVDL_FK       " +
				"where a.Dondathang_FK = '" + this.id + "' ";
	}

		System.out.println("--INIT SP: " + query);
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
				String spGIAGOC = "";
				String spCHIETKHAU = "";
				String spVAT = "";
				String spSCHEME = "";
				String spSOLUONGTON = "";
				String spTonkho="";
				while(spRs.next())
				{
					spMA += spRs.getString("MA") + "__";
					spTEN += spRs.getString("TEN") + "__";
					spDONVI += spRs.getString("DONVI") + "__";
					spSOLUONG += formater.format(spRs.getDouble("SOLUONG")) + "__";
					spGIANHAP += spRs.getDouble("DONGIA") + "__";
					spGIAGOC += spRs.getDouble("DONGIAGOC") + "__";
					spCHIETKHAU += formater.format(spRs.getDouble("chietkhau")) + "__";
					spVAT += formater.format(spRs.getDouble("thueVAT")) + "__";
					spSOLUONGTON += formater.format(spRs.getDouble("soluongton")) + "__";
					spTonkho += formater.format(spRs.getDouble("tonkhocn")) + "__";
					spSCHEME += " __";
				}
				spRs.close();

				//INIT SP KHUYEN MAI
				/*query = "select isnull(b.MA, ' ') as MA, isnull(b.TEN, ' ') as TEN, isnull(c.DONVI, ' ') as donvi, d.SCHEME, isnull(a.soluong, 0) as soluong, a.tonggiatri " +
						"from ERP_DONDATHANG_CTKM_TRAKM a left join SANPHAM b on a.SPMA = b.MA  " +
						"	left join DONVIDOLUONG c on b.DVDL_FK = c.PK_SEQ  " +
						"	inner join CTKHUYENMAI d on a.ctkmID = d.PK_SEQ " +
						"where a.dondathangID = '" + this.id + "' ";
				System.out.println("--INIT SPKM: " + query);

				spRs = db.get(query);
				while(spRs.next())
				{
					spMA += spRs.getString("MA") + "__";
					spTEN += spRs.getString("TEN") + "__";
					spDONVI += spRs.getString("DONVI") + "__";
					spSOLUONG += formater.format(spRs.getDouble("SOLUONG")) + "__";
					spGIANHAP += formater.format(spRs.getDouble("tonggiatri")) + "__";
					spSCHEME += spRs.getString("SCHEME") + "__";
				}
				spRs.close();*/

				//System.out.println("---SCHEME: " + spSCHEME );
				//System.out.println("---DON GIA: " + spGIANHAP );

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

					spGIAGOC = spGIAGOC.substring(0, spGIAGOC.length() - 2);
					this.spGiagoc = spGIAGOC.split("__");

					spCHIETKHAU = spCHIETKHAU.substring(0, spCHIETKHAU.length() - 2);
					this.spChietkhau = spCHIETKHAU.split("__");

					System.out.println("---VAT LA::::::" + spVAT);
					spVAT = spVAT.substring(0, spVAT.length() - 2);
					this.spVAT = spVAT.split("__");

					spSCHEME = spSCHEME.substring(0, spSCHEME.length() - 2);
					this.spSCheme = spSCHEME.split("__");

					spSOLUONGTON = spSOLUONGTON.substring(0, spSOLUONGTON.length() - 2);
					this.spSoluongton = spSOLUONGTON.split("__");


					spTonkho = spTonkho.substring(0, spTonkho.length() - 2);
					this.sptonkhocn = spTonkho.split("__");


				}

			} 
			catch (Exception e) 
			{
				e.printStackTrace();
				System.out.println("115.Exception: " + e.getMessage());
			}
		}
	}

	public void init(HttpServletRequest request) 
	{

		String query = "select  isnull(isdhkhac,0) isdhkhac,isnull(isingia,0) isingia, ngaydonhang, ngaydenghi, ISNULL(ghichu, '') as ghichu, dvkd_fk, kbh_fk, npp_fk, kho_fk, chietkhau, vat, loaidonhang, npp_dat_fk, " +
		" (select quanlykho from nhaphanphoi where pk_seq = a.npp_fk)quanlykho,Isnull( ( select dungchungkenh from NHAPHANPHOI where pk_seq = a.npp_fk ), 0 ) as dungchungkenh,trangthai, DATEDIFF (day, ngaytao, '2015-01-29') as CL,isnull(a.iskm,0) as iskm " +
		"from ERP_DondathangNPP a where pk_seq = '" + this.id + "'";
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
					this.khId = rs.getString("npp_dat_fk");
					this.khoNhanId = rs.getString("kho_fk");
					this.loaidonhang = rs.getString("loaidonhang");
					this.chietkhau = rs.getString("chietkhau");
					this.vat = rs.getString("vat");
					this.dungchungKENH = rs.getString("dungchungkenh");
					this.trangthai = rs.getString("trangthai");
					this.ngay_Chenhlech = rs.getInt("CL");
					this.iskm = rs.getString("iskm")==null?"0":rs.getString("iskm");
					this.isdhk=rs.getString("isdhkhac");
					this.isgia=rs.getString("isingia");
					this.quanlykho = rs.getString("quanlykho");
				}
				rs.close();
			} 
			catch (Exception e) 
			{
				e.printStackTrace();
				System.out.println("---LOI INIT: " + e.getMessage());
			}
		}
		this.initSANPHAM(request);
		this.createRs();




		//LẤY CÔNG NỢ // ÁP DỤNG CHO ĐƠN CỦA ĐỐI TÁC

		//XET XEM ĐỐI TÁC NÀY CÓ KHAI BÁO HAY KHÔNG
		query = "  SELECT COUNT(*) dem FROM CONGNO_NPP  WHERE NPP_FK ='"+this.khId+"'";
		System.out.println(query);

		ResultSet ktrakhaibao = db.get(query);

		int i = 0;
		try{
			while(ktrakhaibao.next()){
				i = ktrakhaibao.getInt("dem");
			}
		}
		catch(Exception e){
			e.printStackTrace();
		}

		if(i>0){
			//1.XET THEO HẠN MỨC NỢ CỦA ĐỐI TÁC
			query =

				"\n SELECT HDD.NPP_DAT_FK, SUM(isnull(sotienconno,0) - isnull(cn_npp.HANMUCNO,0)) SOTIENVUOTMUC \n"+ 
				"\n FROM ( \n"+ 
				"\n		SELECT dh.NPP_DAT_FK,sum(isnull(dh.TONGTIENAVAT,0)) - isnull(DATHANHTOAN.DATHANHTOAN,0) as sotienconno \n"+ 
				"\n		FROM ( \n"+ 
	/*			"\n				  SELECT dh.NPP_DAT_FK,SUM(dh.tongtien - dh.tongtienck + dh.tongthueVat) TONGTIENAVAT \n"+ 
				"\n				  FROM (  SELECT dh.PK_SEQ,dh.NPP_DAT_FK, round(SUM(sp.dongia*sp.soluong),0) tongtien, round(SUM(sp.chietkhau),0) tongtienck, round(SUM((sp.soluong*sp.dongia - sp.chietkhau)*sp.thueVAT/100),0) tongthueVat \n"+ 
				"\n						  FROM ERP_DONDATHANGNPP dh LEFT JOIN ERP_DONDATHANGNPP_SANPHAM sp on dh.PK_SEQ = sp.dondathang_fk \n"+	      
				"\n						  WHERE NPP_DAT_FK IS NOT NULL AND dh.NPP_FK = '"+this.nppId+"' AND dh.NPP_DAT_FK ='"+this.khId+"' AND dh.PK_SEQ IN ("+this.id+") \n"+ 
				"\n						  GROUP BY  dh.PK_SEQ, dh.NPP_DAT_FK \n"+	  
				"\n					  ) dh \n"+ 
				"\n				  GROUP BY dh.NPP_DAT_FK \n"+

				"\n				  UNION ALL \n"+ */

				"\n				  SELECT NPP_DAT_FK, SUM(tongtienavat) TONGTIENAVAT \n"+
				"\n				  FROM ERP_HOADONNPP \n"+ 
				"\n				  WHERE NPP_DAT_FK IS NOT NULL AND LOAIXUATHD = 0 AND NPP_FK = '"+this.nppId+"' AND NPP_DAT_FK ="+this.khId+" AND TRANGTHAI IN (1,2,4) \n"+
				"\n				  GROUP BY NPP_DAT_FK \n"+

				"\n			  ) dh \n"+ 
				"\n			 LEFT JOIN \n"+ 
				"\n				( \n"+	
				"\n					SELECT HOADONDATT.NPP_DAT_FK,SUM(ISNULL(DATHANHTOAN, 0)) AS DATHANHTOAN \n"+ 
				"\n					FROM \n"+  
				"\n						( \n"+ 	
				"\n							SELECT	hd.NPP_DAT_FK, SUM(ISNULL(TTHD.SOTIENTT,0)) DATHANHTOAN \n"+ 
				"\n							FROM \n"+		
				"\n									( SELECT PK_SEQ , NPP_DAT_FK \n"+ 
				"\n									  FROM ERP_HOADONNPP \n"+ 
				"\n									  WHERE NPP_DAT_FK IS NOT NULL AND LOAIXUATHD = 0 AND NPP_FK = '"+this.nppId+"' AND NPP_DAT_FK ='"+this.khId+"' ) hd \n"+ 
				"\n									LEFT JOIN ERP_THUTIENNPP_HOADON TTHD ON  hd.PK_SEQ = TTHD.HOADONNPP_FK and hd.NPP_DAT_FK = TTHD.NPP_FK \n"+ 
				"\n									LEFT JOIN ERP_THUTIENNPP TT ON TTHD.THUTIENNPP_FK = TT.PK_SEQ \n"+ 
				"\n							WHERE	TT.NPP_FK= '"+this.nppId+"' AND  TT.TRANGTHAI = 1 \n"+ 
				"\n 							GROUP BY HD.PK_SEQ,hd.NPP_DAT_FK \n"+ 

				"\n						) HOADONDATT \n"+  
				"\n						GROUP BY HOADONDATT.NPP_DAT_FK \n"+ 						
				"\n				) DATHANHTOAN ON dh.NPP_DAT_FK= DATHANHTOAN.NPP_DAT_FK \n"+ 
				"\n			GROUP BY dh.NPP_DAT_FK, DATHANHTOAN.DATHANHTOAN \n"+
				"\n	) HDD LEFT JOIN NHAPHANPHOI npp ON HDD.NPP_DAT_FK = npp.PK_SEQ \n"+ 
				"\n		 LEFT JOIN CONGNO_NPP cn_npp ON npp.PK_SEQ = cn_npp.NPP_FK \n"+ 
				"\n WHERE HDD.NPP_DAT_FK in ("+this.khId+") and NPP.TRUCTHUOC_FK = '"+this.nppId+"' and npp.loaiNPP = '4' \n"+  
				"\n  GROUP BY HDD.NPP_DAT_FK  " +
				"\n  HAVING sum(isnull(sotienconno,0) - isnull(cn_npp.HANMUCNO,0)) >0 \n";

			System.out.println("Check cong nợ: " +query);

			ResultSet ktracongno = db.get(query);

			double tiencongno = 0;

			try{
				while(ktracongno.next()){
					tiencongno = ktracongno.getDouble("SOTIENVUOTMUC");
				}
			}
			catch(Exception e){
				e.printStackTrace();
			}

			this.congno = Double.toString(tiencongno);

		}
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

	private String getDateTime() 
	{
		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		Date date = new Date();
		return dateFormat.format(date);	
	}

	private String getDateTime1() 
	{
		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd  HH:mm:ss.SS");
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

	public boolean duyetDH() 
	{	
		Utility  uilt_kho = new Utility();
		String ngayhoadon_ = uilt_kho.getngayhoadon(this.userId, db,this.ngayyeucau,this.khId,0);

		try
		{

			db.getConnection().setAutoCommit(false);

			//NEU CO DOI NGAY THI GHI NHAN LAI
			String query = " Update ERP_DondathangNPP set ngaydonhang = '" + this.ngayyeucau +  "', ngaydenghi = '" + this.ngaydenghi + "', ghichu = N'" + this.ghichu + "' " +
			"where pk_seq = '" + this.id + "' and TrangThai!=4 ";

			if(db.updateReturnInt(query)!=1)
			{
				this.msg = "Lỗi khi duyệt: " + query;
				db.getConnection().rollback();
				return false;
			}
			
			query = "update ERP_DondathangNPP set trangthai = '4', NPP_DACHOT = '1', nguoisua = '" + userId + "', ngaygiochot='"+getDateTime1() +"' where trangthai=1 and pk_seq = '" + id + "'";
			if(db.updateReturnInt(query)!=1)
			{
				msg = "Khong the chot: " + query;
				db.getConnection().rollback();
				return false;
			}

			Utility util = new Utility();
			msg= util.Check_Huy_NghiepVu_KhoaSo("ERP_DondathangNPP", id, "ngaydonhang", db);
			if(msg.length()>0)
			{
				db.getConnection().rollback();
				return false;
			}

			query = "select dungchungkenh from NHAPHANPHOI where PK_SEQ =  (SELECT NPP_FK FROM ERP_DONDATHANGNPP WHERE PK_sEQ="+this.id+" )";
			ResultSet rs = db.get(query);
			boolean dungchungkenh=false;
			if(rs.next())
			{
				if(rs.getString("dungchungkenh").equals("1")){
					dungchungkenh=true;
				}
			}
			rs.close();
			
			
			// ko cập nhật lại lô
			if (1==2 && this.quanlykho.equals("1")) {
				// CAP NHAT LAI LÔ
				query=	"\n SELECT KHOXUAT_FK, NPP_FK,   "+(dungchungkenh?"100025":" kbh_fk")+ " as  KBH_FK, SANPHAM_FK, SUM(SOLUONG) AS SOLUONG   ,SOLO, NGAYHETHAN, NGAYNHAPKHO "+      
				"\n FROM "+
				"\n ( "+
				"\n SELECT C.KHO_FK AS KHOXUAT_FK, C.NPP_FK,   KBH_FK, A.SANPHAM_FK, A.SOLO,A.NGAYHETHAN,A.NGAYNHAPKHO, "+     
				"\n CASE WHEN A.DVDL_FK IS NULL THEN A.SOLUONG       "+
				"\n WHEN A.DVDL_FK = B.DVDL_FK THEN A.SOLUONG      "+
				"\n ELSE  A.SOLUONG * ( SELECT SOLUONG1 / SOLUONG2 FROM QUYCACH "+ 
				"\n WHERE SANPHAM_FK = A.SANPHAM_FK AND DVDL2_FK = A.DVDL_FK AND DVDL1_FK = B.DVDL_FK )  END AS SOLUONG "+   
				"\n FROM ERP_DONDATHANGNPP_SANPHAM_CHITIET A INNER JOIN SANPHAM B ON A.SANPHAM_FK = B.PK_SEQ   "+
				"\n INNER JOIN ERP_DONDATHANGNPP C ON A.DONDATHANG_FK = C.PK_SEQ     "+
				"\n WHERE A.DONDATHANG_FK IN (  "+this.id+" ) AND A.SOLUONG > 0 "+
				"\n ) "+
				"\n DATHANG "+ 
				"\n GROUP BY KHOXUAT_FK, NPP_FK, "+(dungchungkenh?"":" kbh_fk,")+ " SANPHAM_FK ,SOLO, NGAYHETHAN, NGAYNHAPKHO    ";
	
				ResultSet rskho=db.get(query);
				while(rskho.next())
				{
					String   _khoxuat_fk, _npp_fk, _kbh_fk, _sanpham_fk,_solo,_ngayhethan,_ngaynhapkho ;
					_khoxuat_fk=rskho.getString("khoxuat_fk");
					_npp_fk=rskho.getString("npp_fk");
					_kbh_fk=rskho.getString("kbh_fk");
					_sanpham_fk=rskho.getString("sanpham_fk"); 
					_solo= rskho.getString("SOLO");
					_ngayhethan= rskho.getString("ngayhethan");
					_ngaynhapkho= rskho.getString("ngaynhapkho");
	
					double soluongct_ =rskho.getDouble("SOLUONG");
	
					String msg1=uilt_kho.Update_NPP_Kho_Sp_Chitiet( this.ngayyeucau,"Cập nhật đơn hàng đối tác :erpdondathangDoitacSvl 372" 
							, db, _khoxuat_fk, _sanpham_fk, _npp_fk, _kbh_fk, _solo, _ngayhethan, _ngaynhapkho, 0, (-1)*soluongct_, soluongct_, soluongct_, 0);
					if(msg1.length() >0){
						this.msg=msg1;
						db.getConnection().rollback();
						return false;
					}
				}
				rskho.close();
	
				query = "delete ERP_DONDATHANGNPP_SANPHAM_CHITIET where dondathang_fk = '" + this.id + "' ";
				if(!db.update(query))
				{
					this.msg = "Lỗi khi duyệt: " + query;
					db.getConnection().rollback();
					return false;
				}
	
				query = "SELECT NPP_FK  ,Kho_FK,kbh_fk  FROM ERP_DONDATHANGNPP where pk_Seq="+this.id;
				ResultSet rs1=db.get(query);
				String _npp_fk="",_khoxuat_fk="",_kbh_fk="";
				if(rs1.next()){
					_npp_fk=rs1.getString("NPP_FK");
					_khoxuat_fk=rs1.getString("Kho_FK");
					_kbh_fk=rs1.getString("kbh_fk");
				}
				if(dungchungkenh){
					_kbh_fk="100025";
				}
	
				//LUU VAO BANG CHI TIET
				for(int i = 0; i < spMa.length; i++)
				{
					if(spMa[i].trim().length() > 0 && spSoluong[i].trim().length() > 0 )
					{
						if(this.sanpham_soluong != null)
						{
							Enumeration<String> keys = this.sanpham_soluong.keys();
							double totalCT = 0;
							System.out.println(" key size = "+sanpham_soluong.size() );
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
	
									query = "insert ERP_DONDATHANGNPP_SANPHAM_CHITIET( dondathang_fk, SANPHAM_FK, dvdl_fk, solo, soluong, ngayhethan,NGAYNHAPKHO )  " +
									"select '" + this.id + "', pk_seq, ( select dvdl_fk from ERP_DONDATHANGNPP_SANPHAM where dondathang_fk = '" + this.id + "' and sanpham_fk = a.pk_seq ),  N'" + _sp[1] + "' as solo, '" + _soluongCT.replaceAll(",", "") + "' as soluong, '"+_sp[2]+"' as NgayHetHan   " +
									" , '"+_sp[3]+"' as ngaynhapkho from SANPHAM a where MA = '" + spMa[i] + "'  ";
	
									System.out.println("1.2.Insert DDH - SP - CT: " + query);
									if(!db.update(query))
									{
										this.msg = "Khong the tao moi ERP_DONDATHANGNPP_SANPHAM_CHITIET: " + query;
										db.getConnection().rollback();
										return false;
									}
	
									// cập nhật lại kho chi tiet quy đổi lại số lượng chuẩn để cập nhật kho 
									double soluong_quydoi=Double.parseDouble(_soluongCT);
									query=  " SELECT PK_sEQ, ( select dvdl_fk from ERP_DONDATHANGNPP_SANPHAM where dondathang_fk = '" + this.id + "' " +
									" and sanpham_fk = SP.pk_seq ) AS DONVI_DONHANG,SP.DVDL_FK  FROM SANPHAM SP WHERE MA='"+spMa[i]+"'";
	
									ResultSet rssp=db.get(query);
									String spid_="";
									if(rssp.next()){
										spid_=rssp.getString("PK_sEQ");
										if(!rssp.getString("DONVI_DONHANG").equals(rssp.getString("DVDL_FK"))){
											// nếu khác 
											query=" SELECT qc.SOLUONG1,qc.SOLUONG2 FROM QUYCACH qc WHERE SANPHAM_FK="+spid_+" AND DVDL1_FK="+rssp.getString("DVDL_FK")+"  and qc.DVDL2_FK="+rssp.getString("DONVI_DONHANG");
											ResultSet rsqc=db.get(query);
											if(rsqc.next()){
												soluong_quydoi = Double.parseDouble(_soluongCT) * rsqc.getDouble("SOLUONG1")/ rsqc.getDouble("SOLUONG2");
	
											}else{
												this.msg="Không thể xác định quy đổi của sản phẩm : "+rs.getString("ten");;
												db.getConnection().rollback();
												return false;
											}
										}
	
									}else{
										this.msg="Không thể xác định quy đổi của sản phẩm : "+rs.getString("ten");;
										db.getConnection().rollback();
										return false;
									}
									rssp.close();
	
									String msg1 = uilt_kho.Update_NPP_Kho_Sp_Chitiet(ngayhoadon_, "Sửa số lô trên đuyệt đơn hàng " 
											, db, _khoxuat_fk, spid_, _npp_fk,_kbh_fk ,  _sp[1] , _sp[2], _sp[3], 0, soluong_quydoi, (-1)* soluong_quydoi, (-1)* soluong_quydoi, 0);
	
									if(msg1.length() >0){
										this.msg=msg1;
										db.getConnection().rollback();
										return false;
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
	
				//CHECK TONG KHO CHI TIET PHAI BANG TONG TRONG KHO TONG
				query = "select count(*) as soDONG   " +
				"from ERP_DONDATHANGNPP_SANPHAM tong left join   " +
				"	(  " +
				"		select sanpham_fk, sum(soluong) as soluong   " +
				"		from ERP_DONDATHANGNPP_SANPHAM_CHITIET  " +
				"		where  dondathang_fk = '" + this.id + "'  " +
				"		group by sanpham_fk " +
				"	)  " +
				"	CT on tong.sanpham_fk = CT.sanpham_fk " +
				"where dondathang_fk = '" + this.id + "' and tong.soluong != isnull(CT.soluong, 0)  " ;
				ResultSet rsCHECK = db.get(query);
				int soDONG = 0;
				if(rsCHECK != null )
				{
					if( rsCHECK.next() )
					{
						soDONG = rsCHECK.getInt("soDONG");
					}
					rsCHECK.close();
				}
	
				if(soDONG > 0)
				{
					db.getConnection().rollback();
					this.msg = "11.Lỗi hệ thống ( tổng xuất theo lô đề xuất đang bị sai ). Vui lòng liên hệ trung tâm để được hỗ trợ xử lý.";
					return false;
				}
			}

			query = "select khachhang_fk, a.kbh_fk, a.npp_fk, a.npp_dat_fk, " +
			"( select priandsecond from NHAPHANPHOI where pk_seq = a.npp_fk ) as tuxuatOTC,  " +
			"( select tuxuatETC from NHAPHANPHOI where pk_seq = a.npp_fk ) as tuxuatETC,  " +
			"( select loaiNPP from NHAPHANPHOI where pk_seq = a.npp_fk ) as loaiNPP, " +
			"( select tructhuoc_fk from NHAPHANPHOI where pk_seq = a.npp_fk ) as tructhuoc_fk,  " +
			" ISNULL( ( select dungchungkenh from NHAPHANPHOI where pk_seq = a.npp_fk ), 0 ) as dungchungkenh, a.kho_fk, a.ngaydonhang  " +
			"from ERP_DondathangNPP a where a.pk_seq = '" + id + "' order by pk_seq desc";
			String khachhangID = "";
			String loaiNPP = "";
			String tructhuoc = "";
			String nppId = "";
			String npp_dat_fk = "";
			String kbh_fk = "";
			String khonhanID = "";
			String ngaydonhang = "";

			String tuquanlyKHO_OTC = "0";
			String tuquanlyKHO_ETC = "0";

			rs = db.get(query);
			if(rs != null)
			{
				if(rs.next())
				{
					ngaydonhang = rs.getString("ngaydonhang");
					if(rs.getString("khachhang_fk") != null)
						khachhangID = rs.getString("khachhang_fk");

					loaiNPP = rs.getString("loaiNPP");
					tructhuoc = rs.getString("tructhuoc_fk");
					nppId = rs.getString("npp_fk");

					if(rs.getString("npp_dat_fk") != null)
						npp_dat_fk = rs.getString("npp_dat_fk");

					if(rs.getString("dungchungkenh").equals("1"))
						kbh_fk = "100025";
					else
						kbh_fk = rs.getString("kbh_fk");

					if(rs.getString("tuxuatOTC") != null)
						tuquanlyKHO_OTC = rs.getString("tuxuatOTC");

					if(rs.getString("tuxuatETC") != null)
						tuquanlyKHO_ETC = rs.getString("tuxuatETC");

					khonhanID = rs.getString("kho_fk");
				}
				rs.close();
			}
			

			// PHAI HUY DON DUOI Đối tác trực thuộc đặt lên (trường hợp không phải tự tao mới)
			query = "update ERP_Dondathang set trangthai = '4' where pk_seq = ( select from_dondathang from ERP_DondathangNPP where pk_seq = '" + id + "' ) ";
			if(!db.update(query))
			{
				msg = "1.Khong the chot: " + query;
				db.getConnection().rollback();
				return false;
			}

			// Tu dong tao Hoa don tai chinh cho NPP					
			msg = this.TaoHoaDonTaiChinhNPP(db, id, userId, nppId, npp_dat_fk, ngaydonhang);
			if(msg.trim().length() > 0)
			{
				msg = "Khong the tao hoa don tai chinh: " + msg;
				db.getConnection().rollback();
				return false;
			}
			
			if (this.quanlykho.equals("1")) {
				msg= util.Check_Kho_Tong_VS_KhoCT(nppId, db);
				if(msg.length()>0)
				{
					db.getConnection().rollback();
					return false;
				}
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

	private String TaoHoaDonTaiChinhNPP(dbutils db, String id, String userId, String nppId, String npp_dat_fk, String ngaydonhang) 
	{
		String msg1 = "";
		try
		{
			String query = "";

			query =" update NHANVIEN SET Active = '1' where pk_seq='"+userId+"'";
			if(!db.update(query))
			{
				msg1 = "Không thể cập nhật thông tin NHANVIEN " + query;
				return msg1;
			}

			//Lấy thông tin hóa đơn
			String kyhieuhoadon = "";
			String sohoadon = "";
			String ngayhoadon = "";
			String[] info = util.LayThongTinSHD(db, userId, nppId, ngaydonhang, "");
			if( info.length < 3 )
				return info[0];

			kyhieuhoadon = info[0];
			sohoadon = info[1];
			ngayhoadon = info[2];

			if(sohoadon.trim().length() <=0)
			{
				msg1 = "Khoong laay duoc so hoa don!";
				return msg1;
			}
			
			// LAY TIEN DE LUU
			double tienck= 0;
			double tienbvat= 0;
			double tienavat= 0;
			String nguoimua ="";

			query = "\n select (case when dh.khachhang_fk is not null then " +
					"\n                               (select isnull(chucuahieu,'') from KHACHHANG where pk_seq = dh.khachhang_fk ) " +
					"\n             else '' end ) as nguoimua  ," +
					"\n        dh_sp.chietkhau, dh_sp.bvat , (dh_sp.bvat + dh.Vat) as AVAT "+
					"\n from ERP_DONDATHANGNPP dh inner join  "+
					"\n	(select a.dondathang_fk, SUM(a.chietkhau)as chietkhau , sum(a.soluong * a.dongia - a.chietkhau) as bvat "+
					"\n	from ERP_DONDATHANGNPP_SANPHAM a   "+
					"\n	group by  a.dondathang_fk ) dh_sp on dh.PK_SEQ = dh_sp.dondathang_fk "+
					"\n where dh.PK_SEQ = "+ id +"  ";

			ResultSet rsLayTien = db.get(query);
			if(rsLayTien!= null)
			{
				while(rsLayTien.next())
				{
					tienck = rsLayTien.getDouble("chietkhau");
					tienbvat = rsLayTien.getDouble("bvat");
					tienavat = rsLayTien.getDouble("avat");
					nguoimua =  rsLayTien.getString("nguoimua");

				}rsLayTien.close();
			}

			// CN TẠI HÀ NỘI DÙNG MẪU 2 (HO)
			String mau = "1";
/*			if(nppId.equals("100002")) mau = "2";	*/		

			query = " insert ERP_HOADONNPP(DDKD_fK,KBH_FK,KHO_fK,nguoimua ,ngayxuatHD, trangthai, ngaytao, nguoitao, ngaysua, nguoisua, kyhieu, sohoadon, hinhthuctt ," +
			"        chietkhau, tongtienbvat, tongtienavat, vat, ghichu, loaixuathd, npp_fk,  npp_dat_fk, mauhoadon,TENKHACHHANG,DIACHI,MASOTHUE,LoaiHoaDon,loaihd ) " +
			" SELECT  DH.ddkd_Fk, DH.KBH_FK, DH.kho_Fk , N'" + nguoimua + "', '" + ngayhoadon + "', '1','" + getDateTime() + "', '" + userId + "', '" + getDateTime() + "', '" + userId + "', '" + kyhieuhoadon + "'," +
			"       '" + sohoadon + "', N'TM/CK' , '"+ tienck  +"', '"+ tienbvat +"', '"+ tienavat  +"'," +
			" '" + this.vat.replaceAll(",", "") + "', (select isnull(ghichu,'') from erp_dondathangnpp where pk_seq =" + id + "), '0', '" + nppId + "', " + npp_dat_fk + ", " + mau + "" +
			" , NPP.ten as nppMua " +
			" , ISNULL(NPP.DIACHI,'')  as diachinpp " +
			" , ISNULL(NPP.MASOTHUE,'')  as mst,case when dh.iskm=1 then 1 else 0 end as LoaiHoaDon "+
			", isnull(DH.isdhkhac,0)"+
			" FROM Erp_DonDatHangNPP DH INNER JOIN NHAPHANPHOI NPP ON DH.NPP_DAT_FK = NPP.PK_SEQ "+
			" WHERE DH.PK_SEQ = '"+id+"' " ;

			System.out.println("1.Insert ERP_HOADONNPP: " + query);
			if(db.updateReturnInt(query) <= 0 )
			{
				msg1 = "Không thể tạo mới ERP_HOADONNPP " + query;
				return msg1;
			}		

			String hdId = "";
			query = "select scope_identity() as hdId";
			ResultSet rs1 = db.get(query);
			rs1.next();
			hdId = rs1.getString("hdId");
			rs1.close();

			query = "insert ERP_HOADONNPP_SP( kho_fk,kbh_fk,hoadon_fk, sanpham_fk, sanphamTEN, donvitinh, soluong, dongia, thanhtien, chietkhau, scheme , vat,soluong_chuan,dongia_chuan) " +
			" select a.kho_fk,a.kbh_fk,"+ hdId +", "+
			"	b.PK_SEQ, a.sanphamTEN, DV.donvi, SUM( a.soluong), a.dongia, SUM( a.soluong) * a.dongia ,SUM( isnull(a.chietkhau, 0)), "+
			"  	isnull(scheme,' ') , isnull(a.thuevat,0) as vat ,	a.soluong * dbo.LayQuyCach( a.SANPHAM_FK, null, a.DVDL_FK ) as SoLuong_Chuan,  \n" + 
			"  	a.dongia * dbo.LayQuyCach_DVBan( a.SANPHAM_FK, null, a.DVDL_FK ) as DonGia_Chuan  \n" +
			"from ERP_DONDATHANGNPP_SANPHAM a inner Join SanPham b on a.SANPHAM_FK = b.PK_SEQ   "+  	 
			" INNER JOIN DONVIDOLUONG DV ON DV.PK_SEQ = a.DVDL_FK  " +
			" inner join  ERP_DONDATHANGNPP c on a.dondathang_fk=c.pk_seq    "+
			"where a.dondathang_fk in ( "+ id +" ) and a.dondathang_fk in (select pk_seq from ERP_DONDATHANGNPP where NPP_FK="+ nppId +") and a.soluong > 0  " +
			"group by a.kho_fk,a.kbh_fk, b.PK_SEQ , a.sanphamTEN, DV.donvi, a.dongia , isnull(scheme,' ') , isnull(a.thuevat,0),a.soluong ,a.dvdl_fk,a.sanpham_fk  ";

			System.out.println("1.1.Insert ERP_HOADONNPP_SP: " + query);
			if(db.updateReturnInt(query) <= 0 )
			{
				msg1 = "Khong the tao moi ERP_HOADONNPP_SP: " + query;
				return msg1;
			}

			query = 
				"\n insert ERP_HOADONNPP_CTKM_TRAKM(kbh_fk,hoadon_fk,sanpham_fk,sanphamma,soluong,ctkm,donvi,vat,kho_fk,ghichu,thuevat,chietkhaungay) " +
				"\n select a.kbh_fk, "+ hdId +", sanpham_fk,sp.MA,a.soluong,ct.SCHEME ,dv.DONVI, sp.PT_VAT,a.kho_fk,'',sp.PT_VAT,0 " +
				"\n from ERP_DONDATHANGNPP_CTKM_TRAKM a " +
				"\n inner join SanPham sp on sp.PK_SEQ = a.sanpham_fk " +
				"\n inner join DONVIDOLUONG dv on dv.PK_SEQ =sp.DVDL_FK " +
				"\n inner join CTKHUYENMAI ct on ct.PK_SEQ = a.CTKMID " +
				"\n where a.DONDATHANGID in ( "+ id +" )  ";
			if(db.updateReturnInt(query) < 0 )
			{
				msg1 = "Khong the tao moi ERP_HOADONNPP_CTKM_TRAKM: " + query;
				return msg1;
			}

			query = " insert ERP_HOADONNPP_CHIETKHAU(HOADON_FK,DIENGIAI,GIATRI,LOAI) " +
					" select "+ hdId +", ct.scheme, a.tonggiatri, 0 " +
					" from ERP_DONDATHANGNPP_CTKM_TRAKM a" +
					" inner join  CTKHUYENMAI ct on ct.PK_SEQ = a.CTKMID " +
					" where a.sanpham_fk is null and  a.DONDATHANGID in ( "+ id +" ) ";
			if(db.updateReturnInt(query) < 0 )
			{
				msg1 = "Khong the tao moi ERP_HOADONNPP_CTKM_TRAKM: " + query;
				return msg1;
			}
			
			query = "Insert ERP_HOADONNPP_DDH(hoadonnpp_fk, ddh_fk) " +
			" values( "+ hdId +",  " + id + "  )";
			if(db.updateReturnInt(query) <= 0 )
			{
				msg1 = "Không thể tạo mới ERP_HOADONNPP_DDH " + query;
				return msg1;
			}

			if (this.quanlykho.equals("1")) {
				query = "  insert ERP_HOADONNPP_SP_CHITIET(sanpham_fk,hoadon_fk, donhang_fk, KBH_FK, Kho_FK, MA, TEN, DONVI, DVCHUAN, DVDATHANG, SOLUONG, SoLuong_Chuan, SoLuong_DatHang, SOLO, NGAYHETHAN, NGAYNHAPKHO, CHIETKHAU, THUEVAT, DONGIA, DonGia_Chuan) " + 
				"  select a.sanpham_fk,'" + hdId + "', a.DonDatHang_FK, a.KBH_FK, a.Kho_FK, b.MA, b.TEN, d.DONVI, b.DVDL_FK as dvChuan, a.Dvdl_Fk as dvDathang,  " + 
				"  			a.SoLuong, a.SoLuong * dbo.LayQuyCach(a.sanpham_fk, null, e.dvdl_fk) as soluongChuan, a.SoLuong as SoLuong_DatHang,  " + 
				"  			a.SoLo, a.NgayHetHan, a.ngaynhapkho, e.chietkhau as chietkhau, e.thueVAT as THUEVAT, e.dongia as DONGIA, e.dongia * dbo.LayQuyCach_DVBan( a.SanPham_fk, null, e.Dvdl_Fk ) as DonGia_Chuan " + 
				"  from ERP_DONDATHANGNPP_SANPHAM_CHITIET a inner join SANPHAM b on a.SanPham_fk = b.PK_SEQ " + 
				"  		inner join ERP_DONDATHANGNPP c on a.DonDatHang_FK = c.PK_SEQ " + 
				"  		left join DONVIDOLUONG d on a.Dvdl_Fk = d.PK_SEQ " + 
				"  		inner join ERP_DONDATHANGNPP_SANPHAM e on a.dondathang_fk = e.dondathang_fk and a.sanpham_fk = e.sanpham_fk " + 
				"  where a.DonDatHang_FK = '" + this.id + "' and a.Scheme = '' ";
	
				if(db.updateReturnInt(query) <= 0 )
				{
					msg1 = "Không thể tạo mới ERP_HOADONNPP_SP_CHITIET " + query;
					return msg1;
				}
				
				
				query = 
					"\n insert [ERP_HOADONNPP_CTKM_TRAKM_CHITIET](hoadon_fk,sanpham_fk,sanphamma,soluong,ctkm,donvi,vat,kho_fk,ghichu,thuevat,chietkhaungay,KBH_FK,ngaynhapkho,ngayhethan,solo) " +
					"\n select '" + hdId + "',a.sanpham_fk,sp.MA,a.soluong,ct.SCHEME ,dv.DONVI, sp.PT_VAT,a.kho_fk,'',sp.PT_VAT,0,a.KBH_FK,a.NGAYNHAPKHO,a.NGAYHETHAN,a.SOLO " +
					"\n from ERP_DONDATHANGNPP_CTKM_TRAKM_CHITIET a " +
					"\n inner join ERP_DONDATHANGNPP dh on a.DONDATHANGID = dh.PK_SEQ " +
					"\n inner join SanPham sp on sp.PK_SEQ = a.sanpham_fk " +
					"\n inner join DONVIDOLUONG dv on dv.PK_SEQ =sp.DVDL_FK " +
					"\n inner join CTKHUYENMAI ct on ct.PK_SEQ = a.CTKMID " +
					"\n where a.DONDATHANGID in ( "+ id +" )  ";
				if(db.updateReturnInt(query) < 0 )
				{
					msg1 = "Khong the tao moi ERP_HOADONNPP_CTKM_TRAKM: " + query;
					return msg1;
				}
			}

			//LUU VAO BANG CHI TIET

			//CAP NHAT LAI CAC COT TIEN CUA ETC, SAU NAY IN RA THI CHI IN TU DAY
			String msgA = ErpHoadontaichinhNPP.update_gia_tri_hoadonnpp(db, hdId);
			if(msgA.length() > 0 )
			{
				return msgA;
			}

			//CHECK BANG TONG PHAI BANG BANG CHI TIET
			if (this.quanlykho.equals("1")) {
				query = "select count(*) as sodong  " +
				"from " +
				"( " +
				"	select b.pk_seq as sanpham_fk, sum(soluong) as soluong  " +
				"	from ERP_HOADONNPP_SP a inner join SANPHAM b on a.sanpham_fk = b.pk_seq " +
				"	where a.hoadon_fk = '" + hdId + "' " +
				"	group by b.pk_seq " +
				") " +
				"dh left join " +
				"( " +
				"	select b.pk_seq as sanpham_fk, sum(soluong) as soluong  " +
				"	from ERP_HOADONNPP_SP_CHITIET a inner join SANPHAM b on a.MA = b.MA " +
				"	where a.hoadon_fk = '" + hdId + "' " +
				"	group by b.pk_seq " +
				") " +
				"xk on dh.sanpham_fk = xk.sanpham_fk " +
				"where dh.soluong != isnull(xk.soluong, 0) ";
				System.out.println("---CHECK HOA DON: " + query);
				int soDONG = 0;
				ResultSet rsCHECK = db.get(query);
				if(rsCHECK != null)
				{
					if(rsCHECK.next())
					{
						soDONG = rsCHECK.getInt("sodong");
					}
					rsCHECK.close();
				}
	
				if(soDONG > 0)
				{
					msg = "3.Số lượng trong đơn hàng không khớp với hóa đơn. Vui lòng liên hệ Admin để xử lý ";
					return msg;
				}
			} 
		}
		catch (Exception e) 
		{
			db.update("rollback");
			msg1 = "Exception: " + e.getMessage();
			e.printStackTrace();
			return msg1;
		}

		return msg1;
	}

	private String TaoPhieuXuatKho_CapTren_NPP(dbutils db, String id, String userId, String khoId, String nppId, String tructhuoc, String kbh_fk) 
	{
		String query = "";
		String msg = "";

		try
		{
			//Tu dong tao YCXK  --> VA CHOT YCXK NAY LUON
			query = " insert ERP_YCXUATKHONPP(NgayYeuCau, ghichu, trangthai, npp_fk, kho_fk, xuatcho, npp_dat_fk, khachhang_fk, kbh_fk, ngaytao, nguoitao, ngaysua, nguoisua) " +
			" select '" + this.getDateTime()
			+ "', N'Phiếu xuất kho xuất dùm cho khách hàng ETC của CN cấp 2 / đối tác (đơn hàng số: " + id + ")', '2', '" + tructhuoc + "', '100000', " +
			" '0' as xuatcho, '" + nppId + "' as npp_dat_fk, khachhang_fk, kbh_fk, '" + getDateTime() + "', '" + userId + "', '" + getDateTime() + "', '" + userId + "' " +
			" from ERP_DONDATHANGNPP where pk_seq = '" + id + "' ";

			System.out.println("1.Insert YCXUATKHO: " + query);
			if(db.updateReturnInt(query) <= 0 )
			{
				msg = "Không thể tạo mới ERP_YCXUATKHONPP " + query;
				//db.getConnection().rollback();
				return msg;
			}

			String ycxkId = "";
			ResultSet rsYCXK = db.get("select IDENT_CURRENT('ERP_YCXUATKHONPP') as ycxkId");
			if(rsYCXK.next())
			{
				ycxkId = rsYCXK.getString("ycxkId");
			}
			rsYCXK.close();

			query = "Insert ERP_YCXUATKHONPP_DDH(ycxk_fk, ddh_fk) " +
			"select IDENT_CURRENT('ERP_YCXUATKHONPP'), pk_seq from ERP_DONDATHANGNPP where pk_seq in ( " + id + " )  ";
			if(!db.update(query))
			{
				msg = "Không thể tạo mới ERP_YCXUATKHONPP_DDH " + query;
				//db.getConnection().rollback();
				return msg;
			}

			query = "insert ERP_YCXUATKHONPP_SANPHAM( ycxk_fk, sanpham_fk, soluongDAT, tonkho, daxuat, soluongXUAT, LOAI, SCHEME ) " +
			"select IDENT_CURRENT('ERP_YCXUATKHONPP'), sp.PK_SEQ, SUM(dathang.soluong) as soluongDAT, ISNULL( ( select AVAILABLE from ERP_KHOTT_SANPHAM where khott_fk = '" + id + "' and sanpham_fk = sp.PK_SEQ ), 0)  as tonkho, 0, SUM(dathang.soluong) as soluongXUAT, loai, scheme  " +
			"from    " +
			"(    " +
			"		select a.sanpham_fk, b.DVDL_FK as dvCHUAN,    " +
			"				case when a.dvdl_fk IS null then a.soluong     " +
			"					 when a.dvdl_fk = b.DVDL_FK then a.soluong    " +
			"					 else  a.soluong * ( select SOLUONG1 from QUYCACH where sanpham_fk = a.sanpham_fk and DVDL2_FK = a.dvdl_fk )     " +
			"									 / ( select SOLUONG2 from QUYCACH where sanpham_fk = a.sanpham_fk and DVDL2_FK = a.dvdl_fk )	 end as soluong, 0 as loai, ' ' as scheme   " +
			"		from ERP_DONDATHANGNPP_SANPHAM a inner join SANPHAM b on a.sanpham_fk = b.PK_SEQ    " +
			"		where a.dondathang_fk in ( '" + id + "' )   " +
			")    " +
			"dathang inner join SANPHAM sp on dathang.sanpham_fk = sp.PK_SEQ     " +
			"group by sp.PK_SEQ, scheme, loai  " ;

			System.out.println("1.1.Insert YCXK - SP: " + query);
			if(db.updateReturnInt(query) <= 0 )
			{
				msg = "Khong the tao moi ERP_YCXUATKHO_SANPHAM: " + query;
				//db.getConnection().rollback();
				return msg;
			}

			//CHECK TON KHO
			query =  "select khoxuat_fk as kho_fk, npp_fk, kbh_fk, sp.PK_SEQ, sp.TEN, SUM(dathang.soluong) as soluongXUAT,  " +
			"	ISNULL( ( select AVAILABLE from NHAPP_KHO where kho_fk = dathang.khoxuat_fk and sanpham_fk = sp.PK_SEQ and kbh_fk = dathang.kbh_fk and npp_fk = '" + tructhuoc + "' ), 0) as tonkho  " +
			"from     " +
			"(     " +
			"	select c.kho_fk as khoxuat_fk, '" + tructhuoc + "' as npp_fk, c.kbh_fk, a.sanpham_fk, b.DVDL_FK as dvCHUAN,     " +
			"			case when a.dvdl_fk IS null then a.soluong      " +
			"				 when a.dvdl_fk = b.DVDL_FK then a.soluong     " +
			"				 else  a.soluong * ( select SOLUONG1 from QUYCACH where sanpham_fk = a.sanpham_fk and DVDL2_FK = a.dvdl_fk and dvdl1_fk = b.dvdl_fk )      " +
			"								 / ( select SOLUONG2 from QUYCACH where sanpham_fk = a.sanpham_fk and DVDL2_FK = a.dvdl_fk and dvdl1_fk = b.dvdl_fk )	 end as soluong   " +
			"	from ERP_DONDATHANGNPP_SANPHAM a inner join SANPHAM b on a.sanpham_fk = b.PK_SEQ  " +
			"			inner join ERP_DONDATHANGNPP c on a.dondathang_fk = c.pk_seq    " +
			"	where a.dondathang_fk in ( " + id + " )     " +
			")     " +
			"dathang inner join SANPHAM sp on dathang.sanpham_fk = sp.PK_SEQ   " +
			"group by khoxuat_fk, npp_fk, kbh_fk, sp.PK_SEQ, sp.TEN  ";

			System.out.println("--CHECK TON KHO: " + query);

			ResultSet rs = db.get(query);
			if(rs != null)
			{
				while(rs.next())
				{
					String khoID = rs.getString("kho_fk");
					String kbhID = rs.getString("kbh_fk");
					String nppID = rs.getString("npp_fk");
					String spID = rs.getString("PK_SEQ");

					double soluong = rs.getDouble("soluongXUAT");
					double tonkho = rs.getDouble("tonkho");

					if(soluong > tonkho)
					{
						msg = "Sản phẩm ( " + rs.getString("TEN") + " ) với số lượng yêu cầu ( " + rs.getString("soluongXUAT") + " ) không đủ tồn kho ( " + rs.getString("tonkho") + " ). Vui lòng liên hệ với chi nhánh cấp trên để xử lý.";
						//db.getConnection().rollback();
						rs.close();
						return msg;
					}

					//CAP NHAT KHO XUAT TONG
					query = "Update NHAPP_KHO set soluong = soluong - '" + soluong + "', AVAILABLE = AVAILABLE - '" + soluong + "' " +
					"where KHO_FK = '"+khoID+"' and KBH_FK = '" + kbhID + "' and NPP_FK = '" + nppID + "' and SANPHAM_FK = '" + spID + "' ";
					if(db.updateReturnInt(query)!=1)
					{
						msg = "Khong the cap nhat ERP_KHOTT_SANPHAM: " + query;
						//db.getConnection().rollback();
						rs.close();
						return msg;
					}


					//CAP NHAT KHO CHI TIET
					query = " select AVAILABLE, SOLO, ngayhethan,NGAYNHAPKHO from NHAPP_KHO_CHITIET " +
					" where AVAILABLE > 0 and KHO_FK = '" + khoID + "'  and SANPHAM_FK = '" + spID + "'  " +
					" and NPP_FK = '" + nppID + "' and KBH_FK = '" + kbhID + "' order by ngayhethan asc,ngaynhapkho asc ";

					ResultSet rsTK = db.get(query);
					double avai = 0;
					double totalXUAT = 0;
					while(rsTK.next())
					{
						double soluongCT = 0;
						String solo = rsTK.getString("SOLO");
						String ngayhethan = rsTK.getString("ngayhethan");
						String ngaynhapkho= rsTK.getString("ngaynhapkho");
						avai = rsTK.getDouble("AVAILABLE");
						totalXUAT += avai;

						if(totalXUAT <= soluong)
						{
							soluongCT = avai;

							query = "insert ERP_YCXUATKHONPP_SANPHAM_CHITIET( ycxk_fk, SANPHAM_FK, solo, soluong, ngayhethan ,ngaynhapkho) " +
							"select '" + ycxkId + "', '" + spID + "', N'" + solo + "', '" + soluongCT  + "', '" + ngayhethan + "','"+ngaynhapkho+"' ";

							System.out.println("1.2.Insert YCXK - SP - CT: " + query);
							if(!db.update(query))
							{
								msg = "Khong the tao moi ERP_YCXUATKHONPP_SANPHAM_CHITIET: " + query;
								//db.getConnection().rollback();
								rs.close();
								return msg;
							}

							query = "Update NHAPP_KHO_CHITIET set soluong = soluong - '" + soluongCT + "', AVAILABLE = AVAILABLE - '" + soluongCT + "' " +
							"where KHO_FK = '" + khoID + "' and SOLO = '" + solo + "' and SANPHAM_FK = '" + spID + "' AND KBH_FK = '" + kbhID + "' and NPP_FK = '" + nppID + "' AND NgayHetHan='"+ngayhethan+"' and ngaynhapkho='"+ngaynhapkho+"' ";
							if(db.updateReturnInt(query)!=1)
							{
								msg = "Khong the cap nhat NHAPP_KHO_CHITIET: " + query;
								//db.getConnection().rollback();
								rs.close();
								return msg;
							}

						}
						else
						{
							soluongCT = soluong - ( totalXUAT - avai );

							query = "insert ERP_YCXUATKHONPP_SANPHAM_CHITIET( ycxk_fk, SANPHAM_FK, solo, soluong, ngayhethan,ngaynhapkho ) " +
							"select '" + ycxkId + "', '" + spID + "', N'" + solo + "', '" + soluongCT + "', '" + ngayhethan + "','"+ngaynhapkho+"' ";

							System.out.println("1.2.Insert YCXK - SP - CT: " + query);
							if(!db.update(query))
							{
								msg = "Khong the tao moi ERP_YCXUATKHONPP_SANPHAM_CHITIET: " + query;
								//db.getConnection().rollback();
								rs.close();
								return msg;
							}

							query = "Update NHAPP_KHO_CHITIET set soluong = soluong - '" + soluongCT + "', AVAILABLE = AVAILABLE - '" + soluongCT + "' " +
							"where KHO_FK = '" + khoID + "' and SOLO = '" + solo + "' and SANPHAM_FK = '" + spID + "' and KBH_FK = '" + kbhID + "' and NPP_FK = '" + nppID + "' and NgayHetHan='"+ngayhethan+"' and ngaynhapkho='"+ngaynhapkho+"' ";
							if(db.updateReturnInt(query)!=1)
							{
								msg = "Khong the cap nhat NHAPP_KHO_CHITIET: " + query;
								//db.getConnection().rollback();
								rs.close();
								return msg;
							}

							break;
						}
					}
					rsTK.close();
				}
				rs.close();
			}
		} 
		catch (Exception e) {

			e.printStackTrace();
			return "Không thể duyệt đơn hàng " + e.getMessage();
		}

		return "";
	}

	private String TaoPhieuXuatKho_CapTren_HO(dbutils db, String id, String userId, String khoNhanId, String nppId, String tructhuoc, String kbh_fk) 
	{
		String msg = "";
		String query = "";

		try 
		{
			db.getConnection().setAutoCommit(false);

			//CHECK TON KHO
			query = "select sp.PK_SEQ, sp.TEN, SUM(dathang.soluong) as soluongXUAT, " +
			"	ISNULL( ( select AVAILABLE from ERP_KHOTT_SANPHAM where khott_fk = '" + khoNhanId + "' and sanpham_fk = sp.PK_SEQ ), 0) as tonkho " +
			"from    " +
			"(    " +
			"		select a.sanpham_fk, b.DVDL_FK as dvCHUAN,    " +
			"				case when a.dvdl_fk IS null then a.soluong     " +
			"					 when a.dvdl_fk = b.DVDL_FK then a.soluong    " +
			"					 else  a.soluong * ( select SOLUONG1 from QUYCACH where sanpham_fk = a.sanpham_fk and DVDL2_FK = a.dvdl_fk )     " +
			"									 / ( select SOLUONG2 from QUYCACH where sanpham_fk = a.sanpham_fk and DVDL2_FK = a.dvdl_fk )	 end as soluong, 0 as loai, ' ' as scheme   " +
			"		from ERP_DONDATHANGNPP_SANPHAM a inner join SANPHAM b on a.sanpham_fk = b.PK_SEQ    " +
			"		where a.dondathang_fk in ( '" + id + "' )   " +
			")    " +
			"dathang inner join SANPHAM sp on dathang.sanpham_fk = sp.PK_SEQ  " +
			"group by sp.PK_SEQ, sp.TEN " +
			"having  SUM(dathang.soluong) > ISNULL( ( select AVAILABLE from ERP_KHOTT_SANPHAM where khott_fk = '" + khoNhanId + "' and sanpham_fk = sp.PK_SEQ ), 0) " ;

			System.out.println("--CHECK TON KHO: " + query);

			ResultSet rsCHECK = db.get(query);
			if(rsCHECK != null)
			{
				while(rsCHECK.next())
				{
					msg = "Sản phẩm ( " + rsCHECK.getString("TEN") + " ) với số lượng yêu cầu ( " + rsCHECK.getString("soluongXUAT") + " ) không đủ tồn kho ( " + rsCHECK.getString("tonkho") + " ) của HO. Vui lòng liên hệ với HO để xử lý.";
					//db.getConnection().rollback();
					rsCHECK.close();
					return msg;
				}
				rsCHECK.close();
			}

			//Tu dong tao YCXK  --> VA CHOT YCXK NAY LUON
			query = " insert ERP_YCXUATKHO(NgayYeuCau, ghichu, trangthai, npp_fk, kho_fk, ngaytao, nguoitao, ngaysua, nguoisua) " +
			" values('" + this.getDateTime() + "', N'Phiếu xuất kho xuất dùm cho khách hàng ETC của CN cấp 2 / đối tác (đơn hàng số: " + id + ")', '2', '"
			+ nppId + "', " + khoNhanId + ", '" + getDateTime() + "', '" + userId + "', '" + getDateTime() + "', '" + userId + "' )";

			System.out.println("1.Insert YCXUATKHO: " + query);
			if(db.updateReturnInt(query) <= 0 )
			{
				msg = "Không thể tạo mới ERP_YCXUATKHO " + query;
				//db.getConnection().rollback();
				return msg;
			}

			query = "Insert ERP_YCXUATKHO_DDH(ycxk_fk, ddh_fk) " +
			"select IDENT_CURRENT('ERP_YCXUATKHO'), pk_seq from ERP_DONDATHANG where pk_seq in ( " + id + " )  ";
			if(!db.update(query))
			{
				msg = "Không thể tạo mới ERP_YCXUATKHO_DDH " + query;
				//db.getConnection().rollback();
				return msg;
			}

			query = "insert ERP_YCXUATKHO_SANPHAM( ycxk_fk, sanpham_fk, soluongDAT, tonkho, daxuat, soluongXUAT, LOAI, SCHEME ) " +
			"select IDENT_CURRENT('ERP_YCXUATKHO'), sp.PK_SEQ, SUM(dathang.soluong) as soluongDAT, ISNULL( ( select AVAILABLE from ERP_KHOTT_SANPHAM where khott_fk = '" + khoNhanId + "' and sanpham_fk = sp.PK_SEQ ), 0)  as tonkho, 0, SUM(dathang.soluong) as soluongXUAT, loai, scheme  " +
			"from    " +
			"(    " +
			"		select a.sanpham_fk, b.DVDL_FK as dvCHUAN,    " +
			"				case when a.dvdl_fk IS null then a.soluong     " +
			"					 when a.dvdl_fk = b.DVDL_FK then a.soluong    " +
			"					 else  a.soluong * ( select SOLUONG1 from QUYCACH where sanpham_fk = a.sanpham_fk and DVDL2_FK = a.dvdl_fk )     " +
			"									 / ( select SOLUONG2 from QUYCACH where sanpham_fk = a.sanpham_fk and DVDL2_FK = a.dvdl_fk )	 end as soluong, 0 as loai, ' ' as scheme   " +
			"		from ERP_DONDATHANGNPP_SANPHAM a inner join SANPHAM b on a.sanpham_fk = b.PK_SEQ    " +
			"		where a.dondathang_fk in ( '" + id + "' )   " +
			")    " +
			"dathang inner join SANPHAM sp on dathang.sanpham_fk = sp.PK_SEQ     " +
			"group by sp.PK_SEQ, scheme, loai  " ;

			System.out.println("1.1.Insert YCXK - SP: " + query);
			if(db.updateReturnInt(query) <= 0 )
			{
				msg = "Khong the tao moi ERP_YCXUATKHO_SANPHAM: " + query;
				//db.getConnection().rollback();
				return msg;
			}

			query = "select sp.PK_SEQ, sp.TEN, LOAI, SCHEME, SUM(dathang.soluong) as soluongXUAT " +
			"from    " +
			"(    " +
			"		select a.sanpham_fk, b.DVDL_FK as dvCHUAN,    " +
			"				case when a.dvdl_fk IS null then a.soluong     " +
			"					 when a.dvdl_fk = b.DVDL_FK then a.soluong    " +
			"					 else  a.soluong * ( select SOLUONG1 from QUYCACH where sanpham_fk = a.sanpham_fk and DVDL2_FK = a.dvdl_fk )     " +
			"									 / ( select SOLUONG2 from QUYCACH where sanpham_fk = a.sanpham_fk and DVDL2_FK = a.dvdl_fk )	 end as soluong, 0 as loai, ' ' as scheme   " +
			"		from ERP_DONDATHANGNPP_SANPHAM a inner join SANPHAM b on a.sanpham_fk = b.PK_SEQ    " +
			"		where a.dondathang_fk in ( '" + id + "' )   " +
			")    " +
			"dathang inner join SANPHAM sp on dathang.sanpham_fk = sp.PK_SEQ  " +
			"group by sp.PK_SEQ, sp.TEN, LOAI, SCHEME ";
			System.out.println("--CHECK KHO CHI TIET: " + query);
			rsCHECK = db.get(query);
			if(rsCHECK != null)
			{
				while(rsCHECK.next())
				{
					String sanpham_fk = rsCHECK.getString("PK_SEQ");
					String LOAI = rsCHECK.getString("LOAI");
					String SCHEME = rsCHECK.getString("SCHEME");
					double soLUONG = rsCHECK.getDouble("soluongXUAT");

					query = "Update ERP_KHOTT_SANPHAM set soluong = soluong - '" + soLUONG + "', AVAILABLE = AVAILABLE - '" + soLUONG + "' " +
					"where KHOTT_FK = '" + khoNhanId + "' and SANPHAM_FK = '" + sanpham_fk + "'  ";
					if(!db.update(query))
					{
						msg = "Khong the cap nhat ERP_KHOTT_SANPHAM: " + query;
						//db.getConnection().rollback();
						return msg;
					}

					//CAP NHAT KHO CHI TIET
					query = "select AVAILABLE, SOLO from ERP_KHOTT_SP_CHITIET " +
					"where KHOTT_FK = '" + khoNhanId + "'  and SANPHAM_FK = '" + sanpham_fk + "' order by ngayhethan asc ";

					ResultSet rsTK = db.get(query);
					double avai = 0;
					double totalXUAT = 0;
					while(rsTK.next())
					{
						double soluongCT = 0;
						String solo = rsTK.getString("SOLO");

						avai = rsTK.getDouble("AVAILABLE");
						totalXUAT += avai;

						if(totalXUAT <= soLUONG)
						{
							soluongCT = avai;

							query = "insert ERP_YCXUATKHO_SANPHAM_CHITIET( ycxk_fk, SANPHAM_FK, solo, soluong, loai, scheme ) " +
							"select IDENT_CURRENT('ERP_YCXUATKHO'), '" + sanpham_fk + "', N'" + solo + "', '" + soluongCT + "', '" + LOAI + "', '" + SCHEME + "' ";

							System.out.println("1.2.Insert YCXK - SP - CT: " + query);
							if(!db.update(query))
							{
								msg = "Khong the tao moi ERP_YCXUATKHO_SANPHAM_CHITIET: " + query;
								//db.getConnection().rollback();
								return msg;
							}

							query = "Update ERP_KHOTT_SP_CHITIET set soluong = soluong - '" + soluongCT + "', AVAILABLE = AVAILABLE - '" + soluongCT + "' " +
							"where KHOTT_FK = '" + khoNhanId + "' and SOLO = '" + solo + "' and SANPHAM_FK = '" + sanpham_fk + "'  ";
							if(!db.update(query))
							{
								msg = "Khong the cap nhat ERP_KHOTT_SP_CHITIET: " + query;
								//db.getConnection().rollback();
								return msg;
							}

						}
						else
						{
							soluongCT = soLUONG - ( totalXUAT - avai );

							query = "insert ERP_YCXUATKHO_SANPHAM_CHITIET( ycxk_fk, SANPHAM_FK, solo, soluong, loai, scheme ) " +
							"select IDENT_CURRENT('ERP_YCXUATKHO'), '" + sanpham_fk + "', N'" + solo + "', '" + soluongCT + "', '" + LOAI + "', '" + SCHEME + "' ";

							System.out.println("1.2.Insert YCXK - SP - CT: " + query);
							if(!db.update(query))
							{
								msg = "Khong the tao moi ERP_YCXUATKHO_SANPHAM_CHITIET: " + query;
								//db.getConnection().rollback();
								return msg;
							}

							query = "Update ERP_KHOTT_SP_CHITIET set soluong = soluong - '" + soluongCT + "', AVAILABLE = AVAILABLE - '" + soluongCT + "' " +
							"where KHOTT_FK = '" + khoNhanId + "' and SOLO = '" + solo + "' and SANPHAM_FK = '" + sanpham_fk + "'  ";
							if(!db.update(query))
							{
								msg = "Khong the cap nhat ERP_KHOTT_SP_CHITIET: " + query;
								//db.getConnection().rollback();
								return msg;
							}

							break;
						}
					}
					rsTK.close();

				}
				rsCHECK.close();
			}
		} 
		catch (Exception e) 
		{
			msg = "--LOI DUYET: " + e.getMessage();
			e.printStackTrace();
			return msg;
		}

		return "";
	}

	private String TaoPhieuXuatKhoNPP(dbutils db, String id, String userId, String khoNhanId, String nppId, String npp_dat_fk, String kbh_fk)
	{
		String query = "";
		String msg = "";
		Utility util_kho=new Utility();

		try
		{
			query =" update NHANVIEN SET Active = '1' where pk_seq='"+userId+"'";
			if(!db.update(query))
			{
				msg = "Không thể tạo mới ERP_YCXUATKHONPP " + query;
				//db.getConnection().rollback();
				return msg;
			}
			// CHECK XEM CO SP NAO CÓ SỐ LƯỢNG TRONG ĐƠN HÀNG MÀ CHƯA THIẾT LẬP
			// QUY CÁCH KHÔNG
			query = "		select a.sanpham_fk, b.DVDL_FK as dvCHUAN,    " +
			"				case when a.dvdl_fk IS null then a.soluong     " +
			"					 when a.dvdl_fk = b.DVDL_FK then a.soluong    " +
			"					 else  a.soluong * ( select SOLUONG1 / SOLUONG2 from QUYCACH where sanpham_fk = a.sanpham_fk and DVDL2_FK = a.dvdl_fk and DVDL1_FK = b.DVDL_FK )   end as soluong, " +
			"			0 as loai, ' ' as scheme, b.ten as spTEN   " +
			"		from ERP_DONDATHANGNPP_SANPHAM a inner join SANPHAM b on a.sanpham_fk = b.PK_SEQ    " +
			"		where a.dondathang_fk in ( '" + id + "' ) and a.soluong > 0   ";
			ResultSet rsCHECK = db.get(query);
			String spCHUACOQC = "";
			if(rsCHECK != null)
			{
				while(rsCHECK.next())
				{
					if (rsCHECK.getString("soluong") == null) // Chưa có thiết lập quy cách mà có số lượng
						spCHUACOQC += rsCHECK.getString("spTEN") + ", ";
				}
				rsCHECK.close();
			}

			if(spCHUACOQC.trim().length() > 0)
			{
				msg = "Các sản phẩm sau chưa thiết lập quy cách: " + spCHUACOQC;
				//db.getConnection().rollback();
				return msg;
			}

			//Tu dong tao YCXK  --> VA CHOT YCXK NAY LUON
			query = " insert ERP_YCXUATKHONPP(NgayYeuCau, ghichu, trangthai, npp_fk, kho_fk, xuatcho, npp_dat_fk, khachhang_fk, kbh_fk, ngaytao, nguoitao, ngaysua, nguoisua) " +
			" select '" + this.getDateTime() + "', N'Phiếu xuất kho tạo tự động từ duyệt đơn đặt hàng " + id+ "', '2', '" + nppId + "', " + khoNhanId + ", " +
			" case when npp_dat_fk is not null then '0' else '1' end as xuatcho, npp_dat_fk, khachhang_fk, KBH_FK as kbh_fk, '" + getDateTime() + "', '" + userId + "', '" + getDateTime() + "', '" + userId + "' " +
			" from ERP_DONDATHANGNPP where pk_seq = '" + id + "' ";

			System.out.println("1.Insert YCXUATKHO: " + query);
			if(db.updateReturnInt(query) <= 0 )
			{
				msg = "Không thể tạo mới ERP_YCXUATKHONPP " + query;
				//db.getConnection().rollback();
				return msg;
			}

			String ycxkId = "";
			ResultSet rsYCXK = db.get("select IDENT_CURRENT('ERP_YCXUATKHONPP') as ycxkId");
			if(rsYCXK.next())
			{
				ycxkId = rsYCXK.getString("ycxkId");
			}
			rsYCXK.close();

			query = "Insert ERP_YCXUATKHONPP_DDH(ycxk_fk, ddh_fk) " +
			"select '" + ycxkId + "', pk_seq from ERP_DONDATHANGNPP where pk_seq in ( " + id + " )  ";
			if(!db.update(query))
			{
				msg = "Không thể tạo mới ERP_YCXUATKHONPP_DDH " + query;
				//db.getConnection().rollback();
				return msg;
			}

			query = "insert ERP_YCXUATKHONPP_SANPHAM( ycxk_fk, sanpham_fk, soluongDAT, tonkho, daxuat, soluongXUAT, LOAI, SCHEME ) " +
			" select '" + ycxkId + "', sp.PK_SEQ, SUM(dathang.soluong) as soluongDAT, " +
			"	ISNULL( ( select AVAILABLE from NHAPP_KHO where kho_fk = dathang.khoxuat_fk and sanpham_fk = sp.PK_SEQ and kbh_fk = '" + kbh_fk + "' and npp_fk = dathang.npp_fk ), 0) as tonkho, 0, SUM(dathang.soluong) as soluongXUAT, loai, scheme  " +
			"from    " +
			"(    " +
			"		select c.kho_fk as khoxuat_fk, c.npp_fk, c.kbh_fk, a.sanpham_fk, b.DVDL_FK as dvCHUAN,    " +
			"				case when a.dvdl_fk IS null then a.soluong     " +
			"					 when a.dvdl_fk = b.DVDL_FK then a.soluong    " +
			"					 else  a.soluong * ( select SOLUONG1 from QUYCACH where sanpham_fk = a.sanpham_fk and DVDL2_FK = a.dvdl_fk )     " +
			"									 / ( select SOLUONG2 from QUYCACH where sanpham_fk = a.sanpham_fk and DVDL2_FK = a.dvdl_fk )	 end as soluong, 0 as loai, ' ' as scheme   " +
			"		from ERP_DONDATHANGNPP_SANPHAM a inner join SANPHAM b on a.sanpham_fk = b.PK_SEQ    " +
			"			inner join ERP_DONDATHANGNPP c on a.dondathang_fk = c.pk_seq    " +
			"		where a.dondathang_fk in ( '" + id + "' )   " +
			")    " +
			"dathang inner join SANPHAM sp on dathang.sanpham_fk = sp.PK_SEQ     " +
			"group by dathang.khoxuat_fk, dathang.npp_fk, sp.PK_SEQ, scheme, loai  " ;

			System.out.println("1.1.Insert YCXK - SP: " + query);
			if(db.updateReturnInt(query) <= 0 )
			{
				msg = "Khong the tao moi ERP_YCXUATKHO_SANPHAM: " + query;
				//db.getConnection().rollback();
				return msg;
			}


			/*// CẬP NHẬT KHO TỔNG
			query = "update kho   " +
					"set kho.soluong = kho.soluong - BOOK_KHO.soluong,  " +
					"	kho.booked = kho.booked - BOOK_KHO.soluong  " +
					"from " +
					"( " +
					"	select khoxuat_fk, npp_fk, kbh_fk, sanpham_fk, sum(soluong) as soluong  " +
					"	from " +
					"	( " +
					"		select c.kho_fk as khoxuat_fk, c.npp_fk, case when isnull(d.dungchungkenh, 0) = 0 then c.kbh_fk else 100025 end as kbh_fk, a.sanpham_fk,       " +
					"				case when a.dvdl_fk IS null then a.soluong       " +
					"					 when a.dvdl_fk = b.DVDL_FK then a.soluong      " +
					"					 else  a.soluong * ( select SOLUONG1 / SOLUONG2 from QUYCACH where sanpham_fk = a.sanpham_fk and DVDL2_FK = a.dvdl_fk and dvdl1_fk = b.dvdl_fk )  end as soluong    " +
					"		from ERP_DONDATHANGNPP_SANPHAM a inner join SANPHAM b on a.sanpham_fk = b.PK_SEQ   " +
					"				inner join ERP_DONDATHANGNPP c on a.dondathang_fk = c.pk_seq  inner join NHAPHANPHOI d on c.npp_fk = d.pk_seq   " +
					"		where a.dondathang_fk in (  " + this.id + "  ) and a.soluong > 0 " +
					"	) " +
					"	DATHANG  " +
					"	group by khoxuat_fk, npp_fk, kbh_fk, sanpham_fk " +
					") " +
					"BOOK_KHO inner join NHAPP_KHO kho on BOOK_KHO.khoxuat_fk = kho.kho_fk and BOOK_KHO.npp_fk = kho.npp_fk and BOOK_KHO.kbh_fk = kho.kbh_fk and BOOK_KHO.sanpham_fk = kho.sanpham_fk ";
			if(!db.update(query))
			{
				msg = "Không thể cập nhật NHAPP_KHO " + query;
				return msg;
			}*/

			query=	"  select khoxuat_fk, npp_fk, kbh_fk, sanpham_fk, sum(soluong) as soluong  " +
			"	from " +
			"	( " +
			"		select c.kho_fk as khoxuat_fk, c.npp_fk, case when isnull(d.dungchungkenh, 0) = 0 then c.kbh_fk else 100025 end as kbh_fk, a.sanpham_fk,       " +
			"				case when a.dvdl_fk IS null then a.soluong       " +
			"					 when a.dvdl_fk = b.DVDL_FK then a.soluong      " +
			"					 else  a.soluong * ( select SOLUONG1 / SOLUONG2 from QUYCACH where sanpham_fk = a.sanpham_fk and DVDL2_FK = a.dvdl_fk and dvdl1_fk = b.dvdl_fk )  end as soluong    " +
			"		from ERP_DONDATHANGNPP_SANPHAM a inner join SANPHAM b on a.sanpham_fk = b.PK_SEQ   " +
			"				inner join ERP_DONDATHANGNPP c on a.dondathang_fk = c.pk_seq  inner join NHAPHANPHOI d on c.npp_fk = d.pk_seq   " +
			"		where a.dondathang_fk in (  " + this.id + "  ) and a.soluong > 0 " +
			"	) " +
			"	DATHANG  " +
			"	group by khoxuat_fk, npp_fk, kbh_fk, sanpham_fk ";

			ResultSet rssp=db.get(query);
			String _khoxuat_fk="", _npp_fk="", _kbh_fk="", _sanpham_fk="";
			while(rssp.next()){
				_khoxuat_fk =rssp.getString("khoxuat_fk");
				_npp_fk =rssp.getString("npp_fk");
				_kbh_fk =rssp.getString("kbh_fk");
				_sanpham_fk =rssp.getString("sanpham_fk");

				double soluongct_=0;

				soluongct_=rssp.getDouble("soluong");

				String msg1= util_kho.Update_NPP_Kho_Sp(this.ngayyeucau, "Duyệt đơn hàng đối tác ErpdondathangDoitac.java 3515 ", db, _khoxuat_fk, _sanpham_fk, _npp_fk, _kbh_fk,(-1)* soluongct_, (-1)* soluongct_, 0, 0);

				if(msg1.length()>0){
					return msg1;

				}

			}
			rssp.close();


			query = "select c.npp_fk, case when isnull(d.dungchungkenh, 0) = 0 then c.kbh_fk else 100025 end as kbh_fk, " +
			"		c.kho_fk, a.sanpham_fk, b.ten as TEN, a.soluong as soluongDAT, a.solo, a.ngayhethan,a.ngaynhapkho ,  " +
			"		case when a.dvdl_fk IS null then a.soluong      " +
			"			 when a.dvdl_fk = b.DVDL_FK then a.soluong     " +
			"			 else  a.soluong * ( select SOLUONG1 / SOLUONG2 from QUYCACH where sanpham_fk = a.sanpham_fk and DVDL2_FK = a.dvdl_fk and DVDL1_FK = b.DVDL_FK )   end as soluong,  " +
			"	0 as loai, ' ' as scheme    " +
			"from ERP_DONDATHANGNPP_SANPHAM_CHITIET a inner join SANPHAM b on a.sanpham_fk = b.PK_SEQ   " +
			"		inner join ERP_DONDATHANGNPP c on  a.dondathang_fk = c.pk_seq inner join NHAPHANPHOI d on c.npp_fk = d.pk_seq " +
			"where a.dondathang_fk in ( " + this.id + " )   ";

			System.out.println("--CHECK TON KHO CHI TIET: " + query);

			ResultSet rs = db.get(query);
			if(rs != null)
			{
				while(rs.next())
				{
					String khoID = rs.getString("kho_fk");
					String kbhID = rs.getString("kbh_fk");
					String nppID = rs.getString("npp_fk");
					String spID = rs.getString("sanpham_fk");

					double soluong = rs.getDouble("soluong");
					String solo = rs.getString("solo");
					String ngayhethan = rs.getString("ngayhethan");
					String ngaynhapkho=rs.getString("ngaynhapkho");

					String msg1= util.Update_NPP_Kho_Sp_Chitiet("", "Duyệt đơn hàng đối tác ErpdondathangDoitac.java 3612 ", db, khoID, spID, nppID, kbhID, solo, ngayhethan, ngaynhapkho, (-1)* soluong, (-1)* soluong, 0, 0, 0);

					if(msg1.length()>0){
						return msg1;
					}


					query = "insert ERP_YCXUATKHONPP_SANPHAM_CHITIET( ycxk_fk, SANPHAM_FK, solo, soluong, ngayhethan, kho_fk ,NGAYNHAPKHO) " +
					"select '" + ycxkId + "', '" + spID + "', N'" + solo + "', '" + soluong  + "', '" + ngayhethan + "', '" + khoID + "','"+ngaynhapkho+"' ";

					System.out.println("1.2.Insert YCXK - SP - CT: " + query);
					if(!db.update(query))
					{
						msg = "Khong the tao moi ERP_YCXUATKHONPP_SANPHAM_CHITIET: " + query;
						//db.getConnection().rollback();
						rs.close();
						return msg;
					}							
				}
				rs.close();
			}

			//CHECK TONG PHAI BANG CHI TIET
			query = "select count(*) as soDONG   " +
			"from ERP_YCXUATKHONPP_SANPHAM tong left join   " +
			"	(  " +
			"		select sanpham_fk, sum(soluong) as soluong   " +
			"		from ERP_YCXUATKHONPP_SANPHAM_CHITIET  " +
			"		where  ycxk_fk = '" + ycxkId + "'  " +
			"		group by sanpham_fk " +
			"	)  " +
			"	CT on tong.sanpham_fk = CT.sanpham_fk " +
			"where ycxk_fk = '" + ycxkId + "' and tong.soluongXUAT != isnull(CT.soluong, 0)  ";
			rsCHECK = db.get(query);
			int soDONG = 0;
			if(rsCHECK != null )
			{
				if( rsCHECK.next() )
				{
					soDONG = rsCHECK.getInt("soDONG");
				}
				rsCHECK.close();
			}

			if(soDONG > 0)
			{
				db.getConnection().rollback();
				return "11.Lỗi hệ thống ( tổng xuất theo lô đề xuất đang bị sai ). Vui lòng liên hệ trung tâm để được hỗ trợ xử lý.";
			}

			if(npp_dat_fk.trim().length() > 0)
			{
				//Tu dong tao nhan hang
				query = " insert NHAPHANG(NGAYNHAN, NGAYCHUNGTU, NPP_FK, NCC_FK, GSBH_FK, ASM_FK, BM_FK, DVKD_FK, KBH_FK, YCXKNPP_FK, TRANGTHAI, NGUOITAO, NGAYTAO, NGUOISUA, NGAYSUA) " +
				" select distinct NgayYeuCau, NgayYeuCau, NPP_DAT_FK,  " +
				" 			( select top(1) NCC_FK from NHACUNGCAP_DVKD where PK_SEQ in ( select NCC_DVKD_FK from NHAPP_NHACC_DONVIKD where NPP_FK = a.NPP_DAT_FK ) ), " +
				"			( select top(1) GSBH_FK from NHAPP_GIAMSATBH where NPP_FK = a.NPP_DAT_FK ), " +
				"			( select top(1) ASM_FK from ASM_KHUVUC where KHUVUC_FK in ( select KHUVUC_FK from NHAPHANPHOI where PK_SEQ = a.NPP_DAT_FK )), " +
				"			( select top(1) BM_FK from BM_CHINHANH where VUNG_FK in ( select VUNG_FK from KHUVUC where PK_SEQ in (  select KHUVUC_FK from NHAPHANPHOI where PK_SEQ = a.NPP_DAT_FK ) ) ), " +
				" 	   '100001' as DVKD_FK, a.KBH_FK, '" + ycxkId + "', '0', '" + userId + "', '" + getDateTime() + "', '" + userId + "', '" + getDateTime() + "' " +
				" from ERP_YCXUATKHONPP a inner join ERP_YCXUATKHONPP_SANPHAM b on a.PK_SEQ = b.ycxk_fk " +
				" where a.PK_SEQ = '" + ycxkId + "' ";

				System.out.println("---INSERT NHAN HANG: " + query );
				if(!db.update(query))
				{
					msg = "Không tạo mới NHAPHANG " + query;
					//db.getConnection().rollback();
					return msg;
				}

				query = " insert NHAPHANG_SP(NHAPHANG_FK, SANPHAM_FK, SOLUONG, soluongNHAN, DONGIA, CHIETKHAU, DVDL_FK, LOAI, SCHEME, SOLO, NGAYHETHAN) " +
				" select ( select pk_seq from NHAPHANG where YCXKNPP_FK = a.PK_SEQ  ),  " +
				" 		b.sanpham_fk, b.soluong, NULL, b.dongia, 0 as chietkhau, c.DVDL_FK, b.LOAI, b.SCHEME, b.solo, b.ngayhethan " +
				" from ERP_YCXUATKHONPP a inner join ERP_YCXUATKHONPP_SANPHAM_CHITIET b on a.PK_SEQ = b.ycxk_fk " +
				" 		inner join SANPHAM c on b.sanpham_fk = c.PK_SEQ   " +
				" where a.PK_SEQ = '" + ycxkId + "' and b.soluong > 0 ";

				System.out.println("---INSERT NHAN HANG - SP: " + query );
				if(!db.update(query))
				{
					msg = "Không tạo mới NHAPHANG_SP " + query;
					//db.getConnection().rollback();
					return msg;
				}

				query = "insert NHAPHANG_DDH(nhaphang_fk, ddh_fk)  " +
				"select ( select PK_SEQ from NHAPHANG where YCXKNPP_FK = '" + id + "' ) as nhID, ddh_fk  " +
				"from ERP_YCXUATKHONPP_DDH where ycxk_fk = '" + ycxkId + "'";
				if(!db.update(query))
				{
					msg = "Không tạo mới NHAPHANG_DDH " + query;
					//db.getConnection().rollback();
					return msg;
				}
			}
		} 
		catch (Exception e) {

			e.printStackTrace();
			return "Không thể duyệt đơn hàng " + e.getMessage();
		}

		return "";
	}

	public Hashtable<String, String> getSanpham_Soluong() {

		return this.sanpham_soluong;
	}


	public void setSanpham_Soluong(Hashtable<String, String> sp_soluong) {

		this.sanpham_soluong = sp_soluong;
	}

	public ResultSet getSoloTheoSp(String spMa, String donvi, String tongluong)
	{

		Utility  util_kho=new Utility();

		String ngayhoadon_=util_kho.getngayhoadon(this.userId, db,this.ngayyeucau,this.khId,1);


		tongluong = tongluong.replaceAll(",", "");
		//System.out.println("---TONG LUONG: " + tongluong );


		String kbh_fk = "";
		String sqlk="	select (select  kbh_fk from ERP_DONDATHANGNPP where pk_seq="+this.id +")kbh_fk" +
					"	, (select pk_seq from donvidoluong where donvi = N'"+donvi+"')donvi		 ";
		ResultSet rsk=db.get(sqlk);
		try {
			rsk.next();
			kbh_fk=rsk.getString("kbh_fk");
			donvi =  rsk.getString("donvi");
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

		/*String sqlDONHANG = "";
		if( this.id.trim().length() > 0 )
			sqlDONHANG = " select SUM(soluong) from ERP_DONDATHANGNPP_SANPHAM_CHITIET where dondathang_fk = '" + this.id + "' and SANPHAM_FK = CT.sanpham_fk and solo = CT.solo and ngayhethan = CT.ngayhethan and ngaynhapkho = CT.ngaynhapkho  ";
		else
			sqlDONHANG = " select SUM(soluong) from ERP_DONDATHANGNPP_SANPHAM_CHITIET where dondathang_fk = '1' and SANPHAM_FK = CT.sanpham_fk and solo = CT.solo and ngayhethan = CT.ngayhethan and ngaynhapkho = CT.ngaynhapkho  ";

		String query = "select case when sp.dvdl_fk != '" + donvi + "'  " +
					   "	then ( select soluong2 / soluong1 from QUYCACH where SANPHAM_FK = sp.PK_SEQ and DVDL1_FK = sp.DVDL_FK and DVDL2_FK = '" + donvi + "' ) * AVAILABLE else AVAILABLE end as AVAILABLE,  " +
					   "	NGAYHETHAN, SOLO " +
					   "from NHAPP_KHO_CHITIET ct inner join SANPHAM sp on ct.sanpham_fk = sp.pk_seq " +
					   "where KHO_FK = '" + this.khoNhanId + "' and SANPHAM_FK = ( select pk_seq from SANPHAM where ma = '" + spMa + "' )   " +
					   "	and AVAILABLE + ( " + sqlDONHANG + " ) > 0 and NPP_FK = '" + this.nppId + "' and KBH_FK = '" + kbh_fk + "'  order by NGAYHETHAN asc ";*/


		String dondathang=this.id;

		String query= " select * from  ( "+
		"\n	 	 select '"+ngayhoadon_+"' as NgayDonHang ,a.SANPHAM_FK,a.soluong,a.ngayhethan,a.solo,a.ngaynhapkho,  "+
		"\n	 	case when sp.dvdl_fk !=  '" + donvi + "'    "+
		"\n				then (( select soluong2 / soluong1 from QUYCACH where SANPHAM_FK = a.sanpham_fk  "+
		"\n		 		and DVDL1_FK = sp.DVDL_FK and DVDL2_FK = '" + donvi + "'  ) * AVAILABLE + a.soluong) else AVAILABLE  + a.soluong end as AVAILABLE   "+
		"\n		 from ERP_DONDATHANGNPP_SANPHAM_CHITIET a inner join ERP_DONDATHANGNPP b  "+
		"\n		on a.dondathang_fk=b.PK_SEQ inner join SANPHAM sp on sp.PK_SEQ=a.sanpham_fk   "+
		"\n		inner join NHAPP_KHO_CHITIET kho on kho.SANPHAM_FK=a.sanpham_fk  "+ 
		"\n		and kho.KBH_FK=(select case when isnull(dungchungkenh,0)=1 then 100025 else 100052 end from nhaphanphoi where pk_Seq="+this.nppId+")  "+
		"\n		 and kho.KHO_FK=b.kho_fk and kho.NPP_FK="+this.nppId+"  "+
		"\n		and kho.SOLO=a.solo and kho.NGAYHETHAN=a.ngayhethan and kho.NGAYNHAPKHO=a.ngaynhapkho  "+
		"\n		where b.PK_SEQ='"+dondathang+"' and a.sanpham_fk= ( select pk_seq from SANPHAM where ma = '"+spMa+"' ) "+
		"\n		union all "+
		"\n 	select '"+ngayhoadon_+"',ct.SANPHAM_FK,0 as soluong,ct.NGAYHETHAN,ct.SOLO,ct.NGAYNHAPKHO,case when sp.dvdl_fk != '"+donvi+"'   "+
		"\n		then ( select soluong2 / soluong1 from QUYCACH  "+
		"\n		where SANPHAM_FK = sp.PK_SEQ and DVDL1_FK = sp.DVDL_FK and DVDL2_FK = '"+donvi+"' ) * AVAILABLE  "+
		"\n		else AVAILABLE end as AVAILABLE "+
		"\n 	from NHAPP_KHO_CHITIET ct inner join SANPHAM sp on ct.sanpham_fk = sp.pk_seq  "+
		"\n	where KHO_FK = '"+this.khoNhanId+"' and SANPHAM_FK = ( select pk_seq from SANPHAM where ma = '"+spMa+"' )   "+
		"\n		and AVAILABLE > 0  and NPP_FK = '"+this.nppId+"' and KBH_FK = '"+kbh_fk+"'   "+
		"\n	and not exists ( "+

		"\n  select '"+ngayhoadon_+"' as NgayDonHang,a.SANPHAM_FK,a.soluong,a.ngayhethan,a.solo,a.ngaynhapkho,   "+
		"\n	 	case when sp.dvdl_fk !=  '"+donvi+"'     "+
		"\n				then (( select soluong2 / soluong1 from QUYCACH where SANPHAM_FK = a.sanpham_fk   "+
		"\n		 		and DVDL1_FK = sp.DVDL_FK and DVDL2_FK = '"+donvi+"'  ) * AVAILABLE + a.soluong) else AVAILABLE  + a.soluong end as AVAILABLE  "+  
		"\n		 from ERP_DONDATHANGNPP_SANPHAM_CHITIET a inner join ERP_DONDATHANGNPP b   "+
		"\n		on a.dondathang_fk=b.PK_SEQ inner join SANPHAM sp on sp.PK_SEQ=a.sanpham_fk    "+
		"\n		inner join NHAPP_KHO_CHITIET kho on kho.SANPHAM_FK=a.sanpham_fk   "+
		"\n		and kho.KBH_FK=(select case when isnull(dungchungkenh,0)=1 then 100025 else 100052 end from nhaphanphoi where pk_Seq="+this.nppId+")   "+
		"\n		 and kho.KHO_FK=b.kho_fk and kho.NPP_FK="+this.nppId+"   "+
		"\n		and kho.SOLO=a.solo and kho.NGAYHETHAN=a.ngayhethan and kho.NGAYNHAPKHO=a.ngaynhapkho   "+
		"\n		where b.PK_SEQ='"+dondathang+"' and a.sanpham_fk= ( select pk_seq from SANPHAM where ma =  '"+spMa+"' ) "+
		"\n		  and a.SANPHAM_FK=ct.SANPHAM_FK and a.ngayhethan=ct.NGAYHETHAN and a.solo=ct.SOLO "+
		"\n		  and kho.kbh_fk=ct.KBH_FK and b.Kho_FK=ct.KHO_FK and a.ngaynhapkho=ct.NGAYNHAPKHO		"+ 
		"\n	) "+
		"\n	) as data "+
		"\n	where  data.ngaydonhang >= data.ngaynhapkho "+
		"\n	order by data.NGAYHETHAN  asc ";


		System.out.println("----LAY SO LO: " + query );
		String query2 = "";
		ResultSet rs = db.get(query);
		try 
		{
			double total = 0;

			while(rs.next())
			{
				double slg = 0;
				double avai = Math.round(rs.getDouble("AVAILABLE") * 100.0 ) / 100.0;

				System.out.println("===================== AVAI: " + avai);
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
					query2 += "select '" + avai + "' as AVAILABLE, '" + rs.getString("NGAYHETHAN") + "' as NGAYHETHAN, '"+rs.getString("ngaynhapkho")+"' as NGAYNHAPKHO,'" + rs.getString("SOLO") + "' as SOLO, '" + slg + "' as tuDEXUAT union ALL ";
				}
				else
				{
					query2 += "select '" + avai + "' as AVAILABLE, '" + rs.getString("NGAYHETHAN") + "' as NGAYHETHAN, '"+rs.getString("ngaynhapkho")+"' as NGAYNHAPKHO, '" + rs.getString("SOLO") + "' as SOLO, '' as tuDEXUAT union ALL ";
				}

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
			//System.out.println("---TU DONG DE XUAT BIN - LO: " + query2 );
			return db.get(query2);
		}

		return null;
	}

	public String getDungchungKenh() {

		return this.dungchungKENH;
	}


	public void setDungchungKenh(String dungchungKenh) {

		this.dungchungKENH = dungchungKenh;
	}


	public String getCongNo() {

		return this.congno;
	}

	public String[] getSpGiagoc()
	{
		return this.spGiagoc;
	}


	public void setSpGiagoc(String[] spGiagoc)
	{
		this.spGiagoc = spGiagoc;

	}


	public int getNgay_Chenhlech() 
	{
		return this.ngay_Chenhlech;
	}


	public void setNgay_Chenhlech(int ngay_Chenhlech) 
	{
		this.ngay_Chenhlech = ngay_Chenhlech;

	}


	/*// CN HÀ NỘI DÙNG MẪU HD TRÊN TT
	if(this.nppId.equals("100002") ) 
    {

		// KIEM TRA USER ĐÃ KHAI BAO SO HOA DON TRONG DLN CHUA
		query= " select count(pk_seq) as dem" +
			   " from NHANVIEN" +
			   " where pk_seq= '"+ this.userId+"' and  isnull(sohoadontu2, '') != '' and isnull( sohoadonden2, '') != ''" +
			   "       and isnull(kyhieu2,'') != '' ";

		System.out.println("_KIEM TRA USER ĐÃ KHAI BAO SO HOA DON TRONG DLN CHUA__" + query);
		ResultSet KTDLN = db.get(query);
		if(KTDLN!= null)
		{
			while(KTDLN.next())
			{
				kbDLN = KTDLN.getInt("dem");
			}
			KTDLN.close();
		}

		if(kbDLN <= 0 )
		{					
			msg1 = "Vui lòng khai báo Số hóa đơn trong (Thiết lập dữ liệu nền > Số hóa đơn) cho user này ";
			return msg1;
		}
		else
		{
			// LAY KY HIEU HOA DON ,SOHDTU TRONG DLN
			query= " select kyhieu2 as kyhieuhoadon, isnull(sohoadontu2, -1) as sohoadontu, isnull(sohoadonden2, -1) as sohoadonden, " +
				   "        isnull(ngayhoadon2, '') as ngayhoadon " +
				   " from NHANVIEN" +
				   " where pk_seq = '" + userId + "'";

			ResultSet rsLayDL = db.get(query);
			if(rsLayDL != null )
			{
				while(rsLayDL.next())
				{
					kyhieuhoadon= rsLayDL.getString("kyhieuhoadon");
					sohoadontu = rsLayDL.getLong("sohoadontu");
					sohoadonden = rsLayDL.getString("sohoadonden");
				}
				rsLayDL.close();
			}

			if(sohoadontu == -1 || sohoadonden.equals("-1") )
			{
				msg = "Vui lòng thiết lập khoảng số hóa đơn cho USER";
				return msg;
			}
			// KIEM TRA SOHOADON DA DUOC DUNG CHUA
			    // OTC
			query =" select count(pk_seq) as dem " +
				   " from HOADON " +
				   " where kyhieu = '" + kyhieuhoadon + "' and cast(sohoadon as int) >=  "+ sohoadontu +" and cast(sohoadon as int) <=  "+ Integer.parseInt(sohoadonden) + "  " +
				   "       and trangthai != 3 and nguoisua= "+ userId +" and sohoadon != 'NA' and mauhoadon = 2 ";
			System.out.println("1.Câu kiểm tra OTC: " + query);
			ResultSet KiemTra = db.get(query);
			int check = 0;
			if(KiemTra != null)
			{
				while(KiemTra.next())
				{
					check = KiemTra.getInt("dem");
				}
				KiemTra.close();
			}

			// ETC
			query = " select count(pk_seq) as dem " +
					" from ERP_HOADONNPP " +
					" where kyhieu = '" + kyhieuhoadon + "' and cast(sohoadon as numeric(18,0)) >= " + sohoadontu + " and cast(sohoadon as numeric(18,0)) <= " + Integer.parseInt(sohoadonden) + " " +
					"       and trangthai != 3 and nguoisua= "+ userId +" and sohoadon != 'NA' and mauhoadon = 2 ";
			System.out.println("2.Câu kiểm tra ETC: " + query);
			ResultSet KiemTra_OTC = db.get(query);
			int check_OTC = 0;
			if(KiemTra_OTC != null)
			{
				while(KiemTra_OTC.next())
				{
					check_OTC = KiemTra_OTC.getInt("dem");
				}
				KiemTra_OTC.close();
			}

			// LAY SOIN MAX	
			if(check <= 0 && check_OTC <= 0)
			{
				chuoi = ("000000"+ sohoadontu);
				chuoi = chuoi.substring(chuoi.length() - 7, chuoi.length());
			}
			else
			{// LAY SOIN MAX TRONG HOADON : 
					  //OTC
					query = "select  MAX(cast(SOHOADON as numeric)) as SOIN_MAX" +
						" from HOADON where KYHIEU = '" + kyhieuhoadon + "' and cast(sohoadon as numeric(18, 0)) >= "+ sohoadontu +" and cast(sohoadon as numeric(18,0)) <= " + Integer.parseInt(sohoadonden) + " " +
						"      and trangthai != 3 and nguoisua= "+ userId +" and sohoadon != 'NA' and mauhoadon = 2 ";

				System.out.println("Câu lấy shd max " + query);
					ResultSet laySOIN = db.get(query);
				    long soinMAX_OTC= 0;
					if(laySOIN!= null)
					{
						while(laySOIN.next())
						{
							soinMAX_OTC = laySOIN.getLong("SOIN_MAX");

						}laySOIN.close();
					}


					 //ETC
					query = "select  MAX(cast(SOHOADON as numeric)) as SOIN_MAX" +
							" from ERP_HOADONNPP where KYHIEU ='"+ kyhieuhoadon +"' and cast(sohoadon as numeric(18,0)) >= "+ sohoadontu +" and cast(sohoadon as numeric(18,0))<= " + Integer.parseInt(sohoadonden) + " " +
							"  and nguoisua= "+ userId +" and sohoadon != 'NA' and mauhoadon = 2 ";
				System.out.println("Câu lấy shd max " + query);
					ResultSet laySOIN_ETC = db.get(query);
				    long soinMAX_ETC= 0;
					if(laySOIN_ETC!= null)
					{
						while(laySOIN_ETC.next())
						{
							soinMAX_ETC = laySOIN_ETC.getLong("SOIN_MAX");

						}laySOIN_ETC.close();
					}

				if(soinMAX_OTC > soinMAX_ETC) 
				{
					chuoi = ("000000"+ (soinMAX_OTC >0 ? (soinMAX_OTC +1) :"1"));
				}else
				{
					chuoi = ("000000"+ (soinMAX_ETC >0 ? (soinMAX_ETC +1) :"1"));
				}

				chuoi = chuoi.substring(chuoi.length() - 7, chuoi.length());

			}


			System.out.println("---SO HOA DON LAY DUOC KHI CHUA VUOT: " + chuoi );

			if(Integer.parseInt(chuoi) > Integer.parseInt(sohoadonden.trim()))
			{ 
				//CHECK THEM NEU TRONG KHOANG HOA DON CUA USER DO VAN CON SHD THI TU DONG LAY SO DO
				query = "select  MAX(cast(SOHOADON as numeric)) as SOIN_MAX  " +
						"from HOADON a inner join NHANVIEN b on a.NGUOITAO = b.PK_SEQ " +
						"where  a.nguoisua= '" + userId + "' and a.kyhieu = '" + kyhieuhoadon + "' and a.trangthai != 3 and mauhoadon = 2 " +
						"		and cast(a.SOHOADON as numeric) >= CAST(b.sohoadontu as numeric(18, 0) )   " +
						"		and cast(a.SOHOADON as numeric) <= CAST(b.sohoadonden as numeric(18, 0) ) and a.sohoadon != 'NA'  " +
						"having "+
						" MAX(cast(SOHOADON as numeric)) != ( select  MAX(cast(SOHOADON as numeric)) as SOIN_MAX  from HOADON where  kyhieu = '" + kyhieuhoadon + "' and nguoisua= '" + userId + "' and sohoadon != 'NA' )";

				System.out.println("Câu check khoang HOADON: " + query);
				ResultSet SoMAX_HD = db.get(query);
				String soinmax= "";
				if(SoMAX_HD!= null)
				{
					while(SoMAX_HD.next())
					{
						soinmax = SoMAX_HD.getString("SOIN_MAX")== null ? "" : SoMAX_HD.getString("SOIN_MAX") ;
						chuoi = ("000000" + (SoMAX_HD.getLong("SOIN_MAX")));
						System.out.println("---SO HOA DON LAY DUOC KHI VUOT: " + chuoi );

					}
					SoMAX_HD.close();
				}
				chuoi = chuoi.substring(chuoi.length() - 7, chuoi.length());

				if(soinmax.trim().length() <= 0)
				{
					msg = "Số hóa đơn tiếp theo  đã vượt quá Số hóa đơn đến (" + sohoadonden + ")  trong dữ liệu nền. Vui lòng vào dữ liệu nền khai báo lại ! ";
					return msg;
				}
			}

			sohoadon =  chuoi;

			// KIEM TRA LAI SO HOA DON MOI TAO CO TRUNG VS SO HOA DON NAO HIEN TAI TRONG HD O & E 
			query = " select (select count(*) from HOADON where SOHOADON = '"+ sohoadon +"' and kyhieu = '"+ kyhieuhoadon +"' and trangthai != '3' and npp_fk = '" + nppId + "' and mauhoadon = 2 and sohoadon != 'NA' ) as KtraOTC, " +
					"        (select count(*) from ERP_HOADONNPP where SOHOADON = '"+ sohoadon +"' and kyhieu = '"+ kyhieuhoadon +"' and trangthai != '3' and npp_fk = '" + nppId + "' and mauhoadon = 2 and sohoadon != 'NA' ) as KtraETC " +
					" from NHANVIEN where pk_seq = '" + userId + "' ";
			ResultSet RsRs = db.get(query);
			int KT_OTC = 0;
			int KT_ETC = 0;
			if(RsRs != null)
			{
				while(RsRs.next())
				{
					KT_OTC = RsRs.getInt("KtraOTC") ;
					KT_ETC = RsRs.getInt("KtraETC") ;
				}
			}

			if (KT_OTC > 0 || KT_ETC > 0) // CÓ HÓA ĐƠN (CỦA USER
											// KHÁC) CÓ SỐ HD TRÙNG
											// VS SỐ HÓA ĐƠN MỚI TẠO
			{
				msg = "Số hóa đơn tiếp theo đã trùng với số hóa đơn trong Hóa Đơn OTC/ETC ! ";
				return msg;
			}

		}

	}
	else
	{
		// KIEM TRA USER ĐÃ KHAI BAO SO HOA DON TRONG DLN CHUA
	query= " select count(pk_seq) as dem" +
		   " from NHANVIEN" +
		   " where pk_seq= '"+ this.userId+"' and  isnull(sohoadontu, '') != '' and isnull( sohoadonden, '') != ''" +
		   "       and isnull(kyhieu,'') != '' ";

		System.out.println("_KIEM TRA USER ĐÃ KHAI BAO SO HOA DON TRONG DLN CHUA_" + query);

	ResultSet KTDLN = db.get(query);
	if(KTDLN!= null)
	{
		while(KTDLN.next())
		{
			kbDLN = KTDLN.getInt("dem");
		}
		KTDLN.close();
	}

	if(kbDLN <= 0 )
	{					
			msg1 = "Vui lòng khai báo Số hóa đơn trong (Thiết lập dữ liệu nền > Số hóa đơn) cho user này ";
		return msg1;
	}
	else
	{
		// LAY KY HIEU HOA DON ,SOHDTU TRONG DLN
		query= " select kyhieu as kyhieuhoadon, isnull(sohoadontu, -1) as sohoadontu, isnull(sohoadonden, -1) as sohoadonden, " +
			   "        isnull(ngayhoadon, '') as ngayhoadon " +
			   " from NHANVIEN where pk_seq = '" + userId + "'";

		ResultSet rsLayDL = db.get(query);
		if(rsLayDL != null )
		{
			while(rsLayDL.next())
			{
				kyhieuhoadon= rsLayDL.getString("kyhieuhoadon");
				sohoadontu = rsLayDL.getLong("sohoadontu");
				sohoadonden = rsLayDL.getString("sohoadonden");
			}
			rsLayDL.close();
		}

		if(sohoadontu == -1 || sohoadonden.equals("-1") )
		{
				msg = "Vui lòng thiết lập khoảng số hóa đơn cho USER";
			return msg;
		}
		// KIEM TRA SOHOADON DA DUOC DUNG CHUA
		    // OTC
		query =" select count(pk_seq) as dem " +
			   " from HOADON " +
			   " where kyhieu = '" + kyhieuhoadon + "' and cast(sohoadon as int) >=  "+ sohoadontu +" and cast(sohoadon as int) <=  "+ Integer.parseInt(sohoadonden) + "  " +
			   "       and trangthai != 3 and nguoisua= "+ userId +" and sohoadon != 'NA' and mauhoadon = 1 ";
			System.out.println("1.Câu kiểm tra OTC: " + query);
		ResultSet KiemTra = db.get(query);
		int check = 0;
		if(KiemTra != null)
		{
			while(KiemTra.next())
			{
				check = KiemTra.getInt("dem");
			}
			KiemTra.close();
		}

		// ETC
		query = " select count(pk_seq) as dem " +
				" from ERP_HOADONNPP " +
				" where kyhieu = '" + kyhieuhoadon + "' and cast(sohoadon as numeric(18,0)) >= " + sohoadontu + " and cast(sohoadon as numeric(18,0)) <= " + Integer.parseInt(sohoadonden) + " " +
				"       and trangthai != 3 and nguoisua= "+ userId +" and sohoadon != 'NA' and mauhoadon = 1 ";
			System.out.println("2.Câu kiểm tra ETC: " + query);
		ResultSet KiemTra_OTC = db.get(query);
		int check_OTC = 0;
		if(KiemTra_OTC != null)
		{
			while(KiemTra_OTC.next())
			{
				check_OTC = KiemTra_OTC.getInt("dem");
			}
			KiemTra_OTC.close();
		}

		// LAY SOIN MAX	
		if(check <= 0 && check_OTC <= 0)
		{
			chuoi = ("000000"+ sohoadontu);
			chuoi = chuoi.substring(chuoi.length() - 7, chuoi.length());
		}
		else
		{// LAY SOIN MAX TRONG HOADON : 
				  //OTC
				query = "select  MAX(cast(SOHOADON as numeric)) as SOIN_MAX" +
					" from HOADON where KYHIEU = '" + kyhieuhoadon + "' and cast(sohoadon as numeric(18, 0)) >= "+ sohoadontu +" and cast(sohoadon as numeric(18,0)) <= " + Integer.parseInt(sohoadonden) + " " +
					"      and trangthai != 3 and nguoisua= "+ userId +" and sohoadon != 'NA' and mauhoadon = 1 ";

				System.out.println("Câu lấy shd max " + query);
				ResultSet laySOIN = db.get(query);
			    long soinMAX_OTC= 0;
				if(laySOIN!= null)
				{
					while(laySOIN.next())
					{
						soinMAX_OTC = laySOIN.getLong("SOIN_MAX");

					}laySOIN.close();
				}


				 //ETC
				query = "select  MAX(cast(SOHOADON as numeric)) as SOIN_MAX" +
						" from ERP_HOADONNPP where KYHIEU ='"+ kyhieuhoadon +"' and cast(sohoadon as numeric(18,0)) >= "+ sohoadontu +" and cast(sohoadon as numeric(18,0))<= " + Integer.parseInt(sohoadonden) + " " +
						"   and trangthai != 3 and nguoisua= "+ userId +" and sohoadon != 'NA' and mauhoadon = 1 ";
				System.out.println("Câu lấy shd max " + query);
				ResultSet laySOIN_ETC = db.get(query);
			    long soinMAX_ETC= 0;
				if(laySOIN_ETC!= null)
				{
					while(laySOIN_ETC.next())
					{
						soinMAX_ETC = laySOIN_ETC.getLong("SOIN_MAX");

					}laySOIN_ETC.close();
				}

			if(soinMAX_OTC > soinMAX_ETC) 
			{
				chuoi = ("000000"+ (soinMAX_OTC >0 ? (soinMAX_OTC +1) :"1"));
			}else
			{
				chuoi = ("000000"+ (soinMAX_ETC >0 ? (soinMAX_ETC +1) :"1"));
			}

			chuoi = chuoi.substring(chuoi.length() - 7, chuoi.length());

		}


		System.out.println("---SO HOA DON LAY DUOC KHI CHUA VUOT: " + chuoi );

		if(Integer.parseInt(chuoi) > Integer.parseInt(sohoadonden.trim()))
		{ 
			//CHECK THEM NEU TRONG KHOANG HOA DON CUA USER DO VAN CON SHD THI TU DONG LAY SO DO
			query = "select  MAX(cast(SOHOADON as numeric)) as SOIN_MAX  " +
					"from HOADON a inner join NHANVIEN b on a.NGUOITAO = b.PK_SEQ " +
					"where  a.nguoisua= '" + userId + "' and a.kyhieu = '" + kyhieuhoadon + "' and a.trangthai != 3 and mauhoadon = 1 " +
					"		and cast(a.SOHOADON as numeric) >= CAST(b.sohoadontu as numeric(18, 0) )   " +
					"		and cast(a.SOHOADON as numeric) <= CAST(b.sohoadonden as numeric(18, 0) ) and a.sohoadon != 'NA'  " +
					"having "+
					" MAX(cast(SOHOADON as numeric)) != ( select  MAX(cast(SOHOADON as numeric)) as SOIN_MAX  from HOADON where  kyhieu = '" + kyhieuhoadon + "' and nguoisua= '" + userId + "' and sohoadon != 'NA' )";

				System.out.println("Câu check khoang HOADON: " + query);
			ResultSet SoMAX_HD = db.get(query);
			String soinmax= "";
			if(SoMAX_HD!= null)
			{
				while(SoMAX_HD.next())
				{
					soinmax = SoMAX_HD.getString("SOIN_MAX")== null ? "" : SoMAX_HD.getString("SOIN_MAX") ;
					chuoi = ("000000" + (SoMAX_HD.getLong("SOIN_MAX")));
					System.out.println("---SO HOA DON LAY DUOC KHI VUOT: " + chuoi );

				}
				SoMAX_HD.close();
			}
			chuoi = chuoi.substring(chuoi.length() - 7, chuoi.length());

			if(soinmax.trim().length() <= 0)
			{
					msg = "Số hóa đơn tiếp theo  đã vượt quá Số hóa đơn đến (" + sohoadonden + ")  trong dữ liệu nền. Vui lòng vào dữ liệu nền khai báo lại ! ";
				return msg;
			}
		}

		sohoadon =  chuoi;

		// KIEM TRA LAI SO HOA DON MOI TAO CO TRUNG VS SO HOA DON NAO HIEN TAI TRONG HD O & E 
		query = " select (select count(*) from HOADON where SOHOADON = '"+ sohoadon +"' and kyhieu = '"+ kyhieuhoadon +"' and trangthai != '3' and npp_fk = '" + nppId + "' and mauhoadon = 1 and sohoadon != 'NA' ) as KtraOTC, " +
				"        (select count(*) from ERP_HOADONNPP where SOHOADON = '"+ sohoadon +"' and kyhieu = '"+ kyhieuhoadon +"' and trangthai != '3' and npp_fk = '" + nppId + "' and mauhoadon = 1 and sohoadon != 'NA' ) as KtraETC " +
				" from NHANVIEN where pk_seq = '" + userId + "' ";
		ResultSet RsRs = db.get(query);
		int KT_OTC = 0;
		int KT_ETC = 0;
		if(RsRs != null)
		{
			while(RsRs.next())
			{
				KT_OTC = RsRs.getInt("KtraOTC") ;
				KT_ETC = RsRs.getInt("KtraETC") ;
			}
			RsRs.close();
		}

			if (KT_OTC > 0 || KT_ETC > 0) // CÓ HÓA ĐƠN (CỦA USER
											// KHÁC) CÓ SỐ HD TRÙNG
											// VS SỐ HÓA ĐƠN MỚI TẠO
		{
				msg = "Số hóa đơn tiếp theo đã trùng với số hóa đơn trong Hóa Đơn OTC/ETC ! ";
			return msg;
		}

	}
  }*/

	public boolean chot(String id)
	{

		Utility uilt_kho =new Utility();

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

		if( this.khId.trim().length() <= 0 )
		{
			this.msg = "Vui lòng chọn đối tác đặt hàng";
			return false;
		}

		if( this.khoNhanId.trim().length() <= 0 )
		{
			this.msg = "Vui lòng chọn kho đặt hàng";
			return false;
		}

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

			String query = "";
			query = "select dungchungkenh from NHAPHANPHOI where PK_SEQ =  (SELECT NPP_FK FROM ERP_DONDATHANGNPP WHERE PK_sEQ="+this.id+" )";
			ResultSet rs = db.get(query);
			boolean dungchungkenh=false;
			if(rs.next())
			{
				if(rs.getString("dungchungkenh").equals("1")){
					dungchungkenh=true;
				}
			}
			rs.close();


			//GIAM BOOK, TANG AVAI
			query=	"	select khoxuat_fk, npp_fk, "+(dungchungkenh?"100025":" kbh_fk")+ " as kbh_fk, sanpham_fk, sum(soluong) as soluong  " +
			"	from " +
			"	( " +
			"		select c.kho_fk as khoxuat_fk, c.npp_fk,   kbh_fk, a.sanpham_fk,       " +
			"				case when a.dvdl_fk IS null then a.soluong       " +
			"					 when a.dvdl_fk = b.DVDL_FK then a.soluong      " +
			"					 else  a.soluong * ( select SOLUONG1 / SOLUONG2 from QUYCACH where sanpham_fk = a.sanpham_fk and DVDL2_FK = a.dvdl_fk and dvdl1_fk = b.dvdl_fk )  end as soluong    " +
			"		from ERP_DONDATHANGNPP_SANPHAM a inner join SANPHAM b on a.sanpham_fk = b.PK_SEQ   " +
			"				inner join ERP_DONDATHANGNPP c on a.dondathang_fk = c.pk_seq     " +
			"		where a.dondathang_fk in (  " + this.id + "  ) and a.soluong > 0 " +
			"	) " +
			"	DATHANG  " +
			"	group by khoxuat_fk, npp_fk, "+(dungchungkenh?"":" kbh_fk,")+ "  sanpham_fk " ;

			ResultSet rskho=db.get(query);
			while(rskho.next()){
				String   _khoxuat_fk, _npp_fk, _kbh_fk, _sanpham_fk ;
				_khoxuat_fk=rskho.getString("khoxuat_fk");
				_npp_fk=rskho.getString("npp_fk");
				_kbh_fk=rskho.getString("kbh_fk");
				_sanpham_fk=rskho.getString("sanpham_fk"); 
				double soluongct_ =rskho.getDouble("soluong");
				String msg1=uilt_kho.Update_NPP_Kho_Sp(this.getDateTime(), "erpdondathangDoitacSvl 340",
						db, _khoxuat_fk, _sanpham_fk, _npp_fk, _kbh_fk, 0, soluongct_*(-1), soluongct_, 0);
				if(msg1.length() >0){
					this.msg=msg1;
					db.getConnection().rollback();
					return false;
				}

			}
			rskho.close();

			// xoa bang chi tiet


			query=	" SELECT KHOXUAT_FK, NPP_FK,   "+(dungchungkenh?"100025":" kbh_fk")+ " as  KBH_FK, SANPHAM_FK, SUM(SOLUONG) AS SOLUONG   ,SOLO, NGAYHETHAN, NGAYNHAPKHO "+      
			" FROM "+
			" ( "+
			" SELECT C.KHO_FK AS KHOXUAT_FK, C.NPP_FK,   KBH_FK, A.SANPHAM_FK, A.SOLO,A.NGAYHETHAN,A.NGAYNHAPKHO, "+     
			" CASE WHEN A.DVDL_FK IS NULL THEN A.SOLUONG       "+
			" WHEN A.DVDL_FK = B.DVDL_FK THEN A.SOLUONG      "+
			" ELSE  A.SOLUONG * ( SELECT SOLUONG1 / SOLUONG2 FROM QUYCACH "+ 
			" WHERE SANPHAM_FK = A.SANPHAM_FK AND DVDL2_FK = A.DVDL_FK AND DVDL1_FK = B.DVDL_FK )  END AS SOLUONG "+   
			" FROM ERP_DONDATHANGNPP_SANPHAM_CHITIET A INNER JOIN SANPHAM B ON A.SANPHAM_FK = B.PK_SEQ   "+
			" INNER JOIN ERP_DONDATHANGNPP C ON A.DONDATHANG_FK = C.PK_SEQ     "+
			" WHERE A.DONDATHANG_FK IN (  "+this.id+" ) AND A.SOLUONG > 0 "+
			" ) "+
			" DATHANG "+ 
			" GROUP BY KHOXUAT_FK, NPP_FK, "+(dungchungkenh?"":" kbh_fk,")+ "  SANPHAM_FK ,SOLO, NGAYHETHAN, NGAYNHAPKHO    ";

			rskho=db.get(query);
			while(rskho.next()){
				String   _khoxuat_fk, _npp_fk, _kbh_fk, _sanpham_fk,_solo,_ngayhethan,_ngaynhapkho ;
				_khoxuat_fk=rskho.getString("khoxuat_fk");
				_npp_fk=rskho.getString("npp_fk");
				_kbh_fk=rskho.getString("kbh_fk");
				_sanpham_fk=rskho.getString("sanpham_fk"); 
				_solo= rskho.getString("SOLO");
				_ngayhethan= rskho.getString("ngayhethan");
				_ngaynhapkho= rskho.getString("ngaynhapkho");

				double soluongct_ =rskho.getDouble("SOLUONG");

				String msg1=uilt_kho.Update_NPP_Kho_Sp_Chitiet(this.ngayyeucau,"Cập nhật đơn hàng đối tác :erpdondathangDoitac.java 1056" 
						, db, _khoxuat_fk, _sanpham_fk, _npp_fk, _kbh_fk, _solo, _ngayhethan, _ngaynhapkho, 0, (-1)*soluongct_, soluongct_, soluongct_, 0);
				if(msg1.length() >0){
					this.msg = msg1;
					db.getConnection().rollback();
					return false;
				}


			}
			rskho.close();


			query = "delete ERP_DONDATHANGNPP_SANPHAM_CHITIET where Dondathang_fk = '" + this.id + "'";
			if(!db.update(query))
			{
				this.msg = "Không thể cập nhật ERP_DondathangNPP_SANPHAM " + query;
				db.getConnection().rollback();
				return false;
			}		
			query = "delete ERP_DondathangNPP_SANPHAM where Dondathang_fk = '" + this.id + "'";
			if(!db.update(query))
			{
				this.msg = "Không thể cập nhật ERP_DondathangNPP_SANPHAM " + query;
				db.getConnection().rollback();
				return false;
			}


			// PHAI CẬP NHẬT KHO SAU TRONG TRƯỜNG HỢP ĐỔI KHO KHÁC
			query =		" Update ERP_DondathangNPP set ngaydonhang = '" + this.ngayyeucau + "', ngaydenghi = '" + this.ngaydenghi + "', ghichu = N'" + this.ghichu + "', " +
			" dvkd_fk = '" + this.dvkdId + "', kbh_fk = '" + this.kbhId + "', npp_fk = '" + this.nppId + "', kho_fk = " + 
			khonhan_fk + ", ngaysua = '" + getDateTime() + "', nguoisua = '" + this.userId + "', chietkhau = '" +
			chietkhau + "', vat = '" + vat + "',isKM='"+this.iskm+"',isdhkhac="+this.isdhk+",isingia="+this.isgia+" " + 
			" , ngaygiochot='" + getDateTime1() +"'  " +
			" where pk_seq = '" + this.id + "' ";

			System.out.println(" qr chot "+query);
			if(!db.update(query))
			{
				this.msg = "Không thể cập nhật ERP_DondathangNPP " + query;
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

			for(int i = 0; i < spMa.length; i++)
			{
				if(spMa[i].trim().length() > 0 && spSCheme[i].trim().length() <= 0 )
				{
					//CHECK SP NAY DA CO KHAI QUY CACH CHUA?
					query =    "select sp.ten, dv.donvi, case when sp.dvdl_fk != dv.pk_seq   " +
					"	then ISNULL( ( select soluong2 / soluong1 from QUYCACH where SANPHAM_FK = sp.PK_SEQ and DVDL1_FK = sp.DVDL_FK and DVDL2_FK = dv.pk_seq ), -1 ) else 1 end as quycach   "  +
					"from SANPHAM sp, DONVIDOLUONG dv " +
					"where sp.MA = '" + spMa[i].trim() + "' and dv.donvi = N'" + spDonvi[i].trim() + "'   ";

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


					String ck = "0";
					if(spChietkhau[i].trim().length() > 0)
						ck = spChietkhau[i].trim().replaceAll(",", "");

					String thueVAT = this.spVAT[i].trim().replaceAll(",", "");
					if(thueVAT.trim().length() < 0)
						thueVAT = "0";

					query = "insert ERP_DondathangNPP_SANPHAM( Dondathang_fk, SANPHAM_FK, soluong, dongia, chietkhau, thueVAT, dvdl_fk, dongiagoc ) " +
					"select '" + this.id + "', pk_seq, '" + spSoluong[i].replaceAll(",", "") + "', '" + spGianhap[i].replaceAll(",", "") + "', '" + ck + "', '" + thueVAT + "', ( select pk_Seq from DONVIDOLUONG where donvi = N'" + spDonvi[i].trim() + "' ), "+ spGiagoc[i].replaceAll(",", "")  +" " +
					"from SANPHAM where MA = '" + spMa[i].trim() + "' ";

					System.out.println("1.Insert NK - SP: " + query);
					if(!db.update(query))
					{
						this.msg = "Khong the tao moi ERP_Dondathang_SANPHAM: " + query;
						db.getConnection().rollback();
						return false;
					}

				}
			}

			//CHECK BOOKED THEO DV CHUAN
			query =  "select sp.dvdl_fk,dvCHUAN, khoxuat_fk as kho_fk, npp_fk, kbh_fk, sp.PK_SEQ, sp.TEN, SUM(dathang.soluong) as soluongXUAT,  " +
			"	ISNULL( ( select AVAILABLE from NHAPP_KHO where kho_fk = dathang.khoxuat_fk and sanpham_fk = sp.PK_SEQ and kbh_fk = dathang.kbh_fk and npp_fk = dathang.npp_fk ), 0) as tonkho  " +
			"from     " +
			"(     " +
			"	select c.kho_fk as khoxuat_fk, c.npp_fk, '" + kbh_fk + "' kbh_fk, a.sanpham_fk, b.DVDL_FK as dvCHUAN,     " +
			"			case when a.dvdl_fk IS null then a.soluong      " +
			"				 when a.dvdl_fk = b.DVDL_FK then a.soluong     " +
			"				 else  a.soluong * ( select SOLUONG1 from QUYCACH where sanpham_fk = a.sanpham_fk and DVDL2_FK = a.dvdl_fk and dvdl1_fk = b.dvdl_fk )      " +
			"								 / ( select SOLUONG2 from QUYCACH where sanpham_fk = a.sanpham_fk and DVDL2_FK = a.dvdl_fk and dvdl1_fk = b.dvdl_fk )	 end as soluong   " +
			"	from ERP_DONDATHANGNPP_SANPHAM a inner join SANPHAM b on a.sanpham_fk = b.PK_SEQ  " +
			"			inner join ERP_DONDATHANGNPP c on a.dondathang_fk = c.pk_seq    " +
			"	where a.dondathang_fk in ( " + this.id + " )     " +
			")     " +
			"dathang inner join SANPHAM sp on dathang.sanpham_fk = sp.PK_SEQ   " +
			"group by khoxuat_fk, npp_fk, kbh_fk, sp.PK_SEQ, sp.TEN ,sp.dvdl_fk,dvCHUAN ";





			System.out.println("--CHECK TON KHO: " + query);

			rs = db.get(query);

			while(rs.next())
			{ 
				String khoID = rs.getString("kho_fk");
				String kbhID = rs.getString("kbh_fk");
				String nppID = rs.getString("npp_fk");
				String spID = rs.getString("PK_SEQ");
				String dvCHUAN=rs.getString("dvCHUAN");
				String DVDL_FK=rs.getString("DVDL_FK");

				double soluong = rs.getDouble("soluongXUAT");
				double tonkho = rs.getDouble("tonkho");


				soluong =Double.parseDouble(formater_1sole.format(soluong));

				String msg1=uilt_kho.Update_NPP_Kho_Sp(ngayyeucau, "Tạo mới đơn hàng đối tác ErpDondathangDoitac.java 485 " 
						, db, khoID, spID, nppID, kbhID, 0, soluong, (-1)* soluong, 0);
				if(msg1.length() >0){
					msg =msg1;
					db.getConnection().rollback();
					rs.close();
					return false;
				}

				// đề xuất lô để booked ngay 

				query=		" select KHO_FK,SANPHAM_FK,KBH_FK,SOLO,NGAYHETHAN,NGAYNHAPKHO,available  from NHAPP_KHO_CHITIET "+  
				" where NPP_FK ="+nppID+" and KBH_FK= " +kbhID +
				" and KHO_FK="+khoID+"  and SANPHAM_FK =  "+ spID +
				" AND AVAILABLE >0  and NGAYNHAPKHO<='"+this.ngayyeucau+"'"+
				" order by NGAYHETHAN ,NGAYNHAPKHO,AVAILABLE ";
				ResultSet rssp=db.get(query);
				double soluongdenghi=soluong ;

				while(rssp.next() && soluongdenghi >0){
					double soluong_avai= rssp.getDouble("AVAILABLE");
					double soluongcapnhat=0;
					if(soluong_avai >soluongdenghi){
						soluongcapnhat= soluongdenghi;
						soluongdenghi=0;
					}else{
						soluongcapnhat =soluong_avai;
						soluongdenghi =soluongdenghi - soluong_avai;
					}
					String solo=rssp.getString("SOLO");
					String ngaynhapkho=rssp.getString("ngaynhapkho");
					String ngayhethan=rssp.getString("ngayhethan");
					String _khoid=rssp.getString("kho_fk");
					String _kbhid=rssp.getString("KBH_FK");
					// cập nhật vào bảng đơn hàng sp _chitiet
					double soluongcapnhat_quydoi ;
					if(dvCHUAN.equals(DVDL_FK)){
						// nếu là đơn vị giống nhau
						soluongcapnhat_quydoi= soluongcapnhat;

					}else{
						query=" SELECT qc.SOLUONG1,qc.SOLUONG2 FROM QUYCACH qc WHERE SANPHAM_FK="+spID+" AND DVDL1_FK="+DVDL_FK+"  and qc.DVDL2_FK="+dvCHUAN;
						ResultSet rs1=db.get(query);
						if(rs1.next()){
							soluongcapnhat_quydoi = soluongcapnhat * rs1.getDouble("SOLUONG1")/ rs1.getDouble("SOLUONG2");

						}else{
							this.msg="Không thể xác định quy đổi của sản phẩm : "+rs.getString("ten");;
							db.getConnection().rollback();
							return false;
						}
					}





					query = " Insert into ERP_DONDATHANGNPP_SANPHAM_CHITIET(donDAThang_fk ,   sanpham_fk,solo,ngaynhapkho,ngayhethan, soluong,dvdl_fk) "
						+ " values('" + id + "', '" +spID + "','" +solo+ "','"+ngaynhapkho+"','"+ngayhethan+"',"+soluongcapnhat_quydoi+" ,"+dvCHUAN+" )";

					if (!db.update(query)) 
					{
						this.msg="Không thể cập nhật : "+query;
						db.getConnection().rollback();
						rs.close();
						return false;
					}

					soluongcapnhat =Double.parseDouble(formater_1sole.format(soluongcapnhat));

					msg1=uilt_kho.Update_NPP_Kho_Sp_Chitiet(this.ngayyeucau, "8989 ErpDondathangDoiTac.java :566  DHID: "+this.id,  db, _khoid, spID, nppId, _kbhid, solo, ngayhethan, ngaynhapkho, 0,soluongcapnhat,(-1)* soluongcapnhat, (-1)* soluongcapnhat, 0);
					if (msg1.length()> 0) 
					{
						this.msg=msg1;
						db.getConnection().rollback();
						rs.close();
						return false;
					}
				}
				rssp.close();

				if(soluongdenghi!=0){

					this.msg=  "Số lượng đề xuất trong lô chi tiết theo ngày không còn đủ, vui lòng kiểm tra xuất nhập tồn theo lô để biết thêm chi tiết ";
					db.getConnection().rollback();
					rs.close();
					return false;

				}


			}
			rs.close();

			query = "update ERP_DondathangNPP set trangthai = '1', NPP_DACHOT = '1' where pk_seq = '" + id + "'";
			if(!db.update(query))
			{
				msg = "Khong the chot: " + query;
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


	String iskm;
	public String getIsKm()
	{
		return iskm;
	}

	public void setIsKm(String iskm)
	{
		this.iskm = iskm;
	}
	public String[] getSptonkhocn() {
		return sptonkhocn;
	}

	public void setSptonkhocn(String[] sptonkhocn) {
		this.sptonkhocn = sptonkhocn;
	}
	public ResultSet getKhuyenMaiRs()
	{
		if( this.id != null && this.id.length() > 0)
		{
			String query =  "\n select isnull(b.MA, ' ') as MA, isnull(b.TEN, ' ') as TEN, isnull(c.DONVI, ' ') as donvi, d.SCHEME, isnull(a.soluong, 0) as soluong, a.tonggiatri " +
							"\n from ERP_DONDATHANGNPP_CTKM_TRAKM a inner join SANPHAM b on a.SPMA = b.MA  " +
							"\n left join DONVIDOLUONG c on b.DVDL_FK = c.PK_SEQ  " +
							"\n inner join CTKHUYENMAI d on a.ctkmID = d.PK_SEQ " +
							"\n where a.dondathangID = '" + this.id + "'  " +
							"\n union all " +
							"\n select N'', N'Trả tiền' TEN, '' as donvi, d.SCHEME, 1 , a.tonggiatri " +
							"\n from ERP_DONDATHANGNPP_CTKM_TRAKM a  " +
							"\n inner join CTKHUYENMAI d on a.ctkmID = d.PK_SEQ " +
							"\n where a.sanpham_fk is null and  a.dondathangID = '" + this.id + "'  " ;
			System.out.println("--getKMRS: " + query);
			return db.get(query);
		}
		return null;
	}
	String data = "";
	String  danh_sach_ctkm_Str= "";
	String dieuchinh = "0";
	public void setData(String data) {
		this.data = data;
	}
	public String getData() {
		return data;
	}
	
	public String getDanh_sach_ctkm_Str() {
		return danh_sach_ctkm_Str;
	}
	public void setDanh_sach_ctkm_Str(String danh_sach_ctkm_Str) {
		this.danh_sach_ctkm_Str = danh_sach_ctkm_Str;
	}
	public String getDieuchinh() {
		return dieuchinh;
	}
	public void setDieuchinh(String dieuchinh) {
		this.dieuchinh = dieuchinh;
	}
	
	public static String Ap_Khuyen_Mai_SellIn_Dac_Biet(Idbutils db ,String dhId ,String kbh_fk)
	{
		try
		{
			
			
			String query =  "\n insert erp_dondathangnpp_ctkm_trakm(KBH_FK,DONDATHANGID,CTKMID,TRAKMID,SOXUAT,TONGGIATRI,SPMA,SOLUONG,CHIETKHAU,sanpham_fk,kho_fk) " +
							"\n select "+kbh_fk+",dh.PK_SEQ,ctkm.PK_SEQ ctkmId, tkm.PK_SEQ tkmId, round((tt.tongtien * ctkm.pt_ChietKhau/100.0)/bg.giamua  ,0) " +
							"\n			,0 ,sp.ma, round((tt.tongtien * ctkm.pt_ChietKhau/100.0)/bg.giamua  ,0), ctkm.pt_ChietKhau, ct.sanpham_fk, ctkm.KHO_FK " +
							"\n from  ERP_DONDATHANGNPP dh " +
							"\n inner join CTKHUYENMAI ctkm on  ctkm.TUNGAY <=dh.NgayDonHang and ctkm.DENNGAY >=dh.NgayDonHang and loaict = 9 " +
							"\n inner join CTKM_NPP_SANPHAM ct on ct.CTKM_FK = ctkm.PK_SEQ and ct.NPP_FK =dh.NPP_DAT_FK" +
							"\n	inner join sanpham sp on sp.pk_seq = ct.sanpham_fk " +
							"\n outer apply " +
							"\n ( " +
							"\n 	select  sum(round (soluong * dongia * ( 1 + thuevat/100.0),0))tongtien from ERP_DONDATHANGNPP_SANPHAM where  dondathang_fk =dh.pk_seq " +
							"\n )tt " +
							"\n outer apply " +
							"\n ( " +
							"\n 	select dbo.[GiaDoitacSanpham](100001,dh.KBH_FK,dh.NPP_DAt_FK,ct.sanpham_fk,0,dh.ngaydonhang) giamua " +
							"\n )bg " +
							"\n cross apply " +
							"\n ( " +
							"\n 	select top 1 pk_seq from TRAKHUYENMAI where istichluy = 1 and LOAI =3 and HINHTHUC = 1 " +
							"\n )tkm " +
							"\n  where isnull(ctkm.trangthai,0) = 1 and dh.isdhkhac = 0 and dh.iskm = 0 and round((tt.tongtien * ctkm.pt_ChietKhau/100.0)/bg.giamua  ,0) > 0 and  dh.PK_SEQ ="+dhId+"  and bg.giamua > 0 ";
			System.out.println("query = " + query);
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
							query = "\n insert erp_dondathangnpp_ctkm_trakm(dondathangId,ctkmId,TRAKMID,SOXUAT,TONGGIATRI) " +
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
									query = "\n insert erp_dondathangnpp_ctkm_trakm(kbh_fk,dondathangId,ctkmId,TRAKMID,SOXUAT,TONGGIATRI,SPMA,SOLUONG,sanpham_fk,kho_fk) " +
											"\n select "+kbh_fk+", "+dhId+", ctkm.pk_seq, "+tkmId+", "+sosuat+",0, sp.ma, "+sl+", sp.pk_seq, ctkm.kho_fk" +
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
										query = "\n insert erp_dondathangnpp_ctkm_trakm(kbh_fk,dondathangId,ctkmId,TRAKMID,SOXUAT,TONGGIATRI,SPMA,SOLUONG,sanpham_fk,kho_fk) " +
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
	
	public static String update_giatri_erp_dondathangnpp(Idbutils db ,String id)
	{
		String query = 
						"\n update dh set SOTIENTHU = isnull(sp.tiensp,0) -  isnull(km.tiensp,0) " +
			"\n from ERP_DONDATHANGNPP dh " +
			"\n outer apply " +
			"\n ( " +
			"\n 	select sum(  round(soluong * dongia * ( 1 + thueVAT/100.0 ),0) ) tiensp from ERP_DONDATHANGNPP_SANPHAM sp where sp.dondathang_fk  = dh.PK_SEQ " +
			"\n )sp " +
			"\n outer apply " +
			"\n ( " +
			"\n 	select sum( tonggiatri ) tiensp from ERP_DONDATHANGNPP_CTKM_TRAKM sp where sp.dondathangId  = dh.PK_SEQ and sp.sanpham_fk is  null " +
			"\n )km " +
			"\n where dh.PK_SEQ = "+id+" and  isnull(sp.tiensp,0) -  isnull(km.tiensp,0) >= 0";
		int sd = db.updateReturnInt(query);
		if(sd == 0)
			return " Giá trị đơn hàng < 0 ";
		if(sd == 1) return "";
		
		return "Lỗi cập nhật đơn hàng";
		
	}
	
}
