<%@ page  import = "geso.dms.center.util.*" %>
<%@page import="java.sql.SQLException"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page  import = "java.util.Iterator" %>
<%@ page  import = "java.util.List" %>
<%@ page  import = "java.util.ArrayList" %>
<%@ page  import = "geso.dms.distributor.beans.uynhiemchi.*" %>
<%@ page  import = "java.sql.ResultSet" %>

<%@ page  import = "java.text.DecimalFormat" %>
<%@ page  import = "java.text.NumberFormat" %>


<% IErpUynhiemchiList obj = (IErpUynhiemchiList)session.getAttribute("obj"); %>
<% ResultSet nccList = (ResultSet)obj.getNccList(); %>
<% ResultSet htttList = (ResultSet)obj.getHtttList(); %>
<% ResultSet nvList = (ResultSet)obj.getNvList(); %>
<% ResultSet loaihoadonList = (ResultSet)obj.getLoaihoadonList(); %>
<% ResultSet tthdList = (ResultSet)obj.getTThoadonList(); %>

<% String userId = (String) session.getAttribute("userId");  %>
<% String userTen = (String) session.getAttribute("userTen");  %>


<%  
NumberFormat formatter = new DecimalFormat("#,###,###.##"); 
NumberFormat formatterVND = new DecimalFormat("#,###,###"); 

String sum = (String) session.getAttribute("sum");
Utility util = (Utility) session.getAttribute("util");
if(!util.check(userId, userTen, sum)){
	response.sendRedirect("/Phanam/index.jsp");
}else{	

	 int[] quyen = new  int[5];
	 quyen = util.Getquyen("ErpUynhiemchiSvl","",userId);

%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd"> <% Utility Utilback = new Utility(); %>
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
	<title>Phanam - Project</title>
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
	<script src="../scripts/ui/jquery.ui.datepicker.js"
		type="text/javascript"></script>

<script type="text/javascript" src="../scripts/dropdowncontent.js"></script>
<link href="../css/jquery-ui.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="../scripts/jquery.autocomplete.js"></script>
	
	
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
		document.forms["erpDmhForm"].action.value = "Tao moi";
	    document.forms["erpDmhForm"].submit();
	 }
	 function clearform()
	 {   
		document.forms["erpDmhForm"].nccId.value = "";
	    document.forms["erpDmhForm"].tungay.value = "";
	    document.forms["erpDmhForm"].denngay.value = "";
	    document.forms["erpDmhForm"].trangthai.value = "";
	    document.forms["erpDmhForm"].sohoadon.value = "";
	    document.forms["erpDmhForm"].sochungtu.value = "";
	    document.forms["erpDmhForm"].loaihoadon.value = "";
	    document.forms["erpDmhForm"].nvId.value = "";
	    document.forms["erpDmhForm"].sotientt.value = "";
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
	
<link href="../css/select2.css" rel="stylesheet" />
<script src="../scripts/select2.js"></script>
<script>
     $(document).ready(function() { $("select").select2(); });
     
</script>
	
	
</head>
<body>
<form name="erpDmhForm" method="post" action="../../ErpUynhiemchiSvl">
<input type="hidden" name="userId" value="<%= userId %>" >
<input type="hidden" name="action" value="1" >

<input type="hidden" name="msg" value='<%= obj.getmsg() %>'>
<script language="javascript" type="text/javascript">
    thongbao();
</script> 

<div id="main" style="width:100%; padding-left:2px">
	<div align="left" id="header" style="width:100%; float:none">
    	<div style="width:70%; padding:5px; float:left" class="tbnavigation">
        	Qu???n l?? c??ng n??? > C??ng n??? ph???i tr??? > ???y nhi???m chi
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
                        <TD class="plainlabel"  width="30%">
                            <input type="text" class="days" 
                                   id="tungay" name="tungay" value="<%= obj.getTungay() %>" maxlength="10" onchange="submitform()" />
                        </TD>
                        <TD class="plainlabel" width="15%"><%=Utility.GLanguage("?????n ng??y",session,jedis) %> </TD>
                        <TD class="plainlabel">
                            <input type="text" class="days" 
                                   id="denngay" name="denngay" value="<%= obj.getDenngay() %>" maxlength="10" onchange="submitform()" />
                        </TD>
                    </TR>
                     
                    <TR>
                        <TD class="plainlabel" >S??? h??a ????n</TD>
                        <TD class="plainlabel">
                            <input type="text"  
                                   id="sohoadon" name="sohoadon" value="<%= obj.getSohoadon() %>"  onchange="submitform()" />
                        </TD>
                         <TD class="plainlabel" >S??? ch???ng t??? </TD>
                        <TD class="plainlabel">
                            <input type="text"  
                                   id="sochungtu" name="sochungtu" value="<%= obj.getSochungtu() %>" maxlength="10" onchange="submitform()" />
                        </TD>
                    </TR>
                   <TR>
                        <%-- <TD class="plainlabel" valign="middle" >H??nh th???c thanh to??n </TD>
                        <TD class="plainlabel" valign="middle">
                            <select id= "httt" name="httt" onchange="submitform()"  style="width:200px">
                            	<option value=""></option>
                            	<%
	                        		if(htttList != null)
	                        		{
	                        			while(htttList.next())
	                        			{  
	                        			if( htttList.getString("pk_seq").equals(obj.getHtttId())){ %>
	                        				<option value="<%= htttList.getString("pk_seq") %>" selected="selected" ><%= htttList.getString("ten") %></option>
	                        			<%}else { %>
	                        				<option value="<%= htttList.getString("pk_seq") %>" ><%= htttList.getString("ten") %></option>
	                        		 <% } } htttList.close();
	                        		}
	                        	%>
                            </select>
                        </TD> --%>   
                                          
                    </TR> 
                    <TR>
                    	<TD class="plainlabel" valign="middle" ><%=Utility.GLanguage("Tr???ng th??i",session,jedis) %> </TD> 
                        <TD class="plainlabel" valign="middle">
							<select name = "trangthai" id= "trangthai" style = "width:200px" onchange="submitform()">
								<%if(obj.getTrangthai().equals("0")){ %>
								<option value=""></option>
								<option value="0" selected="selected">Ch??a duy???t</option>
								<option value="-1">???? duy???t</option>
								<option value="1">???? chi</option>
								<option value="2">???? x??a</option>
								<option value="3">???? h???y</option>
								<%}else if(obj.getTrangthai().equals("-1")){ %>
								<option value=""></option>
								<option value="0" >Ch??a duy???t</option>
								<option value="-1" selected="selected">???? duy???t</option>
								<option value="1">???? chi</option>
								<option value="2">???? x??a</option>
								<option value="3">???? h???y</option>
								<%} else if(obj.getTrangthai().equals("1")){ %>
								<option value=""></option>
								<option value="0" >Ch??a duy???t</option>
								<option value="-1" >???? duy???t</option>
								<option value="1" selected="selected">???? chi</option>
								<option value="2">???? x??a</option>
								<option value="3">???? h???y</option>
								<%} else if(obj.getTrangthai().equals("2")){ %>
								<option value=""></option>
								<option value="0" >Ch??a duy???t</option>
								<option value="-1" >???? duy???t</option>
								<option value="1">???? chi</option>
								<option value="2" selected="selected">???? x??a</option>
								<option value="3">???? h???y</option>								
								<%} else if(obj.getTrangthai().equals("3")){ %>
								<option value=""></option>
								<option value="0" >Ch??a duy???t</option>
								<option value="-1" >???? duy???t</option>
								<option value="1">???? chi</option>
								<option value="2">???? x??a</option>
								<option value="3" selected="selected">???? h???y</option>								
								<%} else { %>
								<option value="" selected="selected"></option>
								<option value="0" >Ch??a duy???t</option>
								<option value="-1" >???? duy???t</option>
								<option value="1">???? chi</option>
								<option value="2" >???? x??a</option>
								<option value="3">???? h???y</option>
								<%} %>
							</select>
                        </TD>  
                        
                        <TD class="plainlabel" valign="middle" >Nh?? cung c???p </TD>
                        <TD class="plainlabel" valign="middle" >
                            <select id = "nccId" name="nccId" onchange="submitform()"  style="width:200px">
                            	<option value=""></option>
                            	<%
	                        		if(nccList != null)
	                        		{
	                        			while(nccList.next())
	                        			{  
	                        			if( nccList.getString("pk_seq").equals(obj.getNccId())){ %>
	                        				<option value="<%= nccList.getString("pk_seq") %>" selected="selected" ><%= nccList.getString("nccTen") %></option>
	                        			<%}else { %>
	                        				<option value="<%= nccList.getString("pk_seq") %>" ><%= nccList.getString("nccTen") %></option>
	                        		 <% } } nccList.close();
	                        		}
	                        	%>
                            </select>
                        </TD>                        
                    </TR> 
                   
                   <TR>
                         
                        <TD class="plainlabel" valign="middle" >Lo???i h??a ????n </TD> 
                        <TD class="plainlabel" valign="middle">
							 <select id = "loaihoadon" name="loaihoadon" multiple="multiple" onchange="submitform()"  style="width:200px">
                            	<option value=""></option>
                            	<%
	                        		if(loaihoadonList != null)
	                        		{
	                        			while(loaihoadonList.next())
	                        			{  
	                        			if( obj.getLoaihoadon().contains(loaihoadonList.getString("pk_seq")) ){ %>
	                        				<option value="<%= loaihoadonList.getString("pk_seq") %>" selected="selected" ><%= loaihoadonList.getString("ten") %></option>
	                        			<%}else { %>
	                        				<option value="<%= loaihoadonList.getString("pk_seq") %>" ><%= loaihoadonList.getString("ten") %></option>
	                        		 <% } } loaihoadonList.close();
	                        		}
	                        	%>
                            </select>
                        </TD>  
                         <TD class="plainlabel" valign="middle" >Nh??n vi??n </TD>
                        <TD class="plainlabel" valign="middle" >
                            <select id = "nvId" name="nvId" onchange="submitform()"  style="width:200px">
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
                        </TD>                  
                    </TR> 
                    
                    <TR>
                         
                        <TD class="plainlabel" valign="middle" >S??? ti???n </TD> 
                        <TD class="plainlabel" valign="middle">
							 <input type="text" id="sotientt" name="sotientt" value="<%= obj.getSotien() %>"  onchange="submitform()" />
                        </TD>  
                         <TD class="plainlabel" valign="middle" ></TD>
                        <TD class="plainlabel" valign="middle" >                           
                        </TD>                  
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
            	<legend><span class="legendtitle"> ???y nhi???m chi </span>&nbsp;&nbsp;
            	<%//if(quyen[0]!=0){ %>
                	<a class="button3" href="javascript:newform()">
                           <img style="top: -4px;" src="../images/New.png" alt=""><%=Utility.GLanguage("T???o m???i",session,jedis) %> </a>
                           <%//} %>
                </legend>
            	<TABLE width="100%" border="0" cellspacing="1" cellpadding="4">
					<TR class="tbheader">
	                    <TH align="center" width="6%">S??? (Post No.)</TH>
	                    <TH align="center" width="6%">S??? ch???ng t???</TH>
						<TH align="center" width="8%">Ng??y ghi nh???n</TH>
						<TH align="center" width="15%">N???i dung (?????I T?????NG)</TH>
						<TH align="center" width="8%">S??? ti???n</TH>
						<TH align="center" width="6%"><%=Utility.GLanguage("Tr???ng th??i",session,jedis) %></TH>
						<TH align="center" width="6%"><%=Utility.GLanguage("Ng??y t???o",session,jedis) %></TH>
						<TH align="center" width="6%"><%=Utility.GLanguage("Ng?????i t???o",session,jedis) %></TH>
						<TH align="center" width="6%"><%=Utility.GLanguage("Ng??y s???a",session,jedis) %></TH>
						<TH align="center" width="6%"><%=Utility.GLanguage("Ng?????i s???a",session,jedis) %></TH>
						<TH align="center" width="6%"><%=Utility.GLanguage("T??c v???",session,jedis) %></TH>
	                </TR>
	                
	                	<% if(tthdList!= null)  { 
								int m = 0;
								String trangthai = "";
								while (tthdList.next())
								{
									String tt = tthdList.getString("trangthai");
									
									if((m % 2 ) == 0) {%>
										<TR class="tbdarkrow">
									<%}else{ %>									
										<TR class="tblightrow">
									<%} %>
									<TD align="center" <% if( Integer.parseInt(tt) >= 2){ %>style="color: red" <%} %>><%= tthdList.getString("machungtu") %></TD>	
									
									<TD align="center" <% if( Integer.parseInt(tt) >= 2){ %>style="color: red" <%} %>><%= tthdList.getString("SOCTTUDONG") %></TD>
									
									<TD align="center" <% if( Integer.parseInt(tt) >= 2){ %>style="color: red" <%} %>><%=  tthdList.getString("ngayghinhan")  %></TD>	
									
									<TD align="left" <% if( Integer.parseInt(tt) >= 2){ %>style="color: red" <%} %>><%= tthdList.getString("nccTen") %></TD>			
									
									<TD align="right" <% if( Integer.parseInt(tt) >= 2){ %>style="color: red" <%} %>><%= formatterVND.format(Double.parseDouble(tthdList.getString("sotientt")))%></TD>		
									
									<TD align="left" <% if( Integer.parseInt(tt) >= 2){ %>style="color: red" <%} %>>
									<%										
											if(tt.equals("0"))
											{
												trangthai = "Ch??a duy???t";
												if(tthdList.getString("iskttduyet").equals("1"))  trangthai = "???? duy???t";
											}
											else
											{
												if(tt.equals("1"))
												{
													trangthai = "???? chi";
												}
												else
												{
													if(tt.equals("2"))
														trangthai = "???? x??a";
													else
														trangthai = "???? h???y";
												}
											}
										%> <%= trangthai %></TD>
										
									<TD align="left" <% if( Integer.parseInt(tt) >= 2){ %>style="color: red" <%} %>><%= tthdList.getString("NGAYTAO") %></TD>
									<TD align="left" <% if( Integer.parseInt(tt) >= 2){ %>style="color: red" <%} %>><%= tthdList.getString("NGUOITAO")%></TD>
									<TD align="left" <% if( Integer.parseInt(tt) >= 2){ %>style="color: red" <%} %>><%= tthdList.getString("NGAYSUA") %></TD>
									<TD align="left" <% if( Integer.parseInt(tt) >= 2){ %>style="color: red" <%} %>><%= tthdList.getString("NGUOISUA") %></TD>
									<TD align="center">
										<% if(tt.equals("0")){ %> 
											<%if(quyen[2]!=0){ %> 
											<A	href="../../ErpUynhiemchiUpdateSvl?userId=<%=userId%>&update=<%= tthdList.getString("tthdId") %>">
												<IMG src="../images/Edit20.png" alt="C???p nh???t" title="C???p nh???t" border=0>
											</A>&nbsp; 
										<%} %>
										
										<%if(quyen[4]!=0){ if( tthdList.getString("iskttduyet").equals("1")) { %>
										<A id='chotphieu<%=tthdList.getString("tthdId")%>' href="">
										<img src="../images/Chot.png" alt="Ch???t thanh to??n" width="20" height="20" title="Ch???t thanh to??n" border="0"
											onclick="if(!confirm('B???n c?? ch???c ch???t phi???u thanh to??n n??y?')) {return false ;}else{ processing('<%="chotphieu"+tthdList.getString("tthdId")%>' , '../../ErpUynhiemchiSvl?userId=<%=userId%>&chot=<%= tthdList.getString("tthdId") %>');}">
										</A> <%}%> <% } %> 
										
										<%if(quyen[1]!=0){ %> 
										<A href="../../ErpUynhiemchiSvl?userId=<%=userId%>&delete=<%= tthdList.getString("tthdId") %>">
										<img src="../images/Delete20.png" alt="X??a thanh to??n" title="X??a thanh to??n" width="20" height="20" border=0
											onclick="if(!confirm('B???n c?? mu???n x??a phi???u thanh to??n n??y?')) return false;">
										</A> <% }%> 
										
										<%if(quyen[3]!=0){ %> 
										<A href="../../ErpUynhiemchiUpdateSvl?userId=<%=userId%>&display=<%= tthdList.getString("tthdId") %>">
										<IMG src="../images/Display20.png" alt="Hi???n th???" title="Hi???n th???" border=0>
										</A>&nbsp; 
										<%} %>
									
								<%}else{ %> 
									<%if(quyen[3]!=0){ %> 									
									<A href="../../ErpUynhiemchiUpdateSvl?userId=<%=userId%>&display=<%= tthdList.getString("tthdId") %>">
									<IMG src="../images/Display20.png" alt="Hi???n th???" title="Hi???n th???" border=0>
									</A>&nbsp; 
									<%} %>
														
								<% } %>
								</TD>
							<% m++; } 
							}%>
							
	                
						 
				</TABLE>
            </fieldset>
        </div>
    </div>  
</div>

<% 
try{
	if(nccList!=null){
		nccList.close();
	}
	if(htttList!=null){
		htttList.close();
	}
	if(nvList!=null){
		nvList.close();
	}
	if(loaihoadonList!=null){
		loaihoadonList.close();
	}
	if(tthdList!=null){
		tthdList.close();
	}

	obj.DBclose();
	session.setAttribute("ddmhBean",null);
}catch(Exception er){
	
}
}%>
</form>
</body>
</HTML>