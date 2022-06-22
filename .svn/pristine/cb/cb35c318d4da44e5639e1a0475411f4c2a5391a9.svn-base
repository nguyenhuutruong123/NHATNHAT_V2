<%@page import="geso.dms.center.beans.stockintransit.IStockintransit"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page  import = "geso.dms.center.util.*" %>
<%@page import="java.sql.ResultSet"%>

<%	
	IStockintransit obj = (IStockintransit)session.getAttribute("obj");
	String userId =obj.getuserId();  
	String userTen = obj.getuserTen();  	
	String loi=  obj.getMsg();
	String tungay= obj.gettungay();
	String denngay= obj.getdenngay();
	ResultSet kenh = obj.getkenh();
 %>


<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"> <% Utility Utilback = new Utility(); %>

<HTML>
<HEAD>
<TITLE><%= getServletContext().getInitParameter("TITLENAME") %></TITLE>
	<link type="text/css" rel="stylesheet" href="../css/mybutton.css">
<META http-equiv="Content-Style-Type" content="text/css">
<LINK rel="stylesheet" href="../css/main.css" type="text/css">
<script type="text/javascript" src="../scripts/jquery-1.js"></script>
<link type="text/css" rel="stylesheet" href="../css/mybutton.css">
<LINK rel="stylesheet" type="text/css" href="../css/style.css" />

<script type="text/javascript" src="../scripts/jquery.min.1.7.js"></script>
<link href="../css/jquery-ui.css" rel="stylesheet" type="text/css" />
<script src="../scripts/ui/jquery.ui.core.js" type="text/javascript"></script>
<script src="../scripts/ui/jquery.ui.widget.js" type="text/javascript"></script>
<script src="../scripts/ui/jquery.ui.datepicker.js"
	type="text/javascript"></script>
<script type="text/javascript">
	
	$(document).ready(function() {
		$(".days").datepicker({
			changeMonth : true,
			changeYear : true
		});		
	});
</script>




<SCRIPT language="javascript" type="text/javascript">

function submitform()
{
	//document.getElementById("btnTaoBC").innerHTML = "<a href='#'><img src='../images/waiting.gif'  title='cho thuc hien..' border='1' longdesc='cho thuc hien..' style='border-style:outset'> Processing...</a>";

	//document.forms['rpForm'].dataerror.value="";
	document.forms['rpForm'].action.value="";
	document.forms['rpForm'].submit();
}

</SCRIPT>
</HEAD>
<BODY leftmargin="0" bottommargin="0" topmargin="0" rightmargin="0">
<form name="rpForm" method="post" action="../../Iventorynpp">
<% redis.clients.jedis.Jedis jedis =  geso.dms.center.util.Utility.getJedis(); %><% geso.dms.center.util.Csrf csdr=new geso.dms.center.util.Csrf(request,response,true,false,true);%>
 <input type="hidden" name="<%=csdr.get_tokenName() %>" value='<%=csdr.get_tokenValue() %>'>

<input type="hidden" name="userId" value= <%= userId %> >
<input type="hidden" name="action" value='1'>

<TABLE width="100%" border="0" cellspacing="0" cellpadding="0"
	height="100%">
	
	<TR>
		<TD colspan="4" align='left' valign='top' bgcolor="#FFFFFF">
			<TABLE width="100%" cellpadding="0" cellspacing="1">		
				<TR>
					<TD width="100%" align="left">
					  <table width="100%" border="0" cellpadding="0" cellspacing="0">
						  <tr height="22">
						   <TD align="left" colspan="2" class="tbnavigation">&nbsp;Quản lý tồn kho &gt; Báo cáo &gt; Tồn hiện tại </TD>
   
						   <TD colspan="2" align="right" class="tbnavigation"><%=Utility.GLanguage("Chào mừng bạn",session,jedis) %>  <%=userTen %>&nbsp;</TD>
						  </tr>
 					  </table>					</TD>
				</TR>
				<tr>
					<TD align="left" colspan="4" class="legendtitle">
						<FIELDSET>
						<LEGEND class="legendtitle"><%=Utility.GLanguage("Báo lỗi nhập liệu",session,jedis) %></LEGEND>
	    				<textarea name="dataerror"  style="width: 100% ; color:#F00 ; font-weight:bold"  style="width: 100% ; color:#F00 ; font-weight:bold" readonly="readonly" rows="1"><%=obj.getMsg()%></textarea>
						</FIELDSET>
				   </TD>
				</tr>	
				<TR>
					<TD>
					<TABLE border="0" width="100%" cellpadding="0" cellspacing="0">
						<TR>
							<TD width="100%" align="center" >
							<FIELDSET>
							  <LEGEND class="legendtitle">Thời gian xuất báo cáo</LEGEND>
							<TABLE  width="100%" cellpadding="6" cellspacing="0">
								
								
								<TR>
																	
				  				<TD class="plainlabel" width="5%"><%=Utility.GLanguage("Kênh bán hàng",session,jedis) %></TD>										
								<TD class="plainlabel" width="25%">
									<select name="kenhId" id="kenhId">
											<option value="" selected>All</option>
											<% if(kenh != null)
											   while(kenh.next()){
											   if(kenh.getString("pk_seq").equals(obj.getkenhId()))
											   { %>
											   <option value="<%= kenh.getString("pk_seq") %>" selected><%=kenh.getString("diengiai") %></option>
											   <%}else { %>
											   <option value="<%= kenh.getString("pk_seq") %>"><%=kenh.getString("diengiai") %></option>
											<%} }%>
									</select>
								</TD>
								</TR>											
				  				<TR>
				  					<TD class="plainlabel" colspan = '3' style="font-size: 12px; font-weight: bold;" >
				  						<input type="radio" value="0" name="avail"  />Lấy tất cả
				  						<input type="radio" value="1" name="avail" checked="checked"  />Chỉ lấy số lượng lớn hơn 0										
									</TD>
									
                           		</TR> 
                           		<TR>
                           			<TD class="plainlabel" colspan = '3'>
                           			<%if(obj.gettype().trim().equals("1")){ %>
							  				<input name="laysolo" checked="checked" value="1"  type="checkbox" /> Lấy có số Lô
							  				<%}else{ %>
							  				<input name="laysolo" value="1"  type="checkbox" /> Lấy có số Lô
							  				<%} %>	
                           			</TD>
                           		</TR>                                    										
							    <TR>
									<TD colspan="2" class="plainlabel">
									<div id="btnTaoBC">
									<a class="button2" href="javascript:submitform()" >
	<img style="top: -4px;" src="../images/button.png" alt=""><%=Utility.GLanguage("Tạo báo cáo",session,jedis) %></a>
									</div>
	&nbsp;&nbsp;&nbsp;&nbsp;                                    </TD>
								</TR>
							</TABLE>
							</FIELDSET>						</TD>
						</TR>
					</TABLE>					</TD>
				</TR>
			</TABLE>
		</TD>
	</TR>
</TABLE>
</form>
</BODY>
</HTML>
<%
try{
	if(obj!=null){
		obj.DBclose();
		obj=null;
		
	}
	session.setAttribute("obj",null);
	
}catch(Exception er){
	
}
%>

