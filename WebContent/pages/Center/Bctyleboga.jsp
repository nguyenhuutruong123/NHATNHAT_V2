<%@page import="java.sql.ResultSet"%>
<%@page import="geso.dms.center.beans.report.IBctylebogaList"%>
<%@page import="java.util.Calendar"%>
<%@page import="java.sql.Date"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@page import="java.text.DecimalFormat"%>
<%@page import="java.text.NumberFormat"%>
<%@ page  import = "geso.dms.center.util.*" %>
<% NumberFormat formatter = new DecimalFormat("#,###,###"); %>

<%
	String userId = (String) session.getAttribute("userId");
	String userTen = (String) session.getAttribute("userTen");
	IBctylebogaList obj = (IBctylebogaList) session.getAttribute("obj");
	ResultSet ddkd = obj.getDdkdRs();
	ResultSet khRs = obj.getKhRs();
	ResultSet spRs = obj.getSpRs();
	ResultSet hdRs = obj.getHoadonRs();
	ResultSet kbhRs = obj.getKbhRs();
	ResultSet totalRs = obj.getTotalRs();
	
	Utility util = (Utility) session.getAttribute("util");
	String url="";
	url = util.getUrl("BcDoanhThuKhachHangSvl","&DSKHT");

	ResultSet vungRs = obj.getVungRs();
	ResultSet ttRs = obj.getTtRs();
	ResultSet nppRs = obj.getNppRs();
	ResultSet nhomRs = obj.getNhomRs();
	
	
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
	document.forms['ckParkForm'].action.value= 'excel';
	document.forms['ckParkForm'].submit();
	
}

function search()
{
	
	document.forms['ckParkForm'].action.value= 'search';
	document.forms['ckParkForm'].submit();
	
}
</script>
</head>
<BODY leftmargin="0" bottommargin="0" topmargin="0" rightmargin="0">
	<form name="ckParkForm" method="post" action="../../BctylebogathangSVL">
		<input type="hidden" name="action" value=''>
			<input type="hidden" name="DSKHT" value='DSKHT'>
			<input type="hidden" name="DSKHT_client" value='<%=obj.getDSKHT_client()%>'>
		<input type="hidden" name="userId" value='<%=userId%>'>
<input type="hidden" name="currentPage" value="<%= obj.getCurrentPage() %>" >



		<input type="hidden" name="msg" id="msg" value='<%= obj.getMsg() %>'>
		
		<div id="main" style="width: 99%; padding-left: 2px">
			<div align="left" id="header" style="width: 100%; float: none">
				<div style="width: 70%; padding: 5px; float: left"
					class="tbnavigation">B??o c??o qu???n tr??? > B??o c??o ph??n t??ch > T??? l??? HH-BG kh??ch h??ng theo th??ng</div>
				<div align="right" style="padding: 5px" class="tbnavigation">
					<%=Utility.GLanguage("Ch??o m???ng b???n",session,jedis) %> 
					<%=userTen%></div>
			</div>
			<div align="left" style="width: 100%; float: none; clear: left">
				<fieldset>
					<legend class="legendtitle"> <%=Utility.GLanguage("B??o l???i nh???p li???u",session,jedis) %></legend>
					<textarea id="errors" name="errors" rows="1"  style="width: 100% ; color:#F00 ; font-weight:bold">
						<%=obj.getMsg()%></textarea>
				</fieldset>
			</div>
			<div align="left"
				style="width: 100%; float: none; clear: left; font-size: 0.7em">
				<fieldset>
					<legend class="legendtitle">Doanh thu theo kh??ch h??ng </legend>
					<div style="width: 100%; float: none" align="left">
						<div style="width: 100%; float: none" align="left"
							class="plainlabel">
							<TABLE width="100%" cellpadding="6px" cellspacing="0px" style="margin-top: 5px " >
							<TR >
									<%-- <TD class="plainlabel" width="120px"><%=Utility.GLanguage("T??? ng??y",session,jedis) %></TD>
									<TD class="plainlabel" width="250px" >
								 	<input type="text" name="Sdays"	class="days" value='<%=obj.getTuNgay()%>'  /></TD> 
									<TD class="plainlabel" width="100px" > ?????n ng??y</TD>
									<TD class="plainlabel" colspan="3">
										<input type="text" name="Edays" class="days" value='<%=obj.getDenNgay()%>'   /></td> 
										 --%>
								<TD class="plainlabel" width="60px">Th??ng BD</TD>
								<TD class="plainlabel" width="30px" >
								<select name='thangbd' >
									<%for(int i=1;i<13;i++){ %>
									<option value="<%=i%>" <%if(obj.getThangbd().equals(""+i)){ %> selected="selected" <%} %>><%=i %></option>
									<%} %>
								</select>
								</TD>	
								
								<TD class="plainlabel" width="60px">n??m BD</TD>
								<TD class="plainlabel" width="30px" >
								<select name='nambd' >
									<%for(int i=2010;i<2030;i++){ %>
									<option value="<%=i%>" <%if(obj.getNambd().equals(""+i)){ %> selected="selected" <%} %>  ><%=i %></option>
									<%} %>
								</select>
								</TD>		
						   </TR>
						   
						   
						   <TR >
							
								<TD class="plainlabel" width="60px" >Th??ng KT</TD>
								<TD class="plainlabel" width="30px" >
								<select name='thangkt' >
									<%for(int i=1;i<13;i++){ %>
									<option value="<%=i%>" <%if(obj.getThangkt().equals(""+i)){ %> selected="selected" <%} %>><%=i %></option>
									<%} %>
								</select>
								</TD>	
								
								<TD class="plainlabel"  width="60px">n??m KT</TD>
								<TD class="plainlabel" width="30px" >
								<select name='namkt' >
									<%for(int i=2010;i<2030;i++){ %>
									<option value="<%=i%>" <%if(obj.getNamkt().equals(""+i)){ %> selected="selected" <%} %>><%=i %></option>
									<%} %>
								</select>
								</TD>
										
								
						   </TR>
						   
						   <TR>
									<TD class="plainlabel">Mi???n  </TD>
									<TD class="plainlabel">
										<select name="vungId" id="vungId" style="width:250px"   onchange="submitform();" >
											<option value="" >All</option>
											<%if (vungRs != null)
													while (vungRs.next()) {
														if (vungRs.getString("pk_seq").equals(obj.getVungId()  )) {%>
											   <option value="<%=vungRs.getString("pk_seq")%>" selected><%=vungRs.getString("ten")%></option>
											   <%} else {%>
											   <option value="<%=vungRs.getString("pk_seq")%>"><%=vungRs.getString("ten")%></option>
											<%}}%>
										</select>
									</TD>
									
										<TD class="plainlabel">?????a b??n  </TD>
									<TD class="plainlabel" colspan="3">
										<select name="ttId" id="ttId" style="width:250px"   onchange="submitform();" >
											<option value="" >All</option>
											<%if (ttRs != null)
													while (ttRs.next()) {
														if (ttRs.getString("pk_seq").equals(obj.getTtId()  )) {%>
											   <option value="<%=ttRs.getString("pk_seq")%>" selected><%=ttRs.getString("ten")%></option>
											   <%} else {%>
											   <option value="<%=ttRs.getString("pk_seq")%>"><%=ttRs.getString("ten")%></option>
											<%}}%>
										</select>
									</TD>
									
								</TR>
								
								<TR>
								
								<TD class="plainlabel"> Chi nh??nh/??T </TD>
									<TD class="plainlabel" >
										<select name="nppId" id="nppId" style="width:250px"  onchange="submitform();" >
											<option value="" >All</option>
											<%if (nppRs != null)
													while (nppRs.next()) {
														if (nppRs.getString("pk_seq").equals(obj.getNppId()   )) {%>
											   <option value="<%=nppRs.getString("pk_seq")%>" selected><%=nppRs.getString("ten")%></option>
											   <%} else {%>
											   <option value="<%=nppRs.getString("pk_seq")%>"><%=nppRs.getString("ten")%></option>
											<%}}%>
										</select>
									</TD>
								
									<TD class="plainlabel">  </TD>
									<TD class="plainlabel" colspan="3" >
										<%-- <select name="kbhId" id="kbhId" style="width:250px"  onchange="submitform();"  >
											<option value="" >All</option>
											<%if (kbhRs != null)
													while (kbhRs.next()) {
														if (kbhRs.getString("pk_Seq").equals(obj.getKbhId())) {%>
											   <option value="<%=kbhRs.getString("pk_Seq")%>" selected><%=kbhRs.getString("ten")%></option>
											   <%} else {%>
											   <option value="<%=kbhRs.getString("pk_Seq")%>"><%=kbhRs.getString("ten")%></option>
											<%}}%>
										</select> --%>
									</TD>
									
									
								</TR>
								
								
								<%-- <TR>
									<TD class="plainlabel"><%=Utility.GLanguage("NH??N VI??N B??N H??NG",session,jedis) %> </TD>
									<TD class="plainlabel">
										<select name="ddkdId" id="ddkdId" style="width:250px"  >
											<option value="" >All</option>
											<%if (ddkd != null)
													while (ddkd.next()) {
														if (ddkd.getString("pk_seq").equals(obj.getDdkdId()  )) {%>
											   <option value="<%=ddkd.getString("pk_seq")%>" selected><%=ddkd.getString("ten")%></option>
											   <%} else {%>
											   <option value="<%=ddkd.getString("pk_seq")%>"><%=ddkd.getString("ten")%></option>
											<%}}%>
										</select>
									</TD>
									
									<TD class="plainlabel"> Kh??ch h??ng </TD>
									<TD class="plainlabel">
										<select name="khId" id="khId" style="width:250px"  onchange="submitform();">
											<option value="" >All</option>
											<%if (khRs != null)
													while (khRs.next()) {
														if (khRs.getString("pk_seq").equals(obj.getKhId()  )) {%>
											   <option value="<%=khRs.getString("pk_seq")%>" selected><%=khRs.getString("ten")%></option>
											   <%} else {%>
											   <option value="<%=khRs.getString("pk_seq")%>"><%=khRs.getString("ten")%></option>
											<%}}%>
										</select>
									</TD>
								</TR>
								
						<TR>
		
									<TD class="plainlabel"> Nh??m h??ng  </TD>
									<TD class="plainlabel">
										<select name="nhomId" id="nhomId" style="width:250px"   onchange="submitform();" >
											<option value="" >All</option>
											<%if (nhomRs != null)
													while (nhomRs.next()) {
														if (nhomRs.getString("pk_seq").equals(obj.getNhomId()   )) {%>
											   <option value="<%=nhomRs.getString("pk_seq")%>" selected><%=nhomRs.getString("ten")%></option>
											   <%} else {%>
											   <option value="<%=nhomRs.getString("pk_seq")%>"><%=nhomRs.getString("ten")%></option>
											<%}}%>
										</select>
									</TD>					
						</TD>
					<TR> --%>
					<%-- 			<%
					double DoanhSo=0;
					double Thue=0;
					double DoanhThu=0;
					double soluongkh=0;
					while(totalRs!=null && totalRs.next())
					{
						DoanhSo=totalRs.getDouble("BVAT");
						Thue=totalRs.getDouble("VAT");
						DoanhThu=totalRs.getDouble("AVAT");
						soluongkh=totalRs.getDouble("slkh");
					}
					if(obj.get_Action().length()>0)
					{
					%>
					
					<TR>
						<TD class="plainlabel" colspan="6"></TD>
								<TR>
									<TD class="plainlabel" >DOANH THU</TD>
									<td class="plainlabel"><input type="text" name="ds" size="6" value="<%= formatter.format(DoanhSo   ) %>"></td>
									<TD class="plainlabel" >THU??? GTGT</TD>
									<td class="plainlabel"><input type="text" name="ck" size="6" value="<%= formatter.format( Thue ) %>"></td>
									<TD class="plainlabel"  width="120px">T???NG TI???N</TD>
									<td class="plainlabel"><input type="text" name="dt" size="6" value="<%= formatter.format(  DoanhThu) %>"></td>
							
								</TR>
								<TD class="plainlabel" colspan="6"></TD>
								<TR>
									<TD class="plainlabel" ></TD>
									<td class="plainlabel"></td>
									
									<TD class="plainlabel" width="120px" >S??? L?????NG KH??CH H??NG</TD>
									<td class="plainlabel"><input type="text" name="soluongkh" size="6" value="<%= formatter.format( soluongkh) %>"></td>
									<TD class="plainlabel" ></TD>
									<td class="plainlabel"></td>
							
								</TR>
					<%} %>
								
								
						    --%>
					<TR>
						<TD class="plainlabel" colspan="6">
								<!-- <a class="button2" href="javascript:search();"> 
									<img style="top: -4px;" src="../images/button.png" alt=""> <%=Utility.GLanguage("T??m ki???m",session,jedis) %></a>&nbsp;&nbsp;&nbsp;&nbsp; -->
							<a class="button2" href="javascript:xuatExcel();"> 
								<img style="top: -4px;" src="../images/button.png" alt=""><%=Utility.GLanguage("Xu???t d??? li???u",session,jedis) %> </a>&nbsp;&nbsp;&nbsp;&nbsp;
					</TR> 
			
								
							</TABLE>
						</div>
			</div>
			</fieldset>
		</div>
		
		
		<%-- <div id="tabKM" class="tab_content">
											<TABLE width="100%" border="0" cellspacing="1px" cellpadding="3px">
												<TR class="plainlabel" valign="bottom">
													<th  width="5%"  align="center"> STT</th>
													<th  width="5%"  align="left"> ?????A B??N </th>
													<th  width="10%" align="left">CHI NH??NH /??T</th>
													<th  width="10%" align="left"><%=Utility.GLanguage("NH??N VI??N B??N H??NG",session,jedis) %></th>
													<th  width="5%" align="left">M?? KH</th>
													<th  width="10%" align="left">M?? H???P ?????NG</th>
													<th  width="10%" align="left">KH??CH H??NG</th>
													<th  width="15%"  align="left">CH??? C???A HI???U</th>
													<th  width="15%"  align="left">?????A CH???</th>
													<th  width="5%"  align="left">LO???I KH</th>
													<th  width="10%"  align="right">DOANH THU</th>
												</TR>
																	
								<%
					 DoanhSo=0;
					 Thue=0;
					 DoanhThu=0;
					
					%>
								
													
												<%
												if(hdRs!=null){
													try{
														int m = 1;
														String lightrow = "tblightrow";
														String darkrow = "tbdarkrow";
														while (hdRs.next()){
															
															DoanhSo +=hdRs.getDouble("BVAT");
															Thue +=hdRs.getDouble("VAT");
															DoanhThu +=hdRs.getDouble("AVAT");
															
															
															if (m % 2 != 0) {%>						
															<TR class= <%=lightrow%> >
														<%} else {%>
															<TR class= <%= darkrow%> >
														<%}%>
															<Td align="center"><%= hdRs.getInt("_no") %></Td>
															<Td align="left"><%=hdRs.getString("ttTEN") %></Td>
															<Td align="left"><%=hdRs.getString("nppTen") %></Td>
															<Td align="left"><%=hdRs.getString("DDKD") %></Td>
															<Td align="left"><%=hdRs.getString("khMa") %></Td>
															<Td align="left"><%=hdRs.getString("khMaHD") %></Td>
															<Td align="left"><%=hdRs.getString("khCHUCH") %></Td>
															<Td align="left"><%=hdRs.getString("khTEN") %></Td>
															<Td align="left"><%=hdRs.getString("khDIACHI") %></Td>
															<Td align="left"><%=hdRs.getString("khLOAI") %></Td>
															<Td align="right"><%= formatter.format(hdRs.getDouble("AVAT")) %></Td>
															
														
												<% m++;}}catch(Exception e){}} %>
												</TR>
												<tr class="tbfooter">
													<Td align="center" colspan="10" >T???NG C???NG</Td>
													<Td align="right"><%= formatter.format( DoanhThu ) %></Td>
												</TR>												
												<tr class="tbfooter" > 
													 <TD align="center" valign="middle" colspan="14" class="tbfooter">
						 							 <% obj.setNextSplittings(); %>
												 		<script type="text/javascript" src="../scripts/phanTrang.js"></script>
														<input type="hidden" name="crrApprSplitting" value="<%= obj.getCrrApprSplitting() %>" >
														<input type="hidden" name="nxtApprSplitting" value="<%= obj.getNxtApprSplitting() %>" >
											 	<%if(obj.getNxtApprSplitting() >1) {%>
													<img alt="Trang Dau" src="../images/first.gif" style="cursor: pointer;" onclick="View(document.forms[0].name, 1, 'view')"> &nbsp;
												<%}else {%>
													<img alt="Trang Dau" src="../images/first.gif" > &nbsp;
													<%} %>
												<% if(obj.getNxtApprSplitting() > 1){ %>
													<img alt="Trang Truoc" src="../images/prev.gif" style="cursor: pointer;" onclick="View(document.forms[0].name, eval(document.forms[0].nxtApprSplitting.value) -1, 'view')"> &nbsp;
												<%}else{ %>
													<img alt="Trang Truoc" src="../images/prev_d.gif" > &nbsp;
												<%} %>
												
												<%
													int[] listPage = obj.getNextSplittings();
													for(int i = 0; i < listPage.length; i++){
												%>
												
												<% 
												
												System.out.println("Current page:" + obj.getNxtApprSplitting());
												System.out.println("List page:" + listPage[i]);
												
												if(listPage[i] == obj.getNxtApprSplitting()){ %>
												
													<a  style="color:white;"><%= listPage[i] %>/ <%=obj.getTheLastSplitting() %></a>
												<%}else{ %>
													<a href="javascript:View(document.forms[0].name, <%= listPage[i] %>, 'view')"><%= listPage[i] %></a>
												<%} %>
													<input type="hidden" name="list" value="<%= listPage[i] %>" />  &nbsp;
												<%} %>
												
												<% if(obj.getNxtApprSplitting() < obj.getTheLastSplitting()){ %>
													&nbsp; <img alt="Trang Tiep" src="../images/next.gif" style="cursor: pointer;" onclick="View(document.forms[0].name, eval(document.forms[0].nxtApprSplitting.value) +1, 'view')"> &nbsp;
												<%}else{ %>
													&nbsp; <img alt="Trang Tiep" src="../images/next_d.gif" > &nbsp;
												<%} %>
												<%if(obj.getNxtApprSplitting() == obj.getTheLastSplitting()) {%>
											   		<img alt="Trang Cuoi" src="../images/last.gif" > &nbsp;
										   		<%}else{ %>
										   			<img alt="Trang Cuoi" src="../images/last.gif" style="cursor: pointer;" onclick="View(document.forms[0].name, -1, 'view')"> &nbsp;
										   		<%} %>
						</TD>
					 </tr>
					 
												
												 
												 											
											</TABLE>
										</div> --%>
		
	</div>
	</form>
	<%
		if(obj!=null){obj.closeDB();
		obj = null;}
		session.setAttribute("obj", null);
	%>
</body>
</HTML>