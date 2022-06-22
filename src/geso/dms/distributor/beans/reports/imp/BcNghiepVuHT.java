package geso.dms.distributor.beans.reports.imp;

import geso.dms.center.util.Phan_Trang;
import geso.dms.center.util.Utility;
import geso.dms.distributor.beans.reports.IBcNghiepVuHT;
import geso.dms.distributor.db.sql.dbutils;

import java.io.Serializable;
import java.sql.ResultSet;

public class BcNghiepVuHT extends Phan_Trang implements IBcNghiepVuHT, Serializable
{
	/**
   * 
   */
  private static final long serialVersionUID = -6878643533846192322L;
	public BcNghiepVuHT()
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
		this.khoId="";
		db = new dbutils();
	}

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
			
			query=" select * from kho where trangthai=1";
			this.khoRs=this.db.get(query);
			
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
		
		
		query="select pk_Seq,ten,diachi from Nhaphanphoi where trangthai=1 and iskhachhang=0 ";
		
		
		
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
	
	public void init(String search)
	{
		
		getNppInfo();
		
		String query;
		String condition="";
		
   if(action.length()>0)
   {
				query=
						"declare @nppId numeric(18,0),@kbhId numeric(18,0)   \n "+      
						"   set @nppId=('"+this.nppId+"')   \n "+      
						"   set @kbhId=100025   \n "+      
						"   select  ctId,ngayCT,npp.MA as nppMA,npp.TEN as nppTEN,kbh.TEN as kbhTEN,KHO.TEN as khoTEN   \n "+      
						"   	,sp.MA as spMa,sp.TEN as spTen,dv.DONVI as spDONVI,data.SoLuong,NghiepVu   \n "+      
						"   from    \n "+      
						"   (   \n "+      
						"   	select ctId,NGAYCT,NPP_FK, KBH_FK,KHO_FK,SANPHAM_FK,SUM(SoLuong) as SoLuong,NghiepVu   \n "+      
						"   	from    \n "+      
						"   	(   \n "+      
						"   		select a.PK_SEQ as ctId,a.NGAYNHAP as ngayCT,a.NPP_FK,a.KBH_FK,b.KHO_FK,b.SANPHAM_FK,(b.soluong) as SoLuong,N'Xuất Bán' as NghiepVu   \n "+      
						"   		from DONHANG a inner join DONHANG_SANPHAM b on b.DONHANG_FK=a.PK_SEQ   \n "+      
						"   		where  a.TRANGTHAI=0    \n "+      
						"   		   \n "+      
						"   		union all   \n "+      
						"      \n "+      
						"   		SELECT B.PK_SEQ as ctId,B.NGAYNHAP,B.NPP_FK,B.KBH_FK,KM.KHO_FK,SPKM.PK_SEQ AS SANPHAM_FK,ISNULL((A.SOLUONG),0) AS SOLUONG ,   \n "+      
						"   				N'Xuất hàng KM' AS NghiepVu      \n "+      
						"   		FROM DONHANG_CTKM_TRAKM  A INNER JOIN DONHANG B ON B.PK_SEQ=A.DONHANGID      \n "+      
						"   			INNER JOIN SANPHAM SPKM ON SPKM.MA = A.SPMA  	      \n "+      
						"   			INNER JOIN CTKHUYENMAI KM ON KM.PK_SEQ = A.CTKMID       \n "+      
						"   		WHERE B.TRANGTHAI=0	      \n "+      
						"      \n "+      
						"   		union all   \n "+      
						"   		select ck.ctId,ck.NgayChuyen,ck.npp_fk,KBH_FK,ck.KhoXuat_FK AS kho_fk, ck.sanpham_fk,(soluong) as SoLuong,N'Xuất chuyển nội bộ' as NghiepVu      \n "+      
						"   		from       \n "+      
						"   		(      \n "+      
						"   		select  c.PK_SEQ as ctId,c.NgayChuyen,c.npp_fk, c.kbh_fk, c.khoxuat_fk, a.sanpham_fk,            \n "+      
						"   			case when a.dvdl_fk IS null then a.soluongchuyen             \n "+      
						"   				 when a.dvdl_fk = b.DVDL_FK then a.soluongchuyen            \n "+      
						"   				 else  a.soluongchuyen * ( select SOLUONG1 from QUYCACH where sanpham_fk = a.sanpham_fk and DVDL2_FK = a.dvdl_fk and dvdl1_fk = b.dvdl_fk )   \n "+      
						"   								 / ( select SOLUONG2 from QUYCACH where sanpham_fk = a.sanpham_fk and DVDL2_FK = a.dvdl_fk and dvdl1_fk = b.dvdl_fk )					end as soluong          \n "+      
						"   		from ERP_CHUYENKHONPP_SANPHAM a inner join SANPHAM b on a.sanpham_fk = b.PK_SEQ         \n "+      
						"   		inner join ERP_CHUYENKHONPP c on a.chuyenkho_fk = c.pk_seq         \n "+      
						"   		where c.trangthai=0   and NPP_FK=@nppId   \n "+      
						"   		)as ck   		   \n "+      
						"   		   \n "+      
						"   		union all   \n "+      
						"   		   \n "+      
						"   		select a.PK_SEQ as ctId,a.NgayChuyen,a.NPP_FK,a.KBH_FK,a.KhoXuat_FK, b.sanpham_fk, (b.soluong) as soluong ,N'Chuyển Kho' as NghiepVu   \n "+      
						"    		from ERP_CHUYENKENH a inner join ERP_CHUYENKENH_SANPHAM_CHITIET b on a.pk_seq = b.chuyenkenh_fk    \n "+      
						"    		where a.trangthai=0    \n "+      
						"    		   \n "+      
						"   		   \n "+      
						"   		union all   \n "+      
						"   		   \n "+      
						"   		select a.PK_SEQ as ctId,a.NGAYTRA,a.NPP_FK,a.KBH_FK,a.kho_fk, b.sanpham_fk,(b.soluong) as SoLuong,N'Trả hàng NCC' as NghiepVu   \n "+      
						"   		from DONTRAHANG a inner join DONTRAHANG_SP b on a.pk_seq = b.DONTRAHANG_FK    \n "+      
						"   		where a.trangthai in (0,1) and b.SOLUONG!=0   \n "+      
						"   		   \n "+      
						"   		   \n "+      
						"   		union all   \n "+      
						"   		   \n "+      
						"   		select a.PK_SEQ as ctId,a.NGAYDC,a.NPP_FK,a.kbh_fk,a.kho_fk, b.sanpham_fk,-1*(b.DIEUCHINH) as SoLuong,N'Điều chỉnh tồn kho' as NghiepVu   \n "+      
						"   		from DIEUCHINHTONKHO a inner join DIEUCHINHTONKHO_SP b on a.pk_seq = b.DIEUCHINHTONKHO_FK    \n "+      
						"   		where a.trangthai in (0) and b.DIEUCHINH<0   \n "+      
						"   		   \n "+      
						"   		union all    \n "+      
						"   		   \n "+      
						"   		select  data.ctId,data.NgayDonHang as NgayCT,npp_fk,kbh_fk,khoxuat_fk, sanpham_fk, (soluong) as soluong   ,N'Xuất ETC Đối Tác' as NghiepVu   \n "+      
						"   		from     \n "+      
						"   		(     \n "+      
						"   			select c.PK_SEQ as ctId,c.NgayDonHang,c.kho_fk as khoxuat_fk, c.npp_fk,c.KBH_FK as kbh_fk, a.sanpham_fk,           \n "+      
						"   				case when a.dvdl_fk IS null then a.soluong           \n "+      
						"   					 when a.dvdl_fk = b.DVDL_FK then a.soluong          \n "+      
						"   					 else  a.soluong *    \n "+      
						"   					 ( select SOLUONG1 / SOLUONG2 from QUYCACH where sanpham_fk = a.sanpham_fk and DVDL2_FK = a.dvdl_fk and dvdl1_fk = b.dvdl_fk )     \n "+      
						"   					 end as soluong        \n "+      
						"   			from ERP_DONDATHANGNPP_SANPHAM a inner join SANPHAM b on a.sanpham_fk = b.PK_SEQ       \n "+      
						"   				inner join ERP_DONDATHANGNPP c on a.dondathang_fk = c.pk_seq         \n "+      
						"   			where  a.soluong > 0  and c.trangthai in (0,1) and c.npp_dat_fk is not null   \n "+      
						"   		)as data   \n "+      
						"  union all 		" +
						"select  data.ctId,data.NgayDonHang as NgayCT,npp_fk,kbh_fk,khoxuat_fk, sanpham_fk, (soluong) as soluong   ,N'Xuất ETC' as NghiepVu   \n "+      
						"   		from     \n "+      
						"   		(     \n "+      
						"   			select c.PK_SEQ as ctId,c.NgayDonHang,c.kho_fk as khoxuat_fk, c.npp_fk,c.KBH_FK as kbh_fk, a.sanpham_fk,           \n "+      
						"   				case when a.dvdl_fk IS null then a.soluong           \n "+      
						"   					 when a.dvdl_fk = b.DVDL_FK then a.soluong          \n "+      
						"   					 else  a.soluong *    \n "+      
						"   					 ( select SOLUONG1 / SOLUONG2 from QUYCACH where sanpham_fk = a.sanpham_fk and DVDL2_FK = a.dvdl_fk and dvdl1_fk = b.dvdl_fk )     \n "+      
						"   					 end as soluong        \n "+      
						"   			from ERP_DONDATHANGNPP_SANPHAM a inner join SANPHAM b on a.sanpham_fk = b.PK_SEQ       \n "+      
						"   				inner join ERP_DONDATHANGNPP c on a.dondathang_fk = c.pk_seq         \n "+      
						"   			where  a.soluong > 0  and c.trangthai in (0,1) and c.khachHang_fk is not null   \n "+      
						"   		)as data   \n "+      
						"   		   \n "+      
						"   	union all   \n "+      
						"   		   \n "+      
						"   	select A.PK_SEQ as ctId,a.NgayChuyen,a.TrucThuoc_FK ,a.kbh_fk,a.KhoXuat_FK,b.sanpham_fk,(b.soluongchuyen) as SoLuong,N'Xuất kho khác' as NghiepVu   \n "+      
						"   	from ERP_CHUYENKHO a inner join erp_CHUYENKHO_sANPHAM b on b.chuyenkho_fk=a.pk_Seq        \n "+      
						"   	where a.trangthai=0   and tructhuoc_fk=@nppId   \n "+      
						"   )as total    \n "+      
						"   	group by NPP_FK,KHO_FK,SANPHAM_FK,total.KBH_FK,NghiepVu,ctId,ngayCT   \n "+      
						"   ) as data inner join sanpham sp on sp.pk_Seq=data.sanpham_Fk   \n "+      
						"   inner join DONVIDOLUONG dv on dv.PK_SEQ=sp.DVDL_FK   \n "+      
						"   inner join KENHBANHANG kbh on kbh.PK_SEQ=data.KBH_FK   \n "+      
						"   inner join KHO kho on kho.PK_SEQ=data.KHO_FK   \n "+      
						"   inner join NHAPHANPHOI npp on npp.PK_SEQ=data.NPP_FK   \n "+      
						"   where data.npp_fk=@nppId " ;
						
						if(this.khoId.length()>0)
						{
							query+=" and data.kho_fk='"+this.khoId+"'";
						}
						
						if(this.kbhId.length()>0)
						{
							query+=" and data.kbh_fk='"+this.kbhId+"'";
						}
						
						if(spId.length()>0)
						{
							query+=" and data.sanpham_fk='"+this.spId+"'";
						}
						
						query+=
						"order by NghiepVu,ngayCT desc ";
				
				
					System.out.println("______"+query);
				
	 					this.queryHd=query;
	 					this.hoadonRs=this.db.get(query);
	 		   }
	 		 	setTotal_Query("");
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
		String query = "";				
		this.totalRs=this.db.get(query);
	 	
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
	public String getAction()
  {
  	return action;
  }
	public void setAction(String timkiem)
  {
  	this.action = timkiem;
  }
	
	String[] spNhomId,spNhomTen;
	public String[] getSpNhomId()
  {
  	return spNhomId;
  }
	public void setSpNhomId(String[] spNhomId)
  {
  	this.spNhomId = spNhomId;
  }
	public String[] getSpNhomTen()
  {
  	return spNhomTen;
  }
	public void setSpNhomTen(String[] spNhomTen)
  {
  	this.spNhomTen = spNhomTen;
  }
	
	String khoId;
	ResultSet khoRs;
	public String getKhoId()
  {
  	return khoId;
  }
	public void setKhoId(String khoId)
  {
  	this.khoId = khoId;
  }
	public ResultSet getKhoRs()
  {
  	return khoRs;
  }
	public void setKhoRs(ResultSet khoRs)
  {
  	this.khoRs = khoRs;
  }
	private void getNppInfo()
	{	
		geso.dms.distributor.util.Utility util = new geso.dms.distributor.util.Utility();
		this.nppId = util.getIdNhapp(this.userId);
	}
	
}
