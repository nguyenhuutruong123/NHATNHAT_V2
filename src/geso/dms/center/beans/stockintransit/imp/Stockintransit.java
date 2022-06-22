
package geso.dms.center.beans.stockintransit.imp;

 import geso.dms.center.beans.stockintransit.IStockintransit;
import geso.dms.center.db.sql.dbutils;
import geso.dms.center.db.sql.dbutils_syn;
import geso.dms.center.util.Utility;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Hashtable;

import javax.servlet.ServletOutputStream;
import javax.swing.table.DefaultTableModel;

import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.BaseFont;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

public class Stockintransit  implements IStockintransit
{
	String xemtheo;
	String Ctkmtichluy;
	ResultSet Rskmtl;
	String phanloai;
	String userId;
	String nppId= "";	
	String nppTen;
	String khId;
	String covat;
	String khTen;
	String kenhId;
	String dvkdId;
	String nhanhangId;
	String chungloaiId;
	String tungay  = "";
	String denngay =  "";
	String userTen;
	String khoId;
	String book;
	String unghang;
	String dhchuachot;
	ResultSet kho;
	String msg;
	ResultSet kenh;
	ResultSet dvkd;
	ResultSet nhanhang;
	ResultSet tinhthanh;
	
	String qhId, phuongxa;
	ResultSet qh;
	ResultSet px;
	
	ResultSet chungloai;
	String vungId;
	String khuvucId;
	ResultSet khuvuc;
	ResultSet vung;
	ResultSet npp;
	String vat;
	String gsbhId;
	String Ngayketthucctkm;

	ResultSet gsbh;
	String asmId;
	ResultSet asm;
	String bmId;
	ResultSet bm;
	String sanphamId;
	ResultSet sanpham;
	String dvdlId;
	String[] FieldShow;
	String[] FieldHidden;
	String ngayton;
	String promotion;
	String discount;
	ResultSet dvdl;
	String lessday;
	String moreday;
	dbutils db;
	String year;
	String month;
	String QuyCachId;
	String ddkd;
	ResultSet rsddkd;
	ResultSet khrs;
	String trungtam; 
	ResultSet rsnppupload;
	
	//
	String unit;
	String groupCus;
	//
	String programsId;
	ResultSet rsPrograms;

	String donviTinh;
	String HangDiDuong;



	String fromMonth;
	String toMonth;
	String type;
	String ToYear = "";
	String FromYear = "";
	String Nhospid = "";
	ResultSet RsNhomSp;
	ResultSet RsQuyCach;

	ResultSet Rshangcuahang;

	ResultSet Rsloaicuahang;
	ResultSet RsVitricuahang;
	ResultSet RsTinhthanh;
	ResultSet RsQuanhuyen;

	ResultSet nhomRs;

	String hangcuahangid;
	String loaicuahangid;
	String vitricuahangid;
	String tinhthanhid;
	String quanhuyenid;
	String muclay;
	String nppID;

	ResultSet nrs;
	String nspIds;
	ResultSet tieuchiRs;
	ResultSet nppRs;

	float CONVERT = 28.346457f; // = 1cm

	String tieuchiId;

	String nganhHangId;
	ResultSet ttRs;
	String ttId;
	ResultSet nganhHangRs;

	// chỉnh sửa
	String activeTab;
	ResultSet EtcRs;
	ResultSet HdKhacRs;
	ResultSet OtcRs;
	ResultSet kmRs;
	String nhomhangid;
	ResultSet nhomhangRs;
	
	
	String nvgnId;

	String cn1;

	String theohd;
	String layluongchuyenkho;
	ResultSet rsnppID;
	String nppids;
	String dophumien;
	
	DefaultTableModel tableDbRS ;
	String tableDbName= "";
	DefaultTableModel dataDbRs;
	String tctctId ="";
	String diengiaiTctctId ="";
	String tieuchi ="";
	ResultSet tctctRs;
	
	String namTruoc = "";
	String namHienTai ="" ;
	String view = "";
	
	String lon_hon_0 = "";
	public String getLon_hon_0() {
		return lon_hon_0;
	}
	public void setLon_hon_0(String lon_hon_0) {
		this.lon_hon_0 = lon_hon_0;
	}
	
	public String getView() {
		return view;
	}
	public void setView(String view) {
		this.view = view;
	}
	public String getDophumien() {
		return dophumien;
	}

	public void setDophumien(String dophumien) {
		this.dophumien = dophumien;
	}

	public String getNppids() {
		return nppids;
	}

	public void setNppids(String nppids) {
		this.nppids = nppids;
	}

	public ResultSet getRsnppID() {
		return rsnppID;
	}

	public void setRsnppID(ResultSet rsnppID) {
		this.rsnppID = rsnppID;
	}

	public String getLayluongchuyenkho() {
		return layluongchuyenkho;
	}

	public void setLayluongchuyenkho(String layluongchuyenkho) {
		this.layluongchuyenkho = layluongchuyenkho;
	}

	public String getTheohd() {
		return theohd;
	}

	public void setTheohd(String theohd) {
		this.theohd = theohd;
	}
	ResultSet Khors;
	public ResultSet getKhors() {
		return Khors;
	}

	public void setKhors(ResultSet khors) {
		Khors = khors;
	}

	public String getCn1() {
		return cn1;
	}

	public void setCn1(String cn1) {
		this.cn1 = cn1;
	}
	String khoid;
	public String getKhoid() {
		return khoid;
	}

	public void setKhoid(String khoid) {
		this.khoid = khoid;
	}
	String tenkho;

	public String getTenkho() {
		return tenkho;
	}

	public void setTenkho(String tenkho) {
		this.tenkho = tenkho;
	}

	public Stockintransit()
	{
		this.qhId = "";
		this.phuongxa = "";
		this.trungtam="";
		this.cn1="0";
		this.userId = "";
		this.xemtheo = "";
		this.nppId = "";
		this.nppTen = "";
		this.dhchuachot = "";
		this.kenhId = "";
		this.dvkdId = "";
		this.nhanhangId = "";
		this.chungloaiId = "";
		this.tungay = "";
		this.denngay = "";
		this.userTen = "";
		this.khoId = "";
		this.book = "";
		this.covat = "0";
		this.msg = "";
		this.vungId = "";
		this.khuvucId = "";
		this.vat = "";
		this.gsbhId = "";
		this.asmId = "";
		this.bmId = "";
		this.sanphamId = "";
		this.dvdlId = "";
		this.ngayton = "1";
		this.promotion = "0";
		this.Ngayketthucctkm="";
		this.discount = "0";
		this.lessday = "0";
		this.moreday = "0";
		this.ddkd = "";
		this.unit = "";
		this.groupCus = "";
		this.programsId = "";
		this.donviTinh = "";
		this.month = "";
		this.year = "";
		this.fromMonth = "";
		this.toMonth = "";
		this.unghang = "0";
		this.type = "0";
		this.phanloai = "";
		this.Ctkmtichluy = "";
		this.khId="";
		this.ttId="";
		this.coHoadon = "";
		this.spId = "";
		this.laytheo = "0";

		this.nspIds = "";
		this.year = getDate().substring(0, 4);
		this.FromYear = this.year;
		this.ToYear = this.year;
		this.toMonth = getDate().substring(5, 7);

		////System.out.println("11.Month la: " + this.toMonth);
		this.db = new dbutils();

		hangcuahangid = "";
		loaicuahangid = "";
		vitricuahangid = "";
		tinhthanhid = "";
		quanhuyenid = "";
		this.muclay = "";
		this.dvdlId = "";
		this.HangDiDuong="0";
		this.nganhHangId="";
		this.HoaDonKmDb="";
		this.tbhId="";
		this.nppID="";
		this.activeTab="0";
		this.nvgnId="";
		loaiNv="";
		this.trangthai="";
		this.tructhuoc_fk="";

		this.cndt="1";
		this.kh="1";
		this.coHoadon = "";
		this.spId = "";
		this.laytheo = "0";
		this.quy="";
		this.cn1="0";
		this.theohd="1";
		this.layluongchuyenkho="1";
		this.nppids="";
		this.dophumien="";

	}

	public String getTinhthanhid()
	{
		return tinhthanhid;
	}

	public void setTinhthanhid(String tinhthanhid)
	{
		this.tinhthanhid = tinhthanhid;
	}

	public void setuserId(String userId)
	{

		this.userId = userId;
	}

	public String getuserId()
	{

		return this.userId;
	}

	public void setnppId(String nppId)
	{

		this.nppId = nppId;
	}

	public String getnppId()
	{

		return this.nppId;
	}

	public void setnppTen(String nppTen)
	{

		this.nppTen = nppTen;
	}

	public String getnppTen()
	{

		return this.nppTen;
	}

	public void setkenhId(String kenhId)
	{

		this.kenhId = kenhId;
	}

	public String getkenhId()
	{

		return this.kenhId;
	}

	public void setdvkdId(String dvkdId)
	{

		this.dvkdId = dvkdId;
	}

	public String getdvkdId()
	{

		return this.dvkdId;
	}

	public void setASMId(String asmId)
	{

		this.asmId = asmId;
	}

	public String getASMId()
	{

		return this.asmId;
	}

	public void setBMId(String bmId)
	{

		this.bmId = bmId;
	}

	public String getBMId()
	{

		return this.bmId;
	}

	public void setnhanhangId(String nhanhangId)
	{

		this.nhanhangId = nhanhangId;
	}

	public String getnhanhangId()
	{

		return this.nhanhangId;
	}

	public void setchungloaiId(String chungloaiId)
	{

		this.chungloaiId = chungloaiId;
	}

	public String getchungloaiId()
	{

		return this.chungloaiId;
	}

	public void setkenh(ResultSet kenh)
	{

		this.kenh = kenh;
	}

	public ResultSet getkenh()
	{
		//return this.kenh;
		this.kenh = this.db.get(" select pk_seq,ten,diengiai from kenhbanhang ");
		return this.kenh;
	}

	public void setdvkd(ResultSet dvkd)
	{

		this.dvkd = dvkd;
	}

	public ResultSet getdvkd()
	{
		this.dvkd = db.get("select pk_seq,diengiai from donvikinhdoanh ");
		return this.dvkd;
	}

	public void setASM(ResultSet asm)
	{

		this.asm = asm;
	}

	public ResultSet getASM()
	{
		String sql = "SELECT ASM.PK_SEQ, ASM.TEN FROM ASM ASM WHERE ASM.TRANGTHAI ='1' ";
		if (this.kenhId.length() > 0) { sql = sql + " AND ASM.KBH_FK ='" + this.kenhId + "'"; } 
		else if (this.dvkdId.length() > 0) { sql = sql + " AND ASM.DVKD_FK ='" + this.dvkdId + "'"; } 
		else if (this.khuvucId.length() > 0) { sql = sql + " AND ASM.PK_SEQ IN ( SELECT asm_fk FROM ASM_KHUVUC WHERE khuvuc_Fk= '" + this.khuvucId + "' ) "; } 
		else if (this.vungId.length() > 0) { sql = sql + " AND ASM.PK_SEQ in  ( SELECT asm_fk FROM ASM_KHUVUC WHERE khuvuc_Fk in (select pk_Seq from KhuVuc where vung_Fk='"+this.vungId+"' ) ) "; }
		this.asm = db.get(sql);
		return this.asm;
	}

	public void setBM(ResultSet bm)
	{

		this.bm = bm;
	}

	public ResultSet getBM()
	{
		String sql = " SELECT BM.PK_SEQ, BM.TEN  " + "FROM BM BM " + "INNER JOIN BM_CHINHANH BM_CHINHANH ON BM_CHINHANH.BM_FK = BM.PK_SEQ " + "INNER JOIN VUNG VUNG ON VUNG.PK_SEQ = BM_CHINHANH.VUNG_FK " + "WHERE BM.TRANGTHAI ='1'";
		if (this.kenhId.length() > 0) { sql = sql + " AND BM.KBH_FK ='" + this.kenhId + "'"; } 
		else if (this.dvkdId.length() > 0) { sql = sql + " AND BM.DVKD_FK ='" + this.dvkdId + "'"; } 
		else if (this.vungId.length() > 0) { sql = sql + " AND VUNG.PK_SEQ = '" + this.vungId + "'"; }
		this.bm = db.get(sql);
		return this.bm;
	}

	public void setnhanhang(ResultSet nhanhang)
	{

		this.nhanhang = nhanhang;
	}

	public ResultSet getnhanhang()
	{
		if (this.dvkdId.length() > 0) {	this.nhanhang = db.get("select * from nhanhang where dvkd_fk ='" + this.dvkdId + "'"); }
		else { this.nhanhang = db.get("select * from nhanhang "); }
		return this.nhanhang;
	}

	public void setchungloai(ResultSet chungloai)
	{

		this.chungloai = chungloai;
	}

	public ResultSet getchungloai()
	{
		String sql = "";
		if(this.nhanhangId.length() > 0)
		{	sql = " select cl.pk_Seq,ten from chungloai cl " +
				  " inner join nhanhang_chungloai nhcl on nhcl.cl_fk = cl.pk_seq " +
				  " where nh_fk = '"+ this.nhanhangId +"' ";
		} else {	sql = "select pk_Seq,ten  from chungloai"; }
		this.chungloai = db.get(sql);
		return this.chungloai;
	}

	public void setMonth(String month)
	{

		this.month = month;
	}

	public String getMonth()
	{
		if (this.month.length() > 0)
		{
			return this.month;
		} else
		{
			return this.getDate().substring(5, 7);
		}

	}

	public void setUnghang(String unghang)
	{
		this.unghang = unghang;
	}

	public String getUnghang()
	{
		return this.unghang;
	}
	
	@Override
	public String getDHChuachot() {
		return this.dhchuachot;
	}
	
	@Override
	public void setDHChuachot(String dhchuachot) {
		this.dhchuachot = dhchuachot;
	}

	public void setYear(String year)
	{
		this.year = year;
	}

	public String getYear()
	{
		if (this.year.length() > 0)
		{
			return this.year;
		} else
		{
			return this.getDate().substring(0, 4);
		}
	}

	public void createKhRS1()
	{
		Utility ut = new Utility();
		this.nppId = ut.getIdNhapp(userId);

		//NPP
		if (this.nppId!=null)
		{
			// Dai dien kinh doanh				
			String query="SELECT pk_seq,ten FROM DAIDIENKINHDOANH d where trangthai=1 ";
			query+= " and NPP_FK='"+nppId+"'";

			if(this.phanloai.equals("2")){
				query+= " and pk_Seq in " + ut.Quyen_Ddkd(userId)+"";
			}
			this.setRsddkd(db.get(query));
			//GET KHACH HANG

			query ="select distinct  kh.PK_SEQ, kh.MaFast + ' - ' + kh.Ten as Ten" +
			"	from KHACHHANG kh inner join KHACHHANG_TUYENBH kh_tbh on kh.PK_SEQ=kh_tbh.KHACHHANG_FK "+
			" inner join TUYENBANHANG tbh on tbh.PK_SEQ=kh_tbh.TBH_FK" +
			" where 1=1 and kh.TrangThai ='1'";

			if(this.ddkd.length()>0)
			{
				query += " and tbh.DDKD_FK ='"+ddkd+"'";
			}
			if(nppId.trim().length()>0)
			{
				query+= " and kh.NPP_FK='"+nppId+"'";
			}

			//System.out.println("cau kh:" + query);
			this.khrs =  this.db.get( query);

			query="select PK_SEQ,TEN ,DIACHI from nhanviengiaonhan where 1=1  ";
			if(this.nppId.length()>0)
			{
				query+=" and npp_fk='"+nppId+"' ";
			}
			if(this.phanloai.equals("2"))
			{
				query+= " and npp_fk in " + ut.quyen_npp(userId)+"";
			}
			this.nvgnRs=this.db.get(query);

		} 
		else//TRUNG TAM
		{
			//GET NHA PHAN PHOI
			String query="";
			query="select pk_Seq,ten,ma from nhaphanphoi where loainpp in(4,5) and trangthai=1 and pk_Seq in (" + ut.quyen_npp(userId)+")";

			if(this.ttId.length()>0)
			{
				query+=" and tinhthanh_fk='"+this.ttId+"' ";
			}

			this.nppRs = this.db.get(query);


			//GET DAI DIEN KINH DOANH
			query="SELECT pk_seq,ten FROM DAIDIENKINHDOANH d where trangthai=1 ";

			if(this.phanloai.equals("2")){
				query+= " and pk_Seq in " + ut.Quyen_Ddkd(userId)+"";
			}

			if(nppID.trim().length()>0){
				query+= " and NPP_FK='"+nppID+"'";
			}

			this.setRsddkd(db.get(query));

			//GET KHACH HANG
			query ="select distinct  kh.PK_SEQ, kh.MaFast + ' - ' +kh.Ten as Ten" +
			"	from KHACHHANG kh inner join KHACHHANG_TUYENBH kh_tbh on kh.PK_SEQ=kh_tbh.KHACHHANG_FK "+
			" inner join TUYENBANHANG tbh on tbh.PK_SEQ=kh_tbh.TBH_FK" +
			" where 1=1 and kh.TrangThai ='1'";

			if(this.ddkd.length()>0)
			{
				query += " and tbh.DDKD_FK ='"+ddkd+"'";
			}
			if(nppID.trim().length()>0)
			{
				query+= " and kh.NPP_FK='"+nppID+"'";
			}

			//System.out.println("cau kh:" + query);
			this.khrs =  this.db.get( query);
		}
	}

	String loaiNv="";
	String loaiUser= "";
	String tructhuoc_fk="";

	String cttbId= "";
	ResultSet cttbRs ;

	String loaiMenu="";
	public String getLoaiMenu() {
		return loaiMenu;
	}
	public void setLoaiMenu(String loaiMenu) {
		this.loaiMenu = loaiMenu;
	}
	
	
	public String getCttbId() {
		return cttbId;
	}
	public void setCttbId(String cttbId) {
		this.cttbId = cttbId;
	}
	public ResultSet getCttbRs() {
		Utility u = new Utility();
		
		String quyennpp = "";
		////System.out.println("NppId: "+nppId);
		if(this.nppId.trim().length() >0)
			quyennpp = "and tbnpp.npp_fk =  "+ this.nppId;
		else
			quyennpp = "and tbnpp.npp_fk in  "+ u.quyen_npp(this.userId);
			
			
		String query =  "select  distinct pk_seq ,diengiai \r\n" + 
				"from CTTRUNGBAY cttb inner join cttb_npp tbnpp on cttb.pk_seq=tbnpp.cttb_fk\r\n" + 
				"where 1=1  " + quyennpp + 
				"order by pk_seq desc \r\n";
		////System.out.println("CTTBRS: "+query);
		this.cttbRs = db.get(query);
		return cttbRs;
	}

	public String getTructhuoc_fk()
	{
		return tructhuoc_fk;
	}

	public void setTructhuoc_fk(String tructhuoc_fk)
	{
		this.tructhuoc_fk = tructhuoc_fk;
	}
	
	public void initDongBo()
	{
		try
		{
			String query  =" SELECT N'KeXuatDuLieu_KT_CN' TABLE_NAME union all SELECT N'KeXuatDuLieu_KT_CongTy' TABLE_NAME   ";
			tableDbRS = Utility.getDefaultTableDB_Syns(query);
			
			if(this.tungay.trim().length() <= 0)
				this.tungay = Utility.getNgayHienTai();
			if(this.denngay.trim().length() <= 0)
				this.denngay = Utility.getNgayHienTai();

			
			
			
		}catch (Exception e) {
			e.printStackTrace();
		}

	}
	public boolean DongBoERP()
	{
		String query  ="";
		try
		{
			
			if(this.tableDbName.trim().length() <=0)
			{
				this.msg = "Vui lòng chọn Table muốn đồng bộ";
				return false;
			}
			
			db.getConnection().setAutoCommit(false);
			
			query =" select  convert(varchar(10),DATEADD(day, 1,MAX(Ngay)),120)kq  from KhoaSoDongBo where TABLE_NAME = '"+this.tableDbName+"' ";
			ResultSet rs = db.get(query);
			if(rs.next())
				if(rs.getString("kq") != null)
				{
					this.tungay = rs.getString("kq");
				}
			if(this.denngay.compareTo(this.tungay) < 0)
			{
				db.getConnection().rollback();
				db.getConnection().setAutoCommit(true);
				this.msg = "Đến ngày phải lớn hơn hoặc bằng từ ngày (khóa số + 1)!";
				return false;
			}
			if(this.denngay.compareTo(Utility.getNgayHienTai()) > 0)
			{
				db.getConnection().rollback();
				db.getConnection().setAutoCommit(true);
				this.msg = "Đến ngày không được lớn hơn ngày hiện tại!";
				return false;
			}
			if(this.tableDbName.equals("TBL_XUAT_BANHANG"))
			{
				query =    "\n insert   "+dbutils_syn.dbName+"..TBL_XUAT_BANHANG(MaCN ,MACT,SoCT,ngayct,SOHOADON,ngayhoadon,soseri,HTTT,VAt,NoidungGiamgia,Tiengiam,[%ck],Madonvi,Noidung,Loaitien,mavt,malo,soluong,tygia,dongiabanVND,tienbanVND,dongiabanNT,TienbanNT,tkxuat,khoxuat,ytXuat,tkdu,dtdu,ytdu,tkdoanhthu,TKTHUE,tkcongno)  " +  
					 "\n select * from (  " + 
					 "\n select c.MaFAST MaCN,'HDO' MACT,'HDO'+cast (a.PK_SEQ as nvarCHAR) as SoCT,a.NGAYTAO  as ngayct  " + 
					 "\n ,a.SOHOADON,a.NGAYXUATHD as ngayhoadon,a.KYHIEU as soseri,a.HINHTHUCTT as HTTT,a.vat,isnull(a.noidunggiamgia,'') noidunggiamgia,  " + 
					 "\n a.TienGiamGia as tiengiam,a.chietkhau [%CK],c.MaFAST as Madonvi,a.GHICHU as noidung,'VNĐ' as loaitien,  " + 
					 "\n b.MA as mavt,b.SOLO as malo,b.SOLUONG,'1' as Tygia,DONGIA as dongiabanvnd,SOLUONG*DONGIA as tienbanvnd,  " + 
					 "\n '0' dongiabannt,'0' tienbannt  " + 
					 "\n 	,tkhh.TK_KHO tkxuat,sp.khoErp_fk khoxuat,cl.ten ytxuat,tkhh.TK_GVON tkdu,'' dtdu,cl.ten ytdu,tkhh.TK_DTHU tkdoanhthu  " + 
					 "\n 	, tkhh.TK_THUE_DAURA tkthue,tkhh.TK_CONGNO tkcongno  " + 
					 "\n from HOADON  a inner join HOADON_SP_CHITIET b on a.PK_SEQ=b.hoadon_fk   " + 
					 "\n inner join NHAPHANPHOI c on c.PK_SEQ=a.NPP_FK  " + 
					 "\n inner join SANPHAM sp on sp.MA = b.MA   " + 
					 "\n inner join CHUNGLOAI cl on cl.PK_SEQ = sp.CHUNGLOAI_FK    "+
					 "\n inner join TaiKhoanHangHoa tkhh on charindex(tkhh.ma,sp.ma)=1  " + 
					 "\n where a.TRANGTHAI not in (3,5) and  a.NGAYXUATHD >='"+this.tungay+"'   and  a.NGAYXUATHD <='"+this.denngay+"' " + 
					 "\n   " + 
					 "\n union all  " + 

					 // CN 1 bán ra gom (ETC - doi tac)
					 "\n select c.MaFAST MaCN,'HDE' MACT,'HDE'+cast (a.PK_SEQ as nvarCHAR) as SoCT,a.NGAYTAO  as ngayct  "+
					 "\n	,a.SOHOADON,a.NGAYXUATHD as ngayhoadon,a.KYHIEU as soseri,a.HINHTHUCTT as HTTT,a.vat,isnull(a.noidunggiamgia,''),   "+
					 "\n	 a.TienGiamGia as tiengiam,a.chietkhau [%CK],c.MaFAST as Madonvi,a.GHICHU as noidung,'VNĐ' as loaitien,   "+
					 "\n	 b.MA as mavt,b.SOLO as malo,b.SOLUONG,'1' as Tygia,DONGIA as dongiabanvnd,SOLUONG*DONGIA as tienbanvnd,   "+
					 "\n	 '0' dongiabannt,'0' tienbannt   "+
					 "\n	 	,tkhh.TK_KHO tkxuat,sp.khoErp_fk khoxuat,cl.ten ytxuat,tkhh.TK_GVON tkdu,'' dtdu,cl.ten ytdu,tkhh.TK_DTHU tkdoanhthu  "+ 
					 "\n		, tkhh.TK_THUE_DAURA tkthue,tkhh.TK_CONGNO tkcongno   "+
					 "\n	 from ERP_HOADONNPP  a inner join ERP_HOADONNPP_SP_CHITIET b on a.PK_SEQ=b.hoadon_fk  "+  
					 "\n	 inner join NHAPHANPHOI c on c.PK_SEQ=a.NPP_FK   "+
					 "\n	 inner join SANPHAM sp on sp.MA = b.MA    "+
					 "\n	 inner join CHUNGLOAI cl on cl.PK_SEQ = sp.CHUNGLOAI_FK    "+
					 "\n	 inner join TaiKhoanHangHoa tkhh on charindex(tkhh.ma,sp.ma)=1  "+
					 "\n	 where a.TRANGTHAI not in (1,3,5) and  a.NGAYXUATHD >='"+this.tungay+"'   and  a.NGAYXUATHD <='"+this.denngay+"' " +
					 "\n union all "+
					 
 					// HO bán ra gom (ETC - doi tac)
					 "\n  select c.MaFAST MaCN,'HOE' MACT,'HOE'+cast (a.PK_SEQ as nvarCHAR) as SoCT,a.NGAYTAO  as ngayct   "+
					 "\n	,a.SOHOADON,a.NGAYXUATHD as ngayhoadon,a.KYHIEU as soseri,a.HINHTHUCTT as HTTT,a.vat,isnull(a.noidunggiamgia,''), "+  
					 "\n	 a.TienGiamGia as tiengiam,a.chietkhau [%CK],c.MaFAST as Madonvi,a.GHICHU as noidung,'VNĐ' as loaitien,   "+
					 "\n	 b.MA as mavt,b.SOLO as malo,b.SOLUONG,'1' as Tygia,DONGIA as dongiabanvnd,SOLUONG*DONGIA as tienbanvnd,  "+
					 "\n	 '0' dongiabannt,'0' tienbannt   "+
					 "\n	 	,tkhh.TK_KHO tkxuat,sp.khoErp_fk khoxuat,cl.ten ytxuat,tkhh.TK_GVON tkdu,'' dtdu,cl.ten ytdu,tkhh.TK_DTHU tkdoanhthu   "+
					 "\n		, tkhh.TK_THUE_DAURA tkthue,tkhh.TK_CONGNO tkcongno   "+
					 "\n	 from ERP_HOADON  a inner join ERP_HOADON_SP_CHITIET b on a.PK_SEQ=b.hoadon_fk  "+  
					 "\n	 inner join NHAPHANPHOI c on c.PK_SEQ= 1  "+
					 "\n	 inner join SANPHAM sp on sp.MA = b.MA   "+
					 "\n	 inner join CHUNGLOAI cl on cl.PK_SEQ = sp.CHUNGLOAI_FK    "+
					 "\n	 inner join TaiKhoanHangHoa tkhh on charindex(tkhh.ma,sp.ma)=1 "+  
					 "\n	 where a.TRANGTHAI not in (1,3,5) and  a.NGAYXUATHD >='"+this.tungay+"'   and  a.NGAYXUATHD <='"+this.denngay+"' "+
					 "\n ) as data ";
			}
			else
			if(this.tableDbName.equals("TBL_PHIEUTHUNH"))
			{
				query =  "\n insert   "+dbutils_syn.dbName+"..TBL_PHIEUTHUNH(MaCN,MaCT,SoCT,NgayCT,Nguoinop,Madonvi,Noidung,Loaitien,tkno,dtno,ytno,tkco,dtco,ytco,tiennt,tygia,tienVND)  " + 
						 "\n select npp.MaFAST MaCN , 'PTKNH' MaCT, 'PTKNH' + CAST(a.pk_seq as varchar) SoCT ,a.ngaychungtu NgayCT, a.tennguoinop as Nguoinop   " + 
						 "\n 	,'' Madonvi, b.noidung, 'VND' Loaitien, b.tkno,b.dtno,b.ytno,b.tkco,b.dtco,b.ytco,b.tiennt,1 tygia, b.tiennt tienVND    " + 
						 "\n  from DCL..erp_thutienKhac  a   " + 
						 "\n  inner join DCL..erp_thutienKhac_chitiet b on a.pk_seq = b.thutienkhac_fk   " + 
						 "\n  inner join DCL..NHAPHANPHOI npp on a.npp_fk = npp.PK_SEQ   " + 
						 "\n  where   a.trangthai = 1  and a.HTTT_FK = 100001   " + 
						 "\n 	and a.ngaychungtu >= isnull( (select  convert(varchar(10),DATEADD(day, 1,MAX(Ngay)),120)kq  from KhoaSoDongBo where TABLE_NAME = 'TBL_PHIEUTHUNH'),'2010-01-01')  " +
						 "\n	and a.ngaychungtu <='"+this.denngay+"'" ;
			}
			else
			if(this.tableDbName.equals("TBL_PHIEUTHU"))
			{
				query =  "\n insert   "+dbutils_syn.dbName+"..TBL_PHIEUTHU(MaCN,MaCT,SoCT,NgayCT,Nguoinop,Madonvi,Noidung,Loaitien,tkno,dtno,ytno,tkco,dtco,ytco,tiennt,tygia,tienVND)  " + 
						 "\n select npp.MaFAST MaCN , 'PTKNH' MaCT, 'PTK' + CAST(a.pk_seq as varchar) SoCT ,a.ngaychungtu NgayCT, a.tennguoinop as Nguoinop   " + 
						 "\n 	,'' Madonvi, b.noidung, 'VND' Loaitien, b.tkno,b.dtno,b.ytno,b.tkco,b.dtco,b.ytco,b.tiennt,1 tygia, b.tiennt tienVND    " + 
						 "\n  from DCL..erp_thutienKhac  a   " + 
						 "\n  inner join DCL..erp_thutienKhac_chitiet b on a.pk_seq = b.thutienkhac_fk   " + 
						 "\n  inner join DCL..NHAPHANPHOI npp on a.npp_fk = npp.PK_SEQ   " + 
						 "\n  where   a.trangthai = 1  and a.HTTT_FK = 100000   " + 
						 "\n 	and a.ngaychungtu >= isnull( (select  convert(varchar(10),DATEADD(day, 1,MAX(Ngay)),120)kq  from KhoaSoDongBo where TABLE_NAME = 'TBL_PHIEUTHUNH'),'2010-01-01')  " +
						 "\n	and a.ngaychungtu <='"+this.denngay+"'" ;
			}
			if(query.trim().length() <=0)
			{
				db.getConnection().rollback();
				db.getConnection().setAutoCommit(true);
				this.msg = "Table chưa được hỗ trợ đồng bộ";
				return false;
			}
			
			if(!db.update(query))
			{
				db.getConnection().rollback();
				db.getConnection().setAutoCommit(true);
				this.msg = "Lỗi!"+ query;
				return false;
			}
			
			query =" insert KhoaSoDongBo (NGAY,table_Name,NguoiTao,ThoiDiem) select '"+this.denngay+"','"+this.tableDbName+"','"+this.userId+"',getdate() ";
			if(!db.update(query))
			{
				db.getConnection().rollback();
				db.getConnection().setAutoCommit(true);
				this.msg = "Lỗi!"+ query;
				return false;
			}
			
			db.getConnection().commit();
			db.getConnection().setAutoCommit(true);
			this.msg = "Đồng bộ thành công!";
				
			return true;
			
		}catch(Exception e)
		{
			db.update("rollback");
			this.msg = "Exception:" +e.getMessage() +"("+query+")";
			e.printStackTrace();
			return false;
		}
	}
	
	/*public ResultSet getKenhbanhangRs()
	{
		this.kenh = this.db.get(" select pk_seq,ten,diengiai from kenhbanhang ");
		return this.kenh;
	}*/
	
	public ResultSet getVungRs()
	{
		this.vung = db.get("select pk_seq,ten,diengiai from vung ");
		return this.vung;
	}
	public void init_user()
	{
		try{
			
			Utility Ult = new Utility();
			String query="select phanloai,loai from nhanvien where pk_seq='"+this.userId+ "'";
			System.out.println(" user :" + query);
			ResultSet rs=this.db.get(query);
			if(rs!=null){
				if(rs.next()){

					this.phanloai = rs.getString("phanloai");
					System.out.println("Phan loai : "+this.phanloai);					 				
					this.loaiUser =  rs.getString("loai");
					if( rs.getString("phanloai").equals("1")){
						this.nppId = Ult.getIdNhapp(this.userId);
						this.nppTen = Ult.getTenNhaPP();
					}
					rs.close();
				}
			}
		}catch(Exception er){

		}
		System.out.println("nppId = "+ this.nppId);
	}
	
	
	public void init()
	{
		
		String sql = "";
		init_user();
		
		// INIT KENH BAN HANG
		//this.kenh = db.get(" select pk_seq,ten,diengiai from kenhbanhang ");
		
		// INIT VUNG
		//this.vung = db.get("select pk_seq,ten,diengiai from vung ");

		// INIT DON VI KINH DOANH
		//this.dvkd = db.get("select pk_seq,diengiai from donvikinhdoanh ");

		// INIT TINH THANH
		//this.tinhthanh = db.get("select pk_seq,ten from tinhthanh");
		// INIT KHO
		//this.Khors=db.get("select pk_seq,ten from kho");

		// INIT KHU VUC
		/*if (this.vungId.length() > 0)
		{
			this.khuvuc = db.get("select pk_seq,ten from khuvuc where vung_fk ='" + this.vungId + "'");
		} else
		{
			String query_khuvuc=" select PK_SEQ, TEN from KHUVUC "
				+ "	where PK_SEQ in (select KHUVUC_Fk from NHAPHANPHOI "
				+ "	where pk_seq in "+ Ult.quyen_npp(userId)+")"; 			
			this.khuvuc= db.get(query_khuvuc);		
		}*/

		Utility  util = new Utility();

		sql = "select pk_seq,ten from nhaphanphoi where trangthai ='1'    "; //and loainpp <> 4 
		String sql1 = "select pk_seq,ten from nhaphanphoi where trangthai ='1'  and loainpp not in (2,3)   ";
		
		if(this.ttId.length()>0)
		{
			sql+=" and TinhThanh_fk='"+this.ttId+"'";
			sql1+=" and TinhThanh_fk='"+this.ttId+"'";
		}

		/*
		 * Neu dang nhap la trung tam va phan loai #
		 * <%=obj.getLoai().equals("4") ? " selected " : ""%>>Trình dược
		 * viên</option> <option value="5" <%=obj.getLoai().equals("5") ?
		 * " selected " : ""%>>Ban Giám Đốc</option> <option value="1"
		 * <%=obj.getLoai().equals("1") ? " selected " : ""%>>Giám Đốc
		 * Miền</option> <option value="2" <%=obj.getLoai().equals("2") ?
		 * " selected " : ""%>>Phụ trách Vùng</option> <option value="3"
		 * <%=obj.getLoai().equals("3") ? " selected " : ""%>>Phụ Trách Tỉnh/
		 * GĐCN Cấp 2</option>
		 */
		if(this.phanloai.equals("2"))
		{
			sql+= " and pk_Seq in " + util.quyen_npp(userId)+"";
			sql1+= " and pk_Seq in " + util.quyen_npp(userId)+"";
		}


		if (this.khuvucId.length() > 0)
		{
			sql = sql + " and khuvuc_fk ='" + this.khuvucId + "'";
			sql1 = sql1 + " and khuvuc_fk ='" + this.khuvucId + "'";
		}
		if (this.vungId.length() > 0)
		{
			sql = sql + " and khuvuc_fk in (select pk_seq from khuvuc where vung_fk ='" + this.vungId + "')";
			sql1 = sql1 + " and khuvuc_fk in (select pk_seq from khuvuc where vung_fk ='" + this.vungId + "')";
		}
		// //System.out.println("Get NPP :"+sql);
		if (this.kenhId.length() > 0)
			sql = sql + " and pk_seq in (select npp_fk from NHAPP_KBH where kbh_fk ='" + this.kenhId + "')";

		if(this.gsbhId.length() > 0)
		{
			sql = sql + " and pk_seq in (select npp_fk from NHAPP_GIAMSATBH where gsbh_fk = '"+ this.gsbhId +"')";			
		}

		if(this.programsId.trim().length() >0 && this.nppId.trim().equals("")) 
		{
			sql = sql + " and pk_seq in (select npp_fk from  CTKM_NPP inner join CTKHUYENMAI ctkm on ctkm.pk_seq=CTKM_NPP.ctkm_fk where ctkm.scheme='"+this.getPrograms()+"'    ) ";
		}	
		//	//System.out.println("Get NPP :" + sql);

		sql += " order by ten ";
		this.npp = db.getScrol(sql);
		this.rsnppupload=db.getScrol(sql1);

		//System.out.println("ASM " + sql1);
		
		// INIT CTKM
		/*sql=" select distinct a.PK_SEQ, a.scheme from CTKHUYENMAI a  " +
		"inner join CTKM_NPP b on b.CTKM_FK = a.PK_SEQ where LOAICT = '3' ";
		if (this.nppId !=null && this.nppId.length() > 0){
			sql=sql+ " and b.NPP_FK = '" + this.nppId + "' "; 
		}
		if(this.gettungay()!=null &&this.gettungay().length() >0) {
			sql=sql+ " and a.tungay >='"+this.gettungay()+"'";
		}
		if( this.getdenngay()!=null && this.getdenngay().length() > 0){
			sql=sql+ " and a.denngay <='"+this.getdenngay()+"'";
		}
		if(this.getNgayketthucctkm()!= null&& this.getNgayketthucctkm().length() >0) {
			sql=sql+ " and a.denngay like  '"+this.getNgayketthucctkm()+"%'";
		}
		this.Rskmtl = db.get(sql);*/

		// INIT NHAN HANG
		/*if (this.dvkdId.length() > 0) {	this.nhanhang = db.get("select * from nhanhang where dvkd_fk ='" + this.dvkdId + "'"); }
		else { this.nhanhang = db.get("select * from nhanhang "); }*/

		// INIT CHUNG LOAI
		/*if(this.nhanhangId.length() > 0)
		{	sql = " select cl.pk_Seq,ten from chungloai cl " +
				  " inner join nhanhang_chungloai nhcl on nhcl.cl_fk = cl.pk_seq " +
				  " where nh_fk = '"+ this.nhanhangId +"' ";
		} else {	sql = "select pk_Seq,ten  from chungloai"; }
		this.chungloai = db.get(sql);*/
		
		// INIT GIAM SAT BAN HANG
		/*sql = " SELECT GSBH.PK_SEQ, GSBH.TEN FROM GIAMSATBANHANG GSBH WHERE GSBH.TRANGTHAI = '1' ";
		if (this.kenhId.length() > 0) { sql = sql + " AND GSBH.KBH_FK ='" + this.kenhId + "'"; } 
		else if (this.dvkdId.length() > 0) { sql = sql + " AND GSBH.DVKD_FK ='" + this.dvkdId + "'"; } 
		else if (this.khuvucId.length() > 0) { sql = sql + " AND GSBH.PK_SEQ IN ( SELECT GSBH_FK FROM GSBH_KHUVUC WHERE KHUVUC_FK ='" + this.khuvucId + "' )"; }
		else if (this.nppId!=null && this.nppId.length() > 0) { sql = sql + " AND GSBH.PK_SEQ in (select GSBH_FK from NHAPP_GIAMSATBH where NPP_FK = '" + this.nppId + "') "; }
		if(this.vungId!=null && this.vungId.length()>0) { sql+= " and  GSBH.VUNG_fk in ( select pk_seq from Vung where vung_fk ='"+this.vungId+"') "; }
		this.gsbh = db.get(sql);*/

		// INIT ASM
		/*sql = "SELECT ASM.PK_SEQ, ASM.TEN FROM ASM ASM WHERE ASM.TRANGTHAI ='1' ";
		if (this.kenhId.length() > 0) { sql = sql + " AND ASM.KBH_FK ='" + this.kenhId + "'"; } 
		else if (this.dvkdId.length() > 0) { sql = sql + " AND ASM.DVKD_FK ='" + this.dvkdId + "'"; } 
		else if (this.khuvucId.length() > 0) { sql = sql + " AND ASM.PK_SEQ IN ( SELECT asm_fk FROM ASM_KHUVUC WHERE khuvuc_Fk= '" + this.khuvucId + "' ) "; } 
		else if (this.vungId.length() > 0) { sql = sql + " AND ASM.PK_SEQ in  ( SELECT asm_fk FROM ASM_KHUVUC WHERE khuvuc_Fk in (select pk_Seq from KhuVuc where vung_Fk='"+this.vungId+"' ) ) "; }
		this.asm = db.get(sql);*/

		// INIT BM
		/*sql = " SELECT BM.PK_SEQ, BM.TEN  " + "FROM BM BM " + "INNER JOIN BM_CHINHANH BM_CHINHANH ON BM_CHINHANH.BM_FK = BM.PK_SEQ " + "INNER JOIN VUNG VUNG ON VUNG.PK_SEQ = BM_CHINHANH.VUNG_FK " + "WHERE BM.TRANGTHAI ='1'";
		if (this.kenhId.length() > 0) { sql = sql + " AND BM.KBH_FK ='" + this.kenhId + "'"; } 
		else if (this.dvkdId.length() > 0) { sql = sql + " AND BM.DVKD_FK ='" + this.dvkdId + "'"; } 
		else if (this.vungId.length() > 0) { sql = sql + " AND VUNG.PK_SEQ = '" + this.vungId + "'"; }
		this.bm = db.get(sql);*/

		// INIT SANPHAM & SPRS
		/*String st = "select pk_seq,ma,ten from sanpham where trangthai ='1' ";
		if (this.nhanhangId.length() > 0) { st = st + " and nhanhang_fk ='" + this.nhanhangId + "'"; }
		if (this.chungloaiId.length() > 0) { st = st + " and chungloai_fk ='" + this.chungloaiId + "'"; }
		if (this.dvkdId.length() > 0) { st = st + " and dvkd_fk ='" + this.dvkdId + "'"; }
		if (this.dvdlId.length() > 0) { st = st + " and dvdl_fk ='" + this.dvdlId + "'"; }
		if (this.nganhHangId!=null && this.nganhHangId.length()>0) { st+=" and NGANHHANG_fk ='"+this.nganhHangId+"'"; }
		if (this.Nhospid.length() > 0) { st = st + " and pk_seq in  (select sanpham_fk from NhomHang_SanPham where nhomhang_fk='" + this.Nhospid + "')"; }
		this.sanpham = db.get(st);
		this.SpRs = db.get(st);*/

		// INIT DVLD
		/*sql = "select pk_seq,donvi from donvidoluong ";
		this.dvdl = db.get(sql);*/
		
		//INIT KHO 
		/*this.kho = db.get("select * from kho ");*/

		// INIT DAI DIEN KINH DOANH
		/*if ( this.nppId!=null&& this.nppId.length() > 0)
		{ this.setRsddkd(db.get("SELECT pk_seq,ten FROM DAIDIENKINHDOANH d where d.trangthai=1 and d.PK_SEQ in (Select ddkd_fk from DAIDIENKINHDOANH_NPP where npp_fk='"+this.nppId+"' )  ")); } 
		else
		{
			String query="SELECT pk_seq,ten FROM DAIDIENKINHDOANH d where trangthai=1 ";
			if(this.phanloai.equals("2")&& !loaiNv.equals("3"))
			{
				query+= " and pk_Seq in " + util.Quyen_Ddkd(userId)+"";
			}
			if(this.ttId.length()>0)
			{
				query+=" and pk_seq in (select ddkd_fk from daidienkinhdoanh_npp where npp_fk in (Select pk_Seq from nhaphanphoi where tinhthanh_fk='"+this.ttId+"')) ";
			}
			this.setRsddkd(db.get(query));
		}*/

		// INIT CHUONG TRINH KHUYEN MAI
		/*sql=" select distinct  a.pk_seq,a.diengiai,a.scheme from CTKHUYENMAI a  " +
		"inner join CTKM_NPP b on b.CTKM_FK = a.PK_SEQ where 1=1 ";
		if (this.nppId != null && this.nppId.length() > 0 && this.getPrograms().trim().equals("") ){ sql=sql+ " and b.NPP_FK = '" + this.nppId + "' "; }
		if(this.gettungay()!=null &&this.gettungay().length() >0 && this.getdenngay()!=null && this.getdenngay().length() > 0) 
		{ sql=sql+ " and ( a.tungay >='"+this.gettungay()+"' or a.denngay <= '"+this.getdenngay()+"' ) "; }
		
		//if( this.gettungay()!=null && this.gettungay().length() >0) {
		//	sql=sql+ " and a.tungay >='"+this.gettungay()+"'";
		//}
		//if( this.getdenngay()!=null && this.getdenngay().length() > 0){
		//	sql=sql+ " and a.denngay <='"+this.getdenngay()+"'";
		//}
		
		if( this.getNgayketthucctkm()!=null  && this.getNgayketthucctkm().length() >0) {
			sql=sql+ " and ( a.denngay like '"+this.getNgayketthucctkm()+"%' or a.tungay like '"+this.getNgayketthucctkm()+"%' ) " ;
		}
		this.rsPrograms = db.get(sql);*/

		// INIT NHOM SP
		//this.RsNhomSp = db.get("select pk_seq,ten as diengiai from nhomhang ");
		
		// INIT QUY CACH
		//this.RsQuyCach = db.get("select pk_seq,donvi as ten from donvidoluong where trangthai=1 ");

		// INIT NGANH HANG
		//this.nganhHangRs=db.get("SELECT PK_SEQ,TEN FROM NGANHHANG WHERE TRANGTHAI=1");

		// INIT KHACH HANG
		/*String query ="select distinct  kh.PK_SEQ, kh.MaFast + ' - '+ kh.Ten as Ten" +
		"	from KHACHHANG kh inner join KHACHHANG_TUYENBH kh_tbh on kh.PK_SEQ=kh_tbh.KHACHHANG_FK "+
		" inner join TUYENBANHANG tbh on tbh.PK_SEQ=kh_tbh.TBH_FK" +
		" where 1=1 and kh.TrangThai ='1'";
		if(this.ddkd.length()>0) { query += " and tbh.DDKD_FK ='"+ddkd+"'"; }
		if(nppId!=null && nppId.trim().length()>0) { query+= " and kh.NPP_FK='"+nppId+"'"; }
		if(this.ttId.length()>0) { query+= " and kh.TinhThanh_fk='"+ttId+"'"; }
		this.khrs =  this.db.get( query);*/

		// INIT DIA BAN
		/*query="select PK_SEQ, TEN from tinhthanh where 1=1 ";
		if(vungId.length()>0)
			query+=" and vung_fk = '"+vungId+"'";
		query+=" and pk_seq in  "+util.Quyen_TinhThanh(this.userId)+" ";
		this.ttRs= this.db.get(query);*/
		
		// INIT NHOM HANG
		//this.nhomhangRs=this.db.get(" select pk_seq,ten from NHOMHANG");

		//createQhRS();
		//createPxRS();
	}
	
	public void createTpRS()
	{  	
		this.tinhthanh = this.db.get("select ten as tpTen, pk_seq as tpId from tinhthanh order by ten");
	}

	public void createQhRS()
	{  	

		if (this.tinhthanhid != null && this.tinhthanhid.length() > 0){

			this.qh = this.db.get("select ten as qhTen, pk_seq as qhId from quanhuyen where tinhthanh_fk='"+ this.tinhthanhid +"' order by ten");
		}
		else
			this.qh = this.db.get("select ten as qhTen, pk_seq as qhId from quanhuyen order by ten");
	}
	
	public void createPxRS()
	{ 
		String querypx = "";
		if (this.qhId != null && this.qhId.length() > 0){
			querypx = "\n select pk_Seq, ten from phuongxa ";
			querypx += "\n where quanhuyen_fk = '"+this.qhId+"'";
			this.px =this.db.get(querypx);
		}
		////System.out.println("phuongxa : "+ querypx);
	}

	public String getLoaiNv()
	{
		return loaiNv;
	}

	public void setLoaiNv(String loaiNv)
	{
		this.loaiNv = loaiNv;
	}

	public void settungay(String tungay)
	{

		this.tungay = tungay;
	}

	public String gettungay()
	{
		return tungay;
	}

	public void setdenngay(String denngay)
	{

		this.denngay = denngay;
	}

	public String getdenngay()
	{

		return this.denngay;
	}

	public void setMsg(String msg)
	{

		this.msg = msg;
	}

	public String getMsg()
	{

		return this.msg;
	}

	public void setuserTen(String userTen)
	{

		this.userTen = userTen;
	}

	public String getuserTen()
	{

		return this.userTen;
	}

	public void setkhoId(String khoId)
	{

		this.khoId = khoId;
	}

	public String getkhoId()
	{

		return this.khoId;
	}

	public void setkho(ResultSet kho)
	{

		this.kho = kho;
	}

	public ResultSet getkho()
	{
		
		
		System.out.println("USerId_HH: "+ this.userId);
		this.kho = db.get("select * from kho ");
		return this.kho;
	}

	public void setbook(String book)
	{

		this.book = book;
	}

	public String getbook()
	{

		return this.book;
	}

	public void setvat(String vat)
	{

		this.vat = vat;
	}

	public void DBclose()
	{
		try
		{
			if (chungloai != null)
				chungloai.close();
			if (dvdl != null)
				dvdl.close();
			if (dvkd != null)
				dvkd.close();
			if (gsbh != null)
				gsbh.close();
			if (kenh != null)
				kenh.close();
			if (kho != null)
				kho.close();
			if (khuvuc != null)
				khuvuc.close();
			if (nhanhang != null)
				nhanhang.close();
			if (npp != null)
				npp.close();
			if (rsddkd != null)
				rsddkd.close();
			if (rsPrograms != null)
				rsPrograms.close();
			if (sanpham != null)
				sanpham.close();
			if (vung != null)
				vung.close();
			if (RsNhomSp != null)
			{
				RsNhomSp.close();
			}
			if (RsQuyCach != null)
			{
				RsQuyCach.close();
			}
			if (db != null)
				db.shutDown();

			if (asm != null)
			{
				asm.close();
			}
			if (bm != null)
			{
				bm.close();
			}

			if (RsVitricuahang != null)
			{
				RsVitricuahang.close();
			}
			if (RsTinhthanh != null)
			{
				RsTinhthanh.close();
			}
			if (RsQuanhuyen != null)
			{
				RsQuanhuyen.close();
			}
			if (Rsloaicuahang != null)
			{
				Rsloaicuahang.close();
			}
			if (Rshangcuahang != null)
			{
				Rshangcuahang.close();
			}
			if (HdKhacRs != null)
			{
				HdKhacRs.close();
			}

		} catch (Exception e)
		{
			// TODO: handle exception
			e.printStackTrace();
			//System.out.println("khong the dong ket noi");
		}
	}

	public void setvungId(String vungId)
	{

		this.vungId = vungId;
	}

	public String getvungId()
	{

		return this.vungId;
	}

	public void setvung(ResultSet vung)
	{

		this.vung = vung;
	}

	public ResultSet getvung()
	{
		this.vung = db.get("select pk_seq,ten,diengiai from vung ");
		return this.vung;
	}

	public void setkhuvucId(String khuvucId)
	{

		this.khuvucId = khuvucId;
	}

	public String getkhuvucId()
	{

		return this.khuvucId;
	}

	public void setkhuvuc(ResultSet khuvuc)
	{

		this.khuvuc = khuvuc;
	}

	public ResultSet getkhuvuc()
	{
		if (this.vungId.length() > 0)
		{
			this.khuvuc = db.get("select pk_seq,ten from khuvuc where vung_fk ='" + this.vungId + "'");
		} else
		{
			Utility Ult = new Utility();
			String query_khuvuc=" select PK_SEQ, TEN from KHUVUC "
				+ "	where PK_SEQ in (select KHUVUC_Fk from NHAPHANPHOI "
				+ "	where pk_seq in "+ Ult.quyen_npp(userId)+")"; 			
			this.khuvuc= db.get(query_khuvuc);		
		}
		return this.khuvuc;
	}

	public void setnpp(ResultSet npp)
	{

		this.npp = npp;
	}

	public ResultSet getnpp()
	{

		return this.npp;
	}

	public void setgsbhId(String gsbhId)
	{

		this.gsbhId = gsbhId;
	}

	public String getgsbhId()
	{

		return this.gsbhId;
	}

	public void setgsbh(ResultSet gsbh)
	{

		this.gsbh = gsbh;
	}

	public ResultSet getgsbh()
	{
		String sql = " SELECT GSBH.PK_SEQ, GSBH.TEN FROM GIAMSATBANHANG GSBH WHERE GSBH.TRANGTHAI = '1' ";
		if (this.kenhId.length() > 0) { sql = sql + " AND GSBH.KBH_FK ='" + this.kenhId + "'"; } 
		else if (this.dvkdId.length() > 0) { sql = sql + " AND GSBH.DVKD_FK ='" + this.dvkdId + "'"; } 
		else if (this.khuvucId.length() > 0) { sql = sql + " AND GSBH.PK_SEQ IN ( SELECT GSBH_FK FROM GSBH_KHUVUC WHERE KHUVUC_FK ='" + this.khuvucId + "' )"; }
		else if (this.nppId!=null && this.nppId.length() > 0) { sql = sql + " AND GSBH.PK_SEQ in (select GSBH_FK from NHAPP_GIAMSATBH where NPP_FK = '" + this.nppId + "') "; }
		if(this.vungId!=null && this.vungId.length()>0) { sql+= " and  GSBH.VUNG_fk in ( select pk_seq from Vung where vung_fk ='"+this.vungId+"') "; }
		this.gsbh = db.get(sql);
		return this.gsbh;
	}

	public void setsanphamId(String sanphamId)
	{

		this.sanphamId = sanphamId;
	}

	public String getsanphamId()
	{

		return this.sanphamId;
	}

	public void setsanpham(ResultSet sanpham)
	{

		this.sanpham = sanpham;
	}

	public ResultSet getsanpham()
	{
		String st = "select pk_seq,ma,ten from sanpham where trangthai ='1' ";
		if (this.nhanhangId.length() > 0) { st = st + " and nhanhang_fk ='" + this.nhanhangId + "'"; }
		if (this.chungloaiId.length() > 0) { st = st + " and chungloai_fk ='" + this.chungloaiId + "'"; }
		if (this.dvkdId.length() > 0) { st = st + " and dvkd_fk ='" + this.dvkdId + "'"; }
		if (this.dvdlId.length() > 0) { st = st + " and dvdl_fk ='" + this.dvdlId + "'"; }
		if (this.nganhHangId!=null && this.nganhHangId.length()>0) { st+=" and NGANHHANG_fk ='"+this.nganhHangId+"'"; }
		if (this.Nhospid.length() > 0) { st = st + " and pk_seq in  (select sanpham_fk from NhomHang_SanPham where nhomhang_fk='" + this.Nhospid + "')"; }
		this.sanpham = db.get(st);
		return this.sanpham;
	}

	public void setdvdlId(String dvdlId)
	{

		this.dvdlId = dvdlId;
	}

	public String getdvdlId()
	{

		return this.dvdlId;
	}

	public void setdvdl(ResultSet dvdl)
	{

		this.dvdl = dvdl;
	}

	public ResultSet getdvdl()
	{
		String sql = "select pk_seq,donvi from donvidoluong ";
		this.dvdl = db.get(sql);
		return this.dvdl;
	}

	public void setFieldShow(String[] fieldShow)
	{

		this.FieldShow = fieldShow;
	}

	public String[] getFieldShow()
	{
		return this.FieldShow;

	}

	public void setFieldHidden(String[] fieldHidden)
	{

		this.FieldHidden = fieldHidden;
	}

	public String[] getFieldHidden()
	{

		return this.FieldHidden;
	}

	public void setngayton(String ngayton)
	{

		this.ngayton = ngayton;
	}

	public String getngayton()
	{

		return this.ngayton;
	}

	public String getDateTime()
	{
		DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
		Date date = new Date();
		return dateFormat.format(date);
	}

	public String getDate()
	{
		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		Date date = new Date();
		return dateFormat.format(date);
	}

	public String getdiscount()
	{

		return this.discount;
	}

	public String getpromotion()
	{

		return this.promotion;
	}

	public void setdiscount(String discount)
	{

		this.discount = discount;
	}

	public void setpromotion(String promotion)
	{

		this.promotion = promotion;
	}

	public String getvat()
	{

		return this.vat;
	}

	public String getlessday()
	{

		return this.lessday;
	}

	public String getmoreday()
	{

		return this.moreday;
	}

	public void setlessday(String lessday)
	{

		this.lessday = lessday;
	}

	public void setmoreday(String moreday)
	{

		this.moreday = moreday;
	}

	public void setDdkd(String ddkd)
	{
		this.ddkd = ddkd;
	}

	public String getDdkd()
	{

		return this.ddkd;
	}

	public void setRsddkd(ResultSet rs)
	{
		this.rsddkd = rs;
	}

	public ResultSet getRsddkd()
	{
		System.out.println("___nppId:::: "+nppId);

		if ( this.nppID!=null&& this.nppID.length() > 0)
		{ this.rsddkd = db.get("SELECT pk_seq,ten FROM DAIDIENKINHDOANH d where d.ISPG=0 and d.trangthai=1 and d.PK_SEQ in (Select ddkd_fk from DAIDIENKINHDOANH_NPP where npp_fk='"+this.nppID+"' ) "); } 
		else
		{
			Utility util = new Utility();
			String query="SELECT pk_seq,ten FROM DAIDIENKINHDOANH d where trangthai=1 AND  d.ISPG=0 ";
			if(this.phanloai.equals("2"))
			{ query+= " and pk_Seq in " + util.Quyen_Ddkd(userId)+""; }
			if(this.ttId.length()>0) { query+=" and pk_seq in (select ddkd_fk from daidienkinhdoanh_npp where npp_fk in (Select pk_Seq from nhaphanphoi where tinhthanh_fk='"+this.ttId+"')) "; }
			this.rsddkd = db.get(query);
		}
		return this.rsddkd;
	}

	public void setUnit(String unit)
	{
		this.unit = unit;
	}

	public String getUnit()
	{

		return this.unit;
	}

	public void setGroupCus(String groupCus)
	{
		this.groupCus = groupCus;
	}

	public String getGroupCus()
	{

		return this.groupCus;
	}

	public void setPrograms(String programs)
	{
		this.programsId = programs;
	}

	public String getPrograms()
	{

		return this.programsId;
	}

	public void setRsPrograms(ResultSet RsPrograms)
	{
		this.rsPrograms = RsPrograms;
	}

	public ResultSet getRsPrograms()
	{
		String sql=
		" select distinct  a.pk_seq,a.diengiai,a.scheme from CTKHUYENMAI a " +
		" inner join CTKM_NPP b on b.CTKM_FK = a.PK_SEQ where 1=1 ";
		if (this.nppId != null && this.nppId.length() > 0 && this.getPrograms().trim().equals("") ){ sql=sql+ " and b.NPP_FK = '" + this.nppId + "' "; }
		if(this.gettungay()!=null &&this.gettungay().length() >0 && this.getdenngay()!=null && this.getdenngay().length() > 0) 
		{ sql=sql+ " and ( a.tungay >='"+this.gettungay()+"' or a.denngay <= '"+this.getdenngay()+"' ) "; }
		if( this.getNgayketthucctkm()!=null  && this.getNgayketthucctkm().length() >0)
		{ sql=sql+ " and ( a.denngay like '"+this.getNgayketthucctkm()+"%' or a.tungay like '"+this.getNgayketthucctkm()+"%' ) " ; }
		this.rsPrograms = db.get(sql);
		return this.rsPrograms;
	}

	public void setDonViTinh(String donviTinh)
	{
		this.donviTinh = donviTinh;

	}

	public String getDonViTinh()
	{
		return this.donviTinh;
	}

	public void setFromMonth(String month)
	{
		this.fromMonth = month;
	}

	public String getFromMonth()
	{
		return this.fromMonth;
	}

	public void setToMonth(String month)
	{
		this.toMonth = month;
	}

	public String getToMonth()
	{
		return this.toMonth;
	}

	public void settype(String _type)
	{

		this.type = _type;
	}

	public String gettype()
	{

		return this.type;
	}

	public void setFromYear(String fromyear)
	{

		this.FromYear = fromyear;
	}

	public String getFromYear()
	{

		return this.FromYear;
	}

	public void setToYear(String toyear)
	{

		this.ToYear = toyear;
	}

	public String getToYear()
	{

		return this.ToYear;
	}

	public void SetNhoSPId(String nhomspid)
	{

		this.Nhospid = nhomspid;
	}

	public String GetNhoSPId()
	{

		return this.Nhospid;
	}

	public void setRsNhomSP(ResultSet rs)
	{

		this.RsNhomSp = rs;
	}

	public ResultSet GetRsNhomSP()
	{
		this.RsNhomSp = db.get("select pk_seq,ten as diengiai, ten from nhomhang ");
		return this.RsNhomSp;
	}

	public void setRsQuyCach(ResultSet QuyCach)
	{

		this.RsQuyCach = QuyCach;
	}

	public ResultSet GetRsQuyCach()
	{
		this.RsQuyCach = db.get("select pk_seq,donvi as ten from donvidoluong where trangthai=1 ");
		return this.RsQuyCach;
	}

	public void SetQuyCachId(String _QuyCachId)
	{

		this.QuyCachId = _QuyCachId;
	}

	public String GetQuyCachId()
	{

		return this.QuyCachId;
	}

	public ResultSet getNhomrs()
	{
		return this.nrs;
	}

	public void setNhomrs(ResultSet nrs)
	{
		this.nrs = nrs;
	}

	public String getNspId()
	{
		return this.nspIds;
	}

	public void setNspId(String nspId)
	{
		this.nspIds = nspId;
	}


	public void CreaterRsKh()
	{

		String sql = "select pk_seq,ten from tinhthanh ";

		this.RsTinhthanh = db.get(sql);

		sql = "select pk_seq,ten from quanhuyen ";
		if (this.tinhthanhid.length() > 0)
		{
			sql = "select pk_seq,ten from quanhuyen  where tinhthanh_fk=" + this.tinhthanhid;
		}

		this.RsQuanhuyen = db.get(sql);

		sql = "select pk_seq,ten from tinhthanh  ";

		this.RsTinhthanh = db.get(sql);

		sql = "select pk_seq,diengiai as ten   from vitricuahang ";

		this.RsVitricuahang = db.get(sql);

		sql = "select pk_seq,diengiai as ten   from hangcuahang  ";

		this.Rshangcuahang = db.get(sql);

		sql = "select pk_seq,diengiai as ten   from loaicuahang ";

		this.Rsloaicuahang = db.get(sql);

	}


	public ResultSet GetRsTinhThanh()
	{

		return this.RsTinhthanh;
	}


	public ResultSet GetRsQuanHuyen()
	{

		return this.RsQuanhuyen;
	}


	public ResultSet GetRsHangCuahang()
	{

		return this.Rshangcuahang;
	}


	public ResultSet GetRsLoaicuahang()
	{

		return this.Rsloaicuahang;
	}


	public ResultSet GetRsVitriCuahang()
	{

		return this.RsVitricuahang;
	}


	public String GetIdTinhThanh()
	{

		return this.tinhthanhid;
	}


	public String GetIdQuanHuyen()
	{

		return this.quanhuyenid;
	}


	public String GetIdHangCuahang()
	{

		return this.hangcuahangid;
	}


	public String GetIdLoaicuahang()
	{

		return this.loaicuahangid;
	}


	public String GetIdVitriCuahang()
	{

		return this.vitricuahangid;
	}


	public void SetIdTinhThanh(String id)
	{

		this.tinhthanhid = id;
	}


	public void SetIdQuanHuyen(String id)
	{

		this.quanhuyenid = id;
	}


	public void SetIdHangCuahang(String id)
	{

		this.hangcuahangid = id;
	}


	public void SetIdLoaicuahang(String id)
	{

		this.loaicuahangid = id;
	}


	public void SetIdVitriCuahang(String id)
	{

		this.vitricuahangid = id;
	}


	public void setphanloai(String pl)
	{

		this.phanloai = pl;
	}


	public String getphanloai()
	{

		return this.phanloai;
	}


	public void setctkmtlId(String ctkmtlId)
	{

		this.Ctkmtichluy = ctkmtlId;
	}


	public String getctkmtlId()
	{

		return this.Ctkmtichluy;
	}


	public void setRskmtl(ResultSet rskmtl)
	{

		this.Rskmtl = rskmtl;
	}


	public ResultSet getRskmtl()
	{
		String sql = " select distinct a.PK_SEQ, a.scheme from CTKHUYENMAI a  " +
					 " inner join CTKM_NPP b on b.CTKM_FK = a.PK_SEQ where LOAICT = '3' ";
			if (this.nppId !=null && this.nppId.length() > 0){
				sql=sql+ " and b.NPP_FK = '" + this.nppId + "' "; 
			}
			if(this.gettungay()!=null &&this.gettungay().length() >0) {
				sql=sql+ " and a.tungay >='"+this.gettungay()+"'";
			}
			if( this.getdenngay()!=null && this.getdenngay().length() > 0){
				sql=sql+ " and a.denngay <='"+this.getdenngay()+"'";
			}
			if(this.getNgayketthucctkm()!= null&& this.getNgayketthucctkm().length() >0) {
				sql=sql+ " and a.denngay like  '"+this.getNgayketthucctkm()+"%'";
			}
			this.Rskmtl = db.get(sql);
		return this.Rskmtl;
	}

	public void setxemtheo(String xemtheo)
	{

		this.xemtheo = xemtheo;
	}


	public String getxemtheo()
	{

		return this.xemtheo;
	}

	public String getMuclay()
	{
		return muclay;
	}

	public void setMuclay(String muclay)
	{
		this.muclay = muclay;
	}


	public void TonKhoHangNgayPdf(Document document, ServletOutputStream outstream, IStockintransit obj, String query) throws SQLException, IOException
	{
		try
		{
			PdfWriter.getInstance(document, outstream);
			// document.setMargins(15.0f, 15.0f, 15.0f, 15.0f);

			document.setPageSize(PageSize.A4);
			document.open();

			// chi dinh BaseFont.IDENTITY_H de co the go tieng viet
			BaseFont bf = BaseFont.createFont("C:\\WINDOWS\\Fonts\\times.ttf", BaseFont.IDENTITY_H, BaseFont.EMBEDDED);
			Font header = new Font(bf, 14, Font.BOLD);
			Font bold = new Font(bf, 13, Font.BOLD);
			Font normal = new Font(bf, 10, Font.NORMAL);
			Font underline = new Font(bf, 12, Font.UNDERLINE);
			bold.setColor(BaseColor.RED);
			Paragraph ddh = new Paragraph("TỒN KHO HÀNG NGÀY", bold);
			ddh.setSpacingAfter(15);
			ddh.setAlignment(Element.ALIGN_CENTER);
			document.add(ddh);

			PdfPTable tableHead = new PdfPTable(4);
			tableHead.setWidthPercentage(100);
			tableHead.setHorizontalAlignment(Element.ALIGN_LEFT);
			tableHead.setSpacingAfter(10);
			float[] with =
			{ 15.0f, 10.0f, 10f, 15.0f }; // set chieu rong cac columns
			tableHead.setWidths(with);

			PdfPCell cell1 = new PdfPCell(new Paragraph("Từ ngày: ", underline));
			PdfPCell cell2 = new PdfPCell(new Paragraph(obj.gettungay(), normal));
			PdfPCell cell3 = new PdfPCell(new Paragraph("Đến ngày: ", underline));
			PdfPCell cell4 = new PdfPCell(new Paragraph(obj.getdenngay(), normal));
			cell1.setBorder(0);
			cell2.setBorder(0);
			cell3.setBorder(0);
			cell4.setBorder(0);
			tableHead.addCell(cell1);
			tableHead.addCell(cell2);
			tableHead.addCell(cell3);
			tableHead.addCell(cell4);

			PdfPCell cell5 = new PdfPCell(new Paragraph("Ngày báo cáo: ", underline));
			PdfPCell cell6 = new PdfPCell(new Paragraph(getDateTime(), normal));
			PdfPCell cell7 = new PdfPCell(new Paragraph("Được tạo bởi: ", underline));
			PdfPCell cell8 = new PdfPCell(new Paragraph(obj.getuserTen(), normal));
			cell5.setBorder(0);
			cell6.setBorder(0);
			cell7.setBorder(0);
			cell8.setBorder(0);
			tableHead.addCell(cell5);
			tableHead.addCell(cell6);
			tableHead.addCell(cell7);
			tableHead.addCell(cell8);
			tableHead.setSpacingAfter(15);
			tableHead.setSpacingAfter(15);
			document.add(tableHead);

			// Table Content
			ResultSet rs = this.db.get(query);
			//	//System.out.println("Query" + query);
			int socot = 6;
			PdfPTable table;
			table = null;
			PdfPCell cell = new PdfPCell();
			String nppPrev = "";
			String ngayPrev = "";
			double tongluong = 0;
			double tongtien = 0;
			double soluong = 0;
			double sotien = 0;
			float[] withs = new float[socot];
			for (int i = 0; i < socot; i++)
			{
				switch (i)
				{
				case 0:
					withs[i] = 10.0f;
					break;
				case 1:
					withs[i] = 15.0f;
					break;
				case 2:
					withs[i] = 15.0f;
					break;
				case 3:
					withs[i] = 35.0f;
					break;
				case 4:
					withs[i] = 20.0f;
					break;
				case 5:
					withs[i] = 20.0f;
					break;
				default:
					withs[i] = 15.0f;
					break;
				}
			}
			NumberFormat formatter = new DecimalFormat("#,###,###.###");
			int stt = 1;
			while (rs.next())
			{
				String nppCur = rs.getString("distcode");
				String ngayCur = rs.getString("date");
				if (!nppCur.equals(nppPrev))
				{
					if (table != null)
					{
						String[] cell_total =
						{"Ngày " + ngayPrev, "Tổng cộng ", formatter.format(tongluong), formatter.format(tongtien)};
						for (int i = 0; i < cell_total.length; i++)
						{
							cell = new PdfPCell(new Paragraph(cell_total[i], bold));
							if (i == 0)
							{
								cell.setHorizontalAlignment(Element.ALIGN_LEFT);
								cell.setColspan(3);
							} else
							{
								cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
							}
							cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
							cell.setPadding(0.1f * CONVERT);
							table.addCell(cell);
						}
						document.add(table);
						ngayPrev="";
					}

					Paragraph p = new Paragraph("Nhà phân phối : " + nppCur + " - " + rs.getString("distributor"), normal);
					p.setSpacingBefore(0.3f * CONVERT);
					p.setSpacingAfter(0.2f * CONVERT);
					p.setAlignment(Element.ALIGN_LEFT);
					document.add(p);

					tongluong = 0;
					tongtien = 0;
					nppPrev = nppCur;
					table = new PdfPTable(withs.length);
					table.setWidths(withs);
					table.setWidthPercentage(100);

					String[] spTitles =
					{"STT", "Ngày", "Kho", "Sản phẩm", "Số lượng(THG)", "Thành tiền tồn"};
					for (int i = 0; i < spTitles.length; i++)
					{
						cell = new PdfPCell(new Paragraph(spTitles[i], bold));
						cell.setHorizontalAlignment(Element.ALIGN_CENTER);
						cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
						cell.setPadding(0.1f * CONVERT);
						table.addCell(cell);
					}
					//	//System.out.println("[Npp 113]" + nppCur);
				}
				if (!ngayCur.equals(ngayPrev))
				{
					stt = 1;
					if (ngayPrev.length() > 0)
					{
						String[] cell_total =
						{"Ngày " + ngayPrev, "Tổng cộng ", formatter.format(tongluong), formatter.format(tongtien)};
						for (int i = 0; i < cell_total.length; i++)
						{
							cell = new PdfPCell(new Paragraph(cell_total[i], bold));
							if (i == 0)
							{
								cell.setHorizontalAlignment(Element.ALIGN_LEFT);
								cell.setColspan(3);
							} else
							{
								cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
							}
							cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
							cell.setPadding(0.1f * CONVERT);
							table.addCell(cell);
						}
					}
					tongluong = 0;
					tongtien = 0;
					ngayPrev = ngayCur;
				}
				soluong = rs.getDouble("Quatity");
				sotien = rs.getDouble("amount");
				tongluong += soluong;
				tongtien += sotien;
				String[] spTitles = new String[]
				                               { Integer.valueOf(stt).toString(), ngayCur, rs.getString("WareHouse"), rs.getString("SkuCode") +"__"+ rs.getString("SKU"), formatter.format(soluong), formatter.format(sotien) };
				for (int i = 0; i < spTitles.length; i++)
				{
					cell = new PdfPCell(new Paragraph(spTitles[i], normal));
					if (i == 0 || i == 1)
					{
						cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
					} else if (i == 2 || i == 3)
					{
						cell.setHorizontalAlignment(Element.ALIGN_LEFT);
					} else
					{
						cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
					}
					cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
					cell.setPadding(0.1f * CONVERT);
					table.addCell(cell);
				}
				stt++;
			}
			if (table != null)
			{
				String[] cell_total =
				{"Ngày " + ngayPrev, "Tổng cộng ", formatter.format(tongluong), formatter.format(tongtien)};
				for (int i = 0; i < cell_total.length; i++)
				{
					cell = new PdfPCell(new Paragraph(cell_total[i], bold));
					if (i == 0)
					{
						cell.setHorizontalAlignment(Element.ALIGN_LEFT);
						cell.setColspan(3);
					} else
					{
						cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
					}
					cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
					cell.setPadding(0.1f * CONVERT);
					table.addCell(cell);
				}
				document.add(table);
				document.close();
			}

		} catch (Exception e)
		{
			e.printStackTrace();
		}
	}

	public void MuaHangPdf(Document document, ServletOutputStream outstream, IStockintransit obj, String query) throws SQLException, IOException
	{
		try
		{
			PdfWriter.getInstance(document, outstream);
			// document.setMargins(10.0f, 15.0f, 15.0f, 15.0f);

			document.setPageSize(PageSize.A4);
			document.open();

			// chi dinh BaseFont.IDENTITY_H de co the go tieng viet
			BaseFont bf = BaseFont.createFont("C:\\WINDOWS\\Fonts\\times.ttf", BaseFont.IDENTITY_H, BaseFont.EMBEDDED);
			Font header = new Font(bf, 14, Font.BOLD);
			Font bold = new Font(bf, 13, Font.BOLD);
			Font normal = new Font(bf, 10, Font.NORMAL);
			Font underline = new Font(bf, 12, Font.UNDERLINE);
			bold.setColor(BaseColor.RED);
			Paragraph ddh = new Paragraph("MUA HÀNG THEO THÁNG", bold);
			if (obj.gettungay().length() > 0)
			{
				ddh = new Paragraph("MUA HÀNG THEO NGÀY", bold);
			}
			ddh.setSpacingAfter(15);
			ddh.setAlignment(Element.ALIGN_CENTER);
			document.add(ddh);

			PdfPTable tableHead = new PdfPTable(4);
			tableHead.setWidthPercentage(100);
			tableHead.setHorizontalAlignment(Element.ALIGN_LEFT);
			tableHead.setSpacingAfter(10);
			float[] with =
			{ 15.0f, 10.0f, 10f, 15.0f }; // set chieu rong cac columns
			tableHead.setWidths(with);

			PdfPCell cell1 = new PdfPCell(new Paragraph("Từ ngày", underline));

			PdfPCell cell2 = new PdfPCell(new Paragraph(obj.gettungay(), normal));

			PdfPCell cell3 = new PdfPCell(new Paragraph("Đến ngày ", underline));

			PdfPCell cell4 = new PdfPCell(new Paragraph(obj.getdenngay(), normal));
			if (obj.getFromMonth().length() > 0)
			{
				cell1 = new PdfPCell(new Paragraph("Từ tháng ", underline));
				cell2 = new PdfPCell(new Paragraph(obj.getFromMonth(), normal));
				cell3 = new PdfPCell(new Paragraph("Đến tháng ", underline));
				cell4 = new PdfPCell(new Paragraph(obj.getToMonth(), normal));
			}

			cell1.setBorder(0);
			cell2.setBorder(0);
			cell3.setBorder(0);
			cell4.setBorder(0);
			tableHead.addCell(cell1);
			tableHead.addCell(cell2);
			tableHead.addCell(cell3);
			tableHead.addCell(cell4);

			PdfPCell cell5 = new PdfPCell(new Paragraph("Ngày báo cáo: ", underline));
			PdfPCell cell6 = new PdfPCell(new Paragraph(getDateTime(), normal));
			PdfPCell cell7 = new PdfPCell(new Paragraph("Được tạo bởi: ", underline));
			PdfPCell cell8 = new PdfPCell(new Paragraph(obj.getuserTen(), normal));
			cell5.setBorder(0);
			cell6.setBorder(0);
			cell7.setBorder(0);
			cell8.setBorder(0);
			tableHead.addCell(cell5);
			tableHead.addCell(cell6);
			tableHead.addCell(cell7);
			tableHead.addCell(cell8);
			tableHead.setSpacingAfter(15);
			tableHead.setSpacingAfter(15);
			document.add(tableHead);

			// Table Content
			ResultSet rs = this.db.get(query);
			//		//System.out.println("____[Init Pdf]___" + query);

			int socot = 7;
			PdfPTable table;
			table = null;
			PdfPCell cell = new PdfPCell();
			String nppPrev = "";
			String ngayPrev = "";
			String thoigian = "Ngày  ";
			double tongluong = 0;
			double tongtien = 0;
			double soluong = 0;
			double sotien = 0;
			float[] withs = new float[socot];
			for (int i = 0; i < socot; i++)
			{
				switch (i)
				{
				case 0:
					withs[i] = 10.0f;
					break;
				case 1:
					withs[i] = 20.0f;
					break;
				case 2:
					withs[i] = 20.0f;
					break;
				case 3:
					withs[i] = 25.0f;
					break;
				case 4:
					withs[i] = 40.0f;
					break;
				case 5:
					withs[i] = 25.0f;
					break;
				default:
					withs[i] = 30.0f;
					break;
				}
			}
			NumberFormat formatter = new DecimalFormat("#,###,###.###");
			int stt = 1;
			while (rs.next())
			{
				String nppCur = rs.getString("distributor_code");
				String ngayCur = rs.getString("ngaynhan");

				if (obj.getFromMonth().length() > 0)
				{
					ngayCur = rs.getString("ngaynhan").substring(0, rs.getString("ngaynhan").length() - 3);
					thoigian = "Tháng  ";
				}
				if (!nppCur.equals(nppPrev))
				{
					if (table != null)
					{
						String[] cell_total =
						{thoigian + ngayPrev, "Tổng cộng ", formatter.format(tongluong), formatter.format(tongtien)};
						for (int i = 0; i < cell_total.length; i++)
						{
							cell = new PdfPCell(new Paragraph(cell_total[i], bold));
							if (i == 0)
							{
								cell.setHorizontalAlignment(Element.ALIGN_LEFT);
								cell.setColspan(3);
							} else
							{
								cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
							}
							cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
							cell.setPadding(0.1f * CONVERT);
							table.addCell(cell);
						}
						tongluong=0;
						tongtien=0;
						ngayPrev="";
						document.add(table);
					}

					Paragraph p = new Paragraph("Nhà phân phối : " + nppCur + " - " + rs.getString("distributor"), normal);
					p.setSpacingBefore(0.3f * CONVERT);
					p.setSpacingAfter(0.2f * CONVERT);
					p.setAlignment(Element.ALIGN_LEFT);
					document.add(p);

					tongluong = 0;
					tongtien = 0;
					nppPrev = nppCur;
					table = new PdfPTable(withs.length);
					table.setWidths(withs);
					table.setWidthPercentage(100);

					String[] spTitles =
					{"STT", "Số hóa đơn", "Ngày nhận", "Mã sản phẩm", "Tên sản phẩm", "Số lượng(THG)", "Thành tiền"};
					for (int i = 0; i < spTitles.length; i++)
					{
						cell = new PdfPCell(new Paragraph(spTitles[i], bold));
						cell.setHorizontalAlignment(Element.ALIGN_CENTER);
						cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
						cell.setPadding(0.1f * CONVERT);
						table.addCell(cell);
					}
					//System.out.println("[Npp 113]" + nppCur);
				}
				if (!ngayCur.equals(ngayPrev))
				{
					if (ngayPrev.length() > 0)
					{
						String[] cell_total =
						{thoigian + ngayPrev, "Tổng cộng ", formatter.format(tongluong), formatter.format(tongtien)};
						for (int i = 0; i < cell_total.length; i++)
						{
							cell = new PdfPCell(new Paragraph(cell_total[i], bold));
							if (i == 0)
							{
								cell.setHorizontalAlignment(Element.ALIGN_LEFT);
								cell.setColspan(4);
							} else
							{
								cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
							}
							cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
							cell.setPadding(0.1f * CONVERT);
							table.addCell(cell);
						}
					}
					stt = 1;
					tongluong=0;
					tongtien=0;
					ngayPrev = ngayCur;
				}
				soluong = rs.getDouble("peice");
				int qc1 = rs.getInt("qc1");
				int qc2 = rs.getInt("qc2");
				double slThg = soluong / qc1 / qc2;
				String trangthai = rs.getString("TrangThai");
				if (trangthai.equals("0"))
					trangthai = "Chưa nhận hàng";
				else
					trangthai = "Đã nhận hàng";

				sotien = rs.getDouble("amount");
				tongluong += slThg;
				tongtien += sotien;
				String[] spTitles = new String[]
				                               { Integer.valueOf(stt).toString(), rs.getString("docno"), rs.getString("ngaynhan"), rs.getString("SKU_code"), rs.getString("SKU"), formatter.format(slThg), formatter.format(sotien) };

				for (int i = 0; i < spTitles.length; i++)
				{
					cell = new PdfPCell(new Paragraph(spTitles[i], normal));
					if (i <= 4)
					{
						cell.setHorizontalAlignment(Element.ALIGN_LEFT);
					} else
					{
						cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
					}
					cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
					cell.setPadding(0.1f * CONVERT);
					table.addCell(cell);
				}

				stt++;
			}

			if (table != null)
			{
				String[] cell_total =
				{thoigian + ngayPrev, "Tổng cộng ", formatter.format(tongluong), formatter.format(tongtien)};
				for (int i = 0; i < cell_total.length; i++)
				{
					cell = new PdfPCell(new Paragraph(cell_total[i], bold));
					if (i == 0)
					{
						cell.setHorizontalAlignment(Element.ALIGN_LEFT);
						cell.setColspan(4);
					} else
					{
						cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
					}
					cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
					cell.setPadding(0.1f * CONVERT);
					table.addCell(cell);
				}
				rs.close();
				document.add(table);
				document.close();
			}
		} catch (Exception e)
		{
			e.printStackTrace();
		}
	}

	private static String FILE = "D:/temp/FirstPdf.pdf";

	public static void main(String[] arg) throws DocumentException, SQLException, IOException
	{
		Stockintransit obj = new Stockintransit();
		obj.settungay("2013-07-01");
		obj.setdenngay("2013-07-16");
		obj.setuserTen("AAA");
		obj.setuserId("100345");

		Document document = new Document();
		//FileOutputStream outstream = new FileOutputStream(FILE);

		//obj.XuatNhapTonPdf(document, outstream, obj, "", "1");

	}

	public PdfPTable getTable(Date day) throws SQLException, DocumentException, IOException
	{
		PdfPTable table = new PdfPTable(new float[]
		                                          { 2, 1, 2, 5, 1 });
		table.setWidthPercentage(100f);
		table.getDefaultCell().setUseAscender(true);
		table.getDefaultCell().setUseDescender(true);
		table.getDefaultCell().setBackgroundColor(BaseColor.LIGHT_GRAY);
		for (int i = 0; i < 2; i++)
		{
			table.addCell("Location");
			table.addCell("Time");
			table.addCell("Run Length");
			table.addCell("Title");
			table.addCell("Year");
		}
		table.getDefaultCell().setBackgroundColor(null);
		table.setHeaderRows(2);
		table.setFooterRows(1);
		return table;
	}


	public ResultSet getNhomspRs()
	{

		return this.nhomRs;
	}


	public void setNhomspRs(ResultSet nspRs)
	{

		this.nhomRs = nspRs;
	}

	public void BanHangPdf(Document document, ServletOutputStream outstream, IStockintransit obj, String query, String level) throws SQLException, IOException
	{
		try
		{
			PdfWriter.getInstance(document, outstream);
			// document.setMargins(15.0f, 15.0f, 15.0f, 15.0f);
			document.setPageSize(PageSize.A4);
			document.open();

			// chi dinh BaseFont.IDENTITY_H de co the go tieng viet
			BaseFont bf = BaseFont.createFont("C:\\WINDOWS\\Fonts\\times.ttf", BaseFont.IDENTITY_H, BaseFont.EMBEDDED);
			Font header = new Font(bf, 14, Font.BOLD);
			Font bold = new Font(bf, 13, Font.BOLD);
			Font normal = new Font(bf, 10, Font.NORMAL);
			Font underline = new Font(bf, 12, Font.UNDERLINE);
			bold.setColor(BaseColor.RED);
			Paragraph ddh = new Paragraph("BÁN HÀNG THEO THÁNG", bold);
			if (obj.gettungay().length() > 0)
			{
				ddh = new Paragraph("BÁN HÀNG THEO NGÀY", bold);
			}
			ddh.setSpacingAfter(15);
			ddh.setAlignment(Element.ALIGN_CENTER);
			document.add(ddh);

			PdfPCell cell1 = new PdfPCell(new Paragraph("Từ ngày", underline));

			PdfPCell cell2 = new PdfPCell(new Paragraph(obj.gettungay(), normal));

			PdfPCell cell3 = new PdfPCell(new Paragraph("Đến ngày ", underline));

			PdfPCell cell4 = new PdfPCell(new Paragraph(obj.getdenngay(), normal));
			if (obj.getFromMonth().length() > 0)
			{
				cell1 = new PdfPCell(new Paragraph("Từ tháng ", underline));
				cell2 = new PdfPCell(new Paragraph(obj.getFromMonth(), normal));
				cell3 = new PdfPCell(new Paragraph("Đến tháng ", underline));
				cell4 = new PdfPCell(new Paragraph(obj.getToMonth(), normal));
			}

			PdfPTable tableHead = new PdfPTable(4);
			tableHead.setWidthPercentage(100);
			tableHead.setHorizontalAlignment(Element.ALIGN_LEFT);
			tableHead.setSpacingAfter(10);
			float[] with =
			{ 15.0f, 10.0f, 10f, 15.0f }; // set chieu rong cac columns
			tableHead.setWidths(with);

			cell1.setBorder(0);
			cell2.setBorder(0);
			cell3.setBorder(0);
			cell4.setBorder(0);
			tableHead.addCell(cell1);
			tableHead.addCell(cell2);
			tableHead.addCell(cell3);
			tableHead.addCell(cell4);

			PdfPCell cell5 = new PdfPCell(new Paragraph("Ngày báo cáo: ", underline));
			PdfPCell cell6 = new PdfPCell(new Paragraph(getDateTime(), normal));
			PdfPCell cell7 = new PdfPCell(new Paragraph("Được tạo bởi: ", underline));
			PdfPCell cell8 = new PdfPCell(new Paragraph(obj.getuserTen(), normal));
			cell5.setBorder(0);
			cell6.setBorder(0);
			cell7.setBorder(0);
			cell8.setBorder(0);
			tableHead.addCell(cell5);
			tableHead.addCell(cell6);
			tableHead.addCell(cell7);
			tableHead.addCell(cell8);
			tableHead.setSpacingAfter(15);
			tableHead.setSpacingAfter(15);
			document.add(tableHead);
			ResultSet rs;
			rs = this.db.get(query);
			//	//System.out.println("[Init Pdf Ban Hang] " + query);
			PdfPTable table;
			table = null;
			PdfPCell cell = new PdfPCell();
			String nppPrev = "";
			String ngayPrev = "";
			String thoigian = "Ngày  ";
			double tongluong = 0;
			double tongtien = 0;
			double soluong = 0;
			double sotien = 0;
			float[] TABLE_DISTRIBUTOR_WIDTH =
			{ 8.0f, 35.0f, 15.0f, 15.0f, 25.0f };
			float[] TABLE_SALESRES_WIDTH =
			{ 8.0f, 25.0f, 35.0f, 17.0f, 12.0f, 20.0f };
			float[] TABLE_CUSTOMER_WIDTH =
			{ 8.0f, 42.0f, 35.0f, 15.0f, 15.0f, 20.0f };

			NumberFormat formatter = new DecimalFormat("#,###,###.###");
			;
			int stt = 1;
			while (rs.next())
			{
				String nppCur = rs.getString("distcode");

				String ngayCur = rs.getString("ngay");

				if (obj.getFromMonth().length() > 0)
				{
					thoigian = "Tháng  ";
				}

				if (!nppCur.equals(nppPrev))
				{
					if (table != null)
					{
						String[] cell_total =
						{thoigian + ngayPrev, "Tổng cộng ", formatter.format(tongluong), formatter.format(tongtien)};
						for (int i = 0; i < cell_total.length; i++)
						{
							cell = new PdfPCell(new Paragraph(cell_total[i], bold));
							if (i == 0)
							{
								cell.setHorizontalAlignment(Element.ALIGN_LEFT);
								if (level.equals("0"))
								{
									cell.setColspan(2);
								} else if (level.equals("1"))
									cell.setColspan(3);
								else
									cell.setColspan(3);
							} else
							{
								cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
							}
							cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
							cell.setPadding(0.1f * CONVERT);
							table.addCell(cell);
						}
						document.add(table);
					}

					Paragraph p = new Paragraph("Nhà phân phối : " + nppCur + " - " + rs.getString("distributor"), normal);
					p.setSpacingBefore(0.3f * CONVERT);
					p.setSpacingAfter(0.2f * CONVERT);
					p.setAlignment(Element.ALIGN_LEFT);
					document.add(p);

					tongluong = 0;
					tongtien = 0;
					nppPrev = nppCur;
					if (level.equals("0"))
					{
						table = new PdfPTable(TABLE_DISTRIBUTOR_WIDTH.length);
						table.setWidths(TABLE_DISTRIBUTOR_WIDTH);
						table.setWidthPercentage(100);
						String[] spTitles =
						{"STT", "Sản phẩm", thoigian, "Số lượng(THG)", "Thành tiền"};
						for (int i = 0; i < spTitles.length; i++)
						{
							cell = new PdfPCell(new Paragraph(spTitles[i], bold));
							cell.setHorizontalAlignment(Element.ALIGN_CENTER);
							cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
							cell.setPadding(0.1f * CONVERT);
							table.addCell(cell);
						}
					} else if (level.equals("1"))
					{
						table = new PdfPTable(TABLE_SALESRES_WIDTH.length);
						table.setWidths(TABLE_SALESRES_WIDTH);
						table.setWidthPercentage(100);
						String[] spTitles =
						{"STT", "Đại diện", "Sản phẩm", thoigian, "Số lượng(THG)", "Thành tiền"};
						for (int i = 0; i < spTitles.length; i++)
						{
							cell = new PdfPCell(new Paragraph(spTitles[i], bold));
							cell.setHorizontalAlignment(Element.ALIGN_CENTER);
							cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
							cell.setPadding(0.1f * CONVERT);
							table.addCell(cell);
						}
					} else
					{
						table = new PdfPTable(TABLE_CUSTOMER_WIDTH.length);
						table.setWidths(TABLE_CUSTOMER_WIDTH);
						table.setWidthPercentage(100);
						String[] spTitles =
						{"STT", "Khách hàng", "Sản phẩm", thoigian, "Số lượng(THG)", "Thành tiền"};
						for (int i = 0; i < spTitles.length; i++)
						{
							cell = new PdfPCell(new Paragraph(spTitles[i], bold));
							cell.setHorizontalAlignment(Element.ALIGN_CENTER);
							cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
							cell.setPadding(0.1f * CONVERT);
							table.addCell(cell);
						}
					}
					//	//System.out.println("[Npp 113]" + nppCur);
				}
				soluong = rs.getDouble("quantity");
				sotien = rs.getDouble("amount");
				tongluong += soluong;
				tongtien += sotien;
				if (!ngayCur.equals(ngayPrev))
				{
					if (ngayPrev.length() > 0)
					{
						stt = 1;
						String[] cell_total =
						{thoigian + ngayPrev, "Tổng cộng ", formatter.format(tongluong), formatter.format(tongtien)};
						for (int i = 0; i < cell_total.length; i++)
						{
							cell = new PdfPCell(new Paragraph(cell_total[i], bold));
							if (i == 0)
							{
								cell.setHorizontalAlignment(Element.ALIGN_LEFT);
								if (level.equals("0"))
								{
									cell.setColspan(2);
								} else if (level.equals("1"))
									cell.setColspan(3);
								else
									cell.setColspan(3);
							} else
							{
								cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
							}
							cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
							cell.setPadding(0.1f * CONVERT);
							table.addCell(cell);
						}
						tongluong = 0;
						tongtien = 0;
					}

					ngayPrev = ngayCur;
				}

				if (level.equals("0"))
				{
					String[] spTitles = new String[]
					                               { Integer.valueOf(stt).toString(), rs.getString("SKU"), rs.getString("ngay"), formatter.format(soluong), formatter.format(sotien) };
					for (int i = 0; i < spTitles.length; i++)
					{
						cell = new PdfPCell(new Paragraph(spTitles[i], normal));
						if (i <= 2)
						{
							cell.setHorizontalAlignment(Element.ALIGN_LEFT);
						} else
						{
							cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
						}
						cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
						cell.setPadding(0.1f * CONVERT);
						table.addCell(cell);
					}

				} else if (level.equals("1"))
				{
					String[] spTitles = new String[]
					                               { Integer.valueOf(stt).toString(), rs.getString("salesrep"), rs.getString("SKU"), rs.getString("ngay"), formatter.format(soluong), formatter.format(sotien) };
					for (int i = 0; i < spTitles.length; i++)
					{
						cell = new PdfPCell(new Paragraph(spTitles[i], normal));
						if (i <= 3)
						{
							cell.setHorizontalAlignment(Element.ALIGN_LEFT);
						} else
						{
							cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
						}
						cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
						cell.setPadding(0.1f * CONVERT);
						table.addCell(cell);
					}

				} else if (level.equals("2"))
				{
					String[] spTitles = new String[]
					                               { Integer.valueOf(stt).toString(), rs.getString("customer"), rs.getString("SKU"), rs.getString("ngay"), formatter.format(soluong), formatter.format(sotien) };
					for (int i = 0; i < spTitles.length; i++)
					{
						cell = new PdfPCell(new Paragraph(spTitles[i], normal));
						if (i < 4)
						{
							cell.setHorizontalAlignment(Element.ALIGN_LEFT);
						} else
						{
							cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
						}
						cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
						cell.setPadding(0.1f * CONVERT);
						table.addCell(cell);
					}
				}
				stt++;
			}
			if (table != null)
			{
				String[] cell_total =
				{thoigian + ngayPrev, "Tổng cộng ", formatter.format(tongluong), formatter.format(tongtien)};
				for (int i = 0; i < cell_total.length; i++)
				{
					cell = new PdfPCell(new Paragraph(cell_total[i], bold));
					if (i == 0)
					{
						cell.setHorizontalAlignment(Element.ALIGN_LEFT);
						if (level.equals("0"))
						{
							cell.setColspan(2);
						} else if (level.equals("1"))
							cell.setColspan(3);
						else
							cell.setColspan(3);
					} else
					{
						cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
					}
					cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
					cell.setPadding(0.1f * CONVERT);
					table.addCell(cell);
				}
				document.add(table);
				document.close();
			}
		} catch (Exception e)
		{
			e.printStackTrace();
		}
	}

	public void XuatNhapTonPdf(Document document, ServletOutputStream outstream, IStockintransit obj, String query, String giatinh) throws SQLException, IOException
	{
		try
		{
			PdfWriter.getInstance(document, outstream);
			//	document.setMargins(15.0f, 15.0f, 15.0f, 15.0f);
			document.setPageSize(PageSize.A2);
			document.open();

			// chi dinh BaseFont.IDENTITY_H de co the go tieng viet
			BaseFont bf = BaseFont.createFont("C:\\WINDOWS\\Fonts\\times.ttf", BaseFont.IDENTITY_H, BaseFont.EMBEDDED);
			Font header = new Font(bf, 14, Font.BOLD);
			Font bold = new Font(bf, 13, Font.BOLD);
			Font normal = new Font(bf, 10, Font.NORMAL);
			Font underline = new Font(bf, 12, Font.UNDERLINE);
			bold.setColor(BaseColor.RED);
			Paragraph ddh = new Paragraph("XUẤT NHẬP TỒN", bold);
			ddh.setSpacingAfter(15);
			ddh.setAlignment(Element.ALIGN_CENTER);
			document.add(ddh);

			PdfPTable tableHead = new PdfPTable(4);
			tableHead.setWidthPercentage(100);
			tableHead.setHorizontalAlignment(Element.ALIGN_LEFT);
			tableHead.setSpacingAfter(10);
			float[] with =
			{ 15.0f, 10.0f, 10f, 15.0f }; // set chieu rong cac columns
			tableHead.setWidths(with);

			PdfPCell cell1 = new PdfPCell(new Paragraph("Từ ngày: ", underline));
			PdfPCell cell2 = new PdfPCell(new Paragraph(obj.gettungay(), normal));
			PdfPCell cell3 = new PdfPCell(new Paragraph("Đến ngày: ", underline));
			PdfPCell cell4 = new PdfPCell(new Paragraph(obj.getdenngay(), normal));
			cell1.setBorder(0);
			cell2.setBorder(0);
			cell3.setBorder(0);
			cell4.setBorder(0);
			tableHead.addCell(cell1);
			tableHead.addCell(cell2);
			tableHead.addCell(cell3);
			tableHead.addCell(cell4);

			PdfPCell cell5 = new PdfPCell(new Paragraph("Ngày báo cáo: ", underline));
			PdfPCell cell6 = new PdfPCell(new Paragraph(getDateTime(), normal));
			PdfPCell cell7 = new PdfPCell(new Paragraph("Được tạo bởi: ", underline));
			PdfPCell cell8 = new PdfPCell(new Paragraph(obj.getuserTen(), normal));
			cell5.setBorder(0);
			cell6.setBorder(0);
			cell7.setBorder(0);
			cell8.setBorder(0);
			tableHead.addCell(cell5);
			tableHead.addCell(cell6);
			tableHead.addCell(cell7);
			tableHead.addCell(cell8);
			tableHead.setSpacingAfter(15);
			tableHead.setSpacingAfter(15);
			document.add(tableHead);

			// Table Content

			String[] param = new String[13];
			param[0] = obj.getnppId().equals("") ? null : obj.getnppId();
			param[1] = obj.gettungay();
			param[2] = obj.getdenngay();
			param[3] = obj.getkenhId().equals("") ? null : obj.getkenhId();
			param[4] = obj.getnhanhangId().equals("") ? null : obj.getnhanhangId();
			param[5] = obj.getchungloaiId().equals("") ? null : obj.getchungloaiId();
			param[6] = obj.getdvkdId().equals("") ? null : obj.getdvkdId();
			param[7] = obj.getkhoId() == "" ? null : obj.getkhoId();
			param[8] = obj.getkhuvucId() == "" ? null : obj.getkhuvucId();
			param[9] = obj.getvungId() == "" ? null : obj.getvungId();
			param[10] = obj.getvat().equals("") ? null : obj.getvat();
			param[11] = obj.getuserId();
			param[12] = "1";// LAY BAO CAO CENTER

			ResultSet rs = null;

			if (giatinh.equals("1"))
			{
				rs = db.getRsByPro("REPORT_XUATNHAPTON", param);
			} else
			{
				if (giatinh.equals("2"))
				{
					rs = db.getRsByPro("REPORT_XUATNHAPTON_GIABANLECHUAN", param);
				} else
				{
					rs = db.getRsByPro("REPORT_XUATNHAPTON_GIATON", param);
				}
			}
			//	//System.out.println("Query" + query);

			PdfPTable table;
			table = null;
			PdfPCell cell = new PdfPCell();
			String nppPrev = "";
			String ngayPrev = "";
			String spPrev = "";

			double tongluong = 0;
			double tongtien = 0;
			double soluong = 0;
			double sotien = 0;

			Hashtable<String, Integer> getColum = hbGetColumn();

			float[] withs =
			{ 8.0f, 15.0f, 35.0f, 20.0f, 20.0f, 20.0f, 20.0f, 20.0f, 20.0f, 20.0f, 20.0f, 20.0f, 20.0f, 20.0f, 20.0f };
			NumberFormat formatter = new DecimalFormat("#,###,###.###");
			int stt = 0;
			String[] spTitles = new String[15];

			while (rs.next())
			{

				////System.out.println(rs.getString("TYPE") + rs.getString("masp"));

				String nppCur = rs.getString("nppId");
				String ngayCur = rs.getString("TYPE");
				String spId = rs.getString("masp");
				if (!nppCur.equals(nppPrev))
				{
					if (table != null)
					{
						String[] cell_total =
						{"", "Tổng cộng ", formatter.format(tongluong), formatter.format(tongtien)};
						for (int i = 0; i < cell_total.length; i++)
						{
							cell = new PdfPCell(new Paragraph(cell_total[i], bold));
							if (i == 0)
							{
								cell.setHorizontalAlignment(Element.ALIGN_LEFT);
								cell.setColspan(0);
							} else
							{
								cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
							}
							cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
							cell.setPadding(0.1f * CONVERT);
							table.addCell(cell);
						}
						document.add(table);
					}

					Paragraph p = new Paragraph("Nhà phân phối : " + nppCur + " - " + rs.getString("npp"), normal);
					p.setSpacingBefore(0.3f * CONVERT);
					p.setSpacingAfter(0.2f * CONVERT);
					p.setAlignment(Element.ALIGN_LEFT);
					document.add(p);

					tongluong = 0;
					tongtien = 0;
					nppPrev = nppCur;
					table = new PdfPTable(withs.length);
					table.setWidths(withs);
					table.setWidthPercentage(100);

					String[] spTitles1 =
					{"STT", "Kho", "Sản phẩm", "Tồn đầu", "Nhập hàng bán", "Nhập hàng KM", "Xuất hàng bán", "Xuất hàng KM", "Hàng trả về NPP", "KH Trả hàng bán", "KH Trả hàng KM", "Mua hàng NPP", "Bán cho NPP", "Điều chỉnh", "Tồn cuối"};
					for (int i = 0; i < spTitles1.length; i++)
					{
						cell = new PdfPCell(new Paragraph(spTitles1[i], bold));
						cell.setHorizontalAlignment(Element.ALIGN_CENTER);
						cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
						cell.setPadding(0.1f * CONVERT);
						table.addCell(cell);
					}
					////System.out.println("[Npp 113]" + nppCur);
				}

				soluong = rs.getDouble("Quantily");
				sotien = rs.getDouble("amount");
				tongluong += soluong;
				tongtien += sotien;
				if (stt == 0)
				{
					spPrev = spId;
					stt++;
					spTitles[0] = stt + "";
					spTitles[1] = rs.getString("kho");
					spTitles[2] = rs.getString("MASP")+"_"+rs.getString("tensp");
				}

				if (stt != 0 && !spId.trim().equals(spPrev.trim()))
				{
					for (int i = 0; i < spTitles.length; i++)
					{
						cell = new PdfPCell(new Paragraph(spTitles[i], normal));
						cell.setHorizontalAlignment(Element.ALIGN_CENTER);
						cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
						cell.setPadding(0.1f * CONVERT);
						table.addCell(cell);
					}
					spTitles = new String[15];
					spTitles[0] = stt + "";
					spTitles[1] = rs.getString("kho");
					spTitles[2] = rs.getString("MASP")+"_"+rs.getString("tensp");
					spPrev = spId;
					stt++;
				}
				if (getColum.get(ngayCur) != null)
				{
					spTitles[getColum.get(ngayCur)] = formatter.format(soluong);
				}
			}

			for (int i = 0; i < spTitles.length; i++)
			{
				cell = new PdfPCell(new Paragraph(spTitles[i], normal));
				cell.setHorizontalAlignment(Element.ALIGN_CENTER);
				cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
				cell.setPadding(0.1f * CONVERT);
				table.addCell(cell);
			}
			document.add(table);
			document.close();
		} catch (Exception e)
		{
			e.printStackTrace();
		}
	}

	private Hashtable<String, Integer> hbGetColumn()
	{
		Hashtable<String, Integer> ht = new Hashtable<String, Integer>();
		ht.put("1.Tồn đầu", 3);
		ht.put("2.Nhập hàng bán", 4);
		ht.put("3.Nhập hàng KM", 5);
		ht.put("4.Xuất hàng bán", 6);
		ht.put("5.Xuất hàng KM", 7);
		ht.put("6.Hàng trả về NPP", 8);
		ht.put("7.1.KH Trả hàng bán", 9);
		ht.put("7.2.KH Trả hàng KM", 10);
		ht.put("7.3.Mua Hang NPP", 11);
		ht.put("7.3.Ban Cho NPP", 12);
		ht.put("8.Điều chỉnh", 13);
		ht.put("9.Tồn cuối", 14);
		return ht;
	}
	public String getTieuchiId()
	{
		return tieuchiId;
	}

	public void setTieuchiId(String tieuchiId)
	{
		this.tieuchiId = tieuchiId;
	}
	public ResultSet getTieuchiRs()
	{
		return tieuchiRs;
	}

	public void setTieuchiRs(ResultSet tieuchiRs)
	{
		this.tieuchiRs = tieuchiRs;
	}


	public String getNgayketthucctkm() {

		return Ngayketthucctkm;
	}


	public void setNgayketthucctkm(String _Ngayketthucctkm) {

		this.Ngayketthucctkm=_Ngayketthucctkm;

	}
	public String getHangDiDuong()
	{
		return HangDiDuong;
	}

	public void setHangDiDuong(String hangDiDuong)
	{
		HangDiDuong = hangDiDuong;
	}


	public String getNganhHangId() 
	{

		return this.nganhHangId;
	}


	public void SetNganhHangId(String nganhhangId) 
	{
		this.nganhHangId=nganhhangId;		
	}


	public ResultSet getNganhHangRs() 
	{
		this.nganhHangRs=db.get("SELECT PK_SEQ,TEN FROM NGANHHANG WHERE TRANGTHAI=1");
		return this.nganhHangRs;
	}


	public ResultSet setNganhHangRs(ResultSet nganhhangRs) 
	{
		return this.nganhHangRs=nganhhangRs;
	}

	String HoaDonKmDb;

	public String getHoaDonKmDb()
	{
		return HoaDonKmDb;
	}

	public void setHoaDonKmDb(String hoaDonKmDb)
	{
		HoaDonKmDb = hoaDonKmDb;
	}

	String tbhId;

	public String getTbhId()
	{
		return tbhId;
	}

	public void setTbhId(String tbhId)
	{
		this.tbhId = tbhId;
	}


	public void setkh(ResultSet kh) {
		this.khrs=kh;

	}


	public ResultSet kh() {
		String query =
		" select distinct  kh.PK_SEQ, kh.MaFast + ' - '+ kh.Ten as Ten" +
		" from KHACHHANG kh inner join KHACHHANG_TUYENBH kh_tbh on kh.PK_SEQ=kh_tbh.KHACHHANG_FK "+
		" inner join TUYENBANHANG tbh on tbh.PK_SEQ=kh_tbh.TBH_FK" +
		" where 1=1 and kh.TrangThai ='1'";
		if(this.ddkd.length()>0) { query += " and tbh.DDKD_FK ='"+ddkd+"'"; }
		if(nppId!=null && nppId.trim().length()>0) { query+= " and kh.NPP_FK='"+nppId+"'"; }
		if(this.ttId.length()>0) { query+= " and kh.TinhThanh_fk='"+ttId+"'"; }
		this.khrs =  this.db.get( query);
		return this.khrs;
	}


	public void setkhId(String khId) {
		this.khId=khId;

	}


	public String getkhId() {

		return this.khId;
	}


	public void setkhTen(String khTen) {

		this.khTen=khTen;
	}


	public String getkhTen() {

		return this.khTen;
	}


	public ResultSet getkh() {

		return this.khrs;
	}


	public String getNppId() {

		return this.nppID;
	}


	public void setNppId(String nppId) {

		this.nppID=nppId;
	}


	public ResultSet getNppRs() {

		return this.nppRs;
	}


	public void setNppRs(ResultSet nppRs) {

		this.nppRs=nppRs;
	}


	public String getActiveTab() {

		return this.activeTab;
	}


	public void setActiveTab(String active) {
		this.activeTab= active ;

	}


	public ResultSet getETCRs() {

		return this.EtcRs;
	}


	public void setETCRS(ResultSet ETCRs) {

		this.EtcRs= ETCRs;
	}


	public ResultSet getOTCRs() {		
		return this.OtcRs;
	}


	public void setOTCRS(ResultSet OTCRs) {		
		this.OtcRs = OTCRs;
	}


	public ResultSet getKMRs() {

		return this.kmRs;
	}


	public void setKMRS(ResultSet KMRs) {

		this.kmRs= KMRs;
	}

	public void searchQuery_ETC(String searchquery) {
		String sql = "";
		//System.out.println("câu query: ETC" + searchquery);
		if(searchquery.length() > 0)
			sql = searchquery;	
		this.EtcRs= db.get(sql);
	}


	public void searchQuery_OTC(String searchquery) {
		String sql = "";

		//System.out.println("câu query OTC: " + searchquery);
		if(searchquery.length() > 0)
			sql = searchquery;	
		this.OtcRs= db.get(sql);
	}

	public void searchQuery_KM(String searchquery) {
		String sql = "";

		if(searchquery.length() > 0)
			sql = searchquery;	
		this.kmRs= db.get(sql);
	}


	public String getNvgnId() {

		return this.nvgnId;
	}


	public void setNvgnId(String nvgnId) {

		this.nvgnId=nvgnId;
	}

	ResultSet nvgnRs;
	public ResultSet getNvgnRs() {

		return this.nvgnRs;
	}


	public void setNvgnRs(ResultSet nvgnRs) {

		this.nvgnRs=nvgnRs;
	}


	public void setTotal_Query(String searchquery) 
	{
		//System.out.println("___Total_Query__" + searchquery);
		this.totalRs= db.get(searchquery);

	}

	ResultSet totalRs;
	public ResultSet getTotalRs()
	{
		return totalRs;
	}
	public void setTotalRs(ResultSet totalRs)
	{
		this.totalRs=totalRs;
	}

	String trangthai;

	public String getTrangthai()
	{
		return trangthai;
	}

	public void setTrangthai(String trangthai)
	{
		this.trangthai = trangthai;
	}


	public String getTtId()
	{
		return ttId;
	}
	public void setTtId(String ttId)
	{
		this.ttId = ttId;
	}


	public ResultSet getTtRs()
	{
		Utility  util = new Utility();
		String query="select PK_SEQ, TEN from tinhthanh where 1=1 ";
		if(vungId.length()>0) {	query+=" and vung_fk = '"+vungId+"'"; }
		query+=" and pk_seq in  "+util.Quyen_TinhThanh(this.userId)+" ";
		this.ttRs= this.db.get(query);
		return ttRs;
	}
	public void setTtRs(ResultSet ttRs)
	{
		this.ttRs = ttRs;
	}

	String cndt,kh;
	private String coHoadon;
	private String spId;
	private ResultSet SpRs;
	private ResultSet rsBaocao;
	private String laytheo;
	String loaihoadon = "";
	
	public String getLoaihoadon() {
		return loaihoadon;
	}
	public void setLoaihoadon(String loaihoadon) {
		this.loaihoadon = loaihoadon;
	}
	
	public String 	getMucCN_DT()
	{
		return 	this.cndt;
	}

	public void setMucCN_DT(String cndt)
	{
		this.cndt=cndt;
	}

	public String getMuc_KhachHang()
	{
		return 	this.kh;
	}

	public void setMuc_KhachHang(String cndt)
	{
		this.kh=cndt;
	}

	public ResultSet getTinhthanh()
	{
		this.tinhthanh = db.get("select pk_seq,ten from tinhthanh");
		return this.tinhthanh;
	}

	public void setTinhthanh(ResultSet tinhthanh)
	{
		this.tinhthanh = tinhthanh;
	}


	public String getCoHoadon() {

		return this.coHoadon;
	}


	public void setCoHoadon(String value) {
		this.coHoadon = value;
	}


	public ResultSet getSpRs() {

		String st = "select pk_seq,ma,ten from sanpham where trangthai ='1' ";
		if (this.nhanhangId.length() > 0) { st = st + " and nhanhang_fk ='" + this.nhanhangId + "'"; }
		if (this.chungloaiId.length() > 0) { st = st + " and chungloai_fk ='" + this.chungloaiId + "'"; }
		if (this.dvkdId.length() > 0) { st = st + " and dvkd_fk ='" + this.dvkdId + "'"; }
		if (this.dvdlId.length() > 0) { st = st + " and dvdl_fk ='" + this.dvdlId + "'"; }
		if (this.nganhHangId!=null && this.nganhHangId.length()>0) { st+=" and NGANHHANG_fk ='"+this.nganhHangId+"'"; }
		if (this.Nhospid.length() > 0) { st = st + " and pk_seq in  (select sanpham_fk from NhomHang_SanPham where nhomhang_fk='" + this.Nhospid + "')"; }
		this.SpRs = db.get(st);
		return this.SpRs;
	}


	public void setSpId(String spid) {
		this.spId = spid;
	}

	public String getSpId() {
		return this.spId;
	}

	public void setRSBaocao(ResultSet rs) {
		this.rsBaocao = rs;
	}

	public ResultSet getRSBaocao() {
		return this.rsBaocao;
	}

	public String getLaytheo() {
		return this.laytheo;
	}

	public void setLaytheo(String value) {
		this.laytheo = value;
	}

	String quy;
	public String getQuy()
	{
		return quy;
	}

	public void setQuy(String quy)
	{
		this.quy = quy;
	}

	public void searchQuery_HDKhac(String searchquery) {
		String sql = "";
		//System.out.println("câu query: HDKhac " + searchquery);
		if(searchquery.length() > 0)
			sql = searchquery;	
		this.HdKhacRs= db.get(sql);
	}

	public ResultSet getHdKhacRs() {

		return this.HdKhacRs;
	}

	public void setHdKhacRs(ResultSet HdKhacRs) {

		this.HdKhacRs=HdKhacRs;
	}
	ResultSet dataRs ;
	public void setDataRs(String query)
	{
		this.dataRs = db.get(query);
	}
	public ResultSet getDataRs()
	{
		return this.dataRs;
	}
	ResultSet dataRs2 ;
	public void setDataRs2(String query)
	{
		this.dataRs2 = db.get(query);
	}
	public ResultSet getDataRs2()
	{
		return this.dataRs2;
	}
	
	public void getRsnpp(String id)
	{
		String sql="";
		if(!this.trungtam.equals("TT") )
		 sql ="select pk_seq,ten from nhaphanphoi where tructhuoc_fk="+id;
		else
		sql ="select pk_seq,ten from nhaphanphoi  where pk_seq<>1";
		this.rsnppID=db.get(sql);
		//System.out.println("lay nppids "+sql);
	}
	
	public ResultSet getNhomhangRs() {
		return nhomhangRs;
	}

	public void setNhomhangRs(ResultSet nhomhangRs) {
		this.nhomhangRs = nhomhangRs;
	}

	public String getNhomhangid() {
		return nhomhangid;
	}

	public void setNhomhangid(String nhomhangid) {
		this.nhomhangid = nhomhangid;
	}
	public String getTrungtam() {
		return trungtam;
	}

	public void setTrungtam(String trungtam) {
		this.trungtam = trungtam;
	}
	public ResultSet getRsnppupload() {
		return rsnppupload;
	}

	public void setRsnppupload(ResultSet rsnppupload) {
		this.rsnppupload = rsnppupload;
	}
	public String getTableDbName() {
		return tableDbName;
	}
	public void setTableDbName(String tableDbName) {
		this.tableDbName = tableDbName;
	}
	public DefaultTableModel getTableDbRS() {
		return tableDbRS;
	}
	public DefaultTableModel getDataDbRs() {
		return dataDbRs;
	}
	
	public String getTctctId() {
		return tctctId;
	}
	
	public void setTctctId(String tctctId) {
		this.tctctId = tctctId;
		if(this.tctctId.trim().length() > 0)
		{
			String query ="select diengiai,tieuchi from tieuchithuong_chitiet where pk_seq = "+ this.tctctId;
			//System.out.println("query get dien giai= "+ query);
			ResultSet rs = db.get(query);
			try {
				rs.next();
				this.diengiaiTctctId = rs.getString("diengiai");
				this.tieuchi = rs.getString("tieuchi");
				 rs.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}

		}
		
		
	}
	
	public String getDiengiaiTctctId() {
		return diengiaiTctctId;
	}

	public ResultSet getTctctRs() {
	
		String monthStr = " month(getdate() )";
		String yearStr = "  year(getdate() )";
		if(this.month.trim().length() > 0 && this.year.trim().length() > 0)
		{
			monthStr = this.month;
			yearStr = this.year;
		}
		String query =  "select a.pk_seq, a.diengiai from tieuchithuong_chitiet a" +
						" inner join tieuchitinhthuong b on a.TIEUCHITINHTHUONG_FK = b.PK_SEQ where b.THANG = "+monthStr+" and b.NAM = "+yearStr+
						" and b.TRANGTHAI = 1 and b.LOAI = 1 " ;
			//System.out.println("query loaichitieu= "+ query);
			this.tctctRs = db.get(query);
		
		return tctctRs;
	}
	
	public dbutils getDb() {
		return db;
	}
	
	public String getNamHienTai() {
		return namHienTai;
	}
	public void setNamHienTai(String namHienTai) {
		this.namHienTai = namHienTai;
	}
	public String getNamTruoc() {
		return namTruoc;
	}
	public void setNamTruoc(String namTruoc) {
		this.namTruoc = namTruoc;
	}
	
	String nppId_BCTKKH = "";
	String ddkdId_BCTKKH = "";
	public String getNppId_BCTKKH() {
		return nppId_BCTKKH;
	}
	public void setNppId_BCTKKH(String nppId_BCTKKH) {
		this.nppId_BCTKKH = nppId_BCTKKH;
	}
	public String getDdkdId_BCTKKH() {
		return ddkdId_BCTKKH;
	}
	public void setDdkdId_BCTKKH(String ddkdId_BCTKKH) {
		this.ddkdId_BCTKKH = ddkdId_BCTKKH;
	}
	public void initBCTonKhoKH() {
		Utility Ult = new Utility();
		String query="";
		query="select pk_Seq,ten,ma from nhaphanphoi where loainpp in(0,4) and trangthai=1 and pk_Seq in (" + Ult.quyen_npp(this.userId)+") and pk_seq != 1";
		//System.out.println("NPPRS: "+query +" nppbmid: "+this.nppId_BCTKKH);
		this.nppRs = db.get(query);
		query = "SELECT pk_seq,ten,mafast FROM DAIDIENKINHDOANH d where trangthai=1 ";
		this.rsddkd = db.get(query);
		
	}
	public void getNppInfo()
	{	
		geso.dms.distributor.util.Utility util = new geso.dms.distributor.util.Utility();
		this.nppId = util.getIdNhapp(this.userId);
		this.nppTen = util.getTenNhaPP();

	}
	public void initBCChiPhiKhuyenMai() {
		Utility util = new Utility();
		if (this.nppId != null && this.nppId != "") {
			if (this.nppId.length() > 0) {
				String query = "select pk_seq, ten from nhaphanphoi where pk_seq ="+this.nppId;
				this.rsnppupload=db.getScrol(query);
			}
		}
		else {
			createNPPRs(util);
		}
		
		createDdkdRs(util);
		createKhRS1();
		createVungRs();
		createtKhuVucRs(util);
		
	}
	
	public void initBCSuDungQuaTang() {
		Utility util = new Utility();
		if (this.nppId.length() > 0) {
			String query = "select pk_seq, ten from nhaphanphoi where pk_seq ="+this.nppId;
			this.rsnppupload=db.getScrol(query);
		}
		else 
		{
			createNPPRs(util);
		}
		
		createDdkdRs(util);
		createKhRS1();
		createVungRs();
		createtKhuVucRs(util);
		
		String query = " select pk_seq, diengiai from phanbo_quatang where trangthai = 1 ";
		if(this.nppId.trim().length() > 0)
			query +=" and npp_fk =  "+ this.nppId;
		this.rsPrograms = db.get(query);
		
	}
	
	
	
	
	public void createtKhuVucRs(Utility Ult)
	{
		if (this.vungId.length() > 0)
		{
			this.khuvuc = db.get("select pk_seq,ten from khuvuc where vung_fk ='" + this.vungId + "'");
		} else
		{
			String query_khuvuc=" select PK_SEQ, TEN from KHUVUC "
				+ "	where PK_SEQ in (select KHUVUC_Fk from NHAPHANPHOI "
				+ "	where pk_seq in "+ Ult.quyen_npp(userId)+")"; 	
			//System.out.println("Query Get KV: "+query_khuvuc);
			this.khuvuc= db.get(query_khuvuc);		
		}
	}
	
	public void createVungRs()
	{
		this.vung = db.get("select pk_seq,ten,diengiai from vung ");
	}
	
	public void createDdkdRs(Utility util) {
		
		String query = " SELECT pk_seq,ten FROM DAIDIENKINHDOANH d where trangthai=1 ";
		
		if ( this.nppId!=null&& this.nppId.length() > 0)
		{
			query += " and d.PK_SEQ in (Select ddkd_fk from DAIDIENKINHDOANH_NPP where npp_fk='"+this.nppId+"') ";
		}
		if(this.phanloai.equals("2"))
		{
			query+= " and d.PK_SEQ in (" + Utility.PhanQuyenDDKD(this.userId)+")";
		}
		if(this.ttId.length()>0)
		{
			query+=" and d.PK_SEQ in (select ddkd_fk from daidienkinhdoanh_npp where npp_fk in (Select pk_Seq from nhaphanphoi where tinhthanh_fk='"+this.ttId+"')) ";
		}
		//System.out.println("_________NVBH"+query);
		this.setRsddkd(db.get(query));
		
		
	
	}
	
	public void createNPPRs(Utility util)
	{

		String sql = "select pk_seq,ten from nhaphanphoi where trangthai ='1' "; //and loainpp <> 4 
		String sql1 = "select pk_seq,ten from nhaphanphoi where trangthai ='1' and loainpp not in (2,3)   ";
		
		if(this.ttId.length()>0)
		{
			sql+=" and TinhThanh_fk='"+this.ttId+"'";
			sql1+=" and TinhThanh_fk='"+this.ttId+"'";
		}

		if(this.phanloai.equals("2"))
		{
			sql+= " and pk_Seq in " + util.quyen_npp(userId)+"";
			sql1+= " and pk_Seq in " + util.quyen_npp(userId)+"";
		}
		
		if (this.kenhId.length() > 0)
			sql = sql + " and pk_seq in (select npp_fk from NHAPP_KBH where kbh_fk ='" + this.kenhId + "')";

		if(this.gsbhId.length() > 0)
		{
			sql = sql + " and pk_seq in ("+Utility.PhanQuyenNppTheoGSBH(this.gsbhId)+") ";			
		}
		
		
		
		if (this.khuvucId.length() > 0)
		{
			sql = sql + " and pk_seq in ("+Utility.PhanQuyenNppTheoKV(this.khuvucId)+") ";	
			sql1 = sql1 + " and pk_seq in ("+Utility.PhanQuyenNppTheoKV(this.khuvucId)+") ";	
		}
		else
			if (this.vungId.length() > 0)
			{
				sql = sql + " and pk_seq in ("+Utility.PhanQuyenNppTheoVung(this.vungId)+") ";	
				sql1 = sql1 + " and pk_seq in ("+Utility.PhanQuyenNppTheoVung(this.vungId)+") ";	
			}
		//System.out.println("Get NPP :"+sql);
		
		if(this.programsId.trim().length() >0 && this.nppId.trim().equals("")) 
		{
			sql = sql + 	" and pk_seq in (select npp_fk from  CTKM_NPP inner join CTKHUYENMAI ctkm on ctkm.pk_seq=CTKM_NPP.ctkm_fk where ctkm.scheme='"+this.getPrograms()+"'    ) ";
		}	
		//	//System.out.println("Get NPP :" + sql);

		sql += " order by ten ";
		this.npp = db.getScrol(sql);
		this.rsnppupload=db.getScrol(sql1);

		//System.out.println("ASM " + sql1);
	}
	@Override
	public String getQhId() {
		// TODO Auto-generated method stub
		return this.qhId;
	}
	@Override
	public void setQhId(String qhId) {
		// TODO Auto-generated method stub
		this.qhId = qhId;
	}
	@Override
	public ResultSet getQh() {
		if (this.tinhthanhid != null && this.tinhthanhid.length() > 0){ this.qh = this.db.get("select ten as qhTen, pk_seq as qhId from quanhuyen where tinhthanh_fk='"+ this.tinhthanhid +"' order by ten"); }
		else { this.qh = this.db.get("select ten as qhTen, pk_seq as qhId from quanhuyen order by ten"); }
		return this.qh;
	}
	@Override
	public void setQh(ResultSet qh) {
		// TODO Auto-generated method stub
		this.qh = qh;
	}
	@Override
	public String getPhuongxa() {
		// TODO Auto-generated method stub
		return this.phuongxa;
	}
	@Override
	public void setPhuongxa(String phuongxa) {
		// TODO Auto-generated method stub
		this.phuongxa = phuongxa;
	}
	@Override
	public ResultSet getPhuongxaRs() {
		String querypx = "";
		if (this.qhId != null && this.qhId.length() > 0){
			querypx = "\n select pk_Seq, ten from phuongxa ";
			querypx += "\n where quanhuyen_fk = '"+this.qhId+"'";
			this.px =this.db.get(querypx);
		}
		return this.px;
	}
	
	ResultSet  anhtrungbayRs ;
	public void setAnhtrungbayRs(String query) 
	{
		this.anhtrungbayRs = db.get(query);
	}
	public ResultSet getAnhtrungbayRs() 
	{
		return this.anhtrungbayRs;
	}
	
	public void init_BCPG()
	{
		Utility Ult = new Utility();
		String query = "";

		try{
			query = "select pk_seq,ten from tinhthanh ";
			this.tinhthanh = db.get(query);
			query="select phanloai,loai from nhanvien where pk_seq="+this.userId;
			ResultSet rs=this.db.get(query);
			if(rs!=null)
			{
				if(rs.next())
				{
					this.phanloai = rs.getString("phanloai");
					//System.out.println("Phan loai : "+this.phanloai);					 				
					this.loaiUser =  rs.getString("loai");
					if( rs.getString("phanloai").equals("1"))
					{
						this.nppId = Ult.getIdNhapp(this.userId);
						this.nppTen = Ult.getTenNhaPP();
					}
					rs.close();
				}
			}
		}catch(Exception er){ er.printStackTrace(); }
		
		query =  "\n select pk_seq,ten,diengiai from vung where 1 = 1 ";
		if(this.phanloai.equals("2") )
		{
			if(this.loaiUser.equals("2"))
				query += " and pk_seq in (select distinct kv.VUNG_FK from ASM_KHUVUC a inner join KHUVUC kv on a.KHUVUC_FK = kv.PK_SEQ where a.ASM_FK = (select top(1) asm_fk from NHANVIEN where PK_SEQ ='"+userId+"'  )  )";
			else if(this.loaiUser.equals("3"))
				query += " and pk_seq in (select distinct kv.VUNG_FK from GSBH_KHUVUC a inner join KHUVUC kv on a.KHUVUC_FK = kv.PK_SEQ where a.GSBH_FK = (select top(1) gsbh_fk from NHANVIEN where PK_SEQ ='"+userId+"'  )  )";

		}
		this.vung = db.get(query);
		
		
		query = " select pk_seq,ten, ma " +
				" from nhaphanphoi where trangthai ='1' ";
		
		if (this.vungId.length() > 0) {
			query += " and khuvuc_fk in (select pk_seq from khuvuc where vung_fk in ("	+ this.vungId + ") )";
		}
		if(this.khuvucId.trim().length() > 0)
			query +=" and khuvuc_fk in  "+this.khuvucId+"   ";
		
		if(this.phanloai.equals("2"))
			query=query+" and pk_seq in "+Ult.quyen_npp(this.userId);
		
		this.npp = db.get(query);
		
		
		query = "\n SELECT DISTINCT GSBH.PK_SEQ, GSBH.TEN " +
				"\n FROM GIAMSATBANHANG GSBH " +
				"\n WHERE GSBH.TRANGTHAI = '1'" ;

		if (this.vungId.length() > 0) {
			query += " and pk_seq in ( select gsbh_fk from gsbh_khuvuc where khuvuc_fk in ( select pk_seq from khuvuc where vung_fk in ("	+ this.vungId + ") )  )";
		}
		if(this.nppId.trim().length()  > 0)
			query +=" and pk_seq in ( select gsbh_fk from nhapp_giamsatbh where npp_fk in ("+this.nppId+") ) ";
		
		if(this.phanloai.equals("2"))
			query=query+" and pk_seq in ( select gsbh_fk from nhapp_giamsatbh where npp_fk in "+Ult.quyen_npp(this.userId)+"  ) ";
		if(this.loaiUser.equals("3"))
			query +=" and pk_seq in ( select top(1) gsbh_fk from NHANVIEN where PK_SEQ ='"+this.userId+"' ) ";
		
		this.gsbh = db.get(query);
		
		
		query = "\n SELECT pk_seq,ten FROM DAIDIENKINHDOANH d " +
				"\n where   d.trangthai=1 and isnull(d.isPG,0) = 1 ";
		if(this.phanloai.equals("2"))
			query=query+" and d.NPP_FK in "+Ult.quyen_npp(this.userId)+"    ";
		if(this.nppId.trim().length()  > 0)
			query +=" and d.npp_fk = "  + this.nppId;
		if(this.gsbhId.trim().length() > 0)
			query +=" and d.pk_seq in (select ddkd_fk from ddkd_gsbh where gsbh_fk =  "+this.gsbhId+"  ) ";
		if(this.vungId.trim().length() > 0)
			query +=" and d.NPP_FK in ( select pk_seq from nhaphanphoi where khuvuc_fk in (select pk_seq from khuvuc where vung_fk = "+this.vungId+" )  ) ";
		if(this.khuvucId.trim().length() > 0)
			query +=" and d.NPP_FK in ( select pk_seq from nhaphanphoi where khuvuc_fk in  "+this.khuvucId+"  ) ";
		this.rsddkd = db.get(query);
	}
	
	String text_baocaoSR = "";
	ArrayList<String> arr_text_baocaoSR;
	
	public ArrayList<String> getArr_text_baocaoSR() {
		return arr_text_baocaoSR;
	}
	public void setArr_text_baocaoSR(ArrayList<String> arr_text_baocaoSR) {
		this.arr_text_baocaoSR = arr_text_baocaoSR;
	}
	public String getText_baocaoSR() {
		return text_baocaoSR;
	}
	public void setText_baocaoSR(String text_baocaoSR) {
		this.text_baocaoSR = text_baocaoSR;
	}
	
	public void setcovat(String covat) {

		this.covat = covat;
	}

	public String getcovat() {

		return this.covat;
	}
	
	public ResultSet getNhanVienRs( String loai)
	{
		String query = "";
		if(loai.equals("1"))
			query = " select nv.pk_seq,nv.ten,nv.mafast ma  from daidienkinhdoanh nv where nv.trangthai = 1 ";
		else if(loai.equals("2"))
			query = " select nv.pk_seq,nv.ten,nv.mafast ma  from giamsatbanhang nv where nv.trangthai = 1 ";
		else if(loai.equals("3"))
			query = " select nv.pk_seq,nv.ten,nv.mafast ma  from asm nv where nv.trangthai = 1 ";
		return db.get(query);
	}
	
	
	/// bộ báo cáo doanh thu Dược
	String DSKHT_client="";
	public String getDSKHT_client() {
		return DSKHT_client;
	}
	public void setDSKHT_client(String dSKHT_client) {
		DSKHT_client = dSKHT_client;
	}
	String thangbd  = "";
	String thangkt  = "";
	String nambd = "";
	String namkt = "";
	String DSKHT="";
	public String getThangbd() {
		return thangbd;
	}
	public void setThangbd(String thangbd) {
		this.thangbd = thangbd;
	}
	public String getThangkt() {
		return thangkt;
	}
	public void setThangkt(String thangkt) {
		this.thangkt = thangkt;
	}
	public String getNambd() {
		return nambd;
	}
	public void setNambd(String nambd) {
		this.nambd = nambd;
	}
	public String getNamkt() {
		return namkt;
	}
	public void setNamkt(String namkt) {
		this.namkt = namkt;
	}
	public String getDSKHT() {
		return DSKHT;
	}
	public void setDSKHT(String dSKHT) {
		DSKHT = dSKHT;
	}
	String laynk="";
	public String getLaynk() {
		return laynk;
	}
	public void setLaynk(String laynk) {
		this.laynk = laynk;
	}
	String action;
	public String get_Action()
	{
		return action;
	}
	public void set_Action(String timkiem)
	{
		this.action = timkiem;
	}
	public ResultSet getKhachHangRs()
	{
		String query="select pk_seq,isnull(mafast,'') +' ' + ten + ' ' + isnull(diachi,'') as Ten from khachhang where 1=1 ";
		if(this.view.length()>0 )
		{
			query+=" and npp_fk in "+Utility.Quyen_npp(userId);
		}
		
		if( this.nppId != null && this.nppId.length()>0)
			query+=" and npp_fk='"+this.nppId+"' ";
		
		if(this.ddkd.length()>0)
		{	query+= " and a.khachhang_fk in (select khachhang_fk from KHACHHANG_TUYENBH where tbh_fk in (select pk_Seq from tuyenbanhang where ddkd_Fk ='"+this.ddkd+"'))"; }
		
		if( this.tinhthanhid != null && this.tinhthanhid.length()>0) 	 {	query+=" and tinhthanh_fk = '"+ this.tinhthanhid +"' "; }
		if( this.qhId != null &&this.qhId.length()>0) 	 {	query+=" and quanhuyen_fk = '"+ this.qhId +"' "; }
		if( this.phuongxa != null &&this.phuongxa.length()>0) {	query+=" and phuongxa = '"+ this.phuongxa +"' "; }
		
		return db.get(query);
	}
	public ResultSet getNhaphanphoiRs()
	{
		String sql = "select pk_seq,ten from nhaphanphoi where trangthai ='1'    "; 
		if(this.phanloai.equals("1"))
		{
			sql += " and pk_seq = " + this.nppId;
		}
		if(this.ttId.length()>0)
		{
			sql+=" and TinhThanh_fk='"+this.ttId+"'";

		}

	
		if(this.phanloai.equals("2"))
		{
			sql+= " and pk_Seq in " + Utility.Quyen_npp(this.userId);

		}


		if (this.khuvucId.length() > 0)
		{
			sql = sql + " and khuvuc_fk ='" + this.khuvucId + "'";
		}
		if (this.vungId.length() > 0)
		{
			sql = sql + " and khuvuc_fk in (select pk_seq from khuvuc where vung_fk ='" + this.vungId + "')";
		}
		// //System.out.println("Get NPP :"+sql);
		if (this.kenhId.length() > 0)
			sql = sql + " and pk_seq in (select npp_fk from NHAPP_KBH where kbh_fk ='" + this.kenhId + "')";

		if(this.gsbhId.length() > 0)
		{
			sql = sql + " and pk_seq in (select npp_fk from NHAPP_GIAMSATBH where gsbh_fk = '"+ this.gsbhId +"')";			
		}

		if(this.programsId.trim().length() >0 && this.nppId.trim().equals("")) 
		{
			sql = sql + " and pk_seq in (select npp_fk from  CTKM_NPP inner join CTKHUYENMAI ctkm on ctkm.pk_seq=CTKM_NPP.ctkm_fk where ctkm.scheme='"+this.getPrograms()+"'    ) ";
		}	

		sql += " order by ten ";
		
		System.out.println("SQL NP = "+ sql);
		return db.get(sql);
	}
	public String getPhanloai() {
		return phanloai;
	}
	public void setPhanloai(String phanloai) {
		this.phanloai = phanloai;
	}
}
