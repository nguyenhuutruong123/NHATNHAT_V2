package geso.dms.erp.beans.lenhsanxuat.imp;

import geso.dms.center.util.Utility;
import geso.dms.center.util.Utility_Kho;
import geso.dms.erp.beans.lenhsanxuat.IErpChuyenkhoSX;
import geso.dms.erp.beans.lenhsanxuat.IYeucau;
import geso.dms.erp.beans.nhapkho.IKhu_Vitri;
import geso.dms.erp.beans.phieuxuatkho.ISpDetail;
import geso.dms.erp.beans.phieuxuatkho.imp.SpDetail;
import geso.dms.erp.db.sql.dbutils;

import java.sql.ResultSet;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class ErpChuyenkhoSX implements IErpChuyenkhoSX
{
	private static final long serialVersionUID = 1L;
	String userId;
	String id;
	String ngayyeucau;
	String Ngaydenghi;
	String PoLsx;
	String lydoyeucau;
	
	String Kyhieu="";
	String sochungtuin="";
	String msg;
	String isnhanHang;
	String trangthai;
	String Loaidoituong;
	
	String Loaikhonhan;
	String muahangId;
	String task;
	
    String LENHDIEUDONG="";
	String NGAYDIEUDONG="";
	String CUA="";
	String VEVIEC="";
	String NGAYCHUYEN="";
	String NGUOICHUYEN="";
	String PHUONGTIEN="";
	String khoXuatId;
	
	String   SOHOPDONG="";
	ResultSet khoXuatRs;
	
	String khoNhanId;
	ResultSet khoNhanRs;

	String loaichuyenkygui;
	String loainhankygui;
	
	String phongbanid;
	
	ResultSet PhongbanRs;
	
	String nccXuatId;
	ResultSet nccXuatRs;
	
	String nccNhanId;
	ResultSet nccNhanRs;
	
	
	String KhachhangId;
	ResultSet KhachhangRs;
	
	String KhachhangKyguiId;
	ResultSet KhachhangKyguiRs;
	
	
	String NhanvienKyguiId;
	ResultSet NhanvienKyguiRs;
	
	String lsxIds;
	ResultSet lsxRs;
	
	String CheckNhanHang;
	
	String NhanvienId;
	ResultSet RsNhanVien;
	
	
	List<IYeucau> spList;
	List<IYeucau> spChoNhapList;
	
	List<IKhu_Vitri> khuList;
	List<IKhu_Vitri> vitriList;
	
	String ndxId;
	ResultSet ndxRs;
	
	String trangthaisp;  // -1 khong dat, 1 dat
	dbutils db;
	NumberFormat formatter1 = new DecimalFormat("######.###");
	
	// 28/12/2015, thêm tổng thể tích, tổng khối lượng
	Double tongthetich;
	Double tongkhoiluong;
	
	public ErpChuyenkhoSX()
	{
		this.id = "";
		this.ngayyeucau = getDateTime();
		this.lydoyeucau = "";
		this.PoLsx="";
		this.khoXuatId = "";
		this.khoNhanId = "";
		this.lsxIds = "";
		this.msg = "";
		this.NhanvienKyguiId="";
		this.Loaikhonhan="";
		this.Ngaydenghi= getDateTime();
		this.isnhanHang = "0";
		this.trangthai = "0";
		this.trangthaisp = "";
		this.KhachhangKyguiId="";
		this.task="";
		this.NhanvienId="";
		this.Kyhieu="";
		this.sochungtuin="";
		this.CheckNhanHang="";
		this.KhachhangId="";
		this.Loaidoituong="";
		this.spList = new ArrayList<IYeucau>();
		this.spChoNhapList = new ArrayList<IYeucau>();
		this.khuList = new ArrayList<IKhu_Vitri>();
		this.vitriList = new ArrayList<IKhu_Vitri>();
		this.loaichuyenkygui = "";
		this.loainhankygui = "";
		this.nccXuatId = "";
		this.nccNhanId = "";
		this.ndxId = "";
		this.muahangId="";
		
		this.tongkhoiluong = (double) 0;
		this.tongthetich = (double) 0;
		this.db = new dbutils();
	}
	
	public ErpChuyenkhoSX(String id)
	{
		this.id = id;
		this.ngayyeucau = getDateTime();
		this.lydoyeucau = "";
		this.khoXuatId = "";
		this.Ngaydenghi= getDateTime();
		this.khoNhanId = "";
		this.Loaidoituong="";
		this.task="";
		this.lsxIds = "";
		this.muahangId="";
		this.msg = "";
		this.isnhanHang = "0";
		this.trangthai = "0";
		this.KhachhangKyguiId="";
		this.trangthaisp = "";
		this.PoLsx="";
		this.NhanvienId="";
		this.KhachhangId="";
		this.NhanvienKyguiId="";
		this.spList = new ArrayList<IYeucau>();
		this.spChoNhapList = new ArrayList<IYeucau>();
		
		this.khuList = new ArrayList<IKhu_Vitri>();
		this.vitriList = new ArrayList<IKhu_Vitri>();

		
		this.ndxId = "";
		this.loaichuyenkygui = "";
		this.loainhankygui = "";
		this.nccXuatId = "";
		this.nccNhanId = "";
		
		this.tongkhoiluong = (double) 0;
		this.tongthetich = (double) 0;
		
		this.db = new dbutils();
	}
	
	public Double getTongthetich() {
		return tongthetich;
	}

	public void setTongthetich(Double tongthetich) {
		this.tongthetich = tongthetich;
	}

	public Double getTongkhoiluong() {
		return tongkhoiluong;
	}

	public void setTongkhoiluong(Double tongkhoiluong) {
		this.tongkhoiluong = tongkhoiluong;
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

	public String getLydoyeucau() 
	{
		return this.lydoyeucau;
	}

	public void setLydoyeucau(String lydoyeucau) 
	{
		this.lydoyeucau = lydoyeucau;
	}

	public String getKhoXuatId() 
	{
		return this.khoXuatId;
	}

	public void setKhoXuatId(String khoxuattt) 
	{
		this.khoXuatId = khoxuattt;
	}

	public ResultSet getKhoXuatRs()
	{
		return this.khoXuatRs;
	}

	public void setKhoXuatRs(ResultSet khoxuatRs) 
	{
		this.khoXuatRs = khoxuatRs;
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
 
	public String getLoaiChuyenKyGui()
	{
		return this.loaichuyenkygui;
	}
	
	public void setLoaiChuyenKyGui(String loaichuyenkygui) {
		this.loaichuyenkygui = loaichuyenkygui;
	}

	public String getLoaiNhanKyGui()
	{
		return this.loainhankygui;
	}
	
	public void setLoaiNhanKyGui(String loainhankygui) {
		this.loainhankygui = loainhankygui;
	}
	
	public List<IYeucau> getSpList()
	{
		return this.spList;
	}

	public void setSpList(List<IYeucau> spList) 
	{
		this.spList = spList;
	}

	public String getMsg() 
	{
		return this.msg;
	}

	public void setMsg(String msg) 
	{
		this.msg = msg;
	}

	
	public boolean createCK() 
	{
		if(this.ngayyeucau.trim().length() <= 0)
		{
			this.msg = "Vui lòng nhập ngày yêu cầu";
			return false;
		}
		
		if(this.ndxId.trim().length() <= 0)
		{
			this.msg = "Vui lòng chọn nội dung xuất";
			return false;
		}
		
		if(this.khoXuatId.trim().length() <= 0)
		{
			this.msg = "Vui lòng chọn kho xuất";
			return false;
		}
		if(this.ndxId.equals("100024")|| this.ndxId.equals("100009")||this.ndxId.equals("100020")|| this.ndxId.equals("100006")) 
		{
			if(this.khoNhanId.trim().length() <= 0)
			{
				this.msg = "Vui lòng chọn kho nhận";
				return false;
			}
		}
		
		//neu la kho nho gia cong thi phai co NCC nhờ gia c
		if( this.khoXuatId.trim().equals("100013") )
		{
			if(this.nccXuatId.trim().length() <= 0)
			{
				this.msg = "Vui lòng chọn nhà cung cấp chuyển";
				return false;
			}
		}
		else if( this.khoXuatId.trim().equals("100015") ) {
			if(this.loaichuyenkygui.trim().length() <= 0) {
				this.msg = "Vui lòng chọn chuyển ký gửi cho khách hàng hoặc nhân viên";
				return false;
			} else if(this.loaichuyenkygui.equals("0")) {
				if(this.nccXuatId.trim().length() <= 0)
				{
					this.msg = "Vui lòng chọn khách hàng chuyển ký gửi";
					return false;
				}
			} else if(this.loaichuyenkygui.equals("1")) {
				if(this.nccXuatId.trim().length() <= 0)
				{
					this.msg = "Vui lòng chọn nhân viên chuyển ký gửi";
					return false;
				}
			}
			
		}
		String ncc_nhan_fk =null;
		
		if( this.khoNhanId.trim().equals("100013") )// || this.khoNhanId.trim().equals("100011") )
		{
			if(this.nccNhanId.trim().length() <= 0)
			{
				this.msg = "Vui lòng chọn nhà cung cấp nhận";
				return false;
			}
			ncc_nhan_fk=this.nccNhanId;
		}
		else if( this.khoNhanId.trim().equals("100015") ) 
		{
			if(this.loainhankygui.trim().length() <= 0) {
				this.msg = "Vui lòng chọn nhận ký gửi cho khách hàng hoặc nhân viên";
				return false;
			} else if(this.loainhankygui.equals("0")) {
				if(this.KhachhangKyguiId.trim().length() <= 0)
				{
					
					this.msg = "Vui lòng chọn khách hàng nhận ký gửi";
					return false;
				} 
				ncc_nhan_fk=this.KhachhangKyguiId;
				
			} else if(this.loainhankygui.equals("1")) {
				if(this.NhanvienKyguiId.trim().length() <= 0)
				{
					this.msg = "Vui lòng chọn nhân viên nhận ký gửi";
					return false;
				}
				ncc_nhan_fk=NhanvienKyguiId;
				
			} 
		}
		String loaikhonhan = "";
		String npp_fk = "";
		
		//100006	CHUYỂN KHO BÊN NGOÀI
		/*if(this.spList.size() >15 && this.ndxId.equals("100006") && this.muahangId.length() >2){
			this.msg = "Vui lòng chỉ tạo phiếu tối đa 15 sản phẩm cho phiếu chuyển kho bên ngoài";
			return false;
		}*/
		
		if(this.spList.size() <= 0)
		{
			this.msg = "Không có sản phẩm nào được chọn";
			return false;
		} 
		else
		{
			//Check trung san pham + 1
			for(int i = 0; i < this.spList.size() - 1; i++)
			{
				IYeucau yc = this.spList.get(i);
				for(int j = i + 1; j < this.spList.size(); j++)
				{
					IYeucau yc2 = this.spList.get(j);
					
					if( yc.getId().trim().equals(yc2.getId().trim()) && yc.getSolo().trim().equals(yc2.getSolo().trim()) )
					{
						this.msg = "Sản phẩm ( " + yc.getTen() + " ) đã trùng số lô. Vui lòng kiểm tra lại.";
						return false;
					}
					
				}
			}
			
		/*	100024	XC13	Xuất đổi hàng
			100009	XC06	Chuyển kho nội bộ
			100006	XC03	Chuyển kho bên ngoài*/
			
				String sqlkhoxuat = " SELECT LOAI, NPP_FK,TRUNGTAMPP_FK FROM ERP_KHOTT WHERE pk_seq = " + this.khoXuatId;
			
				ResultSet rskhoxuat = db.get(sqlkhoxuat);
			 
				 	try{
						String loaikhoxuat=""; 
						if(rskhoxuat.next()){
							loaikhoxuat = rskhoxuat.getString("LOAI").trim();
							
							if(loaikhoxuat.equals("9")){
								if(!this.khoNhanId.trim().equals(rskhoxuat.getString("TRUNGTAMPP_FK"))){
									 this.msg= "Kho nhận chỉ được là kho trung tâm chi phí của kho chờ xử lý này,vui lòng chọn lại kho nhận";
									 return false;
								}
							}
							
						}else{
							 this.msg= "Không xác định kho xuất,vui lòng kiểm tra lại";
							 return false;
						}
						rskhoxuat.close();
				 	}catch(Exception err){
				 		
				 		err.printStackTrace();
				 		 this.msg= err.getMessage();
						 return false;
				 	}
				 	
		 
			// NẾU XUẤT ĐỔI HÀNG || CHUYỂN KHO NỘI BỘ || CHUYỂN KHO KÝ GỬI || CHUYỂN KHO BÊN NGOÀI
			
			if(this.ndxId.equals("100024")|| this.ndxId.equals("100009")||this.ndxId.equals("100020")|| this.ndxId.equals("100006")){
					String sqlLoai = " SELECT LOAI, NPP_FK FROM ERP_KHOTT WHERE pk_seq = " + this.khoNhanId;
				
					ResultSet rsLoai = db.get(sqlLoai);
					if(rsLoai != null) {
						try {
							rsLoai.next();
							loaikhonhan = rsLoai.getString("LOAI");
							npp_fk = rsLoai.getString("NPP_FK");
							rsLoai.close();
						} catch(Exception e) {
							this.msg = "Xảy ra lỗi khi lấy thông tin kho nhận (" + e.getMessage() + ")";
							return false;
						}
					} else {
						this.msg = "Xảy ra lỗi khi lấy thông tin kho nhận";
						return false;
					}
			}else{
				this.khoNhanId="";
				
			}
			
			boolean flag = false;
			for(int i = 0; i < this.spList.size(); i++)
			{
				IYeucau yc = this.spList.get(i);
				if(yc.getSoluongYC().trim().length() > 0)
				{
					flag = true;
					
					if(this.khoNhanId.trim().length() > 0) //Check kho nhan + san pham phai duoc thiet lap trong du lieu nen KHO_SANPHAM
					{
						
						
							String sqlCheck = "select count(*) as sodong from ERP_KHOTT_SANPHAM where khott_fk = '" + this.khoNhanId + "' and sanpham_fk = '" + yc.getId() + "' ";
							int sodong = 0;
							ResultSet rsCheck = db.get(sqlCheck);
							try 
							{
								if(rsCheck.next())
								{
									sodong = rsCheck.getInt("sodong");
								}
								rsCheck.close();
							} 
							catch (Exception e) {}
							
							if(sodong <= 0)
							{
								this.msg = "Sản phẩm ( " + yc.getTen() + " ) bạn yêu cầu chưa được khai báo trong dữ liệu nền kho nhận.";
								return false;
							}
						//}
					}
					

					String tonkho = yc.getTonhientai().trim().length() > 0 ? yc.getTonhientai().trim() : "0" ;
					String soluong = yc.getSoluongYC().trim().length() > 0 ? yc.getSoluongYC().trim() : "0" ;
					
					/*if( Float.parseFloat(soluong) >  Float.parseFloat(tonkho) )
					{
						this.msg = "Số lượng chuyển không được phép vượt quá số lượng hiện tại trong kho.";
						return false;
					}*/
				}
			}
 
			if(!flag)
			{
				this.msg = "Không có sản phẩm nào được nhập số lượng.";
				return false;
			}
			
		}
 
		try
		{
			db.getConnection().setAutoCommit(false);
			
			String loaichuyenkygui = this.loaichuyenkygui.trim().length() <= 0 ? "null" : this.loaichuyenkygui.trim();
			String loainhankygui = this.loainhankygui.trim().length() <= 0 ? "null" : this.loainhankygui.trim();
			String khonhan_fk = this.khoNhanId.trim().length() <= 0 ? "null" : this.khoNhanId.trim();
			String ncc_xuat_fk = this.nccXuatId.trim().length() <= 0 ? "null" : this.nccXuatId.trim();
			 
			if(this.ndxId.trim().equals("100006")){
					String query=" select max( cast(SOCHUNGTUIN as float))  as somax from  ERP_YEUCAUCHUYENKHO where    NOIDUNGXUAT_FK=100006  and KYHIEU like '%PC/16T%' AND IS_SOCHUNGTUIN_NEW LIKE '1'";
					
					String chuoi= "";
					ResultSet rs=db.get(query);
					
					if(rs.next()){
					   // Lấy SOCHUNGTUIN max trong CHUYENKHO
						String sql = " select max( cast(SOCHUNGTUIN as float))  as somax_CK from  ERP_CHUYENKHO where    NOIDUNGXUAT_FK=100006 and KYHIEU like '%PC/16T%' AND IS_SOCHUNGTUIN_NEW LIKE '1'";
						int soin_CK = 0;
						ResultSet rsCK=db.get(sql);
						if(rsCK.next())
						{
							soin_CK = rsCK.getInt("somax_CK");
														
							if(rs.getInt("somax") >= soin_CK) // NẾU "SOCHUNGTUIN" TRONG YEUCAUCHUYENKHO >= CHUYENKHO 
							{
								chuoi=("000000"+ (rs.getLong("somax")>0 ? (rs.getLong("somax")+1) :"1"));
							}
							else
							{
								chuoi=("000000"+ (rsCK.getLong("somax_CK")>0 ? (rsCK.getLong("somax_CK")+1) :"1"));
							}
						}

						chuoi=chuoi.substring(chuoi.length()-7,chuoi.length());
						this.sochungtuin=chuoi;
					}
			}
			
			 
			
			 String query = " insert ERP_YEUCAUCHUYENKHO(IS_SOCHUNGTUIN_NEW, SOCHUNGTUIN,KYHIEU,NPP_FK,NGAYYEUCAU,NGAYDIEUDONG,COXACNHANNHAPKHO,LENHDIEUDONG,LENHDIEUDONGCUA,VEVIEC,NGUOIVANCHUYEN,PHUONGTIEN ,LOAIDOITUONG,PHONGBAN_FK,NHANVIEN_FK,noidungxuat_fk, ngaychuyen, ngaynhan, ngaychot, lydo, trangthai, khoxuat_fk, khonhan_fk, trangthaisp, ngaytao, nguoitao, ngaysua, nguoisua, NCC_CHUYEN_FK, NCC_NHAN_FK, LOAICHUYENKYGUI, LOAINHANKYGUI ) " +
							" values('1', '"+this.sochungtuin+"',N'"+this.Kyhieu+"',"+(this.KhachhangId.length()>0?this.KhachhangId:"null")+",'"+this.Ngaydenghi+"','"+this.NGAYDIEUDONG+"','"+this.CheckNhanHang+"',N'"+this.LENHDIEUDONG+"',N'"+this.CUA+"',N'"+this.VEVIEC+"',N'"+this.NGUOICHUYEN+"',N'"+this.PHUONGTIEN+"','"+this.Loaidoituong+"',"+(this.phongbanid.length() >0? this.phongbanid:"Null" )+","+(this.NhanvienId.length() >0? this.NhanvienId:"Null" )+"" +
							" ,'" + this.ndxId + "', '" + this.ngayyeucau + "', '" + this.ngayyeucau + "', '" + this.ngayyeucau + "'," +
							" N'" + this.lydoyeucau + "', '0', '" + this.khoXuatId + "', " + khonhan_fk + ", '" + this.trangthaisp + "'," +
							" '" + getDateTime() + "', '" + this.userId + "', '" + getDateTime() + "', '" + this.userId + "', " + ncc_xuat_fk + ", " + ncc_nhan_fk + ", " + loaichuyenkygui + ", " + loainhankygui + ")";
  
			if(!db.update(query))
			{
				this.msg = "Không thể tạo mới ERP_YEUCAUCHUYENKHO " + query;
				db.getConnection().rollback();
				return false;
			}
  
			String ycnlCurrent = "";
			query = "select IDENT_CURRENT('ERP_YEUCAUCHUYENKHO') as ckId";
			
			ResultSet rsPxk = db.get(query);						
			if(rsPxk.next())
			{
				ycnlCurrent = rsPxk.getString("ckId");
				rsPxk.close();
			}
 
			if(this.spList.size() > 0)
			{
				for(int i = 0; i < this.spList.size(); i++)
				{
					IYeucau sp = this.spList.get(i);
					
					query = "insert ERP_YEUCAUCHUYENKHO_SANPHAM(chuyenkho_fk, SANPHAM_FK, SOLUONGYEUCAU,SOLUONGXUAT,SOLUONGNHAN, vitrixuat,NGAYYEUCAU) " +
					"values( '" + ycnlCurrent + "', '" + sp.getId() + "', '" + sp.getSoluongYC() + "','" + sp.getSoluongYC() + "','" + sp.getSoluongYC() + "', '100000','"+sp.getNgayyeucau()+"' ) ";
			
					if(!db.update(query))
					{
						this.msg = "Khong the tao moi ERP_YEUCAUCHUYENKHO_SANPHAM: " + query;
						db.getConnection().rollback();
						return false;
					}
				}
			}
			
			db.getConnection().commit();
			db.getConnection().setAutoCommit(true);
		} 
		catch (Exception e) 
		{
			e.printStackTrace();
			db.update("rollback");
			this.msg = "Exception: " + e.getMessage();
			return false;
		}
		
		return true;
	}

	public boolean updateCK() 
	{
		if(this.ngayyeucau.trim().length() <= 0)
		{
			this.msg = "Vui lòng nhập ngày yêu cầu";
			return false;
		}
		
		if(this.ndxId.trim().length() <= 0)
		{
			this.msg = "Vui lòng chọn nội dung xuất";
			return false;
		}
		
		if(this.khoXuatId.trim().length() <= 0)
		{
			this.msg = "Vui lòng chọn kho xuất";
			return false;
		}
		if(this.ndxId.equals("100024")|| this.ndxId.equals("100009")||this.ndxId.equals("100020")|| this.ndxId.equals("100006")) 
		{
			if(this.khoNhanId.trim().length() <= 0)
			{
				this.msg = "Vui lòng chọn kho nhận";
				return false;
			}
		}
		
		/*if(this.spList.size() >15 && this.ndxId.equals("100006")){
			this.msg = "Vui lòng chỉ tạo phiếu tối đa 15 sản phẩm cho phiếu chuyển kho bên ngoài";
			return false;
		}*/
		
		//neu la kho nho gia cong thi phai co NCC nhờ gia c
		if( this.khoXuatId.trim().equals("100013") )// || this.khoXuatId.trim().equals("100011"))
		{
			if(this.nccXuatId.trim().length() <= 0)
			{
				this.msg = "Vui lòng chọn nhà cung cấp chuyển";
				return false;
			}
		}
		else if( this.khoXuatId.trim().equals("100015") ) {
			if(this.loaichuyenkygui.trim().length() <= 0) {
				this.msg = "Vui lòng chọn chuyển ký gửi cho khách hàng hoặc nhân viên";
				return false;
			} else if(this.loaichuyenkygui.equals("0")) {
				if(this.nccXuatId.trim().length() <= 0)
				{
					this.msg = "Vui lòng chọn khách hàng chuyển ký gửi";
					return false;
				}
			} else if(this.loaichuyenkygui.equals("1")) {
				if(this.nccXuatId.trim().length() <= 0)
				{
					this.msg = "Vui lòng chọn nhân viên chuyển ký gửi";
					return false;
				}
			}
			
		}
		String ncc_nhan_fk =null;
		
		if( this.khoNhanId.trim().equals("100013") )// || this.khoNhanId.trim().equals("100011") )
		{
			if(this.nccNhanId.trim().length() <= 0)
			{
				
				this.msg = "Vui lòng chọn nhà cung cấp nhận";
				return false;
			}
			ncc_nhan_fk=this.nccNhanId;
		}
		else if( this.khoNhanId.trim().equals("100015") ) 
		{
			if(this.loainhankygui.trim().length() <= 0) {
				this.msg = "Vui lòng chọn nhận ký gửi cho khách hàng hoặc nhân viên";
				return false;
			} else if(this.loainhankygui.equals("0")) {
				if(this.KhachhangKyguiId.trim().length() <= 0)
				{
					
					this.msg = "Vui lòng chọn khách hàng nhận ký gửi";
					return false;
				} 
				ncc_nhan_fk=this.KhachhangKyguiId;
				
			} else if(this.loainhankygui.equals("1")) {
				if(this.NhanvienKyguiId.trim().length() <= 0)
				{
					this.msg = "Vui lòng chọn nhân viên nhận ký gửi";
					return false;
				}
				ncc_nhan_fk=NhanvienKyguiId;
				
			} 
		}
		String loaikhonhan = "";
		String npp_fk = "";
		if(this.spList.size() <= 0)
		{
			this.msg = "Không có sản phẩm nào được chọn";
			return false;
		}
		else
		{
			//Check trung san pham + 1
			for(int i = 0; i < this.spList.size() - 1; i++)
			{
				IYeucau yc = this.spList.get(i);
				for(int j = i + 1; j < this.spList.size(); j++)
				{
					IYeucau yc2 = this.spList.get(j);
					
					if( yc.getId().trim().equals(yc2.getId().trim()) && yc.getSolo().trim().equals(yc2.getSolo().trim()) )
					{
						this.msg = "Sản phẩm ( " + yc.getTen() + " ) đã trùng số lô. Vui lòng kiểm tra lại.";
						return false;
					}
					
				}
			}
			
		/*	100024	XC13	Xuất đổi hàng
			100009	XC06	Chuyển kho nội bộ
			100020	XC10	Chuyển kho ký gửi
			100006	XC03	Chuyển kho bên ngoài*/
			
			String sqlkhoxuat = " SELECT LOAI, NPP_FK,TRUNGTAMPP_FK FROM ERP_KHOTT WHERE pk_seq = " + this.khoXuatId;
			
			ResultSet rskhoxuat = db.get(sqlkhoxuat);
		 
			 	try{
					String loaikhoxuat=""; 
					if(rskhoxuat.next()){
						loaikhoxuat = rskhoxuat.getString("LOAI").trim();
						
						if(loaikhoxuat.equals("9")){
							if(!this.khoNhanId.trim().equals(rskhoxuat.getString("TRUNGTAMPP_FK"))){
								 this.msg= "Kho nhận chỉ được là kho trung tâm chi phí của kho chờ xử lý này,vui lòng chọn lại kho nhận";
								 return false;
							}
						}
						
					}else{
						 this.msg= "Không xác định kho xuất,vui lòng kiểm tra lại";
						 return false;
					}
					rskhoxuat.close();
			 	}catch(Exception err){
			 		
			 		err.printStackTrace();
			 		 this.msg= err.getMessage();
					 return false;
			 	}
			 	
	  
			if(this.ndxId.equals("100024")|| this.ndxId.equals("100009")||this.ndxId.equals("100020")|| this.ndxId.equals("100006")){
					String sqlLoai = "SELECT LOAI, NPP_FK FROM ERP_KHOTT WHERE pk_seq = " + this.khoNhanId;
				
					ResultSet rsLoai = db.get(sqlLoai);
					if(rsLoai != null) {
						try {
							rsLoai.next();
							loaikhonhan = rsLoai.getString("LOAI");
							npp_fk = rsLoai.getString("NPP_FK");
							rsLoai.close();
						} catch(Exception e) {
							this.msg = "Xảy ra lỗi khi lấy thông tin kho nhận (" + e.getMessage() + ")";
							return false;
						}
					} else {
						this.msg = "Xảy ra lỗi khi lấy thông tin kho nhận";
						return false;
					}
			}else{
				this.khoNhanId="";
				
			}
			
			boolean flag = false;
			for(int i = 0; i < this.spList.size(); i++)
			{
				IYeucau yc = this.spList.get(i);
				if(yc.getSoluongYC().trim().length() > 0)
				{
					flag = true;
					
					if(this.khoNhanId.trim().length() > 0) //Check kho nhan + san pham phai duoc thiet lap trong du lieu nen KHO_SANPHAM
					{
						 
							String sqlCheck = "select count(*) as sodong from ERP_KHOTT_SANPHAM where khott_fk = '" + this.khoNhanId + "' and sanpham_fk = '" + yc.getId() + "' ";
							int sodong = 0;
							ResultSet rsCheck = db.get(sqlCheck);
							try 
							{
								if(rsCheck.next())
								{
									sodong = rsCheck.getInt("sodong");
								}
								rsCheck.close();
							} 
							catch (Exception e) {}
							
							if(sodong <= 0)
							{
								this.msg = "Sản phẩm ( " + yc.getTen() + " ) bạn yêu cầu chưa được khai báo trong dữ liệu nền kho nhận.";
								return false;
							}
				 
					}
					

					String tonkho = yc.getTonhientai().trim().length() > 0 ? yc.getTonhientai().trim() : "0" ;
					String soluong = yc.getSoluongYC().trim().length() > 0 ? yc.getSoluongYC().trim() : "0" ;
					
					/*if( Float.parseFloat(soluong) >  Float.parseFloat(tonkho) )
					{
						this.msg = "Số lượng chuyển không được phép vượt quá số lượng hiện tại trong kho.";
						return false;
					}*/
				}
			}
 
			if(!flag)
			{
				this.msg = "Không có sản phẩm nào được nhập số lượng.";
				return false;
			}
			
		}
		
		try
		{
			db.getConnection().setAutoCommit(false);
			
			String khonhan_fk = this.khoNhanId.trim().length() <= 0 ? "null" : this.khoNhanId.trim();
			String ncc_xuat_fk = this.nccXuatId.trim().length() <= 0 ? "null" : this.nccXuatId.trim();
 
			if(this.ndxId.trim().equals("100006")){
				
				String query="SELECT isnull(SOCHUNGTUIN,'0') as SOCHUNGTUIN FROM ERP_YEUCAUCHUYENKHO WHERE PK_SEQ= "+this.id;
				
				ResultSet rs=db.get(query);
				rs.next();
				if(rs.getString("SOCHUNGTUIN").length() >2){
					this.sochungtuin=rs.getString("SOCHUNGTUIN");
				}else{
						query="select max( cast(SOCHUNGTUIN as float))   as somax from  ERP_YEUCAUCHUYENKHO where    NOIDUNGXUAT_FK=100006  AND IS_SOCHUNGTUIN_NEW LIKE '1'";
						  rs=db.get(query);
						
						if(rs.next()){
						 
							String chuoi=("000000"+ (rs.getLong("somax")>0 ? rs.getLong("somax")+1 :"1"));
							
							chuoi=chuoi.substring(chuoi.length()-7,chuoi.length());
							this.sochungtuin=chuoi;
						}
				}
		}
			
			
			String  query =  " Update ERP_YEUCAUCHUYENKHO set  SOCHUNGTUIN='"+this.sochungtuin+"', kyhieu=N'"+this.Kyhieu+"',npp_fk="+(this.KhachhangId.length()>0?this.KhachhangId:"null")+",Ngayyeucau='"+this.Ngaydenghi+"',NGAYDIEUDONG='"+this.NGAYDIEUDONG+"',COXACNHANNHAPKHO='"+this.CheckNhanHang+"',LENHDIEUDONG=N'"+this.LENHDIEUDONG+"',LENHDIEUDONGCUA=N'"+this.CUA+"' " +
							" ,NOIDUNGXUAT_FK="+this.ndxId+",VEVIEC=N'"+this.VEVIEC+"',NGUOIVANCHUYEN=N'"+this.NGUOICHUYEN+"',PHUONGTIEN=N'"+this.PHUONGTIEN+"' ,LOAIDOITUONG='"+this.Loaidoituong+"',PHONGBAN_FK ="+(this.phongbanid.length()==0? "null":this.phongbanid) +",NHANVIEN_FK="+(this.NhanvienId.length()==0?"null":this.NhanvienId) +" , " +
							"  ngaychuyen = '" + this.ngayyeucau + "', ngaynhan = '" + this.ngayyeucau + "', lydo = N'" + this.lydoyeucau + "', khoxuat_fk = '" + this.khoXuatId + "'," +
							"  khonhan_fk = " + khonhan_fk + ", ngaysua = '" + getDateTime() + "', nguoisua = '" + this.userId + "', trangthaisp = '" + this.trangthaisp + "', NCC_CHUYEN_FK = " + ncc_xuat_fk + ", NCC_NHAN_FK = " + ncc_nhan_fk + 
							"  where pk_seq = '" + this.id + "' ";
							
			if(!db.update(query))
			{
				this.msg = "Không thể cập nhật ERP_YEUCAUCHUYENKHO " + query;
				db.getConnection().rollback();
				return false;
			}
 
			query = "delete ERP_YEUCAUCHUYENKHO_SANPHAM where chuyenkho_fk = '" + this.id + "'";
			if(!db.update(query))
			{
				this.msg = "Không thể cập nhật ERP_YEUCAUCHUYENKHO_SANPHAM " + query;
				db.getConnection().rollback();
				return false;
			}
			
			if(this.spList.size() > 0)
			{
				for(int i = 0; i < this.spList.size(); i++)
				{
					IYeucau sp = this.spList.get(i);
 
					if(sp.getSoluongYC().trim().length() > 0)
					{
						query = "insert ERP_YEUCAUCHUYENKHO_SANPHAM(chuyenkho_fk, SANPHAM_FK, SOLUONGYEUCAU,SOLUONGXUAT,SOLUONGNHAN, vitrixuat,ngayyeucau) " +
						"values( '" + this.id + "', '" + sp.getId() + "', '" + sp.getSoluongYC() + "','" + sp.getSoluongYC() + "',"+sp.getSoluongYC()+", '100000','"+sp.getNgayyeucau()+"' ) ";
						 if(!db.update(query))
						{
							this.msg = "Khong the tao moi ERP_YEUCAUCHUYENKHO_SANPHAM: " + query;
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
			e.printStackTrace();
			db.update("rollback");
			this.msg = "Exception: " + e.getMessage();
			return false;
		}
		
		return true;
	}

	public boolean nhanHang() 
	{
		if(this.ngayyeucau.trim().length() <= 0)
		{
			this.msg = "Vui lòng nhập ngày nhận hàng";
			return false;
		}
		
		if(this.spList.size() <= 0)
		{
			this.msg = "Không có sản phẩm nào được chọn";
			return false;
		}
		else
		{
			boolean flag = false;
			for(int i = 0; i < this.spList.size(); i++)
			{
				IYeucau yc = this.spList.get(i);
				if(yc.getSoluongNhan().trim().length() > 0)
				{
					flag = true;
				
					String soluongNhan = yc.getSoluongNhan().trim().length() > 0 ? yc.getSoluongNhan().trim() : "0" ;
					String soluongChuyen = yc.getSoluongYC().trim().length() > 0 ? yc.getSoluongYC().trim() : "0" ;
					
					if( Float.parseFloat(soluongNhan) >  Float.parseFloat(soluongChuyen) )
					{
						this.msg = "Số lượng nhận không được phép vượt quá số lượng chuyển.";
						return false;
					}
				}
			}
			
			//
			if(!flag)
			{
				this.msg = "Không có sản phẩm nào được nhập số lượng.";
				return false;
			}
			
		}
		
		try
		{
			db.getConnection().setAutoCommit(false);
			
			String query = "Update ERP_CHUYENKHO set ngaynhan = '" + this.ngayyeucau + "', ngaysua = '" + getDateTime() + "', nguoisua = '" + this.userId + "', trangthai = '2' " +
							"where pk_seq = '" + this.id + "' ";
							
			if(!db.update(query))
			{
				this.msg = "Không thể cập nhật ERP_CHUYENKHO " + query;
				db.getConnection().rollback();
				return false;
			}
			
			
			query = "update ERP_CHUYENKHO_SANPHAM set SOLUONGNHAN = '0' where CHUYENKHO_FK = '" + this.id + "'";
			if(!db.update(query))
			{
				this.msg = "Không thể cập nhật ERP_CHUYENKHO_SANPHAM " + query;
				db.getConnection().rollback();
				return false;
			}
			
			 
			
			if(this.spList.size() > 0)
			{
				for(int i = 0; i < this.spList.size(); i++)
				{
					IYeucau sp = this.spList.get(i);
					
					//Update tong nhap
					if(sp.getSoluongNhan().trim().length() > 0)
					{
						query = "update ERP_CHUYENKHO_SANPHAM set SOLUONGNHAN = SOLUONGNHAN + '" + sp.getSoluongNhan() + "' " +
								"where SANPHAM_FK = '" + sp.getId() + "' and SOLO = '" + sp.getSolo() + "' and CHUYENKHO_FK = '" + this.id + "'";
						
						if(!db.update(query))
						{
							this.msg = "Không thể cập nhật ERP_CHUYENKHO_SANPHAM " + query;
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
	
	
	public boolean xuatHang() 
	{
		try
		{
			if(this.ngayyeucau.trim().length() <= 0)
			{
				this.msg = "Vui lòng nhập ngày xuất hàng";
				return false;
			}
			
			if(this.spList.size() <= 0)
			{
				this.msg = "Không có sản phẩm nào được chọn";
				return false;
			}
			if(this.khoXuatId.length()== 0){
				this.msg = "Vui lòng thử lại, không lấy được kho xuất";
				return false;
			}
			
		 	String query="select isnull(muahang_fk,0) as  MUAHANG_FK FROM ERP_CHUYENKHO WHERE PK_SEQ="+this.id;
		 	//System.out.println("Query  : "+query);
			ResultSet rs=db.get(query);
			if(rs.next()){
				this.muahangId=rs.getString("muahang_fk");
			}
			System.out.println("Mua hang Id: "+this.muahangId);
			rs.close();
			String msg_tonkho="";
				boolean flag = false;
				int bien_check=0;
				for(int i = 0; i < this.spList.size(); i++)
				{
					IYeucau yc = this.spList.get(i);
					
					String tb = Check_TonKhoNgay_Sp(db, this.khoXuatId, yc.getId(), Double.parseDouble(yc.getTongSoluongDaXuat()), this.ngayyeucau,this.loaichuyenkygui,this.nccXuatId);
					
					if(tb.trim().length() > 0)
					{
						this.msg = tb;
						return false;
					}
					
					double tongsoluongxuat_=0;
					try{
						tongsoluongxuat_ =Double.parseDouble(yc.getTongSoluongDaXuat().replaceAll(",", ""));
						
					}catch(Exception er){
						er.printStackTrace();
					}
					
					System.out.println("tongsoluongxuat_: "+tongsoluongxuat_);
					
					if(tongsoluongxuat_ > 0)
					{
						bien_check++;
						
						flag = true;
					
						String soluongxuat = yc.getTongSoluongDaXuat().trim().length() > 0 ? yc.getTongSoluongDaXuat().trim() : "0" ;
						String soluongChuyen = yc.getSoluongYC().trim().length() > 0 ? yc.getSoluongYC().trim() : "0" ;
						
						if( Float.parseFloat(soluongxuat) >  Float.parseFloat(soluongChuyen) )
						{
							if (this.muahangId.equals("0")){
								this.msg = "Sản phẩm: "+yc.getMa()+".  Số lượng xuất không được phép vượt quá số lượng yêu cầu. số lượng yeucau : " +soluongChuyen +", Số lượng xuất  : " +soluongxuat;
								return false;
							}
						}
						
						query=  " SELECT AVAILABLE ,booked , ISNULL((SELECT SUM(SOLUONG) FROM ERP_CHUYENKHO_SP_XUATHANG CK_SP "+
								" INNER JOIN ERP_CHUYENKHO CK ON CK.PK_SEQ=CK_SP.CHUYENKHO_FK "+
								" WHERE CK.PK_SEQ="+this.id+" AND CK_SP.SANPHAM_FK= KHO.SANPHAM_FK),0)  as SOLUONGDABOOKED "+
								" ,SP.MA +' - '+ SP.TEN AS TEN FROM ERP_KHOTT_SANPHAM KHO "+
								" INNER JOIN SANPHAM SP ON SP.PK_SEQ=SANPHAM_FK  "+
								" WHERE   KHOTT_FK= "+this.khoXuatId+" AND SANPHAM_FK ="+yc.getId();
						//System.out.println("Query: "+query);
						rs=db.get(query);
						double soluongtonkho=0;
						if(rs.next()){
								soluongtonkho= rs.getDouble("AVAILABLE")+rs.getDouble("SOLUONGDABOOKED");
								if( Double.parseDouble(soluongxuat) >  soluongtonkho){
									msg_tonkho=msg_tonkho+ " \n Số lượng tồn kho của sản phẩm "+rs.getString("ten")+" không còn đủ. Số lượng tồn :  "+soluongtonkho+" . Số lượng đã booked :"+rs.getDouble("booked");
								}
						}
						rs.close();
					}
				}
				
				if(this.ndxId.equals("100006")){
					if(bien_check>15){
						this.msg = "Vui lòng chỉ được xuất tối đa 15 sản phẩm cho nội dung xuất kho bên ngoài này. ";
						return false;
					}
				}	
				
				if(!flag)
				{
					this.msg = "Không có sản phẩm nào được nhập số lượng.";
					return false;
				}
				if(msg_tonkho.length() >0){
					this.msg = msg_tonkho;
					return false;
				}
 
			db.getConnection().setAutoCommit(false);
			
			// NEU THAY DOI SL XUAT THI 1.DELETE  ERP_CHUYENKHO_SP_XUATHANG VA 2.INSERT ERP_CHUYENKHO_SP_XUATHANG
			// revert
			  query=   		" SELECT  CK.LOAICHUYENKYGUI  ,CK.NCC_CHUYEN_FK , CK.KHOXUAT_FK,CK_SP.SANPHAM_FK,CK_SP.SOLO,CK_SP.SOLUONG  "+
							" FROM ERP_CHUYENKHO_SP_XUATHANG CK_SP INNER JOIN ERP_CHUYENKHO CK "+
							" ON CK.PK_SEQ=CK_SP.CHUYENKHO_FK   "+
							" WHERE CK.PK_SEQ= "+this.id;
			System.out.println(query);
			
			  rs=db.get(query);
			while (rs.next()){
				
				/*query=" update erp_khott_sanpham set booked=booked -" +rs.getString("soluong") +" ,available=available+"+rs.getString("soluong")+" where khott_fk= "+rs.getString("khoxuat_fk")+" and sanpham_fk="+rs.getString("sanpham_fk");
				if(db.updateReturnInt(query)!= 1)
				{
					this.msg = "Không thể cập nhật ERP_CHUYENKHO_SANPHAM " + query;
					db.getConnection().rollback();
					return false;
				}*/
				
				String spid_= rs.getString("sanpham_fk");
				String khoit_cn= rs.getString("khoxuat_fk");
				double soluongct_=0;
				double booked_ct_=0;
				double avai_ct_=0;
				try{ booked_ct_ = (-1)* Double.parseDouble(rs.getString("soluong").replaceAll(",", ""));}catch(Exception err){}
				try{ avai_ct_ = Double.parseDouble(rs.getString("soluong").replaceAll(",", ""));}catch(Exception err){}
				
				String msg1= Utility_Kho.Update_Kho_Sp_Provence(db, khoit_cn, spid_,  soluongct_,booked_ct_,avai_ct_, 0,this.id,"ErpchyenkhoSx.java 1211");
				
				if(msg1.length() >0 )
				{
					db.getConnection().rollback();
					this.msg= "Lỗi :"+msg1;
					return false;
				}

				
				// kho chi tiết có số lô
				
				query="update erp_khott_sp_chitiet set booked=booked -" +rs.getString("soluong") +" ,available=available+"+rs.getString("soluong")+" " +
						" where rtrim(ltrim(SOLO))='"+rs.getString("solo").trim()+"' and khott_fk= "+rs.getString("khoxuat_fk")+" and sanpham_fk="+rs.getString("sanpham_fk");
				if(db.updateReturnInt(query) != 1)
				{
					this.msg = "Không thể cập nhật ERP_CHUYENKHO_SANPHAM " + query;
					db.getConnection().rollback();
					return false;
				}
				if(this.khoXuatId.trim().equals("100013")){
					
							query="update ERP_KHOTT_SP_CHITIET_NCC set booked=booked -" +rs.getString("soluong") +" ,available=available+ "+rs.getString("soluong")+" " +
							" where NCC_FK ="+rs.getString("NCC_CHUYEN_FK")+"    and  rtrim(ltrim(SOLO))='"+rs.getString("solo").trim()+"' and khott_fk= "+rs.getString("khoxuat_fk")+" and sanpham_fk="+rs.getString("sanpham_fk");
			
							//System.out.println("Cap Nhat KHo chi Tiet: "+query);
							if(db.updateReturnInt(query)!= 1)
							{
								this.msg = "Không thể cập nhật ERP_CHUYENKHO_SANPHAM " + query;
								db.getConnection().rollback();
								return false;
							}
					
				}else{
						if( rs.getString("LOAICHUYENKYGUI")!= null){
							if(rs.getString("LOAICHUYENKYGUI").trim().equals("1")){
								// nhân viên ký gửi
								query="update ERP_KHOTT_SP_CHITIET_KYGUINHANVIEN set booked=booked -" +rs.getString("soluong") +" ,available=available+ "+rs.getString("soluong")+" " +
										" where NHANVIEN_FK="+rs.getString("NCC_CHUYEN_FK")+"    and  rtrim(ltrim(SOLO))='"+rs.getString("solo").trim()+"' and khott_fk= "+rs.getString("khoxuat_fk")+" and sanpham_fk="+rs.getString("sanpham_fk");
								
								//System.out.println("Cap Nhat KHo chi Tiet: "+query);
								if(db.updateReturnInt(query)!= 1)
								{
									this.msg = "Không thể cập nhật ERP_CHUYENKHO_SANPHAM " + query;
									db.getConnection().rollback();
									return false;
								}
								
								 query="update ERP_KHOTT_SANPHAM_KYGUINHANVIEN set booked=booked -" +rs.getString("soluong") +" ,available=available+ "+rs.getString("soluong")+" " +
								" where NHANVIEN_FK="+rs.getString("NCC_CHUYEN_FK")+"    and     khott_fk= "+rs.getString("khoxuat_fk")+" and sanpham_fk="+rs.getString("sanpham_fk");
						
								//System.out.println("Cap Nhat KHo chi Tiet: "+query);
								if(db.updateReturnInt(query)!= 1)
								{
									this.msg = "Không thể cập nhật ERP_CHUYENKHO_SANPHAM " + query;
									db.getConnection().rollback();
									return false;
								}
						
								
							}else{
									query="update ERP_KHOTT_SP_CHITIET_KYGUINPP set booked=booked -" +rs.getString("soluong") +" ,available=available+ "+rs.getString("soluong")+" " +
									" where NPP_FK ="+rs.getString("NCC_CHUYEN_FK")+"    and  rtrim(ltrim(SOLO))='"+rs.getString("solo").trim()+"' and khott_fk= "+rs.getString("khoxuat_fk")+" and sanpham_fk="+rs.getString("sanpham_fk");
						
									//System.out.println("Cap Nhat KHo chi Tiet: "+query);
									if(db.updateReturnInt(query)!= 1)
									{
										this.msg = "Không thể cập nhật ERP_CHUYENKHO_SANPHAM " + query;
										db.getConnection().rollback();
										return false;
									}
									
									query="update ERP_KHOTT_SANPHAM_KYGUINPP set booked=booked -" +rs.getString("soluong") +" ,available=available+ "+rs.getString("soluong")+" " +
									" where NPP_FK ="+rs.getString("NCC_CHUYEN_FK")+"     and khott_fk= "+rs.getString("khoxuat_fk")+" and sanpham_fk="+rs.getString("sanpham_fk");
						
									//System.out.println("Cap Nhat KHo chi Tiet: "+query);
									if(db.updateReturnInt(query)!= 1)
									{
										this.msg = "Không thể cập nhật ERP_CHUYENKHO_SANPHAM " + query;
										db.getConnection().rollback();
										return false;
									}
							}
						}
						}
				
			}
			 
			//<-huy tham khảo
			query=" DELETE  ERP_CHUYENKHO_SP_XUATHANG WHERE CHUYENKHO_FK="+this.id;
			if(!db.update(query))
			{
				this.msg = "Không thể cập nhật ERP_CHUYENKHO_SANPHAM " + query;
				db.getConnection().rollback();
				return false;
			}
			
			
			

			if(this.spList.size() > 0)
			{
				for(int i = 0; i < this.spList.size(); i++)
				{
					IYeucau sp = this.spList.get(i);
					
					String spid=sp.getId();
					String soluongxuat=sp.getTongSoluongDaXuat();
					
					List<ISpDetail> spConList= sp.getSpDetailList();
					for(int t=0;t< spConList.size();t++){
						ISpDetail spCon = spConList.get(t);
						String solo=spCon.getSolo();
						String soluong=spCon.getSoluong();
						query=" INSERT INTO ERP_CHUYENKHO_SP_XUATHANG (CHUYENKHO_FK,SANPHAM_FK,SOLO,VITRI,SOLUONG) " +
							  " VALUES ("+this.id+", "+spid+",'"+solo+"',100000,'"+soluong+"')";
						if(!db.update(query))
						{
							this.msg = "Không thể cập nhật ERP_CHUYENKHO_SANPHAM " + query;
							db.getConnection().rollback();
							return false;
						}
						
					/*	query="update erp_khott_sanpham set booked=booked +" +soluong +" ,available=available-"+soluong+" where khott_fk= "+this.khoXuatId+" and sanpham_fk="+spid;
						if(db.updateReturnInt(query)< 1)
						{
							this.msg = "Không thể cập nhật ERP_CHUYENKHO_SANPHAM " + query;
							db.getConnection().rollback();
							return false;
						}*/
						String spid_= spid;
						String khoit_cn= this.khoXuatId;
						double soluongct_=0;
						double booked_ct_=0;
						double avai_ct_=0;
						try{ booked_ct_ =  Double.parseDouble(soluong.replaceAll(",", ""));}catch(Exception err){}
						try{ avai_ct_ = (-1)*Double.parseDouble(soluong.replaceAll(",", ""));}catch(Exception err){}
						
						String msg1= Utility_Kho.Update_Kho_Sp_Provence(db, khoit_cn, spid_,  soluongct_,booked_ct_,avai_ct_, 0,this.id,"ErpchyenkhoSx.java 1349");
						
						if(msg1.length() >0 )
						{
							db.getConnection().rollback();
							this.msg= "Lỗi :"+msg1;
							return false;
						}
						
						
						// kho chi tiết có số lô
						
						query="update erp_khott_sp_chitiet set booked=booked +" +soluong +" ,available=available-"+soluong+" where  rtrim(ltrim(SOLO))='"+solo.trim()+"' and khott_fk= "+this.khoXuatId+" and sanpham_fk="+spid;
						
						//System.out.println("Cap Nhat KHo chi Tiet: "+query);
						if(db.updateReturnInt(query)< 1)
						{
							this.msg = "Không thể cập nhật ERP_CHUYENKHO_SANPHAM " + query;
							db.getConnection().rollback();
							return false;
						}
						if(this.khoXuatId.trim().equals("100013")){
								query="update ERP_KHOTT_SP_CHITIET_NCC set booked=booked +" +soluong +" ,available=available-"+soluong+" " +
								" where NCC_FK ="+this.nccXuatId+" and rtrim(ltrim(SOLO))='"+solo.trim()+"' and khott_fk= "+this.khoXuatId+" and sanpham_fk="+spid;
								
								//System.out.println("Cap Nhat KHo chi Tiet: "+query);
								if(db.updateReturnInt(query)!= 1)
								{
									this.msg = "Không thể cập nhật ERP_CHUYENKHO_SANPHAM " + query;
									db.getConnection().rollback();
									return false;
								}
						}else{
						// NẾU LÀ KÝ GỬI THÌ PHẢI TRỪ KHO KÝ GỬI
						
							if(!this.loaichuyenkygui.equals("")){
								if(this.loaichuyenkygui.equals("1")){
									// nhân viên ký gửi
									query="update ERP_KHOTT_SP_CHITIET_KYGUINHANVIEN set booked=booked +" +soluong +" ,available=available-"+soluong+" " +
											" where NHANVIEN_FK="+this.nccXuatId+" and rtrim(ltrim(SOLO))='"+solo.trim()+"' and khott_fk= "+this.khoXuatId+" and sanpham_fk="+spid;
									
									//System.out.println("Cap Nhat KHo chi Tiet: "+query);
									if(db.updateReturnInt(query)< 1)
									{
										this.msg = "Không thể cập nhật ERP_CHUYENKHO_SANPHAM " + query;
										db.getConnection().rollback();
										return false;
									}
									query="update ERP_KHOTT_SANPHAM_KYGUINHANVIEN set booked=booked +" +soluong +" ,available=available-"+soluong+" " +
									" where NHANVIEN_FK="+this.nccXuatId+" and   khott_fk= "+this.khoXuatId+" and sanpham_fk="+spid;
							
									//System.out.println("Cap Nhat KHo chi Tiet: "+query);
									if(db.updateReturnInt(query)< 1)
									{
										this.msg = "Không thể cập nhật   " + query;
										db.getConnection().rollback();
										return false;
									}
									
									
								}else{
									query="update ERP_KHOTT_SP_CHITIET_KYGUINPP set booked=booked +" +soluong +" ,available=available-"+soluong+" " +
									" where NPP_FK="+this.nccXuatId+" and rtrim(ltrim(SOLO))='"+solo.trim()+"' and khott_fk= "+this.khoXuatId+" and sanpham_fk="+spid;
	 
									if(db.updateReturnInt(query)< 1)
									{
										this.msg = "Không thể cập nhật ERP_CHUYENKHO_SANPHAM " + query;
										db.getConnection().rollback();
										return false;
									}
									query="update ERP_KHOTT_SANPHAM_KYGUINPP set booked=booked +" +soluong +" ,available=available-"+soluong+" " +
									" where NPP_FK="+this.nccXuatId+"   and khott_fk= "+this.khoXuatId+" and sanpham_fk="+spid;
	 
									if(db.updateReturnInt(query)< 1)
									{
										this.msg = "Không thể cập nhật   " + query;
										db.getConnection().rollback();
										return false;
									}
								}
							}
						}
						
					}
					
					query = "update ERP_CHUYENKHO_SANPHAM set SOLUONGXUAT = "+soluongxuat+",SOLUONGNHAN="+soluongxuat+"  where CHUYENKHO_FK = '" + this.id + "' AND SANPHAM_FK="+spid;
					if(!db.update(query))
					{
						this.msg = "Không thể cập nhật ERP_CHUYENKHO_SANPHAM " + query;
						db.getConnection().rollback();
						return false;
					}
					
				}
			}
			
				query =  "  Update ERP_CHUYENKHO set  NGAYDIEUDONG='"+this.NGAYDIEUDONG+"',LENHDIEUDONG=N'"+this.LENHDIEUDONG+"',LENHDIEUDONGCUA=N'"+this.CUA+"',VEVIEC=N'"+this.VEVIEC+"',NGUOIVANCHUYEN=N'"+this.NGUOICHUYEN+"',PHUONGTIEN=N'"+this.PHUONGTIEN+"' , "+
						 "	trangthai=2 ,KYHIEU=N'"+this.Kyhieu+"',ngaychuyen = '" + this.ngayyeucau + "', ngaysua = '" + getDateTime() + "', nguoisua = '" + this.userId + "' " +
				" where pk_seq = '" + this.id + "' ";
				
				if(!db.update(query))
				{
					this.msg = "Không thể cập nhật ERP_CHUYENKHO " + query;
					db.getConnection().rollback();
					return false;
				}
			db.getConnection().commit();
			db.getConnection().setAutoCommit(true);
		} 
		catch (Exception e) 
		{	
			this.msg="Vui lòng thử lại ,lỗi trong quá trình tải dữ liệu : "+e.getMessage();
			db.update("rollback");
			e.printStackTrace();
			return false;
		}
		
		return true;

	}
	
	
	public boolean chuyenNL() 
	{
		if(this.msg.trim().length() > 0)
		{
			this.msg = "Vui lòng kiểm tra lại các thông tin: \n" + this.msg;
			return false;
		}
		
		if(this.khoXuatId.trim().length() <= 0)
		{
			this.msg = "Vui lòng chọn kho xuất";
			return false;
		}
		
		if(this.khoNhanId.trim().length() <= 0)
		{
			this.msg = "Vui lòng chọn kho nhận";
			return false;
		}
	
		if(this.ngayyeucau.trim().length() <= 0)
		{
			this.msg = "Vui lòng nhập ngày yêu cầu";
			return false;
		}
		
		if(this.spList.size() <= 0)
		{
			this.msg = "Không có sản phẩm nào được chọn";
			return false;
		}
		else
		{
			boolean flag = false;
			
			for(int i = 0; i < this.spList.size(); i++)
			{
				IYeucau yc = this.spList.get(i);
				
				String soluongYC = yc.getSoluongYC().trim().length() > 0 ? yc.getSoluongYC().trim() : "0" ;
				String soluongDN = yc.getSoluongDaNhan().trim().length() > 0 ? yc.getSoluongDaNhan().trim() : "0";
				String soluongN = yc.getSoluongNhan().trim().length() > 0 ? yc.getSoluongNhan().trim() : "0" ;
				
				if(Integer.parseInt(soluongN) > ( Integer.parseInt(soluongYC) - Integer.parseInt(soluongDN) ) )
				{
					this.msg = "Số lượng chuyển không được phép vượt quá số lượng yêu cầu và số lượng đã chuyển";
					return false;
				}
				
				if(yc.getSoluongNhan().trim().length() > 0)
				{
					if(Integer.parseInt(yc.getSoluongNhan().trim()) > 0)
					{
						flag = true;
					}
					
					List<ISpDetail> detail = yc.getSpDetailList();
					
					int sum = 0;
					for(int j = 0; j < detail.size(); j++)
					{
						if(detail.get(j).getSoluong().trim().length() > 0)
							sum += Integer.parseInt(detail.get(j).getSoluong().trim());
					}
					
					if( Integer.parseInt(yc.getSoluongNhan()) != sum )
					{
						this.msg = "Vui lòng kiểm tra Bin / Lô hàng xuất trước khi thực hiện thao tác.";
						flag = false;
						return false;
						
					}
				}
			}
			
			if(!flag)
			{
				this.msg = "Vui lòng kiểm tra Bin / Lô hàng xuất trước khi thực hiện thao tác.";
				return false;
			}
		}
		
		try 
		{
			db.getConnection().setAutoCommit(false);
			
			for(int i = 0; i < this.spList.size(); i++)
			{
				IYeucau sp = this.spList.get(i);
				String query =  "Update ERP_YEUCAUNGUYENLIEU_SANPHAM set soluongnhan = soluongnhan + '" + sp.getSoluongNhan() + "' " +
								"where  yeucaunguyenlieu_fk = '" + this.id + "' and  SANPHAM_FK = ( select pk_seq from SANPHAM where ma = '" + sp.getMa() + "') ";
				
				if(!db.update(query))
				{
					this.msg = "Khong the cap nhat ERP_YEUCAUNGUYENLIEU_SANPHAM: " + query;
					db.getConnection().rollback();
					return false;
				}
				else
				{
					List<ISpDetail> spCon = sp.getSpDetailList();
					for(int j = 0; j < spCon.size(); j++)
					{
						ISpDetail detail = spCon.get(j);
						
						if( ! detail.equals("0"))
						{
							
							//Luu lai luong xuatkho
							query =  "insert ERP_YEUCAUNGUYENLIEU_SP_XUATKHO(yeucaunguyenlieu_fk, sanpham_fk, khott_fk, solo, soluong, bean) " +
									 "select '" + this.id + "', pk_seq, '" + this.khoXuatId + "', '" + detail.getSolo() + "', '" + detail.getSoluong() + "', '" + detail.getVitriId() + "' " +
									 "from SANPHAM where ma = '" + sp.getMa() + "' ";
							
							if(!db.update(query))
							{
								this.msg = "2.Khong the cap nhat ERP_YEUCAUNGUYENLIEU_SP_XUATKHO: " + query;
								db.getConnection().rollback();
								return false;
							}
							
							
							query = "select count(*) as sodong from ERP_YEUCAUNGUYENLIEU_SP_CHITIET " +
									"where yeucaunguyenlieu_fk = '" + this.id + "' and SANPHAM_FK = ( select pk_seq from SANPHAM where ma = '" + detail.getMa() + "' ) and SOLO = '" + detail.getSolo() + "' and BEAN = '" + detail.getVitriId() + "'";
							
							ResultSet rsCheck = db.get(query);
							int sodong = 0;
							if(rsCheck != null)
							{
								if(rsCheck.next())
								{
									sodong = rsCheck.getInt("sodong");
								}
								rsCheck.close();
							}
							
							if(sodong <= 0)
							{
								query = "insert ERP_YEUCAUNGUYENLIEU_SP_CHITIET(yeucaunguyenlieu_fk, SANPHAM_FK, SOLO, SOLUONG, BEAN) " +
										"select '" + this.id + "', pk_seq, '" + detail.getSolo() + "', '" + detail.getSoluong() + "', '" + detail.getVitriId() + "' " +
										"from SANPHAM where ma = '" + detail.getMa() + "' ";
							}
							else
							{
								query = "update ERP_YEUCAUNGUYENLIEU_SP_CHITIET set soluong = soluong + '" + detail.getSoluong() + "' " + 
										"where yeucaunguyenlieu_fk = '" + this.id + "' and SANPHAM_FK = ( select pk_seq from SANPHAM where ma = '" + detail.getMa() + "' ) and SOLO = '" + detail.getSolo() + "' and BEAN = '" + detail.getVitriId() + "'";
							}
							
							if(!db.update(query))
							{
								this.msg = "Khong the cap nhat ERP_YEUCAUNGUYENLIEU_SP_CHITIET: " + query;
								db.getConnection().rollback();
								return false;
							}
						}
					}
				}
			}
			
			db.getConnection().commit();
			db.getConnection().setAutoCommit(true);
		}
		catch (Exception e) {}
		
		return true;
		
	}
	
	
	
	public void createRs() 
	{
 
		//Lấy danh sách nội dung xuất
		String query = 	" SELECT PK_SEQ,TEN FROM ERP_NOIDUNGNHAP WHERE MA LIKE 'X%' AND TRANGTHAI = '1' " +
						" AND PK_SEQ NOT IN (100002, 100003, 100007, 100022, 100023)  ";
		
		System.out.println("ndxuat-------"+this.task);
		if(this.task.equals("shop")){
			query=query+ " and pk_seq= 100006 ";
			this.ndxId="100006";
		}

		else if(this.task.equals("printYCXK"))
		{	
			query=query+ " and pk_seq="+this.ndxId;
	
		}
		query=query +" ORDER BY TEN";
		System.out.println("Cho Ney day : "+query);
		this.ndxRs = this.db.get(query);
		
		if(this.task.equals("shop")){
			Utility util=new Utility();
			String nppid=util.getIdNhapp(this.userId);
			query="select pk_seq,trungtampp_fk from erp_khott where npp_fk="+nppid;
			System.out.println("Query :"+query);
			ResultSet rs=db.get(query);
			try{
				if(rs.next()){
					this.khoNhanId=rs.getString("pk_seq");
					this.khoXuatId=rs.getString("trungtampp_fk");
					}
			rs.close();
			}catch(Exception err){
				err.printStackTrace();
			}
		}
		
		
		//System.out.println("[ErpChuyenkhoSX.createRs] ndxRs query = " + query);
		

		query = "SELECT DISTINCT CHKX.NOIDUNGXUAT_FK, NDN.TEN AS NDX, CHKX.LOAIKHOXUAT, KHO.PK_SEQ, KHO.TEN " +
				"FROM ERP_CAUHINHKHOXUAT CHKX " +
				"INNER JOIN ERP_NOIDUNGNHAP NDN ON NDN.PK_SEQ = CHKX.NOIDUNGXUAT_FK " +
				"INNER JOIN ERP_KHOTT KHO ON KHO.LOAI = CHKX.LOAIKHOXUAT AND KHO.TRANGTHAI = '1' " +
				"WHERE CHKX.NOIDUNGXUAT_FK = " + this.ndxId + " ";
		if(this.task.equals("shop")){
			query=query + " and  KHO.pk_seq= "+this.khoXuatId;
		}
		//System.out.println("[ErpChuyenkhoSX.createRs] khoXuatRs query = " + query);
		this.khoXuatRs = db.get(query);
 
		query = "SELECT DISTINCT CHKN.NOIDUNGXUAT_FK, NDN.TEN AS NDX, CHKN.LOAIKHONHAN, KHO.PK_SEQ, KHO.TEN " +
				"FROM ERP_CAUHINHKHONHAN CHKN " +
				"INNER JOIN ERP_NOIDUNGNHAP NDN ON NDN.PK_SEQ = CHKN.NOIDUNGXUAT_FK " +
				"INNER JOIN ERP_KHOTT KHO ON KHO.LOAI = CHKN.LOAIKHONHAN " +
				"WHERE CHKN.NOIDUNGXUAT_FK = " + this.ndxId + " ";
		if(this.task.equals("shop")){
			query=query + " and  KHO.pk_seq= "+this.khoNhanId;
		}
		if(this.khoXuatId.length() > 0) query = query + " AND KHO.PK_SEQ <> " + this.khoXuatId + " ";
		
		//System.out.println("[ErpChuyenkhoSX.createRs] khoNhanRs query = " + query);
		this.khoNhanRs = db.get(query);
		
		
		//Lấy danh sách NCC, NPP, Nhân viên chuyển tùy nội dung xuất
		//kho xuat la kho gia cong :100013
		if( this.khoXuatId.trim().equals("100013") )
		{
			query = "select PK_SEQ, TEN from ERP_NHACUNGCAP where KhoNL_Nho_GC = '" + this.khoXuatId + "'";
			//System.out.println("[ErpChuyenkhoSX.createRs] nccXuatRs query = " + query);
			this.nccXuatRs = db.get(query);
		}
		else if(this.khoXuatId.trim().equals("100015")) 
		{
			if(this.loaichuyenkygui.equals("0")) {
				query = " select PK_SEQ, TEN from NHAPHANPHOI where PRIANDSECOND = 1 AND ISKYGUI = 1 AND TRANGTHAI = 1 ";
				///System.out.println("[ErpChuyenkhoSX.createRs] nccXuatRs query = " + query);
				this.nccXuatRs = db.get(query);
			} else if (this.loaichuyenkygui.equals("1")) {
				query = " select PK_SEQ, TEN from ERP_NHANVIEN where TRANGTHAI = 1 AND COBANKYGUI = 1 ";
				//System.out.println("[ErpChuyenkhoSX.createRs] nccXuatRs query = " + query);
				this.nccXuatRs = db.get(query);
			}
		}
		
		//Lấy danh sách NCC, NPP, hoặc nhân viên nhận tùy nội dung nhận
		//kho gia cong :100013
		if( this.khoNhanId.trim().equals("100013") )
		{
			query = "select PK_SEQ, TEN from ERP_NHACUNGCAP where KhoNL_Nho_GC = '" + this.khoNhanId + "'";
			//System.out.println("[ErpChuyenkhoSX.createRs] nccNhanRs query = " + query);
			this.nccNhanRs = db.get(query);
		}
		else if(this.khoNhanId.trim().equals("100015")) 
		{
			if(this.loainhankygui.equals("0")) {
				query = " select PK_SEQ, TEN from NHAPHANPHOI where PRIANDSECOND = 1 AND ISKYGUI = 1 AND TRANGTHAI = 1 ";
				//System.out.println("[ErpChuyenkhoSX.createRs] nccNhanRs query = " + query);
				this.KhachhangKyguiRs = db.get(query);
	 
			} else if (this.loainhankygui.equals("1")) {
				 
				query = " select PK_SEQ, TEN from ERP_NHANVIEN where TRANGTHAI = 1 AND COBANKYGUI = 1 ";
				//System.out.println("[ErpChuyenkhoSX.createRs] nccNhanRs query = " + query);
				this.NhanvienKyguiRs = db.get(query);
			}
		}
		
		if(this.khoXuatId.trim().length() > 0 &&  this.spList.size() <= 0 && this.id.trim().length() > 0 )
		{			
			this.createChuyenKho_SanPham();
		}
		// 6. Xuất đổi hàng 
		 
			query="select PK_SEQ,TEN from NHAPHANPHOI   where   trangthai=1";
			this.KhachhangRs=this.db.get(query);
			query="select PK_SEQ,MA +'-'+ TEN as TEN from ERP_NHANVIEN where cobankygui=0      ";
			this.RsNhanVien=this.db.get(query);
			query="select PK_SEQ,TEN from ERP_DONVITHUCHIEN   ";
			this.PhongbanRs=this.db.get(query);
			if(this.id.trim().length()==0){
				this.InitNew();
			}
 
	}

	private void createChuyenKho_SanPham() 
	{
		String query = "";
	  
				 
					query = 	"   SELECT B.PK_SEQ AS SPID, B.MA AS SPMA,  B.TRONGLUONG,B.THETICH,B.TEN AS SPTEN,DVDL.DONVI, "+
								" 	ISNULL(A.SOLUONGYEUCAU, 0) AS SOLUONGYEUCAU ,KHO.AVAILABLE  ,ISNULL( A.NGAYYEUCAU,'') AS NGAYYEUCAU,   "+
								"   ISNULL(QC.SOLUONG1,0) AS QUYCACH, ISNULL(QC.SOLUONG2,0) SOLUONG2 "+
								" 	FROM ERP_YEUCAUCHUYENKHO_SANPHAM A   "+
								" 	INNER JOIN ERP_YEUCAUCHUYENKHO E ON A.CHUYENKHO_FK = E.PK_SEQ "+     
								" 	INNER JOIN SANPHAM B ON B.PK_SEQ=A.SANPHAM_FK  " +
								"   INNER JOIN DONVIDOLUONG DVDL ON DVDL.PK_SEQ=B.DVDL_FK  " +
								"   LEFT JOIN QUYCACH QC ON B.PK_SEQ = QC.SANPHAM_FK AND QC.DVDL2_FK = '100018' "+
								" 	INNER JOIN ERP_KHOTT_SANPHAM KHO ON KHO.KHOTT_FK= E.KHOXUAT_FK  AND KHO.SANPHAM_FK=A.SANPHAM_FK "+
								" 	WHERE A.CHUYENKHO_FK = "+this.id; 
					if(this.khoXuatId.equals("100013")) {
						if(this.nccXuatId.length() > 0){
								query = 	"   SELECT B.PK_SEQ AS SPID, B.MA AS SPMA, B.TRONGLUONG,B.THETICH, B.TEN AS SPTEN,DVDL.DONVI, "+
								" 	ISNULL(A.SOLUONGYEUCAU, 0) AS SOLUONGYEUCAU ,KHO.AVAILABLE  ,ISNULL( A.NGAYYEUCAU,'') AS NGAYYEUCAU,   "+
								"   ISNULL(QC.SOLUONG1,0) AS QUYCACH, ISNULL(QC.SOLUONG2,0) SOLUONG2 "+
								" 	FROM ERP_YEUCAUCHUYENKHO_SANPHAM A   "+
								" 	INNER JOIN ERP_YEUCAUCHUYENKHO E ON A.CHUYENKHO_FK = E.PK_SEQ "+     
								" 	INNER JOIN SANPHAM B ON B.PK_SEQ=A.SANPHAM_FK  " +
								"   INNER JOIN DONVIDOLUONG DVDL ON DVDL.PK_SEQ=B.DVDL_FK  " +
								"   LEFT JOIN QUYCACH QC ON B.PK_SEQ = QC.SANPHAM_FK AND QC.DVDL2_FK = '100018' "+
								" 	INNER JOIN (select  KHOTT_FK ,SUM(AVAILABLE)as AVAILABLE ,SANPHAM_FK from ERP_KHOTT_SP_CHITIET_NCC   where  NCC_FK= "+this.nccXuatId+" and  AVAILABLE >0  and  khott_fk="+this.khoXuatId+"    group by SANPHAM_FK,KHOTT_FK  ) KHO ON KHO.KHOTT_FK= E.KHOXUAT_FK  AND KHO.SANPHAM_FK=A.SANPHAM_FK "+
								" 	WHERE A.CHUYENKHO_FK = "+this.id; 
						}
					}
					if(this.khoXuatId.equals("100015")) {
							if(loaichuyenkygui.equals("0")){
								//KHÁCH HÀNG KÝ GỬI
								query = 	"   SELECT B.PK_SEQ AS SPID, B.MA AS SPMA, B.TEN AS SPTEN,DVDL.DONVI,B.TRONGLUONG,B.THETICH, "+
								" 	ISNULL(A.SOLUONGYEUCAU, 0) AS SOLUONGYEUCAU ,KHO.AVAILABLE  ,ISNULL( A.NGAYYEUCAU,'') AS NGAYYEUCAU, "+
								"   ISNULL(QC.SOLUONG1,0) AS QUYCACH, ISNULL(QC.SOLUONG2,0) SOLUONG2 "+
								" 	FROM ERP_YEUCAUCHUYENKHO_SANPHAM A   "+
								" 	INNER JOIN ERP_YEUCAUCHUYENKHO E ON A.CHUYENKHO_FK = E.PK_SEQ "+     
								" 	INNER JOIN SANPHAM B ON B.PK_SEQ=A.SANPHAM_FK  " +
								"   INNER JOIN DONVIDOLUONG DVDL ON DVDL.PK_SEQ=B.DVDL_FK  " +
								"   LEFT JOIN QUYCACH QC ON B.PK_SEQ = QC.SANPHAM_FK AND QC.DVDL2_FK = '100018' "+
								" 	INNER JOIN (select  KHOTT_FK ,SUM(AVAILABLE)as AVAILABLE ,SANPHAM_FK from ERP_KHOTT_SANPHAM_KYGUINPP   where  NPP_FK= "+this.nccXuatId+" and  AVAILABLE >0  and  khott_fk="+this.khoXuatId+"    group by SANPHAM_FK,KHOTT_FK  ) KHO ON KHO.KHOTT_FK= E.KHOXUAT_FK  AND KHO.SANPHAM_FK=A.SANPHAM_FK "+
								" 	WHERE A.CHUYENKHO_FK = "+this.id;
							}else{
								//NHAN VIEN KY GUI
								query = 	"   SELECT B.PK_SEQ AS SPID, B.MA AS SPMA, B.TRONGLUONG,B.THETICH, B.TEN AS SPTEN,DVDL.DONVI, "+
								" 	ISNULL(A.SOLUONGYEUCAU, 0) AS SOLUONGYEUCAU ,KHO.AVAILABLE ,ISNULL( A.NGAYYEUCAU,'') AS NGAYYEUCAU, "+
								"   ISNULL(QC.SOLUONG1,0) AS QUYCACH, ISNULL(QC.SOLUONG2,0) SOLUONG2 "+
								" 	FROM ERP_YEUCAUCHUYENKHO_SANPHAM A   "+
								" 	INNER JOIN ERP_YEUCAUCHUYENKHO E ON A.CHUYENKHO_FK = E.PK_SEQ "+     
								" 	INNER JOIN SANPHAM B ON B.PK_SEQ=A.SANPHAM_FK  " +
								"   INNER JOIN DONVIDOLUONG DVDL ON DVDL.PK_SEQ=B.DVDL_FK  " +
								"   LEFT JOIN QUYCACH QC ON B.PK_SEQ = QC.SANPHAM_FK AND QC.DVDL2_FK = '100018' "+
								" 	INNER JOIN (select  KHOTT_FK ,SUM(AVAILABLE)as AVAILABLE ,SANPHAM_FK from ERP_KHOTT_SANPHAM_KYGUINHANVIEN   where  NHANVIEN_FK = "+this.nccXuatId+" and  AVAILABLE >0  and  khott_fk="+this.khoXuatId+"    group by SANPHAM_FK,KHOTT_FK  ) KHO ON KHO.KHOTT_FK= E.KHOXUAT_FK  AND KHO.SANPHAM_FK=A.SANPHAM_FK "+
								" 	WHERE A.CHUYENKHO_FK = "+this.id;

							}
							
					  }
		 
 
		System.out.println("[ErpChuyenkhoSX.createChuyenKho_SanPham] spRs query = " + query);
		
		ResultSet spRs = db.get(query);
		
		List<IYeucau> spList = new ArrayList<IYeucau>();
		
		if(spRs != null)
		{
			try 
			{
				IYeucau sp = null;
				while(spRs.next())
				{
					String spId = spRs.getString("spId");
					String spMa = spRs.getString("spMa");
					String spTen = spRs.getString("spTen");
					
					int soluong = spRs.getInt("SOLUONGYEUCAU");
					int quycach = spRs.getInt("QUYCACH");
					int soluong2 = spRs.getInt("SOLUONG2");
					if(quycach==0)quycach=1;
					int thung = soluong * soluong2 / quycach;
					
					sp = new Yeucau();
					sp.setId(spId);
					sp.setdonvi(spRs.getString("donvi"));
					sp.setMa(spMa);
					sp.setTen(spTen);
					sp.setTonhientai(spRs.getString("available"));
					sp.setSoluongYC(spRs.getString("SOLUONGYEUCAU"));
					sp.setSoluongThung(Integer.toString(thung));
					sp.setNgayyeucau(spRs.getString("NGAYYEUCAU"));
					sp.setTrongLuong(spRs.getString("TRONGLUONG"));
					sp.setTheTich(spRs.getString("THETICH"));
					spList.add(sp);
				}
				spRs.close();
			} 
			catch (Exception e) 
			{
				e.printStackTrace();
			}
			
			this.spList = spList;
		}
	}

	public void init() 
	{
		String query = "select  ISNULL(MUAHANG_FK,0) AS MUAHANGID , ISNULL(KYHIEU,'') AS  KYHIEU ,ISNULL(SOCHUNGTUIN,'') AS SOCHUNGTUIN , NPP_FK ,ISNULL(LENHSANXUAT_FK,ISNULL(MUAHANG_FK,0)) AS POLSX ,isnull(Ngayyeucau,'') as Ngayyeucau,isnull(LOAIDOITUONG,'') as LOAIDOITUONG ,PHONGBAN_FK,NHANVIEN_FK,isnull(LENHDIEUDONG,'') as LENHDIEUDONG " +
				" ,isnull(NGAYDIEUDONG,'') as NGAYDIEUDONG,isnull(LENHDIEUDONGCUA,'') as LENHDIEUDONGCUA  ,isnull(VEVIEC,'') as VEVIEC " +
				" ,isnull(NGUOIVANCHUYEN,'') as  NGUOIVANCHUYEN , isnull(PHUONGTIEN,'') as PHUONGTIEN,ISNULL( COXACNHANNHAPKHO,0) AS COXACNHANNHAPKHO, " +
				" noidungxuat_fk, ngaychuyen, lydo, khoxuat_fk, khonhan_fk, trangthai, isnull(trangthaisp, 0) as trangthaisp, " +
				" NCC_CHUYEN_FK, NCC_NHAN_FK, LOAICHUYENKYGUI, LOAINHANKYGUI " +
				" from ERP_YEUCAUCHUYENKHO where pk_seq = '" + this.id + "'";
		System.out.println("[ErpChuyenkhoSX.init] query = " + query);
		ResultSet rs = db.get(query);
		if(rs != null)
		{
			try 
			{
				if(rs.next())
				{
					this.Kyhieu=rs.getString("KYHIEU");
					this.sochungtuin=rs.getString("SOCHUNGTUIN");
					this.Ngaydenghi=rs.getString("ngayyeucau");
					this.PoLsx=rs.getString("POLSX");
					this.phongbanid=rs.getString("PHONGBAN_FK");
					this.NhanvienId=rs.getString("nhanvien_fk");
					this.CheckNhanHang=rs.getString("COXACNHANNHAPKHO");
					this.LENHDIEUDONG=rs.getString("LENHDIEUDONG");
					this.CUA=rs.getString("LENHDIEUDONGCUA");
					this.VEVIEC=rs.getString("VEVIEC");
					this.NGUOICHUYEN=rs.getString("NGUOIVANCHUYEN");
					this.NGAYDIEUDONG=rs.getString("NGAYDIEUDONG");
					this.PHUONGTIEN=rs.getString("PHUONGTIEN");
					this.Loaidoituong=rs.getString("LOAIDOITUONG");
					this.ndxId = rs.getString("noidungxuat_fk");
					this.ngayyeucau = rs.getString("ngaychuyen");
					this.lydoyeucau = rs.getString("lydo");
					this.khoXuatId = rs.getString("khoxuat_fk");
					this.khoNhanId = rs.getString("khonhan_fk");
					if(this.khoNhanId==null){
						this.khoNhanId="";
					}
					this.KhachhangId=rs.getString("NPP_FK") == null ? "" : rs.getString("NPP_FK");
					this.trangthai = rs.getString("trangthai");
					this.trangthaisp = rs.getString("trangthaisp");
					this.loaichuyenkygui = rs.getString("LOAICHUYENKYGUI") == null ? "" : rs.getString("LOAICHUYENKYGUI");
					this.loainhankygui = rs.getString("LOAINHANKYGUI") == null ? "" : rs.getString("LOAINHANKYGUI");
					this.nccXuatId = rs.getString("NCC_CHUYEN_FK") == null ? "" : rs.getString("NCC_CHUYEN_FK");
					this.nccNhanId = rs.getString("NCC_NHAN_FK") == null ? "" : rs.getString("NCC_NHAN_FK");
					this.KhachhangKyguiId=this.nccNhanId;
					this.NhanvienKyguiId=this.nccNhanId;
					this.muahangId=rs.getString("MUAHANGID");
				 
				}
				rs.close();
			} 
			catch (Exception e) {
				e.printStackTrace();
			}
		}
		this.createRs();
	}
	public String getLsxIds()
	{
		return this.lsxIds;
	}

	public void setLsxIds(String lsxIds) 
	{
		this.lsxIds = lsxIds;
	}

	public ResultSet getLsxRs()
	{
		return this.lsxRs;
	}

	public void setLsxRs(ResultSet lsxRs) 
	{
		this.lsxRs = lsxRs;
	}
	
	private String getDateTime() 
	{
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date date = new Date();
        return dateFormat.format(date);	
	}

	public String getIsnhanHang() 
	{
		return this.isnhanHang;
	}

	public void setIsnhanHang(String isnhanHang)
	{
		this.isnhanHang = isnhanHang;
	}

	
	public List<IKhu_Vitri> getKhuList()
	{
		return this.khuList;
	}

	public void setKhuList(List<IKhu_Vitri> khuList) 
	{
		this.khuList = khuList;
	}

	public List<IKhu_Vitri> getVitriList() 
	{
		return this.vitriList;
	}

	public void setVitriList(List<IKhu_Vitri> vitriList)
	{
		this.vitriList = vitriList;
	}

	public List<IYeucau> getSpChoNhapList()
	{
		return this.spChoNhapList;
	}

	public void setSpChoNhapList(List<IYeucau> spchoNhapList) 
	{
		this.spChoNhapList = spchoNhapList;
	}

	public String getTrangthaiSP() 
	{
		return this.trangthaisp;
	}

	public void setTrangthaiSP(String trangthaisp) 
	{
		this.trangthaisp = trangthaisp;
	}
	
	public String getNdxId()
	{
		return this.ndxId;
	}

	public void setNdxId(String ndxId) 
	{
		this.ndxId = ndxId;
	}

	public ResultSet getNdxList()
	{
		return this.ndxRs;
	}

	public void setNdxList(ResultSet ndxList) 
	{
		this.ndxRs = ndxList;	
	}

	
	public String getNccChuyenIds() {
		
		return this.nccXuatId;
	}

	
	public void setNccChuyenIds(String nccChuyenIds) {
		
		this.nccXuatId = nccChuyenIds;
	}

	
	public ResultSet getNccChuyenRs() {
		
		return this.nccXuatRs;
	}

	
	public void setNccChuyenRs(ResultSet nccChuyenRs) {
		
		this.nccXuatRs = nccChuyenRs;
	}

	
	public String getNccNhanIds() {
		
		return this.nccNhanId;
	}

	
	public void setNccNhanIds(String nccNhanIds) {
		
		this.nccNhanId = nccNhanIds;
	}

	
	public ResultSet getNccNhanRs() {
		
		return this.nccNhanRs;
	}

	
	public void setNccNhanRs(ResultSet nccNhanRs) {
		
		this.nccNhanRs = nccNhanRs;
	}

	public String getTrangthai() 
	{
		return this.trangthai;
	}

	public void setTrangthai(String trangthai) 
	{
		this.trangthai = trangthai;
	}

	
	public void initYeucauNLPdf() 
	{
		String query =  "select a.SANPHAM_FK, b.MA, b.TEN, a.SOLUONGYEUCAU as SoLuong " +
						"from ERP_YEUCAUNGUYENLIEU_SANPHAM a " +
						"inner Join SANPHAM b on a.SANPHAM_FK = b.PK_SEQ where yeucaunguyenlieu_fk = '" + this.id + "'";
		
		System.out.println("1.Query khoi tao sp: " + query);
		ResultSet rs = db.get(query);
		
		if(rs != null)
		{
			List<IYeucau> spList = new ArrayList<IYeucau>();
			
			try 
			{
				IYeucau yeucau;

				while(rs.next())
				{
					String spId = rs.getString("SANPHAM_FK");
					String spMa = rs.getString("MA");
					String spTen = rs.getString("TEN");
					String soluong = rs.getString("SOLUONG");

					yeucau = new Yeucau();
					yeucau.setId(spId);
					yeucau.setMa(spMa);
					yeucau.setTen(spTen);
					yeucau.setSoluongYC(soluong);
			
					query = " select b.MA, b.TEN, c.MA AS VITRI, d.DIENGIAI AS KHUTEN, e.DONVI as DONVIDOLUONG, nhapkho.solo, nhapkho.tongNhap, f.ma as khoTen  " +
						"from " +
						"( " +
							"select sanpham_fk, khott_fk, solo, bean, SUM(soluong) as tongNhap  " +
							"from ERP_YEUCAUNGUYENLIEU_SP_NHAPKHO   " +
							"where yeucaunguyenlieu_fk = '" + this.id + "' and sanpham_fk = '" + spId + "'  " +
							"group by sanpham_fk, khott_fk, solo, bean  " +
						")  " +
						"nhapkho inner Join SANPHAM b on nhapkho.sanpham_fk = b.PK_SEQ " +
						"INNER JOIN ERP_VITRIKHO c ON nhapkho.BEAN = c.PK_SEQ  " +
						"inner join ERP_KHUVUCKHO d on c.KHU_FK = d.PK_SEQ  " +
						"inner join DONVIDOLUONG e on b.DVDL_FK = e.PK_SEQ inner join ERP_KHOTT f on d.khott_fk = f.pk_seq ";
					
					System.out.println("1.San pham lay hang Detail: " + query);
					ResultSet rsSpDetail = db.get(query);
					
					List<ISpDetail> spConList = new ArrayList<ISpDetail>();
					ISpDetail spCon = null;
					
					boolean flag = false;
					if(rsSpDetail != null)
					{
						while(rsSpDetail.next())
						{
							String idhangmua = rsSpDetail.getString("DONVIDOLUONG"); //luu donvidoluong
							String solo = rsSpDetail.getString("solo");
							
							int conlai = Integer.parseInt(soluong) - rsSpDetail.getInt("tongNhap");
							String slg = soluong + " - " + rsSpDetail.getString("tongNhap") + " - " + Integer.toString(conlai);
							String khu = rsSpDetail.getString("KHUTEN");
							String vitri = rsSpDetail.getString("VITRI");
							String vitriId = rsSpDetail.getString("khoTen");
							
							spCon = new SpDetail(idhangmua, solo, slg, khu, vitri, vitriId);
							spConList.add(spCon);
							
							flag = true;
						}
						rsSpDetail.close();
					}
					
					if(!flag)
					{
						String slg = soluong + " - 0 - " + soluong;
						spCon = new SpDetail(" ", " ", slg, " ", " ", " ");
						spConList.add(spCon);
					}
					
					yeucau.setSpDetailList(spConList);	
					spList.add(yeucau);
				}
			
				rs.close();
			} 
			catch (Exception e) 
			{ 
				System.out.println("1.Exception: " + e.getMessage() + "\n");
			}
			
			this.spList = spList;
		}
	}

	public void initChuyenNLPdf() 
	{
		String query =  "select a.SANPHAM_FK, b.MA, b.TEN, a.SOLUONGYEUCAU as SoLuong " +
				"from ERP_YEUCAUNGUYENLIEU_SANPHAM a " +
				"inner Join SANPHAM b on a.SANPHAM_FK = b.PK_SEQ where yeucaunguyenlieu_fk = '" + this.id + "'";
		
		System.out.println("1.Query khoi tao sp: " + query);
		ResultSet rs = db.get(query);
		
		if(rs != null)
		{
		List<IYeucau> spList = new ArrayList<IYeucau>();
		
		try 
		{
		IYeucau yeucau;
		
		while(rs.next())
		{
			String spId = rs.getString("SANPHAM_FK");
			String spMa = rs.getString("MA");
			String spTen = rs.getString("TEN");
			String soluong = rs.getString("SOLUONG");
		
			yeucau = new Yeucau();
			yeucau.setId(spId);
			yeucau.setMa(spMa);
			yeucau.setTen(spTen);
			yeucau.setSoluongYC(soluong);
		
			query = " select b.MA, b.TEN, c.MA AS VITRI, d.DIENGIAI AS KHUTEN, e.DONVI as DONVIDOLUONG, nhapkho.solo, nhapkho.tongNhap, f.ma as khoTen  " +
				"from " +
				"( " +
					"select sanpham_fk, khott_fk, solo, bean, SUM(soluong) as tongNhap  " +
					"from ERP_YEUCAUNGUYENLIEU_SP_XUATKHO   " +
					"where yeucaunguyenlieu_fk = '" + this.id + "' and sanpham_fk = '" + spId + "'  " +
					"group by sanpham_fk, khott_fk, solo, bean  " +
				")  " +
				"nhapkho inner Join SANPHAM b on nhapkho.sanpham_fk = b.PK_SEQ " +
				"INNER JOIN ERP_VITRIKHO c ON nhapkho.BEAN = c.PK_SEQ  " +
				"inner join ERP_KHUVUCKHO d on c.KHU_FK = d.PK_SEQ  " +
				"inner join DONVIDOLUONG e on b.DVDL_FK = e.PK_SEQ inner join ERP_KHOTT f on d.khott_fk = f.pk_seq ";
			
			System.out.println("1.San pham lay hang Detail: " + query);
			ResultSet rsSpDetail = db.get(query);
			
			List<ISpDetail> spConList = new ArrayList<ISpDetail>();
			ISpDetail spCon = null;
			
			boolean flag = false;
			if(rsSpDetail != null)
			{
				while(rsSpDetail.next())
				{
					String idhangmua = rsSpDetail.getString("DONVIDOLUONG"); //luu donvidoluong
					String solo = rsSpDetail.getString("solo");
					
					int conlai = Integer.parseInt(soluong) - rsSpDetail.getInt("tongNhap");
					String slg = soluong + " - " + rsSpDetail.getString("tongNhap") + " - " + Integer.toString(conlai);
					String khu = rsSpDetail.getString("KHUTEN");
					String vitri = rsSpDetail.getString("VITRI");
					String vitriId = rsSpDetail.getString("khoTen");
					
					spCon = new SpDetail(idhangmua, solo, slg, khu, vitri, vitriId);
					spConList.add(spCon);
					
					flag = true;
				}
				rsSpDetail.close();
			}
			
			if(!flag)
			{
				String slg = soluong + " - 0 - " + soluong;
				spCon = new SpDetail(" ", " ", slg, " ", " ", " ");
				spConList.add(spCon);
			}
			
			yeucau.setSpDetailList(spConList);	
			spList.add(yeucau);
		}
		
		rs.close();
		} 
		catch (Exception e) 
		{ 
		System.out.println("1.Exception: " + e.getMessage() + "\n");
		}
		
		this.spList = spList;
		}
	}


	public void initNhanhang() 
	{
		String query =  " select noidungxuat_fk, ngaychuyen, lydo, khoxuat_fk, khonhan_fk, " +
						" trangthai, isnull(trangthaisp, 0) as trangthaisp, NCC_CHUYEN_FK, NCC_NHAN_FK, loaichuyenkygui, loainhankygui " +
						" from ERP_CHUYENKHO where pk_seq = '" + this.id + "'";
		ResultSet rs = db.get(query);
		if(rs != null)
		{
			try 
			{
				if(rs.next())
				{
					this.ndxId = rs.getString("noidungxuat_fk");
					this.ngayyeucau = rs.getString("ngaychuyen");
					this.lydoyeucau = rs.getString("lydo");
					this.khoXuatId = rs.getString("khoxuat_fk");
					this.khoNhanId = rs.getString("khonhan_fk") == null ? "" : rs.getString("khonhan_fk");
					this.trangthai = rs.getString("trangthai");
					this.trangthaisp = rs.getString("trangthaisp");

					
					this.loaichuyenkygui = rs.getString("LOAICHUYENKYGUI") == null ? "" : rs.getString("LOAICHUYENKYGUI");
					this.loainhankygui = rs.getString("LOAINHANKYGUI") == null ? "" : rs.getString("LOAINHANKYGUI");
					this.nccXuatId = rs.getString("NCC_CHUYEN_FK") == null ? "" : rs.getString("NCC_CHUYEN_FK");
					this.nccNhanId = rs.getString("NCC_NHAN_FK") == null ? "" : rs.getString("NCC_NHAN_FK");
				}
				rs.close();
			} 
			catch (Exception e) {}
		}
				
		query = "select pk_seq, MA + ' - ' + TEN as TEN from ERP_NOIDUNGNHAP  " +
				"where trangthai = '1' and upper(substring(ma, 0, 2)) = upper('X') and upper(substring(ma, 0, 3)) != upper('XK')";
		
		this.ndxRs = db.get(query);
		
		this.khoXuatRs = db.get("select PK_SEQ, TEN from ERP_KHOTT where TrangThai = '1' order by loai asc ");
		
		query = "select PK_SEQ, TEN from ERP_KHOTT where TrangThai = '1'  ";
		if(this.khoXuatId.trim().length() > 0)
			query += " and pk_seq != '" + this.khoXuatId + "' ";
		query += " order by loai asc ";
		
		this.khoNhanRs = db.get(query);
		
		
		//Lấy danh sách NCC, NPP, Nhân viên chuyển tùy nội dung xuất
		if( this.khoXuatId.trim().equals("100013") )
		{
			query = "select PK_SEQ, TEN from ERP_NHACUNGCAP where KhoNL_Nho_GC = '" + this.khoXuatId + "'";
			//System.out.println("[ErpChuyenkhoSX.createRs] nccXuatRs query = " + query);
			this.nccXuatRs = db.get(query);
		} 
		  
		else if(this.khoXuatId.trim().equals("100015")) 
		{
			if(this.loaichuyenkygui.equals("0")) {
				query = " select PK_SEQ, TEN from NHAPHANPHOI where PRIANDSECOND = 1 AND ISKYGUI = 1 AND TRANGTHAI = 1 ";
				//System.out.println("[ErpChuyenkhoSX.createRs] nccXuatRs query = " + query);
				this.nccXuatRs = db.get(query);
			} else if (this.loaichuyenkygui.equals("1")) {
				query = " select PK_SEQ, TEN from ERP_NHANVIEN where TRANGTHAI = 1 AND COBANKYGUI = 1 ";
				//System.out.println("[ErpChuyenkhoSX.createRs] nccXuatRs query = " + query);
				this.nccXuatRs = db.get(query);
			}
		}
		
		//Lấy danh sách NCC, NPP, hoặc nhân viên nhận tùy nội dung xuất
		if( this.khoNhanId.trim().equals("100013") )
		{
			query = " select PK_SEQ, TEN from ERP_NHACUNGCAP where KhoNL_Nho_GC = '" + this.khoNhanId + "'";
			//System.out.println("[ErpChuyenkhoSX.createRs] nccNhanRs query = " + query);
			this.nccNhanRs = db.get(query);
		}
		 
		else if(this.khoNhanId.trim().equals("100015")) 
		{
			if(this.loainhankygui.equals("0")) {
				query = " select PK_SEQ, TEN from NHAPHANPHOI where PRIANDSECOND = 1 AND ISKYGUI = 1 AND TRANGTHAI = 1 ";
				//System.out.println("[ErpChuyenkhoSX.createRs] nccNhanRs query = " + query);
				this.nccNhanRs = db.get(query);
			} else if (this.loainhankygui.equals("1")) {
				query = " select PK_SEQ, TEN from ERP_NHANVIEN where TRANGTHAI = 1 AND COBANKYGUI = 1 ";
				//System.out.println("[ErpChuyenkhoSX.createRs] nccNhanRs query = " + query);
				this.nccNhanRs = db.get(query);
			}
		}
		
		if( this.spList.size() <= 0 )
		{
			this.createChuyenKho_SanPham_NhanHang();
		}
		
	}
	
	public void initXuathang() 
	{
		String query = "select isnull(kyhieu,'') as kyhieu ,isnull(LOAIDOITUONG,'') as LOAIDOITUONG ,PHONGBAN_FK,NHANVIEN_FK,LENHDIEUDONG,NGAYDIEUDONG,LENHDIEUDONGCUA,VEVIEC " +
		" ,NGUOIVANCHUYEN,PHUONGTIEN,ISNULL( COXACNHANNHAPKHO,0) AS COXACNHANNHAPKHO, " +
		" noidungxuat_fk, ngaychuyen, lydo, khoxuat_fk, khonhan_fk, trangthai, isnull(trangthaisp, 0) as trangthaisp, " +
		" NCC_CHUYEN_FK, NCC_NHAN_FK, LOAICHUYENKYGUI, LOAINHANKYGUI " +
		" from ERP_CHUYENKHO where pk_seq = '" + this.id + "' ";
		System.out.println(query);
		ResultSet rs = db.get(query);
		 
			try 
			{
				if(rs.next())
				{
					this.phongbanid=rs.getString("PHONGBAN_FK");
					this.NhanvienId=rs.getString("nhanvien_fk");
					this.CheckNhanHang=rs.getString("COXACNHANNHAPKHO");
					this.LENHDIEUDONG=rs.getString("LENHDIEUDONG");
					this.CUA=rs.getString("LENHDIEUDONGCUA");
					this.VEVIEC=rs.getString("VEVIEC");
					this.NGUOICHUYEN=rs.getString("NGUOIVANCHUYEN");
					this.NGAYDIEUDONG=rs.getString("NGAYDIEUDONG");
					this.PHUONGTIEN=rs.getString("PHUONGTIEN");
					this.Loaidoituong=rs.getString("LOAIDOITUONG");
					this.ndxId = rs.getString("noidungxuat_fk");
					this.ngayyeucau = rs.getString("ngaychuyen");
					this.lydoyeucau = rs.getString("lydo");
					this.khoXuatId = rs.getString("khoxuat_fk");
					this.khoNhanId = rs.getString("khonhan_fk");
					this.Kyhieu=rs.getString("kyhieu");
					if(this.khoNhanId==null){
					     this.khoNhanId="";
					}
					this.trangthai = rs.getString("trangthai");
					this.trangthaisp = rs.getString("trangthaisp");
					this.loaichuyenkygui = rs.getString("LOAICHUYENKYGUI") == null ? "" : rs.getString("LOAICHUYENKYGUI");
					this.loainhankygui = rs.getString("LOAINHANKYGUI") == null ? "" : rs.getString("LOAINHANKYGUI");
					this.nccXuatId = rs.getString("NCC_CHUYEN_FK") == null ? "" : rs.getString("NCC_CHUYEN_FK");
					this.nccNhanId = rs.getString("NCC_NHAN_FK") == null ? "" : rs.getString("NCC_NHAN_FK");
					this.KhachhangKyguiId=this.nccNhanId;
					this.NhanvienKyguiId=this.nccNhanId;
				}
				rs.close();
			} 
			catch (Exception e) {
				e.printStackTrace();
			}
		 
		this.CreateRsXuatKho();
		 
		this.createChuyenKho_SanPham_XuatHang();
 
	}

	private void CreateRsXuatKho() {
		// TODO Auto-generated method stub
		String 	query = 	"SELECT PK_SEQ,TEN FROM ERP_NOIDUNGNHAP WHERE MA LIKE 'X%' AND TRANGTHAI = '1' " +
			"AND PK_SEQ NOT IN (100002, 100003, 100007, 100022, 100023) ORDER BY TEN ";
			this.ndxRs = this.db.get(query);
			
			System.out.println("[ErpChuyenkhoSX.createRs] ndxRs query = " + query);
			this.ndxRs = db.get(query);
			
			query = "SELECT DISTINCT CHKX.NOIDUNGXUAT_FK, NDN.TEN AS NDX, CHKX.LOAIKHOXUAT, KHO.PK_SEQ, KHO.TEN " +
			"FROM ERP_CAUHINHKHOXUAT CHKX " +
			"INNER JOIN ERP_NOIDUNGNHAP NDN ON NDN.PK_SEQ = CHKX.NOIDUNGXUAT_FK " +
			"INNER JOIN ERP_KHOTT KHO ON KHO.LOAI = CHKX.LOAIKHOXUAT AND KHO.TRANGTHAI = '1' " +
			"WHERE CHKX.NOIDUNGXUAT_FK = " + this.ndxId + " ";
			System.out.println("[ErpChuyenkhoSX.createRs] khoXuatRs query = " + query);
			this.khoXuatRs = db.get(query);
			
			
			
			query = "SELECT DISTINCT CHKN.NOIDUNGXUAT_FK, NDN.TEN AS NDX, CHKN.LOAIKHONHAN, KHO.PK_SEQ, KHO.TEN " +
			"FROM ERP_CAUHINHKHONHAN CHKN " +
			"INNER JOIN ERP_NOIDUNGNHAP NDN ON NDN.PK_SEQ = CHKN.NOIDUNGXUAT_FK " +
			"INNER JOIN ERP_KHOTT KHO ON KHO.LOAI = CHKN.LOAIKHONHAN " +
			"WHERE CHKN.NOIDUNGXUAT_FK = " + this.ndxId + " ";
			
			if(this.khoXuatId.length() > 0) 
				{
					query = query + " AND KHO.PK_SEQ <> " + this.khoXuatId + " ";
				}
			
			System.out.println("[ErpChuyenkhoSX.createRs] khoNhanRs query = " + query);
			this.khoNhanRs = db.get(query);
			
			
			//Lấy danh sách NCC, NPP, Nhân viên chuyển tùy nội dung xuất
			if( this.khoXuatId.trim().equals("100013") )
			{
				query = "select PK_SEQ, TEN from ERP_NHACUNGCAP where KhoNL_Nho_GC = '" + this.khoXuatId + "'";
				//System.out.println("[ErpChuyenkhoSX.createRs] nccXuatRs query = " + query);
				this.nccXuatRs = db.get(query);
			}
			else if(this.khoXuatId.trim().equals("100015")) 
			{
				if(this.loaichuyenkygui.equals("0")) {
					query = " select PK_SEQ, TEN from NHAPHANPHOI where PRIANDSECOND = 1 AND ISKYGUI = 1 AND TRANGTHAI = 1 ";
					//System.out.println("[ErpChuyenkhoSX.createRs] nccXuatRs query = " + query);
					this.nccXuatRs = db.get(query);
				} else if (this.loaichuyenkygui.equals("1")) {
					query = " select PK_SEQ, TEN from ERP_NHANVIEN where TRANGTHAI = 1 AND COBANKYGUI = 1 ";
					//System.out.println("[ErpChuyenkhoSX.createRs] nccXuatRs query = " + query);
					this.nccXuatRs = db.get(query);
				}
			}
			
			//Lấy danh sách NCC, NPP, hoặc nhân viên nhận tùy nội dung nhận
			
			if( this.khoNhanId.trim().equals("100013") )
			{
				query = "select PK_SEQ, TEN from ERP_NHACUNGCAP where KhoNL_Nho_GC = '" + this.khoNhanId + "'";
				//System.out.println("[ErpChuyenkhoSX.createRs] nccNhanRs query = " + query);
				this.nccNhanRs = db.get(query);
			}
			else if(this.khoNhanId.trim().equals("100015")) 
			{
				if(this.loainhankygui.equals("0")) {
				query = " select PK_SEQ, TEN from NHAPHANPHOI where PRIANDSECOND = 1 AND ISKYGUI = 1 AND TRANGTHAI = 1 ";
				//System.out.println("[ErpChuyenkhoSX.createRs] nccNhanRs query = " + query);
				this.KhachhangKyguiRs = db.get(query);
				
				} else if (this.loainhankygui.equals("1")) {
				 
				query = " select PK_SEQ, TEN from ERP_NHANVIEN where TRANGTHAI = 1 AND COBANKYGUI = 1 ";
				//System.out.println("[ErpChuyenkhoSX.createRs] nccNhanRs query = " + query);
				this.NhanvienKyguiRs = db.get(query);
				}
			}
 
			query="select PK_SEQ,TEN from NHAPHANPHOI   where   trangthai=1";
			this.KhachhangRs=this.db.get(query);
			
			
			query="select PK_SEQ,MA +'-'+ TEN as TEN from ERP_NHANVIEN where cobankygui=0      ";
			this.RsNhanVien=this.db.get(query);
			
			query="select PK_SEQ,TEN from ERP_DONVITHUCHIEN   ";
			this.PhongbanRs=this.db.get(query);
	}

	private void createChuyenKho_SanPham_NhanHang() 
	{
		String query = " select b.PK_SEQ as spId, b.MA as spMa,  b.Ten as spTen, a.SOLO, SUM(a.SOLUONGXUAT) as tongXuat, isnull(Sum(a.SOLUONGNHAN), 0) as tongNhan  " +
				"from ERP_CHUYENKHO_SANPHAM a inner Join SANPHAM b on a.SANPHAM_FK = b.PK_SEQ  " +
				"where a.CHUYENKHO_FK = '" + this.id + "'  " +
				"group by b.PK_SEQ, b.MA, b.Ten, a.SOLO  " +
				"having SUM(a.SOLUONGXUAT) > 0 ";
		
		System.out.println("1.Khoi tao SP: " + query);
		ResultSet spRs = db.get(query);
		
		List<IYeucau> spList = new ArrayList<IYeucau>();
		
		if(spRs != null)
		{
			try 
			{
				IYeucau sp = null;
				while(spRs.next())
				{
					String spId = spRs.getString("spId");
					String spMa = spRs.getString("spMa");
					String spTen = spRs.getString("spTen");
					String solo = spRs.getString("solo");
					
					sp = new Yeucau();
					sp.setId(spId);
					sp.setMa(spMa);
					sp.setTen(spTen);
					sp.setSolo(solo);
					
					if(spRs.getString("tongXuat").trim().length() > 0)
						sp.setSoluongYC(spRs.getString("tongXuat"));
					
					if(spRs.getString("tongNhan").trim().length() > 0)
						sp.setSoluongNhan(spRs.getString("tongNhan"));
					
					
					//Create kho nhan
					query = " select vitri, soluong, khu from ERP_CHUYENKHO_SP_NHANHANG   " +
							" where chuyenkho_fk = '" + this.id + "' and sanpham_fk = '" + spId + "' and solo = '" + solo + "'";
					
					System.out.println("__Khoi tao kho nhan: " + query);
					
					ResultSet rsSpDetail = db.get(query);
					List<ISpDetail> spConList = new ArrayList<ISpDetail>();
					ISpDetail spCon = null;
					if(rsSpDetail != null)
					{
						while(rsSpDetail.next())
						{
							String slg = rsSpDetail.getString("soluong");
							String khu = rsSpDetail.getString("khu");
							String vitriId = khu + " - " + rsSpDetail.getString("vitri");
							
							spCon = new SpDetail();
							spCon.setSoluong(slg);
							spCon.setKhu(khu);
							spCon.setVitriId(vitriId);
							
							spConList.add(spCon);
						}
						rsSpDetail.close();
					}
					
					sp.setSpDetailList(spConList);	
					
					spList.add(sp);
				}
				spRs.close();
			} 
			catch (Exception e) 
			{
				System.out.println("115.Exception: " + e.getMessage());
			}
			
			this.spList = spList;
		}
	}

	private void createChuyenKho_SanPham_XuatHang() 
	{
		String query =  " SELECT CK.KHOXUAT_FK , B.PK_SEQ AS SPID, B.MA AS SPMA,  B.TEN AS SPTEN  " +
						"  ,A.SOLUONGYEUCAU  , A.SOLUONGXUAT  AS TONGXUAT,DVDL.DONVI "+ 
						" FROM ERP_CHUYENKHO_SANPHAM  A "+
						" INNER JOIN ERP_CHUYENKHO CK ON CK.PK_SEQ=A.CHUYENKHO_FK "+
						" INNER JOIN SANPHAM B ON A.SANPHAM_FK = B.PK_SEQ  " +
						" INNER JOIN DONVIDOLUONG DVDL ON DVDL.PK_SEQ=B.DVDL_FK   "+
						" WHERE A.CHUYENKHO_FK = "+this.id ;
												 
		
		System.out.println("1.Khoi tao SP: " + query);
		ResultSet spRs = db.get(query);
		
		List<IYeucau> spList = new ArrayList<IYeucau>();
		
		if(spRs != null)
		{
			try 
			{
				IYeucau sp = null;
				while(spRs.next())
				{
					String spId = spRs.getString("spId");
					String spMa = spRs.getString("spMa");
					String spTen = spRs.getString("spTen");
 
					sp = new Yeucau();
					sp.setId(spId);
					sp.setMa(spMa);
					sp.setTen(spTen);
					sp.setdonvi(spRs.getString("donvi"));
					
					sp.setSoluongYC(spRs.getString("SOLUONGYEUCAU"));
					sp.setTongSoluongDaXuat(spRs.getString("TONGXUAT"));
 
					query= "select * from ERP_CHUYENKHO_SP_XUATHANG where  CHUYENKHO_FK="+this.id+"";
					
					ResultSet rs=db.get(query);
					boolean daxuat=false;
					if(rs.next()){
						daxuat=true;
					}
					
					query=  " SELECT A.SOLO, A.NGAYSANXUAT, ISNULL((  "+
							" SELECT SOLUONG   FROM ERP_CHUYENKHO_SP_XUATHANG   "+
							" WHERE SANPHAM_FK=A.SANPHAM_FK   AND CHUYENKHO_FK="+this.id+" AND SOLO=A.SOLO "+
							" ),0) AS SOLUONGDAXUAT ,AVAILABLE AS SOLUONG " +
							" FROM ERP_KHOTT_SP_CHITIET A "+  
							" WHERE SANPHAM_FK="+spId+" AND KHOTT_FK= "+spRs.getString("KHOXUAT_FK")+" AND AVAILABLE +ISNULL(( "+ 
							" SELECT SOLUONG   FROM ERP_CHUYENKHO_SP_XUATHANG   "+
							" WHERE SANPHAM_FK=A.SANPHAM_FK   AND CHUYENKHO_FK="+this.id+" AND SOLO=A.SOLO "+
							" ),0)  > 0  "+
							" ORDER BY NGAYHETHAN ";
					if(this.khoXuatId.trim().equals("100013")){
						if(this.nccXuatId.length() >0){
							query=  " SELECT A.SOLO, A.NGAYSANXUAT, ISNULL((  "+
							" SELECT SOLUONG   FROM ERP_CHUYENKHO_SP_XUATHANG   "+
							" WHERE SANPHAM_FK=A.SANPHAM_FK   AND CHUYENKHO_FK="+this.id+" AND SOLO=A.SOLO "+
							" ),0) AS SOLUONGDAXUAT ,AVAILABLE AS SOLUONG  FROM ERP_KHOTT_SP_CHITIET_NCC A "+  
							" WHERE A.NCC_FK="+this.nccXuatId+" and  SANPHAM_FK="+spId+" AND KHOTT_FK= "+spRs.getString("KHOXUAT_FK")+" AND AVAILABLE +ISNULL(( "+ 
							" SELECT SOLUONG   FROM ERP_CHUYENKHO_SP_XUATHANG   "+
							" WHERE SANPHAM_FK=A.SANPHAM_FK   AND CHUYENKHO_FK="+this.id+" AND SOLO=A.SOLO "+
							" ),0)  > 0  "+
							" ORDER BY NGAYHETHAN";
						}
					}else{
							if(!this.loaichuyenkygui.equals("")){
								if(this.loaichuyenkygui.equals("1")){
									//nhan vien ký gửi
									query=  " SELECT A.SOLO, A.NGAYSANXUAT, ISNULL((  "+
									" SELECT SOLUONG   FROM ERP_CHUYENKHO_SP_XUATHANG   "+
									" WHERE SANPHAM_FK=A.SANPHAM_FK   AND CHUYENKHO_FK="+this.id+" AND SOLO=A.SOLO "+
									" ),0) AS SOLUONGDAXUAT ,AVAILABLE AS SOLUONG  FROM ERP_KHOTT_SP_CHITIET_KYGUINHANVIEN A "+  
									" WHERE NHANVIEN_FK="+this.nccXuatId+" AND  SANPHAM_FK="+spId+" AND KHOTT_FK= "+spRs.getString("KHOXUAT_FK")+" AND AVAILABLE +ISNULL(( "+ 
									" SELECT SOLUONG   FROM ERP_CHUYENKHO_SP_XUATHANG   "+
									" WHERE SANPHAM_FK=A.SANPHAM_FK   AND CHUYENKHO_FK="+this.id+" AND SOLO=A.SOLO "+
									" ),0)  > 0  "+
									" ORDER BY NGAYHETHAN";
									
								}else{
									// khách hàng ký gửi 
									
									query=  " SELECT A.SOLO, A.NGAYSANXUAT,ISNULL((  "+
									" SELECT SOLUONG   FROM ERP_CHUYENKHO_SP_XUATHANG   "+
									" WHERE SANPHAM_FK=A.SANPHAM_FK   AND CHUYENKHO_FK="+this.id+" AND SOLO=A.SOLO "+
									" ),0) AS SOLUONGDAXUAT ,AVAILABLE AS SOLUONG  FROM ERP_KHOTT_SP_CHITIET_KYGUINPP A "+  
									" WHERE NPP_FK ="+this.nccXuatId+" AND  SANPHAM_FK="+spId+" AND KHOTT_FK= "+spRs.getString("KHOXUAT_FK")+" AND AVAILABLE +ISNULL(( "+ 
									" SELECT SOLUONG   FROM ERP_CHUYENKHO_SP_XUATHANG   "+
									" WHERE SANPHAM_FK=A.SANPHAM_FK   AND CHUYENKHO_FK="+this.id+" AND SOLO=A.SOLO "+
									" ),0)  > 0  "+
									" ORDER BY NGAYHETHAN";
								}
							}
					}
					System.out.println("__Khoi tao kho xuat: " + query);
					double tongsoluongxuat=spRs.getDouble("tongXuat");
					ResultSet rsSpDetail = db.get(query);
					List<ISpDetail> spConList = new ArrayList<ISpDetail>();
					ISpDetail spCon = null;
					 
						while(rsSpDetail.next())
						{	
							spCon = new SpDetail();
						 
							double slgton = rsSpDetail.getDouble("soluong")+rsSpDetail.getDouble("SOLUONGDAXUAT")  ;
							String solo = rsSpDetail.getString("solo");
							String ngaysanxuat = rsSpDetail.getString("ngaysanxuat");
							
							if(daxuat){
								spCon.setSoluong(formatter1.format(rsSpDetail.getDouble("SOLUONGDAXUAT")));
							}else{
								if( tongsoluongxuat >0) {
								if(tongsoluongxuat <rsSpDetail.getDouble("soluong")){
									spCon.setSoluong(formatter1.format(tongsoluongxuat) );
									tongsoluongxuat=0;
								}else{
									tongsoluongxuat=tongsoluongxuat-rsSpDetail.getDouble("soluong");
									spCon.setSoluong(formatter1.format(slgton));
								}
								}else{
									spCon.setSoluong("0");
								}
							}
							spCon.setSoluongton(formatter1.format(slgton));
							spCon.setSolo(solo);
							spCon.setNgaysanxuat(ngaysanxuat);
							spConList.add(spCon);
						 
						}
						
						if(tongsoluongxuat >0 && daxuat==false){
							this.msg="Số lượng trong kho của sản phẩm : "+spMa +"-" +spTen+".Không còn đủ hàng để xuất.Vui lòng kiểm tra lại";
						}
						rsSpDetail.close();
 
					sp.setSpDetailList(spConList);	
					
					spList.add(sp);
				}
				spRs.close();
			} 
			catch (Exception e) 
			{
				e.printStackTrace();
				
			}
			
			this.spList = spList;
		}
	}

	public void initView() 
	{
		String query = 
			" select ISNULL(KYHIEU,'') AS  KYHIEU ,ISNULL(SOCHUNGTUIN,'') AS SOCHUNGTUIN ,  ISNULL(LENHSANXUAT_FK,ISNULL(MUAHANG_FK,0)) AS POLSX ,isnull(Ngayyeucau,'') as Ngayyeucau, ISNULL(KHO.LOAI,'')  AS LOAIKHONHAN, isnull(LOAIDOITUONG,'') as LOAIDOITUONG ,PHONGBAN_FK,NHANVIEN_FK,LENHDIEUDONG,NGAYDIEUDONG,LENHDIEUDONGCUA,VEVIEC " +
			",NGUOIVANCHUYEN,PHUONGTIEN,ISNULL( COXACNHANNHAPKHO,0) AS COXACNHANNHAPKHO, " +
			" noidungxuat_fk, ngaychuyen, lydo, khoxuat_fk, khonhan_fk, ck.trangthai, isnull(trangthaisp, 0) as trangthaisp, " +
			" NCC_CHUYEN_FK, NCC_NHAN_FK, LOAICHUYENKYGUI, LOAINHANKYGUI   " +
			" from ERP_YEUCAUCHUYENKHO ck " +
			" LEFT JOIN ERP_KHOTT  KHO ON KHO.PK_SEQ= khonhan_fk " +
			" where ck.pk_seq = '" + this.id + "' ";
		System.out.println("query  : "+query);
		ResultSet rs = db.get(query);
		if(rs != null)
		{
			try 
			{
				if(rs.next())
				{
					this.Loaikhonhan=rs.getString("LOAIKHONHAN");
					this.sochungtuin=rs.getString("SOCHUNGTUIN");
					this.Kyhieu=rs.getString("kyhieu");
					this.phongbanid=rs.getString("PHONGBAN_FK");
					this.NhanvienId=rs.getString("nhanvien_fk");
					this.CheckNhanHang=rs.getString("COXACNHANNHAPKHO");
					this.LENHDIEUDONG=rs.getString("LENHDIEUDONG");
					this.CUA=rs.getString("LENHDIEUDONGCUA");
					this.VEVIEC=rs.getString("VEVIEC");
					this.NGUOICHUYEN=rs.getString("NGUOIVANCHUYEN");
					this.NGAYDIEUDONG=rs.getString("NGAYDIEUDONG");
					this.PHUONGTIEN=rs.getString("PHUONGTIEN");
					this.Loaidoituong=rs.getString("LOAIDOITUONG");
					this.ndxId = rs.getString("noidungxuat_fk");
					this.ngayyeucau = rs.getString("ngaychuyen");
					this.lydoyeucau = rs.getString("lydo");
					this.khoXuatId = rs.getString("khoxuat_fk");
					this.khoNhanId = rs.getString("khonhan_fk");
					if(this.khoNhanId==null){
						this.khoNhanId="";
					}
					this.trangthai = rs.getString("trangthai");
					this.trangthaisp = rs.getString("trangthaisp");
					this.loaichuyenkygui = rs.getString("LOAICHUYENKYGUI") == null ? "" : rs.getString("LOAICHUYENKYGUI");
					this.loainhankygui = rs.getString("LOAINHANKYGUI") == null ? "" : rs.getString("LOAINHANKYGUI");
					this.nccXuatId = rs.getString("NCC_CHUYEN_FK") == null ? "" : rs.getString("NCC_CHUYEN_FK");
					this.nccNhanId = rs.getString("NCC_NHAN_FK") == null ? "" : rs.getString("NCC_NHAN_FK");
					this.KhachhangKyguiId=this.nccNhanId;
					this.NhanvienKyguiId=this.nccNhanId;
					this.PoLsx = rs.getString("POLSX");
				}
				rs.close();
			} 
			catch (Exception e) {
				e.printStackTrace();
			}
		}
		
		this.CreateRsXuatKho();
		
		if( this.spList.size() <= 0 )
		{
			this.createChuyenKho_SanPham_View();
		}
	}
	
	private void createChuyenKho_SanPham_View() 
	{
		String query =  " SELECT CK.KHOXUAT_FK , B.PK_SEQ AS SPID, B.MA AS SPMA,   B.TEN AS SPTEN  ,  "+ 
		                " B.TRONGLUONG as TRONGLUONG, B.THETICH as THETICH, "+
						" A.SOLUONGYEUCAU  , isnull((SELECT SUM(CKSP.SOLUONGNHAN) FROM ERP_CHUYENKHO_SANPHAM CKSP "+ 
						" INNER JOIN ERP_CHUYENKHO CK1 ON CK1.PK_SEQ=CKSP.CHUYENKHO_FK WHERE CK1.YEUCAUCHUYENKHO_FK=CK.PK_SEQ "+ 
						" AND CKSP.SANPHAM_FK=A.SANPHAM_FK and ck1.TRANGTHAI=3 ),0)  AS TONGXUAT,DVDL.DONVI    "+
						" FROM ERP_YEUCAUCHUYENKHO_SANPHAM  A   "+
						" INNER JOIN ERP_YEUCAUCHUYENKHO CK ON CK.PK_SEQ=A.CHUYENKHO_FK "+  
						" INNER JOIN SANPHAM B ON A.SANPHAM_FK = B.PK_SEQ    "+
						" INNER JOIN DONVIDOLUONG DVDL ON DVDL.PK_SEQ= B.DVDL_FK "+   
						" WHERE A.CHUYENKHO_FK ="+this.id;
		System.out.println("1.Khoi tao SP: " + query);
		ResultSet spRs = db.get(query);
		List<IYeucau> spList = new ArrayList<IYeucau>();
		Double tongthetich_ = (double) 0;
		Double tongkhoiluong_ = (double) 0;
		if(spRs != null)
		{
		try 
		{
		IYeucau sp = null;
		while(spRs.next())
		{
			String spId = spRs.getString("spId");
			String spMa = spRs.getString("spMa");
			String spTen = spRs.getString("spTen");
			double thetich = spRs.getDouble("THETICH");
			double khoiluong = spRs.getDouble("TRONGLUONG");
			
			sp = new Yeucau();
			sp.setId(spId);
			sp.setMa(spMa);
			sp.setTen(spTen);
			sp.setdonvi(spRs.getString("donvi"));
			sp.setSoluongYC(spRs.getString("SOLUONGYEUCAU"));
			sp.setTongSoluongDaXuat(spRs.getString("TONGXUAT"));
			
			tongthetich_ += thetich * Double.parseDouble(sp.getSoluongYC());
			tongkhoiluong_ += khoiluong * Double.parseDouble(sp.getSoluongYC());

			query=  "SELECT SOLUONG ,SOLO  FROM ERP_CHUYENKHO_SP_XUATHANG   "+
					" WHERE SANPHAM_FK="+spId+"   AND CHUYENKHO_FK="+this.id+"";
 
			ResultSet rsSpDetail = db.get(query);
			List<ISpDetail> spConList = new ArrayList<ISpDetail>();
			ISpDetail spCon = null;
			 
				while(rsSpDetail.next())
				{	
					spCon = new SpDetail();
					 
					String solo = rsSpDetail.getString("solo");
					spCon.setSoluong(rsSpDetail.getString("soluong")); 
					spCon.setSolo(solo);
					spConList.add(spCon);
				 
				}
 
				rsSpDetail.close();
		
			sp.setSpDetailList(spConList);	
			
			spList.add(sp);
		}
			this.tongkhoiluong = tongkhoiluong_;
			this.tongthetich = tongthetich_;
			spRs.close();
		} 
		catch (Exception e) 
		{
			e.printStackTrace();
		
		}
		this.spList = spList;
		}
	}
 
	public void DBclose() {
		
		try{
			
			if(khoNhanRs!=null){
				khoNhanRs.close();
			}
			
			if(lsxRs!=null){
				lsxRs.close();
			}
			if(spList!=null){
				spList.clear();
			}
			if(spChoNhapList!=null){
				spChoNhapList.clear();
			}
			if(khuList!=null){
				khuList.clear();
			}
			
			if(vitriList!=null){
				vitriList.clear();
			}
			
			if(khoXuatRs!=null){
				khoXuatRs.close();
			}
			this.db.shutDown();
			
		}catch(Exception er){
			
		}
	}

	@Override
	public String getCheckNhanHang() {
		// TODO Auto-generated method stub
		return this.CheckNhanHang;
	}

	@Override
	public void setCheckNhanHang(String CheckNhanHang) {
		// TODO Auto-generated method stub
		this.CheckNhanHang=CheckNhanHang;
	}

	@Override
	public String getLenhdieudong() {
		// TODO Auto-generated method stub
		return this.LENHDIEUDONG;
	}

	@Override
	public void setLenhdieudong(String lenhdieudong) {
		// TODO Auto-generated method stub
		this.LENHDIEUDONG=lenhdieudong;
	}

	@Override
	public String getNgaydieudong() {
		// TODO Auto-generated method stub
		return this.NGAYDIEUDONG;
	}

	@Override
	public void setNgaydieudong(String ngaydieudong) {
		// TODO Auto-generated method stub
		this.NGAYDIEUDONG=ngaydieudong;
	}

	@Override
	public String getCua() {
		// TODO Auto-generated method stub
		return this.CUA;
	}

	@Override
	public void setCua(String cua) {
		// TODO Auto-generated method stub
		this.CUA=cua;
	}

	@Override
	public String getVeviec() {
		// TODO Auto-generated method stub
		return this.VEVIEC;
	}

	@Override
	public void setVeviec(String veviec) {
		// TODO Auto-generated method stub
		this.VEVIEC=veviec;
	}

	@Override
	public String getSohopdong() {
		// TODO Auto-generated method stub
		return this.SOHOPDONG;
	}

	@Override
	public void setSohopdong(String sohopdong) {
		// TODO Auto-generated method stub
		this.SOHOPDONG=sohopdong;
	}

	@Override
	public String getNguoichuyen() {
		// TODO Auto-generated method stub
		return this.NGUOICHUYEN;
	}

	@Override
	public void setNguoichuyen(String nguoichuyen) {
		// TODO Auto-generated method stub
		this.NGUOICHUYEN=nguoichuyen;
	}

	@Override
	public String getPhuongtien() {
		// TODO Auto-generated method stub
		return this.PHUONGTIEN;
	}

	@Override
	public void setPhuongtien(String phuongtien) {
		// TODO Auto-generated method stub
		this.PHUONGTIEN=phuongtien;
	}

	@Override
	public String getKhachhangId() {
		// TODO Auto-generated method stub
		return this.KhachhangId;
	}

	@Override
	public void setKhachhangId(String KhId) {
		// TODO Auto-generated method stub
		this.KhachhangId=KhId;
	}

	@Override
	public ResultSet getKhachhangRs() {
		// TODO Auto-generated method stub
		return this.KhachhangRs;
	}

	@Override
	public void setKhachhangRs(ResultSet rs) {
		// TODO Auto-generated method stub
		this.KhachhangRs=rs;
	}

	@Override
	public String getLoaidoituong() {
		// TODO Auto-generated method stub
		return this.Loaidoituong;
	}

	@Override
	public void setLoaidoituong(String loaidt) {
		// TODO Auto-generated method stub
		this.Loaidoituong=loaidt;
	}

	@Override
	public String getNhanVienId() {
		// TODO Auto-generated method stub
		return this.NhanvienId;
	}

	@Override
	public void setNhanVienId(String NvId) {
		// TODO Auto-generated method stub
		this.NhanvienId=NvId;
	}

	@Override
	public ResultSet getNhanvienRs() {
		// TODO Auto-generated method stub
		return this.RsNhanVien;
	}

	@Override
	public void setNhanvienRs(ResultSet rs) {
		// TODO Auto-generated method stub
		this.RsNhanVien=rs;
	}

	@Override
	public String getPhongBanId() {
		// TODO Auto-generated method stub
		return this.phongbanid;
	}

	@Override
	public void setPhongBanId(String PhongbanId) {
		// TODO Auto-generated method stub
		this.phongbanid=PhongbanId;
	}

	@Override
	public ResultSet getPhongbanRs() {
		// TODO Auto-generated method stub
		return this.PhongbanRs;
	}

	@Override
	public void setPhongbanRs(ResultSet rs) {
		// TODO Auto-generated method stub
		this.PhongbanRs=rs;
	}

	@Override
	public String getKhachhangKyguiId() {
		// TODO Auto-generated method stub
		return this.KhachhangKyguiId;
	}

	@Override
	public void setKhachhangKyguiId(String KhId) {
		// TODO Auto-generated method stub
		this.KhachhangKyguiId=KhId;
	}

	@Override
	public ResultSet getKhachhangKyguiRs() {
		// TODO Auto-generated method stub
		return this.KhachhangKyguiRs;
	}

	@Override
	public void setKhachhangKyguiRs(ResultSet rs) {
		// TODO Auto-generated method stub
		 this.KhachhangKyguiRs=rs;
	}

	@Override
	public String getNhanvienKyguiId() {
		// TODO Auto-generated method stub
		return this.NhanvienKyguiId;
	}

	@Override
	public void setNhanvienKyguiId(String nvid) {
		// TODO Auto-generated method stub
		this.NhanvienKyguiId=nvid;
	}

	@Override
	public ResultSet getNhanVienKyguiRs() {
		// TODO Auto-generated method stub
		return this.NhanvienKyguiRs;
	}

	@Override
	public void setNhanVienKyguiRs(ResultSet rs) {
		// TODO Auto-generated method stub
		this.NhanvienKyguiRs=rs;
	}

	@Override
	public String getLoaiKhonhan() {
		// TODO Auto-generated method stub
		return this.Loaikhonhan;
	}

	@Override
	public void setLoaiKhonhan(String LoaiKhonhan) {
		// TODO Auto-generated method stub
		this.Loaikhonhan=LoaiKhonhan;
	}

	@Override
	public String gettask() {
		// TODO Auto-generated method stub
		return this.task;
	}

	@Override
	public void settask(String _task) {
		// TODO Auto-generated method stub
		this.task=_task;
	}

	@Override
	public String getNgaydenghi() {
		// TODO Auto-generated method stub
		return this.Ngaydenghi;
	}

	@Override
	public void setNgaydenghi(String ngayyeucau) {
		// TODO Auto-generated method stub
		this.Ngaydenghi=ngayyeucau;
	}

	@Override
	public String getPoLsx() {
		// TODO Auto-generated method stub
		return this.PoLsx;
	}

	@Override
	public void setPoLsx(String PoLsx) {
		// TODO Auto-generated method stub
		this.PoLsx=PoLsx;
	}

	@Override
	public String getKyhieu() {
		// TODO Auto-generated method stub
		return this.Kyhieu;
	}

	@Override
	public void setKyhieu(String Kyhieu) {
		// TODO Auto-generated method stub
		this.Kyhieu=Kyhieu;
	}

	@Override
	public String getSochungtuin() {
		// TODO Auto-generated method stub
		return this.sochungtuin;
	}

	@Override
	public void setSochungtuin(String Sochungtuin) {
		// TODO Auto-generated method stub
		this.sochungtuin=Sochungtuin;
	}

	@Override
	public void InitNew() {
		// TODO Auto-generated method stub
		try{
			String sql=" select ISNULL(kyhieu,'') as kyhieu  from erp_chuyenkho where  "+
					   " PK_SEQ= (select MAX(PK_SEQ) from ERP_CHUYENKHO where NOIDUNGXUAT_FK=100006 and kyhieu <>'' and kyhieu is not null) ";
			ResultSet rs=db.get(sql);
			if(rs.next()){
				this.Kyhieu=rs.getString("kyhieu");
			}
			rs.close();
		}catch(Exception er){
			er.printStackTrace();
		}
	}

	@Override
	public String getMuahangid() {
		// TODO Auto-generated method stub
		return this.muahangId;
	}

	@Override
	public void setMuahangId(String muhangid) {
		// TODO Auto-generated method stub
		this.muahangId= muhangid;
	}

	@Override
	public String CreateChuyenKho(String id_ycck) {
		// TODO Auto-generated method stub
		try{
			db.getConnection().setAutoCommit(false);
			
			String sql="select pk_seq from ERP_CHUYENKHO where  trangthai in (1,2) and YEUCAUCHUYENKHO_FK ="+id_ycck;
			
			ResultSet rs=db.get(sql);
			if(rs.next()){
				db.update("rollback");
				return "Yêu cầu này đang có xuất kho chưa hoàn tất,vui lòng hoàn tất phiếu nhận hàng của yêu cầu để tạo xuất hàng khác ";
			}
			
			sql=  	 " SELECT   noidungxuat_fk  ,isnull(muahang_fk,0) as muahang_fk   FROM ERP_YEUCAUCHUYENKHO WHERE PK_SEQ="+id_ycck;
			boolean isckbenngoai=false;
			rs=db.get(sql);
			if(rs.next()){
				if(rs.getString("noidungxuat_fk").trim().equals("100006") && rs.getString("muahang_fk").equals("0")){
					isckbenngoai=true;
				}
			}
			rs.close();
			// Lay SOCHUNGTUIN MAX trong CHUYENKHO
			String query=" select max( cast(SOCHUNGTUIN as float))  as somax_CK from  ERP_CHUYENKHO where NOIDUNGXUAT_FK= '100006' and KYHIEU like '%PC/16T%' and TRANGTHAI != 4 AND IS_SOCHUNGTUIN_NEW LIKE '1'";
			
			ResultSet rsLaysoin_CK=db.get(query);
			long soin_CK= 0;
			String chuoi="";
			
			if(rsLaysoin_CK.next()){
				soin_CK= rsLaysoin_CK.getInt("somax_CK");
			}			

			// Rang neu Sochungtuin khong duoc > 0002000
			if(soin_CK >= 2000 )
			{
				db.update("rollback");
				return "Phiếu chuyển kho tiếp theo có số in vượt quá 0002000. Vui lòng liên hệ với admin";
			}
			
					chuoi=("000000"+ (soin_CK>0 ? (soin_CK+1) :"1"));
					
					chuoi=chuoi.substring(chuoi.length()-7,chuoi.length());
					
					 sql=" insert into ERP_CHUYENKHO (IS_SOCHUNGTUIN_NEW, YEUCAUCHUYENKHO_FK,SOCHUNGTUIN,KYHIEU,NPP_FK,NGAYYEUCAU,NGAYDIEUDONG,COXACNHANNHAPKHO,LENHDIEUDONG,LENHDIEUDONGCUA,VEVIEC,NGUOIVANCHUYEN,PHUONGTIEN ,LOAIDOITUONG,PHONGBAN_FK,NHANVIEN_FK,noidungxuat_fk, ngaychuyen, ngaynhan, ngaychot, lydo, trangthai, khoxuat_fk, khonhan_fk, trangthaisp, ngaytao, nguoitao, ngaysua, nguoisua, NCC_CHUYEN_FK, NCC_NHAN_FK, LOAICHUYENKYGUI, LOAINHANKYGUI ,MUAHANG_FK) " +
					   	 " SELECT '1', "+id_ycck+",'"+ chuoi +"' ,'PC/16T',NPP_FK,NGAYYEUCAU,NGAYDIEUDONG,COXACNHANNHAPKHO,LENHDIEUDONG,LENHDIEUDONGCUA,VEVIEC,NGUOIVANCHUYEN,PHUONGTIEN ,LOAIDOITUONG,PHONGBAN_FK,NHANVIEN_FK,noidungxuat_fk, ngaychuyen, ngaynhan, ngaychot, lydo,1, khoxuat_fk, khonhan_fk, trangthaisp, ngaytao, nguoitao, ngaysua, nguoisua, NCC_CHUYEN_FK, NCC_NHAN_FK, LOAICHUYENKYGUI, LOAINHANKYGUI,MUAHANG_FK FROM ERP_YEUCAUCHUYENKHO WHERE PK_SEQ="+id_ycck;
					 System.out.println("Câu insert "+sql);
						if(!db.update(sql)){
							db.update("rollback");
							return "Lỗi dòng lệnh :"+sql;
						}
				

			String ycnlCurrent = "";
			sql = "select IDENT_CURRENT('ERP_CHUYENKHO') as ckId";
			
			ResultSet rsPxk = db.get(sql);						
			if(rsPxk.next())
			{
				ycnlCurrent = rsPxk.getString("ckId");
				rsPxk.close();
			}
			
			/*sql=" INSERT INTO ERP_CHUYENKHO_SANPHAM (chuyenkho_fk, SANPHAM_FK, SOLUONGYEUCAU,SOLUONGXUAT,SOLUONGNHAN, vitrixuat,NGAYYEUCAU) " +
			" SELECT "+ycnlCurrent+",A.SANPHAM_FK,A.SOLUONG,A.SOLUONG,A.SOLUONG,A.VITRIXUAT,A.NGAYYEUCAU FROM ( "+
			" SELECT   SANPHAM_FK, SOLUONGYEUCAU - ISNULL((  "+
			" SELECT SUM(SOLUONGNHAN) FROM ERP_CHUYENKHO_SANPHAM CKSP  "+
			" INNER JOIN ERP_CHUYENKHO CK ON CK.PK_SEQ=CKSP.CHUYENKHO_FK "+
			" WHERE CKSP.SANPHAM_FK=A.SANPHAM_FK AND CK.YEUCAUCHUYENKHO_FK=A.CHUYENKHO_FK AND CK.TRANGTHAI=3),0) AS SOLUONG, vitrixuat,NGAYYEUCAU "+
			" FROM ERP_YEUCAUCHUYENKHO_SANPHAM A "+
			" WHERE A.CHUYENKHO_FK="+id_ycck +
			" AND A.SOLUONGYEUCAU -ISNULL((  "+
			" SELECT SUM(SOLUONGNHAN) FROM ERP_CHUYENKHO_SANPHAM CKSP  "+
			" INNER JOIN ERP_CHUYENKHO CK ON CK.PK_SEQ=CKSP.CHUYENKHO_FK "+
			" WHERE CKSP.SANPHAM_FK=A.SANPHAM_FK AND CK.YEUCAUCHUYENKHO_FK=A.CHUYENKHO_FK AND CK.TRANGTHAI=3),0) >0 "+
			" ) AS A" ;*/
			
			
			sql=" SELECT "+ycnlCurrent+",A.SANPHAM_FK,A.SOLUONG,A.SOLUONG,A.SOLUONG,A.VITRIXUAT,A.NGAYYEUCAU FROM ( "+
			" SELECT   SANPHAM_FK, SOLUONGYEUCAU - ISNULL((  "+
			" SELECT SUM(SOLUONGNHAN) FROM ERP_CHUYENKHO_SANPHAM CKSP  "+
			" INNER JOIN ERP_CHUYENKHO CK ON CK.PK_SEQ=CKSP.CHUYENKHO_FK "+
			" WHERE CKSP.SANPHAM_FK=A.SANPHAM_FK AND CK.YEUCAUCHUYENKHO_FK=A.CHUYENKHO_FK AND CK.TRANGTHAI=3),0) AS SOLUONG, vitrixuat,NGAYYEUCAU "+
			" FROM ERP_YEUCAUCHUYENKHO_SANPHAM A "+
			" WHERE A.CHUYENKHO_FK="+id_ycck +
			" AND A.SOLUONGYEUCAU -ISNULL((  "+
			" SELECT SUM(SOLUONGNHAN) FROM ERP_CHUYENKHO_SANPHAM CKSP  "+
			" INNER JOIN ERP_CHUYENKHO CK ON CK.PK_SEQ=CKSP.CHUYENKHO_FK "+
			" WHERE CKSP.SANPHAM_FK=A.SANPHAM_FK AND CK.YEUCAUCHUYENKHO_FK=A.CHUYENKHO_FK AND CK.TRANGTHAI=3),0) >0 "+
			" ) AS A" ;
			int bien=0;
			ResultSet rssp=db.get(sql);
			while (rssp.next()){
					/*" SELECT "+ycnlCurrent+", SANPHAM_FK, SOLUONGYEUCAU,SOLUONGXUAT,SOLUONGNHAN, vitrixuat,NGAYYEUCAU FROM  " +
							" ERP_YEUCAUCHUYENKHO_SANPHAM WHERE CHUYENKHO_FK="+id_ycck;*/
 
				if(bien/15==1 && isckbenngoai){
					bien=0;
					
					// Phieu chuyen kho ke tiep co SO IN tăng lên 1 : 
					
					chuoi=("000000"+ ((Integer.parseInt(chuoi))>0 ? (Integer.parseInt(chuoi) +1) :"1"));
					
					chuoi=chuoi.substring(chuoi.length()-7,chuoi.length());
					
					 sql=" insert into ERP_CHUYENKHO (IS_SOCHUNGTUIN_NEW, YEUCAUCHUYENKHO_FK,SOCHUNGTUIN,KYHIEU,NPP_FK,NGAYYEUCAU,NGAYDIEUDONG,COXACNHANNHAPKHO,LENHDIEUDONG,LENHDIEUDONGCUA,VEVIEC,NGUOIVANCHUYEN,PHUONGTIEN ,LOAIDOITUONG,PHONGBAN_FK,NHANVIEN_FK,noidungxuat_fk, ngaychuyen, ngaynhan, ngaychot, lydo, trangthai, khoxuat_fk, khonhan_fk, trangthaisp, ngaytao, nguoitao, ngaysua, nguoisua, NCC_CHUYEN_FK, NCC_NHAN_FK, LOAICHUYENKYGUI, LOAINHANKYGUI ,MUAHANG_FK) " +
				   	 " SELECT '1', "+id_ycck+",'"+ chuoi +"' ,'PC/16T',NPP_FK,NGAYYEUCAU,NGAYDIEUDONG,COXACNHANNHAPKHO,LENHDIEUDONG,LENHDIEUDONGCUA,VEVIEC,NGUOIVANCHUYEN,PHUONGTIEN ,LOAIDOITUONG,PHONGBAN_FK,NHANVIEN_FK,noidungxuat_fk, ngaychuyen, ngaynhan, ngaychot, lydo,1, khoxuat_fk, khonhan_fk, trangthaisp, ngaytao, nguoitao, ngaysua, nguoisua, NCC_CHUYEN_FK, NCC_NHAN_FK, LOAICHUYENKYGUI, LOAINHANKYGUI,MUAHANG_FK FROM ERP_YEUCAUCHUYENKHO WHERE PK_SEQ="+id_ycck;
					 System.out.println("Câu insert "+sql);
						if(!db.update(sql)){
							db.update("rollback");
							return "Lỗi dòng lệnh :"+sql;
						}
			

						  ycnlCurrent = "";
						sql = "select IDENT_CURRENT('ERP_CHUYENKHO') as ckId";
						
						  rsPxk = db.get(sql);						
						if(rsPxk.next())
						{
							ycnlCurrent = rsPxk.getString("ckId");
							rsPxk.close();
						}
				
						
				}
				
				sql="INSERT INTO ERP_CHUYENKHO_SANPHAM (chuyenkho_fk, SANPHAM_FK, SOLUONGYEUCAU,SOLUONGXUAT,SOLUONGNHAN, vitrixuat,NGAYYEUCAU) values " +
					" ("+ ycnlCurrent+","+rssp.getString("SANPHAM_FK")+","+rssp.getString("SOLUONG")+",0,0,"+rssp.getString("VITRIXUAT")+",'"+rssp.getString("NGAYYEUCAU")+"')";
				//System.out.println("Cau chen Chuyen kho SP"+sql);
				if(!db.update(sql)){
					db.update("rollback");
					return "Lỗi dòng lệnh :"+sql;
				}
				bien++;
			}
			 
			sql="UPDATE ERP_YEUCAUCHUYENKHO SET TRANGTHAI=2 WHERE PK_SEQ="+id_ycck;
			if(!db.update(sql)){
				db.update("rollback");
				return "Lỗi dòng lệnh :"+sql;
			}
			
			db.getConnection().commit();
			db.getConnection().setAutoCommit(true);
			this.id=ycnlCurrent;
			return "";
			
		}catch(Exception err){
			db.update("rollback");
			err.printStackTrace();
			this.msg="Lỗi tạo chuyển kho :"+err.getMessage();
			return "";
		}
		 
	}


	public void initViewShop() 
	{

		String query = 
			" select ISNULL(KYHIEU,'') AS  KYHIEU ,ISNULL(SOCHUNGTUIN,'') AS SOCHUNGTUIN ,  ISNULL(LENHSANXUAT_FK,ISNULL(MUAHANG_FK,0)) AS POLSX ,isnull(Ngayyeucau,'') as Ngayyeucau, ISNULL(KHO.LOAI,'')  AS LOAIKHONHAN, isnull(LOAIDOITUONG,'') as LOAIDOITUONG ,PHONGBAN_FK,NHANVIEN_FK,LENHDIEUDONG,NGAYDIEUDONG,LENHDIEUDONGCUA,VEVIEC " +
			",NGUOIVANCHUYEN,PHUONGTIEN,ISNULL( COXACNHANNHAPKHO,0) AS COXACNHANNHAPKHO, " +
			" noidungxuat_fk, ngaychuyen, lydo, khoxuat_fk, khonhan_fk, ck.trangthai, isnull(trangthaisp, 0) as trangthaisp, " +
			" NCC_CHUYEN_FK, NCC_NHAN_FK, LOAICHUYENKYGUI, LOAINHANKYGUI   " +
			" from ERP_CHUYENKHO ck " +
			" LEFT JOIN ERP_KHOTT  KHO ON KHO.PK_SEQ= khonhan_fk " +
			" where ck.pk_seq = '" + this.id + "' ";
		System.out.println("query  : "+query);
		ResultSet rs = db.get(query);
		if(rs != null)
		{
			try 
			{
				if(rs.next())
				{
					this.Loaikhonhan=rs.getString("LOAIKHONHAN");
					this.sochungtuin=rs.getString("SOCHUNGTUIN");
					this.Kyhieu=rs.getString("kyhieu");
					this.phongbanid=rs.getString("PHONGBAN_FK");
					this.NhanvienId=rs.getString("nhanvien_fk");
					this.CheckNhanHang=rs.getString("COXACNHANNHAPKHO");
					this.LENHDIEUDONG=rs.getString("LENHDIEUDONG");
					this.CUA=rs.getString("LENHDIEUDONGCUA");
					this.VEVIEC=rs.getString("VEVIEC");
					this.NGUOICHUYEN=rs.getString("NGUOIVANCHUYEN");
					this.NGAYDIEUDONG=rs.getString("NGAYDIEUDONG");
					this.PHUONGTIEN=rs.getString("PHUONGTIEN");
					this.Loaidoituong=rs.getString("LOAIDOITUONG");
					this.ndxId = rs.getString("noidungxuat_fk");
					this.ngayyeucau = rs.getString("ngaychuyen");
					this.lydoyeucau = rs.getString("lydo");
					this.khoXuatId = rs.getString("khoxuat_fk");
					this.khoNhanId = rs.getString("khonhan_fk");
					if(this.khoNhanId==null){
						this.khoNhanId="";
					}
					this.trangthai = rs.getString("trangthai");
					this.trangthaisp = rs.getString("trangthaisp");
					this.loaichuyenkygui = rs.getString("LOAICHUYENKYGUI") == null ? "" : rs.getString("LOAICHUYENKYGUI");
					this.loainhankygui = rs.getString("LOAINHANKYGUI") == null ? "" : rs.getString("LOAINHANKYGUI");
					this.nccXuatId = rs.getString("NCC_CHUYEN_FK") == null ? "" : rs.getString("NCC_CHUYEN_FK");
					this.nccNhanId = rs.getString("NCC_NHAN_FK") == null ? "" : rs.getString("NCC_NHAN_FK");
					this.KhachhangKyguiId=this.nccNhanId;
					this.NhanvienKyguiId=this.nccNhanId;
					this.PoLsx = rs.getString("POLSX");
				}
				rs.close();
			} 
			catch (Exception e) {
				e.printStackTrace();
			}
		}
		
		this.CreateRsXuatKho();
		
		if( this.spList.size() <= 0 )
		{
			this.createChuyenKho_SanPhamSHOP_View();
		}
	
		
	}

	private void createChuyenKho_SanPhamSHOP_View() 
	{

		String query =  " SELECT CK.KHOXUAT_FK , B.PK_SEQ AS SPID, B.MA AS SPMA,   B.TEN AS SPTEN  ,  "+ 
						" A.SOLUONGYEUCAU  , A.SOLUONGXUAT  AS TONGXUAT,DVDL.DONVI    "+
						" FROM ERP_CHUYENKHO_SANPHAM  A   "+
						" INNER JOIN ERP_CHUYENKHO CK ON CK.PK_SEQ=A.CHUYENKHO_FK "+  
						" INNER JOIN SANPHAM B ON A.SANPHAM_FK = B.PK_SEQ    "+
						" INNER JOIN DONVIDOLUONG DVDL ON DVDL.PK_SEQ= B.DVDL_FK "+   
						" WHERE A.CHUYENKHO_FK ="+this.id;
		System.out.println("1.Khoi tao SP: " + query);
		ResultSet spRs = db.get(query);
		List<IYeucau> spList = new ArrayList<IYeucau>();
		if(spRs != null)
		{
		try 
		{
		IYeucau sp = null;
		while(spRs.next())
		{
			String spId = spRs.getString("spId");
			String spMa = spRs.getString("spMa");
			String spTen = spRs.getString("spTen");
			sp = new Yeucau();
			sp.setId(spId);
			sp.setMa(spMa);
			sp.setTen(spTen);
			sp.setdonvi(spRs.getString("donvi"));
			sp.setSoluongYC(spRs.getString("SOLUONGYEUCAU"));
			sp.setTongSoluongDaXuat(spRs.getString("TONGXUAT"));

			query=  "SELECT a.SOLUONG, a.SOLO, c.NGAYSANXUAT FROM ERP_CHUYENKHO_SP_XUATHANG a "+ 
					"inner join ERP_CHUYENKHO b on a.chuyenkho_fk = b.PK_SEQ inner join ERP_KHOTT_SP_CHITIET c on b.KHOXUAT_FK = c.KHOTT_FK "+ 
					"and a.sanpham_fk = c.SANPHAM_FK and a.SOLO = c.SOLO  "+
					"WHERE a.SANPHAM_FK="+spId+" AND a.CHUYENKHO_FK="+this.id+"";
			//System.out.println("Câu init sp chi tiet "+query);
			ResultSet rsSpDetail = db.get(query);
			List<ISpDetail> spConList = new ArrayList<ISpDetail>();
			ISpDetail spCon = null;
			 
				while(rsSpDetail.next())
				{	
					spCon = new SpDetail();
					 
					String solo = rsSpDetail.getString("solo");
					spCon.setSoluong(rsSpDetail.getString("soluong")); 
					spCon.setSolo(solo);
					spCon.setNgaysanxuat(rsSpDetail.getString("ngaysanxuat")); 
					spConList.add(spCon);
				 
				}
 
				rsSpDetail.close();
		
			sp.setSpDetailList(spConList);	
			
			spList.add(sp);
		}
			spRs.close();
		} 
		catch (Exception e) 
		{
			e.printStackTrace();
		
		}
		this.spList = spList;
		}
	
		
	}

public String Check_TonKhoNgay_Sp(dbutils db, String khott_fk, String spId, double soluong ,String ngaychungtu ,String loaikygui,String doituong) {
		
		try{
			NumberFormat formatter2 = new DecimalFormat("#######.######");
				if(khott_fk.equals("100013")){
					String[] param = new String[5];
				 	param[0] =khott_fk;
				    param[1] =ngaychungtu;
				    param[2] =ngaychungtu;
				    param[3] =spId;
				    param[4] =doituong;
				    
				    ResultSet tonkhongay= db.getRsByPro("REPORT_XUATNHAPTON_THEKHO_DAUKY_NCC", param);
				    double soluongtonngay=0;
				    String SPTEN="";
				  
				    if(tonkhongay.next()){
				    	soluongtonngay=tonkhongay.getDouble("TON");
				    	SPTEN=tonkhongay.getString("SPMA")+" - "+tonkhongay.getString("SPTEN");
				    	 
				    } 
				    if( Double.parseDouble(formatter2.format(soluongtonngay)) < (soluong *-1)){
				    	return "Không thể thực hiện xuất kho :    cho sản phẩm :"+SPTEN.replace("'", "")+". Tồn kho  tới ngày "+ngaychungtu +" chỉ còn :"+formatter2.format(soluongtonngay) +".Vui lòng kiểm tra lại xuất nhập tồn để thực hiện xuất kho";
				    } 
				    tonkhongay.close();
				}else{
					String[] param = new String[4];
				 	param[0] =khott_fk;
				    param[1] =ngaychungtu;
				    param[2] =ngaychungtu;
				    param[3] =spId;
				    ResultSet tonkhongay= db.getRsByPro("REPORT_XUATNHAPTON_TT_THEKHO_DAUKY", param);
				    double soluongtonngay=0;
				    String SPTEN="";
				  
				    if(tonkhongay.next()){
				    	soluongtonngay=tonkhongay.getDouble("TON");
				    	SPTEN=tonkhongay.getString("SPMA")+" - "+tonkhongay.getString("SPTEN");
				    	 
				    } 
				    
				    if( Double.parseDouble(formatter2.format(soluongtonngay)) < (soluong)){
				    	return "Không thể thực hiện xuất kho cho sản phẩm :"+SPTEN.replace("'", "")+". Tồn kho  tới ngày "+ngaychungtu +" chỉ còn :"+formatter2.format(soluongtonngay) +".Vui lòng kiểm tra lại xuất nhập tồn để thực hiện xuất kho";
				    } 
				    tonkhongay.close();
				}
			    			
			
			
		}catch(Exception er){
			er.printStackTrace();
			return  "không thể thực hiện cập nhật kho  Util.Nhap_Kho_Sp : "+er.getMessage();
		}
		return "";
	}

}
