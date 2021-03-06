<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.Calendar"%>
<%@ page import="java.util.Date"%>
<%@ page import="java.sql.SQLException"%>
<%@ page import = "java.util.Iterator" %>
<%@ page import = "java.util.List" %>
<%@ page import = "java.util.ArrayList" %>
<%@ page import = "java.sql.ResultSet" %>
<%@ page import = "geso.dms.center.beans.bcchart.*" %>
<%@ page import = "geso.dms.center.util.*" %>
<%@ page  import = "java.text.DecimalFormat" %>
<%@ page  import = "java.text.NumberFormat" %>

<%	String userId = (String) session.getAttribute("userId");
	String userTen = (String) session.getAttribute("userTen");
	String sum = (String) session.getAttribute("sum");
	Utility util = (Utility) session.getAttribute("util");
	if(!util.check(userId, userTen, sum)){
		response.sendRedirect("/Trafaco/index.jsp");
	}else
	{ 
		IBcchart obj =(IBcchart)session.getAttribute("obj");
	
		ResultSet rsTonKhoDiaBan = obj.getRsTonKhoDiaBan();
		ResultSet rsTonKhoMien = obj.getRsTonKhoMien();
		ResultSet rsDoanhSoDiaBan = obj.getRsDoanhSoDiaBan();
		ResultSet rsDoanhSoMien = obj.getRsDoanhSoMien();
		ResultSet rsDoanhSoNSP = obj.getRsDoanhSoNSP();
		ResultSet rsDoanhThuDiaBan = obj.getRsDoanhThuDiaBan();
		ResultSet rsDoanhThuMien = obj.getRsDoanhThuMien();
		ResultSet rsDoanhSoSPMoi = obj.getRsDoanhSoSPMoi();
		ResultSet rsDoanhSoSPChuLuc= obj.getRsDoanhSoSPChuLuc();
		
		String[] arrIdDiaBan = obj.getArrIDDiaBan();
		String[] arrTenDiaBan = obj.getArrTenDiaBan();
		String[] arrIdMien = obj.getArrIDMien();
		String[] arrTenMien = obj.getArrTenMien();
		String[] arrIdNSP = obj.getArrIDNSP();
		String[] arrTenNSP = obj.getArrTenNSP();
		String[] arrIdSpChuLuc = obj.getArrIDSanPhamChuLuc();
		String[] arrTenSpChuLuc = obj.getArrTenSanPhamChuLuc();
		String[] arrIdSpMoi = obj.getArrIDSanPhamMoi();
		String[] arrTenSpMoi = obj.getArrTenSanPhamMoi();
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
			jQuery('#chartDSDiaBan').gvChart({
				chartType: 'PieChart',
				gvSettings: {
					vAxis: {title: 'Doanh s??? theo ?????a b??n (????n v??? t??nh: Tri???u ?????ng)'},
					hAxis: {title: 'T???nh th??nh'},
					width: 700,
					height:500
					}
			});			
		});
		
		jQuery(document).ready(function(){
			jQuery('#chartDSMien').gvChart({
				chartType: 'PieChart',
				gvSettings: {
					vAxis: {title: 'Doanh s??? theo Mi???n (????n v??? t??nh: Tri???u ?????ng)'},
					hAxis: {title: 'Mi???n'},
					width: 700,
					height:500
					}
			});
		});
		
		jQuery(document).ready(function(){
			jQuery('#chartDSNSP').gvChart({
				chartType: 'PieChart',
				gvSettings: {
					vAxis: {title: 'Doanh s??? theo Nh??m s???n ph???m (????n v??? t??nh: Tri???u ?????ng)'},
					hAxis: {title: 'Nh??m s???n ph???m'},
					width: 700,
					height:500
					}
			});			
		});
		
		jQuery(document).ready(function(){
			jQuery('#chartDTDiaBan').gvChart({
				chartType: 'PieChart',
				gvSettings: {
					vAxis: {title: 'Doanh thu theo ?????a b??n (????n v??? t??nh: Tri???u ?????ng)'},
					hAxis: {title: 'T???nh th??nh'},
					width: 700,
					height:500
					}
			});			
		});
		
		jQuery(document).ready(function(){
			jQuery('#chartDTMien').gvChart({
				chartType: 'PieChart',
				gvSettings: {
					vAxis: {title: 'Doanh thu theo Mi???n (????n v??? t??nh: Tri???u ?????ng)'},
					hAxis: {title: 'Mi???n'},
					width: 700,
					height:500
					}
			});			
		});
		
		jQuery(document).ready(function(){
			jQuery('#chartTKDiaBan').gvChart({
				chartType: 'PieChart',
				gvSettings: {
					vAxis: {title: 'T???n kho theo ?????a b??n (????n v??? t??nh: S???n ph???m)'},
					hAxis: {title: 'T???nh th??nh'},
					width: 700,
					height:500
					}
			});			
		});
		
		jQuery(document).ready(function(){
			jQuery('#chartTKMien').gvChart({
				chartType: 'PieChart',
				gvSettings: {
					vAxis: {title: 'T???n kho theo Mi???n (????n v??? t??nh: S???n ph???m)'},
					hAxis: {title: 'Mi???n'},
					width: 700,
					height:500
					}
			});			
		});
		
		jQuery(document).ready(function(){
			jQuery('#chartDSSPChuLuc').gvChart({
				chartType: 'PieChart',
				gvSettings: {
					vAxis: {title: 'Doanh s??? theo S???n ph???m ch??? l???c (????n v??? t??nh: Tri???u ?????ng)'},
					hAxis: {title: 'S???n ph???m'},
					width: 700,
					height:500
					}
			});			
		});
		
		jQuery(document).ready(function(){
			jQuery('#chartDSSPMoi').gvChart({
				chartType: 'PieChart',
				gvSettings: {
					vAxis: {title: 'Doanh s??? theo S???n ph???m m???i (????n v??? t??nh: Tri???u ?????ng)'},
					hAxis: {title: 'S???n ph???m'},
					width: 700,
					height:500
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
</head>
<BODY leftmargin="0" bottommargin="0" topmargin="0" rightmargin="0">
<form name="khtt" method="post" action="../../BCChartSvl">
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
								<TD align="left" colspan="2" class="tbnavigation">&nbsp;B??o c??o qu???n tr??? > Bi???u ????? > B??o c??o bi???u ????? </TD>
								<TD colspan="2" align="right" class="tbnavigation">Cha??o m????ng ba??n <%= userTen %></TD>
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
										<LEGEND class="legendtitle">&nbsp;Ph???m vi b??o c??o &nbsp;</LEGEND>

										<TABLE width="100%" cellpadding="6" cellspacing="0">
								<TR>
								  	<TD class="plainlabel" >N??m </TD>
							  	  	<TD class="plainlabel" >
										<select name="nam" id = "nam" style="width :150px" onchange="submitform()" >
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
									<TD class="plainlabel" >Th??ng </TD>
									<TD class="plainlabel">
										<select name="thang" id = "thang" style="width: 150px" onchange="submitform()">
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
									<TD class="plainlabel" colspan="2">																			
									<a class="button"
										href="javascript:submitform();"> <img style="top: -4px;"
											src="../images/button.png" alt=""> T???o b??o c??o
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
				
				<!-- ################################################## B??O C??O DOANH S??? ##################################################### -->
				
				<TR>
					<TD width="100%">
						
						<FIELDSET>
							<LEGEND class="legendtitle">&nbsp;B??o c??o doanh s??? &nbsp;&nbsp;
								
							</LEGEND>

							<TABLE class="" width="100%">
								<TR>
								<TD>
									<!-- ################################################## DOANH S??? ?????A B??N ##################################################### -->
									
									<table align="left" id='chartDSDiaBan' >
									
										<caption align="left" >Doanh s??? theo ?????a b??n (????n v??? t??nh: Tri???u ?????ng) </caption>
									
										<thead>
											<tr>
												<th></th>
												<%  //In ra List ?????a b??n
													for(int i=0;i<arrTenDiaBan.length;i++)
													{
														if( arrTenDiaBan[i]!=null){
														%>
														<th><%= arrTenDiaBan[i] %></th>
												<% 		}
													}
												%>             
											</tr>
										</thead>
										<tbody>
										<%while (rsDoanhSoDiaBan.next()){ %>
											<tr>
												<th><%=rsDoanhSoDiaBan.getString("type") %></th>
												<%  
												for(int j=0;j<arrIdDiaBan.length;j++)
												{
													if( arrIdDiaBan[j]!=null){
														%>
														<td><%= rsDoanhSoDiaBan.getString(arrIdDiaBan[j])%></td>
												<%
													}
												}%>
											</tr>	
									<%} %>            
										</tbody>
									</table>
								</TD>
								<TD>
								<!-- ################################################## DOANH S??? MI???N ##################################################### -->
								
								<table align="left" id='chartDSMien' >
								
									<caption align="left" >Doanh s??? theo Mi???n (????n v??? t??nh: Tri???u ?????ng) </caption>
								
									<thead>
										<tr>
											<th></th>
											<%  //In ra List ?????a b??n
												for(int i=0;i<arrTenMien.length;i++)
												{
													if( arrTenMien[i]!=null){
													%>
													<th><%= arrTenMien[i] %></th>
											<% 		}
												}
											%>             
										</tr>
									</thead>
									<tbody>
									<%while (rsDoanhSoMien.next()){ %>
										<tr>
											<th><%=rsDoanhSoMien.getString("type") %></th>
											<%  
											for(int j=0;j<arrIdMien.length;j++)
											{
												if( arrIdMien[j]!=null){
													%>
													<td><%= rsDoanhSoMien.getString(arrIdMien[j])%></td>
											<%
												}
											}%>
										</tr>	
								<%} %>            
									</tbody>
								</table>
								</TD>	
								</TR>
								
								<TR>
									<TD>
										<!-- ################################################## DOANH S??? NSP ##################################################### -->
										
										<table align="left" id='chartDSNSP' >
										
											<caption align="left" >Doanh s??? theo Nh??m s???n ph???m (????n v??? t??nh: Tri???u ?????ng) </caption>
										
											<thead>
												<tr>
													<th></th>
													<%  //In ra List NSP
														for(int i=0;i<arrTenNSP.length;i++)
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
											<%while (rsDoanhSoNSP.next()){ %>
												<tr>
													<th><%=rsDoanhSoNSP.getString("type") %></th>
													<%  
													for(int j=0;j<arrTenNSP.length;j++)
													{
														if( arrTenNSP[j]!=null){
															%>
															<td><%= rsDoanhSoNSP.getString(arrIdNSP[j])%></td>
													<%
														}
													}%>
												</tr>	
										<%} %>            
											</tbody>
										</table>
									</TD>
								</TR>
								<TR>
									<TD>
										<!-- ################################################## DOANH S??? S???N PH???M CH??? L???C ################################################ -->
										
										<%-- <table align="left" id='chartDSSPChuLuc' >
										
											<caption align="left" >Doanh s??? theo S???n ph???m ch??? l???c (????n v??? t??nh: Tri???u ?????ng) </caption>
										
											<thead>
												<tr>
													<th></th>
													<%  //In ra List SP CH??? L???C
														for(int i=0;i<arrTenSpChuLuc.length;i++)
														{
															if( arrTenSpChuLuc[i]!=null){
															%>
															<th><%= arrTenSpChuLuc[i] %></th>
													<% 		}
														}
													%>             
												</tr>
											</thead>
											<tbody>
											<%while (rsDoanhSoSPChuLuc.next()){ %>
												<tr>
													<th><%=rsDoanhSoSPChuLuc.getString("type") %></th>
													<%  
													for(int j=0;j<arrTenSpChuLuc.length;j++)
													{
														if( arrTenSpChuLuc[j]!=null){
															%>
															<td><%= rsDoanhSoSPChuLuc.getString(arrIdSpChuLuc[j])%></td>
													<%
														}
													}%>
												</tr>	
										<%} %>            
											</tbody>
										</table> --%>
									</TD>
									<TD>
										<!-- ################################################## DOANH S??? S???N PH???M M???I ################################################ -->
										
										<%-- <table align="left" id='chartDSSPMoi' >
										
											<caption align="left" >Doanh s??? theo S???n ph???m m???i (????n v??? t??nh: Tri???u ?????ng) </caption>
										
											<thead>
												<tr>
													<th></th>
													<%  //In ra List SP M???i
														for(int i=0;i<arrTenSpMoi.length;i++)
														{
															if( arrTenSpMoi[i]!=null){
															%>
															<th><%= arrTenSpMoi[i] %></th>
													<% 		}
														}
													%>             
												</tr>
											</thead>
											<tbody>
											<%while (rsDoanhSoSPMoi.next()){ %>
												<tr>
													<th><%=rsDoanhSoSPMoi.getString("type") %></th>
													<%  
													for(int j=0;j<arrTenSpMoi.length;j++)
													{
														if( arrTenSpMoi[j]!=null){
															%>
															<td><%= rsDoanhSoSPMoi.getString(arrIdSpMoi[j])%></td>
													<%
														}
													}%>
												</tr>	
										<%} %>            
											</tbody>
										</table> --%>
									</TD>
								</TR>							

							</TABLE>							
							
						</FIELDSET>
					</TD>
				</TR>
				<!-- ############################################## K???T TH??C B??O C??O DOANH S??? ################################################# -->
				
				<!-- ################################################## B??O C??O DOANH THU ##################################################### -->
				
				<TR>
					<TD width="100%">
						
						<FIELDSET>
							<LEGEND align="left" class="legendtitle">&nbsp;B??o c??o doanh thu &nbsp;&nbsp;
								
							</LEGEND>

							<TABLE class="" width="100%">
								<TR>
								<TD>
									<!-- ################################################## DOANH THU ?????A B??N ##################################################### -->
									
									<table align="center" id='chartDTDiaBan' >
									
										<caption align="center" >Doanh thu theo ?????a b??n (????n v??? t??nh: Tri???u ?????ng) </caption>
									
										<thead>
											<tr>
												<th></th>
												<%  //In ra List ?????a b??n
													for(int i=0;i<arrTenDiaBan.length;i++)
													{
														if( arrTenDiaBan[i]!=null){
														%>
														<th><%= arrTenDiaBan[i] %></th>
												<% 		}
													}
												%>             
											</tr>
										</thead>
										<tbody>
										<%while (rsDoanhThuDiaBan.next()){ %>
											<tr>
												<th><%=rsDoanhThuDiaBan.getString("type") %></th>
												<%  
												for(int j=0;j<arrIdDiaBan.length;j++)
												{
													if( arrIdDiaBan[j]!=null){
														%>
														<td><%= rsDoanhThuDiaBan.getString(arrIdDiaBan[j])%></td>
												<%
													}
												}%>
											</tr>	
									<%} %>            
										</tbody>
									</table>
								</TD>
								<!-- ################################################## DOANH THU MI???N ##################################################### -->
								<TD>
								<table align="center" id='chartDTMien' >
								
									<caption align="center" >Doanh thu theo Mi???n (????n v??? t??nh: Tri???u ?????ng) </caption>
								
									<thead>
										<tr>
											<th></th>
											<%  //In ra List ?????a b??n
												for(int i=0;i<arrTenMien.length;i++)
												{
													if( arrTenMien[i]!=null){
													%>
													<th><%= arrTenMien[i] %></th>
											<% 		}
												}
											%>             
										</tr>
									</thead>
									<tbody>
									<%while (rsDoanhThuMien.next()){ %>
										<tr>
											<th><%=rsDoanhThuMien.getString("type") %></th>
											<%  
											for(int j=0;j<arrIdMien.length;j++)
											{
												if( arrIdMien[j]!=null){
													%>
													<td><%= rsDoanhThuMien.getString(arrIdMien[j])%></td>
											<%
												}
											}%>
										</tr>	
								<%} %>            
									</tbody>
								</table>	
							</TD>						
							</TABLE>
							
							
						</FIELDSET>
					</TD>
				</TR>		
			<!-- ############################################## K???T TH??C B??O C??O DOANH THU ################################################# -->

			<!-- ################################################### B??O C??O T???N KHO ####################################################### -->
				
				<TR>
					<TD width="100%">
						
						<FIELDSET>
							<LEGEND align="left" class="legendtitle">&nbsp;B??o c??o t???n kho hi???n t???i&nbsp;&nbsp;
								
							</LEGEND>
							
							<TABLE class="" width="100%">
								<TR>
								<TD>
								<!-- ################################################## T???N KHO ?????A B??N ##################################################### -->
								
								<table align="center" id='chartTKDiaBan' >
								
									<caption align="center" >T???n kho theo ?????a b??n (????n v??? t??nh: S???n ph???m) </caption>
								
									<thead>
										<tr>
											<th></th>
											<%  
												//In ra List ?????a b??n
												for(int i=0;i<arrTenDiaBan.length;i++)
												{
													if( arrTenDiaBan[i]!=null){
													%>
													<th><%= arrTenDiaBan[i] %></th>
											<% 		}
												}
											%>             
										</tr>
									</thead>
									<tbody>
									<%while (rsTonKhoDiaBan.next()){ %>
										<tr>
											<th><%=rsTonKhoDiaBan.getString("type") %></th>
											<%  
											for(int j=0;j<arrIdDiaBan.length;j++)
											{
												if( arrIdDiaBan[j]!=null){
													%>
													<td><%= rsTonKhoDiaBan.getString(arrIdDiaBan[j])%></td>
											<%
												}
											}%>
										</tr>	
								<%} %>            
									</tbody>
								</table>
								</TD>
								<!-- ################################################## T???N KHO MI???N ##################################################### -->
								<TD>
								<table align="center" id='chartTKMien' >								
									<caption align="center" >T???n kho theo Mi???n (????n v??? t??nh: S???n ph???m) </caption>								
									<thead>
										<tr>	
											<th></th>										
											<%  
												//In ra List ?????a b??n
												for(int i=0;i<arrTenMien.length;i++)
												{
													if( arrTenMien[i]!=null){
													%>
													<th><%= arrTenMien[i] %></th>
											<% 		}
												}
											%>             
										</tr>
									</thead>
									<tbody>
									<%while (rsTonKhoMien.next()){ %>
										<tr>
											<th><%=rsTonKhoMien.getString("type") %></th>
											<%  
											for(int j=0;j<arrIdMien.length;j++)
											{
												if( arrIdMien[j]!=null){
													%>
													<td><%= rsTonKhoMien.getString(arrIdMien[j])%></td>
											<%
												}
											}%>
										</tr>	
								<%} %>            
									</tbody>
								</table>
								</TD>	
								</TR>								
							</TABLE>
							
							
						</FIELDSET>
					</TD>
				</TR>		
			<!-- ############################################## K???T TH??C B??O C??O T???N KHO ################################################# -->
			
			</TABLE>
		</TD>
	</TR>
</TABLE>
</form>
</BODY>
</HTML>

<%
		 if(rsDoanhSoDiaBan != null)
			rsDoanhSoDiaBan.close(); 
		if(rsDoanhSoMien != null)
			rsDoanhSoMien.close(); 
		if(rsDoanhSoNSP != null)
			rsDoanhSoNSP.close(); 
		if(rsDoanhThuDiaBan != null)
			rsDoanhThuDiaBan.close(); 
		if(rsDoanhThuMien != null)
			rsDoanhThuMien.close(); 
		if(rsTonKhoDiaBan != null)
			rsTonKhoDiaBan.close();
		 if(rsTonKhoMien != null)
			rsTonKhoMien.close(); 
		 if(rsDoanhSoSPChuLuc != null)
				rsDoanhSoSPChuLuc.close(); 
		 if(rsDoanhSoSPMoi != null)
			 rsDoanhSoSPMoi.close(); 
		if(obj != null)
		{
			obj.DbClose();
			obj = null;
		}
		session.setAttribute("obj", null);
	}	
%>
