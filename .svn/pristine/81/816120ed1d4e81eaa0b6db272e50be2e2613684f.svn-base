<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page  import = "geso.dms.center.beans.kho.IKho" %>
<%@ page  import = "geso.dms.center.beans.kho.IKhoList" %>
<%@ page  import = "java.util.Iterator" %>
<%@ page  import = "java.util.List" %>
<%@ page  import = "java.util.ArrayList" %>
<%@ page  import = "geso.dms.center.util.*" %>
<%	String userId = (String) session.getAttribute("userId");  
	String userTen = (String) session.getAttribute("userTen");  	
	String sum = (String) session.getAttribute("sum");
	Utility util = (Utility) session.getAttribute("util");
	if(!util.check(userId, userTen, sum)){
		response.sendRedirect(request.getContextPath() + "/redirect.jsp");
	}else{ %>

<% IKhoList obj = (IKhoList)session.getAttribute("obj"); %>
<% List<IKho> kholist = (List<IKho>)obj.getKhoList(); %>
<%int[] quyen = new  int[4];
quyen = util.Getquyen("KhoSvl", "", userId);

System.out.println(quyen[0]);
System.out.println(quyen[1]);
System.out.println(quyen[2]);
System.out.println(quyen[3]); %>

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
<script type="text/javascript" language="JavaScript" src="../scripts/simplecalendar.js"></script>
	<link type="text/css" rel="stylesheet" href="../css/mybutton.css">
	
	
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
    document.khoForm.ten.value = "";   
	document.khoForm.tungay.value = "";
	document.khoForm.denngay.value = "";       
    document.khoForm.trangthai.selectedIndex = 0;
    submitform();
}

function submitform()
{
	document.forms['khoForm'].action.value= 'search';
	document.forms['khoForm'].submit();
}

function newform()
{
	document.forms['khoForm'].action.value= 'new';
	document.forms['khoForm'].submit();
}
function thongbao()
{var tt = document.forms['khoForm'].msg.value;
	if(tt.length>0)
    alert(document.forms['khoForm'].msg.value);
	}
</SCRIPT>

</HEAD>
<BODY leftmargin="0" bottommargin="0" topmargin="0" rightmargin="0">
<form name="khoForm" method="post" action="../../KhoSvl" charset="UTF-8">
<% redis.clients.jedis.Jedis jedis =  geso.dms.center.util.Utility.getJedis(); %>
<% geso.dms.center.util.Csrf csdr=new geso.dms.center.util.Csrf(request,response,true,false,true);%>
 <input type="hidden" name="<%=csdr.get_tokenName() %>" value='<%=csdr.get_tokenValue() %>'>

<INPUT name="userId" type="hidden" value='<%=userId %>' size="30">
<input type="hidden" name="action" value='1'>
<input type="hidden" name="msg" value='<%=obj.getMsg() %>'>

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
							   		&nbsp;Thiết lập dữ liệu nền > Dữ liệu Kinh doanh > Khai báo kho </TD>
							   <TD colspan="2" align="right" class="tbnavigation"><%=Utility.GLanguage("Chào mừng bạn",session,jedis) %>  <%=userTen %> &nbsp;</td> 
						    </tr>
   
						</table>
					</TD>
				</TR>
			</TABLE>
			<TABLE width="100%" border="0" cellpadding="0" cellspacing="0">
				<TR>
					<TD width="100%" align="left"><FIELDSET>
						<LEGEND class="legendtitle"><%=Utility.GLanguage("Tiêu chí tìm kiếm",session,jedis) %> </LEGEND>

							<TABLE class="tblight" width="100%" cellpadding="6" cellspacing = "0">
								<TR>
									  	<TD class="plainlabel" width="19%">Tên kho</TD>
									  	<TD class="plainlabel" ><INPUT name="ten" size="20" type="text" value='<%= obj.getTen() %>' onChange="submitform();"></TD>
								</TR>
								<TR>
										<TD class="plainlabel" ><%=Utility.GLanguage("Từ ngày",session,jedis) %> </TD>
									 	<TD class="plainlabel" >
											<TABLE cellpadding="0" cellspacing="0">
											<TR><TD>
												<input class="days" type="text" name="tungay" size="20" value='<%= obj.getTungay() %>' onchange="submitform();">
												</TD>
												
                                    		</TR>
											</TABLE>
										</TD>
								</TR>
								<TR>
                                    <TD class="plainlabel" ><%=Utility.GLanguage("Đến ngày",session,jedis) %> </TD>
								  	<TD class="plainlabel" >
							  			<TABLE cellpadding="0" cellspacing="0">
							  				<TR>
							  					<TD>
													<input class="days" type="text" name="denngay" size="20" value='<%= obj.getDenngay() %>' onchange="submitform();">
												</TD>
												
                	                     	</TR>
										</TABLE>
									</TD>

								</TR>
								<TR>
									<TD class="plainlabel"><%=Utility.GLanguage("Trạng thái",session,jedis) %></TD>
											
									<TD class="plainlabel"><select name="trangthai" onChange="submitform();">
											<option value="2" selected> </option>
										<% if (obj.getTrangthai().equals("1")){ %>
										  	<option value="1" selected ><%=Utility.GLanguage("Hoạt động",session,jedis) %></option>
										  	<option value="0"><%=Utility.GLanguage("Ngưng hoạt động",session,jedis) %></option>
										 <%}else{ %>
											<% if (obj.getTrangthai().equals("0")){ %>
										  		<option value="1"  ><%=Utility.GLanguage("Hoạt động",session,jedis) %></option>
										  		<option value="0" selected><%=Utility.GLanguage("Ngưng hoạt động",session,jedis) %></option>
										 	<%}else{ %>
												<option value="1"  ><%=Utility.GLanguage("Hoạt động",session,jedis) %></option>
										  		<option value="0"  ><%=Utility.GLanguage("Ngưng hoạt động",session,jedis) %></option>
										 	<%}}%>										 
										    </select>
									</TD>
								</TR>
								<TR>
									<TD class="plainlabel" colspan="2">
									<a class="button2" href="javascript:clearform()">
	<img style="top: -4px;" src="../images/button.png" alt=""><%=Utility.GLanguage("Nhập lại",session,jedis) %></a>&nbsp;&nbsp;&nbsp;&nbsp;		
                                  
									</TD>
								</TR>
							</TABLE>
							</FIELDSET>
						</TD>	
					</TR>
				</TABLE>
			
			<TABLE width="100%" border="0" cellpadding="0" cellspacing="0">		
			    <TR>
					<TD align="left" >
						<FIELDSET>
							<LEGEND class="legendtitle">&nbsp;Danh sách kho &nbsp;&nbsp;
							<%if(quyen[0]!=0) {%>
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
												<TH width="10%">Tên kho </TH>
												<TH width="20%"><%=Utility.GLanguage("Diễn giải",session,jedis) %> </TH>
												<TH width="12%"><%=Utility.GLanguage("Trạng thái",session,jedis) %> </TH>
												<TH width="9%"><%=Utility.GLanguage("Ngày tạo",session,jedis) %></TH>
												<TH width="13%"><%=Utility.GLanguage("Người tạo",session,jedis) %> </TH>
												<TH width="9%"><%=Utility.GLanguage("Ngày sửa",session,jedis) %></TH>
												<TH width="13%"><%=Utility.GLanguage("Người sửa",session,jedis) %></TH>
												<TH width="11%"><%=Utility.GLanguage("Tác vụ",session,jedis) %></TH>
											</TR>
								<% 
									IKho kho = null;
									int size = kholist.size();
									int m = 0;
									String lightrow = "tblightrow";
									String darkrow = "tbdarkrow";
									while (m < size){
										kho = (IKho) kholist.get(m);
										if (m % 2 != 0) {%>						
											<TR class= <%=lightrow%> >
										<%} else {%>
											<TR class= <%= darkrow%> >
										<%}%>
											<TD align="center"><%=m+1%></TD>
											<TD align="left"><%=kho.getTen() %></TD>                                   
											<TD><%=kho.getDiengiai() %></TD>
											
											<%if (kho.getTrangthai().equals("1")) {%>
												<TD align="center">Hoạt động </TD>
											<%}else{ %>
												<TD align="center">Ngưng hoạt động </TD>
											<%} %>
												<TD align="center"><%=kho.getNgaytao() %></TD>
												<TD align="center"><%=kho.getNguoitao() %></TD>
												<TD align="center"><%=kho.getNgaysua() %></TD>												
												<TD align="center"><%=kho.getNguoisua() %></TD>
												<TD align="center">
													<TABLE border = 0 cellpadding="0" cellspacing="0">
														<TR><TD>
														<%if(quyen[2]!=0) {%>
														<A href = "../../RouterSvl?task=<%= Utilback.dongMa(getServletContext().getInitParameter("RedirectNoScript") +"KhoUpdateSvl?userId="+userId+"&khoId="+kho.getId()+"")%>" ><img src="../images/Edit20.png" alt="Chinh sua" width="20" height="20" longdesc="Chinh sua" border = 0></A>
														<%} %>
														</TD>
														<TD>&nbsp;</TD>
														<TD>
														<%if(quyen[1]!=0) {%>
														<A href = "../../RouterSvl?task=<%= Utilback.dongMa(getServletContext().getInitParameter("RedirectNoScript") +"KhoSvl?userId="+userId+"&delete="+kho.getId()+"")%>"><img src="../images/Delete20.png" alt="Xoa" width="20" height="20" longdesc="Xoa" border=0 onclick="if(!confirm('Bạn muốn xóa kho này?')) return false;">
														</TD>
														<%} %>
														
														</TR>
													</TABLE>
													</TD>
												</TR>
										<% 	m++; }%>
								
										
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
	kholist  =null;
	obj = null;
	session.setAttribute("obj", null);
}
%>