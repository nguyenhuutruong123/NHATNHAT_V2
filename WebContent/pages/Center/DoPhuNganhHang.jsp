<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page  import = "geso.dms.distributor.beans.bando.IBando" %>
<%@ page  import = "geso.dms.distributor.beans.bando.imp.Bando" %>
<%@ page  import = "java.sql.ResultSet" %>
<%@ page  import = "geso.dms.center.util.*" %>
<%@ page  import = "java.text.DecimalFormat" %>
<%@ page  import = "java.text.NumberFormat" %>

<%	
	NumberFormat formatter = new DecimalFormat("#,###,###");
	String userId = (String) session.getAttribute("userId");  
	String userTen = (String) session.getAttribute("userTen");  	
	String sum = (String) session.getAttribute("sum");
	Utility util = (Utility) session.getAttribute("util");
	if(!util.check(userId, userTen, sum)){
		response.sendRedirect(request.getContextPath() + "/redirect.jsp");
	}else{ %>

<% IBando bdBean = (IBando)session.getAttribute("obj"); %>
<% ResultSet chungloaiRs = bdBean.getChungloaiRs(); %>
<% ResultSet khList = bdBean.getKhChuaViengThamRs(); %>
<% ResultSet vungRs = bdBean.getVungRs(); %>
<% ResultSet kvRs = bdBean.getKhuvucRs(); %>
<% ResultSet nppRs = bdBean.getNppRs(); %>
<% ResultSet spRs = bdBean.getSanphamRs();
String url = util.getChuyenNguUrl("BandoSvl", "",session);%>

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
        
        <% if(bdBean.getTrungtam().length() > 0 ){ %>
        	center = new GLatLng(<%= bdBean.getTrungtam() %>)
        <% } else { %>
        	//center = new GLatLng(10.822031,106.630293); 
        	center = new GLatLng(10.79912,106.680259);
        <%} %>
        
        //map.addControl(new GNavLabelControl());
        
        //map.setCenter(new GLatLng(10.822031,106.630293), 14);
        map.setCenter(center, 14);
        map.setUIToDefault();
        
        // Create our "tiny" marker icon
        var blueIcon = new GIcon(G_DEFAULT_ICON);
        blueIcon.image = "../images/maps_human.png";
		blueIcon.iconSize = new GSize(39, 39);
		markerOptions = { icon:blueIcon };
		
		var ko_doanh_so = new GIcon(G_DEFAULT_ICON);
		ko_doanh_so.image = "../images/ko_doanh_so.png";
		ko_doanh_so.iconSize = new GSize(39, 39);
		marker_ko_ds = { icon:ko_doanh_so };
		
		var thang_truoc_co = new GIcon(G_DEFAULT_ICON);
		thang_truoc_co.image = "../images/thang_truoc_co.png";
		thang_truoc_co.iconSize = new GSize(39, 39);
		marker_thang_truoc_co = { icon:thang_truoc_co };
		
		var latKh = new Array();
		var marKh = new Array();
		
		<%
				if(khList != null)
				{
					int count = 0;
					while(khList.next())
					{
				%>  
			
			   latKh[<%= count %>] = new GLatLng(parseFloat('<%= khList.getString("lat").trim().replaceAll("'","") %>'), parseFloat('<%= khList.getString("long").trim().replaceAll("'","") %>') );      
			   <% if(khList.getFloat("doanhsotheonganh") > 0) { %>
			   		marKh[<%= count %>] = new GMarker(latKh[<%= count %>], marker_thang_truoc_co);
			   <%} else { %>
			   		marKh[<%= count %>] = new GMarker(latKh[<%= count %>], markerOptions);
			   <%} %>

		       GEvent.addListener(marKh[<%= count %>],"click", function()
		      	{
		    	    var info = '<b>Khách hàng: </b><%= khList.getString("khTen").trim().replaceAll("'","") %><br /><b>Số điện thoại: </b><%= khList.getString("dienthoai").trim().replaceAll("'","") %><br /><b>Doanh số tổng: </b><%= formatter.format(khList.getDouble("tbdoanhso")) %><br /><b>Doanh số theo ngành: </b><%= formatter.format(khList.getDouble("doanhsotheonganh")) %>';
					map.openInfoWindowHtml(new GLatLng(parseFloat('<%= khList.getString("lat").trim().replaceAll("'","") %>'), parseFloat('<%= khList.getString("long").trim().replaceAll("'","") %>') ), info);
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
    	        alert("Không thể tìn thấy địa chỉ:" + address);
    	      } 
    	      else 
    	      {
	    	        map.setCenter(point, 14);
	    	        var marker = new GMarker(point);
	    	        map.addOverlay(marker);
	
	    	        // As this is user-generated content, we display it as
	    	        // text rather than HTML to reduce XSS vulnerabilities.
	    	        marker.openInfoWindow(document.createTextNode("Địa chỉ: " + address));
	    	        document.getElementById("trungtam").value = point.lat() + "," + point.lng();
    	      }
    	    }
    	  );
    }
    
    </script>
    
    
	<LINK rel="stylesheet" type="text/css" media="print" href="../css/impression.css">
	<LINK rel="stylesheet" href="../css/main.css" type="text/css">
	<script type="text/javascript" src="../scripts/dropdowncontent.js"></script>

   	<script type="text/javascript" src="../scripts/jquery.min.1.7.js"></script>
	<link href="../css/jquery-ui.css" rel="stylesheet" type="text/css" />
	<script src="../scripts/ui/jquery.ui.core.js" type="text/javascript"></script>
	<script src="../scripts/ui/jquery.ui.widget.js" type="text/javascript"></script>
	<script src="../scripts/ui/jquery.ui.datepicker.js"	type="text/javascript"></script>
	
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
		    document.forms["bdForm"].submit();
		}
	  	
	  	function sellectAll()
		 {
			 var chkAll = document.getElementById("chkAll");
			 var nppIds = document.getElementsByName("nppLocIds");
			 
			 if(chkAll.checked)
			 {
				 for(i = 0; i < nppIds.length; i++)
				 {
					 nppIds.item(i).checked = true;
				 }
			 }
			 else
			 {
				 for(i = 0; i < nppIds.length; i++)
				 {
					 nppIds.item(i).checked = false;
				 }
			 }
		 }
	  	
	  	function sellectAll2()
		 {
			 var chkAll = document.getElementById("chkAll2");
			 var nppIds = document.getElementsByName("spIds");
			 
			 if(chkAll.checked)
			 {
				 for(i = 0; i < nppIds.length; i++)
				 {
					 nppIds.item(i).checked = true;
				 }
			 }
			 else
			 {
				 for(i = 0; i < nppIds.length; i++)
				 {
					 nppIds.item(i).checked = false;
				 }
			 }
		 }
	  	
  	</script>
</head>

<body onload="initialize()" onunload="GUnload()">
<form name="bdForm" method="post" action="../../BandoSvl">
<% redis.clients.jedis.Jedis jedis =  geso.dms.center.util.Utility.getJedis(); %><% geso.dms.center.util.Csrf csdr=new geso.dms.center.util.Csrf(request,response,true,false,true);%>
 <input type="hidden" name="<%=csdr.get_tokenName() %>" value='<%=csdr.get_tokenValue() %>'>

<input type="hidden" name="userId" value='<%= userId %>'>
<input type="hidden" name="nppId" value='<%= bdBean.getNppId() %>'>
<input type="hidden" name="action" value='1'>
<input type="hidden" name="view" value='dophuTT'>
<input type="hidden" name="trungtam" id="trungtam" value=''>

<div id="main" style="width:99.5%; padding-left:2px">

	<div align="left" id="header" style="width:100%; float:none">
    	<div style="width:40%; padding:5px; float:left" class="tbnavigation">
        	<%=url %>
        </div>
        <div align="right" style="padding:5px" class="tbnavigation">
        	<%=Utility.GLanguage("Chào mừng bạn",session,jedis) %>  <%= userTen %> &nbsp;
        </div>
    </div>
    
  	<div align="left" id="button" style="width:100%; height:32px; padding:0px; float:none" class="tbdarkrow">
    	<A href= "../../TratrungbaySvl?userId=UserID" >
        	<img src="../images/Back30.png" alt="Quay về"  title="Quay về" border="1px" longdesc="Quay về" style="border-style:outset"></A>
    </div>
    
    <div style="width:100%; float:none" align="left">
    <fieldset>
    	<legend class="legendtitle"><%=Utility.GLanguage("Độ phủ ngành hàng",session,jedis) %> </legend>
        <TABLE width="100%" cellpadding="4" cellspacing="0">								   
            <TR>
                <TD class="plainlabel" width="150" ><%=Utility.GLanguage("Từ ngày",session,jedis) %> </TD>
                <TD class="plainlabel">
                    <input type="text" size="11" class="days" 
                          id="ngay" name="ngay" value="<%= bdBean.getDate() %>" maxlength="10" readonly onchange="submitform()"  />
                </TD>
                <TD class="plainlabel" width="150" ><%=Utility.GLanguage("Đến ngày",session,jedis) %> </TD>
                <TD class="plainlabel">
                    <input type="text" size="11" class="days" 
                          id="denngay" name="denngay" value="<%= bdBean.getDenngay() %>" maxlength="10" readonly onchange="submitform()" />
                </TD>
            </TR>
            <TR>
                <TD class="plainlabel" ><%=Utility.GLanguage("Vùng",session,jedis) %> </TD>
                <TD class="plainlabel">
                    <select name="vungId" id="vungId" onChange = "submitform();">
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
                <TD class="plainlabel" ><%=Utility.GLanguage("Khu vực",session,jedis) %> </TD>
                <TD class="plainlabel">
                    <select name="kvId" id="kvId" onChange = "submitform();">
                            <option value="" selected></option>
                            <% if(kvRs != null){
								  try{ while(kvRs.next()){ 
					    			if(kvRs.getString("pk_seq").equals(bdBean.getKhuvucId())){ %>
					      				<option value='<%= kvRs.getString("pk_seq")%>' selected><%= kvRs.getString("ten") %></option>
					      			<%}else{ %>
					     				<option value='<%= kvRs.getString("pk_seq")%>'><%= kvRs.getString("ten") %></option>
					     	<%}} kvRs.close(); }catch(java.sql.SQLException e){} }%>
                        </select>    
                </TD>
            </TR>
            <TR>
            	<TD class="plainlabel" ><%=Utility.GLanguage("Nhà phân phối",session,jedis) %> </TD>
                <TD class="plainlabel">
                    
                   		<a href="" id="nppId" rel="subcontentNpp">
        	 							&nbsp; <img alt="Chọn Chi nhánh / NPP" src="../images/vitriluu.png"></a>
        	 		
                        <DIV id="subcontentNpp" style="position:absolute; visibility: hidden; border: 9px solid #80CB9B;
		                             background-color: white; width: 590px; max-height:300px; overflow:auto; padding: 4px; z-index: 100000000; ">
		                    <table width="90%" align="center">
		                        <tr>
		                            <th width="100px" style="font-size: 12px"><%=Utility.GLanguage("Mã NPP",session,jedis) %></th>
		                            <th width="350px" style="font-size: 12px"><%=Utility.GLanguage("Tên NPP",session,jedis) %></th>
		                            <th width="100px" align="center" style="font-size: 12px"><%=Utility.GLanguage("Chọn hết",session,jedis) %> <input type="checkbox" onchange="sellectAll()" id="chkAll" ></th>
		                        </tr>
                            	<%
	                        		if(nppRs != null)
	                        		{
	                        			while(nppRs.next())
	                        			{  %>
	                        			
	                        			<tr>
	                        				<td><input style="width: 100%" value="<%= nppRs.getString("ma") %>"></td>
	                        				<td><input style="width: 100%" value="<%= nppRs.getString("ten") %>"></td>
	                        				<td align="center">
	                        				<% if(bdBean.getNppLocId().indexOf(nppRs.getString("pk_seq")) >= 0 ){ %>
	                        					<input type="checkbox" name="nppLocIds" checked="checked" value="<%= nppRs.getString("pk_seq") %>">
	                        				<%}else{ %>
	                        					<input type="checkbox" name="nppLocIds" value="<%= nppRs.getString("pk_seq") %>">
	                        				<%} %>
	                        				</td>
	                        			</tr>
	                        			
	                        		 <% } nppRs.close(); } %>
		                    </table>
		                     <div align="right">
		                     	<label style="color: red" ></label>
		                     	&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
		                     	<a href="javascript:dropdowncontent.hidediv('subcontentNpp')" onclick="submitform();" >Hoàn tất</a>
		                     </div>
		            </DIV>   
                          
                </TD>
                <TD class="plainlabel" > <%=Utility.GLanguage("Ngành hàng",session,jedis) %></TD>
                <TD class="plainlabel">
                    <select name="chungloai" id="chungloai" onChange = "submitform();">
                            <option value="" selected></option>
                            <% if(chungloaiRs != null){
								  try{ while(chungloaiRs.next()){ 
					    			if(chungloaiRs.getString("pk_seq").equals(bdBean.getChungloaiId())){ %>
					      				<option value='<%= chungloaiRs.getString("pk_seq")%>' selected><%= chungloaiRs.getString("ten") %></option>
					      			<%}else{ %>
					     				<option value='<%= chungloaiRs.getString("pk_seq")%>'><%= chungloaiRs.getString("ten") %></option>
					     	<%}} chungloaiRs.close(); }catch(java.sql.SQLException e){} }%>
                        </select>    
                </TD>
            </TR>
			<tr>
                <TD class="plainlabel" ><%=Utility.GLanguage("Sản phẩm",session,jedis) %> </TD>
                <TD class="plainlabel">
                    
                     <a href="" id="spId" rel="subcontentSp">
        	 							&nbsp; <img alt="Chọn sản phẩm" src="../images/vitriluu.png"></a>
        	 		
                        <DIV id="subcontentSp" style="position:absolute; visibility: hidden; border: 9px solid #80CB9B;
		                             background-color: white; width: 590px; max-height:300px; overflow:auto; padding: 4px; z-index: 100000000; ">
		                    <table width="90%" align="center">
		                        <tr>
		                            <th width="100px" style="font-size: 12px"> <%=Utility.GLanguage("Mã",session,jedis) %></th>
		                            <th width="350px" style="font-size: 12px"> <%=Utility.GLanguage("Tên",session,jedis) %></th>
		                            <th width="100px" align="center" style="font-size: 12px"><%=Utility.GLanguage("Chọn hết",session,jedis) %> <input type="checkbox" onchange="sellectAll2()" id="chkAll2" ></th>
		                        </tr>
                            	<%
	                        		if(spRs != null)
	                        		{
	                        			while(spRs.next())
	                        			{  %>
	                        			
	                        			<tr>
	                        				<td><input style="width: 100%" value="<%= spRs.getString("ma") %>"></td>
	                        				<td><input style="width: 100%" value="<%= spRs.getString("ten") %>"></td>
	                        				<td align="center">
	                        				<% if(bdBean.getSanphamId().indexOf(spRs.getString("pk_seq")) >= 0 ){ %>
	                        					<input type="checkbox" name="spIds" checked="checked" value="<%= spRs.getString("pk_seq") %>">
	                        				<%}else{ %>
	                        					<input type="checkbox" name="spIds" value="<%= spRs.getString("pk_seq") %>">
	                        				<%} %>
	                        				</td>
	                        			</tr>
	                        			
	                        		 <% } spRs.close(); } %>
		                    </table>
		                     <div align="right">
		                     	<label style="color: red" ></label>
		                     	&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
		                     	<a href="javascript:dropdowncontent.hidediv('subcontentSp')" onclick="submitform();" >Hoàn tất</a>
		                     </div>
		            </DIV>   
                        
                        
                </TD>
                
                <TD class="plainlabel" ><%=Utility.GLanguage("Đoạn đường",session,jedis) %> </TD>
                <TD class="plainlabel">
                    <input type="text" name="address" value="<%= bdBean.getAddress() %>" onchange="showAddress(this.value)" >  
               </TD>
            </TR>
        </TABLE>
     	<hr>
    
	<div id="map_canvas" style="width: 100%; height: 650px; position:static; margin-left:auto; margin-right:auto">
    </div>
    
    </fieldset></div>
 </div><%geso.dms.center.util.Utility.JedisClose(jedis); %>
 </form>
 
 <script type="text/javascript">
	dropdowncontent.init('nppId', "right-bottom", 300, "click");
	dropdowncontent.init('spId', "right-bottom", 300, "click");
 </script>
</body>
</HTML>

<% bdBean.DBclose(); %>
<%}%>