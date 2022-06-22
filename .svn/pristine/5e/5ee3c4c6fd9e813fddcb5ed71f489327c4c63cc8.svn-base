package geso.dms.erp.beans.thutien.imp;

import geso.dms.distributor.db.sql.dbutils;
import geso.dms.distributor.util.Utility;
import geso.dms.erp.beans.thutien.IDinhkhoanco;
import geso.dms.erp.beans.thutien.IErpThutien;
import geso.dms.erp.beans.thutien.IHoadon;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Enumeration;
import java.util.Hashtable;
import java.util.List;

public class ErpThutien implements IErpThutien {
	String userId;
	String id;
	String ngaychungtu;
	String ngayghiso;
	String ctyId;

	String nhomkhtt;
	String nhomkhttId;
	ResultSet nhomkhttRs;

	String nppId;
	ResultSet nppRs;

	String htttId;
	ResultSet htttRs;

	String ndId;
	ResultSet ndRs;

	String DoiTuongTamUng;

	String nccId = "";
	ResultSet nccRs;

	String nvtuId = "";
	ResultSet nvtuRs;

	String nganhangId;
	ResultSet nganhangRs;
	String chinhanhId;
	ResultSet chinhanhRs;

	String sotaikhoan;
	String noidungtt;
	String sotientt;
	String sotienttNT;

	String sochungtu;

	String tienteId;
	ResultSet tienteRs;

	String nguoinoptien;
	String diachi;

	String hoadonId;
	List<IHoadon> hoadonList;

	String msg;
	String thuduoc;
	String thuduocNT;
	String cpnganhang;
	String cpnganhangNT;
	String chenhlech;
	String chenhlechNT;
	String tongVND;
	String tongNT;
	String tigia;
	String Tigia_hoadon;

	String chietkhau;
	String chietkhauNT;

	String DoiTuongDinhKhoan;
	String DoituongdinhkhoanId;
	String MaTenDoiTuongDinhKhoan;
	String dinhkhoanco;
	String dinhkhoancoId;

	String xoakhtratruocId;

	String chungtukemtheo;

	String lydonop;
	String nppdangnhap;

	String nvgnId;

	List<IDinhkhoanco> dinhkhoancoList;

	ResultSet taikhoanRs;

	ResultSet sotkRs;
	dbutils db;
	Utility util;

	ResultSet SanphamList;
	ResultSet NganhangList;
	ResultSet NccList;
	ResultSet TaisanList;
	ResultSet KhachhangList;
	ResultSet NhanvienList;
	ResultSet NvgnRs;

	String kbhId;
	ResultSet kbhRs;

	String doituongId;
	ResultSet doituongRs;

	String nhomkenhId;
	ResultSet nhomkenhRs;

	String bangkeId;
	ResultSet BangKeRs;

	String isNPP;
	String nppIdgoc;
	String isChuyenCN;
	
	String nppChinhanhId;
	String isthuhoCN;

	// NỘP TIỀN
	String[] MangkhId;
	String[] MangkhMa;
	String[] MangkhTen;
	String[] MangTienNop;

	String bpkinhdoanh = "";
	Hashtable<String, String> hd_chungloai;
	
	public ErpThutien() {
		this.id = "";
		this.ctyId = "";
		this.ngaychungtu = "";
		this.ngayghiso = "";
		this.nppId = "";
		this.htttId = "";
		this.ndId = "100000";
		this.nganhangId = "";
		this.chinhanhId = "";
		this.sotaikhoan = "";
		this.noidungtt = "";
		this.sotientt = "0";
		this.sotienttNT = "0";
		this.hoadonId = "";
		this.msg = "";
		this.sochungtu = "";
		this.tienteId = "100000";
		this.thuduoc = "0";
		this.thuduocNT = "0";
		this.cpnganhang = "0";
		this.cpnganhangNT = "0";
		this.chenhlech = "0";
		this.chenhlechNT = "0";
		this.tongNT = "0";
		this.tongVND = "0";
		this.chietkhau = "0";
		this.chietkhauNT = "0";
		Tigia_hoadon = "";
		this.tigia = "1";

		this.dinhkhoanco = "";
		this.dinhkhoancoId = "";
		this.DoiTuongDinhKhoan = "";

		this.DoiTuongTamUng = "";
		this.nccId = "";
		this.nvtuId = "";

		this.xoakhtratruocId = "";
		this.lydonop = "";

		this.hoadonList = new ArrayList<IHoadon>();

		this.nhomkhttId = "";
		this.nhomkhtt = "0";

		this.nguoinoptien = "";
		this.diachi = "";
		this.chungtukemtheo = "";

		this.dinhkhoancoList = new ArrayList<IDinhkhoanco>();

		this.kbhId = "";
		this.nppdangnhap = "";

		this.nvgnId = "";

		this.doituongId = "1";
		this.nhomkenhId = "";

		this.bangkeId = "";
		this.isNPP = "";
		this.nppIdgoc = "";

		this.db = new dbutils();
		this.util = new Utility();
		
		this.isChuyenCN = "0";	
		this.isthuhoCN = "0";
		this.sochungtu = getSoChungTuMax();
		
		this.nppChinhanhId = "";
		this.hd_chungloai = new Hashtable<String, String>();
	}

	public ErpThutien(String id) {
		this.id = id;
		this.ctyId = "";
		this.ngaychungtu = "";
		this.ngayghiso = "";
		this.nppId = "";
		this.htttId = "";
		this.ndId = "100000";
		this.nganhangId = "";
		this.chinhanhId = "";
		this.sotaikhoan = "";
		this.noidungtt = "";
		this.sotientt = "0";
		this.sotienttNT = "0";
		this.hoadonId = "";
		this.msg = "";
		this.sochungtu = "";
		this.tienteId = "100000";
		this.thuduoc = "0";
		this.thuduocNT = "0";
		this.cpnganhang = "0";
		this.cpnganhangNT = "0";
		this.chenhlech = "0";
		this.chenhlechNT = "0";
		this.tongNT = "0";
		this.tongVND = "0";
		this.chietkhau = "0";
		this.chietkhauNT = "0";

		this.dinhkhoanco = "";
		this.dinhkhoancoId = "";
		this.DoiTuongDinhKhoan = "";
		this.chungtukemtheo = "";

		this.DoiTuongTamUng = "";
		this.nccId = "";
		this.nvtuId = "";

		this.xoakhtratruocId = "";
		this.nhomkhttId = "";
		this.nhomkhtt = "0";

		this.lydonop = "";

		this.nguoinoptien = "";
		this.diachi = "";

		this.dinhkhoancoList = new ArrayList<IDinhkhoanco>();

		this.kbhId = "";

		this.tigia = "1";
		this.nppdangnhap = "";

		this.nvgnId = "";

		this.doituongId = "1";
		this.nhomkenhId = "";

		this.bangkeId = "";
		this.isNPP = "";

		this.isChuyenCN = "0";	
		this.isthuhoCN = "0";
		this.nppIdgoc = "";
		
		this.nppChinhanhId = "";

		this.hoadonList = new ArrayList<IHoadon>();
		this.util = new Utility();
		this.db = new dbutils();
		this.hd_chungloai = new Hashtable<String, String>();
	}

	private String getSoChungTuMax()
	{
		String maxDate = "";
		String curDate = util.getDateTime();
		String lastDayOfMonth = Utility.getFirstDayOfMonth(curDate);
		String firstDayOfMonth = Utility.getLastDayOfMonth(curDate);
		String query = 
			"select case when (COUNT(pk_seq) + 1) < 10 then '000' + CONVERT(nvarchar, (COUNT(pk_seq) + 1))\n" +
			"when (COUNT(pk_seq) + 1) < 100 then '00' + CONVERT(nvarchar, (COUNT(pk_seq) + 1))\n" +
			"when (COUNT(pk_seq) + 1) < 1000 then '0' + CONVERT(nvarchar, (COUNT(pk_seq) + 1))\n" +
			"end as maxDate\n" +
			"from ERP_THUTIEN where NGAYTAO >= '" + firstDayOfMonth + "' \n" +
			"and NGAYTAO <= '" + lastDayOfMonth + "' and trangThai <> 2";
		System.out.println("cau lay so hoa don:\n" + query + "\n--------------------------------------------");
		try {
			ResultSet rs = this.db.get(query);
			
			if (rs != null)
			{
				if (rs.next())
					maxDate = rs.getString("maxDate");
				rs.close();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return maxDate;
	}
	
	public String getDoiTuongTamUng() {
		return DoiTuongTamUng;
	}

	public void setDoiTuongTamUng(String DoiTuongTamUng) {
		this.DoiTuongTamUng = DoiTuongTamUng;

	}

	public String getNccId() {

		return this.nccId;
	}

	public void setNccId(String nccId) {

		this.nccId = nccId;
	}

	public String getXoakhtratruocId() {

		return this.xoakhtratruocId;
	}

	public void setXoakhtratruocId(String xoakhtratruocId) {

		this.xoakhtratruocId = xoakhtratruocId;
	}

	public void setNccRs(ResultSet nccRs) {
		this.nccRs = nccRs;
	}

	public ResultSet getNccRs() {
		return nccRs;
	}

	public String getNvtuId() {

		return this.nvtuId;
	}

	public void setNvtuId(String nvtuId) {

		this.nvtuId = nvtuId;
	}

	public ResultSet getNvtuRs() {
		return nvtuRs;
	}

	public void setNvtuRs(ResultSet nvtuRs) {
		this.nvtuRs = nvtuRs;
	}

	public void setChietkhau(String chietkhau) {
		this.chietkhau = chietkhau;
	}

	public String getChietkhau() {
		return chietkhau;
	}

	public void setChietkhauNT(String chietkhauNT) {
		this.chietkhauNT = chietkhauNT;
	}

	public String getChietkhauNT() {
		return chietkhauNT;
	}

	public String getUserId() {
		return this.userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getCtyId() {
		return this.ctyId;
	}

	public void setCtyId(String ctyId) {
		this.ctyId = ctyId;
	}

	public String getId() {
		return this.id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getNgaychungtu() {
		return this.ngaychungtu;
	}

	public void setNgaychungtu(String ngaychungtu) {
		this.ngaychungtu = ngaychungtu;
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

	public ResultSet getSotkRs() {
		return this.sotkRs;
	}

	public void setSotkRs(ResultSet sotkRs) {
		this.sotkRs = sotkRs;
	}

	public String getHtttId() {
		return this.htttId;
	}

	public void setHtttId(String htttId) {
		this.htttId = htttId;
	}

	public ResultSet getHtttRs() {
		return this.htttRs;
	}

	public void setHtttRs(ResultSet htttRs) {
		this.htttRs = htttRs;
	}

	public String getNganhangId() {
		return this.nganhangId;
	}

	public void setNganhangId(String nganhangId) {
		this.nganhangId = nganhangId;
	}

	public ResultSet getNganhangRs() {
		return this.nganhangRs;
	}

	public void setNganhangRs(ResultSet nganhangRs) {
		this.nganhangRs = nganhangRs;
	}

	public String getChinhanhId() {
		return this.chinhanhId;
	}

	public void setChinhanhId(String cnId) {
		this.chinhanhId = cnId;
	}

	public ResultSet getChinhanhRs() {
		return this.chinhanhRs;
	}

	public void setChinhanhRs(ResultSet chinhanhRs) {
		this.chinhanhRs = chinhanhRs;
	}

	public String getSotaikhoan() {
		return this.sotaikhoan;
	}

	public void setSotaikhoan(String sotk) {
		this.sotaikhoan = sotk;
	}

	public String getNoidungtt() {
		return this.noidungtt;
	}

	public void setNoidungtt(String ndtt) {
		this.noidungtt = ndtt;
	}

	public String getSotientt() {
		return this.sotientt;
	}

	public void setSotientt(String sotientt) {
		this.sotientt = sotientt;
	}

	public String getSotienttNT() {
		return this.sotienttNT;
	}

	public void setSotienttNT(String sotienttNT) {
		this.sotienttNT = sotienttNT;
	}

	public String getHoadonIds() {
		return this.hoadonId;
	}

	public void setHoadonIds(String hdIds) {
		this.hoadonId = hdIds;
	}

	public List<IHoadon> getHoadonRs() {
		return this.hoadonList;
	}

	public void setHoadonRs(List<IHoadon> hoadonRs) {
		this.hoadonList = hoadonRs;
	}

	public String getThuduoc() {
		return this.thuduoc;
	}

	public void setThuduoc(String thuduoc) {
		this.thuduoc = thuduoc;
	}

	public String getThuduocNT() {
		return this.thuduocNT;
	}

	public void setThuduocNT(String thuduocNT) {
		this.thuduocNT = thuduocNT;
	}

	public String getChiphinganhang() {
		return this.cpnganhang;
	}

	public void setChiphinganhang(String cpnganhang) {
		this.cpnganhang = cpnganhang;
	}

	public String getChiphinganhangNT() {
		return this.cpnganhangNT;
	}

	public void setChiphinganhangNT(String cpnganhangNT) {
		this.cpnganhangNT = cpnganhangNT;
	}

	public String getChenhlech() {
		return this.chenhlech;
	}

	public void setChenhlech(String chenhlech) {
		this.chenhlech = chenhlech;
	}

	public String getChenhlechNT() {
		return this.chenhlechNT;
	}

	public void setChenhlechNT(String chenhlechNT) {
		this.chenhlechNT = chenhlechNT;
	}

	public String getTigia() {
		return this.tigia;
	}

	public void setTigia(String tigia) {
		if (tigia.length() == 0) {
			String query = "SELECT TIGIAQUYDOI FROM ERP_TIGIA WHERE TUNGAY <= '" + this.ngaychungtu
					+ "' AND DENNGAY >= '" + this.ngaychungtu + "' AND TIENTE_FK = " + this.tienteId + "";
			System.out.println(query);
			ResultSet rs = this.db.get(query);
			try {
				if (rs != null) {
					rs.next();
					this.tigia = rs.getString("TIGIAQUYDOI");
					rs.close();
				}
			} catch (java.sql.SQLException e) {
				System.out.println(e.toString());
			}
		} else {
			this.tigia = tigia;
		}

	}

	public String getMsg() {
		return this.msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public String getTongNT() {
		return this.tongNT;
	}

	public void setTongNT(String tongNT) {
		this.tongNT = tongNT;
	}

	public String getTongVND() {
		return this.tongVND;
	}

	public void setTongVND(String tongVND) {
		this.tongVND = tongVND;
	}

	public boolean createTTHD() {

		this.ngaychungtu = Utility.getNgayHienTai();
		this.ngayghiso = Utility.getNgayHienTai();
		
		if (this.ndId.trim().length() <= 0) {
			this.msg = "Vui lòng chọn nội dung thu tiền";
			return false;
		}

		if (this.sochungtu.equals(this.getSoChungTuMax()) != true)
		{
			this.msg = "Số chừng từ phải liên tiếp với số chứng từ đã tạo trong tháng";
			return false;
		}

		if (this.ndId.equals("100000") || this.ndId.equals("100003") || this.ndId.equals("100004")) // Thu tiền bán hàng
		{
			System.out.println("size hóa đơn: "+ this.hoadonList.size());
			
			if (this.hoadonList.size() <= 0) {
				this.msg = "Vui lòng chọn hóa đơn thu tiền";
				return false;
			}

			if (this.htttId.length() <= 0) {
				this.msg = "Vui lòng chọn hình thức thanh toán";
				return false;
			}

			if (this.htttId.equals("100001")) {

				if (this.sotaikhoan.trim().length() <= 0) {
					this.msg = "Vui lòng chọn số tài khoản ngân hàng";
					return false;
				}
			}
			
			System.out.println("ndID:"+this.ndId);
			
			if(this.ndId.trim().equals("100001") || this.ndId.trim().equals("100002") || this.ndId.equals("100004") || this.ndId.trim().equals("100000") ){
				if(this.thuduoc.equals("0") )
				{
					this.msg = "Vui lòng chọn hóa đơn thu tiền";
					return false;
				}
				
				System.out.println("thuduoc: "+ thuduoc);
			}
			
		}

		if (this.ndId.equals("100002")) {
			if (this.dinhkhoancoList.size() <= 0) {
				this.msg = "Vui lòng chọn định khoản có";
				return false;
			}

			for (int j = 0; j < this.dinhkhoancoList.size(); j++) {
				IDinhkhoanco dkco = this.dinhkhoancoList.get(j);

				if (Integer.parseInt(dkco.getDoituongdinhkhoan()) >= 1) {
					if (dkco.getDoituongId().trim().length() <= 0) {
						this.msg = "Vui lòng chọn mã và tên đối tượng ";
						return false;
					}
				}
			}

		}

		if (this.DoiTuongTamUng.equals("1")) // NEU LA NCC
			this.nvtuId = "NULL";
		if (this.DoiTuongTamUng.equals("0")) // NEU LA NV
			this.nccId = "NULL";

		if (this.sotientt.trim().length() <= 0) {
			this.msg = "Vui lòng nhập số tiền thanh toán";
			return false;
		}

		try {
			String ngaytao = getDateTime();

			db.getConnection().setAutoCommit(false);

			String khachhang_fk = "NULL";

			if (!this.ndId.equals("100002")) {
				khachhang_fk = this.nppId;
			}

			if (this.nhomkhttId.trim().length() <= 0)
				this.nhomkhttId = "NULL";

			if (this.nhomkhtt.equals("1"))
				khachhang_fk = "NULL";
			this.kbhId = "NULL";
			if (this.ndId.equals("100003"))
				khachhang_fk = "NULL";
			this.kbhId = "NULL";
			if (this.ndId.equals("100002"))
				khachhang_fk = "NULL";
			this.kbhId = "NULL";
			if (!this.ndId.equals("100003")) {
				this.nvtuId = "NULL";
				this.nccId = "NULL";
			}

			String query = "";

			// HÌNH THỨC THANH TOÁN LÀ CHUYỂN KHOẢN THÌ K CẦN KẾ TOÁN TRƯỞNG DUYỆT

			if (this.ndId.equals("100004")) {
				khachhang_fk = null;
				this.isNPP = null;
			} else
				this.bangkeId = null;
			
			query = "Insert ERP_THUTIEN ( bpkinhdoanh,KBH_FK, NGUOINOPTIEN, DIACHI, NHOMKHTT_FK, CHIETKHAUNT, CHIETKHAU, TIGIA, SOTIENTTNT, THUDUOCNT, PHINGANHANGNT, THUDUOC, PHINGANHANG, CHENHLECH, NGAYCHUNGTU, NGAYGHISO, TRANGTHAI, "+
					"KHACHHANG_FK, HTTT_FK, NGANHANG_FK, CHINHANH_FK, SOTAIKHOAN, NOIDUNGTT_FK, GHICHU, SOTIENTT, TIENTE_FK, SOCHUNGTU, NGAYTAO, "+
					"NGUOITAO, NGAYSUA, NGUOISUA, NCC_FK, NHANVIEN_FK, CTKEMTHEO, ISKTTDUYET,CONGTY_FK, BANGKE_FK, PREFIX, isNPP, NPPCN_FK , ISThuhoCN ) "+
					"values(N'"+this.bpkinhdoanh+"', " + this.kbhId + ", N'" + this.nguoinoptien + "', N'" + this.diachi + "' , "+
					this.nhomkhttId + ", " + this.chietkhauNT.replace(",", "") + "," + this.chietkhau.replace(",", "")+
					" ," + this.tigia.replaceAll(",", "") + ", " + this.sotienttNT.replaceAll(",", "") + ", "+
					this.thuduocNT.replaceAll(",", "") + ", " + this.cpnganhangNT.replaceAll(",", "") + ", "+
					this.thuduoc + ", " + this.cpnganhang + ", " + this.chenhlech + ", '" + this.ngaychungtu + "', '"+
					this.ngayghiso + "', '0', " + khachhang_fk + ", '" + this.htttId + "', NULL, NULL, '', " + " '"+
					this.ndId + "', N'" + this.noidungtt + "', '" + this.sotientt.replaceAll(",", "") + "', '"+
					this.tienteId + "', '" + this.sochungtu + "', '" + ngaytao + "', '" + this.userId + "', '"+
					ngaytao + "', '" + this.userId + "', " + this.nccId + ", " + this.nvtuId + ",N'"+
					this.chungtukemtheo + "', 0, " + this.ctyId + ", " + this.bangkeId + ", 'PT', "+ (this.isNPP ==""?null:this.isNPP ) +", "+( this.nppChinhanhId ==""?null:this.nppChinhanhId ) +", "+ (this.isthuhoCN ==""?null:this.isthuhoCN ) +" )";

			System.out.println(query);

			if (!db.update(query)) {
				this.msg = "Khong the tao moi ERP_THUTIEN: " + query;
				System.out.println("[ErpThutien.createTTHD] error message:" + this.msg);
				db.getConnection().rollback();
				return false;
			}
		

			String tthdCurrent = "";
			query = "select Scope_identity() as tthdId";

			ResultSet rsTthd = db.get(query);
			if (rsTthd.next()) {
				tthdCurrent = rsTthd.getString("tthdId");
				rsTthd.close();
			}
			
			int sonetInt =  geso.dms.center.util.Utility.getChungTuSonet( db ,this.userId,"1","PhieuThu_SoChungTu","month('"+this.ngaychungtu+"')","year('"+this.ngaychungtu+"')", tthdCurrent,"ERP_THUTIEN" );
		    if(sonetInt <=0)
		    {
		    	this.db.getConnection().rollback();
				this.msg = "Không thể lấy số chứng từ tự động";
				return false;
		    }
		   
		    query = geso.dms.center.util.Utility.UpdateChungTuSoNet( "PhieuThu_SoChungTu","1", tthdCurrent,this.ngaychungtu,  sonetInt,  "ERP_THUTIEN", "PT-"  );
		    if(this.db.updateReturnInt(query)<=0)
			{
				this.db.getConnection().rollback();
				this.msg = "Số chứng từ ("+sonetInt+") đã phát sinh trong đơn gần nhất, vui lòng thử lại!";
				return false;
			}
		    query = "\n UPDATE 	ERP_THUTIEN set sonetId =  'PT-' + dbo.[PlusZero]("+sonetInt+",5)  " +
			"\n WHERE 	pk_seq = "+ tthdCurrent;
			if(!db.update(query))
			{
				this.msg = "Không thể ghi nhận so chung tu " + query;
				db.getConnection().rollback();
				return false;
			}
			
			
			
			if( this.ndId.equals("100003") || this.ndId.equals("100000") ) // THU HỒI TẠM ỨNG
			{				
				for (int i = 0; i < this.hoadonList.size(); i++) {
					IHoadon hoadon = this.hoadonList.get(i);

					String thanhtoan = hoadon.getThanhtoan().replaceAll(",", "");
					String avat = hoadon.getTongtiencoVAT().replaceAll(",","");
					String sotienNT = hoadon.getSotienNT().replaceAll(",","");
					String conlai = hoadon.getConlai().replaceAll(",", "");

					if (thanhtoan.length() > 0 ) {
						if (Float.parseFloat(thanhtoan) != 0) {

							if (this.ndId.equals("100003"))
								hoadon.setLoaihd("");

							if (hoadon.getLoaihd().equals("7"))
								{
									thanhtoan = Float.parseFloat(thanhtoan)*(-1)+"";
									avat = Float.parseFloat(avat)*(-1)+"";
									conlai = Float.parseFloat(conlai)*(-1)+"";
								}
								
							query = " Insert ERP_THUTIEN_HOADON(THUTIEN_FK, HOADON_FK, SOTIENTT, SOTIENAVAT, CONLAI,LOAIHOADON) "+
									" values('"+ tthdCurrent+ "', '"+ hoadon.getId()+ "', '"+ thanhtoan.trim()	+ "', '"+ avat+ "', '"+ conlai.trim()+ "', '"+ hoadon.getLoaihd() + "')";

							if (!db.update(query)) {
								this.msg = "Khong the tao moi ERP_THUTIEN_HOADON: "	+ query;
								System.out.println("[ErpThutien.createTTHD] error message: "+ this.msg);
								db.getConnection().rollback();
								return false;
							}

						}
					}
				}
			}
			else if (this.ndId.equals("100002")) // THU KHÁC
			{

				for (int j = 0; j < this.dinhkhoancoList.size(); j++) {
					IDinhkhoanco dkc = this.dinhkhoancoList.get(j);

					if (Double.parseDouble(dkc.getSotien().replaceAll(",", "")) > 0) {
						String doituongdk = dkc.getDoituongdinhkhoan();
						String doituong_fk = dkc.getDoituongId();

						double sotienco = Double.parseDouble(dkc.getSotien().replaceAll(",", ""));
						double sotiencoNT = 0;

						if (!this.tienteId.equals("100000")) {
							sotiencoNT = Double.parseDouble(dkc.getSotien().replaceAll(",", ""));
							sotienco = Math.round(sotiencoNT * Double.parseDouble(this.tigia.replaceAll(",", "")));
						}

						if (doituong_fk.trim().length() <= 0) {
							doituongdk = "0";
							doituong_fk = "NULL";
						}
						
						String is_NPP = "0";
						if(doituongdk.equals("5")) 
						{
							//CHỖ NÀY ĐỂ PHÂN BIỆT LÀ NPP HAY KHÁCH HÀNG HAY NHÂN VIÊN
							
							if( doituong_fk.contains("-") )
							{
								String kh[] = doituong_fk.split("-");
								doituong_fk = kh[0];
								is_NPP = kh[1];
							}
						}
						
						query = "Insert into ERP_THUTIEN_DINHKHOANCO(THUTIEN_FK, TAIKHOAN_FK, DOITUONGDINHKHOAN, DOITUONG_FK, SOTIENNT, SOTIEN, ISNPP) "
								+ "values('" + tthdCurrent + "', '" + dkc.getTaikhoanId() + "', '" + doituongdk + "', "
								+ doituong_fk + ", '" + sotiencoNT + "' , '" + sotienco + "', "+is_NPP+")";

						if (!db.update(query)) {
							this.msg = "Khong the tao moi ERP_THUTIEN_HOADON: " + query;
							db.getConnection().rollback();
							return false;
						}
					}
				}
			}

			// cập nhật mã chứng từ
			query = " update ERP_THUTIEN set machungtu = 'PT' + SUBSTRING(NGAYGHISO, 6, 2) + SUBSTRING(NGAYGHISO, 0, 5) + '-' + dbo.LaySoChungTu( " + tthdCurrent + " ) " + 
					" where pk_seq = '" + tthdCurrent + "' ";
			
			System.out.println("[ErpThutien.createTTHD] error message:" + query);
			
			if(!db.update(query))
			{
				this.msg = "Khong the tao moi ERP_THUTIEN: " + query;
				System.out.println("[ErpThutien.createTTHD] error message:" + this.msg);
				db.getConnection().rollback();
				return false;
			}
			
			if( this.hd_chungloai != null )
			{
				Enumeration<String> keys = this.hd_chungloai.keys();
				while( keys.hasMoreElements() )
				{
					String key = keys.nextElement();
					String[] arr = key.split("__");
					
					query = "insert ERP_THUTIEN_HOADON_CHITIET( THUTIEN_FK, HOADON_FK, CHUNGLOAI_FK, sotienHD, SOTIENTT ) " + 
							"select '" + tthdCurrent + "', '" + arr[0] + "', pk_seq, '" + arr[2].replaceAll(",", "") + "', '" + this.hd_chungloai.get(key) + "' " + 
							"from CHUNGLOAI where ten = N'" + arr[1] + "'  ";
					System.out.println("::: THU TIEN CHI TIET: " + query);
					if(!db.update(query))
					{
						this.msg = "KHONG THE CAP NHAT ERP_THUTIENNPP_HOADON_CHITIET: " + query;
						db.getConnection().rollback();
						return false;
					}
				}
			}
			
			db.getConnection().commit();
			db.getConnection().setAutoCommit(true);
		} 
		catch (SQLException e) {
			try {
				e.printStackTrace();
				db.getConnection().rollback();
			} catch (SQLException e1) {
			}
			return false;
		}

		return true;
	}

	public boolean updateTTHD() {

		this.ngaychungtu = Utility.getNgayHienTai();
		this.ngayghiso = Utility.getNgayHienTai();
		
		/*if ((this.ndId.equals("100000")) && this.kbhId.trim().length() <= 0) {
			this.msg = "Khách hàng này chưa thuộc Kênh bán hàng nào. Vui lòng thiết lập lại trong dữ liệu nền.";
			return false;
		}*/

		if (this.ndId.trim().length() <= 0) {
			this.msg = "Vui lòng chọn nội dung thu tiền";
			return false;
		}

		if (this.ndId.equals("100000") || this.ndId.equals("100003") || this.ndId.equals("100004")) // Thu tiền bán hàng  // THU HỒI TẠM ỨNG
		{
			if (this.hoadonList.size() <= 0) {
				this.msg = "Vui lòng chọn hóa đơn thu tiền";
				return false;
			}

			if (this.htttId.length() <= 0) {
				this.msg = "Vui lòng chọn hình thức thanh toán";
				return false;
			}
			
			if(this.ndId.trim().equals("100001") || this.ndId.trim().equals("100002") || this.ndId.equals("100004") ){
				if(this.thuduoc.equals("0") )
				{
					this.msg = "Vui lòng chọn hóa đơn thu tiền";
					return false;
				}					
			}
			
			if(this.ndId.trim().equals("100001") || this.ndId.trim().equals("100002") || this.ndId.equals("100004") || this.ndId.trim().equals("100000") ){
				if(this.thuduoc.equals("0") )
				{
					this.msg = "Vui lòng chọn hóa đơn thu tiền";
					return false;
				}
				
				System.out.println("thuduoc: "+ thuduoc);
			}

		}

		if (this.ndId.equals("100002")) {
			if (this.dinhkhoancoList.size() <= 0) {
				this.msg = "Vui lòng chọn định khoản có";
				return false;
			}

			for (int j = 0; j < this.dinhkhoancoList.size(); j++) {
				IDinhkhoanco dkco = this.dinhkhoancoList.get(j);

				if (Integer.parseInt(dkco.getDoituongdinhkhoan()) >= 1) {
					if (dkco.getDoituongId().trim().length() <= 0) {
						this.msg = "Vui lòng chọn mã và tên đối tượng ";
						return false;
					}
				}
			}

		}

		/*
		 * if(this.DoiTuongDinhKhoan.trim().length() <= 0)
		 * this.DoiTuongDinhKhoan = "NULL";
		 * if(this.dinhkhoancoId.trim().length() <= 0) this.dinhkhoancoId =
		 * "NULL"; if(this.DoituongdinhkhoanId.trim().length() <= 0)
		 * this.DoituongdinhkhoanId = "NULL";
		 */

		if (this.DoiTuongTamUng.equals("1")) // NEU LA NCC
			this.nvtuId = "NULL";
		if (this.DoiTuongTamUng.equals("0")) // NEU LA NV
			this.nccId = "NULL";

		if (this.sotientt.trim().length() <= 0) {
			this.msg = "Vui lòng nhập số tiền thanh toán";
			return false;
		}

		try {
			String ngaysua = getDateTime();

			db.getConnection().setAutoCommit(false);

			String khachhang_fk = "NULL";

			if (!this.ndId.equals("100002")) {
				khachhang_fk = this.nppId;
			}

			if (this.nhomkhttId.trim().length() <= 0)
				this.nhomkhttId = "NULL";

			if (this.nhomkhtt.equals("1"))
				khachhang_fk = "NULL";
			this.kbhId = "NULL";
			if (this.ndId.equals("100003")) // THU HỒI TẠM ỨNG
				khachhang_fk = "NULL";
			//this.kbhId = "NULL";
			if (this.ndId.equals("100002"))
				khachhang_fk = "NULL";
			//this.kbhId = "NULL";
			if (!this.ndId.equals("100003")) {
				this.nvtuId = "NULL";
				this.nccId = "NULL";
			}

			if(this.isNPP.trim().length()<=0)
				this.isNPP = null;
			// NẾU CHỌN THU THEO NHÓM KHÁCH HÀNG THÌ CHO CỘT KHACHHANG_FK = NULL
			if (this.nhomkhtt.equals("1"))
				khachhang_fk = "NULL";

			if (this.ndId.equals("100004")) {
				khachhang_fk = null;
				this.isNPP = null;
			} else
				this.bangkeId = null;
			
						
			
			if(!geso.dms.center.util.Utility.KiemTraNgayChungTuSoNet( db,this.userId,"1" ,"PhieuThu_SoChungTu",this.ngaychungtu, this.id,"ERP_THUTIEN" ))
			{
				this.db.getConnection().rollback();
				this.msg="Không được đổi ngày khác tháng với số chứng từ đã phát sinh!";
				return false;
			}
			
			
			String query = "";

			// HÌNH THỨC THANH TOÁN LÀ CHUYỂN KHOẢN THÌ K CẦN KẾ TOÁN TRƯỞNG
			// DUYỆT

			query = "update ERP_THUTIEN set bpkinhdoanh =N'"+this.bpkinhdoanh+"',NGUOINOPTIEN = N'" + this.nguoinoptien + "', DIACHI = N'" + this.diachi+
					"' , NHOMKHTT_FK = " + this.nhomkhttId + ", CHIETKHAUNT= " + this.chietkhauNT.replace(",", "") +
					" ,CHIETKHAU= " + this.chietkhau.replace(",", "") + " , SOTIENTTNT = "+
					this.sotienttNT.replaceAll(",", "") + ", TIGIA = " + this.tigia.replaceAll(",", "")+
					", THUDUOCNT = " + this.thuduocNT.replaceAll(",", "") + ", PHINGANHANGNT = "+
					this.cpnganhangNT.replaceAll(",", "") + ", THUDUOC = " + this.thuduoc + ", PHINGANHANG = "+
					this.cpnganhang + ", CHENHLECH = " + this.chenhlech + ", " + "NGAYCHUNGTU = '"+
					this.ngaychungtu + "', NGAYGHISO = '" + this.ngayghiso + "', " + "KHACHHANG_FK = "+
					khachhang_fk + ", HTTT_FK = '" + this.htttId + "', "+
					"NGANHANG_FK = NULL, CHINHANH_FK = NULL, SOTAIKHOAN = ''," + " NOIDUNGTT_FK = '" + this.ndId+
					"', GHICHU = N'" + this.noidungtt + "', SOTIENTT = '" + this.sotientt.replaceAll(",", "")+
					"', SOCHUNGTU = '" + this.sochungtu + "', TIENTE_FK = '" + this.tienteId + "', "+
					"NGAYSUA = '" + ngaysua + "', NGUOISUA = '" + this.userId + "', NCC_FK= " + this.nccId+
					" , NHANVIEN_FK= " + this.nvtuId + " , CTKEMTHEO = N'" + this.chungtukemtheo+
					"', BANGKE_FK =  " + this.bangkeId + ", PREFIX = 'PT', isNPP =  " + (this.isNPP ==""?null:this.isNPP ) + ", NPPCN_FK = "+( this.nppChinhanhId ==""?null:this.nppChinhanhId ) +", ISThuhoCN = "+( this.isthuhoCN ==""?null:this.isthuhoCN ) +
					" where PK_SEQ = '" + this.id + "'";
			
			System.out.println(query);

			if (!db.update(query)) {
				this.msg = "Khong the cap nhat ERP_THUTIEN: " + query;
				System.out.println(this.msg);
				db.getConnection().rollback();
				return false;
			}

			// cập nhật mã chứng từ
			query = " update ERP_THUTIEN set machungtu = 'PT' + SUBSTRING(NGAYGHISO, 6, 2) + SUBSTRING(NGAYGHISO, 0, 5) + '-' + dbo.LaySoChungTu( " + this.id + " ) " + 
					" where pk_seq = '" + this.id + "' ";
			
			System.out.println("[ErpThutien.createTTHD] error message:" + query);
			
			if(!db.update(query))
			{
				this.msg = "Khong the tao moi ERP_THUTIEN: " + query;
				System.out.println("[ErpThutien.createTTHD] error message:" + this.msg);
				db.getConnection().rollback();
				return false;
			}
			
			// -------------------------------End---------------------

			query = "delete ERP_THUTIEN_HOADON where THUTIEN_FK = '" + this.id + "'";
			System.out.println(query);
			db.update(query);

			query = "delete ERP_THUTIEN_HOADON_SP where THUTIEN_FK = '" + this.id + "'";
			System.out.println(query);
			db.update(query);
			
			query = "delete ERP_THUTIEN_DINHKHOANCO where THUTIEN_FK = '" + this.id + "'";
			System.out.println(query);
			db.update(query);
			
			if(this.ndId.equals("100003") || this.ndId.equals("100000")) // THU HỒI TẠM ỨNG
			{
				String tthdCurrent = "";
				query = "select IDENT_CURRENT('ERP_THUTIEN') as tthdId";

				ResultSet rsTthd = db.get(query);
				if (rsTthd.next()) 
				{
					tthdCurrent = rsTthd.getString("tthdId");
					rsTthd.close();
				}
				
				for (int i = 0; i < this.hoadonList.size(); i++) {
					IHoadon hoadon = this.hoadonList.get(i);

					String thanhtoan = hoadon.getThanhtoan().replaceAll(",", "");
					String avat = hoadon.getTongtiencoVAT().replaceAll(",","");
					String sotienNT = hoadon.getSotienNT().replaceAll(",","");
					String conlai = hoadon.getConlai().replaceAll(",", "");

					if (thanhtoan.length() > 0 ) {
						if (Float.parseFloat(thanhtoan) != 0) {

							if (this.ndId.equals("100003"))
								hoadon.setLoaihd("");
							
							if (hoadon.getLoaihd().equals("7"))
							{
								thanhtoan = Float.parseFloat(thanhtoan)*(-1)+"";
								avat = Float.parseFloat(avat)*(-1)+"";
								conlai = Float.parseFloat(conlai)*(-1)+"";
							}

							query = "Insert ERP_THUTIEN_HOADON(THUTIEN_FK, HOADON_FK, SOTIENTT, SOTIENAVAT, CONLAI,LOAIHOADON) "+
									"values('"+ this.id+ "', '"+ hoadon.getId()+ "', '"+ thanhtoan.trim()	+ "', '"+ avat+ "', '"+ conlai.trim()+ "', '"+ hoadon.getLoaihd() + "')";

							if (!db.update(query)) {
								this.msg = "Khong the tao moi ERP_THUTIEN_HOADON: "	+ query;
								System.out.println("[ErpThutien.createTTHD] error message: "+ this.msg);
								db.getConnection().rollback();
								return false;
							}
							
						}
					}
				}
			}
			else if (this.ndId.equals("100002")) // THU KHÁC
			{
				String tthdCurrent = "";
				query = "select IDENT_CURRENT('ERP_THUTIEN') as tthdId";

				ResultSet rsTthd = db.get(query);
				if (rsTthd.next()) {
					tthdCurrent = rsTthd.getString("tthdId");
					rsTthd.close();
				}

				for (int j = 0; j < this.dinhkhoancoList.size(); j++) {
					IDinhkhoanco dkc = this.dinhkhoancoList.get(j);

					if (Double.parseDouble(dkc.getSotien().replaceAll(",", "")) > 0) {
						String doituongdk = dkc.getDoituongdinhkhoan();
						String doituong_fk = dkc.getDoituongId();

						double sotienco = Double.parseDouble(dkc.getSotien().replaceAll(",", ""));
						double sotiencoNT = 0;

						if (!this.tienteId.equals("100000")) {
							sotiencoNT = Double.parseDouble(dkc.getSotien().replaceAll(",", ""));
							sotienco = Math.round(sotiencoNT * Double.parseDouble(this.tigia.replaceAll(",", "")));
						}

						if (doituong_fk.trim().length() <= 0) {
							doituongdk = "0";
							doituong_fk = "NULL";
						}
						
						String is_NPP = "0";
						if(doituongdk.equals("5")) 
						{
							//CHỖ NÀY ĐỂ PHÂN BIỆT LÀ NPP HAY KHÁCH HÀNG HAY NHÂN VIÊN
							
							if( doituong_fk.contains("-") )
							{
								String kh[] = doituong_fk.split("-");
								doituong_fk = kh[0];
								is_NPP = kh[1];
							}
						}
						

						query = "Insert into ERP_THUTIEN_DINHKHOANCO(THUTIEN_FK, TAIKHOAN_FK, DOITUONGDINHKHOAN, DOITUONG_FK, SOTIENNT, SOTIEN, isNPP) "
								+ "values('" + this.id + "', '" + dkc.getTaikhoanId() + "', '" + doituongdk + "', "
								+ doituong_fk + ", '" + sotiencoNT + "' , '" + sotienco + "', "+is_NPP+")";

						if (!db.update(query)) {
							this.msg = "Khong the tao moi ERP_THUTIEN_HOADON: " + query;
							db.getConnection().rollback();
							return false;
						}
					}
				}
			}
			
			query = "delete ERP_THUTIEN_HOADON_CHITIET where THUTIEN_FK = '" + this.id + "' ";
			if (!db.update(query)) {
				this.msg = "Khong the tao moi ERP_THUTIEN_HOADON_CHITIET: " + query;
				db.getConnection().rollback();
				return false;
			}
			
			if( this.hd_chungloai != null )
			{
				Enumeration<String> keys = this.hd_chungloai.keys();
				while( keys.hasMoreElements() )
				{
					String key = keys.nextElement();
					String[] arr = key.split("__");
					
					query = "insert ERP_THUTIEN_HOADON_CHITIET( THUTIEN_FK, HOADON_FK, CHUNGLOAI_FK, sotienHD, SOTIENTT ) " + 
							"select '" + this.id + "', '" + arr[0] + "', pk_seq, '" + arr[2].replaceAll(",", "") + "', '" + this.hd_chungloai.get(key) + "' " + 
							"from CHUNGLOAI where ten = N'" + arr[1] + "'  ";
					System.out.println("::: THU TIEN CHI TIET: " + query);
					if(!db.update(query))
					{
						this.msg = "KHONG THE CAP NHAT ERP_THUTIENNPP_HOADON_CHITIET: " + query;
						db.getConnection().rollback();
						return false;
					}
				}
			}
			
			db.getConnection().commit();
			db.getConnection().setAutoCommit(true);
		} catch (SQLException e) {
			try {
				System.out.println(e.toString());
				db.getConnection().rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
			return false;
		}

		return true;
	}

	public boolean chotTTHD(String userId) {
		
		try {
			String ngaysua = getDateTime();
			this.ngaychungtu = Utility.getNgayHienTai();
			this.ngayghiso = Utility.getNgayHienTai();
			
			db.getConnection().setAutoCommit(false);

			String query = " update ERP_THUTIEN set ngaychungtu='"+this.ngaychungtu+"' ,ngayghiso='"+this.ngayghiso+"' , TRANGTHAI = '1', NGUOISUA = '"+ userId+ "', NGAYSUA = '"+ ngaysua+ "' where PK_SEQ = '" + this.id + "'";
			System.out.println("1.Cap nhat ERP_THUTIEN: " + query);

			if (!db.update(query)) {
				this.msg = "Khong the chot ERP_THUTIEN: " + query;
				System.out.println(this.msg);
				db.getConnection().rollback();
				return false;
			}

			// KIỂM TRA PHIẾU THU NẾU CÓ PHIẾU XÓA NỢ KH THÌ CHỐT PHIẾU XÓA NỢ
			query = "update ERP_XOAKHTRATRUOC set TRANGTHAI = '1', NGUOISUA = '"+ userId+ "', NGAYSUA = '"+ ngaysua+ "' where PK_SEQ IN (select XOAKHTRATRUOC_FK from ERP_THUTIEN where PK_SEQ = "+ this.id + " )";
			System.out.println("1.Cap nhat ERP_THUTIEN: " + query);

			if (!db.update(query)) {
				this.msg = "Khong the chot ERP_XOANOKH: " + query;
				db.getConnection().rollback();
				return false;
			}			
		

			db.getConnection().commit();
			db.getConnection().setAutoCommit(true);
		} catch (java.sql.SQLException e) {
			try {
				db.getConnection().rollback();
				e.printStackTrace();
			} catch (SQLException e1) {
			}
			return false;
		}

		return true;
	}

	public void init() 
	{
		NumberFormat formatter = new DecimalFormat("#,###,###");

		//this.getNppInfo();

		String query = 	" select tt.bpkinhdoanh,tt.pk_seq, tt.ngaychungtu, tt.ngayghiso, tt.trangthai, tt.khachhang_fk as khachhang_fk, \n"+
				 		" tt.httt_fk, tt.nganhang_fk, tt.chinhanh_fk, tk.SOHIEUTAIKHOAN +' -- '+ tk.TENTAIKHOAN as dinhkhoanco, tt.doituongdinhkhoan, tt.madoituongdinhkhoan, \n"+
				 		" tt.sotaikhoan, tt.noidungtt_fk, isnull(tt.ghichu, '') as ghichu, ISNULL(tt.sotientt, 0) AS SOTIENTT, tt.tiente_fk, \n"+
				 		" isnull(tt.sochungtu,'') as sochungtu, tt.NCC_FK, tt.NHANVIEN_FK , \n"+
				 		" isnull(tt.thuduoc, 0) as thuduoc, isnull(tt.phinganhang, 0) as phinganhang, isnull(tt.chenhlech, 0) as chenhlech, \n"+
				 		" ISNULL(tt.THUDUOCNT, 0) AS THUDUOCNT, ISNULL(tt.PHINGANHANGNT, 0) AS PHINGANHANGNT, \n"+
				 		" ISNULL(tt.SOTIENTTNT, 0) AS SOTIENTTNT, ISNULL(tt.TIGIA, 0) AS TIGIA, tt.KBH_FK, \n"+
				 		" isnull(tt.chietkhau,0) as chietkhau,isnull(tt.chietkhauNT,0) as chietkhauNT, ISNULL(NGUOINOPTIEN,'') as NGUOINOPTIEN, ISNULL(DIACHI,'') as DIACHI , \n"+
				 		" xoakhtratruoc_fk , NHOMKHTT_FK, isnull(tt.ctkemtheo,'') chungtukemtheo, doituong, nhomkenh_fk, tt.BANGKE_FK, isnull(tt.isNPP,0) isNPP, isnull(tt.NPPCN_FK, 0) NPPCN_FK, isnull(tt.ISThuhoCN, 0) ISThuhoCN  \n"+
				 		" from ERP_THUTIEN tt \n" + 
				 		" left join ERP_TAIKHOANKT tk on tk.SOHIEUTAIKHOAN = tt.dinhkhoanco \n"+
				 		" where tt.pk_seq = " + this.id;

		System.out.println("115.Khoi tao thu tien: " + query);
		ResultSet rs = db.get(query);
		if (rs != null) {
			try {
				while (rs.next()) {
					this.nhomkhttId = rs.getString("NHOMKHTT_FK") == null ? "" : rs.getString("NHOMKHTT_FK");
					this.ngaychungtu = rs.getString("ngaychungtu");
					this.ngayghiso = rs.getString("ngayghiso");
					this.nppId = rs.getString("khachhang_fk") == null ? "" : rs.getString("khachhang_fk");
					this.bpkinhdoanh = rs.getString("bpkinhdoanh") == null ? "" : rs.getString("bpkinhdoanh");
					this.isNPP = rs.getString("isNPP");
					this.isthuhoCN = rs.getString("isthuhoCN");
					
					if(this.nppId.trim().length()>0)
						this.nppIdgoc = this.nppId  + " -- "+this.isNPP;
					
					this.nppChinhanhId = rs.getString("NPPCN_FK") == null ? "" : rs.getString("NPPCN_FK");
					
					if(this.nppChinhanhId.trim().length()>0)
						this.isChuyenCN = "1";

					this.htttId = rs.getString("httt_fk");
					this.ndId = rs.getString("noidungtt_fk");

					this.nguoinoptien = rs.getString("nguoinoptien");
					this.diachi = rs.getString("diachi");

					this.kbhId = rs.getString("KBH_FK") == null ? "" : rs.getString("KBH_FK");					

					if (this.nhomkhttId.trim().length() > 0)
						this.nhomkhtt = "1";

					if (this.htttId.equals("100001")) {
						this.nganhangId = rs.getString("nganhang_fk");
						this.chinhanhId = rs.getString("chinhanh_fk");
						this.sotaikhoan = rs.getString("sotaikhoan");
					}
					this.noidungtt = rs.getString("ghichu");
					this.sotientt = formatter.format(rs.getDouble("sotientt"));
					this.sotienttNT = "" + rs.getDouble("sotienttNT");
					this.tienteId = rs.getString("tiente_fk");
					this.sochungtu = rs.getString("sochungtu");
					this.thuduoc = "" + rs.getString("thuduoc");
					this.thuduocNT = "" + rs.getString("thuduocNT");
					this.cpnganhang = "" + rs.getString("phinganhang");
					this.cpnganhangNT = "" + rs.getString("phinganhangNT");
					this.chenhlech = "" + rs.getString("chenhlech");
					this.chietkhau = "" + rs.getString("chietkhau");
					this.chietkhauNT = "" + rs.getString("chietkhauNT");
					this.chungtukemtheo = "" + rs.getString("chungtukemtheo");
					this.doituongId = rs.getString("doituong");
					this.nhomkenhId = rs.getString("nhomkenh_fk");
					

					this.xoakhtratruocId = rs.getString("xoakhtratruoc_fk") == null ? "": rs.getString("xoakhtratruoc_fk");
					this.bangkeId = rs.getString("BANGKE_FK") == null ? "" : rs.getString("BANGKE_FK");

					this.nccId = rs.getString("NCC_FK") == null ? "" : rs.getString("NCC_FK");
					this.nvtuId = rs.getString("NHANVIEN_FK") == null ? "" : rs.getString("NHANVIEN_FK");

					this.tigia = "" + rs.getString("TIGIA");
					if (this.ndId.equals("100001") || this.ndId.equals("100002")) {
						this.tongVND = "" + (Double.parseDouble(this.thuduoc) + Double.parseDouble(this.cpnganhang));
						this.tongNT = "" + (Double.parseDouble(this.thuduocNT) + Double.parseDouble(this.cpnganhangNT));
					}
					
					if (this.ndId.equals("100003")) {
						if (this.nccId.length() > 0)
							this.DoiTuongTamUng = "1";
						if (this.nvtuId.length() > 0)
							this.DoiTuongTamUng = "0";
					}

				}
				rs.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		//INIT CHUNG LOAI
		query = "select a.hoadon_fk, b.ten, cast(a.sotienHD as numeric(18, 0)) as sotienHD, cast(SOTIENTT as numeric(18, 0)) as SOTIENTT " +
				"from ERP_THUTIEN_HOADON_CHITIET a inner join CHUNGLOAI b on a.CHUNGLOAI_FK = b.pk_seq " +
				"where a.thutien_fk = '" + this.id + "' ";
		System.out.println("::: INIT CHI TIET: " + query);
		rs = db.get(query);
		if(rs != null)
		{
			Hashtable<String, String> hd_chungloai = new Hashtable<String, String>();
			try
			{
				while(rs.next())
				{
					System.out.println("---KEY BEAN: " + ( rs.getString("hoadon_fk") + "__" + rs.getString("ten") + "__" + rs.getString("sotienHD") ) + "  VALUE: " + rs.getString("SOTIENTT") );
					hd_chungloai.put( rs.getString("hoadon_fk") + "__" + rs.getString("ten") + "__" + rs.getString("sotienHD"), rs.getString("SOTIENTT") );
				}
				rs.close();
			}
			catch(Exception ex){ ex.printStackTrace(); }
			this.hd_chungloai = hd_chungloai;
		}
		
		this.createRs();

		if (this.ndId.equals("100002")) // Thu khác
		{
			createDoiTuongDinhKhoanCo();
		}

	}

	private void createDoiTuongDinhKhoanCo() {

		if (this.id.trim().length() > 0 && this.ndId.equals("100002")) {
			String command = "SELECT distinct ttct.TAIKHOAN_FK, ttct.DOITUONGDINHKHOAN, ttct.DOITUONG_FK, ttct.SOTIEN, isnull( ttct.ISNPP,0 ) isNPP  \n"+
							 "FROM ERP_THUTIEN_DINHKHOANCO ttct " + 
							 "WHERE ttct.THUTIEN_FK = " + this.id + " ";

			System.out.println("LAY dkc " + command);
			ResultSet rsDKC = db.get(command);
			List<IDinhkhoanco> dkcList = new ArrayList<IDinhkhoanco>();

			NumberFormat formatter = new DecimalFormat();
			try {
				IDinhkhoanco dkc = null;
				if (rsDKC != null) {
					while (rsDKC.next()) {
						String taikhoan_fk = rsDKC.getString("TAIKHOAN_FK");
						String doituongdinhkhoan = rsDKC.getString("DOITUONGDINHKHOAN");
						String doituong_fk = rsDKC.getString("DOITUONG_FK");
						String is_NPP = rsDKC.getString("isNPP");

						String sotienNT = "";

						String sotien = formatter.format(rsDKC.getDouble("SOTIEN"));
						
						dkc = new Dinhkhoanco(taikhoan_fk, doituongdinhkhoan, doituong_fk, sotienNT, sotien);

						dkcList.add(dkc);

					}
					rsDKC.close();
				}
			} catch (Exception e) {
				e.printStackTrace();
			}

			this.dinhkhoancoList = dkcList;

		}

	}

	public void createDoiTuongDinhKhoan() {
		String query = "";
		if (this.DoiTuongDinhKhoan.equals("1")) {
			query = "select cast(PK_SEQ as varchar) + ' -- ' + MA+ ',' + TEN  as maten from SANPHAM where PK_SEQ = "
					+ this.DoituongdinhkhoanId;
		} else if (this.DoiTuongDinhKhoan.equals("2")) {
			query = "select cast(PK_SEQ as varchar) + ' -- ' + MA+ ',' + TEN  as maten from ERP_NGANHANG where PK_SEQ = "
					+ this.DoituongdinhkhoanId;
		} else if (this.DoiTuongDinhKhoan.equals("3")) {
			query = "select cast(PK_SEQ as varchar) + ' -- ' + MA+ ',' + TEN  as maten from ERP_NHACUNGCAP where PK_SEQ = "
					+ this.DoituongdinhkhoanId;
		} else if (this.DoiTuongDinhKhoan.equals("4")) {
			query = "select cast(PK_SEQ as varchar) + ' -- ' + MA+ ',' + TEN  as maten from ERP_TAISANCODINH where PK_SEQ = "
					+ this.DoituongdinhkhoanId;
		} else if (this.DoiTuongDinhKhoan.equals("5")) {
			query = "select cast(PK_SEQ as varchar) + ' -- ' + MA+ ',' + TEN  as maten from NHAPHANPHOI where PK_SEQ = "
					+ this.DoituongdinhkhoanId;
		} else if (this.DoiTuongDinhKhoan.equals("6")) {
			query = "select cast(PK_SEQ as varchar) + ' -- ' + MA+ ',' + TEN  as maten from ERP_NHANVIEN where PK_SEQ = "
					+ this.DoituongdinhkhoanId;
		}

		System.out.println("GET doi tuong : " + query);
		ResultSet dtrs = db.get(query);
		if (dtrs != null)
			try {
				while (dtrs.next()) {
					this.MaTenDoiTuongDinhKhoan = dtrs.getString("maten");
				}
				dtrs.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
	}

	public void initDisplay() {
		NumberFormat formatter = new DecimalFormat("#,###,###");

		String query =" SELECT tt.bpkinhdoanh,tt.pk_seq, tt.ngaychungtu, tt.ngayghiso, tt.trangthai, tt.khachhang_fk as khachhang_fk, \n"+
					  " tt.httt_fk, tt.nganhang_fk, tt.chinhanh_fk, tk.SOHIEUTAIKHOAN +' -- '+ tk.TENTAIKHOAN as dinhkhoanco, tt.doituongdinhkhoan, tt.madoituongdinhkhoan, \n"+
					  " tt.sotaikhoan, tt.noidungtt_fk, isnull(tt.ghichu, '') as ghichu, ISNULL(tt.sotientt, 0) AS SOTIENTT, tt.tiente_fk, \n"+
					  " isnull(tt.sochungtu,'') as sochungtu, tt.NCC_FK, tt.NHANVIEN_FK , \n"+
					  " isnull(tt.thuduoc, 0) as thuduoc, isnull(tt.phinganhang, 0) as phinganhang, isnull(tt.chenhlech, 0) as chenhlech, \n"+
					  " ISNULL(tt.THUDUOCNT, 0) AS THUDUOCNT, ISNULL(tt.PHINGANHANGNT, 0) AS PHINGANHANGNT, \n"+
					  " ISNULL(tt.SOTIENTTNT, 0) AS SOTIENTTNT, ISNULL(tt.TIGIA, 0) AS TIGIA, tt.KBH_FK, \n"+
					  " isnull(tt.chietkhau,0) as chietkhau,isnull(tt.chietkhauNT,0) as chietkhauNT, ISNULL(NGUOINOPTIEN,'') as NGUOINOPTIEN, ISNULL(DIACHI,'') as DIACHI , \n"+
					  " xoakhtratruoc_fk , NHOMKHTT_FK, isnull(tt.ctkemtheo,'') chungtukemtheo, doituong, nhomkenh_fk, tt.BANGKE_FK, isnull(tt.isNPP,0) isNPP, isnull(tt.NPPCN_FK, 0) NPPCN_FK, isnull(tt.ISThuhoCN, 0) ISThuhoCN \n"+
					  " FROM ERP_THUTIEN tt \n" + 
					  " left join ERP_TAIKHOANKT tk on tk.SOHIEUTAIKHOAN = tt.dinhkhoanco \n"+
					  " WHERE tt.pk_seq = " + this.id + " and tt.congty_fk = " + this.ctyId;

		System.out.println("115.Khoi tao thu tien: " + query);
		ResultSet rs = db.get(query);
		if (rs != null) {
			try {
				while (rs.next()) {
					this.nhomkhttId = rs.getString("NHOMKHTT_FK") == null ? "" : rs.getString("NHOMKHTT_FK");
					this.bpkinhdoanh= rs.getString("bpkinhdoanh") == null ? "" : rs.getString("bpkinhdoanh");
					this.ngaychungtu = rs.getString("ngaychungtu");
					this.ngayghiso = rs.getString("ngayghiso");
					this.nppId = rs.getString("khachhang_fk") == null ? "" : rs.getString("khachhang_fk");
					this.isNPP = rs.getString("isNPP");
					this.isthuhoCN = rs.getString("ISThuhoCN");

					this.htttId = rs.getString("httt_fk");
					this.ndId = rs.getString("noidungtt_fk");

					this.nguoinoptien = rs.getString("nguoinoptien");
					this.diachi = rs.getString("diachi");

					if(this.nppId.trim().length()>0)
						this.nppIdgoc = this.nppId  + " -- "+this.isNPP;
										
					this.nppChinhanhId = rs.getString("NPPCN_FK") == null ? "" : rs.getString("NPPCN_FK");
					
					if(this.nppChinhanhId.trim().length()>0)
						this.isChuyenCN = "1";
					
					this.kbhId = rs.getString("KBH_FK") == null ? "" : rs.getString("KBH_FK");

					if (this.nhomkhttId.trim().length() > 0)
						this.nhomkhtt = "1";

					if (this.htttId.equals("100001")) {
						this.nganhangId = rs.getString("nganhang_fk");
						this.chinhanhId = rs.getString("chinhanh_fk");
						this.sotaikhoan = rs.getString("sotaikhoan");
					}
					this.noidungtt = rs.getString("ghichu");
					this.sotientt = formatter.format(rs.getDouble("sotientt"));
					this.sotienttNT = "" + rs.getDouble("sotienttNT");
					this.tienteId = rs.getString("tiente_fk");
					this.sochungtu = rs.getString("sochungtu");
					this.thuduoc = "" + rs.getString("thuduoc");
					this.thuduocNT = "" + rs.getString("thuduocNT");
					this.cpnganhang = "" + rs.getString("phinganhang");
					this.cpnganhangNT = "" + rs.getString("phinganhangNT");
					this.chenhlech = "" + rs.getString("chenhlech");
					this.chietkhau = "" + rs.getString("chietkhau");
					this.chietkhauNT = "" + rs.getString("chietkhauNT");
					this.chungtukemtheo = "" + rs.getString("chungtukemtheo");
					this.doituongId = rs.getString("doituong");
					this.nhomkenhId = rs.getString("nhomkenh_fk");

					this.xoakhtratruocId = rs.getString("xoakhtratruoc_fk") == null ? "": rs.getString("xoakhtratruoc_fk");
					this.bangkeId = rs.getString("BANGKE_FK") == null ? "" : rs.getString("BANGKE_FK");

					this.nccId = rs.getString("NCC_FK") == null ? "" : rs.getString("NCC_FK");
					this.nvtuId = rs.getString("NHANVIEN_FK") == null ? "" : rs.getString("NHANVIEN_FK");

					this.tigia = "" + rs.getString("TIGIA");
					if (this.ndId.equals("100001") || this.ndId.equals("100002")) {
						this.tongVND = "" + (Double.parseDouble(this.thuduoc) + Double.parseDouble(this.cpnganhang));
						this.tongNT = "" + (Double.parseDouble(this.thuduocNT) + Double.parseDouble(this.cpnganhangNT));
					}
					if (this.ndId.equals("100003")) {
						if (this.nccId.length() > 0)
							this.DoiTuongTamUng = "1";
						if (this.nvtuId.length() > 0)
							this.DoiTuongTamUng = "0";
					}

				}
				rs.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		this.createRs_display();

		if (this.ndId.equals("100002")) // Thu khác
		{
			createDoiTuongDinhKhoanCo();
		}

	}

	public void initPdf() {
		NumberFormat formatter = new DecimalFormat("#,###,###");
		String query = "select a.pk_seq as tthdId, a.thuduoc, a.trangthai, a.ngaychungtu, a.ngayghiso, "
				// + " (case when isnull(a.nguoinoptien,'NA') = '' then 'NA'
				// else isnull(a.nguoinoptien,'NA') end) as nguoinoptien, "
				+ "       isnull(a.nguoinoptien,'')  as nguoinoptien, "
				+ "       (case when isnull(a.diachi,'') = '' then '' else isnull(a.diachi,'') end) as diachi_ , "
				+ "	isnull(TenXuatHD,'') as nppTen, isnull(b.diachi, '') as diachi, c.ten as htttTen,f.pk_seq as noidungId, f.ten as noidungTen, a.ghichu as ghichu, isnull(thuduoc, 0) as thuduoc, isnull(phinganhang, 0) as phinganhang, isnull(chenhlech, 0) as chenhlech, "
				+ " isnull(a.ctkemtheo,'') chungtukemtheo \n "
				+ "from ERP_THUTIEN a left join KHACHHANG b on a.khachhang_fk = b.pk_seq "
				+ "inner join ERP_HINHTHUCTHANHTOAN c on a.HTTT_FK = c.pk_seq "
				+ "inner join ERP_NOIDUNGTHUTIEN f on a.noidungtt_fk = f.pk_seq where a.pk_seq = '" + this.id + "' ";

		System.out.println("[ErpThutien.initPdf] query = " + query);
		ResultSet rs = db.get(query);
		if (rs != null) {
			try {
				while (rs.next()) {
					this.ngaychungtu = rs.getString("ngaychungtu");
					this.ngayghiso = rs.getString("ngayghiso");
					this.nguoinoptien = rs.getString("nguoinoptien") + " --- " + rs.getString("diachi_");
					this.nppId = rs.getString("nppTen") + " --- " + rs.getString("diachi");
					this.htttId = rs.getString("htttTen");
					this.ndId = rs.getString("noidungId") + " --- " + rs.getString("noidungTen");
					this.noidungtt = rs.getString("ghichu");
					this.lydonop = rs.getString("ghichu");
					this.thuduoc = formatter.format(rs.getDouble("thuduoc"));
					this.cpnganhang = "" + rs.getString("phinganhang");
					this.chenhlech = "" + rs.getString("chenhlech");
					this.chungtukemtheo = "" + rs.getString("chungtukemtheo");

				}
			} catch (SQLException e) {
				System.out.println("115..Exception: " + e.getMessage());
			}
		}

		// khoi tao hoa don
		query = "select hoadon_fk from erp_thutien_hoadon a inner join ERP_HOADONNPP b on a.hoadon_fk = b.pk_seq where thutien_fk = '"
				+ this.id + "'";
		String sohoadon = "";
		ResultSet hoadonRs = db.get(query);
		if (hoadonRs != null) {
			try {
				while (hoadonRs.next()) {
					sohoadon += hoadonRs.getString("hoadon_fk") + ", ";
				}
				hoadonRs.close();
			} catch (SQLException e) {
			}
		}

		if (sohoadon.length() > 0)
			this.noidungtt = sohoadon.substring(0, sohoadon.length() - 2);

	}

	public void initUnc() {
		NumberFormat formatter = new DecimalFormat("#,###,###");
		String query = "select a.pk_seq, a.ngaychungtu, b.ten as nppTen, b.diachi, a.httt_fk, c.ten as nganhang_fk, a.chinhanh_fk, a.sotaikhoan, a.noidungtt, a.sotientt "
				+ "from ERP_THANHTOANHOADON a inner join ERP_NHACUNGCAP b on a.npp_fk = b.pk_seq left join erp_nganhang c on a.nganhang_fk = c.pk_seq"
				+ " where a.pk_seq = '" + this.id + "'";
		System.out.println("Khoi tao Unc: " + query);
		ResultSet rs = db.get(query);
		if (rs != null) {
			try {
				while (rs.next()) {
					this.ngaychungtu = rs.getString("ngaychungtu");
					this.nppId = rs.getString("nppTen") + " --- " + rs.getString("diachi");
					this.htttId = rs.getString("httt_fk");
					if (this.htttId.equals("100001")) {
						this.nganhangId = rs.getString("nganhang_fk");
						this.chinhanhId = rs.getString("chinhanh_fk");
						this.sotaikhoan = rs.getString("sotaikhoan");
					}
					this.noidungtt = rs.getString("noidungtt");
					this.sotientt = formatter.format(rs.getDouble("sotientt"));

				}
			} catch (SQLException e) {
				System.out.println("Exception: " + e.getMessage());
			}
		}

	}

	public void createRs() {

		if (this.ngaychungtu.length() > 0&this.ngayghiso.length() <=0)
			this.ngayghiso = this.ngaychungtu;

		String command = "";
		
		if (this.nppId.trim().length() > 0) {
			command = "select PK_SEQ, TEN + ', ' + DIENGIAI as TEN  \n"+
					  "from KENHBANHANG \n"+
					  "where TRANGTHAI = 1  ";
			this.kbhRs = db.get(command);
		}
		
		
		
		this.nppRs = db.getScrol(" select CONVERT(VARCHAR, PK_SEQ) + ' -- ' + '1' AS PK_SEQ , MA + ' - ' + TEN  as nppTen, DIACHI AS DIACHI, TEN AS TEN   from NHAPHANPHOI where trangthai = '1' and PK_SEQ!=1 "); // LẤY NHÀ PHÂN PHỐI KHÁC != 1 VÌ 1 CHÍNH LÀ HO
		
		this.nhomkhttRs = db.get("Select pk_seq, diengiai,cast(pk_seq as varchar(10)) +' , ' +diengiai as ten  from Nhomkhachhangtt where trangthai='1'");		
		this.htttRs = db.get("select pk_seq, pk_seq as  ma, ten from HINHTHUCTHANHTOAN  ");
		this.ndRs = db.get("select pk_seq, ma, ten from ERP_NOIDUNGTHUTIEN where trangthai = '1' ");
				
		String query = "SELECT (CASE WHEN NH.MA = 'EIB' THEN 1 ELSE 2 END) AS STT ,  \n"+
					   "NH_CTY.SOTAIKHOAN, NH_CTY.SOTAIKHOAN + ' - ' + NH.TEN + ' - ' + CN.TEN + ', ' + TT.MA  AS TAIKHOAN \n"+
					   "FROM ERP_NGANHANG_CONGTY NH_CTY \n"+
					   "INNER JOIN ERP_TIENTE TT ON TT.PK_SEQ = NH_CTY.TIENTE_FK \n"+
					   "INNER JOIN ERP_NGANHANG NH ON NH.PK_SEQ = NH_CTY.NGANHANG_FK \n"+
					   "INNER JOIN ERP_CHINHANH CN ON CN.PK_SEQ = NH_CTY.CHINHANH_FK \n"+
					   "WHERE NH_CTY.TRANGTHAI = 1 AND NH_CTY.CONGTY_FK = '"+ this.ctyId + "' ";
		if (this.tienteId.length() > 0) {
			query = query + " AND TT.PK_SEQ = " + this.tienteId + " ";
		}

		query += " ORDER BY STT ";

		this.sotkRs = this.db.get(query);

		this.tienteRs = db.get("select pk_seq, ma + ', ' + ten as TEN from ERP_TIENTE ");

		String sql = "select pk_seq, ma + ', ' + ten as nppTen from ERP_KhachHang where trangthai = '1' ";

		if (this.ndId.equals("100002")) // PHẢI THU KHÁC --> LOAD NCC
		{
			sql = "select pk_seq, ma + ', ' + ten as nppTen from ERP_NHACUNGCAP where trangthai = '1' ";
			this.nppRs = db.getScrol(sql);
		}

		if (this.htttId.equals("100001")) { // THU TIỀN KHÁCH HÀNG TRẢ TRƯỚC
			if (this.nganhangId.length() > 0) {
				this.chinhanhRs = db.get("select pk_seq, ma + ', ' + ten as cnTen from erp_chinhanh where PK_SEQ in ( select ChiNhanh_FK from ERP_NGANHANG_CONGTY where congty_fk = '"+ this.ctyId + "' and nganhang_fk = '" + this.nganhangId + "' )");
			}

			if (this.chinhanhId.length() > 0) {
				ResultSet rs = db.get("select sotaikhoan from ERP_NGANHANG_CONGTY where congty_fk = '" + this.ctyId	+ "' and chinhanh_fk = '" + this.chinhanhId + "' and nganhang_fk = '" + this.nganhangId + "' ");
				if (rs != null) {
					try {
						if (rs.next()) {
							this.sotaikhoan = rs.getString("sotaikhoan");
						}
						rs.close();
					} catch (SQLException e) {
					}
				}
			}

		}

		query = "";

		if (this.xoakhtratruocId.trim().length() == 0)
			this.xoakhtratruocId = "0";

		String layKH = "";
		if (this.nhomkhttId.trim().length() > 1) // NHÓM KH
		{
			layKH = " in ( select khachhang_fk  from NHOMKHACHHANGTT_KHACHHANGTT where nkhtt_fk ="+ this.nhomkhttId + ") ";
		} else if (this.nppId.trim().length() > 1) {
			layKH = " = '" + this.nppId + "' ";
		}

		if ( ( this.nhomkhttId.trim().length() > 1	|| this.nppId.trim().length() > 1 ) && this.htttId.length() > 0 && this.hoadonList.size() <= 0) 
		{
			if (this.ndId.equals("100000")) { // THU TIEN HOA DON
				if (this.id.length() > 0) {

					// Loai hoa don : 0 Hd tài chính, 1 Hóa đơn khác
					// Lấy thu tiền this.id
					// HOADONTAICHINH
				query += "(SELECT '0' AS LOAIHD, ISNULL(HOADON.TYGIA, 1) as TIGIA , HOADON.PK_SEQ, HOADON.MAHOPDONG AS MAHOPDONG, \n"+
						 "        HOADON.KYHIEU, HOADON.SOHOADON, HOADON.NGAYHOADON, \n"+
						 "        HOADON.TONGTIENAVAT AS SOTIENGOC, \n"+
						 "        CAST((ISNULL(HOADON.TYGIA, 1)*(ISNULL(HOADON.TONGTIENAVAT, 0) - ISNULL(DATHANHTOAN.DATHANHTOAN, '0'))) AS numeric(18,0) ) AS SOTIENVND, \n"+
						 "        (HOADON.TONGTIENAVAT - ISNULL(DATHANHTOAN.DATHANHTOAN, '0')) AS SOTIENNT, \n"+
						 "        isnull( TTHD.SOTIENTT,0) AS SOTIENTT, HOADON.TTID, \n"+
						 "        HOADON.NGAYDENHANTT, HOADON.chungloaiCN \n"+
						 "FROM ( \n"+
						 "		SELECT 	HD.PK_SEQ, '' AS MAHOPDONG, HD.KYHIEU, HD.SOHOADON, HD.NGAYXUATHD AS NGAYHOADON, \n"+
						 "				HD.TONGTIENAVAT, ISNULL(HD.TYGIA,1) AS TYGIA, ISNULL(HD.TIENTE_FK,100000) AS TTID, \n"+
						 "   			'' as NGAYDENHANTT, isnull( HD.chungloaiCN, '' ) as chungloaiCN \n"+
						 "		FROM 	DCL.dbo.ERP_HOADON HD 	\n"+
						 "        		INNER JOIN NHAPHANPHOI KH ON HD.NPP_FK = KH.PK_SEQ \n"+
						 "		WHERE 	HD.NPP_FK "+ layKH +
						 " 				AND HD.TRANGTHAI in (2,4) AND HD.LOAIHOADON = '0'  \n" +
						 "				AND HD.PK_SEQ IN (SELECT HOADON_FK FROM ERP_THUTIEN_HOADON WHERE THUTIEN_FK = '"+ this.id + "') \n";
				query += "		) HOADON \n"+
						 " INNER JOIN ERP_THUTIEN_HOADON TTHD ON HOADON.PK_SEQ = TTHD.HOADON_FK AND TTHD.THUTIEN_FK = "+ this.id + " AND TTHD.LOAIHOADON = 0 \n"+
						 " LEFT JOIN \n"+
						 " ( \n"+
						 "	SELECT HOADON_FK, SUM(ISNULL(DATHANHTOAN, 0)) AS DATHANHTOAN \n"+
						 "	FROM  \n"+
						 "	( 	\n"+
						 "		SELECT TTHD.HOADON_FK, SUM(TTHD.SOTIENTT) AS DATHANHTOAN \n"+
						 "		FROM ERP_XOAKHTRATRUOC_HOADON TTHD \n"+
						 "		INNER JOIN ERP_XOAKHTRATRUOC TT ON TTHD.XOAKHTRATRUOC_FK = TT.PK_SEQ \n"+
						 "		WHERE TT.TRANGTHAI NOT IN (2) AND ISNULL(TT.LOAIXOATRATRUOC,0) = '0'  AND TTHD.LOAIHD = '0'  \n"+
						 "		GROUP BY HOADON_FK \n"+
						
						 "		UNION ALL \n"+
						
						 "		SELECT TTHD.HOADON_FK , SUM(TTHD.SOTIENTT) AS DATHANHTOAN \n"+
						 "		FROM ERP_THUTIEN_HOADON TTHD \n"+
						 "		INNER JOIN ERP_THUTIEN TT ON TTHD.THUTIEN_FK = TT.PK_SEQ \n"+
						 "		WHERE TTHD.LOAIHOADON= '0' AND TT.TRANGTHAI NOT IN (2) AND TT.PK_SEQ  != '" + this.id + "' \n";

				query += " 		GROUP BY HOADON_FK \n"+

						"		UNION ALL  \n"+

						"		SELECT BT_KH.HOADON_FK, SUM(BT_KH.XOANO) AS SOTIENBUTRU  \n"+
						"		FROM   ERP_BUTRUKHACHHANG BT INNER JOIN ERP_BUTRUKHACHHANG_CHITIET BT_KH ON BT.PK_SEQ =BT_KH.BTKH_FK \n"+
						"		WHERE  BT.TRANGTHAI NOT IN (2) AND BT_KH.LOAIHD = 0  AND BT.KH_CHUYENNO "+ layKH + "  \n " +
						"		GROUP BY BT_KH.HOADON_FK \n"+

						"	) HOADONDATT  \n"+
						"	GROUP BY HOADON_FK "+
						")DATHANHTOAN ON HOADON.PK_SEQ = DATHANHTOAN.HOADON_FK \n"+
						" WHERE TTHD.THUTIEN_FK = "+ this.id + " AND TTHD.LOAIHOADON = 0 )\n";
					
					query += " UNION ALL \n";
				}

				// HOADONTAICHINH
				query += "(SELECT '0' AS LOAIHD, ISNULL(HOADON.TYGIA, 1) as TIGIA , HOADON.PK_SEQ, HOADON.MAHOPDONG AS MAHOPDONG, \n"+
						 "        HOADON.KYHIEU, HOADON.SOHOADON, HOADON.NGAYHOADON, \n"+
						 "        HOADON.TONGTIENAVAT AS SOTIENGOC, \n"+
						 "        CAST((ISNULL(HOADON.TYGIA, 1)*(ISNULL(HOADON.TONGTIENAVAT, 0) - ISNULL(DATHANHTOAN.DATHANHTOAN, '0'))) AS numeric(18,0) ) AS SOTIENVND, \n"+
						 "        (HOADON.TONGTIENAVAT - ISNULL(DATHANHTOAN.DATHANHTOAN, '0')) AS SOTIENNT, \n"+
						 "        0 AS DATHANHTOAN, HOADON.TTID, \n"+
						 "        HOADON.NGAYDENHANTT, HOADON.chungloaiCN \n"+
						 "FROM ( \n"+
						 "		SELECT 	HD.PK_SEQ, '' AS MAHOPDONG, HD.KYHIEU, HD.SOHOADON, HD.NGAYXUATHD AS NGAYHOADON, \n"+
						 "				HD.TONGTIENAVAT, ISNULL(HD.TYGIA,1) AS TYGIA, ISNULL(HD.TIENTE_FK,100000) AS TTID, \n"+
						 "   			'' as NGAYDENHANTT, isnull( HD.chungloaiCN, '' ) as chungloaiCN \n"+
						 "		FROM 	DCL.dbo.ERP_HOADON HD 	\n"+
						 "        		INNER JOIN NHAPHANPHOI KH ON HD.NPP_FK = KH.PK_SEQ \n"+
						 "		WHERE 	HD.NPP_FK "+ layKH +
						 " 				AND HD.TRANGTHAI in (2,4) AND HD.LOAIHOADON = '0'  \n";

				if (this.id.length() > 0) {
					query += "				AND HD.PK_SEQ NOT IN (SELECT HOADON_FK FROM ERP_THUTIEN_HOADON WHERE THUTIEN_FK = '"+ this.id + "') \n";

				}

				query += "		) HOADON \n"+
						 "LEFT JOIN ( \n"+
						 "	SELECT HOADON_FK, SUM(ISNULL(DATHANHTOAN, 0)) AS DATHANHTOAN \n"+
						 "	FROM  \n"+
						 "	( 	\n"+
						 "		SELECT TTHD.HOADON_FK, SUM(TTHD.SOTIENTT) AS DATHANHTOAN \n"+
						 "		FROM ERP_XOAKHTRATRUOC_HOADON TTHD \n"+
						 "		INNER JOIN ERP_XOAKHTRATRUOC TT ON TTHD.XOAKHTRATRUOC_FK = TT.PK_SEQ \n"+
						 "		WHERE TT.TRANGTHAI NOT IN (2) AND ISNULL(TT.LOAIXOATRATRUOC,0) = '0'  AND TTHD.LOAIHD = '0'  \n"+
						 "		GROUP BY HOADON_FK \n"+
						
						 "		UNION ALL \n"+
						
						 "		SELECT TTHD.HOADON_FK , SUM(TTHD.SOTIENTT) AS DATHANHTOAN \n"+
						 "		FROM ERP_THUTIEN_HOADON TTHD \n"+
						 "		INNER JOIN ERP_THUTIEN TT ON TTHD.THUTIEN_FK = TT.PK_SEQ \n"+
						 "		WHERE TTHD.LOAIHOADON= '0' AND TT.TRANGTHAI NOT IN (2) ";

				if (this.id.trim().length() > 0) {
					query += " 		AND TT.PK_SEQ  != '" + this.id + "' \n";
				}

				query += " 		GROUP BY HOADON_FK \n"+

						"		UNION ALL  \n"+

						"		SELECT BT_KH.HOADON_FK, SUM(BT_KH.XOANO) AS SOTIENBUTRU  \n"+
						"		FROM   ERP_BUTRUKHACHHANG BT INNER JOIN ERP_BUTRUKHACHHANG_CHITIET BT_KH ON BT.PK_SEQ =BT_KH.BTKH_FK \n"+
						"		WHERE  BT.TRANGTHAI NOT IN (2) AND BT_KH.LOAIHD = 0  AND BT.KH_CHUYENNO "+ layKH + "  \n " +
						"		GROUP BY BT_KH.HOADON_FK \n"+

						"	) HOADONDATT  \n"+
						"	GROUP BY HOADON_FK "+
						")DATHANHTOAN ON HOADON.PK_SEQ = DATHANHTOAN.HOADON_FK \n"+
						" WHERE HOADON.TONGTIENAVAT - ISNULL(DATHANHTOAN.DATHANHTOAN, '0') > 0 AND HOADON.TTID = "+ this.tienteId + " \n" + ")\n";

				query += " ORDER BY LOAIHD, NGAYHOADON ASC ";

			} 

			System.out.println("1.Query khoi tao hoa don  : " + query);
			if(query.trim().length() > 0)
			{
				ResultSet rsHoadon = db.get(query);
				int row;
				try {
					row = rsHoadon.getRow();
					System.out.println("Size resultset:"+ row);
				} catch (SQLException e1) {
					
					e1.printStackTrace();
				}
				
				List<IHoadon> hdList = new ArrayList<IHoadon>();
				if (rsHoadon != null) {
					try {
						IHoadon hd = null;
						while (rsHoadon.next()) {
	
							String id = rsHoadon.getString("PK_SEQ");
							String kyhieu = rsHoadon.getString("KYHIEU");
							String sohoadon = rsHoadon.getString("SOHOADON");
							String ngayhd = rsHoadon.getString("NGAYHOADON");
							String sotiengoc = rsHoadon.getString("SOTIENGOC");
							String avat = "" + rsHoadon.getDouble("SOTIENVND");
							String sotienNT = "" + rsHoadon.getDouble("SOTIENNT");
							String ttId = rsHoadon.getString("TTID");
							String tygia = rsHoadon.getString("TIGIA");
							String ngaydenhanTT = rsHoadon.getString("NGAYDENHANTT");
							String loaihd = "";
							String tenloaihd = "";
							String mahopdong = "";
	
							if (this.ndId.equals("100000")) {
								mahopdong = rsHoadon.getString("MAHOPDONG");
								loaihd = rsHoadon.getString("LOAIHD");
	
								if (loaihd.equals("0"))
									tenloaihd = "Hóa đơn tài chính";
								if (loaihd.equals("1"))
									tenloaihd = "Hóa đơn khác";
								if (loaihd.equals("2"))
									tenloaihd = "Giảm/Tăng giá hàng bán";
								if (loaihd.equals("3"))
									tenloaihd = "Khách hàng trả trước";
								if (loaihd.equals("4"))
									tenloaihd = "Bù trừ công nợ";
							}
							String dathanhtoan = "0";
							if (this.id.length() > 0) {
								if (Math.abs(rsHoadon.getDouble("SOTIENTT")) > 0) {
									dathanhtoan = ""
											+ rsHoadon.getDouble("SOTIENTT");
								}
							}
							hd = new Hoadon(id, mahopdong, kyhieu, sohoadon,
									ngayhd, sotiengoc, avat, sotienNT, dathanhtoan,
									ttId, "", tygia);
							hd.setLoaihd(loaihd);
							hd.setNgaydenhanTT(ngaydenhanTT);
							hd.setTenloaihd(tenloaihd);
							hd.setChungloai(rsHoadon.getString("chungloaiCN"));
							hdList.add(hd);
						}
						rsHoadon.close();
					} 
					catch (Exception e) 
					{
						e.printStackTrace();
					}
				}
				this.hoadonList = hdList;
				System.out.println("Size: " + this.hoadonList.size());
			}

		}

	}

	private String getDateTime() {
		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		Date date = new Date();
		return dateFormat.format(date);
	}

	public void DBclose() {
		this.db.shutDown();		
	}

	public String getNgayghiso() {
		return this.ngayghiso;
	}

	public void setNgayghiso(String ngayghiso) {
		this.ngayghiso = ngayghiso;
	}

	public String getNoidungId() {
		return this.ndId;
	}

	public void setNoidungId(String ndId) {
		this.ndId = ndId;
	}

	public ResultSet getNoidungRs() {
		return this.ndRs;
	}

	public void setNoidungRs(ResultSet ndRs) {
		this.ndRs = ndRs;
	}

	public String getSochungtu() {

		return this.sochungtu;
	}

	public void setSochungtu(String soct) {

		this.sochungtu = soct;
	}

	public String getTienteId() {

		return this.tienteId;
	}

	public void setTienteId(String ttId) {

		this.tienteId = ttId;
	}

	public ResultSet getTienteRs() {

		return this.tienteRs;
	}

	public void setTienteRs(ResultSet tienteRs) {

		this.tienteRs = tienteRs;
	}

	public String getTigia_hoadon() {

		return Tigia_hoadon;
	}

	public void setTigia_hoadon(String _Tigia_hoadon) {

		this.Tigia_hoadon = _Tigia_hoadon;
	}

	public String getDinhkhoanco() {
		return this.dinhkhoanco;
	}

	public void setDinhkhoanco(String dinhkhoanco) {
		this.dinhkhoanco = dinhkhoanco;

	}

	public String getDinhkhoancoId() {
		return this.dinhkhoancoId;
	}

	public void setDinhkhoancoId(String dinhkhoancoId) {
		this.dinhkhoancoId = dinhkhoancoId;

	}

	public String getDoiTuongDinhKhoan() {
		return this.DoiTuongDinhKhoan;
	}

	public void setDoiTuongDinhKhoan(String DoiTuongDinhKhoan) {
		this.DoiTuongDinhKhoan = DoiTuongDinhKhoan;

	}

	public String getDoituongdinhkhoanId() {
		return this.DoituongdinhkhoanId;
	}

	public void setDoituongdinhkhoanId(String DoituongdinhkhoanId) {
		this.DoituongdinhkhoanId = DoituongdinhkhoanId;

	}

	public String getMaTenDoiTuongDinhKhoan() {
		return this.MaTenDoiTuongDinhKhoan;
	}

	public void setMaTenDoiTuongDinhKhoan(String MaTenDoiTuongDinhKhoan) {
		this.MaTenDoiTuongDinhKhoan = MaTenDoiTuongDinhKhoan;

	}

	public String getNhomkhtt() {
		return this.nhomkhtt;
	}

	public void setNhomkhtt(String nhomkhtt) {
		this.nhomkhtt = nhomkhtt;
	}

	public String getNhomkhttId() {
		return this.nhomkhttId;
	}

	public void setNhomkhttId(String nhomkhttId) {
		this.nhomkhttId = nhomkhttId;
	}

	public ResultSet getNhomkhttRs() {
		return this.nhomkhttRs;
	}

	public void setNhomkhttRs(ResultSet nhomkhttRs) {
		this.nhomkhttRs = nhomkhttRs;
	}

	public String getNguoinoptien() {
		return this.nguoinoptien;
	}

	public void setNguoinoptien(String nguoinoptien) {
		this.nguoinoptien = nguoinoptien;
	}

	public String getDiachi() {
		return this.diachi;
	}

	public void setDiachi(String diachi) {
		this.diachi = diachi;
	}

	public String getChungTuKemTheo() {

		return this.chungtukemtheo;
	}

	public void setChungTuKemTheo(String chungtukemtheo) {

		this.chungtukemtheo = chungtukemtheo;
	}

	public String getLydonop() {

		return this.lydonop;
	}

	public void setLydonop(String lydonop) {

		this.lydonop = lydonop;
	}

	public List<IDinhkhoanco> getDinhkhoancoRs() {
		return this.dinhkhoancoList;
	}

	public void setDinhkhoancoRs(List<IDinhkhoanco> dinhkhoancoRs) {
		this.dinhkhoancoList = dinhkhoancoRs;
	}

	public ResultSet getTaikhoanRs() {
		return this.taikhoanRs;
	}

	public void setTaikhoanRs(ResultSet taikhoanRs) {
		this.taikhoanRs = taikhoanRs;
	}

	public ResultSet getSanphamList() {

		return this.SanphamList;
	}

	public void setSanphamRs(ResultSet sanphamList) {
		this.SanphamList = sanphamList;
	}

	public ResultSet getNganhangList() {
		return this.NganhangList;

	}

	public void setNganhangList(ResultSet nganhangList) {
		this.NganhangList = nganhangList;
	}

	public ResultSet getNccList() {

		return this.NccList;

	}

	public void setNccList(ResultSet nccList) {
		this.NccList = nccList;
	}

	public ResultSet getKhachhangList() {

		return this.KhachhangList;

	}

	public void setKhachhangList(ResultSet khachhangList) {
		this.KhachhangList = khachhangList;
	}

	public ResultSet getNhanvienList() {

		return this.NhanvienList;

	}

	public void setNhanvienList(ResultSet nhanvienList) {
		this.NhanvienList = nhanvienList;
	}

	public ResultSet getTaisanList() {

		return this.TaisanList;

	}

	public void setTaisanList(ResultSet taisanList) {
		this.TaisanList = taisanList;
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

	public String getnppdangnhap() {

		return this.nppdangnhap;
	}

	public void setnppdangnhap(String nppdangnhap) {

		this.nppdangnhap = nppdangnhap;
	}

	private void getNppInfo() {
		// Phien ban moi
		geso.dms.distributor.util.Utility util = new geso.dms.distributor.util.Utility();
		this.nppdangnhap = util.getIdNhapp(this.userId);
	}

	public String[] getMangkhId() {

		return this.MangkhId;
	}

	public void setMangKhId(String[] MangkhId) {

		this.MangkhId = MangkhId;
	}

	public String[] getMangkhMa() {

		return this.MangkhMa;
	}

	public void setMangkhMa(String[] MangkhMa) {

		this.MangkhMa = MangkhMa;
	}

	public String[] getMangkhTen() {

		return this.MangkhTen;
	}

	public void setMangkhTen(String[] MangkhTen) {

		this.MangkhTen = MangkhTen;
	}

	public String[] getMangTienNop() {

		return this.MangTienNop;
	}

	public void setMangTienNop(String[] MangTienNop) {

		this.MangTienNop = MangTienNop;
	}

	public String getNvgnId() {

		return this.nvgnId;
	}

	public void setNvgnId(String nvgnId) {

		this.nvgnId = nvgnId;
	}

	public ResultSet getNvgnRs() {

		return NvgnRs;
	}

	public void setNvgnRs(ResultSet NvgnRs) {

		this.NvgnRs = NvgnRs;
	}

	public String getDoituongId() {

		return this.doituongId;
	}

	public void setDoituongId(String doituongId) {

		this.doituongId = doituongId;
	}

	public ResultSet getDoituongRs() {

		return this.doituongRs;
	}

	public void setDoituongRs(ResultSet doituongRs) {

		this.doituongRs = doituongRs;
	}

	public String getNhomkenhId() {

		return this.nhomkenhId;
	}

	public void setNhomkenhId(String nhomkenhId) {

		this.nhomkenhId = nhomkenhId;
	}

	public ResultSet getNhomkenhRs() {

		return this.nhomkenhRs;
	}

	public void setNhomkenhRs(ResultSet nhomkenhRs) {

		this.nhomkenhRs = nhomkenhRs;
	}

	public String getBangkeId() {

		return this.bangkeId;
	}

	public void setBangkeId(String bangkeId) {

		this.bangkeId = bangkeId;
	}

	public ResultSet getBangkeRs() {

		return this.BangKeRs;
	}

	public void setBangkeRs(ResultSet bangkeRs) {

		this.BangKeRs = bangkeRs;
	}

	public String getisNPP() {

		return this.isNPP;
	}

	public void setisNPP(String isNPP) {

		this.isNPP = isNPP;
	}

	public String getnppIdgoc() {

		return this.nppIdgoc;
	}

	public void setnppIdgoc(String nppIdgoc) {

		this.nppIdgoc = nppIdgoc;
	}

	
	public boolean Delete(String userId) {
		try {
			String ngaysua = getDateTime();

			db.getConnection().setAutoCommit(false);

			
			String	query  = "delete PhieuThu_SoChungTu   where tableName = 'ERP_THUTIEN' and Id_FK = '" + this.id + "'";
			if(db.updateReturnInt(query)< 0)		
			{
				db.getConnection().rollback();
				db.getConnection().setAutoCommit(true);
				this.msg = "Lỗi xóa chứng từ:"+ query;
				return false;
			}
			
			
			query = "update ERP_THUTIEN set TRANGTHAI = '2', NGUOISUA = '"+ userId+ "', NGAYSUA = '"+ ngaysua+ "' where PK_SEQ = '" + this.id + "'";
			
			System.out.println("1.Cap nhat ERP_THUTIEN: " + query);

			if (!db.update(query)) {
				this.msg = "Khong the chot ERP_THUTIEN: " + query;
				System.out.println(this.msg);
				db.getConnection().rollback();
				return false;
			}
			
		/*	query = " update ERP_BANGKETHUTIEN SET TRANGTHAI = 0 WHERE PK_SEQ = (SELECT BANGKE_FK FROM ERP_THUTIEN WHERE PK_SEQ = "+this.id+") ";
			
			System.out.println("1.Cap nhat ERP_BANGKETHUTIEN: " + query);

			if (!db.update(query)) {
				this.msg = "Khong the chot ERP_THUTIEN: " + query;
				System.out.println(this.msg);
				db.getConnection().rollback();
				return false;
			}*/

			db.getConnection().commit();
			db.getConnection().setAutoCommit(true);
		} catch (java.sql.SQLException e) {
			try {
				db.getConnection().rollback();
				e.printStackTrace();
			} catch (SQLException e1) {
			}
			return false;
		}

		return true;
	}

	
	public void createRs_display() {
		// Kênh bán hàng

		NumberFormat formatter = new DecimalFormat("#,###,###.##");
		NumberFormat formatterNT = new DecimalFormat("#,###,###.##");

		//this.getNppInfo();

		if (this.ngaychungtu.length() > 0)
			this.ngayghiso = this.ngaychungtu;

		String command = "";
		
		
		this.nppRs = db.getScrol(" select CONVERT(VARCHAR, PK_SEQ) + ' -- ' + '1' AS PK_SEQ , Ma + ' - ' + TEN  as nppTen from NHAPHANPHOI where trangthai = '1' and PK_SEQ!=1 "); // LẤY NHÀ PHÂN PHỐI KHÁC != 1 VÌ 1 CHÍNH LÀ HO
				
		this.nhomkhttRs = db.get("Select pk_seq, diengiai,cast(pk_seq as varchar(10)) +' , ' +diengiai as ten  from Nhomkhachhangtt where trangthai='1'");		
		this.htttRs = db.get("select pk_seq, ma, ten from ERP_HINHTHUCTHANHTOAN where trangthai = '1' and ma in ('TIENMAT') ");
		this.ndRs = db.get("select pk_seq, ma, ten from ERP_NOIDUNGTHUTIEN where trangthai = '1' ");
				
		String query = "SELECT (CASE WHEN NH.MA = 'EIB' THEN 1 ELSE 2 END) AS STT ,  \n"+
					   "NH_CTY.SOTAIKHOAN, NH_CTY.SOTAIKHOAN + ' - ' + NH.TEN + ' - ' + CN.TEN + ', ' + TT.MA  AS TAIKHOAN \n"+
					   "FROM ERP_NGANHANG_CONGTY NH_CTY \n"+
					   "INNER JOIN ERP_TIENTE TT ON TT.PK_SEQ = NH_CTY.TIENTE_FK \n"+
					   "INNER JOIN ERP_NGANHANG NH ON NH.PK_SEQ = NH_CTY.NGANHANG_FK \n"+
					   "INNER JOIN ERP_CHINHANH CN ON CN.PK_SEQ = NH_CTY.CHINHANH_FK \n"+
					   "WHERE NH_CTY.TRANGTHAI = 1 AND NH_CTY.CONGTY_FK = '"+ this.ctyId + "' ";
		if (this.tienteId.length() > 0) {
			query = query + " AND TT.PK_SEQ = " + this.tienteId + " ";
		}

		query += " ORDER BY STT ";

		this.sotkRs = this.db.get(query);

		this.tienteRs = db.get("select pk_seq, ma + ', ' + ten as TEN from ERP_TIENTE ");

		String sql = "select pk_seq, ma + ', ' + ten as nppTen from ERP_KhachHang where trangthai = '1' ";

		sql = "select pk_seq, ma + ', ' + ten as nppTen from ERP_NHACUNGCAP where trangthai = '1' ";
		this.nppRs = db.getScrol(sql);
		

		if (this.htttId.equals("100001")) { // THU TIỀN KHÁCH HÀNG TRẢ TRƯỚC
			if (this.nganhangId.length() > 0) {
				this.chinhanhRs = db.get("select pk_seq, ma + ', ' + ten as cnTen from erp_chinhanh where PK_SEQ in ( select ChiNhanh_FK from ERP_NGANHANG_CONGTY where congty_fk = '"+ this.ctyId + "' and nganhang_fk = '" + this.nganhangId + "' )");
			}

			if (this.chinhanhId.length() > 0) {
				ResultSet rs = db.get("select sotaikhoan from ERP_NGANHANG_CONGTY where congty_fk = '" + this.ctyId	+ "' and chinhanh_fk = '" + this.chinhanhId + "' and nganhang_fk = '" + this.nganhangId + "' ");
				if (rs != null) {
					try {
						if (rs.next()) {
							this.sotaikhoan = rs.getString("sotaikhoan");
						}
						rs.close();
					} catch (SQLException e) {
					}
				}
			}

		}

		query = "";

		if (this.xoakhtratruocId.trim().length() == 0)
			this.xoakhtratruocId = "0";

		String layKH = "";
		if (this.nhomkhttId.trim().length() > 1) // NHÓM KH
		{
			layKH = " in ( select khachhang_fk  from NHOMKHACHHANGTT_KHACHHANGTT where nkhtt_fk ="+ this.nhomkhttId + ") ";
		} else if (this.nppId.trim().length() > 1) {
			layKH = " = '" + this.nppId + "' ";
		}

		query = "";

		if (this.xoakhtratruocId.trim().length() == 0)
			this.xoakhtratruocId = "0";

		if ( ( this.nhomkhttId.trim().length() > 1 || this.nppId.trim().length() > 1 ) && this.htttId.length() > 0 && this.hoadonList.size() <= 0) 
		{
			if (this.ndId.equals("100000")) { // THU TIEN HOA DON
				if (this.id.length() > 0) {

					// Loai hoa don : 0 Hd tài chính, 1 Hóa đơn khác
					// Lấy thu tiền this.id
					// HOADONTAICHINH
				query += "(SELECT '0' AS LOAIHD, ISNULL(HOADON.TYGIA, 1) as TIGIA , HOADON.PK_SEQ, HOADON.MAHOPDONG AS MAHOPDONG, \n"+
						 "        HOADON.KYHIEU, HOADON.SOHOADON, HOADON.NGAYHOADON, \n"+
						 "        HOADON.TONGTIENAVAT AS SOTIENGOC, \n"+
						 "        CAST((ISNULL(HOADON.TYGIA, 1)*(ISNULL(HOADON.TONGTIENAVAT, 0) - ISNULL(DATHANHTOAN.DATHANHTOAN, '0'))) AS numeric(18,0) ) AS SOTIENVND, \n"+
						 "        (HOADON.TONGTIENAVAT - ISNULL(DATHANHTOAN.DATHANHTOAN, '0')) AS SOTIENNT, \n"+
						 "        isnull( TTHD.SOTIENTT,0) AS SOTIENTT, HOADON.TTID, \n"+
						 "        HOADON.NGAYDENHANTT \n"+
						 "FROM ( \n"+
						 "		SELECT 	HD.PK_SEQ, '' AS MAHOPDONG, HD.KYHIEU, HD.SOHOADON, HD.NGAYXUATHD AS NGAYHOADON, \n"+
						 "				HD.TONGTIENAVAT, ISNULL(HD.TYGIA,1) AS TYGIA, ISNULL(HD.TIENTE_FK,100000) AS TTID, \n"+
						 "   			'' as NGAYDENHANTT \n"+
						 "		FROM 	DCL.dbo.ERP_HOADON HD 	\n"+
						 "        		INNER JOIN NHAPHANPHOI KH ON HD.NPP_FK = KH.PK_SEQ \n"+
						 "		WHERE 	HD.NPP_FK "+ layKH +
						 " 				AND HD.TRANGTHAI in (2,4) AND HD.LOAIHOADON = '0'  \n" +
						 "				AND HD.PK_SEQ IN (SELECT HOADON_FK FROM ERP_THUTIEN_HOADON WHERE THUTIEN_FK = '"+ this.id + "') \n";
				query += "		) HOADON \n"+
						 " INNER JOIN ERP_THUTIEN_HOADON TTHD ON HOADON.PK_SEQ = TTHD.HOADON_FK AND TTHD.THUTIEN_FK = "+ this.id + " AND TTHD.LOAIHOADON = 0 \n"+
						 " LEFT JOIN \n"+
						 " ( \n"+
						 "	SELECT HOADON_FK, SUM(ISNULL(DATHANHTOAN, 0)) AS DATHANHTOAN \n"+
						 "	FROM  \n"+
						 "	( 	\n"+
						 "		SELECT TTHD.HOADON_FK, SUM(TTHD.SOTIENTT) AS DATHANHTOAN \n"+
						 "		FROM ERP_XOAKHTRATRUOC_HOADON TTHD \n"+
						 "		INNER JOIN ERP_XOAKHTRATRUOC TT ON TTHD.XOAKHTRATRUOC_FK = TT.PK_SEQ \n"+
						 "		WHERE TT.TRANGTHAI NOT IN (2) AND ISNULL(TT.LOAIXOATRATRUOC,0) = '0'  AND TTHD.LOAIHD = '0'  \n"+
						 "		GROUP BY HOADON_FK \n"+
						
						 "		UNION ALL \n"+
						
						 "		SELECT TTHD.HOADON_FK , SUM(TTHD.SOTIENTT) AS DATHANHTOAN \n"+
						 "		FROM ERP_THUTIEN_HOADON TTHD \n"+
						 "		INNER JOIN ERP_THUTIEN TT ON TTHD.THUTIEN_FK = TT.PK_SEQ \n"+
						 "		WHERE TTHD.LOAIHOADON= '0' AND TT.TRANGTHAI NOT IN (2) AND TT.PK_SEQ  != '" + this.id + "' \n";

				query += " 		GROUP BY HOADON_FK \n"+

						"		UNION ALL  \n"+

						"		SELECT BT_KH.HOADON_FK, SUM(BT_KH.XOANO) AS SOTIENBUTRU  \n"+
						"		FROM   ERP_BUTRUKHACHHANG BT INNER JOIN ERP_BUTRUKHACHHANG_CHITIET BT_KH ON BT.PK_SEQ =BT_KH.BTKH_FK \n"+
						"		WHERE  BT.TRANGTHAI NOT IN (2) AND BT_KH.LOAIHD = 0  AND BT.KH_CHUYENNO "+ layKH + "  \n " +
						"		GROUP BY BT_KH.HOADON_FK \n"+

						"	) HOADONDATT  \n"+
						"	GROUP BY HOADON_FK "+
						")DATHANHTOAN ON HOADON.PK_SEQ = DATHANHTOAN.HOADON_FK \n"+
						" WHERE TTHD.THUTIEN_FK = "+ this.id + " AND TTHD.LOAIHOADON = 0 )\n";
										
					query += " UNION ALL \n";
				}

				// HOADONTAICHINH
				query += "(SELECT '0' AS LOAIHD, ISNULL(HOADON.TYGIA, 1) as TIGIA , HOADON.PK_SEQ, HOADON.MAHOPDONG AS MAHOPDONG, \n"+
						 "        HOADON.KYHIEU, HOADON.SOHOADON, HOADON.NGAYHOADON, \n"+
						 "        HOADON.TONGTIENAVAT AS SOTIENGOC, \n"+
						 "        CAST((ISNULL(HOADON.TYGIA, 1)*(ISNULL(HOADON.TONGTIENAVAT, 0) - ISNULL(DATHANHTOAN.DATHANHTOAN, '0'))) AS numeric(18,0) ) AS SOTIENVND, \n"+
						 "        (HOADON.TONGTIENAVAT - ISNULL(DATHANHTOAN.DATHANHTOAN, '0')) AS SOTIENNT, \n"+
						 "        0 AS DATHANHTOAN, HOADON.TTID, \n"+
						 "        HOADON.NGAYDENHANTT \n"+
						 "FROM ( \n"+
						 "		SELECT 	HD.PK_SEQ, '' AS MAHOPDONG, HD.KYHIEU, HD.SOHOADON, HD.NGAYXUATHD AS NGAYHOADON, \n"+
						 "				HD.TONGTIENAVAT, ISNULL(HD.TYGIA,1) AS TYGIA, ISNULL(HD.TIENTE_FK,100000) AS TTID, \n"+
						 "   			'' as NGAYDENHANTT \n"+
						 "		FROM 	DCL.dbo.ERP_HOADON HD 	\n"+
						 "        		INNER JOIN NHAPHANPHOI KH ON HD.NPP_FK = KH.PK_SEQ \n"+
						 "		WHERE 	HD.NPP_FK "+ layKH +
						 " 				AND HD.TRANGTHAI in (2,4) AND HD.LOAIHOADON = '0'  \n";

				if (this.id.length() > 0) {
					query += "				AND HD.PK_SEQ NOT IN (SELECT HOADON_FK FROM ERP_THUTIEN_HOADON WHERE THUTIEN_FK = '"+ this.id + "') \n";

				}

				query += "		) HOADON \n"+
						 "LEFT JOIN ( \n"+
						 "	SELECT HOADON_FK, SUM(ISNULL(DATHANHTOAN, 0)) AS DATHANHTOAN \n"+
						 "	FROM  \n"+
						 "	( 	\n"+
						 "		SELECT TTHD.HOADON_FK, SUM(TTHD.SOTIENTT) AS DATHANHTOAN \n"+
						 "		FROM ERP_XOAKHTRATRUOC_HOADON TTHD \n"+
						 "		INNER JOIN ERP_XOAKHTRATRUOC TT ON TTHD.XOAKHTRATRUOC_FK = TT.PK_SEQ \n"+
						 "		WHERE TT.TRANGTHAI NOT IN (2) AND ISNULL(TT.LOAIXOATRATRUOC,0) = '0'  AND TTHD.LOAIHD = '0'  \n"+
						 "		GROUP BY HOADON_FK \n"+
						
						 "		UNION ALL \n"+
						
						 "		SELECT TTHD.HOADON_FK , SUM(TTHD.SOTIENTT) AS DATHANHTOAN \n"+
						 "		FROM ERP_THUTIEN_HOADON TTHD \n"+
						 "		INNER JOIN ERP_THUTIEN TT ON TTHD.THUTIEN_FK = TT.PK_SEQ \n"+
						 "		WHERE TTHD.LOAIHOADON= '0' AND TT.TRANGTHAI NOT IN (2) ";

				if (this.id.trim().length() > 0) {
					query += " 		AND TT.PK_SEQ  != '" + this.id + "' \n";
				}

				query += " 		GROUP BY HOADON_FK \n"+

						"		UNION ALL  \n"+

						"		SELECT BT_KH.HOADON_FK, SUM(BT_KH.XOANO) AS SOTIENBUTRU  \n"+
						"		FROM   ERP_BUTRUKHACHHANG BT INNER JOIN ERP_BUTRUKHACHHANG_CHITIET BT_KH ON BT.PK_SEQ =BT_KH.BTKH_FK \n"+
						"		WHERE  BT.TRANGTHAI NOT IN (2) AND BT_KH.LOAIHD = 0  AND BT.KH_CHUYENNO "+ layKH + "  \n " +
						"		GROUP BY BT_KH.HOADON_FK \n"+

						"	) HOADONDATT  \n"+
						"	GROUP BY HOADON_FK "+
						")DATHANHTOAN ON HOADON.PK_SEQ = DATHANHTOAN.HOADON_FK \n"+
						" WHERE HOADON.TONGTIENAVAT - ISNULL(DATHANHTOAN.DATHANHTOAN, '0') > 0 AND HOADON.TTID = "+ this.tienteId + " \n" + ")\n";

				query += " ORDER BY LOAIHD, NGAYHOADON ASC ";

			}

			System.out.println("1.Query khoi tao hoa don  : " + query);
			if(query.trim().length() > 0)
			{
				ResultSet rsHoadon = db.get(query);
				int row;
				try {
					row = rsHoadon.getRow();
					System.out.println("Size resultset:"+ row);
				} catch (SQLException e1) {
					
					e1.printStackTrace();
				}
				
				List<IHoadon> hdList = new ArrayList<IHoadon>();
				if (rsHoadon != null) {
					try {
						IHoadon hd = null;
						while (rsHoadon.next()) {
	
							String id = rsHoadon.getString("PK_SEQ");
							String kyhieu = rsHoadon.getString("KYHIEU");
							String sohoadon = rsHoadon.getString("SOHOADON");
							String ngayhd = rsHoadon.getString("NGAYHOADON");
							String sotiengoc = rsHoadon.getString("SOTIENGOC");
							String avat = "" + rsHoadon.getDouble("SOTIENVND");
							String sotienNT = "" + rsHoadon.getDouble("SOTIENNT");
							String ttId = rsHoadon.getString("TTID");
							String tygia = rsHoadon.getString("TIGIA");
							String ngaydenhanTT = rsHoadon.getString("NGAYDENHANTT");
							String loaihd = "";
							String tenloaihd = "";
							String mahopdong = "";
	
							if (this.ndId.equals("100000")) {
								mahopdong = rsHoadon.getString("MAHOPDONG");
								loaihd = rsHoadon.getString("LOAIHD");
	
								if (loaihd.equals("0"))
									tenloaihd = "Hóa đơn tài chính";
								if (loaihd.equals("1"))
									tenloaihd = "Hóa đơn khác";
								if (loaihd.equals("2"))
									tenloaihd = "Giảm/Tăng giá hàng bán";
								if (loaihd.equals("3"))
									tenloaihd = "Khách hàng trả trước";
								if (loaihd.equals("4"))
									tenloaihd = "Bù trừ công nợ";
							}
							String dathanhtoan = "0";
							if (this.id.length() > 0) {
								if (Math.abs(rsHoadon.getDouble("SOTIENTT")) > 0) {
									dathanhtoan = ""+ rsHoadon.getDouble("SOTIENTT");
								}
							}
							hd = new Hoadon(id, mahopdong, kyhieu, sohoadon,
									ngayhd, sotiengoc, avat, sotienNT, dathanhtoan,
									ttId, "", tygia);
							hd.setLoaihd(loaihd);
							hd.setNgaydenhanTT(ngaydenhanTT);
							hd.setTenloaihd(tenloaihd);
							hdList.add(hd);
	
						}
						rsHoadon.close();
					} catch (SQLException e) {
						e.printStackTrace();
					}
				}
				this.hoadonList = hdList;
				System.out.println("Size: " + this.hoadonList.size());
			}

		}

	}
	
	
	Object loainhanvien;
	Object doituongIddn;
	public String getLoainhanvien() 
	{
		if( this.loainhanvien == null )
			return "";
		
		return this.loainhanvien.toString();
	}

	public void setLoainhanvien(Object loainhanvien) 
	{
		this.loainhanvien = loainhanvien;
	}
	
	public String getDoituongIddn() 
	{
		if( this.doituongIddn == null )
			return "";
		
		return this.doituongIddn.toString();
	}

	public void setDoituongIddn(Object doituongIddn) 
	{
		this.doituongIddn = doituongIddn;
	}

	
	public String getIsChuyenCN() {
		
		return this.isChuyenCN;
	}

	
	public void setIsChuyenCN(String isChuyenCN) {
		
		this.isChuyenCN = isChuyenCN;
	}

	
	public String getNPPChinhanhId() {
		
		return this.nppChinhanhId;
	}

	
	public void setNPPChinhanhId(String nppchinhanhId) {
		
		this.nppChinhanhId = nppchinhanhId;
	}

	
	public String getIsthuhoCN() {
		
		return this.isthuhoCN;
	}

	
	public void setIsthuhoCN(String isthuhoCN) {
		
		this.isthuhoCN  = isthuhoCN;
	}
	public String getBpkinhdoanh() 
	{
		return this.bpkinhdoanh;
	}

	public void setBpkinhdoanh(String Bpkinhdoanh)
	{
		this.bpkinhdoanh = Bpkinhdoanh;
	}
	
	public Hashtable<String, String> getHoadon_chungloai() {

		return this.hd_chungloai;
	}


	public void setHoadon_chungloai(Hashtable<String, String> hd_chungloai) {
		
		this.hd_chungloai = hd_chungloai;
	}


}