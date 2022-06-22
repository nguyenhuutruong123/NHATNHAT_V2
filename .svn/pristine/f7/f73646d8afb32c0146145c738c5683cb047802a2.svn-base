<%@page import="java.sql.SQLException"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@page import="java.util.Iterator"%>
<%@page import="java.util.List"%>
<%@page import="java.util.ArrayList"%>
<%@ page  import = "java.sql.ResultSet" %>
<%@ page  import = "geso.dms.distributor.db.sql.dbutils" %>
<%@ page import = "java.net.URLDecoder" %>
<%@ page import="geso.dms.center.util.Utility"%>

<%
    String nppId = (String) session.getAttribute("nppId");
	String ddkdId = (String) session.getAttribute("ddkdId");
	String usedId = (String) session.getAttribute("userId");
	try
	{  
	
	dbutils db = new dbutils();
	
	String smartId = "";
	String khId = "";
	String khTen = "";
	String khChietKhau = "";
	String khBgst = "";
	
	String command="";
	request.setCharacterEncoding("UTF-8");
   
 	Utility Ult = new Utility();
	
   	String query = (String)request.getQueryString(); 
   	query = new String(query.substring(query.indexOf("q=") + 2, query.indexOf("&limit=")).getBytes("UTF-8"), "UTF-8");
   	query = URLDecoder.decode(query, "UTF-8").replace("+", " ");
	query = Ult.replaceAEIOU(query);
	
	response.setHeader("Content-Type", "text/html; charset=UTF-8");
	
 	//thay bang truy van long xem thu co nhanh hon khong.
 	if(ddkdId.length() > 0)
 	{
	 	command = "\n select maFAST, b.pk_seq as khId, b.ten as khTen, b.diachi, isnull(c.chietkhau, '0') as chietkhau ";
		command	+= "\n from khachhang b left join mucchietkhau c on b.chietkhau_fk = c.pk_seq where b.trangthai = '1' and b.pk_seq  in ( select khachhang_fk from khachhang_npp where npp_fk = '" + nppId + "')  ";
		command += "\n and b.pk_seq in (select khachhang_fk from khachhang_tuyenbh ";		
		command += "\n where b.kbh_fk !=100052  and  tbh_fk in(select pk_seq from tuyenbanhang where ddkd_fk = '" + ddkdId + "')) ";
		//command += "and ( b.maFAST like '%" + query + "%' or upper(dbo.ftBoDau(b.ten)) like upper((N'%" + Ult.replaceAEIOU(query).toUpperCase() + "%')) or upper(dbo.ftBoDau(b.diachi)) like upper((N'%" + Ult.replaceAEIOU(query).toUpperCase() + "%'))  ) ";
		command += "\n  and upper(timkiem) like upper((N'%" + Ult.replaceAEIOU(query).toUpperCase() + "%')) ";
		/* command += " and b.kho_fk in "+Ult.quyen_kho(usedId);  */
		command += "\n order by maFAST, khTen";
 	}
 	else//lay khoang 30 khach hang gan giong ky tu nguoi su dung nhap vao
 	{
 		command = "\n select top(50) b.maFAST, b.pk_seq as khId, b.ten as khTen, b.diachi, isnull(c.chietkhau, '0') as chietkhau ";
		command += "\n from khachhang b left join mucchietkhau c on b.chietkhau_fk = c.pk_seq where b.kbh_fk != 100052 and  b.trangthai = '1' and b.pk_seq  in ( select khachhang_fk from khachhang_npp where npp_fk = '" + nppId + "') ";
		//command += "and (b.maFAST like '%" + query + "%' or upper(dbo.ftBoDau(b.ten)) like upper((N'%" + Ult.replaceAEIOU(query).toUpperCase() + "%')) or upper(dbo.ftBoDau(b.diachi)) like upper((N'%" + Ult.replaceAEIOU(query).toUpperCase() + "%')) ) ";
		command += "\n  and upper(timkiem) like upper((N'%" + Ult.replaceAEIOU(query).toUpperCase() + "%')) ";
		command += "\n and b.pk_seq in (select distinct khachhang_fk from khachhang_tuyenbh ";
		command += "\n where tbh_fk in (select pk_seq from tuyenbanhang where npp_fk = '" + nppId + "')) ";
		/* command += " and b.kho_fk in "+Ult.quyen_kho(usedId);  */
		command += "  ";
		command+=
			"\n 	union  "+ 
			"\n 	select top(50) b.maFAST, b.pk_seq as khId, "+ 
			"\n 	b.ten as khTen, b.diachi, isnull(c.chietkhau, '0') as chietkhau  "+
			"\n 	from khachhang b left join mucchietkhau c on b.chietkhau_fk = c.pk_seq where b.trangthai = '1' and b.npp_fk = '" + nppId + "'  "+ 
			//"	and b.XuatKhau=1 "+
			//"	and (b.maFAST like '%" + query + "%' or upper(dbo.ftBoDau(b.ten)) like upper((N'%" + Ult.replaceAEIOU(query).toUpperCase() + "%')) or upper(dbo.ftBoDau(b.diachi)) like upper((N'%" + Ult.replaceAEIOU(query).toUpperCase() + "%')) ) "+ 
			"\n   and upper(timkiem) like upper((N'%" + Ult.replaceAEIOU(query).toUpperCase() + "%')) and b.kbh_fk != 100052 " +
			/* " and b.kho_fk in "+Ult.quyen_kho(usedId)+ */
			"\n order by maFAST, khTen ";
				
 	}
 	
 	System.out.println("_____Lay khach hang JSP: " + command);
  	
 	ResultSet kh = db.get(command);
 	
	if(kh != null)
	{
		int m = 0;
			String khb;
			while(kh.next())
			{   
				khb =  kh.getString("khId");
				khId = kh.getString("maFAST");
				khTen = kh.getString("khTen") + " - " + kh.getString("diachi");
				khChietKhau = kh.getString("chietkhau");
				khBgst = "0";//kh.getString("bgstId");
               
                //if( Ult.replaceAEIOU(khTen.toUpperCase()).contains(query.toUpperCase()) || khId.contains(query.toUpperCase()) || 
                	//	Ult.replaceAEIOU(kh.getString("diachi").toUpperCase()).contains(query.toUpperCase()) )
				//{
					String khachhang = khId + "-->[" + khTen + "] [" + khChietKhau + "] [" + khBgst + "] [" + khb + "]|";
					//System.out.println("khachhang : "+khachhang);
					out.println(khachhang + "\n");
					
					m++;
					//if(m >= 50)
						//break;
				//}
			}
			kh.close();
		
	}
	db.shutDown();
	db=null;

	}
	catch(Exception e)
	{
		e.printStackTrace();
	}
		
%>