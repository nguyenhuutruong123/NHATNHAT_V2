<%@page import="geso.traphaco.center.util.IThongTinHienThi"%>
<%@page import="geso.traphaco.center.util.IDinhKhoanKeToan"%>
<%@page import="geso.traphaco.center.util.Utility"%>
<%@page import="java.sql.SQLException"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page  import = "java.util.Iterator" %>
<%@ page  import = "java.util.List" %>
<%@ page  import = "java.util.ArrayList" %>
<%@ page  import = "geso.traphaco.erp.beans.thutien.*" %>
<%@ page  import = "java.sql.ResultSet" %>
<%@ page  import = "java.text.DecimalFormat" %>
<%@ page  import = "java.text.NumberFormat" %>


<% IErpThutienCKList obj = (IErpThutienCKList)session.getAttribute("obj"); %>
<% ResultSet nccList = (ResultSet)obj.getNccList(); %>
<% ResultSet khList = (ResultSet)obj.getKhList(); %>
<% ResultSet nvList = (ResultSet)obj.getNvList(); %>
<% ResultSet htttList = (ResultSet)obj.getHtttList(); %>
<% ResultSet tthdList = (ResultSet)obj.getTThoadonList(); %>
<% ResultSet nguoisuaList = (ResultSet)obj.getNguoisuaRs(); %>
<% ResultSet kbhRs = (ResultSet)obj.getKbhRs(); %>
<% ResultSet tthdRs = (ResultSet)obj.getTThoadonList(); 
   ResultSet rskhoid=obj.getKhohangRs();
   ResultSet nvgnRs=obj.getNvgnRs();

%>
<% String userId = (String) session.getAttribute("userId");  %>
<% String userTen = (String) session.getAttribute("userTen"); 
 

String sum = (String) session.getAttribute("sum");
Utility util = (Utility) session.getAttribute("util");
if(!util.check(userId, userTen, sum)){
	response.sendRedirect("/TraphacoERP/index.jsp");
}else{	
obj.setNextSplittings();   
int[] quyen = new  int[5];
quyen = util.Getquyen("ErpThutienCKSvl","",userId);

NumberFormat formatterNT = new DecimalFormat("#,###,###.##"); 
NumberFormat formatterVND = new DecimalFormat("#,###,###"); 
NumberFormat formatter = new DecimalFormat("#,###,###.##"); 
%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
	<title>TraphacoERP - Project</title>
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
   
   <script type="text/javascript" src="../scripts/dropdowncontent.js"></script>
   <script type="text/javascript" src="../scripts/ajax.js"></script>
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
		document.forms["erpDmhForm"].tungay.value = "";
	    document.forms["erpDmhForm"].denngay.value = "";
	    document.forms["erpDmhForm"].sochungtu.value = "";
	    document.forms["erpDmhForm"].khachhang.value = "";
	    document.forms["erpDmhForm"].trangthai.value = "";
	    document.forms["erpDmhForm"].nguoisua.value = "";
	    document.forms["erpDmhForm"].kbhId.value = "";
	    document.forms["erpDmhForm"].khohhid.value = "";
	    document.forms["erpDmhForm"].ghichu.value = "";
	    document.forms["erpDmhForm"].sobangke.valuse = "";
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
	 
	 function TinhTien()
		{
			// t???ng s???n ph???m
			
		    var sotien = document.getElementById("sotientt").value;
			if(sotien == ''){
				 document.getElementById("sotientt").value = '';
			}
			else{
				document.getElementById("sotientt").value = DinhDangTien(sotien);
			}
			
		}
	 
	 function formatTien(e)
		{
			var giatrinhap = e.value;
			e.value = DinhDangTien(giatrinhap);
			
		}
		
	function DinhDangTien(num) 
	 {
	    num = num.toString().replace(/\$|\,/g,'');
	    if(isNaN(num))
	    num = "0";
	    sign = (num == (num = Math.abs(num)));
	    num = Math.floor(num*100+0.50000000001);
	    num = Math.floor(num/100).toString();
	    for (var i = 0; i < Math.floor((num.length-(1+i))/3); i++)
	    num = num.substring(0,num.length-(4*i+3))+','+
	    num.substring(num.length-(4*i+3));
	    return (((sign)?'':'-') + num);
	}
	
	function duyetform(Id, trangthai)
	{
		 if(!confirm('B???n c?? mu???n duy???t phi???u thu n??y?')) 
		 {
			 return false ;
		 }
		 
		 document.forms['erpDmhForm'].chungtu.value = Id;
		 document.forms['erpDmhForm'].trangthaiphieu.value = trangthai;
		 document.forms['erpDmhForm'].action.value= 'chot';
		 document.forms['erpDmhForm'].submit();
	}
	
	function xoaform(Id, trangthai)
	{
		 if(!confirm('B???n c?? mu???n duy???t phi???u thu n??y?')) 
		 {
			 return false ;
		 }
		 
		 document.forms['erpDmhForm'].chungtu.value = Id;
		 document.forms['erpDmhForm'].trangthaiphieu.value = trangthai;
		 document.forms['erpDmhForm'].action.value= 'delete';
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
<form name="erpDmhForm" method="post" action="../../ErpThutienCKSvl">
<input type="hidden" name="userId" value="<%= userId %>" >
<input type="hidden" name="action" value="1" >
<input type="hidden" name="chungtu" id="chungtu"  >
<input type="hidden" name="trangthaiphieu" id="trangthaiphieu"  >
<input type="hidden" name="crrApprSplitting" value="<%= obj.getCrrApprSplitting() %>" >
<input type="hidden" name="nxtApprSplitting" value="<%= obj.getNxtApprSplitting() %>" >

<input type="hidden" name="msg" value='<%= obj.getmsg() %>'>
<script language="javascript" type="text/javascript">
    thongbao();
</script> 

<div id="main" style="width:100%; padding-left:2px">
	<div align="left" id="header" style="width:100%; float:none">
    	<div style="width:70%; padding:5px; float:left" class="tbnavigation">
        	Qu???n l?? c??ng n??? > C??ng n??? ph???i thu > Thu ti???n chuy???n kho???n
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
                        
                        <TD class="plainlabel" ><%=Utility.GLanguage("?????n ng??y",session,jedis) %> </TD>
                        <TD class="plainlabel">
                            <input type="text" class="days" 
                                   id="denngay" name="denngay" value="<%= obj.getDenngay() %>" maxlength="10" onchange="submitform()" />
                        </TD>
                        
                    </TR>
                    
                    <TR>
                        <TD class="plainlabel" >M?? phi???u </TD>
                        <TD class="plainlabel">
                            <input type="text" id="sochungtu" name="sochungtu" value="<%= obj.getsochungtu() %>"  onchange="submitform()" />
                        </TD>
                        
                        <TD class="plainlabel">M??/T??n ?????i t?????ng</TD>
	                    <TD class="plainlabel">
	                       	<input type="text" name="khachhang" value="<%= obj.getKhId() %>" onchange="submitform()" >
	                     </TD> 
	                     
                    </TR>

					<TR>
						<TD class="plainlabel" ><%=Utility.GLanguage("Tr???ng th??i",session,jedis) %></TD>
						<TD class="plainlabel" >
							<select name = "trangthai" id= "trangthai" style = "width:200px" onchange="submitform()">								
								<%if(obj.getTrangthai().equals("0")){ %>
								<option value=""></option>
								<option value="0" selected="selected" >Ch??a ch???t</option>
								<option value="1" >???? ch???t</option>
								<option value="2" >???? x??a</option>
								<%} else if(obj.getTrangthai().equals("1")){ %>
								<option value=""></option>
								<option value="0" >Ch??a ch???t</option>
								<option value="1" selected="selected" >???? ch???t</option>
								<option value="2" >???? x??a</option>
								<%} else if(obj.getTrangthai().equals("2")){ %>
								<option value=""></option>
								<option value="0" >Ch??a ch???t</option>
								<option value="1" >???? ch???t</option>
								<option value="2" selected="selected" >???? x??a</option>
								<%} else { %>
								<option value="" selected="selected" ></option>
								<option value="0" >Ch??a ch???t</option>
								<option value="1" >???? ch???t</option>
								<option value="2" >???? x??a</option>
								<%} %>
							</select>
						</TD>
						
						<TD class="plainlabel" valign="middle" >Ng?????i l???p </TD>
                        <TD class="plainlabel" valign="middle">
                            <select name="nguoisua" id="nguoisua" style = "width:200px" onchange="submitform()">
                            	<option value=""></option>
                            	<%
	                        		if(nguoisuaList != null)
	                        		{
	                        			while(nguoisuaList.next())
	                        			{  
	                        			if( nguoisuaList.getString("pk_seq").equals(obj.getNguoisuaId())){ %>
	                        				<option value="<%= nguoisuaList.getString("pk_seq") %>" selected="selected" ><%= nguoisuaList.getString("ten") %></option>
	                        			<%}else { %>
	                        				<option value="<%= nguoisuaList.getString("pk_seq") %>" ><%= nguoisuaList.getString("ten") %></option>
	                        		 <% } } nguoisuaList.close();
	                        		}
	                        	%>
                            </select>
                        </TD>
					</TR>
					
                    <TR>
                         
                      <TD class="plainlabel" valign="middle" >K??nh b??n h??ng </TD>
                      <TD class="plainlabel" valign="middle">
                          <select name="kbhId" id="kbhId" style = "width:200px" onchange="submitform()">
                          	<option value=""></option>
                          	<%
                       		if(kbhRs != null)
                       		{
                       			while(kbhRs.next())
                       			{  
                       			if( kbhRs.getString("pk_seq").equals(obj.getKbhId())){ %>
                       				<option value="<%= kbhRs.getString("pk_seq") %>" selected="selected" ><%= kbhRs.getString("diengiai") %></option>
                       			<%}else { %>
                       				<option value="<%= kbhRs.getString("pk_seq") %>" ><%= kbhRs.getString("diengiai") %></option>
                       		 <% } } kbhRs.close();
                       		}
                       	%>
                          </select>
                      </TD>    
                                          	 
                      <TD  class="plainlabel">Kho h??ng h??a</TD>
							<TD  class="plainlabel" colspan="3" >
							<select name="khohhid" class="select2"  style="width: 200px;" onchange="submitform();">
							
								<option value="">  </option>
								<%if(rskhoid!=null){ while (rskhoid.next()){ %>
								<option value="<%=rskhoid.getString("pk_seq")%>" <%if(rskhoid.getString("pk_seq").equals(obj.getKhohangId())){%> selected="selected" <%}%>><%=rskhoid.getString("ten") %>  </option>
								<%} }%>
								
							</select>
					 </TD> 
                       
                    </TR> 
                    
                     <TR>                                               
                        <TD class="plainlabel" >Ghi ch?? </TD>
                        <TD class="plainlabel" >
                            <input type="text" id="ghichu" name="ghichu" value="<%= obj.getGhichu() %>" maxlength="10" onchange="submitform()" />
                        </TD>
                        
                        <TD class="plainlabel" >B???ng k??</TD>
                        <TD class="plainlabel">
                            <input type="text" id="sobangke" name="sobangke" value="<%= obj.getsobangke() %>"  onchange="submitform()" />
                        </TD>
                        
                    </TR>
                    <tr>
                        <td colspan="4" class="plainlabel">
                            <a class="button" href="javascript:submitform()">
                                <img style="top: -4px;" src="../images/Search30.png" alt=""><%=Utility.GLanguage("T??m ki???m",session,jedis) %> </a>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                            <a class="button2" href="javascript:clearform()">
                                <img style="top: -4px;" src="../images/button.png" alt=""><%=Utility.GLanguage("Nh???p l???i",session,jedis) %></a>&nbsp;&nbsp;&nbsp;&nbsp;
                           <a class="button3" href="javascript:xuatExcel()"> <img style="top: -4px;" src="../images/button.png" alt=""><%=Utility.GLanguage("Xu???t Excel",session,jedis) %></a> 
                        </td>
                    </tr>        					
                </TABLE>                      
        </fieldset>                      
    	</div>
        <div align="left" style="width:100%; float:none; clear:left; font-size:0.7em">
        	<fieldset>
            	<legend><span class="legendtitle">Thu ti???n kh??ch h??ng&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span>
            	<%if(quyen[0]!=0){ %>
                	<a class="button3" href="javascript:newform()">
                           <img style="top: -4px;" src="../images/New.png" alt=""><%=Utility.GLanguage("T???o m???i",session,jedis) %> </a>
                           <%} %>
                </legend>
            	<TABLE width="100%" border="0" cellspacing="1" cellpadding="4">
					<TR class="tbheader">
	                    <TH align="center" width = "7%">M?? phi???u</TH>
	                    <TH align="center" width = "7%">Ng??y </TH>
	                   <!--  <TH align="center" width = "6%">S??? h??a ????n </TH> -->
	                   <!--  <TH align="center" width = "13%">?????i t?????ng</TH> -->
	                    <TH align="center" width = "7%">Th???c thu</TH>
	                   <!-- <TH align="center" width = "5%">Ti???n t???</TH> -->
	                   <!-- <TH align="center" width = "7%">H??nh th???c TT</TH> -->
	                    <TH align="center" width = "7%">N???i dung TT</TH>
	                    <TH align="center" width = "7%"><%=Utility.GLanguage("Tr???ng th??i",session,jedis) %></TH>
	                    <TH align="center" width = "7%"><%=Utility.GLanguage("Ng??y s???a",session,jedis) %> </TH>
	                    <TH align="center" width = "7%"><%=Utility.GLanguage("Ng?????i s???a",session,jedis) %> </TH>
	                    <TH align="center" width = "10%"><%=Utility.GLanguage("T??c v???",session,jedis) %></TH>
	                </TR>
	                
	                <% 
	                int m = 0;		
					if(tthdRs!=null)
					{
						 while(tthdRs.next())
						{ 
							 if((m % 2 ) == 0) { %>
                      			<TR class="tbdarkrow">
	                     	<%}else{ %>
	                          	<TR class="tblightrow">
	                        <%} %> 
	                        
	                        <TD align="center" <%if(tthdRs.getString("trangthai").equals("2")) {%> style="color: red" <%} %>><%= tthdRs.getString("machungtu")%></TD>
	                        <TD align="center" <%if(tthdRs.getString("trangthai").equals("2")) {%> style="color: red" <%} %>><%= tthdRs.getString("ngaychungtu") %></TD>
	                        <TD align="right" <%if(tthdRs.getString("trangthai").equals("2")) {%> style="color: red" <%} %>><%= formatterVND.format(Double.parseDouble(tthdRs.getString("THUCTHU"))) %>
							<TD align="center" <%if(tthdRs.getString("trangthai").equals("2")) {%> style="color: red" <%} %> ><%= tthdRs.getString("htttTen") %></TD>	
							<TD align="center" <%if(tthdRs.getString("trangthai").equals("2")) {%> style="color: red" <%} %>>
							<%
								String trangthai = "";
								String tt = tthdRs.getString("trangthai");
								if(tt.equals("0"))
								{
										trangthai = "Ch??a duy???t";
									
								}
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
							<TD align="center" <%if(tthdRs.getString("trangthai").equals("2")) {%> style="color: red" <%} %> ><%= tthdRs.getString("ngaysua") %></TD>
							<TD align="center" <%if(tthdRs.getString("trangthai").equals("2")) {%> style="color: red" <%} %> ><%= tthdRs.getString("nguoisua") %></TD>	
							<TD align="center"> 
								<% if(tt.equals("0")){ %>
									<%if(quyen[2]!=0){ %>
			                            <A href = "../../ErpThutienCKUpdateSvl?userId=<%=userId%>&update=<%= tthdRs.getString("tthdId") %>"><IMG src="../images/Edit20.png" alt="C???p nh???t" title="C???p nh???t" border=0></A>&nbsp;
			                       	<%} %>
			                       	
		                       	   <%if(quyen[4]!=0){ %>
		                               <A id='chotphieu<%=tthdRs.getString("tthdId")%>'
							       			href=""><img
							       			src="../images/Chot.png" alt="Ch???t thu ti???n"
							       			width="20" height="20" title="Ch???t thu ti???n"
							      			border="0" onclick="if(!confirm('B???n c?? ch???c ch???t phi???u thu ti???n n??y?')) {return false ;}else{ processing('<%="chotphieu"+  tthdRs.getString("tthdId")%>' , '../../ErpThutienCKSvl?userId=<%=userId%>&chot=<%=   tthdRs.getString("tthdId") %>&trangthai=<%= tthdRs.getString("trangthai") %>');}"  >
									   </A>	 													 																			 
																						 
									<%} %>
									
								 <%if(quyen[1]!=0){ %>
	                                <A href="javascript:xoaform(<%= tthdRs.getString("tthdId") %>,<%= tthdRs.getString("trangthai") %> );" >
										 	<img  src="../images/Delete20.png" alt="X??a thu ti???n" width="20" height="20"  border='0' title="X??a thu ti???n"	 >
										</A>
	                             <%} %>		
										 
								 <%}else if (tt.equals("1")){ %>
								 	<A href = "../../ErpThutienCKUpdateSvl?userId=<%=userId%>&display=<%= tthdRs.getString("tthdId") %>"><IMG src="../images/Display20.png" alt="Hi???n th???" title="Hi???n th???" border=0></A>&nbsp; 
								
								 <%} else { %>
				                    <A href = "../../ErpThutienCKUpdateSvl?userId=<%=userId%>&display=<%= tthdRs.getString("tthdId") %>"><IMG src="../images/Display20.png" alt="Hi???n th???" title="Hi???n th???" border=0></A>&nbsp;
				                  <%} %>
				                    
							</TD>		
					<% 	m++; }					 
					}	%>
					
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
<script type="text/javascript"> 
	  <%for(int k=0; k < m; k++) {%>
	   
		dropdowncontent.init("ktlist<%=k%>", "left-bottom", 250, "click");
	   
	  <%}%>
	  TinhTien();
</script>

<%
try{
	if( nccList!=null){
		nccList.close();
	}
	if(khList!=null){
		khList.close();
	}
	if(nvList!=null){
		nvList.close();
	}
	if(htttList!=null){
		htttList.close();
	}
	if(tthdList!=null){
		tthdList.close();
	}
	if(nguoisuaList!=null){
		nguoisuaList.close();
	}
	if(kbhRs!=null){
		kbhRs.close();
	}
	if(tthdRs!=null){
		tthdRs.close();
	}
	if(rskhoid!=null){
		rskhoid.close();
	}
	if(nvgnRs!=null){
		nvgnRs.close();
	}
	obj.DBclose(); 
	session.setAttribute("obj",null);
}catch(Exception er){
	
}

}%>

</form>

</body>
</HTML>