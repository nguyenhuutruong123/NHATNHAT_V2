<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page  import = "java.util.Iterator" %>
<%@ page  import = "java.util.List" %>
<%@ page  import = "java.util.ArrayList" %>
<%@ page  import = "geso.dms.center.beans.nhapkho.*" %>
<%@ page  import = "java.sql.ResultSet" %>
<%@ page  import = "geso.dms.center.util.*" %>
<% IErpNhapkhoList obj = (IErpNhapkhoList)session.getAttribute("obj"); %>
<% ResultSet nhapkhoRs =  obj.getNhapkhoRs(); %>
<% String userId = (String) session.getAttribute("userId");  %>
<% String userTen = (String) session.getAttribute("userTen");  %>
<% obj.setNextSplittings();
	String sum = (String) session.getAttribute("sum");
	Utility util = (Utility) session.getAttribute("util");
	if(!util.check(userId, userTen, sum)){
		response.sendRedirect(request.getContextPath() + "/redirect.jsp");
	}else{
		int[] quyen = new  int[6];
		quyen = util.Getquyen("ErpNhapkhoSvl","",userId);
%>
<%String url = util.getChuyenNguUrl("ErpNhapkhoSvl", "",session); %>
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
	    document.forms["ckParkForm"].submit();
	 }
	 function thongbao()
	 {
		 var tt = document.forms["ckParkForm"].msg.value;
	 	 if(tt.length>0)
	     	alert(document.forms["ckParkForm"].msg.value);
	 }
	</SCRIPT>
</head>
<body>
<form name="ckParkForm" method="post" action="../../ErpNhapkhoSvl">
<% redis.clients.jedis.Jedis jedis =  geso.dms.center.util.Utility.getJedis(); %><% geso.dms.center.util.Csrf csdr=new geso.dms.center.util.Csrf(request,response,true,false,true);%>
<input type="hidden" name="userId" value="<%= userId %>" >
<input type="hidden" name="action" value="1" >
<input type="hidden" name="msg" value='<%=Utility.GLanguage(obj.getMsg(),session,jedis) %>'>
<input type="hidden" name="currentPage" value="<%= obj.getCurrentPage() %>" >

 <input type="hidden" name="<%=csdr.get_tokenName() %>" value='<%=csdr.get_tokenValue() %>'>
<script language="javascript" type="text/javascript">
    thongbao();
</script> 

<div id="main" style="width:100%; padding-left:2px">
	<div align="left" id="header" style="width:100%; float:none">
    	<div style="width:55%; padding:5px; float:left" class="tbnavigation">
        	&nbsp;<%=url %>
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
                        <TD class="plainlabel" width="15%"><%=Utility.GLanguage("T??? ng??y",session,jedis) %></TD>
                        <TD class="plainlabel">
                            <input type="text" name="tungay" value="<%= obj.getTungayTao() %>" class="days" maxlength="10" onchange="submitform();" />
                        </TD>
                    </TR>								                          
                    <TR>
                        <TD class="plainlabel" width="15%"><%=Utility.GLanguage("?????n ng??y",session,jedis) %></TD>
                        <TD class="plainlabel">
                            <input type="text" class="days" 
                                   name="denngay" value="<%= obj.getDenngayTao() %>" class="days" maxlength="10" onchange="submitform();"  />
                        </TD>
                    </TR>
                    
                    
                     <TR>
                        <TD class="plainlabel" valign="middle"><%=Utility.GLanguage("Tr???ng th??i",session,jedis) %> </TD>
                        <TD class="plainlabel" valign="middle">
                           <select name="trangthai" onchange="submitform();" >
                           		<option value=""> </option>
								<% if (obj.getTrangthai().equals("1")){%>
								  	<option value="1" selected><%=Utility.GLanguage("???? ch???t",session,jedis) %></option>
								  	<option value="0"><%=Utility.GLanguage("Ch??a ch???t",session,jedis) %></option>
								  	<option value="2" ><%=Utility.GLanguage("???? h???y",session,jedis) %></option>
								  
								<%}else if(obj.getTrangthai().equals("0")) {%>
								 	<option value="0" selected><%=Utility.GLanguage("Ch??a ch???t",session,jedis) %></option>
								  	<option value="1" ><%=Utility.GLanguage("???? ch???t",session,jedis) %></option>
								  	<option value="2" ><%=Utility.GLanguage("???? h???y",session,jedis) %></option>
								<%}else if(obj.getTrangthai().equals("2")) {%>
							 	<option value="2" selected><%=Utility.GLanguage("???? h???y",session,jedis) %></option>
							  	<option value="0" ><%=Utility.GLanguage("Ch??a ch???t",session,jedis) %></option>
							  	<option value="1" ><%=Utility.GLanguage("???? ch???t",session,jedis) %></option>
								<%} else  {%>
							 	<option value="0"><%=Utility.GLanguage("Ch??a ch???t",session,jedis) %></option>
							  	<option value="1" ><%=Utility.GLanguage("???? ch???t",session,jedis) %></option>
							  	<option value="2" ><%=Utility.GLanguage("???? h???y",session,jedis) %></option>
							<%} %>
                           </select>
                        </TD>                        
                    </TR>    
                    <tr>
                        <td colspan="2" class="plainlabel">
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
            	<legend><span class="legendtitle"> <%=Utility.GLanguage("Nh???p kho",session,jedis) %> </span>&nbsp;&nbsp;
            	<%if(quyen[Utility.THEM]!=0) {%> 
                	<a class="button3" href="javascript:newform()">
                           <img style="top: -4px;" src="../images/New.png" alt=""><%=Utility.GLanguage("T???o m???i",session,jedis) %> </a><%} %>
                </legend>
            	<TABLE width="100%" border="0" cellspacing="1" cellpadding="4">
					<TR class="tbheader">
	                    <TH align="center"><%=Utility.GLanguage("M?? s???",session,jedis) %></TH>
	                    <TH align="center"><%=Utility.GLanguage("Ng??y nh???p",session,jedis) %></TH>
	                    <TH align="center"><%=Utility.GLanguage("Kho nh???p",session,jedis) %></TH>
	                    <TH align="center"><%=Utility.GLanguage("Tr???ng th??i",session,jedis) %></TH>
	                    <TH align="center"><%=Utility.GLanguage("Ng??y t???o",session,jedis) %></TH>
	                    <TH align="center"><%=Utility.GLanguage("Ng?????i t???o",session,jedis) %> </TH>
	                    <TH align="center"><%=Utility.GLanguage("Ng??y s???a",session,jedis) %> </TH>
	                    <TH align="center"><%=Utility.GLanguage("Ng?????i s???a",session,jedis) %> </TH>
	                    <TH align="center" ><%=Utility.GLanguage("T??c v???",session,jedis) %></TH>
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
		                    <TD align="center"><%= nhapkhoRs.getString("NGAYNHAP") %></TD>
		                    <TD align="center"><%= nhapkhoRs.getString("KhoNhan") %></TD>  
		                    	 <TD align="center">
		                    	<%
		                    		String trangthai = "";
		                    		String tt = nhapkhoRs.getString("trangthai");
		                    		if(tt.equals("0"))
		                    			trangthai = "Ch??a ch???t";
		                    		else
		                    		{
		                    			if(tt.equals("1"))
		                    				trangthai = "???? ch???t";
		                    			else if(tt.equals("2"))
		                    			{
			                    			trangthai = "???? h???y";
		                    			}
		                    			else if(tt.equals("3"))
		                    			{
		                    				trangthai = "Ho??n t???t";
	                    				}
		                    		} 
		                    	%>
		                    	<%= trangthai %>
		                    </TD>   									                                    
					     	<TD align="center"><%= nhapkhoRs.getString("NGAYTAO") %></TD>	
		                    <TD align="center"><%= nhapkhoRs.getString("NGUOITAO") %></TD>
         					<TD align="center"><%= nhapkhoRs.getString("NGAYSUA") %></TD>
							<TD align="center"><%= nhapkhoRs.getString("NGUOISUA") %></TD>
									
		                    <TD align="center"> 
		                    <% if(tt.equals("0")){ %>
		                    
		                    	<%if(quyen[Utility.SUA]!=0) {%>
                                	<A href = "../../RouterSvl?task=<%= Utility.dongMa(getServletContext().getInitParameter("RedirectNoScript") +"ErpNhapkhoUpdateSvl?userId="+userId+"&update="+nhapkhoRs.getString("PK_SEQ"))%>"><IMG src="../images/Edit20.png" alt="C???p nh???t" title="C???p nh???t" border=0></A>&nbsp;
                                <%} %>
                                
                                <%if(quyen[Utility.CHOT]!=0) {%>
                                
                               		<A href = "../../RouterSvl?task=<%= Utility.dongMa(getServletContext().getInitParameter("RedirectNoScript") +"ErpNhapkhoSvl?userId="+userId+"&chot="+nhapkhoRs.getString("PK_SEQ"))%>"><img src="../images/Chot.png" alt="Ch???t" title="Ch???t" width="20" height="20" border=0 onclick="if(!confirm('B???n c?? mu???n ch???t nh???p kho n??y?')) return false;"></A>&nbsp;
                                <%} %>
                                
                                <%if(quyen[Utility.XOA]!=0) {%>
                              	<A href = "../../RouterSvl?task=<%= Utility.dongMa(getServletContext().getInitParameter("RedirectNoScript") +"ErpNhapkhoSvl?userId="+userId+"&delete="+nhapkhoRs.getString("PK_SEQ")) %>"><img src="../images/Delete20.png" alt="X??a" title="X??a" width="20" height="20" border=0 onclick="if(!confirm('B???n c?? mu???n x??a nh???p kho n??y?')) return false;"></A>
                              	<%} %>
                              										
		                    <%} else{ %>
		                    
		                    <%if(quyen[Utility.XEM]!=0) {%>
		                   <A href = "../../RouterSvl?task=<%= Utility.dongMa(getServletContext().getInitParameter("RedirectNoScript") +"ErpNhapkhoUpdateSvl?userId="+userId+"&display="+nhapkhoRs.getString("PK_SEQ")) %>"><IMG src="../images/Display20.png" alt="Hi???n th???" title="Hi???n th???" border=0></A>
		                    	
		                    <A href = "../../RouterSvl?task=<%= Utility.dongMa(getServletContext().getInitParameter("RedirectNoScript") +"ErpInPhieuNhapKhoHOPdfSvl?userId="+userId+"&display="+nhapkhoRs.getString("PK_SEQ")) %>"><img src="../images/Printer20.png" alt="In phi???u nh???p kho" title="In phi???u nh???p kho"  width="20" height="20" longdesc="In phi???u nh???p h??ng" border = 0 "></A>
																
		                    	
		                    	<%} %>
		                    	
		                    <% } %>
		                    </TD>
		                </TR>
                     <% m++; } nhapkhoRs.close(); } %>
					<tr class="tbfooter" > 
						 <TD align="center" valign="middle" colspan="13" class="tbfooter">
						 	 <% obj.setNextSplittings(); %>
												 <script type="text/javascript" src="../scripts/phanTrang.js"></script>
												<input type="hidden" name="crrApprSplitting" value="<%= obj.getCrrApprSplitting() %>" >
												<input type="hidden" name="nxtApprSplitting" value="<%= obj.getNxtApprSplitting() %>" >

											 	<%if(obj.getNxtApprSplitting() >1) {%>
													<img alt="Trang Dau" src="../images/first.gif" style="cursor: pointer;" onclick="View(document.forms[0].name, 1, 'view')"> &nbsp;
												<%}else {%>
													<img alt="Trang Dau" src="../images/first.gif" > &nbsp;
													<%} %>
												<% if(obj.getNxtApprSplitting() > 1){ %>
													<img alt="Trang Truoc" src="../images/prev.gif" style="cursor: pointer;" onclick="View(document.forms[0].name, eval(document.forms[0].nxtApprSplitting.value) -1, 'view')"> &nbsp;
												<%}else{ %>
													<img alt="Trang Truoc" src="../images/prev_d.gif" > &nbsp;
												<%} %>
												
												<%
													int[] listPage = obj.getNextSplittings();
													for(int i = 0; i < listPage.length; i++){
												%>
												
												<% 
												
												System.out.println("Current page:" + obj.getNxtApprSplitting());
												System.out.println("List page:" + listPage[i]);
												
												if(listPage[i] == obj.getNxtApprSplitting()){ %>
												
													<a  style="color:white;"><%= listPage[i] %>/ <%=obj.getTheLastSplitting() %></a>
												<%}else{ %>
													<a href="javascript:View(document.forms[0].name, <%= listPage[i] %>, 'view')"><%= listPage[i] %></a>
												<%} %>
													<input type="hidden" name="list" value="<%= listPage[i] %>" />  &nbsp;
												<%} %>
												
												<% if(obj.getNxtApprSplitting() < obj.getTheLastSplitting()){ %>
													&nbsp; <img alt="Trang Tiep" src="../images/next.gif" style="cursor: pointer;" onclick="View(document.forms[0].name, eval(document.forms[0].nxtApprSplitting.value) +1, 'view')"> &nbsp;
												<%}else{ %>
													&nbsp; <img alt="Trang Tiep" src="../images/next_d.gif" > &nbsp;
												<%} %>
												<%if(obj.getNxtApprSplitting() == obj.getTheLastSplitting()) {%>
											   		<img alt="Trang Cuoi" src="../images/last.gif" > &nbsp;
										   		<%}else{ %>
										   			<img alt="Trang Cuoi" src="../images/last.gif" style="cursor: pointer;" onclick="View(document.forms[0].name, -1, 'view')"> &nbsp;
										   		<%} %>
						</TD>
					 </tr>
					 
				</TABLE>
            </fieldset>
        </div>
    </div>  
</div>
</form>
<%geso.dms.center.util.Utility.JedisClose(jedis); %>
</body>
</HTML><%}%>