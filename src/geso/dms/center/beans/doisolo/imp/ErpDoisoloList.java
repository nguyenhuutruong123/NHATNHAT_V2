package geso.dms.center.beans.doisolo.imp;

import geso.dms.center.beans.doisolo.IErpDoisoloList;
import geso.dms.center.db.sql.dbutils;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ErpDoisoloList implements IErpDoisoloList
{
	private static final long serialVersionUID = -9217977546733610214L;

	String userId; 

	ResultSet nvbhRs;
	String nvbhId;
	
	String trangthai;
	String tungay;
	String denngay;
		
	String nppId;
	String nppTen;
	String sitecode;
	
	ResultSet chuyenkhoRs;
	
	String msg;
	
	dbutils db;

	public ErpDoisoloList()
	{
		this.nvbhId = "";
		this.trangthai = "";
		this.tungay = "";
		this.denngay = "";
		this.msg = "";

		db = new dbutils();
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
	
	public String getTungay() 
	{		
		return this.tungay;
	}
	
	public void setTungay(String tungay) 
	{
		this.tungay = tungay;	
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
		geso.dms.distributor.util.Utility util=new geso.dms.distributor.util.Utility();
		this.nppId=util.getIdNhapp(this.userId);
		this.nppTen=util.getTenNhaPP();
		this.sitecode=util.getSitecode();
	}

	public void init(String search) 
	{
		this.getNppInfo();
		
		String query = "";	
		if (search.length() == 0)
		{
			query = "select a.pk_seq, a.trangthai, a.ngaydoi, e.ten as khoTEN, a.ngaytao, c.ten as nguoitao, a.ngaysua, d.ten as nguoisua  " +
					"from ERP_DOISOLO a  " +
						"inner join nhanvien c on a.nguoitao = c.pk_seq " +
						"inner join nhanvien d on a.nguoisua = d.pk_seq  " +
						"inner join Erp_KhoTT e on a.kho_fk = e.pk_seq  " +
					"where 1=1  " +
					"order by a.pk_seq desc";
		}
		else
		{
			query = search;
		}	
		
		this.chuyenkhoRs = db.get(query);
		
	}

	
	public void createRS() 
	{
		String sql = "select pk_seq as nvbhId, ten as nvbhTen from daidienkinhdoanh where pk_seq in (select ddkd_fk from daidienkinhdoanh_npp where npp_fk='"+this.nppId+"') and trangthai = '1'";
		this.nvbhRs = db.get(sql);
	}

	public void DBclose() 
	{
		try 
		{
			if( nvbhRs != null )
				nvbhRs.close();
			if(this.db != null)
				this.db.shutDown();
		} 
		catch (SQLException e) {}
	}

	public String getMsg() 
	{
		return this.msg;
	}

	public void setMsg(String msg) 
	{
		this.msg = msg;
	}

	public String getDenngay() 
	{
		return this.denngay;
	}

	public void setDenngay(String tungay)
	{
		this.denngay = tungay;
	}

	public ResultSet getNhanvienBH() 
	{
		return this.nvbhRs;
	}

	public void setNhanvienBH(ResultSet nhanvienbh) 
	{
		this.nvbhRs = nhanvienbh;
	}

	public String getNvbhId() 
	{
		return this.nvbhId;
	}

	public void setNvbhId(String nvbhId)
	{
		this.nvbhId = nvbhId;
	}

	public ResultSet getRsChuyenKho()
	{
		return this.chuyenkhoRs;
	}

	public void setRsChuyenKho(ResultSet rsChuyenKho) 
	{
		this.chuyenkhoRs = rsChuyenKho;
	}		


		
}
