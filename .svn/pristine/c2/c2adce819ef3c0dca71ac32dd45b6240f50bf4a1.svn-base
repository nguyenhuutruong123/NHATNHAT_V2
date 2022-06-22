package geso.dms.center.beans.report.imp;

import geso.dms.center.util.Phan_Trang;
import geso.dms.center.util.Utility;
import geso.dms.center.beans.report.IBangKeHoaDonSpList;
import geso.dms.center.beans.report.IKetQuaBanHangNam;
import geso.dms.distributor.db.sql.dbutils;

import java.io.Serializable;
import java.sql.ResultSet;

public class KetQuaBanHangNam extends Phan_Trang implements IKetQuaBanHangNam, Serializable
{
	public KetQuaBanHangNam()
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
	String tuNgay,denNgay,spId,nppId,ddkdId,userId,khId,year= "";
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
		
		if(this.nppId.length()>0)
		{
			query += " and ncc_fk = '"+this.nppId+"'  ";
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
		
		
		query="select pk_Seq,ten,diachi from dms_nhacungcap  ";
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
		
		if(this.spId.length()>0)
		{
			condition+=" and DHSP.sanpham_fk ='"+this.spId+"'";
		}
		if(this.nppId.length()>0)
		{
			condition+=" and SP.ncc_fk ='"+this.nppId+"'";
		}
		
		if(this.year.length() > 0)
		{
			condition+= " and year(dh.ngaynhap) = "+this.year+" ";
		}
		
		String condition2="";   
		
		if(this.spId.length()>0)
		{
			condition2+=" and DHSP.sanpham_fk ='"+this.spId+"'";
		}
		if(this.nppId.length()>0)
		{
			condition2+=" and SP.ncc_fk ='"+this.nppId+"'";
		}
		
		if(this.year.length() > 0)
		{
			condition2+= " and year(dh.ngaynhap) = ("+this.year+"-1) ";
		}
	 	 query =	" select A.TEN, A.DSHT as DSHT,A.DSHT/(SUM(A.DSHT)*1.0)*100 AS TYTRONGHT,"
				 +"\n B.DSNT,B.DSNT/(SUM(B.DSNT)*1.0)*100 AS TYTRONGNT"
				 +"\n  from "
				 +"\n  (SELECT NCC.PK_SEQ AS NCC,NCC.TEN,SUM(DHSP.SOLUONG*DHSP.GIAMUA) AS DSHT FROM  DONHANG DH inner join DONHANG_SANPHAM DHSP"
				 +"\n ON DH.PK_SEQ = DHSP.DONHANG_FK"
				 +"\n INNER JOIN SANPHAM SP ON SP.PK_SEQ = DHSP.SANPHAM_FK"
				 +"\n INNER JOIN DMS_NHACUNGCAP NCC ON NCC.PK_SEQ = SP.NCC_FK"
				 +"\n WHERE DH.TRANGTHAI <> 2 "+condition
				 +"\n group by NCC.PK_SEQ,NCC.TEN"
				 +"\n ) AS A INNER JOIN"
				 +"\n (SELECT NCC.PK_SEQ AS NCC,NCC.TEN,SUM(DHSP.SOLUONG*DHSP.GIAMUA) AS DSNT FROM DONHANG DH inner join DONHANG_SANPHAM DHSP"
				 +"\n ON DH.PK_SEQ = DHSP.DONHANG_FK"
				 +"\n INNER JOIN SANPHAM SP ON SP.PK_SEQ = DHSP.SANPHAM_FK"
				 +"\n INNER JOIN DMS_NHACUNGCAP NCC ON NCC.PK_SEQ = SP.NCC_FK"
				 +"\n WHERE DH.TRANGTHAI <> 2 "+condition2
				 +"\n group by NCC.PK_SEQ,NCC.TEN"
				 +"\n ) AS B ON A.NCC = B.NCC"
				 +"\n GROUP BY A.TEN,A.DSHT,B.DSNT ";
	 	 this.queryHd = query;
	 		  
	 		   
	 		
	 		 	
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
	 							 "select SUM(SoLuong)as SoLuong,AVG(DonGia) as DonGia,SUM(AVAT)as AVAT,SUM(BVAT)as BVAT,SUM(VAT)as VAT  \n"+   
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
	@Override
	public void setFromYear(String fromyear) {
		this.year = fromyear;
		
	}
	@Override
	public String getFromYear() {
		// TODO Auto-generated method stub
		return this.year;
	}
	
	
}
