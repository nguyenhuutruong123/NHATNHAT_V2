<%@page import="java.sql.ResultSet"%>
<%@page import="geso.dms.center.beans.stockintransit.IStockintransit"%>
<%@page import="java.util.Calendar"%>
<%@page import="java.sql.Date"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%
	
	String userId = (String) session.getAttribute("userId");
	String userTen = (String) session.getAttribute("userTen");
	IStockintransit obj = (IStockintransit) session.getAttribute("obj");
		
	ResultSet kenh = obj.getkenh();

	ResultSet dvkd = obj.getdvkd();

	ResultSet ddkd =obj.getRsddkd();
	ResultSet dvdl = obj.getdvdl();

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
			$( ".days" ).datepicker({			    
					changeMonth: true,
					changeYear: true				
			});
            $(".button").hover(function(){
                $(".button img")
                .animate({top:"-10px"}, 200).animate({top:"-4px"}, 200) // first jump
                .animate({top:"-7px"}, 100).animate({top:"-4px"}, 100) // second jump
                .animate({top:"-6px"}, 100).animate({top:"-4px"}, 100); // the last jump
            });
        }); 
		$(document).ready(function(){
            $(".button1").hover(function(){
                $(".button1 img")
                .animate({top:"-10px"}, 200).animate({top:"-4px"}, 200) // first jump
                .animate({top:"-7px"}, 100).animate({top:"-4px"}, 100) // second jump
                .animate({top:"-6px"}, 100).animate({top:"-4px"}, 100); // the last jump
            });
        });
		
    </script>

<script type="text/javascript"
	src="../scripts/report-js/action-report.js"></script>
<script language="javascript" type="text/javascript">	
function seach()
{
	document.forms['frm'].action.value= 'seach';
	document.forms["frm"].submit();
}
	function submitform() {
//		if (document.forms["frm"]["Sdays"].value.length == 0) {
//			setErrors("Vui l??ng ch???n ng??y b???t ?????u");
//			return ;
//		}
//		if (document.forms["frm"]["Edays"].value.length == 0) {
//			setErrors("Vui l??ng ch???n ng??y k???t th??c");
//			return ;
//		}

		var fieldShow = document.getElementsByName("fieldsHien");
		for ( var i = 0; i < fieldShow.length; ++i) {
			fieldShow.item(i).checked = true;
		}
		
		document.forms['frm'].action.value= 'Taomoi';
		document.forms["frm"].submit();
		reset();
	//	for ( var i = 0; i < fieldShow.length; ++i) {
	//		fieldShow.item(i).checked = false;
	//	}
	}
	function setErrors(errorMsg) {
		var errors = document.getElementById("errors");
		errors.value = errorMsg;
	}
	function reset() {
		var fieldShow = document.getElementsByName("fieldsHien");
		var fieldHidden = document.getElementsByName("fieldsAn");
		for ( var i = 0; i < fieldShow.length; ++i) {
			fieldShow.item(i).checked = false;
		}
		for ( var i = 0; i < fieldHidden.length; ++i) {
			fieldHidden.item(i).checked = false;
		}
	};
</script>
</head>
<BODY leftmargin="0" bottommargin="0" topmargin="0" rightmargin="0">
	<form name="frm" method="post"
		action="../../Srperfomance">
		<input type="hidden" name="action" value='1'> <input
			type="hidden" name="userId" value='<%=userId%>'>
		<div id="main" style="width: 99%; padding-left: 2px">
			<div align="left" id="header" style="width: 100%; float: none">
				<div style="width: 70%; padding: 5px; float: left"
					class="tbnavigation">Qu???n l?? ch??? ti??u &#62; B??o c??o &#62; Th???c hi???n ch??? ti??u c???a nh??n vi??n b??n h??ng </div>
				<div align="right" style="padding: 5px" class="tbnavigation">
					<%=Utility.GLanguage("Ch??o m???ng b???n",session,jedis) %> 	<%=userTen%></div>
			</div>
			<div align="left" style="width: 100%; float: none; clear: left">
				<fieldset>
					<legend class="legendtitle"> <%=Utility.GLanguage("B??o l???i nh???p li???u",session,jedis) %></legend>
					<textarea id="errors" name="errors" rows="1" style="width: 100% ; color:#F00 ; font-weight:bold"><%=obj.getMsg()%></textarea>
				</fieldset>
			</div>
			<div align="left"
				style="width: 100%; float: none; clear: left; font-size: 0.7em">
				<fieldset>
					<legend class="legendtitle">Th???c hi???n ch??? ti??u nh??n vi??n b??n h??ng
					</legend>
					<div style="width: 100%; float: none" align="left">
						<div style="width: 100%; float: none" align="left"
							class="plainlabel">

							<TABLE width="70%" cellpadding="6" cellspacing="0">
								<TR>
									<TD class="plainlabel">Ch???n n??m</TD>
									
									<TD class="plainlabel">
									<SELECT name = "year">
										<%
									  
  										int n;
  										for(n=2008;n<2015;n++){
  										if(obj.getYear().equals(""+n)){
	  									%>
										<option value=<%=n%> selected="selected" > <%=n%></option> 
										<%
	 										}else{
	 									%>
										<option value=<%=n%> ><%=n%></option> 
										<%
	 										}
	 									 }
 										%>

										</SELECT>									
									</TD>
									
									<TD class="plainlabel">Ch???n th??ng</TD>
									<TD>
										<SELECT name = "month">
										<%if(obj.getMonth().equals("01")){ %>
											<option value = "01" selected>T1</option>
										<%}else{ %>
											<option value = "01">T1</option>
										<%} %>

										<%if(obj.getMonth().equals("02")){ %>
											<option value = "02" selected>T2</option>
										<%}else{ %>
											<option value = "02">T2</option>
										<%} %>

										<%if(obj.getMonth().equals("03")){ %>
											<option value = "03" selected>T3</option>
										<%}else{ %>
											<option value = "03">T3</option>
										<%} %>

										<%if(obj.getMonth().equals("04")){ %>
											<option value = "04" selected>T4</option>
										<%}else{ %>
											<option value = "04">T4</option>
										<%} %>

										<%if(obj.getMonth().equals("05")){ %>
											<option value = "05" selected>T5</option>
										<%}else{ %>
											<option value = "05">T5</option>
										<%} %>

										<%if(obj.getMonth().equals("06")){ %>
											<option value = "06" selected>T6</option>
										<%}else{ %>
											<option value = "06">T6</option>
										<%} %>

										<%if(obj.getMonth().equals("07")){ %>
											<option value = "07" selected>T7</option>
										<%}else{ %>
											<option value = "07">T7</option>
										<%} %>

										<%if(obj.getMonth().equals("08")){ %>
											<option value = "08" selected>T8</option>
										<%}else{ %>
											<option value = "08">T8</option>
										<%} %>
										
										<%if(obj.getMonth().equals("09")){ %>
											<option value = "09" selected>T9</option>
										<%}else{ %>
											<option value = "09">T9</option>
										<%} %>
										
										<%if(obj.getMonth().equals("10")){ %>
											<option value = "10" selected>T10</option>
										<%}else{ %>
											<option value = "10">T10</option>
										<%} %>
										
										<%if(obj.getMonth().equals("11")){ %>
											<option value = "11" selected>T11</option>
										<%}else{ %>
											<option value = "11">T11</option>
										<%} %>
										
										<%if(obj.getMonth().equals("12")){ %>
											<option value = "12" selected>T12</option>
										<%}else{ %>
											<option value = "12">T12</option>
										<%} %>
										
										

										</SELECT>
									
									</TD>
								</TR>
								<TR>
									<TD class="plainlabel"><%=Utility.GLanguage("K??nh b??n h??ng",session,jedis) %></TD>
									<TD class="plainlabel"><select name="kenhId" id="kenhId"
										onchange="seach();">
											<option value="" selected>All</option>
											<%
												if (kenh != null)
													while (kenh.next()) {
														if (kenh.getString("pk_seq").equals(obj.getkenhId())) {
											%>
											<option value="<%=kenh.getString("pk_seq")%>" selected><%=kenh.getString("diengiai")%></option>
											<%
												} else {
											%>
											<option value="<%=kenh.getString("pk_seq")%>"><%=kenh.getString("diengiai")%></option>
											<%
												}
													}
											%>
									</select></TD>
								</TR>
								
								
								<TR>
									<TD class="plainlabel">????n v??? kinh doanh</TD>
									<TD class="plainlabel"><select name="dvkdId" id="dvkdId"
										onchange="seach();">
											<option value="" selected>All</option>
											<%
												if (dvkd != null)
													while (dvkd.next()) {
														if (dvkd.getString("pk_seq").equals(obj.getdvkdId())) {
											%>
											<option value="<%=dvkd.getString("pk_seq")%>" selected><%=dvkd.getString("diengiai")%></option>
											<%
												} else {
											%>
											<option value="<%=dvkd.getString("pk_seq")%>"><%=dvkd.getString("diengiai")%></option>
											<%
												}
													}
											%>
									</select></TD>	
									
								</TR>
								<TR>
									<TD class="plainlabel"><%=Utility.GLanguage("NH??N VI??N B??N H??NG",session,jedis) %></TD>
									<TD class="plainlabel"><select name="ddkdId" id="ddkdId"
										onchange="seach();">
											<option value="" selected>All</option>
											<%
												if (ddkd != null)
													while (ddkd.next()) {
														if (ddkd.getString("pk_seq").equals(obj.getDdkd())) {
											%>
											<option value="<%=ddkd.getString("pk_seq")%>" selected><%=ddkd.getString("ten")%></option>
											<%
												} else {
											%>
											<option value="<%=ddkd.getString("pk_seq")%>"><%=ddkd.getString("ten")%></option>
											<%
												}
													}
											%>
									</select></TD>	
									
								</TR>
								
								
								<TR>
								<TR>
									<td colspan="4"><a class="button"
										href="javascript:submitform();"> <img style="top: -4px;"
											src="../images/button.png" alt=""> T???o b??o c??o </a>
									</td>
								</TR>
							</TABLE>
						</div>
						<hr>
						<div style="width: 100%; float: none;display: none">
							<div style="width: 30%; float: left">
								<fieldset style="min-height: 200px">
									<legend class="legendtitle"> D??? li???u hi???n th??? </legend>
									<TABLE width="100%" border="0" cellspacing="1" cellpadding="4"
										id="tbShowFields">
										<tbody id="ShowFields">
											<tr class="tbheader">
												<th></th>
												<th align="center">Ch???n ???n</th>
											</tr>
											<tr class="tbdarkrow">
												<td><%=Utility.GLanguage("NH??N VI??N B??N H??NG",session,jedis) %></td>
												<td align="center"><input name="fieldsHien"
													type="checkbox" value="Sales_Rep">
												</td>
											</tr>
											<tr class="tblightrow">
												<td>????n v??? kinh doanh</td>
												<td align="center"><input name="fieldsHien"
													type="checkbox" value="Business_unit">
												</td>
											</tr>
											<tr class="tbdarkrow">
												<td>Ch??? ti??u doanh s??? b??n trong th??ng </td>
												<td align="center"><input name="fieldsHien"
													type="checkbox" value="Monthly_Target">
												</td>
											</tr>
											<tr class="tblightrow">
												<td>Doanh s??? th???c ?????t</td>
												<td align="center"><input name="fieldsHien"
													type="checkbox" value="Archive">
												</td>
											</tr>
											<tr class="tbdarkrow">
												<td>% </td>
												<td align="center"><input name="fieldsHien"
													type="checkbox" value="%">
												</td>
											</tr>
											<tr class="tblightrow">
												<td>Trung b??nh ????n h??ng tr??n ng??y</td>
												<td align="center"><input name="fieldsHien"
													type="checkbox" value="Avg.Order/Day">
												</td>
											</tr>

											<tr class="tbdarkrow">
												<td>Trung b??nh s???n ph???m tr??n ????n h??ng</td>
												<td align="center"><input name="fieldsHien"
													type="checkbox" value="Avg.SKU/Order">
												</td>
											</tr>
											<tr class="tblightrow">
												<td>Doanh s??? trung b??nh tr??n m???t ????n h??ng</td>
												<td align="center"><input name="fieldsHien"
													type="checkbox" value="Avg.Vaue_Per_Order">
												</td>
											</tr>
											<tr class="tbdarkrow">
												<td>Doanh s??? trung b??nh c???a 1 s???n ph???m</td>
												<td align="center"><input name="fieldsHien"
													type="checkbox" value="Avg.Value_Per_SKU">
												</td>
											</tr>
											<tr class="tbdarkrow">
												<td>TB ph???i b??n / ng??y</td>
												<td align="center"><input name="fieldsHien"
													type="checkbox" value="Avg.Value_Per_Day">
												</td>
											</tr>
											
									</tbody>
									</TABLE>
								</fieldset>
							</div>
							<div
								style="width: 35px; float: left; min-height: 200px; vertical-align: middle;display: none"
								align="center">
								<br> <br> <br> <img src="../images/Back30.png"
									border="0" class="imageClick" onClick="ChuyenSangPhai();"><br />
								<br> <br> <img src="../images/Next30.png" border="0"
									class="imageClick" onClick="ChuyenSangTrai();">
							</div>
							<div style="width: 30%; float: left;display: none">
								<fieldset style="min-height: 200px;">
									<legend class="legendtitle"> D??? li???u ???n </legend>
									<TABLE width="100%" border="0" cellspacing="1" cellpadding="4"
										id="tbAllFields">
										<tbody id="AllFields">
											<tr class="tbheader">
												<th></th>
												<th align="center">Ch???n hi???n th???</th>
											</tr>

											<tr class="tbdarkrow">
												<td>M?? Chi nh??nh / NPP</td>
												<td align="center"><input name="fieldsAn"
													type="checkbox" value="Distributor_Code">
												</td>
											</tr>											
											
										</tbody>
									</TABLE>
								</fieldset>
							</div>


						</div>
					</div>
				</fieldset>
			</div>
		</div>
		<br /> <br /> <br /> <br />
	</form>
</body>
</HTML>
<%

	if(kenh != null) kenh.close();
	if(dvkd != null) dvkd.close();
	if(ddkd != null) ddkd.close();
	if(dvdl != null) dvdl.close(); 
	obj.DBclose();
%>