

<%@page import="java.text.DecimalFormat"%>
<%@page import="java.text.NumberFormat"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="ISO-8859-1"%>
<%@page import="java.util.Iterator"%>
<%@page import="java.util.List"%>
<%@page import="java.util.ArrayList"%>
<%@ page  import = "java.sql.ResultSet" %>

<%@ page  import = "geso.dms.distributor.db.sql.dbutils" %>
<%@ page  import = "geso.dms.center.util.*" %>
<%@ page import = "java.net.URLDecoder" %>

<% String nppId = (String) session.getAttribute("nppId"); %>
<% String khId = (String) session.getAttribute("khId"); %>
<% String khoId = (String) session.getAttribute("khoId"); %>
<% String donhangKhac = (String) session.getAttribute("donhangKhac");
	System.out.println("dhk:"+donhangKhac);
	
	String LINKSERVER_DB = getServletContext().getInitParameter("LINKSERVER_DB");
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
			String  makho = "",loainpp="",machinhanh ="", quanlykho = "";
			
			String info =" select  MaKho,MaFAST,loainpp, quanlykho from NHAPHANPHOI where pk_seq=    "+ nppId;
			ResultSet rs= db.get(info);
			if( rs.next())
			{
				makho = rs.getString("MaKho") == null ?"": rs.getString("MaKho");
				machinhanh = rs.getString("MaFAST") == null ?"": rs.getString("MaFAST");
				loainpp = rs.getString("loainpp") == null ?"": rs.getString("loainpp");
				quanlykho = rs.getString("quanlykho") == null ?"0": rs.getString("quanlykho");
			}
			rs.close();
			
			String command ="";
			//if(quanlykho.trim().equals("0"))
				if(1==2)
			{
				command = 
					"\n select a.ma, a.ten, b.donvi, dbo.MaxNum(0, floor(ISNULL(d.AVAILABLE, 0))) AS HIENHUU, " +
					"\n	 isnull(a.PT_VAT,0) as VAT, '' scheme, " +
					"\n	[dbo].[GiaCkBanLeNppSanPham]("+nppId+","+khId+",a.pk_seq,'"+ngaydonhang+"' ) as dongia ,0 as isnhomnk, isnull(kh.PT_CHIETKHAU,0) chietkhau, " +
					"\n [dbo].[GiaBanLeNppSanPham](( select kbh_fk from khachhang where pk_seq="+khId+" ),"+nppId+",a.pk_seq,'"+ngaydonhang+"' ) as dongiagoc, '' as program " +
					"\n from SANPHAM a inner join DONVIDOLUONG b on a.dvdl_fk = b.pk_seq " +
					"\n inner join Nhaphanphoi npp on npp.pk_seq ='" + nppId + "' " + 
					"\n inner join khachhang kh on kh.pk_seq =  "+ khId +
					"\n OUTER APPLY " +
					"\n ( " +
					"\n 	SELECT SP.MA AS SPMA, NPP_E.MA AS NPPMA,  SUM(AVAILABLE *isnull(qc.qd,0) ) AVAILABLE FROM "+ LINKSERVER_DB +".[dbo].[ERP_KHOTT_SP_CHITIET] K " +
					"\n 	INNER JOIN "+ LINKSERVER_DB +".[dbo].NHAPHANPHOI NPP_E ON NPP_E.PK_SEQ = K.NPP_FK " +
					"\n 	INNER JOIN "+ LINKSERVER_DB +".[dbo].ERP_SANPHAM SP ON SP.PK_SEQ = K.SANPHAM_FK " +
					"\n 			inner join " + LINKSERVER_DB + ".dbo.Donvidoluong dv_e ON dv_e.PK_SEQ = SP.dvdl_fk  " +
					"\n 		outer apply " +
					"\n 		(  " +
					"\n 			select avg( soluong1/soluong2)qd from QUYCACH qc  " +
					"\n 			inner join DONVIDOLUONG dv_dms on qc.DVDL2_FK = dv_dms.PK_SEQ  " +
					"\n 			where dv_dms.DONVI = dv_e.donvi and qc.DVDL1_FK = a.DVDL_FK and qc.SANPHAM_FK = a.PK_SEQ  " +
					"\n 		)qc " +
					"\n 	WHERE SP.MA = A.MA AND NPP_E.MA = NPP.MA AND K.NGAYNHAPKHO <= '"+ngaydonhang +"' GROUP BY SP.MA, NPP_E.MA " +
					"\n ) as d " +
					"\n where isnull(a.isKM,0) = 0 and a.pk_seq > 0 and a.DVKD_FK = '100001' and  [dbo].[GiaCkBanLeNppSanPham]("+nppId+","+khId+",a.pk_seq,'"+ngaydonhang+"' ) > 0 ";
			}
			else
			{
				command = 
					"\n select a.ma, a.ten, b.donvi, dbo.MaxNum(0, d.AVAILABLE - isnull(ttt.booked,0)) as hienhuu, " +
					"\n	 isnull(a.PT_VAT,0) as VAT, '' scheme, " +
					"\n	[dbo].[GiaCkBanLeNppSanPham]("+nppId+","+khId+",a.pk_seq,'"+ngaydonhang+"' ) as dongia ,0 as isnhomnk, isnull(kh.PT_CHIETKHAU,0) chietkhau, " +
					"\n [dbo].[GiaBanLeNppSanPham](( select kbh_fk from khachhang where pk_seq="+khId+" ),"+nppId+",a.pk_seq,'"+ngaydonhang+"' ) as dongiagoc, '' as program " +
					"\n from SANPHAM a inner join DONVIDOLUONG b on a.dvdl_fk = b.pk_seq " +
					"\n inner join Nhaphanphoi npp on npp.pk_seq ='" + nppId + "' " + 
					"\n inner join khachhang kh on kh.pk_seq =  "+ khId +
					"\n	inner join NHAPP_KHO d on a.PK_SEQ = d.SANPHAM_FK and d.NPP_FK = npp.pk_seq  and d.kho_fk = '" + khoId + "' " +
					"\n	and d.KBH_FK = case npp.DUNGCHUNGKENH  when 1 then 100025 else (select kbh_fk from khachhang where pk_Seq = "+khId+") end "	+	
					"\n left join ( " +
					" select NPP_FK,KBH_FK,KHO_FK,SANPHAM_FK,SUM(booked_sql)booked from [dbo].[ufn_TonTamTinh_ChiTiet]("+nppId+") "+ 
					"\n group by NPP_FK,KBH_FK,KHO_FK,SANPHAM_FK " +
					") ttt on ttt.kbh_fk=d.kbh_fk and ttt.kho_fk=d.KHO_FK and ttt.npp_fk=d.npp_fk and ttt.sanpham_fk=d.SANPHAM_FK " +
					"\n where isnull(a.isKM,0) = 0 and a.pk_seq > 0 and a.DVKD_FK = '100001' and  [dbo].[GiaCkBanLeNppSanPham]("+nppId+","+khId+",a.pk_seq,'"+ngaydonhang+"' ) > 0 ";
			}
						
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
					//int soluong1 = sp.getInt("soluong1");
					//int soluong2 = sp.getInt("soluong2");
					
					if(donhangKhac.equals("0"))
					{
						if(masp.toUpperCase().startsWith(query.toUpperCase()) ||masp.toUpperCase().indexOf(query.toUpperCase()) >= 0 
								|| tensp.toUpperCase().indexOf(query.toUpperCase()) >= 0 || donvi.toUpperCase().indexOf(query.toUpperCase()) >= 0 )
						{
							if(TENSP.length() > 50)
								TENSP = TENSP.substring(0, 50);
							//out.print("###" + sp.getString("ma") + " - " + TENSP +" [" + sp.getString("donvi") + "] ["+ sp.getString("dongia") + "] [" + format.format(hienhuu) + "] [" + Integer.toString(soluong1) + "] [" + Integer.toString(soluong2) + "]|"); //luu y: bat buoc phai co dau phan cach '|' o cuoi moi dong'
							
							//out.print("###" + sp.getString("ma") + " - " + TENSP +" [" + sp.getString("donvi") + "] ["+ sp.getString("dongia") + "] [" + hienhuu + "] [" + sp.getString("VAT") + "] [" + sp.getString("isnhomnk") + "]|"); //luu y: bat buoc phai co dau phan cach '|' o cuoi moi dong'
							out.print("###" + sp.getString("ma") + " - " + TENSP +" [" + sp.getString("donvi") + "] ["+ sp.getString("dongia") + "] ["+ sp.getString("dongia") + "] [" + hienhuu + "] [" + sp.getString("VAT") + "] [" + sp.getString("chietkhau") + "]|");
						}
					}
					
					if(donhangKhac.equals("1"))
					{
						if(masp.toUpperCase().startsWith(query.toUpperCase()) ||masp.toUpperCase().indexOf(query.toUpperCase()) >= 0 
								|| tensp.toUpperCase().indexOf(query.toUpperCase()) >= 0 || donvi.toUpperCase().indexOf(query.toUpperCase()) >= 0 )
						{
							if(TENSP.length() > 50)
								TENSP = TENSP.substring(0, 50);
							//out.print("###" + sp.getString("ma") + " - " + TENSP +" [" + sp.getString("donvi") + "] ["+ sp.getString("dongia") + "] [" + format.format(hienhuu) + "] [" + Integer.toString(soluong1) + "] [" + Integer.toString(soluong2) + "]|"); //luu y: bat buoc phai co dau phan cach '|' o cuoi moi dong'
							
							System.out.println("###" + sp.getString("ma") + " - " + TENSP +" [" + sp.getString("donvi") + "] ["+ sp.getString("dongia") + "] ["+ sp.getString("dongiagoc") + "] ["+ sp.getString("program") + "] [" + format.format(hienhuu) + "] [" + sp.getString("VAT") + "] [" + sp.getString("isnhomnk") + "]|");
							out.print("###" + sp.getString("ma") + " - " + TENSP +" [" + sp.getString("donvi") + "] ["+ sp.getString("dongia") + "] ["+ sp.getString("dongia") + "] [" + format.format(hienhuu) + "] [" + sp.getString("VAT") + "] [" + sp.getString("isnhomnk") + "]|"); //luu y: bat buoc phai co dau phan cach '|' o cuoi moi dong'
						}
					}
				}	
			}
			sp.close();
			db.shutDown();
			db=null;
			nppId=null;
			khId=null;
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

