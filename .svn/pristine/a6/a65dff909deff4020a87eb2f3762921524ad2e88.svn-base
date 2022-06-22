<%@page import="java.text.DecimalFormat"%>
<%@page import="java.text.NumberFormat"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="ISO-8859-1"%>
<%@page import="java.util.Iterator"%>
<%@page import="java.util.List"%>
<%@page import="java.util.ArrayList"%>
<%@ page  import = "java.sql.ResultSet" %>
<%@ page  import = "geso.dms.center.db.sql.dbutils" %>

<%@ page  import = "geso.dms.center.util.*" %>
<%@ page import = "java.net.URLDecoder" %>

<%@ page import ="javax.xml.transform.dom.DOMResult" %>
<%@ page import = "javax.xml.soap.*" %>
<%@ page import = "javax.xml.transform.*" %>
<%@ page import = "org.w3c.dom.*" %>


<% String nppId = (String) session.getAttribute("nppId"); %>
<% String kbhId = (String) session.getAttribute("kbhId"); %>
<% String khoId = (String) session.getAttribute("khoId"); %>
<% String dvkdId = (String) session.getAttribute("dvkdId"); %>
<%
%>

<%
	String ngaydonhang = (String)session.getAttribute("ngaydonhang"); 
	if(ngaydonhang == null)
		ngaydonhang = "";
	
	if(ngaydonhang.trim().length() > 0)
	{
		dbutils db = new dbutils();
		try
		{

			String command =  "select a.ma, a.ten, b.donvi, d.AVAILABLE  as hienhuu " +
						"from SANPHAM a inner join DONVIDOLUONG b on a.dvdl_fk = b.pk_seq   " +
						"	inner join NHAPP_KHO d on a.PK_SEQ = d.SANPHAM_FK " +
						"where   a.pk_seq > 0 and a.DVKD_FK = '"+dvkdId+"' and d.NPP_FK = '" + nppId + "' and d.kho_fk = '" + khoId + "' and d.KBH_FK ="+kbhId+" ";
			
			System.out.println("Lay San pham: " + command);
			
			
			response.setHeader("Content-Type", "text/html");
			request.setCharacterEncoding("UTF-8");
			
			 
		   	String query = (String)request.getQueryString(); 
		   	
		   	query = new String(query.substring(query.indexOf("&letters=") + 9, query.length()).getBytes("UTF-8"), "UTF-8");
		   	query = URLDecoder.decode(query, "UTF-8").replace("+", " ");
		   	
		   	Utility Ult = new Utility();
		   	query = Ult.replaceAEIOU(query);
			
			ResultSet sp = db.get(command);
			int j = 0;
			if(sp != null)
			{
				NumberFormat format = new DecimalFormat("#,###,###,##0.00");
				while(sp.next())
				{
					double hienhuu = sp.getDouble("hienhuu");
					if(hienhuu <= 0)
						hienhuu = 0;
					
					String MASP = sp.getString("ma");
					String TENSP = sp.getString("ten");
					
					String masp = Ult.replaceAEIOU(sp.getString("ma"));
					String tensp = Ult.replaceAEIOU(sp.getString("ten"));
					String donvi = Ult.replaceAEIOU(sp.getString("donvi"));
				
					

					if(masp.toUpperCase().startsWith(query.toUpperCase()) ||masp.toUpperCase().indexOf(query.toUpperCase()) >= 0 
							|| tensp.toUpperCase().indexOf(query.toUpperCase()) >= 0 || donvi.toUpperCase().indexOf(query.toUpperCase()) >= 0 )
					{
						if(TENSP.length() > 50)
							TENSP = TENSP.substring(0, 50);
				
						out.print("###" + sp.getString("ma") + " - " + TENSP +" [" + sp.getString("donvi") + "] [" + format.format(hienhuu) +  "]|"); //luu y: bat buoc phai co dau phan cach '|' o cuoi moi dong'
					}
				
					
				}	
			}
			sp.close();
			db.shutDown();
			db=null;
			nppId=null;
			khoId=null;
		
		}	
		catch(Exception ex)
		{
			System.out.println("__EXCEPTION LAY SP: " + ex.getMessage());
			if(db!=null){		
				db.shutDown();
				db=null;
			}
		}
	
	}
%>

