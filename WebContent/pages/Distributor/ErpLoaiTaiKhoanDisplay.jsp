<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@page import="java.sql.SQLException"%>
<%@ page import="geso.dms.distributor.beans.loaitaikhoan.*"%>
<%@ page import="geso.dms.distributor.beans.loaitaikhoan.imp.*"%>
<%@ page  import = "geso.dms.center.util.*" %>
<%@ page import="java.sql.ResultSet"%>
<%@ page import="java.util.Hashtable"%>
<%@ page import="java.util.List"%>

<%	String userId = (String) session.getAttribute("userId");  
	String userTen = (String) session.getAttribute("userTen");  	
	String sum = (String) session.getAttribute("sum");
	Utility util = (Utility) session.getAttribute("util");
	if(!util.check(userId, userTen, sum)){
		response.sendRedirect("/Phanam/index.jsp");
	}else{ %>

<%
	IErpLoaiTaiKhoan  ltkBean = (IErpLoaiTaiKhoan) session.getAttribute("ltkBean");
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd"> <% Utility Utilback = new Utility(); %>
<html>
<head>
<title>Phanam - Project</title>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<meta http-equiv="Content-Style-Type" content="text/css">
<link rel="stylesheet" type="text/css" media="print" href="../css/impression.css">
<link rel="stylesheet" href="../css/main.css" type="text/css">
<script>
	function goBack() {
	    window.history.back();
	}
	</script>
</head>
<BODY leftmargin="0" bottommargin="0" topmargin="0" rightmargin="0">
	<form name="tkForm" method="post" action="../../ErpLoaiTaiKhoanUpdateSvl">
		<input type="hidden" name="userId" value='<%=userId%>' /> 
		<input type="hidden" name="action" value='1' />
		<input type="hidden" name="id" value='<%=ltkBean.getId() %>' />
		<table width="100%" border="0" cellspacing="0" cellpadding="0">
			<tr>
				<td colspan="4" align='left' valign='top' bgcolor="#ffffff">
					<table width="100%" cellpadding="0" cellspacing="1">
						<tr>
							<td align="left" class="tbnavigation">
								<table width="100%" border="0" cellpadding="0" cellspacing="0">
									<tr height="22">
										<TD align="left" colspan="2" class="tbnavigation">&nbsp;Dữ liệu nền &gt; Kế toán &gt; Loại tài khoản > Hiển thị</TD>
										<td colspan="2" align="right" class="tbnavigation"><%=Utility.GLanguage("Chào mừng bạn",session,jedis) %>  <%=userTen%> &nbsp;
										</td>
									</tr>
								</table>
							</td>
						</tr>
					</table>
					
					<table width="100%" border="0" cellpadding="3" cellspacing="0">
						<tr>
							<td>
								<table width="100%" border="0" cellpadding="0" cellspacing="0">
									<tr class="tbdarkrow">
										<td width="30" align="left"><a href="javascript:goBack();"> <img src="../images/Back30.png" alt="Quay ve" title="Quay ve"
												border="1" longdesc="Quay ve" style="border-style: outset">
										</a></td>
										<td>&nbsp;</td>
									</tr>
								</table>
							</td>
						</tr>
					</table>
					<table width="100%" cellspacing="0" cellpadding="4">
						<tr>
							<TD align="left" colspan="4" class="legendtitle">
								<fieldset>
									<LEGEND class="legendtitle">Loại tài khoản kế toán </LEGEND>
									<TABLE width="100%" cellspacing="0" cellpadding="6">
										<tr>
											<td width="15%" class="plainlabel">Mã tắt<font class="erroralert">*</font></td>
											<td class="plainlabel"><input type="text" readonly name="Ma" id="Ma" size="40" id="Ma" value="<%= ltkBean.getMa()%>"></td>
										</tr>
										<tr>
											<td class="plainlabel">Tên <font class="erroralert">*</font></td>
											<td class="plainlabel"><input type="text" readonly name="Ten"  id="Ten" value="<%= ltkBean.getTen()%>"></td>
										</tr>
								
								<TR>
									<TD class = "plainlabel"><%=Utility.GLanguage("Hoạt động",session,jedis) %></TD>					
									<TD colspan="7" class = "plainlabel">
									<%
									   if(ltkBean.getTrangThai().equals("1")){ %>
										<input name="TrangThai" checked="checked" disabled type="checkbox" value="1">
									<%}else{ %>
										<input name="TrangThai" type="checkbox" disabled value="1" >
									<%} %>
									</TD>											
						</TR>				
								
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
	ltkBean.closeDB();
	
}%>