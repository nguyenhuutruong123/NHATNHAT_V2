<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@page import="java.util.Iterator"%>
<%@page import="java.util.List"%>
<%@page import="java.util.ArrayList"%>
<%@ page  import = "java.sql.ResultSet" %>
<%@ page  import = "geso.dms.center.util.*" %>
<%@ page import = "java.net.URLDecoder" %>
<%@ page  import = "geso.dms.center.db.sql.dbutils" %>

<%
	String dvkdId = "100001";
	if(session.getAttribute("dvkdId") != null )
		dvkdId = (String) session.getAttribute("dvkdId");
	
	String kbhId = "-1";
	if(session.getAttribute("kbhId") != null )
		kbhId = (String) session.getAttribute("kbhId");
	
	String nppId = "-1";
	if(session.getAttribute("nppId") != null )
		nppId = (String) session.getAttribute("nppId");

	String ngaydh = "-1";
	if(session.getAttribute("ngaydh") != null )
		ngaydh = (String) session.getAttribute("ngaydh");
	
	//System.out.println("--LOC SP..." + dvkdId + "  -- KBH ID: " + kbhId + "  -- Đối tác ID: " + nppId );
	if( dvkdId.trim().length() > 0 && kbhId.trim().length() > 0 && nppId.trim().length() > 0 )
	{
		dbutils db = new dbutils();
		try
		{		
			/* String command = " select a.ma, a.ten, b.donvi, " + 
							 //"    isnull( ( select SOLUONG2 / SOLUONG1 from QUYCACH where SANPHAM_FK = a.pk_seq and DVDL1_FK = a.DVDL_FK ), 0 ) as quydoi, " +
							 " 	  isnull( ( select GIAMUA_SAUCK from BGMUANPP_SANPHAM " +
							 "			  where SANPHAM_FK = a.pk_seq " +
							 "					and BGMUANPP_FK in ( select top(1) PK_SEQ from BANGGIAMUANPP bg inner join BANGGIAMUANPP_NPP bg_npp on bg.PK_SEQ = bg_npp.BANGGIAMUANPP_FK where bg.TRANGTHAI = '1' and bg_npp.NPP_FK = '" + nppId + "' and bg.DVKD_FK = '" + dvkdId + "' and bg.KENH_FK = '" + kbhId + "' order by bg.TUNGAY desc ) ), 0) as giamua " + 
							 " from SANPHAM a inner join DONVIDOLUONG b on a.dvdl_fk = b.pk_seq where a.pk_seq > 0 and a.DVKD_FK = '" + dvkdId  + "' "; */
			 
			 /* String dvdl_fk = "";
			 if(kbhId.equals("100052"))
				 dvdl_fk = " isnull(c.donvi, b.donvi) as donvi ";
			 else
				 dvdl_fk = " b.donvi "; */
						
			 //BAN DAU LAY GIA CHUAN SAU DO CHON LAI GIA THI CAP NHAT LAI GIA THEO DON VI (BANG GIA BEN NAY KHONG CHIA THEO KENH)
			 String command = "select count(*) as sl from nhaphanphoi where loainpp=4 and TRUCTHUOC_FK <>1 and pk_seq="+nppId;
			//System.out.print("command "+command);
				 int sl=0;
				ResultSet rs1=db.get(command);
				if(rs1.next())
					sl=rs1.getInt("sl");
				rs1.close();
				if(sl>0 )
				{
				  command =  		"\n select a.ma, a.ten, b.donvi, ISNULL(trongluong, 0) as trongluong, ISNULL(thetich, 0) as thetich, " +
									"\n	cast (  isnull( ( select soluong2 / soluong1 from QUYCACH where SANPHAM_FK = a.PK_SEQ and DVDL1_FK = a.DVDL_FK	and DVDL2_FK = '100018' ), 0 ) as numeric(18, 2) ) as qc,	" +
									"\n 	 dbo.[GiaDoitacSanpham](100001,"+kbhId+","+nppId+",a.pk_seq,0,'"+ ngaydh +"') as giamua, isnull(a.PT_VAT,0) thuexuat  " +
									"\n from SANPHAM a left join DONVIDOLUONG b on a.dvdl_fk = b.pk_seq " +
				
									"\n where a.pk_seq > 0 and a.DVKD_FK = '" + dvkdId + "' and a.trangthai=1  "; 
				}
				else
				{
				 command =  "select a.ma, a.ten, b.donvi, ISNULL(trongluong, 0) as trongluong, ISNULL(thetich, 0) as thetich, " +
						"	cast (  isnull( ( select soluong2 / soluong1 from QUYCACH where SANPHAM_FK = a.PK_SEQ and DVDL1_FK = a.DVDL_FK	and DVDL2_FK = '100018' ), 0 ) as numeric(18, 2) ) as qc,	" +
						" 	[dbo].[GiaMuanppSanpham]("+ dvkdId +"," + kbhId +  "," + nppId + ",a.pk_seq,0,'"+ ngaydh +"' ) as giamua,isnull(a.PT_VAT,0) thuexuat  " +
						"from SANPHAM a left join DONVIDOLUONG b on a.dvdl_fk = b.pk_seq " +
						
						"where a.pk_seq > 0 and a.DVKD_FK = '" + dvkdId + "' and a.trangthai=1  ";	 	 
				}	 
			//System.out.println("Lay san pham: " + command);
			
			response.setHeader("Content-Type", "text/html");
			String query = (String)request.getParameter("letters");
		 	Utility Ult = new Utility();
		 	query = URLDecoder.decode(query, "UTF-8").replace("+", " ");
			query = Ult.replaceAEIOU(query);
			ResultSet sp = db.get(command);
			int j = 0;
			if(sp != null)
			{
				while(sp.next())
				{
					
					//System.out.println(Ult.replaceAEIOU(sp.getString("ten")).toUpperCase() +"và "+query.toUpperCase());
					if(sp.getString("ma").toUpperCase().contains(query.toUpperCase()) || Ult.replaceAEIOU(sp.getString("ten")).toUpperCase().contains(query.toUpperCase()) )
					{
						String tensp = sp.getString("ten");
						
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

