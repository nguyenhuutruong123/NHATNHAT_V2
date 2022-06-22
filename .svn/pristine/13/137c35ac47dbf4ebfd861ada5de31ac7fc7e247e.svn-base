package geso.dms.center.beans.tieuchithuong.imp;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import geso.dms.center.beans.tieuchithuong.ITieuchithuong;
import geso.dms.center.db.sql.dbutils;

public class Tieuchithuong implements ITieuchithuong
{
	public String tctId; 	
	public String diengiai;
	public ResultSet tct;
	public ResultSet nhomsp;

	public String tungay;
	public String denngay;

	public ResultSet nhomtc;
	public String nhomtcId;

	public String tcdiengiai;

	public String nhomspId;

	public String loai;

	public String loaiDS;

	public String thuongnsp;
	public String dstoithieudh;

	public String tongthuong;
	public String tldstoithieu;

	public String dvkdId;
	public String kbhId;
	public String thang;
	public String nam;
	public String tc;
	public String tcfk;
	public ResultSet tieuchi;

	public String[] ctct ;
	public String[] stt ;
	public String[] tu ;
	public String[] den ;
	public String[] thuong ;

	public String userId;	
	public String msg;
	public String action;

	String loaithuong;
	String hienthi;
	String thucdattoida = "",trongso = "";

	public String getHienthi() {
		return hienthi;
	}

	public void setHienthi(String hienthi) {
		this.hienthi = hienthi;
	}

	public String getLoaithuong() {
		return loaithuong;
	}

	public void setLoaithuong(String loaithuong) {
		this.loaithuong = loaithuong;
	}
	public String LoaiTieuChi;
	ResultSet rsKenh;
	ResultSet rsDVKD;

	public String toithieu;
	public String toida;

	dbutils db;


	public String[] tctctYeuCauIdList ;
	public String[] giatriTctctList;
	ResultSet tctctYeuCauRs;


	public String[] spDieuKienList ;
	public String[] soluongSpDieuKienList;
	ResultSet spDieuKienRs;

	public Tieuchithuong()
	{
		this.tungay = "";
		this.denngay = "";

		this.dvkdId = "";
		this.kbhId = "";
		this.tctId = "";
		this.thang = "";
		this.nam = "";

		this.loai = "";
		this.loaiDS = "";
		this.thuongnsp = "";
		this.dstoithieudh = "0";

		this.nhomtcId = "";
		this.tcdiengiai = "";

		this.tc = "";
		this.tcfk = "";
		this.action = "";
		this.diengiai="";
		this.msg = "";
		this.db = new dbutils();		
		this.tct = null;
		this.LoaiTieuChi="";
		this.toithieu = "";
		this.toida = "";
		this.nhomspId = "";
		this.tongthuong = "0";
		this.tldstoithieu = "0";
		this.hienthi="";
	}

	public void setDiengiai(String diengiai){
		this.diengiai = diengiai;
	}

	public String getDiengiai(){
		return this.diengiai;
	}

	public void setDvkd(String dvkdId){
		this.dvkdId = dvkdId;
	}

	public String getDvkd(){
		return this.dvkdId;
	}

	public void setKbh(String kbhId){
		this.kbhId = kbhId;
	}

	public String getKbh(){
		return this.kbhId;
	}

	public void setNam(String nam){
		this.nam = nam;
	}

	public String getNam(){
		return this.nam;
	}

	public void setThang(String thang){
		this.thang = thang;
	}

	public String getThang(){
		return this.thang;
	}

	public void setTct(ResultSet tct){
		this.tct = tct;
	}

	public ResultSet getTct(){
		return this.tct;
	}

	public void setTctId(String tctId){
		this.tctId = tctId;
	}

	public String getTctId(){
		return this.tctId;
	}

	public void setTieuchi(String tc){
		this.tc = tc;
	}

	public String getTieuchi(){
		return this.tc;
	}

	public String getUserId(){
		return this.userId;
	}

	public void setUserId(String userId)
	{
		this.userId = userId;

	}



	public void setMsg(String msg){
		this.msg = msg;
	}

	public String getMsg(){
		return this.msg;
	}

	public void setAction(String action){
		this.action = action;
	}

	public String getAction(){
		return this.action;
	}

	public void setTieuchiRS(ResultSet tieuchi){
		this.tieuchi = tieuchi;
	}

	public ResultSet getTieuchiRS(){
		return this.tieuchi;
	}

	public void setData(String[] ctct, String[] stt, String[] tu, String[] den, String[] thuong){
		this.ctct = ctct;
		this.stt = stt;
		this.tu = tu;
		this.den = den;
		this.thuong = thuong;

	}
	public boolean Save()
	{
		try
		{

			String query;
			if(this.tctId.length() > 0 && !this.tctId.equals("0") )
			{
				db.getConnection().setAutoCommit(false);

				query = "UPDATE TIEUCHITINHTHUONG SET DIENGIAI =N'"+ this.diengiai + "', thang = '" + this.thang + "', nam = '" + this.nam + "'," +
				"DVKD_FK='" + this.dvkdId + "', KBH_FK = '"+ this.kbhId + "', NGUOISUA ='" + this.userId + "', NGAYSUA = '" + this.getDateTime() + "', tongthuong = '"+ this.tongthuong.replaceAll(",", "") +"', " +
				"TYLEDOANHSOTOITHIEU = '"+ this.tldstoithieu.replaceAll(",", "") +"' " +
				"WHERE PK_SEQ = '" + this.tctId + "'";

				//System.out.println("115.Update TCT: " + query);
				if(!this.db.update(query))
				{
					db.getConnection().rollback();
					this.msg = "Lỗi::" + query;
					return false;
				}


				String chuoi = "";

				if(this.tcfk == null || this.tcfk.length() <= 0)
				{
					db.getConnection().rollback();
					this.msg = "Lỗi: Chưa chọn tiêu chí";
					return false;
				}

				String denngayInsert = "";
				String tungayInsert = "";
				if(this.denngay == "")
					denngayInsert = "null";
				else
					denngayInsert = "'"+ this.denngay +"'"; 

				if(this.tungay == "")
					tungayInsert = "null";
				else
					tungayInsert = "'"+ this.tungay +"'"; 
				
				if(this.ctct.length > 0)
				{
					for(int i = 0; i < this.ctct.length; i++)
					{
						chuoi += this.ctct[i] +","+ this.stt[i] +","+ this.tu[i].replaceAll(",", "") +","+ this.den[i].replaceAll(",", "") +","+ this.thuong[i].replaceAll(",", "") +";";
					}
				}
				if(chuoi!="")
				{
					query = " UPDATE TIEUCHITHUONG_CHITIET SET NOIDUNG = '" + chuoi.substring(0, chuoi.length() - 1) + "', " +
					" NGUOISUA = '" + this.userId + "', NGAYSUA = '" + this.getDateTime() + "', TOITHIEU = '"+ this.toithieu.replaceAll(",", "") +"', " +
					" TOIDA = '"+ this.toida.replaceAll(",", "") +"', NHOMSP_FK = "+ (this.nhomspId.trim().length() == 0 ? "null" :  this.nhomspId) +", THUONG = '"+ this.thuongnsp.replaceAll(",", "") +"', LOAI = '"+ this.loai +"', DSTOITHIEUDH = '"+ this.dstoithieudh.replaceAll(",", "") +"', LOAIDS = '"+ this.loaiDS +"', TUNGAY = "+ tungayInsert +", DENNGAY = "+ denngayInsert +" "+
					" WHERE TIEUCHITINHTHUONG_FK = '" + this.tctId + "' AND PK_SEQ = '" + this.tcfk + "' ";
				}
				else
				query = " UPDATE TIEUCHITHUONG_CHITIET SET NGUOISUA = '" + this.userId + "', NGAYSUA = '" + this.getDateTime() + "', TOITHIEU = '"+ this.toithieu.replaceAll(",", "") +"', " +
				" TOIDA = '"+ this.toida.replaceAll(",", "") +"', NHOMSP_FK = "+ ((this.nhomspId == "" || this.nhomspId.length() <= 0 ) ? null :  this.nhomspId) +", THUONG = '"+ this.thuongnsp.replaceAll(",", "") +"', LOAI = '"+ this.loai +"', DSTOITHIEUDH = '"+ this.dstoithieudh.replaceAll(",", "") +"', LOAIDS = '"+ this.loaiDS +"', TUNGAY = "+ tungayInsert +", DENNGAY = "+ denngayInsert +"  "+
				" WHERE TIEUCHITINHTHUONG_FK = '" + this.tctId + "' AND PK_SEQ = '" + this.tcfk + "' ";
			
				System.out.println("116.Update TCT lan 2 : " + query);
				if(!this.db.update(query))
				{
					db.getConnection().rollback();
					db.getConnection().setAutoCommit(true);
					this.msg = "Lỗi cập nhật" + query;
					return false;
				}
				query = "DELETE from TieuChiThuong_ChiTiet_MucThuong WHERE TCTCT_FK =" + this.tcfk;
				if(!this.db.update(query))
				{
					db.getConnection().rollback();
					this.msg = "Lỗi cập nhật" + query;
					return false;
				}

				
				query = "SELECT PK_SEQ AS TCTCT_FK, NOIDUNG FROM TieuChiThuong_ChiTiet WHERE pk_Seq =" +this.tcfk;
				System.out.println(query);
				ResultSet rs = db.get(query);
				while(rs.next())
				{
					String[] strNoidung = rs.getString("noidung").split(";");
					for(int i = 0; i < strNoidung.length; i++)
					{ 
						query = "INSERT INTO TieuChiThuong_ChiTiet_MucThuong (TCTCT_FK, STT, TU, DEN, THUONG) VALUES ('"+ rs.getString("TCTCT_FK") +"', '"+ strNoidung[i].split(",")[1] +"', '"+ strNoidung[i].split(",")[2] +"', '"+ strNoidung[i].split(",")[3] +"', '"+ strNoidung[i].split(",")[4] +"')";
						if(!this.db.update(query))
						{
							db.getConnection().rollback();
							this.msg = "Lỗi cập nhật" + query;
							return false;
						}			
					}
				}


				db.getConnection().commit();
				db.getConnection().setAutoCommit(true);
				this.msg = "Tiêu chí tính thưởng đã được cập nhật";
				return true;
			}
			else
			{			
				db.getConnection().setAutoCommit(false);

				query = "INSERT INTO TIEUCHITINHTHUONG(trangthai ,NgayTao,NguoiTao,NGAYSUA,NGUOISUA,DienGiai,THANG, NAM, DVKD_FK, KBH_FK, LOAI) " +
				"SELECT 0,'" + this.getDateTime() + "', " + this.userId + ",'" + this.getDateTime() + "'," + this.userId + ",N'"+this.diengiai+"'," + this.thang + ", " + this.nam + ", " + this.dvkdId + ", "+ this.kbhId + ", "+this.LoaiTieuChi+"";
				if(!this.db.update(query))
				{
					db.getConnection().rollback();
					this.msg = "Lỗi::" + query;
					return false;
				}

				query = "SELECT SCOPE_IDENTITY() as id";
				ResultSet rs = this.db.get(query);
				rs.next();
				this.tctId = rs.getString("id");
				db.getConnection().commit();
				this.msg = "Tạo mới Loại chỉ tiêu thành công";
				return true;

			}


		}catch (Exception e) {
			this.msg  ="Lỗi exception="  + e.getMessage();
			e.printStackTrace();
			return false;
		}
	}

	private String getDateTime() 
	{
		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd: hh-mm-ss");
		Date date = new Date();
		return dateFormat.format(date);	
	}	

	public void init()
	{
		//CreateRs();
		System.out.println("loai thuong "+this.loaithuong);
		String query = "";
		if(this.action.equals("copy"))
		{
			query = "INSERT INTO TIEUCHITINHTHUONG(THANG, NAM, DVKD_FK, KBH_FK, LOAI, TONGTHUONG, TYLEDOANHSOTOITHIEU,npp_fk, tructhuoc_fk) " +
			"SELECT THANG, NAM, DVKD_FK, KBH_FK, LOAI, TONGTHUONG, TYLEDOANHSOTOITHIEU,npp_fk, tructhuoc_fk FROM TIEUCHITINHTHUONG WHERE PK_SEQ = '" + this.tctId + "'";
			this.db.update(query);

			query = "SELECT SCOPE_IDENTITY() as id";
			ResultSet rs = this.db.get(query);
			try
			{
				rs.next();
				String id = rs.getString("id");
				query = "UPDATE TIEUCHITINHTHUONG SET DIENGIAI = ' ', THANG = ' ', NGUOITAO='" + this.userId + "', NGUOISUA='" + this.userId + "'," +
				"NGAYTAO = '" + this.getDateTime() + "', NGAYSUA = '" + this.getDateTime() + "', TRANGTHAI = '0' " +
				"WHERE PK_SEQ ='" + id + "'";
				this.db.update(query);


				query = "INSERT INTO TIEUCHITHUONG_CHITIET (TIEUCHITINHTHUONG_FK, DIENGIAI, NOIDUNG, TIEUCHI, NGUOISUA, NGAYSUA, TOITHIEU, TOIDA, NHOMSP_FK, THUONG, DSTOITHIEUDH, LOAI, LOAIDS, LOAINVBH, TUNGAY, DENNGAY,TRONGSO,THUCDATTOIDA) " +
				"SELECT '"+ id +"' AS TEUCHITINHTHUONG_FK, DIENGIAI, NOIDUNG, TIEUCHI, '"+ this.userId +"' AS NGUOISUA, '"+ this.getDateTime() +"' AS NGAYSUA, TOITHIEU, TOIDA, NHOMSP_FK, THUONG, DSTOITHIEUDH, LOAI, LOAIDS, LOAINVBH, TUNGAY, DENNGAY,TRONGSO,THUCDATTOIDA " +
				"FROM TIEUCHITHUONG_CHITIET WHERE TIEUCHITINHTHUONG_FK = '"+ this.tctId +"' ";

				//System.out.println("insert : "+query);
				this.db.update(query);


				this.tctId = id;
			}catch(Exception e){}
		}


		if(this.tctId.length() > 0)
		{
			query = " SELECT TCT.PK_SEQ, TCT.DIENGIAI, KBH.PK_SEQ AS KBHID, " + 
			" DVKD.PK_SEQ AS DVKDID, TCT.THANG, TCT.NAM ,tct.loai, ISNULL(TCT.TONGTHUONG,0) AS TONGTHUONG, ISNULL(TCT.TYLEDOANHSOTOITHIEU, 0) AS TYLEDOANHSOTOITHIEU " +
			" FROM TIEUCHITINHTHUONG TCT " +
			" INNER JOIN KENHBANHANG KBH ON KBH.PK_SEQ = TCT.KBH_FK " +
			" INNER JOIN DONVIKINHDOANH DVKD ON DVKD.PK_SEQ = TCT.DVKD_FK " +
			" WHERE TCT.PK_SEQ = '" + this.tctId + "' ";
			System.out.println(query);
			this.tct = this.db.get(query);
			try{
				this.tct.next();
				this.diengiai = this.tct.getString("diengiai");
				this.kbhId = this.tct.getString("kbhId");
				this.dvkdId = this.tct.getString("dvkdId");
				this.thang = this.tct.getString("thang");
				this.nam = this.tct.getString("nam");
				this.LoaiTieuChi=this.tct.getString("loai");

				this.tongthuong = this.tct.getString("tongthuong");
				this.tldstoithieu = this.tct.getString("tyledoanhsotoithieu");

				//System.out.println("Loai nek k: "+this.tct.getString("loai"));

			}catch(Exception e){}


			query = 				
				"SELECT pk_seq, tieuchitinhthuong_fk, pk_seq as tcfk, noidung, nguoisua, ngaysua, isnull(toithieu, 0) as toithieu" +
				", isnull(toida, 0) as toida, isnull(nhomsp_fk, 0) as nhomsp_fk, isnull(thuong, 0) as thuong, isnull(loai, 0) as loai" +
				", isnull(dstoithieudh, 0) as dstoithieudh,   " +
				"ISNULL(LOAIDS, 0) AS LOAIDS, ISNULL(TUNGAY, '') AS TUNGAY, ISNULL(DENNGAY, '') AS DENNGAY,isnull(thucdattoida,0) as thucdattoida, isnull(trongso,0) as trongso " +
				"FROM TIEUCHITHUONG_CHITIET " + 
				"WHERE TIEUCHITINHTHUONG_FK = '"+ this.tctId +"' AND tieuchi='" + tc + "' ";

			if(this.tcfk != null && !this.tcfk.equals(""))
			{ query += " and pk_seq = '"+ this.tcfk +"'"; }
			this.tct = this.db.getScrol(query);

			System.out.println("1.Query Khoi Tao: " + query);

			//query  = "SELECT DISTINCT TIEUCHI AS TC, DIENGIAI FROM TIEUCHITINHTHUONG_CHITIET WHERE TIEUCHITINHTHUONG_FK='"+ this.tctId  +"'";
			query  = "SELECT pk_seq AS TC, tieuchi, DIENGIAI FROM TIEUCHITHUONG_CHITIET WHERE TIEUCHITINHTHUONG_FK='"+ this.tctId  +"' order by tieuchi, pk_seq asc ";
			System.out.println("2.Get Tieu Chi: " + query);

			this.tieuchi = this.db.get(query);

			if( this.tc.equals("10") || this.tc.equals("11")  )
			{
				//INIT TIEU CHI DUOC CHON
				query = "select tctctYeuCau_FK from TieuChiThuong_ChiTiet_TieuChiKep where tctct_fk = '" + this.tcfk + "'";
				System.out.println("QRTCK "+query);
				ResultSet rs = db.get(query);
				if( rs != null )
				{
					try 
					{
						String nhomtieuchiId = "";

						while( rs.next() )
						{
							if( !nhomtieuchiId.contains(rs.getString("tctctYeuCau_FK") ) )
								nhomtieuchiId += rs.getString("tctctYeuCau_FK") + ",";

						}
						rs.close();
						if( nhomtieuchiId.trim().length() > 0 )
							this.nhomspId = nhomtieuchiId.substring(0, nhomtieuchiId.length() - 1);

					} 
					catch (Exception e) {
						e.printStackTrace();
					}
				}


			}

			System.out.println("QRTCK "+this.nhomspId);
		}
		CreateRs();

	}
	public void CreateRs() {

		this.rsDVKD=db.get("select pk_Seq,donvikinhdoanh as ten from donvikinhdoanh where trangthai = 1 ");
		this.rsKenh=db.get("select pk_Seq,ten from kenhbanhang where trangthai = 1 ");
		String query = "SELECT * FROM TIEUCHI WHERE LOAI = '"+ this.LoaiTieuChi +"' order by tieuchi ";
		System.out.println("Query tieu chi: "+query);
		this.nhomtc = this.db.get("SELECT * FROM TIEUCHI WHERE LOAI = '"+ this.LoaiTieuChi +"' order by tieuchi ");
		String sqlnsp = " select PK_SEQ, (CAST(PK_SEQ AS VARCHAR(50)) + ' - ' + TEN) AS TEN from NhomSanPhamChiTieu where TRANGTHAI = '1' ";
		if(!thang.equals("") && !nam.equals("")) {
				sqlnsp += " AND MONTH(TUNGAY) = '"+thang+"' AND YEAR(TUNGAY) = '"+nam+"' ";
		}
		this.nhomsp = db.get(sqlnsp);
		if(this.tc.equals("10"))
			this.nhomsp = db.get("select PK_SEQ, DIENGIAI as TEN from TIEUCHITHUONG_CHITIET WHERE TIEUCHITINHTHUONG_FK='" + this.tctId + "' and tieuchi = 1 ");
		else
			if(this.tc.equals("11"))
				this.nhomsp = db.get("select PK_SEQ, DIENGIAI as TEN from TIEUCHITHUONG_CHITIET WHERE TIEUCHITINHTHUONG_FK='" + this.tctId + "' and tieuchi = 7 ");



	}

	public void closeDB(){
		try{
			if(rsDVKD!=null){
				rsDVKD.close();
			}
			if(rsKenh!=null){
				rsKenh.close();
			}
			if (this.db != null)
				this.db.shutDown();
		}
		catch(Exception er){
			//er.printStackTrace();
		}
	}

	public void SetLoaiTieuChi(String loaitieuchi) {

		this.LoaiTieuChi=loaitieuchi;
	}


	public String GetLoaiTieuChi() {

		return this.LoaiTieuChi;
	}


	public ResultSet GetRsKenh() {

		return this.rsKenh;
	}


	public ResultSet GetRsDVKD() {

		return this.rsDVKD;
	}


	public void setToithieu(String toithieu) {

		this.toithieu = toithieu;
	}


	public String getToithieu() {

		return this.toithieu;
	}


	public void setToida(String toida) {

		this.toida = toida;
	}


	public String getToida() {

		return this.toida;
	}


	public ResultSet GetRsNhomSp() {

		return this.nhomsp;
	}


	public String getNhomsp() {

		return this.nhomspId;
	}


	public void setNhomsp(String nhomsp) {

		this.nhomspId = nhomsp;
	}


	public void setLoai(String loai) {

		this.loai = loai;
	}


	public String getLoai() {

		return this.loai;
	}


	public void setThuongnsp(String thuongnsp) {

		this.thuongnsp = thuongnsp;
	}


	public String getThuongnsp() {

		return this.thuongnsp;
	}


	public void setDstoithieuDH(String dstoithieudh) {

		this.dstoithieudh = dstoithieudh;
	}


	public String getDstoithieuDH() {

		return this.dstoithieudh;
	}


	public void setTongThuong(String tongthuong) {

		this.tongthuong = tongthuong;
	}


	public String getTongThuong() {

		return this.tongthuong;
	}


	public void setTileDStoithieu(String tldstoithieu) {

		this.tldstoithieu = tldstoithieu;
	}


	public String getTileDStoithieu() {

		return this.tldstoithieu;
	}


	public void setTieuchiFK(String tcfk) {

		this.tcfk = tcfk;	
	}


	public String getTieuchiFK() {

		return this.tcfk;
	}


	public void setLoaiDS(String loaids) {

		this.loaiDS = loaids;
	}


	public String getLoaiDS() {

		return this.loaiDS;
	}


	public void setNhomtcRS(ResultSet nhomtc) {

		this.nhomtc = nhomtc;
	}


	public ResultSet getNhomtcRS() {

		return this.nhomtc;
	}


	public void setTCDiengiai(String tcdiengiai) {

		this.tcdiengiai = tcdiengiai;
	}


	public String getTCDiengiai() {

		return this.tcdiengiai;
	}


	public void setTCNhomId(String nhomtcid) {

		this.nhomtcId = nhomtcid;
	}


	public String getTCNhomId() {

		return this.nhomtcId;
	}

	@Override
	public boolean xoaTC() {

		boolean result = true;
		String query = "DELETE TIEUCHITHUONG_CHITIET WHERE PK_SEQ = '"+ this.tcfk +"' AND TIEUCHITINHTHUONG_FK = '"+ this.tctId +"'";
		System.out.println("delete tc : "+query);
		if(!this.db.update(query)){
			result = false;
		}
		query = "DELETE TieuChiThuong_ChiTiet_MucThuong WHERE TCTCT_FK = '"+ this.tcfk +"' ";
		System.out.println("delete tc : "+query);
		if(!this.db.update(query)){
			result = false;
		}
		if(result) 
			this.msg = "Tiêu chí đã được xóa.";
		else
			this.msg = "Lỗi trong quá trình xóa tiêu chí. Vui lòng liên hệ trung tâm để được xử lý.";
		return result;
	}

	@Override
	public String getTungay() {
		// TODO Auto-generated method stub
		return this.tungay;
	}

	@Override
	public void setTungay(String tungay) {
		// TODO Auto-generated method stub
		this.tungay = tungay;
	}

	@Override
	public String getDenngay() {
		// TODO Auto-generated method stub
		return this.denngay;
	}

	@Override
	public void setDenngay(String denngay) {
		// TODO Auto-generated method stub
		this.denngay = denngay;
	}

	public String[] getTctctYeuCauIdList() {
		return tctctYeuCauIdList;
	}
	public void setTctctYeuCauIdList(String[] tctctYeuCauIdList) {
		this.tctctYeuCauIdList = tctctYeuCauIdList;
	}
	public String[] getGiatriTctctList() {
		return giatriTctctList;
	}
	public void setGiatriTctctList(String[] giatriTctctList) {
		this.giatriTctctList = giatriTctctList;
	}
	public ResultSet getTctctYeuCauRs() {
		return tctctYeuCauRs;
	}

	public String[] getSoluongSpDieuKienList() {
		return soluongSpDieuKienList;
	}
	public String[] getSpDieuKienList() {
		return spDieuKienList;
	}
	public ResultSet getSpDieuKienRs() {
		return spDieuKienRs;
	}
	public void setSoluongSpDieuKienList(String[] soluongSpDieuKienList) {
		this.soluongSpDieuKienList = soluongSpDieuKienList;
	}
	public void setSpDieuKienList(String[] spDieuKienList) {
		this.spDieuKienList = spDieuKienList;
	}
	public void setSpDieuKienRs(ResultSet spDieuKienRs) {
		this.spDieuKienRs = spDieuKienRs;
	}

	@Override
	public String getTrongSo() {
		// TODO Auto-generated method stub
		return this.trongso;
	}

	@Override
	public void setTrongSo(String trongso) {
		this.trongso = trongso;

	}

	@Override
	public String getThucDatToiDa() {
		// TODO Auto-generated method stub
		return this.thucdattoida;
	}

	@Override
	public void setThucDatToiDa(String thucdattoida) {
		this.thucdattoida = thucdattoida;

	}
}
