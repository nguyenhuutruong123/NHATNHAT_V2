<%@page import="java.sql.ResultSet"%>
<%@page import="geso.dms.center.beans.stockintransit.IStockintransit"%>
<%@page import="java.util.Calendar"%>
<%@page import="java.sql.Date"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page  import = "geso.dms.center.util.*" %>
	<%
		String userId = (String) session.getAttribute("userId");
		String userTen = (String) session.getAttribute("userTen");
		IStockintransit obj = (IStockintransit) session.getAttribute("obj");
		ResultSet kenh = obj.getkenh();
		ResultSet vung = obj.getvung();
		ResultSet khuvuc = obj.getkhuvuc();
		ResultSet npp = obj.getnpp();
		ResultSet dvkd = obj.getdvkd();
		ResultSet dvdl = obj.getdvdl();
		ResultSet khoRs = obj.getkho();
		Utility util = (Utility) session.getAttribute("util");
		String url="";
		url = util.getUrl("BcDuLieuImport","&view=TT");
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
	function search(){
		document.forms["frm"]["action"].value = "search";
		document.forms["frm"].submit();
	}	
	
	function submitform() {
		if (document.forms["frm"]["Sdays"].value.length == 0) {
			setErrors("Vui lòng chọn ngày bắt đầu");
			return;
		}
		 
		var fieldShow = document.getElementsByName("fieldsHien");		
		for ( var i = 0; i < fieldShow.length; ++i) {
			fieldShow.item(i).checked = true;
		}		
		document.forms["frm"]["action"].value = "Taomoi";
		document.forms["frm"].submit();
		reset();
	}
	function setErrors(errorMsg) {
		var errors = document.getElementById("errors");
		errors.value = errorMsg;
	}
	function reset() {
		var fieldShow = document.getElementsByName("fieldsHien");		
		for ( var i = 0; i < fieldShow.length; ++i) {
			fieldShow.item(i).checked = false;
		}		
	};
</script>
<link href="../css/select2.css" rel="stylesheet" />
<script src="../scripts/select2.js"></script>
<script>
$(document).ready(function()
{
	$("#nppId").select2();
	$("#vungId").select2();
	$("#khuvucId").select2();
	$("#nhanhangId").select2();
	$("#chungloaiId").select2();
	$("#programs").select2();
	$("#dvkdId").select2();
	$("#gsbhId").select2();
	$("#kenhId").select2();
});
</script>


</head>

<BODY leftmargin="0" bottommargin="0" topmargin="0" rightmargin="0">
	<form name="frm" method="post" action="../../BcDuLieuImport">
	<input type="hidden" name="action" value='1'>
	<input type="hidden" name="userId" value='<%=userId%>'>
	<input type="hidden" name="view" value='TT'>
		<div id="main" style="width: 99%; padding-left: 2px">
			<div align="left" id="header" style="width: 100%; float: none">
				<div style="width: 70%; padding: 5px; float: left" class="tbnavigation"> <%=url %> </div>
				<div align="right" style="padding: 5px" class="tbnavigation">
					<%=Utility.GLanguage("Chào mừng bạn",session,jedis) %>  <%=userTen%></div>
			</div>
			<div align="left" id="button"
				style="width: 100%; height: 32px; padding: 0px; float: none" class="tbdarkrow">
			</div>
			<div align="left" style="width: 100%; float: none; clear: left">
				<fieldset>
					<legend class="legendtitle"> <%=Utility.GLanguage("Báo lỗi nhập liệu",session,jedis) %></legend>
					<textarea id="errors" name="errors" rows="1" style="width: 100% ; color:#F00 ; font-weight:bold">
					<%= obj.getMsg() %></textarea>
				</fieldset>
			</div>
			<div align="left"
				style="width: 100%; float: none; clear: left; font-size: 0.7em">
				<fieldset>
					<legend class="legendtitle"> Dữ liệu Import</legend>
					<div style="width: 100%; float: none" align="left">
						<div style="width: 100%; float: none" align="left"
							class="plainlabel">
							<TABLE width="70%" cellpadding="6" cellspacing="0">
							<TR>
							<TD class="plainlabel"><%=Utility.GLanguage("Từ ngày",session,jedis) %></TD>
									<TD class="plainlabel">
										<input type="text" name="Sdays"	class="days" value='<%=obj.gettungay()%>' /></TD>
									<TD class="plainlabel"><%=Utility.GLanguage("Đến ngày",session,jedis) %></TD>
									<td>
										<input type="text" name="Edays" class="days" value='<%=obj.getdenngay()%>' /></td>
								</TR>
								
								<TR>
									<TD class="plainlabel"><%=Utility.GLanguage("Nhà phân phối",session,jedis) %> </TD>
									<TD class="plainlabel" > 
										<select name="nppId" onchange="seach();" id="nppId" style="width:200px">
											<option value="" selected>All</option>
											<%if (npp != null)
													while (npp.next()) {
														if (npp.getString("pk_seq").equals(obj.getnppId())) {%>
															<option value="<%=npp.getString("pk_seq")%>" selected><%=npp.getString("ten")%></option>
													<%} else {%>
														<option value="<%=npp.getString("pk_seq")%>"><%=npp.getString("ten")%></option>
													<%}}%>
										</select>
									</TD>
								</TR>
								
								
								<TR>
									<TD class="plainlabel">Loại báo cáo</TD>
										<TD class="plainlabel">
										<% 	String[] trangthai = new  String[] {"Xuất kho nội bộ","Hóa đơn CN","Thu tiền mặt","Thu tiền CK","Bù Trừ","Hóa đơn CTY" } ;
									String[] idTrangThai = new  String[] {"0","1","2","3","4","5"} ;
								%> 
								<SELECT name="type" >
				      		 <% for( int i=0;i<trangthai.length;i++) { 
				    			if(idTrangThai[i].equals(obj.gettype()) ){ %>
				      				<option value='<%=idTrangThai[i]%>' selected><%=trangthai[i] %></option>
				      		 	<%}else{ %>
				     				<option value='<%=idTrangThai[i]%>'><%=trangthai[i] %></option>
				     			<%} 
				      		 }
				      		 	%>
				      		 	</SELECT>
		      		 	</TR>
		      		 	
		      		 	<TR>
									<TD class="plainlabel">Kho xuất </TD>
									<TD class="plainlabel" > 
										<select name="khoId" onchange="seach();" id="khoId" style="width:200px">
											<option value="" selected>All</option>
											<%if (khoRs != null)
													while (khoRs.next()) {
														if (khoRs.getString("pk_seq").equals(obj.getkhoId()   )) {%>
															<option value="<%=khoRs.getString("pk_seq")%>" selected><%=khoRs.getString("ten")%></option>
													<%} else {%>
														<option value="<%=khoRs.getString("pk_seq")%>"><%=khoRs.getString("ten")%></option>
													<%}}%>
										</select>
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
try{
if(kenh!=null){
	kenh.close();
}
if(vung!=null){
	vung.close();
}
if(khuvuc!=null){
	khuvuc.close();
}
if(npp!=null){
	npp.close();
}
if(dvkd!=null){
	dvkd.close();
}
if(dvdl!=null){
	dvdl.close();
}
obj.DBclose();
}catch(Exception er){
	
}
%>