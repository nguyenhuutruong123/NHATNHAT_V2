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

<% IErpHopdongNpp lsxBean = (IErpHopdongNpp)session.getAttribute("lsxBean"); %>
<% ResultSet dvkdRs = lsxBean.getDvkdRs(); %>
<% ResultSet kbhRs = lsxBean.getKbhRs(); %>
<% ResultSet nppRs = lsxBean.getKhRs(); %>
<% ResultSet khApdungRs = lsxBean.getKhApdungRs(); %>
<% ResultSet khonhapRs = lsxBean.getKhoNhapRs(); %>
<% Utility Utilback = new Utility(); %>
<% ResultSet gsbhRs = lsxBean.getGsbhRs(); %>
<% ResultSet ddkdRs = lsxBean.getDdkdRs(); %>
<% ResultSet hopdongRs = lsxBean.getHopdongRs();
	ResultSet ghichuRs = lsxBean.getGhichuRs();

%>

<% 
	String[] spStt = lsxBean.getSpStt();
	String[] spMa = lsxBean.getSpMa(); 
	String[] spTen = lsxBean.getSpTen();
	String[] spDonvi = lsxBean.getSpDonvi();
	String[] spSoluong = lsxBean.getSpSoluong();
	String[] spGianhap = lsxBean.getSpGianhap();
	String[] spTungay = lsxBean.getSpTungay();
	String[] spDenngay = lsxBean.getSpDenngay();
	String[] spTrongluong = lsxBean.getSpTrongluong();
	String[] spThetich = lsxBean.getSpThetich();
	String[] spQuyDoi = lsxBean.getSpQuyDoi();
	
	String[] spVat = lsxBean.getSpVat();
	String[] dadat = lsxBean.getDadat();
	String[] ckcskh = lsxBean.getCkcskh();
	
	NumberFormat formater = new DecimalFormat("##,###,###");
	List<String> spTheoHopdong = lsxBean.SanPham_theo_HopDong_chinh();
%>

<% String userId = (String) session.getAttribute("userId");  %>
<% String userTen = (String) session.getAttribute("userTen");  
	String sum = (String) session.getAttribute("sum");
	Utility util = (Utility) session.getAttribute("util");
	
	if(!util.check(userId, userTen, sum)){
		response.sendRedirect("/KHUONGDUY/index.jsp");
	} else { %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
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

 <link rel="stylesheet" href="https://ajax.googleapis.com/ajax/libs/jqueryui/1.12.1/themes/smoothness/jquery-ui.css">
<script src="../css/bower_components/jquery/dist/jquery.min.js"></script>
 <script src="https://ajax.googleapis.com/ajax/libs/jqueryui/1.12.1/jquery-ui.min.js"></script>

<link href="../css/jquery-ui.css" rel="stylesheet" type="text/css" />
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


<script src="../css/bower_components/bootstrap-datepicker/dist/js/bootstrap-datepicker.js"></script>
<script src="../css/bower_components/select2/js/select2.min.js"></script>



	
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
	
	function replaces()
	{
		var spMa = document.getElementsByName("spMa");
		var spTen = document.getElementsByName("spTen");  
		var donvi = document.getElementsByName("donvi");
		var soluong = document.getElementsByName("soluong");
		var dongia = document.getElementsByName("dongia");
		var thanhtien = document.getElementsByName("thanhtien");
		var slton = document.getElementsByName("slton");
		var dongiaSAUVAT = document.getElementsByName("dongiaSAUVAT");
		var spvat = document.getElementsByName("spvat");
		var ckcskh = document.getElementsByName("ckcskh");
		
		var i;
		for(i = 0; i < spMa.length; i++)
		{
			
			if(spMa.item(i).value == "")
			{
				$(dongia.item(i)).attr('readonly', false);
				
				spMa.item(i).value = "";
				spTen.item(i).value = "";
				donvi.item(i).value = "";
				soluong.item(i).value = "";
				dongia.item(i).value = "";
				slton.item(i).value = "";
				thanhtien.item(i).value = "";	
				dongiaSAUVAT.item(i).value = "";	
				spvat.item(i).value = "";	
				ckcskh.item(i).value = "";	
				
			}
		}	
		
		TinhTien();
		setTimeout(replaces, 300);
	}
	

	function DinhDangDonGia(num) 
	{
	 num = num.toString().replace(/\$|\,/g,'');
	  
	 var sole = '';
	 if(num.indexOf(".") >= 0)
	 {
	  sole = num.substring(num.indexOf('.'));
	 }
	 
	 if(isNaN(num))
	 num = "0";
	 sign = (num == (num = Math.abs(num)));
	 num = Math.floor(num*100);
	 num = Math.floor(num/100).toString();
	 for (var i = 0; i < Math.floor((num.length-(1+i))/3); i++)
	 num = num.substring(0,num.length-(4*i+3)) + ',' + num.substring(num.length-(4*i+3));

	 var kq;
	 if(sole.length >= 0)
	 {
	  kq = (((sign)?'':'-') + num) + sole;
	 }
	 else
	  kq = (((sign)?'':'-') + num);
	 
	 //alert(kq);
	 return kq;
	}
		
	 function TinhTien()
	 {
		 var spMa = document.getElementsByName("spMa");
			var soluong = document.getElementsByName("soluong");
			var dongia = document.getElementsByName("dongia");
			var chietkhau = document.getElementsByName("chietkhau");
			var thueVAT = document.getElementsByName("spvat");
			var thanhtien = document.getElementsByName("thanhtien");
			var dongiaSAUVAT=document.getElementsByName("dongiaSAUVAT");
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
						
					
					var tt = parseFloat(dongia.item(i).value.replace(/,/g,"")) * parseFloat(soluong.item(i).value.replace(/,/g,"")) ;
					tt = parseFloat(tt) * ( 1 +  parseFloat(_thueVAT) / 100 );
					thanhtien.item(i).value = DinhDangTien(tt);
					dongia.item(i).value= DinhDangDonGia(parseFloat(dongia.item(i).value.replace(/,/g,"")));
					
					dongiaSAUVAT.item(i).value=DinhDangDonGia(parseFloat(dongiaSAUVAT.item(i).value.replace(/,/g,"")));
						
					tongtien += ( parseFloat(dongia.item(i).value.replace(/,/g,"")) * parseFloat(soluong.item(i).value.replace(/,/g,"")) );
					
					tongtienVAT += ( parseFloat(dongia.item(i).value.replace(/,/g,"")) * parseFloat(soluong.item(i).value.replace(/,/g,"")) ) * ( parseFloat(_thueVAT) / 100 );
				}		
			}
			
		
			var tongDhCK = 0;
			var tongtien_sau_hoahong = 0;
		
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
	
	 function CapNhatGia(e, pos)
	 { 
		/*  var nppId= document.getElementById("nppId").value;
			var kbhId= document.getElementById("kbhId").value;
			var dvkdId = document.getElementById("dvkdId").value;
			var dvdlId = document.getElementsByName("donvi");
			var spId = document.getElementsByName("spMa");
			var spMa = document.getElementsByName("spMa");
			var dongia=document.getElementsByName("dongia");
			var ngaychuyen = document.getElementById("ngaychuyen").value;
			var spQuyDoi=document.getElementsByName("spQuyDoi");
			var spTrongLuong = document.getElementsByName("spTrongLuong") ;
			var spTheTich = document.getElementsByName("spTheTich") ;
			
			 $( dongia.item(pos) ).val( "" );
			 $( spQuyDoi.item(pos) ).val( "" );
			 $( spTrongLuong.item(pos)).val( "" );
			 $( spTheTich.item(pos)).val( "" );
			 
			$.ajax
			(
				{
			    url: "../../ErpHopdongNppSvl?type=GetDonGia&spMa=" + spMa.item(pos).value + "&dvdlId=" + dvdlId.item(pos).value + "&nppId=" + nppId+"&kbhId="+kbhId+"&dvkdId="+dvkdId+""  ,
			    type : 'GET',
			    dataType: 'json',
			    success: function( data )
			    {
			        var npp = data ;
			        console.log(data);
			       $( dongia.item(pos)        ).val( npp.dongia );
			       $( spQuyDoi.item(pos)         ).val( npp.quycach );
			       $( spTheTich.item(pos)         ).val( npp.thetich );
			       $( spTrongLuong.item(pos)         ).val( npp.trongluong );
			       
			       CapNhatSAUVAT(pos);
			       
			     }
			});
			
			CapNhatSAUVAT(pos); */
	 }
	 
		 
	 function saveform()
	 {	
		 document.getElementById("btnSave").innerHTML = "<a href='#'><img src='../images/waiting.gif'  title='cho luu..' border='1' longdesc='cho luu..' style='border-style:outset'> Processing...</a>";
	 	 document.forms['hctmhForm'].action.value = 'save';
	     document.forms['hctmhForm'].submit();
	 }
	 
	 function submitform()
	 { 
		 document.forms['hctmhForm'].action.value='submit';
	     document.forms["hctmhForm"].submit();
	 }
	 
	 function changePhuLuc()
	 {
		 document.forms['hctmhForm'].action.value='changePhuLuc';
	     document.forms["hctmhForm"].submit();
	 }
	 
	 function Apkhuyenmai()
	 {
		 document.getElementById("btnApKhuyenMai").innerHTML = "<a href='#'><img src='../images/waiting.gif'  title='cho thuc hien..' border='1' longdesc='cho thuc hien..' style='border-style:outset'> Processing...</a>";
		 document.forms['hctmhForm'].action.value='apkhuyenmai';
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
	
	
	
	
	
	function toFixed(value, precision) 
	{
	    var precision = precision || 0,
	    neg = value < 0,
	    power = Math.pow(10, precision),
	    value = Math.round(value * power),
	    integral = String((neg ? Math.ceil : Math.floor)(value / power)),
	    fraction = String((neg ? -value : value) % power),
	    padding = new Array(Math.max(precision - fraction.length, 0) + 1).join('0');

	    return precision ? integral + '.' +  padding + fraction : integral;
	}
	 
</script>



<link href="../css/select2.css" rel="stylesheet" />
<script src="../scripts/select2.js"></script>
<script>
	$(document).ready(function()
	{
		$(".select2").select2();
		
		
		/* $(".apchoNhanVien").colorbox({width:"80%", inline:true, href:"#apchoNhanVien"});
        
        $("#click").click(function(){ 
            $('#click').css({"background-color":"#f00", "color":"#fff", "cursor":"inherit"}).text("SalesUpDemoDuoc - Project.");
            return false;
        }); */
		
	});
	
	
</script>

<script type="text/javascript" src="../scripts/ajax.js"></script>
<LINK rel="stylesheet" type="text/css" media="screen" href="../css/tabs.css">
<LINK rel="stylesheet" type="text/css" media="screen" href="../css/tabs-panes.css">
<link rel="stylesheet" href="../css/myloader.css" />
<script type="text/javascript" src="../scripts/myloader.js"></script>


<script>

$(function() {
 
 	$("ul.tabs").tabs("div.panes > div");
});
</script>

</head>
<BODY leftmargin="0" bottommargin="0" topmargin="0" rightmargin="0">
<form name="hctmhForm" method="post" id="hctmhForm" action="../../ErpHopdongNppUpdateSvl">
<% geso.dms.center.util.Csrf csdr=new geso.dms.center.util.Csrf(request,response,true,false,true);%>
 <input type="hidden" id="<%=csdr.get_tokenName() %>" name="<%=csdr.get_tokenName() %>" value='<%=csdr.get_tokenValue() %>'>
<input type="hidden" name="userId" value='<%= userId %>'>
<input type="hidden" name="action" id ='action' value='AUTOCOMPLETE_SP'>
<input type="hidden" name="nppId" value='<%= lsxBean.getNppId() %>'>
<input type="hidden" name="id" id = "id" value='<%= lsxBean.getId() %>'>

<div class="loading hidden">
            <div class='uil-ring-css' style='transform:scale(0.79);'>
                <div></div>
            </div>
        </div>

<div id="main" style="width:100%; padding-left:2px">
	<div align="left" id="header" style="width:100%; float:none">
    	<div style="width:60%; padding:5px; float:left" class="tbnavigation">
        	Quản lý bán hàng > Bán hàng ETC/Đối tác > Hợp đồng > Hiển thị
        </div>
        <div align="right" style="padding:5px" class="tbnavigation">
        	Chào mừng bạn <%= userTen %> &nbsp;
        </div>
    </div>
  
  	<div align="left" id="button" style="width:100%; height:32px; padding:0px; float:none" class="tbdarkrow">
    	<A href= "../../RouterSvl?task=<%= Utilback.dongMa(getServletContext().getInitParameter("RedirectNoScript") +"ErpHopdongNppSvl?userId="+ userId+"&loaidonhang="+ lsxBean.getLoaidonhang()+"") %>" >
        	<img src="../images/Back30.png" alt="Quay ve"  title="Quay ve" border="1px" longdesc="Quay ve" style="border-style:outset"></A>
        <span id="btnSave">

        <span id="btnSave">
	       
        </span>
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
        	
           <!--  <TABLE width="100%" cellpadding="4" cellspacing="0">	 -->
           <table id="tableSp" class="table  table-extra" style="width : 100%">
            	<TR>
                    <TD class="plainlabel" >Mã hợp đồng </TD>
                    <TD class="plainlabel"  colspan="3" >
                    	<input type="text" name="mahopdong" value="<%= lsxBean.getMahopdong() %>"/>
                    </TD>
                </TR>						
                <TR>
                    <TD width="150px" class="plainlabel" >Từ ngày </TD>
                    <TD width="300px"  class="plainlabel"  >
                    	<input data-date-format="yyyy-mm-dd" type="text" class="days" readonly="readonly"   id="hopdong_tungay" name="hopdong_tungay" value="<%= lsxBean.getTungay() %>"/>
                    </TD>
                    
                    <TD width="120px" class="plainlabel" >Đến ngày </TD>
                    <TD class="plainlabel" >
                    	<input data-date-format="yyyy-mm-dd" type="text" class="days" readonly="readonly"   id="hopdong_denngay"  name="hopdong_denngay" value="<%= lsxBean.getDenngay() %>"/>
                    </TD>
                </TR>
                 <TR>
                    <TD width="150px" class="plainlabel" >Ngày mở thầu </TD>
                    <TD width="300px"  class="plainlabel"  >
                    	<input data-date-format="yyyy-mm-dd" type="text" class="days" readonly="readonly"   id="NgayMoThau" name="NgayMoThau" value="<%= lsxBean.getNgayMoThau() %>"/>
                    </TD>
                    
                    <TD width="120px" class="plainlabel" >Ngày nộp hồ sơ thầu </TD>
                    <TD class="plainlabel" >
                    	<input data-date-format="yyyy-mm-dd" type="text" class="days" readonly="readonly"   id="NgayNopHoSoThau"  name="NgayNopHoSoThau" value="<%= lsxBean.getNgayNopHoSoThau() %>"/>
                    </TD>
                </TR>
                
                
                <TR style="display: none;" >
                	<TD class="plainlabel" >Đơn vị kinh doanh </TD>
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
                    <TD class="plainlabel" >Kênh bán hàng </TD>
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
                	<TD class="plainlabel" >Phụ trách tỉnh / GĐCN </TD>
                    <TD class="plainlabel"   >
                    	<input type="hidden" name="gsbhId" value="<%= lsxBean.getGsbhId() %>"  >
                    	<select class="select2" style="width: 200px" disabled="disabled" >
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
                    <TD class="plainlabel" >Nhân viên bán hàng </TD>
                    <TD class="plainlabel"    >
                    	<select name = "ddkdId"  class="select2" style="width: 200px"  >
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
                
                <TR >
                	<TD class="plainlabel">Loại hợp đồng </TD>
                    <TD class="plainlabel"  >
                    	<select disabled="disabled" name = "loaidonhang"  class="select2" style="width: 200px" onchange="submitform();"  >
                        	<% if(lsxBean.getLoaidonhang().equals("0")) { %>
                        		<option value="0" selected="selected" >Bình thường</option>
                        	<% } else { %>
                        		<option value="0"  >Bình thường</option>
                        	<% } %>
                        	<% if(lsxBean.getLoaidonhang().equals("1")) { %>
                        		<option value="1" selected="selected" >Phụ lục</option>
                        	<% } else { %>
                        		<option value="1"  >Phụ lục</option>
                        	<% } %>
                        	<% if(lsxBean.getLoaidonhang().equals("2")) { %>
                        		<option value="2" selected="selected" >Nguyên tắc</option>
                        	<% } else { %>
                        		<option value="2"  >Nguyên tắc</option>
                        	<% } %>
                        	<% if(lsxBean.getLoaidonhang().equals("3")) { %>
                        		<option value="3" selected="selected" >Hợp đồng chung</option>
                        	<% } else { %>
                        		<option value="3"  >Hợp đồng chung</option>
                        	<% } %>
                    	</select>
                    	<input type="hidden" name ='loaidonhang' value="<%= lsxBean.getLoaidonhang() %>" >
                    </TD> 
                    
                    <TD class="plainlabel" >Kho đặt hàng</TD>
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
                
                <% if (!lsxBean.getLoaidonhang().equals("3")) { %>
                <TR>
                	<TD class="plainlabel" >Khách hàng ETC </TD>
                    <TD class="plainlabel" <%= ( !lsxBean.getLoaidonhang().equals("1") )  ? " colspan='3' " : "" %>  >
                    	<select  disabled="disabled" name = "khId" id="khId"  class="select2" style="width: 200px" onchange="submitform();" >
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
                    	<input type="hidden" name ='khId' value="<%=lsxBean.getKhId() %>" >
                    </TD>   
                    
                    <% if(lsxBean.getLoaidonhang().equals("1")) { %> 
                    <TD class="plainlabel" >Phụ lục của HĐ </TD>
                    <TD class="plainlabel"    >
                    	<select  disabled="disabled" name = "hopdongId"  class="select2" style="width: 200px" onchange="changePhuLuc();" >
                    		<option value="" ></option>
                        	<%
                        		if(hopdongRs != null)
                        		{
                        			try
                        			{
                        			while(hopdongRs.next())
                        			{  
                        			if( hopdongRs.getString("pk_seq").equals(lsxBean.getHopdongId())){ %>
                        				<option value="<%= hopdongRs.getString("pk_seq") %>" selected="selected" ><%= hopdongRs.getString("diengiai") %></option>
                        			<%}else { %>
                        				<option value="<%= hopdongRs.getString("pk_seq") %>" ><%= hopdongRs.getString("diengiai") %></option>
                        		 <% } } 
                        			hopdongRs.close();} 
                        			catch(Exception ex){ ex.printStackTrace(); }
                        		}
                        	%>
                    	</select>
                    	<input type="hidden" name ='hopdongId' value="<%=lsxBean.getHopdongId() %>" >
                    </TD>  
                    <% } //else if (lsxBean.getLoaidonhang().equals("3")) { %>
                    	
                  
                      	
                </TR>
                <% } %>
                
                <TR>
                    <TD class="plainlabel" >Tổng thành tiền </TD>
                    <TD class="plainlabel"  colspan="3" >
                    	<input type="text" readonly="readonly"  id="txtBVAT" value="" style="text-align: right;"  /> VNĐ
                    	
                    
                    	<input type="hidden"  value="<%= lsxBean.getChietkhau() %>" id="txtPTChietKhau" style="text-align: right;" name="ptChietkhau" onkeypress="return keypress(event);" onkeyup="Dinhdang(this);" />
                    </TD>
                </TR>
                
                <TR style="display: none;">
                    <TD class="plainlabel" >Tổng tiền chiết khấu </TD>
                    <TD class="plainlabel"  >
                    	<input type="text" readonly="readonly"  value="" id="txtTongCK" style="text-align: right; width: 200px; " />
                    </TD>
                    	
                    <TD class="plainlabel" >Tổng tiền sau CK </TD>
                    <TD class="plainlabel" >
                    	<input type="text" readonly="readonly"  value="" id="txtTongSauCK" style="text-align: right;" />
                    </TD>
                </TR>
                
                <TR>
                    <TD class="plainlabel" >Tổng tiền VAT </TD>
                    <TD class="plainlabel"  >
                    	<input type="text" value="<%= lsxBean.getVat() %>" id="txtVAT" style="text-align: right;" name="ptVat" onkeypress="return keypress(event);" onkeyup="Dinhdang(this);"  readonly='readonly'  />
                    </TD>
                    	
                    <TD class="plainlabel"  style="color: red;" >Tổng tiền sau VAT </TD>
                    <TD class="plainlabel" >
                    	<input type="text" readonly="readonly"  value="" id="txtSauVAT" style="text-align: right;" />
                    </TD>
                </TR>
                
                 <TR>
                	<TD class="plainlabel" >Lý do không trúng thầu </TD>
                    <TD class="plainlabel" colspan="3" >
                    	<input type="text"  name="LyDoKhongTrungThau" value="<%= lsxBean.getLyDoKhongTrungThau() %>" style="width: 700px;" />
                    </TD>
                </TR>
                
                
                
              
                
                <TR>
                	<TD class="plainlabel" >Ghi chú </TD>
                    <TD class="plainlabel" colspan="3" >
                    	<input type="text"  name="ghichu" value="<%= lsxBean.getGhichu() %>" style="width: 700px;" />
                    </TD>
                </TR>
                 <TR style="display: none">
                	<TD class="plainlabel" >Lý do không trúng thầu </TD>
                    <TD class="plainlabel" colspan="3" >
                    	<input type="text"  name="LyDoKhongTrungThau" value="<%= lsxBean.getLyDoKhongTrungThau() %>" style="width: 700px;" />
                    </TD>
                </TR>
                
                <tr>
                	<td colspan="4">
                		
                	</td>
                </tr>
                
               
                
            </TABLE>
				<table id="tableSp" class="table table-bordered table-extra" style="width : 100%">
					<tr class="tbheader">
						<th align="center" width="3%" rowspan="2" style="text-align: center; vertical-align: middle;" >STT</th>
						<th align="center"  width="6%" rowspan="2" style="text-align: center; vertical-align: middle;">Mã sản phẩm</th>
						<th align="center" width="15%" rowspan="2"style="text-align: center; vertical-align: middle;"  >Tên sản phẩm</th>
						<th align="center" width="7%" rowspan="2" style="text-align: center; vertical-align: middle;">Đơn vị</th>
						<th align="center" width="8%" rowspan="2" style="text-align: center; vertical-align: middle;">Số lượng</th>
						<th align="center" width="8%" rowspan="2" style="text-align: center; vertical-align: middle;">Đơn giá</th>
						<th align="center" width="8%" rowspan="2"  style="display: none;" >Đơn giá<br />Sau VAT</th>
						<th align="center" width="8%"  rowspan="2" style="text-align: center; vertical-align: middle;">Số lượng Bán</th>
						<th align="center" width="7%" rowspan="2" >% VAT</th>
				
						<th align="center" width="8%" rowspan="2" style="text-align: center; vertical-align: middle;">Thành tiền</th>
						<th align="center" width="8%" rowspan="2" >%Chiết khấu CSKH</th>
						<th align="center" width="15%" colspan="2" style="text-align: center; vertical-align: middle;">Ngày giao</th>
					</tr>
					
					<tr class="tbheader">
						<th align="center" width="7.5%" style="text-align: center; vertical-align: middle;">Từ ngày</th>
						<th align="center" width="7.5%" style="text-align: center; vertical-align: middle;">Đến ngày</th>
					</tr>
					
					<%
						int count = 0;
						if(spMa != null)
						{
							for(int i = 0; i < spMa.length; i++)
							{%>
						
							<tr>
								<td align="center"> <%= i+1 %>
									<input type="hidden" name="spStt" value="<%= spStt[i] %>"  >  
									
								</td>
								<td>
									<input type="text" name="spMa" id="spMa_<%=i %>" value="<%= spMa[i] %>" style="width: 100%" AUTOCOMPLETE="off"   class='autoMa' > 
									<input type="hidden" name="spTrongLuong" value="<%= spTrongluong[i] %>" > 
									<input type="hidden" name="spTheTich" value="<%= spThetich[i] %>" > 
									<input type="hidden" name="spQuyDoi" value="<%= spQuyDoi[i] %>"  >
								</td>
								<td> <input type="text" name="spTen"  id="spTen_<%=i %>"  value="<%= spTen[i] %>" style="width: 100%" readonly="readonly"> </td>
								<td>
									<div id="divDonvi_<%=i %>">
									<%if(spMa[i].length() > 0)
									{
										if(lsxBean.getLoaidonhang().equals("1") &&  spTheoHopdong.contains(spMa[i]) )
										{%>
												
											<input type="text" name="donvi"   value="<%=spDonvi[i] %>" style="width: 100%" readonly="readonly"> 
										<%	
										}else
										{
									%>
																			
									
												<select name="donvi" style="width: 100%"  >
												<option value="" ></option>
												<% 
												ResultSet dvtRs = ErpHopdongNpp.getDonViTinhRs(lsxBean.getDb(), spMa[i]);
												if(dvtRs != null) 
												{ 
														
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
										<%}
									}
										else { %>
										<input type="hidden" name ='donvi' >
										<%} %>
									</div>
									
								</td>
								
								<td> 
									<input type="text" name="soluong" value="<%= spSoluong[i] %>" style="width: 100%; text-align: right;" onkeypress="return keypress(event);" onkeyup="Dinhdang(this);" > 
								</td>
								<td> <input type="text" name="dongia"  id="dongia_<%=i %>"  value="<%= spGianhap[i] %>" style="width: 100%; text-align: right;" onkeypress="return keypress(event);" <%= lsxBean.getLoaidonhang().equals("1") &&  spTheoHopdong.contains(spMa[i])  ? " readonly='readonly' " : " " %>  onkeyup="CapNhatSAUVAT(<%= count %>);"  > </td>
								<td  style="display: none;"> <input type="text" name="dongiaSAUVAT" value="" style="width: 100%; text-align: right;" onkeypress="return keypress(event);" <%=  lsxBean.getLoaidonhang().equals("1")  ? " readonly='readonly' " : " " %>  onkeyup="CapNhatVAT(<%= count %>);" > </td>
								
								<td  > <input type="text" name="slton" value="<%= dadat[i] %>" style="width: 100%; text-align: right;" onkeypress="return keypress(event);" onkeyup="Dinhdang(this);" <%= lsxBean.getLoaidonhang().equals("1") ? " readonly='readonly' " : "" %> > </td>
								<td> <input type="text" name="spvat" id="spvat_<%=i %>" value="<%= spVat[i] %>" style="width: 100%; text-align: right;" onkeypress="return keypress(event);" readonly="readonly"  <%= lsxBean.getLoaidonhang().equals("1") ? " readonly='readonly' " : "" %> > </td>
								
								
								<td> <input  type="text" name="thanhtien" value="" style="width: 100%; text-align: right;" readonly="readonly" > </td>
								<td> <input type="text" name="ckcskh" value="<%=ckcskh[i] %>" style="width: 100%; text-align: right;" onkeypress="return keypress(event);" onkeyup="Dinhdang(this);" > </td>
								
								<td> <input  AUTOCOMPLETE="off" data-date-format="yyyy-mm-dd" type="text" name="tungay"  value="<%= spTungay[i] %>" style="width: 100%; text-align: center;" <%= lsxBean.getLoaidonhang().equals("1") ? " readonly='readonly' " : " class='days' " %> > </td>
								<td> <input  AUTOCOMPLETE="off" data-date-format="yyyy-mm-dd" type="text" name="denngay" value="<%= spDenngay[i] %>" style="width: 100%; text-align: center;" <%= lsxBean.getLoaidonhang().equals("1") ? " readonly='readonly' " : " class='days' " %> > </td>
							</tr>	
								
						<% count ++; } } %>
					
					<% for(int i = count; i < 20; i++) { %>
						
						<tr>
							<td align="center"> <%= i+1 %>
								<input type="hidden" name="spStt" value="<%= i %>"  > 
							</td>
							<td>
								<input type="text" id="spMa_<%=i %>" name="spMa" value="" style="width: 100%"  class='autoMa' AUTOCOMPLETE="off"  > 
								<input type="hidden" name="spTrongLuong" value="" > 
								<input type="hidden" name="spTheTich" value="" > 
								<input type="hidden" name="spQuyDoi" value=""  >
							</td>
							<td> <input type="text" name="spTen" id="spTen_<%=i %>" value="" style="width: 100%" readonly="readonly"> </td>
							<td>
								<div id="divDonvi_<%=i %>">
									<input type="hidden" name ='donvi' >
								</div>
							 </td>
							
							<td> <input type="text" name="soluong" value="" style="width: 100%; text-align: right;" onkeypress="return keypress(event);" onkeyup="Dinhdang(this);" > </td>
							<td> <input type="text" name="dongia" id="dongia_<%=i %>" value="" style="width: 100%; text-align: right;" onkeypress="return keypress(event);" onkeyup="CapNhatSAUVAT(<%= i %>);" > </td>
							 <td style="display: none;"> <input type="text" name="dongiaSAUVAT" value="" style="width: 100%; text-align: right;" onkeypress="return keypress(event);" onkeyup="CapNhatVAT(<%= i %>);" > </td>
							 
							<td  > <input type="text" name="slton" value="" style="width: 100%; text-align: right;" onkeypress="return keypress(event);" onkeyup="Dinhdang(this);" <%= lsxBean.getLoaidonhang().equals("1") ? " readonly='readonly' " : "" %> > </td>
								<td> <input type="text" name="spvat" id="spvat_<%=i %>" value="" style="width: 100%; text-align: right;" onkeypress="return keypress(event);" readonly="readonly" > </td>
						
							
							<td> <input type="text" name="thanhtien" value="" style="width: 100%; text-align: right;" readonly="readonly" > </td>
								<td> <input type="text" name="ckcskh" value="" style="width: 100%; text-align: right;" onkeypress="return keypress(event);"  > </td>
						
							<td> <input data-date-format="yyyy-mm-dd" type="text" name="tungay" class="days" value="" style="width: 100%; text-align: center;"   AUTOCOMPLETE="off" > </td>
							<td> <input data-date-format="yyyy-mm-dd" type="text" name="denngay" class="days" value="" style="width: 100%; text-align: center;"   AUTOCOMPLETE="off" > </td>
						</tr>	
						
					<% } %>	
						
				</table>
			
     		
     		</div>
     </fieldset>	
    </div>
</div>

<script type="text/javascript">
	replaces();
	

	
	
	$.fn.serializeObject = function()
	{
	   var o = {};
	   var a = this.serializeArray();
	   $.each(a, function() {
	       if (o[this.name]) {
	           if (!o[this.name].push) {
	               o[this.name] = [o[this.name]];
	           }
	           o[this.name].push(this.value || '');
	       } else {
	           o[this.name] = this.value || '';
	       }
	   });
	   return o;
	};

	
	function getDonViDoLuong(num,spMa)
	{
		
		
		 var o = {};
		 o['action'] ='GetDonVi';
  	  	 o['spMaDonVi'] = spMa;
  	     o['<%=csdr.get_tokenName() %>'] = $('#<%=csdr.get_tokenName() %>').val();

       $.ajax({
      	 type:'POST',
           data : o ,
           url: '../../ErpHopdongNppUpdateSvl',
           success: function (data) {
              	if(data !='')
            	{
              		$('#divDonvi_'+num).html(data);
              		
            	}
           },

       });
	}
	
	$(document).ready(function() {		
	       
		
		$('.days').datepicker({
			  autoclose: true
			});
		
		$(".autoMa").autocomplete({
			 
	         source: function (request, response) {
	        	
	        	 $('#action').val('AUTOCOMPLETE_SP');
	        	 
	        	  var o = $('#hctmhForm').serializeObject();
	        	  o['term'] = request.term;
	        	 
	             $.ajax({
	            	 type:'POST',
	                 data : o ,
	                 url: '../../ErpHopdongNppUpdateSvl',
	                 success: function (data) {
	                     response($.map(data, function (item) {
	                          return {
	                            /*   label: item.description ,
	                              value: item.description ,
	                              recordKey: JSON.stringify(item) */
	                              
	                              label: "Mã:" + item.ma + " , Tên : " + item.ten + " , đơn vị:" + item.donvi+", thuế xuất:"+item.thuexuat,
	                              value: item.ma ,
	                              recordKey:  JSON.stringify(item)
	                              
	                             };
	                     }))
	                 },

	             });
	         },
	         select: function (e, i) 
	         {
	        	 
	        	 	var obj = JSON.parse(i.item.recordKey);
	           		var num = this.id.replace("spMa_", "");
	            	$('#spTen_'+num).val(obj.ten);
	            	$('#spvat_'+num).val(obj.thuexuat);
	            	
	            	
	            	if(obj.isHopdongChinh =='1')
	            	{
	            		$('#dongia_'+num).val(obj.dongiaHopdongChinh);
	            		$('#dongia_'+num).attr('readonly', true);
	            		
	            		var dvHTML = '<input type="text" name="donvi"   value="'+obj.donviHopdongChinh+'" style="width: 100%" readonly="readonly"> ';
	            		$('#divDonvi_'+num).html(dvHTML);
	            	}
	            	else
	            	{
	            		getDonViDoLuong(num,obj.ma);
	            	}
	            	
	            
	         },
	         close: function () {},
	         minLength: 1
	     });
	});	
	
</script>

<%
	try
	{
		
		lsxBean.DBclose(); 
	}
	catch(Exception er){ }

	} %>
</form>
</BODY>
</html>