<%@page import="geso.dms.center.util.Utility"%>
<%@page import="java.sql.ResultSet"%>
<%@page import="geso.dms.center.beans.report.IBcTheKho"%>
<%@page import="java.util.Calendar"%>
<%@page import="java.sql.Date"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@page import="java.text.DecimalFormat"%>
<%@page import="java.text.NumberFormat"%>

<% NumberFormat formatter = new DecimalFormat("#,###,###"); %>
<% NumberFormat formatter_Gia = new DecimalFormat("#,###,###.####"); %>

<%
geso.dms.center.util.Utility util =  new geso.dms.center.util.Utility();
	String userId = (String) session.getAttribute("userId");
	String userTen = (String) session.getAttribute("userTen");
	IBcTheKho obj = (IBcTheKho) session.getAttribute("obj");
	ResultSet ddkd = obj.getDdkdRs();
	ResultSet khRs = obj.getKhRs();
	ResultSet spRs = obj.getSpRs();
	ResultSet khoRs = obj.getKhoRs();
	ResultSet hdRs = obj.getHoadonRs();
	ResultSet kbhRs = obj.getKbhRs();
	ResultSet totalRs = obj.getTotalRs();
		
	ResultSet soloRs= obj.getSoloRs();

	ResultSet vungRs = obj.getVungRs();
	ResultSet ttRs = obj.getTtRs();
	ResultSet nppRs = obj.getNppRs();
	ResultSet nhomRs = obj.getNhomRs();
	String url = util.getChuyenNguUrl("BcTheKhoSvl", "",session);
	
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
<script type="text/javascript" src="../scripts/phanTrang.js"></script>

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

<script type="text/javascript" src="../scripts/report-js/action-report.js"></script>
<script language="javascript" type="text/javascript">

function clearform()
{   
	document.forms["ckParkForm"].Sdays.value="";
	document.forms["ckParkForm"].Edays.value="";
	document.forms["ckParkForm"].khId.value="";
	document.forms["ckParkForm"].spId.value="";
	document.forms["ckParkForm"].ddkdId.value="";
	document.forms["ckParkForm"].kbhId.value="";
   document.forms["ckParkForm"].submit();
}

function submitform()
{   
	document.forms['ckParkForm'].action.value='';
   document.forms["ckParkForm"].submit();
}

function xuatExcel()
{
	var spId=document.getElementById("spId");
	
	if(spId.value.length==0)
		{
			alert ('vui lòng chọn sản phẩm !!');
		}
	else
		{
	document.forms['ckParkForm'].action.value= 'excel';
	document.forms['ckParkForm'].submit();
		}
}

function search()
{
	
var spId=document.getElementById("spId");
	
	if(spId.value.length==0)
		{
			alert ('vui lòng chọn sản phẩm !!');
		}
	else
		{
	document.forms['ckParkForm'].action.value= 'search';
	document.forms['ckParkForm'].submit();
		}
	
}



</script>

<link href="../css/select2.css" rel="stylesheet" />
<script src="../scripts/select2.js"></script>
<script>
     $(document).ready(function() { $("select").select2(); });
     
</script>
</head>
<BODY leftmargin="0" bottommargin="0" topmargin="0" rightmargin="0">
	<form name="ckParkForm" method="post" action="../../BcTheKhoSvl">
	<% redis.clients.jedis.Jedis jedis =  geso.dms.center.util.Utility.getJedis(); %><% geso.dms.center.util.Csrf csdr=new geso.dms.center.util.Csrf(request,response,true,false,true);%>
 <input type="hidden" name="<%=csdr.get_tokenName() %>" value='<%=csdr.get_tokenValue() %>'>
	
		<input type="hidden" name="action" value=''>
		<input type="hidden" name="userId" value='<%=userId%>'>
		<input type="hidden"name="crrApprSplitting" value="<%= obj.getCrrApprSplitting() %>">
		<input type="hidden" name="nxtApprSplitting" value="<%= obj.getNxtApprSplitting() %>">
		<% obj.setNextSplittings(); %>
		<input type="hidden" name="msg" id="msg" value='<%=Utility.GLanguage(obj.getMsg(),session,jedis) %>'>
		
		<div id="main" style="width: 99%; padding-left: 2px">
			<div align="left" id="header" style="width: 100%; float: none">
				<div style="width: 70%; padding: 5px; float: left"
					class="tbnavigation"><%=url %></div>
				<div align="right" style="padding: 5px" class="tbnavigation">
					<%= Utility.GLanguage("Chào mừng bạn",session,jedis) %> 
					<%=userTen%></div>
			</div>
			<div align="left" style="width: 100%; float: none; clear: left">
				<fieldset>
					<legend class="legendtitle"> <%= Utility.GLanguage("Báo lỗi nhập liệu",session,jedis) %></legend>
					<textarea id="errors" name="errors" rows="1"  style="width: 100% ; color:#F00 ; font-weight:bold">
						<%=Utility.GLanguage(obj.getMsg(),session,jedis) %></textarea>
				</fieldset>
			</div>
			<div align="left"
				style="width: 100%; float: none; clear: left; font-size: 0.7em">
				<fieldset>
					<legend class="legendtitle"><%=Utility.GLanguage("Báo cáo Thẻ Kho",session,jedis) %> </legend>
					<div style="width: 100%; float: none" align="left">
						<div style="width: 100%; float: none" align="left"
							class="plainlabel">
							<TABLE width="100%" cellpadding="6px" cellspacing="0px" style="margin-top: 5px " >
							<TR >
									<TD class="plainlabel" width="120px"><%=Utility.GLanguage("Từ ngày",session,jedis) %></TD>
									<TD class="plainlabel" width="250px" >
								 	<input type="text" name="Sdays"	class="days" value='<%=obj.getTuNgay()%>'  /></TD>
								 	
								 	<TD class="plainlabel"> <%=Utility.GLanguage("Chi nhánh/ĐT",session,jedis) %> </TD>
									<TD class="plainlabel" colspan="3">
										<select name="nppId" id="nppId" style="width:250px" >
											<%if (nppRs != null)
													while (nppRs.next()) {
														if (nppRs.getString("pk_seq").equals(obj.getNppId()   )) {%>
											   <option value="<%=nppRs.getString("pk_seq")%>" selected><%=nppRs.getString("ten")%></option>
											   <%} else {%>
											   <option value="<%=nppRs.getString("pk_seq")%>"><%=nppRs.getString("ten")%></option>
											<%}}%>
										</select>
									</TD> 
									
						   </TR>
						   
						   
						   <TR>
						   
						   <TD class="plainlabel" width="100px" > <%=Utility.GLanguage("Đến ngày",session,jedis) %></TD>
									<TD class="plainlabel" colspan="3" >
										<input type="text" name="Edays" class="days" value='<%=obj.getDenNgay()%>'   /></td> 
						  
								</TR>
								
								<TR>
						   
									   <TD class="plainlabel" width="100px" ><%=Utility.GLanguage("Kho xuất",session,jedis) %></TD>
												<TD class="plainlabel" >
													<select name="khoId" id="khoId" style="width:250px"   onchange="submitform();" >
														<option value="" ><%=Utility.GLanguage("All",session,jedis) %></option>
														<%if (khoRs != null)
																while (khoRs.next()) {
																	if (khoRs.getString("pk_seq").equals(obj.getKhoId() )) {%>
														   <option value="<%=khoRs.getString("pk_seq")%>" selected><%=khoRs.getString("ten")%></option>
														   <%} else {%>
														   <option value="<%=khoRs.getString("pk_seq")%>"><%=khoRs.getString("ten")%></option>
														<%}}%>
													</select>
										</td> 
									   
									   <TD class="plainlabel" width="100px" ><%=Utility.GLanguage("Kênh bán hàng",session,jedis) %></TD>
												<TD class="plainlabel" >
													<select name="kbhId" id="kbhId" style="width:250px"   onchange="submitform();" >
														<option value="" ><%=Utility.GLanguage("All",session,jedis) %></option>
														<%if (kbhRs != null)
																while (kbhRs.next()) {
																	if (kbhRs.getString("pk_seq").equals(obj.getKbhId() )) {%>
														   <option value="<%=kbhRs.getString("pk_seq")%>" selected><%=kbhRs.getString("ten")%></option>
														   <%} else {%>
														   <option value="<%=kbhRs.getString("pk_seq")%>"><%=kbhRs.getString("ten")%></option>
														<%}}%>
													</select>
										</td> 
									   
								</TR>
								
								
								<TR>
						   
									   <TD class="plainlabel">  <%=Utility.GLanguage("Sản phẩm",session,jedis) %></TD>
										<TD class="plainlabel" >
										<select name="spId" id="spId" style="width:250px"   onchange="submitform();" >
											<option value="" ></option>	
											<%if (spRs != null)
													while (spRs.next()) {
														if (spRs.getString("pk_seq").equals(obj.getSpId() )) {%>
											   <option value="<%=spRs.getString("pk_seq")%>" selected><%=spRs.getString("ten")%></option>
											   <%} else {%>
											   <option value="<%=spRs.getString("pk_seq")%>"><%=spRs.getString("ten")%></option>
											<%}}%>
										</select>
										</TD>
									   
									   <TD class="plainlabel" width="100px" ><%=Utility.GLanguage("Số lô",session,jedis) %></TD>
												<TD class="plainlabel" >
													<select name="solo" id="solo" style="width:250px"  >
														<option value="" ><%=Utility.GLanguage("All",session,jedis) %></option>
														<%if (soloRs != null)
																while (soloRs.next()) {
																	if (soloRs.getString("solo").equals(obj.getSolo() )) {%>
														   <option value="<%=soloRs.getString("solo")%>" selected><%=soloRs.getString("solo")%></option>
														   <%} else {%>
														   <option value="<%=soloRs.getString("solo")%>"><%=soloRs.getString("solo")%></option>
														<%}}%>
													</select>
										</td> 
									   
								</TR>
	
							
					
			
					<TR>
						<TD class="plainlabel" colspan="6">
								<a class="button2" href="javascript:search();"> 
									<img style="top: -4px;" src="../images/button.png" alt=""> <%=Utility.GLanguage("Tìm kiếm",session,jedis) %></a>&nbsp;&nbsp;&nbsp;&nbsp;
							<a class="button2" href="javascript:xuatExcel();"> 
								<img style="top: -4px;" src="../images/button.png" alt=""><%=Utility.GLanguage("Xuất dữ liệu",session,jedis) %> </a>&nbsp;&nbsp;&nbsp;&nbsp;
					</TR> 
			
								
							</TABLE>
						</div>
			</div>
			</fieldset>
		</div>
		
		
		 <div id="tabKM" class="tab_content">
											<TABLE width="100%" border="0" cellspacing="1px" cellpadding="3px">
												<TR class="plainlabel" valign="bottom">
													<th  width="12.5%"  align="center"> NGÀY CHỨNG TỪ</th>
													<th  width="12.5%"  align="center">SỐ CHỨNG TỪ</th>
													<th  width="12.5%"  align="center">MÃ KHÁCH HÀNG   </th>
													<th  width="12.5%" align="left">TÊN KH</th>
													<th  width="12.5%" align="left">NGHIỆP VỤ</th>
													<!-- <th  width="12.5%" align="left">HẠN DÙNG</th> -->
													<th  width="12.5%"  align="right">NHẬP </th>
													<th  width="12.5%" align="right">XUẤT </th>
												
												</TR>
																	
								<%
					
					%>
					<%
												if(hdRs!=null){
													try{
														int m = 1;
														String lightrow = "tblightrow";
														String darkrow = "tbdarkrow";
														while (hdRs.next())
														{
															if (m % 2 != 0) {%>						
															<TR class= <%=lightrow%> >
														<%} else {%>
															<TR class= <%= darkrow%> >
														<%}%>
															<Td align="left"><%=hdRs.getString("NgayCT") %></Td>
															<Td align="left"><%=hdRs.getString("SoCT") %></Td>
															<Td align="left"><%=hdRs.getString("khMA")==null?"":hdRs.getString("khMA") %></Td>
															<Td align="left"><%=hdRs.getString("khTEN")==null?"":hdRs.getString("khTEN") %></Td>
															<Td align="left"><%=hdRs.getString("NghiepVu") %></Td>
														 
															<Td align="right"><%=   formatter.format(hdRs.getDouble("nhap"))  %></Td>
															<Td align="right"><%= formatter.format(hdRs.getDouble("xuat"))  %></Td>
															
												<% m++;}}catch(Exception e){}} %>
					
					
					
												<%
												if(hdRs!=null){
													try{
														int m = 1;
														String lightrow = "tblightrow";
														String darkrow = "tbdarkrow";
														while (hdRs.next())
														{
															if (m % 2 != 0) {%>						
															<TR class= <%=lightrow%> >
														<%} else {%>
															<TR class= <%= darkrow%> >
														<%}%>
															<Td align="left"><%=hdRs.getString("spMa") %></Td>
															<Td align="left"><%=hdRs.getString("NgayCT") %></Td>
															<Td align="left"><%=hdRs.getString("khMA") %></Td>
															<Td align="left"><%=hdRs.getString("khTEN") %></Td>
															<Td align="left"><%=hdRs.getString("NghiepVu") %></Td>
															<Td align="left"><%=   hdRs.getDouble("SoLuong")>0?formatter.format(hdRs.getDouble("SoLuong")):"" %></Td>
															<Td align="left"><%= hdRs.getDouble("SoLuong")<0?formatter.format(-1*hdRs.getDouble("SoLuong")):"" %></Td>
															
												<% m++;}}catch(Exception e){}} %>
												</TR>
											</TABLE>
										</div>
		
	</div><%geso.dms.center.util.Utility.JedisClose(jedis); %>
	</form>
	<%
		if(obj!=null){obj.closeDB();
		obj = null;}
		session.setAttribute("obj", null);
	%>
</body>
</HTML>