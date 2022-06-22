<%@page import="geso.dms.center.beans.thongbaodoanhso.Ithongbaodoanhso"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page  import = "geso.dms.center.beans.thongbao.*" %>
<%@ page  import = "java.sql.ResultSet" %>
<%@ page  import = "java.util.Hashtable" %>
<% Ithongbaodoanhso kstdBean = (Ithongbaodoanhso)session.getAttribute("obj"); %>
<% String userId = (String) session.getAttribute("userId");  %>
<% String userTen = (String) session.getAttribute("userTen");  %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd"> <% Utility Utilback = new Utility(); %>
<html>
<head>
<TITLE><%= getServletContext().getInitParameter("TITLENAME") %></TITLE>
<META http-equiv="Content-Type" content="text/html; charset=utf-8">
<META http-equiv="Content-Style-Type" content="text/css">
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
	<TITLE><%= getServletContext().getInitParameter("TITLENAME") %></TITLE>
	<META http-equiv="Content-Type" content="text/html; charset=utf-8">
	<META http-equiv="Content-Style-Type" content="text/css">
    <LINK rel="stylesheet" type="text/css" media="print" href="../css/impression.css">
    <LINK rel="stylesheet" href="../css/main.css" type="text/css">
    <LINK rel="stylesheet" href="../css/datepicker.css" type="text/css">
    <script language="javascript" src="../scripts/datepicker.js"></script>
   	<link href="../css/jquery-ui.css" rel="stylesheet" type="text/css"/>
   	
  	<script type="text/javascript" src="../scripts/Timepicker/jquery.min.js"></script>
  	<script type="text/javascript" src="../scripts/Timepicker/jquery-ui.min.js"></script>
  	<script type="text/javascript" src="../scripts/phanTrang.js"></script>
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
   
  	<script type="text/javascript" src="..scripts/jquery-1.js"></script>
	<link type="text/css" rel="stylesheet" href="../css/mybutton.css">
    <script type="text/javascript">
        $(document).ready(function(){
            $(".button").hover(function(){
                $(".button img")
                .animate({top:"-10px"}, 200).animate({top:"-4px"}, 200) // first jump
                .animate({top:"-7px"}, 100).animate({top:"-4px"}, 100) // second jump
                .animate({top:"-6px"}, 100).animate({top:"-4px"}, 100); // the last jump
            });
        }); 
    </script>

<SCRIPT language="javascript" type="text/javascript">
	
	function submitform()
	{
		document.forms['kstdForm'].action.value='submitForm';
	   	document.forms['kstdForm'].submit();
	}
	
	function saveform()
	{	   
		
		 document.forms['kstdForm'].action.value='save';
	     document.forms['kstdForm'].submit();
	}
	
	function CheckNpp()
	{
		var npp = document.getElementsByName("nppIds");
		for(i = 0; i < npp.length; i++)
		{
			if(npp.item(i).checked)
				 return true;
		}
		return false;
		
	}
	function CheckAllNpp(values){
		var npp = document.getElementsByName("nppIds");
		for(i = 0; i < npp.length; i++)
		{
			npp.item(i).checked =values;
				
		}
	
	}
	
</SCRIPT>


</head>
<BODY leftmargin="0" bottommargin="0" topmargin="0" rightmargin="0">
<form name ="kstdForm" method="post" action="../../thongbaodoanhsoSvl" >
<input type="hidden" name="action" value='1'>
<input name="userId" type="hidden" value='<%=userId %>' size="30">
<div id="main" style="width:100%; padding-left:2px">
	<div align="left" id="header" style="width:100%; float:none">
    	<div style="width:55%; padding:5px; float:left" class="tbnavigation">
        	&nbsp;Dữ liệu nền &gt; Cơ bản &gt; Thiết lập khóa sổ
        </div>
        <div align="right" style="padding:5px" class="tbnavigation">
        	<%=Utility.GLanguage("Chào mừng bạn",session,jedis) %>  <%= userTen %> &nbsp;&nbsp;
        </div>
    </div>
  
  	<div align="left" id="button" style="width:100%; height:32px; padding:0px; float:none" class="tbdarkrow">
    	<A href= "../../KhoasotudongSvl?userId=<%=userId %>">		 		
	<img src="../images/Back30.png" alt="Quay ve"  title="Quay ve" width="30" height="30" border="1" longdesc="Quay ve" style="border-style:outset"></A>
        <A href="javascript:saveform()">
        	<IMG src="../images/Save30.png" title="Luu lai" alt="Luu lai" border = "1"  style="border-style:outset"></A>
    </div>
    
    <div align="left" style="width:100%; float:none; clear:left">
  		<fieldset>
    		<legend class="legendtitle"> <%=Utility.GLanguage("Báo lỗi nhập liệu",session,jedis) %></legend>
    		<textarea name="dataerror" style="width: 100%" readonly="readonly" rows="1" readonly="readonly"><%= kstdBean.getMsg() %></textarea>
		         <% kstdBean.setMsg(""); %>
    	</fieldset>
  	</div>
  	
  	<div align="left" style="width:100%; float:none; clear:left" >
    <fieldset>
    	<legend class="legendtitle"> Thiết lập thời gian và hạn gửi thông báo doanh số nhóm hàng sandoz </legend>       	
        <div style="float:none; width:100%" align="left">
             <table width="100%" cellspacing="1px" cellpadding="4px">
             	<tr class="tbheader">
             		<TH width="100%" align="center">NHÓM HÀNG SANDOZ</TH>
                </tr>
                
                
             </table>  
              <table width="100%" cellspacing="1px" cellpadding="4px" style="background: #C5E8CD !important">
             	
                <%-- <tr >
             		<Td  class="plainlabel" width="30%" align="center"><input name="giosandoz" type="text"  value=<%=kstdBean.getGiosandoz()%>></Td>
             		<Td  class="plainlabel" width="30%" align="center"><input name="phutsandoz" type="text"  value=<%=kstdBean.getPhutsandoz()%>> </Td>
             		<Td  class="plainlabel" width="40%" align="center"><input name="thoigiankhoasandoz" type="text"  value="<%=kstdBean.getNgaysandoz()%>" class="days"> </Td>
                </tr> --%>
                <tr >
             		<Td  class="plainlabel" width="20%" align="center"><input name="email" type="text"  value=""></Td>
             		<Td  class="plainlabel" width="20%" align="center"><input name="email" type="text"  value=""></Td>
             		<Td  class="plainlabel" width="20%" align="center"><input name="email" type="text"  value=""></Td>
             		<Td  class="plainlabel" width="20%" align="center"><input name="email" type="text"  value=""></Td>
             		<Td  class="plainlabel" width="20%" align="center"><input name="email" type="text"  value=""></Td>
                  </tr>
                
                <tr>
                 <% int i=0; if(kstdBean.getRsemail()!=null) while (kstdBean.getRsemail().next()){ 
                 
                 	if(i%5==0){i++;%>
             		</tr >
             		</tr>	 
             			<Td  class="plainlabel" width="20%" align="center"><input name="email" type="text"  value="<%=kstdBean.getRsemail().getString("email")%>"></Td>
   						<%}else{ i++; %>
   								<Td  class="plainlabel" width="20%" align="center"><input name="email" type="text"  value="<%=kstdBean.getRsemail().getString("email")%>"></Td>
   						<%} %>
             		
             		<%} %>
             		
                 </tr>
                  
             </table>  
             <hr>
             
         
            
         </div>      
    </fieldset>	
    </div>
  	
  	 
  	
    
</div>
</form>
</body>
</HTML>
<%
	
	if(kstdBean!=null){kstdBean.DBclose();
	kstdBean = null;}
	session.setAttribute("obj", null);
%>
