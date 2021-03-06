package geso.dms.distributor.beans.erp_chinhanh.imp;

import java.sql.ResultSet;
import java.sql.SQLException;

import geso.dms.center.util.Phan_Trang;
import geso.dms.distributor.util.Utility;
import geso.dms.center.db.sql.dbutils;
import  geso.dms.distributor.beans.erp_chinhanh.IErp_chinhanhList;

public class Erp_chinhanhList  extends Phan_Trang implements IErp_chinhanhList
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 668328923969461422L;
	dbutils db;
	String ID;
	String userId;
	String userTen;
	String MA;
	String TEN;
	String NGAYTAO;
	String NGAYSUA;
	String NGUOITAO;
	String NGUOISUA;
	String TRANGTHAI;
	String Msg;
	String nppid;
	String congtyId;
	ResultSet Rscn;
	
	String chixem;
	
	public Erp_chinhanhList()
	{
		db = new dbutils();
		this.userId = "";
		this.userTen = "";
		this.ID = "";
		this.MA = "";
		this.TEN = "";
		this.NGAYTAO = "";
		this.NGAYSUA = "";
		this.NGUOITAO = "";
		this.NGUOISUA = "";
		this.TRANGTHAI = "";
		this.Msg = "";
		this.nppid = "";		
		this.chixem = "0";
		this.congtyId = "";
	}

	
	public String getID() {
		
		return ID;
	}

	
	public String getMA() {
		
		return MA;
	}

	
	public String getTEN() {
		
		return TEN;
	}

	
	public String getNGAYTAO() {
		
		return NGAYTAO;
	}

	
	public String getNGAYSUA() {
		
		return NGAYSUA;
	}

	
	
	
	public String getNGUOITAO() {
		
		return NGUOITAO;
	}

	
	public String getNGUOISUA() {
		
		return NGUOISUA;
	}

	
	public String getTRANGTHAI() {
		
		return TRANGTHAI;
	}

	
	public String getMsg() {
		
		return Msg;
	}

	
	public ResultSet getRscn() {
		
		return Rscn;
	}

	
	
	
	public void setID(String ID) {
		
		this.ID = ID;
	}

	public void setMA(String MA) {
		
		this.MA = MA;
	}

	
	public void setTEN(String TEN) {
		
		this.TEN = TEN;
	}

	
	public void setNGAYTAO(String NGAYTAO) {
		
		this.NGAYTAO = NGAYTAO;
	}

	
	public void setNGAYSUA(String NGAYSUA) {
		
		this.NGAYSUA = NGAYSUA;
	}

	
	public void setNGUOITAO(String NGUOITAO) {
		
		this.NGUOITAO = NGUOITAO;
	}

	
	public void setNGUOISUA(String NGUOISUA) {
		
		this.NGUOISUA = NGUOISUA;
	}

	
	public void setTRANGTHAI(String TRANGTHAI) {
		
		this.TRANGTHAI = TRANGTHAI;
	}

	
	public void setMsg(String Msg) {
		
		this.Msg = Msg;
	}

	

	
	public void init(String sql) 
	{
		Utility util = new Utility();
		
		String query =" SELECT CN.PK_SEQ AS ID_CN,CN.MA,CN.TEN,isnull(CN.TRANGTHAI,0) AS TT ,CN.NGAYTAO ,CN.NGAYSUA ,NT.TEN AS \n"+
					  " NGUOITAO,NS.TEN AS NGUOISUA \n"+
					  " FROM ERP_CHINHANH CN \n"+
					  " INNER JOIN NHANVIEN NT  ON NT.PK_SEQ = CN.NGUOITAO \n"+
					  " INNER JOIN NHANVIEN NS ON NS.PK_SEQ = CN.NGUOISUA where 1= 1  " ;				
		if (this.MA.trim().length() > 0)
			query += " and CN.ma like N'%" + this.MA+ "%'";
		if (this.TEN.trim().length() > 0)
			query += " and dbo.ftBoDau(CN.ten) like N'%" + util.replaceAEIOU(this.TEN) + "%'";
		if (this.TRANGTHAI.length() > 0)
			query += " and CN.TRANGTHAI = '" + TRANGTHAI + "' ";
		if (this.NGAYTAO.length() > 0)
			query += " and cn.ngaytao >= '%" + NGAYTAO + "%'";
		this.Rscn = db.get(query);
		
		System.out.println("query list " + query);
		
	}
	
	
	
	public void DBClose() 
	{
		if(this.Rscn!=null)
			try
			{
				this.Rscn.close();
				if(this.db!=null)
					this.db.shutDown();
			} catch (SQLException e)
			{
			
				e.printStackTrace();
			}
	}

	
	
	public void setUserTen(String userten) {
		this.userTen=userten;
		
	}

	
	public String getUserTen() {
		
		return this.userTen;
	}

	
	public void setUserid(String userid) {
		
		this.userId = userid;
	}

	
	public String getUserid() {
		
		return userId;
	}

	
	public boolean CheckReferences(String column,String table)
	{
		String query="SELECT count("+column+") AS NUM  FROM "+table+" WHERE "+column+" ="+this.ID+""; 
		System.out.println("CheckReferences "+query);
		ResultSet rs = db.get(query);
		System.out.println("____Kiem tra rang buoc_____ "+query);
		try {//kiem tra ben san pham
		while(rs.next())
		{ if(rs.getString("num").equals("0"))
		   return false;
		}
		} catch (SQLException e) {
		
			e.printStackTrace();
		}
		 return true;
	}

	public boolean Delete()
	{
		if(CheckReferences("ChiNhanh_FK","NhaCungCap"))
		{
			this.Msg="Chi nh??nh n??y ???? ???????c s??? d???ng,kh??ng th??? x??a ";
			return false;
		}
		if(CheckReferences("ChiNhanh_FK","Erp_NganHang_CongTy"))
		{
			this.Msg="Chi nh??nh n??y ???? ???????c s??? d???ng,kh??ng th??? x??a ";
			return false;
		}
		String query = "Delete Erp_ChiNhanh Where PK_SEQ =" + this.ID + "";
		if (!this.db.update(query))
		{
			this.Msg = "Kh??ng th??? xo?? chi nh??nh n??y ";
			return false;
		}
		return true;
	}
	
	public void setChixem(String chixem) {
		
		this.chixem = chixem;
	}

	public String getChixem() {
		
		return this.chixem;
	}


	
	public String getnppId() {
		
		return this.nppid;
	}


	
	public void setnppId(String nppId) {
		
		this.nppid = nppId;
	}


	
	public String getcongtyId() {
		
		return this.congtyId;
	}


	
	public void setcongtyId(String congtyId) {
		
		this.congtyId = congtyId;
	}
	
}
