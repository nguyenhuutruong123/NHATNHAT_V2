package geso.dms.center.beans.report.imp;

import java.io.Serializable;
import java.sql.ResultSet;

import geso.dms.center.beans.report.IBcDoanhThuBanHangTTList;
import geso.dms.center.util.Phan_Trang;
import geso.dms.center.util.Utility;
import geso.dms.distributor.db.sql.dbutils;

public class BcDoanhThuBanHangTTList  extends Phan_Trang implements IBcDoanhThuBanHangTTList, Serializable
{
	public BcDoanhThuBanHangTTList()
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
		this.loaiHoaDon="";
		this.action="";
		this.cndt="0";
		this.kh="0";
		db = new dbutils();
	}
	/**
   * 
   */
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
	
	public void closeDB()
	{
		
	}
	
	dbutils db = new dbutils();
	public void createRs()
	{
		Utility util = new Utility();
		String query="select pk_Seq,ma,ten from sanpham where trangthai=1";
		this.spRs=this.db.get(query);
		
		query="select pk_seq,manhanvien,ten from DaiDienKinhDoanh a where 1=1  ";
		
		if(this.nppId.length()>0)
		{
			query+=" and a.pk_seq in (select ddkd_fk from daidienkinhdoanh_npp where npp_fk='"+nppId+"') ";
		}
		if(this.view.length()>0)
		{
			query+=" and pk_Seq in  "+util.Quyen_Ddkd(this.userId)+" ";
		}
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
		
		
	
		query="select pk_Seq,ten from NhomHang ";
		this.nhomRs=this.db.get(query);
		
		query="select pk_Seq,ten,DIENGIAI from KENHBANHANG where TRANGTHAI=1 ";
		this.kbhRs = this.db.get(query);	
		
		
		query="select PK_SEQ,TEN from tinhthanh   where 1=1 ";
		if(vungId.length()>0)
			query+=" and vung_fk='"+vungId+"'";
		if(this.view.length()>0)
			query+=" and pk_Seq in  "+util.Quyen_TinhThanh(this.userId)+" ";
		
		this.ttRs= this.db.get(query);
		
		query="select pk_seq ,ten,VUNG_FK from khuvuc  where 1=1 ";
		if(this.view.length()>0)
			query+=" and pk_Seq in  "+util.Quyen_KhuVuc(this.userId)+" ";
		this.kvRs=this.db.get(query);
		
		query="select pk_seq,ten from vung where 1=1 ";
		if(this.view.length()>0)
			query+=" and pk_Seq in  "+util.Quyen_Vung(this.userId)+" ";
		this.vungRs=this.db.get(query);
		
		
		query="select pk_seq,ma,diachi,ten from nhaphanphoi where trangthai=1  and isnull(isKHACHHANG,0)=0 ";
		if(this.view.length()>0)
		{
			query+="and pk_Seq in "+util.quyen_npp(userId)+"" ;
		}
		/*if(this.cndt.equals("0"))
			query+="and loaiNPP not in (4,5) and loainpp is not null"; 
		*/
		if(this.ttId.length()>0)
		query+=" and tinhthanh_Fk='"+this.ttId+"' ";
		
		if(this.vungId.length()>0)
			query+=" and khuvuc_fk in (select pk_seq from khuvuc where vung_fk='"+this.vungId+"' ) ";		
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
		
		String condition=" and Isnull(a.LoaiHoaDon,0) =0  ";  
		condition+= " and a.npp_fk in " + Ult.quyen_npp(userId)+"";		 
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
			 condition+= "  and a.npp_fk in (select pk_Seq  from nhaphanphoi  where khuvuc_fk in (select pk_seq from KHUVUC WHERE VUNG_FK='"+this.vungId+"' ))";
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
			condition+=" and a.khachHang_fk in (select pk_seq	 from KhachHang  where tinhthanh_fk='"+this.ttId+"' ) ";
		}
		
		if(this.nhomId.length()>0)
		{
			condition+=" and b.SanPham_fk in  (select sanpham_fk FROM nhomhang_sanpham  where nhomhang_fk='"+this.nhomId+"' )  ";
		}
		
		
		if(this.kbhId.length()>0)
		{
			condition+=" and a.pk_seq in (select hoadon_fk from HoaDon_DDH where ddh_fk in (select pk_seq from DonHang where kbh_fk='"+this.kbhId+"' )) ";
		}
		
/*		if(this.cndt.equals("0"))
			condition +=" AND a.npp_fk  in (select pk_Seq from nhaphanphoi where loaiNPP not in (4,5) and loainpp is not null ) ";*/
		
		
		
		String condition_HO=" and Isnull(a.LoaiHoaDon,0) =0  ";   
		condition_HO+= " and a.npp_fk in " + Ult.quyen_npp(userId)+"";	
		if(this.tuNgay.length()>0)
		{
			condition_HO+=" and a.NgayXuatHD>='"+this.tuNgay+"'";
		}
		if(this.denNgay.length()>0)
		{
			condition_HO+=" and a.NgayXuatHD <='"+this.denNgay+"'";
		}
		if(this.nppId.length()>0)
		{
			condition_HO+=" and a.npp_fk ='"+this.nppId+"'";
		}
		 if(vungId.length()>0)
	   {
			 condition_HO+= "  and a.npp_fk in (select pk_Seq  from nhaphanphoi  where khuvuc_fk in (select pk_seq from KHUVUC WHERE VUNG_FK='"+this.vungId+"' ))";
	   }
		
		if(this.spId.length()>0)
		{
			condition_HO+=" and b.sanpham_fk ='"+this.spId+"'";
		}
		if(this.khId.length()>0)
		{
			condition_HO+=" and a.khachhang_Fk ='"+this.khId+"'";
		}
		
		if(this.ttId.length()>0)
		{
			condition_HO+=" and a.npp_fk in (select pk_seq	 from nhaphanphoi  where tinhthanh_fk='"+this.ttId+"' ) ";
		}
		
		if(this.nhomId.length()>0)
		{
			condition_HO+=" and b.SanPham_fk in  (select sanpham_fk FROM nhomhang_sanpham  where nhomhang_fk='"+this.nhomId+"' )  ";
		}
		
		
		if(this.kbhId.length()>0)
		{
			condition_HO+=" and a.pk_seq in (select hoadon_fk from erp_HoaDon_DDH where ddh_fk in (select pk_seq from Erp_DonDatHang where kbh_fk='"+this.kbhId+"' )) ";
		}
		//condition_HO +=" AND a.npp_fk  in (select pk_Seq from nhaphanphoi where loaiNPP in (4,5) and loainpp is not null ) ";
			
		String conditionETC="";   
		conditionETC+= "  and isnull(a.LOAIHOADON,0)  ='0' and a.npp_fk in " + Ult.quyen_npp(userId)+"";	
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
			conditionETC+=" and a.npp_fk ='"+this.nppId+"' ";
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
			conditionETC+=" and a.khachhang_Fk ='"+this.khId+"'";
		}
		

		if(this.ttId.length()>0)
		{
			conditionETC+=
				" and	(  a.khachHang_FK  in (select pk_seq	 from KhachHang  where tinhthanh_fk='"+this.ttId+"' ) OR  a.npp_dat_Fk in (select pk_seq from nhaphanphoi  where tinhthanh_fk='"+this.ttId+"' )  ) ";
		}
		
		
		if(this.nhomId.length()>0)
		{
			conditionETC+=" and C.SanPham_fk in  (select sanpham_fk FROM NhomHang_SanPham  where nhomhang_fk='"+this.nhomId+"' )  ";
		}
		
		if(this.kbhId.length()>0)
		{
			conditionETC+=" and a.pk_seq in (select hoadonnpp_fk from erp_hoadonnpp_Ddh where ddh_fk in (select pk_seq from erp_dondathangnpp where kbh_fk='"+this.kbhId+"' )) ";
		}
		
		/*if(this.cndt.equals("0"))
			conditionETC +=" AND a.npp_fk  in (select pk_Seq from nhaphanphoi where loaiNPP not in (4,5) and loainpp is not null ) ";
		*/
		
		String condition_CK=" and Isnull(a.LoaiHoaDon,0) =0  ";
		condition_CK+= " and a.npp_fk in " + Ult.quyen_npp(userId)+"";	
	  if(nppId.length()>0)
	   {
	  	 condition_CK+= " and a.npp_fk='"+this.nppId+"' ";
	   }
	   
	  if(this.ttId.length()>0)
		{
	  	condition_CK+=" and a.khachHang_fk in (select pk_seq	 from KhachHang  where tinhthanh_fk='"+this.ttId+"' ) ";
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
	  	 condition_CK+= " and a.pk_seq in  (select hoadon_fk from HOADON_SP where sanpham_fk in   (select sanpham_fk FROM NhomHang_SanPham  where nhomhang_fk='"+this.nhomId+"'  )   )  ";
	   }

	   
	 	if(this.kbhId.length()>0)
		{
	 		condition_CK+=" and a.pk_seq in (select hoadon_fk from hoadon_Ddh where ddh_fk in (select pk_seq from DonHang where kbh_fk='"+this.kbhId+"' )) ";
		}
	   
	 /*	if(this.cndt.equals("0"))
	 		condition_CK +=" AND a.npp_fk  in (select pk_Seq from nhaphanphoi where loaiNPP not in (4,5) and loainpp is not null ) ";
	 	*/
	 	
	 	
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
	   {
			 condition_TRA+= "  and a.npp_fk in (select pk_Seq  from nhaphanphoi  where khuvuc_fk in (select pk_seq from KHUVUC WHERE VUNG_FK='"+this.vungId+"' ))";
	   }
		
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
			condition_TRA+=" and b.SanPham_fk in   (select sanpham_fk FROM NhomHang_SanPham  where nhomhang_fk='"+this.nhomId+"' ) ";
		}
		
		
		if(this.kbhId.length()>0)
		{
			condition_TRA+=" and a.kbh_fk='"+this.kbhId+"' ";
		}
		
		/*if(this.cndt.equals("0"))
			condition_TRA +=" AND a.npp_fk  in (select pk_Seq from nhaphanphoi where loaiNPP  in (4,5) and loainpp is not null ) ";
	 	*/
	   /*
	    * 
	    * Lấy HD Chi nhánh cấp 1,Chi nhánh cấp 2,Quầy bán buôn,Quầy TraphacoDMS
	    * 
	    */
	   query="";
	   if(this.action.length()>0)
	   {
				query=
						" SELECT vTen,ttTen,nppTen, SOLUONG, DONGIA, ( ISNULL([1],0) + ISNULL([3],0) ) AS DOANHSO, ( ISNULL([1],0) + ISNULL([3],0) + ISNULL([2],0) ) DOANHSOTRUTRALAI "+  
						" from  ( "+
						" select vTen,ttTen,nppTen,SUM(SoLuong)as SoLuong,AVG(DonGia) as DonGia,SUM(AVAT)as AVAT,SUM(BVAT)as BVAT,SUM(VAT)as VAT, LOAIHD \n "+      
						"   from  \n "+      
						"   ( \n "+      
						"   	select v.TEN as vTen,kv.TEN as kvTen,tt.TEN as ttTen,sp.ma as nppMa,sp.TEN as nppTen ,      \n "+      
						"   	'1' as LoaiHD, hdOTC.SoLuong,hdOTC.DonGia ,hdOTC.BVAT, hdOTC.VAT ,hdOTC.AVAT      \n "+      
						"   	from \n "+      
						"   	( \n "+      
						"   		select a.NPP_FK ,c.TINHTHANH_FK, \n "+      
						"   		sum(round(b.SoLuong*b.DONGIA,0)) as BVAT, \n "+      
						"   		sum( round( round( b.SoLuong*b.DONGIA,0) *(1+ b.vat/100),0) ) as AVAT ,   \n "+      
						"   		sum( round( round(b.SoLuong*b.DONGIA,0)*(b.vat/100),0 ) ) as VAT,  \n "+      
						"   		SUM(b.SoLuong)as SoLuong,AVG(b.DonGia) as DonGia  \n "+      
						"   		from HOADON a inner join HOADON_SP b on b.HOADON_FK=a.PK_SEQ  " +
						"        	inner join KHACHHANG c on c.PK_SEQ=a.KHACHHANG_FK                                                         \n "+      
						"   		where a.LOAIHOADON =0 and a.TRANGTHAI not in (3,5) "+condition+" \n "+      
						"   		group by a.NPP_FK ,c.TINHTHANH_FK \n "+      
						"   	) as hdOTC \n "+      
						"   	inner join NHAPHANPHOI sp on sp.pk_Seq=hdOTC.NPP_FK \n "+      
						"   	left join KHUVUC kv on kv.PK_SEQ=sp.KHUVUC_FK      \n "+      
						"   	left join VUNG v on v.PK_SEQ=kv.VUNG_FK      \n "+      
						"   	left join TINHTHANH tt on tt.PK_SEQ=hdOTC.TINHTHANH_FK      \n ";
						if(this.nhomId.length()==0)
						{
							query+=
								"   UNION ALL      \n "+      
								"	select v.TEN as vTen,kv.TEN as kvTen,tt.TEN as ttTen,npp.MA as nppMa,npp.TEN as nppTen ,'3' as Loai,0 as SoLuong,0 as DonGia ,-1*SUM(round(b.chietkhau,0)) as BVAT,(-1)*SUM( round( round(b.chietkhau,0)*b.thuevat/100.0,0) ) as VAT,  	\n"+      
								"	(-1)*SUM(round(  round(b.chietkhau,0)*(1+ b.thuevat/100.0),0 ) ) as AVAT        \n"+
								"	from HOADON a inner join HOADON_CHIETKHAU b on b.hoadon_fk=a.PK_SEQ  \n"+
								"	inner join NHAPHANPHOI  npp on npp.PK_SEQ=a.NPP_FK  \n"+
								"	inner join KHACHHANG c on c.PK_SEQ=a.KHACHHANG_FK   \n"+
								"   left join TINHTHANH tt on tt.PK_SEQ=c.TINHTHANH_FK      \n "+
								" 	left join KHUVUC kv on kv.PK_SEQ=npp.KHUVUC_FK      \n "+      
								"   left join VUNG v on v.PK_SEQ=kv.VUNG_FK      \n "+      
								"	where b.HienThi=1  and a.TRANGTHAI not IN (3,5)  \n"+   
								"	and Isnull(a.LoaiHoaDon,0) =0 and b.chietkhau>500   "+condition_CK+"  \n"+
								"   group by v.TEN,kv.TEN,tt.TEN,npp.MA,npp.TEN      \n ";    
						}
					query+=" union all 	" +
						" select v.TEN as vTen,kv.TEN as kvTen,tt.TEN as ttTen,sp.ma as nppMa,sp.TEN as nppTen ,      \n "+      
						" '2' as LoaiHD, hdOTC.SoLuong,hdOTC.DonGia ,hdOTC.BVAT, hdOTC.VAT ,hdOTC.AVAT      \n "+      
						" from      \n "+      
						" (      \n "+      
						" 	select a.NPP_FK ,c.TINHTHANH_FK,    \n "+      
						" 	(-1)*sum(round(b.SoLuong*b.DONGIA,0)) as BVAT,      \n "+      
						" 	(-1)*sum( round(  round( b.SoLuong*b.DONGIA,0) *(1+ b.Vat/100),0) ) as AVAT ,   \n "+      
						" 	(-1)*sum( round(  round(b.SoLuong*b.DONGIA,0)*(b.Vat/100),0 ) ) as VAT,      \n "+      
						" 	(-1)* SUM(b.SoLuong)as SoLuong,AVG(b.DonGia) as DonGia      \n "+      
						" 	from erp_hangtralainpp a inner join erp_hangtralainpp_sanpham b on b.hangtralai_FK = a.PK_SEQ " +
						"   inner join  NHAPHANPHOI c on c.pk_Seq=a.npp_fk                                                                  "+      
						" 	where a.TrangThai = 1   "+condition_TRA+"   \n "+      
						"	group by a.NPP_FK ,c.TINHTHANH_FK       \n "+      
						" ) as hdOTC      \n "+      
						" inner join NHAPHANPHOI sp on sp.pk_Seq=hdOTC.NPP_FK      \n "+      
						" left join KHUVUC kv on kv.PK_SEQ=sp.KHUVUC_FK      \n "+      
						" left join VUNG v on v.PK_SEQ=kv.VUNG_FK      \n "+      
						" left join TINHTHANH tt on tt.PK_SEQ=hdOTC.TINHTHANH_FK      \n "+      						
						") as HD      \n "+      
						"   group by vTen,ttTen,nppTen, LOAIHD " +
						") P  PIVOT ( SUM(BVAT) FOR LOAIHD IN ( [1],[2],[3] ) ) AS TONG ";
					this.queryHd=query;
				this.hoadonRs=this.createSplittingData(10000, super.getSplittings(), "vTEN,ttTen,nppTEN asc", query);
	   }
	   System.out.println(":::::::"+query);
		createRs();
		setTotal_Query(this.queryHd);
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
	   /*
	    * 
	    * Lấy HD Chi nhánh cấp 1,Chi nhánh cấp 2,Quầy bán buôn,Quầy TraphacoDMS
	    * 
	    */
	   String query="";
	   if(this.action.length()>0)
	   {
						query=
							 " select SUM(SoLuong)as SoLuong,AVG(DonGia) as DonGia, SUM(DOANHSO)as DOANHSO,SUM(DOANHSOTRUTRALAI)as DOANHSOTRUTRALAI \n"+
							 //" select SUM(SoLuong)as SoLuong,AVG(DonGia) as DonGia,SUM(AVAT)as AVAT,SUM(BVAT)as BVAT,SUM(VAT)as VAT      \n "+      
							 " from ( "+ searchquery +" ) as HD \n ";
	
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
	
	String vungId,nhomId,ttId,kvId;
	ResultSet vungRs,nhomRs,ttRs,kvRs;
	public String getKvId()
  {
  	return kvId;
  }
	public void setKvId(String kvId)
  {
  	this.kvId = kvId;
  }
	public ResultSet getKvRs()
  {
  	return kvRs;
  }
	public void setKvRs(ResultSet kvRs)
  {
  	this.kvRs = kvRs;
  }
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
	public String getAction()
  {
  	return action;
  }
	public void setAction(String timkiem)
  {
  	this.action = timkiem;
  }
	
	String cndt,kh;
	
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
	
	
	
}
