<%@page import="java.sql.ResultSet"%>
<%@page import="geso.dms.center.beans.nhomkhachhangkm.INhomkhachhangkmList"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page  import = "java.util.List" %>
<%@ page  import = "java.util.Hashtable" %>
<%@ page  import = "geso.dms.center.util.*" %>
<%	
	String userId = (String) session.getAttribute("userId");  
	String userTen = (String) session.getAttribute("userTen");  	
	String sum = (String) session.getAttribute("sum");
	Utility util = (Utility) session.getAttribute("util");
	if(!util.check(userId, userTen, sum)){
		response.sendRedirect(request.getContextPath() + "/redirect.jsp");
	}else{
		int[] quyen = new  int[6];
		quyen = util.Getquyen("nhomkhachhangkmSvl","",userId);
%>
<%String url = util.getChuyenNguUrl("nhomkhachhangkmSvl", "",session); %>
<% Utility Util = new Utility(); %>
<% INhomkhachhangkmList obj = (INhomkhachhangkmList)session.getAttribute("obj"); %>
<% ResultSet Dskh = obj.getDskh(); %>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"> <% Utility Utilback = new Utility(); %>
<HTML>
<HEAD>
<TITLE><%= getServletContext().getInitParameter("TITLENAME") %></TITLE>
<META http-equiv="Content-Type" content="text/html; charset=utf-8">

<META http-equiv="Content-Style-Type" content="text/css">
	<link type="text/css" rel="stylesheet" href="../css/mybutton.css">
<LINK rel="stylesheet" href="../css/main.css" type="text/css">
<LINK rel="stylesheet" href="../css/calendar.css" type="text/css">

<script type="text/javascript" src="../scripts/jquery.min.1.7.js"></script>
<link href="../css/jquery-ui.css" rel="stylesheet" type="text/css" />
<script src="../scripts/ui/jquery.ui.core.js" type="text/javascript"></script>
<script src="../scripts/ui/jquery.ui.widget.js" type="text/javascript"></script>
<script src="../scripts/ui/jquery.ui.datepicker.js"
	type="text/javascript"></script>
<script type="text/javascript">
	$(document).ready(function() {		
			$( ".days" ).datepicker({			    
					changeMonth: true,
					changeYear: true				
			});            
        }); 		
		
</script>


<script type="text/javascript" language="JavaScript" src="../scripts/simplecalendar.js"></script>
<SCRIPT language="JavaScript" type="text/javascript">
function submitform()
{
	 document.nkhForm.action.value = "new";
   	 document.forms["nkhForm"].submit();
}

function searchform()
{
	 document.nkhForm.action.value = "search";
   	 document.forms["nkhForm"].submit();
}

function clearform()
{
	document.nkhForm.action.value = "xoa";
  	 document.forms["nkhForm"].submit();
}

function thongbao()
{var tt = document.forms['nkhForm'].msg.value;
	if(tt.length>0)
    alert(document.forms['nkhForm'].msg.value);
	}
</SCRIPT>
</HEAD>
<BODY leftmargin="0" bottommargin="0" topmargin="0" rightmargin="0">
<form name="nkhForm" method="post" action="../../nhomkhachhangkmSvl">
<% redis.clients.jedis.Jedis jedis =  geso.dms.center.util.Utility.getJedis(); %><% geso.dms.center.util.Csrf csdr=new geso.dms.center.util.Csrf(request,response,true,false,true);%>
 <input type="hidden" name="<%=csdr.get_tokenName() %>" value='<%=csdr.get_tokenValue() %>'>

<input type="hidden" name="userId" value='<%=userId%>'>
<input type="hidden" name="action" value="1">
<input type="hidden" name="msg" value='<%=Utility.GLanguage(obj.getmsg(),session,jedis) %>'>
<script language="javascript" type="text/javascript">
    thongbao();
</script> 

<TABLE width="100%" border="0" cellspacing="0" cellpadding="0"
	height="100%">
	<TR>
		<TD colspan="4" align='left' valign='top' bgcolor="#FFFFFF">
			<TABLE width="100%" border="0" cellpadding="0" cellspacing="1">
				<TR>
					<TD align="left" class="tbnavigation">
				   		<table width="100%" border="0" cellpadding="0" cellspacing="0">
					  		<tr height="22">
						  		<TD align="left" colspan="2" class="tbnavigation"><%= url %></TD> 
						  		<TD colspan="2" align="right" class="tbnavigation"><%=Utility.GLanguage("Chào mừng bạn",session,jedis) %>  <%=userTen %>&nbsp;  </TD>
						  	</tr>
						</table></TD>
			  	</TR>
			</TABLE>
			
			<TABLE width="100%" border="0" cellpadding="0">
				<TR>
					<TD >
						<FIELDSET><LEGEND class="legendtitle">&nbsp;<%=Utility.GLanguage("Tiêu chí tìm kiếm",session,jedis) %>&nbsp;</LEGEND>
							<TABLE  width="100%" cellspacing="0" cellpadding="6">
								
								<TR>
									<TD width="19%" class="plainlabel"><%=Utility.GLanguage("Diễn giải",session,jedis) %> </TD>
								    <TD width="81%" class="plainlabel">
										<TABLE cellpadding="0" cellspacing="0">
											<TR><TD>
												<INPUT name="diengiai" type="text" size="40" value='<%=obj.getDiengiai()%>' onChange ="searchform();">
											</TD></TR>
										</TABLE>								
								</TR>
								<TR>
									<TD class="plainlabel"><%=Utility.GLanguage("Trạng thái",session,jedis) %> </TD>
								  	<TD  class="plainlabel"><SELECT name = "trangthai"  onChange = "searchform();">
								  	<% if (obj.getTrangthai().equals("0")){ %>
								    	<option value=""> </option>
								    	<option value="1"><%=Utility.GLanguage("Hoạt động",session,jedis) %></option>
								    	<option value="0" selected><%=Utility.GLanguage("Ngưng hoạt động",session,jedis) %></option>
									<%}else{ 							
								  		if (obj.getTrangthai().equals("1")){ %>
								    	<option value=""> </option>
								    	<option value="1" selected><%=Utility.GLanguage("Hoạt động",session,jedis) %></option>
								    	<option value="0" ><%=Utility.GLanguage("Ngưng hoạt động",session,jedis) %></option>
									<%}else{ %>
								    	<option value="" selected> </option>
								    	<option value="1" ><%=Utility.GLanguage("Hoạt động",session,jedis) %></option>
								    	<option value="0" ><%=Utility.GLanguage("Ngưng hoạt động",session,jedis) %></option>
									<%}} %>

										</SELECT></TD>
									
								</TR>
								
										<TR>
											<TD class="plainlabel" ><%=Utility.GLanguage("Từ ngày",session,jedis) %> </TD>
											<TD class="plainlabel" colspan="3">
												<TABLE cellpadding="0" cellspacing="0">
													<TR><TD>
														<input class="days" type="text" name="tungay" value='<%=obj.getTungay() %>'  size="20" onchange = "searchform();">
													</TD>
													
													</TR>
												</TABLE>																					
		  									</TD>
										</TR>
										<TR>
                                          <TD class="plainlabel" ><%=Utility.GLanguage("Đến ngày",session,jedis) %> </TD>
										  <TD class="plainlabel" colspan="3">
										  		<TABLE cellpadding="0" cellspacing="0"><TR><TD>
										 <input class="days" type="text" name="denngay" value='<%=obj.getDenngay() %>' size="20" onchange = "searchform();">
										  		</TD>
										

											  </TR>
											 </TABLE>
									  </TR>
								<TR>
									<TD class="plainlabel">
									<a class="button2" href="javascript:clearform()">
	<img style="top: -4px;" src="../images/button.png" alt=""><%=Utility.GLanguage("Nhập lại",session,jedis) %></a>&nbsp;&nbsp;&nbsp;&nbsp;	
									
                                  </TD>								
									<TD class="plainlabel">&nbsp;</TD>										
								</TR>
								
							</TABLE>
					  </FIELDSET>
					</TD>	
				</TR>
			</TABLE>
			
			<TABLE width="100%" border = "0" cellpading = "0" cellspacing = "0">
				<TR>
					<TD width="100%">
					<FIELDSET>
					<LEGEND class="legendtitle">&nbsp;<%=Utility.GLanguage("Nhóm khách hàng",session,jedis) %> &nbsp;&nbsp;&nbsp;
					<%if(quyen[Utility.THEM]!=0) {%>
					<a class="button3" href="javascript:submitform()">
    	<img style="top: -4px;" src="../images/New.png" alt=""><%=Utility.GLanguage("Tạo mới",session,jedis) %> </a>                            
					<%} %>
					</LEGEND>
					<TABLE class="" width="100%">
						<TR>
							<TD width="98%">
							<TABLE width="100%" border="0" cellspacing="1" cellpadding="4">
								<TR class="tbheader">
									<TH width="19%"><%=Utility.GLanguage("Diễn giải",session,jedis) %> </TH>
									<TH width="9%"><%=Utility.GLanguage("Trạng thái",session,jedis) %> </TH>
									<TH width="8%"><%=Utility.GLanguage("Ngày tạo",session,jedis) %> </TH>
									<TH width="13%"><%=Utility.GLanguage("Người tạo",session,jedis) %> </TH>
									<TH width="9%"><%=Utility.GLanguage("Ngày sửa",session,jedis) %></TH>
									<TH width="12%"><%=Utility.GLanguage("Người sửa",session,jedis) %> 
									<TH width="9%"><%=Utility.GLanguage("Tác vụ",session,jedis) %></TH>
								</TR>
						<% 
							
							int m = 0;
							String lightrow = "tblightrow";
							String darkrow = "tbdarkrow";
							while (Dskh.next()){
								if (m % 2 != 0) {%>						
									<TR class= <%=lightrow%> >
								<%} else {%>
									<TR class= <%= darkrow%> >
								<%} %>										
															
									<TD><%=Dskh.getString("diengiai") %></TD>
									<% if(Dskh.getString("trangthai").trim().equals("1")) {%>
										<TD align="center"><%=Utility.GLanguage("Hoạt động",session,jedis) %></TD>
									<%}else {%>
										<TD align="center"><%=Utility.GLanguage("Ngưng hoạt động",session,jedis) %></TD>
									<%} %>
									<TD align="center"><%=Dskh.getString("ngaytao") %></TD>
									<TD align="center"><%=Dskh.getString("nguoitao") %></TD>
									<TD align="center"><%=Dskh.getString("ngaysua") %></TD>
									<TD align="center"><%=Dskh.getString("nguoisua") %> </TD>
									<TD align="center">
										<TABLE>
											<TR><TD>
												<%if(quyen[Utility.SUA]!=0) {%>
												<A href = "../../RouterSvl?task=<%= Util.dongMa(getServletContext().getInitParameter("RedirectNoScript") +"NhomkhachhangkmUpdateSvl?userId="+userId+"&update="+Dskh.getString("pk_seq")+"")%>"><img src="../images/Edit20.png" alt="Cap nhat" title="Cập nhật" width="20" height="20" longdesc="Cap nhat" border = 0></A>
												<%} %>
											</TD>
											<TD>&nbsp;</TD>
											<TD>
												<%if(quyen[Utility.XOA]!=0){ %>
												<A href = "../../RouterSvl?task=<%= Util.dongMa(getServletContext().getInitParameter("RedirectNoScript") +"nhomkhachhangkmSvl?userId="+userId+"&delete="+Dskh.getString("pk_seq")+"")%>"><img src="../images/Delete20.png" alt="Xoa" title="Xóa" width="20" height="20" longdesc="Xoa" border=0 onclick="if(!confirm('Ban Co Muon Xoa Nhom Khach Hang Nay?')) return false;"></A>
												<%} %>
												</TD>
											</TR>												
										</TABLE>									
									</TD>
								</TR>
							<%m++; }%>
							
								<TR>
									<TD align="center" colspan="12" class="tbfooter">&nbsp;</TD>
								</TR>
							</TABLE>
							</TD>
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
<%}%>