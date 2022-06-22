<%@page import="geso.dms.center.util.Utility"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@page import="java.util.ArrayList"%>
<%@page import="java.sql.SQLException"%>
<%@ page  import = "geso.dms.center.beans.hangtralai.imp.*" %>
<%@ page  import = "geso.dms.center.beans.hangtralai.*" %>
<%@ page  import = "java.sql.ResultSet" %>
<%@ page  import = "java.util.Hashtable" %>
<%@ page  import = "java.util.List" %>
<%@ page  import = "java.text.NumberFormat" %>
<%@ page  import = "java.text.DecimalFormat" %>

<% 
	IErpHangTraLaiNpp lsxBean = (IErpHangTraLaiNpp)session.getAttribute("lsxBean");  
	ResultSet khoxuatRs = lsxBean.getKhoXuatRs();
	ResultSet nppRs = lsxBean.getDtRs();
	ResultSet dvtRs = lsxBean.getDvtRs();
	ResultSet kbhRs = lsxBean.getKbhRs();
	Hashtable<String, String> sanpham_soluong = lsxBean.getSanpham_Soluong();
	
	String[] spMa = lsxBean.getSpMa(); 
	String[] spTen = lsxBean.getSpTen();
	String[] spDonvi = lsxBean.getSpDonvi();
	String[] spSoluong = lsxBean.getSpSoluong();
	String[] spGianhap = lsxBean.getSpGianhap();
	String[] spTonkho = lsxBean.getSpTonkho();
	String[] spSoLo = lsxBean.getSpSolo();
	String[] spNgayHetHan = lsxBean.getSpNgayHetHan();
	String[] spGhiChu = lsxBean.getSpGhiChu();
	String[] spVat = lsxBean.getSpVat(); 
	NumberFormat formater = new DecimalFormat("##,###,###");
	
%>
<% ResultSet khRs = lsxBean.getKhRs(); %>

<% NumberFormat formatter = new DecimalFormat("#,###,###"); %>


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
<script type="text/javascript" src="../scripts/AjaxHangTraLaiNpp.js"></script>

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
		var spHansd = document.getElementsByName("spHansd");
		var spMa = document.getElementsByName("spMa");
		var spTen = document.getElementsByName("spTen");  
		var donvi = document.getElementsByName("donvi");
		var soluong = document.getElementsByName("soluong");
		var tonkho = document.getElementsByName("tonkho");
		var dongia = document.getElementsByName("dongia");
		var thanhtien = document.getElementsByName("thanhtien");
		var spVat = document.getElementsByName("spVat");
		
		var trongluong = document.getElementsByName("spTrongLuong");
		var thetich = document.getElementsByName("spTheTich");
		
		var spQuyDoi = document.getElementsByName("spQuyDoi");
		
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
					
					spVat.item(i).value = sp.substring(0, parseInt(sp.indexOf("] [")));
					sp = sp.substr(parseInt(sp.indexOf("] [")) + 3); 
										
					dongia.item(i).value = '0'; 
					thanhtien.item(i).value = '0'; 
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
				spHansd.item(i).value = "";
				spVat.item(i).value = "";
			
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
		var thueVAT = document.getElementsByName("spVat");
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
				
				tongtien += ( parseFloat(dongia.item(i).value.replace(/,/g,"")) * parseFloat(soluong.item(i).value.replace(/,/g,"")) );
				tongtienCK += parseFloat( _ck );
				tongtienVAT += ( parseFloat(dongia.item(i).value.replace(/,/g,"")) * parseFloat(soluong.item(i).value.replace(/,/g,"")) - parseFloat(_ck) ) * ( parseFloat(_thueVAT) / 100 );
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
		var tongtienSAUVAT = parseFloat(tongtienSAUCK) + ( parseFloat(tongtienVAT) );
		document.getElementById("txtSauVAT").value = DinhDangTien(tongtienSAUVAT);
		
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
	


	 
</script>

<link href="../css/select2.css" rel="stylesheet" />
<script src="../scripts/select2.js"></script>
<script>
	$(document).ready(function()
	{
		$(".select2").select2();
	});
</script>

</head>
<BODY leftmargin="0" bottommargin="0" topmargin="0" rightmargin="0">
<form name="hctmhForm" method="post" action="../../ErpHangTraLaiTTUpdateSvl">
<input type="hidden" name="userId" value='<%= userId %>'>
<input type="hidden" name="action" value='1'>
<input type="hidden" name="nppId" value='<%= lsxBean.getNppId() %>'>
<input type="hidden" name="kenhId" value='<%= lsxBean.getKbhId() %>'>
<input type="hidden" name="khoxuat" value='<%= lsxBean.getKhoXuatId() %>'>

<div id="main" style="width:100%; padding-left:2px">
	<div align="left" id="header" style="width:100%; float:none">
    	<div style="width:60%; padding:5px; float:left" class="tbnavigation">
        	Quản lý tồn kho > Xuất chuyển hàng > Nhập hàng trả lại  > Tạo mới
        </div>
        <div align="right" style="padding:5px" class="tbnavigation">
        	<%=Utility.GLanguage("Chào mừng bạn",session,jedis) %>  <%= userTen %> &nbsp;
        </div>
    </div>
  
  	<div align="left" id="button" style="width:100%; height:32px; padding:0px; float:none" class="tbdarkrow">
    	<A href= "../../ErpHangTraLaiNppSvl?userId=<%= userId %>" >
        	<img src="../images/Back30.png" alt="Quay ve"  title="Quay ve" border="1px" longdesc="Quay ve" style="border-style:outset"></A>
        <span id="btnSave">
	        <A href="javascript:saveform()" >
	        	<IMG src="../images/Save30.png" title="Luu lai" alt="Luu lai" border ="1px" style="border-style:outset"></A>
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
    	<legend class="legendtitle"> Nhập hàng trả lại  </legend>
        	<div style="float:none; width:100%" align="left">
            <TABLE width="100%" cellpadding="4" cellspacing="0">							
               <TR>
                    <TD class="plainlabel"  >Ngày trả </TD>
                    <TD  class="plainlabel" >
                    	<input type="text" class="days" readonly="readonly"  name="ngaychuyen" value="<%= lsxBean.getNgayyeucau() %>"/></TD>
                    	
                    	<TD class="plainlabel" > Ngày HĐ  </TD>
                    	<TD class="plainlabel" >
                    		<input type="text" class="days" readonly="readonly"  name="ngayhoadon" value="<%= lsxBean.getNgayHoaDon() %>"/>
                    	</TD>
                    	
                    	
                </TR>
                
               
                
                 <TR>
                     <TD class="plainlabel"  >Đối tượng trả </TD>
                   <TD class="plainlabel"    >
                   			<select name = "doituong" class="select2" style="width: 200px" onchange="submitform();" >
                   		 <%  if (lsxBean.getDoituong().equals("1")){%>
                    		<option value=""  ></option>
                    		<option value="1" selected="selected">Khách Hàng</option>
                    		<%} else if (lsxBean.getDoituong().equals("")){%>
                    		<option value=""  selected="selected"></option>
                    		<option value="1" >Khách Hàng</option>
                    		<%} %>
                    	</select>
                   </TD>
                 
                  <%if(lsxBean.getDoituong().equals("1")){ %>
                	<TD class="plainlabel" >Khách hàng </TD>
                    <TD class="plainlabel" >
                    	<select name = "khId" class="select2" style="width: 200px" onchange="submitform();" >
                    		<option value=""> </option>
                        	<%
                        		if(khRs != null)
                        		{
                        			try
                        			{
                        			while(khRs.next())
                        			{  
                        			if( khRs.getString("pk_seq").equals(lsxBean.getKhId())){ %>
                        				<option value="<%= khRs.getString("pk_seq") %>" selected="selected" ><%= khRs.getString("ten") %></option>
                        			<%}else { %>
                        				<option value="<%= khRs.getString("pk_seq") %>" ><%= khRs.getString("ten") %></option>
                        		 <% } } khRs.close();} catch(SQLException ex){}
                        		}
                        	%>
                    	</select>
                    </TD> 
                    <%}else if (lsxBean.getDoituong().equals("0")){ %>
                    <TD class="plainlabel" >Đối tác </TD>
                    <TD class="plainlabel" >
                    	<select name = "dtId" class="select2" style="width: 200px" onchange="submitform();" >
                    		<option value=""> </option>
                        	<%
                        		if(nppRs != null)
                        		{
                        			try
                        			{
                        			while(nppRs.next())
                        			{  
                        			if( nppRs.getString("pk_seq").equals(lsxBean.getDtId())){ %>
                        				<option value="<%= nppRs.getString("pk_seq") %>" selected="selected" ><%= nppRs.getString("ten") %></option>
                        			<%}else { %>
                        				<option value="<%= nppRs.getString("pk_seq") %>" ><%= nppRs.getString("ten") %></option>
                        		 <% } } nppRs.close();} catch(SQLException ex){}
                        		}
                        	%>
                    	</select>
                    </TD> 
                    <%}else if (lsxBean.getDoituong().equals("")){ %>
                     <TD class="plainlabel" colspan="2"></TD>
                     
                    <%} %>
                                 	                   
                </TR>
                
                <TR>
                	<TD class="plainlabel"   width="130px" >Kho nhận</TD>
                    <TD class="plainlabel"   width="230px" >
                    	<select name = "khoxuatId" style="width: 200px" class="select2"  >
                        	<%
                        		if(khoxuatRs != null)
                        		{
                        			try
                        			{
                        			while(khoxuatRs.next())
                        			{  
                        			if( khoxuatRs.getString("pk_seq").equals(lsxBean.getKhoXuatId())){ %>
                        				<option value="<%= khoxuatRs.getString("pk_seq") %>" selected="selected" ><%= khoxuatRs.getString("ten") %></option>
                        			<%}else { %>
                        				<option value="<%= khoxuatRs.getString("pk_seq") %>" ><%= khoxuatRs.getString("ten") %></option>
                        		 <% } } khoxuatRs.close();} catch(SQLException ex){}
                        		}
                        	%>
                    	</select>
                    </TD>   
                    <TD class="plainlabel" width="120px" ><%=Utility.GLanguage("Kênh bán hàng",session,jedis) %></TD>
                    <TD class="plainlabel"  >
                    	<select name = "kbhId" style="width: 200px" class="select2"  >
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
                    <TD class="plainlabel" >Số HĐ </TD>
                    <TD  class="plainlabel" >
                    	<input type="text"   name="sohoadon" value="<%= lsxBean.getSoHoaDon()  %>"/></TD>
                    
                    <TD class="plainlabel" > Ký Hiệu  </TD>
                    	<TD class="plainlabel" >
                    	<input type="text"  name="kyhieu" value="<%= lsxBean.getKyHieu() %>"/>
						
						</TR>           
                
                
              
                <TR style="display:none" >
                    <TD class="plainlabel" >Tổng thành tiền </TD>
                    <TD class="plainlabel"  colspan="3" >
                    	<input type="text" readonly="readonly"  id="txtBVAT" value="" style="text-align: right;"  />
                    	
                    
                    	<input type="hidden"  value="" id="txtPTChietKhau" style="text-align: right;" name="ptChietkhau" onkeypress="return keypress(event);" onkeyup="Dinhdang(this);" />
                    </TD>
                </TR>
                
                <TR style="display:none" >
                    <TD class="plainlabel" >Tổng tiền chiết khấu </TD>
                    <TD class="plainlabel"  >
                    	<input type="text" readonly="readonly"  value="" id="txtTongCK" style="text-align: right; width: 200px; " />
                    </TD>
                    	
                    <TD class="plainlabel" >Tổng tiền sau CK </TD>
                    <TD class="plainlabel" >
                    	<input type="text" readonly="readonly"  value="" id="txtTongSauCK" style="text-align: right;" />
                    </TD>
                </TR>
                
                <TR  >
                    <TD class="plainlabel" >Tổng tiền VAT </TD>
                    <TD class="plainlabel"  >
                    	<input type="text" value="" id="txtVAT" style="text-align: right;" name="ptVat" onkeypress="return keypress(event);" onkeyup="Dinhdang(this);"  readonly='readonly'  />
                    </TD>
                    	
                    <TD class="plainlabel"  style="color: red;" >Tổng tiền sau VAT </TD>
                    <TD class="plainlabel" >
                    	<input type="text" readonly="readonly"  value="" id="txtSauVAT" style="text-align: right;" />
                    </TD>
                </TR>
                
                 <tr>
                <TD class="plainlabel" > Số  </TD>
                    	<TD class="plainlabel" colspan="3" >
                    		<input type="text"  name="so" value=""/>
                    	</TD>
                 </tr>
                
                   <tr>
                
                 <TD class="plainlabel" >Ghi chú </TD>
                    <TD  class="plainlabel" colspan="3" >
                    	<input type="text"  name="ghichu" value="<%= lsxBean.getGhichu() %>" style="width: 400px" />
                    </TD>
                    
                   
                </TR>
               
                 
                
                
            </TABLE>
			
			
			 
			 <table cellpadding="0px" cellspacing="1px" width="100%">
				<tr class="tbheader">
					<th align="center" width="8%" >  Mã sản phẩm</th>
					<th align="center" width="28%" >  Tên sản phẩm</th>
					<th align="center" width="8%" >  Đơn vị</th>					
					<th align="center" width="10%" >  Số lượng</th>
					<th align="center" width="10%" >  Đơn giá</th>
					<th align="center" width="10%" >  Số lô</th>
					<th align="center" width="10%" >  Ngày hết hạn</th>
					<th align="center" width="12%" >  Thành tiền</th>
					<th align="center" width="5%" >  Ghi chú</th>
				</tr>

				<%
					boolean choCHON_DVT = false;
					int count = 0;
					if(spMa != null )
					{
						for(int i = 0; i < spMa.length; i++)
						{%>
					
						<tr>
							<td>
								<input type="text" name="spMa" value="<%= spMa[i] %>" style="width: 100%"  onkeyup="ajax_showOptions(this,'nhapkho',event)" AUTOCOMPLETE="off"  >
								<input type="hidden" name="spTrongLuong" value="" > 
								<input type="hidden" name="spTheTich" value="" > 
								<input type="hidden" name="spQuyDoi" value=""  >
								<input type="hidden" name="spHansd" value="1"  >
								<input type="hidden" name="spVat" value="<%=spVat[i] %>"  >
								<input type="hidden" name="chietkhau" value="0"  >
							</td>
							<td> <input type="text" name="spTen" value="<%= spTen[i] %>" style="width: 100%" readonly="readonly"> </td>
							
							<td>
							<%if(!choCHON_DVT) { %>
									<input type="text" name="donvi" value="<%= spDonvi[i] %>" style="width: 100%" readonly="readonly">
								<% } else { %>
									<select name="donvi" style="width: 100%" onchange="CapNhatGia(this, <%= count %>);"   >
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
							
							<td > <input type="text" name="soluong" value="<%= spSoluong[i] %>" style="width: 100%; text-align: right;" onkeypress="return keypress(event);" onkeyup="Dinhdang(this);"  > 
									
							
							</td>
							<td > <input type="text" name="dongia" value="<%= spGianhap[i] %>" style="width: 100%; text-align: right;"   > </td>
							<td > <input type="text" name="solo" value="<%= spSoLo[i] %>" style="width: 100%; text-align: right;"  > </td>
							<td > <input type="text" name="ngayhethan" value="<%= spNgayHetHan[i] %>" style="width: 100%; text-align: right;" class="days"   > </td>
							<td > <input type="text" name="thanhtien" value="" style="width: 100%; text-align: right;" readonly="readonly" > </td>
							
							 <td align="center">
           	 					<a href="" id="ghichu_<%= i %>" rel="subcontent<%= i %>">
	           	 							<img alt="Tên sản phẩm hóa đơn" src="../images/vitriluu.png"></a>
	           	 				<DIV id="subcontent<%= i %>" style="position:absolute; visibility: hidden; border: 9px solid #80CB9B;
				                             background-color: white; width: 450px; padding: 4px;">
				                    <table width="90%" align="center">
				                        <tr>
				                            <th width="100px">Ghi chú</th>
				                        </tr>
	                        			<tr>
				                            <td>
				                            	<input type="text" style="width: 100%;" name="spGhiChu" value="<%= spGhiChu[i] %>" />
				                            </td>
				                            
				                        </tr>
				                    </table>
				                     <div align="right">
				                     	<label style="color: red" ></label>
				                     	&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
				                     	<a href="javascript:dropdowncontent.hidediv('subcontent<%= i %>')">Hoàn tất</a>
				                     </div>
				                           <script type="text/javascript">
						            	dropdowncontent.init('ghichu_<%= i %>', "left-top", 300, "click");
						            </script>
				                </DIV>								               
           	 		     </td>
							
						</tr>	
							
					<% count ++; } } %>
				
				<% for(int i = count; i < 50; i++) { %>
					
					<tr>
						<td>
							<input type="text" name="spMa" value="" style="width: 100%"  onkeyup="ajax_showOptions(this,'nhapkho',event)" AUTOCOMPLETE="off"  > 
						</td>
						<td> <input type="text" name="spTen" value="" style="width: 100%" readonly="readonly"> 
						
									<input type="hidden" name="spTrongLuong" value="" > 
									<input type="hidden" name="spTheTich" value="" > 
									<input type="hidden" name="spQuyDoi" value=""  >
									<input type="hidden" name="spHansd" value="1"  >
									<input type="hidden" name="spVat" value="0"  >
									<input type="hidden" name="chietkhau" value="0"  >  
						
						</td>
						
						<td>
						<%if(!choCHON_DVT) { %>
									<input type="text" name="donvi" value="" style="width: 100%" readonly="readonly">
								<% } else { %>
									<select name="donvi" style="width: 100%" onchange="CapNhatGia(this, <%= count %>);"   >
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
								
							<td > <input type="text" name="soluong" value="" style="width: 100%; text-align: right;" onkeypress="return keypress(event);" onkeyup="Dinhdang(this);"  > </td>
							<td > <input type="text" name="dongia" value="" style="width: 100%; text-align: right;"   > </td>
							<td > <input type="text" name="solo" value="" style="width: 100%; text-align: right;"     > </td>
							<td > <input type="text" name="ngayhethan" value="" style="width: 100%; text-align: right;" class="days"  > </td>
							<td > <input type="text" name="thanhtien" value="" style="width: 100%; text-align: right;" readonly="readonly" > </td>
							
							<td align="center">
           	 					<a href="" id="ghichu_<%= i %>" rel="subcontent<%= i %>">
	           	 							<img alt="Tên sản phẩm hóa đơn" src="../images/vitriluu.png"></a>
	           	 				<DIV id="subcontent<%= i %>" style="position:absolute; visibility: hidden; border: 9px solid #80CB9B;
				                             background-color: white; width: 450px; padding: 4px;">
				                    <table width="90%" align="center">
				                        <tr>
				                            <th width="100px">Ghi chú</th>
				                        </tr>
	                        			<tr>
				                            <td>
				                            	<input type="text" style="width: 100%;" name="spGhiChu" value="" />
				                            </td>
				                            
				                        </tr>
				                    </table>
				                     <div align="right">
				                     	<label style="color: red" ></label>
				                     	&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
				                     	<a href="javascript:dropdowncontent.hidediv('subcontent<%= i %>')">Hoàn tất</a>
				                     </div>
				                           <script type="text/javascript">
						            	dropdowncontent.init('ghichu_<%= i %>', "left-top", 300, "click");
						            </script>
				                </DIV>								               
           	 		     </td>
					</tr>	
				<% } %>	
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
		lsxBean.DBclose(); 
		
	}
	catch(Exception er){er.printStackTrace(); }

	} %>
</form>
</BODY>
</HTML>