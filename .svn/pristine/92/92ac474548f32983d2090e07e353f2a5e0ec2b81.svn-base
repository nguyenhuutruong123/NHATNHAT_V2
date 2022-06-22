<%@page import="geso.dms.center.beans.nganhhang.INganhHang"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page  import = "geso.dms.center.util.*" %>
<%@ page  import = "java.sql.ResultSet" %>
<%	String userId = (String) session.getAttribute("userId");  
	String userTen = (String) session.getAttribute("userTen");  	
	String sum = (String) session.getAttribute("sum");
	Utility util = (Utility) session.getAttribute("util");
	if(!util.check(userId, userTen, sum)){
		response.sendRedirect(request.getContextPath() + "/redirect.jsp");
	}else{ %>

<% INganhHang nhBean = (INganhHang)session.getAttribute("nhBean"); %>
<% ResultSet dvkdList = (ResultSet)nhBean.getDvkdList();
String url = util.getChuyenNguUrl("NganhHangSvl", "",session);
%>


<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"> <% Utility Utilback = new Utility(); %>
<% Utility Util = new Utility(); %>
<HTML>
<HEAD>
<TITLE><%= getServletContext().getInitParameter("TITLENAME") %></TITLE>
<META http-equiv="Content-Type" content="text/html; charset=utf-8">

<META http-equiv="Content-Style-Type" content="text/css">
<LINK rel="stylesheet" type="text/css" media="print"
	href="../css/impression.css">
<LINK rel="stylesheet" href="../css/main.css" type="text/css">

<script type="text/javascript" src="../scripts/jquery.min.js"></script>
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
     document.forms['nhForm'].submit();
 }
 function saveform()
 {
	var kbhTen = document.getElementById('kbh');
	var diengiai = document.getElementById('diengiai');
	var dvkd = $("#dvkdSlt").val().trim();
	if(dvkd === "0") {
		alert("Bạn chưa chọn đơn vị kinh doanh!");
		return;
	}
	if(diengiai.value == "")
	{
		alert('Bạn chưa nhập diễn giải');
		return;
	}
 	document.forms['nhForm'].action.value = 'save';
    document.forms['nhForm'].submit();
 }
</SCRIPT>

</HEAD>
<BODY leftmargin="0" bottommargin="0" topmargin="0" rightmargin="0">
<form name='nhForm' method="post" action="../../NganhHangUpdateSvl">
<% redis.clients.jedis.Jedis jedis =  geso.dms.center.util.Utility.getJedis(); %><% geso.dms.center.util.Csrf csdr=new geso.dms.center.util.Csrf(request,response,true,false,true);%>
 <input type="hidden" name="<%=csdr.get_tokenName() %>" value='<%=csdr.get_tokenValue() %>'>

<input type="hidden" name="action" value='1'>
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
							   <TD align="left" colspan="2" class="tbnavigation">
							   	&nbsp; <%= url %> > <%=Utility.GLanguage("Tạo mới",session,jedis) %></TD>
							   <TD colspan="2" align="right" class="tbnavigation"><%=Utility.GLanguage("Chào mừng bạn",session,jedis) %> <%=userTen %>&nbsp;  </TD> 
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
									<TD width="30" align="left"><A href="../../RouterSvl?task=<%= Util.dongMa(getServletContext().getInitParameter("RedirectNoScript") +"NganhHangSvl?userId="+userId+"") %>" ><img src="../images/Back30.png" alt="Quay ve"  title="Quay ve" border="1" longdesc="Quay ve" style="border-style:outset"></A></TD>
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
						<LEGEND class="legendtitle"><%=Utility.GLanguage("Báo lỗi nhập liệu",session,jedis) %></LEGEND>
				
		    				<textarea name="dataerror"  style="width: 100% ; color:#F00 ; font-weight:bold" style="width: 100%" readonly="readonly" rows="1" ><%= nhBean.getMessage() %></textarea>
						</FIELDSET>
				   </TD>
				</TR>
				
				  	<tr>
					   <TD align="left" colspan="4" class="legendtitle">
							<FIELDSET>
							<LEGEND class="legendtitle"><%=Utility.GLanguage("Thông tin ngành hàng",session,jedis) %>
							</LEGEND>
							<TABLE class="tblight" width="100%" cellspacing="0" cellpadding="6">
								<TR>
								  <TD width="15%" class="plainlabel" ><%=Utility.GLanguage("Ngành hàng",session,jedis) %> <FONT class="erroralert">*</FONT></TD>
								  <TD  class="plainlabel" ><INPUT name="ten" id="kbh" type="text" value='<%= nhBean.getTen() %>' size="20"></TD>
							 	  <TD class="plainlabel" ></TD>
								  <TD class="plainlabel" >
												<%--   <select name="thuexuat" onChange="submitform();">
												  
												<% if (nhBean.getThuexuat()==10){%>
												  	<option value="10" selected>10%</option>
												  	<option value="5">5%</option>							  
												<%}else{%>																					 	 
												  	<option value="10" >10%</option>
												  	<option value="5" selected>5%</option>
												<%}%>
										          </select> --%>
								 </TD>
							  </TR>
								<TR>
								  <TD class="plainlabel" ><%=Utility.GLanguage("Diễn giải",session,jedis) %><FONT class="erroralert">*</FONT></TD>
								  <TD class="plainlabel" >
								  	<INPUT name="diengiai" id="diengiai" type="text" value='<%= nhBean.getDiengiai() %>' size="80">
								  </TD>
							 	  <TD class="plainlabel"><%=Utility.GLanguage("Đơn vị kinh doanh",session,jedis) %> <FONT class="erroralert">*</FONT></TD>
				                  <TD class="plainlabel">
				                      <select name="dvkd" id="dvkdSlt">
				                        	<option value="0"  ></option>
											<% 
				                                try{											
				                                    while (dvkdList.next()){%>													
				                                        <%	if(dvkdList.getString("pk_seq").equals(nhBean.getDvkdId())){ %>											
				                                                <option value= <%=dvkdList.getString("pk_seq")%> selected><%= dvkdList.getString("donvikinhdoanh") %></option>															
				                                            <%}else{%>
				                                                <option value= <%=dvkdList.getString("pk_seq")%> ><%= dvkdList.getString("donvikinhdoanh") %></option>																																		
				                                        <%	}
				                                    }
				                                } catch(java.sql.SQLException e){
				                                    
				                                }
				                            %>
				                        </select>
				                  </TD>
							  </TR>
							
							  
								<TR>
								  <TD  class="plainlabel" colspan="4"><label>
									<%  if (nhBean.getTrangthai().equals("1")){%>
									  	<input name="trangthai" type="checkbox" value ="1" checked="checked">
									<%} else {%>
										<input name="trangthai" type="checkbox" value ="1">
									<%} %>
								    <%=Utility.GLanguage("Hoạt động",session,jedis) %></label></TD>
								
							  </TR>
							</TABLE>
							</FIELDSET>
						</TD>	
					</TR>
			</TABLE>
		</TD>
	</TR>
 
</TABLE>
<%geso.dms.center.util.Utility.JedisClose(jedis); %>
</form>
</BODY>
</HTML>
<%
	nhBean.DBClose();
	}%>