<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="ISO-8859-1"%>
<%@page import="java.util.Iterator"%>
<%@page import="java.util.List"%>
<%@page import="java.util.ArrayList"%>
<%@ page  import = "java.sql.ResultSet" %>
<%@ page  import = "geso.dms.center.db.sql.dbutils" %>

<%

	String ngaydonhang = "";
	if(session.getAttribute("ngaydonhang") != null )
		ngaydonhang = (String) session.getAttribute("ngaydonhang");	

	String dvkdId = "";
	if(session.getAttribute("dvkdId") != null )
		dvkdId = (String) session.getAttribute("dvkdId");
	
	String kbhId = "";
	if(session.getAttribute("kbhId") != null )
		kbhId = (String) session.getAttribute("kbhId");
	
	String nppId = "";
	if(session.getAttribute("nppId") != null )
		nppId = (String) session.getAttribute("nppId");
	
	String doitacId = "-1";
	if(session.getAttribute("doitacId") != null )
		doitacId = (String) session.getAttribute("doitacId");

	String khonhanid = "-1";
	if(session.getAttribute("khonhanid") != null )
		khonhanid = (String) session.getAttribute("khonhanid");

	
	if( dvkdId.trim().length() > 0 && kbhId.trim().length() > 0 && nppId.trim().length() > 0 )
	{
		dbutils db = new dbutils();
		try
		{		
		
									String command="";		
							
									if(kbhId.equals("100052") || kbhId.equals("100025"))
									{
									command = "select a.ma, a.ten, b.donvi, ISNULL(trongluong, 0) as trongluong, ISNULL(thetich, 0) as thetich, " +
											"	cast (  isnull( ( select soluong2 / soluong1 from QUYCACH where SANPHAM_FK = a.PK_SEQ and DVDL1_FK = a.DVDL_FK	and DVDL2_FK = '100018' ), 0 ) as numeric(18, 2) ) as qc	" +
											" 	, [dbo].[GiaMuanppSanpham](a.DVKD_FK,"+kbhId+","+nppId+",a.pk_seq,"+doitacId+",'"+ngaydonhang+"' ) as giamua "+
											"	, isnull(a.PT_VAT,0) thuexuat  " +
											"from SANPHAM a  inner join ufn_sanpham("+khonhanid+",null,null) kh on kh.sanpham_fk=a.pk_seq  inner join DONVIDOLUONG b on a.dvdl_fk = b.pk_seq " +
											
											"where a.pk_seq > 0 and a.DVKD_FK = '" + dvkdId + "'  ";	
									}
									if(kbhId.equals("100053"))
									{
										command = "select a.ma, a.ten, b.donvi, ISNULL(trongluong, 0) as trongluong, ISNULL(thetich, 0) as thetich, " +
												"	cast (  isnull( ( select soluong2 / soluong1 from QUYCACH where SANPHAM_FK = a.PK_SEQ and DVDL1_FK = a.DVDL_FK	and DVDL2_FK = '100018' ), 0 ) as numeric(18, 2) ) as qc,	" +
												"(select bb.GIABAN * (1-bb.chietkhau/100.0) from BANGGIAB2B aa  "+
												"	inner join BANGGIAB2B_SANPHAM bb on aa.PK_SEQ=bb.BANGGIAB2B_FK  "+ 
												"	where aa.TRANGTHAI=1 and bb.SANPHAM_FK=a.PK_SEQ and aa.kenh_fk='"+kbhId+"' and aa.dvkd_fk='"+dvkdId+"' and aa.trangthai=1) as giamua, isnull(a.PT_VAT,0) thuexuat  " +
												"from SANPHAM a  inner join ufn_sanpham("+khonhanid+",null,null) kh on kh.sanpham_fk=a.pk_seq  inner join DONVIDOLUONG b on a.dvdl_fk = b.pk_seq " +
												"where a.pk_seq > 0 and a.DVKD_FK = '" + dvkdId + "'  ";	
							
									}
									
			System.out.println("Lay san pham_command : " + command);
			
			response.setHeader("Content-Type", "text/html");
			String query = (String)request.getParameter("letters");
			
			ResultSet sp = db.get(command);
			int j = 0;
			if(sp != null)
			{
				while(sp.next())
				{
					//double quycach = rs.get
					if(sp.getString("ma").toUpperCase().contains(query.toUpperCase()) || sp.getString("ten").toUpperCase().contains(query.toUpperCase()) )
					{
						String tensp = sp.getString("ten");
						//out.print("###" + sp.getString("ma") + " - " + tensp + " [" + sp.getString("donvi") + "] [" + sp.getString("trongluong") + "] [" + sp.getString("thetich") + "] [" + sp.getString("qc") + "] [" + sp.getString("giamua") + "] [" + sp.getString("thuexuat") + "]|"); //luu y: bat buoc phai co dau phan cach '|' o cuoi moi dong'
						out.print("###" + sp.getString("ma") + " - " + tensp + " [" + sp.getString("donvi") + "] [" + sp.getString("giamua") + "] [" + sp.getString("thuexuat") + "]|"); //luu y: bat buoc phai co dau phan cach '|' o cuoi moi dong'
					}	
				}	
				sp.close();
			}
			
			db.shutDown();
		}
		catch(Exception ex){ System.out.println("Xay ra exception roi ban..."); }
	}
%>

