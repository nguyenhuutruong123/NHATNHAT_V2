	<%@page import="java.sql.ResultSet"%>
<%@page import="geso.dms.center.beans.stockintransit.IStockintransit"%>
<%@page import="java.util.Calendar"%>
<%@page import="java.sql.Date"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@page import="java.text.DecimalFormat"%>
<%@page import="java.text.NumberFormat"%>
<%@ page  import = "geso.dms.center.util.*" %>

<% NumberFormat formatter = new DecimalFormat("#,###,###"); %>
<% NumberFormat formatter_Gia = new DecimalFormat("#,###,###.####"); %>

<%
	String userId = (String) session.getAttribute("userId");
	String userTen = (String) session.getAttribute("userTen");
	IStockintransit obj = (IStockintransit) session.getAttribute("obj");
	
	ResultSet ddkd = obj.getRsddkd();
	ResultSet khRs = obj.getKhachHangRs();
	ResultSet spRs = obj.getSpRs();
	ResultSet kbhRs = obj.getkenh();

	


	ResultSet vungRs = obj.getvung();
	ResultSet ttRs = obj.getTtRs();
	ResultSet nppRs = obj.getNhaphanphoiRs();
	ResultSet nhomRs = obj.GetRsNhomSP();
	Utility util = (Utility) session.getAttribute("util");

	String url = util.getChuyenNguUrl("BangKeHoaDonSpTTSvl", "",session);
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd"> <% Utility Utilback = new Utility(); %>
<html>
<head>
<TITLE><%= getServletContext().getInitParameter("TITLENAME") %></TITLE>
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
  <link rel="stylesheet" href="https://fonts.googleapis.com/css?family=Source+Sans+Pro:300,400,600,700,300italic,400italic,600italic">
        
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

<script type="text/javascript" src="../scripts/report-js/action-report.js"></script>
<script language="javascript" type="text/javascript">

function clearform()
{   
	document.forms["ckParkForm"].tungay.value="";
	document.forms["ckParkForm"].denngay.value="";
	document.forms["ckParkForm"].khId.value="";
	document.forms["ckParkForm"].spId.value="";
	document.forms["ckParkForm"].ddkdId.value="";
	document.forms["ckParkForm"].kenhId.value="";
   document.forms["ckParkForm"].submit();
}

function submitform()
{   
	document.forms['ckParkForm'].action.value='';
   document.forms["ckParkForm"].submit();
}

function xuatExcel()
{
	document.forms['ckParkForm'].action.value= 'excel';
	document.forms['ckParkForm'].submit();
	
}

function getdata(){
	if (document.forms["ckParkForm"]["denngay"].value.length == 0) {
		alert("Vui lòng chọn ngày bắt đầu");
		return;
	}
	if (document.forms["ckParkForm"]["tungay"].value.length == 0) {
		alert("Vui lòng chọn ngày kết thúc");
		return;
	}
	getSumData();
	readyFunc();
}

function search()
{
	
	document.forms['ckParkForm'].action.value= 'search';
	document.forms['ckParkForm'].submit();
	
}
</script>
</head>
<BODY leftmargin="0" bottommargin="0" topmargin="0" rightmargin="0">

<!-- Navigator chao mung -->

<form name="ckParkForm" method="post" action="../../BangKeHoaDonSpTTSvl">
<% redis.clients.jedis.Jedis jedis =  geso.dms.center.util.Utility.getJedis(); %><% geso.dms.center.util.Csrf csdr=new geso.dms.center.util.Csrf(request,response,true,false,true);%>
 <input type="hidden" name="<%=csdr.get_tokenName() %>" value='<%=csdr.get_tokenValue() %>'>

<input type="hidden" name="action" value=''>
<input type="hidden" name="userId" id="userId" value='<%=userId%>'>
<input type="hidden" name="msg" id="msg" value='<%=Utility.GLanguage(obj.getMsg(),session,jedis) %>'>
<ul class="chaomung">
<li><%=url %></li>
<li style="float:right"><%=Utility.GLanguage("Chào mừng bạn",session,jedis) %>  <%=userTen %></li>
</ul>

<div class="box" style="border: 1px solid #dddbdb; background-color: #C5E8CD;">
	<%if(obj.getMsg().length()>0){ %> <div class="alert alert-warning" id="errors" name="errors" role="alert"><%=obj.getMsg()%></div> <%} %>
	<div class="box-header">
		<div class="form-group col-md-6">
		  <h3 class="box-title"><%=Utility.GLanguage("Doanh thu/Doanh số theo sản phẩm",session,jedis) %></h3>
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
           <select class="select2" name="vungId" id="vungId" style="width:250px" onchange="submitform();" >
				<option value="" ><%=Utility.GLanguage("All",session,jedis) %></option>
				<%if (vungRs != null)
						while (vungRs.next()) {
							if (vungRs.getString("pk_seq").equals(obj.getvungId()  )) {%>
				   <option value="<%=vungRs.getString("pk_seq")%>" selected><%=vungRs.getString("ten")%></option>
				   <%} else {%>
				   <option value="<%=vungRs.getString("pk_seq")%>"><%=vungRs.getString("ten")%></option>
				<%}}%>
			</select>
        </div>
        </div>
        
        <div class="col-md-6">
		<div class="form-group">
		   <div class="col-md-4"><label><%=Utility.GLanguage("Kênh bán hàng",session,jedis) %></label></div> 
           <select class="select2" name="kenhId" id="kenhId" style="width:250px"  onchange="submitform();"  >
				<option value="" ><%=Utility.GLanguage("All",session,jedis) %></option>
				<%if (kbhRs != null)
						while (kbhRs.next()) {
							if (kbhRs.getString("pk_Seq").equals(obj.getkenhId())) {%>
				   <option value="<%=kbhRs.getString("pk_Seq")%>" selected><%=kbhRs.getString("ten")%></option>
				   <%} else {%>
				   <option value="<%=kbhRs.getString("pk_Seq")%>"><%=kbhRs.getString("ten")%></option>
				<%}}%>
			</select>
        </div>
        </div>
         
        <div class="col-md-6">
		<div class="form-group">
		   <div class="col-md-4"><label><%=Utility.GLanguage("Chi nhánh/ĐT",session,jedis) %></label></div> 
           <select class="select2" name="nppId" id="nppId" style="width:250px">
				<option value="" ><%=Utility.GLanguage("All",session,jedis) %></option>
				<%if (nppRs != null)
						while (nppRs.next()) {
							if (nppRs.getString("pk_seq").equals(obj.getNppId()   )) {%>
				   <option value="<%=nppRs.getString("pk_seq")%>" selected><%=nppRs.getString("ten")%></option>
				   <%} else {%>
				   <option value="<%=nppRs.getString("pk_seq")%>"><%=nppRs.getString("ten")%></option>
				<%}}%>
			</select>
        </div>
        </div> 
        
        <div class="col-md-6">
		<div class="form-group">
		   <div class="col-md-4"><label><%=Utility.GLanguage("Sản phẩm",session,jedis) %></label></div> 
           <select class="select2" name="spId" id="spId" style="width:250px">
				<option value="" ><%=Utility.GLanguage("All",session,jedis) %></option>
				<%if (spRs != null)
						while (spRs.next()) {
							if (spRs.getString("pk_seq").equals(obj.getSpId() )) {%>
				   <option value="<%=spRs.getString("pk_seq")%>" selected><%=spRs.getString("ten")%></option>
				   <%} else {%>
				   <option value="<%=spRs.getString("pk_seq")%>"><%=spRs.getString("ten")%></option>
				<%}}%>
			</select>
        </div>
        </div> 
        
        <div class="col-md-6">
		<div class="form-group">
		   <div class="col-md-4"><label><%=Utility.GLanguage("Nhóm hàng",session,jedis) %></label></div> 
           <select class="select2" name="nhomId" id="nhomId" style="width:250px">
				<option value="" ><%=Utility.GLanguage("All",session,jedis) %></option>
				<%if (nhomRs != null)
						while (nhomRs.next()) {
							if (nhomRs.getString("pk_seq").equals(obj.getNhomhangid()   )) {%>
				   <option value="<%=nhomRs.getString("pk_seq")%>" selected><%=nhomRs.getString("ten")%></option>
				   <%} else {%>
				   <option value="<%=nhomRs.getString("pk_seq")%>"><%=nhomRs.getString("ten")%></option>
				<%}}%>
			</select>
        </div>
        </div>
        
         <DIV id = "totalhaha" style="display: none" >	       
        </DIV>
        
        <div class="col-md-12" style="margin-top: 10px;">
		<div class="form-group">
        <div class="col-md-4">
        <a class="button" id ="imgText1" style="border: 1px solid #80CB9B; padding: 6px; margin-right: 5px;  background: white;" href="javascript:getdata()"><%=Utility.GLanguage("Tìm kiếm",session,jedis) %></a>
        <a class="button" id ="imgText2" style="border: 1px solid #80CB9B; padding: 6px; margin-right: 5px;  background: white;" href="javascript:xuatExcel()"><%=Utility.GLanguage("Xuất dữ liệu",session,jedis) %></a>
		</div></div>
		</div> 
        
	</div>
</div>

<div class="box-body">
<table id="example2" class="table table-bordered table-hover table-striped">
  <thead class="colorheader">
     <tr>
       <th><%=Utility.GLanguage("STT",session,jedis) %></th>
       <th><%=Utility.GLanguage("MÃ VẬT TƯ",session,jedis) %></th>
       <th><%=Utility.GLanguage("TÊN VẬT TƯ",session,jedis) %></th>
       <th><%=Utility.GLanguage("ĐVT",session,jedis) %></th>
       <th><%=Utility.GLanguage("SỐ LƯỢNG",session,jedis) %></th>
       <th><%=Utility.GLanguage("DOANH SỐ",session,jedis) %></th>
       <th><%=Utility.GLanguage("DS TRỪ TRẢ LẠI",session,jedis) %></th>
     </tr>
     </thead>
   <tfoot><tr></tr></tfoot>
</table>
</div><%geso.dms.center.util.Utility.JedisClose(jedis); %>
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

function getSumData()
{	
	
	var nhanvien = $("#userId").val();
	var tungay = $("#tungay").val();
	var denngay = $("#denngay").val();
	var vungId = $("#vungId").val();
	var kenhId = $("#kenhId").val();
	var spId = $("#spId").val(); 
	var nppId = $("#nppId").val(); 
	var nhomId = $("#nhomId").val(); 
	
	var xmlhttp;
	   
	   if (window.XMLHttpRequest)
	   {// code for IE7+, Firefox, Chrome, Opera, Safari
	      xmlhttp = new XMLHttpRequest();
	   }
	   else
	   {// code for IE6, IE5
	      xmlhttp = new ActiveXObject("Microsoft.XMLHTTP");
	   }
	   xmlhttp.onreadystatechange=function()
	   {
		  
	       if (xmlhttp.readyState == 4 && xmlhttp.status == 200)
	       {
	         
	         
	         if(xmlhttp.responseText !='')
	         {
	        	 document.getElementById("totalhaha").innerHTML =xmlhttp.responseText;
	        	 document.getElementById("totalhaha").style.display = "";
			 }
	         else
	         { 
	        	 document.getElementById("totalhaha").style.display = "none";
	     		
	         }
	      } 
	   }
	   xmlhttp.open("POST","../../AjaxDistributionTT?userId="+nhanvien+"&tungay="+ tungay +"&denngay="+ denngay +"&vungId="+ vungId +"&kenhId="+ kenhId +"&spId="+ spId +"&nppId="+ nppId +"&nhomId="+ nhomId +"&action=DoanhThuTheoSP",true);
	   xmlhttp.send();


}

function readyFunc()
{
	if ( $.fn.DataTable.isDataTable('#example2') ) 
	{
		$('#example2').DataTable().destroy();
	}
	
	 nhanvien = $("#userId").val();
	 tungay = $("#tungay").val();
	 denngay = $("#denngay").val();
	 vungId = $("#vungId").val();
	 kenhId = $("#kenhId").val();
	 spId = $("#spId").val(); 
	 nppId = $("#nppId").val(); 
	 nhomId = $("#nhomId").val(); 
	 //laytheo = $('input[name=laytheo]:checked').val();
	 
     $('#example2').DataTable({
    	"sAjaxSource": "../../AjaxDistributionTT?userId="+nhanvien+"&tungay="+ tungay +"&denngay="+ denngay +"&vungId="+ vungId +"&kenhId="+ kenhId +"&spId="+ spId +"&nppId="+ nppId +"&nhomId="+ nhomId +"&action=DoanhThuTheoSP",
    	"dom": 'C<"clear">lfrtip',
    	"serverSide": true,
	    "responsive": true,
	    "searching": false,
	    "lengthChange": false,
	    "iDisplayLength": 50,
	    "ordering": false,
	    "iDisplayStart":0,
	    drawCallback: function () {
	         var api = this.api();
	        $( api.table().footer() ).html(
	       	'<tr><th colspan=6>Tổng</th><th> '+ formatMoney(api.column( 6, {page:'current'} ).data().sum()) +'</th><th> '+ formatMoney(api.column( 7, {page:'current'} ).data().sum()) +'</th><th> '+ formatMoney(api.column( 8, {page:'current'} ).data().sum()) +'</th></tr>'
	        ); 
	      } 
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
</body>
</HTML>

<%
	if(obj!=null){obj.DBclose();
	obj = null;}
	session.setAttribute("obj", null);
%>