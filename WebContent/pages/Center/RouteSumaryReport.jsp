<%@page import="java.util.Map"%>
<%@page import="java.util.Enumeration"%>
<%@page import="java.util.Iterator"%>
<%@page import="java.util.Hashtable"%>
<%@page import="java.sql.ResultSet"%>
<%@page import="geso.dms.center.beans.routesumaryreport.IRouteSumaryReport"%>
<%@page import="java.util.Calendar"%>
<%@page import="java.sql.Date"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%
	String userId = (String) session.getAttribute("userId");
	String userTen = (String) session.getAttribute("userTen");
	IRouteSumaryReport obj = (IRouteSumaryReport) session.getAttribute("obj");
	
	Hashtable<String, String> HashStatus = obj.getHashStatus();
	ResultSet rsKhuVuc = obj.getArea();
	ResultSet rsNPP = obj.getDistributor();
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
<script type="text/javascript">
		$(document).ready(function() {	
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


<script language="javascript" type="text/javascript">	
	function change(){
		document.forms['frm'].action.value="search";
		document.forms["frm"].submit();
	}
	function submitform() {
		if (document.forms["frm"]["khuvucs"].value.length == 0) {
			setErrors("Vui l??ng ch???n Khu V???c");
			return;
		}
		if (document.forms["frm"]["hoatdong"].value.length == 0) {
			setErrors("Vui l??ng ch???n tr???ng th??i ");
			return;
		}

		if (document.forms["frm"]["npps"].value.length == 0) {
			setErrors("Vui l??ng ch???n Chi nh??nh / NPP");
			return;
		}				
		document.forms['frm'].action.value="create";
		document.forms["frm"].submit();
	}
	function setErrors(errorMsg) {
		var errors = document.getElementById("errors");
		errors.value = errorMsg;
	}	
</script>
</head>
<BODY leftmargin="0" bottommargin="0" topmargin="0" rightmargin="0">
	<form name="frm" method="post" action="../../RouteSumaryReport">
		<input type="hidden" name="action" value="" />
		<div id="main" style="width: 99%; padding-left: 2px">
			<div align="left" id="header" style="width: 100%; float: none">
				<div style="width: 70%; padding: 5px; float: left"
					class="tbnavigation">&nbsp;B??o c??o qu???n tr??? &gt; B??o c??o kh??c &gt; B??o c??o t??m t???t tuy???n ???????ng</div>
				<div align="right" style="padding: 5px" class="tbnavigation">
					<%=Utility.GLanguage("Ch??o m???ng b???n",session,jedis) %>  : <%=userTen%></div>
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
					<legend class="legendtitle"> <%=Utility.GLanguage("B??o l???i nh???p li???u",session,jedis) %></legend>
					<textarea id="errors" value="<%=session.getAttribute("errors")%>"
						name="errors" rows="1" style="width: 100% ; color:#F00 ; font-weight:bold"></textarea>
				</fieldset>
			</div>
			<div align="left"
				style="width: 100%; float: none; clear: left; font-size: 0.7em">
				<fieldset>
					<legend class="legendtitle">Route Sumary Report</legend>
					<div style="width: 100%; float: none" align="left">
						<div style="width: 100%; float: none" align="left"
							class="plainlabel">
							<TABLE width="70%" cellpadding="6" cellspacing="0">	
								<TR>
									<TD class="plainlabel"><%=Utility.GLanguage("Khu v???c",session,jedis) %></TD>
									<TD class="plainlabel">
									<select name="khuvucs" id="khuvucs" onchange="change()" style="width: 200px;" >
											<option value="" selected="selected" disabled="disabled" >Ch???n</option>
											<%
												if (rsKhuVuc != null) {
													while (rsKhuVuc.next()) {
														if (rsKhuVuc.getString("PK_SEQ").equals(obj.getKhuVuc())) {
											%>
														<option selected="selected" value="<%=rsKhuVuc.getString("PK_SEQ")%>">	<%=rsKhuVuc.getString("TEN")%></option> 
													<%
 														} else {
 													%>												
													<option value="<%=rsKhuVuc.getString("PK_SEQ")%>"><%=rsKhuVuc.getString("TEN")%></option>												
											 <%
																							 	}
																							 		}
																							 	}
																							 %>
									</select></TD>									
								</TR>
								<TR>									
									<TD class="plainlabel"><%=Utility.GLanguage("Tr???ng th??i",session,jedis) %></TD>
									<TD class="plainlabel">
									<select name="hoatdong" onchange="change()" style="width: 200px;" id="loaiCt">
											<%
												for (Map.Entry<String, String> map : HashStatus.entrySet()) {
													if (obj.getStatus().equals(map.getKey())) {	%>
													<option value="<%=map.getKey()%>" selected="selected"><%=map.getValue()%></option>
													<%} else {%>
														<option value="<%=map.getKey()%>"><%=map.getValue()%></option>
													<%}}%>
									</select>
									</TD>									
								</TR>								
								<TR>
									<TD class="plainlabel"><%=Utility.GLanguage("Nh?? ph??n ph???i",session,jedis) %></TD>
									<TD class="plainlabel">
									<select name="npps" style="width: 200px;" id="loaiCt">
											<option value="" disabled="disabled" selected>Ch???n</option>
											<%
												if (rsNPP != null) {
													while (rsNPP.next()) {
														if (rsNPP.getString("PK_SEQ").equals(obj.getDistributor())) {
											%>
														<option selected="selected" value="<%=rsNPP.getString("PK_SEQ")%>"><%=rsNPP.getString("TEN")%></option> 
													<%
 														} else {
 													%>												
													<option value="<%=rsNPP.getString("PK_SEQ")%>"><%=rsNPP.getString("TEN")%></option>												
											<%
																								}
																									}
																								}
																							%>
										</select>
									</TD>								
								</TR>			
								<TR>
									<td colspan="4"><a class="button"
										href="javascript:submitform();"> <img style="top: -4px;"
											src="../images/button.png" alt=""> T???o b??o c??o
									</a></td>
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
</body>
</HTML>
<%
	try{
		if(rsKhuVuc!=null){
		rsKhuVuc.close();}
		if(rsNPP!=null){
		rsNPP.close();}
		if(obj!=null){obj.DBClose();
		obj = null;}
		session.setAttribute("obj",null);
	}catch(Exception er){
	
}
%>