<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page  import = "geso.dms.center.beans.nhomctkhuyenmai.INhomctkhuyenmai" %>
<%@ page  import = "geso.dms.center.beans.nhomctkhuyenmai.imp.Nhomctkhuyenmai" %>
<%@ page  import = "java.util.Iterator" %>
<%@ page  import = "java.util.List" %>
<%@ page  import = "java.util.ArrayList" %>
<%@ page  import = "java.sql.ResultSet" %>
<%@page import="java.sql.SQLException"%>
<%@ page  import = "geso.dms.center.util.*" %>
<%	String userId = (String) session.getAttribute("userId");  
	String userTen = (String) session.getAttribute("userTen");  	
	String sum = (String) session.getAttribute("sum");
	Utility util = (Utility) session.getAttribute("util");
	if(!util.check(userId, userTen, sum)){
		response.sendRedirect(request.getContextPath() + "/redirect.jsp");
	}else{ %>

<% 
	INhomctkhuyenmai nctkmBean = (INhomctkhuyenmai)session.getAttribute("nctkmBean");
	ResultSet ctkmList = (ResultSet)nctkmBean.getCtkmList(); 
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"> <% Utility Utilback = new Utility(); %>

<HTML>
<HEAD>
<TITLE><%= getServletContext().getInitParameter("TITLENAME") %></TITLE>

<META http-equiv="Content-Style-Type" content="text/css">
<LINK rel="stylesheet" href="../css/main.css" type="text/css">

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

<SCRIPT language="JavaScript" type="text/javascript">

function submitform()
{
	document.forms["nctkmForm"].action.value = "submit";
    document.forms["nctkmForm"].submit();
}

function saveform()
{
	if(document.getElementById("tennhom").value == "")
	{
		alert('Ban phai nhap ten nhom...');
		return;
	}
	if(document.getElementById("diengiai").value == "")
	{
		alert('Ban phai nhap dien giai nhom...');
		return;
	}
	
	document.forms["nctkmForm"].action.value = "save";
    document.forms["nctkmForm"].submit();
}

function checkAll()
{
	var chk = document.getElementsByName("ctkmIds");
	var check = document.getElementById("chonhet");
	for(i = 0; i < chk.length; i++)
	{
	 	if(check.checked == true)
	 	{
			chk[i].checked = true;
		}
	 	else
	 	{
			chk[i].checked = false;
		}
	 }
}

</SCRIPT>
</HEAD>
<BODY leftmargin="0" bottommargin="0" topmargin="0" rightmargin="0">
<form name ="nctkmForm" method="post" action="../../NhomctkhuyenmaiUpdateSvl" >
<input type="hidden" name="userId" value='<%=userId%>'>
<input type="hidden" name="action" value="0">

<TABLE width="100%" border="0" cellspacing="0" cellpadding="0" height="100%">
	<TR>
		<TD colspan="4" align='left' valign='top' bgcolor="#FFFFFF">
			<TABLE width="100%" border="0" cellpadding="0" cellspacing="1">
				<TR>
					<TD align="left" class="tbnavigation">
						<table width="100%" border="0" cellpadding="0" cellspacing="0">
						  <tr height="22">
							 <TD align="left" colspan="2" class="tbnavigation">&nbsp;Quản lý khuyến mại > Nhóm chương trình khuyến mại > Tạo mới</TD> 
							 <TD colspan="2" align="right" class="tbnavigation"><%=Utility.GLanguage("Chào mừng bạn",session,jedis) %>  <%=userTen %>&nbsp;  </TD></tr>
						</table>
			 		</TD>
				</TR>
			</TABLE>
		<TABLE width="100%" border="0" cellpadding="0" cellspacing="1">
			<TR ><TD >
					<TABLE width="100%" border="0" cellpadding="0" cellspacing="0">
						<TR class = "tbdarkrow">
							<TD width="30" align="left"><A href="../../NhomctkhuyenmaiSvl?userId=<%=userId %>" >
								<img src="../images/Back30.png" alt="Quay ve"  title="Quay ve" border="1" longdesc="Quay ve" style="border-style:outset"></A></TD>
						    <TD width="2" align="left" ></TD>
						    <TD width="30" align="left" ><A href="javascript: saveform()" ><IMG src="../images/Save30.png" title="Luu lai" alt="Luu lai" border = "1"  style="border-style:outset"></A></TD>
							<TD>&nbsp;</TD>						
						</TR>
					</TABLE>
			</TD></TR>
		</TABLE>
			<TABLE width="100%" border="0" cellpadding="0"  cellspacing="1" >
			  	<tr>
					<TD align="left" colspan="4" class="legendtitle">
						<FIELDSET>
						<LEGEND class="legendtitle">Thông báo</LEGEND>
				
	    				<textarea name="dataerror"  style="width: 100% ; color:#F00 ; font-weight:bold"  style="width: 100% ; color:#F00 ; font-weight:bold" readonly="readonly" rows="1"><%= nctkmBean.getMessage() %></textarea>
							<% nctkmBean.setMessage(""); %>
						</FIELDSET>
				   </TD>
				</tr>			

				<TR>
				  <TD height="100%" width="100%">
						<FIELDSET >
						<LEGEND class="legendtitle" style="color:black">Thông tin nhóm chương trình khuyến mại</LEGEND>
						<TABLE border="0" width="100%" cellpadding="6" cellspacing="0">
							<TR>
								<TD width="29%" class="plainlabel" >Tên nhóm chương trình <FONT class="erroralert"> *</FONT></TD>
								<TD class="plainlabel"><INPUT type="text" name="tennhom" id="tennhom" style="width:300px" value='<%= nctkmBean.getTen()%>'></TD>
							</TR>
							<TR>
							  	<TD class="plainlabel"><%=Utility.GLanguage("Diễn giải",session,jedis) %> <FONT class="erroralert"> *</FONT></TD>
						  	  	<TD class="plainlabel"><INPUT type="text" name="diengiai" id="diengiai" style="width:300px" value='<%= nctkmBean.getDiengiai() %>'></TD>
						  	</TR>
						  	<TR>
							  	<TD class="plainlabel"><%=Utility.GLanguage("Từ ngày",session,jedis) %></TD>
						  	  	<TD class="plainlabel">
						  	  		 <input  class="days" type="text" name="tungay" value='<%= nctkmBean.getTungay() %>' size="20" onchange = "submitform();">
						  	  	</TD>
						  	</TR>
						  	<TR>
							  	<TD class="plainlabel"><%=Utility.GLanguage("Đến ngày",session,jedis) %></TD>
						  	  	<TD class="plainlabel">
						  	  		 <input  class="days" type="text" name="denngay" value='<%= nctkmBean.getDenngay() %>' size="20" onchange = "submitform();">
						  	  	</TD>
						  	</TR>
						</TABLE>
						<hr>
						<TABLE width="100%" border="0" cellspacing="1" cellpadding="4">							
							<tr class="tbheader">
								<th width="15%">Mã chương trình</th>
								<th width="15%">Scheme</th>
								<th width="60%" align="left"><%=Utility.GLanguage("Diễn giải",session,jedis) %></th>
								<th width="10%">Chọn hết <input type="checkbox" id="chonhet" name="chonhet" onchange="checkAll()"></th>
							</tr>
							<% if(ctkmList != null){
								int k = 0;
								while(ctkmList.next())
								{ 
									if (k % 2 != 0) {%>						
										<TR class = "tblightrow" >
									<%  } else {%>
										<TR class = "tbdarkrow" >
									<%  } %>
									<TD align="center"><%= ctkmList.getString("ctkmId") %></TD>
									<TD align="center"><%= ctkmList.getString("scheme") %></TD>
									<TD align="left"><%= ctkmList.getString("diengiai") %></TD>
									<TD align="center">
									<% if (nctkmBean.getCtkmIds().indexOf(ctkmList.getString("ctkmId")) >= 0 ){%>
										<input type="checkbox" name="ctkmIds" value='<%= ctkmList.getString("ctkmId") %>' checked>
									<%}else{ %>
										<input type="checkbox" name="ctkmIds" value='<%= ctkmList.getString("ctkmId") %>'>
									<%} %>
									</TD>
							<%} k++; } %>
							<tr class="tbheader"><td colspan="4">&nbsp;</td></tr>
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
<%}%>