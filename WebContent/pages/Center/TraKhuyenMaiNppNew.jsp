<%@page import="geso.dms.center.beans.trakhuyenmainpp.ITrakhuyenmainpp"%>
<%@page import="geso.dms.center.beans.trakhuyenmainpp.ITrakhuyenmainppList"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page  import = "java.util.Iterator" %>
<%@ page  import = "java.util.List" %>
<%@ page  import = "java.util.ArrayList" %>
<%@ page  import = "geso.dms.center.beans.trakhuyenmai.*" %>
<%@ page  import = "java.sql.ResultSet" %>
<% ITrakhuyenmainpp obj = (ITrakhuyenmainpp)session.getAttribute("obj"); %>
<% ResultSet ctkmIds = obj.getctkmIds(); %>
<% ResultSet nppIds = obj.getnppIds(); %>
<% ResultSet sanpham = obj.getSanpham(); %>
<% String userId = (String) session.getAttribute("userId");  %>
<% String userTen = (String) session.getAttribute("userTen");  %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd"> <% Utility Utilback = new Utility(); %>
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
	<title>Insert title here</title>
	<META http-equiv="Content-Type" content="text/html; charset=utf-8">
	<META http-equiv="Content-Style-Type" content="text/css">
    <LINK rel="stylesheet" type="text/css" media="print" href="../css/impression.css">
    <LINK rel="stylesheet" href="../css/main.css" type="text/css">
    <LINK rel="stylesheet" href="../css/datepicker.css" type="text/css">
    
    <script language="javascript" src="../scripts/datepicker.js"></script>
   	<link href="../css/jquery-ui.css" rel="stylesheet" type="text/css"/>
  	<script type="text/javascript" src="../scripts/Timepicker/jquery.min.js"></script>
  	<script type="text/javascript" src="../scripts/Timepicker/jquery-ui.min.js"></script>
   
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
	    if(confirm("Ban co muon dang xuat?"))
	    {
			top.location.href = "home.jsp";
	    }
	    return
	 }
	 function submitform()
	 {   
	    document.forms["trakmnppForm"].submit();
	 }
	
	
	 function tong()
	 {   var tong = 0;
		var dasudung =document.getElementsByName("dasudung");
		 var tongtien = document.getElementById("tongtien");
		var i; 
		//alert(dasudung.length);
		 for(i=0; i < dasudung.length; i++)
		 { 	
			 tong  = parseInt(tong) + parseInt(dasudung.item(i).value);
		 }
		// alert(tong);
		 tongtien.value = tong;
		 setTimeout(tong,20);
	 }
	 tong();
	

	 function saveform()
	 {
		 var diengiai = document.getElementById("diengiai");
		 if(diengiai.value.length >0)
		 {
		    document.forms["trakmnppForm"].action.value = "save";
		    document.forms["trakmnppForm"].submit();
		 }
		 else
			 alert("Ban phai nhap dien giai");
	 }
	
	</SCRIPT>
</head>
<body>
<form name="trakmnppForm" method="post" action="../../TrakhuyenmainppUpdateSvl">
<input type="hidden" name="userId" value="<%= userId %>" >
<input type="hidden" name="action" value="1" >
<input type="hidden" name="id" value="<%= obj.getId() %>" >
<input type="hidden" name="type" value="<%= obj.getType() %>>" >
<div id="main" style="width:99%; padding-left:2px">
	<div align="left" id="header" style="width:100%; float:none">
    	<div style="width:55%; padding:5px; float:left" class="tbnavigation">
        Qu???n l?? khuy???n m??i> Tr??? khuy???n m??i
        </div>
        <div align="right" style="padding:5px" class="tbnavigation">
        <%=Utility.GLanguage("Ch??o m???ng b???n",session,jedis) %>  <%= userTen %> &nbsp;
        </div>
    </div>
  	<div id="cotent" style="width:100%; float:none">
    	<div align="left" style="width:100%; float:none; clear:left; font-size:0.7em">
                 <TABLE width="100%" border="0" cellpadding="0" cellspacing="0">
                    <TR class = "tbdarkrow">
                        <TD width="30" align="left"><A href= "../../TrakhuyenmainppSvl?userId=<%=userId %>" ><img src="../images/Back30.png" alt="Quay ve"  title="Quay ve" border="1" longdesc="Quay ve" style="border-style:outset"></A></TD>
                        <TD width="2" align="left" ></TD>
                        <%if(!obj.getType().trim().equals("3")) { %>
                        <TD width="30" align="left" ><A href="javascript:saveform()" ><IMG src="../images/Save30.png" title="Luu lai" alt="Luu lai" border = "1"  style="border-style:outset"></A></TD>
                         <%} %>
                        <TD align="left" >&nbsp;</TD>
                        
                    </TR>
                </TABLE>
            </TD>
            <fieldset>

    		<legend class="legendtitle"> <%=Utility.GLanguage("B??o l???i nh???p li???u",session,jedis) %></legend>
    		<textarea name="dataerror"  style="width: 100% ; color:#F00 ; font-weight:bold" readonly="readonly" rows="1"><%= obj.getMsg() %></textarea>
		         
    	</fieldset>
        <fieldset>
            <legend class="legendtitle"> <%=Utility.GLanguage("Ti??u ch?? t??m ki???m",session,jedis) %> </legend>
            
                <TABLE width="100%" cellpadding="6px" cellspacing="0px">
                    <TR>
                        <TD class="plainlabel" valign="middle" width="15%"><%=Utility.GLanguage("Di???n gi???i",session,jedis) %> </TD>
                        <TD class="plainlabel" valign="middle">
                            <input type="text" name="diengiai" id ="diengiai" value="<%= obj.getDiengiai() %>">
                        </TD>                        
                    </TR>      
                     	<TR>
                   	          <TD class="plainlabel"><%=Utility.GLanguage("Nh?? ph??n ph???i",session,jedis) %> </TD>
								      <TD class="plainlabel">
								      	<SELECT name="nppid" onchange="submitform();">								      
								  	 		<option value =""></option>
								  	 <% try{ while(nppIds.next()){ 
								    	if(nppIds.getString("pk_seq").equals(obj.getnppId())){ %>
								      		<option value='<%=nppIds.getString("pk_seq") %>' selected><%=nppIds.getString("ten") %></option>
								      	<%}else{ if(!obj.getType().trim().equals("3")){ %>
								      	    
								     		<option value='<%=nppIds.getString("pk_seq") %>'><%=nppIds.getString("ten") %></option>
								     	<%}}}}catch(java.sql.SQLException e){} %>	
								     	
									</SELECT></TD>
								</TR>  
								 <TR>
                        <TD class="plainlabel" valign="middle" width="15%">T???ng ti???n tr???</TD>
                        <TD class="plainlabel" valign="middle">
                            <input type="text"  name="tongtien" id ="tongtien" value="<%= obj.getTongtien() %>">
                        </TD>                        
                    </TR>  
	            </TABLE>                      
        </fieldset>                      
    	</div>
        <div align="left" style="width:100%; float:none; clear:left; font-size:0.7em">
        	<fieldset>
            	<TABLE class="tabledetail" width="100%"  border='1'  cellspacing="1" cellpadding="0">
									<TR class="tbheader">
								    <TH style="width:10%" align="center">Scheme</TH>
				                    <TH style="width:8%" align="center"><%=Utility.GLanguage("T??? ng??y",session,jedis) %></TH>
				                    <TH style="width:8%" align="center"><%=Utility.GLanguage("?????n ng??y",session,jedis) %></TH>
				                    <TH style="width:30%" align="center"><%=Utility.GLanguage("Di???n gi???i",session,jedis) %></TH>
				                    <TH style="width:10%" align="center">SKU KM</TH>
				                    <TH style="width:5%" align="center">S??? l?????ng</TH>
				                    <TH style="width:10%" align="center">Th??nh ti???n</TH>
				                    <TH style="width:10%" align="center">H??nh th???c</TH>
				                   <TH style="width:15%" align="center"><%=Utility.GLanguage("S???n ph???m",session,jedis) %></TH>
				                    <TH style="width:5%" align="center">S??? l?????ng</TH>
				                    <TH style="width:10%" align="center">????n gi??</TH>
				                    <TH style="width:10%" align="center">Th??nh ti???n</TH>
				                    <TH style="width:5%" align="center">Duy???t tr???</TH>
				                    </TR>
							  <%
							   int m =0;
							   String lightrow = "tblightrow";
								String darkrow = "tbdarkrow";
							
							   if(sanpham != null) 
							   while(sanpham.next()) {
								 if(m % 2 == 0) { %>
							     <TR class ="tblightrow" ">
							      <% }else { %>
							      <TR class ="tblightrow">
							      <%} %>
							    <TD align="left" > <%= sanpham.getString("scheme") %> 
 									<input type="hidden" name ="mact" id = "mact" value ='<%= sanpham.getString("pk_seq")%>'>
							     </TD>
							      <TD align="left" > <%= sanpham.getString("tungay") %> </TD>
							      <TD align="left" > <%= sanpham.getString("denngay") %> </TD>
							      <TD align="left" > <%= sanpham.getString("diengiai") %> </TD>
							       <TD align="left" > <%= sanpham.getString("spma") %> </TD>
							        <TD align="left" > <%= sanpham.getString("soluong") %> </TD>
							         <TD align="left" > <%= sanpham.getString("tonggiatri") %> </TD>
							     <TD>
								      	<SELECT name="thanhtoan" style="width:100%">
								      	<% if(sanpham.getString("thanhtoan").trim().equals("1")) {%>		
								      	<option value='1' selected>Ti???n</option>
								     	<option value='2'><%=Utility.GLanguage("S???n ph???m",session,jedis) %></option>						      
								  		<%} else if(sanpham.getString("thanhtoan").trim().equals("2")){ %>
								     	<option value='1' >Ti???n</option>
								     	<option value='2' selected><%=Utility.GLanguage("S???n ph???m",session,jedis) %></option>
								     	<%}else { %>
								     	<option value='1'>Ti???n</option>
								     	<option value='2'><%=Utility.GLanguage("S???n ph???m",session,jedis) %></option>		
								     	<%} %>
									</SELECT>
								</TD>
								<TD style="width:30%"align="left" ><input type="text" name ="sanphamtra"> </TD>
							     <TD align="left" ><input type="text" name ="soluong"> </TD>
								  <TD align="left" ><input type="text" name ="dongia"> </TD>
								   <TD align="left" ><input type="text" name ="thanhtien"> </TD>
								    <TD align="left" ><input type="checkbox" name ="chon"> </TD>
							   </TR>
							  <%m++;} %>
								
						</TABLE>
            </fieldset>
        </div>
    </div>  
    
</div>
</form>
<%
try{
	
	if(obj!=null){
		obj.DBClose();
		obj=null;
	}
	if(ctkmIds!=null){
		ctkmIds.close();
	}
	if(nppIds!=null){
		nppIds.close();
	}
	if(sanpham!=null){
		sanpham.close();
	}
	session.setAttribute("obj",null);
	
}catch(Exception err){}
%>
</body>
</HTML>