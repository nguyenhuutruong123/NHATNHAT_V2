<%@page import="geso.dms.center.util.Utility"%>
<%@page import="java.sql.SQLException"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page  import = "java.util.Iterator" %>
<%@ page  import = "java.util.List" %>
<%@ page  import = "java.util.ArrayList" %>
<%@ page  import = "geso.dms.distributor.beans.khachhangtt.*" %>
<%@ page  import = "java.sql.ResultSet" %>
<%@ page  import = "java.text.DecimalFormat" %>
<%@ page  import = "java.text.NumberFormat" %>


<% IKhachhangTTList obj = (IKhachhangTTList)session.getAttribute("obj"); %>
<% ResultSet nvgnList = (ResultSet)obj.getNvgnList(); %>
<% ResultSet nvbhList = (ResultSet)obj.getNvbhList(); %>
<% ResultSet khList = (ResultSet)obj.getKhList(); %>
<% ResultSet htttList = (ResultSet)obj.getHtttList(); %>
<% ResultSet tthdList = (ResultSet)obj.getTThoadonList(); %>
<% String userId = (String) session.getAttribute("userId");  %>
<% String userTen = (String) session.getAttribute("userTen");  


String sum = (String) session.getAttribute("sum");
Utility util = (Utility) session.getAttribute("util");
if(!util.check(userId, userTen, sum)){
	response.sendRedirect(request.getContextPath() + "/redirect.jsp");
}else{	
obj.setNextSplittings();   
int[] quyen = new  int[5];
quyen = util.Getquyen("114",userId);

NumberFormat formatterNT = new DecimalFormat("#,###,###.##"); 
NumberFormat formatterVND = new DecimalFormat("#,###,###"); 
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
		document.forms["erpDmhForm"].action.value = "Tao moi";
	    document.forms["erpDmhForm"].submit();
	 }
	 function clearform()
	 {   
	    document.forms["erpDmhForm"].nvgnId.value = "";
	    document.forms["erpDmhForm"].nvbhId.value = "";
	    document.forms["erpDmhForm"].khId.value = "";
	    document.forms["erpDmhForm"].trangthai.value = "";
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
	 
	 function xuatExcel() {
			document.forms['erpDmhForm'].action.value = 'excel';
			document.forms['erpDmhForm'].submit();
		}
	</SCRIPT>
</head>

<link href="../css/select2.css" rel="stylesheet" />
<script src="../scripts/select2.js"></script>
<script>
     $(document).ready(function() { $("select").select2();  });
     
</script>

<body>
<form name="erpDmhForm" method="post" action="../../KhachHangTraTruocSvl">
<input type="hidden" name="userId" value="<%= userId %>" >
<input type="hidden" name="action" value="1" >
<input type="hidden" name="crrApprSplitting" value="<%= obj.getCrrApprSplitting() %>" >
<input type="hidden" name="nxtApprSplitting" value="<%= obj.getNxtApprSplitting() %>" >

<input type="hidden" name="msg" value='<%= obj.getmsg() %>'>
<script language="javascript" type="text/javascript">
    thongbao();
</script> 

<div id="main" style="width:100%; padding-left:2px">
	<div align="left" id="header" style="width:100%; float:none">
    	<div style="width:55%; padding:5px; float:left" class="tbnavigation">
        	Qu???n l?? c??ng n??? > X??a n??? kh??ch h??ng
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
                        <TD class="plainlabel" width="13%"><%=Utility.GLanguage("T??? ng??y",session,jedis) %> </TD>
                        <TD class="plainlabel">
                            <input type="text" class="days" 
                                   id="tungay" name="tungay" value="<%= obj.getTungay() %>" maxlength="10" onchange="submitform()" />
                        </TD>
                        <TD class="plainlabel" ><%=Utility.GLanguage("?????n ng??y",session,jedis) %> </TD>
                        <TD class="plainlabel">
                            <input type="text" class="days" 
                                   id="denngay" name="denngay" value="<%= obj.getDenngay() %>" maxlength="10" onchange="submitform()" />
                        </TD>
                    </TR>
                    <TR>
                        <TD class="plainlabel" valign="middle" >Kh??ch h??ng </TD>
                        <TD class="plainlabel" valign="middle">
                            <select name="khId" id="khId" style = "width:200px" onchange="submitform()">
                            	<option value=""></option>
                            	<%
	                        		if(khList != null)
	                        		{
	                        			while(khList.next())
	                        			{  
	                        			if( khList.getString("pk_seq").equals(obj.getKhId())){ %>
	                        				<option value="<%= khList.getString("pk_seq") %>" selected="selected" ><%= khList.getString("ten") %></option>
	                        			<%}else { %>
	                        				<option value="<%= khList.getString("pk_seq") %>" ><%= khList.getString("ten") %></option>
	                        		 <% } } khList.close();
	                        		}
	                        	%>
                            </select>
                        </TD> 
                        <TD class="plainlabel"><%=Utility.GLanguage("NH??N VI??N B??N H??NG",session,jedis) %></TD>
                  		<TD class="plainlabel" >                        
                  		<select name="nvbhId" id="nvbhId" style="width: 200px" onchange="submitform()" >
                            <option value=""> </option>
                        	<%
                        		if(nvbhList != null)
                        		{
                        			try
                        			{
                        			while(nvbhList.next())
                        			{  
                        			if(nvbhList.getString("pk_seq").equals(obj.getNvbhId()) ){ %>
                        				<option value="<%= nvbhList.getString("pk_seq") %>" selected="selected" ><%= nvbhList.getString("Ten") %></option>
                        			<%}else { %>
                        				<option value="<%= nvbhList.getString("pk_seq") %>" ><%= nvbhList.getString("Ten") %></option>
                        		 <% } } nvbhList.close();} catch(SQLException ex){}
                        		}
                        	%>
                        </select>
                     </TD>                        
                    </TR> 
                    <TR>
                     <TD class="plainlabel">Nh??n vi??n giao nh???n</TD>
                  		<TD class="plainlabel" >                        
                  		<select name="nvgnId" id="nvgnId" style="width: 200px" onchange="submitform()" >
                            <option value=""> </option>
                        	<%
                        		if(nvgnList != null)
                        		{
  			    			try
                        			{
                        			while(nvgnList.next())
                        			{  
                        			if( nvgnList.getString("pk_seq").equals(obj.getNvgnId()) ){ %>
                        				<option value="<%= nvgnList.getString("pk_seq") %>" selected="selected" ><%= nvgnList.getString("Ten") %></option>
                        			<%}else { %>
                        				<option value="<%= nvgnList.getString("pk_seq") %>" ><%= nvgnList.getString("Ten") %></option>
                        		 <% } } nvgnList.close();} catch(SQLException ex){}
                        		}
                        	%>
                        </select>
                     </TD>
                    <TD class="plainlabel" ><%=Utility.GLanguage("Tr???ng th??i",session,jedis) %> </TD>
                        <TD class="plainlabel" >
                        	<select name="trangthai" id="trangthai" onchange="submitform()" style="width: 200px" >
                        	<%if(obj.getTrangthai().equals("0")){ %>
	                        	<option value=""></option>
	                        	<option value="0" selected="selected">Ch??a x??c nh???n</option>
	                        	<option value="1">???? x??c nh???n</option>
	                        	<option value="2">???? h???y</option>
	                        <%}else if(obj.getTrangthai().equals("1")){ %>
	                        	<option value=""></option>
	                        	<option value="0" >Ch??a x??c nh???n</option>
	                        	<option value="1" selected="selected">???? x??c nh???n</option>
	                        	<option value="2">???? h???y</option>
	                        <%} else if(obj.getTrangthai().equals("2")){ %>
	                        	<option value=""></option>
	                        	<option value="0" >Ch??a x??c nh???n</option>
	                        	<option value="1" >???? x??c nh???n</option>
	                        	<option value="2" selected="selected">???? h???y</option>
	                        <% }else if(obj.getTrangthai().equals("")){ %>
	                        	<option value="" selected="selected"></option>
	                        	<option value="0" >Ch??a x??c nh???n</option>
	                        	<option value="1" >???? x??c nh???n</option>
	                        	<option value="2" >???? h???y</option>
	                        <%} %>
                        	</select>
                        </TD> 
                    </TR>    
                    <tr>
                        <td colspan="5" class="plainlabel">
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
            	<legend><span class="legendtitle">X??a n??? kh??ch h??ng&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span>
            	
                	<a class="button3" href="javascript:newform()">
                           <img style="top: -4px;" src="../images/New.png" alt=""><%=Utility.GLanguage("T???o m???i",session,jedis) %> </a>
                           
                </legend>
            	<TABLE width="100%" border="0" cellspacing="1" cellpadding="4">
					<TR class="tbheader">
	                    <TH align="center" width = "10%">M?? phi???u</TH>
	                    <TH align="center" width = "10%">Ng??y </TH>
	                    <TH align="center" width = "15%">Th???c thu</TH>
	                    <TH align="center" width = "10%"><%=Utility.GLanguage("Tr???ng th??i",session,jedis) %></TH>
	                    <TH align="center" width = "10%"><%=Utility.GLanguage("Ng??y s???a",session,jedis) %> </TH>
	                    <TH align="center" width = "10%"><%=Utility.GLanguage("Ng?????i s???a",session,jedis) %> </TH>
	                    <TH align="center" width = "15%"><%=Utility.GLanguage("T??c v???",session,jedis) %></TH>
	                </TR>
					<%
						if(tthdList != null)
						{
							try
							{
								int m = 0;
								while(tthdList.next())
								{
									if((m % 2 ) == 0) {%>
		                         		<TR class="tbdarkrow">
			                     <%}else{ %>
			                          	<TR class="tblightrow">
			                        <%} %>
	
									<TD align="center"><%= tthdList.getString("tthdId") %></TD>
									<TD align="center"><%= tthdList.getString("ngaychungtu") %></TD>
									
									
									<TD align="right">									
										<%= formatterNT.format(Double.parseDouble(tthdList.getString("THUCTHU"))) %>										
									</TD>
									<TD align="center">
										<%
											String trangthai = "";
											String tt = tthdList.getString("trangthai");
											if(tt.equals("0"))
												trangthai = "Ch??a ch???t";
											else
											{
												if(tt.equals("1"))
												{
													trangthai = "???? x??c nh???n";
												}
												else
												{
													if(tt.equals("2"))
														trangthai = "???? h???y";
													else
														trangthai = "Ch??a x??c nh???n";
												}
											}
										%>
										<%= trangthai %>
									</TD>
									<TD align="center"><%= tthdList.getString("ngaysua") %></TD>
									<TD align="center"><%= tthdList.getString("nguoisua") %></TD>
									<TD align="center"> 
				                    <% if(tt.equals("0")){ %><A href = "../../KhachHangTraTruocUpdateSvl?userId=<%=userId%>&update=<%= tthdList.getString("tthdId") %>"><IMG src="../images/Edit20.png" alt="C???p nh???t" title="C???p nh???t" border=0></A>&nbsp;		                               
		                                 <A id='chotphieu<%=tthdList.getString("tthdId")%>'
							       			href=""><img
							       			src="../images/Chot.png" alt="Ch???t thu ti???n"
							       			width="20" height="20" title="Ch???t thu ti???n"
							      			border="0" onclick="if(!confirm('B???n c?? ch???c ch???t phi???u thu ti???n n??y?')) {return false ;}else{ processing('<%="chotphieu"+tthdList.getString("tthdId")%>' , '../../KhachHangTraTruocSvl?userId=<%=userId%>&chot=<%= tthdList.getString("tthdId") %>');}"  >
										 </A>
		                                <A href = "../../KhachHangTraTruocSvl?userId=<%=userId%>&delete=<%= tthdList.getString("tthdId") %>"><img src="../images/Delete20.png" alt="X??a thanh to??n" title="X??a thanh to??n" width="20" height="20" border=0 onclick="if(!confirm('B???n c?? mu???n x??a phi???u thu ti???n n??y?')) return false;"></A>								
				                    <%}else{ %>
				                    	<A href = "../../KhachHangTraTruocUpdateSvl?userId=<%=userId%>&display=<%= tthdList.getString("tthdId") %>"><IMG src="../images/Display20.png" alt="Hi???n th???" title="Hi???n th???" border=0></A>&nbsp; 
				                    <%} %>
				                    </TD>
				                    </TR>
								<% m++;}
							}
							catch(SQLException ex){}
						}
					%>
						<tr class="tbfooter" > 
						 <TD align="center" valign="middle" colspan="13" class="tbfooter">
						 	<%if(obj.getNxtApprSplitting() >1) {%>
								<img alt="Trang Dau" src="../images/first.gif" style="cursor: pointer;" onclick="View('erpDmhForm', 1, 'view')"> &nbsp;
							<%}else {%>
								<img alt="Trang Dau" src="../images/first.gif" > &nbsp;
								<%} %>
							<% if(obj.getNxtApprSplitting() > 1){ %>
								<img alt="Trang Truoc" src="../images/prev.gif" style="cursor: pointer;" onclick="Prev('erpDmhForm', 'prev')"> &nbsp;
							<%}else{ %>
								<img alt="Trang Truoc" src="../images/prev_d.gif" > &nbsp;
							<%} %>
							
							<%
								int[] listPage = obj.getNextSplittings();
								for(int i = 0; i < listPage.length; i++){
							%>
							
							<% 							
						
							if(listPage[i] == obj.getNxtApprSplitting()){ %>
							
								<a  style="color:white;"><%= listPage[i] %>/ <%=obj.getTheLastSplitting() %></a>
							<%}else{ %>
								<a href="javascript:View('erpDmhForm', <%= listPage[i] %>, 'view')"><%= listPage[i] %></a>
							<%} %>
								<input type="hidden" name="list" value="<%= listPage[i] %>" />  &nbsp;
							<%} %>
							
							<% if(obj.getNxtApprSplitting() < obj.getTheLastSplitting()){ %>
								&nbsp; <img alt="Trang Tiep" src="../images/next.gif" style="cursor: pointer;" onclick="Next('erpDmhForm', 'next')"> &nbsp;
							<%}else{ %>
								&nbsp; <img alt="Trang Tiep" src="../images/next_d.gif" > &nbsp;
							<%} %>
							<%if(obj.getNxtApprSplitting() == obj.getTheLastSplitting()) {%>
						   		<img alt="Trang Cuoi" src="../images/last.gif" > &nbsp;
					   		<%}else{ %>
					   			<img alt="Trang Cuoi" src="../images/last.gif" style="cursor: pointer;" onclick="View('erpDmhForm', -1, 'view')"> &nbsp;
					   		<%} %>
						</TD>
					 </tr>  
				</TABLE>
            </fieldset>
        </div>
    </div>  
</div>
<%
try{
	if( nvbhList!=null){
		nvbhList.close();
	}
	if(nvgnList!=null){
		nvgnList.close();
	}
	if(khList!=null){
		khList.close();
	}
	if(tthdList!=null){
		tthdList.close();
	}
	obj.DBclose(); 
	session.setAttribute("obj",null);
}catch(Exception er){
	
}

}%>
</form>
</body>
</HTML>