<%@ page  import = "geso.dms.center.util.*" %>
<%@page import="java.sql.SQLException"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page  import = "java.util.Iterator" %>
<%@ page  import = "java.util.List" %>
<%@ page  import = "java.util.ArrayList" %>
<%@ page  import = "geso.dms.distributor.beans.buttoantonghop.*" %>
<%@ page  import = "java.sql.ResultSet" %>



<%@ page  import = "java.text.DecimalFormat" %>
<%@ page  import = "java.text.NumberFormat" %>

<% IErpButToanTongHopList btthList = (IErpButToanTongHopList)session.getAttribute("btthList"); %>
<% ResultSet rsButToan =btthList.getRsButToan(); %>
<% ResultSet nguoitaoRs = (ResultSet)btthList.getRsNguoiTao();%>
<% String userId = (String) session.getAttribute("userId");  %>
<% String userTen = (String) session.getAttribute("userTen");  
NumberFormat formater = new DecimalFormat("##,###,###");

%>
<%   
String sum = (String) session.getAttribute("sum");
	Utility util = (Utility) session.getAttribute("util");
	if(!util.check(userId, userTen, sum)){
		response.sendRedirect("/TraphacoERP/index.jsp");
	}else{	

		 int[] quyen = new  int[5];
		 
	
		 
		 quyen = util.Getquyen("ErpButToanTongHopSvl","&view=" + btthList.getView(),userId);
 %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd"> <% Utility Utilback = new Utility(); %>
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
	<title>Canfoco - Project</title>
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
	<script type="text/javascript" src="../scripts/dropdowncontent.js"></script>
   <script type="text/javascript" src="../scripts/ajax.js"></script>
   <script type="text/javascript" src="../scripts/jquery.autocomplete.js"></script> 
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
		document.forms["erpDmhForm"].action.value = "new";
	    document.forms["erpDmhForm"].submit();
	 }
	 function clearform()
	 {   
	    document.forms["erpDmhForm"].NgayButToan.value = "";
	    document.forms["erpDmhForm"].Sochungtu.value = "";
	    document.forms["erpDmhForm"].DenNgayButToan.value = "";
	    document.forms["erpDmhForm"].Taikhoanno.value = "";
	    document.forms["erpDmhForm"].Taikhoanco.value = "";
	    document.forms["erpDmhForm"].Sotien.value = "";
	    document.forms["erpDmhForm"].Nguoitao.value = "";
	    document.forms["erpDmhForm"].trangthai.value = "";
	    document.forms["erpDmhForm"].submit();
	 }
	 function thongbao()
	 {
		 var tt = document.forms["erpDmhForm"].msg.value;
	 	 if(tt.length>0)
	     	alert(document.forms["erpDmhForm"].msg.value);
	 }
	 
	 function duyetform(Id)
	 {
	 	 if(!confirm('B???n c?? ch???c ch???t b??t to??n n??y?')) 
	 	 {
	 		 return false ;
	 	 }
	 	 
	 	 document.forms['erpDmhForm'].chungtu.value = Id;
	 	 document.forms['erpDmhForm'].action.value= 'chot';
	 	 document.forms['erpDmhForm'].submit();
	 }
	 
	 function xoaform(Id)
	 {
	 	 if(!confirm('B???n c?? ch???c ch???t x??a b??t to??n n??y?')) 
	 	 {
	 		 return false ;
	 	 }
	 	 
	 	 document.forms['erpDmhForm'].chungtu.value = Id;
	 	 document.forms['erpDmhForm'].action.value= 'delete';
	 	 document.forms['erpDmhForm'].submit();
	 }
	 
	 function processing(id,chuoi)
	 {
 	    document.getElementById(id).innerHTML = "<a href='#'><img src='../images/waiting.gif' width = '20' height = '20' title='cho thuc hien..' border='0' longdesc='cho thuc hien..' style='border-style:outset'> Proc...</a>"; 		  
 	 	document.getElementById(id).href=chuoi;
 	 }
	</SCRIPT>
</head>
<body>
<form name="erpDmhForm" method="post" action="../../ErpButToanTongHopSvl">
<input type="hidden" name="userId" value="<%= userId %>" >
<input type="hidden" name="action" value="1" >
<input type="hidden" name="crrApprSplitting" value="<%= btthList.getCrrApprSplitting() %>" >
<input type="hidden" name="nxtApprSplitting" value="<%= btthList.getNxtApprSplitting() %>" >
<input type="hidden" name="chungtu" id="chungtu" >
<input type="hidden" name="view" id="view" value='<%= btthList.getView() %>' >
<input type="hidden" name="msg" value='<%= btthList.getMsg() %>'>
<script language="javascript" type="text/javascript">
    thongbao();
</script> 

<div id="main" style="width:100%; padding-left:2px">
	<div align="left" id="header" style="width:100%; float:none">
    	<div style="width:70%; padding:5px; float:left" class="tbnavigation">
        	&#160; Qu???n l?? k??? to??n &gt; B??t to??n t???ng h???p
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
                        <TD class="plainlabel">
                            <input type="text" class="days" 
                                   id="NgayButToan" name="NgayButToan" value="<%= btthList.getNgayButToan() %>" maxlength="10" onchange="submitform()" />
                        </TD>
                        
                        <TD class="plainlabel" width="15%"><%=Utility.GLanguage("?????n ng??y",session,jedis) %> </TD>
                        <TD class="plainlabel" colspan="3">
                            <input type="text" class="days" 
                                   id="DenNgayButToan" name="DenNgayButToan" value="<%= btthList.getDenNgayButToan() %>" maxlength="10" onchange="submitform()" />
                        </TD>
                        
                    </TR>
                    
                    <TR>
                        <TD class="plainlabel" width="15%">S??? ch???ng t??? </TD>
                        <TD class="plainlabel" colspan="6">
                            <input type="text" id="Sochungtu" name="Sochungtu" value="<%= btthList.getSoChungTu() %>" maxlength="10" onchange="submitform()" />
                        </TD>   
                    </TR>
                    
                     <TR>
                        <TD class="plainlabel" width="15%">T??i kho???n n??? </TD>
                        <TD class="plainlabel" >
                            <input type="text"
                                   id="Taikhoanno" name="Taikhoanno" value="<%= btthList.getTaiKhoanNo() %>" maxlength="10" onchange="submitform()" />
                        </TD>  
                        
                        
                        <TD class="plainlabel" width="15%">T??i kho???n c?? </TD>
                        <TD class="plainlabel" colspan="3">
                            <input type="text"
                                   id="Taikhoanco" name="Taikhoanco" value="<%= btthList.getTaiKhoanCo() %>" maxlength="10" onchange="submitform()" />
                        </TD>
                         
                    </TR>
                    
                      <TR>
                         <TD class="plainlabel" width="15%">S??? ti???n</TD>
                        <TD class="plainlabel" >
                            <input type="text" 
                                   id="Sotien" name="Sotien" value="<%= btthList.getSoTien() %>" maxlength="10" onchange="submitform()" />
                        </TD> 
                        
                        
                        <TD class="plainlabel" width="15%"><%=Utility.GLanguage("Ng?????i t???o",session,jedis) %> </TD>
                        <TD class="plainlabel" valign="middle" >
                            <select name="Nguoitao" onchange="submitform()" >
                            	<option value=""></option>
                            	<%
	                        		if(nguoitaoRs != null)
	                        		{
	                        			while(nguoitaoRs.next())
	                        			{  
	                        			if( nguoitaoRs.getString("pk_seq").equals(btthList.getNguoiTao())){ %>
	                        				<option value="<%= nguoitaoRs.getString("pk_seq") %>" selected="selected" ><%= nguoitaoRs.getString("ten") %></option>
	                        			<%}else { %>
	                        				<option value="<%= nguoitaoRs.getString("pk_seq") %>" ><%= nguoitaoRs.getString("ten") %></option>
	                        		 <% } } nguoitaoRs.close();
	                        		}
	                        	%>
                            </select>
                        </TD>    
                          <TD class="plainlabel" width="15%"></TD>
                        <TD class="plainlabel" >
                           <%--  <input type="text" 
                                   id="Sotien" name="Sotien" value="<%= btthList.getSoTien() %>" maxlength="10" onchange="submitform()" /> --%>
                        </TD>   
                         
                    </TR>
                    
                    <TR>
                     <TD class="plainlabel" valign="middle"><%=Utility.GLanguage("Tr???ng th??i",session,jedis) %> </TD>
	                        <TD class="plainlabel" valign="middle" colspan="5">
	                           <select name="trangthai" onChange="submitform();" style ="width:200px">
	                           		<option value=""> </option>
									<% if (btthList.getTrangthai().equals("1")){%>
									  	<option value="1" selected>???? ch???t</option>
									  	<option value="0">Ch??a ch???t</option>
									  	<option value="2">???? x??a</option>
									  
									<%}else if(btthList.getTrangthai().equals("0")) {%>
									 	<option value="0" selected>Ch??a ch???t</option>
									  	<option value="1" >???? ch???t</option>
									 	<option value="2" >???? x??a</option>
									  	
									<%}else if(btthList.getTrangthai().equals("2")){%>											
									 	<option value="2" selected>???? x??a</option>										  	
									  	<option value="0" >Ch??a ch???t</option>
									  	<option value="1" >???? ch???t</option>
									  	  	
									<%}else{%>
										<option value="0" >Ch??a ch???t</option>
									  	<option value="1" >???? ch???t</option>											
									  	<option value="2" >???? x??a</option>
									<%} %>
	                           </select>
	                        </TD>  
                    </TR>
                    <tr>
                        <td colspan="6" class="plainlabel">
                            <!-- <a class="button" href="javascript:submitform()">
                                <img style="top: -4px;" src="../images/Search30.png" alt=""><%=Utility.GLanguage("T??m ki???m",session,jedis) %> </a>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; -->
                            <a class="button2" href="javascript:clearform()">
                                <img style="top: -4px;" src="../images/button.png" alt=""><%=Utility.GLanguage("Nh???p l???i",session,jedis) %></a>&nbsp;&nbsp;&nbsp;&nbsp;
                        </td>
                    </tr>        					
                </TABLE>                      
        </fieldset>                      
    	</div>
        <div align="left" style="width:100%; float:none; clear:left; font-size:0.7em">
        	<fieldset>
            	<legend><span class="legendtitle">B??t to??n t???ng h???p </span>&nbsp;&nbsp;
            	
					<%if(quyen[0]!=0){ %>
					
                	<a class="button3" href="javascript:newform()">
                           <img style="top: -4px;" src="../images/New.png" alt=""><%=Utility.GLanguage("T???o m???i",session,jedis) %> </a>
                           <%} %>
                </legend>
            	<TABLE width="100%" border="0" cellspacing="1" cellpadding="4">
					<TR class="tbheader">
	                    <TH align="center">S??? ch???ng t???</TH>
	                    <TH align="center">Ng??y b??t to??n</TH>
	                    <TH align="center"><%=Utility.GLanguage("Di???n gi???i",session,jedis) %></TH>
	                    <TH align="center">T???ng gi?? tr???</TH>	                    
	                    <!-- <TH align="center"><%=Utility.GLanguage("Ng??y t???o",session,jedis) %></TH>
	                    <TH align="center"><%=Utility.GLanguage("Ng?????i t???o",session,jedis) %></TH> -->	                    
	                    <TH align="center"><%=Utility.GLanguage("Ng??y s???a",session,jedis) %></TH>
	                    <TH align="center"><%=Utility.GLanguage("Ng?????i s???a",session,jedis) %></TH>
	                    <TH align="center"><%=Utility.GLanguage("Tr???ng th??i",session,jedis) %></TH>
	                    <TH align="center" ><%=Utility.GLanguage("T??c v???",session,jedis) %></TH>
	                </TR>
					<%
								int m = 0;
	                    
	                    		if(rsButToan!=null)
	                    		{
	                    			while(rsButToan.next())
	                    			{
	                    				if((m % 2 ) == 0) {%>
		                         		<TR class="tbdarkrow">
					                     <%}else{ %>
					                          	<TR class="tblightrow">
					                     <%} %>
					                     
					                     <% String trangthai = "";
									   		String tt = rsButToan.getString("TRANGTHAI"); %>
									   	<TD align="center" <%if(Integer.parseInt(tt) >= 2 ) {%> style="color: red" <%} %> ><%= rsButToan.getString("PK_SEQ") %></TD>
									   	<TD align="center" <%if(Integer.parseInt(tt) >= 2 ) {%> style="color: red" <%} %> ><%= rsButToan.getString("NgayButToan") %></TD>
									    <TD align="left" <%if(Integer.parseInt(tt) >= 2 ) {%> style="color: red" <%} %> ><%= rsButToan.getString("DIENGIAI")  %></TD>
									    <TD align="center" <%if(Integer.parseInt(tt) >= 2 ) {%> style="color: red" <%} %> ><%= formater.format(Double.parseDouble(rsButToan.getString("TONGTIEN") ))%></TD>
					                    <TD align="center" <%if(Integer.parseInt(tt) >= 2 ) {%> style="color: red" <%} %> ><%= rsButToan.getString("NGAYSUA") %></TD> 
					                    <TD align="center" <%if(Integer.parseInt(tt) >= 2 ) {%> style="color: red" <%} %> ><%= rsButToan.getString("NGUOISUA") %></TD> 
					                    
					                    <TD align="center" <%if(Integer.parseInt(tt) >= 2 ) {%> style="color: red" <%} %> >
										<%
											
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
										
										<TD align="center" > 
				                    <% if(tt.equals("0")){ %>
				                    <%if(quyen[2]!=0){ %>
		                                <A href = "../../ErpButToanTongHopUpdateSvl?userId=<%=userId%>&update=<%= rsButToan.getString("PK_SEQ")  %>&view=<%=btthList.getView()%>"><IMG src="../images/Edit20.png" alt="C???p nh???t" title="C???p nh???t" border=0></A>&nbsp;
		                               <%} %>
		                               <%if(quyen[1]!=0){ %>
		                                <%-- <A href = "../../ErpButToanTongHopSvl?userId=<%=userId%>&delete=<%= ht.getId() %>"><img src="../images/Delete20.png" alt="X??a thanh to??n" title="X??a b??t to??n" width="20" height="20" border=0 onclick="if(!confirm('B???n c?? mu???n x??a b??t to??n n??y?')) return false;"></A> --%>
		                                
		                                 <A href="javascript:xoaform(<%= rsButToan.getString("PK_SEQ")  %>);" >
										 	<img  src="../images/Delete20.png" alt="X??a b??t to??n" width="20" height="20"  border='0' title="X??a b??t to??n"	 >
										</A>
		                                <%} %>

		                               <%if(quyen[4]!=0){ %>
		                                <%--  <A id='chotphieu<%= ht.getId()%>'
							       			href=""><img src="../images/Chot.png" alt="Ch???t b??t to??n"
							       			width="20" height="20" title="Ch???t b??t to??n"
							      			border="0" onclick="if(!confirm('B???n c?? ch???c ch???t b??t to??n n??y?')) {return false ;}else{ processing('<%="chotphieu"+ht.getId()%>' , '../../ErpButToanTongHopSvl?userId=<%=userId%>&chot=<%= ht.getId() %>');}"  >
										 </A> --%>
										  <A href="javascript:duyetform(<%= rsButToan.getString("PK_SEQ")  %>);" >
										 	<img  src="../images/Chot.png" alt="Ch???t b??t to??n" width="20" height="20"  border='0' title="Ch???t x??a kh??ch h??ng tr?? tr?????c"	 >
										</A>
		                               <%} %> 
		                              
																				
				                    <%}else if(tt.equals("1")){ %>
				                    	<A href = "../../ErpButToanTongHopUpdateSvl?userId=<%=userId%>&display=<%= rsButToan.getString("PK_SEQ")  %>&view=<%=btthList.getView()%>"><IMG src="../images/Display20.png" alt="Hi???n th???" title="Hi???n th???" border=0></A>&nbsp;
				                    	
				                    	<A href = "../../ErpInPhieuKeToanSvl?userId=<%=userId%>&id=<%= rsButToan.getString("PK_SEQ")  %>&view=<%=btthList.getView()%>"><IMG src="../images/Printer20.png" alt="In Phi???u K??? To??n" title="In Phi???u K??? To??n" border=0></A>&nbsp;
				                    		 
				                    <%} else {%>
				                    	<A href = "../../ErpButToanTongHopUpdateSvl?userId=<%=userId%>&display=<%= rsButToan.getString("PK_SEQ")  %>&view=<%=btthList.getView()%>"><IMG src="../images/Display20.png" alt="Hi???n th???" title="Hi???n th???" border=0></A>&nbsp;
				                    
				                    	<A href = "../../ErpInPhieuKeToanSvl?userId=<%=userId%>&id=<%= rsButToan.getString("PK_SEQ")  %>&view=<%=btthList.getView()%>"><IMG src="../images/Printer20.png" alt="In Phi???u K??? To??n" title="In Phi???u K??? To??n" border=0></A>&nbsp;
				                    	
				                    
				                    <%} %>
				                    </TD>
									
	                    			<%  m++;}
	                    			rsButToan.close();
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
	
	btthList.DBClose(); 
	session.setAttribute("obj",null);
if(rsButToan!=null){
	rsButToan.close();
}
}catch(Exception er){
	
}
	}
%>
</form>
</body>
</HTML>