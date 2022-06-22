package geso.dms.distributor.beans.hopdong.imp;

import geso.dms.distributor.db.sql.dbutils;
import geso.dms.center.util.Utility;
import geso.dms.distributor.beans.hopdong.IErpDonhangNppETC;

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

public class ErpDonhangNppETC implements IErpDonhangNppETC
{
	String userId;
	String id;
	
	String ma;
	String tungay;
	String denngay;
	String ghichu;

	String msg;
	String trangthai;
	
	String loaidonhang; 
	String chietkhau;
	String vat;
	
	String khoNhanId;
	ResultSet khoNhanRs;
	
	String dungchungKENH;
	String khId;
	ResultSet khRs;
	
	String dvkdId;
	ResultSet dvkdRs;
	
	String kbhId;
	ResultSet kbhRs;
	
	String gsbhId;
	ResultSet gsbhRs;
	
	String ddkdId;
	ResultSet ddkdRs;
	
	ResultSet dvtRs;

	String[] spId;
	String[] spMa;
	String[] spStt;
	String[] spTen;
	String[] spDonvi;
	String[] spSoluong;
	String[] spDonviChuan;
	String[] spSoluongChuan;
	String[] spGianhap;
	String[] spChietkhau;
	String[] spVAT;
	String[] spTungay;
	String[] spDenngay;
	String[] spTrongluong;
	String[] spThetich;
	String[] spQuyDoi;
	String[] spSoluongton;
	public String[] getSpSoluongton()
	{
		return spSoluongton;
	}

	public void setSpSoluongton(String[] spSoluongton)
	{
		this.spSoluongton = spSoluongton;
	}

	Hashtable<String, String> sanpham_soluong;
	
	String[] dhCk_diengiai;
	String[] dhCk_giatri;
	String[] dhCk_loai;
	
	String nppId;
	String nppTen;
	String sitecode;
	
	dbutils db;
	Utility util;
	
	public ErpDonhangNppETC()
	{
		this.id = "";
		this.ma = "";
		this.tungay = "";
		this.denngay = "";
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
		this.ddkdId = "";
		this.gsbhId = "";
		this.dungchungKENH = "0";
		
		this.dhCk_diengiai = new String[]{"", "", "", ""};
		this.dhCk_giatri = new String[]{"0", "0", "0", "0"};
		this.dhCk_loai = new String[]{"0", "0", "0", "0"};
		
		this.sanpham_soluong = new Hashtable<String, String>();
		
		this.db = new dbutils();
		this.util = new Utility();
	}
	
	public ErpDonhangNppETC(String id)
	{
		this.id = id;
		this.ma = "";
		this.tungay = "";
		this.denngay = "";
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
		this.ddkdId = "";
		this.gsbhId = "";
		this.dungchungKENH = "0";

		this.dhCk_diengiai = new String[]{"", "", "", ""};
		this.dhCk_giatri = new String[]{"0", "0", "0", "0"};
		this.dhCk_loai = new String[]{"0", "0", "0", "0"};
		
		this.sanpham_soluong = new Hashtable<String, String>();
		
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

	
	public boolean createNK(HttpServletRequest request) 
	{
		NumberFormat formater2 = new DecimalFormat("##,###,###.##");
		System.out.println("vao day ne");
		if(this.tungay.trim().length() < 10)
		{
			this.msg = "Vui lòng nhập ngày bắt đầu hợp đồng";
			return false;
		}
		
		if(this.denngay.trim().length() < 10)
		{
			this.msg = "Vui lòng nhập ngày kết thúc hợp đồng";
			return false;
		}

		if( this.gsbhId.trim().length() <= 0 )
		{
			this.msg = "Vui lòng chọn phụ trách tỉnh / GĐCN";
			return false;
		}
		
		if( this.ddkdId.trim().length() <= 0 )
		{
			this.msg = "Vui lòng chọn NHÂN VIÊN BÁN HÀNG";
			return false;
		}
		
		if( this.khId.trim().length() <= 0 )
		{
			this.msg = "Vui lòng chọn khách hàng ETC";
			return false;
		}
		
		if( this.khoNhanId.trim().length() <= 0 )
		{
			this.msg = "Vui lòng chọn kho đặt hàng";
			return false;
		}
		
		if(spMa == null)
		{
			this.msg = "Vui lòng kiểm tra lại danh sách sản phẩm hợp đồng";
			return false;
		}
		else
		{
			boolean coSP = false;
			int flag=0;
			for(int i = 0; i < spMa.length; i++)
			{
				if( spMa[i].trim().length() > 0 )
				{
					if(spSoluong[i].trim().length() <= 0 )
					{
						this.msg = "Bạn phải nhập số lượng của sản phẩm ( " + spTen[i] + " )";
						return false;
					}
					
					if(spGianhap[i].trim().length() <= 0 || spGianhap[i].trim().equals("0") )
					{
						this.msg = "Bạn phải nhập đơn giá của sản phẩm ( " + spTen[i] + " )";
						return false;
					}
					
					if(spDonvi[i].trim().length() <= 0)
					{
						this.msg = "Bạn phải nhập đơn vị  của sản phẩm ( " + spTen[i] + " )";
						return false;
					}
					if(!spSoluong[i].trim().equals("0"))
						flag=1;
					
					coSP = true;
				}
			}
			if(flag==0)
			{
				this.msg = "Vui lòng nhập số lượng sản phẩm";
				return false;
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
		
		 Utility utility2=new Utility();
		 String ngayhoadon_=utility2.getngayhoadon(this.userId, db,this.tungay,this.khId,1);
		 //System.out.println("ngay hoa don la 111----------------- "+ngayhoadon_);
		 
		try
		{
			db.getConnection().setAutoCommit(false);
			
			String khonhan_fk = this.khoNhanId.trim().length() <= 0 ? "null" : this.khoNhanId.trim();
			String chietkhau = this.chietkhau.trim().length() <= 0 ? "0" : this.chietkhau.trim().replaceAll(",", "");
			String vat = this.vat.trim().length() <= 0 ? "0" : this.vat.trim().replaceAll(",", "");
			
			// NẾU ĐƠN ĐẶT HÀNG TỪ HỢP ĐỒNG QUA >> HOPDONG_FK CO GIÁ TRỊ
			//                     NGƯỢC LẠI >> -1
			
			if(this.ma.trim().length() <= 0) this.ma = "-1" ;
			
			String query = " insert ERP_DondathangNPP(ngaydonhang, ngaydenghi, loaidonhang, npp_dachot, ghichu, trangthai, dvkd_fk, kbh_fk, gsbh_fk, ddkd_fk, khachhang_fk, npp_fk, kho_fk, chietkhau, vat, hopdong_fk, ngaytao, nguoitao, ngaysua, nguoisua) " +
						   " values ( '" + this.tungay + "', '" + this.denngay + "', 0, 1, N'" + this.ghichu + "', 99, '" + this.dvkdId + "', '" + this.kbhId + "', '" + this.gsbhId + "', '" + this.ddkdId + "', '" + this.khId + "', '" + this.nppId + "', " + khonhan_fk + ", " + chietkhau + ", " + vat + ", '"+ this.ma +"', '" + this.getDateTime() + "', '" + this.userId + "', '" + this.getDateTime() + "', '" + this.userId + "' ) ";
			//System.out.println("-- INSERT DDH: " + query );
			if(!db.update(query))
			{
				msg = "Lỗi khi tạo mới đơn hàng: " + query;
				db.getConnection().rollback();
				return false;
			}
			
			//LAY ID
			ResultSet rsDDH = db.get("select scope_identity() as ID ");
			if(rsDDH.next())
			{
				this.id = rsDDH.getString("ID");
			}
			rsDDH.close();
			
			System.out.println("DDH ID: " + this.id);
			
			String quanlykho = "";
			String kbh_fk = this.kbhId;
			query = "select dungchungkenh, quanlykho from NHAPHANPHOI where PK_SEQ = '" + this.nppId + "' ";
			System.out.println(query);
			ResultSet rs = db.get(query);
			if(rs.next())
			{
				if(rs.getString("dungchungkenh").equals("1")) { kbh_fk = "100025"; }
				quanlykho = rs.getString("quanlykho");
			}
			
			for(int i = 0; i < spMa.length; i++)
			{
				if(spMa[i].trim().length() > 0 && spSoluong[i].trim().length() > 0 && spGianhap[i].trim().length() > 0  )
				{
					query = 
						" select sp.ten, dv.donvi, case when sp.dvdl_fk != dv.pk_seq   " +
					    " then ISNULL( ( select soluong2 / soluong1 from QUYCACH where SANPHAM_FK = sp.PK_SEQ and DVDL1_FK = sp.DVDL_FK and DVDL2_FK = dv.pk_seq ), -1 ) else 1 end as quycach   "  +
					    " from SANPHAM sp, DONVIDOLUONG dv " +
					   	" where sp.MA = '" + spMa[i].trim() + "' and dv.donvi = N'" + spDonvi[i].trim() + "'   ";
						
						//System.out.println("-----CHECK QUY CACH: " + query );
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
					
					String thueVAT = spVAT[i].trim().replaceAll(",", "");
					if(thueVAT.trim().length() < 0)
						thueVAT = "0";
					
					boolean ktra = true;
					// Kiểm tra nếu đơn hàng đi từ Hợp đồng (Bình thường) qua >> Số lượng không được vượt quá SL còn lại trong Hợp đồng 
					// Lấy ra SP
					if(this.ma.trim().length() >= 6 && spMa[i].trim().length() > 0)
					{
						//QUY DOI SO LUONG - > SO LUONG DO LUONG CHUAN      
						 query = " select case when sp.DVDL_FK=dv.PK_SEQ then "+spSoluong[i].replaceAll(",", "") +"     \n" +
					        " else  "+spSoluong[i].replaceAll(",", "") +" * ( select SOLUONG1 from QUYCACH where sanpham_fk = sp.PK_SEQ and DVDL2_FK = dv.PK_SEQ and sp.DVDL_FK=QUYCACH.DVDL1_FK  ) \n" +
					        " / ( select SOLUONG2 from QUYCACH where  sanpham_fk = sp.PK_SEQ and DVDL2_FK = dv.PK_SEQ and sp.DVDL_FK=QUYCACH.DVDL1_FK )  end as soluong " +
					        " from SANPHAM sp, DONVIDOLUONG dv " +
					        " where sp.MA = '" + spMa[i].trim() + "' and dv.donvi = N'" + spDonvi[i].trim() + "' ";
					     
					      System.out.println("Cau lấy SL Do luong chuan "+query);
					      ResultSet LaySL_DLC = db.get(query);
					      int sl_DLC = 0;    
					      int k=0;
					    
					      while (LaySL_DLC.next())
					      {
					    	  sl_DLC = LaySL_DLC.getInt("soluong"); 
					    	  k++;
					      }
					      LaySL_DLC.close();
					      
					      if(k > 1)
					      {
					    	  this.msg = "Lỗi trong quá trình quy đổi, vui lòng kiểm tra lại quy đổi hoặc báo với Admin để được xử lý "+query;
					    	  db.getConnection().rollback();
					    	  return false;
					      }
						
					      query = "\n select (select loaidonhang from ERP_HOPDONGNPP where PK_SEQ = "+this.ma+") as loaidh, hd.soluong - isnull(dh.daDAT, 0) as SL  " +
								"\n from " +
								"\n ( " +
								"\n		select sanpham_fk, dvdl_fk, sum(soluong) as soluong, avg(dongia) as dongia, avg(chietkhau) as chietkhau, avg(thuevat) as thuevat, tungay, denngay " +
								"\n		from " +
								"\n		( " +
								"\n			select sanpham_fk,  " +
								"\n			case when a.dvdl_fk IS null then a.soluong       " +
								"\n			when a.dvdl_fk = b.DVDL_FK then a.soluong      " +
								"\n			else  a.soluong * ( select SOLUONG1 from QUYCACH where sanpham_fk = a.sanpham_fk and DVDL2_FK = a.dvdl_fk )       " +
								"\n			/ ( select SOLUONG2 from QUYCACH where sanpham_fk = a.sanpham_fk and DVDL2_FK = a.dvdl_fk )	 end as soluong, dongia, chietkhau, thueVAT, b.pk_seq as dvdl_fk, tungay, denngay  " +
								"\n			from ERP_HOPDONGNPP_SANPHAM a inner join SANPHAM b on a.sanpham_fk = b.pk_seq  " +
								"\n			where HOPDONG_FK = '" + this.ma + "'  " +
								"\n			union ALL " +
								"\n			select sanpham_fk,  " +
								"\n			case when a.dvdl_fk IS null then a.soluong       " +
								"\n			when a.dvdl_fk = b.DVDL_FK then a.soluong      " +
								"\n			else  a.soluong * ( select SOLUONG1 from QUYCACH where sanpham_fk = a.sanpham_fk and DVDL2_FK = a.dvdl_fk )       " +
								"\n			/ ( select SOLUONG2 from QUYCACH where sanpham_fk = a.sanpham_fk and DVDL2_FK = a.dvdl_fk )	 end as soluong, dongia, chietkhau, thueVAT, b.pk_seq as dvdl_fk, tungay, denngay  " +
								"\n			from ERP_HOPDONGNPP_SANPHAM a inner join SANPHAM b on a.sanpham_fk = b.pk_seq   " +
								"\n			where HOPDONG_FK in ( select pk_seq from ERP_HOPDONGNPP where hopdong_fk = '" + this.ma + "' and trangthai in (1, 2) ) " +
								"\n		) hopdong "+
								"\n 	group by sanpham_fk, dvdl_fk, tungay, denngay " +
								"\n ) hd "+
								"\n left join " +
								"\n ( " +
								"\n		select sanpham_fk, sum(soluong) as daDAT " +
								"\n		from " +
								"\n		( " +
								"\n			select a.sanpham_fk, b.DVDL_FK as dvCHUAN,      " +
								"\n			case when a.dvdl_fk IS null then a.soluong       " +
								"\n			when a.dvdl_fk = b.DVDL_FK then a.soluong      " +
								"\n			else  a.soluong * ( select SOLUONG1 from QUYCACH where sanpham_fk = a.sanpham_fk and DVDL2_FK = a.dvdl_fk )       " +
								"\n			/ ( select SOLUONG2 from QUYCACH where sanpham_fk = a.sanpham_fk and DVDL2_FK = a.dvdl_fk ) end as soluong  " +
								"\n			from ERP_DONDATHANGNPP_SANPHAM a inner join SANPHAM b on a.sanpham_fk = b.PK_SEQ      " +
								"\n			where a.dondathang_fk in (   select pk_seq from ERP_DondathangNPP where trangthai != '3' and hopdong_fk = '" + this.ma + "'  )     " +
								"\n		) dathang group by sanpham_fk " +
								"\n ) dh on hd.sanpham_fk = dh.sanpham_fk " +
								"\n	where hd.sanpham_fk =  (select pk_seq from SANPHAM where MA = '" + spMa[i].trim() + "' ) ";  
						System.out.println("check sl con lai : "+query);
						ResultSet LaySL_Conlai = db.get(query);
						
						int sl_conlai = 0;
						String loaidh = "";
						
						if(LaySL_Conlai!= null)
						{
							while (LaySL_Conlai.next())
							{
								sl_conlai = LaySL_Conlai.getInt("SL");	
								loaidh = LaySL_Conlai.getString("loaidh");
							}
							LaySL_Conlai.close();
							
						}
						
						if( (loaidh.equals("0") || loaidh.equals("3") )  && sl_DLC > sl_conlai )
						{
							ktra = false;
						}
					}
					
					if(ktra)
					{
						query = " insert ERP_DONDATHANGNPP_SANPHAM( dondathang_fk, SANPHAM_FK, sanphamTEN, soluong, dongia, chietkhau, thueVAT, dvdl_fk, tungay, denngay, dongiaGOC, stt ) " +
								" select '" + this.id + "', pk_seq, N'" + spTen[i] + "', '" + spSoluong[i].replaceAll(",", "") + "', '" + spGianhap[i].replaceAll(",", "") + "', '" + ck + "', '" + thueVAT + "', ISNULL( ( select pk_Seq from DONVIDOLUONG where donvi = N'" + spDonvi[i].trim() + "' ), DVDL_FK ), '" + spTungay[i].trim() + "', '" + spDenngay[i].trim() + "',  " +
								" isnull( ( select GIAMUANPP * ( 1 - isnull( ( select chietkhau from BANGGIAMUANPP_NPP where banggiamuaNPP_FK = bg_sp.bgmuaNPP_FK and NPP_FK = '" + this.nppId + "' ), 0) / 100 ) " +
								" from BGMUANPP_SANPHAM bg_sp " +
								" where SANPHAM_FK = a.pk_seq  " +
								" and BGMUANPP_FK in ( select top(1) PK_SEQ from BANGGIAMUANPP bg inner join BANGGIAMUANPP_NPP bg_npp on bg.PK_SEQ = bg_npp.BANGGIAMUANPP_FK where bg.TRANGTHAI = '1' and bg_npp.NPP_FK = '" + this.nppId + "' and bg.DVKD_FK = '" + this.dvkdId + "'  order by bg.TUNGAY desc ) ), 0) as giamua, "+spStt[i]+" " +
								" from SANPHAM a where MA = '" + spMa[i].trim() + "' ";
						
						//System.out.println("1.Insert HD - SP: " + query);
						if(!db.update(query))
						{
							this.msg = "Khong the tao moi ERP_DONDATHANGNPP_SANPHAM: " + query;
							db.getConnection().rollback();
							return false;
						}
					}
					else
					{
						this.msg = "Số lượng sản phẩm "+ spMa[i].trim() + " đã vượt quá số lượng còn lại trong Hợp đồng "+ this.ma + ".Vui lòng kiểm tra lại." ;
						db.getConnection().rollback();
						return false;
					}
				}
			}
			
			String kqVat = geso.dms.center.util.Utility.CheckVat_Loai2( db , "ERP_DONDATHANGNPP_SANPHAM","thueVAT", "dondathang_fk", this.id,"SoLuong" );
			if(kqVat.trim().length() > 0)
			{
				this.msg = kqVat;
				db.getConnection().rollback();
				db.getConnection().setAutoCommit(true);
				return false;
			}

			//check xem co quy doi vê chuan dc ko 
			query = " select dathang.soluongdh,sp.dvdl_fk,dvCHUAN, khoxuat_fk as kho_fk, npp_fk, kbh_fk, sp.PK_SEQ, sp.TEN, SUM(dathang.soluong) as soluongXUAT,  " +
					" ISNULL( ( select AVAILABLE from NHAPP_KHO where kho_fk = dathang.khoxuat_fk and sanpham_fk = sp.PK_SEQ and kbh_fk = dathang.kbh_fk and npp_fk = dathang.npp_fk ), 0) as tonkho  " +
					" from " +
					" ( " +
					"	select a.soluong as soluongdh, c.kho_fk as khoxuat_fk, c.npp_fk, '" + kbh_fk + "' kbh_fk, a.sanpham_fk, a.DVDL_FK as dvCHUAN,     " +
					"	case when a.dvdl_fk IS null then a.soluong      " +
					"	when a.dvdl_fk = b.DVDL_FK then a.soluong     " +
					"	else  a.soluong * ( select SOLUONG1 from QUYCACH where sanpham_fk = a.sanpham_fk and DVDL2_FK = a.dvdl_fk and dvdl1_fk = b.dvdl_fk )      " +
					"	/ ( select SOLUONG2 from QUYCACH where sanpham_fk = a.sanpham_fk and DVDL2_FK = a.dvdl_fk and dvdl1_fk = b.dvdl_fk )	 end as soluong   " +
					"	from ERP_DONDATHANGNPP_SANPHAM a inner join SANPHAM b on a.sanpham_fk = b.PK_SEQ  " +
					"	inner join ERP_DONDATHANGNPP c on a.dondathang_fk = c.pk_seq    " +
					"	where a.dondathang_fk in ( " + this.id + " )  and a.soluong > 0   " +
					") dathang "+
					"inner join SANPHAM sp on dathang.sanpham_fk = sp.PK_SEQ " +
					"group by khoxuat_fk, npp_fk, kbh_fk, sp.PK_SEQ, sp.TEN ,sp.dvdl_fk,dvCHUAN,dathang.soluongdh ";
					System.out.println("--CHECK TON KHO: " + query);
					rs = db.get(query);
					while(rs.next())
					{
						String khoID = rs.getString("kho_fk");
						String kbhID = rs.getString("kbh_fk");
						String nppID = rs.getString("npp_fk");
						String spID = rs.getString("PK_SEQ");
						String spten= rs.getString("ten");
						String  dvdl_fk =rs.getString("dvdl_fk"); 
						String  dvCHUAN =rs.getString("dvCHUAN"); 

						double soluong = rs.getDouble("soluongXUAT");
						double tonkho = rs.getDouble("tonkho");

						if(soluong - (int) soluong > 0)
						{
							msg = "Sản phẩm ( " + rs.getString("TEN") + " ) với số lượng yêu cầu ( " + rs.getString("soluongXUAT") + " ) quy đổi về đơn vị chuẩn bị Lẻ vui lòng điều chỉnh lại ";
							db.getConnection().rollback();
							rs.close();
							return false;
						}
					}
			
			String LINKSERVER_DB = request.getServletContext().getInitParameter("LINKSERVER_DB");
			if(quanlykho.equals("0"))
			{
				//CHECK BOOKED THEO DV CHUAN
				query = " IF OBJECT_ID('tempdb.dbo.#kho') IS NOT NULL DROP TABLE #kho select * into #kho from "+
					 	" ( "+
						" 	 SELECT SP_E.MA SPMA, NPP_E.MA NPPMA, SUM(AVAILABLE) AVAILABLE "+
						" 	 FROM "+ LINKSERVER_DB +".[dbo].[ERP_KHOTT_SP_CHITIET] K   "+
						" 	 INNER JOIN "+ LINKSERVER_DB +".[dbo].NHAPHANPHOI NPP_E ON NPP_E.PK_SEQ = K.NPP_FK   "+
						" 	 INNER JOIN "+ LINKSERVER_DB +".[dbo].ERP_SANPHAM SP_E ON SP_E.PK_SEQ = K.SANPHAM_FK    "+
						" 	 WHERE K.NGAYNHAPKHO <= '"+ this.tungay +"' AND NPP_E.MA = ( SELECT DISTINCT MA FROM NHAPHANPHOI WHERE PK_SEQ = ( select NPP_FK from ERP_DONDATHANGNPP where  PK_SEQ = '"+ this.id +"' )) "+
						" 	 GROUP BY SP_E.MA, NPP_E.MA "+
						" ) AS K "+
				
						" select dathang.soluongdh,sp.dvdl_fk,dvCHUAN, khoxuat_fk as kho_fk, npp_fk, kbh_fk, sp.PK_SEQ, sp.TEN, SUM(dathang.soluong) as soluongXUAT,  " +
						//" ISNULL( ( select AVAILABLE from NHAPP_KHO where kho_fk = dathang.khoxuat_fk and sanpham_fk = sp.PK_SEQ and kbh_fk = dathang.kbh_fk and npp_fk = dathang.npp_fk ), 0) as tonkho  " +
						" K.AVAILABLE as tonkho  " +
						" from " +
						" ( " +
						"	select a.soluong as soluongdh, c.kho_fk as khoxuat_fk, c.npp_fk, '" + kbh_fk + "' kbh_fk, a.sanpham_fk, a.DVDL_FK as dvCHUAN,     " +
						"	case when a.dvdl_fk IS null then a.soluong      " +
						"	when a.dvdl_fk = b.DVDL_FK then a.soluong     " +
						"	else  a.soluong * ( select SOLUONG1 from QUYCACH where sanpham_fk = a.sanpham_fk and DVDL2_FK = a.dvdl_fk and dvdl1_fk = b.dvdl_fk )      " +
						"	/ ( select SOLUONG2 from QUYCACH where sanpham_fk = a.sanpham_fk and DVDL2_FK = a.dvdl_fk and dvdl1_fk = b.dvdl_fk )	 end as soluong   " +
						"	from ERP_DONDATHANGNPP_SANPHAM a inner join SANPHAM b on a.sanpham_fk = b.PK_SEQ  " +
						"	inner join ERP_DONDATHANGNPP c on a.dondathang_fk = c.pk_seq    " +
						"	where a.dondathang_fk in ( " + this.id + " )  and a.soluong > 0   " +
						" ) dathang "+
						" inner join SANPHAM sp on dathang.sanpham_fk = sp.PK_SEQ   " +
						" OUTER APPLY ( SELECT * FROM #KHO WHERE SPMA = sp.MA ) AS K "+
						" group by khoxuat_fk, npp_fk, kbh_fk, sp.PK_SEQ, sp.TEN ,sp.dvdl_fk,dvCHUAN,dathang.soluongdh, k.available ";
			}
			else
			{
				//CHECK BOOKED THEO DV CHUAN
				query = " select dathang.soluongdh,sp.dvdl_fk,dvCHUAN, khoxuat_fk as kho_fk, npp_fk, kbh_fk, sp.PK_SEQ, sp.TEN, SUM(dathang.soluong) as soluongXUAT,  " +
						" ISNULL( ( select AVAILABLE from NHAPP_KHO where kho_fk = dathang.khoxuat_fk and sanpham_fk = sp.PK_SEQ and kbh_fk = dathang.kbh_fk and npp_fk = dathang.npp_fk ), 0) as tonkho  " +
						" from     " +
						" (     " +
						"	select a.soluong as soluongdh, c.kho_fk as khoxuat_fk, c.npp_fk, '" + kbh_fk + "' kbh_fk, a.sanpham_fk, a.DVDL_FK as dvCHUAN,     " +
						"	case when a.dvdl_fk IS null then a.soluong      " +
						"	when a.dvdl_fk = b.DVDL_FK then a.soluong     " +
						"	else  a.soluong * ( select SOLUONG1 from QUYCACH where sanpham_fk = a.sanpham_fk and DVDL2_FK = a.dvdl_fk and dvdl1_fk = b.dvdl_fk )      " +
						"	/ ( select SOLUONG2 from QUYCACH where sanpham_fk = a.sanpham_fk and DVDL2_FK = a.dvdl_fk and dvdl1_fk = b.dvdl_fk )	 end as soluong   " +
						"	from ERP_DONDATHANGNPP_SANPHAM a inner join SANPHAM b on a.sanpham_fk = b.PK_SEQ  " +
						"	inner join ERP_DONDATHANGNPP c on a.dondathang_fk = c.pk_seq    " +
						"	where a.dondathang_fk in ( " + this.id + " )  and a.soluong > 0   " +
						" ) dathang inner join SANPHAM sp on dathang.sanpham_fk = sp.PK_SEQ   " +
						" group by khoxuat_fk, npp_fk, kbh_fk, sp.PK_SEQ, sp.TEN ,sp.dvdl_fk,dvCHUAN,dathang.soluongdh ";
			}
			
			System.out.println("--CHECK TON KHO: " + query);
			
			rs = db.get(query);
		
			while(rs.next())
			{
				String khoID = rs.getString("kho_fk");
				String kbhID = rs.getString("kbh_fk");
				String nppID = rs.getString("npp_fk");
				String spID = rs.getString("PK_SEQ");
				String spten= rs.getString("ten");
				String  dvdl_fk =rs.getString("dvdl_fk"); 
				String  dvCHUAN =rs.getString("dvCHUAN"); 
				
				double soluong = rs.getDouble("soluongXUAT");
				double tonkho = rs.getDouble("tonkho");
				
				if(soluong > tonkho)
				{
					msg = "Sản phẩm ( " + rs.getString("TEN") + " ) với số lượng yêu cầu ( " + formater2.format(rs.getDouble("soluongXUAT")) + " ) không đủ tồn kho ( " + formater2.format(rs.getDouble("tonkho")) + " ). Vui lòng kiểm tra lại.";
					db.getConnection().rollback();
					rs.close();
					return false;
				}
				
				 query="select isnull(checkkho,0) as sl from ERP_DondathangNPP where pk_Seq="+this.id;
				 ResultSet rschk=db.get(query);
				 rschk.next();
				 
				 System.out.println("da vao day rui------- "+ rschk.getInt("sl"));
				 if(rschk.getInt("sl")==1)
				 {
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
						if(dvCHUAN.equals(dvdl_fk)){
							// nếu là đơn vị giống nhau
							soluongcapnhat_quydoi= soluongcapnhat;
							
						}else{
							query=" SELECT qc.SOLUONG1,qc.SOLUONG2 FROM QUYCACH qc WHERE SANPHAM_FK="+spID+" AND DVDL1_FK="+dvdl_fk+"  and qc.DVDL2_FK="+dvCHUAN;
							ResultSet rs1=db.get(query);
							if(rs1.next()){
								soluongcapnhat_quydoi = soluongcapnhat * rs1.getDouble("SOLUONG2")/ rs1.getDouble("SOLUONG1");
								
							}else{
								this.msg="Không thể xác định quy đổi của sản phẩm : "+rs.getString("ten");
								db.getConnection().rollback();
								return false;
							}
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
			}
			rs.close();
		 
			query = " Update ERP_DondathangNPP set trangthai=0, ptVat = (select top 1 thueVAT from ERP_DONDATHANGNPP_SANPHAM where dondathang_fk = ERP_DondathangNPP.pk_seq and soluong > 0  )   " +
					" where pk_seq= "+this.id;
			if(!db.update(query))
			{
				msg = "Khong the cap nhat ERP_KHOTT_SANPHAM: " + query;
				db.getConnection().rollback();
				rs.close();
				return false;
			}
			db.getConnection().commit();
			db.getConnection().setAutoCommit(true);
		} 
		catch (Exception e) 
		{
			Utility.rollback_throw_exception(db);
			e.printStackTrace();
			this.msg = "Exception: " + e.getMessage();
			return false;
		}
		return true;
	}

	public boolean updateNK(String checkKM, HttpServletRequest request) 
	{
		NumberFormat formater2 = new DecimalFormat("##,###,###.##");
		Utility utilkho= new Utility();
		String _ngayhoadon=utilkho.getngayhoadon(this.userId, db, this.tungay,this.khId,1);
		if(this.tungay.trim().length() < 10)
		{
			this.msg = "Vui lòng nhập ngày đặt hàng";
			return false;
		}
		
		if(this.denngay.trim().length() < 10)
		{
			this.msg = "Vui lòng nhập ngày đề nghị giao";
			return false;
		}

		if( this.gsbhId.trim().length() <= 0 )
		{
			this.msg = "Vui lòng chọn phụ trách tỉnh / GĐCN";
			return false;
		}
		
		if( this.ddkdId.trim().length() <= 0 )
		{
			this.msg = "Vui lòng chọn NHÂN VIÊN BÁN HÀNG";
			return false;
		}

		if( this.khId.trim().length() <= 0 )
		{
			this.msg = "Vui lòng chọn khách hàng ETC";
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
			int flag=0;
			for(int i = 0; i < spMa.length; i++)
			{
				if( spMa[i].trim().length() > 0 )
				{	
					if(spSoluong[i].trim().length() <= 0   )
						spSoluong[i] = "0";
						
					if(spSoluong[i].trim().length() <= 0   )
					{
						this.msg = "Bạn phải nhập số lượng của sản phẩm ( " + spTen[i] + " )";
						return false;
					}
					
					if(spGianhap[i].trim().length() <= 0  )
					{
						this.msg = "Bạn phải nhập đơn giá của sản phẩm ( " + spTen[i] + " )";
						return false;
					}
					
					if(!this.loaidonhang.equals("4"))
					{
						if(spDonvi[i].trim().length() <= 0 )
						{
							this.msg = "Bạn phải nhập đơn vị của sản phẩm ( " + spTen[i] + " )";
							return false;
						}
					}
					
					if(!spSoluong[i].trim().equals("0"))
						flag=1;
					
					coSP = true;
				}
			}
			if(flag==0)
			{
				this.msg = "Vui lòng nhập số lượng sản phẩm";
				return false;
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
						if( spMa[i].trim().equals(spMa[j].trim())  )
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
			String query = "select quanlykho, dungchungkenh from NHAPHANPHOI where PK_SEQ =  (SELECT NPP_FK FROM ERP_DONDATHANGNPP WHERE PK_sEQ="+this.id+" )";
			ResultSet rs = db.get(query);
			String quanlykho = "";
			boolean dungchungkenh=false;
			if(rs.next())
			{
				if(rs.getString("dungchungkenh").equals("1")){
					dungchungkenh=true;
				}
				quanlykho = rs.getString("quanlykho");
			}
			rs.close();

			 query = " Update ERP_DOnDatHangNPP set trangthai=99,ngaydonhang = '" + this.tungay + "', ngaydenghi = '" + this.denngay + "', ghichu = N'" + this.ghichu + "', " +
					 " 	dvkd_fk = '" + this.dvkdId + "', kbh_fk = '" + this.kbhId + "', gsbh_fk = '" + this.gsbhId + "', ddkd_fk = '" + this.ddkdId + "', khachhang_fk = '" + this.khId + "', kho_fk = " + khonhan_fk + ", ngaysua = '" + getDateTime() + "', nguoisua = '" + this.userId + "', chietkhau = '" + chietkhau + "', vat = '" + vat + "' " + 
					 " where pk_seq = '" + this.id + "' ";
			if(!db.update(query))
			{
				this.msg = "Không thể cập nhật ERP_DOnDatHangNPP " + query;
				db.getConnection().rollback();
				return false;
			}
			
			query = "delete ERP_DONDATHANGNPP_SANPHAM where dondathang_fk = '" + this.id + "'";
			if(!db.update(query))
			{
				this.msg = "Không thể cập nhật ERP_DONDATHANGNPP_SANPHAM " + query;
				db.getConnection().rollback();
				return false;
			}
			query = "delete ERP_DONDATHANGNPP_SANPHAM_CHITIET where dondathang_fk = '" + this.id + "'";
			if(!db.update(query))
			{
				this.msg = "Không thể cập nhật ERP_DONDATHANGNPP_SANPHAM " + query;
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
				if(spMa[i].trim().length() > 0 && spSoluong[i].trim().length() > 0 && spGianhap[i].trim().length() > 0  )
				{					
					query = " select sp.ten, dv.donvi, case when sp.dvdl_fk != dv.pk_seq   " +
						    " then ISNULL( ( select soluong2 / soluong1 from QUYCACH where SANPHAM_FK = sp.PK_SEQ and DVDL1_FK = sp.DVDL_FK and DVDL2_FK = dv.pk_seq ), -1 ) else 1 end as quycach   "  +
						    " from SANPHAM sp, DONVIDOLUONG dv " +
						    " where sp.MA = '" + spMa[i].trim() + "' and dv.donvi = N'" + spDonvi[i].trim() + "'   ";
					
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
					
					String thueVAT = spVAT[i].trim().replaceAll(",", "");
					if(thueVAT.trim().length() < 0)
						thueVAT = "0";
					
					boolean ktra = true;
					// Kiểm tra nếu đơn hàng đi từ Hợp đồng (Bình thường) qua >> Số lượng không được vượt quá SL còn lại trong Hợp đồng 
										
					if(this.ma.trim().length() >= 6 && spMa[i].trim().length() > 0)
					{
						//QUY DOI SO LUONG - > SO LUONG DO LUONG CHUAN      
						query = " select case when sp.DVDL_FK=dv.PK_SEQ then "+spSoluong[i].replaceAll(",", "") +" \n" +
					        " else  "+spSoluong[i].replaceAll(",", "") +" * ( select SOLUONG1 from QUYCACH where sanpham_fk = sp.PK_SEQ and DVDL2_FK = dv.PK_SEQ and sp.DVDL_FK=QUYCACH.DVDL1_FK  )       \n" +
					        " / ( select SOLUONG2 from QUYCACH where  sanpham_fk = sp.PK_SEQ and DVDL2_FK = dv.PK_SEQ and sp.DVDL_FK=QUYCACH.DVDL1_FK )  end as soluong " +
					        " from SANPHAM sp, DONVIDOLUONG dv " +
				            " where sp.MA = '" + spMa[i].trim() + "' and dv.donvi = N'" + spDonvi[i].trim() + "'   ";
					     
				      System.out.println("Cau lấy SL Do luong chuan "+query);
				      ResultSet LaySL_DLC = db.get(query);
				      int sl_DLC = 0;    
				      int k=0;
				       while (LaySL_DLC.next())
				       {
					       sl_DLC = LaySL_DLC.getInt("soluong"); 
					       k++;
				       }
				       LaySL_DLC.close();
				      
				       if(k>1)
				       {
				    	    this.msg = "Lỗi trong quá trình quy đổi, vui lòng kiểm tra lại quy đổi hoặc báo với Admin để được xử lý"+query;
							db.getConnection().rollback();
							return false;
				       }
					      
				       query = 
				    		" select (select loaidonhang from ERP_HOPDONGNPP where PK_SEQ = "+this.ma+") as loaidh, hd.soluong - isnull(dh.daDAT, 0) as SL  " +
							" from " +
							" ( " +
							"	select sanpham_fk, dvdl_fk, sum(soluong) as soluong, avg(dongia) as dongia, avg(chietkhau) as chietkhau, avg(thuevat) as thuevat, tungay, denngay " +
							"	from " +
							"	( " +
							"		select sanpham_fk,  " +
							"		case when a.dvdl_fk IS null then a.soluong       " +
							"		when a.dvdl_fk = b.DVDL_FK then a.soluong      " +
							"		else  a.soluong * ( select SOLUONG1 from QUYCACH where sanpham_fk = a.sanpham_fk and DVDL2_FK = a.dvdl_fk   and QUYCACH.dvdl1_fk=b.dvdl_fk)       " +
							"		/ ( select SOLUONG2 from QUYCACH where sanpham_fk = a.sanpham_fk and DVDL2_FK = a.dvdl_fk and QUYCACH.dvdl1_fk=b.dvdl_fk )	 end as soluong, dongia, chietkhau, thueVAT, b.pk_seq as dvdl_fk, tungay, denngay  " +
							"		from ERP_HOPDONGNPP_SANPHAM a inner join SANPHAM b on a.sanpham_fk = b.pk_seq  " +
							"		where HOPDONG_FK = '" + this.ma + "'  " +
							"		union ALL " +
							"		select sanpham_fk,  " +
							"		case when a.dvdl_fk IS null then a.soluong       " +
							"		when a.dvdl_fk = b.DVDL_FK then a.soluong      " +
							"		else  a.soluong * ( select SOLUONG1 from QUYCACH where sanpham_fk = a.sanpham_fk and DVDL2_FK = a.dvdl_fk and QUYCACH.dvdl1_fk=b.dvdl_fk )       " +
							"		/ ( select SOLUONG2 from QUYCACH where sanpham_fk = a.sanpham_fk and DVDL2_FK = a.dvdl_fk and QUYCACH.dvdl1_fk=b.dvdl_fk )	 end as soluong,  " +
							" 		dongia, chietkhau, thueVAT, b.pk_seq as dvdl_fk, tungay, denngay  " +
							"		from ERP_HOPDONGNPP_SANPHAM a inner join SANPHAM b on a.sanpham_fk = b.pk_seq   " +
							"		where HOPDONG_FK in ( select pk_seq from ERP_HOPDONGNPP where hopdong_fk = '" + this.ma + "' and trangthai in (1, 2) ) " +
							"	) hopdong group by sanpham_fk, dvdl_fk, tungay, denngay " +
							" ) hd "+
							" left join " +
							" ( " +
							"	select sanpham_fk, sum(soluong) as daDAT " +
							"	from " +
							"	( " +
							"		select a.sanpham_fk, b.DVDL_FK as dvCHUAN, " +
							"		case when a.dvdl_fk IS null then a.soluong " +
							"		when a.dvdl_fk = b.DVDL_FK then a.soluong " +
							"		else  a.soluong * ( select SOLUONG1 from QUYCACH where sanpham_fk = a.sanpham_fk and DVDL2_FK = a.dvdl_fk  and QUYCACH.dvdl1_fk=b.dvdl_fk)       " +
							"		/ ( select SOLUONG2 from QUYCACH where sanpham_fk = a.sanpham_fk and DVDL2_FK = a.dvdl_fk  and QUYCACH.dvdl1_fk=b.dvdl_fk ) end as soluong  " +
							"		from ERP_DONDATHANGNPP_SANPHAM a inner join SANPHAM b on a.sanpham_fk = b.PK_SEQ      " +
							"		where a.dondathang_fk in (   select pk_seq from ERP_DondathangNPP where trangthai != '3' and hopdong_fk = '" + this.ma + "'  )     " +
							"	) dathang group by sanpham_fk " +
							" ) dh on hd.sanpham_fk = dh.sanpham_fk " +
							" where hd.sanpham_fk = (select pk_seq from SANPHAM where MA = '" + spMa[i].trim() + "' ) ";  
						
						System.out.println("Câu check SL "+query);
						ResultSet LaySL_Conlai = db.get(query);
						
						int sl_conlai = 0;
						String loaidh = "";
						 
						while (LaySL_Conlai.next())
						{
							sl_conlai = LaySL_Conlai.getInt("SL");	
							loaidh = LaySL_Conlai.getString("loaidh");
						}
						LaySL_Conlai.close();
						 
						if( loaidh.equals("0") && sl_DLC > sl_conlai )
						{
							ktra = false;
						}
					}
					
					if(ktra)
					{					
						query = " insert ERP_DONDATHANGNPP_SANPHAM( dondathang_fk, SANPHAM_FK, sanphamTEN, soluong, dongia, chietkhau, thueVAT, dvdl_fk, tungay, denngay, dongiaGOC, stt ) " +
								" select '" + this.id + "', pk_seq, N'" + spTen[i] + "', '" + spSoluong[i].replaceAll(",", "") + "', '" + spGianhap[i].replaceAll(",", "") + "', '" + ck + "', '" + thueVAT + "', ISNULL( ( select pk_Seq from DONVIDOLUONG where donvi = N'" + spDonvi[i].trim() + "' ), DVDL_FK ), '" + spTungay[i].trim() + "', '" + spDenngay[i].trim() + "', " +
								" isnull( ( select GIAMUANPP * ( 1 - isnull( ( select chietkhau from BANGGIAMUANPP_NPP where banggiamuaNPP_FK = bg_sp.bgmuaNPP_FK and NPP_FK = '" + this.nppId + "' ), 0) / 100 ) " +
								" from BGMUANPP_SANPHAM bg_sp " +
								" where SANPHAM_FK = a.pk_seq " +
								" and BGMUANPP_FK in ( select top(1) PK_SEQ from BANGGIAMUANPP bg inner join BANGGIAMUANPP_NPP bg_npp on bg.PK_SEQ = bg_npp.BANGGIAMUANPP_FK where bg.TRANGTHAI = '1' and bg_npp.NPP_FK = '" + this.nppId + "' and bg.DVKD_FK = '" + this.dvkdId + "'  order by bg.TUNGAY desc ) ), 0) as giamua, "+spStt[i]+" " +
								" from SANPHAM a where MA = '" + spMa[i].trim() + "' ";
						
						System.out.println("1.Insert HD - SP: " + query);
						if(!db.update(query))
						{
							this.msg = "Khong the tao moi ERP_DONDATHANGNPP_SANPHAM: " + query;
							db.getConnection().rollback();
							return false;
						}
					} else
					{
						this.msg = "Số lượng sản phẩm "+ spMa[i].trim() + " đã vượt quá số lượng còn lại trong Hợp đồng "+ this.ma + ".Vui lòng kiểm tra lại." ;
						db.getConnection().rollback();
						return false;						
					}
				}
			}
			
			String kqVat = geso.dms.center.util.Utility.CheckVat_Loai2( db , "ERP_DONDATHANGNPP_SANPHAM","thueVAT", "dondathang_fk", this.id,"soluong" );
			if(kqVat.trim().length() > 0)
			{
				this.msg = kqVat;
				db.getConnection().rollback();
				db.getConnection().setAutoCommit(true);
				return false;
			}
			
			// kiem tra xem co quy doi ve chuan dc ko 
			
			// kiem tra xem co ban chan ko
			query = 
				" select dathang.soluongdh,sp.dvdl_fk,dvCHUAN, khoxuat_fk as kho_fk, npp_fk, kbh_fk, sp.PK_SEQ, sp.TEN, SUM(dathang.soluong) as soluongXUAT,  " +
				" ISNULL( ( select AVAILABLE from NHAPP_KHO where kho_fk = dathang.khoxuat_fk and sanpham_fk = sp.PK_SEQ and kbh_fk = dathang.kbh_fk and npp_fk = dathang.npp_fk ), 0) as tonkho  " +
				" from " +
			    " ( " +
			    " 	select a.soluong as soluongdh, c.kho_fk as khoxuat_fk, c.npp_fk, '" + kbh_fk + "' kbh_fk, a.sanpham_fk, a.DVDL_FK as dvCHUAN,     " +
			    "	case when a.dvdl_fk IS null then a.soluong      " +
			    "	when a.dvdl_fk = b.DVDL_FK then a.soluong     " +
			    "	else  a.soluong * ( select SOLUONG1 from QUYCACH where sanpham_fk = a.sanpham_fk and DVDL2_FK = a.dvdl_fk and dvdl1_fk = b.dvdl_fk )      " +
			    "	/ ( select SOLUONG2 from QUYCACH where sanpham_fk = a.sanpham_fk and DVDL2_FK = a.dvdl_fk and dvdl1_fk = b.dvdl_fk )	 end as soluong   " +
			    "	from ERP_DONDATHANGNPP_SANPHAM a inner join SANPHAM b on a.sanpham_fk = b.PK_SEQ  " +
			    "	inner join ERP_DONDATHANGNPP c on a.dondathang_fk = c.pk_seq    " +
			    "	where a.dondathang_fk in ( " + this.id + " )  and a.soluong > 0   " +
			    " ) dathang inner join SANPHAM sp on dathang.sanpham_fk = sp.PK_SEQ   " +
			    " group by khoxuat_fk, npp_fk, kbh_fk, sp.PK_SEQ, sp.TEN ,sp.dvdl_fk,dvCHUAN,dathang.soluongdh ";

			System.out.println("--CHECK TON KHO: " + query);
			rs = db.get(query);
			while(rs.next())
			{
				String khoID = rs.getString("kho_fk");
				String kbhID = rs.getString("kbh_fk");
				String nppID = rs.getString("npp_fk");
				String spID = rs.getString("PK_SEQ");
				String spten= rs.getString("ten");
				String  dvdl_fk =rs.getString("dvdl_fk"); 
				String  dvCHUAN =rs.getString("dvCHUAN"); 

				double soluong = rs.getDouble("soluongXUAT");
				double tonkho = rs.getDouble("tonkho");

				if(soluong - (int) soluong > 0)
				{
					msg = "Sản phẩm ( " + rs.getString("TEN") + " ) với số lượng yêu cầu ( " + rs.getString("soluongXUAT") + " ) quy đổi về đơn vị chuẩn bị Lẻ vui lòng điều chỉnh lại ";
					db.getConnection().rollback();
					rs.close();
					return false;
				}
			}
			
			String LINKSERVER_DB = request.getServletContext().getInitParameter("LINKSERVER_DB");
			if(quanlykho.equals("0"))
			{
				//CHECK BOOKED THEO DV CHUAN
				query = 
					" IF OBJECT_ID('tempdb.dbo.#kho') IS NOT NULL DROP TABLE #kho select * into #kho from "+
				 	" ( "+
					" 	 SELECT SP_E.MA SPMA, NPP_E.MA NPPMA, SUM(AVAILABLE) AVAILABLE "+
					" 	 FROM "+ LINKSERVER_DB +".[dbo].[ERP_KHOTT_SP_CHITIET] K   "+
					" 	 INNER JOIN "+ LINKSERVER_DB +".[dbo].NHAPHANPHOI NPP_E ON NPP_E.PK_SEQ = K.NPP_FK   "+
					" 	 INNER JOIN "+ LINKSERVER_DB +".[dbo].ERP_SANPHAM SP_E ON SP_E.PK_SEQ = K.SANPHAM_FK    "+
					" 	 WHERE K.NGAYNHAPKHO <= '"+ this.tungay +"' AND NPP_E.MA = ( SELECT DISTINCT MA FROM NHAPHANPHOI WHERE PK_SEQ = ( select NPP_FK from ERP_DONDATHANGNPP where  PK_SEQ = '"+ this.id +"' )) "+
					" 	 GROUP BY SP_E.MA, NPP_E.MA "+
					" ) AS K "+	
					
					" select dvCHUAN,sp.dvdl_fk,khoxuat_fk as kho_fk, npp_fk, kbh_fk, sp.PK_SEQ, sp.TEN, SUM(dathang.soluong) as soluongXUAT,  " +
					//" ISNULL( ( select AVAILABLE from NHAPP_KHO where kho_fk = dathang.khoxuat_fk and sanpham_fk = sp.PK_SEQ and kbh_fk = dathang.kbh_fk and npp_fk = dathang.npp_fk ), 0) as tonkho  " +
					" k.available as tonkho "+
					" from     " +
					" (     " +
					"	select c.kho_fk as khoxuat_fk, c.npp_fk, '" + kbh_fk + "' kbh_fk, a.sanpham_fk, a.DVDL_FK as dvCHUAN,     " +
					"	case when a.dvdl_fk IS null then a.soluong      " +
					"	when a.dvdl_fk = b.DVDL_FK then a.soluong     " +
					"	else  a.soluong * ( select SOLUONG1 from QUYCACH where sanpham_fk = a.sanpham_fk and DVDL2_FK = a.dvdl_fk and dvdl1_fk = b.dvdl_fk )      " +
					"	/ ( select SOLUONG2 from QUYCACH where sanpham_fk = a.sanpham_fk and DVDL2_FK = a.dvdl_fk and dvdl1_fk = b.dvdl_fk )	 end as soluong   " +
					"	from ERP_DONDATHANGNPP_SANPHAM a inner join SANPHAM b on a.sanpham_fk = b.PK_SEQ  " +
					"	inner join ERP_DONDATHANGNPP c on a.dondathang_fk = c.pk_seq    " +
					"	where a.dondathang_fk in ( " + this.id + " )     " +
					" ) dathang "+
					" inner join SANPHAM sp on dathang.sanpham_fk = sp.PK_SEQ " +
					" OUTER APPLY ( SELECT * FROM #KHO WHERE SPMA = sp.MA ) AS K "+
					" group by khoxuat_fk, npp_fk, kbh_fk, sp.PK_SEQ, sp.TEN ,dvCHUAN,sp.dvdl_fk, k.available ";
			}
			else
			{
				//CHECK BOOKED THEO DV CHUAN
				query = 
					" select dvCHUAN,sp.dvdl_fk,khoxuat_fk as kho_fk, npp_fk, kbh_fk, sp.PK_SEQ, sp.TEN, SUM(dathang.soluong) as soluongXUAT,  " +
					" ISNULL( ( select AVAILABLE from NHAPP_KHO where kho_fk = dathang.khoxuat_fk and sanpham_fk = sp.PK_SEQ and kbh_fk = dathang.kbh_fk and npp_fk = dathang.npp_fk ), 0) as tonkho  " +
					" from     " +
					" (     " +
					"	select c.kho_fk as khoxuat_fk, c.npp_fk, '" + kbh_fk + "' kbh_fk, a.sanpham_fk, a.DVDL_FK as dvCHUAN,     " +
					"	case when a.dvdl_fk IS null then a.soluong      " +
					"	when a.dvdl_fk = b.DVDL_FK then a.soluong     " +
					"	else  a.soluong * ( select SOLUONG1 from QUYCACH where sanpham_fk = a.sanpham_fk and DVDL2_FK = a.dvdl_fk and dvdl1_fk = b.dvdl_fk )      " +
					"	/ ( select SOLUONG2 from QUYCACH where sanpham_fk = a.sanpham_fk and DVDL2_FK = a.dvdl_fk and dvdl1_fk = b.dvdl_fk )	 end as soluong   " +
					"	from ERP_DONDATHANGNPP_SANPHAM a inner join SANPHAM b on a.sanpham_fk = b.PK_SEQ  " +
					"	inner join ERP_DONDATHANGNPP c on a.dondathang_fk = c.pk_seq    " +
					"	where a.dondathang_fk in ( " + this.id + " )     " +
					" ) dathang inner join SANPHAM sp on dathang.sanpham_fk = sp.PK_SEQ   " +
					" group by khoxuat_fk, npp_fk, kbh_fk, sp.PK_SEQ, sp.TEN ,dvCHUAN,sp.dvdl_fk  ";
			}
			System.out.println("--CHECK TON KHO: " + query);
			
			rs = db.get(query);
			/*if(rs != null)*/
			{
				while(rs.next())
				{
					String khoID = rs.getString("kho_fk");
					String kbhID = rs.getString("kbh_fk");
					String nppID = rs.getString("npp_fk");
					String spID = rs.getString("PK_SEQ");
					String dvCHUAN=rs.getString("dvCHUAN");
					String spten=rs.getString("ten");
					String dvdl_fk=rs.getString("dvdl_fk");
					double soluong = rs.getDouble("soluongXUAT");
					double tonkho = rs.getDouble("tonkho");
					
					if(soluong > tonkho)
					{
						msg = "Sản phẩm ( " + rs.getString("TEN") + " ) với số lượng yêu cầu ( " + formater2.format(rs.getDouble("soluongXUAT")) + " ) không đủ tồn kho ( " + formater2.format(rs.getDouble("tonkho")) + " ). Vui lòng kiểm tra lại.";
						db.getConnection().rollback();
						rs.close();
						return false;
					}
			 
					 query="select isnull(checkkho,0) as sl from ERP_DondathangNPP where pk_Seq="+this.id;
					 ResultSet rschk=db.get(query);
					 rschk.next();
					 if(rschk.getInt("sl")==1)
					 {
						
						// de xuat lo book khi chi tiet 

						 query=		" select KHO_FK,SANPHAM_FK,KBH_FK,SOLO,NGAYHETHAN,NGAYNHAPKHO,available  from NHAPP_KHO_CHITIET "+  
									" where NPP_FK ="+nppID+" and KBH_FK= " +kbhID +
									" and KHO_FK="+khoID+"  and SANPHAM_FK =  "+ spID +
									" AND AVAILABLE >0  and NGAYNHAPKHO<='"+_ngayhoadon+"'"+
									" order by NGAYHETHAN ,NGAYNHAPKHO,AVAILABLE ";
						ResultSet rssp=db.get(query);
						double soluongdenghi=soluong ;
					
						while(rssp.next() && soluongdenghi >0)
						{
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
							if(dvCHUAN.equals(dvdl_fk)){
								// nếu là đơn vị giống nhau
								soluongcapnhat_quydoi= soluongcapnhat;
								
							}else{
								query=" SELECT qc.SOLUONG1,qc.SOLUONG2 FROM QUYCACH qc WHERE SANPHAM_FK="+spID+" AND DVDL1_FK="+dvdl_fk+"  and qc.DVDL2_FK="+dvCHUAN;
								ResultSet rs1=db.get(query);
								if(rs1.next()){
									soluongcapnhat_quydoi = soluongcapnhat * rs1.getDouble("SOLUONG2")/ rs1.getDouble("SOLUONG1");
									
								}else{
									this.msg="Không thể xác định quy đổi của sản phẩm : "+rs.getString("ten");;
									db.getConnection().rollback();
									return false;
								}
							}
						}
						rssp.close();
						
						if(soluongdenghi!=0)
						{
							this.msg = " Số lượng đề xuất trong lô chi tiết của sản phẩm "+spten+"   tới ngày (ngày cấu hình hóa đơn)"+_ngayhoadon+" không còn đủ, " +
									   " vui lòng kiểm tra báo cáo ( xuất nhập tồn,tồn hiện tại) theo lô để biết thêm chi tiết ";
							db.getConnection().rollback();
							rs.close();
							return false;
						}
					}
				}
				rs.close();
				 
				query = " Update ERP_DondathangNPP set trangthai = '0', ptVat = (select top 1 thueVAT from ERP_DONDATHANGNPP_SANPHAM where dondathang_fk = ERP_DondathangNPP.pk_seq and soluong > 0  ) where pk_seq= "+this.id;
				if(!db.update(query))
				{
					msg = "Khong the cap nhat ERP_KHOTT_SANPHAM: " + query;
					db.getConnection().rollback();
					rs.close();
					return false;
				}
			}
			
			//INSERT CHIET KHAU BO SUNG
			if(this.dhCk_diengiai != null)
			{
				for(int i = 0; i < this.dhCk_diengiai.length; i++)
				{
					if(this.dhCk_giatri[i].trim().length() > 0)
					{
						query = "insert ERP_DONDATHANGNPP_CHIETKHAU(DONDATHANG_FK, DIENGIAI, GIATRI, LOAI) " +
								"values( '" + this.id + "', N'" + this.dhCk_diengiai[i].trim() + "', '" + this.dhCk_giatri[i].replaceAll(",", "") + "', '" + this.dhCk_loai[i] + "' ) ";
						
						//System.out.println("1.Insert HD - CK: " + query);
						if(!db.update(query))
						{
							this.msg = "Khong the tao moi ERP_DONDATHANGNPP_CHIETKHAU: " + query;
							db.getConnection().rollback();
							return false;
						}
					}
				}
			}
			
			//CHECK XEM SO LUONG CO BI VUOT HOP DONG KHONG
			query = " select pk_seq, loaidonhang, hopdong_fk " +
					" from ERP_HOPDONGNPP where pk_seq in ( select hopdong_fk from ERP_DONDATHANGNPP where pk_seq = '" + this.id + "' ) ";
			rs = db.get(query);
			String hopdong_fk = "";
			String hoadonId = "";
			if(rs.next())
			{
				hoadonId = rs.getString("pk_seq");
				
				if(rs.getString("hopdong_fk") != null)
					hopdong_fk = rs.getString("hopdong_fk");
				
				rs.close();
			}
			
			if (hopdong_fk.trim().length() > 0) // Hóa đơn bình thường, chỉ được phép đặt bằng số còn lại
			{
				query = "select ( select ten from SANPHAM where pk_seq = hd.sanpham_fk ) as spTEN, isnull( hd.soluong, 0) as soluong, isnull( dh.daDAT, 0 ) as daDAT  " +
						"from " +
						"( " +
						"	select sanpham_fk, dvdl_fk, sum(soluong) as soluong, avg(dongia) as dongia, avg(chietkhau) as chietkhau, avg(thuevat) as thuevat, tungay, denngay " +
						"	from " +
						"	( " +
						"		select sanpham_fk,  " +
						"		case when a.dvdl_fk IS null then a.soluong       " +
						"		when a.dvdl_fk = b.DVDL_FK then a.soluong      " +
						"		else  a.soluong * ( select SOLUONG1 from QUYCACH where sanpham_fk = a.sanpham_fk and DVDL2_FK = a.dvdl_fk )       " +
						"		/ ( select SOLUONG2 from QUYCACH where sanpham_fk = a.sanpham_fk and DVDL2_FK = a.dvdl_fk )	 end as soluong, dongia, chietkhau, thueVAT, b.pk_seq as dvdl_fk, tungay, denngay  " +
						"		from ERP_HOPDONGNPP_SANPHAM a inner join SANPHAM b on a.sanpham_fk = b.pk_seq  " +
						"		where HOPDONG_FK = '" + hoadonId + "' and HOPDONG_FK in ( select pk_seq from ERP_HOPDONGNPP where pk_seq = '" + hoadonId + "' and loaidonhang = '0' )  " +
						"		union ALL " +
						"		select sanpham_fk,  " +
						"		case when a.dvdl_fk IS null then a.soluong       " +
						"		when a.dvdl_fk = b.DVDL_FK then a.soluong      " +
						"		else  a.soluong * ( select SOLUONG1 from QUYCACH where sanpham_fk = a.sanpham_fk and DVDL2_FK = a.dvdl_fk )       " +
						"		/ ( select SOLUONG2 from QUYCACH where sanpham_fk = a.sanpham_fk and DVDL2_FK = a.dvdl_fk )	 end as soluong, dongia, chietkhau, thueVAT, b.pk_seq as dvdl_fk, tungay, denngay  " +
						"		from ERP_HOPDONGNPP_SANPHAM a inner join SANPHAM b on a.sanpham_fk = b.pk_seq   " +
						"		where HOPDONG_FK in ( select pk_seq from ERP_HOPDONGNPP where hopdong_fk = '" + hoadonId + "' and trangthai in (1, 2) ) " +
						"	) hopdong group by sanpham_fk, dvdl_fk, tungay, denngay " +
						") hd "+
						"left join " +
						"( " +
						"	select sanpham_fk, sum(soluong) as daDAT " +
						"	from " +
						"	( " +
						"		select a.sanpham_fk, b.DVDL_FK as dvCHUAN,      " +
						"		case when a.dvdl_fk IS null then a.soluong       " +
						"		when a.dvdl_fk = b.DVDL_FK then a.soluong      " +
						"		else  a.soluong * ( select SOLUONG1 from QUYCACH where sanpham_fk = a.sanpham_fk and DVDL2_FK = a.dvdl_fk )       " +
						"		/ ( select SOLUONG2 from QUYCACH where sanpham_fk = a.sanpham_fk and DVDL2_FK = a.dvdl_fk ) end as soluong  " +
						"		from ERP_DONDATHANGNPP_SANPHAM a inner join SANPHAM b on a.sanpham_fk = b.PK_SEQ      " +
						"		where a.dondathang_fk in (   select pk_seq from ERP_DondathangNPP where trangthai != '3' and hopdong_fk = '" + hoadonId + "'  )     " +
						"	) dathang group by sanpham_fk " +
						") dh on hd.sanpham_fk = dh.sanpham_fk " +
						"where hd.soluong < isnull(dh.daDAT, 0) ";
				
				System.out.println("----CHECK VUOT HOPDONG-SANPHAM: " + query );
				rs = db.get(query);
				if(rs != null)
				{
					while(rs.next())
					{
						String spTEN = rs.getString("spTEN");
						double tongHOADON = rs.getDouble("soluong");
						double tongDAXUAT = rs.getDouble("daDAT");
						
						if(tongDAXUAT > tongHOADON )
						{
							this.msg += " Tổng đặt ( " + tongDAXUAT + " ) của sản phẩm ( " + spTEN + " ) đã vượt quá tổng lượng trong hợp đồng ( " + tongHOADON + " ) \n";
							db.getConnection().rollback();
							return false;
						}
					}
					rs.close();
				}
			}
			
			db.getConnection().commit();
			db.getConnection().setAutoCommit(true);
			this.msg = "Cập nhật đơn hàng " + this.id + " thành công!";
			return true;
		} 
		catch (Exception e) 
		{
			e.printStackTrace();
			Utility.rollback_throw_exception(db);
			this.msg = "Exception: " + e.getMessage();
			return false;
		}
	}

	public void createRs() 
	{
		this.getNppInfo();
		
		String query = "select PK_SEQ, TEN from KHO where trangthai = '1' and pk_seq in " + this.util.quyen_kho(this.userId);
		if(this.ma != null && this.ma.length() >=2 && this.khoNhanId != null && this.khoNhanId.length() > 0)
			query +=" and pk_seq = "+this.khoNhanId;
		this.khoNhanRs = db.get(query  );
		
		this.dvtRs = db.getScrol("select PK_SEQ, DONVI from DONVIDOLUONG where trangthai = '1' ");
		this.dvkdRs = db.get("select PK_SEQ, DONVIKINHDOANH as TEN from DONVIKINHDOANH where TRANGTHAI = '1'");
		this.kbhRs = db.get("select PK_SEQ, DIENGIAI as TEN from KENHBANHANG where TRANGTHAI = '1' and pk_seq = '100052'  ");
		
	
		
		query = "select PK_SEQ, TEN from GIAMSATBANHANG where trangthai = '1' and pk_seq in (select GSBH_FK from NHAPP_GIAMSATBH where npp_fk = '" + this.nppId + "' ) ";
		if(this.ma != null && this.ma.length() >=2 && this.gsbhId != null && this.gsbhId.length() > 0)
			query +=" and pk_seq = "+this.gsbhId;
		this.gsbhRs = db.get(query);
		//String query = "select pk_seq, TEN from DAIDIENKINHDOANH where tructhuoc_fk = '" + this.nppId + "' ";
		
		query = "select pk_seq, TEN from DAIDIENKINHDOANH where pk_seq in ( select ddkd_fk from DAIDIENKINHDOANH_NPP where npp_fk = '" + this.nppId + "' ) ";
		if(this.ma != null && this.ma.length() >=2 && this.ddkdId != null && this.ddkdId.length() > 0)
			query +=" and pk_seq = "+this.ddkdId;
		this.ddkdRs = db.get(query);
		
		query = "select PK_SEQ, MAFAST + ', ' + TEN as TEN from KHACHHANG where TRANGTHAI = '1'  AND KBH_FK = '100052' and NPP_FK = '" + this.nppId + "' ";
		if(this.ma != null && this.ma.length() >=2 && this.khId != null && this.khId.length() > 0)
			query +=" and pk_seq = "+this.khId;
		this.khRs = db.get(query);
		
		System.out.println("---NPP ID: " + this.nppId);
		if(this.nppId.trim().length() > 0)
		{
			query = "select GSBH_FK from NHAPP_GIAMSATBH  " +
				    "where NPP_FK = '" + this.nppId + "' ";
			ResultSet rsInfo = db.get(query);
			if(rsInfo != null)
			{
				try 
				{
					if(rsInfo.next())
					{
						this.gsbhId = rsInfo.getString("gsbh_fk");
					}
					rsInfo.close();
				} 
				catch (Exception e) {}
			}
		}
		
		if(this.khId.trim().length() > 0 && this.ddkdId.trim().length() <= 0 )
		{
			query = "select b.ddkd_fk from KHACHHANG_TUYENBH a inner join TUYENBANHANG b on a.TBH_FK = b.pk_seq " +
					"where a.khachhang_fk = '" + this.khId + "'";
			ResultSet rsDDKD = db.get(query);
			if(rsDDKD != null)
			{
				try 
				{
					if(rsDDKD.next())
					{
						this.ddkdId = rsDDKD.getString("ddkd_fk");
					}
					rsDDKD.close();
				}
				catch (Exception e) { }
			}
		}
		
		if (this.khId.trim().length() > 0 && this.ma.trim().length() <= 0)
		{
			query = "select isnull(PT_CHIETKHAU, 0) as PT_CHIETKHAU from KHACHHANG where pk_seq = '" + this.khId + "'";
			ResultSet rsDDKD = db.get(query);
			if(rsDDKD != null)
			{
				try 
				{
					if(rsDDKD.next())
					{
						this.chietkhau = rsDDKD.getString("PT_CHIETKHAU");
					}
					rsDDKD.close();
				}
				catch (Exception e) { }
			}
		}
		
	}

	public void initSANPHAM2() 
	{

		String dungchungkenh="0";
		String qu="select dungchungkenh from nhaphanphoi where pk_seq="+this.nppId;
		ResultSet rsdd=db.get(qu);
		try {
			rsdd.next();
			dungchungkenh=rsdd.getString("dungchungkenh"); 
			rsdd.close();
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		
		String query = "\n select a.stt, b.MA,(select kho.available from nhapp_kho kho where kho.sanpham_fk=b.pk_seq and kho.KHO_FK= " + this.getKhoNhapId() + " and NPP_FK in(select NPP_FK from ERP_DONDATHANGNPP where  PK_SEQ=a.dondathang_fk) and kho.KBH_FK="+ (dungchungkenh.equals("1") ? "100025" : this.kbhId) + " )as soluongton,"+
					"\n  isnull(a.sanphamTEN, b.TEN) as TEN, DV.donvi, a.soluong,case when (select HopDong from ERP_DONDATHANGNPP where pk_Seq='" + this.id + "')=null then   [dbo].[GiaCkBanLeNppSanPham]("+nppId+","+khId+",a.sanpham_fk,'"+this.tungay+"' ) else a.dongia end   as dongia, isnull(a.chietkhau, 0) as chietkhau, ISNULL(b.trongluong, 0) as trongluong, ISNULL(b.thetich, 0) as thetich, a.tungay, a.denngay, a.thueVAT    "+
					"\n --	,(select soluong1/ soluong2 from QUYCACH where sanpham_fk=a.sanpham_fk and DVDL1_FK=b.DVDL_FK and DVDL2_FK=100018)   as spQuyDoi" +
					"\n 	,[dbo].[LayQuyCach](a.sanpham_fk, null , a.dvdl_fk ) spQuyDoi  "+
					"\n  from ERP_DONDATHANGNPP_SANPHAM a inner Join SanPham b on a.SANPHAM_FK = b.PK_SEQ    " +
					"\n  		INNER JOIN DONVIDOLUONG DV ON DV.PK_SEQ = a.DVDL_FK       " +
					"\n where a.DONDATHANG_FK = '" + this.id + "' order by a.stt ";
		String query1 = "";
		System.out.println("---INIT SP_1: " + query);
		System.out.println("---INIT SP_2: " + query1);
		System.out.println("nha phan phoi id:");
		ResultSet spRs = db.get(query);
		
		NumberFormat formater = new DecimalFormat("##,###,###");
		NumberFormat formater2 = new DecimalFormat("##,###,###.##");
		/*if(this.id.equals("") )
		{
			this.spMa =null;
			this.spTen =null;
			
				this.spDonvi =null;
			
				this.spSoluong =null;
			
			this.spSoluongton = null;

			this.spGianhap = null;
			
			this.spChietkhau = null;
			this.spVAT = null;
			this.spTungay = null;
			
			this.spDenngay = null;
			this.spTrongluong =null;
			
			this.spThetich = null;	
			this.spQuyDoi = null;
		}*/
		if(spRs != null)
		{
			try 
			{
				String spSTT = "";
				String spMA = "";
				String spTEN = "";
				String spDONVI = "";
				String spSOLUONG = "";
				String spGIANHAP = "";
				String spCHIETKHAU = "";
				String spVAT = "";
				String spTUNGAY = "";
				String spDENNGAY = "";
				
				String spTRONGLUONG = "";
				String spTHETICH = "";
				
				String spQuyDoi ="";
				String spSOLUONGTON = "";
				while(spRs.next())
				{
					spSTT += spRs.getString("stt") + "__";
					spMA += spRs.getString("MA") + "__";
					spTEN += spRs.getString("TEN") + "__";
					spDONVI += spRs.getString("DONVI") + "__";
					spSOLUONG += formater.format(spRs.getDouble("SOLUONG")) + "__";
					spGIANHAP += spRs.getDouble("DONGIA") + "__";
					spCHIETKHAU += formater.format(spRs.getDouble("chietkhau")) + "__";
					spVAT += formater.format(spRs.getDouble("thueVAT")) + "__";
					spSOLUONGTON += formater.format(spRs.getDouble("soluongton")) + "__";
					if(spRs.getString("tungay").trim().length() > 0)
						spTUNGAY += spRs.getString("tungay") + "__";
					else
						spTUNGAY += " __";
					
					if(spRs.getString("denngay").trim().length() > 0)
						spDENNGAY += spRs.getString("denngay") + "__";
					else
						spDENNGAY += " __";
					
					spTRONGLUONG += spRs.getString("trongluong") + "__";
					spTHETICH += spRs.getString("thetich") + "__";
					spQuyDoi +=spRs.getString("spQuyDoi") + "__";
				}
				spRs.close();
				
				if(spMA.trim().length() > 0)
				{
					spSTT = spSTT.substring(0, spSTT.length() - 2);
					this.spStt = spSTT.split("__");
					
					spMA = spMA.substring(0, spMA.length() - 2);
					this.spMa = spMA.split("__");
					
					spTEN = spTEN.substring(0, spTEN.length() - 2);
					this.spTen = spTEN.split("__");
					
					spDONVI = spDONVI.substring(0, spDONVI.length() - 2);
					this.spDonvi = spDONVI.split("__");
					
					spSOLUONG = spSOLUONG.substring(0, spSOLUONG.length() - 2);
					this.spSoluong = spSOLUONG.split("__");
					
					spSOLUONGTON = spSOLUONGTON.substring(0, spSOLUONGTON.length() - 2);
					this.spSoluongton = spSOLUONGTON.split("__");

					spGIANHAP = spGIANHAP.substring(0, spGIANHAP.length() - 2);
					this.spGianhap = spGIANHAP.split("__");
					
					spCHIETKHAU = spCHIETKHAU.substring(0, spCHIETKHAU.length() - 2);
					this.spChietkhau = spCHIETKHAU.split("__");
					
					spVAT = spVAT.substring(0, spVAT.length() - 2);
					this.spVAT = spVAT.split("__");
					
					spTUNGAY = spTUNGAY.substring(0, spTUNGAY.length() - 2);
					this.spTungay = spTUNGAY.split("__");
					
					spDENNGAY = spDENNGAY.substring(0, spDENNGAY.length() - 2);
					this.spDenngay = spDENNGAY.split("__");
					
					spTRONGLUONG = spTRONGLUONG.substring(0, spTRONGLUONG.length() - 2);
					this.spTrongluong = spTRONGLUONG.split("__");
					
					spTHETICH = spTHETICH.substring(0, spTHETICH.length() - 2);
					this.spThetich = spTHETICH.split("__");
					
					spQuyDoi = spQuyDoi.substring(0, spQuyDoi.length() - 2);
					this.spQuyDoi = spQuyDoi.split("__");
				}
				
			} 
			catch (Exception e) 
			{
				e.printStackTrace();
				System.out.println("115.Exception: " + e.getMessage());
			}
		}
		
	}
	
	private void initSANPHAM(HttpServletRequest request) 
	{
		String sql = "select quanlykho from nhaphanphoi where pk_Seq = '"+ this.nppId +"'";
		String quanlykho = "";
		ResultSet rs = this.db.get(sql);
		try {
			rs.next();
			quanlykho = rs.getString("quanlykho");
			rs.close();
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		String LINKSERVER_DB = request.getServletContext().getInitParameter("LINKSERVER_DB");
		String query = "";
		if(quanlykho.trim().equals("0"))
		{
			query = 
				"IF OBJECT_ID('tempdb.dbo.#kho') IS NOT NULL DROP TABLE #kho select * into #kho from "+
			 	" ( "+
				" 	 SELECT SP_E.MA SPMA, NPP_E.MA NPPMA, SUM(AVAILABLE) AVAILABLE "+
				" 	 FROM "+ LINKSERVER_DB +".[dbo].[ERP_KHOTT_SP_CHITIET] K   "+
				" 	 INNER JOIN "+ LINKSERVER_DB +".[dbo].NHAPHANPHOI NPP_E ON NPP_E.PK_SEQ = K.NPP_FK   "+
				" 	 INNER JOIN "+ LINKSERVER_DB +".[dbo].ERP_SANPHAM SP_E ON SP_E.PK_SEQ = K.SANPHAM_FK    "+
				" 	 WHERE K.NGAYNHAPKHO <= '"+ this.tungay +"' AND NPP_E.MA = ( SELECT DISTINCT MA FROM NHAPHANPHOI WHERE PK_SEQ = ( select NPP_FK from ERP_DONDATHANGNPP where  PK_SEQ = '"+ this.id +"' )) "+
				" 	 GROUP BY SP_E.MA, NPP_E.MA "+
				" ) AS K "+

				"\n select a.stt, b.MA, " +
				 " K.AVAILABLE * dbo.[LayQuyCach_DVBan](a.sanpham_fk, null, a.dvdl_fk ) as soluongton, "+
				//+ "(select kho.available from nhapp_kho kho where kho.sanpham_fk=b.pk_seq and kho.KHO_FK= " + this.getKhoNhapId() + " and NPP_FK in(select NPP_FK from ERP_DONDATHANGNPP where  PK_SEQ=a.dondathang_fk) and kho.KBH_FK="+ (this.dungchungKENH.equals("1") ? "100025" : this.kbhId) + " )  *dbo.[LayQuyCach_DVBan](a.sanpham_fk, null, a.dvdl_fk ) as soluongton,"+
				"\n isnull(a.sanphamTEN, b.TEN) as TEN, DV.donvi, a.soluong, a.dongia, isnull(a.chietkhau, 0) as chietkhau, ISNULL(b.trongluong, 0) as trongluong, ISNULL(b.thetich, 0) as thetich, a.tungay, a.denngay, a.thueVAT    "+
				"\n --,(select soluong1/ soluong2 from QUYCACH where sanpham_fk=a.sanpham_fk and DVDL1_FK=b.DVDL_FK and DVDL2_FK=100018)   as spQuyDoi" +
				"\n ,[dbo].[LayQuyCach](a.sanpham_fk, null , a.dvdl_fk ) spQuyDoi  "+
				"\n from ERP_DONDATHANGNPP_SANPHAM a "
				+ " inner Join SanPham b on a.SANPHAM_FK = b.PK_SEQ " +
				" OUTER APPLY ( SELECT * FROM #KHO WHERE SPMA = b.MA ) AS K "+
				"\n INNER JOIN DONVIDOLUONG DV ON DV.PK_SEQ = a.DVDL_FK       " +
				"\n where a.DONDATHANG_FK = '" + this.id + "' order by stt "; 
		}
		else
		{
			query = 
				"\n select a.stt, b.MA,(select kho.available from nhapp_kho kho where kho.sanpham_fk=b.pk_seq and kho.KHO_FK= " + this.getKhoNhapId() + " and NPP_FK in(select NPP_FK from ERP_DONDATHANGNPP where  PK_SEQ=a.dondathang_fk) and kho.KBH_FK="+ (this.dungchungKENH.equals("1") ? "100025" : this.kbhId) + " )  *dbo.[LayQuyCach_DVBan](a.sanpham_fk, null, a.dvdl_fk ) as soluongton,"+
				"\n isnull(a.sanphamTEN, b.TEN) as TEN, DV.donvi, a.soluong, a.dongia, isnull(a.chietkhau, 0) as chietkhau, ISNULL(b.trongluong, 0) as trongluong, ISNULL(b.thetich, 0) as thetich, a.tungay, a.denngay, a.thueVAT    "+
				"\n --,(select soluong1/ soluong2 from QUYCACH where sanpham_fk=a.sanpham_fk and DVDL1_FK=b.DVDL_FK and DVDL2_FK=100018)   as spQuyDoi" +
				"\n ,[dbo].[LayQuyCach](a.sanpham_fk, null , a.dvdl_fk ) spQuyDoi  "+
				"\n from ERP_DONDATHANGNPP_SANPHAM a inner Join SanPham b on a.SANPHAM_FK = b.PK_SEQ " +
				"\n INNER JOIN DONVIDOLUONG DV ON DV.PK_SEQ = a.DVDL_FK " +
				"\n where a.DONDATHANG_FK = '" + this.id + "' order by stt ";
		}
			
		String query1 = "";
		System.out.println("---INIT SP_1: " + query);
		System.out.println("---INIT SP_2: " + query1);
		System.out.println("nha phan phoi id:");
		ResultSet spRs = db.get(query);
		
		NumberFormat formater = new DecimalFormat("##,###,###");
		NumberFormat formater2 = new DecimalFormat("##,###,###.##");
		
		if(spRs != null)
		{
			try 
			{
				String spSTT = "";
				String spMA = "";
				String spTEN = "";
				String spDONVI = "";
				String spSOLUONG = "";
				String spGIANHAP = "";
				String spCHIETKHAU = "";
				String spVAT = "";
				String spTUNGAY = "";
				String spDENNGAY = "";
				
				String spTRONGLUONG = "";
				String spTHETICH = "";
				
				String spQuyDoi ="";
				String spSOLUONGTON = "";
				while(spRs.next())
				{
					spSTT += spRs.getString("stt") + "__";
					spMA += spRs.getString("MA") + "__";
					spTEN += spRs.getString("TEN") + "__";
					spDONVI += spRs.getString("DONVI") + "__";
					spSOLUONG += formater.format(spRs.getDouble("SOLUONG")) + "__";
					spGIANHAP += spRs.getDouble("DONGIA") + "__";
					spCHIETKHAU += formater.format(spRs.getDouble("chietkhau")) + "__";
					spVAT += formater.format(spRs.getDouble("thueVAT")) + "__";
					spSOLUONGTON += formater.format(spRs.getDouble("soluongton")) + "__";
					if(spRs.getString("tungay").trim().length() > 0)
						spTUNGAY += spRs.getString("tungay") + "__";
					else
						spTUNGAY += " __";
					
					if(spRs.getString("denngay").trim().length() > 0)
						spDENNGAY += spRs.getString("denngay") + "__";
					else
						spDENNGAY += " __";
					
					spTRONGLUONG += spRs.getString("trongluong") + "__";
					spTHETICH += spRs.getString("thetich") + "__";
					spQuyDoi +=spRs.getString("spQuyDoi") + "__";
				}
				spRs.close();
				
				if(spMA.trim().length() > 0)
				{
					spSTT = spSTT.substring(0, spSTT.length() - 2);
					this.spStt = spSTT.split("__");
					
					spMA = spMA.substring(0, spMA.length() - 2);
					this.spMa = spMA.split("__");
					
					spTEN = spTEN.substring(0, spTEN.length() - 2);
					this.spTen = spTEN.split("__");
					
					spDONVI = spDONVI.substring(0, spDONVI.length() - 2);
					this.spDonvi = spDONVI.split("__");
					
					spSOLUONG = spSOLUONG.substring(0, spSOLUONG.length() - 2);
					this.spSoluong = spSOLUONG.split("__");
					
					spSOLUONGTON = spSOLUONGTON.substring(0, spSOLUONGTON.length() - 2);
					this.spSoluongton = spSOLUONGTON.split("__");

					spGIANHAP = spGIANHAP.substring(0, spGIANHAP.length() - 2);
					this.spGianhap = spGIANHAP.split("__");
					
					spCHIETKHAU = spCHIETKHAU.substring(0, spCHIETKHAU.length() - 2);
					this.spChietkhau = spCHIETKHAU.split("__");
					
					spVAT = spVAT.substring(0, spVAT.length() - 2);
					this.spVAT = spVAT.split("__");
					
					spTUNGAY = spTUNGAY.substring(0, spTUNGAY.length() - 2);
					this.spTungay = spTUNGAY.split("__");
					
					spDENNGAY = spDENNGAY.substring(0, spDENNGAY.length() - 2);
					this.spDenngay = spDENNGAY.split("__");
					
					spTRONGLUONG = spTRONGLUONG.substring(0, spTRONGLUONG.length() - 2);
					this.spTrongluong = spTRONGLUONG.split("__");
					
					spTHETICH = spTHETICH.substring(0, spTHETICH.length() - 2);
					this.spThetich = spTHETICH.split("__");
					
					spQuyDoi = spQuyDoi.substring(0, spQuyDoi.length() - 2);
					this.spQuyDoi = spQuyDoi.split("__");
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
		String query = "select ISNULL( cast(( select pk_seq from ERP_HOPDONGNPP where pk_seq = a.hopdong_fk  ) as nvarchar), '') as mahopdong, ngaydonhang as tungay, ngaydenghi as denngay, ISNULL(ghichu, '') as ghichu, dvkd_fk, kbh_fk, gsbh_fk, ddkd_fk, npp_fk, khachhang_fk, kho_fk, isnull(chietkhau, 0) as chietkhau, vat, loaidonhang, " +
					" Isnull( ( select dungchungkenh from NHAPHANPHOI where pk_seq = a.npp_fk ), 0 ) as dungchungkenh, isnull(chietkhau, 0) as chietkhau,trangthai " +
						"from ERP_DONDATHANGNPP a where pk_seq = '" + this.id + "'";
		System.out.println("____INIT NHAP KHO: " + query);
		ResultSet rs = db.get(query);
		if(rs != null)
		{
			try 
			{
				if(rs.next())
				{
					this.ma = rs.getString("mahopdong");
					this.tungay = rs.getString("tungay");
					this.denngay = rs.getString("denngay");
					this.ghichu = rs.getString("ghichu");
					this.dvkdId = rs.getString("dvkd_fk");
					this.kbhId = rs.getString("kbh_fk");
					
					if(rs.getString("khachhang_fk") != null)
						this.khId = rs.getString("khachhang_fk");
					
					this.khoNhanId = rs.getString("kho_fk");
					this.loaidonhang = rs.getString("loaidonhang");
					this.chietkhau = rs.getString("chietkhau");
					this.vat = rs.getString("vat");
					this.gsbhId = rs.getString("gsbh_fk");
					this.ddkdId = rs.getString("ddkd_fk");
					this.trangthai = rs.getString("trangthai");
					// System.out.println("\n trang thai -------------" +
					// this.trangthai);
					if (rs.getString("mahopdong").trim().length() > 0)
						this.chietkhau = "0";
					
					this.dungchungKENH = rs.getString("dungchungkenh");

				}
				rs.close();
			} 
			catch (Exception e) 
			{
				System.out.println("---LOI INIT: " + e.getMessage());
			}
		}

		this.createRs();
		this.initSANPHAM(request);
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

	public String[] getSpStt() {
		
		return this.spStt;
	}

	
	public void setSpStt(String[] spStt) {
		
		this.spStt = spStt;
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
		
		System.out.println("---VAT LA: " + this.vat);
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

	public ResultSet getDvtRs() {

		return this.dvtRs;
	}


	public void setDvtRs(ResultSet dvtRs) {
		
		this.dvtRs = dvtRs;
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

	
	public String[] getSpTrongluong() {
		
		return this.spTrongluong;
	}

	
	public void setSpTrongluong(String[] spTrongluong) {
		
		this.spTrongluong = spTrongluong;
	}

	
	public String[] getSpThetich() {
		
		return this.spThetich;
	}

	
	public void setSpThetich(String[] spThetich) {
		
		this.spThetich = spThetich;
	}

	public String[] getSpQuyDoi()
	{
		return spQuyDoi;
	}
	
	public void setSpQuyDoi(String[] spQuyDoi)
	{
		this.spQuyDoi =spQuyDoi;
	}

	
	public String getTungay() {
		
		return this.tungay;
	}

	
	public void setTungay(String tungay) {
		
		this.tungay = tungay;
	}

	
	public String getDenngay() {
		
		return this.denngay;
	}

	
	public void setDenngay(String denngay) {
		
		this.denngay = denngay;
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
	
	public String[] getSpTungay() {
		
		return this.spTungay;
	}

	
	public void setSpTungay(String[] spTungay) {
		
		this.spTungay = spTungay;
	}

	
	public String[] getSpDenngay() {
		
		return this.spDenngay;
	}

	
	public void setSpDenngay(String[] spDenngay) {
		
		this.spDenngay = spDenngay;
	}

	
	public String getMahopdong() {
		
		return this.ma;
	}

	
	public void setMahopdong(String ma) {
		
		this.ma = ma;
	}

	
	public String getGsbhId() {
		
		return this.gsbhId;
	}

	
	public void setGsbhId(String gsbhId) {
		
		this.gsbhId = gsbhId;
	}

	
	public ResultSet getGsbhRs() {
		
		return this.gsbhRs;
	}

	
	public void setGsbhRs(ResultSet gsbhRs) {
		
		this.gsbhRs = gsbhRs;
	}

	
	public String getDdkdId() {
		
		return this.ddkdId;
	}

	
	public void setDdkdId(String ddkdId) {
		
		this.ddkdId = ddkdId;
	}

	
	public ResultSet getDdkdRs() {
		
		return this.ddkdRs;
	}

	
	public void setDddkdRs(ResultSet ddkdRs) {
		
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
	
	private void getNppInfo()
	{		
		//Phien ban moi
		geso.dms.distributor.util.Utility util=new geso.dms.distributor.util.Utility();
		this.nppId=util.getIdNhapp(this.userId);
		this.nppTen=util.getTenNhaPP();
		//this.dangnhap = util.getDangNhap();
		this.sitecode=util.getSitecode();
	}

	
	public boolean duyetETC() 
	{
		try
		{
			db.update("SET TRANSACTION ISOLATION LEVEL SNAPSHOT;");
			db.update("SET LOCK_TIMEOUT 60000;");
		   
			db.getConnection().setAutoCommit(false);
			
			//NEU CO DOI NGAY THI GHI NHAN LAI
			String query = " Update ERP_DondathangNPP set ngaydonhang = '" + this.tungay +  "', ngaydenghi = '" + this.denngay + "', ghichu = N'" + this.ghichu + "' " +
						   "where pk_seq = '" + this.id + "' ";
			if(!db.update(query))
			{
				this.msg = "Lỗi khi duyệt: " + query;
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
			
			
			String _kbh_fk="";

			query= " select a.sanpham_fk,a.solo,a.soluong,b.Kho_FK,(select case when DUNGCHUNGKENH=1 Then 100025 else b.KBH_FK end  from nhaphanphoi where PK_SEQ=kho.NPP_FK) as kbh_fk,a.ngayhethan,a.ngaynhapkho,b.NPP_FK, \n"+
				   "	round(case when c.dvdl_fk != a.DVDL_FK  \n"+
				   "	then ( select soluong1 / soluong2 from QUYCACH where SANPHAM_FK = c.PK_SEQ  \n"+
				   "	and DVDL1_FK = c.DVDL_FK and DVDL2_FK = a.DVDL_FK ) * a.soluong else a.soluong end,1) as luongdat \n"+
				   "	 from ERP_DONDATHANGNPP_SANPHAM_CHITIET a inner join ERP_DONDATHANGNPP b \n"+
				   "	on a.dondathang_fk=b.PK_SEQ \n"+
				   "	inner join SANPHAM c on c.PK_SEQ=a.SANPHAM_FK \n"+
				   "	inner join NHAPP_KHO_CHITIET kho on kho.NPP_FK=b.NPP_FK \n"+
				   "	and kho.KBH_FK=(select case when DUNGCHUNGKENH=1 Then 100025 else b.KBH_FK end  from nhaphanphoi where PK_SEQ=kho.NPP_FK) and kho.KHO_FK=b.Kho_FK and kho.SANPHAM_FK=a.SANPHAM_FK \n"+
				   "	and kho.SOLO=a.solo and kho.NGAYHETHAN=a.ngayhethan and kho.NGAYNHAPKHO=a.ngaynhapkho \n"+
				   "	where b.PK_SEQ="+this.id+"";
			System.out.println("nha kho ne "+query);
			ResultSet rsnhakho=db.get(query);
			while (rsnhakho.next())
			{
				String _kho_fk=rsnhakho.getString("kho_fk");
				String _sanpham_fk=rsnhakho.getString("sanpham_fk");
				String _npp_fk=rsnhakho.getString("npp_fk");
				  _kbh_fk= rsnhakho.getString("kbh_fk");
				String _solo= rsnhakho.getString("solo");
				String _ngayhethan=rsnhakho.getString("ngayhethan");
				String _ngaynhankho=rsnhakho.getString("ngaynhapkho");
				double _soluong=rsnhakho.getDouble("luongdat");
				String kq1=util.Update_NPP_Kho_Sp_Chitiet(this.tungay, "ErpDonhangNppETC.java 2369 ID: "+this.id, db, _kho_fk, _sanpham_fk,_npp_fk ,_kbh_fk, _solo,_ngayhethan ,_ngaynhankho , 0, (-1)*_soluong, _soluong, 0, 0);
				if(kq1.length()>0)
				{
					this.msg = "Lỗi khi nha kho 2582: " + query;
					db.getConnection().rollback();
					db.getConnection().setAutoCommit(true);
					return false;
				}
			}
			rsnhakho.close();
			
			
			query = "delete ERP_DONDATHANGNPP_SANPHAM_CHITIET where dondathang_fk = '" + this.id + "' ";
			if(db.updateReturnInt(query)<=0)
			{
				this.msg = "Lỗi khi duyệt: " + query;
				db.getConnection().rollback();
				db.getConnection().setAutoCommit(true);
				return false;
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
						
						while(keys.hasMoreElements())
						{
							String key = keys.nextElement();
							
							if(key.startsWith( spMa[i]) )
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
								query = "insert ERP_DONDATHANGNPP_SANPHAM_CHITIET( dondathang_fk, SANPHAM_FK, dvdl_fk, solo, soluong, ngayhethan,ngaynhapkho )  " +
										"select '" + this.id + "', pk_seq, ( select dvdl_fk from ERP_DONDATHANGNPP_SANPHAM where dondathang_fk = '" + this.id + "' and sanpham_fk = a.pk_seq ),  N'" + _sp[1] + "' as solo, '" + _soluongCT.replaceAll(",", "") + "' as soluong, '"+_sp[2]+"' as NgayHetHan ,'"+_sp[3]+"' as ngaynhapkho   " +
										"from SANPHAM a where MA = '" + spMa[i] + "'  ";
							
								
								System.out.println("1.2.Insert DDH - SP - CT: " + query);
								if(!db.update(query))
								{
									this.msg = "Khong the tao moi ERP_DONDATHANGNPP_SANPHAM_CHITIET: " + query;
									db.getConnection().rollback();
									return false;
								}
								
								double soluongqd=0;
								/* soluongquydoi="( select (select dvdl_fk from ERP_DONDATHANGNPP_SANPHAM where dondathang_fk = '" + this.id + "' and sanpham_fk = a.pk_seq) as dvdl_fk,(select case when dungchungkenh=1 then 100025 else '"+kbhId+"' end as kbh_fk from nhaphanphoi where pk_seq='"+this.nppId+"'),a.sanpham_fk,case when b.DVDL_FK=" + _sp[1] + " then 1 else ( select soluong1 / soluong2 from QUYCACH where SANPHAM_FK = b.PK_SEQ "+
										 "		 and DVDL1_FK = b.DVDL_FK and DVDL2_FK = (select dvdl_fk from ERP_DONDATHANGNPP_SANPHAM where dondathang_fk = '" + this.id + "' and sanpham_fk = a.pk_seq)   end  * " + _soluongCT.replaceAll(",", "") + " soluongxuat from QUYCACH a inner join SANPHAM b "+
										 " 	on a.DVDL1_FK=b.DVDL_FK and a.SANPHAM_FK=b.PK_SEQ where  a.SANPHAM_FK=(select pk_seq from sanpham where ma='"+ spMa[i] +"')  and a.DVDL2_FK=(select dvdl_fk from ERP_DONDATHANGNPP_SANPHAM where dondathang_fk = '" + this.id + "' and sanpham_fk = a.pk_seq))" ;
								*/
								/* String soluongquydoi=" ( select (select dvdl_fk from ERP_DONDATHANGNPP_SANPHAM where dondathang_fk = '" + this.id + "' and sanpham_fk = b.pk_seq) as dvdl_fk,"+
												  "	\n	(select case when dungchungkenh=1 then 100025 else '100052' end as kbh_fk from nhaphanphoi where pk_seq="+this.nppId+") as kbh_fk,a.sanpham_fk,case when b.DVDL_FK=(select dvdl_fk from ERP_DONDATHANGNPP_SANPHAM where dondathang_fk = '" + this.id + "' and sanpham_fk = b.pk_seq) "+
												  "	\n	 then 1 else ( select soluong1 / soluong2 from QUYCACH where SANPHAM_FK = b.PK_SEQ  "+
												  "	\n			 and DVDL1_FK = b.DVDL_FK  "+
												  "	\n			 and DVDL2_FK = (select dvdl_fk from ERP_DONDATHANGNPP_SANPHAM  "+
												  "	\n			 where dondathang_fk = '"+this.id+"' and sanpham_fk = b.pk_seq) )    end   * " + _soluongCT.replaceAll(",", "") + "as soluongxuat  "+
												  "	\n			 from QUYCACH a inner join SANPHAM b  "+ 
												  "	\n	 	on a.DVDL1_FK=b.DVDL_FK and a.SANPHAM_FK=b.PK_SEQ where  a.SANPHAM_FK=(select pk_seq from sanpham where ma='" + spMa[i] + "')  "+
												  "	\n	 	and a.DVDL2_FK=(select dvdl_fk from ERP_DONDATHANGNPP_SANPHAM  "+
												  "	\n	 	where dondathang_fk = '"+this.id+"' and sanpham_fk = b.pk_seq)"
												  ")" ;*/
								 
								 String soluongquydoi=" select sp.pk_seq as sanpham_fk, "+_kbh_fk+" as kbh_fk " +
										 ", (select dvdl_fk from ERP_DONDATHANGNPP_SANPHAM where dondathang_fk = '" + this.id + "' and sanpham_fk = sp.pk_seq) as dvdl_fk "+
								 		 ", case when sp.dvdl_fk != dv.pk_seq   	\n" +
										  "	 then ISNULL( ( select soluong1 / soluong2 from QUYCACH where SANPHAM_FK = sp.PK_SEQ  \n" +
										  "	 and DVDL1_FK = sp.DVDL_FK and DVDL2_FK = dv.pk_seq ), -1 ) else 1 end * " + _soluongCT.replaceAll(",", "") + "  as soluongquydoi    \n" +
										  "	 from SANPHAM sp, DONVIDOLUONG dv where sp.pk_seq = (select pk_seq from sanpham where ma='" + spMa[i] + "') " +
										  " and dv.pk_seq =(select dvdl_fk from ERP_DONDATHANGNPP_SANPHAM where dondathang_fk = '" + this.id + "' and sanpham_fk = sp.pk_seq )    "; 

								
								System.out.println("loi ne ba con "+soluongquydoi);
								String _sanpham_fk="";
								 
								String _dvdl="";
								ResultSet rsk=db.get(soluongquydoi);
								if(rsk.next())
								{
									soluongqd=rsk.getFloat("soluongquydoi");
									_sanpham_fk=rsk.getString("sanpham_fk");
									_kbh_fk=rsk.getString("kbh_fk");
									_dvdl=rsk.getString("dvdl_fk");
								}
								else
								{
									this.msg="loi de xuat lo save dh 1.1"+ query;
									db.getConnection().rollback();
									db.getConnection().setAutoCommit(true);
									return false;
								}
								
								String kq1=	util.Update_NPP_Kho_Sp_Chitiet(this.tungay,"duyet ETC 2636 ", db, this.khoNhanId, _sanpham_fk, this.nppId, _kbh_fk,_sp[1] , _sp[2], _sp[3], 0, soluongqd, (-1)*soluongqd, 0, 0);
								if(kq1.length()>0)
								{
									this.msg="loi de xuat lo save dh 2684"+ kq1;
									db.getConnection().rollback();
									db.getConnection().setAutoCommit(true);
									return false;
								}
								
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
			System.out.println("_++++++++++++"+query);
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
			
			
			//CHECK TONG KHO CHI TIET PHAI BANG KHO TONG
			query = "select khachhang_fk, a.kbh_fk, a.npp_fk, a.npp_dat_fk, " +
						"( select priandsecond from NHAPHANPHOI where pk_seq = a.npp_fk ) as tuxuatOTC,  " +
						"( select tuxuatETC from NHAPHANPHOI where pk_seq = a.npp_fk ) as tuxuatETC,  " +
						"( select loaiNPP from NHAPHANPHOI where pk_seq = a.npp_fk ) as loaiNPP, " +
						"( select tructhuoc_fk from NHAPHANPHOI where pk_seq = a.npp_fk ) as tructhuoc_fk,  " +
						" ISNULL( ( select dungchungkenh from NHAPHANPHOI where pk_seq = a.npp_fk ), 0 ) as dungchungkenh, a.kho_fk, a.ngaydonhang  " +
					"from ERP_DondathangNPP a where a.pk_seq = '" + id + "' order by pk_seq desc";
			
			System.out.println("---CHECK DON HANG: " + query);
	
			String khachhangID = "";
			String loaiNPP = "";
			String tructhuoc = "";
			String nppId = "";
			String npp_dat_fk = "";
			String kbh_fk = "";
			String khonhanID = "";
			
			String tuquanlyKHO_OTC = "0";
			String tuquanlyKHO_ETC = "0";
			String ngaydonhang = "";
			
			ResultSet rs = db.get(query);
			if(rs != null)
			{
				if(rs.next())
				{
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
					ngaydonhang = rs.getString("ngaydonhang");
				}
				rs.close();
			}
			
			
			// Nếu là khách hàng ETC của CN2, đối tác thì cấp trên xuât kho
			// (---> neu co check la tu quan ly kho thi tru luon cua no)
			//if( khachhangID.trim().length() > 0 && ( loaiNPP.equals("1") || loaiNPP.equals("4") ) && tuquanlyKHO_OTC.equals("0") )
			if( ( khachhangID.trim().length() > 0 && tuquanlyKHO_ETC.equals("0") )  )
			{
				if (tructhuoc.trim().length() >= 5) // TRỰC THUỘC NPP
				{
					msg = this.TaoPhieuXuatKho_CapTren_NPP(db, id, userId, khonhanID, nppId, tructhuoc, kbh_fk );  //100000 la kho hang ban cua NPP
					if(msg.trim().length() > 0)
					{
						msg = "Khong the chot: " + msg;
						db.getConnection().rollback();
						return false;
					}
				}
				else // PHIẾU YCXK của HO
				{
					msg = this.TaoPhieuXuatKho_CapTren_HO(db, id, userId, khonhanID, nppId, tructhuoc, kbh_fk ); //100001 la kho TT cua HO
					if(msg.trim().length() > 0)
					{
						msg = "Khong the chot: " + msg;
						db.getConnection().rollback();
						return false;
					}
				}
			}
			else // Tạo PXK cho NPP (NPP TU QUAN LY TON KHO)
			{
				/*msg = this.TaoPhieuXuatKhoNPP(db, id, userId, khonhanID, nppId, npp_dat_fk, kbh_fk );
				if(msg.trim().length() > 0)
				{
					msg = "Khong the chot: " + msg;
					db.getConnection().rollback();
					return false;
				}	*/		
			}
			
			query = "update ERP_DondathangNPP set trangthai = '4', NPP_DACHOT = '1', nguoisua = '" + userId + "' where pk_seq = '" + id + "'";
			if(!db.update(query))
			{
				msg = "Khong the chot: " + query;
				db.getConnection().rollback();
				return false;
			}
			
			// KIỂM TRA CN/DOITAC CHECK TUTAOHOADON : TỰ ĐỘNG XUẤT HÓA ĐƠN TÀI
			// CHÍNH
			query = " select count(pk_seq) as dem from NHAPHANPHOI where pk_seq= '"+ this.nppId +"'  and tutaohoadon = '1'  ";
			System.out.println("Cau kiem tra "+query);
			ResultSet ktraNPP = db.get(query);
			int dem= 0;
			if(ktraNPP!= null)
			{
				while(ktraNPP.next())
				{
					dem = ktraNPP.getInt("dem");
				}
				ktraNPP.close();
			}
			
			if(dem > 0)
			{
				 // Tu dong tao Hoa don tai chinh cho NPP					
				msg = this.TaoHoaDonTaiChinhNPP(db, id, userId, nppId, khachhangID, ngaydonhang);
				if(msg.trim().length() > 0)
				{
					msg = "Khong the tao hoa don tai chinh: " + msg;
					db.getConnection().rollback();
					return false;
				}
			}
			
			msg= util.Check_Kho_Tong_VS_KhoCT(nppId, db);
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
	
	private String TaoHoaDonTaiChinhNPP(dbutils db, String id, String userId, String nppId, String khachhangID, String ngaydonhang) 
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
		 	
			String kyhieuhoadon="";
			String sohoadon="";
			String ngayhoadon = "";
			String mau = "";
			
			System.out.println("::: USerID: " + userId + " :: NPP ID: " + nppId);
			String[] info = util.LayThongTinSHD(db, userId, nppId, ngaydonhang, khachhangID);
			if( info.length < 3 )
				return info[0];
			
			kyhieuhoadon = info[0];
			//sohoadon = "";
			sohoadon = info[1];
			ngayhoadon = ngaydonhang;
			mau = info[3];
			if(db.updateReturnInt(query) <= 0 )
			{
				msg1 = "Khoong laay duoc so hoa don!";
				return msg1;
			}	
			
			
			// LAY TIEN DE LUU
			double tienck= 0;
			double tienbvat= 0;
			double tienavat= 0;
			String nguoimua ="";
				
			query = " select (case when dh.khachhang_fk is not null then " +
					"                               (select isnull(chucuahieu,'') from KHACHHANG where pk_seq = dh.khachhang_fk ) " +
					"             else '' end ) as nguoimua  ," +
					"        dh_sp.chietkhau, dh_sp.bvat , (dh_sp.bvat + dh.Vat) as AVAT "+
					" from ERP_DONDATHANGNPP dh inner join  "+
					"	(select a.dondathang_fk, SUM(a.chietkhau)as chietkhau , sum(a.soluong * a.dongia - a.chietkhau) as bvat "+
					"	from ERP_DONDATHANGNPP_SANPHAM a   "+
					"	group by  a.dondathang_fk ) dh_sp on dh.PK_SEQ = dh_sp.dondathang_fk "+
					" where dh.PK_SEQ = "+ id +"  ";
			
			ResultSet rsLayTien = db.get(query);
			{
				while(rsLayTien.next())
				{
					tienck = rsLayTien.getDouble("chietkhau");
					tienbvat = rsLayTien.getDouble("bvat");
					tienavat = rsLayTien.getDouble("avat");
					nguoimua =  rsLayTien.getString("nguoimua");
					
				}rsLayTien.close();
			}
		
			query =   " insert ERP_HOADONNPP(DDKD_FK,KBH_fK,KHO_FK,nguoimua ,ngayxuatHD, trangthai, ngaytao, nguoitao, ngaysua, nguoisua, kyhieu, sohoadon, hinhthuctt , \n" +
				       "        chietkhau, tongtienbvat, tongtienavat, vat, ghichu, loaixuathd, npp_fk, khachhang_fk, mauhoadon,TENKHACHHANG,DIACHI,MASOTHUE) \n" +
					   " SELECT DH.ddkd_Fk, DH.kbh_Fk, DH.kho_fk, N'" + nguoimua + "', '" + ngayhoadon + "', '1','" + getDateTime() + "', '" + userId + "', '" + getDateTime() + "', '" + userId + "', '" + kyhieuhoadon + "', \n" +
					   "       '" + sohoadon + "', N'TM/CK' , '"+ tienck  +"', '"+ tienbvat +"', '"+ tienavat  +"', \n" +
					   "       '" + this.vat.replaceAll(",", "") + "', N'Phiếu xuất hóa đơn từ động từ đơn hàng " + id + " ', '1', '" + nppId + "', " + khachhangID + ", " + mau + " \n" +
					   " 		, KH.TEN as tenkh \n" +
					   " 		, ISNULL(KH.DIACHI,'') as diachikh \n" +
					   " 		, ISNULL(KH.MASOTHUE,'')  as mst \n" +
					   " FROM Erp_DonDatHangNPP DH inner join KHACHHANG KH ON DH.KHACHHANG_FK = KH.PK_SEQ \n" +
					   " WHERE DH.PK_SEQ = '"+ id +"' ";
								 
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
			
			query = "insert ERP_HOADONNPP_SP( hoadon_fk, sanpham_fk, sanphamTEN, donvitinh, soluong, dongia, thanhtien, chietkhau, scheme , vat,soluong_chuan,dongia_chuan) \n" +
					" SELECT "+ hdId +", \n" +
					"       b.PK_SEQ, a.sanphamTEN, DV.donvi, SUM( a.soluong), a.dongia, SUM( a.soluong) * a.dongia ,SUM( isnull(a.chietkhau, 0)), \n" +
					"       isnull(scheme,' ') , isnull(a.thuevat,0) as vat  ,	a.soluong * dbo.LayQuyCach( a.SANPHAM_FK, null, a.DVDL_FK ) as SoLuong_Chuan,  \n" + 
					"  	a.dongia * dbo.LayQuyCach_DVBan( a.SANPHAM_FK, null, a.DVDL_FK ) as DonGia_Chuan  \n" +
					" FROM ERP_DONDATHANGNPP_SANPHAM a INNER JOIN SanPham b on a.SANPHAM_FK = b.PK_SEQ   \n" +  	 
					"                                  INNER JOIN DONVIDOLUONG DV ON DV.PK_SEQ = a.DVDL_FK  \n" +
					"                                  INNER JOIN  ERP_DONDATHANGNPP c on a.dondathang_fk=c.pk_seq    \n" +
					" WHERE a.dondathang_fk in ( "+ id +" ) and a.dondathang_fk in (select pk_seq from ERP_DONDATHANGNPP where NPP_FK="+ nppId +") and a.soluong > 0  \n" +
					" GROUP BY b.PK_SEQ , a.sanphamTEN, DV.donvi, a.dongia , isnull(scheme,' ') , isnull(a.thuevat,0),a.soluong ,a.dvdl_fk,a.sanpham_fk ";
			
			System.out.println("1.1.Insert ERP_HOADONNPP_SP: " + query);
			if(db.updateReturnInt(query) <= 0 )
			{
				msg1 = "Khong the tao moi ERP_HOADONNPP_SP: " + query;
				return msg1;
			}
			
			query = "select npp_fk from  ERP_HOADONNPP  where pk_seq = '" + hdId + "' ";
			ResultSet rs=db.get(query);
			String npp="";
			if(rs.next())
			{
				npp=rs.getString("npp_fk");
			}
			rs.close();

			
			query = "Insert ERP_HOADONNPP_DDH(hoadonnpp_fk, ddh_fk) " +
					" values( "+ hdId +",  " + id + "  )";
			if(db.updateReturnInt(query) <= 0 )
			{
				msg1 = "Không thể tạo mới ERP_HOADONNPP_DDH " + query;
				return msg1;
			}
			

			
			query = "  insert ERP_HOADONNPP_SP_CHITIET(sanpham_fk,hoadon_fk, donhang_fk, KBH_FK, Kho_FK, MA, TEN, DONVI, DVCHUAN, DVDATHANG, SOLUONG, SOLO, NGAYHETHAN, NGAYNHAPKHO, CHIETKHAU, THUEVAT, DONGIA,SoLuong_Chuan,DonGia_Chuan,SoLuong_DatHang)   " + 
					"  select b.SANPHAM_FK,'" + hdId + "' as hoadon_fk, a.pk_seq as donhang_fk, a.KBH_FK, a.KHO_FK, c.MA, isnull(dhsp.sanphamTEN ,c.TEN ) as TEN, (select donvi from DONVIDOLUONG where pk_seq = dhsp.dvdl_fk ) as donvi, d.pk_seq as dvCHUAN, dhsp.dvdl_fk  as dvDATHANG,    " + 
					"  	b.soluong,    " + 
					"  	b.solo, b.NGAYHETHAN, NGAYNHAPKHO, dhsp.chietkhau, dhsp.thuevat,   " + 
					"  	dhsp.dongia,   " + 
					"  	b.soluong * dbo.LayQuyCach( b.SANPHAM_FK, null, b.DVDL_FK ) as SoLuong_Chuan,   " + 
					"  	dhsp.dongia * dbo.LayQuyCach_DVBan( b.SANPHAM_FK, null, b.DVDL_FK ) as DonGia_Chuan,   " + 
					"  	dhsp.soluong as SoLuong_DatHang   " + 
					"  from ERP_DONDATHANGNPP a inner join ERP_DONDATHANGNPP_SANPHAM_CHITIET b on a.pk_seq = b.dondathang_fk	  								   " + 
					"       inner join ERP_DONDATHANGNPP_SANPHAM dhsp on dhsp.dondathang_fk = a.PK_SEQ and dhsp.sanpham_fk = b.sanpham_fk	   " + 
					"       inner join SANPHAM c on dhsp.sanpham_fk = c.PK_SEQ  						   " + 
					"       inner join DONVIDOLUONG d on d.PK_SEQ = c.dvdl_fk 	   " + 
					"  where a.trangthai != '3' and a.PK_SEQ = '" + id + "' and b.soluong > 0  ";
			
			System.out.println("1.2.Insert ERP_HOADONNPP_SP: " + query);
			if(db.updateReturnInt(query) < 0 )
			{
				msg1 = "Không thể tạo mới ERP_HOADONNPP_SP_CHITIET " + query;
				return msg1;
			}
			
			query = "select dh.sanpham_fk,dh.soluong,xk.soluong as xksl,xk.sanpham_fk as spxl  " +
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
			
			ResultSet rsCHECK = db.get(query);
			{
				while(rsCHECK.next())
				{
					System.out.println("hoa don sp loi la"+rsCHECK.getInt("sanpham_fk") + "---"+rsCHECK.getInt("soluong") +"---"+rsCHECK.getInt("xksl") ); 
				}
				rsCHECK.close();
			}
			
			
			//CHECK BANG TONG PHAI BANG BANG CHI TIET
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
			 rsCHECK = db.get(query);
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
			
			//LUU VAO BANG CHI TIET
			
			
			//CAP NHAT LAI CAC COT TIEN CUA ETC, SAU NAY IN RA THI CHI IN TU DAY
			query = "update hd set hd.tongtienbvat = round(giatri.bVAT,0), hd.chietkhau = round(giatri.chietkhau,0),   " +
					"			hd.vat = round(giatri.vat,0), hd.tongtienavat = round(giatri.avat,0)  " +
					"from ERP_HOADONNPP hd inner join  " +
					"(  " +
					"	select hoadonnpp_fk, sum(bVAT) as bVAT, sum(chietkhau) as chietkhau, (sum(bVAT) - sum(chietkhau)) * ((VAT/100.0)) as VAT, " +
					"			(sum(bVAT) - sum(chietkhau)) * (1+(VAT/100.0)) as aVAT  " +
					"	from  " +
					"	(  " +
					"		select a.hoadonnpp_fk,   " +
					" cast( floor(b.soluong * b.dongia) as numeric(18, 0) )  as bVAT, isnull(b.chietkhau, 0) as chietkhau,    " +
					"		thuevat as VAT " +
					"		from ERP_HOADONNPP_DDH a inner join ERP_DONDATHANGNPP_SANPHAM b on a.ddh_fk = b.dondathang_fk  " +
					"				inner join ERP_DONDATHANGNPP c on a.ddh_fk = c.pk_seq  " +
					"		where a.ddh_fk = '" + id + "'  " +
					"	)  " +
					"	etc group by hoadonnpp_fk,etc.vat  " +
					")  " +
					"giatri on hd.pk_seq = giatri.hoadonnpp_fk  ";
			if(db.updateReturnInt(query) <= 0 )
			{
				msg1 = "Không thể tạo mới ERP_HOADONNPP " + query;
				return msg1;
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
			if(db.updateReturnInt(query) <= 0)
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
			if(db.updateReturnInt(query) <= 0)
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
			/*if(rs != null)*/
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
					/*query = "Update NHAPP_KHO set soluong = soluong - '" + soluong + "', AVAILABLE = AVAILABLE - '" + soluong + "' " +
							"where KHO_FK = '100000' and KBH_FK = '" + kbhID + "' and NPP_FK = '" + nppID + "' and SANPHAM_FK = '" + spID + "' ";
		*/			Utility utility=new Utility();
					
					String kq=utility.Update_NPP_Kho_Sp(this.getDateTime(), "xuat kho duyet ETC", db, "100000", spID, nppID, kbhID,(-1)* soluong, 0, (-1)* soluong, 0);
					
					if(kq.length()>0)
					{
						msg = "Khong the cap nhat ERP_KHOTT_SANPHAM: 3427 " + kq;
						//db.getConnection().rollback();
						rs.close();
						return msg;
					}
					
					
					
					
					 query="select isnull(checkkho,0) as sl from ERP_DondathangNPP where pk_Seq="+this.id;
					 ResultSet rschk=db.get(query);
					 rschk.next();
					 if(rschk.getInt("sl")==1)
					 {
						 
						 query=" select a.PK_SEQ,a.Kho_FK,a.KBH_FK,b.SANPHAM_FK,b.solo,b.ngayhethan,b.ngaynhapkho,b.DVDL_FK \n"+
							   "		 from ERP_DONDATHANGNPP a inner join ERP_DONDATHANGNPP_SANPHAM_CHITIET b \n"+
							   "	 	on a.PK_SEQ=b.dondathang_fk \n"+
							   "		where a.PK_SEQ='"+this.id+"'";
						 
						 ResultSet layhang=db.get(query);
						 while(layhang.next())
						 {
							 String _sanpham_fk=layhang.getString("sanpham_fk");
							 String _kho_fk=layhang.getString("Kho_FK");
							 String _kbh_fk=layhang.getString("KBH_FK");
							 String _solo=layhang.getString("solo");
							 String _ngayhethan=layhang.getString("ngayhethan");
							 String _ngaynhapkho=layhang.getString("ngaynhapkho");
							 String _DVDL_FK=layhang.getString("DVDL_FK");
							 String _soluong=layhang.getString("soluong");
							 
							/* String soluongquydoi="( select case when b.DVDL_FK='"+_DVDL_FK+"' then 1 else ( select soluong1 / soluong2 from QUYCACH where SANPHAM_FK = b.PK_SEQ "+
									 "		 and DVDL1_FK = b.DVDL_FK and DVDL2_FK = '"+_DVDL_FK+"')   end  * "+_soluong+" soluongxuat from QUYCACH a inner join SANPHAM b "+
									 " 	on a.DVDL1_FK=b.DVDL_FK and a.SANPHAM_FK=b.PK_SEQ where  a.SANPHAM_FK='"+_sanpham_fk+"' )" ;
*/
							 String soluongquydoi=" select  case when sp.dvdl_fk != dv.pk_seq   	\n" +
									  "	 then ISNULL( ( select soluong1 / soluong2 from QUYCACH where SANPHAM_FK = sp.PK_SEQ  \n" +
									  "	 and DVDL1_FK = sp.DVDL_FK and DVDL2_FK = dv.pk_seq ), -1 ) else 1 end * "+_soluong+"  as soluongquydoi    \n" +
									  "	 from SANPHAM sp, DONVIDOLUONG dv where sp.pk_seq = '"+_sanpham_fk+"' and dv.pk_seq ='"+_DVDL_FK+"'   "; 

							 double soluongqd=0;
								
								
								//System.out.println("loi ne ba con "+soluongquydoi);
								ResultSet rsk=db.get(soluongquydoi);
								if(rsk.next())
								{
									soluongqd=rsk.getFloat("soluongquydoi");
								}
								else
								{
									this.msg="loi de xuat lo save dh 1.1"+ query;
									return this.msg;
								}
								System.out.println("vao day de xuat nay "+ soluongquydoi);
								//CAP NHAT KHO CHI TIET
								/*query = "update nhapp_kho_chitiet set booked = booked + " + soluongquydoi + ", AVAILABLE = AVAILABLE - " + soluongquydoi + " " +
										" where sanpham_fk = '" + sanpham_fksp + "' and npp_fk = '" + nppId + "' and kho_fk = '" + khoId + "' and kbh_fk = '" + kbhID + "' and SOLO = '" + rs1.getString("SOLO") + "' and NgayHetHan='"+NgayHetHan+"' and ngaynhapkho='"+ngaynhapkho+"' ";
							*/		
								String kq1="first";
								 kq1=utility.Update_NPP_Kho_Sp_Chitiet(this.getDateTime(), "yxck ETC npp", db, _kho_fk, _sanpham_fk, nppId, kbhID, _solo, _ngayhethan, _ngaynhapkho, (-1)*soluongqd, (-1)*soluongqd, 0, 0, 0);
								
								 if(kq1.length()>0 )
								{
									 this.msg="loi de xuat lo save dh 1.1"+ query;
									return this.msg;
								}
							 
							 
							 
						 }
							layhang.close();								 
						
					 }
					 else
					 {
					//CAP NHAT KHO CHI TIET
					query = "select AVAILABLE, SOLO, ngayhethan from NHAPP_KHO_CHITIET " +
							"where AVAILABLE > 0 and KHO_FK = '" + khoID + "'  and SANPHAM_FK = '" + spID + "' and NPP_FK = '" + nppID + "' and KBH_FK = '" + kbhID + "' order by ngayhethan asc ";
					
					ResultSet rsTK = db.get(query);
					double avai = 0;
					double totalXUAT = 0;
					while(rsTK.next())
					{
						double soluongCT = 0;
						String solo = rsTK.getString("SOLO");
						String ngayhethan = rsTK.getString("ngayhethan");
						
						avai = rsTK.getDouble("AVAILABLE");
						totalXUAT += avai;
						
						// NẾU LÀ CN HỒ CHÍ MINH: CHECK SP CÓ LÔ NA THÌ CẢNH BÁO
						// VÀ ROLLBACK LẠI (3/9/2014)
						if( nppId.equals("106210") && solo.equals("NA"))
						{							
							msg = "6a.Sản phẩm " + rs.getString("TEN") + " đang có số lô là 'NA'. Vui lòng điều chỉnh lại số lô. ";
							return msg;
						}
						
						if(totalXUAT <= soluong)
						{
							soluongCT = avai;
							
							
							query = "insert ERP_YCXUATKHONPP_SANPHAM_CHITIET( ycxk_fk, SANPHAM_FK, solo, soluong, ngayhethan ) " +
									"select '" + ycxkId + "', '" + spID + "', N'" + solo + "', '" + soluongCT  + "', '" + ngayhethan + "' ";
							
							System.out.println("1.2.Insert YCXK - SP - CT: " + query);
							if(!db.update(query))
							{
								msg = "Khong the tao moi ERP_YCXUATKHONPP_SANPHAM_CHITIET: " + query;
								//db.getConnection().rollback();
								rs.close();
								return msg;
							}
							
							query = "Update NHAPP_KHO_CHITIET set soluong = soluong - '" + soluongCT + "', AVAILABLE = AVAILABLE - '" + soluongCT + "' " +
									"where KHO_FK = '" + khoID + "' and SOLO = '" + solo + "' and SANPHAM_FK = '" + spID + "' adn KBH_FK = '" + kbhID + "' and NPP_FK = '" + nppID + "' and NgayHetHan='"+ngayhethan+"' ";
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
							
							query = "insert ERP_YCXUATKHONPP_SANPHAM_CHITIET( ycxk_fk, SANPHAM_FK, solo, soluong, ngayhethan ) " +
									"select '" + ycxkId + "', '" + spID + "', N'" + solo + "', '" + soluongCT + "', '" + ngayhethan + "' ";
							
							System.out.println("1.2.Insert YCXK - SP - CT: " + query);
							if(!db.update(query))
							{
								msg = "Khong the tao moi ERP_YCXUATKHONPP_SANPHAM_CHITIET: " + query;
								//db.getConnection().rollback();
								rs.close();
								return msg;
							}
							
							query = "Update NHAPP_KHO_CHITIET set soluong = soluong - '" + soluongCT + "', AVAILABLE = AVAILABLE - '" + soluongCT + "' " +
									"where KHO_FK = '" + khoID + "' and SOLO = '" + solo + "' and SANPHAM_FK = '" + spID + "' and KBH_FK = '" + kbhID + "' and NPP_FK = '" + nppID + "' and NgayHetHan='"+ngayhethan+"' ";
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
			
			System.out.println("--CHECK TON KHO HO: " + query);
			
			ResultSet rsCHECK = db.get(query);
			/*if(rsCHECK != null)*/
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
			if(db.updateReturnInt(query) <= 0)
			{
				msg = "Không thể tạo mới ERP_YCXUATKHO " + query;
				//db.getConnection().rollback();
				return msg;
			}
			
			query = "Insert ERP_YCXUATKHO_DDH(ycxk_fk, ddh_fk) " +
					"select IDENT_CURRENT('ERP_YCXUATKHO'), pk_seq from ERP_DONDATHANGNPP where pk_seq in ( " + id + " )  ";
			System.out.println("1.Insert YCXUATKHO - DDH: " + query);
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
						
						// NẾU LÀ CN HỒ CHÍ MINH: CHECK SP CÓ LÔ NA THÌ CẢNH BÁO
						// VÀ ROLLBACK LẠI (3/9/2014)
						if( nppId.equals("106210") && solo.equals("NA"))
						{							
							msg = "6a.Sản phẩm " + rsCHECK.getString("TEN") + " đang có số lô là 'NA'. Vui lòng điều chỉnh lại số lô. ";
							return msg;
						}
						
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
		
		try
		{
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
			/*if(rsCHECK != null)*/
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
					" select '" + this.getDateTime() + "', N'Phiếu xuất kho tạo tự động từ duyệt đơn đặt hàng " + id
					+ "', '2', '" + nppId + "', " + khoNhanId + ", " +
							" case when npp_dat_fk is not null then '0' else '1' end as xuatcho, npp_dat_fk, khachhang_fk, kbh_fk, '" + getDateTime() + "', '" + userId + "', '" + getDateTime() + "', '" + userId + "' " +
					" from ERP_DONDATHANGNPP where pk_seq = '" + id + "' ";
			
			System.out.println("1.Insert YCXUATKHO: " + query);
			if(db.updateReturnInt(query) <= 0)
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
			
			//XUAT KHO THI LUC NAO CUNG XUAT THEO DON VI CHUAN
			query = "insert ERP_YCXUATKHONPP_SANPHAM( ycxk_fk, sanpham_fk, soluongDAT, tonkho, daxuat, soluongXUAT, LOAI, SCHEME ) " +
					"select '" + ycxkId + "', sp.PK_SEQ, SUM(dathang.soluong) as soluongDAT, ISNULL( ( select AVAILABLE from ERP_KHOTT_SANPHAM where khott_fk = '" + id + "' and sanpham_fk = sp.PK_SEQ ), 0)  as tonkho, 0, SUM(dathang.soluong) as soluongXUAT, loai, scheme  " +
					"from    " +
					"(    " +
					"		select a.sanpham_fk, b.DVDL_FK as dvCHUAN,    " +
					"				case when a.dvdl_fk IS null then a.soluong     " +
					"					 when a.dvdl_fk = b.DVDL_FK then a.soluong    " +
					"					 else  a.soluong * ( select SOLUONG1 / SOLUONG2 from QUYCACH where sanpham_fk = a.sanpham_fk and DVDL2_FK = a.dvdl_fk and DVDL1_FK = b.DVDL_FK )   end as soluong, " +
					"			0 as loai, ' ' as scheme   " +
					"		from ERP_DONDATHANGNPP_SANPHAM a inner join SANPHAM b on a.sanpham_fk = b.PK_SEQ    " +
					"		where a.dondathang_fk in ( '" + id + "' ) and a.soluong > 0   " +
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
			
			
			
			//CHECK TON KHO  --> KO TU DONG DE XUAT, MA LAY SOLO NGUOI DUNG GO VAO
			/*
			 * query =
			 * "select khoxuat_fk as kho_fk, npp_fk, kbh_fk, sp.PK_SEQ, sp.TEN, SUM(dathang.soluong) as soluongXUAT,  "
			 * +
			 * "	ISNULL( ( select AVAILABLE from NHAPP_KHO where kho_fk = dathang.khoxuat_fk and sanpham_fk = sp.PK_SEQ and kbh_fk = dathang.kbh_fk and npp_fk = dathang.npp_fk ), 0) as tonkho  "
			 * + "from     " + "(     " +
			 * "	select c.kho_fk as khoxuat_fk, c.npp_fk, c.kbh_fk, a.sanpham_fk, b.DVDL_FK as dvCHUAN,     "
			 * + "			case when a.dvdl_fk IS null then a.soluong      " +
			 * "				 when a.dvdl_fk = b.DVDL_FK then a.soluong     " +
			 * "				 else  a.soluong * ( select SOLUONG1 from QUYCACH where sanpham_fk = a.sanpham_fk and DVDL2_FK = a.dvdl_fk and dvdl1_fk = b.dvdl_fk )      "
			 * +
			 * "								 / ( select SOLUONG2 from QUYCACH where sanpham_fk = a.sanpham_fk and DVDL2_FK = a.dvdl_fk and dvdl1_fk = b.dvdl_fk )	 end as soluong   "
			 * +
			 * "	from ERP_DONDATHANGNPP_SANPHAM a inner join SANPHAM b on a.sanpham_fk = b.PK_SEQ  "
			 * +
			 * "			inner join ERP_DONDATHANGNPP c on a.dondathang_fk = c.pk_seq    "
			 * + "	where a.dondathang_fk in ( " + id + " )     " + ")     " +
			 * "dathang inner join SANPHAM sp on dathang.sanpham_fk = sp.PK_SEQ   "
			 * + "group by khoxuat_fk, npp_fk, kbh_fk, sp.PK_SEQ, sp.TEN  ";
			 * 
			 * System.out.println("--CHECK TON KHO: " + query);
			 * 
			 * ResultSet rs = db.get(query); if(rs != null) { while(rs.next()) {
			 * String khoID = rs.getString("kho_fk"); String kbhID =
			 * rs.getString("kbh_fk"); String nppID = rs.getString("npp_fk");
			 * String spID = rs.getString("PK_SEQ");
			 * 
			 * double soluong = rs.getDouble("soluongXUAT"); double tonkho =
			 * rs.getDouble("tonkho");
			 * 
			 * if(soluong > tonkho) { msg = "Sản phẩm ( " + rs.getString("TEN")
			 * + " ) với số lượng yêu cầu ( " + rs.getString("soluongXUAT") +
			 * " ) không đủ tồn kho ( " + rs.getString("tonkho") +
			 * " ). Vui lòng kiểm tra lại."; //db.getConnection().rollback();
			 * rs.close(); return msg; }
			 * 
			 * //CAP NHAT KHO XUAT TONG query =
			 * "Update NHAPP_KHO set soluong = soluong - '" + soluong +
			 * "', AVAILABLE = AVAILABLE - '" + soluong + "' " +
			 * "where KHO_FK = '100000' and KBH_FK = '" + kbhID +
			 * "' and NPP_FK = '" + nppID + "' and SANPHAM_FK = '" + spID +
			 * "' "; if(!db.update(query)) { msg =
			 * "Khong the cap nhat ERP_KHOTT_SANPHAM: " + query;
			 * //db.getConnection().rollback(); rs.close(); return msg; }
			 * 
			 * 
			 * //CAP NHAT KHO CHI TIET query =
			 * "select AVAILABLE, SOLO, ngayhethan from NHAPP_KHO_CHITIET " +
			 * "where AVAILABLE > 0 and KHO_FK = '" + khoID +
			 * "'  and SANPHAM_FK = '" + spID + "' and NPP_FK = '" + nppID +
			 * "' and KBH_FK = '" + kbhID + "' order by ngayhethan asc ";
			 * 
			 * ResultSet rsTK = db.get(query); double avai = 0; double totalXUAT
			 * = 0; while(rsTK.next()) { double soluongCT = 0; String solo =
			 * rsTK.getString("SOLO"); String ngayhethan =
			 * rsTK.getString("ngayhethan");
			 * 
			 * avai = rsTK.getDouble("AVAILABLE"); totalXUAT += avai;
			 * 
			 * if(totalXUAT <= soluong) { soluongCT = avai;
			 * 
			 * query =
			 * "insert ERP_YCXUATKHONPP_SANPHAM_CHITIET( ycxk_fk, SANPHAM_FK, solo, soluong, ngayhethan ) "
			 * + "select '" + ycxkId + "', '" + spID + "', N'" + solo + "', '" +
			 * soluongCT + "', '" + ngayhethan + "' ";
			 * 
			 * System.out.println("1.2.Insert YCXK - SP - CT: " + query);
			 * if(!db.update(query)) { msg =
			 * "Khong the tao moi ERP_YCXUATKHONPP_SANPHAM_CHITIET: " + query;
			 * //db.getConnection().rollback(); rs.close(); return msg; }
			 * 
			 * query = "Update NHAPP_KHO_CHITIET set soluong = soluong - '" +
			 * soluongCT + "', AVAILABLE = AVAILABLE - '" + soluongCT + "' " +
			 * "where KHO_FK = '" + khoID + "' and SOLO = '" + solo +
			 * "' and SANPHAM_FK = '" + spID + "' adn KBH_FK = '" + kbhID +
			 * "' and NPP_FK = '" + nppID + "' "; if(!db.update(query)) { msg =
			 * "Khong the cap nhat NHAPP_KHO_CHITIET: " + query;
			 * //db.getConnection().rollback(); rs.close(); return msg; }
			 * 
			 * } else { soluongCT = soluong - ( totalXUAT - avai );
			 * 
			 * query =
			 * "insert ERP_YCXUATKHONPP_SANPHAM_CHITIET( ycxk_fk, SANPHAM_FK, solo, soluong, ngayhethan ) "
			 * + "select '" + ycxkId + "', '" + spID + "', N'" + solo + "', '" +
			 * soluongCT + "', '" + ngayhethan + "' ";
			 * 
			 * System.out.println("1.2.Insert YCXK - SP - CT: " + query);
			 * if(!db.update(query)) { msg =
			 * "Khong the tao moi ERP_YCXUATKHONPP_SANPHAM_CHITIET: " + query;
			 * //db.getConnection().rollback(); rs.close(); return msg; }
			 * 
			 * query = "Update NHAPP_KHO_CHITIET set soluong = soluong - '" +
			 * soluongCT + "', AVAILABLE = AVAILABLE - '" + soluongCT + "' " +
			 * "where KHO_FK = '" + khoID + "' and SOLO = '" + solo +
			 * "' and SANPHAM_FK = '" + spID + "' and KBH_FK = '" + kbhID +
			 * "' and NPP_FK = '" + nppID + "' "; if(!db.update(query)) { msg =
			 * "Khong the cap nhat NHAPP_KHO_CHITIET: " + query;
			 * //db.getConnection().rollback(); rs.close(); return msg; }
			 * 
			 * break; } } rsTK.close(); } rs.close(); }
			 */
			
			
			// CẬP NHẬT KHO TỔNG
			query = "update kho   " +
					"set kho.soluong = round(kho.soluong,1) - round(BOOK_KHO.soluong,1),  " +
					"	kho.booked = round(kho.booked,1) - round(BOOK_KHO.soluong,1)  " +
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
			}
			
			query = "select c.npp_fk, case when isnull(d.dungchungkenh, 0) = 0 then c.kbh_fk else 100025 end as kbh_fk, " +
					"		c.kho_fk, a.sanpham_fk, b.ten as TEN, a.soluong as soluongDAT, a.solo, a.ngayhethan,  " +
					"		case when a.dvdl_fk IS null then a.soluong      " +
					"			 when a.dvdl_fk = b.DVDL_FK then a.soluong     " +
					"			 else  a.soluong * ( select SOLUONG1 / SOLUONG2 from QUYCACH where sanpham_fk = a.sanpham_fk and DVDL2_FK = a.dvdl_fk and DVDL1_FK = b.DVDL_FK )   end as soluong,  " +
					"	0 as loai, ' ' as scheme,a.ngaynhapkho    " +
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
					double tonkho = 0;
					query = "select AVAILABLE from NHAPP_KHO_CHITIET " +
							"where kho_fk = '" + khoID + "' and sanpham_fk = '" + spID + "' and kbh_fk = '" + kbhID + "' and npp_fk = '" + nppID + "' and solo = '" + solo + "' and NgayHetHan='"+ngayhethan+"' and ngaynhapkho='"+ngaynhapkho+"'";
					System.out.println("CHECK TON KHO: " + query);
					ResultSet rsTONKHO = db.get(query);
					if(rsTONKHO != null)
					{
						if(rsTONKHO.next())
							tonkho = rsTONKHO.getDouble("AVAILABLE")+soluong;
						rsTONKHO.close();
					}
					System.out.println("so luong "+soluong +"ton kho "+tonkho);
					if(soluong > tonkho)
					{
						msg = "Sản phẩm ( " + rs.getString("TEN") + " ), số lô ( " + rs.getString("solo") + " ) với số lượng yêu cầu ( " + rs.getString("soluong") + " ) không đủ tồn kho ( " + tonkho + " ). Vui lòng kiểm tra lại.";
						//db.getConnection().rollback();
						rs.close();
						return msg;
					}

					
					//CAP NHAT KHO XUAT TONG
					/*query = "Update NHAPP_KHO set soluong = soluong - '" + soluong + "', AVAILABLE = AVAILABLE - '" + soluong + "' " +
							"where KHO_FK = '" + khoID + "' and KBH_FK = '" + kbhID + "' and NPP_FK = '" + nppID + "' and SANPHAM_FK = '" + spID + "' ";
					if(db.updateReturnInt(query)!=1)
					{
						msg = "Khong the cap nhat ERP_KHOTT_SANPHAM: " + query;
						//db.getConnection().rollback();
						rs.close();
						return msg;
					}*/
					
				/*	query = "Update NHAPP_KHO_CHITIET set soluong = soluong - '" + soluong + "', AVAILABLE = AVAILABLE - '" + soluong + "' " +
								  "where KHO_FK = '" + khoID + "' and KBH_FK = '" + kbhID + "' and NPP_FK = '" + nppID + "' and SANPHAM_FK = '" + spID + "' and SOLO = '" + solo + "' and NgayHetHan='"+ngayhethan+"' and ngaynhapkho='"+ngaynhapkho+"' ";
					if(db.updateReturnInt(query)!=1)
					{
						msg = "Khong the cap nhat NHAPP_KHO_CHITIET: " + query;
						//db.getConnection().rollback();
						rs.close();
						return msg;
					}*/
					
					
					/*Utility utility=new Utility();
					String kq1=utility.Update_NPP_Kho_Sp(this.getDateTime(), "chot phieu xuat kho ETC ", db, khoID, spID, nppID, kbhID, (-1) * soluong, 0, (-1) * soluong, 0);
					if(kq1.length()>0)
					{
						msg = "Khong the cap nhat NHAPP_KHO: " + kq1;
						rs.close();
						return msg;
					}*/
					Utility utility=new Utility();
					String kq1=utility.Update_NPP_Kho_Sp_Chitiet(this.getDateTime(), "chot phieu xuat kho ETC ", db, khoID, spID, nppID, kbhID, solo, ngayhethan, ngaynhapkho,  (-1) * soluong,  (-1) * soluong,0, 0, 0);
					if(kq1.length()>0)
					{
						msg = "Khong the cap nhat NHAPP_KHO_CHITIET: " + kq1;
						rs.close();
						return msg;
					}
					
					query = "insert ERP_YCXUATKHONPP_SANPHAM_CHITIET( ycxk_fk, SANPHAM_FK, solo, soluong, ngayhethan, kho_fk,ngaynhapkho ) " +
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
				
				System.out.println("---INSERT DON DAT HANG: " + query );
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
	
	
	public ResultSet getSoloTheoSp_nhapkho(String spMa, String dondathang,String donvi)
	{
		//System.out.println("---TONG LUONG: " + tongluong );
		
		String kbh_fk = "";
		if(this.dungchungKENH.equals("1")) //DUNG CHUNG KENH THI QUY VE OTC
			kbh_fk = "100025";
		else
			kbh_fk = "100052";
		
		
		Utility util_kho=new Utility();
		String ngayhoadon_=util_kho.getngayhoadon(this.userId, db,this.tungay,this.khId,1);
		
		
		/*String query=" select a.SANPHAM_FK,a.soluong,a.ngayhethan,a.solo,a.ngaynhapkho,kho.kbh_fk,b.kho_fk, \n"+
					 " 	case when sp.dvdl_fk != '" + donvi + "'   \n"+
					 "		then (( select soluong2 / soluong1 from QUYCACH where SANPHAM_FK = a.sanpham_fk \n"+
					 " 		and DVDL1_FK = sp.DVDL_FK and DVDL2_FK = '" + donvi + "' ) * AVAILABLE + a.soluong) else AVAILABLE  + a.soluong end as AVAILABLE  \n"+
					 "	 from ERP_DONDATHANGNPP_SANPHAM_CHITIET a inner join ERP_DONDATHANGNPP b \n"+
					 "	on a.dondathang_fk=b.PK_SEQ inner join SANPHAM sp on sp.PK_SEQ=a.sanpham_fk  \n"+
					 "	inner join NHAPP_KHO_CHITIET kho on kho.SANPHAM_FK=a.sanpham_fk \n"+
					 "	and kho.KBH_FK=(select case when isnull(dungchungkenh,0)=1 then 100025 else 100052 end from nhaphanphoi where pk_Seq="+this.nppId+") \n"+
					 "	 and kho.KHO_FK=b.kho_fk and kho.NPP_FK="+this.nppId+" \n"+
					 "	and kho.SOLO=a.solo and kho.NGAYHETHAN=a.ngayhethan and kho.NGAYNHAPKHO=a.ngaynhapkho where b.PK_SEQ='"+dondathang+"' and a.sanpham_fk= ( select pk_seq from SANPHAM where ma = '" + spMa + "' ) ";
		*/	
		String query= " select * from  ( "+
			   "\n	 	 select '"+ngayhoadon_+"' ngaydonhang,a.SANPHAM_FK,a.soluong,a.ngayhethan,a.solo,a.ngaynhapkho,  "+
				"\n	 	case when sp.dvdl_fk !=  '" + donvi + "'    "+
				"\n				then (( select soluong2 / soluong1 from QUYCACH where SANPHAM_FK = a.sanpham_fk  "+
				"\n		 		and DVDL1_FK = sp.DVDL_FK and DVDL2_FK = '" + donvi + "'  ) * AVAILABLE) else AVAILABLE   end as AVAILABLE   "+
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

				"\n  select b.NgayDonHang,a.SANPHAM_FK,a.soluong,a.ngayhethan,a.solo,a.ngaynhapkho,   "+
				"\n	 	case when sp.dvdl_fk !=  '"+donvi+"'     "+
				"\n				then (( select soluong2 / soluong1 from QUYCACH where SANPHAM_FK = a.sanpham_fk   "+
				"\n		 		and DVDL1_FK = sp.DVDL_FK and DVDL2_FK = '"+donvi+"'  ) * AVAILABLE ) else AVAILABLE  end as AVAILABLE  "+  
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
						

		
		System.out.println("lo de xuat la " +query);
		return db.get(query);
		
	}
	
	
	
	public ResultSet getSoloTheoSp(String spMa, String donvi, String tongluong)
	{
		tongluong = tongluong.replaceAll(",", "");
		//System.out.println("---TONG LUONG: " + tongluong );
		
		String kbh_fk = "";
		if(this.dungchungKENH.equals("1")) //DUNG CHUNG KENH THI QUY VE OTC
			kbh_fk = "100025";
		else
			kbh_fk = "100052";
			
		String query = "select case when sp.dvdl_fk != '" + donvi + "'  " +
					   "	then ( select soluong2 / soluong1 from QUYCACH where SANPHAM_FK = sp.PK_SEQ and DVDL1_FK = sp.DVDL_FK and DVDL2_FK = '" + donvi + "' ) * AVAILABLE else AVAILABLE end as AVAILABLE,  " +
					   "	NGAYHETHAN, SOLO " +
					   "from NHAPP_KHO_CHITIET ct inner join SANPHAM sp on ct.sanpham_fk = sp.pk_seq " +
					   "where KHO_FK = '" + this.khoNhanId + "' and SANPHAM_FK = ( select pk_seq from SANPHAM where ma = '" + spMa + "' )   " +
					   "	and AVAILABLE > 0 and NPP_FK = '" + this.nppId + "' and KBH_FK = '" + kbh_fk + "'  order by NGAYHETHAN asc ";
		
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
		}
		
		if(query2.trim().length() > 0)
		{
			query2 = query2.substring(0, query2.length() - 10);
			//System.out.println("---TU DONG DE XUAT BIN - LO: " + query2 );
			return db.get(query2);
		}
		
		return null;
	}
	
	public Hashtable<String, String> getSanpham_Soluong() {
		
		return this.sanpham_soluong;
	}

	
	public void setSanpham_Soluong(Hashtable<String, String> sp_soluong) {
		
		this.sanpham_soluong = sp_soluong;
	}

	
	public String[] getSpDonviChuan() {
		
		return this.spDonviChuan;
	}

	
	public void setSpDonviChuan(String[] spDonvi) {
		
		this.spDonviChuan = spDonvi;
	}

	
	public String[] getSpSoluongChuan() {
		
		return this.spSoluongChuan;
	}

	
	public void setSpSoluongChuan(String[] spSoluong) {
		
		this.spSoluongChuan = spSoluong;
	}

	
	public String getDungchungKenh() {
		
		return this.dungchungKENH;
	}

	
	public void setDungchungKenh(String dungchungKenh) {
		
		this.dungchungKENH = dungchungKenh;
	}
	
	// LAY HÓA ĐƠN CŨ : 23/1 về trước
	/*if(loai!=4)
	{	
		
		 if(nppId.equals("106210")&& mau==null)
		{
			msg1 = "Vui lòng thiết lập mẫu in cho khách hàng !";
			return msg1;
		}
		
		// CN HÀ NỘI / CN HỒ CHÍ MINH && KHÁCH HÀNG DÙNG MẪU HD TRÊN TT
	   if(nppId.equals("100002") || (nppId.equals("106210")&& mau.equals("2"))) 
	   {

			// KIEM TRA USER ĐÃ KHAI BAO SO HOA DON TRONG DLN CHUA
			query= " select count(pk_seq) as dem" +
				   " from NHANVIEN" +
				   " where pk_seq= '"+ this.userId+"' and  isnull(sohoadontu2, '') != '' and isnull( sohoadonden2, '') != ''" +
				   "       and isnull(kyhieu2,'') != '' ";
			
			System.out.println("KIEM TRA USER ĐÃ KHAI BAO SO HOA DON TRONG DLN CHUA_" + query);
			ResultSet KTDLN = db.get(query);
			if(KTDLN!= null)
			{
				while(KTDLN.next())
				{
					ktra = KTDLN.getInt("dem");
				}
				KTDLN.close();
			}
			
			if(ktra <= 0 )
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
						
						ngayhoadon = rsLayDL.getString("ngayhoadon");
						if(ngayhoadon.trim().length() <= 0)  ngayhoadon = ngaydonhang;
					}
					rsLayDL.close();
				}
				
				if(sohoadontu == -1 || sohoadonden.equals("-1") )
				{
					msg = "Vui lòng thiết lập khoảng số hóa đơn cho USER";
					return msg;
				}
				// KIEM TRA SOHOADON TRONG KHOẢNG KHAI BÁO DA DUOC DUNG
				// CHUA
				    // ETC
				query =" select count(pk_seq) as dem " +
					   " from ERP_HOADONNPP " +
					   " where kyhieu = '" + kyhieuhoadon + "' and cast(sohoadon as int) >=  "+ sohoadontu +" and cast(sohoadon as int) <=  "+ Integer.parseInt(sohoadonden) + "  " +
					   "       and trangthai != 3 and nguoisua= "+ userId +" and sohoadon != 'NA'  and mauhoadon = 2 ";
				System.out.println("1.Câu kiểm tra HO: " + query);
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
				
				 // OTC
				query =" select count(pk_seq) as dem " +
					   " from HOADON " +
					   " where kyhieu = '" + kyhieuhoadon + "' and cast(sohoadon as int) >=  "+ sohoadontu +" and cast(sohoadon as int) <=  "+ Integer.parseInt(sohoadonden) + "  " +
					   "       and trangthai != 3 and nguoisua= "+ userId +" and sohoadon != 'NA' and mauhoadon = 2 ";
				System.out.println("1.Câu kiểm tra OTC: " + query);
				ResultSet KiemTraOTC = db.get(query);
				int checkOTC = 0;
				if(KiemTraOTC != null)
				{
					while(KiemTraOTC.next())
					{
						checkOTC = KiemTraOTC.getInt("dem");
					}
					KiemTraOTC.close();
				}
								
				// LAY SOIN MAX	
				if(check <= 0 && checkOTC <= 0)
				{
					chuoi = ("000000"+ sohoadontu);
					chuoi = chuoi.substring(chuoi.length() - 7, chuoi.length());
				}
				else
				{// LAY SOIN MAX TRONG HOADON : 
						  //ETC
						query =" select  MAX(cast(SOHOADON as numeric)) as SOIN_MAX" +
							   " from ERP_HOADONNPP " +
							   " where KYHIEU = '" + kyhieuhoadon + "' and cast(sohoadon as numeric(18, 0)) >= "+ sohoadontu +" and cast(sohoadon as numeric(18,0)) <= " + Integer.parseInt(sohoadonden) + " " +
							   "       and trangthai != 3 and nguoisua= "+ userId +" and sohoadon != 'NA' and mauhoadon = 2 ";
					
					System.out.println("Câu lấy shd max " + query);
						ResultSet laySOIN = db.get(query);
					    long soinMAX_ETC= 0;
						if(laySOIN!= null)
						{
							while(laySOIN.next())
							{
								soinMAX_ETC = laySOIN.getLong("SOIN_MAX");
								
							}laySOIN.close();
						}
						
						//OTC
						query =" select  MAX(cast(SOHOADON as numeric)) as SOIN_MAX" +
							   " from HOADON" +
							   " where KYHIEU = '" + kyhieuhoadon + "' and cast(sohoadon as numeric(18, 0)) >= "+ sohoadontu +" and cast(sohoadon as numeric(18,0)) <= " + Integer.parseInt(sohoadonden) + " " +
							   "       and trangthai != 3 and nguoisua= "+ userId +" and sohoadon != 'NA' and mauhoadon = 2 ";
					
					System.out.println("Câu lấy shd max " + query);
						ResultSet laySOIN_OTC = db.get(query);
					    long soinMAX_OTC= 0;
						if(laySOIN_OTC!= null)
						{
							while(laySOIN_OTC.next())
							{
								soinMAX_OTC = laySOIN_OTC.getLong("SOIN_MAX");
								
							}laySOIN_OTC.close();
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
							"from ERP_HOADONNPP a inner join NHANVIEN b on a.NGUOITAO = b.PK_SEQ " +
							"where  a.nguoisua= '" + userId + "' and a.kyhieu = '" + kyhieuhoadon + "' and a.trangthai != 3 " +
							"		and cast(a.SOHOADON as numeric) >= CAST(b.sohoadontu2 as numeric(18, 0) )   " +
							"		and cast(a.SOHOADON as numeric) <= CAST(b.sohoadonden2 as numeric(18, 0) ) and a.sohoadon != 'NA' and a.mauhoadon = 2  " +
							"having "+
							" MAX(cast(SOHOADON as numeric)) != ( select  MAX(cast(SOHOADON as numeric)) as SOIN_MAX  from ERP_HOADONNPP where  kyhieu = '" + kyhieuhoadon + "' and nguoisua= '" + userId + "' and sohoadon != 'NA' and mauhoadon = 2 )";
					
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
				query = " select (select count(*) from HOADON where SOHOADON = '"+ sohoadon +"' and kyhieu = '"+ kyhieuhoadon +"' and trangthai != '3' and npp_fk = '" + nppId + "'  and mauhoadon = 2 and sohoadon != 'NA' ) as KtraOTC, " +
						"        (select count(*) from ERP_HOADONNPP where SOHOADON = '"+ sohoadon +"' and kyhieu = '"+ kyhieuhoadon +"' and trangthai != '3' and npp_fk = '" + nppId + "' and mauhoadon = 2 and sohoadon != 'NA' ) as KtraETC " +
						" from NHANVIEN where pk_seq = '" + userId + "' ";
				ResultSet RsRs = db.get(query);
				int KT_OTC = 0;
				if(RsRs != null)
				{
					while(RsRs.next())
					{
						KT_OTC = RsRs.getInt("KtraOTC") ;
					}
				}
				
				if (KT_OTC > 0) // CÓ HÓA ĐƠN (CỦA USER KHÁC) CÓ SỐ HD
								// TRÙNG VS SỐ HÓA ĐƠN MỚI TẠO
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
				   " where pk_seq= '"+ userId+"' and  isnull(sohoadontu, '') != '' and isnull( sohoadonden, '') != ''" +
				   "       and isnull(kyhieu,'') != '' ";
			
			ResultSet KTDLN = db.get(query);
			if(KTDLN!= null)
			{
				while(KTDLN.next())
				{
					ktra = KTDLN.getInt("dem");
				}
				KTDLN.close();
			}
			
			if(ktra <= 0 )
			{					
				msg1 = "Vui lòng khai báo Số hóa đơn trong (Thiết lập dữ liệu nền > Số hóa đơn) cho user này ";
				return msg1;
			}
			else
			{
				// LAY KY HIEU HOA DON ,SOHDTU TRONG DLN
				query= " select kyhieu as kyhieuhoadon, isnull(sohoadontu, -1) as sohoadontu, isnull(sohoadonden, -1) as sohoadonden, " +
					   "        isnull(ngayhoadon, '') as ngayhoadon " +
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
						
						ngayhoadon = rsLayDL.getString("ngayhoadon");
						if(ngayhoadon.trim().length() <= 0)  ngayhoadon = ngaydonhang;
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
						query =" select  MAX(cast(SOHOADON as numeric)) as SOIN_MAX" +
							   " from HOADON" +
							   " where KYHIEU = '" + kyhieuhoadon + "' and cast(sohoadon as numeric(18, 0)) >= "+ sohoadontu +" and cast(sohoadon as numeric(18,0)) <= " + Integer.parseInt(sohoadonden) + " " +
							   "       and trangthai != 3 and nguoisua= "+ userId +" and sohoadon != 'NA' and mauhoadon = 1 ";
					
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
						query = " select  MAX(cast(SOHOADON as numeric)) as SOIN_MAX" +
								" from ERP_HOADONNPP" +
								" where KYHIEU ='"+ kyhieuhoadon +"' and cast(sohoadon as numeric(18,0)) >= "+ sohoadontu +" and cast(sohoadon as numeric(18,0))<= " + Integer.parseInt(sohoadonden) + " " +
								"       and trangthai != 3 and nguoisua= "+ userId +" and sohoadon != 'NA' and mauhoadon = 1 ";
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
	}*/
	
	
}
