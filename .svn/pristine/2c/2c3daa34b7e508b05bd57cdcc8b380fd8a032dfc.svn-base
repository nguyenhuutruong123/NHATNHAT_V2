<%@page import="geso.dms.center.util.Utility"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@page import="java.util.ArrayList"%>
<%@page import="java.sql.SQLException"%>
<%@ page  import = "geso.dms.distributor.beans.dondathang.*" %>
<%@ page  import = "geso.dms.distributor.beans.dondathang.imp.*" %>
<%@ page  import = "java.sql.ResultSet" %>
<%@ page  import = "java.util.Hashtable" %>
<%@ page  import = "java.util.List" %>
<%@page import="java.text.NumberFormat"%>
<%@page import="java.text.DecimalFormat"%>

<%@ page import = "com.google.gson.JsonArray" %>
<%@ page import = "com.google.gson.JsonObject" %>
<%@ page import = "com.google.gson.JsonParser" %>

<% IErpDondathangNpp lsxBean = (IErpDondathangNpp)session.getAttribute("lsxBean"); %>
<% ResultSet dvkdRs = lsxBean.getDvkdRs(); %>
<% ResultSet kbhRs = lsxBean.getKbhRs(); %>
<% ResultSet nppRs = lsxBean.getNppRs(); %>
<% ResultSet khonhapRs = lsxBean.getKhoNhapRs(); %>
<% ResultSet dvtRs = lsxBean.getDvtRs(); %>
<% ResultSet congnoRs = lsxBean.getCongnoRs(); %>

<% 
	String[] spMa = lsxBean.getSpMa(); 
	String[] spTen = lsxBean.getSpTen();
	String[] spDonvi = lsxBean.getSpDonvi();
	String[] spSoluong = lsxBean.getSpSoluong();
	String[] spGianhap = lsxBean.getSpGianhap();
	String[] spVat = lsxBean.getSpVat();
	String[] spSCheme = lsxBean.getSpScheme();
	String dieuchinh = lsxBean.getDieuchinh();
	NumberFormat formater = new DecimalFormat("##,###,###");
%>

<% String userId = (String) session.getAttribute("userId");  %>
<% String userTen = (String) session.getAttribute("userTen");  
	String sum = (String) session.getAttribute("sum");
	Utility util = (Utility) session.getAttribute("util");
	
	if(!util.check(userId, userTen, sum)){
		response.sendRedirect(request.getContextPath() + "/redirect.jsp");
	} else { %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd"> <% Utility Utilback = new Utility(); %>
<html>
<head>
	<TITLE><%= getServletContext().getInitParameter("TITLENAME") %></TITLE>
<META http-equiv="Content-Type" content="text/html; charset=utf-8">
<META http-equiv="Content-Style-Type" content="text/css">
<LINK rel="stylesheet" type="text/css" media="print" href="../css/impression.css">
<LINK rel="stylesheet" href="../css/main.css" type="text/css">
<LINK rel="stylesheet" type="text/css" href="../css/style.css" />
<link type="text/css" rel="stylesheet" href="../css/table_new.css">
<link type="text/css" rel="stylesheet" href="../css/mybutton.css">
<link type="text/css" rel="stylesheet" href="../css/modal.css">
<link type="text/css" rel="stylesheet" href="../css/link.css">
<script type="text/javascript" src="../scripts/dropdowncontent.js"></script>

 <link rel="stylesheet" href="https://ajax.googleapis.com/ajax/libs/jqueryui/1.12.1/themes/smoothness/jquery-ui.css">
<script src="../css/bower_components/jquery/dist/jquery.min.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/moment.js/2.10.3/moment.min.js"></script>
 <script src="https://ajax.googleapis.com/ajax/libs/jqueryui/1.12.1/jquery-ui.min.js"></script>

<style type="text/css">

body ul .draggable {
	 will-change: transform;
	 list-style-type: none;
	 background-color: white;
	 color: #212121;
	 padding-left: 10px;
	 cursor: move;
	 transition: all 200ms;
	 user-select: none;
	 margin: 10px auto;
	 position: relative;
}
body ul .draggable:after {
	 right: 7px;
	 font-size: 10px;
	 position: absolute;
	 cursor: pointer;
	 line-height: 5;
	 transition: all 200ms;
	 transition-timing-function: cubic-bezier(0.48, 0.72, 0.62, 1.5);
	 transform: translateX(120%);
	 opacity: 0;
}
	
	body ul .draggable:hover:after {
	  opacity: 1;
	  transform: translate(0);
	}
	 
	.over {
	  transform: scale(1.1, 1.1);
	}


.table-extra td input
{
	width:100%;
	border:none;
	/* background:transparent; */
}

{
  box-sizing: border-box;
}

ul {
  list-style-type: none;
  padding: 0;
  margin: 0;
}

ul li {
  border: 1px solid #ddd;
  margin-top: -1px; /* Prevent double borders */
  background-color: #f6f6f6;
  padding: 12px;
  
  font-family: Roboto, Arial, Helvetica, sans-serif;
  font-size: 10pt;
  font-weight: bold;
  line-height: 1;
}

#mainContainer {
	width: 660px;
	margin: 0 auto;
	text-align: left;
	height: 100%;
	border-left: 3px double #000;
	border-right: 3px double #000;
}

#formContent {
	padding: 5px;
}
/* END CSS ONLY NEEDED IN DEMO */

/* Big box with list of options */
#ajax_listOfOptions {
	position: absolute; /* Never change this one */
	width: auto; /* Width of box */
	height: 200px; /* Height of box */
	overflow: auto; /* Scrolling features */
	border: 1px solid #317082; /* Dark green border */
	background-color: #C5E8CD; /* White background color */
	color: black;
	text-align: left;
	font-size: 1.0em;
	z-index: 100;
}

#ajax_listOfOptions div {
	/* General rule for both .optionDiv and .optionDivSelected */
	margin: 1px;
	padding: 1px;
	cursor: pointer;
	font-size: 1.0em;
}

#ajax_listOfOptions .optionDiv { /* Div for each item in list */
	
}

#ajax_listOfOptions .optionDivSelected { /* Selected item in the list */
	background-color: #317082; /*mau khi di chuyen */
	color: #FFF;
}

#ajax_listOfOptions_iframe {
	background-color: #F00;
	position: absolute;
	z-index: 5;
}

form {
	display: inline;
}

#dhtmltooltip {
	position: absolute;
	left: -300px;
	width: 150px;
	border: 1px solid black;
	padding: 2px;
	background-color: lightyellow;
	visibility: hidden;
	z-index: 100;
	/*Remove below line to remove shadow. Below line should always appear last within this CSS*/
	filter: progid:DXImageTransform.Microsoft.Shadow(color=gray, direction=135
		);
}

#dhtmlpointer {
	position: absolute;
	left: -300px;
	z-index: 101;
	visibility: hidden;
}
}
</style>
<!-- 
<script type="text/javascript" src="../scripts/jquery.min.1.7.js"></script> -->


<script src="https://code.jquery.com/jquery-3.4.1.min.js"></script>
<script type="text/javascript" language="JavaScript" src="../scripts/jquery.tools.min.js"></script>

<link href="../css/jquery-ui.css" rel="stylesheet" type="text/css" />
<script src="../scripts/ui/jquery.ui.core.js" type="text/javascript"></script>
<script src="../scripts/ui/jquery.ui.widget.js" type="text/javascript"></script>
<script src="../scripts/ui/jquery.ui.datepicker.js" type="text/javascript"></script>

<script type="text/javascript">
	$(document).ready(function() {		
		$( ".days" ).datepicker({	
			dateFormat: "yy-mm-dd",
				changeMonth: true,
				changeYear: true				
		});            
	});	
</script>

<script type="text/javascript" src="../scripts/ajax.js"></script>
<script type="text/javascript" src="../scripts/AjaxErpDonDatHang.js"></script>

<script language="javascript" type="text/javascript">

	function keypress(e) //Hàm dùng d? ngan ngu?i dùng nh?p các ký t? khác ký t? s? vào TextBox
	{    
		var keypressed = null;
		if (window.event)
			keypressed = window.event.keyCode; //IE
		else
			keypressed = e.which; //NON-IE, Standard
		
		if (keypressed < 48 || keypressed > 57)
		{ 
			if (keypressed == 8 || keypressed == 127 || keypressed == 37 || keypressed == 39 || keypressed == 0 || keypressed == 46)
			{//Phím Delete và Phím Back
				return;
			}
			return false;
		}
	}
	
	function myFunction() {
		  var x = document.getElementById("myDIV");
		  if (x.style.display === "none") {
		    x.style.display = "block";
		  } else {
		    x.style.display = "none";
		  }
		} 
	
	function replaces()
	{
		var spHansd = document.getElementsByName("spHansd");
		var spMa = document.getElementsByName("spMa");
		var spTen = document.getElementsByName("spTen");  
		var donvi = document.getElementsByName("donvi");
		var soluong = document.getElementsByName("soluong");
		var dongia = document.getElementsByName("dongia");
		var thanhtien = document.getElementsByName("thanhtien");
		
		var spvat = document.getElementsByName("spvat");
		
		var i;
		for(i = 0; i < spMa.length; i++)
		{
			if(spMa.item(i).value != "")
			{
				var sp = spMa.item(i).value;
				var pos = parseInt(sp.indexOf(" - "));

				if(pos > 0)
				{
					spMa.item(i).value = sp.substring(0, pos);
					sp = sp.substr(parseInt(sp.indexOf(" - ")) + 3);
					
					spTen.item(i).value = sp.substring(0, parseInt(sp.indexOf(" [")));
					sp = sp.substr(parseInt(sp.indexOf(" [")) + 2);
					
					donvi.item(i).value = sp.substring(0, parseInt(sp.indexOf("] [")));
					sp = sp.substr(parseInt(sp.indexOf("] [")) + 3);
					
					
					dongia.item(i).value = sp.substring(0, parseInt(sp.indexOf("]"))); 
					
					
					<% if(lsxBean.getIsdhKhac().equals("1")){ %>
					
					dongia.item(i).value = "0";
					
					<%}%>
					
					
					sp = sp.substr(parseInt(sp.indexOf("] [")) + 3);
					
					//dongia.item(i).value = sp.substring(0, parseInt(sp.indexOf("]"))); 
					spvat.item(i).value = sp.substring(0, parseInt(sp.indexOf("]")));
				}
			}
			else
			{
				spMa.item(i).value = "";
				spTen.item(i).value = "";
				donvi.item(i).value = "";
				soluong.item(i).value = "";
				dongia.item(i).value = "";
				thanhtien.item(i).value = "";	
				spvat.item(i).value = "";
			}
		}	
		
		TinhTien();
		setTimeout(replaces, 300);
	}
	
	
	 function TinhTien()
	 {
		var spMa = document.getElementsByName("spMa");
		var soluong = document.getElementsByName("soluong");
		var dongia = document.getElementsByName("dongia");
		var thueVAT = document.getElementsByName("spvat");
		var thanhtien = document.getElementsByName("thanhtien");
		
		var tongtien = 0;
		var tt_vat = 0;
		for(var i = 0; i < spMa.length; i++)
		{
			if(spMa.item(i).value != "" && dongia.item(i).value != "" && soluong.item(i).value != "" )
			{
				var _thueVAT = thueVAT.item(i).value.replace(/,/g,"");
				if(_thueVAT == '')
					_thueVAT = '0';	
				
				
				var tt = Math.round ( parseFloat(dongia.item(i).value.replace(/,/g,"")) * parseFloat(soluong.item(i).value.replace(/,/g,"")) );
				
				var tienthue = Math.round ( parseFloat(tt) * parseFloat(_thueVAT) / 100 );
				
				thanhtien.item(i).value = DinhDangTien( parseFloat(tt) + parseFloat(tienthue) );
				
				tongtien += parseFloat (tt );
				tt_vat += parseFloat (tienthue );
			}
		}
		
		var chietkhau = document.getElementById("txtPTChietKhau").value;
		if(chietkhau == '')
			chietkhau = '0';
		
		/* var vat = document.getElementById("txtVAT").value;
		if(vat == '')
			vat = '0'; */
		
		document.getElementById("txtVAT").value = DinhDangTien(tt_vat);
		
		var tongtienCK = parseFloat(chietkhau) * parseFloat(tongtien) / 100;
		var tongtienSAUCK = parseFloat(tongtien) - parseFloat(tongtienCK);
		
		document.getElementById("txtTongCK").value = DinhDangTien(tongtienCK);
		document.getElementById("txtBVAT").value = DinhDangTien(tongtienSAUCK);
		document.getElementById("txtTongSauCK").value = DinhDangTien(tongtienSAUCK);
		
		//var tongtienSAUVAT = parseFloat(tongtienSAUCK) + ( parseFloat(vat) * parseFloat(tongtienSAUCK) / 100 );
		var tongtienSAUVAT = parseFloat(tongtienSAUCK) + parseFloat(tt_vat);
		document.getElementById("txtSauVAT").value = DinhDangTien(tongtienSAUVAT);

		var tongtienkm =  document.getElementById("tongtienKMHidden").value;
		document.getElementById("txtTongGiaTri").value =  DinhDangTien ( parseFloat(tongtienSAUVAT)  -   parseFloat(tongtienkm) );
		
	 }
	
	 function CapNhatGia(e, stt)
	 { 
		 	//alert(e.value);
		 	
		 	var spMa = document.getElementsByName("spMa");
		 	var dongia = document.getElementsByName("dongia");
		 	//dongia.item(stt).value = DinhDangTien(e.value); 
		 	var ngaychuyen=document.getElementById("ngaychuyen").value.replace(" ","+");
		    //DUNG AJAX LOAD LAI DON GIA NEU KO PHAI LA DON VI CHUAN
		 	var xmlhttp;
			if ( e.value == "" || spMa.item(stt).value == "" )
			{
				dongia.item(stt).value = "0";
			    return;
			}
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
				   //alert(xmlhttp.responseText);
				   dongia.item(stt).value = DinhDangTien(xmlhttp.responseText);
			   }
			}
			
			var dvtMA = encodeURIComponent(e.value.replace(" ","+"));
			var spMA = encodeURIComponent(spMa.item(stt).value.replace(" ","+"));
			xmlhttp.open("GET","../../ErpDondathangNppSvl?spMa=" + spMA + "&dvt=" + dvtMA + "&locgiaQUYDOI=1&ngaydh="+ngaychuyen,true);
			xmlhttp.send();
	 }
	 
	 function checkSlgTraKm(name)
	 {
		var slg = document.getElementsByName(name);
		var idTlg = name.substring(0, name.lastIndexOf("."));
		var k = document.getElementById(idTlg + ".soluongmain").value;
		var sum = 0;
		for( var i = 0; i < slg.length; i++)
		{
			if(slg.item(i).value != '')
			{ sum = parseInt(sum) + parseInt(slg.item(i).value); }
			if(parseInt(sum) > parseInt(k))
			{
				slg.item(i).value = "";
				alert('Số lượng bạn nhập vào,lớn hơn tổng số lượng trả khuyến mãi (' + k + '), Vui lòng điều chỉnh.');
				return;
			}
		}
	 }
	 
	 function saveform()
	 {	
		 document.getElementById("btnSave").innerHTML = "<a href='#'><img src='../images/waiting.gif'  title='cho luu..' border='1' longdesc='cho luu..' style='border-style:outset'> Processing...</a>";
	 	 document.forms['hctmhForm'].action.value = 'save';
	     document.forms['hctmhForm'].submit();
	 }
	 
	 function save_donhang_va_khuyen_mai()
	 {	
		 document.getElementById("btnSaveKM").innerHTML = "<a href='#'><img src='../images/waiting.gif'  title='cho luu..' border='1' longdesc='cho luu..' style='border-style:outset'> Processing...</a>";
	 	 document.forms['hctmhForm'].action.value = 'save_donhang_va_khuyen_mai';
	     document.forms['hctmhForm'].submit();
	 }
	 
	 function submitform()
	 { 
		 document.forms['hctmhForm'].action.value='submit';
	     document.forms["hctmhForm"].submit();
	 }
	 
	function dieuchinhKM()
	{  
		var SchemeId = document.getElementsByName("SchemeId");
		var schemeOR = document.getElementsByName("schemeOR");
		for(var pos = 0; pos < SchemeId.length; pos++)
  		{
			if(schemeOR.item(pos).value == "1")
  			{
				var trakmSelected = document.getElementsByName(SchemeId.item(pos).value + ".chontrakm");
				var selected = '';
				if(trakmSelected.length <= 1)
					selected = trakmSelected.item(0).value;
				
				for( var jj = 0; jj < trakmSelected.length; jj++)
				{
					if(trakmSelected.item(jj).checked)
					{
						selected = trakmSelected.item(jj).value;
					}
				}
				if(selected == '')
				{
					alert("Vui lòng chọn trả khuyến mãi cho chương trình khuyến mãi được chọn.");
					document.getElementById("Show_All").checked = true;
					return;
				}
  			}
  		}	
		
	   	document.forms['hctmhForm'].action.value='dieuchinhkm';
	   	document.forms['hctmhForm'].submit();
	}
	 
	 function Apkhuyenmai()
	 {
		 //document.getElementById("myModal").style.display = "block";
		 document.getElementById("btnApKhuyenMai").innerHTML = "<a href='#'><img src='../images/waiting.gif'  title='cho thuc hien..' border='1' longdesc='cho thuc hien..' style='border-style:outset'> Processing...</a>";
		 document.forms['hctmhForm'].action.value='apkhuyenmai_2';
		 document.forms['hctmhForm'].submit();
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
		element.value = DinhDangTien(element.value);
		if(element.value== '' )
		{
			element.value= '';
		}
	}	
	 
	function clearform()
	{
		var dongia = document.getElementsByName("dongia");
		var spMa = document.getElementsByName("spMa");
	
		
		var tongtien = 0;
		var tt_vat = 0;
		for(var i = 0; i < spMa.length; i++)
		{
			spMa.item(i).value="";
			dongia.item(i).value="";
			
		}
		
		submitform();
	}
</script>

<link href="../css/select2.css" rel="stylesheet" />
<script src="../scripts/select2.js"></script>
<!-- <script>
	$(document).ready(function()
	{
		$(".select2").select2();
		
	});
</script> -->

<script type="text/javascript" src="../scripts/ajax.js"></script>
<LINK rel="stylesheet" type="text/css" media="screen" href="../css/tabs.css">
<LINK rel="stylesheet" type="text/css" media="screen" href="../css/tabs-panes.css">

<script>

$(function() {
 
 	$("ul.tabs").tabs("div.panes > div");
});
</script>

</head>
<BODY leftmargin="0" bottommargin="0" topmargin="0" rightmargin="0">
<form name="hctmhForm" method="post" action="../../ErpDondathangNppUpdateSvl">
<% redis.clients.jedis.Jedis jedis =  geso.dms.center.util.Utility.getJedis(); %><% geso.dms.center.util.Csrf csdr=new geso.dms.center.util.Csrf(request,response,true,false,true);%>
 <input type="hidden" name="<%=csdr.get_tokenName() %>" value='<%=csdr.get_tokenValue() %>'>

<input type="hidden" name="userId" value='<%= userId %>'>
<input type="hidden" name="action" value='1'>
<input type="hidden" name="danh_sach_ctkm_Str" value='<%= lsxBean.getDanh_sach_ctkm_Str() %>'>
<input type="hidden" name="dieuchinh" value='<%= dieuchinh %>'>
<input type="hidden" name="loaidonhang" value='<%= lsxBean.getLoaidonhang() %>'>

<div id="main" style="width:100%; padding-left:2px"> 
	<div align="left" id="header" style="width:100%; float:none">
    	<div style="width:55%; padding:5px; float:left" class="tbnavigation">
        	&nbsp;Quản lý tồn kho > Mua hàng từ nhà cung cấp > Đơn đặt hàng > Tạo mới
        </div>
        <div align="right" style="padding:5px" class="tbnavigation">
        	Chào mừng nhà phân phối <%= lsxBean.getNppTen() %> &nbsp;
        </div>
    </div>
  
  	<div align="left" id="button" style="width:100%; height:32px; padding:0px; float:none" class="tbdarkrow">
    	<A href= "../../RouterSvl?task=<%= Utilback.dongMa(getServletContext().getInitParameter("RedirectNoScript") +"ErpDondathangNppSvl?userId="+ userId +"&loaidonhang="+ lsxBean.getLoaidonhang()+"") %>" >
        	<img src="../images/Back30.png" alt="Quay ve"  title="Quay ve" border="1px" longdesc="Quay ve" style="border-style:outset"></A>
       <!--  <span id="btnSave">
	        <A href="javascript:saveform()" >
	        	<IMG src="../images/Save30.png" title="Luu lai" alt="Luu lai" border ="1px" style="border-style:outset"></A>
        </span> -->
    </div>
  	
  	<div align="left" style="width:100%; float:none; clear:left">
  		<fieldset>
    		<legend class="legendtitle"> Thông báo</legend>
    		<textarea name="dataerror"  rows="1" readonly="readonly" style ="width:100%"><%= lsxBean.getMsg() %></textarea>
		         <% lsxBean.setMsg(""); %>
    	</fieldset>
  	</div>
  	
    <div align="left" style="width:100%; float:none; clear:left">
    <fieldset>
    	<legend class="legendtitle">Đơn đặt hàng </legend>
        	<div style="float:none; width:100%" align="left">
            
            <TABLE width="100%" cellpadding="4" cellspacing="0">							
                <TR>
                    <TD width="130px" class="plainlabel" valign="top">Ngày đơn hàng </TD>
                    <TD width="250px"  class="plainlabel" valign="top" >
                    	<%-- <input type="text" class="days" readonly="readonly"  name="ngaychuyen"  id="ngaychuyen" value="<%= lsxBean.getNgayyeucau() %>"/>
         --%>           
                   		<input type="text"
																class="days" size="11" 
																id="ngaychuyen" name="ngaychuyen"
																value="<%= lsxBean.getNgayyeucau()  %>" maxlength="10"
																readonly />
                   
                    </TD>
                    
                    <TD width="120px" class="plainlabel" valign="top">Ngày đề nghị giao </TD>
                    <TD class="plainlabel" valign="top">
                    	<input type="text" class="days" readonly="readonly"  name="ngaydenghi" value="<%= lsxBean.getNgaydenghi() %>"/>
                    </TD>
                </TR>
                
                <TR>
                	<TD class="plainlabel" valign="top">Đơn vị kinh doanh </TD>
                    <TD class="plainlabel" valign="top"  >
                    	<select name = "dvkdId" onchange="submitform();" class="select2" style="width: 200px" >
                    		<option value=""> </option>
                        	<%
                        		if(dvkdRs != null)
                        		{
                        			try
                        			{
                        			while(dvkdRs.next())
                        			{  
                        			if( dvkdRs.getString("pk_seq").equals(lsxBean.getDvkdId())){ %>
                        				<option value="<%= dvkdRs.getString("pk_seq") %>" selected="selected" ><%= dvkdRs.getString("ten") %></option>
                        			<%}else { %>
                        				<option value="<%= dvkdRs.getString("pk_seq") %>" ><%= dvkdRs.getString("ten") %></option>
                        		 <% } } dvkdRs.close();} catch(SQLException ex){}
                        		}
                        	%>
                    	</select>
                    </TD>   
                    <TD class="plainlabel" valign="top">Kênh bán hàng </TD>
                    <TD class="plainlabel" valign="top"   >
                    	<select name = "kbhId" class="select2" style="width: 200px" onchange="clearform();" >
                    		<option value=""  > </option>
                        	<%
                        		if(kbhRs != null)
                        		{
                        			try
                        			{
                        			while(kbhRs.next())
                        			{  
                        			if( kbhRs.getString("pk_seq").equals(lsxBean.getKbhId())){ %>
                        				<option value="<%= kbhRs.getString("pk_seq") %>" selected="selected" ><%= kbhRs.getString("ten") %></option>
                        			<%}else { %>
                        				<option value="<%= kbhRs.getString("pk_seq") %>" ><%= kbhRs.getString("ten") %></option>
                        		 <% } } kbhRs.close();} catch(SQLException ex){}
                        		}
                        	%>
                    	</select>
                    </TD>         	
                </TR>
                
                <TR>
                	<TD class="plainlabel" valign="top">Nhà phân phối </TD>
                    <TD class="plainlabel" valign="top"  >
                    	<input type="hidden" name = "nppId" value="<%= lsxBean.getNppId() %>" > 
                    	<select class="select2" style="width: 200px" disabled="disabled" >
                    		<option value=""> </option>
                        	<%
                        		if(nppRs != null)
                        		{
                        			try
                        			{
                        			while(nppRs.next())
                        			{  
                        			if( nppRs.getString("pk_seq").equals(lsxBean.getNppId())){ %>
                        				<option value="<%= nppRs.getString("pk_seq") %>" selected="selected" ><%= nppRs.getString("ten") %></option>
                        			<%}else { %>
                        				<option value="<%= nppRs.getString("pk_seq") %>" ><%= nppRs.getString("ten") %></option>
                        		 <% } } nppRs.close();} catch(SQLException ex){}
                        		}
                        	%>
                    	</select>
                    </TD>   
                    <TD class="plainlabel" valign="top">Kho đặt </TD>
                    <TD class="plainlabel" valign="top"  >
                    	<select name = "khonhapId" class="select2" style="width: 200px" >
                        	<%
                        		if(khonhapRs != null)
                        		{
                        			try
                        			{
                        			while(khonhapRs.next())
                        			{  
                        			if( khonhapRs.getString("pk_seq").equals(lsxBean.getKhoNhapId())){ %>
                        				<option value="<%= khonhapRs.getString("pk_seq") %>" selected="selected" ><%= khonhapRs.getString("ten") %></option>
                        			<%}else { %>
                        				<option value="<%= khonhapRs.getString("pk_seq") %>" ><%= khonhapRs.getString("ten") %></option>
                        		 <% } } khonhapRs.close();} catch(SQLException ex){}
                        		}
                        	%>
                    	</select>
                    </TD>         	
                </TR>
                
                <TR>
                    <TD class="plainlabel" >Tổng giá trị </TD>
                    <TD class="plainlabel"  >
                    	<input type="text" readonly="readonly"  id="txtBVAT" value="" style="text-align: right;"  />
                    	<input type="hidden"  value="<%= lsxBean.getChietkhau() %>" id="txtPTChietKhau" style="text-align: right;" name="ptChietkhau" onkeypress="return keypress(event);" onkeyup="Dinhdang(this);" />
                    </TD>
                    
                    <%-- <TD class="plainlabel"  > Đơn hàng KM có HĐTC </td>
                    <TD class="plainlabel"  >
                           <input type="checkbox" onchange="clearform();"  name="iskm"  id="iskm" readonly="readonly" <%=lsxBean.getIsKm().equals("1")?"checked=\"checked\"":"" %>   value="1" style="text-align: right;"  />
                    </TD> --%>
                    
                    <TD class="plainlabel"  > Đơn hàng khác, KM </td>
                    <TD class="plainlabel"  >
                           <input type="checkbox" onchange="clearform();"  name="isdhkhac"  id="isdhkhac" readonly="readonly" <%=lsxBean.getIsdhKhac().equals("1")?"checked=\"checked\"":"" %>   value="1" style="text-align: right;"  />
                    </TD>
                    
                </TR>
                
                <TR style="display: none;" >
                    <TD class="plainlabel" valign="top">Tổng tiền chiết khấu </TD>
                    <TD class="plainlabel" valign="top" >
                    	<input type="text" readonly="readonly"  value="" id="txtTongCK" style="text-align: right;" /></TD>
                    	
                    <TD class="plainlabel" valign="top">Tổng tiền sau CK </TD>
                    <TD class="plainlabel" valign="top">
                    	<input type="text" readonly="readonly"  value="" id="txtTongSauCK" style="text-align: right;" />
                    </TD>
                </TR>
                
                <TR>
                    <TD class="plainlabel" valign="top">Tiền VAT </TD>
                    <TD class="plainlabel" valign="top" >
                    	<input type="text" value="<%= lsxBean.getVat() %>" id="txtVAT" style="text-align: right;" name="ptVat" onkeypress="return keypress(event);" onkeyup="Dinhdang(this);" readonly='readonly' />
                    </TD>
                    	
                    <TD class="plainlabel" valign="top">Tổng tiền sau VAT </TD>
                    <TD class="plainlabel" valign="top">
                    	<input type="text" readonly="readonly"  value="" id="txtSauVAT" style="text-align: right;" />
                    </TD>
                </TR>
                
               
                <TR>
                	 <TD class="plainlabel" valign="top">Tổng tiền</TD>
                    <TD class="plainlabel" valign="top">
                    	<input type="text" readonly="readonly"  value="" id="txtTongGiaTri" style="text-align: right;" />
                    </TD>
                	<TD class="plainlabel" >Ghi chú </TD>
                    <TD class="plainlabel" >
                    	<input type="text"  name="ghichu" value="<%= lsxBean.getGhichu() %>" style="width: 700px;" />
                    </TD>
                </TR>
				<%if ( !lsxBean.getLoainpp().equals("0") && ! lsxBean.getIsdhKhac().equals("1") ){ %>
                <TR>
				  	<TD class="plainlabel" colspan = '4'>
				  	<DIV id="btnApKhuyenMai">
				  	<a style="height: 26px;" class="button2" href="javascript:Apkhuyenmai()"><img style="top: -4px;" src="../images/New.png" alt="">Áp khuyến mãi và Lưu </a>
				 	</DIV>					
				  	</TD>
				</TR>
				<%} else { %>
					<TR>
				  	<TD class="plainlabel" colspan = '4'>
				  	<DIV id="btnSave">
				  	<a style="height: 26px;" class="button2" href="javascript:saveform()"><img style="top: -4px;" src="../images/New.png" alt="">Lưu </a>
				 	</DIV>					
				  	</TD>
				</TR>
				<%} %>
            </TABLE>
			 
			<% if(lsxBean.getData().trim().length() > 0 )
  			   { 
  			    request.setCharacterEncoding("utf-8");  
  			%> 	
  			 	<jsp:include page="ErpKhuyenMaiDonDatHangNpp.jsp" flush="true">
    			<jsp:param name="json_data" value="<%= lsxBean.getData()%>"/>
    			<jsp:param name="json_dieuchinh" value="<%= lsxBean.getDieuchinh()%>"/>
				</jsp:include>
  		 	<% } %>
			
				<table class="tablechitiet table table-bordered table-extra" style="width : 100%">
					<tr class="tbheader">
						<th align="center" width="3%" >STT</th>
						<th align="center" width="12%">Mã sản phẩm</th>
						<th align="center" width="25%">Tên sản phẩm</th>
						<th align="center" width="10%">Đơn vị</th>
						<th align="center" width="10%">Số lượng</th>
						<th align="center" width="10%">% VAT</th>
						<th align="center" width="10%">Đơn giá</th>
						<th align="center" width="10%">Thành tiền</th>
						<th align="center" width="10%">CTKM</th>
					</tr>
					<%
						int count = 0;
						String loaiNPP = lsxBean.getLoainpp();
						boolean choCHON_DVT = false;
						/* if(lsxBean.getKbhId().equals("100025"))  //OTC không cho chọn đv khác
							choCHON_DVT = false;
						else
						{
							if( loaiNPP.equals("4") || loaiNPP.equals("5") )
								choCHON_DVT = true;
						} */
						
						if(spMa != null)
						{
							for(int i = 0; i < spMa.length; i++)
							{ if(spMa[i].length() > 0){%>
							<tr>
								<td style="text-align: center;" > <%= i + 1 %> </td>
								<td>
									<input type="text" name="spMa"  value="<%= spMa[i] %>" style="width: 100%"  onkeyup="ajax_showOptions(this,'nhapkho',event)" AUTOCOMPLETE="off"  > 
								</td>
								<td> <input type="text" name="spTen" value="<%= spTen[i] %>" style="width: 100%"  readonly="readonly"> </td>
								<td>
								<% if(!choCHON_DVT) { %>
									<input type="text" name="donvi" value="<%= spDonvi[i] %>" style="width: 100%" readonly="readonly">
								<% } else { %>
									<select name="donvi" style="width: 100%" onchange="CapNhatGia(this, <%= count %>);">
										<option value="" ></option>
										<% if(dvtRs != null) 
										{ 
												dvtRs.beforeFirst();
												while(dvtRs.next())
												{
													if(spDonvi[i].equals(dvtRs.getString("DONVI")))
													{ %>
														<option value="<%= dvtRs.getString("DONVI") %>" selected="selected" ><%= dvtRs.getString("DONVI") %></option>
													<% } else { %>
														<option value="<%= dvtRs.getString("DONVI") %>" ><%= dvtRs.getString("DONVI") %></option>
												   <% } }
										} %>
									 </select> 
								<% } %>
								</td>
								<%-- <td> <input type="text" name="soluongTHUNG" value="<%= spSoluong[i] %>" style="width: 100%; text-align: right;" onkeypress="return keypress(event);" onkeyup="Dinhdang(this);" > </td> --%>
								<td> <input type="text" name="soluong" value="<%= spSoluong[i] %>" style="width: 100%; text-align: right;" onkeypress="return keypress(event);" onkeyup="Dinhdang(this);" > </td>
								
								<td> <input type="text" name="spvat" value="<%= spVat[i] %>" style="width: 100%; text-align: right;" readonly="readonly"  > </td>
								
								<%-- <td> <input type="text" name="dongiaTHUNG" value="<%= spGianhap[i] %>" style="width: 100%; text-align: right;" readonly="readonly" > </td> --%>
								<td> <input type="text" name="dongia" value="<%= spGianhap[i] %>" style="width: 100%; text-align: right;" <%= lsxBean.getKbhId().equals("100052") ? "" : "readonly='readonly'"  %>  onkeypress="return keypress(event);" > </td>
								
								<td> <input type="text" name="thanhtien" value="" style="width: 100%; text-align: right;" readonly="readonly" > </td>
								<td> <input type="text" name="scheme" value="" style="width: 100%; text-align: right;" readonly="readonly" > </td>
							</tr>	
								
						<% count ++; } } } %>
					
					<% for(int i = count; i < 5; i++) { %>
						
						<tr class="duplicate">
							<td style="text-align: center;"><input class="stt" type="text" readonly="readonly" id="stt_<%=i %>" value="<%= i + 1 %>"></td>
							<td>
								<input type="text" name="spMa" id="spMa_<%=i %>" value="" style="width: 100%"  onkeyup="ajax_showOptions(this,'nhapkho',event)" AUTOCOMPLETE="off"  > 
							</td>
							<td> <input type="text" name="spTen" id="spTen_<%=i %>" value="" style="width: 100%" readonly="readonly"> </td>
							<td>
							<% if(!choCHON_DVT) { %>
								<input type="text" name="donvi" id="donvi_<%=i %>" value="" style="width: 100%" readonly="readonly">
							<% } else { %>
								<select name="donvi" id="donvi_<%=i %>" style="width: 100%" onchange="CapNhatGia(this, <%= i %>);"   >
									<option value="" ></option>
										<% if(dvtRs != null) 
										{ 
											dvtRs.beforeFirst();
											while(dvtRs.next())
											{ %>
												<option value="<%= dvtRs.getString("DONVI") %>"  ><%= dvtRs.getString("DONVI") %></option>
										   <% } 
										} %>
									 </select> 
							<% } %>
							 </td>
							<!-- <td> <input type="text" name="soluongTHUNG" value="" style="width: 100%; text-align: right;" onkeypress="return keypress(event);" onkeyup="Dinhdang(this);" > </td> -->
							<td> <input type="text" name="soluong" id="soluong_<%=i %>" value="" style="width: 100%; text-align: right;" onkeypress="return keypress(event);" onkeyup="Dinhdang(this);" > </td>
							<!-- <td> <input type="text" name="dongiaTHUNG" value="" style="width: 100%; text-align: right;" readonly="readonly" > </td> -->
							
							<td> <input type="text" name="spvat" id="spvat_<%=i %>" value="" style="width: 100%; text-align: right;" readonly="readonly"  > </td>
							
							<td> <input type="text" name="dongia" id="dongia_<%=i %>" value="" style="width: 100%; text-align: right;" <%= lsxBean.getKbhId().equals("100052") ? "" : "readonly='readonly'"  %>  onkeypress="return keypress(event);" > </td>
							
							<td> <input type="text" name="thanhtien" id="thanhtien_<%=i %>" value="" style="width: 100%; text-align: right;" readonly="readonly" > </td>
							<td> <input type="text" name="scheme" id="scheme_<%=i %>" value="" style="width: 100%; text-align: right;" readonly="readonly" > </td>
						</tr>	
						
					<% }
					
					double tongtienKM = 0;
					%>	
						<input type="hidden" id ="tongtienKMHidden"  value="<%= tongtienKM %>" style="width: 100%; color: red" readonly >
				</table>
     		</div>
     </fieldset>	
    </div>
</div>
<%
	try
	{
		dvtRs.close();
		lsxBean.DBclose(); 
	} catch(Exception er){ }
} 
%>
</form>
<!-- <script type="text/javascript" src="../scripts/Sortable/jquery.min.js"></script>
<script type="text/javascript" src="../scripts/Sortable/jquery-ui.min.js"></script> -->
 
 
 <script type="text/javascript">
var remove = document.querySelector('.draggable');
  
  function dragStart(e) {
    this.style.opacity = '0.4';
    dragSrcEl = this;
    e.dataTransfer.effectAllowed = 'move';
    e.dataTransfer.setData('text/html', this.innerHTML);
  };
   
  function dragEnter(e) {
    this.classList.add('over');
  }
   
  function dragLeave(e) {
    e.stopPropagation();
    this.classList.remove('over');
  }
   
  function dragOver(e) {
    e.preventDefault();
    e.dataTransfer.dropEffect = 'move';
    return false;
  }
   
  function dragDrop(e) {
    if (dragSrcEl != this) {
      dragSrcEl.innerHTML = this.innerHTML;
      this.innerHTML = e.dataTransfer.getData('text/html');
    }
    return false;
  }
   
  function dragEnd(e) {
    var listItens = document.querySelectorAll('.draggable');
    [].forEach.call(listItens, function(item) {
      item.classList.remove('over');
    });
    this.style.opacity = '1';
  }
   
  function addEventsDragAndDrop(el) {
    el.addEventListener('dragstart', dragStart, false);
    el.addEventListener('dragenter', dragEnter, false);
    el.addEventListener('dragover', dragOver, false);
    el.addEventListener('dragleave', dragLeave, false);
    el.addEventListener('drop', dragDrop, false);
    el.addEventListener('dragend', dragEnd, false);
  }
   
  var listItens = document.querySelectorAll('.draggable');
  [].forEach.call(listItens, function(item) {
    addEventsDragAndDrop(item);
  });
  
</script>
 
<script>
  $(document).ready(function() {
	//$("#sortable").sortable();
	$(".select2").select2();
  });
  //Get the modal
  var modal = document.getElementById("myModal");
  // When the user clicks anywhere outside of the modal, close it
  window.onclick = function(event) {
    if (event.target == modal) {
      modal.style.display = "none";
    }
  }
  
  replaces();
  
  var count = 0;
	$('body').on('focus', ".duplicate:last input", function() {
	  count++;
	  var $clone = $('.duplicate:last').clone();
	  $clone.find("input,select").each(function() {
	    $(this).attr({
	      id: $(this).attr("id").split('_')[0] + '_' + (parseInt($(this).attr("id").split('_')[1]) + 1),
	    });
	  });
	  
	  $clone.find(".stt").each(function() {
		    $(this).attr({
		      value: (parseInt($(this).attr("id").split('_')[1]) + 1),
		    });
		  });
	  
	  $(".tablechitiet tbody").append($clone);
	  $(".duplicate:last .cleanVal").val('');
	});
</script> 
</BODY>
</HTML>