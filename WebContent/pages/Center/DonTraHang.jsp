<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page  import = "java.util.Iterator" %>
<%@ page  import = "java.util.List" %>
<%@ page  import = "java.util.ArrayList" %>
<%@page import="java.sql.SQLException"%>
<%@ page  import = "geso.dms.center.beans.dontrahang.*" %>
<%@ page  import = "java.sql.ResultSet" %>
<%@ page  import = "geso.dms.center.util.*" %>
<% IDontrahangList obj = (IDontrahangList)session.getAttribute("obj"); %>
<% ResultSet nhapkhoRs =  obj.getNhapkhoRs(); %>

<%	ResultSet nppRs = obj.getKhRs(); %>
<% String userId = (String) session.getAttribute("userId");  %>
<% String userTen = (String) session.getAttribute("userTen");  %>
<% obj.setNextSplittings();
	String sum = (String) session.getAttribute("sum");
	Utility util = (Utility) session.getAttribute("util");
	if(!util.check(userId, userTen, sum)){
		response.sendRedirect("/LT/index.jsp");
	}else{
		int[] quyen = new  int[6];
		quyen = util.Getquyen("DontrahangTTSvl","",userId); 
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
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
<form name="ckParkForm" method="post" action="../../DontrahangTTSvl">
<input type="hidden" name="userId" value="<%= userId %>" >
<input type="hidden" name="action" value="1" >
<input type="hidden" name="msg" value='<%= obj.getMsg() %>'>
<input type="hidden" name="nppId" value='<%= obj.getNppId() %>'>
<script language="javascript" type="text/javascript">
    thongbao();
</script> 

<div id="main" style="width:100%; padding-left:2px">
	<div align="left" id="header" style="width:100%; float:none">
    	<div style="width:55%; padding:5px; float:left" class="tbnavigation">
        	&nbsp;Qu???n l?? t???n kho > Ch???c n??ng > Duy???t tr??? h??ng v??? NCC 
        </div>
        <div align="right" style="padding:5px" class="tbnavigation">
        	Ch??o m???ng B???n <%= userTen %> &nbsp;
        </div>
    </div>
  	<div id="cotent" style="width:100%; float:none">
    	<div align="left" style="width:100%; float:none; clear:left; font-size:0.7em">
        <fieldset style="margin-top:5px" >
            <legend class="legendtitle"> Ti??u ch?? t??m ki???m</legend>
                <TABLE width="100%" cellpadding="6px" cellspacing="0px" style="margin-top: 5px " >
                	 <TR>
                        <TD class="plainlabel" width="15%">T??? ng??y</TD>
                        <TD class="plainlabel">
                            <input type="text" name="tungay" value="<%= obj.getTungayTao() %>" class="days" maxlength="10" onchange="submitform();" />
                        </TD>
                        <TD class="plainlabel" width="15%">?????n ng??y</TD>
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
                        <TD class="plainlabel" valign="middle">Tr???ng th??i </TD>
                        <TD class="plainlabel" valign="middle">
                           <select name="trangthai" onchange="submitform();" >
                           		<option value=""> </option>
								<% if (obj.getTrangthai().equals("1")){%>
								  	<option value="1" selected>???? ch???t</option>
								  	<option value="0">Ch??a ch???t</option>
								  	<option value="2" >???? duy???t</option>
								  	<option value="3" >???? h???y</option>
								  
								<%}else if(obj.getTrangthai().equals("0")) {%>
								 	<option value="0" selected>Ch??a ch???t</option>
								 	<option value="1" >???? ch???t</option>
								  	<option value="2" >???? duy???t</option>
								  	<option value="3" >???? h???y</option>
								<%}else if(obj.getTrangthai().equals("2")) {%>
								 	<option value="2" selected>???? duy???t</option>
								  	<option value="0">Ch??a ch???t</option>
								  	<option value="1" >???? ch???t</option>
								  	<option value="3" >???? h???y</option>
								<%} else  {%>
								 	<option value="0">Ch??a ch???t</option>
								  	<option value="1" >???? ch???t</option>
								  	<option value="2" >???? duy???t</option>
								  	<option value="3" >???? h???y</option>
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
                                <img style="top: -4px;" src="../images/Search30.png" alt="">T??m ki???m </a>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                            <a class="button2" href="javascript:clearform()">
                                <img style="top: -4px;" src="../images/button.png" alt="">Nh???p l???i</a>&nbsp;&nbsp;&nbsp;&nbsp;
                        </td>
                    </tr>        					
                </TABLE>                      
        </fieldset>                      
    	</div>
        <div align="left" style="width:100%; float:none; clear:left; font-size:0.7em">
        	<fieldset>
            	<legend><span class="legendtitle"> Tr??? h??ng v??? NCC </span>&nbsp;&nbsp;
                	<%if(quyen[Utility.THEM]!= 0) {%>
	                	<a class="button3" href="javascript:newform()">
	                    <img style="top: -4px;" src="../images/New.png" alt="">T???o m???i </a>s
                    <%} %>
                </legend>
            	<TABLE width="100%" border="0" cellspacing="1" cellpadding="4">
					<TR class="tbheader">
	                    <TH style="width: 7%" align="center">M?? s???</TH>
	                    <TH style="width: 7%" align="center">Ng??y tr???</TH>
	                    <TH style="width: 15%" align="center">?????i t?????ng</TH>
	                    <TH style="width: 10%" align="center">Tr???ng th??i</TH>
	                    <TH style="width: 10%" align="center">Ng??y t???o</TH>
	                    <TH style="width: 10%" align="center"> Ng?????i t???o </TH>
	                    <TH style="width: 10%" align="center"> Ng??y s???a </TH>
	                    <TH style="width: 15%" align="center"> Ng?????i s???a </TH>
	                    <TH style="width: 10%" align="center" >T??c v???</TH>
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
		                    <TD align="center"><%= nhapkhoRs.getString("ngaytra") %></TD>
		                    <TD align="left"><%= nhapkhoRs.getString("nppTen") %></TD>  
		                    	 <TD align="center">
		                    	<%
		                    		String trangthai = "";
		                    		String tt = nhapkhoRs.getString("trangthai");
		                    		if(tt.equals("0"))
		                    			trangthai = "Ch??a ch???t";
		                    		else
		                    		{
		                    			if(tt.equals("1"))
		                    				trangthai = "NPP ???? ch???t";
		                    			else if(tt.equals("2"))
		                    			{
			                    			trangthai = "???? duy???t";
		                    			}
		                    			else if(tt.equals("3"))
		                    			{
		                    				trangthai = "???? h???y";
	                    				}
		                    		}
		                    	%>
		                    	<%= trangthai %>
		                    </TD>   									                                    
					     	<TD align="center"><%= nhapkhoRs.getString("created_date") %></TD>	
		                    <TD align="left"><%= nhapkhoRs.getString("NGUOITAO") %></TD>
         					<TD align="center"><%= nhapkhoRs.getString("modified_Date") %></TD>
							<TD align="left"><%= nhapkhoRs.getString("NGUOISUA") %></TD>
									
		                    <TD align="center"> 
		                   
		                    <% if(quyen[Utility.CHOT]!= 0 && tt.equals("1")){ %>
                                <A href = "../../DontrahangTTUpdateSvl?userId=<%=userId%>&update=<%= nhapkhoRs.getString("PK_SEQ") %>"><IMG src="../images/Chot.png" alt="Hi???n th???" title="Hi???n th???" border=0></A>
                                <% if(quyen[1]!=0){ %>
                              	<A href = "../../DontrahangTTSvl?userId=<%=userId%>&delete=<%= nhapkhoRs.getString("PK_SEQ") %>"><img src="../images/Delete20.png" alt="X??a" title="X??a" border=0 onclick="if(!confirm('B???n c?? mu???n x??a Tr??? h??ng v??? NCC n??y?')) return false;"></A>
                            	<%} %>
		                    <%} else{ %>
		                     	<% if(quyen[Utility.XEM]!= 0 ){ %>
		                    	<A href = "../../DontrahangTTUpdateSvl?userId=<%=userId%>&display=<%= nhapkhoRs.getString("PK_SEQ") %>"><IMG src="../images/Display20.png" alt="Hi???n th???" title="Hi???n th???" border=0></A>		                   
		                     	<%} %>
		                     	<%-- <% if(tt.equals("2") && (quyen[Utility.HUYCHOT]!=0)){ %> 
                              	<A href = "../../DontrahangTTSvl?userId=<%=userId%>&UnChot=<%= nhapkhoRs.getString("PK_SEQ") %>"><img src="../images/unChot.png" alt="M??? Ch???t" title="M??? Ch???t" width="20" height="20" border=0 onclick="if(!confirm('B???n c?? mu???n m??? Tr??? h??ng v??? NCC n??y?')) return false;"></A>
                              	<%} %> --%>
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
</html>

<%
	if(nhapkhoRs != null){ nhapkhoRs.close(); nhapkhoRs = null; }
	if(nppRs != null){ nppRs.close(); nppRs = null; }
	
	if(obj != null){
		obj.DBclose(); obj = null;
	}
	session.setAttribute("obj",null);

}%>