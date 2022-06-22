package geso.dms.distributor.beans.tamung.imp;

import java.sql.ResultSet;
import java.sql.SQLException;

import geso.dms.center.util.*;
import geso.dms.distributor.beans.tamung.IErpTamUngList;
import geso.dms.center.db.sql.dbutils;

public class ErpTamUngList extends Phan_Trang implements IErpTamUngList
{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	String UserId, TuNgay, TrangThai, NhanVienId, TienTeId, SoTienTamUng,
			ThoiGianHoanUng, Msg, DenNgay,DoiTuongTamUng,NccId,TenHienThi, congtyId, nppdangnhap, sochungtu;
	ResultSet rsTamUng, rsTienTe;
	dbutils db;

	public ErpTamUngList()
	{
		this.UserId = "";
		this.TuNgay = "";
		this.DenNgay = "";
		this.TrangThai = "";
		this.NhanVienId = "";
		this.TienTeId = "";
		this.SoTienTamUng = "";
		this.ThoiGianHoanUng = "";
		this.Msg = "";
		this.NccId="";
		this.DoiTuongTamUng="";
		this.TenHienThi="";
		this.congtyId = "";
		this.nppdangnhap = "";
		this.sochungtu = "";
		this.db = new dbutils();
	}

	public String getTuNgay()
	{

		return this.TuNgay;
	}

	public void setTuNgay(String TuNgay)
	{
		this.TuNgay = TuNgay;
	}

	public String getNhanVienId()
	{

		return this.NhanVienId;
	}

	public void setNhanVienId(String NhanVienId)
	{

		this.NhanVienId = NhanVienId;
	}

	public String getTrangThai()
	{

		return this.TrangThai;
	}

	public void setTrangThai(String TrangThai)
	{
		this.TrangThai = TrangThai;
	}

	public String getSoTienTamUng()
	{

		return this.SoTienTamUng;
	}

	public void setSoTienTamUng(String SoTienTamUng)
	{
		this.SoTienTamUng = SoTienTamUng;
	}

	public String getTienTeId()
	{

		return this.TienTeId;
	}

	public void setTienTeId(String TienTeId)
	{
		this.TienTeId = TienTeId;
	}

	public String getThoiGianHoanUng()
	{
		return this.ThoiGianHoanUng;
	}

	public void setThoiGianHoanUng(String ThoiGianHoanUng)
	{
		this.ThoiGianHoanUng = ThoiGianHoanUng;
	}

	public String getUserId()
	{

		return this.UserId;
	}

	public void setUserId(String UserId)
	{
		this.UserId = UserId;
	}

	public String getMsg()
	{
		return this.Msg;
	}

	public void setMsg(String Msg)
	{
		this.Msg = Msg;
	}

	public ResultSet getRsTamUng()
	{
		return this.rsTamUng;
	}

	public void setRsTamUng(ResultSet rsTamUng)
	{
		this.rsTamUng = rsTamUng;
	}

	public ResultSet getRsTienTe()
	{
		return this.rsTienTe;
	}

	public void setRsTienTe(ResultSet rsTienTe)
	{
		this.rsTienTe = rsTienTe;
	}

	public void init()
	{
		this.getNppInfo();
		String query = 
				"SELECT TU.PK_SEQ,TU.THOIGIANHOANUNG,TU.NGAYTAMUNG,TU.SOTIENTAMUNG,TU.TRANGTHAI, \n"+
				"		CASE WHEN TU.NCC_FK IS NOT NULL THEN NCC.MA +',' +NCC.TEN  \n"+
				"		WHEN TU.NHANVIEN_FK IS NOT NULL THEN NV.MA +',' +NV.TEN \n"+
				"		END AS NHANVIEN, \n"+
				" 		TU.HINHTHUCHOANUNG, \n"+
				" 		TT.MA AS TIENTE,NT.TEN AS NGUOITAO,NS.TEN AS NGUOISUA,TU.NGAYSUA,TU.NGAYTAO, TU.LYDOTAMUNG, ISNULL(TU.ISTHANHTOAN,0) ISTHANHTOAN, TU.DACHOT, "+
				" 		ISNULL(ISNULL(TU.ISQLTT, TU.ISKENH),0) CAPTREN, "+
				" 		ISNULL(TU.ISQLTT,0) ISQLTT, ISNULL(TU.ISKENH,0) ISKENH "+
				"FROM ERP_TAMUNG TU \n"+
				"INNER JOIN ERP_TIENTE TT ON TT.PK_SEQ = TU.TIENTE_FK \n"+
				"INNER JOIN NHANVIEN NT ON NT.PK_SEQ = TU.NGUOITAO \n"+
				"LEFT JOIN ERP_NHANVIEN NV ON NV.PK_SEQ = TU.NHANVIEN_FK \n"+
				"LEFT JOIN TraphacoERP.dbo.ERP_NHACUNGCAP NCC ON NCC.PK_SEQ = TU.NCC_FK \n"+
				"INNER JOIN NHANVIEN NS ON NS.PK_SEQ = TU.NGUOISUA WHERE 1 = 1 AND TU.NGUOITAO = "+this.UserId+" AND TU.NPP_FK = "+this.nppdangnhap;
		
		if (this.TuNgay.trim().length() > 0)
			query += " AND TU.NGAYTAMUNG>='" + this.TuNgay + "' ";

		if (this.DenNgay.trim().length() > 0)
			query += " AND TU.NGAYTAMUNG<='" + this.DenNgay + "' ";

		if (this.TrangThai.trim().length() > 0 && !this.TrangThai.equals("3"))
			query += " AND TU.TRANGTHAI='" + this.TrangThai + "' ";
		
		if(this.TrangThai.trim().length()>0 && this.TrangThai.equals("3"))
			query += " AND TU.ISTHANHTOAN = 1";
		
		if (this.NhanVienId.trim().length() > 0)
			query += " AND NV.PK_SEQ ='" + this.NhanVienId + "'  ";
		else if (this.NccId.trim().length() > 0)				
			query += " AND NCC.PK_SEQ ='" + this.NccId + "'  ";

		if (this.TienTeId.trim().length() > 0)
			query += " AND TT.MA ='" + this.TienTeId + "'  ";

		if (this.SoTienTamUng.trim().length() > 0)
			query += " AND TU.SOTIENTAMUNG ='" + this.SoTienTamUng.replaceAll(",", "") + "'  ";
		
		if (this.DoiTuongTamUng.length() > 0)
		{
			if(this.DoiTuongTamUng.equals("1"))
				query += " AND TU.NHANVIEN_FK IS NOT NULL  ";
			else
				query += " AND TU.NCC_FK IS NOT NULL  ";
		}
			
		if(this.sochungtu.length()>0)
		{
			query+= " AND TU.PK_SEQ = "+this.sochungtu;
		}

		System.out.println("Query Tam Ung List "+query);
		
		this.rsTamUng = db.get(query);

		query = "SELECT PK_SEQ,MA,TEN  FROM ERP_TIENTE WHERE TRANGTHAI = 1";
		System.out.println(query);
		this.rsTienTe = this.db.get(query);
	}

	public void DBClose()
	{
		try
		{
			if (rsTamUng != null)
				rsTamUng.close();
			if (rsTienTe != null)
				rsTienTe.close();
			if (db != null)
				this.db.shutDown();
		} catch (SQLException e)
		{
			e.printStackTrace();
		}

	}

	public String getDenNgay()
	{

		return this.DenNgay;
	}

	public void setDenNgay(String DenNgay)
	{
		this.DenNgay = DenNgay;
	}


	public String getNccId() {
		
		return this.NccId;
	}


	public void setNccId(String NccId) {
		this.NccId=NccId;
		
	}


	public String getDoiTuongTamUng() {
		
		return this.DoiTuongTamUng;
	}


	public void setDoiTuongTamUng(String DoiTuongTamUng) {
		this.DoiTuongTamUng=DoiTuongTamUng;
		
	}
	
	public String getTenHienThi() {
		
		return this.TenHienThi;
	}

	
	public void setTenHienThi(String TenHienThi) {
		this.TenHienThi=TenHienThi;
		
	}

	
	public String getCongtyId() {
		
		return this.congtyId;
	}

	
	public void setCongtyId(String congtyId) {
		
		this.congtyId = congtyId;
	}

	
	public String getnppdangnhap() {
		
		return this.nppdangnhap;
	}

	
	public void setnppdangnhap(String nppdangnhap) {
		
		this.nppdangnhap = nppdangnhap;
	}
	
	private void getNppInfo()
	{		
		//Phien ban moi
		geso.dms.distributor.util.Utility util=new geso.dms.distributor.util.Utility();
		this.nppdangnhap=util.getIdNhapp(this.UserId);
	}


	public String getSochungtu() {
	
		return this.sochungtu;
	}


	public void setSochungtu(String sochungtu) {
	
		this.sochungtu = sochungtu;
	}
}
