<%@ page  import = "geso.dms.center.util.*" %>
<%@page import="java.sql.SQLException"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page  import = "java.util.Iterator" %>
<%@ page  import = "java.util.List" %>
<%@ page  import = "java.util.ArrayList" %>
<%@ page  import = "geso.dms.distributor.beans.xoanoncc.*" %>
<%@ page  import = "java.sql.ResultSet" %>
<%@ page  import = "java.text.DecimalFormat" %>
<%@ page  import = "java.text.NumberFormat" %>


<% IErpXoaNoNCCList obj = (IErpXoaNoNCCList)session.getAttribute("obj"); %>
<% ResultSet nccList = (ResultSet)obj.getNccList(); %>
<% ResultSet nvList = (ResultSet)obj.getNvList(); %>
<% ResultSet htttList = (ResultSet)obj.getHtttList(); %>
<% ResultSet tthdList = (ResultSet)obj.getTThoadonList(); %>
<% String userId = (String) session.getAttribute("userId");  %>
<% String userTen = (String) session.getAttribute("userTen");  


NumberFormat formatter = new DecimalFormat("#,###,###.##"); 

String sum = (String) session.getAttribute("sum");
	Utility util = (Utility) session.getAttribute("util");
	if(!util.check(userId, userTen, sum)){
		response.sendRedirect("/SalesUp/index.jsp");
	}else{	

		 int[] quyen = new  int[5];
		 quyen = util.Getquyen("ErpXoaNoNCCSvl","",userId);
		 
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
   
   <script type="text/javascript" src="../scripts/jquery.min.js"></script>
<script type="text/javascript" src="../scripts/speechbubbles.js"></script>
<script type="text/javascript" src="../scripts/dropdowncontent.js"></script>
<script type="text/javascript" src="../scripts/jquery.autocomplete.js"></script>

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
     $(document).ready(function()
    		 { $(".select2").select2();  });
     
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
		$(document).ready(function(){
            $(".button2").hover(function(){
                $(".button2 img")
                .animate({top:"-10px"}, 200).animate({top:"-4px"}, 200) // first jump
                .animate({top:"-7px"}, 100).animate({top:"-4px"}, 100) // second jump
                .animate({top:"-6px"}, 100).animate({top:"-4px"}, 100); // the last jump
            });
        }); 
		$(document).ready(function(){
            $(".button3").hover(function(){
                $(".button3 img")
                .animate({top:"-10px"}, 200).animate({top:"-4px"}, 200) // first jump
                .animate({top:"-7px"}, 100).animate({top:"-4px"}, 100) // second jump
                .animate({top:"-6px"}, 100).animate({top:"-4px"}, 100); // the last jump
            });
        }); 
    </script>
    <SCRIPT language="javascript" type="text/javascript">
	 function confirmLogout()
	 {
	    if(confirm("B???n c?? mu???n ????ng xu???t?"))
	    {
			top.location.href = "home.jsp";
	    }
	    return
	 }
	 function submitform()
	 {   
	    document.forms["erpDmhForm"].submit();
	 }
	 function newform()
	 {   
		document.forms["erpDmhForm"].action.value = "Tao moi";
	    document.forms["erpDmhForm"].submit();
	 }
	 function clearform()
	 {   
	    document.forms["erpDmhForm"].nppId.value = "";
	    document.forms["erpDmhForm"].nvId.value = "";
	    document.forms["erpDmhForm"].maphieu.value = "";
	    document.forms["erpDmhForm"].ngaychungtu.value = "";
	    document.forms["erpDmhForm"].tungay.value = "";
	    document.forms["erpDmhForm"].denngay.value = "";
	   
	    document.forms["erpDmhForm"].submit();
	 }
	 function thongbao()
	 {
		 var tt = document.forms["erpDmhForm"].msg.value;
	 	 if(tt.length>0)
	     	alert(document.forms["erpDmhForm"].msg.value);
	 }
	 

	 function processing(id,chuoi)
	 {
 	    document.getElementById(id).innerHTML = "<a href='#'><img src='../images/waiting.gif' width = '20' height = '20' title='cho thuc hien..' border='0' longdesc='cho thuc hien..' style='border-style:outset'> Proc...</a>"; 		  
 	 	document.getElementById(id).href=chuoi;
 	 }
	</SCRIPT>
</head>
<body>
<form name="erpDmhForm" method="post" action="../../ErpXoaNoNCCSvl">
<input type="hidden" name="userId" value="<%= userId %>" >
<input type="hidden" name="action" value="1" >

<input type="hidden" name="msg" value='<%= obj.getmsg() %>'>
<script language="javascript" type="text/javascript">
    thongbao();
</script> 

<div id="main" style="width:100%; padding-left:2px">
	<div align="left" id="header" style="width:100%; float:none">
    	<div style="width:70%; padding:5px; float:left" class="tbnavigation">
        	Qu???n l?? c??ng n??? > C??ng n??? ph???i tr??? > X??a n??? nh?? cung c???p
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
                        <TD class="plainlabel" width="15%"><%=Utility.GLanguage("T??? ng??y",session,jedis) %> </TD>
                        <TD class="plainlabel" width="25%">
                            <input type="text" class="days" 
                                   id="tungay" name="tungay" value="<%= obj.getTungay() %>" maxlength="10" onchange="submitform()" />
                        </TD>
                   
                        <TD class="plainlabel" width="15%" ><%=Utility.GLanguage("?????n ng??y",session,jedis) %> </TD>
                        <TD class="plainlabel">
                            <input type="text" class="days" 
                                   id="denngay" name="denngay" value="<%= obj.getDenngay() %>" maxlength="10" onchange="submitform()" />
                        </TD>
                    </TR>
                    <TR>
                        <TD class="plainlabel" >M?? phi???u </TD>
                        <TD class="plainlabel" >
                            <input type="text"  
                                   id="maphieu" name="maphieu" value="<%= obj.getMaPhieu() %>" maxlength="10" onchange="submitform()" />
                        </TD>
                   
                        <TD class="plainlabel" >Ng??y ch???ng t??? </TD>
                        <TD class="plainlabel">
                            <input type="text" class="days" 
                                   id="ngaychungtu" name="ngaychungtu" value="<%= obj.getNgayChungTu() %>" maxlength="10" onchange="submitform()" />
                        </TD>
                    </TR>
                    <TR>
                        <TD class="plainlabel" valign="middle" >Nh?? cung c???p </TD>
                        <TD class="plainlabel" valign="middle" >
                            <select name="nppId" id="nppId" class="select2" style="width: 200px" onchange="submitform()">
                            	<option value=""></option>
                            	<%
	                        		if(nccList != null)
	                        		{
	                        			while(nccList.next())
	                        			{  
	                        			if( nccList.getString("pk_seq").equals(obj.getNccId())){ %>
	                        				<option value="<%= nccList.getString("pk_seq") %>" selected="selected" ><%= nccList.getString("nppTen") %></option>
	                        			<%}else { %>
	                        				<option value="<%= nccList.getString("pk_seq") %>" ><%= nccList.getString("nppTen") %></option>
	                        		 <% } } nccList.close();
	                        		}
	                        	%>
                            </select>
                        </TD>                        
                   
                    <TD class="plainlabel" valign="middle" ></TD>
                    <td class="plainlabel"></td>
                       <%--  <TD class="plainlabel" valign="middle" >Nh??n vi??n </TD>
                        <TD class="plainlabel" valign="middle" >
                            <select name="nvId" id="nvId" class="select2" style="width: 200px" onchange="submitform()">
                            	<option value=""></option>
                            	<%
	                        		if(nvList != null)
	                        		{
	                        			while(nvList.next())
	                        			{  
	                        			if( nvList.getString("pk_seq").equals(obj.getNvId())){ %>
	                        				<option value="<%= nvList.getString("pk_seq") %>" selected="selected" ><%= nvList.getString("nvTen") %></option>
	                        			<%}else { %>
	                        				<option value="<%= nvList.getString("pk_seq") %>" ><%= nvList.getString("nvTen") %></option>
	                        		 <% } } nvList.close();
	                        		}
	                        	%>
                            </select>
                        </TD>               --%>          
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
            	<legend><span class="legendtitle">X??a n??? nh?? cung c???p </span>&nbsp;&nbsp;
            	
					<%if(quyen[0]!=0){ %>
                	<a class="button3" href="javascript:newform()">
                           <img style="top: -4px;" src="../images/New.png" alt=""><%=Utility.GLanguage("T???o m???i",session,jedis) %> </a>
                           <%} %>
                </legend>
            	<TABLE width="100%" border="0" cellspacing="1" cellpadding="4">
					<TR class="tbheader">
	                    <TH align="center">M?? phi???u</TH>
	                    <TH align="center">Ng??y ch???ng t???</TH>
	                    <TH align="center">Nh?? cung c???p</TH>
	                    <TH align="center"><%=Utility.GLanguage("Tr???ng th??i",session,jedis) %></TH>
	                    <TH align="center"><%=Utility.GLanguage("Ng??y t???o",session,jedis) %></TH>
	                    <TH align="center"><%=Utility.GLanguage("Ng?????i t???o",session,jedis) %> </TH>
	                    <TH align="center"><%=Utility.GLanguage("Ng??y s???a",session,jedis) %> </TH>
	                    <TH align="center"><%=Utility.GLanguage("Ng?????i s???a",session,jedis) %> </TH>
	                    <TH align="center" ><%=Utility.GLanguage("T??c v???",session,jedis) %></TH>
	                </TR>
					<%
						
								int m = 0;
	                    
	                    		if(tthdList!=null)
	                    		{
	                    			while(tthdList.next())
	                    			{ if((m % 2 ) == 0) {%>
		                         		<TR class="tbdarkrow">
				                     <%}else{ %>
				                          	<TR class="tblightrow">
				                        <%} %>
	                    				
	                    			<TD align="center"><%= tthdList.getString("tthdId")%></TD>
									<TD align="center"><%= tthdList.getString("ngaychungtu") %></TD>
									<TD align="left"><%= tthdList.getString("tendoituong") %></TD>
									
									<TD align="left">
										<%
											String trangthai = "";
											String tt = tthdList.getString("trangthai");
											if(tt.equals("0"))
												trangthai = "Ch??a ch???t";
											else
											{
												if(tt.equals("1"))
												{
													trangthai = "???? ch???t";
												}
												else
												{
													if(tt.equals("2"))
														trangthai = "???? x??a";
													else
														trangthai = "???? h???y";
												}
											}
										%>
										<%= trangthai %>
									</TD>
									
									<TD align="left"><%= tthdList.getString("NGAYTAO") %></TD>
									<TD align="left"><%= tthdList.getString("NGUOITAO") %></TD>
									<TD align="left"><%= tthdList.getString("NGAYSUA") %></TD>
									<TD align="left"><%= tthdList.getString("NGUOISUA") %></TD>
									
									<TD align="center"> 
				                    <% if(tt.equals("0")){ %>
				                    
										<%if(quyen[1]!=0){ %>
		                                <A href = "../../ErpXoaNoNCCUpdateSvl?userId=<%=userId%>&update=<%= tthdList.getString("tthdId")%>"><IMG src="../images/Edit20.png" alt="C???p nh???t" title="C???p nh???t" border=0></A>&nbsp;
		                               <%} %>
		                               
										<%if(quyen[4]!=0){ %>
		                                 <A id='chotphieu<%= tthdList.getString("tthdId") %>'
							       			href=""><img
							       			src="../images/Chot.png" alt="Ch???t x??a n??? nh?? cung c???p"
							       			width="20" height="20" title="Ch???t x??a n??? nh?? cung c???p"
							      			border="0" onclick="if(!confirm('B???n c?? ch???c ch???t x??a n??? nh?? cung c???p n??y?')) {return false ;}else{ processing('<%="chotphieu"+tthdList.getString("tthdId")%>' , '../../ErpXoaNoNCCSvl?userId=<%=userId%>&chot=<%= tthdList.getString("tthdId") %>');}"  >
										 </A>
										 <%} %>
		                                
									<%if(quyen[1]!=0){ %>
		                                <A href = "../../ErpXoaNoNCCSvl?userId=<%=userId%>&delete=<%= tthdList.getString("tthdId") %>"><img src="../images/Delete20.png" alt="X??a thanh to??n" title="X??a thanh to??n" width="20" height="20" border=0 onclick="if(!confirm('B???n c?? mu???n x??a n??? nh?? cung c???p n??y?')) return false;"></A>
		                                <%} %>
	                																
				                    <%}else if(tt.equals("1")){ %>
				                    	<A href = "../../ErpXoaNoNCCUpdateSvl?userId=<%=userId%>&display=<%= tthdList.getString("tthdId") %>"><IMG src="../images/Display20.png" alt="Hi???n th???" title="Hi???n th???" border=0></A>&nbsp;
				                    	
				                    <%} else{%>
				                    	<A href = "../../ErpXoaNoNCCUpdateSvl?userId=<%=userId%>&display=<%= tthdList.getString("tthdId") %>"><IMG src="../images/Display20.png" alt="Hi???n th???" title="Hi???n th???" border=0></A>&nbsp;
				                    <%} %>
				                    </TD>
									
	                    		  <% m++; }
	                    		}
	                    	%>
	                    		
						
				</TABLE>
            </fieldset>
        </div>
    </div>  
</div>
<script type="text/javascript"> 
	  <%for(int k=0; k < m; k++) {%>
	   
		dropdowncontent.init("ktlist<%=k%>", "left-bottom", 250, "click");
	   
	  <%}%>
</script>

<% 


try{
	
	obj.DBclose(); 
	if(tthdList!=null){
	tthdList.close();
	}
	session.setAttribute("obj",null);
}catch(Exception er){
	
}
	}
%>
</form>
</body>
</HTML>