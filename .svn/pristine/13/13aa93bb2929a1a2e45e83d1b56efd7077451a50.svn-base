<%@page import="java.sql.ResultSet"%>
<%@page import="geso.dms.center.beans.stockintransit.IStockintransit"%>
<%@page import="java.util.Calendar"%>
<%@page import="java.sql.Date"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"

	pageEncoding="UTF-8"%>
	<%@page import="geso.dms.center.util.Utility"%>

<%	String userId = (String) session.getAttribute("userId");  
	String userTen = (String) session.getAttribute("userTen");
	String view = (String) session.getAttribute("view");  
	String sum = (String) session.getAttribute("sum");
	Utility util = (Utility) session.getAttribute("util");
	if(!util.check(userId, userTen, sum)){
		response.sendRedirect(request.getContextPath() + "/redirect.jsp");
	}else{ %>
	<%
	IStockintransit obj = (IStockintransit) session.getAttribute("obj");
	ResultSet kenh = obj.getkenh();
	ResultSet vung = obj.getvung();
	ResultSet khuvuc = obj.getkhuvuc();
	ResultSet npp = obj.getnpp();
	ResultSet dvkd = obj.getdvkd();
	ResultSet dvdl = obj.getdvdl();
	ResultSet gsbh = obj.getgsbh();
	ResultSet ddkd = obj.getRsddkd();
	String url = util.getChuyenNguUrl("ThucDatChiTieuSR", "",session); 
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

<script type="text/javascript"	src="../scripts/jquery.min.1.7.js"></script>
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
            $(".button").hover(function(){
                $(".button img")
                .animate({top:"-10px"}, 200).animate({top:"-4px"}, 200) // first jump
                .animate({top:"-7px"}, 100).animate({top:"-4px"}, 100) // second jump
                .animate({top:"-6px"}, 100).animate({top:"-4px"}, 100); // the last jump
            });
        }); 
		$(document).ready(function(){
            $(".button1").hover(function(){
                $(".button1 img")
                .animate({top:"-10px"}, 200).animate({top:"-4px"}, 200) // first jump
                .animate({top:"-7px"}, 100).animate({top:"-4px"}, 100) // second jump
                .animate({top:"-6px"}, 100).animate({top:"-4px"}, 100); // the last jump
            });
        });
		
    </script>

<script type="text/javascript"
	src="../scripts/report-js/action-report.js"></script>
<script language="javascript" type="text/javascript">	
	function submitform() {
		
		var loainv = document.getElementById("loainv").value;
		if(loainv == '')
		{
			alert("Vui lòng chọn loại nhân viên");
			return;
		}
		
		document.forms['frm'].action.value= 'Taomoi';
		document.forms["frm"].submit();
	
		
	}
	function seach()
	{
		document.forms['frm'].action.value= 'seach';
		document.forms["frm"].submit();
	}
	
	function chotform()
	{
		document.forms['frm'].action.value= 'chot';
		document.forms["frm"].submit();
	}
	function setErrors(errorMsg) {
		var errors = document.getElementById("errors");
		errors.value = errorMsg;
	}
	function reset() {
		var fieldShow = document.getElementsByName("fieldsHien");
		var fieldHidden = document.getElementsByName("fieldsAn");
		for ( var i = 0; i < fieldShow.length; ++i) {
			fieldShow.item(i).checked = false;
		}
		for ( var i = 0; i < fieldHidden.length; ++i) {
			fieldHidden.item(i).checked = false;
		}
	};
	
</script>

<link href="../css/select2.css" rel="stylesheet" />
	<script src="../scripts/select2.js"></script>
	<script>
    	$(document).ready(function() { 
    		$("select:not(.notuseselect2)").select2({ width: 'resolve' });     
    	});
    </script>	
</head>
<BODY leftmargin="0" bottommargin="0" topmargin="0" rightmargin="0">
	<form name="frm" method="post" action="../../ThucDatChiTieuSR">  
	<% redis.clients.jedis.Jedis jedis =  geso.dms.center.util.Utility.getJedis(); %><% geso.dms.center.util.Csrf csdr=new geso.dms.center.util.Csrf(request,response,true,false,true);%>
 <input type="hidden" name="<%=csdr.get_tokenName() %>" value='<%=csdr.get_tokenValue() %>'>
	
	<input type="hidden" name="action" value='1'>
	<input type="hidden" name="view" value='<%= view %>'>
	<input type="hidden" name="userId" value='<%=userId%>'>
		<div id="main" style="width: 99%; padding-left: 2px">
			<div align="left" id="header" style="width: 100%; float: none">
				<div style="width: 70%; padding: 5px; float: left"
					class="tbnavigation"> <%=url %></div>
				<div align="right" style="padding: 5px" class="tbnavigation"><%=Utility.GLanguage("Chào mừng bạn",session,jedis) %>  <%=userTen%></div>
			</div>
		
		<%if(obj.getMsg().length()> 0) { %>
			<div align="left" style="width: 100%; float: none; clear: left">
				<fieldset>
					<legend class="legendtitle"><%=Utility.GLanguage("Thông báo",session,jedis) %></legend>
					<textarea id="errors" name="errors" rows="1" style="width: 100% ; color:#F00 ; font-weight:bold">
					<%=Utility.GLanguage(obj.getMsg(),session,jedis) %></textarea>
				</fieldset>
			</div>
		<%} %>
			<div align="left"
				style="width: 100%; float: none; clear: left; font-size: 0.7em">
				<fieldset>
					<legend class="legendtitle"> <%=Utility.GLanguage("Thực hiện chỉ tiêu",session,jedis) %></legend>
					<div style="width: 100%; float: none" align="left">
						<div style="width: 100%; float: none" align="left"
							class="plainlabel">
							<TABLE width="70%" cellpadding="6" cellspacing="0">
							<!-- <TD>
								<TABLE width="100%" border="0" cellpadding="0" cellspacing="0">
									<TR class="tbdarkrow">
										
										<TD width="2" align="left"></TD>
										<TD width="10" align="left"><A
											href="javascript:chotform()"><IMG
												src="../images/Save30.png" title="Luu lai" alt="Luu lai"
												border="1" style="border-style: outset">
										</A>
										</TD>
										<TD>&nbsp;</TD>

									</TR>
								</TABLE></TD> -->
								
								
									<TR>
									<TD class="plainlabel"><%=Utility.GLanguage("Chọn nhân viên",session,jedis) %></TD>
									<TD class="plainlabel">
										<SELECT name = "loainv" id="loainv" onchange = "seach();">
										
											<option  value="1" <%= obj.getLoaiNv().equals("1") ? "selected":""  %> ><%=Utility.GLanguage("NVBH",session,jedis) %></option>
											<option  value="2" <%= obj.getLoaiNv().equals("2") ? "selected":""  %> ><%=Utility.GLanguage("GSBH",session,jedis) %></option>
											<option  value="3" <%= obj.getLoaiNv().equals("3") ? "selected":""  %> ><%=Utility.GLanguage("ASM",session,jedis) %></option>
										
										</SELECT>									
									</TD>
									</TR>
									
									<TR>
									<TD class="plainlabel"><%=Utility.GLanguage("Chọn tháng",session,jedis) %></TD>
									<TD>
										<SELECT name = "month" id="month">
										<%
										String month = "";
										for(int i=1; i<=12; i++){ 
											month = ""+i;
											if(i < 10) { month = "0"+i; }
											if(obj.getMonth().equals(month)){ %>
											<option value = "<%= month%>" selected><%= month%></option>
										<%} else {%>
											<option value = "<%= month%>"><%= month%></option>
										<%}} %>
										</SELECT>
									</TD>
									
									<TD class="plainlabel"><%=Utility.GLanguage("Chọn năm",session,jedis) %></TD>
									<TD class="plainlabel">
										<SELECT name = "year">
										<%
										for(int n=2018; n<2025; n++){
  										if(obj.getYear().equals(""+n)){%>
										<option value=<%=n%> selected="selected" > <%=n%></option> 
										<%}else{%>
										<option value=<%=n%> ><%=n%></option> 
										<%}}%>
										</SELECT>									
									</TD>
								</TR>
								
								<%if(view.trim().equals("TT")) { %>	
									<TR>
									<TD class="plainlabel"><%=Utility.GLanguage("Vùng",session,jedis) %><%=Utility.GLanguage("Miền",session,jedis) %></TD>
									<TD class="plainlabel">
										<select name="vungId" id="vungId" onchange="seach();" style ="width:200px" multiple="multiple">
										
											<%if (vung != null)
													while (vung.next()) {
														if (obj.getvungId().contains(vung.getString("pk_seq"))) {%>
													<option value="<%=vung.getString("pk_seq")%>" selected><%=vung.getString("ten")%></option>
												<%} else {%>
													<option value="<%=vung.getString("pk_seq")%>"><%=vung.getString("ten")%></option>
											<%}}%>
										</select>
									</TD>
									 
								<TD width="19%" class="plainlabel" ><%=Utility.GLanguage("Khu vực",session,jedis) %> </TD>
								<TD class="plainlabel" >
								<TABLE cellpadding="0" cellspacing="0">
								<TR><TD>
								<select name="khuvucId" id = "khuvucId" onchange="submitCBO();" multiple="multiple" >
                                 <option value ="" > </option>  
                               <%
                               if(khuvuc != null)
                               while(khuvuc.next())
                               {
                               if(obj.getkhuvucId().contains(khuvuc.getString("pk_seq"))) {
                            	%><option value ="<%=khuvuc.getString("pk_seq") %>" selected> <%=khuvuc.getString("ten") %></option>  
                            	  <%} else { %>
                            	  <option value ="<%=khuvuc.getString("pk_seq") %>"> <%=khuvuc.getString("ten") %></option>
                              <%}} %>                             
                              	</select>		   										
                             	</TD>
                               	</TR>
								</TABLE>
								</TD>
								</tr>
								
								<TR>
								  	<TD width="19%" class="plainlabel" ><%=Utility.GLanguage("Nhà phân phối",session,jedis) %></TD>
								  	<TD class="plainlabel" >
									<TABLE cellpadding="0" cellspacing="0">
									<TR><TD>
									<select id = "nppId" name="nppId"  style = "width: 250px"> <!--  multiple="multiple" > -->
                                 	<option value =""> </option>  
                               <%
                               if(npp != null)
                               while(npp.next())
                               { if(obj.getnppId().equals(npp.getString("pk_seq")) ) {%>
                            		<option value ="<%=npp.getString("pk_seq") %>" selected> <%=npp.getString("ten") %></option>  
                           	  <%} else { %>
                            	  	<option value ="<%=npp.getString("pk_seq") %>"> <%=npp.getString("ten") %></option>
                              <%}} %>                             
                              		</select>		   										
                              		</TD>
                              		</TR>
									</TABLE>									
									</TD>
								</TR>
								<%} %>
								
								 <TR>
									<TD class="plainlabel"><%=Utility.GLanguage("Nhân viên",session,jedis) %></TD>
									<TD class="plainlabel">
										<SELECT name = "ddkdId" id="ddkdId"  >
											<option  value=""  ></option>
											<% ResultSet nvRs =obj.getNhanVienRs(obj.getLoaiNv()); 
												if(nvRs != null)
													while(nvRs.next()){
											%>
													<option  value="<%=nvRs.getString("pk_seq") %>" <%= obj.getDdkd().equals(nvRs.getString("pk_seq")) ? "selected":""  %> ><%=nvRs.getString("ten") %></option>
											
													<%} %>
										</SELECT>									
									</TD>
								</TR>
 	
								<TR>
									<td colspan="4">
									<a class="button" href="javascript:submitform();"> 
									<img style="top: -4px;" src="../images/button.png" alt=""><%=Utility.GLanguage("Tạo báo cáo",session,jedis) %></a>
									</td> 
								</TR>
							</TABLE>
						</div>
					</div>
				</fieldset>
			</div>
		</div>
	</form>
	<%geso.dms.center.util.Utility.JedisClose(jedis); %>
</body> <script type='text/javascript' src='../scripts/loadingv2.js'></script>
</HTML>
<%
	try
	{
		if(!(ddkd == null))
			ddkd.close();
		if(dvdl != null)
			dvdl.close();
		if(dvkd != null)
			dvkd.close();
		if(gsbh != null)
			gsbh.close();
		if(kenh != null)
			kenh.close();
		if(khuvuc != null)
			khuvuc.close();
		if(npp != null)
			npp.close();

		if(obj != null){
			obj.DBclose();
			obj = null;
		}
	}catch(java.sql.SQLException e){}
	}
	
%>