package geso.dms.center.beans.hoadontaichinh.imp;

import geso.dms.center.beans.hoadontaichinh.IErpHoadontaichinh;
import geso.dms.distributor.db.sql.dbutils;
import geso.dms.center.util.Utility;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Enumeration;
import java.util.Hashtable;

public class ErpHoadontaichinh implements IErpHoadontaichinh
{
	String userId;
	String id;
	
	String ngayxuatHD;
	String ngayghinhanCN;
	String ghichu;
	String ptchietkhau;
	String kyhieuhoadon;
	String sohoadon;
	String hinhthuctt;
	String nguoimua;
	String innguoimua;

	String loaidonhang;  //0 đơn đặt hàng, 1 đơn hàng khuyến mại ứng hàng, 3 đơn hàng khuyến mại tích lũy, 4 đơn hàng trưng bày, 5 đơn hàng khác
	String loaiXHD;
	
	String msg;
	String trangthai;
	String khoNhanId;
	ResultSet khoNhanRs;
	String nppId;
	ResultSet nppRs;

	String ddhId;
	ResultSet ddhRs;
	
	String[] spId;
	String[] spMa;
	String[] spTen;
	String[] spDonvi;
	String[] spDongia;
	String[] spChietkhau;
	String[] spSoluong;
	String[] spLoai;
	String[] spSCheme;
	String[] spVat;
	String[] spTienthue;
	
	String[] dhCk_diengiai;
	String[] dhCk_giatri;
	String[] dhCk_loai;
	
	String bvat;
	String totalCHIETKHAU;
	String thueVAT;
	String avat;
	String mavuviec="";
	String noibo;
	
	dbutils db;
	
	public ErpHoadontaichinh()
	{
		this.id = "";
		this.ngayxuatHD = getDateTime();
		this.ngayghinhanCN = getDateTime();
		this.ghichu = "";
		this.kyhieuhoadon = "";
		this.hinhthuctt = "";
		this.sohoadon= "";
		this.khoNhanId = "";
		this.nppId = "";
		this.msg = "";
		this.trangthai = "0";
		this.ddhId = "";
		
		this.bvat = "0";
		this.totalCHIETKHAU = "0";
		this.thueVAT = "0";
		this.avat = "0";
		
		this.loaidonhang = "0";
		this.nguoimua = ""; 
		this.innguoimua = "";
		this.loaiXHD ="";
		this.ptchietkhau="0";
		this.noibo = "0";
		this.db = new dbutils();
	}
	
	public ErpHoadontaichinh(String id)
	{
		this.id = id;
		this.ngayxuatHD = getDateTime();
		this.ngayghinhanCN = getDateTime();
		this.ghichu = "";
		this.kyhieuhoadon = "";
		this.sohoadon = "";
		this.khoNhanId = "";
		this.hinhthuctt = "";
		this.nppId = "";
		this.msg = "";
		this.trangthai = "0";
		this.ddhId = "";
		
		this.bvat = "0";
		this.totalCHIETKHAU = "0";
		this.thueVAT = "0";
		this.avat = "0";

		this.loaidonhang = "0";
		this.nguoimua= "";
		this.innguoimua = "";
		
		this.loaiXHD ="";
		this.ptchietkhau="0";
		this.noibo = "0";
		
		this.db = new dbutils();
	}

	public String getInNguoimua() 
	{
		return this.innguoimua;
	}

	public void setInNguoimua(String innguoimua) 
	{
		this.innguoimua = innguoimua;
	}
	
	public String getMavuviec() {
		return mavuviec;
	}

	public void setMavuviec(String mavuviec) {
		this.mavuviec = mavuviec;
	}
	
	public String getPtchietkhau() {
		return ptchietkhau;
	}

	public void setPtchietkhau(String ptchietkhau) {
		this.ptchietkhau = ptchietkhau;
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

	public String getNgayxuatHD() 
	{
		return this.ngayxuatHD;
	}

	public void setNgayxuatHD(String ngayxuatHD) 
	{
		this.ngayxuatHD = ngayxuatHD;
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


	public void createRs() 
	{
		String query = "select PK_SEQ, MAFAST + '-' + TEN as TEN from NHAPHANPHOI where TRANGTHAI = '1'  " +
				" and pk_seq in ( select NPP_FK from ERP_DONDATHANG where pk_seq in ( " + this.ddhId + " ) )";
		this.nppRs = db.get(query);


		if(this.ddhId.length()>0)
		{
			query = "select pk_seq,ten from kenhbanhang where pk_Seq in (select kbh_fk from Erp_DonDatHangNPP where pk_Seq in ("+this.ddhId+"))";
			this.kbhRs = this.db.get(query);
		}

		if(this.id.length() <=0 )
		{
			// TỰ TẠO SỐ HÓA ĐƠN CỦA USER
			int kbDLN =0;
			String chuoiHD= "";
			long sohoadontu= 0;
			String sohoadonden= "";
			String mau = "1";
			
			try
			{
				//KIEM TRA USER ĐÃ KHAI BAO SO HOA DON TRONG DLN CHUA
				query=  " select count(pk_seq) as dem" +
						" from NHANVIEN" +
						" where pk_seq = '"+ this.userId+"' and  isnull(sohoadontu,'') != '' and isnull(sohoadonden,'')  != ''" +
						"       and isnull(kyhieu, '') != ''  ";

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
					this.msg = "Vui lòng khai báo Số hóa đơn trong menu Cập nhật nhân viên cho user này ";
				}
				else
				{
					// LAY KY HIEU HOA DON ,SOHDTU TRONG DLN
					query= "  select kyhieu as kyhieuhoadon, sohoadontu, sohoadonden " +
							" from NHANVIEN " +
							" where pk_seq= '"+ this.userId +"'";
					ResultSet rsLayDL =  db.get(query);
					if(rsLayDL != null)
					{
						while(rsLayDL.next())
						{
							this.kyhieuhoadon= rsLayDL.getString("kyhieuhoadon");
							sohoadontu = rsLayDL.getLong("sohoadontu");
							sohoadonden = rsLayDL.getString("sohoadonden");
						}
						rsLayDL.close();
					}

					// KIEM TRA SOHOADON DA DUOC DUNG CHUA
					// ETC
					query = " select count(pk_seq) as dem" +
							" from ERP_HOADON" +
							" where RTRIM(LTRIM(kyhieu)) = '"+ this.kyhieuhoadon +"' and cast((case when  sohoadon!='NA' then sohoadon else 0 end) as int) >=  "+ sohoadontu +" " +
							"       and trangthai != '3' and nguoisua= "+ this.userId +" and sohoadon != 'NA' and mauhoadon = 1  ";
					System.out.println("Câu kiểm tra SHD "+query);
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

					//OTC
					query = " select count(pk_seq) as dem" +
							" from HOADON" +
							" where RTRIM(LTRIM(kyhieu)) = '"+ this.kyhieuhoadon +"' and cast((case when  sohoadon!='NA' then sohoadon else 0 end) as numeric(18,0)) >= "+ sohoadontu +" and cast(sohoadon as numeric(18,0))<= " + Integer.parseInt(sohoadonden) + " " +
							"        and trangthai != '3'  and nguoisua= "+ this.userId +" and sohoadon != 'NA' and mauhoadon = 1 ";
					System.out.println("Câu kiểm tra SHD "+query);
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
						chuoiHD = ("000000"+ sohoadontu);
						chuoiHD = chuoiHD.substring(chuoiHD.length() - 7, chuoiHD.length());
					}
					else
					{
						// LAY SOIN MAX TRONG HOADON : 
						//OTC
						query = " select  MAX(cast((case when  sohoadon!='NA' then sohoadon else 0 end) as numeric)) as SOIN_MAX" +
								" from HOADON where RTRIM(LTRIM(KYHIEU)) ='"+ this.kyhieuhoadon +"' and cast((case when  sohoadon!='NA' then sohoadon else 0 end) as numeric(18,0)) >= "+ sohoadontu +" and cast(sohoadon as numeric(18,0))<= " + Integer.parseInt(sohoadonden) + " " +
								"       and trangthai != '3'  and nguoisua= "+ this.userId +" and sohoadon != 'NA' and mauhoadon = 1 ";
						System.out.println("Câu lấy shd max "+query);
						ResultSet laySOIN = db.get(query);
						long soinMAX_OTC= 0;
						if(laySOIN!= null)
						{
							while(laySOIN.next())
							{
								soinMAX_OTC = laySOIN.getLong("SOIN_MAX");
							}
							laySOIN.close();
						}

						//ETC
						query = " select  MAX(cast((case when  sohoadon!='NA' then sohoadon else 0 end) as numeric)) as SOIN_MAX" +
								" from ERP_HOADON " +
								" where RTRIM(LTRIM(KYHIEU)) ='"+ this.kyhieuhoadon +"' and cast((case when  sohoadon!='NA' then sohoadon else 0 end) as numeric(18,0)) >= "+ sohoadontu +" and cast(sohoadon as numeric(18,0))<= " + Integer.parseInt(sohoadonden) + " " +
								"       and trangthai != '3'  and nguoisua= "+ this.userId +" and sohoadon != 'NA' and mauhoadon = 1 ";
						System.out.println("Câu lấy shd max "+query);
						ResultSet laySOIN_ETC = db.get(query);
						long soinMAX_ETC= 0;
						if(laySOIN_ETC!= null)
						{
							while(laySOIN_ETC.next())
							{
								soinMAX_ETC = laySOIN_ETC.getLong("SOIN_MAX");
							}
							laySOIN_ETC.close();
						}

						if(soinMAX_OTC > soinMAX_ETC) 
						{
							chuoiHD = ("000000"+ (soinMAX_OTC >0 ? (soinMAX_OTC +1) :"1"));
						}else
						{
							chuoiHD = ("000000"+ (soinMAX_ETC >0 ? (soinMAX_ETC +1) :"1"));
						}

						chuoiHD = chuoiHD.substring(chuoiHD.length() - 7, chuoiHD.length());
					}
					this.sohoadon =  chuoiHD;

					if(Integer.parseInt(this.sohoadon) > Integer.parseInt(sohoadonden))
					{ 
						this.msg = "Số hóa đơn đã vượt quá Số hóa đơn đến trong dữ liệu nền. Vui lòng khai báo ký hiệu hóa đơn mới ! ";
					}
				}

				if(this.nppId.trim().length() > 0 )
				{	
					this.nguoimua = "";
							
					// LAY DONHANG	
					query = " select PK_SEQ , NgayDonHang  " +
							" from ERP_DONDATHANG " +
							" where  NPP_FK = '"+ this.nppId +"'   " +
							" and pk_seq not in (select a.DDH_FK " +
							"                    from  ERP_HOADON_DDH a inner join ERP_HOADON b on a.HOADON_FK=b.PK_SEQ" +
							"                    where b.TRANGTHAI in ( 1, 2, 4) )" ;
					System.out.println("Câu query "+query);		
					this.ddhRs = db.get(query);
				}
				
				String chuoi = this.ddhId;
				if(chuoi.length() > 0)
				{	
					query = "select b.PK_SEQ as SPID, b.MA, b.TEN, DV.donvi, a.dongia , "+
							"  isnull(scheme,' ') as scheme , isnull(a.thuevat,0) as vat ,   " +
							"  SUM( a.soluong)  as soluong, SUM( isnull(a.chietkhau, 0)) as chietkhau "+
							"from ERP_DONDATHANG_SANPHAM a inner Join SanPham b on a.SANPHAM_FK = b.PK_SEQ   "+  	 
							" INNER JOIN DONVIDOLUONG DV ON DV.PK_SEQ = a.DVDL_FK  " +
							" inner join  ERP_DONDATHANG c on a.dondathang_fk=c.pk_seq    "+
							"where a.dondathang_fk in ( "+ chuoi +" ) and a.dondathang_fk in (select pk_seq from ERP_DONDATHANG where NPP_FK="+ this.nppId +")  " +
							"group by b.PK_SEQ , b.MA, b.TEN, DV.donvi, a.dongia , isnull(scheme,' ') , isnull(a.thuevat,0) ";

					System.out.println("Lấy sản phẩm: "+query);
					ResultSet rsLaySP = db.get(query);

					String spID = "";
					String spMA = "";
					String spTEN = "";
					String spDONVI = "";
					String spSOLUONG = "";
					String spGIANHAP = "";
					String spCHIETKHAU = "";
					String spSCHEME = "";
					String spVAT = "";
					String spTIENTHUE = "";

					if(rsLaySP!= null)
					{				    	
						while(rsLaySP.next())
						{
							spID += rsLaySP.getString("SPID") + "__";
							spMA += rsLaySP.getString("MA") + "__";
							spTEN += rsLaySP.getString("TEN") + "__";
							spDONVI += rsLaySP.getString("DONVI") + "__";
							spSOLUONG += (rsLaySP.getDouble("SOLUONG")) + "__";
							spGIANHAP += (rsLaySP.getDouble("DONGIA")) + "__";
							spCHIETKHAU += (rsLaySP.getDouble("chietkhau")) + "__";
							spSCHEME += rsLaySP.getString("scheme") + "__";
							spVAT +=  (rsLaySP.getDouble("vat")) + "__";
							spTIENTHUE +=  Math.round( ( Math.round( rsLaySP.getDouble("SOLUONG") * rsLaySP.getDouble("DONGIA") ) - rsLaySP.getDouble("chietkhau") ) * rsLaySP.getDouble("vat") / 100 ) + "__";

						}
						rsLaySP.close();

						if(spMA.trim().length() > 0)
						{
							spID = spID.substring(0, spID.length() - 2);
							this.spId = spID.split("__");

							spMA = spMA.substring(0, spMA.length() - 2);
							this.spMa = spMA.split("__");

							spTEN = spTEN.substring(0, spTEN.length() - 2);
							this.spTen = spTEN.split("__");

							spDONVI = spDONVI.substring(0, spDONVI.length() - 2);
							this.spDonvi = spDONVI.split("__");

							spSOLUONG = spSOLUONG.substring(0, spSOLUONG.length() - 2);
							this.spSoluong = spSOLUONG.split("__");

							spGIANHAP = spGIANHAP.substring(0, spGIANHAP.length() - 2);
							this.spDongia = spGIANHAP.split("__");

							spCHIETKHAU = spCHIETKHAU.substring(0, spCHIETKHAU.length() - 2);
							this.spChietkhau = spCHIETKHAU.split("__");

							spSCHEME = spSCHEME.substring(0, spSCHEME.length() - 2);
							this.spSCheme = spSCHEME.split("__");

							spVAT = spVAT.substring(0, spVAT.length() - 2);
							this.spVat = spVAT.split("__");

							spTIENTHUE = spTIENTHUE.substring(0, spTIENTHUE.length() - 2);
							this.spTienthue = spTIENTHUE.split("__");

						}
					}
				}
			}
			catch(Exception ex)
			{
				ex.printStackTrace();
			}
		}
		else // ID > 0
		{
			query = "select b.PK_SEQ as SPID, b.MA, isnull(a.tensp,b.TEN) as ten, a.donvitinh, a.soluong, a.dongia, isnull(a.chietkhau, 0) as chietkhau, a.scheme, a.vat," +
					"	ROUND ( isnull( TIENVAT, ( ( round( soluong * dongia, 0 ) - chietkhau ) * isnull(vat, 0) / 100 ) ), 0) as tienVAT  " +
					"from ERP_HOADON_SP a inner Join SanPham b on a.SANPHAM_FK = b.PK_SEQ    " +
					"where a.hoadon_fk = "+ this.id +"  " ;		

			System.out.println("INIT sản phẩm: "+query);
			ResultSet rsLaySP = db.get(query);
			try 
			{
				String spID = "";
				String spMA = "";
				String spTEN = "";
				String spDONVI = "";
				String spSOLUONG = "";
				String spGIANHAP = "";
				String spCHIETKHAU = "";
				String spSCHEME = "";
				String spVAT = "";
				String spTIENTHUE = "";

				if(rsLaySP!= null)
				{				    	
					while(rsLaySP.next())
					{
						spID += rsLaySP.getString("SPID") + "__";
						spMA += rsLaySP.getString("MA") + "__";
						spTEN += rsLaySP.getString("TEN") + "__";
						spDONVI += rsLaySP.getString("DONVITINH") + "__";
						spSOLUONG += (rsLaySP.getDouble("SOLUONG")) + "__";
						spGIANHAP += (rsLaySP.getDouble("DONGIA")) + "__";
						spCHIETKHAU += (rsLaySP.getDouble("chietkhau")) + "__";
						spSCHEME += rsLaySP.getString("scheme") + "__";
						spVAT +=  (rsLaySP.getDouble("vat")) + "__";
						spTIENTHUE +=  (rsLaySP.getDouble("tienVAT")) + "__";

					}
					rsLaySP.close();

					if(spMA.trim().length() > 0)
					{
						spID = spID.substring(0, spID.length() - 2);
						this.spId = spID.split("__");

						spMA = spMA.substring(0, spMA.length() - 2);
						this.spMa = spMA.split("__");

						spTEN = spTEN.substring(0, spTEN.length() - 2);
						this.spTen = spTEN.split("__");

						spDONVI = spDONVI.substring(0, spDONVI.length() - 2);
						this.spDonvi = spDONVI.split("__");

						spSOLUONG = spSOLUONG.substring(0, spSOLUONG.length() - 2);
						this.spSoluong = spSOLUONG.split("__");

						spGIANHAP = spGIANHAP.substring(0, spGIANHAP.length() - 2);
						this.spDongia = spGIANHAP.split("__");

						spCHIETKHAU = spCHIETKHAU.substring(0, spCHIETKHAU.length() - 2);
						this.spChietkhau = spCHIETKHAU.split("__");

						spSCHEME = spSCHEME.substring(0, spSCHEME.length() - 2);
						this.spSCheme = spSCHEME.split("__");

						spVAT = spVAT.substring(0, spVAT.length() - 2);
						this.spVat = spVAT.split("__");

						spTIENTHUE = spTIENTHUE.substring(0, spTIENTHUE.length() - 2);
						this.spTienthue = spTIENTHUE.split("__");

					}
				}
			} 
			catch (SQLException e) 
			{
				e.printStackTrace();
			}
		}
	}

	public void init() 
	{
		NumberFormat formatter = new DecimalFormat("##,###,###");
		Utility util = new Utility();

		String query =  "  select isnull(c.ChietKhau,0) as ptchietkhau,a.kho_fk,dondathang_fk, a.kbh_fk as kbhId, a.npp_fk, ngayxuatHD, ISNULL(a.ghichu, '') as ghichu,  " + 
						"  		a.npp_fk, a.trangthai, kyhieu, sohoadon, hinhthuctt ,  isnull(a.chietkhau,0) as chietkhau, '' as nguoimua, 1 as innguoimua,   " + 
						"  		isnull(tongtienbvat,0) as tongtienbvat,  isnull(tongtienavat,0) as tongtienavat,  isnull(a.vat, 0) as vat, isnull(a.chietkhau, 0) as chietkhau, loaixuatHD, '' as mavv, isnull(a.noibo, 0) as noibo  " + 
						"  from ERP_HOADON a  " + 
						"     inner join ERP_HOADON_DDH b on b.HOADON_FK = a.PK_SEQ " + 
						"     inner join ERP_DONDATHANG c on c.PK_SEQ = b.DDH_FK " +
						"   where a.pk_seq = '" + this.id + "'";
		System.out.println("____INIT HOADON: " + query);
		ResultSet rs = db.get(query);
		if(rs != null)
		{
			try 
			{
				if(rs.next())
				{
					this.ngayxuatHD = rs.getString("ngayxuatHD");
					this.ghichu = rs.getString("ghichu");
					this.hinhthuctt = rs.getString("hinhthuctt");
					this.kyhieuhoadon = rs.getString("kyhieu");
					this.sohoadon = rs.getString("sohoadon");
					this.nppId = rs.getString("npp_fk");
					this.loaiXHD = rs.getString("loaixuatHD");		
					this.nguoimua = rs.getString("nguoimua");	
					this.innguoimua = rs.getString("innguoimua");
					this.trangthai = rs.getString("trangthai");
					this.kbhId=rs.getString("kbhId") == null ? "" : rs.getString("kbhId");
					this.khoNhanId=rs.getString("kho_fk");
					this.mavuviec=rs.getString("mavv");
					this.ptchietkhau=rs.getString("ptchietkhau");
					this.bvat = formatter.format(rs.getDouble("tongtienbvat"));
					this.totalCHIETKHAU = formatter.format(rs.getDouble("chietkhau"));
					this.thueVAT = formatter.format(rs.getDouble("vat"));
					this.avat = formatter.format(rs.getDouble("tongtienavat"));
					this.noibo = rs.getString("noibo");
					
					this.ddhId="";
					//INIT DDH
					query = "SELECT HOADON_FK, DDH_FK FROM ERP_HOADON_DDH WHERE HOADON_FK = " + this.id;
					System.out.println("---LAY DDH: " + query );
					rs = this.db.get(query);
					if(rs!=null)
					{

						String _ddhId = "";
						while(rs.next())
						{
							_ddhId = _ddhId + rs.getString("DDH_FK") + ",";
						}

						if(_ddhId.trim().length() > 0)
						{
							_ddhId = _ddhId.substring(0, _ddhId.length() - 1);
							this.ddhId = _ddhId;
						}
					}

					if(this.trangthai.equals("3") || this.trangthai.equals("5") )
					{
						query = " select B.PK_SEQ ,B.NgayDonHang   " +
								" from ERP_HOADON_DDH A INNER JOIN ERP_DONDATHANG B ON A.DDH_FK = B.PK_SEQ " +
								" where A.HOADON_FK = '"+ this.id +"'  and B.kho_fk in " + util.quyen_kho(this.userId);
					}
					else
					{
						if(this.loaiXHD.equals("1") ) // KHACHHANG
						{			
							query = " select PK_SEQ , NgayDonHang  " +
									" from ERP_DONDATHANG " +
									" where trangthai = 4 and NPP_FK="+ this.nppId + 
									" and pk_seq not in  (select a.DDH_FK from  ERP_HOADON_DDH a inner join ERP_HOADON b on a.HOADON_FK=b.PK_SEQ where b.TRANGTHAI in (2,4) and b.pk_seq != " + this.id +")   " ;

						}
						else // DOITAC
						{			
							query = " select PK_SEQ , NgayDonHang  " +
									" from ERP_DONDATHANG " +
									" where trangthai = 4 and  NPP_FK="+ this.nppId +
									" and pk_seq not in(select a.DDH_FK from  ERP_HOADON_DDH a inner join ERP_HOADON b on a.HOADON_FK=b.PK_SEQ where b.TRANGTHAI =2 and b.pk_seq != " + this.id +")  " ;

						}
					}
					System.out.println("LAY DANH SACH DDH: " + query);		
					this.ddhRs = db.get(query);
				}
				rs.close();
			} 
			catch (Exception e) 
			{
				e.printStackTrace();
				System.out.println("---LOI INIT: " + e.getMessage());
			}
		}
		try
		{
			//INIT SOLO
			query = "select ngaynhapkho ,sanpham_fk as sanpham_fk, solo, ngayhethan, sum(soluong) as soluong  " +
					"from ERP_DONDATHANG_SANPHAM_CHITIET  ct "
					+ " where dondathang_fk='"+this.ddhId+"' " +
					"group by sanpham_fk, solo, ngayhethan,ngaynhapkho  ";
			System.out.println("---LO DA XUAT: " + query);

			ResultSet rsSOLO = db.get(query);
			this.sanpham_soluong = new Hashtable<String, String>();
			if(rsSOLO != null)
			{
				while(rsSOLO.next())
				{
					String key = rsSOLO.getString("sanpham_fk") + "__" + rsSOLO.getString("solo") + "__" + rsSOLO.getString("ngayhethan")+ "__" + rsSOLO.getString("ngaynhapkho");
					this.sanpham_soluong.put(key, rsSOLO.getString("soluong"));
				}
				rsSOLO.close();
			}
		}
		catch (Exception e) 
		{	
			e.printStackTrace();
		}

		this.createRs();
	}

	public void DBclose() {
		
		try{
			
			if(khoNhanRs!=null){
				khoNhanRs.close();
			}
			
			this.db.shutDown();
			
		}catch(Exception er){
			er.printStackTrace();
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

	public String getHinhthucTT() {
		
		return this.hinhthuctt;
	}

	public void setHinhthucTT(String hinhthucTT) {
		
		this.hinhthuctt = hinhthucTT;
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

	
	public String getDondathangId() {
		
		return this.ddhId;
	}

	
	public void setDondathangId(String kbhId) {
		
		this.ddhId = kbhId;
	}

	
	public ResultSet getDondathangRs() {
		
		return this.ddhRs;
	}

	
	public void setDondathangRs(ResultSet ddhRs) {
		
		this.ddhRs = ddhRs;
	}

	public boolean create() 
	{
		if(this.ngayxuatHD.trim().length() < 10)
		{
			this.msg = "Vui lòng nhập ngày xuất hóa đơn";
			return false;
		}

		if(this.ngayghinhanCN.trim().length() < 10)
		{
			this.msg = "Vui lòng nhập ngày ghi nợ";
			return false;
		}
		
		if(this.ddhId.trim().length() <= 0)
		{
			this.msg = "Vui lòng chọn đơn đặt hàng";
			return false;
		}
				
		if(spMa == null)
		{
			this.msg = "Vui lòng kiểm tra lại danh sách sản phẩm  xuất hoá đơn";
			return false;
		}
		else
		{
			boolean coSP = false;
			for(int i = 0; i < spId.length; i++)
			{
				if( spSoluong[i].trim().length() > 0 )
				{
					if(Double.parseDouble(spSoluong[i].trim().replace(",", "")) > 0)
						coSP = true;
				}
			}
			
			if(!coSP)
			{
				this.msg = "Vui lòng kiểm tra lại danh sách sản phẩm  xuất hoá đơn";
				return false;
			}	
		}
		
		try
		{
			db.getConnection().setAutoCommit(false);
			
			// LAY KY HIEU HOA DON ,SOHDTU TRONG DLN
			String chuoi = "";
			long sohoadontu = 0;
			String sohoadonden = "";
			int kbDLN = 0;
			String mau = "1";

			String query =  " select  sohoadontu, sohoadonden " +
							" from NHANVIEN" +
							" where pk_seq= '"+ this.userId +"' and  isnull(kyhieu,'')= '"+ this.kyhieuhoadon +"' ";
			
			ResultSet rsLayDL =  db.get(query);
			if(rsLayDL != null)
			{
				while(rsLayDL.next())
				{
					sohoadontu = rsLayDL.getLong("sohoadontu");
					sohoadonden = rsLayDL.getString("sohoadonden");
				}
				rsLayDL.close();
			}
			if (sohoadontu == 0 || sohoadonden.trim().length() <= 0 )
			{
				this.msg = "Ký hiệu "+ this.kyhieuhoadon +" khác với ký hiệu khai báo trong dữ liệu nền/ Chưa khai báo số hóa đơn trong dữ liệu nền (Số hóa đơn)";
				db.getConnection().rollback();
				return false;
			}
			
			// Check So hoa don sua da co dung chua
		   // OTC
			query= " select  count(pk_seq) as kiemtra " +
			       " from HOADON " +
			       " where nguoisua= '"+ this.userId +"' and RTRIM(LTRIM(sohoadon)) = '"+ this.sohoadon.trim() +"' and RTRIM(LTRIM(kyhieu)) = '"+ this.kyhieuhoadon +"' " +
			       "       and trangthai != '3' and pk_seq != "+ this.id +"  and npp_fk = " + this.nppId + "  and mauhoadon = 1 ";
			System.out.println("Cau kiem tra so hoa don "+query);
			ResultSet KtraSHD =  db.get(query);
			int ktra= 0;
			
			if(KtraSHD != null)
			{
				while(KtraSHD.next())
				{
					ktra = KtraSHD.getInt("kiemtra");
				}
				KtraSHD.close();
			}
		
		  // ETC
			query= " select  count(pk_seq) as kiemtra " +
			       " from ERP_HOADON " +
			       " where nguoisua= '"+ this.userId +"' and RTRIM(LTRIM(sohoadon)) = '"+ this.sohoadon.trim() +"' and RTRIM(LTRIM(kyhieu))='"+ this.kyhieuhoadon +"' " +
			       "       and trangthai != '3' and pk_seq != "+ this.id +"  and npp_fk = " + this.nppId + " and mauhoadon = 1  ";
			System.out.println("Cau kiem tra so hoa don "+query);
			ResultSet KtraSHD_ETC =  db.get(query);
			int ktra_ETC= 0;
			
			if(KtraSHD_ETC != null)
			{
				while(KtraSHD_ETC.next())
				{
					ktra_ETC = KtraSHD_ETC.getInt("kiemtra");
				}
				KtraSHD_ETC.close();
			}
			
			if (ktra > 0 || ktra_ETC > 0 )
			{
				this.msg = "Số hóa đơn này đã được sử dụng. Vui lòng đánh số hóa đơn khác. ";
				db.getConnection().rollback();
				return false;
			}
			else if(this.sohoadon.trim().length() != 7)
			{
				this.msg = "Số hóa đơn phải đủ 7 ký tự .Vui lòng kiểm tra lại! ";
				db.getConnection().rollback();
				return false;
			}
					
			String tbThongTin = " NHAPHANPHOI ";
			String thongtinId = this.nppId;
			String ddkd_fk ="(select ddkd_fk From Erp_DonDatHang where pk_Seq='"+this.ddhId+"') ";
			String khoId ="(select KHO_FK From Erp_DonDatHang where pk_Seq='"+ddhId+"') ";
			String kbhId ="(select kbh_fk From Erp_DonDatHang where pk_Seq='"+ddhId+"') ";
		 
			
			 query = 	" insert ERP_HOADON(KHO_FK,DDKD_FK,nguoimua, innguoimua,ngayxuatHD, trangthai, ngaytao, nguoitao, ngaysua, nguoisua, kyhieu, sohoadon, hinhthuctt ," +
				       " 	chietkhau, tongtienbvat, tongtienavat, vat, ghichu, loaixuathd, npp_fk, mauhoadon,TENKHACHHANG,DIACHI,MASOTHUE,mavv ) " +
					   " select "+khoId+","+ddkd_fk+",  N'"+ this.nguoimua +"' , "+ this.innguoimua +", '" + this.ngayxuatHD + "', '1','" + getDateTime() + "', '" + this.userId + "', '" + getDateTime() + "', '" + this.userId + "', RTRIM(LTRIM('" + this.kyhieuhoadon + "')), " +
					   "       RTRIM(LTRIM('" + this.sohoadon + "')), N'"+ this.hinhthuctt +"' , '"+ this.totalCHIETKHAU.replaceAll(",", "")  +"', '"+ this.bvat.replaceAll(",", "") +"', '" + this.avat.replaceAll(",", "")  +"'," +
					   "       '"+ this.thueVAT.replaceAll(",", "") +"', N'"+ this.ghichu +"', '"+ this.loaiXHD +"', "+ this.nppId +", '"+ mau +"'" +
					   "		 , (select ten from "+tbThongTin+" where pk_Seq =" + thongtinId + " ) as nppMua " +
					   " 		, (select ISNULL(DIACHI,'') from "+tbThongTin+" where pk_Seq =" + thongtinId + " ) as diachinpp " +
					   " 		, (select ISNULL(MASOTHUE,'') from "+tbThongTin+" where pk_Seq =" + thongtinId + " ) as mst, '"+this.mavuviec+"'" ;
			 			
			System.out.println("1.Insert ERP_HOADON: " + query);
			if(!db.update(query))
			{
				this.msg = "Không thể tạo mới ERP_HOADON " + query;
				db.getConnection().rollback();
				return false;
			}
			
			String hdId = "";
			query = "select SCOPE_IDENTITY() as hdId";
			ResultSet rs1 = db.get(query);
			rs1.next();
			hdId = rs1.getString("hdId");
			rs1.close();
			
			for(int i = 0; i < spId.length; i++)
			{
				if(spId[i].trim().length() > 0 && Double.parseDouble(spSoluong[i].trim().replaceAll(",", "")) > 0 )
				{
					String thanhtien = spSoluong[i].replaceAll(",", "")+ " * "+ spDongia[i].replaceAll(",", "");
					
					query = "insert ERP_HOADON_SP( hoadon_fk, sanpham_fk, donvitinh, soluong, dongia, thanhtien, chietkhau, scheme , vat) " +
							" values( "+ hdId +", '" + spId[i] + "', N'"+ spDonvi[i] +"', '" + spSoluong[i].replaceAll(",", "") + "', '" + spDongia[i].replaceAll(",", "") + "'," +
									" "+ thanhtien +", '"+ spChietkhau[i].replaceAll(",", "") +"', N'"+ spSCheme[i].replaceAll(",", "") +"', '"+ spVat[i].replaceAll(",", "") +"' ) ";
					
					System.out.println("1.1.Insert ERP_HOADON_SP: " + query);
					if(!db.update(query))
					{
						this.msg = "Khong the tao moi ERP_HOADON_SP: " + query;
						db.getConnection().rollback();
						return false;
					}
			
				}
			}
			
				query=
				"		update C set SoLuong_Chuan=  "+
				"				case     when c.donvitinh = e.donvi then c.soluong  "+     
				"								  else c.soluong * ( select SOLUONG1 / SOLUONG2 from QUYCACH where sanpham_fk = c.sanpham_fk and DVDL2_FK = ( select pk_seq from DONVIDOLUONG where donvi = c.donvitinh ) and DVDL1_FK = d.DVDL_FK )   end  ,  "+ 
				"		DonGia_Chuan=	case     when c.donvitinh = e.donvi then c.dongia      "+
				"							  else c.dongia * ( select SOLUONG2 / SOLUONG1 from QUYCACH where sanpham_fk = c.sanpham_fk and DVDL2_FK = ( select pk_seq from		DONVIDOLUONG where donvi = c.donvitinh ) and DVDL1_FK = d.DVDL_FK )   end  , "+
				"			DonVi_Chuan=e.DONVI  "+
				"		from ERP_HOADON a         "+
				"			left join KHACHHANG KH on a.KHACHHANG_FK = KH.PK_SEQ      "+
				"			inner join ERP_HOADON_SP c on a.pk_seq = c.hoadon_fk  "+ 
				"			inner join SANPHAM d on c.sanpham_fk = d.pk_seq    "+
				"			inner join DONVIDOLUONG e on d.DVDL_FK = e.pk_seq  "+      
				"		where a.pk_Seq='"+hdId+"' ";
				System.out.println("1.1.UPDATE ERP_HOADON: " + query);
				if(!db.update(query))
				{
					this.msg = "Không thể tạo mới ERP_HOADON_SP " + query;
					db.getConnection().rollback();
					return false;
				}
			
			
			query = "Insert ERP_HOADON_DDH(hoadon_fk, ddh_fk) select " + hdId + ", pk_seq from ERP_DONDATHANG where pk_seq in ( " + this.ddhId + " )    ";
			if(!db.update(query))
			{
				this.msg = "Không thể tạo mới ERP_HOADON_DDH " + query;
				db.getConnection().rollback();
				return false;
			}
					
			query = "Update ERP_YCXUATKHO set NGAYYEUCAU = '" + this.ngayxuatHD + "' " +
					"where PK_SEQ in ( select ycxk_fk from ERP_YCXUATKHO_DDH where ddh_fk in ( " + this.ddhId + " ) )";
			if(!db.update(query))
			{
				this.msg = "Không thể tạo mới ERP_YCXUATKHO " + query;
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
		if(this.ngayxuatHD.trim().length() < 10)
		{
			this.msg = "Vui lòng nhập ngày xuất hóa đơn";
			return false;
		}

		if(this.ngayghinhanCN.trim().length() < 10)
		{
			this.msg = "Vui lòng nhập ngày ghi nợ";
			return false;
		}
		
		if(this.ddhId.trim().length() <= 0)
		{
			this.msg = "Vui lòng chọn đơn đặt hàng";
			return false;
		}
				
		if(spMa == null)
		{
			this.msg = "Vui lòng kiểm tra lại danh sách sản phẩm  xuất hoá đơn";
			return false;
		}
		else
		{
			boolean coSP = false;
			for(int i = 0; i < spId.length; i++)
			{
				if( spSoluong[i].trim().length() > 0 )
				{
					if(Double.parseDouble(spSoluong[i].trim().replace(",", "")) > 0)
						coSP = true;
				}
			}
			
			if(!coSP)
			{
				this.msg = "Vui lòng kiểm tra lại danh sách sản phẩm  xuất hoá đơn";
				return false;
			}	
		}
		
		try
		{
			db.getConnection().setAutoCommit(false);
			
			String query = " select loaiNPP from NHAPHANPHOI where pk_seq = '" + this.nppId + "' ";
			ResultSet rs = db.get(query);
			String loaiNPP = "";
			if(rs != null)
			{
				if(rs.next())
				{
					loaiNPP = rs.getString("loaiNPP");
				}
				rs.close();
			}
			
			// LAY KY HIEU HOA DON ,SOHDTU TRONG DLN
			String chuoi="";
			long sohoadontu=0;
			String sohoadonden="";
			int kbDLN=0;
			String mau = "1";
			
			 query =" select isnull(SOHOADONTU,'') as SOHOADONTU,isnull(SOHOADONDEN,'') as SOHOADONDEN,isnull(SOHOADONTU2,'') as SOHOADONTU2,isnull(SOHOADONDEN2,'') as SOHOADONDEN2 from NHANVIEN where pk_seq="+userId;
			System.out.println("AAAAA:"+ query);
			ResultSet mauHDrs = db.get(query);
			
			String SOHOADONTU1="";
			String SOHOADONDEN1="";
			String SOHOADONTU2="";
			String SOHOADONDEN2="";
			if(mauHDrs!=null)
			{
				while(mauHDrs.next())
				{
					SOHOADONTU1 = mauHDrs.getString("SOHOADONTU");
					SOHOADONDEN1 = mauHDrs.getString("SOHOADONDEN");
					SOHOADONTU2 = mauHDrs.getString("SOHOADONTU2");
					SOHOADONDEN2 = mauHDrs.getString("SOHOADONDEN2");
					
				}
				mauHDrs.close();
			}
			
			if(!loaiNPP.equals("4"))
			{
				String query_kyhieu = " KYHIEU ";				
				String query_sohdTU = " SOHOADONTU ";	
				String query_sohdDEN = " SOHOADONDEN ";	
				String query_mauhd = "1";
				String query_ngayhd = " NGAYHOADON  ";
				
				if(nppId.equals("100002") ||(nppId.equals("106210") && mau.equals("2"))  )
				{
					
						query_kyhieu = " KYHIEU2 ";				
						query_sohdTU = " SOHOADONTU2 ";	
						query_sohdDEN = " SOHOADONDEN2 ";				
						query_mauhd = "2";
						query_ngayhd = " NGAYHOADON2 ";
					
				}
				
				if(((SOHOADONDEN1.trim().length()>0 && SOHOADONTU1.trim().length()>0) && (SOHOADONDEN2.trim().length()==0 && SOHOADONTU2.trim().length()==0)) || ((SOHOADONDEN1.trim().length()==0 && SOHOADONTU1.trim().length()==0) && (SOHOADONDEN2.trim().length()>0 && SOHOADONTU2.trim().length()>0)) )
				{
					if(SOHOADONDEN1.trim().length()>0 && SOHOADONTU1.trim().length()>0)
					{
						 query_kyhieu = " KYHIEU ";				
						 query_sohdTU = " SOHOADONTU ";	
						 query_sohdDEN = " SOHOADONDEN ";	
						 query_mauhd = "1";
						 query_ngayhd = " NGAYHOADON  ";
					}
					if(SOHOADONDEN2.trim().length()>0 && SOHOADONTU2.trim().length()>0)
					{
						query_kyhieu = " KYHIEU2 ";				
						query_sohdTU = " SOHOADONTU2 ";	
						query_sohdDEN = " SOHOADONDEN2 ";				
						query_mauhd = "2";
						query_ngayhd = " NGAYHOADON2 ";
					}
				}
				
				mau=query_mauhd;
				 query= " select  "+query_sohdTU+" as sohoadontu, "+query_sohdDEN+" as sohoadonden " +
						" from NHANVIEN" +
						" where pk_seq= '"+ this.userId +"' and  isnull("+query_kyhieu+",'')= '"+ this.kyhieuhoadon +"' ";
				
				ResultSet rsLayDL =  db.get(query);
				if(rsLayDL != null)
				{
					while(rsLayDL.next())
					{
						sohoadontu = rsLayDL.getLong("sohoadontu");
						sohoadonden = rsLayDL.getString("sohoadonden");
					}
					rsLayDL.close();
				}
				if (sohoadontu == 0 || sohoadonden.trim().length() <= 0 )
				{
					this.msg = "Ký hiệu "+ this.kyhieuhoadon +" khác với ký hiệu khai báo trong dữ liệu nền/ Chưa khai báo số hóa đơn trong dữ liệu nền (Số hóa đơn)";
					db.getConnection().rollback();
					return false;
				}
				
				// Check So hoa don sua da co dung chua
				   // OTC
					query= " select  count(pk_seq) as kiemtra " +
					       " from HOADON " +
					       " where nguoisua= '"+ this.userId +"' and RTRIM(LTRIM(sohoadon)) = '"+ this.sohoadon.trim() +"' and RTRIM(LTRIM(kyhieu)) ='"+ this.kyhieuhoadon +"' " +
					       "       and trangthai != '3' and pk_seq != "+ this.id +"  and npp_fk = "+this.nppId+"  and mauhoadon = "+mau+" ";
					System.out.println("Cau kiem tra so hoa don "+query);
					ResultSet KtraSHD =  db.get(query);
					int ktra= 0;
					
					if(KtraSHD != null)
					{
						while(KtraSHD.next())
						{
							ktra = KtraSHD.getInt("kiemtra");
						}
						KtraSHD.close();
					}
				
				  // ETC
					query= " select  count(pk_seq) as kiemtra " +
					       " from ERP_HOADON " +
					       " where nguoisua= '"+ this.userId +"' and RTRIM(LTRIM(sohoadon)) = '"+ this.sohoadon.trim() +"' and RTRIM(LTRIM(kyhieu))='"+ this.kyhieuhoadon +"' " +
					       "       and trangthai != '3' and pk_seq != "+ this.id +"  and npp_fk = "+this.nppId+" and mauhoadon = "+mau+"  ";
					System.out.println("Cau kiem tra so hoa don "+query);
					ResultSet KtraSHD_ETC =  db.get(query);
					int ktra_ETC= 0;
					
					if(KtraSHD_ETC != null)
					{
						while(KtraSHD_ETC.next())
						{
							ktra_ETC = KtraSHD_ETC.getInt("kiemtra");
						}
						KtraSHD_ETC.close();
					}
				
				
				if (ktra > 0 || ktra_ETC > 0 )
				{
					this.msg = "Số hóa đơn này đã được sử dụng. Vui lòng đánh số hóa đơn khác. ";
					db.getConnection().rollback();
					return false;
				}
				else if(this.sohoadon.trim().length() != 7)
				{
					this.msg = "Số hóa đơn phải đủ 7 ký tự .Vui lòng kiểm tra lại! ";
					db.getConnection().rollback();
					return false;
				}
			}
			
			String tbThongTin = " NHAPHANPHOI ";
			String thongtinId = this.nppId;

			String ddkd_fk ="(select ddkd_fk From Erp_DonDatHang where pk_Seq='"+this.ddhId+"') ";
			String khoId ="(select KHO_FK From Erp_DonDatHang where pk_Seq='"+ddhId+"') ";
			String kbhId ="(select kbh_fk From Erp_DonDatHang where pk_Seq='"+ddhId+"') ";
		 
			
			 query = 	" insert ERP_HOADON(KHO_FK,DDKD_FK,nguoimua, innguoimua,ngayxuatHD, trangthai, ngaytao, nguoitao, ngaysua, nguoisua, kyhieu, sohoadon, hinhthuctt ," +
				       " 	chietkhau, tongtienbvat, tongtienavat, vat, ghichu, loaixuathd, npp_fk, mauhoadon,TENKHACHHANG,DIACHI,MASOTHUE,mavv ) " +
					   " select "+khoId+","+ddkd_fk+",  N'"+ this.nguoimua +"' , "+ this.innguoimua +", '" + this.ngayxuatHD + "', '1','" + getDateTime() + "', '" + this.userId + "', '" + getDateTime() + "', '" + this.userId + "', RTRIM(LTRIM('" + this.kyhieuhoadon + "')), " +
					   "       RTRIM(LTRIM('" + this.sohoadon + "')), N'"+ this.hinhthuctt +"' , '"+ this.totalCHIETKHAU.replaceAll(",", "")  +"', '"+ this.bvat.replaceAll(",", "") +"', '" + this.avat.replaceAll(",", "")  +"'," +
					   "       '"+ this.thueVAT.replaceAll(",", "") +"', N'"+ this.ghichu +"', '"+ this.loaiXHD +"', "+ this.nppId +", '"+ mau +"'" +
					   "		 , (select ten from "+tbThongTin+" where pk_Seq =" + thongtinId + " ) as nppMua " +
					   " 		, (select ISNULL(DIACHI,'') from "+tbThongTin+" where pk_Seq =" + thongtinId + " ) as diachinpp " +
					   " 		, (select ISNULL(MASOTHUE,'') from "+tbThongTin+" where pk_Seq =" + thongtinId + " ) as mst, '"+this.mavuviec+"'" ;
			 			
			System.out.println("1.Insert ERP_HOADON: " + query);
			if(!db.update(query))
			{
				this.msg = "Không thể tạo mới ERP_HOADON " + query;
				db.getConnection().rollback();
				return false;
			}
			
			String hdId = "";
			query = "select SCOPE_IDENTITY() as hdId";
			ResultSet rs1 = db.get(query);
			rs1.next();
			hdId = rs1.getString("hdId");
			rs1.close();
			
			for(int i = 0; i < spId.length; i++)
			{
				if(spId[i].trim().length() > 0 && Double.parseDouble(spSoluong[i].trim().replaceAll(",", "")) > 0 )
				{
					String thanhtien = spSoluong[i].replaceAll(",", "")+ " * "+ spDongia[i].replaceAll(",", "");
					
					query = "insert ERP_HOADON_SP( hoadon_fk, sanpham_fk, donvitinh, soluong, dongia, thanhtien, chietkhau, scheme , vat) " +
							" values( "+ hdId +", '" + spId[i] + "', N'"+ spDonvi[i] +"', '" + spSoluong[i].replaceAll(",", "") + "', '" + spDongia[i].replaceAll(",", "") + "'," +
									" "+ thanhtien +", '"+ spChietkhau[i].replaceAll(",", "") +"', N'"+ spSCheme[i].replaceAll(",", "") +"', '"+ spVat[i].replaceAll(",", "") +"' ) ";
					
					System.out.println("1.1.Insert ERP_HOADON_SP: " + query);
					if(!db.update(query))
					{
						this.msg = "Khong the tao moi ERP_HOADON_SP: " + query;
						db.getConnection().rollback();
						return false;
					}
			
				}
			}
			
				query=
				"		update C set SoLuong_Chuan=  "+
				"				case     when c.donvitinh = e.donvi then c.soluong  "+     
				"								  else c.soluong * ( select SOLUONG1 / SOLUONG2 from QUYCACH where sanpham_fk = c.sanpham_fk and DVDL2_FK = ( select pk_seq from DONVIDOLUONG where donvi = c.donvitinh ) and DVDL1_FK = d.DVDL_FK )   end  ,  "+ 
				"		DonGia_Chuan=	case     when c.donvitinh = e.donvi then c.dongia      "+
				"							  else c.dongia * ( select SOLUONG2 / SOLUONG1 from QUYCACH where sanpham_fk = c.sanpham_fk and DVDL2_FK = ( select pk_seq from		DONVIDOLUONG where donvi = c.donvitinh ) and DVDL1_FK = d.DVDL_FK )   end  , "+
				"			DonVi_Chuan=e.DONVI  "+
				"		from ERP_HOADON a         "+
				"			left join KHACHHANG KH on a.KHACHHANG_FK = KH.PK_SEQ      "+
				"			inner join ERP_HOADON_SP c on a.pk_seq = c.hoadon_fk  "+ 
				"			inner join SANPHAM d on c.sanpham_fk = d.pk_seq    "+
				"			inner join DONVIDOLUONG e on d.DVDL_FK = e.pk_seq  "+      
				"		where a.pk_Seq='"+hdId+"' ";
				System.out.println("1.1.UPDATE ERP_HOADON: " + query);
				if(!db.update(query))
				{
					this.msg = "Không thể tạo mới ERP_HOADON_SP " + query;
					db.getConnection().rollback();
					return false;
				}
			
			
			query = "Insert ERP_HOADON_DDH(hoadon_fk, ddh_fk) select " + hdId + ", pk_seq from ERP_DONDATHANG where pk_seq in ( " + this.ddhId + " )    ";
			if(!db.update(query))
			{
				this.msg = "Không thể tạo mới ERP_HOADON_DDH " + query;
				db.getConnection().rollback();
				return false;
			}
					
			query = "Update ERP_YCXUATKHO set NGAYYEUCAU = '" + this.ngayxuatHD + "' " +
					"where PK_SEQ in ( select ycxk_fk from ERP_YCXUATKHO_DDH where ddh_fk in ( " + this.ddhId + " ) )";
			if(!db.update(query))
			{
				this.msg = "Không thể tạo mới ERP_YCXUATKHO " + query;
				db.getConnection().rollback();
				return false;
			}
			
			this.id=hdId;
			
			Utility util = new Utility();
			msg= util.Check_Huy_NghiepVu_KhoaSo("ERP_HOADON", id, "NgayXuatHD", db);
			if(msg.length()>0)
				return false;
			
			
			 query = "update ERP_HOADON set trangthai = '2',NguoiSua='"+this.userId+"',NgaySua='"+getDateTime()+"'  where pk_seq = '" + this.id + "' and TrangThai!=2 ";
			if(db.updateReturnInt(query)!=1)
			{
				msg = "Hóa đơn đã chốt rồi hoặc có lỗi phát sinh trong quá trình cập nhật "+query;
				db.getConnection().rollback();
				return false;
			}
			query="delete from ERP_HOADON_SP_CHITIET where hoadon_fk='"+this.id+"'";
			if(!db.update(query))
			{
				msg = "Hóa đơn đã chốt rồi hoặc có lỗi phát sinh trong quá trình cập nhật "+query;
				db.getConnection().rollback();
				return false;
			}
			
			query = 
					" insert ERP_HOADON_SP_CHITIET(hoadon_fk, donhang_fk, KBH_FK, Kho_FK, MA, TEN, DONVI, DVCHUAN, DVDATHANG, SOLUONG, SOLO, NGAYHETHAN, CHIETKHAU, THUEVAT, DONGIA,SoLuong_Chuan,DonGia_Chuan,SoLuong_DatHang,ngaynhapkho)  " +
					"		select '" + this.id + "' as hoadon_fk, dh.pk_seq as donhang_fk, a.KBH_FK, a.KHO_FK, c.MA, isnull(dhsp.sanphamTEN ,c.TEN ) as TEN, (select donvi from DONVIDOLUONG where pk_seq = dhsp.dvdl_fk ) as donvi, d.pk_seq as dvCHUAN, dhsp.dvdl_fk  as dvDATHANG, "+    
					"	case when d.pk_seq = dhsp.dvdl_fk then b.soluong  "+    
					"			else b.soluong * ( select SOLUONG2 / SOLUONG1 from QUYCACH where sanpham_fk = c.pk_seq and DVDL2_FK = dhsp.dvdl_fk and DVDL1_FK = d.pk_seq ) end as soluong, "+      
					"	case solo when 'NA' then ' ' else b.solo end as solo,     "+
					"	case solo when 'NA' then ' ' else isnull(b.NGAYHETHAN,'') end as NGAYHETHAN, " +
					"		CASE WHEN ROW_NUMBER() OVER(PARTITION BY c.ma ORDER BY c.ma DESC) = 1 then dhsp.chietkhau else 0 end as chietKhau "+
					", dhsp.thuevat,    "+
					"	( select dongia from ERP_HOADON_SP where hoadon_fk ='" + this.id + "' and sanpham_fk = b.sanpham_fk ) as dongia  	, "+   
					"		b.soluong  as SoLuong_Chuan,   "+
					"			case when d.pk_seq = dhsp.dvdl_fk then dhsp.DONGIA "+     
					"	else dhsp.DONGIA *    "+
					"	( select SOLUONG2 / SOLUONG1 "+
					"	from QUYCACH where sanpham_fk = c.pk_seq and DVDL2_FK = dhsp.dvdl_fk and DVDL1_FK = d.pk_seq ) end as DonGia_Chuan ,   "+
					"		CASE WHEN ROW_NUMBER() OVER(PARTITION BY c.ma ORDER BY c.ma DESC) = 1 THEN dhsp.soluong else 0 end as SoLuong_DatHang,b.ngaynhapkho "+  
					" from ERP_YCXUATKHO a inner join ERP_YCXUATKHO_SANPHAM_CHITIET b on a.pk_seq = b.ycxk_fk "+	   
					"     inner join 	ERP_YCXUATKHO_DDH e on e.ycxk_fk = a.PK_SEQ									       "+ 									   
					"     inner join ERP_DONDATHANG dh on dh.PK_SEQ = e.ddh_fk    									    "+
					"     inner join ERP_DONDATHANG_SANPHAM dhsp on dhsp.dondathang_fk = dh.PK_SEQ and dhsp.sanpham_fk = b.sanpham_fk "+	   
					"     inner join SANPHAM c on dhsp.sanpham_fk = c.PK_SEQ   "+						   
					"     inner join DONVIDOLUONG d on d.PK_SEQ = c.dvdl_fk "+ 	   
					" where dh.trangthai != '3' and a.PK_SEQ in    "+
					"  ( select ycxk_fk from ERP_YCXUATKHO_DDH   "+
					"  where ddh_fk = ( select DDH_FK from ERP_HOADON_DDH where hoadon_fk = '" + this.id + "' ) ) and b.soluong > 0 and a.TRANGTHAI != '3' " ;
			
			System.out.println("---CHAY HOA DON CHI TIET: " + query );
			if(!db.update(query))
			{
				msg = "Hóa đơn đã chốt rồi hoặc có lỗi phát sinh trong quá trình cập nhật "+query;
				db.getConnection().rollback();
				return false;
			}
			
			//CHECK BANG TONG PHAI BANG BANG CHI TIET
			query = "select count(*) as sodong  " +
					"from " +
					"( " +
					"	select b.pk_seq as sanpham_fk, sum(soluong) as soluong  " +
					"	from ERP_HOADON_SP a inner join SANPHAM b on a.sanpham_fk = b.pk_seq " +
					"	where a.hoadon_fk = '" + this.id + "' " +
					"	group by b.pk_seq " +
					") " +
					"dh left join " +
					"( " +
					"	select b.pk_seq as sanpham_fk, sum(soluong) as soluong  " +
					"	from ERP_HOADON_SP_CHITIET a inner join SANPHAM b on a.MA = b.MA " +
					"	where a.hoadon_fk = '" + this.id + "' " +
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
				db.getConnection().rollback();
				return false;
			}
			
			msg= util.Check_Kho_Tong_VS_KhoCT(util.getIdNhapp(userId), db);
			if(msg.length()>0)
			{
				db.getConnection().rollback();
				return false;
			}

			//LUU LAI THONG TIN
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

	
	public boolean update() 
	{
		Utility util_kho = new Utility();

		//NEU HOA DON DA HUY THI CHI DUOC SUA KY HIEU VA SO HOA DON
		String sqlCHECK = "select a.trangthai, b.loaiNPP from ERP_HOADON a inner join NHAPHANPHOI b on a.npp_fk = b.pk_seq " +
						  "where a.pk_seq = '" + this.id + "'";
		System.out.println("Lấy trạng thái "+sqlCHECK);
		ResultSet rsCHECK = db.get(sqlCHECK);
		String loaiNPP = "";
		try 
		{
			if(rsCHECK.next())
			{
				this.trangthai = rsCHECK.getString("trangthai");
				loaiNPP = rsCHECK.getString("loaiNPP");
			}
			rsCHECK.close();
		} 
		catch (Exception e2) 
		{
			e2.printStackTrace();
		}

		if(this.ngayxuatHD.trim().length() < 10)
		{
			this.msg = "Vui lòng nhập ngày xuất hóa đơn";
			return false;
		}

		if(this.ngayghinhanCN.trim().length() < 10)
		{
			this.msg = "Vui lòng nhập ngày ghi nhận";
			return false;
		}

		if(trangthai.equals("1") || trangthai.equals("2") || trangthai.equals("4") )
		{
			if(this.ddhId.length() <= 0)
			{
				this.msg = "Vui lòng chọn đơn đặt hàng";
				return false;
			}

			if(spMa == null)
			{
				this.msg = "Vui lòng kiểm tra lại danh sách sản phẩm  xuất hoá đơn tài chính";
				return false;
			}
			else
			{
				boolean coSP = false;
				for(int i = 0; i < spId.length; i++)
				{
					if( spSoluong[i].trim().length() > 0 )
					{
						if(Double.parseDouble(spSoluong[i].trim().replaceAll(",", "")) > 0)
							coSP = true;
					}
				}

				if(!coSP)
				{
					this.msg = "Vui lòng kiểm tra lại danh sách sản phẩm  xuất hoá đơn tài chính";
					return false;
				}	
			}
		}


		try
		{
			db.getConnection().setAutoCommit(false);

			// LAY KY HIEU HOA DON ,SOHDTU TRONG DLN
			String chuoi="";
			long sohoadontu=0;
			String sohoadonden="";
			int kbDLN=0;
			String mau = "1";

			String query =" select isnull(SOHOADONTU,'') as SOHOADONTU,isnull(SOHOADONDEN,'') as SOHOADONDEN,isnull(SOHOADONTU2,'') as SOHOADONTU2,isnull(SOHOADONDEN2,'') as SOHOADONDEN2 from NHANVIEN where pk_seq="+userId;
			System.out.println("AAAAA:"+ query);
			ResultSet mauHDrs = db.get(query);

			String SOHOADONTU1="";
			String SOHOADONDEN1="";
			String SOHOADONTU2="";
			String SOHOADONDEN2="";

			if(mauHDrs!=null)
			{
				while(mauHDrs.next())
				{
					SOHOADONTU1 = mauHDrs.getString("SOHOADONTU");
					SOHOADONDEN1 = mauHDrs.getString("SOHOADONDEN");
					SOHOADONTU2 = mauHDrs.getString("SOHOADONTU2");
					SOHOADONDEN2 = mauHDrs.getString("SOHOADONDEN2");

				}
				mauHDrs.close();
			}
			if(!loaiNPP.equals("4"))
			{
				String query_kyhieu = " KYHIEU ";				
				String query_sohdTU = " SOHOADONTU ";	
				String query_sohdDEN = " SOHOADONDEN ";	
				String query_mauhd = "1";
				String query_ngayhd = " NGAYHOADON  ";


				if(SOHOADONDEN1.trim().length()>0 && SOHOADONTU1.trim().length()>0)
				{
					query_kyhieu = " KYHIEU ";				
					query_sohdTU = " SOHOADONTU ";	
					query_sohdDEN = " SOHOADONDEN ";	
					query_mauhd = "1";
					query_ngayhd = " NGAYHOADON  ";
				}
				if(SOHOADONDEN2.trim().length()>0 && SOHOADONTU2.trim().length()>0)
				{
					query_kyhieu = " KYHIEU2 ";				
					query_sohdTU = " SOHOADONTU2 ";	
					query_sohdDEN = " SOHOADONDEN2 ";				
					query_mauhd = "2";
					query_ngayhd = " NGAYHOADON2 ";
				}

				mau = query_mauhd;
				query=   " select  "+query_sohdTU+" as sohoadontu , "+query_sohdDEN+" as sohoadonden  " +
						" from NHANVIEN" +
						" where pk_seq= '"+ this.userId +"' and isnull("+query_kyhieu+", '') = '"+ this.kyhieuhoadon +"' ";
				System.out.println("Câu query: "+query);
				ResultSet rsLayDL =  db.get(query);
				if(rsLayDL != null)
				{
					while(rsLayDL.next())
					{
						System.out.println ("---sdas-----------"+query_sohdTU+", "+query_sohdDEN+"");
						sohoadontu = rsLayDL.getLong("sohoadontu");
						sohoadonden = rsLayDL.getString("sohoadonden");
					}
					rsLayDL.close();
				}

				if(sohoadontu == 0 || sohoadonden.trim().length() <= 0)
				{
					this.msg = " Ký hiệu hóa đơn vừa sửa không giống với ký hiệu khai báo trong dữ liệu nền/ Chưa khai báo số hóa đơn trong dữ liệu nền (Số hóa đơn) ";
					db.getConnection().rollback();
					return false;
				}

				// ETC
				query= " select  count(pk_seq) as kiemtra " +
						" from ERP_HOADON " +
						" where nguoisua= '"+ this.userId +"' and RTRIM(LTRIM(sohoadon)) = '"+ this.sohoadon.trim() +"' and RTRIM(LTRIM(kyhieu)) ='"+ this.kyhieuhoadon +"' " +
						"       and trangthai != 3  and pk_seq != "+ this.id +"  and mauhoadon ="+mau+" ";
				System.out.println("Cau kiem tra so hoa don "+query);
				ResultSet KtraSHD_ETC =  db.get(query);
				int ktra_ETC= 0;

				if(KtraSHD_ETC != null)
				{
					while(KtraSHD_ETC.next())
					{
						ktra_ETC = KtraSHD_ETC.getInt("kiemtra");
					}
					KtraSHD_ETC.close();
				}


				if (ktra_ETC > 0 )
				{
					this.msg = "Số hóa đơn này đã được sử dụng. Vui lòng đánh số hóa đơn khác. ";
					db.getConnection().rollback();
					return false;
				}
				else if (this.sohoadon.trim().length() != 7 )
				{
					this.msg = "Số hóa đơn phải đủ 7 ký tự . Vui lòng kiểm tra lại. ";
					db.getConnection().rollback();
					return false;
				}
			}

			query = "";
			if(!trangthai.equals("1") )  //NEU DA DUYET THI CHI DUOC SUA CAC THONG NAY
			{
				query = " update ERP_HOADON set  ngaysua = '" + getDateTime() + "' , nguoisua ='" + this.userId + "'," +
						" kyhieu = RTRIM(LTRIM('" + this.kyhieuhoadon + "')) , sohoadon= RTRIM(LTRIM('" + this.sohoadon + "')), hinhthuctt= N'"+ this.hinhthuctt +"', ghichu= N'"+ this.ghichu +"' " +
						" where pk_seq = '"+ this.id +"' and TrangThai!=1 " ;
				System.out.println("1.Update ERP_HOADON1: " + query);
				if(db.updateReturnInt(query)!=1)
				{
					this.msg = "Hóa đơn đã chốt,vui lòng kiểm tra lại "+query;
					db.getConnection().rollback();
					return false;
				}

				for(int i = 0; i < spId.length; i++)
				{
					if(spId[i].trim().length() > 0 && Double.parseDouble(spSoluong[i].trim().replaceAll(",", "")) > 0 )
					{	
						query = " update ERP_HOADON_SP set  tensp = N'" + spTen[i].replaceAll(",", "") + "' "+
								" where hoadon_fk = '"+ this.id +"' and sanpham_fk='"+spId[i]+"'" ;

						System.out.println("1.1.update ERP_HOADON_SP: " + query);
						if(!db.update(query))
						{
							this.msg = "Khong the tao moi update ERP_HOADON_SP: " + query;
							db.getConnection().rollback();
							return false;
						}
					}
				}
			}
			else
			{
				query = " update ERP_HOADON set innguoimua= "+ this.innguoimua +" , nguoimua = N'"+ this.nguoimua +"' , dondathang_fk = "+ this.ddhId + ", ngayxuatHD = '" + this.ngayxuatHD + "' , ngaysua = '" + getDateTime() + "' , nguoisua ='" + this.userId + "'," +
						" kyhieu = RTRIM(LTRIM('" + this.kyhieuhoadon + "')) , sohoadon= RTRIM(LTRIM('" + this.sohoadon + "')), hinhthuctt= N'"+ this.hinhthuctt +"' ," +
						" chietkhau =  '"+ this.totalCHIETKHAU.replaceAll(",", "")  +"' , tongtienbvat= '"+ this.bvat.replaceAll(",", "") +"', tongtienavat='" + this.avat.replaceAll(",", "") + "', vat = '"+ this.thueVAT.replaceAll(",", "") + "', ghichu= N'"+ this.ghichu +"'," +
						" loaixuathd= '"+ this.loaiXHD +"' , npp_fk = "+ this.nppId +" ,mavv='" +this.mavuviec+"'"+
						" where pk_seq = '"+ this.id +"'" ;

				System.out.println("1.Update ERP_HOADON2: " + query);
				if(!db.update(query))
				{
					this.msg = "Không thể cập nhật ERP_HOADON " + query;
					db.getConnection().rollback();
					return false;
				}

				Utility util = new Utility();
				msg= util.Check_Huy_NghiepVu_KhoaSo("ERP_HOADON", id, "ngayxuatHD", db);
				if(msg.length()>0)
				{
					db.getConnection().rollback();
					return false;
				}

				query = "delete from ERP_HOADON_SP where hoadon_fk = '"+ this.id +"' " ;
				if(!db.update(query))
				{
					this.msg = "Không thể xóa HOADON_SP " + query;
					db.getConnection().rollback();
					return false;
				}

				

				query = "delete from ERP_HOADON_DDH where hoadon_fk = '"+ this.id +"' " ;
				if(!db.update(query))
				{
					this.msg = "Không thể xóa ERP_HOADON_DDH " + query;
					db.getConnection().rollback();
					return false;
				}

				for(int i = 0; i < spId.length; i++)
				{
					if(spId[i].trim().length() > 0 && Double.parseDouble(spSoluong[i].trim().replaceAll(",", "")) > 0 )
					{
						String thanhtien = spSoluong[i].replaceAll(",", "") + " * "+ spDongia[i].replaceAll(",", "");

						query = "insert ERP_HOADON_SP( hoadon_fk, sanpham_fk, donvitinh, soluong, dongia, thanhtien, chietkhau, scheme, vat, TIENVAT,tensp ) values " +
								" ('"+ this.id +"', '" + spId[i] + "', N'"+ spDonvi[i] +"', '" + spSoluong[i].replaceAll(",", "") + "', '" + spDongia[i].replaceAll(",", "") + "'," +
								" ("+ thanhtien +"), '"+ spChietkhau[i].replaceAll(",", "") +"', N'"+ spSCheme[i].replaceAll(",", "") +"','"+ spVat[i].replaceAll(",", "") +"', '" + spTienthue[i].replaceAll(",", "") + "',N'" + spTen[i].replaceAll(",", "") + "' )  ";

						System.out.println("1.1.Insert ERP_HOADON_SP: " + query);
						if(!db.update(query))
						{
							this.msg = "Khong the tao moi ERP_HOADON_SP: " + query;
							db.getConnection().rollback();
							return false;
						}
					}
				}

				//Tăng kho ngược lại trước khi xóa - trường hợp đổi số lô
				query =  "  select a.soluong,c.NPP_FK, a.Kho_FK, b.PK_SEQ as sanpham_fk, a.SOLO, a.NGAYHETHAN, a.NGAYNHAPKHO  " + 
						 "  from ERP_HOADON_SP_ChiTiet a inner join SANPHAM b on a.MA = b.MA  " + 
						 "  		inner join ERP_HOADON c on a.hoadon_fk = c.PK_SEQ " + 
						 "  where hoadon_fk = '" + this.id + "' ";
				System.out.println("::: TANG KHO NGUOC LAI TRUOC KHI XOA: " + query);
				ResultSet rs = db.get(query);
				while(rs.next())
				{
					double SoLuong=rs.getDouble("SoLuong");
					String khoId =rs.getString("Kho_FK");
					String sanpham_fk =rs.getString("sanpham_fk");
					String SoLo =rs.getString("SoLo");
					String NgayHetHan =rs.getString("NgayHetHan");
					String Ngaynhapkho =rs.getString("Ngaynhapkho");

					String msg1= util_kho.Update_KhoTT(this.ngayxuatHD, "HO / Sửa hóa đơn ETC - tang kho nguoc lai :ErpHoadontaichinh.java 2584 ", db, 
							khoId, sanpham_fk, SoLo, NgayHetHan, Ngaynhapkho, 0, -1 * SoLuong, SoLuong);
					if(msg1.length() >0){
						msg = msg1;
						db.getConnection().rollback();
						return false;
					}	
				}

				// cập nhật lại kho chi tiết 
				query = "delete from ERP_HOADON_SP_ChiTiet where hoadon_fk="+this.id+"";
				if(db.updateReturnInt(query)<=0)
				{
					msg = "Khong the cap nhat ERP_HOADON_SP_ChiTiet: " + query;
					db.getConnection().rollback();
					return false;
				}
				
				query = "		update C set SoLuong_Chuan=  "+
						"				case     when c.donvitinh = e.donvi then c.soluong  "+     
						"								  else c.soluong * ( select SOLUONG1 / SOLUONG2 from QUYCACH where sanpham_fk = c.sanpham_fk and DVDL2_FK = ( select pk_seq from DONVIDOLUONG where donvi = c.donvitinh ) and DVDL1_FK = d.DVDL_FK )   end  ,  "+ 
						"		DonGia_Chuan=	case     when c.donvitinh = e.donvi then c.dongia      "+
						"							  else c.dongia * ( select SOLUONG2 / SOLUONG1 from QUYCACH where sanpham_fk = c.sanpham_fk and DVDL2_FK = ( select pk_seq from		DONVIDOLUONG where donvi = c.donvitinh ) and DVDL1_FK = d.DVDL_FK )   end  , "+
						"			DonVi_Chuan=e.DONVI  "+
						"		from ERP_HOADON a         "+
						"			inner join ERP_HOADON_SP c on a.pk_seq = c.hoadon_fk  "+ 
						"			inner join SANPHAM d on c.sanpham_fk = d.pk_seq    "+
						"			inner join DONVIDOLUONG e on d.DVDL_FK = e.pk_seq  "+      
						"		where a.pk_Seq='"+this.id+"' ";

				System.out.println("1.1.UPDATE ERP_HOADON_SP: " + query);
				if(!db.update(query))
				{
					this.msg = "Không thể tạo mới ERP_HOADON_SP " + query;
					db.getConnection().rollback();
					return false;
				}	

				query = "Insert ERP_HOADON_DDH(hoadon_fk, ddh_fk) select '"+ this.id +"', pk_seq from ERP_DONDATHANG where pk_seq in ( " + this.ddhId + " )  ";
				if(!db.update(query))
				{
					this.msg = "Không thể tạo mới ERP_HOADON_DDH " + query;
					db.getConnection().rollback();
					return false;
				}

				

				query = "delete ERP_DONDATHANG_SANPHAM_CHITIET where dondathang_fk in  (" + this.ddhId + ") ";
				if(!db.update(query))
				{
					this.msg = "Lỗi khi duyệt: " + query;
					db.getConnection().rollback();
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

								if(key.startsWith( spMa[i] + "__" ) )
								{
									String[] _sp = key.split("__");

									String _soluongCT = "0"; 
									if(this.sanpham_soluong.get(key) != null)
									{
										_soluongCT = this.sanpham_soluong.get(key).replaceAll(",", "");
									}

									totalCT += Double.parseDouble(_soluongCT);
									String ngaynhapkho=_sp[3]; 

									query = "insert ERP_DONDATHANG_SANPHAM_CHITIET( dondathang_fk, SANPHAM_FK, dvdl_fk, solo, soluong, ngayhethan, ngaynhapkho )  " +
											"select '" + ddhId+ "', pk_seq, ( select dvdl_fk from ERP_DONDATHANG_SANPHAM where dondathang_fk = '" + ddhId + "' and sanpham_fk = a.pk_seq ),   " +
											" N'" + _sp[1] + "' as solo, '" + _soluongCT.replaceAll(",", "") + "' as soluong, '"+_sp[2]+"' as NgayHetHan ,'"+ngaynhapkho+"' as  ngaynhapkho  " +
											" from SANPHAM a where MA = '" + spMa[i] + "'  ";

									System.out.println("1.2.Insert DDH - SP - CT: " + query);
									if(db.updateReturnInt(query) < 1)
									{
										this.msg = "Khong the tao moi ERP_DONDATHANG_SANPHAM_CHITIET: " + query;
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
						"from ERP_DONDATHANG_SANPHAM tong left join   " +
						"	(  " +
						"		select sanpham_fk, sum(soluong) as soluong   " +
						"		from ERP_DONDATHANG_SANPHAM_CHITIET  " +
						"		where  dondathang_fk = '" + this.ddhId + "'  " +
						"		group by sanpham_fk " +
						"	)  " +
						"	CT on tong.sanpham_fk = CT.sanpham_fk " +
						"where dondathang_fk = '" + this.ddhId + "' and tong.soluong != isnull(CT.soluong, 0)  " ;

				System.out.println("[CheckTong_CT]"+query);

				rsCHECK = db.get(query);
				int soDONG = 0;
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
					this.msg = "11.Lỗi hệ thống ( tổng xuất theo lô đề xuất đang bị sai ). Vui lòng liên hệ trung tâm để được hỗ trợ xử lý."+query;
					return false;
				}

				//CAP NHAT TON KHO CHI TIET
				query = "select c.kho_fk, a.sanpham_fk, b.ten as TEN, a.soluong as soluongDAT, a.solo, a.ngayhethan, a.ngaynhapkho , " +
						"		case when a.dvdl_fk IS null then a.soluong      " +
						"			 when a.dvdl_fk = b.DVDL_FK then a.soluong     " +
						"			 else  a.soluong * ( select SOLUONG1 / SOLUONG2 from QUYCACH where sanpham_fk = a.sanpham_fk and DVDL2_FK = a.dvdl_fk and DVDL1_FK = b.DVDL_FK )   end as soluong,  " +
						"	0 as loai, ' ' as scheme    " +
						"from ERP_DONDATHANG_SANPHAM_CHITIET a inner join SANPHAM b on a.sanpham_fk = b.PK_SEQ   " +
						"		inner join ERP_DONDATHANG c on  a.dondathang_fk = c.pk_seq  " +
						"where a.dondathang_fk in ( " + this.ddhId + " )   ";

				System.out.println("--CHECK TON KHO CHI TIET: " + query);
				rs = db.get(query);				
				{
					while(rs.next())
					{
						String spID = rs.getString("sanpham_fk");
						String khoID = rs.getString("kho_fk");

						double soluong = rs.getDouble("soluong");
						String solo = rs.getString("solo");
						String ngayhethan = rs.getString("ngayhethan");
						String ngaynhapkho=rs.getString("ngaynhapkho");

						String msg1= util_kho.Update_KhoTT(this.ngayxuatHD, "Sửa hóa đơn đối tác :ErpHoadontaichinh.java 2660 ", db, khoID, spID, solo, ngayhethan, ngaynhapkho, 0, soluong, soluong*(-1));
						if(msg1.length() >0){
							msg = msg1;
							db.getConnection().rollback();
							return false;
						}

						/*query = "insert ERP_YCXUATKHO_SANPHAM_CHITIET( ycxk_fk, SANPHAM_FK, solo, soluong, ngayhethan, kho_fk,ngaynhapkho ) " +
								"select " + ycxkId + ", '" + spID + "', N'" + solo + "', '" + soluong  + "', '" + ngayhethan + "', '" + khoID + "' ,'"+ngaynhapkho+"'";

						System.out.println("1.2.Insert YCXK - SP - CT: " + query);
						if(db.updateReturnInt(query)!=1)
						{
							msg = "Khong the cap nhat ERP_YCXUATKHO_SANPHAM_CHITIET: " + query;
							rs.close();
							db.getConnection().rollback();
							return false;
						}	*/					
					}
					rs.close();
				}

				query = "  insert ERP_HOADON_SP_CHITIET(hoadon_fk, donhang_fk, KBH_FK, Kho_FK, MA, TEN, DONVI, DVCHUAN, DVDATHANG, SOLUONG, SOLO, NGAYHETHAN, NGAYNHAPKHO, CHIETKHAU, THUEVAT, DONGIA,SoLuong_Chuan,DonGia_Chuan,SoLuong_DatHang)   " + 
						"  select '" + this.id + "' as hoadon_fk, a.pk_seq as donhang_fk, a.KBH_FK, a.KHO_FK, c.MA, (select top(1) tensp from ERP_HOADON_SP where HOADON_FK="+this.id+" and sanpham_fk=b.sanpham_fk) as TEN, (select donvi from DONVIDOLUONG where pk_seq = dhsp.dvdl_fk ) as donvi, d.pk_seq as dvCHUAN, dhsp.dvdl_fk  as dvDATHANG,    " + 
						"  	b.soluong,    " + 
						"  	b.solo, b.NGAYHETHAN, NGAYNHAPKHO, dhsp.chietkhau, dhsp.thuevat,   " + 
						"  	dhsp.dongia,   " + 
						"  	b.soluong * dbo.LayQuyCach( b.SANPHAM_FK, null, b.DVDL_FK ) as SoLuong_Chuan,   " + 
						"  	dhsp.dongia * dbo.LayQuyCach_DVBan( b.SANPHAM_FK, null, b.DVDL_FK ) as DonGia_Chuan,   " + 
						"  	dhsp.soluong as SoLuong_DatHang   " + 
						"  from ERP_DONDATHANG a inner join ERP_DONDATHANG_SANPHAM_CHITIET b on a.pk_seq = b.dondathang_fk	  								   " + 
						"       inner join ERP_DONDATHANG_SANPHAM dhsp on dhsp.dondathang_fk = a.PK_SEQ and dhsp.sanpham_fk = b.sanpham_fk	   " + 
						"       inner join SANPHAM c on dhsp.sanpham_fk = c.PK_SEQ  						   " + 
						"       inner join DONVIDOLUONG d on d.PK_SEQ = c.dvdl_fk 	   " + 
						"  where a.trangthai != '3' and a.PK_SEQ = '" + this.ddhId + "' and b.soluong > 0  ";

				if(db.updateReturnInt(query)<=0)
				{
					msg = "Khong the cap nhat ERP_HOADON_SP_CHITIET: " + query;
					rs.close();
					db.getConnection().rollback();
					return false;
				}						
			}				

			/*Utility util = new Utility();
			msg = util.Check_Kho_Tong_VS_KhoCT(util.getIdNhapp(userId), db);
			if(msg.length()>0)
			{
				db.getConnection().rollback();
				return false;
			}*/

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

		this.msg = "Số hóa đơn bạn vừa lưu: " + this.sohoadon;
		return true;
	}
	
	public boolean chot_update()
	{
		try
		{
			Utility util = new Utility();
			
			boolean flag = this.update();
			if( flag == false )
				return flag;
			
			String query = "";
			
			
	
			query = "update ERP_HOADON set trangthai = '2', NguoiSua='"+this.userId+"',NgaySua='"+getDateTime()+"'  where pk_seq = '" + this.id + "' and TrangThai!=2 ";
			if(db.updateReturnInt(query)!=1)
			{
				msg = "Hóa đơn đã chốt rồi hoặc có lỗi phát sinh trong quá trình cập nhật "+query;
				return false;
			}
			
			query = " select a.npp_fk, a.noibo, a.loaihoadon, dh.isKM  " +
					" from ERP_HOADON a " +
					" inner join ERP_HOADON_DDH ddh on ddh.HOADON_FK=a.PK_SEQ "+
					" inner join ERP_DONDATHANG dh on dh.PK_SEQ=ddh.DDH_FK "+
					" where a.pk_seq = "+this.id;

			ResultSet rs=db.get(query);
			String npp="";
			int noibo = 0;
			int isKM = 0;
			
			if(rs.next())
			{
				npp = rs.getString("npp_fk");
				noibo = rs.getInt("NOIBO");
				isKM = rs.getInt("isKM");
			}
	
			if(noibo == 1 ) // HÓA ĐƠN NỘI BỘ
			{
				// NẾU LÀ ĐƠN NỘI BỘ THÌ TỰ ĐỘNG TẠO HÓA ĐƠN NCC DƯỚI CHI NHÁNH
				db.execProceduce2("CREATE_INVOICE_SUPPLIER", new String[] { this.id } );
			}
			this.msg = "Số hóa đơn bạn vừa chốt: " + this.sohoadon;
			return true;
		}
		catch(Exception ex)
		{
			ex.printStackTrace();
		}
		
		return true;
		
	}

	
	public String[] getSpLoai() {

		return this.spLoai;
	}


	public void setSpLoai(String[] spLoai) {

		this.spLoai = spLoai;
	}

	
	public String[] getSpScheme() {
		
		return this.spSCheme;
	}

	
	public void setSpScheme(String[] spScheme) {
		
		this.spSCheme = spScheme;
	}

	public boolean chot() 
	{
		try
		{
			
			Utility util = new Utility();
			msg= util.Check_Huy_NghiepVu_KhoaSo("ERP_HOADON", id, "NgayXuatHD", db);
			if(msg.length()>0)
				return false;
			
			db.getConnection().setAutoCommit(false);
			String query = "update ERP_HOADON set trangthai = '2',NguoiSua='"+this.userId+"',NgaySua='"+getDateTime()+"'  where pk_seq = '" + this.id + "' and TrangThai!=2 ";
			if(db.updateReturnInt(query)!=1)
			{
				msg = "Hóa đơn đã chốt rồi hoặc có lỗi phát sinh trong quá trình cập nhật "+query;
				db.getConnection().rollback();
				return false;
			}
			query="delete from ERP_HOADON_SP_CHITIET where hoadon_fk='"+this.id+"'";
			if(!db.update(query))
			{
				msg = "Hóa đơn đã chốt rồi hoặc có lỗi phát sinh trong quá trình cập nhật "+query;
				db.getConnection().rollback();
				return false;
			}
			
			query = 
					" insert ERP_HOADON_SP_CHITIET(hoadon_fk, donhang_fk, KBH_FK, Kho_FK, MA, TEN, DONVI, DVCHUAN, DVDATHANG, SOLUONG, SOLO, NGAYHETHAN, CHIETKHAU, THUEVAT, DONGIA,SoLuong_Chuan,DonGia_Chuan,SoLuong_DatHang,ngaynhapkho)  " +
					"		select '" + this.id + "' as hoadon_fk, dh.pk_seq as donhang_fk, a.KBH_FK, a.KHO_FK, c.MA, isnull(dhsp.sanphamTEN ,c.TEN ) as TEN, (select donvi from DONVIDOLUONG where pk_seq = dhsp.dvdl_fk ) as donvi, d.pk_seq as dvCHUAN, dhsp.dvdl_fk  as dvDATHANG, "+    
					"	case when d.pk_seq = dhsp.dvdl_fk then b.soluong  "+    
					"			else b.soluong * ( select SOLUONG2 / SOLUONG1 from QUYCACH where sanpham_fk = c.pk_seq and DVDL2_FK = dhsp.dvdl_fk and DVDL1_FK = d.pk_seq ) end as soluong, "+      
					"	case solo when 'NA' then ' ' else b.solo end as solo,     "+
					"	case solo when 'NA' then ' ' else isnull(b.NGAYHETHAN,'') end as NGAYHETHAN, " +
					"		CASE WHEN ROW_NUMBER() OVER(PARTITION BY c.ma ORDER BY c.ma DESC) = 1 then dhsp.chietkhau else 0 end as chietKhau "+
					", dhsp.thuevat,    "+
					"	( select dongia from ERP_HOADON_SP where hoadon_fk ='" + this.id + "' and sanpham_fk = b.sanpham_fk ) as dongia  	, "+   
					"		b.soluong  as SoLuong_Chuan,   "+
					"			case when d.pk_seq = dhsp.dvdl_fk then dhsp.DONGIA "+     
					"	else dhsp.DONGIA *    "+
					"	( select SOLUONG2 / SOLUONG1 "+
					"	from QUYCACH where sanpham_fk = c.pk_seq and DVDL2_FK = dhsp.dvdl_fk and DVDL1_FK = d.pk_seq ) end as DonGia_Chuan ,   "+
					"		CASE WHEN ROW_NUMBER() OVER(PARTITION BY c.ma ORDER BY c.ma DESC) = 1 THEN dhsp.soluong else 0 end as SoLuong_DatHang ,b.ngaynhapkho "+  
					" from ERP_YCXUATKHO a inner join ERP_YCXUATKHO_SANPHAM_CHITIET b on a.pk_seq = b.ycxk_fk "+	   
					"     inner join 	ERP_YCXUATKHO_DDH e on e.ycxk_fk = a.PK_SEQ									       "+ 									   
					"     inner join ERP_DONDATHANG dh on dh.PK_SEQ = e.ddh_fk    									    "+
					"     inner join ERP_DONDATHANG_SANPHAM dhsp on dhsp.dondathang_fk = dh.PK_SEQ and dhsp.sanpham_fk = b.sanpham_fk "+	   
					"     inner join SANPHAM c on dhsp.sanpham_fk = c.PK_SEQ   "+						   
					"     inner join DONVIDOLUONG d on d.PK_SEQ = c.dvdl_fk "+ 	   
					" where dh.trangthai != '3' and a.PK_SEQ in    "+
					"  ( select ycxk_fk from ERP_YCXUATKHO_DDH   "+
					"  where ddh_fk = ( select DDH_FK from ERP_HOADON_DDH where hoadon_fk = '" + this.id + "' ) ) and b.soluong > 0 and a.TRANGTHAI != '3' " ;
			
			System.out.println("---CHAY HOA DON CHI TIET: " + query );
			if(!db.update(query))
			{
				msg = "Hóa đơn đã chốt rồi hoặc có lỗi phát sinh trong quá trình cập nhật "+query;
				db.getConnection().rollback();
				return false;
			}
			
			//CHECK BANG TONG PHAI BANG BANG CHI TIET
			query = "select count(*) as sodong  " +
					"from " +
					"( " +
					"	select b.pk_seq as sanpham_fk, sum(soluong) as soluong  " +
					"	from ERP_HOADON_SP a inner join SANPHAM b on a.sanpham_fk = b.pk_seq " +
					"	where a.hoadon_fk = '" + this.id + "' " +
					"	group by b.pk_seq " +
					") " +
					"dh left join " +
					"( " +
					"	select b.pk_seq as sanpham_fk, sum(soluong) as soluong  " +
					"	from ERP_HOADON_SP_CHITIET a inner join SANPHAM b on a.MA = b.MA " +
					"	where a.hoadon_fk = '" + this.id + "' " +
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
				db.getConnection().rollback();
				return false;
			}
			
			msg= util.Check_Kho_Tong_VS_KhoCT(util.getIdNhapp(userId), db);
			if(msg.length()>0)
			{
				db.getConnection().rollback();
				return false;
			}

			//LUU LAI THONG TIN
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

	public String getNgayghinhanCN() 
	{
		return this.ngayghinhanCN;
	}

	public void setNgayghinhanCN(String ngayghinhanCN) 
	{
		this.ngayghinhanCN = ngayghinhanCN;
	}

	public String getKyhieuhoadon() 
	{
		return this.kyhieuhoadon;
	}

	public void setKyhieuhoadon(String kyhieuhoadon) 
	{
		this.kyhieuhoadon = kyhieuhoadon;
		
	}

	public String getSohoadon() 
	{		
		return this.sohoadon;
	}

	public void setSohoadon(String sohoadon) 
	{
		this.sohoadon = sohoadon;
	}

	public String[] getSpDongia() 
	{
		return this.spDongia;
	}

	public void setSpDongia(String[] spDongia) 
	{
		this.spDongia = spDongia;
		
	}

	public String[] getSpChietkhau()
	{
		return this.spChietkhau;
	}

	public void setSpChietkhau(String[] spChietkhau) 
	{
		this.spChietkhau = spChietkhau;
		
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

	public String getLoaidonhang() {
		
		return this.loaidonhang;
	}

	
	public void setLoaidonhang(String loaidonhang) {
		
		this.loaidonhang = loaidonhang;
	}

	
	public String getNguoimua() 
	{
		return this.nguoimua;
	}

	
	public void setNguoimua(String nguoimua) {
		this.nguoimua = nguoimua;
		
	}


	public String getLoaiXHD() {
		
		return this.loaiXHD;
	}

	public void setLoaiXHD(String loaiXHD) {
		this.loaiXHD= loaiXHD;
		
	}

	
	public String[] getSpVat() {
		
		return this.spVat;
	}

	
	public void setSpVat(String[] spVat) {
		 this.spVat = spVat;
		
	}

	
	public void loadContents()
	{
		String query ="";
		Utility util = new Utility();

		query = "select PK_SEQ, MA + ' - ' + TEN as TEN from NHAPHANPHOI where TRANGTHAI = '1'  ";
		this.nppRs = db.get(query);
		
		if(this.nppId.trim().length() > 0 )
		{			
			query =   " select PK_SEQ , NgayDonHang  " +
					" from ERP_DONDATHANG " +
					" where NPP_FK = '"+ this.nppId +"' " +
					" and pk_seq not in(select a.DDH_FK from  ERP_HOADON_DDH a inner join ERP_HOADON b on a.HOADON_FK=b.PK_SEQ where b.TRANGTHAI in ( 1, 2, 4 ) and b.pk_seq != " + ( this.id.trim().length() > 0 ? this.id : "-1" ) + " )  " +
					" order by NgayDonHang desc ";
		  	System.out.println("Câu query "+query);		
			this.ddhRs = db.get(query);
		}
		
		String chuoi = this.ddhId;
		if(chuoi.length() > 0)
		{	
			// INIT TONG TIEN VAT
		  try 
		   {
				NumberFormat formater = new DecimalFormat("##,###,###");
			    
			 query = "select b.PK_SEQ as SPID, b.MA, b.TEN, DV.donvi, a.dongia , "+
				    "  isnull(scheme,' ') as scheme , isnull(a.thuevat,0) as vat ,   " +
				    "  SUM( a.soluong)  as soluong, SUM( isnull(a.chietkhau, 0)) as chietkhau "+
				    "from ERP_DONDATHANG_SANPHAM a inner Join SanPham b on a.SANPHAM_FK = b.PK_SEQ   "+  	 
				    " INNER JOIN DONVIDOLUONG DV ON DV.PK_SEQ = b.DVDL_FK  " +
				    " inner join  ERP_DONDATHANG c on a.dondathang_fk=c.pk_seq    "+
				    "where a.dondathang_fk in ( "+ chuoi +" ) and a.dondathang_fk in (select pk_seq from ERP_DONDATHANG where NPP_FK="+ this.nppId +")  " +
				    "group by b.PK_SEQ , b.MA, b.TEN, DV.donvi, a.dongia , isnull(scheme,' ') , isnull(a.thuevat,0) ";
			 
		    System.out.println("Lấy sản phẩm: "+query);
		    ResultSet rsLaySP = db.get(query);
		
		    
		    	String spID = "";
				String spMA = "";
				String spTEN = "";
				String spDONVI = "";
				String spSOLUONG = "";
				String spGIANHAP = "";
				String spCHIETKHAU = "";
				String spSCHEME = "";
				String spVAT = "";
				String spTIENTHUE = "";
			
		    	
			    if(rsLaySP!= null)
			    {				    	
					while(rsLaySP.next())
					{
						spID += rsLaySP.getString("SPID") + "__";
						spMA += rsLaySP.getString("MA") + "__";
						spTEN += rsLaySP.getString("TEN") + "__";
						spDONVI += rsLaySP.getString("DONVI") + "__";
						spSOLUONG += (rsLaySP.getDouble("SOLUONG")) + "__";
						spGIANHAP += (rsLaySP.getDouble("DONGIA")) + "__";
						spCHIETKHAU += (rsLaySP.getDouble("chietkhau")) + "__";
						spSCHEME += rsLaySP.getString("scheme") + "__";
						spVAT +=  (rsLaySP.getDouble("vat")) + "__";
						
						//spTIENTHUE +=  Math.round( ( Math.round( rsLaySP.getDouble("SOLUONG") * rsLaySP.getDouble("DONGIA") ) - rsLaySP.getDouble("chietkhau") ) * rsLaySP.getDouble("vat") / 100 ) + "__";
						spTIENTHUE +=  Math.round( Math.round( rsLaySP.getDouble("SOLUONG") * rsLaySP.getDouble("DONGIA") - rsLaySP.getDouble("chietkhau")) * ( rsLaySP.getDouble("vat") / 100 ) ) + "__";
					}
					rsLaySP.close();
					
					if(spMA.trim().length() > 0)
					{
						spID = spID.substring(0, spID.length() - 2);
						this.spId = spID.split("__");
						
						spMA = spMA.substring(0, spMA.length() - 2);
						this.spMa = spMA.split("__");
						
						spTEN = spTEN.substring(0, spTEN.length() - 2);
						this.spTen = spTEN.split("__");
						
						spDONVI = spDONVI.substring(0, spDONVI.length() - 2);
						this.spDonvi = spDONVI.split("__");
						
						spSOLUONG = spSOLUONG.substring(0, spSOLUONG.length() - 2);
						this.spSoluong = spSOLUONG.split("__");
						
						spGIANHAP = spGIANHAP.substring(0, spGIANHAP.length() - 2);
						this.spDongia = spGIANHAP.split("__");
						
						spCHIETKHAU = spCHIETKHAU.substring(0, spCHIETKHAU.length() - 2);
						this.spChietkhau = spCHIETKHAU.split("__");
						
						spSCHEME = spSCHEME.substring(0, spSCHEME.length() - 2);
						this.spSCheme = spSCHEME.split("__");
						
						spVAT = spVAT.substring(0, spVAT.length() - 2);
						this.spVat = spVAT.split("__");
						
						spTIENTHUE = spTIENTHUE.substring(0, spTIENTHUE.length() - 2);
						this.spTienthue = spTIENTHUE.split("__");
						
					}
			    }	
			} 
		  catch (SQLException e) 
		  {
				e.printStackTrace();
			}
		}
	
	}
	
	public String getTongtienBVAT() {
		
		return this.bvat;
	}

	
	public void setTongtienBVAT(String bvat) {
		
		this.bvat = bvat;
	}

	
	public String getTongCK() {
		
		return this.totalCHIETKHAU;
	}

	
	public void setTongCK(String tongCK) {
		
		this.totalCHIETKHAU = tongCK;
	}

	
	public String getTongVAT() {
		
		return this.thueVAT;
	}

	
	public void setTongVAT(String vat) {
		
		this.thueVAT = vat;
	}

	
	public String getTongtienAVAT() {
		
		return this.avat;
	}

	
	public void setTongtienAVAT(String avat) {
		
		this.avat = avat;
	}

	
	public String[] getSpTienthue() {

		return this.spTienthue;
	}


	public void setSpTienthua(String[] spTienthue) {
		
		this.spTienthue = spTienthue;
	}

	Hashtable<String, String> sanpham_soluong= new Hashtable<String, String>();
	
	public Hashtable<String, String> getSanpham_Soluong() {
		
		return this.sanpham_soluong;
	}

	
	public void setSanpham_Soluong(Hashtable<String, String> sp_soluong) {
		
		this.sanpham_soluong = sp_soluong;
	}
	
	public ResultSet getSoloTheoSp(String spMa, String donvi, String tongluong)
	{
		tongluong = tongluong.replaceAll(",", "");
		//System.out.println("---TONG LUONG: " + tongluong );
		
		String	query = "select AVAILABLE, NGAYHETHAN, SOLO, ngaynhapkho  " +
						"from ERP_KHOTT_SP_CHITIET ct inner join SANPHAM sp on ct.sanpham_fk = sp.pk_seq " +
						"where KHOTT_FK = '" + this.khoNhanId + "' and SANPHAM_FK = '" + spMa + "'" +
						"and ( AVAILABLE > 0 or( "+
						"	exists "+
						"	(( select hdsp.SOLUONG   "+
						"		 from erp_dondathang_sanpham_chitiet hdsp  "+
						"		 where hdsp.dondathang_fk ='"+this.ddhId+"'  "+
						"		 and hdsp.SOLO=ct.SOLO and ct.sanpham_fk=hdsp.sanpham_fk and hdsp.NGAYHETHAN=ct.NGAYHETHAN and hdsp.ngaynhapkho=ct.ngaynhapkho  "+
						"	))) )"+ 
						" and ct.ngaynhapkho <= '" + this.ngayxuatHD + "'    "+
						"order by NGAYHETHAN asc ";
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
					query2 += "select '" + avai + "' as AVAILABLE, '" + rs.getString("NGAYHETHAN") + "' as NGAYHETHAN,'"+rs.getString("ngaynhapkho")+"' AS   NGAYNHAPKHO , '" + rs.getString("SOLO") + "' as SOLO, '" + slg + "' as tuDEXUAT union ALL ";
				}
				else
				{
					query2 += "select '" + avai + "' as AVAILABLE, '" + rs.getString("NGAYHETHAN") + "' as NGAYHETHAN ,'"+rs.getString("ngaynhapkho")+"' AS NGAYNHAPKHO , '" + rs.getString("SOLO") + "' as SOLO, '' as tuDEXUAT union ALL ";
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
		
		return null;
	}
	
	String kbhId;
	ResultSet kbhRs;

	public String getKbhId() {
		return kbhId;
	}

	public void setKbhId(String kbhId) {
		this.kbhId = kbhId;
	}

	public ResultSet getKbhRs() {
		return kbhRs;
	}

	public void setKbhRs(ResultSet kbhRs) {
		this.kbhRs = kbhRs;
	}
	
	String dungchungKENH;

	public String getDungchungKENH() {
		return dungchungKENH;
	}

	public void setDungchungKENH(String dungchungKENH) {
		this.dungchungKENH = dungchungKENH;
	}
	
	public String getNOIBO() {

		return this.noibo;
	}


	public void setNOIBO(String noibo) {
		
		this.noibo = noibo;
	}
		
}
