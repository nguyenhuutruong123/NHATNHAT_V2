<%@page import="java.sql.SQLException"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page  import = "geso.dms.distributor.beans.phieuxuatkho.*" %>
<%@ page  import = "java.sql.ResultSet" %>
<%@ page  import = "java.util.Hashtable" %>
<%@ page import = "java.text.DateFormat" %>
<%@ page import = "java.text.DecimalFormat" %>
<%@ page  import = "geso.dms.center.util.*" %>

<%	String userId = (String) session.getAttribute("userId");  
	String userTen = (String) session.getAttribute("userTen");  	
	String sum = (String) session.getAttribute("sum");
	Utility util = (Utility) session.getAttribute("util");
	if(!util.check(userId, userTen, sum)){
		response.sendRedirect(request.getContextPath() + "/redirect.jsp");
	}else{ %>

<% IPhieuxuatkho pxkBean = (IPhieuxuatkho)session.getAttribute("pxkBean"); %>
<% ResultSet nvbh = (ResultSet)pxkBean.getNvBanhang(); %>
<% ResultSet nvgn = (ResultSet)pxkBean.getNhanvienGN(); %>
<% ResultSet donhangList = (ResultSet)pxkBean.getDonhangList(); %>

<% Hashtable<Integer, String> donhangIds = (Hashtable<Integer, String>)pxkBean.getDonhangIds(); %>


<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd"> <% Utility Utilback = new Utility(); %>
<html>
<head>
	<TITLE><%= getServletContext().getInitParameter("TITLENAME") %></TITLE>
	<META http-equiv="Content-Type" content="text/html; charset=utf-8">
	<META http-equiv="Content-Style-Type" content="text/css">

	<LINK rel="stylesheet" type="text/css" media="print" href="../css/impression.css">
    <LINK rel="stylesheet" href="../css/main.css" type="text/css">
    <LINK rel="stylesheet" href="../css/datepicker.css" type="text/css">
    
    <script language="javascript" src="../scripts/datepicker.js"></script>
   	<link href="../css/jquery-ui.css" rel="stylesheet" type="text/css"/>
  	<script type="text/javascript" src="../scripts/Timepicker/jquery.min.js"></script>
  	<script type="text/javascript" src="../scripts/Timepicker/jquery-ui.min.js"></script>
  	
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
   
    <script language="javascript" type="text/javascript">
		function CheckAll()
		{
			var selectAll = document.getElementById("selectAll");
			var chon = document.getElementsByName("donhangList");
			if(selectAll.checked)
			{
				for(i = 0; i < chon.length; i++)
					chon.item(i).checked = true;
			}
			else
			{
				for(i = 0; i < chon.length; i++)
					chon.item(i).checked = false;
			}
		}
		
		function UnCheckedAll()
		{
			var selectAll = document.getElementById("selectAll");
			selectAll.checked = false;
		}
		
		function submitform()
		{   
			document.forms['pxkForm'].action="../../PhieuxuatkhoUpdateSvl";
		    document.forms['pxkForm'].action.value='submitForm';
		    document.forms['pxkForm'].submit();
		}
		
		function LoadSanPhamLo()
		{   
			document.forms['pxkForm'].action="../../PhieuxuatkhoUpdateSvl";
		    document.forms['pxkForm'].action.value='LoadLoSp';
		    document.forms['pxkForm'].submit();
		}
		function saveform()
		{	
			if(document.getElementById("ngaylap").value == "")
			{
				alert('B???n ph???i ch???n ng??y l???p phi???u ????? l???c ????n h??ng...');
				return;	
			}
			var nvgn = document.getElementById("nvgnList");
			if(nvgn.value == "")
			{
				alert('B???n ph???i ch???n nh??n vi??n giao nh???n...');
				return;
			}
			if(checkDonhangList() == false)
			{
				alert('Xin l???i , kh??ng c?? ????n h??ng...');
				return;
			}
			var ngaykhoaso = document.getElementById("ngaykhoaso").value;
			 var tungay = document.getElementById("ngaylap").value;
			// alert(ngaykhoaso +' '+ tungay);
			 if(ngaykhoaso.length >0 && tungay.length >0 ){
			 var ngay1 =Date.parse(ngaykhoaso);
			 var ngay2 = Date.parse(tungay);
			 	if(ngay1 >=  ngay2)
				{
					alert('L???i...B???n ???? kh??a s??? ?????n ng??y '+ngaykhoaso  +' r???i...');
					return;
				}
			 }
			 document.forms['pxkForm'].action="../../PhieuxuatkhoUpdateSvl";
			 
			 document.getElementById("btnSave").innerHTML = "<a href='#'><img src='../images/waiting.gif'  title='cho thuc hien..' border='1' longdesc='cho thuc hien..' style='border-style:outset'> Processing...</a>";
			 
			document.forms['pxkForm'].action.value='save';
		    document.forms['pxkForm'].submit();
		}
		
		function checkDonhangList()
		{
			if(document.getElementsByName("donhangList") == null)
				return false;
			var dhList = document.getElementsByName("donhangList");
			for(k=0; k < dhList.length; k++)
			{
				if(dhList.item(k).checked)
					return true;
			}
			return false;
		}
		
	</script>
    
</head>
<BODY leftmargin="0" bottommargin="0" topmargin="0" rightmargin="0">
<form name="pxkForm" method="post" action="">
<% redis.clients.jedis.Jedis jedis =  geso.dms.center.util.Utility.getJedis(); %><% geso.dms.center.util.Csrf csdr=new geso.dms.center.util.Csrf(request,response,true,false,true);%>
 <input type="hidden" name="<%=csdr.get_tokenName() %>" value='<%=csdr.get_tokenValue() %>'>
<INPUT name="userId" type="hidden" value='<%=userId %>' size="30">
<INPUT name="nppId" type="hidden" value='<%=pxkBean.getNppId() %>' size="30">
<input type="hidden" name="ngaykhoaso" id = "ngaykhoaso" value='<%=pxkBean.getngaykhoaso() %>'>
<input type="hidden" name="id" value='<%=pxkBean.getId() %>'>
<input type="hidden" name="action" value='1'>
<div id="main" style="width:99%; padding-left:2px">
	<div align="left" id="header" style="width:100%; float:none">
    	<div style="width:55%; padding:5px; float:left" class="tbnavigation">
        	Qu???n l?? b??n h??ng > B??n h??ng OTC > Phi???u xu???t kho > C???p nh???t
        </div>
        <div align="right" style="padding:5px" class="tbnavigation">
        	Ch??o m???ng   <%= pxkBean.getNppTen() %> 
        </div>
    </div>
  
  	<div align="left" id="button" style="width:100%; height:32px; padding:0px; float:none" class="tbdarkrow">
    	<A href= "../../PhieuxuatkhoSvl?userId=<%=userId %>" >
        	<img src="../images/Back30.png" alt="Quay v???"  title="Quay v???" border="1px" longdesc="Quay ve" style="border-style:outset"></A>
       <label id="btnSave">
        
        <A href="javascript:saveform()" >
        	<IMG src="../images/Save30.png" title="L??u l???i" alt="L??u l???i" border ="1px" style="border-style:outset"></A>
    	</label>
    </div>
    
  	<div align="left" style="width:100%; float:none; clear:left">
  		<fieldset>
    		<legend class="legendtitle"><%=Utility.GLanguage("B??o l???i nh???p li???u",session,jedis) %></legend>
    		<textarea name="dataerror"  style="width: 100% ; color:#F00 ; font-weight:bold" style="width:100%" rows="1" readonly="readonly"><%= pxkBean.getMessage() %></textarea>
		         <% pxkBean.setMessage(""); %>
    	</fieldset>
  	</div>
  	
    <div align="left" style="width:100%; float:none; clear:left">
    <fieldset>
    	<legend class="legendtitle"> Phi???u xu???t kho </legend>
        	
        	<div style="float:none; width:100%" align="left">
            	<table width="100%" cellspacing="0" cellpadding="6px">
                    <tr>
                    	<td class="plainlabel" width="20%" valign="middle">Ng??y l???p phi???u</td>
                    	<td class="plainlabel">
                            <input type="text" size="11" class="days" 
                                    id="ngaylap" name="ngaylap" value="<%= pxkBean.getNgaylap() %>" maxlength="10" readonly  onchange="submitform()" />
                    	</td> 
                    	<td class="plainlabel">&nbsp;</td>                   
                    </tr>       
                    <tr>
           				<td class="plainlabel">Nh??n vi??n giao nh???n <FONT class="erroralert"> *</FONT></td> 
                        <td class="plainlabel">
                            <SELECT name="nvgnTen" id="nvgnList" onchange="submitform()">
					 			 <option value="">&nbsp;</option>
								  <% if(nvgn != null){
									  try{ while(nvgn.next()){ 
						    			if(nvgn.getString("nvgnId").equals(pxkBean.getNvgnId())){ %>
						      				<option value='<%=nvgn.getString("nvgnId")%>' selected><%=nvgn.getString("nvgnTen") %></option>
						      			<%}else{ %>
						     				<option value='<%=nvgn.getString("nvgnId")%>'><%=nvgn.getString("nvgnTen") %></option>
						     	<%}}}catch(java.sql.SQLException e){} }%>	 
                              </SELECT>
                        </td>
                        <td class="plainlabel">&nbsp;</td>
                    </tr>
                    <tr>
           				<td class="plainlabel"><%=Utility.GLanguage("NH??N VI??N B??N H??NG",session,jedis) %><FONT class="erroralert"> *</FONT></td> 
                        <td class="plainlabel">
                            <select name="nvbhTen" id="nvbhTen" onchange="submitform()">
                            	<option value="">&nbsp;</option>
                                <% if(nvbh != null){
									  try{ while(nvbh.next()){ 
						    			if(nvbh.getString("nvbhId").equals(pxkBean.getNvbhId())){ %>
						      				<option value='<%=nvbh.getString("nvbhId")%>' selected><%=nvbh.getString("nvbhTen") %></option>
						      			<%}else{ %>
						     				<option value='<%=nvbh.getString("nvbhId")%>'><%=nvbh.getString("nvbhTen") %></option>
						     	<%}}}catch(java.sql.SQLException e){} }%>
                            </select>
                        </td>
                        <td class="plainlabel">&nbsp;</td>
                    </tr>
                 </table>
                 <hr>
                 <% if(donhangList != null){ DecimalFormat df2 = new DecimalFormat( "#,###,###,##0" ); %>
                 <table width="100%" cellpadding="4px" cellspacing="1px">
                 	<tr>
                    	<th class="tbheader" align="center">S??? ????n h??ng</th>
                        <th class="tbheader" align="center">Ng??y ????n h??ng</th>
                        <th class="tbheader" align="center">M?? kh??ch h??ng</th>
                        <th class="tbheader" align="left">T??n kh??ch h??ng</th>
                        <th class="tbheader" align="center">T???ng gi?? tr???</th>
                        <th class="tbheader" valign="middle" width="10%" align="center">Ch???n 
                        	</th>
                    </tr>
                    
                    <% try{ while(donhangList.next()){ 
                    	String style = "";
                    	if( donhangList.getString("loaihoadon").equals("1") )
                    		style = "style='color:red;'";
                    	%>
		    				<tr class="tbdarkrow" <%= style %> >
	                    	<td align="center">
	                    		<%= donhangList.getString("dhId") %>
	                    	</td>
	                        <td align="center"><%= donhangList.getDate("NGAYXUATHD").toString() %></td>
	                        <td align="center"><%= donhangList.getString("maFAST") %></td>
	                        <td><%= donhangList.getString("khTen") %></td>
	                        <td align="center">
	                        	<%= df2.format(donhangList.getFloat("tonggiatri")).toString() %>
	                        </td>
	                        <% if(donhangIds.contains(donhangList.getString("dhId"))){ %>
	                        	<td align="center"><input type="radio" name="donhangList" value="<%= donhangList.getString("dhId") %>"" checked></td>
	                        <%}else{ %>
	                        	<td align="center"><input type="radio" name="donhangList" value="<%= donhangList.getString("dhId") %>" "></td>
	                        <%} %>
                    	</tr>
	     			<% } } catch(Exception e){ e.printStackTrace(); } %>
	     			
                    <tr class="tbfooter"><td colspan="7">&nbsp;</td></tr>
                 </table>
                 <%} %>
            </div>
        
    </fieldset>	
    </div>
</div>
</form>
</BODY>
</HTML>
<% 	

	try{
	if(nvbh != null)
		nvbh.close();
	if(nvgn != null)
		nvgn.close();
	if(donhangList!=null){
		donhangList.close();
	}
	if(donhangIds!=null){
		donhangIds.clear();
	}
	if(pxkBean != null){
		pxkBean.DBclose();
		pxkBean= null;
	}
	session.setAttribute("pxkBean",null);
	}
	catch (Exception e) {}

%>
<%}%>