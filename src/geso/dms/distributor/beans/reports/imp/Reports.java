package geso.dms.distributor.beans.reports.imp;

import java.io.Serializable;
import java.sql.ResultSet;

import geso.dms.center.db.sql.dbutils;
import geso.dms.center.util.Utility;
import geso.dms.distributor.beans.reports.Ireportnpp;

public class Reports implements Ireportnpp, Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -7536612550604652258L;
	String user;
	String userTen;
	String tungay;
	String denngay;
	String sku;
	ResultSet skur;
	dbutils db ;
	String NhappId;
	String KenhId;
	String NhanHangId;
	String LoaiHangId;
	String SanPhamId;
	String vungId;
	String kvId;
	ResultSet rs_kenh;
	ResultSet rs_npp;
	ResultSet rs_nhanhang;
	ResultSet rs_loaihang;
	ResultSet rs_sanpham;
	ResultSet rs_vung;
	ResultSet rs_khuvuc;
	ResultSet rs_cttb;
	String Loi;
	String cttbid;
	Utility util;
	public Reports ()
	{
		this.user = "";
		this.tungay = "";
		this.denngay = "";
		this.sku ="";
		this.SanPhamId="";
		this.KenhId="";
		this.NhanHangId="";
		this.LoaiHangId="";
		this.NhappId="";
		this.vungId = "";
		this.kvId = "";
		this.Loi="";
		this.util=new Utility(); 
		this.db = new dbutils();
	}
	public void setUser(String user) {
		this.user=user;
		
	}

	
	public String getUser() {
		
		return this.user;
	}
public void setTuNgay(String tungay) {
		
		this.tungay = tungay;
	}

	
	public String getTuNgay() {
		
		return this.tungay;
	}

	public void setcttbid(String _cttbid) {
		
		this.cttbid = _cttbid;
	}

	
	public String getcttbid() {
		
		return this.cttbid;
	}
	public void setDenNgay(String denngay) {
		
		this.denngay = denngay;
	}

	
	public String getDenNgay() {
		
		return this.denngay;
	}
	
	public void setSKU(String sku) {
		
		this.sku = sku;
	}
	
	public String getSKU() {
		
		return this.sku;
	}
	
	public void setSKUr(ResultSet SKUr) {
		this.skur = SKUr;
		
	}
	
	public ResultSet getSKUr() {
		
		return this.skur;
	}
	
	public void init() {
		
		this.skur = db.get("select pk_seq,ma,ten from sanpham");
	}
	
	public void setNppId(String npp_id) {
		
		this.NhappId=npp_id;
		
	}
	
	public String getNppId() {
		
		return this.NhappId;
	}
	
	public void setKenhId(String kenh_id) {
		
		this.KenhId=kenh_id;
	}
	
	public String getKenhId() {
		
		return this.KenhId;
	}
	
	public void setNhanHang(String nhanhang) {
		
		this.NhanHangId=nhanhang;
	}
	
	public String getNhanHang() {
		
		return this.NhanHangId;
	}
	
	public void setLoaiHang(String loaihang) {
		
		this.LoaiHangId=loaihang;
	}
	
	public String getLoaiHang() {
		
		return this.LoaiHangId;
	}
	
	public void setSanPham(String sanphamid) {
		
		this.SanPhamId=sanphamid;
	}
	
	public String getSanPhamId() {
		
		return this.SanPhamId;
	}
	
	
	String phanloai,loaiNv;
	String nppId,nppTen;
	public String getPhanloai()
  {
  	return phanloai;
  }
	public void setPhanloai(String phanloai)
  {
  	this.phanloai = phanloai;
  }
	public String getLoaiNv()
  {
  	return loaiNv;
  }
	public void setLoaiNv(String loaiNv)
  {
  	this.loaiNv = loaiNv;
  }
	public String getNppTen()
  {
  	return nppTen;
  }
	public void setNppTen(String nppTen)
  {
  	this.nppTen = nppTen;
  }
	public void CreateRsNpp() {
		
		Utility Ult = new Utility();
		String sql = "";

		try
		{

			sql = "select phanloai,LOAI from nhanvien where pk_seq=" + this.user;
			ResultSet rs = this.db.get(sql);
			if (rs != null)
			{
				if (rs.next())
				{
					this.phanloai = rs.getString("phanloai");
					loaiNv= rs.getString("LOAI")==null?"":rs.getString("LOAI");

					if (rs.getString("phanloai").equals("1")||( this.phanloai.equals("2")   && loaiNv.equals("3")   )  )
					{
						this.nppId = Ult.getIdNhapp(this.user);
						this.nppTen = Ult.getTenNhaPP();
					}
					rs.close();
				}
			}
			//System.out.println("sql : " + sql);
		} catch (Exception er)
		{

		}
		
		
		 sql="select pk_seq,ten from nhaphanphoi where trangthai='1' ";
		 
		 if(this.phanloai.equals("2")&& !loaiNv.equals("3"))
			{
				sql+= " and pk_Seq in " + util.quyen_npp(user)+"";
			}
			
		 
		if(this.kvId.length()>0)
			sql+=" and khuvuc_fk  in ("+this.kvId+") ";
		
		if(this.vungId.length()>0)
			sql+= " and khuvuc_fk in ( select pk_seq from khuvuc where vung_fk   in ("+vungId+"))";
		
		sql+=" order by ten ";
		
		this.rs_npp= this.db.get(sql);
	}
	
	public void CreateRsCTTB(){
		String sql="";
		//Lay nhung CTTB ma nha phan phoi do dang ky
		if(this.NhappId.trim().length()>0)
		 sql=
			"SELECT CT.PK_SEQ,SCHEME,DIENGIAI FROM CTTRUNGBAY CT" +
			" INNER JOIN DANGKYTRUNGBAY DK ON DK.CTTRUNGBAY_FK=CT.PK_SEQ"+
			" WHERE DK.NPP_FK='"+this.NhappId+"'"+
			" ORDER BY NGAYTAO DESC";
		//Lay nhung CTTB cua NPP ma nvien trung tam qua ly.
		else if(this.user.trim().length()>0)
			sql=
			"SELECT DISTINCT CT.PK_SEQ,SCHEME,DIENGIAI,NGAYTAO FROM CTTRUNGBAY CT" +
			" LEFT JOIN DANGKYTRUNGBAY DK ON DK.CTTRUNGBAY_FK=CT.PK_SEQ"+
			" WHERE DK.NPP_FK IN "+util.quyen_npp(user)+""+
			" ORDER BY NGAYTAO DESC";
		System.out.println("__CTTB__ "+sql);
		this.rs_cttb= this.db.get(sql);
	}
	public ResultSet getRsCTTB(){
		return this.rs_cttb;
	}
	public void CreateRsNhanHang() {
		
		String sql="select pk_seq,ten from nhanhang where trangthai='1'";
		this.rs_nhanhang= this.db.get(sql);
	}
	
	public void CreateRsLoaiHang() {
		
		String sql="select pk_seq,ten from chungloai where trangthai='1'";
		this.rs_loaihang= this.db.get(sql);
	}
	
	public void CreateRsSanPham() {
		
		String sql="select pk_seq,ten from sanpham where trangthai='1'";
		this.rs_sanpham= this.db.get(sql);
	}
	
	public void CreateRsKenh() {
		
		String sql="select pk_seq,ten from kenhbanhang where trangthai='1'";
		this.rs_kenh= this.db.get(sql);
	}
	
	public ResultSet getRsNhanHang() {
		
		return this.rs_nhanhang;
	}
	
	public ResultSet getRsNhaPP() {
		
		return this.rs_npp;
	}
	
	public ResultSet getRsLoaiHang() {
		
		return this.rs_loaihang;
	}
	
	public ResultSet getRsKenh() {
		
		return this.rs_kenh;
	}
	
	public ResultSet getRsSanPham() {
		
		return this.rs_sanpham;
	}
	
	public void setUserTen(String tenuser) {
		
		this.userTen=tenuser;
	}
	
	public String getTenUser() {
		
		return this.userTen;
	}
	
	public void setLoi(String loi) {
		
		this.Loi=loi;
	}
	
	public String getLoi() {
		
		return this.Loi;
	}
	
	public void DBclose() {
		
		try {

			if(this.rs_kenh != null)
				this.rs_kenh.close();
			if(this.rs_loaihang != null)
				this.rs_loaihang.close();
			if(this.rs_nhanhang != null)
				this.rs_nhanhang.close();
			if(this.rs_npp != null)
				this.rs_npp.close();
			if(this.rs_sanpham != null)
				this.rs_sanpham.close();
			if(this.rs_vung!=null){
				this.rs_vung.close();
			}
			if(this.rs_khuvuc!=null){
				this.rs_khuvuc.close();
			}
			if(this.rs_cttb!=null){
				this.rs_cttb.close();
			}
			if(this.skur!=null){
				this.skur.close();
			}
			if(this.db != null)
				this.db.shutDown();
		} catch (Exception e) {
			
		}
	}

	public void CreateRsVung() 
	{
		String sql="select pk_seq as vungId, ten as vungTen from vung where trangthai = '1' ";
		
		
		
		 sql+="order by ten asc ";
		 
		System.out.println("Lenh lay vung: " + sql + "\n");
		this.rs_vung = this.db.get(sql);
	}
	
	public void CreateRsKhuVuc()
	{
		String sql="select pk_seq as kvId, ten as kvTen from khuvuc where trangthai='1' ";
		
		if(this.vungId.length()>0)
			sql+=" and vung_fk in ("+this.vungId+")";
		
		sql+=" order by ten";
		
		System.out.println("Lenh lay khu vuc la: " + sql + "\n");
		this.rs_khuvuc = this.db.get(sql);
	}
	
	public ResultSet getRsVung() 
	{
		return this.rs_vung;
	}
	
	public ResultSet getRsKhuVuc() 
	{
		return this.rs_khuvuc;
	}
	
	public void setVungId(String vung_id) 
	{
		this.vungId = vung_id;
	}
	
	public String getVungId() {
		
		return this.vungId;
	}
	
	public void setKhuVucId(String kv_id) {
		
		this.kvId = kv_id;
	}
	
	public String getKhuVucId() 
	{
		return this.kvId;
	}
	
}
