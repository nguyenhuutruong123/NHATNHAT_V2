<%@page import="java.sql.ResultSet"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page  import = "geso.dms.center.beans.vung.IVungmien" %>
<%@ page  import = "geso.dms.center.util.*" %>
<%	String userId = (String) session.getAttribute("userId");  
	String userTen = (String) session.getAttribute("userTen");  	
	String sum = (String) session.getAttribute("sum");
	Utility util = (Utility) session.getAttribute("util");
	if(!util.check(userId, userTen, sum)){
		response.sendRedirect(request.getContextPath() + "/redirect.jsp");
	}
	else { %>
<% 
	IVungmien vmBean = (IVungmien)session.getAttribute("vmBean"); 
	ResultSet bms = (ResultSet)vmBean.getBms(); 
	String url = util.getChuyenNguUrl("VungmienSvl", "",session);
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"> <% Utility Utilback = new Utility(); %>
<HTML>
<HEAD>
<TITLE><%= getServletContext().getInitParameter("TITLENAME") %></TITLE>
<META http-equiv="Content-Type" content="text/html; charset=utf-8">

<META http-equiv="Content-Style-Type" content="text/css">
<LINK rel="stylesheet" type="text/css" media="print"
	href="../css/impression.css">
<LINK rel="stylesheet" href="../css/main.css" type="text/css">

<SCRIPT language="javascript" type="text/javascript">
 function confirmLogout(){
    if(confirm("Ban co muon dang xuat?"))
    {
		top.location.href = "home.jsp";
    }
    return
  }
 function submitform()
 {
     document.forms['vmForm'].submit();
 }
 function saveform()
 {
 	 document.forms['vmForm'].action.value= 'save';
     document.forms['vmForm'].submit();
 }
</SCRIPT>

</HEAD>
<BODY leftmargin="0" bottommargin="0" topmargin="0" rightmargin="0">
<form name='vmForm' method="post" action="../../VungmienUpdateSvl">
<% redis.clients.jedis.Jedis jedis =  geso.dms.center.util.Utility.getJedis(); %><% geso.dms.center.util.Csrf csdr=new geso.dms.center.util.Csrf(request,response,true,false,true);%>
 <input type="hidden" name="<%=csdr.get_tokenName() %>" value='<%=csdr.get_tokenValue() %>'>

<input type="hidden" name="action" value='1'>
<input type="hidden" name="id" value='<%= vmBean.getId() %>'>
<INPUT name="userId" type="hidden" value='<%= userId %>' size="30">
<TABLE width="100%" border="0" cellspacing="0" cellpadding="0"
	height="100%">
	<TR>
		<TD colspan="4" align='left' valign='top' bgcolor="#ffffff">
			<TABLE width="100%">
				<TR>
					<TD align="left" class="tbnavigation">

					   	<TABLE width="100%" border="0" cellpadding="0" cellspacing="0">
						  <tr height="22">
							   <TD align="left" colspan="2" class="tbnavigation"><%= url %> > <%=Utility.GLanguage("C???p nh???t",session,jedis) %> </TD>
							   <TD colspan="2" align="right" class="tbnavigation"><%=Utility.GLanguage("Ch??o m???ng b???n",session,jedis) %>  <%=userTen %>&nbsp;  </TD> 
						    </tr>
   
						   	<tr>
						   		<TD align="left" height="5" colspan="4" class=""></td>
   
  							</tr>
						</TABLE>
					</TD>
				</TR>
			</TABLE>	
			<TABLE width="100%" border="0" cellpadding="3" cellspacing="0">
				<TR ><TD >
						<TABLE width="100%" border="0" cellpadding="0" cellspacing="0">
								<TR class = "tbdarkrow">
									<TD width="30" align="left"><A href="../../RouterSvl?task=<%= Utilback.dongMa(getServletContext().getInitParameter("RedirectNoScript") +"VungmienSvl?userId="+userId +"")%>" ><img src="../images/Back30.png" alt="Quay ve"  title="Quay ve" border="1" longdesc="Quay ve" style="border-style:outset"></A></TD>
								    <TD width="2" align="left" ></TD>
								    <TD width="30" align="left" ><A href="javascript: saveform()" ><IMG src="../images/Save30.png" title="Luu lai" alt="Luu lai" border = "1"  style="border-style:outset"></A></TD>
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
				
		    				<textarea name="dataerror"  style="width: 100% ; color:#F00 ; font-weight:bold" style="width: 100%" readonly="readonly" rows="1" ><%= vmBean.getMessage() %></textarea>
						</FIELDSET>
				   </TD>
				</TR>
				
				  	<tr>
						   <TD align="left" colspan="4" class="legendtitle">
								<FIELDSET>
								<LEGEND class="legendtitle"><%=Utility.GLanguage("Th??ng tin v??ng mi???n",session,jedis) %>
								</LEGEND>
								<TABLE class="tblight" width="100%" cellspacing="0" cellpadding="6">
									<TR>
									  <TD width="15%" class="plainlabel" ><%=Utility.GLanguage("V??ng",session,jedis) %> <FONT class="erroralert">*</FONT></TD>
									  <TD width="70%" class="plainlabel" ><INPUT name="vungmien"
										type="text" value='<%= vmBean.getVungmien() %>' style="width:300px"></TD>
								  </TR>
									<TR>
									  <TD class="plainlabel" ><%=Utility.GLanguage("Di???n gi???i",session,jedis) %> <FONT class="erroralert">*</FONT> </TD>
									  <TD class="plainlabel" ><INPUT name="diengiai"
										type="text" value='<%= vmBean.getDiengiai() %>' style="width:300px"></TD>
								  </TR>
								  <%--  <TR>
								  <TD class="plainlabel">Qu???n l?? v??ng</TD>
								  <TD class="plainlabel"><SELECT name="bm">
								<%  try{
								  		while(bms.next()){								  			
								  			if (bms.getString("pk_seq").equals(vmBean.getBm())){ %>								  			
								  				<option value="<%= bms.getString("pk_seq")%>" selected><%= bms.getString("ten")%></option>
								  		  <%}else{ %>		
								  		  		<option value="<%= bms.getString("pk_seq")%>" ><%= bms.getString("ten")%></option>
								  	<%		}
								  		}
								  	}catch (java.sql.SQLException e){} %>
                                  </SELECT></TD>
							  	</TR> --%>
									<TR>
									  <TD width="15%" class="plainlabel" ><label>
										<%  if (vmBean.getTrangthai().equals("1")){%>
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
 
</TABLE><%geso.dms.center.util.Utility.JedisClose(jedis); %>
</form>
</BODY>
</HTML>
<% vmBean.closeDB(); %>
<%}%>