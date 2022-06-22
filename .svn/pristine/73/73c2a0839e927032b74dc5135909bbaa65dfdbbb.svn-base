<%@page import="java.sql.ResultSet"%>
<%@page import="geso.dms.center.beans.quanhuyen.IQuanhuyen"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page  import = "geso.dms.center.util.*" %>

<% 
	String userId = (String) session.getAttribute("userId");
	String userTen = (String) session.getAttribute("userTen");
	IQuanhuyen quan = (IQuanhuyen)session.getAttribute("quan");
	ResultSet tinhRs = quan.getTinhRs();
	session.setAttribute("db", null);
	
	String sum = (String) session.getAttribute("sum");
	Utility util = (Utility) session.getAttribute("util");
	String url="";
	url = util.getChuyenNguUrl("QuanhuyenSvl","",session);
	System.out.print(url);
	if(!util.check(userId, userTen, sum)){
		response.sendRedirect(request.getContextPath() + "/redirect.jsp");
	}else{
		int[] quyen = new  int[6];
		quyen = util.Getquyen("QuanhuyenSvl","",userId);
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd"> <% Utility Utilback = new Utility(); %>
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
	<title><%= getServletContext().getInitParameter("TITLENAME") %></title>
	<META http-equiv="Content-Type" content="text/html; charset=utf-8">
	<META http-equiv="Content-Style-Type" content="text/css">
	
	<!-- Bootstrap 3.3.7 -->
  <link rel="stylesheet" href="../css/bower_components/bootstrap/dist/css/bootstrap.min.css">
  <!-- Font Awesome -->
  <link rel="stylesheet" href="../css/bower_components/font-awesome/css/font-awesome.min.css">
  <!-- Ionicons -->
  <link rel="stylesheet" href="../css/bower_components/Ionicons/css/ionicons.min.css">
  <!-- jvectormap -->
  <link rel="stylesheet" href="../css/bower_components/jvectormap/jquery-jvectormap.css">
  <!-- datepicker -->
  <link rel="stylesheet" href="../css/bower_components/bootstrap-datepicker/dist/css/bootstrap-datepicker.css">
  <!-- DataTables -->
  <link rel="stylesheet" href="../css/bower_components/datatables.net-bs/css/dataTables.bootstrap.min.css">
  <!-- Theme style -->
  <link rel="stylesheet" href="../css/dist/css/AdminLTE.min.css">
  <link rel="stylesheet" href="../css/dist/css/skins/_all-skins.min.css">
   <!-- Select2 -->
 <link href="../css/bower_components/select2/css/select2.min.css" rel="stylesheet" />
  <!-- Google Font -->
 <!--  <link rel="stylesheet" href="https://fonts.googleapis.com/css?family=Source+Sans+Pro:300,400,600,700,300italic,400italic,600italic"> -->
   
   <style>
 	ul.chaomung{
    list-style-type: none;
    margin: 1px;
    padding: 9px;
    overflow: hidden;
    
    font-family: Roboto, Arial, Helvetica, sans-serif;
	font-size: 9pt;
	font-weight: bold;
	font-style : normal;
	background-color: #80CB9B;
}

ul.chaomung li {
    float: left;
}

ul.chaomung li a {
    display: block;
    color: white;
    text-align: center;
    padding: 14px 16px;
    text-decoration: none;
}

/* Change the link color to #111 (black) on hover */
ul.chaomung li a:hover {
    background-color: #111;
}

.colorheader
{
	background-color: #80CB9B;
}
 </style>
	
    <SCRIPT language="javascript" type="text/javascript">
	 function saveform() {
		 var ten = document.getElementById("ten").value;
		 //var ma = document.getElementById("ma").value;
		 var tinh = document.getElementById("tinhId").value;

		 /* if(ma == '')
		 {
		 	alert("Vui lòng nhập mã cho quận huyện.");
		 	return;
		 } */
		 
		 if(ten == '')
		 {
		 	alert("Vui lòng nhập tên cho quận huyện.");
		 	return;
		 }
		 
		 if(tinh == '')
		 {
		 	alert("Vui lòng chọn tỉnh cho quận huyện.");
		 	return;
		 }
		 
		 document.forms["ckParkForm"].action.value= 'save';
		 document.forms["ckParkForm"].submit();
	 }
	</SCRIPT>
	
	<link href="../css/select2.css" rel="stylesheet" />
	<script src="../scripts/select2.js"></script>
	<script>
		$(document).ready(function()
		{
			$(".select2").select2();
		});
	</script>
	
	<script type="text/javascript" src="../scripts/cool_DHTML_tootip.js"></script>
	<style type="text/css">
		#dhtmltooltip
		{
			position: absolute;
			left: -300px;
			width: 150px;
			border: 1px solid black;
			padding: 2px;
			background-color: lightyellow;
			visibility: hidden;
			z-index: 100;
			/*Remove below line to remove shadow. Below line should always appear last within this CSS*/
			filter: progid:DXImageTransform.Microsoft.Shadow(color=gray,direction=135);
		}	
		#dhtmlpointer
		{
			position:absolute;
			left: -300px;
			z-index: 101;
			visibility: hidden;
		}
		th {
		cursor: pointer;
		}	
  	</style>
	
	
</head>
<body>
<!-- <form name="ckParkForm" method="post" action="../../QuanhuyenUpdateSvl"> -->
<form class="needs-validation" novalidate name="ckParkForm" method="post" id="reg_form" action="../../QuanhuyenUpdateSvl"> 
<% redis.clients.jedis.Jedis jedis =  geso.dms.center.util.Utility.getJedis(); %><% geso.dms.center.util.Csrf csdr=new geso.dms.center.util.Csrf(request,response,true,false,true);%>
 <input type="hidden" name="<%=csdr.get_tokenName() %>" value='<%=csdr.get_tokenValue() %>'>

<input type="hidden" name="userId" value="<%= userId %>" >
<input type="hidden" name="quanhuyenId" value="<%= quan.getId() %>" >
<input type="hidden" name="action" value="1" >
<input type="hidden" name="type" value="<%=quan.getType()%>" >
<input type="hidden" name="id" value="<%=quan.getId()%>" >

<!-- Navigator chao mung -->
<ul class="chaomung">
<li><%=url %> <% if(quan.getId().length() > 0) { %> > <%=quan.getType().equals("update")? Utility.GLanguage("Cập nhật",session,jedis):Utility.GLanguage("Hiển thị",session,jedis) %> <%} else { %> > <%=Utility.GLanguage("Tạo mới",session,jedis) %> <%} %> </li>
<li style="float:right"><%=Utility.GLanguage("Chào mừng bạn",session,jedis) %>  <%=userTen %></li>
</ul> 

<div class="box" style="border: 1px solid #dddbdb; background-color: #C5E8CD; margin-left: 2pt;">
	<div class="box-header">
		<div class="form-group col-md-6">
		  <h3 class="box-title"><%=Utility.GLanguage("Thông tin quận huyện",session,jedis) %></h3>
		</div> 
	</div>
	
	<div class="box-body">
	
	<% if(quan.getMsg().length() > 0) { %>
		<div class="col-md-12">
		<div class="form-group">
		   <div class="col-md-1"><label><%= quan.getMsg() %></label></div> 
        </div>
        </div>
     <%} %>
     
		<div  class="col-md-12">
		<div class="form-group">
		   <div class="col-md-1"><label><%=Utility.GLanguage("Mã",session,jedis) %></label></div> 
           <input type="text" name="ma" id="ma" required="" value='<%= quan.getMa() %>' style="width:400px">
        </div>
        </div>
        
        <div class="col-md-12">
		<div class="form-group">
		   <div class="col-md-1"><label><%=Utility.GLanguage("Tên",session,jedis) %></label></div> 
           <input type="text" name="ten" id="ten" required="" value='<%= quan.getTen() %>' style="width:400px">
        </div>
        </div>
        
        <div class="col-md-12">
		<div class="form-group">
            <div class="col-md-1"><label><%=Utility.GLanguage("Tỉnh",session,jedis) %></label></div>
            
            <select class="select2" name="tinhId" id="tinhId" required="" style="width:300px">
           	<option value=""></option>
           	<%
           	if (tinhRs != null) {
           		while (tinhRs.next()) {
           			if (tinhRs.getString("PK_SEQ").equals(quan.getTinhId())) {
           				%>
           	<option value="<%= tinhRs.getString("PK_SEQ") %>" selected="selected"><%= tinhRs.getString("TEN") %></option>
           				<%
           			} else {
           				%>
           	<option value="<%= tinhRs.getString("PK_SEQ") %>"><%= tinhRs.getString("TEN") %></option>
           				<%
           			}
           		}
           		tinhRs.close();
           	}
           	%>
           </select>
        </div>
        </div>
        
        <div class="col-md-12" style="margin-top: 10px;">
		<div class="form-group">
        <div class="col-md-4">
        <%-- <a class="button" id ="imgText1" style="border: 1px solid #80CB9B; padding: 6px; margin-right: 5px;  background: white;" href="../../QuanhuyenSvl?userId=<%=userId %>">Quay về</a>
        <a class="button" id ="imgText2" style="border: 1px solid #80CB9B; padding: 6px; margin-right: 5px;  background: white;" href="javascript:saveform()">Lưu lại</a> --%>
        
        <a class="btn btn-default" href="../../RouterSvl?task=<%= Utilback.dongMa(getServletContext().getInitParameter("RedirectNoScript") +"QuanhuyenSvl?userId="+userId+"") %>"><%=Utility.GLanguage("Quay về",session,jedis) %></a>
      	<%if(quan.getType().equals("update") || quan.getId().length()==0){ %>
      		<a class="btn btn-primary" href="javascript:saveform()"><%=Utility.GLanguage("Lưu lại",session,jedis) %></a>
      	<%} %>
		</div></div>
		</div>
	</div>
</div>

<%--
< table width="100%" border="0" cellspacing="0" cellpadding="0" height="100%">
	<tr>
		<td colspan="4" align='left' valign='top' bgcolor="#ffffff">
			<table width="100%">
				<tr>
					<td align="left" class="tbnavigation">
						<table width="100%" border="0" cellpadding="0" cellspacing="0">
							<tr height="22">
								<td align="left" colspan="2" class="tbnavigation">
							   		&nbsp;<%= url %> &gt; <% if(quan.getId().length() > 0) { %> Cập nhật <%} else { %> Tạo mới <%} %> 
							   	</td>
							   	<td colspan="2" align="right" class="tbnavigation">
							   		<%=Utility.GLanguage("Chào mừng bạn",session,jedis) %>  <%=userTen %>&nbsp;  
							   	</td> 
						    </tr>
   
						   	<tr>
						   		<td align="left" height="5" colspan="4" class="">
						   		</td>
  							</tr>
						</TABLE>
					</td>
				</tr>
			</table>	
			<table width="100%" border="0" cellpadding="3" cellspacing="0">
				<tr>
					<td>
						<table width="100%" border="0" cellpadding="0" cellspacing="0">
							<tr class = "tbdarkrow">
								<td width="30" align="left"><A href="../../QuanhuyenSvl?userId=<%=userId %>" ><img src="../images/Back30.png" alt="Quay ve"  title="Quay ve" border="1" longdesc="Quay ve" style="border-style:outset"></A></td>
								<td width="2" align="left" ></td>
								<td width="30" align="left" ><A href="javascript: saveform()" ><IMG src="../images/Save30.png" title="Luu lai" alt="Luu lai" border = "1"  style="border-style:outset"></A></td>
								<td>&nbsp; </td>						
							</tr>
						</TABLE>
				</td></tr>
			</table>
				
			<table width = 100% cellpadding = "3" cellspacing = "0" border = "0">
			  	<tr>
					<td align="left" colspan="4" class="legendtitle">
						<fieldset>
						<legend class="legendtitle"><%=Utility.GLanguage("Báo lỗi nhập liệu",session,jedis) %></legend>
				
		    				<textarea name="dataerror"  style="width: 100% ; color:#F00 ; font-weight:bold" style="width: 100%" readonly="readonly" rows="1" ><%= quan.getMsg() %></textarea>
						</fieldset>
				   </td>
				</tr>
				
				<tr>
					<td align="left" colspan="4" class="legendtitle">
						<fieldset>
						<legend class="legendtitle">Thông tin quận huyện
						</legend>
						<table class="tblight" width="100%" cellspacing="0" cellpadding="6">
						
						<tr>
							<td width="15%" class="plainlabel">Mã <FONT class="erroralert">*</FONT></td>
							<td class="plainlabel">
								<input name="ma" id="ma" type="text" value='<%= quan.getMa() %>' style="width:400px">
							</td>
						</tr>
						
						<tr>
							<td width="15%" class="plainlabel">Tên <FONT class="erroralert">*</FONT></td>
							<td class="plainlabel">
								<input name="ten" id="ten" type="text" value='<%= quan.getTen() %>' style="width:400px">
							</td>
						</tr>
						
						<tr>
							<td class="plainlabel">Tỉnh <FONT class="erroralert">*</FONT></td>
							<td class="plainlabel">
								<select class="select2" name="tinhId" id="tinhId">
									<option value=""></option>
									<%
									if (tinhRs != null) {
										while (tinhRs.next()) {
											if (tinhRs.getString("PK_SEQ").equals(quan.getTinhId())) {
												%>
									<option value="<%= tinhRs.getString("PK_SEQ") %>" selected="selected"><%= tinhRs.getString("TEN") %></option>
												<%
											} else {
												%>
									<option value="<%= tinhRs.getString("PK_SEQ") %>"><%= tinhRs.getString("TEN") %></option>
												<%
											}
										}
										tinhRs.close();
									}
									%>
								</select>
							</td>
						</tr>
						</table>
						</fieldset>
					</td>	
				</tr>
			</table>
		</td>
	</tr>
</table> --%><%geso.dms.center.util.Utility.JedisClose(jedis); %>
</form>

<!-- jQuery 3 -->
<script src="../css/bower_components/jquery/dist/jquery.min.js"></script>
<script src="../css/bower_components/datatables.net/js/jquery.dataTables.min.js"></script>
<script src="../css/bower_components/datatables.net-bs/js/dataTables.bootstrap.min.js"></script>
<script src="../css/bower_components/bootstrap-datepicker/dist/js/bootstrap-datepicker.js"></script>
<script src="../css/bower_components/select2/js/select2.min.js"></script>

<script>
$(document).ready(function() { 
	$('.select2').select2(); 
});
</script>

</body>
</HTML>
<%
	quan.getDb().shutDown();
	}
%>