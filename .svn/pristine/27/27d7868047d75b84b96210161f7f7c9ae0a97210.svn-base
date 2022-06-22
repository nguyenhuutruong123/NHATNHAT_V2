<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page  import = "java.util.List" %>
<%@ page  import = "geso.dms.distributor.beans.Phanboquatang.IPhanboquatang" %>
<%@ page  import = "geso.dms.distributor.beans.Phanboquatang.IPhanboquatangList" %>
<%@ page  import = "java.sql.ResultSet" %>
<%@ page  import = "geso.dms.center.util.*" %>
<%	String userId = (String) session.getAttribute("userId");  
	String userTen = (String) session.getAttribute("userTen");  	
	String sum = (String) session.getAttribute("sum");
	Utility util = (Utility) session.getAttribute("util");
	if(!util.check(userId, userTen, sum)){
		response.sendRedirect(request.getContextPath() + "/redirect.jsp");
	}else{ %>

<% IPhanboquatangList obj = (IPhanboquatangList)session.getAttribute("obj"); %>

<% ResultSet rslist = (ResultSet)obj.getRslist();  
	int[] quyen = new  int[6];
	quyen = util.Getquyen("PhanboquatangSvl","",userId);
%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"> <% Utility Utilback = new Utility(); %>
<HTML>
<HEAD>
<TITLE><%= getServletContext().getInitParameter("TITLENAME") %></TITLE>
<META http-equiv="Content-Type" content="text/html; charset=utf-8">
<META http-equiv="Content-Style-Type" content="text/css">
<LINK rel="stylesheet" href="../css/main.css" type="text/css">
<link type="text/css" rel="stylesheet" href="../css/mybutton.css">
<script type="text/javascript"	src="../scripts/jquery.min.1.7.js"></script>
<script type="text/javascript" language="JavaScript" src="../scripts/jquery.tools.min.js"></script>

<link href="../css/jquery-ui.css" rel="stylesheet" type="text/css" />
<script src="../scripts/ui/jquery.ui.core.js" type="text/javascript"></script>
<script src="../scripts/ui/jquery.ui.widget.js" type="text/javascript"></script>
<script src="../scripts/ui/jquery.ui.datepicker.js" type="text/javascript"></script>
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
    document.bgblcForm.bgTen.value = "";
    document.bgblcForm.dvkdId.selectedIndex = 0;
    document.bgblcForm.trangthai.selectedIndex = 0;
    submitform();   
}

function submitform()
{
	document.bgblcForm.action.value= 'search';
	document.forms['bgblcForm'].submit();
}

function newform()
{
	document.forms['bgblcForm'].action.value= 'new';
	document.forms['bgblcForm'].submit();
}
function thongbao()
{
	if(document.getElementById("msg").value != '')
		alert(document.getElementById("msg").value);
}
</SCRIPT>
</HEAD>
<body leftmargin="0" bottommargin="0" topmargin="0" rightmargin="0">
<form name="bgblcForm" method="post" action="../../PhanboquatangSvl">
<input type="hidden" name="userId" value='<%=obj.getUserId() %>'>
<input type="hidden" name="msg" id="msg" value='<%= obj.getMsg() %>'>
<input type="hidden" name="action" value='1'>
<script type="text/javascript">
	thongbao();
</script>
<TABLE width="100%" border="0" cellspacing="0" cellpadding="0"
	height="100%">
	
	<TR>
		<TD colspan="4" align='left' valign='top' bgcolor="#FFFFFF">

		<TABLE width="100%" cellpadding="0" cellspacing="1">
			<TR>
				<TD align="left" class="tbnavigation">
				  <TABLE width="100%" border="0" cellpadding="0" cellspacing="0">
					<TR height="22">
					   <TD align="left" colspan="2" class="tbnavigation">
					   		&nbsp;Dữ liệu nền &gt;Sản phẩm &gt; chương trình phân bổ </TD>
					   <TD colspan="2" align="right" class="tbnavigation"><%=Utility.GLanguage("Chào mừng bạn",session,jedis) %>  <%=userTen %>&nbsp;  </TD>
					</TR>
				  </TABLE>
				</TD>
			</TR>
		</TABLE>
		<TABLE width="100%" cellpadding="0" cellspacing="1">				
				<TR>
					<TD>
					<TABLE border="0" width="100%"  cellpadding="0" cellspacing="0">
						<TR>
							<TD width="100%" align="left"><FIELDSET>
							<LEGEND class="legendtitle"><%=Utility.GLanguage("Tiêu chí tìm kiếm",session,jedis) %> &nbsp;</LEGEND>

							<TABLE  width="100%" cellpadding="6" cellspacing="0">
								<TR>
									<TD class="plainlabel">Tên chương trình phân bổ </TD>
									<TD class="plainlabel"><INPUT name ="ten" type="text" value="<%= obj.getTen() %>" size="40" onChange = "submitform();"/></TD>
									
									<TD class="plainlabel"><%=Utility.GLanguage("Trạng thái",session,jedis) %></TD>
									<TD class="plainlabel"><select name="trangthai" onChange = "submitform();">
								  	<% if (obj.getTrangthai().equals("0")){ %>
								    	<option value="2"> </option>
								    	<option value="1"><%=Utility.GLanguage("Hoạt động",session,jedis) %></option>
								    	<option value="0" selected><%=Utility.GLanguage("Ngưng hoạt động",session,jedis) %></option>
									<%}else{ 							
								  		if (obj.getTrangthai().equals("1")){ %>
								    	<option value="2"> </option>
								    	<option value="1" selected><%=Utility.GLanguage("Hoạt động",session,jedis) %></option>
								    	<option value="0" ><%=Utility.GLanguage("Ngưng hoạt động",session,jedis) %></option>
									<%}else{ %>
								    	<option value="2" selected> </option>
								    	<option value="1" ><%=Utility.GLanguage("Hoạt động",session,jedis) %></option>
								    	<option value="0" ><%=Utility.GLanguage("Ngưng hoạt động",session,jedis) %></option>
									<%}} %>
								    	</select></TD>
								</TR>
								
								<TR>
								
								  <TD class="plainlabel"><%=Utility.GLanguage("Từ ngày",session,jedis) %></TD>
									<TD class="plainlabel"><INPUT name ="tungay" class="days" value="<%= obj.getTungay()%>" size="40" onChange = "submitform();"/></TD>
									<TD class="plainlabel"><%=Utility.GLanguage("Đến ngày",session,jedis) %></TD>
									<TD class="plainlabel"><INPUT name ="denngay" class="days" value="<%= obj.getDenngay() %>" size="40" onChange = "submitform();"/></TD>
												
								</TR>
								
								
							</TABLE>

							</FIELDSET>
							</TD>
						</TR>
					</TABLE>
					</TD>
				</TR>

				<TR>
					<TD width="100%">
					<FIELDSET>
					<LEGEND class="legendtitle">&nbsp; Phân bổ &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
					<%if(quyen[Utility.THEM]!=0) {%>
					<a class="button3" href="javascript:newform()">
    						<img style="top: -4px;" src="../images/New.png" alt="" onClick="newform();"><%=Utility.GLanguage("Tạo mới",session,jedis) %> </a>
					<%} %>
					</LEGEND>
	
					<TABLE class="" width="100%">
						<TR>
							<TD width="98%">
							<TABLE width="100%" border="0" cellspacing="1" cellpadding="4">
								<TR class="tbheader">
									<TH width="4%">STT</TH>
									<TH width="36%">Tên Chương trình </TH>
									<TH  width="10%"><%=Utility.GLanguage("Trạng thái",session,jedis) %> </TH>
									<TH width="15%"><%=Utility.GLanguage("Từ ngày",session,jedis) %></TH>
									<TH width="15%"><%=Utility.GLanguage("Đến ngày",session,jedis) %> </TH>
									<TH width="20%" align="center">&nbsp;Tác vụ</TH>
								</TR>

									<% 
									int m = 0;
									String lightrow = "tblightrow";
									String darkrow = "tbdarkrow";
									while (rslist.next()){
										if (m % 2 != 0) {%>						
											<TR class= <%=lightrow%> >
										<%} else {%>
											<TR class= <%= darkrow%> >
										<%}%>
												<TD align="center"><%=m+1%></TD>
												<TD align="left"><div align="left"><%= rslist.getString("Ma")%></div></TD>                             
												<%if (rslist.getString("trangthai").equals("1")){%>
													<TD align="center"><%=Utility.GLanguage("Hoạt động",session,jedis) %></TD>
												<%}else{ %>
													<TD align="center"><%=Utility.GLanguage("Ngưng hoạt động",session,jedis) %></TD>
												<%} %>
												<TD align="center"><%=rslist.getString("tungay")%></TD>
												<TD align="center"><%=rslist.getString("denngay")%></TD>
												<TD align="center">
												<TABLE border = 0 cellpadding="0" cellspacing="0">
													<TR>
													<TD>
													<%if(rslist.getString("trangthai").equals("0") && quyen[Utility.SUA]!=0){ %>
														<A href = "../../PhanboquatangUpdateSvl?userId=<%=userId%>&update=<%=rslist.getString("pk_seq") %>"><img src="../images/Edit.png" alt="Cap nhat" title="Cập nhật" width="20" height="20" longdesc="Cap nhat" border = 0></A>
													<%} %>
													</TD>
													<TD>
														<%if(rslist.getString("trangthai").equals("0") &&  quyen[Utility.XOA]!=0){ %>
															<A href = "../../PhanboquatangSvl?userId=<%=userId%>&delete=<%=rslist.getString("pk_seq")  %>"><img src="../images/Delete20.png" alt="Xoa" width="20" height="20" longdesc="Xoa" border=0 onclick="if(!confirm('Bạn muốn xóa chương trình phân bổ Nay?')) return false;"></A>
														<%} %>	
													</TD>
													
													<TD>
														<%if(rslist.getString("trangthai").equals("0") &&  quyen[Utility.CHOT]!=0){ %>
															<A href = "../../PhanboquatangSvl?userId=<%=userId%>&chot=<%=rslist.getString("pk_seq")  %>"><img src="../images/Chot.png" alt="Xoa" width="20" height="20" longdesc="Xoa" border=0 onclick="if(!confirm('Bạn muốn chốt chương trình phân bổ Nay?')) return false;"></A>
														<%} %>	
													</TD>
													
													<TD>
													<%if(quyen[Utility.XEM]!=0){ %>
														<A href = "../../PhanboquatangUpdateSvl?userId=<%=userId%>&display=<%= rslist.getString("pk_seq") %>"><img src="../images/Display.png" alt="Cap nhat" title="Cập nhật" width="20" height="20" longdesc="Cap nhat" border = 0></A>
													<%} %>
													</TD>
													
													
													
													<TD>&nbsp;</TD>

													</TR></TABLE>
												</TD>
											</TR>
								<%m++; }%>
								
								<TR>
									<TD align="center" colspan="10" class="tbfooter">&nbsp;		</TD>
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
</body>
</HTML>
<% if (rslist != null)
	rslist.close(); 
	obj.DbClose();
	}
	
%>