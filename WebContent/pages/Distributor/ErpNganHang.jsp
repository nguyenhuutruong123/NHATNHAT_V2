<%@page import="java.sql.SQLException"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page  import = "java.sql.ResultSet" %>
<%@ page  import = "geso.dms.center.util.*" %>
<%@ page  import = "geso.dms.distributor.beans.nganhang.IErpNganHangList" %>
<%@ page  import = "geso.dms.distributor.beans.nganhang.imp.*" %>
<%	String userId = (String) session.getAttribute("userId");  
	String userTen = (String) session.getAttribute("userTen");  	
	String sum = (String) session.getAttribute("sum");
	Utility util = (Utility) session.getAttribute("util");
	if(!util.check(userId, userTen, sum)){
		response.sendRedirect("/Phanam/index.jsp");
	}else{ %>
<%
	IErpNganHangList nhList = (IErpNganHangList)session.getAttribute("nhList");
	ResultSet NhList = (ResultSet)nhList.getNhList();
	 int[] quyen = new  int[5];
		quyen = util.Getquyen("ErpNganHangSvl","",userId);
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"> <% Utility Utilback = new Utility(); %>

<html>
<head>
<title>Phannam - Ngan Hang</title>
	<link type="text/css" rel="stylesheet" href="../css/mybutton.css">
<meta http-equiv="Content-Style-Type" content="text/css">
<link rel="stylesheet" href="../css/main.css" type="text/css">
<link rel="stylesheet" href="../css/calendar.css" type="text/css">

<script language="javascript" type="text/javascript">

function xoaform()
{
    document.nhListForm.MA.value = "";
    document.nhListForm.TEN.value = "";
    submitform();
}

function submitform()
{
	document.forms['nhListForm'].action.value= 'search';
	document.forms['nhListForm'].submit();
}

function newform()
{
	document.forms['nhListForm'].action.value= 'new';
	document.forms['nhListForm'].submit();
}

function thongbao()
{
	 var tt = document.forms["nhListForm"].msg.value;
	 if(tt.length>0)
    	alert(document.forms["nhListForm"].msg.value);
	}

</script>
</head>
<body leftmargin="0" bottommargin="0" topmargin="0" rightmargin="0">
<form name="nhListForm" method="post" action="../../ErpNganHangSvl">
<input type="hidden" name="userId" value='<%=userId%>'>
<input type="hidden" name="userTen" value='<%=userTen%>'> 
<input type="hidden" name="action" value='1'>
<input type="hidden" name="msg" value='<%= nhList.getMsg() %>'>
<input type="hidden" name="chixem" value='<%= nhList.getChixem() %>'>
<script language="javascript" type="text/javascript">
    thongbao();
</script> 
<table width="100%" border="0" cellspacing="0" cellpadding="0"
	height="100%">
	
	<tr>
		<td colspan="4" align='left' valign='top' bgcolor="#FFFFFF">
			<table width="100%" cellpadding="0" cellspacing="1">		
				<tr>
					<td align="left">
					  <table width="100%" border="0" cellpadding="0" cellspacing="0">
						  <tr height="22">
						   <td align="left" colspan="2" class="tbnavigation">&nbsp;D??? li???u n???n &gt; K??? to??n &gt; Ng??n h??ng
						   </TD>
						   <td align = "right" colspan="2" class="tbnavigation"><%=Utility.GLanguage("Ch??o m???ng b???n",session,jedis) %>  <%=userTen %> &nbsp;</td>
						  </tr>
 					  </table>
					</td>
				</tr>
				<tr>
					<td>
					<table border="0" width="100%" cellpadding="0" cellspacing="0">
						<tr>
							<td width="100%" align="center" >
							<fieldset>
							  <legend class="legendtitle"><%=Utility.GLanguage("Ti??u ch?? t??m ki???m",session,jedis) %> &nbsp;</legend>
							<table width="100%" cellpadding="6" cellspacing="0">
								<tr>
								  	<td class="plainlabel">M??</td>
								    <TD class="plainlabel"><input type="text" name="MA" value="<%=nhList.getMA() %>" onchange="submitform()"></td>
						      	</tr>
						      	
						      	<tr>
								  	<td class="plainlabel">T??n</td>
								    <TD class="plainlabel"><input type="text" name="TEN" value="<%=nhList.getTEN() %>" onchange="submitform()"></td>
						      	</tr>

							    <tr>
									<td colspan="2" class="plainlabel">
									<a class="button2" href="javascript:xoaform()">
									<img style="top: -4px;" src="../images/button.png" alt=""><%=Utility.GLanguage("Nh???p l???i",session,jedis) %></a>&nbsp;&nbsp;&nbsp;&nbsp;
									</td>									
								</tr>
								
							</table>

							</fieldset>
						</td>
						</tr>
					</table>
					</td>
				</tr>

				<tr>
					<td width="100%">
						<fieldset>
						<legend class="legendtitle">&nbsp;Ng??n h??ng&nbsp;&nbsp;&nbsp;
						
						<%-- <%if( quyen[0]!=0  ){ %>
							<a class="button3" href="javascript:newform()">
	    					<img style="top: -4px;" src="../images/New.png" alt=""><%=Utility.GLanguage("T???o m???i",session,jedis) %> </a>      
    					<%} %>   --%>
    					                    
					 	</legend>
	
		   				<table width="100%" border="0" cellpadding="0" cellspacing="0">
			  				<tr>
			  	   				<td align="left" colspan="4" class="legendtitle">
									<table width="100%" border="0" cellspacing="1" cellpadding="4">
										<tr class="tbheader">
											<th width="6%">M??</th>
											<th width="30%">T??n</th>	
											<th width="9%"><%=Utility.GLanguage("Tr???ng th??i",session,jedis) %></th>										
											<th width="5%"><%=Utility.GLanguage("Ng??y t???o",session,jedis) %></th>
											<th width="9%"><%=Utility.GLanguage("Ng?????i t???o",session,jedis) %></th>
											<th width ="5%"><%=Utility.GLanguage("Ng??y s???a",session,jedis) %></th>											
											<th width ="9%"><%=Utility.GLanguage("Ng?????i s???a",session,jedis) %></th>											
											<th width="4%" align="center">&nbsp;T??c v???</th>					
										</tr>
				
									<%
									int m = 0;
									String lightrow = "tblightrow";
									String darkrow = "tbdarkrow";
									
									if(NhList != null)
									while (NhList.next())
									{
										if(m % 2 != 0) {%>
										<tr class = <%=lightrow%> >
									<%} else {%>
										<tr class = <%=darkrow%> >
									<%}%>
									
										<td align="center"><%=NhList.getString("MA")%> </td>
										<td align="left"><%=NhList.getString("TEN")%> </td>
										<% 
										if(NhList.getString("trangthai").equals("1")){
										%>	
											<td align="center">Ho???t ?????ng </td>
											<%
										}else{
											%>
											<td align="center">Ng??ng Ho???t ?????ng </td>
											<% 
										}
										%>								
										<td align="center"><%=NhList.getString("NGAYTAO")%> </td>
										<td align="center"><%=NhList.getString("NGUOITAO")%> </td>
										<td align="center"><%=NhList.getString("NGAYSUA")%> </td>										
										<td align="center"><%=NhList.getString("NGUOISUA")%> </td>
										
										
										<td align = "center">
											<table border = 0 cellpadding="0" cellspacing="0">
												<tr>
												<%-- <td>
												<%if(quyen[2]!=0  ){ %>
													<a href = "../../ErpNganHangUpdateSvl?userid=<%=userId%>&update=<%=NhList.getString("PK_SEQ")%>"><img title="C???p nh???t" src="../images/Edit20.png" alt="Chinh sua" width="20" height="20" longdesc="Chinh sua" border = 0></A>&nbsp;
													<%} %>
												</td>
												<td>
												<%if(quyen[1]!=0 ){ %>
												<a href = "../../ErpNganHangSvl?userid=<%=userId%>&delete=<%=NhList.getString("PK_SEQ")%>"><img title="X??a" src="../images/Delete20.png" alt="Xoa" width="20" height="20" longdesc="Xoa" border="0" onclick="if(!confirm('B???n c?? mu???n x??a ng??n h??ng n??y ?')) return false; "></a>&nbsp;
												<%} %>
												</td> --%>
												<td>
												<!-- QUYEN VIEW DLN -->
													<a href = "../../ErpNganHangUpdateSvl?userid=<%=userId%>&display=<%=NhList.getString("PK_SEQ")%>"><img title="Hi???n th???" src="../images/Display20.png" title="Hi???n th???" alt="Hien thi" width="20" height="20" longdesc="Hien thi" border = 0></A>
												<!-- END QUYEN VIEW DLN -->
												</td>
												</tr>
											</table>				
										</tr>
									
									<% 	m++;
									}%>									
									
										<tr>
											<td height="26" colspan = "11" align="center" class="tbfooter">&nbsp;	</td>
										</tr>
									</table>
								</td>
							</tr>
						</table>

					</fieldset>
					</td>
				</tr>
			</table>
		</td>
	</tr>
</table>
</form>
</body>
</HTML>
<%
if(NhList != null) NhList.close();
nhList.DBClose();

session.setAttribute("nhList",null);
}%>