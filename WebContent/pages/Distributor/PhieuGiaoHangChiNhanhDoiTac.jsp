<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page  import = "java.util.Iterator" %>
<%@ page  import = "java.util.List" %>
<%@ page  import = "java.util.ArrayList" %>
<%@ page  import = "geso.dms.distributor.beans.phieugiaohangchinhanhdoitac.*" %>
<%@ page  import = "java.sql.ResultSet" %>
<%@ page  import = "geso.dms.center.util.*" %>
<%@page import="java.text.NumberFormat"%>
<%@page import="java.text.DecimalFormat"%>
<% IPhieugiaohangchinhanhdoitacList obj = (IPhieugiaohangchinhanhdoitacList)session.getAttribute("obj"); %>
<% ResultSet nhapkhoRs =  obj.getDondathangRs(); %>
<% ResultSet khRs = obj.getKhRs(); %>
<% String userId = (String) session.getAttribute("userId");  %>
<% String userTen = (String) session.getAttribute("userTen");  %>
<% obj.setNextSplittings();
	String sum = (String) session.getAttribute("sum");
	Utility util = (Utility) session.getAttribute("util");
	if(!util.check(userId, userTen, sum)){
		response.sendRedirect(request.getContextPath() + "/redirect.jsp");
	}else{
		
		
		int[] quyen = new  int[6];
		quyen = util.Getquyen("PhieugiaohangchinhanhdoitacSvl","",userId);
		
	NumberFormat formatter = new DecimalFormat("#,###,###");
	 NumberFormat formater = new DecimalFormat("##,###,###");
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd"> <% Utility Utilback = new Utility(); %>
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
	<title><%= getServletContext().getInitParameter("TITLENAME") %></title>
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
	    document.forms["ckParkForm"].submit();
	 }
	 function newform()
	 {   
		document.forms["ckParkForm"].action.value = "Tao moi";
	    document.forms["ckParkForm"].submit();
	 }
	 function clearform()
	 {   
	    document.forms["ckParkForm"].tungay.value = "";
	    document.forms["ckParkForm"].denngay.value = "";
	    document.forms["ckParkForm"].trangthai.value = "";	   
	    document.forms["ckParkForm"].khTen.value = "";
	    document.forms["ckParkForm"].sohoadon.value = "";
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
<form name="ckParkForm" method="post" action="../../PhieugiaohangchinhanhdoitacSvl">
<input type="hidden" name="userId" value="<%= userId %>" >
<input type="hidden" name="action" value="1" >
<input type="hidden" name="msg" value='<%= obj.getMsg() %>'>
<input type="hidden" name="nppId" value="<%= obj.getNppId() %>" >
<script language="javascript" type="text/javascript">
    thongbao();
</script> 
<% obj.setNextSplittings(); %>
<input type="hidden"name="crrApprSplitting" value="<%= obj.getCrrApprSplitting() %>">
<input type="hidden" name="nxtApprSplitting" value="<%= obj.getNxtApprSplitting() %>">

<div id="main" style="width:100%; padding-left:2px">
	<div align="left" id="header" style="width:100%; float:none">
    	<div style="width:55%; padding:5px; float:left" class="tbnavigation">
        	&nbsp;Qu???n l?? b??n h??ng> B??n h??ng ETC / ?????i t??c > In phi???u giao h??ng
        </div>
        <div align="right" style="padding:5px" class="tbnavigation">
        	<%=Utility.GLanguage("Ch??o m???ng b???n",session,jedis) %>  <%= userTen %> &nbsp;
        </div>
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
                        <TD class="plainlabel">
                            <input type="text" class="days" name="denngay" value="<%= obj.getDenngay() %>" maxlength="10" onchange="submitform();" />
                        </TD>
                    </TR>
                    
                     <TR>
                     
                     	<TD class="plainlabel" width="100px">Kh??ch h??ng</TD>
                        <TD class="plainlabel">
                            <select name = "khTen" class="select2" style="width: 200px" onchange="submitform();" >
	                    		<option value=""> </option>
	                        	<%
	                        		if(khRs != null)
	                        		{
	                        			try
	                        			{
	                        			while(khRs.next())
	                        			{  
	                        			if( khRs.getString("pk_seq").equals(obj.getKhTen())){ %>
	                        				<option value="<%= khRs.getString("pk_seq") %>" selected="selected" ><%= khRs.getString("ten") %></option>
	                        			<%}else { %>
	                        				<option value="<%= khRs.getString("pk_seq") %>" ><%= khRs.getString("ten") %></option>
	                        		 <% } } khRs.close();
	                        		 } catch(Exception ex){}
	                        		}
	                        	%>
	                    	</select>
                        </TD>  
                        <TD class="plainlabel" width="100px">S??? h??a ????n </TD>
                        <TD class="plainlabel">
                         	<input type="text"  name="sohoadon" value="<%= obj.getSoHoaDon() %>"  onchange="submitform();" />
                        </TD>               
                    </TR>    
                    
                    <TR>
                    	    <TD width="130px" class="plainlabel"><%=Utility.GLanguage("Tr???ng th??i",session,jedis) %> </TD>
							<TD class="plainlabel">
								<select name="trangthai" style="width: 200px" onChange="submitform();"  class="select2">
									<% if (obj.getTrangthai().equals("1")){%>
									  	<option value="1" selected>???? in</option>
									  	<option value="0">Ch??a in </option>
									  	<option value=""></option>
									  
									<%}else if(obj.getTrangthai().equals("0")) {%>
									 	<option value="0" selected>Ch??a in</option>
									  	<option value="1" >???? in</option>
									  	<option value="" ></option>  	
									<%}else{%>
										<option value="" selected> </option>
										<option value="0" >Ch??a in</option>
									  	<option value="1" >???? in</option>	
									<%} %>
							          </select>
						</TD>
						
						<TD class="plainlabel" colspan="4"></TD>
                    </TR>
                     
                    <tr>
                        <td colspan="4" class="plainlabel">
                            <a class="button" href="javascript:submitform()">
                                <img style="top: -4px;" src="../images/Search30.png" alt=""><%=Utility.GLanguage("T??m ki???m",session,jedis) %> </a>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                            <a class="button2" href="javascript:clearform()">
                                <img style="top: -4px;" src="../images/button.png" alt=""><%=Utility.GLanguage("Nh???p l???i",session,jedis) %></a>&nbsp;&nbsp;&nbsp;&nbsp;
                        </td>
                    </tr>        					
                </TABLE>                      
        </fieldset>                      
    	</div>
        <div align="left" style="width:100%; float:none; clear:left; font-size:0.7em">
        	<fieldset>
            	<legend><span class="legendtitle">In phi???u giao h??ng ETC/?????i t??c </span>&nbsp;&nbsp;
            	 </legend>
            	<TABLE width="100%" border="0" cellspacing="1" cellpadding="4">
					<TR class="tbheader">
	                    <TH align="center" style="width: 7%" >M?? s???</TH>
	                    <TH align="center" style="width: 10%" >Ng??y xu???t H??</TH>
	                    <TH align="center" style="width: 10%" >S??? h??a ????n</TH>
	                    <TH align="center" style="width: 20%" >Kh??ch h??ng ETC/?????i t??c</TH>
	                    <TH align="center" style="width: 10%" >T???ng gi?? tr???</TH>
	                    <TH align="center" style="width: 10%" ><%=Utility.GLanguage("Tr???ng th??i",session,jedis) %></TH>
	                    <TH align="center" style="width: 5%" ><%=Utility.GLanguage("T??c v???",session,jedis) %></TH>
	                </TR>
					<%
                 		if(nhapkhoRs != null)
                 		{
                 			int m = 0;
                 			while(nhapkhoRs.next())
                 			{  		
                 				if((m % 2 ) == 0) {%>
		                         	<TR class='tbdarkrow'>
		                        <%}else{ %>
		                          	<TR class='tblightrow'>
		                        <%} %>
		                    <TD align="center"><%= nhapkhoRs.getString("PK_SEQ") %></TD>
		                    <TD align="center"><%= nhapkhoRs.getString("ngayxuatHD") %></TD>
		                    <TD align="center"><%= nhapkhoRs.getString("sohoadon")%></TD>	
		                                         
		                    <TD align="center"><%= nhapkhoRs.getString("khTEN") %></TD> 
		                    <TD align="center"><%= formater.format(nhapkhoRs.getDouble("tongtien")) %></TD> 
		                     <TD align="center">
		                    <% 
		                    	String trangthai = "";
		                    	String tt = nhapkhoRs.getString("trangthai");
		                    	if(tt.equals("0")){
		                    		trangthai = "Ch??a in";
		                    	}
		                    	else if(tt.equals("1")){
		                    		trangthai = "???? in";		                    		
		                    	}		                    	
		                    %>
		                    <%= trangthai %></TD>	
		                    <TD align="center">
		                    	<A href = "../../PhieugiaohangchinhanhdoitacPdfSvl?userId=<%=userId%>&pdf=<%= nhapkhoRs.getString("PK_SEQ") %>&nppId=<%= obj.getNppId()%>"><IMG src="../images/pdf.jpg" title="In phi???u giao h??ng ETC / ?????i t??c" width="20" height="20" border=0 ></A>		                    
		                    </TD>
		                </TR>
                     <% m++; }  }  else {%>
                     <TR class="tbheader"><TD colspan="8"></TD></TR>
                     <%} %>
                     
					<tr class="tbfooter">
							<TD align="center" valign="middle" colspan="13" class="tbfooter">
								<%if(obj.getNxtApprSplitting() >1) {%> 
									<img alt="Trang Dau" src="../images/first.gif" style="cursor: pointer;" onclick="View('ckParkForm', 1, 'view')"> &nbsp; <%}else {%>
									<img alt="Trang Dau" src="../images/first.gif">
								&nbsp; <%} %> <% if(obj.getNxtApprSplitting() > 1){ %> <img
								alt="Trang Truoc" src="../images/prev.gif"
								style="cursor: pointer;"
								onclick="Prev('ckParkForm', 'prev')"> &nbsp; <%}else{ %>
								<img alt="Trang Truoc" src="../images/prev_d.gif">
								&nbsp; <%} %> <%
						int[] listPage = obj.getNextSplittings();
						for(int i = 0; i < listPage.length; i++){
					%> <% 							
				
					if(listPage[i] == obj.getNxtApprSplitting()){ %> <a
								style="color: white;"><%= listPage[i] %>/ <%=obj.getTheLastSplitting() %></a>
								<%}else{ %> <a
								href="javascript:View('ckParkForm', <%= listPage[i] %>, 'view')"><%= listPage[i] %></a>
								<%} %> <input type="hidden" name="list"
								value="<%= listPage[i] %>" /> &nbsp; <%} %> <% if(obj.getNxtApprSplitting() < obj.getTheLastSplitting()){ %>
								&nbsp; <img alt="Trang Tiep" src="../images/next.gif"
								style="cursor: pointer;"
								onclick="Next('ckParkForm', 'next')"> &nbsp; <%}else{ %>
								&nbsp; <img alt="Trang Tiep" src="../images/next_d.gif">
								&nbsp; <%} %> <%if(obj.getNxtApprSplitting() == obj.getTheLastSplitting()) {%>
								<img alt="Trang Cuoi" src="../images/last.gif">
								&nbsp; <%}else{ %> <img alt="Trang Cuoi"
								src="../images/last.gif" style="cursor: pointer;"
								onclick="View('ckParkForm', -1, 'view')"> &nbsp; <%} %>
							</TD>
						</tr>
					 
				</TABLE>
				</legend>
				
            </fieldset>
            
        </div>
    </div>  
</div>
</form>
</body>
</HTML><%}%>