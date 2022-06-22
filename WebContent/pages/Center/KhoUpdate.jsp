<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page  import = "geso.dms.center.beans.kho.IKho" %>
<%@ page  import = "geso.dms.center.util.*" %>
<%	String userId = (String) session.getAttribute("userId");  
	String userTen = (String) session.getAttribute("userTen");  	
	String sum = (String) session.getAttribute("sum");
	Utility util = (Utility) session.getAttribute("util");
	if(!util.check(userId, userTen, sum)){
		response.sendRedirect("/KHUONGDUY/index.jsp");
	}else{ %>

<% IKho khoBean = (IKho)session.getAttribute("khoBean"); 
 
%>
<% Utility Utilback = new Utility(); %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<HTML>
<HEAD>
<TITLE><%= getServletContext().getInitParameter("TITLENAME") %></TITLE>
<META http-equiv="Content-Type" content="text/html; charset=utf-8">

<META http-equiv="Content-Style-Type" content="text/css">
<LINK rel="stylesheet" type="text/css" media="print"
	href="../css/impression.css">
<LINK rel="stylesheet" href="../css/main.css" type="text/css">

<SCRIPT language="JavaScript" type="text/javascript">
<!--HPB_SCRIPT_CODE_40
//-->
function submitform()
{
	document.forms["khoForm"].action.value='submit';
    document.forms["khoForm"].submit();
}
function saveform()
{
	
	document.forms["khoForm"].action.value='save';
    document.forms["khoForm"].submit();  
}
  
</SCRIPT>
</HEAD>
<BODY leftmargin="0" bottommargin="0" topmargin="0" rightmargin="0">
<form name="khoForm" method="post" action="../../KhoUpdateSvl">
<INPUT name="userId" type="hidden" value='<%=userId %>' size="30">
<INPUT name="khoId" type="hidden" value='<%=khoBean.getId() %>' size="30">
<INPUT name="action" type="hidden" value='' size="30">
<% redis.clients.jedis.Jedis jedis =  geso.dms.center.util.Utility.getJedis(); %>
<% geso.dms.center.util.Csrf csdr=new geso.dms.center.util.Csrf(request,response,true,false,true);%>
 <input type="hidden" name="<%=csdr.get_tokenName() %>" value='<%=csdr.get_tokenValue() %>'>

<TABLE width="100%" border="0" cellspacing="0" cellpadding="0" height="100%">
	<TR>
		<TD colspan="4" align='left' valign='top' bgcolor="#ffffff">
			<TABLE width="100%" cellpadding="0" cellspacing="1">
				<TR>
					<TD align="left" class="tbnavigation">

					   	<TABLE width="100%" border="0" cellpadding="0" cellspacing="0">
						  <tr height="22">
							   <TD align="left" colspan="2" class="tbnavigation">
							   		&nbsp;Thiết lập dữ liệu nền > Dữ liệu Kinh doanh > Khai báo kho> Cập nhật </TD>
							   <TD colspan="2" align="right" class="tbnavigation">Chào mừng bạn <%=userTen %> &nbsp;</td> 
						    </tr>
   
						</TABLE>
					</TD>
				</TR>
			</TABLE>	
			<TABLE width="100%" border="0" cellpadding="0" cellspacing="1">
				<TR ><TD >
						<TABLE width="100%" border="0" cellpadding="0" cellspacing="0">
								<TR class = "tbdarkrow">
									<TD width="30" align="left"><A href="../../RouterSvl?task=<%= Utilback.dongMa(getServletContext().getInitParameter("RedirectNoScript") +"KhoSvl?userId="+userId+"") %>" ><img src="../images/Back30.png" alt="Quay ve"  title="Quay ve" border="1" longdesc="Quay ve" style="border-style:outset"></A></TD>
								    <TD width="2" align="left" ></TD>
								    <TD width="30" align="left" ><A href="javascript:saveform()" ><IMG src="../images/Save30.png" title="Luu lai" alt="Luu lai" border = "1"  style="border-style:outset"></A></TD>
									<TD >&nbsp; </TD>						
								</TR>
						</TABLE>
				</TD></TR>
			</TABLE>
				
			<TABLE width = 100% cellpadding = "4" cellspacing = "0" border = "0">
				  	<tr>
							<TD align="left" colspan="4" class="legendtitle">
								<FIELDSET>
								<LEGEND class="legendtitle">Báo lỗi nhập liệu </LEGEND>
				
			    				<textarea name="dataerror"  style="width: 100% ; color:#F00 ; font-weight:bold"  style="width: 100% ; color:#F00 ; font-weight:bold" readonly="readonly" rows="1" ><%=khoBean.getMessage()%></textarea>
								<%khoBean.setMessage(""); %>
								</FIELDSET>
						   </TD>
					</tr>			

				  	<tr>
						   <TD align="left" colspan="4" class="legendtitle">
								<FIELDSET>
								<LEGEND class="legendtitle">Thông tin kho  
								</LEGEND>
								<TABLE class="tblight" width="100%" cellspacing="0" cellpadding="6">
									<TR>
									  <TD width="15%" class="plainlabel" >Tên kho <FONT class="erroralert">*</FONT></TD>
									  <TD  class="plainlabel" ><INPUT name="ten" style="width:300px" value ='<%=khoBean.getTen()%>' type="text"></TD>
								  </TR>
									<TR>
									  <TD class="plainlabel" >Diễn giải <FONT class="erroralert">*</FONT></TD>
									  <TD class="plainlabel" ><INPUT name="diengiai" style="width:300px" value ='<%=khoBean.getDiengiai()%>' type="text"></TD>
								  </TR>
								 	<TR> 
								  <TD class="plainlabel"> Loại kho </TD>
		                    	<TD class="plainlabel" >
		                    		<select onchange="submitform()" name="loaikho" style="width:300px">
											<option value="0" <%= khoBean.getLoaikho()==0 ? "selected":""  %>    > Kho hàng bán </option>
											<option value="1" <%= khoBean.getLoaikho()==1 ? "selected":""  %>    > Kho hàng Km </option>
									</select>
								</TD>
								   </TR>
									<TR>
									  <TD class="plainlabel" ><label>
									  <% if (khoBean.getTrangthai().equals("1")){ %>
									    	<input name="trangthai" type="checkbox" value="1" checked >
									  <%}else{ %>
									    	<input name="trangthai" type="checkbox" value="0" >
									  <%}%>
								      Hoạt động</label></TD>
									  <TD class="plainlabel" >&nbsp;</TD>
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
	khoBean.DBClose();
	khoBean = null;
	session.setAttribute("khoBean", null);
}
%>