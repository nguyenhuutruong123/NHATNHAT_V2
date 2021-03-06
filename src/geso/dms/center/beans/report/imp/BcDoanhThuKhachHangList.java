package geso.dms.center.beans.report.imp;

import java.io.Serializable;
import java.sql.ResultSet;


import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;

import geso.dms.center.beans.report.IBcDoanhThuKhachHangList;
import geso.dms.center.util.IPhanTrang;
import geso.dms.center.util.PhanTrang;
import geso.dms.center.util.Phan_Trang;
import geso.dms.center.util.Utility;
import geso.dms.distributor.beans.donhang.IDonhangList;
import geso.dms.distributor.db.sql.dbutils;

public class BcDoanhThuKhachHangList  extends Phan_Trang implements IBcDoanhThuKhachHangList, Serializable
{
	private int num;
	private int[] listPages;
	private int currentPages;
	/**
	 * 
	 */
	
	String tuNgay,denNgay,spId,nppId,ddkdId,userId,khId, tpId, qhId, phuongxa;
	ResultSet phuongxaRs ;
	ResultSet tp;
	ResultSet qh = null;
	int xemtheo=1; //=1 xem mac dinh theo khach hang, -1 xem theo trinh duoc vien
	
	private static final long serialVersionUID = -3237541992706452258L;
	public BcDoanhThuKhachHangList()
	{
		Calendar cal = Calendar.getInstance();
		int year = cal.get(Calendar.YEAR);
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
		currentPages = 1;
		num = 1;
		this.xemtheo = 1;
		this.flagnpp="0";
		this.laynk="1";
		this.thangbd="1";
		this.nambd=""+year;
		this.thangkt="1";
		this.namkt=""+year;
		this.DSKHT="";
		this.DSKHT_client="";
		this.tpId = "";
		this.qhId = "";
		this.phuongxa = "";
		db = new dbutils();
	}
	
	String DSKHT_client="";
	public String getDSKHT_client() {
		return DSKHT_client;
	}
	public void setDSKHT_client(String dSKHT_client) {
		DSKHT_client = dSKHT_client;
	}
	String thangbd="";

	String thangkt="";
	String nambd="";
	String namkt="";
	String DSKHT="";
	ArrayList<String> arrname=new ArrayList<String>();
	ArrayList<String> arrname_colum=new ArrayList<String>();
	public ArrayList<String> getArrname_colum() {
		return arrname_colum;
	}
	public void setArrname_colum(ArrayList<String> arrname_colum) {
		this.arrname_colum = arrname_colum;
	}
	public ArrayList<String> getArrname() {
		
		return arrname;
	}
	public void setArrname(ArrayList<String> arrname) {
		this.arrname = arrname;
	}
	public String getDSKHT() {
		return DSKHT;
	}
	public void setDSKHT(String dSKHT) {
		DSKHT = dSKHT;
	}
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
	
	String laynk="";
	public String getLaynk() {
		return laynk;
	}
	public void setLaynk(String laynk) {
		this.laynk = laynk;
	}
	String flagnpp;
	
	public String getFlagnpp() {
		return flagnpp;
	}
	public void setFlagnpp(String flagnpp) {
		this.flagnpp = flagnpp;
	}
	
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
		if(this.view.length()>0 && !this.flagnpp.equals("1"))
		{
			query+=" and pk_Seq in  "+util.Quyen_Ddkd(this.userId)+" ";
		}
		this.ddkdRs = this.db.get(query);
		
		System.out.println("nhapp::::::::::::::::::::;;"+ query);
		/*if(this.nppId.length()>0)
		{*/
			query="select pk_seq,isnull(mafast,'') +' ' + ten + ' ' + isnull(diachi,'') as Ten from khachhang where 1=1 ";
			if(this.view.length()>0 && 	!this.flagnpp.equals("1"))
			{
				query+=" and npp_fk in "+util.quyen_npp(userId);
			}
			
			if(this.nppId.length()>0)
				query+=" and npp_fk='"+this.nppId+"' ";
			
			if(this.ddkdId.length()>0)
			{	query+= " and a.khachhang_fk in (select khachhang_fk from KHACHHANG_TUYENBH where tbh_fk in (select pk_Seq from tuyenbanhang where ddkd_Fk ='"+this.ddkdId+"'))"; }
			
			if(this.tpId.length()>0) 	 {	query+=" and tinhthanh_fk = '"+ this.tpId +"' "; }
			if(this.qhId.length()>0) 	 {	query+=" and quanhuyen_fk = '"+ this.qhId +"' "; }
			if(this.phuongxa.length()>0) {	query+=" and phuongxa = '"+ this.phuongxa +"' "; }
			
			System.out.println("query kh " + query);
			this.khRs= this.db.get(query);
			
		/*}*/		
		query="select pk_Seq,ten,DIENGIAI from KENHBANHANG where TRANGTHAI=1 ";
		this.kbhRs = this.db.get(query);	
		
		query="select PK_SEQ,TEN from tinhthanh   where 1=1 ";
		if(vungId.length()>0)
			query+=" and vung_fk='"+vungId+"'";
	if(this.view.length()>0 && !this.DSKHT_client.equals("DSKHT_client"))
			query+=" and pk_Seq in  "+util.Quyen_TinhThanhTheoKhachHang(this.userId)+" ";
		System.out.println("::::::::::::::::: dia ban :"+query);
		this.ttRs= this.db.get(query);
		
		
		query="select pk_seq ,ten,VUNG_FK from khuvuc  where 1=1 ";
		if(this.view.length()>0)
			query+=" and pk_Seq in  "+util.Quyen_KhuVuc(this.userId)+" ";
		this.kvRs=this.db.get(query);
		
		query="select pk_seq,ten from vung where 1=1 ";
		System.out.println("view la "+this.view.length());
		if(this.view.length()>0 && !this.DSKHT_client.equals("DSKHT_client"))
			query+=" and pk_Seq in  "+util.Quyen_Vung(this.userId)+" ";
	
		this.vungRs=this.db.get(query);
		
		System.out.println(":::::::::::::::;;; vung:");
		
		
		query="select pk_seq,ma,diachi,ten from nhaphanphoi where trangthai=1  and isnull(isKHACHHANG,0)=0";
		if(this.flagnpp.equals("1"))
			query="select nhaphanphoi.pk_seq,nhaphanphoi.ma,nhaphanphoi.diachi,nhaphanphoi.ten from nhaphanphoi inner join NHANVIEN on NHAPHANPHOI.SITECODE=NHANVIEN.CONVSITECODE"+
				" where nhaphanphoi.trangthai=1  and isnull(isKHACHHANG,0)=0 and NHANVIEN.pk_seq="+this.userId;
	
		if(this.DSKHT_client.equals("DSKHT_client"))
		{
			query="select nhaphanphoi.pk_seq,nhaphanphoi.ma,nhaphanphoi.diachi,nhaphanphoi.ten from nhaphanphoi inner join NHANVIEN on NHAPHANPHOI.SITECODE=NHANVIEN.CONVSITECODE"+
					" where nhaphanphoi.trangthai=1   and NHANVIEN.pk_seq="+this.userId;
		}
	
		System.out.println("nhaphanp la alalalala "+query);
		System.out.println("DSKHT_client la a a"+DSKHT_client);
		
		if(this.view.length()>0 && !this.flagnpp.equals("1") &&  !this.DSKHT_client.equals("DSKHT_client"))
		{
			query+=" and pk_Seq in "+util.quyen_npp(userId)+"" ;
		}
		
		if(this.ttId.length()>0)
			query+=" and tinhthanh_Fk='"+this.ttId+"' ";
		
		if(this.vungId.length()>0)
			query+=" and khuvuc_fk in (select PK_SEQ from khuvuc where vung_fk='"+this.vungId+"' ) ";
		System.out.println("nhapp::::::::::::::::::::;;"+query);
		this.nppRs=this.db.get(query);
		this.nhomRs=this.db.get("select pk_seq,ten from nhomhang");
		if(this.DSKHT_client.equals("DSKHT_client"))
		this.nppId=util.getIdNhapp(this.userId);
		
		createTpRS();
		createQhRS();
		createPxRS();
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
		
		String condition="", condition_tra="";   
		
		condition+= " and a.npp_fk in " + Ult.quyen_npp(userId)+"";		 
		condition_tra+= " and a.npp_fk in " + Ult.quyen_npp(userId)+"";
		
		if(this.tuNgay.length()>0)
		{
			condition+=" and a.NgayXuatHD>='"+this.tuNgay+"'";
			condition_tra+=" and a.ngaytra>='"+this.tuNgay+"'";
		}
		if(this.denNgay.length()>0)
		{
			condition+=" and a.NgayXuatHD <='"+this.denNgay+"'";
			condition_tra+=" and a.ngaytra <='"+this.denNgay+"'";
		}
		if(this.nppId.length()>0)
		{
			condition+=" and a.npp_fk ='"+this.nppId+"'";
			condition_tra+=" and a.npp_fk ='"+this.nppId+"'";
		}
		
		if(vungId.length()>0)
		{
			condition+= "  and a.npp_fk in (select pk_Seq  from nhaphanphoi  where khuvuc_fk in (select pk_seq from KHUVUC WHERE VUNG_FK='"+this.vungId+"' ))";
			condition_tra+= "  and a.npp_fk in (select pk_Seq  from nhaphanphoi  where khuvuc_fk in (select pk_seq from KHUVUC WHERE VUNG_FK='"+this.vungId+"' ))";
		}
		if(this.spId.length()>0)
		{
			condition+=" and b.sanpham_fk ='"+this.spId+"'";
			condition_tra+=" and b.sanpham_fk ='"+this.spId+"'";
		}
		if(this.khId.length()>0)
		{
			condition+=" and a.khachhang_Fk ='"+this.khId+"'";
			condition_tra+=" and a.khachhang_Fk ='"+this.khId+"'";
		}
		
		if(this.ttId.length()>0)
		{
			condition+=" and a.khachHang_fk in (select pk_seq	 from KhachHang  where tinhthanh_fk='"+this.ttId+"' ) ";
			condition_tra+=" and a.khachHang_fk in (select pk_seq	 from KhachHang  where tinhthanh_fk='"+this.ttId+"' ) ";
			/*condition+=" and a.npp_fk  in (select pk_seq from NHAPHANPHOI  where TINHTHANH_FK='"+this.ttId+"')   ";*/
		}
		
		if(this.nhomId.length()>0)
		{
			condition+=" and b.SanPham_fk in  (select SANPHAM_FK FROM NHOMHANG_SANPHAM  where NhomHang_fk='"+this.nhomId+"' ) ";
			condition_tra+=" and b.SanPham_fk in  (select SANPHAM_FK FROM NHOMHANG_SANPHAM  where NhomHang_fk='"+this.nhomId+"' ) ";
		}
		
		if(this.kbhId.length()>0)
		{
			condition+=" and a.pk_seq in (select hoadon_fk from hoadon_Ddh where ddh_fk in (select pk_seq from DonHang where kbh_fk='"+this.kbhId+"' )) ";
		}
		
		if(this.ddkdId.length()>0)
		{
			condition += " and a.khachhang_fk in (select khachhang_fk from KHACHHANG_TUYENBH where tbh_fk in (select pk_Seq from tuyenbanhang where ddkd_Fk ='"+this.ddkdId+"'))";
			condition_tra += " and a.khachhang_fk in (select khachhang_fk from KHACHHANG_TUYENBH where tbh_fk in (select pk_Seq from tuyenbanhang where ddkd_Fk ='"+this.ddkdId+"'))";
		}
		
		if(this.DSKHT.equals("DSKHT"))
		{

			String ngaythangbd=this.nambd + ( (Integer.parseInt(this.thangbd))<10?"-0"+this.thangbd +"-01": "-"+this.thangbd+"-01");
			String ngaythangkt=this.namkt + ( (Integer.parseInt(this.thangkt)+1)<10?"-0"+(Integer.parseInt(this.thangkt)+1)+ "-01": "-"+(Integer.parseInt(this.thangkt)+1)+"-01");
			condition+=" and a.NgayXuatHD>='"+ngaythangbd+"'";
			condition+=" and a.NgayXuatHD <'"+ngaythangkt+"'";
			
			condition_tra+=" and a.ngaytra >= '"+ngaythangbd+"'";
			condition_tra+=" and a.ngaytra < '"+ngaythangkt+"'";
		}
		
		String conditionETC="";   
		conditionETC+= " and a.npp_fk in " + Ult.quyen_npp(userId)+"";	
		if(this.DSKHT.equals("DSKHT"))
		{
			String ngaythangbd=this.nambd + ( (Integer.parseInt(this.thangbd))<10?"-0"+this.thangbd +"-01": "-"+this.thangbd+"-01");
			String ngaythangkt=this.namkt + ( (Integer.parseInt(this.thangkt)+1)<10?"-0"+(Integer.parseInt(this.thangkt)+1)+ "-01": "-"+(Integer.parseInt(this.thangkt)+1)+"-01");
			
			conditionETC+=" and a.NgayXuatHD>='"+ngaythangbd+"'";
			conditionETC+=" and a.NgayXuatHD <'"+ngaythangkt+"'";
			
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
			conditionETC+=" and a.khachhang_Fk ='"+this.khId+"'";
		}
		
		if(this.ttId.length()>0)
		{
			conditionETC+=
					" and	(  a.khachHang_FK  in (select pk_seq	 from KhachHang  where tinhthanh_fk='"+this.ttId+"' ) OR  a.npp_dat_Fk in (select pk_seq from nhaphanphoi  where tinhthanh_fk='"+this.ttId+"' )  ) ";
			//conditionETC+=" and a.npp_fk  in (select pk_seq from NHAPHANPHOI  where TINHTHANH_FK='"+this.ttId+"')   ";
			
		}
		
		if(this.nhomId.length()>0)
		{
			conditionETC+=" and C.SanPham_fk in  (select SANPHAM_FK FROM NHOMHANG_SANPHAM  where NhomHang_fk='"+this.nhomId+"' )  ";
		}
		
		
		if(this.kbhId.length()>0)
		{
			conditionETC+=" and a.pk_seq in (select hoadonNpp_fk from Erp_hoadonNpp_Ddh where ddh_fk in (select pk_seq from Erp_DonDatHangNpp where kbh_fk='"+this.kbhId+"' )) ";
		}
		
		if(this.ddkdId.length()>0)
		{
			conditionETC += " and a.khachhang_fk in (select khachhang_fk from KHACHHANG_TUYENBH where tbh_fk in (select pk_Seq from tuyenbanhang where ddkd_Fk ='"+this.ddkdId+"'))";
		}
		
		
		
		String condition_CK="";
		condition_CK+= " and a.npp_fk in " + Ult.quyen_npp(userId)+"";
		if(nppId.length()>0)
		{
			condition_CK+= " and npp_fk='"+this.nppId+"' ";
		}
		
		if(ttId.length()>0)
		{
			condition_CK+=" and a.khachHang_fk in (select pk_seq	 from KhachHang  where tinhthanh_fk='"+this.ttId+"' ) ";
			//condition_CK+=" and a.npp_fk ='"+this.ttId+"'   ";
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
		
		if(this.DSKHT.equals("DSKHT"))
		{

			String ngaythangbd=this.nambd + ( (Integer.parseInt(this.thangbd))<10?"-0"+this.thangbd +"-01": "-"+this.thangbd+"-01");
			String ngaythangkt=this.namkt + ( (Integer.parseInt(this.thangkt)+1)<10?"-0"+(Integer.parseInt(this.thangkt)+1)+ "-01": "-"+(Integer.parseInt(this.thangkt)+1)+"-01");
			
			condition_CK+=" and a.NgayXuatHD>='"+ngaythangbd+"'";
			condition_CK+=" and a.NgayXuatHD <'"+ngaythangkt+"'";
			
		}
		
		
		if(spId.length()>0)
		{
			condition_CK+= " and a.pk_seq in  (select hoadon_fk from HOADON_SP where sanpham_fk='"+spId+"'  ) ";
		}
		
		if(nhomId.length()>0)
		{
			condition_CK+= " and a.pk_seq in  (select hoadon_fk from HOADON_SP where sanpham_fk in   (select SANPHAM_FK FROM NHOMHANG_SANPHAM  where NhomHang_fk='"+this.nhomId+"' )   )  ";
		}
		
		if(this.kbhId.length()>0)
		{
			condition_CK+=" and a.pk_seq in (select hoadon_fk from hoadon_Ddh where ddh_fk in (select pk_seq from DonHang where kbh_fk='"+this.kbhId+"' )) ";
			
		}
		
		if(this.ddkdId.length()>0)
		{
			condition_CK += " and a.khachhang_fk in (select khachhang_fk from KHACHHANG_TUYENBH where tbh_fk in (select pk_Seq from tuyenbanhang where ddkd_Fk ='"+this.ddkdId+"'))";
		}
		
		String conditionNK="";
		/*if(this.laynk.equals("1"))
		{
			conditionNK+=" and isnull(b.isnhapkhau,1)=1 ";
		}*/
		String conditionNKE="";
		/*if(this.laynk.equals("1"))
		{
			conditionNKE+=" and isnull(c.isnhapkhau,1)=1 ";
		}*/

		
		String condition2019 = "";
		if(this.tpId.length()>0) { condition2019 += " and exists ( select 1 from tinhthanh where pk_seq = kh.tinhthanh_fk and pk_seq = '"+ this.tpId +"' ) "; }
		if(this.qhId.length()>0) { condition2019 += " and exists ( select 1 from quanhuyen where pk_seq = kh.quanhuyen_fk and pk_seq = '"+ this.qhId +"' ) "; }
		if(this.phuongxa.length()>0) { condition2019 += " and exists ( select 1 from phuongxa where pk_seq = kh.phuongxa and pk_seq = '"+ this.phuongxa +"' ) "; }
		
		query="";
		if(this.action.length()>0)
		{
			query=
				" SELECT DDKD,vTen,ttTEN, nppMa,nppTEN, khMa, khTEN, khChuCH, khLOAI, khDIACHI, khMaHD, SOLUONG, DONGIA, ( ISNULL([OTC],0) + ISNULL([ETC],0) )  AS DOANHSO, "+ 
				" ( ISNULL([OTC],0) + ISNULL([ETC],0) + ISNULL([OTC_TRA],0) )  AS DOANHSOTRUTRALAI "+
				" FROM  "+
				" (  "+
				" select HD.DDKD,v.TEN as vTen,tt.TEN as ttTEN,npp.MA as nppMa,npp.TEN as nppTEN, \n "+  
				" kh.maFAST as khMa,KH.TEN AS khTEN,isnull(kh.nguoimuahang,'') as khChuCH,isnull(loai.diengiai,'') as khLOAI,KH.DIACHI as khDIACHI,kh.MaHD as khMaHD , \n "+  
				" SUM(SoLuong)as SoLuong,AVG(DonGia) as DonGia,SUM(AVAT)as AVAT,SUM(BVAT)as BVAT,SUM(VAT)as VAT, LOAIHD \n "+  
				" from \n "+  
				" ( \n "+  
				" 	select hdOTC.DDKD,NPP_FK,KHACHHANG_FK,'OTC' as LoaiHD, hdOTC.SoLuong,hdOTC.DonGia ,hdOTC.BVAT, hdOTC.VAT ,hdOTC.AVAT \n "+  
				" 	from \n "+  
				" 	( \n "+  
				"	  	select  ( select top(1)dd.TEN from  KHACHHANG_TUYENBH bb  "+  
				"		inner join TUYENBANHANG cc on bb.TBH_FK=cc.PK_SEQ "+
				"		inner join DAIDIENKINHDOANH dd on cc.DDKD_FK=dd.PK_SEQ  "+
				"		where bb.KHACHHANG_FK=A.KHACHHANG_FK ) as DDKD, a.KHACHHANG_FK,a.NPP_FK	,sum(round(b.SoLuong*b.DONGIA,0)) as BVAT, \n "+
				"		sum( round( round( b.SoLuong*b.DONGIA,0) *(1+ b.vat/100) ,0)) as AVAT ,  \n"+
				"		sum( round( round( b.SoLuong*b.DONGIA,0) *(b.vat/100.0) ,0)) as VAT,  \n "+ 
				"		SUM(b.SoLuong)as SoLuong,AVG(b.DonGia) as DonGia \n "+
				"		from HOADON a inner join HOADON_SP b on b.HOADON_FK=a.PK_SEQ  \n "+
				"		where a.LOAIHOADON =0 and a.TRANGTHAI not in (1,3,5) "+condition+conditionNK+" \n "+
				"		group by a.NPP_FK ,A.KHACHHANG_FK  "+
				" 	) AS hdOTC \n "+  
				
				" 	UNION ALL \n "+    
				" 	select hdOTC_TRA.DDKD,NPP_FK, KHACHHANG_FK, 'OTC_TRA' as LoaiHD, hdOTC_TRA.SoLuong, hdOTC_TRA.DonGia, hdOTC_TRA.BVAT, hdOTC_TRA.VAT, hdOTC_TRA.AVAT "+   
				" 	from "+   
				" 	( "+   
				" 		select ( select top(1)dd.TEN from  KHACHHANG_TUYENBH bb "+    
				" 		inner join TUYENBANHANG cc on bb.TBH_FK=cc.PK_SEQ "+ 
				" 		inner join DAIDIENKINHDOANH dd on cc.DDKD_FK=dd.PK_SEQ "+  
				" 		where bb.KHACHHANG_FK=A.KHACHHANG_FK ) as DDKD, a.KHACHHANG_FK,a.NPP_FK, "+
				" 		(-1)*sum(round(b.SoLuong*b.DONGIA,0)) as BVAT, "+ 
				" 		(-1)*sum( round( round( b.SoLuong*b.DONGIA,0) *(1+ b.vat/100) ,0)) as AVAT, "+   
				" 		(-1)*sum( round( round( b.SoLuong*b.DONGIA,0) *(b.vat/100.0) ,0)) as VAT, "+   
				" 		(-1)*SUM(b.SoLuong)as SoLuong, AVG(b.DonGia) as DonGia "+ 
				" 		FROM ERP_HANGTRALAINPP A INNER JOIN ERP_HANGTRALAINPP_SANPHAM B ON B.HANGTRALAI_FK = A.PK_SEQ "+ 
				" 		WHERE A.TRANGTHAI = '1' "+ condition_tra + 	 
				" 		group by a.NPP_FK, A.KHACHHANG_FK "+  
				" 	) AS hdOTC_TRA "+
				
				" 	UNION ALL \n "+    
				" 	select ETC.DDKD,npp_fk,KHACHHANG_FK, \n "+  
				" 	'ETC' as LoaiHD,SUM(ETC.SoLuong)as SoLuong,AVG(ETC.DonGia) as DonGia, \n"+  
				"	sum( ROUND(soluong * dongia,0 ) - ck )  as BVAT, \n"+
				" 	sum(ROUND( ROUND ( soluong * dongia- ck,0 )* thuexuat/100.0,0 )) as VAT,  \n"+ 
				"	sum(ROUND( ( ROUND (soluong * dongia , 0 ) - ck ) * (1+ thuexuat/100.0),0 )) as AVAT \n"+  	  
				" 	from \n "+  
				" 	( \n "+  
				" 		select ( select top(1)dd.TEN from  KHACHHANG_TUYENBH bb \n"+  
				"		inner join TUYENBANHANG cc on bb.TBH_FK=cc.PK_SEQ \n"+
				"		inner join DAIDIENKINHDOANH dd on cc.DDKD_FK=dd.PK_SEQ \n"+
				"		where bb.KHACHHANG_FK=a.KHACHHANG_FK ) as DDKD, \n"+
				"		a.npp_fk, a.KHACHHANG_FK, \n "+  
				" 		case when c.donvitinh = e.donvi then c.soluong \n "+  
				" 		else c.soluong * ( select SOLUONG1 / SOLUONG2 from QUYCACH where sanpham_fk = c.sanpham_fk and DVDL2_FK = ( select pk_seq from DONVIDOLUONG where donvi = c.donvitinh ) and DVDL1_FK = d.DVDL_FK ) end as soluong, \n "+  
				" 		case when c.donvitinh = e.donvi then c.dongia \n "+  
				" 		else c.dongia * ( select SOLUONG2 / SOLUONG1 from QUYCACH where sanpham_fk = c.sanpham_fk and DVDL2_FK = ( select pk_seq from DONVIDOLUONG where donvi = c.donvitinh ) and DVDL1_FK = d.DVDL_FK ) end as dongia, \n "+  
				" 		c.vat as thuexuat , round(isnull(c.CHIETKHAU,0),0) as ck ,d.MA as spMa,d.TEN as spTen,e.DONVI as spDonVi \n "+  
				" 		from ERP_HOADONNPP a \n"+  
				" 		inner join ERP_HOADONNPP_SP c on a.pk_seq = c.hoadon_fk \n"+  
				" 		inner join SANPHAM d on c.sanpham_fk = d.pk_seq \n"+  
				" 		inner join DONVIDOLUONG e on d.DVDL_FK = e.pk_seq \n"+  
				" 		where 1=1 and  d.trangthai=1 and a.trangthai not in ( 1 , 3, 5 )  and a.KHACHHANG_FK is not null "+conditionETC+conditionNKE+" \n "+  
				" 	) ETC \n"+  
				" 	where soluong>0 \n"+  
				" 	GROUP BY NPP_FK,KHACHHANG_FK,DDKD \n";

				if(this.nhomId.length()==0)
				{
					query+= " UNION ALL \n "+   
							" select ( select top(1)dd.TEN from  KHACHHANG_TUYENBH bb \n"+ 
							" inner join TUYENBANHANG cc on bb.TBH_FK=cc.PK_SEQ \n"+
							" inner join DAIDIENKINHDOANH dd on cc.DDKD_FK=dd.PK_SEQ \n"+
							" where bb.KHACHHANG_FK=c.KHACHHANG_FK ) as DDKD, \n"+
							" c.NPP_FK,c.KHACHHANG_FK,'OTC' as Loai,0 as SoLuong,0 as DonGia \n"+  
							" ,-1*SUM(ROUND(a.chietkhau,0)) as BVAT,(-1)*SUM(ROUND(  ROUND( a.chietkhau,0)*(a.thuevat/100.0),0 ,0 )) as VAT, \n "+  
							" (-1)*SUM(ROUND(  ROUND( a.chietkhau,0)*(1+ a.thuevat/100.0),0 ,0 )) as AVAT  \n "+
							" from HOADON_CHIETKHAU a left join LoaiCK b on b.Ma=a.diengiai \n "+  
							" inner join HOADON c on c.PK_SEQ=a.hoadon_fk \n "+  
							" inner join NHAPHANPHOI npp on npp.pk_Seq= C .NPP_FK \n "+  
							" inner join KHACHHANG kh on kh.PK_SEQ= C.KHACHHANG_FK \n "+  
							" left join KHUVUC kv on kv.PK_SEQ=npp.KHUVUC_FK \n "+  
							" left join VUNG v on v.PK_SEQ=kv.VUNG_FK \n "+  
							" left join TINHTHANH tt on tt.PK_SEQ=npp.TINHTHANH_FK \n "+  
							" left join LOAIKHACHHANG loai on loai.pk_seq=kh.XuatKhau \n "+  
							" where  isnull(a.HIENTHI,0)=1 and  a.hoadon_fk in \n "+  
							" ( select pk_Seq from HOADON a where isnull(LOAIHOADON,0)=0 and a.TRANGTHAI not IN (1,3,5)  "+condition_CK+" ) \n "+  
							" group by c.NPP_FK,c.KHACHHANG_FK  \n ";  
				}

				query+= 
					" ) as HD \n "+  
					" inner join NHAPHANPHOI npp on npp.PK_SEQ=HD.NPP_FK \n "+  
					" left join KHUVUC kv on kv.PK_SEQ=npp.KHUVUC_FK \n "+  
					" left join VUNG v on v.PK_SEQ=kv.VUNG_FK \n "+  
					" left join KHACHHANG kh on kh.PK_SEQ=HD.KHACHHANG_FK \n "+  
					//" left join LOAIKHACHHANG loai on loai.pk_seq=kh.XuatKhau \n "+  
					" left join loaicuahang loai on loai.pk_seq=kh.lch_fk \n "+  
					" left join TINHTHANH tt on tt.PK_SEQ=kh.TINHTHANH_FK \n " +
					" where 1=1 "+ condition2019 +  
					" group by v.TEN, tt.TEN, npp.MA, npp.TEN, kh.maFAST, KH.TEN, KH.DIACHI, loai.diengiai, kh.nguoimuahang, kh.MaHD, HD.DDKD , HD.LOAIHD "+  
					" ) P PIVOT ( SUM(BVAT) FOR LOAIHD IN ( [OTC],[OTC_TRA], [ETC] ) ) AS TONG ";
			this.queryHd=query;
			setTotal_Query(query);
		}
		System.out.println("init() query: " + query);
		this.hoadonRs= super.createSplittingData(super.getItems(), super.getSplittings(), " khMa ", query); 
		createRs();
	}
	
	public void init3(String search) 
	{
		String query;
		String condition="", condition_tra = "";   
		if(!this.DSKHT_client.equals("DSKHT_client"))
		{
			condition+= " and a.npp_fk in " + Ult.quyen_npp(userId)+"";
			condition_tra+= " and a.npp_fk in " + Ult.quyen_npp(userId)+"";
		}
		
		if(this.tuNgay.length()>0)
		{
			condition+=" and a.NgayXuatHD>='"+this.tuNgay+"'";
			condition_tra+=" and a.ngaytra>='"+this.tuNgay+"'";
		}
		if(this.denNgay.length()>0)
		{
			condition+=" and a.NgayXuatHD <='"+this.denNgay+"'";
			condition_tra+=" and a.ngaytra <='"+this.denNgay+"'";
		}
		if(this.nppId.length()>0)
		{
			condition+=" and a.npp_fk ='"+this.nppId+"'";
			condition_tra+=" and a.npp_fk ='"+this.nppId+"'";
		}
		
		if(vungId.length()>0)
		{
			condition+= "  and a.npp_fk in (select pk_Seq  from nhaphanphoi  where khuvuc_fk in (select pk_seq from KHUVUC WHERE VUNG_FK='"+this.vungId+"' ))";
			condition_tra+= "  and a.npp_fk in (select pk_Seq  from nhaphanphoi  where khuvuc_fk in (select pk_seq from KHUVUC WHERE VUNG_FK='"+this.vungId+"' ))";
		}
		if(this.spId.length()>0)
		{
			condition+=" and b.sanpham_fk ='"+this.spId+"'";
			condition_tra+=" and b.sanpham_fk ='"+this.spId+"'";
		}
		if(this.khId.length()>0)
		{
			condition+=" and a.khachhang_Fk ='"+this.khId+"'";
			condition_tra+=" and a.khachhang_Fk ='"+this.khId+"'";
		}
		
		if(this.ttId.length()>0)
		{
			condition+=" and a.khachHang_fk in (select pk_seq from KhachHang where tinhthanh_fk='"+this.ttId+"' ) ";
			condition_tra+=" and a.khachHang_fk in (select pk_seq from KhachHang where tinhthanh_fk='"+this.ttId+"' ) ";
			/*condition+=" and a.npp_fk  in (select pk_seq from NHAPHANPHOI  where TINHTHANH_FK='"+this.ttId+"')   ";*/
		}
		
		if(this.nhomId.length()>0)
		{
			condition+=" and b.SanPham_fk in  (select SANPHAM_FK FROM NHOMHANG_SANPHAM  where NhomHang_fk='"+this.nhomId+"' ) ";
			condition_tra+=" and b.SanPham_fk in  (select SANPHAM_FK FROM NHOMHANG_SANPHAM  where NhomHang_fk='"+this.nhomId+"' ) ";
		}
		
		if(this.kbhId.length()>0)
		{
			condition+=" and a.pk_seq in (select hoadon_fk from hoadon_Ddh where ddh_fk in (select pk_seq from DonHang where kbh_fk='"+this.kbhId+"' )) ";
		}
		
		if(this.ddkdId.length()>0)
		{
			condition += " and a.khachhang_fk in (select khachhang_fk from KHACHHANG_TUYENBH where tbh_fk in (select pk_Seq from tuyenbanhang where ddkd_Fk ='"+this.ddkdId+"'))";
			condition_tra += " and a.khachhang_fk in (select khachhang_fk from KHACHHANG_TUYENBH where tbh_fk in (select pk_Seq from tuyenbanhang where ddkd_Fk ='"+this.ddkdId+"'))";
		}
		
		if(this.DSKHT.equals("DSKHT"))
		{
			String ngaythangbd=this.nambd + ( (Integer.parseInt(this.thangbd))<10?"-0"+this.thangbd +"-01": "-"+this.thangbd+"-01");
			String ngaythangkt=this.namkt + ( (Integer.parseInt(this.thangkt)+1)<10?"-0"+(Integer.parseInt(this.thangkt)+1)+ "-01": "-"+(Integer.parseInt(this.thangkt)+1)+"-01");
			condition+=" and a.NgayXuatHD>='"+ngaythangbd+"'";
			condition+=" and a.NgayXuatHD <'"+ngaythangkt+"'";
			
			condition_tra+=" and a.ngaytra>='"+ngaythangbd+"'";
			condition_tra+=" and a.ngaytra <'"+ngaythangkt+"'";
		}
		
		String ngaythangbd_O=this.nambd + ( (Integer.parseInt(this.thangbd))<10?"-0"+this.thangbd : "-"+this.thangbd);
		String ngaythangkt_O=this.namkt + ( (Integer.parseInt(this.thangkt))<10?"-0"+(Integer.parseInt(this.thangkt)): "-"+(Integer.parseInt(this.thangkt)));
		
		String sqls="";
		sqls+= "select * from  uf_CacThangTrongKhoangThoiGian ('"+ngaythangbd_O+"','"+ngaythangkt_O+"') " ;
		System.out.println("cau len la "+sqls);
		String column="";
		String column_name="";
		ResultSet rs=db.get(sqls);
		try {
			while(rs.next())
			{
				column +="["+rs.getString("thang")+"],";
				column_name +="isnull(pivotdata.["+rs.getString("thang")+"],0) as 'DT_THANG_"+rs.getString("thang")+"',";
				this.arrname.add("DT th??ng "+ rs.getString("thang") );
				this.arrname_colum.add("DT_THANG_"+rs.getString("thang")+"");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("colm la "+column);
		System.out.println("colm_name la "+column_name);
		
		String condition_CK="";
		if(!this.DSKHT_client.equals("DSKHT_client")) { condition_CK+= " and a.npp_fk in " + Ult.quyen_npp(userId)+""; }
		if(nppId.length()>0) { condition_CK+= " and npp_fk='"+this.nppId+"' "; }
		
		if(ttId.length()>0)
		{ condition_CK+=" and a.khachHang_fk in (select pk_seq from KhachHang  where tinhthanh_fk='"+this.ttId+"' ) "; }
		
		if(vungId.length()>0)
		{ condition_CK+= "  and a.npp_fk in (select pk_Seq  from nhaphanphoi  where khuvuc_fk in (select pk_seq from KHUVUC WHERE VUNG_FK='"+this.vungId+"' ))"; }
		
		if(khId.length()>0)
		{ condition_CK+= " and khachhang_fk='"+this.khId+"' "; }
		
		if(tuNgay.length()>0)
		{ condition_CK+= " and a.NgayXuatHD >='"+this.tuNgay+"' "; }
		
		if(denNgay.length()>0)
		{ condition_CK+= " and a.NgayXuatHD <='"+this.denNgay+"' "; }
		
		if(this.DSKHT.equals("DSKHT"))
		{
			String ngaythangbd=this.nambd + ( (Integer.parseInt(this.thangbd))<10?"-0"+this.thangbd +"-01": "-"+this.thangbd+"-01");
			String ngaythangkt=this.namkt + ( (Integer.parseInt(this.thangkt)+1)<10?"-0"+(Integer.parseInt(this.thangkt)+1)+ "-01": "-"+(Integer.parseInt(this.thangkt)+1)+"-01");
			condition_CK+=" and a.NgayXuatHD>='"+ngaythangbd+"'";
			condition_CK+=" and a.NgayXuatHD <'"+ngaythangkt+"'";
		}
		
		if(spId.length()>0)
		{ condition_CK+= " and a.pk_seq in  (select hoadon_fk from HOADON_SP where sanpham_fk='"+spId+"'  ) "; }
		
		if(nhomId.length()>0)
		{ condition_CK+= " and a.pk_seq in  (select hoadon_fk from HOADON_SP where sanpham_fk in   (select SANPHAM_FK FROM NHOMHANG_SANPHAM  where NhomHang_fk='"+this.nhomId+"' )) "; }
		
		if(this.kbhId.length()>0)
		{ condition_CK+=" and a.pk_seq in (select hoadon_fk from hoadon_Ddh where ddh_fk in (select pk_seq from DonHang where kbh_fk='"+this.kbhId+"' )) "; }
		
		if(this.ddkdId.length()>0)
		{ condition_CK += " and a.khachhang_fk in (select khachhang_fk from KHACHHANG_TUYENBH where tbh_fk in (select pk_Seq from tuyenbanhang where ddkd_Fk ='"+this.ddkdId+"'))"; }
		String conditionNK="";
		query="";

		if(this.action.length()>0)
		{
			System.out.println("action init3 : "+ action);
			if(this.action.trim().equals("excel1"))
			{
				query =
					" select "+column_name+"vTen, isnull(khDIACHI,'') khDIACHI, ddkd, ttTEN, nppMa, nppTEN, isnull(khMa,'') khMa, isnull(khTEN,'') khTEN, isnull(khChuCH,'') khChuCH, isnull(khLOAI,'') khLOAI, khMaHD from ( select  HD.ngayhd,HD.DDKD,v.TEN as vTen,tt.TEN as ttTEN,npp.MA as nppMa,npp.TEN as nppTEN,   \n "+      
					" kh.maFAST as khMa,KH.TEN AS khTEN,kh.CHUCUAHIEU as khChuCH,loai.ten as khLOAI,KH.DIACHI as khDIACHI,kh.MaHD as khMaHD ,   \n "+      
					" SUM(AVAT) as AVAT \n "+      
					" from \n "+      
					" ( \n "+      
					"	select hdOTC.ngayhd,hdOTC.DDKD,NPP_FK,KHACHHANG_FK,'OTC' as LoaiHD, hdOTC.SoLuong,hdOTC.DonGia ,hdOTC.BVAT, hdOTC.VAT ,hdOTC.AVAT \n "+      
					"   from \n "+      
					"   ( \n "+      
					"		select convert(char(7), a.NGAYXUATHD, 126) as ngayhd, ( select top(1)dd.TEN from  KHACHHANG_TUYENBH bb \n"+    
					"		inner join TUYENBANHANG cc on bb.TBH_FK=cc.PK_SEQ \n"+
					"		inner join DAIDIENKINHDOANH dd on cc.DDKD_FK=dd.PK_SEQ \n"+
					"		where bb.KHACHHANG_FK=A.KHACHHANG_FK ) as DDKD, a.KHACHHANG_FK,a.NPP_FK	,sum(round(b.SoLuong*b.DONGIA,0)) as BVAT,sum( round( round( b.SoLuong*b.DONGIA,0) *(1+ b.vat/100) ,0)) as AVAT, \n"+
					"		sum( round( round( b.SoLuong*b.DONGIA,0) *(b.vat/100.0) ,0)) as VAT, \n "+   
					"		SUM(b.SoLuong)as SoLuong,AVG(b.DonGia) as DonGia \n "+
					"		from HOADON a inner join HOADON_SP b on b.HOADON_FK=a.PK_SEQ \n "+
					"		where a.LOAIHOADON =0 and a.TRANGTHAI not in ('3', '5') "+condition+conditionNK+" \n "+
					"		group by a.NPP_FK ,A.KHACHHANG_FK,a.NGAYXUATHD \n"+
					"   ) as hdOTC \n "+
				
					"	UNION ALL "+
					" 	SELECT HDOTC.NGAYHD,HDOTC.DDKD,NPP_FK,KHACHHANG_FK,'OTC_TRA' AS LOAIHD, HDOTC.SOLUONG,HDOTC.DONGIA ,HDOTC.BVAT, HDOTC.VAT ,HDOTC.AVAT    "+
					" 	FROM "+
					" 	( "+
					" 		SELECT CONVERT(CHAR(7), A.NGAYTRA, 126) AS NGAYHD, ( SELECT TOP(1)DD.TEN FROM  KHACHHANG_TUYENBH BB  "+
					" 		INNER JOIN TUYENBANHANG CC ON BB.TBH_FK=CC.PK_SEQ  "+
					" 		INNER JOIN DAIDIENKINHDOANH DD ON CC.DDKD_FK=DD.PK_SEQ  "+
					" 		WHERE BB.KHACHHANG_FK=A.KHACHHANG_FK ) AS DDKD, A.KHACHHANG_FK,A.NPP_FK, "+
					" 		(-1)*SUM(ROUND(B.SOLUONG*B.DONGIA,0)) AS BVAT, (-1)*SUM( ROUND( ROUND( B.SOLUONG*B.DONGIA,0) *(1 + B.VAT/100) ,0)) AS AVAT,  "+
					" 		(-1)*SUM( ROUND( ROUND( B.SOLUONG*B.DONGIA,0) *(B.VAT/100.0) ,0)) AS VAT,  "+
					" 		(-1)*SUM(B.SOLUONG)AS SOLUONG,AVG(B.DONGIA) AS DONGIA  "+
					" 		FROM ERP_HANGTRALAINPP A INNER JOIN ERP_HANGTRALAINPP_SANPHAM B ON B.HANGTRALAI_FK = A.PK_SEQ  "+
					" 		WHERE A.TRANGTHAI = '1' "+ condition_tra +
					" 		GROUP BY A.NPP_FK ,A.KHACHHANG_FK,A.NGAYTRA  "+
					" 	) AS HDOTC ";
					
				if(this.nhomId.length()==0)
				{
					query+= 
						" UNION ALL \n "+     
						" select convert (char(7),c.NGAYXUATHD,126) as ngayhd ,( select top(1)dd.TEN from  KHACHHANG_TUYENBH bb  \n"+    
						" inner join TUYENBANHANG cc on bb.TBH_FK=cc.PK_SEQ \n"+
						" inner join DAIDIENKINHDOANH dd on cc.DDKD_FK=dd.PK_SEQ  \n"+
						" where bb.KHACHHANG_FK=c.KHACHHANG_FK ) as DDKD  , \n"+
						" c.NPP_FK,c.KHACHHANG_FK,'OTC' as Loai,0 as SoLuong,0 as DonGia \n "+      
						" ,-1*SUM(ROUND(a.chietkhau,0)) as BVAT,(-1)*SUM(ROUND(  ROUND( a.chietkhau,0)*(a.thuevat/100.0),0 ,0 )) as VAT, \n "+      
						" (-1)*SUM(ROUND(  ROUND( a.chietkhau,0)*(1+ a.thuevat/100.0),0 ,0 )) as AVAT \n "+
						" from HOADON_CHIETKHAU a left join LoaiCK b on b.Ma=a.diengiai \n "+      
						" inner join HOADON c on c.PK_SEQ=a.hoadon_fk \n "+      
						" inner join NHAPHANPHOI npp on npp.pk_Seq= C .NPP_FK \n "+      
						" inner join KHACHHANG kh on kh.PK_SEQ= C.KHACHHANG_FK \n "+      
						" left join KHUVUC kv on kv.PK_SEQ=npp.KHUVUC_FK   \n "+      
						" left join VUNG v on v.PK_SEQ=kv.VUNG_FK \n "+      
						" left join TINHTHANH tt on tt.PK_SEQ=npp.TINHTHANH_FK \n "+      
						" left join LOAIKHACHHANG loai on loai.pk_seq=kh.XuatKhau \n "+      
						" where isnull(a.HIENTHI,0)=1 and  a.hoadon_fk in \n "+      
						" ( select pk_Seq from HOADON a where isnull(LOAIHOADON,0)= 0 and a.TRANGTHAI not IN (3,5)  "+condition_CK+" ) \n "+      
						" group by c.NPP_FK,c.KHACHHANG_FK,c.NGAYXUATHD \n ";      
				}
			
				query+= 
					" ) as HD \n "+      
					" inner join NHAPHANPHOI npp on npp.PK_SEQ=HD.NPP_FK   \n "+      
					" left join KHUVUC kv on kv.PK_SEQ=npp.KHUVUC_FK   \n "+      
					" left join VUNG v on v.PK_SEQ=kv.VUNG_FK   \n "+      
					" left join KHACHHANG kh on kh.PK_SEQ=HD.KHACHHANG_FK   \n "+      
					" left join LOAIKHACHHANG loai on loai.pk_seq=kh.XuatKhau   \n "+      
					" left join TINHTHANH tt on tt.PK_SEQ=kh.TINHTHANH_FK   \n "+      
					" group by ngayhd,v.TEN ,tt.TEN ,npp.MA,npp.TEN , kh.maFAST ,KH.TEN ,KH.DIACHI,loai.ten ,kh.CHUCUAHIEU ,kh.MaHD,HD.DDKD  "+
					" ) as data "+
					" pivot (sum(data.avat) "+
					" FOR ngayhd IN ("+column+"[0]) ) as pivotdata ";
			}
			else if(this.action.trim().equals("search") || this.action.trim().equals("excel"))
			{
				query =
					" SELECT 0 SOLUONG, 0 DONGIA, VTEN, KHDIACHI, DDKD, TTTEN, NPPMA, NPPTEN, KHMA, KHTEN, KHCHUCH, KHLOAI, KHMAHD, NGAYHD, "+ 
					" ( ISNULL([OTC], 0) + ISNULL([ETC], 0) ) DOANHSO, ( ISNULL([OTC], 0) + ISNULL([OTC_TRA], 0) + ISNULL([ETC], 0) ) DOANHSOTRUTRALAI "+
					" FROM "+
					" ( " +
					" 	SELECT HD.NGAYHD,HD.DDKD,V.TEN AS VTEN,TT.TEN AS TTTEN,NPP.MA AS NPPMA,NPP.TEN AS NPPTEN, " +
					" 	kh.maFAST as khMa, KH.TEN AS KHTEN,KH.CHUCUAHIEU AS KHCHUCH,LOAI.TEN AS KHLOAI,KH.DIACHI AS KHDIACHI,KH.MAHD AS KHMAHD, HD.LOAIHD, " +
					" 	SUM(BVAT) AS BVAT "+  
					" 	FROM \n "+      
					" 	( \n "+      
					"		select hdOTC.ngayhd,hdOTC.DDKD,NPP_FK,KHACHHANG_FK,'OTC' as LoaiHD, hdOTC.SoLuong,hdOTC.DonGia ,hdOTC.BVAT, hdOTC.VAT ,hdOTC.AVAT \n "+      
					"   	from \n "+      
					"   	( \n "+      
					"			select convert(char(7), a.NGAYXUATHD, 126) as ngayhd, ( select top(1)dd.TEN from  KHACHHANG_TUYENBH bb \n"+    
					"			inner join TUYENBANHANG cc on bb.TBH_FK=cc.PK_SEQ \n"+
					"			inner join DAIDIENKINHDOANH dd on cc.DDKD_FK=dd.PK_SEQ \n"+
					"			where bb.KHACHHANG_FK=A.KHACHHANG_FK ) as DDKD, a.KHACHHANG_FK,a.NPP_FK	,sum(round(b.SoLuong*b.DONGIA,0)) as BVAT,sum( round( round( b.SoLuong*b.DONGIA,0) *(1+ b.vat/100) ,0)) as AVAT, \n"+
					"			sum( round( round( b.SoLuong*b.DONGIA,0) *(b.vat/100.0) ,0)) as VAT, \n "+   
					"			SUM(b.SoLuong)as SoLuong,AVG(b.DonGia) as DonGia \n "+
					"			from HOADON a inner join HOADON_SP b on b.HOADON_FK=a.PK_SEQ \n "+
					"			where a.LOAIHOADON =0 and a.TRANGTHAI not in ('3', '5') "+condition+conditionNK+" \n "+
					"			group by a.NPP_FK ,A.KHACHHANG_FK,a.NGAYXUATHD \n"+
					"  	 	) as hdOTC \n "+
				
					"		UNION ALL "+
					" 		SELECT HDOTC.NGAYHD,HDOTC.DDKD,NPP_FK,KHACHHANG_FK,'OTC_TRA' AS LOAIHD, HDOTC.SOLUONG,HDOTC.DONGIA ,HDOTC.BVAT, HDOTC.VAT ,HDOTC.AVAT    "+
					" 		FROM "+
					" 		( "+
					" 			SELECT CONVERT(CHAR(7), A.NGAYTRA, 126) AS NGAYHD, ( SELECT TOP(1)DD.TEN FROM  KHACHHANG_TUYENBH BB  "+
					" 			INNER JOIN TUYENBANHANG CC ON BB.TBH_FK=CC.PK_SEQ  "+
					" 			INNER JOIN DAIDIENKINHDOANH DD ON CC.DDKD_FK=DD.PK_SEQ  "+
					" 			WHERE BB.KHACHHANG_FK=A.KHACHHANG_FK ) AS DDKD, A.KHACHHANG_FK,A.NPP_FK, "+
					" 			(-1)*SUM(ROUND(B.SOLUONG*B.DONGIA,0)) AS BVAT, (-1)*SUM( ROUND( ROUND( B.SOLUONG*B.DONGIA,0) *(1 + B.VAT/100) ,0)) AS AVAT,  "+
					" 			(-1)*SUM( ROUND( ROUND( B.SOLUONG*B.DONGIA,0) *(B.VAT/100.0) ,0)) AS VAT,  "+
					" 			(-1)*SUM(B.SOLUONG)AS SOLUONG,AVG(B.DONGIA) AS DONGIA  "+
					" 			FROM ERP_HANGTRALAINPP A INNER JOIN ERP_HANGTRALAINPP_SANPHAM B ON B.HANGTRALAI_FK = A.PK_SEQ  "+
					" 			WHERE A.TRANGTHAI = '1' "+ condition_tra +
					" 			GROUP BY A.NPP_FK ,A.KHACHHANG_FK,A.NGAYTRA  "+
					" 		) AS HDOTC ";
					
				if(this.nhomId.length()==0)
				{
					query+= 
						" UNION ALL \n "+     
						" select convert (char(7),c.NGAYXUATHD,126) as ngayhd ,( select top(1)dd.TEN from  KHACHHANG_TUYENBH bb  \n"+    
						" inner join TUYENBANHANG cc on bb.TBH_FK=cc.PK_SEQ \n"+
						" inner join DAIDIENKINHDOANH dd on cc.DDKD_FK=dd.PK_SEQ  \n"+
						" where bb.KHACHHANG_FK=c.KHACHHANG_FK ) as DDKD  , \n"+
						" c.NPP_FK,c.KHACHHANG_FK,'OTC' as Loai,0 as SoLuong,0 as DonGia \n "+      
						" ,-1*SUM(ROUND(a.chietkhau,0)) as BVAT,(-1)*SUM(ROUND(  ROUND( a.chietkhau,0)*(a.thuevat/100.0),0 ,0 )) as VAT, \n "+      
						" (-1)*SUM(ROUND(  ROUND( a.chietkhau,0)*(1+ a.thuevat/100.0),0 ,0 )) as AVAT \n "+
						" from HOADON_CHIETKHAU a left join LoaiCK b on b.Ma=a.diengiai \n "+      
						" inner join HOADON c on c.PK_SEQ=a.hoadon_fk \n "+      
						" inner join NHAPHANPHOI npp on npp.pk_Seq= C .NPP_FK \n "+      
						" inner join KHACHHANG kh on kh.PK_SEQ= C.KHACHHANG_FK \n "+      
						" left join KHUVUC kv on kv.PK_SEQ=npp.KHUVUC_FK   \n "+      
						" left join VUNG v on v.PK_SEQ=kv.VUNG_FK \n "+      
						" left join TINHTHANH tt on tt.PK_SEQ=npp.TINHTHANH_FK \n "+      
						" left join LOAIKHACHHANG loai on loai.pk_seq=kh.XuatKhau \n "+      
						" where isnull(a.HIENTHI,0)=1 and  a.hoadon_fk in \n "+      
						" ( select pk_Seq from HOADON a where isnull(LOAIHOADON,0)= 0 and a.TRANGTHAI not IN (3,5)  "+condition_CK+" ) \n "+      
						" group by c.NPP_FK,c.KHACHHANG_FK,c.NGAYXUATHD \n ";      
				}
			
				query+= 
					" ) as HD \n "+      
					" inner join NHAPHANPHOI npp on npp.PK_SEQ=HD.NPP_FK   \n "+      
					" left join KHUVUC kv on kv.PK_SEQ=npp.KHUVUC_FK   \n "+      
					" left join VUNG v on v.PK_SEQ=kv.VUNG_FK   \n "+      
					" left join KHACHHANG kh on kh.PK_SEQ=HD.KHACHHANG_FK   \n "+      
					" left join LOAIKHACHHANG loai on loai.pk_seq=kh.XuatKhau   \n "+      
					" left join TINHTHANH tt on tt.PK_SEQ=kh.TINHTHANH_FK   \n "+
					
					" group by ngayhd,v.TEN ,tt.TEN ,npp.MA,npp.TEN , kh.maFAST ,KH.TEN ,KH.DIACHI,loai.ten ,kh.CHUCUAHIEU ,kh.MaHD,HD.DDKD, HD.LoaiHD "+ 
					" ) AS DATA "+  
					" PIVOT ( SUM(BVAT) FOR LOAIHD IN ([OTC], [OTC_TRA], [ETC]) ) AS PivotTable ";
			}
			
			this.queryHd = query;
			setTotal_Query(query);
		}
		System.out.println("init() query: " + query);
		this.hoadonRs= super.createSplittingData(super.getItems(), super.getSplittings(), " khMa ", query); 
		createRs();
	}
	
	
	/** B??o c??o qu???n tr??? > Ch??? s??? th??nh t??ch > Doanh thu s???n ph???m theo KH */
	public void init2(String search) // danh cho ChiSoDoanhThuNV.jsp
	{
		String query;
		System.out.println("flag ne"+this.flagnpp);
		String condition="", condition_tra = "";   
		if(this.flagnpp.equals("1"))
		{ condition=""; condition_tra = ""; }
		else { condition+= " and a.npp_fk in " + Ult.quyen_npp(userId)+""; condition_tra += " and a.npp_fk in " + Ult.quyen_npp(userId)+""; }
		
		if(this.tuNgay.length()>0) { condition+=" and a.NgayXuatHD>='"+this.tuNgay+"'"; condition_tra+=" and a.ngaytra>='"+this.tuNgay+"'"; }
		if(this.denNgay.length()>0) { condition+=" and a.NgayXuatHD <='"+this.denNgay+"'"; condition_tra+=" and a.ngaytra <= '"+this.denNgay+"' "; }
		if(this.nppId.length()>0) { condition+=" and a.npp_fk ='"+this.nppId+"'"; condition_tra+=" and a.npp_fk ='"+this.nppId+"'"; }
		if(vungId.length()>0) 
		{ 
			condition+= "  and a.npp_fk in (select pk_Seq  from nhaphanphoi  where khuvuc_fk in (select pk_seq from KHUVUC WHERE VUNG_FK='"+this.vungId+"' ))";
			condition_tra+= "  and a.npp_fk in (select pk_Seq  from nhaphanphoi  where khuvuc_fk in (select pk_seq from KHUVUC WHERE VUNG_FK='"+this.vungId+"' ))";
		}
		if(this.spId.length()>0) 
		{ condition+=" and b.sanpham_fk ='"+this.spId+"'";
		  condition_tra+=" and b.sanpham_fk ='"+this.spId+"'";
		}
		if(this.khId.length()>0) { condition+=" and a.khachhang_Fk ='"+this.khId+"'"; condition_tra+=" and a.khachhang_Fk ='"+this.khId+"'"; }
		if(this.ttId.length()>0) 
		{ 
			condition+=" and a.npp_fk in (select pk_seq	 from nhaphanphoi  where tinhthanh_fk='"+this.ttId+"' ) ";
			condition_tra+=" and a.npp_fk in (select pk_seq	 from nhaphanphoi  where tinhthanh_fk='"+this.ttId+"' ) ";
		}
		if(this.nhomId.length()>0) 
		{ 
			condition+=" and b.SanPham_fk in  (select sp_FK FROM NHOMSANPHAM_SANPHAM  where nsp_fk='"+this.nhomId+"' )  ";
			condition_tra+=" and b.SanPham_fk in  (select sp_FK FROM NHOMSANPHAM_SANPHAM  where nsp_fk='"+this.nhomId+"' )  ";
		}
		if(this.kbhId.length()>0) { condition+=" and a.pk_seq in (select hoadon_fk from hoadon_Ddh where ddh_fk in (select pk_seq from DonHang where kbh_fk='"+this.kbhId+"' )) "; }
		if(this.ddkdId.length()>0) 
		{ 
			condition += " and a.khachhang_fk in (select khachhang_fk from KHACHHANG_TUYENBH where tbh_fk in (select pk_Seq from tuyenbanhang where ddkd_Fk ='"+this.ddkdId+"'))";
			condition_tra += " and a.khachhang_fk in (select khachhang_fk from KHACHHANG_TUYENBH where tbh_fk in (select pk_Seq from tuyenbanhang where ddkd_Fk ='"+this.ddkdId+"'))";
		}
		
		String conditionETC="";  
		if(this.flagnpp.equals("1")) { conditionETC=""; }
		else {	conditionETC+= " and a.npp_fk in " + Ult.quyen_npp(userId)+""; }	
		
		if(this.tuNgay.length()>0) { conditionETC+=" and a.NgayXuatHD>='"+this.tuNgay+"'"; }
		if(this.denNgay.length()>0) { conditionETC+=" and a.NgayXuatHD <='"+this.denNgay+"'"; }
		if(this.nppId.length()>0) { conditionETC+=" and a.npp_fk ='"+this.nppId+"'"; }
		if(vungId.length()>0) { conditionETC+= "  and a.npp_fk in (select pk_Seq  from nhaphanphoi  where khuvuc_fk in (select pk_seq from KHUVUC WHERE VUNG_FK='"+this.vungId+"' ))"; }
		if(this.spId.length()>0) { conditionETC+=" and C.sanpham_fk ='"+this.spId+"'"; }
		if(this.khId.length()>0) { conditionETC+=" and a.khachhang_Fk ='"+this.khId+"'"; }
		if(this.ttId.length()>0) { conditionETC+=" and a.npp_fk in (select pk_seq from nhaphanphoi  where tinhthanh_fk='"+this.ttId+"' ) "; }
		if(this.nhomId.length()>0) { conditionETC+=" and C.SanPham_fk in  (select sp_FK FROM NHOMSANPHAM_SANPHAM  where nsp_fk='"+this.nhomId+"' )  "; }
		if(this.kbhId.length()>0) { conditionETC+=" and a.pk_seq in (select hoadonNpp_fk from Erp_hoadonNpp_Ddh where ddh_fk in (select pk_seq from Erp_DonDatHangNpp where kbh_fk='"+this.kbhId+"' )) "; }
		if(this.ddkdId.length()>0) { conditionETC += " and a.khachhang_fk in (select khachhang_fk from KHACHHANG_TUYENBH where tbh_fk in (select pk_Seq from tuyenbanhang where ddkd_Fk ='"+this.ddkdId+"'))"; }
		
		String condition_CK="";
		if(this.flagnpp.equals("1")) { condition_CK=""; }
		else { condition_CK+= " and a.npp_fk in " + Ult.quyen_npp(userId)+""; }	
		if(nppId.length()>0) { condition_CK+= " and npp_fk='"+this.nppId+"' "; }
		if(ttId.length()>0) { condition_CK+= "  and a.npp_fk in (select pk_seq from nhaphanphoi  where tinhthanh_fk='"+this.ttId+"' )"; }
		if(vungId.length()>0) { condition_CK+= "  and a.npp_fk in (select pk_Seq  from nhaphanphoi  where khuvuc_fk in (select pk_seq from KHUVUC WHERE VUNG_FK='"+this.vungId+"' ))"; }
		if(khId.length()>0) { condition_CK+= " and khachhang_fk='"+this.khId+"' "; }
		if(tuNgay.length()>0) { condition_CK+= " and a.NgayXuatHD >='"+this.tuNgay+"' "; }
		if(denNgay.length()>0) { condition_CK+= " and a.NgayXuatHD <='"+this.denNgay+"' "; }
		if(spId.length()>0) { condition_CK+= " and a.pk_seq in  (select hoadon_fk from HOADON_SP where sanpham_fk='"+spId+"'  ) "; }
		if(nhomId.length()>0) { condition_CK+= " and a.pk_seq in  (select hoadon_fk from HOADON_SP where sanpham_fk in   (select sp_FK FROM NHOMSANPHAM_SANPHAM  where nsp_fk='"+this.nhomId+"' )   )  "; }
		if(this.kbhId.length()>0) { condition_CK+=" and a.pk_seq in (select hoadon_fk from hoadon_Ddh where ddh_fk in (select pk_seq from DonHang where kbh_fk='"+this.kbhId+"' )) "; }
		if(this.ddkdId.length()>0) { condition_CK += " and a.khachhang_fk in (select khachhang_fk from KHACHHANG_TUYENBH where tbh_fk in (select pk_Seq from tuyenbanhang where ddkd_Fk ='"+this.ddkdId+"'))"; }
		query="";
		if(this.action.length()>0)
		{
			if(this.xemtheo == -1) // xem theo trinh duoc vien
			{
				query=
				  /* " SELECT '0' as khma, tdvTen, spMa, spTen, SOLUONG, DONGIA, ( ISNULL([OTC],0) + ISNULL([ETC],0) )  AS DOANHSO, "+ 
				   " ( ISNULL([OTC],0) + ISNULL([ETC],0) + ISNULL([OTC_TRA],0) )  AS DOANHSOTRUTRALAI "+
				   " FROM  "+
				   " (  "+		
								   
				   " select d.tdvTen,d.spMa,d.spTen,SUM(d.SoLuong)as SoLuong, 0 as DonGia, loaihd, SUM(d.VAT) as VAT, SUM(d.BVAT) as BVAT,SUM(d.AVAT)as AVAT" + 
				   " from ( \n"+*/
				   " select v.TEN as vTen,tt.TEN as ttTEN,npp.MA as nppMa,npp.TEN as nppTEN,   \n "+      
				   " isnull(kh.maFAST,'') as khMa, isnull(KH.TEN,'') AS khTEN, isnull(kh.CHUCUAHIEU,'') as khChuCH, loai.ten as khLOAI, KH.DIACHI as khDIACHI,kh.MaHD as khMaHD ,   \n "+      
				   " HD.spMa, HD.spTen,SUM(SoLuong)as SoLuong,AVG(DonGia) as DonGia, SUM(BVAT)as DOANHSO, SUM(BVAT + BVAT_TRA) as DOANHSOTRUTRALAI \n "+  
			  	   " ,isnull((	\n	" +
			  	   " select top(1) c.ten	\n	" +
			  	   " from KHACHHANG_TUYENBH a inner join TUYENBANHANG b on a.TBH_FK=b.PK_SEQ	\n	" +
			  	   " inner join DAIDIENKINHDOANH c on c.PK_SEQ=b.DDKD_FK	\n	" +
			  	   " where a.khachhang_fk=kh.PK_SEQ	\n	" +
			  	   " ),'N/A') as tdvTen	\n	"  +
			  	   " from   \n "+      
			  	   " (   \n "+      
			  	   " 	select NPP_FK,KHACHHANG_FK,'OTC' as LoaiHD,hdOTC.spMa, hdOTC.spTen, hdOTC.SoLuong, 0 SOLUONG_TRA, hdOTC.DonGia ,hdOTC.BVAT, 0 BVAT_TRA \n "+      
			  	   "   	from   \n "+      
			  	   "   	(   \n "+      
			  	   "	  	   select a.KHACHHANG_FK,a.NPP_FK	,sum(round(b.SoLuong*b.DONGIA,0)) as BVAT,sum( round( round( b.SoLuong*b.DONGIA,0) *(1+ b.vat/100) ,0)) as AVAT ,  \n"+
			  	   "			sum( round( round( b.SoLuong*b.DONGIA,0) *(b.vat/100.0) ,0)) as VAT,  \n "+
			  	   " 			sp.MA as spMa, sp.TEN as spTen, \n "+
			  	   "				SUM(b.SoLuong)as SoLuong,AVG(b.DonGia) as DonGia     \n "+
			  	   "			from HOADON a inner join HOADON_SP b on b.HOADON_FK=a.PK_SEQ    \n "+
			  	   " 			left join SANPHAM sp on sp.PK_SEQ=b.SANPHAM_FK  \n " +
			  	   "			where a.LOAIHOADON =0 and a.TRANGTHAI not in (1,3,5) "+condition+" \n "+
			  	   "				group by a.NPP_FK ,A.KHACHHANG_FK,sp.MA,sp.TEN  "+
			  	   "   	)as hdOTC   \n "+      

					" UNION ALL "+
					" SELECT NPP_FK, KHACHHANG_FK, 'OTC_TRA' AS LOAIHD,SPMA, SPTEN, 0 SOLUONG, HDOTC.SOLUONG SOLUONG_TRA, HDOTC.DONGIA, 0 BVAT, HDOTC.BVAT BVAT_TRA "+
					" FROM  "+
					" (  "+
					" 	SELECT CONVERT(CHAR(7), A.NGAYTRA, 126) AS NGAYHD, ( SELECT TOP(1)DD.TEN FROM  KHACHHANG_TUYENBH BB  "+
					" 	INNER JOIN TUYENBANHANG CC ON BB.TBH_FK=CC.PK_SEQ  "+
					" 	INNER JOIN DAIDIENKINHDOANH DD ON CC.DDKD_FK=DD.PK_SEQ  "+
					" 	WHERE BB.KHACHHANG_FK=A.KHACHHANG_FK ) AS DDKD, A.KHACHHANG_FK,A.NPP_FK, "+
					" 	(-1)*SUM(ROUND(B.SOLUONG*B.DONGIA,0)) AS BVAT, (-1)*SUM( ROUND( ROUND( B.SOLUONG*B.DONGIA,0) *(1 + B.VAT/100) ,0)) AS AVAT,  "+
					" 	(-1)*SUM( ROUND( ROUND( B.SOLUONG*B.DONGIA,0) *(B.VAT/100.0) ,0)) AS VAT,  "+
					" 	(-1)*SUM(B.SOLUONG)AS SOLUONG,AVG(B.DONGIA) AS DONGIA, SP.MA SPMA, SP.TEN SPTEN "+
					" 	FROM ERP_HANGTRALAINPP A INNER JOIN ERP_HANGTRALAINPP_SANPHAM B ON B.HANGTRALAI_FK = A.PK_SEQ "+
					"   left join SANPHAM sp on sp.PK_SEQ=b.SANPHAM_FK "+  
					" 	WHERE A.TRANGTHAI = '1' "+ condition_tra +
					" 	GROUP BY A.NPP_FK, A.KHACHHANG_FK, A.NGAYTRA, SP.MA, SP.TEN "+
					" ) AS HDOTC "+
			  	   
			  	    " UNION ALL \n "+      
			  	    " select npp_fk,KHACHHANG_FK, \n "+      
			  	    " 'ETC' as LoaiHD,ETC.spMa, ETC.spTen, SUM(ETC.SoLuong)as SoLuong, 0 SOLUONG_TRA, AVG(ETC.DonGia) as DonGia,   \n "+      
			  	    "		sum( ROUND(soluong * dongia,0 ) - ck )  as BVAT, 0 BVAT_TRA "+
			  	    /*"		sum(ROUND(   ROUND ( soluong * dongia,0) - ck * thuexuat/100.0,0 )) as VAT,  "+ 
			  	    "		sum(ROUND( ( ROUND (soluong * dongia , 0 ) - ck )   * (1+ thuexuat/100.0),0 )) as AVAT   "+*/  	      
			  	    "   		from   \n "+      
			  	    "   		(   \n "+      
			  	    "   			select a.npp_fk, a.KHACHHANG_FK,   \n "+      
			  	    "   			case when c.donvitinh = e.donvi then c.soluong   \n "+      
			  	    "   			else c.soluong * ( select SOLUONG1 / SOLUONG2 from QUYCACH where sanpham_fk = c.sanpham_fk and DVDL2_FK = ( select pk_seq from DONVIDOLUONG where donvi = c.donvitinh ) and DVDL1_FK = d.DVDL_FK ) end as soluong,   \n "+      
			  	    "   			case when c.donvitinh = e.donvi then c.dongia   \n "+      
			  	    "   			else c.dongia * ( select SOLUONG2 / SOLUONG1 from QUYCACH where sanpham_fk = c.sanpham_fk and DVDL2_FK = ( select pk_seq from DONVIDOLUONG where donvi = c.donvitinh ) and DVDL1_FK = d.DVDL_FK ) end as dongia,   \n "+      
			  	    "   			c.vat as thuexuat , round(isnull(c.CHIETKHAU,0),0) as ck ,d.MA as spMa,d.TEN as spTen,e.DONVI as spDonVi   \n "+      
			  	    "   			from ERP_HOADONNPP a   \n "+      
			  	    "   			inner join ERP_HOADONNPP_SP c on a.pk_seq = c.hoadon_fk   \n "+      
			  	    "   			inner join SANPHAM d on c.sanpham_fk = d.pk_seq   \n "+      
			  	    "   			inner join DONVIDOLUONG e on d.DVDL_FK = e.pk_seq   \n "+      
			  	    "   			where 1=1 and a.trangthai not in ( 1 , 3, 5 ) and KHACHHANG_FK is not null "+conditionETC+"  \n "+      
			  	    "   		) ETC   \n "+      
			  	    "   		where soluong>0   \n "+      
			  	    "   		GROUP BY NPP_FK,KHACHHANG_FK,ETC.spMa,ETC.spTen   \n "+      
			  	    "      \n "+      
			  	    "   ) as HD   \n "+      
			  	    "   inner join NHAPHANPHOI npp on npp.PK_SEQ=HD.NPP_FK   \n "+      
			  	    "   left join KHUVUC kv on kv.PK_SEQ=npp.KHUVUC_FK   \n "+      
			  	    "   left join VUNG v on v.PK_SEQ=kv.VUNG_FK   \n "+      
			  	    "   left join KHACHHANG kh on kh.PK_SEQ=HD.KHACHHANG_FK   \n "+      
			  	    "   left join LOAIKHACHHANG loai on loai.pk_seq=kh.XuatKhau   \n "+      
			  	    "   left join TINHTHANH tt on tt.PK_SEQ=npp.TINHTHANH_FK   \n "+      
			  	    "   group by v.TEN ,tt.TEN ,npp.MA,npp.TEN , kh.maFAST ,KH.TEN ,KH.DIACHI,loai.ten ,kh.CHUCUAHIEU ,kh.MaHD, HD.spMa, HD.spTen, kh.PK_SEQ  \n ";
			  	   /* "	)as d	" +
			  	    "	group by d.tdvTen,d.spMa,d.spTen ";*/
				System.out.println("xemtheo "+ this.xemtheo +" : "+ query);
				this.queryHd=query;
				setTotal_Query("");
				this.hoadonRs= super.createSplittingData(super.getItems(), super.getSplittings(), " tdvTen ", query); 
				
			}
			else if(this.xemtheo == 1) // xem theo khach hang
			{
				query=
				    " select v.TEN as vTen,tt.TEN as ttTEN,npp.MA as nppMa,npp.TEN as nppTEN,   \n "+   
					" isnull(kh.maFAST,'') as khMa, isnull(KH.TEN,'') AS khTEN, isnull(kh.CHUCUAHIEU,'') as khChuCH, isnull(loai.ten,'') as khLOAI,KH.DIACHI as khDIACHI, isnull(kh.MaHD,'') as khMaHD ,   \n "+      
				    " HD.spMa, HD.spTen,SUM(SoLuong)as SoLuong,AVG(DonGia) as DonGia, SUM(BVAT)as DOANHSO, SUM(BVAT + BVAT_TRA)as DOANHSOTRUTRALAI \n "+  
			  	    " ,isnull(( \n " +
			  	    " select top(1) c.ten \n " +
			  	    " from KHACHHANG_TUYENBH a inner join TUYENBANHANG b on a.TBH_FK=b.PK_SEQ \n " +
			  	    " inner join DAIDIENKINHDOANH c on c.PK_SEQ=b.DDKD_FK \n" +
			  	    " where a.khachhang_fk=kh.PK_SEQ ),'N/A') as tdvTen \n	"  +
			  	    " from \n "+      
			  	    " ( \n "+      
			  	    "   	select NPP_FK,KHACHHANG_FK,'OTC' as LoaiHD,hdOTC.spMa, hdOTC.spTen, HDOTC.SOLUONG, 0 SOLUONG_TRA, HDOTC.DONGIA, HDOTC.BVAT, 0 AS BVAT_TRA \n "+      
			  	    "   	from \n "+      
			  	    "   	( \n "+      
			  	    "	  	select a.KHACHHANG_FK,a.NPP_FK	,sum(round(b.SoLuong*b.DONGIA,0)) as BVAT,sum( round( round( b.SoLuong*b.DONGIA,0) *(1+ b.vat/100) ,0)) as AVAT ,  \n"+
			  	    "		sum( round( round( b.SoLuong*b.DONGIA,0) *(b.vat/100.0) ,0)) as VAT,  \n "+
			  	    " 		sp.MA as spMa, sp.TEN as spTen, \n "+
			  	    "		SUM(b.SoLuong)as SoLuong,AVG(b.DonGia) as DonGia     \n "+
			  	    "		from HOADON a inner join HOADON_SP b on b.HOADON_FK=a.PK_SEQ    \n "+
			  	    " 		left join SANPHAM sp on sp.PK_SEQ=b.SANPHAM_FK  \n " +
			  	    "		where a.LOAIHOADON =0 and a.TRANGTHAI not in (1,3,5) "+condition+" \n "+
			  	    "		group by a.NPP_FK ,A.KHACHHANG_FK,sp.MA,sp.TEN  "+
			  	    "   	) as hdOTC \n "+    
			  	   
				    " 	UNION ALL \n "+    
					" 	select hdOTC_TRA.NPP_FK, KHACHHANG_FK, 'OTC_TRA' as LoaiHD, hdOTC_TRA.spma, hdOTC_TRA.spten, 0 SOLUONG, hdOTC_TRA.SOLUONG AS SOLUONG_TRA, hdOTC_TRA.DONGIA, 0 BVAT, hdOTC_TRA.BVAT AS BVAT_TRA "+   
					" 	from "+   
					" 	( "+   
					" 		select a.KHACHHANG_FK,a.NPP_FK, "+
					" 		(-1)*sum(round(b.SoLuong*b.DONGIA,0)) as BVAT, "+ 
					" 		(-1)*sum( round( round( b.SoLuong*b.DONGIA,0) *(1+ b.vat/100) ,0)) as AVAT, "+   
					" 		(-1)*sum( round( round( b.SoLuong*b.DONGIA,0) *(b.vat/100.0) ,0)) as VAT, "+
					"		sp.MA as spMa, sp.TEN as spTen, \n "+
					" 		(-1)*SUM(b.SoLuong)as SoLuong, AVG(b.DonGia) as DonGia "+ 
					" 		FROM ERP_HANGTRALAINPP A INNER JOIN ERP_HANGTRALAINPP_SANPHAM B ON B.HANGTRALAI_FK = A.PK_SEQ "+
					" 		left join SANPHAM sp on sp.PK_SEQ=b.SANPHAM_FK  \n " +
					" 		WHERE A.TRANGTHAI = '1' "+ condition_tra + 	 
					" 		group by a.NPP_FK, A.KHACHHANG_FK, sp.ma, sp.ten "+  
					" 	) AS hdOTC_TRA "+
			  	   
			  	   "   UNION ALL \n "+      
			  	   "   select npp_fk,KHACHHANG_FK,   \n "+      
			  	   "   'ETC' as LoaiHD,ETC.spMa, ETC.spTen, SUM(ETC.SOLUONG)AS SOLUONG, 0 SOLUONG_TRA, AVG(ETC.DONGIA) AS DONGIA, " +
			  	   "   SUM( ROUND(SOLUONG * DONGIA,0 ) - CK ) AS BVAT, 0 BVAT_TRA "+  	      
			  	   "   from \n "+      
			  	   "   (   \n "+      
			  	   "   		select a.npp_fk, a.KHACHHANG_FK,   \n "+      
			  	   "   		case when c.donvitinh = e.donvi then c.soluong   \n "+      
			  	   "   		else c.soluong * ( select SOLUONG1 / SOLUONG2 from QUYCACH where sanpham_fk = c.sanpham_fk and DVDL2_FK = ( select pk_seq from DONVIDOLUONG where donvi = c.donvitinh ) and DVDL1_FK = d.DVDL_FK ) end as soluong,   \n "+      
			  	   "   		case when c.donvitinh = e.donvi then c.dongia   \n "+      
			  	   "   		else c.dongia * ( select SOLUONG2 / SOLUONG1 from QUYCACH where sanpham_fk = c.sanpham_fk and DVDL2_FK = ( select pk_seq from DONVIDOLUONG where donvi = c.donvitinh ) and DVDL1_FK = d.DVDL_FK ) end as dongia,   \n "+      
			  	   "   		c.vat as thuexuat , round(isnull(c.CHIETKHAU,0),0) as ck ,d.MA as spMa,d.TEN as spTen,e.DONVI as spDonVi   \n "+      
			  	   "   		from ERP_HOADONNPP a   \n "+      
			  	   "   		inner join ERP_HOADONNPP_SP c on a.pk_seq = c.hoadon_fk   \n "+      
			  	   "   		inner join SANPHAM d on c.sanpham_fk = d.pk_seq   \n "+      
			  	   "   		inner join DONVIDOLUONG e on d.DVDL_FK = e.pk_seq   \n "+      
			  	   "   		where 1=1 and a.trangthai not in ( 1 , 3, 5 ) and KHACHHANG_FK is not null "+conditionETC+"  \n "+      
			  	   "   	) ETC   \n "+      
			  	   "   	where soluong>0   \n "+      
			  	   "   	GROUP BY NPP_FK,KHACHHANG_FK,ETC.spMa,ETC.spTen   \n "+      
			  	   "   ) as HD   \n "+      
			  	   "   inner join NHAPHANPHOI npp on npp.PK_SEQ=HD.NPP_FK   \n "+      
			  	   "   left join KHUVUC kv on kv.PK_SEQ=npp.KHUVUC_FK   \n "+      
			  	   "   left join VUNG v on v.PK_SEQ=kv.VUNG_FK   \n "+      
			  	   "   left join KHACHHANG kh on kh.PK_SEQ=HD.KHACHHANG_FK   \n "+      
			  	   "   left join LOAIKHACHHANG loai on loai.pk_seq=kh.XuatKhau   \n "+      
			  	   "   left join TINHTHANH tt on tt.PK_SEQ=npp.TINHTHANH_FK   \n "+      
			  	  "   group by v.TEN ,tt.TEN ,npp.MA,npp.TEN , kh.maFAST ,KH.TEN ,KH.DIACHI,loai.ten ,kh.CHUCUAHIEU ,kh.MaHD, HD.spMa, HD.spTen, kh.PK_SEQ  \n " ;
				//here2
				System.out.println("xemtheo "+ this.xemtheo +" : "+ query);
				this.queryHd=query;
				setTotal_Query(query);
				this.hoadonRs= super.createSplittingData(super.getItems(), super.getSplittings(), " khMa ", query); 
			}
			this.queryHd=query;
			setTotal_Query(query);
		}
		else { this.hoadonRs= super.createSplittingData(super.getItems(), super.getSplittings(), " 1 ", query); }
		System.out.println("query : "+ query);
		createRs();
	}
	
	public void xuatexcel(String search)
	{
		String query;
		
		String condition="";   
		if(this.flagnpp.equals("1"))
		{
			condition="";
		}
		else
		{
			condition+= " and a.npp_fk in " + Ult.quyen_npp(userId)+"";		 
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
			condition+=" and a.npp_fk in (select pk_seq	 from nhaphanphoi  where tinhthanh_fk='"+this.ttId+"' ) ";
		}
		
		if(this.nhomId.length()>0)
		{
			condition+=" and b.SanPham_fk in  (select sp_FK FROM NHOMSANPHAM_SANPHAM  where nsp_fk='"+this.nhomId+"' )  ";
		}
		
		if(this.kbhId.length()>0)
		{
			condition+=" and a.pk_seq in (select hoadon_fk from hoadon_Ddh where ddh_fk in (select pk_seq from DonHang where kbh_fk='"+this.kbhId+"' )) ";
		}
		
		if(this.ddkdId.length()>0)
		{
			condition += " and a.khachhang_fk in (select khachhang_fk from KHACHHANG_TUYENBH where tbh_fk in (select pk_Seq from tuyenbanhang where ddkd_Fk ='"+this.ddkdId+"'))";
		}
		
		String conditionETC=""; 
		
		if(this.flagnpp.equals("1"))
		{
			conditionETC="";
		}
		else
		{
		conditionETC+= " and a.npp_fk in " + Ult.quyen_npp(userId)+"";	
		}
		System.out.println("flag ne:"+this.flagnpp);
		
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
			conditionETC+=" and a.khachhang_Fk ='"+this.khId+"'";
		}
		
		if(this.ttId.length()>0)
		{
			conditionETC+=" and a.npp_fk in (select pk_seq from nhaphanphoi  where tinhthanh_fk='"+this.ttId+"' ) ";
		}
		
		if(this.nhomId.length()>0)
		{
			conditionETC+=" and C.SanPham_fk in  (select sp_FK FROM NHOMSANPHAM_SANPHAM  where nsp_fk='"+this.nhomId+"' )  ";
		}
		
		
		if(this.kbhId.length()>0)
		{
			conditionETC+=" and a.pk_seq in (select hoadonNpp_fk from Erp_hoadonNpp_Ddh where ddh_fk in (select pk_seq from Erp_DonDatHangNpp where kbh_fk='"+this.kbhId+"' )) ";
		}
		
		if(this.ddkdId.length()>0)
		{
			conditionETC += " and a.khachhang_fk in (select khachhang_fk from KHACHHANG_TUYENBH where tbh_fk in (select pk_Seq from tuyenbanhang where ddkd_Fk ='"+this.ddkdId+"'))";
		}
		
		
		System.out.println("flag ne:"+this.flagnpp);
		String condition_CK="";
		if(this.flagnpp.equals("1"))
		{
			condition_CK+="";
		}
		else
		condition_CK+= " and a.npp_fk in " + Ult.quyen_npp(userId)+"";
		if(nppId.length()>0)
		{
			condition_CK+= " and npp_fk='"+this.nppId+"' ";
		}
		
		if(ttId.length()>0)
		{
			condition_CK+= "  and a.npp_fk in (select pk_seq from nhaphanphoi  where tinhthanh_fk='"+this.ttId+"' )";
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
			condition_CK+= " and a.pk_seq in  (select hoadon_fk from HOADON_SP where sanpham_fk in   (select sp_FK FROM NHOMSANPHAM_SANPHAM  where nsp_fk='"+this.nhomId+"' )   )  ";
		}
		
		if(this.kbhId.length()>0)
		{
			condition_CK+=" and a.pk_seq in (select hoadon_fk from hoadon_Ddh where ddh_fk in (select pk_seq from DonHang where kbh_fk='"+this.kbhId+"' )) ";
		}
		
		if(this.ddkdId.length()>0)
		{
			condition_CK += " and a.khachhang_fk in (select khachhang_fk from KHACHHANG_TUYENBH where tbh_fk in (select pk_Seq from tuyenbanhang where ddkd_Fk ='"+this.ddkdId+"'))";
		}
		
		String conditionNK="";
		/*if(this.laynk.equals("1"))
		{
			conditionNK+=" and isnull(b.isnhapkhau,1)=1 ";
		}*/
		
		query="";
		
		
		if(this.action.length()>0)
		{
			if(this.xemtheo==-1) // xem theo trinh duoc vien
			{
				query=
						" select d.tdvTen,d.spMa,d.spTen,SUM(d.SoLuong)as SoLuong,0 as DonGia,SUM(d.VAT) as VAT, SUM(d.BVAT) as BVAT,SUM(d.AVAT)as AVAT,  COUNT(d.spTen) OVER (PARTITION BY tdvTen) as sosp  from ( \n"+
								"	select v.TEN as vTen,tt.TEN as ttTEN,npp.MA as nppMa,npp.TEN as nppTEN,   \n "+      
								"   kh.maFAST as khMa,KH.TEN AS khTEN,kh.CHUCUAHIEU as khChuCH,loai.ten as khLOAI,KH.DIACHI as khDIACHI,kh.MaHD as khMaHD ,   \n "+      
								"   HD.spMa, HD.spTen,SUM(SoLuong)as SoLuong,AVG(DonGia) as DonGia,SUM(AVAT)as AVAT,SUM(BVAT)as BVAT,SUM(VAT)as VAT   \n "+  
								
				  	   "	,isnull((	\n	" +
				  	   "		select top(1) c.ten	\n	" +
				  	   "		from KHACHHANG_TUYENBH a inner join TUYENBANHANG b on a.TBH_FK=b.PK_SEQ	\n	" +
				  	   "		inner join DAIDIENKINHDOANH c on c.PK_SEQ=b.DDKD_FK	\n	" +
				  	   "		where a.khachhang_fk=kh.PK_SEQ	\n	" +
				  	   "		),'N/A') as tdvTen	\n	"  +
				  	   
				  	   "   from   \n "+      
				  	   "   (   \n "+      
				  	   "   	select NPP_FK,KHACHHANG_FK,'OTC' as LoaiHD,hdOTC.spMa, hdOTC.spTen, hdOTC.SoLuong,hdOTC.DonGia ,hdOTC.BVAT, hdOTC.VAT ,hdOTC.AVAT   \n "+      
				  	   "   	from   \n "+      
				  	   "   	(   \n "+      
				  	   "	  	   select a.KHACHHANG_FK,a.NPP_FK	,sum(round(b.SoLuong*b.DONGIA,0)) as BVAT,sum( round( round( b.SoLuong*b.DONGIA,0) *(1+ b.vat/100) ,0)) as AVAT ,  \n"+
				  	   "			sum( round( round( b.SoLuong*b.DONGIA,0) *(b.vat/100.0) ,0)) as VAT,  \n "+
				  	   " 			sp.MA as spMa, sp.TEN as spTen, \n "+
				  	   "				SUM(b.SoLuong)as SoLuong,AVG(b.DonGia) as DonGia     \n "+
				  	   "			from HOADON a inner join HOADON_SP b on b.HOADON_FK=a.PK_SEQ    \n "+
				  	   " 			left join SANPHAM sp on sp.PK_SEQ=b.SANPHAM_FK  \n " +
				  	   "			where a.LOAIHOADON =0 and a.TRANGTHAI not in (1,3,5) "+condition+conditionNK+" \n "+
				  	   "				group by a.NPP_FK ,A.KHACHHANG_FK,sp.MA,sp.TEN  "+
				  	   "   	)as hdOTC   \n "+      
				  	   "      \n "+      
				  	   "      \n "+      
				  	   "   UNION ALL   \n "+      
				  	   "      \n "+      
				  	   "   		select npp_fk,KHACHHANG_FK,   \n "+      
				  	   "   		'ETC' as LoaiHD,ETC.spMa, ETC.spTen,SUM(ETC.SoLuong)as SoLuong,AVG(ETC.DonGia) as DonGia,   \n "+      
				  	   "		sum( ROUND(soluong * dongia,0 ) - ck )  as BVAT,   "+
				  	   "		sum(ROUND(   ROUND ( soluong * dongia,0) - ck * thuexuat/100.0,0 )) as VAT,  "+ 
				  	   "		sum(ROUND( ( ROUND (soluong * dongia , 0 ) - ck )   * (1+ thuexuat/100.0),0 )) as AVAT   "+  	      
				  	   "   		from   \n "+      
				  	   "   		(   \n "+      
				  	   "   			select a.npp_fk, a.KHACHHANG_FK,   \n "+      
				  	   "   			case when c.donvitinh = e.donvi then c.soluong   \n "+      
				  	   "   			else c.soluong * ( select SOLUONG1 / SOLUONG2 from QUYCACH where sanpham_fk = c.sanpham_fk and DVDL2_FK = ( select pk_seq from DONVIDOLUONG where donvi = c.donvitinh ) and DVDL1_FK = d.DVDL_FK ) end as soluong,   \n "+      
				  	   "   			case when c.donvitinh = e.donvi then c.dongia   \n "+      
				  	   "   			else c.dongia * ( select SOLUONG2 / SOLUONG1 from QUYCACH where sanpham_fk = c.sanpham_fk and DVDL2_FK = ( select pk_seq from DONVIDOLUONG where donvi = c.donvitinh ) and DVDL1_FK = d.DVDL_FK ) end as dongia,   \n "+      
				  	   "   			c.vat as thuexuat , round(isnull(c.CHIETKHAU,0),0) as ck ,d.MA as spMa,d.TEN as spTen,e.DONVI as spDonVi   \n "+      
				  	   "   			from ERP_HOADONNPP a   \n "+      
				  	   "   			inner join ERP_HOADONNPP_SP c on a.pk_seq = c.hoadon_fk   \n "+      
				  	   "   			inner join SANPHAM d on c.sanpham_fk = d.pk_seq   \n "+      
				  	   "   			inner join DONVIDOLUONG e on d.DVDL_FK = e.pk_seq   \n "+      
				  	   "   			where 1=1 and a.trangthai not in ( 1 , 3, 5 ) and KHACHHANG_FK is not null "+conditionETC+"  \n "+      
				  	   "   		) ETC   \n "+      
				  	   "   		where soluong>0   \n "+      
				  	   "   		GROUP BY NPP_FK,KHACHHANG_FK,ETC.spMa,ETC.spTen   \n "+      
				  	   "      \n "+      
				  	   /*   "   	UNION ALL   \n "+      
				  	   "   	   \n "+      
				  	   "   	select c.NPP_FK,c.KHACHHANG_FK,'OTC' as Loai,'' as spMA,'' as spTen,0 as SoLuong,0 as DonGia   \n "+      
				  	   "   		,-1*SUM(ROUND(a.chietkhau,0)) as BVAT,(-1)*SUM(ROUND(  ROUND( a.chietkhau,0)*(a.thuevat/100.0),0 ,0 )) as VAT,   \n "+      
				  	 	 "  (-1)*SUM(ROUND(  ROUND( a.chietkhau,0)*(1+ a.thuevat/100.0),0 ,0 )) as AVAT            \n "+
				  	   "   	from HOADON_CHIETKHAU a left join LoaiCK b on b.Ma=a.diengiai   \n "+      
				  	   "   		inner join HOADON c on c.PK_SEQ=a.hoadon_fk   \n "+      
				  	   "   		inner join NHAPHANPHOI npp on npp.pk_Seq= C .NPP_FK   \n "+      
				  	   "   		inner join KHACHHANG kh on kh.PK_SEQ= C.KHACHHANG_FK   \n "+      
				  	   "   		left join KHUVUC kv on kv.PK_SEQ=npp.KHUVUC_FK   \n "+      
				  	   "   		left join VUNG v on v.PK_SEQ=kv.VUNG_FK   \n "+      
				  	   "   		left join TINHTHANH tt on tt.PK_SEQ=npp.TINHTHANH_FK   \n "+      
				  	   "   		left join LOAIKHACHHANG loai on loai.pk_seq=kh.XuatKhau   \n "+      
				  	   "   	where    isnull(a.HIENTHI,0)=1 and  a.hoadon_fk in   \n "+      
				  	   "   	(   \n "+      
				  	   "   		select pk_Seq from HOADON a where isnull(LOAIHOADON,0)=0 and a.TRANGTHAI not IN (1,3,5)  "+condition_CK+"  \n "+      
				  	   "   	)   \n "+      
				  	   "   	group by c.NPP_FK,c.KHACHHANG_FK   \n "+      */
				  	   "      \n "+      
				  	   "   ) as HD   \n "+      
				  	   "   inner join NHAPHANPHOI npp on npp.PK_SEQ=HD.NPP_FK   \n "+      
				  	   "   left join KHUVUC kv on kv.PK_SEQ=npp.KHUVUC_FK   \n "+      
				  	   "   left join VUNG v on v.PK_SEQ=kv.VUNG_FK   \n "+      
				  	   "   left join KHACHHANG kh on kh.PK_SEQ=HD.KHACHHANG_FK   \n "+      
				  	   "   left join LOAIKHACHHANG loai on loai.pk_seq=kh.XuatKhau   \n "+      
				  	   "   left join TINHTHANH tt on tt.PK_SEQ=npp.TINHTHANH_FK   \n "+      
				  	   "   group by v.TEN ,tt.TEN ,npp.MA,npp.TEN , kh.maFAST ,KH.TEN ,KH.DIACHI,loai.ten ,kh.CHUCUAHIEU ,kh.MaHD, HD.spMa, HD.spTen, kh.PK_SEQ  \n " +
				  	   //"	order by kh.maFAST asc, HD.spMa desc 	";
				  	   "	)as d	" +
				  	   "	group by d.tdvTen,d.spMa,d.spTen	"+
				  	   "	order by d.tdvTen asc,d.spMa desc ";
				
				this.queryHd=query;
				setTotal_Query(query);
				//this.hoadonRs= super.createSplittingData(super.getItems(), super.getSplittings(), " tdvTen ", query); 
				
			}
			else if(this.xemtheo==1) // xem theo khach hang
			{
				query=
						"	select v.TEN as vTen,tt.TEN as ttTEN,npp.MA as nppMa,npp.TEN as nppTEN,   \n "+      
								"   kh.maFAST as khMa,KH.TEN AS khTEN,kh.CHUCUAHIEU as khChuCH,loai.ten as khLOAI,KH.DIACHI as khDIACHI,kh.MaHD as khMaHD ,   \n "+      
								"   HD.spMa, HD.spTen,SUM(SoLuong)as SoLuong,AVG(DonGia) as DonGia,SUM(AVAT)as AVAT,SUM(BVAT)as BVAT,SUM(VAT)as VAT   \n "+  
								
				  	   "	,isnull((	\n	" +
				  	   "		select top(1) c.ten	\n	" +
				  	   "		from KHACHHANG_TUYENBH a inner join TUYENBANHANG b on a.TBH_FK=b.PK_SEQ	\n	" +
				  	   "		inner join DAIDIENKINHDOANH c on c.PK_SEQ=b.DDKD_FK	\n	" +
				  	   "		where a.khachhang_fk=kh.PK_SEQ	\n	" +
				  	   "		),'N/A') as tdvTen	\n	"  +
				  	   
				  	   "   from   \n "+      
				  	   "   (   \n "+      
				  	   "   	select NPP_FK,KHACHHANG_FK,'OTC' as LoaiHD,hdOTC.spMa, hdOTC.spTen, hdOTC.SoLuong,hdOTC.DonGia ,hdOTC.BVAT, hdOTC.VAT ,hdOTC.AVAT   \n "+      
				  	   "   	from   \n "+      
				  	   "   	(   \n "+      
				  	   "	  	   select a.KHACHHANG_FK,a.NPP_FK	,sum(round(b.SoLuong*b.DONGIA,0)) as BVAT,sum( round( round( b.SoLuong*b.DONGIA,0) *(1+ b.vat/100) ,0)) as AVAT ,  \n"+
				  	   "			sum( round( round( b.SoLuong*b.DONGIA,0) *(b.vat/100.0) ,0)) as VAT,  \n "+
				  	   " 			sp.MA as spMa, sp.TEN as spTen, \n "+
				  	   "				SUM(b.SoLuong)as SoLuong,AVG(b.DonGia) as DonGia     \n "+
				  	   "			from HOADON a inner join HOADON_SP b on b.HOADON_FK=a.PK_SEQ    \n "+
				  	   " 			left join SANPHAM sp on sp.PK_SEQ=b.SANPHAM_FK  \n " +
				  	   "			where a.LOAIHOADON =0 and a.TRANGTHAI not in (1,3,5) "+condition+conditionNK+" \n "+
				  	   "				group by a.NPP_FK ,A.KHACHHANG_FK,sp.MA,sp.TEN  "+
				  	   "   	)as hdOTC   \n "+      
				  	   "      \n "+      
				  	   "      \n "+      
				  	   "   UNION ALL   \n "+      
				  	   "      \n "+      
				  	   "   		select npp_fk,KHACHHANG_FK,   \n "+      
				  	   "   		'ETC' as LoaiHD,ETC.spMa, ETC.spTen,SUM(ETC.SoLuong)as SoLuong,AVG(ETC.DonGia) as DonGia,   \n "+      
				  	   "		sum( ROUND(soluong * dongia,0 ) - ck )  as BVAT,   "+
				  	   "		sum(ROUND(   ROUND ( soluong * dongia- ck,0 )* thuexuat/100.0,0 )) as VAT,  "+ 
				  	   "		sum(ROUND( ( ROUND (soluong * dongia , 0 ) - ck )   * (1+ thuexuat/100.0),0 )) as AVAT   "+  	      
				  	   "   		from   \n "+      
				  	   "   		(   \n "+      
				  	   "   			select a.npp_fk, a.KHACHHANG_FK,   \n "+      
				  	   "   			case when c.donvitinh = e.donvi then c.soluong   \n "+      
				  	   "   			else c.soluong * ( select SOLUONG1 / SOLUONG2 from QUYCACH where sanpham_fk = c.sanpham_fk and DVDL2_FK = ( select pk_seq from DONVIDOLUONG where donvi = c.donvitinh ) and DVDL1_FK = d.DVDL_FK ) end as soluong,   \n "+      
				  	   "   			case when c.donvitinh = e.donvi then c.dongia   \n "+      
				  	   "   			else c.dongia * ( select SOLUONG2 / SOLUONG1 from QUYCACH where sanpham_fk = c.sanpham_fk and DVDL2_FK = ( select pk_seq from DONVIDOLUONG where donvi = c.donvitinh ) and DVDL1_FK = d.DVDL_FK ) end as dongia,   \n "+      
				  	   "   			c.vat as thuexuat , round(isnull(c.CHIETKHAU,0),0) as ck ,d.MA as spMa,d.TEN as spTen,e.DONVI as spDonVi   \n "+      
				  	   "   			from ERP_HOADONNPP a   \n "+      
				  	   "   			inner join ERP_HOADONNPP_SP c on a.pk_seq = c.hoadon_fk   \n "+      
				  	   "   			inner join SANPHAM d on c.sanpham_fk = d.pk_seq   \n "+      
				  	   "   			inner join DONVIDOLUONG e on d.DVDL_FK = e.pk_seq   \n "+      
				  	   "   			where 1=1 and a.trangthai not in ( 1 , 3, 5 ) and KHACHHANG_FK is not null "+conditionETC+"  \n "+      
				  	   "   		) ETC   \n "+      
				  	   "   		where soluong>0   \n "+      
				  	   "   		GROUP BY NPP_FK,KHACHHANG_FK,ETC.spMa,ETC.spTen   \n "+      
				  	   "      \n "+      
				  	   "   	UNION ALL   \n "+      
				  	   "   	   \n "+      
				  	   "   	select c.NPP_FK,c.KHACHHANG_FK,'OTC' as Loai,'' as spMA,'' as spTen,0 as SoLuong,0 as DonGia   \n "+      
				  	   "   		,-1*SUM(ROUND(a.chietkhau,0)) as BVAT,(-1)*SUM(ROUND(  ROUND( a.chietkhau,0)*(a.thuevat/100.0),0 ,0 )) as VAT,   \n "+      
				  	   "  (-1)*SUM(ROUND(  ROUND( a.chietkhau,0)*(1+ a.thuevat/100.0),0 ,0 )) as AVAT            \n "+
				  	   "   	from HOADON_CHIETKHAU a left join LoaiCK b on b.Ma=a.diengiai   \n "+      
				  	   "   		inner join HOADON c on c.PK_SEQ=a.hoadon_fk   \n "+      
				  	   "   		inner join NHAPHANPHOI npp on npp.pk_Seq= C .NPP_FK   \n "+      
				  	   "   		inner join KHACHHANG kh on kh.PK_SEQ= C.KHACHHANG_FK   \n "+      
				  	   "   		left join KHUVUC kv on kv.PK_SEQ=npp.KHUVUC_FK   \n "+      
				  	   "   		left join VUNG v on v.PK_SEQ=kv.VUNG_FK   \n "+      
				  	   "   		left join TINHTHANH tt on tt.PK_SEQ=npp.TINHTHANH_FK   \n "+      
				  	   "   		left join LOAIKHACHHANG loai on loai.pk_seq=kh.XuatKhau   \n "+      
				  	   "   	where    isnull(a.HIENTHI,0)=1 and  a.hoadon_fk in   \n "+      
				  	   "   	(   \n "+      
				  	   "   		select pk_Seq from HOADON a where isnull(LOAIHOADON,0)=0 and a.TRANGTHAI not IN (1,3,5)  "+condition_CK+"  \n "+      
				  	   "   	)   \n "+      
				  	   "   	group by c.NPP_FK,c.KHACHHANG_FK   \n "+      
				  	   "      \n "+      
				  	   "   ) as HD   \n "+      
				  	   "   inner join NHAPHANPHOI npp on npp.PK_SEQ=HD.NPP_FK   \n "+      
				  	   "   left join KHUVUC kv on kv.PK_SEQ=npp.KHUVUC_FK   \n "+      
				  	   "   left join VUNG v on v.PK_SEQ=kv.VUNG_FK   \n "+      
				  	   "   left join KHACHHANG kh on kh.PK_SEQ=HD.KHACHHANG_FK   \n "+      
				  	   "   left join LOAIKHACHHANG loai on loai.pk_seq=kh.XuatKhau   \n "+      
				  	   "   left join TINHTHANH tt on tt.PK_SEQ=npp.TINHTHANH_FK   \n "+      
				  	   "   group by v.TEN ,tt.TEN ,npp.MA,npp.TEN , kh.maFAST ,KH.TEN ,KH.DIACHI,loai.ten ,kh.CHUCUAHIEU ,kh.MaHD, HD.spMa, HD.spTen, kh.PK_SEQ  \n " ;
				
				
				setTotal_Query(query);
				query+="	order by kh.maFAST asc, HD.spMa desc 	";
				this.queryHd=query;
				//this.hoadonRs= super.createSplittingData(super.getItems(), super.getSplittings(), " khMa ", query); 
			}
							this.queryHd=query;
				setTotal_Query(query);
		}
		//this.hoadonRs= super.createSplittingData(super.getItems(), super.getSplittings(), " khMa ", query); 
		
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
		
		if(this.action.length()>0)
		{
			String	query=
					" select SUM(SoLuong)as SoLuong, AVG(DonGia) as DonGia, SUM(DOANHSO)as DOANHSO, SUM(DOANHSOTRUTRALAI) as DOANHSOTRUTRALAI, count(khMa) as slkh \n "+
					/*" select SUM(SoLuong)as SoLuong,AVG(DonGia) as DonGia,SUM(AVAT)as AVAT,SUM(BVAT)as BVAT,SUM(VAT)as VAT,count(khMa) as slkh \n "+*/      
					" from ( "+ searchquery +" ) as HD \n ";
			System.out.println("tong la "+query);
			this.totalRs= db.get(query);
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
	
	public int getNum()
	{
		return this.num;
	}
	
	public void setNum(int num)
	{
		this.num = num;
		listPages = PhanTrang.getListPages(num);
	}
	
	public int getCurrentPage()
	{
		return this.currentPages;
	}
	
	@Override
	public void setCurrentPage(int current) 
	{
		this.currentPages = current;
	}
	
	public int[] getListPages() 
	{
		return this.listPages;
	}
	
	public void setListPages(int[] listPages) 
	{
		this.listPages = listPages;
	}
	
	public int getLastPage() 
	{
		ResultSet rs = db.get("select count(*) as c from ERP_YEUCAUNGUYENLIEU");
		return PhanTrang.getLastPage(rs);
	}
	
	public int[] getNewPagesList(String action, int num, int currentPage, int theLastPage, String[] listPage)
	{
		IPhanTrang pt = new PhanTrang();
		return pt.getNewPagesList(action, num, currentPage, theLastPage, listPage);
	}
	@Override
	public int getxemtheo() {
		return this.xemtheo;
	}
	@Override
	public void setxemtheo(int so) {
		this.xemtheo=so;
	}
	
	public String getidnpp(String userid)
	{
		String sql="select nhaphanphoi.pk_seq,nhaphanphoi.ma,nhaphanphoi.diachi,nhaphanphoi.ten from nhaphanphoi inner join NHANVIEN on NHAPHANPHOI.SITECODE=NHANVIEN.CONVSITECODE where nhaphanphoi.trangthai=1  and isnull(isKHACHHANG,0)=0 and NHANVIEN.pk_seq="+this.userId;
		ResultSet rsnppid=this.db.get(sql);
		try {
			if(rsnppid.next())
			{
				try {
					return rsnppid.getString("pk_seq");
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			rsnppid.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return "";
	}
	
	public static void main(String args[]){
			dbutils db=new dbutils();
/*			String query="	declare @chuoi nvarchar(200) " +
						 "	set @chuoi=( " +
						 "	select " +
						 "	STUFF((Select ',['+thang+']' " +
						 "	from  uf_CacThangTrongKhoangThoiGian ('2015-01','2015-09') " +
						 "	FOR XML PATH('')),1,1,'') " +
						 "	) " +
						 "	select  @chuoi as thang";*/
			
			String query= "select *	from  uf_CacThangTrongKhoangThoiGian ('2015-01','2015-09') " ;
			
			String column="";
			String column_name="";
			ResultSet rs=db.get(query);
			try {
				while(rs.next())
				{
					column +="["+rs.getString("thang")+"],";
					column_name +="["+rs.getString("thang")+"] as DT_THANG_"+rs.getString("thang")+",";
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			System.out.println("colm la "+column);
			System.out.println("colm_name la "+column_name);
	}
	
	@Override
	public String getTpId() {
		// TODO Auto-generated method stub
		return this.tpId;
	}
	@Override
	public void setTpId(String tpId) {
		// TODO Auto-generated method stub
		this.tpId = tpId;
	}
	@Override
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
	public String getPhuongxa() {
		// TODO Auto-generated method stub
		return this.phuongxa;
	}
	@Override
	public void setPhuongxa(String pxa) {
		// TODO Auto-generated method stub
		this.phuongxa = pxa;
	}
	@Override
	public ResultSet getPhuongxaRs() {
		// TODO Auto-generated method stub
		return this.phuongxaRs;
	}
	
}
