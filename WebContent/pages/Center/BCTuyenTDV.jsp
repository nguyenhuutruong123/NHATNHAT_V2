<%@page import="geso.dms.center.beans.stockintransit.IStockintransit"%>
<%@page import="java.sql.ResultSet"%>
<%@page import="java.util.Calendar"%>
<%@page import="java.sql.Date"%>
<%@ page  import = "geso.dms.center.util.*" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%
	String userTen = (String)session.getAttribute("userTen");
	String userId = (String)session.getAttribute("userId");
	IStockintransit obj = (IStockintransit)session.getAttribute("obj");
	ResultSet rsKenh = obj.getkenh();
	ResultSet rsKhuVuc = obj.getkhuvuc();
	ResultSet rsVung = obj.getvung();
	
	ResultSet rsNpp = obj.getnpp();
	ResultSet rsGsbh = obj.getgsbh();
	ResultSet rsDdkd = obj.getRsddkd();
	
	Utility util = (Utility) session.getAttribute("util");
	String url="";
	url = util.getUrl("BCTuyenNVBH","");
	
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
		var fieldHidden = document.getElementsByName("fieldsAn");
		for ( var i = 0; i < fieldShow.length; ++i) {
			fieldShow.item(i).checked = true;
		}
		for ( var i = 0; i < fieldHidden.length; ++i) {
			fieldHidden.item(i).checked = true;
		}
		document.forms["frm"]["action"].value = "create";
		document.forms["frm"].submit();
		reset();
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
$(document).ready(function()
{
	$("#nppId").select2();
	$("#vungId").select2();
	$("#khuvucId").select2();
	$("#nhanhangId").select2();
	$("#chungloaiId").select2();
	$("#programs").select2();
	$("#ddkdId").select2();
	$("#gsbhId").select2();
	$("#kenhId").select2();
	$("#dvdlId").select2();
});
</script>

</head>
<BODY leftmargin="0" bottommargin="0" topmargin="0" rightmargin="0">
	<form name="frm" method="post" action="../../BCTuyenNVBH">
		<input type="hidden" value="1" name="action"  >
		<div id="main" style="width: 99%; padding-left: 2px">
			<div align="left" id="header" style="width: 100%; float: none">
				<div style="width: 70%; padding: 5px; float: left"
					class="tbnavigation">&nbsp;<%=url %></div>
				<div align="right" style="padding: 5px" class="tbnavigation">
					<%=Utility.GLanguage("Chào mừng bạn",session,jedis) %>  <%=userTen %></div>
			</div>
			<div align="left" style="width: 100%; float: none; clear: left">
				<fieldset>
					<legend class="legendtitle"> <%=Utility.GLanguage("Báo lỗi nhập liệu",session,jedis) %></legend>
					<textarea id="errors" 	name="errors" rows="1" style="width: 100% ; color:#F00 ; font-weight:bold">
					<%= obj.getMsg() %>
					</textarea>
				</fieldset>
			</div>
			<div align="left"
				style="width: 100%; float: none; clear: left; font-size: 0.7em">
				<fieldset>
					<legend class="legendtitle">Đánh giá cửa hiệu</legend>
					<div style="width: 100%; float: none" align="left">
						<div style="width: 100%; float: none" align="left"
							class="plainlabel">
							<TABLE width="70%" cellpadding="6" cellspacing="0">
								<TR>
									<TD class="plainlabel" ><%=Utility.GLanguage("Từ ngày",session,jedis) %></TD>
									<TD class="plainlabel" ><input type="text" name="Sdays"
										class="days" value='<%=obj.gettungay() %>' /></TD>
									<TD class="plainlabel" ><%=Utility.GLanguage("Đến ngày",session,jedis) %></TD>
									<TD class="plainlabel"><input type="text" name="Edays"
										class="days" value='<%=obj.getdenngay() %>' /></TD>
								</TR>

								<TR>									
									<TD class="plainlabel"><%=Utility.GLanguage("NHÂN VIÊN BÁN HÀNG",session,jedis) %></TD>
									<TD class="plainlabel" colspan="2">
										<select value="" name="ddkds"  id="ddkdId" style="width:202px">
											<option value="">All</option>
											<% if(rsDdkd!=null){
													while(rsDdkd.next()){
														String ddkdId = rsDdkd.getString(1);
														if(ddkdId.equals(obj.getDdkd())){%>
															<option selected="selected" value="<%=rsDdkd.getString(1)%>"><%=rsDdkd.getString(2)%></option>
														<%}else{%>
														<option value="<%=rsDdkd.getString(1)%>"><%=rsDdkd.getString(2)%></option>
											<%}}}%>
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
	<%
	try{
		if(rsKenh !=null)rsKenh.close();
		if(rsVung!=null)rsVung.close();
		if(rsKhuVuc!=null)rsKhuVuc.close();
		if(rsNpp!=null)rsNpp.close();
		if(rsDdkd!=null)rsDdkd.close();
		if(rsGsbh!=null)rsGsbh.close();
		if(obj!=null){obj.DBclose();
		obj = null;}
		session.setAttribute("obj", null);
	}catch(Exception er){
		
	}
	%>
</body>
</HTML>