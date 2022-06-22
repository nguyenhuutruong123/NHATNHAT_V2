<%@page import="java.util.Formatter"%>
<%@page import="geso.dms.center.util.Utility"%>
<%@page import="java.sql.ResultSet"%>
<%@page import="java.text.DecimalFormat"%>
<%@page import="java.text.NumberFormat"%>
<%@page import="geso.dms.center.beans.stockintransit.IStockintransit"%>
<%@page import="java.util.Calendar"%>
<%@page import="java.sql.Date"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<% NumberFormat formatter = new DecimalFormat("#,###,###"); %>
<%

Utility util = new Utility();
	String userId = (String) session.getAttribute("userId");
	String userTen = (String) session.getAttribute("userTen");
	String groupCustomer = (String)session.getAttribute("groupCustomer");
	String gorupSKU = (String)session.getAttribute("gorupSKU");
	IStockintransit obj = (IStockintransit) session.getAttribute("obj");
	ResultSet npp = obj.getnpp();
	ResultSet dvkd = obj.getdvkd();
	ResultSet ddkd = obj.getRsddkd();
	ResultSet ETCRs = obj.getETCRs();
	ResultSet OTCRs = obj.getOTCRs();
	ResultSet HdKhac = obj.getHdKhacRs();
	ResultSet kh = obj.getkh();	
	ResultSet totalRs = obj.getTotalRs();
	ResultSet khors = obj.getKhors();	
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
<LINK rel="stylesheet" type="text/css" media="screen"href="../css/tabs.css">
<LINK rel="stylesheet" type="text/css" media="screen" href="../css/tabs-panes.css">
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
</script>

<script type="text/javascript">
	 
		$(document).ready(function() {

		    //When page loads...
		    $(".tab_content").hide(); //Hide all content
		    var index = $("ul.tabs li.current").show().index(); 
		    $(".tab_content").eq(index).show();
		    //On Click Event
		    $("ul.tabs li").click(function() {
		  
		        $("ul.tabs li").removeClass("current"); //Remove any "active" class
		        $(this).addClass("current"); //Add "active" class to selected tab
		        $(".tab_content").hide(); //Hide all tab content  
		        var activeTab = $(this).find("a").attr("href"); //Find the href attribute value to identify the active tab + content  
		        $(activeTab).show(); //Fade in the active ID content
		        return false;
		    });

		});
</script>

<script type="text/javascript"
	src="../scripts/report-js/action-report.js"></script>
<script language="javascript" type="text/javascript">
	function submitform() 
	{
		var fieldShow = document.getElementsByName("fieldsHien");
		for ( var i = 0; i < fieldShow.length; ++i) {
			fieldShow.item(i).checked = true;
		}
		document.forms['frm'].action.value = 'Taomoi';
		document.forms["frm"].submit();
		reset();
	}

	function search()	
	 {		
		if (document.forms["frm"]["Sdays"].value.length == 0) {
			setErrors("Vui lòng chọn ngày bắt đầu");
			return;
		}
		if (document.forms["frm"]["Edays"].value.length == 0) {
			setErrors("Vui lòng chọn ngày kết thúc");
			return;
		}	
		
		var fieldShow = document.getElementsByName("fieldsHien");		
		for ( var i = 0; i < fieldShow.length; ++i) {
			fieldShow.item(i).checked = true;
		}	
		
	 	document.forms['frm'].action.value= 'search';
	 	document.forms['frm'].submit();
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

<link href="../css/select2.css" rel="stylesheet" />
<script src="../scripts/select2.js"></script>
<script>
		$(document).ready(function()
		{
			$(".select2").select2();
		});
	</script>

</head>
<BODY leftmargin="0" bottommargin="0" topmargin="0" rightmargin="0">
	<form name="frm" method="post" action="../../DailySecondarySalesTTSvl">
	<% redis.clients.jedis.Jedis jedis =  geso.dms.center.util.Utility.getJedis(); %><% geso.dms.center.util.Csrf csdr=new geso.dms.center.util.Csrf(request,response,true,false,true);%>
 <input type="hidden" name="<%=csdr.get_tokenName() %>" value='<%=csdr.get_tokenValue() %>'>
	
		<input type="hidden" name="action" value='1'> 
		<input type="hidden" name="userId" value='<%=obj.getuserId()%>'>
		<div id="main" style="width: 99%; padding-left: 2px">
			<div align="left" id="header" style="width: 100%; float: none">
				<div style="width: 70%; padding: 5px; float: left" class="tbnavigation">Quản lý bán hàng &#62; Báo cáo &#62; Doanh số bán hàng</div>
				<div align="right" style="padding: 5px" class="tbnavigation"><%=Utility.GLanguage("Chào mừng bạn",session,jedis) %> <%=obj.getuserTen()%></div>
			</div>
			<div align="left" style="width: 100%; float: none; clear: left">
				<FIELDSET>
					<LEGEND class="legendtitle"><%=Utility.GLanguage("Báo lỗi nhập liệu",session,jedis) %></LEGEND>
					<textarea id="errors" readonly="readonly" name="errors" rows="1"
						style="width: 100%; color: #F00; font-weight: bold">
						<%= obj.getMsg() %>
					</textarea>
				</FIELDSET>
			</div>
			<div align="left"
				style="width: 100%; float: none; clear: left; font-size: 0.7em">
				<fieldset>
					<legend class="legendtitle"> Doanh số bán hàng </legend>
					<div style="width: 100%; float: none" align="left">
						<div style="width: 100%; float: none" align="left"
							class="plainlabel">
							<TABLE width="100%" cellpadding="6px" cellspacing="0px"
								style="margin-top: 5px">
								<TR>
									<TD class="plainlabel"><%=Utility.GLanguage("Từ ngày",session,jedis) %></TD>
									<TD class="plainlabel">
										<input type="text" class="days" name="Sdays" value='<%=obj.gettungay()%>' style="width: 250px" /></TD>
									<TD class="plainlabel"><%=Utility.GLanguage("Đến ngày",session,jedis) %></TD>
									<td>
										<input type="text" class="days" name="Edays" value='<%=obj.getdenngay()%>' style="width: 250px" /></td>
								</TR>
								<TR>
									<TD class="plainlabel"><%=Utility.GLanguage("NHÂN VIÊN BÁN HÀNG",session,jedis) %></TD>
									<TD class="plainlabel">
										<select name="ddkdId" id="ddkdId" class="select2" style="width: 250px" onchange="search();">
											<option value="">All</option>
											<%if (ddkd != null)
													while (ddkd.next()) 
													{
														if (ddkd.getString("pk_seq").equals(obj.getDdkd())) {%>
														<option value="<%=ddkd.getString("pk_seq")%>" selected><%=ddkd.getString("ten")%></option>
													<%} else {%>
														<option value="<%=ddkd.getString("pk_seq")%>"><%=ddkd.getString("ten")%></option>
											<%}}%>
									</select></TD>
									<TD class="plainlabel">Khách hàng</TD>
									<TD class="plainlabel" colspan="3"><select name="khId"
										class="select2" id="khId" style="width: 230px"
										onchange="search();">
											<option value="" selected>All</option>
											<% if(kh != null)
											   while(kh.next()){
											   if(kh.getString("pk_seq").equals(obj.getkhId()))
											   { %>
											<option value="<%= kh.getString("pk_seq") %>" selected><%=kh.getString("ten") %></option>
											<%}else { %>
											<option value="<%= kh.getString("pk_seq") %>"><%=kh.getString("ten") %></option>
											<%} }%>
									</select></TD>

									
								</TR>
								<tr>
								
								<TD class="plainlabel">Kho</TD>
									<TD class="plainlabel" colspan="3"><select name="khoid"
										class="select2" id="khoid" style="width: 230px"
										onchange="search();">
											<option value="" selected>All</option>
											<% if(khors != null)
											   while(khors.next()){
											   if(khors.getString("pk_seq").equals(obj.getkhoId()))
											   { %>
											<option value="<%= khors.getString("pk_seq") %>" selected><%=khors.getString("ten") %></option>
											<%}else { %>
											<option value="<%= khors.getString("pk_seq") %>"><%=khors.getString("ten") %></option>
											<%} }%>
									</select></TD>
									
								</tr>
								<%
					double DoanhSo=0;
					double Thue=0;
					double DoanhThu=0;
					while(totalRs!=null && totalRs.next())
					{
						DoanhSo=totalRs.getDouble("AVAT");
						Thue=totalRs.getDouble("AVAT_CK");
						DoanhThu=totalRs.getDouble("DOANHTHU");
					}
					
					%>
								<TR>
									<TD class="plainlabel" colspan="6"></TD>
								<TR>
									<TD class="plainlabel">Doanh số</TD>
									<td class="plainlabel"><input type="text" name="ds"
										size="6" value="<%= formatter.format(DoanhSo   ) %>"></td>
									<TD class="plainlabel">Thuế</TD>
									<td class="plainlabel"><input type="text" name="ck"
										size="6" value="<%= formatter.format( Thue ) %>"></td>
									<TD class="plainlabel">Doanh thu</TD>
									<td class="plainlabel"><input type="text" name="dt"
										size="6" value="<%= formatter.format(  DoanhThu) %>"></td>

								</TR>

								<TR>
									<TD class="plainlabel">Mức lấy</TD>
									<TD class="plainlabel">
										<%
										if(obj.gettype().equals("0")){
											%> <input type="radio" name="type" value="1"
										onchange="seach();" /> Chi tiết &nbsp; &nbsp; <input
										type="radio" name="type" value="0" checked="checked"
										onchange="seach();" />Tổng quát <%
										}
										else
										{
											%> <input type="radio" name="type" value="1"
										checked="checked" onchange="seach();" />Chi tiết &nbsp;
										&nbsp; <input type="radio" name="type" value="0"
										onchange="seach();" /> Tổng quát <%
										}
									%>

									</TD>

								</TR>
								<TR>
									<TD class="plainlabel"></TD>
									<TD class="plainlabel" colspan="5">
										<%
							if(obj.getCn1().equals("1")) {%> <input type="checkbox"
										name="cn1" value="1" checked="checked" /> Lấy số liệu CN1
										bán tại khu vực &nbsp; &nbsp; <% }else {%> <input
										type="checkbox" name="cn1" value="1" /> Lấy số liệu CN1 bán
										tại khu vực &nbsp; &nbsp; <%} %>

									</TD>

								</TR>

								<TR>
									<td class="plainlabel"><a class="button"
										href="javascript:submitform();"> <img style="top: -5px;"
											src="../images/button.png" alt=""><%=Utility.GLanguage("Tạo báo cáo",session,jedis) %>
									</a></td>
									<td class="plainlabel"><a class="button"
										href="javascript:search();"> <img style="top: -5px;"
											src="../images/button.png" alt=""><%=Utility.GLanguage("Tìm kiếm",session,jedis) %>
									</a></td>
								</TR>
							</TABLE>
							</div>
							</div>
					</FIELDSET>
							<!--Chỉnh sửa  -->
							<% String current=""; %>
							<table width="100%">
								<tr>
									<td>
										<ul class="tabs">
										
											<li
												<%=obj.getActiveTab().equals("0") ? "class='current'" : "" %>><a
												href="#tabOTC">OTC</a>
											</li>
												
											<li
												<%=obj.getActiveTab().equals("1") ? "class='current'" : "" %>><a
												href="#tabETC"> ETC</a>
											</li>
											<li
												<%=obj.getActiveTab().equals("2") ? "class='current'" : "" %>><a
												href="#tabHdKhac"> Hóa đơn khác</a>
											</li>

										</ul>

										<div class="panes">
											

											<div id="tabOTC" class="tab_content">
												<TABLE width="100%" border="0" cellspacing="1px" cellpadding="3px">
													<TR class="tbheader">
														<th align="center">STT</th>
														<th align="center"><%=Utility.GLanguage("NHÂN VIÊN BÁN HÀNG",session,jedis) %></th>
														<th align="center">Mã Fast</th>
														<th align="center">Tên khách hàng</th>
														<th align="center">Doanh số</th>
														<th align="center">Chiết khấu</th>
														<th align="center">Doanh thu</th>
													</TR>
													<%
												if(OTCRs!=null){
													try{
														int m = 1;
														String lightrow = "tblightrow";
														String darkrow = "tbdarkrow";
													while (OTCRs.next()){
														double avat_ck = OTCRs.getDouble("avat_ck");
														double avat = OTCRs.getDouble("avat");
														double doanhthu= avat-avat_ck;
															if (m % 2 != 0) {%>
													<TR class=<%=lightrow%>>
														<%} else {%>
													
													<TR class=<%= darkrow%>>
														<%}%>
														<Td align="center"><%= m %></Td>
														<Td align="left"><%= OTCRs.getString("ddkdTen") %></Td>
														<Td align="left"><%= OTCRs.getString("khMa") %></Td>
														<Td align="left"><%= OTCRs.getString("khTen") %></Td>
														<Td align="right"><%= formatter.format(avat) %></Td>
														<Td align="right"><%= formatter.format(avat_ck) %></Td>
														<Td align="right"><%= formatter.format(doanhthu) %></Td>
														<% m++;}}catch(Exception e){}} %>
													</TR>
												</TABLE>
											</div>
											
											<div id="tabETC" class="tab_content">
												<TABLE width="100%" border="0" cellspacing="1px"
													cellpadding="3px">
													<TR class="tbheader">
														<th align="center">STT</th>
														<th align="center"><%=Utility.GLanguage("NHÂN VIÊN BÁN HÀNG",session,jedis) %></th>
														<th align="center">Mã Fast</th>
														<th align="center">Tên khách hàng</th>
														<th align="center">Doanh số</th>
														<th align="center">Chiết khấu</th>
														<th align="center">Doanh thu</th>

													</TR>
													<%
												if(ETCRs!=null){
													try{
														int m = 1;
														String lightrow = "tblightrow";
														String darkrow = "tbdarkrow";
														while (ETCRs.next()){
															
															double avat_ck = ETCRs.getDouble("avat_ck");
															double avat = ETCRs.getDouble("avat");
															double doanhthu= avat-avat_ck;
															if (m % 2 != 0) {%>
													<TR class=<%=lightrow%>>
														<%} else {%>
													
													<TR class=<%= darkrow%>>
														<%}%>
														<Td align="center"><%= m %></Td>
														<Td align="left"><%= ETCRs.getString("ddkdTen")==null?ETCRs.getString("nppTEN"):ETCRs.getString("ddkdTen") %></Td>
														<Td align="left"><%= ETCRs.getString("khMa") %></Td>
														<Td align="left"><%= ETCRs.getString("khTen") %></Td>
														<Td align="right"><%= formatter.format(avat) %></Td>
														<Td align="right"><%= formatter.format(avat_ck) %></Td>
														<Td align="right"><%= formatter.format(doanhthu) %></Td>


														<% m++;}}catch(Exception e){}} %>
													</TR>
												</TABLE>

											</div>
											
											<div id="tabHdKhac" class="tab_content">
												<TABLE width="100%" border="0" cellspacing="1px"
													cellpadding="3px">
													<TR class="tbheader">
														<th align="center">STT</th>
														<th align="center"><%=Utility.GLanguage("NHÂN VIÊN BÁN HÀNG",session,jedis) %></th>
														<th align="center">Mã Fast</th>
														<th align="center">Tên khách hàng</th>
														<th align="center">Doanh số</th>
														<th align="center">Chiết khấu</th>
														<th align="center">Doanh thu</th>

													</TR>
												 <%
												if(HdKhac!=null){
													try{
														int m = 1;
														String lightrow = "tblightrow";
														String darkrow = "tbdarkrow";
														while (HdKhac.next()){
															
															double avat_ck = HdKhac.getDouble("avat_ck");
															double avat = HdKhac.getDouble("avat");
															double doanhthu= avat-avat_ck;
															if (m % 2 != 0) {%>
													<TR class=<%=lightrow%>>
														<%} else {%>
													
													<TR class=<%= darkrow%>>
														<%}%>
														<Td align="center"><%= m %></Td>
														<Td align="left"><%= HdKhac.getString("ddkdTen")==null?HdKhac.getString("nppTEN"):HdKhac.getString("ddkdTen") %></Td>
														<Td align="left"><%= HdKhac.getString("khMa") %></Td>
														<Td align="left"><%= HdKhac.getString("khTen") %></Td>
														<Td align="right"><%= formatter.format(avat) %></Td>
														<Td align="right"><%= formatter.format(avat_ck) %></Td>
														<Td align="right"><%= formatter.format(doanhthu) %></Td>


														<% m++;}}catch(Exception e){}} %> 
													</TR>
												</TABLE>

											</div>
											
											
										</div>
									</td>
								</tr>
							</table>
						</div>
					</div>
	</form>
	<%
		if(npp!=null)npp.close();
		if(ddkd!=null)ddkd.close();
		if(obj!=null){obj.DBclose();
		obj = null;}
		session.setAttribute("obj", null);
	%>
</body>
</HTML>