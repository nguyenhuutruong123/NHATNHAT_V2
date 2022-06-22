<%@page import="geso.dms.center.beans.quanhuyen.IQuanhuyenList"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page  import = "java.sql.ResultSet" %>
<%@ page  import = "geso.dms.center.util.*" %>

<% 
	String userId = (String) session.getAttribute("userId");
	String userTen = (String) session.getAttribute("userTen");
	IQuanhuyenList quanList = (IQuanhuyenList)session.getAttribute("quanList");
	ResultSet quanRs = quanList.getQuanhuyenRs();
	ResultSet tinhRs = quanList.getTinhthanhRs();
	session.setAttribute("db", null);
	
	String sum = (String) session.getAttribute("sum");
	Utility util = (Utility) session.getAttribute("util");
	String url="";
	url = util.getChuyenNguUrl("QuanhuyenSvl","",session);
	if(!util.check(userId, userTen, sum)){
		response.sendRedirect(request.getContextPath() + "/redirect.jsp");
	}else{
		int[] quyen = new  int[6];
		quyen = util.Getquyen("QuanhuyenSvl","",userId);
%>
<% Utility Util = new Utility(); %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd"> <% Utility Utilback = new Utility(); %>
<html>
<head>
	<META http-equiv="Content-Type" content="text/html; charset=utf-8">
	<META http-equiv="Content-Style-Type" content="text/css">
	<title><%= getServletContext().getInitParameter("TITLENAME") %></title>
	
	<LINK rel="stylesheet" href="../css/main.css" type="text/css">
	<!-- Bootstrap 3.3.7 -->
  <link rel="stylesheet" href="../css/bower_components/bootstrap/dist/css/bootstrap.min.css">
  <!-- Font Awesome -->
  <!-- <link rel="stylesheet" href="../css/bower_components/font-awesome/css/font-awesome.min.css"> -->
  <!-- Ionicons -->
  <link rel="stylesheet" href="../css/bower_components/Ionicons/css/ionicons.min.css">
  <!-- jvectormap -->
  <link rel="stylesheet" href="../css/bower_components/jvectormap/jquery-jvectormap.css">
  <!-- datepicker -->
  <link rel="stylesheet" href="../css/bower_components/bootstrap-datepicker/dist/css/bootstrap-datepicker.css">
  <!-- DataTables -->
  <link rel="stylesheet" href="../css/bower_components/datatables.net-bs/css/dataTables.bootstrap.min.css">
  <!-- Theme style -->
  <!-- <link rel="stylesheet" href="../css/dist/css/AdminLTE.min.css">
  <link rel="stylesheet" href="../css/dist/css/skins/_all-skins.min.css"> -->
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
 
input[type="text"]{
		width:200px;
		background-color:white;
		border:1px;
		border-style: solid;
		border-color: #808080;
			
	}	
input[type="file"]{
	background-color:white;
	border:1px;
	border-style:solid;
    border-color: #808080;
	}

.button {
	padding: 4px 10px 3px 25px;
	border: solid 1px #8AB134;
	position: relative;
	cursor: pointer;
	display: inline-block;
	background-image: url( 'bkg.png' );
	background-repeat: repeat-x;
	font-size: 11px;
	text-decoration: none;
	color: #40740D;
	-moz-border-radius-bottomleft: 5px;
	-moz-border-radius-bottomright: 5px;
	-moz-border-radius-topleft: 5px;
	-moz-border-radius-topright: 5px;
}

.button img {
	position: absolute;
	top: -4px;
	left: -12px;
	border: none;
}
.button:hover {
	color: #8AB134;
}
 
fieldset.scheduler-border {
    border: 1px groove #ddd !important;
    padding: 0 1.4em 1.4em 1.4em !important;
    margin: 0 0 1.5em 0 !important;
    -webkit-box-shadow:  0px 0px 0px 0px #000;
            box-shadow:  0px 0px 0px 0px #000;
}

    legend.scheduler-border {
        font-size: 1.2em !important;
        font-weight: bold !important;
        text-align: left !important;
        width:auto;
        padding:0 10px;
        border-bottom:none;
    }
 </style>
 
    <SCRIPT language="javascript" type="text/javascript">
	 function submitform() {
		 document.forms["ckParkForm"].action.value= 'submit';
		 document.forms["ckParkForm"].submit();
	 }
	 function excel() {
		 document.forms["ckParkForm"].action.value= 'excel';
		 document.forms["ckParkForm"].submit();
	 }
	 function clearform() { 
		 
		   /*  document.forms["ckParkForm"].ten.value = "";
		    document.forms["ckParkForm"].tinhId.selectedIndex = 0;
		    submitform(); */
		    
			 window.location.href ='../../RouterSvl?task=<%= Util.dongMa(getServletContext().getInitParameter("RedirectNoScript") +"QuanhuyenSvl?userId="+userId) %>';
		 }
	 function newform() {
		 document.forms["ckParkForm"].action.value= 'new';
		 document.forms["ckParkForm"].submit();
	 }
	 
	</SCRIPT>
</head>
<body>
<form name="ckParkForm" method="post" action="../../QuanhuyenSvl">
<% redis.clients.jedis.Jedis jedis =  geso.dms.center.util.Utility.getJedis(); %><% geso.dms.center.util.Csrf csdr=new geso.dms.center.util.Csrf(request,response,true,false,true);%>
 <input type="hidden" name="<%=csdr.get_tokenName() %>" value='<%=csdr.get_tokenValue() %>'>

<input type="hidden" name="userId" id="userId" value="<%= userId %>" >
<input type="hidden" name="action" value="1" >
<input type="hidden" name="quyensua" id="quyensua" value="<%=quyen[Utility.SUA]%>">
<input type="hidden" name="quyenxoa" id="quyenxoa" value="<%=quyen[Utility.XOA]%>">
<input type="hidden" name="quyenxem" id="quyenxem" value="<%=quyen[Utility.XEM]%>">

<!-- Navigator chao mung -->
<ul class="chaomung">
<li><%=url %></li>
<li style="float:right"><%=Utility.GLanguage("Chào mừng bạn",session,jedis) %>  <%=userTen %></li>
</ul> 

<!-- <div class="" style="border: 1px solid #dddbdb; background-color: #C5E8CD; margin-left: 2pt;"> -->
<div align="left" style="width:100%; float:none; clear:left; font-size:0.7em">
<!-- <fieldset class="scheduler-border" style="margin-top:5px">      -->    
  <!-- <legend class="legendtitle"> <%=Utility.GLanguage("Tiêu chí tìm kiếm",session,jedis) %></legend> -->
  <fieldset class="scheduler-border">     
  <legend class="scheduler-border" style="margin-bottom: 5px"><%=Utility.GLanguage("Tiêu chí tìm kiếm",session,jedis) %></legend>
    
     <!-- <TABLE class="table" width="100%" cellpadding="6px" cellspacing="0px" style="margin-top: 5px; font-size: 10pt; " > -->
     <TABLE class="table" style="margin-bottom: 0px;">
	     <TR>
	       <TD class="plainlabel" style="padding-left: 10px; width : 50px;"><%=Utility.GLanguage("Tên",session,jedis) %></TD>
		   <TD class="plainlabel" style="width : 250px;">
		       <input type="text" autocomplete="off" name="ten" id="ten" value="<%=quanList.getTen()%>">
		   </TD>
	     </TR>
	     
	     <TR>
	       <TD class="plainlabel" style="padding-left: 10px; width:50px;"><%=Utility.GLanguage("Tỉnh",session,jedis) %></TD>
	       <TD class="plainlabel" width="250px" >
	           <select class="select2" name="tinhId" id="tinhId" onchange="submitform()" style="width:300px">
	           	<option value=""></option>
	           <% if (tinhRs != null) {
		           		while (tinhRs.next()) {
		           			if (tinhRs.getString("PK_SEQ").equals(quanList.getTinhId())) {
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
	       </TD>
	   </TR>
	   
	     <tr>
            <td colspan="4" style="padding-left: 10px;" class="plainlabel">
                <a class="button" href="javascript:readyFunc();">
                    <img style="top: -4px;" src="../images/button.png" alt=""><%=Utility.GLanguage("Tìm kiếm",session,jedis) %> </a>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                <a class="button" href="javascript:clearform()">
                    <img style="top: -4px;" src="../images/button.png" alt=""><%=Utility.GLanguage("Nhập lại",session,jedis) %></a>&nbsp;&nbsp;&nbsp;&nbsp;
                <a class="button" href="javascript:excel()">
                    <img style="top: -4px;" src="../images/button.png" alt=""><%=Utility.GLanguage("Xuất dữ liệu",session,jedis) %></a>&nbsp;&nbsp;&nbsp;&nbsp;    
            </td>
        </tr> 
     </TABLE>
	</fieldset>
</div>

<div align="left" style="width:100%; float:none; clear:left; font-size:0.7em">

<fieldset class="scheduler-border">     
  <legend class="scheduler-border" style="margin-bottom: 5px"> <%=Utility.GLanguage("Quận huyện",session,jedis) %>
  <% if (quyen[Utility.THEM] != 0) { %>
	     <a style="margin-left : 10px" class="button" href="javascript:newform()"><img style="top: -4px;" src="../images/New.png" alt=""><%=Utility.GLanguage("Tạo mới",session,jedis) %></a>
	  <%}%>
	</legend>
 
  <table id="example2" class="table table-bordered table-hover table-striped" style="font-size: 10pt; width: 100%; ">
  <thead class="colorheader">
     <tr>
       <th><%=Utility.GLanguage("Mã hệ thống",session,jedis) %></th>
       <th><%=Utility.GLanguage("Tên quận huyện",session,jedis) %></th>
       <th><%=Utility.GLanguage("Tỉnh",session,jedis) %></th>
       <th><%=Utility.GLanguage("Ngày tạo",session,jedis) %></th>
       <th><%=Utility.GLanguage("Người tạo",session,jedis) %></th>
       <th><%=Utility.GLanguage("Ngày sửa",session,jedis) %></th>
       <th><%=Utility.GLanguage("Người sửa",session,jedis) %></th>
       <th><%=Utility.GLanguage("Tác vụ",session,jedis) %></th>
     </tr>
     </thead>
   <tfoot><tr></tr></tfoot>
</table>
  </fieldset>
  </div><%geso.dms.center.util.Utility.JedisClose(jedis); %>
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
 	readyFunc();	
});
	
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
	
	userId = $("#userId").val();
	ten = $("#ten").val(); 
	tinhId = $("#tinhId").val(); 
	quyensua = $("#quyensua").val();
	quyenxoa = $("#quyenxoa").val();
	quyenxem = $("#quyenxem").val();
     $('#example2').DataTable({
    	"sAjaxSource": "../../AjaxDistributionTT?userId="+userId+"&ten="+ xoa_dau(ten) +"&tinhId="+ tinhId +"&action=Quanhuyen",
    	"dom": 'C<"clear">lfrtip',
    	"serverSide": true,
	    "responsive": true,
	    "searching": false,
	    "lengthChange": false,
	    "iDisplayLength": 50,
	    "ordering": false,
	    "iDisplayStart":0,
	    
	    "columnDefs": [ {
	    	  "targets": 7,
		        "data": function(data, type, row){
		        
		        	var giatri="";
		        	if(quyensua != 0)
		        		{
		        		giatri+='<a href = "../../QuanhuyenUpdateSvl?userId='+ userId +'&update='+ data[0] +'&type=update "><img src="../images/Edit20.png" alt="Cap nhat" width="20" height="20" longdesc="Cap nhat" border = 0></a>';
		        		}
		        	
		        	if(quyenxoa != 0)
	        		{
	        		giatri+=' <a href = "../../QuanhuyenUpdateSvl?userId='+ userId +'&delete='+ data[0] +'&type=delete "><img src="../images/Delete20.png" alt="xoa" width="20" height="20" longdesc="xoa" border = 0></a>';
	        		}
		        	
		        	if(quyenxem != 0)
	        		{
	        		giatri+=' <a href = "../../QuanhuyenUpdateSvl?userId='+ userId +'&display='+ data[0] +'&type=display "><img src="../images/Display20.png" alt="xem" width="20" height="20" longdesc="xem" border = 0></a>';
	        		}
		        		return giatri;
		        	
		        	
		        	
		        	
		        	
		        }
	      } ]
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

function xoa_dau(str) {
    str = str.replace(/à|á|ạ|ả|ã|â|ầ|ấ|ậ|ẩ|ẫ|ă|ằ|ắ|ặ|ẳ|ẵ/g, "a");
    str = str.replace(/è|é|ẹ|ẻ|ẽ|ê|ề|ế|ệ|ể|ễ/g, "e");
    str = str.replace(/ì|í|ị|ỉ|ĩ/g, "i");
    str = str.replace(/ò|ó|ọ|ỏ|õ|ô|ồ|ố|ộ|ổ|ỗ|ơ|ờ|ớ|ợ|ở|ỡ/g, "o");
    str = str.replace(/ù|ú|ụ|ủ|ũ|ư|ừ|ứ|ự|ử|ữ/g, "u");
    str = str.replace(/ỳ|ý|ỵ|ỷ|ỹ/g, "y");
    str = str.replace(/đ/g, "d");
    str = str.replace(/À|Á|Ạ|Ả|Ã|Â|Ầ|Ấ|Ậ|Ẩ|Ẫ|Ă|Ằ|Ắ|Ặ|Ẳ|Ẵ/g, "A");
    str = str.replace(/È|É|Ẹ|Ẻ|Ẽ|Ê|Ề|Ế|Ệ|Ể|Ễ/g, "E");
    str = str.replace(/Ì|Í|Ị|Ỉ|Ĩ/g, "I");
    str = str.replace(/Ò|Ó|Ọ|Ỏ|Õ|Ô|Ồ|Ố|Ộ|Ổ|Ỗ|Ơ|Ờ|Ớ|Ợ|Ở|Ỡ/g, "O");
    str = str.replace(/Ù|Ú|Ụ|Ủ|Ũ|Ư|Ừ|Ứ|Ự|Ử|Ữ/g, "U");
    str = str.replace(/Ỳ|Ý|Ỵ|Ỷ|Ỹ/g, "Y");
    str = str.replace(/Đ/g, "D");
    return str;
}
</script>	
</BODY>
</HTML>
<%
quanList.getDb().shutDown(); }
%>
