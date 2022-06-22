<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page  import = "geso.dms.center.beans.donvidoluong.IDonvidoluong" %>
<%@ page  import = "geso.dms.center.beans.donvidoluong.IDonvidoluongList" %>
<%@ page  import = "java.util.Iterator" %>
<%@ page  import = "java.util.List" %>
<%@ page  import = "java.sql.ResultSet" %>
<%@ page  import = "java.util.ArrayList" %>
<%@ page  import = "geso.dms.center.util.*" %>

<%	String userId = (String) session.getAttribute("userId");  
	String userTen = (String) session.getAttribute("userTen");  	
	String sum = (String) session.getAttribute("sum");
	Utility util = (Utility) session.getAttribute("util");
	if(!util.check(userId, userTen, sum)){
		response.sendRedirect(request.getContextPath() + "/redirect.jsp");
	}else{ %>

<% IDonvidoluongList obj = (IDonvidoluongList)session.getAttribute("obj"); %>
<% ResultSet donvilist = (ResultSet)obj.getDonvilist(); 
	int[] quyen = new  int[6];
	quyen = util.Getquyen("DonvidoluongSvl","",userId);
	String url = util.getChuyenNguUrl("DonvidoluongSvl", "",session);
%>
<% Utility Util = new Utility(); %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"> <% Utility Utilback = new Utility(); %>
<HTML>
<HEAD>
<TITLE><%= getServletContext().getInitParameter("TITLENAME") %></TITLE>
<META http-equiv="Content-Type" content="text/html; charset=utf-8">

<META http-equiv="Content-Style-Type" content="text/css">
<LINK rel="stylesheet" type="text/css" media="print"
	href="../css/impression.css">
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

<link type="text/css" rel="stylesheet" href="../css/mybutton.css">
<SCRIPT language="JavaScript" type="text/javascript">
function clearform()
{
	document.dvdlForm.dvdl.value = "";
    document.dvdlForm.diengiai.value = "";
    document.dvdlForm.tungay.value = "";
	document.dvdlForm.denngay.value = "";       
    document.dvdlForm.trangthai.selectedIndex = 0;
    submitform();
}

function submitform()
{
	document.forms['dvdlForm'].action.value= 'search';
	document.forms['dvdlForm'].submit();
}

function newform()
{
	document.forms['dvdlForm'].action.value= 'new';
	document.forms['dvdlForm'].submit();
}
function thongbao()
{var tt = document.forms['dvdlForm'].msg.value;
	if(tt.length>0)
    alert(document.forms['dvdlForm'].msg.value);
	}


function xuatExcel()
{
 	document.forms['dvdlForm'].action.value= 'excel';
 	document.forms['dvdlForm'].submit();
 	document.forms['dvdlForm'].action.value= '';
}
</SCRIPT>
</HEAD>
<BODY leftmargin="0" bottommargin="0" topmargin="0" rightmargin="0">
<form name="dvdlForm" method="post" action="../../DonvidoluongSvl">
<% redis.clients.jedis.Jedis jedis =  geso.dms.center.util.Utility.getJedis(); %><% geso.dms.center.util.Csrf csdr=new geso.dms.center.util.Csrf(request,response,true,false,true);%>
 <input type="hidden" name="<%=csdr.get_tokenName() %>" value='<%=csdr.get_tokenValue() %>'>


<input type="hidden" name="userId" value='<%=userId%>'>
<input type="hidden" name="action" value="1">
<input type="hidden" name="msg" value='<%=Utility.GLanguage(obj.getMsg(),session,jedis) %>'>

<script language="javascript" type="text/javascript">
    thongbao();
</script> 
<TABLE width="100%" border="0" cellspacing="0" cellpadding="0"
	height="100%">
	<TR>
		<TD colspan="4" align='left' valign='top' bgcolor="#ffffff">
			<TABLE width="100%" border="0" cellpadding="0" cellspacing="1">
				<TR>
					<TD align="left" >
					   <table width="100%" border="0" cellpadding="0" cellspacing="0">
						  <tr height="22">
							   <TD align="left" colspan="2" class="tbnavigation">
							   		&nbsp;<%=url%> </TD>
							   <TD colspan="2" align="right" class="tbnavigation"><%=Utility.GLanguage("Chào mừng bạn",session,jedis) %>  <%=userTen %>&nbsp;  </TD> 
						    </tr>
						</table>
					</TD>
				</TR>
			</TABLE>
			<TABLE border="0" width="100%" cellpadding="0"  cellspacing="1">
							<TR>
								<TD width="100%" align="left"><FIELDSET>
									<LEGEND class="legendtitle"><%=Utility.GLanguage("Tiêu chí tìm kiếm",session,jedis) %>  </LEGEND>

									<TABLE class="tblight" width="100%" cellpadding="6" cellspacing = "0">
										<TR>
											<TD class="plainlabel" ><%=Utility.GLanguage("Đơn vị đo lường",session,jedis) %></TD>
										  	<TD class="plainlabel" ><INPUT name="dvdl" type="text" size="40" value="<%= obj.getDvdl() %>" onChange="submitform();"></TD>
											<TD class="plainlabel" ><%=Utility.GLanguage("Từ ngày",session,jedis) %> </TD>
											<TD class="plainlabel">
												<TABLE cellpadding="0" cellspacing="0">
													<TR><TD>
														<input class="days" type="text" name="tungay" value='<%=obj.getTungay() %>'  size="20" onchange="submitform();">
													</TD>
													
													</TR>
												</TABLE>																					
		  									</TD>
										</TR>

										<TR>
											<TD class="plainlabel" ><%=Utility.GLanguage("Diễn giải",session,jedis) %></TD>
										  	<TD class="plainlabel" ><INPUT name="diengiai" type="text" size="40" value="<%= obj.getDiengiai()%>" onChange="submitform();"></TD>
											<TD class="plainlabel" ><%=Utility.GLanguage("Đến ngày",session,jedis) %> </TD>
										  	<TD class="plainlabel" >
										  		<TABLE cellpadding="0" cellspacing="0">
										  			<TR>
										  				<TD>
										 					<input class="days" type="text" name="denngay" value='<%=obj.getDenngay() %>' size="20" onchange="submitform();">
										  				</TD>
										

											  		</TR>
											 	</TABLE>
											</TD>	 
										</TR>
										
										<TR>
											<TD class="plainlabel" ><%=Utility.GLanguage("Trạng thái",session,jedis) %> </TD>
										    <TD class="plainlabel" >
											    <select name="trangthai" onChange="submitform();">
													<% if (obj.getTrangthai().equals("1")){%>
													  <option value="2" selected> </option>
													  <option value="1" selected><%=Utility.GLanguage("Hoạt động",session,jedis) %></option>
													  <option value="0"><%=Utility.GLanguage("Ngưng hoạt động",session,jedis) %></option>
													<%}else if(obj.getTrangthai().equals("0")) {%>
													  <option value="2" > </option>
													  <option value="0" selected><%=Utility.GLanguage("Ngưng hoạt động",session,jedis) %></option>
													  <option value="1" ><%=Utility.GLanguage("Hoạt động",session,jedis) %></option>
													<%}else{%>											
													  <option value="2" selected> </option>
													  <option value="1" ><%=Utility.GLanguage("Hoạt động",session,jedis) %></option>
													  <option value="0" ><%=Utility.GLanguage("Ngưng hoạt động",session,jedis) %></option>
													<%}%>
											    </select>
									       </TD>
									      	<TD class="plainlabel" colspan="2">
										    	<a class="button2" href="javascript:clearform()"> <img style="top: -4px;" src="../images/button.png" alt=""><%=Utility.GLanguage("Nhập lại",session,jedis) %></a>&nbsp;&nbsp;&nbsp;&nbsp;
										    	<a class="button2" href="javascript:xuatExcel()"> <img style="top: -4px;" src="../images/button.png" alt=""><%=Utility.GLanguage("Xuất Excel",session,jedis) %> </a>&nbsp;&nbsp;&nbsp;&nbsp;
											</TD> 
										</TR>
										
										
									</TABLE>
									</FIELDSET>
								</TD>	
							</TR>
				</TABLE>
				<TABLE width = 100% cellpadding="0" cellspacing="1">
			    <TR>
					<TD align="left" >
						<FIELDSET>
							<LEGEND class="legendtitle">&nbsp;<%=Utility.GLanguage("Đơn vị đo lường sản phẩm",session,jedis) %> &nbsp;&nbsp;
							<%if(quyen[Utility.THEM]!=0) {%>
							<a name="new" class="button3" href="javascript:newform()">
    						<img style="top: -4px;" src="../images/New.png" alt=""><%=Utility.GLanguage("Tạo mới",session,jedis) %> </a>  
							<%} %>
							
						</LEGEND>
				
							<TABLE class="" width="100%" border="0" cellpadding="0" cellspacing="0">
								<TR>
									<TD width="98%">
										<TABLE width="100%" border="0" cellspacing="1" cellpadding="4">
											<TR class="tbheader">
												<TH width="4%"><%=Utility.GLanguage("STT",session,jedis) %></TH>
											    <TH width="14%"><%=Utility.GLanguage("Đơn vị đo lường",session,jedis) %></TH>
											    <TH width="10%"><%=Utility.GLanguage("Diễn giải",session,jedis) %></TH>
											    <TH width="16%"><%=Utility.GLanguage("Tình trạng",session,jedis) %> </TH>
												<TH width="9%"><%=Utility.GLanguage("Ngày tạo",session,jedis) %> </TH>
												<TH width="16%"><%=Utility.GLanguage("Người tạo",session,jedis) %> </TH>
												<TH width="9%"><%=Utility.GLanguage("Ngày sửa",session,jedis) %> </TH>
												<TH width="16%"><%=Utility.GLanguage("Người sửa",session,jedis) %> </TH>
												<TH width="10%"><%=Utility.GLanguage("Tác vụ",session,jedis) %></TH>
											</TR>
										<% 
											int m = 0;
											String lightrow = "tblightrow";
											String darkrow = "tbdarkrow";
											while (donvilist.next()){
												if (m % 2 != 0) {%>						
													<TR class= <%=lightrow%> >
												<%} else {%>
													<TR class= <%= darkrow%> >
												<%}%>
												<TD align="center"><%=m+1%></TD>
												<TD><%= donvilist.getString("donvi") %></TD>
												<TD align="center"><%= donvilist.getString("diengiai") %></TD>
												<%if (donvilist.getString("trangthai").equals("1")){ %>
													<TD align="center"><%=Utility.GLanguage("Hoạt động",session,jedis) %></TD>
												<%}else{ %>
													<TD align="center"><%=Utility.GLanguage("Ngưng hoạt động",session,jedis) %></TD>
												<%} %>
												<TD align="center"><%= donvilist.getString("ngaytao") %></TD>
												<TD align="center"><%= donvilist.getString("nguoitao") %></TD>
												<TD align="center"><%= donvilist.getString("ngaysua") %></TD>
												<TD align="center"><%= donvilist.getString("nguoisua") %></TD>
												<TD align="center">
												<TABLE border = 0 cellpadding="0" cellspacing="0">
													<TR><TD>
													<%if(quyen[Utility.SUA]!=0) {%>
														<A href = "../../RouterSvl?task=<%= Util.dongMa(getServletContext().getInitParameter("RedirectNoScript") +"DonvidoluongUpdateSvl?userId="+userId+"&update="+donvilist.getString("pk_seq")+"")%>"><img src="../images/Edit20.png" alt="Cap nhat" title="<%=Utility.GLanguage("Cập nhật",session,jedis) %>" width="20" height="20" longdesc="Cap nhat" border = 0></A>
													<%} %>
													</TD>
													<TD>
													<%if(quyen[Utility.XEM]!=0) {%>
														<A href = "../../RouterSvl?task=<%= Util.dongMa(getServletContext().getInitParameter("RedirectNoScript") +"DonvidoluongUpdateSvl?userId="+userId+"&display="+donvilist.getString("pk_seq")+"")%>"><img src="../images/Display20.png" alt="Cap nhat" title="<%=Utility.GLanguage("Hiển thị",session,jedis) %>" width="20" height="20" longdesc="hien thi" border = 0></A>
													<%} %>
													</TD>
													<TD>
													<%if(quyen[Utility.XOA]!=0) {%>
														<A href = "../../RouterSvl?task=<%= Util.dongMa(getServletContext().getInitParameter("RedirectNoScript") +"DonvidoluongSvl?userId="+userId+"&delete="+donvilist.getString("pk_seq")+"")%>"><img src="../images/Delete20.png" alt="Xoa" title="<%=Utility.GLanguage("Xóa",session,jedis) %>" width="20" height="20" longdesc="Xoa" border=0 onclick="if(!confirm('Bạn có muốn xóa đơn vị đo lường này ?')) return false;"></A>
													<%} %>
													</TD>
													</TR>												
												</TABLE>												
												</TD>
											</TR>
										<%m++; }%>
									<TR>
											<TD height="26" colspan="11" align="center" class="tbfooter">&nbsp;	</TD>
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
	
</TABLE>
<%geso.dms.center.util.Utility.JedisClose(jedis); %>
</form>
</BODY>
</HTML>
<%	if(donvilist != null) donvilist.close();
	obj.DBClose();

	}%>