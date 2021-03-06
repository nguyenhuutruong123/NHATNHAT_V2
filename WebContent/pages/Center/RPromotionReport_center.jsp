<%@page import="geso.dms.center.util.Utility"%>
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
	ResultSet vung = obj.getvung();
	ResultSet khuvuc = obj.getkhuvuc();
	ResultSet npp = obj.getnpp();	
	ResultSet programs = obj.getRsPrograms();
%>
<% Utility util = new Utility(); %>
<%String url = util.getChuyenNguUrl("PromotionReportTTSvl", "",session); %>
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
		document.forms['frm'].action.value = "search";
		document.forms["frm"].submit();
	}
	function submitform() {
		var fieldShow = document.getElementsByName("fieldsHien");
		for ( var i = 0; i < fieldShow.length; ++i) {
			fieldShow.item(i).checked = true;
		}

		document.forms['frm'].action.value = "Taomoi";
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
<link href="../css/select2.css" rel="stylesheet" />
<script src="../scripts/select2.js"></script>
<script>
$(document).ready(function()
{
	$("#nppId").select2();
	$("#vungId").select2();
	$("#khuvucId").select2();
	$("#kenhId").select2();
	$("#nhanhangId").select2();
	$("#chungloaiId").select2();
	$("#programs").select2();
	$("#dvkdId").select2();
	$("#gsbhId").select2();
	
	$("#year").select2();
	$("#month").select2();
});
</script>


</head>
<BODY leftmargin="0" bottommargin="0" topmargin="0" rightmargin="0">
	<form name="frm" method="post" action="../../PromotionReportTTSvl">
	<% redis.clients.jedis.Jedis jedis =  geso.dms.center.util.Utility.getJedis(); %><% geso.dms.center.util.Csrf csdr=new geso.dms.center.util.Csrf(request,response,true,false,true);%>
 <input type="hidden" name="<%=csdr.get_tokenName() %>" value='<%=csdr.get_tokenValue() %>'>
	
		<input type="hidden" name="action" value='1'> <input
			type="hidden" name="userId" value='<%=obj.getuserId()%>'>
		<div id="main" style="width: 99%; padding-left: 2px">
			<div align="left" id="header" style="width: 100%; float: none">
				<div style="width: 70%; padding: 5px; float: left"
					class="tbnavigation"><%=url %></div>
				<div align="right" style="padding: 5px" class="tbnavigation">
					<%=Utility.GLanguage("Ch??o m???ng b???n",session,jedis) %> 
					<%=obj.getuserTen()%></div>
			</div>
			<div align="left" style="width: 100%; float: none; clear: left">
				<fieldset>
					<legend class="legendtitle"><%=Utility.GLanguage("B??o l???i nh???p li???u",session,jedis) %></legend>
					<textarea id="errors" name="errors" rows="2"  style="width: 100%;text-align: left; color:#F00 ; font-weight:bold">
						<%=Utility.GLanguage(obj.getMsg(),session,jedis) %></textarea>
				</fieldset>
			</div>
			<div align="left" style="width: 100%; float: none; clear: left;font-size: 0.7em">
				<fieldset>
					<legend class="legendtitle"><%=Utility.GLanguage("B??o c??o xu???t khuy???n m??i",session,jedis) %></legend>
					<div style="width: 100%; float: none" align="left">
						<!-- <div style="width: 100%; float: none" align="left" class="plainlabel"> -->
							<TABLE width="100%" cellpadding="6" cellspacing="0">
								<TR>
									<TD class="plainlabel"><%=Utility.GLanguage("Ch???n n??m",session,jedis) %></TD>
									<TD class="plainlabel">
										<SELECT name = "year" id="year" onchange="seach();">
										<%
											int namhientai = Utility.getNamHienTai();
	  										int n;
	  										for(n=namhientai - 3;n<namhientai + 5;n++){
	  										if(obj.getYear().equals(""+n)){
	  									%>
										<option value=<%=n%> selected="selected" > <%=n%></option> 
										<% } else{ %>
										<option value=<%=n%> ><%=n%></option> 
										<% } } %>
										</SELECT>									
									</TD>
									
									<TD class="plainlabel"><%=Utility.GLanguage("Ch???n th??ng",session,jedis) %></TD>
									<TD class="plainlabel">
										<SELECT name = "month" id="month" onchange="seach();">
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
									<TD class="plainlabel"><%=Utility.GLanguage("Ch????ng tr??nh khuy???n m??i",session,jedis) %></TD>
									<TD class="plainlabel">
										<select name="programs"  onchange="seach();"  id="programs"  style="width:250px">
											<option value="">All</option>
											<%
												if(programs!=null){
													while(programs.next()){
														if(obj.getPrograms().equals(programs.getString("pk_Seq"))){%>
															<option value="<%=programs.getString("pk_Seq")%>" selected="selected" >
																<%=programs.getString("DIENGIAI") %></option>															
														<%}else{%>
															<option value="<%=programs.getString("pk_Seq")%>" >
																<%=programs.getString("DIENGIAI") %></option>	
														<%}
													}
												}
											%>
										</select>
									</TD>
									<TD class="plainlabel"><%=Utility.GLanguage("K??nh b??n h??ng",session,jedis) %></TD>
									<TD class="plainlabel">
										<select name="kenhId" id="kenhId" onchange="seach();"  id="kenhId"  style="width:250px">
											<option value="" selected>All</option>
											<%if (kenh != null)
													while (kenh.next()) {
														if (kenh.getString("pk_seq").equals(obj.getkenhId())) {%>
														<option value="<%=kenh.getString("pk_seq")%>" selected><%=kenh.getString("diengiai")%></option>
											<%} else { %>
												<option value="<%=kenh.getString("pk_seq")%>"><%=kenh.getString("diengiai")%></option>
											<%}}%>
										</select>
									</TD>											
								</TR>
								
								<TR>									
									<TD class="plainlabel"><%=Utility.GLanguage("Mi???n",session,jedis) %></TD>
									<TD class="plainlabel">
										<select name="vungId" id="vungId" onchange="seach();"    style="width:250px">
											<option value="" selected>All</option>
											<%
												if (vung != null)
													while (vung.next()) {
														if (vung.getString("pk_seq").equals(obj.getvungId())) {
											%>
											   <option value="<%=vung.getString("pk_seq")%>" selected><%=vung.getString("ten")%></option>
											   <%
											   	} else {
											   %>
											   <option value="<%=vung.getString("pk_seq")%>"><%=vung.getString("ten")%></option>
											<%
												}
													}
											%>
										</select>
										</TD>
									<TD class="plainlabel"><%=Utility.GLanguage("Khu v???c",session,jedis) %></TD>
									<TD class="plainlabel">
									<select name="khuvucId" id="khuvucId" onchange="seach();"   style="width:250px">
											<option value="" selected>All</option>
											<%
												if (khuvuc != null)
													while (khuvuc.next()) {
														if (khuvuc.getString("pk_seq").equals(obj.getkhuvucId())) {
											%>
											   <option value="<%=khuvuc.getString("pk_seq")%>" selected><%=khuvuc.getString("ten")%></option>
											   <%
											   	} else {
											   %>
											   <option value="<%=khuvuc.getString("pk_seq")%>"><%=khuvuc.getString("ten")%></option>
											<%
												}
													}
											%>
									</select>
									</TD>
								</TR>		
								
								<TR>
									<TD class="plainlabel"><%=Utility.GLanguage("Nh?? ph??n ph???i",session,jedis) %></TD>
									<TD class="plainlabel" colspan='4'><select name="nppId" id="nppId" onchange="seach();"  style="width:250px">
											<option value="" selected>All</option>
											<%
												if (npp != null)
													while (npp.next()) {
														if (npp.getString("pk_seq").equals(obj.getnppId())) {
											%>
											   <option value="<%=npp.getString("pk_seq")%>" selected><%=npp.getString("ten")%></option>
											   <%
											   	} else {
											   %>
											   <option value="<%=npp.getString("pk_seq")%>"><%=npp.getString("ten")%></option>
											<%
												}
													}
											%>
									</select>
									</TD>
								</TR>
								
								<TR>
									<TD class="plainlabel"> <%=Utility.GLanguage("Bao g???m ch????ng tr??nh ???ng h??ng tr?????c",session,jedis) %></TD>
									<TD class="plainlabel" colspan='3'>
									<%  if (obj.getUnghang().equals("1")){%>
										  	<input name="unghang" type="checkbox" value ="1" checked>
										<%} else {%>
											<input name="unghang" type="checkbox" value ="1">
										<%} %>
									 </TD>
								</TR>
								
								<TR>
									<TD class="plainlabel"> <%=Utility.GLanguage("Bao g???m ????n h??ng ch??a ch???t",session,jedis) %></TD>
									<TD class="plainlabel" colspan='3'>
									<%  if (obj.getDHChuachot().equals("1")){%>
										  	<input name="donhangchuachot" type="checkbox" value ="1" checked>
										<%} else {%>
											<input name="donhangchuachot" type="checkbox" value ="1">
										<%} %>
									 </TD>
								</TR>
									
								<TR>
									<TD class="plainlabel" colspan='4'>
									<a class="button" href="javascript:submitform();"> <img style="top: -4px;" src="../images/button.png" alt=""> T???o b??o c??o </a>
									</TD>
								</TR>
							</TABLE>
						</div>
						
						<div style="width: 100%; float: none;display: none">
							<div style="width: 30%; float: left">
								<fieldset style="min-height: 200px">
									<legend class="legendtitle"> <%=Utility.GLanguage("D??? li???u hi???n th???",session,jedis) %></legend>
									<TABLE width="100%" border="0" cellspacing="1" cellpadding="4"
										id="tbShowFields">
										<tbody id="ShowFields">
											<tr class="tbheader">
												<th></th>
												<th align="center"><%=Utility.GLanguage("Ch???n ???n",session,jedis) %></th>
											</tr>
											<tr class="tbdarkrow">
												<td><%=Utility.GLanguage("K??nh b??n h??ng",session,jedis) %></td>
												<td align="center"><input name="fieldsHien"
													type="checkbox" value="KenhBanHang"></td>
											</tr>
											<tr class="tblightrow">
												<td><%=Utility.GLanguage("Mi???n",session,jedis) %></td>
												<td align="center"><input name="fieldsHien"
													type="checkbox" value="ChiNhanh"></td>
											</tr>
											<tr class="tbdarkrow">
												<td><%=Utility.GLanguage("Khu v???c",session,jedis) %></td>
												<td align="center"><input name="fieldsHien"
													type="checkbox" value="KhuVuc"></td>
											</tr>
											<tr class="tblightrow">
												<td><%=Utility.GLanguage("Nh?? ph??n ph???i",session,jedis) %></td>
												<td align="center"><input name="fieldsHien"
													type="checkbox" value="NhaPhanPhoi"></td>
											</tr>	
											<tr class="tbdarkrow">
												<td><%=Utility.GLanguage("M?? ch????ng tr??nh",session,jedis) %></td>
												<td align="center"><input name="fieldsHien"
													type="checkbox" value="MaChuongTrinh"></td>
											</tr>

											<tr class="tblightrow">
												<td><%=Utility.GLanguage("Di???n gi???i",session,jedis) %></td>
												<td align="center"><input name="fieldsHien"
													type="checkbox" value="ChuongTrinhKhuyenMai"></td>
											</tr>

											<tr class="tbdarkrow">
												<td><%=Utility.GLanguage("M?? s???n ph???m",session,jedis) %></td>
												<td align="center"><input name="fieldsHien"
													type="checkbox" value="MaSanPham"></td>
											</tr>
											
											<tr class="tblightrow">
												<td> <%=Utility.GLanguage("T??n s???n ph???m",session,jedis) %></td>
												<td align="center"><input name="fieldsHien"
													type="checkbox" value="TenSanPham"></td>
											</tr>

											<tr class="tbdarkrow">
												<td><%=Utility.GLanguage("S??? l?????ng l???",session,jedis) %></td>
												<td align="center"><input name="fieldsHien"
													type="checkbox" value="SoLuong(Le)"></td>
											</tr>
											<tr class="tblightrow">
												<td><%=Utility.GLanguage("S??? ti???n",session,jedis) %></td>
												<td align="center"><input name="fieldsHien"
													type="checkbox" value="SoTien"></td>
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
									<legend class="legendtitle"> <%=Utility.GLanguage("D??? li???u ???n",session,jedis) %> </legend>
									<TABLE width="100%" border="0" cellspacing="1" cellpadding="4"
										id="tbAllFields">
										<tbody id="AllFields">
											<tr class="tbheader">
												<th></th>
												<th align="center"><%=Utility.GLanguage("Ch???n hi???n th???",session,jedis) %></th>
											</tr>
											<tr class="tbdarkrow">
												<td><%=Utility.GLanguage("Nh??n h??ng",session,jedis) %></td>
												<td align="center"><input name="fieldsAn"
													type="checkbox" value="NhanHang"></td>
											</tr>
											<tr class="tblightrow">
												<td><%=Utility.GLanguage("Ch???ng lo???i",session,jedis) %></td>
												<td align="center"><input name="fieldsAn"
													type="checkbox" value="ChungLoai"></td>
											</tr>
											<tr class="tbdarkrow">
												<td><%=Utility.GLanguage("M?? Chi nh??nh / NPP",session,jedis) %></td>
												<td align="center"><input name="fieldsAn"
													type="checkbox" value="MaNhaPhanPhoi"></td>
											</tr>
											<tr class="tblightrow">
												<td><%=Utility.GLanguage("NH??N VI??N B??N H??NG",session,jedis) %></td>
												<td align="center"><input name="fieldsAn"
													type="checkbox" value="DaiDienKinhDoanh"></td>
											</tr>

											<tr class="tbdarkrow">
												<td><%=Utility.GLanguage("T???nh/Th??nh",session,jedis) %></td>
												<td align="center"><input name="fieldsAn"
													type="checkbox" value="Tinh/Thanh"></td>
											</tr>
											<tr class="tblightrow">
												<td><%=Utility.GLanguage("Qu???n/Huy???n",session,jedis) %></td>
												<td align="center"><input name="fieldsAn"
													type="checkbox" value="Quan/Huyen"></td>
											</tr>

											<tr class="tbdarkrow">
												<td><%=Utility.GLanguage("M?? kh??ch h??ng",session,jedis) %></td>
												<td align="center"><input name="fieldsAn"
													type="checkbox" value="MaKhachHang"></td>
											</tr>
											
											
											<tr class="tblightrow">
												<td><%=Utility.GLanguage("T??n kh??ch h??ng",session,jedis) %></td>
												<td align="center"><input name="fieldsAn"
													type="checkbox" value="TenKhachHang"></td>
											</tr>
											<tr class="tbdarkrow">
												<td><%=Utility.GLanguage("Ng??y",session,jedis) %></td>
												<td align="center"><input name="fieldsAn"
													type="checkbox" value="Ngay"></td>
											</tr>		

											<tr class="tblightrow">
												<td><%=Utility.GLanguage("Lo???i ch????ng tr??nh",session,jedis) %></td>
												<td align="center"><input name="fieldsAn"
													type="checkbox" value="LoaiChuongTrinh"></td>
											</tr>
																						
											<tr class="tbdarkrow">
												<td><%=Utility.GLanguage("S??? l?????ng th??ng",session,jedis) %></td>
												<td align="center"><input name="fieldsAn"
													type="checkbox" value="SoLuong(Thung)"></td>
											</tr>											
										</tbody>
									</TABLE>
								</fieldset>
							</div>
		

						</div>
					<!-- </div> -->
				</fieldset>
			</div>
		</div>
		<!-- <br /> <br /> <br /> <br /> -->
	</form>
<%geso.dms.center.util.Utility.JedisClose(jedis); %>
<%
	if(kenh !=null) kenh.close();
	if(vung !=null) vung.close();	
	if(khuvuc !=null) khuvuc.close();
	if(npp !=null) npp.close();
	if(programs!=null) programs.close();
	if(obj!=null) { obj.DBclose(); obj = null;}
	session.setAttribute("obj", null);
%>
</body>
</HTML>