<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page  import = "geso.dms.center.beans.phuongxa.IPhuongxa" %>
<%@ page  import = "java.sql.ResultSet" %>
<%@ page  import = "geso.dms.center.util.*" %>
<%	String userId = (String) session.getAttribute("userId");  
	String userTen = (String) session.getAttribute("userTen");  	
	String sum = (String) session.getAttribute("sum");
	Utility util = new Utility();
	String url = util.getChuyenNguUrl("PhuongxaSvl", "",session);
	if(!util.check(userId, userTen, sum)){
		response.sendRedirect("/AHF/index.jsp");
	}else{ %>

<% IPhuongxa kvBean = (IPhuongxa)session.getAttribute("kvBean"); %>
<% ResultSet ttRs = (ResultSet)kvBean.getTinhthanhRs(); %>
<% ResultSet qhRs = (ResultSet)kvBean.getQuanhuyenRs(); %>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"> <% Utility Utilback = new Utility(); %>
<HTML>
<HEAD>
<TITLE><%= getServletContext().getInitParameter("TITLENAME") %></TITLE>
<META http-equiv="Content-Type" content="text/html; charset=utf-8">

<META http-equiv="Content-Style-Type" content="text/css">
<LINK rel="stylesheet" type="text/css" media="print" href="../css/impression.css">
<LINK rel="stylesheet" href="../css/main.css" type="text/css">
<LINK rel="stylesheet" href="../css/select2.css" type="text/css">

<SCRIPT language="javascript" type="text/javascript">
 
 function saveform()
 {
 	 document.forms['kvForm'].action.value= 'save';
     document.forms['kvForm'].submit();
 }
</SCRIPT>
</HEAD>
<BODY leftmargin="0" bottommargin="0" topmargin="0" rightmargin="0">
<form name='kvForm' method="post" action="../../PhuongxaUpdateSvl">
<% redis.clients.jedis.Jedis jedis =  geso.dms.center.util.Utility.getJedis(); %><% geso.dms.center.util.Csrf csdr=new geso.dms.center.util.Csrf(request,response,true,false,true);%>
 <input type="hidden" name="<%=csdr.get_tokenName() %>" value='<%=csdr.get_tokenValue() %>'>

<input type="hidden" name="action" value='1'>
<INPUT name="userId" type="hidden" value='<%= userId %>' size="30">
<INPUT name="id" type="hidden" value='<%= kvBean.getId() %>' size="30">
<TABLE width="100%" border="0" cellspacing="0" cellpadding="0"
	height="100%">
	<TR>
		<TD colspan="4" align='left' valign='top' bgcolor="#ffffff">
			<TABLE width="100%">
				<TR>
					<TD align="left" class="tbnavigation">

					   	<TABLE width="100%" border="0" cellpadding="0" cellspacing="0">
						  <tr height="22">
							   <TD align="left" colspan="2" class="tbnavigation">
							   		<%=url %> > <%=Utility.GLanguage("Hi???n th???",session,jedis) %> </TD>
							   <TD colspan="2" align="right" class="tbnavigation"><%=Utility.GLanguage("Ch??o m???ng b???n",session,jedis) %>  <%=userTen %>&nbsp;  </TD> 
						    </tr>
						</TABLE>
					</TD>
				</TR>
			</TABLE>	
			<TABLE width="100%" border="0" cellpadding="3" cellspacing="0">
				<TR ><TD >
						<TABLE width="100%" border="0" cellpadding="0" cellspacing="0">
								<TR class = "tbdarkrow">
									<TD width="30" align="left"><A href="javascript:history.back()" ><img src="../images/Back30.png" alt="Quay ve"  title="Quay ve" border="1" longdesc="Quay ve" style="border-style:outset"></A></TD>
								    <TD width="2" align="left" ></TD>
								    
									<TD >&nbsp; </TD>						
								</TR>
						</TABLE>
				</TD></TR>
			</TABLE>
				
			<TABLE width = 100% cellpadding = "3" cellspacing = "0" border = "0">
			  	<TR>
					<TD align="left" colspan="4" class="legendtitle">
						<FIELDSET>
						<LEGEND class="legendtitle"><%=Utility.GLanguage("B??o l???i nh???p li???u",session,jedis) %></LEGEND>
				
		    				<textarea name="dataerror"  style="width: 100% ; color:#F00 ; font-weight:bold" style="width: 100%" readonly="readonly" rows="1" ><%= kvBean.getMessage() %></textarea>
						</FIELDSET>
				   </TD>
				</TR>
				
				  	<tr>
						   <TD align="left" colspan="4" class="legendtitle">
								<FIELDSET>
								<LEGEND class="legendtitle"><%=Utility.GLanguage("Ph?????ng x??",session,jedis) %></LEGEND>
								<TABLE class="tblight" width="100%" cellspacing="0" cellpadding="6">
									<TR>
									  <TD class="plainlabel" ><%=Utility.GLanguage("Di???n gi???i",session,jedis) %><FONT class="erroralert"> *</FONT></TD>
									  <TD class="plainlabel" ><INPUT name="diengiai" readonly="readonly" type="text" value='<%= kvBean.getDiengiai() %>' style="width:300px"></TD>
								  	</TR>
									
									<TR>
								    <TD width="20%" class="plainlabel"><%=Utility.GLanguage("T???nh th??nh",session,jedis) %><FONT class="erroralert"> *</FONT></TD>
								      <TD width="80%" class="plainlabel">
								      <SELECT name="tinhthanh" disabled="disabled">
								      	<option value='' ></option>
								        <% try{ while(ttRs.next()){ 
								    	if(ttRs.getString("pk_seq").equals(kvBean.getTinhthanhId())){ %>
								      		<option value='<%= ttRs.getString("pk_seq") %>' selected> <%= ttRs.getString("ten") %></option>
								      	<%}else{ %>
								     		<option value='<%= ttRs.getString("pk_seq") %>'> <%= ttRs.getString("ten") %></option>
								     	<%}}}catch(java.sql.SQLException e){} %>
								      </SELECT></TD>

									</TR>
									
									<TR>
										<TD width="20%" class="plainlabel"><%=Utility.GLanguage("Qu???n huy???n",session,jedis) %><FONT class="erroralert"> *</FONT></TD>
									      <TD width="80%" class="plainlabel">
									      <SELECT name="quanhuyen" disabled="disabled">
									      	<option value='' ></option>
									        <% try{ while(qhRs.next()){ 
									    	if(qhRs.getString("pk_seq").equals(kvBean.getQuanhuyenId())){ %>
									      		<option value='<%= qhRs.getString("pk_seq") %>' selected> <%= qhRs.getString("ten") %></option>
									      	<%}else{ %>
									     		<option value='<%= qhRs.getString("pk_seq") %>'> <%= qhRs.getString("ten") %></option>
									     	<%}}}catch(java.sql.SQLException e){} %>
									      </SELECT></TD>
									</TR>
								  
									<TR>
									  <TD width="15%" class="plainlabel" ><label>
										<%  if (kvBean.getTrangthai().equals("1")){%>
										  	<input name="trangthai" type="checkbox" value ="1" checked>
										<%} else {%>
											<input name="trangthai" type="checkbox" value ="0">
										<%} %>
									    <%=Utility.GLanguage("Ho???t ?????ng",session,jedis) %></label></TD>
										<TD  class="plainlabel" >&nbsp;</TD>
								  </TR>
								</TABLE>
								</FIELDSET>
							</TD>	
						</TR>
					</TABLE>
			</TD>
	</TR>
 
</TABLE>
</form>
<%geso.dms.center.util.Utility.JedisClose(jedis); %>
</body>  
<script type="text/javascript" src="../scripts/jquery.min.1.7.js"></script>
<script type='text/javascript' src='../scripts/loadingv2.js'></script>
<script type='text/javascript' src='../scripts/select2.js'></script>

<script>
$(document).ready(function()
{
	$("select").select2();
});
</script>
</HTML>
<%}%>