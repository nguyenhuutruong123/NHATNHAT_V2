<%@page import="geso.dms.center.beans.stockintransit.IStockintransit"%>
<%@page import="java.sql.ResultSet"%>
<%@page import="geso.dms.distributor.beans.report.Ireport"%>
<%@page import="java.util.Calendar"%>
<%@page import="java.sql.Date"%>
<%@ page  import = "geso.dms.center.util.*" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
	<%
	String userId = (String) session.getAttribute("userId");  
	String userTen = (String) session.getAttribute("userTen");
	IStockintransit obj = (IStockintransit)session.getAttribute("obj");

	ResultSet ddkd = obj.getRsddkd();
	ResultSet khoRs = obj.getkho();
	Utility util = (Utility) session.getAttribute("util");
	String url="";
	url = util.getUrl("BCDonHangBanTrongKy","&view=TT");
	
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
<script type="text/javascript">
	function submitform() {
		if (document.forms["frm"]["Sdays"].value.length==0) {
			setErrors("Vui lòng chọn ngày bắt đầu");
			return ;
		}
		if (document.forms["frm"]["Edays"].value.length==0) {
			setErrors("Vui lòng chọn ngày kết thúc");
			return ;
		}		
	/*	if (document.forms["frm"]["ddkds"].value == "0") {
			setErrors("Chọn Nhân viên bán hàng");
			return false;
		}
		if (document.forms["frm"]["khachs"].value == "0") {
			setErrors("Chọn khách hàng");
			return false;
		}
		*/
		var fieldShow = document.getElementsByName("fieldsHien");
		var fieldHidden = document.getElementsByName("fieldsAn");
		for ( var i = 0; i < fieldShow.length; ++i) {
			fieldShow.item(i).checked = true;
		}
		/*
		for ( var i = 0; i < fieldHidden.length; ++i) {
			fieldHidden.item(i).checked = true;
		}
		*/
		document.forms['frm'].action.value= 'tao';
		document.forms["frm"].submit();
		for ( var i = 0; i < fieldShow.length; ++i) {
			fieldShow.item(i).checked = false;
		}
	}
	function setErrors(errorMsg) {
		var errors = document.getElementById("errors");
		errors.value = errorMsg;
	}
	window.onload = function reset() {
		var fieldShow = document.getElementsByName("fieldsHien");
		var fieldHidden = document.getElementsByName("fieldsAn");
		for ( var i = 0; i < fieldShow.length; ++i) {
			fieldShow.item(i).checked = false;
		}
		for ( var i = 0; i < fieldHidden.length; ++i) {
			fieldHidden.item(i).checked = false;
		}
	};
	function seach()
	{
		document.forms['frm'].action.value= 'seach';
		document.forms["frm"].submit();
	}
</script>

<BODY leftmargin="0" bottommargin="0" topmargin="0" rightmargin="0">
	<form name="frm" method="post" action="../../BCDonHangBanTrongKy">
	<% redis.clients.jedis.Jedis jedis =  geso.dms.center.util.Utility.getJedis(); %><% geso.dms.center.util.Csrf csdr=new geso.dms.center.util.Csrf(request,response,true,false,true);%>
 <input type="hidden" name="<%=csdr.get_tokenName() %>" value='<%=csdr.get_tokenValue() %>'>
	
	<input type="hidden" name="action" value='1'>
	<input type="hidden" name="userId" value='<%=userId%>'>
		<input type="hidden" name="view" value='NPP'>
		
		<div id="main" style="width: 99%; padding-left: 2px">
			<div align="left" id="header" style="width: 100%; float: none">
				<div style="width: 70%; padding: 5px; float: left"
					class="tbnavigation"><%=url %></div>
				<div align="right" style="padding: 5px" class="tbnavigation">
					<%=Utility.GLanguage("Chào mừng bạn",session,jedis) %>  <%=userTen %></div>
			</div>

			<div align="left" style="width: 100%; float: none; clear: left">
				<fieldset>

					<legend class="legendtitle"> <%=Utility.GLanguage("Báo lỗi nhập liệu",session,jedis) %></legend>
					<textarea id="errors" 
						name="errors" rows="1" style="width: 100% ; color:#F00 ; font-weight:bold">
						<%= obj.getMsg() %>
						
						</textarea>
				</fieldset>
			</div>
			<div align="left"
				style="width: 100%; float: none; clear: left; font-size: 0.7em">
				<fieldset>
					<legend class="legendtitle">Báo cáo đơn hàng bán trong kỳ</legend>
					<div style="width: 100%; float: none" align="left">
						<div style="width: 100%; float: none" align="left"
							class="plainlabel">
							<TABLE width="70%" cellpadding="6" cellspacing="0">
								<TR>
										<TD class="plainlabel"><%=Utility.GLanguage("Từ ngày",session,jedis) %></TD>
											<TD class="plainlabel">	<input type="text" name="Sdays" class="days" value='<%= obj.gettungay() %>' />
											</TD>
											<TD class="plainlabel"><%=Utility.GLanguage("Đến ngày",session,jedis) %></TD>
											<TD class="plainlabel">
												<input type="text" name="Edays" class="days" value='<%= obj.getdenngay() %>'/>
											</TD>
									</TR>
								<TR>
									<TD class="plainlabel"><%=Utility.GLanguage("NHÂN VIÊN BÁN HÀNG",session,jedis) %></TD>
									<TD class="plainlabel"><select name="ddkdId" id="ddkdId" onchange="seach();">
											<option value="" >All</option>
										<% if(ddkd !=null){ 
											while(ddkd.next())
											{ 
											if(ddkd.getString("pk_seq").equals(obj.getDdkd())) {
											%>
											<option value="<%=ddkd.getString("pk_seq")%>" selected><%=ddkd.getString("ten")%></option>
											<%} else { %>
											<option value="<%=ddkd.getString("pk_seq")%>" ><%=ddkd.getString("ten")%></option>
											<%}}} %>
											</select></TD>
								
								</TR>
								
								
								<TR>
								<%if(obj.gettype().equals("0")){ %>
										
											<TD class="plainlabel"><%=Utility.GLanguage("Trạng thái",session,jedis) %></TD>
											<TD class="plainlabel">
											<% String[] trangthai = new  String[] {"Chưa chốt","Đã chốt","Đã hủy","Đã xuất Kho","Đã In","Đã xuất HĐ"  } ;
													String[] idTrangThai = new  String[] {"0","1","2","3","4","5"} ;
											%> 
											<SELECT name="trangthai" >
											<option> </option>
							      		 <% for( int i=0;i<trangthai.length;i++) { 
							    			if(idTrangThai[i].equals(obj.getTrangthai()) ){ %>
							      				<option value='<%=idTrangThai[i]%>' selected><%=trangthai[i] %></option>
							      		 	<%}else{ %>
							     				<option value='<%=idTrangThai[i]%>' ><%=trangthai[i] %></option>
							     			<%} 
							      		 }
							      		 	%>
											</SELECT>
											</TD>
								  
						  <%}else { %>
											<TD class="plainlabel"><%=Utility.GLanguage("Trạng thái",session,jedis) %></TD>
											<TD class="plainlabel">
											<% String[] trangthai = new  String[] {"Chờ xác nhận","Đã xác nhận","Đã Xoá","Đã In","Đã Hủy"  } ;
													String[] idTrangThai = new  String[] {"1","2","3","4","5"} ;
											%> 
											<SELECT name="trangthai" >
											<option> </option>
							      		 <% for( int i=0;i<trangthai.length;i++) { 
							    			if(idTrangThai[i].equals(obj.getTrangthai()) ){ %>
							      				<option value='<%=idTrangThai[i]%>' selected><%=trangthai[i] %></option>
							      		 	<%}else{ %>
							     				<option value='<%=idTrangThai[i]%>'><%=trangthai[i] %></option>
							     			<%} 
							      		 }
							      		 	%>
											</SELECT>
											</TD>
								  
						  
						  <%} %>
						  
						  <TD class="plainlabel">Kho</TD>
									<TD class="plainlabel"><select name="khoId" id="khoId" >
											<option value="" >All</option>
										<% if(khoRs !=null){ 
											while(khoRs.next())
											{ 
											if(khoRs.getString("pk_seq").equals(obj.getkhoId())) {
											%>
											<option value="<%=khoRs.getString("pk_seq")%>" selected><%=khoRs.getString("ten")%></option>
											<%} else { %>
											<option value="<%=khoRs.getString("pk_seq")%>" ><%=khoRs.getString("ten")%></option>
											<%}}} %>
											</select></TD>
						  
						  </TR>
								
								
						  <TR>
									<TD class="plainlabel">Doanh số tính theo </TD>
									<TD class="plainlabel">
							<%
								if(obj.getMucCN_DT().equals("1")){ %>
								<input type="checkbox" name="cndt" value="1" checked="checked"  /> CN/DT &nbsp; &nbsp;
							<% }else { %>
								    <input type="checkbox" name="cndt" value="1"  /> CN/DT &nbsp; &nbsp;
							 <%} %>
										
									<%
								if(obj.getMuc_KhachHang().equals("1")){ %>
								<input type="checkbox" name="kh" value="1" checked="checked"  /> Khách Hàng &nbsp; &nbsp;
							<% }else { %>
								    <input type="checkbox" name="kh" value="1"  /> Khách Hàng &nbsp; &nbsp;
							 <%} %>
										
									</TD>
									
								</TR>
								
								
								<TR>
									<TD class="plainlabel"> Mức lấy  </TD>
									<TD class="plainlabel">
									<%
										if(obj.gettype().equals("0")){
											%>
											<input type="radio" name="type" value="1"  onchange="seach();" /> Hóa Đơn &nbsp; &nbsp;
											<input type="radio" name="type" value="0"  checked="checked"  onchange="seach();" /> Đơn hàng
											<%
										}
										else
										{
											%>
												<input type="radio" name="type" value="1"  checked="checked"  onchange="seach();" />Hóa Đơn  &nbsp; &nbsp;
												<input type="radio" name="type" value="0"  onchange="seach();" /> Đơn hàng
											<%
										}
									%>
										
									</TD>
									
								</TR>
								
								<TR>
									<td colspan="4"><a class="button"
										href="javascript:submitform();"> <img style="top: -4px;"
											src="../images/button.png" alt=""> Tạo báo cáo
									</a></td>
								</TR>
							</TABLE>
						</div>
						<hr>
					</div>
				</fieldset>
			</div>
		</div>
		<br /> <br /> <br /> <br />
	</form>
</body>
</HTML>

<%
	try
	{
		if(!(ddkd == null))
			ddkd.close();
		
		 if(obj != null){
	    obj.DBclose();	   
		;} 
		session.setAttribute("obj",null);
	}catch(java.sql.SQLException e){}
	
%>