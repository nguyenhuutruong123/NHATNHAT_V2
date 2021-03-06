<%@page import="geso.dms.center.util.Utility"%>
<%@page import="java.util.ArrayList"%>
<%@page import="java.sql.SQLException"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page  import = "geso.dms.distributor.beans.huychungtuketoan.*" %>
<%@ page  import = "geso.dms.distributor.beans.huychungtuketoan.imp.*" %>
<%@ page  import = "java.sql.ResultSet" %>
<%@ page  import = "java.util.Hashtable" %>
<%@ page  import = "java.util.List" %>
<%@ page  import = "java.text.DecimalFormat" %>
<%@ page  import = "java.text.NumberFormat" %>

<% IErpHuychungtuketoan hctmhBean = (IErpHuychungtuketoan)session.getAttribute("hctmhBean"); %>
<% ResultSet soctRequest = hctmhBean.getSochungtuRequest(); %>
<% String userId = (String) session.getAttribute("userId");  %>
<% String userTen = (String) session.getAttribute("userTen");  
	String sum = (String) session.getAttribute("sum");
	Utility util = (Utility) session.getAttribute("util");
	
	NumberFormat formatter=new DecimalFormat("#,###,###.##"); 
	if(!util.check(userId, userTen, sum)){
		response.sendRedirect("/TraphacoERP/index.jsp");
	}else{
%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd"> <% Utility Utilback = new Utility(); %>
<html>
<head>
	<TITLE><%= getServletContext().getInitParameter("TITLENAME") %></TITLE>
	<META http-equiv="Content-Type" content="text/html; charset=utf-8">
	<META http-equiv="Content-Style-Type" content="text/css">
	<LINK rel="stylesheet" type="text/css" media="print" href="../css/impression.css">
	<LINK rel="stylesheet" href="../css/main.css" type="text/css">
	<link type="text/css" rel="stylesheet" href="../css/mybutton.css">
	<LINK rel="stylesheet" type="text/css" href="../css/style.css" />

<link rel="stylesheet" type="text/css" href="../css/speechbubbles.css" />

<script type="text/javascript" src="../scripts/jquery.min.js"></script>
<script type="text/javascript" src="../scripts/dropdowncontent.js"></script>
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


<script language="javascript" type="text/javascript">

	function keypress(e) //H??m d??ng d? ngan ngu?i d??ng nh?p c??c k?? t? kh??c k?? t? s? v??o TextBox
	{    
		var keypressed = null;
		if (window.event)
			keypressed = window.event.keyCode; //IE
		else
			keypressed = e.which; //NON-IE, Standard
		
		if (keypressed < 48 || keypressed > 57)
		{ 
			if (keypressed == 8 || keypressed == 127 || keypressed == 37 || keypressed == 39 || keypressed == 0 || keypressed == 46)
			{//Ph??m Delete v?? Ph??m Back
				return;
			}
			return false;
		}
	}
	
	 function saveform()
	 {	
		 var r = confirm("B???n c?? ch???c ch???n h???y ch???ng t??? n??y?")
		 if (r == false)
		 {
		   return;
		 }
		 
		 document.getElementById("btnSave").innerHTML = "<a href='#'><img src='../images/waiting.gif'  title='cho luu..' border='1' longdesc='cho luu..' style='border-style:outset'> Processing...</a>";
	 	 document.forms['hctmhForm'].action.value='save';
	     document.forms['hctmhForm'].submit();
	 }
	 
	 function submitform()
	 { 
		 document.forms['hctmhForm'].action.value='submit';
	     document.forms["hctmhForm"].submit();
	 }
	 
	 function checkHuyCt()
	 {
		 var sochungtuhuy = document.getElementsByName("sochungtuhuy");
		 for(i = 0; i < sochungtuhuy.length; i++)
		 {
			 if(sochungtuhuy.item(i).checked)
			 {
				 for(j = 0; j < i; j++)
				 {
					 if(sochungtuhuy.item(j).checked == false)
					 {
						 alert('B???n ph???i h???y c??c ch???ng t??? theo ????ng th??? t??? trong quy tr??nh');
						 return false;
					 }
				 }
			 }
		 } 
		 return true;
	 }
	 
	 function HuyChungTu(pos)
	 {
		 var sochungtuhuy = document.getElementsByName("sochungtuhuy");
		 if(sochungtuhuy.item(pos).checked)
		 {
			 for(i = 0; i < parseInt(pos); i++)
			 {
				 if(sochungtuhuy.item(i).checked == false)
				 {
					 alert('B???n ph???i h???y c??c ch???ng t??? theo ????ng th???u t??? trong quy tr??nh');
					 sochungtuhuy.item(pos).checked = false;
					 return false;
				 }
			 } 
		 }
		 return true;
	 }
	 
	 function ReadOnly(pos)
	 {
		 var sochungtuhuy = document.getElementsByName("sochungtuhuy");
		 if(sochungtuhuy.item(pos).checked == false)
		 {
			 sochungtuhuy.item(pos).checked = true;
		 }
	 }
	 
</script>
	
</head>
<BODY leftmargin="0" bottommargin="0" topmargin="0" rightmargin="0">
<form name="hctmhForm" method="post" action="../../ErpHuychungtuketoanUpdateSvl">
<input type="hidden" name="userId" value='<%= userId %>'>
<input type="hidden" name="action" value='1'>

<div id="main" style="width:100%; padding-left:2px">
	<div align="left" id="header" style="width:100%; float:none">
    	<div style="width:60%; padding:5px; float:left" class="tbnavigation">
        	Qu???n l?? k??? to??n > Hu??? ch???ng t??? > Hu??? ch???ng t??? > T???o m???i
        </div>
        <div align="right" style="padding:5px" class="tbnavigation">
        	<%=Utility.GLanguage("Ch??o m???ng b???n",session,jedis) %>  <%= userTen %> &nbsp;
        </div>
    </div>
  
  	<div align="left" id="button" style="width:100%; height:32px; padding:0px; float:none" class="tbdarkrow">
    	<A href= "../../ErpHuychungtuketoanSvl?userId=<%= userId %>" >
        	<img src="../images/Back30.png" alt="Quay ve"  title="Quay ve" border="1px" longdesc="Quay ve" style="border-style:outset"></A>
        <span id="btnSave">
	        <A href="javascript:saveform()" >
	        	<IMG src="../images/Save30.png" title="Luu lai" alt="Luu lai" border ="1px" style="border-style:outset"></A>
        </span>
    </div>
  	
  	<div align="left" style="width:100%%; float:none; clear:left">
  		<fieldset>
    		<legend class="legendtitle"> Th??ng b??o</legend>
    		<textarea name="dataerror"  rows="1" readonly="readonly" style ="width:100%;color: red; background-color: white;"><%= hctmhBean.getMsg() %></textarea>
		         <% hctmhBean.setMsg(""); %>
    	</fieldset>
  	</div>
  	
    <div align="left" style="width:100%; float:none; clear:left">
    <fieldset>
    	<legend class="legendtitle"> H???y ch???ng t??? </legend>
        	<div style="float:none; width:100%" align="left">
            <TABLE width="100%" cellpadding="4" cellspacing="0">	
            
            	<TR class="plainlabel" >
                     <TD class="plainlabel" valign="middle">Lo???i ch???ng t??? </TD>
                     <TD class="plainlabel" valign="middle">
                        <select name="loaict"  id="loaict" onChange="submitform();">
					<% if (hctmhBean.getLoaiCTId().equals("1")){	%>
					  	<option value="1" selected>??i???u chuy???n ti???n</option>
					<% } else {%>
						<option value="1" selected>??i???u chuy???n ti???n</option>
					<% } %>
                        </select>
                     </TD>  
				</TR>			
																	
                <TR>
                    <TD width="15%" class="plainlabel" valign="top"> S??? ch???ng t??? </TD>
                    <TD colspan="2" class="plainlabel" valign="top">
                    	<input type="text" id="sochungtu" name="sochungtu" value="<%= hctmhBean.getSochungtu() %>" onchange="submitform()"  />
                    	<input type="hidden" id="sochungtugoc" name="sochungtugoc" value="<%= hctmhBean.getSochungtugoc() %>"   />
                    </TD>
                </TR>
                 	 <TR>
	          			<TD class="plainlabel" valign="middle" >Ng??y ghi nh???n</TD>
						<TD class="plainlabel" valign="middle"  colspan="2">
							<input type="text" class="days" name="ngayghinhan"  id="ngayghinhan" value="<%= hctmhBean.getNgayghinhan() %>" readonly="readonly">
						</TD>
					</TR>						
                 <TR>
                    <TD width="15%" class="plainlabel" valign="top">&nbsp;</TD>
                    <TD colspan="2" class="plainlabel" valign="top">&nbsp;</TD>
                </TR>
                            
            </TABLE>          
            </div>
           
           <% 
           	if(soctRequest != null)
           	{ 
        	   int i = 1; %>
        	   
        	   <div align="left" style="width:100%; float:none; clear:none;" class="plainlabel">
	            <TABLE class="tabledetail" width="100%" border="0" cellpadding="1" cellspacing="1" >
	                <TR class="tbheader"> 
	                	<TH align="center" width="5%">STT</TH>
	                	<TH align="center" width="15%">&nbsp;S??? ch???ng t???</TH>
	                	<TH align="center" width="15%">&nbsp;Ng??y ch???ng t???</TH>
	                	<TH align="center" width="15%">&nbsp;<%=Utility.GLanguage("Tr???ng th??i",session,jedis) %></TH>
	               	 	<TH align="center" width="10%"><%=Utility.GLanguage("Ng??y t???o",session,jedis) %></TH>
	               	 	<TH align="center" width="15%">&nbsp;Lo???i ch???ng t???</TH>
	               	 	<TH align="center" width="10%">S??? ti???n thanh to??n</TH>
	               	 	<TH align="center" width="10%">Ch???n h???y</TH>
	                </TR>
        	   
        	<% while(soctRequest.next()){ %>
	                <TR>
	                	<TD align="center">
	                		<input type="text" name="stt" style="width: 100%; text-align: center;" value="<%= i %>" readonly="readonly" >
	                		<input type="hidden" name="thutu" style="width: 100%; text-align: center;" value="<%= soctRequest.getString("STT") %>" >
	                	</TD>
	                	<TD align="center">
	                		<input type="text" name="sochungtu" style="width: 100%" value="<%= soctRequest.getString("SOCHUNGTU") %>" readonly="readonly" >
	                	</TD>
	                	<TD align="center">
	                		<input type="text" name="ngaychungtu" style="width: 100%; text-align: center;" value="<%= soctRequest.getString("NGAYCHUNGTU") %>" readonly="readonly" >
	                	</TD>
	                	<TD align="center">
	                		<input type="text" name="trangthai" style="width: 100% ; text-align: center; " value="<%= soctRequest.getString("TRANGTHAI") %>" readonly="readonly" >
	                	</TD>
	                	<TD align="center">
	                		<input type="text" name="ngaytao" style="width: 100%; text-align: center;" value="<%= soctRequest.getString("NGAYTAO") %>" readonly="readonly" >
	                	</TD>
	                	<TD align="center">
	                		<input type="text" name="loaichungtu" style="width: 100%; text-align: center;" value="<%= soctRequest.getString("LOAICHUNGTU") %>" readonly="readonly" >
	                	</TD>
	                
	                	<TD align="right">
	                		<input type="text" name="sotientt" style="width: 100%; text-align: right;"  value="<%= formatter.format(Double.parseDouble(soctRequest.getString("SOTIENTT"))) %>" readonly="readonly"  >
	                	</TD>
	                	
	                	<TD align="center">
	                		<% if(hctmhBean.getSochungtu().equals(soctRequest.getString("SOCHUNGTU"))){ %>
	                			<input type="checkbox" name="sochungtuhuy" value="<%= soctRequest.getString("SOCHUNGTU") %>" onchange="ReadOnly(<%= i - 1 %>)" checked="checked" readonly="readonly" >
	                		<%} else { %>
	                			<input type="checkbox" name="sochungtuhuy" value="<%= soctRequest.getString("SOCHUNGTU") %>" onchange="HuyChungTu(<%= i - 1 %>)" >
	                		<%} %>
	                	</TD>
	                </TR>
           <% i++; } } %>
           
            </TABLE> 
	        </div> 
     </fieldset>	
    </div>
</div>
</form>
<%
	try{
		if(soctRequest!=null){
			soctRequest.close();
		}
	}catch(Exception err){
		err.printStackTrace();	
	}
	finally
	{
		if (hctmhBean != null)
			hctmhBean.DbClose();
		session.removeAttribute("hctmhBean");
	}
}
%>
</BODY>
</HTML>