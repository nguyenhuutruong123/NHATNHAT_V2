<%@page import="geso.dms.center.util.Utility"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page  import = "java.util.Iterator" %>
<%@ page  import = "java.util.List" %>
<%@ page  import = "java.util.ArrayList" %>
<%@ page  import = "geso.dms.distributor.beans.thanhtoanhoadon.*" %>
<%@ page  import = "java.sql.ResultSet" %>
<%@ page  import = "java.text.DecimalFormat" %>
<%@ page  import = "java.text.NumberFormat" %>


<%
	String userId = (String) session.getAttribute("userId");  
	String userTen = (String) session.getAttribute("userTen");  	
	String sum = (String) session.getAttribute("sum");
	Utility util = (Utility) session.getAttribute("util");
	if(!util.check(userId, userTen, sum)){
		response.sendRedirect("/Phanam/index.jsp");
	}else{
		IDuyetthanhtoanhoadon obj = (IDuyetthanhtoanhoadon)session.getAttribute("dtthdBean"); %>
<% ResultSet nvList = (ResultSet)obj.getNvRs() ;%>
<% ResultSet khList = (ResultSet)obj.getKhRs(); %>
<% ResultSet nccList = (ResultSet)obj.getNccList(); %>

<% ResultSet polist = (ResultSet)obj.getPoList(); %>
<% NumberFormat formatter = new DecimalFormat("#,###,###");  
	int[] quyen = new  int[5];
	quyen = util.Getquyen("ErpDuyetthanhtoanhoadonSvl","",userId);
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

    <script type="text/javascript" src="../scripts/jquery.min.js"></script>
	<script type="text/javascript" src="../scripts/speechbubbles.js"></script>
	<script type="text/javascript" src="../scripts/dropdowncontent.js"></script>
	<script type="text/javascript" src="../scripts/jquery.autocomplete.js"></script>
	
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
	function replaces()
	{
		var ncc = document.getElementsByName("ncc");
		var nccId = document.getElementsByName("nccId");
		var nccId_old = nccId[0].value;
		var nccId_new = ncc[0].value.substring(0, parseInt(ncc[0].value.indexOf(" - ")))
			
		if(nccId_old != nccId_new){
			nccId[0].value = ncc[0].value.substring(0, parseInt(ncc[0].value.indexOf(" - ")));
			document.forms["erpDmhForm"].submit();
		}
			
		setTimeout(replaces, 300);
	}
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
	 
	 function clearform()
	 {   
	    document.forms["erpDmhForm"].loaict.value = "";
	    document.forms["erpDmhForm"].nvId.value = "";
	    document.forms["erpDmhForm"].khId.value = "";
	    document.forms["erpDmhForm"].ngayghinhan.value = "";
	    document.forms["erpDmhForm"].maTTHD.value = "";
	    document.forms["erpDmhForm"].nccId.value = "";
	    document.forms["erpDmhForm"].submit();
	 }

	 function duyetform(id, loaiid){
	    document.forms["erpDmhForm"].Id.value = id;
	    document.forms["erpDmhForm"].LoaiId.value = loaiid;
	    document.forms["erpDmhForm"].action.value = "duyet";
	    document.forms["erpDmhForm"].submit();
			 
	 }
	 
	 function thongbao()
	 {
		 var tt = document.forms["erpDmhForm"].msg.value;
	 	 if(tt.length>0)
	     	alert(document.forms["erpDmhForm"].msg.value);
	 }
	 

	</SCRIPT>
<link href="../css/select2.css" rel="stylesheet" />
<script src="../scripts/select2.js"></script>
<script>
     $(document).ready(function() { $("select").select2();  });
     
</script>
</head>
<body>
<form name="erpDmhForm" method="post" action="../../ErpDuyetthanhtoanhoadonSvl">
<input type="hidden" name="ctyId" value="<%= obj.getCtyId() %>" >
<input type="hidden" name="userId" value="<%= userId %>" >
<input type="hidden" name="Id" value="" >
<input type="hidden" name="LoaiId" value="" >

<input type="hidden" name="action" value="1" >

<input type="hidden" name="msg" value='<%= obj.getMsg() %>'>
<script language="javascript" type="text/javascript">
    thongbao();
</script> 

<div id="main" style="width:100%; padding-left:2px">
	<div align="left" id="header" style="width:100%; float:none">
    	<div style="width:70%; padding:5px; float:left" class="tbnavigation">Qu???n l?? c??ng n??? > C??ng n??? ph???i tr??? > Duy???t phi???u chi/ U??? nhi???m chi
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
                    	<TD class="plainlabel" valign="middle" >M?? phi???u chi/ UNC</TD>	
                    	<TD class="plainlabel" valign="middle" >
                    		<input type="text" name="maTTHD" id="maTTHD"  value="<%= obj.getMaTTHD() %>"   onchange="submitform()"/>
                    	 </TD>
                    	
                    	<TD class="plainlabel" valign="middle" >Ng??y ghi nh???n</TD>	
                    	<TD class="plainlabel" valign="middle" >
                    		<input type="text" class="days" name="ngayghinhan" id="ngayghinhan"  value="<%= obj.getNgayghinhan() %>" maxlength="10"  onchange="submitform()"/>
                    	 </TD>
                    </TR>
                    
                     <TR>
                        <TD class="plainlabel" valign="middle" >Nh?? cung c???p</TD>
                        <TD class="plainlabel" valign="middle" >
		                    <select name="nccId" onchange="submitform()" style = "width:200px">
                            	<option value=""></option>
                            	<%
	                        		if(nccList != null)
	                        		{
	                        			while(nccList.next())
	                        			{  
	                        			  if( nccList.getString("pk_seq").equals(obj.getNccId() )){ %>
	                        				<option value="<%= nccList.getString("pk_seq") %>" selected="selected" ><%= nccList.getString("tenncc") %></option>
	                        			<%}else { %>
	                        				<option value="<%= nccList.getString("pk_seq") %>" ><%= nccList.getString("tenncc") %></option>
	                        		 <% } } nccList.close();
	                        		}
	                        	%>
                            </select>  
                        </TD>    
                        
                        <TD class="plainlabel" valign="middle" >Nh??n vi??n</TD>
                        <TD class="plainlabel" valign="middle" >
		                    <select name="nvId" onchange="submitform()" style = "width:200px">
                            	<option value=""></option>
                            	<%
	                        		if(nvList != null)
	                        		{
	                        			while(nvList.next())
	                        			{  
	                        			  if( nvList.getString("pk_seq").equals(obj.getNvId() )){ %>
	                        				<option value="<%= nvList.getString("pk_seq") %>" selected="selected" ><%= nvList.getString("tennv") %></option>
	                        			<%}else { %>
	                        				<option value="<%= nvList.getString("pk_seq") %>" ><%= nvList.getString("tennv") %></option>
	                        		 <% } } nvList.close();
	                        		}
	                        	%>
                            </select>  
                        </TD>                     
                    </TR>
                    
                    <TR>
                    	<%-- <TD class="plainlabel" valign="middle" >Kh??ch h??ng</TD>
                        <TD class="plainlabel" valign="middle" >
		                    <select name="khId" onchange="submitform()" style = "width:200px">
                            	<option value=""></option>
                            	<%
	                        		if(khList != null)
	                        		{
	                        			while(khList.next())
	                        			{  
	                        			  if( khList.getString("pk_seq").equals(obj.getKhId() )){ %>
	                        				<option value="<%= khList.getString("pk_seq") %>" selected="selected" ><%= khList.getString("tenkh") %></option>
	                        			<%}else { %>
	                        				<option value="<%= khList.getString("pk_seq") %>" ><%= khList.getString("tenkh") %></option>
	                        		 <% } } khList.close();
	                        		}
	                        	%>
                            </select>  
                        </TD>  --%>
                        
                         <TD class="plainlabel" valign="middle" >Lo???i ch???ng t??? </TD> 
                        <TD class="plainlabel" valign="middle" colspan="3">
							<select name = "loaict" id= "loaict" style = "width:200px" onchange="submitform()">
								<%if(obj.getLoaiCT().equals("0")){ %>
								<option value=""></option>
								<option value="0" selected="selected">Phi???u chi</option>
								<option value="1">???y nhi???m chi</option>
								<option value="2">Phi???u thu</option>
								<%}else if(obj.getLoaiCT().equals("1")){ %>
								<option value=""></option>
								<option value="0">Phi???u chi</option>
								<option value="1" selected="selected">???y nhi???m chi</option>
								<option value="2">Phi???u thu</option>	
								<%}else if(obj.getLoaiCT().equals("2")){ %>
								<option value=""></option>
								<option value="0">Phi???u chi</option>
								<option value="1" >???y nhi???m chi</option>
								<option value="2" selected="selected">Phi???u thu</option>						
								<%} else { %>
								<option value="" selected="selected"></option>
								<option value="0">Phi???u chi</option>
								<option value="1" >???y nhi???m chi</option>
								<option value="2">Phi???u thu</option>		
								<%} %>
							</select>
                        </TD>  
                    </TR>
                    
                    <tr>
                        <td colspan="4" class="plainlabel">
                            <a class="button2" href="javascript:clearform()">
                                <img style="top: -4px;" src="../images/button.png" alt=""><%=Utility.GLanguage("Nh???p l???i",session,jedis) %> </a>&nbsp;&nbsp;&nbsp;&nbsp;
                        </td>
                    </tr>
                </TABLE>  
        </fieldset>                      
    	</div>
        <div align="left" style="width:100%; float:none; clear:left; font-size:0.7em">
        	<fieldset>
            	<legend><span class="legendtitle">Duy???t phi???u chi/ U??? nhi???m chi </span>&nbsp;&nbsp;
                </legend>
            	<TABLE width="100%" border="0" cellspacing="1" cellpadding="4">
					<TR class="tbheader">
	                    <TH align="center" width="9%">Duy???t</TH>
	                    <TH align="center" width="9%">M?? phi???u chi/ UNC</TH>
	                    <TH align="center" width="20%">Nh?? cung c???p/ Nh??n vi??n/ Kh??ch h??ng</TH>	                    
	                    <TH align="center" width="10%">T???ng ti???n</TH>
	                    <TH align="center" width="9%">Ng??y</TH>
	                    <TH align="center" width="9%"><%=Utility.GLanguage("Ng?????i t???o",session,jedis) %></TH>
	                    <TH align="center" width="9%">Lo???i ch???ng t???</TH>	     
	                    
	                </TR>
	                
		<%	try
			{
				int m = 0;
				String lightrow = "tblightrow";
				String darkrow = "tbdarkrow";
				String mhId = "";
				String mhId_bk = "";
				
				while(polist.next())
				{
					mhId = polist.getString("TTHDID");
					if(!mhId.equals(mhId_bk)){
				%>				
						<%//	m = 0 ;
					if (m % 2 != 0) { %>						
									<TR class= <%=lightrow%> >
							<%  } else { %>
									<TR class= <%= darkrow%> >
							<%  } %>
							<TD align = "center" > 
							
								<%if(quyen[4]!=0 && polist.getInt("isKTT") >= 0){ %>
								<a  href="javascript:duyetform(<%= polist.getString("TTHDID") %>, <%= polist.getString("LOAICTID") %>)">
								<img style="top: -4px;" src="../images/Chot.png" alt=""></a>&nbsp;&nbsp;&nbsp;&nbsp;
								<%} %>
								
								 <%if(quyen[0] != 0 ){ 
									 if(polist.getString("LOAICTID").equals("0")) {  // PHIEU CHI / UNC 
									     if(polist.getString("HTTT_FK").equals("100000")) { // PHI???U CHI %>
				                		<A href = "../../ErpTTHoadonUpdateSvl?userId=<%=userId%>&update=<%= polist.getString("TTHDID") %>">
	                            		<IMG src="../images/Edit20.png" alt="Cap nhat" title="C???p nh???t" border=0></A>&nbsp;
	                                 <% }else if(polist.getString("HTTT_FK").equals("100001")) { // ???Y NHI???M CHI %>
	                                 	<A href = "../../ErpUynhiemchiUpdateSvl?userId=<%=userId%>&update=<%= polist.getString("TTHDID") %>">
	                            		<IMG src="../images/Edit20.png" alt="Cap nhat" title="C???p nh???t" border=0></A>&nbsp;
	                                 <%}
								      } else { // PHIEU THU %>
								      <A href = "../../ErpThutienUpdateSvl?userId=<%=userId%>&update=<%=polist.getString("TTHDID")  %>">
								      <IMG src="../images/Edit20.png" alt="C???p nh???t" title="C???p nh???t" border=0></A>&nbsp;
								  <%}} %>
							</TD>
							<TD align = "center"><%= polist.getString("SOCHUNGTU") %> </TD>
							<TD align = "left" ><%= polist.getString("TENDT")  %> </TD>
							<TD align = "right" ><%= formatter.format(Double.parseDouble(polist.getString("TONGTIENAVAT")))  %> </TD>
							<TD align = "center"><%= polist.getString("NGAYGHINHAN") %> </TD>
							<TD align = "center"><%= polist.getString("NGUOITAO") %> </TD>	
							<TD align = "center"><%= polist.getString("LOAICT") %> </TD>							
						</TR>			

		<%			}else{ %>

				<%	m--; {%>						
			
			
		<%			}
					mhId_bk = polist.getString("TTHDID");
					m++;
		
					}
			}
			}catch(Exception e){ e.printStackTrace();}%>
	                
				</TABLE>
            </fieldset>
        </div>
    </div>  

</form>

</body>
</HTML>
<% 
try{
if(polist!=null){
	polist.close();
}
obj.DBclose();
session.setAttribute("dtthdBean",null);
}catch(Exception er){
	
}
	}
%>