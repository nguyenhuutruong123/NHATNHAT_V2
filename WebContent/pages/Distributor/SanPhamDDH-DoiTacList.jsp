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
	
	String doitacId = "-1";
	if(session.getAttribute("doitacId") != null )
		doitacId = (String) session.getAttribute("doitacId");
	
	String khonhapId = "-1";
	if(session.getAttribute("khoId") != null )
		khonhapId = (String) session.getAttribute("khoId");
	
	String ngaydh = "-1";
	if(session.getAttribute("ngaydh") != null )
		ngaydh = (String) session.getAttribute("ngaydh");
	
	System.out.println("--LOC SP..." + dvkdId + "  -- KBH ID: " + kbhId + "  -- Đối tác ID: " + nppId );
	if( dvkdId.trim().length() > 0 && kbhId.trim().length() > 0 && nppId.trim().length() > 0 )
	{
		String LINKSERVER_DB = getServletContext().getInitParameter("LINKSERVER_DB");
		dbutils db = new dbutils();
		String sql = " select isnull(quanlykho,0) as quanlykho from NHAPHANPHOI where pk_seq = '" + nppId + "' ";
		ResultSet rs = db.get(sql);
		String quanlykho = "";
		if(rs != null)
		{
			try 
			{
				if(rs.next())
				{
					quanlykho = rs.getString("quanlykho");
				}
				rs.close();
			} 
			catch (Exception e) { e.printStackTrace(); }
		}
		
		try
		{	
			
			//BÁN CHO ĐỐI TÁC DÙNG BẢNG GIÁ RIÊNG
			
			 //BAN DAU LAY GIA CHUAN SAU DO CHON LAI GIA THI CAP NHAT LAI GIA THEO DON VI (BANG GIA BEN NAY KHONG CHIA THEO KENH)
			 String command = "";
			 if(quanlykho.trim().equals("0"))
			 {
				 command = 
					"\n IF OBJECT_ID('tempdb.dbo.#kho') IS NOT NULL DROP TABLE #kho select * into #kho from "+
				 	"\n ( "+
					"\n 	 SELECT SP_E.MA SPMA, NPP_E.MA NPPMA, SUM(AVAILABLE) AVAILABLE "+
					"\n 	 FROM "+ LINKSERVER_DB +".[dbo].[ERP_KHOTT_SP_CHITIET] K   "+
					"\n 	 INNER JOIN "+ LINKSERVER_DB +".[dbo].NHAPHANPHOI NPP_E ON NPP_E.PK_SEQ = K.NPP_FK   "+
					"\n 	 INNER JOIN "+ LINKSERVER_DB +".[dbo].ERP_SANPHAM SP_E ON SP_E.PK_SEQ = K.SANPHAM_FK    "+
					"\n 	 WHERE K.NGAYNHAPKHO <= '"+ ngaydh +"' AND NPP_E.MA = ( SELECT DISTINCT MA FROM NHAPHANPHOI WHERE PK_SEQ = '"+nppId+"' ) "+
					"\n 	 GROUP BY SP_E.MA, NPP_E.MA "+
					"\n ) AS K "+
					
					" select "+
					"\n K.AVAILABLE AS SOLUONGTON, "+
					"\n a.ma, a.ten, b.donvi, ISNULL(trongluong, 0) as trongluong, ISNULL(thetich, 0) as thetich, " +
					"\n cast ( isnull( ( select soluong2 / soluong1 from QUYCACH where SANPHAM_FK = a.PK_SEQ and DVDL1_FK = a.DVDL_FK	and DVDL2_FK = '100018' ), 0 ) as numeric(18, 2) ) as qc,	" +
					"\n dbo.[GiaDoitacSanpham](100001,"+kbhId+","+doitacId+",a.pk_seq,0,'"+ ngaydh +"') as giamua,  isnull(a.PT_VAT,0) thuexuat  " +
					"\n from SANPHAM a left join DONVIDOLUONG b on a.dvdl_fk = b.pk_seq " +
					"\n  CROSS APPLY ( SELECT * FROM #KHO WHERE SPMA = A.MA AND AVAILABLE > 0 ) AS K "+
					"\n where a.pk_seq > 0 and a.DVKD_FK = '" + dvkdId + "'  ";
			 }
			 else
			 {
				 command =  
					" select (select kho.available from nhapp_kho kho where kho.sanpham_fk=a.pk_seq and kho.KHO_FK= "+khonhapId+" and NPP_FK='"+nppId+"' and kho.KBH_FK="+kbhId+" )as soluongton, "+
					" a.ma, a.ten, b.donvi, ISNULL(trongluong, 0) as trongluong, ISNULL(thetich, 0) as thetich, " +
					" cast ( isnull( ( select soluong2 / soluong1 from QUYCACH where SANPHAM_FK = a.PK_SEQ and DVDL1_FK = a.DVDL_FK	and DVDL2_FK = '100018' ), 0 ) as numeric(18, 2) ) as qc,	" +
					" dbo.[GiaDoitacSanpham](100001,"+kbhId+","+doitacId+",a.pk_seq,0,'"+ ngaydh +"') as giamua,  isnull(a.PT_VAT,0) thuexuat  " +
					" from SANPHAM a left join DONVIDOLUONG b on a.dvdl_fk = b.pk_seq " +
					" where a.pk_seq > 0 and a.DVKD_FK = '" + dvkdId + "'  ";
			 }
							 
			System.out.println("command Lay san pham________________: " + command);
			
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
					//double quycach = rs.get
					if(sp.getString("ma").toUpperCase().contains(query.toUpperCase()) || Ult.replaceAEIOU(sp.getString("ten")).toUpperCase().contains(query.toUpperCase())  )
					{
						String tensp = sp.getString("ten");
						//out.print("###" + sp.getString("ma") + " - " + tensp + " [" + sp.getString("donvi") + "] [" + sp.getString("trongluong") + "] [" + sp.getString("thetich") + "] [" + sp.getString("qc") + "] [" + sp.getString("giamua") + "] [" + sp.getString("thuexuat") + "]|"); //luu y: bat buoc phai co dau phan cach '|' o cuoi moi dong'
						out.print("###" + sp.getString("ma") + " - " + tensp + " [" + sp.getString("donvi") + "] [" + sp.getString("giamua") + "] [" + sp.getString("thuexuat") + "] [" + sp.getFloat("soluongton") + "]|"); //luu y: bat buoc phai co dau phan cach '|' o cuoi moi dong'
					}	
					
				}	
				sp.close();
			}
			
			db.shutDown();
		}
		catch(Exception ex){ System.out.println("Xay ra exception roi ban..."); }
	}
%>

