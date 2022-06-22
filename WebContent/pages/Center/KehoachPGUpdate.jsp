<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page
	import="geso.dms.center.beans.kehoachpg.IKehoachPG"%>
<%@ page import="java.util.Hashtable"%>
<%@ page import="java.sql.ResultSet "%>
<%@ page import="geso.dms.center.util.*"%>
<%	String userId = (String) session.getAttribute("userId");  
	String userTen = (String) session.getAttribute("userTen");  	
	String sum = (String) session.getAttribute("sum");
	Utility util = (Utility) session.getAttribute("util");
	if (!util.check(userId, userTen, sum)){
		response.sendRedirect("/SEANEST/index.jsp");
	}else{ %>


<% IKehoachPG ddkdBean = (IKehoachPG)session.getAttribute("ddkdBean"); %>
<% ResultSet npp = (ResultSet)ddkdBean.getNppList(); %>
<% String nppId = (String)ddkdBean.getNppId(); %>


<% 

String url = "";
if (ddkdBean.getView() != null && ddkdBean.getView().equals("TT")) {
	url = util.getUrl("KehoachPGSvl","&view=TT"); 
}
else {
	url = util.getUrl("KehoachPGSvl",""); 
}
String view = ddkdBean.getView();

%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"> <% Utility Utilback = new Utility(); %>
<HTML>
<HEAD>
<TITLE><%= getServletContext().getInitParameter("TITLENAME") %></TITLE>
<META http-equiv="Content-Type" content="text/html; charset=utf-8">

<META http-equiv="Content-Style-Type" content="text/css">

<LINK rel="stylesheet" href="../css/main.css" type="text/css">
<script type="text/javascript" src="../scripts/jquery.min.1.7.js"></script>
<script type="text/javascript" src="../scripts/maskedinput.js"></script>
<link href="../css/jquery-ui.css" rel="stylesheet" type="text/css" />
<script src="../scripts/ui/jquery.ui.core.js" type="text/javascript"></script>
<script src="../scripts/ui/jquery.ui.widget.js" type="text/javascript"></script>
<script src="../scripts/ui/jquery.ui.datepicker.js"
	type="text/javascript"></script>
<link type="text/css" rel="stylesheet" href="../css/mybutton.css">
<script type="text/javascript">
	$(document).ready(function() {
		  $("#ngaysinh").mask("9999-99-99");
			$( ".days" ).datepicker({			    
					changeMonth: true,
					changeYear: true				
			});            
        }); 		
		
</script>

<link href="../css/select2.css" rel="stylesheet" />
<script src="../scripts/select2.js"></script>
<script>
     $(document).ready(function() { 
      $("select:not(.notuseselect2)").select2({ width: 'resolve' });     
     });
</script>

<SCRIPT language="javascript" type="text/javascript">
 function confirmLogout(){
    if (confirm("Bạn có muốn đăng xuất?"))
    {
		top.location.href = "home.jsp";
    }
    return
  }
 function submitform()
 {   
    document.forms["ddkdForm"].submit();
 }
 
 function xuatExcel()
 {
	 
	 
	 if(document.forms["ddkdForm"].nppId.value=="")
	 {
		 alert('Vui lòng chọn nhà phân phối');
		 return;
	 }
	 
 	 document.forms['ddkdForm'].action.value='excel';
     document.forms['ddkdForm'].submit();
 }

 function saveform()
 {
	 if(document.forms["ddkdForm"].nppId.value=="")
	 {
		 alert('Vui lòng chọn nhà phân phối');
		 return;
	 }
	 if(document.forms["ddkdForm"].filename.value=="")
	 {   
		 alert("Chưa tìm file upload lên. Vui lòng chọn file cần upload.");
		 return;
	 }
	 
	 document.forms["ddkdForm"].setAttribute('enctype', "multipart/form-data", 0);	 
 	 document.forms['ddkdForm'].action.value='save';
     document.forms['ddkdForm'].submit();
 }
 function keypress(e) 
 {    
 	var keypressed = null;
 	if (window.event)
 		keypressed = window.event.keyCode; //IE
 	else
 		keypressed = e.which; //NON-IE, Standard
 	
 	if (keypressed < 48 || keypressed > 57)
 	{ 
 		if (keypressed == 8 || keypressed == 127 || keypressed == 37 || keypressed == 39)
 		{//Phím Delete và Phím Back
 			return;
 		}
 		return false;
 	}
 }
</SCRIPT>


<style type="text/css">
.uploadify-button {
	background-color: transparent;
	border: none;
	padding: 0;
}

.uploadify:hover .uploadify-button {
	background-color: transparent;
}
</style>

<script src="../Uploadify/swfobject.js" type="text/javascript"></script>
<script src="../Uploadify/jquery.uploadify.v2.1.4.min.js"
	type="text/javascript"></script>
<link href="../Uploadify/uploadify.css" rel="stylesheet" type="text/css" />

<script type="text/javascript">
     $(function () {
         $('#file_upload').uploadify({
             'buttonImage': '../Uploadify/images.jpg',
             'uploader': '../Uploadify/uploadify.swf',
             'script': '../../DaidienkinhdoanhUpdateSvl?flag=saveFile',
             'cancelImg': '../Uploadify/cancel.png',
             'buttonText': ' Upload File Hinh ',
             'folder': 'Files',
             'fileDataName': 'Filedata',
             'fileDesc': 'Images Files',
             'fileExt': '*.jpg; *.png; *.gif',
             'multi': false,
             'auto': true,
             'removeCompleted': false,
             'onComplete': function (event, queueID, fileObj, response, data) {
              document.getElementById("hinhdaidien").innerHTML = "<img src='../Templates/images/" + response + "' style='max-width:50px; max-height:50px' />";
              document.getElementById("fileName").value = response;
             }
         });

     });
 </script>
</HEAD>

<BODY leftmargin="0" bottommargin="0" topmargin="0" rightmargin="0">


	<form name="ddkdForm" method="post"
		action="../../KehoachPGUpdateSvl"> 
 <% redis.clients.jedis.Jedis jedis =  geso.dms.center.util.Utility.getJedis(); %><% geso.dms.center.util.Csrf csdr=new geso.dms.center.util.Csrf(request,response,true,false,true);%>
 <input type="hidden" name="<%=csdr.get_tokenName() %>" value='<%=csdr.get_tokenValue() %>'>
 
		<INPUT name="userId" type="hidden" value='<%= userId %>' size="15">
		<input type="hidden" name="action" value='1'>
		<input type="hidden" name="id" value='<%=  ddkdBean.getId() %>'>
		
		<input type="hidden" name="view" value='<%=view%>'>
		<TABLE width="100%" border="0" cellspacing="0" cellpadding="0"
			height="100%">
			<TR>
				<TD colspan="4" align='left' valign='top' bgcolor="#FFFFFF">
					<TABLE width="100%" border="0" cellpadding="0" cellspacing="1">
						<TR>
							<TD align="left" class="tbnavigation">
								<table width="100%" border="0" cellpadding="0" cellspacing="0">
									<tr height="22">
										<TD align="left" colspan="2" class="tbnavigation">&nbsp;<%=url %> > Cập nhật</TD>
										<TD colspan="2" align="right" class="tbnavigation">Chào mừng<%=userTen %>&nbsp;
										</TD>
									</tr>

								</table>
							</TD>
						</TR>
					</TABLE>
					<TABLE width="100%" border="0" cellpadding="0" cellspacing="1">
						<TR class="tbdarkrow">
							<TD width="30" align="center"><A
								href="javascript:history.back()"><img
									src="../images/Back30.png" alt="Back" title="Back"
									width="30" height="30" border="1" longdesc="Back"
									style="border-style: outset"></A></TD>
							<TD width="2" align="left"></TD>
							<TD width="30" align="left"><A href="javascript:saveform()"><IMG
									src="../images/Save30.png" title="Save" alt="Save"
									border="1" style="border-style: outset"></A></TD>

							<TD align="left">&nbsp;</TD>
						</TR>
					</TABLE>
					<TABLE width="100%" border="0" cellpadding="0" cellspacing="0">
						<TR>
							<TD align="left" colspan="4" class="legendtitle">
								<FIELDSET>
									<LEGEND class="legendtitle">"<%=Utility.GLanguage("Báo lỗi nhập liệu",session,jedis) %></LEGEND>

									<textarea name="dataerror"
										style="width: 100%; color: #F00; font-weight: bold"
										style="width: 100% ; color:#F00 ; font-weight:bold"
										readonly="readonly" rows="1"><%= ddkdBean.getMessage() %></textarea>
									<%ddkdBean.setMessage(""); %>
								</FIELDSET>
							</TD>
						</TR>

						<TR>
							<TD height="100%" width="100%">
								<FIELDSET>
									<LEGEND class="legendtitle" style="color: black">Báo lỗi</LEGEND>
									<TABLE border="0" width="100%" cellpadding="4" cellspacing="0">

								
										<TR class="plainlabel">
											<TD width="130px" class="plainlabel" valign="top">Chọn File</TD>
											<TD width="300px" class="plainlabel" valign="top"><INPUT
												type="file" name="filename" size="40" value=''></td>


										</TR>


										<TR>
											<TD width="130px" class="plainlabel" valign="top"><%=Utility.GLanguage("Diễn giải",session,jedis) %> <FONT class="erroralert">*</FONT>
											</TD>
											<TD width="300px" class="plainlabel" valign="top"><INPUT
												name="ddkdTen" maxlength="50" id="ddkdTen" type="text"
												value="<%= ddkdBean.getTen() %>"></TD>
											

										</TR>
										
										<TR>
											<TD width="130px" class="plainlabel" valign="top">Tuần<FONT class="erroralert">*</FONT>
											</TD>
											<TD width="300px" class="plainlabel" valign="top">
												<select name="tuan" style="width :300px"  onChange="submitform()">
												<option value=0> </option>  
												<%
			  										
													int k ;
			  									for(k = 1 ;k<=52;k++){
			  									  if(Integer.parseInt( ddkdBean.getTuan() ) == k){
			  									%>
												<option value=<%=k%> selected="selected" > <%=k%></option> 
												<%
			 										}else{
			 									%>
												<option value=<%=k%> ><%=k%></option> 
												<%
			 										}
			 									  }
			 									%>
												</select>
												
											</TD>
											

										</TR>
										
										
										<TR>
											<TD width="130px" class="plainlabel" valign="top">Năm<FONT class="erroralert">*</FONT>
											</TD>
												<TD width="300px" class="plainlabel" valign="top">
												<select name="nam" style="width :300px"  onChange="submitform()" >
												<option value=0> </option>  
												<%
			  										int min= Integer.parseInt( ddkdBean.getNam() ) - 2;
												int max = Integer.parseInt( ddkdBean.getNam() ) + 2;
												
			  									for(k = min ;k<=max;k++){
			  									  if(Integer.parseInt( ddkdBean.getNam() ) == k){
			  									%>
												<option value=<%=k%> selected="selected" > <%=k%></option> 
												<%
			 										}else{
			 									%>
												<option value=<%=k%> ><%=k%></option> 
												<%
			 										}
			 									  }
			 									%>
												</select>
												
											</TD>

										</TR>
										
										<TR>
											<TD width="130px" class="plainlabel" valign="top"><%=Utility.GLanguage("Từ ngày",session,jedis) %> <FONT class="erroralert">*</FONT>
											</TD>
											<TD width="300px" class="plainlabel" valign="top"><INPUT
												readonly="readonly" name="tungay" maxlength="50" id="tungay" type="text"
												value="<%= ddkdBean.getTungay() %>"></TD>
											

										</TR>
										<TR>
											<TD width="130px" class="plainlabel" valign="top"><%=Utility.GLanguage("Đến ngày",session,jedis) %><FONT class="erroralert">*</FONT>
											</TD>
											<TD width="300px" class="plainlabel" valign="top"><INPUT
												readonly="readonly" name="denngay" maxlength="50" id="denngay" type="text"
												value="<%= ddkdBean.getDenngay() %>"></TD>
											

										</TR>
										
									
										
										<TR>
											<TD class="plainlabel">Nhà phân phối
												<FONT class="erroralert">*</FONT>
											</TD>
											<TD  class="plainlabel"><SELECT name="nppId"
												id="nppId" onChange="submitform()">
													<option value=""></option>
													<% if (npp != null){
										  try
										  { 
											  String optionGroup = "";
											  String optionName = "";
											  int i = 0;
											  
											  while(npp.next())
											  { 
												 if (i == 0)
												 {
													 optionGroup = npp.getString("kvTen");
													 optionName = npp.getString("kvTen");
													 
													 %>

													<optgroup label="<%= optionName %>">
														<% }
												 
												 optionGroup = npp.getString("kvTen");
												 if (optionGroup.trim().equals(optionName.trim()))
												 { %>
														<% if (npp.getString("nppId").equals(nppId)) {%>
														<option value="<%= npp.getString("nppId") %>"
															selected="selected"><%= npp.getString("nppTen") %></option>
														<%} else { %>
														<option value="<%= npp.getString("nppId") %>"><%= npp.getString("nppTen") %></option>
														<%} %>
														<% }
												 else
												 {
													 %>
													</optgroup>
													<% optionName = optionGroup; %>
													<optgroup label="<%= optionName %>">
														<% if (npp.getString("nppId").equals(nppId)) {%>
														<option value="<%= npp.getString("nppId") %>"
															selected="selected"><%= npp.getString("nppTen") %></option>
														<%} else { %>
														<option value="<%= npp.getString("nppId") %>"><%= npp.getString("nppTen") %></option>
														<%} %>
														<% }
												 i++;
								     	 	  } 
											  %>
													</optgroup>
													<%npp.close(); 
								     	 }catch(java.sql.SQLException e){} } %>
											</SELECT></TD>
											
										
												
										</TR>
										<TR>
											
											<TD colspan="2" class="plainlabel" >&nbsp;&nbsp;&nbsp;&nbsp;
												<a class="button2" href="javascript:xuatExcel()"> <img
													 src="../images/button.png" alt="">
													Xuất teamplate
											</a>
											</td>
										</TR>
									
										
										
									</TABLE>
								</FIELDSET>
								<TABLE border="0" width="100%" cellpadding="0" cellspacing="0">
									<TR>
										<TD colspan="4">
											
										</TD>
									</TR>
								</TABLE>
							</TD>
						</TR>
					</TABLE>
				</TD>
			</TR>
		</TABLE>
	</form>
</body> <!--  <script type='text/javascript' src='../scripts/loadingv2.js'></script> -->
</HTML>
<%
try{
	if (npp!=null){
		npp.close();
	}
	
	
	ddkdBean.DBClose();
}catch(Exception er){
	
}

}%>