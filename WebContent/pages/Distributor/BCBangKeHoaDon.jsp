<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@page import="java.util.ArrayList"%>
<%@page import="java.sql.SQLException"%>
<%@ page  import = "geso.dms.center.util.*" %>
<%@ page  import = "geso.dms.distributor.beans.reports.IBCBangKeHoaDon" %>
<%@ page  import = "geso.dms.distributor.beans.reports.imp.BCBangKeHoaDon" %>
<%@ page  import = "java.sql.ResultSet" %>
<%@ page  import = "java.util.Hashtable" %>
<%@ page  import = "java.util.List" %>
<%@ page  import = "java.text.DecimalFormat" %>
<%@ page  import = "java.text.NumberFormat" %>

<%	IBCBangKeHoaDon bccn = (IBCBangKeHoaDon)session.getAttribute("bccn"); 
    ResultSet nvbhRs = bccn.getNvbhRs();
    ResultSet nvgnRs = bccn.getNvgnRs();
    ResultSet khRs = bccn.getKhRs();
    ResultSet dtRs = bccn.getDtRs();

    String userId = (String) session.getAttribute("userId");  
	String userTen = (String) session.getAttribute("userTen");  	
	String sum = (String) session.getAttribute("sum");
	String loi=(String)session.getAttribute("loi");
	Utility util = (Utility) session.getAttribute("util");
	if(!util.check(userId, userTen, sum)){
		response.sendRedirect(request.getContextPath() + "/redirect.jsp");
	}else{ %>


<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"> <% Utility Utilback = new Utility(); %>

<HTML>
<HEAD>
<TITLE><%= getServletContext().getInitParameter("TITLENAME") %></TITLE>
	<META http-equiv="Content-Type" content="text/html; charset=utf-8">
	<META http-equiv="Content-Style-Type" content="text/css">
	<LINK rel="stylesheet" type="text/css" media="print" href="../css/impression.css">
	<LINK rel="stylesheet" href="../css/main.css" type="text/css">
	<link type="text/css" rel="stylesheet" href="../css/mybutton.css">
	<LINK rel="stylesheet" type="text/css" href="../css/style.css" />

<link rel="stylesheet" type="text/css" href="../css/speechbubbles.css" />

<script type="text/javascript" src="../scripts/jquery.min.1.7.js"></script>
<script type="text/javascript" src="../scripts/dropdowncontent.js"></script>
<link href="../css/jquery-ui.css" rel="stylesheet" type="text/css" />
<script src="../scripts/ui/jquery.ui.core.js" type="text/javascript"></script>
<script src="../scripts/ui/jquery.ui.widget.js" type="text/javascript"></script>
<script src="../scripts/ui/jquery.ui.datepicker.js" type="text/javascript"></script>
<script type="text/javascript" src="../scripts/jquery.autocomplete.js"></script>
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
     $(document).ready(function() { $("select").select2(); });
     
</script>

<script type="text/javascript">
function submitform()
{
	document.forms['rpForm'].action.value="pdf";
	document.forms['rpForm'].submit();
}

function submitform1()
{	
	document.forms['rpForm'].action.value="excel";
	document.forms['rpForm'].submit();
}
</SCRIPT>

</HEAD>
<BODY leftmargin="0" bottommargin="0" topmargin="0" rightmargin="0">
<form name="rpForm" method="post" action="../../BCBangKeHoaDonSvl">
<input type="hidden" name="userId" value= <%= userId %> >
<input type="hidden" name="action" value='1'>

<TABLE width="100%" border="0" cellspacing="0" cellpadding="0"
	height="100%">
	
	<TR>
		<TD colspan="4" align='left' valign='top' bgcolor="#FFFFFF">
			<TABLE width="100%" cellpadding="0" cellspacing="1">		
				<TR>
					<TD width="100%" align="left">
					  <table width="100%" border="0" cellpadding="0" cellspacing="0">
						  <tr height="22">
						   <TD align="left" colspan="2" class="tbnavigation">&nbsp;Qu???n l?? c??ng n??? &gt; B??o c??o &gt; B???ng k?? h??a ????n </TD>
   
						   <TD colspan="2" align="right" class="tbnavigation"><%=Utility.GLanguage("Ch??o m???ng b???n",session,jedis) %>  <%=userTen %>&nbsp;</TD>
						  </tr>
 					  </table>					</TD>
				</TR>
				<tr>
					<TD align="left" colspan="4" class="legendtitle">
						<FIELDSET>
						<LEGEND class="legendtitle"><%=Utility.GLanguage("B??o l???i nh???p li???u",session,jedis) %></LEGEND>
	    				<textarea name="dataerror"  style="width: 100% ; color:#F00 ; font-weight:bold"  style="width: 100% ; color:#F00 ; font-weight:bold" readonly="readonly" rows="1"></textarea>
						</FIELDSET>
				   </TD>
				</tr>	
				<TR>
					<TD>
					<TABLE border="0" width="100%" cellpadding="0" cellspacing="0">
						<TR>
							<TD width="100%" align="center" >
							<FIELDSET>
							  <LEGEND class="legendtitle">Th???i gian xu???t b??o c??o</LEGEND>
							<TABLE  width="100%" cellpadding="6" cellspacing="0">
								<TR>
								  	<TD width="19%" class="plainlabel" ><%=Utility.GLanguage("T??? ng??y",session,jedis) %></TD>
								  	<TD class="plainlabel" >
										<TABLE cellpadding="0" cellspacing="0">
											<TR><TD>
												<input type="text" class="days" name="tuNgay" size="20" value = "<%=bccn.getTuNgay()%>" >
												</TD>
												<TD>		   										
												</TD>
                                    		</TR>
										</TABLE>									</TD>
								<TR>
									<TD class="plainlabel" ><%=Utility.GLanguage("?????n ng??y",session,jedis) %> </TD>
								  	<TD class="plainlabel" >
							  			<TABLE cellpadding="0" cellspacing="0">
							  				<TR>
							  					<TD>
													<input type="text"  class="days" name="denNgay" size="20" value = "<%=bccn.getDenNgay()%>" >												</TD>
												<TD>                                        		</TD>
                                     		</TR>
										</TABLE>									</TD>
								</TR>
							    <TR>
							     <TR>
                      <TD class="plainlabel">T??m NH??N VI??N B??N H??NG</TD>
                  		<TD class="plainlabel" colspan="3">                        
                  		<select name="nvbhId" id="nvbhId" style="width: 500px" multiple="multiple"  >
                            <option value=""> </option>
                        	<%
                        		if(nvbhRs != null)
                        		{
                        			try
                        			{
                        			while(nvbhRs.next())
                        			{  
                        			if( bccn.getNvbhIds().contains(nvbhRs.getString("pk_seq")) ){ %>
                        				<option value="<%= nvbhRs.getString("pk_seq") %>" selected="selected" ><%= nvbhRs.getString("Ten") %></option>
                        			<%}else { %>
                        				<option value="<%= nvbhRs.getString("pk_seq") %>" ><%= nvbhRs.getString("Ten") %></option>
                        		 <% } } nvbhRs.close();} catch(SQLException ex){}
                        		}
                        	%>
                        </select>
                     </TD>                     
                 </TR>   
                  <TR>
                      <TD class="plainlabel">T??m nh??n vi??n giao nh???n</TD>
                  		<TD class="plainlabel" colspan="3">                        
                  		<select name="nvgnId" id="nvgnId" style="width: 500px" multiple="multiple"  >
                            
                        	<%
                        		if(nvgnRs != null)
                        		{
  			    			try
                        			{
                        			while(nvgnRs.next())
                        			{  
                        			if( bccn.getNvgnIds().contains(nvgnRs.getString("pk_seq")) ){ %>
                        				<option value="<%= nvgnRs.getString("pk_seq") %>" selected="selected" ><%= nvgnRs.getString("Ten") %></option>
                        			<%}else { %>
                        				<option value="<%= nvgnRs.getString("pk_seq") %>" ><%= nvgnRs.getString("Ten") %></option>
                        		 <% } } nvgnRs.close();} catch(SQLException ex){}
                        		}
                        	%>
                        </select>
                     </TD>                     
                 </TR>
                
                 <TR>
                    <TD class="plainlabel">T??m ?????i t??c</TD>
                  		<TD class="plainlabel" colspan="3">                        
                  		<select name="dtId" style="width: 500px" multiple="multiple" >
                           <option value=""> </option>
                           
                        	<%
                        		if(dtRs != null)
                        		{
                        			try
                        			{
                        			while(dtRs.next())
                        			{  
                        			if( bccn.getDtIds().contains(dtRs.getString("pk_seq")) ){ %>
                        				<option value="<%= dtRs.getString("pk_seq") %>" selected="selected" ><%= dtRs.getString("nppTen") %></option>
                        			<%}else { %>
                        				<option value="<%= dtRs.getString("pk_seq") %>" ><%= dtRs.getString("nppTen") %></option>
                        		 <% } } dtRs.close();} catch(SQLException ex){}
                        		}
                        	%>
                        </select>
                     </TD>
                  </TR>
                  <TR>    
                       <TD class="plainlabel">T??m kh??ch h??ng</TD>
                  		<TD class="plainlabel" colspan="3">                        
                  		<select name="khId" style="width: 500px" multiple="multiple" >
                           <option value=""> </option>
                        	<%
                        		if(khRs != null)
                        		{
                        			try
                        			{
                        			while(khRs.next())
                        			{  
                        			if( bccn.getKhIds().contains(khRs.getString("pk_seq")) ){ %>
                        				<option value="<%= khRs.getString("pk_seq") %>" selected="selected" ><%= khRs.getString("khTen") %></option>
                        			<%}else { %>
                        				<option value="<%= khRs.getString("pk_seq") %>" ><%= khRs.getString("khTen") %></option>
                        		 <% } } khRs.close();} catch(SQLException ex){}
                        		}
                        	%>
                        </select>
                     </TD>
                    </TR>
                    <Tr>
									<TD  class="plainlabel">
										<a class="button2" href="javascript:submitform()" >
										<img style="top: -4px;" src="../images/button.png" alt="">In PDF</a>                                   
									</TD>
	
									<TD  class="plainlabel">
										<a class="button2" href="javascript:submitform1()" >
										<img style="top: -4px;" src="../images/button.png" alt=""><%=Utility.GLanguage("Xu???t Excel",session,jedis) %></a>									
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