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
		response.sendRedirect("/HTP/index.jsp");
	}else{ %>
	<%
		IStockintransit obj = (IStockintransit) session.getAttribute("obj");
		ResultSet kenh = obj.getkenh();
		ResultSet vung = obj.getvung();
		ResultSet khuvuc = obj.getkhuvuc();
		ResultSet npp = obj.getnpp();
		ResultSet dvkd = obj.getdvkd();
		ResultSet dvdl = obj.getdvdl();
		ResultSet gsbh = obj.getgsbh();
		ResultSet ddkd = obj.getRsddkd();
		ResultSet tctctRs = obj.getTctctRs();
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
<link href="../css/select2.css" rel="stylesheet" />
<script src="../scripts/select2.js"></script>
<script>
$(document).ready(function()
{
	$("#nppId").select2();
	$("#vungId").select2();
	$("#khuvucId").select2();
	$("#ddkdId").select2();
});
</script>

<script type="text/javascript"
	src="../scripts/report-js/action-report.js"></script>
<script language="javascript" type="text/javascript">	
	function submitform() {
		var fieldShow = document.getElementsByName("fieldsHien");		
		for (var i = 0; i < fieldShow.length; ++i) {
			fieldShow.item(i).checked = true;
		}	
		document.forms['frm'].action.value= 'Taomoi';
		document.forms["frm"].submit();
		reset();
	}
	function submitform2() {
		var fieldShow = document.getElementsByName("fieldsHien");		
		for (var i = 0; i < fieldShow.length; ++i) {
			fieldShow.item(i).checked = true;
		}	
		document.forms['frm'].action.value= 'Taomoi2';
		document.forms["frm"].submit();
		reset();
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
	<form name="frm" method="post" action="../../BCThucDatChiTieuPivot">
	<input type="hidden" name="action" value='1'>
	<input type="hidden" name="view" value='NPP'>
	<input type="hidden" name="userId" value='<%=userId%>'>
		<div id="main" style="width: 99%; padding-left: 2px">
			<div align="left" id="header" style="width: 100%; float: none">
				<div style="width: 70%; padding: 5px; float: left"
					class="tbnavigation">Qu???n l?? ch??? ti??u > B??o c??o > Th???c hi???n ch??? ti??u c???a NVBH  </div>
				<div align="right" style="padding: 5px" class="tbnavigation">
					<%=Utility.GLanguage("Ch??o m???ng b???n",session,jedis) %>  <%=userTen%></div>
			</div>
			<div align="left" id="button"
				style="width: 100%; height: 32px; padding: 0px; float: none"
				class="tbdarkrow">
				<A href="#"> <img
					src="../images/Back30.png" alt="Quay ve" title="Quay ve"
					border="1px" longdesc="Quay ve" style="border-style: outset"></A>
			</div>
			<div align="left" style="width: 100%; float: none; clear: left">
				<fieldset>
					<legend class="legendtitle"> <%=Utility.GLanguage("B??o l???i nh???p li???u",session,jedis) %></legend>
					<textarea id="errors" name="errors" rows="1" style="width: 100% ; color:#F00 ; font-weight:bold">
					<%= obj.getMsg() %></textarea>
				</fieldset>
			</div>
			<div align="left"
				style="width: 100%; float: none; clear: left; font-size: 0.7em">
				<fieldset>
					<legend class="legendtitle"> Th???c hi???n ch??? ti??u c???a NH??N VI??N B??N H??NG</legend>
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
  										for(n=2014;n<2020;n++){
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
									
									<TD class="plainlabel">Lo???i</TD>
									<TD class="plainlabel">
										
										<SELECT name = "loainv" >
										<%String[] loaiArr = {"NVBH","NPP"}; int dech=0; %>
										<%	for (  dech = 0; dech < loaiArr.length ; dech++) { 
											System.out.println(" xxxxxxxxxxxxxxxxxl : " +  dech);
										%>
											<%if( obj.getLoaiNv().equals( loaiArr[dech]) ) {%>
												<option value=<%=loaiArr[dech]%> selected><%=loaiArr[dech]%></option> 
											<%}else{ %>
												<option value=<%=loaiArr[dech]%> ><%=loaiArr[dech]%></option>
											<%} %>
										<%} %>
									</SELECT>
									</TD>
								</TR>
								<TR>
								
									
									<TD class="plainlabel">T??? th??ng</TD>
									<TD>
										<SELECT name = "month" >
										<%for(int i = 1; i <=12; i++ ) { %>
											<%if(Utility.parseDouble(obj.getMonth()) == i){ %>
												<option value = "<%=i %>" selected><%=i %></option>
											<%}else{ %>
												<option value = "<%=i %>" ><%=i %></option>
											<%} %>
										<%} %>

										</SELECT>
									
									</TD>
									<TD class="plainlabel">?????n th??ng</TD>
										<TD>
											<SELECT name = "to_month" >
											<%for(int i = 1; i <=12; i++ ) { %>
												<%if(Utility.parseDouble(obj.getMonth()) == i){ %>
													<option value = "<%=i %>" selected><%=i %></option>
												<%}else{ %>
													<option value = "<%=i %>" ><%=i %></option>
												<%} %>
											<%} %>
	
											</SELECT>
										
										</TD>
								</TR>
								

								<TR>			
								
								
												
												
									<TD class="plainlabel">Chi nh??nh/NPP</TD>
									<TD class="plainlabel">
										<select name="nppId" id="nppId" style="width:250px" >
											<option value="" >All</option>
											<%if (npp != null)
													while (npp.next()) {
														if (npp.getString("pk_seq").equals(obj.getnppId())) {%>
											   <option value="<%=npp.getString("pk_seq")%>" selected><%=npp.getString("ten")%></option>
											   <%} else {%>
											   <option value="<%=npp.getString("pk_seq")%>"><%=npp.getString("ten")%></option>
											<%}}%>
										</select>
									</TD>						
									
									<TD class="plainlabel"><%=Utility.GLanguage("NH??N VI??N B??N H??NG",session,jedis) %></TD>
									<TD class="plainlabel">
										<select name="ddkdId" id="ddkdId" style="width:250px" >
											<option value="" >All</option>
											<%if (ddkd != null)
													while (ddkd.next()) {
														if (ddkd.getString("pk_seq").equals(obj.getDdkd())) {%>
											   <option value="<%=ddkd.getString("pk_seq")%>" selected><%=ddkd.getString("ten")%></option>
											   <%} else {%>
											   <option value="<%=ddkd.getString("pk_seq")%>"><%=ddkd.getString("ten")%></option>
											<%}}%>
										</select>
									</TD>		
									<%-- <TD class="plainlabel">Lo???i ch??? ti??u</TD>
									<TD class="plainlabel">
										<select name="tctctId" id="tctctId" style="width:250px">
											<option value="" ></option>
											<%if (tctctRs != null)
													while (tctctRs.next()) {
														if (tctctRs.getString("pk_seq").equals(obj.getTctctId())) {%>
											   <option value="<%=tctctRs.getString("pk_seq")%>" selected><%=tctctRs.getString("diengiai")%></option>
											   <%} else {%>
											   <option value="<%=tctctRs.getString("pk_seq")%>"><%=tctctRs.getString("diengiai")%></option>
											<%}}%>
										</select>
									</TD>				 --%>				
								</TR>	
														
								<TR>
									<td><a class="button"
										href="javascript:submitform();"> <img style="top: -4px;"
											src="../images/button.png" alt=""> T???o b??o c??o
									</a></td>
									<!-- <td colspan="3"><a class="button"
										href="javascript:submitform2();"> <img style="top: -4px;"
											src="../images/button.png" alt=""> B??o c??o theo s???n ph???m
									</a></td> -->
								</TR>
							</TABLE>
						</div>
						<hr>
						<div style="width: 100%; float: none;">
							
							
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
	try
	{
		if(!(ddkd == null))
			ddkd.close();
		if(dvdl != null)
			dvdl.close();
		if(dvkd != null)
			dvkd.close();
		if(gsbh != null)
			gsbh.close();
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