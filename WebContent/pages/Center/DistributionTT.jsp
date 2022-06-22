<%@page import="geso.dms.center.beans.stockintransit.IStockintransit"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page  import = "geso.dms.center.util.*" %>
<%@page import="java.sql.ResultSet"%>
<%@page import="java.text.DecimalFormat"%>
<%@page import="java.text.NumberFormat"%>
<% NumberFormat formatter = new DecimalFormat("#,###,###"); %>
<% NumberFormat formatter2 = new DecimalFormat("#,###,###.##");  %>
<%	String userId = (String) session.getAttribute("userId");  
	String userTen = (String) session.getAttribute("userTen"); 
	String sum = (String) session.getAttribute("sum");
	
	
	Utility util = (Utility) session.getAttribute("util");
	
	String url = util.getChuyenNguUrl("DistributionTT_Svl", "",session);
	IStockintransit obj = (IStockintransit)session.getAttribute("obj");
	ResultSet npp = obj.getNhaphanphoiRs();
	ResultSet ddkds = obj.getRsddkd();
	ResultSet sku = obj.getsanpham();
	ResultSet kenh = obj.getkenh();
	ResultSet vung = obj.getvung();
	ResultSet khuvuc = obj.getkhuvuc();
	ResultSet ttRs = obj.getTtRs();
	ResultSet spRs=obj.getSpRs();
	ResultSet chungloai=obj.getchungloai();
	ResultSet dvkd=obj.getdvkd();
	ResultSet nhomsp=obj.GetRsNhomSP();
	String wheresku =obj.getsanphamId();
	
	
	if(!util.check(userId, userTen, sum)){
		response.sendRedirect("/NTPM/index.jsp");
	}else{ %>


<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"> <% Utility Utilback = new Utility(); %>

<HTML>
<HEAD>
<TITLE><%= getServletContext().getInitParameter("TITLENAME") %></TITLE>

<!-- <script type="text/javascript" src="../scripts/jquery.min.1.7.js"></script>
<link href="../css/jquery-ui.css" rel="stylesheet" type="text/css" />
<script src="../scripts/ui/jquery.ui.core.js" type="text/javascript"></script>
<script src="../scripts/ui/jquery.ui.widget.js" type="text/javascript"></script>
<script src="../scripts/ui/jquery.ui.datepicker.js" type="text/javascript"></script>
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
   ul { height: 100px; overflow:auto; width: 100px; border: 1px solid #000; }
   ul { list-style-type: none; margin: 0; padding: 0; overflow-x: hidden; width: 298px }
   li { margin: 0; padding: 0; }
   label { display: block; color: WindowText; background-color: Window; margin: 0; padding: 0; width: 100%; }
   label:hover { background-color: Highlight; color: HighlightText; }
  </style>

	<link type="text/css" rel="stylesheet" href="../css/mybutton.css"> -->
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
  <link rel="stylesheet"
        href="https://fonts.googleapis.com/css?family=Source+Sans+Pro:300,400,600,700,300italic,400italic,600italic">

<!-- <LINK rel="stylesheet" type="text/css" media="print" href="../css/impression.css">
<LINK rel="stylesheet" href="../css/main.css" type="text/css">

<script type="text/javascript" src="../scripts/jquery-1.js"></script>
<link type="text/css" rel="stylesheet" href="../css/mybutton.css">
<LINK rel="stylesheet" type="text/css" href="../css/style.css" />

<script type="text/javascript" src="../scripts/jquery.min.1.7.js"></script>
<link href="../css/jquery-ui.css" rel="stylesheet" type="text/css" />
<script src="../scripts/ui/jquery.ui.core.js" type="text/javascript"></script>
<script src="../scripts/ui/jquery.ui.widget.js" type="text/javascript"></script>
<script src="../scripts/ui/jquery.ui.datepicker.js"
	type="text/javascript"></script>
<script type="text/javascript">
	
	$(document).ready(function() {
		$(".days").datepicker({
			changeMonth : true,
			changeYear : true
		});
		$(".button").hover(function() {
			$(".button img").animate({
				top : "-10px"
			}, 200).animate({
				top : "-4px"
			}, 200) // first jump
			.animate({
				top : "-7px"
			}, 100).animate({
				top : "-4px"
			}, 100) // second jump
			.animate({
				top : "-6px"
			}, 100).animate({
				top : "-4px"
			}, 100); // the last jump
		});
	});
	$(document).ready(function() {
		$(".button1").hover(function() {
			$(".button1 img").animate({
				top : "-10px"
			}, 200).animate({
				top : "-4px"
			}, 200) // first jump
			.animate({
				top : "-7px"
			}, 100).animate({
				top : "-4px"
			}, 100) // second jump
			.animate({
				top : "-6px"
			}, 100).animate({
				top : "-4px"
			}, 100); // the last jump
		});
	});
</script> -->
  
  
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

.SumClass
{
	background-color: #D3D3D3;
}

.colorheader
{
	background-color: #80CB9B;
}
 	
 </style>


<!-- ////////////////////////////// -->

<!-- <LINK rel="stylesheet" href="../css/main.css" type="text/css"> -->
<SCRIPT language="javascript" type="text/javascript">

	function seach() {
		document.forms['rpForm'].action.value = 'seach';
		document.forms["rpForm"].submit();
	}
	
	function setErrors(errorMsg) {
		var errors = document.getElementById("errors");
		errors.value = errorMsg;
	}

	function submitform()
	{
		if (document.forms["rpForm"]["tungay"].value.length == 0) {
			alert("Vui lòng chọn ngày bắt đầu");
			return;
		}
		if (document.forms["rpForm"]["denngay"].value.length == 0) {
			alert("Vui lòng chọn ngày kết thúc");
			return;
		}
		
		document.forms['rpForm'].action.value = 'excel';
		//document.forms['rpForm'].dataerror.value="";
		document.forms['rpForm'].submit();
		
	}
	function getdata(){
		if (document.forms["rpForm"]["tungay"].value.length == 0) {
			alert("Vui lòng chọn ngày bắt đầu");
			return;
		}
		if (document.forms["rpForm"]["denngay"].value.length == 0) {
			alert("Vui lòng chọn ngày kết thúc");
			return;
		}
		
		/* document.forms['rpForm'].action.value = 'getdata';
		document.forms['rpForm'].submit(); */
		readyFunc();
	}
	
	 function DinhDangTien(num) 
	 {
	    num = num.toString().replace(/\$|\,/g,'');
	    if(isNaN(num))
	    num = "0";
	    sign = (num == (num = Math.abs(num)));
	    num = Math.floor(num*100+0.50000000001);
	    num = Math.floor(num/100).toString();
	    for (var i = 0; i < Math.floor((num.length-(1+i))/3); i++)
	    num = num.substring(0,num.length-(4*i+3))+','+
	    num.substring(num.length-(4*i+3));
	    return (((sign)?'':'-') + num);
	}
	 function Dinhdang(element)
		{
			element.value=DinhDangTien(element.value);
			if(element.value== '' ||element.value=='0' )
			{
				element.value= '';
			}
		}
	
</SCRIPT>
<!-- <link href="../css/select2.css" rel="stylesheet" />
<script src="../scripts/select2.js"></script>
<script>
$(document).ready(function()
{
	$(document).ready(function()
	{
		$(".select2").select2();
	});
});
</script> -->
</HEAD>
<BODY leftmargin="0" bottommargin="0" topmargin="0" rightmargin="0">


<form name="rpForm" method="post" action="../../DistributionTT_Svl">
<% redis.clients.jedis.Jedis jedis =  geso.dms.center.util.Utility.getJedis(); %><% geso.dms.center.util.Csrf csdr=new geso.dms.center.util.Csrf(request,response,true,false,true);%>
 <input type="hidden" name="<%=csdr.get_tokenName() %>" value='<%=csdr.get_tokenValue() %>'>

<input type="hidden" name="userId" id="userId" value= <%= userId %> >
<input type="hidden" name="action" id="action" value='1'>

<!-- Navigator chao mung -->
<ul class="chaomung">
<li><%=url %></li>
<li style="float:right"><%=Utility.GLanguage("Chào mừng bạn",session,jedis) %>  <%=userTen %></li>
</ul>
<div class="box" style="border: 1px solid #dddbdb; background-color: #C5E8CD;">

<%if(obj.getMsg().length()>0){ %>
 <div class="alert alert-warning" id="errors" name="errors" role="alert"></div>
 	<%-- <textarea id="errors" name="errors" rows="1"  style="width: 100% ; color:#F00 ; font-weight:bold"><%=obj.getMsg()%></textarea> --%>
 <%} %>

	<div class="box-header">
		<div class="form-group col-md-6">
		  <h3 class="box-title"><%=Utility.GLanguage("Độ phủ sản phẩm",session,jedis) %></h3>
		</div> 
	</div>

<div class="box-body">
		<div class="col-md-6">
		<div class="form-group">
		  <div class="col-md-4"><label><%=Utility.GLanguage("Từ ngày",session,jedis) %></label></div> 
          <input type="text" autocomplete="off" class="days" name="tungay" id="tungay" size="20" value = "<%=obj.gettungay()%>" data-date-format="yyyy-mm-dd">
        </div>
        </div>
        
        <div class="col-md-6">
		<div class="form-group">
		   <div class="col-md-4"><label><%=Utility.GLanguage("Đến ngày",session,jedis) %></label></div> 
           <input type="text" autocomplete="off" class="days" name="denngay" id="denngay" size="20" value = "<%=obj.getdenngay()%>" data-date-format="yyyy-mm-dd">
        </div>
        </div>
 
		<div class="col-md-6">
		<div class="form-group">
            <div class="col-md-4"><label><%=Utility.GLanguage("Miền",session,jedis) %></label></div>
			<select class="select2" name="vungId" id="vungId" onchange="seach();" style="width:300px">
				<option value="" selected>All</option>
				<%if (vung != null)
						while (vung.next()) {
							if (vung.getString("pk_seq").equals(obj.getvungId())) {%>
						<option value="<%=vung.getString("pk_seq")%>" selected><%=vung.getString("ten")%></option>
					<%} else {%>
						<option value="<%=vung.getString("pk_seq")%>"><%=vung.getString("ten")%></option>
				<%}}%>
			</select>
        </div>
        </div>
        
        <div class="col-md-6">
		<div class="form-group">
            <div class="col-md-4"><label><%=Utility.GLanguage("Kênh bán hàng",session,jedis) %> </label></div>
			<select class="select2" name="kenhId" id="kenhId" onchange="seach();" style="width:300px">
				<option value="" selected>All</option>
				<%if (kenh != null)
						while (kenh.next()) {
							if (kenh.getString("pk_seq").equals(obj.getkenhId())) {%>
							<option value="<%=kenh.getString("pk_seq")%>" selected><%=kenh.getString("diengiai")%></option>
				<%} else { %>
					<option value="<%=kenh.getString("pk_seq")%>"><%=kenh.getString("diengiai")%></option>
				<%}}%>
			</select>
        </div>
        </div>
		
		
        
        <div class="col-md-6">
		<div class="form-group">
			<div class="col-md-4"><label><%=Utility.GLanguage("Nhà phân phối",session,jedis) %></label></div>
			<SELECT class="select2" name="nppId" id="nppId" style="width:300px;" onchange="seach();">	
			<option value="">All</option>							      
			<%if(npp != null) try{ while(npp.next()){ 
			if(npp.getString("pk_seq").trim().equals(obj.getnppId())){ %>
			<option value='<%=npp.getString("pk_seq") %>' selected><%=npp.getString("ten") %></option>
			<%}else{ %>
			<option value='<%=npp.getString("pk_seq") %>'><%=npp.getString("ten") %></option>
			<%}}}catch(java.sql.SQLException e){} %>	
			</select>
        </div>
        </div>
        
        <div class="col-md-6">
		<div class="form-group">
			<div class="col-md-4"><label><%=Utility.GLanguage("Sản phẩm",session,jedis) %></label></div>
			<select class="select2" name="spId" id="spId" style="width:300px"  multiple="multiple">
			<%if (spRs != null)
			while (spRs.next()) {
				if (obj.getsanphamId().indexOf(spRs.getString("pk_seq")) >=0){	%>
				<option value="<%=spRs.getString("pk_seq")%>" selected><%=spRs.getString("ma") + " - " +spRs.getString("ten")%></option>
			<%} else {%>
				<option value="<%=spRs.getString("pk_seq")%>"><%=spRs.getString("ma") + " - "+spRs.getString("ten")%></option>
			<%}}%>
			</select>
        </div>
        </div>

		<div class="col-md-6">
		<div class="form-group">
			<div class="col-md-4"><label><%=Utility.GLanguage("Doanh số tối thiểu KH",session,jedis) %></label></div>
			<input name="ds_toi_thieu_kh" id="ds_toi_thieu_kh" onkeyup="Dinhdang(this)" value="<%=obj.getMuc_KhachHang()== null  || obj.getMuc_KhachHang().trim().length() <=0  ? "0": formatter.format(Utility.parseDouble(obj.getMuc_KhachHang().replace(",","")))  %>" >  </input>
        </div>
        </div>
        
        <div class="col-md-6">
		<div class="form-group">
			<div class="col-md-4"><label><%=Utility.GLanguage("NHÂN VIÊN BÁN HÀNG",session,jedis) %></label></div>
			<select class="select2" name="ddkdId" id ="ddkdId" style="width:300px;">
			<option value="" selected>All</option>
			<%if (ddkds != null)
			while (ddkds.next()) {
				if (ddkds.getString("pk_seq").equals(obj.getDdkd())) {%>
			<option value="<%=ddkds.getString("pk_seq")%>" selected>
			<%=ddkds.getString("ten")%></option>
			<%} else {%>
			<option value="<%=ddkds.getString("pk_seq")%>"><%=ddkds.getString("ten")%></option>
			<%}}%>
			</select>
        </div>
        </div>
        
        
        
        <div  class="col-md-6">
		<div class="form-group">
			<div class="col-md-4"><label><%=Utility.GLanguage("Lấy theo",session,jedis) %></label></div>
			 <% if(obj.getLaytheo().equals("0")){ %>
				<div class="col-md-4"><label><input onchange="seach()" type="radio" name="laytheo" id="laytheo" value="0"  checked="checked" /><%=Utility.GLanguage("Tổng quát",session,jedis) %> </label></div> 
				<div class="col-md-4"><label><input onchange="seach()" type="radio" name="laytheo" id="laytheo" value="1"/><%=Utility.GLanguage("Chi tiết",session,jedis) %> </label></div>
			<%}else if(obj.getLaytheo().equals("1")){ %>
				<div class="col-md-4"><label><input  onchange="seach()" type="radio" name="laytheo" id="laytheo" value="0"/><%=Utility.GLanguage("Tổng quát",session,jedis) %> </label></div>
				<div class="col-md-4"><label><input  onchange="seach()" type="radio" name="laytheo" id="laytheo" value="1"  checked="checked" /><%=Utility.GLanguage("Chi tiết",session,jedis) %> </label></div>
			<%} %>
			
        </div>
        </div>
        
        <div class="col-md-12" style="margin-top: 10px;">
		<div class="form-group">
        <div class="col-md-4">
        <a class="button" id ="imgText1" style="border: 1px solid #80CB9B; padding: 6px; margin-right: 5px;  background: white;" href="javascript:submitform()"><%=Utility.GLanguage("Tạo báo cáo",session,jedis) %></a>
        <a class="button" id ="imgText2" style="border: 1px solid #80CB9B; padding: 6px; margin-right: 5px;  background: white;" href="javascript:getdata()"><%=Utility.GLanguage("Xem dữ liệu",session,jedis) %></a>
		</div></div>
		</div>
</div>
</div>

<div class="box-body">
<table id="example2" class="table table-bordered">
  <%-- <%=obj.getText_baocaoSR() %> --%>
  <thead class="colorheader">
     <tr>
     <% if(obj.getLaytheo().equals("0")){ %>
       <th><%=Utility.GLanguage("STT",session,jedis) %></th>
       <th><%=Utility.GLanguage("Miền",session,jedis) %></th>
       <th><%=Utility.GLanguage("NPP",session,jedis) %></th>
       <th><%=Utility.GLanguage("Sản phẩm",session,jedis) %></th>
       <th><%=Utility.GLanguage("Số khách hàng",session,jedis) %></th>
       <th><%=Utility.GLanguage("Tỷ lệ",session,jedis) %></th>
       <th><%=Utility.GLanguage("Số lượng",session,jedis) %></th>
       <th><%=Utility.GLanguage("Doanh số",session,jedis) %></th>
       <th><%=Utility.GLanguage("DS trừ trả lại",session,jedis) %></th>
       <%}else if(obj.getLaytheo().equals("1")){ %>
       	 <th><%=Utility.GLanguage("STT",session,jedis) %></th>
       <th><%=Utility.GLanguage("Miền",session,jedis) %></th>
       <th><%=Utility.GLanguage("NPP",session,jedis) %></th>
       <th><%=Utility.GLanguage("Sản phẩm",session,jedis) %></th>
       <th><%=Utility.GLanguage("Số khách hàng",session,jedis) %></th>
       <th><%=Utility.GLanguage("Số lượng",session,jedis) %></th>
        <th><%=Utility.GLanguage("Doanh số",session,jedis) %></th>
       <th><%=Utility.GLanguage("DS trừ trả lại",session,jedis) %></th>
       <%} %>
     </tr>
     </thead>
   <tfoot><tr></tr></tfoot>
</table>
</div>
 
<%-- <TABLE width="100%" border="0" cellspacing="0" cellpadding="0"
	height="100%">
	<TR>
		<TD colspan="4" align='left' valign='top' bgcolor="#FFFFFF">
			<TABLE width="100%" cellpadding="0" cellspacing="1">		
				 
				<tr>
					<TD align="left" colspan="4" class="legendtitle">
						<FIELDSET>
						<LEGEND class="legendtitle"><%=Utility.GLanguage("Báo lỗi nhập liệu",session,jedis) %></LEGEND>
	    				<textarea name="dataerror"  style="width: 100% ; color:#F00 ; font-weight:bold" id="errors" style="width: 100%" readonly="readonly" rows="1"><%= obj.getMsg() %></textarea>
						</FIELDSET>
				   </TD>
				</tr>	
				<TR>
					<TD>
						<TABLE border="0" width="100%" cellpadding="0" cellspacing="0">
							<TR>
								<TD width="100%" align="center" >
								<FIELDSET>
								  <LEGEND class="legendtitle">Thời gian xuất báo cáo<%=Utility.GLanguage("",session,jedis) %></LEGEND>
								<TABLE  width="100%" cellpadding="5"  cellspacing="0">
									<TR>
									  	<TD class="plainlabel"><%=Utility.GLanguage("Từ ngày",session,jedis) %></TD>
									  	<TD class="plainlabel" width="350px">
											<input type="text" autocomplete="off" class="days" name="tungay" id="tungay" size="20" value = "<%=obj.gettungay()%>" >																		
										</TD>																		
									
										<TD class="plainlabel" ><%=Utility.GLanguage("Đến ngày",session,jedis) %> </TD>
									  	<TD class="plainlabel" >
								  			<input type="text" autocomplete="off" name="denngay" id="denngay" class="days" size="20" value = "<%=obj.getdenngay()%>" >						
										</TD>
									</tr>
									<tr>
										<TD class="plainlabel">Miền</TD>
										<TD class="plainlabel" >
											<select name="vungId" id="vungId" onchange="seach();" style="width:300px">
												<option value="" selected>All</option>
												<%if (vung != null)
														while (vung.next()) {
															if (vung.getString("pk_seq").equals(obj.getvungId())) {%>
														<option value="<%=vung.getString("pk_seq")%>" selected><%=vung.getString("ten")%></option>
													<%} else {%>
														<option value="<%=vung.getString("pk_seq")%>"><%=vung.getString("ten")%></option>
												<%}}%>
											</select>
										</TD>
										<TD class="plainlabel"><%=Utility.GLanguage("Kênh bán hàng",session,jedis) %></TD>
										<TD class="plainlabel" >
											<select name="kenhId" id="kenhId" onchange="seach();" style="width:300px">
												<option value="" selected>All</option>
												<%if (kenh != null)
														while (kenh.next()) {
															if (kenh.getString("pk_seq").equals(obj.getkenhId())) {%>
															<option value="<%=kenh.getString("pk_seq")%>" selected><%=kenh.getString("diengiai")%></option>
												<%} else { %>
													<option value="<%=kenh.getString("pk_seq")%>"><%=kenh.getString("diengiai")%></option>
												<%}}%>
											</select>
										</TD>
										 
									</tr>
									 
									<tr>
										<TD class="plainlabel">Địa bàn  </TD>
										<TD class="plainlabel">
											<select name="ttId" id="ttId" onchange="seach();"  style="width:300px">
												<option value="" >All</option>
												<%if (ttRs != null)
														while (ttRs.next()) {
															if (ttRs.getString("pk_seq").equals(obj.getTtId()  )) {%>
												   <option value="<%=ttRs.getString("pk_seq")%>" selected><%=ttRs.getString("ten")%></option>
												   <%} else {%>
												   <option value="<%=ttRs.getString("pk_seq")%>"><%=ttRs.getString("ten")%></option>
												<%}}%>
											</select>
										</TD>
										<TD class="plainlabel"><%=Utility.GLanguage("Sản phẩm",session,jedis) %></TD>
										<TD class="plainlabel">
											<select name="spId" id="spId" style="width:300px"  multiple="multiple">
													<%if (spRs != null)
															while (spRs.next()) {
																if (obj.getsanphamId().indexOf(spRs.getString("pk_seq")) >=0){	%>
																<option value="<%=spRs.getString("pk_seq")%>" selected><%=spRs.getString("ma") + " - " +spRs.getString("ten")%></option>
															<%} else {%>
																<option value="<%=spRs.getString("pk_seq")%>"><%=spRs.getString("ma") + " - "+spRs.getString("ten")%></option>
													<%}}%>
											</select>
										</TD>
									</tr>			
													
									<TR>
									    <TD class="plainlabel"><%=Utility.GLanguage("Nhà phân phối",session,jedis) %> </TD>
									      <TD class="plainlabel">
									      	<SELECT name="nppId" id="nppId" style="width:300px;" onchange="seach();">	
									      	<option value="">All</option>							      
									  	 	<%if(npp != null) try{ while(npp.next()){ 
									    	if(npp.getString("pk_seq").trim().equals(obj.getnppId())){ %>
									      		<option value='<%=npp.getString("pk_seq") %>' selected><%=npp.getString("ten") %></option>
									      	<%}else{ %>
									     		<option value='<%=npp.getString("pk_seq") %>'><%=npp.getString("ten") %></option>
									     	<%}}}catch(java.sql.SQLException e){} %>	
	                                  </select>
								  		</TD>
								  		
										<TD class="plainlabel">Trình dược viên</TD>
										<TD class="plainlabel">
											<select name="ddkdId" id ="ddkdId" style="width:300px;">
													<option value="" selected>All</option>
													<%if (ddkds != null)
															while (ddkds.next()) {
																if (ddkds.getString("pk_seq").equals(obj.getDdkd())) {%>
															<option value="<%=ddkds.getString("pk_seq")%>" selected>
																<%=ddkds.getString("ten")%></option>
													<%} else {%>
															<option value="<%=ddkds.getString("pk_seq")%>"><%=ddkds.getString("ten")%></option>
													<%}}%>
											</select>
										</td>
									</tr>			
										
									<TR>
										<TD class="plainlabel"></TD>
										<TD class="plainlabel" colspan="3">
										<fieldset style="display:none;">
										<legend >Tiêu chí</legend> 
										 <% if(obj.gettype().equals("0")){ %>
											<input type="radio" name="typeid" onchange="Laytheokh();" value="0"  checked="checked"/>Lấy theo khách hàng &nbsp;&nbsp;&nbsp;
											<input type="radio" name="typeid" onchange="LayThethoigian();" value="1"/>Theo thời gian
											<input type="radio" name="typeid"  value="2" />Độ phủ theo nhiều sản phẩm
										<%}else if(obj.gettype().equals("1")){ %>
											<input type="radio" name="typeid" onchange="Laytheokh();" value="0"/>Lấy theo khách hàng &nbsp;&nbsp;&nbsp;
											<input type="radio" name="typeid" onchange="LayThethoigian();" value="1"  checked="checked" />Theo thời gian
											<input type="radio" name="typeid"  value="2" />Độ phủ theo nhiều sản phẩm
										<%}else{ %>
											<input type="radio" name="typeid" onchange="Laytheokh();" value="0"  />Lấy theo khách hàng &nbsp;&nbsp;&nbsp;
											<input type="radio" name="typeid" onchange="LayThethoigian();" value="1" />Theo thời gian
											<input type="radio" name="typeid"  value="2" checked="checked"  />Độ phủ theo nhiều sản phẩm
										<%} %>
										</fieldset>
										</TD>
									</TR>
									
									<TR>
										<TD class="plainlabel"></TD>
										<TD class="plainlabel" colspan="3">
										<fieldset>
										<legend>Lấy theo</legend> 
										 <% if(obj.getLaytheo().equals("0")){ %>
											<input type="radio" name="laytheo" value="0"  checked="checked"/>Tổng quát&nbsp;&nbsp;&nbsp;
											<input type="radio" name="laytheo" value="1"/>Chi tiết
										<%}else if(obj.getLaytheo().equals("1")){ %>
											<input type="radio" name="laytheo" value="0"/>Tổng quát&nbsp;&nbsp;&nbsp;
											<input type="radio" name="laytheo" value="1"  checked="checked" />Chi tiết
										<%} %>
										</fieldset>
										</TD>
									</TR>
									
									 <TR>
										<TD colspan="4" class="plainlabel">
										<a class="button2" href="javascript:submitform()" >
											<img style="top: -4px;" src="../images/button.png" alt=""><%=Utility.GLanguage("Tạo báo cáo",session,jedis) %></a>&nbsp;&nbsp;&nbsp;&nbsp;  
											
											<a class="button2" href="javascript:getdata()" >
											<img style="top: -4px;" src="../images/button.png" alt="">Xem dữ liệu</a>&nbsp;&nbsp;&nbsp;&nbsp;                                    </TD>
									</TR>
								</TABLE>
								</FIELDSET>						
								</TD>
							</TR>
						</TABLE>					
					</TD>
				</TR>
				
				<TR>
					<TD>
						<FIELDSET>
							<LEGEND class="legendtitle">Dữ liệu</LEGEND>
							
							<div class="box-body">
								<table id="example2" class="table table-bordered table-hover table-striped">
								  <thead>
					                <tr>
					                  <th>Số TT</th>
					                  <th>Miền</th>
					                  <th>NPP</th>
					                  <th><%=Utility.GLanguage("Sản phẩm",session,jedis) %></th>
					                  <th>Số khách hàng</th>
					                  <th>Số lượng</th>
					                  <th>Tổng tiền</th>
					                </tr>
					                </thead>
								  
								</table>
							</div>
						</FIELDSET>
					</TD>
				</TR>
				
			</TABLE>
		</TD>
	</TR>
</TABLE> --%>
</form>

	<!-- jQuery 3 -->
<script src="../css/bower_components/jquery/dist/jquery.min.js"></script>
<script src="../css/bower_components/datatables.net/js/jquery.dataTables.min.js"></script>
<script src="../css/bower_components/datatables.net-bs/js/dataTables.bootstrap.min.js"></script>
<script src="../css/bower_components/bootstrap-datepicker/dist/js/bootstrap-datepicker.js"></script>
<script src="../css/bower_components/select2/js/select2.min.js"></script>
<script type="text/javascript" src="../scripts/sum().js"></script>

<script>
$(document).ready(function() { 
	//Date picker
	$('#tungay').datepicker({
	  autoclose: true
	});

	//Date picker
	$('#denngay').datepicker({
	  autoclose: true
	});
	
	$('.select2').select2(); });
	
function formatMoney(num) 
{
   num = num.toString().replace(/\$|\,/g,'');
   if(isNaN(num))
   num = "0";
   sign = (num == (num = Math.abs(num)));
   num = Math.floor(num*100+0.50000000001);
   num = Math.floor(num/100).toString();
   for (var i = 0; i < Math.floor((num.length-(1+i))/3); i++)
   num = num.substring(0,num.length-(4*i+3))+','+
   num.substring(num.length-(4*i+3));
   return (((sign)?'':'-') + num);
}	

function readyFunc()
{
	if ( $.fn.DataTable.isDataTable('#example2') ) 
	{
		$('#example2').DataTable().destroy();
	}
	 ds_toi_thieu_kh = $("#ds_toi_thieu_kh").val();
	 nhanvien = $("#userId").val();
	 tungay = $("#tungay").val();
	 denngay = $("#denngay").val();
	 vungId = $("#vungId").val();
	 kenhId = $("#kenhId").val();
	 ttId = $("#ttId").val();
	 spId = $("#spId").val(); 
	 nppId = $("#nppId").val(); 
	 ddkdId = $("#ddkdId").val(); 
	 laytheo = $('input[name=laytheo]:checked').val();
	 
     $('#example2').DataTable({
    	"sAjaxSource": "../../AjaxDistributionTT?userId="+nhanvien+"&ds_toi_thieu_kh="+ ds_toi_thieu_kh +"&tungay="+ tungay +"&denngay="+ denngay +"&vungId="+ vungId +"&kenhId="+ kenhId +"&ttId="+ ttId +"&spId="+ spId +"&nppId="+ nppId +"&ddkdId="+ ddkdId +"&laytheo="+ laytheo +"&action=DoPhuSP",
    	"dom": 'C<"clear">lfrtip',
    	"serverSide": true,
	    "responsive": true,
	    "searching": false,
	    "lengthChange": false,
	    "iDisplayLength": 50,
	    "ordering": false,
	    "iDisplayStart":0,
	    "createdRow": function( row, data, dataIndex){
            if( data[0] ==  ''){
                $(row).addClass('SumClass');
            }
        },
        "columnDefs": [ {
            "targets": [ 5 ],
            "render": function ( data, type, full, meta ) 
            { 
            	 if(laytheo == '0')
            	 { return +data+' %'; }
            	 else { return data; }
            }
        } ]
        
	    /* ,drawCallback: function () {
	         var api = this.api();
	        $( api.table().footer() ).html(
	       	'<tr><th colspan=5>Tổng</th><th> '+ formatMoney(api.column( 5, {page:'current'} ).data().sum()) +'</th><th> '+ formatMoney(api.column( 6, {page:'current'} ).data().sum()) +'</th></tr>'
	        ); 
	      } */ 
  });  
} 
 
// UMD
(function( factory ) {
    "use strict";
 
    if ( typeof define === 'function' && define.amd ) {
        // AMD
        define( ['jquery'], function ( $ ) {
            return factory( $, window, document );
        } );
    }
    else if ( typeof exports === 'object' ) {
        // CommonJS
        module.exports = function (root, $) {
            if ( ! root ) {
                root = window;
            }
 
            if ( ! $ ) {
                $ = typeof window !== 'undefined' ?
                    require('jquery') :
                    require('jquery')( root );
            }
 
            return factory( $, root, root.document );
        };
    }
    else {
        // Browser
        factory( jQuery, window, document );
    }
}
(function( $, window, document ) 
{
$.fn.dataTable.ext.order.intl = function ( locales, options ) {
    if ( window.Intl ) {
        var collator = new Intl.Collator( locales, options );
        var types = $.fn.dataTable.ext.type;
 
        delete types.order['string-pre'];
        types.order['string-asc'] = collator.compare;
        types.order['string-desc'] = function ( a, b ) {
            return collator.compare( a, b ) * -1;
        };
    }
};
}));
</script>	
</BODY>
</HTML>

<%
	if(vung!=null) vung.close();		
	if(khuvuc!=null) khuvuc.close();	
	if(ttRs!=null) ttRs.close();
	if(kenh!=null) kenh.close();		
	if(npp!=null) npp.close();		
	if(sku!=null) sku.close();		 
	if(spRs!=null) spRs.close();		
	if(chungloai!=null) chungloai.close();	
	if(dvkd!=null) dvkd.close();	 
	if(nhomsp!=null) nhomsp.close();
	if(ddkds!=null) ddkds.close();
	
	if(obj!=null){ 
		obj.DBclose();
		obj = null;
	}
	session.setAttribute("obj", null);
	session.setAttribute("checkedSKU", null);
	session.setAttribute("loi", null);
		               	  			


	}%>