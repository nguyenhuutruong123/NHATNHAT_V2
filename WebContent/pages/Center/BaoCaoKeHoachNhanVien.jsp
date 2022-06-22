<%@page import="java.sql.ResultSet"%>
<%@page import="geso.dms.center.beans.stockintransit.IStockintransit"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page  import = "geso.dms.center.util.*" %>
<%@ page  import = "java.util.Date" %>
<%@ page  import = "java.text.DateFormat" %>
<%@ page  import = "java.text.SimpleDateFormat" %>
<%@ page  import = "geso.dms.center.util.*" %>
<%	
	IStockintransit obj = (IStockintransit) session.getAttribute("obj");
Utility util = (Utility) session.getAttribute("util");

 %>
 <%String url = util.getChuyenNguUrl("BaoCaoKeHoachNhanVien", "",session); %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd"> <% Utility Utilback = new Utility(); %>
<html>
<head>
<TITLE><%= getServletContext().getInitParameter("TITLENAME") %></TITLE>
<META http-equiv="Content-Type" content="text/html; charset=utf-8">
<META http-equiv="Content-Style-Type" content="text/css">
<LINK rel="stylesheet" type="text/css" media="print"
	href="../css/impression.css">
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
		
		$(".button").hover(function() {
			$(".button img").animate({
				top : "-10px"
			}, 200).animate({
				top : "-4px"
			}, 200) // first jump
			.animate({
				top : "-7px"
			}, 100).animate({
				top : "-4px"
			}, 100) // second jump
			.animate({
				top : "-6px"
			}, 100).animate({
				top : "-4px"
			}, 100); // the last jump
		});
	});
	$(document).ready(function() {
		$(".button1").hover(function() {
			$(".button1 img").animate({
				top : "-10px"
			}, 200).animate({
				top : "-4px"
			}, 200) // first jump
			.animate({
				top : "-7px"
			}, 100).animate({
				top : "-4px"
			}, 100) // second jump
			.animate({
				top : "-6px"
			}, 100).animate({
				top : "-4px"
			}, 100); // the last jump
		});
	});
</script>


<script language="javascript" type="text/javascript">

	function seach() 
	{
		document.forms['frm'].action.value = 'seach';
		document.forms["frm"].submit();
	}
	function submitform() 
	{
		document.forms['frm'].action.value = 'Taomoi';
		document.forms["frm"].submit();
	}

</script>
</head>
<BODY leftmargin="0" bottommargin="0" topmargin="0" rightmargin="0">
	<form name="frm" method="post" action="../../BaoCaoKeHoachNhanVien">
	<% redis.clients.jedis.Jedis jedis =  geso.dms.center.util.Utility.getJedis(); %><% geso.dms.center.util.Csrf csdr=new geso.dms.center.util.Csrf(request,response,true,false,true);%>
 <input type="hidden" name="<%=csdr.get_tokenName() %>" value='<%=csdr.get_tokenValue() %>'>
	
		<input type="hidden" name="action" value='1'> 
		<input type="hidden" name="userId" value='<%=obj.getuserId()%>'>
			
		<div id="main" style="width: 99%; padding-left: 2px">
			<div align="left" id="header" style="width: 100%; float: none">
				<div style="width: 70%; padding: 5px; float: left"
					class="tbnavigation"><%=url %> </div>
				<div align="right" style="padding: 5px" class="tbnavigation">
					<%=Utility.GLanguage("Chào mừng bạn",session,jedis) %>  <%= obj.getuserTen() %></div>
			</div>
			<div align="left" style="width: 100%; float: none; clear: left">
				<fieldset>
					<legend class="legendtitle"> <%=Utility.GLanguage("Thông báo",session,jedis) %></legend>
					<textarea id="errors" name="errors" rows="2"  style="width: 100%;text-align: left; color:#F00 ; font-weight:bold">
						<%=Utility.GLanguage(obj.getMsg(),session,jedis) %></textarea>
				</fieldset>
			</div>
			<div align="left"
				style="width: 100%; float: none; clear: left;font-size: 0.7em">
				<fieldset>
					<legend class="legendtitle"><%=Utility.GLanguage("Báo cáo MCP",session,jedis) %></legend>
					<div style="width: 100%; float: none" align="left">
						<div style="width: 100%; float: none" align="left"
							class="plainlabel">
							<TABLE width="70%" cellpadding="6" cellspacing="0">
								<TR>
									<TD class="plainlabel"><%=Utility.GLanguage("Từ",session,jedis) %></TD>
									<TD class="plainlabel"><%=Utility.GLanguage("tháng",session,jedis) %>
										<select name="tuthang" style="width: 150px"  onchange="submitform()">
											<%
												
											for (int n = 1; n <=12; n++) {
												if (n == Integer.parseInt(obj.getFromMonth())) {
											%>
													<option value=<%=n%> selected="selected"><%=n%></option>
											<%
												} else {
											%>
													<option value=<%=n%>><%=n%></option>
											<%
												}
											}
											%>
										</select>
									</TD>
									<TD class="plainlabel">
										<%=Utility.GLanguage("Năm",session,jedis) %> <select name="tunam" style="width: 200px;" onchange="submit()">
									  	<%  
										  	DateFormat dateFormat = new SimpleDateFormat("yyyy");
									        Date date = new Date();
									        int year = Integer.parseInt(dateFormat.format(date));
									  		for(int i = year - 2; i < year + 2; i++) {
									  			if (obj.getFromYear().equals(String.valueOf(i))) { %>
										  			<option value ="<%=i %>" selected><%=i %></option>
										<%} 	else { %>
													<option value ="<%=i %>"><%=i %></option>
										<%		}
											}%>
										</select>
									</TD>
								</TR>
								<TR>
									<TD class="plainlabel"><%=Utility.GLanguage("Đến",session,jedis) %></TD>
									<TD class="plainlabel">
										<%=Utility.GLanguage("Tháng",session,jedis) %> <select name="denthang" style="width: 150px"  onchange="submitform()">
											<%
												
											for (int n = 1; n <=12; n++) {
												if (n == Integer.parseInt(obj.getToMonth())) {
											%>
													<option value=<%=n%> selected="selected"><%=n%></option>
											<%
												} else {
											%>
													<option value=<%=n%>><%=n%></option>
											<%
												}
											}
											%>
										</select>
									</TD>
									<TD class="plainlabel">
										<%=Utility.GLanguage("Năm",session,jedis) %> <select name="dennam" style="width: 200px;" onchange="submit()">
									  	<%  
									  		for(int i = year - 2; i < year + 2; i++) {
									  			if (obj.getToYear().equals(String.valueOf(i))) { %>
										  			<option value ="<%=i %>" selected><%=i %></option>
										<%} 	else { %>
													<option value ="<%=i %>"><%=i %></option>
										<%		}
											}%>
										</select>	
									</TD>
								</TR>
								
								<TR>
									<TD colspan="4"><a class="button"
										href="javascript:submitform();"> <img style="top: -4px;"
											src="../images/button.png" alt=""><%=Utility.GLanguage("Tạo báo cáo",session,jedis) %> </a>
									</TD>
								</TR>
							</TABLE>
						</div>
					</div>
				</fieldset>
			</div>
		</div>
		
<%geso.dms.center.util.Utility.JedisClose(jedis); %>
	</form>
<%
	if(obj!=null){obj.DBclose();
	obj = null;}
	session.setAttribute("obj", null);
%>
</body>
</HTML>