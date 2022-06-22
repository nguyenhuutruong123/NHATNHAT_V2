package geso.dms.distributor.beans.hopdong.imp;

import geso.dms.distributor.beans.hopdong.IErpHopdongNpp;
import geso.dms.center.db.sql.Idbutils;
import geso.dms.center.db.sql.dbutils;
import geso.dms.center.util.Utility;

import java.sql.ResultSet;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Hashtable;
import java.util.List;

public class ErpHopdongNpp implements IErpHopdongNpp
{

	
	
	String userId;
	String id;
	
	String ma;
	String hopdongchung;
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
	
	String khId;
	ResultSet khRs;
	
	String khApdungId;
	ResultSet khApdungRs;
	
	String dvkdId;
	ResultSet dvkdRs;
	
	String kbhId;
	ResultSet kbhRs;
	
	String gsbhId;
	ResultSet gsbhRs;
	
	String ddkdId;
	ResultSet ddkdRs;
	
	String hopdongId;
	ResultSet hopdongRs;
	
	ResultSet dvtRs;
	ResultSet ghichuRs;
	String[] spId;
	String[] spMa;
	String[] spStt;
	String[] spTen;
	String[] spDonvi;
	String[] spSoluong;
	String[] spGianhap;
	String[] spChietkhau;
	String[] spVAT;
	String[] spTungay;
	String[] spDenngay;
	String[] spTrongluong;
	String[] spThetich;
	String[] spQuyDoi;
	
	String[] dhCk_diengiai;
	String[] dhCk_giatri;
	String[] dhCk_loai;
	String[] ckcskh;
	
	String[] dadat;

	String [] nothd;
	
	String nppId;
	String nppTen;
	String sitecode;
	Utility util;
	dbutils db;
	
	
	String NgayNopHoSoThau = "";
	String NgayMoThau = "";
	String LyDoKhongTrungThau = "";
	
	public ErpHopdongNpp()
	{
		this.id = "";
		this.ma = "";
		this.tungay = "";
		this.denngay = "";
		this.ghichu = "";
		this.khoNhanId = "";
		this.khId = "";
		this.khApdungId = "";
		this.msg = "";
		this.loaidonhang = "0";
		this.trangthai = "0";
		this.chietkhau = "0";
		this.vat = "10";
		this.dvkdId = "";
		this.kbhId = "";
		this.ddkdId = "";
		this.gsbhId = "";
		this.hopdongId = "";
		this.hopdongchung = "0";
		
		this.dhCk_diengiai = new String[]{"", "", "", ""};
		this.dhCk_giatri = new String[]{"0", "0", "0", "0"};
		this.dhCk_loai = new String[]{"0", "0", "0", "0"};
		
		this.db = new dbutils();
		this.util = new Utility();
	}
	
	public ErpHopdongNpp(String id)
	{
		this.id = id;
		this.ma = "";
		this.tungay = "";
		this.denngay = "";
		this.ghichu = "";
		this.khoNhanId = "";
		this.khId = "";
		this.khApdungId = "";
		this.msg = "";
		this.loaidonhang = "0";
		this.trangthai = "0";
		this.chietkhau = "0";
		this.vat = "10";
		this.dvkdId = "";
		this.kbhId = "";
		this.ddkdId = "";
		this.gsbhId = "";
		this.hopdongId = "";
		this.hopdongchung = "0";

		this.dhCk_diengiai = new String[]{"", "", "", ""};
		this.dhCk_giatri = new String[]{"0", "0", "0", "0"};
		this.dhCk_loai = new String[]{"0", "0", "0", "0"};
		
		this.db = new dbutils();
		this.util = new Utility();
	}

	public String[] getDadat() {
		return dadat;
	}

	public void setDadat(String[] dadat) {
		this.dadat = dadat;
	}
	
	public String[] getCkcskh() {
		return ckcskh;
	}

	public void setCkcskh(String[] ckcskh) {
		this.ckcskh = ckcskh;
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

	
	public boolean createNK() 
	{
		if(this.ma.trim().length() <= 0)
		{
			this.msg = "Vui lòng nhập mã hợp đồng";
			return false;
		}
		
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
		System.out.println("loai don hang la "+this.loaidonhang);
		if(this.loaidonhang.equals("1"))
		{
			
		}
		else if( this.khId.trim().length() <= 0 && !this.loaidonhang.equals("3") )
		{
			this.msg = "Vui lòng chọn khách hàng ETC";
			return false;
		}
		else
		{
			/*if(this.loaidonhang.equals("3") && this.khApdungId.trim().length() <= 0 )
			{
				this.msg = "Vui lòng chọn khách hàng ETC áp dụng";
				return false;
			}*/
		}
		
		//NEU PHU LUC CUA HOP DONG NGUYEN TAC THI KHONG CAN NHAP SO LUONG
		String loaihopdongPL = "";
		if(this.loaidonhang.equals("1"))
		{
			if(this.hopdongId.trim().length() <= 0)
			{
				this.msg = "Vui lòng chọn phụ lục của hợp đồng";
				return false;
			}
			else
			{
				String query = "select loaidonhang from ERP_HOPDONGNPP where pk_seq = '" + this.hopdongId + "'";
				ResultSet rsLOAI = db.get(query);
				if(rsLOAI != null)
				{
					try 
					{
						if(rsLOAI.next())
						{
							loaihopdongPL = rsLOAI.getString("loaidonhang");
						}
						rsLOAI.close();
					} 
					catch (Exception e) {}
				}
			}
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
			for(int i = 0; i < spMa.length; i++)
			{
				if( spMa[i].trim().length() > 0 )
				{
					if(this.loaidonhang.equals("1"))  //HỢP ĐỒNG PHỤ LỤC CỦA HỢP ĐỒNG NGUYÊN TẮC THÌ KHÔNG CẦN NHẬP SỐ LƯỢNG
					{
						if(spSoluong[i].trim().length() <= 0 && !loaihopdongPL.equals("2") )
						{
							this.msg = "Bạn phải nhập số lượng của sản phẩm ( " + spTen[i] + " )";
							return false;
						}
					}
					else
					{
						if(spSoluong[i].trim().length() <= 0 && !( this.loaidonhang.equals("2") || this.loaidonhang.equals("3") ) )  //Hợp đồng nguyên tắc thì không cần nhập số lượng
						{
							this.msg = "Bạn phải nhập số lượng của sản phẩm ( " + spTen[i] + " )";
							return false;
						}
					}
					
					if(spGianhap[i].trim().length() <= 0 || spGianhap[i].trim().equals("0") )
					{
						this.msg = "Bạn phải nhập đơn giá của sản phẩm ( " + spTen[i] + " )";
						return false;
					}
					
					/*if(spTungay[i].trim().length() <= 0)
					{
						this.msg = "Bạn phải nhập từ ngày giao của sản phẩm ( " + spTen[i] + " )";
						return false;
					}
					
					if(spDenngay[i].trim().length() <= 0)
					{
						this.msg = "Bạn phải nhập đến ngày giao của sản phẩm ( " + spTen[i] + " )";
						return false;
					}*/
					
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
			String hopdong_fk = this.hopdongId.trim().length() <= 0 ? "null" : this.hopdongId.trim();
			String khachhang_fk = this.khId.trim().length() <= 0 ? "null" : this.khId.trim();
			String dvkd_fk = this.dvkdId.trim().length() <= 0 ? "null" : this.dvkdId;
			String kbh_fk = this.kbhId.trim().length() <= 0 ? "null" : this.kbhId;
			
			String query="select count(*) sl from erp_hopdongnpp where Mahopdong='"+ this.ma +"'";
			ResultSet rscheckma=db.get(query);
			rscheckma.next();
			if(rscheckma.getInt("sl")>0)
			{
					this.msg = "Mã hợp đồng đã tồn tại vui lòng nhập lại " ;
					db.getConnection().rollback();
					return false;
			}
			rscheckma.close();
			
			
			String NgayNopHoSoThauInsert = "null";
			if(this.NgayNopHoSoThau.trim().length() >0)
				NgayNopHoSoThauInsert = "'" + this.NgayNopHoSoThau + "'";
			
			String NgayMoThauInsert = "null";
			if(this.NgayMoThau.trim().length() >0)
				NgayMoThauInsert = "'" + this.NgayMoThau + "'";
			
			
			
			query = 
				" insert ERP_HopDongNpp(mahopdong, tungay, denngay, loaidonhang, ghichu, trangthai, dvkd_fk, kbh_fk, gsbh_fk, ddkd_fk, khachhang_fk, npp_fk, kho_fk, chietkhau, vat, HOPDONG_FK, ngaytao, nguoitao, ngaysua, nguoisua,NgayNopHoSoThau,NgayMoThau,LyDoKhongTrungThau) " +
				" values(N'" + this.ma + "', '" + this.tungay + "', '" + this.denngay + "', '" + this.loaidonhang + "', N'" + this.ghichu + "', '0', " + dvkd_fk + ", " + kbh_fk + ", '" + this.gsbhId + "', '" + this.ddkdId + "', " + khachhang_fk + ", '" + this.nppId + "', " + khonhan_fk + ", '" + chietkhau + "', '" + vat + "', " + hopdong_fk + ", '" + getDateTime() + "', '" + this.userId + "', '" + getDateTime() + "', '" + this.userId + "'" +
						", "+NgayNopHoSoThauInsert+", "+NgayMoThauInsert+",N'"+LyDoKhongTrungThau+"' )";
			
			System.out.println("1.Insert HOPDONG: " + query);
			if(!db.update(query))
			{
				this.msg = "Không thể tạo mới ERP_HopDong " + query;
				db.getConnection().rollback();
				return false;
			}
			
			//LAY ID
			ResultSet rsDDH = db.get("select scope_identity() ID ");
			if(rsDDH.next())
			{
				this.id = rsDDH.getString("ID");
			}
			rsDDH.close();
			
			System.out.println("HOPDONG ID: " + this.id);
			
			
			
			
			
			for(int i = 0; i < spMa.length; i++)
			{
				if(spMa[i].trim().length() > 0 && spGianhap[i].trim().length() > 0  )
				{
					
					String thueVAT = spVAT[i].trim().replaceAll(",", "");
					if(thueVAT.trim().length() < 0)
						thueVAT = "0";
					
					String slg = spSoluong[i].trim().length() <= 0 ? "NULL" : spSoluong[i].replaceAll(",", "");
					
					query = "insert ERP_HOPDONGNPP_SANPHAM( ckcskh,dvdlChuan_FK,hopdong_fk, SANPHAM_FK, soluong, dongia, chietkhau, thueVAT, dvdl_fk, tungay, denngay, stt ) " +
							"select  '"+(ckcskh[i].trim().length()<0?"0":ckcskh[i])+"',sp.dvdl_fk,'" + this.id + "', pk_seq, " + slg + ", '" + spGianhap[i].replaceAll(",", "") + "', 0, "+thueVAT+" , ISNULL( ( select pk_Seq from DONVIDOLUONG where donvi = N'" + spDonvi[i].trim() + "' ), DVDL_FK ), '" + spTungay[i].trim() + "', '" + spDenngay[i].trim() + "', " + spStt[i].trim() + " " +
							"from SANPHAM sp where MA = '" + spMa[i].trim() + "' ";
										
					
					System.out.println("1.Insert HD - SP: " + query);
					if(!db.update(query))
					{
						this.msg = "Khong the tao moi ERP_HOPDONGNPP_SANPHAM: " + query;
						db.getConnection().rollback();
						return false;
					}
					
					query = "\n insert ERP_HOPDONGNPP_SANPHAM_log ( ckcskh,hopdong_fk, SANPHAM_FK, soluong, dongia, chietkhau, thueVAT, dvdl_fk, tungay, denngay, stt,userid,ghichu ) " +
							"\n select ckcskh,hopdong_fk, SANPHAM_FK, soluong, dongia, chietkhau, thueVAT, dvdl_fk, tungay, denngay, stt,"+this.userId+",N'Tạo mới' from ERP_HOPDONGNPP_SANPHAM where hopdong_fk =  "+this.id;
				
					if(!db.update(query))
					{
						this.msg = "Không thể Insert ERP_HOPDONGNPP_SANPHAM_log: " + query;
						db.getConnection().rollback();
						return false;
					}
				}	
			}
			
			
			
			
			//HOP DONG CHUNG AP DUNG CHO NHUGN ETC NAO
			if(loaidonhang.equals("3") && this.khApdungId.trim().length() > 0)
			{
				query = "Insert ERP_HOPDONGNPP_APDUNG(hopdong_fk, khachhang_fk) " +
						"select '" + this.id + "', pk_seq from KHACHHANG where NPP_FK = '" + this.nppId + "' and pk_seq in ( " + this.khApdungId + " ) ";
				System.out.println("1.Insert HD - AP: " + query);
				if(!db.update(query))
				{
					this.msg = "Khong the tao moi ERP_HOPDONGNPP_APDUNG: " + query;
					db.getConnection().rollback();
					return false;
				}
			}
			
			
			
			String msgLog = Log_HopDongNPP( db,this.id,"Tạo mới",userId);
			if(msgLog.trim().length() > 0)
			{
				this.msg = msgLog;
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
	
	public static String Log_HopDongNPP(Idbutils db,String hdId,String NghiepVu,String userId)
	{
		try
		{
			
			String query = 
				" insert ERP_HopDongNpp_Log(ngaynophosothau,userId,NghiepVu,pk_seq,mahopdong, tungay, denngay, loaidonhang, ghichu, trangthai, dvkd_fk, kbh_fk, gsbh_fk, ddkd_fk, khachhang_fk, npp_fk, kho_fk, chietkhau, vat, HOPDONG_FK, ngaytao, nguoitao, ngaysua, nguoisua,NgayMoThau,LyDoKhongTrungThau,isKhongTrungThau) " +
				" select ngaynophosothau,"+userId+",N'"+NghiepVu+"',pk_seq,mahopdong, tungay, denngay, loaidonhang, ghichu, trangthai, dvkd_fk, kbh_fk, gsbh_fk, ddkd_fk, khachhang_fk, npp_fk, kho_fk, chietkhau, vat, HOPDONG_FK, ngaytao, nguoitao, ngaysua, "+userId+",NgayMoThau,LyDoKhongTrungThau,isKhongTrungThau from  ERP_HopDongNpp where pk_seq =  "+hdId;
			
			System.out.println("1.Insert HOPDONG: " + query);
			if(db.updateReturnInt(query)<=0)
			{
				return "Không thể lưu log hợp đồng "+ query;
			} 
			
			return "";
		}catch(Exception e)
		{
			e.printStackTrace();
			return "Exeption " + e.getMessage();
		}
	}

	public boolean updateNK(String checkKM) 
	{
		if(this.ma.trim().length() <= 0)
		{
			this.msg = "Vui lòng nhập mã hợp đồng";
			return false;
		}
		
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

		if(this.loaidonhang.equals("1"))
		{
			
		}
		else if( this.khId.trim().length() <= 0 && !this.loaidonhang.equals("3") )
		{
			this.msg = "Vui lòng chọn khách hàng ETC";
			return false;
		}
		else
		{
			/*if(this.loaidonhang.equals("3") && this.khApdungId.trim().length() <= 0 )
			{
				this.msg = "Vui lòng chọn khách hàng ETC áp dụng";
				return false;
			}*/
		}
		
		//NEU PHU LUC CUA HOP DONG NGUYEN TAC THI KHONG CAN NHAP SO LUONG
		String loaihopdongPL = "";
		if(this.loaidonhang.equals("1"))
		{
			if(this.hopdongId.trim().length() <= 0)
			{
				this.msg = "Vui lòng chọn phụ lục của hợp đồng";
				return false;
			}
			else
			{
				String query = "select loaidonhang from ERP_HOPDONGNPP where pk_seq = '" + this.hopdongId + "'";
				ResultSet rsLOAI = db.get(query);
				if(rsLOAI != null)
				{
					try 
					{
						if(rsLOAI.next())
						{
							loaihopdongPL = rsLOAI.getString("loaidonhang");
						}
						rsLOAI.close();
					} 
					catch (Exception e) {}
				}
			}
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
					if(this.loaidonhang.equals("1"))  //HỢP ĐỒNG PHỤ LỤC CỦA HỢP ĐỒNG NGUYÊN TẮC THÌ KHÔNG CẦN NHẬP SỐ LƯỢNG
					{
						if(spSoluong[i].trim().length() <= 0 && !loaihopdongPL.equals("2") )
						{
							this.msg = "Bạn phải nhập số lượng của sản phẩm ( " + spTen[i] + " )";
							return false;
						}
					}
					else
					{
						if(spSoluong[i].trim().length() <= 0 && !( this.loaidonhang.equals("2") || this.loaidonhang.equals("3") ) )  //Hợp đồng nguyên tắc thì không cần nhập số lượng
						{
							this.msg = "Bạn phải nhập số lượng của sản phẩm ( " + spTen[i] + " )";
							return false;
						}
					}
					
					if(spGianhap[i].trim().length() <= 0 || spGianhap[i].trim().equals("0") )
					{
						this.msg = "Bạn phải nhập đơn giá của sản phẩm ( " + spTen[i] + " )";
						return false;
					}
					
					/*if(spTungay[i].trim().length() <= 0)
					{
						this.msg = "Bạn phải nhập từ ngày giao của sản phẩm ( " + spTen[i] + " )";
						return false;
					}
					
					if(spDenngay[i].trim().length() <= 0)
					{
						this.msg = "Bạn phải nhập đến ngày giao của sản phẩm ( " + spTen[i] + " )";
						return false;
					}*/
					
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
			String hopdong_fk = this.hopdongId.trim().length() <= 0 ? "null" : this.hopdongId.trim();
			String khachhang_fk = this.khId.trim().length() <= 0 ? "null" : this.khId.trim();
			String dvkd_fk = this.dvkdId.trim().length() <= 0 ? "null" : this.dvkdId;
			String kbh_fk = this.kbhId.trim().length() <= 0 ? "null" : this.kbhId;

			String query=" select count(*) sl from erp_hopdongnpp where pk_seq<>'"+this.id+"' and  Mahopdong='"+ this.ma +"'";
			System.out.println("check mahd" +query);
			ResultSet rscheckma=db.get(query);
			rscheckma.next();
			if(rscheckma.getInt("sl")>0)
			{
					this.msg = "Mã hợp đồng đã tồn tại vui lòng nhập lại " ;
					db.getConnection().rollback();
					return false;
			}
			rscheckma.close();
			
			
			
			String NgayNopHoSoThauInsert = "null";
			if(this.NgayNopHoSoThau.trim().length() >0)
				NgayNopHoSoThauInsert = "'" + this.NgayNopHoSoThau + "'";
			
			String NgayMoThauInsert = "null";
			if(this.NgayMoThau.trim().length() >0)
				NgayMoThauInsert = "'" + this.NgayMoThau + "'";
			
			
			
					
			 query =	" Update ERP_HopDongNPP set mahopdong = N'" + this.ma + "', loaidonhang = '" + this.loaidonhang + "', tungay = '" + this.tungay + "', denngay = '" + this.denngay + "', ghichu = N'" + this.ghichu + "', " +
						" 	dvkd_fk = " + dvkd_fk + ", kbh_fk = " + kbh_fk + ", gsbh_fk = '" + this.gsbhId + "', ddkd_fk = '" + this.ddkdId + "', khachhang_fk = " + khachhang_fk + ", npp_fk = '" + this.nppId + "', kho_fk = " + khonhan_fk + ", hopdong_fk = " + hopdong_fk + ", ngaysua = '" + getDateTime() + "', nguoisua = '" + this.userId + "', chietkhau = '" + chietkhau + "', vat = '" + vat + "'" +
						" , NgayNopHoSoThau = "+NgayNopHoSoThauInsert+", NgayMoThau = "+NgayMoThauInsert+", LyDoKhongTrungThau = N'"+this.LyDoKhongTrungThau+"'  " + 
						" where pk_seq = '" + this.id + "' ";
	
			if(!db.update(query))
			{
				this.msg = "Không thể cập nhật ERP_HopDongNPP " + query;
				db.getConnection().rollback();
				return false;
			}
			
			
			query = "\n insert ERP_HOPDONGNPP_SANPHAM_log ( ckcskh,hopdong_fk, SANPHAM_FK, soluong, dongia, chietkhau, thueVAT, dvdl_fk, tungay, denngay, stt,userid,ghichu ) " +
			"\n select ckcskh,hopdong_fk, SANPHAM_FK, soluong, dongia, chietkhau, thueVAT, dvdl_fk, tungay, denngay, stt,"+this.userId+",N'Trước cập nhật' from ERP_HOPDONGNPP_SANPHAM where hopdong_fk =  "+this.id;

			if(!db.update(query))
			{
				this.msg = "Không thể Insert ERP_HOPDONGNPP_SANPHAM_log 1: " + query;
				db.getConnection().rollback();
				return false;
			}

						
			query = "delete ERP_HOPDONGNPP_SANPHAM where hopdong_fk = '" + this.id + "'";
			if(!db.update(query))
			{
				this.msg = "Không thể cập nhật ERP_HOPDONGNPP_SANPHAM " + query;
				db.getConnection().rollback();
				return false;
			}
			
			query = "delete ERP_HOPDONGNPP_SANPHAM where hopdong_fk = '" + this.id + "' ";
			if(!db.update(query))
			{
				this.msg = "Không thể cập nhật ERP_HOPDONGNPP_SANPHAM " + query;
				db.getConnection().rollback();
				return false;
			}
			
			query = "delete ERP_HOPDONGNPP_APDUNG where hopdong_fk = '" + this.id + "' ";
			if(!db.update(query))
			{
				this.msg = "Không thể cập nhật ERP_HOPDONGNPP_APDUNG " + query;
				db.getConnection().rollback();
				return false;
			}
			
			for(int i = 0; i < spMa.length; i++)
			{
				if(spMa[i].trim().length() > 0 && spGianhap[i].trim().length() > 0  )
				{
					
					String thueVAT = spVAT[i].trim().replaceAll(",", "");
					if(thueVAT.trim().length() < 0)
						thueVAT = "0";
					
					String slg = spSoluong[i].trim().length() <= 0 ? "NULL" : spSoluong[i].replaceAll(",", "");
					
					
					
					query = "insert ERP_HOPDONGNPP_SANPHAM( dvdlChuan_FK,ckcskh,hopdong_fk, SANPHAM_FK, soluong, dongia, thueVAT, dvdl_fk, tungay, denngay, stt ) " +
							"select dvdl_fk,'"+(ckcskh[i].trim().length()<0?"0":ckcskh[i])+"','" + this.id + "', pk_seq, " + slg + ", '" + spGianhap[i].replaceAll(",", "") + "', '" + thueVAT + "', ISNULL( ( select pk_Seq from DONVIDOLUONG where donvi = N'" + spDonvi[i].trim() + "' ), DVDL_FK ), '" + spTungay[i].trim() + "', '" + spDenngay[i].trim() + "', " + spStt[i].trim() + " " +
							"from SANPHAM where MA = '" + spMa[i].trim() + "' ";
					
					
					System.out.println("1.Insert HD - SP: " + query);
					if(!db.update(query))
					{
						this.msg = "Khong the tao moi ERP_HOPDONGNPP_SANPHAM: " + query;
						db.getConnection().rollback();
						return false;
					}
					
					query = "\n insert ERP_HOPDONGNPP_SANPHAM_log ( ckcskh,hopdong_fk, SANPHAM_FK, soluong, dongia, chietkhau, thueVAT, dvdl_fk, tungay, denngay, stt,userid,ghichu ) " +
					"\n select ckcskh,hopdong_fk, SANPHAM_FK, soluong, dongia, chietkhau, thueVAT, dvdl_fk, tungay, denngay, stt,"+this.userId+",N'Sau cập nhật' from ERP_HOPDONGNPP_SANPHAM where hopdong_fk =  "+this.id;

					if(!db.update(query))
					{
						this.msg = "Không thể Insert ERP_HOPDONGNPP_SANPHAM_log 2: " + query;
						db.getConnection().rollback();
						return false;
					}
				}
			}
			
		
			
			//HOP DONG CHUNG AP DUNG CHO NHUGN ETC NAO
			if(loaidonhang.equals("3") && this.khApdungId.trim().length() > 0)
			{
				query = "Insert ERP_HOPDONGNPP_APDUNG(hopdong_fk, khachhang_fk) " +
						"select '" + this.id + "', pk_seq from KHACHHANG where NPP_FK = '" + this.nppId + "' and pk_seq in ( " + this.khApdungId + " ) ";
				System.out.println("1.Insert HD - AP: " + query);
				if(!db.update(query))
				{
					this.msg = "Khong the tao moi ERP_HOPDONGNPP_APDUNG: " + query;
					db.getConnection().rollback();
					return false;
				}
			}
			
			String msgLog = Log_HopDongNPP( db,this.id,"Câp nhật",userId );
			if(msgLog.trim().length() > 0)
			{
				this.msg = msgLog;
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
				
		this.khoNhanRs = db.get("select PK_SEQ, TEN from KHO where trangthai = '1' and pk_seq in " + this.util.quyen_kho(this.userId) );
		
		this.dvtRs = db.getScrol("select PK_SEQ, DONVI from DONVIDOLUONG where trangthai = '1' ");
		this.dvkdRs = db.get("select PK_SEQ, DONVIKINHDOANH as TEN from DONVIKINHDOANH where TRANGTHAI = '1' ");
		this.kbhRs = db.get("select PK_SEQ, DIENGIAI as TEN from KENHBANHANG where TRANGTHAI = '1' and pk_seq = '100052' ");
		
		this.gsbhRs = db.get("select PK_SEQ, TEN from GIAMSATBANHANG where trangthai = '1' and pk_seq in (select GSBH_FK from NHAPP_GIAMSATBH where npp_fk = '" + this.nppId + "' )  ");
		
		//String query = "select pk_seq, TEN from DAIDIENKINHDOANH where tructhuoc_fk = '" + this.nppId + "' ";
		String query = "select pk_seq, TEN from DAIDIENKINHDOANH where isnull(isPG,0)=0 and pk_seq in ( select ddkd_fk from DAIDIENKINHDOANH_NPP where npp_fk = '" + this.nppId + "' ) ";
		//if(this.gsbhId.trim().length() > 0)
			//query += " and pk_seq in ( select ddkd_fk from DDKD_GSBH where GSBH_FK = '" + this.gsbhId + "' ) ";
		this.ddkdRs = db.get(query);
		
		if(this.trangthai.equals("0") || !this.loaidonhang.equals("3") )
		{
			query = "select PK_SEQ, MAFAST + ', ' + TEN as TEN from KHACHHANG where TRANGTHAI = '1'  AND KBH_FK = '100052' AND NPP_FK = '" + this.nppId + "' ";
			this.khRs = db.get(query);
			//this.khApdungRs = db.get(query);
		}
		else
		{
			if(this.khId.trim().length() > 0)
			{
				query = "select PK_SEQ, MAFAST + ', ' + TEN as TEN " +
						"from KHACHHANG where TRANGTHAI = '1'  AND KBH_FK = '100052' AND PK_SEQ = '" + this.khId + "' AND NPP_FK = '" + this.nppId + "' ";
				
				this.khRs = db.get(query);
			}
		}
		
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
			query = "select b.ddkd_fk from KHACHHANG_TUYENBH a inner join TUYENBANHANG b on a.TBH_FK = b.pk_seq where a.khachhang_fk = '" + this.khId + "'";
			System.out.println("--LAY DDKD: " + query );
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
		
		if(this.loaidonhang.equals("1"))
		{
			if(this.khId.trim().length()>0)
			{
			query = "select PK_SEQ, cast(pk_seq as varchar(10)) + ' [' + mahopdong + ']' as diengiai " +
					"from ERP_HOPDONGNPP where LoaiDonHang = 0 and khachhang_fk="+this.khId+"  order by pk_seq desc ";
			}
			else
			{
			query = "select PK_SEQ, cast(pk_seq as varchar(10)) + ' [' + mahopdong + ']' as diengiai " +
					"from ERP_HOPDONGNPP where LoaiDonHang = 0    order by pk_seq desc ";
			}
			System.out.println("ohuj"+ query);
			this.hopdongRs = db.get(query);
			
			if(this.spMa == null && this.hopdongId.trim().length() > 0 )
			{
				//INIT SAN PHAM LUC DAU SE GIONG SP TRONG HOP DONG CU, NHUNG DUOC PHEP SUA LAI SO LUONG
				
				String loaihopdongPL = "";
				if(this.loaidonhang.equals("1") && this.hopdongId.trim().length() > 0 )
				{
					query = "select loaidonhang from ERP_HOPDONGNPP where pk_seq = '" + this.hopdongId + "'";
					ResultSet rsLOAI = db.get(query);
					if(rsLOAI != null)
					{
						try 
						{
							if(rsLOAI.next())
							{
								loaihopdongPL = rsLOAI.getString("loaidonhang");
							}
							rsLOAI.close();
						} 
						catch (Exception e) {}
					}
				}
				
				if( !(this.loaidonhang.equals("1") && loaihopdongPL.equals("2")) )
					initSANPHAM_THEOHD();
			}
			
		}
		
	}

	private void initSANPHAM() 
	{
		String query =  
					"select isnull((select 1 from erp_hopdongnpp_sanpham aa where aa.hopdong_fk=hd.hopdong_fk and aa.sanpham_fk=a.sanpham_fk),0) as ishd, a.stt, isnull(dadat.daDAT,0)*dbo.[LayQuyCach_DVBan](a.sanpham_fk, null,a.dvdl_fk ) as dadat,isnull(a.ckcskh,0) ckcskh ,b.MA, b.TEN, DV.donvi, isnull(a.soluong, 0) as soluong, a.dongia, isnull(a.chietkhau, 0) as chietkhau, ISNULL(b.trongluong, 0) as trongluong, ISNULL(b.thetich, 0) as thetich, a.tungay, a.denngay, a.thueVAT    \n" +	
					"	,(select soluong1/ soluong2 from QUYCACH where sanpham_fk=a.sanpham_fk and DVDL1_FK=b.DVDL_FK and DVDL2_FK=100018)   as spQuyDoi "+
					" from ERP_HopDongNPP_SANPHAM a inner Join SanPham b on a.SANPHAM_FK = b.PK_SEQ    \n" +
					"	inner join ERP_HopDongnpp hd on hd.pk_seq=a.hopdong_fk    \n" +
					" 		INNER JOIN DONVIDOLUONG DV ON DV.PK_SEQ = a.DVDL_FK       \n" +
					" left join  \n" +
					"  (select sanpham_fk, sum(soluong) as daDAT  \n" + 
				  	"	from   \n" +
				  	"	(   \n" +
				  	"		select a.sanpham_fk, b.DVDL_FK as dvCHUAN,        \n" +
				  	"				case when a.dvdl_fk IS null then a.soluong      \n" +   
				  	"					 when a.dvdl_fk = b.DVDL_FK then a.soluong        \n" +
				  	"					 else  a.soluong * ( select SOLUONG1 from QUYCACH where sanpham_fk = a.sanpham_fk and DVDL2_FK = a.dvdl_fk  and QUYCACH.DVDL1_FK=b.DVDL_FK )         \n" +
				  	"									 / ( select SOLUONG2 from QUYCACH where sanpham_fk = a.sanpham_fk and DVDL2_FK = a.dvdl_fk   and QUYCACH.DVDL1_FK=b.DVDL_FK) end as soluong    \n" +
				  	"		from ERP_DONDATHANGNPP_SANPHAM a inner join SANPHAM b on a.sanpham_fk = b.PK_SEQ       \n" +
				  	"		where a.dondathang_fk in (   select pk_seq from ERP_DondathangNPP where trangthai != '3' and hopdong_fk = '" + this.id + "'  )       \n" +
				  	" )  \n" +
				  	" dathang group by sanpham_fk  ) dadat on  dadat.sanpham_fk=a.sanpham_fk  \n" +
					"where a.HOPDONG_FK = '" + this.id + "' order by a.stt ";
		
		System.out.println("---INIT SP: " + query);
		ResultSet spRs = db.get(query);
		
		NumberFormat formater = new DecimalFormat("##,###,###");
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
				String ckcskh = "";
				String datat="";
				String spnothd="";
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
					
					if(spRs.getString("tungay").trim().length() > 0)
						spTUNGAY += spRs.getString("tungay") + "__";
					else
						spTUNGAY += " __";
					
					if(spRs.getString("tungay").trim().length() > 0)
						spDENNGAY += spRs.getString("denngay") + "__";
					else
						spDENNGAY += " __";
					
					spTRONGLUONG += spRs.getString("trongluong") + "__";
					spTHETICH += spRs.getString("thetich") + "__";
					spQuyDoi +=spRs.getString("spQuyDoi") + "__";
					ckcskh +=spRs.getString("ckcskh") + "__";
					datat +=spRs.getString("dadat") + "__";
					spnothd+=spRs.getString("ishd") + "__";
					
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
					
					ckcskh = ckcskh.substring(0, ckcskh.length() - 2);
					this.ckcskh = ckcskh.split("__");
					
					datat = datat.substring(0, datat.length() - 2);
					this.dadat = datat.split("__");
					
					spnothd = spnothd.substring(0, spnothd.length() - 2);
					this.nothd = spnothd.split("__");
					
				}
				
				//INIT CHIET KHAU
				query = "select DIENGIAI, GIATRI, LOAI from ERP_HOPDONGNPP_CHIETKHAU where HOPDONG_FK = '" + this.id + "'";
				System.out.println("[INIT_CK]"+query);
				ResultSet rsCK = db.get(query);
				if(rsCK != null)
				{
					String dkCK_diengiai = "";
					String dkCK_giatri = "";
					String dkCK_loai = "";
					
					while(rsCK.next())
					{
						dkCK_diengiai += rsCK.getString("DIENGIAI") + "__";
						dkCK_giatri += formater.format(rsCK.getDouble("GIATRI")) + "__";
						dkCK_loai += rsCK.getString("LOAI") + "__";
					}
					rsCK.close();
					
					if(dkCK_diengiai.trim().length() > 0)
					{
						dkCK_diengiai = dkCK_diengiai.substring(0, dkCK_diengiai.length() - 2);
						this.dhCk_diengiai = dkCK_diengiai.split("__");
						
						dkCK_giatri = dkCK_giatri.substring(0, dkCK_giatri.length() - 2);
						this.dhCk_giatri = dkCK_giatri.split("__");
						
						dkCK_loai = dkCK_loai.substring(0, dkCK_loai.length() - 2);
						this.dhCk_loai = dkCK_loai.split("__");
					}
					
				}
			} 
			catch (Exception e) 
			{
				System.out.println("115.Exception: " + e.getMessage());
			}
		}
		
	}
	
	
	public List<String> SanPham_theo_HopDong_chinh()
	{
		List<String> hash = new ArrayList<String>();
		try
		{
			if(this.hopdongId != null && this.hopdongId.length() > 0)
			{
				String query =  
						"select distinct sp.ma "+
						" from ERP_HopDongNPP_SANPHAM a inner join  sanpham sp on sp.pk_seq = a.sanpham_fk " +
						"where a.HOPDONG_FK = '" + this.hopdongId + "'  ";
				ResultSet rs= db.get(query);
				while(rs.next())
				{
					hash.add(rs.getString("ma"));
				}
			}
		}
		catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return hash;
	}
	
	
	private void initSANPHAM_THEOHD() 
	{
		String query =  
					"select a.stt, 0 as dadat,b.MA, b.TEN, DV.donvi, 0 as soluong, a.dongia, isnull(a.chietkhau, 0) as chietkhau, ISNULL(b.trongluong, 0) as trongluong, ISNULL(b.thetich, 0) as thetich, a.tungay, a.denngay, a.thueVAT    " +	
					"	,(select soluong1/ soluong2 from QUYCACH where sanpham_fk=a.sanpham_fk and DVDL1_FK=b.DVDL_FK and DVDL2_FK=100018)   as spQuyDoi "+
					" from ERP_HopDongNPP_SANPHAM a inner Join SanPham b on a.SANPHAM_FK = b.PK_SEQ    " +
					" 		INNER JOIN DONVIDOLUONG DV ON DV.PK_SEQ = a.DVDL_FK       " +
					"where a.HOPDONG_FK = '" + this.hopdongId + "' order by a.stt ";
		
		System.out.println("---INIT SP: " + query);
		ResultSet spRs = db.get(query);
		
		NumberFormat formater = new DecimalFormat("##,###,###");
		NumberFormat formater_2 = new DecimalFormat("##,###,###.####");
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
				String dadat="";
				
				while(spRs.next())
				{
					spSTT += spRs.getString("STT") + "__";
					spMA += spRs.getString("MA") + "__";
					spTEN += spRs.getString("TEN") + "__";
					spDONVI += spRs.getString("DONVI") + "__";
					spSOLUONG += formater.format(spRs.getDouble("SOLUONG")) + "__";
					spGIANHAP += formater_2.format(spRs.getDouble("DONGIA")) + "__";
					spCHIETKHAU += formater.format(spRs.getDouble("chietkhau")) + "__";
					spVAT += formater.format(spRs.getDouble("thueVAT")) + "__";
					
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
					dadat +=spRs.getString("dadat") + "__";
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
					
					dadat = dadat.substring(0, dadat.length() - 2);
					this.dadat = dadat.split("__");
					
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
		String query = "select isnull(lydokhongtrungthau,'') lydokhongtrungthau ,isnull(NgayNopHoSoThau,'')NgayNopHoSoThau,isnull(NgayMoThau,'')NgayMoThau,mahopdong, trangthai, tungay, denngay, ISNULL(ghichu, '') as ghichu, dvkd_fk, kbh_fk, gsbh_fk, ddkd_fk, khachhang_fk, kho_fk, isnull(chietkhau, 0) as chietkhau, vat, loaidonhang, hopdong_fk " +
						"from ERP_HopDongNPP where pk_seq = '" + this.id + "'";
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
					this.khId = rs.getString("khachhang_fk") == null ? "" : rs.getString("khachhang_fk");
					this.khoNhanId = rs.getString("kho_fk");
					this.loaidonhang = rs.getString("loaidonhang");
					this.chietkhau = rs.getString("chietkhau");
					this.vat = rs.getString("vat");
					this.gsbhId = rs.getString("gsbh_fk");
					this.ddkdId = rs.getString("ddkd_fk");
					this.trangthai = rs.getString("trangthai");
					this.NgayMoThau = rs.getString("NgayMoThau");
					this.NgayNopHoSoThau = rs.getString("NgayNopHoSoThau");
					this.ghichu=rs.getString("ghichu");
					this.LyDoKhongTrungThau=rs.getString("lydokhongtrungthau");
					if(rs.getString("hopdong_fk") != null)
						this.hopdongId = rs.getString("hopdong_fk");

				}
				rs.close();
				
				 
				
				
				query = "   select ghichu,ngay,loaighichu,isnull(linkFile,'')linkFile from erp_hopdongnpp_ghichu where hopdong_fk = '" + this.id + "' " +
						"	order by stt  ";
				ghichuRs = db.get(query);
				
				
				
				//
				query = "select khachhang_fk from ERP_HOPDONGNPP_APDUNG where hopdong_fk = '" + this.id + "'";
				System.out.println("=================== AP DUNG: " + query );
				rs = db.get(query);
				String khApdungId = "";
				while(rs.next())
				{
					khApdungId += rs.getString("khachhang_fk") + ",";
				}
				rs.close();
				
				if(khApdungId.trim().length() > 0)
				{
					khApdungId = khApdungId.substring(0, khApdungId.length() - 1);
					this.khApdungId = khApdungId;
				}
				
			} 
			catch (Exception e) 
			{
				System.out.println("---LOI INIT: " + e.getMessage());
			}
		}

		this.initSANPHAM();
		
		this.createRs();
		
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
	
	public String[] getSpVat() {
		
		return this.spVAT;
	}

	
	public void setSpVat(String[] spVat) {
		
		this.spVAT = spVat;
	}

	
	public String getHopdongId() {
		
		return this.hopdongId;
	}

	
	public void setHopdongId(String hopdongId) {
		
		this.hopdongId = hopdongId;
	}

	
	public ResultSet getHopdongRs() {
		
		return this.hopdongRs;
	}

	
	public void setHopdongRs(ResultSet hopdongRs) {
		
		this.hopdongRs = hopdongRs;
	}


	public String getHopdongchung() {

		return this.hopdongchung;
	}


	public void setHopdongchung(String hopdongchung) {
		
		this.hopdongchung = hopdongchung;
	}

	
	public String getKhApdungId() {
		
		return this.khApdungId;
	}

	
	public void setKhApdungId(String khApdungId) {
		
		this.khApdungId = khApdungId;
	}

	
	public ResultSet getKhApdungRs() {
		
		return this.khApdungRs;
	}

	
	public void setKhApdungRs(ResultSet khApdungRs) {
		
		this.khApdungRs = khApdungRs;
	}

	public static ResultSet getDonViTinhRs(Idbutils db , String spMa ) {
		String command = 	" select  donvi from DONVIDOLUONG where  pk_seq =  (select DVDL_FK from sanpham where ma ='"+spMa+"')\r\n" + 
				" union\r\n" + 
				" select donvi from DONVIDOLUONG\r\n" + 
				" where pk_seq in\r\n" + 
				" (\r\n" + 
				"	select DVDL2_FK from QUYCACH where SANPHAM_FK in (select pk_seq from sanpham where ma ='"+spMa+"')\r\n" + 
				" ) ";
		return db.get(command);
	}
	
	public boolean convertSO() 
	{
		if(this.khId.trim().length() <= 0)
		{
			this.msg = "Bạn phải chọn khách hàng ETC muốn chuyển SO";
			return false;
		}
		
		try 
		{
			db.getConnection().setAutoCommit(false);
			
			String query = " select loaidonhang from ERP_HOPDONGNPP where pk_seq = '" + this.id + "' ";
			ResultSet rs = db.get(query);
			String loaidonhang = "";
			if(rs.next())
			{
				loaidonhang = rs.getString("loaidonhang");
				rs.close();
			}
			
			if(loaidonhang.equals("0")) //Hóa đơn bình thường, chỉ được phép đặt bằng số còn lại
			{
				query = "select count(*) as soDONG  " +
						"from " +
						"( " +
						"	select sanpham_fk, dvdl_fk, sum(soluong) as soluong, avg(dongia) as dongia, avg(chietkhau) as chietkhau, avg(thuevat) as thuevat, tungay, denngay " +
						"	from " +
						"	( " +
						"		select sanpham_fk,  " +
						"			case when a.dvdl_fk IS null then a.soluong       " +
						"				 when a.dvdl_fk = b.DVDL_FK then a.soluong      " +
						"				 else  a.soluong * ( select SOLUONG1 from QUYCACH where sanpham_fk = a.sanpham_fk and DVDL2_FK = a.dvdl_fk )       " +
						"							/ ( select SOLUONG2 from QUYCACH where sanpham_fk = a.sanpham_fk and DVDL2_FK = a.dvdl_fk )	 end as soluong, dongia, chietkhau, thueVAT, b.pk_seq as dvdl_fk, tungay, denngay  " +
						"		from ERP_HOPDONGNPP_SANPHAM a inner join SANPHAM b on a.sanpham_fk = b.pk_seq  " +
						"		where HOPDONG_FK = '" + this.id + "'  " +
						"	union ALL " +
						"		select sanpham_fk,  " +
						"			case when a.dvdl_fk IS null then a.soluong       " +
						"				 when a.dvdl_fk = b.DVDL_FK then a.soluong      " +
						"				 else  a.soluong * ( select SOLUONG1 from QUYCACH where sanpham_fk = a.sanpham_fk and DVDL2_FK = a.dvdl_fk )       " +
						"							/ ( select SOLUONG2 from QUYCACH where sanpham_fk = a.sanpham_fk and DVDL2_FK = a.dvdl_fk )	 end as soluong, dongia, chietkhau, thueVAT, b.pk_seq as dvdl_fk, tungay, denngay  " +
						"		from ERP_HOPDONGNPP_SANPHAM a inner join SANPHAM b on a.sanpham_fk = b.pk_seq   " +
						"		where HOPDONG_FK in ( select hopdong_fk from ERP_HOPDONGNPP where hopdong_fk = '" + this.id + "' and trangthai in (1, 2) ) " +
						"	) " +
						"	hopdong group by sanpham_fk, dvdl_fk, tungay, denngay " +
						") " +
						"hd left join " +
						"( " +
						"	select sanpham_fk, sum(soluong) as daDAT " +
						"	from " +
						"	( " +
						"		select a.sanpham_fk, b.DVDL_FK as dvCHUAN,      " +
						"				case when a.dvdl_fk IS null then a.soluong       " +
						"					 when a.dvdl_fk = b.DVDL_FK then a.soluong      " +
						"					 else  a.soluong * ( select SOLUONG1 from QUYCACH where sanpham_fk = a.sanpham_fk and DVDL2_FK = a.dvdl_fk )       " +
						"									 / ( select SOLUONG2 from QUYCACH where sanpham_fk = a.sanpham_fk and DVDL2_FK = a.dvdl_fk ) end as soluong  " +
						"		from ERP_DONDATHANGNPP_SANPHAM a inner join SANPHAM b on a.sanpham_fk = b.PK_SEQ      " +
						"		where a.dondathang_fk in (   select pk_seq from ERP_DondathangNPP where trangthai != '3' and hopdong_fk = '" + this.id + "'  )     " +
						"	) " +
						"	dathang group by sanpham_fk " +
						") " +
						"dh on hd.sanpham_fk = dh.sanpham_fk " +
						"where hd.soluong > isnull(dh.daDAT, 0) ";  //KHONG CON SP NAO
				
				System.out.println("----CHECK SANPHAM: " + query );
				rs = db.get(query);
				int soDONG = 0;
				if(rs.next())
				{
					soDONG = rs.getInt("soDONG");
				}
				rs.close();
				
				if(soDONG <= 0)
				{
					msg = "Hợp đồng đã chuyển hết thành SO. Bạn không thể chuyển tiếp.";
					db.getConnection().rollback();
					return false;
				}
			}
				
			query = "update ERP_HOPDONGNPP set trangthai = '2' where pk_seq = '" + this.id + "'";
			if(!db.update(query))
			{
				msg = "Lỗi khi chuyển sang đơn đặt hàng: " + query;
				db.getConnection().rollback();
				return false;
			}
			
			query = " insert ERP_DondathangNPP(ngaydonhang, ngaydenghi, loaidonhang, npp_dachot, ghichu, trangthai, dvkd_fk, kbh_fk, gsbh_fk, ddkd_fk, khachhang_fk, npp_fk, kho_fk, hopdong_fk, chietkhau, vat, ngaytao, nguoitao, ngaysua, nguoisua) " +
					" select tungay, denngay, 0, 1 as npp_dachot, ghichu, 0 as trangthai, dvkd_fk, kbh_fk, gsbh_fk, ddkd_fk, '" + this.khId + "' as khachhang_fk, npp_fk, kho_fk, pk_seq, chietkhau, vat, ngaytao, nguoitao, ngaysua, nguoisua " +
					" from ERP_HOPDONGNPP where pk_seq = '" + this.id + "' ";
			System.out.println("-- INSERT DDH: " + query );
			if(!db.update(query))
			{
				msg = "Lỗi khi chuyển sang đơn đặt hàng: " + query;
				db.getConnection().rollback();
				return false;
			}
			
			//LAY ID
			ResultSet rsDDH = db.get("select IDENT_CURRENT('ERP_DondathangNPP') as ID ");
			if(rsDDH.next())
			{
				msg = rsDDH.getString("ID");
			}
			rsDDH.close();
			
			/*query = "insert ERP_DondathangNPP_SANPHAM( Dondathang_fk, SANPHAM_FK, soluong, dongia, chietkhau, thueVAT, dvdl_fk, tungay, denngay ) " +
					"select '" + msg + "', sanpham_fk, soluong, dongia, chietkhau, thueVAT, dvdl_fk, tungay, denngay " +
					"from ERP_HOPDONGNPP_SANPHAM where HOPDONG_FK = '" + lsxId + "' ";*/
			
			String sqlSOLUONG = " hd.soluong - isnull(dh.daDAT, 0) ";
			if(loaidonhang.equals("2")) //Hợp đồng nguyên tắc
				sqlSOLUONG = " isnull(hd.soluong, 0) ";
			
			query = "insert ERP_DondathangNPP_SANPHAM( Dondathang_fk, SANPHAM_FK, soluong, dongia, chietkhau, thueVAT, dvdl_fk, tungay, denngay ) " +
					"select '" + msg + "', hd.sanpham_fk, " + sqlSOLUONG + ", hd.dongia, hd.chietkhau, hd.thueVAT, hd.dvdl_fk, hd.tungay, hd.denngay " +
					"from " +
					"( " +
					"	select sanpham_fk, dvdl_fk, sum(soluong) as soluong, avg(dongia) as dongia, avg(chietkhau) as chietkhau, avg(thuevat) as thuevat, tungay, denngay " +
					"	from " +
					"	( " +
					"		select sanpham_fk,  " +
					"			case when a.dvdl_fk IS null then a.soluong       " +
					"				 when a.dvdl_fk = b.DVDL_FK then a.soluong      " +
					"				 else  a.soluong * ( select SOLUONG1 from QUYCACH where sanpham_fk = a.sanpham_fk and DVDL2_FK = a.dvdl_fk )       " +
					"							/ ( select SOLUONG2 from QUYCACH where sanpham_fk = a.sanpham_fk and DVDL2_FK = a.dvdl_fk )	 end as soluong, dongia, chietkhau, thueVAT, b.dvdl_fk as dvdl_fk, tungay, denngay  " +
					"		from ERP_HOPDONGNPP_SANPHAM a inner join SANPHAM b on a.sanpham_fk = b.pk_seq  " +
					"		where HOPDONG_FK = '" + this.id + "'  " +
					"	union ALL " +
					"		select sanpham_fk,  " +
					"			case when a.dvdl_fk IS null then a.soluong       " +
					"				 when a.dvdl_fk = b.DVDL_FK then a.soluong      " +
					"				 else  a.soluong * ( select SOLUONG1 from QUYCACH where sanpham_fk = a.sanpham_fk and DVDL2_FK = a.dvdl_fk )       " +
					"							/ ( select SOLUONG2 from QUYCACH where sanpham_fk = a.sanpham_fk and DVDL2_FK = a.dvdl_fk )	 end as soluong, dongia, chietkhau, thueVAT, b.dvdl_fk as dvdl_fk, tungay, denngay  " +
					"		from ERP_HOPDONGNPP_SANPHAM a inner join SANPHAM b on a.sanpham_fk = b.pk_seq   " +
					"		where HOPDONG_FK in ( select hopdong_fk from ERP_HOPDONGNPP where hopdong_fk = '" + this.id + "' and trangthai in (1, 2) ) " +
					"	) " +
					"	hopdong group by sanpham_fk, dvdl_fk, tungay, denngay " +
					") " +
					"hd left join " +
					"( " +
					"	select sanpham_fk, sum(soluong) as daDAT " +
					"	from " +
					"	( " +
					"		select a.sanpham_fk, b.DVDL_FK as dvCHUAN,      " +
					"				case when a.dvdl_fk IS null then a.soluong       " +
					"					 when a.dvdl_fk = b.DVDL_FK then a.soluong      " +
					"					 else  a.soluong * ( select SOLUONG1 from QUYCACH where sanpham_fk = a.sanpham_fk and DVDL2_FK = a.dvdl_fk )       " +
					"									 / ( select SOLUONG2 from QUYCACH where sanpham_fk = a.sanpham_fk and DVDL2_FK = a.dvdl_fk ) end as soluong  " +
					"		from ERP_DONDATHANGNPP_SANPHAM a inner join SANPHAM b on a.sanpham_fk = b.PK_SEQ      " +
					"		where a.dondathang_fk in (   select pk_seq from ERP_DondathangNPP where trangthai != '3' and hopdong_fk = '" + this.id + "'  )     " +
					"	) " +
					"	dathang group by sanpham_fk " +
					") " +
					"dh on hd.sanpham_fk = dh.sanpham_fk ";
			
			if(!loaidonhang.equals("2"))
					query += "where hd.soluong > isnull(dh.daDAT, 0) ";
			
			System.out.println("--CHEN SP: " + query);
			if(!db.update(query))
			{
				msg = "Lỗi khi chuyển sang đơn đặt hàng: " + query;
				db.getConnection().rollback();
				return false;
			}
			
			query = "insert ERP_DONDATHANGNPP_CHIETKHAU(DONDATHANG_FK, DIENGIAI, GIATRI, LOAI) " +
					"select '" + msg + "', DIENGIAI, GIATRI, LOAI from ERP_HOPDONGNPP_CHIETKHAU where hopdong_fk = '" + this.id + "' ";
			System.out.println("--CHEN SP 2: " + query);
			if(!db.update(query))
			{
				msg = "Lỗi khi chuyển sang đơn đặt hàng: " + query;
				db.getConnection().rollback();
				return false;
			}
			
			db.getConnection().commit();
		} 
		catch (Exception e)
		{
			e.printStackTrace();
			db.update("rollback");
			this.msg = "Lỗi: " + e.getMessage();
		}
		
		return true;
	}
	
	public String[] getNothd() {
		return nothd;
	}

	public void setNothd(String[] nothd) {
		this.nothd = nothd;
	}
	
	public String getNgayMoThau() {
		return NgayMoThau;
	}
	public String getNgayNopHoSoThau() {
		return NgayNopHoSoThau;
	}
	public String getLyDoKhongTrungThau() {
		return LyDoKhongTrungThau;
	}
	public void setNgayMoThau(String ngayMoThau) {
		NgayMoThau = ngayMoThau;
	}
	public void setNgayNopHoSoThau(String ngayNopHoSoThau) {
		NgayNopHoSoThau = ngayNopHoSoThau;
	}
	public void setLyDoKhongTrungThau(String lyDoKhongTrungThau) {
		LyDoKhongTrungThau = lyDoKhongTrungThau;
	}
	
	public ResultSet getGhichuRs() {
		return ghichuRs;
	}
	public void setGhichuRs(ResultSet ghichuRs) {
		this.ghichuRs = ghichuRs;
	}
	
	public static class SanPhamHopDong
	{
		public String spMa= "";
		public String spTen = "";
		public String dvdlId = "";
		public String kbhId = "";
		public String khoId = "";
		public String ngaynhapkho = "";
		public String ngayhethan = "";
		public String solo = "";
		public int loai = 0;
		public String ctkmId = "";
		public String trakmId ="";
	}
	public dbutils getDb() {
		return db;
	}
}
