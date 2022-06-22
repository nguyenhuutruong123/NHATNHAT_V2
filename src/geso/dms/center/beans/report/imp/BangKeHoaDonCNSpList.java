package geso.dms.center.beans.report.imp;

import geso.dms.center.util.Phan_Trang;
import geso.dms.center.util.Utility;
import geso.dms.center.beans.report.IBangKeHoaDonCNSpList;
import geso.dms.distributor.db.sql.dbutils;

import java.io.Serializable;
import java.sql.ResultSet;

public class BangKeHoaDonCNSpList extends Phan_Trang implements IBangKeHoaDonCNSpList, Serializable
{
	public BangKeHoaDonCNSpList()
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
		this.laynk="0";
		db = new dbutils();
	}

  private static final long serialVersionUID = 1L;
	String tuNgay,denNgay,spId,nppId,ddkdId,userId,khId;
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
	String laynk="";
	
	public String getLaynk() {
		return laynk;
	}
	public void setLaynk(String laynk) {
		this.laynk = laynk;
	}
	public void closeDB()
	{
		
	}
	
	dbutils db = new dbutils();
	public void createRs()
	{
		
		Utility util = new Utility();
		
		String query="select pk_Seq,ma + '-' + ten as Ten from sanpham where trangthai=1 ";
		
		if(this.nhomId.length()>0)
		{
			query += " and pk_Seq in (select sanpham_fk from nhomhang_sanpham where nhomhang_fk='"+this.nhomId+"' ) ";
		}
		query += "  order by ma  ";
		
		this.spRs = this.db.get(query);
		
		query = "select pk_seq, manhanvien,ten from DaiDienKinhDoanh a where 1=1  ";
		if(this.view.length()>0)
		{
			query += " and pk_seq in  " + util.Quyen_Ddkd(userId) ;
		}
		
		if(this.nppId.length() > 0)
			query += " and a.pk_seq in ( select ddkd_fk from daidienkinhdoanh_npp where npp_fk = '" + nppId + "')  ";
		this.ddkdRs = this.db.get(query);
	
		if(this.nppId.length()>0)
		{
			query="select pk_seq,isnull(mafast,'') +' ' + ten + ' ' + isnull(diachi,'') as Ten from khachhang where 1=1 ";
			
			if(this.view.length()>0)
			{
				query+=" and npp_fk in "+util.quyen_npp(userId);
			}
			
			if(this.nppId.length()>0)
			query+=" and npp_fk='"+this.nppId+"' ";
			
			this.khRs= this.db.get(query);
		}
		
		
	
	
		
		query="select pk_Seq,ten,DIENGIAI from KENHBANHANG where TRANGTHAI=1 ";
		this.kbhRs = this.db.get(query);	
		
		query="select pk_seq,ten from tinhthanh where 1=1 ";
		
		if(this.vungId.length()>0)
		{
			query+=" and vung_fk='"+this.vungId+"' ";
		}
		this.ttRs=this.db.get(query);
		
		query="select pk_seq,ten from vung where 1=1 ";
		query+=" and pk_Seq in  "+util.Quyen_Vung(userId)+" ";
		
		this.vungRs=this.db.get(query);
		
		
		query="select pk_Seq,ten from NhomHang ";
		this.nhomRs=this.db.get(query);
		
		
		query="select pk_Seq,ten,diachi from Nhaphanphoi where trangthai=1 and iskhachhang=0  ";
	
		
		if(this.view.length()>0)
		{
			query+="and pk_Seq in "+util.quyen_npp(userId)+"" ;
		}
		
		
		if(this.ttId.length()>0)
		query+=" and tinhthanh_Fk='"+this.ttId+"' ";
		
		if(this.vungId.length()>0)
			query+=" and khuvuc_fk in (select pk_seq from khuvuc where vung_fk='"+this.vungId+"' ) ";
		
		System.out.println("_NPP_"+query);
		
		this.nppRs=this.db.get(query);
		
		
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
	
	Utility Ult = new Utility();
	public void init(String search)
	{
		String query;
		

		String condition="";   
		
		if(this.loaiHoaDon.length()<=0)
		{
			condition += "and a.LOAIHOADON =0"; 
		}
		else 
		{
			condition += "and a.LOAIHOADON ='"+this.loaiHoaDon+"' ";
		}
	   
		if(this.tuNgay.length()>0)
		{
			condition+=" and a.NgayXuatHD>='"+this.tuNgay+"'";
		}
		if(this.denNgay.length()>0)
		{
			condition+=" and a.NgayXuatHD <='"+this.denNgay+"'";
		}
		if(this.nppId.length()>0)
		{
			condition+=" and a.npp_fk ='"+this.nppId+"'";
		}
		 if(vungId.length()>0)
	   {
			 condition+= " and a.npp_fk in (select pk_Seq  from nhaphanphoi  where khuvuc_fk in (select pk_seq from KHUVUC WHERE VUNG_FK='"+this.vungId+"' ))";
	   }
		
		if(this.spId.length()>0)
		{
			condition+=" and b.sanpham_fk ='"+this.spId+"'";
		}
		if(this.khId.length()>0)
		{
			condition+=" and a.khachhang_Fk ='"+this.khId+"'";
		}
		
		if(this.ttId.length()>0)
		{
			condition+=" and a.khachHang_fk in (select pk_seq from khachhang  where tinhthanh_fk='"+this.ttId+"' ) ";
		}
		
		String conditionNH="";
		if(this.nhomId.length()>0)
		{
			conditionNH+=" and sp.pk_seq in  (select SANPHAM_FK FROM NHOMHANG_SANPHAM  where NhomHang_fk='"+this.nhomId+"' )  ";
		}
		
		if(this.kbhId.length()>0)
		{
			condition+=" and a.pk_seq in (select hoadon_fk from hoadon_Ddh where ddh_fk in (select pk_seq from DonHang where kbh_fk='"+this.kbhId+"')) ";
		}
		
		String conditionHO="";   
		if(this.loaiHoaDon.length()<=0)
		{
			conditionHO += "and isnull(a.LOAIHOADON,0) =0"; 
		}
		else 
		{
			conditionHO += "and isnull(a.LOAIHOADON,0) ='"+this.loaiHoaDon+"' ";
		}
		
		if(this.tuNgay.length()>0)
		{
			conditionHO+=" and a.NgayXuatHD>='"+this.tuNgay+"'";
		}
		if(this.denNgay.length()>0)
		{
			conditionHO+=" and a.NgayXuatHD <='"+this.denNgay+"'";
		}
		if(this.nppId.length()>0)
		{
			conditionHO+=" and a.npp_fk ='"+this.nppId+"'";
		}
		 if(vungId.length()>0)
	   {
			 conditionHO+= "  and a.npp_fk in (select pk_Seq  from nhaphanphoi  where khuvuc_fk in (select pk_seq from KHUVUC WHERE VUNG_FK='"+this.vungId+"' ))";
	   }
		
		if(this.spId.length()>0)
		{
			conditionHO+=" and b.sanpham_fk ='"+this.spId+"'";
		}
		if(this.khId.length()>0)
		{
			conditionHO+=" and a.khachhang_Fk ='"+this.khId+"'";
		}
		
		if(this.ttId.length()>0)
		{
			conditionHO+=" and a.npp_fk in (select pk_seq	 from nhaphanphoi  where tinhthanh_fk='"+this.ttId+"' ) ";
		}
		String conditionHONH="";
		if(this.nhomId.length()>0)
		{
			conditionHONH+=" and sp.pk_seq in  (select SANPHAM_FK FROM NHOMHANG_SANPHAM  where NhomHang_fk='"+this.nhomId+"' )  ";
		}
		
		if(this.kbhId.length()>0)
		{
			conditionHO+=" and a.pk_seq in (select hoadon_fk from erp_hoadon_Ddh where ddh_fk in (select pk_seq from erp_dondathang where kbh_fk='"+this.kbhId+"')) ";
		}
		
	
		
		String conditionETC="";   
		
		if(this.loaiHoaDon.length()<=0)
		{
			conditionETC+= "and isnull(a.LOAIHOADON,0) =0"; 
		}
		else 
		{
			conditionETC+= "and isnull(a.LOAIHOADON,0)  ='"+this.loaiHoaDon+"' ";
		}
		
		if(this.tuNgay.length()>0)
		{
			conditionETC+=" and a.NgayXuatHD>='"+this.tuNgay+"'";
		}
		if(this.denNgay.length()>0)
		{
			conditionETC+=" and a.NgayXuatHD <='"+this.denNgay+"'";
		}
		if(this.nppId.length()>0)
		{
			conditionETC+=" and a.npp_fk ='"+this.nppId+"'";
		}
		if(vungId.length()>0)
	    {
			conditionETC+= "  and a.npp_fk in (select pk_Seq  from nhaphanphoi  where khuvuc_fk in (select pk_seq from KHUVUC WHERE VUNG_FK='"+this.vungId+"' ))";
	    }
		
		if(this.spId.length()>0)
		{
			conditionETC+=" and C.sanpham_fk ='"+this.spId+"'";
		}
		if(this.khId.length()>0)
		{
			conditionETC+=" and a.pk_seq in (select hoadonnpp_fk from erp_hoadonnpp_Ddh where ddh_fk in (select pk_seq from ErpDonDatHangNpp where kbh_fk='"+this.kbhId+"')) ";
		}
		
		if(this.ttId.length()>0)
		{
			conditionETC+=	" and	(  a.khachHang_FK  in (select pk_seq	 from KhachHang  where tinhthanh_fk='"+this.ttId+"' ) OR  a.npp_dat_Fk in (select pk_seq from nhaphanphoi  where tinhthanh_fk='"+this.ttId+"' )  ) ";
		}
		
		if(this.nhomId.length()>0)
		{
			conditionETC+=" and C.SanPham_fk in  (select SANPHAM_FK FROM NHOMHANG_SANPHAM  where NHOMHANG_FK='"+this.nhomId+"' )  ";
		}
		
		
		if(this.kbhId.length()>0)
		{
			conditionETC+=" and a.pk_seq in (select hoadonnpp_fk from Erp_hoadonnpp_Ddh where ddh_fk in (select pk_seq from Erp_DonDatHangNpp where kbh_fk='"+this.kbhId+"' )) ";
		}
		
		
		String condition_CK="";
		

		if(this.loaiHoaDon.length()<=0)
		{
			condition_CK+= "and isnull(a.LOAIHOADON,0) =0"; 
		}
		else 
		{
			condition_CK+= "and isnull(a.LOAIHOADON,0)  ='"+this.loaiHoaDon+"' ";
		}
		
		
	  if(nppId.length()>0)
	   {
	  	 condition_CK+= " and npp_fk='"+this.nppId+"' ";
	   }
	   
	  if(ttId.length()>0)
	   {
	  	 condition_CK+= "  and a.khachHang_fk in (select pk_seq from khachhang  where tinhthanh_fk='"+this.ttId+"' )";
	   }
	  
	  if(vungId.length()>0)
	   {
	  	 condition_CK+= "  and a.npp_fk in (select pk_Seq  from nhaphanphoi  where khuvuc_fk in (select pk_seq from KHUVUC WHERE VUNG_FK='"+this.vungId+"' ))";
	   }
	  
	  
	   if(khId.length()>0)
	   {
	  	 condition_CK+= " and khachhang_fk='"+this.khId+"' ";
	   }
	   
	   if(tuNgay.length()>0)
	   {
	  	 condition_CK+= " and a.NgayXuatHD >='"+this.tuNgay+"' ";
	   }
	   
	   if(denNgay.length()>0)
	   {
	  	 condition_CK+= " and a.NgayXuatHD <='"+this.denNgay+"' ";
	   }
	   
	   if(spId.length()>0)
	   {
	  	 condition_CK+= " and a.pk_seq in  (select hoadon_fk from HOADON_SP where sanpham_fk='"+spId+"'  ) ";
	   }
	   
	   if(nhomId.length()>0)
	   {
	  	 condition_CK+= " and a.pk_seq in  (select hoadon_fk from HOADON_SP where sanpham_fk in   (select SANPHAM_FK FROM NHOMHANG_SANPHAM  where NHOMHANG_FK='"+this.nhomId+"' )   )  ";
	   }
	   
	   if(this.kbhId.length()>0)
			{
	  	 condition_CK+=" and a.pk_seq in (select hoadon_fk from HoaDon_Ddh where ddh_fk in (select pk_seq from DonHang where kbh_fk='"+this.kbhId+"' )) ";
			}
	  
	   
	   String condition_TRA="";  
		condition+= " and a.npp_fk in " + Ult.quyen_npp(userId)+"";		 
		if(this.tuNgay.length()>0)
		{
			condition_TRA+=" and a.NGAYTRA>='"+this.tuNgay+"'";
		}
		if(this.denNgay.length()>0)
		{
			condition_TRA+=" and a.NGAYTRA <='"+this.denNgay+"'";
		}
		if(this.nppId.length()>0)
		{
			condition_TRA+=" and a.npp_fk ='"+this.nppId+"'";
		}
		if(vungId.length()>0)
	    { condition_TRA+= "  and a.npp_fk in (select pk_Seq  from nhaphanphoi  where khuvuc_fk in (select pk_seq from KHUVUC WHERE VUNG_FK='"+this.vungId+"' ))"; }
		
		if(this.spId.length()>0)
		{
			condition_TRA+=" and b.sanpham_fk ='"+this.spId+"'";
		}		
		if(this.ttId.length()>0)
		{
			condition_TRA+=" and a.npp_fk in (select pk_seq	 from NhaPhanPhoi  where tinhthanh_fk='"+this.ttId+"' ) ";
		}
		
		if(this.nhomId.length()>0)
		{
			condition_TRA+=" and b.SanPham_fk in  (select SANPHAM_FK FROM NhomHang_SanPham  where NhomHang_FK='"+this.nhomId+"' )  ";
		}
		
		
		if(this.kbhId.length()>0)
		{
			condition_TRA+=" and a.kbh_fk='"+this.kbhId+"' ";
		}
		String conditionnk="";
		//System.out.println("nhom hang id la"+this.nhomId.length());
		String conditionnkE="";
	 	
	 	String hdKM=
	 		 	" union all	select b.sanpham_fk, round(sum(b.SoLuong*b.DONGIA),0) as BVAT,                       \n"+
	 		 	"		round(sum(b.SoLuong*b.DONGIA*(1 + b.vat/100) ),0) as AVAT ,round(sum(b.SoLuong*b.DONGIA*(b.vat/100) ),0) as VAT, \n"+             
	 		 	"		SUM(b.SoLuong)as SoLuong,AVG(b.DonGia) as DonGia,d.DONVI   as spDonVi                     \n"+
	 		 	"	from HOADON a inner join HOADON_CTKM_TRAKM b on b.HOADON_FK=a.PK_SEQ \n"+  
	 		 	"		inner join SANPHAM c on c.PK_SEQ=b.SANPHAM_FK           \n"+
	 		 	"		inner join DONVIDOLUONG d on d.PK_SEQ=c.DVDL_FK         \n"+
	 		 	"	where  a.TRANGTHAI not in  (3,5)     and LOAIHOADON=1 "+condition+" \n"+			   
	 		 	"	group by  b.sanpham_fk ,d.DONVI    ";
	 		  
	 		   
	 		   query="";
	 		   if(this.action.length()>0)
	 		   {
 					query =
					   /*" SELECT STT, tennpp,diaban,vung, spMa,spTen,spDonVi, SOLUONG, DONGIA, ISNULL([OTC],0) AS DOANHSO, ( ISNULL([OTC],0) + ISNULL([TRA],0) ) DOANHSOTRUTRALAI "+  
					   " from  ( "+
					   " select HD.tennpp,HD.diaban,HD.vung,spMa,spTen,spDonVi,SUM(SoLuong)as SoLuong,AVG(DonGia) as DonGia,SUM(AVAT)as AVAT,SUM(BVAT)as BVAT,SUM(VAT)as VAT, LOAIHD, STT  \n"+   
					   " from \n"+*/
					   
					   " SELECT HD.TENNPP,HD.DIABAN,HD.VUNG,SPMA,SPTEN,SPDONVI,SUM(SOLUONG)AS SOLUONG,AVG(DONGIA) AS DONGIA,SUM(BVAT)AS DOANHSO, SUM(BVAT + BVAT_TRA) AS DOANHSOTRUTRALAI, STT "+
					   " FROM ( \n"+   
					   " 	SELECT NPP.TEN AS TENNPP,TT.TEN AS DIABAN,V.TEN AS VUNG, SP.MA AS SPMA,SP.TEN AS SPTEN,SPDONVI, "+ 
					   "	'OTC' AS LOAIHD, HDOTC.SOLUONG, 0 SOLUONG_TRA, HDOTC.DONGIA, HDOTC.BVAT, 0 BVAT_TRA, 0 AS STT "+  
					   "   	from \n"+   
					   "   	( \n"+   
					   "		select a.NPP_FK,a.KHACHHANG_FK,b.sanpham_fk, \n"+
					   "		sum(round(b.SoLuong*b.DONGIA,0)) as BVAT,  \n"+   
					   "		sum( round(  round( b.SoLuong*b.DONGIA,0) *(1+ b.vat/100),0) ) as AVAT , \n"+
					   "		sum( round(  round(b.SoLuong*b.DONGIA,0)*(b.vat/100),0 ) ) as VAT, \n"+
					   "		SUM(b.SoLuong)as SoLuong,AVG(b.DonGia) as DonGia ,  b.DONVITINH as spDonVi \n"+                   
					   "		from HOADON a inner join HOADON_SP b on b.HOADON_FK=a.PK_SEQ \n"+
					   "		where  a.TRANGTHAI not in  (3,5) and a.LOAIHOADON ='0' "+condition+conditionnk+" \n"+     
					   "		group by  b.sanpham_fk ,b.DONVITINH, a.NPP_FK,a.KHACHHANG_FK \n";
				   if(this.loaiHoaDon.equals("1"))
				   query+=hdKM;
				   query+=
					   "   	) as hdOTC \n"+   
					   "   	inner join sanpham sp on sp.pk_Seq=hdOTC.sanpham_fk    \n"+
					   "	inner join NHAPHANPHOI npp on npp.PK_SEQ=hdOTC.NPP_FK \n"+
					   "	inner join KHACHHANG kh on kh.PK_SEQ=hdOTC.KHACHHANG_FK \n"+
					   "	inner join TINHTHANH tt on tt.PK_SEQ=kh.TINHTHANH_FK \n"+
					   "	inner join VUNG v on v.PK_SEQ=tt.VUNG_FK  where sp.trangthai=1  \n"  + conditionNH+" \n"+   
					   "	union all \n"+
					   "	SELECT  NPP.TEN AS TENNPP,TT.TEN AS DIABAN,V.TEN AS VUNG,C.MA AS SPMA,C.TEN AS SPTEN,D.DONVI AS SPDONVI,'TRA' AS LOAIHD, 0 SOLUONG, "+ 
					   "    (-1)* SUM(B.SOLUONG)AS SOLUONG_TRA,  "+
					   "    (-1)* AVG(B.DONGIA) AS DONGIA, 0 BVAT, "+
					   "    (-1)*SUM(ROUND(B.SOLUONG*B.DONGIA,0)) AS BVAT_TRA, 0 AS STT \n"+
					   "	from DONTRAHANG a inner join DONTRAHANG_SP b on b.DONTRAHANG_FK=a.PK_SEQ  \n"+  
					   "	inner join NHAPHANPHOI npp on npp.PK_SEQ=a.NPP_FK \n"+  
					   "	inner join TINHTHANH tt on tt.PK_SEQ=npp.TINHTHANH_FK  \n"+  
					   "	inner join VUNG v on v.PK_SEQ=tt.VUNG_FK 		 \n"+  
					   "	inner join SANPHAM c on c.PK_SEQ=b.SANPHAM_FK \n"+
				       "	inner join donvidoluong d on d.pk_Seq=c.dvdl_Fk \n"+
					   "	where a.TrangThai=2 and c.trangthai=1  "+condition_TRA+conditionnk+" \n"+ 
					   "	group by c.MA,c.TEN,d.DONVI,npp.TEN,tt.TEN,v.TEN  \n";

	 				   /********Lọc 1 sản phẩm thì k quan tâm CK********/
	 				   if(this.spId.length() <= 0 &&  this.nhomId.length() == 0)
	 				   {
					  	 query +=
						   " UNION ALL  \n"+   
						   " SELECT '' AS TENNPP,'' AS DIABAN,'' AS VUNG,A.DIENGIAI AS SPMA,B.TEN AS SPTEN,'' AS SPDONVI,'OTC' AS LOAI,0 AS SOLUONG, 0 SOLUONG_TRA, 0 AS DONGIA "+  
						   " ,-1*SUM(ROUND(A.CHIETKHAU,0)) AS BVAT, 0 BVAT_TRA, 1 AS STTT          \n "+
						   " from HOADON_CHIETKHAU a left join LoaiCK b on b.Ma=a.diengiai  \n"+   
						   " where  isnull(a.HienThi,0)=1 and a.hoadon_fk in   \n"+   
						   " ( select pk_Seq from HOADON a where LOAIHOADON=0 and a.TRANGTHAI not IN (3,5) "+condition_CK+" ) \n"+
						   " and a.chietkhau > 500 \n"+
						   " group by a.diengiai,B.Ten \n";
	 				   }
					   query+=
					   "     \n"+   
					   "   ) as HD \n";
					   query+=
					   "   group by STT,spMa,spTen,spDonVi, HD.tennpp,HD.diaban,HD.vung ";
					  setTotal_Query(query);
					  this.hoadonRs=this.createSplittingData(10000, super.getSplittings(), "stt,spMa asc", query);
					  System.out.println("___sss"+query);
					  this.queryHd=query+" order by STT,spMa,tennpp,vung,diaban asc ";
	 		   }
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
	 		   if(this.action.length()>0)
	 		   {
	 					query=
	 							" select SUM(SoLuong)as SoLuong,AVG(DonGia) as DonGia, SUM(DOANHSO)as DOANHSO,SUM(DOANHSOTRUTRALAI)as DOANHSOTRUTRALAI \n"+
	 							// "select SUM(SoLuong)as SoLuong,AVG(DonGia) as DonGia,SUM(AVAT)as AVAT,SUM(BVAT)as BVAT,SUM(VAT)as VAT  \n"+   
	 						   "   from  ("+searchquery+")   as HD   \n";
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
	public String getAction()
  {
  	return action;
  }
	public void setAction(String timkiem)
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
	public ResultSet getNppRs() {
		// TODO Auto-generated method stub
		return nppRs;
	}
	@Override
	public void setNppRs(ResultSet nppRs) {
		// TODO Auto-generated method stub
		this.nppRs=nppRs;
	}
	@Override
	public String getNhomhangId() {
		// TODO Auto-generated method stub
		return this.nhomhangId;
	}
	@Override
	public void setNhomhangId(String value) {
		// TODO Auto-generated method stub
		this.nhomhangId = value;
	}
	@Override
	public ResultSet getNhomhangRs() {
		// TODO Auto-generated method stub
		return nhomhangRs;
	}
	
	
	
}
