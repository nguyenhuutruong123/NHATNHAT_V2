<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page  import = "geso.dms.center.beans.dieuchuyenkhuyenmai.IDieuchuyenkhuyenmai" %>
<%@ page  import = "java.util.Iterator" %>
<%@ page  import = "java.util.List" %>
<%@ page  import = "java.util.ArrayList" %>
<%@ page  import = "java.sql.ResultSet" %>
<%@ page  import = "geso.dms.center.util.*" %>
<%@ page  import = "java.util.Hashtable" %>
<%@ page  import = "java.text.DecimalFormat" %>
<%@ page  import = "java.text.NumberFormat" %>

<%	String userId = (String) session.getAttribute("userId");  
	String userTen = (String) session.getAttribute("userTen");  	
	String sum = (String) session.getAttribute("sum");
	Utility util = (Utility) session.getAttribute("util");
	if(!util.check(userId, userTen, sum)){
		response.sendRedirect(request.getContextPath() + "/redirect.jsp");
	}else{ %>

<% IDieuchuyenkhuyenmai dckmBean = (IDieuchuyenkhuyenmai)session.getAttribute("dckm"); %>
<% String schemeId = (String)session.getAttribute("schemeId"); %>
<% ResultSet scheme = (ResultSet)dckmBean.getSchemeRS() ; %>
<% ResultSet kv = (ResultSet)dckmBean.getKv() ; %>
<% ResultSet npp = (ResultSet)dckmBean.getNpp(); %>
<% Hashtable<String, String> usedPro = (Hashtable<String, String>)dckmBean.getusedPro(); %>
<% Hashtable<String, String> adjust = (Hashtable<String, String>)dckmBean.getAdjust() ; %>
<% String[] dieuchuyen = (String[]) dckmBean.getdieuchuyen(); %>
<%NumberFormat formatter = new DecimalFormat("#,###,###"); %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"> <% Utility Utilback = new Utility(); %>
<%@page import="java.sql.SQLException"%>
<HTML>
<HEAD>
<TITLE><%= getServletContext().getInitParameter("TITLENAME") %></TITLE>
<META http-equiv="Content-Type" content="text/html; charset=utf-8">

<META http-equiv="Content-Style-Type" content="text/css">

<LINK rel="stylesheet" href="../css/main.css" type="text/css">
<link type="text/css" rel="stylesheet" href="../css/mybutton.css">
<SCRIPT language="JavaScript" type="text/javascript">
function submitform()
{
	document.forms['dckmForm'].submit();
}

function saveform()
{
	if (checkadjust()){
		document.dckmForm.action.value= "save";
		document.forms['dckmForm'].submit();
	}
}

function dieuchuyen()
{

	document.forms['dckmForm'].action.value="adjust";
	
	document.forms['dckmForm'].submit();
}


function adjust(amount){ 	
	var i = <%= dckmBean.getSize()%>;
	var npp = new Array(<%= dckmBean.getNppstr() %>);	
	var budget = <%= dckmBean.getRest() %>;
	var tong = 0;
	var diff = amount;
	alert(diff);
	for(x=0; x<i; x++){
		var dcId = "dc" + npp[x];
		var dc= document.getElementById(dcId).value;
		dc = dc.replace(',', '');
		dc = dc.replace(',', '');
		dc = dc.replace(',', '');
		dc = dc.replace(',', '');
		dc = dc.replace(',', '');		

		if((parseFloat(dc) + parseFloat(diff)) >= 0 ){
			dc = Math.round(parseFloat(dc) + parseFloat(diff));
			document.getElementById(dcId).value = dc;
		}else{
			document.getElementById(dcId).value = 0;
			tong = tong + (Math.round(parseFloat(dc) + parseFloat(diff)));			
		}							
	}
	if(tong < 0){ 
		tong = Math.round(tong/i);
		adjust(tong);
	}		

}
function keypress(e) //H??m d??ng d? ngan ngu?i d??ng nh?p c??c k?? t? kh??c k?? t? s? v??o TextBox
{    
	var keypressed = null;
	if (window.event)
		keypressed = window.event.keyCode; //IE
	else
		keypressed = e.which; //NON-IE, Standard
	
	if (keypressed < 48 || keypressed > 57)
	{ 
		if (keypressed == 8 || keypressed == 127 || keypressed == 37 || keypressed == 39)
		{//Ph??m Delete v?? Ph??m Back
			return;
		}
		return false;
	}
}
function checkadjust(){
	var dieuchuyen = document.getElementsByName("dieuchuyen");
	var tongchung = document.getElementById("tong");
	tongchung.value = tongchung.value.replace(',', '');
	tongchung.value = tongchung.value.replace(',', '');
	tongchung.value = tongchung.value.replace(',', '');
	var i;
	var tong  =0;
	for(i=0;i<dieuchuyen.length ;i++)
	{
		tong = tong + parseInt(dieuchuyen[i].value);
	}
 	  if(tong != parseInt(tongchung.value)){
		alert("??i???u chuy???n v?????t m???c ng??n tong :" +tong+" tong chung :" +tongchung.value);
		return false;
	}
 	  
 return true;
 }

</SCRIPT>
</HEAD>
<BODY leftmargin="0" bottommargin="0" topmargin="0" rightmargin="0">
<form name ="dckmForm" method="post" action="../../DieuchuyenkhuyenmaiSvl" >
<input type="hidden" name="action" value="0">

<TABLE width="100%" border="0" cellspacing="0" cellpadding="0"
	height="100%">
	<TR>
		<TD colspan="4" align='left' valign='top' bgcolor="#FFFFFF">
			<TABLE width="100%" border="0" cellpadding="0" cellspacing="1">
				<TR>
					<TD align="left" class="tbnavigation">
						<table width="100%" border="0" cellpadding="0" cellspacing="0">
						  <tr height="22">
							 <TD align="left" colspan="2" class="tbnavigation">&nbsp;Qu???n l?? khuy???n m??i &gt; ??i???u chuy???n ng??n s??ch </TD> 
							 <TD colspan="2" align="right" class="tbnavigation"><%=Utility.GLanguage("Ch??o m???ng b???n",session,jedis) %>  <%=userTen %>&nbsp;  </TD></tr>
						</table>
			 		</TD>
				</TR>
			</TABLE>
		<TABLE width="100%" border="0" cellpadding="0" cellspacing="1">
			<TR ><TD >
					<TABLE width="100%" border="0" cellpadding="0" cellspacing="0">
						<TR class = "tbdarkrow">
							<TD width="30" align="left"><A href="javascript:saveform();"  ><img src="../images/Save30.png" alt="Quay ve"  title="Luu lai" border="1" longdesc="Luu lai" style="border-style:outset"></A></TD>

							<TD >&nbsp; </TD>						
						</TR>
					</TABLE>
			</TD></TR>
		</TABLE>
			<TABLE width="100%" border="0" cellpadding="0"  cellspacing="1" >
			  	<tr>
					<TD align="left" colspan="4" class="legendtitle">
						<FIELDSET>
						<LEGEND class="legendtitle"><%=Utility.GLanguage("B??o l???i nh???p li???u",session,jedis) %></LEGEND>
				
	    				<textarea name="dataerror"  style="width: 100% ; color:#F00 ; font-weight:bold" style="width: 100%" readonly="readonly"  rows="1"><%= dckmBean.getMessage() %></textarea>

						</FIELDSET>
				   </TD>
				</tr>			

				<TR>
				  <TD height="100%" width="100%">
						<FIELDSET >
						<LEGEND class="legendtitle" style="color:black">??i???u chuy???n ng??n s??ch khuy???n m??i</LEGEND>
						<TABLE border="0" width="100%" cellpadding="4" cellspacing="0">
						  <TR>
							  	<TD width="20%" class="plainlabel">Ch????ng tr??nh</TD>
						  	  	<TD class="plainlabel">
									<SELECT name="schemeId" >
								    <option value="0"></option>
								      <% try{while(scheme.next()){ 
								    	if(scheme.getString("pk_seq").equals(dckmBean.getSchemeId())){ %>
								      		<option value='<%=scheme.getString("pk_seq")%>' selected><%=scheme.getString("scheme") %></option>
								      	<%}else{ %>
								     		<option value='<%=scheme.getString("pk_seq")%>'><%=scheme.getString("scheme") %></option>
								     	<%}}}catch(java.sql.SQLException e){} %>	  
                        				</SELECT>						  	  	
						  	  	
						  	  	</TD>
						  </TR>

						  <TR>						    
							    <TD class="plainlabel"><B></B><%=Utility.GLanguage("Khu v???c",session,jedis) %> </TD>
							    <TD class="plainlabel" >
							    <SELECT name="kvId" >
								    <option value="0"></option>
								      <% try{while(kv.next()){ 
								    	if(kv.getString("pk_seq").equals(dckmBean.getKvId())){ %>
								      		<option value='<%=kv.getString("pk_seq")%>' selected><%=kv.getString("diengiai") %></option>
								      	<%}else{ %>
								     		<option value='<%=kv.getString("pk_seq")%>'><%=kv.getString("diengiai") %></option>
								     	<%}}}catch(java.sql.SQLException e){} %>	  
                        				</SELECT>			
                        		</TD>
                        		                        				
					      </TR>

						  <TR>
						    	<TD class="plainlabel" >T???ng ng??n s??ch khuy???n m??i c???a Khu V???c</TD>
						    	<%Float budget = Float.valueOf(dckmBean.getBudget()).floatValue(); %>
							    <TD class="plainlabel" ><INPUT type="text" name="tong" id ="tong" value="<%=formatter.format(Math.round(budget)) %>" disabled=disabled> VND </TD> 	
                        		
					      </TR>

						  <TR>
						    	<TD class="plainlabel" >T???ng ng??n s??ch ???? s??? d???ng </TD>
						    	<%Float used = Float.valueOf(dckmBean.getUsedBudget()).floatValue(); %>
							    <TD class="plainlabel" ><INPUT type="text" name="dadung" id ="dadung" value="<%=formatter.format(Math.round(used)) %>" disabled=disabled> VND </TD> 	
                        		
					      </TR>

						  <TR>
						    	<TD class="plainlabel" >T???ng ng??n s??ch c??n l???i </TD>
							    <TD class="plainlabel"><INPUT type="text" name="conlai" id ="conlai" value="<%=formatter.format(Math.round(budget - used)) %>" disabled=disabled> VND </TD> 	
                        		
					      </TR>

						  <TR>
									<TD class="plainlabel" >Ti??u ch?? ??i???u chuy???n</TD>
									<TD class="plainlabel"><select name="tieuchi"">
								  	<% if (dckmBean.getTieuchi().equals("0")){ %>
								    	<option value="0" selected>C???nh tranh</option>
								    	<option value="1">Ti??u chu???n</option>
								    	<option value="2">L???ch s???</option>
									<%}else{ 							
										if (dckmBean.getTieuchi().equals("1")){ %>
								    	<option value="0" >C???nh tranh </option>
								    	<option value="1" selected>Ti??u chu???n</option>
								    	<option value="2">L???ch s???</option>
									<%}else{ %>
								    	<option value="0" >C???nh tranh </option>
								    	<option value="1" >Ti??u chu???n</option>
								    	<option value="2" selected>L???ch s???</option>
									<%}} %>
								    	</select></TD>
							</TR>
							<TR>							  	
						  	  	<TD class="plainlabel" colspan="2">
						  	  	<a class="button2"  href="javascript:submitform()">
	                       <img style="top: -4px;" src="../images/button.png" alt="">C???p nh???t</a>&nbsp;&nbsp;&nbsp;&nbsp;	
						  	  	
						  	 
						  	  	</TD>
						  </TR>
								</TABLE>
						
					</TD>
				
				
				</TR>
				  
				
					<table cellpadding="0" cellspacing="1" style="width:100%" >
							<tr class="tbheader">
						  <TH width="10%" >M?? ?????i t??c </TH>
						  <TH width="30%" >T??n NPP</TH>
						  <TH width="15%" >Ng??n s??ch</TH>
						  <TH width="15%" >???? s??? d???ng</TH>
						  <TH width="15%">C??n l???i</TH>
						  <TH width="15%" >??i???u chuy???n</TH>
						  </tr>

						  <%
						  int dadung =0;
							int conlai =0;
							
							
						  try{
							    String lightrow = "tblightrow";
								String darkrow = "tbdarkrow";
								int m = 0;
								long rest = 0;
								if(npp != null){
									while(npp.next()){
										dadung = dadung + Integer.parseInt(npp.getString("dasudung"));
										conlai = conlai + Integer.parseInt(npp.getString("conlai"));
									if (m % 2 != 0) {%>						
										<TR class= <%=lightrow%> >
									<%} else {%>
										<TR class= <%= darkrow%> >
									<%}%>
											<TD align="left"><div align="center"><%= npp.getString("pk_seq")%></div></TD>                                   
											<TD align="left"><div align="left"><%=npp.getString("ten") %></div></TD>                                   
							  				<TD align="left"><div align="right"><%=npp.getString("ngansach") %></div></TD>
							  				<TD align="left"><div align="right"><%=npp.getString("dasudung") %></div></TD>
							  				<TD align="left"><div align="right"><%=npp.getString("conlai") %></div></TD>
							  				<TD align="left"><div align="right"><input type ="text" name ="dieuchuyen" value=' <%= dieuchuyen[Integer.parseInt(npp.getString("pk_seq"))]%>' onkeypress="return keypress(event);"></div></TD>
							  			 </TR>
							  
						  		<% m++ ;} 
						  		
						  		}%>		
						  		
						  <%}catch(java.sql.SQLException e){}%>
						<tr class="tbfooter">
						<td colspan="13">
						&nbsp;
						</td>
						</tr>
					</table>
					</fieldset>
					</td>	
				</tr>	
				<SCRIPT language="JavaScript" type="text/javascript">
				 var dadung = document.getElementById("dadung");
				 var conlai = document.getElementById("conlai");
				 dadung.value = <%= dadung %>;
				 conlai.value =<%= conlai %>;
				</SCRIPT>
			</TABLE>
			</TD>
		</TR>
		</TABLE>
		
		</form>
		</BODY>
		</HTML>
<%}%>