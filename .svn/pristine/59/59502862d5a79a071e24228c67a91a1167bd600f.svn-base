package geso.dms.center.beans.lotrinh.imp;

import java.sql.ResultSet;

import javax.rmi.CORBA.Util;

import geso.dms.center.beans.lotrinh.ILoTrinh;
import geso.dms.center.db.sql.dbutils;
import geso.dms.center.util.Utility;

public class LoTrinh implements ILoTrinh{

	String nppId = "";
	String ddkdId = "";
	String tuyenId = "";

	String tungay = "";
	String denngay = "";
	ResultSet tuyen;
	ResultSet npp;
	ResultSet ddkd;
	dbutils db;
	ResultSet danhsach;
	ResultSet kenh;
	String kenhId;
	
	
	
	private String khuvucId;
	private ResultSet khuvuc;
	
	private String tinhthanhId = "";
	private ResultSet tinhthanhRs ;
	
	private String vungId = "";
	private ResultSet vungRs ;
	
	private String status;
	
	
	private String gsbhId = "";
	ResultSet gsbhRs ;
	
	private String rsmId = "";
	ResultSet rsmRs ;
	
	public dbutils getDb() {
		return db;
	}
	String view = "";
	public String getView() {
		return view;
	}
	public void setView(String view) {
		this.view = view;
	}
	public LoTrinh()
	{
		this.khuvucId="";
		this.status = "";
		this.nppId ="";
		this.ddkdId ="";
		this.tuyenId ="";
		this.kenhId ="";
		this.tungay = "";
		this.denngay = "";
		this.tinhthanhId  = "";
		this.vungId = "";
		this.gsbhId = "";
		this.rsmId = "";
		
		db = new dbutils();
	}
	
	String loaiNv = "",phanloai= "",userId = "";
	public String getLoaiNv()
  {
  	return loaiNv;
  }

	public void setLoaiNv(String loaiNv)
  {
  	this.loaiNv = loaiNv;
  }
	String loaiMenu="";
	public String getLoaiMenu() {
		return loaiMenu;
	}
	public void setLoaiMenu(String loaiMenu) {
		this.loaiMenu = loaiMenu;
	}
	public String getPhanloai()
  {
  	return phanloai;
  }

	public void setPhanloai(String phanloai)
  {
  	this.phanloai = phanloai;
  }

	public String getUserId()
  {
  	return userId;
  }

	public void setUserId(String userId)
  {
  	this.userId = userId;
  }
	
	public void setKenhId(String kenhId){
		this.kenhId = kenhId;
	}
	public void setnppId(String nppId) {
		
		this.nppId = nppId;
	}

	
	public String getnppId() {
		
		return this.nppId;
	}

	
	public void setddkdId(String ddkdId) {
		this.ddkdId = ddkdId;
		
	}

	
	public String getddkdId() {
		
		return this.ddkdId;
	}

	
	public ResultSet getnpp() {
		
		return this.npp;
	}

	
	public ResultSet getddkd() {
		
		return this.ddkd;
	}
	String loaiUser = "";
	Utility Ult = new Utility();
	public void init() 
	{
		try{
			
			Utility Ult = new Utility();
			String query="select phanloai,loai from nhanvien where pk_seq="+this.userId;
			System.out.println(" user :" + query);
			ResultSet rs=this.db.get(query);
			if(rs!=null){
				if(rs.next()){

					this.phanloai = rs.getString("phanloai");
					System.out.println("Phan loai : "+this.phanloai);					 				
					this.loaiUser =  rs.getString("loai");
					if( rs.getString("phanloai").equals("1")){
						this.nppId = Ult.getIdNhapp(this.userId);
						
					}
					rs.close();
				}
			}
		}catch(Exception er){

		}
		
		
		//String sql = "select * from nhaphanphoi where trangthai='"+this.status+"' ";
		String sql = "select * from nhaphanphoi where trangthai=1 ";
		 if(this.phanloai.equals("2"))
			{
				sql+= " and pk_Seq in " + Ult.quyen_npp(userId)+"";
			}
		
		 if (this.khuvucId.length() > 0)
			{
				sql = sql + " and pk_seq  in "+
				"  (select distinct npp_fk from KHACHHANG where PK_SEQ in "+ 
				" ( "+
				" select a.KHACHHANG_FK from KHACHHANG_TUYENBH a inner join "+
				" tuyenbanhang b on a.TBH_FK = b.PK_SEQ inner join DAIDIENKINHDOANH "+
				" c on b.DDKD_FK = c.PK_SEQ inner join DIABAN db on c.diaban_fk = db.PK_SEQ inner join "+
				" KHUVUC kv on db.KHUVUC_FK = kv.PK_SEQ  where KHUVUC_FK = '" + this.khuvucId + "'"+ 
				") )";
				
			}
			if (this.vungId.length() > 0)
			{
				sql = sql + " and pk_seq  in "+
						"  (select distinct npp_fk from KHACHHANG where PK_SEQ in "+ 
						" ( "+
						" select a.KHACHHANG_FK from KHACHHANG_TUYENBH a inner join "+
						" tuyenbanhang b on a.TBH_FK = b.PK_SEQ inner join DAIDIENKINHDOANH "+
						" c on b.DDKD_FK = c.PK_SEQ inner join DIABAN db on c.diaban_fk = db.PK_SEQ inner join "+
						" KHUVUC kv on db.KHUVUC_FK = kv.PK_SEQ  where KHUVUC_FK in (select pk_seq from khuvuc where vung_fk ='" + this.vungId + "')"+ 
						") )";
				
			}
			if(this.ddkdId.length()>0)
				sql+=" and pk_seq in (select npp_fk from DAIDIENKINHDOANH_NPP where ddkd_fk ='"+this.ddkdId+"' ) ";
		System.out.println("NPP init: "+sql);
		String kenh = "select* from kenhbanhang";
		String khuvuc = "select * from khuvuc where trangthai = 1 ";
		
		
		String vung = "select pk_seq,ten from vung where trangthai = 1 ";
		
		if(this.phanloai.equals("2"))
		{
			if(loaiUser.equals("1"))
			{
				khuvuc+= "	and vung_fk in "+"(select vung_fk from bm_chinhanh where bm_fk = (select bm_fk from nhanvien where pk_seq = '"+this.userId+"'))  ";
			}
		
		}
		
		if(this.phanloai.equals("2"))
		{
			// user RSM
			
				vung+= " and pk_Seq in " + Ult.Quyen_Vung(userId)+" ";
		}
		//System.out.println("vung : "+vung);
		this.vungRs = db.get(vung);
		
		String tinhthanh = "select pk_seq, ten from diaban where TRANGTHAI = 1 ";
		
		if(this.vungId.length()>0){
			tinhthanh += "\n and khuvuc_fk IN (SELECT PK_SEQ FROM KHUVUC WHERE TRANGTHAI = 1 AND Vung_fk = '"+this.vungId+"')  ";
			khuvuc += " and VUNG_FK = " + this.vungId;
		}
		if(this.khuvucId.length() > 0)
			tinhthanh += " and KHUVUC_FK = " + this.khuvucId;
		tinhthanh += "\n order by ten";
		System.out.println("Dia ban "+tinhthanh);
		this.tinhthanhRs  = db.get(tinhthanh);
		System.out.println("khuvuc : " + khuvuc);
		this.kenh = db.get(kenh);
		this.khuvuc = db.get(khuvuc);
		this.npp = db.get(sql);
		
		sql = "select * from DAIDIENKINHDOANH d where TRANGTHAI = 1 ";
		if(this.vungId.length() > 0)
			sql += " and diaban_fk in (select PK_SEQ from DIABAN where KHUVUC_FK in (select PK_SEQ from KHUVUC where VUNG_FK ='"+this.vungId+"'))";
		if(khuvucId.length() > 0 )
			sql += " and diaban_fk in (select PK_SEQ from DIABAN where KHUVUC_FK = '"+this.khuvucId+"')";
        System.out.println("Tinh thanh trong bean "+tinhthanhId);
        if(this.tinhthanhId.length() > 0)
        	sql +=" and diaban_fk ='"+this.tinhthanhId+"'";	
        if(this.nppId.length() > 0)
        	sql +=" and d.PK_SEQ in (Select ddkd_fk from DAIDIENKINHDOANH_NPP where npp_fk='"+this.nppId+"' ) ";
        System.out.println("Get Ddkd "+sql);
        this.ddkd = db.get(sql);
        
        if(this.ddkdId.length() > 0)
        	sql ="select distinct ngaylamviec,ngayid from tuyenbanhang where ddkd_fk ='"+ this.ddkdId +"' order by ngayid asc";
        else
        	sql ="select distinct ngaylamviec,ngayid from tuyenbanhang order by ngayid asc ";
        this.tuyen = db.get(sql);

        
        
        sql ="select pk_seq, ten from BM where TRANGTHAI = 1";
        this.rsmRs = db.get(sql);
        sql ="select pk_seq, ten from GiamSatBanHang where TRANGTHAI = 1 ";
        if(this.rsmId.trim().length() >0)
        sql +=" and BM_FK =" + this.rsmId;	
        this.gsbhRs = db.get(sql);

		
	}


	
	public void settuyenId(String tuyenId) {
		
		this.tuyenId = tuyenId;
	}


	
	public String gettuyenId() {
		
		return this.tuyenId;
	}


	
	public ResultSet getTuyen() {
		
		return this.tuyen;
	}


	
	public ResultSet getdanhsach() {
		
		return this.danhsach;
	}

	
	public ResultSet getKenh() {
		
		return this.kenh;
	}
	
	public String getkenhId() {
		
		return this.kenhId;
	}

	@Override
	public void DBclose() {
		// TODO Auto-generated method stub
		try {
			if(this.db != null)
				this.db.shutDown();
			if(this.danhsach != null)
				this.danhsach.close();
			if(this.ddkd != null)
				this.ddkd.close();
			if(this.kenh != null)
				this.kenh.close();
			if(this.npp != null)
				this.npp.close();
			if(this.tuyen != null)
				this.tuyen.close();
			
				
		} catch (Exception e) {
			// TODO: handle exception
		}
	}

	@Override
	public void setkhuvucId(String khuvucId) {
		// TODO Auto-generated method stub
		this.khuvucId = khuvucId;
		
	}

	@Override
	public String getkhuvucId() {
		// TODO Auto-generated method stub
		return this.khuvucId;
	}

	@Override
	public ResultSet getkhuvuc() {
		// TODO Auto-generated method stub
		return this.khuvuc;
	}

	@Override
	public void createNPP() {
		// TODO Auto-generated method stub
		this.khuvuc = db.get("select * from khuvuc order by ten");
		String sql = "select * from nhaphanphoi where trangthai='"+this.status+"' order by ten";
		if(this.khuvucId.trim().length() > 0)
			sql = "select * from nhaphanphoi where khuvuc_fk = '"+this.khuvucId+"' and trangthai='"+this.status+"' order by ten";
		this.npp = db.get(sql);
		
		//System.out.println("nhapp: select * from nhaphanphoi where khuvuc_fk = '"+this.khuvucId+"' order by ten");
	}

	@Override
	public String getStatus() {
		// TODO Auto-generated method stub
		return this.status;
	}

	@Override
	public void setStatus(String status) {
		// TODO Auto-generated method stub
		this.status = status;
	}

	
	public String getTungay(){
		return this.tungay;
	}
	
	public void setTungay(String tungay){
		this.tungay = tungay;
	}

	public String getDenngay(){
		return this.denngay;
	}
	
	public void setDenngay(String denngay){
		this.denngay = denngay;
	}

	public void setVungId(String vungId) {
		this.vungId = vungId;
	}
	public String getVungId() {
		return vungId;
	}
	public ResultSet getVungRs() {
		return vungRs;
	}
	public void setTinhthanhId(String tinhthanhId) {
		this.tinhthanhId = tinhthanhId;
	}
	public String getTinhthanhId() {
		return tinhthanhId;
	}
	public ResultSet getTinhthanhRs() {
		return tinhthanhRs;
	}
	
	public String getRsmId() {
		return rsmId;
	}
	public void setRsmId(String rsmId) {
		this.rsmId = rsmId;
	}
	public String getGsbhId() {
		return gsbhId;
	}
	public void setGsbhId(String gsbhId) {
		this.gsbhId = gsbhId;
	}
	public ResultSet getRsmRs() {
		return rsmRs;
	}
	public ResultSet getGsbhRs() {
		return gsbhRs;
	}
	
	
}
