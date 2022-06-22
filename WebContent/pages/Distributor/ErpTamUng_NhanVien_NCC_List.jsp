<%@page import="geso.dms.center.db.sql.dbutils"%>
<%@page import="java.sql.SQLException"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@page import="java.util.Iterator"%>
<%@page import="java.util.List"%>
<%@page import="java.util.ArrayList"%>
<%@ page import = "java.sql.ResultSet" %>
<%@ page import = "geso.dms.center.util.*" %>
<%@ page import = "java.net.URLDecoder" %>

<% String doituong = (String) session.getAttribute("doituong"); %>

<%
	try
	{  
		dbutils db = new dbutils();	
		String khId = "";
		String khTen = "";
		String type="";
		
		String command ="";
		if(doituong.equals("1"))
			command="SELECT PK_SEQ,MA+','+TEN AS TEN FROM ERP_NHANVIEN WHERE TRANGTHAI=1 ";
		else 
			command="SELECT PK_SEQ,MA+','+TEN AS TEN  FROM ERP_NHACUNGCAP WHERE TRANGTHAI=1 ";
		
		request.setCharacterEncoding("UTF-8");
	   
	   	String query = (String)request.getQueryString(); 
	   	query = new String(query.substring(query.indexOf("q=") + 2, query.indexOf("&limit=")).getBytes("UTF-8"), "UTF-8");
	   	query = URLDecoder.decode(query, "UTF-8").replace("+", " ");
		
		response.setHeader("Content-Type", "text/html; charset=UTF-8");
		
	 	System.out.println("Lay duoc nha cung cap: " + command + "\n");
	 	
	 	ResultSet ncc = db.get(command);
	 	
		if(ncc != null)
		{
			while(ncc.next())
			{   
				khId = ncc.getString("pk_seq");
				khTen = ncc.getString("Ten");
				if(khTen.toUpperCase().startsWith(query.toUpperCase()) || khTen.toUpperCase().indexOf(query.toUpperCase()) >= 0||khId.indexOf(query)>=0||khId.startsWith(query)) 
				{
					out.println(khId + " -- " + khTen+  "\n");
				}
			}
			ncc.close();
		}
			
		db.shutDown();
	}
	catch(Exception e)
	{
		System.out.println("1.Exception: " + e.getMessage());
	}
		
%>