
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.Calendar"%>
<%@ page import="java.util.Date"%>
<%@ page import="java.sql.SQLException"%>
<%@ page import = "java.util.Iterator" %>

<%@ page import = "java.util.List" %>
<%@ page import = "java.util.ArrayList" %>
<%@ page import = "java.sql.ResultSet" %>
<%@ page import = "geso.dms.center.beans.bcchartdoanhthursm.*" %>
<%@ page import = "geso.dms.center.util.*" %>
<%@ page  import = "java.text.DecimalFormat" %>
<%@ page  import = "java.text.NumberFormat" %>
<%	String userId = (String) session.getAttribute("userId");
	String userTen = (String) session.getAttribute("userTen");
	String sum = (String) session.getAttribute("sum");
	Utility util = (Utility) session.getAttribute("util");
	if(!util.check(userId, userTen, sum)){
		response.sendRedirect("/opv/index.jsp");
	}else{ 

		IBcchartdoanhthursm obj =(IBcchartdoanhthursm)session.getAttribute("obj");
	ResultSet rs=obj.getRs();
	
	String[] arrIdNSP=obj.getArrIDNSP();
	String[] arrTenNSP=obj.getArrTenNSP();
	ResultSet Rsm = obj.getRsmRs();
	ResultSet Rsm2Rs = obj.getRms2Rs();
	String[] Thangdl2 = obj.getThangrms2DL();
	Long[] DoanhthuTB = obj.getDoanhthuTB();
	Long[] DoanhthuRsm1 = obj.getDoanhthuRsm1();
	Long[] DoanhthuRsm2 = obj.getDoanhthuRsm2();
	
	Long[] SokhRsm1 = obj.getSoKHRsm1();
	Long[] SokhRsm2 = obj.getSoKHRsm2();
	
	NumberFormat formatter = new DecimalFormat("#,###,###");
	NumberFormat formatter2 = new DecimalFormat("#,###,###.####"); 
%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd"> <% Utility Utilback = new Utility(); %>
<html>
<head>
	<TITLE><%= getServletContext().getInitParameter("TITLENAME") %></TITLE>
	<META http-equiv="Content-Type" content="text/html; charset=utf-8">
	<META http-equiv="Content-Style-Type" content="text/css">
	<LINK rel="stylesheet" href="../css/main.css" type="text/css">
	<LINK rel="stylesheet" href="../css/calendar.css" type="text/css">
	<LINK rel="stylesheet" type="text/css" media="print" href="../css/impression.css">
	<script type="text/javascript" language="JavaScript" src="../scripts/Numberformat.js"></script>
	<link type="text/css" rel="stylesheet" href="../css/mybutton.css">

	<script type="text/javascript" src="../scripts/jquery.min.1.7.js"></script>
	<link href="../css/jquery-ui.css" rel="stylesheet" type="text/css" />
	<script src="../scripts/ui/jquery.ui.core.js" type="text/javascript"></script>
	<script src="../scripts/ui/jquery.ui.widget.js" type="text/javascript"></script>
	<script src="../scripts/ui/jquery.ui.datepicker.js" type="text/javascript"></script>
	<script type="text/javascript">
		$(document).ready(function() {
			$( ".days" ).datepicker({
				changeMonth: true,
				changeYear: true
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
					vAxis: {title: 'Doanh thu RSM (Đơn vị tính: Triệu đồng) '},
					hAxis: {title: 'Ngày'},
					width: 2000,
					height:700,
					seriesType: 'bars',
				    series: {2: {type: 'line'}},
				    tooltip: {format: 'currency'}
				        
					}
			});
			jQuery('#myTable1').gvChart({
				chartType: 'ColumnChart',
				gvSettings: {
					vAxis: {title: 'Số khách hàng RSM (Đơn vị tính: Người) '},
					hAxis: {title: 'Ngày'},
					width: 2000,
					height:700,
					seriesType: 'bars',
				    series: {2: {type: 'line'}},
				    tooltip: {
			            pointFormat: '{series.name}: <b>{point.y}</b><br/>',
			            valueSuffix: ' cm',
			            shared: true
			        },

			        series: [{
			            name: 'dogs',
			            data: [29.9, 71.5, 106.4, 129.2, 144.0, 176.0, 135.6, 148.5, 216.4, 194.1, 95.6, 54.4,29.9, 71.5, 106.4, 129.2, 144.0, 176.0, 135.6, 148.5, 216.4, 194.1, 95.6, 54.4,95.6, 54.4]
			        }, {
			            name: 'cats',
			            data: [29.9, 71.5, 106.4, 129.2, 144.0, 176.0, 135.6, 148.5, 216.4, 194.1, 95.6, 54.4,29.9, 71.5, 106.4, 129.2, 144.0, 176.0, 135.6, 148.5, 216.4, 194.1, 95.6, 54.4,95.6, 54.4].reverse()
			        }]
				}
			});
			
			jQuery('#myTable2').gvChart({
				chartType: 'ColumnChart',
				gvSettings: {
					vAxis: {title: 'Tổng doanh số và số khách hàng'},
					hAxis: {title: 'Ngày'},
					width: 2000,
					height:700,
					seriesType: 'bars',
				    series: {2: {type: 'line'}},
				    tooltip: {
			            pointFormat: '{series.name}: <b>{point.y}</b><br/>',
			            valueSuffix: ' cm',
			            shared: true
			        },

			        series: [{
			            name: 'dogs',
			            data: [29.9, 71.5, 106.4, 129.2, 144.0, 176.0, 135.6, 148.5, 216.4, 194.1, 95.6, 54.4,29.9, 71.5, 106.4, 129.2, 144.0, 176.0, 135.6, 148.5, 216.4, 194.1, 95.6, 54.4,95.6, 54.4]
			        }, {
			            name: 'cats',
			            data: [29.9, 71.5, 106.4, 129.2, 144.0, 176.0, 135.6, 148.5, 216.4, 194.1, 95.6, 54.4,29.9, 71.5, 106.4, 129.2, 144.0, 176.0, 135.6, 148.5, 216.4, 194.1, 95.6, 54.4,95.6, 54.4].reverse()
			        }]
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
	<SCRIPT language="javascript" type="text/javascript">


	function submitform()
	{
		document.forms["khtt"].submit();
	}


	</SCRIPT>
<link href="../css/select2.css" rel="stylesheet" />
<script src="../scripts/select2.js"></script>
<script>
$(document).ready(function()
{
	$("#rsm1").select2();
	$("#rsm2").select2();

});
</script>
</head>
<BODY leftmargin="0" bottommargin="0" topmargin="0" rightmargin="0">
<form name="khtt" method="post" action="../../BCChartdoanhthursmSvl">
<input type="hidden" name="userId" value="<%= userId %>" >
<input type="hidden" name="action" value="1" >
<TABLE width="100%" border="0" cellspacing="0" cellpadding="0" height="100%">
	<TR>
		<TD colspan="4" align="left" valign="top" bgcolor="#FFFFFF">
			<TABLE width="100%" cellpadding="0" cellspacing="2">
				<TR>
					<TD align="left" class="tbnavigation">
						<TABLE width="100%" border="0" cellpadding="0" cellspacing="0">
							<TR height="22">
								<TD align="left" colspan="2" class="tbnavigation">&nbsp;Báo cáo quản trị > Biểu đồ mới > Báo cáo doanh thu RSM</TD>
								<TD colspan="2" align="right" class="tbnavigation">Chào mừng bạn <%= userTen %></TD>
							</TR>
						</TABLE>
					</TD>
				</TR>
			</TABLE>
			<TABLE width="100%" cellpadding="0" cellspacing="1">
				<TR>
					<TD>
						<TABLE border="0" width="100%" cellpadding="0" cellspacing="0">
							<TR>
								<TD width="100%" align="left" >
									<FIELDSET>
										<LEGEND class="legendtitle">&nbsp;Phạm vi báo cáo &nbsp;</LEGEND>

										<TABLE width="100%" cellpadding="6" cellspacing="0">
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
								  	<TD class="plainlabel" >Năm </TD>
							  	  	<TD class="plainlabel" >
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
									<TD class="plainlabel">Chọn Rsm 1 </TD>
									<TD class="plainlabel">
										<select name="rsm1" id="rsm1" style="width:250px" class = "select2"  >
											<option value="" ></option>
											<%if (Rsm != null)
													while (Rsm.next()) {
														if (Rsm.getString("pk_seq").equals(obj.getRsmId1())) {
														
														%>
											   <option value="<%=Rsm.getString("pk_seq")%>" selected><%=Rsm.getString("ten")%></option>
											   <%} else {%>
											   <option value="<%=Rsm.getString("pk_seq")%>"><%=Rsm.getString("ten")%></option>
											<%}}%>
										</select>
									</TD>
									<TD class="plainlabel">Chọn Rsm 2 </TD>
									<TD class="plainlabel">
										<select name="rsm2" id="rsm2" style="width:250px" class = "select2"  >
											<option value="" ></option>
											<%if (Rsm != null)
													while (Rsm.previous()) {
														if (Rsm.getString("pk_seq").equals(obj.getRsmId2())) {%>
											   <option value="<%=Rsm.getString("pk_seq")%>" selected><%=Rsm.getString("ten")%></option>
											   <%} else {%>
											   <option value="<%=Rsm.getString("pk_seq")%>"><%=Rsm.getString("ten")%></option>
											<%}}%>
										</select>
									</TD>
								<TR>
									<TD class="plainlabel" colspan="6">																			
									<a class="button"
										href="javascript:submitform();"> <img style="top: -4px;"
											src="../images/button.png" alt=""> Tạo báo cáo
									</a>
									</td>
								</TR>
							</TABLE>
									</FIELDSET>
								</TD>
							</TR>
						</TABLE>
					</TD>
				</TR>

				<TR>
					<TD width="100%">
						<FIELDSET>
							<LEGEND class="legendtitle">&nbsp;Báo cáo doanh thu RSM &nbsp;&nbsp;
								
							</LEGEND>

							<TABLE class="" width="100%">
								
								<table align="left" id='myTable' >
								
									<caption align="left" >Doanh thu RSM (Đơn vị tính: Triệu đồng)</caption>
								
									<thead>
										<tr>
											<th></th>
											<%  //In ra List ngày theo tháng
												for(int i= 1;i < arrTenNSP.length;i++)
												{
													if( arrTenNSP[i]!=null){
													%>
													<th><%= arrTenNSP[i] %></th>
											<% 		}
												}
											%>             
										</tr>
									</thead>
									<tbody>
								
										<tr>
											<th>Doanh số Rsm1</th>
											<%  
											for(int j= 1;j< DoanhthuRsm1.length;j++)
											{
												
													%>
													<td><%=  DoanhthuRsm1[j]%></td>
											<%
												
											}%>
										</tr>	
							        
								
										<tr>
											<th>Doanh số Rsm2</th>
											<%  
											for(int j=1;j< DoanhthuRsm2.length;j++)
											{
												
													%>
													<td><%= DoanhthuRsm2[j]%></td>
											<%
												
											}%>
										</tr>
									</tbody>
								</table>
							<table align="left" id='myTable1' >
								
									<caption align="left" >Khách hàng theo RSM</caption>
								
									<thead>
										<tr>
											<th></th>
											<%  //In ra List ngày theo tháng
												for(int i= 1;i < arrTenNSP.length;i++)
												{
													if( arrTenNSP[i]!=null){
													%>
													<th><%= arrTenNSP[i] %></th>
											<% 		}
												}
											%>             
										</tr>
									</thead>
									<tbody>
								
										<tr>
											<th>Số khách hàng Rsm1</th>
											<%  
											for(int j= 1;j< SokhRsm1.length;j++)
											{
												
													%>
													<td><%=  SokhRsm1[j]%></td>
											<%
												
											}%>
										</tr>	
							        
								
										<tr>
											<th>Số khách hàng Rsm2</th>
											<%  
											for(int j=1;j< SokhRsm2.length;j++)
											{
												
													%>
													<td><%= SokhRsm2[j]%></td>
											<%
												
											}%>
										</tr>
									</tbody>
								</table>
								
								<table align="left" id='myTable2' >
								
									<caption align="left" >Tổng doanh số và khách hàng của 2 RSM</caption>
								
									<thead>
										<tr>
											<th></th>
											<%  //In ra List ngày theo tháng
												for(int i= 1;i < arrTenNSP.length;i++)
												{
													if( arrTenNSP[i]!=null){
													%>
													<th><%= arrTenNSP[i] %></th>
											<% 		}
												}
											%>             
										</tr>
									</thead>
									<tbody>
								
										<tr>
											<th>Tổng số khách hàng</th>
											<%  
											for(int j= 1;j< SokhRsm1.length;j++)
											{
												if(SokhRsm1[j] == null) SokhRsm1[j] = (long)0;
												if(SokhRsm2[j] == null) SokhRsm2[j] = (long)0;
													%>
													<td><%= (SokhRsm1[j]+SokhRsm2[j]) %></td>
											<%
												
											}%>
										</tr>	
							        
								
										<tr>
											<th>Tổng doanh số</th>
											<%  
											for(int j=1;j< DoanhthuRsm1.length;j++)
											{
												if(DoanhthuRsm1[j] == null) DoanhthuRsm1[j] = (long)0;
												if(DoanhthuRsm2[j] == null) DoanhthuRsm2[j] = (long)0;
													%>
													<td><%=(DoanhthuRsm1[j]+DoanhthuRsm2[j]) %></td>
											<%
												
											}%>
										</tr>
									</tbody>
								</table>
							</TABLE>
						</FIELDSET>
					</TD>
				</TR>
			</TABLE>
		</TD>
	</TR>
</TABLE>
</form>
</BODY>
</HTML>

<%
		 if(rs != null)
			rs.close(); 

		if(obj != null)
		{
			obj.DbClose();
			obj = null;
		}
		session.setAttribute("obj", null);
	}	
%>
