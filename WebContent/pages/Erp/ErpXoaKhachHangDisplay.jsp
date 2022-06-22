<%@page import="java.util.ArrayList"%>
<%@page import="java.sql.SQLException"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page  import = "geso.dms.erp.beans.xoakhachhangtt.*" %>
<%@ page  import = "geso.dms.erp.beans.xoakhachhangtt.imp.*" %>
<%@ page  import = "java.sql.ResultSet" %>
<%@ page  import = "java.util.Hashtable" %>
<%@ page  import = "java.util.List" %>
<%@ page  import = "java.text.NumberFormat" %>
<%@ page  import = "java.text.DecimalFormat" %>
<% IErpXoakhachhangtt tthdBean = (IErpXoakhachhangtt)session.getAttribute("tthdBean"); %>
<% ResultSet nppList = tthdBean.getNppRs(); %>
<% ResultSet tienteList = tthdBean.getTienteRs(); %>
<% List<IHoadon> hoadonList = tthdBean.getHoadonRs(); %>
<% List<IHoadon> ctttList = tthdBean.getCtttList(); %>
<% ResultSet nccList = tthdBean.getNccRs(); %>
<% ResultSet nvtutList = tthdBean.getNvtuRs();%>
<% String userId = (String) session.getAttribute("userId");  %>
<% String userTen = (String) session.getAttribute("userTen");  %>
<% NumberFormat formatter = new DecimalFormat("#,###,###"); %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<TITLE>Phanam - Project</TITLE>
	<META http-equiv="Content-Type" content="text/html; charset=utf-8">
	<META http-equiv="Content-Style-Type" content="text/css">
	<LINK rel="stylesheet" type="text/css" media="print" href="../css/impression.css">
	<LINK rel="stylesheet" href="../css/main.css" type="text/css">
	<link type="text/css" rel="stylesheet" href="../css/mybutton.css">
	<LINK rel="stylesheet" type="text/css" href="../css/style.css" />

<link rel="stylesheet" type="text/css" href="../css/speechbubbles.css" />

<script type="text/javascript" src="../scripts/jquery.min.js"></script>
<script type="text/javascript" src="../scripts/dropdowncontent.js"></script>
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


<script language="javascript" type="text/javascript">
	function replaces(stt)
	{
		 var trahet = document.getElementById("trahet" + stt);
		 var avat = document.getElementById("avat" + stt);
		 var conlai = document.getElementById("conlai" + stt);
		 var thanhtoan = document.getElementById("thanhtoan" + stt);
		 
		 var sotienungtruoc = document.getElementById("sotienungtruoc").value.replace(/,/g,"");
		 if(sotienungtruoc == '')
			 sotienungtruoc = '0';
		
		 var tienthanhtoan = thanhtoan.value.replace(/,/g,"");
		 if(tienthanhtoan == '')
			 tienthanhtoan = '0';
		 
		 
		 thanhtoan.value = DinhDangTien(tienthanhtoan);
		 tienAvat = avat.value.replace(/,/g,"");
		 
		 if(parseFloat(tienthanhtoan) >= parseFloat(tienAvat))
		 {
			 thanhtoan.value = DinhDangTien(tienAvat);
			 conlai.value = "0";
			 
			 var ttThanhtoan = TongTienThanhToan()
			 if(parseFloat(sotienungtruoc) < parseFloat(ttThanhtoan) )
			 {
				thanhtoan.value = 0;
				conlai.value = DinhDangTien(parseFloat(tienAvat));
				
				alert('Tổng tiền thanh toán (' + DinhDangTien(ttThanhtoan) + ') không được phép vượt quá tổng số tiền trả trước (' + DinhDangTien(sotienungtruoc) + ') ');
				return;
			 }
		 
			
			 trahet.checked = true;
		 }
		 else
		 {
			 conlai.value = DinhDangTien(parseFloat(tienAvat) - parseFloat(tienthanhtoan));
			 
			 var ttThanhtoan = TongTienThanhToan()
			 if(parseFloat(sotienungtruoc) < parseFloat(ttThanhtoan) )
			 {
				thanhtoan.value = 0;
				conlai.value = DinhDangTien(parseFloat(tienAvat));
				
				alert('Tổng tiền thanh toán (' + DinhDangTien(ttThanhtoan) + ') không được phép vượt quá tổng số tiền trả trước (' + DinhDangTien(sotienungtruoc) + ') ');
				return;
			 }
			 
			 trahet.checked = false;
		 }
				
		 //document.getElementById("sotienungtruoc").value = DinhDangTien(TongTienThanhToan());
	}
	
	function TraTienCT(stt)
	{
		 var trahet = document.getElementById("ctttIds" + stt);
		 var avat = document.getElementById("tienchungtu" + stt);
		 var conlai = document.getElementById("tienconlai" + stt);
		 var thanhtoan = document.getElementById("tienthanhtoan" + stt);
		 var tongcong = 0;
		 
		 var tienthanhtoan = thanhtoan.value.replace(/,/g,"");
		 if(tienthanhtoan == '')
			 tienthanhtoan = '0';
		 
		 thanhtoan.value = DinhDangTien(tienthanhtoan);
		 tienAvat = avat.value.replace(/,/g,"");
		 
		 if(parseFloat(tienthanhtoan) >= parseFloat(tienAvat))
		 {
			 thanhtoan.value = DinhDangTien(tienAvat);
			 conlai.value = "0";
			 
			 trahet.checked = true;
			 tongcong = tongcong + parseFloat(tienAvat);
		 }
		 else
		 {
			 conlai.value = DinhDangTien(parseFloat(tienAvat) - parseFloat(tienthanhtoan));
			 trahet.checked = false;
			 tongcong = tongcong + parseFloat(tienthanhtoan);
		 }
		 var sotienungtruoc = document.getElementsByName("sotienungtruoc");
		 sotienungtruoc.item(0).value = DinhDangTien(tongcong);
		 
		 ThanhToan(100);
	}
	
	
	function TongTienThanhToan()
	{
		var tienthanhtoan = document.getElementsByName("thanhtoan");
		var tongthanhtoan = 0;
		for(k = 0; k < tienthanhtoan.length; k++)
		{
			if(tienthanhtoan.item(k).value != "")
			{
				var ttt = tienthanhtoan.item(k).value.replace(/,/g,""); 
				tongthanhtoan = parseFloat(ttt) + parseFloat(tongthanhtoan);
			}
		}
		return tongthanhtoan;
	}
	
	function DinhDangTienTT()
	{
		var giatrinhap = document.getElementById("sotienungtruoc");
		giatrinhap.value = DinhDangTien(giatrinhap.value);
		
		PhanBoTien();
	}
	
	function PhanBoTien()
	{	
		 var kyhieuhd = document.getElementsByName("kyhieuhd");
		 var trahet = document.getElementsByName("trahet");
		 var avat = document.getElementsByName("avat");
		 var thanhtoan = document.getElementsByName("thanhtoan");
		 var conlai = document.getElementsByName("conlai");
		 
		 for(j = 0; j < kyhieuhd.length; j++)
		 {
			 thanhtoan.item(j).value = "";
			 conlai.item(j).value = "";
			 trahet.item(j).checked = false;
		 }
		 
		 var sotienphanbo = document.getElementById("sotienungtruoc").value.replace(/,/g,"");
		 //alert(sotienphanbo);

		 var tongtien = 0;
		 var tongthanhtoan = 0;
		 for(i = 0; i < kyhieuhd.length; i++)
		 {
			tienAvat =  avat.item(i).value.replace(/,/g, "");
			tongtien = parseFloat(tongtien) + parseFloat(tienAvat);
			
			if(tongtien < parseFloat(sotienphanbo))
			{
				thanhtoan.item(i).value = DinhDangTien(roundNumber(tienAvat, 0));
				tongthanhtoan = tongthanhtoan + parseFloat(tienAvat);
				conlai.item(i).value = 0;
				trahet.item(i).checked = true;
			}
			else
			{
				tongtien = parseFloat(tongtien) - parseFloat(tienAvat);
				var tienconlai = parseFloat(sotienphanbo) - parseFloat(tongtien);
				
				thanhtoan.item(i).value = DinhDangTien(roundNumber(tienconlai, 0));
				tongthanhtoan = tongthanhtoan + tienconlai;
				
				conlai.item(i).value = DinhDangTien(roundNumber(parseFloat(tienAvat) - parseFloat(tienconlai), 0));
				if(parseFloat(tienAvat) - parseFloat(tienconlai) <= 0)
					trahet.item(i).checked = true;
				else
					trahet.item(i).checked = false;
				
				break;
			}			
		 }
		 
		 var tongtt = document.getElementsByName("tongthanhtoan");
		 tongtt.item(0).value = DinhDangTien(roundNumber(tongthanhtoan, 0));
		 var chenhlech = document.getElementsByName("chenhlech");
		 chenhlech.item(0).value = DinhDangTien(roundNumber(parseFloat(sotienphanbo) - tongthanhtoan), 0);
		 
	}
	
	function TongTienTT()
	{
		var tienthanhtoan = document.getElementsByName("tienthanhtoan");
		var tongtien = 0;
		for(j = 0; j < tienthanhtoan.length; j++)
		{
			if(tienthanhtoan.item(j).value != '')
			{
				var tt = tienthanhtoan.item(j).value.replace(/,/g,"");
				tongtien = parseFloat(tongtien) + parseFloat(tt);
			}
		}

		document.getElementById("sotienungtruoc").value = DinhDangTien(roundNumber(tongtien, 0));
		
		PhanBoTien();
	}
	
	function TraHetCTTT()
	 {
		 var ctttIds = document.getElementsByName("ctttIds");
		 var tienchungtu = document.getElementsByName("tienchungtu");
		 var tienthanhtoan = document.getElementsByName("tienthanhtoan");
		 var tienconlai = document.getElementsByName("tienconlai");
		 
		 var tongtien = 0;
		 for(i = 0; i < ctttIds.length; i++)
		 {
			if(ctttIds.item(i).checked)
			{
				tienthanhtoan.item(i).value = tienchungtu.item(i).value;
				tienconlai.item(i).value = '0';
				
				var tt = tienchungtu.item(i).value.replace(/,/g,"");
				tongtien = parseFloat(tongtien) + parseFloat(tt);
			}
		 }
		 document.getElementById("sotienungtruoc").value = DinhDangTien(roundNumber(tongtien, 0));
		 
		 PhanBoTien();
	 }
	 
	 function ThanhToan(n)
	 {
		 var sotienungtruoc = document.getElementById("sotienungtruoc").value.replace(/,/g,"");
		 if(sotienungtruoc == '')
			 sotienungtruoc = '0';
		 
		 var tongthanhtoan = document.getElementById("tongthanhtoan").value.replace(/,/g,"");
		 if(tongthanhtoan == '')
			 tongthanhtoan = '0';

		 var chenhlech = document.getElementById("chenhlech").value.replace(/,/g,"");
		 if(chenhlech == '')
			 chenhlech = '0';

		 var trahet = document.getElementsByName("trahet");
		 var avat = document.getElementsByName("avat");
		 var thanhtoan = document.getElementsByName("thanhtoan");
		 var conlai = document.getElementsByName("conlai");
		 
		 var tongtien = 0;
		 for(i = 0; i < trahet.length; i++)
		 {
			 
			if(trahet.item(i).checked) // tuong hop click chon tra het
			{
				if(n == 100){  // tuong hop click chon tra het nhung thay doi thanh toan
					var tt;
					if(thanhtoan.item(i).value != ''){
						tt = thanhtoan.item(i).value.replace(/,/g,"");
						 
					}else{
						tt = 0;
					}					

					if((parseFloat(avat.item(i).value.replace(/,/g,"")) - parseFloat(tt)) > 0){
						conlai.item(i).value = DinhDangTien(roundNumber(parseFloat(avat.item(i).value.replace(/,/g,"")) - parseFloat(tt), 0));
						
						tongtien = parseFloat(tongtien) + parseFloat(tt);					
						trahet.item(i).checked = false;	
					}else{
						thanhtoan.item(i).value = avat.item(i).value;
						conlai.item(i).value = parseFloat(avat.item(i).value) - parseFloat(thanhtoan.item(i).value);
						
						var tt = thanhtoan.item(i).value.replace(/,/g,"");
						tongtien = parseFloat(tongtien) + parseFloat(tt);					
						trahet.item(i).checked = true;
					}			
					
					thanhtoan.item(i).value = DinhDangTien(roundNumber(tt, 0));		
					
				}else{
					thanhtoan.item(i).value = avat.item(i).value;
					conlai.item(i).value = parseFloat(avat.item(i).value) - parseFloat(thanhtoan.item(i).value);
					
					var tt = thanhtoan.item(i).value.replace(/,/g,"");
					tongtien = parseFloat(tongtien) + parseFloat(tt);
				}					
			}else if(i == n){ // truong hop bo chon tra het
				
				thanhtoan.item(i).value = "0";				
				conlai.item(i).value = avat.item(i).value;
				
			}else{
				
				var tt;
				if(thanhtoan.item(i).value != ''){
					tt = thanhtoan.item(i).value.replace(/,/g,"");
					 
				}else{
					tt = 0;
				}					
				
				if((parseFloat(avat.item(i).value.replace(/,/g,"")) - parseFloat(tt)) >= 0){
					conlai.item(i).value = DinhDangTien(roundNumber(parseFloat(avat.item(i).value.replace(/,/g,"")) - parseFloat(tt), 0));
					
					tongtien = parseFloat(tongtien) + parseFloat(tt);					
					thanhtoan.item(i).value = DinhDangTien(roundNumber(tt, 0));
					
				}else{
					thanhtoan.item(i).value = avat.item(i).value;
					conlai.item(i).value = parseFloat(avat.item(i).value) - parseFloat(thanhtoan.item(i).value);
					
					var tt = thanhtoan.item(i).value.replace(/,/g,"");
					tongtien = parseFloat(tongtien) + parseFloat(tt);
					trahet.item(i).checked = true;	
				}			
			}
		 }
		 document.getElementById("tongthanhtoan").value = DinhDangTien(roundNumber(tongtien, 0));
		 document.getElementById("chenhlech").value = DinhDangTien(roundNumber(parseFloat(sotienungtruoc) - tongtien, 0));

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
	
	function roundNumber(num, dec) 
	{
		var result = Math.round(num*Math.pow(10,dec))/Math.pow(10,dec);
		return result;
	}
	
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
	
	 function saveform()
	 {	
		 var ngayghinhan = document.getElementById("ngaychungtu");
		 if(ngayghinhan.value == "")
		 {
			alert("Vui lòng nhập ngày chứng từ");
			return;
		 }
		 
		 var sotienungtruoc = document.getElementById("sotienungtruoc");
		 if(sotienungtruoc.value == "")
		 {
			alert("Vui lòng nhập số tiền thu của khách hàng");
			return;
		 }
		 

		 document.getElementById("btnSave").innerHTML = "<a href='#'><img src='../images/waiting.gif'  title='cho luu..' border='1' longdesc='cho luu..' style='border-style:outset'> Processing...</a>";
	 	 document.forms['tthdForm'].action.value='save';
	     document.forms['tthdForm'].submit();
	 }
	 
	 function submitform()
	 { 
		 document.forms['tthdForm'].action.value='submit';
	     document.forms["tthdForm"].submit();
	 }
	 function nextform()
	 { 
		 document.forms['tthdForm'].action.value='next';
	     document.forms["tthdForm"].submit();
	 }
	 function changePO()
	 { 
		 document.forms['tthdForm'].action.value='changePO';
	     document.forms["tthdForm"].submit();
	 }
	 function goBack()
	 {
	  	window.history.back();
	 }
</script>

<link href="../css/select2.css" rel="stylesheet" />
<script src="../scripts/select2.js"></script>
<script>
     $(document).ready(function() { $("select").select2();  });
     
</script>
	
</head>
<BODY leftmargin="0" bottommargin="0" topmargin="0" rightmargin="0">
<form name="tthdForm" method="post" action="../../ErpXoakhachhangttUpdateSvl">
<input type="hidden" name="userId" value='<%= userId %>'>
<input type="hidden" name="id" value='<%= tthdBean.getId() %>'>
<input type="hidden" name="action" value='1'>

<div id="main" style="width:100%; padding-left:2px">
	<div align="left" id="header" style="width:100%; float:none">
    	<div style="width:60%; padding:5px; float:left" class="tbnavigation">
        	Quản lý công nợ > Công nợ phải thu > Xóa nợ khách hàng > Hiển thị
        </div>
        <div align="right" style="padding:5px" class="tbnavigation">
        	<%=Utility.GLanguage("Chào mừng bạn",session,jedis) %>  <%= userTen %> &nbsp;
        </div>
    </div>
  
  	<div align="left" id="button" style="width:100%; height:32px; padding:0px; float:none" class="tbdarkrow">
    	<A href = "javascript: goBack();" ><img src="../images/Back30.png" alt="Quay ve"  title="Quay ve" border="1" longdesc="Quay ve" style="border-style:outset"></A>
        <!-- <span id="btnSave">
	        <A href="javascript:saveform()" >
	        	<IMG src="../images/Save30.png" title="Luu lai" alt="Luu lai" border ="1px" style="border-style:outset"></A>
        </span> -->
    </div>
  	
  	<div align="left" style="width:100%; float:none; clear:left">
  		<fieldset>
    		<legend class="legendtitle"> Thông báo</legend>
    		<textarea name="dataerror"  rows="1" readonly="readonly" style ="width:100%"><%= tthdBean.getMsg() %></textarea>
		         <% tthdBean.setMsg(""); %>
    	</fieldset>
  	</div>
  	
     <div align="left" style="width:100%; float:none; clear:left">
    <fieldset>
    	<legend class="legendtitle"> Xóa nợ khách hàng </legend>
        	<div style="float:none; width:100%" align="left">
            <TABLE width="100%" cellpadding="4" cellspacing="0" border = 0>							
                <TR>
                    <TD width="150px" class="plainlabel" valign="top">Ngày chứng từ</TD>
                    <TD class="plainlabel" width="300px" valign="top">
                    	<input type="text"  class="days" id="ngaychungtu" name="ngaychungtu" value="<%= tthdBean.getNgaychungtu() %>" 
                    		maxlength="10" onChange = "submitform();" readonly /></TD>
                    <TD width="140px" class="plainlabel" valign="top">Ngày ghi sổ</TD>
                    <TD class="plainlabel" valign="top">
                    	<input type="text"  class="days" id="ngayghiso" name="ngayghiso" value="<%= tthdBean.getNgayghiso() %>" 
                    		maxlength="10" readonly /></TD>
                </TR> 
                 <%-- <TR>
                        <TD class="plainlabel" width="15%">Lọc Từ ngày </TD>
                        <TD class="plainlabel" width="25%">
                            <input type="text" class="days" 
                                   id="tungay" name="tungay" value="<%= tthdBean.getTungay() %>" maxlength="10" onchange="submitform()" />
                        </TD>
                    
                        <TD class="plainlabel" width="15%" >Lọc Đến ngày </TD>
                        <TD class="plainlabel">
                            <input type="text" class="days" 
                                   id="denngay" name="denngay" value="<%= tthdBean.getDenngay() %>" maxlength="10" onchange="submitform()" />
                        </TD>
                </TR> --%>
                    
                <TR>
                	<TD class="plainlabel">Loại </TD>
                    <TD  class="plainlabel">
                        <select class="plainlabel" id="loaiId" name="loaiId" onchange="submitform()">
                          <%if(tthdBean.getLoaixnId().equals("0")) {%>
                        	<option value="0" selected="selected">Xóa nợ KH trả trước</option>
                        	<option value="1">Xóa tạm ứng</option>
                        	<%}else{ %>
                        	<option value="0" >Xóa nợ KH trả trước</option>
                        	<option value="1" selected="selected">Xóa tạm ứng</option>
                        	<%} %>
                        </select>
                     </TD> 
                    <TD class="plainlabel">Ghi chú </TD>
                    <TD  class="plainlabel">
                        <input type="text" name="ghichu" value="<%= tthdBean.getNoidungtt() %>"> 
                     </TD> 
                </TR>
                
                
<%--                 <%if(tthdBean.getLoaixnId().equals("0")) { // XÓA NỢ KHÁCH HÀNG TRẢ TRƯỚC%>                --%>
<!--                 	<TR> -->
<!--                 	<TD class="plainlabel" valign="middle">Loại khách hàng </TD> -->
<!-- 	                        <TD class="plainlabel" valign="middle" colspan="3"> -->
<!-- 	                           <select name="trangthai" onChange="submitform();" style ="width:200px"> -->
<!-- 	                           		<option value=""> </option> -->
<%-- 									<% if (tthdBean.gettrangthaitk().equals("1")){%> --%>
<!-- 									  	<option value="1" selected>Đã chốt</option> -->
<!-- 									  	<option value="0">Chưa chốt</option> -->
<!-- 									  	<option value="2">Đã xóa</option> -->
<!-- 									  	<option value="4" >Đã xuất kho(chưa hoàn tất)</option>
<!-- 									  	<option value="5" >Hoàn tất xuất kho </option> -->
									  
<%-- 									<%}else if(tthdBean.gettrangthaitk().equals("0")) {%> --%>
<!-- 									 	<option value="0" selected>Chưa chốt</option> -->
<!-- 									  	<option value="1" >Đã chốt</option> -->
<!-- 									 	<option value="2" >Đã xóa</option> -->
									  	
<%-- 									<%}else if(tthdBean.gettrangthaitk().equals("2")){%>											 --%>
<!-- 									 	<option value="2" selected>Đã xóa</option>										  	 -->
<!-- 									  	<option value="0" >Chưa chốt</option> -->
<!-- 									  	<option value="1" >Đã chốt</option> -->
									  	  	
<%-- 									<%}else{%> --%>
<!-- 										<option value="0" >Chưa chốt</option> -->
<!-- 									  	<option value="1" >Đã chốt</option>											 -->
<!-- 									  	<option value="2" >Đã xóa</option> -->
<!-- 									  	<option value="4" >Đã xuất kho(chưa hoàn tất)</option>
<!-- 									  	<option value="5" >Hoàn tất xuất kho</option> --> 
<%-- 									<%} %> --%>
<!-- 	                           </select> -->
<!-- 	                        </TD>   -->
<!-- 	                 </TR> -->
<%--                 <%} %> --%>
                
                
                
                <%if(tthdBean.getLoaixnId().equals("1")){ %>
                 <TR>
                  <TD class="plainlabel">Đối tượng tạm ứng</TD>
                    <TD class="plainlabel" valign="top">
                    	 <select name="doituongtamung" id="doituongtamung" onChange="submitform();">
                        	
                        	<%
                        		System.out.println("Tam ung trang JSP : "+tthdBean.getDoiTuongTamUng());
                        			if(tthdBean.getDoiTuongTamUng().equals("1")){ %>
                        				<option value="1" selected="selected" >Nhà cung cấp</option>
                        				<option value="0"  >Nhân viên</option>
                        				
                        			<%}else if(tthdBean.getDoiTuongTamUng().equals("0")){ %>
                        				<option value="1"  >Nhà cung cấp</option>
                        				<option value="0" selected="selected" >Nhân viên</option>
                        		 <% } %>
                 						                       		
                        </select>
                    </TD>
                    <%if(tthdBean.getDoiTuongTamUng().equals("1")) { %>
                      <TD class="plainlabel">Nhà cung cấp</TD>
                       <TD class="plainlabel" >
		                  <select name="nccId" id="nccId" onChange="submitform();">
                        	<option value=""> </option>
                        	<%
                        		if(nccList != null)
                        		{
                        			try
                        			{
                        			while(nccList.next())
                        			{  
                        			if( nccList.getString("pk_seq").equals(tthdBean.getNccId())){ %>
                        				<option value="<%= nccList.getString("pk_seq") %>" selected="selected" ><%= nccList.getString("ten")%></option>
                        			<%}else { %>
                        				<option value="<%= nccList.getString("pk_seq") %>" ><%= nccList.getString("ten") %></option>
                        		 <% } } nccList.close();} catch(SQLException ex){}
                        		}
                        	%>
                        </select>
		               </TD> 		                    
                    <%}else{ %>
                      <TD class="plainlabel">Nhân viên</TD>
                       <TD class="plainlabel"   >
		                 <select name="nvtuId" id="nvtuId" onChange="submitform();">
                        	<option value=""> </option>
                        	<%
                        		if(nvtutList != null)
                        		{
                        			try
                        			{
                        			while(nvtutList.next())
                        			{  
                        			if( nvtutList.getString("pk_seq").equals(tthdBean.getNvtuId())){ %>
                        				<option value="<%= nvtutList.getString("pk_seq") %>" selected="selected" ><%= nvtutList.getString("ten")%></option>
                        			<%}else { %>
                        				<option value="<%= nvtutList.getString("pk_seq") %>" ><%= nvtutList.getString("ten") %></option>
                        		 <% } } nvtutList.close();} catch(SQLException ex){}
                        		}
                        	%>
                        </select>       	 
		               </TD> 
                    <%} %>
                    
                	
                </TR>  
                <TR>
                    <TD class="plainlabel">Tiền tệ</TD>
                    <TD class="plainlabel" style = "width:100px" colspan="3">
                    	
                        <select name="tienteId" id="tienteId" style = "width:150px" onChange = "submitform();">
                        		
                        	<%
                        		if(tienteList != null)
                        		{
                        			try
                        			{
                        			while(tienteList.next())
                        			{  
                        			if( tienteList.getString("pk_seq").equals(tthdBean.getTienteId())){ %>
                        				<option value="<%= tienteList.getString("pk_seq") %>" selected="selected" ><%= tienteList.getString("ten")%></option>
                        			<%}else { %>
                        				<option value="<%= tienteList.getString("pk_seq") %>" ><%= tienteList.getString("ten") %></option>
                        		 <% } } tienteList.close();} catch(SQLException ex){}
                        		}
                        	%>
                        </select>
                     </TD> 
                
                </TR>
                <%}else{ %>
                <TR>
                   <TD class="plainlabel">Khách hàng</TD>                            		
                    <TD  class="plainlabel" >
                        <select name="nppId" id="nppId" style = "width:300px" onChange="submitform();">
                        	<option value=""> </option>
                        	<%
                        		if(nppList != null)
                        		{
                        			try
                        			{
                        			while(nppList.next())
                        			{  
                        			if( nppList.getString("pk_seq").equals(tthdBean.getNppId())){ %>
                        				<option value="<%= nppList.getString("pk_seq") %>" selected="selected" ><%= nppList.getString("nppTen") %></option>
                        			<%}else { %>
                        				<option value="<%= nppList.getString("pk_seq") %>" ><%= nppList.getString("nppTen") %></option>
                        		 <% } } nppList.close();} catch(SQLException ex){}
                        		}
                        	%>
                        </select>
                     </TD> 
                     
                    <TD class="plainlabel">Tiền tệ</TD>
                    <TD class="plainlabel" style = "width:100px">
                    	
                        <select name="tienteId" id="tienteId" style = "width:150px" onChange = "submitform();">
                        		
                        	<%
                        		if(tienteList != null)
                        		{
                        			try
                        			{
                        			while(tienteList.next())
                        			{  
                        			if( tienteList.getString("pk_seq").equals(tthdBean.getTienteId())){ %>
                        				<option value="<%= tienteList.getString("pk_seq") %>" selected="selected" ><%= tienteList.getString("ten")%></option>
                        			<%}else { %>
                        				<option value="<%= tienteList.getString("pk_seq") %>" ><%= tienteList.getString("ten") %></option>
                        		 <% } } tienteList.close();} catch(SQLException ex){}
                        		}
                        	%>
                        </select>
                     </TD> 
                     
                </TR>
                <%} %>
            	<TR>
                 <TD class="plainlabel" >Chứng từ trả trước </TD>
			     <TD class="plainlabel" colspan="3">
			      <a href="" id="ctttId" rel="subcontentCttt">
                  &nbsp; <img alt="Chọn chứng từ trả trước" src="../images/vitriluu.png"></a>
             
                         <DIV id="subcontentCttt" style="position:absolute; visibility: hidden; border: 9px solid #80CB9B;
                                background-color: white; width: 620px; max-height:300px; overflow:auto; padding: 4px;">
                       <table width="90%" align="center">
                           <tr>
                            <th width="100px" align="center" style="font-size: 12px" align="center">Loại chứng từ</th>
                               <th width="100px" style="font-size: 12px" align="center">Số chứng từ</th>
                               <th width="100px" style="font-size: 12px" align="center">Ngày chứng từ</th> 
                             <%if(tthdBean.getTienteId().equals("100000")){ %>
                              <th width="100px" style="font-size: 12px" align="center">Số tiền TT</th>                               
                             <%}else{ %>                              
                               <th width="100px" style="font-size: 12px" align="center">Số tiền TT</th>
                               <th width="100px" style="font-size: 12px" align="center">Số tiền TT (VND)</th>
                             <%} %>  
                               <th width="100px" style="font-size: 12px" align="center">Thanh toán</th>
                               <th width="100px" style="font-size: 12px" align="center">Còn lại</th>
                               <th width="100px" align="center" style="font-size: 12px" align="center">Trả hết</th>
                               
                           </tr>
						 <%
                            if(ctttList.size() > 0)
                            {
                             for(int i = 0; i <ctttList.size(); i++)
                             {
                              IHoadon hoadonTT = ctttList.get(i);
                            
                             %>
                             
                             <tr>
                              <td>
                               <input type="hidden" name="loaict" id = "loaict<%= i %>"  style="width: 100%"  value="<%= hoadonTT.getLoaict() %>" >
                               <%if(hoadonTT.getLoaict().equals("0")) { %>
                                <input type="text" style="width: 100%" value="Thu tiền"> 
                               <%} else { %>
                                <input type="text" style="width: 100%" value="Bút toán tổng hợp"> 
                               <%} %>
                              </td>
                              <td>
                               <input type="hidden" name = "idchungtu" style="width: 100%" value="<%= hoadonTT.getId() %>">
                               <input type="hidden"  id = "tigiachungtu<%= i %>" name = "tigiachungtu" style="width: 100%" value="<%= hoadonTT.getTigiaHD() %>">
                               <input type="text" name = "khchungtu" style="width: 100%" value="<%= hoadonTT.getKyhieu() %>">
                              </td>
                              <td><input style="width: 100%"  value="<%= hoadonTT.getNgay() %>" name="ngaychungtu" ></td>
                              
                             <% if(tthdBean.getTienteId().equals("100000")){ %>
                              <td><input style="width: 100%; text-align: right;" name="tienchungtu" id="tienchungtu<%= i %>"
                                value="<%= hoadonTT.getTongtiencoVAT() %>" readonly="readonly"></td>
           				<%}else{ %>
            				<td>
            					<input style="width: 100%; text-align: right;" name="tienchungtu" id="tienchungtu<%= i %>" value="<%= hoadonTT.getTongtiencoVAT() %>" readonly="readonly">
            				</td>
                            <td><input style="width: 100%; text-align: right;" name="tienchungtuVND" id="tienchungtuVND<%= i %>"
                                value="<%= hoadonTT.getTongtiencoVATVND() %>" readonly="readonly"></td>
          				 <%} %>
 
                              <td ><input style="width: 100%; text-align: right;" name="tienthanhtoan" id="tienthanhtoan<%= i %>"  
                                onkeyup="TraTienCT(<%= i %>)" value="<%= hoadonTT.getThanhtoan() %>" ></td>
  
                              <td ><input style="width: 100%; text-align: right;" name="tienconlai" id="tienconlai<%= i %>" readonly="readonly"
                                value="<%= hoadonTT.getConlai() %>" ></td>
                              <td align="center">
                              
                              <% if( hoadonTT.getConlai().equals("0") ){ %>
                               <input type="checkbox" name="ctttIds" id="ctttIds<%= i %>" checked="checked" onchange="TraHetCTTT(<%= i %>)" >
                              <%}else{ %>
                               <input type="checkbox" name="ctttIds" id="ctttIds<%= i %>" onchange="TraHetCTTT(<%= i %>)">
                              <%} %>
                              </td>
                             </tr>
                             
                             <% } }  %>
                       </table>
                        <div align="right">
                         <label style="color: red" ></label>
                         &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                         <a href="javascript:dropdowncontent.hidediv('subcontentCttt')" >Hoàn tất</a>
                        </div>
               </DIV>         
		     </TD>
		 </TR >
                
            	<TR>
                    <TD class="plainlabel">Tổng tiền ứng trước </TD>
                    <TD class="plainlabel">
                        <input type="text" style="text-align: right;" name="sotienungtruoc" id = "sotienungtruoc" 
                        		value="<%= tthdBean.getSotientt() %>"  readonly="readonly" > 
                     </TD> 
                <%if(!tthdBean.getTienteId().equals("100000")){ // NGOẠI TỆ%>
                	<TD class="plainlabel">Tổng tiền ứng trước (VNĐ)</TD>
                    <TD class="plainlabel">
                        <input type="text" style="text-align: right;" name="sotienungtruocVND" id = "sotienungtruocVND" 
                        		value="<%= tthdBean.getSotienttVND() %>"  readonly="readonly" > 
                     </TD> 
                <%} else {%>
                 	<TD class="plainlabel" colspan="2">
                        <input type="hidden" style="text-align: right;" name="sotienungtruocVND" id = "sotienungtruocVND" 
                        		value=""  readonly="readonly" > 
                     </TD>
                <%} %>
                
                </TR>
                <TR>
                    <TD class="plainlabel">Tổng tiền thanh toán </TD>
                    <TD  class="plainlabel">
                        <input type="text" style="text-align: right;" name="tongthanhtoan" id = "tongthanhtoan" 
                        		value="<%= tthdBean.getTongthanhtoan() %>"  readonly="readonly"> 
                     </TD> 
                     
                <%if(!tthdBean.getTienteId().equals("100000")){ // NGOẠI TỆ%>
                	<TD class="plainlabel">Tổng tiền thanh toán (VNĐ)</TD>
                    <TD class="plainlabel">
                        <input type="text" style="text-align: right;" name="tongthanhtoanVND" id = "tongthanhtoanVND" 
                        		value="<%= tthdBean.getTongthanhtoanVND() %>"  readonly="readonly" > 
                     </TD> 
                <%} else {%>
                 	<TD class="plainlabel" colspan="2">
                        <input type="hidden" style="text-align: right;" name="tongthanhtoanVND" id = "tongthanhtoanVND" 
                        		value=""  readonly="readonly" > 
                     </TD> 
                <%} %>
                
                </TR>

                <TR>
                    <TD class="plainlabel">Chênh lệch </TD>
                    <TD  class="plainlabel">
                        <input type="text" style="text-align: right;" name="chenhlech" id = "chenhlech" 
                        		value="<%= tthdBean.getChenhlech() %>"  readonly="readonly" > 
                     </TD> 
                     
                <%if(!tthdBean.getTienteId().equals("100000")){ // NGOẠI TỆ%>
                	<TD class="plainlabel">Chênh lệch (VNĐ)</TD>
                    <TD class="plainlabel">
                        <input type="text" style="text-align: right;" name="chenhlechVND" id = "chenhlechVND" 
                        		value="<%= tthdBean.getChenhlechVND() %>"  readonly="readonly" > 
                     </TD> 
                <%} else {%>
                    <TD class="plainlabel" colspan="2">
                        <input type="hidden" style="text-align: right;" name="chenhlechVND" id = "chenhlechVND" 
                        		value=""  readonly="readonly" > 
                     </TD> 
                <%} %>
                
                </TR>
                
            </TABLE>
            <hr> 
            </div>
        
           	<div align="left" style="width:100%; float:none; clear:none;" class="plainlabel">
            <TABLE class="tabledetail" width="100%" border="0" cellpadding="1" cellspacing="1" >
                <TR class="tbheader"> 
                	<TH align="center" width="12%">Loại chứng từ</TH>
                	<TH align="center" width="12%">Ký hiệu HĐ</TH>
                	<TH align="center" width="12%">Số HĐ</TH>
                	<TH align="center" width="15%">Ngày HĐ</TH>
                <%if(tthdBean.getTienteId().equals("100000")){ %>
                	<TH align="center" width="20%">Tổng số tiền (đã có VAT)</TH>
                <%}else { %>
                	<TH align="center" width="15%">Tổng số tiền (Ngoại tệ)</TH>
                	<TH align="center" width="15%">Tổng số tiền VNĐ </TH>
                <%} %>
               	 	<TH align="center" width="10%">Thanh toán</TH>
               	 	<TH align="center" width="15%">Còn lại</TH>
               	 	<TH align="center" width="15%">
               	 		Tất cả 
               	 		<input type="checkbox" onchange="sellectAll()" id="chkAll"  />
               	 	</TH>
                </TR>
                <%
                	for(int i = 0; i < hoadonList.size(); i++)
                	{
                		IHoadon hoadon = hoadonList.get(i);
                		String loaichungtu = "";
	               		%>
	               		<tr>
	               			<% if( hoadon.getLoaihd().equals("0")) loaichungtu = "Hóa đơn tài chính";
           	 					else if ( hoadon.getLoaihd().equals("1")) loaichungtu = "Hóa đơn khác";
           	 					else if ( hoadon.getLoaihd().equals("2")) loaichungtu = "Bút toán tổng hợp";
           	 					else if ( hoadon.getLoaihd().equals("4")) loaichungtu = "Bù trừ công nợ";
           	 					else if ( hoadon.getLoaihd().equals("7")) loaichungtu = "Hóa đơn hàng trả về";
           	 				%>
           	 				<td align="center">
           	 					<input type="text" style="width: 100%;" value="<%= loaichungtu %>" name= "sohd" readonly="readonly" >
           	 				</td>
           	 				<td align="center">
           	 					
           	 					<input type="hidden" style="width: 100%;" value="<%= hoadon.getId() %>" name= "idHd" readonly="readonly" >
           	 					<input type="hidden" style="width: 100%;" value="<%= hoadon.getLoaihd() %>" name= "loaiHd" readonly="readonly" >
           	 					<input type="hidden" style="width: 100%;" value="<%= hoadon.getTigiaHD() %>" name= "tigiaHD" readonly="readonly" >
           	 					<input type="text" style="width: 100%;" value="<%= hoadon.getKyhieu() %>" name= "kyhieuhd" readonly="readonly" >
           	 				</td>
           	 				<td align="center">
           	 					<input type="text" style="width: 100%;" value="<%= hoadon.getSo() %>" name= "sohd" readonly="readonly" >
           	 				</td>
           	 				<td align="center">
           	 					<input type="text" style="width: 100%; text-align: left;" value="<%= hoadon.getNgay() %>" name= "ngayhd" readonly="readonly" >
           	 				</td>
           	 			 <%if(tthdBean.getTienteId().equals("100000")){ %>	
           	 				<td align="center">
           	 					<input type="text" style="width: 100%; text-align: right;" value="<%= hoadon.getTongtiencoVAT() %>" name= "avat" id="avat<%= i %>" readonly="readonly" >
           	 				</td>
           	 			 <%}else { %>
           	 			 	<td align="center">
           	 					<input type="text" style="width: 100%; text-align: right;" value="<%= hoadon.getTongtiencoVAT() %>" name= "avat" id="avat<%= i %>" readonly="readonly" >
           	 				</td>
           	 				<td align="center">
           	 					<input type="text" style="width: 100%; text-align: right;" value="<%= hoadon.getTongtiencoVATVND() %>" name= "avatVND" id="avatVND<%= i %>" readonly="readonly" >
           	 				</td>
           	 			 <%} %>
           	 				<td align="center">
           	 					<input type="text" style="width: 100%; text-align: right;" value="<%= hoadon.getThanhtoan() %>" name= "thanhtoan" id="thanhtoan<%= i %>"  onkeyup="TraTienHD(<%= i %>)"  onKeyPress = "return keypress(event);" >
           	 				</td>
           	 				<td align="center">
           	 					<input type="text" style="width: 100%; text-align: right;" value="<%= hoadon.getConlai() %>" name= "conlai" id="conlai<%= i %>" readonly="readonly" >
           	 				</td>
           	 				<td align="center">
           	 				<%if(hoadon.getConlai().equals("0")){ %>
           	 					<input type="checkbox" value="<%= hoadon.getId() %>" name = "trahet" id="trahet<%= i %>" checked="checked" onchange="TraHetHD(<%= i %>)" >
           	 				<%}else{ %>
           	 					<input type="checkbox"  value="<%= hoadon.getId() %>" name = "trahet" id="trahet<%= i %>" onchange="TraHetHD(<%= i %>)" >
           	 				<%}%>
           	 				</td>
           	 			</tr>
           	 	<%} %>
            </TABLE> 
        	</div>    
     </fieldset>	
    </div>
</div>
</form>
<script type="text/javascript">
	dropdowncontent.init('ctttId', "right-bottom", 300, "click");
	
	jQuery(function()
			{		
				$("#nppTen").autocomplete("ErpKhachhangCanTruList.jsp");
				
				repl();		

			});
	
</script>
<script type="text/javascript">
	$("select").css("width","200px");
</script>
</BODY>
</HTML>

<%
try{
	if (nppList != null)
		nppList.close();
	if (tienteList != null)
		tienteList.close();
	if (hoadonList != null)
		hoadonList.clear();
	if (ctttList != null)
		ctttList.clear();
	if (nccList != null)
		nccList.close();
	if (nvtutList != null)
		nvtutList.close();
	tthdBean.DBclose();
}
catch (Exception ex)
{
	ex.printStackTrace();
}
%>