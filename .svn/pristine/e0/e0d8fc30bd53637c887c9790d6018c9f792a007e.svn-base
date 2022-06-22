package geso.dms.distributor.beans.hoadontaichinh.imp;

import geso.dms.distributor.beans.hoadontaichinh.IHoadontaichinh;
import geso.dms.distributor.db.sql.dbutils;
import geso.dms.center.db.sql.Idbutils;
import geso.dms.center.util.Utility;

import java.sql.ResultSet;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Enumeration;
import java.util.Hashtable;

public class Hoadontaichinh implements IHoadontaichinh
{

	String userId;
	String id;

	String[] tichluy_scheme;
	String[] tichluy_tongtien;
	String[] tichluy_vat;

	String ngayxuatHD;
	String ngayghinhanCN;
	String ghichu;

	String kyhieuhoadon;
	String sohoadon;
	String hinhthuctt;
	String nguoimua;
	String innguoimua;

	String chietkhau;

	String loaidonhang;  //0 đơn đặt hàng, 1 đơn hàng khuyến mại ứng hàng, 3 đơn hàng khuyến mại tích lũy, 4 đơn hàng trưng bày, 5 đơn hàng khác

	String msg;
	String trangthai;
	String khoNhanId;
	ResultSet khoNhanRs;

	String nppId;
	ResultSet nppRs;

	String khId;
	ResultSet khRs;

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
	String[] spThue;

	String[] dhCk_diengiai;
	String[] dhCk_giatri;
	String[] dhCk_loai;

	String bvat;
	String totalCHIETKHAU;
	String thueVAT;
	String avat;
	String mavuviec;
	ResultSet rsspkm;
	public ResultSet getRsspkm() {
		return rsspkm;
	}

	public void setRsspkm(ResultSet rsspkm) {
		this.rsspkm = rsspkm;
	}

	public String getMavuviec() {
		return mavuviec;
	}

	public void setMavuviec(String mavuviec) {
		this.mavuviec = mavuviec;
	}


	Hashtable<String, String> sanpham_soluong;

	dbutils db;

	public Hoadontaichinh()
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
		this.chietkhau="";
		this.khId ="";
		this.msg = "";
		this.trangthai = "0";
		this.ddhId = "";

		this.innguoimua = "";

		this.loaidonhang = "0";
		this.nguoimua = "";

		this.bvat = "0";
		this.totalCHIETKHAU = "0";
		this.thueVAT = "0";
		this.avat = "0";
		this.mavuviec="";
		this.sanpham_soluong = new Hashtable<String, String>();
		this.db = new dbutils();
	}

	public Hoadontaichinh(String id)
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
		this.chietkhau="";
		this.msg = "";
		this.trangthai = "0";
		this.khId ="";
		this.ddhId = "";

		this.loaidonhang = "0";
		this.nguoimua= "";

		this.innguoimua = "";

		this.bvat = "0";
		this.totalCHIETKHAU = "0";
		this.thueVAT = "0";
		this.avat = "0";
		this.mavuviec="";
		this.sanpham_soluong = new Hashtable<String, String>();
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
	private void getNppInfo()
	{		
		//Phien ban moi
		geso.dms.distributor.util.Utility util=new geso.dms.distributor.util.Utility();
		this.nppId = util.getIdNhapp(this.userId);
	}

	public void createRs() 
	{
		this.getNppInfo();
		String query = "  select PK_SEQ, MAFAST + '-' + TEN as TEN" +
		"  from KHACHHANG" +
		"  where TRANGTHAI = '1' and PK_SEQ in ( select Khachhang_fk from KHACHHANG_NPP where   NPP_FK ="+this.nppId+" ) and KBH_FK <> 100052 " +
		"         and pk_seq in (select KHACHHANG_FK from DONHANG where trangthai in  (1,4) ) ";

		System.out.println("Khách hàng "+query);
		this.khRs = db.get(query);

		this.createChietkhau();
		if(this.khId.trim().length() > 0 )
		{			
			if(trangthai.equals("1") || trangthai.trim().length() <= 0 )
			{
				query = " select PK_SEQ , NgayNhap as NgayDonHang  " +
				" from DONHANG " +
				" where TRANGTHAI = '1' and NPP_FK= "+ this.nppId +" AND  KHACHHANG_FK = '" + this.khId + "' " +
				/* "       and pk_seq in (select donhang_fk " +
					  "                      from PHIEUXUATKHO_DONHANG a inner join PHIEUXUATKHO b on a.pxk_fk= b.pk_seq " +
					  "                      where b.trangthai=1 and b.NPP_FK= "+ this.nppId +" )  " +*/
				" and pk_seq not in (Select a.ddh_fk from HoaDon_ddh a inner join hoadon b on a.hoadon_fk=b.pk_seq where b.trangthai in ( 1, 2, 4) and b.loaihoadon = 0 and b.PK_SEQ != " + (  this.id.trim().length() <= 0 ? "1" : this.id ) + " ) " ;

				System.out.println("Lay don hang: " + query);		
				this.ddhRs = db.get(query);
			}
		}

		if(this.id.length() <= 0 )
		{
			// TỰ TẠO SỐ HÓA ĐƠN CỦA USER

			int kbDLN =0;
			String chuoiHD= "";
			long sohoadontu= 0;
			String sohoadonden= "";

			try
			{
				// Kiểm tra npp nếu là Đối tác thì không dùng Số hóa đơn của hệ thống
				query = " select loaiNPP " +
				" from NHAPHANPHOI " +
				" where pk_seq = '" + nppId + "' ";	
				ResultSet rsNpp = db.get(query);
				String loainpp= "";
				if(rsNpp!= null)
				{
					while(rsNpp.next())
					{
						loainpp = rsNpp.getString("loaiNPP");
					}
					rsNpp.close();
				}

				/*if(loainpp.equals("4") || loainpp.equals("5"))	// DOI TAC VA CHI NHANH CUA DOI TAC
				{
					this.kyhieuhoadon = "NA";
					this.sohoadon = "NA";
				}
				else*/
				{

					//KIEM TRA USER ĐÃ KHAI BAO SO HOA DON TRONG DLN CHUA
					query= " select count(pk_seq) as dem" +
					" from NHANVIEN" +
					" where pk_seq= '"+ this.userId+"' and  sohoadontu!= '' and sohoadonden != ''" +
					"       and isnull(kyhieu,'') != ''  ";
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
						this.msg = "Vui lòng khai báo Số hóa đơn trong (Thiết lập dữ liệu nền > Số hóa đơn) cho user này ";
					}
					else
					{
						// LAY KY HIEU HOA DON ,SOHDTU TRONG DLN
						query= " select kyhieu as kyhieuhoadon, sohoadontu, sohoadonden, isnull(ngayhoadon,'') as ngayhoadon " +
						" from NHANVIEN " +
						" where pk_seq= '"+ this.userId +"'";
						System.out.println("Cau .... "+query);
						ResultSet rsLayDL =  db.get(query);
						if(rsLayDL != null)
						{
							while(rsLayDL.next())
							{
								this.kyhieuhoadon= rsLayDL.getString("kyhieuhoadon");
								sohoadontu = rsLayDL.getLong("sohoadontu");
								sohoadonden = rsLayDL.getString("sohoadonden");
								this.ngayxuatHD = rsLayDL.getString("ngayhoadon");
							}
							rsLayDL.close();
						}


						// KIEM TRA SOHOADON DA DUOC DUNG CHUA : 
						//OTC
						query =" select count(pk_seq) as dem" +
						" from HOADON" +
						" where RTRIM(LTRIM(kyhieu)) = '"+ this.kyhieuhoadon +"' and cast((case when  sohoadon='NA' then sohoadon else 0 end) as numeric(18,0)) >= "+ sohoadontu +" and cast((case when  sohoadon='NA' then sohoadon else 0 end) as numeric(18,0))<= " + Integer.parseInt(sohoadonden) + " " +
						"      and trangthai != '3' and nguoisua= "+ this.userId +" and mauhoadon = 1 ";
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

						//ETC 

						query = " select count(pk_seq) as dem" +
						" from ERP_HOADONNPP" +
						" where RTRIM(LTRIM(kyhieu)) = '"+ this.kyhieuhoadon +"' and cast((case when  sohoadon='NA' then sohoadon else 0 end) as numeric(18,0)) >= "+ sohoadontu +" and cast((case when  sohoadon='NA' then sohoadon else 0 end) as numeric(18,0))<= " + Integer.parseInt(sohoadonden) + " " +
						"       and trangthai != '3' and nguoisua= "+ this.userId +" and mauhoadon = 1  ";

						System.out.println("Câu kiểm tra SHD "+query);
						ResultSet KiemTraETC = db.get(query);
						int check_ETC = 0;
						if(KiemTraETC != null)
						{
							while(KiemTraETC.next())
							{
								check_ETC = KiemTraETC.getInt("dem");
							}
							KiemTraETC.close();
						}

						// LAY SOIN MAX	
						if(check <= 0 && check_ETC <= 0)
						{
							chuoiHD = ("000000"+ sohoadontu);
							chuoiHD = chuoiHD.substring(chuoiHD.length() - 7, chuoiHD.length());
						}
						else
						{
							// LAY SOIN MAX TRONG HOADON : 
							//OTC
							query = " select  MAX(cast(SOHOADON as numeric)) as SOIN_MAX" +
							" from HOADON" +
							" where RTRIM(LTRIM(KYHIEU)) ='"+ this.kyhieuhoadon +"' and cast((case when  sohoadon='NA' then sohoadon else 0 end) as numeric(18,0)) >= "+ sohoadontu +" and cast((case when  sohoadon='NA' then sohoadon else 0 end) as numeric(18,0))<= " + Integer.parseInt(sohoadonden) + " " +
							"       and trangthai != 3 and nguoisua= "+ this.userId +" and mauhoadon = 1 ";
							System.out.println("Câu lấy shd max "+query);
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
							" where RTRIM(LTRIM(KYHIEU)) ='"+ this.kyhieuhoadon +"' and cast((case when  sohoadon='NA' then sohoadon else 0 end) as numeric(18,0)) >= "+ sohoadontu +" and cast((case when  sohoadon='NA' then sohoadon else 0 end) as numeric(18,0))<= " + Integer.parseInt(sohoadonden) + " " +
							"       and trangthai != '3'  and nguoisua= "+ this.userId +"  and mauhoadon = 1 ";
							System.out.println("Câu lấy shd max "+query);
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
				}	

			}catch(Exception e)
			{
				this.msg = "Lỗi xảy ra trong quá trình lấy Số hóa đơn";
				e.printStackTrace();
			}


			this.createChietkhau();

			if(this.khId.trim().length() > 0 )
			{			
				// LAY TEN NGUOI MUA TRONG DLN
				query =" select isnull(chucuahieu,'') as nguoimua from KHACHHANG where pk_seq= '"+ this.khId +"' ";
				ResultSet rsLayTT = db.get(query);
				if(rsLayTT!= null)
				{
					try
					{
						while(rsLayTT.next())
						{
							this.nguoimua = rsLayTT.getString("nguoimua");
						}
						rsLayTT.close();
					}catch (Exception e) { e.printStackTrace(); }
				}	

				query = 
					" select PK_SEQ , NgayNhap as NgayDonHang  " +
					" from DONHANG " +
					" where  trangthai not in (0,2) and   isnull(donhangkhac,0) = 0 and  NPP_FK= "+ this.nppId +" AND  KHACHHANG_FK = '" + this.khId + "' " +
					"         and ( import ='1' or pk_seq in (select donhang_fk " +
					"                      from PHIEUXUATKHO_DONHANG a inner join PHIEUXUATKHO b on a.pxk_fk= b.pk_seq " +
					"                      where b.trangthai=1 and b.NPP_FK= "+ this.nppId +" )  ) " +
					"         and pk_seq not in (Select a.ddh_fk from HoaDon_ddh a inner join hoadon b on a.hoadon_fk=b.pk_seq where b.trangthai in (1,2,4) and b.loaihoadon = 0 ) " ;
				System.out.println("Câu query "+query);		
				this.ddhRs = db.get(query);
			}

			String chuoi = this.ddhId;
			if(chuoi.length() > 0)
			{			
				// INIT SP	
				query = " select b.PK_SEQ as SPID, b.MA, b.TEN, DV.donvi, a.giamua as dongia ,' ' as scheme ,a.thuevat," +
				"        sum( a.soluong) as soluong, '0'  as chietkhau " +
				" from Donhang_SANPHAM a inner Join SanPham b on a.SANPHAM_FK = b.PK_SEQ    " +
				" 	   INNER JOIN DONVIDOLUONG DV ON DV.PK_SEQ = b.DVDL_FK       " +
				" where a.Donhang_FK in ( " + chuoi + " ) " +
				" group by  b.PK_SEQ , b.MA, b.TEN, DV.donvi, a.giamua  ,a.thuevat ";


				System.out.println("Lấy sản phẩm: "+query);
				ResultSet rsLaySP = db.get(query);
				NumberFormat formater = new DecimalFormat("##,###,###");
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
					String spTienThue = "";
					double tienthue= 0;

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
							spVAT += (rsLaySP.getDouble("thuevat")) + "__";
							tienthue = (Math.round(rsLaySP.getDouble("SOLUONG") * rsLaySP.getDouble("DONGIA")) - rsLaySP.getDouble("chietkhau"))*rsLaySP.getDouble("thuevat")/100;
							spTienThue += tienthue + "__";
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

							spTienThue = spTienThue.substring(0, spTienThue.length() - 2);
							this.spThue = spTienThue.split("__");


							System.out.println("Da vao day");
						}
					}

					query="select distinct 	b.PK_SEQ as SPID, b.MA AS MA,(b.TEN + N' (KM không thu tiền)') as ten,a.DONVI,a.SOLUONG \n"+  
					"	 from 	HOADON_CTKM_TRAKM a inner join SANPHAM b on a.SANPHAM_FK=b.PK_SEQ	\n"+
					"	 inner join HOADON_DDH dh on dh.HOADON_FK=a.hoadonID and dh.DDH_FK =   "+chuoi+" \n"+
					"	inner join hoadon hd on hd.PK_SEQ=dh.HOADON_FK \n"+
					"	 	left join CTKHUYENMAI c on a.CTKM = c.SCHEME \n"+
					"	 	where isnull(c.inchung,0)=1 and  hd.SOHOADON=	(select SOHOADON from HOADON where PK_SEQ= '"+ this.id+"')"+
					"\n	and ( "+
					"\n		 	  select count(b.PK_SEQ) as sl from HOADON_DDH a  "+
					"\n			 inner join HOADON b on a.HOADON_FK=b.PK_SEQ  "+
					"\n				 where b.LOAIHOADON=1 and a.DDH_FK="+chuoi+"  "+
					"\n			 group by LOAIHOADON "+
					"\n			 	 )=( "+
					"\n			 	  select count(b.PK_SEQ) as sl from HOADON_DDH a "+
					"\n				 inner join HOADON b on a.HOADON_FK=b.PK_SEQ "+
					"\n						 where b.LOAIHOADON=0 and a.DDH_FK="+chuoi+"  "+
					"\n					 group by LOAIHOADON "+
					"\n				 	 )";


					int donhang_sau_ngay_20 = this.CompareDATE(this.ngayxuatHD, "2015-09-21");
					if(donhang_sau_ngay_20>=0)
					{
						query="select distinct 	b.PK_SEQ as SPID, b.MA AS MA,(b.TEN + N' (KM không thu tiền)') as ten,a.DONVI,a.SOLUONG \n"+  
						"	 from 	HOADON_CTKM_TRAKM a inner join SANPHAM b on a.SANPHAM_FK=b.PK_SEQ	\n"+
						"	 inner join HOADON_DDH dh on dh.HOADON_FK=a.hoadonID and dh.DDH_FK =   "+chuoi+" \n"+
						"	inner join hoadon hd on hd.PK_SEQ=dh.HOADON_FK \n"+
						"	 	left join CTKHUYENMAI c on a.CTKM = c.SCHEME \n"+
						"	 	where   hd.SOHOADON=	(select SOHOADON from HOADON where PK_SEQ= '"+ this.id+"') and hd.hoadon_id="+this.id;


					}
					System.out.println("sp khuyen mai ii"+query);
					this.rsspkm=db.get(query);


				} 
				catch (Exception e) 
				{
					e.printStackTrace();
				}

				this.initTichLuy(chuoi);
				this.getTongTien(chuoi);

			}
		}
		else // ID > 0
		{
			String chuoi = this.ddhId;

			this.initTichLuy(chuoi);
			this.getTongTien(chuoi);

			// LAY SP TRONG HOADON
			query = " select b.PK_SEQ as SPID, b.MA, a.TEN, a.donvitinh, a.soluong, a.dongia, '0' as chietkhau, ' ' as scheme, a.vat  " +
			" from HOADON_SP a inner Join SanPham b on a.SANPHAM_FK = b.PK_SEQ    " +
			" where a.hoadon_fk = "+ this.id +"  " ;		

			System.out.println("Lấy sản phẩm: "+query);
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
				String spTienThue = "";
				double tienthue= 0;

				if(rsLaySP!= null)
				{				    	
					while(rsLaySP.next())
					{
						spID += rsLaySP.getString("SPID") + "__";
						spMA += rsLaySP.getString("MA") + "__";
						spTEN += rsLaySP.getString("TEN") + "__";
						spDONVI += rsLaySP.getString("donvitinh") + "__";
						spSOLUONG += (rsLaySP.getDouble("SOLUONG")) + "__";
						spGIANHAP += (rsLaySP.getDouble("DONGIA")) + "__";
						spCHIETKHAU += (rsLaySP.getDouble("chietkhau")) + "__";
						spSCHEME += rsLaySP.getString("scheme") + "__";
						spVAT += rsLaySP.getString("vat") + "__";
						tienthue = Math.round(( Math.round(rsLaySP.getDouble("SOLUONG") * rsLaySP.getDouble("DONGIA")) - rsLaySP.getDouble("chietkhau"))*rsLaySP.getDouble("vat")/100);
						spTienThue += tienthue + "__";
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

						spTienThue = spTienThue.substring(0, spTienThue.length() - 2);
						this.spThue = spTienThue.split("__");
					}
				}
			} 
			catch (Exception e) 
			{
				e.printStackTrace();
			}	

			query="select distinct 	b.PK_SEQ as SPID, b.MA AS MA,(b.TEN + N' (KM không thu tiền)') as ten,a.DONVI,a.SOLUONG \n"+  
			"	 from 	HOADON_CTKM_TRAKM a inner join SANPHAM b on a.SANPHAM_FK=b.PK_SEQ	\n"+
			"	 inner join HOADON_DDH dh on dh.HOADON_FK=a.hoadonID and dh.DDH_FK =   "+chuoi+" \n"+
			"	inner join hoadon hd on hd.PK_SEQ=dh.HOADON_FK \n"+
			"	 	left join CTKHUYENMAI c on a.CTKM = c.SCHEME \n"+
			"	 	where isnull(c.inchung,0)=1 and  hd.SOHOADON=	(select SOHOADON from HOADON where PK_SEQ= '"+ this.id+"')"+
			"\n	and ( "+
			"\n		 	  select count(b.PK_SEQ) as sl from HOADON_DDH a  "+
			"\n			 inner join HOADON b on a.HOADON_FK=b.PK_SEQ  "+
			"\n				 where b.LOAIHOADON=1 and a.DDH_FK="+chuoi+"  "+
			"\n			 group by LOAIHOADON "+
			"\n			 	 )=( "+
			"\n			 	  select count(b.PK_SEQ) as sl from HOADON_DDH a "+
			"\n				 inner join HOADON b on a.HOADON_FK=b.PK_SEQ "+
			"\n						 where b.LOAIHOADON=0 and a.DDH_FK="+chuoi+"  "+
			"\n					 group by LOAIHOADON "+
			"\n				 	 )";

			int donhang_sau_ngay_20 = this.CompareDATE(this.ngayxuatHD, "2015-09-21");
			if(donhang_sau_ngay_20>=0)
			{
				query="select distinct 	b.PK_SEQ as SPID, b.MA AS MA,(b.TEN + N' (KM không thu tiền)') as ten,a.DONVI,a.SOLUONG \n"+  
				"	 from 	HOADON_CTKM_TRAKM a inner join SANPHAM b on a.SANPHAM_FK=b.PK_SEQ	\n"+
				"	 inner join HOADON_DDH dh on dh.HOADON_FK=a.hoadonID and dh.DDH_FK =   "+chuoi+" \n"+
				"	inner join hoadon hd on hd.PK_SEQ=dh.HOADON_FK \n"+
				"	 	left join CTKHUYENMAI c on a.CTKM = c.SCHEME \n"+
				"	 	where   hd.SOHOADON=	(select SOHOADON from HOADON where PK_SEQ= '"+ this.id+"') and hd.hoadon_id="+this.id;


			}
			System.out.println("sp khuyen mai ii"+query);
			this.rsspkm=db.get(query);
		}

		try
		{
			//INIT SOLO
			/*query = "select sanpham_fk, solo, ngayhethan,NGAYNHAPKHO, sum(soluong) as soluong  " +
					"from PHIEUXUATKHO_SANPHAM_CHITIET where PXK_FK in ( select PXK_FK from PHIEUXUATKHO_DONHANG a inner join PHIEUXUATKHO b on a.pxk_fk = b.pk_seq where b.trangthai = '1' and donhang_fk in ( " + this.ddhId + " ) ) " +
					"group by sanpham_fk, solo, ngayhethan ,NGAYNHAPKHO ";*/

			query = "select sanpham_fk, solo, ngayhethan, NGAYNHAPKHO, sum(soluong) as soluong  " +
			"from DONHANG_SANPHAM_CHITIET where donhang_fk = '" + this.ddhId + "' " +
			"group by sanpham_fk, solo, ngayhethan, NGAYNHAPKHO ";

			System.out.println("---LO DA XUAT: " + query);
			ResultSet rsSOLO = db.get(query);
			this.sanpham_soluong = new Hashtable<String, String>();
			if(rsSOLO != null)
			{
				while(rsSOLO.next())
				{
					String key = rsSOLO.getString("sanpham_fk") + "__" + rsSOLO.getString("solo") + "__" + rsSOLO.getString("ngayhethan")+"__" + rsSOLO.getString("NGAYNHAPKHO");
					this.sanpham_soluong.put(key, rsSOLO.getString("soluong"));
				}
				rsSOLO.close();
			}
		}
		catch (Exception e) 
		{	
			e.printStackTrace();
		}

	}

	public void init() 
	{
		this.getNppInfo();
		String query =  " select dondathang_fk, ngayxuatHD, ISNULL(ghichu, '') as ghichu, kyhieu, sohoadon, hinhthuctt ,khachhang_fk, npp_fk, trangthai, isnull(nguoimua,'') as nguoimua, isnull(innguoimua,1) as innguoimua,  " +
		"        isnull(chietkhau,0) as chietkhau, isnull(tongtienbvat,0) as tongtienbvat, isnull(ngayghinhanCN, '"+ getDateTime() +"') as ngayghinhanCN , " +
		"       isnull(tongtienavat, 0) as tongtienavat,isnull(vat, 0) as vat, isnull(tongtienkm, 0) as tongtienkm,isnull(mavv,'') as mavv  " +
		" from HOADON a " +
		" where pk_seq = '" + this.id + "'";
		System.out.println("____INIT HOADON: 1111 " + query);
		ResultSet rs = db.get(query);
		if(rs != null)
		{
			try 
			{
				NumberFormat formatter = new DecimalFormat("#,###,###");
				if(rs.next())
				{
					this.ngayxuatHD = rs.getString("ngayxuatHD");
					this.ghichu = rs.getString("ghichu");
					this.hinhthuctt = rs.getString("hinhthuctt");
					this.kyhieuhoadon = rs.getString("kyhieu");
					this.ngayghinhanCN = rs.getString("ngayghinhanCN");
					this.sohoadon = rs.getString("sohoadon");
					this.khId = rs.getString("khachhang_fk");
					this.nppId = rs.getString("npp_fk");
					this.trangthai = rs.getString("trangthai");
					this.innguoimua = rs.getString("innguoimua");
					this.nguoimua = rs.getString("nguoimua");
					this.mavuviec=rs.getString("mavv");

					//this.bvat = formatter.format(rs.getDouble("tongtienbvat"));

					this.bvat = formatter.format(rs.getDouble("tongtienbvat"));
					this.totalCHIETKHAU = formatter.format(rs.getDouble("chietkhau"));
					this.thueVAT = formatter.format(rs.getDouble("vat"));
					this.avat = formatter.format(rs.getDouble("tongtienavat"));
					//this.mavuviec=rs.getString("mavv");

				}
				rs.close();

				//INIT DDH
				query = "SELECT DDH_FK FROM HOADON_DDH WHERE HOADON_FK = " + this.id;
				rs = this.db.get(query);

				String ddh = "";
				if(rs!=null)
				{
					while(rs.next())
					{
						ddh += rs.getString("DDH_FK") + ",";
					}
					rs.close();

					if(ddh.trim().length() > 0)
						this.ddhId = ddh.substring(0, ddh.length() - 1);
				}

				if(this.trangthai.equals("3") || this.trangthai.equals("5") )
				{
					query = " select B.PK_SEQ, B.NGAYNHAP AS NgayDonHang  " +
					" from HOADON_DDH A INNER JOIN DONHANG B ON A.DDH_FK = B.PK_SEQ " +
					" where A.HOADON_FK = '"+ this.id +"' AND A.HOADON_FK IN (SELECT PK_SEQ FROM HOADON WHERE LOAIHOADON= 0) ";

				}
				else
				{				
					query = "select PK_SEQ, NGAYNHAP AS NgayDonHang  " +
					"from DONHANG " +
					"where  trangthai not in (0,2 ) and   isnull(donhangkhac,0) = 0 and NPP_FK = " + this.nppId + " and  KHACHHANG_FK = '" + this.khId + "' " +
					/*"    and ( import= '1' or pk_seq in (select donhang_fk " +
							"                      from PHIEUXUATKHO_DONHANG a inner join PHIEUXUATKHO b on a.pxk_fk= b.pk_seq " +
							"					   where b.trangthai=1 and b.NPP_FK= "+ this.nppId +"))  " +*/
					" and pk_seq not in ( Select a.ddh_fk from HoaDon_ddh a inner join hoadon b on a.hoadon_fk = b.pk_seq where b.trangthai in ( 1, 2, 4 ) and b.loaihoadon = 0 and b.pk_seq != '" + this.id + "')  " ;
				}

				System.out.println("Lấy đơn hàng  " + query);		
				this.ddhRs = db.get(query);	
			} 
			catch (Exception e) 
			{
				System.out.println("---LOI INIT: " + e.getMessage());
			}
		}

		this.createRs();

	}

	private void createChietkhau()
	{
		if( ( this.chietkhau.equals("0") || this.chietkhau == "" ) && this.ddhId.trim().length() > 0 )
		{
			//String sql ="select ISNULL(b.chietkhau,0 ) as chietkhau from KHACHHANG a left join MUCCHIETKHAU b on a.CHIETKHAU_FK = b.pk_seq where a.PK_SEQ = '" + khId + "'";

			/*String sql = "select a.xuatkhau, ( select loaiNPP from NHAPHANPHOI where pk_seq = a.npp_fk ) as loaiNPP " +
						 "from khachhang a " +
						 "where a.trangthai = '1' and a.npp_fk = '" + nppId + "' and a.pk_seq = '" + this.khId + "' "; */

			String sql = "select isnull(chietkhau, 0) as chietkhau " +
			"from DONHANG a " +
			"where a.pk_seq in (  " + this.ddhId +" ) "; 

			ResultSet rs = db.get(sql);
			if(rs != null)
			{
				try 
				{
					if(rs.next())
					{
						/*String banbuon = rs.getString("xuatkhau");
				    	String loaiNPP = rs.getString("loaiNPP");

				    	if( loaiNPP.equals("4") || loaiNPP.equals("5") ) //Đối tác thì bán buôn, bán lẻ đều chiết khấu 5%
				    		this.chietkhau = "5";
				    	else
				    	{
				    		if( banbuon.equals("1") || banbuon.equals("2") )
				    			this.chietkhau = "5";
				    		else
				    			this.chietkhau = "3";
				    	}*/

						this.chietkhau = rs.getString("chietkhau");
					}
					rs.close();
				} 
				catch(Exception e) {}			
			}
		}

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
		this.getNppInfo();

		Utility util_kho=new Utility();

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

		if( this.ddhId.trim().length() <= 0 )
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
					if(Double.parseDouble(spSoluong[i].trim().replaceAll(",", "")) > 0)
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
			db.update("SET TRANSACTION ISOLATION LEVEL SNAPSHOT;");
			db.update("SET LOCK_TIMEOUT 60000;");
			db.getConnection().setAutoCommit(false);

			String query = "";
			String chuoi="";
			long sohoadontu=0;
			String sohoadonden="";
			int kbDLN=0;

			// Kiểm tra npp nếu là Đối tác thì không dùng Số hóa đơn của hệ thống
			query = " select loaiNPP, (  select PXK_FK from PHIEUXUATKHO_DONHANG a inner join PHIEUXUATKHO b on a.pxk_fk = b.pk_seq where b.trangthai = '1' and donhang_fk in ( " + this.ddhId + " ) ) as pxkID " +
			" from NHAPHANPHOI " +
			" where pk_seq = '" + nppId + "' ";	
			ResultSet rsNpp = db.get(query);
			String loainpp= "";
			String pxkID = "";
			if(rsNpp!= null)
			{
				while(rsNpp.next())
				{
					loainpp = rsNpp.getString("loaiNPP");
					pxkID = rsNpp.getString("pxkID");
				}
				rsNpp.close();
			}

			//if(!loainpp.equals("4") && !loainpp.equals("5"))	// KHONG PHAI LA DOI TAC VA CHI NHANH CUA DOI TAC
			{


				// LAY KY HIEU HOA DON ,SOHDTU TRONG DLN
				query= " select  sohoadontu, sohoadonden " +
				" from NHANVIEN where pk_seq= '"+ this.userId +"' and isnull(kyhieu, '')= '"+ this.kyhieuhoadon +"' ";
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
				if(sohoadontu == 0 || sohoadonden.trim().length() <= 0)
				{
					this.msg = " Ký hiệu "+ this.kyhieuhoadon +" không giống với ký hiệu khai báo trong dữ liệu nền/ Chưa khai báo số hóa đơn trong dữ liệu nền (Số hóa đơn) ";
					db.getConnection().rollback();
					return false;
				}

				// Check So hoa don sua da co dung chua
				// OTC
				query= " select  count(pk_seq) as kiemtra " +
				" from HOADON " +
				" where isnull(inChung1,0)=0 and nguoisua= '"+ this.userId +"' and RTRIM(LTRIM(sohoadon)) = '"+ this.sohoadon.trim() +"' and pk_seq != "+ this.id +" " +
				"       and trangthai != 3 and mauhoadon = 1 and RTRIM(LTRIM(isnull(kyhieu, ''))) = RTRIM(LTRIM('"+ this.kyhieuhoadon +"')) ";
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
				" from ERP_HOADONNPP " +
				" where nguoisua= '"+ this.userId +"' and RTRIM(LTRIM(sohoadon)) = '"+ this.sohoadon.trim() +"' and pk_seq != "+ this.id +" " +
				"        and trangthai != 3 and mauhoadon = 1 and RTRIM(LTRIM(isnull(kyhieu, ''))) = RTRIM(LTRIM('"+ this.kyhieuhoadon +"')) ";
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


				if(Integer.parseInt(sohoadonden.trim()) <  Integer.parseInt(this.sohoadon.trim()))
				{
					this.msg = "Số hóa đơn này đã vượt quá số hóa đơn đến trong dữ liệu nền. Vui lòng thiết lập lại số hóa đơn ";
					db.getConnection().rollback();
					return false;
				}
				else if (ktra > 0 || ktra_ETC > 0 )
				{
					this.msg = "Số hóa đơn này đã được sử dụng. Vui lòng đánh số hóa đơn khác. ";
					db.getConnection().rollback();
					return false;
				}
				else if (this.sohoadon.trim().length() != 7 )
				{
					this.msg = "Số hóa đơn phải đủ 7 ký tự. Vui lòng kiểm tra lại.";
					db.getConnection().rollback();
					return false;
				}
			
			}

			Object[] data = null;
			data = dbutils.setObject(this.ngayxuatHD,this.userId,this.userId,this.kyhieuhoadon,this.sohoadon,this.hinhthuctt
					,this.ghichu,this.nppId,this.mavuviec,this.ddhId);

			query=  " insert HOADON(KBH_FK, DDKD_FK, GSBH_fK, KHO_FK, khachhang_fk, ngayxuatHD, trangthai, ngaytao, nguoitao, ngaysua, nguoisua, "+
			"               kyhieu, sohoadon, hinhthuctt , chietkhau, tongtienbvat, tongtienavat, vat, tongtienkm, ghichu, npp_fk, " +
			" 		        loaihoadon, nguoimua, innguoimua, mauhoadon,TENKHACHHANG,DIACHI,MASOTHUE,mavv,tiengiamtru) " +
			" SELECT DH.KBH_FK, DH.DDKD_FK, DH.GSBH_fK, DH.KHO_FK, DH.KHACHHANG_FK , ?, '1','" + getDateTime() + "', ?, '" + getDateTime() + "', ?, " +
			"        ?, ?, ? , '0', '0', '0', '0', '0', ?, ? , " +
			"         '0', ISNULL(KH.CHUCUAHIEU,'')  ,( CASE WHEN DH.NPP_FK = 106210  THEN 0 ELSE 1 END) AS INNGUOIMUA , 1 AS MAUHD " +
			"  ,KH.TEN as tenkh , ISNULL(KH.DIACHI,'') as diachikh ,ISNULL(KH.MASOTHUE,'') as mst,?,isnull(DH.tiengiamtru,0)  "+
			" FROM  DONHANG DH  INNER JOIN  KHACHHANG KH ON DH.KHACHHANG_FK = KH.PK_SEQ     \n"+
			" WHERE DH.PK_SEQ =?  \n";

			if ( db.update_v2(query, data) < 0)
			{
				this.msg = "Không thể tạo mới HOADON ";
				db.getConnection().rollback();
				return false;
			}

			String hdId = "";
			query = "select SCOPE_IDENTITY() as hdId";
			ResultSet rs1 = db.get(query);
			rs1.next();
			hdId = rs1.getString("hdId");
			this.id=hdId;
			rs1.close();

			/*query = " Update PHIEUXUATKHO set NGAYLAPPHIEU = '" + this.ngayxuatHD + "' " +
					" where PK_SEQ in ( select pxk_fk from PHIEUXUATKHO_DONHANG where donhang_fk in ( "+this.ddhId+" ) )  ";
			System.out.println("1372---3.HUY HOA DON: " + query );
			if(!db.update(query))
			{
				this.msg = "Không thể tạo mới HOADON " + query;
				db.getConnection().rollback();
				return false;
			}*/
			data = null;
			data = dbutils.setObject(hdId,this.ddhId);
			
			query = "Insert HOADON_DDH(hoadon_fk, ddh_fk) select ? , pk_seq from DONHANG where pk_seq in ( ? ) ";
			if ( db.update_v2(query, data) != 1)
			{
				this.msg = "Không thể tạo mới HOADON_DDH ";
				db.getConnection().rollback();
				return false;
			}

			for(int i = 0; i < spId.length; i++)
			{
				if(spId[i].trim().length() > 0 && Double.parseDouble(spSoluong[i].trim().replaceAll(",", "")) > 0 )
				{
					data = null;
					data = dbutils.setObject(hdId,spId[i],spDonvi[i],spSoluong[i].replaceAll(",", ""),spDongia[i].replaceAll(",", "")
							,spSoluong[i].replaceAll(",", ""),spDongia[i].replaceAll(",", ""),spChietkhau[i].replaceAll(",", "")
							,spSCheme[i].replaceAll(",", ""),spVat[i].replaceAll(",", ""),this.nppId,spId[i],this.ddhId);
					query = "\n insert HOADON_SP( hoadon_fk, sanpham_fk, donvitinh, soluong, dongia, thanhtien, chietkhau, scheme, vat, dongiaGOC ) " +
					"\n select ?, ?, ?, ?, ?," +
					"\n (? * ?), ?, ?, ?, " +
					"\n	[dbo].[GiaCkBanLeNppSanPham](?,dh.khachhang_fk, ?,dh.ngaynhap ) 	 as dongiaGOC " +
					"\n from donhang dh where pk_seq = ?  " ;

					if ( db.update_v2(query, data) < 0)
					{
						this.msg = "Khong the tao moi ERP_HOADON_SP ";
						db.getConnection().rollback();
						return false;
					}
				}
			}			

			query="SELECT KBH_FK, kho_fk FROM DONHANG WHERE PK_SEQ = " + ddhId;
			ResultSet rsgetkenh=db.get(query);
			String kbh_fk="";
			String kho_fk="";
			if(rsgetkenh.next())
			{
				kbh_fk = rsgetkenh.getString("KBH_FK");
				kho_fk = rsgetkenh.getString("kho_fk");
			}
			rsgetkenh.close();

			/*query=  " select b.kho_fk, b.kbh_fk, a.NPP_FK, b.SANPHAM_FK, b.SOLO, SUM(b.soluong) as soluong,b.NgayHetHan,isnull(b.ngaynhapkho,'') as ngaynhapkho "+
					" from PHIEUXUATKHO a inner join PHIEUXUATKHO_SANPHAM_CHITIET b on a.PK_SEQ = b.PXK_FK  "+
					" where a.PK_SEQ = "+pxkID+
					" group by b.kho_fk, b.kbh_fk, a.NPP_FK, b.SANPHAM_FK, b.SOLO,b.NgayHetHan  ,b.ngaynhapkho";*/
			query = "  select b.kho_fk, b.kbh_fk, a.NPP_FK, b.SANPHAM_FK, b.SOLO, SUM(b.soluong) as soluong, b.NgayHetHan,isnull(b.ngaynhapkho,'') as ngaynhapkho  " + 
			"  from DONHANG_SANPHAM_CHITIET b inner join DONHANG a on b.donhang_fk = a.PK_SEQ " + 
			"  where DONHANG_FK = '" + this.ddhId + "' " + 
			"  group by b.kho_fk, b.kbh_fk, a.NPP_FK, b.SANPHAM_FK, b.SOLO, b.NgayHetHan, isnull(b.ngaynhapkho,'') ";

			ResultSet rskho=db.get(query);
			while(rskho.next())
			{
				String _khoid=rskho.getString("kho_fk");
				String _kbh_fk=rskho.getString("kbh_fk");
				String _NPP_FK=rskho.getString("NPP_FK");
				String _SANPHAM_FK=rskho.getString("SANPHAM_FK");
				String _SOLO=rskho.getString("SOLO");
				String _NgayHetHan=rskho.getString("NgayHetHan");
				String _ngaynhapkho=rskho.getString("ngaynhapkho");
				double soluongct=rskho.getDouble("SOLUONG");
				String msg1=util_kho.Update_NPP_Kho_Sp_Chitiet(this.ngayxuatHD, "Tạo mới hóa đơn: hoadontaichinh.java 1543 ", db, _khoid, _SANPHAM_FK, _NPP_FK, _kbh_fk, _SOLO, _NgayHetHan, _ngaynhapkho, 0, -1 * soluongct, soluongct, soluongct, 0);

				if(msg1.length() >0)
				{
					db.getConnection().rollback();
					this.msg=msg1;
					return false;
				}
			}

			//CHEN LAI DH TRONG TRUONG HOP DOI SOLO
			for(int i = 0; i < spId.length; i++)
			{
				if(spId[i].trim().length() > 0 && Double.parseDouble(spSoluong[i].trim().replaceAll(",", "")) > 0 )
				{
					if(this.sanpham_soluong.size() > 0)
					{
						Enumeration<String> keys = this.sanpham_soluong.keys();
						double totalCT = 0;

						while(keys.hasMoreElements())
						{
							String key = keys.nextElement();
							System.out.println("----KEY: " + key + "  -- ID: " + spId[i]);

							if(key.startsWith( spId[i]) )
							{
								String[] _sp = key.split("__");

								String _soluongCT = "0"; 
								if(this.sanpham_soluong.get(key) != null)
								{
									_soluongCT = this.sanpham_soluong.get(key).replaceAll(",", "");
								}

								totalCT += Double.parseDouble(_soluongCT);

								//CHECK TỒN KHO CỦA LÔ MUỐN ĐỔI CÓ ĐỦ KHÔNG
								query = " select AVAILABLE from NHAPP_KHO_CHITIET " +
								" where NPP_FK = '" + this.nppId + "'  " +
								" and kho_fk = ( select kho_fk from DONHANG where pk_seq in (" + this.ddhId + ") ) " +
								" and kbh_fk = '100025' and sanpham_fk = '" + _sp[0] + "' and SOLO = '" + _sp[1] + "' and ngayhethan = '" + _sp[2] + "' and ngaynhapkho='"+_sp[3]+"' ";
								System.out.println("1.1.Check TONKHO: " + query);
								ResultSet rsCHECK_TK = db.get(query);
								double avai = 0;
								if(rsCHECK_TK.next())
								{
									avai = rsCHECK_TK.getDouble("AVAILABLE");
								}
								rsCHECK_TK.close();

								if(avai < Double.parseDouble(_soluongCT) )
								{
									db.getConnection().rollback();
									this.msg = "Sản phẩm ( " + spTen[i] + " ) với số lô ( " + _sp[1] + " ), ngày hết hạn ( " + _sp[2] + " ), ngày nhập kho ="+_sp[3]+" còn tối đa ( " + avai + " ) ";
									return false;
								}
								
								data = null;
								data = dbutils.setObject(ddhId,_sp[0],_soluongCT,_sp[1],_sp[2]
										,kho_fk,_sp[3]);

								query = " insert DONHANG_SANPHAM_CHITIET(DONHANG_FK, SANPHAM_FK, SOLUONG, SOLO, NGAYHETHAN, KBH_FK, KHO_FK, NGAYNHAPKHO)" +
								" select ?, ?, ?, ?, ? " +
								" , '100025' as kbh_fk, ? as kho_fk ,?  ";

								System.out.println("1.2.Insert DONHANG_SANPHAM_CHITIET: " + query);
								if(db.updateReturnInt(query)<0)
								{
									this.msg = "Khong the tao moi DONHANG_SANPHAM_CHITIET: " + query;
									db.getConnection().rollback();
									return false;
								}

								double soluongct = Double.parseDouble(_soluongCT);
								String _spid=_sp[0];
								String _solo=_sp[1];
								String _ngayhethan=_sp[2];
								String _ngaynhapkho=_sp[3];
								String msg1=util_kho.Update_NPP_Kho_Sp_Chitiet(this.ngayxuatHD, "Cập nhâth hóa đơn: hoadontaichinh.java 1606 ", db, kho_fk, _spid, this.nppId, kbh_fk, _solo, _ngayhethan, _ngaynhapkho, 0, soluongct, (-1)*soluongct, (-1)* soluongct, 0);

								if(msg1.length() >0){
									db.getConnection().rollback();
									this.msg=msg1;
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

			query = "delete HOADON_SP_CHITIET where hoadon_fk="+this.id;
			System.out.println("---CHAY HOA DON CHI TIET: " + this.id );
			if(db.updateReturnInt(query) <= 0  )
			{
				msg = "Khong the cap nhat HOADON_SP_CHITIET: " + query;
				db.getConnection().rollback();
				return false;
			}

			query = " insert HOADON_SP_CHITIET( HOADON_FK, MA, TEN, DONVI, DONGIA, DONGIAGOC, SOLO, NGAYHETHAN, THUEVAT, SOLUONG, CHIETKHAU, KHO_FK, isnhapkhau, ngaynhapkho )  " +
			" SELECT '" + this.id + "' as HOADON_FK, c.MA, c.TEN, d.DONVI, dhsp.GIAMUA AS DONGIA, dhsp.DONGIAGOC,   " +
			"		f.SOLO,   " +
			"		f.NGAYHETHAN, dhsp.thuevat,     " +
			"	    SUM( f.soluong) as soluong, '0' as chietkhau, dhsp.Kho_Fk, dhsp.isnhapkhau, f.ngaynhapkho " +
			" FROM  DONHANG dh INNER JOIN DONHANG_SANPHAM dhsp on dhsp.DONHANG_FK = dh.PK_SEQ    " +
			"				   INNER JOIN SANPHAM c on dhsp.SANPHAM_FK = c.PK_SEQ                 " +
			"				   LEFT JOIN DONVIDOLUONG d on d.PK_SEQ = c.DVDL_FK   " +
			"				   INNER JOIN DONHANG_SANPHAM_CHITIET f on dh.pk_seq = f.donhang_fk and c.PK_SEQ = f.SANPHAM_FK  " +
			" WHERE dh.PK_SEQ = '" + this.ddhId + "'  and  dhsp.SOLUONG > 0 " +
			" GROUP BY  c.MA, c.TEN, d.DONVI, dhsp.GIAMUA, dhsp.DONGIAGOC, dhsp.THUEVAT, f.SOLO, f.NGAYHETHAN, dhsp.KHO_FK,dhsp.isnhapkhau,f.ngaynhapkho  ";

			System.out.println("---CHAY HOA DON CHI TIET: " + this.id );
			if(db.updateReturnInt(query) <= 0  )
			{
				msg = "Khong the cap nhat HOADON_SP_CHITIET: " + query;
				db.getConnection().rollback();
				return false;
			}

			//LUU LAI THONG TIN CHIET KHAU
			int donhang_sau_ngay_01 = this.CompareDATE(this.ngayxuatHD, "2014-10-01");
			String hienthi = "0";
			if(donhang_sau_ngay_01 < 0)
				hienthi = "1";

			query = "insert HOADON_CHIETKHAU(hoadon_fk, donhang_fk, diengiai, chietkhau, thueVAT, STT, tichluyQUY, HIENTHI) " +
			"	select '" + this.id + "', donhang_fk, N'CN5' as diengiai, sum(chietkhau) as chietkhau, thueVAT, 1 as STT, 0 as tichluyQUY, '" + hienthi + "' as HIENTHI  " +
			"	from DONHANG_SANPHAM  " +
			"	where thueVAT = '5' and donhang_fk in ( select DDH_FK from HOADON_DDH where HOADON_FK = '" + this.id + "' ) and chietkhau > 0 " +
			"	group by donhang_fk, thueVAT " +
			"	 " +
			"union   " +
			"	select '" + this.id + "', donhang_fk, N'CN10' as diengiai, sum(chietkhau) as chietkhau, thueVAT, 2 as STT, 0 as tichluyQUY, '" + hienthi + "' as HIENTHI   " +
			"	from DONHANG_SANPHAM   " +
			"	where thueVAT = '10' and donhang_fk in ( select DDH_FK from HOADON_DDH where HOADON_FK = '" + this.id + "' ) and chietkhau > 0 " +
			"	group by donhang_fk, thueVAT " +
			"union  " +
			"	select '" + this.id + "', donhang_fk, a.diengiai, a.thanhtoan / ( 1 + ptTHUE / 100 ) as chietkhau, a.ptTHUE as thueVAT, 3 as STT, tichluyQUY, HIENTHI   " +
			"	from DUYETTRAKHUYENMAI_DONHANG a inner join TIEUCHITHUONGTL b on a.duyetkm_fk = b.PK_SEQ   " +
			"	where a.thanhtoan > 0 and donhang_fk in ( select DDH_FK from HOADON_DDH where HOADON_FK = '" + this.id + "' )  " +
			"union  " +
			"	select '" + this.id + "', donhang_fk, a.maloai as diengiai, a.chietkhau, a.ptVAT as thueVAT, 4 as STT, 0 as tichluyQUY, 1 as HIENTHI   " +
			"	from DONHANG_CHIETKHAUBOSUNG a   " +
			"	where a.chietkhau > 0 and donhang_fk in ( select DDH_FK from HOADON_DDH where HOADON_FK = '" + this.id + "' )  ";
			if( !db.update(query)  )
			{
				msg = "Khong the cap nhat HOADON_CHIETKHAU: " + query;
				db.getConnection().rollback();
				return false;
			}

			String kqHd = geso.dms.distributor.util.Utility.Update_GiaTri_HoaDon( this.id,  db);
			if( kqHd.trim().length() > 0 )
			{
				msg = "Khong the cap nhat HOADON: " + kqHd;
				db.getConnection().rollback();
				return false;
			}

			Utility util = new Utility();
			msg= util.Check_Huy_NghiepVu_KhoaSo("HoaDon", id, "NgayXuatHD", db);
			if(msg.length()>0)
			{
				db.getConnection().rollback();
				return false;
			}

			msg= util.Check_Kho_Tong_VS_KhoCT(this.nppId, db);
			if(msg.length()>0)
			{
				db.getConnection().rollback();
				return false;
			}

			this.msg = "Số hóa đơn bạn vừa lưu: " + this.sohoadon;
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

		Object[] data = null;
		Utility util_kho=new Utility();
		this.getNppInfo();

		//NEU HOA DON DA HUY THI CHI DUOC SUA KY HIEU VA SO HOA DON
		String sqlCHECK = "select trangthai from HOADON where pk_seq = '" + this.id + "'";
		ResultSet rsCHECK = db.get(sqlCHECK);
		String trangthai = "";
		try 
		{
			if(rsCHECK.next())
			{
				trangthai = rsCHECK.getString("trangthai");
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
			if(this.ddhId.trim().length() <= 0)
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
			db.update("SET TRANSACTION ISOLATION LEVEL SNAPSHOT;");
			db.update("SET LOCK_TIMEOUT 60000;");
			db.getConnection().setAutoCommit(false);
			String query ="";

			// Kiểm tra npp nếu là Đối tác thì không dùng Số hóa đơn của hệ thống
			data = null;
			data = dbutils.setObject(this.ddhId,nppId);
			query = " select loaiNPP, (  select PXK_FK from PHIEUXUATKHO_DONHANG a " +
			" inner join PHIEUXUATKHO b on a.pxk_fk = b.pk_seq where b.trangthai = '1' and donhang_fk in ( ? ) ) as pxkID " +
			" from NHAPHANPHOI " +
			" where pk_seq = ? ";	
			ResultSet rsNpp = db.get_v2(query, data);
			String loainpp= "";
			String pxkID = "";

			while(rsNpp.next())
			{
				loainpp = rsNpp.getString("loaiNPP");
				pxkID = rsNpp.getString("pxkID");
			}
			rsNpp.close();


			//if(!loainpp.equals("4") && !loainpp.equals("5"))	// KHONG PHAI LA DOI TAC VA CHI NHANH CUA DOI TAC
			if(!this.sohoadon.equals("NA"))
			{
				// LAY KY HIEU HOA DON ,SOHDTU TRONG DLN
				String chuoi="";
				long sohoadontu=0;
				String sohoadonden="";
				int kbDLN=0;



				/* query= " select  sohoadontu, sohoadonden " +
						" from NHANVIEN " +
						" where pk_seq= '"+ this.userId +"' and " +
						"       isnull(kyhieu,'') = '"+ this.kyhieuhoadon +"' ";

				ResultSet rsLayDL =  db.get(query);
				{
					while(rsLayDL.next())
					{
						sohoadontu = rsLayDL.getLong("sohoadontu");
						sohoadonden = rsLayDL.getString("sohoadonden");
					}
					rsLayDL.close();
				}
				if(sohoadontu == 0 || sohoadonden.trim().length() <= 0)
				{
					this.msg = " Ký hiệu "+ this.kyhieuhoadon +" không giống với ký hiệu khai báo trong dữ liệu nền/ Chưa khai báo số hóa đơn trong dữ liệu nền (Số hóa đơn) ";
					db.getConnection().rollback();
					return false;
				}*/

				// Check So hoa don sua da co dung chua
				// OTC
				data = null;
				data = dbutils.setObject(this.userId,this.sohoadon.trim(),this.id,this.kyhieuhoadon);
				query= " select  count(pk_seq) as kiemtra " +
				" from HOADON " +
				" where nguoisua= ? and RTRIM(LTRIM(sohoadon)) = ? and pk_seq != ? " +
				"        and mauhoadon = 1 and trangthai != 3 and RTRIM(LTRIM(isnull(kyhieu, ''))) = RTRIM(LTRIM(?)) and isnull(inchung1,0)=0 ";
				
				ResultSet KtraSHD =  db.get_v2(query, data);
				int ktra= 0;

				if(KtraSHD != null)
				{
					while(KtraSHD.next())
					{
						ktra = KtraSHD.getInt("kiemtra");
					}
					KtraSHD.close();
				}
				
				data = null;
				data = dbutils.setObject(this.userId,this.sohoadon.trim(),this.id,this.kyhieuhoadon);
				// ETC
				query= " select  count(pk_seq) as kiemtra " +
				" from ERP_HOADONNPP " +
				" where nguoisua= ? and trangthai != 3 and RTRIM(LTRIM(sohoadon)) = ? and pk_seq != ? " +
				"       and mauhoadon = 1  and RTRIM(LTRIM(isnull(kyhieu, ''))) = RTRIM(LTRIM(?)) ";
				System.out.println("Cau kiem tra so hoa don "+query);
				ResultSet KtraSHD_ETC = db.get_v2(query, data);
				int ktra_ETC= 0;

				if(KtraSHD_ETC != null)
				{
					while(KtraSHD_ETC.next())
					{
						ktra_ETC = KtraSHD_ETC.getInt("kiemtra");
					}
					KtraSHD_ETC.close();
				}


				//	if(Integer.parseInt(sohoadonden.trim()) <  Integer.parseInt(this.sohoadon.trim()))
				if(1==2)
				{
					this.msg = "Số hóa đơn này đã vượt quá số hóa đơn đến trong dữ liệu nền. Vui lòng thiết lập lại số hóa đơn ";
					db.getConnection().rollback();
					return false;
				}
				else if (ktra > 0 || ktra_ETC > 0 )
				{
					this.msg = "Số hóa đơn này đã được sử dụng. Vui lòng đánh số hóa đơn khác. ";
					db.getConnection().rollback();
					return false;
				}
				else if (this.sohoadon.trim().length() != 7 )
				{
					this.msg = "Số hóa đơn phải đủ 7 ký tự. Vui lòng kiểm tra lại.";
					db.getConnection().rollback();
					return false;
				}

			}

			// neu don in chung thi doi lun hd km


			/*if(CompareDATE("2015-10-08", "2015-10-09")>0)
			{*/
			
			query="	select ( select case when count(*)>0 then 0 else  1 end  from DONHANG_CTKM_TRAKM where donhangID = (select a.DDH_FK from HOADON_DDH a where a.HOADON_FK='"+this.id+"') and spMA is not null "+
			" and ctkmId in ( select pk_seq from CTKHUYENMAI where kho_fk = '100000'  and isnull(inchung,0)=1  and PK_SEQ=CTKMID)  ) as coKHOHB";

			/*}*/
			/*else
			{
				query="	select ( select count(*) from DONHANG_CTKM_TRAKM where donhangID = (select a.DDH_FK from HOADON_DDH a where a.HOADON_FK='"+this.id+"') and spMA is not null "+
						" and ctkmId in ( select pk_seq from CTKHUYENMAI where kho_fk = '100000'  and isnull(inchung,0)=0) ) as coKHOHB";

			}*/
			System.out.println("cau kiem tra co in chung ko "+query);
			int flag_inchung=0;
			/*ResultSet Rsco_inchung=db.get(query);
			if(Rsco_inchung.next())
				flag_inchung=Rsco_inchung.getInt("coKHOHB");
			 */
			if(flag_inchung==0)
			{
				data = null;
				data = dbutils.setObject(this.id);
				query="select a.DDH_FK,b.PK_SEQ,b.LOAIHOADON from HOADON_DDH a inner join HOADON b on a.HOADON_FK=b.PK_SEQ  \n"+
				"	where b.trangthai not in (3,5) and a.DDH_FK=(select a.DDH_FK from HOADON_DDH a where a.HOADON_FK=?) \n"+ 
				"	and b.LOAIHOADON=1 \n";

				ResultSet rskm=db.get_v2(query, data);
				if(rskm.next())
				{
					data = null;
					data = dbutils.setObject(this.id,this.ngayxuatHD,this.sohoadon);
					query = "Update HOADON set ngayxuathd=?,sohoadon = RTRIM(LTRIM(?)), kyhieu = RTRIM(LTRIM(?)) where pk_seq = '"+rskm.getString("pk_seq")+"' ";
					System.out.println("1.Update HOADON: " + query);
					if ( db.update_v2(query, data) <0)
					{
						this.msg = "Lỗi khi cập nhật hóa đơnz: ";
						db.getConnection().rollback();
						return false;
					}
				}
				rskm.close();
			}
			

			if( !trangthai.equals("1") )
			{
				data = null;
				data = dbutils.setObject(this.sohoadon,this.kyhieuhoadon,this.id);

				query = "Update HOADON set sohoadon = RTRIM(LTRIM(?)), kyhieu = RTRIM(LTRIM(?)) where pk_seq = ? ";
				System.out.println("1.Update HOADON: " + query);
				if(!db.update(query))
				{
					this.msg = "Lỗi khi cập nhật hóa đơn: " + query;
					db.getConnection().rollback();
					return false;
				}
				Utility util = new Utility();
				msg= util.Check_Huy_NghiepVu_KhoaSo("HOADON", id, "ngayxuatHD", db);
				if(msg.length()>0)
				{
					db.getConnection().rollback();
					return false;
				}

				//Đã chốt, đã xóa, đã hủy, đã in thì khi thay đổi số hóa đơn phải lưu lại thay đổi đó trong LOG

				db.getConnection().commit();
				return true;				

			}
			else
			{

				String ngayxuat="";
				query="select ngayxuatHD  from hoadon where pk_seq="+this.id;
				ResultSet rsngay=db.get(query);
				rsngay.next();
				geso.dms.distributor.util.Utility utility=new geso.dms.distributor.util.Utility();
				if(utility.CompareDATE(rsngay.getString("ngayxuatHD"), this.ngayxuatHD)>=0)
				{
					ngayxuat=this.ngayxuatHD;
				}
				else
				{
					ngayxuat=rsngay.getString("ngayxuatHD");
				}
				
				data = null;
				data = dbutils.setObject(this.ngayghinhanCN,this.khId,ngayxuat,this.userId,this.kyhieuhoadon,this.sohoadon
						,this.hinhthuctt,this.ghichu,this.nppId,this.nguoimua,this.innguoimua,this.mavuviec,this.id);

				query =    " update HOADON set ngayghinhanCN =  ?, khachhang_fk= ? , ngayxuatHD = ? , ngaysua = '" + getDateTime() + "' , nguoisua =?," +
				" kyhieu = RTRIM(LTRIM(?)) , sohoadon= RTRIM(LTRIM(?)), hinhthuctt= ? ," +
				" ghichu= ?, NPP_FK= ? , loaihoadon = '0', nguoimua = ?, innguoimua = ?, mavv=?  " +
				" where pk_seq = ?" ;
				if ( db.update_v2(query, data) != 1)
				{
					this.msg = "Không thể cập nhật HOADON " ;
					db.getConnection().rollback();
					return false;
				}
				Utility util = new Utility();
				msg= util.Check_Huy_NghiepVu_KhoaSo("HOADON", id, "ngayxuatHD", db);
				if(msg.length()>0)
				{
					db.getConnection().rollback();
					return false;
				}
				
				data = null;
				data = dbutils.setObject(this.id);
				
				query = " delete from HOADON_SP where hoadon_fk = ? " ;
				if ( db.update_v2(query, data) < 0)
				{
					this.msg = "Không thể xóa HOADON_SP " ;
					db.getConnection().rollback();
					return false;
				}

				data = null;
				data = dbutils.setObject(this.id);
				query = " delete from HOADON_DDH where hoadon_fk = ? " ;
				if ( db.update_v2(query, data) < 0)
				{
					this.msg = "Không thể xóa HOADON_DDH ";
					db.getConnection().rollback();
					return false;
				}

				
				data = null;
				data = dbutils.setObject(this.id,this.ddhId);
				query = "Insert HOADON_DDH(hoadon_fk, ddh_fk) select ? ,  pk_seq from DONHANG where pk_seq in ( ? ) ";
				if ( db.update_v2(query, data) < 0)
				{
					this.msg = "Không thể tạo mới HOADON_DDH ";
					db.getConnection().rollback();
					return false;
				}

				for(int i = 0; i < spId.length; i++)
				{
					if(spId[i].trim().length() > 0 && Double.parseDouble(spSoluong[i].trim().replaceAll(",", "")) > 0 )
					{
						data = null;
						data = dbutils.setObject(this.id,spId[i],spDonvi[i],spSoluong[i].replaceAll(",", ""),spDongia[i].replaceAll(",", ""), spSoluong[i].replaceAll(",", ""),spDongia[i].replaceAll(",", "")
								,spChietkhau[i].replaceAll(",", ""),spSCheme[i].replaceAll(",", ""),spVat[i].replaceAll(",", ""),this.nppId
								,spId[i],spTen[i],this.ddhId);
						query = "\n insert HOADON_SP( hoadon_fk, sanpham_fk, donvitinh, soluong, dongia, thanhtien, chietkhau, scheme, vat, dongiaGOC,isnhapkhau,chietkhaungay,ten ) " +
						"\n  select ? , ?, ?, ?, ?," +
						"\n (? * ?), ?,?, ?, " +
						"\n		[dbo].[GiaBanLeNppSanPham](?,dh.khachhang_fk,? ,dh.ngaynhap )   as dongiaGOC, "+
						"\n 1,isnull(dh.chietkhau,0),? " +
						"\n from donhang dh where pk_seq=?  ";

						if ( db.update_v2(query, data) != 1)
						{
							this.msg = "Khong the tao moi HOADON_SP: ";
							db.getConnection().rollback();
							return false;
						}
					}
				}
			}

			/*query=  " select b.kho_fk, b.kbh_fk, a.NPP_FK, b.SANPHAM_FK, b.SOLO, SUM(b.soluong) as soluong,b.NgayHetHan  " +
					",isnull(b.ngaynhapkho,'') as ngaynhapkho "+
					" from PHIEUXUATKHO a inner join PHIEUXUATKHO_SANPHAM_CHITIET b on a.PK_SEQ = b.PXK_FK  "+
					" where a.PK_SEQ = " + pxkID +
					" group by b.kho_fk, b.kbh_fk, a.NPP_FK, b.SANPHAM_FK, b.SOLO,b.NgayHetHan  ,b.ngaynhapkho";*/

			query=  "  select b.kho_fk, 100025 as kbh_fk, a.NPP_FK, c.PK_SEQ as SANPHAM_FK, b.SOLO, SUM(b.soluong) as soluong, b.NgayHetHan,isnull(b.ngaynhapkho,'') as ngaynhapkho " + 
			"  from HOADON a inner join HOADON_SP_CHITIET b on a.PK_SEQ = b.hoadon_fk  " + 
			"  		inner join SANPHAM c on b.MA = c.MA " + 
			"  where a.PK_SEQ = '" + this.id + "' " + 
			"  group by b.kho_fk, a.NPP_FK, c.PK_SEQ, b.SOLO, b.NgayHetHan, isnull(b.ngaynhapkho, '') ";

			ResultSet rskho = db.get(query);
			while(rskho.next())
			{
				String _khoid=rskho.getString("kho_fk");
				String _kbh_fk=rskho.getString("kbh_fk");
				String _NPP_FK=rskho.getString("NPP_FK");
				String _SANPHAM_FK=rskho.getString("SANPHAM_FK");
				String _SOLO=rskho.getString("SOLO");
				String _NgayHetHan=rskho.getString("NgayHetHan");
				String _ngaynhapkho=rskho.getString("ngaynhapkho");
				double soluongct=rskho.getDouble("SOLUONG");
				String msg1 = util_kho.Update_NPP_Kho_Sp_Chitiet(this.ngayxuatHD, "Cập nhật hóa đơn - tăng kho ngược lại trước khi đổi lô: hoadontaichinh.java 1543 ", db, _khoid, _SANPHAM_FK, _NPP_FK, _kbh_fk, _SOLO, _NgayHetHan, _ngaynhapkho, 0, -1 * soluongct, soluongct, soluongct, 0);

				if(msg1.length() >0)
				{
					db.getConnection().rollback();
					this.msg = msg1;
					return false;
				}
			}

			query = "delete DONHANG_SANPHAM_CHITIET where DONHANG_FK = '" + this.ddhId + "'  ";
			if(!db.update(query))
			{
				this.msg = "Không thể hủy " + query;
				db.getConnection().rollback();
				return false;
			}

			query = "SELECT KBH_FK, kho_fk FROM DONHANG WHERE PK_SEQ = " + ddhId;
			ResultSet rsgetkenh = db.get(query);
			String kbh_fk = "";
			String kho_fk = "";
			if(rsgetkenh.next())
			{
				kbh_fk=rsgetkenh.getString("KBH_FK");
				kho_fk=rsgetkenh.getString("kho_fk");
			}
			rsgetkenh.close();

			//CHEN LAI DH TRONG TRUONG HOP DOI SOLO
			for(int i = 0; i < spId.length; i++)
			{
				if(spId[i].trim().length() > 0 && Double.parseDouble(spSoluong[i].trim().replaceAll(",", "")) > 0 )
				{
					if(this.sanpham_soluong.size() > 0)
					{
						Enumeration<String> keys = this.sanpham_soluong.keys();
						double totalCT = 0;

						while(keys.hasMoreElements())
						{
							String key = keys.nextElement();
							System.out.println("----KEY: " + key + "  -- ID: " + spId[i]);

							if(key.startsWith( spId[i]) )
							{
								String[] _sp = key.split("__");

								String _soluongCT = "0"; 
								if(this.sanpham_soluong.get(key) != null)
								{
									_soluongCT = this.sanpham_soluong.get(key).replaceAll(",", "");
								}

								totalCT += Double.parseDouble(_soluongCT);

								//CHECK TỒN KHO CỦA LÔ MUỐN ĐỔI CÓ ĐỦ KHÔNG
								query = " select AVAILABLE from NHAPP_KHO_CHITIET " +
								" where NPP_FK = '" + this.nppId + "'  " +
								" and kho_fk = ( select kho_fk from DONHANG where pk_seq in (" + this.ddhId + ") ) " +
								" and kbh_fk = '100025' and sanpham_fk = '" + _sp[0] + "' and SOLO = '" + _sp[1] + "' and ngayhethan = '" + _sp[2] + "' and ngaynhapkho='"+_sp[3]+"' ";
								System.out.println("1.1.Check TONKHO: " + query);
								ResultSet rsCHECK_TK = db.get(query);
								double avai = 0;
								if(rsCHECK_TK.next())
								{
									avai = rsCHECK_TK.getDouble("AVAILABLE");
								}
								rsCHECK_TK.close();

								if(avai < Double.parseDouble(_soluongCT) )
								{
									db.getConnection().rollback();
									this.msg = "Sản phẩm ( " + spTen[i] + " ) với số lô ( " + _sp[1] + " ), ngày hết hạn ( " + _sp[2] + " ), ngày nhập kho ="+_sp[3]+" còn tối đa ( " + avai + " ) ";
									return false;
								}

								/*query = " insert PHIEUXUATKHO_SANPHAM_CHITIET(PXK_FK, SANPHAM_FK, SOLUONG, SOLO, NGAYHETHAN, KBH_FK, KHO_FK,NGAYNHAPKHO)" +
										" select '" + pxkID + "', '" + _sp[0] + "', '" + _soluongCT + "', '" + _sp[1] + "', '" + _sp[2] + "' " +
										" , '100025', ( select kho_fk from DONHANG where pk_seq in (" + this.ddhId + ") ) as kho_fk ,'"+ _sp[3]+"'  ";

								System.out.println("1.2.Insert PHIEUXUATKHO_SANPHAM_CHITIET: " + query);
								if(db.updateReturnInt(query)!=1)
								{
									this.msg = "Khong the tao moi PHIEUXUATKHO_SANPHAM_CHITIET: " + query;
									db.getConnection().rollback();
									return false;
								}*/
								
								data = null;
								data = dbutils.setObject(ddhId,_sp[0],_soluongCT,_sp[1],_sp[2], kho_fk,_sp[3]);
								query = " insert DONHANG_SANPHAM_CHITIET(DONHANG_FK, SANPHAM_FK, SOLUONG, SOLO, NGAYHETHAN, KBH_FK, KHO_FK, NGAYNHAPKHO)" +
								" select '" + ddhId + "', '" + _sp[0] + "', '" + _soluongCT + "', '" + _sp[1] + "', '" + _sp[2] + "' " +
								" , '100025' as kbh_fk, '" + kho_fk + "' as kho_fk ,'" + _sp[3] + "'  ";

								if ( db.update_v2(query, data) != 1)
								{
									this.msg = "Khong the tao moi DONHANG_SANPHAM_CHITIET: ";
									db.getConnection().rollback();
									return false;
								}

								double soluongct = Double.parseDouble(_soluongCT);
								String _spid=_sp[0];
								String _solo=_sp[1];
								String _ngayhethan=_sp[2];
								String _ngaynhapkho=_sp[3];
								String msg1=util_kho.Update_NPP_Kho_Sp_Chitiet(this.ngayxuatHD, "Cập nhâth hóa đơn: hoadontaichinh.java 2382: "+this.id, db, kho_fk, _spid, this.nppId, kbh_fk, _solo, _ngayhethan, _ngaynhapkho, 0, soluongct, (-1)*soluongct, (-1)* soluongct, 0);

								if(msg1.length() >0){
									db.getConnection().rollback();
									this.msg=msg1;
									return false;
								}

								/*query = "Update NHAPP_KHO_CHITIET set soluong = soluong - '" + _soluongCT + "', AVAILABLE = AVAILABLE - '" + _soluongCT + "' " +
										"where NPP_FK = '" + this.nppId + "' and kho_fk = ( select kho_fk from DONHANG where pk_seq in (" + this.ddhId + ") ) and kbh_fk = '100025' and sanpham_fk = '" + _sp[0] + "' and SOLO = '" + _sp[1] + "' and ngayhethan = '" + _sp[2] + "' ";
								System.out.println("1.2.Insert NHAPP_KHO_CHITIET: " + query);
								if(db.updateReturnInt(query)!=1)
								{
									this.msg = "Khong the tao moi NHAPP_KHO_CHITIET: " + query;
									db.getConnection().rollback();
									return false;
								}*/
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

			query = "delete HOADON_SP_CHITIET where hoadon_fk="+this.id;
			System.out.println("---CHAY HOA DON CHI TIET: " + this.id );
			if(db.updateReturnInt(query) <= 0  )
			{
				msg = "Khong the cap nhat HOADON_SP_CHITIET: " + query;
				db.getConnection().rollback();
				return false;
			}

			/*query = "insert HOADON_SP_CHITIET( HOADON_FK, MA, TEN, DONVI, DONGIA, DONGIAGOC, SOLO, NGAYHETHAN, THUEVAT, SOLUONG, CHIETKHAU,KHO_FK,NGAYNHAPKHO )  " +
					" SELECT '" + this.id + "' as HOADON_FK, c.MA, c.TEN, d.DONVI, dhsp.GIAMUA AS DONGIA, dhsp.DONGIAGOC,   " +
					"		case f.SOLO when 'NA' then ' ' else f.SOLO end as SOLO,   " +
					"		case f.SOLO when 'NA' then ' ' else isnull(f.NGAYHETHAN,'') end as NGAYHETHAN, dhsp.thuevat,     " +
					"	    SUM( f.soluong) as soluong, '0' as chietkhau,dhsp.Kho_Fk ,F.NGAYNHAPKHO    " +
					"  FROM  DONHANG dh INNER JOIN DONHANG_SANPHAM dhsp on dhsp.DONHANG_FK = dh.PK_SEQ    " +
					"				   INNER JOIN SANPHAM c on dhsp.SANPHAM_FK = c.PK_SEQ                 " +
					"				   LEFT JOIN DONVIDOLUONG d on d.PK_SEQ = c.DVDL_FK   " +
					"  LEFT JOIN PHIEUXUATKHO_DONHANG e on dh.PK_SEQ = e.DONHANG_FK  " +
					"  LEFT JOIN PHIEUXUATKHO_SANPHAM_CHITIET f on e.PXK_FK = f.PXK_FK and c.PK_SEQ = f.SANPHAM_FK  " +
					"  WHERE dh.PK_SEQ in (select ddh_fk from HOADON_DDH where hoadon_fk =  '" + this.id + "' )  and  dhsp.SOLUONG > 0 " +
					"  and e.PXK_FK in ( select pk_seq from PHIEUXUATKHO where NPP_FK = '" + nppId + "' and trangthai != '2' )	  " +
					"  GROUP BY  c.MA, c.TEN, d.DONVI, dhsp.GIAMUA, dhsp.DONGIAGOC, dhsp.THUEVAT, f.SOLO, f.NGAYHETHAN, dhsp.KHO_FK ,NGAYNHAPKHO ";*/

			data = null;
			data = dbutils.setObject(this.id,this.id,this.ddhId);
			query = " insert HOADON_SP_CHITIET( HOADON_FK, MA, TEN, DONVI, DONGIA, DONGIAGOC, SOLO, NGAYHETHAN, THUEVAT, SOLUONG, CHIETKHAU, KHO_FK, isnhapkhau, ngaynhapkho,chietkhaungay )  " +
			" SELECT ? as HOADON_FK, c.MA,ISNULL(hd.ten,'') as tensp , d.DONVI, dhsp.GIAMUA AS DONGIA, dhsp.DONGIAGOC,   " +
			"		f.SOLO,   " +
			"		f.NGAYHETHAN, dhsp.thuevat,     " +
			"	    SUM( f.soluong) as soluong, '0' as chietkhau, dhsp.Kho_Fk, dhsp.isnhapkhau, f.ngaynhapkho,isnull(dhsp.chietkhaungay,0) " +
			" FROM  DONHANG dh INNER JOIN DONHANG_SANPHAM dhsp on dhsp.DONHANG_FK = dh.PK_SEQ    " +
			"				   INNER JOIN SANPHAM c on dhsp.SANPHAM_FK = c.PK_SEQ                 " +
			"				   LEFT JOIN DONVIDOLUONG d on d.PK_SEQ = c.DVDL_FK   " +
			"				   INNER JOIN DONHANG_SANPHAM_CHITIET f on dh.pk_seq = f.donhang_fk and c.PK_SEQ = f.SANPHAM_FK  "+
			"  inner join (select  ten,SANPHAM_FK from hoadon_sp hdsp where  hoadon_fk=?	)  hd on hd.SANPHAM_FK=c.PK_SEQ "+
			" WHERE dh.PK_SEQ = ?  and  dhsp.SOLUONG > 0 " +
			" GROUP BY dhsp.chietkhaungay, c.MA, ISNULL(hd.ten,''), d.DONVI, dhsp.GIAMUA, dhsp.DONGIAGOC, dhsp.THUEVAT, f.SOLO, f.NGAYHETHAN, dhsp.KHO_FK,dhsp.isnhapkhau,f.ngaynhapkho  ";

			if ( db.update_v2(query, data) <= 0)
			{
				msg = "Khong the cap nhat HOADON_SP_CHITIET: " + query;
				db.getConnection().rollback();
				return false;
			}
			
			data = null;
			data = dbutils.setObject(this.id);
			query = "delete HOADON_CHIETKHAU where hoadon_fk = ? ";
			if ( db.update_v2(query, data) < 0)
			{
				msg = "Khong the cap nhat HOADON_CHIETKHAU: ";
				db.getConnection().rollback();
				return false;
			}

			//LUU LAI THONG TIN CHIET KHAU
			int donhang_sau_ngay_01 = this.CompareDATE(this.ngayxuatHD, "2014-10-01");
			String hienthi = "0";
			if(donhang_sau_ngay_01 < 0)
				hienthi = "1";
			
			
			data = null;
			data = dbutils.setObject(this.id,hienthi,this.id,this.id,hienthi,this.id,this.id,this.id,this.id,this.id);

			query = "insert HOADON_CHIETKHAU(hoadon_fk, donhang_fk, diengiai, chietkhau, thueVAT, STT, tichluyQUY, HIENTHI) " +
			"	select ?, donhang_fk, N'CN5' as diengiai, sum(chietkhau) as chietkhau, thueVAT, 1 as STT, 0 as tichluyQUY, ? as HIENTHI  " +
			"	from DONHANG_SANPHAM  " +
			"	where isnull(isnhapkhau,1)=1 and thueVAT = '5' and donhang_fk in ( select DDH_FK from HOADON_DDH where HOADON_FK = ? ) and chietkhau > 0 " +
			"	group by donhang_fk, thueVAT " +
			"	 " +
			"union   " +
			"	select ?, donhang_fk, N'CN10' as diengiai, sum(chietkhau) as chietkhau, thueVAT, 2 as STT, 0 as tichluyQUY, ? as HIENTHI   " +
			"	from DONHANG_SANPHAM   " +
			"	where  isnull(isnhapkhau,1)=1 and thueVAT = '10' and donhang_fk in ( select DDH_FK from HOADON_DDH where HOADON_FK = ? ) and chietkhau > 0 " +
			"	group by donhang_fk, thueVAT " +
			"union  " +
			"	select ?, donhang_fk, a.diengiai, a.thanhtoan / ( 1 + ptTHUE / 100 ) as chietkhau, a.ptTHUE as thueVAT, 3 as STT, tichluyQUY, HIENTHI   " +
			"	from DUYETTRAKHUYENMAI_DONHANG a inner join TIEUCHITHUONGTL b on a.duyetkm_fk = b.PK_SEQ   " +
			"	where a.thanhtoan > 0 and donhang_fk in ( select DDH_FK from HOADON_DDH where HOADON_FK = ? )  " +
			"union  " +
			"	select ?, donhang_fk, a.maloai as diengiai, a.chietkhau, a.ptVAT as thueVAT, 4 as STT, 0 as tichluyQUY, 1 as HIENTHI   " +
			"	from DONHANG_CHIETKHAUBOSUNG a   " +
			"	where a.chietkhau > 0 and donhang_fk in ( select DDH_FK from HOADON_DDH where HOADON_FK = ? )  ";
			if( db.update_v2(query, data) < 0  )
			{
				msg = "Khong the cap nhat HOADON_CHIETKHAU ";
				db.getConnection().rollback();
				return false;
			}


			String kqHd = geso.dms.distributor.util.Utility.Update_GiaTri_HoaDon( this.id,  db);
			if( kqHd.trim().length() > 0 )
			{
				msg = "Khong the cap nhat HOADON: " + kqHd;
				db.getConnection().rollback();
				return false;
			}


			Utility util = new Utility();
			msg= util.Check_Huy_NghiepVu_KhoaSo("HoaDon", id, "NgayXuatHD", db);
			if(msg.length()>0)
			{
				db.getConnection().rollback();
				return false;
			}
			msg= util.Check_Kho_Tong_VS_KhoCT(this.nppId, db);
			if(msg.length()>0)
			{
				db.getConnection().rollback();
				return false;
			}

			this.msg = "Số hóa đơn bạn vừa lưu: " + this.sohoadon;
			db.getConnection().commit();
			db.getConnection().setAutoCommit(true);
		} 
		catch (Exception e) 
		{
			e.printStackTrace();
			db.update("rollback");			
			return false;
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

	public boolean chot(String hdId, boolean updateBEFORE) 
	{

		System.out.println("vao chot hd rui ne -----------------"+updateBEFORE);
		//Chốt trong chỗ cập nhật hóa đơn
		if(updateBEFORE)
		{
			boolean kqUPDATE = this.update();
			if(kqUPDATE == false)
			{
				return false;
			}
		}

		try
		{
			this.id = hdId;

			db.update("SET TRANSACTION ISOLATION LEVEL SNAPSHOT;");
			db.update("SET LOCK_TIMEOUT 60000;");
			db.getConnection().setAutoCommit(false);


			String checkhd=" select count(*) as sl from hoadon where (sohoadon='' or kyhieu='' or ngayxuathd='') and pk_seq="+hdId;
			System.out.println("cau query kiem tra la "+checkhd);
			ResultSet rshdcheck=db.get(checkhd);
			rshdcheck.next();
			if(rshdcheck.getInt("sl")>0)
			{
				msg = "Vui lòng điền đầy đủ thông tin ngày hóa đơn , ký hiệu ,số hóa đơn .";
				db.getConnection().rollback();
				rshdcheck.close();
				return false;
			}
			rshdcheck.close();
			//KHONG DUOC CHOT HOA DON TRONG THANG DA KHOA SO
			String query = "select top(1) NAM as namMax, THANGKS as thangMax, " +
			"\n	( select ngayxuatHD from HOADON where pk_seq = '" + hdId + "' ) as ngaylapphieu, " +
			"\n	( select trangthai from HOADON where pk_seq = '" + hdId + "' ) as trangthai, " +
			"\n	(	select count(NVGN_FK) from nvgn_kh where nvgn_kh.khachhang_fk =  ( select khachhang_fk from hoadon where pk_Seq = "+hdId+"  ))  coNVGN	,								" +
			"\n	( select count(*) as soDONG from HOADON where isnull(inChung1,0)=0 and sohoadon = ( select sohoadon from HOADON where pk_seq = '" + hdId + "' and isnull(inchung1,0)=0 ) and sohoadon != 'NA' and npp_fk = ( select npp_fk from HOADON where pk_seq = '" + hdId + "' ) and trangthai != '3' and KYHIEU = ( select KYHIEU from HOADON where pk_seq = '" + hdId + "' ) ) as soDONG_SOHOADON " +
			"\n from KHOASOTHANG where NPP_FK = ( select NPP_FK from HOADON where pk_seq = '" + hdId + "' ) " +
			"\n order by NAM desc, THANGKS desc ";
			System.out.println("1.Khoi tao thang: " + query);
			ResultSet rsKS = db.get(query);

			String trangthai = "";
			int coNVGN = 0;
			int soDONG_SOHOADON = 0;
			try
			{
				{
					while(rsKS.next())
					{
						trangthai = rsKS.getString("trangthai");
						int thangKs = rsKS.getInt("thangMax");
						int namKs = rsKS.getInt("namMax"); 
						coNVGN =   rsKS.getInt("coNVGN");
						this.ngayxuatHD = rsKS.getString("ngaylapphieu");
						soDONG_SOHOADON = rsKS.getInt("soDONG_SOHOADON");

						if(soDONG_SOHOADON > 1)
						{
							msg = "Số hóa đơn đã bị trùng. Vui lòng kiểm tra lại.";
							db.getConnection().rollback();
							rsKS.close();
							return false;
						}

						int nam = Integer.parseInt( rsKS.getString("ngaylapphieu").substring(0, 4) );
						int thang = Integer.parseInt( rsKS.getString("ngaylapphieu").substring(5, 7) );

						if(	thangKs == thang && nam == namKs )
						{
							msg = "Bạn không được chốt hóa đơn trong tháng đã khóa sổ";
							db.getConnection().rollback();
							rsKS.close();
							return false;
						}

					}
					rsKS.close();
				}
			}
			catch (Exception e) 
			{
				db.update("rollback");
				e.printStackTrace();
				msg = "Lỗi khi chốt hóa đơn " + e.getMessage();
				return false;
			}

			if(!trangthai.equals("1"))
			{
				msg = "Trạng thái hóa đơn không hợp lệ. Vui lòng kiểm tra lại.";
				db.getConnection().rollback();
				return false;
			}
			
			if(coNVGN<=0)
			{
				msg = "Vui lòng chọn NVGN ";
				db.getConnection().rollback();
				return false;
			}

			
			
			query = "update HOADON set trangthai = '2'  where pk_seq = '" + this.id + "' and TrangThai=1 ";
			if(db.updateReturnInt(query)!=1)
			{
				msg = "Không thể chốt ERP_HOADON " + query;
				db.getConnection().rollback();
				return false;
			}


			
			query=" update hoadon set trangthai=2 where pk_Seq in (select b.pk_seq from HOADON_DDH a inner join HOADON b on a.HOADON_FK=b.PK_SEQ  \n"+
			     "  where b.LOAIHOADON<>0 and b.trangthai not in (3,5)  \n"+
			     " and a.DDH_FK=(select dh.DDH_FK from HOADON_DDH dh where dh.HOADON_FK="+this.id+")  \n"+
			     " and b.PK_SEQ<>"+this.id+" )";
			if(!db.update(query))
			{
				msg = "Không thể chốt  " + query;
				db.getConnection().rollback();
				return false;
			}
			



			//CHECK THEM - NEU HOA DON KHAC VOI DON HANG THI KHONG CHO CHOT
			query = "select count(*) as sodong  " +
			"from " +
			"( " +
			"	select sanpham_fk, soluong  " +
			"	from DONHANG_SANPHAM where donhang_fk in ( select DDH_FK from HOADON_DDH where HOADON_FK = '" + this.id + "' ) " +
			") " +
			"dh left join " +
			"( " +
			"	select sanpham_fk, sum(soluong) as soluong  " +
			"	from HOADON_SP " +
			"	where HOADON_FK = '" + this.id + "'  " +
			"	group by sanpham_fk " +
			") " +
			"xk on dh.sanpham_fk = xk.sanpham_fk " +
			"where dh.soluong != isnull(xk.soluong, 0) ";
			System.out.println("---CHECK HOA DON: " + query);
			int soDONG = 0;
			ResultSet rsCHECK = db.get(query);
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



			String ddhid="";

			query=  " SELECT HD.NPP_FK,HD.LOAIHOADON ,DDH_FK FROM HOADON_DDH  a " +
			" inner JOIN HOADON HD ON HD.PK_SEQ=A.HOADON_FK WHERE HOADON_FK="+this.id;
			ResultSet rs=db.get(query);


			if(rs.next()){
				ddhid=rs.getString("DDH_FK");
				this.nppId=rs.getString("NPP_FK");

			}rs.close();

			String kqHd = geso.dms.distributor.util.Utility.Update_GiaTri_HoaDon( hdId,  db);
			if( kqHd.trim().length() > 0 )
			{
				msg = "Khong the cap nhat HOADON: " + kqHd;
				db.getConnection().rollback();
				return false;
			}



			//CAP NHAT LAI DA XUAT HOA DON
			query = "update DONHANG set DAXUATHOADON = '1' where pk_seq in ( select DDH_FK from HOADON_DDH where HOADON_FK = '" + this.id + "' ) ";
			if(!db.update(query))
			{
				msg = "Khong the cap nhat DONHANG: " + query;
				db.getConnection().rollback();
				return false;
			}

			geso.dms.distributor.util.Utility util = new geso.dms.distributor.util.Utility();
			msg= util.Check_Huy_NghiepVu_KhoaSo("HoaDon", id, "NgayXuatHD", db);
			if(msg.length()>0)
			{
				db.getConnection().rollback();
				return false; 
			}



			//Cập nhật số hóa đơn trong đơn hàng
			util.Update_SoHoaDon("select DDH_FK from HOADON_DDH where HOADON_FK = '" + this.id + "'", db);



			// tao phieu xuat kho
			String kqtpxk =create_pxk(db,this.id,this.userId,ngayxuatHD);
			if(kqtpxk.trim().length() > 0)
			{
				this.msg =kqtpxk;
				db.getConnection().rollback();
				return false; 
			}

			msg= util.Check_Kho_Tong_VS_KhoCT(this.nppId, db);
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
		finally
		{
			db.shutDown();
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


	public String getKhId() {
		return this.khId;
	}


	public void setKhId(String khId) {
		this.khId =khId;

	}


	public ResultSet getKhRs() {
		return this.khRs;
	}


	public void setKhRs(ResultSet khRs) {
		this.khRs = khRs;

	}


	public String getChietkhau() {

		return this.chietkhau;
	}


	public void setChietkhau(String chietkhau) {
		this.chietkhau= chietkhau;

	}
	public String[] getTichLuy_Scheme() {

		return this.tichluy_scheme;
	}


	public void setTichLuy_Scheme(String[] tichluy_scheme) {

		this.tichluy_scheme = tichluy_scheme;
	}


	public String[] getTichLuy_Tongtien() {

		return this.tichluy_tongtien;
	}


	public void setTichLuy_Tongtien(String[] tichluy_tongtien) {

		this.tichluy_tongtien = tichluy_tongtien;
	}


	public String[] getTichLuy_VAT() {
		return this.tichluy_vat;
	}


	public void setTichLuy_TVAT(String[] tichluy_vat) {
		this.tichluy_vat = tichluy_vat;

	}	

	public void initTichLuy(String ddh) 
	{
		String query = "";

		//CHUA CHOT, TAO MOI THI LOAD TU DON HANG
		if(this.trangthai.equals("0") || this.trangthai.equals("1") || this.trangthai.trim().length() <= 0 )
		{
			query =	"select N'CN5' as diengiai, sum(chietkhau) as chietkhau, thueVAT, 1 as STT, 0 as tichluyQUY from DONHANG_SANPHAM  " +
			"	where donhang_fk in (" + ddh+ ") and thueVAT = '5' and chietkhau > 0 group by thueVAT " +
			"union  " +
			"	select N'CN10' as diengiai, sum(chietkhau) as chietkhau, thueVAT, 2 as STT, 0 as tichluyQUY  " +
			"	from DONHANG_SANPHAM  " +
			"	where donhang_fk in (" + ddh+ ") and thueVAT = '10' and chietkhau > 0 group by thueVAT " +
			"union " +
			"	select a.diengiai, a.thanhtoan / ( 1 + ptTHUE / 100 ) as chietkhau, a.ptTHUE as thueVAT, 3 as STT, tichluyQUY  " +
			"	from DUYETTRAKHUYENMAI_DONHANG a inner join TIEUCHITHUONGTL b on a.duyetkm_fk = b.PK_SEQ   " +
			"	where a.donhang_fk in (" + ddh+ ") and hienthi = '1'  "+
			"union " +
			"	select a.maloai as diengiai, a.chietkhau, a.ptVAT as thueVAT, 4 as STT, 0 as tichluyQUY  " +
			"	from DONHANG_CHIETKHAUBOSUNG a    " +
			"	where a.donhang_fk in (" + ddh+ ")  order by STT, tichluyQUY  ";
		}
		else
		{
			query = " select diengiai, chietkhau, thueVAT, STT, tichluyQUY " +
			" from HOADON_CHIETKHAU where hoadon_fk = '" + this.id + "' and HIENTHI = '1' order by STT, tichluyQUY ";
		}

		System.out.println("---INIT TICH LUY: " + query);
		ResultSet rs = db.get(query);
		if(rs != null)
		{
			String schemeMa = "";
			String schemeVAT = "";
			String schemeTt = "";

			try 
			{
				NumberFormat format = new DecimalFormat("#,###,###.###");
				while(rs.next())
				{
					schemeMa += rs.getString("diengiai") + "__";
					schemeVAT += rs.getString("thueVAT") + "__";
					schemeTt += format.format( rs.getDouble("chietkhau")) + "__";
				}
				rs.close();

				if(schemeMa.trim().length() > 0)
				{
					schemeMa = schemeMa.substring(0, schemeMa.length() - 2);
					this.tichluy_scheme = schemeMa.split("__");

					schemeVAT = schemeVAT.substring(0, schemeVAT.length() - 2);
					this.tichluy_vat = schemeVAT.split("__");

					schemeTt = schemeTt.substring(0, schemeTt.length() - 2);
					this.tichluy_tongtien = schemeTt.split("__");
				}
			} 
			catch (Exception e) 
			{
				System.out.println("__EXCEPTION: " + e.getMessage());
				e.printStackTrace();
			}
		}	
	}


	public void getTongTien(String ddhId) 
	{
		String query = "";

		//CHUA CHOT, TAO MOI THI LOAD TU DON HANG
		if(this.trangthai.equals("0") || this.trangthai.equals("1") || this.trangthai.trim().length() <= 0 )
		{
			query = "select hangban.donhang_fk, hangban.thanhtien as tongtienBVAT, isnull(chietkhau.thanhtien, 0) as chietkhau,  " +
			"			hangban.thueVAT - isnull(chietkhau.thueVAT, 0) as vat, " +
			"			hangban.thanhtien + hangban.thueVAT - ( isnull(chietkhau.thanhtien, 0) + isnull(chietkhau.thueVAT, 0) ) as tongtienavat " +
			"from DONHANG hd inner join " +
			"( " +
			"	 select '" + ddhId + "' as donhang_fk,    " +
			"				sum( cast( ( SOLUONG * DONGIA * (1.0-chietkhaungay/100) ) as numeric(18, 0) ) ) as thanhtien,   " +
			"				sum( cast( (SOLUONG * DONGIA * (1.0-chietkhaungay/100)  * thuevat / 100) as numeric(18, 0) ) )  as thueVAT " +
			"	 from " +
			"	 (	 " +
			"		 select b.PK_SEQ as SPID, b.MA, b.TEN, DV.donvi, a.giamua as dongia, '' as scheme, a.thuevat,  " +
			"	 			sum( a.soluong) as soluong, sum(isnull(a.chietkhau, 0))  as chietkhau  " +
			"		 from Donhang_SANPHAM a inner Join SanPham b on a.SANPHAM_FK = b.PK_SEQ     " +
			"	 		  INNER JOIN DONVIDOLUONG DV ON DV.PK_SEQ = b.DVDL_FK        " +
			"		 where a.Donhang_FK = '" + ddhId + "' " +
			"		 group by  b.PK_SEQ, b.MA, b.TEN, DV.donvi, a.giamua, a.thuevat " +
			"	 ) " +
			"	 DH " +
			") " +
			"hangban on hd.pk_seq = hangban.donhang_fk left join " +
			"( " +
			"	select '" + ddhId + "' as donhang_fk,  " +
			"				SUM(  cast( chietkhau  as numeric(18, 0) )  ) as thanhtien,   " +
			"				SUM(  cast ( ( chietkhau * thueVAT / 100 ) as numeric(18, 0) )  )  as thueVAT " +
			"	from  " +
			"	( " +
			"			select N'CN5' as diengiai, sum(chietkhau) as chietkhau, thueVAT, 1 as STT, 0 as tichluyQUY  " +
			"			from DONHANG_SANPHAM   " +
			"			where donhang_fk = '" + ddhId + "' and thueVAT = '5' group by thueVAT  " +
			"		union   " +
			"			select N'CN10' as diengiai, sum(chietkhau) as chietkhau, thueVAT, 2 as STT, 0 as tichluyQUY   " +
			"			from DONHANG_SANPHAM   " +
			"			where donhang_fk = '" + ddhId + "' and thueVAT = '10' group by thueVAT  " +
			"		union  " +
			"			select a.diengiai, a.thanhtoan / ( 1 + ptTHUE / 100 ) as chietkhau, a.ptTHUE as thueVAT, 3 as STT, tichluyQUY   " +
			"			from DUYETTRAKHUYENMAI_DONHANG a inner join TIEUCHITHUONGTL b on a.duyetkm_fk = b.PK_SEQ    " +
			"			where a.donhang_fk = '" + ddhId + "' and HIENTHI = '1'  " +
			"		union  " +
			"			select a.maloai as diengiai, a.chietkhau, a.ptVAT as thueVAT, 4 as STT, 0 as tichluyQUY   " +
			"			from DONHANG_CHIETKHAUBOSUNG a     " +
			"			where a.donhang_fk = '" + ddhId + "'    " +
			"	) " +
			"	CK " +
			") " +
			"chietkhau on hd.pk_seq = chietkhau.donhang_fk ";
		}
		else
		{
			query = " select tongtienbvat as tongtienbvat, chietkhau as chietkhau, vat as vat , tongtienavat as tongtienavat from HOADON where pk_seq = '" + this.id + "'  ";
		}

		System.out.println("---INIT TONG TIEN: " + query);
		//INIT 4 COT TONG TIEN

		ResultSet rsTien = db.get(query);

		if(rsTien != null)
		{
			try 
			{
				NumberFormat formatter = new DecimalFormat("#,###,###");
				if(rsTien.next())
				{
					this.bvat = formatter.format(rsTien.getDouble("tongtienbvat"));
					this.totalCHIETKHAU = formatter.format(rsTien.getDouble("chietkhau"));
					this.thueVAT = formatter.format(rsTien.getDouble("vat"));
					this.avat = formatter.format(rsTien.getDouble("tongtienavat"));
				}
				rsTien.close();
			} 
			catch (Exception e) { e.printStackTrace(); }	
		}
	}


	public String[] getSpVat() {

		return this.spVat;
	}


	public void setSpVat(String[] spVat) {
		this.spVat= spVat;

	}


	public String[] getSpThue() {

		return this.spThue;
	}


	public void setSpThue(String[] spThue) {
		this.spThue = spThue;

	}

	public void loadContents() 
	{
		System.out.println("da vao loadcontents");
		this.getNppInfo();
		Utility util = new Utility();
		String query = "  select PK_SEQ, MAFAST + '-' + TEN as TEN" +
		"  from KHACHHANG" +
		"  where TRANGTHAI = '1' and NPP_FK= "+ this.nppId +" and KBH_FK=100025" +
		"         and pk_seq in (select KHACHHANG_FK from DONHANG where trangthai in  (1,4) ) ";
		this.khRs = db.get(query);

		this.createChietkhau();
		if(this.khId.trim().length() > 0 )
		{			
			query = " select PK_SEQ , NgayNhap as NgayDonHang  " +
			" from DONHANG " +
			" where  NPP_FK= "+ this.nppId +" AND  KHACHHANG_FK = '" + this.khId + "' and kho_fk in "+util.quyen_kho(this.userId) +
			/*"       and pk_seq in (select donhang_fk " +
					  "                      from PHIEUXUATKHO_DONHANG a inner join PHIEUXUATKHO b on a.pxk_fk= b.pk_seq " +
					  "                      where b.trangthai=1 and b.NPP_FK= "+ this.nppId +" )  " +*/
			" and pk_seq not in ( select a.ddh_fk from HoaDon_ddh a inner join hoadon b on a.hoadon_fk=b.pk_seq where b.trangthai not in ( 3, 5 ) and b.loaihoadon = 0 and b.PK_SEQ != " + (  this.id.trim().length() <= 0 ? "1" : this.id ) + " ) " ;

			System.out.println("Lay don hang: " + query);		
			this.ddhRs = db.get(query);

			if(this.khId.trim().length() > 0 )
			{			
				// LAY TEN NGUOI MUA TRONG DLN
				query =" select isnull(chucuahieu,'') as nguoimua from KHACHHANG where pk_seq= '"+ this.khId +"' ";
				ResultSet rsLayTT = db.get(query);
				if(rsLayTT != null)
				{
					try
					{
						while(rsLayTT.next())
						{
							this.nguoimua = rsLayTT.getString("nguoimua");
						}
						rsLayTT.close();
					}
					catch (Exception e) { e.printStackTrace(); }
				}	
			}
		}

		String chuoi = this.ddhId;
		System.out.println("don dat hang la "+chuoi);
		System.out.println("--Don hang IDS: "+chuoi);
		if(chuoi.trim().length() > 0)
		{			
			// INIT SP	
			query = " select b.PK_SEQ as SPID, b.MA, b.TEN, DV.donvi, a.giamua as dongia , ' ' as scheme, a.thuevat, " +
			" 		sum( a.soluong) as soluong, sum(isnull(a.chietkhau, 0))  as chietkhau " +
			" from Donhang_SANPHAM a inner Join SanPham b on a.SANPHAM_FK = b.PK_SEQ    " +
			" 	  INNER JOIN DONVIDOLUONG DV ON DV.PK_SEQ = b.DVDL_FK       " +
			" where a.Donhang_FK in ( " + chuoi + " ) " +
			" group by  b.PK_SEQ, b.MA, b.TEN, DV.donvi, a.giamua, a.thuevat ";

			System.out.println("Lấy sản phẩm: "+query);
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
				String spTienThue = "";
				double tienthue= 0;

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
						spVAT += rsLaySP.getString("thuevat") + "__";
						tienthue = (rsLaySP.getDouble("SOLUONG") * rsLaySP.getDouble("DONGIA") - rsLaySP.getDouble("chietkhau")) * rsLaySP.getDouble("thuevat")/100;
						spTienThue += tienthue + "__";
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

						spTienThue = spTienThue.substring(0, spTienThue.length() - 2);
						this.spThue = spTienThue.split("__");

					}

					//KHOI TAO SOLO
					/*query = "select sanpham_fk, solo, ngayhethan, sum(soluong) as soluong  " +
							"from PHIEUXUATKHO_SANPHAM_CHITIET where PXK_FK in ( select PXK_FK from PHIEUXUATKHO_DONHANG a inner join PHIEUXUATKHO b on a.pxk_fk = b.pk_seq where b.trangthai = '1' and donhang_fk in ( " + this.ddhId + " ) ) " +
							"group by sanpham_fk, solo, ngayhethan ";*/

					query = "select sanpham_fk, solo, ngayhethan, ngaynhapkho, sum(soluong) as soluong  " +
					"from HOADON_SP_CHITIET where hoadon_fk = '" + this.id + "' " +
					"group by sanpham_fk, solo, ngayhethan, ngaynhapkho ";
					System.out.println("---LO DA XUAT: " + query);
					ResultSet rsSOLO = db.get(query);
					this.sanpham_soluong = new Hashtable<String, String>();
					if(rsSOLO != null)
					{
						while(rsSOLO.next())
						{
							String key = rsSOLO.getString("sanpham_fk") + "__" + rsSOLO.getString("solo") + "__" + rsSOLO.getString("ngayhethan") + "__" + rsSOLO.getString("ngaynhapkho");
							this.sanpham_soluong.put(key, rsSOLO.getString("soluong"));
						}
						rsSOLO.close();
					}

				}
			}
			catch (Exception e) 
			{
				e.printStackTrace();
			}

			this.initTichLuy(chuoi);
			this.getTongTien(chuoi);

		}	
	}

	public boolean duyetDIEUCHINH() 
	{
		if(spMa == null)
		{
			this.msg = "Vui lòng kiểm tra lại danh sách sản phẩm";
			return false;
		}

		try
		{
			db.getConnection().setAutoCommit(false);

			String query = "update HOADON set LANDIEUCHINH = LANDIEUCHINH + 1 where pk_seq = '" + this.id + "' ";
			if(!db.update(query))
			{
				msg = "Không thể điều chỉnh HOADON " + query;
				db.getConnection().rollback();
				return false;
			}

			query = "insert HOADON_LOG_DIEUCHINH(hoadon_fk, landieuchinh, lydo) " +
			"select pk_seq, max(landieuchinh), N'Điều chỉnh hóa đơn' from HOADON where pk_seq = '" + this.id + "' group by pk_seq";
			if(!db.update(query))
			{
				msg = "Không thể điều chỉnh HOADON " + query;
				db.getConnection().rollback();
				return false;
			}

			//COPY HOA DON GOC TRUOC KHI DIEU CHINH
			query = "insert HOADON_LOG_SP(log_fk, hoadon_fk, SANPHAM_FK, SOLUONG_GOC, SOLUONG_MOI, DONGIA, TIENBVAT, VAT, TIENAVAT, DONVITINH, SCHEME, CHIETKHAU, THANHTIEN) " +
			"select ident_current('HOADON_LOG_DIEUCHINH'), hoadon_fk, SANPHAM_FK, SOLUONG, SOLUONG, DONGIA, TIENBVAT, VAT, TIENAVAT, DONVITINH, SCHEME, CHIETKHAU, THANHTIEN   " +
			"from HOADON_SP where hoadon_fk = '" + this.id + "' ";
			if(!db.update(query))
			{
				msg = "Không thể điều chỉnh HOADON " + query;
				db.getConnection().rollback();
				return false;
			}

			/*for(int i = 0; i < spId.length; i++ )
			{
				if(spSoluong[i].trim().length() > 0 && spDongia[i].trim().length() > 0)
				{
					query = "update HOADON_SP set SOLUONG = ''cádcascd, DONGIA = ''23232323asdadsc ";
				}
			}*/

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


	public void init_Display() 
	{
		this.getNppInfo();
		String query =  " select dondathang_fk, ngayxuatHD, ISNULL(ghichu, '') as ghichu, kyhieu, sohoadon, hinhthuctt ,khachhang_fk, npp_fk, trangthai, isnull(nguoimua,'') as nguoimua, isnull(innguoimua,1) as innguoimua,  " +
		"        isnull(tongtienbvatNK, isnull(tongtienbvat, 0))  as tongtienbvat, isnull(chietkhauNK,isnull(chietkhau, 0)) as chietkhau, isnull(ngayghinhanCN, '"+ getDateTime() +"') as ngayghinhanCN , " +
		"       isnull( vatNK,isnull(vat, 0)) as vat, isnull(tongtienavatNK, isnull(tongtienavat, 0))  as tongtienavat, isnull(tongtienkm, 0) as tongtienkm,isnull(mavv,'') as mavv   " +
		" from HOADON" +
		" where pk_seq = '" + this.id + "'";
		System.out.println("____INIT HOADON display: " + query);
		ResultSet rs = db.get(query);
		if(rs != null)
		{
			try 
			{
				NumberFormat formatter = new DecimalFormat("#,###,###");
				if(rs.next())
				{
					this.ngayxuatHD = rs.getString("ngayxuatHD");
					this.ghichu = rs.getString("ghichu");
					this.hinhthuctt = rs.getString("hinhthuctt");
					this.kyhieuhoadon = rs.getString("kyhieu");
					this.ngayghinhanCN = rs.getString("ngayghinhanCN");
					this.sohoadon = rs.getString("sohoadon");
					this.khId = rs.getString("khachhang_fk");
					this.nppId = rs.getString("npp_fk");
					this.trangthai = rs.getString("trangthai");
					this.innguoimua = rs.getString("innguoimua");
					this.nguoimua = rs.getString("nguoimua");
					this.mavuviec=rs.getString("mavv");

					this.bvat = formatter.format(rs.getDouble("tongtienbvat"));
					this.totalCHIETKHAU = formatter.format(rs.getDouble("chietkhau"));
					this.thueVAT = formatter.format(rs.getDouble("vat"));
					this.avat = formatter.format(rs.getDouble("tongtienavat"));

				}
				rs.close();

				//INIT DDH
				query = "SELECT DDH_FK FROM HOADON_DDH WHERE HOADON_FK = " + this.id;
				rs = this.db.get(query);

				String ddh = "";
				if(rs!=null)
				{
					while(rs.next())
					{
						ddh += rs.getString("DDH_FK") + ",";
					}
					rs.close();

					if(ddh.trim().length() > 0)
						this.ddhId = ddh.substring(0, ddh.length() - 1);
				}

				if( !this.trangthai.equals("1")  )
				{
					query = " select B.PK_SEQ ,B.NGAYNHAP AS NgayDonHang  " +
					" from HOADON_DDH A INNER JOIN DONHANG B ON A.DDH_FK = B.PK_SEQ " +
					" where A.HOADON_FK = '"+ this.id +"' AND A.HOADON_FK IN (SELECT PK_SEQ FROM HOADON WHERE LOAIHOADON= 0) ";

				}
				else
				{				
					query = "select PK_SEQ ,NGAYNHAP AS NgayDonHang  " +
					"from DONHANG " +
					"where  trangthai not in (0,2 ) and   isnull(donhangkhac,0) = 0 and NPP_FK= "+ this.nppId +" and  KHACHHANG_FK = '" + this.khId + "' " +
					"    and ( import= '1' or pk_seq in (select donhang_fk " +
					"                      from PHIEUXUATKHO_DONHANG a inner join PHIEUXUATKHO b on a.pxk_fk= b.pk_seq " +
					"					   where b.trangthai=1 and b.NPP_FK= "+ this.nppId +"))  " +
					" and pk_seq not in (Select a.ddh_fk from HoaDon_ddh a inner join hoadon b on a.hoadon_fk=b.pk_seq where b.trangthai in (2,4) and b.loaihoadon = 0 and b.pk_seq!= '"+ this.id +"')  " ;
				}

				System.out.println("Lấy đơn hàng "+query);		
				this.ddhRs = db.get(query);

			} 
			catch (Exception e) 
			{
				System.out.println("---LOI INIT: " + e.getMessage());
			}
		}

		this.createRs();

	}


	public void getTongTienPDF() 
	{
		String query = "";
		query = " select isnull(tongtienbvatNK,tongtienbvat) as tongtienbvat, ISNULL(chietkhauNK,chietkhau) as chietkhau, ISNULL(vatNK,vat) as vat, isnull(tongtienavatNK,tongtienavat) as tongtienavat from HOADON where pk_seq = '" + this.id + "'  ";

		System.out.println("---INIT TONG TIEN: " + query);
		//INIT 4 COT TONG TIEN

		ResultSet rsTien = db.get(query);

		if(rsTien != null)
		{
			try 
			{
				NumberFormat formatter = new DecimalFormat("#,###,###");
				if(rsTien.next())
				{
					this.bvat = formatter.format(rsTien.getDouble("tongtienbvat"));
					this.totalCHIETKHAU = formatter.format(rsTien.getDouble("chietkhau"));
					this.thueVAT = formatter.format(rsTien.getDouble("vat"));
					this.avat = formatter.format(rsTien.getDouble("tongtienavat"));
				}
				rsTien.close();
			} 
			catch (Exception e) { e.printStackTrace(); }	
		}
	}

	private int CompareDATE(String _date1, String _date2)
	{
		try
		{
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			//Date date1 = sdf.parse("2014-10-01");
			//Date date2 = sdf.parse("2014-10-01");

			Date date1 = sdf.parse(_date1);
			Date date2 = sdf.parse(_date2);

			//System.out.println(sdf.format(date1));
			//System.out.println(sdf.format(date2));

			return date1.compareTo(date2);
		}
		catch (Exception e) {
			return 0;
		}

	}

	public Hashtable<String, String> getSanpham_Soluong() {

		return this.sanpham_soluong;
	}


	public void setSanpham_Soluong(Hashtable<String, String> sp_soluong) {

		this.sanpham_soluong = sp_soluong;
	}

	public ResultSet getSoloTheoSp(String spMa, String donvi, String tongluong)
	{
		// Kiểm tra npp nếu là Đối tác thì không dùng Số hóa đơn của hệ thống
		/*String	query = 
				" select    loaiNPP, (  select PXK_FK from PHIEUXUATKHO_DONHANG a inner join PHIEUXUATKHO b on a.pxk_fk = b.pk_seq where b.trangthai = '1' and donhang_fk in ( " + this.ddhId + " ) ) as pxkID " +
						",  ISNULL( ( select DonHangKhac from DONHANG aa  where pk_seq="+this.ddhId+" ), 0) as DonHangKhac " +
						" from NHAPHANPHOI " +
						" where pk_seq = '" + nppId + "' ";
		System.out.println("____Init___"+query);
		ResultSet rs = db.get(query);
		String loainpp= "";
		String pxkID = "";
		String donhangkhac = "";
		{
			try
			{
				while(rs.next())
				{
					loainpp = rs.getString("loaiNPP");
					pxkID = rs.getString("pxkID");
					donhangkhac=rs.getString("DonHangKhac");
				}
				rs.close();
			} catch (SQLException e)
			{
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}*/


		tongluong = tongluong.replaceAll(",", "");
		//System.out.println("---TONG LUONG: " + tongluong );
		String kbh_fk = "100025";
		String query = "";

		query = "select AVAILABLE + isnull(( select hdsp.SOLUONG  from HOADON_SP_CHITIET hdsp where hdsp.hoadon_fk = '" + this.id + "' and hdsp.SOLO=ct.SOLO and sp.MA=hdsp.MA and hdsp.NGAYHETHAN=ct.NGAYHETHAN and hdsp.NGAYNHAPKHO=ct.NGAYNHAPKHO ),0)as AVAILABLE, "+
		" NGAYNHAPKHO, NGAYHETHAN, SOLO " +
		"from NHAPP_KHO_CHITIET ct inner join SANPHAM sp on ct.sanpham_fk = sp.pk_seq " +
		"where KHO_FK = ( select kho_fk from DONHANG where pk_seq in (select ddh_fk  from  hoadon_ddh where hoadon_fk= '" + this.id + "') ) and SANPHAM_FK = '" + spMa + "'   " +
		"	and AVAILABLE + isnull(( select hdsp.SOLUONG  from HOADON_SP_CHITIET hdsp where hdsp.hoadon_fk = '" + this.id + "' and hdsp.SOLO=ct.SOLO and sp.MA=hdsp.MA and hdsp.NGAYHETHAN=ct.NGAYHETHAN and hdsp.NGAYNHAPKHO=ct.NGAYNHAPKHO ),0) > 0 and NPP_FK = '" + this.nppId + "' and KBH_FK = '" + kbh_fk + "' "+ 
		"order by NGAYHETHAN, NGAYNHAPKHO ";

		/*query =	"	 select available+ isnull(( select soluong from phieuxuatkho a inner join  phieuxuatkho_sanpham_chitiet b on b.pxk_fk=a.pk_seq  "+
				"				where b.sanpham_fk=ct.sanpham_fk and a.npp_fk=ct.npp_fk and b.kbh_fk=ct.kbh_fk  "+
				"					and b.kho_fk=ct.kho_fk and b.solo=ct.solo and b.ngayhethan=ct.ngayhethan and b.ngaynhapkho=ct.ngaynhapkho and a.pk_seq='"+pxkID+"' ),0)as available " +
				" ,ngayhethan, solo ,ct.NGAYNHAPKHO  "+
				"			from nhapp_kho_chitiet ct inner join sanpham sp on ct.sanpham_fk = sp.pk_seq  "+
				"			where  ct.ngaynhapkho<='"+this.ngayxuatHD+"' and kho_fk = ( select kho_fk from donhang where pk_seq in ("+this.ddhId+") ) and sanpham_fk = '"+spMa+"'  "+
				"				and available +isnull(( select soluong from phieuxuatkho a inner join phieuxuatkho_sanpham_chitiet b on b.pxk_fk=a.pk_seq "+
				"			where b.sanpham_fk=ct.sanpham_fk and a.npp_fk=ct.npp_fk and b.kbh_fk=ct.kbh_fk  "+
				"				and b.kho_fk=ct.kho_fk and b.solo=ct.solo and b.ngayhethan=ct.ngayhethan  and b.ngaynhapkho=ct.ngaynhapkho and a.pk_seq='"+pxkID+"'),0) >0 and npp_fk = '"+nppId+"' and kbh_fk = '"+kbh_fk+"'  "+
				"			order by ngayhethan asc  ";*/

		System.out.println("----LAY SO LO: " + query  );
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
					query2 += "select '" + avai + "' as AVAILABLE, '" + rs.getString("NGAYHETHAN") + "' as NGAYHETHAN,'"+rs.getString("ngaynhapkho")+"' as ngaynhapkho , '" + rs.getString("SOLO") + "' as SOLO, '" + slg + "' as tuDEXUAT union ALL ";
				}
				else
				{
					query2 += "select '" + avai + "' as AVAILABLE, '" + rs.getString("NGAYHETHAN") + "' as NGAYHETHAN,'"+rs.getString("ngaynhapkho")+"' as ngaynhapkho , '" + rs.getString("SOLO") + "' as SOLO, '' as tuDEXUAT union ALL ";
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
			System.out.println("---TU DONG DE XUAT BIN - LO: " + query2 );
			return db.get(query2);
		}

		return null;
	}

	public static String create_pxk(dbutils db,String hoadonid,String userId, String ngayxuatkho)
	{
		String usertao="";
		String NVGN_FK="";
		String npp_fk="";
		String dh_fk = "";
		try{
			String query="select a.khachhang_fk,a.npp_fk,a.ngayxuathd,b.ddh_fk,(select top 1 NVGN_FK b from NVGN_KH b where b.KHACHHANG_FK=a.khachhang_fk) NVGN_FK from hoadon a" +
					" inner join hoadon_ddh b on a.pk_seq  = b.hoadon_fk where a.pk_seq="+hoadonid +" ";
			ResultSet rs=db.get(query);
			while(rs.next())
			{
				//ngayhoadon=rs.getString("ngayxuathd");
				NVGN_FK=rs.getString("NVGN_FK");
				npp_fk=rs.getString("npp_fk");
				dh_fk = rs.getString("ddh_fk");
				System.out.println(" a.khachhang_fk ==== " + rs.getString("khachhang_fk") +" dh_fk: "+dh_fk);
			}
			rs.close();

			query = "insert into Phieuxuatkho(nvgn_fk, trangthai, ngaytao, ngaysua, nguoitao, nguoisua, npp_fk, ngaylapphieu) " ;
			query = query + "values('" + NVGN_FK + "','0','" + ngayxuatkho + "','" + ngayxuatkho + "','" + userId + "','" + userId +"','" + npp_fk +"','" + ngayxuatkho + "')";
			if(db.updateReturnInt(query)<=0)
			{
				return "Khong the tao moi 'Phieuxuatkho', " + query;	 
			}

			query = "select scope_identity() as pxkId";

			ResultSet rsPxk = db.get(query);
			rsPxk.next();
			String pxk_id = rsPxk.getString("pxkId");
			rsPxk.close();

			//bang phieuxuatkho_donhang

			//chua can luu tonggiatri o buoc nay
			query = "Insert into phieuxuatkho_donhang(pxk_fk, hoadon_fk, donhang_fk,tonggiatri) values('" + pxk_id + "', '" + hoadonid + "','"+dh_fk+"', null)";
			System.out.println("Insert duoc :" + query);
			if(db.updateReturnInt(query)<=0)
			{
				return "Khong the them moi bang 'phieuxuatkho_hoadon', " + query;
			}

			query=	  " Insert into phieuxuatkho_donhang(pxk_fk, hoadon_fk, donhang_fk, tonggiatri)  select '" + pxk_id + "', b.pk_seq,a.ddh_fk,0 from HOADON_DDH a inner join HOADON b on a.HOADON_FK=b.PK_SEQ "+
			"	where b.LOAIHOADON=1 and b.trangthai not in (3,5) and a.DDH_FK=(select dh.DDH_FK from HOADON_DDH dh where dh.HOADON_FK="+hoadonid+") and b.PK_SEQ<>"+hoadonid+" ";
			System.out.println("insert pxkkm  "+query);
			if(!db.update(query))
			{
				return "Khong the them moi bang 'phieuxuatkho_hoadon', " + query;
			}
		} 
		catch (Exception e)
		{
			e.printStackTrace();
			return "Excep"+ e.getMessage();
		}
		return "";
	}

	public static void main(String[] args) {

		dbutils db = new dbutils();
		try
		{

			String query =" select PK_SEQ,NPP_FK,NGAYLAPPHIEU from PHIEUXUATKHO where PK_SEQ in (1000000136) ";
			ResultSet rs = db.get(query);

			while(rs.next())
			{
				db.getConnection().setAutoCommit(false);
				String pxkId = rs.getString("PK_SEQ");
				String nppId = rs.getString("NPP_FK");
				String ngaylap = rs.getString("NGAYLAPPHIEU");
				String kq = Chotphieuxuat( db, pxkId,  nppId,  ngaylap) ;
				if(kq.trim().length() > 0)
				{
					db.getConnection().rollback();
					db.getConnection().setAutoCommit(true);
				}
				else
				{
					db.getConnection().commit();
					db.getConnection().setAutoCommit(true);
				}

				System.out.println("kq "+ rs.getString("PK_SEQ") +" = " + kq);
			}
		}
		catch (Exception e) {
			db.update("rollback");
		}
		finally
		{
			db.shutDown();
		}
	}


	public static String Chotphieuxuat(dbutils db,String pxkId, String nppId, String ngaylap) 
	{	

		String msg = "";
		try
		{

			String query = "";

			//HÀNG BÁN
			query = "  insert PHIEUXUATKHO_SANPHAM( PXK_FK, SANPHAM_FK, kbh_fk, kho_fk, SOLUONG )  " + 
			"  select  '" + pxkId + "', hd_sp.SANPHAM_FK, hd.KBH_FK, isnull(hd_sp.KHO_FK, hd.kho_fk), sum(hd_sp.SOLUONG) as SOLUONG " + 
			"  from HOADON hd inner join HOADON_SP hd_sp on hd.PK_SEQ = hd_sp.HOADON_FK " + 
			"  where hd.loaihoadon = 0 and hd.PK_SEQ in ( select hoadon_fk from PHIEUXUATKHO_DONHANG where PXK_FK = '" + pxkId + "' ) " + 
			"  group by hd_sp.SANPHAM_FK, hd.KBH_FK, isnull(hd_sp.KHO_FK, hd.kho_fk) ";
			System.out.println(":: CHEN HANG BAN: " + query);
			if( !db.update(query) )
			{
				msg = "Khong the cap nhat PHIEUXUATKHO_SANPHAM: " + query;

				return msg;
			}
			
			query = "  insert PHIEUXUATKHO_SANPHAM_CHITIET( PXK_FK, KHO_FK, KBH_FK, SANPHAM_FK, SOLUONG, SOLO, NGAYHETHAN, NGAYNHAPKHO )  " + 
			"  select  '" + pxkId + "', isnull(hd_sp.KHO_FK, hd.kho_fk), hd.KBH_FK, sp.PK_SEQ as SANPHAM_FK, sum(hd_sp.SOLUONG) as SOLUONG, hd_sp.solo, hd_sp.NGAYHETHAN, hd_sp.NGAYNHAPKHO " + 
			"  from HOADON hd inner join HOADON_SP_CHITIET hd_sp on hd.PK_SEQ = hd_sp.HOADON_FK " + 
			"  		inner join SANPHAM sp on hd_sp.MA = sp.MA " + 
			"  where hd.loaihoadon = 0 and hd.PK_SEQ in ( select hoadon_fk from PHIEUXUATKHO_DONHANG where PXK_FK = '" + pxkId + "' ) " + 
			"  group by isnull(hd_sp.KHO_FK, hd.kho_fk), hd.KBH_FK, sp.PK_SEQ, hd_sp.solo, hd_sp.NGAYHETHAN, hd_sp.NGAYNHAPKHO ";
			System.out.println(":: CHEN HANG BAN CT: " + query);
			if( !db.update(query) )
			{
				msg = "Khong the cap nhat PHIEUXUATKHO_SANPHAM_CHITIET: " + query;
				return msg;
			}



			//HÀNG KHUYẾN MẠI
			query = "  insert PHIEUXUATKHO_SPKM( PXK_FK, SANPHAM_FK, kbh_fk, kho_fk, scheme, SOLUONG )  " + 
			"  select  '" + pxkId + "', hd_sp.SANPHAM_FK, hd.KBH_FK, isnull(hd_sp.KHO_FK, hd.kho_fk), km.pk_seq, sum(hd_sp.SOLUONG) as SOLUONG  " + 
			"  from HOADON hd left join HOADON_CTKM_TRAKM hd_sp on hd.PK_SEQ = hd_sp.HOADON_FK left join CTKHUYENMAI km on hd_sp.CTKM = km.SCHEME     " + 
			"  where hd.loaihoadon != 0 and hd.PK_SEQ in ( select hoadon_fk from PHIEUXUATKHO_DONHANG where PXK_FK = '" + pxkId + "' )  " + 
			"  group by hd_sp.SANPHAM_FK, hd.KBH_FK, isnull(hd_sp.KHO_FK, hd.kho_fk), km.pk_seq ";
			System.out.println(":: CHEN HANG KM: " + query);
			if( !db.update(query) )
			{
				msg = "Khong the cap nhat PHIEUXUATKHO_SPKM: " + query;
				return msg;
			}

			query = "  insert PHIEUXUATKHO_SPKM_CHITIET( PXK_FK, KHO_FK, KBH_FK, SANPHAM_FK, SOLUONG, SOLO, NGAYHETHAN, NGAYNHAPKHO, SCHEME )    " + 
			"  select  '" + pxkId + "', isnull(hd_sp.KHO_FK, hd.kho_fk), hd.KBH_FK, sp.PK_SEQ as SANPHAM_FK, sum(hd_sp.SOLUONG) as SOLUONG, hd_sp.solo, hd_sp.NGAYHETHAN, hd_sp.NGAYNHAPKHO, km.pk_seq   " + 
			"  from HOADON hd left join HOADON_CTKM_TRAKM_CHITIET hd_sp on hd.PK_SEQ = hd_sp.HOADON_FK   " + 
			"  inner join SANPHAM sp on hd_sp.sanpham_fk = sp.PK_SEQ left join CTKHUYENMAI km on hd_sp.scheme = km.SCHEME " + 
			"  where hd.loaihoadon != 0 and hd.PK_SEQ in ( select hoadon_fk from PHIEUXUATKHO_DONHANG where PXK_FK = '" + pxkId + "' )   " + 
			"  group by isnull(hd_sp.KHO_FK, hd.kho_fk), hd.KBH_FK, sp.PK_SEQ, hd_sp.solo, hd_sp.NGAYHETHAN, hd_sp.NGAYNHAPKHO, km.pk_seq    ";
			System.out.println(":: CHEN HANG KM CT: " + query);
			if( !db.update(query) )
			{
				msg = "Khong the cap nhat PHIEUXUATKHO_SPKM_CHITIET: " + query;
				return msg;
			}

			//Trừ booked + avai
			query = "  select c.npp_fk, case when isnull(d.dungchungkenh, 0) = 0 then a.kbh_fk else 100025 end as kbh_fk,    " + 
			"  	a.kho_fk, a.sanpham_fk, b.ten as TEN, a.soluong as soluongDAT, a.solo, a.ngayhethan,     " + 
			"  	a.soluong,  0 as loai, ' ' as scheme, a.ngaynhapkho       " + 
			"  from PHIEUXUATKHO_SANPHAM_CHITIET a inner join SANPHAM b on a.sanpham_fk = b.PK_SEQ      " + 
			"  	inner join PHIEUXUATKHO c on  a.pxk_fk = c.pk_seq inner join NHAPHANPHOI d on c.npp_fk = d.pk_seq    " + 
			"  where a.pxk_fk = '" + pxkId + "' ";

			System.out.println("--CAP NHAT TON KHO: " + query);
			ResultSet rs = db.get(query);
			Utility utility = new Utility();
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

				String msg1=utility.Update_NPP_Kho_Sp(Utility.getNgayHienTai(), "chot phieu xuat kho OTC - hàng bán:(tong)"+ pxkId, db, khoID, spID, nppID ,kbhID,(-1) * soluong,(-1) * soluong, 0, 0);// giảm booked,avai cộng
				if(msg1.length() >0)
				{
					msg = "Khong the cap nhat NHAPP_KHO: " + msg1;
					rs.close();
					return msg; 
				}

				String kq1 = utility.Update_NPP_Kho_Sp_Chitiet(Utility.getNgayHienTai(), "chot phieu xuat kho OTC - hàng bán: "+pxkId, db, khoID, spID, nppID, kbhID, solo, ngayhethan, ngaynhapkho,  (-1) * soluong,  (-1) * soluong, 0, 0, 0);
				if( kq1.length() > 0 )
				{
					msg = "Khong the cap nhat NHAPP_KHO_CHITIET: " + kq1;
					rs.close();
					return msg;
				}					
			}
			rs.close();

			//Trừ booked + avai
			query = "  select c.npp_fk, case when isnull(d.dungchungkenh, 0) = 0 then a.kbh_fk else 100025 end as kbh_fk,    " + 
			"  	a.kho_fk, a.sanpham_fk, b.ten as TEN, a.soluong as soluongDAT, a.solo, a.ngayhethan,     " + 
			"  	a.soluong,  0 as loai, ' ' as scheme, a.ngaynhapkho       " + 
			"  from PHIEUXUATKHO_SPKM_CHITIET a inner join SANPHAM b on a.sanpham_fk = b.PK_SEQ      " + 
			"  	inner join PHIEUXUATKHO c on  a.pxk_fk = c.pk_seq inner join NHAPHANPHOI d on c.npp_fk = d.pk_seq    " + 
			"  where a.pxk_fk = '" + pxkId + "' ";

			System.out.println("--CAP NHAT TON KHO: " + query);
			rs = db.get(query);
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

				String msg1=utility.Update_NPP_Kho_Sp(Utility.getNgayHienTai(), "chot phieu xuat kho OTC - hàng KM:(tong)"+ pxkId, db, khoID, spID, nppID ,kbhID,(-1) * soluong,(-1) * soluong, 0, 0);
				if(msg1.length() >0)
				{
					msg = "Khong the cap nhat NHAPP_KHO2: " + msg1;
					rs.close();
					return msg; 
				}

				String kq1 = utility.Update_NPP_Kho_Sp_Chitiet(Utility.getNgayHienTai(), "chot phieu xuat kho OTC - hàng KM: "+pxkId, db, khoID, spID, nppID, kbhID, solo, ngayhethan, ngaynhapkho,  (-1) * soluong,  (-1) * soluong, 0, 0, 0);
				if( kq1.length() > 0 )
				{

					msg = "Khong the cap nhat NHAPP_KHO_CHITIET: " + kq1;
					rs.close();
					return msg;
				}					
			}
			rs.close();


			//GOP CHUNG BUOC YC VA XUAT THANH 1
			query = "update PHIEUXUATKHO set trangthai = '1'  where pk_seq = '" + pxkId + "' and trangthai != 1 ";
			System.out.println("---CAP NHAT TRANG THAI: " + query);
			if(db.updateReturnInt(query) <= 0 )
			{
				msg = "Không thể cập nhật PHIEUXUATKHO " + query;
				db.getConnection().rollback();
				return msg;
			}



			query = "update donhang set isbooked = '0'  where pk_seq = (select top(1) b.DDH_FK from PHIEUXUATKHO_DONHANG a inner join HOADON_DDH b on a.hoadon_fk=b.HOADON_FK where a.pxk_fk='" + pxkId + "') and trangthai = 1 ";
			System.out.println("---CAP NHAT TRANG THAI: " + query);
			if(db.updateReturnInt(query) <= 0 )
			{
				msg = "Không thể cập nhật PHIEUXUATKHO " + query;
				db.getConnection().rollback();
				return msg;
			}




		}
		catch (Exception e) 
		{
			e.printStackTrace();
			return "Exception: " + e.getMessage();
		}

		return "";
	}




}
