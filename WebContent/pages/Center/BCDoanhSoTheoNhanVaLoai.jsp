<%@page import="javax.servlet.jsp.tagext.TryCatchFinally"%>
<%@page import="geso.dms.center.beans.asm.imp.AsmList"%>
<%@page import="geso.dms.center.beans.baocaodoanhsotheonhanvaloai.IBCDoanhSoTheoNhanVaLoai"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.Calendar"%>
<%@ page import="java.util.Date"%>
<%@ page import="java.sql.SQLException"%>
<%@ page import = "java.util.Iterator" %>
<%@ page  import = "java.util.Date" %>
<%@ page  import = "java.util.Calendar" %>
<%@ page import = "java.util.List" %>
<%@ page import = "java.util.ArrayList" %>
<%@ page import = "java.sql.ResultSet" %>
<%@ page import = "geso.dms.center.beans.bcchartsanpham.*" %>
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
		IBCDoanhSoTheoNhanVaLoai obj =(IBCDoanhSoTheoNhanVaLoai)session.getAttribute("obj");
		
		ResultSet rsBrand = obj.getRsBrand();
		ResultSet rsKenh = obj.getRsKenh();
		ResultSet rsASM = obj.getASM();
		ResultSet rsRSM = obj.getRSM();
		long[][] doanhso = new long[obj.getSodong()][obj.getSocot()];
		doanhso = obj.getDoanhSo();
		String[] thangChart = new String[obj.getSocot()];
			thangChart = obj.getMangThang();
		String[] tuaDe = new String[obj.getSodong()];
			tuaDe =	obj.getMaTen();
		%>
	<!-- /* ResultSet rs=obj.getRs(); */
	
	/* String[] arrIdMien=obj.getArrIDMien();
	String[] arrTenMien=obj.getArrTenMien();
	ResultSet spRs=obj.getSpRs();
	long [][]doanhso = obj.getDoanhsoSp();
	ResultSet nhList = (ResultSet)obj.getNhList(); 
	ResultSet gsbhList = (ResultSet)obj.getGsbhList();
	ResultSet bmList = (ResultSet)obj.getbmList();
	 */  -->

<%NumberFormat formatter = new DecimalFormat("#,###,###"); %>
<%NumberFormat formatter2 = new DecimalFormat("#,###,###.####"); %>

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
<link href="../css/chosen.css" rel="stylesheet" type="text/css" />
<script src="../scripts/chosen.jquery.js" type="text/javascript"></script>
<script type="text/javascript">
	$(document).ready(function() {
		$(".days").datepicker({
			changeMonth : true,
			changeYear : true
		});
	});
	jQuery(document).ready(function()
	{
		$("select:not(.notuseselect2)").chosen();     
		
	});
</script>

  <style type="text/css">
   ul { height: 300px; overflow:auto; width: 400px; border: 1px solid #000; }
   ul { list-style-type: none; margin: 0; padding: 0; overflow-x: hidden; width: 400px }
   li { margin: 0; padding: 0; }
   label { display: block; color: WindowText; background-color: Window; margin: 0; padding: 0; width: 100%; }
   label:hover { background-color: Highlight; color: HighlightText; }
  </style>	
<script type="text/javascript" src="../scripts/Chart/jsapi"></script>
<script src="../scripts/Chart/jquery.gvChart-0.1.min.js" type="text/javascript"></script>
<script type="text/javascript">
	gvChartInit();
	
	
	
	
	
		jQuery(document).ready(function(){
			
			var table = '#myTable9';
			jQuery(table).gvChart({
				chartType: 'ColumnChart',
				gvSettings: {
					vAxis: {title: 'Doanh số theo nhãn và kênh (Đơn vị tính: Nghìn đồng,Ngày) '},
					hAxis: {title: 'Tháng'},
					width: 1200,
					height:600,
					isStacked: true
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

	function subInBieuDo()
	{
		var kq1 = checkrong();
		
		if(kq1==false){
			return false;
		}
				
			
		document.forms["khtt"].submit();
	}
	function submitform()
	{
		var kq = check();
		if(kq==false){
			return false;
		}
		document.forms["khtt"].submit();
	}
	function check(){
		var thangTu=document.getElementById('tuThang');
		var thangDen = document.getElementById('denThang');
		
		if(thangTu.value.trim().length != 0 && thangDen.value.trim().length != 0){
			if(thangTu.value > thangDen.value){
				alert("Tháng bắt đầu phải nhỏ hơn tháng đến !");
				thangDen.value = "";
				return false;
			}
		}
		
		return true;
	}
	function checkrong(){
		var thangTu = document.getElementById('tuThang');
		var thangDen = document.getElementById('denThang');
		var nam = document.getElementById('nam');
		var namDen = document.getElementById('namDen');
		if(thangTu.value.trim().length == 0 || thangDen.value.trim().length == 0 || nam.value.trim().length == 0 || namDen.value.trim().length == 0){
			alert("Vui lòng nhập năm, tháng bắt đầu, tháng kết thúc !");
			return false;
		}
		else {
			if(parseInt(nam.value) > parseInt(namDen.value)){
				alert("Năm bắt đầu phải nhỏ hơn năm đến !");
				return false;
			}
			else{
				if(parseInt(nam.value) == parseInt(namDen.value)){
					if(thangTu.value.trim().length != 0 && thangDen.value.trim().length != 0){
						if(parseInt(thangTu.value) > parseInt(thangDen.value)){
							alert("Tháng bắt đầu phải nhỏ hơn tháng đến !");
							thangDen.value = "";
							return false;
						}
					}
				}
			}
		}
		return true;
	}
	</SCRIPT>
</head>
<BODY leftmargin="0" bottommargin="0" topmargin="0" rightmargin="0">
<form name="khtt" method="post" action="../../BCDoanhSoTheoNhanVaLoaiSvl">
<input type="hidden" name="userId" value="<%= userId %>" >
<input type="hidden" name="action" value="1" >
<%-- <input type="hidden" id ="sosp" name="sosp" value="<%= doanhso.length %>" > --%>
<TABLE width="100%" border="0" cellspacing="0" cellpadding="0" height="100%">
	<TR>
		<TD colspan="4" align="left" valign="top" bgcolor="#FFFFFF">
			<TABLE width="100%" cellpadding="0" cellspacing="2">
				<TR>
					<TD align="left" class="tbnavigation">
						<TABLE width="100%" border="0" cellpadding="0" cellspacing="0">
							<TR height="22">
								<TD align="left" colspan="2" class="tbnavigation">&nbsp;Báo cáo quản trị > Biểu đồ > Báo cáo doanh số theo nhãn và loại </TD>
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
						
								<TR >
								<%  
									int year =1;
		                            year = Calendar.getInstance().get(Calendar.YEAR); 
		                            int nam[]= new int[year-2015+1];
		                            int dem=0;
		                            //tao danh sach toi nam hien tai
		                            for(int i=2015; i<=year; i++){
		                             nam[dem]=i;
		                             dem++;
		                            }
		                             
								%>
									<TD class="plainlabel">Từ năm :</TD>
									<TD class="plainlabel">
										<SELECT id="nam" name="nam" onChange="" style="width:400px;height: 300px">
										  			<option value=""  ></option>
										  			
											  			<%for(int i= 0; i<nam.length; i++){ %>	
												  			<%if(String.valueOf(nam[i]).equals(obj.getNam())){ %>
												  				<option value="<%= String.valueOf(nam[i])%>" selected="selected"><%=String.valueOf(nam[i])%></option>
															<%}else {%>
																<option value="<%= String.valueOf(nam[i])%>"><%=String.valueOf(nam[i])%></option>
																<%}} 
																%>
																						                                           
                                              			</SELECT></TD>
                                            <TD class="plainlabel">Đến năm :</TD>
											<TD class="plainlabel"> 			
                                           <SELECT id="namDen" name="namDen" onChange="" style="width:400px;height: 300px">
										  			<option value=""  ></option>
										  			
											  			<%for(int i= 0; i<nam.length; i++){ %>	
												  			<%if(String.valueOf(nam[i]).equals(obj.getNamDen())){ %>
												  				<option value="<%= String.valueOf(nam[i])%>" selected="selected"><%=String.valueOf(nam[i])%></option>
															<%}else {%>
																<option value="<%= String.valueOf(nam[i])%>"><%=String.valueOf(nam[i])%></option>
																<%}} 
																%>
																						                                           
                                              			</SELECT></TD>
							</TR>
							<TR >
									
									<TD class="plainlabel">Từ tháng:</TD>
									<TD class="plainlabel">
									<SELECT id="tuThang"  name="thangTu" onChange="" style="width:400px;height: 300px">
									<option value=""  ></option>
									<% int[] thang = {1,2,3,4,5,6,7,8,9,10,11,12}; 
									for(int i = 0; i< thang.length; i++){
										if(String.valueOf(thang[i]).equals(obj.getThangTu())){%>
											<option value="<%= String.valueOf(thang[i])%>" selected="selected"><%=String.valueOf(thang[i])%></option>
										<%}
										else{%>
											<option value="<%= String.valueOf(thang[i])%>"><%=String.valueOf(thang[i])%></option>
										<%} %>
									<%} %>	
									</SELECT>
								</TD>
								
								<TD class="plainlabel">Đến tháng:</TD>
									<TD class="plainlabel">
									<SELECT id = "denThang" name="thangDen" onChange="" style="width:400px;height: 300px">
									<option value=""  ></option>
									<%
									for(int i = 0; i< thang.length; i++){
										if(String.valueOf(thang[i]).equals(obj.getThangDen())){%>
											<option value="<%= String.valueOf(thang[i])%>" selected="selected"><%=String.valueOf(thang[i])%></option>
										<%}
										else{%>
											<option value="<%= String.valueOf(thang[i])%>"><%=String.valueOf(thang[i])%></option>
										<%} %>
									<%} %>	
									</SELECT>
								</TD>
							</TR>
							<TR >
									
							</TR>
							<%-- <%} %> --%>
							<Tr>
									<TD class="plainlabel">Nhóm hàng: </TD>
										<TD class="plainlabel">
											<select name="kenhId" id="kenhId" style="width:400px;height: 300px"  multiple="multiple" onChange="subInBieuDo();">
													<%if (rsKenh != null){
														try{
															while (rsKenh.next()) {
																if (obj.getIdKenh().contains(rsKenh.getString("MAKENH")) ){	%>
																<option value="<%=rsKenh.getString("MAKENH")%>" selected="selected"><%=rsKenh.getString("MA") + " - " +rsKenh.getString("TENKENH")%></option>
															<%} else {%>
																<option value="<%=rsKenh.getString("MAKENH")%>"><%=rsKenh.getString("MA") + " - "+rsKenh.getString("TENKENH")%></option>
													<%}
																
																}
													rsKenh.close();
														}catch(SQLException ex){}
													}
													%>
											</select>
										</TD>
										<TD class="plainlabel"> </TD>
										<TD class="plainlabel"> </TD>
									</tr>					
								
								<TR>
							<TR>
									<TD width="19%" class="plainlabel">Nhãn hàng </TD>
  								  	<TD class="plainlabel" >
										<TABLE cellpadding="0" cellspacing="0" border="0">
											<TR>
										  		<TD><SELECT  name="nhId" onChange="subInBieuDo()" multiple="multiple" style="width:400px;height: 300px">
										  			<option value=""  ></option>
										  			<%try{ %>
											  			<%while(rsBrand.next()){ %>	
												  			<%if(obj.getIdBrand().contains(rsBrand.getString("MANHANHANG"))){ %>
												  				<option value="<%=rsBrand.getString("MANHANHANG")%>" selected="selected"><%=rsBrand.getString("TENNHANHANG")%></option>
															<%}else {%>
																<option value="<%=rsBrand.getString("MANHANHANG")%>" ><%=rsBrand.getString("TENNHANHANG")%></option>	
																<%}} 
																rsBrand.close();%>
													<%}catch(SQLException ex){} %>										                                           
                                              			</SELECT>
                                         			</TD>
                                         			<TD class="plainlabel"> </TD>
													<TD class="plainlabel"> </TD>
											</TR>
										</TABLE>									
									</TD>
									<TD class="plainlabel"> </TD>
									<TD class="plainlabel"> </TD>
								</TR>
								
									<TD width="19%" class="plainlabel">RSM</TD>
  								  	<TD class="plainlabel" >
										<TABLE cellpadding="0" cellspacing="0" border="0">
											<TR>
										  		<TD><SELECT  name="bmId" onChange="subInBieuDo();" multiple="multiple" style="width:400px;height: 300px">
										  			<option value=""  ></option>
										  			<%try{ %>
											  			<%while(rsRSM.next()){ %>	
												  			<%if(obj.getIdRSM().contains(rsRSM.getString("MARSM"))){ %>
												  				<option value="<%=rsRSM.getString("MARSM")%>" selected="selected"><%=rsRSM.getString("TENRSM")%></option>
															<%}else {%>
																<option value="<%=rsRSM.getString("MARSM")%>" ><%=rsRSM.getString("TENRSM")%></option>	
																<%}} 
																rsRSM.close();%>
													<%}catch(SQLException ex){} %>										                                           
                                              			</SELECT>
                                         			</TD>
                                         			
											</TR>
										</TABLE>									
									</TD>
									<TD class="plainlabel"> </TD>
									<TD class="plainlabel"> </TD>
								</TR>
								
									
								<TR>
									<TD width="19%" class="plainlabel">ASM: </TD>
  								  	<TD class="plainlabel" >
										<TABLE cellpadding="0" cellspacing="0" border="0">
											<TR>
										  		<TD><SELECT  name="gsbhId" onChange="subInBieuDo()" multiple="multiple" style="width:400px;height: 300px">
										  			<option value=""  ></option>
										  			<%try{ %>
											  			<%while(rsASM.next()){ %>	
												  			<%if(obj.getIdASM().contains(rsASM.getString("MAASM"))){ %>
												  				<option value="<%=rsASM.getString("MAASM")%>" selected="selected"><%=rsASM.getString("TENASM")%></option>
															<%}else {%>
																<option value="<%=rsASM.getString("MAASM")%>" ><%=rsASM.getString("TENASM")%></option>	
																<%}} 
																rsASM.close();%>
													<%}catch(SQLException ex){} %>										                                           
                                              			</SELECT>
                                         			</TD>
                                         		<TD class="plainlabel"> </TD>
												<TD class="plainlabel"> </TD>
											</TR>
										</TABLE>									
									</TD>
									<TD class="plainlabel"> </TD>
									<TD class="plainlabel"> </TD>
								</TR>
								<TR>
									<TD class="plainlabel" colspan="2">																			
									<a class="button"
										href="javascript:subInBieuDo();"> <img style="top: -4px;"
											src="../images/button.png" alt=""> Tạo báo cáo
									</a>
									</td>
									<TD class="plainlabel"> </TD>
									<TD class="plainlabel"> </TD>
								</TR>
							</TABLE>
									</FIELDSET>
								</TD>
							</TR>
						</TABLE>
					</TD>
				</TR>

				<TR>
				
				<%if(obj.getSodong() > 0){ %>
					<TD width="100%">
						<FIELDSET>
							<LEGEND class="legendtitle">&nbsp;Báo cáo doanh số theo nhãn và kênh &nbsp;&nbsp;
								
							</LEGEND>
							<%if(obj.getIdBrand().length() > 0 ){ %>
							<table align="left" id='myTable9' >
								
									<caption align="left" >Doanh số (Đơn vị tính: Nghìn đồng,ngày) </caption>
									<thead>
								
										<TR> 
										<th>Tháng</th>
											<%for(int i=0; i< obj.getSodong(); i++) {
											if(thangChart[i] != null){%>
												<th><%= thangChart[i]%></th>
												<%} %>
											<%} %>
										</TR>
									</thead>
									<tbody>
									
									<%for(int i=0; i< obj.getSocot(); i++) {%>
										<TR> 
											
											<th><%= tuaDe[i]%> </th>
											<% for(int j=0; j< obj.getSodong(); j ++) {%>
												<td><%=doanhso[j][i] %></td>
											<%} %>
										</TR>
									<% }%>
									</tbody>
								</table>
							<%} else { %>
							<table align="left" id='myTable9' >
								
									<caption align="left" >Doanh số (Đơn vị tính: Nghìn đồng,ngày) </caption>
									<thead>
								
										<TR> 
										<th>Tháng</th>
											<%for(int i=0; i<= obj.getSocot(); i++) {
											if(thangChart[i] != null){%>
												<th><%= thangChart[i]%></th>
												<%} %>
											<%} %>
										</TR>
									</thead>
									<tbody>
									
									<%for(int i=0; i< obj.getSodong(); i++) {%>
										<TR> 
											
											<th><%= tuaDe[i]%> </th>
											<% for(int j=0; j<= obj.getSocot(); j ++) {%>
												<td><%=doanhso[i][j] %></td>
											<%} %>
										</TR>
									<% }%>
									</tbody>
								</table>
							<%}  %>
						</FIELDSET>
					</TD>
					<%} %>
				</TR>
			</TABLE>
		</TD>
	</TR>
</TABLE>
</form>
</BODY>
</HTML>

<%

obj.dbClose();
	}		
%>
		
