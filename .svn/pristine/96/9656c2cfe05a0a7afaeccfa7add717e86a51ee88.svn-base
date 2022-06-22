package geso.dms.distributor.beans.reports.imp;

import geso.dms.center.util.Phan_Trang;
import geso.dms.center.util.Utility;
import geso.dms.distributor.beans.reports.IBcDoanhThuSanPham;
import geso.dms.distributor.db.sql.dbutils;

import java.io.Serializable;
import java.sql.ResultSet;

public class BcDoanhThuSanPham extends Phan_Trang implements IBcDoanhThuSanPham, Serializable 
{

	public BcDoanhThuSanPham()
	{
		this.spId="";
		this.nppId="";
		this.userId="";
		this.khId="";
		this.msg="";
		this.ddkdId="";
		this.tuNgay="";
		this.denNgay="";
		this.kbhId="";
		this.ttId="";
		this.nhomId="";
		this.vungId="";
		this.loaiHoaDon="0";
		this.action="";
		this.cndt="0";
		this.kh="0";
		this.action="";
		this.laynk="1";
		this.tpId = "";
		this.qhId = "";
		this.phuongxa = "";
		/*		if (Utility.isValid(phuongxa))
			phuongxa = phuongxa.trim() + ", ";
		else 
			phuongxa = "";*/
		db = new dbutils();
	}

	private static final long serialVersionUID = 1L;
	String tuNgay,denNgay,spId,nppId,ddkdId,userId,khId, tpId, qhId;
	String phuongxa;
	ResultSet phuongxaRs ;
	ResultSet tp;
	ResultSet qh = null;
	public String getKhId()
	{
		return khId;
	}
	public void setKhId(String khId)
	{
		this.khId = khId;
	}
	public String getTuNgay()
	{
		return tuNgay;
	}
	public void setTuNgay(String tuNgay)
	{
		this.tuNgay = tuNgay;
	}
	public String getDenNgay()
	{
		return denNgay;
	}
	public void setDenNgay(String denNgay)
	{
		this.denNgay = denNgay;
	}
	public String getSpId()
	{
		return spId;
	}
	public void setSpId(String spId)
	{
		this.spId = spId;
	}
	public String getNppId()
	{
		return nppId;
	}
	public void setNppId(String nppId)
	{
		this.nppId = nppId;
	}
	public String getDdkdId()
	{
		return ddkdId;
	}
	public void setDdkdId(String ddkdId)
	{
		this.ddkdId = ddkdId;
	}
	public String getUserId()
	{
		return userId;
	}
	public void setUserId(String userId)
	{
		this.userId = userId;
	}
	public ResultSet getSpRs()
	{
		return spRs;
	}
	public void setSpRs(ResultSet spRs)
	{
		this.spRs = spRs;
	}
	public ResultSet getDdkdRs()
	{
		return ddkdRs;
	}
	public void setDdkdRs(ResultSet ddkdRs)
	{
		this.ddkdRs = ddkdRs;
	}
	ResultSet spRs,ddkdRs,khRs,hoadonRs;
	public ResultSet getHoadonRs()
	{
		return hoadonRs;
	}
	public void setHoadonRs(ResultSet hoadonRs)
	{
		this.hoadonRs = hoadonRs;
	}
	public ResultSet getKhRs()
	{
		return khRs;
	}
	public void setKhRs(ResultSet khRs)
	{
		this.khRs = khRs;
	}

	public void closeDB()
	{

	}
	String laynk="";

	public String getLaynk() {
		return laynk;
	}
	public void setLaynk(String laynk) {
		this.laynk = laynk;
	}

	dbutils db = new dbutils();
	public void createRs()
	{

		Utility util = new Utility();

		String query="select pk_Seq,ma + '-' + ten as Ten from sanpham where trangthai=1 ";

		if (this.nhomId.length()>0)
		{
			query += " and pk_Seq in (select sanpham_fk from nhomhang_sanpham where nhomhang_fk='"+this.nhomId+"' ) ";
		}
		query += "  order by ma  ";

		this.spRs = this.db.get(query);

		query = "select pk_seq, manhanvien,ten from DaiDienKinhDoanh a where 1=1  ";
		if (this.view.length()>0)
		{
			query += " and pk_seq in  " + util.Quyen_Ddkd(userId) ;
		}

		if (this.nppId.length() > 0)
			query += " and a.pk_seq in ( select ddkd_fk from daidienkinhdoanh_npp where npp_fk = '" + nppId + "')  ";
		this.ddkdRs = this.db.get(query);
		System.out.println("npp la id ddddd"+this.nppId);
		/*if (this.nppId.length()>0)
		{*/
		query="select pk_seq,isnull(mafast,'') +' ' + ten + ' ' + isnull(diachi,'') as Ten from khachhang where 1=1 ";

		if (this.view.length()>0)
		{
			query+=" and npp_fk in "+util.quyen_npp(userId);
		}

		if (this.nppId.length()>0)
			query+=" and npp_fk='"+this.nppId+"' ";

		if (this.tpId.length()>0) 	 {	query+=" and tinhthanh_fk = '"+ this.tpId +"' "; }
		if (this.qhId.length()>0) 	 {	query+=" and quanhuyen_fk = '"+ this.qhId +"' "; }
		if (this.phuongxa.length()>0) {	query+=" and phuongxa = '"+ this.phuongxa +"' "; }

		this.khRs= this.db.get(query);
		System.out.println("khach hang "+query);
		//}

		query="select pk_Seq,ten,DIENGIAI from KENHBANHANG where TRANGTHAI=1 ";
		this.kbhRs = this.db.get(query);	

		query="select pk_seq,ten from tinhthanh where 1=1 ";

		if (this.vungId.length()>0)
		{
			query+=" and vung_fk='"+this.vungId+"' ";
		}
		this.ttRs=this.db.get(query);

		query="select pk_seq,ten from vung where 1=1 ";
		query+=" and pk_Seq in  "+util.Quyen_Vung(userId)+" ";

		this.vungRs=this.db.get(query);


		query="select pk_Seq,ten from NhomHang ";
		this.nhomRs=this.db.get(query);


		query="select pk_Seq,ten,diachi from Nhaphanphoi where trangthai=1 and iskhachhang=0   ";
		if (nppId.length()>0 && view.length()<=0)
		{
			query+=" and pk_seq ='"+this.nppId+"' ";
		}
		/*	query+= " union all select pk_Seq,ten,diachi from Nhaphanphoi where trangthai=1 and iskhachhang=0  ";
		if (nppId.length()>0)
		{
			query+=" and pk_seq ='"+this.nppId+"' ";
		}
		if (this.view.length()>0)
		{
			query+="and pk_Seq in "+util.quyen_npp(userId)+"" ;
		}*/

		if (this.ttId.length()>0)
			query+=" and tinhthanh_Fk='"+this.ttId+"' ";

		if (this.vungId.length()>0)
			query+=" and khuvuc_fk in (select pk_seq from khuvuc where vung_fk='"+this.vungId+"' ) ";

		System.out.println("_NPP_"+query);

		this.nppRs=this.db.get(query);

		createTpRS();
		createQhRS();
		createPxRS();
	}

	String queryHd="";
	public String getQueryHd()
	{
		return queryHd;
	}
	public void setQueryHd(String query)
	{
		this.queryHd=query;
	}

	private void getNppInfo()
	{		
		//Phien ban moi
		geso.dms.distributor.util.Utility util=new geso.dms.distributor.util.Utility();
		this.nppId=util.getIdNhapp(this.userId);
	}

	Utility Ult = new Utility();
	public void init(String search)
	{

		if (this.view.length()<=0)
		{
			this.getNppInfo();
		}

		String query;


		String condition="", condition_tra = "";   

		if (this.loaiHoaDon.length()<=0)
		{
			condition += "and a.LOAIHOADON = 0"; 
		}
		else 
		{
			condition += "and a.LOAIHOADON ='"+this.loaiHoaDon+"' ";
		}

		if (this.tuNgay.length()>0)
		{
			condition+=" and a.NgayXuatHD>='"+this.tuNgay+"'";
			condition_tra += " and a.ngaytra >= '"+ this.tuNgay +"' ";
		}
		if (this.denNgay.length()>0)
		{
			condition +=" and a.NgayXuatHD <='"+this.denNgay+"'";
			condition_tra += " and a.ngaytra <= '"+ this.denNgay +"' ";
		}
		if (this.nppId.length()>0)
		{
			condition +=" and a.npp_fk ='"+this.nppId+"'";
			condition_tra += " and a.npp_fk ='"+this.nppId+"'";
		}
		if (vungId.length()>0)
		{
			condition += " and a.npp_fk in (select pk_Seq  from nhaphanphoi  where khuvuc_fk in (select pk_seq from KHUVUC WHERE VUNG_FK='"+this.vungId+"' ))";
			condition_tra += " and a.npp_fk in (select pk_Seq  from nhaphanphoi  where khuvuc_fk in (select pk_seq from KHUVUC WHERE VUNG_FK='"+this.vungId+"' ))";
		}

		if (this.spId.length()>0)
		{
			condition+=" and b.sanpham_fk ='"+this.spId+"'";
			condition_tra += " and b.sanpham_fk ='"+this.spId+"'";
		}
		if (this.khId.length()>0)
		{
			condition+=" and a.khachhang_Fk ='"+this.khId+"'";
			condition_tra += " and a.khachhang_Fk ='"+this.khId+"'";
		}

		if (this.ttId.length()>0)
		{
			condition += " and a.khachHang_fk in (select pk_seq from khachhang  where tinhthanh_fk='"+this.ttId+"' ) ";
			condition_tra += " and a.khachHang_fk in (select pk_seq from khachhang  where tinhthanh_fk='"+this.ttId+"' ) ";
		}

		if (this.nhomId.length()>0)
		{
			condition+=" and b.SanPham_fk in  (select SANPHAM_FK FROM NHOMHANG_SANPHAM  where NhomHang_fk='"+this.nhomId+"' )  ";
			condition_tra += " and b.SanPham_fk in  (select SANPHAM_FK FROM NHOMHANG_SANPHAM  where NhomHang_fk='"+this.nhomId+"' )  ";
		}

		if (this.kbhId.length()>0)
		{
			condition+=" and a.pk_seq in (select hoadon_fk from hoadon_Ddh where ddh_fk in (select pk_seq from DonHang where kbh_fk='"+this.kbhId+"')) ";
		}

		if (this.ddkdId.length()>0)
		{
			condition += " and a.khachhang_fk in (select khachhang_fk from KHACHHANG_TUYENBH where tbh_fk in (select pk_Seq from tuyenbanhang where ddkd_Fk ='"+this.ddkdId+"'))";
			condition_tra += " and a.khachhang_fk in (select khachhang_fk from KHACHHANG_TUYENBH where tbh_fk in (select pk_Seq from tuyenbanhang where ddkd_Fk ='"+this.ddkdId+"'))";
		}
		String conditionETC="";   
		if (this.loaiHoaDon.length()<=0)
		{
			conditionETC+= "and isnull(a.LOAIHOADON,0) =0"; 
		}
		else 
		{
			conditionETC+= "and isnull(a.LOAIHOADON,0)  ='"+this.loaiHoaDon+"' ";
		}

		if (this.tuNgay.length()>0)
		{
			conditionETC+=" and a.NgayXuatHD>='"+this.tuNgay+"'";
		}
		if (this.denNgay.length()>0)
		{
			conditionETC+=" and a.NgayXuatHD <='"+this.denNgay+"'";
		}
		if (this.nppId.length()>0)
		{
			conditionETC+=" and a.npp_fk ='"+this.nppId+"'";
		}
		if (vungId.length()>0)
		{
			conditionETC+= "  and a.npp_fk in (select pk_Seq  from nhaphanphoi  where khuvuc_fk in (select pk_seq from KHUVUC WHERE VUNG_FK='"+this.vungId+"' ))";
		}

		if (this.spId.length()>0)
		{
			conditionETC+=" and C.sanpham_fk ='"+this.spId+"'";
		}
		if (this.khId.length()>0)
		{
			conditionETC+=" and a.pk_seq in (select hoadonnpp_fk from erp_hoadonnpp_Ddh where ddh_fk in (select pk_seq from Erp_DonDatHangNpp where khachhang_fk='"+this.khId+"')) ";
		}

		if (this.ttId.length()>0)
		{
			conditionETC+=	" and	(  a.khachHang_FK  in (select pk_seq	 from KhachHang  where tinhthanh_fk='"+this.ttId+"' ) OR  a.npp_dat_Fk in (select pk_seq from nhaphanphoi  where tinhthanh_fk='"+this.ttId+"' )  ) ";
		}

		String condition2019 = "";
		if (this.tpId.length()>0) { condition2019 += " and exists ( select 1 from tinhthanh where pk_seq = kh.tinhthanh_fk and pk_seq = '"+ this.tpId +"' ) "; }
		if (this.qhId.length()>0) { condition2019 += " and exists ( select 1 from quanhuyen where pk_seq = kh.quanhuyen_fk and pk_seq = '"+ this.qhId +"' ) "; }
		if (this.phuongxa.length()>0) { condition2019 += " and exists ( select 1 from phuongxa where pk_seq = kh.phuongxa and pk_seq = '"+ this.phuongxa +"' ) "; }

		if (this.nhomId.length()>0)
		{
			conditionETC+=" and C.SanPham_fk in  (select SANPHAM_FK FROM NHOMHANG_SANPHAM  where NHOMHANG_FK='"+this.nhomId+"' )  ";
		}


		if (this.kbhId.length()>0)
		{
			conditionETC+=" and a.pk_seq in (select hoadonnpp_fk from Erp_hoadonnpp_Ddh where ddh_fk in (select pk_seq from Erp_DonDatHangNpp where kbh_fk='"+this.kbhId+"' )) ";
		}

		if (this.ddkdId.length()>0)
		{
			conditionETC += " and a.khachhang_fk in (select khachhang_fk from KHACHHANG_TUYENBH where tbh_fk in (select pk_Seq from tuyenbanhang where ddkd_Fk ='"+this.ddkdId+"'))";
		}


		String condition_CK="";


		if (this.loaiHoaDon.length()<=0)
		{
			condition_CK+= "and isnull(a.LOAIHOADON,0) =0"; 
		}
		else 
		{
			condition_CK+= "and isnull(a.LOAIHOADON,0)  ='"+this.loaiHoaDon+"' ";
		}


		if (nppId.length()>0)
		{
			condition_CK+= " and a.npp_fk='"+this.nppId+"' ";
		}

		if (ttId.length()>0)
		{
			condition_CK+= "  and a.khachHang_fk in (select pk_seq from khachhang  where tinhthanh_fk='"+this.ttId+"' )";
		}

		if (vungId.length()>0)
		{
			condition_CK+= "  and a.npp_fk in (select pk_Seq  from nhaphanphoi  where khuvuc_fk in (select pk_seq from KHUVUC WHERE VUNG_FK='"+this.vungId+"' ))";
		}


		if (khId.length()>0)
		{
			condition_CK+= " and khachhang_fk='"+this.khId+"' ";
		}

		if (tuNgay.length()>0)
		{
			condition_CK+= " and a.NgayXuatHD >='"+this.tuNgay+"' ";
		}

		if (denNgay.length()>0)
		{
			condition_CK+= " and a.NgayXuatHD <='"+this.denNgay+"' ";
		}

		if (spId.length()>0)
		{
			condition_CK+= " and a.pk_seq in  (select hoadon_fk from HOADON_SP where sanpham_fk='"+spId+"'  ) ";
		}

		if (nhomId.length()>0)
		{
			condition_CK+= " and a.pk_seq in  (select hoadon_fk from HOADON_SP where sanpham_fk in   (select SANPHAM_FK FROM NHOMHANG_SANPHAM  where NHOMHANG_FK='"+this.nhomId+"' )   )  ";
		}

		if (this.kbhId.length()>0)
		{
			condition_CK+=" and a.pk_seq in (select hoadon_fk from HoaDon_Ddh where ddh_fk in (select pk_seq from DonHang where kbh_fk='"+this.kbhId+"' )) ";
		}
		if (this.cndt.equals("0"))
			condition_CK +=" AND a.npp_fk  in (select pk_Seq from nhaphanphoi where loaiNPP not in (4,5) and loainpp is not null ) ";


		String conditionNK="";
		/*if (this.laynk.equals("1") && this.nhomId.length()==0)
		{
			conditionNK+=" and isnull(b.isnhapkhau,1)=1";

		}*/

		String conditionNKE="";
		//System.out.println("this.action : "+ this.action);
		query="";
		if (this.action.length()>0)
		{
			query =
				"\n select maFAST,MaHD,donvi,diachikh, PK_SEQ,TEN,ddkdTEN,spMa,spTen,spDonVi, SoLuong, DonGia, "+
				"\n ( ISNULL([OTC],0) + ISNULL([ETC],0) ) AS DOANHSO, ( ISNULL([OTC],0) + ISNULL([ETC],0) + ISNULL([OTC_TRA],0) ) DOANHSOTRUTRALAI, STT "+ 
				"\n from "+
				"\n ( "+
				"\n select maFAST,MaHD,donvi,diachikh, PK_SEQ,TEN,ddkdTEN,spMa,spTen,spDonVi,SUM(SoLuong)as SoLuong,AVG(DonGia) as DonGia,SUM(AVAT)as AVAT,SUM(BVAT)as BVAT,SUM(VAT)as VAT,STT, LoaiHD "+ 
				"\n from"+ 
				"\n ("+ 
				"\n		select maFAST,MaHD,donvi, diachikh, PK_SEQ,TEN,spMa,spTen,spDonVi,SUM(SoLuong)as SoLuong,AVG(DonGia) as DonGia,SUM(AVAT)as AVAT,SUM(BVAT)as BVAT,SUM(VAT)as VAT, STT "+ 
				"\n		,( select top(1) aa.TEN from DAIDIENKINHDOANH aa inner join TUYENBANHANG bb on bb.DDKD_FK = aa.PK_SEQ "+ 
				"\n		inner join KHACHHANG_TUYENBH cc on cc.TBH_FK = bb.PK_SEQ "+ 
				"\n		where cc.KHACHHANG_FK = HD.KHACHHANG_FK "+ 
				"\n		) as ddkdTEN, LoaiHD "+ 
				"\n		from "+ 
				"\n		( "+ 
				"\n			select kh.maFAST,kh.MaHD,kh.TEN as donvi, isnull(kh.diachi, '') diachikh, npp.PK_SEQ,npp.TEN,sp.ma as spMa,sp.TEN as spTen,spDonVi, "+ 
				"\n			'OTC' as LoaiHD, hdOTC.SoLuong,hdOTC.DonGia ,hdOTC.BVAT, hdOTC.VAT ,hdOTC.AVAT  ,0 as STT ,KHACHHANG_FK "+ 
				"\n			from "+ 
				"\n			( "+ 
				"\n				select a.NPP_FK,b.sanpham_fk,  "+ 
				"\n				sum(round(b.SoLuong*b.DONGIA,0)) as BVAT,  "+ 
				"\n			 	sum( round(  round( b.SoLuong*b.DONGIA,0) *(1+ b.vat/100),0) ) as AVAT , "+ 
				"\n			 	sum( round(  round(b.SoLuong*b.DONGIA,0)*(b.vat/100),0 ) ) as VAT,    "+ 
				"\n				SUM(b.SoLuong)as SoLuong,AVG(b.DonGia) as DonGia ,  b.DONVITINH   as spDonVi  ,a.KHACHHANG_FK"+ 
				"\n				from HOADON a inner join HOADON_SP b on b.HOADON_FK=a.PK_SEQ                       "+ 
				"\n				where a.TRANGTHAI not in  (1,3,5) and a.LOAIHOADON ='0' and a.LOAIHOADON ='0'  "+condition+conditionNK+"  "+ 
				"\n				group by  b.sanpham_fk ,b.DONVITINH ,a.KHACHHANG_FK,a.NPP_FK "+ 
				"\n		 	) as hdOTC "+ 
				"\n			inner join sanpham sp on sp.pk_Seq=hdOTC.sanpham_fk    "+
				"\n 		inner join NHAPHANPHOI npp on npp.PK_SEQ=hdOTC.NPP_FK   "+
				"\n 		left join KHACHHANG kh on kh.PK_SEQ=hdOTC.KHACHHANG_FK  " +
				"\n			where 1=1 "+ condition2019 +
				"\n			UNION ALL   "+ 
				"\n			SELECT KH.MAFAST,KH.MAHD,KH.TEN AS DONVI, ISNULL(KH.DIACHI, '') DIACHIKH, NPP.PK_SEQ,NPP.TEN,SP.MA AS SPMA,SP.TEN AS SPTEN,SPDONVI,   "+  
				"\n			'OTC_TRA' AS LOAIHD, HDOTC.SOLUONG,HDOTC.DONGIA ,HDOTC.BVAT, HDOTC.VAT ,HDOTC.AVAT  ,0 AS STT ,KHACHHANG_FK "+
				"\n			FROM "+
				"\n			( "+
				"\n				SELECT A.NPP_FK,B.SANPHAM_FK, "+
				"\n				(-1)*SUM(ROUND(B.SOLUONG*B.DONGIA,0)) AS BVAT, "+
				"\n				(-1)*SUM( ROUND(  ROUND( B.SOLUONG*B.DONGIA,0) *(1+ B.VAT/100),0) ) AS AVAT,  "+
				"\n				(-1)*SUM( ROUND(  ROUND(B.SOLUONG*B.DONGIA,0)*(B.VAT/100),0 ) ) AS VAT, "+
				"\n				(-1)*SUM(B.SOLUONG)AS SOLUONG,AVG(B.DONGIA) AS DONGIA , '' AS SPDONVI  ,A.KHACHHANG_FK "+
				"\n				FROM ERP_HANGTRALAINPP A INNER JOIN ERP_HANGTRALAINPP_SANPHAM B ON B.HANGTRALAI_FK = A.PK_SEQ "+                      
				"\n				WHERE A.TRANGTHAI = '1' "+ condition_tra +"  "+ 
				"\n				GROUP BY B.SANPHAM_FK, A.KHACHHANG_FK, A.NPP_FK "+
				"\n			) AS HDOTC "+
				"\n			INNER JOIN SANPHAM SP ON SP.PK_SEQ=HDOTC.SANPHAM_FK "+
				"\n			INNER JOIN NHAPHANPHOI NPP ON NPP.PK_SEQ=HDOTC.NPP_FK "+
				"\n			LEFT JOIN KHACHHANG KH ON KH.PK_SEQ=HDOTC.KHACHHANG_FK "+
				"\n			where 1=1 "+
				
				"\n			UNION ALL "+
				"\n			select ETC.maFAST,ETC.MaHD,ETC.donvi, etc.diachikh, PK_SEQ,TEN,spMa,spTen,spDonVi, 'ETC' as LoaiHD,SUM(ETC.SoLuong)as SoLuong,AVG(ETC.DonGia) as DonGia,  "+ 
				"\n			sum(ROUND(ETC.SoLuong*ETC.DONGIA,0))-sum(ROUND(ETC.ChietKhau,0)) as BVAT, "+ 
				"\n			sum(round( ROUND(ETC.SoLuong*ETC.DONGIA,0)*(ETC.thuexuat/100),0 ))-sum( round(ETC.ChietKhau,0)*(ETC.thuexuat/100))as VAT ,  "+ 
				"\n			sum(round( ROUND(ETC.SoLuong*ETC.DONGIA,0)*(1+ETC.thuexuat/100),0 ))-sum( round(ETC.ChietKhau,0)*(1+ETC.thuexuat/100))  AS AVAT,0 as STT ,KHACHHANG_FK"+ 
				"\n			from "+ 
				"\n			( "+ 
				"\n				select  kh.maFAST,kh.MaHD,kh.TEN as donvi, isnull(kh.diachi, '') diachikh, npp.PK_SEQ,npp.TEN,c.sanpham_fk,a.KHACHHANG_FK,    "+ 
				"\n				( "+ 
				"\n					select bb.DDKD_FK    "+ 
				"\n					from ERP_HOADONNPP_DDH aa inner join ERP_DONDATHANGNPP bb on bb.PK_SEQ=aa.DDH_FK    "+ 
				"\n					where aa.HOADONNPP_FK=a.PK_SEQ   "+ 
				"\n				) as ddkd_fk,c.HOADON_FK,a.npp_fk,   "+ 
				"\n				case when c.donvitinh = e.donvi then c.soluong          "+ 
				"\n				else c.soluong * ( select SOLUONG1 / SOLUONG2 from QUYCACH where sanpham_fk = c.sanpham_fk and DVDL2_FK = ( select pk_seq from DONVIDOLUONG where donvi = c.donvitinh ) and DVDL1_FK = d.DVDL_FK )   end  as soluong,       "+ 
				"\n				case when c.donvitinh = e.donvi then c.dongia          "+ 
				"\n				else c.dongia * ( select SOLUONG2 / SOLUONG1 from QUYCACH where sanpham_fk = c.sanpham_fk and DVDL2_FK = ( select pk_seq from DONVIDOLUONG where donvi = c.donvitinh ) and DVDL1_FK = d.DVDL_FK )   end  as dongia,        "+ 
				"\n				c.vat as thuexuat , round(isnull(c.CHIETKHAU,0),0) as CHIETKHAU ,d.MA as spMa,d.TEN as spTen,e.DONVI as spDonVi      "+ 
				"\n			from ERP_HOADONNPP a            "+ 
				"\n			inner join ERP_HOADONNPP_SP c on a.pk_seq = c.hoadon_fk           "+ 
				"\n			inner join SANPHAM d on c.sanpham_fk = d.pk_seq       "+ 
				"\n			inner join DONVIDOLUONG e on d.DVDL_FK = e.pk_seq       "+ 
				"\n 		inner join NHAPHANPHOI npp on npp.PK_SEQ=a.NPP_FK "+ 
				"\n 		left join KHACHHANG kh on kh.PK_SEQ=a.KHACHHANG_FK "+
				"\n			where 1=1  and a.trangthai not in ( 1 , 3, 5 )  "+ 
				"\n			and isnull(a.LOAIHOADON,0)  ='0' and a.KHACHHANG_FK is not null "+conditionETC + conditionNKE + condition2019 +"  "+ 
				"\n		)  ETC "+ 
				"\n		where soluong>0    "+ 
				"\n		group by  ETC.sanpham_fk  ,spMa,spTen,spDonVi  ,KHACHHANG_FK,PK_SEQ,TEN,ETC.maFAST,ETC.MaHD,ETC.donvi, etc.diachikh "; 

			if (this.spId.length()<=0 && this.nhomId.length()==0)
			{
				query+="\n UNION ALL  "+ 
				"\n		select kh.maFAST,kh.MaHD,kh.TEN as donvi, isnull(kh.DIACHI,'') diachikh, npp.PK_SEQ,npp.TEN,b.diengiai as spMa,c.Ten as spTen,'' as spDonVi,'OTC' as Loai,0 as SoLuong,0 as DonGia  "+ 
				"\n		,-1*SUM(ROUND(b.chietkhau,0)) as BVAT,(-1)*SUM(ROUND(    ROUND(b.chietkhau,0)*b.thuevat/100.0,0 )) as VAT,  "+ 
				"\n		(-1)*SUM(ROUND(  ROUND( b.chietkhau,0)*(1+ b.thuevat/100.0),0 ,0 )) as AVAT  ,1 as STT,a.KHACHHANG_FK          "+ 
				"\n		from HOADON a left join HOADON_CHIETKHAU b on b.hoadon_fk=a.PK_SEQ  "+ 
				"\n		inner join LoaiCK c on c.Ma=b.diengiai "+ 
				"\n     inner join NHAPHANPHOI npp on npp.PK_SEQ=a.NPP_FK"+
				"\n  	left join KHACHHANG kh on kh.PK_SEQ=a.KHACHHANG_FK "+
				"\n		where  isnull(b.HienThi,0)=1 "+ 
				"\n		and a.TRANGTHAI not IN (1,3,5) and isnull(a.LOAIHOADON,0)  ='0'  "+ 
				"\n		"+condition_CK + condition2019 + "  "+ 
				"\n		group by b.diengiai,c.Ten  ,a.KHACHHANG_FK,npp.PK_SEQ,npp.TEN,kh.maFAST,kh.MaHD,kh.TEN, kh.DIACHI ";
			}
			query+=
				"\n	) as HD   "+ 
				"\n	group by STT,spMa,spTen,spDonVi,STT  ,KHACHHANG_FK,PK_SEQ,TEN,donvi,maFAST,MaHD, diachikh, LoaiHD "+ 
				"\n  )as hd "+ 
				"\n  group by spMa,spTen,spDonVi,ddkdTEN,STT,PK_SEQ,TEN,donvi,maFAST,MaHD, diachikh, LoaiHD " +
				"\n ) P PIVOT ( SUM(BVAT) FOR LOAIHD IN ( [OTC], [ETC], [OTC_TRA] ) ) AS TONG ";
			setTotal_Query(query);
			System.out.println("[BcDoanhThuSanPham.java]"+query);
			this.queryHd=query+" order by ten,ddkdTen DESC ,STT ,spMa asc ";
		}
		this.hoadonRs=this.createSplittingData(100, super.getSplittings(), "ddkdTen DESC ,STT ,spMa asc ", query);
		createRs();
	}

	String view,msg;
	public String getMsg()
	{
		return msg;
	}
	public void setMsg(String msg)
	{
		this.msg = msg;
	}
	public String getView()
	{
		return view;
	}
	public void setView(String view)
	{
		this.view = view;
	}

	String kbhId;
	ResultSet kbhRs;
	public String getKbhId()
	{
		return kbhId;
	}
	public void setKbhId(String kbhId)
	{
		this.kbhId = kbhId;
	}
	public ResultSet getKbhRs()
	{
		return kbhRs;
	}
	public void setKbhRs(ResultSet kbhRs)
	{
		this.kbhRs = kbhRs;
	}

	public void setTotal_Query(String searchquery) 
	{
		String query;
		query="";
		if (this.action.length()>0)
		{
			query=
				" select SUM(SoLuong)as SoLuong,AVG(DonGia) as DonGia, SUM(DOANHSO)as DOANHSO, SUM(DOANHSOTRUTRALAI)as DOANHSOTRUTRALAI \n"+
				/*"select SUM(SoLuong)as SoLuong,AVG(DonGia) as DonGia,SUM(AVAT)as AVAT,SUM(BVAT)as BVAT,SUM(VAT)as VAT  \n"+*/   
				" from  ("+searchquery+")   as HD   \n";
			this.totalRs= db.get(query);		
			/*System.out.println("---QUYRY TOTAL: " + query);*/
			this.totalRs=this.db.get(query);
		}	 	
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

	String vungId,nhomId,ttId;
	ResultSet vungRs,nhomRs,ttRs;
	public String getVungId()
	{
		return vungId;
	}
	public void setVungId(String vungId)
	{
		this.vungId = vungId;
	}
	public String getNhomId()
	{
		return nhomId;
	}
	public void setNhomId(String nhomId)
	{
		this.nhomId = nhomId;
	}
	public String getTtId()
	{
		return ttId;
	}
	public void setTtId(String ttId)
	{
		this.ttId = ttId;
	}
	public ResultSet getVungRs()
	{
		return vungRs;
	}
	public void setVungRs(ResultSet vungRs)
	{
		this.vungRs = vungRs;
	}
	public ResultSet getNhomRs()
	{
		return nhomRs;
	}
	public void setNhomRs(ResultSet nhomRs)
	{
		this.nhomRs = nhomRs;
	}
	public ResultSet getTtRs()
	{
		return ttRs;
	}
	public void setTtRs(ResultSet ttRs)
	{
		this.ttRs = ttRs;
	}

	ResultSet nppRs;
	@Override
	public ResultSet getNppRs()
	{
		// TODO Auto-generated method stub
		return nppRs;
	}
	@Override
	public void setNppRs(ResultSet nppRs)
	{
		this.nppRs=nppRs;

	}

	String loaiHoaDon;
	public String getLoaiHoaDon()
	{
		return loaiHoaDon;
	}
	public void setLoaiHoaDon(String loaiHoaDon)
	{
		this.loaiHoaDon = loaiHoaDon;
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

	String cndt,kh;
	private String nhomhangId;
	private ResultSet nhomhangRs;

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
	@Override
	public String getNhomhangId() {
		return this.nhomhangId;
	}
	@Override
	public void setNhomhangId(String value) {
		this.nhomhangId = value;
	}
	@Override
	public ResultSet getNhomhangRs() {
		return nhomhangRs;
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

	public void createTpRS()
	{  	
		this.tp = this.db.get("select ten as tpTen, pk_seq as tpId from tinhthanh order by ten");
	}

	public void createQhRS()
	{  	

		if (this.tpId != null && this.tpId.length() > 0){

			this.qh = this.db.get("select ten as qhTen, pk_seq as qhId from quanhuyen where tinhthanh_fk='"+ this.tpId +"' order by ten");
		}
		else
			this.qh = this.db.get("select ten as qhTen, pk_seq as qhId from quanhuyen order by ten");
	}

	public void createPxRS()
	{ 
		String querypx = "";
		if (this.qhId != null && this.qhId.length() > 0){
			querypx = "\n select pk_Seq, ten from phuongxa ";
			querypx += "\n where quanhuyen_fk = '"+qhId+"'";
		}
		this.phuongxaRs =this.db.get(querypx);
	}

	public ResultSet getPhuongxaRs() {
		return phuongxaRs;
	}

	public String getPhuongxa() {
		return this.phuongxa;
	}


	public void setPhuongxa(String value) {
		this.phuongxa = value;
	}
}
