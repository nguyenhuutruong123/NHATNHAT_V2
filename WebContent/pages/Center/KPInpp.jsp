<%@page import="geso.dms.center.beans.stockintransit.IStockintransit"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page  import = "geso.dms.center.util.*" %>
<%	String userId = (String) session.getAttribute("userId");  
	String userTen = (String) session.getAttribute("userTen");  	
	String sum = (String) session.getAttribute("sum");
	IStockintransit obj = (IStockintransit) session.getAttribute("obj");	
		
	String loi=obj.getMsg();
	String tungay=obj.gettungay();
	String denngay=obj.getdenngay();
	Utility util = (Utility) session.getAttribute("util");
	if(!util.check(userId, userTen, sum)){
		response.sendRedirect(request.getContextPath() + "/redirect.jsp");
	}else{ %>


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
	document.forms['rpForm'].dataerror.value="";
	document.forms['rpForm'].submit();
}
function LayTheoNgay(){
	
/* 	document.forms['rpForm'].tuthang.value=0;
	document.forms['rpForm'].denthang.value=0;
	document.forms['rpForm'].tunam.value=0;
	document.forms['rpForm'].dennam.value=0; */
	
	//
/* 	document.forms['frm'].tuthang.disabled="disabled";
	document.forms['frm'].denthang.disabled="disabled";
	document.forms['frm'].tunam.disabled="disabled";
	document.forms['frm'].dennam.disabled="disabled"; */
	//
	document.forms['rpForm'].typeid.value=0;
	
}
function LayTheoThang(){
	//lay theo thang.
	document.forms['rpForm'].typeid.value=1;
	/* document.forms['frm'].tuthang.disabled="";
	document.forms['frm'].denthang.disabled="";
	document.forms['frm'].tunam.disabled="";
	document.forms['frm'].dennam.disabled=""; */
}
</SCRIPT>
</HEAD>
<BODY leftmargin="0" bottommargin="0" topmargin="0" rightmargin="0">
<form name="rpForm" method="post" action="../../KPInpp">

<% redis.clients.jedis.Jedis jedis =  geso.dms.center.util.Utility.getJedis(); %><% geso.dms.center.util.Csrf csdr=new geso.dms.center.util.Csrf(request,response,true,false,true);%>
 <input type="hidden" name="<%=csdr.get_tokenName() %>" value='<%=csdr.get_tokenValue() %>'>
<input type="hidden" name="userId" value= <%= userId %> >
<input type="hidden" name="action" value='1'>
	<input type="hidden" name="typeid" value=<%=obj.gettype()%>> 
<TABLE width="100%" border="0" cellspacing="0" cellpadding="0"
	height="100%">
	
	<TR>
		<TD colspan="4" align='left' valign='top' bgcolor="#FFFFFF">
			<TABLE width="100%" cellpadding="0" cellspacing="1">		
				<TR>
					<TD width="100%" align="left">
					  <table width="100%" border="0" cellpadding="0" cellspacing="0">
						  <tr height="22">
						   <TD align="left" colspan="2" class="tbnavigation">
						   B??o c??o qu???n tr??? > Theo d??i th??nh t??ch > Y???u t??? n???n t???ng NH??N VI??N B??N H??NG</TD>
   
						   <TD colspan="2" align="right" class="tbnavigation">Ch??o m???ng  <%=userTen %>&nbsp;</TD>
						  </tr>
 					  </table>					</TD>
				</TR>
				<tr>
					<TD align="left" colspan="4" class="legendtitle">
						<FIELDSET>
						<LEGEND class="legendtitle"><%=Utility.GLanguage("B??o l???i nh???p li???u",session,jedis) %></LEGEND>
	    				<textarea name="dataerror"  style="width: 100% ; color:#F00 ; font-weight:bold"  style="width: 100% ; color:#F00 ; font-weight:bold" readonly="readonly" rows="1">
	    					<%=loi%>
	    				</textarea>
						</FIELDSET>
				   </TD>
				</tr>	
				<TR>
					<TD>
					<TABLE border="0" width="100%" cellpadding="0" cellspacing="0">
						<TR>
							<TD width="100%" align="center" >
							<FIELDSET>
							  <LEGEND class="legendtitle">Ti??u chi xu???t b??o c??o</LEGEND>
							<TABLE  width="100%" cellpadding="6" cellspacing="0">
								
								<TR>
									<TD class="plainlabel">Xem theo</TD>
									<TD class="plainlabel" colspan="3">
									<% if(obj.gettype().equals("1")){ %>
										<input type="radio" name="xemtheo" onchange="LayTheoNgay();" value="0" />Ng??y &nbsp;&nbsp;&nbsp;
										<input type="radio" name="xemtheo" onchange="LayTheoThang();" value="1" checked="checked"/>Th??ng
									<%}else{ %>
										<input type="radio" name="xemtheo" onchange="LayTheoNgay();" value="0" checked="checked" />Ng??y &nbsp;&nbsp;&nbsp;
										<input type="radio" name="xemtheo" onchange="LayTheoThang();" value="1" />Th??ng
									<%} %>
									</TD>
								</TR>
								<TR>
								  	<TD width="15%" class="plainlabel" ><%=Utility.GLanguage("T??? ng??y",session,jedis) %></TD>
								  	<TD width="20%" class="plainlabel" >
										<TABLE cellpadding="0" cellspacing="0">
											<TR><TD>
												<input class="days" type="text" name="tungay" size="20" value = "<%=tungay%>" >
												</TD>												
                                    		</TR>
										</TABLE>									
										</TD>
								
									<TD width="15%" class="plainlabel" ><%=Utility.GLanguage("?????n ng??y",session,jedis) %> </TD>
								  	<TD  width="50%" class="plainlabel" >
							  			<TABLE cellpadding="0" cellspacing="0">
							  				<TR>
							  					<TD>
													<input type="text" class="days" name="denngay" size="20" value = "<%=denngay%>" >												</TD>
												<TD>                                      		
												
                                     		</TR>
										</TABLE>									</TD>
								</TR>
									<TR  class="plainlabel">
									<TD class="plainlabel">T??? th??ng</TD>
									<TD class="plainlabel">
									 <select name="tuthang"  style="width :50px" onchange="loctien()">
									<option value=0> </option>  
									<%
  										int k=1;
  									for(k=1;k<=12;k++){
  										String chuoi=k<10?"0"+k:""+k;
  									  if(obj.getFromMonth().equals(chuoi)){
  									%>
									<option value=<%=k%> selected="selected" > <%=k%></option> 
									<%
 										}else{
 									%>
									<option value=<%=k%> ><%=k%></option> 
									<%
 										}
 									  }
 									%>
									</select>
									<select name="tunam"  style="width :50px" onchange="loctien()">
									<option value=0> </option>  
									<%
									  
  										int n;
  										for(n=2008;n<2015;n++){
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
								
									<TD class="plainlabel">T???i th??ng</TD>
									<TD class="plainlabel">
									 <select name="denthang" style="width :50px" onchange="loctien()">
									<option value=0> </option>  
									<%
  									
  									for(k=1;k<=12;k++){
  										String chuoi=k<10?"0"+k:""+k;
  									  if(obj.getToMonth().equals(chuoi)){
  									%>
									<option value=<%=k%> selected="selected" > <%=k%></option> 
									<%
 										}else{
 									%>
									<option value=<%=k%> ><%=k%></option> 
									<%
 										}
 									  }
 									%>
									</select>
									<select name="dennam"  style="width :50px" onchange="loctien()">
									<option value=0> </option>  
									<%
									  
  									
  										for(n=2008;n<2015;n++){
  										if(obj.getToYear().equals(""+n)){
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
							    <TR  class="plainlabel" >
									<TD colspan="4" class="plainlabel">
									<a class="button2" href="javascript:submitform()" >
	<img style="top: -4px;" src="../images/button.png" alt=""><%=Utility.GLanguage("T???o b??o c??o",session,jedis) %></a>&nbsp;&nbsp;&nbsp;&nbsp;                                    </TD>
								</TR>
							</TABLE>
							</FIELDSET>						</TD>
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
try{
	if(obj!=null){
		obj.DBclose();
		obj=null;
	}
	util=null;
	
	session.setAttribute("obj",null);
}catch(Exception er){
	
}
	}%>