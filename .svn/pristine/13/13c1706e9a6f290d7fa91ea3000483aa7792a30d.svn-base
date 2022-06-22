<%@page import="geso.dms.center.beans.bcchartkhachhangmuahang.IBcchartkhachhangmuahang"%>
<%@page import="geso.dms.center.beans.asm.imp.AsmList"%>
<%@page import="geso.dms.center.beans.bcchartdoanhsosp.IBcchartdoanhsosp"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.Calendar"%>
<%@ page import="java.util.Date"%>
<%@ page import="java.sql.SQLException"%>
<%@ page import = "java.util.Iterator" %>

<%@ page import = "java.util.List" %>
<%@ page import = "java.util.ArrayList" %>
<%@ page import = "java.sql.ResultSet" %>
<%@ page import = "geso.dms.center.beans.bcchartsanpham.*" %>
<%@ page import = "geso.dms.center.util.*" %>
<%@ page  import = "java.text.DecimalFormat" %>
<%@ page  import = "java.text.NumberFormat" %>
<%@ page  import = "java.util.Date" %>
<%@ page  import = "java.util.Calendar" %>


<%	String userId = (String) session.getAttribute("userId");
	String userTen = (String) session.getAttribute("userTen");
	String sum = (String) session.getAttribute("sum");
	Utility util = (Utility) session.getAttribute("util"); 	
	if(!util.check(userId, userTen, sum)){
		response.sendRedirect("/opv/index.jsp");
	}else{ 
		IBcchartkhachhangmuahang obj =(IBcchartkhachhangmuahang)session.getAttribute("obj");
	/* ResultSet rs=obj.getRs(); */
	
/* 	String[] arrIdMien=obj.getArrIDMien();
	String[] arrTenMien=obj.getArrTenMien();*/
	
	ResultSet spRs=obj.getSPrs();
	long [][]doanhso = obj.getDoanhthu();
	ResultSet nhList = (ResultSet)obj.getNhlist(); 
	ResultSet gsbhList = (ResultSet)obj.getGsbhList();
	ResultSet bmList = (ResultSet)obj.getBmlist(); 
	ResultSet nspList = (ResultSet)obj.getNsplist(); 
	
	
	
%>
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
			
			var table = '#myTable';
			jQuery(table).gvChart({
				chartType: 'ColumnChart',
				gvSettings: {
					vAxis: {title: '(Đơn vị tính: Nghìn đồng) '},
					hAxis: {title: ' Tháng'},
					width: 1300,
					height:600
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
		var thangbatdau=document.getElementById('thang1');
		var thangketthuc=document.getElementById('thang2');
		
		var nambatdau=document.getElementById('nam1');
		var namketthuc=document.getElementById('nam2');
		
		if(nambatdau.value.trim().length <= 0 || namketthuc.value.trim().length <= 0 ||thangbatdau.value.trim().length <= 0 || thangketthuc.value.trim().length <= 0){
			alert(" Vui lòng chọn đủ năm tháng bắt đầu và năm tháng kết thúc ");
			return;
		}
		
		if(parseInt(nambatdau.value)> parseInt(namketthuc.value)){
			alert(" Vui lòng chọn năm bắt đầu nhỏ hơn năm kết thúc ");
			return;
		}
		
		if(parseInt(nambatdau.value)==parseInt(namketthuc.value))
		{
			if(parseInt(thangbatdau.value)>parseInt(thangketthuc.value))
			{
				alert(" Vui lòng chọn tháng bắt đầu nhỏ hơn tháng kết thúc ");
				return;
			}
			
		}
		
		document.forms["khtt"].submit();

	}


	</SCRIPT>
	
<link href="../css/select2.css" rel="stylesheet" />
<script src="../scripts/select2.js"></script>
<!-- <script>
	$(document).ready(function()
{
	$("#thang1").select2();
	$("#thang2").select2();
	$("#nam").select2();

}); 

</script> -->
	
	
</head>
<BODY leftmargin="0" bottommargin="0" topmargin="0" rightmargin="0">
<form name="khtt" method="post" action="../../BCKhachHangMuaHangSvl">
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
								<TD align="left" colspan="2" class="tbnavigation">&nbsp;Báo cáo quản trị > Biểu đồ > Báo cáo khách hàng mua hàng </TD>
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
							
								<TD class="plainlabel">Từ tháng</TD>
									
									<% String[] thang={"1","2","3","4","5","6","7","8","9","10","11","12"};%>
									
									<TD class="plainlabel">
										<SELECT  name="thang1" id="thang1"  style="width:400px"  > 
										<option value="" ></option>
										 <% for(int i=0; i<12;i++)
											if(obj.getThangbatdau().equals(thang[i])){%>
											<option value="<%=thang[i]%>" selected="selected"><%=thang[i]%></option>
											<%} else{ %>
											<option value="<%=thang[i]%>"><%=thang[i]%></option>
											<%} %> 
										</SELECT>
									</TD>
										
							
							
								<TD class="plainlabel">Năm</TD>
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
										<TD class="plainlabel"><SELECT  name="nam1" id="nam1"  style="width:400px" >
										<option value=""  ></option>
										<% for(int i=0; i<dem;i++)
											if(obj.getNam1().equals(""+nam[i])){%>
										<option value="<%=nam[i]%>" selected="selected"><%=nam[i]%></option>
										<%} else{%>
										<option value="<%=nam[i]%>" ><%=nam[i]%></option>
										<%} %>
										</SELECT>
									</TD>
								
								
								</TR>
							
							
							<%-- 	<TR >
									<TD class="plainlabel">Từ tháng</TD>
									
									<% String[] thang={"1","2","3","4","5","6","7","8","9","10","11","12"};%>
									
									<TD class="plainlabel">
										<SELECT  name="thang1" id="thang1"  style="width:400px"  > 
										<option value="" ></option>
										 <% for(int i=0; i<12;i++)
											if(obj.getThangbatdau().equals(thang[i])){%>
											<option value="<%=thang[i]%>" selected="selected"><%=thang[i]%></option>
											<%} else{ %>
											<option value="<%=thang[i]%>"><%=thang[i]%></option>
											<%} %> 
										</SELECT>
									</TD>
										
								</TR> --%>
								
								<TR>
								<TD class="plainlabel">Đến tháng</TD>
									<TD class="plainlabel">
									<SELECT  name="thang2" id="thang2" style="width:400px"  >
										<option value=""  ></option>
										 <% for(int i=0; i<12;i++)
											if(obj.getThangketthuc().equals(thang[i])){%>
											<option value="<%=thang[i]%>" selected="selected"><%=thang[i]%></option>
											<%} else{ %>
											<option value="<%=thang[i]%>"><%=thang[i]%></option>
											<%} %> 
										</SELECT>
								</TD>
									
									
									
								<TD class="plainlabel">Năm</TD>
									
										<TD class="plainlabel"><SELECT  name="nam2" id="nam2"  style="width:400px" >
										<option value=""  ></option>
										<% for(int i=0; i<dem;i++)
											if(obj.getNam2().equals(""+nam[i])){%>
										<option value="<%=nam[i]%>" selected="selected"><%=nam[i]%></option>
										<%} else{%>
										<option value="<%=nam[i]%>" ><%=nam[i]%></option>
										<%} %>
										</SELECT>
									</TD>
								
								</TR>
								
								
								
							
							<TR>
									<TD width="19%" class="plainlabel">Nhóm hàng </TD>
  								  	<TD class="plainlabel" colspan="3" >
										<TABLE cellpadding="0" cellspacing="0" border="0">
											<TR>
										  		<TD><SELECT  name="nspId" onChange="submitform();" multiple="multiple" style="width:400px" >
										  			<option value=""  ></option>
										  			<%try{ %>
											  			<%while(nspList.next()){ %>	
												  			<%if(obj.getNspid().contains(nspList.getString("pk_seq"))){ %>
												  				<option value="<%=nspList.getString("pk_seq")%>" selected="selected"><%=nspList.getString("ten")%></option>
															<%}else {%>
																<option value="<%=nspList.getString("pk_seq")%>" ><%=nspList.getString("ten")%></option>	
																<%}} %>
													<%}catch(SQLException ex){ex.printStackTrace();} %>										                                           
                                              			</SELECT>
                                         			</TD>
                                         			
                                         			
											</TR>
										</TABLE>									
									</TD>

								</TR>
							
							
							<TR>
									<TD width="19%" class="plainlabel">Nhãn hàng </TD>
  								  	<TD class="plainlabel" colspan="3" >
										<TABLE cellpadding="0" cellspacing="0" border="0">
											<TR>
										  		<TD><SELECT  name="nhId" onChange="submitform();" multiple="multiple" style="width:400px" >
										  			<option value=""  ></option>
										  			<%try{ %>
											  			<%while(nhList.next()){ %>	
												  			<%if(obj.getNhid().contains(nhList.getString("pk_seq"))){ %>
												  				<option value="<%=nhList.getString("pk_seq")%>" selected="selected"><%=nhList.getString("ten")%></option>
															<%}else {%>
																<option value="<%=nhList.getString("pk_seq")%>" ><%=nhList.getString("ten")%></option>	
																<%}} %>
													<%}catch(SQLException ex){ex.printStackTrace();} %>										                                           
                                              			</SELECT>
                                         			</TD>
                                         				
											</TR>
										</TABLE>									
									</TD>

								</TR>
								
								
								<Tr>
									<TD class="plainlabel"  ><%=Utility.GLanguage("Sản phẩm",session,jedis) %></TD>
										<TD class="plainlabel" colspan="3"> 
											<select name="spId" id="spId" style="width:400px;height: 300px" onChange="submitform();" multiple="multiple" >
											<!-- if (spRs != null) -->
											<%try{ %>
													<%if(spRs !=null)
															while (spRs.next()) {
																if (obj.getSpid().indexOf(spRs.getString("pk_seq")) >=0){	%>
																<option value="<%=spRs.getString("pk_seq")%>" selected><%=spRs.getString("ma") + " - " +spRs.getString("ten")%></option>
															<%} else {%>
																<option value="<%=spRs.getString("pk_seq")%>"><%=spRs.getString("ma") + " - "+spRs.getString("ten")%></option>
													<%}}%>
											<%}catch(SQLException ex){
												ex.printStackTrace();
											} %>		
											</select>
										</TD>
										
											
								
								
								<TR>
									<TD width="19%" class="plainlabel"  >RSM</TD>
  								  	<TD class="plainlabel" colspan="3">
										<TABLE cellpadding="0" cellspacing="0" border="0">
											<TR>
										  		<TD><SELECT  name="bmId" onChange="submitform();" multiple="multiple" style="width:400px" >
										  			<option value=""  ></option>
										  			<%try{ %>
											  			<%
											  			if(bmList !=null)
											  			while(bmList.next()){ %>	
											  			
												  			<%if(obj.getBmid().contains(bmList.getString("pk_seq"))){ %>
												  				<option value="<%=bmList.getString("pk_seq")%>" selected="selected"><%=bmList.getString("ten")%></option>
															<%}else {%>
																<option value="<%=bmList.getString("pk_seq")%>" ><%=bmList.getString("ten")%></option>	
																<%}} %>
													<%}catch(SQLException ex){
														ex.printStackTrace();
													} %>										                                           
                                              			</SELECT>
                                         			</TD>
                                         			
                                         				
											</TR>
										</TABLE>									
									</TD>

								</TR>
								
									
								<TR>
									<TD width="19%" class="plainlabel" >ASM</TD>
  								  	<TD class="plainlabel" colspan="3" >
										<TABLE cellpadding="0" cellspacing="0" border="0">
											<TR>
										  		<TD><SELECT  name="gsbhId" onChange="submitform();" multiple="multiple" style="width:400px" >
										  			<option value=""  ></option>
										  			<%try{ %>
											  			<%
											  			if(gsbhList !=null)
											  			while(gsbhList.next()){ %>	
												  			<%if(obj.getGsbhid().contains(gsbhList.getString("pk_seq"))){ %>
												  				<option value="<%=gsbhList.getString("pk_seq")%>" selected="selected"><%=gsbhList.getString("ten")%></option>
															<%}else {%>
																<option value="<%=gsbhList.getString("pk_seq")%>" ><%=gsbhList.getString("ten")%></option>	
																<%}} %>
													<%}catch(SQLException ex){ex.printStackTrace();} %>										                                           
                                              			</SELECT>
                                         			</TD>
                                         			
                                         			
                                         				
											</TR>
										</TABLE>									
									</TD>

								</TR>
								
								<TR>
									<TD class="plainlabel" colspan="4">																			
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
							<LEGEND class="legendtitle">&nbsp;Báo cáo khách hàng mua hàng &nbsp;&nbsp;
								
							</LEGEND>
							<table align="left" id='myTable' >
								
									<caption align="left" >Số khách hàng -  DS trung bình/khách hàng </caption>
									
									<thead>
									
										<tr>
											<th> </th>
											
											<% 
												int dodai=0;
												if(obj.getThangnam() !=null)
													dodai=obj.getThangnam().length;
												if(dodai>0){
													for(int i=0;i <obj.getThangnam().length;i++)
													{
												%>
														<th><%= obj.getThangnam()[i] %></th>
												<% 		
													}}
												%>   
											
											
											          
										</tr>
										
										
									</thead>
									
									
									<tbody>
									
									<tr>
											<th>Số khách hàng mua hàng </th>
											
												<% 
												System.out.println("do dai: "+dodai);
												if(dodai>0)
												for(int i=0;i <dodai;i++){%>
													<td><%=doanhso[i][0] %></td>
											<% 		
												}
											%>     
									</tr> 
									<tr>		
											<th>Doanh số trung bình/KH ( nghìn đồng)</th>
											
											<% 
												if(dodai>0)
												for(int i=0;i <dodai;i++){%>
													<td><%=doanhso[i][1] %> </td>
											<% 		
												}
											%>     
								</tr> 
									
									</tbody>
								</table>
								
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

<% /* 
		 if(rs != null)
			rs.close();  */

		if(obj != null)
		{
			obj.DbClose();
			obj = null;
		}
		session.setAttribute("obj", null);
	}	
%>
