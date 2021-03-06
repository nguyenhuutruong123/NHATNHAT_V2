<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page  import = "geso.dms.distributor.beans.gioihancongno.*" %>
<%@ page  import = "java.sql.ResultSet" %>
<%@ page  import = "java.util.Hashtable" %>

<%@ page  import = "geso.dms.center.util.*" %>

<%	String userId = (String) session.getAttribute("userId");  
	String userTen = (String) session.getAttribute("userTen");  	
	String sum = (String) session.getAttribute("sum");
	Utility util = (Utility) session.getAttribute("util");
	if(!util.check(userId, userTen, sum)){
		response.sendRedirect(request.getContextPath() + "/redirect.jsp");
	}else{ %>


<% IGioihancongno ghcnBean = (IGioihancongno)session.getAttribute("ghcnBean"); %>
<% ResultSet ddkd = (ResultSet)ghcnBean.getDdkdList(); %>
<% ResultSet khachhang = (ResultSet)ghcnBean.getKhachhangList();  %>

<% Hashtable<Integer, String> kh_ghcnIds = (Hashtable<Integer, String>)ghcnBean.getKh_GhcnIds(); %>


<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd"> <% Utility Utilback = new Utility(); %>
<html>
<head>
<TITLE><%= getServletContext().getInitParameter("TITLENAME") %></TITLE>
<META http-equiv="Content-Type" content="text/html; charset=utf-8">
<META http-equiv="Content-Style-Type" content="text/css">
<LINK rel="stylesheet" href="../css/main.css" type="text/css">
<SCRIPT language="javascript" type="text/javascript">
	function confirmLogout()
	{
	   if(confirm("Bạn có muốn đăng xuất?"))
	   {
			top.location.href = "home.jsp";
	   }
	   return
	 }
	function submitform()
	{   
	   document.forms["ghcnForm"].submit();
	}
	function saveform()
	{	  
		 var diengiai = document.getElementById("diengiai");
		 var songayno = document.getElementById("songayno");
		 var sotienno = document.getElementById("sotienno");
		 
		 if(diengiai.value == "")
		 {
			alert("Vui lòng nhập diễn giải mức chiết khấu...");
			return;
		 }
		 if(songayno.value == "")
		 {
			alert("Vui lòng nhập số ngày nợ...");
			return;
		 }
		 if(sotienno.value == "")
		 {
			alert("Vui lòng nhập số tiền nợ...");
			return;
		 }
				 
		 document.forms['ghcnForm'].action.value='save';
	     document.forms['ghcnForm'].submit();
	}
</SCRIPT>
</head>
<body leftmargin="0" bottommargin="0" topmargin="0" rightmargin="0">
<form name="ghcnForm" method="post" action="../../GioihancongnoUpdateSvl">
<INPUT name="userId" type="hidden" value='<%=userId %>' size="30">
<input type="hidden" name="nppId" value='<%= ghcnBean.getNppId() %>'>
<input type="hidden" name="action" value='1'>
<TABLE width="100%" border="0" cellspacing="0" cellpadding="0" height="100%">
	<TR>
	  <TD colspan="4" align='left' valign='top' bgcolor="#FFFFFF"><!--begin body Dossier-->
			<TABLE width="100%" border="0" cellpadding="0" cellspacing="2">
				<TR>
					<TD align="left" class="tbnavigation">
					   <table width="100%" border="0" cellpadding="0" cellspacing="0">
						  <TR height="22">
						  	<TD align="left" colspan="2" class="tbnavigation"> &nbsp;Thiết lập dữ liệu nền > Dữ liệu Kinh doanh > Mức tín dụng &gt; Tạo mới
						  	<TD colspan="2" align="right" class="tbnavigation">Chào mừng  &nbsp; <%= ghcnBean.getNppTen() %>&nbsp; </TD>
						 </TR>
					  </table>
					</TD>
			  </TR>
			</TABLE>
			<TABLE width="100%" cellpadding="0" cellspacing="2">
				<TR >
					<TD >
						<TABLE width="100%" border="0" cellpadding="0" cellspacing="0">
							<TR class = "tbdarkrow">
								<TD width="30" align="left"><A href= "../../GioihancongnoSvl?userId=<%=userId %>">		 		
									<img src="../images/Back30.png" alt="Quay ve"  title="Quay ve" width="30" height="30" border="1" longdesc="Quay ve" style="border-style:outset"></A></TD>
								<TD width="2" align="left" ></TD>
								<TD width="30" align="left" ><A href="javascript:saveform()" ><IMG src="../images/Save30.png" title="Lưu lại" alt="Luu lai" border = "1"  style="border-style:outset"></A></TD>
				    			<TD align="left" >&nbsp;</TD>
							</TR>
						</TABLE>
					</TD>
				</TR>
			</TABLE>
					
			<TABLE width="100%" border="0" cellpadding="0" cellspacing="0" >
				<tr>
							<TD align="left" colspan="4" class="legendtitle">
								<FIELDSET>
		                        	<LEGEND class="legendtitle"><%=Utility.GLanguage("Báo lỗi nhập liệu",session,jedis) %></LEGEND>              
		                        	<textarea name="dataerror"  style="width: 100% ; color:#F00 ; font-weight:bold" cols="104%" rows="1" style="width: 100%" readonly="readonly"><%= ghcnBean.getMessage() %></textarea>
		                        	<% ghcnBean.setMessage(""); %>
                        		</FIELDSET>
						   </TD>
					</tr>
				<TR>
				  <TD >
						<FIELDSET>
						<LEGEND class="legendtitle">&nbsp;Thông tin mức tín dụng &nbsp;</LEGEND>
						<TABLE class="tblight" width='100%' cellspacing="0" cellpadding="6">
							<TR>
								<TD width="15%" class="plainlabel"><%=Utility.GLanguage("Diễn giải",session,jedis) %> mức tín dụng <FONT class="erroralert">*</FONT></TD>
								<TD width="85%" class="plainlabel"><INPUT name="diengiai" id="diengiai" style="width:400px"
										type="text" value="<%= ghcnBean.getDiengiai() %>" ></TD>
							</TR>
							<TR>
								<TD class="plainlabel">Số ngày tín dụng  <FONT class="erroralert">*</FONT></TD>
								<TD  class="plainlabel"><INPUT name="songayno" id="songayno" size="10"
										type="text" value="<%= ghcnBean.getSongayno() %>" ></TD>
							</TR>
							<TR>
								<TD class="plainlabel">Số tiền tín dụng <FONT class="erroralert">*</FONT></TD>
								<TD class="plainlabel" valign="middle">
								    <INPUT name="sotienno" id="sotienno" size="20" type="text" value="<%= ghcnBean.getSotienno() %>" > 
								</TD>
							</TR>
						</TABLE>
	
						</FIELDSET>
				 	</TD>	
				</TR>
               
			</TABLE>
        <TABLE width="100%" border="0" cellpadding="0" cellspacing="0">
				<TR>
					<TD colspan = "6" width='100%'>
						<FIELDSET><LEGEND class="legendtitle">khách hàng được hưởng tín dụng</LEGEND>
						  	<div  class="title" align="left" style="padding: 10px" >Khách hàng của nhân viên bán hàng
						  	<select name="ddkdTen" onChange = "submitform();">
								<option value="" selected></option>
									<% try{while(ddkd.next()){ 
								    	if(ddkd.getString("ddkdId").equals(ghcnBean.getDdkdId())){ %>
								      		<option value='<%= ddkd.getString("ddkdId") %>' selected><%= ddkd.getString("ddkdTen") %></option>
								      	<%}else{ %>
								     		<option value='<%= ddkd.getString("ddkdId") %>'><%= ddkd.getString("ddkdTen") %></option>
								     <%}}}catch(java.sql.SQLException e){} %>	   
								</select><br>
							</div>					
								
					<TABLE  border="0" cellspacing="1" cellpadding="6" width='100%'>
								<TR class="tbheader">
								  <TH align="center" width="18%">Mã khách hàng</TH>
									<TH width="30%">Tên khách hàng </TH>
									<TH width="45%">Địa chỉ </TH>
									<TH align="center" width="7%">Chọn </TH>
								</TR>
								<%
								int j = 0; 
								try{while(khachhang.next()){ 
									if (j % 2 != 0) {%>						
										<TR class= "tblightrow">
									<%} else {%>
										<TR class= "tbdarkrow" >
									<%}%>
									<TD align="center"><div align="center"><%= khachhang.getString("khId") %> </div></TD>
									<TD align="center"><div align="left"><%= khachhang.getString("khTen") %></div></TD>
									<TD align="center"><div align="left"><%= khachhang.getString("diachi") %> </div></TD>
									<% 
									 if(kh_ghcnIds.contains(khachhang.getString("khId"))){ %>
										   <TD align="center"><input name="khIds" type="checkbox" value ="<%= khachhang.getString("khId") %>" checked></TD>
									<%}else{%>
										   <TD align="center"><input name="khIds" type="checkbox" value ="<%= khachhang.getString("khId") %>"></TD>
									<%}%>
							     	<%j++;%> 
                                    </TR> 
							     	<%}}catch(java.sql.SQLException e){} %>
							     	   <tr class="tbfooter" > <td colspan="5" >&nbsp;</td> </tr>  
						</TABLE>
						</FIELDSET>								
					</TD>
				</TR>
		    </TABLE>
	    <!--end body Dossier--></TD>   
	    
	</TR>
</TABLE>
</form>
<%
	try
	{
		if(!(ddkd == null))
			ddkd.close();
		if(!(khachhang == null))
			khachhang.close();
		if(ghcnBean != null){
			ghcnBean.DBclose();
			ghcnBean = null;
		}
		
	}catch(java.sql.SQLException e){}
%>
</body>
</HTML>
<% } %>