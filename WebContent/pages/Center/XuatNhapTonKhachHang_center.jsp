<%@page import="geso.dms.center.beans.stockintransit.IStockintransit"%>
<%@page import="java.sql.ResultSet"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page  import = "geso.dms.center.util.*" %>
<%	String userId = (String) session.getAttribute("userId");  
	String userTen = (String) session.getAttribute("userTen");  	
	String sum = (String) session.getAttribute("sum");
	Utility util = (Utility) session.getAttribute("util");
	if(!util.check(userId, userTen, sum)){
		response.sendRedirect(request.getContextPath() + "/redirect.jsp");
	}else{ %>
<%
	IStockintransit obj = (IStockintransit) session.getAttribute("obj");
  	ResultSet kenh = obj.getkenh();
   	ResultSet vung = obj.getvung();
   	ResultSet khuvuc = obj.getkhuvuc();
   	ResultSet npp = obj.getnpp();
   	ResultSet dvkd = obj.getdvkd();
   	ResultSet nhanhang = obj.getnhanhang();
   	ResultSet chungloai = obj.getchungloai();
   	ResultSet dvdl = obj.getdvdl();
   	ResultSet sanpham = obj.getsanpham();
  	ResultSet gsbh = obj.getgsbh();
%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"> <% Utility Utilback = new Utility(); %>

<HTML>
<HEAD>
<TITLE><%= getServletContext().getInitParameter("TITLENAME") %></TITLE>
	<link type="text/css" rel="stylesheet" href="../css/mybutton.css">
<META http-equiv="Content-Style-Type" content="text/css">
<LINK rel="stylesheet" href="../css/main.css" type="text/css">

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
	document.forms['rpForm'].action.value="taomoi";
	document.forms['rpForm'].dataerror.value="";
	document.forms['rpForm'].submit();
}
function seach(){
	document.forms['rpForm'].action.value="search";
	document.forms['rpForm'].submit();
}
</SCRIPT>
<link href="../css/select2.css" rel="stylesheet" />
<script src="../scripts/select2.js"></script>
<script>
$(document).ready(function()
{
	$("#nppId").select2();
	$("#vungId").select2();
	$("#khuvucId").select2();
	$("#nhanhangId").select2();
	$("#chungloaiId").select2();
	$("#programs").select2();
	$("#dvkdId").select2();
	$("#gsbhId").select2();
	$("#kenhId").select2();
	$("#sanphamId").select2();
});
</script>


</HEAD>
<BODY leftmargin="0" bottommargin="0" topmargin="0" rightmargin="0">
<form name="rpForm" method="post" action="../../XuatNhapTonKhachHangTTSvl">
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
						   <TD align="left" colspan="2" class="tbnavigation">&#160; Qu???n l?? t???n kho &#62; B??o c??o &#62; Xu???t Nh???p T???n Kh??ch H??ng </TD>
   
						   <TD colspan="2" align="right" class="tbnavigation"><%=Utility.GLanguage("Ch??o m???ng b???n",session,jedis) %>  <%= userTen %>&nbsp;</TD>
						  </tr>
 					  </table>					</TD>
				</TR>
				<tr>
					<TD align="left" colspan="4" class="legendtitle">
						<FIELDSET>
						<LEGEND class="legendtitle"><%=Utility.GLanguage("B??o l???i nh???p li???u",session,jedis) %></LEGEND>
	    				<textarea name="dataerror"  style="width: 100% ; color:#F00 ; font-weight:bold"  style="width: 100% ; color:#F00 ; font-weight:bold" readonly="readonly" rows="1"><%= obj.getMsg() %></textarea>
						</FIELDSET>
				   </TD>
				</tr>	
				<TR>
					<TD>
					<TABLE border="0" width="100%" cellpadding="0" cellspacing="0">
						<TR>
							<TD width="100%" align="center" >
							<FIELDSET>
							  <LEGEND class="legendtitle">B??o c??o xu???t nh???p t???n c???a kh??ch h??ng</LEGEND>
							<TABLE  width="100%" cellpadding="6px" cellspacing="0px">
								<TR>
								  	<TD class="plainlabel" width="100px"><%=Utility.GLanguage("T??? ng??y",session,jedis) %></TD>
									<TD class="plainlabel" width="230px">
										<input type="text" name="Sdays"	class="days" value='<%=obj.gettungay()%>' />
									</TD>
									<TD class="plainlabel" width="130px"><%=Utility.GLanguage("?????n ng??y",session,jedis) %></TD>
									<td class="plainlabel">
										<input type="text" name="Edays" class="days" value='<%=obj.getdenngay()%>' />
									</td>
								</TR>
								
								<TR>
									<TD class="plainlabel"><%=Utility.GLanguage("K??nh b??n h??ng",session,jedis) %></TD>
									
									<TD class="plainlabel">
										<select name="kenhId" id="kenhId" onchange="seach();" style="width:250px">
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
									<TD class="plainlabel" width="130px">????n v??? kinh doanh</TD>
									<TD class="plainlabel"><select name="dvkdId" id="dvkdId"  style="width:250px">
											<option value="" selected>All</option>
											<% if(dvkd != null)
											   while(dvkd.next()){
											   if(dvkd.getString("pk_seq").equals(obj.getdvkdId()))
											   { %>
											   <option value="<%= dvkd.getString("pk_seq") %>" selected><%=dvkd.getString("diengiai") %></option>
											   <%}else { %>
											   <option value="<%= dvkd.getString("pk_seq") %>"><%=dvkd.getString("diengiai") %></option>
											<%} }%>
									</select>
									</TD>
									
								</TR>
								<TR>
								<TD class="plainlabel"><%=Utility.GLanguage("V??ng",session,jedis) %>/Mi???n</TD>
									<TD class="plainlabel">
										<select name="vungId" id="vungId" onchange="seach();" style="width:250px">
											<option value="" selected>All</option>
											<% if(vung != null)
											   while(vung.next()){
											   if(vung.getString("pk_seq").equals(obj.getvungId()))
											   { %>
											   <option value="<%= vung.getString("pk_seq") %>" selected><%=vung.getString("ten") %></option>
											   <%}else { %>
											   <option value="<%= vung.getString("pk_seq") %>"><%=vung.getString("ten") %></option>
											<%} }%>
										</select>
										</TD>
										<TD class="plainlabel">Nh??n h??ng</TD>
									<TD class="plainlabel">
										<select name="nhanhangId" id="nhanhangId" onchange="seach();" style="width:250px">
												<option value="" selected>All</option>
													<% if(nhanhang != null)
												   while(nhanhang.next()){
												   if(nhanhang.getString("pk_seq").equals(obj.getnhanhangId()))
												   { %>
												   <option value="<%= nhanhang.getString("pk_seq") %>" selected><%=nhanhang.getString("ten") %></option>
												   <%}else { %>
												   <option value="<%= nhanhang.getString("pk_seq") %>"><%=nhanhang.getString("ten") %></option>
												<%} }%>
										</select>
									</TD>
									</TR>
								<TR>									
									<TD class="plainlabel"><%=Utility.GLanguage("Khu v???c",session,jedis) %></TD>
									<TD class="plainlabel">
									<select name="khuvucId" id="khuvucId" onchange="seach();" style="width:250px">
											<option value="" selected>All</option>
											<% if(khuvuc != null)
											   while(khuvuc.next()){
											   if(khuvuc.getString("pk_seq").equals(obj.getkhuvucId()))
											   { %>
											   <option value="<%= khuvuc.getString("pk_seq") %>" selected><%=khuvuc.getString("ten") %></option>
											   <%}else { %>
											   <option value="<%= khuvuc.getString("pk_seq") %>"><%=khuvuc.getString("ten") %></option>
											<%} }%>
									</select>
									</TD>
									<TD class="plainlabel">Ch???ng Lo???i</TD>
									<TD class="plainlabel">
									<select name="chungloaiId" id="chungloaiId" onchange="seach();" style="width:250px">
											<option value="" selected>All</option>
											<% if(chungloai != null)
											   while(chungloai.next()){
											   if(chungloai.getString("pk_seq").equals(obj.getchungloaiId()))
											   { %>
											   <option value="<%= chungloai.getString("pk_seq") %>" selected><%=chungloai.getString("ten") %></option>
											   <%}else { %>
											   <option value="<%= chungloai.getString("pk_seq") %>"><%=chungloai.getString("ten") %></option>
											<%} }%>
									</select>
									</TD>
									</TR>
								<TR>									
									<TD class="plainlabel"><%=Utility.GLanguage("Nh?? ph??n ph???i",session,jedis) %></TD>
									<TD class="plainlabel" colspan="3"><select name="nppId" id="nppId"  style="width:250px">
											<option value="" selected >All</option>
											<% if(npp != null)
											   while(npp.next()){
											   if(npp.getString("pk_seq").equals(obj.getnppId()))
											   { %>
											   <option value="<%= npp.getString("pk_seq") %>" selected><%=npp.getString("ten") %></option>
											   <%}else { %>
											   <option value="<%= npp.getString("pk_seq") %>"><%=npp.getString("ten") %></option>
											<%} }%>
									</select></TD>	
								</TR>

							    <TR>
									<TD colspan="4" class="plainlabel">
									<a class="button2" href="javascript:submitform()" >
										<img style="top: -4px;" src="../images/button.png" alt=""><%=Utility.GLanguage("T???o b??o c??o",session,jedis) %> </a>
											&nbsp;&nbsp;&nbsp;&nbsp;
									</TD>
								</TR>
							</TABLE>
							</FIELDSET>	</TD>
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
	if(chungloai != null) chungloai.close();
	if (dvkd != null) dvkd.close();
	if (nhanhang != null) nhanhang.close();
	if (dvdl != null) dvdl.close();  
	if(gsbh != null) gsbh.close();
	if(kenh != null) kenh.close();
	if(khuvuc != null) khuvuc.close();
	if(npp != null) npp.close();
	if(vung != null) vung.close();
	if(sanpham != null) sanpham.close();
	if(obj != null)  obj.DBclose();
	obj = null;
	session.setAttribute("obj", null);
}
%>