<%@page import="java.text.SimpleDateFormat"%>
<%@page import="java.text.DateFormat"%>
<%@page import="java.util.Date"%>

<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="ISO-8859-1"%>
<%@page import="java.util.Iterator"%>
<%@page import="java.util.List"%>
<%@page import="java.util.ArrayList"%>
<%@ page  import = "java.sql.ResultSet" %>
<%@ page  import = "geso.dms.center.db.sql.dbutils" %>
<%@ page  import = "geso.dms.center.util.*" %>
<%@ page import = "java.net.URLDecoder" %>

<%
	String ngayyeucau = "";
	
	DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
	Date date = new Date();
	ngayyeucau = dateFormat.format(date);

	String query = (String)request.getQueryString(); 
	
	
	query = new String(query.substring(query.indexOf("&letters=") + 9, query.length()).getBytes("UTF-8"), "UTF-8");
   	query = URLDecoder.decode(query, "UTF-8").replace("+", " ");
   	
   	Utility Ult = new Utility();
   	query = Ult.replaceAEIOU(query);
	
	dbutils db = new dbutils();
	try
	{	
		ngayyeucau=(String) session.getAttribute("ngayxuatchuyen");
		
		String khoXuatId = "-1";
		if(session.getAttribute("khoXuatId") != null )
			khoXuatId = (String) session.getAttribute("khoXuatId");

		String command = " select top(50) a.ma, a.ten, b.donvi, isnull(a.hansudung, 0) as hansudung, isnull(a.PT_VAT,0) as thuexuat, ISNULL( ( select sum(available) from ERP_KHOTT_SANPHAM where KHOTT_FK = '"+ khoXuatId +"' and sanpham_fk = a.pk_seq ), 0 ) as avai, " +
						 " 0 gia" + // isnull((select [dbo].[GiaCkBanLeNppSanPham](1,null,a.pk_seq,'"+ngayyeucau+"')), 0)
						 " from SANPHAM a "+ 
						  //" inner join ufn_sanpham("+khoXuatId+",null,null) kh on kh.sanpham_fk=a.pk_seq  " 
						  " inner join DONVIDOLUONG b on a.dvdl_fk = b.pk_seq " + 
						 " where a.timkiem like N'%" + query + "%' ";
		
		System.out.println("Lay san pham: " + command);
		
		response.setHeader("Content-Type", "text/html");
		//String query = (String)request.getParameter("letters");
		
		ResultSet sp = db.get(command);
		int j = 0;
		if(sp != null)
		{
			while(sp.next())
			{
				System.out.println(Ult.replaceAEIOU(sp.getString("ten")).toUpperCase() +"và "+query.toUpperCase());
				if(sp.getString("ma").toUpperCase().contains(query.toUpperCase()) || Ult.replaceAEIOU(sp.getString("ten")).toUpperCase().contains(query.toUpperCase()) )
				{
					String tensp = sp.getString("ten");
					out.print("###" + sp.getString("ma") + " - " + tensp + " [" + sp.getString("donvi") + "] [" + sp.getString("thuexuat") + "] [" + sp.getString("avai") + "] [" + sp.getString("gia") + "]|"); //luu y: bat buoc phai co dau phan cach '|' o cuoi moi dong'
					System.out.print("###" + sp.getString("ma") + " - " + tensp + " [" + sp.getString("donvi") + "] [" + sp.getString("hansudung") + "] [" + sp.getString("avai") + "] [" + sp.getString("gia") + "]|");
				}	
			}	
			sp.close();
		}
		
		db.shutDown();
	}
	catch(Exception ex){ System.out.println("Xay ra exception roi ban..."); }
%>

