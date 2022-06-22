<%@page import="geso.dms.center.beans.mucchietkhautt.IMucChietKhauTT"%>
<%@page import="geso.dms.center.beans.gioihancongnott.IGioiHanCongNoTT"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page  import = "java.util.Iterator" %>
<%@ page  import = "java.util.List" %>
<%@ page  import = "java.util.ArrayList" %>
<%@ page  import = "geso.dms.distributor.beans.gioihancongno.*" %>
<%@ page  import = "java.sql.ResultSet" %>
<%@ page  import = "geso.dms.center.util.*" %>
<%	String userId = (String) session.getAttribute("userId");  
	String userTen = (String) session.getAttribute("userTen");  	
	String sum = (String) session.getAttribute("sum");
	String TenNhaPP=(String)session.getAttribute("TenNhaPP");
	if(TenNhaPP == null) TenNhaPP="";

	Utility util = (Utility) session.getAttribute("util");
	if(!util.check(userId, userTen, sum)){
		response.sendRedirect(request.getContextPath() + "/redirect.jsp");
	}else{ %>

<% IMucChietKhauTT obj = (IMucChietKhauTT)session.getAttribute("obj"); %>
<% List<IMucChietKhauTT> ghcnlist = (List<IMucChietKhauTT>)obj.getListMucChietKhau(); %>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"> <% Utility Utilback = new Utility(); %>
<HTML>
<HEAD>
<TITLE><%= getServletContext().getInitParameter("TITLENAME") %></TITLE>
<META http-equiv="Content-Type" content="text/html; charset=utf-8">
<META http-equiv="Content-Style-Type" content="text/css">
<LINK rel="stylesheet" href="../css/main.css" type="text/css">
<LINK rel="stylesheet" type="text/css" href="../css/style.css" />
<link type="text/css" rel="stylesheet" href="../css/mybutton.css">
<SCRIPT language="javascript" type="text/javascript">
function clearform()
{	
    document.ghcnForm.mucchietkhau.value = "0";      

    document.ghcnForm.TenNhaPP.value = "";
    submitform();
}

function submitform()
{
	document.forms['ghcnForm'].action.value= 'search';
	document.forms['ghcnForm'].submit();
}

function newform()
{
	document.forms['ghcnForm'].action.value= 'new';
	document.forms['ghcnForm'].submit();
}

</SCRIPT>
<script type="text/javascript" src="../scripts/jquery-1.4.2.min.js"></script>
<script type="text/javascript" src="../scripts/jquery.autocomplete.js"></script>

</HEAD>
<BODY leftmargin="0" bottommargin="0" topmargin="0" rightmargin="0">
<form name="ghcnForm" method="post" action="../../MucChietKhauTTSvl">
<input type="hidden" name="userId" value="<%= userId %>" >
<input type="hidden" name="action" value="1" >
<script language="javascript" type="text/javascript">
    thongbao();
</script> 

<TABLE width="100%" border="0" cellspacing="0" cellpadding="0" height="100%">
	<TR>
		<TD colspan="4" align='left' valign='top' bgcolor="#FFFFFF">
		<TABLE width="100%" border="0" cellpadding="0" cellspacing="2">
			<TR>
				<TD align="left" class="tbnavigation">
					<table width="100%" border="0" cellpadding="0" cellspacing="0">
						<tr height="22">
   							<TD align="left" colspan="2" class="tbnavigation">&nbsp;Thiết lập dữ liệu nền > Dữ liệu Kinh doanh > Mức chiết khấu</TD>
   							<TD colspan="2" align="right" class="tbnavigation"><%=Utility.GLanguage("Chào mừng bạn",session,jedis) %>  <%= userTen%> &nbsp;</TD>
						</tr>
					</table>
				</TD>
		  	</TR>
		</TABLE>
		<TABLE width="100%" border="0" cellpadding="0" cellspacing="2">
			<TR>
				<TD >
					<FIELDSET><LEGEND class="legendtitle">&nbsp;<%=Utility.GLanguage("Tiêu chí tìm kiếm",session,jedis) %> &nbsp;</LEGEND>
					<TABLE  width="100%" cellspacing="0" cellpadding="3">
						<TR>
							<TD width="19%" class="plainlabel">Mức chiết khấu </TD>
							<TD width="81%" valign="middle" class="plainlabel"><INPUT name="mucchietkhau" size="10"
										type="text" value="<%= obj.getChietKhau()+"" %>" onChange="submitform();" >&nbsp;&nbsp;</TD>
						</TR>
						
						<TR>
							<TD class="plainlabel"><%=Utility.GLanguage("Nhà phân phối",session,jedis) %> </TD>
							<TD class="plainlabel" valign="middle">
								<TABLE cellpadding="0" cellspacing="0">
									<TR><TD>
										<input name="TenNhaPP" id="TenNhaPP" type="text" size="35" value="<%=TenNhaPP %>" onChange="submitform();">
									</TD>
									<TD>&nbsp;</TD>
									</TR>
								</TABLE>
							</TD>
						</TR>
						<TR>
						    <TD class="plainlabel" colspan="2">			
						    	<a class="button2" href="javascript:clearform()">
	<img style="top: -4px;" src="../images/button.png" alt=""><%=Utility.GLanguage("Nhập lại",session,jedis) %> </a>&nbsp;&nbsp;&nbsp;&nbsp;	
						    				 
                            </TD>
						</TR>
					</TABLE>

				  </FIELDSET>
				</TD>	
			</TR>
		</TABLE>
		<TABLE width="100%" border="0" cellpadding="0" cellspacing="0">
			<TR>
					<TD width="100%">
					<FIELDSET>
					<LEGEND class="legendtitle">Mức tín dụng&nbsp;&nbsp;&nbsp;
						<a class="button3" href="javascript:newform()">
    	<img style="top: -4px;" src="../images/New.png" alt=""><%=Utility.GLanguage("Tạo mới",session,jedis) %> </a>                            
						
					</LEGEND>
					<TABLE class="" width="100%" cellpadding="0" cellspacing="0">
						<TR>
							<TD>
								<TABLE width="100%" border="0" cellspacing="1" cellpadding="2">
									<TR class="tbheader">
											<TH width="18%">Diễn giả mức chiết khấu</TH>
											<TH width="9%">Mức chiết khấu</TH>
											<TH width="9%"><%=Utility.GLanguage("Ngày tạo",session,jedis) %></TH>
											<TH width="17%"><%=Utility.GLanguage("Người tạo",session,jedis) %></TH>
											<TH width="10%"><%=Utility.GLanguage("Ngày sửa",session,jedis) %></TH>
											<TH width="16%"><%=Utility.GLanguage("Người sửa",session,jedis) %></TH>
											<TH width="12%"><%=Utility.GLanguage("Tác vụ",session,jedis) %></TH>
									</TR>
									<% 
									IMucChietKhauTT ghcn = null;
									int size = ghcnlist.size();
									int m = 0;
									while (m < size){
										ghcn = ghcnlist.get(m);
										if (m % 2 != 0) {%>						
											<TR class= "tblightrow">
										<%} else {%>
											<TR class= "tbdarkrow">
										<%}%>
												<TD align="left"> <%= ghcn.getDiengiai() %></TD>                                   
												<TD align="center"><%= ghcn.getChietKhau() %></TD>                             
												<TD align="center"><%= ghcn.getNgaytao()%></TD>
												<TD align="center"><%= ghcn.getNguoitao()%></TD>
												<TD align="center"><%= ghcn.getNgaysua()%></TD>
												<TD align="center"><%= ghcn.getNguoisua()%></TD>
												<TD align="center">
												<TABLE border = 0 cellpadding="0" cellspacing="0">
												<TR>
												<TD>
													<A href = "../../MucChietKhauTTNewSvl?userId=<%=userId%>&update=<%= ghcn.getId()%>"><img src="../images/Edit20.png" alt="Cap nhat" width="20" height="20" longdesc="Cap nhat" border = 0></A>
												</TD>
												<TD>&nbsp;</TD>
												<TD>
													<A href = "../../MucChietKhauTTNewSvl?userId=<%=userId%>&delete=<%= ghcn.getId()%>"><img src="../images/Delete20.png" alt="Xoa" width="20" height="20" longdesc="Xoa" border=0 onclick="if(!confirm('Bạn có chắc chắn muỗn xóa  Mức chiết khấu  này?')) return false;"></A></TD>
												</TR></TABLE>
												</TD>
											</TR>
								<%m++; }%>
							
						</TABLE>
					</TD>
				</TR>
			</TABLE>
			</FIELDSET>
		</TD>
	</TR>
</TABLE>
		<!--end body Dossier--></TD>
</TR>
</TABLE>
</form>
</BODY>
</HTML>
<%
}%>