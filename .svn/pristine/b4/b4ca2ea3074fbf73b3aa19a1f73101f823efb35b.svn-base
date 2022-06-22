<%@page import="java.sql.ResultSet"%>
<%@page import="geso.dms.distributor.beans.report.Ireport"%>
<%@page import="geso.dms.distributor.util.Utility"%>

<%@page import="java.util.Calendar"%>
<%@page import="java.sql.Date"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
	<%
	String userId = (String) session.getAttribute("userId");  
	String userTen = (String) session.getAttribute("userTen");
	Ireport obj = (Ireport)session.getAttribute("obj");
	ResultSet khachhang = obj.getkhachhang();
	//ResultSet npp = obj.getNppRs();
	ResultSet ddkd = obj.getddkd();
	
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
<script type="text/javascript">
	function submitform() {
		if (document.forms["frm"]["Sdays"].value.length==0) {
			setErrors("Vui lòng chọn ngày bắt đầu");
			return false;
		}
		if (document.forms["frm"]["Edays"].value.length==0) {
			setErrors("Vui lòng chọn ngày kết thúc");
			return false;
		}		
	/*	if (document.forms["frm"]["ddkds"].value == "0") {
			setErrors("Chọn nhân viên bán hàng");
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
	$("#dvdlId").select2();
});
</script>

<BODY leftmargin="0" bottommargin="0" topmargin="0" rightmargin="0">
	<form name="frm" method="post" action="../../BCDonHangHuyTrongKy">
	<% redis.clients.jedis.Jedis jedis =  geso.dms.center.util.Utility.getJedis(); %><% geso.dms.center.util.Csrf csdr=new geso.dms.center.util.Csrf(request,response,true,false,true);%>
 <input type="hidden" name="<%=csdr.get_tokenName() %>" value='<%=csdr.get_tokenValue() %>'>
	
	<input type="hidden" name="action" value='1'>
	<input type="hidden" name="userId" value='<%=userId%>'>
		<div id="main" style="width: 99%; padding-left: 2px">
			<div align="left" id="header" style="width: 100%; float: none">
				<div style="width: 70%; padding: 5px; float: left"
					class="tbnavigation">Quản lý bán hàng &#62; Báo cáo &#62; Báo Cáo Đơn Hàng Hủy Trong Kỳ</div>
				<div align="right" style="padding: 5px" class="tbnavigation">
					<%=Utility.GLanguage("Chào mừng bạn",session,jedis) %>  <%= userTen %></div>
			</div>

			
			<div align="left" style="width: 100%; float: none; clear: left">
				<fieldset>

					<legend class="legendtitle"> <%=Utility.GLanguage("Báo lỗi nhập liệu",session,jedis) %></legend>
					<textarea id="errors" value="<%= session.getAttribute("errors") %>" 
						name="errors" rows="1" style="width: 100% ; color:#F00 ; font-weight:bold"></textarea>
				</fieldset>
			</div>
			<div align="left"
				style="width: 100%; float: none; clear: left; font-size: 0.7em">
				<fieldset>
					<legend class="legendtitle">Báo cáo đơn hàng hủy trong kỳ</legend>
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
											if(ddkd.getString("pk_seq").equals(obj.getddkdId())) {
											%>
											<option value="<%=ddkd.getString("pk_seq")%>" selected><%=ddkd.getString("ten")%></option>
											<%} else { %>
											<option value="<%=ddkd.getString("pk_seq")%>" ><%=ddkd.getString("ten")%></option>
											<%}}} %>
											</select></TD>
									<TD class="plainlabel">Khách Hàng</TD>
									<TD class="plainlabel">
									<select name="khachhangId" id="khachhangId" onchange="seach();">
									<option value="" >All</option>
											<% if(khachhang !=null){ 
											while(khachhang.next())
											{ 
											if(khachhang.getString("pk_seq").equals(obj.getkhachhangId())) {
											%>
												<option value="<%=khachhang.getString("pk_seq")%>" selected><%=khachhang.getString("ten")%></option>
											<%} else { %>
												<option value="<%=khachhang.getString("pk_seq")%>" ><%=khachhang.getString("ten")%></option>
											<%}}} %>
									</select></TD>
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
						<div style="width: 100%; float: none; display: none">
							<div style="width: 30%; float: left">
								<fieldset style="min-height: 200px">
									<legend class="legendtitle"> Fields hien </legend>
									<TABLE width="100%" border="0" cellspacing="1" cellpadding="4"
										id="tbShowFields">
										<tbody id="ShowFields">
											<tr class="tbheader">
												<th></th>
												<th align="center">Chon an</th>
											</tr>
											<tr class="tbdarkrow">
												<td>Người Xóa</td>
												<td align="center"><input name="fieldsHien"
													type="checkbox" value="Nguoi_xoa"></td>
											</tr>
											<tr class="tblightrow">
												<td>Ngày Xóa</td>
												<td align="center"><input name="fieldsHien"
													type="checkbox" value="Ngay_xoa"></td>
											</tr>
											<tr class="tbdarkrow">
												<td>Tên khách hàng</td>
												<td align="center"><input name="fieldsHien"
													type="checkbox" value="Ten_khach_hang"></td>
											</tr>
											<tr class="tblightrow">
												<td>Số hóa đơn</td>
												<td align="center"><input name="fieldsHien"
													type="checkbox" value="So_hoa_don"></td>
											</tr>
											<tr class="tbdarkrow">
												<td>Ngày hóa đơn</td>
												<td align="center"><input name="fieldsHien"
													type="checkbox" value="Ngay_hoa_don"></td>
											</tr>
											<tr class="tblightrow">
												<td>Tên hàng</td>
												<td align="center"><input name="fieldsHien"
													type="checkbox" value="Ten_hang"></td>
											</tr>
											<tr class="tbdarkrow">
												<td>Tổng tiền</td>
												<td align="center"><input name="fieldsHien"
													type="checkbox" value="Tong_tien"></td>
											</tr>
										</tbody>
									</TABLE>
								</fieldset>
							</div>
							<div
								style="width: 35px; float: left; min-height: 200px; vertical-align: middle;display: none"
								align="center">
								<br> <br> <br> <img src="../images/Back30.png"
									border="0" class="imageClick" onClick="ChuyenSangPhai();"><br />
								<br> <br> <img src="../images/Next30.png" border="0"
									class="imageClick" onClick="ChuyenSangTrai();">
							</div>
							<div style="width: 30%; float: left;display: none">
								<fieldset style="min-height: 200px;">
									<legend class="legendtitle"> Tat cac cac Fields </legend>
									<TABLE width="100%" border="0" cellspacing="1" cellpadding="4"
										id="tbAllFields">
										<tbody id="AllFields">
											<tr class="tbheader">
												<th></th>
												<th align="center">Chon hien</th>
											</tr>
											<tr class="tbdarkrow">
												<td>Chiết Khấu</td>
												<td align="center"><input name="fieldsAn"
													type="checkbox" value="Chiet_khau"></td>
											</tr>
											<tr class="tblightrow">
												<td>Mã Hàng</td>
												<td align="center"><input name="fieldsAn"
													type="checkbox" value="Ma_hang"></td>
											</tr>
											<tr class="tbdarkrow">
												<td>CTKM</td>
												<td align="center"><input name="fieldsAn"
													type="checkbox" value="CTKM"></td>
											</tr>
											<tr class="tblightrow">
												<td>Địa Chỉ</td>
												<td align="center"><input name="fieldsAn"
													type="checkbox" value="Dia_chi"></td>
											</tr>
											<tr class="tbdarkrow">
												<td>Mã Khách Hàng</td>
												<td align="center"><input name="fieldsAn"
													type="checkbox" value="Ma_khach_hang"></td>
											</tr>
											<tr class="tblightrow">
												<td>Số Lượng</td>
												<td align="center"><input name="fieldsAn"
													type="checkbox" value="So_luong"></td>
											</tr>
											<tr class="tbdarkrow">
												<td>Đơn Giá</td>
												<td align="center"><input name="fieldsAn"
													type="checkbox" value="Don_gia"></td>
											</tr>
										</tbody>
									</TABLE>
								</fieldset>
							</div>
							
						
						</div>
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
		if(khachhang!=null){
		khachhang.close();
		}
		/* if(obj != null){
	    obj.DbClose();
		;} */
	}catch(java.sql.SQLException e){}
	
%>