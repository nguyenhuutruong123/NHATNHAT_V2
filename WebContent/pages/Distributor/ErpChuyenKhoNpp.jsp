<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page  import = "java.util.Iterator" %>
<%@ page  import = "java.util.List" %>
<%@ page  import = "java.util.ArrayList" %>
<%@page import="java.sql.SQLException"%>
<%@ page  import = "geso.dms.distributor.beans.chuyenkho.*" %>
<%@ page  import = "java.sql.ResultSet" %>
<%@ page  import = "geso.dms.center.util.*" %>
<%@page import="java.text.NumberFormat"%>
<%@page import="java.text.DecimalFormat"%>
<% IErpChuyenkhoNppList obj = (IErpChuyenkhoNppList)session.getAttribute("obj"); %>
<% ResultSet nhapkhoRs =  obj.getNhapkhoRs(); %>

<%	ResultSet nppRs = obj.getKhRs(); %>
<% String userId = (String) session.getAttribute("userId");  %>
<% String userTen = (String) session.getAttribute("userTen");  %>
<% obj.setNextSplittings();
	String sum = (String) session.getAttribute("sum");
	Utility util = (Utility) session.getAttribute("util");
	if(!util.check(userId, userTen, sum)){
		response.sendRedirect(request.getContextPath() + "/redirect.jsp");
	}else{
		int[] quyen = new  int[6];
		quyen = util.Getquyen("ErpChuyenkhoNppSvl","",userId);
		NumberFormat formatter = new DecimalFormat("#,###,###.###");
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
	
<script type="text/javascript" src="../scripts/dropdowncontent2.js"></script>
	<script src="../scripts/ui/jquery.ui.datepicker.js"
		type="text/javascript"></script>
		<style type="text/css">

		/* Big box with list of options */
		#ajax_listOfOptions {
			position: absolute; /* Never change this one */
			width: auto; /* Width of box */
			height: 200px; /* Height of box */
			overflow: auto; /* Scrolling features */
			border: 1px solid #317082; /* Dark green border */
			background-color: #C5E8CD; /* White background color */
			color: black;
			text-align: left;
			font-size: 1.0em;
			z-index: 100;
		}
		
		#ajax_listOfOptions div {
			/* General rule for both .optionDiv and .optionDivSelected */
			margin: 1px;
			padding: 1px;
			cursor: pointer;
			font-size: 1.0em;
		}
		
		#ajax_listOfOptions .optionDiv { /* Div for each item in list */
			
		}
		
		#ajax_listOfOptions .optionDivSelected { /* Selected item in the list */
			background-color: #317082; /*mau khi di chuyen */
			color: #FFF;
		}
		
		#ajax_listOfOptions_iframe {
			background-color: #F00;
			position: absolute;
			z-index: 5;
		}
		
		form {
			display: inline;
		}
		
		#dhtmltooltip {
			position: absolute;
			left: -300px;
			width: 150px;
			border: 1px solid black;
			padding: 2px;
			background-color: lightyellow;
			visibility: hidden;
			z-index: 100;
			/*Remove below line to remove shadow. Below line should always appear last within this CSS*/
			filter: progid:DXImageTransform.Microsoft.Shadow(color=gray, direction=135
				);
		}
		
		#dhtmlpointer {
			position: absolute;
			left: -300px;
			z-index: 101;
			visibility: hidden;
		}
}
</style>
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
	    document.forms["ckParkForm"].sochungtu.value = "";
	    document.forms["ckParkForm"].khId.value = "";
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
    $(document).ready(function() { 
     $(".select2").select2();
 });
</script>
</head>
<body>
<form name="ckParkForm" method="post" action="../../ErpChuyenkhoNppSvl">

		<% redis.clients.jedis.Jedis jedis =  geso.dms.center.util.Utility.getJedis(); %><% geso.dms.center.util.Csrf csdr=new geso.dms.center.util.Csrf(request,response,true,false,true);%>
 <input type="hidden" name="<%=csdr.get_tokenName() %>" value='<%=csdr.get_tokenValue() %>'>
<input type="hidden" name="userId" value="<%= userId %>" >
<input type="hidden" name="action" value="1" >
<input type="hidden" name="msg" value='<%= obj.getMsg() %>'>
<input type="hidden" name="nppId" value='<%= obj.getNppId() %>'>
<input type="hidden" name="currentPage" value="<%= obj.getCurrentPage() %>" >
<script language="javascript" type="text/javascript">
    thongbao();
</script> 

<div id="main" style="width:100%; padding-left:2px">
	<div align="left" id="header" style="width:100%; float:none">
    	<div style="width:55%; padding:5px; float:left" class="tbnavigation">
        	&nbsp;Qu???n l?? t???n kho > Xu???t chuy???n h??ng > Xu???t chuy???n n???i b???
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
                        <TD class="plainlabel" width="15%"><%=Utility.GLanguage("?????n ng??y",session,jedis) %></TD>
                        <TD class="plainlabel">
                            <input type="text" class="days" 
                                   name="denngay" value="<%= obj.getDenngayTao() %>" class="days" maxlength="10" onchange="submitform();"  />
                        </TD>
                    </TR>	
                    
                    
                     <TR>
                     <TD class="plainlabel" width="15%">S??? ch???ng t???</TD>
                        <TD class="plainlabel">
                            <input type="text" name="sochungtu" value="<%= obj.getSochungtu()%>"  onchange="submitform();" />
                        </TD>
                        <TD class="plainlabel" valign="middle"><%=Utility.GLanguage("Tr???ng th??i",session,jedis) %> </TD>
                        <TD class="plainlabel" valign="middle">
                           <select name="trangthai" onchange="submitform();" >
                           		<option value=""> </option>
								<% if (obj.getTrangthai().equals("1")){%>
								  	<option value="1" selected>???? ch???t</option>
								  	<option value="0">Ch??a ch???t</option>
								  	<option value="2" >???? h???y</option>
								  
								<%}else if(obj.getTrangthai().equals("0")) {%>
								 	<option value="0" selected>Ch??a ch???t</option>
								  	<option value="1" >???? ch???t</option>
								  	<option value="2" >???? h???y</option>
								<%}else if(obj.getTrangthai().equals("2")) {%>
								 	<option value="2" selected>???? h???y</option>
								  	<option value="0" >Ch??a ch???t</option>
								  	<option value="1" >???? ch???t</option>
								<%} else  {%>
								 	<option value="0">Ch??a ch???t</option>
								  	<option value="1" >???? ch???t</option>
								  	<option value="2" >???? h???y</option>
								<%} %>
                           </select>
                        </TD>
                                                
                    </TR> 
                    <TR>
	                    <TD class="plainlabel" >?????i t?????ng nh???n</TD>
	                    <TD class="plainlabel" >
	                    	<select name = "khId" style="width: 200px" class="select2"  onchange="submitform();"  >
	                    		<option value=""> </option>
	                        	<%
	                        		if(nppRs != null)
	                        		{
	                        			try
	                        			{
	                        			while(nppRs.next())
	                        			{  
	                        			if( nppRs.getString("pk_seq").equals(obj.getKhId())){ %>
	                        				<option value="<%= nppRs.getString("pk_seq") %>" selected="selected" ><%= nppRs.getString("ten") %></option>
	                        			<%}else { %>
	                        				<option value="<%= nppRs.getString("pk_seq") %>" ><%= nppRs.getString("ten") %></option>
	                        		 <% } } nppRs.close();} catch(SQLException ex){}
	                        		}%>
	                        	
	                    	</select>
	                    </TD>  
	                    <TD class="plainlabel" > </TD>
	                    <TD class="plainlabel" > </TD>
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
            	<legend><span class="legendtitle"> Xu???t chuy???n n???i b??? </span>&nbsp;&nbsp;
            	<%if(quyen[Utility.THEM]!=0){ %>
                	<a class="button3" href="javascript:newform()">
                    <img style="top: -4px;" src="../images/New.png" alt=""><%=Utility.GLanguage("T???o m???i",session,jedis) %> </a>
                <%} %>
                </legend>
            	<TABLE width="100%" border="0" cellspacing="1" cellpadding="4">
					<TR class="tbheader">
	                    <TH style="width: 7%" align="center">M?? s???</TH>
	                    <TH style="width: 7%" align="center">Ng??y xu???t</TH>
	                     <TH style="width: 7%" align="center">S??? ch???ng t???</TH>
	                    <TH style="width: 15%" align="center">?????i t?????ng</TH>
	                    <TH style="width: 10%" align="center"><%=Utility.GLanguage("Tr???ng th??i",session,jedis) %></TH>
	                    <TH style="width: 10%" align="center"><%=Utility.GLanguage("Ng??y t???o",session,jedis) %></TH>
	                    <TH style="width: 10%" align="center"><%=Utility.GLanguage("Ng?????i t???o",session,jedis) %> </TH>
	                    <TH style="width: 7%" align="center"><%=Utility.GLanguage("Ng??y s???a",session,jedis) %> </TH>
	                    <TH style="width: 7%" align="center"><%=Utility.GLanguage("Ng?????i s???a",session,jedis) %> </TH>
	                     <TH style="width: 8%" align="center"> T???ng ti???n </TH>
	                    <TH style="width: 15%" align="center" ><%=Utility.GLanguage("T??c v???",session,jedis) %></TH>
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
		                    <TD align="center"><%= nhapkhoRs.getString("sonetId") %></TD>
		                    <TD align="center"><%= nhapkhoRs.getString("ngaychuyen") %></TD>
		                    <TD align="center"><%= nhapkhoRs.getString("SOCHUNGTU") %></TD>
		                    <%-- <TD align="center"><%= nhapkhoRs.getString("KhoXuat") %></TD>   --%>
		                    <TD align="left"><%= nhapkhoRs.getString("nppTEN") %></TD>  
		                    	 <TD align="center">
		                    	<%
		                    		String trangthai = "";
		                    		String tt = nhapkhoRs.getString("trangthai");
		                    		String dain = nhapkhoRs.getString("dain");
		                    		if(tt.equals("0"))
		                    			trangthai = "Ch??a ch???t";
		                    		else
		                    		{
		                    			if(tt.equals("1")){
		                    				
		                    				if(dain.equals("0")){
		                    					trangthai = "???? ch???t (Ch??a in)";
		                    				}
		                    				
		                    				if(dain.equals("1")){
		                    					trangthai = "???? ch???t (???? in)";
		                    				}
		                    			}
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
		                    <TD align="left"><%= nhapkhoRs.getString("NGUOITAO") %></TD>
         					<TD align="center"><%= nhapkhoRs.getString("NGAYSUA") %></TD>
							<TD align="left"><%= nhapkhoRs.getString("NGUOISUA") %></TD>
							<%-- <TD align="left"><%= nhapkhoRs.getString("tongtien") %></TD> --%>
							<TD align="left"><%= formatter.format(Double.parseDouble(nhapkhoRs.getString("tongtien"))) %></TD>
							
		                    <TD align="center"> 
		                    <% if(tt.equals("0")){ %>
		                    
		                    <%if(quyen[Utility.SUA]!=0){ %>
                                <A href = "../../ErpChuyenkhoNppUpdateSvl?userId=<%=userId%>&update=<%=nhapkhoRs.getString("PK_SEQ") %>"><IMG src="../images/Edit20.png" alt="C???p nh???t" title="C???p nh???t" border=0></A>&nbsp;
                              <% } %>
                                
                                <%if(quyen[Utility.CHOT]!=0){ %>
                                <A href = "../../ErpChuyenkhoNppSvl?userId=<%=userId%>&chot=<%= nhapkhoRs.getString("PK_SEQ") %>"><img src="../images/Chot.png" alt="Ch???t" title="Ch???t" width="20" height="20" border=0 onclick="if(!confirm('B???n c?? mu???n ch???t xu???t chuy???n h??ng n??y?')) return false;"></A>&nbsp;
                                 <% } %>
                                
                                <%if(quyen[Utility.XOA]!=0){ %>
                              	<A href = "../../ErpChuyenkhoNppSvl?userId=<%=userId%>&delete=<%= nhapkhoRs.getString("PK_SEQ") %>"><img src="../images/Delete20.png" alt="X??a" title="X??a" width="20" height="20" border=0 onclick="if(!confirm('B???n c?? mu???n x??a xu???t chuy???n h??ng n??y?')) return false;"></A>
                              	<% } %>
                              										
		                    <%} else{ %>
		                    
		                    <%if(quyen[Utility.XEM]!=0){ %>
		                    	<A href = "../../ErpChuyenkhoNppUpdateSvl?userId=<%=userId%>&display=<%= nhapkhoRs.getString("PK_SEQ") %>"><IMG src="../images/Display20.png" alt="Hi???n th???" title="Hi???n th???" border=0></A>
		                    	
		                    	<%
		                    		if(!nhapkhoRs.getString("nppId").equals("0")){ %>
		                    		
		                    		
              				<%-- <A href="../../ErpChuyenkhoNppPdfSvl?userId=<%=userId%>&chuyenkho=<%=nhapkhoRs.getString("PK_SEQ")%>&nppId=<%= obj.getNppId()%>" >
							<IMG src="../images/Printer30.png"  title="In" alt="In" width="20" height="20" border=0 >
							</A> --%>
        									
        						<A href="../../ErpInphieuxuatchuyennoiboNppPdfSvl?userId=<%=userId%>&chuyenkho=<%=nhapkhoRs.getString("PK_SEQ")%>&nppId=<%= obj.getNppId()%>" >
							<IMG src="../images/Printer30.png"  title="In phi???u xu???t kho ki??m v???n chuy???n n???i b???" alt="In phi???u xu???t kho ki??m v???n chuy???n n???i b???" width="20" height="20" border=0 >
							</A>
							
							
							<A href="../../ErpInphieudieukhoNppPdfSvl?userId=<%=userId%>&chuyenkho=<%=nhapkhoRs.getString("PK_SEQ")%>&nppId=<%= obj.getNppId()%>" >
							<IMG src="../images/pdfblue.jpg"  title="In phi???u ??i???u kho" alt="In phi???u ??i???u kho" width="20" height="20" border=0 >
							</A>
        						
        						
        						<A href="../../ErpLenhdieudonghanghoaNppPdfSvl?userId=<%=userId%>&chuyenkho=<%=nhapkhoRs.getString("PK_SEQ")%>&nppId=<%= obj.getNppId()%>" >
							<IMG src="../images/Pdf30.jpg"  title="In l???nh ??i???u ?????ng h??ng h??a" alt="In l???nh ??i???u ?????ng h??ng h??a" width="20" height="20" border=0 >
							</A>
							
																
        									
        									
        							<%} %>
		                   <% } %>
		                   
		                    <% if(tt.equals("1") && (quyen[Utility.XOA]!=0)){ %>
                              	<A href = "../../ErpChuyenkhoNppSvl?userId=<%=userId%>&UnChot=<%= nhapkhoRs.getString("PK_SEQ") %>"><img src="../images/unChot.png" alt="M??? Ch???t" title="M??? Ch???t" width="20" height="20" border=0 onclick="if(!confirm('B???n c?? mu???n m??? xu???t chuy???n h??ng n??y?')) return false;"></A>
                              	<% } %>
		                    	
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
</body>
</HTML><%}%>