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

<% IErpDondathangDoitac lsxBean = (IErpDondathangDoitac)session.getAttribute("lsxBean"); %>
<% ResultSet dvkdRs = lsxBean.getDvkdRs(); %>
<% ResultSet kbhRs = lsxBean.getKbhRs(); %>
<% ResultSet nppRs = lsxBean.getKhRs(); %>
<% ResultSet khonhapRs = lsxBean.getKhoNhapRs(); %>
<% ResultSet dvtRs = lsxBean.getDvtRs(); %>
<% ResultSet congnoRs = lsxBean.getCongnoRs(); %>

<% 
	String[] spMa = lsxBean.getSpMa(); 
	String[] spTen = lsxBean.getSpTen();
	String[] spDonvi = lsxBean.getSpDonvi();
	String[] spSoluong = lsxBean.getSpSoluong();
	String[] spGianhap = lsxBean.getSpGianhap();
	String[] spGiagoc = lsxBean.getSpGiagoc();
	String[] spChietkhau = lsxBean.getSpChietkhau();
	String[] spVat = lsxBean.getSpVat();
	String[] spSoluonton = lsxBean.getSpSoluongton();
	String[] spToncn = lsxBean.getSptonkhocn();

	NumberFormat formater = new DecimalFormat("##,###,###");
%>

<% String userId = (String) session.getAttribute("userId");  %>
<% String userTen = (String) session.getAttribute("userTen");  
	String sum = (String) session.getAttribute("sum");
	Utility util = (Utility) session.getAttribute("util");
	
	if(!util.check(userId, userTen, sum)){
		response.sendRedirect("/SalesUpERP/index.jsp");
	} else { %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd"> <% Utility Utilback = new Utility(); %>
<html>
<head>
	<TITLE><%= getServletContext().getInitParameter("TITLENAME") %></TITLE>
<META http-equiv="Content-Type" content="text/html; charset=utf-8">
<META http-equiv="Content-Style-Type" content="text/css">
<LINK rel="stylesheet" type="text/css" media="print" href="../css/impression.css">
<LINK rel="stylesheet" href="../css/main.css" type="text/css">
<link type="text/css" rel="stylesheet" href="../css/mybutton.css">
<LINK rel="stylesheet" type="text/css" href="../css/style.css" />
<script type="text/javascript" src="../scripts/dropdowncontent.js"></script>

<link type="text/css" rel="stylesheet" href="../css/modal.css">
<link type="text/css" rel="stylesheet" href="../css/table_new.css">
<link type="text/css" rel="stylesheet" href="../css/link.css">

<style type="text/css">

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
<script type="text/javascript" src="../scripts/erp-SpListDonDatHang-DoiTac.js"></script>

<script language="javascript" type="text/javascript">

	function keypress(e) //H??m d??ng d? ngan ngu?i d??ng nh?p c??c k?? t? kh??c k?? t? s? v??o TextBox
	{    
		var keypressed = null;
		if (window.event)
			keypressed = window.event.keyCode; //IE
		else
			keypressed = e.which; //NON-IE, Standard
		
		if (keypressed < 48 || keypressed > 57)
		{ 
			if (keypressed == 8 || keypressed == 127 || keypressed == 37 || keypressed == 39 || keypressed == 0 || keypressed == 46)
			{//Ph??m Delete v?? Ph??m Back
				return;
			}
			return false;
		}
	}
	function roundNumber(num, dec) 
	{
		var result = Math.round(num*Math.pow(10,dec))/Math.pow(10,dec);
		return result;
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
		var spSoluongtongoc = document.getElementsByName("soluongtongoc") ; 
		var spSoluongton = document.getElementsByName("soluongton") ; 
		var trongluong = document.getElementsByName("spTrongLuong");
		var thetich = document.getElementsByName("spTheTich");
		var spQuyDoi = document.getElementsByName("spQuyDoi");
		
		var spvat = document.getElementsByName("spvat");
		
		var ptchietkhau = document.getElementById("txtPTChietKhau").value;
		if(ptchietkhau == '')
			ptchietkhau = 0;
		
		var dongiagoc = document.getElementsByName("dongiagoc");
		
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
					
					dongia.item(i).value = sp.substring(0, parseInt(sp.indexOf("] ["))) ;
					
					<%if(lsxBean.getIsdhk().equals("1")){%>
						dongia.item(i).value = "0";
					<%}%>
					sp = sp.substr(parseInt(sp.indexOf("] [")) + 3);
					
					dongiagoc.item(i).value = dongia.item(i).value ;
					
					if( parseFloat(ptchietkhau) > 0  )
					{
						dongia.item(i).value = parseFloat(dongia.item(i).value) * ( 1 - parseFloat(ptchietkhau) / 100 );

						var sole = '';
						if(dongia.item(i).value.indexOf(".") >= 0)
						{
							sole = dongia.item(i).value.substring(dongia.item(i).value.indexOf('.'), parseInt(dongia.item(i).value.indexOf('.')) + 5 );
						}
						var dg = dongia.item(i).value.split(".");
						dongia.item(i).value = dg[0] + sole;
						
					}
					
					spvat.item(i).value = sp.substring(0, parseInt(sp.indexOf("]"))); 
					sp = sp.substr(parseInt(sp.indexOf("] [")) + 3);
					spSoluongton.item(i).value = sp.substring(0, parseInt(sp.indexOf("]")));
					spSoluongtongoc.item(i).value = spSoluongton.item(i).value;  
				}
			}
			else
			{
				spMa.item(i).value = "";
				spTen.item(i).value = "";
				donvi.item(i).value = "";
				soluong.item(i).value = "";
				spSoluongton.item(i).value = "";
				spSoluongtongoc.item(i).value = "";
				dongia.item(i).value = "";
				thanhtien.item(i).value = "";	
				trongluong.item(i).value = "";	
				thetich.item(i).value = "";	
				spQuyDoi.item(i).value = "";
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
		var chietkhau = document.getElementsByName("chietkhau");
		var thueVAT = document.getElementsByName("spvat");
		var thanhtien = document.getElementsByName("thanhtien");
		
		var trongluong = document.getElementsByName("spTrongLuong");
		var thetich = document.getElementsByName("spTheTich");
		var spQuyDoi = document.getElementsByName("spQuyDoi");
		var spDonVi = document.getElementsByName("donvi");
		
		/* var ptchietkhau = document.getElementById("txtPTChietKhau").value;
		if(ptchietkhau == '')
			ptchietkhau = 0; */
		
		var vat = document.getElementById("txtVAT").value;
		if(vat == '')
			vat = '0';
		
		var tongtien = 0;
		var tongtienCK = 0;
		var tongtienVAT = 0;
		
		var totalTL = 0;
		var totalTT = 0;
		
		
		var totalTHG = 0;
		var totalLe = 0 ;
		  var thuevat_chung=0;	
		for(var i = 0; i < spMa.length; i++)
		{
			if(spMa.item(i).value != "" && dongia.item(i).value != "" && soluong.item(i).value != "" )
			{
				/* 27/01
				var tmpCK =  roundNumber(parseFloat(dongia.item(i).value.replace(/,/g,"")) * (parseFloat(ptchietkhau) / 100 ),4);
				var tmpCK2 = Math.round(tmpCK * parseFloat(soluong.item(i).value.replace(/,/g,"")));
				chietkhau.item(i).value = DinhDangTien(parseFloat(tmpCK2));
				var _ck = chietkhau.item(i).value.replace(/,/g,"");  */
				
				/* chietkhau.item(i).value = DinhDangTien( parseFloat(dongia.item(i).value.replace(/,/g,"")) * parseFloat(soluong.item(i).value.replace(/,/g,"")) * parseFloat(ptchietkhau) / 100 );
				var _ck = chietkhau.item(i).value.replace(/,/g,""); */
				
				/* if(_ck == '')
					_ck = '0'; */
				
				var _thueVAT = thueVAT.item(i).value.replace(/,/g,"");
					thuevat_chung=_thueVAT;
				if(  parseFloat(vat) > 0 && _thueVAT == '' )
				{
					thueVAT.item(i).value = vat;
					_thueVAT = vat;
				}
				else
				{
					if(_thueVAT == '')
						_thueVAT = '0';	
				}
					
				
				var tt = Math.floor(parseFloat(dongia.item(i).value.replace(/,/g,"")) * parseFloat(soluong.item(i).value.replace(/,/g,"")));
				tt = parseFloat(tt) * ( 1 +  parseFloat(_thueVAT) / 100 );
				thanhtien.item(i).value = DinhDangTien(tt);
				
				/* tongtien += tt;
				tongtienCK += parseFloat(_ck);
				tongtienVAT += parseFloat(tt) * ( parseFloat(_thueVAT) / 100 ); */
				
				tongtien += Math.floor( parseFloat(dongia.item(i).value.replace(/,/g,"")) * parseFloat(soluong.item(i).value.replace(/,/g,"")) );
				//tongtienCK += parseFloat( _ck );
				tongtienVAT += ( parseFloat(dongia.item(i).value.replace(/,/g,"")) * parseFloat(soluong.item(i).value.replace(/,/g,"")) ) * ( parseFloat(_thueVAT) / 100 );
			}		
		}
		
		tongtien = tongtien;
		tongtienCK = Math.round(tongtienCK);
		tongtienVAT = Math.round(tongtien*thuevat_chung/100.0);
		
		
		//document.getElementById("txtTongTL").value = DinhDangTien(totalTL);
		//document.getElementById("txtTongTT").value = DinhDangTien(totalTT);
		
		//document.getElementById("txtTongThung").value = DinhDangTien(totalTHG);
		//document.getElementById("txtTongLe").value = DinhDangTien(totalLe);
		
		var dhCK_diengiai = document.getElementsByName("dhCK_diengiai");
		var dhCK_giatri = document.getElementsByName("dhCK_giatri");
		var dhCK_loai = document.getElementsByName("dhCK_loai");
		
		var tongDhCK = 0;
		var tongtien_sau_hoahong = 0;
		tongtienCK += parseFloat(tongDhCK);
		
		var tongtienSAUCK = parseFloat(tongtien) - parseFloat(tongtienCK);
		
		document.getElementById("txtTongCK").value = DinhDangTien(tongtienCK);
		document.getElementById("txtBVAT").value = DinhDangTien(tongtien);
		document.getElementById("txtTongSauCK").value = DinhDangTien(tongtienSAUCK);
		
		document.getElementById("txtVAT").value = DinhDangTien(tongtienVAT);
		
		var tongtienkm =  document.getElementById("tongtienKMHidden").value;
		
		
		//var tongtienSAUVAT = parseFloat(tongtienSAUCK) + ( parseFloat(vat) * parseFloat(tongtienSAUCK) / 100 );
		var tongtienSAUVAT = parseFloat(tongtienSAUCK) +  parseFloat(tongtienVAT) -  parseFloat(tongtienkm)  ;
		document.getElementById("txtSauVAT").value = DinhDangTien(tongtienSAUVAT);
		
	 }
	
	 function CapNhatGia(e, stt)
	 { 
		 	//alert(e.value);
		 	
		 	var spMa = document.getElementsByName("spMa");
		 	var dongia = document.getElementsByName("dongia");
		 	var spSoluongtongoc = document.getElementsByName("soluongtongoc") ;
			var spSoluongton = document.getElementsByName("soluongton") ;
			var quycach = document.getElementsByName("spQuyDoi");
		 	//dongia.item(stt).value = DinhDangTien(e.value); 
		 
		    //DUNG AJAX LOAD LAI DON GIA NEU KO PHAI LA DON VI CHUAN
		 	var xmlhttp;
			if ( e.value == "" || spMa.item(stt).value == "" )
			{
				dongia.item(stt).value = "0";
				spSoluongton.item(stt).value="0";
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
				   var rse=xmlhttp.responseText.split("_");
				   //alert(xmlhttp.responseText);
				   console.log( rse[0]+' va   '+rse[1]);
				   console.log( spSoluongtongoc.item(stt).value);
					dongia.item(stt).value=DinhDangTien(rse[0]);
					if(rse[0]!='0.0' && rse[1]=='0.0')
						{
						spSoluongton.item(stt).value=DinhDangTien(roundNumber(spSoluongtongoc.item(stt).value.replace(/,/g,""),0));
						return;
						}
						
					if(rse[0]=='0.0' && rse[1]=='0.0')
						{spSoluongton.item(stt).value="0";
							return;
						}
					if(rse[0]!='0.0' && rse[1]!='0.0')
					{
						spSoluongton.item(stt).value=DinhDangTien(roundNumber(parseFloat(spSoluongtongoc.item(stt).value.replace(/,/g,"")) / parseFloat(rse[1]),0));
						console.log( parseFloat(spSoluongtongoc.item(stt).value));
						return;
					}
					
					
				   dongia.item(stt).value = DinhDangTien(xmlhttp.responseText);
			   }
			}
			
			var dvtMA = encodeURIComponent(e.value.replace(" ","+"));
			var spMA = encodeURIComponent(spMa.item(stt).value.replace(" ","+"));
			xmlhttp.open("GET","../../ErpDondathangDoitacSvl?spMa=" + spMA + "&dvt=" + dvtMA + "&locgiaQUYDOI=1",true);
			xmlhttp.send();
		 
	 }
	 
	 function save_donhang_va_khuyen_mai()
	 {	
		 document.getElementById("btnSaveKM").innerHTML = "<a href='#'><img src='../images/waiting.gif'  title='cho luu..' border='1' longdesc='cho luu..' style='border-style:outset'> Processing...</a>";
	 	 document.forms['hctmhForm'].action.value = 'save_donhang_va_khuyen_mai';
	     document.forms['hctmhForm'].submit();
	 }
	 
	 function saveform()
	 {	
		 document.getElementById("btnSave").innerHTML = "<a href='#'><img src='../images/waiting.gif'  title='cho luu..' border='1' longdesc='cho luu..' style='border-style:outset'> Processing...</a>";
	 	 document.forms['hctmhForm'].action.value = 'save';
	     document.forms['hctmhForm'].submit();
	 }
	 
	/*  function chotform()
	 {	
		 document.getElementById("btnchot").innerHTML = "<a href='#'><img src='../images/waiting.gif'  title='cho chot..' border='1' longdesc='cho chot..' style='border-style:outset'> Processing...</a>";
	 	 document.forms['hctmhForm'].action.value = 'chot';
	     document.forms['hctmhForm'].submit();
	 } */
	 
	 function submitform()
	 { 
		 document.forms['hctmhForm'].action.value='submit';
	     document.forms["hctmhForm"].submit();
	 }
		function myFunction() {
			  var x = document.getElementById("myDIV");
			  if (x.style.display === "none") {
			    x.style.display = "block";
			  } else {
			    x.style.display = "none";
			  }
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
						alert("Vui l??ng ch???n tr??? khuy???n m??i cho ch????ng tr??nh khuy???n m??i ???????c ch???n.");
						document.getElementById("Show_All").checked = true;
						return;
					}
	  			}
	  		}	
			
		   	document.forms['hctmhForm'].action.value='dieuchinhkm';
		   	document.forms['hctmhForm'].submit();
		}
	 function load()
	 { 
		 document.forms['hctmhForm'].action.value='load';
	     document.forms["hctmhForm"].submit();
	 }
	 function Apkhuyenmai()
	 {
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
	
	function TinhLaiDonGia()
	 {

			var spHansd = document.getElementsByName("spHansd");
			var spMa = document.getElementsByName("spMa");
			var spTen = document.getElementsByName("spTen");  
			var donvi = document.getElementsByName("donvi");
			var soluong = document.getElementsByName("soluong");
			var dongia = document.getElementsByName("dongia");
			var thanhtien = document.getElementsByName("thanhtien");
			var soluongton = document.getElementsByName("soluongton");
			var spSoluongtongoc = document.getElementsByName("soluongtongoc") ; 
			var trongluong = document.getElementsByName("spTrongLuong");
			var thetich = document.getElementsByName("spTheTich");
			var spQuyDoi = document.getElementsByName("spQuyDoi");
			
			var spvat = document.getElementsByName("spvat");
			
			var ptchietkhau = document.getElementById("txtPTChietKhau").value;
			if(ptchietkhau == '')
				ptchietkhau = 0;
			
			var dongiagoc = document.getElementsByName("dongiagoc");
			var i;
			for(i = 0; i < spMa.length; i++)
			{
				if(spMa.item(i).value != "")
				{

					if( parseFloat(ptchietkhau) >= 0  )
						{
							dongia.item(i).value = parseFloat(dongiagoc.item(i).value) * ( 1 - parseFloat(ptchietkhau) / 100 );

							var sole = '';
							if(dongia.item(i).value.indexOf(".") >= 0)
							{
								sole = dongia.item(i).value.substring(dongia.item(i).value.indexOf('.'), parseInt(dongia.item(i).value.indexOf('.')) + 5 );
							}
							var dg = dongia.item(i).value.split(".");
							dongia.item(i).value = dg[0] + sole;
							
						}
					
				}
				else
				{
					spMa.item(i).value = "";
					spTen.item(i).value = "";
					donvi.item(i).value = "";
					soluong.item(i).value = "";
					soluongton.item(i).value = "";
					spSoluongtongoc.item(i).value = "";
					dongia.item(i).value = "";
					thanhtien.item(i).value = "";	
					trongluong.item(i).value = "";	
					thetich.item(i).value = "";	
					spQuyDoi.item(i).value = "";
					spvat.item(i).value = "";
				}
			}	
			
			TinhTien();
	 }
	 
</script>

<link href="../css/select2.css" rel="stylesheet" />
<script src="../scripts/select2.js"></script>
<script>
	$(document).ready(function()
	{
		$(".select2").select2();
		
	});
</script>
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
<form name="hctmhForm" method="post" action="../../ErpDondathangDoitacUpdateSvl">
		<% redis.clients.jedis.Jedis jedis =  geso.dms.center.util.Utility.getJedis(); %><% geso.dms.center.util.Csrf csdr=new geso.dms.center.util.Csrf(request,response,true,false,true);%>
 <input type="hidden" name="<%=csdr.get_tokenName() %>" value='<%=csdr.get_tokenValue() %>'>
<input type="hidden" name="userId" value='<%= userId %>'>
<input type="hidden" name="action" value='1'>
<input type="hidden" name="loaidonhang" value='<%= lsxBean.getLoaidonhang() %>'>
<input type="hidden" name="nppId" value='<%= lsxBean.getNppId() %>'>
<input type="hidden" name="danh_sach_ctkm_Str" value='<%= lsxBean.getDanh_sach_ctkm_Str() %>'>
<input type="hidden" name="dieuchinh" value='<%= lsxBean.getDieuchinh() %>'>

<div id="main" style="width:100%; padding-left:2px">
	<div align="left" id="header" style="width:100%; float:none">
    	<div style="width:55%; padding:5px; float:left" class="tbnavigation">
        	&nbsp;Qu???n l?? b??n h??ng > B??n h??ng cho ?????i t??c > T???o m???i
        </div>
        <div align="right" style="padding:5px" class="tbnavigation">
        	<%=Utility.GLanguage("Ch??o m???ng b???n",session,jedis) %>  <%= lsxBean.getNppTen() %> &nbsp;
        </div>
    </div>
  
  	<div align="left" id="button" style="width:100%; height:32px; padding:0px; float:none" class="tbdarkrow">
    	<A href= "../../RouterSvl?task=<%= Utilback.dongMa(getServletContext().getInitParameter("RedirectNoScript") +"ErpDondathangDoitacSvl?userId="+ userId +"&loaidonhang="+ lsxBean.getLoaidonhang()+"") %>" >
        	<img src="../images/Back30.png" alt="Quay ve"  title="Quay ve" border="1px" longdesc="Quay ve" style="border-style:outset"></A>
       <!--  <span id="btnSave">
	        <A href="javascript:saveform()" >
	        	<IMG src="../images/Save30.png" title="Luu lai" alt="Luu lai" border ="1px" style="border-style:outset"></A>
        </span> -->
        <!-- 
        <span id="btnchot">
	        <A href="javascript:chotform()" >
	        	<IMG src="../images/Chot.png" title="Luu lai" alt="chot" border ="1px" style="border-style:outset"></A>
        </span> -->
        
         <span id="btnin">
	        <A href= "../../RouterSvl?task=<%= Utilback.dongMa(getServletContext().getInitParameter("RedirectNoScript") +"InBangKeETCpdfSvl?userId="+ userId+"&display="+lsxBean.getId()+"")%>"><img
			src="../images/Printer30.png" alt="In"
			title="In chung tu" longdesc="In chung tu" border=1
			style="border-style: outset">
			</A>
        </span>
    </div>
  	
  	<div align="left" style="width:100%; float:none; clear:left">
  		<fieldset>
    		<legend class="legendtitle"> Th??ng b??o</legend>
    		<textarea name="dataerror"  rows="1" readonly="readonly" style ="width:100%"><%= lsxBean.getMsg() %></textarea>
		         <% lsxBean.setMsg(""); %>
    	</fieldset>
  	</div>
  	
    <div align="left" style="width:100%; float:none; clear:left">
    <fieldset>
    	<legend class="legendtitle">B??n h??ng cho ?????i t??c </legend>
        	<div style="float:none; width:100%" align="left">
            
            <TABLE width="100%" cellpadding="4" cellspacing="0">							
                <TR>
                    <TD width="200px" class="plainlabel" >Ng??y ????n h??ng </TD>
                    <TD width="300px"  class="plainlabel"  >
                    	<input type="text" class="days" readonly="readonly"  name="ngaychuyen" value="<%= lsxBean.getNgayyeucau() %>"/>
                    </TD>
                    
                    <TD width="200px" class="plainlabel" >Ng??y ????? ngh??? giao </TD>
                    <TD class="plainlabel" >
                    	<input type="text" class="days" readonly="readonly"  name="ngaydenghi" value="<%= lsxBean.getNgaydenghi() %>"/>
                    </TD>
                </TR>
                
                <TR>
                	<TD class="plainlabel" >????n v??? kinh doanh </TD>
                    <TD class="plainlabel"   >
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
                    <TD class="plainlabel" >K??nh b??n h??ng </TD>
                    <TD class="plainlabel"    >
                    	<select name = "kbhId" class="select2" style="width: 200px" onchange="submitform();" >
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
                	<TD class="plainlabel" >?????i t??c </TD>
                    <TD class="plainlabel"   >
                    	<select name='doitacId' class="select2" style="width: 200px" onchange="submitform();"  >
                    		<option value=""> </option>
                        	<%
                        		if(nppRs != null)
                        		{
                        			try
                        			{
                        			while(nppRs.next())
                        			{  
                        			if( nppRs.getString("pk_seq").equals(lsxBean.getKhId())){ %>
                        				<option value="<%= nppRs.getString("pk_seq") %>" selected="selected" ><%= nppRs.getString("ten") %></option>
                        			<%}else { %>
                        				<option value="<%= nppRs.getString("pk_seq") %>" ><%= nppRs.getString("ten") %></option>
                        		 <% } } nppRs.close();} catch(SQLException ex){}
                        		}
                        	%>
                    	</select>
                    </TD>   
                    <TD class="plainlabel" >Kho ?????t </TD>
                    <TD class="plainlabel"   >
                    	<select name = "khonhapId" onchange="submitform();"class="select2" style="width: 200px" >
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
                    
                   
                    <TD class="plainlabel" >T???ng gi?? tr??? </TD>
                    <TD class="plainlabel" >
                    	<input type="text" readonly="readonly"  id="txtBVAT" value="" style="text-align: right;"  />
                    	
                    </TD>
                    
                    <TD class="plainlabel" style="display: none;" >PT chi???t kh???u </TD>
                    <TD class="plainlabel" style="display: none;">
                    	<input type="text"  value="<%= lsxBean.getChietkhau() %>" id="txtPTChietKhau" style="text-align: right;" name="ptChietkhau" onkeypress="return keypress(event);" onchange="TinhLaiDonGia();"  />
                     </TD>	
                    	
                     <TD class="plainlabel" > </TD>
                    	<TD class="plainlabel" ></TD>	
                    	
                </TR>
                
                <TR  style="display: none">
                    <TD class="plainlabel" >T???ng ti???n chi???t kh???u </TD>
                    <TD class="plainlabel"  >
                    	<input type="text" readonly="readonly"  value="" id="txtTongCK" style="text-align: right;" /></TD>
                    	
                    <TD class="plainlabel" >T???ng ti???n sau CK </TD>
                    <TD class="plainlabel" >
                    	<input type="text" readonly="readonly"  value="" id="txtTongSauCK" style="text-align: right;" />
                    </TD>
                </TR>
                
                <TR>
                    <TD class="plainlabel" >T???ng ti???n VAT </TD>
                    <TD class="plainlabel"  >
                    	<input type="text" value="<%= lsxBean.getVat() %>" id="txtVAT" style="text-align: right;" name="ptVat" onkeypress="return keypress(event);" onkeyup="Dinhdang(this);" readonly='readonly' />
                    </TD>
                    	
                    <TD class="plainlabel" >T???ng ti???n sau VAT </TD>
                    <TD class="plainlabel" colspan="3">
                    	<input type="text" readonly="readonly"  value="" id="txtSauVAT" style="text-align: right;" />
                    </TD>
                </TR>
                
                <TR>
                	 <TD class="plainlabel" valign="top">T???ng ti???n</TD>
                    <TD class="plainlabel" valign="top">
                    	<input type="text" readonly="readonly"  value="" id="txtTongGiaTri" style="text-align: right;" />
                    </TD>
                    
                    <TD class="plainlabel" >Ghi ch?? </TD>
                    <TD class="plainlabel">
                    	<textarea rows="3" cols="1"  name="ghichu"  >
                    	<%= lsxBean.getGhichu() %>
                    	</textarea>
                    </TD>
                    
                    <!-- <td class="plainlabel"></td> -->
                    
                    <%--  <TD  class="plainlabel"   > ????n h??ng KM c?? H??TC </td>
                    <TD class="plainlabel" >
                           <input type="checkbox"   name="iskm"  id="iskm" readonly="readonly" <%=lsxBean.getIsKm().equals("1")?"checked=\"checked\"":"" %>   value="1" style="text-align: right;"  />
                      </TD> --%>
                    
              </TR>
                   <TR>
                      <TD class="plainlabel" >????n h??ng kh??c, KM </TD>
                    <TD class="plainlabel" colspan="3">
                    	    <input type="checkbox"  disabled  name="isdhk"  id="isdhk" onchange="submitform()" <%=lsxBean.getIsdhk().equals("1")?"checked=\"checked\"":"" %>   value="1" style="text-align: right;"  />
                     	 	<input  type="hidden"  name="isdhk"  id="isdhk"    value="<%= lsxBean.getIsdhk() %>"   />
                    </TD>
                     <%--  
                     <TD class="plainlabel"   > C?? in gi?? </td>
                    <TD class="plainlabel" >
                           <input type="checkbox"   name="isgia"  id="isgia"  <%=lsxBean.getIsgia().equals("1")?"checked=\"checked\"":"" %>   value="1" style="text-align: right;"  />
                      </TD>
                     --%>
                	</TR>
                <%if (! lsxBean.getIsdhk().equals("1") ){ %>
                <TR>
				   <TD class="plainlabel" colspan = '4'>
				   <div id="btnApKhuyenMai">
				  		<a style="height: 26px;" class="button2" href="javascript:Apkhuyenmai()">
							<img style="top: -4px;" src="../images/New.png" alt="">??p khuy???n m???i v?? l??u </a>
					</div>									
				  </TD>
				</TR>
				<%} else { %>
               
                <TR>
				   <TD class="plainlabel" colspan = '4'>
				   <div id="btnSave">
				  		<a style="height: 26px;" class="button2" href="javascript:saveform()">
							<img style="top: -4px;" src="../images/New.png" alt="">L??u</a>
					</div>									
				  </TD>
				</TR>
               <%} %>
            </TABLE>
			
			<hr />
			
				<table cellpadding="0px" cellspacing="1px" width="100%">
					<tr class="tbheader">
						<th align="center" width="3%" >STT</th>
						<th align="center" width="10%" >M?? s???n ph???m</th>
						<th align="center" width="22%" >T??n s???n ph???m</th>
						<th align="center" width="10%" >????n v???</th>
						<th align="center" width="8%" >S??? l?????ng</th>
						<th align="center" width="8%" >S??? l?????ng t???n</th>	
						<th style="display:none;" >S??? l?????ng t???n</th>
						<th align="center" width="5%" >T???n CN</th>
						<th align="center" width="5%" >VAT</th>
						<th align="center" width="8%" >????n gi??</th>
						<th align="center" width="8%" style="display: none;" >Chi???t kh???u</th>
						<th align="center" width="10%" >Th??nh ti???n</th>
						<th align="center" width="10%" >CTKM</th>
					</tr>
					
					<%
						int count = 0;
						if(spMa != null)
						{
							for(int i = 0; i < spMa.length; i++)
							{  %>
						
							<tr>
								<td style="text-align: center;" > <%= i + 1 %> </td>
								<td>
									<input type="text" name="spMa" value="<%= spMa[i] %>" style="width: 100%"  onkeyup="ajax_showOptions(this,'nhapkho',event)" AUTOCOMPLETE="off"  > 
									<input type="hidden" name="spTrongLuong" value="0" > 
									<input type="hidden" name="spTheTich" value="0" > 
									<input type="hidden" name="spQuyDoi" value="0"  >
								</td>
								<td> <input type="text" name="spTen" value="<%= spTen[i] %>" style="width: 100%" readonly="readonly"> </td>
								<td>
								
									 <input type="text" name="donvi" value="<%=spDonvi[i] %>" style="width: 100%" readonly="readonly">
									<%-- <select  name="donvi" style="width: 100%" onchange="CapNhatGia(this, <%= count %>);" >
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
									 </select>  --%>
								</td>
								
								<td> 
									<input type="text" name="soluong" value="<%= spSoluong[i] %>" style="width: 100%; text-align: right;" onkeypress="return keypress(event);" onkeyup="Dinhdang(this);" > 
								</td>
								<td> <input type="text" name="soluongton" value="<%=spSoluonton[i] %>" style="width: 100%; text-align: right;" onkeypress="return keypress(event);" readonly="readonly"  > </td>
								 <td style="display:none;"><input type="hidden" name="soluongtongoc" value="<%=spSoluonton[i] %>" style="width: 100%; text-align: right;" onkeypress="return keypress(event);" onkeyup="Dinhdang(this);" ></td>
										<td> <input type="text" name="soluongtoncn" value="<%=spToncn[i] %>" style="width: 100%; text-align: right;" onkeypress="return keypress(event);" readonly="readonly"  > </td>
				
								<td> <input type="text" name="spvat" value="<%= spVat[i] %>" style="width: 100%; text-align: right;" onkeypress="return keypress(event);" onkeyup="Dinhdang(this);" readonly="readonly" > </td>
								
								<td> 
									<input type="text" name="dongia" value="<%= spGianhap[i] %>" style="width: 100%; text-align: right;" onkeypress="return keypress(event);"  <%if(!lsxBean.getIsdhk().equals("1")){ %>readonly="readonly"<%} %>> 
									<input type="hidden" name="dongiagoc" value="<%= spGiagoc[i] %>" style="width: 100%; text-align: right;" onkeypress="return keypress(event);" <%if(!lsxBean.getIsdhk().equals("1")){ %>readonly="readonly"<%} %> >
								</td>

								<td style="display: none;"> <input type="text" name="chietkhau" value="<%= spChietkhau[i] %>" style="width: 100%; text-align: right;" onkeypress="return keypress(event);" onkeyup="Dinhdang(this);" > </td>
								
								<td> <input type="text" name="thanhtien" value="" style="width: 100%; text-align: right;" readonly="readonly" > </td>
								<td> <input type="text" name="scheme" value="" style="width: 100%; text-align: right;" readonly="readonly" > </td>
							</tr>		
								
						<% count ++; } } %>
						
						<% 	
						double tongtienKM = 0;
						ResultSet kmRs = lsxBean.getKhuyenMaiRs();
							if(kmRs != null)
								while(kmRs.next())
								{
									tongtienKM+= kmRs.getDouble("TONGGIATRI");
								
								%>
									<tr>
										<td style="text-align: center;" ></td>
										<td>
											<input type="text"  value="<%= kmRs.getString("MA") %>" style="width: 100%; color: red" readonly  > 
										</td>
										<td>
											<input type="text"  value="<%= kmRs.getString("TEN") %>" style="width: 100%; color: red" readonly  > 
										</td>
										<td>
											<input type="text"  value="<%= kmRs.getString("donvi") %>" style="width: 100%; color: red" readonly  > 
										</td>
										<td>
											<input type="text"  value="<%= kmRs.getString("SOLUONG") %>" style="width: 100%;text-align: right; color: red" readonly  > 
										</td>
										<td>
											<input type="text"  value="" style="width: 100%; color: red" readonly> 
										</td>
										<td>
											<input type="text"  value="" style="width: 100%; color: red" readonly> 
										</td>
										<td>
											<input type="text"  value="" style="width: 100%; color: red" readonly> 
										</td>
										<td>
											<input type="text"  value="" style="width: 100%; color: red" readonly> 
										</td>
										<td>
											<input type="text"  value="<%= kmRs.getString("TONGGIATRI") %>" style="width: 100%; color: red;text-align: right" readonly  > 
										</td>
										<td>
											<input type="text"  value="<%= kmRs.getString("scheme") %>" style="width: 100%; color: red;text-align: left" readonly  > 
										</td>
									</tr>
						<% }
							
						
						%>
						<input type="hidden" id ="tongtienKMHidden"  value="<%= tongtienKM %>" style="width: 100%; color: red" readonly  >
					
					<% for(int i = count; i < 50; i++) { %>
						
						<tr>
							<td style="text-align: center;" > <%= i + 1 %> </td>
							<td>
								<input type="text" name="spMa" value="" style="width: 100%"  onkeyup="ajax_showOptions(this,'nhapkho',event)" AUTOCOMPLETE="off"  > 
								<input type="hidden" name="spTrongLuong" value="" > 
								<input type="hidden" name="spTheTich" value="" > 
								<input type="hidden" name="spQuyDoi" value=""  >
							</td>
							<td> <input type="text" name="spTen" value="" style="width: 100%" readonly="readonly"> </td>
							<td>
							
								<input type="text" name="donvi" value="" style="width: 100%" readonly="readonly">
								<%-- <select name="donvi" style="width: 100%" onchange="CapNhatGia(this, <%= i %>);" >
									<option value="" ></option>
										<% if(dvtRs != null) 
										{ 
											dvtRs.beforeFirst();
											while(dvtRs.next())
											{ %>
												<option value="<%= dvtRs.getString("DONVI") %>"  ><%= dvtRs.getString("DONVI") %></option>
										   <% } 
										} %>
									 </select>  --%>
							 </td>
							
							<td> <input type="text" name="soluong" value="" style="width: 100%; text-align: right;" onkeypress="return keypress(event);" onkeyup="Dinhdang(this);" > </td>
								<td> <input type="text" name="soluongton" value="" style="width: 100%; text-align: right;" onkeypress="return keypress(event);" onkeyup="Dinhdang(this);" readonly="readonly" > </td>
							 <Td style="display:none;"><input type="hidden" name="soluongtongoc" value="0" style="width: 100%; text-align: right;" onkeypress="return keypress(event);" onkeyup="Dinhdang(this);" ></td>
							<td> <input type="text" name="soluongtoncn" value="" style="width: 100%; text-align: right;" onkeypress="return keypress(event);" onkeyup="Dinhdang(this);" readonly="readonly" > </td>
						
							<td> <input type="text" name="spvat" value="" style="width: 100%; text-align: right;" readonly="readonly" > </td>
							
							<td> 
								<input type="text" name="dongia" value="" style="width: 100%; text-align: right;" onkeypress="return keypress(event);"  <%if(!lsxBean.getIsdhk().equals("1")){ %>readonly="readonly"<%} %>> 
								<input type="hidden" name="dongiagoc" value="" style="width: 100%; text-align: right;" onkeypress="return keypress(event);" >
							</td>
							<td style="display: none;"> <input type="text" name="chietkhau" value="" style="width: 100%; text-align: right;" onkeypress="return keypress(event);" onkeyup="Dinhdang(this);" <%if(!lsxBean.getIsdhk().equals("1")){ %>readonly="readonly"<%} %> > </td>
							
							<td> <input type="text" name="thanhtien" value="" style="width: 100%; text-align: right;" readonly="readonly" > </td>
							<td> <input type="text" name="scheme" value="" style="width: 100%; text-align: right;" readonly="readonly" > </td>
						</tr>		
						
					<% } %>	
				</table>
     		</div>
     </fieldset>	
    </div>
</div>
<% if(lsxBean.getData().trim().length() > 0 )
  			   { 
  			    request.setCharacterEncoding("utf-8");  
  			%> 	
  			 	<jsp:include page="ErpKhuyenMaiDonDatHangNpp.jsp" flush="true">
    			<jsp:param name="json_data" value="<%= lsxBean.getData()%>"/>
    			<jsp:param name="json_dieuchinh" value="<%= lsxBean.getDieuchinh()%>"/>
				</jsp:include>
  		 	<% } %>
<script type="text/javascript">
	replaces();
</script>

<%
	try
	{
		dvtRs.close();
		lsxBean.DBclose(); 
	}
	catch(Exception er){ }

	} %>
</form>

<script type="text/javascript" src="../scripts/Sortable/jquery.min.js"></script>
<script type="text/javascript" src="../scripts/Sortable/jquery-ui.min.js"></script>
 
<script>
  $(document).ready(function() {
	$("#sortable").sortable();
  });
</script> 

<script>
// Get the modal
var modal = document.getElementById("myModal");
// When the user clicks anywhere outside of the modal, close it
window.onclick = function(event) {
  if (event.target == modal) {
    modal.style.display = "none";
  }
}
</script>
</BODY>
</HTML>