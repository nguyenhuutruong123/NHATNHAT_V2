<%@page import="java.sql.ResultSet"%>
<%@page import="geso.dms.center.beans.stockintransit.IStockintransit"%>
<%@page import="java.util.Calendar"%>
<%@page import="java.sql.Date"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"

	pageEncoding="UTF-8"%>
	<%@page import="geso.dms.center.util.Utility"%>

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
		ResultSet dvdl = obj.getdvdl();
		ResultSet bm = obj.getBM();
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

<script type="text/javascript"	src="../scripts/jquery.min.1.7.js"></script>
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
	function submitform() {
		document.forms['frm'].action.value= 'Taomoi';
		document.forms["frm"].submit();
	}
	function seach()
	{
		document.forms['frm'].action.value= 'seach';
		document.forms["frm"].submit();
	}
	function setErrors(errorMsg) {
		var errors = document.getElementById("errors");
		errors.value = errorMsg;
	}

</script>
</head>
<BODY leftmargin="0" bottommargin="0" topmargin="0" rightmargin="0">
	<form name="frm" method="post" action="../../BMPerformance">
	<input type="hidden" name="action" value='1'>
	<input type="hidden" name="userId" value='<%=userId%>'>
		<div id="main" style="width: 99%; padding-left: 2px">
			<div align="left" id="header" style="width: 100%; float: none">
				<div style="width: 70%; padding: 5px; float: left"
					class="tbnavigation">Quản lý chỉ tiêu > Báo cáo > Thực hiện chỉ tiêu của RSM</div>
				<div align="right" style="padding: 5px" class="tbnavigation">
					<%=Utility.GLanguage("Chào mừng bạn",session,jedis) %>  <%=userTen%></div>
			</div>
			<div align="left" id="button"
				style="width: 100%; height: 32px; padding: 0px; float: none"
				class="tbdarkrow">
				<A href="#"> <img
					src="../images/Back30.png" alt="Quay ve" title="Quay ve"
					border="1px" longdesc="Quay ve" style="border-style: outset"></A>
				<A href="javascript:saveform()"> <IMG src="../images/Save30.png"
					title="Luu lai" alt="Luu lai" border="1px"
					style="border-style: outset"></A>
			</div>
			<div align="left" style="width: 100%; float: none; clear: left">
				<fieldset>
					<legend class="legendtitle"> Thông báo</legend>
					<textarea id="errors" name="errors" rows="1" style="width: 100% ; color:#F00 ; font-weight:bold">
					<%= obj.getMsg() %></textarea>
				</fieldset>
			</div>
			<div align="left"
				style="width: 100%; float: none; clear: left; font-size: 0.7em">
				<fieldset>
					<legend class="legendtitle"> Thực hiện chỉ tiêu của RSM</legend>
					<div style="width: 100%; float: none" align="left">
						<div style="width: 100%; float: none" align="left"
							class="plainlabel">
							<TABLE width="70%" cellpadding="6" cellspacing="0">
								<TR id="XemThang">
									<TD class="plainlabel">Qúy</TD>
									<TD class="plainlabel">
									<% 	String[] trangthai = new  String[] {"Qúy 1","Qúy 2","Qúy 3","Qúy 4"  } ;
							String[] idTrangThai = new  String[] {"1","2","3","4"} ;
						%> 
						<SELECT name="type" >
						<option> </option>
		      		 <% for( int i=0;i<trangthai.length;i++) { 
		    			if(idTrangThai[i].equals(obj.gettype()) ){ %>
		      				<option value='<%=idTrangThai[i]%>' selected><%=trangthai[i] %></option>
		      		 	<%}else{ %>
		     				<option value='<%=idTrangThai[i]%>'><%=trangthai[i] %></option>
		     			<%} 
		      		 }
		      		 	%>
					       	</SELECT>
									
									<select name="tunam"  style="width :50px" ">
									<option value=0> </option>  
									<%
									  
  										int n;
  										for(n=2014;n<2025;n++){
  										if(obj.getFromYear().equals(""+n)){
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
									</TD>
									
								</TR>
								<TR>
								<TD class="plainlabel"><%=Utility.GLanguage("Kênh bán hàng",session,jedis) %></TD>									
								<TD class="plainlabel">
										<select name="kenhId" id="kenhId" onchange="seach();">
												<option value="" selected>All</option>
												<%if (kenh != null)
														while (kenh.next()) {
															if (kenh.getString("pk_seq").equals(obj.getkenhId())) {%>
												   		<option value="<%=kenh.getString("pk_seq")%>" selected><%=kenh.getString("diengiai")%></option>
												   <%} else {%>
												   	<option value="<%=kenh.getString("pk_seq")%>"><%=kenh.getString("diengiai")%></option>
													<%}}%>
										</select>
								</TD>
								<TD class="plainlabel">Đơn vị kinh doanh</TD>
								<TD class="plainlabel">
										<select name="dvkdId" id="dvkdId" onchange="seach();" >
											<option value="" selected>All</option>
											<%if (dvkd != null)
													while (dvkd.next()) {
														if (dvkd.getString("pk_seq").equals(obj.getdvkdId())) {%>
										   				<option value="<%=dvkd.getString("pk_seq")%>" selected><%=dvkd.getString("diengiai")%></option>
										   		  <%} else {%>
										   				<option value="<%=dvkd.getString("pk_seq")%>"><%=dvkd.getString("diengiai")%></option>
													<%}}%>
											</select>
										</TD>
								</TR>
								<TR>
								<TD class="plainlabel">Miền</TD>
								<TD class="plainlabel">
										<select name="vungId" id="vungId" onchange="seach();">
											<option value="" selected>All</option>
											<%if (vung != null)
													while (vung.next()) {
														if (vung.getString("pk_seq").equals(obj.getvungId())) {%>
											   				<option value="<%=vung.getString("pk_seq")%>" selected><%=vung.getString("ten")%></option>
											   			<%} else {%>
											   				<option value="<%=vung.getString("pk_seq")%>"><%=vung.getString("ten")%></option>
														<%}}%>
										</select>
								</TD>
								<TD class="plainlabel">RSM</TD>
								<TD class="plainlabel">
										<select name="bmId">
											<option value="" >All</option>
											<%if (bm != null)
													while (bm.next()) {
														if (bm.getString("pk_seq").equals(obj.getASMId())) {%>
											   				<option value="<%=bm.getString("pk_seq")%>" selected><%=bm.getString("ten")%></option>
											   			<%} else {%>
														   <option value="<%=bm.getString("pk_seq")%>"><%=bm.getString("ten")%></option>
														<%}
													}
											%>
										</select>
								</TD>								
								</TR>
														
								<TR>
									<td colspan="4"><a class="button"
										href="javascript:submitform();"> <img style="top: -4px;"
											src="../images/button.png" alt=""> Tạo báo cáo
									</a></td>
								</TR>
							</TABLE>
						</div>
					</div>
				</fieldset>
			</div>
		</div>

	</form>
</body>
</HTML>
<%
	try
	{
		if(!(bm == null))
			bm.close();
		if(dvdl != null)
			dvdl.close();
		if(dvkd != null)
			dvkd.close();
		if(kenh != null)
			kenh.close();
		if(khuvuc != null)
			khuvuc.close();
		if(npp != null)
			npp.close();

		if(obj != null){
			obj.DBclose();
			obj = null;
		}
	}catch(java.sql.SQLException e){}
	}
%>