<%@page import="java.sql.SQLException"%>
<%@page import="java.text.DecimalFormat"%>
<%@page import="java.text.NumberFormat"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page  import = "java.util.Iterator" %>
<%@ page  import = "java.util.List" %>
<%@ page  import = "java.util.ArrayList" %>
<%@ page  import = "geso.dms.distributor.beans.hoadontaichinh.*" %>
<%@ page  import = "java.sql.ResultSet" %>
<%@ page  import = "geso.dms.center.util.*" %>
<% IBCBangCanDoiPhatSinhCongNo obj = (IBCBangCanDoiPhatSinhCongNo)session.getAttribute("obj"); %>
<% ResultSet khRs = obj.getKhRs();%>

<% ResultSet ddkdRs = obj.getDdkdRs();%>
<% ResultSet nvgnRs = obj.getNvgnRs();%>

<% String userId = (String) session.getAttribute("userId");  %>
<% String userTen = (String) session.getAttribute("userTen");  %>
<% ResultSet nppRs = obj.getDoiTacRs();

	ResultSet chungloaiRs= obj.getChungloaiRs();
	String sum = (String) session.getAttribute("sum");
	Utility util = (Utility) session.getAttribute("util");
	if(!util.check(userId, userTen, sum)){
		response.sendRedirect(request.getContextPath() + "/redirect.jsp");
	}else{
		int[] quyen = new  int[5];
		quyen = util.Getquyen("BCBangCanDoiPhatSinhCongNoSvl","",userId);%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd"> <% Utility Utilback = new Utility(); %>
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
	<title><%= getServletContext().getInitParameter("TITLENAME") %></title>
	<META http-equiv="Content-Type" content="text/html; charset=utf-8">
	<META http-equiv="Content-Style-Type" content="text/css">
    <LINK rel="stylesheet" type="text/css" media="print" href="../css/impression.css">
    <LINK rel="stylesheet" href="../css/main.css" type="text/css">
    <link type="text/css" rel="stylesheet" href="../css/mybutton.css">
    
    <LINK rel="stylesheet" href="../css/datepicker.css" type="text/css">
    <script language="javascript" src="../scripts/datepicker.js"></script>
   	<link href="../css/jquery-ui.css" rel="stylesheet" type="text/css"/>
  	<script type="text/javascript" src="../scripts/Timepicker/jquery.min.js"></script>
  	<script type="text/javascript" src="../scripts/Timepicker/jquery-ui.min.js"></script>

	<script type="text/javascript" src="../scripts/jquery.min.1.7.js"></script>
	<link href="../css/jquery-ui.css" rel="stylesheet" type="text/css" />
	<script src="../scripts/ui/jquery.ui.core.js" type="text/javascript"></script>
	<script src="../scripts/ui/jquery.ui.widget.js" type="text/javascript"></script>
	<script type="text/javascript" src="../scripts/phanTrang.js"></script>
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
		 function submitform()
		 {  
			document.forms['ckParkForm'].action.value= 'search';
		    document.forms['ckParkForm'].submit();
		 }
		 
		 function xuatExcel()
		 {
			 if (document.forms["ckParkForm"]["tuNgay"].value.length == 0) {
					setErrors("Vui l??ng ch???n ng??y b???t ?????u");					
					return;
				}
				if (document.forms["ckParkForm"]["denNgay"].value.length == 0) {
					setErrors("Vui l??ng ch???n ng??y k???t th??c");
					return;
				}			
				var fieldShow = document.getElementsByName("fieldsHien");		
				for ( var i = 0; i < fieldShow.length; ++i) {
					fieldShow.item(i).checked = true;
				}	
				
		 	document.forms['ckParkForm'].action.value= 'excel';
		 	document.forms['ckParkForm'].submit();
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
	
<link href="../css/select2.css" rel="stylesheet" />
<script src="../scripts/select2.js"></script>
<script>
     $(document).ready(function() { $("select").select2(); });
     
</script>
</head>
<body>
<form name="ckParkForm" method="post" action="../../BCBangCanDoiPhatSinhCongNoSvl">
<input type="hidden" name="userId" value="<%= userId %>" >
<input type="hidden" name="action" value="1" >
<input type="hidden" name="msg" value='<%= obj.getMsg() %>'>
<input type="hidden" name="nppId" value="<%= obj.getNppId() %>" >
<input type="hidden" name="view" value='NPP'>

<div id="main" style="width:100%; padding-left:2px">
	<div align="left" id="header" style="width:100%; float:none">
    	<div style="width:55%; padding:5px; float:left" class="tbnavigation">
        	&nbsp;Qu???n l?? c??ng n??? > B??o c??o > B???ng c??n ?????i ph??t sinh c??ng n???
        </div>
        <div align="right" style="padding:5px" class="tbnavigation">
        	<%=Utility.GLanguage("Ch??o m???ng b???n",session,jedis) %>  <%= userTen %> &nbsp;
        </div>
    </div>
  	
  	<div align="left" style="width:100%; float:none; clear:left">
  		<tr>
			<TD align="left" colspan="4" class="legendtitle">
				<FIELDSET>
					<LEGEND class="legendtitle"><%=Utility.GLanguage("B??o l???i nh???p li???u",session,jedis) %></LEGEND>
							<textarea id="errors" readonly="readonly" name="errors" rows="1" style="width: 100% ; color:#F00 ; font-weight:bold">
						<%= obj.getMsg() %>
						</textarea>
				</FIELDSET>
			</TD>
		</tr>
  	</div>
    
  	<div id="cotent" style="width:100%; float:none">
    	<div align="left" style="width:100%; float:none; clear:left; font-size:0.7em">
        <fieldset style="margin-top:5px" >
            <legend class="legendtitle"> <%=Utility.GLanguage("Ti??u ch?? t??m ki???m",session,jedis) %></legend>
                <TABLE width="100%" cellpadding="6px" cellspacing="0px" style="margin-top: 5px " >
                	 <TR>
                                             
                        <TD width="19%" class="plainlabel" ><%=Utility.GLanguage("T??? ng??y",session,jedis) %></TD>
								  	<TD class="plainlabel" >
										<TABLE cellpadding="0" cellspacing="0">
											<TR><TD>
												<input type="text" class="days" name="tuNgay" size="20" value = "<%=obj.getTungay()%>" >
												</TD>
                                    		</TR>
										</TABLE>									</TD>
                    
                        <TD class="plainlabel" ><%=Utility.GLanguage("?????n ng??y",session,jedis) %> </TD>
								  	<TD class="plainlabel" >
							  			<TABLE cellpadding="0" cellspacing="0">
							  				<TR>
							  					<TD>
													<input type="text"  class="days" name="denNgay" size="20" value = "<%=obj.getDenngay()%>" >												</TD>
												<TD>                                        		</TD>
                                     		</TR>
										</TABLE>									</TD>
                    </TR>
                    
                     <TR>
							<TD class="plainlabel"> M???c l???y  </TD>
							<TD class="plainlabel" colspan="3">
							<%
								if(obj.gettype().equals("0")){
									%>
									<input type="radio" name="type" value="1"  onchange="submitform();" /> ?????i t??c &nbsp; &nbsp;
									<input type="radio" name="type" value="0"  checked="checked"  onchange="submitform();" />Kh??ch h??ng
									<%
								}
								else
								{
									%>
										<input type="radio" name="type" value="1"  checked="checked"  onchange="submitform();" />?????i t??c  &nbsp; &nbsp;
										<input type="radio" name="type" value="0"  onchange="submitform();" /> Kh??ch h??ng
									<%
								}
							%>
								
							</TD>									
					</TR>
					
					<%if(obj.gettype().equals("0")) {%>         
                    <TR>									
						<TD class="plainlabel">Nh??n vi??n giao nh???n </TD>
						<TD class="plainlabel" colspan="1"><select name="nvgnId" id="nvgnId" style="width:202px">
								<option value="" selected >All</option>
								<% if(nvgnRs != null)
								{
									try
                        			{	
								   while(nvgnRs.next()){
								   if(nvgnRs.getString("pk_seq").equals(obj.getNvgnId()  ))
								   { %>
								   <option value="<%= nvgnRs.getString("pk_seq") %>" selected><%=nvgnRs.getString("ten") %></option>
								   <%}else { %>
								   <option value="<%= nvgnRs.getString("pk_seq") %>"><%=nvgnRs.getString("ten") %></option>
								<%} }nvgnRs.close();} catch(SQLException ex){}%>
								<%} %>
						</select></TD>	
						
						<TD class="plainlabel"><%=Utility.GLanguage("NH??N VI??N B??N H??NG",session,jedis) %></TD>
						<TD class="plainlabel" colspan="1"><select name="ddkdId" id="ddkdId" style="width:202px">
								<option value="" selected >All</option>
								<% if(ddkdRs != null)
								{
									try
                        			{	
								   while(ddkdRs.next()){
								   if(ddkdRs.getString("pk_seq").equals(obj.getDdkdId() ))
								   { %>
								   <option value="<%= ddkdRs.getString("pk_seq") %>" selected><%=ddkdRs.getString("ten") %></option>
								   <%}else { %>
								   <option value="<%= ddkdRs.getString("pk_seq") %>"><%=ddkdRs.getString("ten") %></option>
								<%} }ddkdRs.close();} catch(SQLException ex){}%>
								<%} %>
						</select></TD>	
									
					</TR>
                    
                   <%} %>
                   
					<%if(obj.gettype().equals("0")) {%>                
	                    <TR>									
								<TD class="plainlabel">Kh??ch h??ng</TD>
								<TD class="plainlabel" colspan="3">
									<select name="khId" id="khId" style="width:202px" onchange="submitform();"  >
										<option value="" selected >All</option>
										<% if(khRs != null)
										{
											try
		                        			{	
										   while(khRs.next()){
										   if(khRs.getString("pk_seq").equals(obj.getKhId()))
										   { %>
										   <option value="<%= khRs.getString("pk_seq") %>" selected><%=khRs.getString("ten") %></option>
										   <%}else { %>
										   <option value="<%= khRs.getString("pk_seq") %>"><%=khRs.getString("ten") %></option>
										<%} }khRs.close();} catch(SQLException ex){}%>
										<%} %>
								</select></TD>	
						</TR>
					<%} %>
					
					<%if(obj.gettype().equals("1")) {%>
						<TR>		
								<TD class="plainlabel" width="100px">?????i t??c</TD>
								<TD class="plainlabel" colspan="3">
									<select name="doitacId"  id="doitacId" class="select2" style="width: 200px" onchange="submitform();"  >
										<option value="">All</option>
										<%
		                       		if(nppRs != null)
		                       		{
		                       			try
		                       			{
		                       			while(nppRs.next())
		                       			{  
		                       			if( nppRs.getString("pk_seq").equals(obj.getDoiTacId())){ %>
										<option value="<%= nppRs.getString("pk_seq") %>"
											selected="selected"><%= nppRs.getString("ten") %></option>
										<%}else { %>
										<option value="<%= nppRs.getString("pk_seq") %>"><%= nppRs.getString("ten") %></option>
										<% } } nppRs.close();} catch(Exception ex){}
		                       		}
		                       	%>
								</select></TD>
						</TR>  
					<%} %>
					
					
					<TR>		
								  <TD class="plainlabel">L???y theo</TD>
								      		<TD class="plainlabel" >
								      		  <SELECT name="nguon" id="nguon" style="width: 230px;" onchange="submitform();">
								      		  <% if(obj.getTheoChungloai().equals("1") ){%>
								      			<option value="0" >Kh??ng theo ngu???n</option>
								        		<option value="1" selected>Theo ngu???n</option>
								        	<%} else { %>
								        		<option value="0" selected >Kh??ng theo ngu???n</option>
								        		<option value="1" >Theo ngu???n</option>
								        	<%} %>	
								          </SELECT></TD>
							
							<% if(obj.getTheoChungloai().equals("1")){ %>
								<TD class="plainlabel" width="100px">Ch???ng lo???i</TD>
								<TD class="plainlabel" >
									<select name="chungloaiId"  id="chungloaiId" class="select2" style="width: 200px" onchange="submitform();"  >
										<option value="">All</option>
										<%
		                       		if(chungloaiRs != null)
		                       		{
		                       			try
		                       			{
		                       			while(chungloaiRs.next())
		                       			{  
		                       			if( chungloaiRs.getString("pk_seq").equals(obj.getChungloaiId())){ %>
										<option value="<%= chungloaiRs.getString("pk_seq") %>"
											selected="selected"><%= chungloaiRs.getString("ten") %></option>
										<%}else { %>
										<option value="<%= chungloaiRs.getString("pk_seq") %>"><%= chungloaiRs.getString("ten") %></option>
										<% } } chungloaiRs.close();} catch(Exception ex){}
		                       		}
		                       	%>
								</select></TD>
							<%}else { %>
								<TD class="plainlabel" width="100px" colspan="2"></TD>
							<%} %>	
						</TR>  
						
						
						
					
								
                    <TR>
						<TD class="plainlabel" colspan="4">
							<a class="button2" href="javascript:xuatExcel();"> 
								<img style="top: -4px;" src="../images/button.png" alt="">Xu???t b??o c??o </a>&nbsp;&nbsp;&nbsp;&nbsp;
					</TR> 
			
                </TABLE>                      
        </fieldset>                      
    	</div>
        
    </div>  
</div>
</form>
</body>
</HTML><%}%>