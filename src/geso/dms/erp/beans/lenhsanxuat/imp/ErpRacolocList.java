package geso.dms.erp.beans.lenhsanxuat.imp;

import java.sql.ResultSet;
import java.sql.SQLException;
import geso.dms.erp.beans.lenhsanxuat.IErpRacolocList;
import geso.dms.erp.db.sql.dbutils;

public class ErpRacolocList implements IErpRacolocList 
{
	String userId;
	String congtyId;
	String ma;
	String diengiai;
	String trangthai; 
	String msg;
	
	private String tungay;
	private String denngay;
	
	String sochungtu;
	
	public String getSochungtu() {
		return sochungtu;
	}

	public void setSochungtu(String sochungtu) {
		this.sochungtu = sochungtu;
	}
	
	public String getTungay() {
		return tungay;
	}

	public void setTungay(String tungay) {
		this.tungay = tungay;
	}

	public String getDenngay() {
		return denngay;
	}

	public void setDenngay(String denngay) {
		this.denngay = denngay;
	}
	
	ResultSet colocRs;
	
	dbutils db;
	
	public ErpRacolocList()
	{
		this.userId = "";

		this.ma = "";
		this.trangthai = "";
		this.diengiai = "";
		this.tungay = "";
		this.denngay = "";
		this.msg = "";
		this.sochungtu = "";
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

	public String getMa() 
	{
		return this.ma;
	}

	public void setMa(String ma) 
	{
		this.ma = ma;
	}
	
	public String getTrangthai() 
	{
		return this.trangthai;
	}

	public void setTrangthai(String trangthai) 
	{
		this.trangthai = trangthai;
	}

	public String getDiengiai() 
	{
		return this.diengiai;
	}

	public void setDiengiai(String diengiai) 
	{
		this.diengiai = diengiai;
	}

	public String getMsg() 
	{
		return this.msg;
	}

	public void setMsg(String msg) 
	{
		this.msg = msg;
	}

	public void init(String query) 
	{
		String sql = "";
		
		if(query.length() > 0)
			sql = query;
		else
		{	
			sql = "select a.pk_seq, a.ngaythuchien, d.ten as spTen, a.soluong, a.trangthai, b.ten as nguoitao, a.ngaytao, c.ten as nguoisua, a.ngaysua    " +
				  "from Erp_RaColoc a inner join NhanVien b on a.nguoitao = b.pk_seq    " +
				  		"inner join nhanvien c on a.nguoisua = c.pk_seq inner join SanPham d on a.sanpham_fk = d.pk_seq " +
				  "order by a.ngaythuchien desc";
		}
		
		System.out.println("__Nha may : " + sql);
		this.colocRs = db.get(sql);
	}

	public void DbClose() 
	{
		try 
		{
			if(this.colocRs != null)
				this.colocRs.close();
			this.db.shutDown();
		} 
		catch (SQLException e) {}
	}

	public ResultSet getRacolocRs() 
	{
		return this.colocRs;
	}

	public void setRacolocRs(ResultSet racolocRs) 
	{
		this.colocRs = racolocRs;
	}

	public String getCongtyId() 
	{
		return this.congtyId;
	}

	public void setCongtyId(String congtyId) 
	{
		this.congtyId = congtyId;
	}

	

}
