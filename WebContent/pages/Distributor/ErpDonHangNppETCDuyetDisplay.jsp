<%@page import="geso.dms.center.util.Utility"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@page import="java.util.ArrayList"%>
<%@page import="java.sql.SQLException"%>
<%@ page  import = "geso.dms.distributor.beans.hopdong.*" %>
<%@ page  import = "geso.dms.distributor.beans.hopdong.imp.*" %>
<%@ page  import = "java.sql.ResultSet" %>
<%@ page  import = "java.util.Hashtable" %>
<%@ page  import = "java.util.List" %>
<%@page import="java.text.NumberFormat"%>
<%@page import="java.text.DecimalFormat"%>

<% IErpDonhangNppETC lsxBean = (IErpDonhangNppETC)session.getAttribute("lsxBean"); %>
<% ResultSet dvkdRs = lsxBean.getDvkdRs(); %>
<% ResultSet kbhRs = lsxBean.getKbhRs(); %>
<% ResultSet nppRs = lsxBean.getKhRs(); %>
<% ResultSet khonhapRs = lsxBean.getKhoNhapRs(); %>
<% ResultSet dvtRs = lsxBean.getDvtRs(); %>
<% ResultSet gsbhRs = lsxBean.getGsbhRs(); %>
<% ResultSet ddkdRs = lsxBean.getDdkdRs(); %>

<% 
	String[] spMa = lsxBean.getSpMa(); 
	String[] spTen = lsxBean.getSpTen();
	String[] spDonvi = lsxBean.getSpDonvi();
	String[] spSoluong = lsxBean.getSpSoluong();
	String[] spGianhap = lsxBean.getSpGianhap();
	String[] spChietkhau = lsxBean.getSpChietkhau();
	String[] spVat = lsxBean.getSpVat();
	String[] spTungay = lsxBean.getSpTungay();
	String[] spDenngay = lsxBean.getSpDenngay();
	String[] spTrongluong = lsxBean.getSpTrongluong();
	String[] spThetich = lsxBean.getSpThetich();
	String[] spQuyDoi = lsxBean.getSpQuyDoi();
	
	
	String[] dhCk_diengiai = lsxBean.getDhck_diengiai();
	String[] dhCk_giatri = lsxBean.getDhck_giatri();
	String[] dhCk_loai = lsxBean.getDhck_loai();
	
	NumberFormat formater = new DecimalFormat("##,###,###");
	NumberFormat formater2 = new DecimalFormat("##,###,###.##");
	Hashtable<String, String> sanpham_soluong = lsxBean.getSanpham_Soluong();
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
<link type="text/css" rel="stylesheet" href="../css/mybutton.css">
<LINK rel="stylesheet" type="text/css" href="../css/style.css" />
<script type="text/javascript" src="../scripts/dropdowncontent2.js"></script>


<style type="text/css">
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

<script type="text/javascript" src="../scripts/jquery.min.1.7.js"></script>
<script type="text/javascript" language="JavaScript" src="../scripts/jquery.tools.min.js"></script>

<link href="../css/jquery-ui.css" rel="stylesheet" type="text/css" />
<script src="../scripts/ui/jquery.ui.core.js" type="text/javascript"></script>
<script src="../scripts/ui/jquery.ui.widget.js" type="text/javascript"></script>
<script src="../scripts/ui/jquery.ui.datepicker.js" type="text/javascript"></script>
<script type="text/javascript">
	$(document).ready(function() {		
		$( ".days" ).datepicker({			    
				changeMonth: true,
				changeYear: true				
		});            
	});	
</script>

<script type="text/javascript" src="../scripts/ajax.js"></script>
<script type="text/javascript" src="../scripts/erp-SpListDonDatHang.js"></script>

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
	
	
	
	function ngayvaolam2()
	{
	  if(document.getElementById("ngayvaolam").value != "")
	  {  
	   var now = new Date();
	   var month = now.getMonth() + 1;
	   var date = now.getDate();
	   if(month < 10)
	   {month = "0"+month;} 
	   if(date < 10)
	   {date = "0"+date;}
	   var ngayvaolam = Date.parse(document.getElementById("ngayvaolam").value);
	   var ngayhientai = Date.parse(now.getFullYear()+"-"+month+"-"+date);   
	   if(ngayvaolam < ngayhientai)
	   {
	    
	    alert('B???n ph???i ch???n ng??y v??o l??m l???n h??n ho???c b???ng ng??y hi???n t???i !');
	    document.getElementById("ngayvaolam").value = "";
	    return;
	   } 
	 }
	  document.forms["ddkdForm"].submit();
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
		
		var trongluong = document.getElementsByName("spTrongLuong");
		var thetich = document.getElementsByName("spTheTich");
		var spQuyDoi = document.getElementsByName("spQuyDoi");
		
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
					
					spvat.item(i).value = sp.substring(0, parseInt(sp.indexOf("]")));
					
					//trongluong.item(i).value = sp.substring(0, parseInt(sp.indexOf("] [")));
					//sp = sp.substr(parseInt(sp.indexOf("] [")) + 3);
					
					//thetich.item(i).value = sp.substring(0, parseInt(sp.indexOf("] [")));
					//sp = sp.substr(parseInt(sp.indexOf("] [")) + 3);
					
					//spQuyDoi.item(i).value = sp.substring(0, parseInt(sp.indexOf("] [")));
					//sp = sp.substr(parseInt(sp.indexOf("] [")) + 3);
					
					//dongia.item(i).value = DinhDangTien(sp.substring(0, parseInt(sp.indexOf("]")))); 
					dongia.item(i).value = '';
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
		
		for(var i = 0; i < spMa.length; i++)
		{
			if(spMa.item(i).value != "" && dongia.item(i).value != "" && soluong.item(i).value != "" )
			{
				var _ck = chietkhau.item(i).value.replace(/,/g,"");
				if(_ck == '')
					_ck = '0';
				
				var _thueVAT = thueVAT.item(i).value.replace(/,/g,"");
				
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
					
				
				var tt = parseFloat(dongia.item(i).value.replace(/,/g,"")) * parseFloat(soluong.item(i).value.replace(/,/g,"")) - parseFloat(_ck);
				tt = parseFloat(tt) * ( 1 +  parseFloat(_thueVAT) / 100 );
				thanhtien.item(i).value = DinhDangTien(tt);
				
				/* tongtien += tt;
				tongtienCK += parseFloat(_ck);
				tongtienVAT += parseFloat(tt) * ( parseFloat(_thueVAT) / 100 ); */
				
				tongtien += ( parseFloat(dongia.item(i).value.replace(/,/g,"")) * parseFloat(soluong.item(i).value.replace(/,/g,"")) );
				tongtienCK += parseFloat( _ck );
				tongtienVAT += ( parseFloat(dongia.item(i).value.replace(/,/g,"")) * parseFloat(soluong.item(i).value.replace(/,/g,"")) - parseFloat(_ck) ) * ( parseFloat(_thueVAT) / 100 );
			}		
		}
		
		tongtien = Math.round(tongtien);
		tongtienCK = Math.round(tongtienCK);
		tongtienVAT = Math.round(tongtienVAT);
		
		//document.getElementById("txtTongTL").value = DinhDangTien(totalTL);
		//document.getElementById("txtTongTT").value = DinhDangTien(totalTT);
		
		//document.getElementById("txtTongThung").value = DinhDangTien(totalTHG);
		//document.getElementById("txtTongLe").value = DinhDangTien(totalLe);
		
		var dhCK_diengiai = document.getElementsByName("dhCK_diengiai");
		var dhCK_giatri = document.getElementsByName("dhCK_giatri");
		var dhCK_loai = document.getElementsByName("dhCK_loai");
		
		var tongDhCK = 0;
		var tongtien_sau_hoahong = 0;
		
		for(var j = 0; j < dhCK_giatri.length; j++ )
		{
			if(dhCK_giatri.item(j).value != '' )
			{
				var loai = dhCK_loai.item(j).value;
				if(loai == '0') //tien
					tongDhCK += parseFloat(dhCK_giatri.item(j).value.replace(/,/g,""));
				else  //CHIET KHAU
				{
					/* if( j == 0)
					{
						var tt_hoahong = parseFloat(dhCK_giatri.item(j).value.replace(/,/g,"")) * parseFloat(tongtien) / 100;
						tongtien_sau_hoahong = tongtien - tt_hoahong;
						tongDhCK += parseFloat(tt_hoahong);
					}
					else
					{ */
						tongDhCK += parseFloat(dhCK_giatri.item(j).value.replace(/,/g,"")) * parseFloat(tongtien_sau_hoahong) / 100;
					//}
					
				}
			}
		}
		
		tongtienCK += parseFloat(tongDhCK);
		
		var tongtienSAUCK = parseFloat(tongtien) - parseFloat(tongtienCK);
		
		document.getElementById("txtTongCK").value = DinhDangTien(tongtienCK);
		document.getElementById("txtBVAT").value = DinhDangTien(tongtien);
		document.getElementById("txtTongSauCK").value = DinhDangTien(tongtienSAUCK);
		
		document.getElementById("txtVAT").value = DinhDangTien(tongtienVAT);
		
		//var tongtienSAUVAT = parseFloat(tongtienSAUCK) + ( parseFloat(vat) * parseFloat(tongtienSAUCK) / 100 );
		var tongtienSAUVAT = parseFloat(tongtienSAUCK) + ( parseFloat(tongtienVAT) );
		document.getElementById("txtSauVAT").value = DinhDangTien(tongtienSAUVAT);
		
	 }
	
	 
	 function dateDiff(date1,date2) {
		   var dtFrom =date1;
		   var dtTo = date2;
		 
		   console.log ("ngay 11 "+ dtFrom);
		   console.log ("ngay 21 "+ dtTo);
		   var dt1 = new Date(dtFrom);
		   var dt2 = new Date(dtTo);
		   console.log ("ngay 1"+ dt1);
		   console.log ("ngay 2"+ dt2);
		   var diff = dt2.getTime() - dt1.getTime();
		   var days = diff/(1000 * 60 * 60 * 24);
		   return days;
		}
	 
	 function chotform()
	 {	
		 var msg="";
		 var spma=document.getElementsByName("spMa");
		 var hopdong_tungay=document.getElementById("hopdong_tungay");
		 for(var i=0;i<spma.length;i++)
		 {
		 	console.log (spma.item(i).value);
		 	var ngayhethan= document.getElementsByName(spma.item(i).value+"_spNGAYHETHAN");
		 	var soluong= document.getElementsByName(spma.item(i).value+"_spSOLUONG");
		 	 for(var j=0;j<ngayhethan.length;j++)
	 		 {
		 		 if(soluong.item(j).value!='0' && soluong.item(j).value!='' )
		 			{
		 				if(dateDiff(hopdong_tungay.value,ngayhethan.item(j).value)<= 12*31 )
	 					{
	 						msg+=spma.item(i).value+" t???n t???i l?? c?? ng??y h???t h???n <= 12 th??ng \n";	
	 					}
		 				
		 			}
	 		 }
		 }
		 if(msg!="")
		 {
			 if(!confirm(msg + " b???n c?? mu???n ti???p t???c kh??ng "))
				{
					
				}
				 else
				{
					 document.getElementById("btnSave").innerHTML = "<a href='#'><img src='../images/waiting.gif'  title='cho luu..' border='1' longdesc='cho luu..' style='border-style:outset'> Processing...</a>";
				 	 document.forms['hctmhForm'].action.value = 'duyet';
				     document.forms['hctmhForm'].submit();
				 }
		 }
		 else
		 {
			 document.getElementById("btnSave").innerHTML = "<a href='#'><img src='../images/waiting.gif'  title='cho luu..' border='1' longdesc='cho luu..' style='border-style:outset'> Processing...</a>";
		 	 document.forms['hctmhForm'].action.value = 'duyet';
		     document.forms['hctmhForm'].submit();
		 }
		
		 
		 
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
	
	 function submitform()
	 {
	 	document.forms['hctmhForm'].action.value= 'disp';
	 	document.forms['hctmhForm'].submit();
	 } 
	
	function CheckSoLuong_DeXuat(element)
	{
		element.value = DinhDangTien(element.value);
		if(element.value== '' )
		{
			element.value= '';
		}
		
		Update_SoLuong( element );
	}	
	
	function Update_SoLuong( element )
	{
		var spMa = document.getElementsByName("spMa");
		var soluong = document.getElementsByName("soluong");
		var soluong2 = document.getElementsByName("soluong2");
		
		for(var i = 0; i < spMa.length; i++ )
		{
			var soluongDEXUAT = document.getElementsByName(spMa.item(i).value + "_spSOLUONG");
			
			var totalXUAT = 0;
			for(var j = 0; j < soluongDEXUAT.length; j++ )
			{
				totalXUAT = parseFloat(totalXUAT) + parseFloat(soluongDEXUAT.item(j).value.replace(/,/g,""));
			}
			
			//alert(totalXUAT);
			
			if( totalXUAT > parseFloat(soluong.item(i).value.replace(/,/g,"")) )
			{
				soluong2.item(i).value = soluong.item(i).value;
				element.value = '0';
				
				alert('S??? l?????ng xu???t ( ' + totalXUAT + ' ) kh??ng ???????c ph??p v?????t qu?? s??? l?????ng ?????t ( ' + soluong.item(i).value + ' )');
			}

			soluong2.item(i).value = soluong.item(i).value;
		}
		
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
<form name="hctmhForm" method="post" action="../../ErpDonhangNppETCUpdateSvl">

		<% redis.clients.jedis.Jedis jedis =  geso.dms.center.util.Utility.getJedis(); %><% geso.dms.center.util.Csrf csdr=new geso.dms.center.util.Csrf(request,response,true,false,true);%>
 <input type="hidden" name="<%=csdr.get_tokenName() %>" value='<%=csdr.get_tokenValue() %>'>
<input type="hidden" name="userId" value='<%= userId %>'>
<input type="hidden" name="action" value='1'>
<input type="hidden" name="loaidonhang" value='<%= lsxBean.getLoaidonhang() %>'>
<input type="hidden" name="id" value='<%= lsxBean.getId() %>'>
<input type="hidden" name="nppId" value='<%= lsxBean.getNppId() %>'>
<input type="hidden" name="dungchungkenh" value='<%= lsxBean.getDungchungKenh() %>'>

<div id="main" style="width:100%; padding-left:2px">
	<div align="left" id="header" style="width:100%; float:none">
    	<div style="width:60%; padding:5px; float:left" class="tbnavigation">
        	Qu???n l?? b??n h??ng > B??n h??ng ETC/?????i t??c > B??n h??ng cho ETC > Duy???t ????n h??ng ETC
        </div>
        <div align="right" style="padding:5px" class="tbnavigation">
        	<%=Utility.GLanguage("Ch??o m???ng b???n",session,jedis) %>  <%= userTen %> &nbsp;
        </div>
    </div>
  
  	<div align="left" id="button" style="width:100%; height:32px; padding:0px; float:none" class="tbdarkrow">
    		<A href= "../../RouterSvl?task=<%= Utilback.dongMa(getServletContext().getInitParameter("RedirectNoScript") +"ErpDuyetddhNppSvl?userId="+ userId +"") %>" >
        	<img src="../images/Back30.png" alt="Quay ve"  title="Quay ve" border="1px" longdesc="Quay ve" style="border-style:outset"></A>
      <%System.out.println("trang thai :"+lsxBean.getTrangthai()); %>
       <%if(!lsxBean.getTrangthai().equals("4")){ %>     
        <span id="btnSave">
	        <A href="javascript:chotform()" >
	        	<IMG src="../images/Chot.png" title="Duy???t ????n h??ng" alt="Duy???t ????n h??ng" border ="1px" style="border-style:outset"></A>
        </span>
   <%} %>
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
    	<legend class="legendtitle">????n h??ng ETC </legend>
        	<div style="float:none; width:100%" align="left">
        	
            <TABLE width="100%" cellpadding="4" cellspacing="0">	
            	<TR>
                    <TD class="plainlabel" >M?? h???p ?????ng </TD>
                    <TD class="plainlabel"  colspan="3" >
                    	<input type="text" name="mahopdong" value="<%= lsxBean.getMahopdong() %>" readonly="readonly" />
                    </TD>
                </TR>						
                <TR>
                    <TD width="150px" class="plainlabel" >Ng??y ????n h??ng </TD>
                    <TD width="300px"  class="plainlabel"  >
                    	<input type="text" class="days" readonly="readonly"   id="hopdong_tungay" name="hopdong_tungay"  onchange="submitform();"  value="<%= lsxBean.getTungay() %>"/>
                    </TD>
                    
                    <TD width="120px" class="plainlabel" >Ng??y ????? ngh??? giao </TD>
                    <TD class="plainlabel" >
                    	<input type="text" class="days" readonly="readonly"   id="hopdong_denngay"  name="hopdong_denngay" value="<%= lsxBean.getDenngay() %>"/>
                    </TD>
                </TR>
                
                <TR style="display: none;" >
                	<TD class="plainlabel" >????n v??? kinh doanh </TD>
                    <TD class="plainlabel"   >
                    	<select name = "dvkdId"   id="dvkdId"  class="select2" style="width: 200px" >
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
                    	<select name = "kbhId"   id="kbhId"  class="select2" style="width: 200px" >
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
                	<TD class="plainlabel" >Ph??? tr??ch t???nh / G??CN </TD>
                    <TD class="plainlabel"   >
                    	<select name = "gsbhId"    onchange="submitform();" class="select2" style="width: 200px" >
                    		<option value=""> </option>
                        	<%
                        		if(gsbhRs != null)
                        		{
                        			try
                        			{
                        			while(gsbhRs.next())
                        			{  
                        			if( gsbhRs.getString("pk_seq").equals(lsxBean.getGsbhId())){ %>
                        				<option value="<%= gsbhRs.getString("pk_seq") %>" selected="selected" ><%= gsbhRs.getString("ten") %></option>
                        			<%}else { %>
                        				<option value="<%= gsbhRs.getString("pk_seq") %>" ><%= gsbhRs.getString("ten") %></option>
                        		 <% } } gsbhRs.close();} catch(SQLException ex){}
                        		}
                        	%>
                    	</select>
                    </TD>   
                    <TD class="plainlabel" ><%=Utility.GLanguage("NH??N VI??N B??N H??NG",session,jedis) %> </TD>
                    <TD class="plainlabel"    >
                    	<select name = "ddkdId"  class="select2" style="width: 200px" onchange="submitform();" >
                    		<option value=""  > </option>
                        	<%
                        		if(ddkdRs != null)
                        		{
                        			try
                        			{
                        			while(ddkdRs.next())
                        			{  
                        			if( ddkdRs.getString("pk_seq").equals(lsxBean.getDdkdId())){ %>
                        				<option value="<%= ddkdRs.getString("pk_seq") %>" selected="selected" ><%= ddkdRs.getString("ten") %></option>
                        			<%}else { %>
                        				<option value="<%= ddkdRs.getString("pk_seq") %>" ><%= ddkdRs.getString("ten") %></option>
                        		 <% } } ddkdRs.close();} catch(SQLException ex){}
                        		}
                        	%>
                    	</select>
                    </TD>         	
                </TR>
                
                <TR>
                	<TD class="plainlabel" >Kh??ch h??ng ETC </TD>
                    <TD class="plainlabel"   >
                    	<select name = "khId"    id="khId"  class="select2" style="width: 200px" onchange="submitform();" >
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
                    <TD class="plainlabel" >Kho ?????t h??ng</TD>
                    <TD class="plainlabel"   >
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
                    <TD class="plainlabel" >T???ng th??nh ti???n </TD>
                    <TD class="plainlabel"  colspan="3" >
                    	<input type="text" readonly="readonly"  id="txtBVAT" value="" style="text-align: right;"  />
                    	
                    
                    	<input type="hidden"  value="<%= lsxBean.getChietkhau() %>" id="txtPTChietKhau" style="text-align: right;" name="ptChietkhau" onkeypress="return keypress(event);" onkeyup="Dinhdang(this);" />
                    </TD>
                </TR>
                
                <TR>
                    <TD class="plainlabel" >T???ng ti???n chi???t kh???u </TD>
                    <TD class="plainlabel"  >
                    	<input type="text" readonly="readonly"  value="" id="txtTongCK" style="text-align: right; width: 170px; " />
                    	
                    	<a href="" id="popupCHIETKHAU" rel="subcontentCK">
		           	 				&nbsp; <img alt="Khai b??o chi???t kh???u" src="../images/vitriluu.png" title="Khai b??o chi???t kh???u" ></a>
		           	 	
			           	 	<DIV id="subcontentCK" style="position:absolute; visibility: hidden; border: 9px solid #80CB9B;
					                             background-color: white; width: 500px; max-height:300px; overflow:auto; padding: 4px;">
				                    <table width="95%" align="center">
				                        <tr>
				                            <th width="250px" style="font-size: 12px; text-align: center;"><%=Utility.GLanguage("Di???n gi???i",session,jedis) %></th>
				                            <th width="80px" style="font-size: 12px; text-align: center;">Gi?? tr???</th>
				                            <th width="80px" style="font-size: 12px; text-align: center;">????n v???</th>
				                        </tr>
				                        <%
				                        	int sodong = 0;
				                        	if(dhCk_diengiai != null)
				                        	{
				                        		for(int i = 0; i < dhCk_diengiai.length; i++)
				                        		{ 
				                        		 	sodong ++;	%>
				                        	<tr>
				                        		<td><input name="dhCK_diengiai" type="text" style="width: 100%;" value="<%= dhCk_diengiai[i]  %>" <%= ( sodong == 1 ? " readonly='readonly' " : "" ) %> > </td>
				                        		<td><input name="dhCK_giatri" type="text" style="width: 100%;" value="<%= dhCk_giatri[i]  %>" onkeypress="return keypress(event);" onkeyup="Dinhdang(this);" > </td>
				                        		<td>
				                        			<select name="dhCK_loai" style="width: 100%" >
					                        			<% if( dhCk_loai[i].equals("0")  ) { %>
					                        				<option value="0" selected="selected" >Ti???n</option>
					                        				<option value="1" >%</option>
					                        			<% } else { %>
					                        				<option value="0" >Ti???n</option>
					                        				<option value="1" selected="selected" >%</option>
					                        			<% } %>
				                        			</select>
				                        		</td>
				                        	</tr>
				                      <% } } %>
				                      
				                      <% for(int i = sodong; i < 4; i++ ) { %>
				                      		
				                      		<tr>
				                        		<td><input name="dhCK_diengiai" type="text" style="width: 100%;" value=""  > </td>
				                        		<td><input name="dhCK_giatri" type="text" style="width: 100%;" value="" onkeypress="return keypress(event);" onkeyup="Dinhdang(this);" > </td>
				                        		<td>
				                        			<select name="dhCK_loai" style="width: 100%" >
				                        				<option value="0" >Ti???n</option>
				                        				<option value="1" >%</option>
				                        			</select>
				                        		</td>
				                        	</tr>
				                      		
				                      <% } %>
				                      
				                   </table>
				                   
				                   <div align="right">
				                     	<label style="color: red" ></label>
				                     	&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
				                     	<a href="javascript:dropdowncontent.hidediv('subcontentCK')" ><b> ????ng l???i </b></a>
				                   </div>
				                   
					       </DIV>
                    	
	                    	<script type="text/javascript">
				            	dropdowncontent.init('popupCHIETKHAU', "right-top", 300, "click");
				            </script>
                    	
                    </TD>
                    	
                    <TD class="plainlabel" >T???ng ti???n sau CK </TD>
                    <TD class="plainlabel" >
                    	<input type="text" readonly="readonly"  value="" id="txtTongSauCK" style="text-align: right;" />
                    </TD>
                </TR>
                
                <TR>
                    <TD class="plainlabel" >T???ng s??? ti???n VAT </TD>
                    <TD class="plainlabel"  >
                    	<input type="text" value="<%= lsxBean.getVat() %>" id="txtVAT" style="text-align: right;" name="ptVat" onkeypress="return keypress(event);" onkeyup="Dinhdang(this);"  readonly='readonly'  />
                    </TD>
                    	
                    <TD class="plainlabel"  style="color: red;" >T???ng ti???n sau VAT </TD>
                    <TD class="plainlabel" >
                    	<input type="text" readonly="readonly"  value="" id="txtSauVAT" style="text-align: right;" />
                    </TD>
                </TR>
                
                
                <TR style="display: none;" >
                    <TD class="plainlabel" >T???ng th??ng </TD>
                    <TD class="plainlabel"  >
                    	<input type="text" value="" id="txtTongThung" style="text-align: right;" readonly="readonly" /> 
                    </TD>
                    	
                    <TD class="plainlabel"  >T???ng l??? </TD>
                    <TD class="plainlabel" >
                    	<input type="text" value="" id="txtTongLe" style="text-align: right;" readonly="readonly"  /> 
                    </TD>
                </TR>
                
                <TR style="display: none;" >
                    <TD class="plainlabel" >T???ng tr???ng l?????ng </TD>
                    <TD class="plainlabel"  >
                    	<input type="text" value="" id="txtTongTL" style="text-align: right;" readonly="readonly" /> gram
                    </TD>
                    	
                    <TD class="plainlabel"  >T???ng th??? t??ch </TD>
                    <TD class="plainlabel" >
                    	<input type="text" value="" id="txtTongTT" style="text-align: right;" readonly="readonly"  /> cm3
                    </TD>
                </TR>
                
                <TR>
                	<TD class="plainlabel" >Ghi ch?? </TD>
                    <TD class="plainlabel" colspan="3" >
                    	<input type="text"  name="ghichu" value="<%= lsxBean.getGhichu() %>" style="width: 700px;" />
                    </TD>
                </TR>
                
            </TABLE>
			
			<hr />
				<table cellpadding="0px" cellspacing="1px" width="100%">
					<tr class="tbheader">
						<th align="center" width="10%" rowspan="2" >M?? s???n ph???m</th>
						<th align="center" width="22%" rowspan="2" >T??n s???n ph???m</th>
						<th align="center" width="10%" rowspan="2" >????n v???</th>
						<th align="center" width="11%" rowspan="2" >S??? l?????ng</th>
						<th align="center" width="8%" rowspan="2" >????n gi??</th>
						<th align="center" width="8%" rowspan="2" >Chi???t kh???u</th>
						<th align="center" width="8%" rowspan="2" >% VAT</th>
						<th align="center" width="8%" rowspan="2" >Th??nh ti???n</th>
						<th align="center" width="15%" colspan="2" >Ng??y giao</th>
					</tr>
					
					<tr class="tbheader">
						<th align="center" width="7.5%" ><%=Utility.GLanguage("T??? ng??y",session,jedis) %></th>
						<th align="center" width="7.5%" ><%=Utility.GLanguage("?????n ng??y",session,jedis) %></th>
					</tr>
					
					<%
						int count = 0;
						if(spMa != null)
						{
							for(int i = 0; i < spMa.length; i++)
							{ 
								String dvdl_fk = "";
								%>
							<% if( !spSoluong[i].equals("0") ) { %>
							<tr>
								<td>
									<input type="text" name="spMa" value="<%= spMa[i] %>" style="width: 100%"   AUTOCOMPLETE="off" readonly="readonly"  > 
									<input type="hidden" name="spTrongLuong" value="<%= spTrongluong[i] %>" > 
									<input type="hidden" name="spTheTich" value="<%= spThetich[i] %>" > 
									<input type="hidden" name="spQuyDoi" value="<%= spQuyDoi[i] %>"  >
								</td>
								<td> <input type="text" name="spTen" value="<%= spTen[i] %>" style="width: 100%" readonly="readonly"> </td>
								<td>
									<select name="donvi" style="width: 100%" disabled="disabled" >
										<option value="" ></option>
										<% if(dvtRs != null) 
										{ 
												dvtRs.beforeFirst();
												while(dvtRs.next())
												{
													if(spDonvi[i].equals(dvtRs.getString("DONVI")))
													{ dvdl_fk = dvtRs.getString("PK_SEQ"); %>
														<option value="<%= dvtRs.getString("DONVI") %>" selected="selected" ><%= dvtRs.getString("DONVI") %></option>
													<% } else { %>
														<option value="<%= dvtRs.getString("DONVI") %>" ><%= dvtRs.getString("DONVI") %></option>
												   <% } }
										} %>
									 </select> 
								</td>
								
								<td> 
									<input type="text" name="soluong" value="<%= spSoluong[i] %>" style="width: 70%; text-align: right;" readonly="readonly" > 
									
									<% if(spSoluong[i].trim().length() > 0) { %>
									<a href="" id="scheme_<%= spMa[i] %>" rel="subcontent_<%= spMa[i] %>">
			           	 				&nbsp; <img alt="Ch???n s??? l??" src="../images/vitriluu.png"></a>
			           	 		
		           	 		 		<DIV id="subcontent_<%= spMa[i] %>" style="position:absolute; visibility: hidden; border: 9px solid #80CB9B;
					                             background-color: white; width: 450px; max-height:300px; overflow:auto; padding: 4px;">
					                    <table width="95%" align="center">
					                    	<tr>
					                    		<td ><b>T???ng xu???t</b></td>
					                    		<td colspan="3" > <input type="text" name="soluong2" value="<%= spSoluong[i] %>" style="width: 100px; text-align: right;" readonly="readonly" >	</td>
					                    	</tr>
					                        <tr>
					                            <th width="100px" style="font-size: 11px">S??? l?????ng</th>
					                            <th width="100px" style="font-size: 11px">S??? l??</th>
					                            <th width="100px" style="font-size: 11px">Ng??y h???t h???n</th>
					                             <th width="100px" style="font-size: 11px">Ng??y nh???p kho</th>
					                            <th width="100px" style="font-size: 11px">T???n kho</th>
					                        </tr>
			                            	<%
			                             
			                            		ResultSet spRs = lsxBean.getSoloTheoSp_nhapkho(spMa[i], lsxBean.getId(), dvdl_fk)  ;
				                        		if(spRs != null)
				                        		{
				                        			while(spRs.next())
				                        			{
				                        				String tudeXUAT = "";
				                        				if(spRs.getString("soluong").trim().length() > 0)
				                        					tudeXUAT = formater2.format(spRs.getDouble("soluong"));
				                        				
				                        				String temID = spMa[i];
				                        				System.out.println("sp la "+temID);
				                        			%>
				                        			
				                        			<tr>
				                        				<td>
				                        				<%  
				                        				double _soluongct=0;
				                        				try{
				                        				  _soluongct=Double.parseDouble(sanpham_soluong.get(spMa[i] + "__" + spRs.getString("SOLO") + "__" +  spRs.getString("NGAYHETHAN")  + "__" +  spRs.getString("NGAYNHAPKHO") ));
				                        				}catch(Exception err){}	
				                        				
				                        				if(sanpham_soluong.get(spMa[i] + "__" + spRs.getString("SOLO") + "__" +  spRs.getString("NGAYHETHAN") + "__" +  spRs.getString("NGAYNHAPKHO") ) != null ) { %>
				                        					<input type="text" style="width: 100%;text-align: right;" name="<%= temID %>_spSOLUONG" value="<%= formater2.format(_soluongct) %>" onkeyup="CheckSoLuong_DeXuat(this);" >
				                        				<% } else { %>
				                        					<input type="text" style="width: 100%;text-align: right;" name="<%= temID %>_spSOLUONG" value="<%= tudeXUAT  %>" onkeyup="CheckSoLuong_DeXuat(this);" >
				                        				<% } %>
				                        				</td>
				                        				<td>
				                        					<input type="text" style="width: 100%;text-align: center;" name="<%= temID %>_spSOLO" value="<%= spRs.getString("SOLO") %>" readonly="readonly">
				                        				</td>
				                        				<td>
				                        					<input type="text" style="width: 100%;text-align: center;" name="<%= temID %>_spNGAYHETHAN" value="<%= spRs.getString("NGAYHETHAN") %>" readonly="readonly">
				                        				</td>
				                        				<td>
				                        					<input type="text" style="width: 100%;text-align: right;" name="<%= temID %>_spNGAYNHAP" value="<%= spRs.getString("NGAYNHAPKHO") %>" readonly="readonly">
				                        				</td>
				                        				<td>
				                        					<input type="text" style="width: 100%;text-align: right;" name="<%= temID %>_spTONKHO" value="<%= formater2.format(spRs.getDouble("AVAILABLE")) %>" readonly="readonly">
				                        				</td>
				                        			</tr>
				                        			
				                        		 <% } } %>
				                        		 
					                     </table>
					                     <div align="right">
					                     	<label style="color: red" ></label>
					                     	&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
					                     	<a href="javascript:dropdowncontent.hidediv('subcontent_<%= spMa[i] %>')" > ????ng l???i </a>
					                     </div>
						            </DIV> 
						            
						            <script type="text/javascript">
						            	dropdowncontent.init('scheme_<%= spMa[i]  %>', "left-top", 300, "click");
						            </script>
						         <% } else { %>
						         	<a href="javascript:void(0);" >&nbsp; <img alt="Ch???n s??? l??" src="../images/vitriluu.png"></a>
						         <% } %>
									
								</td>
								<td> <input type="text" name="dongia" value="<%= spGianhap[i] %>" style="width: 100%; text-align: right;" onkeyup="Dinhdang(this);" readonly="readonly" > </td>
								<td> <input type="text" name="chietkhau" value="<%= spChietkhau[i] %>" style="width: 100%; text-align: right;" onkeypress="return keypress(event);" onkeyup="Dinhdang(this);" readonly="readonly" > </td>
								<td> <input type="text" name="spvat" value="<%= spVat[i] %>" style="width: 100%; text-align: right;" onkeypress="return keypress(event);" readonly="readonly"  > </td>
								<td> <input type="text" name="thanhtien" value="" style="width: 100%; text-align: right;" readonly="readonly" > </td>
								<td> <input type="text" name="tungay" class="days" value="<%= spTungay[i] %>" style="width: 100%; text-align: center;" readonly="readonly"  > </td>
								<td> <input type="text" name="denngay" class="days" value="<%= spDenngay[i] %>" style="width: 100%; text-align: center;" readonly="readonly" > </td>
							</tr>	
							<% } %>
								
						<% count ++; } } %>
					
				</table>

     		</div>
     </fieldset>	
    </div>
</div>

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
</BODY>
</HTML>