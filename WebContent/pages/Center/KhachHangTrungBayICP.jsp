<%@page import="com.cete.dynamicpdf.text.bd"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page  import = "geso.dms.center.beans.bandott.IBandott" %>
<%@ page  import = "geso.dms.center.beans.bandott.imp.Bandott" %>
<%@ page  import = "java.sql.ResultSet" %>
<%@ page  import = "geso.dms.center.util.*" %>
<%	String userId = (String) session.getAttribute("userId");  
	String userTen = (String) session.getAttribute("userTen");  	
	String sum = (String) session.getAttribute("sum");
	Utility util = (Utility) session.getAttribute("util");
	if(!util.check(userId, userTen, sum)){
		response.sendRedirect(request.getContextPath() + "/redirect.jsp");
	}else{ %>

<% IBandott bdBean = (IBandott)session.getAttribute("obj"); %>
<% ResultSet vungRs = bdBean.getVungRs(); %>
<% ResultSet kvRs = bdBean.getKvRs(); %>
<% ResultSet nppRs = bdBean.getNppRs(); %>
<% ResultSet cttbRs = bdBean.getCttbRs(); %>
<% ResultSet ddkd = bdBean.getDdkdRs(); %>
<% ResultSet tbh = bdBean.getTbhRs(); %>
<% ResultSet khList = bdBean.getKhChuaViengThamRs(); %>


<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd"> <% Utility Utilback = new Utility(); %>
<html>
<head>
	<TITLE><%= getServletContext().getInitParameter("TITLENAME") %></TITLE>
	<META http-equiv="Content-Type" content="text/html; charset=utf-8">
	
	<script src="//maps.google.com/maps?file=api&amp;v=2&amp;key=AIzaSyDUs-Mowbr3M3jRoVxy30B9uBAlEFgbKMQ"
            type="text/javascript"></script>
    
    <script type="text/javascript">

    var map = null;
    
    var geocoder = null;
    
    var center = null;

    function initialize() 
    {
      if (GBrowserIsCompatible())
      {
        map = new GMap2(document.getElementById("map_canvas")); 
        geocoder = new GClientGeocoder();
        
        <% if(bdBean.getTrungtam().length() > 0 ){ System.out.println("1.Center la: " + bdBean.getTrungtam());  %>
        	center = new GLatLng(<%= bdBean.getTrungtam() %>)
        <% } else { %>        	
        	center = new GLatLng(10.79912,106.680259);
        <%} %>
        
        map.setCenter(center, 14);
        map.setUIToDefault();
        
		var latKh = new Array();
		var marKh = new Array();
		
		<%
				if(khList != null)
				{
					int count = 0;
					while(khList.next())
					{
				%>  
			
			   latKh[<%= count %>] = new GLatLng(parseFloat('<%= khList.getString("lat") %>'), parseFloat('<%= khList.getString("long") %>') );      
			   
			   
			   var blueIcon = new GIcon(G_DEFAULT_ICON);
		       /* blueIcon.image = "../upload/<%= khList.getString("hinhBenTrai") %>"; */
		       blueIcon.image = "../images/maps_human.png";
			   blueIcon.iconSize = new GSize(39, 39);
			   markerOptions = { icon:blueIcon };
			   var link = "../upload/<%= khList.getString("hinhBenTrai") %>";
			   
			   marKh[<%= count %>] = new GMarker(latKh[<%= count %>], markerOptions);
			   
		       GEvent.addListener(marKh[<%= count %>],"click", function()
		      	{
		    	    var info = '<b>Kh??ch h??ng: </b><%= khList.getString("khTen") %><br /><b>S??? ??i???n tho???i: </b><%= khList.getString("dienthoai") %> ' +
		    	    		   '<br /><b>Ghi ch??: </b><%= khList.getString("ghiChu") %><br /><img src = "../upload/<%= khList.getString("hinhBenTrai") %>" style="max-height: 150px; max-width: 250px" />';
		    	    		   
					map.openInfoWindowHtml(new GLatLng(parseFloat('<%= khList.getString("lat") %>'), parseFloat('<%= khList.getString("long") %>') ), info);
		      	});   
		    	map.addOverlay(marKh[<%= count %>]);   
		          
			<% count++; }}
		%>

		
      }
    }
    
    function showAddress(address) 
    {
    	 geocoder.getLatLng(
    	    address,
    	    function(point) 
    	    {
    	      if (!point) 
    	      {
    	        alert("Kh??ng th??? t??n th???y ?????a ch???:" + address);
    	      } 
    	      else 
    	      {
	    	        map.setCenter(point, 14);
	    	        var marker = new GMarker(point);
	    	        map.addOverlay(marker);
	
	    	        marker.openInfoWindow(document.createTextNode("?????a ch???: " + address));
	    	        document.getElementById("trungtam").value = point.lat() + "," + point.lng();
    	      }
    	    }
    	  );
    }
    
    </script>
    
    
    
    <LINK rel="stylesheet" type="text/css" media="print" href="../css/impression.css">
	<LINK rel="stylesheet" href="../css/main.css" type="text/css">

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
  	
  	<script type="text/javascript">
	  	function submitform()
		{  
	  		document.getElementById("action").value = 'submit';
		    document.forms["bdForm"].submit();
		}
	  	
	  	function exportToPdf()
		{  
	  		document.getElementById("action").value = 'pdf';
		    document.forms["bdForm"].submit();
		}
	  	
	  	function exportToExcel()
		{  
	  		document.getElementById("action").value = 'excel';
		    document.forms["bdForm"].submit();
		}
  	</script>
</head>

<body onload="initialize()" onunload="GUnload()">
<form name="bdForm" method="post" action="../../KhachHangTBSvl">
<input type="hidden" name="userId" value='<%= userId %>'>
<input type="hidden" name="view" value='khachhangTB'>
<input type="hidden" name="action" id="action" value='1'>
<input type="hidden" name="trungtam" id="trungtam" value=''>

<div id="main" style="width:99.5%; padding-left:2px">

	<div align="left" id="header" style="width:100%; float:none">
    	<div style="width:40%; padding:5px; float:left" class="tbnavigation">
        	B??o c??o qu???n tr??? > B??o c??o kh??c > Audit tr??ng b??y
        </div>
        <div align="right" style="padding:5px" class="tbnavigation">
        	<%=Utility.GLanguage("Ch??o m???ng b???n",session,jedis) %>  <%= userTen %> &nbsp;&nbsp;
        </div>
    </div>
    
    <div align="left" style="width:100%; height:32px; padding:0px; float:none" class="tbdarkrow">
    	
        <A href= "javascript:exportToExcel();" >
        	<img src="../images/excel.gif" width="30" height="30" alt="Xu???t file Excel"  title="Xu???t file Excel" border="1px" longdesc="Xu???t file Excel" style="border-style:outset"></A>
    </div>
    
    <div style="width:100%; float:none" align="left">
    <fieldset>
    	<legend class="legendtitle">Th??ng tin audit</legend>
        <TABLE width="100%" cellpadding="4" cellspacing="0">								   
            <TR style="display: none">
                <TD class="plainlabel" width="150px" >Ng??y </TD>
                <TD class="plainlabel" colspan="3">
                    <input type="text" size="11" class="days" 
                          id="ngay" name="ngay" value="<%= bdBean.getDate() %>" maxlength="10" readonly onchange="submitform();"  />
                </TD>
            </TR>
            <TR>
                <TD class="plainlabel" width="150px" ><%=Utility.GLanguage("V??ng",session,jedis) %> mi???n </TD>
                <TD class="plainlabel" width="240px">
                    <select name="vung" id="vung" onChange = "submitform();">
                            <option value="" selected></option>
                            <% if(vungRs != null){
								  try{ while(vungRs.next()){ 
					    			if(vungRs.getString("pk_seq").equals(bdBean.getVungId())){ %>
					      				<option value='<%= vungRs.getString("pk_seq")%>' selected><%= vungRs.getString("ten") %></option>
					      			<%}else{ %>
					     				<option value='<%= vungRs.getString("pk_seq")%>'><%= vungRs.getString("ten") %></option>
					     	<%}} vungRs.close(); }catch(java.sql.SQLException e){} }%>
                        </select>    
                </TD>
                <TD class="plainlabel" width="150px"><%=Utility.GLanguage("Khu v???c",session,jedis) %> </TD>
                <TD class="plainlabel">
                    <select name="khuvuc" id="khuvuc" onChange = "submitform();">
                            <option value="" selected></option>
                            <% if(kvRs != null){
								  try{ while(kvRs.next()){ 
					    			if(kvRs.getString("pk_seq").equals(bdBean.getkvId())){ %>
					      				<option value='<%= kvRs.getString("pk_seq")%>' selected><%= kvRs.getString("ten") %></option>
					      			<%}else{ %>
					     				<option value='<%= kvRs.getString("pk_seq")%>'><%= kvRs.getString("ten") %></option>
					     	<%}} kvRs.close(); }catch(java.sql.SQLException e){} }%>
                        </select>    
                </TD>
            </TR>
            <TR>
                <TD class="plainlabel" ><%=Utility.GLanguage("Nh?? ph??n ph???i",session,jedis) %> </TD>
                <TD class="plainlabel">
                    <select name="npp" id="npp" onChange = "submitform();">
                            <option value="" selected></option>
                            <% if(nppRs != null){
								  try{ while(nppRs.next()){ 
					    			if(nppRs.getString("pk_seq").equals(bdBean.getNppId())){ %>
					      				<option value='<%= nppRs.getString("pk_seq")%>' selected><%= nppRs.getString("ten") %></option>
					      			<%}else{ %>
					     				<option value='<%= nppRs.getString("pk_seq")%>'><%= nppRs.getString("ten") %></option>
					     	<%}} nppRs.close(); }catch(java.sql.SQLException e){} }%>
                        </select>    
                </TD>
                <TD class="plainlabel" >Ch????ng tr??nh tr??ng b??y </TD>
                <TD class="plainlabel">
                    <select name="cttbIds" id="cttbIds" onChange = "submitform();">
                            <option value="" selected></option>
                            <% if(cttbRs != null){
								  try{ while(cttbRs.next()){ 
					    			if(cttbRs.getString("pk_seq").equals(bdBean.getCttbId())){ %>
					      				<option value='<%= cttbRs.getString("pk_seq")%>' selected><%= cttbRs.getString("scheme") %></option>
					      			<%}else{ %>
					     				<option value='<%= cttbRs.getString("pk_seq")%>'><%= cttbRs.getString("scheme") %></option>
					     	<%}} cttbRs.close(); }catch(java.sql.SQLException e){} }%>
                        </select>    
                </TD>
            </TR>
            <TR>
               <TD class="plainlabel" ><%=Utility.GLanguage("NH??N VI??N B??N H??NG",session,jedis) %> </TD>
                <TD class="plainlabel">
                    <select name="ddkd" id="ddkd" onChange = "submitform();">
                            <option value="" selected></option>
                            <% if(ddkd != null){
								  try{ while(ddkd.next()){ 
					    			if(ddkd.getString("ddkdId").equals(bdBean.getDdkdId())){ %>
					      				<option value='<%= ddkd.getString("ddkdId")%>' selected><%= ddkd.getString("ddkdTen") %></option>
					      			<%}else{ %>
					     				<option value='<%= ddkd.getString("ddkdId")%>'><%= ddkd.getString("ddkdTen") %></option>
					     	<%}} ddkd.close(); }catch(java.sql.SQLException e){} }%>
                        </select>    
                </TD>
                <TD class="plainlabel" >Tuy???n b??n h??ng </TD>
                <TD class="plainlabel">
                    <select name="tbh" id="tbh" onChange = "submitform();">
                          <option value="" selected></option>
                          <% if(tbh != null){
						  try{ while(tbh.next()){ 
			    			if(tbh.getString("tbhId").equals(bdBean.getTbhId())){ %>
			      				<option value='<%= tbh.getString("tbhId")%>' selected><%= tbh.getString("tbhTen") %></option>
			      			<%}else{ %>
			     				<option value='<%= tbh.getString("tbhId")%>'><%= tbh.getString("tbhTen") %></option>
			     	<%}} tbh.close(); }catch(java.sql.SQLException e){} }%>
                      </select>    
               </TD>
            </TR>
            <TR>
                <TD class="plainlabel" >??o???n ???????ng </TD>
                <TD class="plainlabel" colspan="3">
                    <input type="text" name="address" value="<%= bdBean.getAddress() %>" onchange="showAddress(this.value)" >  
               </TD>
            </TR>
        </TABLE>
     	<hr>
    
	<div id="map_canvas" style="width: 100%; height: 650px; position:static; margin-left:auto; margin-right:auto">
    </div>
    
    </fieldset></div>
 </div>
 </form>
</body>
</HTML>

<% bdBean.DBclose(); %>
<%}%>