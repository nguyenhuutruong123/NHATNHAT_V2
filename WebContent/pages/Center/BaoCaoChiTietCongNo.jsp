<%@page import="java.sql.ResultSet"%>
<%@page import="java.sql.SQLException"%>
<%@page import="geso.dms.distributor.beans.hoadontaichinh.IBCChiTietCongNo"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page  import = "geso.dms.center.util.*" %>
<%	String userId = (String) session.getAttribute("userId");  
	String userTen = (String) session.getAttribute("userTen");  	
	String sum = (String) session.getAttribute("sum");
	String loi=(String)session.getAttribute("loi");
	String tungay=(String)session.getAttribute("tungay");
	String denngay=(String)session.getAttribute("denngay");
	Utility util = (Utility) session.getAttribute("util");
	if(!util.check(userId, userTen, sum)){
		response.sendRedirect(request.getContextPath() + "/redirect.jsp");
	}else{ %>

	<% 
	IBCChiTietCongNo obj = (IBCChiTietCongNo)session.getAttribute("obj"); 
	%>
	<% ResultSet rsNpp = obj.getNppRs(); %>
	<% ResultSet KhachHangList = (ResultSet)obj.getKHList(); 
	   ResultSet nppRs = obj.DoiTacHORs(); %>
	<% ResultSet rsKhuVuc = obj.getkhuvuc(); %>
	<% ResultSet rsVung = obj.getvung();
		ResultSet ttRs = obj.getTtRs();
	%>	
	
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"> <% Utility Utilback = new Utility(); %>

<HTML>
<HEAD>
<TITLE><%= getServletContext().getInitParameter("TITLENAME") %></TITLE>
	<link type="text/css" rel="stylesheet" href="../css/mybutton.css">
<META http-equiv="Content-Style-Type" content="text/css">
<LINK rel="stylesheet" href="../css/main.css" type="text/css">
<LINK rel="stylesheet" href="../css/calendar.css" type="text/css">
<script type="text/javascript"	src="../scripts/jquery.min.1.7.js"></script>
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

<link href="../css/select2.css" rel="stylesheet" />
<script src="../scripts/select2.js"></script>
<script>
$(document).ready(function()
		{
			$("#KhachHang").select2();
			$("#nppId").select2();
			$("#vungId").select2();
			$("#khuvucId").select2();
			$("#doitacId").select2();
			$("#nppHOId").select2();
		});


</script>


<script type="text/javascript">
function submitform1()
{
	if (document.forms["rpForm"]["tuNgay"].value.length == 0) {
		setErrors("Vui lòng chọn ngày bắt đầu");
		return;
	}
	if (document.forms["rpForm"]["denNgay"].value.length == 0) {
		setErrors("Vui lòng chọn ngày kết thúc");
		return;
	}			
	var fieldShow = document.getElementsByName("fieldsHien");		
	for ( var i = 0; i < fieldShow.length; ++i) {
		fieldShow.item(i).checked = true;
	}		
	
	document.forms['rpForm'].action.value="excel";
	document.forms['rpForm'].submit();
	reset();
}

function submitform()
{		
	document.forms['rpForm'].action.value="";
	document.forms['rpForm'].submit();
	reset();
}

function setErrors(errorMsg) {
	var errors = document.getElementById("errors");
	errors.value = errorMsg;
}

function reset() {
	var fieldShow = document.getElementsByName("fieldsHien");		
	for ( var i = 0; i < fieldShow.length; ++i) {
		fieldShow.item(i).checked = false;
	}		
};
</SCRIPT>
</HEAD>
<BODY leftmargin="0" bottommargin="0" topmargin="0" rightmargin="0">
<form name="rpForm" method="post" action="../../BCChiTietCongNoSvl">
<input type="hidden" name="userId" value= <%= userId %> >
<input type="hidden" name="action" value='1'>
<input type="hidden" name="view" value='TT'>

<TABLE width="100%" border="0" cellspacing="0" cellpadding="0"
	height="100%">
	
	<TR>
		<TD colspan="4" align='left' valign='top' bgcolor="#FFFFFF">
			<TABLE width="100%" cellpadding="0" cellspacing="1">		
				<TR>
					<TD width="100%" align="left">
					  <table width="100%" border="0" cellpadding="0" cellspacing="0">
						  <tr height="22">
						   <TD align="left" colspan="2" class="tbnavigation">&nbsp;Quản lý công nợ &gt; Báo cáo &gt; Báo cáo chi tiết công nợ </TD>
   
						   <TD colspan="2" align="right" class="tbnavigation"><%=Utility.GLanguage("Chào mừng bạn",session,jedis) %>  <%=userTen %>&nbsp;</TD>
						  </tr>
 					  </table>					</TD>
				</TR>
				<tr>
					<TD align="left" colspan="4" class="legendtitle">
						<FIELDSET>
						<LEGEND class="legendtitle"><%=Utility.GLanguage("Báo lỗi nhập liệu",session,jedis) %></LEGEND>
	    				<textarea id="errors" readonly="readonly" name="errors" rows="1" style="width: 100% ; color:#F00 ; font-weight:bold">
						
					</textarea>
						</FIELDSET>
				   </TD>
				</tr>	
				<TR>
					<TD>
					<TABLE border="0" width="100%" cellpadding="0" cellspacing="0">
						<TR>
							<TD width="100%" align="center" >
							<FIELDSET>
							  <LEGEND class="legendtitle">Thời gian xuất báo cáo</LEGEND>
							<TABLE  width="100%" cellpadding="6" cellspacing="0">
								<TR>
								  	<TD width="15%" class="plainlabel" ><%=Utility.GLanguage("Từ ngày",session,jedis) %></TD>
								  	<TD class="plainlabel" >
										<TABLE cellpadding="0" cellspacing="0">
											<TR><TD>
												<input type="text" class="days" name="tuNgay" size="20" value = "<%=tungay%>" style="width: 230px;">
												</TD>
												<TD>		   										
												</TD>
                                    		</TR>
										</TABLE>									
									</TD>
										<TD class="plainlabel" ><%=Utility.GLanguage("Đến ngày",session,jedis) %> </TD>
								  	<TD class="plainlabel" >
							  			<TABLE cellpadding="0" cellspacing="0">
							  				<TR>
							  					<TD>
													<input type="text"  class="days" name="denNgay" size="20" value = "<%=denngay%>" style="width: 230px;">												</TD>
												<TD>                                        		</TD>
                                     		</TR>
										</TABLE>									
								   </TD>
										
								</TR>				
																
								<TR>								
								 <TD class="plainlabel">Miền</TD>
									<TD class="plainlabel">
										<select  name="vungId" onchange="submitform();" id="vungId" name="vungId" style="width:230px">
											<option value="">All</option>
											<% if(rsVung!=null){
										 		while(rsVung.next()){
													String vungId = rsVung.getString("pk_seq");
													if(vungId.equals(obj.getvungId())){%>
														<option selected="selected" value="<%=rsVung.getString("pk_seq")%>">
															<%=rsVung.getString("ten")%></option>
													<%}else{%>
														<option value="<%=rsVung.getString("pk_seq")%>">
															<%=rsVung.getString("ten")%></option>
										 	<% }}}%>										 	
										</select>
									</TD>
									
									<TD class="plainlabel"><%=Utility.GLanguage("Khu vực",session,jedis) %></TD>
									<TD class="plainlabel">
										<select  name="khuvucId" onchange="submitform();"  id="khuvucId" name="khuvucId" style="width:230px">
											<option value="">All</option>
											<% if(rsKhuVuc!=null){
										 		while(rsKhuVuc.next()){
													String kvId = rsKhuVuc.getString("pk_seq");
													if(kvId.equals(obj.getkhuvucId())){%>
														<option selected="selected" value="<%=rsKhuVuc.getString("pk_seq")%>">
															<%=rsKhuVuc.getString("ten")%></option>
													<%}else{%>
														<option value="<%=rsKhuVuc.getString("pk_seq")%>">
															<%=rsKhuVuc.getString("ten")%></option>
										 	<% }}}%>										 	
										</select>
									</TD>
									</TR>								
									
									<TR>			
									<TD class="plainlabel">Đối tác/Nhà phân phối</TD>
									<TD class="plainlabel" colspan="4">
										<select name="nppHOId" id="nppHOId" style="width:230px" onchange="submitform();">
												<option value="" selected >All</option>
												<% 
												if(nppRs != null)
												{
													try
				                        			{	
													   while(nppRs.next()){
													   if(nppRs.getString("pk_seq").equals(obj.getDoiTacHOId()))
													   { %>
													   <option value="<%= nppRs.getString("pk_seq") %>" selected><%=nppRs.getString("ten") %></option>
													   <%}else { %>
													   <option value="<%= nppRs.getString("pk_seq") %>"><%=nppRs.getString("ten") %></option>
													<%} }nppRs.close();} catch(SQLException ex){}%>
												<%} %>
										</select>
									</TD>   
			                    </TR>
                    
								 <TR>
									  <TD class="plainlabel" >Khách Hàng</TD>
							      		<TD class="plainlabel" colspan="3">
							      		  <SELECT name="KhachHang" id="KhachHang" style="width: 230px;" >
							      			<option value="" selected>All</option>
							        	<% try{
							        		if(KhachHangList != null)
							        		{
							        		 while(KhachHangList.next()){ 
							    	      if(KhachHangList.getString("PK_SEQ").equals(obj.getKHId())){ %>
							      			<option value='<%= KhachHangList.getString("PK_SEQ") %>' selected> <%= KhachHangList.getString("TEN") %></option>
							      	    <%}else{ %>
							     			<option value='<%=KhachHangList.getString("PK_SEQ") %>'> <%= KhachHangList.getString("TEN") %></option>
							     	   <%}
							    	        }KhachHangList.close();
							        	  }
							        	  }catch(java.sql.SQLException e){} %>
							          </SELECT></TD>
								</TR>
								
							    <TR>
									<TD colspan="4" class="plainlabel">
									<a class="button2" href="javascript:submitform1()" >
									<img style="top: -4px;" src="../images/button.png" alt="">EXCEL</a>&nbsp;&nbsp;&nbsp;&nbsp; 
									</TD>
								</TR>
							</TABLE>
							</FIELDSET>						</TD>
						</TR>
					</TABLE>					</TD>
				</TR>
			</TABLE>
		</TD>
	</TR>
</TABLE>
</form>
</BODY>
</HTML>
<%
	}%>