<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page  import = "geso.dms.center.beans.thanhthinongthon.IThanhthinongthon" %>
<%@ page  import = "java.sql.ResultSet" %>
<%@ page  import = "geso.dms.center.util.*" %>
<%	String userId = (String) session.getAttribute("userId");  
	String userTen = (String) session.getAttribute("userTen");  	
	String sum = (String) session.getAttribute("sum");
	Utility util = (Utility) session.getAttribute("util");
	if(!util.check(userId, userTen, sum)){
		response.sendRedirect(request.getContextPath() + "/redirect.jsp");
	}else{ %>

<% IThanhthinongthon kvBean = (IThanhthinongthon)session.getAttribute("kvBean"); %>



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
  function submitform()
 {
     document.forms['kvForm'].submit();
 }
 function saveform()
 {
 	 document.forms['kvForm'].action.value= 'save';
     document.forms['kvForm'].submit();
 }
</SCRIPT>

</HEAD>
<BODY leftmargin="0" bottommargin="0" topmargin="0" rightmargin="0">
<form name='kvForm' method="post" action="../../ThanhthinongthonUpdateSvl">
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
							   		<%if(kvBean.getId().length() == 0) {%>
							   		D??? li???u n???n > Kinh doanh  > Th??nh th??? n??ng th??n > T???o m???i
							   		<%} else { %>
							   		D??? li???u n???n > Kinh doanh  > Th??nh th??? n??ng th??n > C???p nh???t 
							   		<%} %>
							   		</TD>
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
									<TD width="30" align="left"><A href="../../ThanhthinongthonSvl?userId=<%=userId %>" ><img src="../images/Back30.png" alt="Quay ve"  title="Quay ve" border="1" longdesc="Quay ve" style="border-style:outset"></A></TD>
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
				
		    				<textarea name="dataerror"  style="width: 100% ; color:#F00 ; font-weight:bold" style="width: 100%" readonly="readonly" rows="1" ><%= kvBean.getMessage() %></textarea>
						</FIELDSET>
				   </TD>
				</TR>
				
				  	<tr>
						   <TD align="left" colspan="4" class="legendtitle">
								<FIELDSET>
								<LEGEND class="legendtitle">Th??ng tin th??nh th??? n??ng th??n
								</LEGEND>
								<TABLE class="tblight" width="100%" cellspacing="0" cellpadding="6">
									<TR>
									  <TD width="15%" class="plainlabel" >Th??nh th??? n??ng th??n<FONT class="erroralert">*</FONT></TD>
									  <TD  class="plainlabel" ><INPUT name="ttnt"
										type="text" value='<%= kvBean.getTen() %>' style="width:300px"></TD>
								  </TR>
									<TR>
									  <TD class="plainlabel" ><%=Utility.GLanguage("Di???n gi???i",session,jedis) %><FONT class="erroralert">*</FONT></TD>
									  <TD class="plainlabel" ><INPUT name="diengiai"
										type="text" value='<%= kvBean.getDiengiai() %>' style="width:300px"></TD>
								  </TR>
								 <%--  <TR>
									  <TD class="plainlabel" ><%=Utility.GLanguage("Khu v???c",session,jedis) %><FONT class="erroralert">*</FONT></TD>
									  <TD class="plainlabel" ><SELECT name="khuvuc">
								      	<option value=''></option>
								        <% try{ while(khuvucRs.next()){ 
								    	if(khuvucRs.getString("pk_seq").equals(kvBean.getKhuvucId())){ %>
								      		<option value='<%= khuvucRs.getString("pk_seq") %>' selected> <%= khuvucRs.getString("Ten") %></option>
								      	<%}else{ %>
								     		<option value='<%= khuvucRs.getString("pk_seq") %>'> <%= khuvucRs.getString("Ten") %></option>
								     	<%}}}catch(java.sql.SQLException e){} %>
								     	
								      </SELECT></TD>
								  </TR> --%>
								    
									<TR>
									  <TD width="24%" class="plainlabel" ><label>
										<%  if (kvBean.getTrangthai().equals("Hoat dong")){%>
										  	<input name="trangthai" type="checkbox" value ="1" checked>
										<%} else {%>
											<input name="trangthai" type="checkbox" value ="0">
										<%} %>
									    Ho???t ?????ng</label></TD>
										<TD width="70%" class="plainlabel" >&nbsp;</TD>
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
</BODY>
</HTML>
<%
	kvBean.closeDB();
}%>