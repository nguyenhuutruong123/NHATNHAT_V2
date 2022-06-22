package geso.dms.center.beans.asm.imp;

import geso.dms.center.beans.asm.IAsm;
import java.sql.ResultSet;
import java.sql.SQLException;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Hashtable;

import javax.servlet.http.HttpServletRequest;

import geso.dms.center.db.sql.*;

public class Asm implements IAsm {
	public String Id;
public String Id1;
	public String asmTen;
	public String dienthoai;
	public String email;
	public String diachi;

	String mact, vitri, vungtt, sonamlamviec, sodtct, ngayvaoct, loaihd, ngaykthd, ngaykyhd, sotk, ghichu, dakyhd;

	String nganhang, chinhanh, manhanvien, mathuviec;

	public String kbhId;
	public ResultSet kbh;

	public String dvkdId;
	public ResultSet dvkd;

	public String[] kvId;
	public ResultSet kv;

	public String trangthai;
	public String msg;

	public String userId;

	public dbutils db ;

	String maFAST;

	public Asm(){
		this.Id = "";
		this.asmTen = "";
		this.dienthoai = "";
		this.email = "";
		this.diachi = "";
		this.kbhId = "";
		this.dvkdId = "";
		this.trangthai = "";
		this.msg = "";
		this.userId = "";

		this.mact = "";
		this.vungtt = "";
		this.dakyhd = "";
		this.ghichu = "";
		this.loaihd = "";
		this.ngaykthd = "";
		this.ngaykyhd = "";
		this.ngayvaoct = "";
		this.sodtct = "";
		this.sotk = "";
		this.sonamlamviec = "";
		this.vitri = "";

		this.nganhang = "";
		this.chinhanh = "";
		this.manhanvien = "";
		this.mathuviec = "";
		this.kbhId="";
		this.maFAST = "";

		this.db = new dbutils();
	}

	public String getMaFAST() {

		return this.maFAST;
	}


	public void MaFAST(String maFAST) {

		this.maFAST = maFAST;
	}

	public String getId(){
		return this.Id;
	}

	public void setId(String Id){
		this.Id = Id;
	}

	public String getTen(){
		return this.asmTen;
	}

	public void setTen(String asmTen){
		this.asmTen = asmTen;
	}

	public String getDienthoai(){
		return this.dienthoai;
	}

	public void setDienthoai(String dienthoai){
		this.dienthoai = dienthoai;
	}

	public String getEmail(){
		return this.email;
	}

	public void setEmail(String email){
		this.email = email;
	}

	public String getDiachi(){
		return this.diachi;	
	}	

	public void setDiachi(String diachi){
		this.diachi = diachi;
	}

	public String getKbhId(){
		return this.kbhId;
	}

	public void setKbhId(String kbhId){
		this.kbhId = kbhId;
	}

	public ResultSet getKbh(){
		return this.kbh;
	}

	public void setKbh(ResultSet kbh){
		this.kbh = kbh;
	}

	public String getDvkdId(){
		return this.dvkdId;
	}

	public void setDvkdId(String dvkdId){
		this.dvkdId = dvkdId;
	}

	public ResultSet getDvkd(){
		return this.dvkd;
	}

	public void setDvkd(ResultSet dvkd){
		this.dvkd = dvkd;
	}

	public String[] getKvId(){
		return this.kvId;
	}

	public void setKvId(String[] kvId){
		this.kvId = kvId;
	}

	public ResultSet getKv(){
		return this.kv;
	}

	public void setKv(ResultSet kv){
		this.kv = kv;
	}

	public String getTrangthai(){
		return this.trangthai;
	}

	public void setTrangthai(String trangthai){
		this.trangthai = trangthai;
	}

	public String getMsg(){
		return this.msg;
	}

	public void setMsg(String msg){
		this.msg = msg;
	}

	public String getUserId(){
		return this.userId;
	}

	public void setUserId(String userId){
		this.userId = userId;
	}

	public void init_New(){
		String sql 	= "SELECT PK_SEQ AS KBHID, TEN AS KBH FROM KENHBANHANG";// WHERE PK_SEQ = '100000' OR PK_SEQ = '100002'";
		System.out.println("[Asm.init_New] kbh sql = " + sql);
		this.kbh  	= this.db.get(sql);

		sql 		= "SELECT PK_SEQ AS DVKDID, DONVIKINHDOANH AS DVKD FROM DONVIKINHDOANH";// WHERE PK_SEQ = '100000' OR PK_SEQ = '100001'";
		System.out.println("[Asm.init_New] dvkd sql = " + sql);
		this.dvkd 	= this.db.get(sql);			

		String s="";
		if(this.bmId!=null && this.bmId.length()>0)
		{
			s=" AND  KHUVUC.VUNG_FK IN ( select a.VUNG_FK from BM_CHINHANH a  where a.BM_FK="+this.bmId +")";
			
			if(this.Id.trim().length()>0)
			{
				sql = " SELECT PK_SEQ AS KVID, TEN AS KV,b.ngaybatdau,b.ngayketthuc  FROM KHUVUC inner join ASM_KHUVUC b  "+
				" on KHUVUC.pk_seq=b.KHUVUC_FK WHERE B.ASM_FK= (select pk_seq from asm where PK_SEQ = '"+ this.Id +"') " +s+
				"  UNION  SELECT PK_SEQ AS KVID, TEN AS KV,'' as ngaybatdau,'' as ngayketthuc "+  
				" FROM KHUVUC WHERE TRANGTHAI = '1' " +
				" AND KHUVUC.PK_SEQ NOT IN " +
				" (SELECT KHUVUC_FK FROM ASM_KHUVUC WHERE ASM_FK = (select pk_seq from asm where PK_SEQ = '"+ this.Id +"')) "+s+"  ORDER BY TEN";  	
			}
			else
			{
				sql= "SELECT PK_SEQ AS KVID, TEN AS KV,'' as ngaybatdau,'' as ngayketthuc "+  
				"  FROM KHUVUC WHERE TRANGTHAI = '1' "+s+" order by ten ";
			}
			System.out.println("[Asm.init_New] kv sql = " + sql);
			this.kv = this.db.get(sql);
		}
		
		

		sql="select pk_Seq,ten from nhacungcap ";
		this.nccRs = this.db.get(sql);


	}

	public void init_Update()
	{
		try {
			String sql 	=	"SELECT ASM.BM_FK,ASM.kbh_fk,isnull(asm.mafast,'') as mafast,  ISNULL(ASM.MACONGTY,'') AS MACONGTY,ISNULL(ASM.VITRI,'') as VITRI,ISNULL(ASM.VUNGTT,'') AS VUNGTT, ISNULL(ASM.SOTAIKHOAN,'') AS SOTAIKHOAN, ASM.DAKYHD, ISNULL(ASM.GHICHU,'') AS GHICHU, ISNULL(ASM.LOAIHD,'') AS LOAIHD, ISNULL(ASM.NGAYKETTHUCHD,'') AS NGAYKETTHUCHD, ISNULL(ASM.NGAYKYHD,'') AS NGAYKYHD, ISNULL(ASM.NGAYVAOCONGTY,'') AS NGAYVAOCONGTY, ISNULL(ASM.SODTCONGTY,'') AS SODTCONGTY, ISNULL(ASM.SONAMLAMVIEC,'') AS SONAMLAMVIEC," +
			"ASM.PK_SEQ AS ASMID, ISNULL(ASM.TEN,'') AS ASMTEN, ISNULL(ASM.DIACHI,'') AS DIACHI, ISNULL(ASM.DIENTHOAI,'') AS DIENTHOAI, ISNULL(ASM.EMAIL,'') AS EMAIL, ISNULL(ASM.TRANGTHAI,'') AS TRANGTHAI, " +
			" ASM.NGAYTAO, ASM.NGUOITAO, ASM.NGAYSUA, ASM.NGUOISUA, KHUVUC.PK_SEQ AS KVID," +
			"ISNULL(KHUVUC.TEN,'') AS KV, DVKD.PK_SEQ AS DVKDID, " +
			"ISNULL(ASM.NGANHANG, '') AS NGANHANG, ISNULL(ASM.CHINHANH, '') AS CHINHANH, ASM.PK_SEQ AS MANHANVIEN, ISNULL(ASM.MATHUVIEC, '') AS MATHUVIEC,ASM.NCC_fk " +
			"FROM ASM ASM " +							
			"left JOIN ASM_KHUVUC ASM_KV ON ASM_KV.ASM_FK = ASM.PK_SEQ " +
			"left JOIN KHUVUC KHUVUC ON KHUVUC.PK_SEQ = ASM_KV.KHUVUC_FK " +
			"INNER JOIN DONVIKINHDOANH DVKD ON DVKD.PK_SEQ = ASM.DVKD_FK " +
			"WHERE ASM.PK_SEQ = '" + this.Id + "'";

			System.out.println("[Asm.init_Update] sql = " + sql);

			ResultSet rs = this.db.get(sql);
			rs.next();
			this.bmId = rs.getString("BM_FK") == null ? "" :   rs.getString("BM_FK");
			this.kbhId  = rs.getString("kbh_fk") == null ? "":rs.getString("kbh_fk");
			this.asmTen = rs.getString("asmten");
			this.diachi = rs.getString("diachi");
			this.dienthoai = rs.getString("dienthoai");
			this.email = rs.getString("email");
			this.trangthai = rs.getString("trangthai");
			//this.kbhId = rs.getString("kbhid");
			this.dvkdId = rs.getString("dvkdid");

			this.mact = rs.getString("macongty");
			this.vitri = rs.getString("vitri");
			this.vungtt = rs.getString("vungtt");
			this.sotk = rs.getString("sotaikhoan");
			String dakyhd = rs.getString("dakyhd") == null ? "" : rs.getString("dakyhd"); 
			this.dakyhd = dakyhd;
			this.ghichu = rs.getString("ghichu");
			this.loaihd = rs.getString("loaihd");
			this.ngaykthd = rs.getString("ngayketthuchd");
			this.ngaykyhd = rs.getString("ngaykyhd");
			this.ngayvaoct = rs.getString("ngayvaocongty");
			this.sodtct = rs.getString("sodtcongty");
			this.sonamlamviec = rs.getString("sonamlamviec");

			this.nganhang = rs.getString("nganhang");
			this.chinhanh = rs.getString("chinhanh");
			this.manhanvien = rs.getString("manhanvien");
			this.mathuviec = rs.getString("mathuviec");
			this.nccId = rs.getString("ncc_fk");
			this.maFAST = rs.getString("mafast");

			rs.close();

			sql = "SELECT COUNT(*) AS NUM FROM ASM_KHUVUC WHERE ASM_FK = '" + this.Id + "'";
			rs = this.db.get(sql);
			rs.next();
			int num = Integer.parseInt(rs.getString("NUM"));
			System.out.println("[Asm.init_Update] num = " + num);

			sql = "SELECT ASM_FK AS ASMID, KHUVUC_FK,NgayBatDau,NgayKetThuc AS NgayKetThuc FROM ASM_KHUVUC WHERE ASM_FK = '" + this.Id + "'";
			System.out.println("[Asm.init_Update] sql = " + sql);

			rs = this.db.get(sql);

			this.kvId = new String[num];

			int i = 0;
			while(rs.next()){
				System.out.println(this.kvId[i]);
				this.kvId[i] = rs.getString("KHUVUC_FK");
				i++;
			}
			if(rs!=null)
				rs.close();
			init_New();

		} catch(Exception e){
			System.out.println("[Asm.init_Update] Exception Message = " + e.getMessage());
			e.printStackTrace();
		}
	}

	public boolean Save(HttpServletRequest request){
		String sql = "";
		System.out.println("id:: "+this.Id);
		try{
			this.db.getConnection().setAutoCommit(false);

			if(this.maFAST==null||this.maFAST.length() <=0)
			{
				this.db.getConnection().rollback();
				this.msg = "Vui lòng chọn Mã khách hàng";
				return false;
			}
			if(this.kbhId==null||this.kbhId.length() <=0)
			{
				this.db.getConnection().rollback();
				this.msg = "Vui lòng chọn Kênh bán hàng";
				return false;
			}
			if(this.bmId.length() <=0)
			{
				geso.dms.center.util.Utility.rollback_throw_exception(this.db);
				this.msg = "Vui lòng chọn RSM trực thuộc!";
				return false;
			}

			if(userId==null||userId=="")
			{
				this.db.getConnection().rollback();
				this.msg = "Tài khoản đã hết hạn, vui lòng đăng nhập lại";
				return false;
			}
			sql="select count(*) from asm where mafast=N'"+this.maFAST+"' ";
			if(this.Id.trim().length()>0)
				sql+=" and pk_seq!='"+this.Id+"'";
			ResultSet rsCheck=db.get(sql);
			while(rsCheck.next())
			{
				if(rsCheck.getInt(1)>0)
				{
					this.msg="Mã nhân viên này đã có vui lòng nhập mã khác !";
					this.db.getConnection().rollback();
					return false;
				}
			}



			if(rsCheck!=null)
			{
				rsCheck.close();
			}
			System.out.println("ID "+Id);
			if(this.Id.trim().length() == 0){
				sql="select COUNT(maFAST) from ASM WHERE maFAST='"+this.maFAST+"' ";
				ResultSet rsCheckmafast=db.get(sql);
				while(rsCheckmafast.next())
				{
					if(rsCheckmafast.getInt(1)>0)
					{
						this.msg="Có lỗi phát sinh trong quá trình tạo mới. Mã giám sát bị trùng!";
						this.db.getConnection().rollback();
						return false;
					}
				}
				sql  = "INSERT INTO ASM (BM_FK,kbh_fk,TEN, DIACHI, EMAIL, DIENTHOAI, TRANGTHAI, NCC_FK, DVKD_FK, NGAYTAO, NGAYSUA, NGUOITAO, NGUOISUA, macongty, vitri, vungtt, sotaikhoan, dakyhd, ghichu, loaihd, ngayketthuchd, ngaykyhd, ngayvaocongty, sodtcongty, sonamlamviec, nganhang, chinhanh, mathuviec , MAFAST) " +
				"VALUES("+this.bmId+"," + this.kbhId + ",N'"+ this.asmTen + "',N'" + this.diachi + "','" + this.email + "','" + this.dienthoai + "','"+this.trangthai+"','"+this.nccId+"'," +
				" '" + this.dvkdId + "','" + this.getDateTime() + "','" + this.getDateTime()+"','" + this.userId + "','" + this.userId + "'," +
				"'"+this.mact+"', '"+this.vitri+"', '"+this.vungtt+"', '"+this.sotk+"', '"+this.dakyhd+"', '"+this.ghichu+"', '"+this.loaihd+"', '"+this.ngaykthd+"', '"+this.ngaykyhd+"', '"+this.ngayvaoct+"', '"+this.sodtct+"', '"+this.sonamlamviec+"', N'"+this.nganhang+"', N'"+this.chinhanh+"', N'"+this.mathuviec+"',  N'"+this.maFAST+ "')";
				System.out.println("SAVE "+sql);
				if(! this.db.update(sql))
				{
					this.db.getConnection().rollback();
					this.msg="Khong The Thuc Hien Luu ASM Nay,Vui Long Lien He Voi Admin De Sua Loi Nay";
					return false;
				}
				sql = "SELECT SCOPE_IDENTITY() AS ASMID";
				ResultSet rs = this.db.get(sql);
				rs.next();
				this.Id = rs.getString("asmId");
				if(rs!=null){
					rs.close();	
				}

			}else{
				sql="select COUNT(maFAST) FROM dbo.ASM  WHERE PK_SEQ NOT IN (SELECT PK_SEQ FROM dbo.ASM WHERE PK_SEQ='"+Id+"') AND maFAST='"+maFAST+"'";
				ResultSet rsCheckmafast=db.get(sql);
				while(rsCheckmafast.next())
				{
					if(rsCheckmafast.getInt(1)>0)
					{
						this.msg="Có lỗi phát sinh trong quá trình tạo mới. Mã giám sát bị trùng!";
						this.db.getConnection().rollback();
						return false;
					}
				}



				sql = " select d.ten, k.TEN kenhDDKD " +
				" from  giamsatbanhang d  " +
				" inner join KENHBANHANG k on k.PK_SEQ = d.KBH_FK " +
				" where d.trangthai = 1 and d.asm_fk ="+this.Id+"	and d.KBH_FK != "+this.kbhId+"";
				ResultSet rs = db.get(sql);
				if(rs.next()) 
				{
					this.msg =  "GSBH   " + rs.getString("ten") + " kênh (" + rs.getString("kenhDDKD") + ")  đang trực thuộc, bạn không thể đổi kênh!  ";
					this.db.getConnection().rollback();
					return false;

				} 

				sql = 	" UPDATE ASM SET BM_FK = "+this.bmId+", kbh_fk = " + this.kbhId + ",TEN = N'" + this.asmTen + "', DIACHI = N'" + this.diachi + "', EMAIL = '" + this.email + "', DIENTHOAI = '" + this.dienthoai + "', TRANGTHAI = '" + this.trangthai + "', " +
				" macongty = '"+this.mact+"', vitri = '"+this.vitri+"', vungtt = '"+this.vungtt+"', sonamlamviec = '"+this.sonamlamviec+"', sodtcongty = '"+this.sodtct+"', " +
				" ngayvaocongty = '"+this.ngayvaoct+"', loaihd = '"+this.loaihd+"',  ngayketthuchd = '"+this.ngaykthd+"', sotaikhoan = '"+this.sotk+"', ghichu = '"+this.ghichu+"', " +
				" ngaykyhd = '"+this.ngaykyhd+"', dakyhd = '"+this.dakyhd+"'," +
				" nganhang = N'"+this.nganhang+"', chinhanh = N'"+this.chinhanh+"', mathuviec = N'"+this.mathuviec+"', " +
				" NGAYSUA = '" + this.getDateTime() + "', NGUOISUA = '" + this.userId + "', MAFAST = N'" + this.maFAST + "' WHERE PK_SEQ = '" + this.Id + "'";

				System.out.println(sql);

				if(!this.db.update(sql))
				{
					this.db.getConnection().rollback();
					this.msg="Exception :"+sql;
					return false; 
				}				 				 
			}
			sql="delete from asm_khuvuc where asm_fk='"+this.Id+"' ";
			if(!this.db.update(sql))
			{
				this.db.getConnection().rollback();
				this.msg="Khong The Thuc Hien Luu ASM Nay,Vui Long Lien He Voi Admin De Sua Loi Nay. Loi :"+sql;
				return false; 
			}				 		

			/*for(int i = 0; i < this.kvId.length ; i++){
				 String ngaybatdau=request.getParameter("ngaybatdau"+this.kvId[i]);
				 String ngayketthuc=request.getParameter("ngayketthuc"+this.kvId[i]);
				 sql="select * from asm_khuvuc where asm_fk='"+this.Id+"' and khuvuc_fk='"+ this.kvId[i]+"'";
				 System.out.println(sql);
				 ResultSet rs=this.db.get(sql);
				sql = "INSERT INTO ASM_KHUVUC (ASM_FK, KHUVUC_FK,ngaybatdau,ngayketthuc) VALUES('" + this.Id+"','" + this.kvId[i] + "','"+ ngaybatdau+"','"+ngayketthuc+"')";


				 if(!this.db.update(sql))
				 {
					 this.db.getConnection().rollback();
						this.msg="Khong The Thuc Hien Luu ASM Nay,Vui Long Lien He Voi Admin De Sua Loi Nay" + sql;
						return false; 
				 }
			 }*/
			//Phat_a.tuan
			sql = "delete from ASM_KHUVUC where asm_fk = '"+this.Id+"'";
			if(!this.db.update(sql)){
				this.db.getConnection().rollback();
				this.msg="Khong The Thuc Hien Luu ASM Nay,Vui Long Lien He Voi Admin De Sua Loi Nay .Loi :"+sql;
				return false;
			}
			for(int i = 0; i < this.kvId.length ; i++){
				String ngaybatdau=request.getParameter("ngaybatdau"+this.kvId[i]);
				String ngayketthuc=request.getParameter("ngayketthuc"+this.kvId[i]);
				sql = "INSERT INTO ASM_KHUVUC (ASM_FK, KHUVUC_FK,NGAYBATDAU,NGAYKETTHUC) VALUES('" + this.Id+"','" + this.kvId[i] + "','"+ngaybatdau+"','"+ngayketthuc+"')";
				if(!this.db.update(sql)){
					this.db.getConnection().rollback();
					this.msg="Khong The Thuc Hien Luu ASM Nay,Vui Long Lien He Voi Admin De Sua Loi Nay .Loi :"+sql;
					return false;
				}
			}
			this.db.getConnection().commit();
		}catch(Exception e){
			try { this.db.getConnection().rollback(); } catch (SQLException e1) { e1.printStackTrace(); }
			this.msg="Khong The Thuc Hien Luu ASM Nay,Vui Long Lien He Voi Admin De Sua Loi Nay.Loi :" + e.toString();
			return false;
		} finally{ try { this.db.getConnection().setAutoCommit(true); } catch (SQLException e) { e.printStackTrace(); } }
		return true;
	}

	public Hashtable<String, String> getHTKvId(){
		Hashtable<String, String> ht = new Hashtable<String, String>();
		ht.put("0", "0");
		if(this.kvId != null){
			for(int i = 0; i < this.kvId.length; i++){
				ht.put(this.kvId[i], this.kvId[i]);
			}
		}
		return ht;
	}

	private String getDateTime() 
	{
		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		Date date = new Date();
		return dateFormat.format(date);	
	}

	public void DBClose(){
		try{
			if(kbh != null) kbh.close(); 
			if(dvkd != null) dvkd.close(); 
			if(kv != null) kv.close(); 
			if(this.db != null) this.db.shutDown();
		}catch(Exception e){}
	}

	public void setMaCt(String mact) {

		this.mact = mact;
	}


	public String getMaCt() {

		return this.mact;
	}


	public void setVitri(String vt) {

		this.vitri = vt;
	}


	public String getVitri() {

		return this.vitri;
	}


	public void setVungTT(String vungtt) {

		this.vungtt = vungtt;
	}


	public String getVungTT() {

		return this.vungtt;
	}


	public void setSonamlamviec(String sonam) {

		this.sonamlamviec = sonam;
	}


	public String getSonamlamviec() {

		return this.sonamlamviec;
	}


	public void setSoDTcongty(String sdt) {

		this.sodtct = sdt;
	}


	public String getSoDTcongty() {

		return this.sodtct;
	}


	public void setNgayvaoct(String ngayvaoct) {

		this.ngayvaoct = ngayvaoct;
	}


	public String getNgayvaoct() {

		return this.ngayvaoct;
	}


	public void setLoaiHD(String loaihd) {

		this.loaihd = loaihd;
	}


	public String getLoaiHD() {

		return this.loaihd;
	}


	public void setNgayketthucHD(String ngaykt) {

		this.ngaykthd = ngaykt;
	}


	public String getNgayketthucHD() {

		return this.ngaykthd;
	}


	public void setSotk(String stk) {

		this.sotk = stk;
	}


	public String getSotk() {

		return this.sotk;
	}


	public void setGhichu(String ghichu) {

		this.ghichu = ghichu;
	}


	public String getGhichu() {

		return this.ghichu;
	}


	public void setNgaykyHD(String ngaykyhd) {

		this.ngaykyhd = ngaykyhd;
	}


	public String getNgaykyHD() {

		return this.ngaykyhd;
	}


	public void setDakyHD(String dakyhd) {

		this.dakyhd = dakyhd;
	}


	public String getDakyHD() {

		return this.dakyhd;
	}

	@Override
	public void setNganHang(String nganhang) {
		this.nganhang = nganhang;
	}

	@Override
	public String getNganHang() {
		return this.nganhang;
	}

	@Override
	public void setChiNhanh(String chinhanh) {
		this.chinhanh = chinhanh;
	}

	@Override
	public String getChiNhanh() {
		return this.chinhanh;
	}

	@Override
	public void setMaNhanVien(String manhanvien) {
		this.manhanvien = manhanvien;
	}

	@Override
	public String getMaNhanVien() {
		return this.manhanvien;
	}

	@Override
	public void setMaThuViec(String mathuviec) {
		this.mathuviec = mathuviec;
	}

	@Override
	public String getMaThuViec() {
		return this.mathuviec;
	}

	String nccId;
	@Override
	public String getNccId()
	{
		return nccId;
	}

	@Override
	public void setNccId(String nccId)
	{
		this.nccId = nccId;

	}

	ResultSet nccRs;

	public ResultSet getNccRs()
	{
		return nccRs;
	}

	public void setNccRs(ResultSet nccRs)
	{
		this.nccRs = nccRs;
	}
	String bmId = "";
	public String getBmId() {
		return bmId;
	}
	public void setBmId(String bmId) {
		this.bmId = bmId;
	}
	public ResultSet getBmRs()
	{
		if(this.kbhId != null && this.kbhId.length() > 0)
		{
			String query = " select pk_seq, ten from BM where trangthai = 1 and kbh_fk = '"+ this.kbhId +"'  and exists ( select 1 from bm_chinhanh ct where ct.bm_fk = bm.pk_seq ) ";
			return db.get(query);
		}
		return null;
	}
}
