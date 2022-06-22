package geso.traphaco.erp.beans.thutien.imp;

import geso.traphaco.center.db.sql.dbutils;
import geso.traphaco.center.util.Utility;
import geso.traphaco.erp.beans.thutien.IDinhkhoanco;
import geso.traphaco.erp.beans.thutien.IErpThutienCK;
import geso.traphaco.erp.beans.thutien.IHoadon;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class ErpThutienCK implements IErpThutienCK {
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

	String kbhId;
	String nppdangnhap;
	ResultSet kbhRs;

	String bangkeId;
	ResultSet bangkeRs;

	
	String isNPP;
	String nppIdgoc;
	
	String nppChinhanhId; 
	String isChuyenCN;
	String isthuhoCN;

	public ErpThutienCK() {
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

		this.bangkeId = "";

		this.isNPP = "";
		this.nppIdgoc = "";
		
		this.isChuyenCN = "0";	
		this.isthuhoCN = "0";
		this.nppChinhanhId = "";

		this.db = new dbutils();
		this.util = new Utility();
	}

	public ErpThutienCK(String id) {
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
		this.bangkeId = "";

		this.isNPP = "";
		this.nppIdgoc = "";
		
		this.isChuyenCN = "0";	
		this.nppChinhanhId = "";
		this.isthuhoCN = "0";

		this.hoadonList = new ArrayList<IHoadon>();
		this.util = new Utility();
		this.db = new dbutils();
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


		/*if ((this.ndId.equals("100000") || this.ndId.equals("100001")) && this.kbhId.trim().length() <= 0) {
			this.msg = "Khách hàng này chưa thuộc Kênh bán hàng nào. Vui lòng thiết lập lại trong dữ liệu nền.";
			return false;
		}*/

		if (this.ndId.trim().length() <= 0) {
			this.msg = "Vui lòng chọn nội dung thu tiền";
			return false;
		}

		if (this.ndId.equals("100000") || this.ndId.equals("100003") || this.ndId.equals("100004")) // Thu tiền bán hàng
		{
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
			if (this.sotaikhoan.length() > 0) {
				ResultSet rs = this.db.get("SELECT NGANHANG_FK, CHINHANH_FK FROM ERP_NGANHANG_CONGTY WHERE SOTAIKHOAN = '"+ this.sotaikhoan + "'");
				if (rs != null) {
					rs.next();
					this.nganhangId = rs.getString("NGANHANG_FK");
					this.chinhanhId = rs.getString("CHINHANH_FK");
					rs.close();
				}

				if (this.ndId.equals("100004"))
					khachhang_fk = null;
				else
					this.bangkeId = null;
				
				if(this.isNPP.trim().length()<=0)
					this.isNPP = "null";

				query = "Insert ERP_THUTIEN(KBH_FK, NGUOINOPTIEN, DIACHI, NHOMKHTT_FK,  CHIETKHAUNT, CHIETKHAU, TIGIA, SOTIENTTNT, THUDUOCNT, PHINGANHANGNT, THUDUOC, PHINGANHANG, CHENHLECH, NGAYCHUNGTU, NGAYGHISO, TRANGTHAI, KHACHHANG_FK, HTTT_FK, \n"+
						"NGANHANG_FK, CHINHANH_FK, SOTAIKHOAN, NOIDUNGTT_FK, GHICHU, SOTIENTT, TIENTE_FK, SOCHUNGTU, NGAYTAO, NGUOITAO, NGAYSUA, NGUOISUA, NCC_FK, NHANVIEN_FK, CTKEMTHEO, ISKTTDUYET, CONGTY_FK, BANGKE_FK, PREFIX, isNPP, NPPCN_FK , ISThuhoCN ) \n"+
						"values( " + this.kbhId + ", N'" + this.nguoinoptien + "', N'" + this.diachi + "' , \n"+
						this.nhomkhttId + ", " + this.chietkhauNT.replace(",", "") + "," +
						this.chietkhau.replace(",", "") + " ," + this.tigia.replaceAll(",", "") + ", " +
						this.sotienttNT.replaceAll(",", "") + ", " + this.thuduocNT.replaceAll(",", "") + ", " +
						this.cpnganhangNT.replaceAll(",", "") + ", " + this.thuduoc + ", " + this.cpnganhang + ", " +
						this.chenhlech + ", '" + this.ngaychungtu + "', '" + this.ngayghiso + "', '0', " + 
						khachhang_fk + ", '" + this.htttId + "', " + this.nganhangId + ", " + this.chinhanhId + ", '" +
						this.sotaikhoan + "', " + " '" + this.ndId + "', N'" + this.noidungtt + "', '"+
						this.sotientt.replaceAll(",", "") + "', '" + this.tienteId + "', '" + this.sochungtu + "', '" +
						ngaytao + "', '" + this.userId + "', '" + ngaytao + "', '" + this.userId + "', " + this.nccId +
						", " + this.nvtuId + ", N'" + this.chungtukemtheo + "', 0, " + this.ctyId + ", " +
						this.bangkeId + ", 'BC', "+this.isNPP+", "+( this.nppChinhanhId ==""?null:this.nppChinhanhId ) +", "+ (this.isthuhoCN ==""?null:this.isthuhoCN ) +" )";
				
				System.out.println(query);

			} else {

				if (this.ndId.equals("100004"))
					khachhang_fk = null;
				else
					this.bangkeId = null;

				query = "Insert ERP_THUTIEN(KBH_FK, NGUOINOPTIEN, DIACHI, NHOMKHTT_FK, CHIETKHAUNT, CHIETKHAU, TIGIA, SOTIENTTNT, THUDUOCNT, PHINGANHANGNT, THUDUOC, PHINGANHANG, CHENHLECH, NGAYCHUNGTU, NGAYGHISO, TRANGTHAI, \n"+
						"KHACHHANG_FK, HTTT_FK, NGANHANG_FK, CHINHANH_FK, SOTAIKHOAN, NOIDUNGTT_FK, GHICHU, SOTIENTT, TIENTE_FK, SOCHUNGTU, NGAYTAO, \n"+
						" NGUOITAO, NGAYSUA, NGUOISUA, NCC_FK, NHANVIEN_FK, CTKEMTHEO, ISKTTDUYET,CONGTY_FK, BANGKE_FK, PREFIX, isNPP, NPPCN_FK , ISThuhoCN ) \n"+
						"values( " + this.kbhId + ", N'" + this.nguoinoptien + "', N'" + this.diachi + "' , \n"+
						this.nhomkhttId + ", " + this.chietkhauNT.replace(",", "") + ","+
						this.chietkhau.replace(",", "") + " ," + this.tigia.replaceAll(",", "") + ", "+
						this.sotienttNT.replaceAll(",", "") + ", " + this.thuduocNT.replaceAll(",", "") + ", "+
						this.cpnganhangNT.replaceAll(",", "") + ", " + this.thuduoc + ", " + this.cpnganhang + ", "+
						this.chenhlech + ", '" + this.ngaychungtu + "', '" + this.ngayghiso + "', '0', "+
						khachhang_fk + ", '" + this.htttId + "', NULL, NULL, '', " + " '" + this.ndId + "', N'"+
						this.noidungtt + "', '" + this.sotientt.replaceAll(",", "") + "', '" + this.tienteId + "', '"+
						this.sochungtu + "', '" + ngaytao + "', '" + this.userId + "', '" + ngaytao + "', '"+
						this.userId + "', " + this.nccId + ", " + this.nvtuId + ",N'" + this.chungtukemtheo + "', 0, "+
						this.ctyId + ", " + this.bangkeId + ", 'BC', "+this.isNPP+", "+( this.nppChinhanhId ==""?null:this.nppChinhanhId ) +", "+ (this.isthuhoCN ==""?null:this.isthuhoCN ) +" )";
				System.out.println(query);
			}

			System.out.println(query);
			if (!db.update(query)) {
				this.msg = "Khong the tao moi ERP_THUTIEN: " + query;
				System.out.println("[ErpThutien.createTTHD] error message:" + this.msg);
				db.getConnection().rollback();
				return false;
			}

			String tthdCurrent = "";
			query = "select IDENT_CURRENT('ERP_THUTIEN') as tthdId";

			ResultSet rsTthd = db.get(query);
			if (rsTthd.next()) {
				tthdCurrent = rsTthd.getString("tthdId");
				rsTthd.close();
			}
		
			if(this.ndId.equals("100003") || this.ndId.equals("100000") ) // THU HỒI TẠM ỨNG
			{				
				for (int i = 0; i < this.hoadonList.size(); i++) {
					IHoadon hoadon = this.hoadonList.get(i);

					String thanhtoan = hoadon.getThanhtoan().replaceAll(",", "");
					String avat = hoadon.getTongtiencoVAT().replaceAll(",","");
//					String sotienNT = hoadon.getSotienNT().replaceAll(",","");
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
						

						query = "Insert into ERP_THUTIEN_DINHKHOANCO(THUTIEN_FK, TAIKHOAN_FK, DOITUONGDINHKHOAN, DOITUONG_FK, SOTIENNT, SOTIEN, ISNPP) "+
								"values('" + tthdCurrent + "', '" + dkc.getTaikhoanId() + "', '" + doituongdk + "', "+
								doituong_fk + ", '" + sotiencoNT + "' , '" + sotienco + "', " +is_NPP+ ")";

						if (!db.update(query)) {
							this.msg = "Khong the tao moi ERP_THUTIEN_HOADON: " + query;
							db.getConnection().rollback();
							return false;
						}
					}
				}
			}
			
			// cập nhật mã chứng từ
			query = " update ERP_THUTIEN set machungtu = 'BC' + SUBSTRING(NGAYGHISO, 6, 2) + SUBSTRING(NGAYGHISO, 0, 5) + '-' + dbo.LaySoChungTu( " + tthdCurrent + " ) " + 
					" where pk_seq = '" + tthdCurrent + "' ";
			
			System.out.println("[ErpThutien.createTTHD] error message:" + query);
			
			if(!db.update(query))
			{
				this.msg = "Khong the tao moi ERP_THUTIEN: " + query;
				System.out.println("[ErpThutien.createTTHD] error message:" + this.msg);
				db.getConnection().rollback();
				return false;
			}

			db.getConnection().commit();
			db.getConnection().setAutoCommit(true);
		} catch (SQLException e) {
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

	/*	if ((this.ndId.equals("100000")) && this.kbhId.trim().length() <= 0) {
			this.msg = "Khách hàng này chưa thuộc Kênh bán hàng nào. Vui lònsg thiết lập lại trong dữ liệu nền.";
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

			// NẾU CHỌN THU THEO NHÓM KHÁCH HÀNG THÌ CHO CỘT KHACHHANG_FK = NULL
			if (this.nhomkhtt.equals("1"))
				khachhang_fk = "NULL";

			if (this.ndId.equals("100004")) {
				khachhang_fk = null;
				this.isNPP = null;
			} else
				this.bangkeId = null;
						
			String query = "";

			// HÌNH THỨC THANH TOÁN LÀ CHUYỂN KHOẢN THÌ K CẦN KẾ TOÁN TRƯỞNG
			// DUYỆT
			
			if (this.sotaikhoan.length() > 0) {
				ResultSet rs = this.db.get("SELECT NGANHANG_FK, CHINHANH_FK FROM ERP_NGANHANG_CONGTY WHERE SOTAIKHOAN = '"+ this.sotaikhoan + "'");
				if (rs != null) {
					rs.next();
					this.nganhangId = rs.getString("NGANHANG_FK");
					this.chinhanhId = rs.getString("CHINHANH_FK");
					rs.close();
				}
			}

			query = "update ERP_THUTIEN set NGUOINOPTIEN = N'" + this.nguoinoptien + "', DIACHI = N'" + this.diachi +
					"' , NHOMKHTT_FK = " + this.nhomkhttId + ", CHIETKHAUNT= " + this.chietkhauNT.replace(",", "") +
					" ,CHIETKHAU= " + this.chietkhau.replace(",", "") + " , SOTIENTTNT = " +
					this.sotienttNT.replaceAll(",", "") + ", TIGIA = " + this.tigia.replaceAll(",", "") +
					", THUDUOCNT = " + this.thuduocNT.replaceAll(",", "") + ", PHINGANHANGNT = " +
					this.cpnganhangNT.replaceAll(",", "") + ", THUDUOC = " + this.thuduoc + ", PHINGANHANG = "+
					this.cpnganhang + ", CHENHLECH = " + this.chenhlech + ", " + "NGAYCHUNGTU = '" +
					this.ngaychungtu + "', NGAYGHISO = '" + this.ngayghiso + "', " + "KHACHHANG_FK = "+
					khachhang_fk + ", HTTT_FK = '" + this.htttId + "', "+
					"NGANHANG_FK = "+this.nganhangId+", CHINHANH_FK ="+this.chinhanhId +", SOTAIKHOAN = '"+this.sotaikhoan+"'," + " NOIDUNGTT_FK = '" + this.ndId +
					"', GHICHU = N'" + this.noidungtt + "', SOTIENTT = '" + this.sotientt.replaceAll(",", "") +
					"', SOCHUNGTU = '" + this.sochungtu + "', TIENTE_FK = '" + this.tienteId + "', "+
					"NGAYSUA = '" + ngaysua + "', NGUOISUA = '" + this.userId + "', NCC_FK= " + this.nccId +
					" , NHANVIEN_FK= " + this.nvtuId + " , CTKEMTHEO = N'" + this.chungtukemtheo +
					"', BANGKE_FK =  " + this.bangkeId + ", PREFIX = 'BC', isNPP =  " + (this.isNPP ==""?null:this.isNPP ) + ", NPPCN_FK = "+( this.nppChinhanhId ==""?null:this.nppChinhanhId )  +", ISThuhoCN = "+( this.isthuhoCN ==""?null:this.isthuhoCN ) +
					" where PK_SEQ = '" + this.id + "'";
			
			System.out.println(query);

			if (!db.update(query)) {
				this.msg = "Khong the cap nhat ERP_THUTIEN: " + query;
				System.out.println(this.msg);
				db.getConnection().rollback();
				return false;
			}

			// -------------------------------End---------------------

			query = "delete ERP_THUTIEN_HOADON where THUTIEN_FK = '" + this.id + "'";
			db.update(query);

			query = "delete ERP_THUTIEN_HOADON_SP where THUTIEN_FK = '" + this.id + "'";
			db.update(query);

			query = "delete ERP_THUTIEN_DINHKHOANCO where THUTIEN_FK = '" + this.id + "'";
			db.update(query);
			
			if(this.ndId.equals("100003") || this.ndId.equals("100000")) // THU HỒI TẠM ỨNG
			{
//				String tthdCurrent = "";
//				query = "select IDENT_CURRENT('ERP_THUTIEN') as tthdId";
//
//				ResultSet rsTthd = db.get(query);
//				if (rsTthd.next()) 
//				{
//					tthdCurrent = rsTthd.getString("tthdId");
//					rsTthd.close();
//				}
				
				for (int i = 0; i < this.hoadonList.size(); i++) {
					IHoadon hoadon = this.hoadonList.get(i);

					String thanhtoan = hoadon.getThanhtoan().replaceAll(",", "");
					String avat = hoadon.getTongtiencoVAT().replaceAll(",","");
//					String sotienNT = hoadon.getSotienNT().replaceAll(",","");
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
			
			// cập nhật mã chứng từ
			query = " update ERP_THUTIEN set machungtu = 'BC' + SUBSTRING(NGAYGHISO, 6, 2) + SUBSTRING(NGAYGHISO, 0, 5) + '-' + dbo.LaySoChungTu( " + this.id + " ) " + 
					" where pk_seq = '" + this.id + "' ";
			
			System.out.println("[ErpThutien.createTTHD] error message:" + query);
			
			if(!db.update(query))
			{
				this.msg = "Khong the tao moi ERP_THUTIEN: " + query;
				System.out.println("[ErpThutien.createTTHD] error message:" + this.msg);
				db.getConnection().rollback();
				return false;
			}
			
			db.getConnection().commit();
			db.getConnection().setAutoCommit(true);
		} catch (SQLException e) {
			try {
				System.out.println(e.toString());
				db.getConnection().rollback();
			} catch (SQLException e1) {
			}
			return false;
		}

		return true;
	}

	public boolean chotTTHD(String userId) {
		try {
			String ngaysua = getDateTime();

			db.getConnection().setAutoCommit(false);

			String query = "update ERP_THUTIEN set TRANGTHAI = '1', NGUOISUA = '" + userId + "', NGAYSUA = '" + ngaysua	+ "' where PK_SEQ = '" + this.id + "'";
			System.out.println("1.Cap nhat ERP_THUTIEN: " + query);

			if (!db.update(query)) {
				this.msg = "Khong the chot ERP_THUTIEN: " + query;
				System.out.println(this.msg);
				db.getConnection().rollback();
				return false;
			}

			// KIỂM TRA PHIẾU THU NẾU CÓ PHIẾU XÓA NỢ KH THÌ CHỐT PHIẾU XÓA NỢ
			query = "select XOAKHTRATRUOC_FK from ERP_THUTIEN where PK_SEQ = " + this.id + " ";
			ResultSet rsKT = db.get(query);
			if (rsKT != null) {
				while (rsKT.next()) {
					this.xoakhtratruocId = rsKT.getString("XOAKHTRATRUOC_FK") == null ? "": rsKT.getString("XOAKHTRATRUOC_FK");
				}
				rsKT.close();
			}

			if (this.xoakhtratruocId.trim().length() > 0) {
				query = "update ERP_XOAKHTRATRUOC set TRANGTHAI = '1', NGUOISUA = '" + userId + "', NGAYSUA = '"+ ngaysua + "' where PK_SEQ = '" + this.xoakhtratruocId + "'";
				System.out.println("1.Cap nhat ERP_THUTIEN: " + query);

				if (!db.update(query)) {
					this.msg = "Khong the chot ERP_XOANOKH: " + query;
					db.getConnection().rollback();
					return false;
				}
			}

//			String ngayghiso = "";
//			query = "Select ngayghiso from ERP_THUTIEN where pk_seq = '" + this.id + "' ";
//			ResultSet rs = db.get(query);
//			try {
//				if (rs != null) {
//					while (rs.next()) {
//						ngayghiso = rs.getString("ngayghiso");
//					}
//					rs.close();
//				}
//			} catch (Exception e) {
//				e.printStackTrace();
//			}

			// ghi nhan but toan

			String doituong_CO = "";
			String madoituong_CO = "";

			String doituong_NO = "";
			String madoituong_NO = "";
			
			String noidungthutien = "";
			String NPPCN_FK = "";			
			String isnpp = "";
			String khachhang_fk = "";
			double tonggiatri = 0;

				 {					
					
					query = "select  tt.ngayghiso, tt.tiente_fk, tt.sotienTT, isnull(tt.thuduoc, 0) as thuduoc, isnull(chietkhau,0) as chietkhau, \n"+
							" isnull(tt.phinganhang, 0) as phinganhang, isnull(tt.chenhlech, 0) as chenhlech, tt.dinhkhoanco, tt.noidungtt_fk , \n"+
							" tt.khachhang_fk, tt.httt_fk, tt.nganhang_fk, tt.chinhanh_fk, kh.ma as maKH, tt.noidungtt_fk , \n"+
							" (case when tt.NCC_FK is not null then (ncc.TAIKHOAN_FK ) \n"+
							"       else ( nv.TAIKHOAN_FK ) end ) as taikhoanCO_NCC_NV_SoHieu , \n"+
							" (case when tt.NCC_FK is not null then N'Nhà cung cấp' else N'Nhân viên' end )as DOITUONG_TU, \n"+
							" (case when tt.NCC_FK is not null then tt.NCC_FK else tt.NHANVIEN_FK  end) as NCC_NV_FK , \n"+
							" ( select TaiKhoan_fk from ERP_NGANHANG_CONGTY where Sotaikhoan=tt.sotaikhoan AND CONGTY_FK = "+this.ctyId+"  ) as taikhoanNO_SoHieu, \n"+
							" ( SELECT PK_SEQ FROM ERP_TAIKHOANKT WHERE SOHIEUTAIKHOAN = '13111000' AND CONGTY_FK = "+this.ctyId+"   ) as taikhoanCO_KH_SoHieu,  \n"+
							" (SELECT PK_SEQ FROM ERP_TAIKHOANKT WHERE SOHIEUTAIKHOAN = '11110000' AND CONGTY_FK = "+this.ctyId+" ) AS HTTHANHTOAN, \n"+
							" (SELECT PK_SEQ FROM ERP_TAIKHOANKT WHERE SOHIEUTAIKHOAN = '64251000' AND CONGTY_FK = "+this.ctyId+") AS taikhoanNo_PhiNganHang, \n"+
							" (SELECT PK_SEQ FROM ERP_TAIKHOANKT WHERE SOHIEUTAIKHOAN = '81180000' AND CONGTY_FK = "+this.ctyId+") AS taikhoanNo_ChenhLechD_VND, \n"+
							" (SELECT PK_SEQ FROM ERP_TAIKHOANKT WHERE SOHIEUTAIKHOAN = '63580000' AND CONGTY_FK = "+this.ctyId+") AS taikhoanNo_ChenhLechD_NGOAITE, \n"+
							" (SELECT PK_SEQ FROM ERP_TAIKHOANKT WHERE SOHIEUTAIKHOAN = '71180000' AND CONGTY_FK = "+this.ctyId+") AS taikhoanCo_ChenhLechA_VND, \n"+
							" (SELECT PK_SEQ FROM ERP_TAIKHOANKT WHERE SOHIEUTAIKHOAN = '51580000' AND CONGTY_FK = "+this.ctyId+") AS taikhoanCo_ChenhLechA_NGOAITE, \n"+
							" (SELECT PK_SEQ FROM ERP_TAIKHOANKT WHERE SOHIEUTAIKHOAN = '11110000' AND CONGTY_FK = "+this.ctyId+") AS taikhoanNo_thuhoi, \n"+
							" (SELECT PK_SEQ FROM ERP_TAIKHOANKT WHERE SOHIEUTAIKHOAN = '64180000' AND CONGTY_FK = "+this.ctyId+" ) AS taikhoanNo_chietkhau, \n"+
							" (select PK_SEQ FROM ERP_TAIKHOANKT WHERE SOHIEUTAIKHOAN = '71100000' AND CONGTY_FK = "	+ this.ctyId + " ) taikhoanCO_chietkhau, isnull(tt.MACHUNGTU,'') MACHUNGTU, isnull(tt.isNPP, 0) isNPP, TT.NPPCN_FK, isnull(tt.ISThuhoCN, 0) ISThuhoCN  \n"+ 				
							"from erp_thutien tt  \n"+
							"left join KHACHHANG kh on tt.khachhang_fk = kh.pk_seq \n"+
							"left join ERP_NHACUNGCAP ncc on tt.NCC_FK = ncc.PK_SEQ \n"+
							"left join ERP_NHANVIEN nv on tt.NHANVIEN_FK = nv.PK_SEQ \n"+
							"where tt.pk_seq = '" + this.id + "' ";
										
					System.out.println("___Check but toan: " + query);

					String taikhoanNO_CK_SoHieu = "";
					String taikhoanCO_CK_SoHieu = "";
					String machungtu = "";
					String ISThuhoCN = "";
					
					double xoachenhlech = 0;
					double tienchenhlech = 0;
					
					ResultSet psktRs = db.get(query);
					if (psktRs != null) {
						while (psktRs.next()) {
							machungtu = psktRs.getString("MACHUNGTU");
							
							khachhang_fk = psktRs.getString("khachhang_fk");
							String nganhang_fk = psktRs.getString("nganhang_fk");
							String hinhthuctt = psktRs.getString("httt_fk");
							isnpp = psktRs.getString("isnpp");
							ISThuhoCN =  psktRs.getString("ISThuhoCN");
							
							tonggiatri = Math.round(psktRs.getDouble("thuduoc"));

							String nam = psktRs.getString("ngayghiso").substring(0,4);
							String thang = psktRs.getString("ngayghiso").substring(5, 7);
							String tiente_fk = psktRs.getString("tiente_fk");
							noidungthutien = psktRs.getString("noidungtt_fk");

							NPPCN_FK = psktRs.getString("NPPCN_FK") == null ? "": psktRs.getString("NPPCN_FK");
							
							String taikhoanCO_SoHieu = psktRs.getString("taikhoanCO_KH_SoHieu") == null ? "": psktRs.getString("taikhoanCO_KH_SoHieu");

							String taikhoanNo_PhiNganHang = psktRs.getString("taikhoanNo_PhiNganHang") == null ? "": psktRs.getString("taikhoanNo_PhiNganHang");
							String taikhoanNO_SoHieu = "";
							
							//taikhoanNO_CK_SoHieu = psktRs.getString("taikhoanNO_chietkhau")== null ? "": psktRs.getString("taikhoanNO_chietkhau");
							taikhoanCO_CK_SoHieu = psktRs.getString("taikhoanCO_chietkhau")== null ? "": psktRs.getString("taikhoanCO_chietkhau");
						

							if (hinhthuctt.equals("100001")) // thanh toan NGANHANG// (CHUYEN KHOAN)
							{
								taikhoanNO_SoHieu = psktRs.getString("taikhoanNO_SoHieu") == null ? "": psktRs.getString("taikhoanNO_SoHieu");

								doituong_NO = "Ngân hàng";
								madoituong_NO = nganhang_fk;

								doituong_CO = "Khách hàng";
								madoituong_CO = khachhang_fk;
							} else {
								taikhoanNO_SoHieu = psktRs.getString("HTTHANHTOAN") == null ? "": psktRs.getString("taikhoanNO_SoHieu");

								doituong_NO = "";
								madoituong_NO = "";

								doituong_CO = "Khách hàng";
								madoituong_CO = khachhang_fk;
							}

							if (noidungthutien.equals("100000") || noidungthutien.equals("100001")) // THU TIEN HÓA ĐƠN && KHACH HANG TRA TRUOC
							{
								// GHI NHAN SO TIEN THU DUOC
								if (taikhoanCO_SoHieu.trim().length() <= 0|| taikhoanNO_SoHieu.trim().length() <= 0) {
									msg = "2.Lỗi xác định tài khoản kế toán. Vui lòng kiểm tra lại thông tin dữ liệu nền trước khi chốt.";
									db.getConnection().rollback();
									return false;
								}

								String loaict = "";
								if (psktRs.getString("noidungtt_fk").equals("100000"))
									loaict = "Thu tiền theo hóa đơn";
								if (psktRs.getString("noidungtt_fk").equals("100001"))
									loaict = "Thu tiền KH trả trước";

								this.msg = util.Update_TaiKhoan_Vat_DienGiai( db, thang, nam, psktRs.getString("ngayghiso"), psktRs.getString("ngayghiso"), loaict, this.id, taikhoanNO_SoHieu, taikhoanCO_SoHieu, "", 
								Double.toString(tonggiatri), Double.toString(tonggiatri), doituong_NO, madoituong_NO, doituong_CO, madoituong_CO, "0", "", "", tiente_fk, "", "1", Double.toString(tonggiatri), Double.toString(tonggiatri), "Thu tiền - Thực thu", "0" , loaict , machungtu, isnpp);
							/*					
								
								this.msg = util.Update_TaiKhoan_DienGiai(db, thang, nam,	psktRs.getString("ngayghiso"),psktRs.getString("ngayghiso"), loaict,
										this.id, taikhoanNO_SoHieu,	taikhoanCO_SoHieu, "",	Double.toString(tonggiatri),Double.toString(tonggiatri), doituong_NO,
										madoituong_NO, doituong_CO, madoituong_CO,"0", "", "", tiente_fk, "", "1",Double.toString(tonggiatri),
										Double.toString(tonggiatri),"Thu tiền - Thực thu", loaict, machungtu);*/
								
								if (this.msg.trim().length() > 0) {
									psktRs.close();
									db.getConnection().rollback();
									return false;
								}
								
								if(tienchenhlech>0 && xoachenhlech == 1)
								{	
									if (taikhoanNO_CK_SoHieu.trim().length() <= 0 || taikhoanCO_SoHieu.trim().length() <= 0) {
										msg = "2.Lỗi xác định tài khoản kế toán. Vui lòng kiểm tra lại thông tin dữ liệu nền trước khi chốt.";
										db.getConnection().rollback();
										return false;
									}
									
									doituong_NO = "";
									madoituong_NO = "";

									doituong_CO = "Khách hàng";
									madoituong_CO = khachhang_fk;
									
									this.msg = util.Update_TaiKhoan_Vat_DienGiai( db, thang, nam, psktRs.getString("ngayghiso"), psktRs.getString("ngayghiso"), loaict, this.id, taikhoanNO_CK_SoHieu, taikhoanCO_SoHieu, "", 
									Double.toString(tienchenhlech), Double.toString(tienchenhlech), doituong_NO, madoituong_NO, doituong_CO, madoituong_CO, "0", "", "", tiente_fk, "", "1", Double.toString(tienchenhlech), Double.toString(tienchenhlech), "Thu tiền - Chiết khấu", "0" , loaict , machungtu, isnpp);
									
						/*			this.msg = util.Update_TaiKhoan_DienGiai(db, thang, nam, psktRs.getString("ngayghiso"),
													psktRs.getString("ngayghiso"), loaict, this.id,
													taikhoanNO_CK_SoHieu, taikhoanCO_SoHieu, "", Double.toString(tienchenhlech),
													Double.toString(tienchenhlech), doituong_NO, madoituong_NO, doituong_CO,
													madoituong_CO, "0", "", "", tiente_fk, "", "1", Double.toString(tienchenhlech),
													Double.toString(tienchenhlech), "Thu tiền - Chiết khấu", loaict, machungtu);*/
									if (this.msg.trim().length() > 0) {
										db.getConnection().rollback();
										return false;
									}
								}
								
								
								if(tienchenhlech<0 && xoachenhlech == 1)
								{
									doituong_NO = "Khách hàng";
									madoituong_NO = khachhang_fk;

									doituong_CO = "";
									madoituong_CO = "";
									
									tienchenhlech =  tienchenhlech *(-1);
									
									this.msg = util.Update_TaiKhoan_Vat_DienGiai( db, thang, nam, psktRs.getString("ngayghiso"), psktRs.getString("ngayghiso"), loaict, this.id, taikhoanCO_SoHieu, taikhoanCO_CK_SoHieu, "", 
									Double.toString(tienchenhlech), Double.toString(tienchenhlech), doituong_NO, madoituong_NO, doituong_CO, madoituong_CO, "0", "", "", tiente_fk, "", "1", Double.toString(tienchenhlech), Double.toString(tienchenhlech), "Thu tiền - Chiết khấu", "0" , loaict , machungtu, isnpp);
											
									
								/*	this.msg = util.Update_TaiKhoan_DienGiai(db, thang, nam, psktRs.getString("ngayghiso"),
													psktRs.getString("ngayghiso"), loaict, this.id,
													taikhoanCO_SoHieu, taikhoanCO_CK_SoHieu, "", Double.toString(tienchenhlech),
													Double.toString(tienchenhlech), doituong_NO, madoituong_NO, doituong_CO,
													madoituong_CO, "0", "", "", tiente_fk, "", "1", Double.toString(tienchenhlech),
													Double.toString(tienchenhlech), "Thu tiền - Chiết khấu", loaict, machungtu);*/
									if (this.msg.trim().length() > 0) {
										db.getConnection().rollback();
										return false;
									}
								}

								

								// GHI NHAN SO TIEN PHI NGAN HANG
								double phinganhang = Math.round(psktRs.getDouble("phinganhang"));
								if (phinganhang > 0) {
									taikhoanNO_SoHieu = taikhoanNo_PhiNganHang;

									if (taikhoanCO_SoHieu.trim().length() <= 0|| taikhoanNO_SoHieu.trim().length() <= 0) {
										msg = "2.Lỗi xác định tài khoản kế toán. Vui lòng kiểm tra lại thông tin dữ liệu nền trước khi chốt.";
										db.getConnection().rollback();
										return false;
									}

									loaict = "";
									if (psktRs.getString("noidungtt_fk").equals("100000"))
										loaict = "Thu tiền theo hóa đơn";
									if (psktRs.getString("noidungtt_fk").equals("100001"))
										loaict = "Thu tiền KH trả trước";

									doituong_NO = "Ngân hàng";
									madoituong_NO = nganhang_fk;

									doituong_CO = "Khách hàng";
									madoituong_CO = khachhang_fk;

									this.msg = util.Update_TaiKhoan_Vat_DienGiai( db, thang, nam, psktRs.getString("ngayghiso"), psktRs.getString("ngayghiso"), loaict, this.id, taikhoanNO_SoHieu, taikhoanCO_SoHieu, "", 
									Double.toString(phinganhang), Double.toString(phinganhang), doituong_NO, madoituong_NO, doituong_CO, madoituong_CO, "0", "", "", tiente_fk, "", "1", Double.toString(phinganhang), Double.toString(phinganhang), "Phí ngân hàng", "0" , loaict , machungtu, isnpp);
																						
									/*this.msg = util.Update_TaiKhoan_DienGiai(db, thang, nam,	psktRs.getString("ngayghiso"),	psktRs.getString("ngayghiso"), loaict,
											this.id, taikhoanNO_SoHieu,	taikhoanCO_SoHieu, "",Double.toString(phinganhang),	Double.toString(phinganhang),
											doituong_NO, madoituong_NO,	doituong_CO, madoituong_CO, "0", "","", tiente_fk, "", "1",	Double.toString(phinganhang),Double.toString(phinganhang),"Phí ngân hàng", loaict, machungtu);*/
									if (this.msg.trim().length() > 0) {
										psktRs.close();
										db.getConnection().rollback();
										return false;
									}
								}

								// GHI NHAN SO TIEN CHENH LECH
								double chenhlech = Math.round(psktRs.getDouble("chenhlech"));
								if (chenhlech != 0) {
									if (chenhlech > 0) {
										if (tiente_fk.equals("100000")) // VND
										{
											taikhoanNO_SoHieu = psktRs.getString("taikhoanNo_ChenhLechD_VND") == null ? "": psktRs.getString("taikhoanNo_ChenhLechD_VND");
											taikhoanCO_SoHieu = psktRs.getString("taikhoanCO_KH_SoHieu") == null ? "": psktRs.getString("taikhoanCO_KH_SoHieu");

											doituong_NO = "";
											madoituong_NO = "";

											doituong_CO = "Khách hàng";
											madoituong_CO = khachhang_fk;

										} else { // NGOAI TỆ

											taikhoanNO_SoHieu = psktRs.getString("taikhoanNo_ChenhLechD_NGOAITE")== null ? "": psktRs.getString("taikhoanNo_ChenhLechD_NGOAITE");
											taikhoanCO_SoHieu = psktRs.getString("taikhoanCO_KH_SoHieu") == null ? "": psktRs.getString("taikhoanCO_KH_SoHieu");

											doituong_NO = "";
											madoituong_NO = "";

											doituong_CO = "Khách hàng";
											madoituong_CO = khachhang_fk;
										}
									} else {
										chenhlech = chenhlech * (-1);
										if (tiente_fk.equals("100000")) // VND
										{
											taikhoanNO_SoHieu = psktRs.getString("taikhoanCO_KH_SoHieu") == null ? "": psktRs.getString("taikhoanCO_KH_SoHieu");
											taikhoanCO_SoHieu = psktRs.getString("taikhoanCo_ChenhLechA_VND") == null ? "": psktRs.getString("taikhoanCo_ChenhLechA_VND");

											doituong_CO = "";
											madoituong_CO = "";

											doituong_NO = "Khách hàng";
											madoituong_NO = khachhang_fk;

										} else { // NGOẠI TỆ
											taikhoanNO_SoHieu = psktRs.getString("taikhoanCO_KH_SoHieu") == null ? "": psktRs.getString("taikhoanCO_KH_SoHieu");
											taikhoanCO_SoHieu = psktRs.getString("taikhoanCo_ChenhLechA_NGOAITE") == null ? "": psktRs.getString("taikhoanCo_ChenhLechA_NGOAITE");

											doituong_CO = "";
											madoituong_CO = "";

											doituong_NO = "Khách hàng";
											madoituong_NO = khachhang_fk;
										}

									}

									chenhlech = Math.abs(chenhlech);
									if (taikhoanCO_SoHieu.trim().length() <= 0|| taikhoanNO_SoHieu.trim().length() <= 0) {
										msg = "2.Lỗi xác định tài khoản kế toán. Vui lòng kiểm tra lại thông tin dữ liệu nền trước khi chốt.";
										db.getConnection().rollback();
										return false;
									}

									loaict = "";
									if (psktRs.getString("noidungtt_fk").equals("100000"))
										loaict = "Thu tiền theo hóa đơn";
									if (psktRs.getString("noidungtt_fk").equals("100001"))
										loaict = "Thu tiền KH trả trước";

									this.msg = util.Update_TaiKhoan_Vat_DienGiai( db, thang, nam, psktRs.getString("ngayghiso"), psktRs.getString("ngayghiso"), loaict, this.id, taikhoanNO_SoHieu, taikhoanCO_SoHieu, "", 
									Double.toString(chenhlech), Double.toString(chenhlech), doituong_NO, madoituong_NO, doituong_CO, madoituong_CO, "0", "", "", tiente_fk, "", "1", Double.toString(chenhlech), Double.toString(chenhlech), "Thu tiền - Chênh lệch", "0" , loaict , machungtu, isnpp);
														
								/*	
									this.msg = util.Update_TaiKhoan_DienGiai(db, thang, nam,psktRs.getString("ngayghiso"),psktRs.getString("ngayghiso"), loaict,
											this.id, taikhoanNO_SoHieu,	taikhoanCO_SoHieu, "",Double.toString(chenhlech),Double.toString(chenhlech),
											doituong_NO, madoituong_NO,doituong_CO, madoituong_CO, "0", "","", tiente_fk, "", "1",
											Double.toString(chenhlech),Double.toString(chenhlech),"Thu tiền - Chênh lệch", loaict, machungtu);*/
									
									if (this.msg.trim().length() > 0) {
										psktRs.close();
										db.getConnection().rollback();
										return false;
									}
								}

								// GHI NHAN TIEN CHIET KHAU (Dung cho Thu tien ban
								// hang)
								double chietkhau = Math.round(psktRs.getDouble("chietkhau"));
								if (chietkhau > 0) {
									taikhoanNO_SoHieu = psktRs.getString("taikhoanNo_chietkhau")== null ? "": psktRs.getString("taikhoanNo_chietkhau");
									taikhoanCO_SoHieu = psktRs.getString("taikhoanCO_KH_SoHieu") == null ? "": psktRs.getString("taikhoanCO_KH_SoHieu");

									doituong_NO = "";
									madoituong_NO = "";

									doituong_CO = "Khách hàng";
									madoituong_CO = khachhang_fk;

									if (taikhoanCO_SoHieu.trim().length() <= 0
											|| taikhoanNO_SoHieu.trim().length() <= 0) {
										msg = "2.Lỗi xác định tài khoản kế toán. Vui lòng kiểm tra lại thông tin dữ liệu nền trước khi chốt.";
										db.getConnection().rollback();
										return false;
									}
									loaict = "Thu tiền theo hóa đơn";

									this.msg = util.Update_TaiKhoan_Vat_DienGiai( db, thang, nam, psktRs.getString("ngayghiso"), psktRs.getString("ngayghiso"), loaict, this.id, taikhoanNO_SoHieu, taikhoanCO_SoHieu, "", 
									Double.toString(chietkhau), Double.toString(chietkhau), doituong_NO, madoituong_NO, doituong_CO, madoituong_CO, "0", "", "", tiente_fk, "", "1", Double.toString(chietkhau), Double.toString(chietkhau), "Chiết khấu thanh toán", "0" , loaict , machungtu, isnpp);
													
									/*
									this.msg = util.Update_TaiKhoan_DienGiai(db, thang, nam,	psktRs.getString("ngayghiso"),
													psktRs.getString("ngayghiso"), loaict,this.id, taikhoanNO_SoHieu,
													taikhoanCO_SoHieu, "",Double.toString(chietkhau),Double.toString(chietkhau),
													doituong_NO, madoituong_NO,	doituong_CO, madoituong_CO, "0", "","", tiente_fk, "", "1",
													Double.toString(chietkhau),Double.toString(chietkhau),
													"Chiết khấu thanh toán", loaict, machungtu);*/
									if (this.msg.trim().length() > 0) {
										psktRs.close();
										db.getConnection().rollback();
										return false;
									}
								}
								
								
								if(noidungthutien.equals("100001")&&ISThuhoCN.equals("1"))// THU HỘ CHI NHÁNH
								{
									String sql = " SELECT (SELECT PK_SEQ FROM ERP_TAIKHOANKT WHERE NPP_FK = tt.NPPCN_FK AND SOHIEUTAIKHOAN = '33610000') TK_336, \n" +
												 " (SELECT PK_SEQ FROM ERP_TAIKHOANKT WHERE NPP_FK = tt.NPPCN_FK AND SOHIEUTAIKHOAN = '13111000') TK_131, \n"+
												 " tt.KHACHHANG_FK \n"+
												 " FROM ERP_THUTIEN tt WHERE PK_SEQ = "+this.id;
						
									ResultSet Rs_thuho = db.get(sql);
									
									if(Rs_thuho!=null)
									{
										while(Rs_thuho.next())
										{
											doituong_NO = "Khách hàng";
											madoituong_NO = "1";
			
											doituong_CO = "Khách hàng";
											madoituong_CO = Rs_thuho.getString("KHACHHANG_FK"); 
											
											taikhoanNO_SoHieu = Rs_thuho.getString("TK_336")== null ? "": Rs_thuho.getString("TK_336");
											taikhoanCO_SoHieu = Rs_thuho.getString("TK_131")== null ? "": Rs_thuho.getString("TK_131");
			
											if (taikhoanCO_SoHieu.trim().length() <= 0	|| taikhoanNO_SoHieu.trim().length() <= 0) {
												msg = "2.Lỗi xác định tài khoản kế toán. Vui lòng kiểm tra lại thông tin dữ liệu nền trước khi chốt.11";
												db.getConnection().rollback();
												return false;
											}
											loaict = "Thu tiền KH trả trước";
											
											this.msg = util.Update_TaiKhoan_Vat_DienGiai( db, thang, nam, psktRs.getString("ngayghiso"), psktRs.getString("ngayghiso"), loaict, this.id, taikhoanNO_SoHieu, taikhoanCO_SoHieu, "", 
											Double.toString(tonggiatri), Double.toString(tonggiatri), doituong_NO, madoituong_NO, doituong_CO, madoituong_CO, "0", "", "", tiente_fk, "", "1", Double.toString(tonggiatri), Double.toString(tonggiatri), "Thu tiền - Thực thu", "0" , loaict , machungtu, isnpp);
																	
											if (this.msg.trim().length() > 0) {
												psktRs.close();
												db.getConnection().rollback();
												return false;
											}
											
										}
										Rs_thuho.close();
									}
						
								}
								

							} else if (noidungthutien.equals("100002"))// THU KHÁC
							{
								query = "SELECT tt.NGAYGHISO, dkc.TAIKHOAN_FK as TAIKHOAN_CO, dkc.SOTIEN as SOTIEN, ISNULL(tt.PHINGANHANG,0) as PHINGANHANG, \n"+
										"       CASE WHEN tt.HTTT_FK = 100000 THEN (select PK_SEQ from ERP_TAIKHOANKT where SOHIEUTAIKHOAN = '11110000' AND CONGTY_FK ="+this.ctyId+") \n"+
										"            ELSE (select a.TAIKHOAN_FK from ERP_NGANHANG_CONGTY a where a.SoTaiKhoan = tt.SoTaiKhoan AND CONGTY_FK = "+this.ctyId+") \n"+
										"       END AS TAIKHOAN_NO, ( select pk_seq from ERP_TAIKHOANKT where SOHIEUTAIKHOAN = '64253000' AND CONGTY_FK = "+this.ctyId +
										") as TAIKHOAN_COPHINH ,\n"+
										"       CASE WHEN tt.HTTT_FK = 100000 THEN '' ELSE N'NGÂN HÀNG' END AS DOITUONGNO, \n"+
										"       CASE WHEN tt.HTTT_FK = 100000 THEN '0' ELSE NH.PK_SEQ END AS DOITUONGNOID, \n"+
										"       CASE WHEN dkc.DOITUONGDINHKHOAN = 1 THEN N'SẢN PHẨM' \n"+
										"            WHEN dkc.DOITUONGDINHKHOAN = 2 THEN N'NGÂN HÀNG'  \n"+
										"            WHEN dkc.DOITUONGDINHKHOAN = 3 THEN N'NHÀ CUNG CẤP'  \n"+
										"            WHEN dkc.DOITUONGDINHKHOAN = 4 THEN N'TÀI SẢN'  \n"+
										"            WHEN dkc.DOITUONGDINHKHOAN = 5 THEN N'KHÁCH HÀNG'  \n"+
										"            WHEN dkc.DOITUONGDINHKHOAN = 6 THEN N'NHÂN VIÊN'  \n"+
										"            ELSE '' \n"+
										"       END AS DOITUONGCO, \n"+
										"       CASE	WHEN dkc.DOITUONGDINHKHOAN = 1 THEN (select pk_seq from SANPHAM where pk_seq = dkc.DOITUONG_FK) \n"+
										"            	WHEN dkc.DOITUONGDINHKHOAN = 2 THEN (select pk_seq from ERP_NGANHANG where pk_seq = dkc.DOITUONG_FK)  \n"+
										"            	WHEN dkc.DOITUONGDINHKHOAN = 3 THEN (select pk_seq from ERP_NHACUNGCAP where pk_seq = dkc.DOITUONG_FK)  \n"+
										"            	WHEN dkc.DOITUONGDINHKHOAN = 4 THEN (select pk_seq from ERP_TAISANCODINH where pk_seq = dkc.DOITUONG_FK)  \n"+
										"  			 	WHEN dkc.DOITUONGDINHKHOAN = 5 AND dkc.isNPP = 0 THEN (select pk_seq from KHACHHANG where pk_seq = dkc.DOITUONG_FK) \n"+
							            "			 	WHEN dkc.DOITUONGDINHKHOAN = 5 AND dkc.isNPP = 1 THEN (select pk_seq from NHAPHANPHOI where pk_seq = dkc.DOITUONG_FK) \n"+
							            "				WHEN dkc.DOITUONGDINHKHOAN = 5 AND dkc.isNPP = 2 THEN (select pk_seq from ERP_NHANVIEN where pk_seq = dkc.DOITUONG_FK) \n"+
										"             WHEN dkc.DOITUONGDINHKHOAN = 6 THEN (select pk_seq from ERP_NHANVIEN where pk_seq = dkc.DOITUONG_FK)  \n"+
										"            ELSE '0' \n"+
										"       END AS DOITUONGCOID, isnull(tt.machungtu,'') machungtu, isnull(dkc.isNPP, 0) isNPP \n"+
										"FROM ERP_THUTIEN_DINHKHOANCO dkc INNER JOIN ERP_THUTIEN tt ON dkc.THUTIEN_FK = tt.PK_SEQ \n"+
										"     LEFT JOIN ERP_NGANHANG NH ON tt.NGANHANG_FK = NH.PK_SEQ \n"+
										"WHERE tt.PK_SEQ = " + this.id + " ";

								System.out.println("Câu query " + query);
								ResultSet rsDKC = db.get(query);

								int i = 0;
								if (rsDKC != null) {
									while (rsDKC.next()) {
										String taikhoanNO = rsDKC.getString("TAIKHOAN_NO") == null ? "": rsDKC.getString("TAIKHOAN_NO");
										String taikhoanCO = rsDKC.getString("TAIKHOAN_CO") == null ? "": rsDKC.getString("TAIKHOAN_CO");

										String loaidt_co = rsDKC.getString("DOITUONGCO");
										String doituongco = rsDKC.getString("DOITUONGCOID") == null ? "": rsDKC.getString("DOITUONGCOID");
										String loaidt_no = rsDKC.getString("DOITUONGNO");
										String doituongno = rsDKC.getString("DOITUONGNOID") == null ? "": rsDKC.getString("DOITUONGNOID");
										machungtu = psktRs.getString("MACHUNGTU");
										isnpp = psktRs.getString("ISNPP");

										if (doituongco.trim().length() <= 1)
											doituongco = "";
										if (doituongno.trim().length() <= 1)
											doituongno = "";

										double sotienco = Math.round(rsDKC.getDouble("SOTIEN"));
										double phinganhang = Math.round(rsDKC.getDouble("phinganhang"));

										// GHI NHAN SO TIEN CO
										if (sotienco > 0) {
											if (taikhoanCO.trim().length() <= 0	|| taikhoanNO.trim().length() <= 0) {
												msg = "Lỗi xác định tài khoản kế toán. Vui lòng kiểm tra lại thông tin dữ liệu nền trước khi chốt.";
												db.getConnection().rollback();
												return false;
											}

											String loaict = "Thu khác";

											this.msg = util.Update_TaiKhoan_Vat_DienGiai( db, thang, nam, rsDKC.getString("ngayghiso"), rsDKC.getString("ngayghiso"), loaict, this.id, taikhoanNO, taikhoanCO, "", 
											Double.toString(sotienco), Double.toString(sotienco), loaidt_no, doituongno, loaidt_co, doituongco, "0", "", "", tiente_fk, "", "1", Double.toString(sotienco), Double.toString(sotienco), "Thu tiền", "0" , loaict , machungtu, isnpp);
																							
											/*this.msg = 	util.Update_TaiKhoan_DienGiai(db,thang, nam,	rsDKC.getString("ngayghiso"),rsDKC.getString("ngayghiso"),loaict, this.id, taikhoanNO,
														taikhoanCO, "",	Double.toString(sotienco),	Double.toString(sotienco),loaidt_no, doituongno,loaidt_co, doituongco, "0", "",
														"", tiente_fk, "", "1",Double.toString(sotienco),Double.toString(sotienco),"Thu tiền", loaict, machungtu);*/
											
											if (this.msg.trim().length() > 0) {
												rsDKC.close();
												db.getConnection().rollback();
												return false;
											}
										}

										// GHI NHAN SO TIEN PHI NGAN HANG CHO LẦN ĐẦU TIÊN
										if (phinganhang > 0 && i == 0) {
											taikhoanCO = rsDKC.getString("TAIKHOAN_COPHINH") == null ? "": rsDKC.getString("TAIKHOAN_COPHINH");

											if (taikhoanCO.trim().length() <= 0 || taikhoanNO.trim().length() <= 0) {
												msg = "Lỗi xác định tài khoản kế toán. Vui lòng kiểm tra lại thông tin dữ liệu nền trước khi chốt.";
												db.getConnection().rollback();
												return false;
											}

											String loaict = "Thu khác";

											this.msg = util.Update_TaiKhoan_Vat_DienGiai( db, thang, nam, rsDKC.getString("ngayghiso"), rsDKC.getString("ngayghiso"), loaict, this.id, taikhoanNO, taikhoanCO, "", 
											Double.toString(phinganhang), Double.toString(phinganhang), loaidt_no, doituongno, "", "", "0", "", "", tiente_fk, "", "1", Double.toString(phinganhang), Double.toString(phinganhang), "Thu tiền - Phí ngân hàng", "0" , loaict , machungtu, isnpp);
														
											/*
											this.msg = util.Update_TaiKhoan_DienGiai(db,thang, nam,	rsDKC.getString("ngayghiso"),rsDKC.getString("ngayghiso"),
											loaict, this.id, taikhoanNO,taikhoanCO, "",	Double.toString(phinganhang),Double.toString(phinganhang),
											loaidt_no, doituongno, "", "",	"0", "", "", tiente_fk, "",	"1",Double.toString(phinganhang),
											Double.toString(phinganhang),"Thu tiền - Phí ngân hàng", loaict, machungtu);*/

											if (this.msg.trim().length() > 0) {
												rsDKC.close();
												db.getConnection().rollback();
												return false;
											}
											
											i++;
										}

									}
									rsDKC.close();
								}

							} else if (noidungthutien.equals("100003"))// THU HOI	// TAM UNG
							{
								String loaidt = psktRs.getString("DOITUONG_TU");

								String ncc_nv = psktRs.getString("NCC_NV_FK");

								if (hinhthuctt.equals("100001")) // thanh toan// NGANHANG	// (CHUYEN	// KHOAN)
								{
									taikhoanNO_SoHieu = psktRs.getString("taikhoanNO_SoHieu") == null ? "": psktRs.getString("taikhoanNO_SoHieu");

									doituong_NO = "Ngân hàng";
									madoituong_NO = nganhang_fk;

									doituong_CO = loaidt;
									madoituong_CO = ncc_nv;

								} else {
									taikhoanNO_SoHieu = psktRs.getString("taikhoanNo_thuhoi")== null ? "": psktRs.getString("taikhoanNo_thuhoi");

									doituong_NO = "";
									madoituong_NO = "";

									doituong_CO = loaidt;
									madoituong_CO = ncc_nv;
								}

								taikhoanCO_SoHieu = psktRs.getString("taikhoanCO_NCC_NV_SoHieu")== null ? "": psktRs.getString("taikhoanNo_thuhoi");

								// GHI NHAN SO TIEN THU DUOC
								if (tonggiatri > 0) {
									if (taikhoanCO_SoHieu.trim().length() <= 0 || taikhoanNO_SoHieu.trim().length() <= 0) 
									{
										msg = "2.Lỗi xác định tài khoản kế toán. Vui lòng kiểm tra lại thông tin dữ liệu nền trước khi chốt.";
										db.getConnection().rollback();
										return false;
									}

									this.msg = util.Update_TaiKhoan_Vat_DienGiai( db, thang, nam, psktRs.getString("ngayghiso"), psktRs.getString("ngayghiso"), "Thu tiền_Thu hồi tạm ứng", this.id, taikhoanNO_SoHieu, taikhoanCO_SoHieu, "", 
									Double.toString(tonggiatri), Double.toString(tonggiatri), doituong_NO, madoituong_NO, doituong_CO, madoituong_CO, "0", "", "", tiente_fk, "", "1", Double.toString(tonggiatri), Double.toString(tonggiatri), "Thu tiền - Thực thu", "0" , "Thu tiền_Thu hồi tạm ứng" , machungtu, isnpp);
											
									
									/*this.msg = util.Update_TaiKhoan_DienGiai(db, thang, nam,psktRs.getString("ngayghiso"),psktRs.getString("ngayghiso"),	"Thu tiền_Thu hồi tạm ứng", this.id,
											taikhoanNO_SoHieu, taikhoanCO_SoHieu,"", Double.toString(tonggiatri),Double.toString(tonggiatri),doituong_NO, madoituong_NO,
											doituong_CO, madoituong_CO, "0", "","", tiente_fk, "", "1",	Double.toString(tonggiatri),Double.toString(tonggiatri),"Thu tiền - Thực thu", "Thu tiền_Thu hồi tạm ứng", machungtu);*/
									if (this.msg.trim().length() > 0) {
										psktRs.close();
										db.getConnection().rollback();
										return false;
									}
								}
								// GHI NHAN SO TIEN PHI NGAN HANG
								double phinganhang = Math.round(psktRs.getDouble("phinganhang"));

								if (phinganhang > 0) {
									taikhoanNO_SoHieu = psktRs.getString("taikhoanNo_PhiNganHang") == null ? "": psktRs.getString("taikhoanNo_PhiNganHang");

									doituong_NO = "";
									madoituong_NO = "";

									doituong_CO = loaidt;
									madoituong_CO = ncc_nv;

									if (taikhoanCO_SoHieu.trim().length() <= 0 || taikhoanNO_SoHieu.trim().length() <= 0) {
										msg = "2.Lỗi xác định tài khoản kế toán. Vui lòng kiểm tra lại thông tin dữ liệu nền trước khi chốt.";
										db.getConnection().rollback();
										return false;
									}

									this.msg = util.Update_TaiKhoan_Vat_DienGiai( db, thang, nam, psktRs.getString("ngayghiso"), psktRs.getString("ngayghiso"), "Thu tiền_Thu hồi tạm ứng", this.id, taikhoanNO_SoHieu, taikhoanCO_SoHieu, "", 
									Double.toString(phinganhang), Double.toString(phinganhang), doituong_NO, madoituong_NO, doituong_CO, madoituong_CO, "0", "", "", tiente_fk, "", "1", Double.toString(phinganhang), Double.toString(phinganhang), "Phí ngân hàng", "0" , "Thu tiền hoàn ứng" , machungtu, isnpp);
																					
								/*	this.msg = util.Update_TaiKhoan_DienGiai(db, thang, nam, psktRs.getString("ngayghiso"),psktRs.getString("ngayghiso"),
											"Thu tiền_Thu hồi tạm ứng", this.id,taikhoanNO_SoHieu, taikhoanCO_SoHieu,
											"", Double.toString(phinganhang),Double.toString(phinganhang),doituong_NO, madoituong_NO,
											doituong_CO, madoituong_CO, "0", "","", tiente_fk, "", "1",	Double.toString(phinganhang),
											Double.toString(phinganhang),"Phí ngân hàng", "Thu tiền hoàn ứng", machungtu);*/
									if (this.msg.trim().length() > 0) {
										psktRs.close();
										db.getConnection().rollback();
										return false;
									}
								}

								// GHI NHAN SO TIEN CHENH LECH
								double chenhlech = Math.round(psktRs.getDouble("chenhlech"));
								if (chenhlech != 0) {
									if (chenhlech > 0) {
										if (tiente_fk.equals("100000")) // VND
										{
											/* taikhoanNO_SoHieu = "81180000"; */
											taikhoanNO_SoHieu = psktRs.getString("taikhoanNo_ChenhLechD_VND")== null ? "": psktRs.getString("taikhoanNo_ChenhLechD_VND");
											taikhoanCO_SoHieu = psktRs.getString("taikhoanCO_NCC_NV_SoHieu") == null ? "": psktRs.getString("taikhoanCO_NCC_NV_SoHieu");

											doituong_NO = "";
											madoituong_NO = "";

											doituong_CO = loaidt;
											madoituong_CO = ncc_nv;

										} else { // NGOẠI TỆ

											taikhoanNO_SoHieu = psktRs.getString("taikhoanNo_ChenhLechD_VND")== null ? "": psktRs.getString("taikhoanNo_ChenhLechD_VND");
											taikhoanCO_SoHieu = psktRs.getString("taikhoanCO_NCC_NV_SoHieu") == null ? "": psktRs.getString("taikhoanCO_NCC_NV_SoHieu");

											doituong_NO = "";
											madoituong_NO = "";

											doituong_CO = loaidt;
											madoituong_CO = ncc_nv;

										}
									} else {
										chenhlech = chenhlech * (-1);
										if (tiente_fk.equals("100000")) // VND
										{
											taikhoanNO_SoHieu = psktRs.getString("taikhoanCO_NCC_NV_SoHieu") == null ? "": psktRs.getString("taikhoanCO_NCC_NV_SoHieu");
											taikhoanCO_SoHieu = psktRs.getString("taikhoanCo_ChenhLechA_VND")== null ? "": psktRs.getString("taikhoanCo_ChenhLechA_VND");
											/* taikhoanCO_SoHieu = "71180000"; */

											doituong_CO = "";
											madoituong_CO = "";

											doituong_NO = loaidt;
											madoituong_NO = ncc_nv;

										} else {

											taikhoanNO_SoHieu = psktRs.getString("taikhoanCO_NCC_NV_SoHieu") == null ? "": psktRs.getString("taikhoanCO_NCC_NV_SoHieu");
											taikhoanCO_SoHieu = psktRs.getString("taikhoanCo_ChenhLechA_NGOAITE")== null ? "": psktRs.getString("taikhoanCo_ChenhLechA_NGOAITE");
											/* taikhoanCO_SoHieu = "51580000"; */

											doituong_CO = "";
											madoituong_CO = "";

											doituong_NO = loaidt;
											madoituong_NO = ncc_nv;

										}

									}

									chenhlech = Math.abs(chenhlech);
									if (taikhoanCO_SoHieu.trim().length() <= 0	|| taikhoanNO_SoHieu.trim().length() <= 0) {
										msg = "2.Lỗi xác định tài khoản kế toán. Vui lòng kiểm tra lại thông tin dữ liệu nền trước khi chốt.";
										db.getConnection().rollback();
										return false;
									}

									this.msg = util.Update_TaiKhoan_Vat_DienGiai( db, thang, nam, psktRs.getString("ngayghiso"), psktRs.getString("ngayghiso"), "Thu tiền_Thu hồi tạm ứng", this.id, taikhoanNO_SoHieu, taikhoanCO_SoHieu, "", 
											Double.toString(chenhlech), Double.toString(chenhlech), doituong_NO, madoituong_NO, doituong_CO, madoituong_CO, "0", "", "", tiente_fk, "", "1", Double.toString(chenhlech), Double.toString(chenhlech), "Thu tiền - Chênh lệch", "0" , "Thu tiền hoàn ứng" , machungtu, isnpp);
																						
									/*this.msg = util.Update_TaiKhoan_DienGiai(db, thang, nam,	psktRs.getString("ngayghiso"),
												psktRs.getString("ngayghiso"),"Thu tiền_Thu hồi tạm ứng", this.id,taikhoanNO_SoHieu, taikhoanCO_SoHieu,
												"", Double.toString(chenhlech),	Double.toString(chenhlech),	doituong_NO, madoituong_NO,
												doituong_CO, madoituong_CO, "0", "","", tiente_fk, "", "1",	Double.toString(chenhlech),
												Double.toString(chenhlech),	"Thu tiền - Chênh lệch","Thu tiền hoàn ứng", machungtu );*/
									if (this.msg.trim().length() > 0) {
										psktRs.close();
										db.getConnection().rollback();
										return false;
									}
								}

							}
						}
						psktRs.close();
					}
									
					
				}


			// NẾU NỘI DUNG THU TIỀN LÀ KH TRẢ TRƯỚC CHO CHI NHÁNH THÌ TỰ ĐỘNG TẠO PHIẾU NỘP TIỀN XUỐNG DƯỚI CHI NHÁNH TƯƠNG ỨNG
				
				if(noidungthutien.equals("100001")&&NPPCN_FK.trim().length()>0)
				{
					if(isnpp.equals("0")) // KHÁCH HÀNG
					{
						query =" INSERT TRAPHACODMS.dbo.NOPTIEN ( KHACHHANG_FK, SOTIEN, TRANGTHAI, NGUOITAO, NGUOISUA, NGAYTAO, NGAYSUA, NPP_FK, NGAYNOP , THUTIENHO_FK ) "+
							   " VALUES ("+khachhang_fk+", "+tonggiatri+", 1, 100002 , 100002, '"+getDateTime()+"', '"+getDateTime()+"', "+NPPCN_FK+", '"+getDateTime()+"', "+this.id+" )";
						
						if (!db.update(query)) {
							this.msg = "Không thể tạo mới phiếu thu tiền dưới chi nhánh. Vui lòng liên hệ admin để được xử lý! ";
							System.out.println("[ErpThutien.createTTHD] error message:" + this.msg);
							db.getConnection().rollback();
							return false;
						}
						
					}
					else // NHÀ PHÂN PHỐI
					{
						query = " INSERT TRAPHACODMS.dbo.NOPTIEN ( NPP_DAT_FK, SOTIEN, TRANGTHAI, NGUOITAO, NGUOISUA, NGAYTAO, NGAYSUA, NPP_FK, NGAYNOP , THUTIENHO_FK ) "+
						   		" VALUES ("+khachhang_fk+", "+tonggiatri+", 1, 100002, 100002, '"+getDateTime()+"', '"+getDateTime()+"', "+NPPCN_FK+", '"+getDateTime()+"', "+this.id+" )";
					
						if (!db.update(query)) {
							this.msg = "Không thể tạo mới phiếu thu tiền dưới chi nhánh. Vui lòng liên hệ admin để được xử lý! ";
							System.out.println("[ErpThutien.createTTHD] error message:" + this.msg);
							db.getConnection().rollback();
							return false;
						}
					}
				}
					
			db.getConnection().commit();
			db.getConnection().setAutoCommit(true);
		} catch (java.sql.SQLException e) {
			try {
				db.getConnection().rollback();
				this.msg = "Lỗi khi chốt thu tiền: " + e.getMessage();
			} catch (SQLException e1) {
			}
			return false;
		}

		return true;
	}
	NumberFormat formatter = new DecimalFormat("#,###,###");
	public void init() {
		
		String query = " 	select 	tt.pk_seq, tt.ngaychungtu, tt.ngayghiso, tt.trangthai,  tt.khachhang_fk as khachhang_fk, \n"+
					   " 		tt.httt_fk, tt.nganhang_fk, tt.chinhanh_fk, tk.SOHIEUTAIKHOAN +' -- '+ tk.TENTAIKHOAN as dinhkhoanco, tt.doituongdinhkhoan, tt.madoituongdinhkhoan, \n"+
					   " 		tt.sotaikhoan, tt.noidungtt_fk, isnull(tt.ghichu, '') as ghichu, ISNULL(tt.sotientt, 0) AS SOTIENTT, tt.tiente_fk, \n"+
					   " 		isnull(tt.sochungtu,'') as sochungtu, tt.NCC_FK, tt.NHANVIEN_FK , \n"+
					   " 		isnull(tt.thuduoc, 0) as thuduoc, isnull(tt.phinganhang, 0) as phinganhang, isnull(tt.chenhlech, 0) as chenhlech, \n"+
					   " 		ISNULL(tt.THUDUOCNT, 0) AS THUDUOCNT, ISNULL(tt.PHINGANHANGNT, 0) AS PHINGANHANGNT, \n"+
					   " 		ISNULL(tt.SOTIENTTNT, 0) AS SOTIENTTNT, ISNULL(tt.TIGIA, 0) AS TIGIA, tt.KBH_FK, \n"+
					   " 		isnull(tt.chietkhau,0) as chietkhau,isnull(tt.chietkhauNT,0) as chietkhauNT, ISNULL(NGUOINOPTIEN,'') as NGUOINOPTIEN, ISNULL(DIACHI,'') as DIACHI , \n"+
					   " 		xoakhtratruoc_fk , NHOMKHTT_FK, isnull(tt.ctkemtheo,'') chungtukemtheo, tt.BANGKE_FK , isnull(tt.isNPP,0) isNPP, isnull(tt.NPPCN_FK, 0) NPPCN_FK , isnull(tt.ISThuhoCN, 0) ISThuhoCN  \n"+
					   " from 	ERP_THUTIEN tt \n"+
					   " left join ERP_TAIKHOANKT tk on tk.SOHIEUTAIKHOAN = tt.dinhkhoanco \n"+
					   " where tt.pk_seq = '" + this.id + "'";

		System.out.println("115.Khoi tao thu tien: " + query);
		ResultSet rs = db.get(query);
		if (rs != null) {
			try {
				while (rs.next()) {
					this.nhomkhttId = rs.getString("NHOMKHTT_FK") == null ? "" : rs.getString("NHOMKHTT_FK");
					this.ngaychungtu = rs.getString("ngaychungtu");
					this.ngayghiso = rs.getString("ngayghiso");
					this.nppId = rs.getString("khachhang_fk") == null ? "" : rs.getString("khachhang_fk");
					this.htttId = rs.getString("httt_fk");
					this.ndId = rs.getString("noidungtt_fk");
					this.isthuhoCN = rs.getString("isthuhoCN");

					this.isNPP = rs.getString("isNPP");

					if(this.nppId.trim().length()>0)
						this.nppIdgoc = this.nppId  + " -- "+this.isNPP;
					
					this.nppChinhanhId = rs.getString("NPPCN_FK") == null ? "" : rs.getString("NPPCN_FK");
					
					if(this.nppChinhanhId.trim().length()>0)
						this.isChuyenCN = "1";
					
					this.nguoinoptien = rs.getString("nguoinoptien");
					this.diachi = rs.getString("diachi");

					this.kbhId = rs.getString("KBH_FK") == null ? "" : rs.getString("KBH_FK");

					if (this.nhomkhttId.trim().length() > 0)
						this.nhomkhtt = "1";

					if (this.htttId.equals("100001")) {
						this.nganhangId = rs.getString("nganhang_fk") == null?"":rs.getString("nganhang_fk");
						this.chinhanhId = rs.getString("chinhanh_fk")== null?"":rs.getString("chinhanh_fk");
						this.sotaikhoan = rs.getString("sotaikhoan")== null?"":rs.getString("sotaikhoan");
					}
					this.noidungtt = rs.getString("ghichu");
					this.sotientt =formatter.format( rs.getDouble("sotientt"));
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

					this.xoakhtratruocId = rs.getString("xoakhtratruoc_fk") == null ? "": rs.getString("xoakhtratruoc_fk");

					this.nccId = rs.getString("NCC_FK") == null ? "" : rs.getString("NCC_FK");
					this.nvtuId = rs.getString("NHANVIEN_FK") == null ? "" : rs.getString("NHANVIEN_FK");
					this.bangkeId = rs.getString("BANGKE_FK") == null ? "" : rs.getString("BANGKE_FK");

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
				System.out.println("115.Exception: " + e.getMessage());
			}
		}

		this.createRs();

		if (this.ndId.equals("100002")) // Thu khác
		{
			createDoiTuongDinhKhoanCo();
		}
	}

	private void createDoiTuongDinhKhoanCo() {

		if (this.id.trim().length() > 0 && this.ndId.equals("100002")) {
			String command = "SELECT distinct ttct.TAIKHOAN_FK, ttct.DOITUONGDINHKHOAN, ttct.DOITUONG_FK, ttct.SOTIEN, ttct.ISNPP  "+ 
							 "FROM ERP_THUTIEN_DINHKHOANCO ttct " + "WHERE ttct.THUTIEN_FK = " + this.id + " ";

			System.out.println("Câu lấy dkc " + command);
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
//						String is_NPP = rsDKC.getString("isNPP");

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

		this.getNppInfo();

		String query =" SELECT tt.pk_seq, tt.ngaychungtu, tt.ngayghiso, tt.trangthai, tt.khachhang_fk as khachhang_fk, \n"+
					  " tt.httt_fk, tt.nganhang_fk, tt.chinhanh_fk, tk.SOHIEUTAIKHOAN +' -- '+ tk.TENTAIKHOAN as dinhkhoanco, tt.doituongdinhkhoan, tt.madoituongdinhkhoan, \n"+
					  " tt.sotaikhoan, tt.noidungtt_fk, isnull(tt.ghichu, '') as ghichu, ISNULL(tt.sotientt, 0) AS SOTIENTT, tt.tiente_fk, \n"+
					  " isnull(tt.sochungtu,'') as sochungtu, tt.NCC_FK, tt.NHANVIEN_FK , \n"+
					  " isnull(tt.thuduoc, 0) as thuduoc, isnull(tt.phinganhang, 0) as phinganhang, isnull(tt.chenhlech, 0) as chenhlech, \n"+
					  " ISNULL(tt.THUDUOCNT, 0) AS THUDUOCNT, ISNULL(tt.PHINGANHANGNT, 0) AS PHINGANHANGNT, \n"+
					  " ISNULL(tt.SOTIENTTNT, 0) AS SOTIENTTNT, ISNULL(tt.TIGIA, 0) AS TIGIA, tt.KBH_FK, \n"+
					  " isnull(tt.chietkhau,0) as chietkhau,isnull(tt.chietkhauNT,0) as chietkhauNT, ISNULL(NGUOINOPTIEN,'') as NGUOINOPTIEN, ISNULL(DIACHI,'') as DIACHI , \n"+
					  " xoakhtratruoc_fk , NHOMKHTT_FK, isnull(tt.ctkemtheo,'') chungtukemtheo, doituong, nhomkenh_fk, tt.BANGKE_FK, isnull(tt.NPPCN_FK, 0) NPPCN_FK , ISNULL(TT.isNPP, 0) isNPP , isnull(tt.ISThuhoCN, 0) ISThuhoCN \n"+
					  " FROM ERP_THUTIEN tt \n" + 
					  " left join ERP_TAIKHOANKT tk on tk.SOHIEUTAIKHOAN = tt.dinhkhoanco \n"+
					  " WHERE tt.pk_seq = " + this.id + " and tt.congty_fk = " + this.ctyId;

		System.out.println("115.Khoi tao thu tien: " + query);
		ResultSet rs = db.get(query);
		if (rs != null) {
			try {
				while (rs.next()) {
					this.nhomkhttId = rs.getString("NHOMKHTT_FK") == null ? "" : rs.getString("NHOMKHTT_FK");
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
		query = "select hoadon_fk from erp_thutien_hoadon a inner join ERP_HOADON b on a.hoadon_fk = b.pk_seq where thutien_fk = '"
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

	private void getNppInfo() {
		// Phien ban moi
		geso.traphaco.distributor.util.Utility util = new geso.traphaco.distributor.util.Utility();
		this.nppdangnhap = util.getIdNhapp(this.userId);
	}
	
	public void createRs() {
		// Kênh bán hàng

		System.out.println("vaoday");

		if (this.ngaychungtu.length() > 0&this.ngayghiso.length() <=0)
			this.ngayghiso = this.ngaychungtu;

		String command = "";
		if (this.nppId.trim().length() > 0) {
			command = "select PK_SEQ, TEN + ', ' + DIENGIAI as TEN  \n"+
					  "from KENHBANHANG \n"+
					  "where TRANGTHAI = 1  ";
			this.kbhRs = db.get(command);
		}
		
		if(this.ndId.equals("100002")) // THU KHÁC
		{
			this.taikhoanRs = db.getScrol("Select pk_seq, sohieutaikhoan + ' - ' + TENTAIKHOAN as sohieutk from ERP_TAIKHOANKT where trangthai = 1 and CONGTY_FK = "+this.ctyId+" ");
			this.SanphamList = db.getScrol("select PK_SEQ, MA , TEN from SANPHAM where TRANGTHAI = 1 ");
			this.NganhangList = db.getScrol("select PK_SEQ, MA , TEN from ERP_NGANHANG where TRANGTHAI = 1 ");
			this.NccList = db.getScrol("select PK_SEQ, MA , TEN from ERP_NHACUNGCAP where TRANGTHAI = 1 ");
			this.TaisanList = db.getScrol("select PK_SEQ, MA , TEN from ERP_TAISANCODINH where TRANGTHAI = 1 ");
			this.KhachhangList = db.getScrol("select PK_SEQ, MA , TEN from NHAPHANPHOI where TRANGTHAI = 1 AND PK_SEQ != 1 ");
			this.NhanvienList = db.getScrol("select PK_SEQ, MA , TEN from ERP_NHANVIEN where TRANGTHAI = 1 ");			
			this.nvtuRs = db.get(" select pk_seq, ma, ten from erp_nhanvien where trangthai=1 ");
		}
		
		if(this.ndId.equals("100001")) // THU TIỀN KH TRẢ TRƯỚC
		{
			if(this.isChuyenCN.equals("0"))// KHÔNG CHUYỂN XUỐNG CHI NHÁNH
				this.nppRs = db.getScrol(" select CONVERT(VARCHAR, PK_SEQ) + ' -- ' + '1' AS PK_SEQ , MaFAST + ' - ' + TEN  as nppTen from NHAPHANPHOI where trangthai = '1' and PK_SEQ!=1 "); // LẤY NHÀ PHÂN PHỐI KHÁC != 1 VÌ 1 CHÍNH LÀ HO
			else
			{
				this.chinhanhRs = db.get(" select pk_seq, Ma + ' - ' + TEN  as nppTen from NHAPHANPHOI where trangthai = '1' and PK_SEQ!=1 ");
								
				if(this.nppChinhanhId.trim().length() > 0)
				{
					String 	KH_CN = " SELECT CONVERT(VARCHAR, PK_SEQ) + ' -- ' + '1'  AS PK_SEQ, Ma + ' - ' + TEN nppTen FROM NHAPHANPHOI WHERE TRANGTHAI = 1 AND PK_SEQ != 1 \n";
							KH_CN +=" AND PK_SEQ != "+this.nppChinhanhId;
					
							KH_CN += " UNION ALL \n"+
									 " SELECT CONVERT(VARCHAR, PK_SEQ) + ' -- ' + '0'  AS PK_SEQ, MaFAST + ' - ' + TEN  as nppTen FROM KHACHHANG WHERE NPP_FK = "+this.nppChinhanhId;
												
					System.out.println(KH_CN);
					this.nppRs = db.getScrol( KH_CN );
				}
			}
		}
		else
		{
			this.nppRs = db.getScrol(" select CONVERT(VARCHAR, PK_SEQ) + ' -- ' + '1' AS PK_SEQ , Ma + ' - ' + TEN  as nppTen from NHAPHANPHOI where trangthai = '1' and PK_SEQ!=1 "); // LẤY NHÀ PHÂN PHỐI KHÁC != 1 VÌ 1 CHÍNH LÀ HO
		}
		
		
		this.nhomkhttRs = db.get("Select pk_seq, diengiai,cast(pk_seq as varchar(10)) +' , ' +diengiai as ten  from Nhomkhachhangtt where trangthai='1'");
		
		this.htttRs = db.get("select pk_seq, ma, ten from ERP_HINHTHUCTHANHTOAN where trangthai = '1' and ma in ('CHUYENKHOAN') ");
		this.ndRs = db.get("select pk_seq, ma, ten from ERP_NOIDUNGTHUTIEN where trangthai = '1' ");
		this.nccRs = db.get(" select pk_seq, ma, ten from erp_nhacungcap where trangthai=1 ");

		String query = "SELECT (CASE WHEN NH.MA = 'EIB' THEN 1 ELSE 2 END) AS STT ,  \n"+
					   "NH_CTY.SOTAIKHOAN, NH_CTY.SOTAIKHOAN + ' - ' + NH.TEN + ' - ' + CN.TEN + ', ' + TT.MA  AS TAIKHOAN \n"+
					   "FROM ERP_NGANHANG_CONGTY NH_CTY \n"+
					   "INNER JOIN ERP_TIENTE TT ON TT.PK_SEQ = NH_CTY.TIENTE_FK \n"+
					   "INNER JOIN ERP_NGANHANG NH ON NH.PK_SEQ = NH_CTY.NGANHANG_FK \n"+
					   "INNER JOIN ERP_CHINHANH CN ON CN.PK_SEQ = NH_CTY.CHINHANH_FK \n"+
					   "WHERE NH_CTY.TRANGTHAI = 1 and NH_CTY.npp_FK = 1 AND NH_CTY.CONGTY_FK = '"+ this.ctyId + "' ";
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
			this.nppRs = db.get(sql);
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

		if (this.nhomkhttId.trim().length() > 1	 || this.nppId.trim().length() > 1 || this.nccId.length() > 0 || this.nvtuId.length() > 0 && this.htttId.length() > 0&& this.hoadonList.size() <= 0) 
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
						 "		FROM 	TraphacoDMS.dbo.ERP_HOADON HD 	\n"+
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

					// HOA DON PHE LIEU
					if (this.tienteId.equals("100000")) {
						query += "UNION ALL \n"+
						
								 "SELECT TT_HD.LOAIHOADON AS LOAIHD, '1' AS TIGIA  ,HDPL.PK_SEQ, '' AS MAHOPDONG, HDPL.kyhieuhoadon AS KYHIEU, HDPL.SOHOADON, HDPL.ngayhoadon , \n"+								 
								 "CAST( (ISNULL(PLSP.SOTIEN, 0) + ISNULL(PLSP.SOTIEN, 0)*HDPL.VAT/100 ) AS numeric(18,0)) AS SOTIENGOC, \n "+
								 "CAST( (ISNULL(PLSP.SOTIEN, 0) + ISNULL(PLSP.SOTIEN, 0)*HDPL.VAT/100 ) AS numeric(18,0)) - ISNULL(DATHU.TONGTHU, '0') AS SOTIENVND, \n"+
								 "0 AS SOTIENNT, \n"+
								 "ISNULL(TT_HD.SOTIENTT,0) + ISNULL(XKH.SOTIENTT,0) AS SOTIENTT, '100000' AS TTID, \n"+
								 "CONVERT(nvarchar(10), (dateadd(DAY, ISNULL(KH.THOIHANNO,0), HDPL.NGAYGHINHAN)),120 ) as NGAYDENHANTT \n"+
								 
								 "FROM ERP_HoaDonPheLieu HDPL \n"+
								 "INNER JOIN ERP_KHACHHANG KH ON HDPL.KHACHHANG_FK = KH.PK_SEQ \n"+
								 "LEFT JOIN ERP_THUTIEN_HOADON TT_HD ON TT_HD.HOADON_FK = HDPL.PK_SEQ and TT_HD.LOAIHOADON= 1  AND TT_HD.THUTIEN_FK = "+ this.id+ "\n"+
								 "INNER JOIN (SELECT hoadonphelieu_fk, SUM(thanhtien)AS SOTIEN \n"+
								 "			 FROM erp_hoadonphelieu_sanpham \n"+
								 "			 GROUP BY hoadonphelieu_fk )PLSP ON HDPL.pk_seq= PLSP.hoadonphelieu_fk \n"+
								 " LEFT JOIN	\n"+
								 " ( 	\n"+
								 "	SELECT TTHD.HOADON_FK, SUM(TTHD.SOTIENTT) AS TONGTHU \n"+
								 "	FROM ERP_THUTIEN_HOADON TTHD \n"+
								 "	INNER JOIN ERP_THUTIEN TT ON TTHD.THUTIEN_FK = TT.PK_SEQ \n"+
								 "	WHERE TT.TRANGTHAI != '2' AND TTHD.THUTIEN_FK !='"+ this.id	+ "'  AND TTHD.LOAIHOADON = 1 \n"+
								 "	GROUP BY HOADON_FK \n"+
								 " )DATHU ON HDPL.PK_SEQ = DATHU.HOADON_FK \n"+
								 "     LEFT JOIN ERP_XOAKHTRATRUOC_HOADON XKH ON HDPL.PK_SEQ = XKH.HOADON_FK AND XKH.XOAKHTRATRUOC_FK = "+ this.xoakhtratruocId +
								 " AND XKH.LOAIHD = 1 \n"+
								 " WHERE TT_HD.THUTIEN_FK = '"+ this.id	+ "' OR HDPL.PK_SEQ IN ( SELECT HOADON_FK  FROM ERP_XOAKHTRATRUOC_HOADON  WHERE XOAKHTRATRUOC_FK =  "+ this.xoakhtratruocId + " AND LOAIHD = 1)  \n";

					}
					// GIAM GIA HANG BAN
					query += "UNION ALL \n"+
							 "SELECT TT_HD.LOAIHOADON AS LOAIHD, TT.TIGIA AS TIGIA ,	GG.PK_SEQ, '' AS MAHOPDONG, GG.kyhieuhoadon AS KYHIEU, GG.SOHOADON, \n"+
							 "	GG.ngayhoadon , \n"+
							 "   CASE GG.TIENTE_FK WHEN '100000' THEN (ISNULL(GGSP.SOTIEN,0)+ ISNULL(GGSP.SOTIEN,0)*GG.VAT/100) \n"+
							 "	ELSE ISNULL(GGSP.SOTIEN,0) END AS SOTIENGOC, \n"+
							 "	CAST((CASE GG.TIENTE_FK WHEN '100000' THEN (ISNULL(GGSP.SOTIEN,0)+ ISNULL(GGSP.SOTIEN,0)*GG.VAT/100)- ISNULL(DATHU.TONGTHU, '0') \n"+
							 "        ELSE ISNULL(GGSP.SOTIEN,0)* TT.TIGIA - ISNULL(DATHU.TONGTHU, '0') END) as numeric(18,0) ) AS SOTIENVND, \n"+
							 "	ISNULL(GGSP.SOTIEN,0) - ISNULL(DATHU.TONGTHU, '0') AS SOTIENNT, \n"+
							 "	ISNULL(TT_HD.SOTIENTT,0) + ISNULL(XKH.SOTIENTT,0) AS SOTIENTT, GG.TIENTE_FK AS TTID, \n"+
							 "   CONVERT(nvarchar(10), (dateadd(DAY, ISNULL(KH.THOIHANNO,0), GG.NGAYGHINHAN)),120 ) as NGAYDENHANTT \n"+
							 " FROM  ERP_GIAMGIAHANGBAN GG  \n"+
							 " INNER JOIN ERP_KHACHHANG KH ON GG.KHACHHANG_FK = KH.PK_SEQ \n"+
							 " LEFT JOIN ERP_THUTIEN_HOADON TT_HD ON TT_HD.HOADON_FK = GG.PK_SEQ and TT_HD.LOAIHOADON = 2 and TT_HD.THUTIEN_FK = "+ this.id+ " \n"+
							 " LEFT JOIN ERP_THUTIEN TT ON TT_HD.THUTIEN_FK= TT.PK_SEQ \n"+
							 " INNER JOIN (SELECT GIAMGIA_FK, SUM(SOTIENTANGGIAM)AS SOTIEN \n"+
							 "			 FROM ERP_GIAMGIAHANGBAN_HOADON \n"+
							 "			 GROUP BY GIAMGIA_FK )GGSP ON GG.pk_seq= GGSP.GIAMGIA_FK \n"+
							 " LEFT JOIN	\n"+
							 " ( 	\n"+
							 "	SELECT TTHD.HOADON_FK, SUM(TTHD.SOTIENTT) AS TONGTHU \n"+
							 "	FROM ERP_THUTIEN_HOADON TTHD \n"+
							 "	INNER JOIN ERP_THUTIEN TT ON TTHD.THUTIEN_FK = TT.PK_SEQ \n"+
							 "	WHERE TT.TRANGTHAI != '2' AND TTHD.THUTIEN_FK !='"+ this.id+ "'  AND TTHD.LOAIHOADON = 2 \n"+
							 "	GROUP BY HOADON_FK \n"+
							 " )DATHU ON GG.PK_SEQ = DATHU.HOADON_FK \n"+
							 "     LEFT JOIN ERP_XOAKHTRATRUOC_HOADON XKH ON GG.PK_SEQ = XKH.HOADON_FK AND XKH.XOAKHTRATRUOC_FK = "+ this.xoakhtratruocId+ " AND XKH.LOAIHD = 2 \n"+
							 " WHERE  GG.TIENTE_FK = "+ this.tienteId+ 
							 " AND  (TT_HD.THUTIEN_FK = '"+ this.id+ "' OR GG.PK_SEQ IN ( SELECT HOADON_FK  FROM ERP_XOAKHTRATRUOC_HOADON  WHERE XOAKHTRATRUOC_FK = "+ this.xoakhtratruocId + " AND LOAIHD = 2)) \n";

					// KHACHHANGTRATRUOC
					if (this.xoakhtratruocId.trim().length() > 1) {
						query += " UNION ALL \n"
								+ "SELECT '3' as LOAIHD, tt.TIGIA, tt.PK_SEQ, '' AS MAHOPDONG, '' AS KYHIEU, CAST(tt.PK_SEQ as nvarchar(50)) AS SOHOADON, tt.NGAYCHUNGTU AS NGAYHOADON, \n"
								+ "        CASE WHEN tt.TIENTE_FK = '100000' THEN tt.THUDUOC*(-1) ELSE tt.THUDUOCNT*(-1) END as SOTIENGOC, \n"
								+ "        CAST( (XKH.tienchungtu*XKH.TIGIA *(-1)) as numeric(18,0)) AS SOTIENVND, \n"
								+ "        XKH.tienchungtu*(-1) AS SOTIENNT, XKH.tienthanhtoan*(-1) AS SOTIENTT, tt.TIENTE_FK AS TTID, \n"
								+ "       CONVERT(nvarchar(10), (dateadd(DAY, ISNULL(KH.THOIHANNO,0), TT.NGAYGHISO)),120 ) as NGAYDENHANTT \n"
								+ "FROM ERP_THUTIEN tt "
								+ "     INNER JOIN ERP_KHACHHANG KH ON TT.KHACHHANG_FK = KH.PK_SEQ \n"
								+ "     INNER JOIN  ERP_XOAKHTRATRUOC_CTTT XKH ON TT.PK_SEQ= XKH.cttt_fk \n"
								+ "WHERE tt.NOIDUNGTT_FK = '100001' AND tt.TRANGTHAI = '1' AND tt.TIENTE_FK = '"
								+ this.tienteId
								+ "' \n"
								+ "      and tt.KHACHHANG_FK "
								+ layKH
								+ " "
								+ "      and tt.PK_SEQ in (select CTTT_FK from ERP_XOAKHTRATRUOC_CTTT where XOAKHTRATRUOC_FK = '"
								+ this.xoakhtratruocId + "') \n";
					}
					// BÙ TRÙ CÔNG NỢ

					// LOAD PHIẾU BÙ TRỪ CÔNG NỢ (KHÁCH HÀNG NHẬN)
					query += " UNION ALL \n"+

							" SELECT '4' as LOAIHD, bt.TIGIA, bt.PK_SEQ, '' AS MAHOPDONG, '' AS KYHIEU, CAST(bt.PK_SEQ as nvarchar(50)) AS SOHOADON, bt.NGAYBUTRU AS NGAYHOADON, \n"+
							"        CASE WHEN bt.TIENTE_FK = '100000' THEN bt.TONGTIEN*bt.tigia ELSE bt.TONGTIEN END as SOTIENGOC, \n"+
							"        (BT.TONGTIEN - ISNULL(DATHANHTOAN.DATHANHTOAN,0))*(BT.TIGIA) AS SOTIENVND, \n"+
							"        (BT.TONGTIEN - ISNULL(DATHANHTOAN.DATHANHTOAN,0)) AS SOTIENNT, isnull(TT_HD.SOTIENTT,0) AS DATHANHTOAN, bt.TIENTE_FK AS TTID, \n"+
							"        '' as NGAYDENHANTT  \n"+
							" FROM ERP_BUTRUKHACHHANG BT  \n"+
							"      INNER JOIN NHAPHANPHOI KH ON BT.KH_NHANNO = KH.PK_SEQ \n"+
							" 	   LEFT JOIN ERP_THUTIEN_HOADON TT_HD ON TT_HD.HOADON_FK = BT.PK_SEQ and TT_HD.LOAIHOADON = 4 and TT_HD.THUTIEN_FK = "+ this.id + " \n"+							
							" 	   LEFT JOIN ERP_THUTIEN TT ON TT_HD.THUTIEN_FK= TT.PK_SEQ \n"+
							"      LEFT JOIN ( \n"+
							"					SELECT HOADON_FK, SUM(DATHANHTOAN.DATHANHTOAN) DATHANHTOAN \n" +
							"					FROM ( "+
							"						SELECT TTHD.HOADON_FK , SUM(TTHD.SOTIENTT) AS DATHANHTOAN \n"+
							"						FROM 	ERP_THUTIEN_HOADON TTHD \n"+
							"								INNER JOIN ERP_THUTIEN TT ON TTHD.THUTIEN_FK = TT.PK_SEQ \n"+
							"						WHERE 	TTHD.LOAIHOADON= '4' AND TT.TRANGTHAI NOT IN (2) AND TTHD.THUTIEN_FK !='" + this.id	+ "' \n"+
							"						GROUP BY HOADON_FK \n"+
							"						UNION ALL \n"+ 
							"						SELECT TTHD.HOADON_FK , SUM(TTHD.SOTIENTT) AS DATHANHTOAN \n"+
							"						FROM 	ERP_XOAKHTRATRUOC_HOADON TTHD \n"+
							"								INNER JOIN ERP_XOAKHTRATRUOC TT ON TTHD.xoakhtratruoc_fk = TT.PK_SEQ \n"+
							"						WHERE TTHD.LOAIHD= '4' AND TT.TRANGTHAI NOT IN (2) \n"+
		 					"						AND TT.pk_seq !='"+ this.id+ "' \n"+
							"						GROUP BY TTHD.HOADON_FK \n"+
							"				 	) DATHANHTOAN \n"+
							"					GROUP BY HOADON_FK \n"+
							"				  ) DATHANHTOAN ON BT.pk_seq = DATHANHTOAN.HOADON_FK \n"+
							" WHERE BT.TRANGTHAI = 1 AND BT.KH_NHANNO "	+ layKH + " AND " + 
							"		BT.TIENTE_FK = "+ this.tienteId + " AND TT_HD.THUTIEN_FK = "+ this.id + "";
					
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
						 "		FROM 	TraphacoDMS.dbo.ERP_HOADON HD 	\n"+
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

				// HOADONPHELIEU
				if (this.tienteId.equals("100000")) {
					query += " UNION ALL "+

							" (SELECT '1' AS LOAIHD , HDPHELIEU.TYGIA AS TIGIA,HDPHELIEU.PK_SEQ ,HDPHELIEU.MAHOPDONG, HDPHELIEU.KYHIEU, "
							+ "  HDPHELIEU.SOHOADON, HDPHELIEU.NGAYHOADON, "
							+ "   ISNULL(HDPHELIEU.SOTIENVND,0) AS SOTIENGOC, \n "
							+ "   ISNULL(HDPHELIEU.SOTIENVND,0) - ISNULL(DATHANHTOAN.DATHANHTOAN,0) AS SOTIENVND, "
							+ "  0 AS SOTIENNT, 0 AS DATHANHTOAN, '100000' AS TTID, HDPHELIEU.NGAYDENHANTT \n"
							+ " FROM "
							+ " ("
							+ " SELECT '1' AS TYGIA,PL.PK_SEQ,'' AS MAHOPDONG, PL.KYHIEUHOADON AS KYHIEU, PL.SOHOADON, PL.NGAYHOADON, CAST( (PLSP.SOTIENVND + PLSP.SOTIENVND*PL.VAT/100) AS NUMERIC(18,0) ) AS SOTIENVND,"
							+ "        0 AS SOTIENNT,0 AS DATHANHTOAN ,'100000' AS TTID , CONVERT(nvarchar(10), (dateadd(DAY, ISNULL(KH.THOIHANNO,0), PL.NGAYGHINHAN)),120 ) as NGAYDENHANTT \n"
							+ " FROM ERP_HOADONPHELIEU PL "
							+ "    INNER JOIN ERP_KHACHHANG KH ON PL.KHACHHANG_FK = KH.PK_SEQ \n"
							+ "    INNER JOIN \n"
							+ "    (SELECT HOADONPHELIEU_FK, SUM(THANHTIEN)AS SOTIENVND \n"
							+ "     FROM ERP_HOADONPHELIEU_SANPHAM \n"
							+ "     GROUP BY HOADONPHELIEU_FK)AS PLSP  ON PL.PK_SEQ= PLSP.HOADONPHELIEU_FK \n"
							+ " WHERE PL.KHACHHANG_FK " + layKH
							+ " and PL.TRANGTHAI = '1' AND ( ( PL.NGAYHOADON <='2015-12-31'  ) OR PL.NGAYHOADON > '2015-12-31' ) \n";
					if (this.id.length() > 0) {
						query += "AND PL.PK_SEQ NOT IN (SELECT HOADON_FK FROM ERP_THUTIEN_HOADON WHERE THUTIEN_FK = '"+ this.id + "') \n";

					}
					query += " )HDPHELIEU \n "
							+ " LEFT JOIN ( \n"
							+ "	SELECT HOADON_FK, SUM(ISNULL(DATHANHTOAN, 0)) AS DATHANHTOAN \n"
							+ "	FROM  \n"
							+ "	( 	\n"
							+ "		SELECT TTHD.HOADON_FK, SUM(TTHD.SOTIENTT) AS DATHANHTOAN \n"
							+ "		FROM ERP_XOAKHTRATRUOC_HOADON TTHD \n"
							+ "		INNER JOIN ERP_XOAKHTRATRUOC TT ON TTHD.XOAKHTRATRUOC_FK = TT.PK_SEQ \n"
							+ "		WHERE TT.TRANGTHAI NOT IN (2) AND ISNULL(TT.LOAIXOATRATRUOC,0) = '0' "
							+ "		AND  TT.NGAYGHISO >= '2014-08-01' AND TTHD.LOAIHD = '1' \n"
							+ "		GROUP BY HOADON_FK \n"
							+ "                             \n"
							+ "       UNION ALL                      \n"
							+ "		SELECT TTHD.HOADON_FK , SUM(TTHD.SOTIENTT) AS DATHANHTOAN \n"
							+ "		FROM ERP_THUTIEN_HOADON TTHD \n"
							+ "		INNER JOIN ERP_THUTIEN TT ON TTHD.THUTIEN_FK = TT.PK_SEQ \n"
							+ "		WHERE TTHD.LOAIHOADON= '1' AND TT.TRANGTHAI NOT IN (2)\n";

					if (this.id.trim().length() > 0) {
						query += " 		AND TT.PK_SEQ  != '" + this.id + "' \n";
					}

					query += " 		GROUP BY HOADON_FK \n"
							+

							"		UNION ALL \n "
							+

							"		SELECT BT_KH.HOADON_FK, SUM(BT_KH.XOANO) AS SOTIENBUTRU  \n"
							+ "		FROM   ERP_BUTRUKHACHHANG BT INNER JOIN ERP_BUTRUKHACHHANG_CHITIET BT_KH ON BT.PK_SEQ =BT_KH.BTKH_FK \n "
							+ "		WHERE  BT.TRANGTHAI NOT IN (2) AND BT_KH.LOAIHD = 1  AND BT.KH_CHUYENNO "
							+ layKH
							+ "  \n "
							+ "		GROUP BY BT_KH.HOADON_FK \n"
							+

							"	) HOADONDATT  \n"
							+ "	GROUP BY HOADON_FK "
							+ ")DATHANHTOAN ON HDPHELIEU.PK_SEQ = DATHANHTOAN.HOADON_FK \n"
							+ " WHERE ISNULL(HDPHELIEU.SOTIENVND,0) - ISNULL(DATHANHTOAN.DATHANHTOAN, '0') != 0  \n"
							+ ")";
				}

				// HOA DON : GIAM /TANG GIA HANG BAN
				query += " UNION ALL " 
						+ " (SELECT '2' AS LOAIHD , '"
						+ this.tigia.replaceAll(",", "")
						+ "' as TIGIA ,GGHANGBAN.PK_SEQ ,GGHANGBAN.MAHOPDONG, GGHANGBAN.KYHIEU, "
						+ "  GGHANGBAN.SOHOADON, GGHANGBAN.NGAYHOADON, "
						+ "  ISNULL(GGHANGBAN.SOTIENVND,0) AS SOTIENGOC, \n "
						+ "  CAST((ISNULL(GGHANGBAN.SOTIENVND,0)*"
						+ this.tigia.replaceAll(",", "")
						+ " - ISNULL(DATHANHTOAN.DATHANHTOAN,0)) as numeric(18,0) )  AS SOTIENVND, "
						+ "  ISNULL(GGHANGBAN.SOTIENVND,0) - ISNULL(DATHANHTOAN.DATHANHTOAN,0) AS SOTIENNT, 0 AS DATHANHTOAN, GGHANGBAN.TTID,"
						+ "  GGHANGBAN.NGAYDENHANTT \n"
						+ " FROM "
						+ " ("
						+ " SELECT GG.PK_SEQ,'' AS MAHOPDONG, GG.KYHIEUHOADON AS KYHIEU, GG.SOHOADON, GG.NGAYHOADON, "
						+ "       (CASE GG.TIENTE_FK  WHEN '100000' THEN (GGSP.SOTIENVND + GGSP.SOTIENVND*GG.VAT/100) "
						+ "                           ELSE GGSP.SOTIENVND END) AS SOTIENVND , "
						+ "       0 AS SOTIENNT,0 AS DATHANHTOAN ,GG.TIENTE_FK AS TTID, CONVERT(nvarchar(10), (dateadd(DAY, ISNULL(KH.THOIHANNO,0), GG.NGAYGHINHAN)),120 ) as NGAYDENHANTT \n"
						+ " FROM ERP_GIAMGIAHANGBAN GG "
						+ "    INNER JOIN ERP_KHACHHANG KH ON GG.KHACHHANG_FK = KH.PK_SEQ \n"
						+ "    INNER JOIN \n"
						+ "    (SELECT GIAMGIA_FK, SUM(SOTIENTANGGIAM)AS SOTIENVND \n"
						+ "     FROM ERP_GIAMGIAHANGBAN_HOADON \n"
						+ "     GROUP BY GIAMGIA_FK)AS GGSP  ON GG.PK_SEQ= GIAMGIA_FK \n"
						+ " WHERE GG.KHACHHANG_FK " + layKH
						+ " and GG.TRANGTHAI = '1' "
						+ "AND GG.NGAYGHINHAN >= '2014-08-01'  ";

				if (this.id.length() > 0) {
					query += "AND GG.PK_SEQ NOT IN (SELECT HOADON_FK FROM ERP_THUTIEN_HOADON WHERE THUTIEN_FK = '"
							+ this.id + "') \n";

				}
				query += " )GGHANGBAN \n "
						+ " LEFT JOIN ( \n"
						+ "	SELECT HOADON_FK, SUM(ISNULL(DATHANHTOAN, 0)) AS DATHANHTOAN \n"
						+ "	FROM  \n"
						+ "	( 	\n"
						+ "		SELECT TTHD.HOADON_FK , SUM(TTHD.SOTIENTT) AS DATHANHTOAN \n"
						+ "		FROM ERP_THUTIEN_HOADON TTHD \n"
						+ "		INNER JOIN ERP_THUTIEN TT ON TTHD.THUTIEN_FK = TT.PK_SEQ \n"
						+ "		WHERE TTHD.LOAIHOADON= '2' AND TT.TRANGTHAI NOT IN (2)\n";

				if (this.id.trim().length() > 0) {
					query += " 		AND TT.PK_SEQ  != '" + this.id + "' \n";
				}

				query += " 	GROUP BY HOADON_FK \n"
						+

						"		UNION ALL \n "
						+

						"		SELECT BT_KH.HOADON_FK, SUM(BT_KH.XOANO) AS SOTIENBUTRU  \n"
						+ "		FROM   ERP_BUTRUKHACHHANG BT INNER JOIN ERP_BUTRUKHACHHANG_CHITIET BT_KH ON BT.PK_SEQ =BT_KH.BTKH_FK \n "
						+ "		WHERE  BT.TRANGTHAI NOT IN (2) AND BT_KH.LOAIHD = 2  AND BT.KH_CHUYENNO  "
						+ layKH
						+ "  \n "
						+ "		GROUP BY BT_KH.HOADON_FK \n"
						+ "	) HOADONDATT  \n"
						+ "	GROUP BY HOADON_FK "
						+ ")DATHANHTOAN ON GGHANGBAN.PK_SEQ = DATHANHTOAN.HOADON_FK \n"
						+ " WHERE GGHANGBAN.SOTIENVND - ISNULL(DATHANHTOAN.DATHANHTOAN, '0') != 0 OR GGHANGBAN.SOTIENVND - ISNULL(DATHANHTOAN.DATHANHTOAN, '0') != 0"
						+ "   AND GGHANGBAN.TTID = " + this.tienteId + " \n"
						+ ")";

				// LOAD PHIEU THU TIEN: KHACH HANG TRA TRUOC
				query += " UNION ALL \n"
						+

						"SELECT '3' as LOAIHD, tt.TIGIA, tt.PK_SEQ, '' AS MAHOPDONG, '' AS KYHIEU, CAST(tt.PK_SEQ as nvarchar(50)) AS SOHOADON, tt.NGAYCHUNGTU AS NGAYHOADON, \n"
						+ "        CASE WHEN tt.TIENTE_FK = '100000' THEN tt.THUDUOC*(-1) ELSE tt.THUDUOCNT*(-1) END as SOTIENGOC, \n"
						+ "        (TT.THUDUOC - ISNULL(DAXOANO.SOTIENVND,0))*(-1) AS SOTIENVND, \n"
						+ "        (TT.THUDUOCNT - ISNULL(DAXOANO.SOTIENNT,0))*(-1) AS SOTIENNT, 0 AS DATHANHTOAN, tt.TIENTE_FK AS TTID,"
						+ "        CONVERT(nvarchar(10), (dateadd(DAY, ISNULL(KH.THOIHANNO,0), TT.NGAYGHISO)),120 ) as NGAYDENHANTT  \n"
						+ "FROM ERP_THUTIEN tt "
						+ "    INNER JOIN ERP_KHACHHANG KH ON TT.KHACHHANG_FK = KH.PK_SEQ \n"
						+ "    LEFT JOIN \n"
						+ "     ( SELECT CT.CTTT_FK, SUM(CT.TIENTHANHTOAN) AS SOTIENNT ,SUM(CT.TIENTHANHTOAN*CT.TIGIA) AS SOTIENVND  \n"
						+ "       FROM ERP_XOAKHTRATRUOC_CTTT CT INNER JOIN ERP_XOAKHTRATRUOC XKH ON CT.XOAKHTRATRUOC_FK = XKH.PK_SEQ \n"
						+ "       WHERE XKH.TRANGTHAI != 2 AND XKH.LOAIXOATRATRUOC = '0' \n"
						+ "       GROUP BY CT.CTTT_FK \n"
						+

						"		UNION ALL \n "
						+

						"		SELECT BT_KH.HOADON_FK, SUM(BT_KH.XOANO) AS SOTIENBUTRU, SUM(BT_KH.XOANO*BT.TIGIA)  AS SOTIENVND  \n"
						+ "		FROM   ERP_BUTRUKHACHHANG BT INNER JOIN ERP_BUTRUKHACHHANG_CHITIET BT_KH ON BT.PK_SEQ =BT_KH.BTKH_FK \n "
						+ "		WHERE  BT.TRANGTHAI NOT IN (2) AND BT_KH.LOAIHD = 3 AND BT.KH_CHUYENNO  "
						+ layKH
						+ "  \n "
						+ "		GROUP BY BT_KH.HOADON_FK \n"
						+

						"      ) DAXOANO ON tt.PK_SEQ = DAXOANO.CTTT_FK \n"
						+ "WHERE tt.NOIDUNGTT_FK = '100001' AND tt.TRANGTHAI = '1' AND tt.TIENTE_FK = '"+ this.tienteId + "' \n" 
						+ "      and tt.KHACHHANG_FK " + layKH + " "
						+ "      AND  ( ( TT.NGAYCHUNGTU <= '2015-12-31' ) OR TT.NGAYCHUNGTU > '2015-12-31' ) AND (TT.THUDUOC - ISNULL(DAXOANO.SOTIENVND,0))*(-1) != 0 \n";
				if (this.id.trim().length() > 0) {
					query += "     and tt.PK_SEQ not in (SELECT CTTT_FK FROM ERP_XOAKHTRATRUOC_CTTT where XOAKHTRATRUOC_FK = "
							+ this.xoakhtratruocId + " )";
				}

				// LOAD PHIẾU BÙ TRỪ CÔNG NỢ (KHÁCH HÀNG NHẬN)
				query +=" UNION ALL \n"+

						" SELECT '4' as LOAIHD, bt.TIGIA, bt.PK_SEQ, '' AS MAHOPDONG, '' AS KYHIEU, CAST(bt.PK_SEQ as nvarchar(50)) AS SOHOADON, bt.NGAYBUTRU AS NGAYHOADON, \n"+
						"        CASE WHEN bt.TIENTE_FK = '100000' THEN bt.TONGTIEN*bt.tigia ELSE bt.TONGTIEN END as SOTIENGOC, \n"+
						"        (BT.TONGTIEN - ISNULL(DATHANHTOAN.DATHANHTOAN,0))*(BT.TIGIA) AS SOTIENVND, \n"+
						"        (BT.TONGTIEN - ISNULL(DATHANHTOAN.DATHANHTOAN,0)) AS SOTIENNT, 0 AS DATHANHTOAN, bt.TIENTE_FK AS TTID, \n"+
						"        '' as NGAYDENHANTT  \n"+
						" FROM ERP_BUTRUKHACHHANG BT  \n"+
						"      INNER JOIN NHAPHANPHOI KH ON BT.KH_NHANNO = KH.PK_SEQ \n"+
						"      LEFT JOIN ( \n"+
						"					SELECT DATHANHTOAN.HOADON_FK, SUM(DATHANHTOAN.DATHANHTOAN) DATHANHTOAN \n"+
						"					FROM \n"+
						"					( \n"+
						"						SELECT TTHD.HOADON_FK , SUM(TTHD.SOTIENTT) AS DATHANHTOAN \n"+
						"						FROM 	ERP_THUTIEN_HOADON TTHD \n"+
						"								INNER JOIN ERP_THUTIEN TT ON TTHD.THUTIEN_FK = TT.PK_SEQ \n"+
						"						WHERE TTHD.LOAIHOADON = '4' AND TT.TRANGTHAI NOT IN (2) \n"+
						"                       AND TT.PK_SEQ != '" + (this.id == "" ? "0": this.id) + "' \n"+		
						"						GROUP BY TTHD.HOADON_FK \n"+
						"						UNION ALL \n"+
						"						SELECT TTHD.HOADON_FK , SUM(TTHD.SOTIENTT) AS DATHANHTOAN \n"+
						"						FROM 	ERP_XOAKHTRATRUOC_HOADON TTHD \n"+
						"								INNER JOIN ERP_XOAKHTRATRUOC TT ON TTHD.xoakhtratruoc_fk = TT.PK_SEQ \n"+
						"						WHERE TTHD.LOAIHD = '4' AND TT.TRANGTHAI NOT IN (2) \n"+	
						"						GROUP BY TTHD.HOADON_FK \n"+
						"					) DATHANHTOAN \n"+
						"					GROUP BY HOADON_FK \n"+
						"				) DATHANHTOAN ON BT.pk_seq = DATHANHTOAN.HOADON_FK \n"+
						" WHERE BT.TRANGTHAI = 1 AND (BT.TONGTIEN - ISNULL(DATHANHTOAN.DATHANHTOAN,0))*(BT.TIGIA) > 0 AND BT.KH_NHANNO " + layKH ;
				
				if (this.id.length() > 0) {
					query += "AND BT.PK_SEQ NOT IN (SELECT HOADON_FK FROM ERP_THUTIEN_HOADON WHERE THUTIEN_FK = '"+ this.id + "' AND LOAIHOADON = '4') \n";

				}

				query += " ORDER BY LOAIHD, NGAYHOADON ASC ";

			} else if (this.ndId.equals("100003")) { // THU HOI TAM UNG

				if (this.DoiTuongTamUng.equals("1")) // NHA CUNG CAP
				{
					if (this.id.length() > 0) {
						query += " SELECT ISNULL(TT.TIGIA,100000) AS TIGIA, TT_HD.HOADON_FK AS PK_SEQ, 'TU' AS KYHIEU, TT.PK_SEQ AS SOHOADON, \n"
								+ "        TT.NGAYCHUNGTU AS NGAYHOADON, \n"
								+ "        CASE WHEN TT.TIENTE_FK= 100000 THEN  (TT_HD.SOTIENAVAT) ELSE (TT_HD.SOTIENAVAT/HD.TIGIA) END AS SOTIENGOC , \n"
								+ "        TT_HD.SOTIENAVAT AS SOTIENVND , \n"
								+ "        CASE WHEN TT.TIENTE_FK= 100000 THEN  0 ELSE (TT_HD.SOTIENAVAT/HD.TIGIA) END AS SOTIENNT , \n"
								+ "        TT_HD.SOTIENTT, TT.TIENTE_FK AS TTID, CONVERT(nvarchar(10), (dateadd(DAY, ISNULL(NCC.THOIHANNO,0), HD.NGAYGHINHAN)),120 ) as NGAYDENHANTT  \n"
								+ " FROM ERP_THUTIEN_HOADON TT_HD INNER JOIN ERP_THUTIEN TT ON TT_HD.THUTIEN_FK= TT.PK_SEQ"
								+ "      INNER JOIN ERP_THANHTOANHOADON HD ON TT_HD.HOADON_FK = HD.PK_SEQ"
								+ "      INNER JOIN ERP_NHACUNGCAP NCC ON NCC.PK_SEQ = HD.NCC_FK \n"
								+ " WHERE TT.PK_SEQ= "+ this.id	+ " \n"
								+ "UNION ";
					}

					query += " SELECT ISNULL(TT.TIGIA,100000) AS TIGIA, TT.PK_SEQ, 'TU' AS KYHIEU, TT.PK_SEQ AS SOHOADON, TT.NGAYGHINHAN AS NGAYHOADON, \n"
							+ "        (TTHD.SOTIENTT - (ISNULL(DAXOATU.SOTIEN_XOATU,0) * ISNULL(DAXOATU.TIGIA,0)) ) AS SOTIENGOC, \n "
							+ " 		 (TTHD.SOTIENTT - (ISNULL(DAXOATU.SOTIEN_XOATU,0) * ISNULL(DAXOATU.TIGIA,0)) - (ISNULL(DATHANHTOAN.SOTIENTT,0)* ISNULL(DATHANHTOAN.TIGIA,0)) ) AS SOTIENVND, \n"
							+ "        CASE WHEN "
							+ this.tienteId
							+ "= 100000 THEN  0  ELSE (TTHD.SOTIENTT - ISNULL(DAXOATU.SOTIEN_XOATU,0) - ISNULL(DATHANHTOAN.SOTIENTT,0)) END AS SOTIENNT , \n"
							+ "        0 AS SOTIENTT, TT.TIENTE_FK AS TTID, CONVERT(nvarchar(10), (dateadd(DAY, ISNULL(NCC.THOIHANNO,0), TT.NGAYGHINHAN)),120 ) as NGAYDENHANTT \n"
							+ " FROM ERP_THANHTOANHOADON TT \n"
							+ "      INNER JOIN  "
							+ "      (SELECT THANHTOANHD_FK, SUM(SOTIENTT) as SOTIENTT \n"
							+ "       FROM ERP_THANHTOANHOADON_HOADON \n"
							+ "       WHERE  LOAIHD ='1' \n"
							+ "       GROUP BY THANHTOANHD_FK ) TTHD ON TTHD.THANHTOANHD_FK = TT.PK_SEQ \n"
							+ "      INNER JOIN ERP_NHACUNGCAP NCC ON NCC.PK_SEQ = TT.NCC_FK \n"
							+ "      LEFT JOIN \n"
							+ "      (SELECT TT_HD.HOADON_FK, TT.TIGIA, SUM(TT_HD.SOTIENTT) AS SOTIENTT \n"
							+ "		 FROM ERP_THUTIEN TT INNER JOIN ERP_THUTIEN_HOADON TT_HD ON TT.PK_SEQ = TT_HD.THUTIEN_FK \n"
							+ "       WHERE  TT.NCC_FK= "
							+ this.nccId
							+ "  AND TT.TRANGTHAI!= 2 AND TT.TIENTE_FK = "
							+ this.tienteId
							+ " \n"
							+ "		 GROUP BY TT_HD.HOADON_FK, TT.TIGIA) DATHANHTOAN ON TT.PK_SEQ= DATHANHTOAN.HOADON_FK \n"
							+ "      LEFT JOIN \n"
							+ "      (SELECT XTT_HD.HOADON_FK, TTHD.TIGIA, SUM(XTT_HD.SOTIENTT) AS SOTIEN_XOATU \n"
							+ "		 FROM ERP_XOAKHTRATRUOC XTT INNER JOIN ERP_XOAKHTRATRUOC_HOADON XTT_HD ON XTT.PK_SEQ=XTT_HD.xoakhtratruoc_fk \n"
							+ "            INNER JOIN ERP_THANHTOANHOADON TTHD ON TTHD.PK_SEQ= XTT_HD.HOADON_FK \n"
							+ "       WHERE XTT.LOAIXOATRATRUOC = '1' AND XTT.NCC_FK= "
							+ this.nccId
							+ "  AND XTT.TRANGTHAI= 1 AND XTT.TIENTE_FK = "
							+ this.tienteId
							+ " \n"
							+ "		 GROUP BY XTT_HD.HOADON_FK, TTHD.TIGIA) DAXOATU ON TT.PK_SEQ= DAXOATU.HOADON_FK \n"
							+ " WHERE TT.TRANGTHAI='1'  AND TT.TIENTE_FK= "
							+ this.tienteId
							+ " AND NCC_FK = "
							+ this.nccId
							+ " "
							+ " AND (TT.SOTIENTT - (ISNULL(DAXOATU.SOTIEN_XOATU,0) * ISNULL(DAXOATU.TIGIA,0)) - (ISNULL(DATHANHTOAN.SOTIENTT,0)* ISNULL(DATHANHTOAN.TIGIA,0)) ) > 0 "
							+ " AND TT.NGAYGHINHAN > '2015-12-31'  ";
					if (this.id.trim().length() > 0) {
						query += " AND TT.PK_SEQ NOT IN (SELECT HOADON_FK FROM ERP_THUTIEN_HOADON WHERE THUTIEN_FK= "
								+ this.id
								+ ") "
								+ " AND TT.NGAYGHINHAN > '2015-12-31' ";
					}

				} else // NHAN VIEN
				{
					if (this.id.length() > 0) {
						query += " SELECT ISNULL(TT.TIGIA,100000) AS TIGIA, TT_HD.HOADON_FK AS PK_SEQ, 'TU' AS KYHIEU, TT.PK_SEQ AS SOHOADON, "
								+ "        TT.NGAYCHUNGTU AS NGAYHOADON, \n"
								+ "        CASE WHEN TT.TIENTE_FK= 100000 THEN (TT_HD.SOTIENAVAT) ELSE (TT_HD.SOTIENAVAT/HD.TIGIA) END AS SOTIENGOC , \n"
								+ "        TT_HD.SOTIENAVAT AS SOTIENVND , \n"
								+ "        CASE WHEN TT.TIENTE_FK= 100000 THEN  0 ELSE (TT_HD.SOTIENAVAT/HD.TIGIA) END AS SOTIENNT , \n"
								+ "        TT_HD.SOTIENTT, TT.TIENTE_FK AS TTID, '' AS NGAYDENHANTT  \n"
								+ " FROM ERP_THUTIEN_HOADON TT_HD INNER JOIN ERP_THUTIEN TT ON TT_HD.THUTIEN_FK= TT.PK_SEQ"
								+ "      INNER JOIN ERP_THANHTOANHOADON HD ON TT_HD.HOADON_FK = HD.PK_SEQ \n"
								+ " WHERE TT.PK_SEQ= "
								+ this.id
								+ " \n"
								+ "UNION ";
					}

					query += " SELECT ISNULL(TT.TIGIA,100000) AS TIGIA, TT.PK_SEQ, 'TU' AS KYHIEU, TT.PK_SEQ AS SOHOADON, TT.NGAYGHINHAN AS NGAYHOADON, \n"
							+"   CASE WHEN TT.NGAYGHINHAN = '2015-04-30' THEN TT.SOTIENHD - ISNULL(DACANTRU.CANTRU,0) - (ISNULL(DAXOATU.SOTIEN_XOATU,0) * ISNULL(DAXOATU.TIGIA,0)) \n"
						    +"   ELSE(TTHD.SOTIENTT - ISNULL(DACANTRU.CANTRU,0) - (ISNULL(DAXOATU.SOTIEN_XOATU,0) * ISNULL(DAXOATU.TIGIA,0))) END AS SOTIENGOC, \n"
						    +"   CASE WHEN TT.NGAYGHINHAN = '2015-04-30' THEN TT.SOTIENHD - ISNULL(DACANTRU.CANTRU,0) - (ISNULL(DAXOATU.SOTIEN_XOATU,0) * ISNULL(DAXOATU.TIGIA,0))  \n"
					  		+"   ELSE  (TTHD.SOTIENTT - ISNULL(DACANTRU.CANTRU,0) - (ISNULL(DAXOATU.SOTIEN_XOATU,0) * ISNULL(DAXOATU.TIGIA,0)) - (ISNULL(DATHANHTOAN.SOTIENTT,0)* ISNULL(DATHANHTOAN.TIGIA,0)) ) END AS SOTIENVND, \n"
							+ "        CASE WHEN "
							+ this.tienteId
							+ "= 100000 THEN  0  ELSE (TTHD.SOTIENTT - ISNULL(DACANTRU.CANTRU,0) - ISNULL(DAXOATU.SOTIEN_XOATU,0) - ISNULL(DATHANHTOAN.SOTIENTT,0)) END AS SOTIENNT , \n"
							+ "        0 AS SOTIENTT, TT.TIENTE_FK AS TTID, '' AS NGAYDENHANTT \n"
							+ " FROM ERP_THANHTOANHOADON TT \n"
							+ "      LEFT JOIN  "
							+ "      (SELECT THANHTOANHD_FK, SUM(SOTIENTT) as SOTIENTT \n"
							+ "       FROM ERP_THANHTOANHOADON_HOADON \n"
							+ "       WHERE LOAIHD ='1'  \n"
							+ "       GROUP BY THANHTOANHD_FK ) TTHD ON TTHD.THANHTOANHD_FK = TT.PK_SEQ \n"
							+ "      LEFT JOIN \n"
							+ "      (SELECT TT_HD.HOADON_FK, TT.TIGIA, SUM(TT_HD.SOTIENTT) AS SOTIENTT \n"
							+ "		 FROM ERP_THUTIEN TT INNER JOIN ERP_THUTIEN_HOADON TT_HD ON TT.PK_SEQ = TT_HD.THUTIEN_FK \n"
							+ "       WHERE  TT.NHANVIEN_FK= "
							+ this.nvtuId
							+ "  AND TT.TRANGTHAI!= 2 AND TT.TIENTE_FK = "
							+ this.tienteId
							+ " \n"
							+ "		 GROUP BY TT_HD.HOADON_FK, TT.TIGIA) DATHANHTOAN ON TT.PK_SEQ= DATHANHTOAN.HOADON_FK \n"
							+ "      LEFT JOIN \n"
							+ "      (SELECT XTT_HD.HOADON_FK, TTHD.TIGIA, SUM(XTT_HD.SOTIENTT) AS SOTIEN_XOATU \n"
							+ "		 FROM ERP_XOAKHTRATRUOC XTT INNER JOIN ERP_XOAKHTRATRUOC_HOADON XTT_HD ON XTT.PK_SEQ=XTT_HD.xoakhtratruoc_fk \n"
							+ "            INNER JOIN ERP_THANHTOANHOADON TTHD ON TTHD.PK_SEQ= XTT_HD.HOADON_FK \n"
							+ "       WHERE XTT.LOAIXOATRATRUOC = '1' AND XTT.NHANVIEN_FK= "
							+ this.nvtuId
							+ "  AND XTT.TRANGTHAI= 1 AND XTT.TIENTE_FK = "
							+ this.tienteId
							+ " \n"
							+ "		 GROUP BY XTT_HD.HOADON_FK, TTHD.TIGIA) DAXOATU ON TT.PK_SEQ= DAXOATU.HOADON_FK \n"
							+ "      LEFT JOIN \n"
							+ "       (SELECT  DN_CT.THANHTOANHOADON_FK AS HOADON_FK , SUM(DN_CT.SOTIENCANTRU) AS CANTRU \n"
							+ "        FROM ERP_DENGHITT_THANHTOANHOADON DN_CT INNER JOIN ERP_MUAHANG DN ON DN_CT.DENGHITT_FK = DN.PK_SEQ \n"
							+ "        WHERE DN.LOAIHANGHOA_FK='2' and DN.TYPE = 0  AND DN.TRANGTHAI NOT IN (3,4)  AND DN.NHANVIEN_FK = "
							+ this.nvtuId
							+ " \n"
							+ "        GROUP BY  DN_CT.THANHTOANHOADON_FK) DACANTRU ON TT.PK_SEQ = DACANTRU.HOADON_FK \n"
							+

							" WHERE TT.TRANGTHAI='1'  AND TT.TIENTE_FK= "
							+ this.tienteId
							+ " AND TT.NHANVIEN_FK = "+ this.nvtuId+ " "
							+ " AND ( ISNULL(TTHD.SOTIENTT,0) - ISNULL(DACANTRU.CANTRU,0) - (ISNULL(DAXOATU.SOTIEN_XOATU,0) * ISNULL(DAXOATU.TIGIA,0)) - (ISNULL(DATHANHTOAN.SOTIENTT,0)* ISNULL(DATHANHTOAN.TIGIA,0)) > 0 \n"
							+ "		 OR (TT.NGAYGHINHAN = '2015-04-30' AND ISNULL(TT.SOTIENHD,0) - ISNULL(DACANTRU.CANTRU,0) - (ISNULL(DAXOATU.SOTIEN_XOATU,0) * ISNULL(DAXOATU.TIGIA,0)) > 0 ) ) \n"; 
					if (this.id.trim().length() > 0) {
						query += " AND TT.PK_SEQ NOT IN (SELECT HOADON_FK FROM ERP_THUTIEN_HOADON WHERE THUTIEN_FK= "+ this.id + ") "
							   + "  AND TT.NGAYGHINHAN > '2015-12-31' ";
					}

				}
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

	private String getDateTime() {
		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		Date date = new Date();
		return dateFormat.format(date);
	}

	public void DBclose() {

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

	public String getBangkeId() {

		return this.bangkeId;
	}

	public void setBangkeId(String bangkeId) {

		this.bangkeId = bangkeId;
	}

	public ResultSet getBangkeRs() {

		return bangkeRs;
	}

	public void setBangkeRs(ResultSet bangkeRs) {

		bangkeRs = this.bangkeRs;
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

	
	public void createRs_display() {
		// Kênh bán hàng

//		NumberFormat formatter = new DecimalFormat("#,###,###.##");
//		NumberFormat formatterNT = new DecimalFormat("#,###,###.##");

		this.getNppInfo();

		if (this.ngaychungtu.length() > 0)
			this.ngayghiso = this.ngaychungtu;

		String command = "";
		
		if(this.ndId.equals("100002")) // THU KHÁC
		{
			this.taikhoanRs = db.getScrol("Select pk_seq, sohieutaikhoan + ' - ' + TENTAIKHOAN as sohieutk from ERP_TAIKHOANKT where trangthai = 1 and CONGTY_FK = "+this.ctyId+" ");
			this.SanphamList = db.getScrol("select PK_SEQ, MA , TEN from SANPHAM where TRANGTHAI = 1 ");
			this.NganhangList = db.getScrol("select PK_SEQ, MA , TEN from ERP_NGANHANG where TRANGTHAI = 1 ");
			this.NccList = db.getScrol("select PK_SEQ, MA , TEN from ERP_NHACUNGCAP where TRANGTHAI = 1 ");
			this.TaisanList = db.getScrol("select PK_SEQ, MA , TEN from ERP_TAISANCODINH where TRANGTHAI = 1 ");
			this.KhachhangList = db.getScrol("select PK_SEQ, MA , TEN from NHAPHANPHOI where TRANGTHAI = 1 AND PK_SEQ != 1 ");
			this.NhanvienList = db.getScrol("select PK_SEQ, MA , TEN from ERP_NHANVIEN where TRANGTHAI = 1 ");			
			this.nvtuRs = db.get(" select pk_seq, ma, ten from erp_nhanvien where trangthai=1 ");
		}
		
		if(this.ndId.equals("100001")) // THU TIỀN KH TRẢ TRƯỚC
		{
			if(this.isChuyenCN.equals("0"))// KHÔNG CHUYỂN XUỐNG CHI NHÁNH
				this.nppRs = db.getScrol(" select CONVERT(VARCHAR, PK_SEQ) + ' -- ' + '1' AS PK_SEQ , Ma + ' - ' + TEN  as nppTen from NHAPHANPHOI where trangthai = '1' and PK_SEQ!=1 "); // LẤY NHÀ PHÂN PHỐI KHÁC != 1 VÌ 1 CHÍNH LÀ HO
			else
			{
				this.chinhanhRs = db.get(" select pk_seq, Ma + ' - ' + TEN  as nppTen from NHAPHANPHOI where trangthai = '1' and PK_SEQ!=1 ");
								
				if(this.nppChinhanhId.trim().length() > 0)
				{
					String 	KH_CN = " SELECT CONVERT(VARCHAR, PK_SEQ) + ' -- ' + '1'  AS PK_SEQ, Ma + ' - ' + TEN nppTen FROM NHAPHANPHOI WHERE TRANGTHAI = 1 AND PK_SEQ != 1 \n";
							KH_CN +=" AND PK_SEQ != "+this.nppChinhanhId;
					
							KH_CN += " UNION ALL \n"+
									 " SELECT CONVERT(VARCHAR, PK_SEQ) + ' -- ' + '0'  AS PK_SEQ, MaFAST + ' - ' + TEN  as nppTen FROM KHACHHANG WHERE NPP_FK = "+this.nppChinhanhId;
												
					System.out.println(KH_CN);
					this.nppRs = db.getScrol( KH_CN );
				}
			}
		}
		else
		{
			this.nppRs = db.getScrol(" select CONVERT(VARCHAR, PK_SEQ) + ' -- ' + '1' AS PK_SEQ , Ma + ' - ' + TEN  as nppTen from NHAPHANPHOI where trangthai = '1' and PK_SEQ!=1 "); // LẤY NHÀ PHÂN PHỐI KHÁC != 1 VÌ 1 CHÍNH LÀ HO
		}
		
		if(this.nppId!=null)
		{
			if (this.nppId.trim().length() > 0) {
				command = "select PK_SEQ, TEN + ', ' + DIENGIAI as TEN  \n"+
						  "from KENHBANHANG \n"+
						  "where TRANGTHAI = 1 and PK_SEQ in (select KBH_FK from KHACHHANG_KENHBANHANG where KHACHHANG_FK = " + this.nppId+ ") ";
				this.kbhRs = db.get(command);
			}
			
		}
						
		
		this.nhomkhttRs = db.get("Select pk_seq, diengiai,cast(pk_seq as varchar(10)) +' , ' +diengiai as ten  from Nhomkhachhangtt where trangthai='1'");


		this.htttRs = db.get("select pk_seq, ma, ten from ERP_HINHTHUCTHANHTOAN where trangthai = '1' AND PK_SEQ = 100001 ");
		this.ndRs = db.get("select pk_seq, ma, ten from ERP_NOIDUNGTHUTIEN where trangthai = '1' ");
		this.nccRs = db.get(" select pk_seq, ma, ten from erp_nhacungcap where trangthai=1 ");
		this.nvtuRs = db.get(" select pk_seq, ma, ten from erp_nhanvien where trangthai=1 ");

		String query = "";
		
		query =   "SELECT (CASE WHEN NH.MA = 'EIB' THEN 1 ELSE 2 END) AS STT ,  \n"+
				  " NH_CTY.SOTAIKHOAN, NH_CTY.SOTAIKHOAN + ' - ' + NH.TEN + ' - ' + CN.TEN + ', ' + TT.MA  AS TAIKHOAN \n"+
				  "FROM ERP_NGANHANG_CONGTY NH_CTY " + "INNER JOIN ERP_TIENTE TT ON TT.PK_SEQ = NH_CTY.TIENTE_FK \n"+
				  "INNER JOIN ERP_NGANHANG NH ON NH.PK_SEQ = NH_CTY.NGANHANG_FK \n"+
				  "INNER JOIN ERP_CHINHANH CN ON CN.PK_SEQ = NH_CTY.CHINHANH_FK \n"+
				  "WHERE NH_CTY.TRANGTHAI = 1 AND NH_CTY.CONGTY_FK = '" + this.ctyId + "' ";
		if (this.tienteId.length() > 0) {
			query = query + " AND TT.PK_SEQ = " + this.tienteId + " ";
		}

		query += " ORDER BY STT ";

		this.sotkRs = this.db.get(query);

		this.tienteRs = db.get("select pk_seq, ma + ', ' + ten as TEN from ERP_TIENTE ");

		String sql = "select pk_seq, ma + ', ' + ten as nppTen from KhachHang where trangthai = '1' and CONGTY_FK = "+ this.ctyId + " and npp_fk = " + this.nppdangnhap;

		if (this.ndId.equals("100002")) // PHẢI THU KHÁC --> LOAD NCC
		{
			sql = "select pk_seq, ma + ', ' + ten as nppTen from ERP_NHACUNGCAP where trangthai = '1' and CONGTY_FK = "	+ this.ctyId + " ";
			this.nppRs = db.get(sql);
		}
		
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

		if (this.nhomkhttId.trim().length() > 1 || this.bangkeId.trim().length() > 0 || this.nppId.trim().length() > 1 || this.nccId.length() > 0 || this.nvtuId.length() > 0 && this.htttId.length() > 0 && this.hoadonList.size() <= 0) 
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
						 "		FROM 	TraphacoDMS.dbo.ERP_HOADON HD 	\n"+
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

					// HOA DON PHE LIEU
					if (this.tienteId.equals("100000")) {
						query += "UNION ALL \n"+
						
								 "SELECT TT_HD.LOAIHOADON AS LOAIHD, '1' AS TIGIA  ,HDPL.PK_SEQ, '' AS MAHOPDONG, HDPL.kyhieuhoadon AS KYHIEU, HDPL.SOHOADON, HDPL.ngayhoadon , \n"+								 
								 "CAST( (ISNULL(PLSP.SOTIEN, 0) + ISNULL(PLSP.SOTIEN, 0)*HDPL.VAT/100 ) AS numeric(18,0)) AS SOTIENGOC, \n "+
								 "CAST( (ISNULL(PLSP.SOTIEN, 0) + ISNULL(PLSP.SOTIEN, 0)*HDPL.VAT/100 ) AS numeric(18,0)) - ISNULL(DATHU.TONGTHU, '0') AS SOTIENVND, \n"+
								 "0 AS SOTIENNT, \n"+
								 "ISNULL(TT_HD.SOTIENTT,0) + ISNULL(XKH.SOTIENTT,0) AS SOTIENTT, '100000' AS TTID, \n"+
								 "CONVERT(nvarchar(10), (dateadd(DAY, ISNULL(KH.THOIHANNO,0), HDPL.NGAYGHINHAN)),120 ) as NGAYDENHANTT \n"+
								 
								 "FROM ERP_HoaDonPheLieu HDPL \n"+
								 "INNER JOIN ERP_KHACHHANG KH ON HDPL.KHACHHANG_FK = KH.PK_SEQ \n"+
								 "LEFT JOIN ERP_THUTIEN_HOADON TT_HD ON TT_HD.HOADON_FK = HDPL.PK_SEQ and TT_HD.LOAIHOADON= 1  AND TT_HD.THUTIEN_FK = "+ this.id+ "\n"+
								 "INNER JOIN (SELECT hoadonphelieu_fk, SUM(thanhtien)AS SOTIEN \n"+
								 "			 FROM erp_hoadonphelieu_sanpham \n"+
								 "			 GROUP BY hoadonphelieu_fk )PLSP ON HDPL.pk_seq= PLSP.hoadonphelieu_fk \n"+
								 " LEFT JOIN	\n"+
								 " ( 	\n"+
								 "	SELECT TTHD.HOADON_FK, SUM(TTHD.SOTIENTT) AS TONGTHU \n"+
								 "	FROM ERP_THUTIEN_HOADON TTHD \n"+
								 "	INNER JOIN ERP_THUTIEN TT ON TTHD.THUTIEN_FK = TT.PK_SEQ \n"+
								 "	WHERE TT.TRANGTHAI != '2' AND TTHD.THUTIEN_FK !='"+ this.id	+ "'  AND TTHD.LOAIHOADON = 1 \n"+
								 "	GROUP BY HOADON_FK \n"+
								 " )DATHU ON HDPL.PK_SEQ = DATHU.HOADON_FK \n"+
								 "     LEFT JOIN ERP_XOAKHTRATRUOC_HOADON XKH ON HDPL.PK_SEQ = XKH.HOADON_FK AND XKH.XOAKHTRATRUOC_FK = "+ this.xoakhtratruocId +
								 " AND XKH.LOAIHD = 1 \n"+
								 " WHERE TT_HD.THUTIEN_FK = '"+ this.id	+ "' OR HDPL.PK_SEQ IN ( SELECT HOADON_FK  FROM ERP_XOAKHTRATRUOC_HOADON  WHERE XOAKHTRATRUOC_FK =  "+ this.xoakhtratruocId + " AND LOAIHD = 1)  \n";

					}
					// GIAM GIA HANG BAN
					query += "UNION ALL \n"+
							 "SELECT TT_HD.LOAIHOADON AS LOAIHD, TT.TIGIA AS TIGIA ,	GG.PK_SEQ, '' AS MAHOPDONG, GG.kyhieuhoadon AS KYHIEU, GG.SOHOADON, \n"+
							 "	GG.ngayhoadon , \n"+
							 "   CASE GG.TIENTE_FK WHEN '100000' THEN (ISNULL(GGSP.SOTIEN,0)+ ISNULL(GGSP.SOTIEN,0)*GG.VAT/100) \n"+
							 "	ELSE ISNULL(GGSP.SOTIEN,0) END AS SOTIENGOC, \n"+
							 "	CAST((CASE GG.TIENTE_FK WHEN '100000' THEN (ISNULL(GGSP.SOTIEN,0)+ ISNULL(GGSP.SOTIEN,0)*GG.VAT/100)- ISNULL(DATHU.TONGTHU, '0') \n"+
							 "        ELSE ISNULL(GGSP.SOTIEN,0)* TT.TIGIA - ISNULL(DATHU.TONGTHU, '0') END) as numeric(18,0) ) AS SOTIENVND, \n"+
							 "	ISNULL(GGSP.SOTIEN,0) - ISNULL(DATHU.TONGTHU, '0') AS SOTIENNT, \n"+
							 "	ISNULL(TT_HD.SOTIENTT,0) + ISNULL(XKH.SOTIENTT,0) AS SOTIENTT, GG.TIENTE_FK AS TTID, \n"+
							 "   CONVERT(nvarchar(10), (dateadd(DAY, ISNULL(KH.THOIHANNO,0), GG.NGAYGHINHAN)),120 ) as NGAYDENHANTT \n"+
							 " FROM  ERP_GIAMGIAHANGBAN GG  \n"+
							 " INNER JOIN ERP_KHACHHANG KH ON GG.KHACHHANG_FK = KH.PK_SEQ \n"+
							 " LEFT JOIN ERP_THUTIEN_HOADON TT_HD ON TT_HD.HOADON_FK = GG.PK_SEQ and TT_HD.LOAIHOADON = 2 and TT_HD.THUTIEN_FK = "+ this.id+ " \n"+
							 " LEFT JOIN ERP_THUTIEN TT ON TT_HD.THUTIEN_FK= TT.PK_SEQ \n"+
							 " INNER JOIN (SELECT GIAMGIA_FK, SUM(SOTIENTANGGIAM)AS SOTIEN \n"+
							 "			 FROM ERP_GIAMGIAHANGBAN_HOADON \n"+
							 "			 GROUP BY GIAMGIA_FK )GGSP ON GG.pk_seq= GGSP.GIAMGIA_FK \n"+
							 " LEFT JOIN	\n"+
							 " ( 	\n"+
							 "	SELECT TTHD.HOADON_FK, SUM(TTHD.SOTIENTT) AS TONGTHU \n"+
							 "	FROM ERP_THUTIEN_HOADON TTHD \n"+
							 "	INNER JOIN ERP_THUTIEN TT ON TTHD.THUTIEN_FK = TT.PK_SEQ \n"+
							 "	WHERE TT.TRANGTHAI != '2' AND TTHD.THUTIEN_FK !='"+ this.id+ "'  AND TTHD.LOAIHOADON = 2 \n"+
							 "	GROUP BY HOADON_FK \n"+
							 " )DATHU ON GG.PK_SEQ = DATHU.HOADON_FK \n"+
							 "     LEFT JOIN ERP_XOAKHTRATRUOC_HOADON XKH ON GG.PK_SEQ = XKH.HOADON_FK AND XKH.XOAKHTRATRUOC_FK = "+ this.xoakhtratruocId+ " AND XKH.LOAIHD = 2 \n"+
							 " WHERE  GG.TIENTE_FK = "+ this.tienteId+ 
							 " AND  (TT_HD.THUTIEN_FK = '"+ this.id+ "' OR GG.PK_SEQ IN ( SELECT HOADON_FK  FROM ERP_XOAKHTRATRUOC_HOADON  WHERE XOAKHTRATRUOC_FK = "+ this.xoakhtratruocId + " AND LOAIHD = 2)) \n";

					// KHACHHANGTRATRUOC
					if (this.xoakhtratruocId.trim().length() > 1) {
						query += " UNION ALL \n"
								+ "SELECT '3' as LOAIHD, tt.TIGIA, tt.PK_SEQ, '' AS MAHOPDONG, '' AS KYHIEU, CAST(tt.PK_SEQ as nvarchar(50)) AS SOHOADON, tt.NGAYCHUNGTU AS NGAYHOADON, \n"
								+ "        CASE WHEN tt.TIENTE_FK = '100000' THEN tt.THUDUOC*(-1) ELSE tt.THUDUOCNT*(-1) END as SOTIENGOC, \n"
								+ "        CAST( (XKH.tienchungtu*XKH.TIGIA *(-1)) as numeric(18,0)) AS SOTIENVND, \n"
								+ "        XKH.tienchungtu*(-1) AS SOTIENNT, XKH.tienthanhtoan*(-1) AS SOTIENTT, tt.TIENTE_FK AS TTID, \n"
								+ "       CONVERT(nvarchar(10), (dateadd(DAY, ISNULL(KH.THOIHANNO,0), TT.NGAYGHISO)),120 ) as NGAYDENHANTT \n"
								+ "FROM ERP_THUTIEN tt "
								+ "     INNER JOIN ERP_KHACHHANG KH ON TT.KHACHHANG_FK = KH.PK_SEQ \n"
								+ "     INNER JOIN  ERP_XOAKHTRATRUOC_CTTT XKH ON TT.PK_SEQ= XKH.cttt_fk \n"
								+ "WHERE tt.NOIDUNGTT_FK = '100001' AND tt.TRANGTHAI = '1' AND tt.TIENTE_FK = '"
								+ this.tienteId
								+ "' \n"
								+ "      and tt.KHACHHANG_FK "
								+ layKH
								+ " "
								+ "      and tt.PK_SEQ in (select CTTT_FK from ERP_XOAKHTRATRUOC_CTTT where XOAKHTRATRUOC_FK = '"
								+ this.xoakhtratruocId + "') \n";
					}
					// BÙ TRÙ CÔNG NỢ

					// LOAD PHIẾU BÙ TRỪ CÔNG NỢ (KHÁCH HÀNG NHẬN)
					query += " UNION ALL \n"
							+

							"SELECT '4' as LOAIHD, bt.TIGIA, bt.PK_SEQ, '' AS MAHOPDONG, '' AS KYHIEU, CAST(bt.PK_SEQ as nvarchar(50)) AS SOHOADON, bt.NGAYBUTRU AS NGAYHOADON, \n"
							+ "        CASE WHEN bt.TIENTE_FK = '100000' THEN bt.TONGTIEN*bt.tigia ELSE bt.TONGTIEN END as SOTIENGOC, \n"
							+ "        (BT.TONGTIEN - ISNULL(DATHANHTOAN.DATHANHTOAN,0))*(BT.TIGIA) AS SOTIENVND, \n"
							+ "        (BT.TONGTIEN - ISNULL(DATHANHTOAN.DATHANHTOAN,0)) AS SOTIENNT, isnull(TT_HD.SOTIENTT,0) AS DATHANHTOAN, bt.TIENTE_FK AS TTID,"
							+ "        '' as NGAYDENHANTT  \n"
							+ " FROM ERP_BUTRUKHACHHANG BT  "
							+ "      INNER JOIN NHAPHANPHOI KH ON BT.KH_NHANNO = KH.PK_SEQ "
							+ " 	   LEFT JOIN ERP_THUTIEN_HOADON TT_HD ON TT_HD.HOADON_FK = BT.PK_SEQ and TT_HD.LOAIHOADON = 4 and TT_HD.THUTIEN_FK = "
							+ this.id
							+ " "
							+ " 	   LEFT JOIN ERP_THUTIEN TT ON TT_HD.THUTIEN_FK= TT.PK_SEQ \n"
							+ "      LEFT JOIN ("
							+ "					SELECT TTHD.HOADON_FK , SUM(TTHD.SOTIENTT) AS DATHANHTOAN \n"
							+ "					FROM 	ERP_THUTIEN_HOADON TTHD \n"
							+ "							INNER JOIN ERP_THUTIEN TT ON TTHD.THUTIEN_FK = TT.PK_SEQ \n"
							+ "					WHERE 	TTHD.LOAIHOADON= '4' AND TT.TRANGTHAI NOT IN (2) AND TTHD.THUTIEN_FK !='"
							+ this.id
							+ "' "
							+ "					GROUP BY HOADON_FK \n"
							+ "				  ) DATHANHTOAN ON BT.pk_seq = DATHANHTOAN.HOADON_FK "
							+ " WHERE BT.TRANGTHAI = 1 AND BT.KH_NHANNO "
							+ layKH + " AND " + "		BT.TIENTE_FK = "
							+ this.tienteId + " AND TT_HD.THUTIEN_FK = "
							+ this.id + "";
					
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
						 "		FROM 	TraphacoDMS.dbo.ERP_HOADON HD 	\n"+
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

				// HOADONPHELIEU
				if (this.tienteId.equals("100000")) {
					query += " UNION ALL "+

							" (SELECT '1' AS LOAIHD , HDPHELIEU.TYGIA AS TIGIA,HDPHELIEU.PK_SEQ ,HDPHELIEU.MAHOPDONG, HDPHELIEU.KYHIEU, "
							+ "  HDPHELIEU.SOHOADON, HDPHELIEU.NGAYHOADON, "
							+ "   ISNULL(HDPHELIEU.SOTIENVND,0) AS SOTIENGOC, \n "
							+ "   ISNULL(HDPHELIEU.SOTIENVND,0) - ISNULL(DATHANHTOAN.DATHANHTOAN,0) AS SOTIENVND, "
							+ "  0 AS SOTIENNT, 0 AS DATHANHTOAN, '100000' AS TTID, HDPHELIEU.NGAYDENHANTT \n"
							+ " FROM "
							+ " ("
							+ " SELECT '1' AS TYGIA,PL.PK_SEQ,'' AS MAHOPDONG, PL.KYHIEUHOADON AS KYHIEU, PL.SOHOADON, PL.NGAYHOADON, CAST( (PLSP.SOTIENVND + PLSP.SOTIENVND*PL.VAT/100) AS NUMERIC(18,0) ) AS SOTIENVND,"
							+ "        0 AS SOTIENNT,0 AS DATHANHTOAN ,'100000' AS TTID , CONVERT(nvarchar(10), (dateadd(DAY, ISNULL(KH.THOIHANNO,0), PL.NGAYGHINHAN)),120 ) as NGAYDENHANTT \n"
							+ " FROM ERP_HOADONPHELIEU PL "
							+ "    INNER JOIN ERP_KHACHHANG KH ON PL.KHACHHANG_FK = KH.PK_SEQ \n"
							+ "    INNER JOIN \n"
							+ "    (SELECT HOADONPHELIEU_FK, SUM(THANHTIEN)AS SOTIENVND \n"
							+ "     FROM ERP_HOADONPHELIEU_SANPHAM \n"
							+ "     GROUP BY HOADONPHELIEU_FK)AS PLSP  ON PL.PK_SEQ= PLSP.HOADONPHELIEU_FK \n"
							+ " WHERE PL.KHACHHANG_FK " + layKH
							+ " and PL.TRANGTHAI = '1' AND ( ( PL.NGAYHOADON <='2015-12-31'  ) OR PL.NGAYHOADON > '2015-12-31' ) \n";
					if (this.id.length() > 0) {
						query += "AND PL.PK_SEQ NOT IN (SELECT HOADON_FK FROM ERP_THUTIEN_HOADON WHERE THUTIEN_FK = '"+ this.id + "') \n";

					}
					query += " )HDPHELIEU \n "
							+ " LEFT JOIN ( \n"
							+ "	SELECT HOADON_FK, SUM(ISNULL(DATHANHTOAN, 0)) AS DATHANHTOAN \n"
							+ "	FROM  \n"
							+ "	( 	\n"
							+ "		SELECT TTHD.HOADON_FK, SUM(TTHD.SOTIENTT) AS DATHANHTOAN \n"
							+ "		FROM ERP_XOAKHTRATRUOC_HOADON TTHD \n"
							+ "		INNER JOIN ERP_XOAKHTRATRUOC TT ON TTHD.XOAKHTRATRUOC_FK = TT.PK_SEQ \n"
							+ "		WHERE TT.TRANGTHAI NOT IN (2) AND ISNULL(TT.LOAIXOATRATRUOC,0) = '0' "
							+ "		AND  TT.NGAYGHISO >= '2014-08-01' AND TTHD.LOAIHD = '1' \n"
							+ "		GROUP BY HOADON_FK \n"
							+ "                             \n"
							+ "       UNION ALL                      \n"
							+ "		SELECT TTHD.HOADON_FK , SUM(TTHD.SOTIENTT) AS DATHANHTOAN \n"
							+ "		FROM ERP_THUTIEN_HOADON TTHD \n"
							+ "		INNER JOIN ERP_THUTIEN TT ON TTHD.THUTIEN_FK = TT.PK_SEQ \n"
							+ "		WHERE TTHD.LOAIHOADON= '1' AND TT.TRANGTHAI NOT IN (2)\n";

					if (this.id.trim().length() > 0) {
						query += " 		AND TT.PK_SEQ  != '" + this.id + "' \n";
					}

					query += " 		GROUP BY HOADON_FK \n"
							+

							"		UNION ALL \n "
							+

							"		SELECT BT_KH.HOADON_FK, SUM(BT_KH.XOANO) AS SOTIENBUTRU  \n"
							+ "		FROM   ERP_BUTRUKHACHHANG BT INNER JOIN ERP_BUTRUKHACHHANG_CHITIET BT_KH ON BT.PK_SEQ =BT_KH.BTKH_FK \n "
							+ "		WHERE  BT.TRANGTHAI NOT IN (2) AND BT_KH.LOAIHD = 1  AND BT.KH_CHUYENNO "
							+ layKH
							+ "  \n "
							+ "		GROUP BY BT_KH.HOADON_FK \n"
							+

							"	) HOADONDATT  \n"
							+ "	GROUP BY HOADON_FK "
							+ ")DATHANHTOAN ON HDPHELIEU.PK_SEQ = DATHANHTOAN.HOADON_FK \n"
							+ " WHERE ISNULL(HDPHELIEU.SOTIENVND,0) - ISNULL(DATHANHTOAN.DATHANHTOAN, '0') != 0  \n"
							+ ")";
				}

				// HOA DON : GIAM /TANG GIA HANG BAN
				query += " UNION ALL " 
						+ " (SELECT '2' AS LOAIHD , '"
						+ this.tigia.replaceAll(",", "")
						+ "' as TIGIA ,GGHANGBAN.PK_SEQ ,GGHANGBAN.MAHOPDONG, GGHANGBAN.KYHIEU, "
						+ "  GGHANGBAN.SOHOADON, GGHANGBAN.NGAYHOADON, "
						+ "  ISNULL(GGHANGBAN.SOTIENVND,0) AS SOTIENGOC, \n "
						+ "  CAST((ISNULL(GGHANGBAN.SOTIENVND,0)*"
						+ this.tigia.replaceAll(",", "")
						+ " - ISNULL(DATHANHTOAN.DATHANHTOAN,0)) as numeric(18,0) )  AS SOTIENVND, "
						+ "  ISNULL(GGHANGBAN.SOTIENVND,0) - ISNULL(DATHANHTOAN.DATHANHTOAN,0) AS SOTIENNT, 0 AS DATHANHTOAN, GGHANGBAN.TTID,"
						+ "  GGHANGBAN.NGAYDENHANTT \n"
						+ " FROM "
						+ " ("
						+ " SELECT GG.PK_SEQ,'' AS MAHOPDONG, GG.KYHIEUHOADON AS KYHIEU, GG.SOHOADON, GG.NGAYHOADON, "
						+ "       (CASE GG.TIENTE_FK  WHEN '100000' THEN (GGSP.SOTIENVND + GGSP.SOTIENVND*GG.VAT/100) "
						+ "                           ELSE GGSP.SOTIENVND END) AS SOTIENVND , "
						+ "       0 AS SOTIENNT,0 AS DATHANHTOAN ,GG.TIENTE_FK AS TTID, CONVERT(nvarchar(10), (dateadd(DAY, ISNULL(KH.THOIHANNO,0), GG.NGAYGHINHAN)),120 ) as NGAYDENHANTT \n"
						+ " FROM ERP_GIAMGIAHANGBAN GG "
						+ "    INNER JOIN ERP_KHACHHANG KH ON GG.KHACHHANG_FK = KH.PK_SEQ \n"
						+ "    INNER JOIN \n"
						+ "    (SELECT GIAMGIA_FK, SUM(SOTIENTANGGIAM)AS SOTIENVND \n"
						+ "     FROM ERP_GIAMGIAHANGBAN_HOADON \n"
						+ "     GROUP BY GIAMGIA_FK)AS GGSP  ON GG.PK_SEQ= GIAMGIA_FK \n"
						+ " WHERE GG.KHACHHANG_FK " + layKH
						+ " and GG.TRANGTHAI = '1' "
						+ "AND GG.NGAYGHINHAN >= '2014-08-01'  ";

				if (this.id.length() > 0) {
					query += "AND GG.PK_SEQ NOT IN (SELECT HOADON_FK FROM ERP_THUTIEN_HOADON WHERE THUTIEN_FK = '"
							+ this.id + "') \n";

				}
				query += " )GGHANGBAN \n "
						+ " LEFT JOIN ( \n"
						+ "	SELECT HOADON_FK, SUM(ISNULL(DATHANHTOAN, 0)) AS DATHANHTOAN \n"
						+ "	FROM  \n"
						+ "	( 	\n"
						+ "		SELECT TTHD.HOADON_FK , SUM(TTHD.SOTIENTT) AS DATHANHTOAN \n"
						+ "		FROM ERP_THUTIEN_HOADON TTHD \n"
						+ "		INNER JOIN ERP_THUTIEN TT ON TTHD.THUTIEN_FK = TT.PK_SEQ \n"
						+ "		WHERE TTHD.LOAIHOADON= '2' AND TT.TRANGTHAI NOT IN (2)\n";

				if (this.id.trim().length() > 0) {
					query += " 		AND TT.PK_SEQ  != '" + this.id + "' \n";
				}

				query += " 	GROUP BY HOADON_FK \n"
						+

						"		UNION ALL \n "
						+

						"		SELECT BT_KH.HOADON_FK, SUM(BT_KH.XOANO) AS SOTIENBUTRU  \n"
						+ "		FROM   ERP_BUTRUKHACHHANG BT INNER JOIN ERP_BUTRUKHACHHANG_CHITIET BT_KH ON BT.PK_SEQ =BT_KH.BTKH_FK \n "
						+ "		WHERE  BT.TRANGTHAI NOT IN (2) AND BT_KH.LOAIHD = 2  AND BT.KH_CHUYENNO  "
						+ layKH
						+ "  \n "
						+ "		GROUP BY BT_KH.HOADON_FK \n"
						+ "	) HOADONDATT  \n"
						+ "	GROUP BY HOADON_FK "
						+ ")DATHANHTOAN ON GGHANGBAN.PK_SEQ = DATHANHTOAN.HOADON_FK \n"
						+ " WHERE GGHANGBAN.SOTIENVND - ISNULL(DATHANHTOAN.DATHANHTOAN, '0') != 0 OR GGHANGBAN.SOTIENVND - ISNULL(DATHANHTOAN.DATHANHTOAN, '0') != 0"
						+ "   AND GGHANGBAN.TTID = " + this.tienteId + " \n"
						+ ")";

				// LOAD PHIEU THU TIEN: KHACH HANG TRA TRUOC
				query += " UNION ALL \n"
						+

						"SELECT '3' as LOAIHD, tt.TIGIA, tt.PK_SEQ, '' AS MAHOPDONG, '' AS KYHIEU, CAST(tt.PK_SEQ as nvarchar(50)) AS SOHOADON, tt.NGAYCHUNGTU AS NGAYHOADON, \n"
						+ "        CASE WHEN tt.TIENTE_FK = '100000' THEN tt.THUDUOC*(-1) ELSE tt.THUDUOCNT*(-1) END as SOTIENGOC, \n"
						+ "        (TT.THUDUOC - ISNULL(DAXOANO.SOTIENVND,0))*(-1) AS SOTIENVND, \n"
						+ "        (TT.THUDUOCNT - ISNULL(DAXOANO.SOTIENNT,0))*(-1) AS SOTIENNT, 0 AS DATHANHTOAN, tt.TIENTE_FK AS TTID,"
						+ "        CONVERT(nvarchar(10), (dateadd(DAY, ISNULL(KH.THOIHANNO,0), TT.NGAYGHISO)),120 ) as NGAYDENHANTT  \n"
						+ "FROM ERP_THUTIEN tt "
						+ "    INNER JOIN ERP_KHACHHANG KH ON TT.KHACHHANG_FK = KH.PK_SEQ \n"
						+ "    LEFT JOIN \n"
						+ "     ( SELECT CT.CTTT_FK, SUM(CT.TIENTHANHTOAN) AS SOTIENNT ,SUM(CT.TIENTHANHTOAN*CT.TIGIA) AS SOTIENVND  \n"
						+ "       FROM ERP_XOAKHTRATRUOC_CTTT CT INNER JOIN ERP_XOAKHTRATRUOC XKH ON CT.XOAKHTRATRUOC_FK = XKH.PK_SEQ \n"
						+ "       WHERE XKH.TRANGTHAI != 2 AND XKH.LOAIXOATRATRUOC = '0' \n"
						+ "       GROUP BY CT.CTTT_FK \n"
						+

						"		UNION ALL \n "
						+

						"		SELECT BT_KH.HOADON_FK, SUM(BT_KH.XOANO) AS SOTIENBUTRU, SUM(BT_KH.XOANO*BT.TIGIA)  AS SOTIENVND  \n"
						+ "		FROM   ERP_BUTRUKHACHHANG BT INNER JOIN ERP_BUTRUKHACHHANG_CHITIET BT_KH ON BT.PK_SEQ =BT_KH.BTKH_FK \n "
						+ "		WHERE  BT.TRANGTHAI NOT IN (2) AND BT_KH.LOAIHD = 3 AND BT.KH_CHUYENNO  "
						+ layKH
						+ "  \n "
						+ "		GROUP BY BT_KH.HOADON_FK \n"
						+

						"      ) DAXOANO ON tt.PK_SEQ = DAXOANO.CTTT_FK \n"
						+ "WHERE tt.NOIDUNGTT_FK = '100001' AND tt.TRANGTHAI = '1' AND tt.TIENTE_FK = '"+ this.tienteId + "' \n" 
						+ "      and tt.KHACHHANG_FK " + layKH + " "
						+ "      AND  ( ( TT.NGAYCHUNGTU <= '2015-12-31' ) OR TT.NGAYCHUNGTU > '2015-12-31' ) AND (TT.THUDUOC - ISNULL(DAXOANO.SOTIENVND,0))*(-1) != 0 \n";
				if (this.id.trim().length() > 0) {
					query += "     and tt.PK_SEQ not in (SELECT CTTT_FK FROM ERP_XOAKHTRATRUOC_CTTT where XOAKHTRATRUOC_FK = "
							+ this.xoakhtratruocId + " )";
				}

				// LOAD PHIẾU BÙ TRỪ CÔNG NỢ (KHÁCH HÀNG NHẬN)
				query += " UNION ALL \n"+

						"SELECT '4' as LOAIHD, bt.TIGIA, bt.PK_SEQ, '' AS MAHOPDONG, '' AS KYHIEU, CAST(bt.PK_SEQ as nvarchar(50)) AS SOHOADON, bt.NGAYBUTRU AS NGAYHOADON, \n"+
						"        CASE WHEN bt.TIENTE_FK = '100000' THEN bt.TONGTIEN*bt.tigia ELSE bt.TONGTIEN END as SOTIENGOC, \n"+
						"        (BT.TONGTIEN - ISNULL(DATHANHTOAN.DATHANHTOAN,0))*(BT.TIGIA) AS SOTIENVND, \n"+
						"        (BT.TONGTIEN - ISNULL(DATHANHTOAN.DATHANHTOAN,0)) AS SOTIENNT, 0 AS DATHANHTOAN, bt.TIENTE_FK AS TTID, \n"+
						"        '' as NGAYDENHANTT  \n"+
						" FROM ERP_BUTRUKHACHHANG BT  \n"+
						"      INNER JOIN NHAPHANPHOI KH ON BT.KH_NHANNO = KH.PK_SEQ \n"+
						"      LEFT JOIN ( \n"+
						"					SELECT TTHD.HOADON_FK , SUM(TTHD.SOTIENTT) AS DATHANHTOAN \n"+
						"					FROM 	ERP_THUTIEN_HOADON TTHD \n"+
						"							INNER JOIN ERP_THUTIEN TT ON TTHD.THUTIEN_FK = TT.PK_SEQ \n"+
						"					WHERE TTHD.LOAIHOADON= '4' AND TT.TRANGTHAI NOT IN (2) \n"+
						"					GROUP BY TTHD.HOADON_FK \n"+
						"				  ) DATHANHTOAN ON BT.pk_seq = DATHANHTOAN.HOADON_FK \n"+
						" WHERE BT.TRANGTHAI = 1 AND (BT.TONGTIEN - ISNULL(DATHANHTOAN.DATHANHTOAN,0))*(BT.TIGIA) > 0 AND BT.KH_NHANNO " + layKH +
						" AND BT.NGAYBUTRU > '2015-12-31' ";
				if (this.id.length() > 0) {
					query += "AND BT.PK_SEQ NOT IN (SELECT HOADON_FK FROM ERP_THUTIEN_HOADON WHERE THUTIEN_FK = '"+ this.id + "' AND LOAIHOADON = '4') \n";

				}

				query += " ORDER BY LOAIHD, NGAYHOADON ASC ";

			} else if (this.ndId.equals("100003")) { // THU HOI TAM UNG

				if (this.DoiTuongTamUng.equals("1")) // NHA CUNG CAP
				{
					if (this.id.length() > 0) {
						query += " SELECT ISNULL(TT.TIGIA,100000) AS TIGIA, TT_HD.HOADON_FK AS PK_SEQ, 'TU' AS KYHIEU, TT.PK_SEQ AS SOHOADON, \n"
								+ "        TT.NGAYCHUNGTU AS NGAYHOADON, \n"
								+ "        CASE WHEN TT.TIENTE_FK= 100000 THEN  (TT_HD.SOTIENAVAT) ELSE (TT_HD.SOTIENAVAT/HD.TIGIA) END AS SOTIENGOC , \n"
								+ "        TT_HD.SOTIENAVAT AS SOTIENVND , \n"
								+ "        CASE WHEN TT.TIENTE_FK= 100000 THEN  0 ELSE (TT_HD.SOTIENAVAT/HD.TIGIA) END AS SOTIENNT , \n"
								+ "        TT_HD.SOTIENTT, TT.TIENTE_FK AS TTID, CONVERT(nvarchar(10), (dateadd(DAY, ISNULL(NCC.THOIHANNO,0), HD.NGAYGHINHAN)),120 ) as NGAYDENHANTT  \n"
								+ " FROM ERP_THUTIEN_HOADON TT_HD INNER JOIN ERP_THUTIEN TT ON TT_HD.THUTIEN_FK= TT.PK_SEQ"
								+ "      INNER JOIN ERP_THANHTOANHOADON HD ON TT_HD.HOADON_FK = HD.PK_SEQ"
								+ "      INNER JOIN ERP_NHACUNGCAP NCC ON NCC.PK_SEQ = HD.NCC_FK \n"
								+ " WHERE TT.PK_SEQ= "+ this.id	+ " \n"
								+ "UNION ";
					}

					query += " SELECT ISNULL(TT.TIGIA,100000) AS TIGIA, TT.PK_SEQ, 'TU' AS KYHIEU, TT.PK_SEQ AS SOHOADON, TT.NGAYGHINHAN AS NGAYHOADON, \n"
							+ "        (TTHD.SOTIENTT - (ISNULL(DAXOATU.SOTIEN_XOATU,0) * ISNULL(DAXOATU.TIGIA,0)) ) AS SOTIENGOC, \n "
							+ " 		 (TTHD.SOTIENTT - (ISNULL(DAXOATU.SOTIEN_XOATU,0) * ISNULL(DAXOATU.TIGIA,0)) - (ISNULL(DATHANHTOAN.SOTIENTT,0)* ISNULL(DATHANHTOAN.TIGIA,0)) ) AS SOTIENVND, \n"
							+ "        CASE WHEN "
							+ this.tienteId
							+ "= 100000 THEN  0  ELSE (TTHD.SOTIENTT - ISNULL(DAXOATU.SOTIEN_XOATU,0) - ISNULL(DATHANHTOAN.SOTIENTT,0)) END AS SOTIENNT , \n"
							+ "        0 AS SOTIENTT, TT.TIENTE_FK AS TTID, CONVERT(nvarchar(10), (dateadd(DAY, ISNULL(NCC.THOIHANNO,0), TT.NGAYGHINHAN)),120 ) as NGAYDENHANTT \n"
							+ " FROM ERP_THANHTOANHOADON TT \n"
							+ "      INNER JOIN  "
							+ "      (SELECT THANHTOANHD_FK, SUM(SOTIENTT) as SOTIENTT \n"
							+ "       FROM ERP_THANHTOANHOADON_HOADON \n"
							+ "       WHERE  LOAIHD ='1' \n"
							+ "       GROUP BY THANHTOANHD_FK ) TTHD ON TTHD.THANHTOANHD_FK = TT.PK_SEQ \n"
							+ "      INNER JOIN ERP_NHACUNGCAP NCC ON NCC.PK_SEQ = TT.NCC_FK \n"
							+ "      LEFT JOIN \n"
							+ "      (SELECT TT_HD.HOADON_FK, TT.TIGIA, SUM(TT_HD.SOTIENTT) AS SOTIENTT \n"
							+ "		 FROM ERP_THUTIEN TT INNER JOIN ERP_THUTIEN_HOADON TT_HD ON TT.PK_SEQ = TT_HD.THUTIEN_FK \n"
							+ "       WHERE  TT.NCC_FK= "
							+ this.nccId
							+ "  AND TT.TRANGTHAI!= 2 AND TT.TIENTE_FK = "
							+ this.tienteId
							+ " \n"
							+ "		 GROUP BY TT_HD.HOADON_FK, TT.TIGIA) DATHANHTOAN ON TT.PK_SEQ= DATHANHTOAN.HOADON_FK \n"
							+ "      LEFT JOIN \n"
							+ "      (SELECT XTT_HD.HOADON_FK, TTHD.TIGIA, SUM(XTT_HD.SOTIENTT) AS SOTIEN_XOATU \n"
							+ "		 FROM ERP_XOAKHTRATRUOC XTT INNER JOIN ERP_XOAKHTRATRUOC_HOADON XTT_HD ON XTT.PK_SEQ=XTT_HD.xoakhtratruoc_fk \n"
							+ "            INNER JOIN ERP_THANHTOANHOADON TTHD ON TTHD.PK_SEQ= XTT_HD.HOADON_FK \n"
							+ "       WHERE XTT.LOAIXOATRATRUOC = '1' AND XTT.NCC_FK= "
							+ this.nccId
							+ "  AND XTT.TRANGTHAI= 1 AND XTT.TIENTE_FK = "
							+ this.tienteId
							+ " \n"
							+ "		 GROUP BY XTT_HD.HOADON_FK, TTHD.TIGIA) DAXOATU ON TT.PK_SEQ= DAXOATU.HOADON_FK \n"
							+ " WHERE TT.TRANGTHAI='1'  AND TT.TIENTE_FK= "
							+ this.tienteId
							+ " AND NCC_FK = "
							+ this.nccId
							+ " "
							+ " AND (TT.SOTIENTT - (ISNULL(DAXOATU.SOTIEN_XOATU,0) * ISNULL(DAXOATU.TIGIA,0)) - (ISNULL(DATHANHTOAN.SOTIENTT,0)* ISNULL(DATHANHTOAN.TIGIA,0)) ) > 0 "
							+ " AND TT.NGAYGHINHAN > '2015-12-31'  ";
					if (this.id.trim().length() > 0) {
						query += " AND TT.PK_SEQ NOT IN (SELECT HOADON_FK FROM ERP_THUTIEN_HOADON WHERE THUTIEN_FK= "
								+ this.id
								+ ") "
								+ " AND TT.NGAYGHINHAN > '2015-12-31' ";
					}

				} else // NHAN VIEN
				{
					if (this.id.length() > 0) {
						query += " SELECT ISNULL(TT.TIGIA,100000) AS TIGIA, TT_HD.HOADON_FK AS PK_SEQ, 'TU' AS KYHIEU, TT.PK_SEQ AS SOHOADON, "
								+ "        TT.NGAYCHUNGTU AS NGAYHOADON, \n"
								+ "        CASE WHEN TT.TIENTE_FK= 100000 THEN (TT_HD.SOTIENAVAT) ELSE (TT_HD.SOTIENAVAT/HD.TIGIA) END AS SOTIENGOC , \n"
								+ "        TT_HD.SOTIENAVAT AS SOTIENVND , \n"
								+ "        CASE WHEN TT.TIENTE_FK= 100000 THEN  0 ELSE (TT_HD.SOTIENAVAT/HD.TIGIA) END AS SOTIENNT , \n"
								+ "        TT_HD.SOTIENTT, TT.TIENTE_FK AS TTID, '' AS NGAYDENHANTT  \n"
								+ " FROM ERP_THUTIEN_HOADON TT_HD INNER JOIN ERP_THUTIEN TT ON TT_HD.THUTIEN_FK= TT.PK_SEQ"
								+ "      INNER JOIN ERP_THANHTOANHOADON HD ON TT_HD.HOADON_FK = HD.PK_SEQ \n"
								+ " WHERE TT.PK_SEQ= "
								+ this.id
								+ " \n"
								+ "UNION ";
					}

					query += " SELECT ISNULL(TT.TIGIA,100000) AS TIGIA, TT.PK_SEQ, 'TU' AS KYHIEU, TT.PK_SEQ AS SOHOADON, TT.NGAYGHINHAN AS NGAYHOADON, \n"
							+"   CASE WHEN TT.NGAYGHINHAN = '2015-04-30' THEN TT.SOTIENHD - ISNULL(DACANTRU.CANTRU,0) - (ISNULL(DAXOATU.SOTIEN_XOATU,0) * ISNULL(DAXOATU.TIGIA,0)) \n"
						    +"   ELSE(TTHD.SOTIENTT - ISNULL(DACANTRU.CANTRU,0) - (ISNULL(DAXOATU.SOTIEN_XOATU,0) * ISNULL(DAXOATU.TIGIA,0))) END AS SOTIENGOC, \n"
						    +"   CASE WHEN TT.NGAYGHINHAN = '2015-04-30' THEN TT.SOTIENHD - ISNULL(DACANTRU.CANTRU,0) - (ISNULL(DAXOATU.SOTIEN_XOATU,0) * ISNULL(DAXOATU.TIGIA,0))  \n"
					  		+"   ELSE  (TTHD.SOTIENTT - ISNULL(DACANTRU.CANTRU,0) - (ISNULL(DAXOATU.SOTIEN_XOATU,0) * ISNULL(DAXOATU.TIGIA,0)) - (ISNULL(DATHANHTOAN.SOTIENTT,0)* ISNULL(DATHANHTOAN.TIGIA,0)) ) END AS SOTIENVND, \n"
							+ "        CASE WHEN "
							+ this.tienteId
							+ "= 100000 THEN  0  ELSE (TTHD.SOTIENTT - ISNULL(DACANTRU.CANTRU,0) - ISNULL(DAXOATU.SOTIEN_XOATU,0) - ISNULL(DATHANHTOAN.SOTIENTT,0)) END AS SOTIENNT , \n"
							+ "        0 AS SOTIENTT, TT.TIENTE_FK AS TTID, '' AS NGAYDENHANTT \n"
							+ " FROM ERP_THANHTOANHOADON TT \n"
							+ "      LEFT JOIN  "
							+ "      (SELECT THANHTOANHD_FK, SUM(SOTIENTT) as SOTIENTT \n"
							+ "       FROM ERP_THANHTOANHOADON_HOADON \n"
							+ "       WHERE LOAIHD ='1'  \n"
							+ "       GROUP BY THANHTOANHD_FK ) TTHD ON TTHD.THANHTOANHD_FK = TT.PK_SEQ \n"
							+ "      LEFT JOIN \n"
							+ "      (SELECT TT_HD.HOADON_FK, TT.TIGIA, SUM(TT_HD.SOTIENTT) AS SOTIENTT \n"
							+ "		 FROM ERP_THUTIEN TT INNER JOIN ERP_THUTIEN_HOADON TT_HD ON TT.PK_SEQ = TT_HD.THUTIEN_FK \n"
							+ "       WHERE  TT.NHANVIEN_FK= "
							+ this.nvtuId
							+ "  AND TT.TRANGTHAI!= 2 AND TT.TIENTE_FK = "
							+ this.tienteId
							+ " \n"
							+ "		 GROUP BY TT_HD.HOADON_FK, TT.TIGIA) DATHANHTOAN ON TT.PK_SEQ= DATHANHTOAN.HOADON_FK \n"
							+ "      LEFT JOIN \n"
							+ "      (SELECT XTT_HD.HOADON_FK, TTHD.TIGIA, SUM(XTT_HD.SOTIENTT) AS SOTIEN_XOATU \n"
							+ "		 FROM ERP_XOAKHTRATRUOC XTT INNER JOIN ERP_XOAKHTRATRUOC_HOADON XTT_HD ON XTT.PK_SEQ=XTT_HD.xoakhtratruoc_fk \n"
							+ "            INNER JOIN ERP_THANHTOANHOADON TTHD ON TTHD.PK_SEQ= XTT_HD.HOADON_FK \n"
							+ "       WHERE XTT.LOAIXOATRATRUOC = '1' AND XTT.NHANVIEN_FK= "
							+ this.nvtuId
							+ "  AND XTT.TRANGTHAI= 1 AND XTT.TIENTE_FK = "
							+ this.tienteId
							+ " \n"
							+ "		 GROUP BY XTT_HD.HOADON_FK, TTHD.TIGIA) DAXOATU ON TT.PK_SEQ= DAXOATU.HOADON_FK \n"
							+ "      LEFT JOIN \n"
							+ "       (SELECT  DN_CT.THANHTOANHOADON_FK AS HOADON_FK , SUM(DN_CT.SOTIENCANTRU) AS CANTRU \n"
							+ "        FROM ERP_DENGHITT_THANHTOANHOADON DN_CT INNER JOIN ERP_MUAHANG DN ON DN_CT.DENGHITT_FK = DN.PK_SEQ \n"
							+ "        WHERE DN.LOAIHANGHOA_FK='2' and DN.TYPE = 0  AND DN.TRANGTHAI NOT IN (3,4)  AND DN.NHANVIEN_FK = "
							+ this.nvtuId
							+ " \n"
							+ "        GROUP BY  DN_CT.THANHTOANHOADON_FK) DACANTRU ON TT.PK_SEQ = DACANTRU.HOADON_FK \n"
							+

							" WHERE TT.TRANGTHAI='1'  AND TT.TIENTE_FK= "
							+ this.tienteId
							+ " AND TT.NHANVIEN_FK = "+ this.nvtuId+ " "
							+ " AND ( ISNULL(TTHD.SOTIENTT,0) - ISNULL(DACANTRU.CANTRU,0) - (ISNULL(DAXOATU.SOTIEN_XOATU,0) * ISNULL(DAXOATU.TIGIA,0)) - (ISNULL(DATHANHTOAN.SOTIENTT,0)* ISNULL(DATHANHTOAN.TIGIA,0)) > 0 \n"
							+ "		 OR (TT.NGAYGHINHAN = '2015-04-30' AND ISNULL(TT.SOTIENHD,0) - ISNULL(DACANTRU.CANTRU,0) - (ISNULL(DAXOATU.SOTIEN_XOATU,0) * ISNULL(DAXOATU.TIGIA,0)) > 0 ) ) \n"; 
					if (this.id.trim().length() > 0) {
						query += " AND TT.PK_SEQ NOT IN (SELECT HOADON_FK FROM ERP_THUTIEN_HOADON WHERE THUTIEN_FK= "+ this.id + ") "
							   + "  AND TT.NGAYGHINHAN > '2015-12-31' ";
					}

				}
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

	
	public String getNPPChinhanhId() {
		
		return this.nppChinhanhId;
	}

	
	public void setNPPChinhanhId(String nppchinhanhId) {
		
		this.nppChinhanhId = nppchinhanhId;
	}

	
	public String getIsChuyenCN() {
		
		return this.isChuyenCN;
	}

	
	public void setIsChuyenCN(String isChuyenCN) {
		
		this.isChuyenCN  = isChuyenCN;
	}

	
	public String getIsthuhoCN() {
		
		return this.isthuhoCN;
	}

	
	public void setIsthuhoCN(String isthuhoCN) {
		
		this.isthuhoCN = isthuhoCN;
	}
}
