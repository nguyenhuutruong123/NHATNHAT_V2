<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page  import = "geso.dms.center.beans.nhanhang.INhanhang" %>
<%@ page  import = "java.sql.ResultSet" %>
<%@ page  import = "geso.dms.center.util.*" %>
<%	String userId = (String) session.getAttribute("userId");  
	String userTen = (String) session.getAttribute("userTen");  	
	String sum = (String) session.getAttribute("sum");
	Utility util = (Utility) session.getAttribute("util");
	if(!util.check(userId, userTen, sum)){
		response.sendRedirect(request.getContextPath() + "/redirect.jsp");
	}else{ %>
<% INhanhang nhBean = (INhanhang)session.getAttribute("nhBean");%>
<% ResultSet dvkdlist = (ResultSet)nhBean.getDvkdList(); 
String url = util.getChuyenNguUrl("NhanhangSvl", "",session);
%>
<% Utility Util = new Utility(); %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"> <% Utility Utilback = new Utility(); %>
<HTML>
<HEAD>
<TITLE><%= getServletContext().getInitParameter("TITLENAME") %></TITLE>
<META http-equiv="Content-Type" content="text/html; charset=utf-8">

<META http-equiv="Content-Style-Type" content="text/css">
<LINK rel="stylesheet" href="../css/main.css" type="text/css">

<SCRIPT language="javascript" type="text/javascript">
</SCRIPT>
<SCRIPT language="JavaScript" type="text/javascript">
<!--HPB_SCRIPT_CODE_40
//-->
function submitform()
{
	var nhanhang = document.getElementById("nhanhang").value;
	var dvkdId = document.getElementById("dvkdId").value;
	if(nhanhang == '')
	{
		alert("Vui lòng nhập tên nhãn hàng."); return;	
	}
	
	if(dvkdId == '0')
	{
		alert("Vui lòng chọn đơn vị kinh doanh."); return;	
	}
    document.forms["nhanhangForm"].submit();
}

function focusDvkd(){
	alert("Không thể thay đổi vì đã có sản phẩm thuộc Nhãn và DVDK này");	
	submitform();
}

</SCRIPT>
</HEAD>
<BODY leftmargin="0" bottommargin="0" topmargin="0" rightmargin="0">
<form name="nhanhangForm" method="post" action="../../NhanhangUpdateSvl" charset="utf-8">
<% redis.clients.jedis.Jedis jedis =  geso.dms.center.util.Utility.getJedis(); %><% geso.dms.center.util.Csrf csdr=new geso.dms.center.util.Csrf(request,response,true,false,true);%>
 <input type="hidden" name="<%=csdr.get_tokenName() %>" value='<%=csdr.get_tokenValue() %>'>

<INPUT name="userId" type="hidden" value='<%=userId %>' size="30">
<INPUT name="nhId" type="hidden" value='<%=nhBean.getId() %>' size="30">

<TABLE width="100%" border="0" cellspacing="0" cellpadding="0"
	height="100%">
	
	<TR>
		<TD colspan="4" align='left' valign='top' bgcolor="#FFFFFF">

		<TABLE width="100%" cellpadding="0" cellspacing="1">
			
				<TR>
					<TD align="left">
					  <table width="100%" border="0" cellpadding="0" cellspacing="0">
						  <tr height="22">
						   <TD align="left" colspan="2" class="tbnavigation">&nbsp; <%= url %> > <%=Utility.GLanguage("Hiển thị",session,jedis) %> </TD>
   
						   <TD colspan="2" align="right" class="tbnavigation"><%=Utility.GLanguage("Chào mừng bạn",session,jedis) %>  <%=userTen %>&nbsp;  </TD>
						  </tr>
 					  </table>
					</TD>
				</TR>
		</TABLE>		
		<TABLE width="100%" border="0" cellpadding="0" cellspacing="1">
				<TR ><TD >
						<TABLE width="100%" border="0" cellpadding="0" cellspacing="0">
								<TR class = "tbdarkrow">
									<TD width="30" align="left"><A href="../../RouterSvl?task=<%= Util.dongMa(getServletContext().getInitParameter("RedirectNoScript") +"NhanhangSvl?userId="+userId+"") %>" ><img src="../images/Back30.png" alt="Quay ve"  title="Quay ve" border="1" longdesc="Quay ve" style="border-style:outset"></A></TD>
								    <TD width="2" align="left" ></TD>
								    <TD width="30" align="left" ></TD>
									<TD >&nbsp; </TD>						
								</TR>
						</TABLE>
				</TD></TR>
		</TABLE>
					
		<TABLE width = 100% cellpadding = "4" cellspacing = "0" border = "0">
		  	<tr>
				<TD align="left" colspan="4" class="legendtitle">
					<FIELDSET>
					<LEGEND class="legendtitle"><%=Utility.GLanguage("Báo lỗi nhập liệu",session,jedis) %></LEGEND>
				
	    				<textarea name="dataerror"  style="width: 100% ; color:#F00 ; font-weight:bold"  style="width: 100% ; color:#F00 ; font-weight:bold" readonly="readonly" rows="1"><%=nhBean.getMessage()%></textarea>
						<% nhBean.setMessage(""); %>
						</FIELDSET>
				   </TD>
			</tr>			
			<TR>
				<TD width="100%" align="left" >
					<FIELDSET>
					<LEGEND class="legendtitle">&nbsp;<%=Utility.GLanguage("Nhãn hàng mới",session,jedis) %></LEGEND>

					<TABLE  width="100%" cellpadding="6" cellspacing="0">
					<TR>
							<TD width="20%" class="plainlabel"><%=Utility.GLanguage("Mã",session,jedis) %>  <FONT class="erroralert">*</FONT></TD>
							<TD width="80%" class="plainlabel">
								<INPUT 	name="ma" id="ma" 	value='<%=nhBean.getMa()%>' type="text" size="40"/>
							</TD>
						</TR>
						<TR>
							<TD width="20%" class="plainlabel"><%=Utility.GLanguage("Nhãn hàng",session,jedis) %> <FONT class="erroralert">*</FONT></TD>
							<TD width="80%" class="plainlabel"><INPUT name="nhanhang" id="nhanhang" value='<%=nhBean.getNhanhang()%>' type="text" size="40"/></TD>
						</TR>
				      	<TR>
							<TD width="19%" class="plainlabel"><%=Utility.GLanguage("Đơn vị kinh doanh",session,jedis) %> <FONT class="erroralert">*</FONT></TD>
							  	<TD class="plainlabel" >
								<TABLE cellpadding="0" cellspacing="0" border="0">
									<TR>
								  		<TD>
								  		<% if (nhBean.AllowtoChangeDvkd()) {%>
								  			<SELECT  name="dvkdId" id="dvkdId">
								  		<%}else{ %>
								  			<SELECT  name="dvkdId" id="dvkdId" onFocus="focusDvkd();">
										<% }
									
											try{%>
												<option value="0"  ></option>
											<% 	while (dvkdlist.next()){%>
													
													<%	if(dvkdlist.getString("pk_seq").equals(nhBean.getDvkdId())){ %>											
															
															<option value= <%=dvkdlist.getString("pk_seq")%> selected><%= dvkdlist.getString("donvikinhdoanh") %></option>															
														<%}else{%>
															<option value= <%=dvkdlist.getString("pk_seq")%> ><%= dvkdlist.getString("donvikinhdoanh") %></option>																																		
														<%	}
														}
															
													}catch(java.sql.SQLException e){
														
													}%>														                                           
                                      			</SELECT>
                                   			</TD>
										</TR>
									</TABLE>									
								</TD>

						</TR>

						<TR>
							<% if(nhBean.getTrangthai().equals("1")) {%>						
						  		<TD class="plainlabel"><input name="trangthai" type="checkbox" value="1" checked>
						  	<%}else{ %>
						  		<TD class="plainlabel"><input name="trangthai" type="checkbox" value="0" >
						  	<%} %>
							      <%=Utility.GLanguage("Hoạt động",session,jedis) %> </TD>
							<TD class="plainlabel">&nbsp;</TD>
						</TR>
								
				</TABLE>

				</FIELDSET>
			</TD>
		</TR>
	</TABLE>
	</TD></TR>
</TABLE>
<%geso.dms.center.util.Utility.JedisClose(jedis); %>
</form>
</BODY>
</HTML>
<% if(dvkdlist != null) 
	dvkdlist.close(); 
 
	nhBean.DBClose();
}%>