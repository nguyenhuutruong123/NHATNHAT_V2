package geso.dms.center.beans.nhapkho.imp;

import java.sql.ResultSet;

import geso.dms.center.db.sql.dbutils;
import geso.dms.center.util.IPhanTrang;
import geso.dms.center.util.PhanTrang;
import geso.dms.center.util.Phan_Trang;
import geso.dms.center.util.Utility;
import geso.dms.center.beans.nhapkho.IErpNhapkhoList;

public class ErpNhapkhoList extends Phan_Trang implements IErpNhapkhoList
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

	
	private int num;
	private int[] listPages;
	private int currentPages;
	
	dbutils db;
	
	public ErpNhapkhoList()
	{
		this.tungay = "";
		this.denngay = "";
		this.trangthai = "";
		this.sophieu="";
		this.lydo = "";
		this.msg = "";
		currentPages = 1;
		num = 1;
		
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
	
	public int getNum()
	{
		return this.num;
	}
	
	public void setNum(int num)
	{
		this.num = num;
		listPages = PhanTrang.getListPages(num);
	}

	public int getCurrentPage()
	{
		return this.currentPages;
	}

	public void setCurrentPage(int current) 
	{
		this.currentPages = current;
	}

	public int[] getListPages() 
	{
		return this.listPages;
	}

	public void setListPages(int[] listPages) 
	{
		this.listPages = listPages;
	}

	public int getLastPage() 
	{
		ResultSet rs = db.get("select count(*) as c from ERP_YEUCAUNGUYENLIEU");
		return PhanTrang.getLastPage(rs);
	}

	public int[] getNewPagesList(String action, int num, int currentPage, int theLastPage, String[] listPage)
	{
		IPhanTrang pt = new PhanTrang();
		return pt.getNewPagesList(action, num, currentPage, theLastPage, listPage);
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
		Utility util =new Utility(); 
		
		String query = "";
        
		if(search.length() > 0)
			query = search;
		else
		{
			query = "\n select a.PK_SEQ, a.trangthai, a.ngaynhap, a.lydo, NV.TEN as nguoitao, b.ten as khonhan, " +
			"\n a.NGAYSUA, a.NGAYTAO, NV2.TEN as nguoisua " +
			"\n from ERP_NHAPKHO a " +
			"\n inner join ERP_KHOTT b on a.khonhap_fk = b.pk_seq " +
			"\n inner join NHANVIEN nv on a.NGUOITAO = nv.PK_SEQ " +
			"\n inner join NHANVIEN nv2 on a.NGUOISUA = nv2.PK_SEQ " +
			"\n where a.pk_seq > 0 AND A.KHONHAP_FK IN " + util.quyen_khoTT(userId,1);
		} 
			
		System.out.println("ErpNhapkhoList: " + query);
		this.nhapkhoRs = createSplittingData(50, 10, "ngaynhap desc, trangthai asc ", query);
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

}
