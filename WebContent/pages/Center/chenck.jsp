<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page  import = "java.sql.ResultSet" %>
<%@ page  import = "geso.dms.center.util.*" %>
<%@ page  import = "geso.dms.center.beans.Chenck.Ichenck" %>


<%	String userId = (String) session.getAttribute("userId");  
	String userTen = (String) session.getAttribute("userTen");  	
	String sum = (String) session.getAttribute("sum");
	Utility util = (Utility) session.getAttribute("util");
	Ichenck obj = (Ichenck)session.getAttribute("obj");
	ResultSet rs=obj.getRsnppid();
	String url="";
	url = util.getUrl("Chenck","");
	if(!util.check(userId, userTen, sum)){
		response.sendRedirect(request.getContextPath() + "/redirect.jsp");
	}else{ %>



<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"> <% Utility Utilback = new Utility(); %>
<%@page import="java.sql.SQLException"%>
<HTML>
<HEAD>
<TITLE><%= getServletContext().getInitParameter("TITLENAME") %></TITLE>
<META http-equiv="Content-Type" content="text/html; charset=utf-8">

<META http-equiv="Content-Style-Type" content="text/css">

<LINK rel="stylesheet" href="../css/main.css" type="text/css">
<script type="text/javascript" src="../scripts/jquery.min.1.7.js"></script>
<link href="../css/jquery-ui.css" rel="stylesheet" type="text/css" />
<script src="../scripts/ui/jquery.ui.core.js" type="text/javascript"></script>
<script src="../scripts/ui/jquery.ui.widget.js" type="text/javascript"></script>
<link type="text/css" rel="stylesheet" href="../css/mybutton.css">
<script src="../scripts/ui/jquery.ui.datepicker.js"
	type="text/javascript"></script>
<SCRIPT language="JavaScript" type="text/javascript">
function submitform()
{
	document.forms['mksForm'].submit();
}
function load()
{
	document.forms['mksForm'].submit();
}
function chenck()
{
	document.forms['mksForm'].action.value= 'chenck';
	document.forms['mksForm'].submit();
}

</SCRIPT>

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
	$("#nppid").select2();
	
});

</script>
    
    
</HEAD>
<BODY leftmargin="0" bottommargin="0" topmargin="0" rightmargin="0">
<form name ="mksForm"  method="post" action="../../Chenck" >
<input type="hidden" name="action" value="0">
<input name="userId" type="hidden" value='<%=userId %>' size="30">
<input type="hidden" name="loai" value="1">
<TABLE width="100%" border="0" cellspacing="0" cellpadding="0"
	height="100%">
	<TR>
		<TD colspan="4" align='left' valign='top' bgcolor="#FFFFFF">
			<TABLE width="100%" border="0" cellpadding="0" cellspacing="1">
				<TR>
					<TD align="left" class="tbnavigation">
						<table width="100%" border="0" cellpadding="0" cellspacing="0">
						  <tr height="22">
							 <TD align="left" colspan="2" class="tbnavigation">&nbsp;<%=url %> </TD> 
							 <TD colspan="2" align="right" class="tbnavigation"><%=Utility.GLanguage("Chào mừng bạn",session,jedis) %>  <%=userTen %>&nbsp;  </TD></tr>
						</table>
			 		</TD>
				</TR>
			</TABLE>
			<TABLE width="100%" border="0" cellpadding="0"  cellspacing="1" >
			  	<tr>
					<TD align="left" colspan="4" class="legendtitle">
						<FIELDSET>
						<LEGEND class="legendtitle">Thông báo </LEGEND>
				
	    				<textarea name="dataerror"  style="width: 100% ; color:#F00 ; font-weight:bold"  style="width :100%" rows="1"><%=obj.getMsg() %></textarea>

						</FIELDSET>
				   </TD>
				</tr>			

				<TR>
				  <TD height="100%" width="100%" border = '0'>
						<FIELDSET >
						<LEGEND class="legendtitle" style="color:black">Chọn Chi nhánh / NPP</LEGEND>
						<TABLE border="0" width="100%" cellpadding="6" cellspacing="0">
						 
							<TR>  
							    <TD class="plainlabel">Nhà phân phối </TD>
							    <TD class="plainlabel">
							    <SELECT name="nppid" id="nppid" >
							    <% if(rs!=null){
							    	while (rs.next())
							    	{%>
							    		 <option class="select2" value="<%=rs.getString("pk_seq")%>" <%if(obj.getNppid().equals(rs.getString("pk_seq"))){ %> selected="selected" <%} %> ><%=rs.getString("ten") %></option>
							    	<%}
							
							    }%>
								   
								      
                        				</SELECT>			
                        		</TD>
                        	</TR>
                        	<TR>  
							    <TD class="plainlabel"><B></B>Mã đơn hàng </TD>
							    <TD class="plainlabel">
							    <input value="<%=obj.getDh() %>" name="dh" />		
                        		</TD>
                        	</TR>
                        	
							<%if(obj.getIschenckthang().equals("1")){ %>
							<TR>  
							    <TD class="plainlabel"><B></B>Tháng </TD>
							    <TD class="plainlabel">
							    <SELECT name="thang">
							    <option value="0"  ></option>
								    <%for (int i=1;i<13;i++){ %>
								    <option value="<%=i%>" <%if(obj.getThang().equals(i+"")) {%> selected="selected" <%} %>>Tháng <%=i %></option>
								      <%} %>
                        				</SELECT>			
                        		</TD>
                        	</TR>
                        	<%} %>
                        	<%
                        
                        	if(obj.getIschenckthang().equals("0")){ %>
                        	<TR>  
							    <TD class="plainlabel"><B></B>Quý </TD>
							    <TD class="plainlabel">
							    <SELECT name="quy" >
								  
								   <option value="0" ></option>
								   <option value="1" <%if(obj.getQuy().equals("1")){ %> selected="selected" <%} %>>Quý 1</option>
								   <option value="2"  <%if(obj.getQuy().equals("2")){ %> selected="selected" <%} %>>Quý 2</option>
								   <option value="3"  <%if(obj.getQuy().equals("3")){ %> selected="selected" <%} %>>Quý 3</option>
								   <option value="4"  <%if(obj.getQuy().equals("4")){ %> selected="selected" <%} %>>Quý 4</option>
								      
                        				</SELECT>			
                        		</TD>
                        	</TR>
                        	<%} %>
                        	
                        	<TR>  
							    <TD class="plainlabel"><B></B>Năm </TD>
							    <TD class="plainlabel">
							    <SELECT name="nam" >
								    <option value="" selected="selected"></option>
								    <%for (int i=2014;i<2030;i++){ %>
								    <option value="<%=i%>" <%if(obj.getNam().equals(i+"")){ %> selected="selected" <%} %>> <%=i %></option>
								      <%} %>
                        				</SELECT>			
                        		</TD>
                        	</TR>
                        	
                        	<TR>  
							    <TD class="plainlabel"><B></B>Nhóm BOGA </TD>
							    <TD class="plainlabel">
							    <input value="<%=obj.getBoga() %>" name="boga" />		
                        		</TD>
                        	</TR>
                        	<TR>  
							    <TD class="plainlabel"><B></B>Nhóm xanh </TD>
							    <TD class="plainlabel">
							    <input value="<%=obj.getXanh() %>" name="xanh" />		
                        		</TD>
                        	</TR>
                        	
                        	<%-- <TR>  
							    <TD class="plainlabel"><B></B>chèn dư nợ </TD>
							    <TD class="plainlabel">
							   		
							   		  <input value="1" name="isdn" type="checkbox" <%if(obj.getIschenduno().equals("1")){ %> checked="checked" <%} %>/> 	
                        		</TD>
                        	</TR> --%>
                        	
							<TR>  
							    <TD class="plainlabel"><B></B>Loại chèn </TD>
							    <TD class="plainlabel">
							   		 <input value="1" <%if(obj.getIschenckthang().equals("1")) {%> checked="checked" <%} %> name="isck" type="radio" onchange="load();" /> Chèn chiết khấu tháng	
							   		  <input value="0" <%if(obj.getIschenckthang().equals("0")) {%> checked="checked" <%} %> name="isck" type="radio"  onchange="load();" /> Chèn chiết khấu Quý	
                        		</TD>
                        	</TR>
					      
					      <TR>
					      		<TD class="legendtitle" colspan=3>
					      		<a class="button3" href="javascript:chenck()">
					      		 	<img style="top: -4px;" src="../images/New.png" alt="">Chèn chiết khấu 
					      		 </a>   
					      		</TD>
					      		
					      </TR>			  
						</TABLE>
						</FIELDSET>
					</td>
				</TR>
						
			</TABLE>
			
		</TD>
		</TR>
		</TABLE>
		</form>
		</BODY>
		</HTML>
<%} %>