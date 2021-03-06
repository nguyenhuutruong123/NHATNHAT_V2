package geso.dms.center.beans.thongtinsanpham.imp;
import java.sql.ResultSet;
import java.sql.SQLException;

import geso.dms.center.beans.thongtinsanpham.IThongtinsanphamList;
import geso.dms.center.db.sql.dbutils;
import geso.dms.center.util.Phan_Trang;

public class ThongtinsanphamList extends Phan_Trang implements IThongtinsanphamList
{
	private static final long serialVersionUID = -9217977556733610214L;

	private String userId;
	private String masp;
	private String tensp;
	private String dvkdId;
	private String nhId;		 
	private String clId;
	private String trangthai;
	private String Msg;
	private String query;
	private ResultSet dvkd;
	private ResultSet nh;
	private ResultSet cl;
	private ResultSet splist;
	private dbutils db;
	
	int currentPages;
	int[] listPages;
	
	public ThongtinsanphamList(String[] param)
	{
		this.tensp = param[0];
		this.dvkdId = param[1];
		this.nhId = param[2];		 
		this.clId = param[3];
		this.trangthai = param[4];
		this.masp = param[5];
		this.query = "";
		
		currentPages = 1;
		listPages = new int[]{1, 2 , 3 , 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20};
		
		this.db = new dbutils();
		
	}
	
	public ThongtinsanphamList()
	{
		this.masp = "";
		this.tensp = "";
		this.dvkdId = "";
		this.nhId = "";		 
		this.clId = "";
		this.trangthai = "2";
		this.query = "";
		this.Msg ="";
		
		currentPages = 1;
		listPages = new int[]{1, 2 , 3 , 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20};
		
		this.db = new dbutils();
	}

	public String getUserId(){
		return this.userId;
	}
	
	public void setUserId(String userId){
		this.userId = userId;
	}

	public String getMasp(){
		return this.masp;
	}
	
	public void setMasp(String masp){
		this.masp = masp;
	}

	public String getTensp(){
		return this.tensp;
	}
	
	public void setTensp(String tensp){
		this.tensp = tensp;
	}
	
	public String getQuery(){
		return this.query;
	}
	
	public void setQuery(String query){
		this.query = query;
	}

	public String getDvkdId(){
		return this.dvkdId;
	}
	
	public void setDvkdId(String dvkdId){
		this.dvkdId = dvkdId;
	}

	public String getNhId(){
		return this.nhId;
	}
	
	public void setNhId(String nhId){
		this.nhId = nhId;
	}

	public String getClId(){
		return this.clId;
	}
	
	public void setClId(String clId){
		this.clId = clId;
	}
			
	public String getTrangthai(){
		return this.trangthai;
	}
	
	public void setTrangthai(String trangthai){
		this.trangthai = trangthai;
	}				

	public ResultSet getDvkd(){
		return this.dvkd;
	}
	
	public void setDvkd(ResultSet dvkd){
		this.dvkd = dvkd;
	}
	
	public ResultSet getNh(){
		return this.nh;
	}
	
	public void setNh(ResultSet nh){
		this.nh = nh;
	}

	public ResultSet getCl(){
		return this.cl;
	}
	
	public void setCl(ResultSet cl){
		this.cl = cl;
	}
	
	public ResultSet getThongtinsanphamList(){
		return this.splist;
	}
	
	public void setThongtinsanphamList(ResultSet splist){
		this.splist = splist;
	}

	private ResultSet createDvkdRS(){
		
		ResultSet dvkdRS =  this.db.get("select pk_seq, donvikinhdoanh as dvkd, pk_seq as dvkdId from donvikinhdoanh where trangthai='1' ");
		//ResultSet dvkdRS =  this.db.get("select a.pk_seq, a.donvikinhdoanh as dvkd, a.pk_seq as dvkdId  from donvikinhdoanh a,nhacungcap_dvkd b where a.pk_seq = b.DVKD_fk and b.checked ='1' and a.trangthai = '1' order by a.donvikinhdoanh");
		
		return dvkdRS;
	}
	
	private ResultSet createNhRS(){
		
		ResultSet nhRS;
		if (dvkdId.length()>0){
			nhRS =  this.db.get("select pk_seq, ten from nhanhang where trangthai='1' and dvkd_fk='" + this.dvkdId + "'");
		}else{
			nhRS =  this.db.get("select pk_seq, ten from nhanhang where trangthai='1'");
		}
		return nhRS;
		
	}

	private ResultSet createClRS() {  	
		
		String query = "select distinct a.pk_seq, a.ten from chungloai a, nhanhang_chungloai b where a.trangthai='1' and a.pk_seq = b.cl_fk ";
		
		if (this.nhId.length()>0){
			query = query + "  and b.nh_fk = '" + this.nhId + "'";
		}
		 
		return this.db.get(query);
			
	}
			
	
	public void CreateRS(){
		
		this.dvkd = createDvkdRS();
		this.nh = createNhRS();
		this.cl= createClRS();

	}

	public void init()
	{	
		if (this.query.length() == 0)
		{
			//query="select a.pk_seq, a.ma, a.ten, b.donvi, g.donvikinhdoanh as dvkd, g.pk_seq as dvkdId, c.pk_seq as nhId, c.ten as nhanhang, d.pk_seq as clId, d.ten as chungloai, a.trangthai, i.giabanlechuan as giablc from sanpham a, donvidoluong b, nhanhang c, chungloai d,  donvikinhdoanh g, banggiabanlechuan h, banggiablc_sanpham i  where a.dvdl_fk=b.pk_seq and a.nhanhang_fk = c.pk_seq and a.chungloai_fk = d.pk_seq and c.dvkd_fk = g.pk_seq and g.pk_seq = h.dvkd_fk and h.pk_seq= i.bgblc_fk and a.pk_seq = i.sanpham_fk ";
		//query = "select a.pk_seq, a.ma, a.ten, b.donvi, g.donvikinhdoanh as dvkd, g.pk_seq as dvkdId, c.pk_seq as nhId, c.ten as nhanhang, d.pk_seq as clId, d.ten as chungloai, a.trangthai, i.giabanlechuan as giablc from sanpham a, donvidoluong b, nhanhang c, chungloai d,  donvikinhdoanh g, banggiabanlechuan h, banggiablc_sanpham i  where a.dvdl_fk=b.pk_seq and a.nhanhang_fk = c.pk_seq and a.chungloai_fk = d.pk_seq and c.dvkd_fk = g.pk_seq and g.pk_seq = h.dvkd_fk and h.pk_seq= i.bgblc_fk and a.pk_seq = i.sanpham_fk union select a.pk_seq, a.ma, a.ten, '' as donvi, '' as dvkd, null as dvkdId, null as nhId, null as nhanhang, null as clId, null as chungloai, a.trangthai, '0' as giablc from sanpham a  where a.pk_seq not in (select a.pk_seq from sanpham a, donvidoluong b, nhanhang c, chungloai d,  donvikinhdoanh g, banggiabanlechuan h, banggiablc_sanpham i  where a.dvdl_fk=b.pk_seq and a.nhanhang_fk = c.pk_seq and a.chungloai_fk = d.pk_seq and c.dvkd_fk = g.pk_seq and g.pk_seq = h.dvkd_fk and h.pk_seq= i.bgblc_fk and a.pk_seq = i.sanpham_fk) order by trangthai, ma";
		//	query ="select a.pk_seq, a.ma, a.ten, b.donvi, g.donvikinhdoanh as dvkd, g.pk_seq as dvkdId, c.pk_seq as nhId, c.ten as nhanhang, d.pk_seq as clId, d.ten as chungloai, a.trangthai, i.giabanlechuan as giablc from sanpham a, donvidoluong b, nhanhang c, chungloai d,  donvikinhdoanh g, banggiabanlechuan h, banggiablc_sanpham i  where a.dvdl_fk=b.pk_seq and a.nhanhang_fk = c.pk_seq and a.chungloai_fk = d.pk_seq and c.dvkd_fk = g.pk_seq and g.pk_seq = h.dvkd_fk and h.pk_seq= i.bgblc_fk and a.pk_seq = i.sanpham_fk ";
       
        query ="select ROW_NUMBER() OVER(ORDER BY a.ma DESC) AS 'stt', a.pk_seq, a.ma, a.ten, a.trongluong, a.thetich, b.donvikinhdoanh as dvkd,b.pk_seq as dvkdId, c.ten as chungloai, e.pk_seq as nhId, d.donvi, e.ten as nhanhang, d.pk_seq as clId, a.trangthai ";
		query = query + " from sanpham a left join donvikinhdoanh b on a.dvkd_fk = b.pk_seq left join chungloai c on a.chungloai_fk = c.pk_seq left join donvidoluong d on a.dvdl_fk = d.pk_seq left join nhanhang e on a.nhanhang_fk = e.pk_seq ";
		//query = query  + " left join banggiablc_sanpham f on a.pk_seq = f.sanpham_fk left join banggiabanlechuan g on f.bgblc_fk = g.pk_seq where 1=1  ";
		   query=query+ "where 1=1 "  ;
		}
		System.out.println("khoi tao :"+query);
		//query = "select top(20) * from (" + query + ") as spList";
	    
		this.splist = super.createSplittingData(super.getItems(), super.getSplittings(), "pk_seq desc", query);
	    
	    CreateRS();
	}
	
	
	public void DBClose()
	{
		try
		{				
			if(this.cl != null) this.cl.close();
			if(this.nh != null) this.nh.close();
			if(this.splist != null) this.splist.close();
			if(this.dvkd != null) this.dvkd.close();
			this.db.shutDown();
		}
		catch(java.sql.SQLException e){}
	}
	
	public void setMsg(String Msg) 
	{
		this.Msg = Msg;	
	}

	public String getMsg() 
	{
		return this.Msg;
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
		ResultSet rs = db.get("select count(*) as skh from khachhang");
		try 
		{
			rs.next();
			int count = Integer.parseInt(rs.getString("skh"));
			rs.close();
			return count;
		}
		catch (SQLException e) {}
		
		return 0;
	}

}
