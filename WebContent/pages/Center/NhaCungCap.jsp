<%@page import="geso.dms.center.db.sql.dbutils"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
    
<%@ page  import = "geso.dms.center.beans.nhacungcap.INhacungcap" %>
<%@ page  import = "geso.dms.center.beans.nhacungcap.INhacungcapList" %>
<%@ page  import = "java.util.List" %>
<%@ page  import = "java.sql.ResultSet" %>
<%@ page  import = "geso.dms.center.util.*" %>

<%	String userId = (String) session.getAttribute("userId");  
	String userTen = (String) session.getAttribute("userTen");  	
	String sum = (String) session.getAttribute("sum");
	Utility util = (Utility) session.getAttribute("util");
	if(!util.check(userId, userTen, sum)){
		response.sendRedirect(request.getContextPath() + "/redirect.jsp");
	}else{ %>

<% 
	INhacungcapList obj = (INhacungcapList)session.getAttribute("obj"); 
	int[] quyen = new  int[6];
	quyen = util.Getquyen("NhacungcapSvl","",userId);
	
	System.out.println(quyen[0]);
	System.out.println(quyen[1]);
	System.out.println(quyen[2]);
	System.out.println(quyen[3]);
%>
<% ResultSet ncclist = (ResultSet)obj.getNccList(); %>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"> <% Utility Utilback = new Utility(); %>
<HTML>
<HEAD>
<TITLE><%= getServletContext().getInitParameter("TITLENAME") %></TITLE>
<META http-equiv="Content-Type" content="text/html; charset=utf-8">

<META http-equiv="Content-Style-Type" content="text/css">
<LINK rel="stylesheet" type="text/css" media="print" href="../css/impression.css">
<LINK rel="stylesheet" href="../css/main.css" type="text/css">
<LINK rel="stylesheet" href="../css/calendar.css" type="text/css">
<script type="text/javascript" language="JavaScript" src="../scripts/simplecalendar.js"></script>
	<link type="text/css" rel="stylesheet" href="../css/mybutton.css">
	<style type="text/css">
	
	</style>
	
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
<SCRIPT language="javascript" type="text/javascript">

function clearform()
{
	document.nccForm.tenviettat.value = "";
    document.nccForm.ncc.value = "";
    document.nccForm.tungay.value = "";
    document.nccForm.denngay.value = "";
    document.nccForm.trangthai.selectedIndex = 2;
    submitform();
}

function submitform()
{
	document.forms['nccForm'].action.value= 'search';
	document.forms['nccForm'].submit();
}

function newform()
{
	document.forms['nccForm'].action.value= 'new';
	document.forms['nccForm'].submit();
}
function thongbao()
{var tt = document.forms['nccForm'].msg.value;
	if(tt.length>0)
    alert(document.forms['nccForm'].msg.value);
	}

</SCRIPT>

</HEAD>
<BODY leftmargin="0" bottommargin="0" topmargin="0" rightmargin="0">
<form name="nccForm" method="post" action="../../NhacungcapSvl">
<input type="hidden" name="userId" value='<%=userId%>'>
<input type="hidden" name="action" value='1'>
<input type="hidden" name="msg" value='<%=obj.getMsg() %>'>

<script language="javascript" type="text/javascript">
    thongbao();
</script> 
<TABLE width="100%" border="0" cellspacing="0" cellpadding="3"
	height="100%">
	<TR>
		<TD colspan="4" align='left' valign='top' bgcolor="#ffffff">
			<TABLE width="100%" border="0" cellpadding="0" cellspacing="3">
				<TR>
					<TD align="left" >
					   <table width="100%" border="0" cellpadding="0" cellspacing="0">
						  <tr height="22">
							   <TD align="left" colspan="2" class="tbnavigation">
							   		 &nbsp; Dữ liệu nền > Cơ bản > Nhà cung cấp
							   </TD>
							   <TD colspan="2" align="right" class="tbnavigation"><%=Utility.GLanguage("Chào mừng bạn",session,jedis) %>  <%=userTen %> &nbsp;</td> 
						    </tr>
						</table>
					</TD>
				</TR>
				<TR>
					<TD>
					<TABLE border="0" width="100%" >
							<TR >
								<TD width="100%" align="left"><FIELDSET>
									<LEGEND class="legendtitle"><%=Utility.GLanguage("Tiêu chí tìm kiếm",session,jedis) %></LEGEND>

									<TABLE class="tblight" width="100%" cellpadding="3" cellspacing = "0">
										<TR style="">
											<TD class="plainlabel" >Tên nhà cung cấp</TD>
										  	<TD class="plainlabel" style="width: 200px;"><INPUT name="ncc" type="text" size="40" value="<%= obj.getNhacungcap()%>" onChange="submitform();" style="width: 200px;" ></TD>
										  	<TD class="plainlabel" ><%=Utility.GLanguage("Từ ngày",session,jedis) %> </TD>
											<TD class="plainlabel" colspan="3">
												<TABLE cellpadding="0" cellspacing="0">
													<TR>
														<TD class="plainlabel">
															<input type="text" class="days" name="tungay" value='<%=obj.getTungay() %>'  size="20" onchange=submitform(); >
														</TD>
		   											</TR>
												</TABLE>											
		  									</TD>
										</TR>
										<TR>
											<TD width="120px" class="plainlabel" >Tên viết tắt</TD>
										  	<TD width="250px" class="plainlabel" ><INPUT name="tenviettat" type="text" size="40" value="<%= obj.getTenviettat() %>" onChange="submitform();" ></TD>
											<TD width="120px" class="plainlabel" ><%=Utility.GLanguage("Đến ngày",session,jedis) %> </TD>
										  	<TD width="250px" class="plainlabel" colspan="3">
										  		<TABLE cellpadding="0" cellspacing="0">
										  			<TR>
										  				<TD>
										  					<input class="days" type="text" name="denngay" value='<%=obj.getDenngay() %>' size="20" onchange=submitform(); >
										  				</TD>
											 		 </TR>
												 </TABLE>
											</TD>	
										</TR>
										
										
										<TR>
											<TD width="25%" class="plainlabel"><%=Utility.GLanguage("Trạng thái",session,jedis) %></TD>
											
											<TD width="25%" class="plainlabel">
												<select name="trangthai"  onChange="submitform();">
											
														<% if (obj.getTrangthai().equals("1")){%>											  
														  <option value="0"><%=Utility.GLanguage("Ngưng hoạt động",session,jedis) %></option>
														  <option value="1" selected><%=Utility.GLanguage("Hoạt động",session,jedis) %></option>
														  <option value="2" > </option>
														  
														<%}else if(obj.getTrangthai().equals("0")) {%>
														  <option value="0" selected><%=Utility.GLanguage("Ngưng hoạt động",session,jedis) %></option>
														  <option value="1" ><%=Utility.GLanguage("Hoạt động",session,jedis) %></option>
														  <option value="2" > </option>
														  
														<%}else{%>											
														  <option value="0" ><%=Utility.GLanguage("Ngưng hoạt động",session,jedis) %></option>											  
														  <option value="1" ><%=Utility.GLanguage("Hoạt động",session,jedis) %></option>
														  <option value="2" selected> </option>
														<%}%>		
										    	</select>
										    </TD>
										    <TD width="25%" class="plainlabel" >
										    	<a class="button2" href="javascript:clearform()">
												<img style="top: -4px;" src="../images/button.png" alt=""><%=Utility.GLanguage("Nhập lại",session,jedis) %></a>&nbsp;&nbsp;&nbsp;&nbsp;	
										   </TD>
											<TD width="25%"  class="plainlabel" ></TD>
										</TR>
										
									</TABLE>
									</FIELDSET>
								</TD>	
							</TR>
						</TABLE>
					</TD>
				</TR>	
				
			    <TR>
					<TD align="left" >
						<FIELDSET>
							<LEGEND class="legendtitle">&nbsp;Nhà cung cấp &nbsp;&nbsp;&nbsp;
							<%if(quyen[Utility.THEM]!=0) {%>
							<a class="button3" href="javascript:newform()">
    								<img style="top: -4px;" src="../images/New.png" alt=""><%=Utility.GLanguage("Tạo mới",session,jedis) %> </a> 
    								<%} %>                           
						</LEGEND>
				
							<TABLE class="" width="100%" border="0" cellpadding="0" cellspacing="0">
								<TR>
									<TD width="98%">
										<TABLE width="100%" border="0" cellspacing="1" cellpadding="4">
											<TR class="tbheader">
											<TH width="4%">STT</TH>
											<TH width="20%">Tên nhà cung cấp</TH>
											<TH width="10%">Tên viết tắt </TH>
											<TH width="12%"><%=Utility.GLanguage("Trạng thái",session,jedis) %> </TH>
											<TH width="9%"><%=Utility.GLanguage("Ngày tạo",session,jedis) %></TH>
											<TH width="13%"><%=Utility.GLanguage("Người tạo",session,jedis) %></TH>
											<TH width="9%"><%=Utility.GLanguage("Ngày sửa",session,jedis) %> </TH>
											<TH width="13%"><%=Utility.GLanguage("Người sửa",session,jedis) %></TH>
											<TH width="13%"><%=Utility.GLanguage("Tác vụ",session,jedis) %></TH>
										</TR>
								<% 
									int m = 0;
									String lightrow = "tblightrow";
									String darkrow = "tbdarkrow";
								if(ncclist != null){
									while (ncclist.next()){
										if (m % 2 != 0) {%>						
											<TR class= <%=lightrow%> >
										<%} else {%>
											<TR class= <%= darkrow%> >
										<%}%>
												<TD align="center"><%=m+1%></TD>
												<TD align="left"><div align="left"><%=ncclist.getString("ten")%></div></TD>                                   
												<TD><div align="center"><%=ncclist.getString("tenviettat")%></div></TD>
												<% if (ncclist.getString("trangthai").equals("1")){ %>
													<TD align="left"><%=Utility.GLanguage("Hoạt động",session,jedis) %></TD>
												<%}else{%>
													<TD align="left"><%=Utility.GLanguage("Ngưng hoạt động",session,jedis) %></TD>
												<%}%>
												<TD align="center"><%=ncclist.getString("ngaytao")%></TD>
												<TD align="center"><%=ncclist.getString("nguoitao")%></TD>
												<TD align="center"><%=ncclist.getString("ngaysua")%></TD>
												<TD align="center"><%=ncclist.getString("nguoisua")%></TD>
												<TD align="center">
												<TABLE border = 0 cellpadding="0" cellspacing="0">
													<TR><TD>
													<%if(quyen[Utility.SUA]!=0) {%>
															<A href = "../../NhacungcapSvl?userId=<%=userId%>&update=<%=ncclist.getString("pk_seq")%>"><img src="../images/Edit20.png" alt="Cap nhat" title="Cập nhật" width="20" height="20" longdesc="Cap nhat" border = 0></A>
															<%} %>
													</TD>
													<TD>&nbsp;</TD>
													<TD>
														<%if(quyen[Utility.XOA]!=0) {%>
														<A href = "../../NhacungcapSvl?userId=<%=userId%>&delete=<%=ncclist.getString("pk_seq")%>"><img src="../images/Delete20.png" alt="Xoa" title="Xóa" width="20" height="20" longdesc="Xoa" border=0 onclick="if(!confirm('Bạn có muốn xóa nhà cung cấp này ?')) return false; "></A></TD>
														<%} %>
														</TR></TABLE>
												</TD>
											</TR>
								<%m++; }}%>
							
								<TR>
									<TD align="center" colspan="11" class="tbfooter">&nbsp;</TD>
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
</form>
</BODY>
</HTML>
<%
if (ncclist != null) ncclist.close();
obj.DBClose();
obj=null;
session.setAttribute("obj", null);
}
%>