<%@page import="java.sql.ResultSet"%>
<%@page import="geso.dms.center.beans.stockintransit.imp.Stockintransit"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

    <%@ page  import = "geso.dms.center.util.*" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd"> <% Utility Utilback = new Utility(); %>
<html>
<head>

<% String userId = (String) session.getAttribute("userId");
   String userTen = (String) session.getAttribute("userTen");
   Stockintransit obj = (Stockintransit) session.getAttribute("obj");
   ResultSet vung = (ResultSet) obj.getvung();
   ResultSet khuvuc = (ResultSet) obj.getkhuvuc();
   ResultSet rsNPP = (ResultSet) obj.getnpp();
   String sum = (String) session.getAttribute("sum");
   Utility util = (Utility) session.getAttribute("util");
	if(!util.check(userId, userTen, sum)){
		response.sendRedirect("/NTPM/index.jsp");
	}else{int[] quyen = new  int[6];
	quyen = util.Getquyen("BCXuatKhoBarcode","",userId);
	
%>
<% String nnId = (String)session.getAttribute("nnId"); %> 
<% if(nnId == null) {
 nnId = "vi"; 
 }
String url = util.getUrl("BCNopTienNPP","");	
 %>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Báo cáo nộp tiền NPP/KH</title>
<LINK rel="stylesheet" type="text/css" media="print" href="../css/impression.css">
<LINK rel="stylesheet" href="../css/main.css" type="text/css">
<link type="text/css" rel="stylesheet" href="../css/mybutton.css">
<LINK rel="stylesheet" href="../css/calendar.css" type="text/css">
<script type="text/javascript" src="../scripts/jquery.min.1.7.js"></script>
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
        }); 		
		
</script>
<script type="text/javascript" language="JavaScript" src="../scripts/simplecalendar.js"></script>
<script type="text/javascript">
	function submitform()
	{
		var tungay = document.forms['frm']['Sdays'].value;
		if(tungay.length==0) 
			{
				document.forms['frm']['errors'].value="Chọn từ ngày";
				return;
			}
		var denngay = document.forms['frm']['Edays'].value;
		if(denngay.length==0)
			{
				document.forms['frm']['errors'].value="Chọn đến ngày";
				return;
			}
		document.forms['frm']['action'].value="excel";
		document.forms['frm'].submit();
	}
	function submitformCT()
	{
		var tungay = document.forms['frm']['Sdays'].value;
		if(tungay.length==0) 
			{
				document.forms['frm']['errors'].value="Chọn từ ngày";
				return;
			}
		var denngay = document.forms['frm']['Edays'].value;
		if(denngay.length==0)
			{
				document.forms['frm']['errors'].value="Chọn đến ngày";
				return;
			}
		document.forms['frm']['action'].value="excel_chitiet";
		document.forms['frm'].submit();
	}
	
	
	function changeform()
	{
		document.forms['frm']['action'].value="change";
		document.forms['frm'].submit();
	}
	
</script>

<link href="../css/select2.css" rel="stylesheet" />
	<script src="../scripts/select2.js"></script>
	<script>
    	$(document).ready(function() { 
    		$("select:not(.notuseselect2)").select2(); 
    	});
    </script>	



</head>
<BODY leftmargin="0" bottommargin="0" topmargin="0" rightmargin="0">


	<form name="frm" method="post" action="../../BCNopTienNPP"> 
 
		<% redis.clients.jedis.Jedis jedis =  geso.dms.center.util.Utility.getJedis(); %><% geso.dms.center.util.Csrf csdr=new geso.dms.center.util.Csrf(request,response,true,false,true);%>
 <input type="hidden" name="<%=csdr.get_tokenName() %>" value='<%=csdr.get_tokenValue() %>'>
		<input type="hidden" name="action" value='1'> 
		<input type="hidden" name="userId" value='<%=userId%>'>
		<input type="hidden" name="userTen" value='<%=userTen%>'>
		<div id="main" style="width: 99%; padding-left: 2px">
			<div align="left" id="header" style="width: 100%; float: none">
				<div style="width: 70%; padding: 5px; float: left"
					class="tbnavigation"><%=" "+url %></div>
				<div align="right" style="padding: 5px" class="tbnavigation">
				<%=Utility.GLanguage("Chào mừng bạn",session,jedis) %> 
					<%=userTen%></div>
			</div>
			<div align="left" style="width: 100%; float: none; clear: left">
				<fieldset>
					<legend class="legendtitle"> <%=Utility.GLanguage("Báo lỗi nhập liệu",session,jedis) %></legend>
					<textarea id="errors" name="errors" rows="2"  style="width: 100% ; color:#F00 ; font-weight:bold">
						<%=obj.getMsg()%></textarea>
				</fieldset>
			</div>
			<div align="left"
				style="width: 100%; float: none; clear: left; font-size: 0.7em">
				<fieldset>
					<legend class="legendtitle">Báo cáo </legend>
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
									<TD class="plainlabel">Đối tượng</TD>
									<TD class="plainlabel" >
									                    		<select  name="type" >
																		<option value="0" <%= obj.gettype().equals("0") ? "selected":""  %>    > NPP </option>
																		<option value="1" <%= obj.gettype().equals("1") ? "selected":""  %>    > Khách hàng </option>
																		
																</select>
															</TD>
								</TR>								
								<TR>
									<td colspan="4">
										<a class="button"
										href="javascript:submitform();"> <img style="top: -4px;"
											src="../images/button.png" alt=""><%=Utility.GLanguage("Tạo báo cáo",session,jedis) %> </a>
										
									</td>
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
	
	<script type="text/javascript">
		$("select:not(.notuseselect2)").css({
			"width": "200px", 
			//"height": "200px"
		});
	</script>
	
</body>  <script type='text/javascript' src='../scripts/loadingv2.js'></script>

<%
	if(vung != null ){ vung.close(); vung = null; }
	if(khuvuc != null ){ khuvuc.close(); khuvuc = null; }
	if(rsNPP != null ){ rsNPP.close(); rsNPP = null; }
	
	obj.DBclose(); obj = null;
	session.setAttribute("obj",null);
	
	} %>
</HTML>