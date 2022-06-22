package geso.dms.distributor.beans.hoadontaichinh.imp;

import java.sql.ResultSet;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import geso.dms.center.util.Utility;
import geso.dms.distributor.beans.hoadontaichinh.IBCBangCanDoiPhatSinhCongNo;
import geso.dms.distributor.db.sql.dbutils;

public class BCBangCanDoiPhatSinhCongNo implements IBCBangCanDoiPhatSinhCongNo
{ 
	private static final long serialVersionUID = 1L;
	String userId;
	String tungay;
	String denngay;
	String trangthai;
	String ptVAT;
	String query;
	String sohoadontu;
	String sohoadonden;
	String khId;
	String nppID;
	String nppTen;
	String msg;
	String activeTab;
	
	ResultSet nppRs;
	ResultSet hoadonRs;
	ResultSet EtcRs;
	ResultSet OtcRs;
	ResultSet kmRs;
	
	ResultSet doitacRs;
	
	String khTen;
	ResultSet khRs;
	String nppId;
	
	String nvbhId;
	ResultSet nvbhRs;
	String maFAST;
	
	String queryexcel;
	String sohoadon;
	String loaidonhang;
	String vungId;
	String khuvucId;
	ResultSet khuvuc;
	ResultSet vung;
	
	ResultSet ttRs;
	String ttId="";
	String type;
	
	String doitacId;
	String doitacHOId;
	
	dbutils db;
	
	String chungloaiId = "";
	ResultSet chungloaiRs ;
	
	public BCBangCanDoiPhatSinhCongNo()
	{
		this.tungay = "";
		this.denngay = "";
		
		this.nppTen = "";
		this.trangthai = "";
		this.msg = "";
		this.loaidonhang = "0";
	    this.khTen= "";
	    this.nppId="";
	    this.sohoadon="";
	    this.ptVAT = "";
	    this.khId="";
	    this.activeTab="0";
	    
	    this.nvbhId = "";
	    this.maFAST = "";
	    
	    this.sohoadontu = "";
	    this.sohoadonden = "";		
	    this.nvbhId="";
	    this.nvgnId="";
	    this.ddkdId="";
		this.vungId="";
		this.khuvucId ="";
		this.ttId="";
		this.doitacId = "";
		this.doitacHOId = "";
		this.type  = "0";
		this.db = new dbutils();
	}

	public String getNppId()
	{
		return this.nppId;
	}

	public void setNppId(String nppId) 
	{
		this.nppId = nppId;
	}
	
	public String getUserId()
	{
		return this.userId;
	}

	public void setUserId(String userId) 
	{
		this.userId = userId;
	}
	
	public String getTrangthai()
	{
		return this.trangthai;
	}

	public void setTrangthai(String trangthai) 
	{
		this.trangthai = trangthai;
	}
	
	public String getMsg() 
	{
		return this.msg;
	}

	public void setMsg(String msg) 
	{
		this.msg = msg;
	}

	
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



	String phanloai="";
	String loaiNv="";

	
	Utility Ult = new Utility();
	private void getNppInfo()
	{		

		String sql = "";
		try
		{

			sql = "select phanloai,LOAI from nhanvien where pk_seq=" + this.userId;
			ResultSet rs = this.db.get(sql);
			if (rs != null)
			{
				if (rs.next())
				{
					this.phanloai = rs.getString("phanloai");
					loaiNv= rs.getString("LOAI")==null?"":rs.getString("LOAI");

					if (rs.getString("phanloai").equals("1")||( this.phanloai.equals("2")   && loaiNv.equals("3")   )  )
					{
						this.nppId = Ult.getIdNhapp(this.userId);
						this.nppTen = Ult.getTenNhaPP();
					}
					rs.close();
				}
			}
		} catch (Exception er)
		{

		}
	
	}
	
	public void init()
	{
		getNppInfo();
		
		String query = "";
       
		if(this.tungay.trim().length() > 0 && this.denngay.trim().length() > 0  )
		{			
			int thangTRUOC = Integer.parseInt(this.tungay);
			int namTRUOC = Integer.parseInt(this.denngay);
			if(thangTRUOC == 1)
			{
				thangTRUOC = 12;
				namTRUOC = namTRUOC - 1;
			}
			else
			{
				thangTRUOC = thangTRUOC - 1;
			}
			
			if( thangTRUOC == 6 && namTRUOC == 2014 )
			{
				query = "select dsTHANGTRUOC.*,  " +
						"	case loaikhachhang when 0 then 0 else isnull(TL.tongCHIETKHAU, 0) end as banBUON, " +
						"	case loaikhachhang when 0 then isnull(TL.tongCHIETKHAU, 0) else 0 end as banLE " +
						"from " +
						"( " +
						"	select khachhang_fk, a.loaikhachhang, maFAST, ten, tongthucthu  " +
						"	from TICHLUYQUY_DAUKY a inner join KHACHHANG b on a.khachhang_fk = b.pk_seq " +
						") " +
						"dsTHANGTRUOC left join   " +
						"(  " +
						"	 select khachhang_fk, sum(thanhtoan) as tongCHIETKHAU   " +
						"	 from DUYETTRAKHUYENMAI_DONHANG  " +
						"	 where tichluyQUY = '0'   " +
						"				and donhang_fk in ( select ddh_fk from HOADON_DDH " +
						"									where hoadon_fk in ( select pk_seq from HOADON where  trangthai not in ( 1 , 3, 5 ) and month(ngayxuatHD) = '" + this.tungay + "' and year(ngayxuatHD) <= '" + this.denngay + "' and isnull(loaihoadon, '0') = '0' " ;
					
					if(this.nppId.length()>0)
					{
						query+=" and npp_fk='"+nppId+"'";  
					}
					if(this.phanloai.equals("2")&& !loaiNv.equals("3"))
					{
						query+= " and npp_fk in " + Ult.quyen_npp(userId)+"";
					}
					
					query+=
								
								"  )  ) " +
						"	 group by khachhang_fk  " +
						" )  " +
						" TL on dsTHANGTRUOC.khachhang_fk = TL.khachhang_fk  ";
			}
			else
			{
				query = "select dsTHANGTRUOC.*,  " +
						"	case loaikhachhang when 0 then 0 else isnull(TL.tongCHIETKHAU, 0) end as banBUON, " +
						"	case loaikhachhang when 0 then isnull(TL.tongCHIETKHAU, 0) else 0 end as banLE " +
						"from " +
						"( " +
						"	select khachhang_fk, loaikhachhang, maFAST, ten, sum( tongtiensauVAT ) as tongtiensauVAT " +
						"	from " +
						"	( " +
						"		select a.khachhang_fk, c.loaikhachhang, d.maFAST, d.ten,   " +
						"			(   select cast( sum( (soluong * giamua -  ( soluong * giamua * cast( chietkhau * 100 / ( soluong * giamua ) as numeric(18, 0) ) / 100 ) ) * ( 1 + thueVAT / 100 ) ) as numeric(18, 0) )           " +
						"				from donhang_sanpham           " +
						"				where donhang_fk = c.pk_seq          " +
						"				group by donhang_fk ) as tongtiensauVAT " +
						"		from HOADON a inner join HOADON_DDH b on a.pk_seq = b.hoadon_fk  " +
						"			inner join DONHANG c on b.ddh_fk = c.pk_seq  " +
						"			inner join KHACHHANG d on a.khachhang_fk = d.pk_seq  " +
						"		where   ISNULL(a.loaihoadon, 0) = 0 AND a.trangthai not in ( 1, 3, 5 )  " +
						"				and month(a.ngayxuatHD) = '" + thangTRUOC + "' and year(a.ngayxuatHD) = '" + namTRUOC + "'  " ;
							
						
						if(this.nppId.length()>0)
						{
							query+=" and  a.npp_fk = '" + this.nppId + "' ";  
						}
						if(this.phanloai.equals("2")&& !loaiNv.equals("3"))
						{
							query+= " and  a.npp_fk in " + Ult.quyen_npp(userId)+"";
						}
						
					query+=		
						"	) " +
						"	ds	 " +
						"	group by khachhang_fk, loaikhachhang, maFAST, ten " +
						") " +
						"dsTHANGTRUOC left join   " +
						"(  " +
						"	 select khachhang_fk, sum(thanhtoan) as tongCHIETKHAU   " +
						"	 from DUYETTRAKHUYENMAI_DONHANG  " +
						"	 where tichluyQUY = '0'   " +
						"				and donhang_fk in ( select ddh_fk from HOADON_DDH " +
						"									where hoadon_fk in ( select pk_seq from HOADON where   trangthai not in ( 1 , 3, 5 ) and month(ngayxuatHD) = '" + this.tungay + "' and year(ngayxuatHD) <= '" + this.denngay + "' and isnull(loaihoadon, '0') = '0' " ;
								
						if(this.nppId.length()>0)
						{
							query+=" and  npp_fk = '" + this.nppId + "' ";  
						}
						if(this.phanloai.equals("2")&& !loaiNv.equals("3"))
						{
							query+= " and npp_fk in " + Ult.quyen_npp(userId)+"";
						}
						
						query+=		
						"  )  ) " +
						"	 group by khachhang_fk  " +
						" )  " +
						" TL on dsTHANGTRUOC.khachhang_fk = TL.khachhang_fk  ";
			}
		
			System.out.println("---INIT CHIET KHAU THANG: " + query);
			this.hoadonRs = db.get(query);
			
		}
	}
	
	public void DBclose() 
	{
		this.db.shutDown();
	}

	public ResultSet getHoadonRs() 
	{
		return this.hoadonRs;
	}

	public void setHoadonRs(ResultSet hdRs) 
	{
		this.hoadonRs = hdRs;
	}

	
	public String getTungay() {
		
		return this.tungay;
	}

	
	public void setTungay(String tungay) {
		
		this.tungay = tungay;
	}

	
	public String getDenngay() {
		
		return this.denngay;
	}

	
	public void setDenngay(String denngay) {
		
		this.denngay = denngay;
	}

	
	public String getNppTen() {
		
		return this.nppTen;
	}

	
	public void setNppTen(String nppTen) {
		
		this.nppTen = nppTen;
	}

	
	public ResultSet getNppRs() {
		
		return this.nppRs;
	}

	
	public void setNppRs(ResultSet nppRs) {
		
		this.nppRs = nppRs;
	}

	
	public String getLoaidonhang() {

		return this.loaidonhang;
	}


	public void setLoaidonhang(String loaidonhang) {
		
		this.loaidonhang = loaidonhang;
	}

	
	public String getKhTen() {
		return this.khTen;
	}

	
	public void setKhTen(String KhTen) {
		this.khTen = KhTen;
		
	}

	
	public ResultSet getKhRs() {
		return this.khRs;
	}

	
	public void setKhRs(ResultSet KhRs) {
		this.khRs = KhRs;
		
	}
	
	public String getSoHoaDon()
	{
		return this.sohoadon;
	}
	public void setSoHoaDon(String sohoadon)
	{
		this.sohoadon =sohoadon;
	}

	private String getDateTime()
	{
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date date = new Date();
        return dateFormat.format(date);	
	}

	
	public String getPtVat() {

		return this.ptVAT;
	}


	public void setPtVat(String ptVat) {
		
		this.ptVAT = ptVat;
	}

	
	public String getNvbhId() {
		
		return this.nvbhId;
	}

	
	public void setNvbhId(String nvbhId) {
		
		this.nvbhId = nvbhId;
	}

	
	public ResultSet getNvbhRs() {
		
		return this.nvbhRs;
	}

	
	public void setNvbhRs(ResultSet nvbhRs) {
		
		this.nvbhRs = nvbhRs;
	}

	
	public String getMaFast() {
		
		return this.maFAST;
	}

	
	public void setMaFast(String maFAST) {
		
		this.maFAST = maFAST;
	}


	public void createRs() 
	{
		getNppInfo();

		String query="";		
		
		query="select PK_SEQ,TEN ,DIACHI from nhanviengiaonhan where 1=1  ";
		if(this.nppId.length()>0)
		{
			query+=" and npp_fk='"+nppId+"' ";
		}
		if(this.phanloai.equals("2")&& !loaiNv.equals("3"))
		{
			query+= " and npp_fk in " + Ult.quyen_npp(userId)+"";
		}
		this.nvgnRs=this.db.get(query);
		
		query="select PK_SEQ,TEN ,DIACHI from daidienkinhdoanh where 1=1  ";
		if(this.nppId.length()>0)
		{
			query+=" and pk_seq in (select ddkd_fk from DAIDIENKINHDOANH_NPP where npp_fk='"+this.nppId+"' )  ";
		}
		if(this.phanloai.equals("2")&& !loaiNv.equals("3"))
		{
			query+= " and pk_seq in " + Ult.Quyen_Ddkd(userId)+"";
		}
		this.ddkdRs=this.db.get(query);
		
		this.vung = db.get("select pk_seq,ten,diengiai from vung ");
		
		if (this.vungId.length() > 0)
		{			
			this.khuvuc = db.get("select pk_seq,ten from khuvuc where vung_fk ='" + this.vungId + "'");
		} else
		{
			String query_khuvuc=" select PK_SEQ, TEN from KHUVUC "
					+ "	where PK_SEQ in (select KHUVUC_Fk from NHAPHANPHOI "
					+ "	where pk_seq in "+ Ult.quyen_npp(userId)+")"; 			
			this.khuvuc= db.get(query_khuvuc);		
		}
		
		query="select pk_Seq,ten,ma from nhaphanphoi where iskhachhang=0 and trangthai=1 ";

		if(this.phanloai.equals("2")&& !loaiNv.equals("3"))
		{
				query+= " and pk_Seq in " + Ult.quyen_npp(userId)+"";
		}
		if (this.khuvucId.length() > 0)
		{
			query = query + " and khuvuc_fk ='" + this.khuvucId + "'";
		}
		if (this.vungId.length() > 0)
		{
			query = query + " and khuvuc_fk in (select pk_seq from khuvuc where vung_fk ='" + this.vungId + "')";
		}
		this.nppRs = this.db.get(query);
		
		query="select  kh.PK_SEQ, kh.MAFAST + ', ' + kh.TEN AS TEN from KHACHHANG kh inner join NHAPHANPHOI npp on kh.NPP_FK=npp.PK_SEQ where 1=1";
		
		if(this.nppId.trim().length()>0)
			query+=	" and kh.NPP_FK = '" + this.nppId + "' ";
		
		if(this.doitacHOId.trim().length()>0)
			query+=	" and kh.NPP_FK = '" + this.doitacHOId + "' ";
		
		if (this.khuvucId.length() > 0)
			query = query + " and npp.khuvuc_fk ='" + this.khuvucId + "'";
		
		if (this.vungId.length() > 0)
			query = query + " and npp.khuvuc_fk in (select pk_seq from khuvuc where vung_fk ='" + this.vungId + "')";
		
		this.khRs = db.get(query);
		
		query = "select PK_SEQ,TEN from tinhthanh   where 1=1 ";
		if(vungId.length()>0)
			query+=" and vung_fk='"+vungId+"'";;
		
		this.ttRs= this.db.get(query);
		
		query =	"	select PK_SEQ,  isnull(maFAST, '') + ', ' + TEN as TEN  " +
				"	from NHAPHANPHOI where TRANGTHAI = '1' ";
		if(nppId.trim().length()>0)
			query += " and tructhuoc_fk = '" + this.nppId + "' ";
		else
			query += " and iskhachhang=0 ";
		
		System.out.println("DOITAC "+query);
		this.doitacRs = this.db.get(query);
		
		
		query = " select pk_seq ,ten from chungloai   ";
		this.chungloaiRs  = db.get(query);
			
	}
	
	public String getFormatDate(String date) 
	{
		String[] arr = date.split("-");
		
		return arr[2] + "-" + arr[1] + "-" + arr[0];
	}


	public String getActiveTab() {
	
		return this.activeTab;
	}

	
	public void setActiveTab(String active) {
		this.activeTab= active ;
		
	}

	
	public void setQuery(String searchQuery) {
	 this.query = searchQuery;
		
	}

	public void searchQuery_ETC(String searchquery) {
		String sql = "";

		if(searchquery.length() > 0)
			sql = searchquery;	
		this.EtcRs= db.get(sql);
		System.out.println("[Danh sach ETC] :" + sql);
	}

	public void searchQuery_OTC(String searchquery) {
		String sql = "";

		if(searchquery.length() > 0)
			sql = searchquery;	
		this.OtcRs= db.get(sql);
		System.out.println("[Danh sach OTC] :" + sql);
		
	}

	public void searchQuery_KM(String searchquery) {
		String sql = "";

		if(searchquery.length() > 0)
			sql = searchquery;	
		this.kmRs= db.get(sql);
		System.out.println("[Danh sach KM] :" + sql);
		
	}

	public ResultSet getETCRs() {
	
		return this.EtcRs;
	}


	public void setETCRS(ResultSet ETCRs) {
		this.EtcRs= ETCRs;
		
	}


	public ResultSet getOTCRs() {
		
		return this.OtcRs;
	}

	public void setOTCRS(ResultSet OTCRs) {
		this.OtcRs = OTCRs;
	}


	public ResultSet getKMRs() {

		return this.kmRs;
	}


	public void setKMRS(ResultSet KMRs) {
		this.kmRs= KMRs;
		
	}
	
	public String getKhId() {
		
		return this.khId;
	}

	
	public void setKhId(String KhId) {
		
		this.khId=KhId;
	}

	
	public String getnppId() {
		
		return this.nppId;
	}

	
	public void setnppId(String nppID) {
		
		this.nppId=nppID;
	}

	String nvgnId,ddkdId;
	ResultSet nvgnRs,ddkdRs;

	public String getNvgnId()
  {
  	return nvgnId;
  }

	public void setNvgnId(String nvgnId)
  {
  	this.nvgnId = nvgnId;
  }

	public String getDdkdId()
  {
  	return ddkdId;
  }

	public void setDdkdId(String ddkdId)
  {
  	this.ddkdId = ddkdId;
  }

	public ResultSet getNvgnRs()
  {
  	return nvgnRs;
  }

	public void setNvgnRs(ResultSet nvgnRs)
  {
  	this.nvgnRs = nvgnRs;
  }

	public ResultSet getDdkdRs()
  {
  	return ddkdRs;
  }

	public void setDdkdRs(ResultSet ddkdRs)
  {
  	this.ddkdRs = ddkdRs;
  }

	
	public void setvungId(String vungId) {
		
		this.vungId=vungId;
	}

	
	public String getvungId() {
		
		return this.vungId;
	}

	
	public void setvung(ResultSet vung) {
		
		this.vung=vung;
	}

	
	public ResultSet getvung() {
		
		return this.vung;
	}

	
	public void setkhuvucId(String khuvucId) {
		
		this.khuvucId=khuvucId;
	}

	
	public String getkhuvucId() {
		
		return this.khuvucId;
	}

	
	public void setkhuvuc(ResultSet khuvuc) {
		
		this.khuvuc=khuvuc;
	}

	
	public ResultSet getkhuvuc() {
		
		return this.khuvuc;
	}

	
	public ResultSet getTtRs() {
		
		return this.ttRs;
	}

	
	public void setTtRs(ResultSet ttRs) {
		
		this.ttRs=ttRs;
	}

	
	public String getTtId() {
		
		return this.ttId;
	}

	
	public void setTtId(String ttId) {
		
		this.ttId=ttId;
	}

	
	public ResultSet getDoiTacRs() {
		
		return this.doitacRs;
	}

	
	public void setDoiTacRs(ResultSet DoiTacRs) {
		
		this.doitacRs = DoiTacRs;
	}

	
	public String getDoiTacId() {
		
		return this.doitacId;
	}

	
	public void setDoiTacId(String doitacId) {
		
		this.doitacId = doitacId;
	}

	
	public void settype(String type) {
		
		this.type = type;
	}

	
	public String gettype() {
		
		return this.type;
	}

	

public String getBCTheoKH() {
		

	
		String with_condition =   "\n with kh as " +
						 "\n( 	" +
						 "\n	select * from KHACHHANG kh  where 1=1 " ;
		if(khId.trim().length()>0)
			with_condition +="\n 		and kh.PK_SEQ='"+khId+"'";
		else if(ddkdId.trim().length()>0)
			with_condition += 	"\n		  and kh.PK_SEQ in " +
								"\n		  						( "+
								"\n			 						SELECT  c.KHACHHANG_FK "+ 
								"\n									FROM 	DAIDIENKINHDOANH a INNER JOIN TUYENBANHANG b on b.DDKD_FK=a.PK_SEQ "+   			
								"\n											inner join KHACHHANG_TUYENBH c on c.TBH_FK=b.PK_SEQ "+
								"\n									WHERE a.PK_SEQ ='"+ddkdId+"'  "+
								"\n		  						)";
		
		
		if(this.nvgnId.trim().length() > 0)
    	{
			with_condition += "  and kh.pk_seq in ( select KHACHHANG_FK from NVGN_KH where nvgn_fk = '" + this.nvgnId + "' ) \n";
    	}
		if(nppId.trim().length()>0)
    		with_condition +="\n			 and kh.NPP_FK='"+nppId+"'";
		else
	    	if(this.vungId.trim().length()>0)
	    	{
	    		with_condition+=" and kh.npp_fk in (select pk_Seq  from nhaphanphoi  where khuvuc_fk in (select pk_seq from KHUVUC WHERE VUNG_FK='"+this.vungId+"' ))";
	    	}
    		
    	if(this.ttId.trim().length() > 0 )//địa bàn
    	{
    		with_condition+=" and kh.NPP_FK in ( select pk_seq from nhaphanphoi  where tinhthanh_fk = '" + ttId + "' ) \n";
    	}
    	
    	if(this.doitacHOId.trim().length() >0)
    		with_condition += " and kh.NPP_FK = '" + this.doitacHOId + "' \n";
		
		
		
    	
    	if(this.chungloaiId.trim().length() > 0)
    		with_condition +=					 "\n), cl as (select* from chungloai where pk_seq =  "+this.chungloaiId+" )  " ;
    	else
    		with_condition +=					 "\n), cl as (select* from chungloai where 1=1 )  " ;
		
	String query = 		
						 "\n 	SELECT	(   " + 
						 "\n 			SELECT  top(1) isnull(a.TEN,'') DDKD   " + 
						 "\n 			FROM 	DAIDIENKINHDOANH a INNER JOIN TUYENBANHANG b on b.DDKD_FK = a.PK_SEQ      " + 
						 "\n 			INNER JOIN KHACHHANG_TUYENBH c on c.TBH_FK = b.PK_SEQ  			WHERE c.KHACHHANG_FK = kh.PK_SEQ   " + 
						 "\n 		) as [NVBH]   " + 
						 "\n  		, kh.maFAST[MÃ KH], kh.TEN[KHÁCH HÀNG],kh.DIACHI [ĐIẠ CHỈ],cl.TEN [CHỦNG LOẠI]     " + 
						 "\n 		,isnull(dndk.tongtienAVAT, 0) as [DƯ NỢ ĐẦU KỲ],  isnull(dcdk.SOTIENTT, 0) as  [DƯ CÓ ĐẦU KỲ]   " + 
						 "\n 		,isnull(psntk.tongtienAVAT, 0) as [PHÁT SINH NỢ], isnull(psctk.sotienTT, 0)  as [PHÁT SINH CÓ]  " +
						 "\n		, isnull(dndk.tongtienAVAT, 0)+ isnull(psntk.tongtienAVAT, 0)  [DƯ NỢ CUỐI KỲ]      " +
						 "\n		, isnull(psctk.sotienTT, 0) +  isnull(dcdk.SOTIENTT, 0)   [DƯ CÓ CUỐI KỲ]     " + 
						 "\n   " + 
						 "\n FROM kh  " + 
						 "\n inner join  cl on 1=1  " + 
						 "\n LEFT JOIN       " + 
						 "\n  (       " + 
						 "\n  	SELECT psn_dk.PK_SEQ KHACHHANG_FK, psn_dk.NPP_FK,chungloai_fk , SUM(ISNULL(psn_dk.tongtienAVAT,0.0)) as tongtienAVAT       " + 
						 "\n  	FROM       " + 
						 "\n  	(	      " + 
						 "\n  		SELECT psn.PK_SEQ, psn.NPP_FK,psn.chungloai_fk , sum(tongtienAVAT) as tongtienAVAT       " + 
						 "\n  		FROM       " + 
						 "\n  		(       " + 
						 "\n  			--- hoadon  " + 
						 "\n  			SELECT hd.KHACHHANG_FK PK_SEQ, hd.NPP_FK, hdcl.chungloai_fk, SUM(round(isnull(hdcl.sotien,0),0)) tongtienAVAT       " + 
						 "\n  			FROM HOADON hd     " + 
						 "\n  			inner join HOADON_CHUNGLOAI hdcl on hd.PK_SEQ = hdcl.hoadon_fk    " + 
						 "\n  			INNER JOIN	 kh on hd.KHACHHANG_FK = kh.PK_SEQ       " + 
						 "\n  			WHERE 1=1 and  hd.LOAIHOADON = 0 and hd.TRANGTHAI not in ( 1, 3, 5 ) and hd.NGAYXUATHD < '" + this.tungay + "'              " + 
						 "\n 				and hdcl.chungloai_fk in ( select pk_Seq from cl   ) 	   " + 
						 "\n  			 GROUP BY hd.KHACHHANG_FK,hd.NPP_FK  , hdcl.chungloai_fk   					    " + 
						 "\n  			 --- hoa don etc   " + 
						 "\n  			UNION ALL       " + 
						 "\n  			SELECT hd.khachhang_fk, hd.NPP_FK ,hdcl.chungloai_fk,sum(round(hdcl.sotien,0)) tongtienavat   " + 
						 "\n  			FROM ERP_HOADONNPP hd 	    " + 
						 "\n  			inner join ERP_HOADONNPP_CHUNGLOAI hdcl on hd.PK_SEQ = hdcl.hoadon_fk	and hdcl.chungloai_fk in ( select pk_Seq from cl   ) 	   " + 
						 "\n  			inner join kh on kh.PK_SEQ = hd.KHACHHANG_FK  " + 
						 "\n  			WHERE hd.trangthai not in ( 1, 3, 5 ) and hd.NgayXuatHD <'" + this.tungay + "'             " + 
						 "\n  			GROUP BY hd.khachhang_fk, hd.NPP_FK  ,hdcl.chungloai_fk   " + 
						 "\n  			union all  " + 
						 "\n 			-- phieu chi  no 131  " + 
						 "\n 			select kh.PK_SEQ, pc.NPP_FK, cl.PK_SEQ, sum(pcct.tiennt)  " + 
						 "\n 			from  Erp_PhieuChi pc   " + 
						 "\n 			inner join Erp_PhieuChi_CHITIET pcct on pc.PK_SEQ = pcct.PhieuChi_Fk  " + 
						 "\n 			inner join kh  on kh.maFAST = pcct.dtno  " + 
						 "\n 			inner join CHUNGLOAI cl on cl.TEN = pcct.ytno  " + 
						 "\n 			where  pc.TRANGTHAI  = 1   and pc.NGAYCHUNGTU <'" + this.tungay + "'  and pcct.TKNO = '131'   " + 
						 "\n  			GROUP BY   kh.PK_SEQ, pc.NPP_FK, cl.PK_SEQ  " + 
						 "\n  			union all  " + 
						 "\n  			-- but toan tong hop no 131  " + 
						 "\n  			select  kh.PK_SEQ, pc.NPP_FK, cl.PK_SEQ, sum(pcct.tiennt)  " + 
						 "\n 			from  ERP_Buttoantonghop pc   " + 
						 "\n 			inner join ERP_BUTTOANTONGHOP_CHITIET pcct on pc.PK_SEQ = pcct.BUTTOANTONGHOP_FK  " + 
						 "\n 			inner join kh  on kh.maFAST = pcct.dtno  " + 
						 "\n 			inner join CHUNGLOAI cl on cl.TEN = pcct.ytno  " + 
						 "\n 			where  pc.TRANGTHAI  = 1   and pc.NGAYBUTTOAN <'" + this.tungay + "'  and pcct.TKNO = '131'   " + 
						 "\n  			GROUP BY   kh.PK_SEQ, pc.NPP_FK, cl.PK_SEQ  " + 
						 "\n  		)       " + 
						 "\n  		psn       " + 
						 "\n  		GROUP BY psn.PK_SEQ , psn.NPP_FK ,psn.chungloai_fk        " + 
						 "\n  	)       " + 
						 "\n  	psn_dk       " + 
						 "\n  	GROUP BY psn_dk.PK_SEQ , psn_dk.NPP_FK ,chungloai_fk     " + 
						 "\n  )      " + 
						 "\n  dndk on kh.PK_SEQ = dndk.KHACHHANG_FK and cl.PK_SEQ = dndk.chungloai_fk    " + 
						 "\n  LEFT JOIN       " + 
						 "\n  (       " + 
						 "\n  	SELECT 	psc.KHACHHANG_FK, psc.NPP_FK,psc.CHUNGLOAI_FK ,round(SUM (isnull(psc.SOTIENTT,0)),0) as SOTIENTT       " + 
						 "\n  	FROM       " + 
						 "\n  	(       " + 
						 "\n  		--- thu tien theo HD  " + 
						 "\n  		SELECT tthd.KHACHHANG_FK ,tt.NPP_FK,tthd.CHUNGLOAI_FK, SUM(round(ISNULL(tthd.sotienTT,0),0)) as sotienTT     			    " + 
						 "\n 		FROM ERP_THUTIENNPP tt   " + 
						 "\n 		inner join ERP_THUTIENNPP_HOADON_CHITIET tthd on tt.PK_SEQ = tthd.THUTIENNPP_FK     			    " + 
						 "\n 		inner join kh  on kh.PK_SEQ = tt.KHACHHANG_FK  " + 
						 "\n 		WHERE tt.TRANGTHAI = 1 and tt.NGAYCHUNGTU < '" + this.tungay + "' and tthd.LOAIHD = 0      " + 
						 "\n 			and tthd.CHUNGLOAI_FK in ( select pk_Seq from cl   ) 	   " + 
						 "\n 		GROUP BY tthd.KHACHHANG_FK, tt.NPP_FK   ,tthd.CHUNGLOAI_FK       " + 
						 "\n  		UNION ALL	     " + 
						 "\n  		-- hang tra lai   " + 
						 "\n  		SELECT 	tttl.KHACHHANG_FK, tttl.NPP_FK,cl.chungloai_fk , SUM(round(isnull(cl.sotien,0),0)) as SOTIENTT 		      " + 
						 "\n  		FROM   	Erp_HangTraLaiNpp  tttl	  " + 
						 "\n  		inner join kh  on kh.PK_SEQ = tttl.KHACHHANG_FK			     " + 
						 "\n 		inner join Erp_HangTraLaiNpp_ChungLoai cl on tttl.pk_seq = cl.Hangtralai_fk   " + 
						 "\n  		WHERE 	1=1 and tttl.trangthai=1 and tttl.NGAYTRA < '" + this.tungay + "' and KHACHHANG_FK is not null 		    " + 
						 "\n 				and cl.chungloai_fk in ( select pk_Seq from cl   ) 	   " + 
						 "\n  		GROUP BY tttl.KHACHHANG_FK , tttl.NPP_FK ,cl.chungloai_fk    " + 
						 "\n  		--- thu tien etc    " + 
						 "\n  		UNION ALL	      " + 
						 "\n  		SELECT tthd.KHACHHANG_FK, tt.NPP_FK,tthd.CHUNGLOAI_FK, SUM(round(isnull(tthd.SOTIENTT,0),0)) as sotienTT       " + 
						 "\n 		FROM ERP_THUTIENNPP tt inner join ERP_THUTIENNPP_HOADON_CHITIET tthd on tt.PK_SEQ = tthd.THUTIENNPP_FK   " + 
						 "\n 		inner join kh  on kh.PK_SEQ = tt.KHACHHANG_FK      " + 
						 "\n 		WHERE tt.TRANGTHAI=1 and tt.NGAYCHUNGTU < '" + this.tungay + "'  and tthd.LOAIHD = 0     " + 
						 "\n 			and tthd.CHUNGLOAI_FK in ( select pk_Seq from cl   ) 	   " + 
						 "\n 		GROUP BY tthd.KHACHHANG_FK,tt.NPP_FK ,tthd.CHUNGLOAI_FK   " + 
						 "\n 		UNION ALL	  " + 
						 "\n 		-- phieu thu  co 131  " + 
						 "\n 		select kh.PK_SEQ, pc.NPP_FK, cl.PK_SEQ, sum(pcct.tiennt)  " + 
						 "\n 		from  ERP_THUTIENKHAC pc   " + 
						 "\n 		inner join ERP_THUTIENKHAC_CHITIET pcct on pc.PK_SEQ = pcct.THUTIENKHAC_FK  " + 
						 "\n 		inner join kh  on kh.maFAST = pcct.dtco  " + 
						 "\n 		inner join CHUNGLOAI cl on cl.TEN = pcct.ytco  " + 
						 "\n 		where  pc.TRANGTHAI  = 1   and pc.NGAYCHUNGTU <'" + this.tungay + "'  and pcct.tkco = '131'   " + 
						 "\n 		GROUP BY   kh.PK_SEQ, pc.NPP_FK, cl.PK_SEQ  " + 
						 "\n 		union all  " + 
						 "\n 		-- but toan tong hop co 131  " + 
						 "\n 		select  kh.PK_SEQ, pc.NPP_FK, cl.PK_SEQ, sum(pcct.tiennt)  " + 
						 "\n 		from  ERP_Buttoantonghop pc   " + 
						 "\n 		inner join ERP_BUTTOANTONGHOP_CHITIET pcct on pc.PK_SEQ = pcct.BUTTOANTONGHOP_FK  " + 
						 "\n 		inner join kh  on kh.maFAST = pcct.dtco  " + 
						 "\n 		inner join CHUNGLOAI cl on cl.TEN = pcct.ytco  " + 
						 "\n 		where  pc.TRANGTHAI  = 1   and pc.NGAYBUTTOAN <'" + this.tungay + "'  and pcct.TKcO = '131'   " + 
						 "\n 		GROUP BY   kh.PK_SEQ, pc.NPP_FK, cl.PK_SEQ  " + 
						 "\n   " + 
						 "\n  	) psc group by psc.KHACHHANG_FK, psc.NPP_FK ,psc.CHUNGLOAI_FK      " + 
						 "\n  )       " + 
						 "\n  dcdk on kh.PK_SEQ = dcdk.KHACHHANG_FK  and dcdk.CHUNGLOAI_FK = cl.PK_SEQ    " + 
						 "\n  left join      " + 
						 "\n  (       " + 
						 "\n  	SELECT psn_dk.PK_SEQ KHACHHANG_FK, psn_dk.NPP_FK,chungloai_fk , SUM(ISNULL(psn_dk.tongtienAVAT,0)) as tongtienAVAT       " + 
						 "\n  	FROM       " + 
						 "\n  	(	      " + 
						 "\n  		SELECT psn.PK_SEQ, psn.NPP_FK,psn.chungloai_fk , sum(tongtienAVAT) as tongtienAVAT       " + 
						 "\n  		FROM       " + 
						 "\n  		(       " + 
						 "\n  			--- hoadon  " + 
						 "\n  			SELECT hd.KHACHHANG_FK PK_SEQ, hd.NPP_FK, hdcl.chungloai_fk, SUM(round(isnull(hdcl.sotien,0),0)) tongtienAVAT       " + 
						 "\n  			FROM HOADON hd     " + 
						 "\n  			inner join HOADON_CHUNGLOAI hdcl on hd.PK_SEQ = hdcl.hoadon_fk    " + 
						 "\n  			INNER JOIN	 kh on hd.KHACHHANG_FK = kh.PK_SEQ       " + 
						 "\n  			WHERE 1=1 and  hd.LOAIHOADON = 0 and hd.TRANGTHAI not in ( 1, 3, 5 ) and hd.NGAYXUATHD >= '" + this.tungay + "'    and hd.NGAYXUATHD <='" + this.denngay + "'           " + 
						 "\n 				and hdcl.chungloai_fk in ( select pk_Seq from cl   ) 	   " + 
						 "\n  			 GROUP BY hd.KHACHHANG_FK,hd.NPP_FK  , hdcl.chungloai_fk   					    " + 
						 "\n  			 --- hoa don etc   " + 
						 "\n  			UNION ALL       " + 
						 "\n  			SELECT hd.khachhang_fk, hd.NPP_FK ,hdcl.chungloai_fk,sum(round(hdcl.sotien,0)) tongtienavat   " + 
						 "\n  			FROM ERP_HOADONNPP hd 	    " + 
						 "\n  			inner join ERP_HOADONNPP_CHUNGLOAI hdcl on hd.PK_SEQ = hdcl.hoadon_fk	and hdcl.chungloai_fk in ( select pk_Seq from cl   ) 	   " + 
						 "\n  			inner join kh on kh.PK_SEQ = hd.KHACHHANG_FK  " + 
						 "\n  			WHERE hd.trangthai not in ( 1, 3, 5 ) and hd.NGAYXUATHD >= '" + this.tungay + "'    and hd.NGAYXUATHD <='" + this.denngay + "'           " + 
						 "\n  			GROUP BY hd.khachhang_fk, hd.NPP_FK  ,hdcl.chungloai_fk   " + 
						 "\n  			union all  " + 
						 "\n 			-- phieu chi  no 131  " + 
						 "\n 			select kh.PK_SEQ, pc.NPP_FK, cl.PK_SEQ, sum(pcct.tiennt)  " + 
						 "\n 			from  Erp_PhieuChi pc   " + 
						 "\n 			inner join Erp_PhieuChi_CHITIET pcct on pc.PK_SEQ = pcct.PhieuChi_Fk  " + 
						 "\n 			inner join kh  on kh.maFAST = pcct.dtno  " + 
						 "\n 			inner join CHUNGLOAI cl on cl.TEN = pcct.ytno  " + 
						 "\n 			where  pc.TRANGTHAI  = 1    and pc.NGAYCHUNGTU >= '" + this.tungay + "'    and pc.NGAYCHUNGTU <='" + this.denngay + "'  and pcct.TKNO = '131'   " + 
						 "\n  			GROUP BY   kh.PK_SEQ, pc.NPP_FK, cl.PK_SEQ  " + 
						 "\n  			union all  " + 
						 "\n  			-- but toan tong hop no 131  " + 
						 "\n  			select  kh.PK_SEQ, pc.NPP_FK, cl.PK_SEQ, sum(pcct.tiennt)  " + 
						 "\n 			from  ERP_Buttoantonghop pc   " + 
						 "\n 			inner join ERP_BUTTOANTONGHOP_CHITIET pcct on pc.PK_SEQ = pcct.BUTTOANTONGHOP_FK  " + 
						 "\n 			inner join kh  on kh.maFAST = pcct.dtno  " + 
						 "\n 			inner join CHUNGLOAI cl on cl.TEN = pcct.ytno  " + 
						 "\n 			where  pc.TRANGTHAI  = 1    and pc.NGAYBUTTOAN  >= '" + this.tungay + "'    and pc.NGAYBUTTOAN  <='" + this.denngay + "' and pcct.TKNO = '131'   " + 
						 "\n  			GROUP BY   kh.PK_SEQ, pc.NPP_FK, cl.PK_SEQ   " + 
						 "\n  		)       " + 
						 "\n  		psn       " + 
						 "\n  		GROUP BY psn.PK_SEQ , psn.NPP_FK ,psn.chungloai_fk        " + 
						 "\n  	)       " + 
						 "\n  	psn_dk       " + 
						 "\n  	GROUP BY psn_dk.PK_SEQ , psn_dk.NPP_FK ,chungloai_fk     " + 
						 "\n  )      " + 
						 "\n  psntk on kh.PK_SEQ = psntk.KHACHHANG_FK and psntk.chungloai_fk = cl.PK_SEQ    " + 
						 "\n  LEFT JOIN       " + 
						 "\n  (       " + 
						 "\n  	SELECT 	psc.KHACHHANG_FK, psc.NPP_FK,psc.CHUNGLOAI_FK ,round(SUM (isnull(psc.SOTIENTT,0)),0) as SOTIENTT       " + 
						 "\n  	FROM       " + 
						 "\n  	(       " + 
						 "\n  		--- thu tien theo HD  " + 
						 "\n  		SELECT tthd.KHACHHANG_FK ,tt.NPP_FK,tthd.CHUNGLOAI_FK, SUM(round(ISNULL(tthd.sotienTT,0),0)) as sotienTT     			    " + 
						 "\n 		FROM ERP_THUTIENNPP tt inner join ERP_THUTIENNPP_HOADON_CHITIET tthd on tt.PK_SEQ = tthd.THUTIENNPP_FK    " + 
						 "\n 		inner join kh  on kh.PK_SEQ = tt.KHACHHANG_FK   			    " + 
						 "\n 		WHERE tt.TRANGTHAI = 1 and tt.NGAYCHUNGTU >= '" + this.tungay + "' and tt.NGAYCHUNGTU <= '" + this.denngay + "' and tthd.LOAIHD = 0      " + 
						 "\n 			and tthd.CHUNGLOAI_FK in ( select pk_Seq from cl   ) 	   " + 
						 "\n 		GROUP BY tthd.KHACHHANG_FK, tt.NPP_FK   ,tthd.CHUNGLOAI_FK       " + 
						 "\n  		UNION ALL	     " + 
						 "\n  		-- hang tra lai   " + 
						 "\n  		SELECT 	tttl.KHACHHANG_FK, tttl.NPP_FK,cl.chungloai_fk , SUM(round(isnull(cl.sotien,0),0)) as SOTIENTT 		      " + 
						 "\n  		FROM   	Erp_HangTraLaiNpp  tttl		  " + 
						 "\n  		inner join kh  on kh.PK_SEQ = tttl.KHACHHANG_FK		     " + 
						 "\n 		inner join Erp_HangTraLaiNpp_ChungLoai cl on tttl.pk_seq = cl.Hangtralai_fk   " + 
						 "\n  		WHERE 	1=1 and tttl.trangthai=1  and tttl.ngaytra >= '" + this.tungay + "' and tttl.ngaytra <= '" + this.denngay + "' and KHACHHANG_FK is not null 		    " + 
						 "\n 				and cl.chungloai_fk in ( select pk_Seq from cl   ) 	   " + 
						 "\n  		GROUP BY tttl.KHACHHANG_FK , tttl.NPP_FK ,cl.chungloai_fk    " + 
						 "\n  		--- thu tien etc    " + 
						 "\n  		UNION ALL	      " + 
						 "\n  		SELECT tthd.KHACHHANG_FK, tt.NPP_FK,tthd.CHUNGLOAI_FK, SUM(round(isnull(tthd.SOTIENTT,0),0)) as sotienTT       " + 
						 "\n 		FROM ERP_THUTIENNPP tt inner join ERP_THUTIENNPP_HOADON_CHITIET tthd on tt.PK_SEQ = tthd.THUTIENNPP_FK       " + 
						 "\n 		inner join kh  on kh.PK_SEQ = tt.KHACHHANG_FK  " + 
						 "\n 		WHERE tt.TRANGTHAI=1 and tt.NGAYCHUNGTU >= '" + this.tungay + "' and tt.NGAYCHUNGTU <= '" + this.denngay + "' and tthd.LOAIHD = 0     " + 
						 "\n 			and tthd.CHUNGLOAI_FK in ( select pk_Seq from cl   ) 	   " + 
						 "\n 		GROUP BY tthd.KHACHHANG_FK,tt.NPP_FK ,tthd.CHUNGLOAI_FK   " + 
						 "\n 		UNION ALL	  " + 
						 "\n 		-- phieu thu  co 131  " + 
						 "\n 		select kh.PK_SEQ, pc.NPP_FK, cl.PK_SEQ, sum(pcct.tiennt)  " + 
						 "\n 		from  ERP_THUTIENKHAC pc   " + 
						 "\n 		inner join ERP_THUTIENKHAC_CHITIET pcct on pc.PK_SEQ = pcct.THUTIENKHAC_FK  " + 
						 "\n 		inner join kh  on kh.maFAST = pcct.dtco  " + 
						 "\n 		inner join CHUNGLOAI cl on cl.TEN = pcct.ytco  " + 
						 "\n 		where  pc.TRANGTHAI  = 1   and pc.NGAYCHUNGTU >= '" + this.tungay + "' and pc.NGAYCHUNGTU <= '" + this.denngay + "'  and pcct.tkco = '131'   " + 
						 "\n 		GROUP BY   kh.PK_SEQ, pc.NPP_FK, cl.PK_SEQ  " + 
						 "\n 		union all  " + 
						 "\n 		-- but toan tong hop co 131  " + 
						 "\n 		select  kh.PK_SEQ, pc.NPP_FK, cl.PK_SEQ, sum(pcct.tiennt)  " + 
						 "\n 		from  ERP_Buttoantonghop pc   " + 
						 "\n 		inner join ERP_BUTTOANTONGHOP_CHITIET pcct on pc.PK_SEQ = pcct.BUTTOANTONGHOP_FK  " + 
						 "\n 		inner join kh  on kh.maFAST = pcct.dtco  " + 
						 "\n 		inner join CHUNGLOAI cl on cl.TEN = pcct.ytco  " + 
						 "\n 		where  pc.TRANGTHAI  = 1   and pc.NGAYBUTTOAN >= '" + this.tungay + "' and pc.NGAYBUTTOAN <= '" + this.denngay + "' and pcct.TKcO = '131'   " + 
						 "\n 		GROUP BY   kh.PK_SEQ, pc.NPP_FK, cl.PK_SEQ  " + 
						 "\n       " + 
						 "\n  	)   	psc group by psc.KHACHHANG_FK, psc.NPP_FK ,psc.CHUNGLOAI_FK      " + 
						 "\n  )       " + 
						 "\n  psctk on kh.PK_SEQ = psctk.KHACHHANG_FK   and psctk.CHUNGLOAI_FK = cl.PK_SEQ   " + 
						 
						 "\n where   " + 
						 "\n 		( isnull(dndk.tongtienAVAT, 0)!= 0 or  isnull(dcdk.SOTIENTT, 0) !=0    " + 
						 "\n 			or isnull(psntk.tongtienAVAT, 0) != 0 or isnull(psctk.sotienTT, 0)!=0 )  " ;
						 
		if(this.theoChungloai.equals("1"))
			query = with_condition +
				 "\n select * " +
				 "\n from" +
				 "\n( " +
				 "\n " + query +
				 "\n) kq ";
		else
			query = with_condition +
			 "\n select  [NVBH],[MÃ KH],[KHÁCH HÀNG], [ĐIẠ CHỈ] " +
			 "\n	, sum([DƯ NỢ ĐẦU KỲ])[DƯ NỢ ĐẦU KỲ]" +
			 "\n	,sum( [DƯ CÓ ĐẦU KỲ])  [DƯ CÓ ĐẦU KỲ]" +
			 "\n    ,sum([PHÁT SINH NỢ]) [PHÁT SINH NỢ]" +
			 "\n	,sum([PHÁT SINH CÓ] ) [PHÁT SINH CÓ] " +
			 "\n	,sum( [DƯ NỢ CUỐI KỲ]) [DƯ NỢ CUỐI KỲ]" +
			 "\n	,sum([DƯ CÓ CUỐI KỲ] )[DƯ CÓ CUỐI KỲ] 	 " +
			 "\n from" +
			 "\n( " +
			 "\n " + query +
			 "\n) kq " +
			 "\n group by  [NVBH],[MÃ KH],[KHÁCH HÀNG], [ĐIẠ CHỈ]      ";
		
		query += "\n  ORDER BY [MÃ KH] ";
		
    	System.out.println("BC_TheoKH \n ____"+query);
		return query;
	}
	
	 

	public String getBCTheoDoiTac() {
		String query  ="";
		String withCondition = "\n with npp as (select * from NHAPHANPHOI where isKHACHHANG = 0 ";
		
		if(this.doitacId.trim().length() > 0)
			withCondition += "\n	 and pk_seq =  "+ this.doitacId;
	 	if(this.nppId.trim().length()>0)
	 		withCondition += "\n 	 and TRUCTHUOC_FK = '"+this.nppId+"' \n";
	 	withCondition += "\n 	)";
	 	
	 	query = withCondition +
	 	 "\n 	SELECT   isnull(npp.MaFAST,'')  [MÃ NPP],npp.TEN [NPP], npp.Diachi [ĐỊA CHỈ], ISNULL(dndk.tongtienAVAT,0) as [DƯ NỢ ĐẦU KỲ],   " + 
	 	 "\n 		   ISNULL (dcdk.SOTIENTT,0) as [DƯ CÓ ĐẦU KỲ], ISNULL (psntk.tongtienAVAT,0) as [PHAT SINH NỢ], ISNULL(psctk.SOTIENTT,0) as [PHÁT SINH CÓ]  " + 
	 	 "\n 		   , ISNULL(dndk.tongtienAVAT,0) +  ISNULL (psntk.tongtienAVAT,0) [DỰ NỢ CUỐI KỲ]  " + 
	 	 "\n 		   , ISNULL (dcdk.SOTIENTT,0) +  ISNULL(psctk.SOTIENTT,0)  [DỰ CÓ CUỐI KỲ]  " + 
	 	 "\n 	   " + 
	 	 "\n 	FROM  npp   " + 
	 	 "\n 	inner join CHUNGLOAI cl on 1=1  " + 
	 	 "\n 	LEFT JOIN   " + 
	 	 "\n 	(   " + 
	 	 "\n 		SELECT psn_dk.NPP_DAT_FK  , psn_dk.chungloai_fk , SUM(ISNULL(psn_dk.tongtienAVAT,0)) as tongtienAVAT   " + 
	 	 "\n 		FROM     " + 
	 	 "\n 		(	  " + 
	 	 "\n 			SELECT	hd.NPP_DAT_FK, cl.chungloai_fk , SUM(tongtienavat) tongtienAVAT   " + 
	 	 "\n 			FROM	ERP_HOADONNPP hd    " + 
	 	 "\n 			INNER JOIN  npp on hd.NPP_DAT_FK = npp.PK_SEQ   " + 
	 	 "\n 			inner join ERP_HOADONNPP_CHUNGLOAI cl on cl.hoadon_fk = hd.PK_SEQ  " + 
	 	 "\n 			WHERE	1=1 and  hd.LOAIXUATHD = 0 and hd.TRANGTHAI  not in ( 1, 3, 5 )   " + 
	 	 "\n 					and hd.NGAYXUATHD < '" + this.tungay + "' and hd.NPP_DAT_FK is not null   " + 
	 	 "\n 			GROUP BY hd.NPP_DAT_FK   , cl.chungloai_fk  " + 
	 	 "\n 		) psn_dk     " + 
	 	 "\n 		GROUP BY psn_dk.NPP_DAT_FK , psn_dk.chungloai_fk  " + 
	 	 "\n 		  " + 
	 	 "\n 	)dndk ON npp.PK_SEQ = dndk.NPP_DAT_FK and dndk.chungloai_fk = cl.PK_SEQ  " + 
	 	 "\n 	LEFT JOIN   " + 
	 	 "\n 	(   " + 
	 	 "\n 		SELECT 	psc.NPP_DAT_FK,psc.CHUNGLOAI_FK, round(SUM (isnull(psc.SOTIENTT,0)),0) as SOTIENTT   " + 
	 	 "\n 		FROM (   " + 
	 	 "\n 				SELECT 	hd.NPP_DAT_FK,tt.CHUNGLOAI_FK, SUM(round(isnull(tt.sotienTT,0),0)) as SOTIENTT   " + 
	 	 "\n 				FROM 	ERP_HOADONNPP hd   " + 
	 	 "\n 				INNER JOIN npp on hd.NPP_DAT_FK=npp.PK_SEQ     " + 
	 	 "\n 				INNER JOIN   " + 
	 	 "\n 				(      " + 
	 	 "\n 					SELECT tthd.NPP_FK NPP_DAT_FK, tthd.HOADONNPP_FK,tthd.CHUNGLOAI_FK, SUM(round(ISNULL(tthd.sotienTT,0),0)) as sotienTT   " + 
	 	 "\n 					FROM ERP_THUTIENNPP tt inner join ERP_THUTIENNPP_HOADON_ChiTiet tthd on tt.PK_SEQ = tthd.THUTIENNPP_FK		  " + 
	 	 "\n 					WHERE tt.TRANGTHAI = 1 and tt.NGAYCHUNGTU < '" + this.tungay + "'  and tthd.NPP_FK is not null    " + 
	 	 "\n 					GROUP BY tthd.NPP_FK, tthd.HOADONNPP_FK   ,tthd.CHUNGLOAI_FK  " + 
	 	 "\n 				)tt on hd.NPP_DAT_FK = tt.NPP_DAT_FK and hd.PK_SEQ = tt.HOADONNPP_FK   " + 
	 	 "\n 				WHERE 	1=1  and hd.NPP_DAT_FK is not null 			  " + 
	 	 "\n 				GROUP BY hd.NPP_DAT_FK ,tt.CHUNGLOAI_FK    " + 
	 	 "\n 				  " + 
	 	 "\n  			--	UNION ALL   " + 
	 	 "\n 				--SELECT	tttl.NppTra_FK NPP_DAT_FK, SUM(tttl.TienSauThue) tongtienAVAT   " + 
	 	 "\n 				--FROM	Erp_HangTraLaiNpp tttl INNER JOIN  npp on tttl.NPP_FK = npp.PK_SEQ   " + 
	 	 "\n 				--WHERE	1=1 and tttl.TRANGTHAI = 1 AND tttl.NGAYTRA < '" + this.tungay + "'    " + 
	 	 "\n 				--		and tttl.NppTra_FK is not null   " + 
	 	 "\n 				--GROUP BY tttl.NppTra_FK   " + 
	 	 "\n 				  " + 
	 	 "\n  		)  psc   " + 
	 	 "\n 		GROUP BY psc.NPP_DAT_FK ,psc.CHUNGLOAI_FK  " + 
	 	 "\n 	)dcdk ON dcdk.NPP_DAT_FK = npp.PK_SEQ  and dcdk.chungloai_fk = cl.PK_SEQ  " + 
	 	 "\n 	LEFT JOIN   " + 
	 	 "\n  	(   " + 
	 	 "\n 		SELECT psn_dk.NPP_DAT_FK  , psn_dk.chungloai_fk , SUM(ISNULL(psn_dk.tongtienAVAT,0)) as tongtienAVAT   " + 
	 	 "\n 		FROM     " + 
	 	 "\n 		(	  " + 
	 	 "\n 			SELECT	hd.NPP_DAT_FK, cl.chungloai_fk , SUM(tongtienavat) tongtienAVAT   " + 
	 	 "\n 			FROM	ERP_HOADONNPP hd    " + 
	 	 "\n 			INNER JOIN  npp on hd.NPP_DAT_FK = npp.PK_SEQ   " + 
	 	 "\n 			inner join ERP_HOADONNPP_CHUNGLOAI cl on cl.hoadon_fk = hd.PK_SEQ  " + 
	 	 "\n 			WHERE	1=1 and  hd.LOAIXUATHD = 0 and hd.TRANGTHAI  not in ( 1, 3, 5 )   " + 
	 	 "\n 					and hd.NGAYXUATHD >= '" + this.tungay + "'	and hd.NGAYXUATHD <= '" + this.denngay + "' and hd.NPP_DAT_FK is not null   " + 
	 	 "\n 			GROUP BY hd.NPP_DAT_FK   , cl.chungloai_fk  " + 
	 	 "\n 		) psn_dk     " + 
	 	 "\n 		GROUP BY psn_dk.NPP_DAT_FK , psn_dk.chungloai_fk  " + 
	 	 "\n 		  " + 
	 	 "\n 	) psntk ON psntk.NPP_DAT_FK = npp.PK_SEQ   and psntk.chungloai_fk = cl.PK_SEQ  " + 
	 	 "\n 	LEFT JOIN   " + 
	 	 "\n 	(   " + 
	 	 "\n 		SELECT 	psc.NPP_DAT_FK,psc.CHUNGLOAI_FK, round(SUM (isnull(psc.SOTIENTT,0)),0) as SOTIENTT   " + 
	 	 "\n 		FROM   " + 
	 	 "\n 		(   " + 
	 	 "\n 				SELECT 	hd.NPP_DAT_FK,tt.CHUNGLOAI_FK, SUM(round(isnull(tt.sotienTT,0),0)) as SOTIENTT   " + 
	 	 "\n 				FROM 	ERP_HOADONNPP hd   " + 
	 	 "\n 				INNER JOIN npp on hd.NPP_DAT_FK=npp.PK_SEQ     " + 
	 	 "\n 				INNER JOIN   " + 
	 	 "\n 				(      " + 
	 	 "\n 					SELECT tthd.NPP_FK NPP_DAT_FK, tthd.HOADONNPP_FK,tthd.CHUNGLOAI_FK, SUM(round(ISNULL(tthd.sotienTT,0),0)) as sotienTT   " + 
	 	 "\n 					FROM ERP_THUTIENNPP tt inner join ERP_THUTIENNPP_HOADON_ChiTiet tthd on tt.PK_SEQ = tthd.THUTIENNPP_FK		  " + 
	 	 "\n 					WHERE tt.TRANGTHAI = 1 and tt.NGAYCHUNGTU >= '" + this.tungay + "'  and tt.NGAYCHUNGTU <= '" + this.denngay + "'  and tthd.NPP_FK is not null    " + 
	 	 "\n 					GROUP BY tthd.NPP_FK, tthd.HOADONNPP_FK   ,tthd.CHUNGLOAI_FK  " + 
	 	 "\n 				)tt on hd.NPP_DAT_FK = tt.NPP_DAT_FK and hd.PK_SEQ = tt.HOADONNPP_FK   " + 
	 	 "\n 				WHERE 	1=1  and hd.NPP_DAT_FK is not null 			  " + 
	 	 "\n 				GROUP BY hd.NPP_DAT_FK ,tt.CHUNGLOAI_FK    " + 
	 	 "\n 				  " + 
	 	 "\n  			--	UNION ALL   " + 
	 	 "\n 				--SELECT	tttl.NppTra_FK NPP_DAT_FK, SUM(tttl.TienSauThue) tongtienAVAT   " + 
	 	 "\n 				--FROM	Erp_HangTraLaiNpp tttl INNER JOIN  npp on tttl.NPP_FK = npp.PK_SEQ   " + 
	 	 "\n 				--WHERE	1=1 and tttl.TRANGTHAI = 1 AND tttl.NGAYTRA < '" + this.tungay + "'    " + 
	 	 "\n 				--		and tttl.NppTra_FK is not null   " + 
	 	 "\n 				--GROUP BY tttl.NppTra_FK   " + 
	 	 "\n 				  " + 
	 	 "\n  		)  psc   " + 
	 	 "\n 		GROUP BY psc.NPP_DAT_FK ,psc.CHUNGLOAI_FK  " + 
	 	 "\n 	   " + 
	 	 "\n 	)psctk ON npp.PK_SEQ = psctk.NPP_DAT_FK   and psctk.chungloai_fk = cl.PK_SEQ   " + 
	 	 "\n 	 WHERE ( isnull(dndk.tongtienAVAT, 0)!= 0 or  isnull(dcdk.SOTIENTT, 0) !=0 or isnull(psntk.tongtienAVAT, 0) != 0 or isnull(psctk.sotienTT, 0)!=0 )    " + 
	 	 "\n 	 ORDER BY npp.TEN asc ";
	 	
	 	System.out.println("BC đối tác: " + query);
		return query;
	}

	
	
	public String getDoiTacHOId() {
		
		return this.doitacHOId;
	}

	
	public void setDoiTacHOId(String doitacHOId) {
		
		this.doitacHOId = doitacHOId;
	}

	
	public ResultSet DoiTacHORs() {
		
		String query = 	"	select PK_SEQ,  isnull(maFAST, '') + ', ' + TEN as TEN  " +
						"	from NHAPHANPHOI where TRANGTHAI = '1' and iskhachhang = 0 ";
		
		return db.get(query);
	}
	
	public dbutils getDb() {
		return db;
	}
	String userTen = "";
	public String getUserTen() {
		return userTen;
	}
	public void setUserTen(String userTen) {
		this.userTen = userTen;
	}

	String theoChungloai= "0";
	public String getTheoChungloai() {
		return theoChungloai;
	}
	public void setTheoChungloai(String theoChungloai) {
		this.theoChungloai = theoChungloai;
	}
	public String getChungloaiId() {
		return chungloaiId;
	}
	public void setChungloaiId(String chungloaiId) {
		this.chungloaiId = chungloaiId;
	}
	public ResultSet getChungloaiRs() {
		return chungloaiRs;
	}
	
	
}
