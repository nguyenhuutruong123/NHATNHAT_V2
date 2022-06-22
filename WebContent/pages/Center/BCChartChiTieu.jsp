<%@page import="java.util.Enumeration"%>
<%@page import="java.util.Hashtable"%>
<%@page import="java.sql.ResultSet"%>
<%@ page import="java.util.Calendar"%>
<%@ page import="java.util.Date"%>
<%@ page import="java.sql.SQLException"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@page import="geso.dms.center.beans.chart.IChart"%>
<%@ page  import = "geso.dms.center.util.*" %>
<%	
	IChart obj = (IChart) session.getAttribute("obj");	
	Hashtable<String, Long> data = (Hashtable<String, Long>)obj.getData();
	Hashtable<String, Long> dataHD = (Hashtable<String, Long>)obj.getNVHoatDong();
	
	String[] chuoingay=obj.getmangthang();
	
	String[] chuoisku=obj.getmangsku();
	String[] chuoitensku=obj.getmangTensku();
	ResultSet rs=obj.GetRsChiTieu();
	ResultSet rsDSSecYear=obj.GetRsDSSecYeer();
	ResultSet rsDSPriYear=obj.GetRsDSPriYeer();
	ResultSet rsDSSKU=obj.GetRsDSSKU();
	ResultSet rsTonKhoKV=obj.getRsTonKhoKhuVuc();
	
	ResultSet rsVung=obj.getVungRs();
	
	String[] chuoiidKhuvuc=obj.getmangIDKhuvuc();
	String[] chuoiTenKhuvuc=obj.getmangTenKhuvuc();
	
 %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd"> <% Utility Utilback = new Utility(); %>
<html>
<head>
<TITLE><%= getServletContext().getInitParameter("TITLENAME") %></TITLE>
<META http-equiv="Content-Type" content="text/html; charset=utf-8">
<META http-equiv="Content-Style-Type" content="text/css">
<LINK rel="stylesheet" type="text/css" media="print"
	href="../css/impression.css">
<LINK rel="stylesheet" href="../css/main.css" type="text/css">

<script type="text/javascript" src="../scripts/jquery-1.js"></script>
<link type="text/css" rel="stylesheet" href="../css/mybutton.css">
<LINK rel="stylesheet" type="text/css" href="../css/style.css" />
<script type="text/javascript" src="../scripts/dropdowncontent.js"></script>

<script type="text/javascript" src="../scripts/jquery.min.1.7.js"></script>
<link href="../css/jquery-ui.css" rel="stylesheet" type="text/css" />
<script src="../scripts/ui/jquery.ui.core.js" type="text/javascript"></script>
<script src="../scripts/ui/jquery.ui.widget.js" type="text/javascript"></script>
<script src="../scripts/ui/jquery.ui.datepicker.js"
	type="text/javascript"></script>
<script type="text/javascript">
	$(document).ready(function() {
		$(".days").datepicker({
			changeMonth : true,
			changeYear : true
		});
		$(".button").hover(function() {
			$(".button img").animate({
				top : "-10px"
			}, 200).animate({
				top : "-4px"
			}, 200) // first jump
			.animate({
				top : "-7px"
			}, 100).animate({
				top : "-4px"
			}, 100) // second jump
			.animate({
				top : "-6px"
			}, 100).animate({
				top : "-4px"
			}, 100); // the last jump
		});
	});
	
</script>

<script type="text/javascript" src="../scripts/Chart/jsapi"></script>

<script src="../scripts/Chart/jquery.gvChart-0.1.min.js" type="text/javascript"></script>
<script type="text/javascript">
	gvChartInit();
	

		
		jQuery(document).ready(function(){
			jQuery('#myTable').gvChart({
				chartType: 'ColumnChart',
				gvSettings: {
					vAxis: {title: 'Thực đạt & chỉ tiêu'},
					hAxis: {title: 'Tháng'},
					width: 600,
					height:300
					}
			});
		});


/* 		jQuery(document).ready(function(){
			jQuery('#myTable1').gvChart({
				chartType: 'LineChart',

				gvSettings: {
					vAxis: {title: 'Thực đạt Sec'},
					hAxis: {title: 'Năm Tháng'},
					width: 600,
					height:300
					}
			});
		});
		 */
		//---- CHƯA TÍNH ĐƯỢC "THỰC ĐẠT PRI " ----//

/* 		jQuery(document).ready(function(){
			jQuery('#myTable2').gvChart({
				chartType: 'LineChart',
				gvSettings: {
					vAxis: {title: 'Thực đạt Pri'},
					hAxis: {title: 'Năm Tháng'},
					width: 400,
					height:300
					}
			});
		}); */
		
/* 		jQuery(document).ready(function(){
			jQuery('#myTable3').gvChart({
				chartType: 'PieChart',
				gvSettings: {
					vAxis: {title: 'Sản Lượng SKU'},
					hAxis: {title: 'Nam'},
					width: 600,
					height:300
					}
			}); 
		}); */
		jQuery(document).ready(function(){
			jQuery('#myTable4').gvChart({
				chartType: 'PieChart',
				gvSettings: {
					vAxis: {title: 'Doanh thu theo khu vực'},
					hAxis: {title: 'Nam'},
					width: 600,
					height:300
					}
			});
		});
</script>
<style>
	body{
		text-align: center;
		font-family: Arial, sans-serif;
		font-size: 12px;
	}
	
	a{
		text-decoration: none;
		font-weight: bold;
		color: #555;
	}
	
	a:hover{
		color: #000;
	}
	
	div.main{
		/* margin: auto;
		*/
		text-align: left;
		width: 1200px;
	}
	
	.gvChart{
		/*border: 2px solid #850000;
		border-radius: 5px;
		-moz-border-radius: 10px;
		
			margin: auto;*/
		width: 500px;
		
	
		margin-left:0px;
		margin-top: 0px;
	}

</style>


<script language="javascript" type="text/javascript">
	function submitform()
	{
		document.forms['frm'].action.value = 'Taomoi';
		document.forms["frm"].submit();
	}
</script>
</head>
<BODY leftmargin="0" bottommargin="0" topmargin="0" rightmargin="0">
	<form name="frm" method="post" action="../../BCChartChiTieu">
		<input type="hidden" name="action" value='1'> 
		<input type="hidden" name="userId" value='<%= obj.getUserId() %>'>
			
		<div id="main" style="width: 99%; padding-left: 2px">
			<div align="left" id="header" style="width: 100%; float: none">
				<div style="width: 60%; padding: 5px; float: left"
					class="tbnavigation">Báo cáo quản trị > Báo cáo khác > Báo cáo Chart </div>
				<div align="right" style="padding: 5px" class="tbnavigation">
					<%=Utility.GLanguage("Chào mừng bạn",session,jedis) %>  <%= obj.getUserTen() %></div>
			</div>
			<div align="left" style="width: 100%; float: none; clear: left">
				<fieldset>
					<legend class="legendtitle"> Thông báo</legend>
					<textarea id="errors" name="errors" rows="1" style="width: 100%;text-align: left; color:#F00 ; font-weight:bold">
						<%= obj.getMsg() %></textarea>
				</fieldset>
			</div>
			<div align="left"
				style="width: 100%; float: none; clear: left;font-size: 0.7em">
				<fieldset>
					<legend class="legendtitle">Thực đạt & chỉ tiêu khu vực</legend>
					<div style="width: 100%; float: none" align="left">
						<div style="width: 100%; float: none" align="left" class="plainlabel">
							<TABLE width="70%" cellpadding="6" cellspacing="0">
								<TR>
								  	<TD class="plainlabel">Năm </TD>
							  	  	<TD class="plainlabel">
										<select name="nam" id = "nam" style="width :150px" >
										<option value= ""> </option>  
										<%
										Calendar cal = Calendar.getInstance();
										int year_ = cal.get(Calendar.YEAR);
										for(int n = 2010; n < year_ + 3; n++) {
										  if(obj.getNam().equals( Integer.toString(n)) ){									  
										%>
											<option value=<%= n %> selected="selected" > <%=n %></option> 
										<%
										  }else{
										 %>
											<option value=<%= n %> ><%=n %></option> 
										<% } }
										%> 
										</select>
							  	  	</TD>
							  	</TR>
								 <TR>
									<TD class="plainlabel" >Tháng </TD>
									<TD class="plainlabel">
										<select name="thang" id = "thang" style="width: 150px" >
										<option value= ""> </option>  
										<%
										int k=1;
										for(k=1; k <= 12; k++ ){
											
										  if(obj.getThang().equals(Integer.toString(k) ) ) {
										%>
											<option value=<%= k %> selected="selected" > <%= k %></option> 
										<%  }else{  %>
											<option value=<%= k %> > <%= k %></option> 
										<% } }%> 
										</select>
									</TD>
								</TR>
							
								<TR>
									<td colspan="4"><a class="button"
										href="javascript:submitform();"> <img style="top: -4px;" src="../images/button.png" alt=""> Tạo báo cáo </a></td>
								</TR>
							</TABLE>
						</div>
						<hr />
						
						<div class="main">
						<table >
						<tr >
							<%-- <td width="600px;">
							<table align="left" id='myTable1'>
								
									<caption align="left" >Thực đạt Sec So Với Năm Trước </caption>
								
									<thead>
										<tr>
											<th></th>
										<%  
												for(int j=11;j>=0;j--){ %>
													<th><%= chuoingay[j] %></th>
											<%  }%>         
										</tr>
									</thead>
									<p align="center" style="font-size: medium; font-weight: bold;">Doanh số</p>
									<tbody>
									<%while (rsDSSecYear.next()){ %>
										<tr>
											<th><%=rsDSSecYear.getString("type") %></th>
											<%  
												for(int j=11;j>=0;j--){ %>
													<td><%= rsDSSecYear.getDouble(chuoingay[j]) %></td>
											<%  }%>
										</tr>	
											<%} %>             
									</tbody>
								</table>
							
							</td> --%>
							<td width="600px;">
								<p align="center" style="font-size: medium; font-weight: bolder; ">DOANH SỐ</p>
								<table align="center">
									<tr><%-- 
										<td>Miền</td>
										<td>
											<select name="vung" style="width: 100px;">
												<option value=""></option>
												<%
												try{
													while(rsVung.next()){
														if(rsVung.getString("PK_SEQ").equals(obj.getVungRs())){
														%>	
														<option value="<%= rsVung.getString("PK_SEQ") %>" selected="selected"><%= rsVung.getString("DIENGIAI") %></option>
														<%}else{%>
														<option value="<%= rsVung.getString("PK_SEQ") %>"><%= rsVung.getString("DIENGIAI") %></option>
														<%}
													}
												}catch(Exception e){}
												%>
												
											</select>
										</td> --%>
									</tr>
									<tr>
										<td><%=Utility.GLanguage("Khu vực",session,jedis) %></td>
										<td></td>
									</tr>
									<tr>
										<td><%=Utility.GLanguage("Nhà phân phối",session,jedis) %></td>
										<td></td>
									</tr>
								</table>
							</td>
							<td width="600px;">
							
							<!-- Vẽ sơ đồ thực đạt & chỉ tiêu  -->
								<table align="left" id='myTable'>
								
									<caption align="left" >Thực đạt & chỉ tiêu  </caption>
								
									<thead>
										<tr>
											<th></th>
											<%  
												for(int j=11;j>=0;j--){ %>
													<th><%= chuoingay[j] %></th>
											<%  }%>            
										</tr>
									</thead>
									<tbody>
									<%while (rs.next()){ %>
										<tr>
											<th><%=rs.getString("type") %></th>
											<%  
												for(int j=11;j>=0;j--){ %>
													<td><%= rs.getDouble(chuoingay[j]) %></td>
											<%  }%>
										</tr>	
											<%} %>     
									</tbody>
								</table>
							</td>
						
							</tr>
						<tr>
								
								
								<td width="600px;">
								<table align="left" id='myTable4'>
								
									<caption align="left" >Doanh thu theo khu vực</caption>
								
									<thead>
										<tr>
											<th></th>
											<%  
												for(int j=0;j<=chuoiTenKhuvuc.length-1;j++){
												if( chuoiTenKhuvuc[j]!=null){
													System.out.println("kv:"+chuoiTenKhuvuc[j]);%>
													
													<th><%= chuoiTenKhuvuc[j] %></th>
											<% 
											}}
											%>             
										</tr>
									</thead>
									<tbody>
									<%while (rsTonKhoKV.next()){ %>
										<tr>
											<th><%=rsTonKhoKV.getString("type") %></th>
											<%  
											for(int j=0;j<=chuoiidKhuvuc.length-1;j++){
												if( chuoiidKhuvuc[j]!=null){
													System.out.println("kv:"+rsTonKhoKV.getDouble(chuoiidKhuvuc[j]));%>
													<td><%= rsTonKhoKV.getDouble(chuoiidKhuvuc[j]) %></td>
											<%
												}
											}%>
										</tr>	
											<%} %>            
									</tbody>
								</table>
								</td>
								
					<%-- 			<td width="600px;">
								<table align="left" id='myTable3'>
								
									<caption align="left" >Sản lượng SKU </caption>
								
									<thead>
										<tr>
											<th></th>
											<%  
												for(int j=0;j<=chuoitensku.length-1;j++){
												if( chuoitensku[j]!=null){
													%>
													<th><%= chuoitensku[j] %></th>
											<% 
											}}
											%>      
										</tr>
									</thead>
									<tbody>
										<%while (rsDSSKU.next()){ %>
										<tr>
											<th><%=rsDSSKU.getString("type") %></th>
											<%  
											for(int j=0;j<=chuoisku.length-1;j++){
												if( chuoisku[j]!=null){ %>
													<td><%= rsDSSKU.getDouble(chuoisku[j]) %></td>
											<%
												}
											}%>
										</tr>	
											<%} %>      
									</tbody>
								</table>
								</td>  --%>
							</tr>
							
							</table>	
							
								
							</div>
						
						<div class="main">
					<%-- 
								<table align="left" id='myTable1'>
								
									<caption align="left" >Thực đạt Sec So Với Năm Trước </caption>
								
									<thead>
										<tr>
											<th></th>
										<%  
												for(int j=11;j>=0;j--){ %>
													<th><%= chuoingay[j] %></th>
											<%  }%>         
										</tr>
									</thead>
									<tbody>
									<%while (rsDSSecYear.next()){ %>
										<tr>
											<th><%=rsDSSecYear.getString("type") %></th>
											<%  
												for(int j=11;j>=0;j--){ %>
													<td><%= rsDSSecYear.getDouble(chuoingay[j]) %></td>
											<%  }%>
										</tr>	
											<%} %>             
									</tbody>
								</table>
							 --%>
								
								
								
							</div>
						
						
						<div class="main">
				<%-- 				<table align="left" id='myTable2'>
								
									<caption align="left" >Thực đạt Pri So Với Năm Trước </caption>
								
									<thead>
										<tr>
											<th></th>
											<%  
												for(int j=11;j>=0;j--){ %>
													<th><%= chuoingay[j] %></th>
											<%  }%>           
										</tr>
									</thead>
									<tbody>
										<%while (rsDSPriYear.next()){ %>
										<tr>
											<th><%=rsDSPriYear.getString("type") %></th>
											<%  
												for(int j=11;j>=0;j--){ %>
													<td><%= rsDSPriYear.getDouble(chuoingay[j]) %></td>
											<%  }%>
										</tr>	
											<%} %>              
									</tbody>
								</table> --%>
							</div>
						
						
							<div class="main">
								<%-- <table align="left" id='myTable3'>
								
									<caption align="left" >Sản lượng SKU </caption>
								
									<thead>
										<tr>
											<th></th>
											<%  
												for(int j=0;j<=chuoitensku.length-1;j++){
												if( chuoitensku[j]!=null){
													%>
													<th><%= chuoitensku[j] %></th>
											<% 
											}}
											%>      
										</tr>
									</thead>
									<tbody>
										<%while (rsDSSKU.next()){ %>
										<tr>
											<th><%=rsDSSKU.getString("type") %></th>
											<%  
											for(int j=0;j<=chuoisku.length-1;j++){
												if( chuoisku[j]!=null){ %>
													<td><%= rsDSSKU.getDouble(chuoisku[j]) %></td>
											<%
												}
											}%>
										</tr>	
											<%} %>      
									</tbody>
								</table> --%>
							</div>
							
							<div class="main">
								<%-- <table align="left" id='myTable4'>
								
									<caption align="left" >Tồn kho theo khu vực</caption>
								
									<thead>
										<tr>
											<th></th>
											<%  
												for(int j=0;j<=chuoiTenKhuvuc.length-1;j++){
												if( chuoiTenKhuvuc[j]!=null){
													System.out.println("kv:"+chuoiTenKhuvuc[j]);%>
													
													<th><%= chuoiTenKhuvuc[j] %></th>
											<% 
											}}
											%>             
										</tr>
									</thead>
									<tbody>
									<%while (rsTonKhoKV.next()){ %>
										<tr>
											<th><%=rsTonKhoKV.getString("type") %></th>
											<%  
											for(int j=0;j<=chuoiidKhuvuc.length-1;j++){
												if( chuoiidKhuvuc[j]!=null){
													System.out.println("kv:"+rsTonKhoKV.getDouble(chuoiidKhuvuc[j]));%>
													<td><%= rsTonKhoKV.getDouble(chuoiidKhuvuc[j]) %></td>
											<%
												}
											}%>
										</tr>	
											<%} %>            
									</tbody>
								</table> --%>
							</div>
					</div>
				</fieldset>
			</div>
		</div>
	</form>
 <%
	if( data != null ) 
	{
		data.clear();
		data = null;
	}
	
	if(obj != null)
	{
		obj.DbClose();
		obj = null;
	}
	session.setAttribute("obj", null);
%> 
</body>
</HTML>