package geso.dms.distributor.beans.donhang.imp;

import geso.dms.center.util.Phan_Trang;
import geso.dms.center.util.Utility;
import geso.dms.distributor.beans.donhang.IDonhang;
import geso.dms.distributor.beans.donhang.IDonhangList;
import geso.dms.distributor.db.sql.dbutils;

import java.io.Serializable;
import java.sql.ResultSet;
import java.util.List;



public class DonhangList extends Phan_Trang implements IDonhangList, Serializable
{
	private static final long serialVersionUID = -9217977546733610214L;
	
	public dbutils getDb() {
		return db;
	}
	
	String userId; //nppId
	String tungay;
	String denngay;
	String trangthai;
	String sohoadon;
	String khachhang;
	String msg;
	String mafast;
	String nppId;
	String nppTen;
	String sitecode;
	
	String nvgnId;
	
	List<IDonhang> dhlist;
	
	ResultSet daidienkd;
	String ddkdId;
	
	ResultSet dhRs;
	ResultSet nvgnRs;
	dbutils db;
	
	int currentPages;
	int[] listPages;
	String ngaygiotao;
	
	String sort = "";
	String dienthoai = "";
	
	String isPhanBoNVGN = "0";
	String PBdonhang_fk = "";
	String[] arrPBdonhang_fk;
	String trangthaipb = "";
	String pbid = "";
	
	

	//Constructor
	public DonhangList(String[] param)
	{
		this.tungay = param[0];
		this.denngay = param[1];
		this.trangthai = param[2];
		this.ddkdId = param[3];
		this.msg = "";
		this.mafast="";
		this.nvgnId="";
		this.ngaygiotao="";
		currentPages = 1;
		this.ttId="";
		this.qhId="";
		listPages = new int[]{1, 2 , 3 , 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20};
		
		this.db = new dbutils();
	}
	
	public DonhangList()
	{
		this.tungay = "";
		this.denngay = "";
		this.trangthai = "";
		this.ddkdId = "";
		this.sohoadon = "";
		this.khachhang = "";
		this.msg = "";
		this.mafast="";
		this.nvgnId="";
		this.ngaygiotao="";
		currentPages = 1;
		this.ttId="";
		this.qhId="";
		listPages = new int[]{1, 2 , 3 , 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20};
		
		this.db = new dbutils();
	}
	
	public String getPbid() {
		return pbid;
	}
	public void setPbid(String pbid) {
		this.pbid = pbid;
	}
	public String getTrangthaipb() {
		return trangthaipb;
	}
	public void setTrangthaipb(String trangthaipb) {
		this.trangthaipb = trangthaipb;
	}
	public String[] getArrPBdonhang_fk() {
		return arrPBdonhang_fk;
	}
	public void setArrPBdonhang_fk(String[] arrPBdonhang_fk) {
		this.arrPBdonhang_fk = arrPBdonhang_fk;
	}
	public String getPBdonhang_fk() {
		return PBdonhang_fk;
	}
	public void setPBdonhang_fk(String pBdonhang_fk) {
		PBdonhang_fk = pBdonhang_fk;
	}
	public String getIsPhanBoNVGN() {
		return isPhanBoNVGN;
	}
	public void setIsPhanBoNVGN(String isPhanBoNVGN) {
		this.isPhanBoNVGN = isPhanBoNVGN;
	}
	public String getSort() {
		return sort;
	}
	public void setSort(String sort) {
		this.sort = sort;
	}
	public String getDienthoai() {
		return dienthoai;
	}
	public void setDienthoai(String dienthoai) {
		this.dienthoai = dienthoai;
	}
	public String getUserId()
	{
		return this.userId;
	}

	public void setUserId(String userId) 
	{
		this.userId = userId;
	}
	
	public ResultSet getDaidienkd() 
	{	
		return this.daidienkd;
	}
	
	public void setDaidienkd(ResultSet daidienkd) 
	{
		this.daidienkd = daidienkd;		
	}
	
	public String getDdkdId()
	{	
		return this.ddkdId;
	}
	
	public void setDdkdId(String ddkdId) 
	{
		this.ddkdId = ddkdId;		
	}

	public String getTrangthai() 
	{	
		return this.trangthai;
	}
	
	public void setTrangthai(String trangthai) 
	{
		this.trangthai = trangthai;	
	}

	public String getTungay()
	{	
		return this.tungay;
	}
	
	public void setTungay(String tungay) 
	{
		this.tungay = tungay;		
	}
	
	public String getDenngay() 
	{		
		return this.denngay;
	}

	public void setDenngay(String denngay) 
	{
		this.denngay = denngay;		
	}
	
	public String getNppId() 
	{
		return this.nppId;
	}

	public void setNppId(String nppId) 
	{
		this.nppId = nppId;
	}
	
	public String getNppTen() 
	{
		return this.nppTen;
	}

	public void setNppTen(String nppTen) 
	{
		this.nppTen = nppTen;
	}
	
	public String getSitecode() 
	{
		return this.sitecode;
	}

	public void setSitecode(String sitecode) 
	{
		this.sitecode = sitecode;
	}
	
	public void createDdkd()
	{
		//this.daidienkd = db.get("select ten as ddkdTen, pk_seq as ddkdId from daidienkinhdoanh where npp_fk = '" + this.nppId +"'");
		
		this.daidienkd = db.get("select ten as ddkdTen, pk_seq as ddkdId from daidienkinhdoanh where pk_seq in ( select ddkd_fk from DAIDIENKINHDOANH_NPP where npp_fk = '" + this.nppId + "' ) ");
	}
	
	public void createnvgn()
	{
		this.nvgnRs = db.get("select * from nhanviengiaonhan where npp_fk = '" + this.nppId +"'");
	}
	
	private void getNppInfo()
	{
		geso.dms.distributor.util.Utility util = new geso.dms.distributor.util.Utility();
		this.nppId=util.getIdNhapp(this.userId,this.db);
		this.nppTen=util.getTenNhaPP();
		this.sitecode=util.getSitecode();
	}
	
	public void createDhBeanList(String query)
	{
		this.dhRs = super.createSplittingData(super.getItems(), super.getSplittings(), "ngaynhap desc, trangthai asc", query); //db.get(query);
	}
	
	public void init(String search) 
	{
		this.getNppInfo();
		//Utility util = new Utility();
		String query = "";	
		if (search.length() == 0)
		{

			
			query = " select isnull(a.ghichu, '') as ghichu, a.synced,isnull(a.ngaytaodh,a.ngaygio) as ngaygio,a.pk_seq as dhId, a.ngaynhap, a.trangthai, a.ngaytao, a.ngaysua, d.maFAST, isnull(a.DAXUATHOADON,0) as DAXUATHOADON , isnull(DAIN, '0') as DAINDH,     " +
					"			c.ten as nguoisua, d.ten as khTen, d.pk_seq as khId, e.pk_seq as ddkdId, (select top (1)'T'+CAST(tb.NGAYID as nvarchar(2))+ '_'+ dd.ten from tuyenbanhang tb inner join khachhang_tuyenbh khtb on tb.pk_seq=khtb.tbh_fk "+ 
					" inner join daidienkinhdoanh dd on dd.pk_seq = tb.ddkd_fk where khtb.khachhang_fk=d.pk_seq and dd.PK_SEQ = a.DDKD_FK ) as ddkdTen        " +
					"		,	'' as nppTen, a.tonggiatri as tonggiatri, d.THANHTOAN, a.VAT " +
					", ' ' as nguoitao, 0 as exitPXK ,     " +
					" 	isnull( a.sohoadon, '' ) AS SoHoaDon  "+
					" from donhang a   " +
					" inner  join nhanvien c on a.nguoisua = c.pk_seq inner join khachhang d on a.khachhang_fk = d.pk_seq        " +
					" left join daidienkinhdoanh e on a.ddkd_fk = e.pk_seq  " +
					" where a.npp_fk = '" + this.nppId + "' and a.trangthai != '2' ";//and a.kho_fk in "+util.quyen_kho(this.userId) ;
		
				if(nppId.equals("106218")||nppId.equals("106243")||nppId.equals("106233") || nppId.equals("106244"))
				{
					query+=" and  d.QUANHUYEN_FK in ( select QUANHUYEN_FK from NhanVien_QuanHuyen where nhanvien_fk ='"+this.userId+"') ";	
				}
				
				//Thêm một biến, phân bổ nvgn dùng chung init với đơn hàng, nếu là phân bổ thì lấy query riêng
				if (this.isPhanBoNVGN.equals("1")) {
							
					query = "\n select * from (" +
					"\n select " +
					"\n		case " +
					"\n		 when exists (select 1 from PhanBo_NVGN_CT where donhang_fk = a.pk_seq and not exists (select 1 from PhanBo_NVGN_CTsp where donhang_fk = a.pk_seq) ) then -1 " +
					"\n		 when exists (select 1 from PhanBo_NVGN_CTSP where donhang_fk = a.pk_seq and exists ( " +
					"\n		 	select 1 from (  " +
					"\n				select sum(soluong) soluong,sanpham_fk,donhang_fk from PhanBo_NVGN_CTsp   " +
					"\n				where donhang_Fk = a.pk_seq " +
					"\n				group by sanpham_fk,donhang_fk ) x inner join   " +
					"\n				(select sanpham_fk, sum(soluong)soluong,donhang_fk from (  " +
					"\n				select sanpham_fk, soluong, donhang_fk from donhang_sanpham where donhang_Fk = a.pk_seq " +
					"\n				union all   " +
					"\n				select bbb.pk_seq,soluong,donhangid donhang_fk from donhang_ctkm_trakm aaa inner join sanpham bbb on aaa.spma = bbb.ma   " +
					"\n				where donhangid = a.pk_seq) bb  " +
					"\n				group by sanpham_fk,donhang_fk  " +
					"\n				) y  " +
					"\n				on x.sanpham_fk = y.sanpham_fk and x.donhang_fk = y.donhang_fk where x.soluong-y.soluong != 0  " +
					"\n		 	)) then 1   " +
					"\n when " +
					"\n exists (select 1 from PhanBo_NVGN_CTSP where donhang_fk = a.pk_seq) " +
					"\n and not exists (select 1 from PhanBo_NVGN_CTSP where donhang_fk = a.pk_seq and exists (  " +
					"\n select 1 from (   " +
					"\n 	select sum(soluong) soluong,sanpham_fk,donhang_fk from PhanBo_NVGN_CTsp    " +
					"\n 	where donhang_Fk = a.pk_seq  " +
					"\n 	group by sanpham_fk,donhang_fk ) x inner join    " +
					"\n 	(select sanpham_fk, sum(soluong)soluong,donhang_fk from (   " +
					"\n 	select sanpham_fk, soluong, donhang_fk from donhang_sanpham where donhang_Fk = a.pk_seq  " +
					"\n 	union all    " +
					"\n 	select bbb.pk_seq,soluong,donhangid donhang_fk from donhang_ctkm_trakm aaa inner join sanpham bbb on aaa.spma = bbb.ma    " +
					"\n 	where donhangid = a.pk_seq) bb   " +
					"\n 	group by sanpham_fk,donhang_fk   " +
					"\n 	) y   " +
					"\n 	on x.sanpham_fk = y.sanpham_fk and x.donhang_fk = y.donhang_fk where x.soluong-y.soluong != 0   " +
					"\n )) then 2  " +
					"\n		 	else 0 end trangthaigh,  " +
					"\n isnull((select top 1 phanbo_fk from PhanBo_NVGN_CT where donhang_fk = a.pk_seq),0)pbid, " +
					"\n isnull((select top 1 ten from nhanviengiaonhan where pk_seq = a.nvgn_fk),'') nvgnten1, " +
					"\n case when exists (select top 1 nvgn_fk from PhanBo_NVGN_CT where donhang_fk = a.pk_seq) " +
					"\n then isnull((select ten from nhanviengiaonhan where pk_seq = (select top 1 nvgn_fk from PhanBo_NVGN_CT where donhang_fk = a.pk_seq) ),'') else " +
					"\n 	isnull((select ten from nhanviengiaonhan where pk_seq = a.nvgn_fk),'') end nvgn, " +
					"\n 	d.diachigiaohang khdiachi, case when a.trangthai = 0  and a.synced != 1 then 1 when a.synced = 1 then 2 else 3 end tmp_sort,isnull(d.daduyet,0)daduyet, a.synced,isnull(a.ngaytaodh,a.ngaygio) as ngaygio,a.pk_seq as dhId, a.ngaynhap, a.trangthai, a.ngaytao, a.ngaysua, d.maFAST, isnull(a.DAXUATHOADON,0) as DAXUATHOADON , isnull(DAIN, '0') as DAINDH,     " +
					"\n 			c.ten as nguoisua, d.ten as khTen, d.pk_seq as khId, e.pk_seq as ddkdId, (select top (1)'T'+CAST(tb.NGAYID as nvarchar(2))+ '_'+ dd.ten from tuyenbanhang tb inner join khachhang_tuyenbh khtb on tb.pk_seq=khtb.tbh_fk "+ 
					"\n  inner join daidienkinhdoanh dd on dd.pk_seq = tb.ddkd_fk where khtb.khachhang_fk=d.pk_seq and dd.PK_SEQ = a.DDKD_FK ) as ddkdTen        " +
					"\n 		,	'' as nppTen, a.tonggiatri as tonggiatri, d.THANHTOAN, a.VAT " +
					"\n , ' ' as nguoitao, 0 as exitPXK ,     " +
					"\n  	isnull( a.sohoadon, '' ) AS SoHoaDon  "+
					"\n  from donhang a   " +
					"\n  inner  join nhanvien c on a.nguoisua = c.pk_seq inner join khachhang d on a.khachhang_fk = d.pk_seq        " +
					"\n  left join daidienkinhdoanh e on a.ddkd_fk = e.pk_seq  " +
					"\n  where a.npp_fk = '" + this.nppId + "' and a.trangthai != '2' and a.trangthai != 0 ) donhang " +
					"\n	 where 1=1 ";
					
					if (this.trangthaipb != null && this.trangthaipb.equals("new")) {
						query += "\n and trangthaigh != -1 and trangthaigh != 2 " +
						"\n and not exists (select 1 from phanbo_nvgn_ct a where donhang_fk = donhang.dhid and not exists " +
						"\n	 (select 1 from phanbo_nvgn_ctsp where phanbo_fk = a.phanbo_fk and donhang_fk = a.donhang_fk)) ";
					}
					else if (this.trangthaipb != null && this.trangthaipb.equals("update")) {
						query += "\n and (not exists (select 1 from phanbo_nvgn_ct where phanbo_fk != "+this.pbid+
								 "\n 	and donhang_fk = donhang.dhid and (trangthaigh = -1 or trangthaigh =2)) " +
								 "\n or exists (select 1 from phanbo_nvgn_ct where phanbo_fk = "+this.pbid+
								 "\n	and donhang_fk = donhang.dhid and trangthaigh = 2) ) ";
					}
					//query += "order by ngaynhap desc";
				}
					
		}
		else
		{
			String temp = "";
			if (this.trangthaipb != null && this.trangthaipb.length() > 0) {
					if  (this.arrPBdonhang_fk != null && this.arrPBdonhang_fk.length > 0)
					{
						for (int i = 0; i<this.arrPBdonhang_fk.length;i++)
						{
							if (temp.equals(""))
								temp = this.arrPBdonhang_fk[i];
							else
								temp += ","+this.arrPBdonhang_fk[i];
						}
					}
					if (temp.length() > 0)
						query = search + "\n or dhid in ("+temp+")"; 
					else
						query = search;
			}
			else {
				query = search + " and a.npp_fk = '" + this.nppId + "' ";
			}
		}
		System.out.println("LAY DON HANG----------------olala: " + query);
		this.createDhBeanList(query); 
		
		this.createDdkd();
		this.createnvgn();
		
	 query = "Select pk_seq, ten from tinhthanh where pk_seq in ( select Distinct TINHTHANH_FK from KHACHHANG where pk_seq in ( select khachhang_fk from khachhang_npp where npp_fk ='" + this.nppId + "') ) order by ten ";
	 this.ttRs=db.get(query);
		 
	 query = "Select pk_seq, ten from quanhuyen where pk_seq in ( select Distinct QUANHUYEN_FK from KHACHHANG where pk_seq in ( select khachhang_fk from khachhang_npp where npp_fk ='" + this.nppId + "') )  ";
	 if(this.ttId.length()>0)
	 {
		 query+=" and tinhthanh_fk='"+this.ttId+"'";
	 }
	 query+=" order by TEN ";
	 
		this.qhRs=this.db.get(query);
		
	
	}

	public void DBclose() 
	{		
		try 
		{
			if(!(this.daidienkd == null))
				this.daidienkd.close();
			if(dhRs!=null){
				dhRs.close();
			}
			if(nvgnRs!=null){
				nvgnRs.close();
			}
			if(db != null)
				this.db.shutDown();
			
		} 
		catch(Exception e) {}
	}
	
	public int getCurrentPage() 
	{
		return this.currentPages;
	}

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
		ResultSet rs = db.get("select count(*) as skh from khachhang");
		try 
		{
			rs.next();
			int count = Integer.parseInt(rs.getString("skh"));
			rs.close();
			return count;
		}
		catch(Exception e) {}
		
		return 0;
	}

	public String getSohoadon() 
	{
		return this.sohoadon;
	}

	public void setSohoadon(String sohoadon) 
	{
		this.sohoadon = sohoadon;
	}

	public String getKhachhang() 
	{
		return this.khachhang;
	}

	public void setKhachhang(String khachhang)
	{
		this.khachhang = khachhang;
	}

	public ResultSet getDonhangRs() 
	{
		return this.dhRs;
	}

	public void setDonhangRs(ResultSet dhRs) 
	{
		this.dhRs = dhRs;
	}

	public String getMsg() 
	{
		return this.msg;
	}

	public void setMsg(String msg) 
	{
		this.msg = msg;
	}

	
	public String getMafast() {
	
		return this.mafast;
	}

	
	public void setMafast(String mafast) {
		this.mafast=mafast;
		
	}
	double tongTruoc = 0 ;
	double tongCK = 0;
	double tongSau = 0;
	
	public void getSumBySearch(String sumqr) {
		if(isSearch){
		ResultSet rs = db.get(sumqr);
			try 
			{
				rs.next();
				this.tongTruoc = rs.getDouble("TONGTRUOCCK");
				this.tongCK = rs.getDouble("TONGCK");
				this.tongSau = rs.getDouble("TONGSAUCK");
				rs.close();
			}
			catch(Exception e) {}
		}
		else{
			this.tongTruoc = 0;
			this.tongCK = 0;
			this.tongSau = 0;
			System.out.print("loi errororrrrrrrrrrrrrrrrrrrrrrr");
		}
	}

	
	public double getTongTruoc() {
		return this.tongTruoc;
	}

	
	public double getTongCK() {
		return this.tongCK;
	}

	
	public double getTongSau() {
		return this.tongSau;
	}
	boolean isSearch = false;
	
	public boolean getIsSearch() {
		return this.isSearch;
	}

	
	public void setIsSearch(boolean search) {
		this.isSearch = search;
	}

	
	public String getnvgnId() {
		
		return this.nvgnId;
	}

	
	public void setnvgnId(String nvgnId) {
		
		this.nvgnId=nvgnId;
	}

	
	public ResultSet getnvgnRs() {
		
		return this.nvgnRs;
	}

	
	public void setnvgnRs(ResultSet nvgnRs) {
		
		this.nvgnRs=nvgnRs;
	}
	
	String ttId,qhId;
	ResultSet ttRs,qhRs;

	public String getTtId()
  {
  	return ttId;
  }

	public void setTtId(String ttId)
  {
  	this.ttId = ttId;
  }

	public String getQhId()
  {
  	return qhId;
  }

	public void setQhId(String qhId)
  {
  	this.qhId = qhId;
  }

	public ResultSet getTtRs()
  {
  	return ttRs;
  }

	public void setTtRs(ResultSet ttRs)
  {
  	this.ttRs = ttRs;
  }

	public ResultSet getQhRs()
  {
  	return qhRs;
  }

	public void setQhRs(ResultSet qhRs)
  {
  	this.qhRs = qhRs;
  }

	public String getNgaygiotao() {
		return ngaygiotao;
	}

	public void setNgaygiotao(String ngaygiotao) {
		this.ngaygiotao = ngaygiotao;
	}	
}

