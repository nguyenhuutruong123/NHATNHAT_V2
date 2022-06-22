<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@page import="geso.dms.center.beans.baocao.IBCKhuyenMaiApGame"%>
<%@page import="com.cete.dynamicpdf.ob"%>
<%@page import="java.sql.ResultSet"%> 
<%@page import="java.util.Calendar"%>
<%@page import="java.sql.Date"%>
<%@page import="geso.dms.center.util.*"%>

<%
	String userId = (String) session.getAttribute("userId");
	String userTen = (String) session.getAttribute("userTen"); 

	IBCKhuyenMaiApGame obj = (IBCKhuyenMaiApGame) session.getAttribute("obj");
	ResultSet npp = obj.getRsNpp(); 
	ResultSet ddkd = obj.getRsDdkd();
	/* int[] quyen = new  int[6];
	quyen = util.Getquyen("BCKhuyenMaiApGameSvl","", userId); */
%>
<% Utility util = new Utility(); %>
<%String url = util.getChuyenNguUrl("UsingPromotionTTSvl", "",session); %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd"> <% Utility Utilback = new Utility(); %>
<html>
<head>
<TITLE><%= getServletContext().getInitParameter("TITLENAME") %></TITLE>
<META http-equiv="Content-Type" content="text/html; charset=utf-8">
<META http-equiv="Content-Style-Type" content="text/css">
<LINK rel="stylesheet" type="text/css" media="print" href="../css/impression.css">
<LINK rel="stylesheet" href="../css/main.css" type="text/css">
<script type="text/javascript" src="../scripts/jquery-1.js"></script>
<link type="text/css" rel="stylesheet" href="../css/mybutton.css">
<LINK rel="stylesheet" type="text/css" href="../css/style.css" />

<script type="text/javascript" src="../scripts/jquery.min.1.7.js"></script>
<link href="../css/jquery-ui.css" rel="stylesheet" type="text/css" />
<script src="../scripts/ui/jquery.ui.core.js" type="text/javascript"></script>
<script src="../scripts/ui/jquery.ui.widget.js" type="text/javascript"></script>
<script src="../scripts/ui/jquery.ui.datepicker.js" type="text/javascript"></script>
<script type="text/javascript">
	$(document).ready(function() {
		$(".days").datepicker({
			changeMonth : true,
			changeYear : true
		});
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

<!-- <script type="text/javascript" src="../scripts/report-js/action-report.js"></script> -->
<script language="javascript" type="text/javascript">

	function taobaocao() {
		//document.getElementById("btnSave1").innerHTML = "<a href='#'><img src='../images/waiting.gif'  title='cho thuc hien..' border='1' longdesc='cho thuc hien..' style='border-style:outset'> Processing...</a>";
		document.forms['frm'].action.value = 'taobaocao'; 
		document.forms["frm"].submit(); 
	}
	
	function search() { 
		document.forms['frm'].action.value = 'search'; 
		document.forms["frm"].submit();
	}
	
</script>

<link href="../css/select2.css" rel="stylesheet" />
<script src="../scripts/select2.js"></script>
<script>
$(document).ready(function()
{
	$("#nppId").select2();
	$("#ddkdId").select2();
});
</script>
</head>
<BODY leftmargin="0" bottommargin="0" topmargin="0" rightmargin="0">
	<form name="frm" method="post" action="../../BCKhuyenMaiApGameSvl">
	<% redis.clients.jedis.Jedis jedis =  geso.dms.center.util.Utility.getJedis(); %><% geso.dms.center.util.Csrf csdr=new geso.dms.center.util.Csrf(request,response,true,false,true);%>
 <input type="hidden" name="<%=csdr.get_tokenName() %>" value='<%=csdr.get_tokenValue() %>'>
	
		<input type="hidden" name="alert" value=<%=Utility.GLanguage(obj.getMsg(),session,jedis) %>>
		<input type="hidden" name="action" value='1'>
		<input type="hidden" name="view" value='TT'>
		<input type="hidden" name="userId" value='<%=obj.getUserId()%>'>
		<input type="hidden" name="userTen" value='<%=obj.getUserTen()%>'>
		<div id="main" style="width: 99%; padding-left: 2px">
			<div align="left" id="header" style="width: 100%; float: none">
				<div style="width: 70%; padding: 5px; float: left" class="tbnavigation">
					<%=url %></div>
				<div align="right" style="padding: 5px" class="tbnavigation">
					<%=Utility.GLanguage("Chào mừng bạn",session,jedis) %>  <%=obj.getUserTen()%></div>
			</div>
			<div align="left" style="width: 100%; float: none; clear: left">
				<fieldset>
					<legend class="legendtitle"><%=Utility.GLanguage("Báo lỗi nhập liệu",session,jedis) %></legend>
					<textarea id="errors" name="errors" readonly rows="1" style="width: 100%; color: #F00; font-weight: bold; text-align: left"><%=obj.getMsg()%></textarea>
				</fieldset>
			</div>
			<div align="left" style="width: 100%; float: none; clear: left; font-size: 0.7em">
				<fieldset>
					<legend class="legendtitle"><%=Utility.GLanguage("Báo cáo khuyến mãi áp game",session,jedis) %></legend></fieldset>
					<div style="width: 100%; float: none" align="left">
						<div style="width: 100%; float: none" align="left" class="plainlabel">
							<TABLE width="70%" cellpadding="6" cellspacing="0">
								<TR>
									<TD class="plainlabel"><%=Utility.GLanguage("Từ ngày",session,jedis) %></TD>
									<TD class="plainlabel">
										<input type="text" name="tungay" width="200px"	class="days" value='<%=obj.getTungay()%>'/></TD>
									
									<TD class="plainlabel"><%=Utility.GLanguage("Đến ngày",session,jedis) %></TD>
									<TD>
										<input type="text" name="denngay" width="200px" class="days" value='<%=obj.getDenngay()%>'/></TD>
								</TR> 
								<TR>
									<TD class="plainlabel"><%=Utility.GLanguage("Nhà phân phối",session,jedis) %></TD>
									<TD class="plainlabel">
										<select name="nppId" id="nppId" style="width: 200px" onchange="search();">
											<option> </option>
										<%
											if (npp != null)
												while (npp.next()) {
													if (npp.getString("pk_seq").equals(obj.getNppId())) {
										%>
														<option value="<%=npp.getString("pk_seq")%>" selected><%=npp.getString("ten")%></option>
													<% } else { %>
														<option value="<%=npp.getString("pk_seq")%>"><%=npp.getString("ten")%></option>
										<%
													}
												}
										%>
										</select></TD>
									
									<TD class="plainlabel"><%=Utility.GLanguage("NHÂN VIÊN BÁN HÀNG",session,jedis) %></TD>
									<TD class="plainlabel">
										<select name="ddkdId" id="ddkdId" style="width: 200px"">
											<option> </option>
										<%
											if (ddkd != null)
												while (ddkd.next()) {
													if (ddkd.getString("pk_seq").equals(obj.getDdkdId())) {
										%>
														<option value="<%=ddkd.getString("pk_seq")%>" selected><%=ddkd.getString("ten")%></option>
													<% } else { %>
														<option value="<%=ddkd.getString("pk_seq")%>"><%=ddkd.getString("ten")%></option>
										<%
														}
												}
										%>
										</select></TD>
								</TR>
								<TR>
									<TD class="plainlabel" colspan="3">
										<div id="btnSave1">
											<a class="button" href="javascript:taobaocao();"> 
												<img style="top: -4px;" src="../images/button.png" alt=""><%=Utility.GLanguage("Tạo báo cáo",session,jedis) %></a>
										</div>
									</TD>
								</TR>
							</TABLE>
						</div>
					</div>
				</div>
			</div>
	</form> 
	<%
	if(npp !=null) npp.close();
	if(ddkd!=null) ddkd.close();
	if(obj!=null) 
	{	obj.DBclose();
		obj = null;}
	session.setAttribute("obj", null);
%>

<%geso.dms.center.util.Utility.JedisClose(jedis); %>

</body>
</HTML>