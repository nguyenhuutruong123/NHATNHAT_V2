package geso.dms.center.beans.tieuchithuong.imp;

import java.io.Serializable;
import java.sql.ResultSet;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import geso.dms.center.beans.tieuchithuong.*;
import geso.dms.center.db.sql.dbutils;
import geso.dms.center.util.Phan_Trang;

public class TieuchithuongList   extends Phan_Trang implements ITieuchithuongList ,Serializable
{
	public String dvkdId;
	public ResultSet dvkd;

	public String kbhId;
	public ResultSet kbh;

	public String msg;

	public String thang;
	public String nam;

	public String userId;


	public String loai;

	public String getLoaithuong() {
		return loaithuong;
	}

	public void setLoaithuong(String loaithuong) {
		this.loaithuong = loaithuong;
	}

	ResultSet tct;

	public dbutils db;
	String loaithuong="";

	public TieuchithuongList() {
		this.dvkdId = "";
		this.kbhId = "";
		this.msg = "";
		this.thang = "";
		this.loai = "1";
		this.nam = "";
		this.db = new dbutils();
	}

	public void setDvkdId(String dvkdId) {
		this.dvkdId = dvkdId;
	}

	public String getdvkdId() {
		return this.dvkdId;
	}

	public void setDvkd(ResultSet dvkd) {
		this.dvkd = dvkd;
	}

	public ResultSet getdvkd() {
		return this.dvkd;
	}

	public void setKbhId(String kbhId) {
		this.kbhId = kbhId;
	}

	public String getkbhId() {
		return this.kbhId;
	}

	public void setKbh(ResultSet kbh) {
		this.kbh = kbh;
	}

	public ResultSet getKbh() {
		return this.kbh;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public String getMsg() {
		return this.msg;
	}

	public void setMonth(String month) {
		this.thang = month;
	}

	public String getMonth() {
		return this.thang;
	}

	public void setYear(String year) {
		this.nam = year;
	}

	public String getYear() {
		return this.nam;
	}

	public String getUserId() {
		return this.userId;
	}
	
	public void setUserId(String userId)
	{
		this.userId = userId;
		
	}

	

	public void setLoai(String loai) {
		this.loai = loai;
	}

	public String getLoai() {
		return this.loai;
	}

	public void setTct(ResultSet tct) {
		this.tct = tct;
	}

	public ResultSet getTct() {
		return this.tct;
	}

	private String getDateTime() {
		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd: hh-mm-ss");
		Date date = new Date();
		return dateFormat.format(date);
	}

	public void init() 
	{
		String query = "SELECT PK_SEQ AS DVKDID, DONVIKINHDOANH AS DVKD FROM DONVIKINHDOANH " ;
		this.dvkd = this.db.get(query);

		query = "SELECT PK_SEQ AS KBHID, TEN AS KBH FROM KENHBANHANG where  trangthai  = 1";
		String vung_fk="";
		String npp_fk="";
		this.kbh = this.db.get(query);

		
		query = "SELECT TCT.PK_SEQ, TCT.DIENGIAI, KBH.TEN AS KBH, "
				+ "\n DVKD.DONVIKINHDOANH AS DVKD, TCT.THANG, TCT.NAM, "
				+ "\n NV1.TEN AS NGUOITAO, "
				+ "\n NV2.TEN AS NGUOISUA,"
				+ "\n TCT.NGAYTAO,"
				+ "\n TCT.NGAYSUA,"
				+ "\n TCT.TRANGTHAI "
				+ "\n FROM TIEUCHITINHTHUONG TCT "
				+ "\n INNER JOIN KENHBANHANG KBH ON KBH.PK_SEQ = TCT.KBH_FK "
				+ "\n INNER JOIN DONVIKINHDOANH DVKD ON DVKD.PK_SEQ = TCT.DVKD_FK "
				+ "\n INNER JOIN NHANVIEN NV1 ON NV1.PK_SEQ = TCT.NGUOITAO "
				+ "\n INNER JOIN NHANVIEN NV2 ON NV2.PK_SEQ = TCT.NGUOISUA "
				+ " ";

	
		/*if(this.loai.equals("2"))
		{
			
				vung_fk="select distinct v.PK_SEQ from PHAMVIHOATDONG a inner join NHAPHANPHOI b "+
					 "\n on a.Npp_fk=b.PK_SEQ inner join TINHTHANH tt on tt.PK_SEQ=b.TINHTHANH_FK "+
					 "\n	inner join VUNG v on v.PK_SEQ=tt.VUNG_FK inner join NHANVIEN nv "+
					 "\n	on nv.PK_SEQ=a.Nhanvien_fk where nv.PK_SEQ="+this.userId;
			query = "SELECT distinct TCT.PK_SEQ, TCT.DIENGIAI, KBH.TEN AS KBH, "
					+ "\n DVKD.DONVIKINHDOANH AS DVKD, TCT.THANG, TCT.NAM, "
					+ "\n NV1.TEN AS NGUOITAO, "
					+ "\n NV2.TEN AS NGUOISUA,"
					+ "\n TCT.NGAYTAO,"
					+ "\n TCT.NGAYSUA,"
					+ "\n isnull(tcnpp.TRANGTHAI,0) trangthai  "
					+ "\n FROM TIEUCHITINHTHUONG TCT "
					+ "\n  inner join TIEUCHITHUONG_CHITIET ct "
					+ "\n  on ct.TIEUCHITINHTHUONG_FK=TCT.PK_SEQ "
					+ "\n  inner join TieuChiThuong_ChiTiet_MucThuong_npp tcnpp on tcnpp.TCTCT_FK=ct.PK_SEQ "
					+ "\n INNER JOIN KENHBANHANG KBH ON KBH.PK_SEQ = TCT.KBH_FK "
					+ "\n INNER JOIN DONVIKINHDOANH DVKD ON DVKD.PK_SEQ = TCT.DVKD_FK "
					+ "\n INNER JOIN NHANVIEN NV1 ON NV1.PK_SEQ = TCT.NGUOITAO "
					+ "\n INNER JOIN NHANVIEN NV2 ON NV2.PK_SEQ = TCT.NGUOISUA ";
		}
		if(this.loai.equals("1"))
		{
			
				npp_fk=" select distinct b.pk_seq from  NHAPHANPHOI b "+
					"\n	inner join TINHTHANH tt on tt.PK_SEQ=b.TINHTHANH_FK "+
					"\n	inner join VUNG v on v.PK_SEQ=tt.VUNG_FK  "+
					"\n	inner join NHANVIEN nv "+
					"\n	on b.SITECODE=nv.CONVSITECODE  " +
					"\n	 where nv.PK_SEQ="+this.userId;
				
			query = "SELECT distinct TCT.PK_SEQ, TCT.DIENGIAI, KBH.TEN AS KBH, "
					+ "\n DVKD.DONVIKINHDOANH AS DVKD, TCT.THANG, TCT.NAM, "
					+ "\n NV1.TEN AS NGUOITAO, "
					+ "\n NV2.TEN AS NGUOISUA,"
					+ "\n TCT.NGAYTAO,"
					+ "\n TCT.NGAYSUA,"
					+ "\n isnull(tcnpp.TRANGTHAI,0) trangthai  "
					+ "\n FROM TIEUCHITINHTHUONG TCT "
					+ "\n  inner join TIEUCHITHUONG_CHITIET ct "
					+ "\n  on ct.TIEUCHITINHTHUONG_FK=TCT.PK_SEQ "
					+ "\n  inner join TieuChiThuong_ChiTiet_MucThuong_ddkd tcnpp on tcnpp.TCTCT_FK=ct.PK_SEQ "
					+ "\n INNER JOIN KENHBANHANG KBH ON KBH.PK_SEQ = TCT.KBH_FK "
					+ "\n INNER JOIN DONVIKINHDOANH DVKD ON DVKD.PK_SEQ = TCT.DVKD_FK "
					+ "\n INNER JOIN NHANVIEN NV1 ON NV1.PK_SEQ = TCT.NGUOITAO "
					+ "\n INNER JOIN NHANVIEN NV2 ON NV2.PK_SEQ = TCT.NGUOISUA ";
		}*/
		String condition = " WHERE 1=1  ";
		if(loai.length() > 0)
		{
			condition = condition + "\n  and  TCT.loai = '"+this.loai+"'   ";
		}
		if(!npp_fk.equals(""))
		{
			condition = condition + "\n  and  tcnpp.npp_fk=("+npp_fk+")   ";
		}
	/*	if(!vung_fk.equals(""))
		{
			condition = condition + "\n  and   tcnpp.vung_fk=("+vung_fk+")   ";
		}*/
		
		if (this.kbhId.length() > 0) {
			condition = condition + "\n  AND TCT.KBH_FK='" + this.kbhId + "' ";
		}

		if (this.dvkdId.length() > 0) {
			condition = condition + "\n  AND TCT.DVKD_FK='" + this.dvkdId + "' ";
		}

		if (this.thang.length() > 0) {
			condition = condition + "\n  AND TCT.THANG = cast ('" + this.thang + "' as int)  ";
		}

		if (this.nam.length() > 0) {
			condition = condition + "\n  AND TCT.NAM = '" + this.nam + "' ";
		}

		query = query + condition;
		query +="\n order by  TCT.NAM desc,TCT.THANG desc ";
		//System.out.println("TctList" +query);
		this.tct = this.db.get(query);
	}

	public void closeDB() {
		if (this.db != null)
			this.db.shutDown();
	}
}
