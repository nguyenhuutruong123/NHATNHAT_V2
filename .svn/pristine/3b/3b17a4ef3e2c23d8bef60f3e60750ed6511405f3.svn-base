package geso.dms.center.beans.khoasothang.imp;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import geso.dms.center.beans.khoasothang.IErpKhoasothang;
import geso.dms.center.db.sql.dbutils;

public class ErpKhoaSoThang implements IErpKhoasothang 
{
	String userId;
	String thang;
	String nam;
	
	int sonhanhang;
	int sonhapkho;
	int soQc_NH;
	int soQc_LSX;
	int soTh_NH;
	int soTh_LSX;
	int coNhapKho_ChuaTH;
	int soDctk;
	int soKiemkho;
	int soHDNCC;
	int soxuatkho;
	int soxuatkhoCXHD;
	int sochuyenkho;
	int sodctk;
	int sohdtc;
	int solsx;
	int sophieuthu;
	int sophieuchi;
	int soBTTH;
	
	int sochaykhauhao;
	int sochaychenhlech;
	int sotinhtigia;
	
	ResultSet chungtuRs;
	int row;
	String msg;
	
	dbutils db;
	
	public ErpKhoaSoThang()
	{
		this.thang = "";
		this.nam = "";
		this.row = 0;
		
		this.sonhanhang = 0;
		this.sonhapkho = 0;
		this.soQc_NH = 0;
		this.soQc_LSX = 0;
		this.soTh_NH = 0;
		this.soTh_LSX = 0;
		this.coNhapKho_ChuaTH = 0;
		this.soDctk = 0;
		this.soKiemkho = 0;
		this.soHDNCC = 0;
		this.soxuatkho = 0;
		this.soxuatkhoCXHD = 0;
		this.sochuyenkho = 0;
		this.sodctk = 0;
		this.sohdtc = 0;
		this.solsx = 0;
		this.sophieuthu = 0;
		this.sophieuchi = 0;
		this.soBTTH = 0;
		
		this.sochaykhauhao = 0;
		this.sochaychenhlech = 0;
		this.sotinhtigia = 0;
		
		this.msg = "";
		
		db = new dbutils();
	}
	
	public String getUserId()
	{
		return this.userId;
	}

	public void setUserId(String userId) 
	{
		this.userId = userId;
	}

	public String getThang() 
	{
		return this.thang;
	}

	public void setThang(String thang) 
	{
		this.thang = thang;
	}

	public String getNam() 
	{
		return this.nam;
	}

	public void setNam(String nam) 
	{
		this.nam = nam;
	}

	public ResultSet getChungtuRs() 
	{
		return this.chungtuRs;
	}

	public void setChungtuRs(ResultSet ctRs) 
	{
		this.chungtuRs = ctRs;
	}

	public int getRow() 
	{
		return this.row;
	}

	public void setRow(int row) 
	{
		this.row = row;
	}

	public String getMsg() 
	{
		return this.msg;
	}

	public void setMsg(String msg)
	{
		this.msg = msg;
	}

	public String getDate() 
	{
		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		Date date = new Date();
		return dateFormat.format(date);
	}
	
	public void init()
	{		
		try 
		{
			String query = "select top(1) NAM as namMax, THANGKS as thangMax from ERP_KHOASOTHANG order by NAM desc, THANGKS desc ";
			System.out.println("1.Khoi tao thang: " + query);
			ResultSet rs = db.get(query);
			
			String thangKsMax = "";
			String namKsMax = "";
			
			if(rs != null)
			{
				while(rs.next())
				{
					thangKsMax = rs.getString("thangMax");
					namKsMax = rs.getString("namMax"); 
					
					if(thangKsMax.equals("12"))
					{
						this.thang = "1";
						this.nam = Integer.toString(Integer.parseInt(namKsMax) + 1);
 					}
					else
					{
						this.thang = Integer.toString(Integer.parseInt(thangKsMax) + 1);
						this.nam = namKsMax;
					}
				}
				rs.close();
			}
			
			//check so nhap kho chua chot trong thang
			String str = "";
			if(Integer.parseInt(this.thang) < 10)
				str = "0" + this.thang;
			else
				str = this.thang;
			
			String ngayChungTu = this.nam + "-" + str;
			String queryChungTu = "";
			
			query = "select *  " +
					"from  " +
					"(  " +
						"select count(pk_seq) as sodongNhapKho from ERP_NHAPKHO  " +
						"where trangthai = '0' and substring(NGAYNHAP, 0, 8) = '" + ngayChungTu + "'  " +
					") nhapkho,   " +
					"( " +
						"select count(pk_seq) as sodongChuyenKho from ERP_CHUYENKHO  " +
						"where trangthai in (0, 1) and substring(NGAYCHUYEN, 0, 8) = '" + ngayChungTu + "'  " +
					") chuyenkho,  " +
					"( " +
						"select count(pk_seq) as sodongDCTK from ERP_DIEUCHINHTONKHO  " +
						"where trangthai = '0' and substring(ngaydieuchinh, 0, 8) = '" + ngayChungTu + "' " +
					") dieuchinhtonkho ";
			
			System.out.println("Câu SQL" +query );
			rs = db.get(query);
			if(rs != null)
			{
				if(rs.next())
				{
					this.sonhapkho = rs.getInt("sodongNhapKho");
					this.sochuyenkho = rs.getInt("sodongChuyenKho");
					this.sodctk = rs.getInt("sodongDCTK");
				}
				rs.close();
			}
			
			String chungtuId = " ";
			String trangthai = " ";
			
			/***************** Nhap kho ***********************/
			
			if(this.sonhapkho > 0)
			{
				chungtuId = "Số chứng từ: ";
				trangthai = "Chưa hoàn tất";
				
				query = "select pk_seq from erp_nhapkho " +
						"where trangthai = '0'  and substring(NGAYNHAP, 0, 8) = '" + ngayChungTu + "'";
				ResultSet rsChungtu = db.get(query);
				while(rsChungtu.next())
				{
					chungtuId += rsChungtu.getString("pk_seq") + ", ";
				}
				rsChungtu.close();
			}
			else
			{
				chungtuId = " ";
				trangthai = "Hoàn tất";
			}
			
			queryChungTu += " select N'1.Kiểm tra chứng từ nhập kho' as type, N'" + chungtuId + "' as chungtu, N'" + trangthai + "' as trangthai  union all ";
			
			/**************************************************/
			
			
			/***************** Chuyển kho ***********************/
			
			if(this.sochuyenkho > 0)
			{
				chungtuId = "Số chứng từ: ";
				trangthai = "Chưa hoàn tất";
				
				query = "select pk_seq from ERP_CHUYENKHO  " +
						"where trangthai in ( '0', '1' ) and substring(NGAYCHUYEN, 0, 8) = '" + ngayChungTu + "'  ";
				
				ResultSet rsChungtu = db.get(query);
				while(rsChungtu.next())
				{
					chungtuId += rsChungtu.getString("pk_seq") + ", ";
				}
				rsChungtu.close();
			}
			else
			{
				chungtuId = " ";
				trangthai = "Hoàn tất";
			}
			
			queryChungTu += " select N'2.Kiểm tra chứng từ xuất chuyển nội bộ' as type, N'" + chungtuId + "' as chungtu, N'" + trangthai + "' as trangthai union all  ";
			
			/**************************************************/
			
			
			/***************** Điều chỉnh tồn kho ***********************/
			
			if(this.sodctk > 0)
			{
				chungtuId = "Số chứng từ: ";
				trangthai = "Chưa hoàn tất";
				
				query = "select pk_seq from ERP_DIEUCHINHTONKHOTT  " +
						"where trangthai = '0' and substring(ngaydieuchinh, 0, 8) = '" + ngayChungTu + "'  ";
				
				ResultSet rsChungtu = db.get(query);
				while(rsChungtu.next())
				{
					chungtuId += rsChungtu.getString("pk_seq") + ", ";
				}
				rsChungtu.close();
			}
			else
			{
				chungtuId = " ";
				trangthai = "Hoàn tất";
			}
			
			queryChungTu += " select N'3.Kiểm tra chứng từ điều chỉnh tồn kho' as type, N'" + chungtuId + "' as chungtu, N'" + trangthai + "' as trangthai  ";
			
			/**************************************************/
			
			System.out.println("___check chung tu: " + queryChungTu);
			this.chungtuRs = db.get(queryChungTu);
			
			
		} 
		catch (Exception e)
		{
			System.out.println("Exception: " + e.getMessage());
		}
	}
	
	public String KhoaSoThang()
	{
		try
		{
			String query = "select top(1) NAM as namMax, THANGKS as thangMax from ERP_KHOASOTHANG order by NAM desc, THANGKS desc ";
			System.out.println("1.Khoi tao thang: " + query);
			ResultSet rs = db.get(query);
			
			String thangKsMax = "";
			String namKsMax = "";
			
			if(rs != null)
			{
				while(rs.next())
				{
					thangKsMax = rs.getString("thangMax");
					namKsMax = rs.getString("namMax"); 
					
					if(thangKsMax.equals("12"))
					{
						this.thang = "1";
						this.nam = Integer.toString(Integer.parseInt(namKsMax) + 1);
						}
					else
					{
						this.thang = Integer.toString(Integer.parseInt(thangKsMax) + 1);
						this.nam = namKsMax;
					}
				}
				rs.close();
			}
			
			//check so nhap kho chua chot trong thang
			String str = "";
			if(Integer.parseInt(this.thang) < 10)
				str = "0" + this.thang;
			else
				str = this.thang;
			
			String ngayChungTu = this.nam + "-" + str;
			
			query = "select *  " +
					"from  " +
					"(  " +
						"select count(pk_seq) as sodongNhapKho from ERP_NHAPKHO  " +
						"where trangthai = '0' and substring(NGAYNHAP, 0, 8) = '" + ngayChungTu + "'  " +
					") nhapkho,   " +
					"( " +
						"select count(pk_seq) as sodongChuyenKho from ERP_CHUYENKHO  " +
						"where trangthai in (0, 1) and substring(NGAYCHUYEN, 0, 8) = '" + ngayChungTu + "'  " +
					") chuyenkho,  " +
					"( " +
						"select count(pk_seq) as sodongDCTK from ERP_DIEUCHINHTONKHO  " +
						"where trangthai = '0' and substring(ngaydieuchinh, 0, 8) = '" + ngayChungTu + "' " +
					") dieuchinhtonkho ";
			
			System.out.println("Câu SQL" +query );
			rs = db.get(query);
			if(rs != null)
			{
				if(rs.next())
				{
					this.sonhapkho = rs.getInt("sodongNhapKho");
					this.sochuyenkho = rs.getInt("sodongChuyenKho");
					this.sodctk = rs.getInt("sodongDCTK");
				}
				rs.close();
			}

			if(this.sonhapkho > 0)
				this.msg += "+ Vui lòng kiểm tra chứng từ NHẬP KHO \n";

			if(this.sochuyenkho > 0)
				this.msg += "+ Vui Lòng kiểm tra chứng từ CHUYỂN KHO \n";
			
			if(this.sodctk > 0)
				this.msg += "+ Vui lòng kiểm tra chứng từ ĐIỀU CHỈNH TỒN KHO \n";
			
			if(this.msg.trim().length() > 0)
			{
				this.msg = "Trước khi khóa sổ, bạn PHẢI KIỂM TRA các thông tin sau: \n" + this.msg;
				return this.msg;
			}
			
			db.getConnection().setAutoCommit(false);
			
			query = "insert erp_khoasothang(thangksgannhat, thangks, nam, ngaytao, nguoitao) " +
					"values('" + thangKsMax + "', '" + this.thang + "', '" + this.nam + "', '" + this.getDate() + "', '" + this.userId + "')";
			
			if(!db.update(query))
			{
				db.getConnection().rollback();
				return "Khong the khoa so thang: " + query;
			}
			
			/*****KHO TONG *******/
			
			query = "insert ERP_TONKHOTHANG(KHOTT_FK, SANPHAM_FK, GIATON, SOLUONG, THANHTIENTON, BOOKED, AVAILABLE, THANG, NAM)  " +
					"select KHOTT_FK, SANPHAM_FK, GIATON, SOLUONG, GIATON * SOLUONG, BOOKED, AVAILABLE, '" + this.thang + "', '" + this.nam + "'  " +
					"from ERP_KHOTT_SANPHAM ";
			
			if(!db.update(query))
			{
				db.getConnection().rollback();
				return "1.Khong the khoa so thang: " + query;
			}
			
			/*****END KHO TONG ****************************************************/
			
			
			
			/*****KHO CHI TIET*****************************************************/
			
			query = " insert ERP_TONKHOTHANG_CHITIET(KHOTT_FK, SANPHAM_FK, SOLUONG, BOOKED, AVAILABLE, SOLO, NGAYHETHAN, BIN, NGAYSANXUAT, THANG, NAM)  " +
					" select KHOTT_FK, SANPHAM_FK, SOLUONG, BOOKED, AVAILABLE, SOLO, NGAYHETHAN, BIN, NGAYSANXUAT, '" + this.thang + "', '" + this.nam + "' " +
					" from ERP_KHOTT_SP_CHITIET ";
			
			if(!db.update(query))
			{
				db.getConnection().rollback();
				return "2.Khong the khoa so thang: " + query;
			}
			
			/****END KHO CHI TIET**************************************************/
			
			db.getConnection().commit();
			db.getConnection().setAutoCommit(true);
			
		}
		catch (Exception e)
		{
			try 
			{
				db.getConnection().rollback();
			}
			catch (SQLException e1) {}
			return "7.Khong the Khoa so Thang: " + e.getMessage();
		}
		
		return "";
	}
	

	public int getSonhapkho() 
	{
		return this.sonhapkho;
	}

	public void setSonhapkho(int row) 
	{
		this.sonhapkho = row;
	}

	public int getSoxuatkho() 
	{
		return this.soxuatkho;
	}

	public void setSoxuatkho(int row)
	{
		this.soxuatkho = row;
	}

	public int getSochuyenkho() 
	{
		return this.sochuyenkho;
	}

	public void setSochuyenkho(int row) 
	{
		this.sochuyenkho = row;
	}

	public int getSodctk()
	{
		return this.sodctk;
	}

	public void setSodctk(int row) 
	{
		this.sodctk = row;
	}
	
	public int getSonhanhang() 
	{
		return this.sonhanhang;
	}

	public void setSonhanhang(int row) 
	{
		this.sonhanhang = row;
	}

	public int getSohdNCC() 
	{
		return this.soHDNCC;
	}

	public void setSohdNCC(int row)
	{
		this.soHDNCC = row;
	}

	public int getSoxuatkhoChuaNhanHD() 
	{
		return this.soxuatkhoCXHD;
	}

	public void setSoxuatkhoChuaNhanHD(int row) 
	{
		this.soxuatkhoCXHD = row;
	}

	public int getSoHdtc() 
	{
		return this.sohdtc;
	}

	public void setSoHdtc(int row) 
	{
		this.sohdtc = row;
	}

	public int getSoLsx() 
	{
		return this.solsx;
	}

	public void setSoLsx(int row) 
	{
		this.solsx = row;
	}

	public int getSoPhieuthu() 
	{
		return this.sophieuthu;
	}
	
	public void setSoPhieuthu(int row)
	{
		this.sophieuthu = row;
	}

	public int getSoPhieuchi() 
	{
		return this.sophieuchi;
	}

	public void setSoPhieuchi(int row) 
	{
		this.sophieuchi = row;
	}

	public int getSoBtth() 
	{
		return this.soBTTH;
	}

	public void setSoBtth(int row) 
	{
		this.soBTTH = row;
	}

	public int getChaykhauhao() 
	{
		return this.sochaykhauhao;
	}

	public void setChaykhauhao(int row) 
	{
		this.sochaykhauhao = row;
	}

	public int getChaychenhlechtigia() 
	{
		return this.sochaychenhlech;
	}

	public void setChaychenhlechtigia(int row)
	{
		this.sochaychenhlech = row;
	}

	public int getTinhgiathanh() 
	{
		return this.sotinhtigia;
	}

	public void setTinhgiathanh(int row)
	{
		this.sotinhtigia = row;
	}

	public void initKSKT() 
	{

		try 
		{
			String query = "select top(1) NAM as namMax, THANGKS as thangMax from ERP_KHOASOKETOAN order by NAM desc, THANGKS desc ";
			System.out.println("1.Khoi tao thang: " + query);
			ResultSet rs = db.get(query);
			
			String thangKsMax = "";
			String namKsMax = "";
			
			if(rs != null)
			{
				while(rs.next())
				{
					thangKsMax = rs.getString("thangMax");
					namKsMax = rs.getString("namMax"); 
					
					if(thangKsMax.equals("12"))
					{
						this.thang = "1";
						this.nam = Integer.toString(Integer.parseInt(namKsMax) + 1);
 					}
					else
					{
						this.thang = Integer.toString(Integer.parseInt(thangKsMax) + 1);
						this.nam = namKsMax;
					}
				}
				rs.close();
			}
			
			//check so nhap kho chua chot trong thang
			String str = "";
			if(Integer.parseInt(this.thang) < 10)
				str = "0" + this.thang;
			else
				str = this.thang;
			
			String ngayChungTu = this.nam + "-" + str;
			String queryChungTu = "";
			
			query = "select *  " +
					"from  " +
					"( " +
						"select count(*) as sodongKsk from ERP_KHOASOTHANG  " +
						"where thangks = '" + this.thang + "' and nam = '" + this.nam + "'  " +
					") khoasokho,  " +
					"( " +
						"select count(*) as sodongTGT from ERP_TINHGIAVON  " +
						"where thang = '" + this.thang + "' and nam = '" + this.nam + "'  " +
					") tinhgiavon, " +
					"(  " +
						"select count(pk_seq) as sodongHDTC from ERP_HOADON  " +
						"where trangthai = '0' and loaihoadon != 6 and substring(NGAYXUATHD, 0, 8) = '" + ngayChungTu + "' and NCC_FK is null " +
					") hoadon, " +
					"(  " +
						"select count(pk_seq) as sodongHDTVNCC from ERP_HOADON  " +
						"where trangthai = '0' and loaihoadon != 6 and substring(NGAYXUATHD, 0, 8) = '" + ngayChungTu + "' and NCC_FK is not null  " +
					") hoadontraveNCC, " +
					"(  " +
						"select count(pk_seq) as sodongHDTVKH from ERP_HOADON  " +
						"where trangthai = '0' and loaihoadon = 6 and substring(NGAYXUATHD, 0, 8) = '" + ngayChungTu + "' and NCC_FK is null " +
					") hoadontraveKH, " + 
					"( " +
						"select count(*) as sodongCPK from ERP_CHIPHIKHAC  " +
						"where trangthai = 0 and substring(NGAY, 0, 8) = '" + ngayChungTu + "'  " +
					") chiphikhac,  " +
					"( " +
						"select count(*) as sodongHDNCC from ERP_PARK  " +
						"where trangthai != 2 and substring(NGAYGHINHAN, 0, 8) = '" + ngayChungTu + "'  " +
					") park,  " +
					"( " +
						"select count(*) as sodongTTNCC from ERP_THANHTOANHOADON  " +
						"where trangthai = 0 and substring(NGAYGHINHAN, 0, 8) = '" + ngayChungTu + "'  " +
					") thanhtoanNCC,  " +
					"( " +
						"select count(*) as sodongTT from ERP_THUTIEN  " +
						"where trangthai = 0 and substring(NGAYGHISO, 0, 8) = '" + ngayChungTu + "'  " +
					") thutienKH,  " +
					"( " +
						"select count(*) as sodongXKHTT from ERP_XOAKHTRATRUOC  " +
						"where trangthai = 0 and substring(NGAYGHISO, 0, 8) = '" + ngayChungTu + "'  " +
					") xoanoKH,  " +
					"( " +
						"select count(*) as sodongNTV from ERP_NHANTIENVAY  " +
						"where trangthai = 0 and substring(NGAYNHAN, 0, 8) = '" + ngayChungTu + "'  " +
					") nhantienvay,  " +
					"( " +
						"select count(*) as sodongTTNV from ERP_THANHTOANNOVAY  " +
						"where trangthai = 0 and substring(NGAY, 0, 8) = '" + ngayChungTu + "'  " +
					") thanhtoannovay,  " +
					"( " +
						"select count(*) as sodongGGHB from ERP_GiamGiaHangBan  " +
						"where trangthai = 0 and substring(NGAYGHINHAN, 0, 8) = '" + ngayChungTu + "'  " +
					") giamgiahangban,  " +
					"( " +
						"select count(*) as sodongHDPL from ERP_HoaDonPheLieu  " +
						"where trangthai = 0 and substring(NGAYGHINHAN, 0, 8) = '" + ngayChungTu + "'  " +
					") hoadonphelieu,  " +
					"( " +
						"select count(*) as sodongBTTH from ERP_BUTTOANTONGHOP  " +
						"where trangthai = 0 and substring(NGAYBUTTOAN, 0, 8) = '" + ngayChungTu + "'  " +
					") buttoantonghop,  " +
					"( " +
						"select count(*) as sodongDGTG from ERP_DANHGIATIGIA  " +
						"where thang = '" + this.thang + "' and nam = '" + this.nam + "'  " +
					") danhgiatigia ";
			
			System.out.println("___Check data: " + query);
			rs = db.get(query);
			if(rs != null)
			{
				if(rs.next())
				{
					this.row = rs.getInt("sodongKsk");
					this.sochaychenhlech = rs.getInt("sodongTGT");
					
					
					String chungtuId = " ";
					String trangthai = " ";
					
					
					/***************** Check khoa so kho ***********************/
					System.out.println("So ROW la: " + this.row);
					if(this.row <= 0)
					{
						chungtuId = " ";
						trangthai = "Chưa hoàn tất";	
					}
					else
					{
						chungtuId = " ";
						trangthai = "Hoàn tất";
					}
					
					queryChungTu += " select N'1.Kiểm tra thực hiện khóa sổ kho' as type, N'' as chungtu,  N'" + trangthai + "' as trangthai union all  ";
					/**************************************************/
					
					
					/***************** Tinh gia thành ***********************/
					
					if(this.sochaychenhlech <= 0)
					{
						chungtuId = " ";
						trangthai = "Chưa hoàn tất";
						
					}
					else
					{
						chungtuId = " ";
						trangthai = "Hoàn tất";
					}
					
					queryChungTu += " select N'2.Kiểm tra chạy tính giá thành sản phẩm & cập nhật giá xuất kho' as type, N'' as chungtu, N'" + trangthai + "' as trangthai union all ";
					
					/**************************************************/
					
					
					/***************** Hóa đơn ***********************/
					
					if( rs.getInt("sodongHDTC") > 0)
					{
						chungtuId = "Số chứng từ: ";
						trangthai = "Chưa hoàn tất";
						
						query = "select pk_seq from ERP_HOADON  " +
								"where trangthai = '0' and loaihoadon != 6 and substring(NGAYXUATHD, 0, 8) = '" + ngayChungTu + "' and NCC_FK is null  ";
						System.out.println("HDTC: " + query);
						ResultSet rsChungtu = db.get(query);
						while(rsChungtu.next())
						{
							chungtuId += rsChungtu.getString("pk_seq") + ", ";
						}
						rsChungtu.close();
					}
					else
					{
						chungtuId = " ";
						trangthai = "Hoàn tất";
					}
					
					queryChungTu += " select N'3.Kiểm tra hóa đơn tài chính' as type, N'" + chungtuId + "' as chungtu, N'" + trangthai + "' as trangthai  union all ";
					
					
					if( rs.getInt("sodongHDTVNCC") > 0)
					{
						chungtuId = "Số chứng từ: ";
						trangthai = "Chưa hoàn tất";
						
						query = "select pk_seq from ERP_HOADON  " +
								"where trangthai = '0' and loaihoadon != 6 and substring(NGAYXUATHD, 0, 8) = '" + ngayChungTu + "' and NCC_FK is not null ";
						
						ResultSet rsChungtu = db.get(query);
						while(rsChungtu.next())
						{
							chungtuId += rsChungtu.getString("pk_seq") + ", ";
						}
						rsChungtu.close();
					}
					else
					{
						chungtuId = " ";
						trangthai = "Hoàn tất";
					}
					
					queryChungTu += " select N'4.Kiểm tra hóa đơn trả hàng nhà cung cấp' as type, N'" + chungtuId + "' as chungtu, N'" + trangthai + "' as trangthai  union all ";
					
					
					if( rs.getInt("sodongHDTVKH") > 0)
					{
						chungtuId = "Số chứng từ: ";
						trangthai = "Chưa hoàn tất";
						
						query = "select pk_seq from ERP_HOADON  " +
								"where trangthai = '0' and loaihoadon = '6' and substring(NGAYXUATHD, 0, 8) = '" + ngayChungTu + "' and NCC_FK is null  ";
						
						ResultSet rsChungtu = db.get(query);
						while(rsChungtu.next())
						{
							chungtuId += rsChungtu.getString("pk_seq") + ", ";
						}
						rsChungtu.close();
					}
					else
					{
						chungtuId = " ";
						trangthai = "Hoàn tất";
					}
					
					queryChungTu += " select N'5.Kiểm tra hóa đơn trả hàng khách hàng' as type, N'" + chungtuId + "' as chungtu, N'" + trangthai + "' as trangthai  union all ";
					
					/**************************************************/
					
					
					/***************** CHUNG TU KHAC ***********************/
					
					if( rs.getInt("sodongCPK") > 0)
					{
						chungtuId = "Số chứng từ: ";
						trangthai = "Chưa hoàn tất";
						
						query = "select pk_seq from ERP_CHIPHIKHAC  " +
								"where trangthai = '0' and substring(ngay, 0, 8) = '" + ngayChungTu + "'   ";
						
						ResultSet rsChungtu = db.get(query);
						while(rsChungtu.next())
						{
							chungtuId += rsChungtu.getString("pk_seq") + ", ";
						}
						rsChungtu.close();
					}
					else
					{
						chungtuId = " ";
						trangthai = "Hoàn tất";
					}
					
					queryChungTu += " select N'6.Kiểm tra chứng từ chi phí khác' as type, N'" + chungtuId + "' as chungtu, N'" + trangthai + "' as trangthai  union all ";
					
					
					if( rs.getInt("sodongHDNCC") > 0)
					{
						chungtuId = "Số chứng từ: ";
						trangthai = "Chưa hoàn tất";
						
						query = "select pk_seq from ERP_PARK  " +
								"where trangthai != '2' and substring(NGAYGHINHAN, 0, 8) = '" + ngayChungTu + "'   ";
						
						ResultSet rsChungtu = db.get(query);
						while(rsChungtu.next())
						{
							chungtuId += rsChungtu.getString("pk_seq") + ", ";
						}
						rsChungtu.close();
					}
					else
					{
						chungtuId = " ";
						trangthai = "Hoàn tất";
					}
					
					queryChungTu += " select N'7.Kiểm tra chứng từ PARK hóa đơn' as type, N'" + chungtuId + "' as chungtu, N'" + trangthai + "' as trangthai  union all ";
					
					
					if( rs.getInt("sodongTTNCC") > 0)
					{
						chungtuId = "Số chứng từ: ";
						trangthai = "Chưa hoàn tất";
						
						query = "select pk_seq from ERP_THANHTOANHOADON  " +
								"where trangthai = '0' and substring(NGAYGHINHAN, 0, 8) = '" + ngayChungTu + "'   ";
						
						ResultSet rsChungtu = db.get(query);
						while(rsChungtu.next())
						{
							chungtuId += rsChungtu.getString("pk_seq") + ", ";
						}
						rsChungtu.close();
					}
					else
					{
						chungtuId = " ";
						trangthai = "Hoàn tất";
					}
					
					queryChungTu += " select N'8.Kiểm tra chứng từ thanh toán hóa đơn NCC' as type, N'" + chungtuId + "' as chungtu, N'" + trangthai + "' as trangthai  union all ";
					
					
					if( rs.getInt("sodongTT") > 0)
					{
						chungtuId = "Số chứng từ: ";
						trangthai = "Chưa hoàn tất";
						
						query = "select pk_seq from ERP_THUTIEN  " +
								"where trangthai = '0' and substring(NGAYGHISO, 0, 8) = '" + ngayChungTu + "'   ";
						
						ResultSet rsChungtu = db.get(query);
						while(rsChungtu.next())
						{
							chungtuId += rsChungtu.getString("pk_seq") + ", ";
						}
						rsChungtu.close();
					}
					else
					{
						chungtuId = " ";
						trangthai = "Hoàn tất";
					}
					
					queryChungTu += " select N'9.Kiểm tra chứng từ thu tiền khách hàng' as type, N'" + chungtuId + "' as chungtu, N'" + trangthai + "' as trangthai union all  ";
					
					
					if( rs.getInt("sodongXKHTT") > 0)
					{
						chungtuId = "Số chứng từ: ";
						trangthai = "Chưa hoàn tất";
						
						query = "select pk_seq from ERP_XOAKHTRATRUOC  " +
								"where trangthai = '0' and substring(NGAYGHISO, 0, 8) = '" + ngayChungTu + "'   ";
						
						ResultSet rsChungtu = db.get(query);
						while(rsChungtu.next())
						{
							chungtuId += rsChungtu.getString("pk_seq") + ", ";
						}
						rsChungtu.close();
					}
					else
					{
						chungtuId = " ";
						trangthai = "Hoàn tất";
					}
					
					queryChungTu += " select N'10.Kiểm tra chứng từ xóa khách hàng trả trước' as type, N'" + chungtuId + "' as chungtu, N'" + trangthai + "' as trangthai union all  ";
					
					
					if( rs.getInt("sodongNTV") > 0)
					{
						chungtuId = "Số chứng từ: ";
						trangthai = "Chưa hoàn tất";
						
						query = "select pk_seq from ERP_NHANTIENVAY  " +
								"where trangthai = '0' and substring(NGAYNHAN, 0, 8) = '" + ngayChungTu + "'   ";
						
						ResultSet rsChungtu = db.get(query);
						while(rsChungtu.next())
						{
							chungtuId += rsChungtu.getString("pk_seq") + ", ";
						}
						rsChungtu.close();
					}
					else
					{
						chungtuId = " ";
						trangthai = "Hoàn tất";
					}
					
					queryChungTu += " select N'11.Kiểm tra chứng từ nhận tiền vay' as type, N'" + chungtuId + "' as chungtu, N'" + trangthai + "' as trangthai union all  ";
					
					
					if( rs.getInt("sodongTTNV") > 0)
					{
						chungtuId = "Số chứng từ: ";
						trangthai = "Chưa hoàn tất";
						
						query = "select pk_seq from ERP_THANHTOANNOVAY  " +
								"where trangthai = '0' and substring(NGAY, 0, 8) = '" + ngayChungTu + "'   ";
						
						ResultSet rsChungtu = db.get(query);
						while(rsChungtu.next())
						{
							chungtuId += rsChungtu.getString("pk_seq") + ", ";
						}
						rsChungtu.close();
					}
					else
					{
						chungtuId = " ";
						trangthai = "Hoàn tất";
					}
					
					queryChungTu += " select N'12.Kiểm tra chứng từ thanh toán nợ vay' as type, N'" + chungtuId + "' as chungtu, N'" + trangthai + "' as trangthai union all  ";
					
					
					if( rs.getInt("sodongGGHB") > 0)
					{
						chungtuId = "Số chứng từ: ";
						trangthai = "Chưa hoàn tất";
						
						query = "select pk_seq from ERP_GiamGiaHangBan  " +
								"where trangthai = '0' and substring(NGAYGHINHAN, 0, 8) = '" + ngayChungTu + "'   ";
						
						ResultSet rsChungtu = db.get(query);
						while(rsChungtu.next())
						{
							chungtuId += rsChungtu.getString("pk_seq") + ", ";
						}
						rsChungtu.close();
					}
					else
					{
						chungtuId = " ";
						trangthai = "Hoàn tất";
					}
					
					queryChungTu += " select N'13.Kiểm tra chứng từ giảm giá hàng bán' as type, N'" + chungtuId + "' as chungtu, N'" + trangthai + "' as trangthai union all  ";
					
					
					if( rs.getInt("sodongHDPL") > 0)
					{
						chungtuId = "Số chứng từ: ";
						trangthai = "Chưa hoàn tất";
						
						query = "select pk_seq from ERP_HoaDonPheLieu  " +
								"where trangthai = '0' and substring(NGAYGHINHAN, 0, 8) = '" + ngayChungTu + "'   ";
						
						ResultSet rsChungtu = db.get(query);
						while(rsChungtu.next())
						{
							chungtuId += rsChungtu.getString("pk_seq") + ", ";
						}
						rsChungtu.close();
					}
					else
					{
						chungtuId = " ";
						trangthai = "Hoàn tất";
					}
					
					queryChungTu += " select N'14.Kiểm tra chứng từ hóa đơn phế liệu' as type, N'" + chungtuId + "' as chungtu, N'" + trangthai + "' as trangthai union all  ";
					
					
					if( rs.getInt("sodongBTTH") > 0)
					{
						chungtuId = "Số chứng từ: ";
						trangthai = "Chưa hoàn tất";
						
						query = "select pk_seq from ERP_BUTTOANTONGHOP  " +
								"where trangthai = '0' and substring(NGAYBUTTOAN, 0, 8) = '" + ngayChungTu + "'   ";
						
						ResultSet rsChungtu = db.get(query);
						while(rsChungtu.next())
						{
							chungtuId += rsChungtu.getString("pk_seq") + ", ";
						}
						rsChungtu.close();
					}
					else
					{
						chungtuId = " ";
						trangthai = "Hoàn tất";
					}
					
					queryChungTu += " select N'15.Kiểm tra chứng từ bút toán tổng hợp' as type, N'" + chungtuId + "' as chungtu, N'" + trangthai + "' as trangthai union all  ";
					
					
					if( rs.getInt("sodongDGTG") > 0)
					{
						chungtuId = "Số chứng từ: ";
						trangthai = "Chưa hoàn tất";
						
						query = "select pk_seq from ERP_DANHGIATIGIA  " +
								"where thang = '" + this.thang + "' and nam = '" + this.nam + "'  ";
						
						ResultSet rsChungtu = db.get(query);
						while(rsChungtu.next())
						{
							chungtuId += rsChungtu.getString("pk_seq") + ", ";
						}
						rsChungtu.close();
					}
					else
					{
						chungtuId = " ";
						trangthai = "Hoàn tất";
					}
					
					queryChungTu += " select N'16.Kiểm tra chứng từ đánh giá tỷ giá' as type, N'" + chungtuId + "' as chungtu, N'" + trangthai + "' as trangthai   ";
					
					/**************************************************/
					
					
					
					System.out.println("___check chung tu: " + queryChungTu);
					this.chungtuRs = db.get(queryChungTu);
					
					
				}
				rs.close();
			}
			
		} 
		catch (Exception e)
		{
			e.printStackTrace();
			System.out.println("Exception: " + e.getMessage());
		}	
	
	}

	
	public String KhoaSoKeToan() 
	{

		String query = "select top(1) NAM as namMax, THANGKS as thangMax from ERP_KHOASOKETOAN order by NAM desc, THANGKS desc ";
		
		try
		{
		
			ResultSet rs = db.get(query);
			
			String thangKsMax = "";
			String namKsMax = "";
			
			if(rs != null)
			{
				while(rs.next())
				{
					thangKsMax = rs.getString("thangMax");
					namKsMax = rs.getString("namMax"); 
					
					if(thangKsMax.equals("12"))
					{
						this.thang = "1";
						this.nam = Integer.toString(Integer.parseInt(namKsMax) + 1);
						}
					else
					{
						this.thang = Integer.toString(Integer.parseInt(thangKsMax) + 1);
						this.nam = namKsMax;
					}
				}
				rs.close();
			}
			
			//check so nhap kho chua chot trong thang
			String str = "";
			if(Integer.parseInt(this.thang) < 10)
				str = "0" + this.thang;
			else
				str = this.thang;
			
			String ngayChungTu = this.nam + "-" + str;
			
			query = "select *  " +
					"from  " +
					"( " +
						"select count(*) as sodongKsk from ERP_KHOASOTHANG  " +
						"where thangks = '" + this.thang + "' and nam = '" + this.nam + "'  " +
					") khoasokho,  " +
					"( " +
						"select count(*) as sodongTGT from ERP_TINHGIAVON  " +
						"where thang = '" + this.thang + "' and nam = '" + this.nam + "'  " +
					") tinhgiavon, " +
					"(  " +
						"select count(pk_seq) as sodongHDTC from ERP_HOADON  " +
						"where trangthai = '0' and loaihoadon != 6 and substring(NGAYXUATHD, 0, 8) = '" + ngayChungTu + "' and NCC_FK is null " +
					") hoadon, " +
					"(  " +
						"select count(pk_seq) as sodongHDTVNCC from ERP_HOADON  " +
						"where trangthai = '0' and loaihoadon != 6 and substring(NGAYXUATHD, 0, 8) = '" + ngayChungTu + "' and NCC_FK is not null  " +
					") hoadontraveNCC, " +
					"(  " +
						"select count(pk_seq) as sodongHDTVKH from ERP_HOADON  " +
						"where trangthai = '0' and loaihoadon = 6 and substring(NGAYXUATHD, 0, 8) = '" + ngayChungTu + "' and NCC_FK is null " +
					") hoadontraveKH, " + 
					"( " +
						"select count(*) as sodongCPK from ERP_CHIPHIKHAC  " +
						"where trangthai = 0 and substring(NGAY, 0, 8) = '" + ngayChungTu + "'  " +
					") chiphikhac,  " +
					"( " +
						"select count(*) as sodongHDNCC from ERP_PARK  " +
						"where trangthai != 2 and substring(NGAYGHINHAN, 0, 8) = '" + ngayChungTu + "'  " +
					") park,  " +
					"( " +
						"select count(*) as sodongTTNCC from ERP_THANHTOANHOADON  " +
						"where trangthai = 0 and substring(NGAYGHINHAN, 0, 8) = '" + ngayChungTu + "'  " +
					") thanhtoanNCC,  " +
					"( " +
						"select count(*) as sodongTT from ERP_THUTIEN  " +
						"where trangthai = 0 and substring(NGAYGHISO, 0, 8) = '" + ngayChungTu + "'  " +
					") thutienKH,  " +
					"( " +
						"select count(*) as sodongXKHTT from ERP_XOAKHTRATRUOC  " +
						"where trangthai = 0 and substring(NGAYGHISO, 0, 8) = '" + ngayChungTu + "'  " +
					") xoanoKH,  " +
					"( " +
						"select count(*) as sodongNTV from ERP_NHANTIENVAY  " +
						"where trangthai = 0 and substring(NGAYNHAN, 0, 8) = '" + ngayChungTu + "'  " +
					") nhantienvay,  " +
					"( " +
						"select count(*) as sodongTTNV from ERP_THANHTOANNOVAY  " +
						"where trangthai = 0 and substring(NGAY, 0, 8) = '" + ngayChungTu + "'  " +
					") thanhtoannovay,  " +
					"( " +
						"select count(*) as sodongGGHB from ERP_GiamGiaHangBan  " +
						"where trangthai = 0 and substring(NGAYGHINHAN, 0, 8) = '" + ngayChungTu + "'  " +
					") giamgiahangban,  " +
					"( " +
						"select count(*) as sodongHDPL from ERP_HoaDonPheLieu  " +
						"where trangthai = 0 and substring(NGAYGHINHAN, 0, 8) = '" + ngayChungTu + "'  " +
					") hoadonphelieu,  " +
					"( " +
						"select count(*) as sodongBTTH from ERP_BUTTOANTONGHOP  " +
						"where trangthai = 0 and substring(NGAYBUTTOAN, 0, 8) = '" + ngayChungTu + "'  " +
					") buttoantonghop,  " +
					"( " +
						"select count(*) as sodongDGTG from ERP_DANHGIATIGIA  " +
						"where thang = '" + this.thang + "' and nam = '" + this.nam + "'  " +
					") danhgiatigia ";
			
			System.out.println("___Check data: " + query);
			rs = db.get(query);
			if(rs != null)
			{
				if(rs.next())
				{
					if(rs.getInt("sodongKsk") <= 0)
						this.msg += "+ Vui lòng thực hiện KHÓA SỔ KHO trước khi khóa sổ kế toán \n";
					
					if(rs.getInt("sodongTGT") <= 0)
						this.msg += "+ Vui lòng thực hiện TÍNH GIÁ THÀNH trước khi khóa sổ kế toán \n";
					
					if(rs.getInt("sodongHDTC") > 0)
						this.msg += "+ Vui lòng kiểm tra chứng từ HÓA ĐƠN TÀI CHÍNH \n";
					
					if(rs.getInt("sodongHDTVNCC") > 0)
						this.msg += "+ Vui lòng kiểm tra chứng từ HÓA ĐƠN TRẢ VỀ NHÀ CUNG CẤP \n";
					
					if(rs.getInt("sodongHDTVKH") > 0)
						this.msg += "+ Vui lòng kiểm tra chứng từ HÓA ĐƠN TRẢ VỀ KHÁCH HÀNG \n";
					
					if(rs.getInt("sodongCPK") > 0)
						this.msg += "+ Vui lòng kiểm tra chứng từ CHI PHÍ KHÁC \n";
					
					if(rs.getInt("sodongHDNCC") > 0)
						this.msg += "+ Vui lòng kiểm tra chứng từ PARK HÓA ĐƠN \n";
					
					if(rs.getInt("sodongTTNCC") > 0)
						this.msg += "+ Vui lòng kiểm tra chứng từ THANH TOÁN NHÀ CUNG CẤP \n";
					
					if(rs.getInt("sodongTT") > 0)
						this.msg += "+ Vui lòng kiểm tra chứng từ THU TIỀN KHÁCH HÀNG \n";
					
					if(rs.getInt("sodongXKHTT") > 0)
						this.msg += "+ Vui lòng kiểm tra chứng từ XÓA KHÁCH HÀNG TRẢ TRƯỚC \n";
					
					if(rs.getInt("sodongNTV") > 0)
						this.msg += "+ Vui lòng kiểm tra chứng từ NHẬN TIỀN VAY \n";
					
					if(rs.getInt("sodongTTNV") > 0)
						this.msg += "+ Vui lòng kiểm tra chứng từ THANH TOÁN NỢ VAY \n";
					
					if(rs.getInt("sodongGGHB") > 0)
						this.msg += "+ Vui lòng kiểm tra chứng từ GIẢM GIÁ HÀNG BÁN \n";
					
					if(rs.getInt("sodongHDPL") > 0)
						this.msg += "+ Vui lòng kiểm tra chứng từ HÓA ĐƠN PHẾ LIỆU \n";
					
					if(rs.getInt("sodongBTTH") > 0)
						this.msg += "+ Vui lòng kiểm tra chứng từ BÚT TOÁN TỔNG HỢP \n";
					
					if(rs.getInt("sodongDGTG") > 0)
						this.msg += "+ Vui lòng kiểm tra chứng từ ĐÁNH GIÁ TỶ GIÁ \n";

				}
				rs.close();
			}
			
			if(this.msg.trim().length() > 0)
			{
				this.msg = "Trước khi khóa sổ, bạn PHẢI KIỂM TRA các thông tin sau: \n" + this.msg;
				return this.msg;
			}
			
			db.getConnection().setAutoCommit(false);
			
			query = "insert ERP_KHOASOKETOAN(thangksgannhat, thangks, nam, ngaytao, nguoitao) " +
					"values('" + thangKsMax + "', '" + this.thang + "', '" + this.nam + "', '" + this.getDate() + "', '" + this.userId + "')";
			
			if(!db.update(query))
			{
				db.getConnection().rollback();
				return "Khong the khoa so thang: " + query;
			}
			
			/***************** No co ******************/
			query = "select TAIKHOANKT_FK, GIATRICOVND, GIATRINOVND, NGUYENTE_FK, GIATRINGUYENTE, GIATRICONGUYENTE, GIATRINONGUYENTE " +
					"from ERP_TAIKHOAN_NOCO where thang = '" + this.thang + "' and nam = '" + this.nam + "' ";
			ResultSet rsTaikhoan = db.get(query);
			if(rsTaikhoan != null)
			{
				String thangTiep = "";
				String namTiep = "";
				if(this.thang.equals("12"))
				{
					thangTiep = "1";
					namTiep = Integer.toString(Integer.parseInt(this.nam) + 1);
				}
				else
				{
					thangTiep = Integer.toString(Integer.parseInt(this.thang) + 1);
					namTiep = this.nam;
				}
				
				
				while(rsTaikhoan.next())
				{
					String taikhoan_fk = rsTaikhoan.getString("TAIKHOANKT_FK");
					
					String nguyente_fk = rsTaikhoan.getString("NGUYENTE_FK");
					double giatringuyente = rsTaikhoan.getDouble("GIATRINGUYENTE");
					
					double chenhlech = rsTaikhoan.getDouble("GIATRINOVND") - rsTaikhoan.getDouble("GIATRICOVND");
					double GIATRICOVND = 0;
					double GIATRINOVND = 0;
					
					double chenhlechNT = rsTaikhoan.getDouble("GIATRINONGUYENTE") - rsTaikhoan.getDouble("GIATRICONGUYENTE");
					double GIATRICONT = 0;
					double GIATRINONT = 0;
					
					if(chenhlech > 0)
						GIATRINOVND = Math.abs(chenhlech);
					else
						GIATRICOVND = Math.abs(chenhlech);
					
					if(chenhlechNT > 0)
						GIATRINONT = Math.abs(chenhlechNT);
					else
						GIATRICONT = Math.abs(chenhlechNT);
					
					//BAT BUOC NGUOI DUNG CHI DUOC THUC HIEN NGHIEP VU SAU KHI DA TIEN HANH MO KY MOI
					query = "insert ERP_TAIKHOAN_NOCO(TAIKHOANKT_FK, GIATRICOVND, GIATRINOVND, NGUYENTE_FK, GIATRINGUYENTE, GIATRICONGUYENTE, GIATRINONGUYENTE, THANG, NAM) " +
							"values('" + taikhoan_fk + "', " + GIATRICOVND + ", " + GIATRINOVND + ", '" + nguyente_fk + "', " + giatringuyente + ", " + GIATRICONT + ", " + GIATRINONT + ", '" + thangTiep + "', '" + namTiep + "')";
					
					if(!db.update(query))
					{
						db.getConnection().rollback();
						return "6.Không thể khóa sổ kế toán: " + query;
					}
					
				}
				rsTaikhoan.close();
			}
			
			
			db.getConnection().commit();
			db.getConnection().setAutoCommit(true);
			
			return "";
		}
		catch (Exception e) 
		{
			db.update("rollback");
			return "Không thể khóa sổ kế toán: " + e.getMessage();
		}
		
		
	
	}

	
	public int getSoQC_NhanHang() {
		
		return this.soQc_NH;
	}

	
	public void setSoQC_NhanHang(int row) {
		
		this.soQc_NH = row;
	}

	
	public int getSoQC_LSX() {
		
		return this.soQc_LSX;
	}

	
	public void setSoQC_LSX(int row) {
		
		this.soQc_LSX = row;
	}

	
	public int getSoTH_NhanHang() {
		
		return this.soTh_NH;
	}

	
	public void setSoTH_NhanHang(int row) {
		
		this.soTh_NH = row;
	}

	
	public int getSoTH_LSX() {
		
		return this.soTh_LSX;
	}

	
	public void setSoTH_LSX(int row) {
		
		this.soTh_LSX = row;
	}

	
	public int getSoDctk() {
		
		return this.soDctk;
	}

	
	public void setSoDctk(int row) {
		
		this.soDctk = row;
	}

	
	public int getSoKiemkho() {
		
		return this.soKiemkho;
	}

	
	public void setSoKiemkho(int row) {
		
		this.soKiemkho = row;
	}

	
	public int getCoNhapKho_ChuaTH() {
		
		return this.coNhapKho_ChuaTH;
	}

	
	public void setCoNhapKho_ChuaTH(int row) {
		
		this.coNhapKho_ChuaTH = row;
	} 

}

