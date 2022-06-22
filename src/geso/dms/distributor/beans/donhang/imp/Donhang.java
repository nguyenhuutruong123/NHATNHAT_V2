package geso.dms.distributor.beans.donhang.imp;

import java.io.Serializable;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.Hashtable;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import geso.dms.center.db.sql.Idbutils;
import geso.dms.center.db.sql.dbutils_syn;
import geso.dms.distributor.beans.ctkhuyenmai.ICtkhuyenmai;
import geso.dms.distributor.beans.ctkhuyenmai.imp.XLkhuyenmai;
import geso.dms.distributor.beans.ctkhuyenmai.imp.XLkhuyenmaiDonhangDXK;
import geso.dms.distributor.beans.donhang.IDonhang;
import geso.dms.distributor.beans.donhang.ISanpham;
import geso.dms.distributor.beans.donhang.ISpDetail;
import geso.dms.distributor.beans.hoadontaichinh.imp.Hoadontaichinh;
import geso.dms.distributor.db.sql.dbutils;
import geso.dms.distributor.util.FixData;
import geso.dms.distributor.util.Utility;

public class Donhang implements IDonhang, Serializable {
	private static final long serialVersionUID = -9217977546733610214L;

	String userId;
	String id; // ma don hang
	String ngaygiaodich;
	String daidienkd;
	String trangthai;
	String ngaytao;
	String nguoitao;
	String ngaysua;
	String nguoisua;
	String chietkhau;
	String tongchietkhau;
	String VAT;
	String msg;
	String ghichu;

	String donhangKhac;

	String nppId;
	String nppTen;
	String sitecode;
	String muctindung;
	String loaiNppId;

	String ddkdId;
	String gsbhId;

	String tbhId;
	String smartId;
	String khTen;
	ResultSet khlist;
	String khId;
	String bgstId;

	String khoId;
	ResultSet gsbhs;
	ResultSet kholist;
	ResultSet tbhlist;
	ResultSet ddkdlist;

	List<ISanpham> splist;
	float ttCKKM;
	float ttsauCKKM;
	float ttsauCK;
	String tienCK;
	String tongtientruocVAT;
	String tongtiensauVAT;
	String ingia;
	String hangkh;
	List<ISanpham> list_sanphamkm_capnhatlo = new ArrayList<ISanpham>();
	ResultSet rshangkh;
	String ngayhoadon;
	String tiengiamtru;

	String khDienThoai = "";
	String khMaSoThue = "";

	public dbutils getDb() {
		return db;
	}

	public String getTiengiamtru() {
		return tiengiamtru;
	}

	public void setTiengiamtru(String tiengiamtru) {
		this.tiengiamtru = tiengiamtru;
	}

	double tiengiamgia = 0;
	String ghichugiamgia = "";

	public String getNgayhoadon() {
		return ngayhoadon;
	}

	public void setNgayhoadon(String ngayhoadon) {
		this.ngayhoadon = ngayhoadon;
	}

	public String getIngia() {
		return ingia;
	}

	public void setIngia(String ingia) {
		this.ingia = ingia;
	}

	Hashtable<String, Integer> spThieuList;

	/// trakhuyen mai
	Hashtable<String, Float> scheme_tongtien = new Hashtable<String, Float>();
	Hashtable<String, Float> scheme_chietkhau = new Hashtable<String, Float>();
	List<ISanpham> scheme_sanpham = new ArrayList<ISanpham>();

	boolean aplaikm;
	boolean cokm;
	boolean chuaApkm;
	boolean dacoDh;
	boolean daxuatkhoChuachot;

	String coTrungBay;
	boolean aplaitb;

	String IsDonHangLe;
	String IsSampling;

	//
	String[] tichluy_scheme;
	String[] tichluy_tongtien;
	String[] tichluy_vat;
	String[] tichluy_cothexoa;

	//
	ResultSet ckbsList;
	String chietkhau_ids;
	Hashtable<String, Float> chietkhau_giatri;
	String chietkhau_vat;

	// Doanh so de nghi
	ResultSet dsdnRs;
	String tieuchiID;
	String dstXanh;
	String dstHHBG;
	String dstKHAC;
	String dstXanh_Denghi;
	String dstHHBG_Denghi;

	String ngayks;
	String enterKH;
	String chucuahieu;

	String nvgnId;
	ResultSet nvgnRs;

	String denghitraCKTHANG;

	dbutils db;

	String chietkhaucu;
	String donquatang;
	String sotaikhoan;
	String chietkhauNNK;

	String loainpp = "-1";
	String makho = "";
	String machinhanh = "";
	String quanlykho = "1";

	public String getLoainpp() {
		return loainpp;
	}

	public void setLoainpp(String loaiNpp) {
		this.loainpp = loaiNpp;
	}

	public String getQuanlykho() {
		return quanlykho;
	}

	public void setQuanlykho(String quanlykho) {
		this.quanlykho = quanlykho;
	}

	public String getMakho() {
		return makho;
	}

	public void setMakho(String makho) {
		this.makho = makho;
	}

	public String getMachinhanh() {
		return machinhanh;
	}

	public void setMachinhanh(String machinhanh) {
		this.machinhanh = machinhanh;
	}

	public Donhang(String[] param) {
		this.id = param[0];
		this.khId = param[1];
		this.ngaygiaodich = param[2];
		this.nppTen = param[3];
		this.daidienkd = param[4];
		this.trangthai = param[5];
		this.ngaytao = param[6];
		this.nguoitao = param[7];
		this.ngaysua = param[8];
		this.nguoisua = param[9];
		this.VAT = param[10];
		this.ddkdId = param[11];
		this.ghichu = "";
		this.tbhId = "";
		this.gsbhId = "";
		this.chietkhau = "";
		this.tongchietkhau = "";
		this.bgstId = "0";
		this.khoId = "";
		this.msg = "";
		this.muctindung = "0";
		this.spThieuList = new Hashtable<String, Integer>();
		this.aplaikm = false;
		this.cokm = false;
		this.chuaApkm = false;
		this.dacoDh = false;
		this.daxuatkhoChuachot = false;
		this.ngayks = "";
		this.coTrungBay = "";
		this.aplaitb = false;

		this.IsDonHangLe = "0";
		this.IsSampling = "0";
		this.enterKH = "0";

		this.chietkhau_ids = "";
		this.chietkhau_giatri = new Hashtable<String, Float>();
		this.chietkhau_vat = "";
		this.tieuchiID = "";

		this.dstXanh = "";
		this.dstHHBG = "";
		this.dstKHAC = "";
		this.dstXanh_Denghi = "";
		this.dstHHBG_Denghi = "";

		this.donhangKhac = "0";
		this.chucuahieu = "";
		this.denghitraCKTHANG = "0";
		this.chietkhaucu = "0";
		this.donquatang = "0";
		this.ngayhoadon = "";
		this.nvgnId = "";
		this.sotaikhoan = "";
		this.ingia = "1";
		this.hangkh = "";
		this.db = new dbutils();

	}

	public Donhang(String id) {
		this.id = id;
		this.khId = "";
		this.ngaygiaodich = "";
		this.nppTen = "";
		this.daidienkd = "";
		this.trangthai = "";
		this.ngaytao = "";
		this.nguoitao = "";
		this.ngaysua = "";
		this.nguoisua = "";
		this.VAT = "";
		this.ddkdId = "";
		this.gsbhId = "";
		this.tbhId = "";
		this.chietkhau = "";
		this.tongchietkhau = "";
		this.tongtiensauVAT = "0";
		this.tongtientruocVAT = "0";
		this.ttsauCKKM = 0;
		this.ttCKKM = 0;
		this.ttsauCK = 0;
		this.bgstId = "0";
		this.khoId = "";
		this.msg = "";
		this.khTen = "";
		this.smartId = "";
		this.muctindung = "0";
		this.aplaikm = false;
		this.cokm = false;
		this.chuaApkm = false;
		this.dacoDh = false;
		this.daxuatkhoChuachot = false;
		this.aplaitb = false;
		this.spThieuList = new Hashtable<String, Integer>();
		this.ngayks = "";
		this.coTrungBay = "";
		this.IsDonHangLe = "0";
		this.IsSampling = "0";
		this.enterKH = "0";
		this.ghichu = "";
		this.chietkhau_ids = "";
		this.chietkhau_giatri = new Hashtable<String, Float>();
		this.chietkhau_vat = "";
		this.tieuchiID = "";

		this.dstXanh = "";
		this.dstHHBG = "";
		this.dstKHAC = "";
		this.dstXanh_Denghi = "";
		this.dstHHBG_Denghi = "";

		this.donhangKhac = "0";
		this.chucuahieu = "";
		this.nvgnId = "";
		this.denghitraCKTHANG = "0";
		this.donquatang = "0";
		this.sotaikhoan = "";
		this.ingia = "1";
		this.hangkh = "";
		this.ngayhoadon = "";
		this.db = new dbutils();
	}

	public Donhang(String id, String nppId, HttpServletRequest request) {
		this.id = id;
		this.nppId = nppId;
		this.CreateSpList(request);
	}

	public String getUserId() {
		return this.userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getSmartId() {
		return this.smartId;
	}

	public void setSmartId(String smartId) {
		this.smartId = smartId;
	}

	public String getKhTen() {
		return this.khTen;
	}

	public void setKhTen(String khTen) {
		this.khTen = khTen;
	}

	public String getId() {
		return this.id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getKhId() {
		return this.khId;
	}

	public void setKhId(String khId) {
		this.khId = khId;
	}

	public String getNgaygiaodich() {
		return this.ngaygiaodich;
	}

	public void setNgaygiaodich(String ngaygiaodich) {
		this.ngaygiaodich = ngaygiaodich;
	}

	public String getDaidienkd() {
		return this.daidienkd;
	}

	public void setDaidienkd(String daidienkd) {
		this.daidienkd = daidienkd;
	}

	public String getTrangthai() {
		if (this.trangthai == null)
			this.trangthai = "0";
		return this.trangthai;
	}

	public void setTrangthai(String trangthai) {
		this.trangthai = trangthai;
	}

	public String getNgaytao() {
		return this.ngaytao;
	}

	public void setNgaytao(String ngaytao) {
		this.ngaytao = ngaytao;
	}

	public String getNguoitao() {
		return this.nguoitao;
	}

	public void setNguoitao(String nguoitao) {
		this.nguoitao = nguoitao;
	}

	public String getNgaysua() {
		return this.ngaysua;
	}

	public void setNgaysua(String ngaysua) {
		this.ngaysua = ngaysua;
	}

	public String getNguoisua() {
		return this.nguoisua;
	}

	public void setNguoisua(String nguoisua) {
		this.nguoisua = nguoisua;
	}

	public String getChietkhau() {
		if (this.chietkhau.length() <= 0) {
			this.chietkhau = "0";
			this.chietkhauNNK = "0";
		}
		return this.chietkhau;
	}

	public void setChietkhau(String chietkhau) {
		this.chietkhau = chietkhau;
	}

	public String getVAT() {
		if (this.VAT == "") {
			// this.VAT = "10";
			this.VAT = "0"; // OneOne, gia trong bang gia da co VAT
		}
		return this.VAT;
	}

	public void setVAT(String vat) {
		this.VAT = vat;
	}

	public String getMessage() {
		return this.msg;
	}

	public void setMessage(String msg) {
		this.msg = msg;
	}

	public String getNppId() {
		return this.nppId;
	}

	public void setNppId(String nppId) {
		this.nppId = nppId;
		try {
			String query = " select  MaKho,MaFAST,loainpp,quanlykho from NHAPHANPHOI where pk_seq=    " + nppId;
			ResultSet rs = db.get(query);
			rs.next();

			this.makho = rs.getString("MaKho") == null ? "" : rs.getString("MaKho");
			this.machinhanh = rs.getString("MaFAST") == null ? "" : rs.getString("MaFAST");
			this.loainpp = rs.getString("loainpp") == null ? "" : rs.getString("loainpp");
			this.quanlykho = rs.getString("quanlykho") == null ? "" : rs.getString("quanlykho");
			rs.close();
		} catch (Exception e) {

		}
	}

	public String getNppTen() {
		return this.nppTen;
	}

	public void setNppTen(String nppTen) {
		this.nppTen = nppTen;
	}

	public String getSitecode() {
		return this.sitecode;
	}

	public void setSitecode(String sitecode) {
		this.sitecode = sitecode;
	}

	public ResultSet getDdkdList() {
		return this.ddkdlist;
	}

	public void setDdkdList(ResultSet ddkdList) {
		this.ddkdlist = ddkdList;
	}

	public String getDdkdId() {
		return this.ddkdId;
	}

	public void setDdkdId(String ddkdId) {
		this.ddkdId = ddkdId;
	}

	public List<ISanpham> getSpList() {
		return this.splist;
	}

	public void setSpList(List<ISanpham> splist) {
		this.splist = splist;
	}

	public float getTongtiensauCK() {
		float tongtientruocvat = 0;
		try {
			tongtientruocvat = Float.parseFloat(this.tongtientruocVAT);
		} catch (Exception er) {

		}
		float tienck = 0;
		try {
			tienck = Float.parseFloat(this.tongchietkhau);
		} catch (Exception er) {

		}

		this.ttsauCK = tongtientruocvat - tienck;

		return this.ttsauCK;
	}

	public void setTongtiensauCK(float ttsck) {
		this.ttsauCK = ttsck;
	}

	public String getTongtientruocVAT() {
		return this.tongtientruocVAT;
	}

	public void setTongtientruocVAT(String tttvat) {
		this.tongtientruocVAT = tttvat;
	}

	public String getTongtiensauVAT() {
		return this.tongtiensauVAT;
	}

	public void setTongtiensauVAT(String ttsvat) {
		this.tongtiensauVAT = ttsvat;
	}

	public float getTongtienCKKM() {
		return this.ttCKKM;
	}

	public void setTongtienCKKM(float ttckkm) {
		this.ttCKKM = ttckkm;

	}

	public float getTongtiensauCKKM() {
		if (this.donhangKhac.equals("1")) {
			this.ttsauCKKM = 0;
		} else {
			////System.out.println("tong tien sau vat " + this.tongtiensauVAT + "----" + this.ttCKKM);
			if (this.tongtiensauVAT == null)
				this.tongtiensauVAT = "0";
			this.ttsauCKKM = Float.parseFloat(this.tongtiensauVAT) - this.ttCKKM;
		}

		return this.ttsauCKKM;
	}

	public void setTongtiensauCKKM(float ttsckkm) {
		this.ttsauCKKM = ttsckkm;
	}

	public ResultSet getTbhList() {
		return this.tbhlist;
	}

	public void setTbhList(ResultSet tbhList) {
		this.tbhlist = tbhList;
	}

	public String getTbhId() {
		return this.tbhId;
	}

	public void setTbhId(String tbhId) {
		this.tbhId = tbhId;
	}

	public String getGsbhId() {
		return this.gsbhId;
	}

	public void setGsbhId(String gsbhId) {
		this.gsbhId = gsbhId;
	}

	public ResultSet getKhList() {
		return this.khlist;
	}

	public void setKhList(ResultSet khlist) {
		this.khlist = khlist;
	}

	public Hashtable<String, Integer> getSpThieuList() {
		return this.spThieuList;
	}

	public void setSpThieuList(Hashtable<String, Integer> spThieuList) {
		this.spThieuList = spThieuList;
	}

	// tra km
	public Hashtable<String, Float> getScheme_Tongtien() {
		return this.scheme_tongtien;
	}

	public void setScheme_Tongtien(Hashtable<String, Float> scheme_tongtien) {
		this.scheme_tongtien = scheme_tongtien;
	}

	public Hashtable<String, Float> getScheme_Chietkhau() {
		return this.scheme_chietkhau;
	}

	public void setScheme_Chietkhau(Hashtable<String, Float> scheme_chietkhau) {
		this.scheme_chietkhau = scheme_chietkhau;
	}

	public List<ISanpham> getScheme_SpList() {
		return this.scheme_sanpham;
	}

	public void setScheme_SpList(List<ISanpham> splist) {
		this.scheme_sanpham = splist;
	}

	public void getTrakhuyenmai() {
		List<ISanpham> scheme_sp = new ArrayList<ISanpham>();
		/*
		 * String query =
		 * "select a.ctkmId, a.trakmId, a.soxuat, a.soluong, a.spMa, b.scheme, c.loai, c.hinhthuc, c.chietkhau,  c.tongluong, c.tongtien "
		 * ; query = query +
		 * "from donhang_ctkm_trakm a inner join ctkhuyenmai b on a.ctkmid = b.pk_seq inner join trakhuyenmai c on a.trakmid = c.pk_seq "
		 * ; query = query + "where a.donhangId = '" + this.id + "'";
		 */
		String query = "\n select  b.LOAICT,a.ctkmId, a.trakmId, a.soxuat, a.soluong,donvi, a.spMa, a.tonggiatri, b.scheme, c.loai, c.hinhthuc, c.chietkhau,  c.tongluong, c.tongtien, d.ten, d.pk_seq as spId, a.khoNVBH ";
		query = query
				+ "\n from donhang_ctkm_trakm a inner join ctkhuyenmai b on a.ctkmid = b.pk_seq inner join trakhuyenmai c on a.trakmid = c.pk_seq left join sanpham d on a.spMa = d.ma ";
		query = query + "\n left join donvidoluong dv on dv.pk_seq=d.dvdl_fk ";
		query = query + "\n where a.donhangId = '" + this.id + "'";

		////System.out.println("1.Khoi tao TKM:" + query);

		ResultSet rs = db.get(query);
		if (rs != null) {
			try {
				while (rs.next()) {
					String ctkmId = rs.getString("ctkmId");
					String schemeName = rs.getString("scheme");
					String trakmId = rs.getString("trakmId");
					String soxuat = rs.getString("soxuat");
					String soluong = rs.getString("soluong");
					String loai = rs.getString("loai");
					String hinhthuc = rs.getString("hinhthuc");
					String tongiatri = rs.getString("tonggiatri");
					String donvi = rs.getString("donvi");
					float tongtien = 0;
					// float chietkhau = 0;
					String spMa = "";
					this.ttCKKM = 0;
					String LOAICT = rs.getString("LOAICT");
					if (loai.equals("4")) // km điểm
					{
						this.scheme_tongtien.put(schemeName, Float.parseFloat(tongiatri));
						this.aplaikm = true; // co ctkm
						this.cokm = true;
					} else {
						if (loai.equals("1")) // tra tien
						{
							if (rs.getString("tongtien") != null)
								tongtien = Float.parseFloat(rs.getString("tongtien"));

							this.scheme_tongtien.put(schemeName, Float.parseFloat(tongiatri));
							this.aplaikm = true;
							this.cokm = true;
						} else {
							if (loai.equals("2")) // tra chiet khau
							{
								/*
								 * if (rs.getString("chietkhau") != null) chietkhau =
								 * Float.parseFloat(rs.getString("chietkhau"));
								 */
								this.scheme_chietkhau.put(schemeName, Float.parseFloat(tongiatri));
								this.ttCKKM = this.ttCKKM + Float.parseFloat(tongiatri);
								this.aplaikm = true;
								this.cokm = true;
								// ////System.out.println("1.Tra chiet khau");

							} else // tra sp
							{
								// String sql = "select a.sanpham_fk as spId, a.soluong, b.ma, b.ten from
								// trakhuyenmai_sanpham a inner join sanpham b on a.sanpham_fk = b.pk_seq where
								// a.trakhuyenmai_fk = '" + trakmId + "'";
								// String sql = "select a.spMa, a.soluong, b.pk_seq as spId, b.ten from
								// donhang_ctkm_trakm a inner join sanpham b on a.spMa = b.ma where ";

								// ResultSet sanphamRs = db.get(sql);
								String[] param = new String[8];
								ISanpham sp = null;

								// while (sanphamRs.next())
								// {
								param[0] = rs.getString("spId");
								param[1] = rs.getString("spMa");
								param[2] = rs.getString("ten");
								param[3] = soluong;
								param[4] = donvi;
								param[5] = "0";
								param[6] = schemeName;
								param[7] = "0";

								sp = new Sanpham(param);
								sp.setKhoNVBH(rs.getString("khoNVBH"));
								sp.setCTKMID(ctkmId);
								sp.setTRAKMID(trakmId);
								List<ISpDetail> spDetail = new ArrayList<ISpDetail>();
								query = "\n SELECT top 10 case when KHO.SoLuong > 0 then 0 else 1 end loai  , KHO.AVAILABLE ,KHO.SOLO,ISNULL(DHCT.SOLUONG,0) AS SOLUONG,KHO.NGAYHETHAN,KHO.NGAYNHAPKHO,kho.AVAILABLE as soluongton "
										+ "\n FROM   NHAPP_KHO_CHITIET KHO "
										+ "\n inner join Nhaphanphoi npp on npp.pk_seq = kho.npp_fk "
										+ "\n inner join DonHang dh on dh.pk_seq = " + this.id + "  "
										+ "\n 						AND KHO.NPP_FK= dh.NPP_FK and KHO.KBH_FK = case npp.DUNGCHUNGKENH  when 1 then 100025 else dh.kbh_fk end    "
										+ "\n  						and KHO.ngaynhapkho <='" + ngayhoadon + "'  "
										+ "\n							and KHO.KHO_FK = ([dbo].[KhoKhuyenMai_FK]("
										+ nppId + "," + this.id + " ," + ctkmId + ",null )) "
										+ "\n LEFT JOIN DONHANG_CTKM_TRAKM_CHITIET DHCT on 	DHCT.DonHang_fk = dh.pk_seq "
										+ "\n													and	DHCT.TRAKM_FK="
										+ trakmId + " and  DHCT.ctkm_fk =" + ctkmId
										+ "\n													AND KHO.SOLO=DHCT.SOLO and KHO.NGAYHETHAN = DHCT.ngayhethan AND  DHCT.sanpham_fk="
										+ rs.getString("spId") + "\n	and kho.ngaynhapkho = DHCT.NGAYNHAPKHO "
										+ "\n WHERE  KHO.SANPHAM_FK=" + rs.getString("spId") +
										// "\n and ( KHO.soluong >0 or ISNULL( DHCT.SOLUONG,0) >0 ) " +
										"\n	ORDER BY loai,ISNULL( DHCT.SOLUONG,0) desc,KHO.NGAYHETHAN";

								////System.out.println("DU LIEU lo chi tiet km : " + query);

								ResultSet rsSpDetail = db.get(query);

								while (rsSpDetail.next()) {
									ISpDetail splo = new SpDetail();
									splo.setSolo(rsSpDetail.getString("solo"));
									splo.setNgayhethan(rsSpDetail.getString("NGAYHETHAN"));
									splo.setNgaynhapkho(rsSpDetail.getString("NGAYNHAPKHO"));
									splo.setSoluong("" + rsSpDetail.getDouble("SOLUONG"));
									splo.setSoluongton("" + (rsSpDetail.getDouble("soluongton")));
									spDetail.add(splo);
								}

								rsSpDetail.close();

								sp.setSpDetailList(spDetail);
								scheme_sp.add(sp);
								this.aplaikm = true;
								this.cokm = true;
								// }
								// sanphamRs.close();
							}
						}
					}
				}
				rs.close();
			} catch (Exception e) {
			}
		}
		this.scheme_sanpham = scheme_sp;

	}

	private void getNppInfo() {
		geso.dms.distributor.util.Utility util = new geso.dms.distributor.util.Utility();
		setNppId(util.getIdNhapp(this.userId)); // dùng hàm để lấy 1 số thông tin từ npp
		this.nppTen = util.getTenNhaPP();
		this.sitecode = util.getSitecode();

		// lay ngay khoa so
		this.ngayks = util.ngaykhoaso(this.nppId);
	}

	public boolean Muctindung() {
		if (this.nppId.equals("102961")) {
			if (this.khId.length() > 0) {
				float sotienno = 0;
				if (khId.length() > 0) {
					String sql = " select sotienno,a.pk_seq from khachhang a inner join gioihancongno b on a.ghcn_fk = b.pk_seq where a.pk_seq ='"
							+ this.khId + "'";
					// ////System.out.println("sotienno:"+sql);
					ResultSet tb = db.get(sql);
					if (tb != null) {
						try {
							if (tb.next())
								sotienno = Float.parseFloat(tb.getString("sotienno"));
							else
								sotienno = 0;
							tb.close();
						} catch (Exception e1) {

							e1.printStackTrace();
						}
						if (this.id.length() > 0)
							sql = " select khachhang_fk ,sum(tonggiatri-dathanhtoan) as num from donhang where pk_seq <>'"
									+ this.id + "' and tonggiatri > dathanhtoan and khachhang_fk ='" + this.khId
									+ "' group by khachhang_fk";
						else
							sql = " select khachhang_fk ,sum(tonggiatri-dathanhtoan) as num from donhang where tonggiatri > dathanhtoan and khachhang_fk ='"
									+ this.khId + "' group by khachhang_fk";
						// ////System.out.println("tongtienno:"+sql);
						ResultSet rs = db.get(sql);
						try {

							if (rs != null) {
								if (rs.next()) {
									if (rs.getString("num").length() > 0)
										sotienno = sotienno - Float.parseFloat(rs.getString("num"));
								}
							}
							rs.close();
						} catch (Exception e) {
							e.printStackTrace();
						}
					}

				}
				this.muctindung = sotienno + "";
			}
		} else
			this.muctindung = "99999999999"; // gia tri don hang khong the vuot qua
		return false;
	}

	private String kenh() {
		// String sql ="select a.kbh_fk from giamsatbanhang a,ddkd_gsbh b where a.pk_seq
		// = b.gsbh_fk and b.ddkd_fk ='"+ this.ddkdId +"'";
		String sql = "select kbh_fk from giamsatbanhang where pk_seq ='" + this.gsbhId + "'";
		ResultSet rs = db.get(sql);
		if (rs != null) {
			try {
				rs.next();
				String kbhfk = rs.getString("kbh_fk");
				rs.close();
				return kbhfk;
			} catch (Exception e) {
			}
		}
		return "null";
	}

	public boolean CreateDh(HttpServletRequest request, List<ISanpham> splist) {

		geso.dms.center.util.Utility util_kho = new geso.dms.center.util.Utility();
		// String
		// ngayhoadon_=util_kho.getngayhoadon(this.userId,db,this.ngaygiaodich,this.khId,0);
		String ngayhoadon_ = Utility.getNgayHienTai();
		if (this.nvgnId.trim().length() <= 0) {
			this.msg = "Vui lòng chọn nhân viên giao nhận";
			return false;
		}
		if (this.khoId.trim().length() <= 0) {
			this.msg = "Vui lòng chọn kho ";
			return false;
		}

		if (this.splist.size() <= 0) {
			this.msg = "Vui lòng kiểm tra lại thông tin sản phẩm";
			return false;
		}

		if (this.spThieuList.size() > 0) {
			this.msg = "Trong kho khong du so luong mot so san pham ban chon, vui long chon lai so luong ...";
			return false;
		} else {
			String sanpham = "";
			String sqlCHECK = "";

			for (int m = 0; m < splist.size(); m++) {
				ISanpham sp = splist.get(m);

				String khoNVBH = sp.getKhoNVBH().replaceAll(",", ""); // Luu % VAT
				if (khoNVBH == null || khoNVBH.trim().length() <= 0)
					khoNVBH = "0";

				////System.out.println("----THUE VAT: " + khoNVBH);
				double _ck = geso.dms.center.util.Utility.parseDouble(sp.getChietkhau().replace(",", ""));

				sqlCHECK += " select ma , isnull(PT_VAT,0) ptVat , pk_seq as sanpham_fk, '" + sp.getSoluong()
						+ "' as soluong, round( ( 1 - " + _ck + "/100.0 ) * [dbo].[GiaCkBanLeNppSanPham](" + this.nppId
						+ "," + this.khId + ", a.pk_seq, '" + this.ngaygiaodich + "' ),4)  dongia ," + _ck
						+ " chietkhau " + " from SANPHAM a where ma = '" + sp.getMasanpham() + "' ";
				if (m < splist.size() - 1)
					sqlCHECK += " union ";
			}

			String query = "";

			String ngaytaodh = getDateTime();
			Utility utility = new Utility();

			if (this.sotaikhoan.trim().length() <= 0)
				this.sotaikhoan = " ";

			try {
				db.getConnection().setAutoCommit(false);

				if (geso.dms.center.util.Utility.KiemTraTransaction("KhachHang", "pk_seq", this.khId, db) <= 0) {
					this.msg = "Khách hàng đang được thao tác ở nghiệp vụ khác, vui lòng kiểm tra lại";
					db.getConnection().rollback();
					db.getConnection().setAutoCommit(true);
					return false;
				}

				String today = geso.dms.center.util.Utility.getNgayHienTai();
				db.ClearParam();
				db.AddParam("@donhangkhac", this.donhangKhac);
				db.AddParam("@ngaynhap", this.ngaygiaodich);
				db.AddParam("@nguoitao", this.userId);
				db.AddParam("@nguoisua", this.userId);
				db.AddParam("@ddkd_fk", this.ddkdId);
				db.AddParam("@npp_fk", this.nppId);
				db.AddParam("@kho_fk", this.khoId);
				db.AddParam("@nvgn_fk", this.nvgnId);
				db.AddParam("@ghichu", this.ghichu);
				db.AddParam("@gsbh_fk", this.gsbhId);
				db.AddParam("@khachhang_fk", this.khId);

				query = "\n  insert into DonHang(donhangkhac ,ngaynhap, trangthai, ngaytao, ngaysua, nguoitao, nguoisua, ddkd_fk, gsbh_fk, khachhang_fk, npp_fk, kho_fk, kbh_fk"
						+ "\n, tinhtrang,BM,ASM, IsDonHangLe, IsSampling, nvgn_fk,ghichu, donquatang ,ngaytaodh)  "
						+ "\n  select @donhangkhac, @ngaynhap, '0', '" + today + "','" + today
						+ "', @nguoitao, @nguoisua, @ddkd_fk, gs.pk_seq,kh.pk_seq "
						+ ", @npp_fk, @kho_fk, kh.kbh_fk, '0',asm.BM_FK ,gs.asm_fk, 0, 0, @nvgn_fk,@ghichu, 0,GETDATE() "
						+ "\n from khachhang kh " + "\n left join giamsatbanhang gs on gs.pk_seq = @gsbh_fk "
						+ "\n left join asm on asm.pk_seq = gs.asm_fk " + "\n where kh.pk_seq = @khachhang_fk  ";
				if (db.update_with_param(query) != 1) {
					this.msg = "Lỗi tạo mới đơn hàng";
					db.getConnection().rollback();
					db.getConnection().setAutoCommit(true);
					return false;
				}

				String idTmp = db.getPk_seq();

				query = "\n select case when npp.DUNGCHUNGKENH = 1 then 100025 else d.KBH_FK end as KBH_FK "
						+ "\n from donhang d " + "\n inner join NHAPHANPHOI npp on npp.PK_SEQ = d.NPP_FK "
						+ "\n where d.PK_SEQ = " + idTmp;
				ResultSet rs = db.get(query);
				rs.next();
				String _kbh_kho = rs.getString("KBH_FK");//

				query = "\n insert donhang_sanpham( KBH_FK,sanpham_fk, donhang_fk, soluong, kho_fk, giamua, chietkhau, THUEVAT, dongiaGOC,isnhapkhau,chietkhaungay ,program,ckprogram) "
						+ "\n select " + _kbh_kho + ",sp.sanpham_fk, '" + idTmp + "', sp.soluong, '" + this.khoId
						+ "', sp.dongia, sp.chietkhau, sp.ptVat , " + "\n [dbo].[GiaCkBanLeNppSanPham]("+this.nppId+","
						+ "kh.pk_seq,sp.sanpham_fk,'" + this.ngaygiaodich + "' ) as dongiaGOC"
						+ "\n			,	1 as isnhapkhau," + this.chietkhau
						+ ",ISNULL(b.scheme,''),ISNULL(b.ChietKhau,0) \n" + "\n from ( " + sqlCHECK + " ) sp "
						+ "\n inner join khachhang kh on kh.pk_seq = " + this.khId
						+ "\n left join KhachHang_ChietKhau b on b.KhachHang_fk =kh.pk_seq  and b.Sanpham_FK =sp.sanpham_fk  ";
				////System.out.println("cau update lai gia " + query);
				if (db.updateReturnInt(query) < 0) {
					db.getConnection().rollback();
					db.getConnection().setAutoCommit(true);
					this.msg = "4.Khong the cap nhat table 'Don Hang San pham' , " + query;
					return false;
				}

				if (this.quanlykho.equals("1"))

				{
					String kq = InsertDonhang_SanPham_ChiTiet(splist, db, idTmp, this.nppId, this.ngaygiaodich,
							ngayhoadon_,_kbh_kho);
					// String kq = dexuatloDonHang(db, this.id,this.nppId , this.ngaygiaodich );
					if (kq.trim().length() > 0) {
						this.msg = kq;
						db.getConnection().rollback();
						db.getConnection().setAutoCommit(true);
						return false;
					}
				}

				Utility util = new Utility();

				if (this.quanlykho.equals("1")) {
					query = "   select count(*) kq from  ( \n"
							+ "	 select SUM(SOLUONG) SL,SANPHAM_FK from DONHANG_SANPHAM a where DONHANG_FK=" + idTmp
							+ "  group by SANPHAM_FK) \n" + "	   AA \n" + "	 full outer join ( \n"
							+ "	  select SUM(SOLUONG) SL,SANPHAM_FK from DONHANG_SANPHAM_chitiet a where DONHANG_FK="
							+ idTmp + "  group by SANPHAM_FK) \n" + "	   BB on AA.SANPHAM_FK=BB.sanpham_fk \n"
							+ "	  where ISNULL(AA.SL,0)<>ISNULL(BB.SL,0)  ";

					ResultSet ckek = db.get(query);
					ckek.next();
					if (ckek.getInt("kq") != 0) {
						this.msg = "số lượng tổng và chi tiết không khớp nhau ";
						db.getConnection().rollback();
						db.getConnection().setAutoCommit(true);
						return false;
					}
					ckek.close();
				}

				String kqKM = LuuKhuyenMai(db, idTmp, request, _kbh_kho);
				if (kqKM.length() > 0) {
					this.msg = kqKM;
					db.getConnection().rollback();
					return false;
				}

				rs.close();
				String bookKho =  BookedKhoDonHang(idTmp, DONHANGSVL_CLICK, ngayhoadon_, util_kho);
				if(bookKho.length() > 0) {
					this.msg = bookKho;
					db.getConnection().rollback();
					return false;
				}
 				// check ngansach
				String npp = "";
				String khachhang = "";

				query = "\n select npp_fk,khachhang_fk from donhang where pk_Seq = " + idTmp;
				rs = db.get(query);
				while (rs.next()) {
					npp = rs.getString("npp_fk");
					khachhang = rs.getString("khachhang_fk");
				}
				rs.close();
				query = "\n select ctkmId,max(soxuat) soxuat ,sum(tonggiatri)  tonggiatri "
						+ "\n from donhang_ctkm_trakm dhkm " + "\n where donhangId = " + idTmp + "\n group by ctkmId  ";
				rs = db.get(query);
				while (rs.next()) {
					String ctkmId = rs.getString("ctkmId");
					String checkmsg = Check_ngan_sach_sau_luu(ctkmId, npp, khachhang, db, " and dh.trangthai!=2 ");
					this.msg += checkmsg;
				}

				if (this.msg.trim().length() > 0) {
					db.getConnection().rollback();
					return false;
				}
				rs.close();

				String kqVat = geso.dms.center.util.Utility.CheckVat(db, "DONHANG_SANPHAM", "THUEVAT", "DONHANG_FK",
						idTmp);
				if (kqVat.trim().length() > 0) {
					this.msg = kqVat;
					db.getConnection().rollback();
					db.getConnection().setAutoCommit(true);
					return false;
				}

				String msgTGT = Utility.Update_GiaTri_DonHang(idTmp, db);
				if (msgTGT.length() > 0) {
					this.msg = msgTGT;
					db.getConnection().rollback();
					db.getConnection().setAutoCommit(true);
					return false;
				}

				db.getConnection().commit();
				db.getConnection().setAutoCommit(true);
				this.id = idTmp;
				return true;

			} catch (Exception e) {
				this.msg = "Lỗi  : " + e.getMessage();
				geso.dms.center.util.Utility.rollback_throw_exception(this.db);
				e.printStackTrace();
				return false;

			}
		}

	}

	public boolean UpdateDh(HttpServletRequest request, List<ISanpham> splist, String action) {
		// geso.dms.center.util.Utility util_kho =new geso.dms.center.util.Utility();
		String ngayhoadon_ = Utility.getNgayHienTai();// util_kho.getngayhoadon(this.userId,db,this.ngaygiaodich,this.khId,0);
		 geso.dms.center.util.Utility util_kho =new geso.dms.center.util.Utility();
		////System.out.println("vao update rui ne22------------------------------------------");

		if (this.nvgnId.trim().length() <= 0) {
			this.msg = "Vui lòng chọn nhân viên giao nhận";
			return false;
		}

		if (this.splist.size() <= 0) {
			this.msg = "Vui lòng kiểm tra lại thông tin sản phẩm";
			return false;
		}

		if (this.spThieuList.size() > 0) {
			this.msg = "Trong kho khong du so luong mot so san pham ban chon, vui long chon lai so luong...";
			return false;
		}

		String sqlCHECK = "";
		Object[] data2 = null;
		for (int m = 0; m < splist.size(); m++) {
			ISanpham sp = splist.get(m);
			double _ck = geso.dms.center.util.Utility.parseDouble(sp.getChietkhau().replace(",", ""));

			Object[] temp = dbutils.setObject(sp.getSoluong(), _ck + "", sp.getMasanpham());

			if (m == 0) {
				data2 = temp;
			} else {
				data2 = appendObjectArrayValue(data2, temp);
			}
			sqlCHECK += " select ma ,pk_seq as sanpham_fk, ? as soluong, round( ( 1 - " + _ck+ "/100.0 ) * [dbo].[GiaCkBanLeNppSanPham](" + this.nppId + "," + this.khId + ", a.pk_seq, '"
					+ this.ngaygiaodich + "' ),4)   dongia, ? as chietkhau ,isnull(PT_VAT,0) as ptVat  from SANPHAM a where ma = ? ";
			if (m < splist.size() - 1)
				sqlCHECK += " union ";
		}

		try {
			String sql = "select kbh_fk as kbhOld, " + "	( select kbh_fk from khachhang where pk_seq='" + this.khId
					+ "'  ) as kbhNew, " + "	( select count(*) from DONHANG_CTKM_TRAKM where DONHANGID = '" + this.id
					+ "' ) as coKM	" + "from donhang where pk_seq = '" + this.id + "'  ";
			////System.out.println("_____sql" + sql);
			ResultSet rs = this.db.get(sql);
			String kbhOld = "";
			String kbhNew = "";
			boolean coKM = false;

			if (rs.next()) {
				kbhOld = rs.getString("kbhOld");
				kbhNew = rs.getString("kbhNew");
				if (rs.getInt("coKM") > 0)
					coKM = true;
			}
			rs.close();

			if (!kbhNew.equals(kbhOld)) {
				this.msg = "Khong The Luu Don Hang Voi Khach Hang Co Kenh  Khac Kenh Luc Tao Moi Don Hang, Vui Long Chon Lai Khach Hang, Hay Doi Kenh Cua Khach Hang";
				return false;
			}

			// if (!this.loainpp.equals("0"))

			if (this.quanlykho.equals("1")) {
				String msgch = this.check_solo_tong_va_chitiet(splist, "(Hàng bán)");
				if (msgch.length() > 0) {
					this.msg = msgch;
					return false;
				}
				msgch = this.check_solo_tong_va_chitiet(this.list_sanphamkm_capnhatlo, "(Hàng bán)");
				if (msgch.length() > 0) {
					this.msg = msgch;
					return false;
				}
			}

			// NEU LA CAP NHAT, MA THAY DOI SP HOAC SO LUONG THI PHAI AP LAI KM
			if (action.equals("save") && coKM && this.donhangKhac.equals("0")) {
				// Đối lại check 1 loạt sản phẩm của đơn hàng luôn, không cấn check từng SP
				sql = "select count(dh.sanpham_fk) as sodong  " + "from DONHANG_SANPHAM dh " + "left join  ( "
						+ sqlCHECK + " ) dh_sp on dh.sanpham_fk = dh_sp.sanpham_fk " + "where donhang_fk = '" + this.id
						+ "' and dh.soluong != dh_sp.soluong";
				////System.out.println("sql check la " + sql);
				int soDONG = 0;
				rs = db.get_v2(sql, data2);
				if (rs.next()) {
					soDONG = rs.getInt("soDONG");
				}
				rs.close();

				if (soDONG > 0) {
					this.msg = "Khi thay đổi thông tin sản phẩm, số lượng trong đơn hàng, bạn phải bấm áp lại khuyến mại.";
					return false;
				}
			}
			// Thay doi ngay va ma KH
			// NEU LA CAP NHAT, MA THAY DOI SP HOAC SO LUONG THI PHAI AP LAI KM
			if (action.equals("save") && coKM && this.donhangKhac.equals("0")) {
				sql = "select count(dh.pk_seq) as sodong  " + "from DONHANG dh " + "where pk_Seq = '" + this.id
						+ "' and dh.ngaynhap <> '" + this.ngaygiaodich + "'";
				////System.out.println("sql check ngaydh: " + sql);
				int soDONG = 0;
				rs = db.get(sql);
				if (rs.next()) {
					soDONG = rs.getInt("soDONG");
				}
				rs.close();

				sql = "select count(dh.pk_seq) as sodong  " + "from DONHANG dh " + "where pk_Seq = '" + this.id
						+ "' and dh.khachhang_fk <> '" + this.khId + "'";
				////System.out.println("sql check makh: " + sql);
				rs = db.get(sql);
				if (rs.next()) {
					soDONG += rs.getInt("soDONG");
				}
				rs.close();

				if (soDONG > 0) {
					this.msg = "Khi thay đổi ngày đơn hàng, mã khách hàng trên đơn bạn phải bấm áp lại khuyến mại.";
					return false;
				}

			}
		} catch (Exception er) {
			this.msg = "Lỗi khi lưu đơn hàng: " + er.getMessage();
			er.printStackTrace();
			return false;
		}

		ResultSet rs;
		try {

			db.getConnection().setAutoCommit(false);

			String query = "";

			String kbh_fk = "", BM_fk = "", ASM_fk = "", ngaysua = "convert(varchar(10), getdate() , 126)";
			query = "select kbh_fk from khachhang where pk_seq = " + this.khId;
			rs = this.db.get(query);
			rs.next();
			kbh_fk = rs.getString("kbh_fk");

			query = " select gs.bm_fk, gs.asm_fk  from giamsatbanhang gs  where gs.pk_seq = " + this.gsbhId + " 	";

			////System.out.println("__" + query);

			rs = this.db.get(query);
			while (rs.next()) {
				BM_fk = rs.getString("bm_fk");
				ASM_fk = rs.getString("asm_fk");
			}

			if (BM_fk == null || BM_fk.length() == 0)
				BM_fk = "NULL";
			if (ASM_fk == null || ASM_fk.length() == 0)
				ASM_fk = "NULL";

			String ngaykyHd = "(select ngaykyHd from khachhang where pk_Seq='" + this.khId + "') ";
			String HCH_FK = "(select HCH_FK from khachhang where pk_Seq='" + this.khId + "') ";
			Object[] data = null;
			data = dbutils.setObject(this.tiengiamtru, this.donhangKhac, this.ngaygiaodich, this.ddkdId, this.gsbhId,
					this.khId, this.khoId, this.userId, this.tongtientruocVAT, kbh_fk, BM_fk, ASM_fk, this.IsDonHangLe,
					this.IsSampling, this.nvgnId, this.chietkhau, this.denghitraCKTHANG, this.ghichu, this.donquatang,
					this.sotaikhoan, this.ingia);

			query = "update donhang set tiengiamtru= ?,checkkho=1, HCH_FK= " + HCH_FK + ",NgayKyHD= " + ngaykyHd
					+ ",donhangkhac = ? , ngaynhap = ?, ddkd_fk = ?, " + "gsbh_fk = ?, khachhang_fk = ?, KHO_FK = ?, "
					+ "ngaysua = " + ngaysua + ", nguoisua = ?, vat = ?, "
					+ "kbh_fk = ? ,BM = ?, ASM = ?, IsDonHangLe = ?, IsSampling = ?, "
					+ "NGAYGIO = GETDATE(), nvgn_fk = ?, chietkhau = ?, denghitraCK = ?, ghichu = ?, donquatang = ?, sotaikhoan = ? , ingia= ?	 where pk_seq = "
					+ this.id + "  and TrangThai =0 ";

			////System.out.println("[DonHang]" + query);

			if (db.update_v2(query, data) != 1) {
				dbutils.viewQuery(query, data);
				db.getConnection().rollback();
				db.getConnection().setAutoCommit(true);
				this.msg = "1.Khong the cap nhat table 'Don Hang'(ĐH đã chốt hoặc xảy ra lỗi) , " + query;
				return false;
			}

			String noidung = "Đơn hàng trước chỉnh sửa: \n";
			query = "select b.MA, b.ten, c.DONVI, a.SOLUONG, a.GIAMUA from DONHANG_SANPHAM a inner join sanpham b on a.SANPHAM_FK = b.PK_SEQ \n"
					+ "inner join DONVIDOLUONG c on b.DVDL_FK = c.PK_SEQ where DONHANG_FK = " + this.id;
			ResultSet rsdh = db.get(query);
			while (rsdh.next()) {
				noidung += "	MASP: " + rsdh.getString("ma") + "; TENSP: " + rsdh.getString("ten") + "; DONVI: "
						+ rsdh.getString("donvi") + ";	SOLUONG: " + rsdh.getString("soluong") + ";	GIA: "
						+ rsdh.getString("giamua") + "\n";
			}
			rsdh.close();
			String msg123 = revertDonhang(this.id);
			if(msg123.length() > 0) {
				this.msg = msg123;
				db.getConnection().rollback();
				return false;
			}
			query = "delete from donhang_sanpham where donhang_fk = " + this.id	+ " and donhang_Fk in (select pk_seq from DonHang where trangthai = 0 and pk_Seq = '" + this.id	+ "') ";
			if (db.updateReturnInt(query) <= 0) {
				db.getConnection().rollback();
				db.getConnection().setAutoCommit(true);
				this.msg = "3.Khong the cap nhat table 'Don Hang San pham' , " + query;
				return false;
			}
			query = "delete from donhang_sanpham_chitiet where donhang_fk = " + this.id
					+ " and donhang_Fk in (select pk_seq from DonHang where trangthai = 0 and pk_Seq = '" + this.id
					+ "') ";
			if (!db.update(query)) {
				db.getConnection().rollback();
				db.getConnection().setAutoCommit(true);
				this.msg = "3.Khong the cap nhat table 'Don Hang San pham' , " + query;
				return false;
			}

		

			query = "\n select case when npp.DUNGCHUNGKENH = 1 then 100025 else d.KBH_FK end as KBH_FK "
					+ "\n from donhang d " + "\n inner join NHAPHANPHOI npp on npp.PK_SEQ = d.NPP_FK "
					+ "\n where d.PK_SEQ = " + this.id;
			rs = db.get(query);
			rs.next();
			String _kbhKho = rs.getString("KBH_FK");

			query = "\n if exists (select PK_SEQ from DONHANG WHERE PK_SEQ = '" + this.id + "' AND TRANGTHAI = 0)"
					+ "\n insert donhang_sanpham( KBH_FK,sanpham_fk, donhang_fk, soluong, kho_fk, giamua, chietkhau, THUEVAT, dongiaGOC,isnhapkhau,chietkhaungay ,program,ckprogram) "
					+ "\n select " + _kbhKho + ",sp.sanpham_fk, '" + this.id + "', sp.soluong, '" + this.khoId

					+ "', sp.dongia, sp.chietkhau, sp.ptVat , " + "\n [dbo].[GiaCkBanLeNppSanPham]("
					+ this.nppId + ","+this.khId+",sp.sanpham_fk,'" + this.ngaygiaodich + "' ) as dongiaGOC"
					+ "\n			,	1 as isnhapkhau," + this.chietkhau
					+ ",ISNULL(b.scheme,''),ISNULL(b.ChietKhau,0) \n" + "\n from ( " + sqlCHECK + " ) sp "
					+ "\n left join KhachHang_ChietKhau b on b.KhachHang_fk =" + this.khId
					+ " and b.Sanpham_FK =sp.sanpham_fk  ";
			////System.out.println("cau update lai gia " + query);
			if (db.update_v2(query, data2) < 0) {
				db.getConnection().rollback();
				db.getConnection().setAutoCommit(true);
				this.msg = "4.Khong the cap nhat table 'Don Hang San pham' , " + query;
				return false;
			}

			String kqVat = geso.dms.center.util.Utility.CheckVat(db, "DONHANG_SANPHAM", "THUEVAT", "DONHANG_FK",
					this.id);
			if (kqVat.trim().length() > 0) {
				this.msg = kqVat;
				db.getConnection().rollback();
				db.getConnection().setAutoCommit(true);
				return false;
			}

			if (this.quanlykho.equals("1")) {
				String kq = InsertDonhang_SanPham_ChiTiet(splist, this.db, this.id, this.nppId, this.ngaygiaodich,
						ngayhoadon_,_kbhKho);
				// String kq = dexuatloDonHang(db, this.id, this.nppId, this.ngaygiaodich);
				if (kq.trim().length() > 0) {
					this.msg = kq;
					db.getConnection().rollback();
					db.getConnection().setAutoCommit(true);
					return false;
				}
			}
			if (action.equals("save")) {
				query = "\n select scheme from CTKHUYENMAI ctkm "
						+ "\n inner join DONHANG_CTKM_TRAKM dhkm on dhkm.CTKMID = ctkm.PK_SEQ  "
						+ "\n where dhkm.DONHANGID = " + this.id
						+ "\n 	and not exists ( select 1 from donhang dh where dh.PK_SEQ = dhkm.DONHANGID and dh.NGAYNHAP >= ctkm.TUNGAY and dh.NGAYNHAP <= ctkm.DENNGAY) ";
				rs = db.get(query);
				if (rs.next()) {
					this.msg = "Khi thay đổi thông tin ngày chứng từ bạn phải bấm áp lại khuyến mại.";
					db.getConnection().rollback();
					db.getConnection().setAutoCommit(true);
					return false;

				}

				if (this.quanlykho.equals("1")) {/*
					String kq = UpdateDonhang_CTKM_TraKM_ChiTiet(this.list_sanphamkm_capnhatlo, this.db, this.id,
							this.nppId, this.ngaygiaodich, "Hàng KM", ngayhoadon_);
					if (kq.trim().length() > 0) {
						this.msg = kq;
						db.getConnection().rollback();
						db.getConnection().setAutoCommit(true);
						return false;
					}
				*/}
			} else // lưu km
			{
				////System.out.println("-- vo else : "+ action);
				String kq = this.capNhatKM(this.id, this.nppId, this.khId, this.trangthai, this.db);
				if (kq.trim().length() > 0) {
					this.msg = kq;
					db.getConnection().rollback();
					db.getConnection().setAutoCommit(true);
					return false;
				}
				
				if(!action.equals("khongapkhuyenmai"))
				{
					String kqKM = LuuKhuyenMai(db, this.id, request, _kbhKho);
					if (kqKM.length() > 0) {
						this.msg = kqKM;
						db.getConnection().rollback();
						return false;
					}
				}
			}

			// check ngansach
			String npp = "";
			String khachhang = "";

			query = "\n select npp_fk,khachhang_fk from donhang where pk_Seq = " + this.id;
			rs = db.get(query);
			if (rs.next()) {
				npp = rs.getString("npp_fk");
				khachhang = rs.getString("khachhang_fk");
			}
			rs.close();
			query = "\n select ctkmId,max(soxuat) soxuat ,sum(tonggiatri)  tonggiatri "
					+ "\n from donhang_ctkm_trakm dhkm " + "\n where donhangId = " + this.id + "\n group by ctkmId  ";
			rs = db.get(query);
			while (rs.next()) {
				String ctkmId = rs.getString("ctkmId");
				String checkmsg = Check_ngan_sach_sau_luu(ctkmId, npp, khachhang, db, " and dh.trangthai!=2 ");
				this.msg += checkmsg;
			}

			if (this.msg.trim().length() > 0) {
				db.getConnection().rollback();
				return false;
			}
			rs.close();

			query = "if exists (select PK_SEQ from DONHANG WHERE PK_SEQ = '" + this.id + "' AND TRANGTHAI = 0)"
					+ "update donhang_sanpham set THANHTIEN = (soluong * giamua - chietkhau) + TIENVAT, TIENVAT = ( soluong * giamua - chietkhau ) * THUEVAT / 100 where donhang_fk = "
					+ this.id;
			////System.out.println("::: CAP NHAT LAI THANH TIEN: " + query);
			if (db.updateReturnInt(query) <= 0) {
				db.getConnection().rollback();
				this.msg = "6.Khong the cap nhat table 'Don Hang San pham' , " + query;
				return false;
			}

			if (this.quanlykho.equals("1")) {
				query = "   select count(*) kq from  ( \n"
						+ "	 select SUM(SOLUONG) SL,SANPHAM_FK from DONHANG_SANPHAM a where DONHANG_FK=" + this.id
						+ "  group by SANPHAM_FK) \n" + "	   AA \n" + "	 full outer join ( \n"
						+ "	  select SUM(SOLUONG) SL,SANPHAM_FK from DONHANG_SANPHAM_chitiet a where DONHANG_FK="
						+ this.id + "  group by SANPHAM_FK) \n" + "	   BB on AA.SANPHAM_FK=BB.sanpham_fk \n"
						+ "	  where ISNULL(AA.SL,0)<>ISNULL(BB.SL,0)  ";

				ResultSet ckek = db.get(query);
				ckek.next();
				if (ckek.getInt("kq") != 0) {
					this.msg = "số lượng tổng và chi tiết không khớp nhau ";
					db.getConnection().rollback();
					db.getConnection().setAutoCommit(true);
					return false;
				}
				ckek.close();
			}

			noidung += "Đơn hàng sau chỉnh sửa: \n";
			query = "select b.MA, b.ten, c.DONVI, a.SOLUONG, a.GIAMUA from DONHANG_SANPHAM a inner join sanpham b on a.SANPHAM_FK = b.PK_SEQ \n"
					+ "inner join DONVIDOLUONG c on b.DVDL_FK = c.PK_SEQ where DONHANG_FK = " + this.id;
			rsdh = db.get(query);
			while (rsdh.next()) {
				noidung += "MASP: " + rsdh.getString("ma") + "; TENSP: " + rsdh.getString("ten") + "; DONVI: "
						+ rsdh.getString("donvi") + ";	SOLUONG: " + rsdh.getString("soluong") + ";	GIA: "
						+ rsdh.getString("giamua") + "\n";
			}
			rsdh.close();

			data = null;
			String nd = "Sửa đơn hàng " + this.id + ".\n" + noidung + "";
			data = dbutils.setObject(nd);

			query = "insert into thongbao(tieude,noidung,filename,nguoitao,ngaytao,nguoisua,ngaysua,ngaybatdau,ngayketthuc,trangthai, loaithongbao, hethieuluc)"
					+ " values(N'Thông báo sửa đơn hàng do user ' + (select ten from nhanvien where pk_seq="
					+ this.userId + ") , ? ,0, '" + this.userId + "', convert(char(10),getdate(),126), '" + this.userId
					+ "',convert(char(10),getdate(),126),convert(char(10),getdate(),126),convert(char(10),getdate(),126),'"
					+ "1" + "', '5', '0')";
			if (db.update_v2(query, data) != 1) {
				////System.out.println("erro " + query);
				this.msg = "lỗi gửi thông báo" + query;
				db.getConnection().rollback();
				db.getConnection().setAutoCommit(true);
				return false;
			}

			String ddkd_fk = "";
			String ASM_FK = "";
			String GSBH_FK = "";
			query = "select  DDKD_FK,gsbh_fk from donhang where pk_seq=" + this.id;
			rsdh = db.get(query);
			while (rsdh.next()) {
				ddkd_fk = rsdh.getString("DDKD_FK");
				GSBH_FK = rsdh.getString("gsbh_fk");
			}
			rsdh.close();

			query = "Insert THONGBAO_DDKD(thongbao_fk, ddkd_fk, trangthai) select IDENT_CURRENT('thongbao'), pk_seq, 0 "
					+ "from DaiDienKinhDoanh where pk_seq in ( " + ddkd_fk + " ) ";
			if (!db.update(query)) {
				////System.out.println("erro " + query);
				this.msg = "Không thể tạo mới THONGBAO_DDKD " + query;
				db.getConnection().rollback();
				db.getConnection().setAutoCommit(true);
				return false;
			}

			String msgTGT = Utility.Update_GiaTri_DonHang(this.id, db);
			if (msgTGT.length() > 0) {
				this.msg = msgTGT;
				db.getConnection().rollback();
				db.getConnection().setAutoCommit(true);
				return false;
			}
			String bookKho = BookedKhoDonHang(this.id, DONHANGUPDATESVL_CLICK, ngayhoadon_, util_kho);
			if(bookKho.length() > 0) {
				this.msg = bookKho;
				db.getConnection().rollback();
				return false;
			}
			db.getConnection().commit();
			db.getConnection().setAutoCommit(true);
			return true;

		} catch (Exception e) {
			e.printStackTrace();
			geso.dms.center.util.Utility.rollback_throw_exception(db);
			this.msg = " Exception : " + e.getMessage();
			return false;
		}

	}

	private String pxkId = "";
	private String ngayxuatkho = "";

	public void createPxkId() {
		String query = "select distinct a.pxk_fk, b.ngaylapphieu from phieuxuatkho_donhang a inner join phieuxuatkho b on a.pxk_fk = b.pk_seq where a.donhang_fk = '"
				+ this.id + "'";
		ResultSet rs = db.get(query);
		////System.out.println("Query vao lay Phieu Xuat Kho La: " + query + "\n");
		try {
			rs.next();
			this.pxkId = rs.getString("pxk_fk");
			this.ngayxuatkho = rs.getString("ngaylapphieu");
			rs.close();
		} catch (Exception e) {
		}
	}

	public void createRS() {
		this.getNppInfo();
		this.InitCtkmDiemRs();
		this.TinhDiemKhuyenMai();
		this.createDdkd();
		this.createChietkhau();
		this.createBgstId();
		this.createKho();
		this.CreateNvgnList();
		this.checkInfo();
		this.Muctindung();
		this.rshangkh = db.get("select pk_seq,DIENGIAI from hangcuahang where TRANGTHAI=1 ");
		////System.out.println("olalalalalalala ++++++++       select pk_seq,DIENGIAI from hangcuahang where TRANGTHAI=1 ");
		if (this.id.trim().length() > 0) {
			this.initTichLuy();

			// Init CHIET KHAU BO SUNG
			String query = "select maloai, diengiai, vat from LOAICHIETKHAU where pk_seq > 0 and maloai in ( 'CT10', 'CQB10',  'CQX10' ) ";

			String condition = "";
			if (this.tichluy_scheme != null) {
				for (int i = 0; i < this.tichluy_scheme.length; i++) {
					condition += "'" + this.tichluy_scheme[i] + "',";
				}
				if (condition.trim().length() > 0) {
					condition = condition.substring(0, condition.length() - 1);
					query += " and maloai not in ( " + condition + " ) ";
				}
			}
			////System.out.println("--SQL TICH LUY: " + query);
			this.ckbsList = db.getScrol(query);
		}

	}

	private void checkInfo() {
		if (this.ngaygiaodich.length() > 0 && this.khId.length() > 0) {
			String query = "select count(*) as sodong from donhang where khachhang_fk = '" + this.khId
					+ "' and ngaynhap = '" + this.ngaygiaodich + "'";
			ResultSet rs = db.get(query);
			try {
				if (rs.next()) {
					if (rs.getInt("sodong") > 0)
						this.dacoDh = true; // khach hang da mua hang trong ngay
					rs.close();
				}
				if (this.id.length() > 0 && this.trangthai.equals("0")) // da xuat kho nhung chua chot pxk
				{
					query = "select count(*) as sodong from phieuxuatkho_donhang where donhang_fk = '" + this.id + "'";
					////System.out.println("Cau lenh check phieuxuatkho: " + query);
					ResultSet xuatkho = db.get(query);
					if (xuatkho.next()) {
						if (xuatkho.getInt("sodong") > 0)
							this.daxuatkhoChuachot = true;
						xuatkho.close();
					}
				}
			} catch (Exception e) {
			}
		}
	}

	private void createDdkd() {
		// tao gsbh
		String sql = "select a.pk_seq,a.ten from giamsatbanhang a inner join NHAPP_GIAMSATBH b on a.pk_seq = b.gsbh_fk where   b.ngaybatdau <='"
				+ this.getDateTime() + "' and b.ngayketthuc >='" + getDateTime()
				+ "' and   a.trangthai = '1' and npp_fk ='" + this.nppId + "'";

		if (this.ddkdId.length() > 0) {
			sql += " union select a.pk_seq,a.ten from giamsatbanhang a where a.pk_Seq in (select gsbh_fk from  ddkd_gsbh where ddkd_fk='"
					+ this.ddkdId + "') ";
		}
		this.gsbhs = db.get(sql);

		////System.out.println("---GSBH ID: " + this.gsbhId + sql);
		String query = "";

		if (this.khId.length() > 0) {

			query = "\n select ten as ddkdTen, pk_seq as ddkdId from daidienkinhdoanh  "
					+ "\n where  PK_SEQ in (select ddkd_fk from TUYENBANHANG a inner join KHACHHANG_TUYENBH b on b.TBH_FK=a.PK_SEQ where b.KHACHHANG_FK='"
					+ this.khId + "' ) "
					+ "\n 	and pk_seq in ( select ddkd_fk from DAIDIENKINHDOANH_NPP where npp_fk = '" + this.nppId
					+ "' ) ";

			////System.out.println("--INIT DDKD: " + query);

			this.ddkdlist = db.get(query);
		}

	}

	private void createBgstId() {
		ResultSet rs = db.get("select banggiasieuthi_fk from bgst_khachhang where khachhang_fk = '" + this.khId + "'");
		String st = "";
		if (rs != null) {
			try {
				while (rs.next()) {
					st = st + rs.getString("banggiasieuthi_fk") + "-";
				}
				// if (rs.getString("bgst_fk") != null)
				if (st.length() > 0)
					this.bgstId = st;// rs.getString("bgst_fk");
				else
					this.bgstId = "0";
				rs.close();
			} catch (Exception e) {
			}
		} else
			this.bgstId = "0";
	}

	private void createChietkhau() {
		// ////System.out.println("----TRANG THAI: " + this.trangthai );
		if (this.trangthai.equals("1") || this.trangthai.equals("3") || this.khId.trim().length() <= 0)
			return;

		if (this.chietkhau.equals("0") || this.chietkhau == "" || this.chietkhau.equals("0.0")
				|| this.trangthai.equals("0") && this.khId.trim().length() > 0) {
			// String sql ="select ISNULL(b.chietkhau,0 ) as chietkhau from KHACHHANG a left
			// join MUCCHIETKHAU b on a.CHIETKHAU_FK = b.pk_seq where a.PK_SEQ = '" + khId +
			// "'";

			String sql = "select a.xuatkhau, ( select loaiNPP from NHAPHANPHOI where pk_seq = a.npp_fk ) as loaiNPP, isnull( PT_CHIETKHAU, 0) as pt_chietkhau "
					+ "from khachhang a " + "where a.trangthai = '1' and a.npp_fk = '" + nppId + "' and a.pk_seq = '"
					+ this.khId + "' ";
			////System.out.println("----KHACH HANG: " + sql);

			ResultSet rs = db.get(sql);
			{
				try {
					if (rs.next()) {
						String banbuon = rs.getString("xuatkhau");
						String loaiNPP = rs.getString("loaiNPP");

						{
							// this.chietkhau = rs.getString("PT_CHIETKHAU");
							this.chietkhauNNK = rs.getString("PT_CHIETKHAU");
							////System.out.println("chiet khau nk " + this.chietkhau + "---------" + this.chietkhauNNK);
						}
					}
					rs.close();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
	}

	private void createKho() {
		geso.dms.center.util.Utility utilCenter = new geso.dms.center.util.Utility();
		String query = "\n select distinct PK_SEQ as khoId, Ten, Diengiai from KHO " + "\n where  pk_seq in "
				+ utilCenter.quyen_kho(this.userId) + " and pk_seq != 100001 order by PK_SEQ ASC ";
		////System.out.println("Quyền kho: " + query);
		this.kholist = db.get(query);
	}

	private void CreateSpList(HttpServletRequest request) {
		DecimalFormat df2 = new DecimalFormat("#,###,###,##0.00");
		String command = "";
		String LINKSERVER_DB = request.getServletContext().getInitParameter("LINKSERVER_DB");
		if (this.quanlykho.trim().equals("0")) {
			command =
					 
					 
					"\n select DISTINCT a.stt,b.pk_seq as spId, b.ma as spMa, b.ten as spTen, a.soluong    "
							+ "\n ,isnull(c.donvi, 'Chua xac dinh') as donvi, a.giamua as dongia,''  program, isnull(a.dongiagoc,a.giamua) dongiagoc,isnull(a.ckprogram,0) program_chietkhau,a.chietkhau, a.THUEVAT,  d.AVAILABLE hienhuu, "
							+ "\n 1 as soluong1, 1 as soluong2, a.khoNVBH,isnull(a.isnhapkhau,1) isnhapkhau "
							+ "\n from donhang_sanpham a inner join sanpham b on a.sanpham_fk = b.pk_seq "
							+ "\n inner join Nhaphanphoi npp on npp.pk_seq ='" + this.nppId + "'   "
							+ "\n left join donvidoluong c on b.dvdl_fk = c.pk_seq  " +
							// "\n inner join NHAPP_KHO d on b.PK_SEQ = d.SANPHAM_FK and d.KHO_FK = '" +
							// this.khoId + "' and d.NPP_FK = '" + this.nppId + "' " +
							
							"\n OUTER APPLY " +
							"\n ( " +
							"\n 	SELECT  SUM(AVAILABLE *isnull(qc.qd,0) ) AVAILABLE "+
							"\n 	FROM "+ LINKSERVER_DB +".[dbo].[ERP_KHOTT_SP_CHITIET] K " +
							"\n 	INNER JOIN "+ LINKSERVER_DB +".[dbo].NHAPHANPHOI NPP_E ON NPP_E.PK_SEQ = K.NPP_FK " +
							"\n 	INNER JOIN "+ LINKSERVER_DB +".[dbo].ERP_SANPHAM SP ON SP.PK_SEQ = K.SANPHAM_FK " +
							"\n 	inner join " + LINKSERVER_DB + ".dbo.Donvidoluong dv_e ON dv_e.PK_SEQ = SP.dvdl_fk  " +
							"\n 	outer apply " +
							"\n 	(  " +
							"\n 		select avg( soluong1/soluong2)qd from QUYCACH qc  " +
							"\n 		inner join DONVIDOLUONG dv_dms on qc.DVDL2_FK = dv_dms.PK_SEQ  " +
							"\n 		where dv_dms.DONVI = dv_e.donvi and qc.DVDL1_FK = b.DVDL_FK and qc.SANPHAM_FK = b.PK_SEQ  " +
							"\n 	)qc " +
							"\n 	WHERE SP.MA = b.MA AND NPP_E.MA = NPP.MA AND K.NGAYNHAPKHO <= '"+this.ngaygiaodich +"' GROUP BY SP.MA, NPP_E.MA " +
							"\n ) as d " +
							"\n where a.donhang_fk = '" + this.id + "'" + "\n order by a.stt  ";
		} else {
			command = "\n select DISTINCT a.stt,b.pk_seq as spId, b.ma as spMa, b.ten as spTen, a.soluong    "
					+ "\n ,isnull(c.donvi, 'Chua xac dinh') as donvi, a.giamua as dongia,''  program, isnull(a.dongiagoc,a.giamua) dongiagoc,isnull(a.ckprogram,0) program_chietkhau, "
					+ "\n a.chietkhau, a.THUEVAT,  d.AVAILABLE  hienhuu, "
					+ "\n 1 as soluong1, 1 as soluong2, a.khoNVBH,isnull(a.isnhapkhau,1) isnhapkhau "
					+ "\n from donhang_sanpham a inner join sanpham b on a.sanpham_fk = b.pk_seq "
					+ "\n inner join Nhaphanphoi npp on npp.pk_seq ='" + this.nppId + "'   "
					+ "\n left join donvidoluong c on b.dvdl_fk = c.pk_seq  "
					+ "\n inner join NHAPP_KHO d on b.PK_SEQ = d.SANPHAM_FK  and d.KHO_FK = '" + this.khoId
					+ "' and d.NPP_FK = '" + this.nppId + "'  "
					+ "\n	and	d.KBH_FK =  case npp.DUNGCHUNGKENH  when 1 then 100025 else " + this.kbhId
					+ "  end 		  " + "\n where a.donhang_fk = '" + this.id + "'" + "\n order by a.stt  ";
		}

		// Don gia goc trong donhang_sanpham join khachhang_chietkhau
		////System.out.println("22.San pham list:" + command);

		ResultSet splistRs = db.get(command);
		float tonggiatri = 0f;
		List<ISanpham> splist = new ArrayList<ISanpham>();
		if (splistRs != null) {
			String[] param = new String[12];
			ISanpham sp = null;
			try {
				while (splistRs.next()) {
					param[0] = splistRs.getString("spId");
					param[1] = splistRs.getString("spma");
					param[2] = splistRs.getString("spten");
					param[3] = splistRs.getString("soluong");
					param[4] = splistRs.getString("donvi");
					param[5] = splistRs.getString("dongia");

					param[6] = "";
					param[7] = splistRs.getString("chietkhau");
					// param[8] = splistRs.getString("program");
					// param[9] = splistRs.getString("program_chietkhau");
					param[10] = splistRs.getString("dongia");
					int soluong1 = splistRs.getInt("soluong1");
					int soluong2 = splistRs.getInt("soluong2");

					// ////System.out.println("------Truoc khi lam tron: " +
					// (splistRs.getDouble("soluong") * soluong2 / soluong1) + " -- Lam tron 100
					// lan: " + Math.round( 100 * splistRs.getDouble("soluong") * soluong2 /
					// soluong1 ) );
					double thung = Math.round(100 * splistRs.getDouble("soluong") * soluong2 / soluong1) / 100.0;
					double sothung = Math.round(10 * thung) / 10.0;

					// ////System.out.println("------So luong 1: " + soluong1 + " -- Soluong 2: " +
					// soluong2 + " -- Thung: " + thung + " -- Sluong thung: " + sothung);

					sp = new Sanpham(param);
					// ////System.out.println("---Hien huu: " + splistRs.getDouble("hienhuu") + " --
					// Formrt: " + df2.format(splistRs.getDouble("hienhuu")));
					sp.setTonhientai(df2.format(splistRs.getDouble("hienhuu")));
					sp.setSoluong1(Integer.toString(soluong1));
					sp.setSoluong2(Integer.toString(soluong2));
					sp.setSoluongThung(Double.toString(sothung));
					sp.setKhoNVBH(splistRs.getString("THUEVAT"));
					sp.setProgram(splistRs.getString("program"));
					sp.setProgram_chietkhau(splistRs.getString("program_chietkhau"));
					sp.setDongiagoc(splistRs.getString("dongiagoc"));
					// sp.setSolo(splistRs.getString("SOLO"));
					// sp.setNgayHetHan(splistRs.getString("ngayhethan"));

					List<ISpDetail> spDetail = new ArrayList<ISpDetail>();
					String query = "\n SELECT top 10 case when KHO.SoLuong > 0 then 0 else 1 end loai,KHO.SOLO,ISNULL( DHCT.SOLUONG,0) AS SOLUONG ,KHO.NGAYHETHAN,KHO.NGAYNHAPKHO,KHO.ngaynhapkho ,dbo.MaxNum(0, KHO.AVAILABLE ) as luongton   "
							+ "\n FROM NHAPP_KHO_CHITIET KHO   "
							+ "\n inner join Nhaphanphoi npp on npp.pk_seq = kho.npp_fk "
							+ "\n left JOIN DONHANG_SANPHAM_chitiet DHCT   ON KHO.NPP_FK = DHCT.npp_fk "
							+ "\n						and KHO.KBH_FK = case npp.DUNGCHUNGKENH  when 1 then 100025 else DHCT.kbh_fk end   "
							+ "\n 					AND DHCT.kho_fk=KHO.kho_fk  and KHO.SANPHAM_FK=DHCT.SANPHAM_FK   "
							+ "\n 					AND KHO.SOLO=DHCT.SOLO and KHO.NGAYHETHAN = DHCT.ngayhethan AND KHO.ngaynhapkho = DHCT.ngaynhapkho and  DHCT.donhang_fk=   "
							+ this.id + "  left join \r\n" + "\n ("
							+ "\n	 select NPP_FK,KBH_FK,KHO_FK,SANPHAM_FK,SUM(booked_sql)booked from [dbo].[ufn_TonTamTinh_ChiTiet]("
							+ this.nppId + ") " + "\n	 group by NPP_FK,KBH_FK,KHO_FK,SANPHAM_FK\r\n"
							+ "\n	) ttt on ttt.kbh_fk=KHO.kbh_fk and ttt.kho_fk=KHO.KHO_FK and ttt.npp_fk=KHO.npp_fk and ttt.sanpham_fk=KHO.SANPHAM_FK "
							+

							"\n where  KHO.SanPham_FK = " + splistRs.getString("spId")
							+ "\n AND exists ( select 1 from DONHANG dh where dh.PK_SEQ = " + this.id
							+ " AND KHO.NPP_FK= dh.NPP_FK and KHO.KBH_FK = case npp.DUNGCHUNGKENH  when 1 then 100025 else DH.kbh_fk end and KHO.KHO_FK = dh.KHO_FK and KHO.ngaynhapkho <= '"
							+ ngayhoadon + "'  ) " +
							// "\n and ( KHO.soluong >0 or ISNULL( DHCT.SOLUONG,0) >0 ) " +
							"\n ORDER BY  ISNULL( DHCT.SOLUONG,0) desc,loai,NGAYHETHAN ";

					////System.out.println(" du lieu 44343: " + query);
					ResultSet rsSpDetail = db.get(query);
					while (rsSpDetail.next()) {
						ISpDetail splo = new SpDetail();

						splo.setSolo(rsSpDetail.getString("solo"));
						splo.setNgayhethan(rsSpDetail.getString("NGAYHETHAN"));
						splo.setNgaynhapkho(rsSpDetail.getString("ngaynhapkho"));
						splo.setSoluong("" + rsSpDetail.getDouble("SOLUONG"));
						splo.setSoluongton("" + (rsSpDetail.getDouble("luongton")));

						spDetail.add(splo);
					}

					rsSpDetail.close();

					sp.setSpDetailList(spDetail);

					splist.add(sp);

				}
				if (splistRs != null) {
					splistRs.close();
				}
			} catch (Exception e) {
				e.printStackTrace();
				////System.out.println("___EXCEPTION SAN PHAM: " + e.getMessage());
			}
		}
		this.splist = splist;
	}

	public void CreateSpDisplay(HttpServletRequest request) {
		DecimalFormat df2 = new DecimalFormat("#,###,###,##0.00");
		String command = "";
		String LINKSERVER_DB = request.getServletContext().getInitParameter("LINKSERVER_DB");
		if (this.quanlykho.trim().equals("0")) {
			command = "\n IF OBJECT_ID('tempdb.dbo.#kho') IS NOT NULL DROP TABLE #kho select * into #kho from "
					+ "\n ( " + "\n  	SELECT SP_E.MA SPMA, NPP_E.MA NPPMA, SUM(AVAILABLE) AVAILABLE " + "\n 	FROM "
					+ LINKSERVER_DB + ".[dbo].[ERP_KHOTT_SP_CHITIET] K   " + "\n 	INNER JOIN " + LINKSERVER_DB
					+ ".[dbo].NHAPHANPHOI NPP_E ON NPP_E.PK_SEQ = K.NPP_FK   " + "\n 	INNER JOIN " + LINKSERVER_DB
					+ ".[dbo].ERP_SANPHAM SP_E ON SP_E.PK_SEQ = K.SANPHAM_FK    " + "\n 	WHERE K.NGAYNHAPKHO <= '"
					+ this.ngaygiaodich
					+ "' AND NPP_E.MA = ( SELECT DISTINCT MA FROM NHAPHANPHOI WHERE PK_SEQ = ( select NPP_FK from DONHANG where  PK_SEQ = '"
					+ this.id + "' )) " + "\n 	GROUP BY SP_E.MA, NPP_E.MA " + "\n ) AS K " +

					" select DISTINCT b.pk_seq as spId, b.ma as spMa, b.ten as spTen, a.soluong, isnull(c.donvi, 'Chua xac dinh') as donvi, a.giamua as dongia, "
					+ " a.chietkhau, d.AVAILABLE as hienhuu, qc.soluong1, qc.soluong2 "
					+ " from donhang_sanpham a inner join sanpham b on a.sanpham_fk = b.pk_seq "
					+ " left join donvidoluong c on b.dvdl_fk = c.pk_seq inner join quycach qc on b.pk_seq = qc.sanpham_fk "
					+ "\n OUTER APPLY ( SELECT * FROM #KHO WHERE SPMA = b.MA ) AS D " + " where a.donhang_fk = '"
					+ this.id + "' "
					// + " and d.KHO_FK = '" + this.khoId + "' and d.NPP_FK = '" + this.nppId + "'
					// and d.KBH_FK = '" + this.kbhId + "' "
					+ " and qc.dvdl2_fk = '100018' ";
		} else {
			command = " select DISTINCT b.pk_seq as spId, b.ma as spMa, b.ten as spTen, a.soluong, isnull(c.donvi, 'Chua xac dinh') as donvi, a.giamua as dongia, "
					+ " a.chietkhau, d.AVAILABLE as hienhuu, qc.soluong1, qc.soluong2 "
					+ " from donhang_sanpham a inner join sanpham b on a.sanpham_fk = b.pk_seq "
					+ " left join donvidoluong c on b.dvdl_fk = c.pk_seq inner join quycach qc on b.pk_seq = qc.sanpham_fk "
					+ " inner join NHAPP_KHO d on b.PK_SEQ = d.SANPHAM_FK " + " where a.donhang_fk = '" + this.id
					+ "' and d.KHO_FK = '" + this.khoId + "' and d.NPP_FK = '" + this.nppId + "'" + " and d.KBH_FK = '"
					+ this.kbhId + "' and qc.dvdl2_fk = '100018' ";
		}

		////System.out.println("2.San pham list:" + command);

		ResultSet splistRs = db.get(command);
		float tonggiatri = 0f;
		List<ISanpham> splist = new ArrayList<ISanpham>();
		if (splistRs != null) {
			String[] param = new String[8];
			ISanpham sp = null;
			try {
				while (splistRs.next()) {
					param[0] = splistRs.getString("spId");
					param[1] = splistRs.getString("spma");
					param[2] = splistRs.getString("spten");
					param[3] = splistRs.getString("soluong");
					param[4] = splistRs.getString("donvi");
					param[5] = splistRs.getString("dongia");
					param[6] = "";

					if (this.IsSampling.equals("0")) {
						float ck = splistRs.getFloat("chietkhau");
						int slg = Integer.parseInt(param[3]);
						float tt = 0f;
						if (slg != 0) {
							tt = (ck / (Integer.parseInt(param[3]) * Float.parseFloat(param[5]))) * 100;
							tt = new Float(df2.format(tt)).floatValue(); // lam tron 2 so
						}
						this.chietkhau = Float.toString(tt);

						param[7] = this.chietkhau;
						tonggiatri += Float.parseFloat(param[5]) * Float.parseFloat(param[3]);
					} else {
						this.chietkhau = "0";
						tonggiatri = 0;
					}

					param[7] = this.chietkhau;

					int soluong1 = splistRs.getInt("soluong1");
					int soluong2 = splistRs.getInt("soluong2");

					// ////System.out.println("------Truoc khi lam tron: " +
					// (splistRs.getDouble("soluong") * soluong2 / soluong1) + " -- Lam tron 100
					// lan: " + Math.round( 100 * splistRs.getDouble("soluong") * soluong2 /
					// soluong1 ) );
					double thung = Math.round(100 * splistRs.getDouble("soluong") * soluong2 / soluong1) / 100.0;

					double sothung = Math.round(10 * thung) / 10.0;

					// ////System.out.println("------So luong 1: " + soluong1 + " -- Soluong 2: " +
					// soluong2 + " -- Thung: " + thung + " -- Sluong thung: " + sothung);

					sp = new Sanpham(param);
					sp.setTonhientai(splistRs.getString("hienhuu"));
					sp.setSoluong1(Integer.toString(soluong1));
					sp.setSoluong2(Integer.toString(soluong2));
					sp.setSoluongThung(Double.toString(sothung));

					splist.add(sp);

				}
				if (splistRs != null) {
					splistRs.close();
				}
			} catch (Exception e) {
			}
		}
		this.splist = splist;
	}

	String kbhId = "";

	private void CreateNvgnList() {
		String query = "select * from nhanviengiaonhan where NPP_FK = " + this.nppId;
		/*
		 * if (this.khId.trim().length() > 0) query +=
		 * " and pk_seq in ( select nvgn_fk from NVGN_KH  where khachhang_fk = '" +
		 * this.khId + "' ) ";
		 */
		this.nvgnRs = db.get(query);
		////System.out.println("nvgn:" + query);
	}

	public void init(HttpServletRequest request) {
		this.getNppInfo();

		String query = "";
		boolean hasId = false;
		ResultSet rs = null;
		if (this.id != null && this.id.length() > 0) {
			hasId = true;
		}

		query = "\n select tienKmDiem, ctkmDiem_fk, isnull(a.tiengiamtru,0) as tiengiamtru, isnull(a.GhiChuGiamGia,0) GhiChuGiamGia, "
				+ "\n     isnull(a.TienGiamGia,0) TienGiamGia, isnull(a.ngaytaodh,a.ngaygio) as ngaygio, isnull(hch.DIENGIAI,0) as hangkh_fk, "
				+ "\n     a.ingia,a.NVGN_FK,a.gsbh_fk,a.pk_seq as dhId, a.ngaynhap, a.trangthai, a.ngaytao, a.ngaysua, a.khachhang_fk as khId,"
				+ "\n     g.ten as khTen, g.diachi, isnull(g.chucuahieu, '') as chucuahieu, isnull(g.maFAST, '') as smartid, a.kho_fk as khoId, "
				+ "\n     a.tonggiatri, b.ten as nguoitao, c.ten as nguoisua, e.pk_seq as ddkdId, e.ten as ddkdTen, f.ten as nppTen, a.VAT, "
				+ "\n     isnull(a.chietkhau, 0) as chietkhau, isnull(a.chietkhau, 0) as ptck, a.kbh_fk, isnull(a.ghichu, '') as ghichu, "
				+ "\n     isnull(dk.dat, 0) as dktrungbay, a.IsDonHangLe, a.IsSampling, g.THANHTOAN, isnull(a.DONHANGKHAC, 0) as DONHANGKHAC, "
				+ "\n     isnull(denghitraCK , 0) as denghitraCK, donquatang, isnull(a.sotaikhoan, '') as sotaikhoan, "
				+ "\n	    isnull(g.dienthoai,'') khDienThoai, isnull(g.masothue,'') khMaSoThue "
				+ "\n from donhang a " + "\n	left join nhanvien b on a.nguoitao = b.pk_seq "
				+ "\n	left join nhanvien c on a.nguoisua = c.pk_seq "
				+ "\n inner join daidienkinhdoanh e on a.ddkd_fk = e.pk_seq "
				+ "\n	inner join nhaphanphoi f on a.npp_fk = f.pk_seq "
				+ "\n inner join khachhang g on a.khachhang_fk=g.pk_seq "
				+ "\n	left join hangcuahang hch on hch.pk_seq=g.hch_fk "
				+ "\n	left join DKTRUNGBAY_DONHANG dk on a.pk_seq = dk.donhang_fk " + "\n where a.npp_fk = '"
				+ this.nppId + "' and a.pk_seq = '" + this.id + "' ";
		////System.out.println("1.Init don hang: " + query);
		rs = db.get(query);

		if (rs != null) {
			try {
				rs.next();
				this.khDienThoai = rs.getString("khDienThoai");
				this.khMaSoThue = rs.getString("khMaSoThue");
				this.id = rs.getString("dhId");
				this.ctkmDiemId = rs.getString("ctkmDiem_fk") == null ? "" : rs.getString("ctkmDiem_fk");
				this.tienKmDiem = rs.getString("tienKmDiem") == null ? 0 : rs.getDouble("tienKmDiem");
				this.khId = rs.getString("khId");
				this.khTen = rs.getString("khTen") + " - " + rs.getString("diachi");
				this.chucuahieu = rs.getString("chucuahieu");
				this.smartId = rs.getString("smartid"); // rs.getString("smartId").substring(rs.getString("smartId").indexOf("_")+1,
														// rs.getString("smartId").length());
				this.nvgnId = rs.getString("NVGN_FK");
				this.ngaygiaodich = rs.getString("ngaynhap");
				this.daidienkd = rs.getString("ddkdTen");
				this.trangthai = rs.getString("trangthai");
				this.ngaytao = rs.getString("ngaytao");
				this.nguoitao = rs.getString("nguoitao");
				this.ngaysua = rs.getString("ngaysua");
				this.nguoisua = rs.getString("nguoisua");
				this.VAT = "0"; // OneOne, gia SP da co thue
				this.ddkdId = rs.getString("ddkdId");
				this.khoId = rs.getString("khoId");
				this.chietkhau = rs.getString("ptck");
				this.tongchietkhau = rs.getString("chietkhau");
				this.chietkhaucu = rs.getString("ptck");
				this.ghichu = rs.getString("ghichu");
				////System.out.println("---PTCK: " + rs.getString("ptck") + " -- TONG CK: " + this.tongchietkhau);
				this.tongtiensauVAT = rs.getString("tonggiatri");
				this.ingia = rs.getString("ingia");
				this.tongtientruocVAT = rs.getString("VAT");
				this.gsbhId = rs.getString("gsbh_fk");
				this.kbhId = rs.getString("kbh_fk");

				if (rs.getInt("dktrungbay") > 0)
					this.aplaitb = true;

				this.IsDonHangLe = rs.getString("IsDonHangLe");
				this.IsSampling = rs.getString("IsSampling");
				this.loaiNppId = rs.getString("THANHTOAN");
				this.donhangKhac = rs.getString("DONHANGKHAC");

				if (donhangKhac.equals(1))
					this.ttsauCKKM = 0;

				this.trangthai = rs.getString("trangthai");
				this.denghitraCKTHANG = rs.getString("denghitraCK");
				this.donquatang = rs.getString("donquatang");
				this.sotaikhoan = rs.getString("sotaikhoan");
				this.hangkh = rs.getString("hangkh_fk");
				this.tiengiamtru = rs.getString("tiengiamtru");

				String ngayhoadon_ = Utility.getNgayHienTai(); // util_kho.getngayhoadon(this.userId,db,this.ngaygiaodich,this.khId,0);
				////System.out.println("ngayhoadon: " + ngayhoadon_);
				this.setNgayhoadon(ngayhoadon_);
				rs.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		String sql = "\n select a.pk_seq, a.ten " + "\n from giamsatbanhang a "
				+ "\n inner join NHAPP_GIAMSATBH b on a.pk_seq = b.gsbh_fk " + "\n where ngaybatdau <= '"
				+ this.getDateTime() + "' and ngayketthuc >= '" + getDateTime() + "' " + "\n and npp_fk = '"
				+ this.nppId + "' and a.trangthai = '1'";

		if (this.ddkdId.length() > 0) {
			sql += "\n union all select a.pk_seq, a.ten from giamsatbanhang a "
					+ "\n where a.pk_Seq in (select gsbh_fk from  ddkd_gsbh where ddkd_fk = '" + this.ddkdId
					+ "') and a.trangthai = 1 ";
		}

		if (hasId) {
			sql += "\n union select a.pk_seq, a.ten from giamsatbanhang a " + "\n where a.pk_Seq = " + this.gsbhId;
		}

		////System.out.println("gsbhs RS: " + sql);
		this.gsbhs = db.get(sql);

		query = "\n select ten as ddkdTen, pk_seq as ddkdId from daidienkinhdoanh "
				+ "\n where trangthai = '1' and pk_seq in (select ddkd_fk from DAIDIENKINHDOANH_NPP where npp_fk = '"
				+ this.nppId + "' ) ";

		if (this.khId.trim().length() > 0) {
			query += "\n and PK_SEQ in (select ddkd_fk from TUYENBANHANG a inner join KHACHHANG_TUYENBH b on b.TBH_FK = a.PK_SEQ where b.KHACHHANG_FK = '"
					+ this.khId + "' ) ";
		}

		////System.out.println("ddkdlist RS: " + query);
		this.ddkdlist = db.get(query);

		this.createBgstId();
		this.CreateSpList(request);
		this.createKho();
		this.CreateNvgnList();
		this.checkInfo();

		this.rshangkh = db.get("select PK_SEQ, DIENGIAI from HANGCUAHANG where TRANGTHAI = 1");

		if (this.donhangKhac.equals("0")) {
			this.createChietkhau();
			this.getTrakhuyenmai();
			this.initTichLuy();
			// this.DeNghiDoanhSo();
		}

		// Init CHIET KHAU BO SUNG
		query = "select maloai, diengiai, vat from LOAICHIETKHAU where pk_seq > 0 and VAT != 5 ";
		String condition = "";

		if (this.tichluy_scheme != null) {
			for (int i = 0; i < this.tichluy_scheme.length; i++) {
				condition += "'" + this.tichluy_scheme[i].trim() + "',";
			}
			if (condition.trim().length() > 0) {
				condition = condition.substring(0, condition.length() - 1);
				query += " and maloai not in (" + condition + ") ";
			}
		}

		////System.out.println("--- INIT TICH LUY: " + query);
		this.ckbsList = db.getScrol(query);

		// INIT TICH LUY BO SUNG
		query = "select maloai, ptVAT, chietkhau from DONHANG_CHIETKHAUBOSUNG where donhang_fk = '" + this.id + "'";
		rs = db.get(query);
		if (rs != null) {
			Hashtable<String, Float> chietkhau_giatri = new Hashtable<String, Float>();
			String ckIds = "";
			try {
				while (rs.next()) {
					ckIds += rs.getString("maloai") + "--" + rs.getString("ptVAT") + "__";
					chietkhau_giatri.put(rs.getString("maloai"), rs.getFloat("chietkhau"));
				}
				rs.close();

				if (ckIds.trim().length() > 0) {
					this.chietkhau_ids = ckIds.substring(0, ckIds.length() - 2);
					this.chietkhau_giatri = chietkhau_giatri;
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		this.InitCtkmDiemRs();
		this.TinhDiemKhuyenMai();
	}

	public void initTichLuy() {
		String query = "select N'CN5' as diengiai, sum(chietkhau) as chietkhau, thueVAT, 1 as STT, 0 as tichluyQUY, 0 as cotheXOA from DONHANG_SANPHAM  "
				+ "	where donhang_fk = '" + this.id + "' and thueVAT = '5' and chietkhau > 0 group by thueVAT "
				+ "union  "
				+ "	select N'CN10' as diengiai, sum(chietkhau) as chietkhau, thueVAT, 2 as STT, 0 as tichluyQUY, 0 as cotheXOA  "
				+ "	from DONHANG_SANPHAM  " + "	where donhang_fk = '" + this.id
				+ "' and thueVAT = '10' and chietkhau > 0 group by thueVAT " + "union "
				+ "	select a.diengiai, a.thanhtoan / ( 1 + ptTHUE / 100 ) as chietkhau, a.ptTHUE as thueVAT, 3 as STT, tichluyQUY, 1 as cotheXOA  "
				+ "	from DUYETTRAKHUYENMAI_DONHANG a inner join TIEUCHITHUONGTL b on a.duyetkm_fk = b.PK_SEQ   "
				+ "	where a.donhang_fk = '" + this.id + "' and HIENTHI = '1' order by STT, tichluyQUY ";

		/*
		 * String query =
		 * "select N'CN5' as diengiai, sum(chietkhau) as chietkhau, thueVAT, 1 as STT, 0 as tichluyQUY, 0 as cotheXOA from DONHANG_SANPHAM  "
		 * + "	where donhang_fk = '" + this.id +
		 * "' and thueVAT = '5' and chietkhau > 0 group by thueVAT " + "union  " +
		 * "	select N'CN10' as diengiai, sum(chietkhau) as chietkhau, thueVAT, 2 as STT, 0 as tichluyQUY, 0 as cotheXOA  "
		 * + "	from DONHANG_SANPHAM  " + "	where donhang_fk = '" + this.id +
		 * "' and thueVAT = '10' and chietkhau > 0 group by thueVAT " + "union " +
		 * "	select a.diengiai, a.thanhtoan / ( 1 + ptTHUE / 100 ) as chietkhau, a.ptTHUE as thueVAT, 3 as STT, tichluyQUY, 1 as cotheXOA  "
		 * +
		 * "	from DUYETTRAKHUYENMAI_DONHANG a inner join TIEUCHITHUONGTL b on a.duyetkm_fk = b.PK_SEQ   "
		 * + "	where a.donhang_fk = '" + this.id + "'  order by STT, tichluyQUY ";
		 */

		////System.out.println("---INIT TICH LUY: " + query);
		ResultSet rs = db.get(query);
		if (rs != null) {
			String schemeMa = "";
			String schemeVAT = "";
			String schemeTt = "";
			String schemeCtx = "";

			try {
				NumberFormat format = new DecimalFormat("#,###,###.###");
				while (rs.next()) {
					schemeMa += rs.getString("diengiai") + "__";
					schemeVAT += rs.getString("thueVAT") + "__";
					schemeCtx += rs.getString("cotheXOA") + "__";
					schemeTt += format.format((-1) * rs.getDouble("chietkhau")) + "__";
				}
				rs.close();

				if (schemeMa.trim().length() > 0) {
					schemeMa = schemeMa.substring(0, schemeMa.length() - 2);
					this.tichluy_scheme = schemeMa.split("__");

					schemeVAT = schemeVAT.substring(0, schemeVAT.length() - 2);
					this.tichluy_vat = schemeVAT.split("__");

					schemeTt = schemeTt.substring(0, schemeTt.length() - 2);
					this.tichluy_tongtien = schemeTt.split("__");

					schemeCtx = schemeCtx.substring(0, schemeCtx.length() - 2);
					this.tichluy_cothexoa = schemeCtx.split("__");
				}
			} catch (Exception e) {
				e.printStackTrace();
				////System.out.println("__EXCEPTION: " + e.getMessage());
			}
		}
	}

	public String getBgstId() {
		return this.bgstId;
	}

	public void setBgstId(String bgstId) {
		this.bgstId = bgstId;
	}

	public String getKhoId() {
		return this.khoId;
	}

	public void setKhoId(String khoId) {
		this.khoId = khoId;
	}

	public ResultSet getKhoList() {
		return this.kholist;
	}

	public void setKhoList(ResultSet kholist) {
		this.kholist = kholist;
	}

	private String getDateTime() {
		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		Date date = new Date();
		return dateFormat.format(date);
	}

	private String getDateTimeTEST() {
		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
		Date date = new Date();
		return dateFormat.format(date);
	}

	public void DBclose() {
		try {
			if (!(this.kholist == null))
				this.kholist = null;

			if (!(this.ddkdlist == null))
				this.ddkdlist.close();
			if (!(this.tbhlist == null))
				this.tbhlist.close();
			if (this.khlist != null) {
				this.khlist.close();
			}
			if (this.gsbhs != null) {
				gsbhs.close();
			}
			splist = null;
			spThieuList = null;
			scheme_tongtien = null;
			scheme_chietkhau = null;
			scheme_sanpham = null;

			this.db.shutDown();
		} catch (Exception e) {
		}
	}

	public void setMuctindung(String muctindung) {

		this.muctindung = muctindung;
	}

	public String getMuctindung() {

		return this.muctindung;
	}

	public ResultSet getgsbhs() {
		return this.gsbhs;
	}

	public String getTongChietKhau() {
		return this.tongchietkhau;
	}

	public void setTongChietKhau(String tck) {
		this.tongchietkhau = tck;
	}

	public String getKHList() {
		String khId = "";
		String khTen = "";
		String khChietKhau = "";
		String khList = "";
		String command = "select b.pk_seq as khId, b.ten as khTen, c.chietkhau "
				+ "from khachhang_tuyenbh a inner join khachhang b on a.khachhang_fk = b.pk_seq "
				+ "inner join tuyenbanhang d on a.tbh_fk = d.pk_seq "
				+ "left join mucchietkhau c on b.chietkhau_fk = c.pk_seq " + "where b.npp_fk='" + this.nppId
				+ "' and d.ddkd_fk= '" + this.ddkdId + "' order by khId, khTen, chietkhau";

		ResultSet kh = db.get(command);
		try {
			if (kh != null) {
				while (kh.next()) {
					khId = khId + kh.getString("khId") + ",";
					khTen = khTen + kh.getString("khTen") + ",";

					if (kh.getString("chietkhau") != null && !kh.wasNull())
						khChietKhau = khChietKhau + kh.getString("chietkhau") + ",";
					else
						khChietKhau = khChietKhau + "0,";

				}

			}
			khId = khId.substring(0, khId.length() - 1);
			khTen = khTen.substring(0, khTen.length() - 1);
			khChietKhau = khChietKhau.substring(0, khChietKhau.length() - 1);

			String[] khIdList = khId.split(",");
			String[] khTenList = khTen.split(",");
			String[] khChietKhauList = khChietKhau.split(",");

			int cnt = 1;
			for (int i = 0; i < khTenList.length; i++) {
				if (i != khTenList.length - 1) {
					khList = khList + "\"" + khIdList[i] + "-->[" + khTenList[i] + "][" + khChietKhauList[i] + "]\",";
				} else {
					khList = khList + "\"" + khIdList[i] + "-->[" + khTenList[i] + "][" + khChietKhauList[i] + "]\"";
				}

			}
			if (kh != null) {
				kh.close();
			}
		} catch (Exception e) {
		}
		return khList;
	}

	public boolean isAplaikhuyenmai() {
		return this.aplaikm;
	}

	public void setAplaikhuyenmai(boolean aplaikm) {
		this.aplaikm = aplaikm;
	}

	public String createPth(String pxkId, dbutils db) {
		String msg = "";
		try {
			db.getConnection().setAutoCommit(false);

			List<ISanpham> spThuhoiList = this.getSpthuhoiList(pxkId, db);
			List<ISanpham> spkmThuhoiList = this.getSpkmthuhoiList(pxkId, db);
			if (spThuhoiList.size() > 0 || spkmThuhoiList.size() > 0) {
				// Xoa cac phieu thu hoi cu cua pxk nay co trang thai = 0
				String query = "delete from phieuthuhoi_sanpham where pth_fk in (select pk_seq from phieuthuhoi where phieuxuatkho_fk = '"
						+ pxkId + "' and trangthai = 0)";
				if (!db.update(query)) {
					db.getConnection().rollback();
					msg = "1.Lỗi tạo phiếu thu hồi: " + query;
					return msg;
				}

				query = "delete from phieuthuhoi_spkm where pth_fk in (select pk_seq from phieuthuhoi where phieuxuatkho_fk = '"
						+ pxkId + "' and trangthai = 0)";
				if (!db.update(query)) {
					db.getConnection().rollback();
					msg = "2.Lỗi tạo phiếu thu hồi: " + query;
					return msg;
				}

				query = "select DONHANG_FK as dhId from PHIEUTHUHOI_DONHANG where pth_fk in (select pk_seq from phieuthuhoi where phieuxuatkho_fk = '"
						+ pxkId + "' and trangthai = 0) and donhang_fk != '" + this.id + "'";
				ResultSet rsDh = db.get(query);
				String dhs = "";
				if (rsDh != null) {
					while (rsDh.next()) {
						if (rsDh.getString("dhId") != null)
							dhs += rsDh.getString("dhId") + ",";
					}
					rsDh.close();
				}

				query = "delete from phieuthuhoi_donhang where pth_fk in (select pk_seq from phieuthuhoi where phieuxuatkho_fk = '"
						+ pxkId + "' and trangthai = 0)";
				if (!db.update(query)) {
					db.getConnection().rollback();
					msg = "3.Lỗi tạo phiếu thu hồi: " + query;
					return msg;
				}

				query = "delete from phieuthuhoi where phieuxuatkho_fk = '" + pxkId + "' and trangthai = 0";
				if (!db.update(query)) {
					db.getConnection().rollback();
					msg = "4.Lỗi tạo phiếu thu hồi: " + query;
					return msg;
				}

				query = "insert into phieuthuhoi(phieuxuatkho_fk, trangthai, ngaytao, ngaysua, nguoitao, nguoisua, donhang_fk, npp_fk) ";
				query = query + "values('" + pxkId + "','0','" + this.ngayxuatkho + "','" + this.getDateTime() + "','"
						+ this.userId + "','" + this.userId + "', '" + this.id + "', '" + this.nppId + "')";
				if (!db.update(query)) {
					db.getConnection().rollback();
					msg = "5.Loi khi them moi bang 'phieuthuhoi', " + query;
					return msg;
				}

				query = "select IDENT_CURRENT('phieuthuhoi') as pthId";
				String pthId = "";
				ResultSet rsPth = db.get(query);
				rsPth.next();
				pthId = rsPth.getString("pthId");
				rsPth.close();

				// luu vao bang phieuthuhoi_sp
				for (int i = 0; i < spThuhoiList.size(); i++) {
					Sanpham sp = (Sanpham) spThuhoiList.get(i);

					if (Double.parseDouble(sp.getSoluong()) != 0) {
						// DOn vi tinh luu kho_fk, don gia se luu kbh_fk
						query = "Insert into phieuthuhoi_sanpham(pth_fk, sanpham_fk, soluong, kho_fk, kbh_fk) values("
								+ pthId + ", '" + sp.getId() + "','" + sp.getSoluong() + "', '" + sp.getDonvitinh()
								+ "', '" + sp.getDongia() + "')";

						if (!db.update(query)) {
							db.getConnection().rollback();
							msg = "6.Loi khi them moi bang 'phieuthuhoi_sanpham', " + query;
							return msg;
						}
					}
				}

				// luu vao bang phieuthuhoi_spkm (chi tao khi in phieu thu hoi cuoi cung)
				for (int i = 0; i < spkmThuhoiList.size(); i++) {
					Sanpham sp = (Sanpham) spkmThuhoiList.get(i);

					if (Double.parseDouble(sp.getSoluong()) != 0) {
						// DOn vi tinh luu kho_fk, don gia se luu kbh_fk
						query = "Insert into phieuthuhoi_spkm(pth_fk, sanpham_fk, soluong, kho_fk, kbh_fk) values('"
								+ pthId + "', '" + sp.getId() + "','" + sp.getSoluong() + "', '" + sp.getDonvitinh()
								+ "', '" + sp.getDongia() + "')";
						if (!db.update(query)) {
							db.getConnection().rollback();
							msg = "7.Loi khi them moi bang 'phieuthuhoi_spkm', " + query;
							return msg;
						}
					}
				}

				query = "insert PHIEUTHUHOI_DONHANG(pth_fk, pxk_fk, donhang_fk) values('" + pthId + "', '" + this.pxkId
						+ "', '" + this.id + "')";
				if (!db.update(query)) {
					db.getConnection().rollback();
					msg = "Loi khi them moi bang 'PHIEUTHUHOI_DONHANG', " + query;
					return msg;
				}

				if (dhs.length() > 0) {
					String[] donhangs = dhs.split(",");
					for (int i = 0; i < donhangs.length; i++) {
						query = "insert PHIEUTHUHOI_DONHANG(pth_fk, pxk_fk, donhang_fk) values('" + pthId + "', '"
								+ this.pxkId + "', '" + donhangs[i].trim() + "')";
						if (!db.update(query)) {
							db.getConnection().rollback();
							msg = "8.Loi khi them moi bang 'PHIEUTHUHOI_DONHANG', " + query;
							return msg;
						}
					}
				}
			}
		} catch (Exception e1) {
			try {
				db.getConnection().rollback();
			} catch (Exception e) {
			}

			msg = "Loi khi tao moi phieu thu hoi :, " + e1.toString();
			e1.printStackTrace();
			return msg;
		}
		return msg;
	}

	private List<ISanpham> getSpthuhoiList(String pxkId, dbutils db) {
		List<ISanpham> spOldList = new ArrayList<ISanpham>();
		String query = "select sanpham_fk, soluong, kbh_fk, kho_fk from phieuxuatkho_sanpham where pxk_fk = '" + pxkId
				+ "'";

		// ////System.out.println("Cau lenh lay du lieu la: " + query + "\n");
		ResultSet spThuhoi = db.get(query);
		if (spThuhoi != null) {
			try {
				while (spThuhoi.next()) {
					// ISanpham sp = new Sanpham(spThuhoi.getString("spId"),
					// spThuhoi.getString("spMa"), spThuhoi.getString("spTen"),
					// spThuhoi.getString("soluong"), spThuhoi.getString("khoId"),
					// spThuhoi.getString("kbhId"), "", "");
					ISanpham sp = new Sanpham(spThuhoi.getString("sanpham_fk"), "", "", spThuhoi.getString("soluong"),
							spThuhoi.getString("kho_fk"), spThuhoi.getString("kbh_fk"), "", "");
					spOldList.add(sp);
				}
				spThuhoi.close();
			} catch (Exception e) {
			}
		}

		// ////System.out.println("Size san pham thu hoi buoc 1 la: " + spOldList.size() +
		// "\n");

		// Nhung phieu thu hoi cua phieu xuat kho nay
		query = "select sanpham_fk as spId, soluong, kho_fk as khoId, kbh_fk as kbhId from phieuthuhoi_sanpham where pth_fk in (select pk_seq from phieuthuhoi where phieuxuatkho_fk = '"
				+ pxkId + "' and trangthai = '1')";
		query += " union all ";
		query += "select c.sanpham_fk as spId, soluong, b.kho_fk as khoId, b.kbh_fk as kbhId from phieuxuatkho_donhang a inner join donhang b on a.donhang_fk = b.pk_seq inner join donhang_sanpham c on b.pk_seq = c.donhang_fk where b.trangthai != '2' and a.pxk_fk = '"
				+ pxkId + "'";

		query = "select spId, sum(soluong) as soluong, khoId, kbhId from (" + query
				+ ") kh group by spId, khoId, kbhId";

		// ////System.out.println("Query lay san pham aaaaa la: " + query + "\n");

		List<ISanpham> spNewList = new ArrayList<ISanpham>();
		ResultSet spThuhoi2 = db.get(query);
		if (spThuhoi2 != null) {
			try {
				while (spThuhoi2.next()) {
					ISanpham sp = new Sanpham(spThuhoi2.getString("spId"), "", "", spThuhoi2.getString("soluong"),
							spThuhoi2.getString("khoId"), spThuhoi2.getString("kbhId"), "", "");

					spNewList.add(sp);
				}
				spThuhoi2.close();
			} catch (Exception e) {
			}
		}

		// List<ISanpham> spNewList = new ArrayList<ISanpham>();
		// sanpham hien tai
		/*
		 * query =
		 * "select b.kho_fk as khoId, b.kbh_fk as kbhId, c.sanpham_fk as spId, sum(c.soluong) as soluong from phieuxuatkho_donhang a inner join donhang b on a.donhang_fk = b.pk_seq inner join donhang_sanpham c on b.pk_seq = c.donhang_fk "
		 * ; query += "where b.trangthai != '2' and a.pxk_fk = '" + pxkId +
		 * "' group by b.kho_fk, b.kbh_fk, c.sanpham_fk";
		 * ////System.out.println("Cau lenh lay du lieu ben duoi: " + query + "\n");
		 * ResultSet spThuhoi3 = db.get(query); if (spThuhoi3 != null) { try { while
		 * (spThuhoi3.next()) { ISanpham sp = new Sanpham(spThuhoi3.getString("spId"),
		 * "", "", spThuhoi3.getString("soluong"), spThuhoi3.getString("khoId"),
		 * spThuhoi3.getString("kbhId"), "", "");
		 * 
		 * spNewList.add(sp); } spThuhoi3.close(); } catch (Exception e) {} }
		 */
		// cong don san pham
		// thu hoi sp trakhyenmai (ve kho_fk, kbh_fk)
		List<ISanpham> spkmList = new ArrayList<ISanpham>();

		for (int i = 0; i < spOldList.size(); i++) {
			Sanpham spA = (Sanpham) spOldList.get(i);
			for (int j = 0; j < spNewList.size(); j++) {
				Sanpham spB = (Sanpham) spNewList.get(j);
				if ((spB.getId().trim().equals(spA.getId().trim()))
						&& (spB.getDonvitinh().trim().equals(spA.getDonvitinh().trim()))
						&& (spB.getDongia().trim().equals(spA.getDongia().trim()))) {
					double slg = Math.abs(Double.parseDouble(spA.getSoluong()) - Double.parseDouble(spB.getSoluong()));

					spOldList.get(i).setSoluong(Double.toString(Math.round(slg)));

					spNewList.remove(j);
					j = 0;
				}
			}
			if (Double.parseDouble(spOldList.get(i).getSoluong()) > 0) {
				ISanpham sp = new Sanpham(spOldList.get(i).getId(), "", "", spOldList.get(i).getSoluong(),
						spOldList.get(i).getDonvitinh(), spOldList.get(i).getDongia(), "", "");
				spkmList.add(sp);

				// ////System.out.println("So luong luc dau thu hoi co so luong la: " +
				// sp.getSoluong() + "\n");
			}
		}

		// ////System.out.println("Size san pham thu hoi buoc 2 la: " + spkmList.size() +
		// "\n");
		return spkmList;
	}

	private List<ISanpham> getSpkmthuhoiList(String pxkId, dbutils db) {
		// spkm trong phieuxuatkho cu
		List<ISanpham> spkmOldList = new ArrayList<ISanpham>();
		String query = "select kho_fk as khoId, kbh_fk as kbhId, sanpham_fk as spId, sum(soluong) as soluong from phieuxuatkho_spkm where pxk_fk = '"
				+ pxkId + "' group by kho_fk, kbh_fk, sanpham_fk";

		// ////System.out.println("Query lan 1: " + query + "\n");

		ResultSet spOld = db.get(query);
		if (spOld != null) {
			try {
				while (spOld.next()) {
					ISanpham sp = new Sanpham(spOld.getString("spId"), "", "", spOld.getString("soluong"),
							spOld.getString("khoId"), spOld.getString("kbhId"), "", "");
					spkmOldList.add(sp);
				}
				spOld.close();
			} catch (Exception e) {
			}
		}

		// tinh lai so lg cac spkm phai thu hoi (nhung donhang daxuatkho ma huy thi ko
		// can lay spkm, khi do spkmOldList se thu hoi dung soluong daxuatkho bandau cua
		// nhung don hang da huy nay)
		// List<ISanpham> spkmNewList = new ArrayList<ISanpham>();
		// nhung phieu thu hoi da chot cua phieu xuat kho nay
		query = "select sanpham_fk as spId, soluong, kho_fk as khoId, kbh_fk as kbhId from phieuthuhoi_spkm where pth_fk in (select pk_seq from phieuthuhoi where phieuxuatkho_fk = '"
				+ pxkId + "' and trangthai = '1')";
		query += " union all ";
		query += "select d.pk_seq as spId, soluong, a.kho_fk as khoId, e.kbh_fk as kbhId from donhang_ctkm_trakm a inner join ctkhuyenmai b on a.ctkmid = b.pk_seq inner join sanpham d on a.spMa = d.ma inner join donhang e on a.donhangId = e.pk_seq where a.spMa is not null and e.trangthai != '2' and a.donhangId in (select donhang_fk from phieuxuatkho_donhang where pxk_fk = '"
				+ pxkId + "') ";

		query = "select spId, sum(soluong) as soluong, khoId, kbhId from (" + query
				+ ") kh group by spId, khoId, kbhId";

		// ////System.out.println("Query lay spkm: " + query + "\n");

		List<ISanpham> spkmNewList = new ArrayList<ISanpham>();
		ResultSet spThuhoi2 = db.get(query);
		if (spThuhoi2 != null) {
			try {
				while (spThuhoi2.next()) {
					ISanpham sp = new Sanpham(spThuhoi2.getString("spId"), "", "", spThuhoi2.getString("soluong"),
							spThuhoi2.getString("khoId"), spThuhoi2.getString("kbhId"), "", "");

					spkmNewList.add(sp);
				}
				spThuhoi2.close();
			} catch (Exception e) {
			}
		}

		// ////System.out.println("Query thu hoi khuyen mai ben duoi: " + query + "\n");
		/*
		 * ResultSet spKhuyenmaiRS = db.get(query); if (spKhuyenmaiRS != null) { try {
		 * while (spKhuyenmaiRS.next()) { String[] param = new String[8]; ISanpham sp =
		 * null;
		 * 
		 * param[0] = spKhuyenmaiRS.getString("spId"); param[1] = ""; param[2] = "";
		 * param[3] = spKhuyenmaiRS.getString("soluong");
		 * 
		 * //luu kho - don vi tinh param[4] = ""; if (spKhuyenmaiRS.getString("khoId")
		 * != null) param[4] = spKhuyenmaiRS.getString("khoId");
		 * 
		 * //luu kenh ban hang - don gia param[5] = ""; if
		 * (spKhuyenmaiRS.getString("kbhId") != null) param[5] =
		 * spKhuyenmaiRS.getString("kbhId");
		 * 
		 * param[6] = ""; param[7] = "";
		 * 
		 * sp = new Sanpham(param); spkmNewList.add(sp); } spKhuyenmaiRS.close(); }
		 * catch (Exception e) {} }
		 */
		// thu hoi sp trakhyenmai (ve kho_fk, kbh_fk)
		List<ISanpham> spkmList = new ArrayList<ISanpham>();

		for (int i = 0; i < spkmOldList.size(); i++) {
			Sanpham spA = (Sanpham) spkmOldList.get(i);
			for (int j = 0; j < spkmNewList.size(); j++) {
				Sanpham spB = (Sanpham) spkmNewList.get(j);
				if ((spB.getId().trim().equals(spA.getId().trim()))
						&& (spB.getDonvitinh().trim().equals(spA.getDonvitinh().trim()))
						&& (spB.getDongia().trim().equals(spA.getDongia().trim()))) {

					double slg = Math.abs(Double.parseDouble(spA.getSoluong()) - Double.parseDouble(spB.getSoluong()));

					spkmOldList.get(i).setSoluong(Double.toString(Math.round(slg)));
					spkmNewList.remove(j);
					j = 0; // quet lai
				}
			}
			if (Double.parseDouble(spkmOldList.get(i).getSoluong()) > 0) {
				ISanpham sp = new Sanpham(spkmOldList.get(i).getId(), "", "", spkmOldList.get(i).getSoluong(),
						spkmOldList.get(i).getDonvitinh(), spkmOldList.get(i).getDongia(), "", "");
				spkmList.add(sp);
			}
		}
		return spkmList;
	}

	public String getPxkId() {
		return this.pxkId;
	}

	public void setPxkId(String pxkId) {
		this.pxkId = pxkId;
	}

	public String DeleteDonHangDxk() {
		try {
			db.getConnection().setAutoCommit(true);

			String query = "update donhang set trangthai='2' where pk_seq = '" + this.id + "'";
			if (!db.update(query)) {
				db.getConnection().rollback();
				return "1.Khong the xoa don hang: " + this.id + ", " + query;
			}

			// cap nhat phan bo km
			/*
			 * query =
			 * "select ctkmId, sum(tonggiatri) as tonggiatri from donhang_ctkm_trakm where donhangid = '"
			 * + this.id + "' group by ctkmId"; ResultSet rs = db.get(query); if (rs !=
			 * null) { try { while (rs.next()) { String ctkmId = rs.getString("ctkmId");
			 * String tonggiatri = rs.getString("tonggiatri");
			 * 
			 * String st ="update Phanbokhuyenmai set DASUDUNG = DASUDUNG - '" + tonggiatri
			 * + "' where ctkm_fk='" + ctkmId + "' and npp_fk='" + this.nppId + "'";
			 * //db.update("update CTKhuyenmai set DASUDUNG = DASUDUNG - '" + tonggiatri +
			 * "' where ctkm_fk = '" + ctkmId + "'"); if (!db.update(st)){
			 * this.db.getConnection().rollback(); return "Khong The Cap Nhat :"+ st; }
			 * 
			 * } rs.close(); }
			 * 
			 * catch (Exception e) { this.db.getConnection().rollback(); return
			 * e.toString(); }
			 * 
			 * }
			 */

			this.createPxkId();
			if (this.pxkId.length() > 0) {
				String msg = this.createPth(this.pxkId, db);
				if (msg.length() > 0) {
					db.getConnection().rollback();
					return "4.Khong the tao phieu thu hoi cua don hang: " + this.id + ", " + msg;
				}
			}

			// Xoa trung bay
			// truong hop cap nhat, phai xoa so xuat cu
			query = "select dktrungbay_fk, khachhang_fk, dat from DKTRUNGBAY_DONHANG where donhang_fk = '" + this.id
					+ "'";
			ResultSet rsDelete = db.get(query);
			if (rsDelete != null) {
				while (rsDelete.next()) {
					String dk_fk = rsDelete.getString("dktrungbay_fk");
					String kh_fk = rsDelete.getString("khachhang_fk");
					String dat = rsDelete.getString("dat");

					query = "delete DKTRUNGBAY_DONHANG where dktrungbay_fk = '" + dk_fk + "' and khachhang_fk = '"
							+ kh_fk + "' and donhang_fk = '" + this.id + "'";
					if (!db.update(query)) {

						db.getConnection().rollback();
						return "5.Không thể cập nhậtDKTRUNGBAY_DONHANG " + this.id + ", " + query;
					}

					query = "update DKTRUNGBAY_KHACHHANG set dat = dat - " + dat + " where dktrungbay_fk = '" + dk_fk
							+ "' and khachhang_fk = '" + kh_fk + "'";
					if (!db.update(query)) {
						db.getConnection().rollback();
						return "2.Không thể cập DKTRUNGBAY_KHACHHANG " + this.id + ", " + query;
					}

				}
				rsDelete.close();
			}

			db.getConnection().commit();
			db.getConnection().setAutoCommit(true);

		}

		catch (Exception e) {
			try {
				db.getConnection().rollback();
			} catch (Exception e1) {
			}

			return "5.Khong the xoa don hang: " + this.id + ", " + e.getMessage();
		}

		return "";

	}

	public boolean isCokhuyenmai() {
		return this.cokm;
	}

	public void setCokhuyenmai(boolean cokm) {
		this.cokm = cokm;
	}

	public boolean isChuaApkhuyenmai() {
		return this.chuaApkm;
	}

	public void setIsChuaApkhuyenmai(boolean chuaApkm) {
		this.chuaApkm = chuaApkm;
	}

	public boolean isDamuahang() {
		return this.dacoDh;
	}

	public void setIsDamuahang(boolean damuahang) {
		this.dacoDh = damuahang;
	}

	public boolean isDxkChuaChot() {
		return this.daxuatkhoChuachot;
	}

	public void setIsDxkChuaChot(boolean cokm) {
		this.daxuatkhoChuachot = cokm;
	}

	public void setNgayKs(String ngayks) {
		this.ngayks = ngayks;
	}

	public String getNgayKs() {
		return this.ngayks;
	}

	public void setCotrungbay(String cotrungbay) {
		this.coTrungBay = cotrungbay;
	}

	public String getCotrungbay() {
		return this.coTrungBay;
	}

	public int ApTrungBay(String dhId, String khId, String nppId, String ngaydh) {
		/*******************
		 * 0 - Khong co trung bay, 1 - Co trung bay, -1 - Loi khi cap nhat trung bay
		 *********************/
		int flag = 0;

		// truong hop cap nhat, phai xoa so xuat cu
		String query = "select count(*) as sodong from CTTRUNGBAY_DonHangDauTien where donhang_fk = '" + dhId + "'";
		ResultSet rsDelete = db.get(query);
		if (rsDelete != null) {
			try {
				db.getConnection().setAutoCommit(false);
				if (rsDelete.next()) {
					if (rsDelete.getInt("sodong") > 0) {
						query = "delete CTTRUNGBAY_DonHangDauTien where donhang_fk = '" + dhId + "' ";
						if (!db.update(query)) {
							this.msg = "1.Không thể cập nhật CTTRUNGBAY_DonHangDauTien " + query;
							db.getConnection().rollback();
							return -1;
						}
					}
				}
				rsDelete.close();

				db.getConnection().commit();
				db.getConnection().setAutoCommit(true);
			} catch (Exception e) {
				try {
					db.getConnection().rollback();
				} catch (Exception e1) {
				}

				this.msg = "115. Loi: " + e.getMessage();
				return -1;
			}
		}

		query = "select c.PK_SEQ as CTTRUNGBAY_FK,  c.scheme, c.NGAYTDS as TuNgay, c.NGAYKTTDS as DenNgay  "
				+ "from CTTRUNGBAY c inner join PHANBOTRUNGBAY d on c.PK_SEQ = d.CTTB_FK " + "where d.NPP_FK = '"
				+ this.nppId + "'  " + "and c.NGAYTDS <= '" + ngaydh + "' and c.NGAYKTTDS >= '" + ngaydh + "'  ";

		////System.out.println("___Check CTTB: " + query);

		ResultSet rs = db.get(query);
		String cttb_fk = "";

		int ngansach = 0;
		int soXuat = 0;

		if (rs != null) {
			try {
				db.getConnection().setAutoCommit(false);

				while (rs.next()) {
					cttb_fk = rs.getString("CTTRUNGBAY_FK");

					soXuat = getSoXuatTheoScheme(dhId, cttb_fk);

					////System.out.println("__-____Soxuat TB la: " + soXuat);

					if (soXuat > 0) {
						if (soXuat > ngansach) {
							soXuat = ngansach;
						}

						this.coTrungBay += rs.getString("scheme") + " ( đạt " + soXuat + " xuất), ";
						flag = 1;

						query = "insert CTTRUNGBAY_DonHangDauTien(CTTB_FK, DONHANG_FK, khachhang_fk, NPP_FK) "
								+ "values('" + cttb_fk + "', '" + dhId + "', '" + this.khId + "', '" + this.nppId
								+ "')";

						////System.out.println("___Insert trung bay: " + query);
						if (!db.update(query)) {
							this.msg = "2.Không thể cập nhật trưng bày";
							rs.close();
							db.getConnection().rollback();
							return -1;
						}
					}
				}
				rs.close();

				db.getConnection().commit();
				db.getConnection().setAutoCommit(true);
			} catch (Exception e) {
				////System.out.println("__Exception: " + e.getMessage());
				this.msg = "__Exception: " + e.getMessage();

				try {
					db.getConnection().rollback();
				} catch (SQLException e1) {
				}

				return -1;
			}
		}

		return flag;
	}

	private int getSoXuatTheoScheme(String dhId, String ctkmId) {
		int soxuat = 0;

		String query = "select isnull(MIN(Soxuat), '0') as SoXuatThoa, COUNT(nsptbId) as sonhom " + "from " + "( "
				+ "select nsptbId, case when pheptoan = 1 then 'AND' else 'OR' end as pheptoan,  "
				+ "case  when (LOAI = 2 and tongluongPhaiMua > 0) then tongluong / tongluongPhaiMua  "
				+ "when (LOAI = 2 and tongtienPhaiMua > 0) then tongtien / tongtienPhaiMua  "
				+ "else soxuatAnd end as Soxuat " + "from" + "( "
				+ "select dieukientrungbay.*, trungbaytheodk.KHACHHANG_FK, SUM(trungbaytheodk.tongluong) as tongluong, SUM(trungbaytheodk.tongtien) as tongtien, "
				+ "COUNT(case when trungbaytheodk.batbuoc > 0 then 1 else null end ) sospphaimua, "
				+ "MIN (trungbaytheodk.tongluong / trungbaytheodk.batbuoc) as soxuatAnd " + "from " + "( "
				+ "select b.PK_SEQ as nsptbId, b.LOAI, a.pheptoan, sum( distinct ISNULL(TONGLUONG, '0')) as tongluongPhaiMua,  "
				+ "SUM( distinct ISNULL(tongtien, 0) ) as tongtienPhaiMua,  "
				+ "count(case when isnull(c.soluong, '0') > 0 then 1 else null end) as sospbatbuoc  "
				+ "from CTTB_NHOMSPTRUNGBAY a inner join NHOMSPTRUNGBAY b on a.NHOMSPTRUNGBAY_FK = b.PK_SEQ "
				+ "inner join NHOMSPTRUNGBAY_SANPHAM c on a.NHOMSPTRUNGBAY_FK = c.NHOMSPTRUNGBAY_FK "
				+ "where CTTRUNGBAY_FK = '" + ctkmId + "'  " + "group by b.PK_SEQ, b.LOAI, a.pheptoan " + ")  "
				+ "dieukientrungbay inner join " + "( " + "select muatrongnhom.*, batbuocmua.batbuoc " + "from ( "
				+ "select a.KHACHHANG_FK, c.NHOMSPTRUNGBAY_FK as nspId, b.SANPHAM_FK, SUM(b.SOLUONG * b.GIAMUA) as tongtien, SUM(b.soluong) as tongluong, '1' as type  "
				+ "from DONHANG a inner join DONHANG_SANPHAM b on a.PK_SEQ = b.DONHANG_FK "
				+ "inner join NHOMSPTRUNGBAY_SANPHAM c on b.SANPHAM_FK = c.SANPHAM_FK " + "where a.PK_SEQ = '" + dhId
				+ "' and  "
				+ "c.NHOMSPTRUNGBAY_FK in ( select NHOMSPTRUNGBAY_FK from CTTB_NHOMSPTRUNGBAY where CTTRUNGBAY_FK = '"
				+ ctkmId + "' )  " + "group by KHACHHANG_FK, c.NHOMSPTRUNGBAY_FK, b.SANPHAM_FK " + ") "
				+ "muatrongnhom left join  " + "( "
				+ "select NHOMSPTRUNGBAY_FK as nspId, SANPHAM_FK, case when isnull(soluong, '0') <= 0 then -1 else soluong end as batbuoc  "
				+ "from NHOMSPTRUNGBAY_SANPHAM  "
				+ "where NHOMSPTRUNGBAY_FK in ( select NHOMSPTRUNGBAY_FK from CTTB_NHOMSPTRUNGBAY where CTTRUNGBAY_FK = '"
				+ ctkmId + "' ) " + ")  "
				+ "batbuocmua on muatrongnhom.SANPHAM_FK = batbuocmua.SANPHAM_FK and muatrongnhom.nspId = batbuocmua.nspId "
				+ "where muatrongnhom.tongluong > batbuocmua.batbuoc " + ") "
				+ "trungbaytheodk on dieukientrungbay.nsptbId = trungbaytheodk.nspId  "
				+ "group by  dieukientrungbay.nsptbId, dieukientrungbay.LOAI, dieukientrungbay.tongluongPhaiMua, dieukientrungbay.tongtienPhaiMua,  "
				+ "dieukientrungbay.sospbatbuoc, dieukientrungbay.pheptoan, trungbaytheodk.KHACHHANG_FK  "
				+ "having COUNT(case when trungbaytheodk.batbuoc > 0 then 1 else null end ) >= dieukientrungbay.sospbatbuoc "
				+ ") chuongtrinhtrungbay " + ") ngansachkhuyenmai";

		////System.out.println("___Lay so xuat CTTB: " + query);

		ResultSet rs = db.get(query);
		int soDK = 0;
		if (rs != null) {
			try {
				if (rs.next()) {
					soxuat = rs.getInt("SoXuatThoa");
					soDK = rs.getInt("sonhom");
				}
				rs.close();
			} catch (Exception e) {
				////System.out.println("115.Error: " + e.getMessage());
			}
		}

		// Check So Dieu Kien AND
		int sophaithoa = 0;
		query = "select SUM(case pheptoan when 1 then 1 else 0 end) as sodieukien from CTTB_NHOMSPTRUNGBAY where CTTRUNGBAY_FK = '"
				+ ctkmId + "' ";
		ResultSet rsCheck = db.get(query);
		if (rsCheck != null) {
			try {
				while (rsCheck.next()) {
					sophaithoa = rsCheck.getInt("sodieukien");
				}
				rsCheck.close();
			} catch (Exception e) {
				////System.out.println("115.Errror: Loi: " + e.getMessage());
			}
		}

		////System.out.println("_____________So dieu kien Ly thuyet: " + soDK + "  --- So bat buoc: " + sophaithoa);
		if (soDK < sophaithoa) {
			soxuat = 0;
		}

		return soxuat;
	}

	public boolean isAplaitrungbay() {
		return this.aplaitb;
	}

	public void setAplaitrugnbay(boolean aplaitb) {
		this.aplaitb = aplaitb;
	}

	public void setIsDonHangLe(String IsDonHangLe) {
		this.IsDonHangLe = IsDonHangLe;
	}

	public String getIsDonHangLe() {
		return this.IsDonHangLe;
	}

	public void setIsSampling(String IsSampling) {
		this.IsSampling = IsSampling;
	}

	public String getIsSampling() {
		return this.IsSampling;
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

	boolean thoaThang = false;
	double duno_dauky = 0;

	NumberFormat format = new DecimalFormat("#,###,####");

	private String getngayCUOITHANG(String ngaygiaodich) {
		String[] arr = ngaygiaodich.split("-");
		String ngaycuoithang = "";

		switch (Integer.parseInt(arr[1])) {
		case 1:
		case 3:
		case 5:
		case 7:
		case 8:
		case 10:
		case 12:
			ngaycuoithang = "31";
			break;
		case 4:
		case 6:
		case 9:
		case 11:
			ngaycuoithang = "30";
			break;
		case 2:
			if (Integer.parseInt(arr[0]) % 100 != 0 && Integer.parseInt(arr[0]) % 4 == 0) {
				ngaycuoithang = "29";
			} else {
				ngaycuoithang = "28";
			}
			break;
		default:
			ngaycuoithang = "28";
		}

		return ngaycuoithang;

	}

	public void setEnterKH(String enterKH) {
		this.enterKH = enterKH;
	}

	public String getEnterKH() {

		return this.enterKH;
	}

	private int Songaytrongthang(int month, int year) {
		int ngay = 0;
		switch (month) {
		case 1:
		case 3:
		case 5:
		case 7:
		case 8:
		case 10:
		case 12: {
			ngay = 31;
			break;
		}
		case 4:
		case 6:
		case 9:
		case 11: {
			ngay = 30;
			break;
		}
		case 2: {
			if (year % 4 == 0)
				ngay = 29;
			else
				ngay = 28;
			break;
		}
		}

		return ngay;
	}

	public String[] getTichLuy_VAT() {

		return this.tichluy_vat;
	}

	public void setTichLuy_TVAT(String[] tichluy_vat) {

		this.tichluy_vat = tichluy_vat;
	}

	public ResultSet getCkbosungList() {

		return this.ckbsList;
	}

	public void setCkbosungList(ResultSet ckbsList) {

		this.ckbsList = ckbsList;
	}

	public String getCkbosungIds() {

		return this.chietkhau_ids;
	}

	public void setCkbosungIds(String ckbsIds) {

		this.chietkhau_ids = ckbsIds;
	}

	public Hashtable<String, Float> getCkbosung_CK() {

		return this.chietkhau_giatri;
	}

	public void setCkbosung_CK(Hashtable<String, Float> ckbs_ck) {

		this.chietkhau_giatri = ckbs_ck;
	}

	public String getCkbosung_VAT() {

		return this.chietkhau_vat;
	}

	public void setCkbosung_TVAT(String ckbs_vat) {

		this.chietkhau_vat = ckbs_vat;
	}

	public void setTieuchi(String tieuchi) {

		this.tieuchiID = tieuchi;
	}

	public String getTieuchi() {

		return this.tieuchiID;
	}

	public ResultSet getDoanhsodenghi() {

		return this.dsdnRs;
	}

	public void setDoanhsodenghi(ResultSet dsDenghi) {

		this.dsdnRs = dsDenghi;
	}

	public void setDsTongNhomXanh(String dstNhomXanh) {

		this.dstXanh = dstNhomXanh;
	}

	public String getDsTongNhomXanh() {

		if (this.dstXanh.trim().length() <= 0)
			return "0";
		return this.dstXanh;
	}

	public void setDsTongNhomHHBG(String dstHHBG) {

		this.dstHHBG = dstHHBG;
	}

	public String getDsTongNhomHHBG() {

		if (this.dstHHBG.trim().length() <= 0)
			return "0";
		return this.dstHHBG;
	}

	public void setDsTongNhomKHAC(String dstKHAC) {

		this.dstKHAC = dstKHAC;
	}

	public String getDsTongNhomKHAC() {

		if (this.dstKHAC.trim().length() <= 0)
			return "0";
		return this.dstKHAC;
	}

	public void setDsTongNhomXanh_DeNghi(String dstDENGHI) {

		this.dstXanh_Denghi = dstDENGHI;
	}

	public String getDsTongNhomXanh_DeNghi() {

		if (this.dstXanh_Denghi.trim().length() <= 0)
			return "0";
		return this.dstXanh_Denghi;
	}

	public void setDsTongNhomHHBG_DeNghi(String dstHHBG_denghi) {

		this.dstHHBG_Denghi = dstHHBG_denghi;
	}

	public String getDsTongNhomHHBG_DeNghi() {

		if (this.dstHHBG_Denghi.trim().length() <= 0)
			return "0";
		return this.dstHHBG_Denghi;
	}

	public String[] getTichLuy_CoTheXoa() {

		return this.tichluy_cothexoa;
	}

	public void setTichLuy_CoTheXoa(String[] tichluy_xoa) {

		this.tichluy_cothexoa = tichluy_xoa;
	}

	public String getLoaiNppId() {

		return this.loaiNppId;
	}

	public void setLoaiNppId(String nppId) {

		this.loaiNppId = nppId;
	}

	public void setDonhangKhac(String donhangKhac) {
		this.donhangKhac = donhangKhac;

	}

	public String getDonhangKhac() {
		return this.donhangKhac;
	}

	public String getChucuahieu() {
		return this.chucuahieu;
	}

	public void setChucuahieu(String chucuahieu) {
		this.chucuahieu = chucuahieu;
	}

	public String getnvgnId() {
		return this.nvgnId;
	}

	public void setnvgnId(String nvgnId) {
		this.nvgnId = nvgnId;
	}

	public ResultSet getnvgnRs() {
		return this.nvgnRs;
	}

	public void setnvgnRs(ResultSet nvgnRs) {

		this.nvgnRs = nvgnRs;
	}

	public static String DONHANGSVL_CLICK = "0";
	public static String DONHANGUPDATESVL_CLICK = "1";
	public static String PDA_CLICK = "2";

	private static final int RUT_GON = 0;
	private static final int TU_CHOT_HOA_DON = 1;

	String kqDuyet = "0"; // 1 là true 0 là false

	public String getKqDuyet() {
		return kqDuyet;
	}

	public void setKqDuyet(String kqDuyet) {
		this.kqDuyet = kqDuyet;
	}

	private boolean checkKH(String dhId) {
		String sql = "select isnull(daduyet, 0) daduyet from khachhang kh where exists ( select 1 from donhang dh where dh.pk_seq = '"
				+ dhId + "' and dh.khachhang_fk = kh.pk_seq )";
		ResultSet rs = this.db.get(sql);
		try {
			rs.next();
			if (!rs.getString("daduyet").equals("1")) {
				return false;
			}
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}
	
	public String DuyetDonHang(String dhId, String vitriBAM, String userId, HttpServletRequest request) {
		////System.out.println("-- DUYETDONHANG");
		int quytrinh = 0;// 0 rut gon , 1 là tự chốt hóa đơn
		this.kqDuyet = "0";
		String pxkId = "";
		String loaiNPP = "";
		String quanlykho = "";
		this.userId = userId;
		try {
			Utility util = new Utility();
			geso.dms.center.util.Utility util_kho = new geso.dms.center.util.Utility();
			String ngayhoadon_ = Utility.getNgayHienTai(); // util_kho.getngayhoadon(this.userId,db,ngaynhap,khachhang_fk,0);
			/// Booked kho

			/*
			 * if(!this.dexuatlo(dhId)) { this.kqDuyet = "0"; return this.msg; }
			 */
			this.db.getConnection().setAutoCommit(false);

			if (!checkKH(dhId)) {
				this.db.getConnection().rollback();
				return "Khách hàng chưa được duyệt, không thể chốt đơn. Vui lòng kiểm tra lại.";
			}

			// Kiểm tra trong trường hợp 2 user chốt cùng một lúc
			String query = "select synced,trangthai, dh.donquatang, "
					+ " ( select count(*) from HOADON_DDH where ddh_fk = dh.pk_seq  "
					+ " and hoadon_fk in ( select pk_seq from HOADON where trangthai in ( 1, 2, 4 )  "
					+ " and npp_fk = ( select npp_fk from DONHANG where pk_seq = '" + dhId
					+ "' ) and loaihoadon = '0' ) ) as soDongHD, " + " isnull(donhangkhac, 0) as donhangkhac "
					+ " ,(select count(NVGN_FK) from nvgn_kh where nvgn_kh.khachhang_fk = dh.khachhang_fk) coNVGN  "
					+ " from DONHANG dh where pk_seq = '" + dhId + "' ";

			////System.out.println("query _______" + query);
			ResultSet checkDH = this.db.get(query);
			String trangthaiDH = "";
			int synced = 1;
			int isDHK = 0;
			int soHD = 0;
			int coNVGN = 0;
			String donquatang = "";
			{
				while (checkDH.next()) {
					trangthaiDH = checkDH.getString("trangthai");
					soHD = checkDH.getInt("soDongHD");
					isDHK = checkDH.getInt("donhangkhac");
					donquatang = checkDH.getString("donquatang");
					coNVGN = checkDH.getInt("coNVGN");
					synced = checkDH.getInt("synced");

				}
				checkDH.close();
			}

			if (trangthaiDH.equals("1")) {
				this.db.getConnection().rollback();

				return "Đơn hàng này đã chốt rồi.";
			}
			if (synced == 1) {
				this.db.getConnection().rollback();
				return "Đơn hàng này đã  đồng bộ rồi ";
			}

			if (soHD >= 2) {
				this.db.getConnection().rollback();
				return "Đơn hàng này đã có hóa đơn rồi. Vui lòng kiểm tra lại";
			}

			if (coNVGN <= 0) {
				this.db.getConnection().rollback();
				return " vui long chon nvgn";
			}

			int flag_inchung = 0;
			int coinrieng = 0;

			query = "select b.loaiNPP, b.quanlykho,a.NPP_FK, a.ngaynhap, b.priandsecond as tuchotOTC, a.khachhang_fk, "
					+ "	ISNULL( ( select count(donhang_fk) from PHIEUXUATKHO_DONHANG where donhang_fk = a.pk_seq and PXK_FK in ( select pk_seq from PHIEUXUATKHO where NPP_FK = b.pk_seq and trangthai != '2' ) ), 0) as exitPXK, "
					+ "   (  select count(donhangID) from DONHANG_CTKM_TRAKM where donhangID = '" + dhId
					+ "' and spMA is not null and ctkmId in ( select pk_seq from CTKHUYENMAI where kho_fk = '100000'  and isnull(inchung,0) = 0) ) as coKHOHB,	"
					+ "   (  select count(donhangID) from DONHANG_CTKM_TRAKM where donhangID = '" + dhId
					+ "' and spMA is not null and ctkmId in ( select pk_seq from CTKHUYENMAI where kho_fk = '100000'  and isnull(inchung,0) = 1) ) as coinrieng	"
					+ "from DONHANG a inner join NHAPHANPHOI b on a.NPP_FK = b.pk_seq where a.pk_seq = '" + dhId + "' ";
			////System.out.println("cau query 111 : " + query);
			boolean exitPXK = false;
			String nppId = "";
			String ngaynhap = "";
			String khachhang_fk = "";

			ResultSet rs = this.db.get(query);

			if (rs.next()) {
				loaiNPP = rs.getString("loaiNPP");
				quanlykho = rs.getString("quanlykho");
				this.quanlykho = quanlykho;
				nppId = rs.getString("NPP_FK");
				ngaynhap = rs.getString("ngaynhap");

				khachhang_fk = rs.getString("khachhang_fk");
				flag_inchung = rs.getInt("coKHOHB");
				coinrieng = rs.getInt("coinrieng");
				if (rs.getInt("exitPXK") > 0)
					exitPXK = true;

				rs.close();
			}

			query = "Update DONHANG set trangthai = '1' , nguoichot = " + userId + ",tdchot= getdate() where pk_seq = '"+ dhId + "' and TrangThai = 0 ";
			if (this.db.updateReturnInt(query) != 1) {
				this.db.getConnection().rollback();
				return "Đơn hàng đã chốt rồi ";
			}

			query = " update dhsp set khong_tich_luy = isnull(sd.khong_tich_luy,0)\r\n" + "from\r\n"
					+ "DONHANG_SANPHAM dhsp\r\n" + "cross apply\r\n" + "(\r\n"
					+ "	select top 1 1 as khong_tich_luy  from donhang_ctkm_dkkm dk\r\n"
					+ "	where exists   (select 1 from CTKHUYENMAI where pk_seq =  dk.ctkm_fk and khong_tich_luy = 1)\r\n"
					+ "		and dk.donhang_fk = dhsp.DONHANG_FK and dk.sanpham_fk = dhsp.SANPHAM_FK\r\n" + ")sd \r\n"
					+ "where dhsp.DONHANG_FK = " + dhId;
			if (this.db.updateReturnInt(query) < 0) {
				this.db.getConnection().rollback();
				return "Lỗi ghi nhận đơn hàng có sp tham gia km không được tích lũy ";
			}

			if (this.quanlykho.equals("1"))
			{
				if (quytrinh == RUT_GON) {
					
					
					String bookedMsg = TRU_KHO_RUT_GON_QUYTRINHBOOKED_DONHANG(this.db, vitriBAM, dhId, ngayhoadon_);
					if (bookedMsg.trim().length() > 0) {
						this.db.getConnection().rollback();
						return bookedMsg;
					}
					
					
				} else if (quytrinh == TU_CHOT_HOA_DON) {
					if (quanlykho.equals("1")) {
						String bookedMsg = BookedKhoDonHang(dhId, vitriBAM, ngayhoadon_, util_kho);
						if (bookedMsg.trim().length() > 0) {
							this.db.getConnection().rollback();
							return bookedMsg;
						}
					}
				} else {
					this.db.getConnection().rollback();
					return "Lỗi quy trình";
				}
				
				if (donquatang.equals("1") || isDHK == 1) 
				{
					String msg = this.TaoHoaDonTaiChinhKM_DHK(this.db, dhId, nppId, ngaynhap, userId, khachhang_fk, donquatang);
					if (msg.trim().length() > 0) {
						this.db.getConnection().rollback();
						return "55.Lỗi khi tạo hóa đơn tài chính KM cua DON HANG KHAC : " + msg;
					}
				}
				else 
				{
					String msg = this.TaoHoaDonTaiChinh(this.db, dhId, nppId, loaiNPP, ngaynhap, userId, khachhang_fk);
					if (msg.trim().length() > 0) {
						this.db.getConnection().rollback();
						return "5.Lỗi khi tạo hóa đơn tài chính : " + msg;
					}
				}
				
				if (quytrinh == RUT_GON) {
					query = " update hoadon set trangthai = 2 where pk_seq in ( select  hoadon_fk from HOADON_DDH where ddh_fk  = "
							+ dhId + ")";
					if (this.db.updateReturnInt(query) != 1) {
						this.db.getConnection().rollback();
						return "Lỗi chốt hóa đơn tự động";
					}
					query = "\n select a.pk_seq,a.npp_fk,a.ngayxuathd,a.nguoitao,convert(char(10),getdate(),126) as ngaytao "
							+ "\n from HOADON a inner join HOADON_DDH b on a.PK_SEQ=b.HOADON_FK " + "\n where b.ddh_fk="
							+ dhId + "  and a.trangthai=2 ";
					ResultSet rsCheck = db.get(query);
					rsCheck.next();
					String hdId = rsCheck.getString("pk_seq");

					if (quanlykho.equals("1")) {
						String kqtpxk = Hoadontaichinh.create_pxk(db, hdId, userId, ngayhoadon_);
						if (kqtpxk.trim().length() > 0) {
							rsCheck.close();
							this.db.getConnection().rollback();
							return kqtpxk;

						}
						rsCheck.close();
						query = " update PHIEUXUATKHO set trangthai = 1 where pk_seq in ( select  pxk_fk from PHIEUXUATKHO_DONHANG where DONHANG_FK  = "
								+ dhId + ")";
						if (db.updateReturnInt(query) != 1) {
							this.db.getConnection().rollback();
							return "Lỗi chốt xuất kho tự động";
						}
					}
				}
				
				query = " select count(*) kq from ( select SUM(SOLUONG) SL,SANPHAM_FK from DONHANG_SANPHAM a where DONHANG_FK="
						+ dhId + "  group by SANPHAM_FK ) AA \n"
						+ " full outer join ( select SUM(SOLUONG) SL,SANPHAM_FK from DONHANG_SANPHAM_chitiet a where DONHANG_FK="
						+ dhId + "  group by SANPHAM_FK) BB on AA.SANPHAM_FK=BB.sanpham_fk \n"
						+ " where ISNULL(AA.SL,0)<>ISNULL(BB.SL,0)  ";

				if (geso.dms.center.util.Utility.Check_Count_Query(this.db, query) > 0) {
					this.db.getConnection().rollback();
					return "số lượng tổng và chi tiết không khớp nhau ";
				}

				query = "\n select count(*) kq " + "\n from  " + "\n ( 	"
						+ "\n 	select SUM(SOLUONG) SL,sp.PK_SEQ SANPHAM_FK,a.kho_fk from DonHang_CTKM_TRAKM a "
						+ "\n		inner join SANPHAM sp on sp.MA = a.SPMA " + "\n 	where DONHANGID=" + dhId
						+ "  group by sp.PK_SEQ,a.kho_fk ) AA " + "\n full outer join "
						+ "\n (	select SUM(SOLUONG) SL,SANPHAM_FK,kho_fk " + "\n 	from DonHang_CTKM_TRAKM_chitiet a "
						+ "\n	 	where DONHANG_FK=" + dhId + "  group by SANPHAM_FK,kho_fk "
						+ "\n ) BB on AA.SANPHAM_FK=BB.sanpham_fk and AA.kho_fk=BB.kho_fk "
						+ "\n where ISNULL(AA.SL,0)<>ISNULL(BB.SL,0)  ";

				if (geso.dms.center.util.Utility.Check_Count_Query(this.db, query) > 0) {
					this.db.getConnection().rollback();
					return "số lượng tổng và chi tiết KM không khớp nhau " + query;
				}
				
			}

			String msgGT = Utility.Update_GiaTri_DonHang(dhId, this.db);
			if (msgGT.trim().length() > 0) {
				this.db.getConnection().rollback();
				return msgGT;
			}

			if (quanlykho.equals("1")) {
				msg = util.Check_Kho_Tong_VS_KhoCT(nppId, db);
				if (msg.length() > 0) {
					this.db.getConnection().rollback();
					return msg;
				}
			}

			msg = util.Check_Huy_NghiepVu_KhoaSo("donhang", dhId, "ngaynhap", db);
			if (msg.length() > 0) {
				this.db.getConnection().rollback();
				return msg;
			}

			 
			this.kqDuyet = "1";
			this.db.getConnection().commit();
			return " Duyệt đơn hàng thành công ";
		 
			//return "";
			
		} catch (Exception e) {
			e.printStackTrace();
			try {
				this.db.getConnection().rollback();
			} catch (Exception e1) {
				e1.printStackTrace();
			}
			return "Lỗi khi duyệt đơn hàng: " + e.getMessage();
		}
		
		  finally { try { this.db.getConnection().setAutoCommit(true); } catch (SQLException e) { e.printStackTrace(); } }
	}
	 
	private String TRU_KHO_RUT_GON_QUYTRINHBOOKED_DONHANG(dbutils db2,
			String vitriBAM, String dhId, String ngayhoadon_) {
		 try{

				geso.dms.center.util.Utility util_kho = new geso.dms.center.util.Utility();


				String query = "\n SELECT DH.NPP_FK,   DH.KHO_FK,DHCT.sanpham_fk AS SPID, "
						+ " case npp.DUNGCHUNGKENH  when 1 then 100025 else dh.KBH_FK end as kbh_fk, " +
						" DHCT.SOLO,DHCT.NGAYHETHAN,DHCT.NGAYNHAPKHO " + "\n  , DHCT.SOLUONG  SOLUONG "
						+ "\n  FROM DONHANG_SANPHAM_CHITIET  DHCT "
						+ "\n   "
						+ "\n  INNER JOIN DONHANG DH ON DH.PK_SEQ=DHCT.DONHANG_fk "
						+ "\n inner join nhaphanphoi npp on npp.pk_seq = dh.npp_fk  "  
						+ "\n  WHERE DHCT.donhang_fk= "
						+ dhId;

				ResultSet rssp = db2.get(query);
				while (rssp.next()) {
					String _spid = rssp.getString("SPID");
					String _nppid = rssp.getString("NPP_FK");
					String _khoid = rssp.getString("KHO_FK");
					String _kbhid = rssp.getString("KBH_FK");
					double _SOLUONG = rssp.getDouble("SOLUONG");
					String _solo = rssp.getString("solo");
					String _ngaynhapkho = rssp.getString("ngaynhapkho");
					String _ngayhethan = rssp.getString("ngayhethan");

					 String msg1= util_kho.Update_NPP_Kho_Sp (this.ngaygiaodich,
							 " 9448. donhang.java CHỐT  đơn hàng___" +id , db2, _khoid, _spid, _nppid,
							  _kbhid , (-1)* _SOLUONG ,(-1)* _SOLUONG, 0,
							   0); // giảm booked, tăng avail lại
							 
							  if (msg1.length() > 0){ db2.getConnection().rollback(); return "Error :" +
							   msg1; }
							  
							  
					    msg1= util_kho.Update_NPP_Kho_Sp_Chitiet(this.ngaygiaodich,
					 " 9448. donhang.java CHỐT  đơn hàng___" +id , db2, _khoid, _spid, _nppid,
					  _kbhid, _solo, _ngayhethan, _ngaynhapkho, (-1)* _SOLUONG ,(-1)* _SOLUONG, 0,
					  0, 0); // giảm booked, tăng avail lại
					 
					  if (msg1.length() > 0){ db2.getConnection().rollback(); return "Error :" +
					   msg1; }
				 
				}
				rssp.close();
				
				
				query = "\n SELECT DH.NPP_FK,   DHCT.KHO_FK,DHCT.sanpham_fk AS SPID, "
						+ " case npp.DUNGCHUNGKENH  when 1 then 100025 else dh.KBH_FK end as kbh_fk, " +
						// "DH.KBH_FK ," +
						" DHCT.SOLO,DHCT.NGAYHETHAN,DHCT.NGAYNHAPKHO " + "\n  , DHCT.SOLUONG  SOLUONG "
						+ "\n  FROM DONHANG_CTKM_TRAKM_CHITIET  DHCT "
						+ "\n  INNER JOIN CTKHUYENMAI KM ON KM.PK_SEQ=DHCT.ctkm_fk "
						+ "\n  INNER JOIN DONHANG DH ON DH.PK_SEQ=DHCT.DONHANG_fk "
						+ "\n inner join nhaphanphoi npp on npp.pk_seq = dh.npp_fk  " + "\n  WHERE DHCT.donhang_fk= "
						+ dhId;

			   rssp = db2.get(query);
				while (rssp.next()) {
					String _spid = rssp.getString("SPID");
					String _nppid = rssp.getString("NPP_FK");
					String _khoid = rssp.getString("KHO_FK");
					String _kbhid = rssp.getString("KBH_FK");
					double _SOLUONG = rssp.getDouble("SOLUONG");
					String _solo = rssp.getString("solo");
					String _ngaynhapkho = rssp.getString("ngaynhapkho");
					String _ngayhethan = rssp.getString("ngayhethan");

					  String msg1= util_kho.Update_NPP_Kho_Sp(this.ngaygiaodich,
								 "9448. donhang.java Cập nhật đơn hàng___" +id , db2, _khoid, _spid, _nppid,
								  _kbhid, (-1)* _SOLUONG ,(-1)* _SOLUONG, 
								  0, 0); // giảm booked, tăng avail lại
								 
								  if (msg1.length() > 0){ db2.getConnection().rollback(); return "Error :" +
								   msg1; }
					    msg1= util_kho.Update_NPP_Kho_Sp_Chitiet(this.ngaygiaodich,
					 "9448. donhang.java Cập nhật đơn hàng___" +id , db2, _khoid, _spid, _nppid,
					  _kbhid, _solo, _ngayhethan, _ngaynhapkho, (-1)* _SOLUONG ,(-1)* _SOLUONG, 0,
					  0, 0); // giảm booked, tăng avail lại
					 
					  if (msg1.length() > 0){ db2.getConnection().rollback(); return "Error :" +
					   msg1; }
				 
				}
				rssp.close();
				
				
				
				
		 }catch(Exception er){
			 return "Lỗi trong quá trình chốt phiếu,vui lòng thử lại hoặc báo Admin để trợ giúp";
		 }
		// TODO Auto-generated method stub
		return "";
	}

	private String DongBo_ERP(String id, String khDmsId, HttpServletRequest request, dbutils db) throws SQLException 
	{
		//dbutils db = new dbutils(); // 
		String status="0";
		try {
			////System.out.println("-- DongBo_ERP");
			String urlParam="", urldhsp = "", urldhspkm="";
			String LINKSERVER_DB = request.getServletContext().getInitParameter("LINKSERVER_DB");
			// LAY DANH SACH SAN PHAM TU DON HANG
			String sql = "SELECT DHSP.DONHANG_FK DONHANGID, SP.MA AS SPMA, SP.PK_SEQ SPID, DHSP.SOLUONG, DHSP.DONGIAGOC,\r\n" + 
					"DHSP.GIAMUA, ISNULL(QC.QD, 0) AS QUYDOI, DVD.DONVI, \r\n" + 
					"DHSP.CHIETKHAU, DHSP.TIENVAT, DHSP.THUEVAT, ISNULL(DHSP.TIENKHUYENMAI,0) AS TIENKM\r\n" + 
					"FROM DONHANG_SANPHAM DHSP \r\n" + 
					"INNER JOIN SANPHAM SP ON SP.PK_SEQ = DHSP.SANPHAM_FK \r\n" + 
					"INNER JOIN DONVIDOLUONG DVD ON DVD.PK_SEQ = SP.DVDL_FK\r\n" + 
					"OUTER APPLY \r\n" + 
					"(  \r\n" + 
					
					/*"	SELECT AVG( SOLUONG1/SOLUONG2)QD FROM QUYCACH QC  \r\n" + 
					"	INNER JOIN DONVIDOLUONG DV_DMS ON QC.DVDL2_FK = DV_DMS.PK_SEQ  \r\n" + 
					"	WHERE QC.DVDL1_FK = SP.DVDL_FK AND QC.SANPHAM_FK = SP.PK_SEQ  \r\n" +*/
					
					"	SELECT SP_E.MA AS SPMA,  case when dvD.DONVI = dv_e.donvi then 1 else  isnull(qc.qd,0) end QD , dv_e.donvi donviERP\r\n" + 
					"	FROM  " + LINKSERVER_DB + ".[dbo].ERP_SANPHAM SP_E \r\n" + 
					"	inner join " + LINKSERVER_DB + ".[dbo].Donvidoluong dv_e ON dv_e.PK_SEQ = SP_E.dvdl_fk  \r\n" + 
					"	outer apply \r\n" + 
					"	(  \r\n" + 
					"		select avg( soluong1/soluong2)qd from QUYCACH qc  \r\n" + 
					"		inner join DONVIDOLUONG dv_dms on qc.DVDL2_FK = dv_dms.PK_SEQ  \r\n" + 
					"		where dv_dms.DONVI = dv_e.donvi and qc.DVDL1_FK = SP.DVDL_FK and qc.SANPHAM_FK = SP.PK_SEQ  \r\n" + 
					"	)qc \r\n" + 
					"	WHERE SP_E.MA = SP.MA " +
					
					") QC \r\n" + 
					"WHERE DHSP.DONHANG_FK = '"+ id +"'";
			ResultSet rsSp = db.get(sql);
			while(rsSp.next())
			{
				urldhsp += 
						" { "+
						" 'donhangid':'"+ rsSp.getString("donhangid") +"', 'ma':'"+ rsSp.getString("spma") +"', 'id':'"+ rsSp.getString("spid") +"', 'soluong':'"+ rsSp.getDouble("soluong") +"', "+
						" 'dongiagoc':'"+ rsSp.getDouble("dongiagoc") +"', 'giamua':'"+ rsSp.getDouble("giamua") +"', 'chietkhau':'"+ rsSp.getDouble("chietkhau") +"', 'tienvat':'"+ rsSp.getDouble("TIENVAT") +"',"
					  + " 'thuevat':'"+ rsSp.getDouble("THUEVAT") +"', 'tienkm':'"+ rsSp.getDouble("tienkm") +"', 'quydoi':'"+ rsSp.getDouble("quydoi") +"', 'donvi':'"+ rsSp.getString("donvi") +"' "+
						" },";
			}
			rsSp.close();
			if (urldhsp.length() > 0) {
				urldhsp = urldhsp.substring(0, urldhsp.lastIndexOf(","));
			}
			
			// LAY DANH SACH SAN PHAM KHUYEN MAI TU DON HANG
			sql = 
				"SELECT DHKM.DONHANGID AS DONHANGID, CTKMDMS.SCHEME AS CTKMID, DHKM.SOXUAT, DHKM.TONGGIATRI, SP.MA AS SPMA, DHKM.SOLUONG, NULL AS CHIETKHAU, DHKM.SOXUAT AS SOXUATTRA, isnull(npp.xuattaikho, '') AS KHO_FK, \r\n" + 
				"ISNULL(QC.QD, 0) AS QUYDOI, DVD.DONVI\r\n" + 
				"FROM DONHANG_CTKM_TRAKM DHKM\r\n" +
				"INNER JOIN DONHANG DH ON DH.PK_SEQ = DHKM.DONHANGID "+
				"INNER JOIN NHAPHANPHOI NPP ON NPP.PK_SEQ = DH.NPP_FK "+
				"INNER JOIN CTKHUYENMAI CTKMDMS ON DHKM.CTKMID = CTKMDMS.PK_SEQ\r\n" + 
				"INNER JOIN SANPHAM SP ON SP.PK_SEQ = DHKM.SANPHAM_FK\r\n" + 
				"INNER JOIN DONVIDOLUONG DVD ON DVD.PK_SEQ = SP.DVDL_FK\r\n" + 
				"OUTER APPLY \r\n" + 
				"(  \r\n" + 
					/*
					 * "	SELECT AVG( SOLUONG1/SOLUONG2)QD FROM QUYCACH QC  \r\n" +
					 * "	INNER JOIN DONVIDOLUONG DV_DMS ON QC.DVDL2_FK = DV_DMS.PK_SEQ  \r\n" +
					 * "	WHERE QC.DVDL1_FK = SP.DVDL_FK AND QC.SANPHAM_FK = SP.PK_SEQ  \r\n" +
					 */ 
				  
				"	SELECT SP1.MA AS SPMA, case when dvD.DONVI = dv_e.donvi then 1 else isnull(qc.qd,0) end QD, dv_e.donvi donviERP \r\n" + 
				"	FROM " + LINKSERVER_DB + ".[dbo].ERP_SANPHAM SP1 \r\n" + 
				"	inner join " + LINKSERVER_DB + ".dbo.Donvidoluong dv_e ON dv_e.PK_SEQ = SP1.dvdl_fk  \r\n" + 
				"	outer apply \r\n" + 
				"	(  \r\n" + 
				"		select avg( soluong1/soluong2)qd from QUYCACH qc  \r\n" + 
				"		inner join DONVIDOLUONG dv_dms on qc.DVDL2_FK = dv_dms.PK_SEQ  \r\n" + 
				"		where dv_dms.DONVI = dv_e.donvi and qc.DVDL1_FK = SP.DVDL_FK and qc.SANPHAM_FK = SP.PK_SEQ  \r\n" + 
				"	) qc \r\n" + 
				"	WHERE SP.MA = SP1.MA \r\n" + 
				" ) QC \r\n" + 
				"WHERE DHKM.SPMA IS NOT NULL AND DHKM.DONHANGID = '"+ id +"'\r\n" + 
				"UNION ALL\r\n" + 
				"SELECT  DHKM.DONHANGID AS DONHANGID, CTKMDMS.SCHEME CTKMID, DHKM.SOXUAT,DHKM.TONGGIATRI,NULL SPMA,'0' SOLUONG, '0' AS CHIETKHAU, DHKM.SOXUAT AS SOXUATTRA, '' AS KHO_FK, 0 QUYDOI, '' AS DONVI\r\n" + 
				"FROM DONHANG_CTKM_TRAKM DHKM\r\n" + 
				"INNER JOIN CTKHUYENMAI CTKMDMS ON DHKM.CTKMID = CTKMDMS.PK_SEQ\r\n" + 
				"WHERE DHKM.SPMA IS NULL AND DHKM.DONHANGID = '"+ id +"'";
			ResultSet rsSpkm = db.get(sql);
			while(rsSpkm.next())
			{
				urldhspkm += 
						" { "+
						" 'donhangid':'"+ rsSpkm.getString("donhangid") +"', 'scheme':'"+ rsSpkm.getString("ctkmid") +"', 'ma':'"+ rsSpkm.getString("spma") +"', 'soluong':'"+ rsSpkm.getDouble("soluong") +"', "+
						" 'chietkhau':'"+ rsSpkm.getDouble("chietkhau") +"', 'soxuat':'"+ rsSpkm.getDouble("SOXUAT") +"', 'khoid':'"+ rsSpkm.getString("kho_fk") +"', "
					  + " 'tienkm':'"+ rsSpkm.getDouble("TONGGIATRI") +"', 'quydoi':'"+ rsSpkm.getDouble("quydoi") +"', 'donvi':'"+ rsSpkm.getString("donvi") +"' "+
						" },";
			}
			rsSpkm.close();
			if (urldhspkm.length() > 0) {
				urldhspkm = urldhspkm.substring(0, urldhspkm.lastIndexOf(","));
			}
			
			sql = " select kh.maFAST  as khid, \r\n" + 
					"dh.npp_fk as nppId, NGAYNHAP as ngaydh, dh.ngaytao, dh.ngaysua, dh.kbh_fk as kbhId, isnull(npp.xuattaikho, '') as khoId,\r\n" + 
					"isnull(kh.DiaChiGiaoHang, '') diachigh, isnull(npp.MA, '') as nppma, isnull(kbh.TEN, '') kbhten,\r\n" + 
					"isnull(dh.GHICHU, '') as ghichu, dh.VAT AS TONGTIENBVAT, dh.tonggiatri, "+
					"CASE WHEN DH.VAT != 0 THEN (( DH.TONGGIATRI / DH.VAT ) - 1) * 100 ELSE 0 END AS VAT, "
					+ " ( CASE WHEN DH.VAT != 0 THEN (( DH.TONGGIATRI / DH.VAT ) - 1) * 100 ELSE 0 END ) AS PTVAT \r\n" + 
					"from donhang dh\r\n" + 
					"inner join khachhang kh on kh.pk_Seq = dh.KHACHHANG_FK\r\n" + 
					"inner join nhaphanphoi npp on npp.PK_SEQ = dh.NPP_FK\r\n" + 
					"left join kenhbanhang kbh on kbh.pk_seq = dh.KBH_FK\r\n" + 
					"where dh.pk_seq = '"+ id +"' ";
			ResultSet rsDh = db.get(sql);
			rsDh.next();
			String khId = rsDh.getString("khid");
			String nppId = rsDh.getString("nppId");
			String ngaydh = rsDh.getString("ngaydh");
			String kbhId = rsDh.getString("kbhId");
			String khoId = rsDh.getString("khoId");
			//String mafast = rsDh.getString("mafast");
			
			String ngaytao = rsDh.getString("ngaytao");
			String ngaysua = rsDh.getString("ngaysua");
			String diachigh = rsDh.getString("diachigh");
			String nppma = rsDh.getString("nppma");
			String kbhten = rsDh.getString("kbhten");
			String ghichu = rsDh.getString("ghichu");
			String VAT = rsDh.getString("VAT");
			String PTVAT = rsDh.getString("PTVAT");
			String tongtienbvat = rsDh.getString("TONGTIENBVAT");
			String tonggiatri = rsDh.getString("tonggiatri");
			rsDh.close();
			
			String dh = " { \"id\":\"" + id + "\", \"khId\":\"" + khId + "\", \"nppId\":\"" + nppId
					+ "\", \"ngaydh\":\"" + ngaydh + "\", \"kbhId\":\"" + kbhId + "\", \"khoId\":\""
					+ khoId + "\" , \"loai\":\"0\", 'diachiGH' : '"+ diachigh +"', 'nppMa':'"+ nppma +"', 'kbhTen':'"+ kbhten +"', 'ghichu':'"+ ghichu +"',"
							+ " 'vat':'"+ VAT +"', 'ptvat':'"+ PTVAT +"', 'tonggiatri':'"+ tonggiatri +"', 'tongtienbvat':'"+ tongtienbvat +"', 'ngaytao':'"+ ngaytao +"', 'ngaysua':'"+ ngaysua +"' }";
			
			urlParam = "dhsp=[" + urldhsp + "]&dhspkm=["+ urldhspkm +"]&donhang=" + dh;
			////System.out.println("urlParam : " + urlParam);
			//String url = request.getServletContext().getInitParameter("path_api") + "DuyetDonHang";
			String url = "http://localhost:56491/API.asmx/DuyetDonHang";
			String data = "";
			String msgAPI = "";
			try {
				data = geso.dms.center.util.Utility.sendPost(url, urlParam);

				JsonObject odata = (JsonObject) new JsonParser().parse(data);
				status = odata.get("status").getAsString();
				msgAPI = odata.get("msg").getAsString();
				
				////System.out.println("message : "+msg);
				/*
				 * danh_sach_ctkm_Str = odata.get("data").getAsString(); dung_khuyen_mai =
				 * odata.get("dung_khuyen_mai").getAsString(); chon_san_pham =
				 * odata.get("chon_san_pham").getAsString(); tra_or =
				 * odata.get("tra_or").getAsString();
				 */
			} catch (Exception e1) {
				e1.printStackTrace();
				status = "0";
				msgAPI = " Không thể kết nối tới server.";
			}

			if (status.equals("0")) {
				this.msg = msgAPI;
			}
			
			else if (status.equals("1")) 
			{
				String sql_log = "INSERT INTO DONHANG_API_LOG(ID, USERID, _DATE, URL_1, URL_2, URL_3) "
						+ " SELECT '"+ id +"' AS ID, '"+ this.userId +"' AS USERID, GETDATE() AS _DATE, N'"+ urlParam.replaceAll("'", "''") +"' AS URL_1, N'"+ urldhsp.replaceAll("'", "''") +"' AS URL_2, N'"+ urldhspkm.replaceAll("'", "''") +"' AS URL_3 ";
				////System.out.println("LOG : "+ sql_log);
				db.update(sql_log);
			}
			
			////System.out.println("status : " + status + " - msgAPI : " + msgAPI);

			// geso.dms.center.util.Utility.commit_and_shutdown(db);
			//db.shutDown();
			
			return status;
		} catch (Exception e) {
			// geso.dms.center.util.Utility.rollback_and_shutdown(db);
			e.printStackTrace();
			////System.out.println("Không thể đồng bộ sang erp. Exception: " + e.getMessage());
			return status;
		}
		//finally { try { db.shutDown(); } catch (Exception e) { e.printStackTrace(); } }
	}

	public static String dongboIMEX(String dhId) {
		dbutils_syn dbSync = new dbutils_syn();
		dbutils dbDms = new dbutils();
		try {
			dbDms.getConnection().setAutoCommit(false);
			dbSync.getConnection().setAutoCommit(false);

			// log bên mình
			String query = "insert TBL_SO_DMS_LOG(MACHINHANH	,SODMS	,MANVBH	,MAKHSAP,	MASANPHAM,	SOLUONG	,DonGia	,CKTHEOPROGRAM,	THANHTIENSAUCK	,THUEVAT,	TIENTHUE,	TIENSAUTHUE,	program,	SCHEME)"
					+ " select MACHINHANH	,SODMS	,MANVBH	,MAKHSAP,	MASANPHAM,	SOLUONG	,DonGia	,ckprogram,	THANHTIENSAUCK	,THUEVAT,	TIENTHUE,	TIENSAUTHUE,	program,	SCHEME from  imexpharm.[dbo].[ufn_SyncDonHang]("
					+ dhId + ") ";
			if (dbDms.updateReturnInt(query) <= 0) {
				dbDms.getConnection().rollback();
				dbDms.getConnection().setAutoCommit(true);
				dbSync.getConnection().rollback();
				dbSync.getConnection().setAutoCommit(true);
				dbSync.shutDown();
				dbDms.shutDown();
				return "Lỗi đồng bộ 1 ; " + query;
			}
			// log bên trung gian
			query = "insert TBL_SO_DMS_LOG(MACHINHANH	,SODMS	,MANVBH	,MAKHSAP,	MASANPHAM,	SOLUONG	,DonGia	,CKTHEOPROGRAM,	THANHTIENSAUCK	,THUEVAT,	TIENTHUE,	TIENSAUTHUE,	program,	SCHEME)"
					+ " select MACHINHANH	,SODMS	,MANVBH	,MAKHSAP,	MASANPHAM,	SOLUONG	,DonGia	,ckprogram,	THANHTIENSAUCK	,THUEVAT,	TIENTHUE,	TIENSAUTHUE,	program,	SCHEME from  imexpharm.[dbo].[ufn_SyncDonHang]("
					+ dhId + ") ";
			if (dbSync.updateReturnInt(query) <= 0) {
				dbDms.getConnection().rollback();
				dbDms.getConnection().setAutoCommit(true);
				dbSync.getConnection().rollback();
				dbSync.getConnection().setAutoCommit(true);
				dbSync.shutDown();
				dbDms.shutDown();
				return "Lỗi đồng bộ 2 ; " + query;
			}
			// thật bên trung gian
			query = "insert TBL_SO_DMS(MACHINHANH	,SODMS	,MANVBH	,MAKHSAP,	MASANPHAM,	SOLUONG	,DonGia	,CKTHEOPROGRAM,	THANHTIENSAUCK	,THUEVAT,	TIENTHUE,	TIENSAUTHUE,	program,	SCHEME)"
					+ " select MACHINHANH	,SODMS	,MANVBH	,MAKHSAP,	MASANPHAM,	SOLUONG	,DonGia	,ckprogram,	THANHTIENSAUCK	,THUEVAT,	TIENTHUE,	TIENSAUTHUE,	program,	SCHEME from  imexpharm.[dbo].[ufn_SyncDonHang]("
					+ dhId + ") ";
			if (dbSync.updateReturnInt(query) <= 0) {
				dbDms.getConnection().rollback();
				dbDms.getConnection().setAutoCommit(true);
				dbSync.getConnection().rollback();
				dbSync.getConnection().setAutoCommit(true);
				dbSync.shutDown();
				dbDms.shutDown();
				return "Lỗi đồng bộ 2 ; " + query;
			}

			dbDms.getConnection().commit();
			dbSync.getConnection().commit();
			dbDms.getConnection().setAutoCommit(true);
			dbSync.getConnection().setAutoCommit(true);
			dbSync.shutDown();
			dbDms.shutDown();
			return "Đồng bộ thành công";
		} catch (Exception e) {
			dbDms.update("update");
			dbSync.update("update");
			dbSync.shutDown();
			dbDms.shutDown();
			return "Lỗi đồng bộ  ; " + e.getMessage();
		}
	}

	/*
	 * public String DuyetDonHang(String dhId, String vitriBAM, String userId) {
	 * String pxkId = ""; String loaiNPP = ""; String tuxuatOTC = ""; this.userId=
	 * userId; try {
	 * 
	 * 
	 * 
	 * 
	 * db.update("SET TRANSACTION ISOLATION LEVEL SNAPSHOT;");
	 * db.update("SET LOCK_TIMEOUT 60000;");
	 * 
	 * db.getConnection().setAutoCommit(false);
	 * 
	 * //Kiểm tra trong trường hợp 2 user chốt cùng một lúc String query =
	 * "select trangthai, dh.donquatang, " +
	 * "	( select count(*) from HOADON_DDH where ddh_fk = dh.pk_seq  " +
	 * "			and hoadon_fk in ( select pk_seq from HOADON where trangthai in ( 1, 2, 4 )  "
	 * + " and npp_fk = ( select npp_fk from DONHANG where pk_seq = '" + dhId +
	 * "' ) and loaihoadon = '0' ) ) as soDongHD, " +
	 * "   isnull(donhangkhac, 0) as donhangkhac, " +
	 * "	( select count(*) from DONHANG_CTKM_TRAKM where donhangID = dh.pk_seq and spMA is not null "
	 * +
	 * "			 and ctkmId in ( select pk_seq from CTKHUYENMAI ) ) as coKHOHB, "
	 * +
	 * "	( select count(*) from DONHANG_CTKM_TRAKM where donhangID = dh.pk_seq and spMA is not null "
	 * +
	 * "			 and ctkmId in ( select pk_seq from CTKHUYENMAI where kho_fk = '100001' ) ) as coKHOKM, "
	 * +
	 * "   ( select count(pk_seq) from NHAPHANPHOI where pk_seq = dh.npp_fk and TUTAOHOADON = '1' and priandsecond = '1' ) as tuxuatHD "
	 * +
	 * "	, (select count(NVGN_FK) from nvgn_kh where nvgn_kh.khachhang_fk = dh.khachhang_fk) coNVGN  "
	 * + "from DONHANG dh where pk_seq = '" + dhId + "' ";
	 * 
	 * ////System.out.println("query _______"+query); ResultSet checkDH = db.get(query);
	 * String trangthaiDH = ""; int isDHK = 0; //int isKM = 0; //int chicoKHOKM = 0;
	 * int soHD = 0; int tuxuatHD = 0; boolean cokoHANGBAN = false; boolean
	 * cokoHANGKM = false; int coNVGN = 0; String donquatang = ""; { while
	 * (checkDH.next()) { trangthaiDH = checkDH.getString("trangthai"); soHD =
	 * checkDH.getInt("soDongHD"); isDHK = checkDH.getInt("donhangkhac"); //isKM =
	 * checkDH.getInt("sodongKM"); //chicoKHOKM = checkDH.getInt("chicoKHOKM");
	 * tuxuatHD = checkDH.getInt("tuxuatHD"); donquatang =
	 * checkDH.getString("donquatang"); coNVGN = checkDH.getInt("coNVGN"); if
	 * (checkDH.getInt("coKHOHB") > 0 ) cokoHANGBAN = true;
	 * 
	 * 
	 * } checkDH.close(); }
	 * 
	 * if (trangthaiDH.equals("1")) { db.getConnection().rollback(); return
	 * "Đơn hàng này đã chốt rồi." ; }
	 * 
	 * if (soHD >= 2) { db.getConnection().rollback(); return
	 * "Đơn hàng này đã có hóa đơn rồi. Vui lòng kiểm tra lại" ; }
	 * 
	 * if (coNVGN <=0) { db.getConnection().rollback(); return " vui long chon nvgn"
	 * ; } //DON HANG KHONG DUOC VUOT QUA 13 DONG int sodongHD = 0; query =
	 * "select (select count(*) from DONHANG d1 inner join DONHANG_CTKM_TRAKM d2 \n "
	 * + "	on d1.PK_SEQ=d2.DONHANGID \n "+
	 * "	inner join CTKHUYENMAI d3 on d3.PK_SEQ=d2.CTKMID \n "+
	 * "	where d1.PK_SEQ=a.pk_seq and d3.inchung=1)+ ( select count(*) from DONHANG_SANPHAM where donhang_fk = a.pk_seq ) +  "
	 * +
	 * "	   ( select count(*) from DONHANG_CHIETKHAUBOSUNG where donhang_fk = a.pk_seq ) +  "
	 * + "	   (  " + "			select count(*) " + "			from " +
	 * "			( " +
	 * "				select N'CN5' as diengiai, sum(chietkhau) as chietkhau, thueVAT, 1 as STT, 0 as tichluyQUY, 0 as cotheXOA from DONHANG_SANPHAM   "
	 * + "				where donhang_fk = '" + dhId +
	 * "' and thueVAT = '5' and chietkhau > 0 group by thueVAT  " +
	 * "			union   " +
	 * "				select N'CN10' as diengiai, sum(chietkhau) as chietkhau, thueVAT, 2 as STT, 0 as tichluyQUY, 0 as cotheXOA   "
	 * + "				from DONHANG_SANPHAM   " +
	 * "				where donhang_fk = '" + dhId +
	 * "' and thueVAT = '10' and chietkhau > 0 group by thueVAT  " +
	 * "			union  " +
	 * "				select a.diengiai, a.thanhtoan / ( 1 + ptTHUE / 100 ) as chietkhau, a.ptTHUE as thueVAT, 3 as STT, tichluyQUY, 1 as cotheXOA   "
	 * +
	 * "				from DUYETTRAKHUYENMAI_DONHANG a inner join TIEUCHITHUONGTL b on a.duyetkm_fk = b.PK_SEQ    "
	 * + "				where a.donhang_fk = '" + dhId +
	 * "' and a.thanhtoan != 0 and hienthi = '1' " + "			) " + "			TL "
	 * + "	   )  " + "	   as soDONG " + "from DONHANG a where pk_seq = '" + dhId +
	 * "' "; // ////System.out.println("----CHECK 13 DONG: " + query );
	 * 
	 * ResultSet rsSODONG = db.get(query); if (rsSODONG.next()) { sodongHD =
	 * rsSODONG.getInt("soDONG"); rsSODONG.close(); }
	 * 
	 * if (sodongHD > 13) { db.getConnection().rollback(); return
	 * "Số dòng sản phẩm và chiết khấu của đơn hàng không được vượt quá 13 dòng "; }
	 * 
	 * if (!db.update("update nhanvien set active=1 where pk_seq = " + userId)) {
	 * db.getConnection().rollback(); msg="vui lòng thực hiện lại....... "; return
	 * msg; }
	 * 
	 * int flag_inchung=0; int coinrieng=0;
	 * 
	 * 
	 * query =
	 * "select b.loaiNPP, a.NPP_FK, a.ngaynhap, b.priandsecond as tuchotOTC, a.khachhang_fk, "
	 * +
	 * "	ISNULL( ( select count(donhang_fk) from PHIEUXUATKHO_DONHANG where donhang_fk = a.pk_seq and PXK_FK in ( select pk_seq from PHIEUXUATKHO where NPP_FK = b.pk_seq and trangthai != '2' ) ), 0) as exitPXK, "
	 * + "   (  select count(donhangID) from DONHANG_CTKM_TRAKM where donhangID = '"
	 * +dhId+"' and spMA is not null and ctkmId in ( select pk_seq from CTKHUYENMAI where kho_fk = '100000'  and isnull(inchung,0) = 0) ) as coKHOHB,	"
	 * + "   (  select count(donhangID) from DONHANG_CTKM_TRAKM where donhangID = '"
	 * +dhId+"' and spMA is not null and ctkmId in ( select pk_seq from CTKHUYENMAI where kho_fk = '100000'  and isnull(inchung,0) = 1) ) as coinrieng	"
	 * +
	 * "from DONHANG a inner join NHAPHANPHOI b on a.NPP_FK = b.pk_seq where a.pk_seq = '"
	 * + dhId + "' "; ////System.out.println("cau query 111 : " + query); boolean
	 * exitPXK = false; String nppId = ""; String ngaynhap = ""; tuxuatOTC = "";
	 * String khachhang_fk="";
	 * 
	 * ResultSet rs = db.get(query); { if (rs.next()) { loaiNPP =
	 * rs.getString("loaiNPP"); nppId = rs.getString("NPP_FK"); ngaynhap =
	 * rs.getString("ngaynhap");
	 * 
	 * tuxuatOTC = rs.getString("tuchotOTC"); khachhang_fk =
	 * rs.getString("khachhang_fk"); flag_inchung = rs.getInt("coKHOHB");
	 * coinrieng=rs.getInt("coinrieng"); if (rs.getInt("exitPXK") > 0 ) exitPXK =
	 * true;
	 * 
	 * rs.close(); }
	 * 
	 * 
	 * geso.dms.center.util.Utility util_kho=new geso.dms.center.util.Utility();
	 * String ngayhoadon_= Utility.getNgayHienTai();
	 * //util_kho.getngayhoadon(this.userId,db,ngaynhap,khachhang_fk,0); ///Booked
	 * kho if (!loaiNPP.equals("0")) { String bookedMsg = BookedKhoDonHang( dhId,
	 * vitriBAM, ngayhoadon_, util_kho) ; if (bookedMsg.trim().length() > 0 ) {
	 * db.getConnection().rollback(); db.getConnection().setAutoCommit(true); return
	 * bookedMsg; } } if (tuxuatHD <= 0 || !tuxuatOTC.equals("1") ) {
	 * db.getConnection().rollback(); return
	 * "Vui lòng thiết lập tự tạo hóa đơn trên dữ liệu nền nhà phân phối "; }
	 * 
	 * if ( tuxuatOTC.equals("1") ) { if (loaiNPP.equals("0")) query =
	 * "Update DONHANG set synced = '1' , nguoisua = "+ userId
	 * +",NgaySua='"+getDateTime()+"' where pk_seq = '" + dhId +
	 * "' and TrangThai = 0 and isnull(synced,0)= 0 "; else query =
	 * "Update DONHANG set trangthai = '1' , nguoisua = "+ userId
	 * +",NgaySua='"+getDateTime()+"' where pk_seq = '" + dhId +
	 * "' and TrangThai = 0 "; ////System.out.println("update donhang "+query); if
	 * (db.updateReturnInt(query) <= 0) { db.getConnection().rollback(); return
	 * "Đơn hàng đã chốt rồi "; } } }
	 * 
	 * 
	 * ////System.out.println("::::::: BAT DAU RA HOA DON TOTAL: " +
	 * this.getDateTimeTEST() ); if (tuxuatHD > 0 && tuxuatOTC.equals("1") ) { if
	 * (donquatang.equals("0")) { if (isDHK == 0 ) { // TU DONG TAO HOA DON TAI
	 * CHINH ////System.out.println("::::::: BAT DAU RA HOA DON HANG BAN: " +
	 * this.getDateTimeTEST() ); String msg = this.TaoHoaDonTaiChinh(db, dhId,
	 * nppId, loaiNPP, ngaynhap, userId, khachhang_fk); if (msg.trim().length() > 0)
	 * { db.getConnection().rollback(); return "5.Lỗi khi tạo hóa đơn tài chính : "
	 * + msg; }
	 * 
	 * ////System.out.println("::::::: KET THUC RA HOA DON HANG BAN: " +
	 * this.getDateTimeTEST() );
	 * 
	 * String hoadon_id=""; String
	 * sql="select a.pk_seq from hoadon a inner join hoadon_ddh b on a.pk_seq =b.hoadon_fk  where a.trangthai in (1,2) and b.ddh_fk="
	 * +dhId; ////System.out.println("hoadon_id"+sql); ResultSet rst = db.get(sql); if
	 * (rst.next()) { hoadon_id = rst.getString("pk_seq"); } rst.close();
	 * 
	 * // TU TAO HD KHUYEN MAI (NEU CO) --> NGÀY 07/04/2015 thay đổi chỉ SCHEME có
	 * kho hàng bán mới ra hóa đơn ( số hóa đơn NA ), SHCEME kho KM không ra hoa đơn
	 * if ( cokoHANGBAN || cokoHANGKM ) { //Nếu đơn hàng có 2 scheme KM hàng bán và
	 * kho KM thì tạo 2 hóa đơn /////// 1 hóa đơn loại = 1 -> vẫn hiển thị số NA để
	 * in ////// 1 hóa đơn loại = 2 -> không hiển thị, nhưng BC NXT vẫn ghi nhận if
	 * (cokoHANGBAN) {
	 * ////System.out.println("::::::: BAT DAU RA HOA DON HANG KM - 01: " +
	 * this.getDateTimeTEST() ); //msg = this.TaoHoaDonTaiChinhKM(db, dhId, nppId,
	 * loaiNPP, ngaynhap, userId, khachhang_fk, 0, donquatang,
	 * "1",flag_inchung,hoadon_id); msg+=this.TaoHoaDonTaiChinhKM_inrieng(db, dhId,
	 * nppId, loaiNPP, ngaynhap, userId, khachhang_fk, 0, donquatang,
	 * "1",0,hoadon_id,coinrieng);
	 * 
	 * if (msg.trim().length() > 0) { db.getConnection().rollback(); return
	 * "6134.Lỗi khi tạo hóa đơn tài chính KM  : " + msg; }
	 * ////System.out.println("::::::: KET THUC RA HOA DON HANG KM - 01: " +
	 * this.getDateTimeTEST() ); }
	 * 
	 * if (cokoHANGKM) {
	 * ////System.out.println("::::::: BAT DAU RA HOA DON HANG KM - 02: " +
	 * this.getDateTimeTEST() ); msg = this.TaoHoaDonTaiChinhKM(db, dhId, nppId,
	 * loaiNPP, ngaynhap, userId, khachhang_fk, 1, donquatang,
	 * "2",flag_inchung,hoadon_id); if (msg.trim().length() > 0) {
	 * db.getConnection().rollback(); return
	 * "7.Lỗi khi tạo hóa đơn tài chính KM  : " + msg; }
	 * ////System.out.println("::::::: KET THUC RA HOA DON HANG KM - 02: " +
	 * this.getDateTimeTEST() ); } } } else if (isDHK == 1) { // TU DONG TAO HOA DON
	 * TAI CHINH KM TU DH KHAC String msg = this.TaoHoaDonTaiChinhKM_DHK(db, dhId,
	 * nppId, ngaynhap, userId, khachhang_fk, donquatang); if (msg.trim().length() >
	 * 0) { db.getConnection().rollback(); return
	 * "55.Lỗi khi tạo hóa đơn tài chính KM cua DON HANG KHAC : " + msg; } } } else
	 * if (donquatang.equals("1")) { String msg = this.TaoHoaDonTaiChinhKM_DHK(db,
	 * dhId, nppId, ngaynhap, userId, khachhang_fk,donquatang ); if
	 * (msg.trim().length() > 0) { db.getConnection().rollback(); return
	 * "55.Lỗi khi tạo hóa đơn tài chính KM cua DON HANG KHAC : " + msg; } } }
	 * 
	 * if ( loaiNPP.equals("4") || loaiNPP.equals("5") ) { // tự chốt xuất kho if (
	 * isDHK == 0 && donquatang.equals("0") ) { query=
	 * "\n select a.pk_seq,a.npp_fk,a.ngayxuathd,a.nguoitao,convert(char(10),getdate(),126) as ngaytao "
	 * + "\n from HOADON a inner join HOADON_DDH b on a.PK_SEQ=b.HOADON_FK "+
	 * "\n where b.ddh_fk="+dhId+"  and a.LOAIHOADON=0 and a.trangthai=2 "; } else {
	 * query=
	 * "\n select a.pk_seq,a.npp_fk,a.ngayxuathd,a.nguoitao,convert(char(10),getdate(),126) as ngaytao "
	 * + "\n from HOADON a inner join HOADON_DDH b on a.PK_SEQ=b.HOADON_FK "+
	 * "\n where b.ddh_fk="+dhId+"  and a.LOAIHOADON !=0 and a.trangthai=2 "; }
	 * 
	 * 
	 * ResultSet rsCheck= db.get(query); rsCheck.next(); String hdId =
	 * rsCheck.getString("pk_seq"); String kqtpxk =
	 * Hoadontaichinh.create_pxk(db,hdId,userId); if (kqtpxk.trim().length() > 0) {
	 * rsCheck.close(); db.getConnection().rollback(); return kqtpxk;
	 * 
	 * } rsCheck.close(); }
	 * 
	 * 
	 * ////System.out.println("::::::: KET THUC RA HOA DON TOTAL: " +
	 * this.getDateTimeTEST() );
	 * 
	 * Utility util = new Utility(); msg = util.Check_Kho_Tong_VS_KhoCT(nppId, db);
	 * if (msg.length() > 0) { db.getConnection().rollback(); return msg; }
	 * 
	 * msg = util.Check_Huy_NghiepVu_KhoaSo("donhang", dhId, "ngaynhap", db); if (
	 * msg.length() > 0) { db.getConnection().rollback(); return msg; }
	 * 
	 * String
	 * sqlck=" select a.NPP_FK,b.sanpham_fk,a.KBH_FK,a.KHO_FK,b.solo,b.ngayhethan,b.ngaynhapkho from DONHANG a inner join DONHANG_SANPHAM_chitiet b "
	 * + "	  on a.PK_SEQ=b.donhang_fk where a.PK_SEQ="+dhId+" "+
	 * " 	  union all  "+
	 * "	   select a.NPP_FK,b.sanpham_fk,a.KBH_FK,a.KHO_FK,b.solo,b.ngayhethan,b.ngaynhapkho from DONHANG a inner join DONHANG_CTKM_TRAKM_chitiet b "
	 * + "	  on a.PK_SEQ=b.donhang_fk where a.PK_SEQ="+dhId+""; ResultSet
	 * rsck=db.get(sqlck); while (rsck.next()) {
	 * 
	 * boolean check=util.check_book_chitiet_ngaynhap(db,rsck.getString("npp_fk")
	 * ,rsck.getString("sanpham_fk") ,rsck.getString("kbh_fk") ,
	 * rsck.getString("kho_fk"),
	 * rsck.getString("solo"),rsck.getString("ngayhethan"),rsck.getString(
	 * "ngaynhapkho")); if (!check) { db.getConnection().rollback(); return
	 * "Lượng booked không khớp với kho chi tiết vui lòng liên hệ admin để xử lý .";
	 * }
	 * 
	 * }
	 * 
	 * query ="   select count(*) kq from  ( \n"+
	 * "	 select SUM(SOLUONG) SL,SANPHAM_FK from DONHANG_SANPHAM a where DONHANG_FK="
	 * + dhId +"  group by SANPHAM_FK) \n"+ "	   AA \n"+
	 * "	 full outer join ( \n"+
	 * "	  select SUM(SOLUONG) SL,SANPHAM_FK from DONHANG_SANPHAM_chitiet a where DONHANG_FK="
	 * + dhId +"  group by SANPHAM_FK) \n"+
	 * "	   BB on AA.SANPHAM_FK=BB.sanpham_fk \n"+
	 * "	  where ISNULL(AA.SL,0)<>ISNULL(BB.SL,0)  ";
	 * 
	 * 
	 * ResultSet ckek=db.get(query); ckek.next(); if (ckek.getInt("kq")!=0) {
	 * db.getConnection().rollback(); return
	 * "số lượng tổng và chi tiết không khớp nhau " ;
	 * 
	 * 
	 * } ckek.close();
	 * 
	 * 
	 * query = "\n   	select count(*) kq " + "\n   	from  " + "\n		( 	" +
	 * "\n					select SUM(SOLUONG) SL,sp.PK_SEQ SANPHAM_FK,a.kho_fk from DonHang_CTKM_TRAKM a "
	 * + "\n					inner join SANPHAM sp on sp.MA = a.SPMA " +
	 * "\n					where DONHANGID="+ dhId
	 * +"  group by sp.PK_SEQ,a.kho_fk ) AA "+ "\n		full outer join " +
	 * "\n		(	select SUM(SOLUONG) SL,SANPHAM_FK,kho_fk " +
	 * "\n			from DonHang_CTKM_TRAKM_chitiet a " +
	 * "\n			where DONHANG_FK="+ dhId +"  group by SANPHAM_FK,kho_fk " +
	 * "\n		) BB on AA.SANPHAM_FK=BB.sanpham_fk and AA.kho_fk=BB.kho_fk "+
	 * "\n	  	where ISNULL(AA.SL,0)<>ISNULL(BB.SL,0)  ";
	 * 
	 * 
	 * ckek=db.get(query); ckek.next(); if (ckek.getInt("kq")!=0) {
	 * db.getConnection().rollback(); return
	 * "số lượng tổng và chi tiết KM không khớp nhau " + query ;
	 * 
	 * 
	 * } ckek.close();
	 * 
	 * 
	 * util.Update_GiaTri_DonHang(dhId, db);
	 * 
	 * db.getConnection().commit(); db.getConnection().setAutoCommit(true); } catch
	 * (Exception e) { e.printStackTrace(); try { db.getConnection().rollback(); }
	 * catch (Exception e1) { e1.printStackTrace();}
	 * 
	 * if (db!=null) db.shutDown(); return "Lỗi khi duyệt đơn hàng: " +
	 * e.getMessage(); }
	 * 
	 * if (vitriBAM.equals("0")) { if ( tuxuatOTC.equals("1") ) return
	 * "Duyệt đơn hàng thành công."; else return
	 * "Duyệt đơn hàng thành công. Số phiếu xuất kho mới tạo là ( " + pxkId + " )";
	 * } else return ""; }
	 */

	private String Chotphieuxuat(dbutils db, String pxkId, String dhId, String nppId, String ngaylap) {
		String msg = "";
		String query = "";

		geso.dms.center.util.Utility util_kho = new geso.dms.center.util.Utility();
		// String
		// ngayhoadon_=util_kho.getngayhoadon(this.userId,db,ngaylap,this.khId,0);
		String ngayhoadon_ = Utility.getNgayHienTai();
		// check ton kho (HANG SU DUNG TRONG KHO NPP)
		query = "\n select pxk_sp.spId, pxk_sp.spMa, isnull(kho_npp.SOLUONG, 0 ) as tonkho, pxk_sp.soluong, kho_npp.booked,   "
				+ "\n		ISNULL (  ( select sum(Total_Booked) from ufn_Booked_Total (  " + nppId + "  )  "
				+ "\n					where sanpham_fk = pxk_sp.spId and kbh_fk = pxk_sp.kbhId and kho_fk = pxk_sp.khoId ), 0 ) as booked_dung "
				+ "\n from   " + "\n (  " + "\n	select khoId, kbhId, spId, spMa, sum(soluong) as soluong   "
				+ "\n	from   " + "\n	(   "
				+ "\n		select c.kho_fk as khoId, c.kbh_fk as kbhId, b.pk_seq as spId, b.ma as spMa, sum(a.soluong) as soluong   "
				+ "\n		from donhang_sanpham a inner join sanpham b on a.sanpham_fk = b.pk_seq inner join donhang c on a.donhang_fk = c.pk_seq   "
				+ "\n		where c.trangthai != 2 and a.donhang_fk in ( select donhang_fk from phieuxuatkho_donhang where pxk_fk =  '"
				+ pxkId + "' ) and a.khoNVBH = '0'    " + "\n		group by c.kho_fk, c.kbh_fk, b.pk_seq, b.ma   "
				+ "\n	union all   "
				+ "\n		select a.kho_fk as khoId, e.kbh_fk as kbhId, d.pk_seq as spId, a.spMa, sum(a.soluong) as soluong   "
				+ "\n		from donhang_ctkm_trakm a inner join ctkhuyenmai b on a.ctkmid = b.pk_seq   "
				+ "\n			inner join sanpham d on a.spMa = d.ma inner join donhang e on a.donhangId = e.pk_seq   "
				+ "\n		where e.trangthai != 2 and a.spMa is not null and a.donhangId in ( select donhang_fk from phieuxuatkho_donhang where pxk_fk =  '"
				+ pxkId + "' ) and a.khoNVBH = '0'   "
				+ "\n		group by a.kho_fk, e.kbh_fk, a.ctkmId, a.spMa, d.pk_seq   " + "\n	 ) a   "
				+ "\n	 group by khoId, kbhId, spId, spMa   " + "\n)   " + "\n pxk_sp left join   " + "(\n   "
				+ "\n	select kho_fk, npp_fk, sanpham_fk, kbh_fk, soluong, AVAILABLE, booked   "
				+ "\n	from NHAPP_KHO where npp_fk = '" + nppId + "'  " + ")\n   "
				+ "\n kho_npp on pxk_sp.khoId = kho_npp.kho_fk and pxk_sp.kbhId = kho_npp.kbh_fk and pxk_sp.spId = kho_npp.sanpham_fk   "
				+ "\n where ( isnull( kho_npp.SOLUONG, 0 ) < pxk_sp.soluong )  or ( kho_npp.soluong <> kho_npp.booked + kho_npp.AVAILABLE )    "
				+ "\n		or ( kho_npp.booked != ISNULL (  (  select sum(Total_Booked) from ufn_Booked_Total (  "
				+ nppId + "  )  "
				+ "\n											where sanpham_fk = pxk_sp.spId and kbh_fk = pxk_sp.kbhId and kho_fk = pxk_sp.khoId ), 0 ) )  ";

		////System.out.println("Query check chot phieu xuat kho: " + query + "");
		ResultSet sosp = db.get(query);
		String spMa = "";
		if (sosp != null) {
			try {
				while (sosp.next()) {
					if (sosp.getDouble("tonkho") < sosp.getDouble("soluong")) {
						spMa += sosp.getString("spMa") + ", ";
						////System.out.println("loi :::::::::::::::::::::::: " + sosp.getDouble("booked") + sosp.getDouble("booked_dung") + sosp.getDouble("tonkho") + "________" + sosp.getDouble("soluong"));
					}

					if (sosp.getDouble("booked") != sosp.getDouble("booked_dung")) {
						// Sai booked, cập nhật lại
						////System.out.println("booked sai " + sosp.getString("spMa") + "_______" + sosp.getDouble("booked")+ "____booked dung" + sosp.getDouble("booked_dung"));
						FixData fixed = new FixData();
						fixed.ProcessBOOKED(nppId, sosp.getString("spId"), db);

					}
				}
				sosp.close();
			} catch (Exception e1) {
				e1.printStackTrace();
				return "Loi: " + e1.getMessage();
			}
		}

		if (spMa.length() > 0) {
			msg = "Các mã sản phẩm sau: " + spMa
					+ " không đủ số lượng trong kho NHÀ PHÂN PHỐI. Vui lòng kiểm tra lại số lượng trước khi chốt phiếu xuất kho";
			return msg;
		}

		// Xử lý booked nếu có sai -> CHỈ XỬ LÝ NHỮNG SP BỊ LỆCH THÔI, CUỐI NGÀY CHẠY TỰ
		// ĐỘNG 1 LƯỢT, TRÁNH TRƯỜNG HỢP DEADLOCK
		/*
		 * FixData fixed = new FixData(); fixed.ProcessBOOKED_DONHANG(nppId, "", "",
		 * db);
		 */

		try {
			// INSERT SAN PHAM
			query = " Insert into phieuxuatkho_sanpham(pxk_fk, sanpham_fk, kho_fk, kbh_fk, soluong,isnhapkhau ) "
					+ " select '" + pxkId
					+ "', b.pk_seq as spId, c.kho_fk as khoId, c.kbh_fk as kbhId, sum(a.soluong) as soluong,isnull(isnhapkhau,1)  as isnhapkhau  "
					+ " from donhang_sanpham a inner join sanpham b on a.sanpham_fk = b.pk_seq inner join donhang c on a.donhang_fk = c.pk_seq   "
					+ " where c.trangthai != 2 and a.donhang_fk in ( " + dhId + " )  "
					+ " group by c.kho_fk, c.kbh_fk, b.pk_seq,isnull(isnhapkhau,1) ";
			// ////System.out.println("---4.CHEN SP: " + query);
			if (db.updateReturnInt(query) <= 0) {
				// db.getConnection().rollback();
				return "4.Lỗi khi chốt xuất kho: " + query;
			}
			int checkkho = 0;
			// B1. CAP NHAT KHO TONG

			query = " select isnull(c.checkkho,0) checkkho,c.kho_fk as khoId, c.kbh_fk as kbhId, b.pk_seq as spId, b.ten as spTEN, c.daraHOADON_OLD, sum(a.soluong) as soluong,isnull(isnhapkhau,1)  as isnhapkhau  "
					+ " from donhang_sanpham  a inner join sanpham b on a.sanpham_fk = b.pk_seq inner join donhang c on a.donhang_fk = c.pk_seq   "
					+ " where c.trangthai != 2 and a.donhang_fk in ( " + dhId + " )  "
					+ " group by c.kho_fk, c.kbh_fk, b.pk_seq, b.ten, c.daraHOADON_OLD ,isnull(isnhapkhau,1),isnull(c.checkkho,0)";
			////System.out.println("---5.CHEN SP: " + query);

			ResultSet rsKHO = db.get(query);
			{
				while (rsKHO.next()) {
					String khoId = rsKHO.getString("khoId");
					String kbhId = rsKHO.getString("kbhId");
					String spId = rsKHO.getString("spId");
					String spTEN = rsKHO.getString("spTEN");
					double soluong = rsKHO.getDouble("soluong");
					int isnhapkhau = rsKHO.getInt("isnhapkhau");
					checkkho = rsKHO.getInt("checkkho");
					// NEU NGAY DON HANG KHAC NGAY HIEN TAI, THI PHAI CHECK NXT TRONG QUA KHU
					if (!ngaylap.equals(this.getDateTime()) && rsKHO.getString("daraHOADON_OLD").equals("0")) {
						Utility util = new Utility();
						msg = util.Check_NghiepVu_QuaKhu(ngaylap, nppId, khoId, kbhId, spId, Double.toString(soluong),
								db);
						if (msg.trim().length() > 0) {
							return "Sản phẩm: " + rsKHO.getString("spTEN") + " không đử nhập xuất tồn. " + msg;
						}
					}

					String msg1 = util_kho.Update_NPP_Kho_Sp(ngayhoadon_, "Chốt phiếu xuất kho Donhang.java 6370 ", db,
							khoId, spId, nppId, kbhId, soluong * (-1), soluong * (-1), 0, 0);
					if (msg1.length() > 0) {
						return msg1;
					}

					if (checkkho == 1) {

						query = " SELECT SP.SANPHAM_FK , DH.KHO_FK, DH.KBH_FK,DH.NPP_FK,SP.SOLO,SP.NGAYHETHAN,SP.NGAYNHAPKHO,SP.SOLUONG "
								+ " FROM  DONHANG_SANPHAM_CHITIET SP INNER JOIN DONHANG DH ON DH.PK_SEQ=SP.DONHANG_FK   "
								+ " WHERE SP.DONHANG_FK = (" + dhId + ")  AND DH.TRANGTHAI!=2  and SP.SANPHAM_FK="
								+ spId;
						ResultSet rssp = db.get(query);
						double tongluongxuatCT = 0;
						while (rssp.next()) {
							String _spid = rssp.getString("SANPHAM_FK");
							String _KHO_FK = rssp.getString("KHO_FK");
							String _KBH_FK = rssp.getString("KBH_FK");
							String _NPP_FK = rssp.getString("NPP_FK");
							String _SOLO = rssp.getString("SOLO");
							String _NGAYNHAPKHO = rssp.getString("NGAYNHAPKHO");
							String _NGAYHETHAN = rssp.getString("NGAYHETHAN");
							double soluongct = rssp.getDouble("SOLUONG");

							msg1 = util_kho.Update_NPP_Kho_Sp_Chitiet(ngayhoadon_, "Chốt đơn hàng donhang.java 6401",
									db, _KHO_FK, _spid, _NPP_FK, _KBH_FK, _SOLO, _NGAYHETHAN, _NGAYNHAPKHO,
									(-1) * soluongct, (-1) * soluongct, 0, 0, 0);

							if (msg1.length() > 0) {
								return msg1;
							}
							tongluongxuatCT += soluongct;
						}
						rssp.close();

						if (tongluongxuatCT != soluong) {

							return "6448.Lỗi hệ thống ( tổng xuất theo lô đề xuất đang bị sai ). Vui lòng liên hệ trung tâm để được hỗ trợ xử lý.";
						}

						query = "   select ngaynhapkho ,isnull(dhsp.isnhapkhau,1) as isnhapkhau,data.soluong,data.npp_fk,data.kbh_fk,data.kho_fk,data.sanpham_fk,data.solo,data.ngayhethan  from  ( \n"
								+ "	 select a.donhang_fk,a.npp_fk,a.kbh_fk,a.kho_fk,a.sanpham_fk,a.solo,a.ngayhethan,a.ngaynhapkho ,sum(cast (a.soluong as float)) soluong \n"
								+ "	 from donhang_sanpham_chitiet  a  \n"
								+ "	  inner join donhang c on a.donhang_fk = c.pk_seq     \n"
								+ "	 where c.trangthai != 2 and a.donhang_fk in ( " + dhId + "  ) and a.sanpham_fk ="
								+ spId + "    \n"
								+ "	  group by  a.npp_fk,a.kbh_fk,a.kho_fk,a.sanpham_fk,a.solo,a.ngayhethan,a.donhang_fk,ngaynhapkho   \n"
								+ " 	) as data inner join DONHANG_SANPHAM dhsp on data.donhang_fk=dhsp.DONHANG_FK  \n"
								+ "	 and data.sanpham_fk=dhsp.SANPHAM_FK ";

						////System.out.println("query la -----------  " + query);
						ResultSet rskq = db.get(query);
						while (rskq.next()) {
							query = " insert into PHIEUXUATKHO_SANPHAM_CHITIET (PXK_FK, KBH_FK, KHO_FK, SANPHAM_FK, SOLUONG, SOLO, NGAYHETHAN,isnhapkhau,ngaynhapkho) "
									+ " values( '" + pxkId + "', '" + rskq.getString("kbh_fk") + "', '"
									+ rskq.getString("kho_fk") + "', '" + rskq.getString("sanpham_fk") + "' " + ", '"
									+ rskq.getString("soluong") + "', '" + rskq.getString("SOLO") + "', '"
									+ rskq.getString("NGAYHETHAN") + "'," + rskq.getString("isnhapkhau") + " ,'"
									+ rskq.getString("ngaynhapkho") + "')";
							////System.out.println("---CHEN PXK CHI TIET: " + query);
							if (db.updateReturnInt(query) != 1) {
								// db.getConnection().rollback();
								return "7.Lỗi khi chốt xuất kho: " + query;
							}
						}
						rskq.close();

					}

				}
				rsKHO.close();
			}

			// CHECK TONG KHO CHI TIET PHAI BANG TONG TRONG KHO TONG
			query = "select count(*) as soDONG  " + "from PHIEUXUATKHO_SANPHAM tong left join  " + "	( "
					+ "		select sanpham_fk, kbh_fk, kho_fk, sum(soluong) as soluong  "
					+ "		from PHIEUXUATKHO_SANPHAM_CHITIET " + "		where  PXK_FK = '" + pxkId + "' "
					+ "		group by sanpham_fk, kbh_fk, kho_fk " + "	) "
					+ "	CT on tong.sanpham_fk = CT.sanpham_fk and tong.kbh_fk = CT.kbh_fk and tong.kho_fk = CT.kho_fk "
					+ "where PXK_FK = '" + pxkId + "' and tong.soluong != isnull(CT.soluong, 0) ";
			////System.out.println("---CHECK PXK CHI TIET: " + query);
			ResultSet rsCHECK = db.get(query);
			int soDONG = 0;
			/* if (rsCHECK != null ) */
			{
				if (rsCHECK.next()) {
					soDONG = rsCHECK.getInt("soDONG");
				}
				rsCHECK.close();
			}

			if (soDONG > 0) {
				// db.getConnection().rollback();
				return "1609.Lỗi hệ thống ( tổng xuất theo lô đề xuất đang bị sai ). Vui lòng liên hệ trung tâm để được hỗ trợ xử lý.";
			}

			// INSERT SPKM
			query = " Insert into phieuxuatkho_spkm(pxk_fk, sanpham_fk, kho_fk, kbh_fk, scheme, soluong, khoNVBH, NVBH_FK)  "
					+ " select '" + pxkId
					+ "', d.pk_seq as spId,  a.kho_fk as khoId, e.kbh_fk as kbhId, a.ctkmId, sum(a.soluong) as soluong, a.khoNVBH, ( case when khoNVBH = 0 then NULL else e.ddkd_fk end )  "
					+ " from donhang_ctkm_trakm a inner join ctkhuyenmai b on a.ctkmid = b.pk_seq inner join sanpham d on a.spMa = d.ma inner join donhang e on a.donhangId = e.pk_seq  "
					+ " where e.trangthai != 2 and a.spMa is not null and a.donhangId in ( " + dhId + " )  "
					+ " group by a.kho_fk, e.kbh_fk, a.ctkmId, d.pk_seq, khoNVBH, ( case when khoNVBH = 0 then NULL else e.ddkd_fk end )  ";
			if (!db.update(query)) {
				// db.getConnection().rollback();
				return "4.Lỗi khi chốt xuất kho: " + query;
			}

			// B1. CAP NHAT KHO TONG HANG KHUYEN MAI
			query = " select d.ten ,'" + pxkId
					+ "', d.pk_seq as spId,  a.kho_fk as khoId, e.kbh_fk as kbhId, a.ctkmId, sum(a.soluong) as soluong  "
					+ " from donhang_ctkm_trakm a inner join ctkhuyenmai b on a.ctkmid = b.pk_seq inner join sanpham d on a.spMa = d.ma inner join donhang e on a.donhangId = e.pk_seq  "
					+ " where e.trangthai != 2 and a.spMa is not null and a.donhangId in ( " + dhId + " )  "
					+ " group by a.kho_fk, e.kbh_fk, a.ctkmId, d.pk_seq ,d.ten  ";
			// ////System.out.println("---5.1.CHEN SPKM: " + query);
			rsKHO = db.get(query);
			{
				while (rsKHO.next()) {
					String khoId = rsKHO.getString("khoId");
					String kbhId = rsKHO.getString("kbhId");
					String spId = rsKHO.getString("spId");
					String ctkmId = rsKHO.getString("ctkmId");
					double soluong = rsKHO.getDouble("soluong");

					String sp_ten = rsKHO.getString("ten");

					/*
					 * query = "update nhapp_kho set BOOKED = BOOKED - '" + soluong +
					 * "', SOLUONG = SOLUONG - '" + soluong + "' " + " where sanpham_fk = '" + spId
					 * + "' and npp_fk = '" + nppId + "' and kho_fk = '" + khoId +
					 * "' and kbh_fk = '" + kbhId + "' ";
					 * 
					 * ////System.out.println("---UPDATE KHO TONG: " + query);
					 * 
					 * if (!db.update(query)) { //db.getConnection().rollback(); return
					 * "5.Lỗi khi chốt xuất kho: " + query; }
					 */

					String msg1 = util_kho.Update_NPP_Kho_Sp(ngaylap, "Chốt đơn hàng  donhang.java 6628 ", db, khoId,
							spId, nppId, kbhId, soluong * (-1), soluong * (-1), 0, 0);

					if (msg1.length() > 0) {
						return msg1;
					}

				}
				rsKHO.close();
			}

			if (checkkho == 1) {

				query = " SELECT   DH.KBH_FK,DHCT.KHO_FK ,DHCT.CTKM_FK,SANPHAM_FK,SOLO,NGAYHETHAN,NGAYNHAPKHO ,SUM(SOLUONG) AS SOLUONG "
						+ " FROM DONHANG_CTKM_TRAKM_CHITIET  DHCT INNER JOIN DONHANG DH ON DH.PK_SEQ= DHCT.DONHANG_FK  "
						+ " inner join ctkhuyenmai KM ON KM.PK_SEQ=DHCT.CTKM_FK " + " WHERE DONHANG_FK IN (" + dhId
						+ ") "
						+ " GROUP BY DHCT.CTKM_FK,TRAKM_FK,SANPHAM_FK,SOLO,NGAYHETHAN,NGAYNHAPKHO,DH.KBH_FK,DHCT.KHO_FK ";

				ResultSet rs = db.get(query);
				while (rs.next()) {
					String spid = rs.getString("SANPHAM_FK");
					String ctkmid = rs.getString("CTKM_FK");
					double soluong = rs.getDouble("SOLUONG");
					String solo = rs.getString("solo");
					String NGAYHETHAN = rs.getString("NGAYHETHAN");
					String NGAYNHAPKHO = rs.getString("NGAYNHAPKHO");
					String _khoid = rs.getString("KHO_FK");
					String _kbhid = rs.getString("KBH_FK");

					query = " insert into PHIEUXUATKHO_SPKM_CHITIET (PXK_FK, KBH_FK, KHO_FK, SANPHAM_FK, SCHEME, SOLUONG, SOLO, NGAYHETHAN,NGAYNHAPKHO) "
							+ " values( '" + pxkId + "', '" + _kbhid + "', '" + _khoid + "', '" + spid + "', '" + ctkmid
							+ "', '" + soluong + "', '" + solo + "', '" + NGAYHETHAN + "','" + NGAYNHAPKHO + "' )";
					// ////System.out.println("---CHEN PXK-SPKM CHI TIET: " + query);
					if (!db.update(query)) {
						// db.getConnection().rollback();
						return "7.Lỗi khi chốt xuất kho: " + query;
					}
					String msg1 = util_kho.Update_NPP_Kho_Sp_Chitiet(ngayhoadon_, "Chốt đơn hàng donhang.java 6760 ",
							db, _khoid, spid, nppId, _kbhid, solo, NGAYHETHAN, NGAYNHAPKHO, soluong * (-1),
							soluong * (-1), 0, 0, 0);

					if (msg1.length() > 0) {
						// db.getConnection().rollback();
						return "6742 .Lỗi khi chốt xuất kho: " + msg1;
					}
				}
			}

			// CHECK TONG KHO CHI TIET PHAI BANG TONG TRONG KHO TONG
			query = // "select count(*) as soDONG " +
					" select ct.sanpham_fk,tong.sanpham_fk as sp,tong.soluong, isnull(CT.soluong, 0) as tong from PHIEUXUATKHO_SPKM tong left join  "
							+ "	( " + "		select sanpham_fk, kbh_fk, kho_fk, sum(soluong) as soluong  "
							+ "		from PHIEUXUATKHO_SPKM_CHITIET " + "		where  PXK_FK = '" + pxkId + "' "
							+ "		group by sanpham_fk, kbh_fk, kho_fk " + "	) "
							+ "	CT on tong.sanpham_fk = CT.sanpham_fk and tong.kbh_fk = CT.kbh_fk and tong.kho_fk = CT.kho_fk "
							+ "where PXK_FK = '" + pxkId + "' and tong.soluong != isnull(CT.soluong, 0) ";
			rsCHECK = db.get(query);
			soDONG = 0;
			{
				while (rsCHECK.next()) {
					// soDONG = rsCHECK.getInt("soDONG");
					soDONG = 1;
					////System.out.println("lech la " + rsCHECK.getString("sp") + " ---- " + rsCHECK.getString("sanpham_fk") + "--- tong " + rsCHECK.getDouble("soluong") + " ct----" + rsCHECK.getDouble("tong"));
				}
				rsCHECK.close();
			}

			if (soDONG > 0) {
				return "6811.Lỗi hệ thống ( tổng xuất theo lô đề xuất đang bị sai ). Vui lòng liên hệ trung tâm để được hỗ trợ xử lý.";
			}

			query = " SELECT *FROM  " + " ( "
					+ " SELECT sp.PK_SEQ ,SUM(soluong) as soluong FROM DONHANG_CTKM_TRAKM  dh "
					+ " 	inner join SANPHAM sp on sp.MA=dh.SPMA " + " WHERE DONHANGID IN (" + dhId
					+ ") AND SPMA IS NOT NULL " + " group by sp.PK_SEQ " + " ) A  " + " FULL OUTER JOIN " + " (  "
					+ " SELECT SP.PK_SEQ,SUM(KM.SOLUONG) AS SOLUONG "
					+ " FROM PHIEUXUATKHO_SPKM KM INNER JOIN SANPHAM SP ON SP.PK_SEQ=KM.SANPHAM_FK " + " WHERE PXK_FK ="
					+ pxkId + " " + " GROUP BY SP.PK_SEQ " + " ) B ON A.PK_SEQ=B.PK_SEQ  "
					+ " WHERE ISNULL(A.soluong,0) <> ISNULL(B.SOLUONG,0)";

			ResultSet rscheck = db.get(query);
			if (rscheck.next()) {
				return "Vui lòng thử lại, sản phẩm khuyến mãi trong phiếu xuất kho và đơn hàng không khớp nhau, nếu không được, vui lòng báo Admin để được trợ giúp";
			}
			rscheck.close();

			query = " Insert into phieuxuatkho_tienkm(pxk_fk, scheme, tonggiatri) " + " select '" + pxkId
					+ "', ctkmID, sum(a.tonggiatri) as tonggiatri  "
					+ " from donhang_ctkm_trakm a inner join donhang b on a.donhangId = b.pk_seq  "
					+ " where b.trangthai != '2' and a.spMa is null and a.donhangId in ( " + dhId + " )  "
					+ " group by ctkmID ";
			if (!db.update(query)) {
				return "5.Lỗi khi chốt xuất kho: " + query;
			}

			query = "update phieuxuatkho set trangthai = '1' where pk_seq = '" + pxkId + "' ";
			if (!db.update(query)) {
				return "8.Lỗi khi chốt xuất kho: " + query;
			}

		} catch (Exception e) {
			e.printStackTrace();
			return "Lỗi khi chốt PXK: " + e.getMessage();
		}

		return "";
	}

	private String TaoHoaDonTaiChinhKM_DHK(dbutils db, String dhId, String nppId, String ngaynhap, String userId,
			String khId, String donquatang) {

		////System.out.println("---------- vo donhangkhac");

		String msg = "";
		try {
			this.nppId = nppId;
			String query = "";
			String kyhieuhoadon = "";
			String sohoadon = "";
			String ngayhoadon = "";
			String trangthai = "1";

			// Kiểm tra npp nếu là Đối tác thì không dùng Số hóa đơn của hệ thống
			query = " select loaiNPP  from NHAPHANPHOI  where pk_seq = '" + nppId + "' ";
			ResultSet rsNpp = db.get(query);
			String loainpp = "";
			if (rsNpp != null) {
				while (rsNpp.next()) {
					loainpp = rsNpp.getString("loaiNPP");
				}
				rsNpp.close();
			}
			kyhieuhoadon = "NA";
			sohoadon = "NA";
			ngayhoadon = ngaynhap;
			trangthai = "2";
			/*
			 * if (loainpp.equals("4") || loainpp.equals("5")) // DOI TAC VA CHI NHANH CUA
			 * DOI TAC { kyhieuhoadon = "NA"; sohoadon = "NA"; ngayhoadon = ngaynhap;
			 * trangthai= "2"; } else
			 */
			{
				geso.dms.center.util.Utility util = new geso.dms.center.util.Utility();
				/*
				 * String[] info = util.LayThongTinSHD(db, userId, nppId, ngaynhap, ""); if (
				 * info.length < 3 ) return info[0];
				 */
				// kyhieuhoadon = info[0];

				// sohoadon = "";
				// ngayhoadon = info[2];
			}

			// NẾU LÀ CN HCM : inNguoimuahang =0
			// CN HÀ NỘI DÙNG MẪU 2 (HO) CÒN LẠI DÙNG MẪU 1 (CN)

			query = "\n insert HOADON(DonHangKhac,KBH_FK,DDKD_FK,KHO_FK,GSBH_FK,khachhang_fk, ngayxuatHD, trangthai, ngaytao, nguoitao, ngaysua, nguoisua, kyhieu, sohoadon, hinhthuctt ,"
					+ "\n  	           chietkhau, tongtienbvat, tongtienavat, vat, ghichu, tongtienkm,npp_fk, loaihoadon"
					+ "\n 				, nguoimua, innguoimua, mauhoadon,TENKHACHHANG,DIACHI,MASOTHUE, donquatang,HCH_FK,NgayKyHD) "
					+ "\n  SELECT 1 DHK,DH.kbh_fk , DH.ddkd_fk , DH.KHO_FK, DH.GSBH_fK," + khId + ",'" + ngayhoadon
					+ "', '" + trangthai + "','" + getDateTime() + "', '" + userId + "', '" + getDateTime() + "', '"
					+ userId + "', '" + kyhieuhoadon + "'," + "\n        '" + sohoadon + "', N'TM/CK' , '0', '0', '0',"
					+ "\n        '0', (select isnull(ghichu,'') from donhang where pk_seq =" + dhId + "), '0', " + nppId
					+ " , '1'," + "\n        ( select isnull(chucuahieu,'') as nguoimua from KHACHHANG where pk_seq= '"
					+ khId + "' ), (CASE WHEN DH.NPP_FK = '106210' THEN 1 ELSE 1 END) AS INNGUOIMUA , 1 AS MAU  "
					+ "\n 		,  ( select isnull(TEN,'') as TENKH from KHACHHANG where pk_seq= '" + khId + "' ) "
					+ "\n 		,  ( select isnull(DIACHI,'') as DIACHI from KHACHHANG where pk_seq= '" + khId + "' ) "
					+ "\n 		,  ( select isnull(MASOTHUE,'') as MASOTHUE from KHACHHANG where pk_seq= '" + khId
					+ "' ), " + donquatang + ",DH.HCH_FK,DH.NgayKyHD " + "\n  FROM DONHANG DH  "
					+ "\n  WHERE DH.PK_SEQ = " + dhId + " ";

			////System.out.println("1.Insert HOADON: " + query);
			if (db.updateReturnInt(query) <= 0) {
				msg = "Không thể tạo mới HOADON " + query;
				return msg;
			}

			String hdId = "";
			query = "select SCOPE_IDENTITY() as hdId";
			ResultSet rs1 = db.get(query);
			rs1.next();
			hdId = rs1.getString("hdId");
			rs1.close();

			query = "Insert HOADON_DDH(hoadon_fk, ddh_fk) values (" + hdId + ",  " + dhId + " ) ";
			if (db.updateReturnInt(query) <= 0) {
				msg = "Không thể tạo mới HOADON_DDH " + query;
				return msg;
			}

			query = " insert HOADON_CTKM_TRAKM( hoadonId,hoadon_fk, sanpham_fk, sanphamma, donvi, soluong, ctkm ,dongia, vat,KHO_fK,thuevat,chietkhaungay) "
					+ " select " + hdId + "," + hdId + ", "
					+ " b.PK_SEQ, b.MA, DV.donvi, sum( a.soluong), ' ' as ctkm, a.giamua , a.thuevat,a.Kho_Fk, a.thuevat,a.chietkhaungay "
					+ " from Donhang_SANPHAM a inner Join SanPham b on a.SANPHAM_FK = b.PK_SEQ    "
					+ " 	  INNER JOIN DONVIDOLUONG DV ON DV.PK_SEQ = b.DVDL_FK       " + " where a.Donhang_FK = "
					+ dhId + " " + " group by  b.PK_SEQ, b.MA, DV.donvi, a.giamua, a.thuevat,a.Kho_fk,a.chietkhaungay ";

			////System.out.println("1.1.Insert HOADON_CTKM_TRAKM: " + query);
			if (db.updateReturnInt(query) <= 0) {
				msg = "Khong the tao moi HOADON_CTKM_TRAKM: " + query;
				return msg;
			}

			// TRUONG HOP SAU NAY MUON IN SOLO
			if (!this.quanlykho.equals("0")) {
				query = "  insert HOADON_CTKM_TRAKM_CHITIET(HoaDonId, hoadon_fk, sanpham_fk, soluong, scheme, solo, ngayhethan, kbh_fk, kho_fk, dongia,ngaynhapkho,thuevat,chietkhaungay )  "
						+ "  select '" + hdId + "', '" + hdId
						+ "' hoadon_fk, a.sanpham_fk, a.soluong, NULL as scheme, a.solo, a.ngayhethan, a.kbh_fk, a.kho_fk  "
						+ "  		,dhsp.giamua, a.ngaynhapkho,dhsp.thuevat,dhsp.chietkhaungay  "
						+ "  from DONHANG_SANPHAM_chitiet a inner join DONHANG b on a.donhang_fk = b.PK_SEQ "
						+ "  inner join DONHANG_SANPHAM dhsp on dhsp.donhang_fk = a.donhang_fk and dhsp.sanpham_fk = a.sanpham_fk "
						+ "  where  a.DONHANG_FK = '" + dhId + "'  ";

				////System.out.println("1.2.Insert HOADON_CTKM_TRAKM_CHITIET: " + query);
				if (db.updateReturnInt(query) <= 0) {
					msg = "Khong the tao moi ERP_HOADON_SP: " + query;
					return msg;
				}
			}

			query = " update hd set hd.TONGTIENBVAT = gt.BVAT, hd.TONGTIENAVAT = gt.BVAT + gt.VAT, VAT = gt.VAT   "
					+ "\n from HOADON hd inner join " + "\n ( "
					+ "\n  select a.HOADON_FK, SUM( ROUND(a.SOLUONG * a.DONGIA, 0 ) ) as BVAT, "
					+ "\n    SUM(  ROUND ( ( ROUND ( a.SOLUONG * a.DONGIA , 0 ) * a.VAT / 100.0 ), 0 )  )  as VAT "
					+ "\n  from HOADON_CTKM_TRAKM a  " + "\n  where a.hoadon_fk = '" + hdId + "'  "
					+ "\n  group by a.HOADON_FK " + "\n ) " + "\n gt on hd.pk_seq = gt.hoadon_fk "
					+ "\n where hd.pk_seq = '" + hdId + "'";
			if (db.updateReturnInt(query) <= 0) {
				msg = "Khong the tao moi HOADON: " + query;
				return msg;
			}

			////System.out.println("hoa don khác update :" + query);
			if (db.updateReturnInt(query) <= 0) {
				msg = "Khong the cap nhat HOADON: " + query;
				return msg;
			}

		} catch (Exception e) {
			msg = "Exception: " + e.getMessage();
			e.printStackTrace();
			return msg;
		}

		////System.out.println("MSG " + msg);
		return msg;

	}

	private String TaoHoaDonTaiChinh(dbutils db, String dhId, String nppId, String loainpp, String ngaynhap,
			String userId, String khId) {
		String msg = "";
		try {
			String query = "";
			String kyhieuhoadon = "";
			String sohoadon = "";
			String ngayhoadon = "";

			// KHONG DUOC CHOT HOA DON TRONG THANG DA KHOA SO
			/*
			 * ////System.out.println("+++++ BAT DAU LAY SO HOA DON: " + this.getDateTimeTEST()
			 * ); if ( loainpp.equals("4") || loainpp.equals("5") ) // DOI TAC VA CHI NHANH
			 * CUA DOI TAC trangthai= "2";
			 */
			/*
			 * ////System.out.println("::: USerID: " + userId + " :: NPP ID: " + nppId);
			 * geso.dms.center.util.Utility util = new geso.dms.center.util.Utility();
			 * String[] info = util.LayThongTinSHD(db, userId, nppId, ngaynhap, ""); if (
			 * info.length < 3 ) return info[0];
			 * 
			 * kyhieuhoadon = info[0]; sohoadon = info[1]; ngayhoadon = info[2];
			 */
			String trangthai = "1";
			geso.dms.center.util.Utility util = new geso.dms.center.util.Utility();
			kyhieuhoadon = "KYHIEU";
			sohoadon = "NA";
			// ngayhoadon = Utility.getNgayHienTai();
			ngayhoadon = ngaynhap;
			////System.out.println("sohoadon " + sohoadon);

			////System.out.println("+++++ KET THUC LAY SO HOA DON: " + this.getDateTimeTEST());

			// IN NGUOI MUA : CN HCM = 0 , CON LAI = 1
			// MAU HOA DON : CN HÀ NỘI = 2 (HO), 1 = (CN)

			query = "\n insert HOADON(KBH_FK, DDKD_FK, GSBH_fK, KHO_FK, khachhang_fk, ngayxuatHD, trangthai, ngaytao, nguoitao, ngaysua, nguoisua, "
					+ "\n               kyhieu, sohoadon, hinhthuctt , chietkhau, tongtienbvat, tongtienavat, vat, tongtienkm, ghichu, npp_fk, "
					+ "\n 		        loaihoadon, nguoimua, innguoimua, mauhoadon,TENKHACHHANG,DIACHI,MASOTHUE, donquatang,NgayKyHD,HCH_FK,tiengiamtru) "
					+ "\n SELECT DH.KBH_FK, DH.DDKD_FK, DH.GSBH_fK, DH.KHO_FK, DH.KHACHHANG_FK , '" + ngayhoadon
					+ "', '" + trangthai + "','" + getDateTime() + "', '" + userId + "', '" + getDateTime() + "', '"
					+ userId + "', " + "\n        '" + kyhieuhoadon + "', '" + sohoadon
					+ "', N'TM/CK' , '0', '0', '0', '0', '0', (select isnull(ghichu,'') from donhang where pk_seq = "
					+ dhId + "), " + nppId + " , "
					+ "\n         '0', ISNULL(KH.CHUCUAHIEU,'')  ,( CASE WHEN DH.NPP_FK = 106210  THEN 1 ELSE 1 END) AS INNGUOIMUA , 1 AS MAUHD "
					+ "\n  ,KH.TEN as tenkh , ISNULL(KH.DIACHI,'') as diachikh ,ISNULL(KH.MASOTHUE,'') as mst, DH.donquatang,DH.NgayKyHD,DH.HCH_FK,isnull(DH.tiengiamtru,0)  "
					+ "\n FROM  DONHANG DH  INNER JOIN  KHACHHANG KH ON DH.KHACHHANG_FK = KH.PK_SEQ  "
					+ "\n WHERE DH.PK_SEQ ='" + dhId + "' ";

			////System.out.println("1.Insert HOADON: " + query);
			if (db.updateReturnInt(query) <= 0) {
				msg = "Không thể tạo mới HOADON " + query;
				return msg;
			}

			String hdId = "";
			query = "select SCOPE_IDENTITY() as hdId";
			ResultSet rs1 = db.get(query);
			rs1.next();
			hdId = rs1.getString("hdId");
			rs1.close();

			msg = util.Check_Huy_NghiepVu_KhoaSo("HoaDon", hdId, "NgayXuatHD", db);
			if (msg.length() > 0)
				return msg;

			query = "Insert HOADON_DDH( hoadon_fk, ddh_fk ) values ( " + hdId + ",  " + dhId + " ) ";
			////System.out.println(query);
			if (db.updateReturnInt(query) <= 0) {
				msg = "Không thể tạo mới HOADON_DDH " + query;
				return msg;
			}

			query = " insert HOADON_SP( tienkhuyenmai,khong_tich_luy,ten,hoadon_fk, sanpham_fk, donvitinh, soluong, dongia, dongiaGOC, thanhtien, chietkhau, scheme, vat,Kho_fk,KBH_FK,isnhapkhau,chietkhaungay ) "
					+ "  SELECT a.tienkhuyenmai,a.khong_tich_luy,b.ten," + hdId
					+ ", b.PK_SEQ, DV.DONVI, a.SOLUONG , a.GIAMUA , a.DONGIAGOC, a.SOLUONG * a.GIAMUA , '0', ' ', a.THUEVAT, a.KHO_FK,a.KBH_FK,isnull(a.isnhapkhau,1) as isnhapkhau,a.chietkhaungay "
					+ "  FROM DONHANG_SANPHAM a INNER JOIN SANPHAM b on a.SANPHAM_FK = b.PK_SEQ    "
					+ " 	    INNER JOIN DONVIDOLUONG DV ON DV.PK_SEQ = b.DVDL_FK       " + "  WHERE a.DONHANG_FK = "
					+ dhId + " ";

			if (db.updateReturnInt(query) <= 0) {
				msg = "Khong the tao moi HOADON_SP: " + query;
				return msg;
			}

			query = "insert HOADON_CTKM_TRAKM( ctkm_fk,trakm_fk,hoadon_fk, hoadonId, sanpham_fk, sanphamma, donvi, soluong, ctkm, dongia, vat,Kho_FK,KBH_FK) "
					+ " select a.ctkmId, a.trakmId," + hdId + " hoadon_fk, '" + hdId + "' as hoadonId, "
					+ "	d.pk_seq, d.MA, donvi, a.soluong, b.scheme, " + " 	[dbo].[GiaCkBanLeNppSanPham]("
					+ nppId + ",dhx.khachhang_fk,d.pk_seq,'" + ngaynhap + "' ) as dongia, "
					+ " 	isnull(d.PT_VAT,0) as VAT,a.Kho_Fk, a.kbh_fk  "
					+ "	from donhang_ctkm_trakm a inner join ctkhuyenmai b on a.ctkmid = b.pk_seq "
					+ "	  inner join sanpham d on a.spMa = d.ma "
					+ "     inner join donvidoluong dv on dv.pk_seq = d.dvdl_fk"
					+ "	  inner join donhang dhx on dhx.pk_seq = a.donhangId	 " + "where a.donhangId in (" + dhId
					+ ") and a.spMA is not null ";
			if (db.updateReturnInt(query) < 0) {
				msg = "Khong the tao moi HOADON_CTKM_TRAKM: " + query;
				return msg;
			}

			query = " insert hoadon_chietkhau(CTKM_FK,HOADON_FK,DIENGIAI,chietkhau,trakm_fk) " + " select a.CTKMID,"
					+ hdId + ", ct.scheme, a.tonggiatri, a.trakmId " + " from DonHang_CTKM_TRAKM a"
					+ " inner join  CTKHUYENMAI ct on ct.PK_SEQ = a.CTKMID "
					+ " where a.sanpham_fk is null and  a.donhangID in ( " + dhId + " ) ";
			if (db.updateReturnInt(query) < 0) {
				msg = "Khong the tao moi hoadon_chietkhau: " + query;
				return msg;
			}

			if (quanlykho.equals("1")) {
				query = " insert HOADON_SP_CHITIET( HOADON_FK,sanpham_fk, MA, TEN, DONVI, DONGIA, DONGIAGOC, SOLO, NGAYHETHAN, THUEVAT, SOLUONG, CHIETKHAU, KHO_FK,KBH_FK, isnhapkhau, ngaynhapkho,chietkhaungay )  "
						+ " SELECT '" + hdId
						+ "' as HOADON_FK,c.pk_seq , c.MA, c.TEN, d.DONVI, dhsp.GIAMUA AS DONGIA, dhsp.DONGIAGOC,   "
						+ "		f.SOLO,   " + "		f.NGAYHETHAN, dhsp.thuevat,     "
						+ "	    SUM( f.soluong) as soluong, '0' as chietkhau, dhsp.Kho_Fk,dhsp.kbh_fk, dhsp.isnhapkhau, f.ngaynhapkho,dhsp.chietkhaungay "
						+ " FROM  DONHANG dh INNER JOIN DONHANG_SANPHAM dhsp on dhsp.DONHANG_FK = dh.PK_SEQ    "
						+ "				   INNER JOIN SANPHAM c on dhsp.SANPHAM_FK = c.PK_SEQ                 "
						+ "				   LEFT JOIN DONVIDOLUONG d on d.PK_SEQ = c.DVDL_FK   "
						+ "				   INNER JOIN DONHANG_SANPHAM_CHITIET f on dh.pk_seq = f.donhang_fk and c.PK_SEQ = f.SANPHAM_FK  "
						+ " WHERE dh.PK_SEQ = '" + dhId + "'  and  dhsp.SOLUONG > 0 "
						+ " GROUP BY  dhsp.chietkhaungay,c.pk_seq,c.MA, c.TEN, d.DONVI, dhsp.GIAMUA, dhsp.DONGIAGOC, dhsp.THUEVAT, f.SOLO, f.NGAYHETHAN, dhsp.KHO_FK,dhsp.kbh_fk,dhsp.isnhapkhau,f.ngaynhapkho  ";
				////System.out.println("---CHAY HOA DON CHI TIET: " + hdId);

				if (db.updateReturnInt(query) <= 0) {
					msg = "Lỗi in hóa đơn HOADON_SP_CHITIET: " + query;
					return msg;
				}

				// TRUONG HOP SAU NAY MUON IN SOLO
				query = "insert HOADON_CTKM_TRAKM_CHITIET(ctkm_fk,trakm_fk,hoadon_fk, hoadonId, sanpham_fk, soluong, scheme, solo, ngayhethan, kbh_fk, kho_fk, dongia,NGAYNHAPKHO )  "
						+ "  select  a.CTKM_FK,a.TRAKM_FK," + hdId + " hoadon_fk, '" + hdId
						+ "', a.sanpham_fk, a.soluong, b.scheme, a.solo, a.ngayhethan, c.kbh_fk, a.kho_fk, "
						+ "  		[dbo].[GiaCkBanLeNppSanPham](c.NPP_FK,c.khachhang_fk, a.sanpham_fk, c.NGAYNHAP)	 as dongia, a.NGAYNHAPKHO  "
						+ "  from DONHANG_CTKM_TRAKM_CHITIET a inner join ctkhuyenmai b on a.CTKM_FK = b.pk_seq  "
						+ "  		inner join DONHANG c on a.DONHANG_FK = c.PK_SEQ " + "  where a.DONHANG_FK = '"
						+ dhId + "'  ";

				if (!db.update(query)) {
					msg = "1.2.Khong the tao moi ERP_HOADON_SP: " + query;
					return msg;
				}
			}

			// CHECK THEM - NEU HOA DON KHAC VOI DON HANG THI KHONG CHO CHOT
			query = "select count(*) as sodong  " + "from " + "( " + "	select sanpham_fk, soluong  "
					+ "	from DONHANG_SANPHAM where donhang_fk in ( select DDH_FK from HOADON_DDH where HOADON_FK = '"
					+ hdId + "' ) " + ") " + "dh left join " + "( " + "	select sanpham_fk, sum(soluong) as soluong  "
					+ "	from HOADON_SP " + "	where HOADON_FK = '" + hdId + "'  " + "	group by sanpham_fk " + ") "
					+ "xk on dh.sanpham_fk = xk.sanpham_fk " + "where dh.soluong != isnull(xk.soluong, 0) ";
			int soDONG = 0;
			ResultSet rsCHECK = db.get(query);
			{
				if (rsCHECK.next()) {
					soDONG = rsCHECK.getInt("sodong");
				}
				rsCHECK.close();
			}

			if (soDONG > 0) {
				msg = "3.Số lượng trong đơn hàng không khớp với hóa đơn. Vui lòng liên hệ Admin để xử lý ";
				return msg;
			}
			////System.out.println(":::: THOI DIEM HOAN TATA CHECK TT HOA DON TEST: " + this.getDateTimeTEST());

			////System.out.println(":::: THOI DIEM TINH CHIET KHAU TEST: " + this.getDateTimeTEST());

			// LUU LAI THONG TIN CHIET KHAU
			int donhang_sau_ngay_01 = this.CompareDATE(ngaynhap, "2014-10-01");
			String hienthi = "0";
			if (donhang_sau_ngay_01 < 0)
				hienthi = "1";

			String kqHd = Utility.Update_GiaTri_HoaDon(hdId, db);
			if (kqHd.trim().length() > 0) {
				msg = "Khong the cap nhat HOADON: " + kqHd;
				return msg;
			}

			// insert vao cantru cong no doi voi nhung nha tu chot hoa don
			/*
			 * if (loainpp.equals("4") || loainpp.equals("5")) { //CAP NHAT LAI DA XUAT HOA
			 * DON query =
			 * "update DONHANG set DAXUATHOADON = '1' where pk_seq in ( select DDH_FK from HOADON_DDH where HOADON_FK = '"
			 * + hdId + "' ) "; if (!db.update(query)) { msg =
			 * "Khong the cap nhat DONHANG: " + query; return msg; }
			 * 
			 * 
			 * 
			 * }
			 */
		} catch (Exception e) {
			msg = "Lỗi khi duyệt đơn hàng: " + e.getMessage();
			e.printStackTrace();
			return msg;
		}

		return msg;
	}

	private int CompareDATE(String _date1, String _date2) {
		try {
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			// Date date1 = sdf.parse("2014-10-01");
			// Date date2 = sdf.parse("2014-10-01");

			Date date1 = sdf.parse(_date1);
			Date date2 = sdf.parse(_date2);

			// ////System.out.println(sdf.format(date1));
			// ////System.out.println(sdf.format(date2));

			return date1.compareTo(date2);
		} catch (Exception e) {
			return 0;
		}

	}

	public void setDenghitrackThang(String denghitrackThang) {
		this.denghitraCKTHANG = denghitrackThang;
	}

	public String getDenghitrackThang() {
		return this.denghitraCKTHANG;
	}

	public String getGhiChu() {
		return this.ghichu;
	}

	public void setGhiChu(String ghichu) {
		this.ghichu = ghichu;
	}

	public String getChietkhaucu() {
		return chietkhaucu;
	}

	public void setChietkhaucu(String chietkhaucu) {
		this.chietkhaucu = chietkhaucu;
	}

	public void setIsDonquatang(String IsDonquatang) {

		this.donquatang = IsDonquatang;
	}

	public String getIsDonquatang() {
		return this.donquatang;
	}

	public static String Xoa_Khuyen_Mai(Idbutils db, String dhId, String ctkmId) {
		String query = " delete donhang_ctkm_trakm where donhangid = " + dhId + " and ctkmId = " + ctkmId;
		db.update(query);
		query = " delete DONHANG_CTKM_TRAKM_CHITIET where donhang_fk = " + dhId + " and ctkm_fk = " + ctkmId;
		db.update(query);
		return "";
	}

	public String[][] initDATA(HttpServletRequest request, HttpSession session, Utility util) {
		String[][] action_msg = new String[1][2];

		String action = request.getParameter("action") == null ? "" : request.getParameter("action");
		action_msg[0][0] = action;
		action_msg[0][1] = "";

		userId = util.antiSQLInspection(request.getParameter("userId"));
		this.setUserId(userId);

		String nppId = util.antiSQLInspection(request.getParameter("nppId"));
		if (nppId == null)
			nppId = "";
		this.setNppId(nppId);

		String ctkmDiemId = util.antiSQLInspection(request.getParameter("ctkmDiemId"));
		if (ctkmDiemId == null)
			ctkmDiemId = "";
		this.setCtkmDiemId(ctkmDiemId);

		String tienkmdiem = util.antiSQLInspection(request.getParameter("tienkmdiem"));
		if (tienkmdiem == null)
			tienkmdiem = "";
		this.setTienKmDiem(geso.dms.center.util.Utility.parseDouble(tienkmdiem.replace(",", "")));

		String nvgnId = util.antiSQLInspection(request.getParameter("nvgnId"));
		if (nvgnId == null)
			nvgnId = "";
		this.setnvgnId(nvgnId);
		////System.out.println("nhân viên giao nhận" + nvgnId);

		String gsbhId = util.antiSQLInspection(request.getParameter("gsbhId"));
		if (gsbhId == null)
			gsbhId = "0";
		this.setGsbhId(gsbhId);

		String ngaygd = util.antiSQLInspection(request.getParameter("ngaygiaodich"));
		if (ngaygd == null || ngaygd == "")
			ngaygd = this.getDateTime();
		this.setNgaygiaodich(ngaygd);
		session.setAttribute("ngaydonhang", ngaygd);

		String trangthai = util.antiSQLInspection(request.getParameter("trangthai"));
		if (trangthai == null)
			trangthai = "";
		this.setTrangthai(trangthai);

		String ddkdId = util.antiSQLInspection(request.getParameter("ddkdTen"));
		if (ddkdId == null)
			ddkdId = "";
		this.setDdkdId(ddkdId);
		session.setAttribute("ddkdId", ddkdId);

		String khoId = util.antiSQLInspection(request.getParameter("khoTen"));
		if (khoId == null)
			khoId = "";
		this.setKhoId(khoId);
		session.setAttribute("khoId", this.getKhoId());

		String donhangKhac = util.antiSQLInspection(request.getParameter("donhangKhac"));
		if (donhangKhac == null)
			donhangKhac = "0";
		this.setDonhangKhac(donhangKhac);
		session.setAttribute("donhangKhac", donhangKhac);

		String khId = util.antiSQLInspection(request.getParameter("khId"));
		if (khId == null)
			khId = "";
		this.setKhId(khId);
		session.setAttribute("khId", khId);

		String smartId = util.antiSQLInspection(request.getParameter("smartId"));
		if (smartId == null)
			smartId = "";
		this.setSmartId(smartId);

		String khTen = util.antiSQLInspection(request.getParameter("khTen"));
		if (khTen == null)
			khTen = "";
		this.setKhTen(khTen);

		String ghichu = util.antiSQLInspection(request.getParameter("ghichu"));
		if (ghichu == null || ghichu.length() == 0)
			ghichu = " ";
		this.setGhiChu(ghichu);

		String muctindung = util.antiSQLInspection(request.getParameter("muctindung"));
		if (muctindung == null)
			muctindung = "0";
		this.setMuctindung(muctindung);

		String chietkhau = util.antiSQLInspection(request.getParameter("ChietKhau"));
		if (chietkhau == null)
			chietkhau = "0";
		else
			chietkhau = chietkhau.replaceAll(",", "");
		this.setChietkhau(chietkhau);

		String chietkhauNN = util.antiSQLInspection(request.getParameter("ChietKhaunk"));
		if (chietkhauNN == null)
			chietkhauNN = "0";
		else
			chietkhauNN = chietkhau.replaceAll(",", "");
		this.setChietkhau(chietkhau);

		String chietkhaucu = util.antiSQLInspection(request.getParameter("ChietKhaucu"));
		if (chietkhaucu == null)
			chietkhaucu = "0";
		else
			chietkhaucu = chietkhaucu.replaceAll(",", "");
		this.setChietkhaucu(chietkhaucu);

		String hchid = util.antiSQLInspection(request.getParameter("hchid"));
		if (hchid == null)
			hchid = "0";
		else
			hchid = hchid.replaceAll(",", "");
		this.setHangkh(hchid);

		String tongtientruocVAT = util.antiSQLInspection(request.getParameter("SoTienChuaVAT"));
		if (tongtientruocVAT == null)
			tongtientruocVAT = "0";
		else
			tongtientruocVAT = tongtientruocVAT.replaceAll(",", "");
		this.setTongtientruocVAT(tongtientruocVAT);

		/*
		 * String tongtiensauVAT =
		 * util.antiSQLInspection(request.getParameter("SoTienCoVAT")); if
		 * (tongtiensauVAT == null) tongtiensauVAT = "0"; else tongtiensauVAT =
		 * tongtiensauVAT.replaceAll(",", ""); this.setTongtiensauVAT(tongtiensauVAT);
		 */
		String tongtienDonhang = util.antiSQLInspection(request.getParameter("SoTienSauCKKM"));
		tongtienDonhang = tongtienDonhang.replaceAll(",", "");
		this.setTongtiensauCKKM(Float.parseFloat(tongtienDonhang));

		////System.out.println("____Tong gia tri don hang ben SVL: " + tongtienDonhang + "\n");

		String TienChietKhau = util.antiSQLInspection(request.getParameter("tck"));
		if (TienChietKhau == null)
			TienChietKhau = "0";
		else
			TienChietKhau = TienChietKhau.replaceAll(",", "");
		this.setTongChietKhau(TienChietKhau);

		String VAT = util.antiSQLInspection(request.getParameter("VAT"));
		if (VAT == null)
			VAT = "0"; // OneOne, don gia da co VAT
		else
			VAT = VAT.replaceAll(",", "");
		this.setVAT(VAT);

		String bgstId = util.antiSQLInspection(request.getParameter("bgstId"));
		if (bgstId == null)
			bgstId = "0"; // neu khach hang khong co bang gia sieu thi
		this.setBgstId(bgstId);
		session.setAttribute("bgstId", bgstId);

		String ngayks = util.antiSQLInspection(request.getParameter("ngaykhoaso"));
		if (ngayks == null)
			ngayks = getDateTime();
		this.setNgayKs(ngayks);

		String IsDonHangLe = util.antiSQLInspection(request.getParameter("IsDonHangLe"));
		if (IsDonHangLe == null)
			IsDonHangLe = "0";
		this.setIsDonHangLe(IsDonHangLe);

		String IsSampling = util.antiSQLInspection(request.getParameter("IsSampling"));
		if (IsSampling == null)
			IsSampling = "0";
		this.setIsSampling(IsSampling);

		String donquatang = util.antiSQLInspection(request.getParameter("donquatang"));
		if (donquatang == null)
			donquatang = "0";
		this.setIsDonquatang(donquatang);

		String sotaikhoan = util.antiSQLInspection(request.getParameter("sotaikhoan"));
		if (sotaikhoan == null)
			sotaikhoan = "";
		this.setSotaikhoan(sotaikhoan);

		if (donhangKhac.equals("1")) {
			String ingia = util.antiSQLInspection(request.getParameter("ingia"));
			if (ingia == null)
				ingia = "0";
			this.setIngia(ingia);
		} else {
			this.ingia = "1";
			this.setIngia("1");
		}

		String tiengiamtru = util.antiSQLInspection(request.getParameter("tiengiamtru").replaceAll(",", ""));
		if (tiengiamtru == null)
			tiengiamtru = "0";
		this.setTiengiamtru(tiengiamtru);

		////System.out.println("________________________________in gia _" + ingia);
		geso.dms.center.util.Utility util_kho = new geso.dms.center.util.Utility();
		// String ngayhoadon_=util_kho.getngayhoadon(this.userId,db,ngaygd,this.khId,0);
		String ngayhoadon_ = Utility.getNgayHienTai();
		this.setNgayhoadon(ngayhoadon_);
		String ngaysua = getDateTime();
		this.setNgaysua(ngaysua);

		int donhang_sau_ngay_01 = util.CompareDATE(ngaygd, "2015-01-01");
		{
			String sql = " select top(1) maFAST as smartId, a.pk_seq as khId, a.ten as khTen, isnull( a.PT_CHIETKHAU, 0) as pt_chietkhau, isnull(a.bgst_fk, '0') as bgstId, c.ddkd_fk,( select top(1) GSBH_FK from ddkd_gsbh where DDKD_FK=c.DDKD_FK )  as gsbh_fk , a.diachi,"
					+ " a.xuatkhau, ( select loaiNPP from NHAPHANPHOI where pk_seq = a.npp_fk ) as loaiNPP, isnull(a.chucuahieu, '') as chucuahieu, a.kho_fk, isnull(a.dienthoai,'') khDienThoai, isnull(a.masothue,'') khMaSoThue "
					+ " from khachhang a inner join khachhang_tuyenbh b on a.pk_seq = b.khachhang_fk inner join tuyenbanhang c on b.tbh_fk = c.pk_seq inner join ddkd_gsbh e on c.ddkd_fk = e.ddkd_fk  "
					+ " where a.trangthai = '1' and a.npp_fk = '" + nppId + "' and a.pk_seq = '" + khId
					+ "' and len(dbo.trim('" + smartId + "')) >0 " + " order by maFAST asc";

			////System.out.println("____Lay khach hang: " + sql);

			ResultSet rs = db.get(sql);
			if (rs != null) {
				try {
					if (rs.next()) {
						this.setSmartId(rs.getString("smartId"));
						this.setChucuahieu(rs.getString("chucuahieu"));
						this.setKhId(rs.getString("khId"));
						session.setAttribute("khId", rs.getString("khId"));
						this.setKhTen(rs.getString("khTen") + " - " + rs.getString("diachi"));
						this.setKhDienThoai(rs.getString("khDienThoai"));
						this.setKhMaSoThue(rs.getString("khMaSoThue"));
						String banbuon = rs.getString("xuatkhau");
						String loaiNPP = rs.getString("loaiNPP");

						// this.setChietkhau(rs.getString("pt_chietkhau"));
						// cho sua gia lay tu ck tren dh

						this.setChietkhauNNK(rs.getString("pt_chietkhau"));

						this.setDdkdId(rs.getString("ddkd_fk"));
						session.setAttribute("ddkdId", rs.getString("ddkd_fk"));
						this.setGsbhId(rs.getString("gsbh_fk"));
						/*
						 * String kho_fk = rs.getString("kho_fk") == null ? "" : rs.getString("kho_fk");
						 * if (kho_fk.length() > 0) { session.setAttribute("khoId", this.getKhoId());
						 * this.setKhoId(kho_fk); }
						 */
					}
					// rs.close();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
		////System.out.println("nha phan phoi id" + this.nppId + " chiet khau" + getChietkhau());
		String[] masp = request.getParameterValues("masp");
		String[] tensp = request.getParameterValues("tensp");
		String[] soluong = request.getParameterValues("soluong");
		String[] program = request.getParameterValues("program");

		String[] donvitinh = request.getParameterValues("donvitinh");
		String[] dongia = request.getParameterValues("dongia");
		// String[] dongia1 = request.getParameterValues("dongia1");
		// ////System.out.println("leght"+dongia.length +"---"+dongia1.length);
		/*
		 * if (dongia!=null) { for (int i=0;i<dongia.length;i++) {
		 * ////System.out.println("don gia "+dongia[i] +" don gia 1"+dongia1[i]); } }
		 */
		String[] spchietkhau = request.getParameterValues("spchietkhau1");
		String[] tonkho = request.getParameterValues("tonkho");
		String[] ptVAT = request.getParameterValues("ptVAT");
		long tonggiatriKm = Math.round(Float.parseFloat(tongtientruocVAT));

		List<ISanpham> spList = new ArrayList<ISanpham>();
		if (masp != null) {
			ISanpham sanpham = null;
			String[] param = new String[9];
			int m = 0;
			while (m < masp.length) {
				if (masp[m] != "" && ngaygd.trim().length() > 0 && khId.trim().length() > 0) {
					if (soluong[m].length() > 0) // chi them nhung san phamco so luong > 0
					{
						List<ISpDetail> spDetail = new ArrayList<ISpDetail>();

						String[] solo = request.getParameterValues(m + ".solo");
						String[] ngayhethan = request.getParameterValues(m + ".ngayhethan");
						String[] ngaynhapkho = request.getParameterValues(m + ".ngaynhapkho");
						String[] soluongtonct = request.getParameterValues(m + ".soluongton");
						String[] soluongct = request.getParameterValues(m + ".soluong");
						if (solo != null) {
							for (int j = 0; j < solo.length; j++) {

								ISpDetail splo = new SpDetail();
								splo.setSolo(util.antiSQLInspection(solo[j]));
								splo.setNgayhethan(util.antiSQLInspection(ngayhethan[j]));
								splo.setNgaynhapkho(util.antiSQLInspection(ngaynhapkho[j]));
								splo.setSoluong(util.antiSQLInspection(soluongct[j]));
								splo.setSoluongton(util.antiSQLInspection(soluongtonct[j]));
								if (ngayhoadon_.compareTo(util.antiSQLInspection(ngaynhapkho[j])) >= 0)
									spDetail.add(splo);

							}
						}

						param[0] = "idSP";
						param[1] = util.antiSQLInspection(masp[m]);
						param[2] = util.antiSQLInspection(tensp[m]);
						param[3] = util.antiSQLInspection(soluong[m].replaceAll(",", ""));
						param[4] = util.antiSQLInspection(donvitinh[m]);
						param[5] = util.antiSQLInspection(dongia[m].replaceAll(",", ""));
						////System.out.println("don gia la " + dongia[m]);
						param[6] = ""; // khuyen mai
						param[7] = util.antiSQLInspection(spchietkhau[m].replaceAll(",", ""));
						// param[8] = program[m];

						/*
						 * ////System.out.println("---IsSampling: " + IsSampling + " -- MA: " + masp[m] +
						 * "  -- DON GIA: " + dongia[m].replaceAll(",", ""));
						 */

						if (donhangKhac.equals("0") && donquatang.equals("0")) {
							String query = "select a.ma, a.ten, b.donvi, d.AVAILABLE  as hienhuu,  "
									+ "	isnull(a.PT_VAT,0), "
									+ "					(ISNULL ([dbo].[GiaCkBanLeNppSanPham](" + nppId + "," + khId
									+ ",a.pk_seq,'" + ngaygd + "' ), 0))	\n" + "					    as dongia \n"
									+ "from SANPHAM a inner join DONVIDOLUONG b on a.dvdl_fk = b.pk_seq   "
									+ "	inner join NHAPP_KHO d on a.PK_SEQ = d.SANPHAM_FK "
									+ "where a.pk_seq > 0  and a.MA='" + util.antiSQLInspection(masp[m])
									+ "' and a.DVKD_FK = '100001' and d.NPP_FK = '" + nppId + "' and d.kho_fk = '"
									+ khoId + "' and d.KBH_FK = ( select kbh_fk from khachhang where pk_seq='" + khId
									+ "' ) ";
							////System.out.println("query lay gia ne ::::::::::::::::::::::: :" + query);
							ResultSet rs1 = db.get(query);
							try {
								while (rs1.next()) {
									// param[5]=rs1.getDouble("dongia")+"";
								}
								rs1.close();
							} catch (Exception e1) {
								e1.printStackTrace();
							}

							query = "select   " + "	isnull(a.PT_VAT,0) as VAT, "
									+ " ISNULL ([dbo].[GiaCkBanLeNppSanPham](" + nppId + ",kh.pk_seq ,a.pk_seq,'"
									+ ngaygd + "' ) , 0) as dongia  " + " from SANPHAM a   "
									+ " inner join khachhang kh on kh.pk_seq = " + khId
									+ "where a.pk_seq > 0 and a.MA = '" + util.antiSQLInspection(masp[m]) + "' ";
							////System.out.println("---LAY GIA CUA SP: " + query);
							ResultSet rsGiaSP = db.get(query);
							String dongiaSP = "0";
							try {
								if (donhangKhac.equals("1")) {
									dongiaSP = dongia[m].replaceAll(",", "");
								}

								if (donhangKhac.equals("0")) {
									if (rsGiaSP.next()) {
										// dongiaSP = rsGiaSP.getString("dongia");
										dongiaSP = dongia[m].replaceAll(",", "");
									}
									rsGiaSP.close();
								}
							} catch (Exception e) {
							}

							param[5] = dongiaSP;

						}

						sanpham = new Sanpham(param);
						sanpham.setDongiagoc(param[5]);
						sanpham.setTonhientai(tonkho[m].replaceAll(",", ""));
						sanpham.setSoluong1("1");
						sanpham.setSoluong2("1");
						sanpham.setSoluongThung("1");
						sanpham.setKhoNVBH(ptVAT[m]);
						sanpham.setSpDetailList(spDetail);
						spList.add(sanpham);
					}
				}
				m++;
			}
		}
		this.splist = spList;

		String[] maspkm = request.getParameterValues("maspTrakm");
		String[] slgTraKm = request.getParameterValues("slgTraKm");
		String[] trakmid_ = request.getParameterValues("trakmid_");
		String[] ctkmid_ = request.getParameterValues("ctkmid_");
		List<ISanpham> scheme_sp = new ArrayList<ISanpham>();

		if (maspkm != null) {
			for (int i = 0; i < maspkm.length; i++) {

				if (maspkm[i].length() > 0) {

					ISanpham spkm = new Sanpham();
					////System.out.println("mã sp km " + maspkm[i]);
					spkm.setMasanpham(util.antiSQLInspection(maspkm[i]));
					spkm.setSoluong(util.antiSQLInspection(slgTraKm[i]));
					spkm.setCTKMID(util.antiSQLInspection(ctkmid_[i]));
					spkm.setTRAKMID(util.antiSQLInspection(trakmid_[i]));

					List<ISpDetail> spDetail = new ArrayList<ISpDetail>();

					String[] solo = request.getParameterValues(i + ".solokm");
					String[] ngayhethan = request.getParameterValues(i + ".ngayhethankm");
					String[] ngaynhapkho = request.getParameterValues(i + ".ngaynhapkhokm");
					String[] soluongtonct = request.getParameterValues(i + ".soluongtonkm");
					String[] soluongct = request.getParameterValues(i + ".soluongkm");
					if (solo != null) {
						for (int j = 0; j < solo.length; j++) {

							ISpDetail splo = new SpDetail();
							splo.setSolo(util.antiSQLInspection(solo[j]));
							splo.setNgayhethan(util.antiSQLInspection(ngayhethan[j]));
							splo.setNgaynhapkho(util.antiSQLInspection(ngaynhapkho[j]));
							splo.setSoluong(util.antiSQLInspection(soluongct[j]));
							splo.setSoluongton(util.antiSQLInspection(soluongtonct[j]));
							spDetail.add(splo);

						}
					}
					spkm.setSpDetailList(spDetail);

					scheme_sp.add(spkm);
				}
			}
		}
		this.list_sanphamkm_capnhatlo = scheme_sp;

		// DE NGHI
		String tieuchi = util.antiSQLInspection(request.getParameter("tieuchidenghi"));
		if (tieuchi == null)
			tieuchi = "";
		this.setTieuchi(tieuchi);

		String hinhthuc = util.antiSQLInspection(request.getParameter("hinhthuc"));
		if (hinhthuc == null)
			hinhthuc = "0";

		String chucuahieu = util.antiSQLInspection(request.getParameter("chucuahieu"));
		if (chucuahieu == null)
			chucuahieu = "";
		this.setChucuahieu(chucuahieu);

		String khDienThoai = util.antiSQLInspection(request.getParameter("khDienThoai"));
		if (khDienThoai == null)
			khDienThoai = "";
		this.setKhDienThoai(khDienThoai);

		String khMaSoThue = util.antiSQLInspection(request.getParameter("khMaSoThue"));
		if (khMaSoThue == null)
			khMaSoThue = "";
		this.setKhMaSoThue(khMaSoThue);

		String denghitraCK = util.antiSQLInspection(request.getParameter("denghitraCK"));
		if (denghitraCK == null)
			denghitraCK = "0";
		this.setDenghitrackThang(denghitraCK);

		if (action.equals("submitKh")) {
			String sql = "\n select top(1) isnull(hch.diengiai,'') as hangkh,maFAST as smartId, a.pk_seq as khId, a.ten as khTen, isnull( a.PT_CHIETKHAU, 0) as pt_chietkhau, isnull(a.bgst_fk, '0') as bgstId, c.ddkd_fk,( select top(1) GSBH_FK from ddkd_gsbh where DDKD_FK=c.DDKD_FK )  as gsbh_fk , a.diachi,"
					+ "\n  	a.xuatkhau, ( select loaiNPP from NHAPHANPHOI where pk_seq = a.npp_fk ) as loaiNPP, isnull(a.chucuahieu, '') as chucuahieu, a.kho_fk"
					+ "\n   , isnull(a.dienthoai,'') khDienThoai, isnull(a.masothue,'') khMaSoThue   "
					+ "\n from khachhang a "
					+ "\n inner join khachhang_tuyenbh b on a.pk_seq = b.khachhang_fk inner join tuyenbanhang c on b.tbh_fk = c.pk_seq inner join ddkd_gsbh e on c.ddkd_fk = e.ddkd_fk left join HANGCUAHANG hch on hch.pk_seq=a.HCH_FK  "
					+ "\n where a.trangthai = '1' and a.pk_seq in (select khachhang_fk from khachhang_npp where npp_fk = '"
					+ nppId + "') and a.pk_seq = '" + khId + "' and len(dbo.trim('" + smartId + "')) >0 "
					+ "\n order by maFAST asc";

			////System.out.println("____Lay khach hang: " + sql);
			ResultSet rs = db.get(sql);
			if (rs != null) {
				try {
					if (rs.next()) {
						this.setKhDienThoai(rs.getString("khDienThoai"));
						this.setKhMaSoThue(rs.getString("khMaSoThue"));
						this.setSmartId(rs.getString("smartId"));
						this.setChucuahieu(rs.getString("chucuahieu"));
						this.setKhId(rs.getString("khId"));
						session.setAttribute("khId", rs.getString("khId"));
						this.setKhTen(rs.getString("khTen") + " - " + rs.getString("diachi"));
						this.hangkh = rs.getString("hangkh");
						/*
						 * String banbuon = rs.getString("xuatkhau"); String loaiNPP =
						 * rs.getString("loaiNPP");
						 */
						this.setChietkhauNNK(rs.getString("pt_chietkhau"));
						this.setChietkhau(rs.getString("pt_chietkhau"));
						this.setChietkhauNNK(rs.getString("pt_chietkhau"));
						this.setDdkdId(rs.getString("ddkd_fk"));
						session.setAttribute("ddkdId", rs.getString("ddkd_fk"));
						this.setGsbhId(rs.getString("gsbh_fk"));
						/*
						 * String kho_fk = rs.getString("kho_fk") == null ? "" : rs.getString("kho_fk");
						 * if (kho_fk.length() > 0) { session.setAttribute("khoId", this.getKhoId());
						 * this.setKhoId(kho_fk); }
						 */

						if (id == null || id.trim().length() <= 0) {
							this.createRS();
							this.setSpList(spList);
							this.setEnterKH("1");
							session.setAttribute("dhBean", this);
							return action_msg;
						} else {
							this.init(request);
							this.setSpList(spList);
							this.setSmartId(rs.getString("smartId"));
							this.setKhId(rs.getString("khId"));
							this.setKhTen(rs.getString("khTen") + " - " + rs.getString("diachi"));
							this.setDdkdId(rs.getString("ddkd_fk"));
							this.setGsbhId(rs.getString("gsbh_fk"));
							this.setEnterKH("1");
							session.setAttribute("dhBean", this);
							return action_msg;
						}
					} else {
						if (id == null) {
							this.createRS();
							this.setSpList(spList);
							this.setMessage(
									"Mã khách hàng không đúng, hoặc khách hàng chưa được phân tuyến vui lòng kiểm tra lại...");
							session.setAttribute("dhBean", this);
							return action_msg;
						} else {
							this.init(request);
							this.setSpList(spList);
							this.setMessage(
									"Mã khách hàng không đúng, hoặc khách hàng chưa được phân tuyến vui lòng kiểm tra lại...");
							session.setAttribute("dhBean", this);
							return action_msg;
						}
					}
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
			return action_msg;
		} // submitKh
		else if (action.equals("save_donhang_va_khuyen_mai")) {
			String aaa = request.getParameter("danh_sach_ctkm_Str");
			////System.out.println("danh_sach_ctkm_Str= " + aaa);
			if (aaa == null)
				aaa = "";
			if (aaa.trim().length() > 0)
				aaa = geso.dms.center.util.Utility.giaiMa_json(aaa);
			this.setDanh_sach_ctkm_Str(aaa);

			if (this.danh_sach_ctkm_Str.trim().length() <= 0) {
				this.createRS();
				this.setSpList(this.splist);
				session.setAttribute("dhBean", this);
				if (this.msg.trim().length() == 0) {
					this.msg = "Lỗi không lấy được khuyến mãi";
				}
				return action_msg;
			}
			boolean temp = false;
			boolean isUpdate = false;
			if (id.equals("")) {
				temp = this.CreateDh(request, spList);

			} else {
				isUpdate = true;
				temp = this.UpdateDh(request, spList, action);
			}
			////System.out.println("temp = " + temp);
			////System.out.println("msg = " + this.msg);
			if (!temp) {
				action_msg[0][1] = "1";
				this.createRS();
				this.setSpList(this.splist);
				session.setAttribute("dhBean", this);
				if (this.msg.trim().length() == 0) {
					this.msg = "Lỗi  đơn hàng";
				}
			} else {
				this.setMessage((isUpdate ? "Cập nhật" : "Tạo mới ") + " đơn hàng " + this.id + " thành công!");
				this.init(request);
				session.setAttribute("dhBean", this);
				session.setAttribute("khId", "");
				session.setAttribute("ddkdId", "");
				session.setAttribute("nppId", this.getNppId());
			}
		} else if (action.equals("save")) {
			boolean temp = false;

			if (id.equals("")) {
				temp = this.CreateDh(request, spList);

			} else {
				temp = this.UpdateDh(request, spList, action);
			}
			if (!temp) {
				this.createRS();
				this.setSpList(this.splist);
				session.setAttribute("dhBean", this);
				action_msg[0][1] = "1";
				if (this.msg.trim().length() == 0) {
					this.msg = "Lỗi tạo mới đơn hàng";
				}
			} else {
				String dhId = this.id;
				// RENEW BEAN
				this.resetDATA(this.id);
				this.setUserId(userId);
				this.createRS();
				this.setNgaygiaodich(ngaygd);
				this.setMessage("Số đơn hàng bạn vừa lưu " + dhId);
				session.setAttribute("dhBean", this);
				session.setAttribute("khId", "");
				session.setAttribute("ddkdId", "");
				session.setAttribute("nppId", this.getNppId());
			}

		} // save

		else if (action.equals("chotdonhang")) {
			String msg = this.DuyetDonHang(id, DONHANGUPDATESVL_CLICK, userId, request);
			if (this.kqDuyet.equals("1")) // //RA LAI TRANG TONG
			{
				action_msg[0][1] = "5";
				return action_msg;
			} else {
				this.init(request);
				this.setSpList(spList);
				this.setDdkdId(ddkdId);
				this.setKhId(khId);
				this.setMessage(msg);
				session.setAttribute("dhBean", this);
				action_msg[0][1] = "4";
				return action_msg;
			}

		} // chotdonhang
		
		/*
		 * else if(action.equals("dongbolai")) { String msg =
		 * this.DuyetDonHang_chiDongBo(id, DONHANGUPDATESVL_CLICK, userId, request); if
		 * (this.kqDuyet.equals("1")) // //RA LAI TRANG TONG { action_msg[0][1] = "5";
		 * return action_msg; } else { this.init(request); this.setSpList(spList);
		 * this.setDdkdId(ddkdId); this.setKhId(khId); this.setMessage(msg);
		 * session.setAttribute("dhBean", this); action_msg[0][1] = "4"; return
		 * action_msg; } }
		 */
		

		else if (action.equals("dexuatlo")) {
			this.init(request);
			session.setAttribute("bgstId", this.getBgstId());
			session.setAttribute("khoId", this.getKhoId());
			session.setAttribute("dhBean", this);
			session.setAttribute("donhangKhac", this.getDonhangKhac());
			session.setAttribute("khId", this.getKhId());
			session.setAttribute("nvgnId", this.getnvgnId());
			session.setAttribute("ddkdId", this.getDdkdId());
			session.setAttribute("ngaydonhang", this.getNgaygiaodich());
			session.setAttribute("nppId", this.getNppId());
			return action_msg;
		} // dexuatlo
		
		else if (action.equals("khongapkhuyenmai")) {
			action_msg[0][1] = this.ProcessKHONGAPKHUYENMAI(request, session, util, tonggiatriKm);
			return action_msg;
		} 
		
		else if (action.equals("apkhuyenmai_2")) {
			action_msg[0][1] = this.ProcessKHUYENMAI_2(request, session, util, tonggiatriKm);
			return action_msg;
		} // apkhuyenmai
		else if (action.equals("dieuchinhkm")) {
			String ShowAll = request.getParameter("ShowAll");
			////System.out.println("ShowAll =" + ShowAll);
			if (ShowAll == null) {
				this.setDieuchinh("1");
			} else {
				this.setDieuchinh("0");
			}
			action_msg[0][1] = this.ProcessKHUYENMAI_2(request, session, util, tonggiatriKm);
			return action_msg;

		} else if (action.equals("apkhuyenmai")) {
			action_msg[0][1] = this.ProcessKHUYENMAI(request, session, util, tonggiatriKm);
			return action_msg;
		} // apkhuyenmai
		else {
			String LOAINHAPHANPHOI = request.getParameter("LOAINHAPHANPHOI");
			if (LOAINHAPHANPHOI == null) {
				LOAINHAPHANPHOI = "0";
			}
			this.setLoaiNppId(LOAINHAPHANPHOI);
			this.setUserId(userId);
			this.createRS();
			this.setSpList(spList);
			this.setNgaygiaodich(ngaygd);
			session.setAttribute("ddkdId", ddkdId);
			session.setAttribute("dhBean", this);
			session.setAttribute("ddkdId", ddkdId);
			session.setAttribute("dhBean", this);
		}
		return action_msg;
	}
	
	private String ProcessKHONGAPKHUYENMAI(HttpServletRequest request, HttpSession session, Utility util,
			long tonggiatriKm) {

		this.setAplaikhuyenmai(false);
		this.setIsChuaApkhuyenmai(true);

		if (!geso.dms.center.util.Utility.isValid(this.ngaygiaodich) || !geso.dms.center.util.Utility.isValid(this.khId)
				|| this.splist == null || this.splist.size() <= 0) {
			this.dieuchinh = "0";
			this.msg = "Vui lòng nhập sản phẩm";

			this.createRS();
			this.setSpList(this.splist);
			session.setAttribute("dhBean", this);
			return "6.2";
		}
 
		String data = "", status = "1";
  
		this.danh_sach_ctkm_Str = danh_sach_ctkm_Str;
		if (!this.dieuchinh.equals("1") && status.equals("1")
				&& (danh_sach_ctkm_Str == null || danh_sach_ctkm_Str.length() <= 0)) {
				boolean temp = this.UpdateDh(request, this.splist, "khongapkhuyenmai");
				if (!temp) {
					this.init(request);
					this.setKhId(khId);
					this.setDdkdId(ddkdId);

					session.setAttribute("dhBean", this);
					return "6.2";
				}
				this.msg = "Cập nhật đơn hàng " + this.id + " thành công! Không áp khuyến mãi";
				this.init(request);
				session.setAttribute("dhBean", this);
				return "6.2";
		}
		this.setObj_ap_khuyen_mai(data);

		this.createRS();
		this.setSpList(this.splist);
		session.setAttribute("dhBean", this);
		return "6.2";

	}

	private String ProcessKHUYENMAI_2(HttpServletRequest request, HttpSession session, Utility util,
			long tonggiatriKm) {
		////System.out.println("-- ProcessKHUYENMAI_2");
		this.setAplaikhuyenmai(false);
		this.setIsChuaApkhuyenmai(true);

		String urlParam = "";

		if (!geso.dms.center.util.Utility.isValid(this.ngaygiaodich) || !geso.dms.center.util.Utility.isValid(this.khId)
				|| this.splist == null || this.splist.size() <= 0) {
			this.dieuchinh = "0";
			this.msg = "Vui lòng nhập sản phẩm";

			this.createRS();
			this.setSpList(this.splist);
			session.setAttribute("dhBean", this);

			if (id == null || id.equals(""))
				return "6.1";
			else
				return "6.2";
		}

		for (int i = 0; i < this.splist.size(); i++) {
			if (this.splist.get(i).getMasanpham().length() > 0) {
				urlParam += " { \"ma\":\"" + this.splist.get(i).getMasanpham() + "\", \"soluong\":\""
						+ this.splist.get(i).getSoluong() + "\", \"dongia\":\"" + this.splist.get(i).getDongia()
						+ "\" , \"chietkhau\" :\"" + this.splist.get(i).getChietkhau() + "\" },";
			}
		}
		if (urlParam.length() > 0) {
			urlParam = urlParam.substring(0, urlParam.lastIndexOf(","));
		}
		String chuoiSort = "";
		if (this.dieuchinh.equals("1")) {
			String[] scheme = request.getParameterValues("Scheme");
			String[] SchemeId = request.getParameterValues("SchemeId");
			// String[] soxuatKm = new String[SchemeId.length];
			String trakmstr = "";
			for (int i = 0; i < SchemeId.length; i++) {
				////System.out.println( "SchemeId[" + i + "].trim() = " + SchemeId[i].trim() + " - " + SchemeId[i].trim() + ".trakmid");
				String[] chontrakm = request.getParameterValues(SchemeId[i].trim() + ".chontrakm");
				for (int j = 0; j < chontrakm.length; j++) {
					if (chontrakm[j] != null) {
						// ////System.out.println("scheme : "+ SchemeId[i].trim()+" - chon : "+
						// chontrakm[j]);
						trakmstr += "{\"ctkmId\":\"" + SchemeId[i] + "\",\"trakmId\":\"" + chontrakm[j]
								+ "\",\"scheme\":\"" + scheme[i] + "\", \"stt\":\"" + i + "\", \"chon\":\"1\"},";
					}
				}
			}
			if (trakmstr.length() > 0) {
				trakmstr = trakmstr.substring(0, trakmstr.lastIndexOf(","));
			}
			chuoiSort = "[" + trakmstr + "]";
		}
		this.dieuchinh = this.dieuchinh == null || this.dieuchinh.trim().length() <= 0 ? "0" : this.dieuchinh;

		String idAdd = this.id != null && this.id.trim().length() > 0 ? this.id : "0";
		String dh = " { \"id\":\"" + idAdd + "\", \"khId\":\"" + this.khId + "\", \"nppId\":\"" + this.nppId
				+ "\", \"ngaydh\":\"" + this.ngaygiaodich + "\", \"kbhId\":\"" + this.kbhId + "\", \"khoId\":\""
				+ this.khoId + "\" , \"loai\":\"0\" }";
		urlParam = "sanpham=[" + urlParam + "]&donhang=" + dh + "&dieuchinh=" + this.dieuchinh + "&chuoiSort="
				+ chuoiSort;
		////System.out.println("urlParam : " + urlParam);
		String url = request.getServletContext().getInitParameter("path_apkhuyenmai") + "ApKhuyenMaiKhachHang";
		//String url = "http://localhost:56491/API.asmx/" + "ApKhuyenMaiKhachHang";
		String data = "";

		String status = "0";// kq áp kM // 1 áp đươc 0 là có lỗi
		String msgAKM = "";
		String danh_sach_ctkm_Str = "";
		String dung_khuyen_mai = "";
		String chon_san_pham = "";
		String tra_or = "";
		try {
			data = geso.dms.center.util.Utility.sendPost(url, urlParam);

			JsonObject odata = (JsonObject) new JsonParser().parse(data);
			status = odata.get("status").getAsString();
			msgAKM = odata.get("msg").getAsString();
			danh_sach_ctkm_Str = odata.get("data").getAsString();
			dung_khuyen_mai = odata.get("dung_khuyen_mai").getAsString();
			chon_san_pham = odata.get("chon_san_pham").getAsString();
			tra_or = odata.get("tra_or").getAsString();
		} catch (Exception e1) {
			e1.printStackTrace();
			status = "0";
			msgAKM = " Không thể kết nối tới server.";
		}

		if (status.equals("0")) {
			this.msg = msgAKM;
		}
		////System.out.println("status : " + status + " - msgAKM : " + msgAKM);

		this.danh_sach_ctkm_Str = danh_sach_ctkm_Str;
		if (!this.dieuchinh.equals("1") && status.equals("1")
				&& (danh_sach_ctkm_Str == null || danh_sach_ctkm_Str.length() <= 0)) {
			// Save donhang truoc
			if (id.equals("")) {
				boolean tao = this.CreateDh(request, this.splist);
				if (!tao) {
					this.createRS();
					this.setSpList(this.splist);

					session.setAttribute("dhBean", this);
					// String nextJSP = request.getContextPath() + "/pages/Distributor/DonHangNew.jsp";
					// response.sendRedirect(nextJSP);

					return "6.1";
				} else {
					id = this.getId();
					this.msg = "Tạo mới đơn hàng " + this.id + " thành công! Không có khuyến mãi bình thường";
					this.init(request);
					session.setAttribute("dhBean", this);
					return "6.2";
				}
			} else {
				boolean temp = this.UpdateDh(request, this.splist, "apkhuyenmai");
				if (!temp) {
					this.init(request);
					this.setKhId(khId);
					this.setDdkdId(ddkdId);

					session.setAttribute("dhBean", this);
					// String nextJSP = request.getContextPath() + "/pages/Distributor/DonHangUpdate.jsp";
					// response.sendRedirect(nextJSP);

					return "6.2";
				}
				this.msg = "Cập nhật đơn hàng " + this.id + " thành công! Không có khuyến mãi bình thường";
				this.init(request);
				session.setAttribute("dhBean", this);
				return "6.2";
			}
		}
		this.setObj_ap_khuyen_mai(data);

		this.createRS();
		this.setSpList(this.splist);
		session.setAttribute("dhBean", this);

		if (id.equals(""))
			return "6.1";
		else
			return "6.2";

	}

	String obj_ap_khuyen_mai = "";
	String danh_sach_ctkm_Str = "";
	String dieuchinh = "0";

	public String getDieuchinh() {
		return dieuchinh;
	}

	public void setDieuchinh(String dieuchinh) {
		this.dieuchinh = dieuchinh;
	}

	public String getObj_ap_khuyen_mai() {
		return obj_ap_khuyen_mai;
	}

	public void setObj_ap_khuyen_mai(String obj_ap_khuyen_mai) {
		this.obj_ap_khuyen_mai = obj_ap_khuyen_mai;
	}

	public String getDanh_sach_ctkm_Str() {
		return danh_sach_ctkm_Str;
	}

	public void setDanh_sach_ctkm_Str(String danh_sach_ctkm_Str) {
		this.danh_sach_ctkm_Str = danh_sach_ctkm_Str;
	}

	private String LuuKhuyenMai(Idbutils db, String dhId, HttpServletRequest request, String kbh_fk) {
		String query = "";
		////System.out.println("danh_sach_ctkm_Str ne : " + this.danh_sach_ctkm_Str);
		try {
			if (this.danh_sach_ctkm_Str.trim().length() > 0) {
				JsonArray jsonCTKM = (JsonArray) new JsonParser().parse(this.danh_sach_ctkm_Str);
				for (int i = 0; i < jsonCTKM.size(); i++) {
					JsonObject oCTKM = (JsonObject) jsonCTKM.get(i);
					String ctkmId = oCTKM.get("id").getAsString();
					String scheme = oCTKM.get("scheme").getAsString();
					String sosuat = oCTKM.get("sosuat").getAsString();
					String tra = oCTKM.get("trakhuyenmai").getAsString();
					////System.out.println(tra);
					JsonArray jsonTraKM = (JsonArray) new JsonParser().parse(tra);
					for (int x = 0; x < jsonTraKM.size(); x++) {
						JsonObject oTra = (JsonObject) jsonTraKM.get(x);
						String tkmId = oTra.get("id").getAsString();
						String loaiTKM = oTra.get("loai").getAsString();
						String hinhthucTKM = oTra.get("hinhthuc").getAsString();
						////System.out.println("tkmId : " + tkmId + " - loaiTKM : " + loaiTKM);
						if (loaiTKM.equals("1") || loaiTKM.equals("2")) {

							Object[] data = null;

							String tongtien = oTra.get("tongtien").getAsString();
							data = dbutils.setObject(dhId, tkmId, sosuat, tongtien, ctkmId);
							// lưu tổng tiền
							query = "\n insert DONHANG_CTKM_TRAKM(DONHANGID,ctkmId,TRAKMID,SOXUAT,TONGGIATRI) "
									+ "\n select  ?, ctkm.pk_seq, ?, ?,round(?,0) " + "\n from ctkhuyenmai ctkm "
									+ "\n where ctkm.pk_seq = ? ";

							if (db.update_v2(query, data) <= 0) {
								return "Lỗi lưu tổng tiền.";
							}
						} else if (loaiTKM.equals("3")) // trả sp
						{
							if (hinhthucTKM.equals("1"))// lưu cố định sp
							{
								String spTra = oTra.get("spTra").getAsString();
								JsonArray json_spTra = (JsonArray) new JsonParser().parse(spTra);
								for (int i_trasp = 0; i_trasp < json_spTra.size(); i_trasp++) {
									JsonObject oTraSP = (JsonObject) json_spTra.get(i_trasp);
									String spId = oTraSP.get("spId").getAsString();
									String SpMa = oTraSP.get("MA").getAsString(); // để gửi thông báo ko đủ tồn kho hoặc
																					// cái quằn gì đó
									String sl = oTraSP.get("SOLUONG").getAsString();

									db.ClearParam();
									db.AddParam("@kbh_fk", kbh_fk);
									db.AddParam("@tkmId", tkmId);
									db.AddParam("@sosuat", sosuat);
									db.AddParam("@sl", sl);
									db.AddParam("@ctkmId", ctkmId);
									db.AddParam("@spId", spId);
									db.AddParam("@dhId", dhId);

									query = "\n insert DONHANG_CTKM_TRAKM(kbh_fk,DONHANGID,ctkmId,TRAKMID,SOXUAT,TONGGIATRI,SPMA,SOLUONG,sanpham_fk,kho_fk) "
											+ "\n select  @kbh_fk, dh.pk_seq, ctkm.pk_seq, @tkmId , @sosuat ,0 , sp.ma, @sl , sp.pk_seq,  [dbo].[KhoKhuyenMai_FK](dh.npp_fk,dh.pk_seq ,ctkm.pk_seq, ctkm.scheme  ) "
											+ "\n from ctkhuyenmai ctkm , sanpham sp , donhang dh "
											+ "\n where ctkm.pk_seq = @ctkmId and sp.pk_seq = @spId and dh.pk_seq= @dhId ";
									if (db.update_with_param(query) <= 0) {
										return "Lỗi lưu sản phẩm cố định.";
									}
								}
							} else if (hinhthucTKM.equals("2")) // bất kì trong
							{
								String spTra = oTra.get("spTra").getAsString();
								double tongluong = geso.dms.center.util.Utility
										.parseDouble(oTra.get("tongluong").getAsString())
										* geso.dms.center.util.Utility.parseDouble(sosuat);
								if (tongluong <= 0) {
									return scheme + " không lấy được tổng lượng trả theo bất kì trong ";
								}
								double datra = 0;
								JsonArray json_spTra = (JsonArray) new JsonParser().parse(spTra);
								for (int i_trasp = 0; i_trasp < json_spTra.size(); i_trasp++) {
									JsonObject oTraSP = (JsonObject) json_spTra.get(i_trasp);
									String spId = oTraSP.get("spId").getAsString();
									String SpMa = oTraSP.get("MA").getAsString(); // để gửi thông báo ko đủ tồn kho hoặc
																					// cái quằn gì đó
									String _sl = request.getParameter(ctkmId + "__" + tkmId + "__" + spId);
									////System.out.println("ctkmId__tkmId__spId : " + ctkmId + "__" + tkmId + "__" + spId);
									////System.out.println("_sl : " + _sl);
									if (_sl == null)
										_sl = "";
									_sl = _sl.replace(",", "");
									double sl = geso.dms.center.util.Utility.parseDouble(_sl);
									if (sl > 0) {
										db.ClearParam();
										db.AddParam("@kbh_fk", kbh_fk);
										db.AddParam("@tkmId", tkmId);
										db.AddParam("@sosuat", sosuat);
										db.AddParam("@sl", sl + "");
										db.AddParam("@ctkmId", ctkmId);
										db.AddParam("@spId", spId);
										db.AddParam("@dhId", dhId);

										query = "\n insert DONHANG_CTKM_TRAKM(kbh_fk,DONHANGID,ctkmId,TRAKMID,SOXUAT,TONGGIATRI,SPMA,SOLUONG,sanpham_fk,kho_fk) "
												+ "\n select  @kbh_fk, dh.pk_seq, ctkm.pk_seq, @tkmId , @sosuat ,0 , sp.ma, @sl , sp.pk_seq,  [dbo].[KhoKhuyenMai_FK](dh.npp_fk,dh.pk_seq ,ctkm.pk_seq, ctkm.scheme  ) "
												+ "\n from ctkhuyenmai ctkm , sanpham sp , donhang dh "
												+ "\n where ctkm.pk_seq = @ctkmId and sp.pk_seq = @spId and dh.pk_seq= @dhId ";
										if (db.update_with_param(query) <= 0) {
											return "Lỗi lưu sản phẩm bất kỳ trong";
										}

										datra += sl;
									}
								}
								if (datra != tongluong) {
									return scheme + " tổng lượng nhập (" + datra + ") vượt tổng lượng cho phép ("
											+ tongluong + ") ";
								}
							}
						} else {
							return "Có lỗi trong khi áp khuyến mãi.";
						}
					}

					String dieukienkhuyenmai = oCTKM.get("dieukienkhuyenmai").getAsString();
					JsonArray jsonDKKM = (JsonArray) new JsonParser().parse(dieukienkhuyenmai);
					for (int x = 0; x < jsonDKKM.size(); x++) {
						JsonObject DKKM = (JsonObject) jsonDKKM.get(x);
						String dkkmId = DKKM.get("dkkmId").getAsString();
						JsonArray spDKKM = DKKM.get("spList").getAsJsonArray();
						for (int i_dkkmsp = 0; i_dkkmsp < spDKKM.size(); i_dkkmsp++) {
							JsonObject oDKKMSP = (JsonObject) spDKKM.get(i_dkkmsp);
							String spId = oDKKMSP.get("id").getAsString();
							String sl = oDKKMSP.get("soluong").getAsString();
							String dongia = oDKKMSP.get("dongia").getAsString();
							double slNew = 0;
							double dongiaNew = 0;
							try {
								slNew = Double.parseDouble(sl);
								dongiaNew = Double.parseDouble(dongia);
							} catch (Exception e) {
								return "Lỗi ghi nhận số lượng sử dụng";
							}
							if (slNew > 0) {
								query = "\n insert DONHANG_CTKM_DKKM(donhang_fk,ctkm_fk,dkkm_fk,soluongmua,soxuat ,sanpham_fk,doanhso,soluongdonhang) "
										+ "\n select distinct " + dhId + ", " + ctkmId + ", " + dkkmId + ", " + sl
										+ ",0," + spId + ",  " + slNew + " * giamua  ,soluong "
										+ "\n from donhang_sanpham " + "\n where donhang_fk = " + dhId
										+ " and sanpham_fk= " + spId;
								if (db.updateReturnInt(query) != 1) {
									return "Lỗi lưu sử dụng KM ";

								}
							}

						}
					}

				}

				String kqdexuatlo = dexuatlokm(db, dhId, this.ngaygiaodich);
				return kqdexuatlo;
			} else
				return "";
		} catch (Exception e1) {
			e1.printStackTrace();
			return "Exception:" + e1.getMessage();
		}
	}

	private String ProcessKHUYENMAI(HttpServletRequest request, HttpSession session, Utility util, long tonggiatriKm) {
		this.setAplaikhuyenmai(false);
		this.setIsChuaApkhuyenmai(true);

		// Save donhang truoc
		if (id.equals("")) {
			boolean tao = this.CreateDh(request, this.splist);
			if (!tao) {
				this.createRS();
				this.setSpList(this.splist);
				session.setAttribute("dhBean", this);
				// String nextJSP = request.getContextPath() + "/pages/Distributor/DonHangNew.jsp";
				// response.sendRedirect(nextJSP);

				return "6.1";
			} else {
				id = this.getId();
			}
		} else {
			boolean temp = this.UpdateDh(request, this.splist, "apkhuyenmai");

			if (!temp) {
				this.init(request);
				this.setKhId(khId);
				this.setDdkdId(ddkdId);
				session.setAttribute("dhBean", this);
				// String nextJSP = request.getContextPath() + "/pages/Distributor/DonHangUpdate.jsp";
				// response.sendRedirect(nextJSP);

				return "6.2";
			}
		}

		Hashtable<String, Double> sanpham_soluong = new Hashtable<String, Double>();
		Hashtable<String, Float> sanpham_dongia = new Hashtable<String, Float>();
		Hashtable<String, Float> sanpham_quycach = new Hashtable<String, Float>();

		String sql = "select ( select ma from SANPHAM where pk_seq = a.SANPHAM_FK ) as spMA"
				+ "\n, (1+ b.pt_vat/100) * [dbo].[GiaBanLeNppSanPham](dh.npp_fk,dh.khachhang_fk,a.sanpham_fk,dh.ngaynhap) GIAMUA, sum(a.SOLUONG) as soluong, "
				+ "\n	isnull(( select SOLUONG1 from QUYCACH qc where SANPHAM_FK = a.SANPHAM_FK and DVDL2_FK = '100018' and b.dvdl_fk=qc.dvdl1_fk ), 1) as quycach  "
				+ "\n from DONHANG_SANPHAM a inner join sanpham b on b.pk_seq=a.sanpham_fk "
				+ "\n inner join donhang dh on dh.pk_Seq =   a.donhang_fk  " + "\n where a.DONHANG_FK = '" + id + "' "
				+ "\n group by a.SANPHAM_FK,dh.kbh_fk,dh.npp_fk,dh.ngaynhap,b.DVDL_FK,b.PT_VAT ";
		////System.out.println(" sp dem ap km:" + sql);
		ResultSet rsInfo = db.get(sql);

		String _masp = "";
		String _soluong = "";
		String _dongia = "";
		String _soluong1 = "";

		try {
			while (rsInfo.next()) {
				sanpham_soluong.put(rsInfo.getString("spMA"), (double) rsInfo.getInt("soluong"));
				sanpham_dongia.put(rsInfo.getString("spMA"), rsInfo.getFloat("GIAMUA"));
				sanpham_quycach.put(rsInfo.getString("spMA"), rsInfo.getFloat("quycach"));

				_masp += rsInfo.getString("spMA") + "__";
				_soluong += rsInfo.getString("soluong") + "__";
				_dongia += rsInfo.getString("GIAMUA") + "__";
				_soluong1 += rsInfo.getString("quycach") + "__";
			}
			rsInfo.close();
		} catch (Exception e) {
			e.printStackTrace();
		}

		if (_masp.trim().length() > 0) {
			_masp = _masp.substring(0, _masp.length() - 2);
			_soluong = _soluong.substring(0, _soluong.length() - 2);
			_dongia = _dongia.substring(0, _dongia.length() - 2);
			_soluong1 = _soluong1.substring(0, _soluong1.length() - 2);
		}

		if (!trangthai.equals("3")) {

			String _msg = this.ApTichluy();
			if (_msg.length() > 0) {
				this.setMessage(_msg);
				return "6.2";
			}

			// XLkhuyenmai xlkm = new XLkhuyenmai(userId, ngaygd, khId, IsDonHangLe); --->
			// LẤY TRONG BEAN
			XLkhuyenmai xlkm = this.getXLkhuyenmai(userId, this.ngaygiaodich, khId, id, IsDonHangLe);
			xlkm.setKhachhang(khId);
			xlkm.setDenghitraCK(denghitraCKTHANG);

			xlkm.setMasp(_masp.split("__"));
			xlkm.setSoluong(_soluong.split("__"));
			xlkm.setDongia(_dongia.split("__"));
			xlkm.setQuycach(_soluong1.split("__"));

			xlkm.setIdDonhang(id);
			xlkm.setTonggiatriDh((float) tonggiatriKm);
			xlkm.setNgaygiaodich(this.ngaygiaodich);
			xlkm.setLoaiDonHang(IsDonHangLe);

			xlkm.setHashA(sanpham_soluong);
			xlkm.setHashB(sanpham_dongia);
			xlkm.setHash_QuyCach(sanpham_quycach);

			xlkm.setDieuchinh(false);
			xlkm.ApKhuyenMai();

			List<ICtkhuyenmai> ctkmResual = xlkm.getCtkmResual();
			////System.out.println("+++So xuat khuyen mai duoc huong: " + ctkmResual.size() + "\n");
			xlkm.checkConfirm();

			if (ctkmResual.size() > 0) // bi dung --> sang trang lua chon
			{
				////System.out.println("Bi dung san pham roi...\n");
				session.setAttribute("xlkm", xlkm);
				// String nextJSP = request.getContextPath() + "/pages/Distributor/KhuyenMai.jsp";

				// response.sendRedirect(nextJSP);
				return "6.3";
			}
			String msg = "";
			/*
			 * String msg = ""; // nhung ctkm khong thoa for (int i = 0; i <
			 * ctkmResual.size(); i++) { ICtkhuyenmai ctkhuyenmai = ctkmResual.get(i);
			 * 
			 * // ////System.out.println("ConFi laf: "+ctkhuyenmai.getConfirm()); if
			 * (ctkhuyenmai.getConfirm() == false) { msg +=
			 * this.CreateKhuyenmai(ctkhuyenmai, id, nppId, this.ngaygiaodich, tonggiatriKm,
			 * khId, sanpham_soluong, sanpham_dongia, db);
			 * 
			 * // remove khoi danh sach ctkmResual.remove(i); i = i - 1; } }
			 */

			/***********************************
			 * Them tich luy
			 **********************************/
			// -- chuc nang chen ngang chiet khau neu da su dung chuc nang nay thi ko ap ck
			// nua
			String sql1 = "select ( select count(donhang_fk) from  DUYETTRAKHUYENMAI_DONHANG_LOG  where donhang_fk='"
					+ id + "' and tichluyQUY=0 ) as sl1,  "
					+ "		  ( select count(donhang_fk) from  DUYETTRAKHUYENMAI_DONHANG_LOG  where donhang_fk='" + id
					+ "' and tichluyQUY=1 ) as sl2	";

			////System.out.println("sql--------" + sql1);
			ResultSet rscount = db.get(sql1);
			int slcount = 0;
			int slcount2 = 0;
			try {
				if (rscount != null) {
					if (rscount.next()) {
						slcount = rscount.getInt("sl1");
						slcount2 = rscount.getInt("sl2");
					}
					rscount.close();
				}
			} catch (Exception e) {
				e.printStackTrace();
			}

			/*
			 * if (slcount < 1) this.ApTichLuy();
			 * 
			 * if (slcount2 < 1 ) this.ApTichLuy_Quy();
			 */

			this.msg = util.Update_GiaTri_DonHang(id, db);

			if (msg.length() > 0) {
				this.init(request);
				this.setSpList(this.splist);
				this.setKhId(khId);
				this.setDdkdId(ddkdId);

				this.setAplaikhuyenmai(false);
				xlkm.DBclose();
				this.setMessage("khong the them moi 'donhang_ctkm_trakm' " + msg);
				session.setAttribute("dhBean", this);
				// String nextJSP = request.getContextPath() + "/pages/Distributor/DonHangUpdate.jsp";
				// response.sendRedirect(nextJSP);

				return "6.2";
			}

			String nextJSP = "";
			if (ctkmResual.size() > 0) {
				session.setAttribute("xlkm", xlkm);
				nextJSP = "/TW3/pages/Distributor/KhuyenMai.jsp";
				// response.sendRedirect(nextJSP);
				return "6.3";
			} else {
				xlkm.DBclose();
				this.init(request);
				this.setAplaikhuyenmai(false);
				this.setKhId(khId);
				this.setDdkdId(ddkdId);
				session.setAttribute("dhBean", this);
				// nextJSP = request.getContextPath() + "/pages/Distributor/DonHangUpdate.jsp";
				// response.sendRedirect(nextJSP);
				return "6.2";
			}
		} else // ap lai khuyen mai cho don hang da xuat kho
		{
			/* ////System.out.println("____Ap lai khuyen mai cho don hang DXK..."); */
			String[] ctkm = request.getParameterValues("trakmSpScheme"); // chi duoc nhung CTKK cu

			String msg = "";
			if (ctkm != null) {
				/* ////System.out.println("1.So CTKM DXK: " + ctkm.length); */
				if (ctkm.length > 0) {
					// XLkhuyenmaiDonhangDXK xlkm = new XLkhuyenmaiDonhangDXK( nppId, id, ngaygd,
					// ctkm, khId);
					XLkhuyenmaiDonhangDXK xlkm = this.getXLkhuyenmaiDXK(nppId, id, this.ngaygiaodich, ctkm, khId);

					xlkm.setTonggiatriDh((float) tonggiatriKm);
					xlkm.setNgaygiaodich(this.ngaygiaodich);

					xlkm.setHashA(sanpham_soluong);
					xlkm.setHashB(sanpham_dongia);
					xlkm.setHash_QuyCach(sanpham_quycach);
					xlkm.setDieuchinh(true);

					xlkm.ApKhuyenMai();

					xlkm.checkConfirm();
					List<ICtkhuyenmai> ctkmResual = xlkm.getCtkmResual();

					/* ////System.out.println("2.So CTKM DXK Sau Do: " + ctkmResual.size()); */

					boolean config = false;
					for (int i = 0; i < ctkmResual.size(); i++) {
						if (ctkmResual.get(i).getConfirm() == true) {
							config = true;
							break;
						}
					}

					if (!config) {
						msg = this.CreateKhuyenmaiDhDxk(ctkmResual, id, nppId, tonggiatriKm, khId, db);
					} else {
						try {
							db.getConnection().setAutoCommit(false);
							String msg1 = this.capNhatKM(id, nppId, khId, trangthai, db);

							if (msg1.length() > 0) {
								/* ////System.out.println(msg1); */
								db.getConnection().rollback();
								return "6.2";
							} else {
								db.getConnection().commit();
								db.getConnection().setAutoCommit(true);

								xlkm.setNppTen(this.getNppTen());
								session.setAttribute("xlkm", xlkm);
								// String nextJSP = request.getContextPath() + "/pages/Distributor/KhuyenMaiDxk.jsp";
								// response.sendRedirect(nextJSP);
								return "6.4";
							}
						} catch (Exception e) {
							e.printStackTrace();
							try {
								db.getConnection().rollback();
								db.getConnection().setAutoCommit(true);
							} catch (Exception e1) {
							}
						}
					}
					xlkm.DBclose();
				}
			}

			this.init(request);
			this.setMessage(msg);
			this.setKhId(khId);
			this.setDdkdId(ddkdId);
			this.createPxkId();

			try {
				db.getConnection().setAutoCommit(false);

				String error = this.createPth(this.getPxkId(), db);
				if (error.trim().length() <= 0)
					db.getConnection().commit();
				else
					db.getConnection().rollback();

				this.setMessage(error);
			} catch (Exception e) {
			}

			this.setAplaikhuyenmai(false);

			/* ////System.out.println("Ao lai khuyen mai la: " + dhBean.isAplaikhuyenmai()); */

			session.setAttribute("dhBean", this);
			// response.sendRedirect(request.getContextPath() + "/pages/Distributor/DonHangUpdate.jsp");
			return "6.2";
		}
	}

	private void resetDATA(String id) {
		this.id = id;
		this.khId = "";
		this.hangkh = "";
		this.khDienThoai = "";
		this.ngaygiaodich = "";
		this.nppTen = "";
		this.daidienkd = "";
		this.trangthai = "";
		this.ngaytao = "";
		this.nguoitao = "";
		this.ngaysua = "";
		this.nguoisua = "";
		this.VAT = "";
		this.ddkdId = "";
		this.gsbhId = "";
		this.tbhId = "";
		this.chietkhau = "";
		this.tongchietkhau = "";
		this.tongtiensauVAT = "0";
		this.tongtientruocVAT = "0";
		this.ttsauCKKM = 0;
		this.ttCKKM = 0;
		this.ttsauCK = 0;
		this.bgstId = "0";
		this.khoId = "";
		this.msg = "";
		this.khTen = "";
		this.smartId = "";
		this.muctindung = "0";
		this.aplaikm = false;
		this.cokm = false;
		this.chuaApkm = false;
		this.dacoDh = false;
		this.daxuatkhoChuachot = false;
		this.aplaitb = false;
		this.splist = new ArrayList<ISanpham>();
		this.spThieuList = new Hashtable<String, Integer>();
		this.ngayks = "";
		this.coTrungBay = "";
		this.IsDonHangLe = "0";
		this.IsSampling = "0";
		this.enterKH = "0";
		this.ghichu = "";
		this.chietkhau_ids = "";
		this.chietkhau_giatri = new Hashtable<String, Float>();
		this.chietkhau_vat = "";
		this.tieuchiID = "";

		this.dstXanh = "";
		this.dstHHBG = "";
		this.dstKHAC = "";
		this.dstXanh_Denghi = "";
		this.dstHHBG_Denghi = "";

		this.donhangKhac = "0";
		this.chucuahieu = "";
		this.nvgnId = "";
		this.denghitraCKTHANG = "0";
		this.donquatang = "0";

	}

	public void Update_GiaTri_DonHang() {
		Utility util = new Utility();
		this.msg = util.Update_GiaTri_DonHang(this.id, this.db);
	}

	public void setSotaikhoan(String sotaikhoan) {

		this.sotaikhoan = sotaikhoan;
	}

	public String getSotaikhoan() {

		return this.sotaikhoan;
	}

	public String getChietkhauNNK() {
		return chietkhauNNK;
	}

	public void setChietkhauNNK(String chietkhauNNK) {
		this.chietkhauNNK = chietkhauNNK;
	}

	public String getHangkh() {
		return hangkh;
	}

	public void setHangkh(String hangkh) {
		this.hangkh = hangkh;
	}

	public ResultSet getRshangkh() {
		return rshangkh;
	}

	public void setRshangkh(ResultSet rshangkh) {
		this.rshangkh = rshangkh;
	}

	private String check_solo_tong_va_chitiet(List<ISanpham> splist, String tag) {

		////System.out.println("vak dkday : " + splist.size());
		if (splist != null)
			for (int m = 0; m < splist.size(); m++) {
				ISanpham sp = splist.get(m);
				List<ISpDetail> spDetail = sp.getSpDetailList();
				double totalsoluong = 0;
				for (int i = 0; i < spDetail.size(); i++) {
					ISpDetail splo = spDetail.get(i);
					double soluong = 0;

					try {
						soluong = Double.parseDouble(splo.getSoluong());
					} catch (Exception er) {
					}
					totalsoluong += soluong;

				}

				if (totalsoluong != Double.parseDouble(sp.getSoluong().replace(",", ""))) {
					return tag
							+ "Vui lòng kiểm tra số lượng trong lô chi tiết (phải bằng số lượng tổng xuất) của sản phẩm : "
							+ sp.getMasanpham();
				}
			}

		return "";
	}
	
	public String InsertDonhang_SanPham_ChiTiet(List<ISanpham> splist, Idbutils db, String dhId, String nppId,
			String NgayNghiepVu, String ngayhoadon,String kbh_fk) {

		String query = "";
		try {
			geso.dms.center.util.Utility util_kho = new geso.dms.center.util.Utility();

			for (int m = 0; m < splist.size(); m++) {
				ISanpham sp = splist.get(m);
				List<ISpDetail> spDetail = sp.getSpDetailList();
				if (spDetail != null) {
					for (int i = 0; i < spDetail.size(); i++) {
						ISpDetail splo = spDetail.get(i);
						double soluong = 0;

						if (splo.getSoluong().trim().length() > 0 && Double.parseDouble(splo.getSoluong()) > 0) {
							Object[] data = null; 
							data = dbutils.setObject(dhId,kbh_fk,dhId, splo.getSolo(),
									splo.getNgayhethan(), splo.getNgaynhapkho(), splo.getSoluong(),nppId,sp.getMasanpham());
							query = "insert into  DONHANG_SANPHAM_chitiet (donhang_fk,sanpham_fk,kbh_fk,kho_fk,solo,ngayhethan,ngaynhapkho,soluong,npp_fk)"
									+ " select ?,pK_seq,?,(select kho_fk from donhang where pk_seq=?),?, ?,?,?,?  from sanpham where ma=?";

							if (((dbutils) db).update_v2(query, data) != 1) {
								return "Lỗi: Không thể thực hiện cập nhật chi tiết lô của sản phẩm:"
										+ sp.getMasanpham()
										+ ".Vui lòng thử lại hoặc báo Admin để được trợ giúp. Command Error: "
										+ query;

							}
							/*
							 * query =
							 * "\n select kho.NPP_FK,kho.KBH_FK,kho.kho_fk ,kho.SanPham_FK,SoLo,Ngayhethan,ngaynhapkho "
							 * + "\n from nhapp_kho_chitiet kho " +
							 * "\n inner join sanpham sp on kho.sanpham_fk = sp.pk_seq  " +
							 * "\n inner join donhang dh on dh.pk_seq =  ? " +
							 * "\n inner join nhaphanphoi npp on npp.pk_seq = dh.npp_fk  " +
							 * "\n where kho.NPP_FK = ? and kho.KBH_FK = case npp.DUNGCHUNGKENH  when 1 then 100025 else dh.KBH_FK end  and kho.kho_fk =dh.KHO_FK "
							 * +
							 * "\n		and sp.ma = ? and kho.Solo = ? and kho.Ngayhethan = ?  and kho.Ngaynhapkho = ?  "
							 * + "\n		and kho.ngaynhapkho <= ? ";
							 * 
							 * System.out.println("querry == " + query); ResultSet rs = ((dbutils)
							 * db).get_v2(query, data);
							 * 
							 * if (rs.next()) {
							 */
							/*
							 * 
							 * String _nppId = rs.getString("NPP_FK"); String _kbhId =
							 * rs.getString("KBH_FK"); String _khoId = rs.getString("kho_fk"); String _spId
							 * = rs.getString("SanPham_FK"); String _soLo = rs.getString("SoLo"); String
							 * _Ngayhethan = rs.getString("Ngayhethan"); String _ngaynhapkho =
							 * rs.getString("ngaynhapkho"); try { soluong =
							 * Double.parseDouble(splo.getSoluong()); } catch (Exception er) { }
							 * 
							 * data = null; data = dbutils.setObject(dhId, _spId, _kbhId, _khoId, _soLo,
							 * _Ngayhethan, _ngaynhapkho, soluong, _nppId);
							 * 
							 * query =
							 * "insert into  DONHANG_SANPHAM_chitiet (donhang_fk,sanpham_fk,kbh_fk,kho_fk,solo,ngayhethan,ngaynhapkho,soluong,npp_fk)"
							 * + " select ?, ? , ?,?, ?,?,?,?,? ";
							 * 
							 * if (((dbutils) db).update_v2(query, data) != 1) { return
							 * "Lỗi: Không thể thực hiện cập nhật chi tiết lô của sản phẩm:" +
							 * sp.getMasanpham() +
							 * ".Vui lòng thử lại hoặc báo Admin để được trợ giúp. Command Error: " + query;
							 * 
							 * }
							 * 
							 * 
							 * String msg1= util_kho.Update_NPP_Kho_Sp_Chitiet(NgayNghiepVu,
							 * "Tao moi DH_"+dhId, db, _khoId, _spId, _nppId, _kbhId, _soLo , _Ngayhethan,
							 * _ngaynhapkho, 0, soluong, (-1)*soluong, (-1)*soluong, 0);
							 * 
							 * if (msg1.length() > 0){ return msg1;
							 * 
							 * }
							 * 
							 * } else {
							 * 
							 * return "Không tồn tại kho theo (SP:" + sp.getMasanpham() + ",Lô:" +
							 * splo.getSolo() + "," + splo.getNgayhethan() + " ) trước ngày (" +
							 * NgayNghiepVu + ") "; }
							 */
						}

					}
				} else {
					/*
					 * //de xuat lo query =
					 * "\n select 0 trakmID,0 CTKMID,1 loai,c.kho_fk as khoId, " +
					 * //"c.kbh_fk as kbhId, " +
					 * " case npp.DUNGCHUNGKENH  when 1 then 100025 else c.kbh_fk end kbhId, " +
					 * " b.pk_seq as spId, b.ten as spTEN, sum(a.soluong) as soluong " +
					 * "\n from donhang_sanpham a inner join sanpham b on a.sanpham_fk = b.pk_seq "
					 * + " inner join donhang c on a.donhang_fk = c.pk_seq   " +
					 * "\n inner join nhaphanphoi npp on npp.PK_SEQ = c.npp_fk  " +
					 * "\n where c.trangthai != 2 and a.donhang_fk in ( " + dhId +
					 * " )  and a.sanpham_fk=(select pk_Seq from sanpham where MA='"+sp.getMasanpham
					 * ()+"') " +
					 * "\n group by c.kho_fk, c.kbh_fk, b.pk_seq, b.ten , npp.DUNGCHUNGKENH " ;
					 * "\n union all" + "\n select a.trakmID,a.CTKMID,2,a.kho_fk as khoId, " +
					 * " case npp.DUNGCHUNGKENH  when 1 then 100025 else c.kbh_fk end kbhId, " +
					 * " b.pk_seq as spId, b.ten as spTEN, sum(a.soluong) as soluong  " +
					 * "\n from DONHANG_CTKM_TRAKM a inner join sanpham b on a.SPMA = b.ma " +
					 * " inner join donhang c on a.DONHANGID = c.pk_seq " +
					 * "\n inner join nhaphanphoi npp on npp.PK_SEQ = c.npp_fk  " +
					 * "\n inner join CTKHUYENMAI ctkm on ctkm.PK_SEQ = a.CTKMID " +
					 * "\n where c.trangthai != 2 and a.DONHANGID in ( " + dhId + " )" +
					 * "\n group by a.trakmID,a.CTKMID,a.kho_fk, c.kbh_fk, b.pk_seq, b.ten , npp.DUNGCHUNGKENH  "
					 * ; System.out.println("de xuat lo sanpham "+sp.getMasanpham() +"\n "+query);
					 * ResultSet rsKHO = db.get(query); while (rsKHO.next()) { String khoId =
					 * rsKHO.getString("khoId"); String kbhId = rsKHO.getString("kbhId"); String
					 * spId = rsKHO.getString("spId"); String trakmID = rsKHO.getString("trakmID");
					 * String CTKMID = rsKHO.getString("CTKMID"); String spTEN =
					 * rsKHO.getString("spTEN"); double soluong = rsKHO.getDouble("soluong"); int
					 * isnhapkhau = 1; String loai = rsKHO.getString("loai");
					 * 
					 * //TU DE XUAT LO --> KHO CHI TIET THI VAN TRU SO LUONG + AVAI query =
					 * "\n SELECT case when kho.AVAILABLE-isnull(dh.soluong,0)-isnull(dhkm.soluong,0) > 0 then 0 else 1 end loai,kho.soluong,AVAILABLE-isnull(dh.soluong,0)-isnull(dhkm.soluong,0) available, kho.SOLO, kho.NGAYHETHAN ,KHO.ngaynhapkho  "
					 * + "\n FROM NHAPP_KHO_CHITIET  KHO   " +
					 * "\n inner join donhang dhx on dhx.pk_seq = " +dhId+
					 * "\n inner join Nhaphanphoi npp on npp.pk_seq = kho.npp_fk " +
					 * "\n INNER JOIN SANPHAM SP ON SP.PK_SEQ=KHO.SANPHAM_FK  " +
					 * "\n left join  donhang_sanpham_chitiet dh on dh.sanpham_fk = KHO.sanpham_fk and dh.npp_fk = kho.npp_fk and dh.kbh_fk = kho.kbh_fk and kho.kho_fk = dh.kho_fk and dh.ngayhethan = kho.ngayhethan and dh.ngaynhapkho = kho.ngaynhapkho and  dh.solo = kho.solo and dh.donhang_fk ="
					 * +dhId+
					 * "\n left join  (select sum(soluong) as soluong,kho_fk,donhang_fk,sanpham_fk,solo,ngayhethan,ngaynhapkho from donhang_ctkm_trakm_chitiet "
					 * + "\n			where donhang_fk = "
					 * +dhId+" group by donhang_fk,sanpham_fk,solo,ngayhethan,ngaynhapkho,kho_fk) dhkm "
					 * +
					 * "\n 				on dhx.kbh_fk = kho.kbh_fk and dhx.npp_fk = kho.npp_fk and dhkm.sanpham_fk = KHO.sanpham_fk and (select npp_fk from donhang  where pk_Seq = dhkm.donhang_fk) = kho.npp_fk and (select kbh_fk from donhang where pk_seq = dhkm.donhang_fk) = kho.kbh_fk and kho.kho_fk = dhkm.kho_fk and dhkm.ngayhethan = kho.ngayhethan and dhkm.ngaynhapkho = kho.ngaynhapkho and  dhkm.solo = kho.solo and dhkm.donhang_fk ="
					 * +dhId+
					 * "\n WHERE  kho.AVAILABLE-isnull(dh.soluong,0)-isnull(dhkm.soluong,0) > 0 and KHO.KHO_FK = "
					 * +khoId+"  " + "\n AND KHO.NPP_FK = "+nppId+" " +
					 * "\n	and KHO.KBH_FK = case npp.DUNGCHUNGKENH  when 1 then 100025 else (select kbh_fk from khachhang where pk_Seq = "
					 * +khId+") end  " + "\n 	and SP.pk_seq="+spId+"  and kho.ngaynhapkho <='"+
					 * geso.dms.center.util.Utility.getNgayHienTai() +"'  " + //ngayhoadon_
					 * "\n ORDER BY  loai asc,NGAYHETHAN asc, ngaynhapkho asc  " ;
					 * System.out.println(" chitiet:"+ query); ResultSet rs = db.get(query);
					 * 
					 * String NgayHetHan = ""; double tongluongxuatCT = 0; //PHAI BAT BUOC TONG
					 * LUONG XUAT O KHO CHI TIET PHAI BANG TONG LUONG XUAT O KHO TONG
					 * 
					 * double totalLUONG = 0; boolean exit = false; while (rs.next()) { NgayHetHan =
					 * rs.getString("NGAYHETHAN"); String ngaynhapkho = rs.getString("ngaynhapkho");
					 * double avai = rs.getDouble("AVAILABLE"); totalLUONG += avai; double
					 * soluongXUAT = 0;
					 * 
					 * if (totalLUONG <= soluong && totalLUONG > 0) { soluongXUAT = avai; } else {
					 * soluongXUAT = soluong - (totalLUONG - avai); exit = true; }
					 * 
					 * 
					 * if (soluongXUAT > 0) { String _solo = rs.getString("SOLO");
					 * 
					 * String msg = Update_NPP_Kho_Sp_Chitiet(ngaynhap, dhId, "Ðon hàng PDA", db,
					 * khoId, spId, nppId, kbhId, _solo, NgayHetHan, ngaynhapkho, 0, soluongXUAT,
					 * (-1) * soluongXUAT, (-1) * soluongXUAT, 0);
					 * 
					 * if (msg.Trim().Length > 0) { return msg; } if (loai.equals("1")) query =
					 * " insert into  DONHANG_SANPHAM_chitiet (donhang_fk,sanpham_fk,kbh_fk,kho_fk,solo,ngayhethan,ngaynhapkho,soluong,trangthai,npp_fk)"
					 * + " select " + dhId + ", '" + spId + "', '" + kbhId + "','" + khoId + "', '"
					 * + _solo + "','" + NgayHetHan + "','" + ngaynhapkho + "','" + soluongXUAT +
					 * "',0,'" + nppId + "' "; else query =
					 * " insert into  DONHANG_CTKM_TRAKM_CHITIET (KBH_FK,kho_fk,donhang_fk,sanpham_fk,solo,ngayhethan,ngaynhapkho,soluong,CTKM_FK,TRAKM_FK) "
					 * + " select '" + kbhId + "',"+khoId+"," + dhId + ", " + spId + " ,  '" + _solo
					 * + "' ,'" + NgayHetHan + "' , '" + ngaynhapkho + "', '" + soluongXUAT + "'," +
					 * CTKMID + "," + trakmID; if (this.db.updateReturnInt(query) != 1) { return
					 * "Lỗi đề xuất lô!!"; }
					 * 
					 * tongluongxuatCT += soluongXUAT; if (exit) //DA XUAT DU { break; } } }
					 * 
					 * //if (tongluongxuatCT != soluong) if (tongluongxuatCT != soluong ) { String
					 * addStr = ""; if (loai.equals("1")) addStr = " Sp hàng bán ("+spTEN+")"; else
					 * addStr = " Sp KM ("+spTEN+")";
					 * 
					 * rsKHO.close(); this.msg =addStr
					 * +": số lượng đề xuất trong lô chi tiết theo ngày không còn đủ, vui lòng kiểm tra xuất nhập tồn theo lô để biết thêm chi tiết"
					 * ;
					 * 
					 * return this.msg;
					 * 
					 * } }
					 */}

			}

			return "";
		} catch (Exception e) {
			e.printStackTrace();
			return "Lỗi:" + e.getMessage();
		}
	}
	
	

	public String UpdateDonhang_CTKM_TraKM_ChiTiet(List<ISanpham> splist, Idbutils db, String dhId, String nppId,
			String NgayNghiepVu, String tag, String ngaynhapkho) {

		String query = "";
		try {
			geso.dms.center.util.Utility util_kho = new geso.dms.center.util.Utility();

			// revert book và xóa đi để chèn lại

			 
			
			query = "\n SELECT DH.NPP_FK,   DHCT.KHO_FK,DHCT.sanpham_fk AS SPID, "
					+ " case npp.DUNGCHUNGKENH  when 1 then 100025 else dh.KBH_FK end as kbh_fk, " +
					// "DH.KBH_FK ," +
					" DHCT.SOLO,DHCT.NGAYHETHAN,DHCT.NGAYNHAPKHO " + "\n  , DHCT.SOLUONG  SOLUONG "
					+ "\n  FROM DONHANG_CTKM_TRAKM_CHITIET  DHCT "
					+ "\n  INNER JOIN CTKHUYENMAI KM ON KM.PK_SEQ=DHCT.ctkm_fk "
					+ "\n  INNER JOIN DONHANG DH ON DH.PK_SEQ=DHCT.DONHANG_fk "
					+ "\n inner join nhaphanphoi npp on npp.pk_seq = dh.npp_fk  " + "\n  WHERE DHCT.donhang_fk= "
					+ dhId;

			ResultSet rssp = db.get(query);
			while (rssp.next()) {
				String _spid = rssp.getString("SPID");
				String _nppid = rssp.getString("NPP_FK");
				String _khoid = rssp.getString("KHO_FK");
				String _kbhid = rssp.getString("KBH_FK");
				double _SOLUONG = rssp.getDouble("SOLUONG");
				String _solo = rssp.getString("solo");
				String _ngaynhapkho = rssp.getString("ngaynhapkho");
				String _ngayhethan = rssp.getString("ngayhethan");
					
				  String msg1= util_kho.Update_NPP_Kho_Sp (this.ngaygiaodich,
							 "9448. donhang.java Cập nhật đơn hàng___" +dhId , db, _khoid, _spid, _nppid,
							  _kbhid,  0 ,(-1)* _SOLUONG, _SOLUONG,
							  0); // giảm booked, tăng avail lại
							 
							  if (msg1.length() > 0){ db.getConnection().rollback(); return "Error :" +
							   msg1; }
							  
							  
				 
				    msg1= util_kho.Update_NPP_Kho_Sp_Chitiet(this.ngaygiaodich,
				 "9448. donhang.java Cập nhật đơn hàng___" +dhId , db, _khoid, _spid, _nppid,
				  _kbhid, _solo, _ngayhethan, _ngaynhapkho, 0 ,(-1)* _SOLUONG, _SOLUONG,
				  0, 0); // giảm booked, tăng avail lại
				 
				  if (msg1.length() > 0){ db.getConnection().rollback(); return "Error :" +
				   msg1; }
			 
			}
			rssp.close();

			// delete neu ton tai, cap nhat lai kho voi so luong tang

			query = "delete from DONHANG_CTKM_TRAKM_CHITIET where DONHANG_FK = '" + dhId + "'  ";

			////System.out.println(query);
			if (!db.update(query)) {
				return tag + "Error :" + query;
			}

			////System.out.println("list_sanphamkm_capnhatlo != null:" + list_sanphamkm_capnhatlo != null ? true : false);
			// cập nhật những sản phẩm khuyến mãi có thay đổi số lô
			if (list_sanphamkm_capnhatlo != null) {

				for (int m = 0; m < this.list_sanphamkm_capnhatlo.size(); m++)

				{
					ISanpham sp = list_sanphamkm_capnhatlo.get(m);

					List<ISpDetail> spDetail = sp.getSpDetailList();

					double soluongtongct = 0;
					for (int i = 0; i < spDetail.size(); i++) {
						ISpDetail splo = spDetail.get(i);
						double soluong = 0;

						try {
							soluong = Double.parseDouble(splo.getSoluong());
						} catch (Exception er) {
						}
						soluongtongct += soluong;
						if (soluong > 0) {
							Object[] data = null;
							data = ((dbutils) db).setObject(dhId, nppId, nppId, dhId, sp.getCTKMID(), sp.getMasanpham(),
									splo.getSolo(), splo.getNgayhethan(), splo.getNgaynhapkho(), ngayhoadon);
							query = "\n select kho.NPP_FK,kho.KBH_FK,kho.kho_fk ,kho.SanPham_FK,SoLo,Ngayhethan,ngaynhapkho "
									+ "\n from nhapp_kho_chitiet kho "
									+ "\n inner join sanpham sp on kho.sanpham_fk = sp.pk_seq  "
									+ "\n inner join donhang dh on dh.pk_seq =  ? "
									+ "\n inner join nhaphanphoi npp on npp.pk_seq = dh.npp_fk  "
									+ "\n where kho.NPP_FK = ? and kho.KBH_FK = case npp.DUNGCHUNGKENH  when 1 then 100025 else dh.KBH_FK end  and kho.kho_fk =([dbo].[KhoKhuyenMai_FK](?,? ,?,null )) "
									+ "\n		and sp.ma = ? and kho.Solo = ?  and kho.Ngayhethan = ?  and kho.Ngaynhapkho = ?  "
									+ "\n		and kho.ngaynhapkho <= ? ";
							////System.out.println("querry == " + query);
							ResultSet rs = ((dbutils) db).get_v2(query, data);
							if (rs.next()) {

								String _nppId = rs.getString("NPP_FK");
								String _kbhId = rs.getString("KBH_FK");
								String _khoId = rs.getString("kho_fk");
								String _spId = rs.getString("SanPham_FK");
								String _soLo = rs.getString("SoLo");
								String _Ngayhethan = rs.getString("Ngayhethan");
								String _ngaynhapkho = rs.getString("ngaynhapkho");

								data = ((dbutils) db).setObject(dhId, sp.getCTKMID(), sp.getTRAKMID(), splo.getSolo(),
										splo.getNgaynhapkho(), splo.getNgayhethan(), soluong, nppId, dhId,
										sp.getCTKMID(), sp.getMasanpham());
								query = "\n Insert into donhang_ctkm_trakm_chitiet(donhang_fk , ctkm_fk, trakm_fk, sanpham_fk,solo,ngaynhapkho,ngayhethan, soluong,kho_fk) "
										+ "\n SELECT ?,?,?,PK_SEQ ,?,?,?,?,[dbo].[KhoKhuyenMai_FK](?,? ,?,null ) FROM SANPHAM WHERE MA=?    ";
								if (((dbutils) db).update_v2(query, data) != 1) {
									return tag + "Không thể thực hiện cập nhật chi tiết lô của sản phẩm:"
											+ sp.getMasanpham()
											+ ".Vui lòng báo Admin để được trợ giúp. Command Error: " + query;
								}
								////System.out.println("thanh cong roi nha  == " + query);
								 
								  String msg1=util_kho.Update_NPP_Kho_Sp(NgayNghiepVu,
										  "8989 donhang.java :Áp khuyến mãi___"+id, db, _khoId, _spId, nppId, _kbhId,
										  0,soluong,(-1)* soluong,  0);
										  if (msg1.length()> 0) { 
											  return tag + msg1; }
										  
										  
								    msg1=util_kho.Update_NPP_Kho_Sp_Chitiet(NgayNghiepVu,
								  "8989 donhang.java :Áp khuyến mãi___"+id, db, _khoId, _spId, nppId, _kbhId,
								  _soLo, _Ngayhethan, _ngaynhapkho, 0,soluong,(-1)* soluong, 0, 0);
								  if (msg1.length()> 0) { 
									  return tag + msg1; }
								 

							} else {
								return tag + "Không tồn tại kho theo (SP:" + sp.getMasanpham() + ",Lô:" + splo.getSolo()
										+ "," + splo.getNgayhethan() + " ) trước ngày (" + NgayNghiepVu + ") ";
							}

						}
					}

					if (Double.parseDouble(sp.getSoluong().replaceAll(",", "")) != soluongtongct) {

						return tag + "Vui lòng kiểm tra lại số lượng   lô chi tiết của sản phẩm: " + sp.getMasanpham()
								+ " .Số lượng tổng và chi tiết không bằng nhau";

					}
				}
			}

			return "";
		} catch (Exception e) {
			e.printStackTrace();
			return tag + "Lỗi:" + e.getMessage();
		}
	}

	public double getTiengiamgia() {
		return tiengiamgia;
	}

	public void setTiengiamgia(double tiengiamgia) {
		this.tiengiamgia = tiengiamgia;
	}

	public String getGhichugiamgia() {
		return ghichugiamgia;
	}

	public void setGhichugiamgia(String ghichugiamgia) {
		this.ghichugiamgia = ghichugiamgia;
	}

	public String BookedKhoDonHang(String dhId, String vitriBAM, String ngayhoadon_,
			geso.dms.center.util.Utility util_kho) throws SQLException {
		String query = "";

		/////////// booked tổng hàng bán

		query = "\n  select dh.NPP_FK, case npp.DUNGCHUNGKENH  when 1 then 100025 else dh.kbh_fk end kbh_fk,sp.kho_fk ,sp.sanpham_fk,sp.soluong  "
				+ "\n from DONHANG dh inner join DONHANG_SANPHAM sp on dh.PK_SEQ = sp.DONHANG_FK "
				+ "\n inner join Nhaphanphoi npp on npp.pk_seq = dh.NPP_FK " + "\n where dh.PK_SEQ = " + dhId;
		ResultSet rssp = db.get(query);

		while (rssp.next()) {
			String _khoid = rssp.getString("kho_fk");
			String _nppid = rssp.getString("NPP_FK");
			String _kbhid = rssp.getString("kbh_fk");
			String _spid = rssp.getString("sanpham_fk");
			double _soluong = rssp.getDouble("soluong");

			String msg1 = util_kho.Update_NPP_Kho_Sp(ngayhoadon_, "DuyetDonHang_BookedTongHB(" + vitriBAM + ")" + dhId,
					db, _khoid, _spid, _nppid, _kbhid, 0, _soluong, (-1) * _soluong, 0);// giảm booked,avai cộng
			if (msg1.length() > 0) {
				return msg1;
			}
		}
		rssp.close();
		/// booked chi tiet hang bán
		query = "\n select isnull(a.NPP_FK,dh.NPP_FK) NPP_FK, case npp.DUNGCHUNGKENH  when 1 then 100025 else dh.kbh_fk end KBH_FK "
				+ "\n 	,isnull(a.kho_fk,dh.kho_fk)kho_fk ,SanPham_FK,SoLo,Ngayhethan,ngaynhapkho,soluong   "
				+ "\n from DONHANG_SANPHAM_chitiet  a  inner join DONHANG dh on dh.PK_SEQ = a.donhang_fk   "
				+ "\n inner join Nhaphanphoi npp on npp.pk_seq = dh.npp_fk " + "\n where a.donhang_fk  = " + dhId;
		////System.out.println("querry == " + query);
		rssp = db.get(query);
		while (rssp.next()) {

			String _nppId = rssp.getString("NPP_FK");
			String _kbhId = rssp.getString("KBH_FK");
			String _khoId = rssp.getString("kho_fk");
			String _spId = rssp.getString("SanPham_FK");
			String _soLo = rssp.getString("SoLo");
			String _Ngayhethan = rssp.getString("Ngayhethan");
			String _ngaynhapkho = rssp.getString("ngaynhapkho");
			double soluong = rssp.getDouble("soluong");

			String msg1 = util_kho.Update_NPP_Kho_Sp_Chitiet(ngayhoadon_,
					"DuyetDonHang_BookedCTHB(" + vitriBAM + ")_" + dhId, db, _khoId, _spId, _nppId, _kbhId, _soLo,
					_Ngayhethan, _ngaynhapkho, 0, soluong, (-1) * soluong, (-1) * soluong, 0);
			if (msg1.length() > 0) {
				return msg1;

			}
		}
		rssp.close();

		// booked KM

		query = "\n select dh.NPP_FK, case npp.DUNGCHUNGKENH  when 1 then 100025 else dh.kbh_fk end KBH_FK "
				+ "\n		,dhkm.kho_fk ,sp.pk_seq sanpham_fk,dhkm.soluong  " + "\n from DONHANG_CTKM_TRAKM dhkm   "
				+ "\n inner join SANPHAM sp on sp.MA = dhkm.SPMA  "
				+ "\n inner join DONHANG dh on dh.PK_SEQ = dhkm.DONHANGID  "
				+ "\n inner join Nhaphanphoi npp on npp.pk_seq = dh.npp_fk " + "\n where dhkm.DONHANGID = " + dhId
				+ "  and dhkm.SPMA is not null ";
		rssp = db.get(query);

		while (rssp.next()) {
			String _khoid = rssp.getString("kho_fk");
			String _nppid = rssp.getString("NPP_FK");
			String _kbhid = rssp.getString("kbh_fk");
			String _spid = rssp.getString("sanpham_fk");
			double _soluong = rssp.getDouble("soluong");

			String msg1 = util_kho.Update_NPP_Kho_Sp(ngayhoadon_, "DuyetDonHang_BookedTongKM(" + vitriBAM + ")" + dhId,
					db, _khoid, _spid, _nppid, _kbhid, 0, _soluong, (-1) * _soluong, 0);// giảm booked,avai cộng
			if (msg1.length() > 0) {
				return msg1;
			}
		}
		rssp.close();

		/// booked KM chi tiet

		query = "\n  select dh.NPP_FK,case npp.DUNGCHUNGKENH  when 1 then 100025 else dh.kbh_fk end KBH_FK  "
				+ "\n			, dhct.kho_fk ,dhct.SanPham_FK,dhct.SoLo,dhct.Ngayhethan,dhct.ngaynhapkho,dhct.soluong  "
				+ "\n  from DONHANG_CTKM_TRAKM_CHITIET dhct "
				+ "\n  inner join DONHANG dh on dh.PK_SEQ = dhct.DONHANG_FK  "
				+ "\n inner join Nhaphanphoi npp on npp.pk_seq = dh.npp_fk " + "\n  where donhang_fk = " + dhId;
		////System.out.println("querry == " + query);
		rssp = db.get(query);
		while (rssp.next()) {

			String _nppId = rssp.getString("NPP_FK");
			String _kbhId = rssp.getString("KBH_FK");
			String _khoId = rssp.getString("kho_fk");
			String _spId = rssp.getString("SanPham_FK");
			String _soLo = rssp.getString("SoLo");
			String _Ngayhethan = rssp.getString("Ngayhethan");
			String _ngaynhapkho = rssp.getString("ngaynhapkho");
			double soluong = rssp.getDouble("soluong");

			String msg1 = util_kho.Update_NPP_Kho_Sp_Chitiet(ngayhoadon_,
					"DuyetDonHang_BookedCTKM(" + vitriBAM + ")_" + dhId, db, _khoId, _spId, _nppId, _kbhId, _soLo,
					_Ngayhethan, _ngaynhapkho, 0, soluong, (-1) * soluong, (-1) * soluong, 0);
			if (msg1.length() > 0) {
				return msg1;

			}
		}
		rssp.close();

		query = "update donhang set isbooked=1 where trangthai=0 and  pk_seq=" + dhId;
		if (db.updateReturnInt(query) != 1) {
			return "Lỗi isbooked ";
		}
		return "";
	}
	public String BookedKhoDonHang_KM(String dhId, String vitriBAM, String ngayhoadon_,
			geso.dms.center.util.Utility util_kho) throws SQLException {
		String query = "";

		/////////// booked tổng hàng bán

		/*query = "\n  select dh.NPP_FK, case npp.DUNGCHUNGKENH  when 1 then 100025 else dh.kbh_fk end kbh_fk,sp.kho_fk ,sp.sanpham_fk,sp.soluong  "
				+ "\n from DONHANG dh inner join DONHANG_SANPHAM sp on dh.PK_SEQ = sp.DONHANG_FK "
				+ "\n inner join Nhaphanphoi npp on npp.pk_seq = dh.NPP_FK " + "\n where dh.PK_SEQ = " + dhId;
		ResultSet rssp = db.get(query);

		while (rssp.next()) {
			String _khoid = rssp.getString("kho_fk");
			String _nppid = rssp.getString("NPP_FK");
			String _kbhid = rssp.getString("kbh_fk");
			String _spid = rssp.getString("sanpham_fk");
			double _soluong = rssp.getDouble("soluong");

			String msg1 = util_kho.Update_NPP_Kho_Sp(ngayhoadon_, "DuyetDonHang_BookedTongHB(" + vitriBAM + ")" + dhId,
					db, _khoid, _spid, _nppid, _kbhid, 0, _soluong, (-1) * _soluong, 0);// giảm booked,avai cộng
			if (msg1.length() > 0) {
				return msg1;
			}
		}
		rssp.close();
		/// booked chi tiet hang bán
		query = "\n select isnull(a.NPP_FK,dh.NPP_FK) NPP_FK, case npp.DUNGCHUNGKENH  when 1 then 100025 else dh.kbh_fk end KBH_FK "
				+ "\n 	,isnull(a.kho_fk,dh.kho_fk)kho_fk ,SanPham_FK,SoLo,Ngayhethan,ngaynhapkho,soluong   "
				+ "\n from DONHANG_SANPHAM_chitiet  a  inner join DONHANG dh on dh.PK_SEQ = a.donhang_fk   "
				+ "\n inner join Nhaphanphoi npp on npp.pk_seq = dh.npp_fk " + "\n where a.donhang_fk  = " + dhId;
		////System.out.println("querry == " + query);
		rssp = db.get(query);
		while (rssp.next()) {

			String _nppId = rssp.getString("NPP_FK");
			String _kbhId = rssp.getString("KBH_FK");
			String _khoId = rssp.getString("kho_fk");
			String _spId = rssp.getString("SanPham_FK");
			String _soLo = rssp.getString("SoLo");
			String _Ngayhethan = rssp.getString("Ngayhethan");
			String _ngaynhapkho = rssp.getString("ngaynhapkho");
			double soluong = rssp.getDouble("soluong");

			String msg1 = util_kho.Update_NPP_Kho_Sp_Chitiet(ngayhoadon_,
					"DuyetDonHang_BookedCTHB(" + vitriBAM + ")_" + dhId, db, _khoId, _spId, _nppId, _kbhId, _soLo,
					_Ngayhethan, _ngaynhapkho, 0, soluong, (-1) * soluong, (-1) * soluong, 0);
			if (msg1.length() > 0) {
				return msg1;

			}
		}
		rssp.close();

		// booked KM
*/
		 query = "\n select dh.NPP_FK, case npp.DUNGCHUNGKENH  when 1 then 100025 else dh.kbh_fk end KBH_FK "
				+ "\n		,dhkm.kho_fk ,sp.pk_seq sanpham_fk,dhkm.soluong  " + "\n from DONHANG_CTKM_TRAKM dhkm   "
				+ "\n inner join SANPHAM sp on sp.MA = dhkm.SPMA  "
				+ "\n inner join DONHANG dh on dh.PK_SEQ = dhkm.DONHANGID  "
				+ "\n inner join Nhaphanphoi npp on npp.pk_seq = dh.npp_fk " + "\n where dhkm.DONHANGID = " + dhId
				+ "  and dhkm.SPMA is not null ";
		ResultSet rssp = db.get(query);

		while (rssp.next()) {
			String _khoid = rssp.getString("kho_fk");
			String _nppid = rssp.getString("NPP_FK");
			String _kbhid = rssp.getString("kbh_fk");
			String _spid = rssp.getString("sanpham_fk");
			double _soluong = rssp.getDouble("soluong");

			String msg1 = util_kho.Update_NPP_Kho_Sp(ngayhoadon_, "DuyetDonHang_BookedTongKM(" + vitriBAM + ")" + dhId,
					db, _khoid, _spid, _nppid, _kbhid, 0, _soluong, (-1) * _soluong, 0);// giảm booked,avai cộng
			if (msg1.length() > 0) {
				return msg1;
			}
		}
		rssp.close();

		/// booked KM chi tiet

		query = "\n  select dh.NPP_FK,case npp.DUNGCHUNGKENH  when 1 then 100025 else dh.kbh_fk end KBH_FK  "
				+ "\n			, dhct.kho_fk ,dhct.SanPham_FK,dhct.SoLo,dhct.Ngayhethan,dhct.ngaynhapkho,dhct.soluong  "
				+ "\n  from DONHANG_CTKM_TRAKM_CHITIET dhct "
				+ "\n  inner join DONHANG dh on dh.PK_SEQ = dhct.DONHANG_FK  "
				+ "\n inner join Nhaphanphoi npp on npp.pk_seq = dh.npp_fk " + "\n  where donhang_fk = " + dhId;
		////System.out.println("querry == " + query);
		rssp = db.get(query);
		while (rssp.next()) {

			String _nppId = rssp.getString("NPP_FK");
			String _kbhId = rssp.getString("KBH_FK");
			String _khoId = rssp.getString("kho_fk");
			String _spId = rssp.getString("SanPham_FK");
			String _soLo = rssp.getString("SoLo");
			String _Ngayhethan = rssp.getString("Ngayhethan");
			String _ngaynhapkho = rssp.getString("ngaynhapkho");
			double soluong = rssp.getDouble("soluong");

			String msg1 = util_kho.Update_NPP_Kho_Sp_Chitiet(ngayhoadon_,
					"DuyetDonHang_BookedCTKM(" + vitriBAM + ")_" + dhId, db, _khoId, _spId, _nppId, _kbhId, _soLo,
					_Ngayhethan, _ngaynhapkho, 0, soluong, (-1) * soluong, (-1) * soluong, 0);
			if (msg1.length() > 0) {
				return msg1;

			}
		}
		rssp.close();

		query = "update donhang set isbooked=1 where trangthai=0 and  pk_seq=" + dhId;
		if (db.updateReturnInt(query) != 1) {
			return "Lỗi isbooked ";
		}
		return "";
	}
	private String ApTichluy() {

		String _ngayhoadon = Utility.getNgayHienTai();
		try {
			db.getConnection().setAutoCommit(false);
			String thuongtl_fk = "";
			String ctkhuyenmai_fk = "";
			String diengiai = "";
			String spId = "";
			String trakm_fk = "";
			String query = "\n update tb set DaTra = 0 from DUYETTRAKHUYENMAI_KHACHHANG tb "
					+ "\n where ISNULL(isdacbiet,0) = 0 "
					+ "\n			and tb.DuyetKm_FK in (select DuyetKm_FK from  DONHANG_CTKM_TRAKM where DONHANGID = "
					+ this.id + "  ) " + "\n         and  tb.KhId in (select khachhang_fk from donHang where pk_seq = '"
					+ this.id + "'   )  "
					+ "\n         and not   exists  (select 1  from  DONHANG_CTKM_TRAKM x inner join DonHang y on x.DONHANGID = y.pk_Seq where x.DONHANGID != '"
					+ this.id + "' and y.trangthai <> 2  "
					+ "\n                      and x.DuyetKm_FK = tb.DuyetKm_FK  and  y.KhachHang_fk = tb.KhId  ) ";
			int kq = db.updateReturnInt(query);
			if (kq > 1 || kq < 0) {
				db.getConnection().rollback();
				db.getConnection().setAutoCommit(true);
				return "Lỗi tích lũy:" + query;
			}

			query = "  delete from DONHANG_CTKM_TRAKM where isDacBiet = 0 and DONHANGID = " + this.id
					+ " and CTKMID in (select PK_SEQ from CTKHUYENMAI where LOAICT = 3) ";
			if (!db.update(query)) {
				db.getConnection().rollback();
				db.getConnection().setAutoCommit(true);
				return "Lỗi tích lũy:" + query;
			}
			

			geso.dms.center.util.Utility util_kho = new geso.dms.center.util.Utility();
			
			
			
			// trả lại kho 
			 
			query = "\n SELECT DH.NPP_FK,   DHCT.KHO_FK,DHCT.sanpham_fk AS SPID, "
					+ " case npp.DUNGCHUNGKENH  when 1 then 100025 else dh.KBH_FK end as kbh_fk, " +
					" DHCT.SOLO,DHCT.NGAYHETHAN,DHCT.NGAYNHAPKHO " + "\n  , DHCT.SOLUONG  SOLUONG "
					+ "\n  FROM DONHANG_CTKM_TRAKM_CHITIET  DHCT "
					+ "\n  INNER JOIN CTKHUYENMAI KM ON KM.PK_SEQ=DHCT.ctkm_fk "
					+ "\n  INNER JOIN DONHANG DH ON DH.PK_SEQ=DHCT.DONHANG_fk "
					+ "\n inner join nhaphanphoi npp on npp.pk_seq = dh.npp_fk  " +
					"\n  WHERE  KM.LOAICT = 3 and DHCT.donhang_fk= "
					+ this.id;

			ResultSet rssp = db.get(query);
			while (rssp.next()) {
				String _spid = rssp.getString("SPID");
				String _nppid = rssp.getString("NPP_FK");
				String _khoid = rssp.getString("KHO_FK");
				String _kbhid = rssp.getString("KBH_FK");
				double _SOLUONG = rssp.getDouble("SOLUONG");
				String _solo = rssp.getString("solo");
				String _ngaynhapkho = rssp.getString("ngaynhapkho");
				String _ngayhethan = rssp.getString("ngayhethan");

				 
				  String msg1= util_kho.Update_NPP_Kho_Sp_Chitiet(this.ngaygiaodich,
				 "9448. donhang.java Cập nhật đơn hàng___" +id , db, _khoid, _spid, _nppid,
				  _kbhid, _solo, _ngayhethan, _ngaynhapkho, 0 ,(-1)* _SOLUONG, _SOLUONG,
				  _SOLUONG, 0); // giảm booked, tăng avail lại
				 
				  if (msg1.length() > 0){ db.getConnection().rollback(); return "Error :" +
				   msg1; }
			 
			}
			rssp.close();
			
			
			
			query = "  delete from donhang_ctkm_trakm_chitiet where isDacBiet = 0 and DONHANG_FK = " + this.id
					+ " and CTKM_FK in (select PK_SEQ from CTKHUYENMAI where LOAICT = 3) ";
			if (!db.update(query)) {
				db.getConnection().rollback();
				db.getConnection().setAutoCommit(true);
				return "Lỗi tích lũy:" + query;
			}
			
			
			
			query = "\n select  ( select top 1 TRAKHUYENMAI_FK  from CTKM_TRAKM where CTKHUYENMAI_FK = cb.ctkhuyenmai_fk   ) trakm_fk "
					+ "\n		,cb.ctkhuyenmai_fk,cb.CTKM_FK, khtb.DuyetKm_FK, isnull(cb.DIENGIAI,'') as diengiai, "
					+ "\n		isnull(khtb.Thuong,0) as TongGiaTri, SANPHAM_FKs, isnull(khtb.donvi,0) as donvi, "
					+ "\n		(select tonggiatri from donhang where pk_seq = '" + this.id + "' ) as TONGDH "
					+ "\n from DUYETTRAKHUYENMAI_KHACHHANG khtb "
					+ "\n	inner join DUYETTRAKHUYENMAI cb on khtb.DuyetKm_FK  = cb.pk_seq  and cb.TRANGTHAI = 1 "
					+ "\n where ISNULL(khtb.isdacbiet,0) = 0  and isnull(DaTra,0) = 0 and isnull(khtb.mucduyet,0) != 0 and khID="
					+ this.khId
					+ "\n 		and not exists ( select 1 from DONHANG x inner join DUYETTRAKHUYENMAI_DONHANG y on x.PK_SEQ = y.DONHANG_FK where x.TRANGTHAI <> 2 and x.KHACHHANG_FK = khtb.Khid and khtb.DUYETKM_FK = y.duyetkm_fk )	 "
					+ "\n			and isnull(khtb.Thuong, 0) <= isnull((select tonggiatri from donhang where pk_seq = '"
					+ this.id + "' ),-1) 	 " + "\n	order by cb.pk_Seq asc";
			////System.out.println(" query cttb = " + query);
			ResultSet rs = db.get(query);
			double TongGiaTri = 0;
			if (rs.next()) {
				trakm_fk = rs.getString("trakm_fk");
				ctkhuyenmai_fk = rs.getString("ctkhuyenmai_fk");
				thuongtl_fk = rs.getString("CTKM_FK");
				diengiai = rs.getString("diengiai");
				spId = rs.getString("SANPHAM_FKs");
				String DuyetKm_FK = rs.getString("DuyetKm_FK");
				TongGiaTri = rs.getDouble("TongGiaTri");
				int donvi = rs.getInt("donvi");

				double giatri = rs.getDouble("TONGDH");

				if (giatri < TongGiaTri) {
					db.getConnection().commit();
					db.getConnection().setAutoCommit(true);
					return "";
				}
				if (donvi != 2) {

					query = "Insert into donhang_ctkm_trakm(isDacBiet,ThuongTL_FK, DuyetKm_FK,donhangId, ctkmId, trakmId, soxuat, tonggiatri) "
							+ "\n select 0," + thuongtl_fk + ", " + DuyetKm_FK + ",'" + this.id + "','" + ctkhuyenmai_fk
							+ "','" + trakm_fk + "','" + 1 + "'," + TongGiaTri;
					////System.out.println(" query cttb = " + query);
					if (!db.update(query)) {
						db.getConnection().rollback();
						db.getConnection().setAutoCommit(true);
						return "Lỗi tích lũy:" + query;
					}

				} else {
					String khotlId = " (select khoId from TIEUCHITHUONGTL where pk_seq =" + thuongtl_fk + " ) ";
					query = "select a.*,s.ma spma from dbo.GetSanphamTraTichluy('" + spId
							+ "') a inner join SANPHAM s on s.PK_SEQ = a.sanphamid";
					ResultSet sprs = this.db.get(query);
					while (sprs.next()) {

						query = "\n Insert into donhang_ctkm_trakm(isDacBiet,ThuongTL_FK, DuyetKm_FK,donhangId, ctkmId, trakmId, soxuat, tonggiatri, spMa, soluong,kho_fk) "
								+ "\n select 0," + thuongtl_fk + ", " + DuyetKm_FK + ",'" + this.id + "',"
								+ ctkhuyenmai_fk + ",'" + trakm_fk + "','" + 1 + "','" + 0 + "', '"
								+ sprs.getString("spma") + "', '" + sprs.getString("soluong") + "'," + khotlId + " ";
						if (db.updateReturnInt(query) <= 0) {
							db.getConnection().rollback();
							db.getConnection().setAutoCommit(true);
							return "Lỗi tích lũy:" + query;
						}

						String spid = sprs.getString("sanphamid");

						// thêm đề xuất số lô tự động
						double slg = geso.dms.center.util.Utility.parseDouble(sprs.getString("soluong"));

						query = "\n  select case when kho.SoLuong > 0 then 0 else 1 end loai,kho.KHO_FK,kho.SANPHAM_FK,kho.KBH_FK,kho.SOLO,kho.NGAYHETHAN,kho.NGAYNHAPKHO,kho.available  "
								+ "\n from NHAPP_KHO_CHITIET kho "
								+ "\n inner join Nhaphanphoi npp on npp.pk_seq = kho.npp_fk " + "\n where kho.NPP_FK ="
								+ nppId
								+ " and kho.KBH_FK= case npp.DUNGCHUNGKENH  when 1 then 100025 else (select kbh_fk from khachhang where pk_seq = '"
								+ khId + "') end   " + "\n and kho.KHO_FK=" + khotlId + "  and kho.SANPHAM_FK =  "
								+ spid + "\n  kho.NGAYNHAPKHO<='" + _ngayhoadon + "'  "
								+ "\n order by  loai asc,kho.NGAYHETHAN asc, kho.ngaynhapkho asc,kho.SOLO asc ";
						  rssp = db.get(query);
						double soluongdenghi = slg;

						while (rssp.next() && soluongdenghi > 0) {
							double soluong_avai = slg + 1;
							double soluongcapnhat = 0;

							if (soluong_avai > soluongdenghi) {
								soluongcapnhat = soluongdenghi;
								soluongdenghi = 0;
							} else {
								soluongcapnhat = soluong_avai;
								soluongdenghi = soluongdenghi - soluong_avai;
							}
							String solo = rssp.getString("SOLO");
							String ngaynhapkho = rssp.getString("ngaynhapkho");
							String ngayhethan = rssp.getString("ngayhethan");
							String _khoid = rssp.getString("kho_fk");
							String _kbhid = rssp.getString("KBH_FK");
							// cập nhật vào bảng đơn hàng sp _chitiet
							query = " Insert into donhang_ctkm_trakm_chitiet(isDacBiet,ThuongTL_FK, DuyetKm_FK,donhang_fk , ctkm_fk, trakm_fk, sanpham_fk,solo,ngaynhapkho,ngayhethan, soluong,kho_fk) "
									+ " select 0," + thuongtl_fk + ", " + DuyetKm_FK + ",'" + this.id + "','"
									+ ctkhuyenmai_fk + "','" + trakm_fk + "','" + spid + "','" + solo + "','"
									+ ngaynhapkho + "','" + ngayhethan + "'," + soluongcapnhat + "," + khotlId;

							if (db.updateReturnInt(query) <= 0) {
								db.getConnection().rollback();
								db.getConnection().setAutoCommit(true);
								return "Lỗi tích lũy:" + query;
							}
							 
							 String msg1=util_kho.Update_NPP_Kho_Sp_Chitiet(_ngayhoadon,
							  "8989 donhang.java :Áp khuyến mãi", db, _khoid, spid, nppId, _kbhid, solo,
							   ngayhethan, ngaynhapkho, 0,soluongcapnhat,(-1)* soluongcapnhat, (-1)*
							  soluongcapnhat, 0); if (msg1.length()> 0) { db.getConnection().rollback();
							 return msg1; }
							 
						}
						rssp.close();

						if (soluongdenghi != 0) {
							db.getConnection().rollback();
							db.getConnection().setAutoCommit(true);
							return "Lỗi tích lũy: Số lượng đề xuất trong lô chi tiết theo ngày không còn đủ, vui lòng kiểm tra xuất nhập tồn theo lô để biết thêm chi tiết ";
						}
					}
					sprs.close();
				}
				query = "update DUYETTRAKHUYENMAI_KHACHHANG set datra = 1 where isnull(datra,0) = 0 and khid ="
						+ this.khId + " and DuyetKm_FK =" + DuyetKm_FK;
				////System.out.println(" query cttb = " + query);
				if (db.updateReturnInt(query) != 1) {
					db.getConnection().rollback();
					db.getConnection().setAutoCommit(true);
					return "Lỗi tích lũy:" + query;
				}
			}
			rs.close();
			db.getConnection().commit();
			db.getConnection().setAutoCommit(true);

			return msg;
		} catch (Exception e) {
			db.update("rollback");
			// this.msg =
			e.printStackTrace();
			return "Lỗi tích lũy: ap tichluy Exception->" + e.getMessage();
		}

	}

	double diemkm = 0.0;
	double tienKmDiem = 0.0;
	String ctkmDiemId = "";
	ResultSet ctkmDiemRs;

	public double getDiemkm() {
		return diemkm;
	}

	public void setDiemkm(double diemkm) {
		this.diemkm = diemkm;
	}

	public String getCtkmDiemId() {
		return ctkmDiemId;
	}

	public void setCtkmDiemId(String ctkmDiemId) {
		this.ctkmDiemId = ctkmDiemId;
	}

	public ResultSet getCtkmDiemRs() {
		return ctkmDiemRs;
	}

	public double getTienKmDiem() {
		return tienKmDiem;
	}

	public void setTienKmDiem(double tienKmDiem) {
		this.tienKmDiem = tienKmDiem;
	}

	public void InitCtkmDiemRs() {
		String query = "\n select distinct a.pk_seq, a.scheme, a.diengiai  "
				+ "\n from ctkhuyenmai a inner join ctkm_npp b on a.pk_seq = b.ctkm_fk and b.NPP_FK = '" + this.nppId
				+ "'  " + "\n where a.trangthai = 1 and a.loaict = 4 and a.tungay <= '" + this.ngaygiaodich + "' and '"
				+ this.ngaygiaodich + "' <= a.denngay   ";
		////System.out.println(" InitCtkmDiemRs =  " + query);
		this.ctkmDiemRs = db.get(query);

	}

	public void TinhDiemKhuyenMai() {
		try {
			if (this.ctkmDiemId.trim().length() <= 0)
				return;

			String query = "";

			String spDonHang = "";
			for (int m = 0; m < splist.size(); m++) {
				String masp = splist.get(m).getMasanpham();
				double sl = geso.dms.center.util.Utility.parseDouble(splist.get(m).getSoluong().replace(",", ""));

				if (spDonHang.trim().length() > 0)
					spDonHang += "\n\t union all select pk_seq, " + sl + " as SoLuongMua from SanPham where ma ='"
							+ masp + "'   ";
				else
					spDonHang += "\n\t select pk_seq, " + sl + " as SoLuongMua from SanPham where ma ='" + masp
							+ "'   ";
			}
			if (spDonHang.trim().length() > 0) {
				query = "\n select  sum( soluongMua *( quydoi/cast(SOLUONG as float) )) tongdiem  "
						+ "\n from DIEUKIENKM_SANPHAM dkm  " + "\n inner join   " + "\n (  " + spDonHang
						+ "\n )spmua on spmua.PK_SEQ = dkm.SANPHAM_FK  "
						+ "\n where DIEUKIENKHUYENMAI_FK in (select DKKHUYENMAI_FK from CTKM_DKKM where CTKHUYENMAI_FK = "
						+ this.ctkmDiemId + "   )  ";
				ResultSet rs = db.get(query);
				if (rs.next()) {
					this.diemkm = rs.getDouble("tongdiem");
				}
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public static String GhinhanCtkmDiem(Idbutils db, String dhId, String ctkmDiemId, double tienKmDiem, double diem,
			String khId) {

		String query = "";
		try {
			query = "delete from donhang_ctkm_trakm where donhangid = '" + dhId
					+ "' and ctkmId in (select pk_Seq from ctkhuyenmai where loaict = 4) ";

			////System.out.println(query);
			if (!db.update(query)) {
				////System.out.println("Error :" + query);
				return "Error KM điểm  :" + query;
			}

			if (tienKmDiem > 0 && ctkmDiemId.trim().length() > 0) {
				query = " update donhang set ctkmDiem_fk =" + ctkmDiemId + ", tienKmDiem =" + tienKmDiem
						+ ", SoDiemCTKM = " + diem + " where pk_Seq =  " + dhId;
			} else {
				query = " update donhang set ctkmDiem_fk =null, tienKmDiem =0, SoDiemCTKM = " + diem
						+ " where pk_Seq =  " + dhId;
			}
			if (!db.update(query)) {
				////System.out.println("Error :" + query);
				return "Error KM điểm  :" + query;
			}

			if (tienKmDiem > 0 && ctkmDiemId.trim().length() > 0) {

				String trakmId = "(select top 1 TRAKHUYENMAI_FK from CTKM_TRAKM  where CTKHUYENMAI_FK =" + ctkmDiemId
						+ " )";

				query = "Insert into donhang_ctkm_trakm(donhangId, ctkmId, trakmId, soxuat, tonggiatri) "
						+ "\n select '" + dhId + "','" + ctkmDiemId + "'," + trakmId + ",'" + 1 + "','" + tienKmDiem
						+ "' ";
				if (db.updateReturnInt(query) <= 0) {
					////System.out.println("Error KM điểm :" + query);
					return "Error :" + query;
				}
			}

			return "";

		} catch (Exception e) {
			e.printStackTrace();
			return "Lỗi:" + e.getMessage();
		}
	}

	public String getKhDienThoai() {
		return khDienThoai;
	}

	public String getKhMaSoThue() {
		return khMaSoThue;
	}

	public void setKhDienThoai(String khDienThoai) {
		this.khDienThoai = khDienThoai;
	}

	public void setKhMaSoThue(String khMaSoThue) {
		this.khMaSoThue = khMaSoThue;
	}

	public String MoChotDonHang(String dhId, String userId, dbutils db) {

		////System.out.println("vo day !");
		String query = "";
		String msg = "";
		String pxk_fk = "", hoadon_fk = "";
		String ngayhoadon_ = Utility.getNgayHienTai();
		geso.dms.center.util.Utility util_kho = new geso.dms.center.util.Utility();
		
		try {
			db.getConnection().setAutoCommit(false);
			if (dhId != null && dhId.length() > 0) {

				query = "select pxk_fk from phieuxuatkho_donhang where donhang_fk =" + dhId;
				ResultSet temprs = db.get(query);
				try {
					while (temprs.next()) {
						pxk_fk = temprs.getString("pxk_fk");
					}
					temprs.close();
				} catch (Exception e1) {
					// e1.printStackTrace();
					db.getConnection().rollback();
					db.getConnection().setAutoCommit(true);
					msg = "Lỗi đơn hàng chưa chốt hoặc không xác định được phiếu xuất kho của đơn hàng " + dhId;
					return msg;
				}

				query = "select hoadon_fk from hoadon_ddh where ddh_fk =" + dhId;
				temprs = db.get(query);
				try {
					while (temprs.next()) {
						hoadon_fk = temprs.getString("hoadon_fk");
					}
					temprs.close();
				} catch (Exception e1) {
					// e1.printStackTrace();
					db.getConnection().rollback();
					db.getConnection().setAutoCommit(true);
					msg = "Lỗi đơn hàng chưa chốt hoặc không xác định được hoá đơn của đơn hàng " + dhId;
					return msg;
				}

				////System.out.println("pxk : " + pxk_fk + " - hoadon : " + hoadon_fk);

				if (pxk_fk.trim().length() > 0) {
					query = "delete phieuxuatkho_donhang where donhang_fk = " + dhId;
					if (!db.update(query)) {
						msg = "Err7221 Lỗi xoá phiếu xuất kho";
						db.getConnection().rollback();
						db.getConnection().setAutoCommit(true);
						return msg;
					}

					query = "delete phieuxuatkho_sanpham_chitiet where pxk_fk = " + pxk_fk;
					if (!db.update(query)) {
						msg = "Err7229 Lỗi xoá phiếu xuất kho";
						db.getConnection().rollback();
						db.getConnection().setAutoCommit(true);
						return msg;
					}

					query = "delete phieuxuatkho_sanpham where pxk_fk = " + pxk_fk;
					if (!db.update(query)) {
						msg = "Err7237 Lỗi xoá phiếu xuất kho";
						db.getConnection().rollback();
						db.getConnection().setAutoCommit(true);
						return msg;
					}

					query = "delete phieuxuatkho where pk_seq = " + pxk_fk;
					if (!db.update(query)) {
						msg = "Err7245 Lỗi xoá phiếu xuất kho";
						db.getConnection().rollback();
						db.getConnection().setAutoCommit(true);
						return msg;
					}
				} else {
					db.getConnection().rollback();
					db.getConnection().setAutoCommit(true);
					msg = "Lỗi đơn hàng chưa chốt hoặc không xác định được phiếu xuất kho của đơn hàng " + dhId;
					////System.out.println("msg : " + msg);
					return msg;
				}

				if (hoadon_fk.trim().length() > 0) {
					query = "delete hoadon_ddh where ddh_fk = " + dhId;
					if (!db.update(query)) {
						msg = "Err7261 Lỗi xoá hoá đơn";
						db.getConnection().rollback();
						db.getConnection().setAutoCommit(true);
						return msg;
					}

					query = "delete hoadon_sp_chitiet where hoadon_fk = " + hoadon_fk;
					if (!db.update(query)) {
						msg = "Err7269 Lỗi xoá hoá đơn";
						db.getConnection().rollback();
						db.getConnection().setAutoCommit(true);
						return msg;
					}

					query = "delete hoadon_sp where hoadon_fk = " + hoadon_fk;
					if (!db.update(query)) {
						msg = "Err7277 Lỗi xoá hoá đơn";
						db.getConnection().rollback();
						db.getConnection().setAutoCommit(true);
						return msg;
					}

					query = "delete hoadon_ctkm_trakm_chitiet where hoadon_fk = " + hoadon_fk;
					if (!db.update(query)) {
						msg = "Err7285 Lỗi xoá hoá đơn";
						db.getConnection().rollback();
						db.getConnection().setAutoCommit(true);
						return msg;
					}

					query = "delete hoadon_ctkm_trakm where hoadon_fk = " + hoadon_fk;
					if (!db.update(query)) {
						msg = "Err7293 Lỗi xoá hoá đơn";
						db.getConnection().rollback();
						db.getConnection().setAutoCommit(true);
						return msg;
					}

					query = "delete HOADON_CHIETKHAU where hoadon_fk = " + hoadon_fk;
					if (!db.update(query)) {
						msg = "Err7346 Lỗi xoá hoá đơn";
						db.getConnection().rollback();
						db.getConnection().setAutoCommit(true);
						return msg;
					}
					
					query = "delete hoadon where pk_seq = " + hoadon_fk;
					if (!db.update(query)) {
						msg = "Err7354 Lỗi xoá hoá đơn";
						db.getConnection().rollback();
						db.getConnection().setAutoCommit(true);
						return msg;
					}
				} else {
					db.getConnection().rollback();
					db.getConnection().setAutoCommit(true);
					msg = "Lỗi đơn hàng chưa chốt hoặc không xác định được hoá đơn của đơn hàng " + dhId;
					return msg;
				}

				query = "update donhang set trangthai = 0, synced = 0, thoidiemmochot=getdate(), usermochot = " + userId
						+ "\n where trangthai = 1 and pk_seq = " + dhId;
				if ( db.updateReturnInt(query) !=1) {
					msg = "Err7316 Không thể mở chốt đơn hàng";
					db.getConnection().rollback();
					db.getConnection().setAutoCommit(true);
					return msg;
				}
				
				query = " select count(*)sd from nhaphanphoi where quanlykho = 1 and pk_seq = (select npp_fk from donhang where pk_seq = "+dhId+") ";
				if( geso.dms.center.util.Utility.Check_Count_Query(db, query) > 0 )
				{
					// Revert kho hàng bán
					query = "\n  select dh.NPP_FK,dh.kbh_fk,sp.kho_fk ,sp.sanpham_fk,sp.soluong  "
							+ "\n from DONHANG dh inner join DONHANG_SANPHAM sp on dh.PK_SEQ = sp.DONHANG_FK "
							+ "\n where dh.PK_SEQ = " + dhId;
					ResultSet rssp = db.get(query);

					while (rssp.next()) {
						String _khoid = rssp.getString("kho_fk");
						String _nppid = rssp.getString("NPP_FK");
						String _kbhid = rssp.getString("kbh_fk");
						String _spid = rssp.getString("sanpham_fk");
						double _soluong = rssp.getDouble("soluong");

						msg = util_kho.Update_NPP_Kho_Sp(ngayhoadon_, "Revert kho hàng bán tổng " + dhId, db, _khoid, _spid,
								_nppid, _kbhid, _soluong,_soluong, 0, 0);// giảm booked,avai cộng
						if (msg.length() > 0) {
							db.getConnection().rollback();
							db.getConnection().setAutoCommit(true);
							return msg;
						}
					}
					rssp.close();

					// Revert kho hàng bán chi tiết
					query = "\n select isnull(a.NPP_FK,dh.NPP_FK) NPP_FK,isnull(a.KBH_FK,dh.KBH_FK)KBH_FK,isnull(a.kho_fk,dh.kho_fk)kho_fk ,SanPham_FK,SoLo,Ngayhethan,ngaynhapkho,soluong   "
							+ "\n from DONHANG_SANPHAM_chitiet  a  inner join DONHANG dh on dh.PK_SEQ = a.donhang_fk   "
							+ "\n where a.donhang_fk =  " + dhId;
					////System.out.println("querry == " + query);
					rssp = db.get(query);
					while (rssp.next()) {

						String _nppId = rssp.getString("NPP_FK");
						String _kbhId = rssp.getString("KBH_FK");
						String _khoId = rssp.getString("kho_fk");
						String _spId = rssp.getString("SanPham_FK");
						String _soLo = rssp.getString("SoLo");
						String _Ngayhethan = rssp.getString("Ngayhethan");
						String _ngaynhapkho = rssp.getString("ngaynhapkho");
						double soluong = rssp.getDouble("soluong");

						msg = util_kho.Update_NPP_Kho_Sp_Chitiet(ngayhoadon_, "Revert kho hàng bán chi tiết " + dhId, db,
								_khoId, _spId, _nppId, _kbhId, _soLo, _Ngayhethan, _ngaynhapkho, soluong, soluong,0 ,
								soluong, 0);
						if (msg.length() > 0) {
							db.getConnection().rollback();
							db.getConnection().setAutoCommit(true);
							return msg;
						}
					}
					rssp.close();

					// Revert kho hàng KM
					query = "\n select dh.NPP_FK,dh.kbh_fk,dhkm.kho_fk ,sp.pk_seq sanpham_fk,dhkm.soluong  "
							+ "\n from DONHANG_CTKM_TRAKM dhkm   " + "\n inner join SANPHAM sp on sp.MA = dhkm.SPMA  "
							+ "\n inner join DONHANG dh on dh.PK_SEQ = dhkm.DONHANGID  " + "\n where dhkm.DONHANGID = "
							+ dhId + "  and dhkm.SPMA is not null ";
					rssp = db.get(query);

					while (rssp.next()) {
						String _khoid = rssp.getString("kho_fk");
						String _nppid = rssp.getString("NPP_FK");
						String _kbhid = rssp.getString("kbh_fk");
						String _spid = rssp.getString("sanpham_fk");
						double _soluong = rssp.getDouble("soluong");

						msg = util_kho.Update_NPP_Kho_Sp(ngayhoadon_, "Revert kho KM tổng " + dhId, db, _khoid, _spid,
								_nppid, _kbhid, _soluong, _soluong, 0, 0);// giảm booked,avai cộng
						if (msg.length() > 0) {
							db.getConnection().rollback();
							db.getConnection().setAutoCommit(true);
							return msg;
						}
					}
					rssp.close();

					// Revert kho hàng KM chi tiết
					query = "\n  select dh.NPP_FK,dh.KBH_FK, dhct.kho_fk ,dhct.SanPham_FK,dhct.SoLo,dhct.Ngayhethan,dhct.ngaynhapkho,dhct.soluong  "
							+ "\n  from DONHANG_CTKM_TRAKM_CHITIET dhct inner join DONHANG dh on dh.PK_SEQ = dhct.DONHANG_FK  "
							+ "\n  where donhang_fk = " + dhId;
					////System.out.println("querry == " + query);
					rssp = db.get(query);
					while (rssp.next()) {

						String _nppId = rssp.getString("NPP_FK");
						String _kbhId = rssp.getString("KBH_FK");
						String _khoId = rssp.getString("kho_fk");
						String _spId = rssp.getString("SanPham_FK");
						String _soLo = rssp.getString("SoLo");
						String _Ngayhethan = rssp.getString("Ngayhethan");
						String _ngaynhapkho = rssp.getString("ngaynhapkho");
						double soluong = rssp.getDouble("soluong");

						msg = util_kho.Update_NPP_Kho_Sp_Chitiet(ngayhoadon_, "Revert kho KM chi tiết " + dhId, db, _khoId,
								_spId, _nppId, _kbhId, _soLo, _Ngayhethan, _ngaynhapkho, soluong, soluong, 0, soluong, 0);
						if (msg.length() > 0) {
							db.getConnection().rollback();
							db.getConnection().setAutoCommit(true);
							return msg;
						}
					}
					rssp.close();
				}
				

			}

			if (msg != null && msg.length() > 0) {
				db.getConnection().rollback();
				db.getConnection().setAutoCommit(true);
				return msg;
			}

			db.getConnection().commit();
			db.getConnection().setAutoCommit(true);

		} catch (Exception e) {
			e.printStackTrace();
			try {
				db.getConnection().rollback();
				db.getConnection().setAutoCommit(true);
				db.shutDown();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}

			msg = "Exception: " + e.getMessage();
		}
		////System.out.println("msg cúi : " + msg);
		return msg;
	}

	 
	// Xoá đơn hàng O và các chứng từ liên quan
	public static void main(String[] args) { }

	public boolean dexuatlo(String dhId) {

		String query = "";
		try {

			this.db.getConnection().setAutoCommit(false);

			String khId = "";
			String nppId = "";
			int loainpp = -1;
			String quanlykho = "";

			query = " select (select quanlykho from nhaphanphoi where pk_seq = dh.npp_fk)quanlykho, "
					+ "( select loainpp from nhaphanphoi where pk_seq=dh.npp_fk)loainpp,"
					+ "\n khachhang_fk, npp_fk ,dh.ngaynhap  from donhang dh where trangthai = 0 and pk_seq = " + dhId;
			String ngaynhap= "";
			ResultSet rsInfo = db.get(query);
			if (rsInfo.next()) {
				khId = rsInfo.getString("khachhang_fk");
				nppId = rsInfo.getString("npp_fk");
				loainpp = rsInfo.getInt("loainpp");
				quanlykho = rsInfo.getString("quanlykho");
				ngaynhap =rsInfo.getString("ngaynhap");
				
			} else {
				this.msg = " Lỗi:" + query;
				this.db.getConnection().rollback();
				this.db.getConnection().setAutoCommit(true);
				return false;
			}

			if (!quanlykho.equals("1")) {
				this.msg = "Chi nhánh không thể đề xuất lô";
				this.db.getConnection().rollback();
				this.db.getConnection().setAutoCommit(true);
				return false;
			}

			geso.dms.center.util.Utility util_kho = new geso.dms.center.util.Utility();
			// String ngayhoadon_=util_kho.getngayhoadon(this.userId,db,ngaynhap,khId,0);

			query = " delete DONHANG_SANPHAM_chitiet where donhang_fk =   " + dhId;
			if (!db.update(query)) {
				this.msg = " Lỗi:" + query;
				this.db.getConnection().rollback();
				this.db.getConnection().setAutoCommit(true);
				return false;
			}
			query = " delete DONHANG_CTKM_TRAKM_CHITIET where donhang_fk =   " + dhId;
			if (!db.update(query)) {
				this.msg = " Lỗi:" + query;
				this.db.getConnection().rollback();
				this.db.getConnection().setAutoCommit(true);
				return false;
			}
		 
			
			

			// de xuat lo book khi chi tiet
			query = "\n select 0 trakmID,0 CTKMID,1 loai,c.kho_fk as khoId, " +
			// "c.kbh_fk as kbhId, " +
					" case npp.DUNGCHUNGKENH  when 1 then 100025 else c.kbh_fk end kbhId, "
					+ " b.pk_seq as spId, b.ten as spTEN, sum(a.soluong) as soluong "
					+ "\n from donhang_sanpham a inner join sanpham b on a.sanpham_fk = b.pk_seq "
					+ " inner join donhang c on a.donhang_fk = c.pk_seq   "
					+ "\n inner join nhaphanphoi npp on npp.PK_SEQ = c.npp_fk  "
					+ "\n where c.trangthai != 2 and a.donhang_fk in ( " + dhId + " )  "
					+ "\n group by c.kho_fk, c.kbh_fk, b.pk_seq, b.ten , npp.DUNGCHUNGKENH " + "\n union all"
					+ "\n select a.trakmID,a.CTKMID,2,a.kho_fk as khoId, "
					+ " case npp.DUNGCHUNGKENH  when 1 then 100025 else c.kbh_fk end kbhId, "
					+ " b.pk_seq as spId, b.ten as spTEN, sum(a.soluong) as soluong  "
					+ "\n from DONHANG_CTKM_TRAKM a inner join sanpham b on a.SPMA = b.ma "
					+ " inner join donhang c on a.DONHANGID = c.pk_seq "
					+ "\n inner join nhaphanphoi npp on npp.PK_SEQ = c.npp_fk  "
					+ "\n inner join CTKHUYENMAI ctkm on ctkm.PK_SEQ = a.CTKMID "
					+ "\n where c.trangthai != 2 and a.DONHANGID in ( " + dhId + " )"
					+ "\n group by a.trakmID,a.CTKMID,a.kho_fk, c.kbh_fk, b.pk_seq, b.ten , npp.DUNGCHUNGKENH  ";
			ResultSet rsKHO = db.get(query);
			while (rsKHO.next()) {
				String khoId = rsKHO.getString("khoId");
				String kbhId = rsKHO.getString("kbhId");
				String spId = rsKHO.getString("spId");
				String trakmID = rsKHO.getString("trakmID");
				String CTKMID = rsKHO.getString("CTKMID");
				String spTEN = rsKHO.getString("spTEN");
				double soluong = rsKHO.getDouble("soluong");
				int isnhapkhau = 1;
				String loai = rsKHO.getString("loai");

				// TU DE XUAT LO --> KHO CHI TIET THI VAN TRU SO LUONG + AVAI
				query = "\n SELECT case when kho.AVAILABLE-isnull(dh.soluong,0)-isnull(dhkm.soluong,0) > 0 then 0 else 1 end loai,kho.soluong,AVAILABLE-isnull(dh.soluong,0)-isnull(dhkm.soluong,0) available, kho.SOLO, kho.NGAYHETHAN ,KHO.ngaynhapkho  "
						+ "\n FROM NHAPP_KHO_CHITIET  KHO   " + "\n inner join donhang dhx on dhx.pk_seq = " + dhId
						+ "\n inner join Nhaphanphoi npp on npp.pk_seq = kho.npp_fk "
						+ "\n INNER JOIN SANPHAM SP ON SP.PK_SEQ=KHO.SANPHAM_FK  "
						+ "\n left join  donhang_sanpham_chitiet dh on dh.sanpham_fk = KHO.sanpham_fk and dh.npp_fk = kho.npp_fk and dh.kbh_fk = kho.kbh_fk and kho.kho_fk = dh.kho_fk and dh.ngayhethan = kho.ngayhethan and dh.ngaynhapkho = kho.ngaynhapkho and  dh.solo = kho.solo and dh.donhang_fk ="
						+ dhId
						+ "\n left join  (select sum(soluong) as soluong,kho_fk,donhang_fk,sanpham_fk,solo,ngayhethan,ngaynhapkho from donhang_ctkm_trakm_chitiet "
						+ "\n			where donhang_fk = " + dhId
						+ " group by donhang_fk,sanpham_fk,solo,ngayhethan,ngaynhapkho,kho_fk) dhkm "
						+ "\n 				on dhx.kbh_fk = kho.kbh_fk and dhx.npp_fk = kho.npp_fk and dhkm.sanpham_fk = KHO.sanpham_fk and (select npp_fk from donhang  where pk_Seq = dhkm.donhang_fk) = kho.npp_fk and (select kbh_fk from donhang where pk_seq = dhkm.donhang_fk) = kho.kbh_fk and kho.kho_fk = dhkm.kho_fk and dhkm.ngayhethan = kho.ngayhethan and dhkm.ngaynhapkho = kho.ngaynhapkho and  dhkm.solo = kho.solo and dhkm.donhang_fk ="
						+ dhId
						+ "\n WHERE  kho.AVAILABLE-isnull(dh.soluong,0)-isnull(dhkm.soluong,0) > 0 and KHO.KHO_FK = "
						+ khoId + "  " + "\n AND KHO.NPP_FK = " + nppId + " "
						+ "\n	and KHO.KBH_FK = case npp.DUNGCHUNGKENH  when 1 then 100025 else (select kbh_fk from khachhang where pk_Seq = "
						+ khId + ") end  " + "\n 	and SP.pk_seq=" + spId + "  and kho.ngaynhapkho <='"
						+ geso.dms.center.util.Utility.getNgayHienTai() + "'  " + // ngayhoadon_
						"\n ORDER BY  loai asc,NGAYHETHAN asc, ngaynhapkho asc  ";
				////System.out.println(" chitiet:" + query);
				ResultSet rs = db.get(query);

				String NgayHetHan = "";
				double tongluongxuatCT = 0; // PHAI BAT BUOC TONG LUONG XUAT O KHO CHI TIET PHAI BANG TONG LUONG XUAT O
											// KHO TONG

				double totalLUONG = 0;
				boolean exit = false;
				while (rs.next()) {
					NgayHetHan = rs.getString("NGAYHETHAN");
					String ngaynhapkho = rs.getString("ngaynhapkho");
					double avai = rs.getDouble("AVAILABLE");
					totalLUONG += avai;
					double soluongXUAT = 0;

					if (totalLUONG <= soluong && totalLUONG > 0) {
						soluongXUAT = avai;
					} else {
						soluongXUAT = soluong - (totalLUONG - avai);
						exit = true;
					}

					if (spId.equals("100598")) {
						////System.out.println("abc CTKMID = " + CTKMID + ", Avai: " + avai + ", abc NgayHetHan: " + NgayHetHan + ", abc ngaynhapkho: " + ngaynhapkho);
						////System.out.println("abc soluong: " + soluong + ", soluongXUAT: " + soluongXUAT);
					}

					if (soluongXUAT > 0) {
						String _solo = rs.getString("SOLO");
						/*(ngaynhap, dhId, "Ðon hàng PDA", db,
								  khoId, spId, nppId, kbhId, _solo, NgayHetHan, ngaynhapkho, 0, soluongXUAT,
								  (-1) * soluongXUAT, (-1) * soluongXUAT, 0);*/
						 
						   String msg1 = util_kho.Update_NPP_Kho_Sp_Chitiet 
								   (ngaynhap, dhId, db, khoId, spId, nppId, kbhId, _solo, 
										   NgayHetHan, ngaynhapkho, 0, soluongXUAT,  (-1) * soluongXUAT, 0, 0);
						   
						  
						  if (msg1.length() > 0) { 
							  
							    this.msg= msg1;
								this.db.getConnection().rollback();
							 
								return false;
						  }
						  
						 
						if (loai.equals("1"))
							query = " insert into  DONHANG_SANPHAM_chitiet (donhang_fk,sanpham_fk,kbh_fk,kho_fk,solo,ngayhethan,ngaynhapkho,soluong,trangthai,npp_fk)"
									+ " select " + dhId + ", '" + spId + "', '" + kbhId + "','" + khoId + "', '" + _solo
									+ "','" + NgayHetHan + "','" + ngaynhapkho + "','" + soluongXUAT + "',0,'" + nppId
									+ "' ";
						else
							query = " insert into  DONHANG_CTKM_TRAKM_CHITIET (KBH_FK,kho_fk,donhang_fk,sanpham_fk,solo,ngayhethan,ngaynhapkho,soluong,CTKM_FK,TRAKM_FK) "
									+ " select '" + kbhId + "'," + khoId + "," + dhId + ", " + spId + " ,  '" + _solo
									+ "' ,'" + NgayHetHan + "' , '" + ngaynhapkho + "', '" + soluongXUAT + "'," + CTKMID
									+ "," + trakmID;
						if (this.db.updateReturnInt(query) != 1) {
							this.msg = " Lỗi:" + query;
							this.db.getConnection().rollback();
							this.db.getConnection().setAutoCommit(true);
							return false;
						}

						tongluongxuatCT += soluongXUAT;
						if (exit) // DA XUAT DU
						{
							break;
						}
					}
				}

				// if (tongluongxuatCT != soluong)
				if (tongluongxuatCT != soluong) {
					String addStr = "";
					if (loai.equals("1"))
						addStr = " Sp hàng bán (" + spTEN + ")";
					else
						addStr = " Sp KM (" + spTEN + ")";

					rsKHO.close();
					this.msg = addStr
							+ ": số lượng đề xuất trong lô chi tiết theo ngày không còn đủ, vui lòng kiểm tra xuất nhập tồn theo lô để biết thêm chi tiết";
					this.db.getConnection().rollback();
					this.db.getConnection().setAutoCommit(true);
					return false;

				}
			}

			rsKHO.close();
			this.db.getConnection().commit();
			this.db.getConnection().setAutoCommit(true);
			this.msg = "Đề xuất lô thành công!";
			return true;
		} catch (Exception e) {
			////System.out.println("query er= " + query);
			e.printStackTrace();
			this.msg = "Exeption" + e.getMessage();
			geso.dms.center.util.Utility.rollback_throw_exception(db);
			return false;
		}

	}

	public String dexuatlokm(Idbutils db, String dhId, String ngaynhap) {

		String query = "";
		try {

			String khId = "";
			String nppId = "";
			int loainpp = -1;
			String quanlykho = "";

			query = " select (select quanlykho from nhaphanphoi where pk_seq = dh.npp_fk)quanlykho,( select loainpp from nhaphanphoi where pk_seq=dh.npp_fk)loainpp,"
					+ "\n khachhang_fk, npp_fk from donhang dh where trangthai = 0 and pk_seq = " + dhId;
			ResultSet rsInfo = db.get(query);
			if (rsInfo.next()) {
				khId = rsInfo.getString("khachhang_fk");
				nppId = rsInfo.getString("npp_fk");
				loainpp = rsInfo.getInt("loainpp");
				quanlykho = rsInfo.getString("quanlykho");
			} else {
				return " Lỗi:" + query;
			}

			if (!quanlykho.equals("1")) {
				return "";
			}

			geso.dms.center.util.Utility util_kho = new geso.dms.center.util.Utility();
			String ngayhoadon_ = ngaynhap;// util_kho.getngayhoadon(this.userId,db,ngaynhap,khId,0);

			query = " delete DONHANG_CTKM_TRAKM_CHITIET where donhang_fk =   " + dhId;
			if (!db.update(query)) {
				return " Lỗi:" + query;

			}

			query = "\n select a.trakmID,a.CTKMID,a.kho_fk as khoId,case npp.DUNGCHUNGKENH  when 1 then 100025 else c.kbh_fk end kbhId "
					+ "\n  ,a.sanpham_fk as spId, b.ten as spTEN, sum(a.soluong) as soluong  "
					+ "\n from DONHANG_CTKM_TRAKM a inner join sanpham b on a.sanpham_fk = b.pk_seq "
					+ "\n inner join donhang c on a.DONHANGID = c.pk_seq "
					+ "\n inner join nhaphanphoi npp on npp.PK_SEQ = c.npp_fk  "
					+ "\n where c.trangthai != 2 and a.DONHANGID in ( " + dhId + " )"
					+ "\n group by a.trakmID,a.CTKMID,a.kho_fk, c.kbh_fk, a.sanpham_fk, b.ten ,npp.DUNGCHUNGKENH   ";
			ResultSet rsKHO = db.get(query);
			while (rsKHO.next()) {
				String khoId = rsKHO.getString("khoId");
				String kbhId = rsKHO.getString("kbhId");
				String spId = rsKHO.getString("spId");
				String trakmID = rsKHO.getString("trakmID");
				String CTKMID = rsKHO.getString("CTKMID");
				String spTEN = rsKHO.getString("spTEN");
				double soluong = rsKHO.getDouble("soluong");

				// TU DE XUAT LO --> KHO CHI TIET THI VAN TRU SO LUONG + AVAI
				query = "\n SELECT kho.soluong,kho.available, kho.SOLO, kho.NGAYHETHAN ,KHO.ngaynhapkho  "
						+ "\n FROM NHAPP_KHO_CHITIET  KHO   " + "\n WHERE  kho.AVAILABLE > 0 and KHO.KHO_FK = " + khoId
						+ " AND KHO.NPP_FK = " + nppId + " " + "\n			and KHO.KBH_FK =   " + kbhId
						+ " and KHO.sanpham_fk = " + spId + "  and kho.ngaynhapkho <='" + ngayhoadon_ + "'  "
						+ "\n ORDER BY  NGAYHETHAN asc, ngaynhapkho asc  ";
				////System.out.println(" chitiet:" + query);
				ResultSet rs = db.get(query);

				String NgayHetHan = "";
				double tongluongxuatCT = 0; // PHAI BAT BUOC TONG LUONG XUAT O KHO CHI TIET PHAI BANG TONG LUONG XUAT O
											// KHO TONG

				double totalLUONG = 0;
				boolean exit = false;
				while (rs.next()) {
					NgayHetHan = rs.getString("NGAYHETHAN");
					String ngaynhapkho = rs.getString("ngaynhapkho");
					double avai = rs.getDouble("AVAILABLE");
					totalLUONG += avai;
					double soluongXUAT = 0;

					if (totalLUONG <= soluong && totalLUONG > 0) {
						soluongXUAT = avai;
					} else {
						soluongXUAT = soluong - (totalLUONG - avai);
						exit = true;
					}

					if (soluongXUAT > 0) {
						String _solo = rs.getString("SOLO");

						query = " insert into  DONHANG_CTKM_TRAKM_CHITIET (KBH_FK,kho_fk,donhang_fk,sanpham_fk,solo,ngayhethan,ngaynhapkho,soluong,CTKM_FK,TRAKM_FK) "
								+ " select " + kbhId + "," + khoId + "," + dhId + ", " + spId + " ,  '" + _solo + "' ,'"
								+ NgayHetHan + "' , '" + ngaynhapkho + "', '" + soluongXUAT + "'," + CTKMID + ","
								+ trakmID;
						if (this.db.updateReturnInt(query) != 1) {
							return " Lỗi:" + query;
						}

						tongluongxuatCT += soluongXUAT;
						if (exit) // DA XUAT DU
						{
							break;
						}
					}
				}

				// if (tongluongxuatCT != soluong)
				if (tongluongxuatCT != soluong) {
					return " Sp KM (" + spTEN
							+ ") số lượng đề xuất trong lô chi tiết theo ngày không còn đủ, vui lòng kiểm tra xuất nhập tồn theo lô để biết thêm chi tiết";
				}
			}
			return "";
		} catch (Exception e) {
			e.printStackTrace();
			return "Exeption" + e.getMessage();

		}

	}

	public static String Check_ngan_sach_sau_luu(String ctkmId, String nppId, String khId, Idbutils db,
			String condition_dh) {
		String sql = "\n select phanbotheodonhang,isnull(SoXuatToiDa,0)SoXuatToiDa "
				+ "\n	from CTKHUYENMAI where pk_seq = '" + ctkmId + "' ";
		int phanbotheodonhang = 0;
		// phanbotheodonhang = 0 ;
		// phanbotheodonhang = 1 // Áp dụng số suất tối đa theo KH

		int SoXuatToiDa = 0; // số suất tối đa từnRg KH
		ResultSet rs = db.get(sql);
		if (rs != null) {
			try {
				if (rs.next()) {

					phanbotheodonhang = rs.getInt("phanbotheodonhang");
					SoXuatToiDa = rs.getInt("SoXuatToiDa");
				}
				rs.close();
			} catch (Exception e) {
				e.printStackTrace();
				return "Exception check ngan sach " + e.getMessage();
			}

		}
		if (phanbotheodonhang == 0)
			return "";
		// LOAINGANSACH 1: gia_tri_su_dung : tiền
		// LOAINGANSACH 2: gia_tri_su_dung : suất

		sql = // "\n select b.scheme , isnull(ssKH.sudungkh,0)dasudungKH " +

				"\n select distinct b.scheme, isnull(ssKH.sosuat,0) as dasudungKH, isnull(B.loaingansach,0)loaingansach, "
						+ "\n case when a.ngansach > 2100000000 then 2100000000 else a.ngansach end as ngansach, B.PHANBOTHEODONHANG, ISNULL(B.soxuattoida, 0) as soxuattoida, "
						+ "\n CASE WHEN ISNULL(B.LOAINGANSACH,0) = '1' THEN "
						+ "\n CASE WHEN ISNULL(B.PHANBOTHEODONHANG, 0) = '0' THEN ISNULL(ssNPP.tonggiatri,0) "
						+ "\n WHEN ISNULL(B.PHANBOTHEODONHANG, 0) = '1' THEN ISNULL(ssNPP.sosuat,0) END "
						+ "\n ELSE 0 END AS dasudungNPP " + "\n from phanbokhuyenmai a "
						+ "\n inner join ctkhuyenmai b on a.ctkm_fk = b.pk_seq " +

						"\n outer apply " + "\n (	" + "\n		select sum(soxuat) sosuat, sum(tonggiatri)tonggiatri "
						+ "\n		from  " + "\n 	("
						+ "\n			select dhkm.donhangId,max(soxuat) soxuat, sum(dhkm.tonggiatri) tonggiatri "
						+ "\n			from donhang_ctkm_trakm dhkm inner join donhang dh on dh.pk_seq = dhkm.donhangId "
						+ "\n			where dh.trangthai !=2 and dh.npp_fk =  a.npp_fk and dhkm.ctkmid = a.ctkm_fk and "
						+ "\n			not exists ( select 1 from Erp_HangTraLaiNpp where trangthai = '1' and donhang_fk = dh.pk_seq) "
						+ "\n			group by dhkm.donhangId " + "\n 	)ss " + "\n )ssNPP	" +

						"\n outer apply " + "\n (	" + "\n		select sum(soxuat) sosuat " + "\n		from  "
						+ "\n 	(" + "\n			select dhkm.donhangId,max(soxuat) soxuat "
						+ "\n			from donhang_ctkm_trakm dhkm inner join donhang dh on dh.pk_seq = dhkm.donhangId "
						+ "\n			where dh.khachhang_fk = '" + khId + "' and b.PHANBOTHEODONHANG = '1' "
						+ "\n   		and dh.trangthai != '2' and dhkm.ctkmid = a.CTKM_FK and not exists (select 1 from erp_hangtralainpp where trangthai = '1' "
						+ "\n			and donhang_fk = dh.pk_seq)  " + "\n			group by dhkm.donhangId "
						+ "\n 	)ss " + "\n )ssKH " +

						/*
						 * "\n outer apply " + "\n (	" + "\n		select sum(soxuat)sudungkh " +
						 * "\n		from  " + "\n 	(" +
						 * "\n			select dhkm.donhangId,max(soxuat) soxuat " +
						 * "\n			from donhang_ctkm_trakm dhkm inner join donhang dh on dh.pk_seq = dhkm.donhangId "
						 * + "\n			where  dh.khachhang_fk =  "
						 * +khId+" and dhkm.ctkmid = a.ctkm_fk and not exists (select 1 from erp_hangtralainpp where trangthai = 1 and donhang_fk = dh.pk_seq) "
						 * + "\n				 " + condition_dh +
						 * "\n			group by dhkm.donhangId " + "\n 	)ss " + "\n )ssKH	" +
						 */
						"\n where a.npp_fk = '" + nppId + "' and a.ctkm_fk = '" + ctkmId + "'  ";

		////System.out.println("sql ngan sach sau lưu : " + sql);

		rs = db.get(sql);
		String scheme = "";

		if (rs != null) {
			try {
				while (rs.next()) {
					double ngansach = 0;
					if (rs.getDouble("ngansach") > 2100000000) {
						ngansach = 2100000000; // CHÍN TRĂM CHÍN MƯƠI CHÍN TRỊU
					} else {
						ngansach = rs.getDouble("ngansach");
					}
					int soxuatKM = 0;
					scheme = rs.getString("scheme");
					int dasudungKH = rs.getInt("dasudungKH");
					int dasudungNPP = rs.getInt("dasudungNPP");
					int soxuattoida = rs.getInt("soxuattoida");
					phanbotheodonhang = rs.getInt("phanbotheodonhang");
					double sosuat_toida_dh = 999999;
					int conlaiKH = 999999;
					double conlaiNPP = 999999;

					boolean cokm = false;
					if (soxuattoida == 0 && rs.getString("loaingansach").equals("0")) {
						cokm = true;
					} else {
						if (soxuattoida > 0) {
							conlaiKH = soxuattoida - dasudungKH;
							sosuat_toida_dh = Math.min(sosuat_toida_dh, conlaiKH);
						}

						if (phanbotheodonhang == 1) {
							conlaiNPP = ngansach - dasudungNPP;
							sosuat_toida_dh = Math.min(sosuat_toida_dh, conlaiNPP);
						} else {
							conlaiNPP = ngansach - dasudungNPP;
						}
						if (sosuat_toida_dh >= 0 && conlaiNPP >= 0) {
							cokm = true;
						}
					}

					if (sosuat_toida_dh >= 0 && cokm) {
						return "";
					} else {
						return "101.Chương trình khuyến mại: " + scheme
								+ ", đã hết ngân sách phân bổ theo khách hàng. Vui lòng áp KM lại! ";
					}
				}
			} catch (SQLException e) {
				e.printStackTrace();
				////System.out.println("__EXCEPTION CHECK NGAN SACH: " + e.getMessage());
				return "201.Chương trình khuyến mại: " + scheme + ", đã hết ngân sách phân bổ";
			}
		}
		return "";

		/*
		 * try { double conlaiKH = 0.0f; double dasudungKH = 0;
		 * 
		 * if (rs.next()) { scheme = rs.getString("scheme");
		 * 
		 * dasudungKH = rs.getDouble("dasudungKH"); if(phanbotheodonhang == 1) conlaiKH
		 * = SoXuatToiDa - dasudungKH;
		 * 
		 * rs.close(); }
		 * 
		 * 
		 * ////System.out.println("ngansachKH = " + SoXuatToiDa);
		 * ////System.out.println("dasudungKH = " + dasudungKH);
		 * 
		 * if (phanbotheodonhang > 0 && conlaiKH < 0 ) { return
		 * "101.Chương trình khuyến mại: " + scheme +
		 * ", đã hết ngân sách phân bổ theo khách hàng "; }
		 * 
		 * 
		 * } catch (Exception e) { e.printStackTrace();
		 * ////System.out.println("__EXCEPTION CHECK NGAN SACH: " + e.getMessage()); return
		 * "201.Chương trình khuyến mại: " + scheme + ", đã hết ngân sách phân bổ"; }
		 * return "";
		 */
	}

	private Object[] appendObjectArrayValue(Object[] obj, Object[] newObj) {
		ArrayList<Object> temp = new ArrayList<Object>(Arrays.asList(obj));
		for (int i = 0; i < newObj.length; i++) {
			temp.add(newObj[i]);
		}
		return temp.toArray();
	}
	public String  revertDonhang(String dhId) throws SQLException {
		geso.dms.center.util.Utility util_kho = new geso.dms.center.util.Utility();
		//Revert tổng
	String	query = "\n SELECT KHO_FK, KBH_FK, NPP_FK, SANPHAM_FK, SUM(SOLUONG) AS SOLUONG " +  
		"\n FROM " +
		"\n ( " +
		"\n     SELECT C.NPP_FK, C.KHO_FK, C.KBH_FK, B.PK_SEQ AS SANPHAM_FK, SUM(A.SOLUONG) AS SOLUONG " +
		"\n     FROM DONHANG_SANPHAM A INNER JOIN SANPHAM B ON A.SANPHAM_FK = B.PK_SEQ INNER JOIN DONHANG C ON A.DONHANG_FK = C.PK_SEQ " +  
		"\n     WHERE C.TRANGTHAI != 2 AND A.DONHANG_FK = " + dhId +  
		"\n     GROUP BY C.NPP_FK, C.KHO_FK, C.KBH_FK, B.PK_SEQ, B.MA " +  
		"\n     UNION ALL " +
		"\n     SELECT E.NPP_FK, A.KHO_FK, E.KBH_FK, D.PK_SEQ AS SANPHAM_FK, SUM(A.SOLUONG) AS SOLUONG " +  
		"\n     FROM DONHANG_CTKM_TRAKM A INNER JOIN CTKHUYENMAI B ON A.CTKMID = B.PK_SEQ " +
		"\n     INNER JOIN SANPHAM D ON A.SPMA = D.MA INNER JOIN DONHANG E ON A.DONHANGID = E.PK_SEQ " +  
		"\n     WHERE E.TRANGTHAI != 2 AND A.SPMA IS NOT NULL AND A.DONHANGID = " + dhId +
		"\n     GROUP BY E.NPP_FK, A.KHO_FK, E.KBH_FK,  A.SPMA, D.PK_SEQ " +  
		"\n ) TX " +
		"\n GROUP BY KHO_FK, KBH_FK, NPP_FK, SANPHAM_FK ";	
		ResultSet rskho = db.get(query);
		while (rskho.next()) {
			String khoid = rskho.getString("kho_fk");
			String KBHid = rskho.getString("KBH_FK");
			String NPpid = rskho.getString("NPP_FK");
			String spid = rskho.getString("SANPHAM_FK");
			double soluong = rskho.getDouble("SOLUONG");

			String msg1 = util_kho.Update_NPP_Kho_Sp("", "Xoá đơn hàng DonhangSvl"+dhId, db, khoid, spid, NPpid, KBHid, 0, soluong*(-1), soluong, 0);
			if (msg1.length() >0) {
				msg = "Lỗi khi xoá đơn hàng 1.";
				db.getConnection().rollback();
				
			}
		}
		rskho.close();

		//Revert chi tiết
		query = "\n SELECT KHO_FK, KBH_FK, NPP_FK, SANPHAM_FK, SOLO, NGAYHETHAN, NGAYNHAPKHO, SUM(SOLUONG) AS SOLUONG " +  
		"\n FROM " +
		"\n ( " +
		"\n     SELECT C.NPP_FK, C.KHO_FK, C.KBH_FK, B.PK_SEQ AS SANPHAM_FK, " +
		"\n         A.SOLO,A.NGAYHETHAN, A.NGAYNHAPKHO, SUM(A.SOLUONG) AS SOLUONG " +
		"\n     FROM DONHANG_SANPHAM_CHITIET A " +
		"\n     INNER JOIN SANPHAM B ON A.SANPHAM_FK = B.PK_SEQ " + 
		"\n     INNER JOIN DONHANG C ON A.DONHANG_FK = C.PK_SEQ " +
		"\n     WHERE C.TRANGTHAI != 2 AND A.DONHANG_FK = " + dhId +
		"\n     GROUP BY C.NPP_FK, C.KHO_FK, C.KBH_FK, B.PK_SEQ, B.MA, A.SOLO, A.NGAYHETHAN, A.NGAYNHAPKHO " +
		"\n     UNION ALL " +
		"\n     SELECT E.NPP_FK, A.KHO_FK, E.KBH_FK, D.PK_SEQ AS SANPHAM_FK, " +
		"\n         A.SOLO, A.NGAYHETHAN, A.NGAYNHAPKHO, SUM(A.SOLUONG) AS SOLUONG " +  
		"\n     FROM DONHANG_CTKM_TRAKM_CHITIET A " +
		"\n     INNER JOIN CTKHUYENMAI B ON A.CTKM_FK = B.PK_SEQ " +
		"\n     INNER JOIN SANPHAM D ON A.SANPHAM_FK = D.PK_SEQ " +
		"\n     INNER JOIN DONHANG E ON A.DONHANG_FK = E.PK_SEQ " +  
		"\n     WHERE E.TRANGTHAI != 2 AND A.SANPHAM_FK IS NOT NULL AND A.DONHANG_FK = " + dhId + 
		"\n     GROUP BY E.NPP_FK, A.KHO_FK, E.KBH_FK, A.SANPHAM_FK, D.PK_SEQ, A.SOLO, A.NGAYHETHAN, A.NGAYNHAPKHO " +
		"\n ) TX " +
		"\n GROUP BY KHO_FK, KBH_FK, NPP_FK, SANPHAM_FK,SOLO, NGAYHETHAN, NGAYNHAPKHO ";
		rskho = db.get(query);
		while (rskho.next()) {
			String khoid = rskho.getString("kho_fk");
			String KBHid = rskho.getString("KBH_FK");
			String NPpid = rskho.getString("NPP_FK");
			String spid = rskho.getString("SANPHAM_FK");
			double soluong = rskho.getDouble("SOLUONG");
			String solo = rskho.getString("SOLO");
			String ngayhethan = rskho.getString("NGAYHETHAN");
			String NGAYNHAPKHO = rskho.getString("NGAYNHAPKHO");

			String msg1=util_kho.Update_NPP_Kho_Sp_Chitiet("", "Xoá đơn hàng chi tiết Donhangsvl"+dhId, db, khoid, spid, NPpid, KBHid, solo, ngayhethan, NGAYNHAPKHO, 0, soluong*(-1), soluong, soluong, 0) ;
			if (msg1.length() >0) {
				msg = "Lỗi khi xoá đơn hàng 2.";
				db.getConnection().rollback();
			
			}
		}
		rskho.close();
		return msg;
	}
	public String capNhatKM(String id, String nppId, String khId, String trangthai, dbutils db) {
		try {

			// delete neu ton tai, cap nhat lai kho voi so luong tang
			String query = "delete from donhang_ctkm_trakm where donhangid = '" + id + "' ";

			////System.out.println(query);
			if (!db.update(query)) {
				return "Error :" + query;
			}
			// delete neu ton tai, cap nhat lai kho voi so luong tang
			query = "delete from DONHANG_CTKM_TRAKM_CHITIET where DONHANG_FK = '" + id + "'  ";

			////System.out.println(query);
			if (!db.update(query)) {
				return "Error :" + query;
			}

			query = "delete from donhang_ctkm_dkkm where DONHANG_FK = '" + id + "'  ";

			////System.out.println(query);
			if (!db.update(query)) {
				return "Error :" + query;
			}

			return "";
		} catch (Exception e1) {
			e1.printStackTrace();
			return "Error :" + e1.toString();
		}
	}

	@Override
	public String CreateKhuyenmai(ICtkhuyenmai ctkm, String id, String nppId, String tungay, long tongGtridh,
			String khId, Hashtable<String, Integer> sp_sl, Hashtable<String, Float> sp_dg, dbutils db) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String CreateKhuyenmaiDhDxk(List<ICtkhuyenmai> ctkmList, String id, String nppId, long tongGtridh,
			String khId, dbutils db) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean CheckDonHangDKM(List<ISanpham> spList, String id, String khId, dbutils db) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public XLkhuyenmai getXLkhuyenmai(String userId, String ngaygd, String khId, String donhangId, String IsDonHangLe) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public XLkhuyenmaiDonhangDXK getXLkhuyenmaiDXK(String nppId, String id, String ngaygd, String[] ctkm, String khId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String saveTRAKHUYENMAI(IDonhang dhBean, HttpServletRequest request) {
		// TODO Auto-generated method stub
		return null;
	}

	public double getTien_KM_Sanpham(String spMa) {
		double tien = 0;
		if (this.id != null && this.id.length() > 0) {
			String query = " select  dhsp.tienkhuyenmai  from DONHANG_SANPHAM dhsp inner join sanpham sp on sp.PK_SEQ = dhsp.SANPHAM_FK where sp.ma = '"
					+ spMa + "' and  dhsp.tienkhuyenmai  is not null and dhsp.DONHANG_FK =  " + this.id;
			ResultSet rs = db.get(query);
			try {
				if (rs.next()) {
					tien = rs.getDouble("tienkhuyenmai");
				}
				rs.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return tien;

	}

	public static String TRU_KHO_RUT_GON(Idbutils db, String vitriBAM, String dhId, String ngayhoadon_) {
		try {

			geso.dms.center.util.Utility util_kho = new geso.dms.center.util.Utility();
			ResultSet rs;
			String query = " delete DONHANG_SANPHAM_chitiet where donhang_fk =   " + dhId;
			if (!db.update(query)) {
				return " Lỗi:" + query;
			}
			query = " delete DONHANG_CTKM_TRAKM_CHITIET where donhang_fk =   " + dhId;
			if (!db.update(query)) {
				return " Lỗi:" + query;
			}

			// de xuat lo book khi chi tiet
			query = "\n select c.npp_fk,0 trakmID,0 CTKMID,1 loai,c.kho_fk as khoId, " +
			// "c.kbh_fk as kbhId, " +
					" case npp.DUNGCHUNGKENH  when 1 then 100025 else c.kbh_fk end kbhId, "
					+ " b.pk_seq as spId, b.ten as spTEN, sum(a.soluong) as soluong "
					+ "\n from donhang_sanpham a inner join sanpham b on a.sanpham_fk = b.pk_seq "
					+ " inner join donhang c on a.donhang_fk = c.pk_seq   "
					+ "\n inner join nhaphanphoi npp on npp.PK_SEQ = c.npp_fk  " + "\n where a.donhang_fk in ( " + dhId
					+ " )  " + "\n group by c.npp_fk,c.kho_fk, c.kbh_fk, b.pk_seq, b.ten, npp.DUNGCHUNGKENH "
					+ "\n union all" + "\n select c.npp_fk,a.trakmID,a.CTKMID,2,ctkm.kho_fk as khoId, "
					+ " case npp.DUNGCHUNGKENH  when 1 then 100025 else c.kbh_fk end kbhId, "
					+ " b.pk_seq as spId, b.ten as spTEN, sum(a.soluong) as soluong  "
					+ "\n from DONHANG_CTKM_TRAKM a inner join sanpham b on a.SPMA = b.ma "
					+ " inner join donhang c on a.DONHANGID = c.pk_seq "
					+ "\n inner join nhaphanphoi npp on npp.PK_SEQ = c.npp_fk  "
					+ "\n inner join CTKHUYENMAI ctkm on ctkm.PK_SEQ = a.CTKMID " + "\n where  a.DONHANGID in ( " + dhId
					+ " )"
					+ "\n group by c.npp_fk,a.trakmID,a.CTKMID,ctkm.kho_fk, c.kbh_fk, b.pk_seq, b.ten, npp.DUNGCHUNGKENH  ";

			boolean datrukho = false;
			ResultSet rsKHO = db.get(query);
			while (rsKHO.next()) {
				String nppId = rsKHO.getString("npp_fk");
				String khoId = rsKHO.getString("khoId");
				String kbhId = rsKHO.getString("kbhId");
				String spId = rsKHO.getString("spId");
				String trakmID = rsKHO.getString("trakmID");
				String CTKMID = rsKHO.getString("CTKMID");
				String spTEN = rsKHO.getString("spTEN");
				double soluong = rsKHO.getDouble("soluong");
				int isnhapkhau = 1;
				String loai = rsKHO.getString("loai");

				// TU DE XUAT LO --> KHO CHI TIET THI VAN TRU SO LUONG + AVAI
				query = "\n SELECT kho.soluong, kho.available, kho.SOLO, kho.NGAYHETHAN ,KHO.ngaynhapkho  "
						+ "\n FROM NHAPP_KHO_CHITIET  KHO   " + "\n inner join donhang dhx on dhx.pk_seq = " + dhId
						+ "\n inner join Nhaphanphoi npp on npp.pk_seq = kho.npp_fk "
						+ "\n INNER JOIN SANPHAM SP ON SP.PK_SEQ=KHO.SANPHAM_FK  "
						+ "\n WHERE  kho.AVAILABLE > 0 and KHO.KHO_FK = " + khoId + "  " + "\n AND KHO.NPP_FK = "
						+ nppId + " "
						+ "\n	and KHO.KBH_FK = case npp.DUNGCHUNGKENH  when 1 then 100025 else (select kbh_fk from khachhang where pk_Seq = dhx.khachhang_fk ) end  "
						+ "\n 	and SP.pk_seq=" + spId + "  and kho.ngaynhapkho <='" + ngayhoadon_ + "'  " + // ngayhoadon_
						"\n ORDER BY  kho.NGAYHETHAN asc, kho.ngaynhapkho asc  ";
				////System.out.println(" chitiet:" + query);
				rs = db.get(query);

				String NgayHetHan = "";
				double tongluongxuatCT = 0; // PHAI BAT BUOC TONG LUONG XUAT O KHO CHI TIET PHAI BANG TONG LUONG XUAT O
											// KHO TONG

				double totalLUONG = 0;
				boolean exit = false;
				//
				while (rs.next()) {
					NgayHetHan = rs.getString("NGAYHETHAN");
					String ngaynhapkho = rs.getString("ngaynhapkho");
					double avai = rs.getDouble("AVAILABLE");
					totalLUONG += avai;
					double soluongXUAT = 0;

					if (totalLUONG <= soluong && totalLUONG > 0) {
						soluongXUAT = avai;
					} else {
						soluongXUAT = soluong - (totalLUONG - avai);
						exit = true;
					}

					if (soluongXUAT > 0) {
						String _solo = rs.getString("SOLO");

						String msg1 = util_kho.Update_NPP_Kho_Sp(ngayhoadon_,
								"DuyetDonHang_trukho_tructiep(" + vitriBAM + ")" + dhId, db, khoId, spId, nppId, kbhId,
								(-1) * soluongXUAT, 0, (-1) * soluongXUAT, 0);// giảm so luong,giam avai
						if (msg1.length() > 0) {
							return msg1;
						}
						msg1 = util_kho.Update_NPP_Kho_Sp_Chitiet(ngayhoadon_,
								"DuyetDonHang_trukho_tructiep(" + vitriBAM + ")_" + dhId, db, khoId, spId, nppId, kbhId,
								_solo, NgayHetHan, ngaynhapkho, (-1) * soluongXUAT, 0, (-1) * soluongXUAT,
								(-1) * soluongXUAT, 0);
						if (msg1.length() > 0) {
							return msg1;

						}

						if (loai.equals("1"))
							query = " insert into  DONHANG_SANPHAM_chitiet (donhang_fk,sanpham_fk,kbh_fk,kho_fk,solo,ngayhethan,ngaynhapkho,soluong,trangthai,npp_fk)"
									+ " select " + dhId + ", '" + spId + "', '" + kbhId + "','" + khoId + "', '" + _solo
									+ "','" + NgayHetHan + "','" + ngaynhapkho + "','" + soluongXUAT + "',0,'" + nppId
									+ "' ";
						else
							query = " insert into  DONHANG_CTKM_TRAKM_CHITIET (kbh_fk,kho_fk,donhang_fk,sanpham_fk,solo,ngayhethan,ngaynhapkho,soluong,CTKM_FK,TRAKM_FK) "
									+ " select " + kbhId + "," + khoId + "," + dhId + ", " + spId + " ,  '" + _solo
									+ "' ,'" + NgayHetHan + "' , '" + ngaynhapkho + "', '" + soluongXUAT + "'," + CTKMID
									+ "," + trakmID;
						if (db.updateReturnInt(query) != 1) {
							return " Lỗi:" + query;
						}

						tongluongxuatCT += soluongXUAT;
						if (exit) // DA XUAT DU
						{
							break;
						}
					}
				}

				// if (tongluongxuatCT != soluong)
				if (tongluongxuatCT != soluong) {
					String addStr = "";
					if (loai.equals("1"))
						addStr = " Sp hàng bán (" + spTEN + ")";
					else
						addStr = " Sp KM (" + spTEN + ")";

					rsKHO.close();
					return addStr
							+ ": số lượng đề xuất trong lô chi tiết theo ngày không còn đủ, vui lòng kiểm tra xuất nhập tồn theo lô để biết thêm chi tiết";

				}
				datrukho = true;
			}
			if (!datrukho) {
				return "Không thể trừ kho đơn hàng ";

			}

			return "";
		} catch (Exception e) {
			e.printStackTrace();
			return "Exception tru kho :" + e.getMessage();
		}
	}
	
	
	public ResultSet getPhanboKM()
	{
		if(this.id != null && this.id.length() > 0)
		{
			String query = " select sp.ma, isnull(tienkhuyenmai,0)tienkhuyenmai\r\n" + 
					"from DONHANG_SANPHAM a inner join sanpham sp on sp.PK_SEQ = a.SANPHAM_FK where a.DONHANG_FK ="+this.id+" \r\n" ;
			////System.out.println("query ="+ query);
			return db.get(query);
		}
		return null;
	}
}
