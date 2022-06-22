<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@page import="java.util.Iterator"%>
<%@page import="java.util.List"%>
<%@page import="java.util.ArrayList"%>
<%@ page  import = "java.sql.ResultSet" %>
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
	
	String khonhapId = "-1";
	if(session.getAttribute("khoId") != null )
		khonhapId = (String) session.getAttribute("khoId");
	System.out.println("kho nhap id:"+khonhapId);
	if(khonhapId.equals("-1"))
		return;
	
	String khId = "null";
	if(session.getAttribute("khId") != null )
		khId = (String)session.getAttribute("khId");
	System.out.println("id kho ban hang: "+khonhapId +",khId = "+ khId);
	
	
	String ngaydonhang = "";
	if(session.getAttribute("ngaydonhang") != null )
		ngaydonhang = (String) session.getAttribute("ngaydonhang");
	System.out.println("--LOC SP111..." + dvkdId + "  -- KBH ID: " + kbhId + "  -- Đối tác ID: " + nppId +"-----ngay dh"+ngaydonhang );
	String LINKSERVER_DB = getServletContext().getInitParameter("LINKSERVER_DB");
	
	if( dvkdId.trim().length() > 0 && kbhId.trim().length() > 0 && nppId.trim().length() > 0 )
	{
		dbutils db = new dbutils();
		String sql = " select tructhuoc_fk, loaiNPP,dungchungkenh, isnull(quanlykho,0) as quanlykho from NHAPHANPHOI where pk_seq = '" + nppId + "' ";
		ResultSet rs = db.get(sql);
		String tructhuocId = "";
		String loaiNPP = "";
		String dungchungkenh="", quanlykho = "";
		if(rs != null)
		{
			try 
			{
				if(rs.next())
				{
					loaiNPP = rs.getString("loaiNPP");
					tructhuocId = rs.getString("tructhuoc_fk");
					dungchungkenh=rs.getString("dungchungkenh");
					quanlykho = rs.getString("quanlykho");
				}
				rs.close();
				
				if(dungchungkenh.equals("1"))
				{
					kbhId="100025";
				}
			} 
			catch (Exception e) { }
		}
		
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
			 String command = "";
				 
				 
			 if(quanlykho.trim().equals("0"))
			 {
				 command += "\n IF OBJECT_ID('tempdb.dbo.#kho') IS NOT NULL DROP TABLE #kho select * into #kho from "+
						 	"\n ( "+
							"\n 	 SELECT SP_E.MA SPMA, NPP_E.MA NPPMA, SUM(AVAILABLE) AVAILABLE "+
							"\n 	 FROM "+ LINKSERVER_DB +".[dbo].[ERP_KHOTT_SP_CHITIET] K   "+
							"\n 	 INNER JOIN "+ LINKSERVER_DB +".[dbo].NHAPHANPHOI NPP_E ON NPP_E.PK_SEQ = K.NPP_FK   "+
							"\n 	 INNER JOIN "+ LINKSERVER_DB +".[dbo].ERP_SANPHAM SP_E ON SP_E.PK_SEQ = K.SANPHAM_FK    "+
							"\n 	 WHERE K.NGAYNHAPKHO <= '"+ngaydonhang+"' AND NPP_E.MA = ( SELECT DISTINCT MA FROM NHAPHANPHOI WHERE PK_SEQ = '"+nppId+"' ) "+
							"\n 	 GROUP BY SP_E.MA, NPP_E.MA "+
							"\n ) AS K ";
				// kahch hnag ETC
				 if( tructhuocId.trim().length() < 6 || ( loaiNPP.equals("0") || loaiNPP.equals("1") || loaiNPP.equals("2") || loaiNPP.equals("3") ) )
				 {
					 command +=  
						"\n  select "+ 
					    "\n  K.AVAILABLE AS SOLUONGTON, "+
						
						"\n  a.ma, a.ten, b.donvi, ISNULL(trongluong, 0) as trongluong, ISNULL(thetich, 0) as thetich, " +
						"\n  cast (  isnull( ( select soluong2 / soluong1 from QUYCACH where SANPHAM_FK = a.PK_SEQ and DVDL1_FK = a.DVDL_FK	and DVDL2_FK = '100018' ), 0 ) as numeric(18, 2) ) as qc,	" +
						"\n  [dbo].[GiaCkBanLeNppSanPham]("+nppId+","+khId+",a.pk_seq,'"+ngaydonhang+"' )  as giamua, isnull(a.PT_VAT,0) thuexuat  " +
						"\n  from SANPHAM a "+
					    "\n  inner join ufn_sanpham("+khonhapId+",null,null) kh on kh.sanpham_fk=a.pk_seq "+
												
						"\n  CROSS APPLY ( SELECT * FROM #KHO WHERE SPMA = A.MA AND AVAILABLE > 0 ) AS K "+
					    		
						"\n  left join DONVIDOLUONG b on a.dvdl_fk = b.pk_seq " +
						"\n  where a.pk_seq > 0 and a.DVKD_FK = '" + dvkdId + "' ";
				 }
				 else
				 {
					 command +=  
						"\n  select "+ 
					 	"\n  K.AVAILABLE AS SOLUONGTON, "+
						
						"\n  a.ma, a.ten, b.donvi, ISNULL(trongluong, 0) as trongluong,a.ma, a.ten, b.donvi, ISNULL(trongluong, 0) as trongluong, ISNULL(thetich, 0) as thetich,  " +
						"\n  cast ( isnull( ( select soluong2 / soluong1 from QUYCACH where SANPHAM_FK = a.PK_SEQ and DVDL1_FK = a.DVDL_FK	and DVDL2_FK = '100018' ), 0 ) as numeric(18, 2) ) as qc,	 " +
						"\n  isnull( ( select dongia from BANGGIABANDOITAC_SANPHAM where sanpham_fk = a.pk_seq " +
						"\n  and BGBANDOITAC_FK in ( select top(1) BANGGIABANDOITAC_FK from BANGGIABANDOITAC_DOITAC where  NPP_FK = '" + nppId + "' and BANGGIABANDOITAC_FK in ( select pk_seq from BANGGIABANDOITAC where  NPP_FK = '" + tructhuocId + "' and trangthai = '1' and KENH_FK = '" + kbhId + "' ) ) ), 0) as giamua, isnull(a.PT_VAT,0) thuexuat   " +
						"\n  from SANPHAM a "+ 
						"\n  inner join ufn_sanpham("+khonhapId+",null,null) kh on kh.sanpham_fk=a.pk_seq "+ 
												
						"\n  OUTER APPLY ( SELECT * FROM #KHO WHERE SPMA = A.MA ) AS K "+
								
						"\n  left join DONVIDOLUONG b on a.dvdl_fk = b.pk_seq  " +
						"\n  where a.pk_seq > 0 and a.DVKD_FK = '" + dvkdId + "' ";	
				 }
			 }
			 else
			 {
				 // kahch hnag ETC
				 if( tructhuocId.trim().length() < 6 || ( loaiNPP.equals("0") || loaiNPP.equals("1") || loaiNPP.equals("2") || loaiNPP.equals("3") ) )
				 {
					 command =  
						" select (select kho.available from nhapp_kho kho where kho.sanpham_fk=a.pk_seq and kho.KHO_FK= "+khonhapId+" and NPP_FK='"+nppId+"' and kho.KBH_FK="+kbhId+" )as soluongton,a.ma, a.ten, b.donvi, ISNULL(trongluong, 0) as trongluong, ISNULL(thetich, 0) as thetich, " +
						" cast (  isnull( ( select soluong2 / soluong1 from QUYCACH where SANPHAM_FK = a.PK_SEQ and DVDL1_FK = a.DVDL_FK	and DVDL2_FK = '100018' ), 0 ) as numeric(18, 2) ) as qc,	" +
						" [dbo].[GiaCkBanLeNppSanPham]("+nppId+","+khId+",a.pk_seq,'"+ngaydonhang+"' )  as giamua, isnull(a.PT_VAT,0) thuexuat  " +
						" from SANPHAM a inner join ufn_sanpham("+khonhapId+",null,null) kh on kh.sanpham_fk=a.pk_seq left join DONVIDOLUONG b on a.dvdl_fk = b.pk_seq " +
						" where a.pk_seq > 0 and a.DVKD_FK = '" + dvkdId + "' ";
				 }
				 else
				 {
					 command =  
						" select (select kho.available from nhapp_kho kho where kho.sanpham_fk=a.pk_seq and kho.KHO_FK= "+khonhapId+" and NPP_FK='"+nppId+"' and kho.KBH_FK="+kbhId+" )as soluongton,a.ma, a.ten, b.donvi, ISNULL(trongluong, 0) as trongluong,a.ma, a.ten, b.donvi, ISNULL(trongluong, 0) as trongluong, ISNULL(thetich, 0) as thetich,  " +
						" cast ( isnull( ( select soluong2 / soluong1 from QUYCACH where SANPHAM_FK = a.PK_SEQ and DVDL1_FK = a.DVDL_FK	and DVDL2_FK = '100018' ), 0 ) as numeric(18, 2) ) as qc,	 " +
						" isnull( ( select dongia from BANGGIABANDOITAC_SANPHAM where sanpham_fk = a.pk_seq " +
						" and BGBANDOITAC_FK in ( select top(1) BANGGIABANDOITAC_FK from BANGGIABANDOITAC_DOITAC where  NPP_FK = '" + nppId + "' and BANGGIABANDOITAC_FK in ( select pk_seq from BANGGIABANDOITAC where  NPP_FK = '" + tructhuocId + "' and trangthai = '1' and KENH_FK = '" + kbhId + "' ) ) ), 0) as giamua, isnull(a.PT_VAT,0) thuexuat   " +
						" from SANPHAM a  inner join ufn_sanpham("+khonhapId+",null,null) kh on kh.sanpham_fk=a.pk_seq  left join DONVIDOLUONG b on a.dvdl_fk = b.pk_seq  " +
						" where a.pk_seq > 0 and a.DVKD_FK = '" + dvkdId + "' ";	
				 }
			 }
			
							 
			System.out.println("Lay san pham ...: " + command);
			
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
						out.print("###" + sp.getString("ma") + " - " + tensp + " [" + sp.getString("donvi") + "] [" + sp.getString("giamua") + "] [" + sp.getString("thuexuat") + "] [" + sp.getFloat("soluongton") + "]|"); //luu y: bat buoc phai co dau phan cach '|' o cuoi moi dong'
					}	
					
				}	
				sp.close();
			}
			
			db.shutDown();
		}
		catch(Exception ex){ System.out.println("Xay ra exception roi ban..."); }
	}
	
	 
	/* session.setAttribute("dvkdId", null);
	session.setAttribute("kbhId", null);
	session.setAttribute("nppId",null);
	session.setAttribute("lsxBean", null);
	session.setAttribute("khoId", null); */
	
%>

