package geso.dms.center.beans.nhaphanphoi.imp;
import geso.dms.center.beans.nhaphanphoi.INhaphanphoi;
import geso.dms.center.db.sql.dbutils;
import geso.dms.center.util.Utility;
import geso.dms.center.util.UtilitySyn;

import java.sql.ResultSet;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.servlet.http.HttpServletRequest;



public class Nhaphanphoi implements INhaphanphoi
{
	private static final long serialVersionUID = -9217977546733610214L;

	String userId;
	String id;
	String ten;
	String diachi;//dia chi nhan hang
	String diachixhd;
	String IdKhoTT;
	String masothue;
	String tpId;
	String qhId;
	String sodienthoai;
	String trangthai;
	String ma;
	String msg;
	String prisec;
	ResultSet khuvucList;
	String kvId;
	String tuvanchuyen;
	String sbhId;
	String tenkyhd;
	ResultSet tp;
	ResultSet qh;

	String kyhieuHD;
	String SoHDTu;
	String SoHDDen;

	ResultSet gsbhList;
	String gsbhIds;

	ResultSet nvbhList;
	String nvbhIds;

	ResultSet rs_khott;
	ResultSet rs_tinhthanh;
	ResultSet rs_quanhuyen;

	String tennguoidaidien;
	String fax;
	String email;
	String hinhthucthanhtoan;
	String nganhang;
	String sotk;
	String ghichu;
	String ngaybatdau;
	String ngayketthuc;
	String dvkdId;
	String chietkhau;

	String loaiNPP;
	ResultSet loaiNppRs ;
	ResultSet tructhuocRs;
	String tructhuocId;
	
	ResultSet capcnRs;
	String idcapcn;
	
	
	String isKH;

	String xuattaikho;
	String tuxuatETC;
	String tutaohoadonOTC;
	String dungchungkenh = "1";

	ResultSet loaicnRs;
	String loaicn;

	String Hanmucno;
	String Songayno;
	String CMTND;

	String thukho;
	String b2b;
	String PTchietkhau = "";
	String loaicongno ="0";
	
	//Thêm địa bàn
	String diabanId;
	ResultSet diabanRs;
	
	String quanlySellOut = "1";
	String tyleOutIn = "";
	
	
	public String getQuanlySellOut() {
		return quanlySellOut;
	}
	public void setQuanlySellOut(String quanlySellOut) {
		this.quanlySellOut = quanlySellOut;
	}
	public String getTyleOutIn() {
		return tyleOutIn;
	}
	public void setTyleOutIn(String tyleOutIn) {
		this.tyleOutIn = tyleOutIn;
	}
	
	
	public String getDiabanId() {
		return diabanId;
	}
	public void setDiabanId(String diabanId) {
		this.diabanId = diabanId;
	}
	public ResultSet getDiabanRs() {
		return diabanRs;
	}
	public void setDiabanRs(ResultSet diabanRs) {
		this.diabanRs = diabanRs;
	}
	public String getB2b() {
		return b2b;
	}

	public void setB2b(String b2b) {
		this.b2b = b2b;
	}

	private String tonkhoAntoan ="";

	private String tansuatDathang ="";

	String tonkhotoida = "";

	public String getCMTND() {
		return CMTND;
	}

	public void setCMTND(String cMTND) {
		CMTND = cMTND;
	}

	dbutils db;

	public Nhaphanphoi(String[] param)
	{
		this.id = param[0];
		this.ten = param[1];
		this.diachi = param[2];
		this.sodienthoai = param[3];
		this.trangthai = param[4];
		this.kvId = param[11];
		this.ma = param[13];
		this.msg = "";
		this.db = new dbutils();
		this.tuvanchuyen="";
		this.dvkdId="";
		this.chietkhau="0";
		this.loaiNPP = "";
		this.tructhuocId = "";
		this.idcapcn = "";
		this.gsbhIds = "";
		this.nvbhIds = "";
		this.isKH = "";
		this.kyhieuHD="";
		this.SoHDTu= "";
		this.SoHDDen="";
		this.xuattaikho ="";
		this.tuxuatETC = "0";
		this.tutaohoadonOTC = "0";
		this.dungchungkenh = "1";
		this.loaicn="1";
		this.makho="";
		this.manx="";
		this.tenkyhd="";
		this.Hanmucno = "";
		this.Songayno  = "";
		this.b2b="";
		this.thukho = "";
	}

	public Nhaphanphoi(String id, String isKH)
	{
		this.id = id;
		this.ten = "";
		this.diachi = "";
		this.masothue = "";
		this.diachixhd = "";
		this.IdKhoTT = "";
		this.tpId = "";
		this.qhId = "";
		this.sodienthoai = "";
		this.trangthai = "2";	
		this.kvId = "";
		this.diabanId = "";
		this.msg = "";
		this.ma = "";
		this.prisec = "";
		this.tennguoidaidien = "";
		this.ngaybatdau = "";
		this.ngayketthuc = "";
		this.nganhang = "";
		this.sotk = "";
		this.email = "";
		this.fax = "";
		this.ghichu = "";
		this.hinhthucthanhtoan = "";
		this.tuvanchuyen = "";
		this.dvkdId = "";
		this.chietkhau = "0";
		this.loaiNPP = "";
		this.tructhuocId = "";
		this.idcapcn = "";
		this.gsbhIds = "";
		this.nvbhIds = "";
		this.isKH = isKH;
		this.kyhieuHD="";
		this.SoHDTu= "";
		this.SoHDDen="";
		this.xuattaikho ="";
		this.tuxuatETC = "0";
		this.tutaohoadonOTC = "0";
		this.dungchungkenh = "1";
		this.makho="";
		this.manx="";
		this.loaicn="1";
		this.tenkyhd="";
		this.Hanmucno = "0";
		this.Songayno  = "0";
		this.CMTND="";
		this.thukho = "";
		this.b2b="";
		this.db = new dbutils();
		
		if (id.length() > 0)
			this.init();
		else
			this.createRS();
	}	

	public String getXuattaikho() {
		return xuattaikho;
	}

	public void setXuattaikho(String xuattaikho) {
		this.xuattaikho = xuattaikho;
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

	public String getTen() 
	{
		return this.ten;
	}

	public void setTen(String ten) 
	{
		this.ten = ten;
	}

	public String getDiachi() 
	{
		return this.diachi;
	}

	public void setDiachi(String diachi) 
	{
		this.diachi = diachi;
	}

	public String getTpId() 
	{
		return this.tpId;
	}

	public void setTpId(String tpId) 
	{
		this.tpId = tpId;
	}

	public String getQhId() 
	{
		return this.qhId;
	}

	public void setQhId(String qhId) 
	{
		this.qhId = qhId;
	}

	public String getSodienthoai() 
	{
		return this.sodienthoai;
	}

	public void setSodienthoai(String sodienthoai) 
	{
		this.sodienthoai = sodienthoai;
	}

	public String getTrangthai() 
	{
		return this.trangthai;
	}

	public void setMaSAP(String ma) 
	{
		this.ma = ma;
	}

	public String getMaSAP() 
	{
		return this.ma;
	}

	public void setTrangthai(String trangthai) 
	{
		this.trangthai = trangthai;
	}

	public String getMessage() 
	{
		return this.msg;
	}

	public void setMessage(String msg) 
	{
		this.msg = msg;
	}

	public ResultSet getTp() 
	{
		return this.tp;
	}

	public void setTp(ResultSet tp) 
	{
		this.tp = tp;
	}

	public ResultSet getQh() 
	{
		return this.qh;
	}

	public void setQh(ResultSet qh) 
	{
		this.qh = qh;
	}

	public ResultSet getKhuvuc() 
	{
		return this.khuvucList;
	}

	public void setKhuvuc(ResultSet khuvucList) 
	{
		this.khuvucList = khuvucList;
	}

	public String getKvId()
	{
		return this.kvId;
	}

	public void setKvId(String kvId)
	{
		this.kvId = kvId;
	}

	public String getPriSec() 
	{
		if (this.prisec==null){
			return "";
		}else{
			return this.prisec;
		}
	}

	public void setPriSec(String prisec) 
	{
		this.prisec = prisec;
	}

	public void setTenNguoiDaiDien(String nguoidaidien)
	{
		this.tennguoidaidien = nguoidaidien;
	}

	public String getTenNguoiDaiDien()
	{
		return this.tennguoidaidien;
	}

	public void setFAX(String fax)
	{
		this.fax = fax;
	}

	public String getFAX()
	{
		return this.fax;
	}

	public void setEmail(String email)
	{
		this.email = email;
	}

	public String getEmail()
	{
		return this.email;
	}

	public void setHinhThucThanhToan(String httt)
	{
		this.hinhthucthanhtoan = httt;
	}

	public String getHinhThucThanhToan()
	{
		return this.hinhthucthanhtoan;
	}

	public void setNganHang(String nganhang)
	{
		this.nganhang = nganhang;
	}

	public String getNganHang()
	{
		return this.nganhang;
	}

	public void setSoTK(String sotk)
	{
		this.sotk = sotk;
	}

	public String getSoTK()
	{
		return this.sotk;
	}

	public void setGhichu(String ghichu)
	{
		this.ghichu = ghichu;
	}

	public String getGhichu()
	{
		return this.ghichu;
	}
	public void setNgaybatdau(String ngaybatdau)
	{
		this.ngaybatdau=ngaybatdau;
	}
	public String getNgaybatdau()
	{
		return this.ngaybatdau;
	}
	public void setNgayketthuc(String ngayketthuc)
	{
		this.ngayketthuc=ngayketthuc;
	}
	public String getNgayketthuc()
	{
		return this.ngayketthuc;
	}

	public boolean CreateNpp(HttpServletRequest request) 
	{
		try
		{
			this.db.getConnection().setAutoCommit(false);
			
			String maNPP = this.ma;
			String tructhuoc_fk = "";
			String loaiNPP = "";
			String nvbh_fk = "";
			String sitecode = "";
			String CMND="";
			if (this.isKH.equals("0"))
			{
				tructhuoc_fk = this.tructhuocId;
				
				loaiNPP = this.loaiNPP;
				nvbh_fk = "NULL";
				sitecode = maNPP;
				CMND="";

			}
			else{
				this.loaicongno  = "0";
				tructhuoc_fk = "1";
				loaiNPP = "6";
				nvbh_fk = this.nvbhIds;
				sitecode = "";
				CMND=this.CMTND;
			}

			String ck = this.chietkhau.trim().length() <= 0 ? "0" : this.chietkhau;

			String query="";
			if (this.loaicn.trim().length()<=0)
				this.loaicn = "NULL";	

			double TONKHOANTOAN  = geso.dms.center.util.Utility.parseDouble(this.tonkhoAntoan);
			double TANSUATDATHANG  = geso.dms.center.util.Utility.parseDouble(this.tansuatDathang);
			double TONKHOTOIDA  = geso.dms.center.util.Utility.parseDouble(this.tonkhotoida);

			if (this.kbhId.equals("100053"))//b2b this cho tick = 1
				this.b2b = "1";
			else
				this.b2b ="0";

			String xk = this.xuatkhau.trim().length() <=0 ? "null":this.xuatkhau;
			String ttntId = this.thanhthinongthonId.trim().length() <=0 ? "null":this.thanhthinongthonId;
			String kbhString = this.kbhId.trim().length() <=0 ? "null":this.kbhId;
			String hchString = this.hchId.trim().length() <=0 ? "null":this.hchId;
			String vtchString = this.vtchId.trim().length() <=0 ? "null":this.vtchId;
			String lchString = this.lchId.trim().length() <=0 ? "null":this.lchId;
			String kvString = this.kvId.trim().length() <=0 ? "null":this.kvId;
			String diabanId = this.diabanId.trim().length() <=0 ? "null":this.diabanId;
			String songaynoStr = "null";

			if (isKH.equals("1")&& this.Songayno.trim().length()>0)
				songaynoStr = this.Songayno;
			
			int quanlykho;
			if (loaiNPP.equals("0"))
			{ 
				quanlykho = 0; 
			}
			else
			{ 
				quanlykho = 1; 
			}
			
			quanlykho = 1;
			this.dungchungkenh = "1";
			
			query= "insert into nhaphanphoi(tyleOutIn,quanlySellOut,loaicongno,Songayno,chucuahieu,tencuahieu,thanhthinongthon_fk,kbh_fk,phuongxa,didong,xuatkhau,MaHD,ngaysinh,hch_fk,vtch_fk,lch_fk,sotienno" +
			",KH_B2B,TONKHOANTOAN,TANSUATDATHANG,TONKHOTOIDA,MaKho,MaNX,kyhieuhoadon, sohoadontu, sohoadonden, TongThau_FK, tuvanchuyen, ten, ngaytao, ngaysua, nguoitao, nguoisua, dienthoai, diachi, khuvuc_fk, diaban_fk, trangthai, ma, MaFAST, sitecode, convsitecode, npptn_fk, priandsecond,tinhthanh_fk,quanhuyen_fk,masothue,diachixhd,khosap,TENNGUOIDAIDIEN,FAX,EMAIL,HINHTHUCTHANHTOAN,NGANHANG,SOTAIKHOAN,GHICHU,ngaybatdau,ngayketthuc,ChietKhau, GiaVanChuyen, loaiNPP, TRUCTHUOC_FK, DDKD_FK, isKHACHHANG , XUATTAIKHO, tuxuatETC,TUTAOHOADON, DUNGCHUNGKENH, TenKyHd,cmnd,HanMucDoanhThu, thukho,ptchietkhau,quanlykho,capCn )" +
			" values("+Utility.parseDouble(tyleOutIn)+","+this.quanlySellOut+","+this.loaicongno+","+songaynoStr+",N'"+this.chucuahieu+"',N'"+this.tencuahieu+"',"+ttntId+"" +
			","+kbhString+",N'"+this.phuongxa+"',N'"+this.didong+"',"+xk+",N'"+this.hopdong+"'" +
			",'"+this.ngaysinh+"',"+hchString+","+vtchString+","+lchString+","+this.sotienno+",'"+this.b2b+"',"+TONKHOANTOAN+","+TANSUATDATHANG+","+TONKHOTOIDA+",'"+this.makho+"','"+this.manx+"','"+ this.kyhieuHD +"', '"+ this.SoHDTu +"','"+ this.SoHDDen+"' ,'0', '0', N'" + this.ten + "', '" + this.getDateTime() + "', '" + this.getDateTime() + "', '" + this.userId + "', '" + this.userId + "', '" + this.sodienthoai + "', N'" + this.diachi + "',"+kvString+","+diabanId+",'" + this.trangthai + "', '" + maNPP + "', '" + this.ma + "', '" + sitecode + "', '" + sitecode + "', NULL,'1', "+ this.tpId + ", " + this.qhId + ", " +
			" '" + this.masothue + "', N'" + this.diachixhd + "', '" + this.IdKhoTT + "', N'" + this.tennguoidaidien + "', '" + this.fax + "','" + this.email + "', N'" + this.hinhthucthanhtoan + "', N'" + this.nganhang + "','" + this.sotk + "', N'" + this.ghichu + "', '" + this.getDateTime() + "','2200-02-02', '0', '" + ck + "', '" + loaiNPP + "', '" + tructhuoc_fk + "', " + nvbh_fk + ", '" + this.isKH + "' , N'" + this.xuattaikho +  "', '1', '1', 1, N'"+this.tenkyhd+"','"+CMND+"',"+(this.hanmucdoanhthu.length()<=0?"NULL":this.hanmucdoanhthu)+",N'"+this.thukho+"',"+(this.PTchietkhau.length()<=0?"NULL":this.PTchietkhau)+","+quanlykho+","+this.idcapcn+")";

			System.out.println("1. Tao nha phan phoi:" +query ) ;
			if (!this.db.update(query))
			{
				this.db.getConnection().rollback();
				this.msg = "Exception: " + query;
				return false; 
			}

			//
			query = "select scope_identity() as nppId";
			ResultSet rs = this.db.get(query);
			rs.next();
			this.id = rs.getString("nppId");
			rs.close();





			//Tao nhan vien --> KHong tu tao, qua phan tao nhan vien tao
			if (this.isKH.equals("0"))
			{
				query = "insert nhapp_nhacc_donvikd(NPP_FK, NCC_DVKD_FK) " +
				"select '" + this.id + "', PK_SEQ from NHACUNGCAP_DVKD";
				//System.out.println("2. Tao NPP_NCC_DVKD : " +query ) ; 
				if (!this.db.update(query))
				{
					this.db.getConnection().rollback();
					this.msg = "Exception: " + query;
					return false; 
				}


				query = "insert into nhapp_kbh(npp_fk, kbh_fk) " +
				"select distinct '" + this.id + "', pk_seq " +
				"from KENHBANHANG where trangthai = '1'  ";
				//System.out.println("3. Tao NPP: " + query ) ; 
				if (!this.db.update(query))
				{   
					this.db.getConnection().rollback();
					this.msg = "Exception: " + query;
					return false;
				}


				if (this.loaiNPP.equals("0")||this.loaiNPP.equals("4")){

					if (this.loaicongno.equals("0")) // không giới hạn nợ thì cho số siêu bự
					{
						this.Hanmucno = "99999999999";
						this.Songayno ="9999";
					}
					if (this.Hanmucno.length() <=0 ){
						this.Hanmucno = "";
					}
					else {
						this.Hanmucno = this.Hanmucno.trim().replaceAll(",", "");
					}

					if (this.Songayno.length() <=0) {
						this.Songayno = "";
					}
					else{
						this.Songayno = this.Songayno.trim().replaceAll(",", "");
					}

					if (this.Hanmucno.length() > 0 || this.Songayno.length() >0){
						query = " INSERT CONGNO_NPP (NPP_FK,HANMUCNO, SONGAYNO, LOAICN) VALUES ("+this.id+","+(this.Hanmucno.length() <=0 ? "0": this.Hanmucno)+","+(this.Songayno.length() <=0 ? "0": this.Songayno)+", 1 )";

						if (!this.db.update(query)){
							this.db.getConnection().rollback();
							this.msg = "Exception: " + query;
							return false;
						}					
					}		

				}

				/*query = "insert into nhanvien(ten, dangnhap, matkhau, trangthai, ngaytao, ngaysua, nguoitao, nguoisua, phanloai, sessionid) " +
			 			"values('" + this.ten + "', '" + this.ma + "', pwdencrypt('" + this.ma + "'), '1', '" + this.getDateTime() + "', '"+ this.getDateTime() +"', '"+ this.userId+"', '" + this.userId + "', '2', '2012-01-01')";
			 	if (!this.db.update(query))
				{	
			 		this.db.getConnection().rollback();
					this.msg = "Khong the cap nhat nhapp_kbh: " + query;
					return false; 
				}

				query = "insert into phamvihoatdong(nhanvien_fk, npp_fk) " +
						"select distinct nhanvien_fk, '" + this.id + "' " +
						"from phamvihoatdong pv inner join nhaphanphoi npp on npp_fk = npp.pk_seq " +
						"where khuvuc_fk = " + this.kvId + " and Nhanvien_fk not in( select Nhanvien_fk from PHAMVIHOATDONG where Npp_fk = '" + this.id + "')";
			    if (!this.db.update(query))
			    {
			    	this.db.getConnection().rollback();
				    this.setMessage("Khong the cap nhat loi :" + query);
				    return false;
			    }*/
			}

			query = " UPDATE NHAPHANPHOI SET TIMKIEM =UPPER(DBO.FTBODAU(TEN))+' '+UPPER(DBO.FTBODAU(ISNULL(DIACHI,'')))+' '+UPPER(DBO.FTBODAU(ISNULL(MA,''))) +' '+UPPER(DBO.FTBODAU(ISNULL(EMAIL,''))) + ' '+ISNULL(DIENTHOAI,'') WHERE PK_SEQ='"+this.id+"' "; 
			//System.out.println("4. Tao NPP: " + query ) ; 
			if (!(this.db.update(query)))
			{
				this.msg="Exception:"+ query;
				this.db.update("rollback");
				return false;
			}

			String bgbanlenppId = "";
			if (this.isKH.equals("0"))
			{
				query = " insert into BANGGIAMUANPP_NPP(BANGGIAMUANPP_FK, NPP_FK) "+
						" select  TOP(1) bgM.PK_SEQ,a.PK_SEQ "+
						" from BANGGIAMUANPP bgM, NHAPHANPHOI a inner join NHAPP_KBH b  on b.NPP_FK=a.PK_SEQ "+
						"	inner join NHAPP_NHACC_DONVIKD c on c.NPP_FK=b.NPP_FK "+
						" 	inner join NHACUNGCAP_DVKD d on d.PK_SEQ=c.NCC_DVKD_FK "+
						" where d.CHECKED = 1  "+
						" and not exists "+
						" (  "+
						"	select * from BANGGIAMUANPP bg inner join BANGGIAMUANPP_NPP "+ 
						"		bgnpp on bgnpp.BANGGIAMUANPP_FK=bg.PK_SEQ "+
						"	where bg.KENH_FK=b.KBH_FK and bg.DVKD_FK=d.DVKD_FK  "+
						"	and bgnpp.NPP_FK=a.PK_SEQ	and bgM.PK_SEQ=bg.PK_SEQ "+
						" ) AND A.PK_sEQ = " + this.id + "" +
						" ORDER BY BGM.TUNGAY DESC " ;
				//System.out.println("5. Tao NPP: " + query ) ; 
				if (!(this.db.update(query)))
				{
					this.msg="Exception: "+ query;
					this.db.getConnection().rollback();
					return false;
				}

				
				query ="\n insert BANGGIABANLENPP(NGAYBATDAU,KBH_FK,TEN, DVKD_FK, NPP_FK, BANGGIABANLECHUAN_FK, NGAYTAO, NGUOITAO, NGAYSUA, NGUOISUA)  " + 
				 "\n  select tungay,bg.KBH_FK,bg.TEN, DVKD_FK, npp.PK_SEQ, bg.pk_seq, '" + getDateTime() + "', " + this.userId + ",'" + getDateTime() + "', " + this.userId + "  " + 
				 "\n  from BANGGIABANLECHUAN bg , NHAPHANPHOI npp  " + 
				 "\n  where  bg.TRANGTHAI= 1 and npp.pk_seq =   " + this.id + 
				 "\n  and not exists ( select 1 from BANGGIABANLENPP x  " + 
				 "\n 	where x.NPP_FK = npp.PK_SEQ and x.BANGGIABANLECHUAN_FK = bg.PK_SEQ )";
				if (!(this.db.update(query)))
				{
					this.msg = "Exception: "+ query;
					this.db.getConnection().rollback();
					return false;
				}
				
				query =  "\n insert BGBANLENPP_SANPHAM(BGBANLENPP_FK, SANPHAM_FK, GIABANLENPP, GIABANLECHUAN)  " + 
						 "\n select b.PK_SEQ, sanpham_fk, giabanlechuan, giabanlechuan  " + 
						 "\n from BANGGIABLC_SANPHAM a  " + 
						 "\n inner join BANGGIABANLENPP b on a.BGBLC_FK = b.BANGGIABANLECHUAN_FK and b.npp_fk =   " + this.id + 
						 "\n where  not exists (select 1 from  BGBANLENPP_SANPHAM x where x.BGBANLENPP_FK = b.PK_SEQ and a.SANPHAM_FK = x.SANPHAM_FK)  " ;
				if (!(this.db.update(query)))
				{
					this.msg = "Exception: "+ query;
					this.db.getConnection().rollback();
					return false;
				}

				// * Chen nhung san pham chua co trong kho.
				// * Theo don vi kinh doanh va kenh ban hang cua npp			 
				query = "\n insert into nhapp_kho(npp_fk,kbh_fk,KHO_FK,SANPHAM_FK,SOLUONG,BOOKED,AVAILABLE)   "+
				"\n select  npp.PK_SEQ, kenh.PK_SEQ as kbhId ,kho.PK_SEQ as khoId,sp.PK_SEQ as spId,(select case when npp.quanlykho = 0 then 999999 else 0 end) as SoLuong,0 as Booked,(select case when npp.quanlykho = 0 then 999999 else 0 end) as avail  from KHO kho,SANPHAM sp,KENHBANHANG kenh ,NHAPHANPHOI npp "+  
				"\n where not exists "+  
				"\n 	( 	 "+
				"\n 		select * from  NHAPP_KHO a 	 "+
				"\n 		where a.KHO_FK=kho.PK_SEQ and a.KBH_FK=kenh.PK_SEQ 	 "+
				"\n 		and a.SANPHAM_FK=sp.PK_SEQ and a.npp_fk =npp.pk_Seq   "+
				"\n 	) and kenh.PK_SEQ in (select kbh_fk from NHAPP_KBH where NPP_FK="+this.id+")   and sp.DVKD_FK  in (select b.DVKD_FK from NHAPP_NHACC_DONVIKD a inner join NHACUNGCAP_DVKD b on a.NCC_DVKD_FK=b.PK_SEQ and a.NPP_FK="+this.id+" ) "+ 
				"\n and npp.pk_Seq="+this.id+"  and npp.quanlykho  = 1 ";
				//System.out.println("6. Tao NPP: " + query ) ; 
				if (!db.update(query))
				{
					this.msg = "Exception "+query;
					this.db.getConnection().rollback();
					return false;
				}

				query = "\n insert into nhapp_kho_ChiTiet(npp_fk,kbh_fk,KHO_FK,SANPHAM_FK,SOLUONG,BOOKED,AVAILABLE,SOLO,NgayHetHan,ngaynhapkho)   "+
				"\n select npp.PK_SEQ, kenh.PK_SEQ as kbhId ,kho.PK_SEQ as khoId,sp.PK_SEQ as spId,(select case when npp.quanlykho = 0 then 999999 else 0 end) as SoLuong,0 as Booked,(select case when npp.quanlykho = 0 then 999999 else 0 end) as avail,'NA','2030-12-31',convert(char(10),getdate(),126)  from KHO kho,SANPHAM sp,KENHBANHANG kenh ,NHAPHANPHOI npp "+  
				"\n where not exists "+  
				"\n ( " +
				"\n     select * from  nhapp_kho_ChiTiet a 	 "+
				"\n     where a.KHO_FK=kho.PK_SEQ and a.KBH_FK=kenh.PK_SEQ 	 "+
				"\n     and a.SANPHAM_FK=sp.PK_SEQ and a.npp_fk =npp.pk_Seq  and a.SOLO='NA'  "+
				"\n ) and kenh.PK_SEQ in (select kbh_fk from NHAPP_KBH where NPP_FK="+this.id+")   and sp.DVKD_FK  in (select b.DVKD_FK from NHAPP_NHACC_DONVIKD a inner join NHACUNGCAP_DVKD b on a.NCC_DVKD_FK=b.PK_SEQ and a.NPP_FK="+this.id+" ) "+ 
				"\n and npp.pk_Seq="+this.id+"  and npp.quanlykho  = 1 ";
				//System.out.println("7. Tao NPP: " + query ) ; 
				if (!db.update(query))
				{
					this.msg = "Exception "+query;
					this.db.getConnection().rollback();
					return false;
				}
			}

			/*********************Tự động chèn đại diện,tạo tuyến đối với Loại nhà phân phối =2 (Quầy bán buôn)**************************/

			if (this.loaiNPP.equals("2") || this.loaiNPP.equals("3") )
			{
				query=
					"	insert into DAIDIENKINHDOANH(TEN,DIACHI,NPP_FK,TRANGTHAI,NGUOITAO,NGUOISUA,NGAYTAO,NGAYSUA,SMARTID,MANHANVIEN,tructhuoc_fk)   "+
					"	SELECT 'NVBH_'+TEN AS TEN,'NA' AS DIACHI,PK_SEQ AS NPPID,1 AS TRA,'"+this.userId+"' AS NTAO,'"+this.userId+"' NSUA,'"+this.getDateTime()+"','"+this.getDateTime()+"',PK_SEQ AS SMARTID,PK_SEQ AS MANV,  "+
					"		PK_SEQ AS TRUCTHUC   "+
					"	FROM NHAPHANPHOI   "+
					"	WHERE PK_SEQ ='" + this.id + "'  ";
				//System.out.println("8. Tao NPP: " + query ) ; 
				if (!db.update(query))
				{
					this.msg = "Exception"+query;
					this.db.getConnection().rollback();
					return false;
				}	
				query=
					" insert into TUYENBANHANG(DIENGIAI,NGAYLAMVIEC,NGAYTAO,NGAYSUA,NGUOITAO,NGUOISUA,DDKD_FK,NPP_FK,NGAYID)  "+
					" select 'T2_'+a.TEN,'Thu hai','"+this.getDateTime()+"','"+this.getDateTime()+"','"+this.userId+"','"+this.userId+"',a.PK_SEQ,a.NPP_FK,2   "+
					" from DAIDIENKINHDOANH a inner join NHAPHANPHOI b on b.PK_SEQ=a.NPP_FK   "+
					" where b.pk_Seq = '" + this.id + "'  ";
				//System.out.println("9. Tao NPP: " + query ) ; 
				if (!db.update(query))
				{
					this.msg = "Exception"+query;
					this.db.getConnection().rollback();
					return false;
				}	

				/*********************Tự động chèn đại diện,tạo tuyến đối với Loại nhà phân phối =2 (Quầy bán buôn)**************************/	
			}

			query=
				"	insert into phamvihoatdong(nhanvien_fk, npp_fk)  "+ 
				"	select PK_SEQ,'"+this.id+"' "+
				"	from NHANVIEN  "+
				"	where PHANLOAI=2 and LOAI in (1,2,5)  ";
			System.out.println("5. Tao NPP: " + query ) ; 

			if (!db.update(query))
			{
				this.msg = "Exception"+query;
				this.db.getConnection().rollback();
				return false;
			}	

			query=	"	insert PHAMVIHOATDONG (Nhanvien_fk,Npp_fk) "+
					"	select nv.PK_SEQ,npp.PK_SEQ "+
					"	from NHAPHANPHOI npp,NHANVIEN nv "+
					"	where nv.IsAdmin=1 and not exists "+
					"	( "+
					"		select 1 from PHAMVIHOATDONG hd where hd.Nhanvien_fk=nv.PK_SEQ "+
					"		and Npp_fk=npp.PK_SEQ "+
					"	) ";

			if (!this.db.update(query))
			{
				this.db.getConnection().rollback();
				this.msg = "Exception" + query;
				return false;
			}
			query=	" insert NHANVIEN_SANPHAM (Nhanvien_fk,Sanpham_fk) "+
			" select nv.PK_SEQ,npp.PK_SEQ "+
			" from SANPHAM npp,NHANVIEN nv "+
			" where nv.IsAdmin=1 and not exists "+
			" ( "+
			"	select 1 from NHANVIEN_SANPHAM hd where hd.Nhanvien_fk=nv.PK_SEQ "+
			"	and Sanpham_fk=npp.PK_SEQ "+
			" )";
			if (!this.db.update(query))
			{
				this.db.getConnection().rollback();
				this.msg = "Exception" + query;
				return false;
			}
			query= "insert NHANVIEN_KENH (Nhanvien_fk,Kenh_fk) "+
			"select nv.PK_SEQ,npp.PK_SEQ "+
			"from KENHBANHANG npp,NHANVIEN nv "+
			"where nv.IsAdmin=1 and not exists "+
			"( "+
			"	select 1 from NHANVIEN_KENH hd where hd.Nhanvien_fk=nv.PK_SEQ "+
			"	and Kenh_fk=npp.PK_SEQ "+
			") ";

			if (!this.db.update(query))
			{
				this.db.getConnection().rollback();
				this.msg = "Exception" + query;
				return false;
			}
			query="insert nhanvien_kho (Nhanvien_fk,kho_fk) "+
			"	select nv.PK_SEQ,npp.PK_SEQ "+
			"	from KHO npp,NHANVIEN nv "+
			"	where nv.IsAdmin=1 and not exists "+
			"	( "+
			"		select 1 from nhanvien_kho hd where hd.Nhanvien_fk=nv.PK_SEQ "+
			"		and kho_fk=npp.PK_SEQ "+
			"	)";
			if (!this.db.update(query))
			{
				this.db.getConnection().rollback();
				this.msg = "Exception" + query;
				return false;
			}
			if (isKH.equals("0"))
			{
					query = "\n insert KhoaSoThang(NPP_FK,thangks,nam,ngaytao,nguoitao,created_date,ngaythangnam)" +
							"\n select "+this.id+", month( dateadd(month,-1,getdate())) ,year( dateadd(month,-1,getdate())),'"+getDateTime()+"',"+this.userId+",getdate(),convert( varchar(10),DATEADD(MONTH, DATEDIFF(MONTH, 0, GETDATE())-1, 0),120) ngaythangnam ";
					if (!this.db.update(query))
					{
						this.db.getConnection().rollback();
						this.msg = "Exception" + query;
						return false;
					}
			}
			this.db.getConnection().commit();
			this.db.getConnection().setAutoCommit(true);

		}
		catch(Exception e)
		{
			this.msg = "Exception"+ e.toString();		
			this.db.update("rollback");
			e.printStackTrace();
			return false;						
		}			

		return true;
	}

	public boolean UpdateNpp(HttpServletRequest request) 
	{
		try
		{
			this.db.getConnection().setAutoCommit(false);

			String tructhuoc_fk = "";
			String loaiNPP = "";
			String nvbh_fk = "";
			String CMND="";
			System.out.println("---IS KH: " + this.isKH);
			if (this.isKH.equals("0"))
			{
				tructhuoc_fk = this.tructhuocId;
				loaiNPP = this.loaiNPP;
				nvbh_fk = "NULL";
				CMND="";
			}
			else
			{
				tructhuoc_fk = "1";
				loaiNPP = "6";
				nvbh_fk = this.nvbhIds;
				CMND=this.CMTND;
				System.out.println("CMNd ___1 ____"+CMND);
			}

			if (this.loaicn.trim().length()<=0)
				this.loaicn="NULL";


			double TONKHOANTOAN  = geso.dms.center.util.Utility.parseDouble(this.tonkhoAntoan);
			double TANSUATDATHANG  = geso.dms.center.util.Utility.parseDouble(this.tansuatDathang);
			double TONKHOTOIDA  = geso.dms.center.util.Utility.parseDouble(this.tonkhotoida);

			if (this.kbhId.equals("100053"))//b2b this cho tick = 1
				this.b2b = "1";
			else
				this.b2b ="0";




			String query = "";
			String ck = this.chietkhau.trim().length() <= 0 ? "0" : this.chietkhau;
			String xk = this.xuatkhau.trim().length() <=0 ? "null":this.xuatkhau;
			String ttntId = this.thanhthinongthonId.trim().length() <=0 ? "null":this.thanhthinongthonId;
			String kbhString = this.kbhId.trim().length() <=0 ? "null":this.kbhId;
			String hchString = this.hchId.trim().length() <=0 ? "null":this.hchId;
			String vtchString = this.vtchId.trim().length() <=0 ? "null":this.vtchId;
			String lchString = this.lchId.trim().length() <=0 ? "null":this.lchId;
			String kvString = this.kvId.trim().length() <=0 ? "null":this.kvId;
			String diabanId = this.diabanId.trim().length() <=0 ? "null":this.diabanId;
			String songaynoStr = "null";
			if (isKH.equals("1")&& this.Songayno.trim().length()>0)
				songaynoStr = this.Songayno;

			int quanlykho;
			if (loaiNPP.equals("0"))
			{ quanlykho = 0; }
			else
			{ quanlykho = 1; }
			
			quanlykho = 1;
			this.dungchungkenh = "1";
			query = "update nhaphanphoi set capCn = '"+ this.idcapcn +"', quanlySellOut = "+this.quanlySellOut+",tyleOutIn = "+Utility.parseDouble(tyleOutIn)+"	 " +
			" 	,songayno = "+songaynoStr+",chucuahieu = N'"+this.chucuahieu+"',tencuahieu = N'"+this.tencuahieu+"',thanhthinongthon_fk="+ttntId+",kbh_fk= "+kbhString+"" +
			",phuongxa  = N'"+this.phuongxa+"',didong = N'"+this.didong+"',xuatkhau ="+xk+"" +
			",MaHD= N'"+this.hopdong+"',ngaysinh= N'"+this.ngaysinh+"',hch_fk = "+hchString+",vtch_fk= "+vtchString+",lch_fk= "+lchString+",sotienno= "+this.sotienno+" " +
			",KH_B2B="+this.b2b +",TONKHOTOIDA = "+TONKHOTOIDA+" ,TANSUATDATHANG = "+TANSUATDATHANG+" ,TONKHOANTOAN = "+TONKHOANTOAN+" ,MaKho='"+this.makho+"',MaNx='"+this.manx+"',kyhieuhoadon ='"+ this.kyhieuHD +"', sohoadontu='"+this.SoHDTu +"', sohoadonden='"+this.SoHDDen+"', khosap='" + this.IdKhoTT + "', masothue='"+this.masothue+"', diachixhd=N'"+this.getDiaChiXuatHoaDon()+"', " +
			" ten=N'" + this.ten + "', dienthoai='" + this.sodienthoai + "', diachi=N'"+ this.diachi + "', khuvuc_fk = "+kvString+",  diaban_fk="+diabanId+", "+
			" ngaysua = '" + this.getDateTime() + "',  nguoisua = '" + this.userId + "', trangthai = '" + this.trangthai + "', " +
			" sitecode ='"+ this.ma + "' ,convsitecode='"+ this.ma + "',ma='"+ this.ma + "',maFAST='"+ this.ma + "',  priandsecond ='1', tinhthanh_fk='" + this.tpId + "', " +
			" quanhuyen_fk='" + this.qhId + "', tennguoidaidien=N'"+this.tennguoidaidien+"',fax ='"+this.fax+"',email='"+this.email+"', " +
			" hinhthucthanhtoan=N'"+this.hinhthucthanhtoan+"',nganhang=N'"+this.nganhang+"',sotaikhoan='"+this.sotk+"',ghichu=N'"+this.ghichu+"',ngaybatdau='"+this.ngaybatdau+"', " +
			" quanlykho = "+quanlykho+", ngayketthuc='"+this.ngayketthuc+"',ChietKhau='" + ck + "', GiaVanChuyen = '0', loaiNPP = '" + loaiNPP + "', TRUCTHUOC_FK = '" + tructhuoc_fk + "', DDKD_FK = " + nvbh_fk + ", XUATTAIKHO = N'" + this.xuattaikho + "', tuxuatETC = 1, TenKyHd= N'"+this.tenkyhd+"',cmnd='"+this.CMTND+"',HanMucDoanhThu="+(this.hanmucdoanhthu.length()<=0?"NULL":this.hanmucdoanhthu)+", thukho = N'"+this.thukho+"',PTchietkhau = "+(this.PTchietkhau.length()<=0?"NULL":this.PTchietkhau)+" where pk_seq = '" + this.id + "'" ;

			System.out.println("Query Update: "+ query);
			if (!this.db.update(query))
			{
				this.db.getConnection().rollback();
				this.msg = "Exception";
				return false; 
			}		


			query = "delete from nhapp_kbh where npp_fk = '" + this.id + "'";
			if (!this.db.update(query))
			{
				this.db.getConnection().rollback();
				this.msg = "Exception";
				return false; 
			}		



			query = " delete from nhapp_nhacc_donvikd where npp_fk = '"+this.id+"' ";
			if (!this.db.update(query))
			{
				this.db.getConnection().rollback();
				this.msg = "Exception";
				return false; 
			}		



			query = "UPDATE NHAPHANPHOI SET TIMKIEM = UPPER(DBO.FTBODAU(TEN))+' '+UPPER(DBO.FTBODAU(ISNULL(DIACHI,'')))+' '+UPPER(DBO.FTBODAU(ISNULL(MA,''))) +' '+UPPER(DBO.FTBODAU(ISNULL(EMAIL,''))) + ' '+ISNULL(DIENTHOAI,'') WHERE PK_SEQ='"+this.id+"' "; 
			if (!(this.db.update(query)))
			{
				this.msg="Exception"+ query;
				this.db.getConnection().rollback();
				return false;
			}

			if (this.loaiNPP.equals("0")){				
				query = "";
			}
			if (this.isKH.equals("0"))
			{

				query = "insert nhapp_nhacc_donvikd(NPP_FK, NCC_DVKD_FK) " +
				"select '" + this.id + "', PK_SEQ from NHACUNGCAP_DVKD";
				System.out.println("2. Tao NPP_NCC_DVKD : " +query ) ; 
				if (!this.db.update(query))
				{
					this.db.getConnection().rollback();
					this.msg = "Exception" + query;
					return false; 
				}


				query = "insert into nhapp_kbh(npp_fk, kbh_fk) " +
				"select distinct'" + this.id + "', pk_seq " +
				"from KENHBANHANG where trangthai = '1'  ";
				if (!this.db.update(query))
				{   
					this.db.getConnection().rollback();
					this.msg = "Exception" + query;
					return false;
				}

				query = "	insert into nhapp_kho(npp_fk,kbh_fk,KHO_FK,SANPHAM_FK,SOLUONG,BOOKED,AVAILABLE)   "+
				"	select  npp.PK_SEQ, kenh.PK_SEQ as kbhId ,kho.PK_SEQ as khoId,sp.PK_SEQ as spId,(select case when npp.quanlykho = 0 then 999999 else 0 end) as SoLuong,0 as Booked,(select case when npp.quanlykho = 0 then 999999 else 0 end) as avail  from KHO kho,SANPHAM sp,KENHBANHANG kenh ,NHAPHANPHOI npp "+  
				"	where not exists "+  
				"	( 	 "+
				"		select * from  NHAPP_KHO a 	 "+
				"		where a.KHO_FK=kho.PK_SEQ and a.KBH_FK=kenh.PK_SEQ 	 "+
				"		and a.SANPHAM_FK=sp.PK_SEQ and a.npp_fk =npp.pk_Seq   "+
				"	) and kenh.PK_SEQ in (select kbh_fk from NHAPP_KBH where NPP_FK="+this.id+")   and sp.DVKD_FK  in (select b.DVKD_FK from NHAPP_NHACC_DONVIKD a inner join NHACUNGCAP_DVKD b on a.NCC_DVKD_FK=b.PK_SEQ and a.NPP_FK="+this.id+" ) "+ 
				"	and npp.pk_Seq="+this.id+"  and npp.quanlykho  = 1 ";
				if (!db.update(query))
				{
					this.msg = "Exception "+query;
					this.db.getConnection().rollback();
					return false;
				}

				query = "	insert into nhapp_kho_ChiTiet(npp_fk,kbh_fk,KHO_FK,SANPHAM_FK,SOLUONG,BOOKED,AVAILABLE,SOLO,NgayHetHan,ngaynhapkho)   "+
				"	select  npp.PK_SEQ, kenh.PK_SEQ as kbhId ,kho.PK_SEQ as khoId,sp.PK_SEQ as spId,(select case when npp.quanlykho = 0 then 999999 else 0 end) as SoLuong,0 as Booked,(select case when npp.quanlykho = 0 then 999999 else 0 end) as avail,'NA','2030-12-31',convert(char(10),getdate(),126)  from KHO kho,SANPHAM sp,KENHBANHANG kenh ,NHAPHANPHOI npp "+  
				"	where not exists "+  
				"	( 	 "+
				"		select 1 from  nhapp_kho_ChiTiet a 	 "+
				"		where a.KHO_FK=kho.PK_SEQ and a.KBH_FK=kenh.PK_SEQ 	 "+
				"		and a.SANPHAM_FK=sp.PK_SEQ and a.npp_fk =npp.pk_Seq and a.SoLo='NA'  "+
				"	) and kenh.PK_SEQ in (select kbh_fk from NHAPP_KBH where NPP_FK="+this.id+")   and sp.DVKD_FK  in (select b.DVKD_FK from NHAPP_NHACC_DONVIKD a inner join NHACUNGCAP_DVKD b on a.NCC_DVKD_FK=b.PK_SEQ and a.NPP_FK="+this.id+" ) "+ 
				"	and npp.pk_Seq="+this.id+" and npp.quanlykho  = 1 ";
				if (!db.update(query))
				{
					this.msg = "Exception"+query;
					this.db.getConnection().rollback();
					return false;
				}

				query = " insert into BANGGIAMUANPP_NPP(BANGGIAMUANPP_FK,NPP_FK) "+
				" select  TOP(1) bgM.PK_SEQ,a.PK_SEQ "+
				" from BANGGIAMUANPP bgM , NHAPHANPHOI a inner join NHAPP_KBH b  on b.NPP_FK=a.PK_SEQ "+
				"	inner join NHAPP_NHACC_DONVIKD c on c.NPP_FK=b.NPP_FK "+
				" 	inner join NHACUNGCAP_DVKD d on d.PK_SEQ=c.NCC_DVKD_FK "+
				"  where d.CHECKED=1  "+
				" and not exists "+
				" (  "+
				"	select * from BANGGIAMUANPP bg inner join BANGGIAMUANPP_NPP "+ 
				"		bgnpp on bgnpp.BANGGIAMUANPP_FK=bg.PK_SEQ "+
				"	where bg.KENH_FK=b.KBH_FK and bg.DVKD_FK=d.DVKD_FK  "+
				"	and bgnpp.NPP_FK=a.PK_SEQ	and bgM.PK_SEQ=bg.PK_SEQ "+
				" ) AND A.PK_sEQ = " + this.id + " and bgM.pk_seq not in ( select banggiamuaNPP_FK from BANGGIAMUANPP_NPP where NPP_FK = '" + this.id + "' ) " +
				" ORDER BY BGM.TUNGAY DESC " ;
				if (!(this.db.update(query)))
				{
					this.msg="Exception 2"+ query;
					this.db.getConnection().rollback();
					return false;
				}
			}

			if (this.loaiNPP.equals("0")||this.loaiNPP.equals("4")){

				if (this.Hanmucno.length() <=0 ) this.Hanmucno = "";

				if (this.Songayno.length() <=0) this.Songayno = "";

				if (this.Hanmucno.length() > 0 || this.Songayno.length() >0){
					query = " SELECT COUNT (PK_SEQ) sodong FROM CONGNO_NPP WHERE NPP_FK ='"+this.id+"'";

					System.out.println(query);
					int count = 0;
					ResultSet dem = db.get(query);
					if (dem!=null){
						while(dem.next()){
							count = dem.getInt("sodong");
						}
						dem.close();
					}

					if (count<=0){					
						query = " INSERT CONGNO_NPP (NPP_FK,HANMUCNO, SONGAYNO, LOAICN) VALUES ("+this.id+","+(this.Hanmucno.length() <=0 ? "0": this.Hanmucno.trim().replaceAll(",", ""))+","+(this.Songayno.length() <=0 ? "0": this.Songayno.trim().replaceAll(",", ""))+", 1 )";
						System.out.println(query);
						if (!this.db.update(query)){
							this.db.getConnection().rollback();
							this.msg = "Exception" + query;
							return false;
						}
					}
					else {
						query = "UPDATE CONGNO_NPP SET HANMUCNO ="+(this.Hanmucno.length() <=0 ? "0": this.Hanmucno.trim().replaceAll(",", ""))+", SONGAYNO ="+(this.Songayno.length() <=0 ? "0": this.Songayno.trim().replaceAll(",", ""))+" WHERE NPP_FK ='"+this.id+"'";
						System.out.println(query);
						if (!this.db.update(query)){
							this.db.getConnection().rollback();
							this.msg = "Exception" + query;
							return false;
						}
					}
				}		

			}
			else{

				query = "DELETE CONGNO_NPP WHERE NPP_FK ='"+this.id+"'";

				System.out.println(query);
				if (!this.db.update(query)){
					this.db.getConnection().rollback();
					this.msg = "Exception" + query;
					return false;
				}

			}


			query=	" insert NHANVIEN_SANPHAM (Nhanvien_fk,Sanpham_fk) "+
			" select nv.PK_SEQ,npp.PK_SEQ "+
			" from SANPHAM npp,NHANVIEN nv "+
			" where nv.IsAdmin=1 and not exists "+
			" ( "+
			"	select 1 from NHANVIEN_SANPHAM hd where hd.Nhanvien_fk=nv.PK_SEQ "+
			"	and Sanpham_fk=npp.PK_SEQ "+
			" )";
			if (!this.db.update(query))
			{
				this.db.getConnection().rollback();
				this.msg = "Exception" + query;
				return false;
			}
			query= "insert NHANVIEN_KENH (Nhanvien_fk,Kenh_fk) "+
			"select nv.PK_SEQ,npp.PK_SEQ "+
			"from KENHBANHANG npp,NHANVIEN nv "+
			"where nv.IsAdmin=1 and not exists "+
			"( "+
			"	select 1 from NHANVIEN_KENH hd where hd.Nhanvien_fk=nv.PK_SEQ "+
			"	and Kenh_fk=npp.PK_SEQ "+
			") ";

			if (!this.db.update(query))
			{
				this.db.getConnection().rollback();
				this.msg = "Exception" + query;
				return false;
			}
			query="insert nhanvien_kho (Nhanvien_fk,kho_fk) "+
			"	select nv.PK_SEQ,npp.PK_SEQ "+
			"	from KHO npp,NHANVIEN nv "+
			"	where nv.IsAdmin=1 and not exists "+
			"	( "+
			"		select 1 from nhanvien_kho hd where hd.Nhanvien_fk=nv.PK_SEQ "+
			"		and kho_fk=npp.PK_SEQ "+
			"	)";
			if (!this.db.update(query))
			{
				this.db.getConnection().rollback();
				this.msg = "Exception" + query;
				return false;
			}

			this.db.getConnection().commit();
			this.db.getConnection().setAutoCommit(true);

		} 
		catch(Exception e)
		{
			this.msg = "Exception"+ e.toString();		
			this.db.update("rollback");
			e.printStackTrace();
			return false;
		}
		return true;
	}

	public ResultSet createTpRS()
	{  	
		ResultSet tpRS = this.db.get("select ten as tpTen, pk_seq as tpId from tinhthanh order by ten");
		return tpRS;
	}

	public ResultSet createQhRS()
	{  	
		ResultSet qhRS = null;
		System.out.println("thanh pho id la "+tpId);
		if (this.tpId.length() > 0){
			qhRS = this.db.get("select ten as qhTen, pk_seq as qhId from quanhuyen where tinhthanh_fk='"+ this.tpId +"' order by ten");
		}
		else
		{
			qhRS = this.db.get("select ten as qhTen, pk_seq as qhId from quanhuyen order by ten");
		}

		return qhRS;
	}

	public ResultSet createKvRS()
	{  	
		ResultSet kvRS = this.db.get("select ten + '-' + diengiai as kvTen, pk_seq as kvId from khuvuc order by ten");
		return kvRS;
	}

	public void createRS()
	{
		this.tp = this.createTpRS();		
		this.qh = createQhRS();
		this.khuvucList = this.createKvRS();
		this.gsbhList = db.get("select pk_seq, ten from GIAMSATBANHANG where trangthai = '1'");
		this.rs_khott = db.get("select pk_seq, ten from ERP_KHOTT where trangthai = '1'");
		this.nvbhList = db.get("select pk_seq as ID, TEN from DAIDIENKINHDOANH ");
		this.loaicnRs = db.get("Select pk_seq, DienGiai from CongNo");
		this.loaiNppRs = db.get("select maloai,tenloai from LOAINHAPHANPHOI");
		
		this.capcnRs = db.get("select N'1' as idCapdo, N'Cấp độ 1' as tencapdo union all\r\n" + 
				"select N'2' as idCapdo, N'Cấp độ 2' as tencapdo");
		
		
		String query = " select pk_seq as ID, TEN, 1 as STT from nhaphanphoi where pk_seq = 1 ";
		if (this.loaiNPP.equals("0")) //Chi nhánh cấp 1  -> TRUC THUOC DCL hoac CHI NHANH CAP 1 KHAC
		{
			//query += " union select pk_seq as ID, TEN, 2 as STT from NHAPHANPHOI where trangthai = '1' and loaiNPP in ( 0 )  ";
			
			if(this.idcapcn.equals("2")) { //Chi nhánh cấp 2  -> TRUC THUOC DCL hoac CHI NHANH CAP 1 KHAC
				query += " union select pk_seq as ID, TEN, 2 as STT from NHAPHANPHOI where pk_seq != 1 and trangthai = '1' and loaiNPP in ( 0, 4 ) and capCn ='1' ";
				System.out.println(this.loaiNPP  + "    " + this.idcapcn);
			}
		}
//		else if ((this.loaiNPP.equals("0")) && (this.idcapcn.equals("2"))) //Chi nhánh cấp 2  -> TRUC THUOC DCL hoac CHI NHANH CAP 1 KHAC
//		{
//			query += " union select pk_seq as ID, TEN, 2 as STT from NHAPHANPHOI where pk_seq != 1 and trangthai = '1' and loaiNPP in ( 0, 4 ) and capCn ='1' ";
//		}
		else if (this.loaiNPP.equals("2")) //Quầy bán buôn  -> TRUC THUOC DCL hoac CHI NHANH CAP 1, 2 KHAC
		{
			query += " union select pk_seq as ID, TEN, 2 as STT from NHAPHANPHOI where pk_seq != 1 and trangthai = '1' and loaiNPP in ( 0, 1 )  ";
		}
		else if (this.loaiNPP.equals("3")) //Quầy DCL  -> TRUC THUOC DOI TAC
		{
			query = " select pk_seq as ID, TEN, 2 as STT from NHAPHANPHOI where  pk_seq != 1 and trangthai = '1' and loaiNPP in ( 4 )  ";
		}
		else if (this.loaiNPP.equals("4")) //Đối tác  -> TRUC THUOC DCL va CHI NHANH CAP 1
		{
			query += " union select pk_seq as ID, TEN, 2 as STT from NHAPHANPHOI where pk_seq != 1 and  trangthai = '1' and loaiNPP in ( 0 )  ";
		}
		else if (this.loaiNPP.equals("5")) //Chi nhánh đối tác  -> TRUC THUOC DOI TAC
		{
			query = " select pk_seq as ID, TEN, 2 as STT from NHAPHANPHOI where pk_seq != 1 and    trangthai = '1' and loaiNPP in ( 4 )  ";
		}

		query += " order by STT asc, TEN asc ";

		System.out.println("--LAY TRUC THUOC: " + query );
		this.tructhuocRs = db.get(query);

		if (this.isKH.equals("1"))
		{
			this.thanhthinongthonRs  = db.get(" select PK_SEQ,TEN from ThanhThiNongThon where trangthai = 1 ");
			this.hangcuahang =  this.db.get("select hang as hchTen, pk_seq as hchId from hangcuahang where trangthai='1' order by hang");
			this.kenhbanhang =  this.db.get("select diengiai as kbhTen, pk_seq as kbhId from kenhbanhang where trangthai='1' order by diengiai");
			this.vtcuahang =  this.db.get("select vitri as vtchTen, pk_seq as vtchId from vitricuahang where trangthai='1' order by vitri");
			this.loaicuahang =  this.db.get("select loai as lchTen, pk_seq as lchId from loaicuahang where trangthai='1' order by loai");
		}
		
		CreateDiabanRs();
	} 

	private void init() 
	{

		String query =  
			"\n	select  a.quanlySellOut,a.capcn, a.tyleOutIn,a.loaicongno,isnull(a.KH_B2B,0) as b2b,ISNULL(a.TONKHOTOIDA,0)TONKHOTOIDA ,ISNULL(a.TANSUATDATHANG,0)TANSUATDATHANG ,ISNULL(a.TONKHOANTOAN,0)TONKHOANTOAN ,a.HanMucDoanhThu, isnull(a.xuattaikho,'') as xuattaikho , a.tongthau_fk, isnull(a.tuvanchuyen,'') as tuvanchuyen , a.khosap as khott,isnull(a.TenKyHd,'') TenKyHd ," +
			"\n   isnull(a.kyhieuhoadon,'') as kyhieuhoadon ,isnull(a.sohoadontu,'') as sohoadontu, isnull(a.sohoadonden,'') as sohoadonden, "+
			"\n		a.masothue,a.diachixhd,a.pk_seq as id, a.ten as nppten, a.dienthoai, a.diachi, "+ 
			"\n		a.trangthai,  a.ma, a.pass, a.ngaytao, b.nguoitao, a.ngaysua, c.nguoisua, "+
			"\n		isnull( (convert(varchar,db.pk_seq)),'')dbid,isnull(d.ten,'') as kvten, d.pk_seq as kvid, a.priandsecond as  prisec, a.tinhthanh_fk as tpid, "+
			"\n		a.quanhuyen_fk as qhid, a.tennguoidaidien as tennguoidaidien, isnull(a.fax,'') as fax, isnull(a.email,'') as email,  "+
			"\n		isnull(a.hinhthucthanhtoan,'') as hinhthucthanhtoan, isnull(a.nganhang,'') as nganhang, isnull(a.sotaikhoan,'') as sotaikhoan, "+  
			"\n		a.ghichu as ghichu, isnull(a.ngaybatdau,'') as ngaybatdau, isnull(a.ngayketthuc,'') as ngayketthuc,  "+
			"\n		isnull(a.chietkhau, 0) as chietkhau, isnull(giavanchuyen, 0) as giavanchuyen ,isnull(sitecode_conv.tennpptn,'') as tennpptn,a.npptn_fk, a.loaiNPP, a.TRUCTHUOC_FK, a.DDKD_FK, a.isKHACHHANG, a.MaFAST, tuxuatETC, isnull(tutaohoadon, 0) as tutaohoadon, DUNGCHUNGKENH "+
			"\n  ,a.MaKho,a.MaNX,a.cmnd, isnull(a.thukho,'') thukho,a.PTchietkhau " +
			"\n ,a.Songayno,a.chucuahieu,a.tencuahieu,a.thanhthinongthon_fk,a.kbh_fk,a.phuongxa,a.didong,a.xuatkhau,a.MaHD,a.ngaysinh,a.hch_fk,a.vtch_fk,a.lch_fk,a.sotienno " +
			"\n	from  nhaphanphoi a inner join nhanvien b on b.pk_seq=a.nguoitao "+ 
			"\n	     inner join  nhanvien c on c.pk_seq=a.nguoisua   "+
			"\n	     left join sitecode_conv on convert(varchar,sitecode_conv.convsitecode)=convert(varchar,a.sitecode) 	 "+
			"\n		 left join  khuvuc d  on a.khuvuc_fk=d.pk_seq " +
			"\n		  left join diaban db on convert(varchar,db.khuvuc_fk) = convert(varchar,d.pk_seq) and a.khuvuc_fk=d.pk_seq "+
			"\n	 where  a.pk_seq = '"+this.id+"' ";

		ResultSet rs =  this.db.get(query);
		System.out.println("1/Load lên: " + query);

		try{
			while(rs.next())
			{          
				this.hanmucdoanhthu = 
				this.quanlySellOut = rs.getString("quanlySellOut") == null ? "1" :  rs.getString("quanlySellOut");
				this.tyleOutIn = rs.getString("tyleOutIn") == null ? "0" :  rs.getString("tyleOutIn");
				this.tonkhoAntoan = rs.getString("TONKHOANTOAN");
				this.tansuatDathang = rs.getString("TANSUATDATHANG");
				this.tonkhotoida = rs.getString("TONKHOTOIDA");
				this.masothue=rs.getString("masothue");
				this.diachixhd=rs.getString("diachixhd");
				this.id = rs.getString("id");
				this.ten = rs.getString("nppTen");
				this.sodienthoai = rs.getString("dienthoai")==null?"": rs.getString("dienthoai");
				this.diachi = rs.getString("diachi")==null?"":rs.getString("diachi");
				this.tpId = rs.getString("tpId");
				this.qhId = rs.getString("qhId");
				this.trangthai = rs.getString("trangthai");
				this.ma 	   = rs.getString("MaFAST");
				this.tuvanchuyen=rs.getString("tuvanchuyen");      	
				this.kvId = rs.getString("kvId");
				this.diabanId = rs.getString("dbId");
				this.tennguoidaidien = rs.getString("tennguoidaidien")==null?"":rs.getString("tennguoidaidien");
				this.fax = rs.getString("fax")==null?"":rs.getString("fax");
				this.email = rs.getString("email")==null?"":rs.getString("email");
				this.hinhthucthanhtoan = rs.getString("hinhthucthanhtoan");
				this.nganhang = rs.getString("nganhang");
				this.sotk = rs.getString("sotaikhoan")==null?"":rs.getString("sotaikhoan");
				this.ngaybatdau = rs.getString("ngaybatdau");
				this.ngayketthuc = rs.getString("ngayketthuc");
				this.ghichu = rs.getString("ghichu");
				String st = rs.getString("prisec");
				this.chietkhau = rs.getString("chietkhau");
				this.b2b=rs.getString("b2b");
				this.kyhieuHD =  rs.getString("kyhieuhoadon");
				this.tenkyhd = rs.getString("TenKyHd");
				this.SoHDTu=  rs.getString("sohoadontu");
				this.SoHDDen =  rs.getString("sohoadonden");
				
				this.idcapcn=rs.getString("capcn") == null ?"": rs.getString("capcn");
				this.loaiNPP = rs.getString("loaiNPP");
				if (rs.getString("TRUCTHUOC_FK") != null)
					this.tructhuocId = rs.getString("TRUCTHUOC_FK");
				if (rs.getString("DDKD_FK") != null)
					this.nvbhIds = rs.getString("DDKD_FK");

				this.isKH = rs.getString("isKHACHHANG");
				this.IdKhoTT=rs.getString("khott") == null ?"": rs.getString("khott");
				if (st==null)
					st = "0";
				this.prisec=st;
				this.xuattaikho = rs.getString("xuattaikho");
				this.tuxuatETC = rs.getString("tuxuatETC");
				this.tutaohoadonOTC= rs.getString("tutaohoadon");
				this.dungchungkenh= rs.getString("dungchungkenh");
				this.makho = rs.getString("makho") ==null?"": rs.getString("makho");
				this.manx = rs.getString("manx") ==null?"": rs.getString("manx");
				this.CMTND=rs.getString("cmnd") ==null?"": rs.getString("cmnd");;
				DecimalFormat df2 = new DecimalFormat( "#,###,###" );

				this.hanmucdoanhthu = rs.getString("hanmucdoanhthu")==null?"":df2.format(rs.getDouble("hanmucdoanhthu"));
				this.thukho = rs.getString("thukho");
				this.PTchietkhau = rs.getString("PTchietkhau")==null?"":rs.getString("PTchietkhau");


				//////// trung tâm

				//// KH etc
				this.chucuahieu = rs.getString("chucuahieu") == null ?"":rs.getString("chucuahieu");
				this.tencuahieu = rs.getString("tencuahieu") == null ?"":rs.getString("tencuahieu");
				this.thanhthinongthonId= rs.getString("thanhthinongthon_fk") == null ?"":rs.getString("thanhthinongthon_fk");
				this.kbhId = rs.getString("kbh_fk") == null ?"":rs.getString("kbh_fk"); 
				this.phuongxa = rs.getString("phuongxa") == null ?"":rs.getString("phuongxa");
				this.didong = rs.getString("didong") == null ?"":rs.getString("didong");
				this.xuatkhau = rs.getString("xuatkhau") == null ?"":rs.getString("xuatkhau");
				this.hopdong = rs.getString("MaHD") == null ?"":rs.getString("MaHD");  
				this.ngaysinh = rs.getString("ngaysinh") == null ?"":rs.getString("ngaysinh");
				this.hchId = rs.getString("hch_fk") == null ?"":rs.getString("hch_fk");
				this.vtchId = rs.getString("vtch_fk") == null ?"":rs.getString("vtch_fk");
				this.lchId = rs.getString("lch_fk") == null ?"":rs.getString("lch_fk");
				this.sotienno = rs.getString("sotienno") == null ? 0 : rs.getDouble("sotienno") ;
				if (this.isKH.equals("1"))
				{
					this.Songayno = rs.getString("Songayno") == null ? "0" : rs.getDouble("Songayno")+"" ;
				}

				this.loaicongno = rs.getString("loaicongno")==null?"0": rs.getString("loaicongno");

			}
			rs.close();      

			//INIT GSBH
			query = "select gsbh_fk from nhapp_giamsatbh where NPP_FK = '" + this.id + "'";
			rs = db.get(query);
			if (rs.next())
				this.gsbhIds = rs.getString("gsbh_fk");
			rs.close();

			//HAN MUC CONG NO

			if (this.loaiNPP.equals("4")||this.loaiNPP.equals("0")){
				query = " SELECT NPP_FK, LOAICN, HANMUCNO, SONGAYNO FROM CONGNO_NPP WHERE NPP_FK ='"+this.id+"'";
				System.out.println(query);
				rs = db.get(query);
				if (rs.next()){
					this.loaicn = rs.getString("LOAICN");
					this.Hanmucno = rs.getString("HANMUCNO");
					this.Songayno = rs.getString("SONGAYNO");
				}
				rs.close();	
			}
		}
		catch(Exception e){e.printStackTrace();}

		createRS(); 
	}

	public void CreateDiabanRs()
	{
		String query = "select distinct db.pk_seq, db.diengiai as ten " + 
		"\n from diaban db " +
		"\n inner join khuvuc d on db.khuvuc_fk = d.pk_seq ";
		this.diabanRs = this.db.get(query); 

		if (this.kvId != null && this.kvId.length() > 0) {
			query = "\n select distinct db.pk_seq, db.diengiai as ten " + 
			"\n from diaban db inner join khuvuc d on db.khuvuc_fk = d.pk_seq " + 
			"\n where d.pk_seq = " + this.kvId;
			System.out.println("diabanRs: " + query);
			this.diabanRs = this.db.get(query);
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
		try
		{		
			if (khuvucList!=null){
				khuvucList.close();
			}

			if (tp!=null){
				tp.close();
			}

			if (qh!=null){
				qh.close();
			}

			if (rs_khott!=null){
				rs_khott.close();
			}
			if (rs_tinhthanh!=null){
				rs_tinhthanh.close();
			}

			if (rs_quanhuyen!=null){
				rs_quanhuyen.close();
			}

			if (!(this.db == null)){

				this.db.shutDown();
			}
		}catch(Exception er){
			er.printStackTrace();
		}
	}


	public void setDiaChiXuatHoaDon(String _diachixhd) {

		this.diachixhd=_diachixhd;
	}


	public String getDiaChiXuatHoaDon() {

		return this.diachixhd;
	}


	public void setMaSoThue(String _masothue) {

		this.masothue=_masothue;
	}


	public String getmaSoThue() {

		return this.masothue;
	}


	public void setKhoTT(String khott) {

		this.IdKhoTT=khott;
	}


	public String getKhoTT() {

		return this.IdKhoTT;
	}

	public String getTuvanchuyen() {

		return this.tuvanchuyen;
	}

	public void setTuvanchuyen(String tuvanchuyen) {

		this.tuvanchuyen=tuvanchuyen;
	}

	public String getDvkdId()
	{
		return dvkdId;
	}

	public void setDvkdId(String dvkdId)
	{
		this.dvkdId = dvkdId;
	}
	public String getChietkhau()
	{
		return chietkhau;
	}

	public void setChietkhau(String chietkhau)
	{
		this.chietkhau = chietkhau;
	}

	public void setLoaiNPP(String loaiNPP) {

		this.loaiNPP = loaiNPP;
	}

	public String getLoaiNPP() {

		return this.loaiNPP;
	}
	public ResultSet getCapCn() {

		return this.capcnRs;
	}

	public void setCapCn(ResultSet capcnRs) {

		this.capcnRs = capcnRs;
	}
	public void setCapCnId(String idcapcn) {

		this.idcapcn = idcapcn;
	}

	public String getCapCnId() {

		return this.idcapcn;
	}
	

	public ResultSet getTructhuoc() {

		return this.tructhuocRs;
	}

	public void setTructhuoc(ResultSet tructhuocRs) {

		this.tructhuocRs = tructhuocRs;
	}

	public void setTructhuocId(String tructhuoc) {

		this.tructhuocId = tructhuoc;
	}

	public String getTructhuocId() {

		return this.tructhuocId;
	}

	public ResultSet getrs_gsbh() {

		return this.gsbhList;
	}

	public String getGsbhId() {

		return this.gsbhIds;
	}

	public void setGsbhId(String gbbhId) {

		this.gsbhIds = gbbhId;
	}

	public ResultSet getrs_khott() {

		return this.rs_khott;
	}

	public void setIsKhachhang(String isKH) {

		this.isKH = isKH;
	}

	public String getIsKhachhang() {

		return this.isKH;
	}

	public ResultSet getrs_nvbh() {

		return this.nvbhList;
	}

	public String getNvbhId() {

		return this.nvbhIds;
	}

	public void setNvbhId(String nvbhId) {

		this.nvbhIds = nvbhId;
	}

	public void setKyhieuHD(String kyhieuHD) {
		this.kyhieuHD = kyhieuHD;
	}
	public String getKyhieuHD() {
		return kyhieuHD;
	}

	public void setSoHDTu(String soHDTu) {
		SoHDTu = soHDTu;
	}
	public String getSoHDTu() {
		return SoHDTu;
	}

	public void setSoHDDen(String soHDDen) {
		SoHDDen = soHDDen;
	}
	public String getSoHDDen() {
		return SoHDDen;
	}

	public String getTuxuatkhoETC() {

		return this.tuxuatETC;
	}

	public void setTuxuatkhoETC(String tuxuatETC) {

		this.tuxuatETC = tuxuatETC;
	}


	public String getTutaohoadonOTC() {
		return this.tutaohoadonOTC;
	}


	public void setTutaohoadonOTC(String tutaohoadonOTC) {
		this.tutaohoadonOTC= tutaohoadonOTC;

	}


	public String getDungchungkenh() {

		return this.dungchungkenh;
	}


	public void setDungchungkenh(String dungchungkenh) {

		this.dungchungkenh = dungchungkenh;
	}

	String makho,manx;

	public String getMaKho()
	{
		return this.makho;
	}
	public void setMaKho(String makho)
	{
		this.makho =makho;
	}

	public String getMaNX()
	{
		return this.manx;
	}
	public void setMaNX(String manx)
	{
		this.manx= manx;
	}


	public String getLoaiCN() {

		return this.loaicn;
	}


	public void setLoaiCN(String loaicn) {

		this.loaicn=loaicn;
	}


	public ResultSet getLoaiCNRs() {

		return this.loaicnRs;
	}


	public void setLoaiCNRs(ResultSet loaicnRs) {

		this.loaicnRs=loaicnRs;
	}


	public String getTenKyHd() {

		return this.tenkyhd;
	}


	public void setTenKyHd(String TenKyHd) {

		this.tenkyhd=TenKyHd;
	}


	public void setHanmucno(String hanmucno) {

		this.Hanmucno = hanmucno;
	}


	public String getHanmucno() {

		return this.Hanmucno;
	}


	public void setSongayno(String songayno) {

		this.Songayno = songayno;
	}


	public String getSongayno() {

		return this.Songayno;
	}
	public String hanmucdoanhthu;



	public String getHanmucdoanhthu()
	{
		return hanmucdoanhthu;
	}

	public void setHanmucdoanhthu(String hanmucdoanhthu)
	{
		this.hanmucdoanhthu = hanmucdoanhthu;
	}


	public String getThuKho() {

		return this.thukho;
	}


	public void setThuKho(String thukho) {

		this.thukho = thukho;
	}

	public ResultSet getLoaiNppRs() {
		return loaiNppRs;
	}

	@Override
	public void setTonkhoAntoan(String tonkhoAT) {
		// TODO Auto-generated method stub
		this.tonkhoAntoan = tonkhoAT;
	}

	@Override
	public String getTonkhoAntoan() {
		// TODO Auto-generated method stub
		return this.tonkhoAntoan;
	}
	@Override
	public void setTansuatDathang(String tansuatDH) {
		// TODO Auto-generated method stub
		this.tansuatDathang = tansuatDH;
	}

	@Override
	public String getTansuatDathang() {
		// TODO Auto-generated method stub
		return this.tansuatDathang;
	}

	public String getTonkhotoida() {
		return tonkhotoida;
	}
	public void setTonkhotoida(String tonkhotoida) {
		this.tonkhotoida = tonkhotoida;
	}

	public String getPTChietkhau()
	{
		return PTchietkhau;
	}

	public void setPTChietkhau(String PTchietkhau)
	{
		this.PTchietkhau = PTchietkhau;
	}

	///////////////////// bổ sung Kh ETC
	String chucuahieu = "",tencuahieu = "",thanhthinongthonId = "",kbhId = "", phuongxa="",  didong = ""
		, xuatkhau = "", hopdong = "",ngaysinh = "",hchId = "",vtchId ="", lchId = "";
	double sotienno = 0; 
	ResultSet thanhthinongthonRs,hangcuahang,kenhbanhang,vtcuahang,loaicuahang ;
	public String getLchId() 
	{	
		return this.lchId;
	}

	public void setLchId(String lchId) 
	{	
		this.lchId = lchId;
	}

	public String getVtchId() 
	{
		return this.vtchId;
	}

	public void setVtId(String vtchId) 
	{
		this.vtchId = vtchId;
	}
	public double getSotienno() {
		return sotienno;
	}
	public void setSotienno(double sotienno) {
		this.sotienno = sotienno;
	}
	public String getHchId() 
	{
		return this.hchId;
	}

	public void setHchId(String hchId) 
	{
		this.hchId = hchId;
	}
	public String getChucuahieu() {

		return this.chucuahieu;
	}


	public void setChucuahieu(String chucuahieu) {

		this.chucuahieu = chucuahieu;
	}
	public String getTencuahieu() {
		return tencuahieu;
	}
	public void setTencuahieu(String tencuahieu) {
		this.tencuahieu = tencuahieu;
	}

	public String getThanhthinongthonId() {
		return thanhthinongthonId;
	}
	public void setThanhthinongthonId(String thanhthinongthonId) {
		this.thanhthinongthonId = thanhthinongthonId;
	}
	public ResultSet getThanhthinongthonRs() {
		return thanhthinongthonRs;
	}
	public ResultSet getHangcuahang() 
	{
		return this.hangcuahang;
	}

	public void setHangcuahang(ResultSet hangcuahang) 
	{
		this.hangcuahang = hangcuahang;		
	}
	public ResultSet getKenhbanhang() 
	{
		return this.kenhbanhang;
	}

	public void setKenhbanhang(ResultSet kenhbanhang) 
	{
		this.kenhbanhang = kenhbanhang;	
	}
	public ResultSet getVtcuahang() 
	{
		return this.vtcuahang;
	}

	public void setVtcuahang(ResultSet vtcuahang) 
	{
		this.vtcuahang = vtcuahang;
	}
	public ResultSet getLoaicuahang() 
	{
		return this.loaicuahang;
	}

	public void setLoaicuahang(ResultSet loaicuahang) 
	{
		this.loaicuahang = loaicuahang;
	}
	public String getKbhId() 
	{
		return this.kbhId;
	}

	public void setKbhId(String kbhId) 
	{
		this.kbhId = kbhId;
	}
	public String getPhuongxa() {
		return phuongxa;
	}
	public void setPhuongxa(String phuongxa) {
		this.phuongxa = phuongxa;
	}
	public String getDidong() {
		return didong;
	}
	public void setDidong(String didong) {
		this.didong = didong;
	}
	public String getXuatkhau()
	{
		return xuatkhau;
	}

	public void setXuatkhau(String xuatkhau)
	{
		this.xuatkhau = xuatkhau;
	}
	public String getHopdong()
	{
		return hopdong;
	}

	public void setHopdong(String hopdong)
	{
		this.hopdong = hopdong;
	}

	public String getNgaysinh()
	{
		return ngaysinh;
	}

	public void setNgaysinh(String ngaysinh)
	{
		this.ngaysinh = ngaysinh;
	}

	public String getLoaicongno() {
		return loaicongno;
	}
	public void setLoaicongno(String loaicongno) {
		this.loaicongno = loaicongno;
	}
}
