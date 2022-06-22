package geso.dms.center.beans.kehoachpg.imp;

import java.util.ArrayList;
import java.util.List;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.http.HttpServletRequest;

import com.oreilly.servlet.MultipartRequest;

import geso.dms.center.beans.kehoachpg.*;
import geso.dms.center.db.sql.dbutils;
import geso.dms.center.util.IPhanTrang;
import geso.dms.center.util.PhanTrang;
import geso.dms.center.util.Phan_Trang;
import geso.dms.center.util.Utility;
import geso.dms.center.*;


public class KehoachPGList extends Phan_Trang implements IKehoachPGList
{
	private static final long serialVersionUID = -9217977546733610214L;

	// Tieu chi tim kiem
	String userId;
	String ten  = "";
	String trangthai  = "";
	String tuan = "";
	String nam = "";
	String tungay = "";
	String denngay  = "";
	
	
	ResultSet nhaphanphoi;
	String nppId  = "";
    String Msg =  "";
	String view = "";
	dbutils db;
	
	private int num;
	private int[] listPages;
	private int currentPages;
	private ResultSet ddkdlist;
	private HttpServletRequest request;

List<Object> dataSearch = new ArrayList<Object>(); 
	
	public List<Object> getDataSearch() {
		return dataSearch;
	}
	public void setDataSearch(List<Object> dataSearch) {
		this.dataSearch = dataSearch;
	}
	
	

	
	
	public KehoachPGList()
	{
		this.db = new dbutils();
		
	}
/*	public void getHienTai()
	{
		
		DECLARE @TaskWeek INT = 26
		DECLARE @TaskYear INT = 2019
		SELECT convert(varchar(10), DATEADD(WEEK, @TaskWeek - 1,DATEADD(dd, 1 - DATEPART(dw, '1/1/' + CONVERT(VARCHAR(4),@TaskYear))
				, '1/1/' + CONVERT(VARCHAR(4),@TaskYear))),120)
		
		String query =  "\n declare @d  varchar(10) = '"+Utility.getNgayHienTai()+"' " +
						"\n select datepart(week, @d) tuan , year ( @d ) nam " +
						"\n 	, convert(varchar(10),DATEADD(dd, -(DATEPART(dw, @d)-1), @d),120) _start " +
						"\n 	,convert(varchar(10),DATEADD(dd, 7-(DATEPART(dw, @d)), @d),120) _end  " ;
		ResultSet rs = db.get(query);
		
		try 
		{
			rs.next();
			this.tuan = rs.getString("tuan");	
			this.nam  = rs.getString("nam");
			this.tungay = rs.getString("_start");
			this.denngay = rs.getString("_end");
			
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}*/
	
	public String getView() {
		return view;
	}
	public void setView(String view) {
		this.view = view;
	}
	public HttpServletRequest getRequestObj() 
	{
		return this.request;
	}

	public void setRequestObj(HttpServletRequest request) 
	{
		this.request = request;
	}
	
	public String getUserId() 
	{
		return this.userId;
	}

	public void setUserId(String userId) 
	{
		this.userId = userId;
	}
	
	public String getTen() 
	{
		return this.ten;
	}

	public void setTen(String ten) 
	{
		this.ten = ten;
	}

	
	
	public String getTrangthai()
	{
		return this.trangthai;
	}
	
	public void setTrangthai(String trangthai)
	{
		this.trangthai = trangthai;
	}

	public ResultSet getDdkdList()
	{
		return this.ddkdlist;
	}
	
	public void setDdkdList(ResultSet ddkdlist)
	{
		this.ddkdlist = ddkdlist;
	}
	
	

	
	

	
	public ResultSet getNhaphanphoi() 
	{
		return this.nhaphanphoi;
	}

	public void setNhaphanphoi(ResultSet nhaphanphoi)
	{
		this.nhaphanphoi = nhaphanphoi;
	}

	public String getNppId() 
	{
		return this.nppId;
	}

	public void setNppId(String nppId) 
	{
		this.nppId = nppId;
	}

	

	private void createNppRS()
	{  	
		Utility util = new Utility();
		String query =  " select a.pk_seq as nppId, a.ten as nppTen, 'Khu vuc ' + b.ten as kvTen " +
		" from nhaphanphoi a inner join khuvuc b on a.khuvuc_fk = b.pk_seq " +
		" where a.trangthai = '1' and a.sitecode = a.convsitecode " +
		"  		and a.pk_seq in "+util.quyen_npp(this.userId)+" " +
		" order by b.pk_seq asc ";
		
		System.out.println("Query NPP: " + query);
		this.nhaphanphoi = this.db.get(query);
	}

	public void createDdkdBeanList(String query)
	{  	  
		this.ddkdlist =  createSplittingData(super.getItems(), super.getSplittings(), "id desc", query);
		//this.ddkdlist = super.createSplittingData_v2(db,super.getItems(), super.getSplittings(), "id desc", query, this.dataSearch, "");
	}
	
	public void init(String contentType,HttpServletRequest request,MultipartRequest multi) 
	{
		geso.dms.center.util.Utility util = new geso.dms.center.util.Utility();
		String query;	
		
		
		String nppId = Utility.getValueFromClient("nppId",contentType,multi,request);   
		if (nppId == null)
			nppId = "";
		this.nppId=nppId;
		
		String userId = Utility.getValueFromClient("userId",contentType,multi,request);   
		this.userId=userId;
		
			
		
		
		
		String ddkdTen = Utility.getValueFromClient("ddkdTen",contentType,multi,request);   
		if (ddkdTen == null)
			ddkdTen = "";
		this.ten=ddkdTen;
		
		query = "\n select  a.pk_seq id,d.ten npp,a.diengiai, a.trangthai , a.tuan, a.nam, a.tungay, a.denngay, a.ngaytao, a.ngaysua, b.ten nguoitao, c.ten nguoisua " +
		"\n from kehoachPG a inner join nhanvien b on a.nguoitao = b.pk_seq "+ 
		"\n inner join nhanvien c on a.nguoisua = c.pk_seq "+
		"\n left join nhaphanphoi d on a.npp_fk = d.pk_seq "+  
		"\n where 1=1 ";
		
		String _nppid = "";
		if (view != null && !view.equals("TT")) {
			
			_nppid = util.getIdNhapp(userId);
			if (_nppid != null && _nppid.length() > 0) {
				query += "\n and a.npp_fk = "+_nppid;
			}
		}
		else
		{
			query +=" and a.npp_fk in "+util.quyen_npp(userId)+"  ";
		}
		
		if(nppId.trim().length()>0)
		{
			query+=" and  a.npp_fk ="+nppId;
		}
		
	
		
		if(ddkdTen.trim().length()>0)
		{
			query+=" and  a.diengiai like N'%"+ddkdTen+"%'";
		}
		System.out.println("Init List: "+query);
		createDdkdBeanList(query);

		createNppRS();
	}

		public void setMsg(String Msg) {
		this.Msg = Msg;
		
	}

	
	public String getMsg() {
		return this.Msg;
	}
	public int getNum(){
		return this.num;
	}
	public void setNum(int num){
		this.num = num;
		listPages = PhanTrang.getListPages(num);

	}

	
	public int getCurrentPage() {
		return this.currentPages;
	}

	
	public void setCurrentPage(int current) {
		this.currentPages = current;
	}

	
	public int[] getListPages() {
		return this.listPages;
	}

	
	public void setListPages(int[] listPages) {
		this.listPages = listPages;
	}

	
	public int getLastPage() {
		ResultSet rs = db.get("select count(*) as c from ERP_CHUYENKHO ");
		return PhanTrang.getLastPage(rs);
	}

	
	public int[] getNewPagesList(String action, int num, int currentPage, int theLastPage, String[] listPage) {
		IPhanTrang pt = new PhanTrang();
		return pt.getNewPagesList(action, num, currentPage, theLastPage, listPage);
	}
	@Override
	public void DbClose() {
		// TODO Auto-generated method stub
	
		try{
			
			if(nhaphanphoi!=null){
				nhaphanphoi.close();
			}
			if(db!=null){
				db.shutDown();
			}
			
		}catch(Exception er){
			
		}
		
	}


	



}
