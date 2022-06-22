package geso.dms.distributor.beans.thanhtoanhoadon.imp;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import geso.dms.distributor.util.Utility;
import geso.dms.distributor.db.sql.dbutils;
import geso.dms.distributor.beans.thanhtoanhoadon.IErpThanhtoanhoadon;
import geso.dms.distributor.beans.thanhtoanhoadon.IHoadon;

public class ErpThanhtoanhoadon implements IErpThanhtoanhoadon 
{
	String userId;
	String id;
	String ngayghinhan;
	String ctyId;
	String dungchos;
	String nccId;
	String diachi;
	ResultSet nccRs;
	String htttId;
	ResultSet htttRs;
	String nganhangId;
	ResultSet nganhangRs;
	String chinhanhId;
	ResultSet chinhanhRs;
	
	String nganhang_tpId;
	String chinhanh_tpId;
	
	String nppdangnhap ;
	
	ResultSet sotkRs;	
	ResultSet sotkRs_tp;
	
	String sotaikhoan;
	String sotaikhoan_tp;
	String noidungtt;
	
	String tthdCurrent = "";
	String tigia;
	String dinhkhoanno = "";
	String dinhkhoannoId = "";
	String trichphi;
	
	String hoadonId;
	List<IHoadon> hoadonList;
	
	String tienteId;
	ResultSet tienteRs;
	
	String msg;
	dbutils db;
	
	String DoiTuongTamUng;
	String LoaiThanhToan;
	String TenHienThi="";
	
	String NhomNCCCN;
	
	String nhomNCCCNId;
	ResultSet nhomNCCRs;
	
	String DoiTuongChiPhiKhac;
	
	String prefix;
	
	String DoiTuongDinhKhoan = "";
	
	String MaTenDoiTuongDinhKhoan = "";
	
	String doituongdinhkhoanId;
	String sotientt;
	String sotienHD;
	String pnganhang;
	String thueVAT;
	String chenhlech;
	
	String sotienttNT;
	String sotienHDNT;
	String pnganhangNT;
	String thueVATNT;
	String chenhlechVND;
	
	String mahoadon;
	String mauhoadon;
	String kyhieu;
	String sohoadon;
	String ngayhoadon;
	String tenNCC;
	String mst;
	String tienhang;
	String thuesuat;
	String tienthue;
	String nhId_VAT;
	String cnId_VAT;
	String tenNCC_VAT;
	
	String checkThanhtoantuTV;
	
	String chungtukemtheo;
	
	String khachhangId ;
	ResultSet khachhangRs;
	
	String NhanvienId;
	ResultSet NhanvienRs;
	
	String bophanId ;
	String bophanTen;
	
	String isktt;
	
	//CHI PHÍ KHÁC
	
	String[] Mahd;
	String[] Mauhd;
	String[] Kyhieuhd;
	String[] Sohd;
	String[] Ngayhd;
	String[] TenNCChd;
	String[] MSThd;
	String[] Tienhanghd;
	String[] Thuesuathd;	
	String[] Tienthuehd;	
	String[] Diengiaihd;	
	
	//Phòng ban
	String[] PhongBanIds;
	
	ResultSet PhongBanRs;
	
	//Kênh bán hàng
	String[] KenhbhIds;
	
	ResultSet KenhBhRs;
	
	//Tỉnh thành
	String[] TinhThanhIds;
	
	ResultSet TinhThanhRs;
	
	//Sản phẩm
	String[] SanPhamIds;
	
	ResultSet SanphamRs;	
	
	//Mã vụ việc
	String[] MavvIds;
	
	ResultSet MavvRs;
	
	//Địa bàn
	String[] DiabanIds;
	
	ResultSet DiabanRs;
	
	//Bệnh viện
	String[] BenhvienIds;
	
	ResultSet BenhvienRs;
		
	ResultSet TaiKhoanKTRs; 
	
	String[] TaiKhoanIds, dcIds, loais;
	
	int count;
	
	public ErpThanhtoanhoadon()
	{
		this.id = "";
		this.ctyId = "";
		this.ngayghinhan = this.getDateTime();
		this.nccId = "";
		this.diachi = "";
		this.htttId = "";
		this.nganhangId = "";
		this.chinhanhId = "";
		this.sotaikhoan = "";
		this.nganhang_tpId = "";
		this.chinhanh_tpId = "";
		this.sotaikhoan_tp = "";
		this.noidungtt = "";
		this.hoadonId = "";
		this.NhanvienId="";
		this.TenHienThi="";
		this.msg = "";
		this.trichphi = "1";
		this.nppdangnhap = "";
		this.hoadonList = new ArrayList<IHoadon>();
		
		this.nhomNCCCNId = "";
		this.NhomNCCCN = "";
			DoiTuongTamUng="1";
			DoiTuongChiPhiKhac="1";
			LoaiThanhToan="1";
		this.db = new dbutils();
		
		this.dinhkhoanno = "";
		this.dinhkhoannoId = "";

		this.sotientt = "0";
		this.sotienHD = "0";
		this.thueVAT = "0";
		this.pnganhang = "0";

		this.sotienttNT = "0";
		this.sotienHDNT = "0";
		this.thueVATNT = "0";
		this.pnganhangNT = "0";
		
		this.tienteId = "100000"; 
		this.tigia = "1";
		this.chenhlechVND = "0";
		
		this.mahoadon = "";
		this.mauhoadon = "";
		this.kyhieu = "";
		this.sohoadon = "";
		this.ngayhoadon = "";
		this.tenNCC = "";
		this.mst = "";
		this.tienhang = "0";
		this.thuesuat = "0";
		this.tienthue = "0";
		this.nhId_VAT = "";
		this.cnId_VAT = "";
		this.tenNCC_VAT = "";
		this.checkThanhtoantuTV="0";
		this.khachhangId = "";
		this.chungtukemtheo  = "";
		
		this.bophanId = "";
		this.bophanTen = "";
		this.count=0;
		this.dungchos = "";
		
		this.isktt = "";
		
	}
	
	public ErpThanhtoanhoadon(String id)
	{
		this.id = id;
		this.ctyId = "";
		this.ngayghinhan = this.getDateTime();
		this.nccId = "";
		this.diachi = "";
		this.htttId = "";
		this.nganhangId = "";
		this.chinhanhId = "";
		this.sotaikhoan = "";
		this.sotaikhoan_tp = "";
		this.noidungtt = "";
		this.sotientt = "0";
		this.hoadonId = "";
		this.trichphi = "1";
		this.msg = "";
		DoiTuongTamUng="";
		DoiTuongChiPhiKhac="";
		LoaiThanhToan="";
		this.NhomNCCCN = "";
		this.nppdangnhap = "";
		this.nhomNCCCNId = "";
		
		this.hoadonList = new ArrayList<IHoadon>();
		
		this.db = new dbutils();
		
		this.dinhkhoanno = "";
		this.dinhkhoannoId = "";
		
		this.sotienHD = "0";
		this.thueVAT = "0";
		this.pnganhang = "0";

		this.sotienttNT = "0";
		this.sotienHDNT = "0";
		this.thueVATNT = "0";
		this.pnganhangNT = "0";

		this.tienteId = "100000";
		this.tigia = "1";
		this.chenhlechVND = "0";
		
		this.mahoadon = "";
		this.mauhoadon = "";
		this.kyhieu = "";
		this.sohoadon = "";
		this.ngayhoadon = "";
		this.tenNCC = "";
		this.mst = "";
		this.tienhang = "0";
		this.thuesuat = "0";
		this.tienthue = "0";
		this.nhId_VAT = "";
		this.cnId_VAT = "";
		this.tenNCC_VAT = "";
		this.checkThanhtoantuTV="";
		this.khachhangId = "";
		this.chungtukemtheo = "";
		
		this.bophanId = "";
		this.bophanTen = "";
		this.count=0;
		this.dungchos = "";
		
		this.isktt = "";
	}
	
	public String getCtyId()
	{
		return this.ctyId;
	}

	public void setCtyId(String ctyId) 
	{
		this.ctyId = ctyId;
	}

	public String getTienteId() {
		
		return this.tienteId;
	}

	
	public void setTienteId(String ttId) {
		
		this.tienteId = ttId;
	}

	public String getTrichphi() {
		
		return this.trichphi;
	}

	
	public void setTrichphi(String trichphi) {
		
		this.trichphi = trichphi;
	}
	
	public ResultSet getTienteRs() {
		
		return this.tienteRs;
	}

	
	public void setTienteRs(ResultSet tienteRs) {
		
		this.tienteRs = tienteRs;
	}

	public String getTigia() 
	{
		return this.tigia;
	}

	public void setTigia(String tigia)
	{
		if(tigia.length() == 0){
			String query = "SELECT TIGIAQUYDOI FROM ERP_TIGIA WHERE TUNGAY <= '" + this.ngayghinhan + "' AND DENNGAY >= '" + this.ngayghinhan + "' AND TIENTE_FK = " + this.tienteId + "";
			System.out.println(query);
			ResultSet rs = this.db.get(query);
			try{
				if(rs != null){
					rs.next();
					this.tigia = rs.getString("TIGIAQUYDOI");
					rs.close();
				}
			}catch(java.sql.SQLException e){
				System.out.println(e.toString());
			}
		}else{
			this.tigia = tigia;
		}
	}

	public String getChenhlech() 
	{
		return this.chenhlechVND;
	}

	public void setChenhlech(String chenhlechVND)
	{
		this.chenhlechVND = chenhlechVND;
	}

	public String getDoituongdinhkhoanId() {
		return doituongdinhkhoanId;
	}

	public void setDoituongdinhkhoanId(String doituongdinhkhoanId) {
		this.doituongdinhkhoanId = doituongdinhkhoanId;
	}

	public String getMaTenDoiTuongDinhKhoan() {
		return MaTenDoiTuongDinhKhoan;
	}

	public void setMaTenDoiTuongDinhKhoan(String maTenDoiTuongDinhKhoan) {
		MaTenDoiTuongDinhKhoan = maTenDoiTuongDinhKhoan;
	}

	public String getDoiTuongDinhKhoan() {
		return DoiTuongDinhKhoan;
	}

	public void setDoiTuongDinhKhoan(String doiTuongDinhKhoan) {
		DoiTuongDinhKhoan = doiTuongDinhKhoan;
	}

	public String getDinhkhoannoId() {
		return dinhkhoannoId;
	}

	public void setDinhkhoannoId(String dinhkhoannoId) {
		this.dinhkhoannoId = dinhkhoannoId;
	}

	public String getDinhkhoanno() {
		return dinhkhoanno;
	}

	public void setDinhkhoanno(String dinhkhoanno) {
		this.dinhkhoanno = dinhkhoanno;
	}

	public String getPrefix() {
		return prefix;
	}

	public void setPrefix(String prefix) {
		this.prefix = prefix;
	}

	public String getDoiTuongChiPhiKhac() {
		return DoiTuongChiPhiKhac;
	}

	public void setDoiTuongChiPhiKhac(String doiTuongChiPhiKhac) {
		DoiTuongChiPhiKhac = doiTuongChiPhiKhac;
	}

	public String getNhomNCCCNId() {
		return nhomNCCCNId;
	}

	public void setNhomNCCCNId(String nhomNCCCNId) {
		this.nhomNCCCNId = nhomNCCCNId;
	}

	public String getNhomNCCCN() {
		return NhomNCCCN;
	}

	public void setNhomNCCCN(String nhomNCCCN) {
		NhomNCCCN = nhomNCCCN;
	}
	
	public ResultSet getSotkRs()
	{
		return this.sotkRs;
	}

	public void setSotkRs(ResultSet sotkRs) 
	{
		this.sotkRs = sotkRs;
	}

	public ResultSet getSotkRs_tp()
	{
		return this.sotkRs_tp;
	}

	public void setSotkRs_tp(ResultSet sotkRs_tp) 
	{
		this.sotkRs_tp = sotkRs_tp;
	}

	public String getTthdCurrent() {
		return tthdCurrent;
	}

	public void setTthdCurrent(String tthdCurrent) {
		this.tthdCurrent = tthdCurrent;
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

	public void setId(String id) 
	{
		this.id = id;
	}

	public String getNgayghinhan() 
	{
		return this.ngayghinhan;
	}

	public void setNgayghinhan(String ngayghinhan) 
	{
		this.ngayghinhan = ngayghinhan;
	}

	public String getNccId()
	{
		return this.nccId;
	}

	public void setNccId(String nccId)
	{
		this.nccId = nccId;	
	}

	public ResultSet getNccRs()
	{
		return this.nccRs;
	}

	public void setNccRs(ResultSet nccRs) 
	{
		this.nccRs = nccRs;
	}

	public String getHtttId() 
	{
		return this.htttId;
	}

	public void setHtttId(String htttId)
	{
		this.htttId = htttId;
	}

	public ResultSet getHtttRs() 
	{
		return this.htttRs;
	}

	public void setHtttRs(ResultSet htttRs)
	{
		this.htttRs = htttRs;	
	}

	public String getNganhangId()
	{
		return this.nganhangId;
	}

	public void setNganhangId(String nganhangId)
	{
		this.nganhangId = nganhangId;
	}

	public ResultSet getNganhangRs() 
	{
		return this.nganhangRs;
	}

	public void setNganhangRs(ResultSet nganhangRs) 
	{
		this.nganhangRs = nganhangRs;
	}

	public String getChinhanhId() 
	{
		return this.chinhanhId;
	}

	public void setChinhanhId(String cnId)
	{
		this.chinhanhId = cnId;
	}

	public ResultSet getChinhanhRs()
	{
		return this.chinhanhRs;
	}

	public void setChinhanhRs(ResultSet chinhanhRs) 
	{
		this.chinhanhRs = chinhanhRs;
	}

	public String getSotaikhoan() 
	{
		return this.sotaikhoan;
	}

	public void setSotaikhoan(String sotk)
	{
		this.sotaikhoan = sotk;
	}

	public String getSotaikhoan_tp() 
	{
		return this.sotaikhoan_tp;
	}

	public void setSotaikhoan_tp(String sotk_tp)
	{
		this.sotaikhoan_tp = sotk_tp;
	}

	public String getNoidungtt()
	{
		return this.noidungtt;
	}

	public void setNoidungtt(String ndtt) 
	{
		this.noidungtt = ndtt;
	}


	public String getHoadonIds() 
	{
		return this.hoadonId;
	}

	public void setHoadonIds(String hdIds) 
	{
		this.hoadonId = hdIds;
	}

	public List<IHoadon> getHoadonRs() 
	{
		return this.hoadonList;
	}

	public void setHoadonRs(List<IHoadon> hoadonRs)
	{
		this.hoadonList = hoadonRs;
	}

	public String getMsg() 
	{
		return this.msg;
	}

	public void setMsg(String msg)
	{
		this.msg = msg;
	}
	
	public String getSotientt() 
	{
		return this.sotientt;
	}

	public void setSotientt(String sotientt) 
	{
		this.sotientt = sotientt;
	}

	public String getSotienHD() 
	{
		return this.sotienHD;
	}

	public void setSotienHD(String sotienHD)
	{
		this.sotienHD = sotienHD;
	}

	public String getPhinganhang() 
	{
		return this.pnganhang;
	}

	public void setPhinganhang(String pnganhang)
	{
		this.pnganhang = pnganhang;
	}

	public String getThueVAT() 
	{
		return this.thueVAT;
	}

	public void setThueVAT(String thueVAT)
	{
		this.thueVAT = thueVAT;
	}

	public String getSotienttNT() 
	{
		return this.sotienttNT;
	}

	public void setSotienttNT(String sotienttNT) 
	{
		this.sotienttNT = sotienttNT;
	}

	public String getSotienHDNT() 
	{
		return this.sotienHDNT;
	}

	public void setSotienHDNT(String sotienHDNT)
	{
		this.sotienHDNT = sotienHDNT;
	}

	public String getPhinganhangNT() 
	{
		return this.pnganhangNT;
	}

	public void setPhinganhangNT(String pnganhangNT)
	{
		this.pnganhangNT = pnganhangNT;
	}

	public String getThueVATNT() 
	{
		return this.thueVATNT;
	}

	public void setThueVATNT(String thueVATNT)
	{
		this.thueVATNT = thueVATNT;
	}

	public String getMahoadon() 
	{
		return this.mahoadon;
	}

	public void setMahoadon(String mahoadon)
	{
		this.mahoadon = mahoadon;
	}

	public String getMauhoadon() 
	{
		return this.mauhoadon;
	}

	public void setMauhoadon(String mauhoadon)
	{
		this.mauhoadon = mauhoadon;
	}

	public String getKyhieu() 
	{
		return this.kyhieu;
	}

	public void setKyhieu(String kyhieu)
	{
		this.kyhieu = kyhieu;
	}

	public String getSohoadon() 
	{
		return this.sohoadon;
	}

	public void setSohoadon(String sohoadon)
	{
		this.sohoadon = sohoadon;
	}

	public String getNgayhoadon() 
	{
		return this.ngayhoadon;
	}

	public void setNgayhoadon(String ngayhoadon)
	{
		this.ngayhoadon = ngayhoadon;
	}

	public String getTenNCC() 
	{
		return this.tenNCC;
	}

	public void setTenNCC(String tenNCC)
	{
		this.tenNCC = tenNCC;
	}

	public String getMST() 
	{
		return this.mst;
	}

	public void setMST(String mst)
	{
		this.mst = mst;
	}

	public String getTienhang() 
	{
		return this.tienhang;
	}

	public void setTienhang(String tienhang)
	{
		this.tienhang = tienhang;
	}

	public String getThuesuat() 
	{
		return this.thuesuat;
	}

	public void setThuesuat(String thuesuat)
	{
		this.thuesuat = thuesuat;
	}

	public String getTienthue() 
	{
		return this.tienthue;
	}

	public void setTienthue(String tienthue)
	{
		this.tienthue = tienthue;
	}

	public String getNhId_VAT() 
	{
		return this.nhId_VAT;
	}

	public void setNhId_VAT(String nhId_VAT)
	{
		this.nhId_VAT = nhId_VAT;
	}

	public String getCnId_VAT() 
	{
		return this.cnId_VAT;
	}

	public void setCnId_VAT(String cnId_VAT)
	{
		this.cnId_VAT = cnId_VAT;
	}
	
	public String getTenNCC_VAT() 
	{
		String tenNCC = "";
		String query = "";
		ResultSet rs;
		try{
			if(!this.tienteId.equals("100000")){ // NGOAI TE
			
				if(this.trichphi.equals("0")){ // NGOAI TE, TRICH PHI BANG NGOAI TE, NGAN HANG TRICH PHI SE BANG NGAN HANG CHUYEN KHOAN
					if(this.sotaikhoan.length() > 0){
						rs = this.db.get("SELECT NGANHANG_FK, CHINHANH_FK FROM ERP_NGANHANG_CONGTY WHERE SOTAIKHOAN = '" + this.sotaikhoan + "'");
						System.out.println("SELECT NGANHANG_FK, CHINHANH_FK FROM ERP_NGANHANG_CONGTY WHERE SOTAIKHOAN = '" + this.sotaikhoan + "'");
						if(rs != null){
							rs.next();
							this.nganhangId = rs.getString("NGANHANG_FK");
							this.chinhanhId = rs.getString("CHINHANH_FK");
							rs.close();
						}
					}else{
						this.nganhangId = "";
						this.chinhanhId = "";

					}
					
					if(this.nganhangId.length() > 0){
						query= 	"SELECT NH.PK_SEQ AS NHID, CN.PK_SEQ AS CNID, NH.TEN + ' - ' + CN.TEN AS NHTEN " +
								"FROM ERP_NGANHANG_CONGTY NHCTY " +
								"INNER JOIN ERP_NGANHANG NH ON NH.PK_SEQ = NHCTY.NGANHANG_FK " +
								"INNER JOIN ERP_CHINHANH CN ON CN.PK_SEQ = NHCTY.CHINHANH_FK " +
								"WHERE NHCTY.TIENTE_FK = " + this.tienteId + " AND NH.TRANGTHAI = 1 AND NH.PK_SEQ = " + this.nganhangId + " " +
								"AND CN.PK_SEQ = " + this.chinhanhId + " ";
						rs = this.db.get(query);
						if(rs != null){
							try{
								rs.next();
								tenNCC = rs.getString("NHTEN");
								this.nhId_VAT = this.nganhangId;
								this.cnId_VAT = this.chinhanhId;
								rs.close();
							}catch(java.sql.SQLException e){}
						}
					}else{
						tenNCC = "";
						this.nhId_VAT = "";
						this.cnId_VAT = "";				
					}	
			
				}else{ // NGOAI TE, TRICH PHI BANG VND, NGAN HANG TRICH PHI SE BANG NGAN HANG VND
					if(this.sotaikhoan_tp.length() > 0){
						rs = this.db.get("SELECT NGANHANG_FK, CHINHANH_FK FROM ERP_NGANHANG_CONGTY WHERE SOTAIKHOAN = '" + this.sotaikhoan_tp + "'");
						System.out.println("SELECT NGANHANG_FK, CHINHANH_FK FROM ERP_NGANHANG_CONGTY WHERE SOTAIKHOAN = '" + this.sotaikhoan_tp + "'");
						if(rs != null){
							rs.next();
							this.nganhang_tpId = rs.getString("NGANHANG_FK");
							this.chinhanh_tpId = rs.getString("CHINHANH_FK");
							rs.close();
						}
					}else{
						this.nganhang_tpId = "";
						this.chinhanh_tpId = "";
					
					}
				
				
					if(this.nganhang_tpId.length() > 0){
						query= 	"SELECT NH.PK_SEQ AS NHID, CN.PK_SEQ AS CNID, NH.TEN + ' - ' + CN.TEN AS NHTEN " +
								"FROM ERP_NGANHANG_CONGTY NHCTY " +
								"INNER JOIN ERP_NGANHANG NH ON NH.PK_SEQ = NHCTY.NGANHANG_FK " +
								"INNER JOIN ERP_CHINHANH CN ON CN.PK_SEQ = NHCTY.CHINHANH_FK " +
								"WHERE NHCTY.TIENTE_FK = " + this.tienteId + " AND NH.TRANGTHAI = 1 AND NH.PK_SEQ = " + this.nganhang_tpId + " " +
								"AND CN.PK_SEQ = " + this.chinhanh_tpId + " ";
						rs = this.db.get(query);
						if(rs != null){
							try{
								rs.next();
								tenNCC = rs.getString("NHTEN");
								this.nhId_VAT = this.nganhang_tpId;
								this.cnId_VAT = this.chinhanh_tpId;

								rs.close();
							}catch(java.sql.SQLException e){}
						}
					}else{
						tenNCC = "";
						this.nhId_VAT = "";
						this.cnId_VAT = "";				
				
					}
			
				}
			}else{ // TIEN VND
				if(this.sotaikhoan.length() > 0){
					rs = this.db.get("SELECT NGANHANG_FK, CHINHANH_FK FROM ERP_NGANHANG_CONGTY WHERE SOTAIKHOAN = '" + this.sotaikhoan + "'");
					System.out.println("SELECT NGANHANG_FK, CHINHANH_FK FROM ERP_NGANHANG_CONGTY WHERE SOTAIKHOAN = '" + this.sotaikhoan + "'");
					if(rs != null){
						rs.next();
						this.nganhangId = rs.getString("NGANHANG_FK");
						this.chinhanhId = rs.getString("CHINHANH_FK");
						rs.close();
					}
				}else{
					this.nganhangId = "";
					this.chinhanhId = "";

				}

				if(this.nganhangId.length() > 0){
					query	= 	"SELECT NH.PK_SEQ AS NHID, CN.PK_SEQ AS CNID, NH.TEN + ' - ' + CN.TEN AS NHTEN " +
								"FROM ERP_NGANHANG_CONGTY NHCTY " +
								"INNER JOIN ERP_NGANHANG NH ON NH.PK_SEQ = NHCTY.NGANHANG_FK " +
								"INNER JOIN ERP_CHINHANH CN ON CN.PK_SEQ = NHCTY.CHINHANH_FK " +
								"WHERE NHCTY.TIENTE_FK = " + this.tienteId + " AND NH.TRANGTHAI = 1 AND NH.PK_SEQ = " + this.nganhangId + " " +
								"AND CN.PK_SEQ = " + this.chinhanhId + " ";
					rs = this.db.get(query);
					if(rs != null){
						try{
							rs.next();
							tenNCC = rs.getString("NHTEN");
							this.nhId_VAT = this.nganhangId;
							this.cnId_VAT = this.chinhanhId;
							rs.close();
						}catch(java.sql.SQLException e){}
					}
				}else{
					tenNCC = "";
					this.nhId_VAT = "";
					this.cnId_VAT = "";				
				}	
			}
		}catch(java.sql.SQLException e){}
		
		System.out.println("TEN NCC:" + query);
		System.out.println(tenNCC);
		return tenNCC;
	}

	public void setTenNCC_VAT(String tenNCC_VAT)
	{
		this.tenNCC_VAT = tenNCC_VAT;
	}

	public boolean createTTHD() 
	{
		this.getNppInfo();
		if(!this.DoiTuongChiPhiKhac.equals("3")){
			if(this.hoadonList.size() <= 0)
			{
				this.msg = "Không có hóa đơn nào để thanh toán";
				return false;
			}
		}
		
		
		if(this.htttId.length() <= 0)
		{
			this.msg = "Vui lòng chọn hình thức thanh toán";
			return false;
		}
		
		if(this.htttId.equals("100001"))
		{
			
			if(this.sotaikhoan.trim().length() <= 0 )
			{
				if(this.checkThanhtoantuTV.equals("1"))
					this.msg = "Vui lòng nhập Số tài khoản để trích phí ngân hàng !";
				else
					this.msg = "Vui lòng nhập Số tài khoản  !";
				return false;
			}
		}
		
		try 
		{
			String ngaytao = getDateTime();
			
			db.getConnection().setAutoCommit(false);
			
			if(this.sotaikhoan.trim().length() <= 0)
				this.sotaikhoan = "";
			
			if(this.DoiTuongDinhKhoan.trim().length() <= 0)
			{
				this.DoiTuongDinhKhoan = "NULL";
				if(this.doituongdinhkhoanId.trim().length() <= 0)
				{
					this.doituongdinhkhoanId = "NULL";
				}
			}
			
			double tongthanhtoan = 0;
			//Tinh lai tong tien			
				
			if(this.sotaikhoan.length() > 0){
				ResultSet rs = this.db.get("SELECT NGANHANG_FK, CHINHANH_FK FROM ERP_NGANHANG_CONGTY WHERE SOTAIKHOAN = '" + this.sotaikhoan + "'");
				System.out.println("SELECT NGANHANG_FK, CHINHANH_FK FROM ERP_NGANHANG_CONGTY WHERE SOTAIKHOAN = '" + this.sotaikhoan + "'");
				if(rs != null){
					rs.next();
					this.nganhangId = rs.getString("NGANHANG_FK");
					this.chinhanhId = rs.getString("CHINHANH_FK");
					rs.close();
				}
			}else{
				this.nganhangId = "";
				this.chinhanhId = "";

			}
			
			if(this.sotaikhoan_tp.length() > 0){
				ResultSet rs = this.db.get("SELECT NGANHANG_FK, CHINHANH_FK FROM ERP_NGANHANG_CONGTY WHERE SOTAIKHOAN = '" + this.sotaikhoan_tp + "'");
				System.out.println("SELECT NGANHANG_FK, CHINHANH_FK FROM ERP_NGANHANG_CONGTY WHERE SOTAIKHOAN = '" + this.sotaikhoan_tp + "'");
				if(rs != null){
					rs.next();
					this.nganhang_tpId = rs.getString("NGANHANG_FK");
					this.chinhanh_tpId = rs.getString("CHINHANH_FK");
					rs.close();
				}
			}else{
				this.nganhang_tpId = "";
				this.chinhanh_tpId = "";
				
			}

			System.out.println("Hoa don list size: " + this.hoadonList.size());
			for(int i = 0; i < this.hoadonList.size(); i++)
			{
				IHoadon hd = this.hoadonList.get(i);
				if(hd.getThanhtoan().length() > 0)
					tongthanhtoan += Double.parseDouble(hd.getThanhtoan().replaceAll(",", ""));
			}
				
			if(this.DoiTuongChiPhiKhac.equals("3"))
			{
				tongthanhtoan = Double.parseDouble(this.sotientt.replaceAll(",", ""));
			}
			
			System.out.println("[ErpThanhtoanhoadon.createTTHD] tongthanhtoan = " + tongthanhtoan);
			
			//String tientt = this.sotientt;
			if(nhomNCCCNId.length() > 0)
			{
				String query = 
				"Insert ERP_THANHTOANHOADON" +
				"(DVTH_FK, NGAYGHINHAN, NHOMNCCCN ,NHANVIEN_FK, HTTT_FK, NGANHANG_FK, CHINHANH_FK, SOTAIKHOAN, NOIDUNGTT, " +
				" SOTIENTT, SOTIENTTNT, SOTIENHD, SOTIENHDNT, " +
				" PHINGANHANG, PHINGANHANGNT, VAT, VATNT, CHENHLECHVND, " +
				" TRICHPHI, SOTAIKHOAN_TP, NGANHANG_TP_FK, CHINHANH_TP_FK, " +
				" NGAYTAO, NGUOITAO, NGAYSUA, NGUOISUA, LOAITHANHTOAN, TIENTE_FK, TIGIA, THANHTOANTUTIENVAY, CTKEMTHEO, NPP_FK, PREFIX  " +
				") " +
				"values("+(this.bophanId.length()==0?null : this.bophanId) +", '" + this.ngayghinhan + "', " + (this.nhomNCCCNId.length()==0?null : this.nhomNCCCNId ) + "," +
				"" +(this.NhanvienId.length()==0?null : this.NhanvienId) +", '" + this.htttId + "', " +
				"" + (this.nganhangId.length() == 0?null : this.nganhangId) + ", " + (this.chinhanhId.length() == 0?null : this.chinhanhId) + ", '" + this.sotaikhoan + "', N'" + this.noidungtt + "', " +
				"" + ("" + this.sotientt).replaceAll(",", "") + ", " + this.sotienttNT.replaceAll(",", "") + ", " + this.sotienHD.replaceAll(",", "") + ", " + this.sotienHDNT.replaceAll(",", "") + " , " +
				"" + this.pnganhang.replaceAll(",", "") + ", " + this.pnganhangNT.replaceAll(",", "") + ", " + this.thueVAT.replaceAll(",", "") + ", " + this.thueVATNT.replaceAll(",", "") + ", " + this.chenhlechVND.replaceAll(",", "") + ", " +
				"" + this.trichphi + ", " + this.sotaikhoan_tp + ", " + (this.nganhang_tpId.length() == 0?null : this.nganhang_tpId) + ", " +
				"" + (this.chinhanh_tpId.length() == 0?null : this.chinhanh_tpId) + ", " +
				"'"+ ngaytao + "', '" + this.userId + "', '" + ngaytao + "', '" + this.userId + "','"+this.LoaiThanhToan+"', " + this.tienteId + ", " + this.tigia.replaceAll(",", "") + " , '"+ this.checkThanhtoantuTV +"', N'"+this.chungtukemtheo+"', "+this.nppdangnhap+", 'DNPC')";


				if(!db.update(query))
				{
					this.msg = "Khong the tao moi ERP_THANHTOANHOADON: " + query;
					System.out.println(this.msg);
					db.getConnection().rollback();
					return false;
				}
			}
			else
			{
				String isTICHLUY = "0";
				
				if(this.DoiTuongChiPhiKhac.equals("5")) isTICHLUY = "1";
					
				String query = 
				"Insert ERP_THANHTOANHOADON " +
				"(DVTH_FK, NGAYGHINHAN, NCC_FK ,NHANVIEN_FK, HTTT_FK, NGANHANG_FK, CHINHANH_FK, SOTAIKHOAN, NOIDUNGTT, " +
				" SOTIENTT, SOTIENTTNT, SOTIENHD, SOTIENHDNT, " +
				" PHINGANHANG, PHINGANHANGNT, VAT, VATNT, CHENHLECHVND, " +
				" TRICHPHI, SOTAIKHOAN_TP, NGANHANG_TP_FK, CHINHANH_TP_FK, " +
				" NGAYTAO, NGUOITAO, NGAYSUA, NGUOISUA, LOAITHANHTOAN, TIENTE_FK, TIGIA , THANHTOANTUTIENVAY, KHACHHANG_FK, CTKEMTHEO, NPP_FK, PREFIX, isTICHLUY " +
				") " +
				"values("+(this.bophanId.length()==0?null : this.bophanId) +", '" + this.ngayghinhan + "', " + (this.nccId.length()==0?null : this.nccId ) + "," +
				""+(this.NhanvienId.length()==0?null : this.NhanvienId) +", '" + this.htttId + "', " +
				"" + (this.nganhangId.length() == 0?null : this.nganhangId) + ", " + (this.chinhanhId.length() == 0?null : this.chinhanhId) + ", '" + this.sotaikhoan + "', N'" + this.noidungtt + "', " +
				"" + ("" + this.sotientt).replaceAll(",", "") + ", " + this.sotienttNT.replaceAll(",", "") + ", " + this.sotienHD.replaceAll(",", "") + ", " + this.sotienHDNT.replaceAll(",", "") + " , " +
				"" + this.pnganhang.replaceAll(",", "") + ", " + this.pnganhangNT.replaceAll(",", "") + ", " + this.thueVAT.replaceAll(",", "") + ", " + this.thueVATNT.replaceAll(",", "") + ", " + this.chenhlechVND.replaceAll(",", "") + ", " +
				"" + this.trichphi + ", '" + this.sotaikhoan_tp + "', " + (this.nganhang_tpId.length() == 0?null : this.nganhang_tpId) + ", " + (this.chinhanh_tpId.length() == 0?null : this.chinhanh_tpId) + ", " +
				"'"+ ngaytao + "', '" + this.userId + "', '" + ngaytao + "', '" + this.userId + "','"+this.LoaiThanhToan + "', " + this.tienteId + ", " + this.tigia.replaceAll(",", "") + ", '"+ this.checkThanhtoantuTV +"', " + (this.khachhangId.length()==0?null : this.khachhangId ) + ", N'"+this.chungtukemtheo+"', "+this.nppdangnhap+", 'DNPC' , "+isTICHLUY+")";

				
				if(this.DoiTuongChiPhiKhac.equals("3"))
				{
					query = "Insert ERP_THANHTOANHOADON(NGAYGHINHAN, HTTT_FK, NGANHANG_FK, CHINHANH_FK, SOTAIKHOAN, NOIDUNGTT,  SOTIENTT, SOTIENTTNT, SOTIENHD, SOTIENHDNT," +
							" TRICHPHI, SOTAIKHOAN_TP, NGANHANG_TP_FK, CHINHANH_TP_FK, " +
							" PHINGANHANG, PHINGANHANGNT, VAT, VATNT, " +
							" NGAYTAO, NGUOITAO, NGAYSUA, NGUOISUA,LOAITHANHTOAN , " +
							" TIENTE_FK, TIGIA, THANHTOANTUTIENVAY, CTKEMTHEO, NPP_FK, PREFIX) " +
							"values('" + this.ngayghinhan + "', '" + this.htttId + "', " + (this.nganhangId.length() == 0?null : this.nganhangId) + ", " + (this.chinhanhId.length() == 0?null : this.chinhanhId) + ", '" + this.sotaikhoan + "', N'" + this.noidungtt + "', " +
							"" + ("" + this.sotientt).replaceAll(",", "") + "," + this.sotienttNT.replaceAll(",", "") + "," + this.sotienHD.replaceAll(",", "") + "," + this.sotienHDNT.replaceAll(",", "") + " ," +							
							"" + this.trichphi + ", '" + this.sotaikhoan_tp + "', " + (this.nganhang_tpId.length() == 0?null : this.nganhang_tpId) + ", " + (this.chinhanh_tpId.length() == 0?null : this.chinhanh_tpId) + ", " +
							"" + this.pnganhang.replaceAll(",", "") + ", " + this.pnganhangNT.replaceAll(",", "") + ", " + this.thueVAT.replaceAll(",", "") + ", " + this.thueVATNT.replaceAll(",", "") + ", " +							
							"'"  + ngaytao + "', '" + this.userId + "', '" + ngaytao + "', '" + this.userId + "','"+this.LoaiThanhToan+ "'," +
							"" + this.tienteId + ", " + this.tigia.replaceAll(",", "") + " ,'"+ this.checkThanhtoantuTV +"', N'"+this.chungtukemtheo+"', "+this.nppdangnhap+", 'DNPC')";

				}
				System.out.println(query);
				if(!db.update(query))
				{
					this.msg = "Khong the tao moi ERP_THANHTOANHOADON: " + query;
					System.out.println(this.msg);
					db.getConnection().rollback();
					return false;
				}
			}
			
			String query = "select IDENT_CURRENT('ERP_THANHTOANHOADON') as tthdId";
			
			ResultSet rsTthd = db.get(query);						
			if(rsTthd.next())
			{
				tthdCurrent = rsTthd.getString("tthdId");
				rsTthd.close();
			}

			if(this.bophanId.trim().length() > 0) // THANH TOÁN THEO BỘ PHẬN
			{
				for(int i = 0; i < this.hoadonList.size(); i++)
				{
					IHoadon hoadon = this.hoadonList.get(i);

					String thanhtoan = (hoadon.getThanhtoan().replaceAll(",", ""));
					String avat = hoadon.getTongtiencoVAT().replaceAll(",", "");
					String sotienNT = hoadon.getSotienNT().replaceAll(",", "");
					String conlai = hoadon.getConlai().replaceAll(",", "");
					String loaihd = hoadon.getLoaihd1();
					String sohoadon = hoadon.getSo();
					
					String doituong =hoadon.getDoituong();
					String nhanvienId = "NULL";
					String nccId= "NULL";
					if(doituong.equals("0")) nhanvienId = hoadon.getDoituongId();
					else nccId = hoadon.getDoituongId();
					
					if(thanhtoan.length() > 0)
					{
						if(Float.parseFloat(thanhtoan) != 0)
						{
							if(this.tienteId.equals("100000")){
								query = "Insert ERP_THANHTOANHOADON_HOADONBOPHAN(THANHTOANHD_FK, HOADON_FK, SOTIENTT, SOTIENAVAT, SOTIENNT, CONLAI, LOAIHD, NHANVIEN_FK, NCC_FK, SOHOADON) " +
										"values('" + tthdCurrent + "', '" + hoadon.getId() + "', '" + thanhtoan.trim().replaceAll(",", "") + "', '" + avat.replaceAll(",", "") + "'," +
										" 0, '" + conlai.trim().replaceAll(",", "") + "', '"+ loaihd +"', "+ nhanvienId +", "+ nccId +", '"+ sohoadon +"')";
							
							}else{
								query = "Insert ERP_THANHTOANHOADON_HOADONBOPHAN(THANHTOANHD_FK, HOADON_FK, SOTIENTT, SOTIENAVAT, SOTIENNT, CONLAI, LOAIHD, NHANVIEN_FK, NCC_FK, SOHOADON) " +
										"values('" + tthdCurrent + "', '" + hoadon.getId() + "', '" + thanhtoan.trim().replaceAll(",", "") + "', '" + avat.replaceAll(",", "") + "', " + sotienNT.replaceAll(",", "") + "," +
										" '" + conlai.trim().replaceAll(",", "") + "', '"+ loaihd +"', "+ nhanvienId +", "+ nccId +", '"+ sohoadon +"')";																					

							}					
							if(!db.update(query))
							{
								this.msg = "Khong the tao moi ERP_THANHTOANHOADON_HOADONBOPHAN: " + query;
								System.out.println(this.msg);
								db.getConnection().rollback();
								return false;
							}
						}
					}
				}
			}else
			{
				for(int i = 0; i < this.hoadonList.size(); i++)
				{
					IHoadon hoadon = this.hoadonList.get(i);

					String thanhtoan = (hoadon.getThanhtoan().replaceAll(",", ""));
					String avat = hoadon.getTongtiencoVAT().replaceAll(",", "");
					String sotienNT = hoadon.getSotienNT().replaceAll(",", "");
					String conlai = hoadon.getConlai().replaceAll(",", "");
					String loaihd = hoadon.getLoaihd1();
					String sohoadon = hoadon.getSo();
					String tigiahd = "1";
					String tientehd = "100000";
					if(hoadon.getTigia()!=null) tigiahd = hoadon.getTigia().replaceAll(",", "");
					if(hoadon.getTienteId()!=null) tientehd = hoadon.getTienteId().replaceAll(",", "");
					
				
					if(thanhtoan.length() > 0)
					{
						if(Float.parseFloat(thanhtoan) != 0)
						{
							if(this.tienteId.equals("100000")){
								query = "Insert ERP_THANHTOANHOADON_HOADON(THANHTOANHD_FK, HOADON_FK, SOTIENTT, SOTIENAVAT, SOTIENNT, CONLAI, LOAIHD, SOHOADON, TIGIA, TIENTE_FK ) " +
										"values('" + tthdCurrent + "', '" + hoadon.getId() + "', '" + thanhtoan.trim().replaceAll(",", "") + "', '" + avat.replaceAll(",", "") + "'," +
										" 0, '" + conlai.trim().replaceAll(",", "") + "', '"+ loaihd +"', '"+ sohoadon +"', "+tigiahd+", "+tientehd+")";
								if(loaihd.equals("4"))
								{
									query = "Insert ERP_THANHTOANHOADON_HOADON(THANHTOANHD_FK, HOADON_FK, SOTIENTT, SOTIENAVAT, SOTIENNT, CONLAI, LOAIHD, LOAITHUE, SOHOADON, TIGIA, TIENTE_FK ) " +
									"values('" + tthdCurrent + "', '" + hoadon.getId() + "', '" + thanhtoan.trim().replaceAll(",", "") + "', '" + avat.replaceAll(",", "") + "'," +
											" 0, '" + conlai.trim().replaceAll(",", "") + "', '"+ loaihd +"', N'"+ hoadon.getKyhieu() +"', '"+ sohoadon +"', "+tigiahd+", "+tientehd+")";
								}
							
							}else{
								query = "Insert ERP_THANHTOANHOADON_HOADON(THANHTOANHD_FK, HOADON_FK, SOTIENTT, SOTIENAVAT, SOTIENNT, CONLAI, LOAIHD, SOHOADON, TIGIA , TIENTE_FK ) " +
										"values('" + tthdCurrent + "', '" + hoadon.getId() + "', '" + thanhtoan.trim().replaceAll(",", "") + "', '" + avat.replaceAll(",", "") + "', " + sotienNT.replaceAll(",", "") + "," +
												" '" + conlai.trim().replaceAll(",", "") + "', '"+ loaihd +"', '"+ sohoadon +"', "+tigiahd+", "+tientehd+")";																					
								if(loaihd.equals("4"))
								{
									query = "Insert ERP_THANHTOANHOADON_HOADON(THANHTOANHD_FK, HOADON_FK, SOTIENTT, SOTIENAVAT, SOTIENNT, CONLAI, LOAIHD, LOAITHUE, SOHOADON, TIGIA, TIENTE_FK ) " +
									"values('" + tthdCurrent + "', '" + hoadon.getId() + "', '" + thanhtoan.trim().replaceAll(",", "") + "', '" + avat.replaceAll(",", "") + "', " + sotienNT.replaceAll(",", "") + "," +
											" '" + conlai.trim().replaceAll(",", "") + "', '"+ loaihd +"', N'"+ hoadon.getKyhieu() +"', '"+ sohoadon +"', "+tigiahd+", "+tientehd+")";																						
							
								}
							}					
							if(!db.update(query))
							{
								this.msg = "Khong the tao moi ERP_THANHTOANHOADON_HOADON: " + query;
								System.out.println(this.msg);
								db.getConnection().rollback();
								return false;
							}
							
							System.out.println("ERP_THANHTOANHOADON_HOADON:"+query);
						}
					}
				}

			}
			
			if(!this.DoiTuongChiPhiKhac.equals("3"))
			{
				query = "INSERT INTO ERP_THANHTOANHOADON_PHINGANHANG( THANHTOANHD_FK, MAHOADON, MAUHOADON, KYHIEU, SOHOADON, " +
						"NGAYHOADON, TENNCC, MST, TIENHANG, THUESUAT, TIENTHUE, NGANHANG_FK, CHINHANH_FK) " +
						"VALUES(" + tthdCurrent + ", N'" + this.mahoadon + "', N'" + this.mauhoadon + "', N'" + this.kyhieu + "', '" + this.sohoadon + "'," +
						"'" + this.ngayghinhan + "', N'" + this.tenNCC + "', '" + this.mst + "', " + this.tienhang.replaceAll(",", "") + ", " + this.thuesuat.replaceAll(",", "") + ", " + this.tienthue.replaceAll(",", "") + ", " +
						"" + (this.nhId_VAT.length() == 0?null : this.nhId_VAT) + ", " + (this.cnId_VAT.length() == 0?null : this.cnId_VAT) + ")";
				System.out.println(query);
				if(!db.update(query))
				{
					this.msg = "Khong the tao moi ERP_THANHTOANHOADON_PHINGANHANG: " + query;
					System.out.println(this.msg);
					db.getConnection().rollback();
					return false;
				}
			}
			else // CHI KHÁC
			{
				int i=0; 
				if(Tienhanghd !=null)
				{
					while(Tienhanghd.length>i)
					{						
						System.out.println("Tienhanghd[i].trim():"+Tienhanghd[i].trim());
						if(Tienhanghd[i].trim().length() <=0)
							Tienhanghd[i] = "0";
						
						if(Double.parseDouble(Tienhanghd[i].trim().replaceAll(",", "")) > 0)
						{
							if(Thuesuathd[i].replaceAll(",", "").trim().length()<=0)
								Thuesuathd[i]  = "0";
							if(Tienthuehd[i].replaceAll(",", "").trim().length()<=0)
								Tienthuehd[i]  = "0";
							if(PhongBanIds[i].trim().length()<=0)
								PhongBanIds[i]  = "null";							
							if(KenhbhIds[i].trim().length()<=0)
								KenhbhIds[i]  = "null";
							if(TinhThanhIds[i].trim().length()<=0)
								TinhThanhIds[i]  = "null";							
							if(SanPhamIds[i].trim().length()<=0)
								SanPhamIds[i]  = "null";
							if(DiabanIds[i].trim().length()<=0)
								DiabanIds[i]  = "null";
							if(MavvIds[i].trim().length()<=0)
								MavvIds[i]  = "null";
							if(BenhvienIds[i].trim().length()<=0)
								BenhvienIds[i]  = "null";
							if(Diengiaihd[i].trim().length()<=0)
								Diengiaihd[i]  = "";
							
							if(TaiKhoanIds[i]!="")
							{

								String []TkId=TaiKhoanIds[i].split("_");
																
								query = "SELECT DUNGCHOKHO, DUNGCHONCC, DUNGCHONGANHANG, DUNGCHOTAISAN, DUNGCHOKHACHHANG, COTTCHIPHI, DUNGCHONHANVIEN, SOHIEUTAIKHOAN FROM ERP_TAIKHOANKT WHERE PK_SEQ = " + TkId[0] + "";
								System.out.println(query);
								ResultSet rs  = this.db.get(query);
								rs.next();
								
								if(dcIds[i] == null)
									dcIds[i] = "";
								System.out.println("dcIds[i]:"+dcIds[i]);
								
								// KIẾM TRA XEM CÓ CHỌN ĐỐI TƯỢNG CHƯA
								if(rs.getString("DUNGCHONHANVIEN").equals("1")&&dcIds[i].equals("")){
									
									this.db.getConnection().rollback();
									this.msg = "Vui lòng chọn mã đối tượng.";
									return false;
								}
								
								if(rs.getString("DUNGCHONCC").equals("1")&&dcIds[i].equals("")){
									
									this.db.getConnection().rollback();
									this.msg = "Vui lòng chọn mã đối tượng.";
									return false;
								}
								
								if(rs.getString("DUNGCHONGANHANG").equals("1")&&dcIds[i].equals("")){
									
									this.db.getConnection().rollback();
									this.msg = "Vui lòng chọn mã đối tượng.";
									return false;
								}

								if(rs.getString("DUNGCHOTAISAN").equals("1")&&dcIds[i].equals("")){
									
									this.db.getConnection().rollback();
									this.msg = "Vui lòng chọn mã đối tượng.";
									return false;
								}
								
								if(rs.getString("DUNGCHOKHACHHANG").equals("1")&&dcIds[i].equals("")){
									
									this.db.getConnection().rollback();
									this.msg = "Vui lòng chọn mã đối tượng.";
									return false;
								}
																
								if(rs.getString("DUNGCHONHANVIEN").equals("1")&&dcIds[i].equals("")){
									
									this.db.getConnection().rollback();
									this.msg = "Vui lòng chọn mã đối tượng.";
									return false;
								}
								
								if(rs.getString("COTTCHIPHI").equals("1")&&dcIds[i].equals("")){
									
									this.db.getConnection().rollback();
									this.msg = "Vui lòng chọn trung tâm chi phí.";
									return false;
								}
								

								if(dcIds[i].trim().length()<=0)
									dcIds[i]  = "null";
								
								if(loais[i].equals("1") ){
									query="INSERT INTO ERP_THANHTOANHOADON_PHINGANHANG(THANHTOANHD_FK, KYHIEU, SOHOADON,NGAYHOADON, TENNCC, MST, TIENHANG, THUESUAT, TIENTHUE, SANPHAM_FK, TAIKHOAN_FK, PHONGBAN_FK, KBH_FK, TINHTHANH_FK, DIABAN_FK, VUVIEC_FK, BENHVIEN_FK, SP_FK, DIENGIAI ) VALUES " +
										" ('"+tthdCurrent+"', N'" + Kyhieuhd[i] + "', '" + Sohd[i] + "'," +
										"'" + Ngayhd[i] + "', N'" + TenNCChd[i] + "', '" + MSThd[i] + "', " + Tienhanghd[i].replaceAll(",", "") + ", " + Thuesuathd[i].replaceAll(",", "") + ", " + Tienthuehd[i].replaceAll(",", "") + ", " +
										dcIds[i]+", "+TkId[0]+","+PhongBanIds[i]+","+KenhbhIds[i]+", "+TinhThanhIds[i]+", "+DiabanIds[i]+", "+MavvIds[i]+", "+ BenhvienIds[i] + ", " + SanPhamIds[i]+", N'"+Diengiaihd[i]+"')";
									
								}else						
								if(loais[i].equals("2") ){
									query="INSERT INTO ERP_THANHTOANHOADON_PHINGANHANG(THANHTOANHD_FK, KYHIEU, SOHOADON,NGAYHOADON, TENNCC, MST, TIENHANG, THUESUAT, TIENTHUE, NCC_FK, TAIKHOAN_FK, PHONGBAN_FK, KBH_FK, TINHTHANH_FK, DIABAN_FK, VUVIEC_FK, BENHVIEN_FK, SP_FK, DIENGIAI ) VALUES " +
											" ('"+tthdCurrent+"', N'" + Kyhieuhd[i] + "', '" + Sohd[i] + "'," +
											"'" + Ngayhd[i] + "', N'" + TenNCChd[i] + "', '" + MSThd[i] + "', " + Tienhanghd[i].replaceAll(",", "") + ", " + Thuesuathd[i].replaceAll(",", "") + ", " + Tienthuehd[i].replaceAll(",", "") + ", " +
											dcIds[i]+", "+TkId[0]+","+PhongBanIds[i]+","+KenhbhIds[i]+", "+TinhThanhIds[i]+", "+DiabanIds[i]+", "+MavvIds[i]+", "+ BenhvienIds[i] + ", " + SanPhamIds[i]+", N'"+Diengiaihd[i]+"')";
									
								}else						
								if(loais[i].equals("3") ){
									query="INSERT INTO ERP_THANHTOANHOADON_PHINGANHANG(THANHTOANHD_FK, KYHIEU, SOHOADON,NGAYHOADON, TENNCC, MST, TIENHANG, THUESUAT, TIENTHUE, NGANHANG_FK, TAIKHOAN_FK, PHONGBAN_FK, KBH_FK, TINHTHANH_FK, DIABAN_FK, VUVIEC_FK, BENHVIEN_FK, SP_FK, DIENGIAI ) VALUES " +
											" ('"+tthdCurrent+"', N'" + Kyhieuhd[i] + "', '" + Sohd[i] + "'," +
											"'" + Ngayhd[i] + "', N'" + TenNCChd[i] + "', '" + MSThd[i] + "', " + Tienhanghd[i].replaceAll(",", "") + ", " + Thuesuathd[i].replaceAll(",", "") + ", " + Tienthuehd[i].replaceAll(",", "") + ", " +
											dcIds[i]+", "+TkId[0]+","+PhongBanIds[i]+","+KenhbhIds[i]+", "+TinhThanhIds[i]+", "+DiabanIds[i]+", "+MavvIds[i]+", "+ BenhvienIds[i] + ", " + SanPhamIds[i]+", N'"+Diengiaihd[i]+"')";									
								}else
								if(loais[i].equals("4") ){
									query="INSERT INTO ERP_THANHTOANHOADON_PHINGANHANG(THANHTOANHD_FK, KYHIEU, SOHOADON,NGAYHOADON, TENNCC, MST, TIENHANG, THUESUAT, TIENTHUE,TAISAN_FK, TAIKHOAN_FK, PHONGBAN_FK, KBH_FK, TINHTHANH_FK, DIABAN_FK, VUVIEC_FK, BENHVIEN_FK, SP_FK, DIENGIAI ) VALUES " +
											" ('"+tthdCurrent+"', N'" + Kyhieuhd[i] + "', '" + Sohd[i] + "'," +
											"'" + Ngayhd[i] + "', N'" + TenNCChd[i] + "', '" + MSThd[i] + "', " + Tienhanghd[i].replaceAll(",", "") + ", " + Thuesuathd[i].replaceAll(",", "") + ", " + Tienthuehd[i].replaceAll(",", "") + ", " +
											dcIds[i]+", "+TkId[0]+","+PhongBanIds[i]+","+KenhbhIds[i]+", "+TinhThanhIds[i]+", "+DiabanIds[i]+", "+MavvIds[i]+", "+ BenhvienIds[i] + ", " + SanPhamIds[i]+", N'"+Diengiaihd[i]+"')";							
									
								}else
								if(loais[i].equals("5") ){ // KHÁCH HÀNG
									//CHỖ NÀY ĐỂ PHÂN BIỆT LÀ NPP HAY KHÁCH HÀNG HAY NHÂN VIÊN
									String isNPP = "NULL";
									String kh_noId = "null";		
									
									kh_noId = dcIds[i];
									
									if( kh_noId.contains("-") )
									{
										String kh[] = kh_noId.split("-");
										kh_noId = kh[0];
										isNPP = kh[1];
									}
									
									query="INSERT INTO ERP_THANHTOANHOADON_PHINGANHANG(THANHTOANHD_FK, KYHIEU, SOHOADON,NGAYHOADON, TENNCC, MST, TIENHANG, THUESUAT, TIENTHUE, KHACHHANG_FK, TAIKHOAN_FK, PHONGBAN_FK, KBH_FK, TINHTHANH_FK, DIABAN_FK, VUVIEC_FK, BENHVIEN_FK, SP_FK, DIENGIAI,ISNPP ) VALUES " +
											" ('"+tthdCurrent+"', N'" + Kyhieuhd[i] + "', '" + Sohd[i] + "'," +
											"'" + Ngayhd[i] + "', N'" + TenNCChd[i] + "', '" + MSThd[i] + "', " + Tienhanghd[i].replaceAll(",", "") + ", " + Thuesuathd[i].replaceAll(",", "") + ", " + Tienthuehd[i].replaceAll(",", "") + ", " +
											kh_noId+", "+TkId[0]+","+PhongBanIds[i]+","+KenhbhIds[i]+", "+TinhThanhIds[i]+", "+DiabanIds[i]+", "+MavvIds[i]+", "+ BenhvienIds[i] + ", " + SanPhamIds[i]+", N'"+Diengiaihd[i]+"', "+isNPP+")";	
									
								}else 
								if(loais[i].equals("7") ){
									query="INSERT INTO ERP_THANHTOANHOADON_PHINGANHANG(THANHTOANHD_FK, KYHIEU, SOHOADON,NGAYHOADON, TENNCC, MST, TIENHANG, THUESUAT, TIENTHUE, NHANVIEN_FK, TAIKHOAN_FK, PHONGBAN_FK, KBH_FK, TINHTHANH_FK, DIABAN_FK, VUVIEC_FK, BENHVIEN_FK , SP_FK, DIENGIAI ) VALUES " +
											" ('"+tthdCurrent+"', N'" + Kyhieuhd[i] + "', '" + Sohd[i] + "'," +
											"'" + Ngayhd[i] + "', N'" + TenNCChd[i] + "', '" + MSThd[i] + "', " + Tienhanghd[i].replaceAll(",", "") + ", " + Thuesuathd[i].replaceAll(",", "") + ", " + Tienthuehd[i].replaceAll(",", "") + ", " +
											dcIds[i]+", "+TkId[0]+","+PhongBanIds[i]+","+KenhbhIds[i]+", "+TinhThanhIds[i]+", "+DiabanIds[i]+", "+MavvIds[i]+", "+ BenhvienIds[i] + ", " + SanPhamIds[i]+", N'"+Diengiaihd[i]+"')";	
									
								}else
								if(loais[i].equals("6") ){
									query="INSERT INTO ERP_THANHTOANHOADON_PHINGANHANG(THANHTOANHD_FK, KYHIEU, SOHOADON,NGAYHOADON, TENNCC, MST, TIENHANG, THUESUAT, TIENTHUE, TTCP_FK, TAIKHOAN_FK, PHONGBAN_FK, KBH_FK, TINHTHANH_FK, DIABAN_FK, VUVIEC_FK, BENHVIEN_FK, SP_FK, DIENGIAI ) VALUES " +
											" ('"+tthdCurrent+"', N'" + Kyhieuhd[i] + "', '" + Sohd[i] + "'," +
											"'" + Ngayhd[i] + "', N'" + TenNCChd[i] + "', '" + MSThd[i] + "', " + Tienhanghd[i].replaceAll(",", "") + ", " + Thuesuathd[i].replaceAll(",", "") + ", " + Tienthuehd[i].replaceAll(",", "") + ", " +
											dcIds[i]+", "+TkId[0]+","+PhongBanIds[i]+","+KenhbhIds[i]+", "+TinhThanhIds[i]+", "+DiabanIds[i]+", "+MavvIds[i]+", "+ BenhvienIds[i] + ", " + SanPhamIds[i]+", N'"+Diengiaihd[i]+"')";								
								}								
								else {
									query="INSERT INTO ERP_THANHTOANHOADON_PHINGANHANG(THANHTOANHD_FK, KYHIEU, SOHOADON,NGAYHOADON, TENNCC, MST, TIENHANG, THUESUAT, TIENTHUE, TAIKHOAN_FK, PHONGBAN_FK, KBH_FK, TINHTHANH_FK, DIABAN_FK, VUVIEC_FK, BENHVIEN_FK, SP_FK, DIENGIAI ) VALUES " +
											" ('"+tthdCurrent+"', N'" + Kyhieuhd[i] + "', '" + Sohd[i] + "'," +
											"'" + Ngayhd[i] + "', N'" + TenNCChd[i] + "', '" + MSThd[i] + "', " + Tienhanghd[i].replaceAll(",", "") + ", " + Thuesuathd[i].replaceAll(",", "") + ", " + Tienthuehd[i].replaceAll(",", "") + ", " +
											TkId[0]+","+PhongBanIds[i]+","+KenhbhIds[i]+", "+TinhThanhIds[i]+", "+DiabanIds[i]+", "+MavvIds[i]+", "+ BenhvienIds[i] + ", " + SanPhamIds[i]+", N'"+Diengiaihd[i]+"')";			
								}
														
								System.out.println(query);
								if(!this.db.update(query))
								{
									this.db.getConnection().rollback();
									this.msg="Loi dong lenh sau "+query;
									return false;
								}
							
							}
							else
							{
								this.msg="Vui lòng chọn tài khoản !";
								return false;
							}
							
					}
						
						i++;
				}
					
			}	
			
			}
			
			// cập nhật mã chứng từ
			query = " update ERP_THANHTOANHOADON set machungtu =  'DNPC' + SUBSTRING(NGAYGHINHAN, 6, 2) + SUBSTRING(NGAYGHINHAN, 0, 5) + '-' + dbo.LaySoChungTu( " + tthdCurrent + " ) " + 
					" where pk_seq = '" + tthdCurrent + "' ";
			
			System.out.println("[ERP_THANHTOANHOADON] error message:" + query);
			
			if(!db.update(query))
			{
				this.msg = "Khong the tao moi ERP_THUTIEN: " + query;
				System.out.println("[ErpThutien.createTTHD] error message:" + this.msg);
				db.getConnection().rollback();
				return false;
			}
			
			db.getConnection().commit();
			db.getConnection().setAutoCommit(true);
		}
		catch (Exception e) 
		{
			e.printStackTrace();
			
			System.out.println("Loi trong qua trinh update : "+e.toString());
			this.msg=e.toString();
			try 
			{
				db.getConnection().rollback();
			}
			catch (SQLException e1) {}
			return false;
		}
		
		return true;
	}

	public boolean updateTTHD() 
	{
		this.getNppInfo();
		if(!this.DoiTuongChiPhiKhac.equals("3")){
			if(this.hoadonList.size() < 0)
			{
				this.msg = "Không có hóa đơn nào để thanh toán";
				return false;
			}else{
				// neu hoa dơn nao bị bỏ chọn thì xóa trong bảng thanhtoanhoadonn_hoadon
				List<String> hdMois = new ArrayList<String>();
				for (IHoadon hd : hoadonList) {
					hdMois.add(hd.getId());
				}
				List<String> listHDHienTai = GetlistHoaDonHienTai();
				for (String hdHienTai : listHDHienTai) {
					if(!hdMois.contains(hdHienTai)){
						XoaThanhToanHoaDon_HoaDon(hdHienTai);
					}
				}
				
			}
		}
		
		if(this.htttId.length() <= 0)
		{
			this.msg = "Vui lòng chọn hình thức thanh toán";
			return false;
		}
		
		if(this.htttId.equals("100001"))
		{		
			if(this.sotaikhoan.trim().length() <= 0 )
			{			
				if(this.checkThanhtoantuTV.equals("1"))
					this.msg = "Vui lòng nhập Số tài khoản để trích phí ngân hàng !";
				else
					this.msg = "Vui lòng nhập Số tài khoản  !";
				return false;
			}
		}
		
			
		//Tinh lai tong tien
		double tongthanhtoan = 0;
		for(int i = 0; i < this.hoadonList.size(); i++)
		{
			IHoadon hd = this.hoadonList.get(i);
			if(hd.getThanhtoan().length() > 0)
				tongthanhtoan += Double.parseDouble(hd.getThanhtoan().replaceAll(",", ""));
		}
		
		if(this.DoiTuongChiPhiKhac.equals("3"))
		{
			tongthanhtoan = Double.parseDouble(this.sotientt.replaceAll(",", ""));
		}
		
		try 
		{
			String ngaysua = getDateTime();
			
			db.getConnection().setAutoCommit(false);
			
			if(this.DoiTuongDinhKhoan.trim().length() <= 0){
				this.DoiTuongDinhKhoan = "NULL";
			
			if(this.doituongdinhkhoanId.trim().length() <= 0)
				this.doituongdinhkhoanId = "NULL";
			}
			
			if(this.sotaikhoan.length() > 0){
				ResultSet rs = this.db.get("SELECT NGANHANG_FK, CHINHANH_FK FROM ERP_NGANHANG_CONGTY WHERE SOTAIKHOAN = '" + this.sotaikhoan + "'");
				System.out.println("SELECT NGANHANG_FK, CHINHANH_FK FROM ERP_NGANHANG_CONGTY WHERE SOTAIKHOAN = '" + this.sotaikhoan + "'");
				if(rs != null){
					rs.next();
					this.nganhangId = rs.getString("NGANHANG_FK");
					this.chinhanhId = rs.getString("CHINHANH_FK");
					rs.close();
				}
			}else{
			
				this.sotaikhoan = "NULL";
			}

			if(this.sotaikhoan_tp.length() > 0){
				ResultSet rs = this.db.get("SELECT NGANHANG_FK, CHINHANH_FK FROM ERP_NGANHANG_CONGTY WHERE SOTAIKHOAN = '" + this.sotaikhoan_tp + "'");
				System.out.println("SELECT NGANHANG_FK, CHINHANH_FK FROM ERP_NGANHANG_CONGTY WHERE SOTAIKHOAN = '" + this.sotaikhoan_tp + "'");
				if(rs != null){
					rs.next();
					this.nganhang_tpId = rs.getString("NGANHANG_FK");
					this.chinhanh_tpId = rs.getString("CHINHANH_FK");
					rs.close();
				}
			}else{
				this.sotaikhoan_tp = "NULL";

			}			
			String query = "";
			
			String isTICHLUY = "0";
			
			if(this.DoiTuongChiPhiKhac.equals("5")) isTICHLUY = "1";
			
      	    query = "update ERP_THANHTOANHOADON " +
					"set NGAYGHINHAN = '" + this.ngayghinhan + "', " +
					"DVTH_FK="+( this.bophanId.length() >0 ?this.bophanId :null)+" , " +
					"NCC_FK = " + ( this.nccId.length() >0 ?this.nccId :null)  + ", " +
					"nhanvien_fk="+( this.NhanvienId.length() >0 ?this.NhanvienId :null)+" , " +
					"HTTT_FK = '" + this.htttId + "', NGANHANG_FK = " + (this.nganhangId.length() == 0?null : this.nganhangId) + ", " +
					"CHINHANH_FK = " + (this.chinhanhId.length() == 0?null : this.chinhanhId) + ", " +
					"SOTAIKHOAN = '" + this.sotaikhoan + "'," +
					" NOIDUNGTT = N'" + this.noidungtt + "', " +
					"SOTIENTT = " + ("" + this.sotientt).replaceAll(",", "") + ", SOTIENTTNT = " + this.sotienttNT.replaceAll(",", "") + ", " +
					"SOTIENHD = " + this.sotienHD.replaceAll(",", "") + ", " +
					"SOTIENHDNT = " + this.sotienHDNT.replaceAll(",", "") + ", PHINGANHANG = " + this.pnganhang.replaceAll(",", "") + ", " +
					"PHINGANHANGNT = " + this.pnganhangNT.replaceAll(",", "") + ", " +
					"VAT = " + this.thueVAT.replaceAll(",", "") + ", VATNT = " + this.thueVATNT.replaceAll(",", "") + ", " +
					"CHENHLECHVND = " + this.chenhlechVND.replaceAll(",", "") + ", " +
					"TRICHPHI = " + this.trichphi + ", SOTAIKHOAN_TP = '" + this.sotaikhoan_tp + "', " +
					"NGANHANG_TP_FK = " + this.nganhang_tpId + ", CHINHANH_TP_FK = " + this.chinhanh_tpId + ", " +
					"NGAYSUA = '" + ngaysua + "', NGUOISUA = '" + this.userId + "', TIENTE_FK = " + this.tienteId + "," +
					" TIGIA = " + this.tigia.replaceAll(",", "") + ", THANHTOANTUTIENVAY = '"+	this.checkThanhtoantuTV +"', khachhang_fk="+( this.khachhangId.length() >0 ?this.khachhangId :null)+", CTKEMTHEO = N'"+this.chungtukemtheo+"', isTICHLUY = " +isTICHLUY+
					" where PK_SEQ = '"  + this.id + "'";
	
      	    	System.out.println("Update: " + query);
			
				if(this.DoiTuongDinhKhoan.equals("3"))
				{
					query = "update ERP_THANHTOANHOADON " +
							"set  NGAYGHINHAN = '" + this.ngayghinhan + "', " +
							"HTTT_FK = '" + this.htttId + "', NGANHANG_FK = " + (this.nganhangId.length() == 0?null : this.nganhangId) + ", " +
							"CHINHANH_FK = " + (this.chinhanhId.length() == 0?null : this.chinhanhId) + ", " +
							"SOTAIKHOAN = '" + this.sotaikhoan + "'," +
							" NOIDUNGTT = N'" + this.noidungtt + "', " +
							"SOTIENTT = " + ("" + this.sotientt).replaceAll(",", "") + ", SOTIENTTNT = " + this.sotienttNT.replaceAll(",", "") + ", " +
							"SOTIENHD = " + this.sotienHD.replaceAll(",", "") + ", " +
							"SOTIENHDNT = " + this.sotienHDNT.replaceAll(",", "") + ", PHINGANHANG = " + this.pnganhang.replaceAll(",", "") + ", " +
							"PHINGANHANGNT = " + this.pnganhangNT.replaceAll(",", "") + ", " +
							"VAT = " + this.thueVAT.replaceAll(",", "") + ", VATNT = " + this.thueVATNT.replaceAll(",", "") + ", " +
							"TRICHPHI = " + this.trichphi + ", SOTAIKHOAN_TP = '" + this.sotaikhoan_tp + "', " +
							"NGANHANG_TP_FK = " + this.nganhang_tpId + ", CHINHANH_TP_FK = " + this.chinhanh_tpId + ", " +
							"NGAYSUA = '" + ngaysua + "', NGUOISUA = '" + this.userId + "', TIENTE_FK = " + this.tienteId + ", TIGIA = " + this.tigia.replaceAll(",", "") + " ," +
						 	" DINHKHOANNO = '" + this.dinhkhoannoId + "', DOITUONGDINHKHOAN = " + this.DoiTuongDinhKhoan + ", MADOITUONGDINHKHOAN = " + this.doituongdinhkhoanId + " , THANHTOANTUTIENVAY = '"+	this.checkThanhtoantuTV +"', CTKEMTHEO = N'"+this.chungtukemtheo+"' " +
						 	" where PK_SEQ = '"  + this.id + "'";
				}
				
				// NẾU CÓ CHECK THANH TOÁN TỪ TIỀN VAY : KHI CẬP NHẬT CHỈ ĐƯỢC SỬA PHÍ NGÂN HÀNG + THUE CÒN LẠI KHÔNG CHO SỬA
				if(Double.parseDouble(this.checkThanhtoantuTV) == 1)
				{
					query = "update ERP_THANHTOANHOADON " +
							"set SOTIENTT = " + ("" + this.sotientt).replaceAll(",", "") + ", SOTIENTTNT = " + this.sotienttNT.replaceAll(",", "") + ", " +
							"PHINGANHANG = " + this.pnganhang.replaceAll(",", "") + ", PHINGANHANGNT = " + this.pnganhangNT.replaceAll(",", "") + ", " +
							"VAT = " + this.thueVAT.replaceAll(",", "") + ", VATNT = " + this.thueVATNT.replaceAll(",", "") + ", " +
							"CHENHLECHVND = " + this.chenhlechVND.replaceAll(",", "") + ", " +
							"NGAYSUA = '" + ngaysua + "', NGUOISUA = '" + this.userId + "', CTKEMTHEO = N'"+this.chungtukemtheo+"' " +
							" WHERE PK_SEQ = '"  + this.id + "'";
				}
			
			if(!db.update(query))
			{
				this.msg = "Khong the cap nhat ERP_THANHTOANHOADON: " + query;
				System.out.println(this.msg);
				db.getConnection().rollback();
				return false;
			}

			
			if(Double.parseDouble(this.checkThanhtoantuTV) == 0)
			{
				query = "delete ERP_THANHTOANHOADON_HOADON where THANHTOANHD_FK = '" + this.id + "'";
				db.update(query);
			}
			
			query = "delete ERP_THANHTOANHOADON_TAMUNG where THANHTOANHD_FK = '" + this.id + "'";
			db.update(query);
			
			query = "delete ERP_THANHTOANHOADON_CHIPHIKHAC where THANHTOANHD_FK = '" + this.id + "'";
			db.update(query);
			
			query = "delete ERP_THANHTOANHOADON_PHINGANHANG where THANHTOANHD_FK = '" + this.id + "'";
			db.update(query);
			
			query = "delete ERP_THANHTOANHOADON_HOADONBOPHAN where THANHTOANHD_FK = '" + this.id + "'";
			db.update(query); 
			
			query = "delete ERP_THANHTOANHOADON_HOADON_PHANBO where TTHD_FK = '" + this.id + "'";
			db.update(query);

			System.out.println("KICH thuoc hoa don " + this.hoadonList.size());
			
			if(this.bophanId.trim().length() > 0)
			{
				for(int i = 0; i < this.hoadonList.size(); i++)
				{
					IHoadon hoadon = this.hoadonList.get(i);

					String thanhtoan = (hoadon.getThanhtoan().replaceAll(",", ""));
					String avat = hoadon.getTongtiencoVAT().replaceAll(",", "");
					String sotienNT = hoadon.getSotienNT().replaceAll(",", "");
					String conlai = hoadon.getConlai().replaceAll(",", "");
					String loaihd = hoadon.getLoaihd1();
					String sohoadon = hoadon.getSo();
				
					String doituong =hoadon.getDoituong();
					String nhanvienId = "NULL";
					String nccId= "NULL";
					if(doituong.equals("0")) nhanvienId = hoadon.getDoituongId();
					else nccId = hoadon.getDoituongId();
					
					if(thanhtoan.length() > 0)
					{
						if(Float.parseFloat(thanhtoan) != 0)
						{
							if(this.tienteId.equals("100000")){
								query = "Insert ERP_THANHTOANHOADON_HOADONBOPHAN(THANHTOANHD_FK, HOADON_FK, SOTIENTT, SOTIENAVAT, SOTIENNT, CONLAI, LOAIHD, NHANVIEN_FK, NCC_FK, SOHOADON) " +
										"values('" + this.id + "', '" + hoadon.getId() + "', '" + thanhtoan.trim().replaceAll(",", "") + "', '" + avat.replaceAll(",", "") + "'," +
												" 0, '" + conlai.trim().replaceAll(",", "") + "', '"+ loaihd +"', "+ nhanvienId +", "+ nccId +", '"+ sohoadon +"')";
							
							}else{
								query = "Insert ERP_THANHTOANHOADON_HOADONBOPHAN(THANHTOANHD_FK, HOADON_FK, SOTIENTT, SOTIENAVAT, SOTIENNT, CONLAI, LOAIHD, NHANVIEN_FK, NCC_FK, SOHOADON) " +
										"values('" + this.id + "', '" + hoadon.getId() + "', '" + thanhtoan.trim().replaceAll(",", "") + "', '" + avat.replaceAll(",", "") + "', " + sotienNT.replaceAll(",", "") + "," +
												" '" + conlai.trim().replaceAll(",", "") + "', '"+ loaihd +"', "+ nhanvienId +", "+ nccId +", '"+ sohoadon +"')";																					

							}					
							if(!db.update(query))
							{
								this.msg = "Khong the tao moi ERP_THANHTOANHOADON_HOADONBOPHAN: " + query;
								System.out.println(this.msg);
								db.getConnection().rollback();
								return false;
							}
						}
					}
				}
			}
			else
			{
				for(int i = 0; i < this.hoadonList.size(); i++)
				{
					IHoadon hoadon = this.hoadonList.get(i);

					String thanhtoan = hoadon.getThanhtoan().replaceAll(",", "");
					String avat = hoadon.getTongtiencoVAT().replaceAll(",", "");
					String sotienNT = hoadon.getSotienNT().replaceAll(",", "");
					String conlai = hoadon.getConlai().replaceAll(",", "");
					String loaihd = hoadon.getLoaihd1();
					String sohoadon = hoadon.getSo();
					
					String tigiahd = "1";
					String tientehd = "100000";
					if(hoadon.getTigia()!=null) tigiahd = hoadon.getTigia().replaceAll(",", "");
					if(hoadon.getTienteId()!=null) tientehd = hoadon.getTienteId().replaceAll(",", "");
					
					
					if(thanhtoan.length() > 0)
					{
						if(Float.parseFloat(thanhtoan) != 0)
						{
							if(this.tienteId.equals("100000")){
								query = "Insert ERP_THANHTOANHOADON_HOADON(THANHTOANHD_FK, HOADON_FK, SOTIENTT, SOTIENAVAT, SOTIENNT, CONLAI, LOAIHD, SOHOADON, TIGIA, TIENTE_FK ) " +
										"values('" + this.id + "', '" + hoadon.getId() + "', '" + thanhtoan.trim().replaceAll(",", "") + "', '" + avat.replaceAll(",", "") + "'," +
										" 0, '" + conlai.trim().replaceAll(",", "") + "', '"+ loaihd +"', '"+ sohoadon +"', "+tigiahd+", "+tientehd+")";		
								if(loaihd.equals("4"))
								{
									query = "Insert ERP_THANHTOANHOADON_HOADON(THANHTOANHD_FK, HOADON_FK, SOTIENTT, SOTIENAVAT, SOTIENNT, CONLAI, LOAIHD, LOAITHUE, SOHOADON, TIGIA, TIENTE_FK ) " +
											"values('" + this.id + "', '" + hoadon.getId() + "', '" + thanhtoan.trim().replaceAll(",", "") + "', '" + avat.replaceAll(",", "") + "'," +
											" 0, '" + conlai.trim().replaceAll(",", "") + "', '"+ loaihd +"', N'"+ hoadon.getKyhieu() +"', '"+ sohoadon +"', "+tigiahd+", "+tientehd+")";		
								}
							
							}else{
								query = "Insert ERP_THANHTOANHOADON_HOADON(THANHTOANHD_FK, HOADON_FK, SOTIENTT, SOTIENAVAT, SOTIENNT, CONLAI, LOAIHD, SOHOADON, TIGIA, TIENTE_FK ) " +
										"values('" + this.id + "', '" + hoadon.getId() + "', '" + thanhtoan.trim().replaceAll(",", "") + "', '" + avat.replaceAll(",", "") + "', " + sotienNT.replaceAll(",", "") + "," +
										" '" + conlai.trim().replaceAll(",", "") + "', '"+ loaihd +"', '"+ sohoadon +"', "+tigiahd+", "+tientehd+")";																						
								if(loaihd.equals("4"))
								{
									query = "Insert ERP_THANHTOANHOADON_HOADON(THANHTOANHD_FK, HOADON_FK, SOTIENTT, SOTIENAVAT, SOTIENNT, CONLAI, LOAIHD, LOAITHUE, SOHOADON, TIGIA , TIENTE_FK ) " +
											"values('" + this.id + "', '" + hoadon.getId() + "', '" + thanhtoan.trim().replaceAll(",", "") + "', '" + avat.replaceAll(",", "") + "', " + sotienNT.replaceAll(",", "") + "," +
											" '" + conlai.trim().replaceAll(",", "") + "', '"+ loaihd +"', N'"+ hoadon.getKyhieu() +"', '"+ sohoadon +"', "+tigiahd+", "+tientehd+")";																					
							
								}
							}					
							if(!db.update(query))
							{
								this.msg = "Khong the tao moi ERP_THANHTOANHOADON_HOADON: " + query;
								System.out.println(this.msg);
								db.getConnection().rollback();
								return false;
							}
							
						}
					}
				}
			}
			
			if(!this.DoiTuongChiPhiKhac.equals("3"))
			{
				query = "INSERT INTO ERP_THANHTOANHOADON_PHINGANHANG(THANHTOANHD_FK, MAHOADON, MAUHOADON, KYHIEU, SOHOADON, " +
						"NGAYHOADON, TENNCC, MST, TIENHANG, THUESUAT, TIENTHUE, NGANHANG_FK, CHINHANH_FK) " +
						"VALUES(" + this.id + ", N'" + this.mahoadon + "', N'" + this.mauhoadon + "', N'" + this.kyhieu + "', '" + this.sohoadon + "'," +
						"'" + this.ngayghinhan + "', N'" + this.tenNCC + "', '" + this.mst + "', " + this.tienhang.replaceAll(",", "") + ", " + this.thuesuat.replaceAll(",", "") + ", " + this.tienthue.replaceAll(",", "") + ", " +
						"" + (this.nhId_VAT.length() == 0?null : this.nhId_VAT) + ", " + (this.cnId_VAT.length() == 0?null : this.cnId_VAT) + ")";
				System.out.println(query);
				if(!db.update(query))
				{
					this.msg = "Khong the tao moi ERP_THANHTOANHOADON_PHINGANHANG: " + query;
					System.out.println(this.msg);
					db.getConnection().rollback();
					return false;
				}
			}
			else
			{
				int i=0; 
				if(Tienhanghd !=null)
				{
					while(Tienhanghd.length>i)
					{						
						if(Tienhanghd[i].trim().length() <=0)
							Tienhanghd[i] = "0";
						
						if(Double.parseDouble(Tienhanghd[i].trim().replaceAll(",", "")) > 0)
						{
							if(Thuesuathd[i].replaceAll(",", "").trim().length()<=0)
								Thuesuathd[i]  = "0";
							if(Tienthuehd[i].replaceAll(",", "").trim().length()<=0)
								Tienthuehd[i]  = "0";							
							if(PhongBanIds[i].trim().length()<=0)
								PhongBanIds[i]  = "null";							
							if(KenhbhIds[i].trim().length()<=0)
								KenhbhIds[i]  = "null";
							if(TinhThanhIds[i].trim().length()<=0)
								TinhThanhIds[i]  = "null";							
							if(SanPhamIds[i].trim().length()<=0)
								SanPhamIds[i]  = "null";
							if(DiabanIds[i].trim().length()<=0)
								DiabanIds[i]  = "null";
							if(MavvIds[i].trim().length()<=0)
								MavvIds[i]  = "null";
							if(BenhvienIds[i].trim().length()<=0)
								BenhvienIds[i]  = "null";
							if(Diengiaihd[i].trim().length()<=0)
								Diengiaihd[i]  = "";
							
							if(TaiKhoanIds[i]!="")
							{
								String []TkId=TaiKhoanIds[i].split("_");
								
								query = "SELECT DUNGCHOKHO, DUNGCHONCC, DUNGCHONGANHANG, DUNGCHOTAISAN, DUNGCHOKHACHHANG, COTTCHIPHI, DUNGCHONHANVIEN, SOHIEUTAIKHOAN FROM ERP_TAIKHOANKT WHERE PK_SEQ = " + TkId[0] + "";
								System.out.println(query);
								ResultSet rs  = this.db.get(query);
								rs.next();
																
								System.out.println("dcIds[i]:"+dcIds[i]);
								
								// KIẾM TRA XEM CÓ CHỌN ĐỐI TƯỢNG CHƯA
								if(rs.getString("DUNGCHONHANVIEN").equals("1")&&dcIds[i].equals("")){
									
									this.db.getConnection().rollback();
									this.msg = "Vui lòng chọn mã đối tượng.";
									return false;
								}
								
								if(rs.getString("DUNGCHONCC").equals("1")&&dcIds[i].equals("")){
									
									this.db.getConnection().rollback();
									this.msg = "Vui lòng chọn mã đối tượng.";
									return false;
								}
								
								if(rs.getString("DUNGCHONGANHANG").equals("1")&&dcIds[i].equals("")){
									
									this.db.getConnection().rollback();
									this.msg = "Vui lòng chọn mã đối tượng.";
									return false;
								}

								if(rs.getString("DUNGCHOTAISAN").equals("1")&&dcIds[i].equals("")){
									
									this.db.getConnection().rollback();
									this.msg = "Vui lòng chọn mã đối tượng.";
									return false;
								}
								
								if(rs.getString("DUNGCHOKHACHHANG").equals("1")&&dcIds[i].equals("")){
									
									this.db.getConnection().rollback();
									this.msg = "Vui lòng chọn mã đối tượng.";
									return false;
								}
																
								if(rs.getString("DUNGCHONHANVIEN").equals("1")&&dcIds[i].equals("")){
									
									this.db.getConnection().rollback();
									this.msg = "Vui lòng chọn mã đối tượng.";
									return false;
								}
								
								if(dcIds[i].trim().length()<=0)
									dcIds[i]  = "null";
								
								// Dùng cho : 1 Kho, 2 NCC, 3 Ngân hàng, 4 Tài Sản, 5 Khách hàng, 6 TTCP, 7 Nhân viên																
								
								if(loais[i].equals("1") ){
									query="INSERT INTO ERP_THANHTOANHOADON_PHINGANHANG(THANHTOANHD_FK, KYHIEU, SOHOADON,NGAYHOADON, TENNCC, MST, TIENHANG, THUESUAT, TIENTHUE, SANPHAM_FK, TAIKHOAN_FK, PHONGBAN_FK, KBH_FK, TINHTHANH_FK, DIABAN_FK, VUVIEC_FK, BENHVIEN_FK, SP_FK , DIENGIAI ) VALUES " +
										" ('"+this.id+"', N'" + Kyhieuhd[i] + "', '" + Sohd[i] + "'," +
										"'" + Ngayhd[i] + "', N'" + TenNCChd[i] + "', '" + MSThd[i] + "', " + Tienhanghd[i].replaceAll(",", "") + ", " + Thuesuathd[i].replaceAll(",", "") + ", " + Tienthuehd[i].replaceAll(",", "") + ", " +
										dcIds[i]+", "+TkId[0]+","+PhongBanIds[i]+","+KenhbhIds[i]+", "+TinhThanhIds[i]+", "+DiabanIds[i]+", "+MavvIds[i]+", "+ BenhvienIds[i] + ", " + SanPhamIds[i]+", N'"+Diengiaihd[i]+"')";
									
								}else						
								if(loais[i].equals("2") ){
									query="INSERT INTO ERP_THANHTOANHOADON_PHINGANHANG(THANHTOANHD_FK, KYHIEU, SOHOADON,NGAYHOADON, TENNCC, MST, TIENHANG, THUESUAT, TIENTHUE, NCC_FK, TAIKHOAN_FK, PHONGBAN_FK, KBH_FK, TINHTHANH_FK, DIABAN_FK, VUVIEC_FK, BENHVIEN_FK, SP_FK, DIENGIAI ) VALUES " +
											" ('"+this.id+"', N'" + Kyhieuhd[i] + "', '" + Sohd[i] + "'," +
											"'" + Ngayhd[i] + "', N'" + TenNCChd[i] + "', '" + MSThd[i] + "', " + Tienhanghd[i].replaceAll(",", "") + ", " + Thuesuathd[i].replaceAll(",", "") + ", " + Tienthuehd[i].replaceAll(",", "") + ", " +
											dcIds[i]+", "+TkId[0]+","+PhongBanIds[i]+","+KenhbhIds[i]+", "+TinhThanhIds[i]+", "+DiabanIds[i]+", "+MavvIds[i]+", "+ BenhvienIds[i] + ", " + SanPhamIds[i]+", N'"+Diengiaihd[i]+"')";
									
								}else						
								if(loais[i].equals("3") ){
									query="INSERT INTO ERP_THANHTOANHOADON_PHINGANHANG(THANHTOANHD_FK, KYHIEU, SOHOADON,NGAYHOADON, TENNCC, MST, TIENHANG, THUESUAT, TIENTHUE, NGANHANG_FK, TAIKHOAN_FK, PHONGBAN_FK, KBH_FK, TINHTHANH_FK, DIABAN_FK, VUVIEC_FK, BENHVIEN_FK, SP_FK, DIENGIAI ) VALUES " +
											" ('"+this.id+"', N'" + Kyhieuhd[i] + "', '" + Sohd[i] + "'," +
											"'" + Ngayhd[i] + "', N'" + TenNCChd[i] + "', '" + MSThd[i] + "', " + Tienhanghd[i].replaceAll(",", "") + ", " + Thuesuathd[i].replaceAll(",", "") + ", " + Tienthuehd[i].replaceAll(",", "") + ", " +
											dcIds[i]+", "+TkId[0]+","+PhongBanIds[i]+","+KenhbhIds[i]+", "+TinhThanhIds[i]+", "+DiabanIds[i]+", "+MavvIds[i]+", "+ BenhvienIds[i] + ", " + SanPhamIds[i]+", N'"+Diengiaihd[i]+"')";									
								}else
								if(loais[i].equals("4") ){
									query="INSERT INTO ERP_THANHTOANHOADON_PHINGANHANG(THANHTOANHD_FK, KYHIEU, SOHOADON,NGAYHOADON, TENNCC, MST, TIENHANG, THUESUAT, TIENTHUE,TAISAN_FK, TAIKHOAN_FK, PHONGBAN_FK, KBH_FK, TINHTHANH_FK, DIABAN_FK, VUVIEC_FK, BENHVIEN_FK, SP_FK , DIENGIAI) VALUES " +
											" ('"+this.id+"', N'" + Kyhieuhd[i] + "', '" + Sohd[i] + "'," +
											"'" + Ngayhd[i] + "', N'" + TenNCChd[i] + "', '" + MSThd[i] + "', " + Tienhanghd[i].replaceAll(",", "") + ", " + Thuesuathd[i].replaceAll(",", "") + ", " + Tienthuehd[i].replaceAll(",", "") + ", " +
											dcIds[i]+", "+TkId[0]+","+PhongBanIds[i]+","+KenhbhIds[i]+", "+TinhThanhIds[i]+", "+DiabanIds[i]+", "+MavvIds[i]+", "+ BenhvienIds[i] + ", " + SanPhamIds[i]+", N'"+Diengiaihd[i]+"')";
									
								}else
								if(loais[i].equals("5") ){
									
									//CHỖ NÀY ĐỂ PHÂN BIỆT LÀ NPP HAY KHÁCH HÀNG HAY NHÂN VIÊN
									String isNPP = "NULL";
									String kh_noId = "null";		
									
									kh_noId = dcIds[i];
									
									if( kh_noId.contains("-") )
									{
										String kh[] = kh_noId.split("-");
										kh_noId = kh[0];
										isNPP = kh[1];
									}
									
									query="INSERT INTO ERP_THANHTOANHOADON_PHINGANHANG(THANHTOANHD_FK, KYHIEU, SOHOADON,NGAYHOADON, TENNCC, MST, TIENHANG, THUESUAT, TIENTHUE, KHACHHANG_FK, TAIKHOAN_FK, PHONGBAN_FK, KBH_FK, TINHTHANH_FK, DIABAN_FK, VUVIEC_FK, BENHVIEN_FK, SP_FK, DIENGIAI , ISNPP) VALUES " +
											" ('"+this.id+"', N'" + Kyhieuhd[i] + "', '" + Sohd[i] + "'," +
											"'" + Ngayhd[i] + "', N'" + TenNCChd[i] + "', '" + MSThd[i] + "', " + Tienhanghd[i].replaceAll(",", "") + ", " + Thuesuathd[i].replaceAll(",", "") + ", " + Tienthuehd[i].replaceAll(",", "") + ", " +
											kh_noId+", "+TkId[0]+","+PhongBanIds[i]+","+KenhbhIds[i]+", "+TinhThanhIds[i]+", "+DiabanIds[i]+", "+MavvIds[i]+", "+ BenhvienIds[i] + ", " + SanPhamIds[i]+", N'"+Diengiaihd[i]+"', "+isNPP+")";
									
								}else 
								if(loais[i].equals("7") ){
									query="INSERT INTO ERP_THANHTOANHOADON_PHINGANHANG(THANHTOANHD_FK, KYHIEU, SOHOADON,NGAYHOADON, TENNCC, MST, TIENHANG, THUESUAT, TIENTHUE, NHANVIEN_FK, TAIKHOAN_FK, PHONGBAN_FK, KBH_FK, TINHTHANH_FK, DIABAN_FK, VUVIEC_FK, BENHVIEN_FK , SP_FK, DIENGIAI ) VALUES " +
											" ('"+this.id+"', N'" + Kyhieuhd[i] + "', '" + Sohd[i] + "'," +
											"'" + Ngayhd[i] + "', N'" + TenNCChd[i] + "', '" + MSThd[i] + "', " + Tienhanghd[i].replaceAll(",", "") + ", " + Thuesuathd[i].replaceAll(",", "") + ", " + Tienthuehd[i].replaceAll(",", "") + ", " +
											dcIds[i]+", "+TkId[0]+","+PhongBanIds[i]+","+KenhbhIds[i]+", "+TinhThanhIds[i]+", "+DiabanIds[i]+", "+MavvIds[i]+", "+ BenhvienIds[i] + ", " + SanPhamIds[i]+", N'"+Diengiaihd[i]+"')";
									
								}else
								if(loais[i].equals("6") ){
									query="INSERT INTO ERP_THANHTOANHOADON_PHINGANHANG(THANHTOANHD_FK, KYHIEU, SOHOADON,NGAYHOADON, TENNCC, MST, TIENHANG, THUESUAT, TIENTHUE, TTCP_FK, TAIKHOAN_FK, PHONGBAN_FK, KBH_FK, TINHTHANH_FK, DIABAN_FK, VUVIEC_FK, BENHVIEN_FK, SP_FK, DIENGIAI ) VALUES " +
											" ('"+this.id+"', N'" + Kyhieuhd[i] + "', '" + Sohd[i] + "'," +
											"'" + Ngayhd[i] + "', N'" + TenNCChd[i] + "', '" + MSThd[i] + "', " + Tienhanghd[i].replaceAll(",", "") + ", " + Thuesuathd[i].replaceAll(",", "") + ", " + Tienthuehd[i].replaceAll(",", "") + ", " +
											dcIds[i]+", "+TkId[0]+","+PhongBanIds[i]+","+KenhbhIds[i]+", "+TinhThanhIds[i]+", " +DiabanIds[i]+", "+MavvIds[i]+", "+ BenhvienIds[i] + ", " + SanPhamIds[i]+", N'"+Diengiaihd[i]+"')";									
								}								
								else {
									query="INSERT INTO ERP_THANHTOANHOADON_PHINGANHANG(THANHTOANHD_FK, KYHIEU, SOHOADON,NGAYHOADON, TENNCC, MST, TIENHANG, THUESUAT, TIENTHUE, TAIKHOAN_FK, PHONGBAN_FK, KBH_FK, TINHTHANH_FK, DIABAN_FK, VUVIEC_FK, BENHVIEN_FK, SP_FK, DIENGIAI ) VALUES " +
											" ('"+this.id+"', N'" + Kyhieuhd[i] + "', '" + Sohd[i] + "'," +
											"'" + Ngayhd[i] + "', N'" + TenNCChd[i] + "', '" + MSThd[i] + "', " + Tienhanghd[i].replaceAll(",", "") + ", " + Thuesuathd[i].replaceAll(",", "") + ", " + Tienthuehd[i].replaceAll(",", "") + ", " +
											TkId[0]+","+PhongBanIds[i]+","+KenhbhIds[i]+", "+TinhThanhIds[i]+", "+DiabanIds[i]+", "+MavvIds[i]+", "+ BenhvienIds[i] + ", " + SanPhamIds[i]+", N'"+Diengiaihd[i]+"')";
								}
														
								System.out.println(query);
								if(!this.db.update(query))
								{
									this.db.getConnection().rollback();
									this.msg="Loi dong lenh sau "+query;
									return false;
								}
							
							}
							else
							{
								this.msg="Vui lòng chọn tài khoản !";
								return false;
							}
							
					}
						
						i++;
				}
					
			}	
			
			}

			// cập nhật mã chứng từ
			query = " update ERP_THANHTOANHOADON set machungtu =  'DNPC' + SUBSTRING(NGAYGHINHAN, 6, 2) + SUBSTRING(NGAYGHINHAN, 0, 5) + '-' + dbo.LaySoChungTu( " + this.id + " ) " + 
					" where pk_seq = '" + this.id + "' ";
			
			System.out.println("[ERP_THANHTOANHOADON] error message:" + query);
			
			if(!db.update(query))
			{
				this.msg = "Khong the tao moi ERP_THANHTOANHOADON: " + query;
				System.out.println("[ErpThutien.createTTHD] error message:" + this.msg);
				db.getConnection().rollback();
				return false;
			}
			
			db.getConnection().commit();
			db.getConnection().setAutoCommit(true);
		}
		catch (SQLException e) 
		{
			try 
			{
				db.getConnection().rollback();
			}
			catch (SQLException e1) {}
			return false;
		}
		
		return true;
	}

	private void XoaThanhToanHoaDon_HoaDon(String hdHienTai) {
		
		
		String sql ="DELETE FROM ERP_THANHTOANHOADON_HOADON\n" +
		"WHERE ERP_THANHTOANHOADON_HOADON.THANHTOANHD_FK = '" + this.id+
		"' AND ERP_THANHTOANHOADON_HOADON.HOADON_FK = '" +hdHienTai+		"'";
		db.update(sql);
		
	}

	private List<String> GetlistHoaDonHienTai() {
		
		
		String sql  = " SELECT ERP_THANHTOANHOADON_HOADON.HOADON_FK FROM ERP_THANHTOANHOADON_HOADON\n" +
		" WHERE ERP_THANHTOANHOADON_HOADON.THANHTOANHD_FK = '" +this.id+"'";
		ResultSet resultSet = db.get(sql);
		List<String> list  = new ArrayList<String>();
		String[] rerult = null;
		try {
//				rerult = (String[])resultSet.getArray("HOADON_FK").getArray();
			
			while (resultSet.next()) {
				list.add(resultSet.getString("HOADON_FK"));
			}
			
			resultSet.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
		return  list;
		
		
	}
	
	public boolean checkNhomNCC()
	{
		String check = "0";
		String query = "select case when NHOMNCCCN IS null then 0 else 1 end as checkNhom from ERP_THANHTOANHOADON "+
						"where PK_SEQ = '" + this.id + "'" ;
		ResultSet checkNhomRs = db.get(query);
		try {
			while(checkNhomRs.next())
			{
				check = checkNhomRs.getString("checkNhom");
			}
			checkNhomRs.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		if (check.equalsIgnoreCase("1"))
			return true;
		else
			return false;
		
	}
	

	public boolean chotTTHD(String userId)
	{
				
		try 
		{
			String ngaysua = getDateTime();
			
			db.getConnection().setAutoCommit(false);
			
			//kiểm tra số lớn nhất.
			String query = "select max(cast(ISNULL(RIGHT(sochungtu, LEN(SOCHUNGTU) - 2), 0) as nvarchar(50))) SoCtMax from ERP_THANHTOANHOADON where HTTT_FK = (SELECT HTTT_FK FROM ERP_THANHTOANHOADON WHERE PK_SEQ = "+this.id+") and TRANGTHAI not in (2) and ISKTTDUYET = 1 AND NPP_fk = "+this.nppdangnhap;
			
			System.out.println(query);
			ResultSet rs = db.get(query);
			
			int SoCtMax = 0;
			String SoCt =  "";
			
			if(rs!= null)
			{
				while(rs.next())
				{
					SoCtMax = rs.getInt("SoCtMax");
				}rs.close();
			}
			
			SoCtMax = SoCtMax + 1;
			//Nếu số chứng từ  < 10 thì thêm số 0
			if(SoCtMax<10)
			{
				SoCt = "0"+SoCtMax;
			}
			else
				SoCt = "" +SoCtMax;
			
			query = " UPDATE ERP_THANHTOANHOADON SET sochungtu = RIGHT(PREFIX,2)+'"+SoCt+"'  WHERE PK_SEQ = '" + this.id + "' ";					
			if(!this.db.update(query)) 
			{
				db.getConnection().rollback();
				return false;
			}
						
			query = "UPDATE ERP_THANHTOANHOADON set TRANGTHAI = '1', NGUOISUA = '" + userId + "', NGAYSUA = '" + ngaysua + "' where PK_SEQ = '"  + this.id + "'";
			System.out.println("1.Cap nhat hoadon: " + query);
			
			if(!db.update(query))
			{
				this.msg = "Khong the chot ERP_THANHTOANHOADON: " + query;
				System.out.println(this.msg);
				db.getConnection().rollback();
				return false;
			}

			//  CẬP NHẬT ĐÃ THANH TOÁN CỦA ĐỀ NGHỊ TẠM ỨNG
			
			query = "UPDATE ERP_TAMUNG SET ISTHANHTOAN = 1 WHERE PK_SEQ IN (SELECT HOADON_FK FROM ERP_THANHTOANHOADON_HOADON WHERE THANHTOANHD_FK = "+this.id+" AND LOAIHD = 1 ) ";
			
			System.out.println("1.Cap nhat đề nghị tạm ứng " + query);
			
			if(!db.update(query))
			{
				this.msg = "Khong the chot ERP_TAMUNG: " + query;
				System.out.println(this.msg);
				db.getConnection().rollback();
				return false;
			}
			
			//  CẬP NHẬT ĐÃ THANH TOÁN CỦA ĐỀ NGHỊ THANH TOÁN
			
			query = "UPDATE ERP_MUAHANG SET ISTHANHTOAN = 1 WHERE PK_SEQ IN (SELECT HOADON_FK FROM ERP_THANHTOANHOADON_HOADON WHERE THANHTOANHD_FK = "+this.id+" AND LOAIHD = 6 ) ";
			
			System.out.println("1.Cap nhat đề nghị tạm ứng " + query);
			
			if(!db.update(query))
			{
				this.msg = "Khong the chot ERP_TAMUNG: " + query;
				System.out.println(this.msg);
				db.getConnection().rollback();
				return false;
			}
 		
			
		Utility util = new Utility();
		
			
		//GHI NHAN KE TOAN
		String hinhthuctt = "";
		String tigia = "";
	
		String nam = "";
		String thang = "";
		String tiente_fk = "";
		String ncc_fk = "";
		String nhanvien_fk = "";
		String khachhang_fk = "";
		String nganhang_fk = "";
		
		System.out.println("Cai kt Chi khac "+query);
		
		if(this.DoiTuongChiPhiKhac.equals("3")) //  KHAC
		{
			query = "select  a.httt_fk, a.SOTIENHD, \n" +
					"    	 (select pk_seq from erp_taikhoankt where sohieutaikhoan= '11110000' AND NPP_FK = "+this.nppdangnhap+") as taikhoanCO_tienhang, \n"+
					"    	 (select pk_seq from erp_taikhoankt where sohieutaikhoan= '13311000' AND NPP_FK = "+this.nppdangnhap+") as taikhoanNO_tienthue, \n"+
					"\n 		isnull(b.TIENHANG, 0) tienhang, isnull(b.TIENTHUE,0) tienthue, b.TAIKHOAN_FK  as taikhoanNO,  " +				    	
				  	"\n 		case when b.KHACHHANG_FK is not null then N'Khách hàng' "+
					"\n   		when b.KHO_FK is not null then N'Sản phẩm' "+
					"\n   		when b.NCC_FK is not null then N'Nhà cung cấp' "+
					"\n   		when b.NGANHANG_FK is not null then N'Ngân hàng' "+
					"\n		    when b.NHANVIEN_FK is not null then N'Nhân viên' "+
					"\n		    when b.TTCP_FK is not null then N'Trung tâm chi phí' "+
					"\n   		when b.TAISAN_FK   is not null then N'Tài sản' else '' end as doituong,  "+
				  	"\n			case when b.KHACHHANG_FK is not null then CAST(b.KHACHHANG_FK as nvarchar(50)) "+
					"\n   		when b.SANPHAM_FK is not null then CAST( b.SANPHAM_FK as nvarchar(50)) "+
					"\n   		when b.NCC_FK is not null then CAST (b.NCC_FK as nvarchar(50)) "+
					"\n   		when b.NGANHANG_FK is not null then CAST( b.NGANHANG_FK as nvarchar(50)) "+
					"\n		    when b.NHANVIEN_FK is not null then CAST( b.NHANVIEN_FK as nvarchar(50)) "+
					"\n   		when b.TAISAN_FK is not null then CAST( b.TAISAN_FK as nvarchar(50)) " +
					"\n			when b.TTCP_FK is not null then CAST( b.TTCP_FK as nvarchar(50)) "+
					"\n   		else '' end as madoituong, a.NGAYGHINHAN , a.TIENTE_FK , isnull(a.TIGIA,1) as TIGIA, a.NGANHANG_FK, a.NOIDUNGTT, A.SOCHUNGTU MACHUNGTU, ISNULL(b.ISNPP,'') ISNPP , "+
					"\n			b.MAHOADON, b.MAUHOADON, b.KYHIEU, b.SOHOADON, b.NGAYHOADON,b.TENNCC,b.MST, b.TIENHANG, b.THUESUAT, "+
				  	"\n			case when b.SANPHAM_FK IS NOT NULL then sp_dt.TEN "+
				  	"\n			when b.NCC_FK IS NOT NULL THEN ncc_dt.TEN "+
				  	"\n			when b.NGANHANG_FK IS NOT NULL THEN nh_dt.TEN "+
				  	"\n 		when b.TAISAN_FK IS NOT NULL then ts_dt.TEN "+
				  	"\n 		when b.KHACHHANG_FK IS NOT NULL then kh_dt.TEN "+
				  	"\n			when b.NHANVIEN_FK IS NOT NULL then nv_dt.TEN "+
				  	"\n			when b.TTCP_FK IS NOT NULL then ttcp_dt.TEN "+
				  	"\n			else '' END TEN_DT, pb.TEN TEN_PB,kbh.TEN TEN_KBH,vv.TEN TEN_VV, db.TEN TEN_DIABAN, "+
				  	"\n			tt.TEN TEN_TINHTHANH,bv.TEN TEN_BENHVIEN,sp.TEN TEN_SANPHAM, "+
				   	"\n			case when b.SANPHAM_FK IS NOT NULL then sp_dt.MA_FAST "+
				   	"\n			when b.NCC_FK IS NOT NULL THEN ncc_dt.MA "+
				   	"\n			when b.NGANHANG_FK IS NOT NULL THEN nh_dt.MA "+
				   	"\n			when b.TAISAN_FK IS NOT NULL then ts_dt.MAFAST "+
				   	"\n  	    when b.KHACHHANG_FK IS NOT NULL then kh_dt.maFAST "+
					"\n	  		when b.NHANVIEN_FK IS NOT NULL then nv_dt.MA "+
					"\n	  		when b.TTCP_FK IS NOT NULL then ttcp_dt.MA "+
					"\n	  		else '' END MAFAST_DT, b.DIENGIAI DIENGIAICT  "+					 
					"\n	from ERP_THANHTOANHOADON a inner join ERP_THANHTOANHOADON_PHINGANHANG b on a.PK_SEQ = b.THANHTOANHD_FK "+
					"\n	LEFT JOIN ERP_NGANHANG nh_dt on b.NGANHANG_FK = nh_dt.PK_SEQ "+
					"\n	LEFT JOIN ERP_DONVITHUCHIEN pb on b.PHONGBAN_FK = pb.PK_SEQ "+
					"\n	LEFT JOIN KENHBANHANG kbh on b.KBH_FK = kbh.PK_SEQ "+
					"\n	LEFT JOIN TraphacoERP.dbo.VUVIEC vv on b.VUVIEC_FK = vv.PK_SEQ "+
					"\n	LEFT JOIN TraphacoERP.dbo.DIABAN db on b.DIABAN_FK = db.PK_SEQ "+
					"\n	LEFT JOIN TraphacoERP.dbo.TINHTHANH tt on b.TINHTHANH_FK = tt.PK_SEQ "+
					"\n	LEFT JOIN KHACHHANG bv on b.BENHVIEN_FK = bv.PK_SEQ "+
					"\n	LEFT JOIN SANPHAM sp on b.SP_FK = sp.PK_SEQ "+
					"\n	LEFT JOIN SANPHAM sp_dt on b.SANPHAM_FK = sp_dt.PK_SEQ "+
					"\n	LEFT JOIN TraphacoERP.dbo.ERP_NHACUNGCAP ncc_dt on b.NCC_FK = ncc_dt.PK_SEQ "+
					"\n	LEFT JOIN KHACHHANG kh_dt on b.KHACHHANG_FK = kh_dt.PK_SEQ "+
					"\n	LEFT JOIN TraphacoERP.dbo.ERP_TAISANCODINH ts_dt on b.TAISAN_FK = ts_dt.pk_seq "+
					"\n	LEFT JOIN ERP_NHANVIEN nv_dt on b.NHANVIEN_FK = nv_dt.PK_SEQ "+
					"\n	LEFT JOIN TraphacoERP.dbo.ERP_TRUNGTAMCHIPHI ttcp_dt on ttcp_dt.PK_SEQ = b.TTCP_FK "+
					"\n	where a.PK_SEQ = '" + this.id + "'";
			
			System.out.println("Câu lấy tiền vat/phinganhang1: "+query);
			
			ResultSet ktRs = db.get(query);
			if(ktRs != null)
			{
				
				while(ktRs.next())
				{						
					double tienhang = ktRs.getDouble("TIENHANG");
					double tienthue = ktRs.getDouble("tienthue");
					
					String taikhoanCO = "";
					String taikhoanNO = "";
					
					String loaidoituongco = "";
					String madoiduongco = "";
					
					String loaidoituongno = "";
					String madoiduongno = "";
					
					String diengiai = ktRs.getString("NOIDUNGTT");
					String machungtu = ktRs.getString("machungtu");
					String isNPP = ktRs.getString("isNPP");
					
					nam = ktRs.getString("ngayghinhan").substring(0, 4);
					thang = ktRs.getString("ngayghinhan").substring(5, 7);
					tiente_fk = ktRs.getString("tiente_fk");
					tigia = ktRs.getString("tigia");
					
					String MAHOADON = ktRs.getString("MAHOADON")== null ? "": ktRs.getString("MAHOADON");
					String MAUHOADON = ktRs.getString("MAUHOADON")== null ? "": ktRs.getString("MAUHOADON"); 
					String KYHIEU = ktRs.getString("KYHIEU")== null ? "": ktRs.getString("KYHIEU"); 
					String SOHOADON = ktRs.getString("SOHOADON")== null ? "": ktRs.getString("SOHOADON"); 
					String NGAYHOADON = ktRs.getString("NGAYHOADON")== null ? "": ktRs.getString("NGAYHOADON"); 
					String TENNCC = ktRs.getString("TENNCC")== null ? "": ktRs.getString("TENNCC"); 
					String MST = ktRs.getString("MST")== null ? "": ktRs.getString("MST"); 
					String TIENHANG = ktRs.getString("TIENHANG")== null ? "": ktRs.getString("TIENHANG"); 
					String THUESUAT = ktRs.getString("THUESUAT")== null ? "": ktRs.getString("THUESUAT"); 
					String TIENTHUE = ktRs.getString("TIENTHUE")== null ? "": ktRs.getString("TIENTHUE"); 
					String MAFAST_DT = ktRs.getString("MAFAST_DT")== null ? "": ktRs.getString("MAFAST_DT"); 
					String TEN_DT = ktRs.getString("TEN_DT")== null ? "": ktRs.getString("TEN_DT"); 
					String TEN_PB = ktRs.getString("TEN_PB")== null ? "": ktRs.getString("TEN_PB"); 
					String TEN_KBH = ktRs.getString("TEN_KBH")== null ? "": ktRs.getString("TEN_KBH"); 
					String TEN_VV = ktRs.getString("TEN_VV")== null ? "": ktRs.getString("TEN_VV"); 
					String TEN_DIABAN = ktRs.getString("TEN_DIABAN")== null ? "": ktRs.getString("TEN_DIABAN"); 
					String TEN_TINHTHANH = ktRs.getString("TEN_TINHTHANH")== null ? "": ktRs.getString("TEN_TINHTHANH"); 
					String TEN_BENHVIEN = ktRs.getString("TEN_BENHVIEN")== null ? "": ktRs.getString("TEN_BENHVIEN"); 
					String TEN_SANPHAM = ktRs.getString("TEN_SANPHAM")== null ? "": ktRs.getString("TEN_SANPHAM"); 
					String DIENGIAI_CT = ktRs.getString("DIENGIAICT")== null ? "": ktRs.getString("DIENGIAICT"); 
					
					
				if(Double.parseDouble(this.checkThanhtoantuTV) <= 0)
				{
					// GHI NHAN SO TIEN TT
					
					if(tienhang > 0)
					{
						taikhoanCO =ktRs.getString("taikhoanCO_tienhang")== null?"": ktRs.getString("taikhoanCO_tienhang");
						taikhoanNO = ktRs.getString("taikhoanNO")== null?"": ktRs.getString("taikhoanNO");
							
						madoiduongno = ktRs.getString("madoituong");
						loaidoituongno = ktRs.getString("doituong");
						
						loaidoituongco = "";
						madoiduongco = "";
						
						if( taikhoanCO.trim().length() <= 0 || taikhoanNO.trim().length() <= 0 )
						{
							this.msg = "Lỗi xác định tài khoản kế toán. Vui lòng kiểm tra lại thông tin dữ liệu nền trước khi chốt.";
							db.getConnection().rollback();
							return false;
						}
													
						msg = util.Update_TaiKhoan_Vat_DienGiai_CHIKHAC( db, thang, nam, ktRs.getString("ngayghinhan"), ktRs.getString("ngayghinhan"), "Trả khác", this.id, taikhoanNO, taikhoanCO, "", 
							  Double.toString(tienhang), Double.toString(tienhang), loaidoituongno, madoiduongno, 
							  loaidoituongco, madoiduongco, "0", "", "", tiente_fk, "", "1", Double.toString(tienhang), Double.toString(tienhang), "Tiền hàng", Double.toString(tienthue)  , diengiai , machungtu, isNPP,
							  MAHOADON, MAUHOADON, KYHIEU, SOHOADON,  NGAYHOADON,  TENNCC, MST,  TIENHANG,  THUESUAT,MAFAST_DT,  TEN_DT,  TEN_PB,
								TEN_KBH,  TEN_VV,  TEN_DIABAN,  TEN_TINHTHANH,  TEN_BENHVIEN,  TEN_SANPHAM, DIENGIAI_CT	);
						if(this.msg.trim().length() > 0)
						{
							ktRs.close();
							db.getConnection().rollback();
							return false;
						}
					}
					
					
					if(tienthue > 0)
					{
						taikhoanCO =ktRs.getString("taikhoanCO_tienhang")== null?"": ktRs.getString("taikhoanCO_tienhang");
						taikhoanNO = ktRs.getString("taikhoanNO_tienthue")== null?"": ktRs.getString("taikhoanNO_tienthue");
							
						madoiduongno = ktRs.getString("madoituong");
						loaidoituongno = ktRs.getString("doituong");
						
						loaidoituongco = "";
						madoiduongco = "";
						
						if( taikhoanCO.trim().length() <= 0 || taikhoanNO.trim().length() <= 0 )
						{
							this.msg = "Lỗi xác định tài khoản kế toán. Vui lòng kiểm tra lại thông tin dữ liệu nền trước khi chốt.";
							db.getConnection().rollback();
							return false;
						}
						
						this.msg = util.Update_TaiKhoan_Vat_DienGiai_CHIKHAC( db, thang, nam, ktRs.getString("ngayghinhan"), ktRs.getString("ngayghinhan"), "Trả khác", this.id, taikhoanNO, taikhoanCO, "", 
						Double.toString(tienthue), Double.toString(tienthue), loaidoituongno, madoiduongno, 
						loaidoituongco, madoiduongco, "0", "", "", tiente_fk, "", tigia, Double.toString(tienthue), Double.toString(tienthue), "Tiền thuế", Double.toString(tienthue)  , diengiai , machungtu, isNPP,
						  MAHOADON, MAUHOADON, KYHIEU, SOHOADON,  NGAYHOADON,  TENNCC, MST,  TIENHANG,  THUESUAT,MAFAST_DT,  TEN_DT,  TEN_PB,
							TEN_KBH,  TEN_VV,  TEN_DIABAN,  TEN_TINHTHANH,  TEN_BENHVIEN,  TEN_SANPHAM, DIENGIAI_CT	);
						if(this.msg.trim().length() > 0)
						{
							ktRs.close();
							db.getConnection().rollback();
							return false;
						}
					}
					
				}
					
				}
				ktRs.close();
			}
										
		}
		else
		{
		
			{
		  // 1. TIỀN VAT, PHÍ NGÂN HÀNG GIỐNG NHAU CHO MỌI TRƯỜNG HỢP
			query =" select  TTHD.CHENHLECHVND, ISNULL(TTHD.PHINGANHANG, 0) AS PHINGANHANG , TTHD.TRICHPHI, \n" +
			   "         ISNULL(TTHD.VAT,0) AS VAT,  TTHD.HTTT_FK as HINHTHUCTT, TTHD.TIENTE_FK, TTHD.KHACHHANG_FK,  \n"+
			   "	     TTHD.NGAYGHINHAN ,TTHD.NCC_FK , TTHD.NGANHANG_FK , TTHD.NGANHANG_TP_FK , isnull(TTHD.TIGIA,1) as TIGIA, \n" +
			   "         ISNULL(TTHD.CHENHLECHVND,0) AS CHENHLECHVND , TTHD.NHANVIEN_FK, NCC.TAIKHOAN_FK as taikhoanNO_NCC, NV.TAIKHOAN_FK as taikhoanNO_NV, \n"+
		       "        ( select TaiKhoan_fk from ERP_NGANHANG_CONGTY where  SoTaiKhoan = TTHD.SOTAIKHOAN AND NPP_FK = "+this.nppdangnhap+") as taikhoanCO_NH , \n"+
		       "        ( select TaiKhoan_fk from ERP_NGANHANG_CONGTY where  SoTaiKhoan = TTHD.SOTAIKHOAN_TP AND NPP_FK = "+this.nppdangnhap+") as taikhoanCO_NH_TP, \n"+
		       "        (select pk_seq from ERP_TAIKHOANKT where SOHIEUTAIKHOAN = '11110000' AND NPP_FK = "+this.nppdangnhap+") as taikhoan_TIENVND, \n"+
		       "        (select pk_seq from ERP_TAIKHOANKT where SOHIEUTAIKHOAN = '64253000' AND NPP_FK = "+this.nppdangnhap+") as taikhoanNO_PHINH, \n"+
		       "        (select pk_seq from ERP_TAIKHOANKT where SOHIEUTAIKHOAN = '13311000' AND NPP_FK = "+this.nppdangnhap+") as taikhoan_THUE, \n" +
		       "        (select pk_seq from ERP_TAIKHOANKT where SOHIEUTAIKHOAN = '63580000' AND NPP_FK = "+this.nppdangnhap+") as taikhoanNO_CHENHLECH, \n" +
		       "        (select pk_seq from ERP_TAIKHOANKT where SOHIEUTAIKHOAN = '51580000' AND NPP_FK = "+this.nppdangnhap+") as taikhoanCO_CHENHLECH, \n"+
		       "   		TTHD.NOIDUNGTT, TTHD.sochungtu machungtu, ISNULL( TTHD.ISNPP, '') ISNPP " +
		       " from   ERP_THANHTOANHOADON TTHD  left join ERP_NHACUNGCAP NCC on TTHD.NCC_FK= NCC.PK_SEQ \n" +
		       "                                  left join ERP_NHANVIEN NV on TTHD.NHANVIEN_FK = NV.PK_SEQ \n"+
		       " where TTHD.PK_SEQ = "+ this.id +"  ";
			
			System.out.println("Câu lấy tiền vat/phinganhang2: "+query);
			
			ResultSet RsRs = db.get(query);
			if(RsRs != null)
			{
				
				while(RsRs.next())
				{
					String diengiai = RsRs.getString("NOIDUNGTT");
					String machungtu = RsRs.getString("machungtu");
					String isNPP = RsRs.getString("ISNPP");
					
					hinhthuctt = RsRs.getString("HINHTHUCTT");
					tigia = RsRs.getString("tigia");
	
					ncc_fk = RsRs.getString("NCC_FK") == null ? "":RsRs.getString("NCC_FK");
					nhanvien_fk = RsRs.getString("NHANVIEN_FK") == null ? "":RsRs.getString("NHANVIEN_FK");
					khachhang_fk = RsRs.getString("KHACHHANG_FK") == null ? "":RsRs.getString("KHACHHANG_FK");
					
					nam = RsRs.getString("ngayghinhan").substring(0, 4);
					thang = RsRs.getString("ngayghinhan").substring(5, 7);
					tiente_fk = RsRs.getString("tiente_fk");
					
					nganhang_fk= RsRs.getString("NGANHANG_FK")== null ?"":RsRs.getString("NGANHANG_FK") ;
					String nganhangTP_fk= RsRs.getString("NGANHANG_TP_FK")== null ?"":RsRs.getString("NGANHANG_TP_FK") ;
					String trichphi_fk = RsRs.getString("TRICHPHI")== null ?"":RsRs.getString("TRICHPHI") ;
					
					String taikhoanCO = "";
					String taikhoanNO = "";
					
					double phinganhang= RsRs.getDouble("PHINGANHANG");
					
					double vat = RsRs.getDouble("VAT");
					
					double chenhlech = RsRs.getDouble("CHENHLECHVND");
					
			//1.a PHÍ NGÂN HÀNG + VAT
			   // THANHTOAN: NGOAI TE, TRICH PHI BANG VND						     
					  if(!tiente_fk.equals("100000")&& trichphi_fk.equals("1") )
					  {							 	
						 //GHI NHAN PHINGANHANG

							if(phinganhang > 0)
							{
								taikhoanNO = RsRs.getString("taikhoanNO_PHINH") == null ? "":RsRs.getString("taikhoanNO_PHINH") ;
								taikhoanCO = RsRs.getString("taikhoanCO_NH_TP") == null ? "":RsRs.getString("taikhoanCO_NH_TP");
								
								if( taikhoanCO.trim().length() <= 0 || taikhoanNO.trim().length() <= 0 )
								{
									this.msg = "Lỗi xác định tài khoản kế toán. Vui lòng kiểm tra lại thông tin dữ liệu nền trước khi chốt.";
									db.getConnection().rollback();
									return false;
								}
								
								this.msg = util.Update_TaiKhoan_Vat_DienGiai( db, thang, nam, RsRs.getString("NGAYGHINHAN"), RsRs.getString("NGAYGHINHAN"), "Thanh toán hóa đơn", this.id, taikhoanNO, taikhoanCO, "", 
								Double.toString(phinganhang), Double.toString(phinganhang), "BANK CHARGES", "", 
								"Ngân hàng", "Ngân hàng", "0", "", "", tiente_fk, "", tigia, Double.toString(phinganhang), Double.toString(phinganhang), "PHÍ NGÂN HÀNG", "0" , diengiai , machungtu, isNPP);
																			
								if(this.msg.trim().length() > 0)
								{
									RsRs.close();
									db.getConnection().rollback();
									return false;
								}
							}
						//GHI NHAN VAT		
							if(vat > 0)
							{
								taikhoanNO = RsRs.getString("taikhoan_THUE")== null?"":RsRs.getString("taikhoan_THUE");
								String doituong = "";
								String madoituong = "";
								if(hinhthuctt.equals("100001")) //THANH TOAN: NGANHANG
								{
									taikhoanCO = RsRs.getString("taikhoanCO_NH_TP")== null?"":RsRs.getString("taikhoanCO_NH_TP");
									doituong= "Ngân hàng";
									madoituong= nganhangTP_fk;
								}
								else
								{
									taikhoanCO = RsRs.getString("taikhoan_TIENVND");
								}
								
								if( taikhoanCO.trim().length() <= 0 || taikhoanNO.trim().length() <= 0 )
								{
									this.msg = "Lỗi xác định tài khoản kế toán. Vui lòng kiểm tra lại thông tin dữ liệu nền trước khi chốt.";
									db.getConnection().rollback();
									return false;
								}
								
								this.msg = util.Update_TaiKhoan_Vat_DienGiai( db, thang, nam, RsRs.getString("NGAYGHINHAN"), RsRs.getString("NGAYGHINHAN"), "Thanh toán hóa đơn", this.id, taikhoanNO, taikhoanCO, "", 
								Double.toString(vat), Double.toString(vat), "", "", doituong, madoituong, "0", "", "", tiente_fk, "", tigia, Double.toString(vat), Double.toString(vat), "VAT", "0" , diengiai , machungtu, isNPP);
																
								if(this.msg.trim().length() > 0)
								{
									RsRs.close();
									db.getConnection().rollback();
									return false;
								}
							}
						
					  }							
					else// TRUONG HOP CON LAI THANH TOÁN BẰNG VND
					{
						 // GHI NHAN PHINGANHANG

							if(phinganhang > 0)
							{
								taikhoanNO = RsRs.getString("taikhoanNO_PHINH") == null ? "": RsRs.getString("taikhoanNO_PHINH") ;
								taikhoanCO = RsRs.getString("taikhoanCO_NH") == null ? "": RsRs.getString("taikhoanCO_NH") ;
								
								if( taikhoanCO.trim().length() <= 0 || taikhoanNO.trim().length() <= 0 )
								{
									this.msg = "Lỗi xác định tài khoản kế toán. Vui lòng kiểm tra lại thông tin dữ liệu nền trước khi chốt.";
									db.getConnection().rollback();
									return false;
								}
								
								this.msg = util.Update_TaiKhoan_Vat_DienGiai( db, thang, nam, RsRs.getString("NGAYGHINHAN"), RsRs.getString("NGAYGHINHAN"), "Thanh toán hóa đơn", this.id, taikhoanNO, taikhoanCO, "", 
								Double.toString(phinganhang), Double.toString(phinganhang), "BANK CHARGES", "", "Ngân hàng", nganhang_fk, "0", "", "", tiente_fk, "", tigia, Double.toString(phinganhang), Double.toString(phinganhang), "PHÍ NGÂN HÀNG", "0" , diengiai , machungtu, isNPP);
								
								if(this.msg.trim().length() > 0)
								{
									RsRs.close();
									db.getConnection().rollback();
									return false;
								}
							}
						//  GHI NHAN VAT		
							if(vat > 0)
							{
								taikhoanNO = RsRs.getString("taikhoan_THUE") == null? "":RsRs.getString("taikhoan_THUE")  ;
								String doituong = "";
								String madoituong = "";
								if(hinhthuctt.equals("100001")) //THANH TOAN: NGANHANG
								{
									taikhoanCO = RsRs.getString("taikhoanCO_NH") == null ? "":RsRs.getString("taikhoanCO_NH");
									doituong= "Ngân hàng";
									madoituong= nganhang_fk;
								}
								else
								{
									taikhoanCO = RsRs.getString("taikhoan_TIENVND") == null ?"": RsRs.getString("taikhoan_TIENVND")  ;
								}
								
								if( taikhoanCO.trim().length() <= 0 || taikhoanNO.trim().length() <= 0 )
								{
									this.msg = "Lỗi xác định tài khoản kế toán. Vui lòng kiểm tra lại thông tin dữ liệu nền trước khi chốt.";
									db.getConnection().rollback();
									return false;
								}
								
								this.msg = util.Update_TaiKhoan_Vat_DienGiai( db, thang, nam, RsRs.getString("NGAYGHINHAN"), RsRs.getString("NGAYGHINHAN"), "Thanh toán hóa đơn", this.id, taikhoanNO, taikhoanCO, "", 
								Double.toString(vat), Double.toString(vat), "", "", doituong, madoituong, "0", "", "", tiente_fk, "", tigia, Double.toString(vat), Double.toString(vat), "VAT", "0" , diengiai , machungtu, isNPP);
							
								if(this.msg.trim().length() > 0)
								{
									RsRs.close();
									db.getConnection().rollback();
									return false;
								}
							}
						
					}
					  
		/*	 // 1.b.CHÊNH LỆCH	
						if(chenhlech!= 0)
						{
							String loaidoituong = "Nhà cung cấp";
							String madoituong = ncc_fk;
							
							if(chenhlech > 0) 
							{
								taikhoanNO = RsRs.getString("taikhoanNO_CHENHLECH")== null ?"": RsRs.getString("taikhoanNO_CHENHLECH");
								taikhoanCO = RsRs.getString("taikhoanNO_NCC")== null ?"": RsRs.getString("taikhoanNO_NCC");
								
								if(nhanvien_fk.trim().length() > 0)
								{
									loaidoituong = "Nhân viên";
									madoituong = nhanvien_fk;
									taikhoanCO = RsRs.getString("taikhoanNO_NV")== null ?"": RsRs.getString("taikhoanNO_NV");
								}
								
								if( taikhoanCO.trim().length() <= 0 || taikhoanNO.trim().length() <= 0 )
								{
									this.msg = "Lỗi xác định tài khoản kế toán. Vui lòng kiểm tra lại thông tin dữ liệu nền trước khi chốt.";
									db.getConnection().rollback();
									return false;
								}
								
								this.msg = util.Update_TaiKhoan_Vat_DienGiai( db, thang, nam, RsRs.getString("NGAYGHINHAN"), RsRs.getString("NGAYGHINHAN"), "Thanh toán hóa đơn", this.id, taikhoanNO, taikhoanCO, "", 
								Double.toString(chenhlech), Double.toString(chenhlech), "", "", loaidoituong, madoituong, "0", "", "", tiente_fk, "", tigia, Double.toString(chenhlech), Double.toString(chenhlech), "", "0" , diengiai , machungtu, isNPP);
									
								
								if(this.msg.trim().length() > 0)
								{
									RsRs.close();
									db.getConnection().rollback();
									return false;
								}
							}
							if(chenhlech < 0)
							{
								taikhoanNO = RsRs.getString("taikhoanNO_NCC")== null ?"":  RsRs.getString("taikhoanNO_NCC");
								taikhoanCO = RsRs.getString("taikhoanCO_CHENHLECH")== null ?"": RsRs.getString("taikhoanCO_CHENHLECH");
								
								if(nhanvien_fk.trim().length() > 0)
								{
									loaidoituong = "Nhân viên";
									madoituong = nhanvien_fk;
									taikhoanNO = RsRs.getString("taikhoanNO_NV") == null ?"":  RsRs.getString("taikhoanNO_NV");
								}
								
								if( taikhoanCO.trim().length() <= 0 || taikhoanNO.trim().length() <= 0 )
								{
									this.msg = "Lỗi xác định tài khoản kế toán. Vui lòng kiểm tra lại thông tin dữ liệu nền trước khi chốt.";
									db.getConnection().rollback();
									return false;
								}
								
								this.msg = util.Update_TaiKhoan_Vat_DienGiai( db, thang, nam, RsRs.getString("NGAYGHINHAN"), RsRs.getString("NGAYGHINHAN"), "Thanh toán hóa đơn", this.id, taikhoanNO, taikhoanCO, "", 
								Double.toString(chenhlech*(-1)), Double.toString(chenhlech*(-1)), loaidoituong, madoituong, "", "", "0", "", "", tiente_fk, "", tigia, Double.toString(chenhlech*(-1)), Double.toString(chenhlech*(-1)), "", "0" , diengiai , machungtu, isNPP);
								
								if(this.msg.trim().length() > 0)
								{
									RsRs.close();
									db.getConnection().rollback();
									return false;
								}
							}
						}  */
					
				}
				RsRs.close();
			}
			
		 if(Double.parseDouble(this.checkThanhtoantuTV) <= 0 )	
		 {
			    if(this.DoiTuongChiPhiKhac.equals("1"))   // NCC
				{
			    	
			    	// ĐỀ NGHỊ THANH TOÁN - CÀI RIÊNG
					
					query = " SELECT TTHD.NGANHANG_FK , TTHD_HD.SOTIENTT, TTHD_HD.LOAIHD, TTHD_HD.HOADON_FK, TTHD.NGAYGHINHAN, TTHD.TIENTE_FK,   \n" +
							" ( SELECT TaiKhoan_fk from ERP_NGANHANG_CONGTY where  SoTaiKhoan = TTHD.SOTAIKHOAN AND NPP_FK = "+ this.nppdangnhap + " ) as taikhoan_NH, "+
					        " ( SELECT pk_seq from ERP_TAIKHOANKT where SOHIEUTAIKHOAN = '11110000' AND NPP_FK = "+ this.nppdangnhap + ") as taikhoan_TIENVND, "+
					        " TTHD.NOIDUNGTT, TTHD.SOCHUNGTU MACHUNGTU, ISNULL(TTHD.ISNPP, '') ISNPP \n"+
							" FROM ERP_THANHTOANHOADON TTHD \n" +
							" INNER JOIN ERP_THANHTOANHOADON_HOADON TTHD_HD ON TTHD.PK_SEQ = TTHD_HD.THANHTOANHD_FK \n"+
							" WHERE TTHD.PK_SEQ = "+this.id +" AND TTHD_HD.LOAIHD = 6 ";
					
					System.out.println(query);
					rs = db.get(query);
					
					if(rs!=null)
					{
						while(rs.next())
						{
							String loaihd = rs.getString("LOAIHD");
							String hoadonId = rs.getString("HOADON_FK");
							String taikhoanCO_NH = rs.getString("taikhoan_NH");
							String taikhoanCO_TM = rs.getString("taikhoan_TIENVND");
							
							String diengiai = rs.getString("NOIDUNGTT");
							String machungtu = rs.getString("MACHUNGTU");									
							String isNPP = rs.getString("ISNPP");
							nam = rs.getString("ngayghinhan").substring(0, 4);
							thang = rs.getString("ngayghinhan").substring(5, 7);
							tiente_fk = rs.getString("tiente_fk");
							
							String taikhoanCO = rs.getString("NGANHANG_FK");
																
							if(loaihd.equals("6"))// ĐỀ NGHỊ THANH TOÁN
							{
								// chạy định khoản
								
								String taikhoanCO_DS = "";
								String taikhoanNO_DS = "";
								
								String taikhoanCO_VAT = "";
								String taikhoanNO_VAT = "";
								
								String loaidoituongCO = "";
								String madoituongCO = "";
								
								String loaidoituongNO = "";
								String madoituongNO = "";
								
								
								query= "  SELECT N'CHI PHÍ' AS LOAIDOITUONGNO, E.PK_SEQ AS MADOITUONGNO,    " +  
									   "  CASE  WHEN A.NHACUNGCAP_FK  IS NOT NULL THEN N'NHÀ CUNG CẤP'  WHEN A.NHANVIEN_FK  IS NOT NULL THEN N'NHÂN VIÊN' ELSE N'KHÁCH HÀNG' END AS LOAIDOITUONGCO,   " +  
									   "  CASE  WHEN  A.NHACUNGCAP_FK  IS NOT NULL THEN NCC.PK_SEQ WHEN A.NHANVIEN_FK  IS NOT NULL THEN NV.PK_SEQ ELSE KH.PK_SEQ END AS MADOITUONGCO, " +  
									   "  A.NGAYMUA AS NGAYHOADON, (D.SOLUONG* D.DONGIA)  AS DOANHSO,(D.SOLUONG* D.DONGIA)*  D.PHANTRAMTHUE/100 AS THUE   ,    " +  
									   "  CASE  WHEN A.NHACUNGCAP_FK  IS NOT NULL THEN NCC.TAIKHOAN_FK WHEN A.NHANVIEN_FK  IS NOT NULL THEN NV.TAIKHOAN_FK ELSE KH.TAIKHOAN_FK END AS TAIKHOANCO_DS,   " +  
									   "  CASE  WHEN A.NHACUNGCAP_FK  IS NOT NULL THEN NCC.TAIKHOAN_FK WHEN A.NHANVIEN_FK  IS NOT NULL THEN NV.TAIKHOAN_FK ELSE KH.TAIKHOAN_FK  END AS TAIKHOANCO_VAT,   " +  
									   "  (SELECT PK_SEQ FROM ERP_TAIKHOANKT WHERE SOHIEUTAIKHOAN = E.TAIKHOAN_FK AND NPP_FK = A.NPP_FK) AS TAIKHOANNO_DS,   " +  
									   "  ( SELECT PK_SEQ FROM ERP_TAIKHOANKT WHERE SOHIEUTAIKHOAN = '13311000' AND NPP_FK = A.NPP_FK  ) AS TAIKHOANNO_VAT   " +  
									   "  FROM ERP_MUAHANG A  " +  
									   "  LEFT JOIN ERP_NHANVIEN NV ON NV.PK_SEQ = A.NHANVIEN_FK    " +  
									   "  LEFT JOIN ERP_NHACUNGCAP NCC ON A.NHACUNGCAP_FK = NCC.PK_SEQ   " + 
									   "  LEFT JOIN KHACHHANG KH ON A.KHACHHANG_FK = KH.PK_SEQ   " +  
									   "  LEFT JOIN ERP_MUAHANG_SP D ON A.PK_SEQ = D.MUAHANG_FK   " +  
									   "  LEFT JOIN ERP_NHOMCHIPHI E ON D.CHIPHI_FK = E.PK_SEQ   " +  
									   "  WHERE A.PK_SEQ = "+hoadonId;
								
								System.out.println("Câu tk11"+ query);
								
								ResultSet rsTk = db.get(query);
								if(rsTk!= null)
								{
									while(rsTk.next())
									{										
										double totalDS = Math.round(rsTk.getDouble("DOANHSO"));
										double totalVAT = Math.round(rsTk.getDouble("THUE"));
										
										taikhoanCO_DS = rsTk.getString("taikhoanCO_DS") == null ? "": rsTk.getString("taikhoanCO_DS") ;
										taikhoanNO_DS = rsTk.getString("taikhoanNO_DS") == null ? "": rsTk.getString("taikhoanNO_DS")  ;
										
										taikhoanCO_VAT = rsTk.getString("taikhoanCO_VAT") == null ? "": rsTk.getString("taikhoanCO_VAT")  ;
										taikhoanNO_VAT = rsTk.getString("taikhoanNO_VAT") == null ? "": rsTk.getString("taikhoanNO_VAT") ;
																																									
										String ngayghinhan = rsTk.getString("ngayhoadon");			
									
										if(totalDS > 0)
										{
											loaidoituongNO = rsTk.getString("loaidoituongNO"); // CHI PHÍ
											madoituongNO = rsTk.getString("madoituongNO"); // MÃ CHI PHÍ
											
											loaidoituongCO = rsTk.getString("LOAIDOITUONGCO"); 
											madoituongCO = rsTk.getString("MADOITUONGCO");
											
											if(taikhoanCO_DS.trim().length() <= 0 || taikhoanNO_DS.trim().length() <= 0 )
											{
												msg = "Lỗi xác định tài khoản kế toán. Vui lòng kiểm tra lại thông tin mã chi phí trước khi chốt.12";
												db.getConnection().rollback();
											 
												return false;
											}
											
											this.msg = util.Update_TaiKhoan_Vat_DienGiai( db, thang, nam, ngayghinhan, ngayghinhan, "Chi phí khác", this.id, taikhoanNO_DS, taikhoanCO_TM, "", 
													Double.toString(totalDS), Double.toString(totalDS), loaidoituongNO, madoituongNO, loaidoituongCO, madoituongCO, "0", "", "", tiente_fk, "", tigia, Double.toString(totalDS), Double.toString(totalDS), "Doanh số", Double.toString(totalVAT) , diengiai , machungtu, isNPP);
																
											if(msg.trim().length() > 0)
											{
												db.getConnection().rollback();
												return false;
											}
											
										}
										
										if(totalVAT > 0)
										{
											loaidoituongNO = ""; 
											madoituongNO = ""; 
											
											loaidoituongCO = rsTk.getString("LOAIDOITUONGCO"); 
											madoituongCO = rsTk.getString("MADOITUONGCO");
											
											if(taikhoanCO_VAT.trim().length() <= 0 || taikhoanNO_VAT.trim().length() <= 0 )
											{
												msg = "Lỗi xác định tài khoản kế toán. Vui lòng kiểm tra lại thông tin mã chi phí trước khi chốt.11";
												db.getConnection().rollback();
												return false;
											}	
											
											this.msg = util.Update_TaiKhoan_Vat_DienGiai( db, thang, nam, ngayghinhan, ngayghinhan, "Chi phí khác", this.id, taikhoanNO_VAT, taikhoanCO_TM, "", 
													Double.toString(totalVAT), Double.toString(totalVAT), loaidoituongNO, madoituongNO, loaidoituongCO, madoituongCO, "0", "", "", tiente_fk, "", tigia, Double.toString(totalVAT), Double.toString(totalVAT), "VAT", "0" , diengiai , machungtu, isNPP);
											
											if(msg.trim().length() > 0)
											{
												db.getConnection().rollback();
												return false;
											}
										}
										
									}
									rsTk.close();
								}
							}
							
						}
					}
					
			    	
					{										
						// NHỮNG LOẠI HĐ LIÊN QUAN ĐẾN NCC TRỪ ĐỀ NGHỊ THANH TOÁN THÌ CÀI RIÊNG									
						
						query = " SELECT  TTHD.NGANHANG_FK , NCC.TAIKHOAN_FK as taikhoan_NCC,  TTHD.NGAYGHINHAN , \n"+
								"        ( select TaiKhoan_fk from ERP_NGANHANG_CONGTY where  SoTaiKhoan = TTHD.SOTAIKHOAN AND NPP_FK = "+ this.nppdangnhap + "  ) as taikhoan_NH , \n"+
								"        ( select pk_seq from ERP_TAIKHOANKT where SOHIEUTAIKHOAN = '11110000' AND NPP_FK = "+ this.nppdangnhap + " ) as taikhoan_TIENVND, \n"+
								"        TTHD.TIENTE_FK , case when TTHD.TIENTE_FK = 100000 then TTHD_HD.SOTIENTT else isnull(TTHD_HD.SOTIENTT,0)* ISNULL(TTHD_HD.TIGIA,1) end AS TIENTHANHTOAN_HD_VND, \n"+
								"        TTHD.TIENTE_FK , case when TTHD.TIENTE_FK = 100000 then TTHD_HD.SOTIENTT else isnull(TTHD_HD.SOTIENTT,0) end AS TIENTHANHTOAN_HD_NT, \n"+
								"        ( case when TTHD.TIENTE_FK = 100000 then TTHD_HD.SOTIENTT else isnull(TTHD_HD.SOTIENTT,0)* ISNULL(TTHD_HD.TIGIA,1) end ) \n"+
								"        - ( case when TTHD.TIENTE_FK = 100000 then TTHD_HD.SOTIENTT else isnull(TTHD_HD.SOTIENTT,0)* ISNULL(TTHD.TIGIA,1) end ) as TIENCHENHLECH , \n"+
								"        TTHD.NOIDUNGTT, TTHD.SOCHUNGTU MACHUNGTU, ISNULL(TTHD.ISNPP,0) ISNPP, TTHD_HD.TIGIA , \n"+
								"        (SELECT PK_SEQ FROM ERP_TAIKHOANKT WHERE SOHIEUTAIKHOAN = '51580000' and NPP_FK = "+ this.nppdangnhap + " ) TAIKHOAN_51580000, \n"+
								"		  (select pk_seq from ERP_TAIKHOANKT where SOHIEUTAIKHOAN = '11110000' AND NPP_FK = "+ this.nppdangnhap + ") as taikhoan_11110000, \n"+
								"		  (SELECT PK_SEQ FROM ERP_TAIKHOANKT WHERE SOHIEUTAIKHOAN = '63580000' and NPP_FK = "+ this.nppdangnhap + " ) TAIKHOAN_63580000 \n"+
								" FROM   ERP_THANHTOANHOADON TTHD \n"+
								" inner join ERP_NHACUNGCAP NCC on TTHD.NCC_FK= NCC.PK_SEQ \n"+
								" inner join ERP_THANHTOANHOADON_HOADON TTHD_HD ON TTHD.pk_seq = TTHD_HD.THANHTOANHD_FK \n"+
								" WHERE TTHD.PK_SEQ =  " + this.id + " ";
						/*query = " SELECT  TTHD.NGANHANG_FK ,NCC.TAIKHOAN_FK as taikhoanNO_NCC,  TTHD.NGAYGHINHAN , \n"+
								"        ( select TaiKhoan_fk from ERP_NGANHANG_CONGTY where  SoTaiKhoan = TTHD.SOTAIKHOAN AND CONGTY_FK = "+ this.ctyId + " ) as taikhoanCO_NH , \n"+
								"        (select pk_seq from ERP_TAIKHOANKT where SOHIEUTAIKHOAN = '11110000' AND CONGTY_FK = "+ this.ctyId + ") as taikhoan_TIENVND, \n"+
								"        ( case when TTHD.TIENTE_FK = 100000 then (select SUM(SOTIENTT) from ERP_THANHTOANHOADON_HOADON where THANHTOANHD_FK = "+ this.id + " and LOAIHD in (0,1,2,3,5) ) \n"+
								"              else (select SUM(SOTIENTT) from ERP_THANHTOANHOADON_HOADON where THANHTOANHD_FK = "+ this.id + " and LOAIHD in (0,1,2,3,5) ) * ISNULL(TTHD.TIGIA,1) \n"+
								"              end)  - isnull(TTHD.CHENHLECHVND,0) as tonggiatri, TTHD.NOIDUNGTT, TTHD.SOCHUNGTU MACHUNGTU, ISNULL(TTHD.ISNPP,0) ISNPP \n"+
								" FROM   ERP_THANHTOANHOADON TTHD \n" +
								" inner join ERP_NHACUNGCAP NCC on TTHD.NCC_FK= NCC.PK_SEQ \n"+
								" inner join ERP_THANHTOANHOADON_HOADON TTHD_HD ON TTHD.pk_seq = TTHD_HD.THANHTOANHD_FK \n"+ 
								" WHERE TTHD.PK_SEQ = " + this.id + " ";*/
						System.out.println("Câu query NCC " + query);
						ResultSet psktRs = db.get(query);
						if (psktRs != null) {
							while (psktRs.next()) {

								String taikhoanNO = "";
								String loaidoituongco = "";
								String madoiduongco = "";
								String loaidoituongno = "";
								String madoituongno = "";
								String taikhoanCO = "";
								
								String diengiai = psktRs.getString("NOIDUNGTT");
								String machungtu = psktRs.getString("MACHUNGTU");
								String isNPP = psktRs.getString("ISNPP");
								nganhang_fk = psktRs.getString("NGANHANG_FK");

								tigia = psktRs.getString("TIGIA");
								
								double TIENTHANHTOAN_HD_VND = psktRs.getDouble("TIENTHANHTOAN_HD_VND"); 
								double TIENTHANHTOAN_HD_NT = psktRs.getDouble("TIENTHANHTOAN_HD_NT"); 
								
								double TIENCHENHLECH = psktRs.getDouble("TIENCHENHLECH"); 
								
								//double tonggiatri = Math.round(psktRs.getDouble("tonggiatri"));

								if (Double.parseDouble(this.checkThanhtoantuTV) <= 0) {
									if (TIENTHANHTOAN_HD_VND > 0) {

										taikhoanNO = psktRs.getString("taikhoan_NCC") == null ? "": psktRs.getString("taikhoan_NCC");
										loaidoituongno = "Nhà cung cấp"; 
										madoituongno = ncc_fk;												
										
										if (hinhthuctt.equals("100001") ) // THANH// TOAN:// NGANHANG
										{
											taikhoanCO = psktRs.getString("taikhoan_NH") == null ? "": psktRs.getString("taikhoan_NH");
											loaidoituongco = "Ngân hàng";
											madoiduongco = nganhang_fk;

										} else // TIEN MAT
										{
											taikhoanCO = psktRs.getString("taikhoan_TIENVND");
										}

										if (taikhoanCO.trim().length() <= 0 || taikhoanNO.trim().length() <= 0) {
											this.msg = "Lỗi xác định tài khoản kế toán. Vui lòng kiểm tra lại thông tin dữ liệu nền trước khi chốt.";
											db.getConnection().rollback();
											return false;
										}

										this.msg = util.Update_TaiKhoan_Vat_DienGiai( db, thang, nam, psktRs.getString("NGAYGHINHAN"), psktRs.getString("NGAYGHINHAN"), "Thanh toán hóa đơn", this.id, taikhoanNO, taikhoanCO, "", 
										Double.toString(TIENTHANHTOAN_HD_VND), Double.toString(TIENTHANHTOAN_HD_VND), "Nhà cung cấp", ncc_fk, loaidoituongco, madoiduongco, "0", "", "", tiente_fk, "", tigia, Double.toString(TIENTHANHTOAN_HD_VND), Double.toString(TIENTHANHTOAN_HD_NT), "", "0" , diengiai , machungtu, isNPP);
			
									
										if (this.msg.trim().length() > 0) {
											psktRs.close();
											db.getConnection().rollback();
											return false;
										}
									}
									
									if(TIENCHENHLECH > 0)
									{
										if(hinhthuctt.equals("100001") ) // NGÂN HÀNG
										{
											taikhoanNO = psktRs.getString("taikhoan_NH") == null ? "": psktRs.getString("taikhoan_NH");
											loaidoituongno = "Ngân hàng"; 
											madoituongno = nganhang_fk;	
											
											taikhoanCO = psktRs.getString("TAIKHOAN_51580000") == null ? "": psktRs.getString("TAIKHOAN_51580000");
											loaidoituongco = ""; 
											madoiduongco = "";	
											
											tiente_fk = "100000";
											tigia = "1";
											
											if (taikhoanCO.trim().length() <= 0 || taikhoanNO.trim().length() <= 0) {
												this.msg = "Lỗi xác định tài khoản kế toán. Vui lòng kiểm tra lại thông tin dữ liệu nền trước khi chốt.";
												db.getConnection().rollback();
												return false;
											}
	
											
											this.msg = util.Update_TaiKhoan_Vat_DienGiai( db, thang, nam, psktRs.getString("NGAYGHINHAN"), psktRs.getString("NGAYGHINHAN"), "Thanh toán hóa đơn", this.id, taikhoanNO, taikhoanCO, "", 
													Double.toString(TIENCHENHLECH), Double.toString(TIENCHENHLECH), loaidoituongno, madoituongno, loaidoituongco, madoiduongco, "0", "", "", tiente_fk, "", tigia, Double.toString(TIENCHENHLECH), Double.toString(TIENCHENHLECH), "", "0" , diengiai , machungtu, isNPP);
						
												
											if (this.msg.trim().length() > 0) {
												psktRs.close();
												db.getConnection().rollback();
												return false;
											}
										}
										else // TIEN MAT
										{
											taikhoanNO = psktRs.getString("taikhoan_11110000") == null ? "": psktRs.getString("taikhoan_11110000");
											loaidoituongno = ""; 
											madoituongno = "";	
											
											taikhoanCO = psktRs.getString("TAIKHOAN_51580000") == null ? "": psktRs.getString("TAIKHOAN_51580000");
											loaidoituongco = ""; 
											madoiduongco = "";	
											
											tiente_fk = "100000";
											tigia = "1";
											
											if (taikhoanCO.trim().length() <= 0 || taikhoanNO.trim().length() <= 0) {
												this.msg = "Lỗi xác định tài khoản kế toán. Vui lòng kiểm tra lại thông tin dữ liệu nền trước khi chốt.";
												db.getConnection().rollback();
												return false;
											}
	
											
											this.msg = util.Update_TaiKhoan_Vat_DienGiai( db, thang, nam, psktRs.getString("NGAYGHINHAN"), psktRs.getString("NGAYGHINHAN"), "Thanh toán hóa đơn", this.id, taikhoanNO, taikhoanCO, "", 
													Double.toString(TIENCHENHLECH), Double.toString(TIENCHENHLECH), loaidoituongno, madoituongno, loaidoituongco, madoiduongco, "0", "", "", tiente_fk, "", tigia, Double.toString(TIENCHENHLECH), Double.toString(TIENCHENHLECH), "", "0" , diengiai , machungtu, isNPP);
						
												
											if (this.msg.trim().length() > 0) {
												psktRs.close();
												db.getConnection().rollback();
												return false;
											}
											
										}
										
									}
									
									if(TIENCHENHLECH < 0)
									{
										TIENCHENHLECH = Math.abs(TIENCHENHLECH);
										
										if(hinhthuctt.equals("100001")) // NGÂN HÀNG
										{
											taikhoanNO = psktRs.getString("TAIKHOAN_63580000") == null ? "": psktRs.getString("TAIKHOAN_63580000");
											loaidoituongno = ""; 
											madoituongno = "";	
											
											taikhoanCO = psktRs.getString("taikhoan_NH") == null ? "": psktRs.getString("taikhoan_NH");
											loaidoituongco = "Ngân hàng"; 
											madoiduongco = nganhang_fk;	
											
											tiente_fk = "100000";
											tigia = "1";
											
											if (taikhoanCO.trim().length() <= 0 || taikhoanNO.trim().length() <= 0) {
												this.msg = "Lỗi xác định tài khoản kế toán. Vui lòng kiểm tra lại thông tin dữ liệu nền trước khi chốt.";
												db.getConnection().rollback();
												return false;
											}
	
											
											this.msg = util.Update_TaiKhoan_Vat_DienGiai( db, thang, nam, psktRs.getString("NGAYGHINHAN"), psktRs.getString("NGAYGHINHAN"), "Thanh toán hóa đơn", this.id, taikhoanNO, taikhoanCO, "", 
													Double.toString(TIENCHENHLECH), Double.toString(TIENCHENHLECH), loaidoituongno, madoituongno, loaidoituongco, madoiduongco, "0", "", "", tiente_fk, "", tigia, Double.toString(TIENCHENHLECH), Double.toString(TIENCHENHLECH), "", "0" , diengiai , machungtu, isNPP);
						
												
											if (this.msg.trim().length() > 0) {
												psktRs.close();
												db.getConnection().rollback();
												return false;
											}
										}
										else // TIEN MAT
										{
											taikhoanNO = psktRs.getString("TAIKHOAN_63580000") == null ? "": psktRs.getString("TAIKHOAN_63580000");
											loaidoituongno = ""; 
											madoituongno = "";	
											
											taikhoanCO = psktRs.getString("taikhoan_11110000") == null ? "": psktRs.getString("taikhoan_11110000");
											loaidoituongco = ""; 
											madoiduongco = "";	
											
											tiente_fk = "100000";
											tigia = "1";
											
											if (taikhoanCO.trim().length() <= 0 || taikhoanNO.trim().length() <= 0) {
												this.msg = "Lỗi xác định tài khoản kế toán. Vui lòng kiểm tra lại thông tin dữ liệu nền trước khi chốt.";
												db.getConnection().rollback();
												return false;
											}			
											
											this.msg = util.Update_TaiKhoan_Vat_DienGiai( db, thang, nam, psktRs.getString("NGAYGHINHAN"), psktRs.getString("NGAYGHINHAN"), "Thanh toán hóa đơn", this.id, taikhoanNO, taikhoanCO, "", 
													Double.toString(TIENCHENHLECH), Double.toString(TIENCHENHLECH), loaidoituongno, madoituongno, loaidoituongco, madoiduongco, "0", "", "", tiente_fk, "", tigia, Double.toString(TIENCHENHLECH), Double.toString(TIENCHENHLECH), "", "0" , diengiai , machungtu, isNPP);
						
												
											if (this.msg.trim().length() > 0) {
												psktRs.close();
												db.getConnection().rollback();
												return false;
											}
											
										}
										
									}

								}

							}
							psktRs.close();
						}

						// THUẾ NHẬP KHẨU
						query = "select (select SOCHUNGTU from ERP_THUENHAPKHAU where PK_SEQ = (select top 1 THUENHAPKHAU_FK from ERP_THANHTOANHOADON_CHIPHIKHAC  where THANHTOANHD_FK= "+ this.id + " )) as sotokhai, \n"+
								" 	    (select sum(SOTIENTT) from ERP_THANHTOANHOADON_HOADON where THANHTOANHD_FK= "+ this.id + " and LOAIHD = 4 and LOAITHUE= N'Thuế nhập khẩu') as sotienThueNK, \n"+
								" 	    (select sum(SOTIENTT) from ERP_THANHTOANHOADON_HOADON where THANHTOANHD_FK= "+ this.id + " and LOAIHD = 4 and LOAITHUE= N'VAT nhập khẩu') as sotienVATNK, \n"+
								"	    (select TaiKhoan_fk from ERP_NGANHANG_CONGTY  where  SoTaiKhoan = a.SOTAIKHOAN  AND NPP_FK = "+ this.nppdangnhap + ")  as taikhoanCO_NH, \n"+
								"       (select pk_seq from erp_taikhoankt where sohieutaikhoan= '33330000' AND NPP_FK = "	+ this.nppdangnhap + ") as taikhoanNO_ThueNK, \n"+
								"       (select pk_seq from erp_taikhoankt where sohieutaikhoan= '33312000' AND NPP_FK = "	+ this.nppdangnhap + ") as taikhoanNO_VATNK, \n"+
								"       (select pk_seq from erp_taikhoankt where sohieutaikhoan= '11110000' AND NPP_FK = "	+ this.nppdangnhap + ") as taikhoanCO_Tienmat, \n"+ 
								"       a.ngayghinhan, a.noidungtt, a.sochungtu machungtu, isnull(a.isnpp, '') ISNPP \n"+
								"from ERP_THANHTOANHOADON a  left join ERP_NHACUNGCAP d on a.ncc_fk = d.pk_seq \n"+
								"where a.PK_SEQ = '" + this.id + "'";

						System.out.println("KE TOAN_ THANH TOAN THUE NHAP KHAU: " + query);
						ResultSet ktRs = db.get(query);
						if (ktRs != null) {
							while (ktRs.next()) {
								String loaidoituongco = "";
								String madoiduongco = "";

								String taikhoanCO = "";
								String taikhoanNO = "";
								
								String diengiai = ktRs.getString("noidungtt");
								String machungtu = ktRs.getString("machungtu");
								String isNPP = ktRs.getString("ISNPP");

								if (Double.parseDouble(this.checkThanhtoantuTV) <= 0) {
									// GHI NHAN : THUE NHAP KHAU
									double stThueNK = ktRs.getDouble("sotienThueNK");
									if (stThueNK > 0) {
										taikhoanNO = ktRs.getString("taikhoanNO_ThueNK") == null ? "": ktRs.getString("taikhoanNO_ThueNK");
										if (hinhthuctt.equals("100000")) {
											taikhoanCO = ktRs.getString("taikhoanCO_Tienmat");
										} else {
											taikhoanCO = ktRs.getString("taikhoanCO_NH") == null ? "": ktRs.getString("taikhoanCO_NH");
											loaidoituongco = "Ngân hàng";
											madoiduongco = nganhang_fk;
										}

										if (taikhoanCO.trim().length() <= 0 || taikhoanNO.trim().length() <= 0) {
											this.msg = "Lỗi xác định tài khoản kế toán (Thuế nhập khẩu). Vui lòng kiểm tra lại thông tin dữ liệu nền trước khi chốt.";
											db.getConnection().rollback();
											return false;
										}

										this.msg = util.Update_TaiKhoan_Vat_DienGiai( db, thang, nam, ktRs.getString("ngayghinhan"), ktRs.getString("ngayghinhan"), "Thanh toán Thuế nhập khẩu", this.id, taikhoanNO, taikhoanCO, "", 
										Double.toString(stThueNK), Double.toString(stThueNK), "", ktRs.getString("sotokhai"), loaidoituongco, madoiduongco, "0", "", "", tiente_fk, "", tigia, Double.toString(stThueNK), Double.toString(stThueNK), ktRs.getString("sotokhai"), "0" , diengiai , machungtu, isNPP);
					
										
										if (this.msg.trim().length() > 0) {
											ktRs.close();
											db.getConnection().rollback();
											return false;
										}
									}

									// GHI NHAN : VAT NHAP KHAU
									double stVATNK = ktRs.getDouble("sotienVATNK");
									if (stVATNK > 0) {
										taikhoanNO = ktRs.getString("taikhoanNO_VATNK") == null ? "": ktRs.getString("taikhoanNO_VATNK");
										if (hinhthuctt.equals("100000")) {
											taikhoanCO = ktRs.getString("taikhoanCO_Tienmat");
										} else {
											taikhoanCO = ktRs.getString("taikhoanCO_NH") == null ? "": ktRs.getString("taikhoanCO_NH");
											loaidoituongco = "Ngân hàng";
											madoiduongco = nganhang_fk;
										}

										if (taikhoanCO.trim().length() <= 0 || taikhoanNO.trim().length() <= 0) {
											this.msg = "Lỗi xác định tài khoản kế toán (VAT nhập khẩu). Vui lòng kiểm tra lại thông tin dữ liệu nền trước khi chốt.";
											db.getConnection().rollback();
											return false;
										}

										this.msg = util.Update_TaiKhoan_Vat_DienGiai( db, thang, nam, ktRs.getString("ngayghinhan"), ktRs.getString("ngayghinhan"), "Thanh toán Thuế nhập khẩu", this.id, taikhoanNO, taikhoanCO, "", 
												Double.toString(stVATNK), Double.toString(stVATNK), "", ktRs.getString("sotokhai"), loaidoituongco, madoiduongco, "0", "", "", tiente_fk, "", tigia, Double.toString(stVATNK), Double.toString(stVATNK), ktRs.getString("sotokhai"), "0" , diengiai , machungtu, isNPP);
							
										if (this.msg.trim().length() > 0) {
											ktRs.close();
											db.getConnection().rollback();
											return false;
										}
									}

								}

							}
							ktRs.close();
						}
					}
				
				}
				else if(this.DoiTuongChiPhiKhac.equals("0")) // NHÂN VIÊN  (TẠM ỨNG/ĐỀ NGHỊ THANH TOÁN/CHI PHÍ KHÁC)
				{
					query = " SELECT TTHD.NGANHANG_FK , TTHD_HD.SOTIENTT, TTHD_HD.LOAIHD, TTHD_HD.HOADON_FK, TTHD.NGAYGHINHAN, TTHD.TIENTE_FK,   \n" +
							" ( select TaiKhoan_fk from ERP_NGANHANG_CONGTY where  SoTaiKhoan = TTHD.SOTAIKHOAN AND NPP_FK = "+ this.nppdangnhap + " ) as taikhoan_NH, "+
					        " (select pk_seq from ERP_TAIKHOANKT where SOHIEUTAIKHOAN = '11110000' AND NPP_FK = "+ this.nppdangnhap + ") as taikhoan_TIENVND, ISNULL(TTHD.NOIDUNGTT, '' ) NOIDUNGTT, ISNULL(TTHD.ISNPP, '') ISNPP , ISNULL(TTHD.SOCHUNGTU, '') MACHUNGTU "+
							" FROM ERP_THANHTOANHOADON TTHD  \n" +
							" INNER JOIN ERP_THANHTOANHOADON_HOADON TTHD_HD ON TTHD.PK_SEQ = TTHD_HD.THANHTOANHD_FK \n"+
							" WHERE TTHD.PK_SEQ = "+this.id;
					
					System.out.println(query);
					rs = db.get(query);
					
					if(rs!=null)
					{
						while(rs.next())
						{
							String loaihd = rs.getString("LOAIHD");
							String hoadonId = rs.getString("HOADON_FK");
							String taikhoanCO_NH = rs.getString("taikhoan_NH");
							String taikhoanCO_TM = rs.getString("taikhoan_TIENVND")== null ? "": rs.getString("taikhoan_TIENVND") ;
							
							nam = rs.getString("ngayghinhan").substring(0, 4);
							thang = rs.getString("ngayghinhan").substring(5, 7);
							tiente_fk = rs.getString("tiente_fk");
							
							String taikhoanCO = rs.getString("taikhoan_TIENVND")== null ? "": rs.getString("taikhoan_TIENVND") ;
							
							String diengiai = rs.getString("NOIDUNGTT");
							String machungtu = rs.getString("MACHUNGTU");
							String isNPP = rs.getString("ISNPP");
							
							if(loaihd.equals("6"))// ĐỀ NGHỊ THANH TOÁN
							{
								// chạy định khoản
								
								String taikhoanCO_DS = "";
								String taikhoanNO_DS = "";
								
								String taikhoanCO_VAT = "";
								String taikhoanNO_VAT = "";
								
								String loaidoituongCO = "";
								String madoituongCO = "";
								
								String loaidoituongNO = "";
								String madoituongNO = "";
								
								
								query= "  SELECT	N'CHI PHÍ' AS LOAIDOITUONGNO, E.PK_SEQ AS MADOITUONGNO,    \n" +  
									   "  CASE  WHEN A.NHACUNGCAP_FK  IS NOT NULL THEN N'NHÀ CUNG CẤP'  WHEN A.NHANVIEN_FK  IS NOT NULL THEN N'NHÂN VIÊN' ELSE N'KHÁCH HÀNG' END AS LOAIDOITUONGCO,   \n" +  
									   "  CASE  WHEN  A.NHACUNGCAP_FK  IS NOT NULL THEN NCC.PK_SEQ WHEN A.NHANVIEN_FK  IS NOT NULL THEN NV.PK_SEQ ELSE KH.PK_SEQ END AS MADOITUONGCO, \n" +  
									   "  A.NGAYMUA AS NGAYHOADON, (D.SOLUONG* D.DONGIA)  AS DOANHSO,(D.SOLUONG* D.DONGIA)*  D.PHANTRAMTHUE/100 AS THUE   ,    \n" +  
									   "  CASE  WHEN A.NHACUNGCAP_FK  IS NOT NULL THEN NCC.TAIKHOAN_FK WHEN A.NHANVIEN_FK  IS NOT NULL THEN NV.TAIKHOAN_FK ELSE KH.TAIKHOAN_FK END AS TAIKHOANCO_DS,   \n" +  
									   "  CASE  WHEN A.NHACUNGCAP_FK  IS NOT NULL THEN NCC.TAIKHOAN_FK WHEN A.NHANVIEN_FK  IS NOT NULL THEN NV.TAIKHOAN_FK ELSE KH.TAIKHOAN_FK  END AS TAIKHOANCO_VAT,   \n" +  
									   "  (SELECT PK_SEQ FROM ERP_TAIKHOANKT WHERE SOHIEUTAIKHOAN = E.TAIKHOAN_FK AND NPP_FK = A.NPP_FK) AS TAIKHOANNO_DS,   \n" +  
									   "  ( SELECT PK_SEQ FROM ERP_TAIKHOANKT WHERE SOHIEUTAIKHOAN = '13311000' AND NPP_FK = A.NPP_FK  ) AS TAIKHOANNO_VAT   \n" +  
									   "  FROM ERP_MUAHANG A  \n" +  
									   "  LEFT JOIN ERP_NHANVIEN NV ON NV.PK_SEQ = A.NHANVIEN_FK    \n" +  
									   "  LEFT JOIN TraphacoERP.dbo.ERP_NHACUNGCAP NCC ON A.NHACUNGCAP_FK = NCC.PK_SEQ   \n" + 
									   "  LEFT JOIN KHACHHANG KH ON A.KHACHHANG_FK = KH.PK_SEQ   \n" +  
									   "  LEFT JOIN ERP_MUAHANG_SP D ON A.PK_SEQ = D.MUAHANG_FK   \n" +  
									   "  LEFT JOIN TraphacoERP.dbo.ERP_NHOMCHIPHI E ON D.CHIPHI_FK = E.PK_SEQ   \n" +  
									   "  WHERE A.PK_SEQ ="+hoadonId;
								
								System.out.println("Câu tk12"+ query);
								ResultSet rsTk = db.get(query);
								if(rsTk!= null)
								{
									while(rsTk.next())
									{
										
										double totalDS = Math.round(rsTk.getDouble("DOANHSO"));
										double totalVAT = Math.round(rsTk.getDouble("THUE"));
										
										taikhoanCO_DS = rsTk.getString("taikhoanCO_DS") == null ? "": rsTk.getString("taikhoanCO_DS") ;
										taikhoanNO_DS = rsTk.getString("taikhoanNO_DS") == null ? "": rsTk.getString("taikhoanNO_DS")  ;
										
										taikhoanCO_VAT = rsTk.getString("taikhoanCO_VAT") == null ? "": rsTk.getString("taikhoanCO_VAT")  ;
										taikhoanNO_VAT = rsTk.getString("taikhoanNO_VAT") == null ? "": rsTk.getString("taikhoanNO_VAT") ;
																																									
										String ngayghinhan = rsTk.getString("ngayhoadon");			
									
										if(totalDS > 0)
										{
											loaidoituongNO = rsTk.getString("loaidoituongNO"); // CHI PHÍ
											madoituongNO = rsTk.getString("madoituongNO"); // MÃ CHI PHÍ
											
											loaidoituongCO = rsTk.getString("LOAIDOITUONGCO"); 
											madoituongCO = rsTk.getString("MADOITUONGCO");
											
											if(taikhoanCO_DS.trim().length() <= 0 || taikhoanNO_DS.trim().length() <= 0 )
											{
												msg = "Lỗi xác định tài khoản kế toán. Vui lòng kiểm tra lại thông tin mã chi phí trước khi chốt.";
												db.getConnection().rollback();
											 
												return false;
											}
											
											this.msg = util.Update_TaiKhoan_Vat_DienGiai( db, thang, nam, ngayghinhan, ngayghinhan, "Chi phí khác", this.id, taikhoanNO_DS, taikhoanCO_TM, "", 
											Double.toString(totalDS), Double.toString(totalDS), loaidoituongNO, madoituongNO, loaidoituongCO, madoituongCO, "0", "", "", tiente_fk, "", "1", Double.toString(totalDS), Double.toString(totalDS), "Doanh số", Double.toString(totalVAT) , diengiai , machungtu, isNPP);
														
											
											if(msg.trim().length() > 0)
											{
												db.getConnection().rollback();
												return false;
											}
											
										}
										
										if(totalVAT > 0)
										{
											loaidoituongNO = ""; 
											madoituongNO = ""; 
											
											loaidoituongCO = rsTk.getString("LOAIDOITUONGCO"); 
											madoituongCO = rsTk.getString("MADOITUONGCO");
											
											if(taikhoanCO_VAT.trim().length() <= 0 || taikhoanNO_VAT.trim().length() <= 0 )
											{
												msg = "Lỗi xác định tài khoản kế toán. Vui lòng kiểm tra lại thông tin mã chi phí trước khi chốt.";
												db.getConnection().rollback();
												return false;
											}	
											
											this.msg = util.Update_TaiKhoan_Vat_DienGiai( db, thang, nam, ngayghinhan, ngayghinhan, "Chi phí khác", this.id, taikhoanNO_VAT, taikhoanCO_TM, "", 
											Double.toString(totalVAT), Double.toString(totalVAT), loaidoituongNO, madoituongNO, loaidoituongCO, madoituongCO, "0", "", "", tiente_fk, "", "1", Double.toString(totalVAT), Double.toString(totalVAT), "VAT", "0" , diengiai , machungtu, isNPP);
											
											if(msg.trim().length() > 0)
											{
												db.getConnection().rollback();
												return false;
											}
										}
										
									}
									rsTk.close();
								}
							
							}
							else
							{
								// NHỮNG LOẠI HĐ CÓ NCC > 0
								query =" select  TTHD.NGANHANG_FK , NV.TAIKHOAN_FK as taikhoanNO_NV,  TTHD.NGAYGHINHAN , \n"+
							       "        ( select TaiKhoan_fk from ERP_NGANHANG_CONGTY where  SoTaiKhoan = TTHD.SOTAIKHOAN ) as taikhoanCO_NH , \n"+
							       "        (select pk_seq from ERP_TAIKHOANKT where SOHIEUTAIKHOAN = '11110000' AND NPP_FK = "+this.nppdangnhap+") as taikhoan_TIENVND, " +
							       "         case when TTHD.TIENTE_FK = 100000 then (select SUM(SOTIENTT) from ERP_THANHTOANHOADON_HOADON where THANHTOANHD_FK = "+ this.id +" and LOAIHD in (1,5,6) ) " +
							       "              else (select SUM(SOTIENTT) from ERP_THANHTOANHOADON_HOADON where THANHTOANHD_FK = "+ this.id +" and LOAIHD in (1,5,6,8) ) * TTHD.TIGIA " +
							       "              end as tonggiatri, TTHD.NOIDUNGTT, TTHD.sochungtu MACHUNGTU, isnull(TTHD.ISNPP,'') ISNPP \n"+
							       " from   ERP_THANHTOANHOADON TTHD  inner join ERP_NHANVIEN NV on TTHD.NHANVIEN_FK = NV.PK_SEQ \n"+
							       " where TTHD.PK_SEQ = "+ this.id +" ";
									System.out.println("Câu query NV "+query);
									ResultSet psktRs = db.get(query);
									
									if(psktRs != null)
									{							
										while(psktRs.next())
										{								 	
											
											taikhoanCO = "";
											String taikhoanNO = "";
											
											String loaidoituongco = "";
											String madoiduongco = "";
											
											diengiai =  psktRs.getString("NOIDUNGTT");
											machungtu = psktRs.getString("MACHUNGTU");
											isNPP =  psktRs.getString("ISNPP");
											
											double tonggiatri = Math.round(psktRs.getDouble("tonggiatri")) ;
											
											if(tonggiatri > 0)
											{
												taikhoanNO = psktRs.getString("taikhoanNO_NV") == null ? "": psktRs.getString("taikhoanNO_NV");
												
												if(hinhthuctt.equals("100001")) //THANH TOAN: NGANHANG
												{
													taikhoanCO = psktRs.getString("taikhoanCO_NH")== null?"": psktRs.getString("taikhoanCO_NH") ;
													loaidoituongco = "Ngân hàng";
													madoiduongco = nganhang_fk;
												}
												else //TIEN MAT
												{
													taikhoanCO = psktRs.getString("taikhoan_TIENVND");
												}																																
											
												if( taikhoanCO.trim().length() <= 0 || taikhoanNO.trim().length() <= 0 )
												{
													this.msg = "Lỗi xác định tài khoản kế toán. Vui lòng kiểm tra lại thông tin dữ liệu nền trước khi chốt.";
													db.getConnection().rollback();
													return false;
												}
												
												this.msg = util.Update_TaiKhoan_Vat_DienGiai( db, thang, nam, psktRs.getString("NGAYGHINHAN"), psktRs.getString("NGAYGHINHAN"), "Thanh toán hóa đơn", this.id, taikhoanNO, taikhoanCO, "", 
												Double.toString(tonggiatri), Double.toString(tonggiatri), "Nhân viên", nhanvien_fk, loaidoituongco, madoiduongco, "0", "", "", tiente_fk, "", tigia, Double.toString(tonggiatri), Double.toString(tonggiatri), "", "0" , diengiai , machungtu, isNPP);
																										
												if(this.msg.trim().length() > 0)
												{
													psktRs.close();
													db.getConnection().rollback();
													return false;
												}
											}		
				
										}
										psktRs.close();
									}									
							}
						}
					}						
				
				}
				else if(this.khachhangId.trim().length() > 0) // Khách hàng
				{
					query = " SELECT TTHD.NGANHANG_FK , TTHD_HD.SOTIENTT, TTHD_HD.LOAIHD, TTHD_HD.HOADON_FK, TTHD.NGAYGHINHAN, TTHD.TIENTE_FK, KH.TAIKHOAN_FK as taikhoanNO_KH,  \n" +
							" ( SELECT TaiKhoan_fk from ERP_NGANHANG_CONGTY where  SoTaiKhoan = TTHD.SOTAIKHOAN AND NPP_FK = "+ this.nppdangnhap + " ) as taikhoan_NH, "+
					        " ( SELECT pk_seq from ERP_TAIKHOANKT where SOHIEUTAIKHOAN = '11110000' AND NPP_FK = "+ this.nppdangnhap + ") as taikhoan_TIENVND, "+
					        "  TTHD.NOIDUNGTT, TTHD.SOCHUNGTU MACHUNGTU, isnull(TTHD.ISNPP,'') ISNPP \n"+
							" FROM ERP_THANHTOANHOADON TTHD INNER JOIN ERP_KHACHHANG KH on TTHD.KHACHHANG_FK = KH.PK_SEQ  \n" +
							" INNER JOIN ERP_THANHTOANHOADON_HOADON TTHD_HD ON TTHD.PK_SEQ = TTHD_HD.THANHTOANHD_FK \n"+
							" WHERE TTHD.PK_SEQ = "+this.id;					
					
					System.out.println(query);
					rs = db.get(query);
					
					if(rs!=null)
					{
						while(rs.next())
						{
							String loaihd = rs.getString("LOAIHD");
							String hoadonId = rs.getString("HOADON_FK");
							String taikhoanCO_NH = rs.getString("taikhoan_NH");
							String taikhoanCO_TM = rs.getString("taikhoan_TIENVND")== null ? "": rs.getString("taikhoan_TIENVND") ;
							
							String diengiai = rs.getString("NOIDUNGTT");
							String machungtu = rs.getString("MACHUNGTU");
							String isNPP = rs.getString("ISNPP");
							
							nam = rs.getString("ngayghinhan").substring(0, 4);
							thang = rs.getString("ngayghinhan").substring(5, 7);
							tiente_fk = rs.getString("tiente_fk");
							
							String taikhoanCO = rs.getString("taikhoan_TIENVND")== null ? "": rs.getString("taikhoan_TIENVND") ;
														
							if(loaihd.equals("6"))// ĐỀ NGHỊ THANH TOÁN
							{
								// chạy định khoản
								
								String taikhoanCO_DS = "";
								String taikhoanNO_DS = "";
								
								String taikhoanCO_VAT = "";
								String taikhoanNO_VAT = "";
								
								String loaidoituongCO = "";
								String madoituongCO = "";
								
								String loaidoituongNO = "";
								String madoituongNO = "";
								
								
								query= "  SELECT	N'CHI PHÍ' AS LOAIDOITUONGNO, E.PK_SEQ AS MADOITUONGNO,    " +  
									   "  CASE  WHEN A.NHACUNGCAP_FK  IS NOT NULL THEN N'NHÀ CUNG CẤP'  WHEN A.NHANVIEN_FK  IS NOT NULL THEN N'NHÂN VIÊN' ELSE N'KHÁCH HÀNG' END AS LOAIDOITUONGCO,   " +  
									   "  CASE  WHEN  A.NHACUNGCAP_FK  IS NOT NULL THEN NCC.PK_SEQ WHEN A.NHANVIEN_FK  IS NOT NULL THEN NV.PK_SEQ ELSE KH.PK_SEQ END AS MADOITUONGCO, " +  
									   "  A.NGAYMUA AS NGAYHOADON, (D.SOLUONG* D.DONGIA)  AS DOANHSO,(D.SOLUONG* D.DONGIA)*  D.PHANTRAMTHUE/100 AS THUE   ,    " +  
									   "  CASE  WHEN A.NHACUNGCAP_FK  IS NOT NULL THEN NCC.TAIKHOAN_FK WHEN A.NHANVIEN_FK  IS NOT NULL THEN NV.TAIKHOAN_FK ELSE KH.TAIKHOAN_FK END AS TAIKHOANCO_DS,   " +  
									   "  CASE  WHEN A.NHACUNGCAP_FK  IS NOT NULL THEN NCC.TAIKHOAN_FK WHEN A.NHANVIEN_FK  IS NOT NULL THEN NV.TAIKHOAN_FK ELSE KH.TAIKHOAN_FK  END AS TAIKHOANCO_VAT,   " +  
									   "  (SELECT PK_SEQ FROM ERP_TAIKHOANKT WHERE SOHIEUTAIKHOAN = E.TAIKHOAN_FK AND NPP_FK = A.NPP_FK) AS TAIKHOANNO_DS,   " +  
									   "  ( SELECT PK_SEQ FROM ERP_TAIKHOANKT WHERE SOHIEUTAIKHOAN = '13311000' AND NPP_FK = A.NPP_FK  ) AS TAIKHOANNO_VAT   " +  
									   "  FROM ERP_MUAHANG A  " +  
									   "  LEFT JOIN ERP_NHANVIEN NV ON NV.PK_SEQ = A.NHANVIEN_FK    " +  
									   "  LEFT JOIN TraphacoERP.dbo.ERP_NHACUNGCAP NCC ON A.NHACUNGCAP_FK = NCC.PK_SEQ   " + 
									   "  LEFT JOIN KHACHHANG KH ON A.KHACHHANG_FK = KH.PK_SEQ   " +  
									   "  LEFT JOIN ERP_MUAHANG_SP D ON A.PK_SEQ = D.MUAHANG_FK   " +  
									   "  LEFT JOIN TraphacoERP.dbo.ERP_NHOMCHIPHI E ON D.CHIPHI_FK = E.PK_SEQ   " +  
									   "  WHERE A.PK_SEQ ="+hoadonId;
								
								System.out.println("Câu tk14"+ query);
								ResultSet rsTk = db.get(query);
								if(rsTk!= null)
								{
									while(rsTk.next())
									{												
										double totalDS = Math.round(rsTk.getDouble("DOANHSO"));
										double totalVAT = Math.round(rsTk.getDouble("THUE"));
										
										taikhoanCO_DS = rsTk.getString("taikhoanCO_DS") == null ? "": rsTk.getString("taikhoanCO_DS") ;
										taikhoanNO_DS = rsTk.getString("taikhoanNO_DS") == null ? "": rsTk.getString("taikhoanNO_DS")  ;
										
										taikhoanCO_VAT = rsTk.getString("taikhoanCO_VAT") == null ? "": rsTk.getString("taikhoanCO_VAT")  ;
										taikhoanNO_VAT = rsTk.getString("taikhoanNO_VAT") == null ? "": rsTk.getString("taikhoanNO_VAT") ;
																																									
										String ngayghinhan = rsTk.getString("ngayhoadon");			
									
										if(totalDS > 0)
										{
											loaidoituongNO = rsTk.getString("loaidoituongNO"); // CHI PHÍ
											madoituongNO = rsTk.getString("madoituongNO"); // MÃ CHI PHÍ
											
											loaidoituongCO = rsTk.getString("LOAIDOITUONGCO"); 
											madoituongCO = rsTk.getString("MADOITUONGCO");
											
											if(taikhoanCO_DS.trim().length() <= 0 || taikhoanCO.trim().length() <= 0 )
											{
												msg = "Lỗi xác định tài khoản kế toán. Vui lòng kiểm tra lại thông tin mã chi phí trước khi chốt.";
												db.getConnection().rollback();
											 
												return false;
											}
											
											this.msg = util.Update_TaiKhoan_Vat_DienGiai( db, thang, nam, ngayghinhan, ngayghinhan, "Chi phí khác", this.id, taikhoanNO_DS, taikhoanCO_TM, "", 
											Double.toString(totalDS), Double.toString(totalDS), loaidoituongNO, madoituongNO, loaidoituongCO, madoituongCO, "0", "", "", tiente_fk, "", "1", Double.toString(totalDS), Double.toString(totalDS),"Doanh số", Double.toString(totalVAT) , diengiai , machungtu, isNPP);
														
											
											if(msg.trim().length() > 0)
											{
												db.getConnection().rollback();
												return false;
											}
											
										}
										
										if(totalVAT > 0)
										{
											loaidoituongNO = ""; 
											madoituongNO = ""; 
											
											loaidoituongCO = rsTk.getString("LOAIDOITUONGCO"); 
											madoituongCO = rsTk.getString("MADOITUONGCO");
											
											if(taikhoanCO_VAT.trim().length() <= 0 || taikhoanNO_VAT.trim().length() <= 0 )
											{
												msg = "Lỗi xác định tài khoản kế toán. Vui lòng kiểm tra lại thông tin mã chi phí trước khi chốt.";
												db.getConnection().rollback();
												return false;
											}	
											
											this.msg = util.Update_TaiKhoan_Vat_DienGiai( db, thang, nam, ngayghinhan, ngayghinhan, "Chi phí khác", this.id, taikhoanNO_VAT, taikhoanCO_TM, "", 
											Double.toString(totalVAT), Double.toString(totalVAT), loaidoituongNO, madoituongNO, loaidoituongCO, madoituongCO, "0", "", "", tiente_fk, "", "1", Double.toString(totalVAT), Double.toString(totalVAT),"VAT", "0" , diengiai , machungtu, isNPP);
																									
											if(msg.trim().length() > 0)
											{
												db.getConnection().rollback();
												return false;
											}
										}
										
									}
									rsTk.close();
								}									
							}
							
						}
					}
					
					query =	   " select isnull(TTHD.NOIDUNGTT,'') as diengiai,  TTHD.NGANHANG_FK , KH.TAIKHOAN_FK as taikhoanNO_KH,  TTHD.NGAYGHINHAN , \n"+
						       "        ( SELECT TaiKhoan_fk from ERP_NGANHANG_CONGTY where  SoTaiKhoan = TTHD.SOTAIKHOAN AND NPP_FK = "+ this.nppdangnhap + " ) as taikhoanCO_NH , \n"+
						       "        ( SELECT pk_seq from ERP_TAIKHOANKT where SOHIEUTAIKHOAN = '11110000' AND NPP_FK = "+ this.nppdangnhap + ")  as taikhoan_TIENVND, " +
						       "        isnull( case when TTHD.TIENTE_FK = 100000 then (select SUM(SOTIENTT) from ERP_THANHTOANHOADON_HOADON where THANHTOANHD_FK = "+ this.id +" and LOAIHD in (7,8)  ) " +
						       "              else (select SUM(SOTIENTT) from ERP_THANHTOANHOADON_HOADON where THANHTOANHD_FK = "+ this.id +"  and LOAIHD in (7,8) ) * TTHD.TIGIA " +
						       "              end , 0 ) as tonggiatri, TTHD.NOIDUNGTT, TTHD.SOCHUNGTU MACHUNGTU, isnull(TTHD.ISNPP,'') ISNPP \n"+
						       " from   ERP_THANHTOANHOADON TTHD  inner join KHACHHANG KH on TTHD.KHACHHANG_FK = KH.PK_SEQ \n"+
						       " where TTHD.PK_SEQ = "+ this.id +" ";
					
					System.out.println("Câu query NV "+query);
					ResultSet psktRs = db.get(query);
					if(psktRs != null)
					{							
						while(psktRs.next())
						{								 	
							String diengiai = psktRs.getString("NOIDUNGTT");
							String machungtu = psktRs.getString("MACHUNGTU");
							String isNPP = psktRs.getString("ISNPP");
							String taikhoanCO = "";
							String taikhoanNO = "";
							
							String loaidoituongco = "";
							String madoiduongco = "";
							
							double tonggiatri = Math.round(psktRs.getDouble("tonggiatri")) ;
							
							if(tonggiatri > 0)
							{
								taikhoanNO = psktRs.getString("taikhoanNO_KH") == null ? "": psktRs.getString("taikhoanNO_KH");
								
								if(hinhthuctt.equals("100001")) //THANH TOAN: NGANHANG
								{
									taikhoanCO = psktRs.getString("taikhoanCO_NH")== null?"": psktRs.getString("taikhoanCO_NH") ;
									loaidoituongco = "Ngân hàng";
									madoiduongco = nganhang_fk;
								}
								else //TIEN MAT
								{
									taikhoanCO = psktRs.getString("taikhoan_TIENVND");
								}																																
							
								if( taikhoanCO.trim().length() <= 0 || taikhoanNO.trim().length() <= 0 )
								{
									this.msg = "Lỗi xác định tài khoản kế toán. Vui lòng kiểm tra lại thông tin dữ liệu nền trước khi chốt.";
									db.getConnection().rollback();
									return false;
								}
								
								this.msg = util.Update_TaiKhoan_Vat_DienGiai( db, thang, nam, psktRs.getString("NGAYGHINHAN"), psktRs.getString("NGAYGHINHAN"), "Thanh toán hóa đơn", this.id, taikhoanNO, taikhoanCO, "", 
										Double.toString(tonggiatri), Double.toString(tonggiatri), "Khách hàng", khachhang_fk, loaidoituongco, madoiduongco, "0", "", "", tiente_fk, "", "1", Double.toString(tonggiatri), Double.toString(tonggiatri),"Trả tạm ứng khách hàng", "0" , diengiai , machungtu, isNPP);
																		
								
								if(this.msg.trim().length() > 0)
								{
									psktRs.close();
									db.getConnection().rollback();
									return false;
								}
							}		

						}
						psktRs.close();
					}	
					
				
				}
				else if(this.bophanId.trim().length() > 0) // Bộ phận 
				{					
					query =	   " select distinct TTHD.NGANHANG_FK , NV.PK_SEQ as NVID, NV.TAIKHOAN_FK as taikhoanNO_NV,  TTHD.NGAYGHINHAN , \n"+
						       "        ( select TAIKHOAN_FK from ERP_NGANHANG_CONGTY where  SoTaiKhoan = TTHD.SOTAIKHOAN AND NPP_FK = "+this.nppdangnhap+" ) as taikhoanCO_NH , \n"+
						       "        ( select PK_SEQ from ERP_TAIKHOANKT where SOHIEUTAIKHOAN = '11110000' AND NPP_FK = "+this.nppdangnhap+") as taikhoan_TIENVND, " +
						       "         case when TTHD.TIENTE_FK = 100000 then (select SUM(SOTIENTT) from ERP_THANHTOANHOADON_HOADONBOPHAN where THANHTOANHD_FK = "+ this.id +" and NHANVIEN_FK = NV.PK_SEQ and LOAIHD in (6)  ) " +
						       "              else (select SUM(SOTIENTT) from ERP_THANHTOANHOADON_HOADONBOPHAN where THANHTOANHD_FK = "+ this.id +" and NHANVIEN_FK = NV.PK_SEQ  and LOAIHD in (6) ) * TTHD.TIGIA " +
						       "              end as tonggiatri, ISNULL( TTHD.NOIDUNGTT, '' ) NOIDUNGTT, ISNULL ( TTHD.sochungtu, '' ) MACHUNGTU, ISNULL ( TTHD.ISNPP, '') ISNPP  \n"+
						       " from   ERP_THANHTOANHOADON TTHD  inner join ERP_THANHTOANHOADON_HOADONBOPHAN TTHD_HD on TTHD.PK_SEQ = TTHD_HD.THANHTOANHD_FK \n"+
						       "        INNER JOIN ERP_NHANVIEN NV ON TTHD_HD.NHANVIEN_FK = NV.PK_SEQ \n"+
						       " where TTHD.PK_SEQ = "+ this.id +" ";
					System.out.println("Câu query BP "+query);
						ResultSet psktRs = db.get(query);
						if(psktRs != null)
						{							
							while(psktRs.next())
							{								 	
								
								String taikhoanNO = "";
								String taikhoanCO = "";
								
								String loaidoituongco = "";
								String madoiduongco = "";
								
								String nhanvienId = psktRs.getString("NVID");
								String diengiai = psktRs.getString("NOIDUNGTT");
								String machungtu = psktRs.getString("MACHUNGTU"); 
								String isNPP = psktRs.getString("ISNPP"); 
								
								double tonggiatri = Math.round(psktRs.getDouble("tonggiatri")) ;
								
								if(tonggiatri > 0)
								{
									taikhoanNO = psktRs.getString("taikhoanNO_NV") == null ? "": psktRs.getString("taikhoanNO_NV");
									
									if(hinhthuctt.equals("100001")) //THANH TOAN: NGANHANG
									{
										taikhoanCO = psktRs.getString("taikhoanCO_NH")== null?"": psktRs.getString("taikhoanCO_NH") ;
										loaidoituongco = "Ngân hàng";
										madoiduongco = nganhang_fk;
									}
									else //TIEN MAT
									{
										taikhoanCO = psktRs.getString("taikhoan_TIENVND");
									}																																
								
									if( taikhoanCO.trim().length() <= 0 || taikhoanNO.trim().length() <= 0 )
									{
										this.msg = "Lỗi xác định tài khoản kế toán. Vui lòng kiểm tra lại thông tin dữ liệu nền trước khi chốt.";
										db.getConnection().rollback();
										return false;
									}
									
									this.msg = util.Update_TaiKhoan_Vat_DienGiai( db, thang, nam, psktRs.getString("NGAYGHINHAN"), psktRs.getString("NGAYGHINHAN"), "Thanh toán hóa đơn", this.id, taikhoanNO, taikhoanCO, "", 
									Double.toString(tonggiatri), Double.toString(tonggiatri), "Nhân viên", nhanvienId, loaidoituongco, madoiduongco, "0", "", "", tiente_fk, "", tigia, Double.toString(tonggiatri), Double.toString(tonggiatri),"", "0" , diengiai , machungtu, isNPP);
																					
									if(this.msg.trim().length() > 0)
									{
										psktRs.close();
										db.getConnection().rollback();
										return false;
									}
								}		
	
							}
							psktRs.close();
						}										
				
					}
				}
			  }
		    }
		  

			db.getConnection().commit();
			db.getConnection().setAutoCommit(true);
			
		}
		catch (Exception e) 
		{
			
			try 
			{
				e.printStackTrace();
				db.getConnection().rollback();
				
			}
			catch (SQLException e1) {}
			
			this.msg = "Khong the cap nhat ERP_HOADONNCC ABC: " + e.getMessage();
			return false;
		}
		
		return true;
	}

	public void init()
	{
		getNppInfo();
		NumberFormat formatter = new DecimalFormat("#,###,###"); 
		String query = " select distinct hd.prefix,  isnull(hd.LoaiThanhToan,'1') as LoaiThanhToan,hd.nhanvien_fk,hd.pk_seq \n" +
						"		, hd.ngayghinhan, hd.trangthai, hd.ncc_fk , hd.httt_fk, hd.nganhang_fk, hd.chinhanh_fk, hd.sotaikhoan, \n" +
						" 		hd.noidungtt, hd.sotientt ,ncc.ma as mancc,ncc.ten as tenncc, nv.ma as manv,nv.ten as tennv, \n" +
						" 		hd.khachhang_fk, kh.maFAST as makh, kh.ten as tenkh, \n" +
						" 		isnull(hd.NHOMNCCCN,'0') nhomncccn , nc.DIENGIAI as tennhomncc , tk.SOHIEUTAIKHOAN +' -- '+ tk.TENTAIKHOAN as dinhkhoanno , \n" +
						" 		hd.doituongdinhkhoan , hd.MADOITUONGDINHKHOAN,isnull(hd.THANHTOANTUTIENVAY,0) as THANHTOANTUTIENVAY , \n" +
						"		hd.ghichu, hd.loaithanhtoan, hd.sotienHD, hd.sotienHDNT, hd.sotientt, hd.sotienttNT, hd.phinganhang, hd.phinganhangNT, \n" +
						"   	hd.vat, hd.vatNT, hd.sotienttNT, isnull(hd.chenhlechVND,0) as chenhlechVND , trichphi, sotaikhoan_tp, nganhang_tp_fk, chinhanh_tp_fk, \n" +
						"   	PNH.mahoadon , PNH.mauhoadon, PNH.KYHIEU, PNH.SOHOADON, PNH.NGAYHOADON,PNH.MST, isnull(PNH.TIENHANG,0) as TIENHANG, isnull(PNH.THUESUAT,0) as THUESUAT, \n" +
						"   	isnull(PNH.TIENTHUE,0) as TIENTHUE, PNH.NGANHANG_FK AS NGANHANG_PNH_FK, PNH.CHINHANH_FK AS CHINHANH_PNH_FK, hd.TIENTE_FK, hd.TIGIA, isnull(hd.ctkemtheo, '') chungtukemtheo,  \n" +
						"   	hd.dvth_fk, dvth.pk_seq as dvthId, dvth.ma as dvthMa, dvth.Ten as dvthTen, isnull(hd.iskttduyet,0) iskttduyet, PNH.TENNCC TENNCC1, isnull(hd.isTICHLUY,0) tratichluy \n"+
						
						" from ERP_THANHTOANHOADON hd  \n" +
						
						" left join ERP_THANHTOANHOADON_PHINGANHANG PNH on PNH.THANHTOANHD_FK = hd.PK_SEQ \n" +						
						" left join TraphacoERP.dbo.erp_nhacungcap ncc on ncc.pk_Seq=hd.ncc_fk \n" +						
						" left join erp_nhanvien nv on nv.pk_Seq=hd.nhanvien_fk  \n" +
						" left join khachhang kh on kh.pk_seq = hd.khachhang_fk \n" +
						" left join TraphacoERP.dbo.NHOMNHACUNGCAPCN nc on nc.PK_SEQ = hd.Nhomncccn \n" +
						" left join erp_donvithuchien dvth on dvth.pk_seq = hd.dvth_fk \n" +
						" left join ERP_TAIKHOANKT tk on tk.SOHIEUTAIKHOAN = hd.dinhkhoanno \n" +
						"where hd.pk_seq = '" + this.id + "'";
		System.out.println("GET SQL1 : "+query);
		String isTICHLUY = "";
		ResultSet rs = db.get(query);
		if(rs != null)
		{
			try 
			{
				while(rs.next())
				{
					this.ngayghinhan = rs.getString("ngayghinhan");
					this.nccId = rs.getString("ncc_fk")== null ? "": rs.getString("ncc_fk") ;
					this.htttId = rs.getString("httt_fk");
					this.nhomNCCCNId = rs.getString("NHOMNCCCN");
					this.prefix = rs.getString("prefix");
					this.checkThanhtoantuTV= rs.getString("THANHTOANTUTIENVAY");
					this.isktt = rs.getString("iskttduyet");
					
					isTICHLUY = rs.getString("tratichluy");
					
					if(this.htttId.equals("100001"))
					{
						this.nganhangId = rs.getString("nganhang_fk");
						this.chinhanhId = rs.getString("chinhanh_fk");
						this.sotaikhoan = rs.getString("sotaikhoan");
					}
					
					this.chungtukemtheo = rs.getString("chungtukemtheo");
					
					this.NhanvienId=rs.getString("nhanvien_fk") ;
					if(this.NhanvienId==null){
						this.NhanvienId="";
						if(this.nccId.length() > 1)
						{
							this.DoiTuongChiPhiKhac = "1";
							this.TenHienThi=rs.getString("ncc_fk")+" -- "+rs.getString("mancc")+" -- "+rs.getString("tenncc");
						}
						else
						{
							this.TenHienThi=rs.getString("NHOMNCCCN")+" -- "+rs.getString("tennhomncc");
						}
					}
					if(this.nccId.length() <=1 && this.NhanvienId.length() >1){
						this.nccId="";
						this.DoiTuongChiPhiKhac = "0";
						this.TenHienThi=rs.getString("nhanvien_fk")+" -- "+rs.getString("manv")+" -- "+rs.getString("tennv");
					}
					
					this.khachhangId = rs.getString("khachhang_fk")== null ? "": rs.getString("khachhang_fk") ;
					if(this.khachhangId.length() > 0){
						if(isTICHLUY.equals("1"))
							this.DoiTuongChiPhiKhac = "5";
						else
							this.DoiTuongChiPhiKhac = "2";
						
						this.TenHienThi=rs.getString("khachhang_fk")+" -- "+rs.getString("makh")+","+rs.getString("tenkh");
					}
					
					this.bophanId = rs.getString("dvth_fk")== null ? "": rs.getString("dvth_fk") ;
					if(this.bophanId.length() > 0){
						this.DoiTuongChiPhiKhac = "4";
						this.bophanTen =rs.getString("dvthId")+" - "+rs.getString("dvthMa")+","+rs.getString("dvthTen");
					}
					
					this.noidungtt = rs.getString("noidungtt");
					this.sotientt = formatter.format(rs.getDouble("sotientt"));
					
					if(this.bophanId.trim().length() <= 0 && this.nccId.trim().length() <= 0 && this.NhanvienId.trim().length() <= 0 && this.khachhangId.trim().length() <= 0 ) // tra Khac
					{
						this.DoiTuongChiPhiKhac = "3";
						this.dinhkhoanno = rs.getString("dinhkhoanno");
						this.DoiTuongDinhKhoan = rs.getString("doituongdinhkhoan")== null ? "": rs.getString("doituongdinhkhoan") ;
						this.doituongdinhkhoanId = rs.getString("MADOITUONGDINHKHOAN")== null ? "": rs.getString("MADOITUONGDINHKHOAN") ;
						
					}
					
					this.sotientt  = rs.getString("sotientt").replaceAll(",", "");
					this.sotienttNT = rs.getString("sotienttNT").replaceAll(",", "");
					this.sotienHD  = rs.getString("sotienHD").replaceAll(",", "");
					this.sotienHDNT = rs.getString("sotienHDNT").replaceAll(",", "");
					this.pnganhang = rs.getString("phinganhang").replaceAll(",", "");
					this.pnganhangNT = rs.getString("phinganhangNT").replaceAll(",", "");
					this.thueVAT = rs.getString("vat").replaceAll(",", "");
					this.thueVATNT = rs.getString("vatNT").replaceAll(",", "");
					this.chenhlechVND = rs.getString("chenhlechVND").replaceAll(",", "");
					this.trichphi = rs.getString("trichphi");					
					this.sotaikhoan_tp = rs.getString("sotaikhoan_tp") == null ? "" :rs.getString("sotaikhoan_tp") ;
					this.nganhang_tpId = rs.getString("nganhang_tp_fk")== null ? "" :rs.getString("nganhang_tp_fk");					
					this.chinhanh_tpId = rs.getString("chinhanh_tp_fk");
					this.mahoadon = rs.getString("mahoadon")== null ? "":rs.getString("mahoadon");
					this.mauhoadon = rs.getString("mauhoadon")== null ?"":rs.getString("mauhoadon");
					this.kyhieu = rs.getString("KYHIEU")== null ?"":rs.getString("KYHIEU");
					this.sohoadon = rs.getString("SOHOADON")== null ?"":rs.getString("SOHOADON");
					this.ngayhoadon = rs.getString("NGAYHOADON");
					
					this.tenNCC = rs.getString("TENNCC") == null ? "" : rs.getString("TENNCC");
					
					this.mst = rs.getString("MST")== null ?"":rs.getString("MST");
					this.tienhang = rs.getString("TIENHANG").replaceAll(",", "");
					
					this.thuesuat = rs.getString("THUESUAT").replaceAll(",", "");
					this.tienthue = rs.getString("TIENTHUE").replaceAll(",", "");

					this.nhId_VAT = rs.getString("NGANHANG_PNH_FK")==null?"":rs.getString("NGANHANG_PNH_FK");
					this.cnId_VAT = rs.getString("CHINHANH_PNH_FK")==null?"":rs.getString("CHINHANH_PNH_FK");
					this.tienteId = rs.getString("TIENTE_FK");
					
					this.tigia = rs.getString("TIGIA").replaceAll(",", "");
				}
				rs.close();
			} 
			catch (Exception e) {
				System.out.println(e.toString());
				
			}
		}
		
		//khoi tao them loai KHAC
		if(this.DoiTuongDinhKhoan != null && !(this.DoiTuongDinhKhoan.equals("")))
		{
			createDoiTuongDinhKhoan();
		}
		
		if(this.DoiTuongChiPhiKhac.equals("3"))
		{
			this.count=0;
			if(this.id.length() >0){
				query=	" SELECT COUNT(*) as count"+
						" FROM  ERP_THANHTOANHOADON_PHINGANHANG BT "+
						" WHERE THANHTOANHD_FK = "+this.id;
				rs =this.db.get(query);
				if(rs!=null)
				{
					try
					{
						while(rs.next())
						{
							this.count=rs.getInt("count");
						}rs.close();
					} catch (SQLException e)
					{
						System.out.println("Init But toan Exception "+e.getMessage());
					}
				}
			}
			
			TaiKhoanIds=new String[count];
			dcIds = new String[count];
			KenhbhIds = new String[count];
			PhongBanIds = new String[count];
			TinhThanhIds = new String[count];
			SanPhamIds = new String[count];
			DiabanIds = new String[count];
			BenhvienIds = new String[count];
			Kyhieuhd = new String[count];
			MavvIds = new String[count];
			Sohd = new String[count];
			Ngayhd = new String[count];
			TenNCChd =  new String[count];
			MSThd = new String[count];
			Thuesuathd = new String[count];
			Tienthuehd = new String[count];
			Tienhanghd = new String[count];
			Diengiaihd = new String[count];
			loais = new String[count];
					
			for (int j = 0; j < count; j++){
				TaiKhoanIds[j] = "";
				dcIds[j] = "";
				KenhbhIds[j] = "";
				PhongBanIds[j] = "";
				TinhThanhIds[j] = "";
				DiabanIds[j] = "";
				BenhvienIds[j] = "";
				SanPhamIds[j] = "";
				MavvIds[j]= "";
				Kyhieuhd[j] = "";
				Sohd[j] = "";
				Ngayhd[j] = "";
				TenNCChd[j] = "";
				MSThd[j] = "";
				Thuesuathd[j] = "";
				Tienthuehd[j] = "";
				Tienhanghd[j] = "";
				Diengiaihd[j] = "";
				loais[j] = "";
			}
			
			query=	" SELECT BT.*, " +
					" CASE WHEN BT.SANPHAM_FK IS NOT NULL THEN 1 "+
					"      WHEN BT.NCC_FK IS NOT NULL THEN 2 "+
					"      WHEN BT.NGANHANG_FK IS NOT NULL THEN 3 "+
					"      WHEN BT.TAISAN_FK IS NOT NULL THEN 4 "+
					"      WHEN BT.KHACHHANG_FK IS NOT NULL THEN 5 "+
					"      WHEN BT.NHANVIEN_FK IS NOT NULL THEN 7 "+
					"      WHEN BT.TTCP_FK IS NOT NULL THEN 6 "+
					" 	   ELSE 0 END LOAI "+
					" FROM ERP_THANHTOANHOADON_PHINGANHANG BT "+
					" WHERE THANHTOANHD_FK = "+this.id;
			
			System.out.println(query);
			rs = db.get(query);
			
			if(rs != null)
			{
				try 
				{
					int i = 0 ;
										
					while(rs.next())
					{				
						loais[i] = rs.getString("LOAI");
						
						TaiKhoanIds[i]=rs.getString("TAIKHOAN_FK") == null?"":rs.getString("taikhoan_fk");
						
						if(rs.getString("SANPHAM_FK") != null){
							
							dcIds[i]=rs.getString("SANPHAM_FK");
						
						}else if(rs.getString("NGANHANG_FK") != null){
						
							dcIds[i]=rs.getString("NGANHANG_FK");
						
						}else if(rs.getString("NCC_FK") != null){ 
						
							dcIds[i]=rs.getString("NCC_FK");
						
						}else if(rs.getString("TAISAN_FK") != null){ 
						
							dcIds[i]=rs.getString("TAISAN_FK");
						
						}else if(rs.getString("KHACHHANG_FK") != null){
						
							dcIds[i]=rs.getString("KHACHHANG_FK")+ "-"+rs.getString("ISNPP");
							
						}else if(rs.getString("NHANVIEN_FK") != null){
							
							dcIds[i]=rs.getString("NHANVIEN_FK");
							
						}else if(rs.getString("TTCP_FK") != null){
							
							dcIds[i]=rs.getString("TTCP_FK");
						}
												
						PhongBanIds[i] =  rs.getString("phongban_fk") == null?"":rs.getString("phongban_fk");						
						
						KenhbhIds[i] =  rs.getString("kbh_fk") == null?"":rs.getString("kbh_fk");
						
						TinhThanhIds[i] =  rs.getString("tinhthanh_fk") == null?"":rs.getString("tinhthanh_fk");
						
						SanPhamIds[i] =  rs.getString("sp_fk") == null?"":rs.getString("sp_fk");
						
						DiabanIds[i] = rs.getString("diaban_fk") == null?"":rs.getString("diaban_fk");
						
						BenhvienIds[i] = rs.getString("benhvien_fk") == null?"":rs.getString("benhvien_fk");
						
						MavvIds[i] = rs.getString("vuviec_fk") == null?"":rs.getString("vuviec_fk");
						
						Kyhieuhd[i] = rs.getString("kyhieu")== null?"":rs.getString("kyhieu");
						
						Sohd[i] = rs.getString("Sohoadon");
						
						Ngayhd[i] = rs.getString("Ngayhoadon");
												
						TenNCChd[i] = rs.getString("tenncc");
												
						MSThd[i] = rs.getString("mst");
						
						Thuesuathd[i] = rs.getString("thuesuat");
						
						Tienthuehd[i] = rs.getString("tienthue");
						
						Tienhanghd[i] = rs.getString("tienhang");	
						
						Diengiaihd[i] = rs.getString("diengiai")== null?"":rs.getString("diengiai");
					
						i++;
					
					}
					rs.close();
				}
					
				catch (Exception e) {
					e.printStackTrace();
					
				}
			}
			
		}		
		
		this.createRs();
		
	}

	public void createDoiTuongDinhKhoan()
	{
		String query = "";
		if(this.DoiTuongDinhKhoan.equals("1"))
		{
			query = "select cast(PK_SEQ as varchar) + ' -- ' + MA+ ',' + TEN  as maten from SANPHAM where PK_SEQ = "+ this.doituongdinhkhoanId ;
		}
		else if (this.DoiTuongDinhKhoan.equals("2"))
		{
			query = "select cast(PK_SEQ as varchar) + ' -- ' + MA+ ',' + TEN  as maten from ERP_NGANHANG where PK_SEQ = "+ this.doituongdinhkhoanId ;
		}
		else if (this.DoiTuongDinhKhoan.equals("3"))
		{
			query = "select cast(PK_SEQ as varchar) + ' -- ' + MA+ ',' + TEN  as maten from TraphacoERP.dbo.ERP_NHACUNGCAP where PK_SEQ = "+ this.doituongdinhkhoanId +" AND NPP_FK = 1" ;
		}
		else if (this.DoiTuongDinhKhoan.equals("4"))
		{
			query = "select cast(PK_SEQ as varchar) + ' -- ' + MA+ ',' + TEN  as maten from ERP_TAISANCODINH where PK_SEQ = "+ this.doituongdinhkhoanId ;
		}
		else if (this.DoiTuongDinhKhoan.equals("5"))
		{
			query = "select cast(PK_SEQ as varchar) + ' -- ' + MA+ ',' + TEN  as maten from NHAPHANPHOI where PK_SEQ = "+ this.doituongdinhkhoanId ;
		}
		else if (this.DoiTuongDinhKhoan.equals("6"))
		{
			query = "select cast(PK_SEQ as varchar) + ' -- ' + MA+ ',' + TEN  as maten from ERP_NHANVIEN where PK_SEQ = "+ this.doituongdinhkhoanId ;
		}
		
		ResultSet dtrs = db.get(query);
		if(dtrs != null)
			try {
				while(dtrs.next())
				{
					this.MaTenDoiTuongDinhKhoan = dtrs.getString("maten");
				}
				dtrs.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
	}
	
	public void initDisplay()
	{
		NumberFormat formatter = new DecimalFormat("#,###,###"); 
		String query = " select hd.prefix,  isnull(hd.LoaiThanhToan,'1') as LoaiThanhToan,hd.nhanvien_fk,hd.pk_seq " +
						", hd.ngayghinhan, hd.trangthai, hd.ncc_fk , hd.httt_fk, hd.nganhang_fk, hd.chinhanh_fk, hd.sotaikhoan," +
						" hd.noidungtt, hd.sotientt ,ncc.ma as mancc,ncc.ten as tenncc, nv.ma as manv,nv.ten as tennv," +
						" hd.khachhang_fk, kh.maFAST as makh, kh.ten as tenkh, " +
						" isnull(hd.NHOMNCCCN,'0') nhomncccn , nc.DIENGIAI as tennhomncc , tk.SOHIEUTAIKHOAN +' -- '+ tk.TENTAIKHOAN as dinhkhoanno , " +
						" hd.doituongdinhkhoan , hd.MADOITUONGDINHKHOAN,isnull(hd.THANHTOANTUTIENVAY,0) as THANHTOANTUTIENVAY , " +
						"	hd.ghichu, hd.loaithanhtoan, hd.sotienHD, hd.sotienHDNT, hd.sotientt, hd.sotienttNT, hd.phinganhang, hd.phinganhangNT, " +
						"   hd.vat, hd.vatNT, hd.sotienttNT, isnull(hd.chenhlechVND,0) as chenhlechVND , trichphi, sotaikhoan_tp, nganhang_tp_fk, chinhanh_tp_fk, " +
						"   PNH.mahoadon , PNH.mauhoadon, PNH.KYHIEU, PNH.SOHOADON, PNH.NGAYHOADON, PNH.TENNCC, PNH.MST, isnull(PNH.TIENHANG,0) as TIENHANG, isnull(PNH.THUESUAT,0) as THUESUAT, " +
						"   isnull(PNH.TIENTHUE,0) as TIENTHUE, PNH.NGANHANG_FK AS NGANHANG_PNH_FK, PNH.CHINHANH_FK AS CHINHANH_PNH_FK, hd.TIENTE_FK, hd.TIGIA, isnull(hd.ctkemtheo, '') chungtukemtheo,  " +
						"   hd.dvth_fk, dvth.pk_seq as dvthId, dvth.ma as dvthMa, dvth.Ten as dvthTen, isnull(hd.iskttduyet,0) iskttduyet, isnull(hd.isTICHLUY,0) isTICHLUY  \n"+
						
						" from ERP_THANHTOANHOADON hd  " +
						" left join ERP_THANHTOANHOADON_PHINGANHANG PNH on PNH.THANHTOANHD_FK = hd.PK_SEQ " +
						
						" left join TraphacoERP.dbo.erp_nhacungcap ncc on ncc.pk_Seq=hd.ncc_fk " +						
						" left join erp_nhanvien nv on nv.pk_Seq=hd.nhanvien_fk  " +
						" left join khachhang kh on kh.pk_seq = hd.khachhang_fk " +
						" left join TraphacoERP.dbo.NHOMNHACUNGCAPCN nc on nc.PK_SEQ = hd.Nhomncccn " +
						" left join erp_donvithuchien dvth on dvth.pk_seq = hd.dvth_fk " +
						" left join ERP_TAIKHOANKT tk on tk.SOHIEUTAIKHOAN = hd.dinhkhoanno " +
						"where hd.pk_seq = '" + this.id + "'";
		System.out.println("GET SQL : "+query);
		
		String isTICHLUY = "";
		ResultSet rs = db.get(query);
		if(rs != null)
		{
			try 
			{
				while(rs.next())
				{
					this.ngayghinhan = rs.getString("ngayghinhan");
					this.nccId = rs.getString("ncc_fk")== null ? "": rs.getString("ncc_fk") ;
					this.htttId = rs.getString("httt_fk");
					this.nhomNCCCNId = rs.getString("NHOMNCCCN");
					this.prefix = rs.getString("prefix");
					this.checkThanhtoantuTV= rs.getString("THANHTOANTUTIENVAY");
					this.isktt = rs.getString("iskttduyet");
					
					isTICHLUY = rs.getString("isTICHLUY");
						
					if(this.htttId.equals("100001"))
					{
						this.nganhangId = rs.getString("nganhang_fk");
						this.chinhanhId = rs.getString("chinhanh_fk");
						this.sotaikhoan = rs.getString("sotaikhoan");
					}
					
					this.chungtukemtheo = rs.getString("chungtukemtheo");
					
					this.NhanvienId=rs.getString("nhanvien_fk") ;
					if(this.NhanvienId==null){
						this.NhanvienId="";
						if(this.nccId.length() > 1)
						{
							this.DoiTuongChiPhiKhac = "1";
							this.TenHienThi=rs.getString("ncc_fk")+" -- "+rs.getString("mancc")+" -- "+rs.getString("tenncc");
						}
						else
						{
							this.TenHienThi=rs.getString("NHOMNCCCN")+" -- "+rs.getString("tennhomncc");
						}
					}
					if(this.nccId.length() <=1 && this.NhanvienId.length() >1){
						this.nccId="";
						this.DoiTuongChiPhiKhac = "0";
						this.TenHienThi=rs.getString("nhanvien_fk")+" -- "+rs.getString("manv")+" -- "+rs.getString("tennv");
					}
					
					this.khachhangId = rs.getString("khachhang_fk")== null ? "": rs.getString("khachhang_fk") ;
					if(this.khachhangId.length() > 0){
						if(isTICHLUY.equals("0"))
							this.DoiTuongChiPhiKhac = "2";
						else
							this.DoiTuongChiPhiKhac = "5";
						
						this.TenHienThi=rs.getString("khachhang_fk")+" -- "+rs.getString("makh")+","+rs.getString("tenkh");
					}
					
					this.bophanId = rs.getString("dvth_fk")== null ? "": rs.getString("dvth_fk") ;
					if(this.bophanId.length() > 0){
						this.DoiTuongChiPhiKhac = "4";
						this.bophanTen =rs.getString("dvthId")+" - "+rs.getString("dvthMa")+","+rs.getString("dvthTen");
					}
					
					this.noidungtt = rs.getString("noidungtt");
					this.sotientt = formatter.format(rs.getDouble("sotientt"));
					
					if(this.bophanId.trim().length() <= 0 && this.nccId.trim().length() <= 0 && this.NhanvienId.trim().length() <= 0 && this.khachhangId.trim().length() <= 0 ) // tra Khac
					{
						this.DoiTuongChiPhiKhac = "3";
						this.dinhkhoanno = rs.getString("dinhkhoanno");
						this.DoiTuongDinhKhoan = rs.getString("doituongdinhkhoan")== null ? "": rs.getString("doituongdinhkhoan") ;
						this.doituongdinhkhoanId = rs.getString("MADOITUONGDINHKHOAN")== null ? "": rs.getString("MADOITUONGDINHKHOAN") ;
						
					}
					
					this.sotientt  = rs.getString("sotientt").replaceAll(",", "");
					this.sotienttNT = rs.getString("sotienttNT").replaceAll(",", "");
					this.sotienHD  = rs.getString("sotienHD").replaceAll(",", "");
					this.sotienHDNT = rs.getString("sotienHDNT").replaceAll(",", "");
					this.pnganhang = rs.getString("phinganhang").replaceAll(",", "");
					this.pnganhangNT = rs.getString("phinganhangNT").replaceAll(",", "");
					this.thueVAT = rs.getString("vat").replaceAll(",", "");
					this.thueVATNT = rs.getString("vatNT").replaceAll(",", "");
					this.chenhlechVND = rs.getString("chenhlechVND").replaceAll(",", "");
					this.trichphi = rs.getString("trichphi");					
					this.sotaikhoan_tp = rs.getString("sotaikhoan_tp") == null ? "" :rs.getString("sotaikhoan_tp") ;
					this.nganhang_tpId = rs.getString("nganhang_tp_fk")== null ? "" :rs.getString("nganhang_tp_fk");					
					this.chinhanh_tpId = rs.getString("chinhanh_tp_fk");
					this.mahoadon = rs.getString("mahoadon")== null ? "":rs.getString("mahoadon");
					this.mauhoadon = rs.getString("mauhoadon")== null ?"":rs.getString("mauhoadon");
					this.kyhieu = rs.getString("KYHIEU")== null ?"":rs.getString("KYHIEU");
					this.sohoadon = rs.getString("SOHOADON")== null ?"":rs.getString("SOHOADON");
					this.ngayhoadon = rs.getString("NGAYHOADON");
					
					this.tenNCC = rs.getString("TENNCC")== null ? "" : rs.getString("TENNCC");
					this.mst = rs.getString("MST")== null ?"":rs.getString("MST");
					this.tienhang = rs.getString("TIENHANG").replaceAll(",", "");
					
					this.thuesuat = rs.getString("THUESUAT").replaceAll(",", "");
					this.tienthue = rs.getString("TIENTHUE").replaceAll(",", "");

					this.nhId_VAT = rs.getString("NGANHANG_PNH_FK")==null?"":rs.getString("NGANHANG_PNH_FK");
					this.cnId_VAT = rs.getString("CHINHANH_PNH_FK")==null?"":rs.getString("CHINHANH_PNH_FK");
					this.tienteId = rs.getString("TIENTE_FK");
					
					this.tigia = rs.getString("TIGIA").replaceAll(",", "");
					


				}
				rs.close();
			} 
			catch (Exception e) {
				System.out.println(e.toString());
				
			}
		}
		
		if(this.DoiTuongChiPhiKhac.equals("3"))
		{
			this.count=0;
			if(this.id.length() >0){
				query=	" SELECT COUNT(*) as count"+
						" FROM  ERP_THANHTOANHOADON_PHINGANHANG BT "+
						" WHERE THANHTOANHD_FK = "+this.id;
				rs =this.db.get(query);
				if(rs!=null)
				{
					try
					{
						while(rs.next())
						{
							this.count=rs.getInt("count");
						}rs.close();
					} catch (SQLException e)
					{
						System.out.println("Init But toan Exception "+e.getMessage());
					}
				}
			}
			
			TaiKhoanIds=new String[count];
			dcIds = new String[count];
			KenhbhIds = new String[count];
			PhongBanIds = new String[count];
			TinhThanhIds = new String[count];
			SanPhamIds = new String[count];
			DiabanIds = new String[count];
			BenhvienIds = new String[count];
			Kyhieuhd = new String[count];
			MavvIds = new String[count];
			Sohd = new String[count];
			Ngayhd = new String[count];
			TenNCChd =  new String[count];
			MSThd = new String[count];
			Thuesuathd = new String[count];
			Tienthuehd = new String[count];
			Tienhanghd = new String[count];
			Diengiaihd = new String[count];
			loais = new String[count];
					
			for (int j = 0; j < count; j++){
				TaiKhoanIds[j] = "";
				dcIds[j] = "";
				KenhbhIds[j] = "";
				PhongBanIds[j] = "";
				TinhThanhIds[j] = "";
				DiabanIds[j] = "";
				BenhvienIds[j] = "";
				SanPhamIds[j] = "";
				MavvIds[j]= "";
				Kyhieuhd[j] = "";
				Sohd[j] = "";
				Ngayhd[j] = "";
				TenNCChd[j] = "";
				MSThd[j] = "";
				Thuesuathd[j] = "";
				Tienthuehd[j] = "";
				Tienhanghd[j] = "";
				Diengiaihd[j] = "";
				loais[j] = "";
			}
			
			query=	" SELECT BT.*, " +
					" CASE WHEN BT.SANPHAM_FK IS NOT NULL THEN 1 "+
					"      WHEN BT.NCC_FK IS NOT NULL THEN 2 "+
					"      WHEN BT.NGANHANG_FK IS NOT NULL THEN 3 "+
					"      WHEN BT.TAISAN_FK IS NOT NULL THEN 4 "+
					"      WHEN BT.KHACHHANG_FK IS NOT NULL THEN 5 "+
					"      WHEN BT.NHANVIEN_FK IS NOT NULL THEN 7 "+
					"      WHEN BT.TTCP_FK IS NOT NULL THEN 6 "+
					" 	   ELSE 0 END LOAI "+
					" FROM ERP_THANHTOANHOADON_PHINGANHANG BT "+
					" WHERE THANHTOANHD_FK = "+this.id;
			
			System.out.println(query);
			rs = db.get(query);
			
			if(rs != null)
			{
				try 
				{
					int i = 0 ;
										
					while(rs.next())
					{				
						loais[i] = rs.getString("LOAI");
						
						TaiKhoanIds[i]=rs.getString("TAIKHOAN_FK") == null?"":rs.getString("taikhoan_fk");
						
						if(rs.getString("SANPHAM_FK") != null){
							
							dcIds[i]=rs.getString("SANPHAM_FK");
						
						}else if(rs.getString("NGANHANG_FK") != null){
						
							dcIds[i]=rs.getString("NGANHANG_FK");
						
						}else if(rs.getString("NCC_FK") != null){ 
						
							dcIds[i]=rs.getString("NCC_FK");
						
						}else if(rs.getString("TAISAN_FK") != null){ 
						
							dcIds[i]=rs.getString("TAISAN_FK");
						
						}else if(rs.getString("KHACHHANG_FK") != null){
						
							dcIds[i]=rs.getString("KHACHHANG_FK")+"-"+rs.getString("ISNPP");
							
						}else if(rs.getString("NHANVIEN_FK") != null){
							
							dcIds[i]=rs.getString("NHANVIEN_FK");
							
						}else if(rs.getString("TTCP_FK") != null){
							
							dcIds[i]=rs.getString("TTCP_FK");
						}
												
						PhongBanIds[i] =  rs.getString("phongban_fk") == null?"":rs.getString("phongban_fk");						
						
						KenhbhIds[i] =  rs.getString("kbh_fk") == null?"":rs.getString("kbh_fk");
						
						TinhThanhIds[i] =  rs.getString("tinhthanh_fk") == null?"":rs.getString("tinhthanh_fk");
						
						SanPhamIds[i] =  rs.getString("sp_fk") == null?"":rs.getString("sp_fk");
						
						DiabanIds[i] = rs.getString("diaban_fk") == null?"":rs.getString("diaban_fk");
						
						BenhvienIds[i] = rs.getString("benhvien_fk") == null?"":rs.getString("benhvien_fk");
						
						MavvIds[i] = rs.getString("vuviec_fk") == null?"":rs.getString("vuviec_fk");
						
						Kyhieuhd[i] = rs.getString("kyhieu")== null?"":rs.getString("kyhieu");
						
						Sohd[i] = rs.getString("Sohoadon");
						
						Ngayhd[i] = rs.getString("Ngayhoadon");
												
						TenNCChd[i] = rs.getString("tenncc");
												
						MSThd[i] = rs.getString("mst");
						
						Thuesuathd[i] = rs.getString("thuesuat");
						
						Tienthuehd[i] = rs.getString("tienthue");
						
						Tienhanghd[i] = rs.getString("tienhang");		
						
						Diengiaihd[i] = rs.getString("diengiai")== null?"":rs.getString("diengiai");
					
						i++;
					
					}
					rs.close();
				}
					
				catch (Exception e) {
					e.printStackTrace();
					
				}
			}
			
		}
		
		//khoi tao them loai KHAC
		if(this.DoiTuongDinhKhoan != null && !(this.DoiTuongDinhKhoan.equals("")))
		{
			createDoiTuongDinhKhoan();
		}
				
		this.sotkRs = db.get("SELECT NH_CTY.SOTAIKHOAN, NH_CTY.SOTAIKHOAN + ' - ' + NH.TEN + ' - ' + CN.TEN + ', ' + TT.MA  AS TAIKHOAN " +
				 			 "FROM ERP_NGANHANG_CONGTY NH_CTY " +
				 			 "INNER JOIN ERP_TIENTE TT ON TT.PK_SEQ = NH_CTY.TIENTE_FK " + 
				 			 "INNER JOIN ERP_NGANHANG NH ON NH.PK_SEQ = NH_CTY.NGANHANG_FK " +
				 			 "INNER JOIN ERP_CHINHANH CN ON CN.PK_SEQ = NH_CTY.CHINHANH_FK " +
				 			 "WHERE NH_CTY.TRANGTHAI = 1 AND NH_CTY.NPP_FK = '" + this.nppdangnhap + "'  " );
      
		this.sotkRs_tp = db.get("SELECT NH_CTY.SOTAIKHOAN, NH_CTY.SOTAIKHOAN + ' - ' + NH.TEN + ' - ' + CN.TEN + ', ' + TT.MA  AS TAIKHOAN " +
	 			 				"FROM ERP_NGANHANG_CONGTY NH_CTY " +
	 			 				"INNER JOIN ERP_TIENTE TT ON TT.PK_SEQ = NH_CTY.TIENTE_FK " + 
	 			 				"INNER JOIN ERP_NGANHANG NH ON NH.PK_SEQ = NH_CTY.NGANHANG_FK " +
	 			 				"INNER JOIN ERP_CHINHANH CN ON CN.PK_SEQ = NH_CTY.CHINHANH_FK " +
	 			 				"WHERE NH_CTY.TRANGTHAI = 1 AND NH_CTY.NPP_FK = '" + this.nppdangnhap + "' AND TT.PK_SEQ = 100000 " );
    
		this.tienteRs = db.get("select pk_seq, ma + ', ' + ten as TEN from ERP_TIENTE ");
		

		this.nccRs = db.get("select pk_seq, ma + ', ' + ten as TEN from TraphacoERP.dbo.erp_nhacungcap where trangthai = '1' and NPP_FK = 1 ");
		
		this.NhanvienRs = db.get("SELECT PK_SEQ ,MA+','+TEN AS TEN FROM ERP_NHANVIEN WHERE TRANGTHAI=1 and NPP_FK = "+this.nppdangnhap+"");
		this.nhomNCCRs = db.get("SELECT PK_SEQ, DIENGIAI AS TEN FROM TraphacoERP.dbo.NHOMNHACUNGCAPCN where TRANGTHAI = 1 and NPP_FK = "+this.nppdangnhap+"" ) ;
		this.khachhangRs = db.get("select pk_seq, mafast + ', ' + ten as TEN from KhachHang where trangthai = '1' and NPP_FK = "+this.nppdangnhap+"");
		
		this.htttRs = db.get("select pk_seq, ma, ten from ERP_HINHTHUCTHANHTOAN where trangthai = '1' ");
			
		
		this.nganhangRs = db.get("select pk_seq, ma + ', ' + ten as nhTen from erp_nganhang  ");
		 
		this.PhongBanRs = db.getScrol("select pk_seq, ten from ERP_DONVITHUCHIEN where trangthai = '1' ");
		
		this.KenhBhRs = db.getScrol("select pk_seq, diengiai ten from KENHBANHANG where trangthai = '1' ");
		
		
		String query1 =	"\n SELECT PK_SEQ,SOHIEUTAIKHOAN as MA,TENTAIKHOAN AS TEN, ISNULL(COTTCHIPHI,0)COTTCHIPHI, " +
						"\n ISNULL(DUNGCHOKHO, 0) DUNGCHOKHO, ISNULL(DUNGCHONGANHANG, 0) DUNGCHONGANHANG, " +
						"\n ISNULL(DUNGCHONCC, 0) DUNGCHONCC, ISNULL(DUNGCHOTAISAN, 0) DUNGCHOTAISAN, " +
						"\n ISNULL(DUNGCHOKHACHHANG, 0) DUNGCHOKHACHHANG, ISNULL(DUNGCHONHANVIEN, 0) DUNGCHONHANVIEN " +
						"\n FROM ERP_TAIKHOANKT " +
						"\n WHERE NPP_FK = " + this.nppdangnhap + "";
		
		this.TaiKhoanKTRs=this.db.getScrol(query1);
		
		if(this.dungchos.trim().length() > 0) this.dungchos = this.dungchos.substring(0, this.dungchos.length()-1);
		 
		if(!this.DoiTuongChiPhiKhac.equals("3")) // neu khac loai  (KHAC) 
		{			
			
			if(  this.htttId.length() > 0 && this.hoadonList.size() <= 0)
			{			
				query = "";
				// LOAD NHỮNG HD CỦA NHÀ CUNG CẤP
				if(this.nccId.trim().length() > 0) 
				{
					// LOAIHD: 0 - HOADONNCC
					if (this.id.length() > 0) {
						query +=" SELECT distinct 0 as LOAIHD, b.pk_seq, isnull(b.sohoadon, '') as sohoadon,  isnull(b.kyhieu, '') as kyhieu, b.ngayhoadon, \n" +
								"         b.sotienAVAT as sotiengoc, b.sotienAVAT - ISNULL(thanh_toan.tongthanhtoan,0) as sotienAVAT, SOTIENTT, \n" +
								" '' as POID, c.TIENTE_FK AS TTID, c.TIGIA, b.NGAYDENHANTT, '' SOHOPDONG, '' SOHOPDONGNGOAI \n"+
								"FROM ERP_THANHTOANHOADON_HOADON a \n" +
								"     inner join ERP_HOADONNCC b on a.hoadon_fk = b.pk_seq  and a.loaihd = 0  \n" +
								"	  inner join ERP_PARK c on b.park_fk = c.pk_seq \n" +
								"     left join	\n" +
								"   ( select TTHD.hoadon_fk, sum(TTHD.SOTIENTT) as tongthanhtoan \n" +
								"     from ERP_THANHTOANHOADON_HOADON TTHD inner join ERP_THANHTOANHOADON TT on TTHD.THANHTOANHD_FK = TT.PK_SEQ \n" +
								"     where TT.NPP_FK = "+this.nppdangnhap+"  and TTHD.LOAIHD = 0 AND TT.trangthai !=2 and TTHD.thanhtoanhd_fk != '" + this.id + "' \n" +  //sua trang thai !=2 thanh =1
								"           and TTHD.hoadon_fk in ( select hoadon_fk \n" +
						        "									from ERP_THANHTOANHOADON_HOADON \n" +
								"									where thanhtoanhd_fk = '" + this.id + "' and loaihd = 0 ) \n" +
								"     group by hoadon_fk) thanh_toan on a.hoadon_fk = thanh_toan.hoadon_fk \n" +
								"WHERE a.thanhtoanhd_fk = '" + this.id + "' and c.TIENTE_FK = " + this.tienteId + " and b.NPP_FK = "+this.nppdangnhap+" \n" +
								
								" UNION ALL \n";
				}

					query +="(SELECT distinct 0 as LOAIHD, hoadon.pk_seq, isnull(hoadon.sohoadon, '') as sohoadon, isnull(hoadon.kyhieu, '') as kyhieu, hoadon.ngayhoadon, \n" +
							"         hoadon.sotienAVAT as sotiengoc, hoadon.sotienAVAT - isnull(dathanhtoan.DATHANHTOAN, '0') - isnull(dathanhtoanNCC.DATHANHTOAN, '0')  as sotienAVAT, 0 as sotientt ,\n " +
							" '' as POID, hoadon.ttId, hoadon.tigia,  hoadon.ngaydenhantt, isnull(hoadon.SOHOPDONG,'') SOHOPDONG, isnull(hoadon.SOHOPDONGNGOAI, '') SOHOPDONGNGOAI \n" +
							" FROM ( \n" +
							"       SELECT a.pk_seq, a.kyhieu, a.sohoadon, a.ngayhoadon, a.sotienAVAT, b.TIENTE_FK as ttId, b.TIGIA, a.SOTIENVIET, isnull(a.ngaydenhantt,'') as  ngaydenhantt, '' SOHOPDONG, '' SOHOPDONGNGOAI \n"+
							"       FROM ERP_HOADONNCC a inner join ERP_PARK b on a.park_fk = b.pk_seq \n" +
							"       WHERE  a.NPP_FK = "+this.nppdangnhap+" and b.ncc_fk = '" + this.nccId + "' and b.trangthai = '2' and a.trangthai = '0' \n";
							if(this.id.length() > 0)
							{
								query += " and a.pk_seq not in (select hoadon_fk from ERP_THANHTOANHOADON_HOADON where thanhtoanhd_fk = '" + this.id + "') \n";
							}
						query += " ) hoadon \n" +
							"      left join " +
							"   ( \n" +
							"     SELECT TTHD.HOADON_FK , sum(TTHD.SOTIENTT) as DATHANHTOAN  \n" +
							"     from ERP_THANHTOANHOADON_HOADON TTHD inner join ERP_THANHTOANHOADON TT on TTHD.THANHTOANHD_FK = TT.PK_SEQ \n" +
							"     where  TT.NPP_FK = "+this.nppdangnhap+" AND TT.NCC_FK is not null AND TTHD.LOAIHD = 0 AND TT.TRANGTHAI !=2 and TTHD.HOADON_FK in (select pk_seq from ERP_HOADONNCC where trangthai = 0)  \n" + //sua trang thai !=2 thanh =1
							"     group by HOADON_FK \n" +
							"    ) dathanhtoan  on hoadon.pk_seq = dathanhtoan.hoadon_fk \n" +
							"      left join " +
							"   ( \n" +
							"     select TTHD.HOADON_FK , sum(TTHD.SOTIENTT) as DATHANHTOAN  \n" +
							"     from ERP_THANHTOANHOADONNCC_HOADON TTHD inner join ERP_THANHTOANHOADONNCC TT on TTHD.THANHTOANHD_FK = TT.PK_SEQ \n" +
							"     where TT.npp_fk = "+this.nppdangnhap+" AND TTHD.LOAIHD = 0 AND TT.TRANGTHAI !=2 and TTHD.HOADON_FK in (select pk_seq from ERP_HOADONNCC where trangthai = 0)  \n" + //sua trang thai !=2 thanh =1
							"     group by HOADON_FK \n" +
							"    ) dathanhtoanNCC  on hoadon.pk_seq = dathanhtoanNCC.hoadon_fk \n" +
							"where hoadon.sotienAVAT - isnull(dathanhtoan.DATHANHTOAN, '0') - isnull(dathanhtoanNCC.DATHANHTOAN, '0')  > 0 and hoadon.ttId = " + this.tienteId + " ) \n" ;

					// LOAIHD: 1 - TAMUNG(NCC)
					if (this.id.length() > 0) {
						query += "UNION ALL \n";

						query += "SELECT distinct 1 as LOAIHD, TU.PK_SEQ , CAST(TU.PK_SEQ as nvarchar(50)) as sohoadon ,N'TẠM ỨNG'  AS KYHIEU ,TU.NGAYTAMUNG AS ngayhoadon, SOTIENTAMUNG as sotiengoc, SOTIENTAMUNG \n"+
								 "             -(SELECT ISNULL(SUM(b.SOTIENTT),0) \n"+
								 "               FROM ERP_THANHTOANHOADON_HOADON B INNER JOIN ERP_THANHTOANHOADON A ON A.PK_SEQ=B.THANHTOANHD_FK  \n"+
								 "               WHERE A.NCC_FK = " + (this.nccId == "" ? "0" : this.nccId) + " \n"+
								 " AND B.LOAIHD = 1 AND A.TRANGTHAI <>2 and a.pk_seq <> " + this.id +" \n"+
								 "  AND B.HOADON_FK=TU.PK_SEQ \n" + 
								 "					  AND A.NPP_FK = "+ this.nppdangnhap + "            ) -(SELECT ISNULL(sum(TTHD.SOTIENTT),0) as DATHANHTOAN  \n"+
								 "     	       FROM ERP_THANHTOANHOADONNCC_HOADON TTHD inner join ERP_THANHTOANHOADONNCC TT on TTHD.THANHTOANHD_FK = TT.PK_SEQ \n"+
								 "     	       WHERE TT.NCC_FK = " + (this.nccId == "" ? "0" : this.nccId) +" \n"+
								 " and TTHD.LOAIHD = 1 AND TT.TRANGTHAI = 0   \n"+
								 "					 AND TT.NPP_FK = " + this.nppdangnhap +" \n"+
								 "    	       )  AS sotienAVAT , \n"+
								 "             0 AS SOTIENTT, '' as POID, TU.TIENTE_FK as TTID, "+ this.tigia.replace(",", "") + " AS TIGIA, \n"+
								 "             isnull(TU.ngaydenhantt,'') as ngaydenhantt , '' SOHOPDONG, '' SOHOPDONGNGOAI \n"+
								 "      FROM ERP_TAMUNG TU \n"+
								 "      WHERE  TU.NPP_FK = " + this.nppdangnhap + " \n"+
								 "  	AND  ISNULL(TU.HOANTAT,'0')='0' and TU.TIENTE_FK = (select tiente_fk from ERP_THANHTOANHOADON where pk_seq="+ this.id + " ) \n"+
								 "            and TU.pk_seq not in  \n"+
								 "           (select HOADON_FK  from ERP_THANHTOANHOADON a inner join ERP_THANHTOANHOADON_HOADON b on a.pk_seq=b.thanhtoanhd_fk \n"+
								 "            where b.LOAIHD = 1 AND  b.thanhtoanhd_fk =" + this.id + "  )  \n"+
								 "           and  (SOTIENTAMUNG \n"+
								 "              -( SELECT ISNULL(SUM(b.SOTIENTT),0) \n"+
								 "                 FROM ERP_THANHTOANHOADON_HOADON B INNER JOIN ERP_THANHTOANHOADON A ON A.PK_SEQ=B.THANHTOANHD_FK  \n"+
								 "                 WHERE A.NCC_FK = " + (this.nccId == "" ? "0" : this.nccId) + " \n"+
								 " 				AND b.LOAIHD = 1 AND A.TRANGTHAI <>2 and a.pk_seq <> " + this.id +" \n"+
								 "  				AND b.HOADON_FK = TU.PK_SEQ \n"+
								 "				  	AND A.NPP_FK = "+ this.nppdangnhap + "            ) - (SELECT ISNULL(sum(TTHD.SOTIENTT),0) as DATHANHTOAN  \n"+
								 "     	        FROM ERP_THANHTOANHOADONNCC_HOADON TTHD inner join ERP_THANHTOANHOADONNCC TT on TTHD.THANHTOANHD_FK = TT.PK_SEQ \n"+
								 "     	        WHERE TT.NCC_FK = " + (this.nccId == "" ? "0" : this.nccId) + " \n"+
								 " 				AND TTHD.LOAIHD = 1 AND TT.TRANGTHAI = 0 and TT.NPP_FK = " + this.nppdangnhap + " \n"+
								 "    	        )  \n"+
								 "              >0 ) \n"+ 
								 "           and tu.NCC_FK="+ (this.nccId == "" ? "0" : this.nccId);

						query += " UNION ALL \n"+
								 " select distinct 1 as LOAIHD, HOADON_FK AS PK_SEQ, CAST(HOADON_FK as nvarchar(50)) AS SOHOADON , N'TẠM ỨNG ' as   KYHIEU ,a.ngaytamung as ngayhoadon, SOTIENTAMUNG as sotiengoc \n"+
								 "        ,a.sotientamung -( SELECT ISNULL(SUM(CT.SOTIENTT),0) \n"+
								 "                           FROM ERP_THANHTOANHOADON_HOADON CT INNER JOIN ERP_THANHTOANHOADON A ON A.PK_SEQ=CT.THANHTOANHD_FK \n"+
								 "                           WHERE  A.NPP_FK = " + this.nppdangnhap + " AND  A.NCC_FK = "+ (this.nccId == "" ? "0" : this.nccId) + " \n"+
								 " AND ct.LOAIHD = 1 AND A.TRANGTHAI<>'2'  AND CT.HOADON_FK = B.HOADON_FK and a.pk_seq <>"	+ this.id + " )  \n"+
								 "                        -(SELECT ISNULL(sum(TTHD.SOTIENTT),0) as DATHANHTOAN  \n"+
								 "     	      			   FROM ERP_THANHTOANHOADONNCC_HOADON TTHD inner join ERP_THANHTOANHOADONNCC TT on TTHD.THANHTOANHD_FK = TT.PK_SEQ \n"+
								 "     	     			   WHERE TT.NPP_FK = " + this.nppdangnhap +" \n"+
								 " AND  TT.NCC_FK = " + (this.nccId == "" ? "0" : this.nccId) +" \n"+
								 " AND TTHD.LOAIHD = 1   AND TT.TRANGTHAI = 0   \n"+
								 "    	     			   )  AS sotienAVAT , \n"+
								 "         sotientt, '' as POID, a.TIENTE_FK as TTID ," + this.tigia.replace(",", "") +" \n"+
								 " AS TIGIA , isnull(a.ngaydenhantt,'') as ngaydenhantt, '' SOHOPDONG, '' SOHOPDONGNGOAI \n"+
								 " from ERP_THANHTOANHOADON_HOADON b inner join ERP_TAMUNG a on a.pk_seq=b.HOADON_FK  AND b.LOAIHD = 1 \n"+
								 "      and ( a.sotientamung -( SELECT ISNULL(SUM(CT.SOTIENTT),0) \n"+
								 "                              FROM ERP_THANHTOANHOADON_HOADON CT INNER JOIN ERP_THANHTOANHOADON A ON A.PK_SEQ=CT.THANHTOANHD_FK \n"+
								 "                              WHERE A.NPP_FK = " + this.nppdangnhap +" \n"+
								 "  AND A.NCC_FK = " + (this.nccId == "" ? "0" : this.nccId) + " \n"+
								 " AND ct.LOAIHD = 1 AND A.TRANGTHAI<>'2'  AND CT.HOADON_FK=B.HOADON_FK and a.pk_seq <>"+ this.id + " ) \n"+
								 "     						-(SELECT ISNULL(sum(TTHD.SOTIENTT),0) as DATHANHTOAN  \n"+
								 "     						  FROM ERP_THANHTOANHOADONNCC_HOADON TTHD inner join ERP_THANHTOANHOADONNCC TT on TTHD.THANHTOANHD_FK = TT.PK_SEQ \n"+
								 "     						  WHERE TT.NPP_FK = " + this.nppdangnhap+ " \n"+
								 "   AND TT.NCC_FK = " + (this.nccId == "" ? "0" : this.nccId) + " \n"+
								 " AND TTHD.LOAIHD = 1 AND TT.TRANGTHAI = 0   \n" + "    						  ) \n"+
								 "                            > 0) \n" + " where thanhtoanhd_fk=" + this.id +
								 " and b.LOAIHD = 1 \n";

					} else {
						query += " UNION ALL \n";
						query += " SELECT distinct 1 as LOAIHD, TU.PK_SEQ , CAST(TU.PK_SEQ as nvarchar(50)) as sohoadon ,N'TẠM ỨNG'  AS KYHIEU ,TU.NGAYTAMUNG AS ngayhoadon, SOTIENTAMUNG as sotiengoc, SOTIENTAMUNG   \n"+
								 "           -( SELECT ISNULL(SUM(b.SOTIENTT),0)   \n"+
								 "              FROM ERP_THANHTOANHOADON_HOADON B INNER JOIN ERP_THANHTOANHOADON A ON A.PK_SEQ=B.THANHTOANHD_FK  \n"+
								 "              WHERE  A.NCC_FK = " + (this.nccId == "" ? "0" : this.nccId) +
								 " 				AND b.LOAIHD = 1 AND A.TRANGTHAI<>'2'  AND B.HOADON_FK=TU.PK_SEQ AND A.NPP_FK = "+this.nppdangnhap+" "+
								 "          ) -(SELECT ISNULL(sum(TTHD.SOTIENTT),0) as DATHANHTOAN  \n"+
								 "     	      FROM ERP_THANHTOANHOADONNCC_HOADON TTHD inner join ERP_THANHTOANHOADONNCC TT on TTHD.THANHTOANHD_FK = TT.PK_SEQ \n"+
								 "     	      WHERE TT.NCC_FK = " + (this.nccId == "" ? "0" : this.nccId) +
								 " 			  AND TTHD.LOAIHD = 1 AND TT.TRANGTHAI = 0 AND  TT.NPP_FK = "+this.nppdangnhap+" \n" + 
								 "    	     )  AS sotienAVAT , \n"+
								 "            0 AS SOTIENTT, '' as POID, TU.TIENTE_FK as TTID,"	+ this.tigia.replace(",", "") +
								 " AS TIGIA, isnull(TU.ngaydenhantt,'') as ngaydenhantt, '' SOHOPDONG, '' SOHOPDONGNGOAI  \n"+
								 " FROM ERP_TAMUNG TU \n" + 
								 " WHERE TU.NPP_FK= " + this.nppdangnhap +
								 " AND TU.TIENTE_FK = '" + this.tienteId + "'  and ISNULL(TU.HOANTAT,'0')='0'  and  (SOTIENTAMUNG   \n" +
								 "     -( SELECT ISNULL(SUM(b.SOTIENTT),0) \n"+
								 "        FROM ERP_THANHTOANHOADON_HOADON B INNER JOIN ERP_THANHTOANHOADON A ON A.PK_SEQ=B.THANHTOANHD_FK  \n"+
								 "        WHERE A.NCC_FK = " + (this.nccId == "" ? "0" : this.nccId) +
								 " AND b.LOAIHD = 1 AND A.TRANGTHAI<>'2'  AND B.HOADON_FK=TU.PK_SEQ) \n"+
								 "     -(SELECT ISNULL(sum(TTHD.SOTIENTT),0) as DATHANHTOAN  \n"+
								 "     	FROM ERP_THANHTOANHOADONNCC_HOADON TTHD inner join ERP_THANHTOANHOADONNCC TT on TTHD.THANHTOANHD_FK = TT.PK_SEQ \n"+
								 "     	WHERE TT.NCC_FK = " + (this.nccId == "" ? "0" : this.nccId) +
								 " AND TTHD.LOAIHD = 1 AND TT.TRANGTHAI = 0  \n" + "    	) \n"+
								 "   >0 ) and TU.TRANGTHAI = 1 \n" + " and   NCC_FK="+ (this.nccId == "" ? "0" : this.nccId);

					}

					/*// LOAIHD: 2 - CHIPHINOIBO
					if (this.id.length() > 0) {
						query += " UNION ALL \n";
						query += " select distinct 2 as LOAIHD, mh.PK_SEQ, cast(mh.PK_SEQ as nvarchar(50)) as sohoadon,  N'Chi phí nội bộ'  AS KYHIEU , mh.NGAYMUA as ngayhoadon , \n"+
								 "        mhsp.SOTIENPO as sotiengoc, CASE WHEN tthd.TIENTE_FK= '100000' THEN tt.SOTIENAVAT ELSE tt.SOTIENNT END as SOTIENAVAT, \n"+
								 "		 tt.SOTIENTT , '' as POID ,mh.TIENTE_FK as ttId, mh.tygiaquydoi as TIGIA , mh.ngaydenhantt, mh.SOPO SOHOPDONG, isnull(mh.SOHOPDONG,'') SOHOPDONGNGOAI  \n"+
								 " from ERP_MUAHANG mh \n" + 
								 "       inner join \n"+
								 "       (select a.PK_SEQ, SUM(a.TONGTIENAVAT)as SOTIENPO \n"+
								 "		from  ERP_MUAHANG a \n"+
								 "			  inner join ERP_NHACUNGCAP c on c.PK_SEQ = a.NHACUNGCAP_FK  \n"+
								 "		where c.NOIBO = 1 and a.TIENTE_FK= " + this.tienteId + "  and a.TRANGTHAI= 2 \n"+
								 "       group by a.PK_SEQ )as mhsp on mh.PK_SEQ= mhsp.PK_SEQ  \n"+
								 " 	left join ERP_THANHTOANHOADON_HOADON tt on tt.HOADON_FK = mh.PK_SEQ AND TT.LOAIHD = 2  \n"+
								 " 	left join ERP_THANHTOANHOADON tthd on tt.THANHTOANHD_FK = tthd.PK_SEQ \n"+
								 " 	left join TraphacoERP.dbo.ERP_NHACUNGCAP ncc on ncc.PK_SEQ = mh.NHACUNGCAP_FK \n"+
								 " where ncc.NOIBO = 1 and  mh.NHACUNGCAP_FK = '" + this.nccId +
								 "' and tt.THANHTOANHD_FK = '" + this.id + "' \n";

					}
					query += " UNION ALL \n";
					query += " select distinct 2 as LOAIHD,  mh.PK_SEQ, cast(mh.PK_SEQ as nvarchar(50)) as sohoadon,  N'Chi phí nội bộ'  AS KYHIEU , mh.NGAYMUA as ngayhoadon \n"+
							 " 		,mhsp.SOTIENPO as sotiengoc , mhsp.SOTIENPO - isnull(t.SOTIENTT,0) - isnull(dathanhtoanNCC.DATHANHTOAN,0) as SOTIENAVAT ,0 as sotientt, '' as POID  \n"+
							 " 		,mh.TIENTE_FK as ttId, mh.tygiaquydoi as TIGIA,  mh.ngaydenhantt, mh.SOPO SOHOPDONG, isnull(mh.SOHOPDONG, '' ) SOHOPDONGNGOAI  \n"+
							 " from ERP_MUAHANG mh \n" + "       inner join \n"+
							 "       (select a.PK_SEQ, SUM(a.TONGTIENAVAT)as SOTIENPO \n"+
							 "		from  ERP_MUAHANG a \n"+
							 "			  inner join TraphacoERP.dbo.ERP_NHACUNGCAP c on c.PK_SEQ = a.NHACUNGCAP_FK  \n"+
							 "		where c.NOIBO = 1 and a.TIENTE_FK= " + this.tienteId +
							 "  and a.TRANGTHAI= 2 and a.CONGTY_FK = " + this.ctyId +
							 "       group by a.PK_SEQ )as mhsp on mh.PK_SEQ = mhsp.PK_SEQ  \n"+
							 " 		left join ERP_THANHTOANHOADON_HOADON tt on  tt.HOADON_FK = mh.PK_SEQ  AND TT.LOAIHD = 2 \n"+
							 " 		left join ERP_THANHTOANHOADON t on  t.pk_seq = tt.thanhtoanhd_fk  \n"+
							 "      	left join " + 
							 "   	( \n"+
							 "       select TTHD.HOADON_FK , sum(TTHD.SOTIENTT) as DATHANHTOAN  \n"+
							 "       from ERP_THANHTOANHOADONNCC_HOADON TTHD inner join ERP_THANHTOANHOADONNCC TT on TTHD.THANHTOANHD_FK = TT.PK_SEQ \n"+
							 "       where TTHD.LOAIHD = 2 AND TT.TRANGTHAI = 0 and TT.CONGTY_FK = " + this.ctyId +
							 "       group by HOADON_FK \n"+
							 "       ) dathanhtoanNCC  on mh.PK_SEQ = dathanhtoanNCC.hoadon_fk \n"+
							 " 		left join ERP_NHACUNGCAP ncc on ncc.PK_SEQ = mh.NHACUNGCAP_FK \n"+
							 " where mh.CONGTY_FK = " + this.ctyId + 
							 " and ncc.NOIBO = 1 and mh.NHACUNGCAP_FK = '"+ this.nccId + "' \n" + 
							 " 		and ( tt.CONLAI is null or (tt.CONLAI > 0 \n"+
							 "			and mh.TIENTE_FK = " + this.tienteId + " \n"+
							 " 			and tt.HOADON_FK not in \n"+
							 "				(select distinct tt.HOADON_FK \n" + 
							 "				from ERP_MUAHANG mh \n"+
							 "				left join ERP_THANHTOANHOADON_HOADON tt on  tt.HOADON_FK = mh.PK_SEQ  AND TT.LOAIHD = 2 \n"+
							 "				left join ERP_NHACUNGCAP ncc on ncc.PK_SEQ = mh.NHACUNGCAP_FK \n"+
							 "				where mh.CONGTY_FK = " + this.ctyId + " AND mh.NHACUNGCAP_FK = '"+ this.nccId + "' and tt.CONLAI = 0 and mh.TIENTE_FK = " + this.tienteId + ") \n"+
							 "  			and tt.THANHTOANHD_FK in \n"+
							 "				(select MAX(tt.THANHTOANHD_FK) \n" + 
							 "				from Erp_MuaHang mh  \n"+
							 "				left join ERP_THANHTOANHOADON_HOADON tt on tt.HOADON_FK = mh.PK_SEQ   AND TT.LOAIHD = 2 \n"+
							 " 				left join ERP_THANHTOANHOADON t on  t.pk_seq = tt.thanhtoanhd_fk \n"+
							 "				left join ERP_NHACUNGCAP ncc on ncc.PK_SEQ = mh.NHACUNGCAP_FK \n"+
							 "				where ncc.NOIBO = 1 and mh.TRANGTHAI =2 and mh.TIENTE_FK = " + this.tienteId +
							 "  and  mh.NHACUNGCAP_FK =  '" + this.nccId + "' and t.TRANGTHAI<>2	\n"+
							 "	and mh.CONGTY_FK =" + this.ctyId +
							 "	group by tt.HOADON_FK ) ) ) \n" + 
							 " 		and mh.TRANGTHAI = 2 \n";
					
					if (this.id.length() > 0) {
						query += " and mh.PK_SEQ not in (select HOADON_FK from  ERP_THANHTOANHOADON_HOADON where THANHTOANHD_FK = "	+ this.id + ") \n";
					}
					
					query += "       and  mhsp.SOTIENPO - isnull(t.SOTIENTT,0) - isnull(dathanhtoanNCC.DATHANHTOAN,0) > 0 \n";
*/
					/*// LOAIHD: 3 - CHIPHINHANHANG
					if (this.id.length() > 0) {
						query += " UNION ALL \n";

						query += "select distinct 3 as LOAIHD, cpct.pk_seq , cpct.SOCHUNGTU as sohoadon, cpct.KYHIEUCHUNGTU as kyhieu , cpct.NGAYCHUNGTU as ngayhoadon, (cpct.TIENHANG + (cpct.TIENHANG*(cpct.THUESUAT/100))) as sotiengoc \n"+
								 " ,tt.sotienavat as sotienavat, tt.SOTIENTT as sotientt  , cast(mh.PK_SEQ as nvarchar(50)) as POID, isnull(cp.TIENTE_FK,'100000') as ttId,  isnull(cp.TIGIA,1) as TIGIA, cp.NGAYDENHANTT, isnull(mh.SOPO, '') SOHOPDONG, isnull(mh.SOHOPDONG, '') SOHOPDONGNGOAI \n"+
								 " from ERP_CHIPHINHAPKHAU_CHITIET cpct \n"+
								 " left join ERP_CHIPHINHAPKHAU cp on cp.pk_seq = cpct.CHIPHINHAPKHAU_FK \n"+
								 " left join ERP_NHANHANG nh on nh.PK_SEQ = cp.nhanhang_fk \n"+
								 " left join ERP_MUAHANG mh on mh.PK_SEQ = nh.MUAHANG_FK  \n"+
								 " left join ERP_THANHTOANHOADON_HOADON tt on tt.HOADON_FK = cpct.PK_SEQ  AND TT.LOAIHD = 3 \n"+
								 " left join ERP_THANHTOANHOADON t on t.PK_SEQ = tt.THANHTOANHD_FK  \n"+
								 " where cp.NCCID_CN = '" + this.nccId + "' and tt.THANHTOANHD_FK = '" + this.id + "'  \n";

					}

					query += " UNION ALL \n";

					query += "select distinct 3 as LOAIHD, cpct.pk_seq ,cpct.SOCHUNGTU as sohoadon,  cpct.KYHIEUCHUNGTU as kyhieu , cpct.NGAYCHUNGTU as ngayhoadon, (cpct.TIENHANG + (cpct.TIENHANG*(cpct.THUESUAT/100))) as sotiengoc  \n"+
							 ",case when (tt.SOTIENAVAT is null OR t.TRANGTHAI=2) then (cpct.TIENHANG + (cpct.TIENHANG*(cpct.THUESUAT/100)) - isnull(dathanhtoanNCC.DATHANHTOAN,0) ) else tt.CONLAI end as sotienavat \n"+
							 " ,'0' as sotientt, cast(mh.PK_SEQ as nvarchar(50)) as POID, isnull(cp.TIENTE_FK,'100000') as ttId,  isnull(cp.TIGIA,1) as TIGIA , cp.NGAYDENHANTT, isnull(mh.SOPO, '') SOHOPDONG, isnull(mh.SOHOPDONG, '') SOHOPDONGNGOAI  \n"+
							 "from ERP_CHIPHINHAPKHAU_CHITIET cpct \n"+
							 "left join ERP_CHIPHINHAPKHAU cp on cp.pk_seq = cpct.CHIPHINHAPKHAU_FK \n"+
							 "left join ERP_NHANHANG nh on nh.PK_SEQ = cp.nhanhang_fk \n"+
							 "left join ERP_MUAHANG mh on mh.PK_SEQ = nh.MUAHANG_FK  \n"+
							 "left join ERP_THANHTOANHOADON_HOADON tt on tt.HOADON_FK = cpct.PK_SEQ  AND TT.LOAIHD = 3 \n"+
							 "left join " + 
							 "   	( \n"+
							 "       select TTHD.HOADON_FK , sum(TTHD.SOTIENTT) as DATHANHTOAN  \n"+
							 "       from ERP_THANHTOANHOADONNCC_HOADON TTHD inner join ERP_THANHTOANHOADONNCC TT on TTHD.THANHTOANHD_FK = TT.PK_SEQ \n"+
							 "       where TTHD.LOAIHD = 3 AND TT.TRANGTHAI = 0 and TT.CONGTY_FK = " + this.ctyId +
							 "       group by HOADON_FK \n"+
							 "       ) dathanhtoanNCC  on cpct.PK_SEQ = dathanhtoanNCC.hoadon_fk \n"+
							 "left join ERP_THANHTOANHOADON t on t.PK_SEQ = tt.THANHTOANHD_FK  \n"+
							 "where isnull(cp.TIENTE_FK,'100000') = " + this.tienteId + " and cp.NCCID_CN = '"+ this.nccId + "' and ( tt.CONLAI is null or (tt.CONLAI > 0  \n"+
							 "					 and tt.HOADON_FK not in  \n"+
							 "						(select distinct tt.HOADON_FK  \n"+
							 "						from ERP_CHIPHINHAPKHAU_CHITIET cpct  \n"+
							 "							left join ERP_CHIPHINHAPKHAU cp on cp.pk_seq = cpct.CHIPHINHAPKHAU_FK \n"+
							 "							left join ERP_THANHTOANHOADON_HOADON tt on tt.HOADON_FK = cpct.PK_SEQ  AND TT.LOAIHD = 3 \n"+
							 "						where CP.CONGTY_FK =" + this.ctyId + "  AND cp.NCCID_CN = '"+ this.nccId + "' and tt.CONLAI = 0 and cp.TIENTE_FK= " + this.tienteId + ") \n"+
							 "					  and tt.THANHTOANHD_FK in \n"+
							 "						(select MAX(tt.THANHTOANHD_FK)  \n"+
							 "						from ERP_CHIPHINHAPKHAU_CHITIET cpct    \n"+
							 "							left join ERP_THANHTOANHOADON_HOADON tt on tt.HOADON_FK = cpct.PK_SEQ AND TT.LOAIHD = 3 \n"+
							 "					 		left join ERP_THANHTOANHOADON t on  t.pk_seq = tt.thanhtoanhd_fk  \n"+
							 "							left join ERP_CHIPHINHAPKHAU cp on cp.pk_seq = cpct.CHIPHINHAPKHAU_FK \n"+
							 "						 where cp.CONGTY_FK = " + this.ctyId + " AND cp.trangthai = 1 and cp.TIENTE_FK= " + this.tienteId + "  and  cp.NCCID_CN =  '"+ this.nccId + "' and t.TRANGTHAI<>2	 \n"+
							 "						 group by tt.HOADON_FK ) ) ) \n"+
							 "					 and cp.trangthai = 1  \n";
					if (this.id.length() > 0) {
						query += " and cpct.pk_seq not in (select HOADON_FK from ERP_THANHTOANHOADON_HOADON where THANHTOANHD_FK = "+ this.id + " ) \n";
					}
*/
					if (this.tienteId.equals("100000")) {
						// LOAIHD: 4 - THUENHAPKHAU
					/*	if (this.id.length() > 0) {
							query += "UNION ALL \n";
							query += "select distinct 4 as LOAIHD, tnk.pk_seq , tnk.SOCHUNGTU as sohoadon, N'Thuế nhập khẩu' as kyhieu , tnk.NGAYCHUNGTU as ngayhoadon, tnk.THUENK as sotiengoc \n"+
									 " 		,tt.sotienavat, tt.SOTIENTT as sotientt  ,'' as POID  \n"+
									 " 		,'100000' as ttId, '1' as TIGIA, tnk.ngaydenhantt, '' SOHOPDONG, '' SOHOPDONGNGOAI \n"+
									 "from ERP_THUENHAPKHAU tnk \n"+
									 "		left join ERP_THANHTOANHOADON_HOADON tt on tt.HOADON_FK = tnk.PK_SEQ AND TT.LOAIHD = 4 \n"+
									 "		left join ERP_THANHTOANHOADON t on t.PK_SEQ = tt.THANHTOANHD_FK  \n"+
									 " where tnk.NCC_FK= '" + this.nccId + "' and tt.THANHTOANHD_FK = '" + this.id + "' and tt.LOAITHUE = N'Thuế nhập khẩu' \n" +

									 " union all \n" +

									 "select distinct  4 as LOAIHD, tnk.pk_seq , tnk.SOCHUNGTU as sohoadon, N'VAT nhập khẩu' as kyhieu , tnk.NGAYCHUNGTU as ngayhoadon, tnk.VAT as sotiengoc \n"+
									 " 		,tt.sotienavat as sotienavat, tt.SOTIENTT as sotientt  , '' as POID \n"+
									 " 		,'100000' as ttId, '1' as TIGIA,  tnk.ngaydenhantt, '' SOHOPDONG, '' SOHOPDONGNGOAI \n"+
									 "from ERP_THUENHAPKHAU tnk \n"+
									 "		left join ERP_THANHTOANHOADON_HOADON tt on tt.HOADON_FK = tnk.PK_SEQ AND TT.LOAIHD = 4 \n"+
									 "		left join ERP_THANHTOANHOADON t on t.PK_SEQ = tt.THANHTOANHD_FK  \n"+
									 " where tnk.NCC_FK= '" + this.nccId + "' and tt.THANHTOANHD_FK = '" + this.id +"' and tt.LOAITHUE = N'VAT nhập khẩu' \n";

						}
						query += " UNION ALL \n";
						query += " select distinct  4 as LOAIHD, tnk.pk_seq, tnk.SOCHUNGTU as sohoadon, N'Thuế nhập khẩu' as kyhieu ,  tnk.NGAYCHUNGTU as ngayhoadon, tnk.THUENK as sotiengoc \n"+
								 "		,tnk.THUENK - isnull(tt.sotientt,0)- isnull(dathanhtoanNCC.DATHANHTOAN,0) as sotienavat, '0' as sotientt, '' as POID \n"+
								 " 		,'100000' as ttId, '1' as TIGIA, tnk.ngaydenhantt, '' SOHOPDONG, '' SOHOPDONGNGOAI \n"+
								 " from ERP_THUENHAPKHAU tnk \n"+
								 "		left join ERP_THANHTOANHOADON_HOADON tt on tt.HOADON_FK = tnk.PK_SEQ and tt.LOAITHUE =  N'Thuế nhập khẩu' AND TT.LOAIHD = 4 \n"+
								 "		left join ERP_THANHTOANHOADON t on t.PK_SEQ = tt.THANHTOANHD_FK  \n"+
								 "       left join " + 
								 "   	( \n"+
								 "       select TTHD.HOADON_FK , sum(TTHD.SOTIENTT) as DATHANHTOAN  \n"+
								 "       from ERP_THANHTOANHOADONNCC_HOADON TTHD inner join ERP_THANHTOANHOADONNCC TT on TTHD.THANHTOANHD_FK = TT.PK_SEQ \n"+
								 "       where TTHD.LOAIHD = 4 AND tthd.LOAITHUE =  N'Thuế nhập khẩu' AND TT.TRANGTHAI = 0   \n"+
								 "			  and TT.CONGTY_FK =" + this.ctyId + 
								 "       group by HOADON_FK \n"+
								 "       ) dathanhtoanNCC  on tnk.PK_SEQ  = dathanhtoanNCC.hoadon_fk \n"+
								 " where tnk.NCC_FK = '" + this.nccId+ "' and ( tt.CONLAI is null or (tt.CONLAI > 0 \n"+
								 "					 and tt.HOADON_FK not in \n"+
								 "						(select distinct tt.HOADON_FK \n"+
								 "						from ERP_THUENHAPKHAU tnk \n"+
								 "						left join ERP_THANHTOANHOADON_HOADON tt on tt.HOADON_FK = tnk.PK_SEQ AND TT.LOAIHD = 4 \n"+
								 "						where tnk.NCC_FK = '" + this.nccId+ "' and tt.CONLAI = 0 and tnk.CONGTY_FK = " + this.ctyId +
								 "					  and tt.THANHTOANHD_FK in \n"+
								 "						(select MAX(tt.THANHTOANHD_FK) \n"+
								 "						from ERP_THUENHAPKHAU tnk  \n"+
								 "						left join ERP_THANHTOANHOADON_HOADON tt on tt.HOADON_FK = tnk.PK_SEQ AND TT.LOAIHD = 4 \n"+
								 "						left join ERP_THANHTOANHOADON t on  t.pk_seq = tt.thanhtoanhd_fk \n"+
								 "						 where tnk.trangthai = 2    and tnk.NCC_FK = '" + this.nccId + "' and t.TRANGTHAI<>2 and tnk.CONGTY_FK = " + this.ctyId +
								 "						 group by tt.HOADON_FK ) ) )\n"+
								 "					 and tnk.trangthai in (1,2)  and tnk.THUENK > 0 ) \n";
						
						if (this.id.length() > 0) {
							query += " and tnk.PK_SEQ not in (select HOADON_FK from ERP_THANHTOANHOADON_HOADON where THANHTOANHD_FK= "
									+ this.id + " AND LOAIHD = 4 and LOAITHUE= N'Thuế nhập khẩu') \n";
						}

						query += " UNION ALL \n"+
								 "select distinct 4 AS LOAIHD, tnk.pk_seq ,tnk.SOCHUNGTU as sohoadon, N'VAT nhập khẩu' as kyhieu ,  tnk.NGAYCHUNGTU as ngayhoadon, tnk.VAT as sotiengoc \n"+
								 "		,tnk.VAT - isnull(tt.sotientt,0)- isnull(dathanhtoanNCC.DATHANHTOAN,0) as sotienavat, '0' as sotientt, '' as POID \n"+
								 " 		,'100000' as ttId, '1' as TIGIA, tnk.ngaydenhantt, '' SOHOPDONG, '' SOHOPDONGNGOAI \n"+
								 "from ERP_THUENHAPKHAU tnk \n"+
								 "left join ERP_THANHTOANHOADON_HOADON tt on tt.HOADON_FK = tnk.PK_SEQ and tt.LOAITHUE =  N'VAT nhập khẩu' AND TT.LOAIHD = 4 \n"+
								 "left join ERP_THANHTOANHOADON t on t.PK_SEQ = tt.THANHTOANHD_FK  \n"+
								 "       left join " + 
								 "   	( \n"+
								 "       select TTHD.HOADON_FK , sum(TTHD.SOTIENTT) as DATHANHTOAN  \n"+
								 "       from ERP_THANHTOANHOADONNCC_HOADON TTHD inner join ERP_THANHTOANHOADONNCC TT on TTHD.THANHTOANHD_FK = TT.PK_SEQ \n"+
								 "       where TTHD.LOAIHD = 4 AND tthd.LOAITHUE =  N'VAT nhập khẩu' AND TT.TRANGTHAI = 0  and TT.CONGTY_FK = "	+ this.ctyId + 
								 "       group by HOADON_FK \n"+
								 "       ) dathanhtoanNCC  on tnk.PK_SEQ  = dathanhtoanNCC.hoadon_fk \n"+
								 "where tnk.CONGTY_FK = " + this.ctyId + " AND tnk.NCC_FK = '" + this.nccId +
								 "' and ( tt.CONLAI is null or (tt.CONLAI > 0 \n"+
								 "					 and tt.HOADON_FK not in \n"+
								 "						(select distinct tt.HOADON_FK \n"+
								 "						from ERP_THUENHAPKHAU tnk \n"+
								 "						left join ERP_THANHTOANHOADON_HOADON tt on tt.HOADON_FK = tnk.PK_SEQ AND TT.LOAIHD = 4 \n"+
								 "						where tnk.NCC_FK = '" + this.nccId+
								 "' and tt.CONLAI = 0 and tnk.CONGTY_FK = " + this.ctyId + " ) \n"+
								 "					  and tt.THANHTOANHD_FK in \n"+
								 "						(select MAX(tt.THANHTOANHD_FK) \n"+
								 "						from ERP_THUENHAPKHAU tnk  \n"+
								 "						left join ERP_THANHTOANHOADON_HOADON tt on tt.HOADON_FK = tnk.PK_SEQ AND LOAIHD = 4 \n"+
								 "						left join ERP_THANHTOANHOADON t on  t.pk_seq = tt.thanhtoanhd_fk \n"+
								 "						 where tnk.trangthai = 2    and tnk.NCC_FK = '" + this.nccId+
								 "' and t.TRANGTHAI<>2 and tnk.CONGTY_FK = " + this.ctyId + " \n"+
								 "						 group by tt.HOADON_FK ) ) )\n"+
								 "					 and tnk.trangthai = 2  and tnk.VAT > 0 \n ";
						if (this.id.length() > 0) {
							query += " and tnk.PK_SEQ not in (select HOADON_FK from ERP_THANHTOANHOADON_HOADON where THANHTOANHD_FK= "
									+ this.id + " AND LOAIHD = 4  and LOAITHUE= N'VAT nhập khẩu' ) \n";
						}*/

					}
					// LOAIHD: 5 - CHIPHIKHAC

					/*if (this.id.length() > 0) {
						query += " UNION ALL \n";
						query += "select distinct 5 as LOAIHD, cp.pk_seq , cast(cp.PK_SEQ as nvarchar(50)) as sohoadon, cp.DIENGIAI as kyhieu ,  cp.NGAY as ngayhoadon, (cpct.TONGTIENCHUATHUE + cpct.THUE ) as sotiengoc \n"+
								 "      ,case when cp.TIENTE_FK= '100000' then tt.sotienavat else tt.SOTIENNT end as sotienavat, tt.SOTIENTT as sotientt  , '' as POID, cp.TIENTE_FK as ttId, \n"+
								 "      isnull(cp.tigia,1) as tigia , isnull(cp.ngaydenhantt,'') as ngaydenhantt, '' SOHOPDONG, '' SOHOPDONGNGOAI \n"+
								 "from ERP_CHIPHIKHAC_CHITIET cpct \n"+
								 "     left join ERP_CHIPHIKHAC cp on cp.pk_seq = cpct.CHIPHIKHAC_FK \n"+
								 "     left join ERP_THANHTOANHOADON_HOADON tt on tt.HOADON_FK = cpct.CHIPHIKHAC_FK AND TT.LOAIHD = 5 \n"+
								 "     left join ERP_THANHTOANHOADON t on t.PK_SEQ = tt.THANHTOANHD_FK  \n"+
								 "where cp.DOITUONG = '" + this.nccId + "' and tt.THANHTOANHD_FK = '" + this.id +
								 "' and cp.LOAI= '0' \n";

					}
					query += " UNION ALL \n";
					query += "select distinct 5 as LOAIHD, cp.pk_seq , cast(cp.PK_SEQ as nvarchar(50)) as sohoadon, cp.DIENGIAI as kyhieu,  cp.NGAY as ngayhoadon, (cpct.TONGTIENCHUATHUE + cpct.THUE ) as sotiengoc \n"+
							 "	   ,case when (tt.SOTIENAVAT is null OR t.TRANGTHAI=2) then (cpct.TONGTIENCHUATHUE + cpct.THUE - isnull(xoatamung.dathanhtoan,0) - isnull(dathanhtoanNCC.DATHANHTOAN,0) ) else tt.CONLAI end as sotienavat \n"+
							 "	   ,'0' as sotientt, '' as POID , cp.TIENTE_FK as ttId, isnull(cp.TIGIA,1) as TIGIA, \n"+
							 "      isnull(cp.ngaydenhantt,'') as ngaydenhantt, '' SOHOPDONG, '' SOHOPDONGNGOAI  \n"+
							 "from ERP_CHIPHIKHAC_CHITIET cpct \n"+
							 "     left join ERP_CHIPHIKHAC cp on cp.pk_seq = cpct.CHIPHIKHAC_FK \n"+
							 "     left join ERP_THANHTOANHOADON_HOADON tt on tt.HOADON_FK = cpct.CHIPHIKHAC_FK AND TT.LOAIHD = 5 \n"+
							 "     left join ERP_THANHTOANHOADON t on t.PK_SEQ = tt.THANHTOANHD_FK  \n"+
							 "     left join (select a.cttt_fk,SUM(a.tienthanhtoan) as dathanhtoan \n"+
							 "	         from ERP_XOAKHTRATRUOC_CTTT a inner join ERP_XOAKHTRATRUOC b on a.xoakhtratruoc_fk=b.PK_SEQ \n"+
							 "	         where b.CONGTY_FK = " + this.ctyId + " AND b.TRANGTHAI=1 and b.TIENTE_FK= "+ this.tienteId + " and b.LOAIXOATRATRUOC ='1' and isnull(b.NCC_FK, b.NHANVIEN_FK)= "+ this.nccId + " \n"+
							 "	         group by a.cttt_fk) xoatamung  on xoatamung.cttt_fk = cp.PK_SEQ \n"+
							 "     left join " + 
							 "   	( \n"+
							 "       select TTHD.HOADON_FK , sum(TTHD.SOTIENTT) as DATHANHTOAN  \n"+
							 "       from ERP_THANHTOANHOADONNCC_HOADON TTHD inner join ERP_THANHTOANHOADONNCC TT on TTHD.THANHTOANHD_FK = TT.PK_SEQ \n"+
							 "       where TTHD.LOAIHD = 5 AND TT.TRANGTHAI = 0 and TT.CONGTY_FK = " + this.ctyId + "  \n" + 
							 "       group by HOADON_FK \n"+
							 "       ) dathanhtoanNCC  on cp.PK_SEQ  = dathanhtoanNCC.hoadon_fk \n"+
							 "where  cp.CONGTY_FK = " + this.ctyId + "  AND cp.DOITUONG = '" + this.nccId +
							 "' and cp.TIENTE_FK= " + this.tienteId + " and ( tt.CONLAI is null or (tt.CONLAI > 0 \n"+
							 "					 and tt.HOADON_FK not in \n"+
							 "						(select distinct tt.HOADON_FK \n"+
							 "						from ERP_CHIPHIKHAC_CHITIET cpct \n"+
							 "						left join ERP_CHIPHIKHAC cp on cp.pk_seq = cpct.CHIPHIKHAC_FK \n"+
							 "						left join ERP_THANHTOANHOADON_HOADON tt on tt.HOADON_FK = cpct.CHIPHIKHAC_FK AND TT.LOAIHD = 5 \n"+
							 "						where cp.DOITUONG = '" + this.nccId +
							 "' and tt.CONLAI = 0 and cp.Congty_fk = " + this.ctyId + " ) \n"+
							 "					  and tt.THANHTOANHD_FK in \n"+
							 "						(select MAX(tt.THANHTOANHD_FK) \n"+
							 "						from ERP_CHIPHIKHAC_CHITIET cpct   \n"+
							 "							left join ERP_THANHTOANHOADON_HOADON tt on tt.HOADON_FK = cpct.CHIPHIKHAC_FK AND TT.LOAIHD = 5 \n"+
							 "					 		left join ERP_THANHTOANHOADON t on  t.pk_seq = tt.thanhtoanhd_fk \n"+
							 "							left join ERP_CHIPHIKHAC cp on cp.pk_seq = cpct.CHIPHIKHAC_FK \n"+
							 "						 where cp.trangthai = 1    and  cp.DOITUONG =  '" + this.nccId +
							 "' and t.TRANGTHAI<>2 and cp.CONGTY_FK = " + this.ctyId + " \n" +
							 "						 group by tt.HOADON_FK ) ) )\n" +
							 "					 and cp.trangthai = 1 \n ";

					if (this.DoiTuongChiPhiKhac.equals("1")) { // Neu la NCC
						query += " and cp.LOAI= '0' \n";
					}
					if (this.id.length() > 0) {
						query += " and cp.pk_seq not in (select HOADON_FK from ERP_THANHTOANHOADON_HOADON where THANHTOANHD_FK = "	+ this.id + "  ) \n";
					}
					query += " and ((cpct.TONGTIENCHUATHUE + cpct.THUE - isnull(xoatamung.dathanhtoan,0) - isnull(dathanhtoanNCC.dathanhtoan,0) ) > 0 or tt.CONLAI > 0) \n";
*/
					// LOAIHD: 6 - DENGHITHANHTOAN
					if (this.id.length() > 0) {
						query += " UNION ALL \n";
						
						query += " select distinct 6 as LOAIHD, mh.PK_SEQ, cast(mh.SOPO as nvarchar(50)) as sohoadon,  N'Đề nghị thanh toán'  AS KYHIEU , mh.NGAYMUA as ngayhoadon , mh.TONGTIENAVAT as sotiengoc, \n"+
								 "        CASE WHEN tthd.TIENTE_FK= '100000' THEN tt.SOTIENAVAT ELSE tt.SOTIENNT END as SOTIENAVAT, \n"+
								 "		 tt.SOTIENTT , '' as POID ,mh.TIENTE_FK as ttId, mh.tygiaquydoi as TIGIA, mh.ngaydenhantt, '' SOHOPDONG, '' SOHOPDONGNGOAI  \n"+
								 " from ERP_MUAHANG mh \n"+
								 " 	inner join ERP_THANHTOANHOADON_HOADON tt on tt.HOADON_FK = mh.PK_SEQ  AND TT.LOAIHD = 6 \n"+
								 " 	inner join ERP_THANHTOANHOADON tthd on tt.THANHTOANHD_FK = tthd.PK_SEQ  \n"+
								 " 	inner join TraphacoERP.dbo.ERP_NHACUNGCAP ncc on ncc.PK_SEQ = mh.NHACUNGCAP_FK \n"+
								 " where   mh.NHACUNGCAP_FK = '" + this.nccId + "' and tt.THANHTOANHD_FK = '" + this.id	+ "'  \n";

					}
					query += " UNION ALL \n";
					query += " select distinct 6 as LOAIHD, mh.PK_SEQ, cast(mh.SOPO as nvarchar(50)) as sohoadon,  N'Đề nghị thanh toán'  AS KYHIEU , mh.NGAYMUA as ngayhoadon, mh.TONGTIENAVAT as sotiengoc \n"+
							 " 		, mh.TONGTIENAVAT- isnull(t.SOTIENTT,0) - isnull(DATT.SOTIENTT,0) - isnull(DATHANHTOANNCC.DATHANHTOAN,0) as SOTIENAVAT ,0 as sotientt, '' as POID  \n"+
							 " 		,mh.TIENTE_FK as ttId, mh.tygiaquydoi as TIGIA,  mh.ngaydenhantt, '' SOHOPDONG, '' SOHOPDONGNGOAI  \n"+
							 " from ERP_MUAHANG mh \n"+
							 " 		LEFT JOIN ERP_THANHTOANHOADON_HOADON tt on  tt.HOADON_FK = mh.PK_SEQ AND TT.LOAIHD = 6 \n"+
							 " 		LEFT JOIN ERP_THANHTOANHOADON t on  t.pk_seq = tt.thanhtoanhd_fk  \n"+
							 " 		INNER JOIN TraphacoERP.dbo.ERP_NHACUNGCAP ncc on ncc.PK_SEQ = mh.NHACUNGCAP_FK \n"+
							 " 		LEFT JOIN " + 
							 "       ( \n"+
							 "         SELECT tt.HOADON_FK , SUM(tt.SOTIENTT) as SOTIENTT \n"+
							 "         FROM ERP_THANHTOANHOADON_HOADONBOPHAN tt \n"+
							 "              INNER JOIN ERP_THANHTOANHOADON t on tt.THANHTOANHD_FK = t.PK_SEQ \n"+
							 "         WHERE tt.LOAIHD = 6 AND t.TRANGTHAI != 2  AND tt.NCC_FK = " + this.nccId+
							 " 		   and t.NPP_FK = " + this.nppdangnhap + 
							 "         GROUP BY tt.HOADON_FK \n"+
							 "        ) DATT ON DATT.HOADON_FK = mh.PK_SEQ \n" + 
							 "       LEFT JOIN  " + 
							 "   	( \n"+
							 "       select TTHD.HOADON_FK , sum(TTHD.SOTIENTT) as DATHANHTOAN  \n"+
							 "       from ERP_THANHTOANHOADONNCC_HOADON TTHD inner join ERP_THANHTOANHOADONNCC TT on TTHD.THANHTOANHD_FK = TT.PK_SEQ \n"+
							 "       where TTHD.LOAIHD = 6 AND TT.TRANGTHAI = 0 AND tt.NPP_FK = " + this.nppdangnhap +
							 "       group by HOADON_FK \n"+
							 "       ) dathanhtoanNCC  on mh.PK_SEQ  = dathanhtoanNCC.hoadon_fk \n"+
							 " where mh.NPP_FK =" + this.nppdangnhap + "  AND mh.TRANGTHAI = 1 and mh.NHACUNGCAP_FK = '"+ this.nccId + "' \n"+ 
							 " 		and ( tt.CONLAI is null or  \n"+
							 "           (tt.CONLAI > 0 \n" + 
							 "			and mh.TIENTE_FK = " + this.tienteId + " \n"+
							 " 			and tt.HOADON_FK not in \n"+
							 "				(select distinct tt.HOADON_FK \n" + 
							 "				 from ERP_MUAHANG mh \n"+
							 "				      left join ERP_THANHTOANHOADON_HOADON tt on  tt.HOADON_FK = mh.PK_SEQ AND TT.LOAIHD = 6 \n"+
							 "				      left join TraphacoERP.dbo.ERP_NHACUNGCAP ncc on ncc.PK_SEQ = mh.NHACUNGCAP_FK \n"+
							 "				where mh.NPP_FK = " + this.nppdangnhap +
							 " and mh.TRANGTHAI = 1 and mh.NHACUNGCAP_FK = '" + this.nccId +
							 "' and tt.CONLAI = 0 and mh.TIENTE_FK = " + this.tienteId + ") \n"+
							 "  			and tt.THANHTOANHD_FK in \n"+
							 "				(select MAX(tt.THANHTOANHD_FK) \n" + 
							 "				from Erp_MuaHang mh  \n"+
							 "				    left join ERP_THANHTOANHOADON_HOADON tt on tt.HOADON_FK = mh.PK_SEQ AND TT.LOAIHD = 6 \n"+
							 " 				    left join ERP_THANHTOANHOADON t on  t.pk_seq = tt.thanhtoanhd_fk \n"+
							 "				    left join TraphacoERP.dbo.ERP_NHACUNGCAP ncc on ncc.PK_SEQ = mh.NHACUNGCAP_FK \n"+
							 "				where mh.NPP_FK = " + this.nppdangnhap +
							 " and  mh.TRANGTHAI = 1 and mh.LOAIHANGHOA_FK = '2' and TYPE = '1' and mh.TIENTE_FK = "+ this.tienteId + 
							 "  and  mh.NHACUNGCAP_FK =  '" + this.nccId + "' and t.TRANGTHAI<>2	\n"+
							 "	 			group by tt.HOADON_FK ) ) ) \n"+
							 " 		and  mh.LOAIHANGHOA_FK = '2' and mh.TYPE ='1' \n";
					if (this.id.length() > 0) {
						query += " and mh.PK_SEQ not in (select HOADON_FK from  ERP_THANHTOANHOADON_HOADON where THANHTOANHD_FK = "	+ this.id + ") \n";
					}
					query += "       and  (mh.TONGTIENAVAT - isnull(t.SOTIENTT,0) - isnull(DATHANHTOANNCC.DATHANHTOAN,0) ) > 0 \n";
				}
				
				// LOAD NHỮNG HD CỦA NHÂN VIÊN
				if(this.NhanvienId.trim().length() > 0)
				{
					// LOAIHD: 1 - TAMUNG
	 				
					if(this.id.length() >0)
					{
							query="SELECT distinct 1 AS LOAIHD, TU.PK_SEQ , cast(TU.PK_SEQ as nvarchar(50)) as sohoadon ,N'TẠM ỨNG'  AS KYHIEU ,TU.NGAYTAMUNG AS ngayhoadon, SOTIENTAMUNG as sotiengoc,SOTIENTAMUNG \n" + 
							"             -(SELECT ISNULL(SUM(b.SOTIENTT),0) \n" +
							"               FROM ERP_THANHTOANHOADON_HOADON B INNER JOIN ERP_THANHTOANHOADON A ON A.PK_SEQ=B.THANHTOANHD_FK  \n" +
							"               WHERE B.LOAIHD = 1 AND A.TRANGTHAI <>2 and a.pk_seq <> "+this.id+"  AND B.HOADON_FK=TU.PK_SEQ AND A.NPP_FK = "+this.nppdangnhap+" ) AS sotienAVAT , \n" +
							"             0 AS SOTIENTT, '' as POID \n" +
							"            , TU.TIENTE_FK as TTID, "+this.tigia.replace(",", "")+" AS TIGIA, isnull(TU.ngaydenhantt,'') as ngaydenhantt, '' SOHOPDONG, '' SOHOPDONGNGOAI  \n" +
							"      FROM ERP_TAMUNG TU \n" +
							"      WHERE TU.TRANGTHAI = 1 AND ISNULL(TU.HOANTAT,'0')='0' and TU.TIENTE_FK = (select tiente_fk from ERP_THANHTOANHOADON where pk_seq="+this.id+" ) \n" +
							"            and TU.pk_seq not in  \n" +
							"            (select hoadon_fk \n" +
							"             from ERP_THANHTOANHOADON a inner join ERP_THANHTOANHOADON_HOADON b on a.pk_seq=b.thanhtoanhd_fk \n" +
							"             where   b.thanhtoanhd_fk="+this.id+" and b.LOAIHD = 1 and a.NPP_FK = "+this.nppdangnhap+"  )  \n" +
							"             and  (SOTIENTAMUNG \n" +
							"              -( SELECT ISNULL(SUM(b.SOTIENTT),0) \n" +
							"                 FROM ERP_THANHTOANHOADON_HOADON B INNER JOIN ERP_THANHTOANHOADON A ON A.PK_SEQ=B.THANHTOANHD_FK  \n" +
							"                WHERE A.NPP_FK = "+this.nppdangnhap+"  AND B.LOAIHD = 1 AND A.TRANGTHAI <>2 and a.pk_seq <> "+this.id+"  AND HOADON_FK = TU.PK_SEQ) >0 ) \n" + 
							"             and   tu.NHANVIEN_FK="+ (this.NhanvienId ==""?"0":this.NhanvienId) ;
							
							query+=
							" UNION ALL \n" +
							" select distinct 1 AS LOAIHD, B.HOADON_FK AS PK_SEQ, cast(B.HOADON_FK as nvarchar(50)) AS SOHOADON , N'TẠM ỨNG ' as   KYHIEU ,a.ngaytamung as ngayhoadon,SOTIENTAMUNG as sotiengoc  \n" +
							"       ,a.sotientamung -( SELECT ISNULL(SUM(CT.SOTIENTT),0) \n" +
							"                          FROM ERP_THANHTOANHOADON_HOADON CT INNER JOIN ERP_THANHTOANHOADON A ON A.PK_SEQ=CT.THANHTOANHD_FK \n" +
							"                          WHERE A.NPP_FK = "+this.nppdangnhap+"  AND CT.LOAIHD = 1 AND A.TRANGTHAI<>'2'  AND CT.HOADON_FK=B.HOADON_FK and a.pk_seq <>"+this.id+" )  AS sotienAVAT , \n" +
							"  		sotientt, '' as POID,  a.TIENTE_FK as TTID ,"+this.tigia.replace(",", "")+" AS TIGIA ,isnull(a.ngaydenhantt,'') as ngaydenhantt, '' SOHOPDONG, '' SOHOPDONGNGOAI \n" +
							" from ERP_THANHTOANHOADON_HOADON b \n" +
							"      inner join ERP_TAMUNG a on a.pk_seq=b.HOADON_fk  and b.loaihd = 1  \n" +
							"      and ( a.sotientamung -( SELECT ISNULL(SUM(CT.SOTIENTT),0) \n" +
							"                              FROM ERP_THANHTOANHOADON_HOADON CT INNER JOIN ERP_THANHTOANHOADON A ON A.PK_SEQ=CT.THANHTOANHD_FK \n" + 
							"                              WHERE A.NPP_FK = "+this.nppdangnhap+" AND CT.LOAIHD = 1 AND A.TRANGTHAI<>'2'  AND CT.HOADON_FK=B.HOADON_FK and a.pk_seq <>"+this.id+" ) >0) \n" +
							" where   thanhtoanhd_fk="+this.id+"  \n"  ;						
											
					}
					else
					{ 
						
						query+=" SELECT distinct 1 AS LOAIHD, TU.PK_SEQ , cast(TU.PK_SEQ as nvarchar(50)) as sohoadon ,N'TẠM ỨNG'  AS KYHIEU ,TU.NGAYTAMUNG AS ngayhoadon, SOTIENTAMUNG as sotiengoc, SOTIENTAMUNG   \n" + 
							"        -( SELECT ISNULL(SUM(b.SOTIENTT),0)   \n" +
							" 			FROM ERP_THANHTOANHOADON_HOADON B INNER JOIN ERP_THANHTOANHOADON A ON A.PK_SEQ=B.THANHTOANHD_FK  \n" +
							"		    WHERE B.LOAIHD = 1 AND A.TRANGTHAI<>'2'  AND B.HOADON_FK = TU.PK_SEQ  AND A.NPP_FK = "+this.nppdangnhap+" ) AS sotienAVAT , \n" +
							"        0 AS SOTIENTT, '' as POID, TU.TIENTE_FK as TTID,"+this.tigia.replace(",", "")+" AS TIGIA, isnull(TU.ngaydenhantt,'') as ngaydenhantt, '' SOHOPDONG, '' SOHOPDONGNGOAI  \n" +
							" FROM ERP_TAMUNG TU \n" +
							" WHERE TU.NPP_FK = "+this.nppdangnhap+"  AND TU.TIENTE_FK = '"+this.tienteId+"'  and ISNULL(TU.HOANTAT,'0')='0'  and  (SOTIENTAMUNG   \n" +
							"   -( SELECT ISNULL(SUM(b.SOTIENTT),0) \n" +
							"      FROM ERP_THANHTOANHOADON_HOADON B INNER JOIN ERP_THANHTOANHOADON A ON A.PK_SEQ=B.THANHTOANHD_FK  \n" +
							"      WHERE A.NPP_FK = "+this.nppdangnhap+" AND B.LOAIHD = 1 AND A.TRANGTHAI<>'2'  AND B.HOADON_FK=TU.PK_SEQ) >0 ) \n" +
							"   and TU.TRANGTHAI = 1 \n" +
							"   and   NHANVIEN_FK="+ (this.NhanvienId ==""?"0":this.NhanvienId) ;
					
					}
				
					/*// LOAIHD: 5 - CHIPHIKHAC

					
					if(this.id.length() > 0)
					{
					 query +=" UNION ALL \n"; 
					 query +="select distinct 5 as LOAIHD, cp.pk_seq , cast(cp.PK_SEQ as nvarchar(50)) as sohoadon, cp.DIENGIAI as kyhieu ,  cp.NGAY as ngayhoadon, (cpct.TONGTIENCHUATHUE + cpct.THUE ) as sotiengoc \n" +
							"      ,case when cp.TIENTE_FK= '100000' then tt.sotienavat else tt.SOTIENNT end as sotienavat, tt.SOTIENTT as sotientt  , '' as POID, cp.TIENTE_FK as ttId, \n" +
							"      isnull(cp.tigia,1) as tigia , isnull(cp.ngaydenhantt,'') as ngaydenhantt, '' SOHOPDONG, '' SOHOPDONGNGOAI \n" +
							"from ERP_CHIPHIKHAC_CHITIET cpct \n" +
							"     left join ERP_CHIPHIKHAC cp on cp.pk_seq = cpct.CHIPHIKHAC_FK \n" +
							"     left join ERP_THANHTOANHOADON_HOADON tt on tt.HOADON_FK = cpct.CHIPHIKHAC_FK AND TT.LOAIHD = 5 \n" +
							"     left join ERP_THANHTOANHOADON t on t.PK_SEQ = tt.THANHTOANHD_FK  \n" +
							"where cp.DOITUONG = '"+this.NhanvienId+"' and tt.THANHTOANHD_FK = '"+this.id+"' and cp.LOAI= '1'  \n";
				
					 
					}
					query += " UNION ALL \n"; 
					
					query += "select distinct 5 as LOAIHD, cp.pk_seq , cast(cp.PK_SEQ as nvarchar(50)) as sohoadon, cp.DIENGIAI as kyhieu,  cp.NGAY as ngayhoadon , (cpct.TONGTIENCHUATHUE + cpct.THUE ) as sotiengoc \n" +
							"	   ,case when (tt.SOTIENAVAT is null OR t.TRANGTHAI=2) then (cpct.TONGTIENCHUATHUE + cpct.THUE - isnull(xoatamung.dathanhtoan,0)) else tt.CONLAI end as sotienavat \n" +
							"	   ,'0' as sotientt, '' as POID , cp.TIENTE_FK as ttId, isnull(cp.TIGIA,1) as TIGIA, \n" +
							"      isnull(cp.ngaydenhantt,'') as ngaydenhantt, '' SOHOPDONG, '' SOHOPDONGNGOAI  \n" +
							"from ERP_CHIPHIKHAC_CHITIET cpct \n" +
							"     left join ERP_CHIPHIKHAC cp on cp.pk_seq = cpct.CHIPHIKHAC_FK\n" +
							"     left join ERP_THANHTOANHOADON_HOADON tt on tt.HOADON_FK = cpct.CHIPHIKHAC_FK AND TT.LOAIHD = 5 \n" +
							"     left join ERP_THANHTOANHOADON t on t.PK_SEQ = tt.THANHTOANHD_FK  \n" +
							"     left join (select a.cttt_fk,SUM(a.tienthanhtoan) as dathanhtoan \n" +
							"	         from ERP_XOAKHTRATRUOC_CTTT a inner join ERP_XOAKHTRATRUOC b on a.xoakhtratruoc_fk=b.PK_SEQ \n" +
							"	         where b.TRANGTHAI=1 and b.TIENTE_FK= "+ this.tienteId +" and b.LOAIXOATRATRUOC ='1' and isnull(b.NCC_FK, b.NHANVIEN_FK)= "+ this.NhanvienId +" and b.CONGTY_FK = "+this.ctyId+"  \n" +
							"	         group by a.cttt_fk) xoatamung  on xoatamung.cttt_fk = cp.PK_SEQ \n" +
							"where cp.DOITUONG = '"+this.NhanvienId+"' and cp.TIENTE_FK= "+ this.tienteId +" and ( tt.CONLAI is null or (tt.CONLAI > 0 \n" +
							"	   and tt.HOADON_FK not in \n" +
							"						(select distinct tt.HOADON_FK \n" +
							"						from ERP_CHIPHIKHAC_CHITIET cpct \n" +
							"						left join ERP_CHIPHIKHAC cp on cp.pk_seq = cpct.CHIPHIKHAC_FK \n" +
							"						left join ERP_THANHTOANHOADON_HOADON tt on tt.HOADON_FK = cpct.CHIPHIKHAC_FK AND TT.LOAIHD = 5 \n" +
							"						where cp.DOITUONG = '"+this.NhanvienId+"' and tt.CONLAI = 0 and cp.CONGTY_FK = "+this.ctyId+" ) \n" +
							"					  and tt.THANHTOANHD_FK in \n" +
							"						(select MAX(tt.THANHTOANHD_FK) \n" +
							"						from ERP_CHIPHIKHAC_CHITIET cpct   \n" +
							"							left join ERP_THANHTOANHOADON_HOADON tt on tt.HOADON_FK = cpct.CHIPHIKHAC_FK AND TT.LOAIHD = 5 \n" +
							"					 		left join ERP_THANHTOANHOADON t on  t.pk_seq = tt.thanhtoanhd_fk \n" +
							"							left join ERP_CHIPHIKHAC cp on cp.pk_seq = cpct.CHIPHIKHAC_FK\n" +
							"						 where cp.trangthai = 1    and  cp.DOITUONG =  '"+this.NhanvienId+"' and t.TRANGTHAI<>2	and cp.CONGTY_FK = "+this.ctyId+"  \n" +
							"						 group by tt.HOADON_FK ) ) )\n" +
							"					 and cp.trangthai = 1 \n ";
							
							if(this.DoiTuongChiPhiKhac.equals("0")){ // Neu la NV 
								query += " and cp.LOAI= '1' \n";
							}
							if(this.id.length() > 0)
							{
								query += " and cp.pk_seq not in (select HOADON_FK from ERP_THANHTOANHOADON_HOADON where THANHTOANHD_FK = "+ this.id +"  ) \n";
							}
							query += " and ((cpct.TONGTIENCHUATHUE + cpct.THUE - isnull(xoatamung.dathanhtoan,0) ) > 0 or tt.CONLAI > 0) \n";
					*/
					
					
				// LOAIHD: 6 - DENGHITHANHTOAN
				if(this.id.length() > 0){
					query += " UNION ALL \n" ;
					query += " select distinct 6 as LOAIHD, mh.PK_SEQ, cast(mh.SOPO as nvarchar(50)) as sohoadon,  N'Đề nghị thanh toán'  AS KYHIEU , mh.NGAYMUA as ngayhoadon ,  mh.TONGTIENAVAT as sotiengoc, \n" +
							"        CASE WHEN tthd.TIENTE_FK= '100000' THEN tt.SOTIENAVAT ELSE tt.SOTIENNT END as SOTIENAVAT, \n"+
							"		 tt.SOTIENTT , '' as POID ,mh.TIENTE_FK as ttId, mh.tygiaquydoi as TIGIA,  mh.ngaydenhantt, '' SOHOPDONG, '' SOHOPDONGNGOAI  \n"+
							" from ERP_MUAHANG mh \n" +
							" 	left join ERP_THANHTOANHOADON_HOADON tt on tt.HOADON_FK = mh.PK_SEQ and TT.LOAIHD = 6 \n"+
							" 	inner join ERP_THANHTOANHOADON tthd on tt.THANHTOANHD_FK = tthd.PK_SEQ  \n"+
							" 	inner join ERP_NHANVIEN nv on nv.PK_SEQ = mh.NHANVIEN_FK \n"+
							" where   mh.NHANVIEN_FK = '" + this.NhanvienId + "' and tt.THANHTOANHD_FK = '" +this.id+ "' \n";
																				
					}
				    query += " UNION ALL \n" ;						
					query += " select distinct 6 as LOAIHD,  mh.PK_SEQ, cast(mh.SOPO as nvarchar(50)) as sohoadon,  N'Đề nghị thanh toán'  AS KYHIEU , mh.NGAYMUA as ngayhoadon,  mh.TONGTIENAVAT as sotiengoc \n"+
							" 		, mh.TONGTIENAVAT  - isnull(DATT.SOTIENTT,0) as SOTIENAVAT ,0 as sotientt, '' as POID  \n"+
							" 		,mh.TIENTE_FK as ttId,  mh.tygiaquydoi as TIGIA, mh.ngaydenhantt, '' SOHOPDONG, '' SOHOPDONGNGOAI  \n"+
							" from ERP_MUAHANG mh " +
							" 		left join ERP_THANHTOANHOADON_HOADON tt on  tt.HOADON_FK = mh.PK_SEQ AND tt.LOAIHD = 6  \n"+
							" 		left join ERP_THANHTOANHOADON t on  t.pk_seq = tt.thanhtoanhd_fk  \n"+
							" 		inner join ERP_NHANVIEN nv on nv.PK_SEQ = mh.NHANVIEN_FK \n"+
							" 		left join \n"+
							"       ( \n"+
							"         SELECT tt.HOADON_FK , SUM(tt.SOTIENTT) as SOTIENTT "+
							"         FROM 	 ERP_THANHTOANHOADON_HOADON tt  \n"+
							"                INNER JOIN ERP_THANHTOANHOADON t on tt.THANHTOANHD_FK = t.PK_SEQ "+
							"         WHERE  tt.LOAIHD = 6 AND t.TRANGTHAI != 2  and t.NPP_FK = "+this.nppdangnhap+"  "+
							"         GROUP BY tt.HOADON_FK "+
							"        ) DATT ON DATT.HOADON_FK = mh.PK_SEQ \n"+
							" where mh.TRANGTHAI = 1 and  mh.NHANVIEN_FK = '" + this.NhanvienId + "' \n" +
							" 		and ( tt.CONLAI is null or  \n" +
							"           (tt.CONLAI > 0 \n"+
							"			and mh.TIENTE_FK = "+ this.tienteId +" \n"+
							" 			and tt.HOADON_FK not in \n"+
							"				(select distinct tt.HOADON_FK \n"+
							"				 from ERP_MUAHANG mh \n"+
							"				      left join ERP_THANHTOANHOADON_HOADON tt on  tt.HOADON_FK = mh.PK_SEQ  AND tt.LOAIHD = 6 \n"+
							"				      left join ERP_NHANVIEN nv on nv.PK_SEQ = mh.NHANVIEN_FK \n"+
							"				where mh.NPP_FK = "+this.nppdangnhap+"  and mh.TRANGTHAI = 1 and  mh.NHANVIEN_FK = '" + this.NhanvienId + "' and tt.CONLAI = 0 and mh.TIENTE_FK = "+ this.tienteId +") \n"+
							"  			and tt.THANHTOANHD_FK in \n"+
							"				(select MAX(tt.THANHTOANHD_FK) \n"+
							"				from Erp_MuaHang mh  \n"+
							"				    left join ERP_THANHTOANHOADON_HOADON tt on tt.HOADON_FK = mh.PK_SEQ AND tt.LOAIHD = 6 \n"+
							" 				    left join ERP_THANHTOANHOADON t on  t.pk_seq = tt.thanhtoanhd_fk \n"+
							"				    left join ERP_NHANVIEN nv on nv.PK_SEQ = mh.NHANVIEN_FK \n"+
							"				where mh.NPP_FK = "+this.nppdangnhap+" and mh.TRANGTHAI = 1 and mh.LOAIHANGHOA_FK = '2' and TYPE = '1'  and mh.TIENTE_FK = "+ this.tienteId +"  and  mh.NHANVIEN_FK =  '" + this.NhanvienId + "' and t.TRANGTHAI<>2	\n"+ //trang thai mh=2 da hoan tat
							"	 			group by tt.HOADON_FK ) ) ) \n"+
							" 		 and  mh.LOAIHANGHOA_FK = '2' and mh.TYPE ='1' and isnull(mh.ISDNTT,0) = '1' and mh.NPP_FK = "+this.nppdangnhap+" \n";
							if(this.id.length() > 0)
							{
								query+= " and mh.PK_SEQ not in (select HOADON_FK from  ERP_THANHTOANHOADON_HOADON where THANHTOANHD_FK = "+ this.id +" ) \n" ;
							}
					query+=	"       and  mh.TONGTIENAVAT  - isnull(DATT.SOTIENTT,0) > 0 \n";
									
				}
				
				
				
				// LOAD NHỮNG HD CỦA KHÁCH HÀNG
				if(this.khachhangId.trim().length() > 0)
				{/*					
					if(this.id.trim().length() > 0)
					{
						// LOAIHD: 7 - KHACHHANGTRATRUOC
					    query = "select distinct 7 as LOAIHD, tt.PK_SEQ, cast(tt.PK_SEQ as nvarchar(50)) as SOHOADON, N'Khách hàng trả trước' as KYHIEU, tt.NGAYCHUNGTU AS NGAYHOADON  , \n" +
					    		"       (select  CASE WHEN tt.TIENTE_FK <> '100000'  "+
					    		"		         THEN ISNULL(tt.THUDUOCNT,0) ELSE ISNULL(tt.THUDUOC,0) END   "+
					    		"		 from 	ERP_THUTIEN a where TRANGTHAI = '1' and NOIDUNGTT_FK = '100001' and KHACHHANG_FK = '"+ this.khachhangId +"' and a.pk_seq = tt.pk_seq ) AS SOTIENGOC,  \n"+
					    		"       CASE WHEN tt.TIENTE_FK <> '100000' THEN ttct.SOTIENNT ELSE ttct.SOTIENAVAT END AS SOTIENAVAT , \n" +
					    		"       ttct.SOTIENTT, '' AS POID, tt.TIENTE_FK AS TTID, ISNULL(tt.TIGIA,1) as TIGIA, '' as NGAYDENHANTT, '' SOHOPDONG, '' SOHOPDONGNGOAI  \n" +
					    		"from ERP_THUTIEN tt  inner join ERP_THANHTOANHOADON_HOADON ttct on ttct.HOADON_FK = tt.PK_SEQ AND ttct.LOAIHD = 7 \n"+
					    		"where tt.KHACHHANG_FK = '"+ this.khachhangId +"' and ttct.THANHTOANHD_FK = "+ this.id +" \n";
					    
					    query +=" UNION ALL \n";
					}
				
			        query +="select distinct 7 as LOAIHD, tt.PK_SEQ, cast(tt.PK_SEQ as nvarchar(50)) as SOHOADON, N'Khách hàng trả trước' as KYHIEU, tt.NGAYCHUNGTU AS NGAYHOADON , \n"+
							"       CASE WHEN tt.TIENTE_FK <> '100000'  THEN ISNULL(tt.THUDUOCNT,0)   \n"+
							"            ELSE  ISNULL(tt.THUDUOC,0)  END AS SOTIENGOC, \n"+
							"       CASE WHEN tt.TIENTE_FK <> '100000'  THEN (ISNULL(tt.THUDUOCNT,0)  - ISNULL(DAXOAKH.DAXOA,0) - ISNULL(DATHANHTOAN.DATT,0) ) \n"+
							"            ELSE  (ISNULL(tt.THUDUOC,0)  - ISNULL(DAXOAKH.DAXOAVND,0)- ISNULL(DATHANHTOAN.DATT,0) ) END AS SOTIENAVAT, \n"+
							"       0 as SOTIENTT, '' AS POID, tt.TIENTE_FK as TTID, ISNULL(tt.TIGIA,1) as TIGIA, \n"+
							"       '' as NGAYDENHANTT, '' SOHOPDONG, '' SOHOPDONGNGOAI \n"+
							"from ERP_THUTIEN tt left join \n"+
							"     ( select ct.cttt_fk , sum(tienthanhtoan) as DAXOA, sum(tienthanhtoan*ISNULL(ct.TIGIA,1)) as DAXOAVND \n"+
							"       from ERP_XOAKHTRATRUOC xkh inner join ERP_XOAKHTRATRUOC_CTTT ct on xkh.PK_SEQ = ct.xoakhtratruoc_fk \n"+
							"       where xkh.trangthai = '1' and loaixoatratruoc = 0 "+
							"       group by ct.cttt_fk \n" + 
							"      ) DAXOAKH on tt.PK_SEQ = DAXOAKH.cttt_fk \n"+
							"     left join \n" + 
							"     ( select ct.HOADON_FK, SUM(ct.SOTIENTT) AS DATT \n"+
							"       from ERP_THANHTOANHOADON_HOADON ct inner join ERP_THANHTOANHOADON tthd on ct.THANHTOANHD_FK = tthd.PK_SEQ "+
							"       where   tthd.TRANGTHAI != '2' AND tthd.KHACHHANG_FK = '" + this.khachhangId + "' "+
							"             and tthd.TIENTE_FK = '" + this.tienteId + "' and ct.LOAIHD = '7' \n"+
							"       group by ct.HOADON_FK  "+
							"     )DATHANHTOAN on  tt.PK_SEQ = DATHANHTOAN.HOADON_FK \n"+
							"where  tt.TRANGTHAI = '1' and tt.NOIDUNGTT_FK = '100001' and tt.KHACHHANG_FK = '"+ this.khachhangId + "' and tt.TIENTE_FK = '" + this.tienteId + "' \n"+
							"       and ISNULL(tt.THUDUOC,0)  - ISNULL(DAXOAKH.DAXOAVND,0) - (ISNULL(DATHANHTOAN.DATT,0)*ISNULL(tt.TIGIA,1) ) > 0 \n";
						// if(this.isNpp.equals("0")) query+= " AND tt.LOAIPHIEUTHU
						// IN (1,2) ";
						// if(this.isNpp.equals("1")) query+= " AND tt.LOAIPHIEUTHU
						// IN (0) ";
		
						if (this.id.trim().length() > 0) {
							query += "     and tt.PK_SEQ not in (select HOADON_FK from ERP_THANHTOANHOADON_HOADON where  THANHTOANHD_FK = '"+ this.id + "') ";
						}
				
        							 
				  if(this.id.length() > 0)
				  {
					  // LOAIHD: 6 - DENGHITHANHTOAN	
					  
					query += " UNION ALL \n"; 
					
					query += " select distinct 6 as LOAIHD, mh.PK_SEQ, cast(mh.SOPO as nvarchar(50)) as sohoadon,  N'Đề nghị thanh toán'  AS KYHIEU , mh.NGAYMUA as ngayhoadon , mh.TONGTIENAVAT as sotiengoc, \n" +
							"        CASE WHEN tthd.TIENTE_FK= '100000' THEN tt.SOTIENAVAT ELSE tt.SOTIENNT END as SOTIENAVAT, \n"+
							"		 tt.SOTIENTT , '' as POID ,mh.TIENTE_FK as ttId, mh.tygiaquydoi as TIGIA, mh.ngaydenhantt, '' SOHOPDONG, '' SOHOPDONGNGOAI \n"+
							" from ERP_MUAHANG mh \n" +
							" 	inner join ERP_THANHTOANHOADON_HOADON tt on tt.HOADON_FK = mh.PK_SEQ  AND TT.LOAIHD = 6 \n"+
							" 	inner join ERP_THANHTOANHOADON tthd on tt.THANHTOANHD_FK = tthd.PK_SEQ  \n"+
							" 	inner join KHACHHANG kh on kh.PK_SEQ = mh.KHACHHANG_FK \n"+
							" where   mh.KHACHHANG_FK = '" + this.khachhangId + "' and tt.THANHTOANHD_FK = '" +this.id+ "'  \n";																			
									
				  }
					query += " UNION ALL \n"; 	
					
					query += " select distinct 6 as LOAIHD, mh.PK_SEQ, cast(mh.SOPO as nvarchar(50)) as sohoadon,  N'Đề nghị thanh toán'  AS KYHIEU , mh.NGAYMUA as ngayhoadon, mh.TONGTIENAVAT as sotiengoc \n"+
							 " 		, mh.TONGTIENAVAT - isnull(DATT.SOTIENTT,0)   as SOTIENAVAT ,0 as sotientt, '' as POID  \n"+
						     " 		,mh.TIENTE_FK as ttId, mh.tygiaquydoi as TIGIA,  mh.ngaydenhantt, '' SOHOPDONG, '' SOHOPDONGNGOAI  \n"+
						     " from ERP_MUAHANG mh \n"+	
						     " 	INNER JOIN KHACHHANG kh on kh.PK_SEQ = mh.KHACHHANG_FK \n"+
						     " 	LEFT JOIN \n"+
						     "       ( \n"+
						     "         SELECT tt.HOADON_FK , SUM(tt.SOTIENTT) as SOTIENTT \n"+
						     "         FROM ERP_THANHTOANHOADON_HOADON tt  \n"+
						     "              INNER JOIN ERP_THANHTOANHOADON t on tt.THANHTOANHD_FK = t.PK_SEQ \n"+
						     "         WHERE tt.LOAIHD = 6 AND t.TRANGTHAI != 2 and t.CONGTY_FK  = " + this.ctyId +" \n"+ 
						     "         GROUP BY tt.HOADON_FK \n"+
						     "        ) DATT ON DATT.HOADON_FK = mh.PK_SEQ \n"+
						     " 		LEFT JOIN ERP_THANHTOANHOADON_HOADON tt on  tt.HOADON_FK = mh.PK_SEQ AND TT.LOAIHD = 6 \n"+
						     " 		LEFT JOIN ERP_THANHTOANHOADON t on  t.pk_seq = tt.thanhtoanhd_fk  \n"+							
						     " where mh.CONGTY_FK = " + this.ctyId +" \n"+
						     " AND mh.TRANGTHAI = 1 and mh.KHACHHANG_FK = '"+ this.khachhangId + "' \n"+
						     " 		and ( tt.CONLAI is null or  \n"+
						     "           	(tt.CONLAI > 0 and mh.TIENTE_FK = " + this.tienteId + " \n"+
						     " 				and tt.HOADON_FK not in \n"+
						     "				(select distinct tt.HOADON_FK \n"+
						     "				 from ERP_MUAHANG mh \n"+
						     "				      left join ERP_THANHTOANHOADON_HOADON tt on  tt.HOADON_FK = mh.PK_SEQ AND TT.LOAIHD = 6 \n"+
						     "				      left join KHACHHANG kh on kh.PK_SEQ = mh.KHACHHANG_FK \n"+
						     "				where mh.TRANGTHAI = 1 and mh.KHACHHANG_FK = '" + this.khachhangId +"' \n"+
						     " 				and tt.CONLAI = 0 and mh.TIENTE_FK = " + this.tienteId + " and mh.CONGTY_FK = "+ this.ctyId + " ) \n"+
						     "  			and tt.THANHTOANHD_FK in \n"+
						     "				(select MAX(tt.THANHTOANHD_FK) \n"+ 
						     "				 from Erp_MuaHang mh  \n"+
						     "				    left join ERP_THANHTOANHOADON_HOADON tt on tt.HOADON_FK = mh.PK_SEQ AND TT.LOAIHD = 6 \n"+
						     " 				    left join ERP_THANHTOANHOADON t on  t.pk_seq = tt.thanhtoanhd_fk \n"+
						     "				    left join KHACHHANG kh on kh.PK_SEQ = mh.KHACHHANG_FK \n"+
						     "				where mh.TRANGTHAI = 1 and mh.LOAIHANGHOA_FK = '2' and TYPE = '1' and mh.TIENTE_FK = "+ this.tienteId + " \n"+ 
						     "  			and  mh.KHACHHANG_FK =  '" + this.khachhangId+ "' and t.TRANGTHAI<>2 and mh.CONGTY_FK = " + this.ctyId + " 	\n"+
						     "	 			group by tt.HOADON_FK ) ) ) \n"+
						     " 		and  mh.LOAIHANGHOA_FK = '2' and mh.TYPE ='1' and isnull(mh.ISDNTT,0) = '1' \n";
							if (this.id.length() > 0) {
								query += " and mh.PK_SEQ not in (select HOADON_FK from  ERP_THANHTOANHOADON_HOADON where THANHTOANHD_FK = "	+ this.id + ") \n";
							}
				query += "       and  (mh.TONGTIENAVAT - isnull(t.SOTIENTT,0)   ) > 0 \n";
				
				
				// HÓA ĐƠN HÀNG TRẢ VỀ
				if(this.id.length() > 0)
				{
					query += " UNION ALL \n"; 
					query += 
					"	select distinct 8 as LOAIHD, hd.PK_SEQ, hd.SOHOADON as sohoadon,  N'Hóa đơn hàng trả về'  AS KYHIEU , hd.NGAYXUATHD as ngayhoadon , \n"+
					"	 HD.TONGTIENAVAT as sotiengoc,  tt.SOTIENAVAT SOTIENAVAT, \n"+
					"	 tt.SOTIENTT , '' as POID , isnull(hd.TIENTE_FK, 100000) as ttId, isnull(hd.TYGIA,1) as TIGIA, '' ngaydenhantt, '' SOHOPDONG, '' SOHOPDONGNGOAI \n"+
					" from ERP_HOADON hd \n"+
					"	inner join ERP_THANHTOANHOADON_HOADON tt on tt.HOADON_FK = hd.PK_SEQ  AND TT.LOAIHD = 8 \n"+
					"	inner join ERP_THANHTOANHOADON tthd on tt.THANHTOANHD_FK = tthd.PK_SEQ  \n"+
					"	inner join KHACHHANG kh on kh.PK_SEQ = hd.KHACHHANG_FK \n"+
					
					"  LEFT JOIN ( \n"+
					"  SELECT HOADON_FK, SUM(ISNULL(DATHANHTOAN, 0)) as DATHANHTOAN  \n"+
					"  FROM  \n"+
					"  (  \n"+ 
					" 		SELECT TTHD.HOADON_FK , sum(TTHD.SOTIENTT) as DATHANHTOAN  \n"+  
					" 		FROM ERP_XOAKHTRATRUOC_HOADON TTHD \n"+
					" 		INNER JOIN ERP_XOAKHTRATRUOC TT on TTHD.XOAKHTRATRUOC_FK = TT.PK_SEQ  \n"+
					" 		WHERE TT.TRANGTHAI != 2 AND TTHD.LOAIHD = '7' and TTHD.KHACHHANG_FK = "+this.khachhangId+" and isnull(TT.isNPP,0) = 0 \n"+ 
					" 		group by HOADON_FK  \n"+
	
					" 		UNION ALL    \n"+
	
					" 		SELECT TTHD.HOADON_FK , sum(TTHD.SOTIENTT) as DATHANHTOAN   \n"+
					" 		FROM ERP_THUTIEN_HOADON TTHD \n"+
					" 		INNER JOIN ERP_THUTIEN TT on TTHD.THUTIEN_FK = TT.PK_SEQ  \n"+
					" 		WHERE  TT.TRANGTHAI != 2 AND TTHD.LOAIHOADON = '7' and isnull(TTHD.isNPP,0) = 0 AND TT.BANGKE_FK IS NULL  \n"+
					" 		group by HOADON_FK  \n"+
	
					" 		UNION ALL  \n"+
	
					" 		SELECT TTHD.HOADON_FK , sum(TTHD.SOTIENTT) as DATHANHTOAN   \n"+
					" 		FROM ERP_THUTIEN_HOADON TTHD \n"+
					" 		INNER JOIN ERP_THUTIEN TT on TTHD.THUTIEN_FK = TT.PK_SEQ \n"+ 
					" 		WHERE  TT.TRANGTHAI != 2 AND TTHD.LOAIHOADON = '7' and isnull(TTHD.isNPP,0) = 0 AND TT.BANGKE_FK IS NOT NULL \n"+
					" 		group by HOADON_FK  \n"+
	
					" 		UNION ALL  \n"+
					 
					" 		select TTHD.HOADON_FK , sum(TTHD.XOANO) as DATHANHTOAN \n"+   
					" 		from ERP_BUTRUKHACHHANG_CHITIET TTHD inner join ERP_BUTRUKHACHHANG TT on TTHD.BTKH_FK = TT.PK_SEQ  \n"+ 
					" 		where  TT.TRANGTHAI != 2 AND TTHD.LOAIHD = '7' and isnull(TT.isNPP,0) = 0 \n"+
					" 		group by HOADON_FK \n"+
							
					" 		UNION ALL  \n"+
							
					" 		select ct.HOADON_FK, SUM(ct.SOTIENTT) AS DATT \n"+
					" 	    from ERP_THANHTOANHOADON_HOADON ct inner join ERP_THANHTOANHOADON tthd on ct.THANHTOANHD_FK = tthd.PK_SEQ  \n"+  
					" 	    where   tthd.TRANGTHAI != '2' \n"+            
					" 	    and  ct.LOAIHD = '8' AND TTHD.PK_SEQ != "+ (this.id.length() <= 0 ? 0 : this.id) +
					" 	    group by ct.HOADON_FK \n"+		       
	
					" 	)HOADONDATT  group by HOADON_FK  \n"+								
					"  )dathanhtoan on hd.pk_seq = dathanhtoan.hoadon_fk    \n"+
											 
					" where   hd.KHACHHANG_FK = '"+this.khachhangId+"' and tt.THANHTOANHD_FK = '"+this.id+"' \n";
					
				}
				
				query += " UNION ALL \n"+
				
						 " SELECT 	distinct 8 as LOAIHD, hd.PK_SEQ, hd.SOHOADON as SOHOADON, N'Hóa đơn hàng trả về' as KYHIEU, hd.NGAYXUATHD AS NGAYHOADON , \n"+
					     "  		hd.TONGTIENAVAT SOTIENGOC, \n"+ 
					     " 			(ISNULL(hd.TONGTIENAVAT,0)  - ISNULL(dathanhtoan.DATHANHTOAN,0) ) SOTIENAVAT, \n"+
					     "  		0 as SOTIENTT, '' AS POID, isnull(hd.TIENTE_FK,10000) as TTID, ISNULL(hd.TYGIA,1) as TIGIA, \n"+
					     "  		'' as NGAYDENHANTT, '' SOHOPDONG, '' SOHOPDONGNGOAI \n"+
					     " FROM ERP_HOADON hd \n"+
					     " LEFT JOIN ( \n"+
					     " SELECT HOADON_FK, SUM(ISNULL(DATHANHTOAN, 0)) as DATHANHTOAN  \n"+
					     " FROM  \n"+
						 " ( \n"+  
						 "		SELECT TTHD.HOADON_FK , sum(TTHD.SOTIENTT) as DATHANHTOAN    \n"+
						 "		FROM ERP_XOAKHTRATRUOC_HOADON TTHD \n"+
						 "		INNER JOIN ERP_XOAKHTRATRUOC TT on TTHD.XOAKHTRATRUOC_FK = TT.PK_SEQ  \n"+
						 "		WHERE TT.TRANGTHAI != 2 AND TTHD.LOAIHD = '7' and TTHD.KHACHHANG_FK = "+this.khachhangId+" and isnull(TT.isNPP,0) = 0 \n"+
						 "		group by HOADON_FK  \n"+
						
						 "		UNION ALL \n"+   
	
						 "		SELECT TTHD.HOADON_FK , sum(TTHD.SOTIENTT) as DATHANHTOAN   \n"+
						 "		FROM ERP_THUTIEN_HOADON TTHD \n"+
						 "		INNER JOIN ERP_THUTIEN TT on TTHD.THUTIEN_FK = TT.PK_SEQ  \n"+
						 "		WHERE  TT.TRANGTHAI != 2 AND TTHD.LOAIHOADON = '7' and isnull(TTHD.isNPP,0) = 0 AND TT.BANGKE_FK IS NULL \n"+ 
						 "		group by HOADON_FK  \n"+
					
						 "		UNION ALL \n"+ 
					
						 "		SELECT TTHD.HOADON_FK , sum(TTHD.SOTIENTT) as DATHANHTOAN   \n"+
						 "		FROM ERP_THUTIEN_HOADON TTHD \n"+
						 "		INNER JOIN ERP_THUTIEN TT on TTHD.THUTIEN_FK = TT.PK_SEQ  \n"+
						 "		WHERE  TT.TRANGTHAI != 2 AND TTHD.LOAIHOADON = '7' and isnull(TTHD.isNPP,0) = 0 AND TT.BANGKE_FK IS NOT NULL \n"+
						 "		group by HOADON_FK  \n"+
					
						 "		UNION ALL \n"+ 
						 
						 "		select TTHD.HOADON_FK , sum(TTHD.XOANO) as DATHANHTOAN    \n"+
						 "		from ERP_BUTRUKHACHHANG_CHITIET TTHD inner join ERP_BUTRUKHACHHANG TT on TTHD.BTKH_FK = TT.PK_SEQ \n"+  
						 "		where  TT.TRANGTHAI != 2 AND TTHD.LOAIHD = '7' and isnull(TT.isNPP,0) = 0 \n"+
						 "		group by HOADON_FK \n"+
								
						 "		UNION ALL \n"+ 
								
						 "		select ct.HOADON_FK, SUM(ct.SOTIENTT) AS DATT \n"+
						 "	    from ERP_THANHTOANHOADON_HOADON ct inner join ERP_THANHTOANHOADON tthd on ct.THANHTOANHD_FK = tthd.PK_SEQ    \n"+    
						 "	    where   tthd.TRANGTHAI != '2'     \n"+        
						 "	    and tthd.TIENTE_FK = '"+this.tienteId+"' and ct.LOAIHD = '8' AND TTHD.PK_SEQ != "+ (this.id.length() <= 0 ? 0 : this.id) +
						 "	    group by ct.HOADON_FK  \n"+				       
					
						 "	)HOADONDATT  group by HOADON_FK  	\n"+							
						 " )dathanhtoan on hd.pk_seq = dathanhtoan.hoadon_fk     \n"+
						 " where  hd.TRANGTHAI = '1' and hd.KHACHHANG_FK = '"+this.khachhangId+"' AND HD.LOAIHOADON = 2  AND (ISNULL(hd.TONGTIENAVAT,0)  - ISNULL(dathanhtoan.DATHANHTOAN,0) ) !=0 ";
						 if (this.id.trim().length() > 0) {
								query += "       and hd.PK_SEQ not in (select HOADON_FK from ERP_THANHTOANHOADON_HOADON where  THANHTOANHD_FK = '"+ this.id + "') ";
						  }  
				
				*/}
				
				if(this.bophanId.trim().length() > 0)
				{
					
					// LOAIHD: 6 - DENGHITHANHTOAN (NV)
					
					if(this.id.length() > 0){
						query +=" select distinct '0' as DOITUONG ,nv.PK_SEQ as DOITUONG_FK, nv.MA as MADOITUONG , 6 as LOAIHD, mh.PK_SEQ, cast(mh.SOPO as nvarchar(50)) as sohoadon,  N'Đề nghị thanh toán'  AS KYHIEU , mh.NGAYMUA as ngayhoadon , mh.TONGTIENAVAT as sotiengoc, \n" +
								"        CASE WHEN tthd.TIENTE_FK= '100000' THEN tt.SOTIENAVAT ELSE tt.SOTIENNT END as SOTIENAVAT, \n"+
								"		 tt.SOTIENTT , '' as POID ,mh.TIENTE_FK as ttId, mh.tygiaquydoi as TIGIA,  mh.ngaydenhantt, '' SOHOPDONG, '' SOHOPDONGNGOAI  \n"+
								" from ERP_MUAHANG mh \n" +
								" 	inner join ERP_THANHTOANHOADON_HOADONBOPHAN tt on tt.HOADON_FK = mh.PK_SEQ and TT.LOAIHD = 6 \n"+
								" 	inner join ERP_THANHTOANHOADON tthd on tt.THANHTOANHD_FK = tthd.PK_SEQ  \n"+
								" 	inner join ERP_NHANVIEN nv on nv.PK_SEQ = mh.NHANVIEN_FK \n"+
								" where   tt.NHANVIEN_FK is not null and tt.THANHTOANHD_FK = '" +this.id+ "' \n";
															
						query += " UNION ALL \n" ;
							
						}
					    						
						query += " select distinct '0' as DOITUONG ,nv.PK_SEQ as DOITUONG_FK, nv.MA as MADOITUONG , 6 as LOAIHD,  mh.PK_SEQ, cast(mh.SOPO as nvarchar(50)) as sohoadon,  N'Đề nghị thanh toán'  AS KYHIEU , mh.NGAYMUA as ngayhoadon, mh.TONGTIENAVAT as sotiengoc \n"+
								" 		, mh.TONGTIENAVAT  - isnull(DATHANHTOAN.DATHANHTOAN,0) - isnull(DATHANHTOANBP.DATHANHTOAN,0) as SOTIENAVAT ,0 as sotientt, '' as POID  \n"+
								" 		,mh.TIENTE_FK as ttId,  mh.tygiaquydoi as TIGIA, mh.ngaydenhantt, '' SOHOPDONG, '' SOHOPDONGNGOAI  \n"+
								" from ERP_MUAHANG mh " +
								" 		inner join ERP_NHANVIEN nv on nv.PK_SEQ = mh.NHANVIEN_FK \n"+
								" 		left join \n"+
								"       ( \n"+
								"        SELECT t.NHANVIEN_FK, tt.HOADON_FK, SUM(tt.SOTIENTT) as DATHANHTOAN \n"+
								"        FROM ERP_THANHTOANHOADON_HOADON tt  \n"+
								" 		      inner join ERP_THANHTOANHOADON t on  t.pk_seq = tt.thanhtoanhd_fk  \n"+
								"        WHERE t.NHANVIEN_FK is not null  and t.TRANGTHAI != 2 AND tt.LOAIHD = 6  "+
								"        GROUP BY t.NHANVIEN_FK, tt.HOADON_FK \n"+
							    "        ) DATHANHTOAN ON mh.PK_SEQ = DATHANHTOAN.HOADON_FK AND mh.NHANVIEN_FK = DATHANHTOAN.NHANVIEN_FK  \n"+
							    " 		left join \n"+
								"       ( \n"+
								"        SELECT tt.NHANVIEN_FK, tt.HOADON_FK, SUM(tt.SOTIENTT) as DATHANHTOAN \n"+
								"        FROM ERP_THANHTOANHOADON_HOADONBOPHAN tt   \n"+
								" 		      inner join ERP_THANHTOANHOADON t on  t.pk_seq = tt.thanhtoanhd_fk  \n"+
								"        WHERE tt.NHANVIEN_FK is not null  and t.TRANGTHAI != 2 ";
							if(this.id.trim().length() > 0)
							{
								query += " and tt.THANHTOANHD_FK  != "+ this.id +"  ";
							}
						query +="        GROUP BY tt.NHANVIEN_FK, tt.HOADON_FK \n"+
							    "        ) DATHANHTOANBP ON mh.PK_SEQ = DATHANHTOANBP.HOADON_FK AND mh.NHANVIEN_FK = DATHANHTOANBP.NHANVIEN_FK  \n"+							    
								" where  mh.NPP_FK = "+this.nppdangnhap+"  and mh.TONGTIENAVAT  - isnull(DATHANHTOAN.DATHANHTOAN,0) - isnull(DATHANHTOANBP.DATHANHTOAN,0) >0 and mh.TRANGTHAI = 1 and  mh.NHANVIEN_FK is not null and mh.DONVITHUCHIEN_FK = "+ this.bophanId +" \n";
							 if(this.id.trim().length()>0){
								 query+= " and mh.PK_SEQ not in (select HOADON_FK from ERP_THANHTOANHOADON_HOADONBOPHAN where THANHTOANHD_FK = "+this.id+" ) \n";
							 }
						 query += 		" order by nv.PK_SEQ ";
				}
			
				if(this.DoiTuongChiPhiKhac.equals("5"))
				{
					query = "";
					if(this.khachhangId.trim().length() >0 )
					{
						if(this.id.trim().length()>0)
						{
							query = // TÍCH LŨY CẦN DUYỆT
								" SELECT distinct 8 as LOAIHD,PK_SEQ , SCHEME sohoadon, SCHEME AS KYHIEU, ngaykyhd ngayhoadon, tonggiatri as sotiengoc, \n"+
								" 		(giatritra - tonggiatri - isnull(DATHANHTOAN.DATT,0)) as SOTIENAVAT , isnull(TICHLUY.DATT,0) as sotientt, '' as POID, \n"+
								"        100000 as ttId, 1 as TIGIA, ngayketthuchd ngaydenhantt, sohopdong SOHOPDONG, '' SOHOPDONGNGOAI \n"+
								" FROM \n"+ 
								" ( \n"+
								"	SELECT DKKMTICHLUY_FK PK_SEQ, c.SCHEME , doanhsoDAT, donvitra, sanphamtra, soluongtra, giatritra, a.ngaykyhd, a.ngayketthuchd, \n"+
								"		   a.sohopdong, \n"+
								"		   ISNULL( ( SELECT SUM(TONGGIATRI)  FROM ERP_DONDATHANGNPP_TICHLUY_TRATL \n"+
								"				  	 WHERE DKKMID = a.DKKMTICHLUY_FK \n"+
								"				  	 AND DONDATHANGID in ( SELECT PK_SEQ FROM ERP_DONDATHANGNPP  \n"+
								"										   WHERE KHACHHANG_FK = '"+this.khachhangId+"' and trangthai != 3 ) ) , 0) as tonggiatri \n"+
								"	FROM DANGKYKM_TICHLUY_KHACHHANG a \n"+
								"		INNER JOIN DANGKYKM_TICHLUY b on a.DKKMTICHLUY_FK = b.PK_SEQ \n"+
								"		INNER join TIEUCHITHUONGTL c on b.TIEUCHITL_FK = c.PK_SEQ \n"+		
								"	WHERE KHACHHANG_FK = '"+this.khachhangId+"' and DUYET = '1' and MucDat is not null \n"+
								"		and c.TRANGTHAI = '1' and c.khongcanduyettra = '0'  \n"+
								"		and donvitra != 2 \n"+
								" ) \n"+
								" DATA \n"+
								" LEFT JOIN \n"+
								" ( SELECT ct.HOADON_FK, SUM(ct.SOTIENTT) AS DATT \n"+
								"   FROM ERP_THANHTOANHOADON_HOADON ct inner join ERP_THANHTOANHOADON tthd on ct.THANHTOANHD_FK = tthd.PK_SEQ \n"+   
								"   where   tthd.TRANGTHAI != '2' AND tthd.KHACHHANG_FK = '"+this.khachhangId+"' and tthd.NPP_FK = " +this.nppdangnhap+
								"   and tthd.TIENTE_FK = '100000' and ct.LOAIHD = '8' and tthd.PK_SEQ != "+this.id +" \n"+
								"   GROUP BY ct.HOADON_FK \n"+
								" )DATHANHTOAN on  DATA.PK_SEQ = DATHANHTOAN.HOADON_FK \n"+	
								" INNER JOIN \n"+
								" ( SELECT ct.HOADON_FK, SUM(ct.SOTIENTT) AS DATT \n"+
								"   FROM ERP_THANHTOANHOADON_HOADON ct inner join ERP_THANHTOANHOADON tthd on ct.THANHTOANHD_FK = tthd.PK_SEQ \n"+   
								"   where   tthd.TRANGTHAI != '2' AND tthd.KHACHHANG_FK = '"+this.khachhangId+"' and tthd.NPP_FK = " +this.nppdangnhap+
								"   and tthd.TIENTE_FK = '100000' and ct.LOAIHD = '8' and tthd.PK_SEQ = "+this.id +" \n"+
								"   GROUP BY ct.HOADON_FK \n"+
								" )TICHLUY on  DATA.PK_SEQ = TICHLUY.HOADON_FK \n"+								
								" where ( giatritra - tonggiatri - isnull(DATHANHTOAN.DATT,0)) > 0  \n";
							
						// TÍCH LŨY KHÔNG CẦN DUYỆT
						query += " UNION ALL \n"+
						 		 " SELECT distinct 9 as LOAIHD, PK_SEQ, DATA.SCHEME SOHOADON, DATA.SCHEME AS KYHIEU , DATA.ngaykyhd ngayhoadon, giatritra as sotiengoc, \n"+
						 		 "		  (giatritra - tonggiatri - isnull(DATHANHTOAN.DATT,0)) as SOTIENAVAT, TICHLUY.DATT as sotientt, '' as POID, \n"+
						 		 " 		  100000 as ttId, 1 as TIGIA, ngayketthuchd ngaydenhantt, sohopdong SOHOPDONG, '' SOHOPDONGNGOAI \n"+
						 		 " FROM \n"+  
						 		 "	(  \n"+
								 "	SELECT 	DT.DKKMTICHLUY_FK PK_SEQ, DT.doanhsoDAT, tc.donvi as donvitra, DT.sohopdong, DT.ngaykyhd, DT.ngayketthuchd, DT.SCHEME, \n"+
								 "			( case tc.donvi when 0 then round( tc.chietkhau * DT.doanhsoDAT / 100.0, 0 ) when 1 then tc.chietkhau else 0 end ) as giatritra, \n"+
								 "			ISNULL( ( select SUM(TONGGIATRI) from ERP_DONDATHANGNPP_TICHLUY_TRATL where DKKMID = DT.DKKMTICHLUY_FK and DONDATHANGID in ( select PK_SEQ from ERP_DONDATHANGNPP where KHACHHANG_FK = '"+this.khachhangId+"' and trangthai != 3 ) ) , 0) as tonggiatri \n"+ 
								 "	FROM \n"+
								 "	( \n"+
								 "		SELECT	DKKMTICHLUY_FK, c.PK_SEQ,  \n"+
								 "				ISNULL ( ( \n"+
								 "				select SUM( dh_sp.soluong * round( ( dongiaGOC * ( 1 + thueVAT / 100.0 ) ), 0 ) ) as tonggiatri \n"+
								 "				from ERP_DONDATHANGNPP dh inner join ERP_DONDATHANGNPP_SANPHAM dh_sp on dh.PK_SEQ = dh_sp.dondathang_fk  \n"+
								 "				where dh.KHACHHANG_FK = a.KHACHHANG_FK and dh.CS_DUYET = '1' and dh.TRANGTHAI not in ( 0, 3 ) and dh.NgayDonHang >= c.ngayds_tungay and dh.NgayDonHang <= c.ngayds_denngay \n"+
								 "		 				and dh_sp.sanpham_fk in ( select sanpham_fk from TIEUCHITHUONGTL_SANPHAM where thuongtl_fk = c.PK_SEQ ) \n"+
								 "				), 0 ) doanhsoDAT , a.sohopdong,a.ngaykyhd, a.ngayketthuchd , c.SCHEME \n"+
								 "		from DANGKYKM_TICHLUY_KHACHHANG a inner join DANGKYKM_TICHLUY b on a.DKKMTICHLUY_FK = b.PK_SEQ \n"+
								 "		inner join TIEUCHITHUONGTL c on b.TIEUCHITL_FK = c.PK_SEQ \n"+
								 "		where KHACHHANG_FK = '"+this.khachhangId+"' and c.TRANGTHAI = '1' and c.khongcanduyettra = '1'  \n"+ 
								 "	) \n"+
								 "	DT inner join TIEUCHITHUONGTL_TIEUCHI tc on DT.PK_SEQ = tc.thuongtl_fk \n"+
								 "	where tc.tumuc <= DT.doanhsoDAT and DT.doanhsoDAT <= tc.denmuc and tc.donvi != 2 \n"+
								 " )  \n"+
								 " DATA  \n"+
								 " LEFT JOIN \n"+
								 " ( SELECT ct.HOADON_FK, SUM(ct.SOTIENTT) AS DATT \n"+
								 "   FROM ERP_THANHTOANHOADON_HOADON ct inner join ERP_THANHTOANHOADON tthd on ct.THANHTOANHD_FK = tthd.PK_SEQ \n"+   
								 "   where   tthd.TRANGTHAI != '2' AND tthd.KHACHHANG_FK = '"+this.khachhangId+"' and tthd.NPP_FK = " +this.nppdangnhap+
								 "   and tthd.TIENTE_FK = '100000' and ct.LOAIHD = '9' and tthd.PK_SEQ != "+this.id +" \n"+
								 "   GROUP BY ct.HOADON_FK \n"+
								 " )DATHANHTOAN on  DATA.PK_SEQ = DATHANHTOAN.HOADON_FK \n"+	
								 " INNER JOIN \n"+
								 " ( SELECT ct.HOADON_FK, SUM(ct.SOTIENTT) AS DATT \n"+
								 "   FROM ERP_THANHTOANHOADON_HOADON ct inner join ERP_THANHTOANHOADON tthd on ct.THANHTOANHD_FK = tthd.PK_SEQ \n"+   
								 "   where   tthd.TRANGTHAI != '2' AND tthd.KHACHHANG_FK = '"+this.khachhangId+"' and tthd.NPP_FK = " +this.nppdangnhap+
								 "   and tthd.TIENTE_FK = '100000' and ct.LOAIHD = '9' and tthd.PK_SEQ = "+this.id +" \n"+
								 "   GROUP BY ct.HOADON_FK \n"+
								 " )TICHLUY on  DATA.PK_SEQ = TICHLUY.HOADON_FK \n"+								
								 " where ( giatritra - tonggiatri - isnull(DATHANHTOAN.DATT,0)) > 0  \n";
								
							
						query += " UNION ALL \n";
						}
						
						query += // TÍCH LŨY CẦN DUYỆT
								" SELECT distinct 8 as LOAIHD,PK_SEQ , SCHEME sohoadon, SCHEME AS KYHIEU, ngaykyhd ngayhoadon, tonggiatri as sotiengoc, \n"+
								" 		(giatritra - tonggiatri - isnull(DATHANHTOAN.DATT,0)) as SOTIENAVAT , 0 as sotientt, '' as POID, \n"+
								"        100000 as ttId, 1 as TIGIA, ngayketthuchd ngaydenhantt, sohopdong SOHOPDONG, '' SOHOPDONGNGOAI \n"+
								" FROM \n"+ 
								" ( \n"+
								"	SELECT DKKMTICHLUY_FK PK_SEQ, c.SCHEME , doanhsoDAT, donvitra, sanphamtra, soluongtra, giatritra, a.ngaykyhd, a.ngayketthuchd, \n"+
								"		   a.sohopdong, \n"+
								"		   ISNULL( ( SELECT SUM(TONGGIATRI)  FROM ERP_DONDATHANGNPP_TICHLUY_TRATL \n"+
								"				  	 WHERE DKKMID = a.DKKMTICHLUY_FK \n"+
								"				  	 AND DONDATHANGID in ( SELECT PK_SEQ FROM ERP_DONDATHANGNPP  \n"+
								"										   WHERE KHACHHANG_FK = '"+this.khachhangId+"' and trangthai != 3 ) ) , 0) as tonggiatri \n"+
								"	FROM DANGKYKM_TICHLUY_KHACHHANG a \n"+
								"		INNER JOIN DANGKYKM_TICHLUY b on a.DKKMTICHLUY_FK = b.PK_SEQ \n"+
								"		INNER join TIEUCHITHUONGTL c on b.TIEUCHITL_FK = c.PK_SEQ \n"+		
								"	WHERE KHACHHANG_FK = '"+this.khachhangId+"' and DUYET = '1' and MucDat is not null \n"+
								"		and c.TRANGTHAI = '1' and c.khongcanduyettra = '0'  \n"+
								"		and donvitra != 2 \n"+
								" ) \n"+
								" DATA \n"+
								" LEFT JOIN \n"+
								" ( SELECT ct.HOADON_FK, SUM(ct.SOTIENTT) AS DATT \n"+
								"   FROM ERP_THANHTOANHOADON_HOADON ct inner join ERP_THANHTOANHOADON tthd on ct.THANHTOANHD_FK = tthd.PK_SEQ \n"+   
								"   where   tthd.TRANGTHAI != '2' AND tthd.KHACHHANG_FK = '"+this.khachhangId+"' and tthd.NPP_FK = " +this.nppdangnhap+
								"   and tthd.TIENTE_FK = '100000' and ct.LOAIHD = '8' and tthd.PK_SEQ != "+(this.id.trim().length() <=0 ? "0": this.id )+" \n"+
								"   GROUP BY ct.HOADON_FK \n"+
								" )DATHANHTOAN on  DATA.PK_SEQ = DATHANHTOAN.HOADON_FK \n"+
								" where ( giatritra - tonggiatri - isnull(DATHANHTOAN.DATT,0)) > 0 \n ";
						
								if(this.id.trim().length()>0)
								{
									query += " and DATA.PK_SEQ NOT IN (SELECT ct.HOADON_FK FROM ERP_THANHTOANHOADON_HOADON ct inner join ERP_THANHTOANHOADON tthd on ct.THANHTOANHD_FK = tthd.PK_SEQ " +
											 "						   WHERE tthd.TRANGTHAI != '2' AND tthd.KHACHHANG_FK = '"+this.khachhangId+"' and tthd.NPP_FK = " +this.nppdangnhap+ " \n" +
											 "						   and tthd.TIENTE_FK = '100000' and ct.LOAIHD = '8' and tthd.PK_SEQ = "+ this.id +" ) \n";
								}
							
						// TÍCH LŨY KHÔNG CẦN DUYỆT
						query += " UNION ALL \n"+
						 		 " SELECT distinct 9 as LOAIHD, PK_SEQ, DATA.SCHEME SOHOADON, DATA.SCHEME AS KYHIEU , DATA.ngaykyhd ngayhoadon, giatritra as sotiengoc, \n"+
						 		 "		  (giatritra - tonggiatri - isnull(DATHANHTOAN.DATT,0)) as SOTIENAVAT, 0 as sotientt, '' as POID, \n"+
						 		 " 		  100000 as ttId, 1 as TIGIA, ngayketthuchd ngaydenhantt, sohopdong SOHOPDONG, '' SOHOPDONGNGOAI \n"+
						 		 " FROM \n"+  
						 		 "	(  \n"+
								 "	SELECT 	DT.DKKMTICHLUY_FK PK_SEQ, DT.doanhsoDAT, tc.donvi as donvitra, DT.sohopdong, DT.ngaykyhd, DT.ngayketthuchd, DT.SCHEME, \n"+
								 "			( case tc.donvi when 0 then round( tc.chietkhau * DT.doanhsoDAT / 100.0, 0 ) when 1 then tc.chietkhau else 0 end ) as giatritra, \n"+
								 "			ISNULL( ( select SUM(TONGGIATRI) from ERP_DONDATHANGNPP_TICHLUY_TRATL where DKKMID = DT.DKKMTICHLUY_FK and DONDATHANGID in ( select PK_SEQ from ERP_DONDATHANGNPP where KHACHHANG_FK = '"+this.khachhangId+"' and trangthai != 3 ) ) , 0) as tonggiatri \n"+ 
								 "	FROM \n"+
								 "	( \n"+
								 "		SELECT	DKKMTICHLUY_FK, c.PK_SEQ,  \n"+
								 "				ISNULL ( ( \n"+
								 "				select SUM( dh_sp.soluong * round( ( dongiaGOC * ( 1 + thueVAT / 100.0 ) ), 0 ) ) as tonggiatri \n"+
								 "				from ERP_DONDATHANGNPP dh inner join ERP_DONDATHANGNPP_SANPHAM dh_sp on dh.PK_SEQ = dh_sp.dondathang_fk  \n"+
								 "				where dh.KHACHHANG_FK = a.KHACHHANG_FK and dh.CS_DUYET = '1' and dh.TRANGTHAI not in ( 0, 3 ) and dh.NgayDonHang >= c.ngayds_tungay and dh.NgayDonHang <= c.ngayds_denngay \n"+
								 "		 				and dh_sp.sanpham_fk in ( select sanpham_fk from TIEUCHITHUONGTL_SANPHAM where thuongtl_fk = c.PK_SEQ ) \n"+
								 "				), 0 ) doanhsoDAT , a.sohopdong,a.ngaykyhd, a.ngayketthuchd , c.SCHEME \n"+
								 "		from DANGKYKM_TICHLUY_KHACHHANG a inner join DANGKYKM_TICHLUY b on a.DKKMTICHLUY_FK = b.PK_SEQ \n"+
								 "		inner join TIEUCHITHUONGTL c on b.TIEUCHITL_FK = c.PK_SEQ \n"+
								 "		where KHACHHANG_FK = '"+this.khachhangId+"' and c.TRANGTHAI = '1' and c.khongcanduyettra = '1' and b.daduyet = '1' and b.TRANGTHAI = '1' \n"+ 
								 "	) \n"+
								 "	DT inner join TIEUCHITHUONGTL_TIEUCHI tc on DT.PK_SEQ = tc.thuongtl_fk \n"+
								 "	where tc.tumuc <= DT.doanhsoDAT and DT.doanhsoDAT <= tc.denmuc and tc.donvi != 2 \n"+
								 " )  \n"+
								 " DATA  \n"+
								 " LEFT JOIN \n"+
								 " ( SELECT ct.HOADON_FK, SUM(ct.SOTIENTT) AS DATT \n"+
								 "  FROM ERP_THANHTOANHOADON_HOADON ct inner join ERP_THANHTOANHOADON tthd on ct.THANHTOANHD_FK = tthd.PK_SEQ \n"+
								 "  where   tthd.TRANGTHAI != '2' AND tthd.KHACHHANG_FK = '"+this.khachhangId+"' and tthd.NPP_FK = "+this.nppdangnhap+"  \n" +
								 " 	and tthd.TIENTE_FK = '100000' and ct.LOAIHD = '9' and tthd.PK_SEQ != "+(this.id.trim().length() <=0 ? "0": this.id )+" \n"+
								 "  GROUP BY ct.HOADON_FK \n"+
								 " )DATHANHTOAN on  DATA.PK_SEQ = DATHANHTOAN.HOADON_FK  \n"+
								 " WHERE  (giatritra - tonggiatri - isnull(DATHANHTOAN.DATT,0)) >0  ";
								 if(this.id.trim().length()>0)
								{
									query += " and DATA.PK_SEQ NOT IN (SELECT ct.HOADON_FK FROM ERP_THANHTOANHOADON_HOADON ct inner join ERP_THANHTOANHOADON tthd on ct.THANHTOANHD_FK = tthd.PK_SEQ " +
											 "						   WHERE tthd.TRANGTHAI != '2' AND tthd.KHACHHANG_FK = '"+this.khachhangId+"' and tthd.NPP_FK = " +this.nppdangnhap+ " \n" +
											 "						   and tthd.TIENTE_FK = '100000' and ct.LOAIHD = '9' and tthd.PK_SEQ = "+ this.id +" ) \n";
								}
						
					}
				}
				
				
				System.out.println("Query khoi tao hoa don 111112: " + query);
				
				ResultSet rsHoadon = db.get(query);
				List<IHoadon> hdList = new ArrayList<IHoadon>();
				if(rsHoadon != null)
				{
					try 
					{
						IHoadon hd = null;
						while(rsHoadon.next())
						{						
							String doituong = "";
							String doituongId = "";
							String madoituong ="";
							
							String id = rsHoadon.getString("pk_seq");
							String kyhieu = rsHoadon.getString("kyhieu");
							String sohoadon = rsHoadon.getString("sohoadon");
							String ngayhd = rsHoadon.getString("ngayhoadon");
							String ngaydenhantt = rsHoadon.getString("ngaydenhantt");
							String tienteId = rsHoadon.getString("TTID");
							String sotiengoc = "";
							String avat = "";
							String loaihd = rsHoadon.getString("LOAIHD");
							String sohopdong = rsHoadon.getString("SOHOPDONG");
							String sohopdongngoai = rsHoadon.getString("sohopdongngoai");
							
							if(tienteId.equals("100000")){
								avat = ("" + rsHoadon.getDouble("sotienAVAT")).replaceAll(",", "");
							}else{
								avat = ("" + rsHoadon.getDouble("sotienAVAT")*rsHoadon.getDouble("TIGIA")).replaceAll(",", "");
							}
							
							if(tienteId.equals("100000")){
								sotiengoc = ("" + rsHoadon.getDouble("sotiengoc")).replaceAll(",", "");
							}else{
								sotiengoc = ("" + rsHoadon.getDouble("sotiengoc")*rsHoadon.getDouble("TIGIA")).replaceAll(",", "");
							}
							
							String sotienNT = ("" + rsHoadon.getDouble("sotienAVAT")).replaceAll(",", "");							
							String dathanhtoan = ("" + rsHoadon.getDouble("SOTIENTT")).replaceAll(",", "");
							String tigia = rsHoadon.getString("TIGIA").replaceAll(",", "");
							
							if(this.bophanId.trim().length() > 0)
							{
								doituong = rsHoadon.getString("DOITUONG");
								doituongId = rsHoadon.getString("DOITUONG_FK");
								madoituong = rsHoadon.getString("MADOITUONG");
							}
							
							
							hd = new Hoadon(id, kyhieu, sohoadon, ngayhd, avat, sotienNT, dathanhtoan,  tienteId);
							hd.setSoTienGoc2(sotiengoc);
							hd.setSoPO(rsHoadon.getString("POID"));
							hd.setTigia(tigia);
							hd.setNgaydenhanTT(ngaydenhantt);
							hd.setLoaihd1(loaihd);
							hd.setSohopdong(sohopdong);
							hd.setSohopdongNGOAI(sohopdongngoai);
							
							if(this.bophanId.trim().length() > 0)
							{
							  hd.setDoituong(doituong);
							  hd.setDoituongId(doituongId);
							  hd.setMadoituong(madoituong);
							}
							
							hdList.add(hd);
						}
						rsHoadon.close();
					} 
					catch (Exception e) { 
						System.out.println("Error Here : "+e.toString());
					}
				}
				this.hoadonList = hdList;
				System.out.println("So hoa don: " + this.hoadonList.size());
			}
			
			
			if( (this.nhomNCCCNId.length() > 1 ) && this.htttId.length() > 0 && this.hoadonList.size() <= 0)
			{
				query ="";
				if(this.LoaiThanhToan.equals("1")) // HÓA ĐƠN
				{
					if(this.id.length() > 0)
					{
					query += " select p.ncc_fk , b.pk_seq, b.kyhieu, b.sohoadon, b.ngayhoadon, b.sotienAVAT - isnull(thanh_toan.tongthanhtoan, '0') as sotienAVAT, SOTIENTT, " +
								" '' as POID " +
							"from ERP_THANHTOANHOADON_HOADON a " +
							"inner join ERP_HOADONNCC b on a.hoadon_fk = b.pk_seq " +
							"    inner join ERP_PARK p on b.park_fk = p.pk_seq  " + // them join voi ERP_PARK de lay ma ncc
								"left join	" +
								"( select TTHD.hoadon_fk, sum(TTHD.SOTIENTT) as tongthanhtoan " +
								"from ERP_THANHTOANHOADON_HOADON TTHD inner join ERP_THANHTOANHOADON TT on TTHD.THANHTOANHD_FK = TT.PK_SEQ " +
								"where TT.trangthai !=2 and TTHD.thanhtoanhd_fk != '" + this.id + "' " +  //sua trang thai !=2 thanh =1
										" and TTHD.hoadon_fk in (select hoadon_fk " +
														  "from ERP_THANHTOANHOADON_HOADON " +
														  "where thanhtoanhd_fk = '" + this.id + "') " +
								"group by hoadon_fk) thanh_toan on a.hoadon_fk = thanh_toan.hoadon_fk " +
								"where a.thanhtoanhd_fk = '" + this.id + "' " +
							" union all ";
					}
					
					query += "(select hoadon.ncc_fk , hoadon.pk_seq, hoadon.kyhieu, hoadon.sohoadon, hoadon.ngayhoadon, " +
							" hoadon.sotienAVAT - isnull(dathanhtoan.DATHANHTOAN, '0') as sotienAVAT, 0 as sotientt ," +
								" ''   as POID " +
							"from ( " +
									"select b.ncc_fk , a.pk_seq, a.kyhieu, a.sohoadon, a.ngayhoadon, a.sotienAVAT " +
									"from ERP_HOADONNCC a inner join ERP_PARK b on a.park_fk = b.pk_seq " +
									"where b.ncc_fk in ( select NCC_FK from NHOMNHACUNGCAPCN_NCC where NHOMNHACUNGCAPCN_FK = '"+ this.nhomNCCCNId  +"')" +
									" and b.trangthai = '2' and a.trangthai = '0' ";
								if(this.id.length() > 0)
								{
									query += "and a.pk_seq not in (select hoadon_fk from ERP_THANHTOANHOADON_HOADON where thanhtoanhd_fk = '" + this.id + "') ";
								}
						query += ") hoadon " +
							"left join ( " +
									"select TTHD.HOADON_FK , sum(TTHD.SOTIENTT) as DATHANHTOAN  " +
									"from ERP_THANHTOANHOADON_HOADON TTHD inner join ERP_THANHTOANHOADON TT on TTHD.THANHTOANHD_FK = TT.PK_SEQ " +
									"where TT.TRANGTHAI !=2 and TTHD.HOADON_FK in (select pk_seq from ERP_HOADONNCC where trangthai = 0)  " + //sua trang thai !=2 thanh =1
									"group by HOADON_FK " +
									") dathanhtoan " +
							"on hoadon.pk_seq = dathanhtoan.hoadon_fk " +
							"where hoadon.sotienAVAT - isnull(dathanhtoan.DATHANHTOAN, '0') > 0 " +
							" and hoadon.pk_seq not in ( "+
							"	select distinct td.HOADON_FK from ERP_THANHTOANHOADON_HOADON td "+
							"	inner join ERP_THANHTOANHOADON t on t.PK_SEQ = td.THANHTOANHD_FK "+
							"	where t.TRANGTHAI =0) )	";
				}
				System.out.println("Query khoi tao hoa don: " + query);
				
				ResultSet rsHoadon = db.get(query);
				List<IHoadon> hdList = new ArrayList<IHoadon>();
				if(rsHoadon != null)
				{
					try 
					{
						IHoadon hd = null;
						while(rsHoadon.next())
						{
							String ncc_fk =  rsHoadon.getString("ncc_fk");
							String id = rsHoadon.getString("pk_seq");
							String kyhieu = rsHoadon.getString("kyhieu");
							String sohoadon = rsHoadon.getString("sohoadon");
							String ngayhd = rsHoadon.getString("ngayhoadon");
							String avat = formatter.format(rsHoadon.getDouble("sotienAVAT"));
							//System.out.println("Query sohoadon: " + sohoadon);
							String dathanhtoan ="";
							if(this.id.length() > 0)
							{
								dathanhtoan = formatter.format(rsHoadon.getDouble("SOTIENTT"));
							}
							
							hd = new Hoadon(ncc_fk, id, kyhieu, sohoadon, ngayhd, avat, "0", dathanhtoan, "" , "");
							hd.setSoPO(rsHoadon.getString("POID"));
							hdList.add(hd);
						}
						rsHoadon.close();
					} 
					catch (Exception e) { 
						System.out.println("Error Here : "+e.toString());
					}
				}
				this.hoadonList = hdList;
				System.out.println("So hoa don: " + this.hoadonList.size());
				
			}
		}
		
		
		
	
	}
	
	public void initPdf() 
	{
		String query = 
			" select a.pk_seq, a.ngayghinhan, " +
			"   case when a.NCC_FK IS not null then ncc.TEN when a.NHANVIEN_FK IS not null then nv.TEN	else '' end AS nguoinhan, " +
			" 	case when a.NCC_FK IS not null then ncc.diachi else '' end AS diachi,  " +
			"   a.httt_fk, a.nganhang_fk, a.chinhanh_fk, a.sotaikhoan, a.noidungtt, a.sotientt, " +
			"	a.ghichu, a.loaithanhtoan, a.sotienHD, a.sotienHDNT, a.phinganhang, a.phinganhangNT, " +
			"   a.vat, a.vatNT, a.sotienttNT, a.chenhlechVND, trichphi, sotaikhoan_tp, nganhang_tp_fk, chinhanh_tp_fk, " +
			"   PNH.mahoadon, PNH.mauhoadon, PNH.KYHIEU, PNH.SOHOADON, PNH.NGAYHOADON, PNH.TENNCC, PNH.MST, PNH.TIENHANG, PNH.THUESUAT, " +
			"   PNH.TIENTHUE, PNH.NGANHANG_FK AS NGANHANG_PNH_FK, PNH.CHINHANH_FK AS CHINHANH_PNH_FK " +
			" from ERP_THANHTOANHOADON a " +
			" left join ERP_THANHTOANHOADON_PHINGANHANG PNH on PNH.THANHTOANHD_FK = a.PK_SEQ " +
			" left join ERP_NHACUNGCAP ncc on a.NCC_FK = ncc.PK_SEQ " +
			" left join ERP_NHANVIEN nv on a.NHANVIEN_FK = nv.PK_SEQ " +
			" where a.pk_seq = '" + this.id + "'";
		System.out.println("[ErpThanhtoanhoadon.initPdf] query = " + query);
		ResultSet rs = db.get(query);
		if(rs != null)
		{
			try 
			{
				while(rs.next())
				{
					this.ngayghinhan = rs.getString("ngayghinhan");
					this.nccId = rs.getString("nguoinhan");
					this.diachi = rs.getString("diachi");
					this.htttId = rs.getString("httt_fk");
					if(this.htttId.equals("100001"))
					{
						this.nganhangId = rs.getString("nganhang_fk");
						this.chinhanhId = rs.getString("chinhanh_fk");
						this.sotaikhoan = rs.getString("sotaikhoan");
					}
					this.noidungtt = rs.getString("noidungtt");
					this.sotienttNT = rs.getString("sotienttNT").replaceAll(",", "");
					this.sotientt = rs.getString("sotientt").replaceAll(",", "");
					this.LoaiThanhToan = rs.getString("loaithanhtoan");
					this.sotienHD  = rs.getString("sotienHD").replaceAll(",", "");
					this.sotienHDNT = rs.getString("sotienHDNT").replaceAll(",", "");
					this.pnganhang = rs.getString("phinganhang").replaceAll(",", "");
					this.pnganhangNT = rs.getString("phinganhangNT").replaceAll(",", "");
					this.thueVAT = rs.getString("vat").replaceAll(",", "");
					this.thueVATNT = rs.getString("vatNT").replaceAll(",", "");
					this.chenhlechVND = rs.getString("chenhlechVND").replaceAll(",", "");
					this.trichphi = rs.getString("trichphi");
					this.sotaikhoan_tp = rs.getString("sotaikhoan_tp");
					this.nganhang_tpId = rs.getString("nganhang_tp_fk");
					this.chinhanh_tpId = rs.getString("chinhanh_tp_fk");
					this.mahoadon = rs.getString("mahoadon");
					this.mauhoadon = rs.getString("mauhoadon");
					this.kyhieu = rs.getString("KYHIEU");
					this.sohoadon = rs.getString("SOHOADON");
					this.ngayhoadon = rs.getString("NGAYHOADON");
					this.tenNCC = rs.getString("TENNCC");
					this.mst = rs.getString("MST");
					this.tienhang = rs.getString("TIENHANG").replaceAll(",", "");
					this.thuesuat = rs.getString("THUESUAT").replaceAll(",", "");
					this.tienthue = rs.getString("TIENTHUE").replaceAll(",", "");
					this.nhId_VAT = rs.getString("NGANHANG_PNH_FK");
					this.cnId_VAT = rs.getString("CHINHANH_PNH_FK");
				}
			} 
			catch (SQLException e) 
			{
				System.out.println("Exception: " + e.getMessage());
			}
		}
		
		//khoi tao hoa don
		query = "select sohoadon from erp_thanhtoanhoadon_hoadon a inner join ERP_HOADONNCC b on a.hoadon_fk = b.pk_seq where thanhtoanhd_fk = '" + this.id + "'";
		String sohoadon = "";
		ResultSet hoadonRs = db.get(query);
		if(hoadonRs != null)
		{
			try
			{
				while(hoadonRs.next())
				{
					sohoadon += hoadonRs.getString("sohoadon") + ", ";
				}
				hoadonRs.close();
			} 
			catch (SQLException e) {}
		}
		
		if(sohoadon.length() > 0)
			this.noidungtt += " --- " + sohoadon.substring(0, sohoadon.length() - 2);
		
	}

	public void initUnc() 
	{
		NumberFormat formatter = new DecimalFormat("#,###,###"); 
		String query = "select a.pk_seq, a.ngayghinhan, b.ten as nccTen, b.diachi, a.httt_fk, c.ten as nganhang_fk, a.chinhanh_fk, a.sotaikhoan, a.noidungtt, a.sotientt " +
						"from ERP_THANHTOANHOADON a inner join ERP_NHACUNGCAP b on a.ncc_fk = b.pk_seq left join erp_nganhang c on a.nganhang_fk = c.pk_seq" +
						" where a.pk_seq = '" + this.id + "'";
		System.out.println("Khoi tao Unc: " + query);
		ResultSet rs = db.get(query);
		if(rs != null)
		{
			try 
			{
				while(rs.next())
				{
					this.ngayghinhan = rs.getString("ngayghinhan");
					this.nccId = rs.getString("nccTen") + " --- " + rs.getString("diachi");
					this.htttId = rs.getString("httt_fk");
					if(this.htttId.equals("100001"))
					{
						this.nganhangId = rs.getString("nganhang_fk");
						this.chinhanhId = rs.getString("chinhanh_fk");
						this.sotaikhoan = rs.getString("sotaikhoan");
					}
					this.noidungtt = rs.getString("noidungtt");
					this.sotientt = formatter.format(rs.getDouble("sotientt"));
				}
				rs.close();
			} 
			catch (SQLException e) 
			{
				System.out.println("Exception: " + e.getMessage());
			}
		}
		
	}
	
	public void createRs() 
	{		
		this.getNppInfo();
		
		this.sotkRs = db.get("SELECT NH_CTY.SOTAIKHOAN, NH_CTY.SOTAIKHOAN + ' - ' + NH.TEN + ' - ' + CN.TEN + ', ' + TT.MA  AS TAIKHOAN " +
				 			 "FROM ERP_NGANHANG_CONGTY NH_CTY " +
				 			 "INNER JOIN ERP_TIENTE TT ON TT.PK_SEQ = NH_CTY.TIENTE_FK " + 
				 			 "INNER JOIN ERP_NGANHANG NH ON NH.PK_SEQ = NH_CTY.NGANHANG_FK " +
				 			 "INNER JOIN ERP_CHINHANH CN ON CN.PK_SEQ = NH_CTY.CHINHANH_FK " +
				 			 "WHERE NH_CTY.TRANGTHAI = 1 AND NH_CTY.NPP_FK = '" + this.nppdangnhap + "'  " );
      
		this.sotkRs_tp = db.get("SELECT NH_CTY.SOTAIKHOAN, NH_CTY.SOTAIKHOAN + ' - ' + NH.TEN + ' - ' + CN.TEN + ', ' + TT.MA  AS TAIKHOAN " +
	 			 				"FROM ERP_NGANHANG_CONGTY NH_CTY " +
	 			 				"INNER JOIN ERP_TIENTE TT ON TT.PK_SEQ = NH_CTY.TIENTE_FK " + 
	 			 				"INNER JOIN ERP_NGANHANG NH ON NH.PK_SEQ = NH_CTY.NGANHANG_FK " +
	 			 				"INNER JOIN ERP_CHINHANH CN ON CN.PK_SEQ = NH_CTY.CHINHANH_FK " +
	 			 				"WHERE NH_CTY.TRANGTHAI = 1 AND NH_CTY.NPP_FK = '" + this.nppdangnhap + "' AND TT.PK_SEQ = 100000 " );
    
		this.tienteRs = db.get("select pk_seq, ma + ', ' + ten as TEN from ERP_TIENTE ");
		

		this.nccRs = db.get("select pk_seq, ma + ', ' + ten as TEN from TraphacoERP.dbo.ERP_NHACUNGCAP where NPP_FK = 1 ");
		
		System.out.println("SELECT PK_SEQ ,MA+','+TEN AS TEN FROM ERP_NHANVIEN WHERE TRANGTHAI = 1 and NPP_FK = "+this.nppdangnhap+"");
		this.NhanvienRs = db.get("SELECT PK_SEQ ,MA+','+TEN AS TEN FROM ERP_NHANVIEN WHERE TRANGTHAI=1 and NPP_FK = "+this.nppdangnhap+"");
		this.nhomNCCRs = db.get("SELECT PK_SEQ, DIENGIAI AS TEN FROM TraphacoERP.dbo.NHOMNHACUNGCAPCN where TRANGTHAI = 1 " ) ;
		this.khachhangRs = db.get("select pk_seq, mafast + ', ' + ten as TEN from KhachHang where trangthai = '1' and NPP_FK = "+this.nppdangnhap+"");
		
		
		this.htttRs = db.get("select pk_seq, ma, ten from ERP_HINHTHUCTHANHTOAN where trangthai = '1'");
		
		
		this.nganhangRs = db.get("select pk_seq, ma + ', ' + ten as nhTen from erp_nganhang  ");
		 
		this.PhongBanRs = db.getScrol("select pk_seq, ten from ERP_DONVITHUCHIEN where trangthai = '1' ");
		
		this.KenhBhRs = db.getScrol("select pk_seq, diengiai ten from KENHBANHANG where trangthai = '1' ");
		
		
		String query1 = " SELECT PK_SEQ,SOHIEUTAIKHOAN as MA,TENTAIKHOAN AS TEN, ISNULL(COTTCHIPHI,0)COTTCHIPHI, " +
						" ISNULL(DUNGCHOKHO, 0) DUNGCHOKHO, ISNULL(DUNGCHONGANHANG, 0) DUNGCHONGANHANG, ISNULL(DUNGCHONCC, 0) DUNGCHONCC, ISNULL(DUNGCHOTAISAN, 0) DUNGCHOTAISAN, " +
						" ISNULL(DUNGCHOKHACHHANG, 0) DUNGCHOKHACHHANG, ISNULL(DUNGCHONHANVIEN, 0) DUNGCHONHANVIEN " +
						" FROM ERP_TAIKHOANKT WHERE NPP_FK ="+this.nppdangnhap;
		
		this.TaiKhoanKTRs=this.db.getScrol(query1);
		
		if(this.dungchos.trim().length() > 0) this.dungchos = this.dungchos.substring(0, this.dungchos.length()-1);
		
		if(!this.DoiTuongChiPhiKhac.equals("3") ) // neu khac loai  (KHAC) 
		{			
			
			if(  this.htttId.length() > 0 && this.hoadonList.size() <= 0)
			{
				NumberFormat formatter = new DecimalFormat("#,###,###");
				
				String query = "";
				// LOAD NHỮNG HD CỦA NHÀ CUNG CẤP
				if(this.nccId.trim().length() > 0) 
				{
					// LOAIHD: 0 - HOADONNCC
					if (this.id.length() > 0) {
						query +=" SELECT distinct 0 as LOAIHD, b.pk_seq, isnull(b.sohoadon, '') as sohoadon,  isnull(b.kyhieu, '') as kyhieu, b.ngayhoadon, \n" +
								"         b.sotienAVAT as sotiengoc, b.sotienAVAT - ISNULL(thanh_toan.tongthanhtoan,0) as sotienAVAT, SOTIENTT, \n" +
								" '' as POID, c.TIENTE_FK AS TTID, c.TIGIA, b.NGAYDENHANTT, '' SOHOPDONG, '' SOHOPDONGNGOAI \n"+
								"FROM ERP_THANHTOANHOADON_HOADON a \n" +
								"     inner join ERP_HOADONNCC b on a.hoadon_fk = b.pk_seq  and a.loaihd = 0  \n" +
								"	  inner join ERP_PARK c on b.park_fk = c.pk_seq \n" +
								"     left join	\n" +
								"   ( select TTHD.hoadon_fk, sum(TTHD.SOTIENTT) as tongthanhtoan \n" +
								"     from ERP_THANHTOANHOADON_HOADON TTHD inner join ERP_THANHTOANHOADON TT on TTHD.THANHTOANHD_FK = TT.PK_SEQ \n" +
								"     where TT.NPP_FK = "+this.nppdangnhap+"  and TTHD.LOAIHD = 0 AND TT.trangthai !=2 and TTHD.thanhtoanhd_fk != '" + this.id + "' \n" +  //sua trang thai !=2 thanh =1
								"           and TTHD.hoadon_fk in ( select hoadon_fk \n" +
						        "									from ERP_THANHTOANHOADON_HOADON \n" +
								"									where thanhtoanhd_fk = '" + this.id + "' and loaihd = 0 ) \n" +
								"     group by hoadon_fk) thanh_toan on a.hoadon_fk = thanh_toan.hoadon_fk \n" +
								"WHERE a.thanhtoanhd_fk = '" + this.id + "' and c.TIENTE_FK = " + this.tienteId + " and b.NPP_FK = "+this.nppdangnhap+" \n" +
								
								" UNION ALL \n";
				}

					query +="(SELECT distinct 0 as LOAIHD, hoadon.pk_seq, isnull(hoadon.sohoadon, '') as sohoadon, isnull(hoadon.kyhieu, '') as kyhieu, hoadon.ngayhoadon, \n" +
							"         hoadon.sotienAVAT as sotiengoc, hoadon.sotienAVAT - isnull(dathanhtoan.DATHANHTOAN, '0') - isnull(dathanhtoanNCC.DATHANHTOAN, '0')  as sotienAVAT, 0 as sotientt ,\n " +
							" '' as POID, hoadon.ttId, hoadon.tigia,  hoadon.ngaydenhantt, isnull(hoadon.SOHOPDONG,'') SOHOPDONG, isnull(hoadon.SOHOPDONGNGOAI, '') SOHOPDONGNGOAI \n" +
							" FROM ( \n" +
							"       SELECT a.pk_seq, a.kyhieu, a.sohoadon, a.ngayhoadon, a.sotienAVAT, b.TIENTE_FK as ttId, b.TIGIA, a.SOTIENVIET, isnull(a.ngaydenhantt,'') as  ngaydenhantt, '' SOHOPDONG, '' SOHOPDONGNGOAI \n"+
							"       FROM ERP_HOADONNCC a inner join ERP_PARK b on a.park_fk = b.pk_seq \n" +
							"       WHERE  a.NPP_FK = "+this.nppdangnhap+" and b.ncc_fk = '" + this.nccId + "' and b.trangthai = '2' and a.trangthai = '0' \n";
							if(this.id.length() > 0)
							{
								query += " and a.pk_seq not in (select hoadon_fk from ERP_THANHTOANHOADON_HOADON where thanhtoanhd_fk = '" + this.id + "') \n";
							}
						query += " ) hoadon \n" +
							"      left join " +
							"   ( \n" +
							"     SELECT TTHD.HOADON_FK , sum(TTHD.SOTIENTT) as DATHANHTOAN  \n" +
							"     from ERP_THANHTOANHOADON_HOADON TTHD inner join ERP_THANHTOANHOADON TT on TTHD.THANHTOANHD_FK = TT.PK_SEQ \n" +
							"     where  TT.NPP_FK = "+this.nppdangnhap+" AND TT.NCC_FK is not null AND TTHD.LOAIHD = 0 AND TT.TRANGTHAI !=2 and TTHD.HOADON_FK in (select pk_seq from ERP_HOADONNCC where trangthai = 0)  \n" + //sua trang thai !=2 thanh =1
							"     group by HOADON_FK \n" +
							"    ) dathanhtoan  on hoadon.pk_seq = dathanhtoan.hoadon_fk \n" +
							"      left join " +
							"   ( \n" +
							"     select TTHD.HOADON_FK , sum(TTHD.SOTIENTT) as DATHANHTOAN  \n" +
							"     from ERP_THANHTOANHOADONNCC_HOADON TTHD inner join ERP_THANHTOANHOADONNCC TT on TTHD.THANHTOANHD_FK = TT.PK_SEQ \n" +
							"     where TT.npp_fk = "+this.nppdangnhap+" AND TTHD.LOAIHD = 0 AND TT.TRANGTHAI !=2 and TTHD.HOADON_FK in (select pk_seq from ERP_HOADONNCC where trangthai = 0)  \n" + //sua trang thai !=2 thanh =1
							"     group by HOADON_FK \n" +
							"    ) dathanhtoanNCC  on hoadon.pk_seq = dathanhtoanNCC.hoadon_fk \n" +
							"where hoadon.sotienAVAT - isnull(dathanhtoan.DATHANHTOAN, '0') - isnull(dathanhtoanNCC.DATHANHTOAN, '0')  > 0 and hoadon.ttId = " + this.tienteId + " ) \n" ;

					// LOAIHD: 1 - TAMUNG(NCC)
					if (this.id.length() > 0) {
						query += "UNION ALL \n";

						query += "SELECT distinct 1 as LOAIHD, TU.PK_SEQ , CAST(TU.PK_SEQ as nvarchar(50)) as sohoadon ,N'TẠM ỨNG'  AS KYHIEU ,TU.NGAYTAMUNG AS ngayhoadon, SOTIENTAMUNG as sotiengoc, SOTIENTAMUNG \n"+
								 "             -(SELECT ISNULL(SUM(b.SOTIENTT),0) \n"+
								 "               FROM ERP_THANHTOANHOADON_HOADON B INNER JOIN ERP_THANHTOANHOADON A ON A.PK_SEQ=B.THANHTOANHD_FK  \n"+
								 "               WHERE A.NCC_FK = " + (this.nccId == "" ? "0" : this.nccId) + " \n"+
								 " AND B.LOAIHD = 1 AND A.TRANGTHAI <>2 and a.pk_seq <> " + this.id +" \n"+
								 "  AND B.HOADON_FK=TU.PK_SEQ \n" + 
								 "					  AND A.NPP_FK = "+ this.nppdangnhap + "            ) -(SELECT ISNULL(sum(TTHD.SOTIENTT),0) as DATHANHTOAN  \n"+
								 "     	       FROM ERP_THANHTOANHOADONNCC_HOADON TTHD inner join ERP_THANHTOANHOADONNCC TT on TTHD.THANHTOANHD_FK = TT.PK_SEQ \n"+
								 "     	       WHERE TT.NCC_FK = " + (this.nccId == "" ? "0" : this.nccId) +" \n"+
								 " and TTHD.LOAIHD = 1 AND TT.TRANGTHAI = 0   \n"+
								 "					 AND TT.NPP_FK = " + this.nppdangnhap +" \n"+
								 "    	       )  AS sotienAVAT , \n"+
								 "             0 AS SOTIENTT, '' as POID, TU.TIENTE_FK as TTID, "+ this.tigia.replace(",", "") + " AS TIGIA, \n"+
								 "             isnull(TU.ngaydenhantt,'') as ngaydenhantt , '' SOHOPDONG, '' SOHOPDONGNGOAI \n"+
								 "      FROM ERP_TAMUNG TU \n"+
								 "      WHERE  TU.NPP_FK = " + this.nppdangnhap + " \n"+
								 "  	AND  ISNULL(TU.HOANTAT,'0')='0' and TU.TIENTE_FK = (select tiente_fk from ERP_THANHTOANHOADON where pk_seq="+ this.id + " ) \n"+
								 "            and TU.pk_seq not in  \n"+
								 "           (select HOADON_FK  from ERP_THANHTOANHOADON a inner join ERP_THANHTOANHOADON_HOADON b on a.pk_seq=b.thanhtoanhd_fk \n"+
								 "            where b.LOAIHD = 1 AND  b.thanhtoanhd_fk =" + this.id + "  )  \n"+
								 "           and  (SOTIENTAMUNG \n"+
								 "              -( SELECT ISNULL(SUM(b.SOTIENTT),0) \n"+
								 "                 FROM ERP_THANHTOANHOADON_HOADON B INNER JOIN ERP_THANHTOANHOADON A ON A.PK_SEQ=B.THANHTOANHD_FK  \n"+
								 "                 WHERE A.NCC_FK = " + (this.nccId == "" ? "0" : this.nccId) + " \n"+
								 " 				AND b.LOAIHD = 1 AND A.TRANGTHAI <>2 and a.pk_seq <> " + this.id +" \n"+
								 "  				AND b.HOADON_FK = TU.PK_SEQ \n"+
								 "				  	AND A.NPP_FK = "+ this.nppdangnhap + "            ) - (SELECT ISNULL(sum(TTHD.SOTIENTT),0) as DATHANHTOAN  \n"+
								 "     	        FROM ERP_THANHTOANHOADONNCC_HOADON TTHD inner join ERP_THANHTOANHOADONNCC TT on TTHD.THANHTOANHD_FK = TT.PK_SEQ \n"+
								 "     	        WHERE TT.NCC_FK = " + (this.nccId == "" ? "0" : this.nccId) + " \n"+
								 " 				AND TTHD.LOAIHD = 1 AND TT.TRANGTHAI = 0 and TT.NPP_FK = " + this.nppdangnhap + " \n"+
								 "    	        )  \n"+
								 "              >0 ) \n"+ 
								 "           and tu.NCC_FK="+ (this.nccId == "" ? "0" : this.nccId);

						query += " UNION ALL \n"+
								 " select distinct 1 as LOAIHD, HOADON_FK AS PK_SEQ, CAST(HOADON_FK as nvarchar(50)) AS SOHOADON , N'TẠM ỨNG ' as   KYHIEU ,a.ngaytamung as ngayhoadon, SOTIENTAMUNG as sotiengoc \n"+
								 "        ,a.sotientamung -( SELECT ISNULL(SUM(CT.SOTIENTT),0) \n"+
								 "                           FROM ERP_THANHTOANHOADON_HOADON CT INNER JOIN ERP_THANHTOANHOADON A ON A.PK_SEQ=CT.THANHTOANHD_FK \n"+
								 "                           WHERE  A.NPP_FK = " + this.nppdangnhap + " AND  A.NCC_FK = "+ (this.nccId == "" ? "0" : this.nccId) + " \n"+
								 " AND ct.LOAIHD = 1 AND A.TRANGTHAI<>'2'  AND CT.HOADON_FK = B.HOADON_FK and a.pk_seq <>"	+ this.id + " )  \n"+
								 "                        -(SELECT ISNULL(sum(TTHD.SOTIENTT),0) as DATHANHTOAN  \n"+
								 "     	      			   FROM ERP_THANHTOANHOADONNCC_HOADON TTHD inner join ERP_THANHTOANHOADONNCC TT on TTHD.THANHTOANHD_FK = TT.PK_SEQ \n"+
								 "     	     			   WHERE TT.NPP_FK = " + this.nppdangnhap +" \n"+
								 " AND  TT.NCC_FK = " + (this.nccId == "" ? "0" : this.nccId) +" \n"+
								 " AND TTHD.LOAIHD = 1   AND TT.TRANGTHAI = 0   \n"+
								 "    	     			   )  AS sotienAVAT , \n"+
								 "         sotientt, '' as POID, a.TIENTE_FK as TTID ," + this.tigia.replace(",", "") +" \n"+
								 " AS TIGIA , isnull(a.ngaydenhantt,'') as ngaydenhantt, '' SOHOPDONG, '' SOHOPDONGNGOAI \n"+
								 " from ERP_THANHTOANHOADON_HOADON b inner join ERP_TAMUNG a on a.pk_seq=b.HOADON_FK  AND b.LOAIHD = 1 \n"+
								 "      and ( a.sotientamung -( SELECT ISNULL(SUM(CT.SOTIENTT),0) \n"+
								 "                              FROM ERP_THANHTOANHOADON_HOADON CT INNER JOIN ERP_THANHTOANHOADON A ON A.PK_SEQ=CT.THANHTOANHD_FK \n"+
								 "                              WHERE A.NPP_FK = " + this.nppdangnhap +" \n"+
								 "  AND A.NCC_FK = " + (this.nccId == "" ? "0" : this.nccId) + " \n"+
								 " AND ct.LOAIHD = 1 AND A.TRANGTHAI<>'2'  AND CT.HOADON_FK=B.HOADON_FK and a.pk_seq <>"+ this.id + " ) \n"+
								 "     						-(SELECT ISNULL(sum(TTHD.SOTIENTT),0) as DATHANHTOAN  \n"+
								 "     						  FROM ERP_THANHTOANHOADONNCC_HOADON TTHD inner join ERP_THANHTOANHOADONNCC TT on TTHD.THANHTOANHD_FK = TT.PK_SEQ \n"+
								 "     						  WHERE TT.NPP_FK = " + this.nppdangnhap+ " \n"+
								 "   AND TT.NCC_FK = " + (this.nccId == "" ? "0" : this.nccId) + " \n"+
								 " AND TTHD.LOAIHD = 1 AND TT.TRANGTHAI = 0   \n" + "    						  ) \n"+
								 "                            > 0) \n" + " where thanhtoanhd_fk=" + this.id +
								 " and b.LOAIHD = 1 \n";

					} else {
						query += " UNION ALL \n";
						query += " SELECT distinct 1 as LOAIHD, TU.PK_SEQ , CAST(TU.PK_SEQ as nvarchar(50)) as sohoadon ,N'TẠM ỨNG'  AS KYHIEU ,TU.NGAYTAMUNG AS ngayhoadon, SOTIENTAMUNG as sotiengoc, SOTIENTAMUNG   \n"+
								 "           -( SELECT ISNULL(SUM(b.SOTIENTT),0)   \n"+
								 "              FROM ERP_THANHTOANHOADON_HOADON B INNER JOIN ERP_THANHTOANHOADON A ON A.PK_SEQ=B.THANHTOANHD_FK  \n"+
								 "              WHERE  A.NCC_FK = " + (this.nccId == "" ? "0" : this.nccId) +
								 " 				AND b.LOAIHD = 1 AND A.TRANGTHAI<>'2'  AND B.HOADON_FK=TU.PK_SEQ AND A.NPP_FK = "+this.nppdangnhap+" "+
								 "          ) -(SELECT ISNULL(sum(TTHD.SOTIENTT),0) as DATHANHTOAN  \n"+
								 "     	      FROM ERP_THANHTOANHOADONNCC_HOADON TTHD inner join ERP_THANHTOANHOADONNCC TT on TTHD.THANHTOANHD_FK = TT.PK_SEQ \n"+
								 "     	      WHERE TT.NCC_FK = " + (this.nccId == "" ? "0" : this.nccId) +
								 " 			  AND TTHD.LOAIHD = 1 AND TT.TRANGTHAI = 0 AND  TT.NPP_FK = "+this.nppdangnhap+" \n" + 
								 "    	     )  AS sotienAVAT , \n"+
								 "            0 AS SOTIENTT, '' as POID, TU.TIENTE_FK as TTID,"	+ this.tigia.replace(",", "") +
								 " AS TIGIA, isnull(TU.ngaydenhantt,'') as ngaydenhantt, '' SOHOPDONG, '' SOHOPDONGNGOAI  \n"+
								 " FROM ERP_TAMUNG TU \n" + 
								 " WHERE TU.NPP_FK= " + this.nppdangnhap +
								 " AND TU.TIENTE_FK = '" + this.tienteId + "'  and ISNULL(TU.HOANTAT,'0')='0'  and  (SOTIENTAMUNG   \n" +
								 "     -( SELECT ISNULL(SUM(b.SOTIENTT),0) \n"+
								 "        FROM ERP_THANHTOANHOADON_HOADON B INNER JOIN ERP_THANHTOANHOADON A ON A.PK_SEQ=B.THANHTOANHD_FK  \n"+
								 "        WHERE A.NCC_FK = " + (this.nccId == "" ? "0" : this.nccId) +
								 " AND b.LOAIHD = 1 AND A.TRANGTHAI<>'2'  AND B.HOADON_FK=TU.PK_SEQ) \n"+
								 "     -(SELECT ISNULL(sum(TTHD.SOTIENTT),0) as DATHANHTOAN  \n"+
								 "     	FROM ERP_THANHTOANHOADONNCC_HOADON TTHD inner join ERP_THANHTOANHOADONNCC TT on TTHD.THANHTOANHD_FK = TT.PK_SEQ \n"+
								 "     	WHERE TT.NCC_FK = " + (this.nccId == "" ? "0" : this.nccId) +
								 " AND TTHD.LOAIHD = 1 AND TT.TRANGTHAI = 0  \n" + "    	) \n"+
								 "   >0 ) and TU.TRANGTHAI = 1 \n" + " and   NCC_FK="+ (this.nccId == "" ? "0" : this.nccId);

					}

					/*// LOAIHD: 2 - CHIPHINOIBO
					if (this.id.length() > 0) {
						query += " UNION ALL \n";
						query += " select distinct 2 as LOAIHD, mh.PK_SEQ, cast(mh.PK_SEQ as nvarchar(50)) as sohoadon,  N'Chi phí nội bộ'  AS KYHIEU , mh.NGAYMUA as ngayhoadon , \n"+
								 "        mhsp.SOTIENPO as sotiengoc, CASE WHEN tthd.TIENTE_FK= '100000' THEN tt.SOTIENAVAT ELSE tt.SOTIENNT END as SOTIENAVAT, \n"+
								 "		 tt.SOTIENTT , '' as POID ,mh.TIENTE_FK as ttId, mh.tygiaquydoi as TIGIA , mh.ngaydenhantt, mh.SOPO SOHOPDONG, isnull(mh.SOHOPDONG,'') SOHOPDONGNGOAI  \n"+
								 " from ERP_MUAHANG mh \n" + 
								 "       inner join \n"+
								 "       (select a.PK_SEQ, SUM(a.TONGTIENAVAT)as SOTIENPO \n"+
								 "		from  ERP_MUAHANG a \n"+
								 "			  inner join ERP_NHACUNGCAP c on c.PK_SEQ = a.NHACUNGCAP_FK  \n"+
								 "		where c.NOIBO = 1 and a.TIENTE_FK= " + this.tienteId + "  and a.TRANGTHAI= 2 \n"+
								 "       group by a.PK_SEQ )as mhsp on mh.PK_SEQ= mhsp.PK_SEQ  \n"+
								 " 	left join ERP_THANHTOANHOADON_HOADON tt on tt.HOADON_FK = mh.PK_SEQ AND TT.LOAIHD = 2  \n"+
								 " 	left join ERP_THANHTOANHOADON tthd on tt.THANHTOANHD_FK = tthd.PK_SEQ \n"+
								 " 	left join TraphacoERP.dbo.ERP_NHACUNGCAP ncc on ncc.PK_SEQ = mh.NHACUNGCAP_FK \n"+
								 " where ncc.NOIBO = 1 and  mh.NHACUNGCAP_FK = '" + this.nccId +
								 "' and tt.THANHTOANHD_FK = '" + this.id + "' \n";

					}
					query += " UNION ALL \n";
					query += " select distinct 2 as LOAIHD,  mh.PK_SEQ, cast(mh.PK_SEQ as nvarchar(50)) as sohoadon,  N'Chi phí nội bộ'  AS KYHIEU , mh.NGAYMUA as ngayhoadon \n"+
							 " 		,mhsp.SOTIENPO as sotiengoc , mhsp.SOTIENPO - isnull(t.SOTIENTT,0) - isnull(dathanhtoanNCC.DATHANHTOAN,0) as SOTIENAVAT ,0 as sotientt, '' as POID  \n"+
							 " 		,mh.TIENTE_FK as ttId, mh.tygiaquydoi as TIGIA,  mh.ngaydenhantt, mh.SOPO SOHOPDONG, isnull(mh.SOHOPDONG, '' ) SOHOPDONGNGOAI  \n"+
							 " from ERP_MUAHANG mh \n" + "       inner join \n"+
							 "       (select a.PK_SEQ, SUM(a.TONGTIENAVAT)as SOTIENPO \n"+
							 "		from  ERP_MUAHANG a \n"+
							 "			  inner join TraphacoERP.dbo.ERP_NHACUNGCAP c on c.PK_SEQ = a.NHACUNGCAP_FK  \n"+
							 "		where c.NOIBO = 1 and a.TIENTE_FK= " + this.tienteId +
							 "  and a.TRANGTHAI= 2 and a.CONGTY_FK = " + this.ctyId +
							 "       group by a.PK_SEQ )as mhsp on mh.PK_SEQ = mhsp.PK_SEQ  \n"+
							 " 		left join ERP_THANHTOANHOADON_HOADON tt on  tt.HOADON_FK = mh.PK_SEQ  AND TT.LOAIHD = 2 \n"+
							 " 		left join ERP_THANHTOANHOADON t on  t.pk_seq = tt.thanhtoanhd_fk  \n"+
							 "      	left join " + 
							 "   	( \n"+
							 "       select TTHD.HOADON_FK , sum(TTHD.SOTIENTT) as DATHANHTOAN  \n"+
							 "       from ERP_THANHTOANHOADONNCC_HOADON TTHD inner join ERP_THANHTOANHOADONNCC TT on TTHD.THANHTOANHD_FK = TT.PK_SEQ \n"+
							 "       where TTHD.LOAIHD = 2 AND TT.TRANGTHAI = 0 and TT.CONGTY_FK = " + this.ctyId +
							 "       group by HOADON_FK \n"+
							 "       ) dathanhtoanNCC  on mh.PK_SEQ = dathanhtoanNCC.hoadon_fk \n"+
							 " 		left join ERP_NHACUNGCAP ncc on ncc.PK_SEQ = mh.NHACUNGCAP_FK \n"+
							 " where mh.CONGTY_FK = " + this.ctyId + 
							 " and ncc.NOIBO = 1 and mh.NHACUNGCAP_FK = '"+ this.nccId + "' \n" + 
							 " 		and ( tt.CONLAI is null or (tt.CONLAI > 0 \n"+
							 "			and mh.TIENTE_FK = " + this.tienteId + " \n"+
							 " 			and tt.HOADON_FK not in \n"+
							 "				(select distinct tt.HOADON_FK \n" + 
							 "				from ERP_MUAHANG mh \n"+
							 "				left join ERP_THANHTOANHOADON_HOADON tt on  tt.HOADON_FK = mh.PK_SEQ  AND TT.LOAIHD = 2 \n"+
							 "				left join ERP_NHACUNGCAP ncc on ncc.PK_SEQ = mh.NHACUNGCAP_FK \n"+
							 "				where mh.CONGTY_FK = " + this.ctyId + " AND mh.NHACUNGCAP_FK = '"+ this.nccId + "' and tt.CONLAI = 0 and mh.TIENTE_FK = " + this.tienteId + ") \n"+
							 "  			and tt.THANHTOANHD_FK in \n"+
							 "				(select MAX(tt.THANHTOANHD_FK) \n" + 
							 "				from Erp_MuaHang mh  \n"+
							 "				left join ERP_THANHTOANHOADON_HOADON tt on tt.HOADON_FK = mh.PK_SEQ   AND TT.LOAIHD = 2 \n"+
							 " 				left join ERP_THANHTOANHOADON t on  t.pk_seq = tt.thanhtoanhd_fk \n"+
							 "				left join ERP_NHACUNGCAP ncc on ncc.PK_SEQ = mh.NHACUNGCAP_FK \n"+
							 "				where ncc.NOIBO = 1 and mh.TRANGTHAI =2 and mh.TIENTE_FK = " + this.tienteId +
							 "  and  mh.NHACUNGCAP_FK =  '" + this.nccId + "' and t.TRANGTHAI<>2	\n"+
							 "	and mh.CONGTY_FK =" + this.ctyId +
							 "	group by tt.HOADON_FK ) ) ) \n" + 
							 " 		and mh.TRANGTHAI = 2 \n";
					
					if (this.id.length() > 0) {
						query += " and mh.PK_SEQ not in (select HOADON_FK from  ERP_THANHTOANHOADON_HOADON where THANHTOANHD_FK = "	+ this.id + ") \n";
					}
					
					query += "       and  mhsp.SOTIENPO - isnull(t.SOTIENTT,0) - isnull(dathanhtoanNCC.DATHANHTOAN,0) > 0 \n";
*/
					/*// LOAIHD: 3 - CHIPHINHANHANG
					if (this.id.length() > 0) {
						query += " UNION ALL \n";

						query += "select distinct 3 as LOAIHD, cpct.pk_seq , cpct.SOCHUNGTU as sohoadon, cpct.KYHIEUCHUNGTU as kyhieu , cpct.NGAYCHUNGTU as ngayhoadon, (cpct.TIENHANG + (cpct.TIENHANG*(cpct.THUESUAT/100))) as sotiengoc \n"+
								 " ,tt.sotienavat as sotienavat, tt.SOTIENTT as sotientt  , cast(mh.PK_SEQ as nvarchar(50)) as POID, isnull(cp.TIENTE_FK,'100000') as ttId,  isnull(cp.TIGIA,1) as TIGIA, cp.NGAYDENHANTT, isnull(mh.SOPO, '') SOHOPDONG, isnull(mh.SOHOPDONG, '') SOHOPDONGNGOAI \n"+
								 " from ERP_CHIPHINHAPKHAU_CHITIET cpct \n"+
								 " left join ERP_CHIPHINHAPKHAU cp on cp.pk_seq = cpct.CHIPHINHAPKHAU_FK \n"+
								 " left join ERP_NHANHANG nh on nh.PK_SEQ = cp.nhanhang_fk \n"+
								 " left join ERP_MUAHANG mh on mh.PK_SEQ = nh.MUAHANG_FK  \n"+
								 " left join ERP_THANHTOANHOADON_HOADON tt on tt.HOADON_FK = cpct.PK_SEQ  AND TT.LOAIHD = 3 \n"+
								 " left join ERP_THANHTOANHOADON t on t.PK_SEQ = tt.THANHTOANHD_FK  \n"+
								 " where cp.NCCID_CN = '" + this.nccId + "' and tt.THANHTOANHD_FK = '" + this.id + "'  \n";

					}

					query += " UNION ALL \n";

					query += "select distinct 3 as LOAIHD, cpct.pk_seq ,cpct.SOCHUNGTU as sohoadon,  cpct.KYHIEUCHUNGTU as kyhieu , cpct.NGAYCHUNGTU as ngayhoadon, (cpct.TIENHANG + (cpct.TIENHANG*(cpct.THUESUAT/100))) as sotiengoc  \n"+
							 ",case when (tt.SOTIENAVAT is null OR t.TRANGTHAI=2) then (cpct.TIENHANG + (cpct.TIENHANG*(cpct.THUESUAT/100)) - isnull(dathanhtoanNCC.DATHANHTOAN,0) ) else tt.CONLAI end as sotienavat \n"+
							 " ,'0' as sotientt, cast(mh.PK_SEQ as nvarchar(50)) as POID, isnull(cp.TIENTE_FK,'100000') as ttId,  isnull(cp.TIGIA,1) as TIGIA , cp.NGAYDENHANTT, isnull(mh.SOPO, '') SOHOPDONG, isnull(mh.SOHOPDONG, '') SOHOPDONGNGOAI  \n"+
							 "from ERP_CHIPHINHAPKHAU_CHITIET cpct \n"+
							 "left join ERP_CHIPHINHAPKHAU cp on cp.pk_seq = cpct.CHIPHINHAPKHAU_FK \n"+
							 "left join ERP_NHANHANG nh on nh.PK_SEQ = cp.nhanhang_fk \n"+
							 "left join ERP_MUAHANG mh on mh.PK_SEQ = nh.MUAHANG_FK  \n"+
							 "left join ERP_THANHTOANHOADON_HOADON tt on tt.HOADON_FK = cpct.PK_SEQ  AND TT.LOAIHD = 3 \n"+
							 "left join " + 
							 "   	( \n"+
							 "       select TTHD.HOADON_FK , sum(TTHD.SOTIENTT) as DATHANHTOAN  \n"+
							 "       from ERP_THANHTOANHOADONNCC_HOADON TTHD inner join ERP_THANHTOANHOADONNCC TT on TTHD.THANHTOANHD_FK = TT.PK_SEQ \n"+
							 "       where TTHD.LOAIHD = 3 AND TT.TRANGTHAI = 0 and TT.CONGTY_FK = " + this.ctyId +
							 "       group by HOADON_FK \n"+
							 "       ) dathanhtoanNCC  on cpct.PK_SEQ = dathanhtoanNCC.hoadon_fk \n"+
							 "left join ERP_THANHTOANHOADON t on t.PK_SEQ = tt.THANHTOANHD_FK  \n"+
							 "where isnull(cp.TIENTE_FK,'100000') = " + this.tienteId + " and cp.NCCID_CN = '"+ this.nccId + "' and ( tt.CONLAI is null or (tt.CONLAI > 0  \n"+
							 "					 and tt.HOADON_FK not in  \n"+
							 "						(select distinct tt.HOADON_FK  \n"+
							 "						from ERP_CHIPHINHAPKHAU_CHITIET cpct  \n"+
							 "							left join ERP_CHIPHINHAPKHAU cp on cp.pk_seq = cpct.CHIPHINHAPKHAU_FK \n"+
							 "							left join ERP_THANHTOANHOADON_HOADON tt on tt.HOADON_FK = cpct.PK_SEQ  AND TT.LOAIHD = 3 \n"+
							 "						where CP.CONGTY_FK =" + this.ctyId + "  AND cp.NCCID_CN = '"+ this.nccId + "' and tt.CONLAI = 0 and cp.TIENTE_FK= " + this.tienteId + ") \n"+
							 "					  and tt.THANHTOANHD_FK in \n"+
							 "						(select MAX(tt.THANHTOANHD_FK)  \n"+
							 "						from ERP_CHIPHINHAPKHAU_CHITIET cpct    \n"+
							 "							left join ERP_THANHTOANHOADON_HOADON tt on tt.HOADON_FK = cpct.PK_SEQ AND TT.LOAIHD = 3 \n"+
							 "					 		left join ERP_THANHTOANHOADON t on  t.pk_seq = tt.thanhtoanhd_fk  \n"+
							 "							left join ERP_CHIPHINHAPKHAU cp on cp.pk_seq = cpct.CHIPHINHAPKHAU_FK \n"+
							 "						 where cp.CONGTY_FK = " + this.ctyId + " AND cp.trangthai = 1 and cp.TIENTE_FK= " + this.tienteId + "  and  cp.NCCID_CN =  '"+ this.nccId + "' and t.TRANGTHAI<>2	 \n"+
							 "						 group by tt.HOADON_FK ) ) ) \n"+
							 "					 and cp.trangthai = 1  \n";
					if (this.id.length() > 0) {
						query += " and cpct.pk_seq not in (select HOADON_FK from ERP_THANHTOANHOADON_HOADON where THANHTOANHD_FK = "+ this.id + " ) \n";
					}
*/
					if (this.tienteId.equals("100000")) {
						// LOAIHD: 4 - THUENHAPKHAU
					/*	if (this.id.length() > 0) {
							query += "UNION ALL \n";
							query += "select distinct 4 as LOAIHD, tnk.pk_seq , tnk.SOCHUNGTU as sohoadon, N'Thuế nhập khẩu' as kyhieu , tnk.NGAYCHUNGTU as ngayhoadon, tnk.THUENK as sotiengoc \n"+
									 " 		,tt.sotienavat, tt.SOTIENTT as sotientt  ,'' as POID  \n"+
									 " 		,'100000' as ttId, '1' as TIGIA, tnk.ngaydenhantt, '' SOHOPDONG, '' SOHOPDONGNGOAI \n"+
									 "from ERP_THUENHAPKHAU tnk \n"+
									 "		left join ERP_THANHTOANHOADON_HOADON tt on tt.HOADON_FK = tnk.PK_SEQ AND TT.LOAIHD = 4 \n"+
									 "		left join ERP_THANHTOANHOADON t on t.PK_SEQ = tt.THANHTOANHD_FK  \n"+
									 " where tnk.NCC_FK= '" + this.nccId + "' and tt.THANHTOANHD_FK = '" + this.id + "' and tt.LOAITHUE = N'Thuế nhập khẩu' \n" +

									 " union all \n" +

									 "select distinct  4 as LOAIHD, tnk.pk_seq , tnk.SOCHUNGTU as sohoadon, N'VAT nhập khẩu' as kyhieu , tnk.NGAYCHUNGTU as ngayhoadon, tnk.VAT as sotiengoc \n"+
									 " 		,tt.sotienavat as sotienavat, tt.SOTIENTT as sotientt  , '' as POID \n"+
									 " 		,'100000' as ttId, '1' as TIGIA,  tnk.ngaydenhantt, '' SOHOPDONG, '' SOHOPDONGNGOAI \n"+
									 "from ERP_THUENHAPKHAU tnk \n"+
									 "		left join ERP_THANHTOANHOADON_HOADON tt on tt.HOADON_FK = tnk.PK_SEQ AND TT.LOAIHD = 4 \n"+
									 "		left join ERP_THANHTOANHOADON t on t.PK_SEQ = tt.THANHTOANHD_FK  \n"+
									 " where tnk.NCC_FK= '" + this.nccId + "' and tt.THANHTOANHD_FK = '" + this.id +"' and tt.LOAITHUE = N'VAT nhập khẩu' \n";

						}
						query += " UNION ALL \n";
						query += " select distinct  4 as LOAIHD, tnk.pk_seq, tnk.SOCHUNGTU as sohoadon, N'Thuế nhập khẩu' as kyhieu ,  tnk.NGAYCHUNGTU as ngayhoadon, tnk.THUENK as sotiengoc \n"+
								 "		,tnk.THUENK - isnull(tt.sotientt,0)- isnull(dathanhtoanNCC.DATHANHTOAN,0) as sotienavat, '0' as sotientt, '' as POID \n"+
								 " 		,'100000' as ttId, '1' as TIGIA, tnk.ngaydenhantt, '' SOHOPDONG, '' SOHOPDONGNGOAI \n"+
								 " from ERP_THUENHAPKHAU tnk \n"+
								 "		left join ERP_THANHTOANHOADON_HOADON tt on tt.HOADON_FK = tnk.PK_SEQ and tt.LOAITHUE =  N'Thuế nhập khẩu' AND TT.LOAIHD = 4 \n"+
								 "		left join ERP_THANHTOANHOADON t on t.PK_SEQ = tt.THANHTOANHD_FK  \n"+
								 "       left join " + 
								 "   	( \n"+
								 "       select TTHD.HOADON_FK , sum(TTHD.SOTIENTT) as DATHANHTOAN  \n"+
								 "       from ERP_THANHTOANHOADONNCC_HOADON TTHD inner join ERP_THANHTOANHOADONNCC TT on TTHD.THANHTOANHD_FK = TT.PK_SEQ \n"+
								 "       where TTHD.LOAIHD = 4 AND tthd.LOAITHUE =  N'Thuế nhập khẩu' AND TT.TRANGTHAI = 0   \n"+
								 "			  and TT.CONGTY_FK =" + this.ctyId + 
								 "       group by HOADON_FK \n"+
								 "       ) dathanhtoanNCC  on tnk.PK_SEQ  = dathanhtoanNCC.hoadon_fk \n"+
								 " where tnk.NCC_FK = '" + this.nccId+ "' and ( tt.CONLAI is null or (tt.CONLAI > 0 \n"+
								 "					 and tt.HOADON_FK not in \n"+
								 "						(select distinct tt.HOADON_FK \n"+
								 "						from ERP_THUENHAPKHAU tnk \n"+
								 "						left join ERP_THANHTOANHOADON_HOADON tt on tt.HOADON_FK = tnk.PK_SEQ AND TT.LOAIHD = 4 \n"+
								 "						where tnk.NCC_FK = '" + this.nccId+ "' and tt.CONLAI = 0 and tnk.CONGTY_FK = " + this.ctyId +
								 "					  and tt.THANHTOANHD_FK in \n"+
								 "						(select MAX(tt.THANHTOANHD_FK) \n"+
								 "						from ERP_THUENHAPKHAU tnk  \n"+
								 "						left join ERP_THANHTOANHOADON_HOADON tt on tt.HOADON_FK = tnk.PK_SEQ AND TT.LOAIHD = 4 \n"+
								 "						left join ERP_THANHTOANHOADON t on  t.pk_seq = tt.thanhtoanhd_fk \n"+
								 "						 where tnk.trangthai = 2    and tnk.NCC_FK = '" + this.nccId + "' and t.TRANGTHAI<>2 and tnk.CONGTY_FK = " + this.ctyId +
								 "						 group by tt.HOADON_FK ) ) )\n"+
								 "					 and tnk.trangthai in (1,2)  and tnk.THUENK > 0 ) \n";
						
						if (this.id.length() > 0) {
							query += " and tnk.PK_SEQ not in (select HOADON_FK from ERP_THANHTOANHOADON_HOADON where THANHTOANHD_FK= "
									+ this.id + " AND LOAIHD = 4 and LOAITHUE= N'Thuế nhập khẩu') \n";
						}

						query += " UNION ALL \n"+
								 "select distinct 4 AS LOAIHD, tnk.pk_seq ,tnk.SOCHUNGTU as sohoadon, N'VAT nhập khẩu' as kyhieu ,  tnk.NGAYCHUNGTU as ngayhoadon, tnk.VAT as sotiengoc \n"+
								 "		,tnk.VAT - isnull(tt.sotientt,0)- isnull(dathanhtoanNCC.DATHANHTOAN,0) as sotienavat, '0' as sotientt, '' as POID \n"+
								 " 		,'100000' as ttId, '1' as TIGIA, tnk.ngaydenhantt, '' SOHOPDONG, '' SOHOPDONGNGOAI \n"+
								 "from ERP_THUENHAPKHAU tnk \n"+
								 "left join ERP_THANHTOANHOADON_HOADON tt on tt.HOADON_FK = tnk.PK_SEQ and tt.LOAITHUE =  N'VAT nhập khẩu' AND TT.LOAIHD = 4 \n"+
								 "left join ERP_THANHTOANHOADON t on t.PK_SEQ = tt.THANHTOANHD_FK  \n"+
								 "       left join " + 
								 "   	( \n"+
								 "       select TTHD.HOADON_FK , sum(TTHD.SOTIENTT) as DATHANHTOAN  \n"+
								 "       from ERP_THANHTOANHOADONNCC_HOADON TTHD inner join ERP_THANHTOANHOADONNCC TT on TTHD.THANHTOANHD_FK = TT.PK_SEQ \n"+
								 "       where TTHD.LOAIHD = 4 AND tthd.LOAITHUE =  N'VAT nhập khẩu' AND TT.TRANGTHAI = 0  and TT.CONGTY_FK = "	+ this.ctyId + 
								 "       group by HOADON_FK \n"+
								 "       ) dathanhtoanNCC  on tnk.PK_SEQ  = dathanhtoanNCC.hoadon_fk \n"+
								 "where tnk.CONGTY_FK = " + this.ctyId + " AND tnk.NCC_FK = '" + this.nccId +
								 "' and ( tt.CONLAI is null or (tt.CONLAI > 0 \n"+
								 "					 and tt.HOADON_FK not in \n"+
								 "						(select distinct tt.HOADON_FK \n"+
								 "						from ERP_THUENHAPKHAU tnk \n"+
								 "						left join ERP_THANHTOANHOADON_HOADON tt on tt.HOADON_FK = tnk.PK_SEQ AND TT.LOAIHD = 4 \n"+
								 "						where tnk.NCC_FK = '" + this.nccId+
								 "' and tt.CONLAI = 0 and tnk.CONGTY_FK = " + this.ctyId + " ) \n"+
								 "					  and tt.THANHTOANHD_FK in \n"+
								 "						(select MAX(tt.THANHTOANHD_FK) \n"+
								 "						from ERP_THUENHAPKHAU tnk  \n"+
								 "						left join ERP_THANHTOANHOADON_HOADON tt on tt.HOADON_FK = tnk.PK_SEQ AND LOAIHD = 4 \n"+
								 "						left join ERP_THANHTOANHOADON t on  t.pk_seq = tt.thanhtoanhd_fk \n"+
								 "						 where tnk.trangthai = 2    and tnk.NCC_FK = '" + this.nccId+
								 "' and t.TRANGTHAI<>2 and tnk.CONGTY_FK = " + this.ctyId + " \n"+
								 "						 group by tt.HOADON_FK ) ) )\n"+
								 "					 and tnk.trangthai = 2  and tnk.VAT > 0 \n ";
						if (this.id.length() > 0) {
							query += " and tnk.PK_SEQ not in (select HOADON_FK from ERP_THANHTOANHOADON_HOADON where THANHTOANHD_FK= "
									+ this.id + " AND LOAIHD = 4  and LOAITHUE= N'VAT nhập khẩu' ) \n";
						}*/

					}
					// LOAIHD: 5 - CHIPHIKHAC

					/*if (this.id.length() > 0) {
						query += " UNION ALL \n";
						query += "select distinct 5 as LOAIHD, cp.pk_seq , cast(cp.PK_SEQ as nvarchar(50)) as sohoadon, cp.DIENGIAI as kyhieu ,  cp.NGAY as ngayhoadon, (cpct.TONGTIENCHUATHUE + cpct.THUE ) as sotiengoc \n"+
								 "      ,case when cp.TIENTE_FK= '100000' then tt.sotienavat else tt.SOTIENNT end as sotienavat, tt.SOTIENTT as sotientt  , '' as POID, cp.TIENTE_FK as ttId, \n"+
								 "      isnull(cp.tigia,1) as tigia , isnull(cp.ngaydenhantt,'') as ngaydenhantt, '' SOHOPDONG, '' SOHOPDONGNGOAI \n"+
								 "from ERP_CHIPHIKHAC_CHITIET cpct \n"+
								 "     left join ERP_CHIPHIKHAC cp on cp.pk_seq = cpct.CHIPHIKHAC_FK \n"+
								 "     left join ERP_THANHTOANHOADON_HOADON tt on tt.HOADON_FK = cpct.CHIPHIKHAC_FK AND TT.LOAIHD = 5 \n"+
								 "     left join ERP_THANHTOANHOADON t on t.PK_SEQ = tt.THANHTOANHD_FK  \n"+
								 "where cp.DOITUONG = '" + this.nccId + "' and tt.THANHTOANHD_FK = '" + this.id +
								 "' and cp.LOAI= '0' \n";

					}
					query += " UNION ALL \n";
					query += "select distinct 5 as LOAIHD, cp.pk_seq , cast(cp.PK_SEQ as nvarchar(50)) as sohoadon, cp.DIENGIAI as kyhieu,  cp.NGAY as ngayhoadon, (cpct.TONGTIENCHUATHUE + cpct.THUE ) as sotiengoc \n"+
							 "	   ,case when (tt.SOTIENAVAT is null OR t.TRANGTHAI=2) then (cpct.TONGTIENCHUATHUE + cpct.THUE - isnull(xoatamung.dathanhtoan,0) - isnull(dathanhtoanNCC.DATHANHTOAN,0) ) else tt.CONLAI end as sotienavat \n"+
							 "	   ,'0' as sotientt, '' as POID , cp.TIENTE_FK as ttId, isnull(cp.TIGIA,1) as TIGIA, \n"+
							 "      isnull(cp.ngaydenhantt,'') as ngaydenhantt, '' SOHOPDONG, '' SOHOPDONGNGOAI  \n"+
							 "from ERP_CHIPHIKHAC_CHITIET cpct \n"+
							 "     left join ERP_CHIPHIKHAC cp on cp.pk_seq = cpct.CHIPHIKHAC_FK \n"+
							 "     left join ERP_THANHTOANHOADON_HOADON tt on tt.HOADON_FK = cpct.CHIPHIKHAC_FK AND TT.LOAIHD = 5 \n"+
							 "     left join ERP_THANHTOANHOADON t on t.PK_SEQ = tt.THANHTOANHD_FK  \n"+
							 "     left join (select a.cttt_fk,SUM(a.tienthanhtoan) as dathanhtoan \n"+
							 "	         from ERP_XOAKHTRATRUOC_CTTT a inner join ERP_XOAKHTRATRUOC b on a.xoakhtratruoc_fk=b.PK_SEQ \n"+
							 "	         where b.CONGTY_FK = " + this.ctyId + " AND b.TRANGTHAI=1 and b.TIENTE_FK= "+ this.tienteId + " and b.LOAIXOATRATRUOC ='1' and isnull(b.NCC_FK, b.NHANVIEN_FK)= "+ this.nccId + " \n"+
							 "	         group by a.cttt_fk) xoatamung  on xoatamung.cttt_fk = cp.PK_SEQ \n"+
							 "     left join " + 
							 "   	( \n"+
							 "       select TTHD.HOADON_FK , sum(TTHD.SOTIENTT) as DATHANHTOAN  \n"+
							 "       from ERP_THANHTOANHOADONNCC_HOADON TTHD inner join ERP_THANHTOANHOADONNCC TT on TTHD.THANHTOANHD_FK = TT.PK_SEQ \n"+
							 "       where TTHD.LOAIHD = 5 AND TT.TRANGTHAI = 0 and TT.CONGTY_FK = " + this.ctyId + "  \n" + 
							 "       group by HOADON_FK \n"+
							 "       ) dathanhtoanNCC  on cp.PK_SEQ  = dathanhtoanNCC.hoadon_fk \n"+
							 "where  cp.CONGTY_FK = " + this.ctyId + "  AND cp.DOITUONG = '" + this.nccId +
							 "' and cp.TIENTE_FK= " + this.tienteId + " and ( tt.CONLAI is null or (tt.CONLAI > 0 \n"+
							 "					 and tt.HOADON_FK not in \n"+
							 "						(select distinct tt.HOADON_FK \n"+
							 "						from ERP_CHIPHIKHAC_CHITIET cpct \n"+
							 "						left join ERP_CHIPHIKHAC cp on cp.pk_seq = cpct.CHIPHIKHAC_FK \n"+
							 "						left join ERP_THANHTOANHOADON_HOADON tt on tt.HOADON_FK = cpct.CHIPHIKHAC_FK AND TT.LOAIHD = 5 \n"+
							 "						where cp.DOITUONG = '" + this.nccId +
							 "' and tt.CONLAI = 0 and cp.Congty_fk = " + this.ctyId + " ) \n"+
							 "					  and tt.THANHTOANHD_FK in \n"+
							 "						(select MAX(tt.THANHTOANHD_FK) \n"+
							 "						from ERP_CHIPHIKHAC_CHITIET cpct   \n"+
							 "							left join ERP_THANHTOANHOADON_HOADON tt on tt.HOADON_FK = cpct.CHIPHIKHAC_FK AND TT.LOAIHD = 5 \n"+
							 "					 		left join ERP_THANHTOANHOADON t on  t.pk_seq = tt.thanhtoanhd_fk \n"+
							 "							left join ERP_CHIPHIKHAC cp on cp.pk_seq = cpct.CHIPHIKHAC_FK \n"+
							 "						 where cp.trangthai = 1    and  cp.DOITUONG =  '" + this.nccId +
							 "' and t.TRANGTHAI<>2 and cp.CONGTY_FK = " + this.ctyId + " \n" +
							 "						 group by tt.HOADON_FK ) ) )\n" +
							 "					 and cp.trangthai = 1 \n ";

					if (this.DoiTuongChiPhiKhac.equals("1")) { // Neu la NCC
						query += " and cp.LOAI= '0' \n";
					}
					if (this.id.length() > 0) {
						query += " and cp.pk_seq not in (select HOADON_FK from ERP_THANHTOANHOADON_HOADON where THANHTOANHD_FK = "	+ this.id + "  ) \n";
					}
					query += " and ((cpct.TONGTIENCHUATHUE + cpct.THUE - isnull(xoatamung.dathanhtoan,0) - isnull(dathanhtoanNCC.dathanhtoan,0) ) > 0 or tt.CONLAI > 0) \n";
*/
					// LOAIHD: 6 - DENGHITHANHTOAN
					if (this.id.length() > 0) {
						query += " UNION ALL \n";
						
						query += " select distinct 6 as LOAIHD, mh.PK_SEQ, cast(mh.SOPO as nvarchar(50)) as sohoadon,  N'Đề nghị thanh toán'  AS KYHIEU , mh.NGAYMUA as ngayhoadon , mh.TONGTIENAVAT as sotiengoc, \n"+
								 "        CASE WHEN tthd.TIENTE_FK= '100000' THEN tt.SOTIENAVAT ELSE tt.SOTIENNT END as SOTIENAVAT, \n"+
								 "		 tt.SOTIENTT , '' as POID ,mh.TIENTE_FK as ttId, mh.tygiaquydoi as TIGIA, mh.ngaydenhantt, '' SOHOPDONG, '' SOHOPDONGNGOAI  \n"+
								 " from ERP_MUAHANG mh \n"+
								 " 	inner join ERP_THANHTOANHOADON_HOADON tt on tt.HOADON_FK = mh.PK_SEQ  AND TT.LOAIHD = 6 \n"+
								 " 	inner join ERP_THANHTOANHOADON tthd on tt.THANHTOANHD_FK = tthd.PK_SEQ  \n"+
								 " 	inner join TraphacoERP.dbo.ERP_NHACUNGCAP ncc on ncc.PK_SEQ = mh.NHACUNGCAP_FK \n"+
								 " where   mh.NHACUNGCAP_FK = '" + this.nccId + "' and tt.THANHTOANHD_FK = '" + this.id	+ "'  \n";

					}
					query += " UNION ALL \n";
					query += " select distinct 6 as LOAIHD, mh.PK_SEQ, cast(mh.SOPO as nvarchar(50)) as sohoadon,  N'Đề nghị thanh toán'  AS KYHIEU , mh.NGAYMUA as ngayhoadon, mh.TONGTIENAVAT as sotiengoc \n"+
							 " 		, mh.TONGTIENAVAT- isnull(t.SOTIENTT,0) - isnull(DATT.SOTIENTT,0) - isnull(DATHANHTOANNCC.DATHANHTOAN,0) as SOTIENAVAT ,0 as sotientt, '' as POID  \n"+
							 " 		,mh.TIENTE_FK as ttId, mh.tygiaquydoi as TIGIA,  mh.ngaydenhantt, '' SOHOPDONG, '' SOHOPDONGNGOAI  \n"+
							 " from ERP_MUAHANG mh \n"+
							 " 		LEFT JOIN ERP_THANHTOANHOADON_HOADON tt on  tt.HOADON_FK = mh.PK_SEQ AND TT.LOAIHD = 6 \n"+
							 " 		LEFT JOIN ERP_THANHTOANHOADON t on  t.pk_seq = tt.thanhtoanhd_fk  \n"+
							 " 		INNER JOIN TraphacoERP.dbo.ERP_NHACUNGCAP ncc on ncc.PK_SEQ = mh.NHACUNGCAP_FK \n"+
							 " 		LEFT JOIN " + 
							 "       ( \n"+
							 "         SELECT tt.HOADON_FK , SUM(tt.SOTIENTT) as SOTIENTT \n"+
							 "         FROM ERP_THANHTOANHOADON_HOADONBOPHAN tt \n"+
							 "              INNER JOIN ERP_THANHTOANHOADON t on tt.THANHTOANHD_FK = t.PK_SEQ \n"+
							 "         WHERE tt.LOAIHD = 6 AND t.TRANGTHAI != 2  AND tt.NCC_FK = " + this.nccId+
							 " 		   and t.NPP_FK = " + this.nppdangnhap + 
							 "         GROUP BY tt.HOADON_FK \n"+
							 "        ) DATT ON DATT.HOADON_FK = mh.PK_SEQ \n" + 
							 "       LEFT JOIN  " + 
							 "   	( \n"+
							 "       select TTHD.HOADON_FK , sum(TTHD.SOTIENTT) as DATHANHTOAN  \n"+
							 "       from ERP_THANHTOANHOADONNCC_HOADON TTHD inner join ERP_THANHTOANHOADONNCC TT on TTHD.THANHTOANHD_FK = TT.PK_SEQ \n"+
							 "       where TTHD.LOAIHD = 6 AND TT.TRANGTHAI = 0 AND tt.NPP_FK = " + this.nppdangnhap +
							 "       group by HOADON_FK \n"+
							 "       ) dathanhtoanNCC  on mh.PK_SEQ  = dathanhtoanNCC.hoadon_fk \n"+
							 " where mh.NPP_FK =" + this.nppdangnhap + "  AND mh.TRANGTHAI = 1 and mh.NHACUNGCAP_FK = '"+ this.nccId + "' \n"+ 
							 " 		and ( tt.CONLAI is null or  \n"+
							 "           (tt.CONLAI > 0 \n" + 
							 "			and mh.TIENTE_FK = " + this.tienteId + " \n"+
							 " 			and tt.HOADON_FK not in \n"+
							 "				(select distinct tt.HOADON_FK \n" + 
							 "				 from ERP_MUAHANG mh \n"+
							 "				      left join ERP_THANHTOANHOADON_HOADON tt on  tt.HOADON_FK = mh.PK_SEQ AND TT.LOAIHD = 6 \n"+
							 "				      left join TraphacoERP.dbo.ERP_NHACUNGCAP ncc on ncc.PK_SEQ = mh.NHACUNGCAP_FK \n"+
							 "				where mh.NPP_FK = " + this.nppdangnhap +
							 " and mh.TRANGTHAI = 1 and mh.NHACUNGCAP_FK = '" + this.nccId +
							 "' and tt.CONLAI = 0 and mh.TIENTE_FK = " + this.tienteId + ") \n"+
							 "  			and tt.THANHTOANHD_FK in \n"+
							 "				(select MAX(tt.THANHTOANHD_FK) \n" + 
							 "				from Erp_MuaHang mh  \n"+
							 "				    left join ERP_THANHTOANHOADON_HOADON tt on tt.HOADON_FK = mh.PK_SEQ AND TT.LOAIHD = 6 \n"+
							 " 				    left join ERP_THANHTOANHOADON t on  t.pk_seq = tt.thanhtoanhd_fk \n"+
							 "				    left join TraphacoERP.dbo.ERP_NHACUNGCAP ncc on ncc.PK_SEQ = mh.NHACUNGCAP_FK \n"+
							 "				where mh.NPP_FK = " + this.nppdangnhap +
							 " and  mh.TRANGTHAI = 1 and mh.LOAIHANGHOA_FK = '2' and TYPE = '1' and mh.TIENTE_FK = "+ this.tienteId + 
							 "  and  mh.NHACUNGCAP_FK =  '" + this.nccId + "' and t.TRANGTHAI<>2	\n"+
							 "	 			group by tt.HOADON_FK ) ) ) \n"+
							 " 		and  mh.LOAIHANGHOA_FK = '2' and mh.TYPE ='1' \n";
					if (this.id.length() > 0) {
						query += " and mh.PK_SEQ not in (select HOADON_FK from  ERP_THANHTOANHOADON_HOADON where THANHTOANHD_FK = "	+ this.id + ") \n";
					}
					query += "       and  (mh.TONGTIENAVAT - isnull(t.SOTIENTT,0) - isnull(DATHANHTOANNCC.DATHANHTOAN,0) ) > 0 \n";
				}
				
				// LOAD NHỮNG HD CỦA NHÂN VIÊN
				if(this.NhanvienId.trim().length() > 0)
				{
					// LOAIHD: 1 - TAMUNG
	 				
					if(this.id.length() >0)
					{
							query="SELECT distinct 1 AS LOAIHD, TU.PK_SEQ , cast(TU.PK_SEQ as nvarchar(50)) as sohoadon ,N'TẠM ỨNG'  AS KYHIEU ,TU.NGAYTAMUNG AS ngayhoadon, SOTIENTAMUNG as sotiengoc,SOTIENTAMUNG \n" + 
							"             -(SELECT ISNULL(SUM(b.SOTIENTT),0) \n" +
							"               FROM ERP_THANHTOANHOADON_HOADON B INNER JOIN ERP_THANHTOANHOADON A ON A.PK_SEQ=B.THANHTOANHD_FK  \n" +
							"               WHERE B.LOAIHD = 1 AND A.TRANGTHAI <>2 and a.pk_seq <> "+this.id+"  AND B.HOADON_FK=TU.PK_SEQ AND A.NPP_FK = "+this.nppdangnhap+" ) AS sotienAVAT , \n" +
							"             0 AS SOTIENTT, '' as POID \n" +
							"            , TU.TIENTE_FK as TTID, "+this.tigia.replace(",", "")+" AS TIGIA, isnull(TU.ngaydenhantt,'') as ngaydenhantt, '' SOHOPDONG, '' SOHOPDONGNGOAI  \n" +
							"      FROM ERP_TAMUNG TU \n" +
							"      WHERE TU.TRANGTHAI = 1 AND ISNULL(TU.HOANTAT,'0')='0' and TU.TIENTE_FK = (select tiente_fk from ERP_THANHTOANHOADON where pk_seq="+this.id+" ) \n" +
							"            and TU.pk_seq not in  \n" +
							"            (select hoadon_fk \n" +
							"             from ERP_THANHTOANHOADON a inner join ERP_THANHTOANHOADON_HOADON b on a.pk_seq=b.thanhtoanhd_fk \n" +
							"             where   b.thanhtoanhd_fk="+this.id+" and b.LOAIHD = 1 and a.NPP_FK = "+this.nppdangnhap+"  )  \n" +
							"             and  (SOTIENTAMUNG \n" +
							"              -( SELECT ISNULL(SUM(b.SOTIENTT),0) \n" +
							"                 FROM ERP_THANHTOANHOADON_HOADON B INNER JOIN ERP_THANHTOANHOADON A ON A.PK_SEQ=B.THANHTOANHD_FK  \n" +
							"                WHERE A.NPP_FK = "+this.nppdangnhap+"  AND B.LOAIHD = 1 AND A.TRANGTHAI <>2 and a.pk_seq <> "+this.id+"  AND HOADON_FK = TU.PK_SEQ) >0 ) \n" + 
							"             and   tu.NHANVIEN_FK="+ (this.NhanvienId ==""?"0":this.NhanvienId) ;
							
							query+=
							" UNION ALL \n" +
							" select distinct 1 AS LOAIHD, B.HOADON_FK AS PK_SEQ, cast(B.HOADON_FK as nvarchar(50)) AS SOHOADON , N'TẠM ỨNG ' as   KYHIEU ,a.ngaytamung as ngayhoadon,SOTIENTAMUNG as sotiengoc  \n" +
							"       ,a.sotientamung -( SELECT ISNULL(SUM(CT.SOTIENTT),0) \n" +
							"                          FROM ERP_THANHTOANHOADON_HOADON CT INNER JOIN ERP_THANHTOANHOADON A ON A.PK_SEQ=CT.THANHTOANHD_FK \n" +
							"                          WHERE A.NPP_FK = "+this.nppdangnhap+"  AND CT.LOAIHD = 1 AND A.TRANGTHAI<>'2'  AND CT.HOADON_FK=B.HOADON_FK and a.pk_seq <>"+this.id+" )  AS sotienAVAT , \n" +
							"  		sotientt, '' as POID,  a.TIENTE_FK as TTID ,"+this.tigia.replace(",", "")+" AS TIGIA ,isnull(a.ngaydenhantt,'') as ngaydenhantt, '' SOHOPDONG, '' SOHOPDONGNGOAI \n" +
							" from ERP_THANHTOANHOADON_HOADON b \n" +
							"      inner join ERP_TAMUNG a on a.pk_seq=b.HOADON_fk  and b.loaihd = 1  \n" +
							"      and ( a.sotientamung -( SELECT ISNULL(SUM(CT.SOTIENTT),0) \n" +
							"                              FROM ERP_THANHTOANHOADON_HOADON CT INNER JOIN ERP_THANHTOANHOADON A ON A.PK_SEQ=CT.THANHTOANHD_FK \n" + 
							"                              WHERE A.NPP_FK = "+this.nppdangnhap+" AND CT.LOAIHD = 1 AND A.TRANGTHAI<>'2'  AND CT.HOADON_FK=B.HOADON_FK and a.pk_seq <>"+this.id+" ) >0) \n" +
							" where   thanhtoanhd_fk="+this.id+"  \n"  ;						
											
					}
					else
					{ 
						
						query+=" SELECT distinct 1 AS LOAIHD, TU.PK_SEQ , cast(TU.PK_SEQ as nvarchar(50)) as sohoadon ,N'TẠM ỨNG'  AS KYHIEU ,TU.NGAYTAMUNG AS ngayhoadon, SOTIENTAMUNG as sotiengoc, SOTIENTAMUNG   \n" + 
							"        -( SELECT ISNULL(SUM(b.SOTIENTT),0)   \n" +
							" 			FROM ERP_THANHTOANHOADON_HOADON B INNER JOIN ERP_THANHTOANHOADON A ON A.PK_SEQ=B.THANHTOANHD_FK  \n" +
							"		    WHERE B.LOAIHD = 1 AND A.TRANGTHAI<>'2'  AND B.HOADON_FK = TU.PK_SEQ  AND A.NPP_FK = "+this.nppdangnhap+" ) AS sotienAVAT , \n" +
							"        0 AS SOTIENTT, '' as POID, TU.TIENTE_FK as TTID,"+this.tigia.replace(",", "")+" AS TIGIA, isnull(TU.ngaydenhantt,'') as ngaydenhantt, '' SOHOPDONG, '' SOHOPDONGNGOAI  \n" +
							" FROM ERP_TAMUNG TU \n" +
							" WHERE TU.NPP_FK = "+this.nppdangnhap+"  AND TU.TIENTE_FK = '"+this.tienteId+"'  and ISNULL(TU.HOANTAT,'0')='0'  and  (SOTIENTAMUNG   \n" +
							"   -( SELECT ISNULL(SUM(b.SOTIENTT),0) \n" +
							"      FROM ERP_THANHTOANHOADON_HOADON B INNER JOIN ERP_THANHTOANHOADON A ON A.PK_SEQ=B.THANHTOANHD_FK  \n" +
							"      WHERE A.NPP_FK = "+this.nppdangnhap+" AND B.LOAIHD = 1 AND A.TRANGTHAI<>'2'  AND B.HOADON_FK=TU.PK_SEQ) >0 ) \n" +
							"   and TU.TRANGTHAI = 1 \n" +
							"   and   NHANVIEN_FK="+ (this.NhanvienId ==""?"0":this.NhanvienId) ;
					
					}
				
					/*// LOAIHD: 5 - CHIPHIKHAC

					
					if(this.id.length() > 0)
					{
					 query +=" UNION ALL \n"; 
					 query +="select distinct 5 as LOAIHD, cp.pk_seq , cast(cp.PK_SEQ as nvarchar(50)) as sohoadon, cp.DIENGIAI as kyhieu ,  cp.NGAY as ngayhoadon, (cpct.TONGTIENCHUATHUE + cpct.THUE ) as sotiengoc \n" +
							"      ,case when cp.TIENTE_FK= '100000' then tt.sotienavat else tt.SOTIENNT end as sotienavat, tt.SOTIENTT as sotientt  , '' as POID, cp.TIENTE_FK as ttId, \n" +
							"      isnull(cp.tigia,1) as tigia , isnull(cp.ngaydenhantt,'') as ngaydenhantt, '' SOHOPDONG, '' SOHOPDONGNGOAI \n" +
							"from ERP_CHIPHIKHAC_CHITIET cpct \n" +
							"     left join ERP_CHIPHIKHAC cp on cp.pk_seq = cpct.CHIPHIKHAC_FK \n" +
							"     left join ERP_THANHTOANHOADON_HOADON tt on tt.HOADON_FK = cpct.CHIPHIKHAC_FK AND TT.LOAIHD = 5 \n" +
							"     left join ERP_THANHTOANHOADON t on t.PK_SEQ = tt.THANHTOANHD_FK  \n" +
							"where cp.DOITUONG = '"+this.NhanvienId+"' and tt.THANHTOANHD_FK = '"+this.id+"' and cp.LOAI= '1'  \n";
				
					 
					}
					query += " UNION ALL \n"; 
					
					query += "select distinct 5 as LOAIHD, cp.pk_seq , cast(cp.PK_SEQ as nvarchar(50)) as sohoadon, cp.DIENGIAI as kyhieu,  cp.NGAY as ngayhoadon , (cpct.TONGTIENCHUATHUE + cpct.THUE ) as sotiengoc \n" +
							"	   ,case when (tt.SOTIENAVAT is null OR t.TRANGTHAI=2) then (cpct.TONGTIENCHUATHUE + cpct.THUE - isnull(xoatamung.dathanhtoan,0)) else tt.CONLAI end as sotienavat \n" +
							"	   ,'0' as sotientt, '' as POID , cp.TIENTE_FK as ttId, isnull(cp.TIGIA,1) as TIGIA, \n" +
							"      isnull(cp.ngaydenhantt,'') as ngaydenhantt, '' SOHOPDONG, '' SOHOPDONGNGOAI  \n" +
							"from ERP_CHIPHIKHAC_CHITIET cpct \n" +
							"     left join ERP_CHIPHIKHAC cp on cp.pk_seq = cpct.CHIPHIKHAC_FK\n" +
							"     left join ERP_THANHTOANHOADON_HOADON tt on tt.HOADON_FK = cpct.CHIPHIKHAC_FK AND TT.LOAIHD = 5 \n" +
							"     left join ERP_THANHTOANHOADON t on t.PK_SEQ = tt.THANHTOANHD_FK  \n" +
							"     left join (select a.cttt_fk,SUM(a.tienthanhtoan) as dathanhtoan \n" +
							"	         from ERP_XOAKHTRATRUOC_CTTT a inner join ERP_XOAKHTRATRUOC b on a.xoakhtratruoc_fk=b.PK_SEQ \n" +
							"	         where b.TRANGTHAI=1 and b.TIENTE_FK= "+ this.tienteId +" and b.LOAIXOATRATRUOC ='1' and isnull(b.NCC_FK, b.NHANVIEN_FK)= "+ this.NhanvienId +" and b.CONGTY_FK = "+this.ctyId+"  \n" +
							"	         group by a.cttt_fk) xoatamung  on xoatamung.cttt_fk = cp.PK_SEQ \n" +
							"where cp.DOITUONG = '"+this.NhanvienId+"' and cp.TIENTE_FK= "+ this.tienteId +" and ( tt.CONLAI is null or (tt.CONLAI > 0 \n" +
							"	   and tt.HOADON_FK not in \n" +
							"						(select distinct tt.HOADON_FK \n" +
							"						from ERP_CHIPHIKHAC_CHITIET cpct \n" +
							"						left join ERP_CHIPHIKHAC cp on cp.pk_seq = cpct.CHIPHIKHAC_FK \n" +
							"						left join ERP_THANHTOANHOADON_HOADON tt on tt.HOADON_FK = cpct.CHIPHIKHAC_FK AND TT.LOAIHD = 5 \n" +
							"						where cp.DOITUONG = '"+this.NhanvienId+"' and tt.CONLAI = 0 and cp.CONGTY_FK = "+this.ctyId+" ) \n" +
							"					  and tt.THANHTOANHD_FK in \n" +
							"						(select MAX(tt.THANHTOANHD_FK) \n" +
							"						from ERP_CHIPHIKHAC_CHITIET cpct   \n" +
							"							left join ERP_THANHTOANHOADON_HOADON tt on tt.HOADON_FK = cpct.CHIPHIKHAC_FK AND TT.LOAIHD = 5 \n" +
							"					 		left join ERP_THANHTOANHOADON t on  t.pk_seq = tt.thanhtoanhd_fk \n" +
							"							left join ERP_CHIPHIKHAC cp on cp.pk_seq = cpct.CHIPHIKHAC_FK\n" +
							"						 where cp.trangthai = 1    and  cp.DOITUONG =  '"+this.NhanvienId+"' and t.TRANGTHAI<>2	and cp.CONGTY_FK = "+this.ctyId+"  \n" +
							"						 group by tt.HOADON_FK ) ) )\n" +
							"					 and cp.trangthai = 1 \n ";
							
							if(this.DoiTuongChiPhiKhac.equals("0")){ // Neu la NV 
								query += " and cp.LOAI= '1' \n";
							}
							if(this.id.length() > 0)
							{
								query += " and cp.pk_seq not in (select HOADON_FK from ERP_THANHTOANHOADON_HOADON where THANHTOANHD_FK = "+ this.id +"  ) \n";
							}
							query += " and ((cpct.TONGTIENCHUATHUE + cpct.THUE - isnull(xoatamung.dathanhtoan,0) ) > 0 or tt.CONLAI > 0) \n";
					*/
					
					
				// LOAIHD: 6 - DENGHITHANHTOAN
				if(this.id.length() > 0){
					query += " UNION ALL \n" ;
					query += " select distinct 6 as LOAIHD, mh.PK_SEQ, cast(mh.SOPO as nvarchar(50)) as sohoadon,  N'Đề nghị thanh toán'  AS KYHIEU , mh.NGAYMUA as ngayhoadon ,  mh.TONGTIENAVAT as sotiengoc, \n" +
							"        CASE WHEN tthd.TIENTE_FK= '100000' THEN tt.SOTIENAVAT ELSE tt.SOTIENNT END as SOTIENAVAT, \n"+
							"		 tt.SOTIENTT , '' as POID ,mh.TIENTE_FK as ttId, mh.tygiaquydoi as TIGIA,  mh.ngaydenhantt, '' SOHOPDONG, '' SOHOPDONGNGOAI  \n"+
							" from ERP_MUAHANG mh \n" +
							" 	left join ERP_THANHTOANHOADON_HOADON tt on tt.HOADON_FK = mh.PK_SEQ and TT.LOAIHD = 6 \n"+
							" 	inner join ERP_THANHTOANHOADON tthd on tt.THANHTOANHD_FK = tthd.PK_SEQ  \n"+
							" 	inner join ERP_NHANVIEN nv on nv.PK_SEQ = mh.NHANVIEN_FK \n"+
							" where   mh.NHANVIEN_FK = '" + this.NhanvienId + "' and tt.THANHTOANHD_FK = '" +this.id+ "' \n";
																				
					}
				    query += " UNION ALL \n" ;						
					query += " select distinct 6 as LOAIHD,  mh.PK_SEQ, cast(mh.SOPO as nvarchar(50)) as sohoadon,  N'Đề nghị thanh toán'  AS KYHIEU , mh.NGAYMUA as ngayhoadon,  mh.TONGTIENAVAT as sotiengoc \n"+
							" 		, mh.TONGTIENAVAT  - isnull(DATT.SOTIENTT,0) as SOTIENAVAT ,0 as sotientt, '' as POID  \n"+
							" 		,mh.TIENTE_FK as ttId,  mh.tygiaquydoi as TIGIA, mh.ngaydenhantt, '' SOHOPDONG, '' SOHOPDONGNGOAI  \n"+
							" from ERP_MUAHANG mh " +
							" 		left join ERP_THANHTOANHOADON_HOADON tt on  tt.HOADON_FK = mh.PK_SEQ AND tt.LOAIHD = 6  \n"+
							" 		left join ERP_THANHTOANHOADON t on  t.pk_seq = tt.thanhtoanhd_fk  \n"+
							" 		inner join ERP_NHANVIEN nv on nv.PK_SEQ = mh.NHANVIEN_FK \n"+
							" 		left join \n"+
							"       ( \n"+
							"         SELECT tt.HOADON_FK , SUM(tt.SOTIENTT) as SOTIENTT "+
							"         FROM 	 ERP_THANHTOANHOADON_HOADON tt  \n"+
							"                INNER JOIN ERP_THANHTOANHOADON t on tt.THANHTOANHD_FK = t.PK_SEQ "+
							"         WHERE  tt.LOAIHD = 6 AND t.TRANGTHAI != 2  and t.NPP_FK = "+this.nppdangnhap+"  "+
							"         GROUP BY tt.HOADON_FK "+
							"        ) DATT ON DATT.HOADON_FK = mh.PK_SEQ \n"+
							" where mh.TRANGTHAI = 1 and  mh.NHANVIEN_FK = '" + this.NhanvienId + "' \n" +
							" 		and ( tt.CONLAI is null or  \n" +
							"           (tt.CONLAI > 0 \n"+
							"			and mh.TIENTE_FK = "+ this.tienteId +" \n"+
							" 			and tt.HOADON_FK not in \n"+
							"				(select distinct tt.HOADON_FK \n"+
							"				 from ERP_MUAHANG mh \n"+
							"				      left join ERP_THANHTOANHOADON_HOADON tt on  tt.HOADON_FK = mh.PK_SEQ  AND tt.LOAIHD = 6 \n"+
							"				      left join ERP_NHANVIEN nv on nv.PK_SEQ = mh.NHANVIEN_FK \n"+
							"				where mh.NPP_FK = "+this.nppdangnhap+"  and mh.TRANGTHAI = 1 and  mh.NHANVIEN_FK = '" + this.NhanvienId + "' and tt.CONLAI = 0 and mh.TIENTE_FK = "+ this.tienteId +") \n"+
							"  			and tt.THANHTOANHD_FK in \n"+
							"				(select MAX(tt.THANHTOANHD_FK) \n"+
							"				from Erp_MuaHang mh  \n"+
							"				    left join ERP_THANHTOANHOADON_HOADON tt on tt.HOADON_FK = mh.PK_SEQ AND tt.LOAIHD = 6 \n"+
							" 				    left join ERP_THANHTOANHOADON t on  t.pk_seq = tt.thanhtoanhd_fk \n"+
							"				    left join ERP_NHANVIEN nv on nv.PK_SEQ = mh.NHANVIEN_FK \n"+
							"				where mh.NPP_FK = "+this.nppdangnhap+" and mh.TRANGTHAI = 1 and mh.LOAIHANGHOA_FK = '2' and TYPE = '1'  and mh.TIENTE_FK = "+ this.tienteId +"  and  mh.NHANVIEN_FK =  '" + this.NhanvienId + "' and t.TRANGTHAI<>2	\n"+ //trang thai mh=2 da hoan tat
							"	 			group by tt.HOADON_FK ) ) ) \n"+
							" 		 and  mh.LOAIHANGHOA_FK = '2' and mh.TYPE ='1' and isnull(mh.ISDNTT,0) = '1' and mh.NPP_FK = "+this.nppdangnhap+" \n";
							if(this.id.length() > 0)
							{
								query+= " and mh.PK_SEQ not in (select HOADON_FK from  ERP_THANHTOANHOADON_HOADON where THANHTOANHD_FK = "+ this.id +" ) \n" ;
							}
					query+=	"       and  mh.TONGTIENAVAT  - isnull(DATT.SOTIENTT,0) > 0 \n";
									
				}
				
				
				
				// LOAD NHỮNG HD CỦA KHÁCH HÀNG
				if(this.khachhangId.trim().length() > 0)
				{/*					
					if(this.id.trim().length() > 0)
					{
						// LOAIHD: 7 - KHACHHANGTRATRUOC
					    query = "select distinct 7 as LOAIHD, tt.PK_SEQ, cast(tt.PK_SEQ as nvarchar(50)) as SOHOADON, N'Khách hàng trả trước' as KYHIEU, tt.NGAYCHUNGTU AS NGAYHOADON  , \n" +
					    		"       (select  CASE WHEN tt.TIENTE_FK <> '100000'  "+
					    		"		         THEN ISNULL(tt.THUDUOCNT,0) ELSE ISNULL(tt.THUDUOC,0) END   "+
					    		"		 from 	ERP_THUTIEN a where TRANGTHAI = '1' and NOIDUNGTT_FK = '100001' and KHACHHANG_FK = '"+ this.khachhangId +"' and a.pk_seq = tt.pk_seq ) AS SOTIENGOC,  \n"+
					    		"       CASE WHEN tt.TIENTE_FK <> '100000' THEN ttct.SOTIENNT ELSE ttct.SOTIENAVAT END AS SOTIENAVAT , \n" +
					    		"       ttct.SOTIENTT, '' AS POID, tt.TIENTE_FK AS TTID, ISNULL(tt.TIGIA,1) as TIGIA, '' as NGAYDENHANTT, '' SOHOPDONG, '' SOHOPDONGNGOAI  \n" +
					    		"from ERP_THUTIEN tt  inner join ERP_THANHTOANHOADON_HOADON ttct on ttct.HOADON_FK = tt.PK_SEQ AND ttct.LOAIHD = 7 \n"+
					    		"where tt.KHACHHANG_FK = '"+ this.khachhangId +"' and ttct.THANHTOANHD_FK = "+ this.id +" \n";
					    
					    query +=" UNION ALL \n";
					}
				
			        query +="select distinct 7 as LOAIHD, tt.PK_SEQ, cast(tt.PK_SEQ as nvarchar(50)) as SOHOADON, N'Khách hàng trả trước' as KYHIEU, tt.NGAYCHUNGTU AS NGAYHOADON , \n"+
							"       CASE WHEN tt.TIENTE_FK <> '100000'  THEN ISNULL(tt.THUDUOCNT,0)   \n"+
							"            ELSE  ISNULL(tt.THUDUOC,0)  END AS SOTIENGOC, \n"+
							"       CASE WHEN tt.TIENTE_FK <> '100000'  THEN (ISNULL(tt.THUDUOCNT,0)  - ISNULL(DAXOAKH.DAXOA,0) - ISNULL(DATHANHTOAN.DATT,0) ) \n"+
							"            ELSE  (ISNULL(tt.THUDUOC,0)  - ISNULL(DAXOAKH.DAXOAVND,0)- ISNULL(DATHANHTOAN.DATT,0) ) END AS SOTIENAVAT, \n"+
							"       0 as SOTIENTT, '' AS POID, tt.TIENTE_FK as TTID, ISNULL(tt.TIGIA,1) as TIGIA, \n"+
							"       '' as NGAYDENHANTT, '' SOHOPDONG, '' SOHOPDONGNGOAI \n"+
							"from ERP_THUTIEN tt left join \n"+
							"     ( select ct.cttt_fk , sum(tienthanhtoan) as DAXOA, sum(tienthanhtoan*ISNULL(ct.TIGIA,1)) as DAXOAVND \n"+
							"       from ERP_XOAKHTRATRUOC xkh inner join ERP_XOAKHTRATRUOC_CTTT ct on xkh.PK_SEQ = ct.xoakhtratruoc_fk \n"+
							"       where xkh.trangthai = '1' and loaixoatratruoc = 0 "+
							"       group by ct.cttt_fk \n" + 
							"      ) DAXOAKH on tt.PK_SEQ = DAXOAKH.cttt_fk \n"+
							"     left join \n" + 
							"     ( select ct.HOADON_FK, SUM(ct.SOTIENTT) AS DATT \n"+
							"       from ERP_THANHTOANHOADON_HOADON ct inner join ERP_THANHTOANHOADON tthd on ct.THANHTOANHD_FK = tthd.PK_SEQ "+
							"       where   tthd.TRANGTHAI != '2' AND tthd.KHACHHANG_FK = '" + this.khachhangId + "' "+
							"             and tthd.TIENTE_FK = '" + this.tienteId + "' and ct.LOAIHD = '7' \n"+
							"       group by ct.HOADON_FK  "+
							"     )DATHANHTOAN on  tt.PK_SEQ = DATHANHTOAN.HOADON_FK \n"+
							"where  tt.TRANGTHAI = '1' and tt.NOIDUNGTT_FK = '100001' and tt.KHACHHANG_FK = '"+ this.khachhangId + "' and tt.TIENTE_FK = '" + this.tienteId + "' \n"+
							"       and ISNULL(tt.THUDUOC,0)  - ISNULL(DAXOAKH.DAXOAVND,0) - (ISNULL(DATHANHTOAN.DATT,0)*ISNULL(tt.TIGIA,1) ) > 0 \n";
						// if(this.isNpp.equals("0")) query+= " AND tt.LOAIPHIEUTHU
						// IN (1,2) ";
						// if(this.isNpp.equals("1")) query+= " AND tt.LOAIPHIEUTHU
						// IN (0) ";
		
						if (this.id.trim().length() > 0) {
							query += "     and tt.PK_SEQ not in (select HOADON_FK from ERP_THANHTOANHOADON_HOADON where  THANHTOANHD_FK = '"+ this.id + "') ";
						}
				
        							 
				  if(this.id.length() > 0)
				  {
					  // LOAIHD: 6 - DENGHITHANHTOAN	
					  
					query += " UNION ALL \n"; 
					
					query += " select distinct 6 as LOAIHD, mh.PK_SEQ, cast(mh.SOPO as nvarchar(50)) as sohoadon,  N'Đề nghị thanh toán'  AS KYHIEU , mh.NGAYMUA as ngayhoadon , mh.TONGTIENAVAT as sotiengoc, \n" +
							"        CASE WHEN tthd.TIENTE_FK= '100000' THEN tt.SOTIENAVAT ELSE tt.SOTIENNT END as SOTIENAVAT, \n"+
							"		 tt.SOTIENTT , '' as POID ,mh.TIENTE_FK as ttId, mh.tygiaquydoi as TIGIA, mh.ngaydenhantt, '' SOHOPDONG, '' SOHOPDONGNGOAI \n"+
							" from ERP_MUAHANG mh \n" +
							" 	inner join ERP_THANHTOANHOADON_HOADON tt on tt.HOADON_FK = mh.PK_SEQ  AND TT.LOAIHD = 6 \n"+
							" 	inner join ERP_THANHTOANHOADON tthd on tt.THANHTOANHD_FK = tthd.PK_SEQ  \n"+
							" 	inner join KHACHHANG kh on kh.PK_SEQ = mh.KHACHHANG_FK \n"+
							" where   mh.KHACHHANG_FK = '" + this.khachhangId + "' and tt.THANHTOANHD_FK = '" +this.id+ "'  \n";																			
									
				  }
					query += " UNION ALL \n"; 	
					
					query += " select distinct 6 as LOAIHD, mh.PK_SEQ, cast(mh.SOPO as nvarchar(50)) as sohoadon,  N'Đề nghị thanh toán'  AS KYHIEU , mh.NGAYMUA as ngayhoadon, mh.TONGTIENAVAT as sotiengoc \n"+
							 " 		, mh.TONGTIENAVAT - isnull(DATT.SOTIENTT,0)   as SOTIENAVAT ,0 as sotientt, '' as POID  \n"+
						     " 		,mh.TIENTE_FK as ttId, mh.tygiaquydoi as TIGIA,  mh.ngaydenhantt, '' SOHOPDONG, '' SOHOPDONGNGOAI  \n"+
						     " from ERP_MUAHANG mh \n"+	
						     " 	INNER JOIN KHACHHANG kh on kh.PK_SEQ = mh.KHACHHANG_FK \n"+
						     " 	LEFT JOIN \n"+
						     "       ( \n"+
						     "         SELECT tt.HOADON_FK , SUM(tt.SOTIENTT) as SOTIENTT \n"+
						     "         FROM ERP_THANHTOANHOADON_HOADON tt  \n"+
						     "              INNER JOIN ERP_THANHTOANHOADON t on tt.THANHTOANHD_FK = t.PK_SEQ \n"+
						     "         WHERE tt.LOAIHD = 6 AND t.TRANGTHAI != 2 and t.CONGTY_FK  = " + this.ctyId +" \n"+ 
						     "         GROUP BY tt.HOADON_FK \n"+
						     "        ) DATT ON DATT.HOADON_FK = mh.PK_SEQ \n"+
						     " 		LEFT JOIN ERP_THANHTOANHOADON_HOADON tt on  tt.HOADON_FK = mh.PK_SEQ AND TT.LOAIHD = 6 \n"+
						     " 		LEFT JOIN ERP_THANHTOANHOADON t on  t.pk_seq = tt.thanhtoanhd_fk  \n"+							
						     " where mh.CONGTY_FK = " + this.ctyId +" \n"+
						     " AND mh.TRANGTHAI = 1 and mh.KHACHHANG_FK = '"+ this.khachhangId + "' \n"+
						     " 		and ( tt.CONLAI is null or  \n"+
						     "           	(tt.CONLAI > 0 and mh.TIENTE_FK = " + this.tienteId + " \n"+
						     " 				and tt.HOADON_FK not in \n"+
						     "				(select distinct tt.HOADON_FK \n"+
						     "				 from ERP_MUAHANG mh \n"+
						     "				      left join ERP_THANHTOANHOADON_HOADON tt on  tt.HOADON_FK = mh.PK_SEQ AND TT.LOAIHD = 6 \n"+
						     "				      left join KHACHHANG kh on kh.PK_SEQ = mh.KHACHHANG_FK \n"+
						     "				where mh.TRANGTHAI = 1 and mh.KHACHHANG_FK = '" + this.khachhangId +"' \n"+
						     " 				and tt.CONLAI = 0 and mh.TIENTE_FK = " + this.tienteId + " and mh.CONGTY_FK = "+ this.ctyId + " ) \n"+
						     "  			and tt.THANHTOANHD_FK in \n"+
						     "				(select MAX(tt.THANHTOANHD_FK) \n"+ 
						     "				 from Erp_MuaHang mh  \n"+
						     "				    left join ERP_THANHTOANHOADON_HOADON tt on tt.HOADON_FK = mh.PK_SEQ AND TT.LOAIHD = 6 \n"+
						     " 				    left join ERP_THANHTOANHOADON t on  t.pk_seq = tt.thanhtoanhd_fk \n"+
						     "				    left join KHACHHANG kh on kh.PK_SEQ = mh.KHACHHANG_FK \n"+
						     "				where mh.TRANGTHAI = 1 and mh.LOAIHANGHOA_FK = '2' and TYPE = '1' and mh.TIENTE_FK = "+ this.tienteId + " \n"+ 
						     "  			and  mh.KHACHHANG_FK =  '" + this.khachhangId+ "' and t.TRANGTHAI<>2 and mh.CONGTY_FK = " + this.ctyId + " 	\n"+
						     "	 			group by tt.HOADON_FK ) ) ) \n"+
						     " 		and  mh.LOAIHANGHOA_FK = '2' and mh.TYPE ='1' and isnull(mh.ISDNTT,0) = '1' \n";
							if (this.id.length() > 0) {
								query += " and mh.PK_SEQ not in (select HOADON_FK from  ERP_THANHTOANHOADON_HOADON where THANHTOANHD_FK = "	+ this.id + ") \n";
							}
				query += "       and  (mh.TONGTIENAVAT - isnull(t.SOTIENTT,0)   ) > 0 \n";
				
				
				// HÓA ĐƠN HÀNG TRẢ VỀ
				if(this.id.length() > 0)
				{
					query += " UNION ALL \n"; 
					query += 
					"	select distinct 8 as LOAIHD, hd.PK_SEQ, hd.SOHOADON as sohoadon,  N'Hóa đơn hàng trả về'  AS KYHIEU , hd.NGAYXUATHD as ngayhoadon , \n"+
					"	 HD.TONGTIENAVAT as sotiengoc,  tt.SOTIENAVAT SOTIENAVAT, \n"+
					"	 tt.SOTIENTT , '' as POID , isnull(hd.TIENTE_FK, 100000) as ttId, isnull(hd.TYGIA,1) as TIGIA, '' ngaydenhantt, '' SOHOPDONG, '' SOHOPDONGNGOAI \n"+
					" from ERP_HOADON hd \n"+
					"	inner join ERP_THANHTOANHOADON_HOADON tt on tt.HOADON_FK = hd.PK_SEQ  AND TT.LOAIHD = 8 \n"+
					"	inner join ERP_THANHTOANHOADON tthd on tt.THANHTOANHD_FK = tthd.PK_SEQ  \n"+
					"	inner join KHACHHANG kh on kh.PK_SEQ = hd.KHACHHANG_FK \n"+
					
					"  LEFT JOIN ( \n"+
					"  SELECT HOADON_FK, SUM(ISNULL(DATHANHTOAN, 0)) as DATHANHTOAN  \n"+
					"  FROM  \n"+
					"  (  \n"+ 
					" 		SELECT TTHD.HOADON_FK , sum(TTHD.SOTIENTT) as DATHANHTOAN  \n"+  
					" 		FROM ERP_XOAKHTRATRUOC_HOADON TTHD \n"+
					" 		INNER JOIN ERP_XOAKHTRATRUOC TT on TTHD.XOAKHTRATRUOC_FK = TT.PK_SEQ  \n"+
					" 		WHERE TT.TRANGTHAI != 2 AND TTHD.LOAIHD = '7' and TTHD.KHACHHANG_FK = "+this.khachhangId+" and isnull(TT.isNPP,0) = 0 \n"+ 
					" 		group by HOADON_FK  \n"+
	
					" 		UNION ALL    \n"+
	
					" 		SELECT TTHD.HOADON_FK , sum(TTHD.SOTIENTT) as DATHANHTOAN   \n"+
					" 		FROM ERP_THUTIEN_HOADON TTHD \n"+
					" 		INNER JOIN ERP_THUTIEN TT on TTHD.THUTIEN_FK = TT.PK_SEQ  \n"+
					" 		WHERE  TT.TRANGTHAI != 2 AND TTHD.LOAIHOADON = '7' and isnull(TTHD.isNPP,0) = 0 AND TT.BANGKE_FK IS NULL  \n"+
					" 		group by HOADON_FK  \n"+
	
					" 		UNION ALL  \n"+
	
					" 		SELECT TTHD.HOADON_FK , sum(TTHD.SOTIENTT) as DATHANHTOAN   \n"+
					" 		FROM ERP_THUTIEN_HOADON TTHD \n"+
					" 		INNER JOIN ERP_THUTIEN TT on TTHD.THUTIEN_FK = TT.PK_SEQ \n"+ 
					" 		WHERE  TT.TRANGTHAI != 2 AND TTHD.LOAIHOADON = '7' and isnull(TTHD.isNPP,0) = 0 AND TT.BANGKE_FK IS NOT NULL \n"+
					" 		group by HOADON_FK  \n"+
	
					" 		UNION ALL  \n"+
					 
					" 		select TTHD.HOADON_FK , sum(TTHD.XOANO) as DATHANHTOAN \n"+   
					" 		from ERP_BUTRUKHACHHANG_CHITIET TTHD inner join ERP_BUTRUKHACHHANG TT on TTHD.BTKH_FK = TT.PK_SEQ  \n"+ 
					" 		where  TT.TRANGTHAI != 2 AND TTHD.LOAIHD = '7' and isnull(TT.isNPP,0) = 0 \n"+
					" 		group by HOADON_FK \n"+
							
					" 		UNION ALL  \n"+
							
					" 		select ct.HOADON_FK, SUM(ct.SOTIENTT) AS DATT \n"+
					" 	    from ERP_THANHTOANHOADON_HOADON ct inner join ERP_THANHTOANHOADON tthd on ct.THANHTOANHD_FK = tthd.PK_SEQ  \n"+  
					" 	    where   tthd.TRANGTHAI != '2' \n"+            
					" 	    and  ct.LOAIHD = '8' AND TTHD.PK_SEQ != "+ (this.id.length() <= 0 ? 0 : this.id) +
					" 	    group by ct.HOADON_FK \n"+		       
	
					" 	)HOADONDATT  group by HOADON_FK  \n"+								
					"  )dathanhtoan on hd.pk_seq = dathanhtoan.hoadon_fk    \n"+
											 
					" where   hd.KHACHHANG_FK = '"+this.khachhangId+"' and tt.THANHTOANHD_FK = '"+this.id+"' \n";
					
				}
				
				query += " UNION ALL \n"+
				
						 " SELECT 	distinct 8 as LOAIHD, hd.PK_SEQ, hd.SOHOADON as SOHOADON, N'Hóa đơn hàng trả về' as KYHIEU, hd.NGAYXUATHD AS NGAYHOADON , \n"+
					     "  		hd.TONGTIENAVAT SOTIENGOC, \n"+ 
					     " 			(ISNULL(hd.TONGTIENAVAT,0)  - ISNULL(dathanhtoan.DATHANHTOAN,0) ) SOTIENAVAT, \n"+
					     "  		0 as SOTIENTT, '' AS POID, isnull(hd.TIENTE_FK,10000) as TTID, ISNULL(hd.TYGIA,1) as TIGIA, \n"+
					     "  		'' as NGAYDENHANTT, '' SOHOPDONG, '' SOHOPDONGNGOAI \n"+
					     " FROM ERP_HOADON hd \n"+
					     " LEFT JOIN ( \n"+
					     " SELECT HOADON_FK, SUM(ISNULL(DATHANHTOAN, 0)) as DATHANHTOAN  \n"+
					     " FROM  \n"+
						 " ( \n"+  
						 "		SELECT TTHD.HOADON_FK , sum(TTHD.SOTIENTT) as DATHANHTOAN    \n"+
						 "		FROM ERP_XOAKHTRATRUOC_HOADON TTHD \n"+
						 "		INNER JOIN ERP_XOAKHTRATRUOC TT on TTHD.XOAKHTRATRUOC_FK = TT.PK_SEQ  \n"+
						 "		WHERE TT.TRANGTHAI != 2 AND TTHD.LOAIHD = '7' and TTHD.KHACHHANG_FK = "+this.khachhangId+" and isnull(TT.isNPP,0) = 0 \n"+
						 "		group by HOADON_FK  \n"+
						
						 "		UNION ALL \n"+   
	
						 "		SELECT TTHD.HOADON_FK , sum(TTHD.SOTIENTT) as DATHANHTOAN   \n"+
						 "		FROM ERP_THUTIEN_HOADON TTHD \n"+
						 "		INNER JOIN ERP_THUTIEN TT on TTHD.THUTIEN_FK = TT.PK_SEQ  \n"+
						 "		WHERE  TT.TRANGTHAI != 2 AND TTHD.LOAIHOADON = '7' and isnull(TTHD.isNPP,0) = 0 AND TT.BANGKE_FK IS NULL \n"+ 
						 "		group by HOADON_FK  \n"+
					
						 "		UNION ALL \n"+ 
					
						 "		SELECT TTHD.HOADON_FK , sum(TTHD.SOTIENTT) as DATHANHTOAN   \n"+
						 "		FROM ERP_THUTIEN_HOADON TTHD \n"+
						 "		INNER JOIN ERP_THUTIEN TT on TTHD.THUTIEN_FK = TT.PK_SEQ  \n"+
						 "		WHERE  TT.TRANGTHAI != 2 AND TTHD.LOAIHOADON = '7' and isnull(TTHD.isNPP,0) = 0 AND TT.BANGKE_FK IS NOT NULL \n"+
						 "		group by HOADON_FK  \n"+
					
						 "		UNION ALL \n"+ 
						 
						 "		select TTHD.HOADON_FK , sum(TTHD.XOANO) as DATHANHTOAN    \n"+
						 "		from ERP_BUTRUKHACHHANG_CHITIET TTHD inner join ERP_BUTRUKHACHHANG TT on TTHD.BTKH_FK = TT.PK_SEQ \n"+  
						 "		where  TT.TRANGTHAI != 2 AND TTHD.LOAIHD = '7' and isnull(TT.isNPP,0) = 0 \n"+
						 "		group by HOADON_FK \n"+
								
						 "		UNION ALL \n"+ 
								
						 "		select ct.HOADON_FK, SUM(ct.SOTIENTT) AS DATT \n"+
						 "	    from ERP_THANHTOANHOADON_HOADON ct inner join ERP_THANHTOANHOADON tthd on ct.THANHTOANHD_FK = tthd.PK_SEQ    \n"+    
						 "	    where   tthd.TRANGTHAI != '2'     \n"+        
						 "	    and tthd.TIENTE_FK = '"+this.tienteId+"' and ct.LOAIHD = '8' AND TTHD.PK_SEQ != "+ (this.id.length() <= 0 ? 0 : this.id) +
						 "	    group by ct.HOADON_FK  \n"+				       
					
						 "	)HOADONDATT  group by HOADON_FK  	\n"+							
						 " )dathanhtoan on hd.pk_seq = dathanhtoan.hoadon_fk     \n"+
						 " where  hd.TRANGTHAI = '1' and hd.KHACHHANG_FK = '"+this.khachhangId+"' AND HD.LOAIHOADON = 2  AND (ISNULL(hd.TONGTIENAVAT,0)  - ISNULL(dathanhtoan.DATHANHTOAN,0) ) !=0 ";
						 if (this.id.trim().length() > 0) {
								query += "       and hd.PK_SEQ not in (select HOADON_FK from ERP_THANHTOANHOADON_HOADON where  THANHTOANHD_FK = '"+ this.id + "') ";
						  }  
				
				*/}
				
				if(this.bophanId.trim().length() > 0)
				{
					
					// LOAIHD: 6 - DENGHITHANHTOAN (NV)
					
					if(this.id.length() > 0){
						query +=" select distinct '0' as DOITUONG ,nv.PK_SEQ as DOITUONG_FK, nv.MA as MADOITUONG , 6 as LOAIHD, mh.PK_SEQ, cast(mh.SOPO as nvarchar(50)) as sohoadon,  N'Đề nghị thanh toán'  AS KYHIEU , mh.NGAYMUA as ngayhoadon , mh.TONGTIENAVAT as sotiengoc, \n" +
								"        CASE WHEN tthd.TIENTE_FK= '100000' THEN tt.SOTIENAVAT ELSE tt.SOTIENNT END as SOTIENAVAT, \n"+
								"		 tt.SOTIENTT , '' as POID ,mh.TIENTE_FK as ttId, mh.tygiaquydoi as TIGIA,  mh.ngaydenhantt, '' SOHOPDONG, '' SOHOPDONGNGOAI  \n"+
								" from ERP_MUAHANG mh \n" +
								" 	inner join ERP_THANHTOANHOADON_HOADONBOPHAN tt on tt.HOADON_FK = mh.PK_SEQ and TT.LOAIHD = 6 \n"+
								" 	inner join ERP_THANHTOANHOADON tthd on tt.THANHTOANHD_FK = tthd.PK_SEQ  \n"+
								" 	inner join ERP_NHANVIEN nv on nv.PK_SEQ = mh.NHANVIEN_FK \n"+
								" where   tt.NHANVIEN_FK is not null and tt.THANHTOANHD_FK = '" +this.id+ "' \n";
															
						query += " UNION ALL \n" ;
							
						}
					    						
						query += " select distinct '0' as DOITUONG ,nv.PK_SEQ as DOITUONG_FK, nv.MA as MADOITUONG , 6 as LOAIHD,  mh.PK_SEQ, cast(mh.SOPO as nvarchar(50)) as sohoadon,  N'Đề nghị thanh toán'  AS KYHIEU , mh.NGAYMUA as ngayhoadon, mh.TONGTIENAVAT as sotiengoc \n"+
								" 		, mh.TONGTIENAVAT  - isnull(DATHANHTOAN.DATHANHTOAN,0) - isnull(DATHANHTOANBP.DATHANHTOAN,0) as SOTIENAVAT ,0 as sotientt, '' as POID  \n"+
								" 		,mh.TIENTE_FK as ttId,  mh.tygiaquydoi as TIGIA, mh.ngaydenhantt, '' SOHOPDONG, '' SOHOPDONGNGOAI  \n"+
								" from ERP_MUAHANG mh " +
								" 		inner join ERP_NHANVIEN nv on nv.PK_SEQ = mh.NHANVIEN_FK \n"+
								" 		left join \n"+
								"       ( \n"+
								"        SELECT t.NHANVIEN_FK, tt.HOADON_FK, SUM(tt.SOTIENTT) as DATHANHTOAN \n"+
								"        FROM ERP_THANHTOANHOADON_HOADON tt  \n"+
								" 		      inner join ERP_THANHTOANHOADON t on  t.pk_seq = tt.thanhtoanhd_fk  \n"+
								"        WHERE t.NHANVIEN_FK is not null  and t.TRANGTHAI != 2 AND tt.LOAIHD = 6  "+
								"        GROUP BY t.NHANVIEN_FK, tt.HOADON_FK \n"+
							    "        ) DATHANHTOAN ON mh.PK_SEQ = DATHANHTOAN.HOADON_FK AND mh.NHANVIEN_FK = DATHANHTOAN.NHANVIEN_FK  \n"+
							    " 		left join \n"+
								"       ( \n"+
								"        SELECT tt.NHANVIEN_FK, tt.HOADON_FK, SUM(tt.SOTIENTT) as DATHANHTOAN \n"+
								"        FROM ERP_THANHTOANHOADON_HOADONBOPHAN tt   \n"+
								" 		      inner join ERP_THANHTOANHOADON t on  t.pk_seq = tt.thanhtoanhd_fk  \n"+
								"        WHERE tt.NHANVIEN_FK is not null  and t.TRANGTHAI != 2 ";
							if(this.id.trim().length() > 0)
							{
								query += " and tt.THANHTOANHD_FK  != "+ this.id +"  ";
							}
						query +="        GROUP BY tt.NHANVIEN_FK, tt.HOADON_FK \n"+
							    "        ) DATHANHTOANBP ON mh.PK_SEQ = DATHANHTOANBP.HOADON_FK AND mh.NHANVIEN_FK = DATHANHTOANBP.NHANVIEN_FK  \n"+							    
								" where  mh.NPP_FK = "+this.nppdangnhap+"  and mh.TONGTIENAVAT  - isnull(DATHANHTOAN.DATHANHTOAN,0) - isnull(DATHANHTOANBP.DATHANHTOAN,0) >0 and mh.TRANGTHAI = 1 and  mh.NHANVIEN_FK is not null and mh.DONVITHUCHIEN_FK = "+ this.bophanId +" \n";
							 if(this.id.trim().length()>0){
								 query+= " and mh.PK_SEQ not in (select HOADON_FK from ERP_THANHTOANHOADON_HOADONBOPHAN where THANHTOANHD_FK = "+this.id+" ) \n";
							 }
						 query += 		" order by nv.PK_SEQ ";
				}
			
				if(this.DoiTuongChiPhiKhac.equals("5"))
				{
					query = "";
					if(this.khachhangId.trim().length() >0 )
					{
						if(this.id.trim().length()>0)
						{
							query = // TÍCH LŨY CẦN DUYỆT
								" SELECT distinct 8 as LOAIHD,PK_SEQ , SCHEME sohoadon, SCHEME AS KYHIEU, ngaykyhd ngayhoadon, tonggiatri as sotiengoc, \n"+
								" 		(giatritra - tonggiatri - isnull(DATHANHTOAN.DATT,0)) as SOTIENAVAT , isnull(TICHLUY.DATT,0) as sotientt, '' as POID, \n"+
								"        100000 as ttId, 1 as TIGIA, ngayketthuchd ngaydenhantt, sohopdong SOHOPDONG, '' SOHOPDONGNGOAI \n"+
								" FROM \n"+ 
								" ( \n"+
								"	SELECT DKKMTICHLUY_FK PK_SEQ, c.SCHEME , doanhsoDAT, donvitra, sanphamtra, soluongtra, giatritra, a.ngaykyhd, a.ngayketthuchd, \n"+
								"		   a.sohopdong, \n"+
								"		   ISNULL( ( SELECT SUM(TONGGIATRI)  FROM ERP_DONDATHANGNPP_TICHLUY_TRATL \n"+
								"				  	 WHERE DKKMID = a.DKKMTICHLUY_FK \n"+
								"				  	 AND DONDATHANGID in ( SELECT PK_SEQ FROM ERP_DONDATHANGNPP  \n"+
								"										   WHERE KHACHHANG_FK = '"+this.khachhangId+"' and trangthai != 3 ) ) , 0) as tonggiatri \n"+
								"	FROM DANGKYKM_TICHLUY_KHACHHANG a \n"+
								"		INNER JOIN DANGKYKM_TICHLUY b on a.DKKMTICHLUY_FK = b.PK_SEQ \n"+
								"		INNER join TIEUCHITHUONGTL c on b.TIEUCHITL_FK = c.PK_SEQ \n"+		
								"	WHERE KHACHHANG_FK = '"+this.khachhangId+"' and DUYET = '1' and MucDat is not null \n"+
								"		and c.TRANGTHAI = '1' and c.khongcanduyettra = '0'  \n"+
								"		and donvitra != 2 \n"+
								" ) \n"+
								" DATA \n"+
								" LEFT JOIN \n"+
								" ( SELECT ct.HOADON_FK, SUM(ct.SOTIENTT) AS DATT \n"+
								"   FROM ERP_THANHTOANHOADON_HOADON ct inner join ERP_THANHTOANHOADON tthd on ct.THANHTOANHD_FK = tthd.PK_SEQ \n"+   
								"   where   tthd.TRANGTHAI != '2' AND tthd.KHACHHANG_FK = '"+this.khachhangId+"' and tthd.NPP_FK = " +this.nppdangnhap+
								"   and tthd.TIENTE_FK = '100000' and ct.LOAIHD = '8' and tthd.PK_SEQ != "+this.id +" \n"+
								"   GROUP BY ct.HOADON_FK \n"+
								" )DATHANHTOAN on  DATA.PK_SEQ = DATHANHTOAN.HOADON_FK \n"+	
								" INNER JOIN \n"+
								" ( SELECT ct.HOADON_FK, SUM(ct.SOTIENTT) AS DATT \n"+
								"   FROM ERP_THANHTOANHOADON_HOADON ct inner join ERP_THANHTOANHOADON tthd on ct.THANHTOANHD_FK = tthd.PK_SEQ \n"+   
								"   where   tthd.TRANGTHAI != '2' AND tthd.KHACHHANG_FK = '"+this.khachhangId+"' and tthd.NPP_FK = " +this.nppdangnhap+
								"   and tthd.TIENTE_FK = '100000' and ct.LOAIHD = '8' and tthd.PK_SEQ = "+this.id +" \n"+
								"   GROUP BY ct.HOADON_FK \n"+
								" )TICHLUY on  DATA.PK_SEQ = TICHLUY.HOADON_FK \n"+								
								" where ( giatritra - tonggiatri - isnull(DATHANHTOAN.DATT,0)) > 0  \n";
							
						// TÍCH LŨY KHÔNG CẦN DUYỆT
						query += " UNION ALL \n"+
						 		 " SELECT distinct 9 as LOAIHD, PK_SEQ, DATA.SCHEME SOHOADON, DATA.SCHEME AS KYHIEU , DATA.ngaykyhd ngayhoadon, giatritra as sotiengoc, \n"+
						 		 "		  (giatritra - tonggiatri - isnull(DATHANHTOAN.DATT,0)) as SOTIENAVAT, TICHLUY.DATT as sotientt, '' as POID, \n"+
						 		 " 		  100000 as ttId, 1 as TIGIA, ngayketthuchd ngaydenhantt, sohopdong SOHOPDONG, '' SOHOPDONGNGOAI \n"+
						 		 " FROM \n"+  
						 		 "	(  \n"+
								 "	SELECT 	DT.DKKMTICHLUY_FK PK_SEQ, DT.doanhsoDAT, tc.donvi as donvitra, DT.sohopdong, DT.ngaykyhd, DT.ngayketthuchd, DT.SCHEME, \n"+
								 "			( case tc.donvi when 0 then round( tc.chietkhau * DT.doanhsoDAT / 100.0, 0 ) when 1 then tc.chietkhau else 0 end ) as giatritra, \n"+
								 "			ISNULL( ( select SUM(TONGGIATRI) from ERP_DONDATHANGNPP_TICHLUY_TRATL where DKKMID = DT.DKKMTICHLUY_FK and DONDATHANGID in ( select PK_SEQ from ERP_DONDATHANGNPP where KHACHHANG_FK = '"+this.khachhangId+"' and trangthai != 3 ) ) , 0) as tonggiatri \n"+ 
								 "	FROM \n"+
								 "	( \n"+
								 "		SELECT	DKKMTICHLUY_FK, c.PK_SEQ,  \n"+
								 "				ISNULL ( ( \n"+
								 "				select SUM( dh_sp.soluong * round( ( dongiaGOC * ( 1 + thueVAT / 100.0 ) ), 0 ) ) as tonggiatri \n"+
								 "				from ERP_DONDATHANGNPP dh inner join ERP_DONDATHANGNPP_SANPHAM dh_sp on dh.PK_SEQ = dh_sp.dondathang_fk  \n"+
								 "				where dh.KHACHHANG_FK = a.KHACHHANG_FK and dh.CS_DUYET = '1' and dh.TRANGTHAI not in ( 0, 3 ) and dh.NgayDonHang >= c.ngayds_tungay and dh.NgayDonHang <= c.ngayds_denngay \n"+
								 "		 				and dh_sp.sanpham_fk in ( select sanpham_fk from TIEUCHITHUONGTL_SANPHAM where thuongtl_fk = c.PK_SEQ ) \n"+
								 "				), 0 ) doanhsoDAT , a.sohopdong,a.ngaykyhd, a.ngayketthuchd , c.SCHEME \n"+
								 "		from DANGKYKM_TICHLUY_KHACHHANG a inner join DANGKYKM_TICHLUY b on a.DKKMTICHLUY_FK = b.PK_SEQ \n"+
								 "		inner join TIEUCHITHUONGTL c on b.TIEUCHITL_FK = c.PK_SEQ \n"+
								 "		where KHACHHANG_FK = '"+this.khachhangId+"' and c.TRANGTHAI = '1' and c.khongcanduyettra = '1'  \n"+ 
								 "	) \n"+
								 "	DT inner join TIEUCHITHUONGTL_TIEUCHI tc on DT.PK_SEQ = tc.thuongtl_fk \n"+
								 "	where tc.tumuc <= DT.doanhsoDAT and DT.doanhsoDAT <= tc.denmuc and tc.donvi != 2 \n"+
								 " )  \n"+
								 " DATA  \n"+
								 " LEFT JOIN \n"+
								 " ( SELECT ct.HOADON_FK, SUM(ct.SOTIENTT) AS DATT \n"+
								 "   FROM ERP_THANHTOANHOADON_HOADON ct inner join ERP_THANHTOANHOADON tthd on ct.THANHTOANHD_FK = tthd.PK_SEQ \n"+   
								 "   where   tthd.TRANGTHAI != '2' AND tthd.KHACHHANG_FK = '"+this.khachhangId+"' and tthd.NPP_FK = " +this.nppdangnhap+
								 "   and tthd.TIENTE_FK = '100000' and ct.LOAIHD = '9' and tthd.PK_SEQ != "+this.id +" \n"+
								 "   GROUP BY ct.HOADON_FK \n"+
								 " )DATHANHTOAN on  DATA.PK_SEQ = DATHANHTOAN.HOADON_FK \n"+	
								 " INNER JOIN \n"+
								 " ( SELECT ct.HOADON_FK, SUM(ct.SOTIENTT) AS DATT \n"+
								 "   FROM ERP_THANHTOANHOADON_HOADON ct inner join ERP_THANHTOANHOADON tthd on ct.THANHTOANHD_FK = tthd.PK_SEQ \n"+   
								 "   where   tthd.TRANGTHAI != '2' AND tthd.KHACHHANG_FK = '"+this.khachhangId+"' and tthd.NPP_FK = " +this.nppdangnhap+
								 "   and tthd.TIENTE_FK = '100000' and ct.LOAIHD = '9' and tthd.PK_SEQ = "+this.id +" \n"+
								 "   GROUP BY ct.HOADON_FK \n"+
								 " )TICHLUY on  DATA.PK_SEQ = TICHLUY.HOADON_FK \n"+								
								 " where ( giatritra - tonggiatri - isnull(DATHANHTOAN.DATT,0)) > 0  \n";
								
							
						query += " UNION ALL \n";
						}
						
						query += // TÍCH LŨY CẦN DUYỆT
								" SELECT distinct 8 as LOAIHD,PK_SEQ , SCHEME sohoadon, SCHEME AS KYHIEU, ngaykyhd ngayhoadon, tonggiatri as sotiengoc, \n"+
								" 		(giatritra - tonggiatri - isnull(DATHANHTOAN.DATT,0)) as SOTIENAVAT , 0 as sotientt, '' as POID, \n"+
								"        100000 as ttId, 1 as TIGIA, ngayketthuchd ngaydenhantt, sohopdong SOHOPDONG, '' SOHOPDONGNGOAI \n"+
								" FROM \n"+ 
								" ( \n"+
								"	SELECT DKKMTICHLUY_FK PK_SEQ, c.SCHEME , doanhsoDAT, donvitra, sanphamtra, soluongtra, giatritra, a.ngaykyhd, a.ngayketthuchd, \n"+
								"		   a.sohopdong, \n"+
								"		   ISNULL( ( SELECT SUM(TONGGIATRI)  FROM ERP_DONDATHANGNPP_TICHLUY_TRATL \n"+
								"				  	 WHERE DKKMID = a.DKKMTICHLUY_FK \n"+
								"				  	 AND DONDATHANGID in ( SELECT PK_SEQ FROM ERP_DONDATHANGNPP  \n"+
								"										   WHERE KHACHHANG_FK = '"+this.khachhangId+"' and trangthai != 3 ) ) , 0) as tonggiatri \n"+
								"	FROM DANGKYKM_TICHLUY_KHACHHANG a \n"+
								"		INNER JOIN DANGKYKM_TICHLUY b on a.DKKMTICHLUY_FK = b.PK_SEQ \n"+
								"		INNER join TIEUCHITHUONGTL c on b.TIEUCHITL_FK = c.PK_SEQ \n"+		
								"	WHERE KHACHHANG_FK = '"+this.khachhangId+"' and DUYET = '1' and MucDat is not null \n"+
								"		and c.TRANGTHAI = '1' and c.khongcanduyettra = '0'  \n"+
								"		and donvitra != 2 \n"+
								" ) \n"+
								" DATA \n"+
								" LEFT JOIN \n"+
								" ( SELECT ct.HOADON_FK, SUM(ct.SOTIENTT) AS DATT \n"+
								"   FROM ERP_THANHTOANHOADON_HOADON ct inner join ERP_THANHTOANHOADON tthd on ct.THANHTOANHD_FK = tthd.PK_SEQ \n"+   
								"   where   tthd.TRANGTHAI != '2' AND tthd.KHACHHANG_FK = '"+this.khachhangId+"' and tthd.NPP_FK = " +this.nppdangnhap+
								"   and tthd.TIENTE_FK = '100000' and ct.LOAIHD = '8' and tthd.PK_SEQ != "+(this.id.trim().length() <=0 ? "0": this.id )+" \n"+
								"   GROUP BY ct.HOADON_FK \n"+
								" )DATHANHTOAN on  DATA.PK_SEQ = DATHANHTOAN.HOADON_FK \n"+
								" where ( giatritra - tonggiatri - isnull(DATHANHTOAN.DATT,0)) > 0 \n ";
						
								if(this.id.trim().length()>0)
								{
									query += " and DATA.PK_SEQ NOT IN (SELECT ct.HOADON_FK FROM ERP_THANHTOANHOADON_HOADON ct inner join ERP_THANHTOANHOADON tthd on ct.THANHTOANHD_FK = tthd.PK_SEQ " +
											 "						   WHERE tthd.TRANGTHAI != '2' AND tthd.KHACHHANG_FK = '"+this.khachhangId+"' and tthd.NPP_FK = " +this.nppdangnhap+ " \n" +
											 "						   and tthd.TIENTE_FK = '100000' and ct.LOAIHD = '8' and tthd.PK_SEQ = "+ this.id +" ) \n";
								}
							
						// TÍCH LŨY KHÔNG CẦN DUYỆT
						query += " UNION ALL \n"+
						 		 " SELECT distinct 9 as LOAIHD, PK_SEQ, DATA.SCHEME SOHOADON, DATA.SCHEME AS KYHIEU , DATA.ngaykyhd ngayhoadon, giatritra as sotiengoc, \n"+
						 		 "		  (giatritra - tonggiatri - isnull(DATHANHTOAN.DATT,0)) as SOTIENAVAT, 0 as sotientt, '' as POID, \n"+
						 		 " 		  100000 as ttId, 1 as TIGIA, ngayketthuchd ngaydenhantt, sohopdong SOHOPDONG, '' SOHOPDONGNGOAI \n"+
						 		 " FROM \n"+  
						 		 "	(  \n"+
								 "	SELECT 	DT.DKKMTICHLUY_FK PK_SEQ, DT.doanhsoDAT, tc.donvi as donvitra, DT.sohopdong, DT.ngaykyhd, DT.ngayketthuchd, DT.SCHEME, \n"+
								 "			( case tc.donvi when 0 then round( tc.chietkhau * DT.doanhsoDAT / 100.0, 0 ) when 1 then tc.chietkhau else 0 end ) as giatritra, \n"+
								 "			ISNULL( ( select SUM(TONGGIATRI) from ERP_DONDATHANGNPP_TICHLUY_TRATL where DKKMID = DT.DKKMTICHLUY_FK and DONDATHANGID in ( select PK_SEQ from ERP_DONDATHANGNPP where KHACHHANG_FK = '"+this.khachhangId+"' and trangthai != 3 ) ) , 0) as tonggiatri \n"+ 
								 "	FROM \n"+
								 "	( \n"+
								 "		SELECT	DKKMTICHLUY_FK, c.PK_SEQ,  \n"+
								 "				ISNULL ( ( \n"+
								 "				select SUM( dh_sp.soluong * round( ( dongiaGOC * ( 1 + thueVAT / 100.0 ) ), 0 ) ) as tonggiatri \n"+
								 "				from ERP_DONDATHANGNPP dh inner join ERP_DONDATHANGNPP_SANPHAM dh_sp on dh.PK_SEQ = dh_sp.dondathang_fk  \n"+
								 "				where dh.KHACHHANG_FK = a.KHACHHANG_FK and dh.CS_DUYET = '1' and dh.TRANGTHAI not in ( 0, 3 ) and dh.NgayDonHang >= c.ngayds_tungay and dh.NgayDonHang <= c.ngayds_denngay \n"+
								 "		 				and dh_sp.sanpham_fk in ( select sanpham_fk from TIEUCHITHUONGTL_SANPHAM where thuongtl_fk = c.PK_SEQ ) \n"+
								 "				), 0 ) doanhsoDAT , a.sohopdong,a.ngaykyhd, a.ngayketthuchd , c.SCHEME \n"+
								 "		from DANGKYKM_TICHLUY_KHACHHANG a inner join DANGKYKM_TICHLUY b on a.DKKMTICHLUY_FK = b.PK_SEQ \n"+
								 "		inner join TIEUCHITHUONGTL c on b.TIEUCHITL_FK = c.PK_SEQ \n"+
								 "		where KHACHHANG_FK = '"+this.khachhangId+"' and c.TRANGTHAI = '1' and c.khongcanduyettra = '1' and b.daduyet = '1' and b.TRANGTHAI = '1' \n"+ 
								 "	) \n"+
								 "	DT inner join TIEUCHITHUONGTL_TIEUCHI tc on DT.PK_SEQ = tc.thuongtl_fk \n"+
								 "	where tc.tumuc <= DT.doanhsoDAT and DT.doanhsoDAT <= tc.denmuc and tc.donvi != 2 \n"+
								 " )  \n"+
								 " DATA  \n"+
								 " LEFT JOIN \n"+
								 " ( SELECT ct.HOADON_FK, SUM(ct.SOTIENTT) AS DATT \n"+
								 "  FROM ERP_THANHTOANHOADON_HOADON ct inner join ERP_THANHTOANHOADON tthd on ct.THANHTOANHD_FK = tthd.PK_SEQ \n"+
								 "  where   tthd.TRANGTHAI != '2' AND tthd.KHACHHANG_FK = '"+this.khachhangId+"' and tthd.NPP_FK = "+this.nppdangnhap+"  \n" +
								 " 	and tthd.TIENTE_FK = '100000' and ct.LOAIHD = '9' and tthd.PK_SEQ != "+(this.id.trim().length() <=0 ? "0": this.id )+" \n"+
								 "  GROUP BY ct.HOADON_FK \n"+
								 " )DATHANHTOAN on  DATA.PK_SEQ = DATHANHTOAN.HOADON_FK  \n"+
								 " WHERE  (giatritra - tonggiatri - isnull(DATHANHTOAN.DATT,0)) >0  ";
								 if(this.id.trim().length()>0)
								{
									query += " and DATA.PK_SEQ NOT IN (SELECT ct.HOADON_FK FROM ERP_THANHTOANHOADON_HOADON ct inner join ERP_THANHTOANHOADON tthd on ct.THANHTOANHD_FK = tthd.PK_SEQ " +
											 "						   WHERE tthd.TRANGTHAI != '2' AND tthd.KHACHHANG_FK = '"+this.khachhangId+"' and tthd.NPP_FK = " +this.nppdangnhap+ " \n" +
											 "						   and tthd.TIENTE_FK = '100000' and ct.LOAIHD = '9' and tthd.PK_SEQ = "+ this.id +" ) \n";
								}
						
					}
				}
				
				
				System.out.println("Query khoi tao hoa don 111112: " + query);
				
				ResultSet rsHoadon = db.get(query);
				List<IHoadon> hdList = new ArrayList<IHoadon>();
				if(rsHoadon != null)
				{
					try 
					{
						IHoadon hd = null;
						while(rsHoadon.next())
						{						
							String doituong = "";
							String doituongId = "";
							String madoituong ="";
							
							String id = rsHoadon.getString("pk_seq");
							String kyhieu = rsHoadon.getString("kyhieu");
							String sohoadon = rsHoadon.getString("sohoadon");
							String ngayhd = rsHoadon.getString("ngayhoadon");
							String ngaydenhantt = rsHoadon.getString("ngaydenhantt");
							String tienteId = rsHoadon.getString("TTID");
							String sotiengoc = "";
							String avat = "";
							String loaihd = rsHoadon.getString("LOAIHD");
							String sohopdong = rsHoadon.getString("SOHOPDONG");
							String sohopdongngoai = rsHoadon.getString("sohopdongngoai");
							
							if(tienteId.equals("100000")){
								avat = ("" + rsHoadon.getDouble("sotienAVAT")).replaceAll(",", "");
							}else{
								avat = ("" + rsHoadon.getDouble("sotienAVAT")*rsHoadon.getDouble("TIGIA")).replaceAll(",", "");
							}
							
							if(tienteId.equals("100000")){
								sotiengoc = ("" + rsHoadon.getDouble("sotiengoc")).replaceAll(",", "");
							}else{
								sotiengoc = ("" + rsHoadon.getDouble("sotiengoc")*rsHoadon.getDouble("TIGIA")).replaceAll(",", "");
							}
							
							String sotienNT = ("" + rsHoadon.getDouble("sotienAVAT")).replaceAll(",", "");							
							String dathanhtoan = ("" + rsHoadon.getDouble("SOTIENTT")).replaceAll(",", "");
							String tigia = rsHoadon.getString("TIGIA").replaceAll(",", "");
							
							if(this.bophanId.trim().length() > 0)
							{
								doituong = rsHoadon.getString("DOITUONG");
								doituongId = rsHoadon.getString("DOITUONG_FK");
								madoituong = rsHoadon.getString("MADOITUONG");
							}
							
							
							hd = new Hoadon(id, kyhieu, sohoadon, ngayhd, avat, sotienNT, dathanhtoan,  tienteId);
							hd.setSoTienGoc2(sotiengoc);
							hd.setSoPO(rsHoadon.getString("POID"));
							hd.setTigia(tigia);
							hd.setNgaydenhanTT(ngaydenhantt);
							hd.setLoaihd1(loaihd);
							hd.setSohopdong(sohopdong);
							hd.setSohopdongNGOAI(sohopdongngoai);
							
							if(this.bophanId.trim().length() > 0)
							{
							  hd.setDoituong(doituong);
							  hd.setDoituongId(doituongId);
							  hd.setMadoituong(madoituong);
							}
							
							hdList.add(hd);
						}
						rsHoadon.close();
					} 
					catch (Exception e) { 
						System.out.println("Error Here : "+e.toString());
					}
				}
				this.hoadonList = hdList;
				System.out.println("So hoa don: " + this.hoadonList.size());
			}
			
			
			
			
			
			
			if( (this.nhomNCCCNId.length() > 1 ) && this.htttId.length() > 0 && this.hoadonList.size() <= 0)
			{
				NumberFormat formatter = new DecimalFormat("#,###,###");
				
				String query = "";
				if(this.LoaiThanhToan.equals("1")) // HÓA ĐƠN
				{
					if(this.id.length() > 0)
					{
					query += " select p.ncc_fk , b.pk_seq, b.kyhieu, b.sohoadon, b.ngayhoadon, b.sotienAVAT - isnull(thanh_toan.tongthanhtoan, '0') as sotienAVAT, SOTIENTT, " +
								" '' as POID " +
							"from ERP_THANHTOANHOADON_HOADON a " +
							"inner join ERP_HOADONNCC b on a.hoadon_fk = b.pk_seq " +
							"    inner join ERP_PARK p on b.park_fk = p.pk_seq  " + // them join voi ERP_PARK de lay ma ncc
								"left join	" +
								"( select TTHD.hoadon_fk, sum(TTHD.SOTIENTT) as tongthanhtoan " +
								"from ERP_THANHTOANHOADON_HOADON TTHD inner join ERP_THANHTOANHOADON TT on TTHD.THANHTOANHD_FK = TT.PK_SEQ " +
								"where TT.trangthai !=2 and TTHD.thanhtoanhd_fk != '" + this.id + "' " +  //sua trang thai !=2 thanh =1
										" and TTHD.hoadon_fk in (select hoadon_fk " +
														  "from ERP_THANHTOANHOADON_HOADON " +
														  "where thanhtoanhd_fk = '" + this.id + "') " +
								"group by hoadon_fk) thanh_toan on a.hoadon_fk = thanh_toan.hoadon_fk " +
								"where a.thanhtoanhd_fk = '" + this.id + "' " +
							" union all ";
					}
					
					query += "(select hoadon.ncc_fk , hoadon.pk_seq, hoadon.kyhieu, hoadon.sohoadon, hoadon.ngayhoadon, " +
							" hoadon.sotienAVAT - isnull(dathanhtoan.DATHANHTOAN, '0') as sotienAVAT, 0 as sotientt ," +
								" ''   as POID " +
							"from ( " +
									"select b.ncc_fk , a.pk_seq, a.kyhieu, a.sohoadon, a.ngayhoadon, a.sotienAVAT " +
									"from ERP_HOADONNCC a inner join ERP_PARK b on a.park_fk = b.pk_seq " +
									"where b.ncc_fk in ( select NCC_FK from NHOMNHACUNGCAPCN_NCC where NHOMNHACUNGCAPCN_FK = '"+ this.nhomNCCCNId  +"')" +
									" and b.trangthai = '2' and a.trangthai = '0' ";
								if(this.id.length() > 0)
								{
									query += "and a.pk_seq not in (select hoadon_fk from ERP_THANHTOANHOADON_HOADON where thanhtoanhd_fk = '" + this.id + "') ";
								}
						query += ") hoadon " +
							"left join ( " +
									"select TTHD.HOADON_FK , sum(TTHD.SOTIENTT) as DATHANHTOAN  " +
									"from ERP_THANHTOANHOADON_HOADON TTHD inner join ERP_THANHTOANHOADON TT on TTHD.THANHTOANHD_FK = TT.PK_SEQ " +
									"where TT.TRANGTHAI !=2 and TTHD.HOADON_FK in (select pk_seq from ERP_HOADONNCC where trangthai = 0)  " + //sua trang thai !=2 thanh =1
									"group by HOADON_FK " +
									") dathanhtoan " +
							"on hoadon.pk_seq = dathanhtoan.hoadon_fk " +
							"where hoadon.sotienAVAT - isnull(dathanhtoan.DATHANHTOAN, '0') > 0 " +
							" and hoadon.pk_seq not in ( "+
							"	select distinct td.HOADON_FK from ERP_THANHTOANHOADON_HOADON td "+
							"	inner join ERP_THANHTOANHOADON t on t.PK_SEQ = td.THANHTOANHD_FK "+
							"	where t.TRANGTHAI =0) )	";
				}
				System.out.println("Query khoi tao hoa don: " + query);
				
				ResultSet rsHoadon = db.get(query);
				List<IHoadon> hdList = new ArrayList<IHoadon>();
				if(rsHoadon != null)
				{
					try 
					{
						IHoadon hd = null;
						while(rsHoadon.next())
						{
							String ncc_fk =  rsHoadon.getString("ncc_fk");
							String id = rsHoadon.getString("pk_seq");
							String kyhieu = rsHoadon.getString("kyhieu");
							String sohoadon = rsHoadon.getString("sohoadon");
							String ngayhd = rsHoadon.getString("ngayhoadon");
							String avat = formatter.format(rsHoadon.getDouble("sotienAVAT"));
							//System.out.println("Query sohoadon: " + sohoadon);
							String dathanhtoan ="";
							if(this.id.length() > 0)
							{
								dathanhtoan = formatter.format(rsHoadon.getDouble("SOTIENTT"));
							}
							
							hd = new Hoadon(ncc_fk, id, kyhieu, sohoadon, ngayhd, avat, "0", dathanhtoan, "" , "");
							hd.setSoPO(rsHoadon.getString("POID"));
							hdList.add(hd);
						}
						rsHoadon.close();
					} 
					catch (Exception e) { 
						System.out.println("Error Here : "+e.toString());
					}
				}
				this.hoadonList = hdList;
				System.out.println("So hoa don: " + this.hoadonList.size());
				
			}
		}
	}
	

	private String getDateTime() 
	{
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date date = new Date();
        return dateFormat.format(date);	
	}
	
	public void DBclose() 
	{
		
	}

	
	public String getLoaiThanhToan() {
		
		return this.LoaiThanhToan;
	}

	
	public void setLoaiThanhToan(String loaithanhtoan) {
		
		this.LoaiThanhToan=loaithanhtoan;
	}

	
	public String getDoiTuongTamUng() {
		
		return this.DoiTuongTamUng;
	}

	
	public void setDoiTuongTamUng(String DoiTuongTamUng) {
		
		 this.DoiTuongTamUng=DoiTuongTamUng ;
	}

	
	public String getNhanVienId() {
		
		return this.NhanvienId;
	}

	
	public void setNhanVienId(String nvId) {
		
		this.NhanvienId=nvId;
	}

	
	public String Gettenhienthi() {
		
		return this.TenHienThi;
	}

	
	public void settenhienthi(String tenhienthi) {
		
		this.TenHienThi=tenhienthi;
	}

	
	public String getDiachi() {
		return this.diachi;
	}

	
	public void setDiachi(String diachi) {
		this.diachi = diachi;
	}

	public String delete() {
		
		try 
		{
			db.getConnection().setAutoCommit(false);
			
			db.update("update ERP_ThanhToanHoaDon set trangthai = '2' where pk_seq = '" + this.id + "'");
			
			db.getConnection().commit();
			db.getConnection().setAutoCommit(true);
			db.shutDown();
			
			return "";
		} 
		catch (SQLException e)
		{ 
			db.shutDown(); 
			return "Khong the xoa ThanhToanHoaDon"; 
		}
	}

	
	
	public String delete(String IdThanhToan) {
		
		try 
		{
			db.getConnection().setAutoCommit(false);
			
			db.update("update ERP_ThanhToanHoaDon set trangthai = '2' where pk_seq = '" + IdThanhToan + "'");
			
			db.getConnection().commit();
			db.getConnection().setAutoCommit(true);
			db.shutDown();
			
			return "";
		} 
		catch (SQLException e)
		{ 
			db.shutDown(); 
			return "Khong the xoa ThanhToanHoaDon"; 
		}
	}
	

	
	public static void main(String[] args) {
		ErpThanhtoanhoadon erpThanhtoanhoadon = new ErpThanhtoanhoadon();
		erpThanhtoanhoadon.setId("100079");
		List<String> hienTai = erpThanhtoanhoadon.GetlistHoaDonHienTai();
		System.out.println(hienTai.toString());
	}

	public void setCheckThanhtoantuTV(String checkThanhtoantuTV) {
		this.checkThanhtoantuTV= checkThanhtoantuTV;
		
	}

	public String getCheckThanhtoantuTV() {
		return this.checkThanhtoantuTV;
	}
	
	public String getKhachhangId() {
		
		return this.khachhangId;
	}

	
	public void setKhachhangId(String khachhangId) {
		
		this.khachhangId=khachhangId;
	}
	
	public ResultSet getNhanvienRs() {

		return this.NhanvienRs;
	}


	public void setNhanvienRs(ResultSet NhanvienRs) 
	{

		this.NhanvienRs = NhanvienRs;
	}


	public ResultSet getKhachhangRs() 
	{
		return this.khachhangRs;
	}


	public void setKhachhangRs(ResultSet khachhangRs) 
	{
		this.khachhangRs = khachhangRs;
		
	}


	public ResultSet getNhomNCCRs() 
	{
		return this.nhomNCCRs;
	}


	public void setNhomNCCRs(ResultSet nhomNCCRs) 
	{

		this.nhomNCCRs = nhomNCCRs;
	}

	
	public String getChungtukemtheo() {
		
		return this.chungtukemtheo;
	}

	
	public void setChungtukemtheo(String chungtukemtheo) {
		
		this.chungtukemtheo = chungtukemtheo;
	}

	public String getBophanTen()
    {
		return this.bophanTen ;
	}

	public void setBophanTen(String bophanTen) 
	{
		this.bophanTen = bophanTen;
	}

	public String getBophanId() 
	{
		return this.bophanId;
	}

	public void setBophanId(String bophanId) 
	{
		this.bophanId = bophanId;
	}

	
	public String getnppdangnhap() {
		
		return this.nppdangnhap;
	}

	
	public void setnppdangnhap(String nppdangnhap) {
		
		this.nppdangnhap = nppdangnhap;
	}
	
	private void getNppInfo()
	{		
		//Phien ban moi
		geso.dms.distributor.util.Utility util=new geso.dms.distributor.util.Utility();
		this.nppdangnhap=util.getIdNhapp(this.userId);
	}

	
	public String getisktt() {
		
		return this.isktt;
	}

	
	public void setisktt(String isktt) {
		
		this.isktt = isktt;
	}

	
	public String[] getMahd() {
		
		return this.Mahd;
	}

	
	public void setMahd(String[] Mahd) {
		
		this.Mahd = Mahd;
	}

	
	public String[] getMauhd() {
		
		return this.Mauhd;
	}

	
	public void setMauhd(String[] Mauhd) {
		
		this.Mauhd = Mauhd;
	}

	
	public String[] getKyhieuhd() {
		
		return this.Kyhieuhd;
	}

	
	public void setKyhieuhd(String[] Kyhieuhd) {
		
		this.Kyhieuhd = Kyhieuhd;
	}

	
	public String[] getSohd() {
		
		return this.Sohd;
	}

	
	public void setSohd(String[] Sohd) {
		
		this.Sohd = Sohd;
	}

	
	public String[] getNgayhd() {
		
		return this.Ngayhd ;
	}

	
	public void setNgayhd(String[] Ngayhd) {
		
		this.Ngayhd = Ngayhd;
	}

	
	public String[] getTenNCChd() {
		
		return this.TenNCChd;
	}

	
	public void setTenNCChd(String[] TenNCChd) {
		
		this.TenNCChd = TenNCChd;
	}

	
	public String[] getMSThd() {
		
		return this.MSThd;
	}

	
	public void setMSThd(String[] MSThd) {
		
		this.MSThd = MSThd;
	}

	
	public String[] getTienhanghd() {
		
		return this.Tienhanghd;
	}

	
	public void setTienhanghd(String[] Tienhanghd) {
		
		this.Tienhanghd = Tienhanghd;
	}

	
	public String[] getThuesuathd() {
		
		return this.Thuesuathd;
	}

	
	public void setThuesuathd(String[] Thuesuathd) {
		
		this.Thuesuathd = Thuesuathd;
	}

	
	public String[] getTienthuehd() {
		
		return this.Tienthuehd;
	}

	
	public void setTienthuehd(String[] Tienthuehd) {
		
		this.Tienthuehd = Tienthuehd;
	}

	
	public String[] getPhongBanIds() {
		
		return this.PhongBanIds;
	}

	
	public void setPhongBanIds(String[] PhongBanIds) {
		
		this.PhongBanIds = PhongBanIds;
	}

	
	public ResultSet getPhongBanRs() {
		
		return this.PhongBanRs;
	}

	
	public void setPhongBanRs(ResultSet PhongBanRs) {
		
		this.PhongBanRs = PhongBanRs;
	}

	
	public int getCount()
	{
		return this.count;
	}
	
	public void setCount(int count)
	{
	this.count=count;	
	}
	

	public ResultSet getTaiKhoanKTRs() {
		return TaiKhoanKTRs;
	}

	
	public String[] getTaiKhoanIds()
	{

		return this.TaiKhoanIds;
	}

	public void setTaiKhoanIds(String[] TaiKhoanIds)
	{
		this.TaiKhoanIds = TaiKhoanIds;
	}

	public String[] getDcIds()
	{

		return this.dcIds;
	}

	public void setDcIds(String[] dcIds)
	{
		this.dcIds = dcIds;
	}
	

	public String[] getLoais() 
	{
		return this.loais;
	}

	public void setLoais(String[] loais) 
	{
		this.loais = loais;
	}

	public ResultSet getDoituongRs(String taikhoanId) 
	{
		this.dungchos = "";
		
		if(taikhoanId.indexOf("_") >= 0){
			taikhoanId = taikhoanId.substring(0, taikhoanId.indexOf("_"));
		}
		String query = 
				" SELECT PK_SEQ,SOHIEUTAIKHOAN as MA,TENTAIKHOAN AS TEN, ISNULL(COTTCHIPHI,0)COTTCHIPHI, " +
				" ISNULL(DUNGCHOKHO, 0) DUNGCHOKHO, ISNULL(DUNGCHONGANHANG, 0) DUNGCHONGANHANG, ISNULL(DUNGCHONCC, 0) DUNGCHONCC, ISNULL(DUNGCHOTAISAN, 0) DUNGCHOTAISAN, " +
				" ISNULL(DUNGCHOKHACHHANG, 0) DUNGCHOKHACHHANG, ISNULL(DUNGCHONHANVIEN, 0) DUNGCHONHANVIEN " +
				" FROM ERP_TAIKHOANKT "+
				" WHERE PK_SEQ = '"+ taikhoanId +"' ";
		System.out.println(" Câu query "+query);
		ResultSet rs = this.db.get(query);
		if (rs != null)
		{
			try
			{
				while (rs.next())
				{
					// Dùng cho : 1 Kho, 2 NCC, 3 Ngân hàng, 4 Tài Sản, 5 Khách hàng, 6 TTCP, 7 Nhân viên
					
					if(rs.getString("DUNGCHOKHO").equals("1")){
						dungchos += "1,";
					}
					if(rs.getString("DUNGCHONCC").equals("1")){
						dungchos += "2,";
					}
					if(rs.getString("DUNGCHONGANHANG").equals("1")){
						dungchos += "3,";
					}
					if(rs.getString("DUNGCHOTAISAN").equals("1")){
						dungchos += "4,";
					}
					if(rs.getString("DUNGCHOKHACHHANG").equals("1")){
						dungchos += "5,";
					}
					if(rs.getString("COTTCHIPHI").equals("1")){
						dungchos += "6,";
					}
					if(rs.getString("DUNGCHONHANVIEN").equals("1")){
						dungchos += "7,";
					}

				}
			} catch (Exception e)
			{
				e.printStackTrace();
			}
		}
		
		if(this.dungchos.trim().length() > 0) this.dungchos = this.dungchos.substring(0, this.dungchos.length()-1);
		
		String command = "";
		ResultSet rsDT = null;
		
		if(this.dungchos.trim().length() > 0)
		{
			String[] mang = this.dungchos.split(",");
			for(int i= 0; i < mang.length; i++)
			{
				if(mang[i].equals("1")){
					 if(command.trim().length() > 0)  command += " UNION ALL ";
					 command += "SELECT PK_SEQ, MA, TEN, 1 AS LOAI FROM SANPHAM WHERE  TRANGTHAI=1";
				}
				if(mang[i].equals("2")){
					 if(command.trim().length() > 0)  command += " UNION ALL ";
					 command += "SELECT PK_SEQ, MA, TEN, 2 AS LOAI FROM TraphacoERP.dbo.ERP_NHACUNGCAP WHERE  TRANGTHAI=1 AND NPP_FK ="+this.nppdangnhap;
				}
				if(mang[i].equals("3")){
					 if(command.trim().length() > 0)  command += " UNION ALL ";
					 command += "SELECT PK_SEQ, MA, TEN, 3 AS LOAI FROM ERP_NGANHANG WHERE  TRANGTHAI=1";
				}
				if(mang[i].equals("4")){
					 if(command.trim().length() > 0)  command += " UNION ALL ";
					 command += "SELECT PK_SEQ, MA, DIENGIAI AS TEN, 4 AS LOAI FROM ERP_TAISANCODINH  WHERE  TRANGTHAI=1";
				}
				if(mang[i].equals("5")){ // KHÁCH HÀNG
					 if(command.trim().length() > 0)  command += " UNION ALL ";
					 command += " SELECT CONVERT(VARCHAR, PK_SEQ) + '-0' AS PK_SEQ, ( '[KH] ' + MAFAST ) AS MA, TEN, 5 AS LOAI FROM KHACHHANG \n" +
					 			" WHERE  TRANGTHAI=1 AND NPP_FK IN ( "+this.nppdangnhap+ " ) \n"+
					 			" UNION ALL \n"+
					 			" SELECT CONVERT(VARCHAR, PK_SEQ) + '-1' AS PK_SEQ, ( '[NPP] ' + MA ) AS MA, TEN, 5 AS LOAI FROM NHAPHANPHOI \n" +
					 			" WHERE  TRANGTHAI=1 AND TRUCTHUOC_FK IN (SELECT PK_SEQ FROM NHAPHANPHOI WHERE PK_SEQ = " + this.nppdangnhap + ") \n" +
								" ORDER BY TEN ";				 
				}
				if(mang[i].equals("6")){
					 if(command.trim().length() > 0)  command += " UNION ALL ";
					 command += "SELECT PK_SEQ, MA, TEN, 6 AS LOAI FROM ERP_TRUNGTAMCHIPHI WHERE  TRANGTHAI=1";
				}
				if(mang[i].equals("7")){
					 if(command.trim().length() > 0)  command += " UNION ALL ";
					 command += "SELECT PK_SEQ, MA, TEN, 7 AS LOAI FROM ERP_NHANVIEN WHERE  TRANGTHAI=1";
				}
			}
		}
		
		System.out.println("Câu DT "+command);
		if(command.length() > 0) rsDT = this.db.get(command);
		
		return rsDT;
	}

	
	public String[] getKenhIds() {
		
		return this.KenhbhIds;
	}

	
	public void setKenhIds(String[] KenhIds) {
		
		this.KenhbhIds = KenhIds;
	}

	
	public ResultSet getKenhBhRs() {
		
		return this.KenhBhRs;
	}

	
	public void setKenhBhRs(ResultSet KenhBhRs) {
		
		this.KenhBhRs = KenhBhRs;
	}

	
	public String[] getTinhThanhIds() {
		
		return this.TinhThanhIds;
	}

	
	public void setTinhThanhIds(String[] TinhThanhIds) {
		
		this.TinhThanhIds = TinhThanhIds;
	}

	
	public ResultSet getTinhThanhRs() {
		
		String query = "SELECT CONVERT(VARCHAR, PK_SEQ) AS PK_SEQ, TEN AS TEN FROM TINHTHANH  ";
		System.out.println(query);
		
		return this.db.getScrol(query);
	}

	
	public void setTinhThanhRs(ResultSet TinhThanhRs) {
		
		this.TinhThanhRs = TinhThanhRs;
	}

	
	public String[] getSanphamIds() {
		
		return this.SanPhamIds;
	}

	
	public void setSanPhamIds(String[] SanPhamIds) {
		
		this.SanPhamIds = SanPhamIds;
	}

	
	public ResultSet getSanPhamRs() {
		String query = "SELECT CONVERT(VARCHAR, PK_SEQ) AS PK_SEQ, MA + ' - ' + TEN AS TEN FROM SANPHAM WHERE  TRANGTHAI = 1";
		
		System.out.println(query);
		return this.db.getScrol(query);
	}

	
	public void setSanPhamRs(ResultSet SanPhamRs) {
		
		this.SanphamRs = SanPhamRs;
	}

	
	public String[] getDiaBanIds() {
	
		return this.DiabanIds;
	}

	
	public void setDiaBanIds(String[] DiaBanIds) {
	
		this.DiabanIds = DiaBanIds;
	}

	
	public ResultSet getDiaBanRs() {
	
		String query = "SELECT CONVERT(VARCHAR, PK_SEQ) AS PK_SEQ, TEN AS TEN FROM TraphacoERP.dbo.diaban WHERE  TRANGTHAI = 1 ";
		System.out.println(query);
		
		return this.db.getScrol(query);
	}

	
	public void setDiaBanRs(ResultSet DiaBanRs) {
	
		this.DiabanRs = DiaBanRs;
	}

	
	public String[] getMavvIds() {
	
		return this.MavvIds;
	}

	
	public void setMavvIds(String[] MavvIds) {
	
		this.MavvIds = MavvIds;
	}

	
	public ResultSet getMavvRs() {
	
		String query =  "SELECT CONVERT(VARCHAR, PK_SEQ) AS PK_SEQ, TEN AS TEN " +
						"FROM TraphacoERP.dbo.VUVIEC WHERE  TRANGTHAI = 1  " ;
		System.out.println(query);
		
		return this.db.getScrol(query);
	}

	
	public void setMavvRs(ResultSet MavvRs) {
	
		this.MavvRs = MavvRs;
	}

	
	public String[] getBenhVienIds() {
	
		return this.BenhvienIds;
	}

	
	public void setBenhVienIds(String[] BenhVienIds) {
	
		this.BenhvienIds = BenhVienIds;
	}

	
	public ResultSet getBenhVienRs() {
	
		this.getNppInfo();
		// LẤY KHÁCH HÀNG LÀ LOẠI BỆNH VIỆN
		String query = "SELECT CONVERT(VARCHAR, PK_SEQ) AS PK_SEQ, MAFAST + ' - ' + TEN AS TEN FROM KHACHHANG WHERE  TRANGTHAI = 1 AND NPP_FK = "+this.nppdangnhap;
				
		System.out.println(query);
		
		return this.db.getScrol(query);
	}

	
	public void setBenhVienRs(ResultSet BenhVienRs) {
	
		this.BenhvienRs = BenhVienRs;
	}

	
	public String[] getDiengiaihd() {
	
		return this.Diengiaihd;
	}

	
	public void setDiengiaihd(String[] Diengiaihd) {
	
		this.Diengiaihd = Diengiaihd;
	}
}
