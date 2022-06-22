<%@page import="geso.dms.center.beans.stockintransit.IStockintransit"%>
<%@page import="java.sql.SQLException"%>

<%@page import="java.util.concurrent.ExecutionException"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page  import = "java.util.Iterator" %>
<%@ page  import = "java.util.List" %>
<%@ page  import = "java.util.ArrayList" %>
<%@ page  import = "java.sql.ResultSet" %>
<%@ page  import = "java.text.DecimalFormat" %>

<% IStockintransit obj = (IStockintransit) session.getAttribute("obj"); %>
<% String userId = (String) session.getAttribute("userId");  %>
<% String userTen = (String) session.getAttribute("userTen");  %>
<% String pattern = "###,###.###";
	ResultSet npp = (ResultSet)obj.getRsnppupload();
	ResultSet ddkd = (ResultSet)obj.getRsddkd();
	ResultSet kh = (ResultSet)obj.getkh();
	ResultSet vung = (ResultSet)obj.getvung();
	ResultSet khuvuc = (ResultSet)obj.getkhuvuc();
	String nppId = obj.getnppId();
   DecimalFormat formatter = new DecimalFormat(pattern); %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd"> <% Utility Utilback = new Utility(); %>
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
	<title><%= getServletContext().getInitParameter("TITLENAME") %></title>
	<META http-equiv="Content-Type" content="text/html; charset=utf-8">
	<META http-equiv="Content-Style-Type" content="text/css">
    <LINK rel="stylesheet" type="text/css" media="print" href="../css/impression.css">
    <LINK rel="stylesheet" href="../css/main.css" type="text/css">
    <LINK rel="stylesheet" href="../css/datepicker.css" type="text/css">
    
    <script language="javascript" src="../scripts/datepicker.js"></script>
   	<link href="../css/jquery-ui.css" rel="stylesheet" type="text/css"/>
  	<script type="text/javascript" src="../scripts/Timepicker/jquery.min.js"></script>
  	<script type="text/javascript" src="../scripts/Timepicker/jquery-ui.min.js"></script>
  	<script type="text/javascript" src="../scripts/phanTrang.js"></script>
  	<script type="text/javascript" src="../scripts/dropdowncontent.js"></script>
	<script type="text/javascript" src="../scripts/jquery.min.1.7.js"></script>
	<link href="../css/jquery-ui.css" rel="stylesheet" type="text/css" />
	<link href="../css/select2.css" rel="stylesheet" />
	<script src="../scripts/select2.js"></script>
	<script>
	     $(document).ready(function() { 
      $("select:not(.notuseselect2)").select2({ width: 'resolve' });     
     });
    </script>
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
	        }); 		
			
	</script>
   
  	<script type="text/javascript" src="..scripts/jquery-1.js"></script>
	<link type="text/css" rel="stylesheet" href="../css/mybutton.css">
    <script type="text/javascript">
        $(document).ready(function(){
            $(".button").hover(function(){
                $(".button img")
                .animate({top:"-10px"}, 200).animate({top:"-4px"}, 200) // first jump
                .animate({top:"-7px"}, 100).animate({top:"-4px"}, 100) // second jump
                .animate({top:"-6px"}, 100).animate({top:"-4px"}, 100); // the last jump
            });
        }); 
		$(document).ready(function(){
            $(".button2").hover(function(){
                $(".button2 img")
                .animate({top:"-10px"}, 200).animate({top:"-4px"}, 200) // first jump
                .animate({top:"-7px"}, 100).animate({top:"-4px"}, 100) // second jump
                .animate({top:"-6px"}, 100).animate({top:"-4px"}, 100); // the last jump
            });
        }); 
		$(document).ready(function(){
            $(".button3").hover(function(){
                $(".button3 img")
                .animate({top:"-10px"}, 200).animate({top:"-4px"}, 200) // first jump
                .animate({top:"-7px"}, 100).animate({top:"-4px"}, 100) // second jump
                .animate({top:"-6px"}, 100).animate({top:"-4px"}, 100); // the last jump
            });
        }); 
    </script>
    <SCRIPT language="javascript" type="text/javascript">
	 
	 function submitform()
	 { 
			var tungay = document.getElementById("tungay").value;
			var denngay = document.getElementById("denngay").value;
			
			if (tungay == "" || denngay == "")
				{
				alert('Vui lòng chọn từ ngày đến ngày!')
				return;
				}

			document.forms['erpDmhForm'].action.value = 'taobaocao';
			document.forms["erpDmhForm"].submit();
	 }
	 
	 function changeVung()
	 { 
			document.forms["erpDmhForm"].submit();
	 }
	 
	 function taobaocao()
	 { 
		 if(document.getElementById("nam").value == "")
		 {
			 alert('Vui lòng chọn năm');
			 return;
		 }
		 
		 if(document.getElementById("thang").value == "")
		 {
			 alert('Vui lòng chọn tháng');
			 return;
		 }
		 
		 if(document.getElementById("sanphamId").value == "")
		 {
			 alert('Vui lòng chọn sản phẩm');
			 return;
		 }
		 
		 document.forms["erpDmhForm"].action.value = "taobaocao";
	     document.forms["erpDmhForm"].submit();
	 }
	 
	 function thongbao()
	 {
		 var tt = document.forms["erpDmhForm"].msg.value;
	 	 if(tt.length>0)
	     	alert(document.forms["erpDmhForm"].msg.value);
	 }
	 
	</SCRIPT>
	<style>
	 .select2-container {
	 	text-align: left;
	 }
	</style>

</head>
<body>
<form name="erpDmhForm" method="post" action="../../BCTheoDoiTienTrinhHopDong">
<input type="hidden" name="userId" value="<%= userId %>" >
<input type="hidden" name="userTen" value="<%= userTen %>" >
<input type="hidden" name="action" value="1" >
<input type="hidden" name="view" value="<%=obj.getView() %>" >
<div id="main" style="width:100%; padding-left:2px">
	<div align="left" id="header" style="width:100%; float:none">
    	<div style="width:70%; padding:5px; float:left" class="tbnavigation">
        	&#160; Quản lý bán hàng > Báo cáo > Báo cáo theo dõi tiến trình hợp đồng
        </div>
        <div align="right" style="padding:5px" class="tbnavigation">
        	Chào mừng <%= userTen %> &nbsp;
        </div>
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
    
  	<div id="cotent" style="width:100%; float:none">
    	<div align="left" style="width:100%; float:none; clear:left; font-size:0.7em">
        <fieldset style="margin-top:5px" >
            <legend class="legendtitle"> Báo cáo theo dõi tiến trình hợp đồng </legend>
            <TABLE width="100%" cellpadding="6px" cellspacing="0px" style="margin-top: 5px " >								                          
                			
                			<tr class="plainlabel">
										<TD class="plainlabel"><%=Utility.GLanguage("Từ ngày",session,jedis) %> <FONT class="erroralert"> *</FONT></TD>
											<TD class="plainlabel">	<input type="text" name="tungay" id="tungay" class="days" value='<%= obj.gettungay() %>' />
											</TD>
											<TD class="plainlabel"><%=Utility.GLanguage("Đến ngày",session,jedis) %> <FONT class="erroralert"> *</FONT></TD>
											<TD class="plainlabel">
												<input type="text" name="denngay" id="denngay" class="days" value='<%= obj.getdenngay() %>'/>
											</TD>
							</TR>
							
							<% if (nppId == null || nppId == "") {%>
							<tr class="plainlabel" style="display:none;">
                			<TD class="plainlabel" style="display:none;"><%=Utility.GLanguage("Vùng",session,jedis) %></TD>
									<TD class="plainlabel" colspan="3">
										<select name="vungId" id="vungId"	onChange="changeVung();" style="width: 300px; text-align: left !important;">
											<option value="" selected></option>
											<% try {
											if (vung != null)
													while (vung.next()) {
														if (vung.getString("pk_seq").equals(obj.getvungId())) {%>
														<option value="<%=vung.getString("pk_seq")%>" selected><%=vung.getString("ten")%></option>
													<%} else {%>
														<option value="<%=vung.getString("pk_seq")%>"><%=vung.getString("ten")%></option>
													<%}} } catch (SQLException e){e.printStackTrace();} %>
										</select>
									</TD>
                			</tr>
                			
                			
                			<tr class="plainlabel">
                			<TD class="plainlabel"><%=Utility.GLanguage("Khu vực",session,jedis) %></TD>
									<TD class="plainlabel" colspan="3">
										<select name="kvId" id=""kvId""	 style="width: 300px; text-align: left !important;">
											<option value="" selected></option>
											<% try {
											if (khuvuc != null)
													while (khuvuc.next()) {
														if (khuvuc.getString("pk_seq").equals(obj.getkhuvucId())) {%>
														<option value="<%=khuvuc.getString("pk_seq")%>" selected><%=khuvuc.getString("ten")%></option>
													<%} else {%>
														<option value="<%=khuvuc.getString("pk_seq")%>"><%=khuvuc.getString("ten")%></option>
													<%}} } catch (SQLException e){e.printStackTrace();} %>
										</select>
									</TD>
                			</tr>
							
							<% } %>
							
							<tr class="plainlabel">
                			<TD class="plainlabel">Nhà phân phối</TD>
									<TD class="plainlabel" colspan="3">
										<select name="nppId" id="nppId"	 style="width: 300px; text-align: left !important;">
											<option value="" selected></option>
											<% try {
											if (npp != null)
													while (npp.next()) {
														if (npp.getString("pk_seq").equals(obj.getnppId())) {%>
														<option value="<%=npp.getString("pk_seq")%>" selected><%=npp.getString("ten")%></option>
													<%} else {%>
														<option value="<%=npp.getString("pk_seq")%>"><%=npp.getString("ten")%></option>
													<%}} } catch (SQLException e){e.printStackTrace();} %>
										</select>
									</TD>
                			</tr>
                			
                			<tr class="plainlabel">
                			<TD class="plainlabel">Khách hàng</TD>
									<TD class="plainlabel" colspan="3">
										<select name="khId" id="khId"	 style="width: 300px; text-align: left !important;">
											<option value="" selected></option>
											<% try {
											if (kh != null)
													while (kh.next()) {
														if (kh.getString("pk_seq").equals(obj.getkhId())) {%>
														<option value="<%=kh.getString("pk_seq")%>" selected><%=kh.getString("ten")%></option>
													<%} else {%>
														<option value="<%=kh.getString("pk_seq")%>"><%=kh.getString("ten")%></option>
													<%}} } catch (SQLException e){e.printStackTrace();} %>
										</select>
									</TD>
                			</tr>

							<TR>
									<TD class="plainlabel" colspan="4"><a class="button" href="javascript:submitform();"> 
										<img style="top: -4px;" src="../images/button.png" alt=""> Xuất báo cáo
									</a>
									</td>
								</TR>
             </TABLE>                  
       </fieldset>            					                    
    	</div>
    	
    	
    </div>  
</div>
</form>
</body>
</HTML>