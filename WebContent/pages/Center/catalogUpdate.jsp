<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page  import = "geso.dms.distributor.beans.catalog.*" %>
<%@ page  import = "geso.dms.distributor.beans.catalog.imp.*" %>
<%@ page  import = "java.util.Iterator" %>
<%@ page  import = "java.util.List" %>
<%@ page  import = "java.util.ArrayList" %>
<%@ page  import = "java.sql.ResultSet" %>
<%@ page  import = "geso.dms.center.util.*" %>
<%	String userId = (String) session.getAttribute("userId");  
	String userTen = (String) session.getAttribute("userTen");  	
	String sum = (String) session.getAttribute("sum");
	Utility util = (Utility) session.getAttribute("util");
	if(!util.check(userId, userTen, sum)){
		response.sendRedirect("/DEMO2/index.jsp");
	}else{ %>

<% 	Icatalog nspBean = (Icatalog)session.getAttribute("nspBean"); %>

<% 	 
String url = util.getChuyenNguUrl("catalogSvl", "",session);
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"> <% Utility Utilback = new Utility(); %>
<%@page import="java.sql.SQLException"%>
<HTML>
<HEAD>
<TITLE><%= getServletContext().getInitParameter("TITLENAME") %></TITLE>

<META http-equiv="Content-Type" content="text/html; charset=utf-8">
<META http-equiv="Content-Style-Type" content="text/css">
<LINK rel="stylesheet" href="../css/main.css" type="text/css">
<script type="text/javascript" src="../scripts/jquery.min.js"></script>
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
<LINK rel="stylesheet" type="text/css" media="screen" href="../css/tabs.css">
<LINK rel="stylesheet" type="text/css" media="screen" href="../css/tabs-panes.css">
<script type="text/javascript" src="../scripts/dropdowncontent.js"></script>
<script>

$(document).ready(function() {

    //When page loads...
    $(".tab_content").hide(); //Hide all content
    var index = $("ul.tabs li.current").show().index(); 
    $(".tab_content").eq(index).show();

    //On Click Event
    $("ul.tabs li").click(function() {
  
        $("ul.tabs li").removeClass("current"); //Remove any "active" class
        $(this).addClass("current"); //Add "active" class to selected tab
        $(".tab_content").hide(); //Hide all tab content  
        var activeTab = $(this).find("a").attr("href"); //Find the href attribute value to identify the active tab + content  
        $(activeTab).show(); //Fade in the active ID content
        return false;
    });

});
</script>
<SCRIPT language="JavaScript" type="text/javascript">
function submitform()
{
	var ma = document.getElementById('Ma');
	var ten = document.getElementById('ten');
	 
	 if(ma.value == "")
	 {
		 alert('Vui l??ng nh???p M?? Catalog');
		 return;
	 }
	 if(ten.value == "")
	 {
		 alert('Vui l??ng nh???p T??n Catalog');
		 return;
	 } 
	 
	 document.nspForm.setAttribute('enctype', "multipart/form-data", 0);	
		document.nspForm.action.value='save';
	    document.forms["nspForm"].submit();
}
function LuuFile()
{
	 document.nspForm.setAttribute('enctype', "multipart/form-data", 0);	
	 document.nspForm.action.value="download";
	 document.nspForm.submit();
}

function filterDvkd()
{
    document.nspForm.action.value='filter';
    document.nspForm.nhId.value='0';
    document.nspForm.clId.value='0';
    document.forms["nspForm"].submit();       
}

function filterNh()
{
    document.nspForm.action.value='filter';
    document.nspForm.clId.value='0';
    document.forms["nspForm"].submit();
}

function filterCl()
{
    document.nspForm.action.value='filter';
    document.forms["nspForm"].submit();       
}

function filter()
{
    document.nspForm.action.value='filter';
    document.forms["nspForm"].submit();       
}

function checkedAll() {
	var chk = document.getElementsByName("sanpham");
	for(i=0; i<chk.length; i++){
	 	if(document.nspForm.chon.checked==true){
			chk[i].checked = true;
		}else{
			chk[i].checked = false;
		}
	 }
}

function checkedAllNPP() {
	var chk = document.getElementsByName("npp");
	for(i=0; i<chk.length; i++){
	 	if(document.nspForm.chon.checked==true){
			chk[i].checked = true;
		}else{
			chk[i].checked = false;
		}
	 }
}

</SCRIPT>
</HEAD>
<BODY leftmargin="0" bottommargin="0" topmargin="0" rightmargin="0">
<form name="nspForm" method="post" action="../../catalogUpdateSvl">
<% redis.clients.jedis.Jedis jedis =  geso.dms.center.util.Utility.getJedis(); %><% geso.dms.center.util.Csrf csdr=new geso.dms.center.util.Csrf(request,response,true,false,true);%>
 <input type="hidden" name="<%=csdr.get_tokenName() %>" value='<%=csdr.get_tokenValue() %>'>

<input type="hidden" name="userId" value='<%=userId%>'>
<input type="hidden" name="nspId" value="<%=nspBean.getId()%>">
<input type="hidden" name="action" value="0">

<TABLE width="100%" border="0" cellspacing="0" cellpadding="0"
	height="100%">
	<TR>
		<TD colspan="4" align='left' valign='top' bgcolor="#FFFFFF">
			<TABLE width="100%" border="0" cellpadding="0" cellspacing="1">
				<TR>
					<TD align="left" class="tbnavigation">
						<table width="100%" border="0" cellpadding="0" cellspacing="0">
						  <tr height="22">
							 <TD align="left" colspan="2" class="tbnavigation">
							 		&nbsp;<%=url%> > <%=Utility.GLanguage("C???p nh???t",session,jedis) %></TD> 
							 <TD colspan="2" align="right" class="tbnavigation"><%=Utility.GLanguage("Ch??o m???ng b???n",session,jedis) %>  <%=userTen %>&nbsp;  </TD></tr>
						</table>
			 		</TD>
				</TR>
			</TABLE>
		<TABLE width="100%" border="0" cellpadding="0" cellspacing="1">
			<TR ><TD >
					<TABLE width="100%" border="0" cellpadding="0" cellspacing="0">
						<TR class = "tbdarkrow">
							<TD width="30" align="left"><A href="../../RouterSvl?task=<%= Utilback.dongMa(getServletContext().getInitParameter("RedirectNoScript") +"catalogSvl?userId="+userId+"") %>" ><img src="../images/Back30.png" alt="Quay ve"  title="Quay ve" border="1" longdesc="Quay ve" style="border-style:outset"></A></TD>
						    <TD width="2" align="left" ></TD>
						    <TD width="30" align="left" ><A href="javascript: submitform()" ><IMG src="../images/Save30.png" title="Luu lai" alt="Luu lai" border = "1"  style="border-style:outset"></A></TD>
							<TD >&nbsp; </TD>						
						</TR>
					</TABLE>
			</TD></TR>
		</TABLE>
			<TABLE width="100%" border="0" cellpadding="0"  cellspacing="1" >
			  	<tr>
					<TD align="left" colspan="4" class="legendtitle">
						<FIELDSET>
						<LEGEND class="legendtitle"><%=Utility.GLanguage("B??o l???i nh???p li???u",session,jedis) %></LEGEND>
				
	    				<textarea name="dataerror"  style="width: 100% ; color:#F00 ; font-weight:bold"  style="width: 100% ; color:#F00 ; font-weight:bold" readonly="readonly" rows="1"><%=nspBean.getMsg() %></textarea>
						
						</FIELDSET>
				   </TD>
				</tr>			

				<TR>
				  <TD height="100%" width="100%">
						<FIELDSET >
						<LEGEND class="legendtitle" style="color:black"><%=Utility.GLanguage("Th??ng tin Catalog",session,jedis) %> </LEGEND>
						<TABLE border="0" width="100%" cellpadding="6" cellspacing="0">
							<TR>
						  	  	<TD class="plainlabel"><%=Utility.GLanguage("M?? Catalog",session,jedis) %> <FONT class="erroralert"> *</FONT> </TD>
						  	  	<TD class="plainlabel" >
						  	  	<INPUT type="text" name="Ma" id="Ma" style="width:200px" value='<%=nspBean.getMa()%>'>
						  	  	</TD>
						  	  	<TD class="plainlabel"><%=Utility.GLanguage("T??n Nh??n h??ng",session,jedis) %></TD>
						  	  	<TD class="plainlabel" >
						  	  	<INPUT type="text" name="nhanhang" style="width:200px" value='<%=nspBean.getNhanhang()%>'>
						  	  	
						  	  	</TD>
						  	</TR>
							
							<TR>
								<TD class="plainlabel" ><%=Utility.GLanguage("T??n Catalog",session,jedis) %> <FONT class="erroralert"> *</FONT></TD>
								<TD class="plainlabel"><INPUT type="text" name="ten" id="ten" style="width:200px" value='<%=nspBean.getTen() %>'></TD>
								<TD class="plainlabel">Ch???ng Lo???i </TD>
							  	<TD class="plainlabel">
										<INPUT type="text" name="chungloai" style="width:200px" value='<%=nspBean.getChungloai() %>'>
					 	  		</TD>
							</TR>
									  	  		
						  	<TR>
						  	  	<TD class="plainlabel"><%=Utility.GLanguage("Ghi ch??",session,jedis) %></TD>
						  	  	<TD class="plainlabel" >
						  	  	<INPUT type="text" name="ghichu" style="width:200px" value='<%=nspBean.getGhichu() %>'>
						  	  	</TD>
						  	  	<TD class="plainlabel"><%=Utility.GLanguage("File",session,jedis) %></TD>
						  	  	<TD class="plainlabel" >
						  	  	<INPUT type="file" name="filename" style="width:200px" value=''>
						  	  
						  	  	</TD>
						  	</TR>
						  	<%if(nspBean.getDuongdan().trim().length() > 0) {%>
						  	<TR>
						  	  	<TD class="plainlabel"></TD>
						  	  	<TD class="plainlabel" >
						  	  	</TD>
						  	  	<TD class="plainlabel"><%=Utility.GLanguage("T??n file",session,jedis) %></TD>
						  	  	<TD class="plainlabel" >
						  	   	<input type="hidden" id="valueten" name="tenfile" value="<%= nspBean.getDuongdan() %>">
						  	 	<div id="tenfilea" ><p><%= nspBean.getDuongdan() %><img src="../images/Save20.png" onclick="LuuFile()" style="cursor: pointer;" alt="L??u File" width="20" height="20" longdesc="L??u File" border = 0></p></div>
						  	  	</TD>
						  	</TR>
						  	<%} %>
					  		
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
<% %>
<%}%>