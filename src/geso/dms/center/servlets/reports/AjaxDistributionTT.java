package geso.dms.center.servlets.reports;

import geso.dms.center.db.sql.dbutils;
import geso.dms.center.util.Utility;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.text.NumberFormat;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
/*import com.google.gson.JSONArray;
import com.google.gson.JSONObject;*/

import org.json.*;

public class AjaxDistributionTT extends HttpServlet
{
	NumberFormat formatter = new DecimalFormat("#,###,###.###");
	private static final long serialVersionUID = 1L;

	public AjaxDistributionTT()
	{
		super();
	}
	
	
	 private int INITIAL;
	 private int RECORD_SIZE;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		//System.out.println("vo AjaxDistributionTT !!!!!!");
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");
		JSONObject jsonResult = new JSONObject();
		int listDisplayAmount = 10;
		int start = 0;
		int column = 0;
		String dir = "asc";
		String pageNo = request.getParameter("iDisplayStart");
		String pageSize = request.getParameter("iDisplayLength");
		Utility util = new Utility();
		
		if (pageNo != null) {
		   start = Integer.parseInt(pageNo);
		   if (start < 0) { start = 0; }
		}
		
		if (pageSize != null) 
		{
		   listDisplayAmount = Integer.parseInt(pageSize);
		   if (listDisplayAmount < 10 || listDisplayAmount > 50) 
		   { listDisplayAmount = 10; }
		 }
		int totalRecords= -1;
		try { totalRecords = getTotalRecordCount(request, util); } catch (SQLException e1) { e1.printStackTrace(); }
		RECORD_SIZE = listDisplayAmount;
		
		INITIAL = start;
		try 
		{
		   jsonResult = getDataDetails(totalRecords, request, util);
		} 
		catch (ClassNotFoundException e) { e.printStackTrace(); } 
		catch (SQLException e) { e.printStackTrace(); }
		response.setContentType("application/json");
		response.setHeader("Cache-Control", "no-store");
		PrintWriter out = response.getWriter();
		out.print(jsonResult);
	}
	
	public static final int QUERY_EXCEL_ACTION = 0;
	public static final int QUERY_DETAIL_ACTION = 1;
	public static final int QUERY_COUNT_ACTION = 2;
	public static final int QUERY_SUM_DATA_ACTION = 3;
	
	public int getTotalRecordCount(HttpServletRequest request, Utility util) throws SQLException 
	{
	  int totalRecords = -1;
	  dbutils db = new dbutils();
	  String sql = "";
	  
	  if(request.getParameter("action").equals("ChiSoDoanhThuNV"))
	  {
		  
	  }
	  else
	  if(request.getParameter("action").equals("DoPhuSP"))
	  {
		  //sql = DoPhuSP_Num(request, util);
		  sql = queryDoPhuSP(request, util, QUERY_COUNT_ACTION, 0, 0);
	  }
	  else  if(request.getParameter("action").equals("DoanhThuTheoSP"))
	  {
		  //sql = DoanhThuTheoSP_Num(request, util);
		  sql = queryDoanhThuTheoSP(request, util, QUERY_COUNT_ACTION, 0, 0);
	  }
	  else  if(request.getParameter("action").equals("DoanhSoTheoSP"))
	  {
		  //sql = DoanhThuTheoSP_Num(request, util);
		  sql = queryDoanhSoTheoSP(request, util, QUERY_COUNT_ACTION, 0, 0);
	  }
	  else  if(request.getParameter("action").equals("DoanhThuBanRa"))
	  {
		  sql = queryDoanhThuBanRa(request, util, QUERY_COUNT_ACTION, 0, 0);
	  }
	  else  if(request.getParameter("action").equals("DoanhThuKhachHang"))
	  {
		  sql = queryDoanhThuKhachHang(request, util, QUERY_COUNT_ACTION, 0, 0);
	  }
	  else 
	  if(request.getParameter("action").equals("Quanhuyen"))
	  {
		  sql = queryQuanhuyen(request, util, QUERY_COUNT_ACTION, 0, 0);
	  }
	 
	  ResultSet rs = db.get(sql);
	  rs.next();
	  totalRecords = rs.getInt("num");
	  rs.close();
	  db.shutDown();
	  return totalRecords;
	}
	
	public JSONObject getDataDetails(int totalRecords, HttpServletRequest request, Utility util) throws SQLException, ClassNotFoundException 
	{
		  int totalAfterSearch = totalRecords;
		  JSONObject result = new JSONObject();
		  JSONArray array = new JSONArray();
		  
		  if(request.getParameter("action").equals("DoPhuSP"))
		  {
			  array = DoPhuSP(request, util, false);
		  }
		  else if(request.getParameter("action").equals("DoanhThuTheoSP"))
		  {
			  array = DoanhThuTheoSP(request, util, false);
		  }
		  else if(request.getParameter("action").equals("DoanhSoTheoSP"))
		  {
			  array = DoanhSoTheoSP(request, util, false);
		  }
		  else
		  if(request.getParameter("action").equals("Quanhuyen"))
		  {
			  array = Quanhuyen(request, util);
		  }else if(request.getParameter("action").equals("DoanhThuBanRa"))
		  {
			  array = DoanhThuBanRa(request, util,false);
		  }
		  else if(request.getParameter("action").equals("DoanhThuKhachHang"))
		  {
			  array = DoanhThuKhachHang(request, util,false);
		  } 
		  
		  try {
			   result.put("iTotalRecords", totalRecords);
			   result.put("iTotalDisplayRecords", totalAfterSearch);
			   result.put("aaData", array);
			  } catch (Exception e) { }
		  return result;
	}
	
	public static String queryQuanhuyen(HttpServletRequest request, Utility util, int action,int RECORD_SIZE,int INITIAL )
	{
		String sql = "";
		try {
			request.setCharacterEncoding("UTF-8");
			String ten = util.antiSQLInspection(request.getParameter("ten"));
			String tinhId = util.antiSQLInspection(request.getParameter("tinhId"));
			String subquery_Top = " ";
			String subquery_Bot = " ";
			
			if(action == QUERY_DETAIL_ACTION)
			{
				subquery_Top = "\n select top("+ RECORD_SIZE +") * from( select row_number() over(order by addNo.[T??n qu???n huy???n]) as _no, addNo.* from  \n (  ";
				subquery_Bot = "\n ) addNo) list where _no > '"+ INITIAL +"' ";
			}else
				if( action == QUERY_COUNT_ACTION)
				{
					subquery_Top = "\n select count(*) num from ( ";
					subquery_Bot  = "\n ) as t ";
				}
			
			
			
			String searchstr = "";
			if (!ten.equals("")) { searchstr += "\n	AND quan.TIMKIEM LIKE UPPER( '%"+ util.replaceAEIOU(ten) +"%') "; }
			if (!tinhId.equals("")) { searchstr += "\n	AND tinh.PK_SEQ = " + tinhId + " "; }
			sql = 
				subquery_Top +
				"\n select PK_SEQ [M?? h??? th???ng] , QUAN [T??n qu???n huy???n], TINH [T???nh], NGAYTAO [Ng??y t???o], NGUOITAO [Ng?????i t???o], NGAYSUA [Ng??y s???a], NGUOISUA [Ng?????i s???a] " +
				"\n from " +
				"\n ( "+
				"\n 	SELECT quan.PK_SEQ, isnull(QUAN.MA,'') MA, quan.TEN AS QUAN, TINH.PK_SEQ MAHTTINH, TINH.MA MATINH, tinh.TEN AS TINH, tao.TEN AS NGUOITAO, quan.NGAYTAO, "+
				"\n		sua.TEN AS NGUOISUA, quan.NGAYSUA "+
				"\n		FROM dbo.QUANHUYEN quan "+
				"\n		INNER JOIN dbo.TINHTHANH tinh ON tinh.PK_SEQ = quan.TINHTHANH_FK "+
				"\n		INNER JOIN dbo.NHANVIEN tao ON tao.PK_SEQ = quan.NGUOITAO "+
				"\n		INNER JOIN dbo.NHANVIEN sua ON sua.PK_SEQ = quan.NGUOISUA "+
				"\n		WHERE 1 = 1 "+ searchstr +
				"\n	) as t where 1=1 "+  subquery_Bot;
			System.out.println("sql AJAX : "+ sql);
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return sql;
	}
	
	
	public JSONArray Quanhuyen(HttpServletRequest request, Utility util) throws SQLException, ClassNotFoundException 
	{
		dbutils db = new dbutils();
		JSONArray array = new JSONArray();
		String sql = queryQuanhuyen(request, util, QUERY_DETAIL_ACTION, RECORD_SIZE, INITIAL);
		  ResultSet rs = db.get(sql);
		  while (rs.next()) 
		  {
			   JSONArray ja = new JSONArray();
			   ja.put(rs.getString("M?? h??? th???ng"));
			   ja.put(rs.getString("T??n qu???n huy???n"));
			   ja.put(rs.getString("T???nh"));
			   ja.put(rs.getString("Ng??y t???o"));
			   ja.put(rs.getString("Ng?????i t???o"));
			   ja.put(rs.getString("Ng??y s???a"));
			   ja.put(rs.getString("Ng?????i s???a"));
			   ja.put(rs.getString("M?? h??? th???ng"));
			   array.put(ja); 
		  }
		  rs.close();
		  db.shutDown();
		return array;
	}
	

	//D??NG C??C MENU C?? L???Y SUM PH??A TR??N
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{ 
		/// DO POST d??ng ri??ng t??nh total
		//System.out.println("vo AjaxDistributionTT !!!!!!");
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");
		Utility util = new Utility();
		String trave = "";
		try 
		{
			trave = getSumdataDetails( request, util);
		} 
		catch (Exception e) { e.printStackTrace(); } 

		response.setContentType("application/json");
		response.setHeader("Cache-Control", "no-store");
		PrintWriter out = response.getWriter();
		out.print(trave);
		return;
		
	}
	
	public String getSumdataDetails( HttpServletRequest request, Utility util) throws SQLException, ClassNotFoundException 
	{
		dbutils db = new dbutils();
		String kq= "";
		String sql= "";
		if(request.getParameter("action").equals("DoanhThuBanRa"))
		{
			sql = queryDoanhThuBanRa(request, util, QUERY_SUM_DATA_ACTION, 0, 0);
			ResultSet rs= db.get(sql);
			rs.next();

			kq=" <div class=\"col-md-6\"> " +
			"<div class=\"form-group\">  " +
			"   <div class=\"col-md-4\"><label>DOANH S???</label></div>   " +
			"   <input readonly type=\"text\" autocomplete=\"off\"   size=\"20\" value = \""+formatter.format(rs.getDouble("DOANH S???"))+"\" >  " +
			"</div> " +
			"</div> "  +

			" <div class=\"col-md-6\"> " +
			"<div class=\"form-group\">  " +
			"   <div class=\"col-md-4\"><label>DS TR??? TR??? L???I</label></div>   " +
			"   <input readonly type=\"text\" autocomplete=\"off\"   size=\"20\" value = \""+formatter.format(rs.getDouble("DS TR??? TR??? L???I"))+"\" >  " +
			"</div> " +
			"</div> " +

			" <div class=\"col-md-6\"> " +
			"<div class=\"form-group\">  " +
			"   <div class=\"col-md-4\"></div>   " +

			"</div> " +
			"</div> " ;
		}
		else
		if(request.getParameter("action").equals("DoanhThuSanPhamTheoNPP"))
		{
			sql = queryDoanhThuSanPhamTheoNPP(request, util, QUERY_SUM_DATA_ACTION, 0, 0);
			ResultSet rs= db.get(sql);
			rs.next();

			kq=" <div class=\"col-md-6\"> " +
			"<div class=\"form-group\">  " +
			"   <div class=\"col-md-4\"><label>DOANH THU</label></div>   " +
			"   <input readonly type=\"text\" autocomplete=\"off\"   size=\"20\" value = \""+formatter.format(rs.getDouble("DOANH THU"))+"\" >  " +
			"</div> " +
			"</div> "  +

			" <div class=\"col-md-6\"> " +
			"<div class=\"form-group\">  " +
			"   <div class=\"col-md-4\"><label>THU??? GTGT</label></div>   " +
			"   <input readonly type=\"text\" autocomplete=\"off\"   size=\"20\" value = \""+formatter.format(rs.getDouble("THU??? GTGT"))+"\" >  " +
			"</div> " +
			"</div> " +

			" <div class=\"col-md-6\"> " +
			"<div class=\"form-group\">  " +
			"   <div class=\"col-md-4\"></div>   " +

			"</div> " +
			"</div> " +

			" <div class=\"col-md-6\"> " +
			"<div class=\"form-group\">  " +
			"   <div class=\"col-md-4\"><label>T???NG TI???N</label></div>   " +
			"   <input readonly type=\"text\" autocomplete=\"off\"   size=\"20\" value = \""+formatter.format(rs.getDouble("T???NG TI???N"))+"\" >  " +
			"</div> " +
			"</div> " ;

		}
		else if(request.getParameter("action").equals("DoanhThuTheoSP"))
		{
			sql = queryDoanhThuTheoSP(request, util, QUERY_SUM_DATA_ACTION, 0, 0);
			ResultSet rs= db.get(sql);
			rs.next();

			kq=" <div class=\"col-md-6\"> " +
			"<div class=\"form-group\">  " +
			"   <div class=\"col-md-4\"><label>DOANH S???</label></div>   " +
			"   <input readonly type=\"text\" autocomplete=\"off\"   size=\"20\" value = \""+formatter.format(rs.getDouble("DOANH S???"))+"\" >  " +
			"</div> " +
			"</div> "  +

			" <div class=\"col-md-6\"> " +
			"<div class=\"form-group\">  " +
			"   <div class=\"col-md-4\"><label>DS TR??? TR??? L???I</label></div>   " +
			"   <input readonly type=\"text\" autocomplete=\"off\"   size=\"20\" value = \""+formatter.format(rs.getDouble("DS TR??? TR??? L???I"))+"\" >  " +
			"</div> " +
			"</div> " ;

		}
			

		db.shutDown();
		return kq;
	}

	
	public static String queryDoPhuSP(HttpServletRequest request, Utility util, int action,int RECORD_SIZE,int INITIAL )
	{
		//  ACTION :
		//	0 xuat excel 
		//  1 query detal
		//	2 query count	
		String userId = util.antiSQLInspection(request.getParameter("userId"));
		String tungay = util.antiSQLInspection(request.getParameter("tungay"));
		String denngay = util.antiSQLInspection(request.getParameter("denngay"));
		String vungId = util.antiSQLInspection(request.getParameter("vungId"));
		String kenhId = util.antiSQLInspection(request.getParameter("kenhId"));
		String ds_toi_thieu_kh  = util.antiSQLInspection(request.getParameter("ds_toi_thieu_kh")) != null ? util.antiSQLInspection(request.getParameter("ds_toi_thieu_kh")) : "0";
		double _ds_toi_thieu_kh = Utility.parseDouble(ds_toi_thieu_kh.replace(",",""));
		
		//String spId = util.antiSQLInspection(request.getParameter("spId"));
		String[] spId = request.getParameterValues("spId");
		String SKU = "";
		if (spId != null)
		{
			for (int i = 0; i < spId.length; i++)
			{
				SKU += spId[i] + ",";
			}
		}
		
		if(SKU.length() > 0)
		{
			SKU = SKU.substring(0, SKU.lastIndexOf(","));
		}
		System.out.println("sku : "+ SKU);
		
		String nppId = util.antiSQLInspection(request.getParameter("nppId"));
		String ddkdId = util.antiSQLInspection(request.getParameter("ddkdId"));
		String laytheo = util.antiSQLInspection(request.getParameter("laytheo"));
		String searchstr = "";
		String searchstr2 = "";
		
		if(laytheo == null ) laytheo = "0";
		if(nppId.length() > 0) { searchstr += " and dh.npp_fk = '"+ nppId +"' "; }
		if(ddkdId.length() > 0) { searchstr += " and dh.ddkd_fk = '"+ ddkdId +"' "; }
		if(SKU.length() > 0) { searchstr += " and dhsp.sanpham_fk in ( "+ SKU +" ) "; }
		if(vungId.length() > 0) { searchstr2 += " and v.pk_seq = '"+ vungId +"' "; }
		if(kenhId.length() > 0) { searchstr += " and dh.kbh_fk = '"+ kenhId +"' "; }
		

		
		searchstr += " and dh.DDKD_FK in ("+Utility.PhanQuyenDDKD(userId)+") ";
		searchstr += " and dh.NPP_FK in ("+Utility.Quyen_npp(userId)+") ";
		
		String subquery_Top = " ";
		String subquery_Bot = " ";
		
		if(action == QUERY_DETAIL_ACTION)
		{
			if(laytheo.equals("0"))
			{
				subquery_Top = "\n select top("+ RECORD_SIZE +") * from( select row_number() over(order by [S???n ph???m], [S??? TT] ) as _no, addNo.* from (  ";
			}
			else
			{
				subquery_Top = "\n select top("+ RECORD_SIZE +") * from( select row_number() over(order by addNo.[Mi???n], [NPP], [S???n ph???m], [S??? TT] ) as _no, addNo.* from (  ";
			}
			
			subquery_Bot = "\n ) addNo) list where _no > '"+ INITIAL +"' ";
		}
		else if( action == QUERY_COUNT_ACTION)
		{
			subquery_Top = "\n select count(*) num from ( ";
			subquery_Bot  = "\n ) as t ";
		}
		else if(action == QUERY_EXCEL_ACTION)
		{
			if(laytheo.equals("0"))
			{
				subquery_Top = "\n select * from( select row_number() over(order by [S???n ph???m], [S??? TT] ) as _no, addNo.* from (  ";
			}
			else
			{
				subquery_Top = "\n select * from( select row_number() over(order by addNo.[Mi???n], [NPP], [S???n ph???m], [S??? TT] ) as _no, addNo.* from (  ";
			}
			
			subquery_Bot = "\n ) addNo) list where _no > '"+ INITIAL +"' ";
		}
		
		String sql = "";
		if(laytheo.equals("0"))
		{	sql = "\n with ds  as " +
				"\n ( " +
				"\n 	select  ds.NPP_FK, cast (count(distinct ds.KHACHHANG_FK) as float) sokh,ds.SANPHAM_FK, sum(soluong) soluong, sum(tongtien)tongtien, sum(DS_TRU_TRA_LAI)DS_TRU_TRA_LAI " +
				"\n 	from " +
				"\n 	( " +
				"\n 		select dh.NPP_FK,dh.KHACHHANG_FK,dhsp.SANPHAM_FK ,sum(dhsp.soluong) soluong , sum( round( dhsp.soluong * dhsp.giamua * ( 1 + dhsp.thuevat/100.0) ,0)) tongtien, sum( round( dhsp.soluong * dhsp.giamua * ( 1 + dhsp.thuevat/100.0) ,0)) DS_TRU_TRA_LAI  "+ 
				"\n 		from donhang dh  "+ 
				"\n 		inner join donhang_sanpham dhsp on dh.PK_SEQ = dhsp.donhang_fk  "+ 
				"\n 		where dh.TRANGTHAI in (1, 5) and dh.ngaynhap between '"+ tungay +"' and '"+ denngay +"' " + searchstr + 
				"\n			 and not exists ( select 1 from Erp_HangTraLaiNpp x where x.DONHANG_FK = dh.pk_seq and x.trangthai  in (1, 5) )	" +
				"\n 		group by dh.NPP_FK,dh.KHACHHANG_FK,dhsp.SANPHAM_FK "+ 
				"\n 		union all "+ 
				"\n 		select dh.NPP_FK,dh.KHACHHANG_FK,dhsp.SANPHAM_FK,sum( (-1)*soluong),0 , sum( round( (-1)*soluong * DonGia,0)) DS_TRU_TRA_LAI "+ 
				"\n 		from Erp_HangTraLaiNpp dh  "+ 
				"\n 		inner join Erp_HangTraLaiNpp_SanPham dhsp on dh.PK_SEQ = dhsp.HangTraLai_fk  "+ 
				"\n 		where dh.TRANGTHAI in (1, 5) and dh.DONHANG_FK is null and dh.ngaytra between '"+ tungay +"' and '"+ denngay +"' " + searchstr + 
				"\n 		group by dh.NPP_FK,dh.KHACHHANG_FK,dhsp.SANPHAM_FK "+ 
				"\n 	) ds " +
				"\n 	group by ds.NPP_FK,ds.SANPHAM_FK " +
				"\n ) " +
				"\n , tongkh as " +
				"\n ( " +
				"\n 	select count(pk_seq) total from khachhang where trangthai in (1, 5) " +
				"\n ) " +
				subquery_Top +
				
				/*"\n select top("+ RECORD_SIZE +") * from( select row_number() over(order by [S???n ph???m],stt ) as [S??? TT], addNo.* from "+
				"\n (  "+*/
				
				"\n select 0 [S??? TT], '' [Mi???n], '' [NPP], sp.TEN [S???n ph???m], ds.sokh [S??? kh??ch h??ng], round(  ds.sokh * 100.0/ tongkh.total,2) [T??? l???], ds.soluong [S??? l?????ng], ds.tongtien [T???ng ti???n] , ds.DS_TRU_TRA_LAI [DS tr??? tr??? l???i] " +
				"\n from  " +
				"\n ( " +
				"\n 	select SANPHAM_FK,sum( sokh) sokh " +
				"\n 		, sum(soluong) soluong, sum(tongtien)tongtien, sum(DS_TRU_TRA_LAI)DS_TRU_TRA_LAI " +
				"\n 	from ds " +
				"\n 	group by  SANPHAM_FK " +
				"\n )ds  " +
				"\n inner join tongkh on 1=1 " +
				"\n inner join SANPHAM sp on sp.PK_SEQ =ds.SANPHAM_FK where 1=1 "+ searchstr2 + 
				"\n union all " +
				"\n select ROW_NUMBER() OVER(PARTITION BY  sp.TEN   ORDER BY  npp.TEN ASC) as  [S??? TT], " +
				"\n v.TEN [Mi???n], npp.TEN[NPP],sp.TEN [S???n ph???m], ds.sokh [SL Kh??ch h??ng],  round( ds.sokh * 100.0/ tongkh.total,2)  ,ds.soluong,ds.tongtien , ds.DS_TRU_TRA_LAI  " +
				"\n from ds  " +
				"\n inner join tongkh on 1=1 " +
				"\n inner join NHAPHANPHOI npp on npp.PK_SEQ = ds.NPP_FK " +
				"\n inner join KHUVUC kv on kv.PK_SEQ = npp.KHUVUC_FK " +
				"\n inner join vung v on v.PK_SEQ = kv.VUNG_FK " +
				"\n inner join SANPHAM sp on sp.PK_SEQ =ds.SANPHAM_FK where 1=1 "+ searchstr2 + 
				subquery_Bot;
		} else {
			sql ="\n with ds as "+ 
				"\n ( "+ 
				"\n 	select ds.NPP_FK,ds.KHACHHANG_FK,ds.SANPHAM_FK, sum(soluong) soluong, sum(tongtien)tongtien, sum(DS_TRU_TRA_LAI)DS_TRU_TRA_LAI "+ 
				"\n 	from "+ 
				"\n 	( "+ 
				"\n 		select dh.NPP_FK,dh.KHACHHANG_FK,dhsp.SANPHAM_FK ,sum(soluong) soluong , sum( round( dhsp.soluong *  dhsp.giamua * ( 1 + dhsp.thuevat/100.0) ,0)) tongtien, sum( round( dhsp.soluong *  dhsp.giamua * ( 1 + dhsp.thuevat/100.0) ,0)) DS_TRU_TRA_LAI  "+ 
				"\n 		from donhang dh  "+ 
				"\n 		inner join donhang_sanpham dhsp on dh.PK_SEQ = dhsp.donhang_fk  "+ 
				"\n 		where dh.TRANGTHAI in (1, 5) and dh.ngaynhap between '"+ tungay +"' and '"+ denngay +"' " + searchstr + 
				"\n			 and not exists ( select 1 from Erp_HangTraLaiNpp x where x.DONHANG_FK = dh.pk_seq and x.trangthai  in (1, 5) )	" +
				"\n 		group by dh.NPP_FK,dh.KHACHHANG_FK,dhsp.SANPHAM_FK "+ 
				"\n 		union all "+ 
				"\n 		select dh.NPP_FK,dh.KHACHHANG_FK,dhsp.SANPHAM_FK,sum( (-1)*soluong),0 , sum( round( (-1)*soluong * DonGia,0)) tongtien "+ 
				"\n 		from Erp_HangTraLaiNpp dh  "+ 
				"\n 		inner join Erp_HangTraLaiNpp_SanPham dhsp on dh.PK_SEQ = dhsp.HangTraLai_fk  "+ 
				"\n 		where dh.TRANGTHAI in (1, 5) and dh.DONHANG_FK is null and dh.ngaytra between '"+ tungay +"' and '"+ denngay +"' " + searchstr + 
				"\n 		group by dh.NPP_FK,dh.KHACHHANG_FK,dhsp.SANPHAM_FK "+ 
				"\n 	) ds "+ 
				"\n 	group by ds.NPP_FK,ds.KHACHHANG_FK,ds.SANPHAM_FK "+ 
				"\n ) "+ 
				"\n , dskh as  "+
				"\n ( " +
				"\n		select ds.NPP_FK,ds.KHACHHANG_FK" +
				"\n		from ds" +
				"\n		group by  ds.NPP_FK,ds.KHACHHANG_FK" +
				"\n		having sum (tongtien) > 	" + _ds_toi_thieu_kh +
				"\n )" +
				subquery_Top + 
				
				/*"\n select top("+ RECORD_SIZE +") * from( select row_number() over(order by addNo.Mien, [NPP], [sanpham],sott ) as _no, addNo.* from "+
				"\n (  "+*/
				"\n 	select stt as [S??? TT], Mien [Mi???n], NPP, Sanpham [S???n ph???m], sokh [S??? kh??ch h??ng], soluong [S??? l?????ng], tongtien [T???ng ti???n],  DS_TRU_TRA_LAI [DS tr??? tr??? l???i]  " +
				"\n  	from " +
				"\n		( "+
				"\n 		select 0 stt, v.TEN Mien, npp.TEN[NPP],sp.TEN Sanpham, ds.sokh ,ds.soluong,ds.tongtien, ds.DS_TRU_TRA_LAI  "+ 
				"\n 		from  "+ 
				"\n 		( "+ 
				"\n 			select NPP_FK, SANPHAM_FK,cast( count(distinct khachhang_fk) as varchar) sokh, "+ 
				"\n 			sum(soluong) soluong, sum(tongtien)tongtien, sum(DS_TRU_TRA_LAI)DS_TRU_TRA_LAI "+ 
				"\n 			from ds" +
				"\n				where exists ( select 1 from dskh where dskh.NPP_FK = ds.NPP_FK and dskh.KHACHHANG_FK = ds.KHACHHANG_FK)  "+ 
				"\n 			group by NPP_FK, SANPHAM_FK "+ 
				"\n 		)ds  "+ 
				"\n 		inner join NHAPHANPHOI npp on npp.PK_SEQ = ds.NPP_FK "+ 
				"\n 		inner join KHUVUC kv on kv.PK_SEQ = npp.KHUVUC_FK "+ 
				"\n 		inner join vung v on v.PK_SEQ = kv.VUNG_FK "+ 
				"\n 		inner join SANPHAM sp on sp.PK_SEQ =ds.SANPHAM_FK where 1=1 "+ searchstr2 + 
				"\n 		union all "+ 
				"\n 		select ROW_NUMBER() OVER(PARTITION BY v.TEN,npp.TEN,sp.TEN ORDER BY kh.ten ASC) as stt, "+ 
				"\n 		v.TEN Mien, npp.TEN[NPP],sp.TEN Sanpham, kh.TEN  ,ds.soluong,ds.tongtien , ds.DS_TRU_TRA_LAI "+ 
				"\n 		from ds  "+ 
				"\n 		inner join KHACHHANG kh on kh.PK_SEQ = ds.KHACHHANG_FK "+ 
				"\n 		inner join NHAPHANPHOI npp on npp.PK_SEQ = ds.NPP_FK "+ 
				"\n 		inner join KHUVUC kv on kv.PK_SEQ = npp.KHUVUC_FK "+ 
				"\n 		inner join vung v on v.PK_SEQ = kv.VUNG_FK "+ 
				"\n 		inner join SANPHAM sp on sp.PK_SEQ =ds.SANPHAM_FK " +
				"\n			where exists ( select 1 from dskh where dskh.NPP_FK = ds.NPP_FK and dskh.KHACHHANG_FK = ds.KHACHHANG_FK) "+ searchstr2 +
				"\n 	) as t where 1=1 "+ subquery_Bot;
				//"\n ) addNo) list where _no > '"+ INITIAL +"' ";
		  }	
		  System.out.println("sql do phu sp : "+ sql);
		  return sql;
	}
	public JSONArray DoPhuSP(HttpServletRequest request, Utility util, boolean isExcel) throws SQLException, ClassNotFoundException 
	{
		dbutils db = new dbutils();
		JSONArray array = new JSONArray();
		String laytheo = util.antiSQLInspection(request.getParameter("laytheo"));
		String sql = queryDoPhuSP(request, util, QUERY_DETAIL_ACTION, RECORD_SIZE, INITIAL);
		ResultSet rs = db.get(sql);
		while (rs.next()) 
		{
		   JSONArray ja = new JSONArray();
		   if(laytheo.equals("0"))
		   {
			   String stt = "";
			   if(!rs.getString("S??? TT").equals("0"))
				   stt= rs.getString("S??? TT");
			   ja.put(stt);
			   ja.put(rs.getString("Mi???n"));
			   ja.put(rs.getString("NPP"));
			   ja.put(rs.getString("S???n ph???m"));
			   ja.put(rs.getString("S??? kh??ch h??ng"));
			   ja.put(formatter.format(rs.getDouble("T??? l???")));
			   ja.put(formatter.format(rs.getDouble("S??? l?????ng")));
			   ja.put(formatter.format(rs.getDouble("T???ng ti???n")));
			   ja.put(formatter.format(rs.getDouble("DS tr??? tr??? l???i")));
		   }
		   else
		   {
			   String stt = "";
			   if(!rs.getString("S??? TT").equals("0"))
				   stt= rs.getString("S??? TT");
			   ja.put(stt);
			   ja.put(rs.getString("Mi???n"));
			   ja.put(rs.getString("NPP"));
			   ja.put(rs.getString("S???n ph???m"));
			   ja.put(rs.getString("S??? kh??ch h??ng"));
			   ja.put(formatter.format(rs.getDouble("S??? l?????ng")));
			   ja.put(formatter.format(rs.getDouble("T???ng ti???n")));
			   ja.put(formatter.format(rs.getDouble("DS tr??? tr??? l???i")));
			   
		   }
		   array.put(ja); 
		}
		rs.close();
		db.shutDown();
		return array;
	}
	
	
	public JSONArray DoanhThuTheoSP(HttpServletRequest request, Utility util, boolean isExcel) throws SQLException, ClassNotFoundException 
	{
		dbutils db = new dbutils();
		JSONArray array = new JSONArray();
		String sql = queryDoanhThuTheoSP( request, util, QUERY_DETAIL_ACTION,RECORD_SIZE,INITIAL);
		ResultSet rs = db.get(sql);
		while (rs.next()) 
		{
		   JSONArray ja = new JSONArray();
		   ja.put(rs.getString("STT"));
		   ja.put(rs.getString("M?? V???T T???"));
		   ja.put(rs.getString("T??N V???T T??"));
		   ja.put(rs.getString("??VT"));
		   ja.put((formatter.format(rs.getDouble("S??? L?????NG"))));
		   ja.put((formatter.format(rs.getDouble("DOANH S???"))));
		   ja.put((formatter.format(rs.getDouble("DS TR??? TR??? L???I"))));
		   array.put(ja); 
		}
		rs.close();
		db.shutDown();
		return array;
	}
	
	public JSONArray DoanhThuBanRa(HttpServletRequest request, Utility util, boolean isExcel) throws SQLException, ClassNotFoundException 
	{
		dbutils db = new dbutils();
		JSONArray array = new JSONArray();
		
		String sql = queryDoanhThuBanRa( request,  util,QUERY_DETAIL_ACTION,RECORD_SIZE,INITIAL);
		ResultSet rs = db.get(sql);
		while (rs.next()) 
		{
		   JSONArray ja = new JSONArray();
		   ja.put(rs.getString("STT"));
		   ja.put(rs.getString("Mi???n"));
		   ja.put(rs.getString("Nh?? ph??n ph???i"));
		   ja.put((formatter.format(rs.getDouble("DOANH S???"))));
		   ja.put((formatter.format(rs.getDouble("DS TR??? TR??? L???I"))));
		   array.put(ja); 
		}
		rs.close();
		db.shutDown();
		return array;
	}
	
	
	public JSONArray DoanhSoTheoSP(HttpServletRequest request, Utility util, boolean isExcel) throws SQLException, ClassNotFoundException 
	{
		String laytheo = util.antiSQLInspection(request.getParameter("laytheo"));
		if(laytheo == null) laytheo= "0";
		dbutils db = new dbutils();
		JSONArray array = new JSONArray();
		String sql = queryDoanhSoTheoSP( request, util, QUERY_DETAIL_ACTION,RECORD_SIZE,INITIAL);
		ResultSet rs = db.get(sql);
		while (rs.next()) 
		{
		   JSONArray ja = new JSONArray();
		   if(laytheo.equals("2"))
		   {
			   ja.put(rs.getString("STT"));
			   ja.put(rs.getString("CHI NH??NH ?????I T??C"));
			   ja.put(rs.getString("M?? V???T T??"));
			   ja.put(rs.getString("T??N V???T T??"));
			   ja.put(rs.getString("??VT"));		   
			   ja.put((formatter.format(rs.getDouble("S??? L?????NG"))));
			   ja.put((formatter.format(rs.getDouble("DOANH S???"))));
			   ja.put((formatter.format(rs.getDouble("DS TR??? TR??? L???I"))));
		   }
		   else
		   if(laytheo.equals("1"))
		   {
			   ja.put(rs.getString("STT"));
			   ja.put(rs.getString("CHI NH??NH ?????I T??C"));
			   ja.put(rs.getString("NH??N VI??N B??N H??NG"));
			   ja.put(rs.getString("M?? V???T T??"));
			   ja.put(rs.getString("T??N V???T T??"));
			   ja.put(rs.getString("??VT"));		   
			   ja.put((formatter.format(rs.getDouble("S??? L?????NG"))));
			   ja.put((formatter.format(rs.getDouble("DOANH S???"))));
			   ja.put((formatter.format(rs.getDouble("DS TR??? TR??? L???I"))));
		   }
		   else
		   {
			   ja.put(rs.getString("STT"));
			   ja.put(rs.getString("CHI NH??NH ?????I T??C"));
			   ja.put(rs.getString("NH??N VI??N B??N H??NG"));
			   ja.put(rs.getString("M?? KH"));
			   ja.put(rs.getString("T??N KH"));
			   ja.put(rs.getString("?????A CH??? KH??CH H??NG"));
			   ja.put(rs.getString("M?? V???T T??"));
			   ja.put(rs.getString("T??N V???T T??"));
			   ja.put(rs.getString("??VT"));
			   
			   ja.put((formatter.format(rs.getDouble("S??? L?????NG"))));
			   ja.put((formatter.format(rs.getDouble("DOANH S???"))));
			   ja.put((formatter.format(rs.getDouble("DS TR??? TR??? L???I"))));
		   }
		   
		   array.put(ja); 
		}
		rs.close();
		db.shutDown();
		return array;
	}
	
	
	public JSONArray DoanhThuKhachHang(HttpServletRequest request, Utility util, boolean isExcel) throws SQLException, ClassNotFoundException 
	{
		dbutils db = new dbutils();
		JSONArray array = new JSONArray();
		String sql = queryDoanhThuKhachHang( request, util, QUERY_DETAIL_ACTION,RECORD_SIZE,INITIAL);
		ResultSet rs = db.get(sql);
		while (rs.next()) 
		{
		   JSONArray ja = new JSONArray();
		   ja.put(rs.getString("STT"));
		   ja.put(rs.getString("T???NH TH??NH"));
		   ja.put(rs.getString("CHI NH??NH ?????I T??C"));
		   ja.put(rs.getString("NVBH"));
		   ja.put(rs.getString("M?? KH"));
		   ja.put(rs.getString("T??N KH"));
		   ja.put(rs.getString("?????A CH??? KH"));
		   ja.put(rs.getString("LO???I KH"));
		   ja.put((formatter.format(rs.getDouble("DOANH S???"))));
		   ja.put((formatter.format(rs.getDouble("DS TR??? TR??? L???I"))));
		   
		   
		  
		   
		   array.put(ja); 
		}
		rs.close();
		db.shutDown();
		return array;
	}
	
	
	
	public static String queryDoanhThuBanRa(HttpServletRequest request, Utility util, int action,int RECORD_SIZE,int INITIAL )
	{
		// int action = 0 xuat excel 
		// 			 = 1 query detal
		//			= 2 query count
		
		
		String userId = util.antiSQLInspection(request.getParameter("userId"));
		String tungay = util.antiSQLInspection(request.getParameter("Sdays"));
		String denngay = util.antiSQLInspection(request.getParameter("Edays"));
		String vungId = util.antiSQLInspection(request.getParameter("vungId"));
		String kenhId = util.antiSQLInspection(request.getParameter("kbhId"));
		String nppId = util.antiSQLInspection(request.getParameter("nppId"));
		String nhomId = util.antiSQLInspection(request.getParameter("nhomId"));
		String searchstr = "";
		
		
		
		
		if(nppId != null && nppId.length() > 0) { searchstr += " and dh.npp_fk = '"+ nppId +"' "; }
		if(kenhId != null && kenhId.length() > 0) { searchstr += " and dh.kbh_fk = '"+ kenhId +"' "; }
		if(nhomId != null && nhomId.length() > 0) { searchstr += " and exists ( select 1 from NhomHang_SanPham nsp where nsp.NhomHang_FK = '"+ nhomId +"' and nsp.SanPham_FK = dhsp.sanpham_fk ) "; }
		
		
		if(vungId.length() > 0) 
		{   
			searchstr += " and exists ( select 1 from nhaphanphoi npp where npp.pk_seq = dh.npp_fk " +
						 " and exists ( select 1 from khuvuc kv where kv.pk_seq = npp.khuvuc_fk " +
						 " and exists ( select 1 from vung v where v.pk_seq = '"+ vungId +"' and v.pk_seq = kv.vung_fk ) ) ) ";
		}
		
		searchstr += " and dh.DDKD_FK in ("+Utility.PhanQuyenDDKD(userId)+") ";
		searchstr += " and dh.NPP_FK in ("+Utility.Quyen_npp(userId)+") ";
		
		String subquery_Top = " ";
		String subquery_Bot = " ";
		
		if(action == QUERY_DETAIL_ACTION)
		{
			subquery_Top = "\n select top("+ RECORD_SIZE +") * from  \n (  ";
			subquery_Bot = "\n ) list where STT > '"+ INITIAL +"' ";
		}else
			if( action == QUERY_COUNT_ACTION)
			{
				subquery_Top = "\n select count(*) num from ( ";
				subquery_Bot  = "\n ) as t ";
			}
			else
				if( action == QUERY_SUM_DATA_ACTION)
				{
					subquery_Top = "\n select sum([DOANH S???])[DOANH S???] , SUM([DS TR??? TR??? L???I])[DS TR??? TR??? L???I] from ( ";
					subquery_Bot  = "\n ) as t ";
				}
		
		String sql = 
			"\n with ds as "+ 
			"\n  ( "+ 
			"\n  	select  ds.NPP_FK, sum(soluong) soluong, sum(ds_tru_tra_lai)ds_tru_tra_lai, sum(avat)avat "+ 
			"\n  	from "+ 
			"\n  	( "+ 
			"\n 		select dh.NPP_FK,sum(dhsp.soluong) soluong , sum( round( dhsp.soluong * dhsp.GIAMUA * (1 + dhsp.THUEVAT/100.0) ,0)) ds_tru_tra_lai, sum( round( dhsp.soluong * dhsp.GIAMUA * (1 + dhsp.THUEVAT/100.0) ,0)) avat  "+ 
			"\n 		from donhang dh  "+ 
			"\n 		inner join donhang_sanpham dhsp on dh.PK_SEQ = dhsp.donhang_fk  "+ 
			"\n 		where  dh.TRANGTHAI in (1, 5) and dh.ngaynhap between '"+ tungay +"' and '"+ denngay +"' " + searchstr + 
			"\n			 and not exists ( select 1 from Erp_HangTraLaiNpp x where x.DONHANG_FK = dh.pk_seq and x.trangthai in (1, 5) )	" +	
			"\n 		group by dh.NPP_FK "+ 
			"\n 		union all "+ 
			"\n 		select dh.NPP_FK,sum( (-1)*soluong),sum( round( (-1)*soluong * DonGia,0))  , 0  "+ 
			"\n 		from Erp_HangTraLaiNpp dh  "+ 
			"\n 		inner join Erp_HangTraLaiNpp_SanPham dhsp on dh.PK_SEQ = dhsp.HangTraLai_fk  "+ 
			"\n 		where dh.TRANGTHAI in (1, 5) and dh.DONHANG_FK is null and dh.ngaytra between '"+ tungay +"' and '"+ denngay +"' " + searchstr + 
			"\n 		group by dh.NPP_FK "+ 
			"\n  	) ds "+ 
			"\n  	group by ds.NPP_FK "+ 
			"\n  ) " +
			subquery_Top +
			"\n 	select [STT], [Mi???n], [Nh?? ph??n ph???i], [DS TR??? TR??? L???I], [DOANH S???] " +
			"\n  	from " +
			"\n		( "+
			"\n			select ROW_NUMBER() OVER( ORDER BY npp.TEN ASC) as [STT], "+
			"\n			v.TEN [Mi???n], npp.TEN [Nh?? ph??n ph???i], round(ds.ds_tru_tra_lai,0) [DS TR??? TR??? L???I], round(ds.avat,0) [DOANH S???] "+
			"\n			from ds "+
			"\n			inner join NHAPHANPHOI npp on npp.PK_SEQ = ds.NPP_FK "+
			"\n			inner join KHUVUC kv on kv.PK_SEQ = npp.KHUVUC_FK "+
			"\n			inner join vung v on v.PK_SEQ = kv.VUNG_FK "+
			"\n	 		where 1=1 "+
			"\n 	) as t where 1=1 " + subquery_Bot ;
			
		System.out.println("sql = "+ sql);
		return sql;
		
	}
	
	public static String queryDoanhThuKhachHang(HttpServletRequest request, Utility util, int action,int RECORD_SIZE,int INITIAL )
	{
 
		//  ACTION :
		//	0 xuat excel 
		//  1 query detal
		//	2 query count	
		String userId = util.antiSQLInspection(request.getParameter("userId"));
		String tungay = util.antiSQLInspection(request.getParameter("tungay"));
		String denngay = util.antiSQLInspection(request.getParameter("denngay"));
		String vungId = util.antiSQLInspection(request.getParameter("vungId"));
		String kenhId = util.antiSQLInspection(request.getParameter("kenhId"));
		String khId = util.antiSQLInspection(request.getParameter("khId"));
		String nppId = util.antiSQLInspection(request.getParameter("nppId"));
		String ddkdId = util.antiSQLInspection(request.getParameter("ddkdId"));
		String searchstr = "";
		String subquery_Top = " ";
		String subquery_Bot = " ";
		System.out.println("kenhId : "+ kenhId);
		if(userId.length() > 0) { searchstr += " and dh.npp_fk in " + util.quyen_npp(userId); }
		if(nppId.length() > 0) { searchstr += " and dh.npp_fk = '"+ nppId +"' "; }
		if(kenhId.length() > 0) { searchstr += " and dh.kbh_fk = '"+ kenhId +"' "; }
		if(ddkdId.length() > 0) { searchstr += " and dh.khachhang_fk in (select khachhang_fk from khachhang_tuyenbh where tbh_Fk in (select pk_seq from tuyenbanhang where ddkd_fk  = "+ddkdId+") ) "; }
		if(khId.length() > 0) { searchstr += " and dh.khachhang_fk in ( "+ khId +" ) "; }
		if(vungId.length() > 0) 
		{   
			searchstr += " and exists ( select 1 from nhaphanphoi npp where npp.pk_seq = dh.npp_fk " +
						 " and exists ( select 1 from khuvuc kv where kv.pk_seq = npp.khuvuc_fk " +
						 " and exists ( select 1 from vung v where v.pk_seq = '"+ vungId +"' and v.pk_seq = kv.vung_fk ) ) ) ";
		}
		
		searchstr += " and dh.DDKD_FK in ("+Utility.PhanQuyenDDKD(userId)+") ";
		searchstr += " and dh.NPP_FK in ("+Utility.Quyen_npp(userId)+") ";
		
		if(action == QUERY_DETAIL_ACTION)
		{
			subquery_Top = 
				"\n select top("+ RECORD_SIZE +") * from "+
				"\n (  ";
			subquery_Bot = "\n )  list where STT > '"+ INITIAL +"' ";
		}
		else if( action == QUERY_COUNT_ACTION)
		{
			subquery_Top = "\n select count(*) num from ( ";
			subquery_Bot  = "\n ) as t ";
		}
		
		String sql = 
				"\n with ds as "+ 
				"\n ( "+ 
				"\n 	select ds.NPP_FK,ds.KHACHHANG_FK, sum(avat)avat , sum(ds_tru_tra_lai)ds_tru_tra_lai "+ 
				"\n 	from "+ 
				"\n 	( "+ 
				"\n 		select dh.NPP_FK,dh.KHACHHANG_FK , sum( round( dhsp.soluong * dhsp.GIAMUA * (1 + dhsp.THUEVAT/100.0) ,0)) avat , sum( round( dhsp.soluong * dhsp.GIAMUA * (1 + dhsp.THUEVAT/100.0) ,0)) ds_tru_tra_lai  "+ 
				"\n 		from donhang dh  "+ 
				"\n 		inner join donhang_sanpham dhsp on dh.PK_SEQ = dhsp.donhang_fk  "+ 
				"\n 		where  dh.TRANGTHAI in (1, 5) and dh.ngaynhap between '"+ tungay +"' and '"+ denngay +"' " + searchstr + 
				"\n			 and not exists ( select 1 from Erp_HangTraLaiNpp x where x.DONHANG_FK = dh.pk_seq and x.trangthai  in (1, 5) )	" +
				"\n 		group by dh.NPP_FK,dh.KHACHHANG_FK "+ 
				"\n 		union all "+ 
				"\n 		select dh.NPP_FK,dh.KHACHHANG_FK , 0 ,  sum( round( (-1)*soluong * DonGia,0)) tongtien "+ 
				"\n 		from Erp_HangTraLaiNpp dh  "+ 
				"\n 		inner join Erp_HangTraLaiNpp_SanPham dhsp on dh.PK_SEQ = dhsp.HangTraLai_fk  "+ 
				"\n 		where dh.TRANGTHAI in (1, 5) and dh.DONHANG_FK is null and dh.ngaytra between '"+ tungay +"' and '"+ denngay +"' " + searchstr + 
				"\n 		group by dh.NPP_FK,dh.KHACHHANG_FK "+ 
				
				"\n 	) ds "+ 
				"\n 	group by ds.NPP_FK,ds.KHACHHANG_FK "+ 
				"\n ) "+ 

				subquery_Top +
				"\n select STT,Tinhthanh [T???NH TH??NH], NPP [CHI NH??NH ?????I T??C], DDKD [NVBH],makh [M?? KH],tenkh [T??N KH], diachikh [?????A CH??? KH], loai [LO???I KH],  tongtien [DOANH S???], ds_tru_tra_lai [DS TR??? TR??? L???I] " +//, giamua [????n gi??]
				"\n from " +
				"\n ( "+
				"\n 	select ROW_NUMBER() OVER( ORDER BY kh.mafast ASC) as stt ,npp.ten [NPP], ddkd.TEN [DDKD],kh.mafast [makh],kh.diachi [diachikh],kh.ten tenkh, db.ten Tinhthanh,l.loai, ds.avat tongtien,ds.ds_tru_tra_lai  "+ 
				"\n 	from ds  "+ 
				"\n 	inner join khachhang kh on kh.pk_seq =ds.khachhang_fk "+
				"\n 	left  join loaicuahang l on l.pk_seq = kh.lch_fk " +
				"\n 	left join TINHTHANH db on db.pk_seq = kh.TINHTHANH_fk "+
				"\n 	inner join NHAPHANPHOI npp on npp.pk_seq = kh.npp_fk  "+
				"\n 	outer apply "+
				"\n 	( "+
				"\n 		select  top 1 ddkd.* "+
				"\n 		from DAIDIENKINHDOANH ddkd "+
				"\n 		where  ddkd.PK_SEQ in (select DDKD_FK from TUYENBANHANG t where t.npp_fk = npp.pk_seq and t.PK_SEQ in (select tbh_fk from KHACHHANG_TUYENBH where khachhang_fk = kh.PK_SEQ ) ) "+
				"\n 		order by ddkd.TRANGTHAI desc "+
				"\n 	)ddkd "+
				"\n 	where 1=1 "+
				"\n ) as t where 1=1 "+ subquery_Bot;
		System.out.println("sql doanh thu sp = "+ sql);
		return sql;
	}
	public static String queryDoanhThuSanPhamTheoNPP(HttpServletRequest request, Utility util, int action,int RECORD_SIZE,int INITIAL )
	{

		//  ACTION :
		//	0 xuat excel 
		//  1 query detal
		//	2 query count	
		String userId = util.antiSQLInspection(request.getParameter("userId"));
		String tungay = util.antiSQLInspection(request.getParameter("tungay"));
		String denngay = util.antiSQLInspection(request.getParameter("denngay"));
		String vungId = util.antiSQLInspection(request.getParameter("vungId"));
		String kenhId = util.antiSQLInspection(request.getParameter("kenhId"));
		String spId = util.antiSQLInspection(request.getParameter("spId"));
		String nppId = util.antiSQLInspection(request.getParameter("nppId"));
		String nhomId = util.antiSQLInspection(request.getParameter("nhomId"));
		String searchstr = "";
		String subquery_Top = " ";
		String subquery_Bot = " ";
		System.out.println("kenhId : "+ kenhId);
		if(userId.length() > 0) { searchstr += " and dh.npp_fk in " + util.quyen_npp(userId); }
		if(nppId.length() > 0) { searchstr += " and dh.npp_fk = '"+ nppId +"' "; }
		if(kenhId.length() > 0) { searchstr += " and dh.kbh_fk = '"+ kenhId +"' "; }
		if(nhomId.length() > 0) { searchstr += " and exists ( select 1 from NhomHang_SanPham nsp where nsp.NhomHang_FK = '"+ nhomId +"' and nsp.SanPham_FK = dhsp.sanpham_fk ) "; }
		if(spId.length() > 0) { searchstr += " and dhsp.sanpham_fk in ( "+ spId +" ) "; }
		if(vungId.length() > 0) 
		{   
			searchstr += " and exists ( select 1 from nhaphanphoi npp where npp.pk_seq = dh.npp_fk " +
			" and exists ( select 1 from khuvuc kv where kv.pk_seq = npp.khuvuc_fk " +
			" and exists ( select 1 from vung v where v.pk_seq = '"+ vungId +"' and v.pk_seq = kv.vung_fk ) ) ) ";
		}

		searchstr += " and dh.DDKD_FK in ("+Utility.PhanQuyenDDKD(userId)+") ";
		searchstr += " and dh.NPP_FK in ("+Utility.Quyen_npp(userId)+") ";

		if(action == QUERY_DETAIL_ACTION)
		{
			subquery_Top = 
				"\n select top("+ RECORD_SIZE +") * from( select row_number() over(order by [CHI NH??NH/?????I T??C] ASC,addNo.[M?? v???t t??]) as _no, addNo.* from "+
				"\n (  ";
			subquery_Bot = "\n ) addNo) list where _no > '"+ INITIAL +"' ";
		}
		else if( action == QUERY_COUNT_ACTION)
		{
			subquery_Top = "\n select count(*) num from ( ";
			subquery_Bot  = "\n ) as t ";
		}
		else if( action == QUERY_SUM_DATA_ACTION)
		{
			subquery_Top = "\n select  SUM([DOANH THU])[DOANH THU],SUM([THU??? GTGT])[THU??? GTGT], SUM([T???NG TI???N])[T???NG TI???N] from ( ";
			subquery_Bot  = "\n ) as t ";
		}

		String sql = 
			"\n with ds as "+ 
			"\n ( "+ 
			"\n 	select ds.SANPHAM_FK,ds.NPP_FK, sum(soluong) soluong, sum(tongtien)tongtien, sum(avat)avat "+ 
			"\n 	from "+ 
			"\n 	( "+ 
			"\n 		select dh.NPP_FK,dhsp.SANPHAM_FK ,sum(dhsp.soluong) soluong , sum(dhsp.thanhtien) tongtien, sum( dhsp.thanhtien + dhsp.vat) avat  "+ 
			"\n 		from hoadon dh  "+ 
			"\n 		inner join hoadon_sp dhsp on dh.PK_SEQ = dhsp.hoadon_fk  "+ 
			"\n 		where  dh.TRANGTHAI in (2,4) and dh.ngayxuathd between '"+ tungay +"' and '"+ denngay +"' " + searchstr + 
			"\n 		group by dh.NPP_FK,dh.NPP_FK,dhsp.SANPHAM_FK "+ 				
			"\n 	) ds "+ 
			"\n 	group by ds.SANPHAM_FK,ds.NPP_FK "+ 
			"\n ) "+ 

			subquery_Top +
			"\n select STT ,ten_nuoc_sx[N?????C S???N XU???T],nha_sx [NH?? S???N XU???T], mavt [M?? V???T T??], tenvt [T??N V???T T??], donvi [??VT], soluong [S??? L?????NG], vung [MI???N],NPP [CHI NH??NH/?????I T??C], doanhthu [DOANH THU], thue [THU??? GTGT], tongtien [T???NG TI???N] " +//, giamua [????n gi??]
			"\n from " +
			"\n ( "+
			"\n 	select ROW_NUMBER() OVER( ORDER BY sp.ma ASC) as stt, "+
			"\n 	isnull(sp.ten_nuoc_sx,'')ten_nuoc_sx, isnull(sp.nha_sx,'')nha_sx, " +
			"\n			sp.ma [mavt], sp.TEN [tenvt], dv.DONVI, v.ten Vung , npp.ten NPP, ds.soluong,  " +
			"\n 	ds.tongtien as doanhthu, ds.avat - ds.tongtien  as thue, ds.avat tongtien "+ 
			"\n 	from ds  "+ 
			"\n 	inner join SANPHAM sp on sp.PK_SEQ =ds.SANPHAM_FK" +
			"\n		inner join nhaphanphoi npp on npp.pk_seq =ds.npp_fk" +
			"\n		left join khuvuc kv on kv.pk_seq = npp.khuvuc_fk" +
			"\n		left join vung v on v.pk_seq = kv.vung_fk "+ 
			"\n 	inner join DONVIDOLUONG dv on dv.PK_SEQ = sp.DVDL_FK "+
			"\n 	where 1=1 "+
			"\n ) as t where 1=1 "+ subquery_Bot;
		System.out.println("sql doanh thu sp theo NPP = "+ sql);
		return sql;
	}
	
	public static String queryDoanhSoTheoSP(HttpServletRequest request, Utility util, int action,int RECORD_SIZE,int INITIAL )
	{
 
		//  ACTION :
		//	0 xuat excel 
		//  1 query detal
		//	2 query count	
		String userId = util.antiSQLInspection(request.getParameter("userId"));
		String tungay = util.antiSQLInspection(request.getParameter("tungay"));
		String denngay = util.antiSQLInspection(request.getParameter("denngay"));
		String vungId = util.antiSQLInspection(request.getParameter("vungId"));if(vungId == null) vungId = "";
		String kenhId = util.antiSQLInspection(request.getParameter("kenhId"));if(kenhId == null) kenhId = "";
		String spId = util.antiSQLInspection(request.getParameter("spId"));if( spId == null)  spId = "";
		String nppId = util.antiSQLInspection(request.getParameter("nppId"));
		String nhomId = util.antiSQLInspection(request.getParameter("nhomId"));
		String searchstr = "";
		String subquery_Top = " ";
		String subquery_Bot = " ";
		System.out.println("kenhId : "+ kenhId);
		
		
		String nppUser =util.getIdNhapp(userId);
		if(nppUser != null && nppUser.length() > 0)
		{
			nppId = nppUser;
		}
		else
		{
			searchstr += " and dh.DDKD_FK in ("+Utility.PhanQuyenDDKD(userId)+") ";
			searchstr += " and dh.NPP_FK in ("+Utility.Quyen_npp(userId)+") ";
		}
		
		if(nppId.length() > 0) { searchstr += " and dh.npp_fk = '"+ nppId +"' "; }
		if(kenhId.length() > 0) { searchstr += " and dh.kbh_fk = '"+ kenhId +"' "; }
		if(nhomId.length() > 0) { searchstr += " and exists ( select 1 from NhomHang_SanPham nsp where nsp.NhomHang_FK = '"+ nhomId +"' and nsp.SanPham_FK = dhsp.sanpham_fk ) "; }
		if(spId.length() > 0) { searchstr += " and dhsp.sanpham_fk in ( "+ spId +" ) "; }
		if(vungId.length() > 0) 
		{   
			searchstr += " and exists ( select 1 from nhaphanphoi npp where npp.pk_seq = dh.npp_fk " +
						 " and exists ( select 1 from khuvuc kv where kv.pk_seq = npp.khuvuc_fk " +
						 " and exists ( select 1 from vung v where v.pk_seq = '"+ vungId +"' and v.pk_seq = kv.vung_fk ) ) ) ";
		}
		
		
		
		
		String laytheo = util.antiSQLInspection(request.getParameter("laytheo"));
		if(laytheo == null )laytheo = "0";
		if(action == QUERY_DETAIL_ACTION)
		{
			subquery_Top = 
				"\n select top("+ RECORD_SIZE +")* from "+
				"\n (  ";
			subquery_Bot = "\n ) list where stt > '"+ INITIAL +"' ";
		}
		else if( action == QUERY_COUNT_ACTION)
		{
			subquery_Top = "\n select count(*) num from ( ";
			subquery_Bot  = "\n ) as t ";
		}
		else if( action == QUERY_SUM_DATA_ACTION)
		{
			subquery_Top = "\n select sum([DOANH S???])[DOANH S???],sum([DS TR??? TR??? L???I])[DS TR??? TR??? L???I] from ( ";
			subquery_Bot  = "\n ) as t ";
		}
		
		String sql = 
				"\n with ds as "+ 
				"\n ( "+ 
				"\n 	select ds.SANPHAM_FK,ds.NPP_FK,ds.DDKD_FK,ds.KHACHHANG_FK, sum(soluong) soluong, sum(DS_TRU_TRA_LAI)DS_TRU_TRA_LAI, sum(AVAT)AVAT  "+ 
				"\n 	from "+ 
				"\n 	( "+ 
				"\n 		select dh.NPP_FK,dh.DDKD_FK,dh.KHACHHANG_FK,dhsp.SANPHAM_FK ,sum(dhsp.soluong) soluong , sum( round( dhsp.soluong * dhsp.GIAMUA * (1 + dhsp.THUEVAT/100.0) ,0)) AVAT, sum( round( dhsp.soluong * dhsp.GIAMUA * (1 + dhsp.THUEVAT/100.0) ,0)) DS_TRU_TRA_LAI   "+ 
				"\n 		from donhang dh  "+ 
				"\n 		inner join donhang_sanpham dhsp on dh.PK_SEQ = dhsp.donhang_fk  "+ 
				"\n 		where  dh.TRANGTHAI in (1, 5) and dh.ngaynhap between '"+ tungay +"' and '"+ denngay +"' " + searchstr + 
				"\n			 and not exists ( select 1 from Erp_HangTraLaiNpp x where x.DONHANG_FK = dh.pk_seq and x.trangthai  in (1, 5) )	" +
				"\n 		group by dh.NPP_FK,dh.KHACHHANG_FK,dhsp.SANPHAM_FK,dh.DDKD_FK "+ 
				"\n 		union all "+ 
				"\n 		select dh.NPP_FK,dh.DDKD_FK,dh.KHACHHANG_FK,dhsp.SANPHAM_FK ,sum( (-1)*soluong),0  , sum( round( (-1)*soluong * DonGia,0)) DS_TRU_TRA_LAI "+ 
				"\n 		from Erp_HangTraLaiNpp dh  "+ 
				"\n 		inner join Erp_HangTraLaiNpp_SanPham dhsp on dh.PK_SEQ = dhsp.HangTraLai_fk  "+ 
				"\n 		where dh.TRANGTHAI in (1, 5) and dh.DONHANG_FK is null and dh.ngaytra between '"+ tungay +"' and '"+ denngay +"' " + searchstr + 
				"\n 		group by dh.NPP_FK,dh.KHACHHANG_FK,dhsp.SANPHAM_FK,dh.DDKD_FK "+ 
				
				"\n 	) ds "+ 
				"\n 	group by ds.SANPHAM_FK,ds.NPP_FK,ds.DDKD_FK,ds.KHACHHANG_FK "+ 
				"\n ) "+ 

				subquery_Top +
				"\n select STT, NPP [CHI NH??NH ?????I T??C], DDKD [NH??N VI??N B??N H??NG],makh [M?? KH],tenkh [T??N KH], diachikh [?????A CH??? KH??CH H??NG], mavt [M?? V???T T??], tenvt [T??N V???T T??], donvi [??VT], soluong [S??? L?????NG], AVAT [DOANH S???], DS_TRU_TRA_LAI [DS TR??? TR??? L???I]  " +
				"\n from " +
				"\n ( "+
				"\n 	select ROW_NUMBER() OVER( ORDER BY sp.ma ASC) as stt ,npp.ten [NPP], ddkd.TEN [DDKD],kh.mafast [makh],kh.diachigiaohang [diachikh],kh.ten tenkh,sp.ma [mavt], sp.TEN [tenvt], dv.DONVI , ds.soluong,  ds.AVAT , ds.DS_TRU_TRA_LAI   "+ 
				"\n 	from ds  "+ 
				"\n 	inner join SANPHAM sp on sp.PK_SEQ =ds.SANPHAM_FK "+ 
				"\n 	inner join DONVIDOLUONG dv on dv.PK_SEQ = sp.DVDL_FK "+
				"\n 	inner join khachhang kh on kh.pk_seq =ds.khachhang_fk "+
				"\n 	inner join NHAPHANPHOI npp on npp.pk_seq = ds.npp_fk  "+
				"\n 	inner join DAIDIENKINHDOANH ddkd on ddkd.pk_seq =ds.ddkd_fk "+
				"\n 	where 1=1 "+
				"\n ) as t where 1=1 "+ subquery_Bot;
		if(laytheo.equals("1")) // DDKD
		{
			sql = 
				"\n with ds as "+ 
				"\n ( "+ 
				"\n 	select ds.SANPHAM_FK,ds.NPP_FK,ds.DDKD_FK, sum(soluong) soluong, sum(DS_TRU_TRA_LAI)DS_TRU_TRA_LAI, sum(AVAT)AVAT  "+ 
				"\n 	from "+ 
				"\n 	( "+ 
				"\n 		select dh.NPP_FK,dh.DDKD_FK,dhsp.SANPHAM_FK ,sum(dhsp.soluong) soluong , sum( round( dhsp.soluong * dhsp.GIAMUA * (1 + dhsp.THUEVAT/100.0) ,0)) AVAT, sum( round( dhsp.soluong * dhsp.GIAMUA * (1 + dhsp.THUEVAT/100.0) ,0)) DS_TRU_TRA_LAI   "+ 
				"\n 		from donhang dh  "+ 
				"\n 		inner join donhang_sanpham dhsp on dh.PK_SEQ = dhsp.donhang_fk  "+ 
				"\n 		where  dh.TRANGTHAI in (1, 5) and dh.ngaynhap between '"+ tungay +"' and '"+ denngay +"' " + searchstr + 
				"\n			 and not exists ( select 1 from Erp_HangTraLaiNpp x where x.DONHANG_FK = dh.pk_seq and x.trangthai  in (1, 5) )	" +
				"\n 		group by dh.NPP_FK,dh.DDKD_FK,dhsp.SANPHAM_FK "+ 
				"\n 		union all "+ 
				"\n 		select dh.NPP_FK,dh.DDKD_FK,dhsp.SANPHAM_FK ,sum( (-1)*soluong),0  , sum( round( (-1)*soluong * DonGia,0)) DS_TRU_TRA_LAI "+ 
				"\n 		from Erp_HangTraLaiNpp dh  "+ 
				"\n 		inner join Erp_HangTraLaiNpp_SanPham dhsp on dh.PK_SEQ = dhsp.HangTraLai_fk  "+ 
				"\n 		where dh.TRANGTHAI in (1, 5) and dh.DONHANG_FK is null and dh.ngaytra between '"+ tungay +"' and '"+ denngay +"' " + searchstr + 
				"\n 		group by dh.NPP_FK,dh.DDKD_FK,dhsp.SANPHAM_FK "+ 
				
				"\n 	) ds "+ 
				"\n 	group by ds.SANPHAM_FK,ds.NPP_FK,ds.DDKD_FK "+ 
				"\n ) "+ 

				subquery_Top +
				"\n select STT, NPP [CHI NH??NH ?????I T??C], DDKD [NH??N VI??N B??N H??NG], mavt [M?? V???T T??], tenvt [T??N V???T T??], donvi [??VT], soluong [S??? L?????NG], AVAT [DOANH S???], DS_TRU_TRA_LAI [DS TR??? TR??? L???I]  " +
				"\n from " +
				"\n ( "+
				"\n 	select ROW_NUMBER() OVER( ORDER BY sp.ma ASC) as stt ,npp.ten [NPP], ddkd.TEN [DDKD],sp.ma [mavt], sp.TEN [tenvt], dv.DONVI , ds.soluong,  ds.AVAT , ds.DS_TRU_TRA_LAI   "+ 
				"\n 	from ds  "+ 
				"\n 	inner join SANPHAM sp on sp.PK_SEQ =ds.SANPHAM_FK "+ 
				"\n 	inner join DONVIDOLUONG dv on dv.PK_SEQ = sp.DVDL_FK "+
				"\n 	inner join NHAPHANPHOI npp on npp.pk_seq = ds.npp_fk  "+
				"\n 	inner join DAIDIENKINHDOANH ddkd on ddkd.pk_seq =ds.ddkd_fk "+
				"\n 	where 1=1 "+
				"\n ) as t where 1=1 "+ subquery_Bot;
		}else if(laytheo.equals("2")) //NPP
		{
			sql = 
				"\n with ds as "+ 
				"\n ( "+ 
				"\n 	select ds.SANPHAM_FK,ds.NPP_FK, sum(soluong) soluong, sum(DS_TRU_TRA_LAI)DS_TRU_TRA_LAI, sum(AVAT)AVAT  "+ 
				"\n 	from "+ 
				"\n 	( "+ 
				"\n 		select dh.NPP_FK,dhsp.SANPHAM_FK ,sum(dhsp.soluong) soluong , sum( round( dhsp.soluong * dhsp.GIAMUA * (1 + dhsp.THUEVAT/100.0) ,0)) AVAT, sum( round( dhsp.soluong * dhsp.GIAMUA * (1 + dhsp.THUEVAT/100.0) ,0)) DS_TRU_TRA_LAI   "+ 
				"\n 		from donhang dh  "+ 
				"\n 		inner join donhang_sanpham dhsp on dh.PK_SEQ = dhsp.donhang_fk  "+ 
				"\n 		where  dh.TRANGTHAI in (1, 5) and dh.ngaynhap between '"+ tungay +"' and '"+ denngay +"' " + searchstr + 
				"\n			 and not exists ( select 1 from Erp_HangTraLaiNpp x where x.DONHANG_FK = dh.pk_seq and x.trangthai  in (1, 5) )	" +
				"\n 		group by dh.NPP_FK,dhsp.SANPHAM_FK "+ 
				"\n 		union all "+ 
				"\n 		select dh.NPP_FK,dhsp.SANPHAM_FK ,sum( (-1)*soluong),0  , sum( round( (-1)*soluong * DonGia,0)) DS_TRU_TRA_LAI "+ 
				"\n 		from Erp_HangTraLaiNpp dh  "+ 
				"\n 		inner join Erp_HangTraLaiNpp_SanPham dhsp on dh.PK_SEQ = dhsp.HangTraLai_fk  "+ 
				"\n 		where dh.TRANGTHAI in (1, 5) and dh.DONHANG_FK is null and dh.ngaytra between '"+ tungay +"' and '"+ denngay +"' " + searchstr + 
				"\n 		group by dh.NPP_FK,dhsp.SANPHAM_FK "+ 
				
				"\n 	) ds "+ 
				"\n 	group by ds.SANPHAM_FK,ds.NPP_FK "+ 
				"\n ) "+ 

				subquery_Top +
				"\n select STT, NPP [CHI NH??NH ?????I T??C], mavt [M?? V???T T??], tenvt [T??N V???T T??], donvi [??VT], soluong [S??? L?????NG], AVAT [DOANH S???], DS_TRU_TRA_LAI [DS TR??? TR??? L???I]  " +
				"\n from " +
				"\n ( "+
				"\n 	select ROW_NUMBER() OVER( ORDER BY sp.ma ASC) as stt ,npp.ten [NPP],sp.ma [mavt], sp.TEN [tenvt], dv.DONVI , ds.soluong,  ds.AVAT , ds.DS_TRU_TRA_LAI   "+ 
				"\n 	from ds  "+ 
				"\n 	inner join SANPHAM sp on sp.PK_SEQ =ds.SANPHAM_FK "+ 
				"\n 	inner join DONVIDOLUONG dv on dv.PK_SEQ = sp.DVDL_FK "+
				"\n 	inner join NHAPHANPHOI npp on npp.pk_seq = ds.npp_fk  "+
				"\n 	where 1=1 "+
				"\n ) as t where 1=1 "+ subquery_Bot;
		}
		
		System.out.println("sql doanh thu sp = "+ sql);
		return sql;
	}
	public static String queryDoanhThuTheoSP(HttpServletRequest request, Utility util, int action,int RECORD_SIZE,int INITIAL )
	{
 
		//  ACTION :
		//	0 xuat excel 
		//  1 query detal
		//	2 query count	
		String userId = util.antiSQLInspection(request.getParameter("userId"));
		String tungay = util.antiSQLInspection(request.getParameter("tungay"));
		String denngay = util.antiSQLInspection(request.getParameter("denngay"));
		String vungId = util.antiSQLInspection(request.getParameter("vungId"));
		String kenhId = util.antiSQLInspection(request.getParameter("kenhId"));
		String spId = util.antiSQLInspection(request.getParameter("spId"));
		String nppId = util.antiSQLInspection(request.getParameter("nppId"));
		String nhomId = util.antiSQLInspection(request.getParameter("nhomId"));
		String searchstr = "";
		String subquery_Top = " ";
		String subquery_Bot = " ";
		System.out.println("kenhId : "+ kenhId);
		if(userId.length() > 0) { searchstr += " and dh.npp_fk in " + util.quyen_npp(userId); }
		if(nppId.length() > 0) { searchstr += " and dh.npp_fk = '"+ nppId +"' "; }
		if(kenhId.length() > 0) { searchstr += " and dh.kbh_fk = '"+ kenhId +"' "; }
		if(nhomId.length() > 0) { searchstr += " and exists ( select 1 from NhomHang_SanPham nsp where nsp.NhomHang_FK = '"+ nhomId +"' and nsp.SanPham_FK = dhsp.sanpham_fk ) "; }
		if(spId.length() > 0) { searchstr += " and dhsp.sanpham_fk in ( "+ spId +" ) "; }
		if(vungId.length() > 0) 
		{   
			searchstr += " and exists ( select 1 from nhaphanphoi npp where npp.pk_seq = dh.npp_fk " +
						 " and exists ( select 1 from khuvuc kv where kv.pk_seq = npp.khuvuc_fk " +
						 " and exists ( select 1 from vung v where v.pk_seq = '"+ vungId +"' and v.pk_seq = kv.vung_fk ) ) ) ";
		}
		
		searchstr += " and dh.DDKD_FK in ("+Utility.PhanQuyenDDKD(userId)+") ";
		searchstr += " and dh.NPP_FK in ("+Utility.Quyen_npp(userId)+") ";
		
		if(action == QUERY_DETAIL_ACTION)
		{
			subquery_Top = 
				"\n select top("+ RECORD_SIZE +") *  from "+
				"\n (  ";
			subquery_Bot = "\n ) list where stt > '"+ INITIAL +"' ";
		}
		else if( action == QUERY_COUNT_ACTION)
		{
			subquery_Top = "\n select count(*) num from ( ";
			subquery_Bot  = "\n ) as t ";
		}
		else if( action == QUERY_SUM_DATA_ACTION)
		{
			subquery_Top = "\n select sum([DOANH S???])[DOANH S???],sum([DS TR??? TR??? L???I])[DS TR??? TR??? L???I] from ( ";
			subquery_Bot  = "\n ) as t ";
		}
		String sql = 
				"\n with ds as "+ 
				"\n ( "+ 
				"\n 	select ds.SANPHAM_FK,  sum(soluong) soluong, sum(DS_TRU_TRA_LAI)DS_TRU_TRA_LAI, sum(AVAT)AVAT "+ 
				"\n 	from "+ 
				"\n 	( "+ 
				"\n 		select dh.NPP_FK,dh.KHACHHANG_FK,dhsp.SANPHAM_FK,sum(dhsp.soluong) soluong , sum( round( dhsp.soluong * dhsp.GIAMUA * (1 + dhsp.THUEVAT/100.0) ,0)) AVAT, sum( round( dhsp.soluong * dhsp.GIAMUA * (1 + dhsp.THUEVAT/100.0) ,0)) DS_TRU_TRA_LAI  "+ 
				"\n 		from donhang dh  "+ 
				"\n 		inner join donhang_sanpham dhsp on dh.PK_SEQ = dhsp.donhang_fk  "+ 
				"\n 		where  dh.TRANGTHAI in (1, 5) and dh.ngaynhap between '"+ tungay +"' and '"+ denngay +"' " + searchstr + 
				"\n			 	and not exists ( select 1 from Erp_HangTraLaiNpp x where x.DONHANG_FK = dh.pk_seq and x.trangthai  in (1, 5) )	" +
				"\n 		group by dh.NPP_FK,dh.KHACHHANG_FK,dhsp.SANPHAM_FK "+ 
				"\n 		union all "+ 
				"\n 		select dh.NPP_FK,dh.KHACHHANG_FK,dhsp.SANPHAM_FK ,sum( (-1)*soluong),0  , sum( round( (-1)*soluong * DonGia,0)) DS_TRU_TRA_LAI "+ 
				"\n 		from Erp_HangTraLaiNpp dh  "+ 
				"\n 		inner join Erp_HangTraLaiNpp_SanPham dhsp on dh.PK_SEQ = dhsp.HangTraLai_fk  "+ 
				"\n 		where dh.TRANGTHAI in (1, 5) and dh.DONHANG_FK is null and dh.ngaytra between '"+ tungay +"' and '"+ denngay +"' " + searchstr + 
				"\n 		group by dh.NPP_FK,dh.KHACHHANG_FK,dhsp.SANPHAM_FK " +				
				"\n 	) ds "+ 
				"\n 	group by ds.SANPHAM_FK "+ 
				"\n ) "+ 

				subquery_Top +
				"\n select STT , mavt [M?? V???T T???], tenvt [T??N V???T T??], donvi [??VT], soluong [S??? L?????NG], AVAT [Doanh s???], DS_TRU_TRA_LAI [DS TR??? TR??? L???I] " +//, giamua [????n gi??]
				"\n from " +
				"\n ( "+
				"\n 	select ROW_NUMBER() OVER( ORDER BY sp.ma ASC) as stt, "+
				"\n 	sp.ma [mavt], sp.TEN [tenvt], dv.DONVI, ds.soluong,  ds.AVAT , ds.DS_TRU_TRA_LAI "+ 
				"\n 	from ds  "+ 
				"\n 	inner join SANPHAM sp on sp.PK_SEQ =ds.SANPHAM_FK "+ 
				"\n 	inner join DONVIDOLUONG dv on dv.PK_SEQ = sp.DVDL_FK "+
				"\n 	where 1=1 "+
				"\n ) as t where 1=1 "+ subquery_Bot;
		System.out.println("sql doanh thu sp = "+ sql);
		return sql;
	}
}
