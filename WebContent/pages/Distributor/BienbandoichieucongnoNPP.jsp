<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page  import = "java.util.Iterator" %>
<%@ page  import = "java.util.List" %>
<%@ page  import = "java.util.ArrayList" %>
<%@ page  import = "geso.dms.distributor.beans.bienbandoichieucongnoNPP.*" %>
<%@ page  import = "java.sql.ResultSet" %>
<%@ page  import = "geso.dms.center.util.*" %>
<%@page import="java.text.NumberFormat"%>
<%@page import="java.text.DecimalFormat"%>

<%
NumberFormat formatter = new DecimalFormat("#,###,###");
IBienbandoichieucongnoNPP obj = (IBienbandoichieucongnoNPP)session.getAttribute("obj"); %>
<% ResultSet khRs = obj.getKhRs(); %>
<% String type = (String) session.getAttribute("type");  %>
<% String userId = (String) session.getAttribute("userId");  %>
<% String userTen = (String) session.getAttribute("userTen");  
   NumberFormat formater = new DecimalFormat("##,###,###");%>
<% 
	String sum = (String) session.getAttribute("sum");
	Utility util = (Utility) session.getAttribute("util");
	if(!util.check(userId, userTen, sum)){
		response.sendRedirect("/Diageo/index.jsp");
	}else{
		int[] quyen = new  int[6];
		quyen = util.Getquyen("BienbandoichieucongnoNPP","",userId);

%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd"> <% Utility Utilback = new Utility(); %>
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
	<title>Diageo - Project</title>
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
		document.forms["ckParkForm"].action.value = "";
	    document.forms["ckParkForm"].submit();
	 }
	 
	 function inmau2()
	 {   
		var tungay= document.forms["ckParkForm"].tungay.value;
		var denngay= document.forms["ckParkForm"].denngay.value ;
		var khTen= document.forms["ckParkForm"].khTen.value ;
		if(!khTen =="")
		{ 
				if(tungay=="" && denngay == ""){
					alert(" Vui l??ng ch???n kh??ch h??ng, th???i gian b???t ?????u v?? th???i gian k???t th??c");
				}
				else{
					document.forms["ckParkForm"].action.value = "in2";
				    document.forms["ckParkForm"].submit();
				}
		}
		else
		{
		alert(" Vui l??ng ch???n kh??ch h??ng");
		}
		
		
		
	 }
	 
	 function inmau3()
	 {  
		 	var tungay= document.forms["ckParkForm"].tungay.value;
			var denngay= document.forms["ckParkForm"].denngay.value ;
			var khTen= document.forms["ckParkForm"].khTen.value ;
			if(!khTen =="")
			{ 
			
					if(tungay=="" && denngay == ""){
						alert(" Vui l??ng ch???n kh??ch h??ng, th???i gian b???t ?????u v?? th???i gian k???t th??c");
					}
					else{
						document.forms["ckParkForm"].action.value = "in3";
					    document.forms["ckParkForm"].submit();
					}
				
			}
			else
			{
			alert(" Vui l??ng ch???n kh??ch h??ng");
			}
			
	 }
	 
	 
	 function newform()
	 {   
		document.forms["ckParkForm"].action.value = "Tao moi";
	    document.forms["ckParkForm"].submit();
	 }
	 
	 
	 function clearform()
	 {   
		document.forms["ckParkForm"].action.value = "";
	    document.forms["ckParkForm"].tungay.value = "";
	    document.forms["ckParkForm"].denngay.value = "";
	    document.forms["ckParkForm"].khTen.value = "";	   
	    document.forms["ckParkForm"].submit();
	 }
	 function thongbao()
	 {
		 var tt = document.forms["ckParkForm"].msg.value;
	 	 if(tt.length>0)
	     	alert(document.forms["ckParkForm"].msg.value);
	 }
	</SCRIPT>
	
	<link href="../css/select2.css" rel="stylesheet" />
<script src="../scripts/select2.js"></script>
<script>
	$(document).ready(function()
	{
		$(".select2").select2();
	});
</script>
</head>
<body>
<form name="ckParkForm" method="post" action="../../BienbandoichieucongnoNPPSvl">
<input type="hidden" name="userId" value="<%= userId %>" >
<input type="hidden" name="action" value="1" >
<input type="hidden" name="msg" value='<%= obj.getMsg() %>'>
<input type="hidden" name="nppId" value='<%= obj.getnppId() %>'>
<script language="javascript" type="text/javascript">
    thongbao();
</script> 

<div id="main" style="width:100%; padding-left:2px">
	<div align="left" id="header" style="width:100%; float:none">
    	<div style="width:55%; padding:5px; float:left" class="tbnavigation">
        	&nbsp;Qu???n l?? c??ng n??? > B??o c??o  > Bi??n b???n ?????i chi???u c??ng n??? NPP
        </div>
        <div align="right" style="padding:5px" class="tbnavigation">
        	<%=Utility.GLanguage("Ch??o m???ng b???n",session,jedis) %>  <%= userTen %> &nbsp;
        </div>
        
       
    </div>
    
    <div align="left" style="width:100%; float:none; clear:left">
  		<fieldset>
    		<legend class="legendtitle"> Th??ng b??o</legend>
    		<textarea name="dataerror"  rows="1" readonly="readonly" style ="width:100%"><%= obj.getMsg() %></textarea>
		         <% obj.setMsg(""); %>
    	</fieldset>
  	</div>
  	
  	
  	<div id="cotent" style="width:100%; float:none">
    	<div align="left" style="width:100%; float:none; clear:left; font-size:0.7em">
        <fieldset style="margin-top:5px" >
            <legend class="legendtitle"> <%=Utility.GLanguage("Ti??u ch?? t??m ki???m",session,jedis) %></legend>
                <TABLE width="100%" cellpadding="6px" cellspacing="0px" style="margin-top: 5px " >
                	 <TR>
                        <TD class="plainlabel" width="100px"><%=Utility.GLanguage("T??? ng??y",session,jedis) %></TD>
                        <TD class="plainlabel" width="250px" >
                            <input type="text" class="days" name="tungay" value="<%= obj.getTungay() %>" maxlength="10" onchange="submitform();" />
                        </TD>
                    
                        <TD class="plainlabel" width="100px"><%=Utility.GLanguage("?????n ng??y",session,jedis) %></TD>
                        <TD class="plainlabel" colspan="4">
                            <input type="text" class="days" name="denngay" value="<%= obj.getDenngay() %>" maxlength="10" onchange="submitform();" />
                        </TD>
                    </TR>
                    
                     <TR>
                     
                     	<TD class="plainlabel" width="100px">Kh??ch h??ng</TD>
                        <TD class="plainlabel" colspan="3" >
                            <select name = "khTen" class="select2" style="width: 200px" onchange="submitform();" >
	                    		<option value=""> </option>
	                        	<%
	                        		if(khRs != null)
	                        		{
	                        			try
	                        			{
	                        			while(khRs.next())
	                        			{  
	                        			if( khRs.getString("pk_seq").equals(obj.getKhId())){ %>
	                        				<option value="<%= khRs.getString("pk_seq") %>" selected="selected" ><%= khRs.getString("ten") %></option>
	                        			<%}else { %>
	                        				<option value="<%= khRs.getString("pk_seq") %>" ><%= khRs.getString("ten") %></option>
	                        		 <% } } khRs.close();} catch(Exception ex){}
	                        		}
	                        	%>
	                    	</select>
                        </TD>
                     	
                      </TR>	

                    <tr>
                        <td colspan="6" class="plainlabel">
                            <a class="button" href="javascript:submitform()">
                                <img style="top: -4px;" src="../images/Search30.png" alt=""><%=Utility.GLanguage("T??m ki???m",session,jedis) %> </a>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                            <a class="button2" href="javascript:clearform()">
                                <img style="top: -4px;" src="../images/button.png" alt=""><%=Utility.GLanguage("Nh???p l???i",session,jedis) %></a>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                        
                           <a class="button" href="javascript:inmau2()">
                                <img style="top: -4px; " src="../images/Printer30.png"  alt="">Bi??n b???n ?????i chi???u c??ng n???</a>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                            <a class="button2" href="javascript:inmau3()">
                                <img style="top: -4px;" src="../images/Printer30.png" alt="">Bi??n b???n ?????i chi???u ki??m b?? tr??? c??ng n???</a>&nbsp;&nbsp;&nbsp;&nbsp;
                       
                        </td>
                    </tr> 
                    
                    					
                </TABLE>                      
        </fieldset>                      
    	</div>
        
    </div>  
</div>
</form>
</body>
</HTML><%}%>