package geso.dms.center.beans.dontrahang.imp;
import java.io.Serializable;
import java.sql.ResultSet;
import java.sql.SQLException;

import geso.dms.center.db.sql.dbutils;
import geso.dms.center.util.Phan_Trang;
import geso.dms.center.util.Utility;
import geso.dms.center.beans.dontrahang.IDontrahangList;

public class DontrahangList  extends Phan_Trang   implements IDontrahangList, Serializable
{
	private static final long serialVersionUID = 1L;
	String userId;
	String tungay;
	String denngay;
	String trangthai;

	String sophieu;
	String lydo;
	String msg;
	
	ResultSet nhapkhoRs;
	ResultSet khRs;
	String khId;
	
	String nppId;
	String nppTen;
	String sitecode;
	String sochungtu;
	

	
	dbutils db;
	
	public DontrahangList()
	{
		this.tungay = "";
		this.denngay = "";
		this.trangthai = "";
		this.sophieu="";
		this.sochungtu="";
		this.lydo = "";
		this.msg = "";

		this.db = new dbutils();
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

	public void init(String search)
	{
		this.getNppInfo();
		Utility util = new Utility();
		String query = "";
        
		if(search.length() > 0)
			query = search;
		else
		{
			query =	
					"\n	select a.pk_Seq,b.MA as nppMa,b.TEN as nppTen,a.NGAYTRA"
					+ "\n,c.TEN as nguoiTao,d.TEN as nguoiSua,e.TEN as tructhuoc,a.TRANGTHAI"
					+ "\n,a.SOTIENBVAT,a.Modified_Date,a.created_date "+
					"\n		from DONTRAHANG a inner join NHAPHANPHOI b on b.PK_SEQ=a.NPP_FK "+
					"\n		inner join NHANVIEN c on c.PK_SEQ=a.NGUOITAO  "+
					"\n		inner join NHANVIEN d on d.PK_SEQ=a.NGUOISUA "+
					"\n		left join NhaCungCap e on e.PK_SEQ=a.NCC_FK " +
					"\n where 1=1 AND A.TrangThai!=0 ";
		} 
		query += " and a.npp_fk in "+util.quyen_npp(this.userId);
		query += " and exists (select 1 from NHAPP_KBH kbh where kbh.npp_fk = b.pk_seq and kbh.KBH_Fk in "+util.quyen_kenh(userId)+" ) ";
		System.out.println("___CHUYEN KHO: " + query);
		
		this.nhapkhoRs = createSplittingData(50, 10, "NGAYTRA desc, pk_Seq desc, TRANGTHAI asc ", query);
		this.khRs = db.get("select PK_SEQ, TEN,MA,DiaChi from NHAPHANPHOI where trangthai = '1' and pk_seq in "+util.quyen_npp(userId)+" ");
		
	
		
		
	}
	
	public void DBclose() 
	{
		this.db.shutDown();
	}

	public String getSophieu()
	{
		return sophieu;
	}

	public void setSophieu(String sophieu) 
	{
		this.sophieu = sophieu;
	}

	public String getLydo() 
	{
		return lydo;
	}

	public void setLydo(String lydo) 
	{
		this.lydo = lydo;
	}

	public String getTungayTao() 
	{
		return this.tungay;
	}

	public void setTungayTao(String tungay) 
	{
		this.tungay =tungay;	
	}

	public String getDenngayTao() 
	{
		return this.denngay;
	}

	public void setDenngayTao(String denngay) 
	{
		this.denngay = denngay;
	}

	public ResultSet getNhapkhoRs() 
	{
		return this.nhapkhoRs;
	}

	public void setNhapkhoRs(ResultSet nkRs) 
	{
		this.nhapkhoRs = nkRs;
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
	
	private void getNppInfo()
	{		
		//Phien ban moi
		geso.dms.distributor.util.Utility util=new geso.dms.distributor.util.Utility();
		System.out.println("NPPID: "+this.nppId +" USERID: "+this.userId);
		this.nppId=util.getIdNhapp(this.userId);
		this.nppTen=util.getTenNhaPP();
		//this.dangnhap = util.getDangNhap();
		this.sitecode=util.getSitecode();
	}

	public String getSochungtu() {
		return this.sochungtu;
	}

	public void setSochungtu(String sochungtu) {
		this.sochungtu=sochungtu;
	}

	public ResultSet getKhRs() {
		return this.khRs;
	}

	public void setKhRs(ResultSet khrs) {
		this.khRs=khrs;
		
	}

	public String getKhId() {
		return this.khId;
	}

	public void setKhId(String KhId) {
		this.khId=KhId;
		
	}
	
	
}
