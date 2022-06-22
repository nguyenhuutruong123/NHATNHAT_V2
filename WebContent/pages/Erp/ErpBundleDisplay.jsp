<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="geso.salesup.erp.beans.lenhsanxuat.imp.*" %>
<%@ page import="geso.salesup.erp.beans.lenhsanxuat.*" %>
<%@ page import="java.util.Calendar" %>
<%@ page import="java.util.Date" %>
<%@ page import="java.util.List" %>
<%@ page  import = "java.util.Iterator" %>
<%@ page  import = "java.sql.ResultSet" %>
<%@ page  import = "geso.salesup.center.util.*" %>
<%@ page import="java.sql.SQLException" %>

<%	String userId = (String) session.getAttribute("userId");  
	String userTen = (String) session.getAttribute("userTen");  	
	String sum = (String) session.getAttribute("sum");
	Utility util = (Utility) session.getAttribute("util");
	if(!util.check(userId, userTen, sum)){
		response.sendRedirect("/SalesUp/index.jsp");
	}else{ %>

<%
 	IErpBundle obj =(IErpBundle)session.getAttribute("csxBean");
	ResultSet khoRs = obj.getKhoTTRs();
	ResultSet spRs = obj.getSpRs();
	
	String[] spIds = obj.getSpIds();
	String[] spMaIds = obj.getSpMaIds();
	String[] spTenIds = obj.getSpTenIds();
	String[] soluongIds = obj.getSoluongIds();
	String[] tonkhoIds = obj.getDongiaIds();
	String[] soloIds = obj.getSoloIds();
	String[] soloChonIds = obj.getSoloChonIds();
%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<HTML>
<HEAD>
<TITLE><%= getServletContext().getInitParameter("TITLENAME") %></TITLE>
<META http-equiv="Content-Type" content="text/html; charset=utf-8">
<META http-equiv="Content-Style-Type" content="text/css">

<LINK rel="stylesheet" href="../css/main.css" type="text/css">
<script type="text/javascript" language="JavaScript" src="../scripts/jquery.tools.min.js"></script>

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

<link href="../css/select2.css" rel="stylesheet" />
<script src="../scripts/select2.js"></script>
<script>
     /*  $(document).ready(function() { $("#sanpham").select2(); }); */
      $(document).ready(function() { 
    	     $("select").select2();
      });
  </script>
    
</HEAD>
<BODY leftmargin="0" bottommargin="0" topmargin="0" rightmargin="0">
<form name ="khtt" method="post" action="../../ErpBundleUpdateSvl" >
<input type="hidden" name="userId" value='<%= userId %>' >
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
							 <TD align="left" colspan="2" class="tbnavigation">&nbsp;Quản lý cung ứng > Sản xuất > Bundle > Hiển thị</TD> 
							 <TD colspan="2" align="right" class="tbnavigation"><%=Utility.GLanguage("Chào mừng bạn",session,jedis) %>  <%= userTen %></TD></tr>
						</table>
			 		</TD>
				</TR>
			</TABLE>
		<TABLE width="100%" border="0" cellpadding="0" cellspacing="1">
			<TR ><TD >
					<TABLE width="100%" border="0" cellpadding="0" cellspacing="0">
						<TR class = "tbdarkrow">
							<TD width="30" align="left"><A href="../../ErpBundleSvl?userId=<%= userId%>" ><img src="../images/Back30.png" alt="Quay ve"  title="Quay ve" border="1" longdesc="Quay ve" style="border-style:outset"></A></TD>
						    <TD width="2" align="left" ></TD>
							<TD >&nbsp; </TD>						
						</TR>
					</TABLE>
			</TD></TR>
		</TABLE>
			<TABLE width="100%" border="0" cellpadding="0"  cellspacing="1" >
			  	<tr>
					<TD align="left" colspan="4" class="legendtitle">
						<FIELDSET>
						<LEGEND class="legendtitle">Thông báo </LEGEND>
				
	    				<textarea name="dataerror"  style="width: 100% ; color:#F00 ; font-weight:bold"  style="width: 100% ; color:#F00 ; font-weight:bold" readonly="readonly" rows="1"><%= obj.getMsg() %></textarea>
						</FIELDSET>
				   </TD>
				</tr>			

				<TR>
				  <TD height="100%" width="100%">
						<FIELDSET >
						<LEGEND class="legendtitle" style="color:black">Thông tin Bundle</LEGEND>
						<TABLE border="0" width="100%" cellpadding="6" cellspacing="0">
                          
                          <tr>
							<TD class="plainlabel" width="100px"">Số chứng từ </TD>
							<TD class="plainlabel" >                               
			                     <input type="text" size="10" readonly="readonly" id="sochungtu" name="sochungtu" value="<%=obj.getId()%>" maxlength="10" />
			                </TD>
			                
			                <TD class="plainlabel"  width="80px" ></TD>   
	                        <TD class="plainlabel" ></TD>    
			                
						  </tr>
                          
                          <TR >
	                        <TD class="plainlabel" width="100px" >Ngày thực hiện </TD>
	                        <TD class="plainlabel" width="220px">
	                            <input type="text" class="days" 
	                                   id="ngaythuchien" name="ngaythuchien" value="<%= obj.getNgaythuchien() %>" onchange="submitform();"  />
	                        </TD>
	                    
                         	<TD class="plainlabel" valign="middle" width="80px" ><%=Utility.GLanguage("Diễn giải",session,jedis) %> </TD>   
	                        <TD class="plainlabel" valign="middle" >
	                        	<input type="text" name="diengiai" value="<%= obj.getDiengiai() %>"  > 
	                        </TD>          
		                  </TR> 
                          <TR>
                          		<TD class="plainlabel" valign="middle" >Kho trung tâm </TD>   
		                        <TD class="plainlabel" valign="middle" colspan="3" >
		                            <select name="khoId" style="width: 200px" onchange="submitform();"  >
		                            	<option value="" ></option>
		                            	<% if(khoRs != null) { 
		                            		while(khoRs.next())
		                            		{
		                            			if(khoRs.getString("pk_seq").equals(obj.getKhoTTId())) 
		                            			{
		                            				%>
		                            				<option value="<%= khoRs.getString("pk_seq") %>" selected="selected" ><%= khoRs.getString("ten") %></option>
		                            			<% } else { %>
		                            				<option value="<%= khoRs.getString("pk_seq") %>" ><%= khoRs.getString("ten") %></option>
		                            			<%  } }
		                            		khoRs.close();
		                            	} %>
		                            </select>
		                        </TD>  
		                  </TR>
		                  
		                  <TR>
                          		<TD valign="top" colspan="4" >
                          			<TABLE width="100%" border="0" cellspacing="1" cellpadding="1">
                          				<TR class="tbheader">
						                    <TH align="center" width="70%"><%=Utility.GLanguage("Sản phẩm",session,jedis) %></TH>
						                    <TH align="center" width="10%">Số lô</TH>
						                    <TH align="center" width="10%">Tồn kho</TH>
						                    <TH align="center" width="10%">Số lượng</TH>
						                </TR>
						                
						                <%
						                int count = 0;
						                if(spIds != null) 
						                {
						                	for(int i = 0 ; i < spIds.length; i ++ ) {
						                	%>
						                
						                	<TR>
						                		<TD>
						                			<select name="nguyenlieu_masanpham" style="width: 100%" onchange="submitform();"  >
						                            	<option value="" ></option>
						                            	<% if(spRs != null) { 
						                            		spRs.beforeFirst();
						                            		while(spRs.next())
						                            		{
						                            			if(spRs.getString("pk_seq").equals(spIds[i])) 
						                            			{
						                            				%>
						                            				<option value="<%= spRs.getString("pk_seq") %>" selected="selected" ><%= spRs.getString("ten") %></option>
						                            			<% } else { %>
						                            				<option value="<%= spRs.getString("pk_seq") %>" ><%= spRs.getString("ten") %></option>
						                            			<%  } }
						                            	} %>
						                            </select>
						                		</TD>
						                		<TD>
						                			<select name="nguyenlieu_solo" style="width: 100%" >
						                				<% 
						                					//System.out.println("___SO LO CHON LAY DUOC: " + soloChonIds[i]); 
						                					
						                					if(soloChonIds != null && soloChonIds[i].trim().length() > 0 )
						                					{
						                						String[] soloArr = null;
						                						if( soloChonIds[i].indexOf("__") > 0 )
						                							soloArr = soloChonIds[i].split("__");
						                						else
						                							soloArr = new String[] { soloChonIds[i] };
						                						
						                						//System.out.println("----DO DAI SOLO: " + soloArr.length);
						                						
						                						for(int j = 0; j < soloArr.length; j++ )
						                						{
						                							if( soloArr[j].equals( soloIds[i] ))
						                							{ %>
						                								<option value="<%= soloArr[j] %>" selected="selected" ><%= soloArr[j] %></option>
						                							<% } else { %> 
						                								<option value="<%= soloArr[j] %>" ><%= soloArr[j] %></option>
						                							<% }
						                						}
						                					}
						                					
						                				%>
						                				<option value="" ></option>
						                			</select> 
						                		</TD>
						                		<TD>
						                			<input type="text" name="nguyenlieu_tonkho" value="<%= tonkhoIds[i] %>" style="width: 100%; text-align: right; " readonly="readonly" > 
						                		</TD>
						                		<TD>
						                			<input type="text" name="nguyenlieu_soluong" value="<%= soluongIds[i] %>" style="width:100%; text-align: right;"  > 
						                		</TD>
						                	</TR>
						                	
						                <%  count ++; }  } %>
						                
						                
						                <% for(int j = count; j < 5; j++ ) { %>
						                
						                	<TR>
						                		<TD>
						                			<select name="nguyenlieu_masanpham" style="width: 100%" onchange="submitform();"  >
						                            	<option value="" ></option>
						                            	<% if(spRs != null) { 
						                            		spRs.beforeFirst();
						                            		while(spRs.next())
						                            		{
						                            			 %>
						                            				<option value="<%= spRs.getString("pk_seq") %>" ><%= spRs.getString("ten") %></option>
						                            			<% 
						                            	} } %>
						                            </select>
						                		</TD>
						                		<TD>
						                			<select name="nguyenlieu_solo" style="width: 100%" >
						                				<option value="" > </option>
						                			</select> 
						                		</TD>
						                		<TD>
						                			<input type="text" name="nguyenlieu_tonkho" value="" style="width: 100%" readonly="readonly" > 
						                		</TD>
						                		<TD>
						                			<input type="text" name="nguyenlieu_soluong" value=""  style="width:100%; text-align: right;" > 
						                		</TD>
						                		
						                	</TR>
						                
						                <% } %>
						                
						                <TR>
						                	<TD colspan="6" style="padding: 5px;" >
						                		<span style="font-weight: bold; font-size: 1.1em;" >BUNDLE</span>
						                	</TD>
						                </TR>
						                
						                <TR>
					                		<TD>
					                			<select name="spId" style="width: 100%" onchange="submitform();"  >
					                            	<option value="" ></option>
					                            	<% if(spRs != null) { 
					                            		spRs.beforeFirst();
					                            		while(spRs.next())
					                            		{
					                            			if(spRs.getString("pk_seq").equals(obj.getSpId())) {
					                            			 %>
					                            				<option value="<%= spRs.getString("pk_seq") %>" selected="selected" ><%= spRs.getString("ten") %></option>
					                            			<% } else { %> 
					                            				<option value="<%= spRs.getString("pk_seq") %>" ><%= spRs.getString("ten") %></option>
					                            			<% }
					                            	} } %>
					                            </select>
					                		</TD>
					                		<TD>
					                			<input type="text" name="solo" value="<%= obj.getSoloId() %>" style="width: 100%" > 
					                		</TD>
					                		<TD>
					                			<input type="text" name="tonkho" value="" style="width: 100%" readonly="readonly" > 
					                		</TD>
					                		<TD>
					                			<input type="text" name="soluong" value="<%= obj.getSoluong() %>" style="width: 100%; text-align: right;"  > 
					                		</TD>
					                		
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
<%}%>
