<%@page import="geso.dms.center.util.Utility"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@page import="java.util.ArrayList"%>
<%@page import="java.sql.SQLException"%>
<%@ page  import = "geso.dms.center.beans.dieuchinhtonkho.*" %>
<%@ page  import = "geso.dms.center.beans.dieuchinhtonkho.imp.*" %>
<%@ page  import = "java.sql.ResultSet" %>
<%@ page  import = "java.util.Hashtable" %>
<%@ page  import = "java.util.List" %>
<%@ page  import = "java.text.NumberFormat" %>
<%@ page  import = "java.text.DecimalFormat" %>

<% 
	IErpChuyenkhoTT lsxBean = (IErpChuyenkhoTT)session.getAttribute("lsxBean");  
	ResultSet khoxuatRs = lsxBean.getKhoXuatRs();
	ResultSet nppRs = lsxBean.getNppRs();
	ResultSet dvtRs = lsxBean.getDvtRs();
	ResultSet kbhRs = lsxBean.getKbhRs();
	Hashtable<String, String> sanpham_soluong = lsxBean.getSanpham_Soluong();
	
	String[] spMa = lsxBean.getSpMa(); 
	String[] spTen = lsxBean.getSpTen();
	String[] spDonvi = lsxBean.getSpDonvi();
	String[] spSoluong = lsxBean.getSpSoluong();
	String[] spGianhap = lsxBean.getSpGianhap();
	String[] spTonkho = lsxBean.getSpTonkho();
	/* String[] spTrongluong = lsxBean.getSpTrongluong();
	String[] spThetich = lsxBean.getSpThetich();
	String[] spQuyDoi = lsxBean.getSpQuyDoi();
	String[] spSCHEME = lsxBean.getSpScheme(); */
	
	NumberFormat formater = new DecimalFormat("##,###,###");
	
%>
<% Utility util = new Utility(); %>
<%String url = util.getChuyenNguUrl("ErpChuyenkhoTTSvl", "&loaidonhang="+lsxBean.getLoaidonhang()+"",session); %>
<% String userId = (String) session.getAttribute("userId");  %>
<% String userTen = (String) session.getAttribute("userTen");  
	String sum = (String) session.getAttribute("sum");
	
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
<script type="text/javascript" src="../scripts/erp-SpListNhapKho.js"></script>

<script type="text/javascript">
	$(document).ready(function() {		
		
		<%if( lsxBean.getPageY() != 0 ){%>		
		$('html, body').animate({scrollTop: <%= lsxBean.getPageY() -200   %>}, "fast");			
		<%}%>
	});	
	
	
	
</script>


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
		var tonkho = document.getElementsByName("tonkho");
		var soluong = document.getElementsByName("soluong");
		var dongia = document.getElementsByName("dongia");
		var thanhtien = document.getElementsByName("thanhtien");
		
		var trongluong = document.getElementsByName("spTrongLuong");
		var thetich = document.getElementsByName("spTheTich");
		var spQuyDoi = document.getElementsByName("spQuyDoi");
		var tongtien=0;
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

					//THUE VAT THEO NGANH HANG
					sp = sp.substr(parseInt(sp.indexOf("] [")) + 3);
					//alert('SP LA: ' + sp);
					
					tonkho.item(i).value = DinhDangTien ( sp.substring(0, parseInt(sp.indexOf("] ["))) );
					sp = sp.substr(parseInt(sp.indexOf("] [")) + 3);
					
					dongia.item(i).value = DinhDangTien ( sp.substring(0, parseInt(sp.indexOf("]"))) );
				}
				if(soluong.item(i).value != "")
				{
					var sl = parseFloat(soluong.item(i).value.replace(/,/g,""));
					var gia = parseFloat(dongia.item(i).value.replace(/,/g,""));
					thanhtien.item(i).value = DinhDangTien ( sl * gia ); 
					tongtien+=( sl * gia);
				}
			}
			else
			{
				spMa.item(i).value = "";
				spTen.item(i).value = "";
				donvi.item(i).value = "";
				tonkho.item(i).value = "";
				soluong.item(i).value = "";
				dongia.item(i).value = "";
				thanhtien.item(i).value = "";	
				trongluong.item(i).value = "";	
				thetich.item(i).value = "";	
				spQuyDoi.item(i).value = "";
			}
		}	
		document.getElementById("tongtien").value = DinhDangTien( tongtien );
		
		//TinhTien();
		setTimeout(replaces, 300);
	}
	
	 function saveform()
	 {	 
		 /* var r = confirm("Bạn chắc chắn muốn duyệt chuyển kho này?");
		 if (r == false) {
		     return;
		 } */
			
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
				
				alert('Số lượng xuất ( ' + totalXUAT + ' ) không được phép vượt quá số lượng đặt ( ' + soluong.item(i).value + ' )');
			}

			soluong2.item(i).value = soluong.item(i).value;
		}
		
	}
	 
	

	function nghiepvuChange(i)
	{
		
		var nv =  $("#soluong_"+i);
		var divPosition = nv.offset();	
		console.log("divPosition" + divPosition.top);
		document.forms["hctmhForm"].pageY.value =divPosition.top ;
		 document.forms['hctmhForm'].action.value='submit';
	    document.forms["hctmhForm"].submit();
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
<form name="hctmhForm" method="post" action="../../ErpChuyenkhoTTUpdateSvl">
<% redis.clients.jedis.Jedis jedis =  geso.dms.center.util.Utility.getJedis(); %><% geso.dms.center.util.Csrf csdr=new geso.dms.center.util.Csrf(request,response,true,false,true);%>
<input type="hidden" name="userId" value='<%= userId %>'>
<input type="hidden" name="action" value='1'>
<input type="hidden" name="loaidonhang" value='<%= lsxBean.getLoaidonhang() %>'>

 <input type="hidden" name="<%=csdr.get_tokenName() %>" value='<%=csdr.get_tokenValue() %>'>
<input type="hidden" name="pageY" value='0'>

<div id="main" style="width:100%; padding-left:2px">
	<div align="left" id="header" style="width:100%; float:none">
    	<div style="width:60%; padding:5px; float:left" class="tbnavigation">
        	<%=url %> > <%=Utility.GLanguage("Tạo mới",session,jedis) %>
        </div>
        <div align="right" style="padding:5px" class="tbnavigation">
        	<%=Utility.GLanguage("Chào mừng bạn",session,jedis) %>  <%= userTen %> &nbsp;
        </div>
    </div>
  
  	<div align="left" id="button" style="width:100%; height:32px; padding:0px; float:none" class="tbdarkrow">
    	<A href= "../../RouterSvl?task=<%= Utility.dongMa(getServletContext().getInitParameter("RedirectNoScript") +"ErpChuyenkhoTTSvl?userId="+ userId+"&loaidonhang="+lsxBean.getLoaidonhang()) %>" >
        	<img src="../images/Back30.png" alt="Quay ve"  title="Quay ve" border="1px" longdesc="Quay ve" style="border-style:outset"></A>
        <span id="btnSave">
	        <A href="javascript:saveform()" >
	        	<IMG src="../images/Save30.png" title="Luu lai" alt="Luu lai" border ="1px" style="border-style:outset"></A>
        </span>
    </div>
  	
  	<div align="left" style="width:100%; float:none; clear:left">
  		<fieldset>
    		<legend class="legendtitle"> <%=Utility.GLanguage("Thông báo",session,jedis) %></legend>
    		<textarea name="dataerror"  rows="1" readonly="readonly" style ="width:100%"><%= lsxBean.getMsg() %></textarea>
		         <% lsxBean.setMsg(""); %>
    	</fieldset>
  	</div>
  	
    <div align="left" style="width:100%; float:none; clear:left">
    <fieldset>
    	<legend class="legendtitle"><%=Utility.GLanguage("Xuất chuyển nội bộ",session,jedis) %> </legend>
        	<div style="float:none; width:100%" align="left">
            <TABLE width="100%" cellpadding="4" cellspacing="0">							
                <TR>
                    <TD class="plainlabel" ><%=Utility.GLanguage("Ngày xuất",session,jedis) %> </TD>
                    <TD  class="plainlabel" >
                    	<input type="text" class="days" readonly="readonly"  name="ngaychuyen" value="<%= lsxBean.getNgayyeucau() %>" onchange="submitform();"/></TD>
                    	
                    <TD class="plainlabel" ><%=Utility.GLanguage("Số chứng từ",session,jedis) %>  <FONT class="erroralert"> *</FONT>   </TD>
                    <TD colspan="3" class="plainlabel" >
                    	<input type="text"   name="sochungtu" value="<%= lsxBean.getSoChungTu() %>"/></TD>
                    	
                </TR>
                
                <TR>
                	<TD class="plainlabel" width="15%" ><%=Utility.GLanguage("Kho xuất hàng",session,jedis) %></TD>
                    <TD class="plainlabel"   width="230px" >
                    	<select name = "khoxuatId" style="width: 200px" class="select2"  onchange="submitform();" >
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
                    <TD class="plainlabel" width="15%"><%=Utility.GLanguage("Kênh bán hàng",session,jedis) %></TD>
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
                
                <tr>
                	<TD class="plainlabel" ><%=Utility.GLanguage("Đối tượng nhận",session,jedis) %></TD>
                    <TD class="plainlabel" >
                    	<select name = "nppId" style="width: 200px" class="select2"  onchange="submitform();" >
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
                
                    <TD class="plainlabel" ><%=Utility.GLanguage("Ghi chú",session,jedis) %> </TD>
                    <TD colspan="3" class="plainlabel" >
                    	<input type="text"  name="ghichu" value="<%= lsxBean.getGhichu() %>" style="width: 400px" />
                    </TD>
                </TR>
               <TR >
               		<TD class="plainlabel" colspan="4" ><%=Utility.GLanguage("THÔNG TIN IN PHIẾU XUẤT CHUYỂN NỘI BỘ",session,jedis) %>: </TD>
               </TR>
                <TR>
                	 <TD class="plainlabel" ><%=Utility.GLanguage("Lệnh điều động số",session,jedis) %> </TD>
                    <TD class="plainlabel" >
                    	<input type="text"  name="lenhdieudong" value="<%= lsxBean.getLenhdieudong()%>" style="width: 200px" />
                    </TD>
                    
                    <TD class="plainlabel" ><%=Utility.GLanguage("Của",session,jedis) %> </TD>
                    <TD class="plainlabel" >
                    	<input type="text"  name="lddcua" value="<%= lsxBean.getLDDcua() %>" style="width: 200px" />
                    </TD>
                </TR>
               
                <TR>
                	 <TD class="plainlabel" ><%=Utility.GLanguage("Về việc",session,jedis) %> </TD>
                    <TD class="plainlabel" >
                    	<input type="text"  name="lddveviec" value="<%= lsxBean.getLDDveviec() %>" style="width: 200px" />
                    </TD>
                    
                    <TD class="plainlabel" ><%=Utility.GLanguage("Ngày lệnh điều động",session,jedis) %> </TD>
                    <TD class="plainlabel" >
                    	<input type="text"  name="ngaydieudong" class="days" value="<%= lsxBean.getNgaydieudong() %>" style="width: 200px" />
                    </TD>
                </TR>
                
                <TR>
                	 <TD class="plainlabel" ><%=Utility.GLanguage("Người vận chuyển",session,jedis) %></TD>
                    <TD class="plainlabel" >
                    	<input type="text"  name="nguoivanchuyen" value="<%= lsxBean.getNguoivanchuyen() %>" style="width: 200px" />
                    </TD>
                    
                    <TD class="plainlabel" ><%=Utility.GLanguage("Phương tiện vận chuyển",session,jedis) %> </TD>
                    <TD class="plainlabel" >
                    	<input type="text"  name="ptvanchuyen" value="<%= lsxBean.getPtvanchuyen() %>" style="width: 200px" />
                    </TD>
                </TR>
                
                 <TR>
                	 <TD class="plainlabel" ><%=Utility.GLanguage("Số hợp đồng",session,jedis) %></TD>
                    <TD class="plainlabel" >
                    	<input type="text"  name="sohopdong" value="<%= lsxBean.getSohopdong() %>" style="width: 200px" />
                    </TD>
                    
                    <TD class="plainlabel" ><%=Utility.GLanguage("Ngày hợp đồng",session,jedis) %> </TD>
                    <TD class="plainlabel" >
                    	<input type="text"  name="ngayhopdong" class="days" value="<%= lsxBean.getNgayhopdong() %>" style="width: 200px" />
                    </TD>
                </TR>
                
                 <TR>
                	 <TD class="plainlabel" ><%=Utility.GLanguage("Tổng tiền",session,jedis) %></TD>
                    <TD class="plainlabel" >
                    	<input type="text"  name="tongtien"  id="tongtien" style="width: 200px" />
                    </TD>
                    
                    <TD class="plainlabel" >  </TD>
                    <TD class="plainlabel" >
                    
                    </TD>
                </TR>
                
                
                <TR>
                	<TD class="plainlabel" colspan="4" >
                		<a class="button2" href="javascript:submitform()">
							<img style="top: -4px;" src="../images/New.png" alt=""><%=Utility.GLanguage("Xem số lô đề xuất",session,jedis) %></a>
                	</TD>
                </TR>
                
            </TABLE>
			<hr />
			
			<table cellpadding="0px" cellspacing="1px" width="100%">
				<tr class="tbheader">
					<th align="center" width="15%" ><%=Utility.GLanguage("Mã sản phẩm",session,jedis) %></th>
					<th align="center" width="30%" ><%=Utility.GLanguage("Tên sản phẩm",session,jedis) %></th>
					<th align="center" width="10%" ><%=Utility.GLanguage("Đơn vị",session,jedis) %></th>
					<th align="center" width="10%" ><%=Utility.GLanguage("Tồn kho",session,jedis) %></th>
					<th align="center" width="10%" ><%=Utility.GLanguage("Số lượng",session,jedis) %></th>
					<th align="center" width="5%" ><%=Utility.GLanguage("Số lô",session,jedis) %></th>
					<!-- <th align="center" width="10%" >Đơn giá</th>
					<th align="center" width="10%" >Thành tiền</th> -->
				</tr>

				<%
					int count = 0;
					if(spMa != null)
					{
						for(int i = 0; i < spMa.length; i++)
						{%>
					
						<tr>
							<td>
								<input type="text" name="spMa" value="<%= spMa[i] %>" style="width: 100%"  onkeyup="ajax_showOptions(this,'nhapkho',event)" AUTOCOMPLETE="off"  > 
								<input type="hidden" name="spTrongLuong" value="1" > 
								<input type="hidden" name="spTheTich" value="1" > 
								<input type="hidden" name="spQuyDoi" value="1"  >
							</td>
							<td> <input type="text" name="spTen" value="<%= spTen[i] %>" style="width: 100%" readonly="readonly"> </td>
							<td>
								<%-- <select name="donvi" style="width: 100%"  >
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
								 
								  <input  type="text" name="donvi" value="<%= spDonvi[i] %>" style="width: 100%" readonly="readonly" />
							</td>
							<td > <input readonly type="text" name="tonkho" value="<%= spTonkho[i] %>" style="width: 100%; text-align: right;" onkeypress="return keypress(event);" onkeyup="Dinhdang(this);" > </td>
							<td ><div id="soluong_<%=i %>"> <input type="text"  name="soluong" value="<%= spSoluong[i] %>" style="width: 100%; text-align: right;" onkeypress="return keypress(event);"  onchange="nghiepvuChange('<%=i%>');" ></div> </td>
							<td align="center" > 
								<% if(spSoluong[i].trim().length() > 0) { %>
									<a href="" id="scheme_<%= spMa[i] %>" rel="subcontent_<%= spMa[i] %>">
			           	 				&nbsp; <img alt="Chọn số lô" src="../images/vitriluu.png"></a>
			           	 		
		           	 		 		<DIV id="subcontent_<%= spMa[i] %>" style="position:absolute; visibility: hidden; border: 9px solid #80CB9B;
					                             background-color: white; width: 450px; max-height:300px; overflow:auto; padding: 4px;">
					                    <table width="95%" align="center">
					                    	<tr>
					                    		<td ><b><%=Utility.GLanguage("Tổng xuất",session,jedis) %></b></td>
					                    		<td colspan="3" > <input type="text" name="soluong2" value="<%= spSoluong[i] %>" style="width: 100px; text-align: right;" readonly="readonly" >	</td>
					                    	</tr>
					                        <tr>
					                            <th width="100px" style="font-size: 11px"><%=Utility.GLanguage("Số lượng",session,jedis) %></th>
					                            <th width="100px" style="font-size: 11px"><%=Utility.GLanguage("Số lô",session,jedis) %></th>
					                            <th width="100px" style="font-size: 11px"><%=Utility.GLanguage("Ngày hết hạn",session,jedis) %></th>
					                             <th width="100px" style="font-size: 11px"><%=Utility.GLanguage("Ngày nhập kho",session,jedis) %></th>
					                            <th width="100px" style="font-size: 11px"><%=Utility.GLanguage("Tồn kho",session,jedis) %></th>
					                        </tr>
			                            	<%
			                            		ResultSet spRs = lsxBean.getSoloTheoSp(spMa[i], spSoluong[i],lsxBean.getNgayyeucau());
				                        		if(spRs != null)
				                        		{
				                        			while(spRs.next())
				                        			{
				                        				String tudeXUAT = "";
				                        				if(spRs.getString("tuDEXUAT").trim().length() > 0)
				                        					tudeXUAT = formater.format(spRs.getDouble("tuDEXUAT"));
				                        				
				                        				String temID = spMa[i];
				                        			%>
				                        			
				                        			<tr>
				                        				<td>
				                        				<% if(sanpham_soluong.get(spMa[i] + "__" + spRs.getString("SOLO")+"__" + spRs.getString("ngayhethan")+"__" + spRs.getString("ngaynhapkho")) != null ) { %>
				                        					<input type="text" style="width: 100%;text-align: right;" name="<%= temID %>_spSOLUONG" value="<%= formater.format(Double.parseDouble( sanpham_soluong.get( spMa[i] + "__" + spRs.getString("SOLO")+"__" + spRs.getString("ngayhethan")+"__" + spRs.getString("ngaynhapkho") ))) %>" onkeyup="CheckSoLuong_DeXuat(this);" >
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
				                        					<input type="text" style="width: 100%;text-align: center;" name="<%= temID %>_spNGAYNHAPKHO" value="<%= spRs.getString("ngaynhapkho") %>" readonly="readonly">
				                        				</td>
				                        				<td>
				                        					<input type="text" style="width: 100%;text-align: right;" name="<%= temID %>_spTONKHO" value="<%= formater.format(spRs.getDouble("AVAILABLE")) %>" readonly="readonly">
				                        				</td>
				                        			</tr>
				                        			
				                        		 <% } } %>
				                        		 
					                     </table>
					                     <div align="right">
					                     	<label style="color: red" ></label>
					                     	&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
					                     	<a href="javascript:dropdowncontent.hidediv('subcontent_<%= spMa[i] %>')" > <%=Utility.GLanguage("Đóng lại",session,jedis) %> </a>
					                     </div>
						            </DIV> 
						            
						            <script type="text/javascript">
						            	dropdowncontent.init('scheme_<%= spMa[i]  %>', "left-top", 300, "click");
						            </script>
						         <% } else { %>
						         	<a href="javascript:void(0);" >&nbsp; <img alt="Chọn số lô" src="../images/vitriluu.png"></a>
						         <% } %>
							</td>
							<td style="display: none"> <input type="text" name="dongia" value="<%= spGianhap[i] %>" style="width: 100%; text-align: right;"  > </td>
							<td style="display: none"> <input type="text" name="thanhtien" value="" style="width: 100%; text-align: right;" readonly="readonly" > </td>
						</tr>	
							
					<% count ++; } } %>
				
				<% for(int i = count; i < 50; i++) { %>
					
					<tr>
						<td>
							<input type="text" name="spMa" value="" style="width: 100%"  onkeyup="ajax_showOptions(this,'nhapkho',event)" AUTOCOMPLETE="off"  > 
							<input type="hidden" name="spTrongLuong" value="" > 
							<input type="hidden" name="spTheTich" value="" > 
							<input type="hidden" name="spQuyDoi" value=""  >
						</td>
						<td> <input type="text" name="spTen" value="" style="width: 100%" readonly="readonly"> </td>
						<td>
							<%-- <select name="donvi" style="width: 100%"  >
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
								  <input  type="text" name="donvi" value="" style="width: 100%" readonly="readonly" />
						 </td>
						 <td > <input type="text" name="tonkho" value="" style="width: 100%; text-align: right;" readonly="readonly" > </td>
						<td > <div id="soluong_<%=i%>" ><input type="text" name="soluong" value="" style="width: 100%; text-align: right;" onkeypress="return keypress(event);"   onchange="nghiepvuChange('<%=i%>');" > </div></td>
						<td align="center" >
							<a href="javascript:void(0);" >
		           	 				&nbsp; <img alt="Chọn số lô" src="../images/vitriluu.png"></a>
						</td>
						<td style="display: none"> <input type="text" name="dongia" value="" style="width: 100%; text-align: right;"  > </td>
						<td style="display: none"> <input type="text" name="thanhtien" value="" style="width: 100%; text-align: right;" readonly="readonly" > </td>
					</tr>	
					
				<% } %>	
					
			</table>
			
			
				
            </div>
     </fieldset>	
    </div>
    <%geso.dms.center.util.Utility.JedisClose(jedis); %>
</div>

<script type="text/javascript">
	replaces();
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
</HTML>