<%@page import="java.sql.ResultSet"%>
<%@page import="geso.dms.center.beans.stockintransit.IStockintransit"%>
<%@page import="java.util.Calendar"%>
<%@page import="java.sql.Date"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%
	String userId = (String) session.getAttribute("userId");
	String userTen = (String) session.getAttribute("userTen");
	String groupCustomer = (String)session.getAttribute("groupCustomer");
	String gorupSKU = (String)session.getAttribute("gorupSKU");
	
	
	IStockintransit obj = (IStockintransit) session.getAttribute("obj");	
	ResultSet vung = obj.getvung();
	ResultSet khuvuc = obj.getkhuvuc();
	ResultSet npp = obj.getnpp();	
	
%>
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

<script type="text/javascript"
	src="../scripts/report-js/action-report.js"></script>
<script language="javascript" type="text/javascript">
	function seach() {
		document.forms['frm'].action.value = 'seach';
		document.forms["frm"].submit();
	}
	function submitform() {
		if (document.forms["frm"]["Sdays"].value.length == 0) {
			setErrors("Vui l??ng ch???n ng??y b???t ?????u");
			return;
		}
		if (document.forms["frm"]["Edays"].value.length == 0) {
			setErrors("Vui l??ng ch???n ng??y k???t th??c");
			return;
		}
		
		var fieldShow = document.getElementsByName("fieldsHien");
		for ( var i = 0; i < fieldShow.length; ++i) {
			fieldShow.item(i).checked = true;
		}

		document.forms['frm'].action.value = 'create';
		document.forms["frm"].submit();
		reset();
	}
	function setErrors(errorMsg) {
		var errors = document.getElementById("errors");
		errors.value = errorMsg;
	}
	function reset() {
		var fieldShow = document.getElementsByName("fieldsHien");		
		for ( var i = 0; i < fieldShow.length; ++i) {
			fieldShow.item(i).checked = false;
		}		
	};
</script>
</head>
<BODY leftmargin="0" bottommargin="0" topmargin="0" rightmargin="0">
	<form name="frm" method="post"
		action="../../BCTheoDoiKSN">
		<input type="hidden" name="action" value='1'> <input
			type="hidden" name="userId" value='<%=obj.getuserId()%>'>
		<div id="main" style="width: 99%; padding-left: 2px">
			<div align="left" id="header" style="width: 100%; float: none">
				<div style="width: 70%; padding: 5px; float: left"
					class="tbnavigation">B??o c??o qu???n tr??? &#62; B??o C??o Kh??c &#62; Theo d??i kh??a s??? ng??y</div>
				<div align="right" style="padding: 5px" class="tbnavigation">
					<%=Utility.GLanguage("Ch??o m???ng b???n",session,jedis) %> 
					<%=obj.getuserTen()%></div>
			</div>
			<div align="left" style="width: 100%; float: none; clear: left">
				<fieldset>
					<legend class="legendtitle"> <%=Utility.GLanguage("B??o l???i nh???p li???u",session,jedis) %></legend>
					<textarea id="errors" name="errors" rows="2"  style="width: 100% ; color:#F00 ; font-weight:bold">
						<%=obj.getMsg()%></textarea>
				</fieldset>
			</div>
			<div align="left"
				style="width: 100%; float: none; clear: left; font-size: 0.7em">
				<fieldset>
					<legend class="legendtitle">Theo d??i kh??a s??? </legend>
					<div style="width: 100%; float: none" align="left">
						<div style="width: 100%; float: none" align="left"
							class="plainlabel">
							<TABLE width="70%" cellpadding="6" cellspacing="0">
								<TR>
									<TD class="plainlabel"><%=Utility.GLanguage("T??? ng??y",session,jedis) %></TD>
									<TD class="plainlabel">
										<input type="text" name="Sdays"	class="days" value='<%=obj.gettungay()%>' /></TD>
									<TD class="plainlabel"><%=Utility.GLanguage("?????n ng??y",session,jedis) %></TD>
									<td>
										<input type="text" name="Edays" class="days" value='<%=obj.getdenngay()%>' /></td>
								</TR>								
								<TR>
									<td colspan="4"><a class="button"
										href="javascript:submitform();"> <img style="top: -4px;"
											src="../images/button.png" alt=""> T???o b??o c??o </a></td>
								</TR>
							</TABLE>
						</div>
						<hr>				
					</div>
				</fieldset>
			</div>
		</div>
		<br /> <br /> <br /> <br />
	</form>
	<%
		
		if(vung!=null)vung.close();
		if(khuvuc!=null)khuvuc.close();
		if(npp!=null)npp.close();		
		if(obj!=null){obj.DBclose();
		obj = null;}
		session.setAttribute("obj", null);
	%>
</body>
</HTML>