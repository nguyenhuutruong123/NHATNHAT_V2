package geso.dms.distributor.beans.phieuthanhtoan.imp;

import geso.dms.distributor.util.Utility;
import geso.dms.center.db.sql.*;
import geso.dms.distributor.beans.phieuthanhtoan.IDonvi;
import geso.dms.distributor.beans.phieuthanhtoan.IErpDonmuahang_Giay;
import geso.dms.distributor.beans.phieuthanhtoan.IHoadon;
import geso.dms.distributor.beans.phieuthanhtoan.IKho;
import geso.dms.distributor.beans.phieuthanhtoan.INhacungcap;
import geso.dms.distributor.beans.phieuthanhtoan.IPhieuchitamung;
import geso.dms.distributor.beans.phieuthanhtoan.ISanpham;
import geso.dms.distributor.beans.phieuthanhtoan.ITiente;
import geso.dms.distributor.beans.phieuthanhtoan.ITrungTamChiPhi;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import sun.security.util.Length;

import com.rp.util.DateTime;

public class ErpDonmuahang_Giay implements IErpDonmuahang_Giay
{
	String congtyId;
	String userId;
	String ctyId;
	String nppId;
	String cty;
	String id;
	String hinhThucTT = "", diadiemgiaohang = "", diachincc = "", htttId;
	String duyetdntt = "";
	String msgCanhbao = "";
	ResultSet rsTtDuyetRs, rsHttt, rsPhongban, rsPB, rsSP, rsDiaBan, rsBenhVien;
	
	public String getHinhThucTT() {
		return hinhThucTT;
	}

	public void setHinhThucTT(String hinhThucTT) {
		this.hinhThucTT = hinhThucTT;
	}

	// Them cot Nguon Goc Hang Hoa,TienTe_FK,mot don mua hang chi thuoc 1 loai
	// tien te
	String NguonGocHH;
	String MaLoaiHH;
	String Loaidoituong;
	String TienTe_FK;
	String GhiChu;
	String ThueNhapKhau;
	String PhanTramThue;
	String TrungTamChiPhi_FK;
	float TyGiaQuyDoi;
	// Them cot Nguon Goc Hang Hoa,TienTe_FK
	String ngaymuahang, ETD="", ETA= "";
	String dvthId;
	ResultSet dvthRs;
	ResultSet nvRs;
	ResultSet nhacungcapRs;
	String nccId;
	String nccTen;
	String nccLoai;
	String trangthai;
	String BVAT;
	String VAT;
	String AVAT;
	String tongtienconlai;
	String lhhId;
	String sochungtu;
	String[] duyetIds;
	ResultSet lhhRs;
	List<ISanpham> spList;
	List<IDonvi> dvList;
	List<ITiente> tienteList;
	List<IKho> khoList;
	List<ITrungTamChiPhi> ListTTCP;
	
	List<INhacungcap> nccList;
	
	List<IPhieuchitamung> phieuchiTURs;
	
	String msg;
	String dungsai;
	String lspId;
	String isdontrahang;
	String maketoStock;
	String khoId;
	String canduyet;
	String quanlyCN;
	
	String sothamchieu;
	String[] ghichuArr;
	
	String[] cpkDiengiai;
	String[] cpkSotien;
	
	String checkedNoiBo;
	
	String  Nvid;
	String NvTen;
	String lydo;
	
	String tongtienCantru;
	int sodong_pc ;
	
	String chucnang;
	
	String khId;
	String khTen;
	String kbhId;
	ResultSet kbhRs;
	ResultSet khachhangRs;
	
	public String getCheckedNoiBo() {
		return checkedNoiBo;
	}

	public void setCheckedNoiBo(String checkedNoiBo) {
		this.checkedNoiBo = checkedNoiBo;
	}

	
	dbutils db;
	
	private Utility util;

	public ErpDonmuahang_Giay()
	{
		this.Nvid="";
		this.NvTen="";
		this.userId = "";
		this.ctyId = "";
		this.cty = "";
		this.id = "";
		this.ngaymuahang = this.getDateTime();
		this.Loaidoituong="0";
		this.dvthId = "";
		this.nccId = "";
		this.nccTen = "";
		this.nccLoai = "";
		this.trangthai = "";
		this.BVAT = "";
		this.VAT = "10";
		this.tongtienconlai = "";
		this.sochungtu = "";
		this.AVAT = "";
		this.lhhId = "0";
		this.msg = "";
		this.dungsai = "10";
		this.NguonGocHH = "";
		this.MaLoaiHH = "";
		this.TienTe_FK = "100000";
		this.GhiChu = "";
		this.TyGiaQuyDoi = 1;
		this.nppId = "";
		this.lydo="";
		this.spList = new ArrayList<ISanpham>();
		this.dvList = new ArrayList<IDonvi>();
		this.tienteList = new ArrayList<ITiente>();
		this.khoList = new ArrayList<IKho>();
		this.ListTTCP = new ArrayList<ITrungTamChiPhi>();
		
		this.nccList = new ArrayList<INhacungcap>();
		
		this.phieuchiTURs = new ArrayList<IPhieuchitamung>();
		this.sodong_pc = 0;
		
		this.lydo="";
		this.checkedNoiBo = "0";
		this.lspId = "";
		this.isdontrahang = "1";
		this.maketoStock = "0";
		this.khoId = "";
		this.canduyet = "1";
		this.quanlyCN = "1";
		this.sothamchieu = "";
		this.db = new dbutils();
		this.util=new Utility();
		
		this.tongtienCantru = "";
		this.chucnang = "";
		
		this.khId = "";
		this.khTen = "";
		
		this.duyetdntt =  "";
		this.htttId = "";
		this.kbhId = "";
		
		this.msgCanhbao = "";
	}

	public ErpDonmuahang_Giay(String id)
	{
		this.userId = "";
		this.ctyId = "";
		this.cty = "";
		this.id = id;
		this.ngaymuahang = this.getDateTime();
		this.dvthId = "";
		this.nccId = "";
		this.nccTen = "";
		this.nccLoai = "";
		this.trangthai = "";
		this.BVAT = "";
		this.sochungtu = "";
		this.VAT = "10";
		this.AVAT = "";
		this.tongtienconlai = "";
		this.lhhId = "0";
		this.msg = "";
		this.dungsai = "10";
		this.MaLoaiHH = "";
		this.NguonGocHH = "";
		this.TienTe_FK = "100000";
		this.GhiChu = "";
		this.TyGiaQuyDoi = 1;
		this.spList = new ArrayList<ISanpham>();
		this.dvList = new ArrayList<IDonvi>();
		this.tienteList = new ArrayList<ITiente>();
		this.khoList = new ArrayList<IKho>();
		this.ListTTCP = new ArrayList<ITrungTamChiPhi>();
		
		this.nccList = new ArrayList<INhacungcap>();
		
		this.phieuchiTURs = new ArrayList<IPhieuchitamung>();
		this.sodong_pc = 0;
		
		this.checkedNoiBo = "0";
		this.lspId = "";
		this.isdontrahang = "1";
		this.maketoStock = "0";
		this.khoId = "";
		this.canduyet = "1";
		this.quanlyCN = "1";
		this.sothamchieu = "";
		this.db = new dbutils();
		this.util=new Utility();
		
		this.tongtienCantru = "";
		this.chucnang = "";
		
		this.khId = "";
		this.khTen = "";
		
		this.duyetdntt =  "";
		this.htttId = "";
		this.kbhId = "";
		
		this.msgCanhbao = "";
	}

	public String getCtyId()
	{
		return this.ctyId;
	}

	public void setCtyId(String ctyId)
	{
		this.ctyId = ctyId;
	}

	public String getCty()
	{
		return this.cty;
	}

	public void setCty(String cty)
	{
		this.cty = cty;
	}

	public String getUserId()
	{
		return this.userId;
	}

	public void setUserId(String userId)
	{
		this.userId = userId;
		
		/*String query = " SELECT PHONGBAN_FK FROM NHANVIEN WHERE PK_SEQ = "+this.userId;
		
		System.out.println(query);
		ResultSet rs = db.get(query);
		
		try
		{
			if(rs!=null)
			{
				if(rs.next())
				{
					this.dvthId = rs.getString("PHONGBAN_FK");
				}
			}
		}
		catch( Exception ex)
		{
			ex.printStackTrace();
		}*/
	}

	public String getId()
	{
		return this.id;
	}

	public void setId(String id)
	{
		this.id = id;
	}

	public String getNgaymuahang()
	{
		return this.ngaymuahang;
	}

	public void setNgaymuahang(String ngaymuahang)
	{
		this.ngaymuahang = ngaymuahang;
	}

	public String getDvthId()
	{
		return this.dvthId;
	}

	public void setDvthId(String dvthid)
	{
		this.dvthId = dvthid;
	}

	public ResultSet getDvthList()
	{
		return this.dvthRs;
	}

	public void setDvthList(ResultSet dvthlist)
	{
		this.dvthRs = dvthlist;
	}

	public String getNCC()
	{
		return this.nccId;
	}

	public void setNCC(String ncc)
	{
		this.nccId = ncc;
	}

	public String getTongtienchuaVat()
	{
		return this.BVAT;
	}

	public void setTongtienchuaVat(String ttchuavat)
	{
		this.BVAT = ttchuavat;
	}

	public String getVat()
	{
		/*if (this.VAT.length() == 0)
			this.VAT = "10";*/
		return this.VAT;
	}

	public void setVat(String vat)
	{
		this.VAT = vat;
	}

	public String getTongtiensauVat()
	{
		return this.AVAT;
	}

	public void setTongtiensauVat(String ttsauvat)
	{
		this.AVAT = ttsauvat;
	}

	public String getLoaispId()
	{
		return this.lspId;
	}

	public void setLoaispId(String loaispid)
	{
		this.lspId = loaispid;
	}

	public ResultSet getLoaiList()
	{
		return this.lhhRs;
	}

	public void setLoaiList(ResultSet loaihhlist)
	{
		this.lhhRs = loaihhlist;
	}

	public List<ISanpham> getSpList()
	{
		return this.spList;
	}

	public void setSpList(List<ISanpham> spList)
	{
		this.spList = spList;
	}

	public String[] getDuyetIds()
	{
		return this.duyetIds ;
	}

	public void setDuyetIds(String[] duyetIds)
	{
		this.duyetIds = duyetIds;
	}

	private void getNppInfo()
	{		
		//Phien ban moi
		geso.dms.distributor.util.Utility util=new geso.dms.distributor.util.Utility();
		this.nppId=util.getIdNhapp(this.userId);
	}
	
	public void createRs()
	{
		this.getNppInfo();
		String query = " SELECT distinct A.LOAICAP_FK, A.NHANVIEN_FK \n" +
		" FROM ( \n"+

		" 		SELECT A.LOAICAP_FK, A.NHANVIEN_FK  \n" +
		" 		FROM ERP_CAPQUANLY_CT A INNER JOIN ERP_CAPQUANLY B ON A.CAPQUANLY_FK = B.PK_SEQ \n" +
		" 		WHERE B.TRANGTHAI = 1 AND A.NPP_FK = "+this.nppId+" AND A.NHANVIEN_FK = " + this.userId +
		
		" 		UNION ALL \n"+
		
		" 		SELECT A.LOAICAP_FK, NVQL_FK NHANVIEN_FK \n" +
		" 		FROM ERP_CAPQUANLY A WHERE  A.NPP_FK = "+this.nppId+" AND A.TRANGTHAI = 1 AND A.NVQL_FK = "+this.userId +
		
		") A  \n";
		
		System.out.println(query);
		ResultSet RsKt = db.get(query);
		int stt = 0;
		String loaicap_fk = "";
		
		if(RsKt!=null)
		{
			try
			{
				while(RsKt.next())
				{
					stt++;
					this.chucnang =  RsKt.getString("LOAICAP_FK");
				}
				RsKt.close();
			}
			catch(Exception ex)
			{
				ex.printStackTrace();
			}
		}
		
		

		String sql=" select pk_seq, ten from ERP_DONVITHUCHIEN " +
				   " where trangthai = '1' "; 
		
		this.dvthRs = db.get(sql);
		
		this.lhhRs = db.get("Select pk_seq, ten, ma From Erp_LoaiSanPham where TRANGTHAI = '1' ");
		
		this.rsHttt = db.get("select pk_seq, ten from ERP_HINHTHUCTHANHTOAN where trangthai = '1' and PK_SEQ IN (100000,100001)");
		
		this.nvRs = db.get("Select pk_seq, (ma +' - ' + ten) as Ten From ERP_NHANVIEN where TRANGTHAI = '1' AND NPP_FK = "+this.nppId+"");
		
		this.nhacungcapRs = db.get("Select pk_seq, (ma +' - ' + ten) as Ten From TraphacoERP.dbo.ERP_NHACUNGCAP where TRANGTHAI = '1' AND NPP_FK = 1 ");
		
		//this.kbhRs = db.getScrol("Select pk_seq, DIENGIAI as Ten From KENHBANHANG where TRANGTHAI = '1' ");
		
		this.rsPhongban = this.db.get("select * from ERP_DONVITHUCHIEN where trangthai = '1' order by TEN asc ");
		
		this.rsPB = this.db.getScrol("select * from ERP_DONVITHUCHIEN where trangthai = '1' order by TEN asc ");
		
		//this.rsDiaBan = this.db.getScrol("SELECT CONVERT(VARCHAR, PK_SEQ) AS PK_SEQ, TEN AS TEN FROM diaban WHERE  TRANGTHAI = 1 ");
		
		//this.rsBenhVien = this.db.getScrol("SELECT CONVERT(VARCHAR, PK_SEQ) AS PK_SEQ, MAFAST + ' - ' + TEN AS TEN FROM KHACHHANG WHERE  TRANGTHAI = 1 AND LCH_FK IN (100011) ");
		
		//this.rsSP = this.db.getScrol("SELECT CONVERT(VARCHAR, PK_SEQ) AS PK_SEQ, MA_FAST + ' - ' + TEN AS TEN FROM SANPHAM WHERE  TRANGTHAI = 1");
		
		this.khachhangRs = db.get("Select pk_seq, (mafast +' - ' + ten) as Ten From KHACHHANG where TRANGTHAI = '1' AND NPP_FK ="+this.nppId+" ");		
		
		this.rsTtDuyetRs = db.get( 	 " SELECT C.TEN CAP, B.TEN TENNV, CASE A.TRANGTHAI WHEN 0 THEN N'Chưa duyệt' WHEN 1 THEN N'Đã duyệt' WHEN 2 THEN N'Đã xóa' END TRANGTHAI, \n"+
									 " ISNULL(A.LYDOMODUYET, '') MODUYET, ISNULL(A.LYDOSUA, '') LYDOSUA, ISNULL(A.LYDOXOA, '') LYDOXOA \n"+
									 " FROM ERP_DUYETMUAHANG A INNER JOIN NHANVIEN B ON A.NHANVIEN_FK = B.PK_SEQ \n"+
									 " INNER JOIN ERP_LOAICAPQUANLY C ON C.PK_SEQ = A.LOAICAP_FK \n"+
									 " WHERE A.MUAHANG_FK = 0 ORDER BY A.THUTU ASC ");
		query = " SELECT C.TEN CAP, B.TEN TENNV, CASE A.TRANGTHAI WHEN 0 THEN N'Chưa duyệt' WHEN 1 THEN N'Đã duyệt' WHEN 2 THEN N'Đã xóa' END TRANGTHAI, \n"+
		 " ISNULL(A.LYDOMODUYET, '') MODUYET, ISNULL(A.LYDOSUA, '') LYDOSUA, ISNULL(A.LYDOXOA, '') LYDOXOA \n"+
		 " FROM ERP_DUYETMUAHANG A INNER JOIN NHANVIEN B ON A.NHANVIEN_FK = B.PK_SEQ \n"+
		 " INNER JOIN ERP_LOAICAPQUANLY C ON C.PK_SEQ = A.LOAICAP_FK \n"+
		 " WHERE A.MUAHANG_FK = 0 ORDER BY A.THUTU ASC ";
		System.out.println(query);
		
		ResultSet donvi = db.get("select PK_SEQ, DONVI from DONVIDOLUONG where TRANGTHAI = '1' ");
		this.dvList.clear();
		if (donvi != null)
		{
			try
			{
				while (donvi.next())
				{
					this.dvList.add(new Donvi(donvi.getString("pk_seq"), donvi.getString("donvi")));
				}
				donvi.close();
			}
			catch (SQLException e) { }
		}
	
		
		if(this.NguonGocHH.equals("TN"))
		{
			this.cpkDiengiai = null;
			this.cpkSotien = null;
		}
		

		query = "select tt.PK_SEQ, tt.MA, isnull((select top 1 TIGIAQUYDOI from ERP_TIGIA where TIENTE_FK = tt.PK_SEQ order by DENNGAY desc  ), 1) TIGIAQUYDOI "
			  + "from ERP_TIENTE tt "
			  + "where tt.Trangthai = '1' ";
		
		ResultSet tiente = db.get(query);
		this.tienteList.clear();
		if (tiente != null)
		{
			try
			{
				while (tiente.next())
				{
					this.tienteList.add(new Tiente(tiente.getString("pk_seq"), tiente.getString("ma"), tiente.getString("TIGIAQUYDOI")));
				}
				tiente.close();
			}
			catch (SQLException e)
			{
			}
		}

		if(this.TienTe_FK.trim().length() > 0)
		{
			String command = ""; 
			
			if(this.Nvid.trim().length() > 0)
			{
				if(this.id.length() > 0)
				{
					
					command ="SELECT DISTINCT tthd.PK_SEQ AS SOCHUNGTU, tthd.NGAYGHINHAN, ISNULL(tthd.NOIDUNGTT,'') as NOIDUNGTT, DN_CT.SOTIENAVAT AS SOTIEN, \n"+
						   "   ISNULL(DN_CT.SOTIENCANTRU,0) AS CANTRU, 1 AS CHON \n"+
						   "    FROM  ERP_THANHTOANHOADON tthd \n"+
						   "         INNER JOIN ERP_DENGHITT_THANHTOANHOADON DN_CT ON tthd.PK_SEQ = DN_CT.THANHTOANHOADON_FK \n"+
						   "         INNER JOIN ERP_MUAHANG DN ON DN_CT.DENGHITT_FK = DN.PK_SEQ \n"+
						   "    WHERE  DN.PK_SEQ = "+ this.id +" AND tthd.NHANVIEN_FK = "+ this.Nvid +" AND tthd.TIENTE_FK = "+ this.TienTe_FK +"  \n"+
						"UNION ALL \n";
				}
				
				command +=" SELECT DISTINCT tthd.PK_SEQ AS SOCHUNGTU, tthd.NGAYGHINHAN, ISNULL(tthd.NOIDUNGTT,'') as NOIDUNGTT, \n"+
						"   CASE WHEN tthd.NGAYGHINHAN = '2015-04-30' " +
						"   THEN tthd.SOTIENHD - ISNULL(DATHANHTOAN.DATHANHTOAN,0) - ISNULL(DACANTRU.CANTRU,0) \n"+
					    "   ELSE ISNULL(CHITU.SOTIENGOC,0) - ISNULL(DATHANHTOAN.DATHANHTOAN,0) - ISNULL(DACANTRU.CANTRU,0) END AS SOTIEN, \n"+
			            "   0 AS CANTRU,'0' AS CHON \n"+
						
						"   FROM ERP_THANHTOANHOADON tthd " +
						"   LEFT JOIN \n"+
						"	(select a.NHANVIEN_FK , a.PK_SEQ, SUM(b.SOTIENTT) as SOTIENGOC  " +
						"	 from ERP_THANHTOANHOADON a inner join ERP_THANHTOANHOADON_HOADON b on a.PK_SEQ = b.THANHTOANHD_FK \n"+
						"	 where a.TRANGTHAI = 1 AND b.LOAIHD in (1) \n"+ // Loại hd: Đề nghị tạm ứng 
						"	 group by a.NHANVIEN_FK, a.PK_SEQ  \n"+
						"   ) CHITU ON tthd.PK_SEQ = CHITU.PK_SEQ "+
						
						"   LEFT JOIN \n"+
						"	( \n"+     
						                      // Chi cho đề nghị tạm ứng
						"   select a.NHANVIEN_FK, a.PK_SEQ as TTHD_FK,  SUM(b.SOTIENTT) as DATHANHTOAN  " +
						"	from ERP_THANHTOANHOADON a inner join ERP_THANHTOANHOADON_HOADON b on a.PK_SEQ = b.THANHTOANHD_FK \n"+
						"	where  a.TRANGTHAI != 2 AND b.LOAIHD in (9) \n"+ 
						"	group by a.NHANVIEN_FK, a.PK_SEQ \n"+
						" \n"+
						"   UNION ALL \n"+     // Xóa tạm ứng nhân viên
						" \n"+
						"   select a.NHANVIEN_FK, b.CTTT_FK as TTHD_FK, SUM(b.TIENTHANHTOAN) as DATHANHTOAN  " +
						"	from ERP_XOAKHTRATRUOC a inner join ERP_XOAKHTRATRUOC_CTTT b on a.PK_SEQ = b.XOAKHTRATRUOC_FK \n"+
						"	where  a.TRANGTHAI != 2  \n"+ 
						"	group by a.NHANVIEN_FK, b.CTTT_FK \n"+
												
						"   UNION ALL \n"+   // Xóa nợ nhân viên (Nằm trong Xóa nợ NCC)

						"   select XN.NHANVIEN_FK , XNHD.THANHTOANHOADON_FK as TTHD_FK, sum(XNHD.SOTIENTT) as DATHANHTOAN  \n"+
						"	from ERP_XOANONCC_TAMUNG XNHD " +
						"		inner join ERP_XOANONCC XN on XNHD.XOANONCC_FK = XN.PK_SEQ \n"+
						"   where  XN.TRANGTHAI != 2 \n"+
						"   group by XN.NHANVIEN_FK, XNHD.THANHTOANHOADON_FK  \n"+
						
						"   ) DATHANHTOAN ON tthd.PK_SEQ = DATHANHTOAN.TTHD_FK AND tthd.NHANVIEN_FK = DATHANHTOAN.NHANVIEN_FK \n"+
						"  LEFT JOIN \n"+
						" ( \n"+
						"   SELECT DN.NHANVIEN_FK, DN_CT.THANHTOANHOADON_FK , SUM(DN_CT.SOTIENCANTRU) AS CANTRU \n"+
						"   FROM ERP_DENGHITT_THANHTOANHOADON DN_CT INNER JOIN ERP_MUAHANG DN ON DN_CT.DENGHITT_FK = DN.PK_SEQ \n"+
						"   WHERE DN.LOAIHANGHOA_FK='2' and DN.TYPE = 1 AND DN.TRANGTHAI NOT IN (3,4)  \n";
						if(this.id.trim().length() > 0)
						{
							command += "      AND DN.PK_SEQ != "+ this.id +" ";
						}
						
						command +="   GROUP BY DN.NHANVIEN_FK, DN_CT.THANHTOANHOADON_FK \n"+
						" ) DACANTRU ON tthd.PK_SEQ = DACANTRU.THANHTOANHOADON_FK  AND tthd.NHANVIEN_FK = DACANTRU.NHANVIEN_FK \n"+
						
						" WHERE tthd.NHANVIEN_FK = "+ this.Nvid +" AND tthd.TIENTE_FK = "+ this.TienTe_FK +"  "+
						"       and  ( (ISNULL(CHITU.SOTIENGOC,0) - ISNULL(DATHANHTOAN.DATHANHTOAN,0) - ISNULL(DACANTRU.CANTRU,0)) > 0 "+
						"               OR (tthd.NGAYGHINHAN = '2015-04-30' AND ISNULL(tthd.SOTIENHD,0) - ISNULL(DATHANHTOAN.DATHANHTOAN,0) - ISNULL(DACANTRU.CANTRU,0) > 0 ) ) \n";
				if(this.id.length() > 0)
				{
					command += "	AND tthd.PK_SEQ NOT IN (SELECT THANHTOANHOADON_FK FROM ERP_DENGHITT_THANHTOANHOADON WHERE DENGHITT_FK = '" + this.id + "') \n";
					
				}			
						
			}
			else if(this.nccId.trim().length() > 0)
			{
				if(this.id.length() > 0)
				{
					command ="SELECT DISTINCT tthd.PK_SEQ AS SOCHUNGTU, tthd.NGAYGHINHAN, ISNULL(tthd.NOIDUNGTT,'') as NOIDUNGTT, DN_CT.SOTIENAVAT AS SOTIEN, \n"+
						"   ISNULL(DN_CT.SOTIENCANTRU,0) AS CANTRU, 1 AS CHON \n"+
						"FROM  ERP_THANHTOANHOADON tthd \n"+
						"         INNER JOIN ERP_DENGHITT_THANHTOANHOADON DN_CT ON tthd.PK_SEQ = DN_CT.THANHTOANHOADON_FK \n"+
						"         INNER JOIN ERP_MUAHANG DN ON DN_CT.DENGHITT_FK = DN.PK_SEQ \n"+
						"WHERE  DN.PK_SEQ = "+ this.id +" AND tthd.NCC_FK = "+ this.nccId+" AND tthd.TIENTE_FK = "+ this.TienTe_FK +"  \n"+
						"UNION ALL \n";
				}
				command +="SELECT DISTINCT tthd.PK_SEQ AS SOCHUNGTU, tthd.NGAYGHINHAN, ISNULL(tthd.NOIDUNGTT,'') as NOIDUNGTT, ISNULL(CHITU.SOTIENGOC,0) - ISNULL(DATHANHTOAN.DATHANHTOAN,0)  - ISNULL(DACANTRU.CANTRU,0) AS SOTIEN, \n"+
						"       ISNULL(DACANTRU.CANTRU,0) AS CANTRU, CASE WHEN  ISNULL(DACANTRU.CANTRU,0) > 0 THEN '1' ELSE '0' END AS CHON \n"+
						"FROM ERP_THANHTOANHOADON tthd INNER JOIN \n"+
						"	(select a.NCC_FK , a.PK_SEQ, SUM(b.SOTIENTT) as SOTIENGOC  " +
						"	 from ERP_THANHTOANHOADON a inner join ERP_THANHTOANHOADON_HOADON b on a.PK_SEQ = b.THANHTOANHD_FK \n"+
						"	 where a.TRANGTHAI = 1 AND b.LOAIHD in (1) \n"+ // Loại hd: Đề nghị tạm ứng 
						"	 group by a.NCC_FK, a.PK_SEQ  \n"+
						"   ) CHITU ON tthd.PK_SEQ = CHITU.PK_SEQ "+
						"   LEFT JOIN \n"+
						"	( \n"+
						"   select a.NCC_FK, a.PK_SEQ as TTHD_FK,  SUM(b.SOTIENTT) as DATHANHTOAN  " +
						"	from ERP_THANHTOANHOADON a inner join ERP_THANHTOANHOADON_HOADON b on a.PK_SEQ = b.THANHTOANHD_FK \n"+
						"	where  a.TRANGTHAI != 2 AND b.LOAIHD in (9) \n"+ // Loại hd: Chi tạm ứng
						"	group by a.NCC_FK, a.PK_SEQ \n"+
						" \n"+
						"   UNION ALL \n"+
						" \n"+
						"   select a.NCC_FK, b.CTTT_FK as TTHD_FK, SUM(b.TIENTHANHTOAN) as DATHANHTOAN  " +
						"	from ERP_XOAKHTRATRUOC a inner join ERP_XOAKHTRATRUOC_CTTT b on a.PK_SEQ = b.XOAKHTRATRUOC_FK \n"+
						"	where  a.TRANGTHAI != 2  \n"+ // Loại hd: Chi tạm ứng
						"	group by a.NCC_FK, b.CTTT_FK \n"+
						
						"   UNION ALL \n"+

					    "	select XN.NCC_FK , XNTU.THANHTOANHOADON_FK as TTHD_FK, sum(XNTU.SOTIENTT) as DATHANHTOAN   \n"+
						"	from ERP_XOANONCC_TAMUNG XNTU " +
						"		 inner join ERP_XOANONCC XN on XNTU.XOANONCC_FK = XN.PK_SEQ  \n"+
						"	where  XN.TRANGTHAI != 2 \n "+
						"   group by XN.NCC_FK,  XNTU.THANHTOANHOADON_FK  \n"+				
						
						"   ) DATHANHTOAN ON tthd.PK_SEQ = DATHANHTOAN.TTHD_FK AND tthd.NCC_FK = DATHANHTOAN.NCC_FK \n"+
						"  LEFT JOIN \n"+
						" ( \n"+
						"   SELECT DN.NHACUNGCAP_FK , DN_CT.THANHTOANHOADON_FK , SUM(DN_CT.SOTIENCANTRU) AS CANTRU \n"+
						"   FROM ERP_DENGHITT_THANHTOANHOADON DN_CT INNER JOIN ERP_MUAHANG DN ON DN_CT.DENGHITT_FK = DN.PK_SEQ \n"+
						"   WHERE DN.LOAIHANGHOA_FK='2' and DN.TYPE = 1  AND DN.TRANGTHAI NOT IN (3,4) \n";
				if(this.id.trim().length() > 0)
				{
					command += "      AND DN.PK_SEQ != "+ this.id +" ";
				}
				
				command +="   GROUP BY DN.NHACUNGCAP_FK, DN_CT.THANHTOANHOADON_FK \n"+
						" ) DACANTRU ON tthd.PK_SEQ = DACANTRU.THANHTOANHOADON_FK  AND tthd.NCC_FK = DACANTRU.NHACUNGCAP_FK \n"+
						
						" WHERE tthd.NCC_FK = "+ this.nccId+" AND tthd.TIENTE_FK = "+ this.TienTe_FK +" and (ISNULL(CHITU.SOTIENGOC,0) - ISNULL(DATHANHTOAN.DATHANHTOAN,0)  - ISNULL(DACANTRU.CANTRU,0)) > 0  \n";
				
				if(this.id.length() > 0)
				{
					command += "	AND tthd.PK_SEQ NOT IN (SELECT THANHTOANHOADON_FK FROM ERP_DENGHITT_THANHTOANHOADON WHERE DENGHITT_FK = '" + this.id + "') \n";
					
				}
			}
			
			if(command.trim().length() > 0)
			{
				System.out.println("Câu query1" + command);
				ResultSet rs = this.db.get(command);
				List<IPhieuchitamung> pcList = new ArrayList<IPhieuchitamung>();
				NumberFormat formatter = new DecimalFormat("#,###,###"); 
				
				try
				{
					IPhieuchitamung pc = null;
					
					if(rs != null)
					{
						
						while(rs.next())
						{
							String sochungtu = rs.getString("SOCHUNGTU");
							String ngaychungtu = rs.getString("NGAYGHINHAN");						
							String noidungtt = rs.getString("NOIDUNGTT");
							
							String sotienAvat = formatter.format(rs.getDouble("SOTIEN"));
							String sotienCantru = formatter.format(rs.getDouble("CANTRU"));
							
							String chon = rs.getString("CHON");
							
							String conlai = formatter.format(rs.getDouble("SOTIEN") - rs.getDouble("CANTRU") ); 
							
							pc = new Phieuchitamung(sochungtu, ngaychungtu, noidungtt, sotienAvat, sotienCantru, conlai, chon);
							
							this.sodong_pc ++;
							
							pcList.add(pc);
						}
						rs.close();
					}
				}
				catch(Exception e)
				{
					e.printStackTrace();
				}
				
			   this.phieuchiTURs = pcList;
			   System.out.println("Size 0: "+ phieuchiTURs.size());
			}
			
		}

	}

	public void init()
	{
		NumberFormat formatter = new DecimalFormat("#,###,###.###"); 
		
		String query = 	"\n select a.PK_SEQ as dmhId, a.HTTT, isnull(a.NguonGocHH ,'') as NguonGocHH, isnull(a.TienTe_FK, '100000') as TienTe_FK, " +
						"\n 		 c.PREFIX + a.PREFIX + CAST(a.PK_SEQ as varchar(20)) as SOCHUNGTU, a.NGAYMUA, isnull(a.GhiChu,'') as GhiChu, " +
						"\n 		 a.DONVITHUCHIEN_FK as dvthId, a.LOAIHANGHOA_FK, a.LOAISANPHAM_FK, b.loainhacungcap_fk as nccLoai,nv.pk_seq as nvid,  nv.ma + ', ' + nv.TEN as nvTen, b.pk_seq as nccId, b.ma + ', ' + b.TEN as nccTen, isnull(a.ETD, '') as ETD, isnull(a.ETA, '') as ETA, " +
						"\n 		 isnull(a.TONGTIENAVAT, '0') as TONGTIENAVAT, isnull(a.VAT, '0') as VAT, isnull(a.TONGTIENBVAT, 0) as TONGTIENBVAT, isnull(a.Dungsai, '0') as dungsai, a.TRANGTHAI, b.loainhacungcap_fk, b.khoNL_Nhan_GC, a.quanlycongno, isnull(sothamchieu, '') as sothamchieu, " +
						"\n 		 isnull(b.noibo,0) as noibo, isnull(TONGTIENCANTRU,0) as TONGTIENCANTRU, isnull(TONGTIENCONLAI,0) as TONGTIENCONLAI  " +
						"\n 		 , isnull(b.noibo,0) as noibo, isnull(a.diadiemgiaohang, '') as diadiemgiaohang, isnull(a.LYDOTT,'') as lydo, KH.PK_SEQ as khid, KH.MAFAST + ' , ' + KH.TEN as khten, isnull(a.diachiNCC, '') diachiNCC, isnull(a.HTTT_FK, 10000) HTTT_FK  " +
						"\n from ERP_MUAHANG a left join TraphacoERP.dbo.ERP_NHACUNGCAP b on a.NHACUNGCAP_FK = b.PK_SEQ " +
						"\n left join ERP_NHANVIEN NV ON NV.PK_SEQ= A.NHANVIEN_FK "+
						"\n left join KHACHHANG KH ON KH.PK_SEQ= A.KHACHHANG_FK "+
						"\n inner join ERP_DONVITHUCHIEN c on c.PK_SEQ = a.DONVITHUCHIEN_FK  " +
						"\n where a.pk_seq = '" + this.id + "'" ;
		
		System.out.println("Don Mua Hang update : " + query);
		ResultSet rs = db.get(query);
		if (rs != null)
		{
			try
			{
				while (rs.next())
				{
					this.id = rs.getString("dmhId");
					this.ngaymuahang = rs.getString("ngaymua");
					this.dvthId = rs.getString("dvthId");
					
					this.nccLoai = rs.getString("nccLoai");
					this.lhhId = rs.getString("LOAIHANGHOA_FK");
					this.lspId = rs.getString("LOAISANPHAM_FK")== null ? "" : rs.getString("LOAISANPHAM_FK");
					this.BVAT = formatter.format(rs.getDouble("TONGTIENBVAT"));
					this.VAT = formatter.format(rs.getDouble("VAT"));
					this.AVAT = formatter.format(rs.getDouble("TONGTIENAVAT"));
					this.trangthai = rs.getString("trangthai");
					this.dungsai = rs.getString("dungsai");
					this.sochungtu = rs.getString("SOCHUNGTU");
					this.NguonGocHH = rs.getString("NguonGocHH");
					this.TienTe_FK = rs.getString("TienTe_FK");
					this.GhiChu = rs.getString("GhiChu");
					this.quanlyCN = rs.getString("quanlycongno");
					this.sothamchieu = rs.getString("SOTHAMCHIEU");
					this.hinhThucTT = rs.getString("HTTT");
					this.checkedNoiBo= rs.getString("noibo");
					this.diachincc = rs.getString("diachincc");
					this.htttId = rs.getString("httt_fk");
				
					this.tongtienCantru = formatter.format(rs.getDouble("TONGTIENCANTRU"));
					this.tongtienconlai = formatter.format(rs.getDouble("TONGTIENCONLAI"));
					
					this.lydo= rs.getString("lydo");
					
					this.diadiemgiaohang = rs.getString("diadiemgiaohang").trim();
					
					this.nccId = rs.getString("nccId")==null ? "" : rs.getString("nccId");
					this.Nvid = rs.getString("nvId") == null ? "" : rs.getString("nvId");
					this.khId = rs.getString("KHID") == null ? "" : rs.getString("KHID");
					
					if(rs.getString("nvId")==null && rs.getString("khId")==null){
						this.nccId = rs.getString("nccId")==null ? "" : rs.getString("nccId");;
						this.nccTen = rs.getString("nccTen");
						this.Loaidoituong="0";
					}else if(rs.getString("nccId")==null && rs.getString("khId")==null){
						this.Nvid = rs.getString("nvId");
						this.NvTen=rs.getString("nvten");
						this.Loaidoituong="1";
					}else{
						this.khId = rs.getString("khId");
						this.khTen=rs.getString("khten");
						this.Loaidoituong="2";
					}
					
					if(this.GhiChu.trim().length() > 0)
					{
						this.ghichuArr = this.GhiChu.split("__");
					}
					
					if(this.NguonGocHH.equals("NN"))
					{
						this.VAT = "0";
						this.AVAT = this.BVAT;
					}
					
					String loainhacungcap_fk = rs.getString("loainhacungcap_fk");
					if(this.lspId.equals("100009") && loainhacungcap_fk.equals("100003"))
					{
						this.khoId = rs.getString("khoNL_Nhan_GC") == null ? "" : rs.getString("khoNL_Nhan_GC");
					}
					this.ETD = rs.getString("ETD");
					this.ETA = rs.getString("ETA");
				}
				rs.close(); 
			}
			catch (Exception e)
			{
				e.printStackTrace();
			}
		}
		
		//INIT CHI PHI KHAC
		query = "select diengiai, sotien from ERP_MUAHANG_CPKHAC where muahang_fk = '" + this.id + "'  ";
		System.out.println("---KHOI TAO CPK: " + query);
		ResultSet rsCPK = db.get(query);
		
		String diengiaiCPK = "";
		String sotienCPK = "";
		if(rsCPK != null)
		{
			try {
				while(rsCPK.next())
				{
					diengiaiCPK += rsCPK.getString("diengiai") + "__";
					sotienCPK += rsCPK.getString("sotien") + "__";
				}
				rsCPK.close();
				
				
				if(diengiaiCPK.trim().length() > 0 )
				{
					diengiaiCPK = diengiaiCPK.substring(0, diengiaiCPK.length() - 2);
					this.cpkDiengiai = diengiaiCPK.split("__");
					
					sotienCPK = sotienCPK.substring(0, sotienCPK.length() - 2);
					this.cpkSotien = sotienCPK.split("__");
				}
			}
			catch (Exception e) { System.out.println("EXCEPTION CPK: " + e.getMessage() ); }
			
		}
		
		this.createRs();
		this.createSanpham();
	}

	private void createSanpham()
	{
		String query =      " select  A.PK_SEQ AS MHSP_FK, isnull(b.pk_seq,0) as spid, isnull( case when ( len(ltrim(rtrim(b.MA))) <= 0 or ( b.MA is null ) ) then b.ma else b.MA end, '' ) as spMa, 'NA' as quycach , \n" +
							" isnull(b.TEN,'') as spTen, \n" +
							" isnull(b.TEN,'') as spTen2, 'NA' as spNh,\n " +
							" isnull(tscd.pk_seq,0) as tscdid, isnull(tscd.ma, '') as tscdMa, isnull(a.diengiai, tscd.ten) as tscdTen, isnull(nts.ma, 'NA') as nstNh,  \n" +
							" isnull(ccdc.pk_seq,0) as ccdcid, isnull(ccdc.ma, '') as ccdcMa, isnull(a.diengiai, ccdc.DIENGIAI) as ccdcTen,  \n" +
							" isnull(ncp.pk_seq,0) as ncpid,isnull(ncp.ten, '') as ncpMa, isnull(a.diengiai, ncp.diengiai) as ncpTen, isnull(ttcp.diengiai, 'NA') as ncpNh, \n" +
							" isnull(a.donvi, '') as donvi, a.soluong, isnull(a.dongia, '0') as dongia, \n" +
							" isnull(a.thanhtien, '0') as thanhtien, isnull(a.phantramthue, '0') as phantramthue, isnull(a.thuenhapkhau, '0') as thuenhapkhau, ngaynhan, a.khott_fk, dungsai,  \n" +
							" isnull(muanguyenlieudukien_fk, 0) as mnlId, \n" +
							" isnull(a.tenhd, '') as tenhd \n" +
							" from erp_muahang_sp a left join SANPHAM b on a.sanpham_fk = b.pk_seq   \n" +
							" left join	TraphacoERP.dbo.erp_taisancodinh tscd on a.taisan_fk = tscd.pk_seq  \n" +
							" left join TraphacoERP.dbo.erp_nhomtaisan nts on tscd.NhomTaiSan_fk = nts.pk_seq   \n" +
							" left join	TraphacoERP.dbo.erp_congcudungcu ccdc on a.ccdc_fk = ccdc.pk_seq  \n" +
							" left join TraphacoERP.dbo.erp_nhomchiphi ncp on a.chiphi_fk = ncp.pk_seq \n" +
							" left join  TraphacoERP.dbo.erp_trungtamchiphi ttcp on ncp.ttchiphi_fk = ttcp.pk_seq  \n" +
							" where muahang_fk = '" + this.id + "' order by a.pk_seq asc ";
		
		System.out.println(" San pham init: " + query);
		ResultSet spRs = db.get(query);
		List<ISanpham> spList = new ArrayList<ISanpham>();
		
		NumberFormat formatter = new DecimalFormat("#,###,###.###"); 
		if (spRs != null)
		{
			try
			{
				ISanpham sp = null;
				while (spRs.next())
				{
					sp = new Sanpham();
					
					if(this.lhhId.equals("0"))
					{
						sp.setPK_SEQ(spRs.getString("spid"));
						sp.setMasanpham(spRs.getString("spMa"));
						sp.setTensanpham(spRs.getString("spTen2"));
						sp.setNhomhang(spRs.getString("spNh"));
						sp.setTenXHD(spRs.getString("spTen2"));
						sp.setMNLId(spRs.getString("mnlId"));
					}
					else
					{
						if(this.lhhId.equals("1")) //Tai san co dinh
						{
							sp.setPK_SEQ(spRs.getString("tscdid"));
							sp.setMasanpham(spRs.getString("tscdMa"));
							sp.setTensanpham(spRs.getString("tscdTen"));
							sp.setNhomhang(spRs.getString("nstNh"));
							sp.setTenXHD(spRs.getString("tscdTen"));
						}
						else
						{
							if(this.lhhId.equals("3")) //Cong cu dung cu
							{
								sp.setPK_SEQ(spRs.getString("ccdcId"));
								sp.setMasanpham(spRs.getString("ccdcMa"));
								sp.setTensanpham(spRs.getString("ccdcTen"));
								sp.setNhomhang("");
								sp.setTenXHD(spRs.getString("ccdcTen"));
							}
							else
							{
								sp.setPK_SEQ(spRs.getString("ncpid"));
								sp.setMasanpham(spRs.getString("ncpMa"));
								sp.setTensanpham(spRs.getString("ncpTen"));
								sp.setNhomhang(spRs.getString("ncpNh"));
								sp.setTenXHD(spRs.getString("ncpTen"));
							}
						}
					}
					
					sp.setSoluong(formatter.format(spRs.getDouble("soluong")));
					sp.setSoluong_bk(formatter.format(spRs.getDouble("soluong")));
					
					sp.setDonvitinh(spRs.getString("donvi"));
					sp.setDongia(formatter.format(spRs.getDouble("dongia")));
					sp.setThanhtien(formatter.format(spRs.getDouble("thanhtien")));
					
					sp.setNgaynhan(spRs.getString("ngaynhan"));
					
					if(spRs.getString("khott_fk") != null)
						sp.setKhonhan(spRs.getString("khott_fk"));
					sp.setThueNhapKhau(formatter.format(spRs.getDouble("thuenhapkhau")));
					sp.setPhanTramThue(formatter.format(spRs.getDouble("phantramthue")));
					
					sp.setTenHD(spRs.getString("tenhd"));

						query=" select a.*, b.TEN PHONGBAN, c.DIENGIAI KENHBANHANG  " +
							  " from erp_muahang_sp_hoadon a left join ERP_DONVITHUCHIEN b on a.PHONGBAN_FK = b.PK_SEQ " +
							  " left join KENHBANHANG c on a.KBH_FK = c.PK_SEQ " +
							  " where a.muahang_fk= "+this.id +" and a.muahang_sp_fk="+spRs.getString("MHSP_FK");
						
						System.out.println(query);
						ResultSet rshd=db.get(query);
						List<IHoadon> HdList = new ArrayList<IHoadon>();
						
						double thanhtientruocthue=0;
						double tienvat=0;
						double thanhtiensauvat=0;
						
						while (rshd.next()){
							IHoadon hd =new  Hoadon();
							
						/*	MUAHANG_FK	MUAHANG_SP_FK	MAHOADON	MAUSOHOADON	KYHIEU	SOHOADON
							NGAYHOADON	TENNHACUNGCAP	MASOTHUE	TIENHANG	THUESUAT	TIENTHUE	TONGCONG
							GHICHU PHONGBAN KBH */
							
							hd.setMahoadon(rshd.getString("MAHOADON"));
							hd.setMauhoadon(rshd.getString("MAUSOHOADON"));
							hd.setKyhieu(rshd.getString("KYHIEU"));
							hd.setSohoadon(rshd.getString("SOHOADON"));
							hd.setNgayhoadon(rshd.getString("NGAYHOADON"));							 
							hd.setNccHDId(rshd.getString("NHACUNGCAPID"));
							hd.setTenNCC(rshd.getString("TENNHACUNGCAP"));
							hd.setMasothue(rshd.getString("MASOTHUE"));
							
							String KenhBhId = rshd.getString("KBH_FK") == null ?"":rshd.getString("KBH_FK");
							hd.setKenhBhId(KenhBhId);
							
							String phongbanId = rshd.getString("PHONGBAN_FK") == null ?"":rshd.getString("PHONGBAN_FK");
							hd.setPhongBanId(phongbanId);
							
							String kenhbanhangId = rshd.getString("KBH_FK") == null ?"":rshd.getString("KBH_FK");
							hd.setKenhBhId(kenhbanhangId);
							
							String sanphamId = rshd.getString("SANPHAM_FK") == null ?"":rshd.getString("SANPHAM_FK");
							hd.setSanPhamId(sanphamId);
							
							String diabanId = rshd.getString("DIABAN_FK") == null ?"":rshd.getString("DIABAN_FK");
							hd.setDiaBanId(diabanId);
							
							String benhvienId = rshd.getString("BENHVIEN_FK") == null ?"":rshd.getString("BENHVIEN_FK");
							hd.setBenhVienId(benhvienId);
							
							String phongbanTen = rshd.getString("PHONGBAN") == null ?"":rshd.getString("PHONGBAN");							
							hd.setPhongBanTen(phongbanTen);
							
							String KenhBhTen = rshd.getString("KENHBANHANG") == null ?"":rshd.getString("KENHBANHANG");								
							hd.setKenhBhTen(KenhBhTen);
							 
							hd.setTienhang(""+rshd.getString("TIENHANG"));
							thanhtientruocthue=thanhtientruocthue+rshd.getDouble("TIENHANG");
							hd.setThuesuat(""+rshd.getString("THUESUAT"));
							hd.setTienthue(""+rshd.getString("TIENTHUE"));
							tienvat=tienvat+rshd.getDouble("TIENTHUE");
														
							hd.setCong("0");
							thanhtiensauvat=thanhtiensauvat+rshd.getDouble("TONGCONG");
							
							hd.setGhichu(rshd.getString("GHICHU"));
							HdList.add(hd);
						}
						rshd.close();
						sp.setHoadonList(HdList);
						sp.setThanhTienTruocThue(formatter.format(thanhtientruocthue));
						sp.setTienThue(formatter.format(tienvat));
						sp.setThanhtien(formatter.format(thanhtiensauvat));
						
						
					spList.add(sp);
					
					
				}
				spRs.close();
			}
			catch (Exception e)
			{
				e.printStackTrace();
				System.out.println("Khong the tao san Pham" + e.getMessage());
			}
		}

		this.spList = spList;
		System.out.println("size : "+this.spList.size());
	}

	public void CreatePOfromPR(ResultSet rs, String mnlId){
		try{
			this.getNppInfo();
			if(rs.next()){
				this.db.getConnection().setAutoCommit(false);
			
				String query = 	"Insert into Erp_MuaHang(NgayMua, DonViThucHien_FK, NhaCungCap_FK, " +
								"LoaiHangHoa_FK, LoaiSanPham_FK, TongTienAVAT, VAT, TongTienBVAT, DungSai, TrangThai, NgayTao, " +
								"NgaySua, NguoiTao, NguoiSua, npp_fk, HTTT) " +
								"Values('" + this.getDateTime() + "','100003', null, '0', '100002', '0', '0' , '0' , '0', '0', '" + this.getDateTime() + "', " +
								"'" + this.getDateTime() + "'," + this.userId + "," + this.userId + ", '" + this.nppId + "',N'" +this.hinhThucTT+"')";

				System.out.println("Insert into Erp_MuaHang " + query);
				if (!db.update(query))
				{
					this.msg = "Khong the tao moi Mua hang: " + query;
					System.out.println("2.Exception tai day: " + query);
					this.db.getConnection().rollback();		
				}

				query = "select SCOPE_IDENTITY() as dmhId";
				ResultSet rsDmh = db.get(query);
				if (rsDmh.next())
				{
					this.id = rsDmh.getString("dmhId");
					rsDmh.close();
				}
			
				query = " insert into ERP_MUAHANG_SP(muahang_fk, sanpham_fk, diengiai, donvi, soluong, muanguyenlieudukien_fk) " +
						" values(" + this.id + ", " + rs.getString("SANPHAM_FK") + ", N'" + rs.getString("TEN") + "', N'" + rs.getString("DONVI") + "', " +
						"'" + rs.getString("SOLUONG") + "', '" + mnlId + "')";

				this.db.update(query);
				
//				query = "update ERP_MUANGUYENLIEUDUKIEN SET DADATHANG = DADATHANG + " + rs.getString("SOLUONG") + " WHERE PK_SEQ = " + mnlId + " ";
				
/*				query = "UPDATE ERP_MUANGUYENLIEUDUKIEN  " +
						"SET ERP_MUANGUYENLIEUDUKIEN.DADATHANG = ISNULL(A.SOLUONG, 0) " +
						"FROM " + 
						"( " +
						"	SELECT SUM(SOLUONG) AS SOLUONG, MUANGUYENLIEUDUKIEN_FK " +
						"	FROM ERP_MUAHANG_SP WHERE MUANGUYENLIEUDUKIEN_FK = " + mnlId + " " +
						"	GROUP BY MUANGUYENLIEUDUKIEN_FK " +
						")A  WHERE ERP_MUANGUYENLIEUDUKIEN.PK_SEQ = " + mnlId + " " ;*/
				
				//Cap nhat DADATHANG trong ERP_MUANGUYENLIEUDUKIEN
				query = "UPDATE ERP_MUANGUYENLIEUDUKIEN	" +  
						"SET ERP_MUANGUYENLIEUDUKIEN.DADATHANG = ISNULL(A.SOLUONG, 0) " + 
						"FROM " +
						"( " +
						"	SELECT SUM(SOLUONG) AS SOLUONG, SANPHAM_FK, SUBSTRING(NGAYNHAN, 1, 4) AS NAM,	" + 
						"	CONVERT(INT, SUBSTRING(NGAYNHAN, 6,2)) AS THANG	" +
						"	FROM ERP_MUAHANG_SP " +
						"	WHERE SANPHAM_FK IS NOT NULL	" +
						"	GROUP BY SANPHAM_FK, SUBSTRING(NGAYNHAN, 1, 4),CONVERT(INT, SUBSTRING(NGAYNHAN, 6,2))	" +
						")A  " +
						"WHERE ERP_MUANGUYENLIEUDUKIEN.NAM = A.NAM	" + 
						"AND ERP_MUANGUYENLIEUDUKIEN.THANG = A.THANG	" +
						"AND ERP_MUANGUYENLIEUDUKIEN.SANPHAM_FK = A.SANPHAM_FK ";
				
						
				System.out.println(query);
				
				this.db.update(query);
						
				
				/*boolean vuotNganSach = false;
				//Chen co che duyet
				// insert nguoi duyet PO 
				query = "SELECT	DUYETCHIPHI.CHUCDANH_FK, DUYETCHIPHI.QUYETDINH, DUYETCHIPHI.THUTU " +
						"FROM ERP_MUAHANG MUAHANG " +
						"INNER JOIN ERP_CHINHSACHDUYETCHIPHI DUYETCHIPHI ON DUYETCHIPHI.DONVITHUCHIEN_FK = MUAHANG.DONVITHUCHIEN_FK " +
						"INNER JOIN ERP_KHOANGCHIPHI KHOANGCHIPHI ON KHOANGCHIPHI.PK_SEQ = DUYETCHIPHI.KHOANGCHIPHI_FK " +
						"INNER JOIN ERP_CHUCDANH CHUCDANH ON CHUCDANH.PK_SEQ = DUYETCHIPHI.CHUCDANH_FK " +
						"WHERE KHOANGCHIPHI.SOTIENTU < MUAHANG.TONGTIENBVAT AND (KHOANGCHIPHI.SOTIENDEN >= MUAHANG.TONGTIENBVAT OR KHOANGCHIPHI.SOTIENDEN IS NULL) " +
						"AND MUAHANG.PK_SEQ = '" + this.id +"' ORDER BY DUYETCHIPHI.THUTU" ;

				System.out.println("3.Duyet PO:" + query);
				
				rs = db.get(query);
				
				boolean dacoTongGiamDoc = false;
				int thutu = 0;
				
				while (rs.next())
				{
					if(rs.getString("CHUCDANH_FK").equals("100003"))
						dacoTongGiamDoc = true;
					
					thutu = Integer.parseInt(rs.getString("THUTU"));
					
					query = "INSERT INTO ERP_DUYETMUAHANG(MUAHANG_FK, CHUCDANH_FK, TRANGTHAI, QUYETDINH, THUTU) " +
							"VALUES('"+ this.id +"', '" + rs.getString("CHUCDANH_FK") + "', '0','" + rs.getString("QUYETDINH")+ "','" + rs.getString("THUTU") + "') ";
					
					System.out.println("4. Insert Duyet PO:" + query);
					if (!db.update(query))
					{
						this.msg = "Khong the them nguoi duyet cho PO: " + query;
						db.getConnection().rollback();
					}
				}
		
				if (rs != null) 
					rs.close();
				
				query = "Update ERP_MUAHANG set VUOTNGANSACH = '" + (vuotNganSach == true ? "1" : "0") + "' where pk_seq = '" + this.id + "' ";
				if (!db.update(query))
				{
					this.msg = "Khong the cap nhat ERP_MUAHANG: " + query;
					db.getConnection().rollback();
				}
				*/
				
				this.init();
			
				this.db.getConnection().commit();
				this.db.getConnection().setAutoCommit(true);
			}
		}catch(java.sql.SQLException e){}
	}
	
	public boolean createDmh()
	{
		this.getNppInfo();
		// Kiem tra moi them vao
		String query = "";
		
		if(this.nccId.trim().length() <= 0 && this.Nvid.length()<=0 && this.khId.length()<=0)
		{
			this.msg = "Vui lòng chọn đối tượng thanh toán.";
			return false;
		}
		
		if(Double.parseDouble(this.tongtienconlai) < 0)
		{
			this.msg = "Số tiền còn lại không được phép nhỏ hơn 0. Vui lòng cập nhật lại.";
			return false;
		}
			
		//Check kho
		String loainccId = "";
		if(this.nccTen.trim().length() > 0 && this.lspId.trim().length() > 0)
		{
			if(this.lspId.equals("100009"))
			{				
				dbutils db = new dbutils();
				
				query = " select LOAINHACUNGCAP_FK, KhoNL_Nhan_GC from ERP_NHACUNGCAP where PK_SEQ = '" + this.nccId + "'";
				ResultSet rs = db.get(query);
				if(rs != null)
				{
					try 
					{
						if(rs.next())
						{
							loainccId = rs.getString("LOAINHACUNGCAP_FK");
							if(loainccId.equals("100003"))
							{
								this.khoId = rs.getString("KhoNL_Nhan_GC") == null ? "" : rs.getString("KhoNL_Nhan_GC");
							}
						}
						rs.close();
					} 
					catch (Exception e) { }
				}
			}
			
		}
				
		//Neu NCC o du lieu nen khong thiet lap QL Cong no, ma o don mua hang chon thiet lap cong no thi bao loi
		if(this.quanlyCN.equals("1") && this.nccId.length() >0)
		{			
			query = "select quanlycongno from Erp_NhaCungCap where pk_seq = '" + this.nccId + "' ";
			ResultSet rsCongNo = db.get(query);
			try 
			{
				if(rsCongNo.next())
				{
					if(rsCongNo.getString("quanlycongno").equals("0"))
					{
						rsCongNo.close();
						this.msg = "Nhà cung cấp trong dữ liệu nền không thiết lập quản lý công nợ. Vui lòng kiểm tra lại dữ liệu nền";
						return false;
					}
				}
				rsCongNo.close();
			} 
			catch (Exception e) {
				
			}
		}
		
		if(this.spList.size() <= 0)
		{
			this.msg = "Vui lòng nhập diễn giải chi phí";
			return false;
		}
		else
		{
			System.out.println("size"+ this.spList.size());
			for(int i = 0; i < this.spList.size(); i++)
			{
				ISanpham sp = this.spList.get(i);
				
				System.out.println("ten"+ sp.getTensanpham());
				
				if(sp.getTensanpham().trim().length() <= 0)
				{
					this.msg = "Vui lòng nhập diễn giải chi phí.";
					return false;
				}
	
				
			}
		}
		
		if(this.TienTe_FK.trim().length() <= 0)
		{
			this.msg = "Vui lòng chọn tiền tệ của đơn mua hàng";
			return false;
		}
		
		 
		try
		{
			String ngaytao = getDateTime();
			//String[] ncc = this.nccTen.split(" - ");
			db.getConnection().setAutoCommit(false);
			
			String loaisanpham = "NULL";
			if(this.lhhId.equals("0"))
				loaisanpham = this.lspId;
			
			String ghichu = "";
			if(this.ghichuArr != null)
			{
				for(int i = 0; i < this.ghichuArr.length; i++)
				{
					ghichu += this.ghichuArr[i] + "__";
				}
				if(ghichu.trim().length() > 0)
				{
					this.GhiChu = ghichu;
				}
			}
			
			
			String nam = this.ngaymuahang.substring(0, 4);
			String thang = this.ngaymuahang.substring(5, 7);

			//CAP NHAT SO PO, MA TU DONG TANG
			query=	"\n SELECT ISNULL( MAX(SOTUTANG_THEONAM), 0) AS MAXSTT, (SELECT PREFIX FROM ERP_DONVITHUCHIEN  "+
					"\n WHERE PK_SEQ ="+this.dvthId+" ) AS PREFIX   "+
					"\n FROM ERP_MUAHANG  DMH WHERE SUBSTRING(NGAYMUA, 0, 5) = "+nam+ 
					"\n AND DMH.DONVITHUCHIEN_FK="+this.dvthId; //and dmh.type != '1'
			
			System.out.println("Lay PO MAX______  :"+query);
			
			String soPO = "";
			int sotutang_theonam = 0;
			ResultSet rsPO = db.get(query);  
			if(rsPO.next())
			{
				sotutang_theonam =  (rsPO.getInt("maxSTT") + 1 );
				String prefix = rsPO.getString("PREFIX");
				String namPO = this.ngaymuahang.substring(2, 4);
				String chuoiso= ("0000"+ Integer.toString(sotutang_theonam)).substring(("0000"+ Integer.toString(sotutang_theonam)).length()-4,("0000"+ Integer.toString(sotutang_theonam)).length());
				
				soPO = prefix + "-" +   chuoiso+ "/" + thang + "/" + namPO;
		 
			}
			rsPO.close();
			System.out.println("SO PO ___ : " + soPO);
			
			// INSERT NGÀY ĐẾN HẠN TT NẾU PO LÀ PO NỘI BỘ (ĐÙN TRONG PHIẾU CHI)
			String ngaydenhantt = "";
			if(this.lhhId.equals("2")&& this.isdontrahang.equals("0"))
			{
				// TÍNH NGÀY ĐẾN HẠN THANH TOÁN (DÙNG TRONG PHIẾU CHI) : ngày hóa đơn(Hóa đơn NCC) + thời hạn nợ(DLN)
				query = "\n SELECT CONVERT(nvarchar(10), (dateadd(DAY, ISNULL(THOIHANNO,0), '" + this.ngaymuahang+ "')),120 ) as ngaydenhantt " +
						"\n FROM ERP_NHACUNGCAP " +
						"\n WHERE PK_SEQ = '"+ this.nccId +"'";
				
				ResultSet rsThoihanno = db.get(query);				
				if(rsThoihanno!= null)
				{
					while(rsThoihanno.next())
					{
						ngaydenhantt = rsThoihanno.getString("ngaydenhantt");
					}
					rsThoihanno.close();
				}
			}
			
			
			if(this.tongtienCantru.trim().length() <= 0) this.tongtienCantru = "0" ;
			
			query = "\n Insert into Erp_MuaHang ( TONGTIENCONLAI, TONGTIENCANTRU, NgayMua, DonViThucHien_FK, NhaCungCap_FK, LoaiHangHoa_FK, LoaiSanPham_FK, TongTienAVAT, VAT, TongTienBVAT, DungSai, TrangThai, NgayTao, NgaySua, NguoiTao, NguoiSua, NguonGocHH, TienTe_FK, GhiChu, TyGiaQuyDoi, type, npp_fk, quanlycongno, SOTHAMCHIEU, ETD, ETA,HTTT, SOPO, SOTUTANG_THEONAM, DIADIEMGIAOHANG,NHANVIEN_FK, LYDOTT, NGAYDENHANTT, ISDNTT, KHACHHANG_FK, diachiNCC, HTTT_FK )" +
					"\n values("+ this.tongtienconlai.replaceAll(",", "") +", "+ this.tongtienCantru.replaceAll(",", "") +", '" + this.ngaymuahang + "','" + this.dvthId + "'," + (this.nccId.length()>0?this.nccId:"NULL") + "," + this.lhhId + ", " + loaisanpham + ", " + this.AVAT + "," + this.VAT + ", " + this.BVAT + ", " + this.dungsai + ", '0', '" + ngaytao + "', '" + ngaytao + "'," + this.userId + "," + this.userId + ",'" + this.NguonGocHH + "'," + this.TienTe_FK + ",N'" + this.GhiChu + "'," +
					"" + this.TyGiaQuyDoi + ", '1', '" + this.nppId + "', '" + this.quanlyCN + "', N'" + this.sothamchieu + "', '" + this.ETD + "', '" + this.ETA + "',N'" +this.hinhThucTT+"', '" + soPO + "', '" + sotutang_theonam + "', N'" +this.diadiemgiaohang+"',"+(this.Nvid.length() >0?this.Nvid:"NULL")+", N'"+this.lydo+"', '"+ ngaydenhantt +"', '1', " + (this.khId.length()>0?this.khId:"NULL") + ", N'"+this.diachincc+"', "+this.htttId+")";
 
			System.out.println(query);
			if (!db.update(query))
			{
				this.msg = "Khong the tao moi Mua hang: " + query;
				db.getConnection().rollback();
				return false;
			}

			String dmhCurrent = "";
			query = "select SCOPE_IDENTITY() as dmhId";
			ResultSet rsDmh = db.get(query);
			if (rsDmh.next())
			{
				dmhCurrent = rsDmh.getString("dmhId");
				this.id = dmhCurrent;
				rsDmh.close();
			}
			
			
			// QUY TRÌNH MỚI
			
			// Kiểm tra và chèn các cấp duyệt vào bảng ERP_DUYETMUAHANG
			
			//1. KIỂM TRA CẤP DUYỆT CỦA USER ĐĂNG NHẬP
			
			String nhanvien_fk = this.userId;
			
			query = "\n SELECT distinct A.LOAICAP_FK, A.NHANVIEN_FK " +
					"\n FROM ( "+
					
					"\n 		SELECT A.LOAICAP_FK, A.NHANVIEN_FK  " +
					"\n 		FROM ERP_CAPQUANLY_CT A INNER JOIN ERP_CAPQUANLY B ON A.CAPQUANLY_FK = B.PK_SEQ " +
					"\n 		WHERE A.NPP_FK = "+this.nppId+" AND B.TRANGTHAI = 1 AND A.NHANVIEN_FK = " + nhanvien_fk +
					
					"\n 		UNION ALL "+
					
					"\n 		SELECT A.LOAICAP_FK, NVQL_FK NHANVIEN_FK " +
					"\n 		FROM ERP_CAPQUANLY A WHERE A.NPP_FK = "+this.nppId+" AND A.TRANGTHAI = 1 AND A.NVQL_FK = "+nhanvien_fk +
					
					"\n ) A  ";
			
			ResultSet RsKt = db.get(query);
			int stt = 0;
			String loaicap_fk = "";
			
			if(RsKt!=null)
			{
				while(RsKt.next())
				{
					stt++;
					loaicap_fk =  RsKt.getString("LOAICAP_FK");
				}
				RsKt.close();
			}
			
			if(stt == 0)
			{
				this.msg = "Nhân viên này không có trong phân quyền tạo đề nghị thanh toán. Yêu cầu tạo quyền cho nhân viên này";
				db.getConnection().rollback();
				return false;
			}
			
			// NẾU 1 NHÂN VIÊN CÓ 2 QUYỀN => BÁO LỖI
			if(stt > 1)
			{
				this.msg = "Nhân viên này có 2 quyền. Vui lòng ktra lại";
				db.getConnection().rollback();
				return false;
			}
			
			// INSERT QUYỀN CỦA NHÂN VIÊN VỪA TẠO VÀO BẢNG DUYỆT ĐƠN HÀNG - TỰ DUYỆT
			
			query = " INSERT ERP_DUYETMUAHANG (MUAHANG_FK, TRANGTHAI, NHANVIEN_FK, LOAICAP_FK, THUTU ) " +
					" VALUES ( "+this.id+", 1 , "+nhanvien_fk+", "+loaicap_fk+", 0 ) ";
			
			if (!db.update(query))
			{
				this.msg = "Khong the cap nhat ERP_DUYETMUAHANG: " + query;
				db.getConnection().rollback();
				return false;
			}
			
			//NẾU LÀ CHI PHÍ XEM CÓ VƯỢT NGÂN SÁCH KHÔNG
			for (int i = 0; i < this.spList.size(); i++)
			{
				ISanpham sp = this.spList.get(i);

				String SanPham_FK = "NULL";
				String ChiPhi_FK = "NULL";
				String TaiSan_FK = "NULL";
				String CCDC_FK = "NULL";
				
				if (this.lhhId.equals("0"))
				{
					SanPham_FK = sp.getPK_SEQ();
				}
				else
				{
					if(this.lhhId.equals("1"))  //Tai san co dinh
					{
						TaiSan_FK = sp.getPK_SEQ();
					}
					else
					{
						if(this.lhhId.equals("3"))  //CONG CU DUNG CU
						{
							CCDC_FK = sp.getPK_SEQ();
						}
						else  //Chi phi dich vu
						{
							ChiPhi_FK = sp.getPK_SEQ();
							if(ChiPhi_FK == null || ChiPhi_FK.trim().length() == 0) {
								ChiPhi_FK = "NULL";
							}
						}
					}
					
				}
							
			 
				if( !this.lhhId.equals("2") && SanPham_FK.equals("NULL") && TaiSan_FK.equals("NULL") && CCDC_FK.equals("NULL") ) // && ChiPhi_FK.equals("NULL")
				{
					this.msg = "Vui lòng kiểm tra lại mã sản phẩm / mã tài sản / mã công cụ dụng cụ / mã chi phí trong danh sách sản phẩm bạn nhập.";
					this.db.getConnection().rollback();
					return false;
				}
				
				long dongiaviet = Math.round((Double.parseDouble(sp.getDongia()) * this.TyGiaQuyDoi) );
				long thanhtienviet = Math.round( dongiaviet * Double.parseDouble(sp.getSoluong()) );
				
				String ptThue = "0";
				
				
				if(Double.parseDouble(sp.getTienThue()) > 0){
					ptThue = ""+(100* Double.parseDouble(sp.getTienThue())/Double.parseDouble(sp.getThanhTienTruocThue()));
					
				}
				
				String thueNK = "0";
				if(sp.getThueNhapKhau().trim().length() > 0)
					thueNK = sp.getThueNhapKhau();
				
				if(loaicap_fk.equals("10001") || loaicap_fk.equals("10002") ||  loaicap_fk.equals("10003") || loaicap_fk.equals("10004"))
				{
					if(ChiPhi_FK.equals("NULL") || ChiPhi_FK.equals(""))
					{
						this.msg = "Vui lòng nhập khoản mục chi phí!";		
						db.getConnection().rollback();
						return false;
					}
				}
			 
				query = "\n insert into ERP_MUAHANG_SP(muahang_fk, sanpham_fk, taisan_fk, ccdc_fk, chiphi_fk, diengiai, donvi, soluong, dongia, thanhtien, dongiaviet, thanhtienviet, ngaynhan, khott_fk, dungsai, PhanTramThue, ThueNhapKhau, TENHD) " +
						"\n values(" + dmhCurrent + ", " + SanPham_FK + ", " + TaiSan_FK + ", " + CCDC_FK + ", " + ChiPhi_FK + ", N'" + sp.getTensanpham() + "', N'" + sp.getDonvitinh() + "', '" + sp.getSoluong() + "','" + sp.getDongia() + "','" + sp.getThanhtien() + "' ,'" + dongiaviet + "', " +
						"'" + thanhtienviet + "', '" + sp.getNgaynhan() + "', " + (sp.getKhonhan().length() > 0 ? sp.getKhonhan() : null) + ", '" + this.dungsai + "','" + ptThue + "', '" + thueNK + "', N'" + sp.getTenHD() + "')";
				
				System.out.println("2.Insert Into Erp_MuaHang_SP :" + query);
				
				if (!db.update(query))
				{
					this.msg = "Khong the tao moi Mua hang - San pham: " + query;
					
					System.out.println("5.Exception tai day: " + query);
					db.getConnection().rollback();
					return false;
				}
				 //thêm chi tiết chi phí :
				
				query = "select SCOPE_IDENTITY() as dmhId";
				ResultSet rsmh_sp = db.get(query);
				String mhspid="";
				if (rsmh_sp.next())
				{
					mhspid = rsmh_sp.getString("dmhId");
				 
					rsmh_sp.close();
				}
				
				
				List<IHoadon> HdList=sp.getHoadonList();
				for(int j=0;j<HdList.size();j++){
					IHoadon hd=HdList.get(j);
					
					//NẾU LOẠI ĐỐI TƯỢNG LÀ NHÀ CUNG CẤP THÌ TA MẶC ĐỊNH LÀ NHÀ CUNG CẤP ĐÃ CHỌN ĐỂ LƯU VÀO BẢNG ERP_MUAHANG_SP_HOADON
					String tenncc ="";
					String idncc = "";
					String mst = "";
					if(this.getLoaidoituong().equals("0"))
					{	
						idncc  = getNCC();
						tenncc = getNccTen();
						mst = "(SELECT isnull(MASOTHUE,'') FROM ERP_NHACUNGCAP WHERE PK_SEQ = '"+idncc+"')";
					}
					else{
						idncc = hd.getNccHDId();
						tenncc = hd.getTenNCC();
						mst = "'"+hd.getMasothue()+"'";
					}
					
					if(hd.getMasothue().trim().length() > 0)
					{
						if(hd.getMasothue().trim().length()<10)
						{
							this.msg = "Mã số thuế phải tối thiểu là 10 số !";							 
							db.getConnection().rollback();
							return false;
						}
					}
						
					query= " INSERT INTO ERP_MUAHANG_SP_HOADON   (	MUAHANG_FK ,MUAHANG_SP_FK  ,MAHOADON  ,	MAUSOHOADON  ,	KYHIEU  ,SOHOADON  ,NGAYHOADON, NHACUNGCAPID "+  
						   " ,TENNHACUNGCAP  ,MASOTHUE  ,TIENHANG ,THUESUAT  ,	TIENTHUE  ,	TONGCONG   ,GHICHU , PHONGBAN_FK, KBH_FK , SANPHAM_FK, DIABAN_FK, BENHVIEN_FK, SOTT ) " +
						   " values("+this.id+","+mhspid+", " +
						   "'"+hd.getMahoadon()+"','"+hd.getMauhoadon()+"','"+hd.getKyhieu()+"','"+hd.getSohoadon()+"','"+hd.getNgayhoadon()+"',  "+ (idncc.trim().length() <=0 ? "NULL": idncc ) +", N'"+tenncc+"', " +
						   ""+mst+","+hd.getTienhang()+","+hd.getThuesuat()+","+hd.getTienthue()+","+hd.getCong()+",N'"+hd.getGhichu()+"', "+(hd.getPhongBanId().trim().length()<=0 ? "NULL" : hd.getPhongBanId())+", "+
						   (hd.getKenhBhId().trim().length()<=0 ? "NULL" : hd.getKenhBhId())+", "+(hd.getSanPhamId().trim().length()<=0 ? "NULL" : hd.getSanPhamId())+", "+(hd.getDiaBanId().trim().length()<=0 ? "NULL" : hd.getDiaBanId())+", "+(hd.getBenhVienId().trim().length()<=0 ? "NULL" : hd.getBenhVienId())+", "+i+") ";
					
					System.out.println(query);
					if (!db.update(query))
					{
						this.msg = "Khong the tao moi ERP_MUAHANG_SP_HOADON: " + query;
						 
						db.getConnection().rollback();
						return false;
					}
					
					// KIỂM TRA XEM CÓ TRÙNG KÝ HIỆU HÓA ĐƠN, SỐ HÓA ĐƠN, NGÀY HÓA ĐƠN, MÃ SỐ THUẾ
					
					query = " SELECT count (*) dem " +
							" FROM ERP_MUAHANG_SP_HOADON A INNER JOIN ERP_MUAHANG B ON A.MUAHANG_FK = B.PK_SEQ  " +
							" WHERE B.TRANGTHAI IN (0,1) AND A.MUAHANG_FK != "+this.id+" AND ISNULL(KYHIEU,'') != '' AND ISNULL(SOHOADON, '')!='' AND ISNULL(MASOTHUE, '') != '' " +
							" AND B.NPP_FK = "+this.nppId + " AND ISNULL(KYHIEU, '') = N'"+hd.getKyhieu() +"' AND ISNULL(SOHOADON, '') = '"+hd.getSohoadon()+"' AND ISNULL(MASOTHUE, '') = '"+ hd.getMasothue() +"' "+
							" GROUP BY KYHIEU, SOHOADON, MASOTHUE " +
							" HAVING count (MUAHANG_FK) >1 ";
					
					System.out.println(query);
					ResultSet rs = db.get(query);
					
					int dem_trung = 0;
					if(rs!=null)
					{
						while(rs.next())
						{
							dem_trung = rs.getInt("dem");
						}
						rs.close();
					}
					
					if(dem_trung > 1)
					{
						this.msg = "Không được phép trùng ký hiệu, số hóa đơn, ngày hóa đơn, mã số thuế! ";
						 
						db.getConnection().rollback();
						return false;
					}
					
					//KIỂM TRA XEM MST CÓ TỒN TẠI TRONG BẢNG MST CHƯA, NẾU CHƯA -> THÊM MỚI
					if(this.getLoaidoituong().equals("1")){
						if(hd.getMasothue().trim().length() > 0){
							query = "select count(*) as sodong from MST_NCC where mst ='"+hd.getMasothue()+"'";
							
							ResultSet check = db.get(query);
							
							int count = 0;
							if(check!=null){
								if(check.next()) count= check.getInt("sodong"); 
							}
							check.close();
							
							if(count==0){
								query = "INSERT INTO MST_NCC (MST, NCC) VALUES (N'"+hd.getMasothue()+"',N'"+hd.getTenNCC()+"')";
								
								if (!db.update(query))
								{
									this.msg = "Khong the tao moi MST_NCC: " + query;
									 
									db.getConnection().rollback();
									return false;
								}
							}
						}
					}
					
				}								
				
			} 
			
			// Insert vào bảng chi tiết Cần trừ tạm ứng
			
			List<IPhieuchitamung> pcList= this.phieuchiTURs;
			
			for(int j=0;j< pcList.size();j++)
			{
				IPhieuchitamung pc = pcList.get(j);
							
				query= "   INSERT INTO ERP_DENGHITT_THANHTOANHOADON (DENGHITT_FK ,THANHTOANHOADON_FK, SOTIENAVAT, SOTIENCANTRU,	CONLAI) values \n"+
					   "   ( "+ this.id +", "+ pc.getSochungtu() +", "+ pc.getSotienAvat() +", "+ pc.getSotienCantru() +", "+ pc.getConlai() +" ) ";
				
				if (!db.update(query))
				{
					this.msg = "Khong the tao moi ERP_DENGHITT_THANHTOANHOADON: " + query;					 
					db.getConnection().rollback();
					return false;
				}
				
				
			}
			
			// End Insert For tung dong
			
			
			//CAP NHAT CHIPHI KHAC NEU CO
			if(this.NguonGocHH.equals("NN") && this.lhhId.equals("0") )
			{
				double cpKHAC = 0;
				if(this.cpkDiengiai != null && this.cpkSotien != null )
				{
					for(int i = 0; i < this.cpkDiengiai.length; i++)
					{
						if(this.cpkSotien[i].trim().length() > 0 )
						{
							cpKHAC += Double.parseDouble(this.cpkSotien[i].replaceAll(",", "").trim() );
							
							query = "Insert ERP_MUAHANG_CPKHAC(muahang_fk, diengiai, sotien) " +
									"values('" + dmhCurrent + "', N'" + this.cpkDiengiai[i] + "', '" + this.cpkSotien[i].replaceAll(",", "") + "') ";
							
							if (!db.update(query))
							{
								this.msg = "Khong the tao moi Mua hang - San pham: " + query;
								
								System.out.println("5.1.Exception tai day: " + query);
								db.getConnection().rollback();
								return false;
							}
							
						}
					}
				}
				
				query = "UPDATE ERP_MUAHANG set CHIPHIKHAC = '" + cpKHAC + "' where pk_seq = '" + dmhCurrent + "' ";
				if (!db.update(query))
				{
					this.msg = "Khong the tao moi Mua hang - San pham: " + query;
					
					System.out.println("5.2.Exception tai day: " + query);
					db.getConnection().rollback();
					return false;
				}
			}			
	 
			db.getConnection().commit();
			db.getConnection().setAutoCommit(true);
			return true;
		}
		catch (Exception e)
		{
			e.printStackTrace();
			this.db.update("rollback");
			this.msg = "1.Exception " + e.getMessage();
			return false;
		}
		
	}

	public boolean updateDmh()
	{
		this.getNppInfo();
		// Kiem tra moi them vao
		String query = "";
		
		if(this.nccId.trim().length() <= 0 && this.Nvid.length()<=0 && this.khId.length()<=0)
		{
			this.msg = "Vui lòng chọn đối tượng thanh toán.";
			return false;
		}
		
		if(Double.parseDouble(this.tongtienconlai) < 0)
		{
			this.msg = "Số tiền còn lại không được phép nhỏ hơn 0. Vui lòng cập nhật lại.";
			return false;
		}
		
		//Check kho
		String loainccId = "";
		if(this.nccTen.trim().length() > 0 && this.lspId.trim().length() > 0)
		{
			if(this.lspId.equals("100009"))
			{
				//String[] ncc_arr = this.nccTen.split(" - ");
				
				dbutils db = new dbutils();
				
				query = "select LOAINHACUNGCAP_FK, KhoNL_Nhan_GC from ERP_NHACUNGCAP where PK_SEQ = '" + this.nccId + "'";
				ResultSet rs = db.get(query);
				if(rs != null)
				{
					try 
					{
						if(rs.next())
						{
							loainccId = rs.getString("LOAINHACUNGCAP_FK");
							if(loainccId.equals("100003"))
							{
								this.khoId = rs.getString("KhoNL_Nhan_GC") == null ? "" : rs.getString("KhoNL_Nhan_GC");
							}
						}
						rs.close();
					} 
					catch (Exception e) { }
				}
			}
		}
		
		
		//Neu NCC o du lieu nen khong thiet lap QL Cong no, ma o don mua hang chon thiet lap cong no thi bao loi
		if(this.quanlyCN.equals("1") && this.nccId.length() > 0 )
		{
			query = "select quanlycongno from Erp_NhaCungCap where pk_seq = '" + this.nccId + "' ";
			ResultSet rsCongNo = db.get(query);
			try 
			{
				if(rsCongNo!= null && rsCongNo.next())
				{
					if(rsCongNo.getString("quanlycongno").equals("0"))
					{
						rsCongNo.close();
						this.msg = "Nhà cung cấp trong dữ liệu nền không thiết lập quản lý công nợ. Vui lòng kiểm tra lại dữ liệu nền";
						return false;
					}
				}
				rsCongNo.close();
			} 
			catch (Exception e) {}
		}
		
		if(this.TienTe_FK.trim().length() <= 0)
		{
			this.msg = "Vui lòng chọn tiền tệ của đơn mua hàng";
			return false;
		}
		
		if(this.spList.size() <= 0)
		{
			this.msg = "Vui lòng nhập diễn giải chi phí";
			return false;
		}
		else
		{
			for(int i = 0; i < this.spList.size(); i++)
			{
				ISanpham sp = this.spList.get(i);				
				
				if(sp.getTensanpham().trim().length() <= 0)
				{
					this.msg = "Vui lòng nhập diễn giải chi phí.";
					return false;
				}	
				
			}
		}
		
		if(this.TienTe_FK.trim().length() <= 0)
		{
			this.msg = "Vui lòng chọn tiền tệ của đơn mua hàng";
			return false;
		}
	 
		try
		{
			String ngaysua = getDateTime();
			db.getConnection().setAutoCommit(false);
			
			String loaisanpham = "NULL";
			if(this.lhhId.equals("0"))
				loaisanpham = this.lspId;
			
			query = "SELECT DONVITHUCHIEN_FK, TONGTIENBVAT FROM ERP_MUAHANG WHERE PK_SEQ = '" + this.id + "' ";
			boolean approve = false; //Vượt tiền so với giá trị tiền đơn cũ
			boolean thaydoidvth = false;
			
			ResultSet rs = this.db.get(query);
			
			if(rs.next())
			{
				System.out.println("Tong tien BVAT: " + rs.getString("TONGTIENBVAT") + " -- BVAT: " + this.BVAT);
				System.out.println("DVTH: " + rs.getString("DONVITHUCHIEN_FK") + " -- DVTH NEW: " + this.dvthId);
				
				if(Double.parseDouble(rs.getString("TONGTIENBVAT")) != Double.parseDouble(this.BVAT))
					approve = true;
				
				if(!rs.getString("DONVITHUCHIEN_FK").equals(this.dvthId))
					thaydoidvth = true;
			}
			rs.close();
			
			String ghichu = "";
			if(this.ghichuArr != null)
			{
				for(int i = 0; i < this.ghichuArr.length; i++)
				{
					ghichu += this.ghichuArr[i] + "__";
				}
				if(ghichu.trim().length() > 0)
				{
					this.GhiChu = ghichu;
				}
			}
			
			//CAP NHAT SO PO, MA TU DONG TANG
			 String nam = this.ngaymuahang.substring(0, 4);
			 String thang = this.ngaymuahang.substring(5, 7);
				String soPO = "";
				int sotutang_theonam = 0;
				
				boolean cothaydoi_dvth=false;
				
			  query="\n select  donvithuchien_fk from erp_muahang mh " +
			  		"\n where mh.pk_seq="+this.id +" and ( donvithuchien_fk <>  "+this.dvthId +" or  SUBSTRING(NGAYMUA, 0, 5) <> "+nam +" ) ";
			  ResultSet rscheckdv=db.get(query);
			  if(rscheckdv.next()){
				  		cothaydoi_dvth=true;
				  		// Có thay đổi đơn vị thực hiện.phải thay đổi lại số PO và số thứ tự,hoặc năm bị thay đổi
						query=
						"\n SELECT ISNULL( MAX(SOTUTANG_THEONAM), 0) AS MAXSTT, (SELECT PREFIX FROM ERP_DONVITHUCHIEN  "+
						"\n WHERE PK_SEQ ="+this.dvthId+" ) AS PREFIX   "+
						"\n FROM ERP_MUAHANG  DMH WHERE SUBSTRING(NGAYMUA, 0, 5) = "+nam+ 
						"\n  AND DMH.DONVITHUCHIEN_FK="+this.dvthId;  // and dmh.type != '1'  
						System.out.println("Du lieu po sai  :"+query);
						
					
						ResultSet rsPO = db.get(query);  //MẤY NỮA BỔ SUNG THÊM, QUA NĂM MỚI SỐ TỰ TĂNG RESET VỀ BẰNG 1
						if(rsPO.next())
						{
							sotutang_theonam =  (rsPO.getInt("maxSTT") + 1 );
							String prefix = rsPO.getString("PREFIX");
							String namPO = this.ngaymuahang.substring(2, 4);
							String chuoiso= ("0000"+ Integer.toString(sotutang_theonam)).substring(("0000"+ Integer.toString(sotutang_theonam)).length()-4,("0000"+ Integer.toString(sotutang_theonam)).length());
							
							soPO = prefix + "-" +   chuoiso+ "/" + thang + "/" + namPO;
					 
						}
						rsPO.close();
						System.out.println("---SO PO: " + soPO);
				  
			  }
			  rscheckdv.close();
			
			
				// UPDATE NGÀY ĐẾN HẠN TT NẾU PO LÀ PO NỘI BỘ (DÙNG TRONG PHIẾU CHI)
				String ngaydenhantt = "";
				if(this.nccId.trim().length() > 0 && this.lhhId.equals("2")&& this.isdontrahang.equals("0"))
				{
					// TÍNH NGÀY ĐẾN HẠN THANH TOÁN (DÙNG TRONG PHIẾU CHI) : ngày hóa đơn(Hóa đơn NCC) + thời hạn nợ(DLN)
					query = "SELECT CONVERT(nvarchar(10), (dateadd(DAY, ISNULL(THOIHANNO,0), '" + this.ngaymuahang+ "')),120 ) as ngaydenhantt " +
							"FROM TraphacoERP.dbo.ERP_NHACUNGCAP " +
							"WHERE PK_SEQ = '"+ this.nccId +"'";
					ResultSet rsThoihanno = db.get(query);				
					if(rsThoihanno!= null)
					{
						while(rsThoihanno.next())
						{
							ngaydenhantt = rsThoihanno.getString("ngaydenhantt");
						}
						rsThoihanno.close();
					}
				}
			  
				if(this.tongtienCantru.trim().length() <= 0) this.tongtienCantru = "0" ;
				
			query = " update erp_muahang set TONGTIENCONLAI = "+ this.tongtienconlai.replaceAll(",", "") +", TONGTIENCANTRU = "+ this.tongtienCantru.replaceAll(",", "") +" , NHANVIEN_FK= "+(this.Nvid.length()>0?this.Nvid:"NULL")+", ngaymua = '" + this.ngaymuahang + "', donvithuchien_fk = '" + this.dvthId + "', type = '1', " +
					" nhacungcap_fk = " +(this.nccId.length()>0?this.nccId:"NULL")  + ", loaisanpham_fk = " + loaisanpham + ", loaihanghoa_fk = '" + this.lhhId + "', tiente_fk = '" + this.TienTe_FK + "', tygiaquydoi = '" + this.TyGiaQuyDoi + "', tongtienBVat = " + this.BVAT + ", " +
					" vat = " + this.VAT + ", tongtienAVat = " + this.AVAT + ", dungsai = '" + this.dungsai + "', ngaysua = '" + ngaysua + "', nguoisua = '" + this.userId + "'," +
					" GhiChu=N'" + this.GhiChu + "', SOTHAMCHIEU = N'" + this.sothamchieu + "', NguonGocHH = '" + this.NguonGocHH + "', quanlycongno = '" + this.quanlyCN + "', ETD = '" + this.ETD + "', ETA = '" + this.ETA + "',HTTT = N'" +this.hinhThucTT+"', DIADIEMGIAOHANG = N'" + this.diadiemgiaohang + "' , LYDOTT=N'"+this.lydo+"', NGAYDENHANTT = '"+ ngaydenhantt +"', KHACHHANG_FK = " + (this.khId.length()>0?this.khId:"NULL") + " , diachiNCC = N'"+this.diachincc +"', HTTT_FK = "+this.htttId;
			
			if(cothaydoi_dvth){
				query=query+ " ,SOTUTANG_THEONAM ='"+sotutang_theonam+"',SOPO='"+soPO+"' ";
			}
							
				query=query+	"  where pk_seq = '" + this.id + "'";
      
				System.out.println("Câu update: "+query);
				
			if (!db.update(query))
			{
				this.msg = "Khong the cap nhat Mua hang: " + query;
				db.getConnection().rollback();
				return false;
			}
			
			query = "delete ERP_DENGHITT_THANHTOANHOADON where DENGHITT_FK = '" + this.id + "'";
			if (!db.update(query))
			{
				this.msg = "Khong the cap nhat ERP_DENGHITT_THANHTOANHOADON: " + query;
				db.getConnection().rollback();
				return false;
			}
			
			query = "delete ERP_MUAHANG_SP_HOADON where muahang_fk = '" + this.id + "'";
			if (!db.update(query))
			{
				this.msg = "Khong the cap nhat ERP_MUAHANG_SP_HOADON: " + query;
				db.getConnection().rollback();
				return false;
			}
			query = "delete ERP_MUAHANG_SP where muahang_fk = '" + this.id + "'";
			System.out.println(query);
			if (!db.update(query))
			{
				this.msg = "Khong the cap nhat ERP_MUAHANG_SP: " + query;
				db.getConnection().rollback();
				return false;
			}
			
			//CAP NHAT CHIPHI KHAC NEU CO
			query = "update ERP_MUAHANG set CHIPHIKHAC = '0' where pk_seq = '" + this.id + "' ";
			if (!db.update(query))
			{
				this.msg = "Khong the tao moi Mua hang - San pham: " + query;
				
				System.out.println("5.2.Exception tai day: " + query);
				db.getConnection().rollback();
				return false;
			}
			
			query = "delete ERP_MUAHANG_CPKHAC where muahang_fk = '" + this.id + "' ";
			if (!db.update(query))
			{
				this.msg = "Khong the tao moi Mua hang - San pham: " + query;
				
				System.out.println("5.2.Exception tai day: " + query);
				db.getConnection().rollback();
				return false;
			}
			
			if(this.NguonGocHH.equals("NN") && this.lhhId.equals("0") )
			{
				double cpKHAC = 0;
				if(this.cpkDiengiai != null && this.cpkSotien != null )
				{
					for(int i = 0; i < this.cpkDiengiai.length; i++)
					{
						if(this.cpkSotien[i].trim().length() > 0 )
						{
							cpKHAC += Double.parseDouble(this.cpkSotien[i].replaceAll(",", "").trim() );
							
							query = "Insert ERP_MUAHANG_CPKHAC(muahang_fk, diengiai, sotien) " +
									"values('" + this.id + "', N'" + this.cpkDiengiai[i] + "', '" + this.cpkSotien[i].replaceAll(",", "") + "') ";
							
							if (!db.update(query))
							{
								this.msg = "Khong the tao moi Mua hang - San pham: " + query;
								
								System.out.println("5.1.Exception tai day: " + query);
								db.getConnection().rollback();
								return false;
							}
							
						}
					}
				}
				
				query = "UPDATE ERP_MUAHANG set CHIPHIKHAC = '" + cpKHAC + "' where pk_seq = '" + this.id + "' ";
				if (!db.update(query))
				{
					this.msg = "Khong the tao moi Mua hang - San pham: " + query;
					
					System.out.println("5.2.Exception tai day: " + query);
					db.getConnection().rollback();
					return false;
				}
			}
			

			//1. KIỂM TRA CẤP DUYỆT CỦA USER ĐĂNG NHẬP
			
			String nhanvien_fk = this.userId;
			
			query = "\n SELECT distinct A.LOAICAP_FK, A.NHANVIEN_FK, (SELECT THUTU FROM ERP_DUYETMUAHANG WHERE MUAHANG_FK = "+this.id+" AND NHANVIEN_FK = "+this.userId+") THUTU " +
					"\n FROM ( "+			

					"\n 		SELECT A.LOAICAP_FK, A.NHANVIEN_FK  " +
					"\n 		FROM ERP_CAPQUANLY_CT A INNER JOIN ERP_CAPQUANLY B ON A.CAPQUANLY_FK = B.PK_SEQ " +
					"\n 		WHERE A.NPP_FK = "+this.nppId+" AND B.TRANGTHAI = 1 AND A.NHANVIEN_FK = " + nhanvien_fk +
					
					"\n 		UNION ALL "+
					
					"\n 		SELECT A.LOAICAP_FK, NVQL_FK NHANVIEN_FK " +
					"\n 		FROM ERP_CAPQUANLY A WHERE A.NPP_FK = "+this.nppId+" AND A.TRANGTHAI = 1 AND A.NVQL_FK = "+nhanvien_fk +
					
					"\n ) A  ";
			
			ResultSet RsKt = db.get(query);
			int stt = 0;
			String loaicap_fk = "";
			int thutu = 0;
			
			if(RsKt!=null)
			{
				while(RsKt.next())
				{
					stt++;
					loaicap_fk =  RsKt.getString("LOAICAP_FK");
					thutu = RsKt.getInt("THUTU");
				}
				RsKt.close();
			}
			
			if(stt == 0)
			{
				this.msg = "Nhân viên này không có trong phân quyền tạo đề nghị thanh toán. Yêu cầu tạo quyền cho nhân viên này";
				db.getConnection().rollback();
				return false;
			}
			
			// NẾU 1 NHÂN VIÊN CÓ 2 QUYỀN => BÁO LỖI
			if(stt > 1)
			{
				this.msg = "Nhân viên này có 2 quyền. Vui lòng ktra lại";
				db.getConnection().rollback();
				return false;
			}
						
			// CẬP NHẬT QUYỀN CỦA USER TRONG ERP_DUYETMUAHANG
			
			query = " SELECT count(MUAHANG_FK) dem FROM ERP_DUYETMUAHANG WHERE NHANVIEN_FK = "+nhanvien_fk +" AND LOAICAP_FK = "+loaicap_fk+ " AND MUAHANG_FK = "+this.id;
			
			System.out.println(query);
			RsKt = db.get(query);
			int count = 0;
			
			if(RsKt!=null)
			{
				while(RsKt.next())
				{
					stt++;
					count = RsKt.getInt("dem");
				}
				RsKt.close();
			}
			
			if(count<=0){ // CHƯA CÓ TRONG BẢNG DUYỆT						
				query = " INSERT ERP_DUYETMUAHANG ( MUAHANG_FK, NHANVIEN_FK, LOAICAP_FK, TRANGTHAI, THUTU ) SELECT MUAHANG_FK, NHANVIEN_FK, LOAICAP_FK, 0 , THUTU  FROM ERP_DUYETMUAHANG_NHAP WHERE MUAHANG_FK = "+this.id+" AND NHANVIEN_FK = "+nhanvien_fk;
						
				if (!db.update(query))
				{
					this.msg = "Khong the cap nhat ERP_DUYETMUAHANG: " + query;
					db.getConnection().rollback();
					return false;
				}
			}
			
			for (int i = 0; i < this.spList.size(); i++)
			{
				ISanpham sp = this.spList.get(i);
				
				String SanPham_FK = "NULL";
				String ChiPhi_FK = "NULL";
				String TaiSan_FK = "NULL";
				String CCDC_FK = "NULL";
				
				if (this.lhhId.equals("0"))
				{
					
					SanPham_FK = sp.getPK_SEQ();
				}
				else
				{
					if(this.lhhId.equals("1"))  //Tai san co dinh
					{
						
						TaiSan_FK = sp.getPK_SEQ();
					}
					else
					{
						if(this.lhhId.equals("3"))  //Cong cu dung cu
						{
							
							CCDC_FK = sp.getPK_SEQ();
						}
						else  //Chi phi dich vu
						{
							
							ChiPhi_FK = sp.getPK_SEQ();
							if(ChiPhi_FK == null || ChiPhi_FK.trim().length() == 0) {
								ChiPhi_FK = "NULL";
							}
						}
					}
				}
				
				
				long dongiaviet = Math.round((Double.parseDouble(sp.getDongia()) * this.TyGiaQuyDoi) );
				long thanhtienviet = Math.round( dongiaviet * Double.parseDouble(sp.getSoluong()) );
				
				String ptThue = "0";				
				
				if(Double.parseDouble(sp.getTienThue()) > 0){
					ptThue = ""+(100* Double.parseDouble(sp.getTienThue())/Double.parseDouble(sp.getThanhTienTruocThue()));
					
				}
								
				String thueNK = "0";
				if(sp.getThueNhapKhau().trim().length() > 0)
					thueNK = sp.getThueNhapKhau();
				
				String muaNLdukien_fk =  ( ( sp.getMNLId().trim().length() <= 0 || sp.getMNLId() == null ) ? "null" : sp.getMNLId() );
				
				System.out.println("LOAICAP:"+loaicap_fk +", ChiPhi_FK: "+ChiPhi_FK);
				if(loaicap_fk.equals("10001") || loaicap_fk.equals("10002") ||  loaicap_fk.equals("10003") || loaicap_fk.equals("10004"))
				{
					if(ChiPhi_FK.equals("NULL") || ChiPhi_FK.equals(""))
					{
						this.msg = "Vui lòng nhập khoản mục chi phí!";		
						db.getConnection().rollback();
						return false;
					}
				}
								
				query = "insert into ERP_MUAHANG_SP ( muahang_fk, sanpham_fk, taisan_fk, ccdc_fk, chiphi_fk, diengiai, donvi, soluong, dongia, thanhtien, dongiaviet, thanhtienviet, ngaynhan, khott_fk, dungsai, PhanTramThue, ThueNhapKhau, Muanguyenlieudukien_fk, tenhd) " +
						"values(" + this.id + ", " + SanPham_FK + ", " + TaiSan_FK + ", " + CCDC_FK + ", " + ChiPhi_FK + ", N'" + sp.getTensanpham() + "', N'" + sp.getDonvitinh() + "', '" + sp.getSoluong() + "','" + sp.getDongia() + "','" + sp.getThanhtien() + "' ,'" + dongiaviet + "', " +
						"'" + thanhtienviet + "', '" + sp.getNgaynhan() + "', " + (sp.getKhonhan().length() > 0 ? sp.getKhonhan() : null) + ", '" + this.dungsai + "','" + ptThue + "', '" + thueNK + "', " + muaNLdukien_fk + ", N'"+sp.getTenHD()+"')";
				
				System.out.println("Insert Into Erp_MuaHang_SP :" + query);
				if (!db.update(query))
				{
					this.msg = "Khong the tao moi Mua hang - San pham: " + query;
					
					System.out.println("5.Exception tai day: " + query);
					db.getConnection().rollback();
					return false;
				}
							// CẬP NHẬT BẢNG CHI TIẾT HÓA ĐƠN
				
							query = "select SCOPE_IDENTITY() as dmhId";
							ResultSet rsmh_sp = db.get(query);
							String mhspid="";
							if (rsmh_sp.next())
							{
								mhspid = rsmh_sp.getString("dmhId");
							 
								rsmh_sp.close();
							}
							
							
							List<IHoadon> HdList=sp.getHoadonList();
							for(int j=0;j<HdList.size();j++){
								IHoadon hd=HdList.get(j);								
								
								//NẾU LOẠI ĐỐI TƯỢNG LÀ NHÀ CUNG CẤP THÌ TA MẶC ĐỊNH LÀ NHÀ CUNG CẤP ĐÃ CHỌN ĐỂ LƯU VÀO BẢNG ERP_MUAHANG_SP_HOADON
								String tenncc ="";
								String idncc = "";
								String mst = "";
								if(this.getLoaidoituong().equals("0"))
								{	
									idncc  = getNCC();
									tenncc = getNccTen();
									mst = "(SELECT isnull(MASOTHUE,'') FROM ERP_NHACUNGCAP WHERE PK_SEQ = '"+idncc+"')";
								}
								else{
									idncc = hd.getNccHDId();
									tenncc = hd.getTenNCC();
									mst = "'" +hd.getMasothue()+ "'";									
								}
														
								
								if(hd.getMasothue().trim().length() > 0)
								{
									if(hd.getMasothue().trim().length()<10)
									{
										this.msg = "Mã số thuế phải tối thiểu là 10 số !";							 
										db.getConnection().rollback();
										return false;
									}
								}								
									
								query= 	" INSERT INTO ERP_MUAHANG_SP_HOADON   (	MUAHANG_FK ,MUAHANG_SP_FK  ,MAHOADON  ,	MAUSOHOADON  ,	KYHIEU  ,SOHOADON  ,NGAYHOADON, NHACUNGCAPID "+  
								   		" ,TENNHACUNGCAP  ,MASOTHUE  ,TIENHANG  ,	THUESUAT  ,	TIENTHUE  ,	TONGCONG   ,GHICHU , PHONGBAN_FK, KBH_FK , SANPHAM_FK, DIABAN_FK, BENHVIEN_FK , SOTT) " +
								   		" values("+this.id+","+mhspid+", " +
								   		"'"+hd.getMahoadon()+"','"+hd.getMauhoadon()+"','"+hd.getKyhieu()+"','"+hd.getSohoadon()+"','"+hd.getNgayhoadon()+"',  "+ (idncc.trim().length() <=0 ? "NULL": idncc ) +", N'"+tenncc+"', " +
								   		""+mst+","+hd.getTienhang()+","+hd.getThuesuat()+","+hd.getTienthue()+","+hd.getCong()+",N'"+hd.getGhichu()+"', "+(hd.getPhongBanId().trim().length()<=0 ? "NULL" : hd.getPhongBanId())+", "+
								   		(hd.getKenhBhId().trim().length()<=0 ? "NULL" : hd.getKenhBhId())+", "+(hd.getSanPhamId().trim().length()<=0 ? "NULL" : hd.getSanPhamId())+", "+(hd.getDiaBanId().trim().length()<=0 ? "NULL" : hd.getDiaBanId())+", "+(hd.getBenhVienId().trim().length()<=0 ? "NULL" : hd.getBenhVienId())+", "+i+") ";
								
								if (!db.update(query))
								{
									this.msg = "Khong the tao moi ERP_MUAHANG_SP_HOADON: " + query;
									 
									db.getConnection().rollback();
									return false;
								}
								
								// KIỂM TRA XEM CÓ TRÙNG KÝ HIỆU HÓA ĐƠN, SỐ HÓA ĐƠN, NGÀY HÓA ĐƠN, MÃ SỐ THUẾ
								
								query = " SELECT count (*) dem " +
										" FROM ERP_MUAHANG_SP_HOADON A INNER JOIN ERP_MUAHANG B ON A.MUAHANG_FK = B.PK_SEQ  " +
										" WHERE B.TRANGTHAI IN (0,1) AND A.MUAHANG_FK != "+this.id+" AND ISNULL(KYHIEU,'') != '' AND ISNULL(SOHOADON, '')!='' AND ISNULL(MASOTHUE, '') != '' " +
										" AND B.NPP_FK = "+this.nppId + " AND ISNULL(KYHIEU, '') = N'"+hd.getKyhieu() +"' AND ISNULL(SOHOADON, '') = '"+hd.getSohoadon()+"' AND ISNULL(MASOTHUE, '') = '"+ hd.getMasothue() +"' "+
										" GROUP BY KYHIEU, SOHOADON, MASOTHUE " +
										" HAVING count (MUAHANG_FK) >1 ";
								System.out.println(query);
								rs = db.get(query);
								
								int dem_trung = 0;
								if(rs!=null)
								{
									while(rs.next())
									{
										dem_trung = rs.getInt("dem");
									}rs.close();
								}
								
								System.out.println("dem_trung:"+dem_trung);
								if(dem_trung > 1)
								{
									this.msg = "Không được phép trùng ký hiệu, số hóa đơn, ngày hóa đơn, mã số thuế! ";
									 
									db.getConnection().rollback();
									return false;
								}
								
								if(this.getLoaidoituong().equals("1")){
									if(hd.getMasothue().trim().length() > 0){
										query = "select count(*) as sodong from MST_NCC where mst ='"+hd.getMasothue()+"'";
										
										ResultSet check = db.get(query);
										
										count = 0;
										if(check!=null){
											if(check.next()) count= check.getInt("sodong"); 
										}
										check.close();
										
										if(count==0){
											query = "INSERT INTO MST_NCC (MST, NCC) VALUES (N'"+hd.getMasothue()+"',N'"+hd.getTenNCC()+"')";
											
											if (!db.update(query))
											{
												this.msg = "Khong the tao moi MST_NCC: " + query;
												 
												db.getConnection().rollback();
												return false;
											}
										}
									}
								}
								
							}
				
				
				
							
			}// End Insert For tung dong

			// Insert vào bảng chi tiết Cần trừ tạm ứng
			
			List<IPhieuchitamung> pcList= this.phieuchiTURs;
			
			for(int j=0;j< pcList.size();j++)
			{
				IPhieuchitamung pc = pcList.get(j);
							
				query= "   INSERT INTO ERP_DENGHITT_THANHTOANHOADON (DENGHITT_FK ,THANHTOANHOADON_FK, SOTIENAVAT, SOTIENCANTRU,	CONLAI) values \n"+
					   "   ( "+ this.id +", "+ pc.getSochungtu() +", "+ pc.getSotienAvat() +", "+ pc.getSotienCantru() +", "+ pc.getConlai() +" ) ";
				
				if (!db.update(query))
				{
					this.msg = "Khong the tao moi ERP_DENGHITT_THANHTOANHOADON: " + query;					 
					db.getConnection().rollback();
					return false;
				}
				
				
			}
						
			
			
			
			db.getConnection().commit();
			db.getConnection().setAutoCommit(true);
			return true;
		}
		catch (Exception e)
		{
			db.update("rollback");
			e.printStackTrace();
			this.msg = "Khong the cap nhat don hang " + query;
			return false;
		}
	}

	public String getMsg()
	{
		return this.msg;
	}

	public void setMsg(String msg)
	{
		this.msg = msg;
	}

	public List<IDonvi> getDvList()
	{
		return this.dvList;
	}

	public void setDvList(List<IDonvi> dvList)
	{
		this.dvList = dvList;
	}

	public List<IKho> getKhoList()
	{
		return this.khoList;
	}

	public void setKhoList(List<IKho> khoList)
	{
		this.khoList = khoList;
	}

	public List<ITiente> getTienteList()
	{
		return this.tienteList;
	}

	public void setTienteList(List<ITiente> ttList)
	{
		this.tienteList = ttList;
	}

	private String getDateTime()
	{
		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		Date date = new Date();
		return dateFormat.format(date);
	}

	public void close()
	{
		try
		{
			
			if (this.dvthRs != null){
				this.dvthRs.close();
			}
			if (this.lhhRs != null){
				this.lhhRs.close();
			}
			if(spList!=null){
				spList.clear();
			}
			if(dvList!=null){
				dvList.clear();
			}
			
			if(tienteList!=null){
				tienteList.clear();
			}
			if(khoList!=null){
				khoList.clear();
			}
			if(ListTTCP!=null){
				ListTTCP.clear();
			}
			this.db.shutDown();
		}
		catch (SQLException e)
		{
			
		}
		
	}

	public String getTrangthai()
	{
		return this.trangthai;
	}

	public void setTrangthai(String trangthai)
	{
		this.trangthai = trangthai;
	}

	public String getDungsai()
	{
		return this.dungsai;
	}

	public void setDungsai(String dungsai)
	{
		this.dungsai = dungsai;
	}

	public String getSochungtu() 
	{
		return this.sochungtu;
	}

	public void setSochungtu(String sochungtu)
	{
		this.sochungtu = sochungtu;
	}

	public void setNguonGocHH(String nguongoc)
	{
		this.NguonGocHH = nguongoc;
	}

	public String getNguonGocHH()
	{
		return this.NguonGocHH;
	}

	public void setMaLoaiHH(String maloaihh)
	{
		this.MaLoaiHH = maloaihh;

	}

	public String getMaLoaiHH()
	{

		return this.MaLoaiHH;
	}

	public void setTienTe_FK(String tiente_fk)
	{
		this.TienTe_FK = tiente_fk;

	}

	public String getTienTe_FK()
	{

		return this.TienTe_FK;
	}

	public String getGhiChu()
	{

		return this.GhiChu;
	}

	public void setGhiChu(String ghichu)
	{

		this.GhiChu = ghichu;
	}

	public void setTrungTamChiPhi_FK(String trungtamchiphi_fk)
	{
		this.TrungTamChiPhi_FK = trungtamchiphi_fk;
	}

	public String getTrungTamChiPhi_FK()
	{

		return this.TrungTamChiPhi_FK;
	}

	public void CreateListTrungTamChiPhi()
	{
		String query = "Select PK_SEQ,Ma,Ten From Erp_TrungTamChiPhi Where TrangThai=1";
		ResultSet rsTTCP = this.db.get(query);
		try
		{
			while (rsTTCP.next())
			{
				ITrungTamChiPhi o = new TrungTamChiPhi();
				o.setId(rsTTCP.getString("PK_SEQ"));
				o.setMaChiPhi(rsTTCP.getString("Ma"));
				o.setTen(rsTTCP.getString("Ten"));
				this.ListTTCP.add(o);
			}
			rsTTCP.close();
		}
		catch (SQLException e)
		{
			e.printStackTrace();
		}

	}

	public List<ITrungTamChiPhi> getTrungTamCpList()
	{

		return this.ListTTCP;
	}

	public void setTrungTamCpList(List<ITrungTamChiPhi> ttcp)
	{
		this.ListTTCP = ttcp;
	}

	public void setTyGiaQuyDoi(float tygiaquydoi)
	{
		this.TyGiaQuyDoi = tygiaquydoi;
	}

	public Float GetTyGiaQuyDoi()
	{

		return this.TyGiaQuyDoi;
	}

	public ResultSet getDuyet(){
		
		String query = 
			 " SELECT C.TEN CAP, B.TEN TENNV, CASE A.TRANGTHAI WHEN 0 THEN N'Chưa duyệt' WHEN 1 THEN N'Đã duyệt' WHEN 2 THEN N'Đã xóa' END TRANGTHAI, \n"+
			 " ISNULL(A.LYDOMODUYET, '') MODUYET, ISNULL(A.LYDOSUA, '') LYDOSUA, ISNULL(A.LYDOXOA, '') LYDOXOA \n"+
			 " FROM ERP_DUYETMUAHANG A INNER JOIN NHANVIEN B ON A.NHANVIEN_FK = B.PK_SEQ \n"+
			 " INNER JOIN ERP_LOAICAPQUANLY C ON C.PK_SEQ = A.LOAICAP_FK \n"+
			 " WHERE A.MUAHANG_FK = "+this.id + " ORDER BY A.THUTU ASC ";
		System.out.println(query);
		ResultSet rs = db.get(query);
		return rs;
	}
	
	public String getTrangthaiDuyet(){
		String result = "Chờ duyệt";
		
		String query = "SELECT TRANGTHAI AS RESULT " +
					   "FROM ERP_DUYETMUAHANG " +
					   "WHERE MUAHANG_FK = '" + this.id + "' AND CHUCDANH_FK = '100000' ";
		
		System.out.println(query);
		
		ResultSet rs = this.db.get(query);
		try{
			if (rs != null){ 				
				if(rs.next()){
					String tmp = rs.getString("RESULT");
					if(tmp != null){
						if(tmp.equals("1")) result = "Đã duyệt";
					}
					rs.close();
				}
			}
			
		}catch (SQLException e){}
		
		return result;
		
	}

	public String getLoaihanghoa() 
	{
		return this.lhhId;
	}

	public void setLoaihanghoa(String loaihh) 
	{
		this.lhhId = loaihh;
	}

	public String getIsdontrahang() 
	{
		return this.isdontrahang;
	}

	public void setIsdontrahang(String dontrahang) 
	{
		this.isdontrahang = dontrahang;
	}

	public String getMakeToStock()
	{
		return this.maketoStock;
	}

	public void setMakeToStock(String maketoStock)
	{
		this.maketoStock = maketoStock;
	}

	public String getCongtyId() 
	{
		return this.congtyId;
	}

	public void setCongtyId(String congtyId) 
	{
		this.congtyId = congtyId;
	}

	public String getKhoId() 
	{
		return this.khoId;
	}

	public void setKhoId(String khoId)
	{
		this.khoId = khoId;
	}

	public String getNccTen() {
	
		return this.nccTen;
	}

	public void setNccTen(String nccTen) {
		
		this.nccTen = nccTen;
	}

	public String getNccLoai() {
		
		return this.nccLoai;
	}

	public void setNccLOai(String nccLoai) {
		
		this.nccLoai = nccLoai;
	}

	
	public String getCanDuyet() {
		if(this.id!=null && this.id.length()>0){
		String sql="select DACHOT from  ERP_MUAHANG  where pk_seq ="+this.id;
		ResultSet rs=db.get(sql);
		try{
			if(rs.next()){
				this.canduyet=rs.getString("DACHOT");
			}
			rs.close();
		}catch(Exception er){
			er.printStackTrace();
		}
		
		}
		return this.canduyet;
	}

	
	public void setCanDuyet(String canduyet) {
		
		this.canduyet = canduyet;
	}
	
	public void setQuanlycongno(String quanlyCN) {
		
		this.quanlyCN = quanlyCN;
	}

	public String getQuanlycongno() {
		
		return this.quanlyCN;
	}

	
	public String getSoThamChieu() {
		
		return this.sothamchieu;
	}

	
	public void setSoThamChieu(String sothamchieu) {
		
		this.sothamchieu = sothamchieu;
	}

	public String[] getGhiChuArr() {
		return this.ghichuArr;
	}

	public void setGhiChuArr(String[] ghichuArr) {
		this.ghichuArr = ghichuArr;
	}
	

	
	public String getETD() {
		return this.ETD;
	}

	
	public void setETD(String ETD) {
		this.ETD = ETD;
	}

	
	public String getETA() {
		return this.ETA;
	}

	
	public void setETA(String ETA) {
		this.ETA = ETA;
	}

	
	public String[] getCpkDienGiai() {
		
		return this.cpkDiengiai;
	}

	
	public void setCpkDiengiai(String[] cpkDD) {
		
		this.cpkDiengiai = cpkDD;
	}

	
	public String[] getCpkSoTien() {
		
		return this.cpkSotien;
	}

	
	public void setCpkSoTien(String[] cpkST) {
		
		this.cpkSotien = cpkST;
	}

	
	public String getDiaDiemGiaoHang() {
		return this.diadiemgiaohang;
	}

	
	public void setDiaDiemGiaoHang(String ddgh) {
		this.diadiemgiaohang = ddgh;
	}
	
	public String getLoaidoituong() {
		
		return this.Loaidoituong;
	}

	
	public void setLoaidoituong(String loaidt) {
		
		this.Loaidoituong=loaidt;
	}

	
	public String getNvId() {
		
		return this.Nvid;
	}

	
	public void setNvId(String _NvId) {
		
		this.Nvid=_NvId;
	}

	
	public String getNvTen() {
		
		return this.NvTen;
	}

	
	public void setNvTen(String _NvTen) {
		
		this.NvTen=_NvTen;
	}

	// ********** LẤY DỮ LIỆU IN PDF *************//
	public void initPdf(String id) {
		
		NumberFormat formatter = new DecimalFormat("#,###,###.###"); 
		
		String query = " select a.PK_SEQ as dmhId, a.HTTT, isnull(a.NguonGocHH ,'') as NguonGocHH, isnull(a.TienTe_FK, '100000') as TienTe_FK, " +
				" c.PREFIX + a.PREFIX + CAST(a.PK_SEQ as varchar(20)) as SOCHUNGTU, a.NGAYMUA, isnull(a.GhiChu,'') as GhiChu, " +
				" a.DONVITHUCHIEN_FK as dvthId, a.LOAIHANGHOA_FK, a.LOAISANPHAM_FK, b.loainhacungcap_fk as nccLoai,nv.pk_seq as nvid,  nv.ma + ', ' + nv.TEN as nvTen, b.pk_seq as nccId, isnull(b.ma + ', ' + b.TEN, '') as nccTen, isnull(a.ETD, '') as ETD, isnull(a.ETA, '') as ETA, " +
				" isnull(a.TONGTIENAVAT, '0') as TONGTIENAVAT, isnull(a.VAT, '0') as VAT, isnull(a.TONGTIENBVAT, 0) as TONGTIENBVAT, isnull(a.Dungsai, '0') as dungsai, a.TRANGTHAI, b.loainhacungcap_fk, b.khoNL_Nhan_GC, a.quanlycongno, isnull(sothamchieu, '') as sothamchieu, " +
				" isnull(b.noibo,0) as noibo, isnull(nv.TEN,'') as nvten2 " +
				" , isnull(b.noibo,0) as noibo, isnull(a.diadiemgiaohang, '') as diadiemgiaohang, c.TEN as dvthTen, isnull(a.LYDOTT,'') as lydo  " +
				"  from ERP_MUAHANG a left join ERP_NHACUNGCAP b on a.NHACUNGCAP_FK = b.PK_SEQ " +
				" left join ERP_NHANVIEN NV ON NV.PK_SEQ= A.NHANVIEN_FK "+
				" inner join ERP_DONVITHUCHIEN c on c.PK_SEQ = a.DONVITHUCHIEN_FK  " +
				" where a.pk_seq = '" + id+ "' " ;
		
		System.out.println("in pdf : " + query);
		ResultSet rs = db.get(query);
		if (rs != null)
		{
			try
			{
				while (rs.next())
				{
					this.id = rs.getString("dmhId");
					this.ngaymuahang = rs.getString("ngaymua");
					this.dvthId = rs.getString("dvthTen");
					
					this.nccLoai = rs.getString("nccLoai");
					this.lhhId = rs.getString("LOAIHANGHOA_FK");
					this.lspId = rs.getString("LOAISANPHAM_FK")== null ? "" : rs.getString("LOAISANPHAM_FK");
					this.BVAT = formatter.format(rs.getDouble("TONGTIENBVAT"));
					this.VAT = formatter.format(rs.getDouble("VAT"));
					this.AVAT = formatter.format(rs.getDouble("TONGTIENAVAT"));
					this.trangthai = rs.getString("trangthai");
					this.dungsai = rs.getString("dungsai");
					this.sochungtu = rs.getString("SOCHUNGTU");
					this.NguonGocHH = rs.getString("NguonGocHH");
					this.TienTe_FK = rs.getString("TienTe_FK");
					this.GhiChu = rs.getString("GhiChu");
					this.quanlyCN = rs.getString("quanlycongno");
					this.sothamchieu = rs.getString("SOTHAMCHIEU");
					this.hinhThucTT = rs.getString("HTTT");
					this.checkedNoiBo= rs.getString("noibo");
					this.diadiemgiaohang = rs.getString("diadiemgiaohang").trim();
					this.nccTen = rs.getString("nccTen");
					
					
					this.NvTen= rs.getString("nvten2");
					
					this.lydo= rs.getString("lydo");
					
				
					
					if(this.GhiChu.trim().length() > 0)
					{
						this.ghichuArr = this.GhiChu.split("__");
					}
					
					if(this.NguonGocHH.equals("NN"))
					{
						this.VAT = "0";
						this.AVAT = this.BVAT;
					}
					
					String loainhacungcap_fk = rs.getString("loainhacungcap_fk");
					if(this.lspId.equals("100009") && loainhacungcap_fk.equals("100003"))
					{
						this.khoId = rs.getString("khoNL_Nhan_GC") == null ? "" : rs.getString("khoNL_Nhan_GC");
					}
					this.ETD = rs.getString("ETD");
					this.ETA = rs.getString("ETA");
				}
				rs.close(); 
			}
			catch (Exception e)
			{
				System.out.println("__Exception: " + e.getMessage());
			}
		}
	
		if(!(this.NvTen.length() > 0)){//---- loại đối tượng = NCC thì, lấy user ---//
			query= " select TEN from NHANVIEN where pk_seq='"+this.userId+"'  ";
	
			ResultSet nv = db.get(query);
			try{
				while(nv.next()){
					this.NvTen= nv.getString("TEN");
				}
				nv.close();
			}catch(Exception e){
				e.printStackTrace();
			}
		}
		
		query = "select diengiai, sotien from ERP_MUAHANG_CPKHAC where muahang_fk = '" + this.id + "'  ";
		System.out.println("---KHOI TAO CPK: " + query);
		ResultSet rsCPK = db.get(query);
		
		String diengiaiCPK = "";
		String sotienCPK = "";
		if(rsCPK != null)
		{
			try {
				while(rsCPK.next())
				{
					diengiaiCPK += rsCPK.getString("diengiai") + "__";
					sotienCPK += rsCPK.getString("sotien") + "__";
				}
				rsCPK.close();
				
				
				if(diengiaiCPK.trim().length() > 0 )
				{
					diengiaiCPK = diengiaiCPK.substring(0, diengiaiCPK.length() - 2);
					this.cpkDiengiai = diengiaiCPK.split("__");
					
					sotienCPK = sotienCPK.substring(0, sotienCPK.length() - 2);
					this.cpkSotien = sotienCPK.split("__");
				}
			}
			catch (Exception e) { System.out.println("EXCEPTION CPK: " + e.getMessage() ); }
			
		}
	
		
	}


	public String getLydoTT() 
	{

		return this.lydo;
	}

	public void setLydoTT(String lydo) 
	{
		this.lydo = lydo;
		
	}


	public List<INhacungcap> getNccList() 
	{
		return this.nccList;
	}

	public void setNccList(List<INhacungcap> nccList) 
	{
		this.nccList = nccList;
		
	}

	public String getTongtienCantru()
	{
		return this.tongtienCantru;
	}

	public void setTongtienCantru(String tongtienCantru)
	{
		this.tongtienCantru = tongtienCantru;
		
	}


	public List<IPhieuchitamung> getPhieuchiTURs() 
	{
		return this.phieuchiTURs;
	}

	public void setPhieuchiTURs(List<IPhieuchitamung> phieuchiTURs) 
	{
		this.phieuchiTURs = phieuchiTURs;
	}


	public int getSodongPC()
	{
		return this.sodong_pc;
	}

	public void setSodongPc(int sodong_pc)
	{
		this.sodong_pc = sodong_pc;
	}

	public String getTongtienconlai() 
	{
		return this.tongtienconlai;
	}

	public void setTongtienconlai(String tongtienconlai) 
	{
		this.tongtienconlai = tongtienconlai;
		
	}
	
	public String getChucnang() 
	{
		return this.chucnang;
	}

	public void setChucnang(String chucnang) 
	{
		this.chucnang = chucnang;
		
	}

	
	public ResultSet getNhanvienRs() {
		
		return this.nvRs;
	}

	
	public void setNhanvienRs(ResultSet nhanvienRs) {
		
		this.nvRs = nhanvienRs;
	}

	
	public ResultSet getNhaCungCapRs() {
		
		return this.nhacungcapRs;
	}

	
	public void setNhaCungCapRs(ResultSet nhacungcapRs) {
		
		this.nhacungcapRs = nhacungcapRs;
	}

	
	public String getKhId()
	{
		return this.khId;
	}

	public void setKhId(String khId)
	{
		this.khId = khId;
	}

	public String getKhTen()
	{
		return this.khTen;
	}

	public void setKhTen(String KhTen)
	{
		this.khTen = KhTen;
	}

	public ResultSet getKhachHangRs()
	{
		return this.khachhangRs;
	}

	public void setKhachHangRs(ResultSet khachhangRs) 
	{
		this.khachhangRs = khachhangRs;
	}

	
	public String getDiachiNCC() {
		
		return this.diachincc;
	}

	
	public void setDiachiNCC(String diachincc) {
		
		this.diachincc = diachincc;
	}

	
	public ResultSet getTtDuyetRs() {
		
		return this.rsTtDuyetRs;
	}

	
	public void setTtDuyetRs(ResultSet TtDuyetRs) {
		
		this.rsTtDuyetRs = TtDuyetRs;
	}

	
	public String getDuyetdntt() {
		
		return this.duyetdntt;
	}

	
	public void setDuyetdntt(String duyetdntt) {
		
		this.duyetdntt = duyetdntt;
	}

	
	public ResultSet getHtttRs() {
		
		return this.rsHttt;
	}

	
	public void setHtttRs(ResultSet HtttRs) {
		
		this.rsHttt = HtttRs;
	}

	
	public String getHtttId() {
		
		return htttId;
	}

	
	public void setHtttId(String htttId) {
		
		this.htttId = htttId;
	}

	
	public void setkbhRs(ResultSet kbhRs) {
	
		this.kbhRs = kbhRs;
	}

	
	public ResultSet getkbhRs() {
	
		return this.kbhRs;
	}

	
	public String getkbhId() {
	
		return this.kbhId;
	}

	
	public void setkbhId(String kbhId) {
	
		this.kbhId = kbhId;
	}

	
	public ResultSet getPBList() {
		
		return this.rsPB;
	}

	
	public void setPBList(ResultSet pblist) {
		
		this.rsPB = pblist;
	}

	
	public ResultSet getDiabanList() {
		
		return this.rsDiaBan;
	}

	
	public void setDiabanList(ResultSet diabanlist) {
		
		this.rsDiaBan = diabanlist;
	}

	
	public ResultSet getSPList() {
		
		return this.rsSP;
	}

	
	public void setSPList(ResultSet splist) {
		
		this.rsSP = splist;
	}

	
	public ResultSet getBenhVienList() {
		
		return this.rsBenhVien;
	}

	
	public void setBenhVienList(ResultSet bvlist) {
		
		this.rsBenhVien = bvlist;
	}

	
	public String getMsgCanhBao() {
		
		return this.msgCanhbao;
	}

	
	public void setMsgCanhBao(String msgcanhbao) {
		
		this.msgCanhbao = msgcanhbao;
	}


}
