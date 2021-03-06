<%@page import="java.sql.SQLException"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page  import = "geso.dms.distributor.beans.khachhang.IKhachhang" %>
<%@ page  import = "java.sql.ResultSet" %>
<%@ page  import = "java.util.Hashtable" %>
<%@ page  import = "geso.dms.center.util.*" %>
<%@ page import="java.text.DecimalFormat"%>
<%@ page import="java.text.NumberFormat"%>

<%	
NumberFormat formatter = new DecimalFormat("#,###,###.##");
	String userId = (String) session.getAttribute("userId");  
	String userTen = (String) session.getAttribute("userTen");  	
	String sum = (String) session.getAttribute("sum");
	Utility util = new Utility();
	
	util.getIdNhapp(userId);
	
	if (!util.check(userId, userTen, sum)){
		response.sendRedirect(request.getContextPath() + "/redirect.jsp");
	}else{ %>
<% 
	IKhachhang khBean = (IKhachhang)session.getAttribute("khBean");
	String view = khBean.getView();
%>
<% ResultSet tp = (ResultSet)khBean.getTp() ;  %>
<% ResultSet qh = (ResultSet)khBean.getQh() ;  %>
<% ResultSet hch = (ResultSet)khBean.getHangcuahang(); %>
<% ResultSet kbh = (ResultSet)khBean.getKenhbanhang();  %>
<% ResultSet vtch = (ResultSet)khBean.getVtcuahang();  %>
<% ResultSet lch = (ResultSet)khBean.getLoaicuahang(); %>
<% ResultSet nch = (ResultSet)khBean.getNhomcuahang();  %>
<% ResultSet mck = (ResultSet)khBean.getMucchietkhau();  %>
<% ResultSet nvgn = (ResultSet)khBean.getNvgnRs();  %>
<% ResultSet diadiemRs = (ResultSet)khBean.getDiadiemRs();  %>
<% 
	ResultSet khoRs = (ResultSet)khBean.getKhoRs();
	ResultSet khoSAP = (ResultSet)khBean.getKhoSAP();%>
<% ResultSet nkh_khList = (ResultSet)khBean.getNkh_khList();  %>
<% Hashtable<Integer, String> nkh_khIds = (Hashtable<Integer, String>)khBean.getNkh_KhIds(); %>
<% ResultSet tbhRs = (ResultSet)khBean.getTbhRs()  ; %>
<% ResultSet ddkdRs = (ResultSet)khBean.getDdkdRs()  ; %>
<% ResultSet dtRs = (ResultSet)khBean.getDtRs()  ; %>
<% ResultSet thanhthinongthonRs = (ResultSet)khBean.getThanhthinongthonRs()  ; %>
<% ResultSet nppRs = (ResultSet)khBean.getNppRs(); %>
<% ResultSet khachhangRs = (ResultSet)khBean.getKhachhangRs(); %>
<% ResultSet nppBanChungRs = (ResultSet)khBean.getNppBanChungRs(); %>
<% 
	ResultSet diabanRs = (ResultSet)khBean.getDiabanRs(); 
	ResultSet khuvucRs = (ResultSet)khBean.getKhuvucRs(); 
	ResultSet phuongxaRs = khBean.getPhuongxaRs();
	String url = util.getChuyenNguUrl("KhachhangSvl", "",session);
	int[] quyen = new  int[6];
	if (view.equals("TT"))
	{
		quyen = util.Getquyen("KhachhangSvl", "&view=" + view, userId);
		url = util.getUrl("KhachhangSvl", "&view=" + view);
	}
	else
	{
		quyen = util.Getquyen("KhachhangSvl", "", userId);
		url = util.getUrl("KhachhangSvl", "");
	}	
%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"> <% Utility Utilback = new Utility(); %>
<HTML>
<HEAD>
<TITLE><%= getServletContext().getInitParameter("TITLENAME") %></TITLE>
<META http-equiv="Content-Type" content="text/html; charset=utf-8">

<META http-equiv="Content-Style-Type" content="text/css">

<LINK rel="stylesheet" href="../css/main.css" type="text/css">

<link href="../css/jquery-ui.css" rel="stylesheet" type="text/css"/>

<script type="text/javascript" src="../scripts/jquery.min.1.7.js"></script>
<script src="../scripts/ui/jquery.ui.core.js" type="text/javascript"></script>
<script src="../scripts/ui/jquery.ui.widget.js" type="text/javascript"></script>
<script type="text/javascript" src="../scripts/phanTrang.js"></script>
<script src="../scripts/ui/jquery.ui.datepicker.js" type="text/javascript"></script>
<link media="screen" rel="stylesheet" href="../css/colorbox.css">
<script src="../scripts/colorBox/jquery.colorbox.js"></script>

<script type="text/javascript">
$(document).ready(function() {		
		$( ".days" ).datepicker({			    
				changeMonth: true,
				changeYear: true				
		});      
		
		<%if (khBean.getPageX() != 0 && khBean.getPageY() != 0 ){%>
$(document).scrollTop(<%= khBean.getPageY()  %>);
$(document).scrollLeft(<%= khBean.getPageX()  %>);
<%}%>

 }); 		
		
</script>
   

<SCRIPT language="javascript" type="text/javascript">




function addThem(e)
{
	removeDisable();
	document.forms["khForm"].action.value = "addThem";
    document.forms["khForm"].submit();
    
    
}
function Xoa(i)
{
	removeDisable();
	document.forms["khForm"].action.value = "Xoa";
	document.forms["khForm"].xoaId.value = i;
    document.forms["khForm"].submit();
}


function submitform()
 {   
	removeDisable();
    document.forms["khForm"].submit();
 }
function removeDisable()
{
	  	document.getElementById("tpId").removeAttribute('disabled');
		 document.getElementById("qhId").removeAttribute('disabled');
}
 
 
	function saveform() {
		removeDisable();
		var phuongxaId = document.getElementById("phuongxaId");
		 if (phuongxaId.value == '' || phuongxaId.value == 'null') {
			alert('Vui l??ng ch???n ph?????ng x?? ');
			return;
		}
		var khoId = document.getElementById("khoId");
		if (khoId.value == '') {
			alert('Vui l??ng ch???n KHO kh??ch h??ng ');
			return;
		}
		/* var lchId = document.getElementById("lchId");
		if (lchId.value == '') {
			alert('Vui l??ng ch???n lo???i kh??ch h??ng ');
			return;
		}  */
		var sonha = document.getElementById("sonha");
		if (sonha.value.trim() == '') {
			alert('Vui l??ng nh???p s??? nh?? ');
			return;
		} 
		var tenduong = document.getElementById("tenduong");
		if (tenduong.value.trim() == '') {
			alert('Vui l??ng nh???p t??n ???????ng ');
			return;
		} 
		var diachigiaohang = document.getElementById("diachigiaohang");
		if (diachigiaohang.value.trim() == '') {
			alert('Vui l??ng nh???p ?????a ch??? giao h??ng ');
			return;
		} 
		var nguoimuahang = document.getElementById("nguoimuahang");
		if (nguoimuahang.value.trim() == '') {
			alert('Vui l??ng nh???p ng?????i mua h??ng');
			return;
		} 
		var tencuahieu = document.getElementById("tencuahieu");
		if (tencuahieu.value.trim() == '') {
			alert('Vui l??ng nh???p t??n c???a hi???u');
			return;
		} 
		
		var tbhid = document.getElementsByName("tbhId");
		var tanso = document.getElementsByName("tanso");
		var ddkdId = document.getElementById("ddkdId");
		var chk = false;
		
		if (ddkdId.value.length > 0) {
			
			//Ki???m tra ??t nh???t 1 tuy???n b??n h??ng ???????c Check hay ch??a
			for (var n=0;n<tbhid.length;n++){
				if (tbhid.item(n).checked)
						{chk = true;}}
			
			if (chk=false){
				alert('Vui l??ng ch???n tuy???n b??n h??ng v?? t???n s???');
				return;
			}
			
			for (var k=0;k<tbhid.length;k++){		
				if (tbhid.item(k).checked && tanso.item(k).value.trim() == ""){
					alert('T???n s??? v?? tuy???n b??n h??ng kh??ng h???p l???, vui l??ng ki???m tra l???i');
					return; 
					}
				if (tanso.item(k).value != "" && !tbhid.item(k).checked){
					alert('T???n s??? v?? tuy???n b??n h??ng kh??ng h???p l???, vui l??ng ki???m tra l???i');
					return;
				}
			} 
			
			var cnt = 0; cntF8 = 0; cntF12 = 0;
			for (var i=0;i<tanso.length;i++){
				if (tbhid.item(i).checked && (tanso.item(i).value == 'F1-1' || tanso.item(i).value == 'F1-2' || tanso.item(i).value == 'F1-3' || tanso.item(i).value == 'F1-4'	
					|| tanso.item(i).value == 'F2-1' || tanso.item(i).value == 'F2-2' || tanso.item(i).value == 'F4' )){
					cnt++;			
				}
				if (tbhid.item(i).checked && tanso.item(i).value == 'F8'){
					cntF8++;			
				}
				if (tbhid.item(i).checked && tanso.item(i).value == 'F12'){
					cntF12++;			
				}
				
			}
			
			for (var i=0;i<tanso.length;i++){
				
				if (tbhid.item(i).checked && (tanso.item(i).value != 'F8' && tanso.item(i).value != 'F12')){
					if (cnt > 1){
						alert('T???n s??? v?? tuy???n b??n h??ng kh??ng h???p l???, vui l??ng ki???m tra l???i');
						return;}} 
				if (tbhid.item(i).checked && tanso.item(i).value == 'F8'){
					console.log('cntf12: '+cntF12);
					if (cntF8 != 2){
						alert('T???n s??? v?? tuy???n b??n h??ng kh??ng h???p l???, vui l??ng ki???m tra l???i');
						return;
						}}
			 	if (tbhid.item(i).checked && tanso.item(i).value == 'F12'){
					if (cntF12 != 3){
						alert('T???n s??? v?? tuy???n b??n h??ng kh??ng h???p l???, vui l??ng ki???m tra l???i');
						return;
						}}	 
			 	if (cntF8 == 2 && (cnt > 0 || cntF12 > 0)){
			 		alert('T???n s??? v?? tuy???n b??n h??ng kh??ng h???p l???, vui l??ng ki???m tra l???i');
					return;
			 	}
			 	if (cntF12 == 3 && (cnt > 0 || cntF8 > 0)){
			 		alert('T???n s??? v?? tuy???n b??n h??ng kh??ng h???p l???, vui l??ng ki???m tra l???i');
					return;
			 	}		
			}	
		}
				
		
		document.getElementById("btnSave").innerHTML = "<a href='#'><img src='../images/waiting.gif'  title='cho thuc hien..' border='1' longdesc='cho thuc hien..' style='border-style:outset'> Processing...</a>";
		document.forms['khForm'].action.value = 'save';
		document.forms['khForm'].submit();
	}
	
	function showElement(id) {
		var element = document.getElementById(id);
		element.style.display = "";
	}

	function hideElement(id) {
		var element = document.getElementById(id);
		element.style.display = "none";
	}

	function SubmitAgain() {
		var type = document.forms['khForm'].type.value;
		if (type == "1") {
			if (!confirm('?????a ch??? kh??ch h??ng n??y ???? b??? tr??ng,B???n mu???n ti???p t???c l??u kh??ch h??ng n??y?')) {
				document.forms['khForm'].type.value = "0";
				return false;
			} else {
				saveform();
			}
		}
	}

	function submitForm2() {
		removeDisable();
		var pxTen = document.getElementById('pxTen').value;
		var tpId = document.getElementById('tpId').value;
		var qhId = document.getElementById('qhId').value;
		var id = document.getElementById('phuongxaId').value;

		if (pxTen == '' || tpId == '' || qhId == '') {
			alert('Vui l??ng nh???p ?????y ????? th??ng tin T???nh th??nh, qu???n huy???n!');
			return;
		}

		var xmlhttp;
		if (window.XMLHttpRequest) {
			xmlhttp = new XMLHttpRequest();
		} else {
			xmlhttp = new ActiveXObject("Microsoft.XMLHTTP");
		}

		xmlhttp.onreadystatechange = function() {
			if (xmlhttp.readyState == 4 && xmlhttp.status == 200) {
				if (xmlhttp.responseText.length > 10) {
					alert('Kh??ng th??? t???o m???i ph?????ng x??.');
				} else {
					alert('T???o ph?????ng x?? m???i th??nh c??ng.');
					submitform();
				}
			}
		}

		xmlhttp.open("GET", "../../AjaxPhuongXa?action=save&tpId=" + tpId
				+ "&qhId=" + qhId + "&pxTen=" + pxTen + "&id=" + id, true);
		xmlhttp.send();
	}
</SCRIPT>
<link href="../css/select2.css" rel="stylesheet" />
	<script src="../scripts/select2.js"></script>
	<script>
    	$(document).ready(function() { 
    		$("select:not(.notuseselect2)").select2({ width: 'resolve' });     
    	});
    	
    	
    	$( document ).on( "mousemove", function( event ) {
        	  
    		document.forms["khForm"].pageX.value = event.pageX;
    		document.forms["khForm"].pageY.value = event.pageY;
    		
    	});
    	
    </script>

<style type="text/css">
.btn {
  display: inline-block;
  padding: 6px 12px;
  margin-bottom: 0;
  font-size: 14px;
  font-weight: normal;
  line-height: 1.42857143;
  text-align: center;
  white-space: nowrap;
  vertical-align: middle;
  -ms-touch-action: manipulation;
      touch-action: manipulation;
  cursor: pointer;
  -webkit-user-select: none;
     -moz-user-select: none;
      -ms-user-select: none;
          user-select: none;
  background-image: none;
  border: 1px solid transparent;
  border-radius: 4px;
}
.btn:focus,
.btn:active:focus,
.btn.active:focus,
.btn.focus,
.btn:active.focus,
.btn.active.focus {
  outline: thin dotted;
  outline: 5px auto -webkit-focus-ring-color;
  outline-offset: -2px;
}
.btn:hover,
.btn:focus,
.btn.focus {
  color: #333;
  text-decoration: none;
}
.btn:active,
.btn.active {
  background-image: none;
  outline: 0;
  -webkit-box-shadow: inset 0 3px 5px rgba(0, 0, 0, .125);
          box-shadow: inset 0 3px 5px rgba(0, 0, 0, .125);
}
.btn.disabled,
.btn[disabled],
fieldset[disabled] .btn {
  pointer-events: none;
  cursor: not-allowed;
  filter: alpha(opacity=65);
  -webkit-box-shadow: none;
          box-shadow: none;
  opacity: .65;
}
.btn-default {
  color: #333;
  background-color: #fff;
  border-color: #ccc;
}
.btn-default:hover,
.btn-default:focus,
.btn-default.focus,
.btn-default:active,
.btn-default.active,
.open > .dropdown-toggle.btn-default {
  color: #333;
  background-color: #e6e6e6;
  border-color: #adadad;
}
.btn-default:active,
.btn-default.active,
.open > .dropdown-toggle.btn-default {
  background-image: none;
}
.btn-default.disabled,
.btn-default[disabled],
fieldset[disabled] .btn-default,
.btn-default.disabled:hover,
.btn-default[disabled]:hover,
fieldset[disabled] .btn-default:hover,
.btn-default.disabled:focus,
.btn-default[disabled]:focus,
fieldset[disabled] .btn-default:focus,
.btn-default.disabled.focus,
.btn-default[disabled].focus,
fieldset[disabled] .btn-default.focus,
.btn-default.disabled:active,
.btn-default[disabled]:active,
fieldset[disabled] .btn-default:active,
.btn-default.disabled.active,
.btn-default[disabled].active,
fieldset[disabled] .btn-default.active {
  background-color: #fff;
  border-color: #ccc;
}
.btn-default .badge {
  color: #fff;
  background-color: #333;
}
</style>

</HEAD>
<BODY leftmargin="0" bottommargin="0" topmargin="0" rightmargin="0">
	<form name="khForm" method="post" action="../../KhachhangUpdateSvl">
		<input type="hidden" name="pageX" value='0'> 
		<input type="hidden" name="pageY" value='0'> 
		<input type="hidden" name="xoaId" value="-1">
		<input type="hidden" name="userId" value='<%=userId %>'> 
		<input type="hidden" name="nppId"  value='<%= khBean.getNppId() %>' >
		<input type="hidden" name="action" value='1'> 
		<input type="hidden" name="type" value='<%=khBean.gettype()%>'>
		<input type="hidden" name="view" value='<%=khBean.getView()%>'>
<input type="hidden" name="id" value='<%=khBean.getId()%>'>
<% redis.clients.jedis.Jedis jedis =  geso.dms.center.util.Utility.getJedis(); %><% geso.dms.center.util.Csrf csdr=new geso.dms.center.util.Csrf(request,response,true,false,true);%>
 <input type="hidden" name="<%=csdr.get_tokenName() %>" value='<%=csdr.get_tokenValue() %>'>

		<TABLE width="100%" border="0" cellspacing="0" cellpadding="0"
			height="100%">
			<TR>
				<TD colspan="4" align='left' valign='top' bgcolor="#FFFFFF">
					<TABLE width="100%" border="0" cellpadding="0" cellspacing="2">
						<TR>
							<TD align="left" class="tbnavigation">
								<table width="100%" border="0" cellpadding="0" cellspacing="0">
									<tr height="22">
										<TD align="left" colspan="2" class="tbnavigation"><%= url %> > <%=Utility.GLanguage("Hi???n th???",session,jedis) %>
										<TD colspan="2" align="right" class="tbnavigation"><%=Utility.GLanguage("Ch??o m???ng b???n",session,jedis) %>  <%= userTen %>&nbsp;
									</tr>
								</table></TD>
						</TR>
					</TABLE>
					<TABLE width="100%" cellpadding="0" cellspacing="2">
						<TR>
							<TD>
								<TABLE width="100%" border="0" cellpadding="0" cellspacing="0">
									<TR class="tbdarkrow">
										<TD width="30" align="left">
										
										<%if (view.equals("TT")){ %>
										<A href="../../RouterSvl?task=<%= Utility.dongMa(getServletContext().getInitParameter("RedirectNoScript") +"KhachhangSvl?userId="+userId+"&view="+view)%>"><img src="../images/Back30.png" alt="Quay ve" title="Quay ve" width="30" height="30" border="1" longdesc="Quay ve" style="border-style: outset"> </A>
										<%} else { %>
										<A href="../../RouterSvl?task=<%= Utility.dongMa(getServletContext().getInitParameter("RedirectNoScript") +"KhachhangSvl?userId="+userId) %>"><img src="../images/Back30.png" alt="Quay ve" title="Quay ve" width="30" height="30" border="1" longdesc="Quay ve" style="border-style: outset"> </A>
										<%} %>
										
										</TD>
										<TD width="2" align="left"></TD>
										<TD width="30" align="left">
											<div id="btnSave">
												
											</div></TD>
										<TD align="left">&nbsp;</TD>
									</TR>
								</TABLE></TD>
						</TR>
					</TABLE>
					<TABLE width="100%" border="0" cellpadding="0" cellspacing="0">
						<tr>
							<TD align="left" colspan="4" class="legendtitle">
								<FIELDSET>
									<LEGEND class="legendtitle"><%=Utility.GLanguage("B??o l???i nh???p li???u",session,jedis) %></LEGEND>
									<textarea name="dataerror"
										style="width: 100%; color: #F00; font-weight: bold"
										style="width:100%" rows="1"><%= khBean.getMessage() %></textarea>
									<%khBean.setMessage(""); %>
								</FIELDSET></TD>
						</tr>

						<TR>
							<TD height="100%" width="100%">
								<FIELDSET>
									<LEGEND class="legendtitle" style="color: black"><%=Utility.GLanguage("Th??ng tin kh??ch h??ng",session,jedis) %></LEGEND>
									<TABLE border="0" width="100%" cellpadding="6" cellspacing="0">
									<TR>
									<TD class="plainlabel" colspan="1"><%=Utility.GLanguage("M?? kh??ch h??ng",session,jedis) %><FONT class="erroralert"> *</FONT></TD>
									<TD class="plainlabel" colspan="1" ><INPUT  type="text" readonly name="maFAST" value="<%= khBean.getMaFAST() %>" ></TD>
									<TD class="plainlabel" colspan="1"><%=Utility.GLanguage("V??? tr?? kh??ch h??ng",session,jedis) %>
											</TD>
									<TD class="plainlabel" colspan="3">
									<SELECT name="vitri" id="vitri" >
													<option value=""></option>
													<%  if (vtch!=null)
														{
															try
															{
																while(vtch.next())
																{ 
											    					if (vtch.getString("vtchId").equals(khBean.getVtchId())){ 	
											    			%>
																<option value='<%= vtch.getString("vtchId") %>' selected><%=vtch.getString("vtchTen") %></option>
																<%}else{ %>
																<option value='<%= vtch.getString("vtchId") %>'><%= vtch.getString("vtchTen") %></option>
																<%}} vtch.close(); }catch(java.sql.SQLException e){e.printStackTrace();} 
														}	%>	
												</SELECT> </TD>
									</TR>		
									<TR>
											<TD  class="plainlabel" colspan="1"><%=Utility.GLanguage("T??n kh??ch h??ng",session,jedis) %><FONT class="erroralert"> *</FONT>
											</TD>
											<TD class="plainlabel" colspan="1"><INPUT type="text" name="khTen" id="khTen" value="<%= khBean.getTen() %>">
											</TD>
											<TD class="plainlabel" colspan="1"><%=Utility.GLanguage("Lo???i kh??ch h??ng",session,jedis) %></TD>
											<TD  class="plainlabel"  colspan="3" >
												<SELECT name="lchId" id="lchId" onChange="submitform();">
													<option value=""></option>
													<%  if (lch!=null)
														{
															try
															{
																while(lch.next())
																{ 
											    					if (lch.getString("lchId").equals(khBean.getLchId())){ 	
											    			%>
																<option value='<%= lch.getString("lchId") %>' selected><%=lch.getString("lchTen") %></option>
																<%}else{ %>
																<option value='<%= lch.getString("lchId") %>'><%= lch.getString("lchTen") %></option>
																<%}} lch.close(); }catch(java.sql.SQLException e){e.printStackTrace();} 
														}	%>	
												</SELECT>
											</TD>
											<%-- <TD class="plainlabel">M?? c??<FONT class="erroralert"> *</FONT></TD>
											<TD class="plainlabel" ><INPUT  type="text" name="macu" value="<%= khBean.getMacu() %>"></TD> --%>																																							
											
									<TR> 
									<TD class="plainlabel" colspan="1"><%=Utility.GLanguage("T??n c???a hi???u",session,jedis) %> <FONT
												class="erroralert"> *</FONT></TD>
											  <TD class="plainlabel" colspan="1"><INPUT type="text"
												name="tencuahieu" id="tencuahieu" value = "<%=khBean.getTencuahieu()%>"></TD>
												<TD class="plainlabel" colspan="1"><%=Utility.GLanguage("H???ng kh??ch h??ng",session,jedis) %></TD>
											<TD class="plainlabel" colspan="3"><SELECT name="hangcuahangId">
													<option value=""></OPTION>
													<% if (hch!=null) 
													while(hch.next())
													{
														if (khBean.getHchId()!=null)
														{
															if (khBean.getHchId().indexOf(hch.getString("hchId")) >= 0)
															{
																%>
																<option value="<%= hch.getString("hchId") %>" selected><%= hch.getString("hchTen") %></option>														
																<%
															}
															else
															{
																%>
																<option value="<%= hch.getString("hchId") %>"><%= hch.getString("hchTen") %></option>														
																<%
															}
														}
														else
														{
															%>
															<option value="<%= hch.getString("hchId") %>"><%= hch.getString("hchTen") %></option>														
															<%
														}
													}													
													%>
											</SELECT></TD>
												<%-- <TD class="plainlabel">V?? ????? (LAT)</TD>
											    <TD class="plainlabel"><INPUT type="text"
												name="lat" id="lat" value = "<%=khBean.getToado_lat()%>"></TD>
												
												<TD class="plainlabel">Kinh ????? (LONG) </TD>
												<TD class="plainlabel"><INPUT type="text"
												name="long" id="long" value = "<%=khBean.getToado_long()%>"></TD> --%>
									
								<!-- 	<TD class="plainlabel" colspan="1">Nh??m kh??ch h??ng </TD>
									<TD class="plainlabel" colspan="2"><input type="text"></TD> -->
									
									</TR>	
									<TR>
									<TD class="plainlabel" colspan="1"><%=Utility.GLanguage("Ng?????i mua h??ng",session,jedis) %><FONT
												class="erroralert"> *</FONT></TD>
											<TD class="plainlabel" colspan="1"><INPUT type="text"
												name="nguoimuahang" id="nguoimuahang"
												value="<%= khBean.getNguoimuahang() %>" size="40">
											</TD>
									<TD class="plainlabel" colspan="1"><%=Utility.GLanguage("Th??nh th??? n??ng th??n",session,jedis) %></TD>
											<TD  class="plainlabel" colspan="3">
												<SELECT name="thanhthinongthonId"
													id="thanhthinongthonId">
														<option value="null"></option>
														<%if (thanhthinongthonRs != null){ 
									      		try{
									      			while(thanhthinongthonRs.next()){ 
									    			if (thanhthinongthonRs.getString("pk_seq").equals(khBean.getThanhthinongthonId())){ %>
														<option value='<%=thanhthinongthonRs.getString("pk_seq")%>' selected><%=thanhthinongthonRs.getString("ten") %></option>
														<%}else{ %>
														<option value='<%=thanhthinongthonRs.getString("pk_seq")%>'><%=thanhthinongthonRs.getString("ten") %></option>
														<%}}}catch(java.sql.SQLException e){} 
									     		
									      		}	%>
	
												</SELECT>
											</TD>		
									
									</TR>	
									<TR>
									<TD class="plainlabel" colspan="1"><%=Utility.GLanguage("?????a ch??? giao h??ng",session,jedis) %><FONT
												class="erroralert"> *</FONT>
											</TD>
											
											<TD  class="plainlabel" colspan="1"><INPUT type="text" 
												name="diachigiaohang" id="diachigiaohang" value="<%= khBean.getDiachigiaohang() %>"
												size="40" style="width:200px;"> 
											</TD>
									<TD class="plainlabel" colspan="1"><%=Utility.GLanguage("?????a ch??? kh??ch h??ng",session,jedis) %></TD>
											
											<TD  class="plainlabel" colspan="3"><INPUT type="text" readonly
												name="diachi" id="diachi" value="<%= khBean.getDiachi() %>"
												size="40" style="width:200px;"> 
											</TD>
											
									</TR>
									<TR>
									<TD class="plainlabel" colspan="1"><%=Utility.GLanguage("S??? nh??",session,jedis) %> <FONT
												class="erroralert"> *</FONT></TD>
									<TD class="plainlabel" colspan="1"><INPUT type="text"
												name="sonha" id="sonha" value = "<%=khBean.getSonha()%>"></TD>
									<TD class="plainlabel" colspan="1"><%=Utility.GLanguage("T??n ???????ng",session,jedis) %> <FONT
												class="erroralert"> *</FONT></TD>
									<TD class="plainlabel" colspan="1"><INPUT type="text"
												name="tenduong" id="tenduong" value = "<%=khBean.getTenduong()%>"></TD>
									<TD class="plainlabel" colspan="1"><%=Utility.GLanguage("???p/kh??m/t???",session,jedis) %> </TD>
									<TD class="plainlabel" colspan="1"><INPUT type="text"
												name="apto" id="apto" value = "<%=khBean.getApto()%>"></TD>
									</TR>
									<TR>
									<TD class="plainlabel" colspan="1"><%=Utility.GLanguage("T???nh /Th??nh ph???",session,jedis) %><FONT
												class="erroralert"> *</FONT>
											</TD>
											<TD class="plainlabel" colspan="1"><SELECT disabled name="tpId" id="tpId" 
												onChange="submitform();">
													<option value=""></option>
													<% 
													if (tp!=null)
													{
														try{while(tp.next()){ 
													    	if (tp.getString("tpId").equals(khBean.getTpId())){ %>
																		<option value='<%=tp.getString("tpId")%>' selected><%=tp.getString("tpTen") %></option>
																		<%}else{ %>
																		<option value='<%=tp.getString("tpId")%>'><%=tp.getString("tpTen") %></option>
																		<%}}}catch(java.sql.SQLException e){}
																		
													}
													
													%>
											</SELECT></TD>
									<TD  class="plainlabel" colspan="1"><%=Utility.GLanguage("Qu???n/Huy???n",session,jedis) %> <FONT
												class="erroralert"> *</FONT>
											</TD>
											<TD  class="plainlabel" colspan="1"><SELECT  disabled name="qhId"
												id="qhId" onChange="submitform();">
													<option value=""></option>
													<%if (qh != null){ 
								      		try{while(qh.next()){ 
								    			if (qh.getString("qhId").equals(khBean.getQhId())){ %>
													<option value='<%=qh.getString("qhId")%>' selected><%=qh.getString("qhTen") %></option>
													<%}else{ %>
													<option value='<%=qh.getString("qhId")%>'><%=qh.getString("qhTen") %></option>
													<%}}}catch(java.sql.SQLException e){} 
								      		}	%>
											</SELECT></TD>
											
											<TD class="plainlabel" colspan="1"><%=Utility.GLanguage("Ph?????ng x??",session,jedis) %> <FONT class="erroralert">*</FONT></TD>
											 <TD class="plainlabel" >
											 <SELECT name="phuongxaId"
													id="phuongxaId">
														<option value=""></option>
														<%
														
													
														if (phuongxaRs != null){ 
															phuongxaRs.beforeFirst();
									      					try{
									      						while(phuongxaRs.next()){ 
									    							if (phuongxaRs.getString("pk_seq").equals(khBean.getPhuongxa())){ %>
														<option value='<%=phuongxaRs.getString("pk_seq")%>' selected><%=phuongxaRs.getString("ten") %></option>
														<%}else{ %>
														<option value='<%=phuongxaRs.getString("pk_seq")%>'><%=phuongxaRs.getString("ten") %></option>
														<%}}}catch(java.sql.SQLException e){} 
									     		
									      		}	%>
	
												</SELECT>
												</TD>
									</TR>
									<TR>
											<TD class="plainlabel" colspan="1"><%=Utility.GLanguage("Ng??y sinh",session,jedis) %></TD>
											<TD class="plainlabel" colspan="1">
											<INPUT type="text" class="days" name="ngaysinh" readonly value="<%= khBean.getNgaysinh() %>" >
											</TD>
											<TD class="plainlabel" colspan="1"><%=Utility.GLanguage("M?? s??? thu???",session,jedis) %></TD>
											<TD class="plainlabel" colspan="1"><INPUT type="text"
												name="masothue" value="<%= khBean.getMasothue() %>"
												size="15">
											</TD>
												
											<TD class="plainlabel" colspan="1"><%=Utility.GLanguage("CMND",session,jedis) %></TD>
											<TD class="plainlabel" colspan="1"><INPUT type="text"
												name="cmnd" value="<%=khBean.getCmnd() %>">
											</TD>
									</TR>
									<TR>
											<TD class="plainlabel" colspan="1"><%=Utility.GLanguage("??i???n tho???i",session,jedis) %></TD>
											<TD class="plainlabel" colspan="1"><INPUT type="text"
												name="dienthoai" value="<%= khBean.getSodienthoai() %>"
												size="15">
											</TD>
											<TD class="plainlabel" colspan="1"><%=Utility.GLanguage("Di ?????ng",session,jedis) %></TD>
											<TD class="plainlabel" colspan="1"><INPUT type="text"
												name="didong" value="<%= khBean.getDidong() %>"
												size="15">
											</TD>
												<TD class="plainlabel" colspan="1"><%=Utility.GLanguage("M?? h???p ?????ng",session,jedis) %></TD>
											<TD class="plainlabel" colspan="1"><INPUT type="text"
												name="mahd" value="<%= khBean.getMahd() %>"
												size="15">
											</TD>
										</TR>
										<TR>
										<TD class="plainlabel" colspan="1"><%=Utility.GLanguage("M???c chi???t kh???u",session,jedis) %></TD>
											<TD class="plainlabel" colspan="1"><input type="text"
												name="ptCHIETKHAU" value="<%= khBean.getPT_Chietkhau() %>"
												style="text-align: right;"> %
												<TD class="plainlabel" colspan="1"><%=Utility.GLanguage("K??nh b??n h??ng",session,jedis) %><FONT
												class="erroralert">*</FONT>
											</TD>
											<TD class="plainlabel" colspan="1"><SELECT name="kbhTen" id="kbhTen"
												onChange="submitform();">

													<% 
													if (kbh!=null)
													{
														try{while(kbh.next()){ 
													    	if (kbh.getString("kbhId").equals(khBean.getKbhId())){ %>
																		<option value='<%= kbh.getString("kbhId") %>' selected><%=kbh.getString("kbhTen") %></option>
																		<%}else{ %>
																		<option value='<%= kbh.getString("kbhId") %>'><%= kbh.getString("kbhTen") %></option>
																		<%}}}catch(java.sql.SQLException e){} 
																		
													}
													
								     				%>
											</SELECT>
											</TD>
											<TD class="plainlabel" colspan="1">Kho<FONT class="erroralert">*</FONT></TD>
												<TD class="plainlabel"  colspan="1">
													<SELECT name="khoId" id ="khoId" >
													<!-- <OPTION value="" selected></OPTION> -->
													<% 
													if (khoRs!=null)
													{
														try{while(khoRs.next()){ 
													    	if (khoRs.getString("PK_SEQ").equals(khBean.getkhoId())){ %>
																	<option value='<%= khoRs.getString("PK_SEQ") %>' selected><%=khoRs.getString("Ten") %></option>
																	<%}else{ %>
																	<option value='<%= khoRs.getString("PK_SEQ") %>'><%= khoRs.getString("Ten") %></option>
																	<%}} khoRs.close(); }catch(java.sql.SQLException e){} 
																	
													}
													
									    			 %>
													</SELECT>
												</TD>
										</TR>
										<TR>
											<TD class="plainlabel" colspan="1"><%=Utility.GLanguage("S??? ti???n n???",session,jedis) %></TD>
											<TD class="plainlabel" colspan="1"><INPUT type="text"
												name="sotienno" value="<%=formatter.format(khBean.getSotienno()) %>"
												size="11">
											</TD>
										<TD class="plainlabel" colspan="1"><%=Utility.GLanguage("S??? ng??y n???",session,jedis) %></TD>
											<TD class="plainlabel" colspan="1"><INPUT type="text"
												name="songayno" value="<%=formatter.format(khBean.getSongayno()) %>"
												size="15">
											</TD>	
											
											<TD class="plainlabel" colspan="1"><%=Utility.GLanguage("M?? kho SAP",session,jedis) %></TD>
											<TD class="plainlabel" colspan="1">
											<SELECT  name="makhoSAP" id="makhoSAP" >
													<option value="null"></option> 
													<% 
													if (khoSAP!=null)
													{
														try{ while(khoSAP.next()){ 
											    			if (khBean.getMakhoSAP() != null && khBean.getMakhoSAP().equals(khoSAP.getString("MakhoSAP"))){ %>
															<option value='<%=khoSAP.getString("MakhoSAP")%>' selected>
																<%=khoSAP.getString("MakhoSAP") %></option>
															<%}else{ %>
															<option value='<%=khoSAP.getString("MakhoSAP")%>'>
																<%=khoSAP.getString("MakhoSAP") %></option>
															<%}}}catch(java.sql.SQLException e){} 
															
													}
													%>
											</SELECT>
											</TD>													
										</TR>
										<TR>
										<TD class="plainlabel" colspan="1"><%=Utility.GLanguage("NH??N VI??N B??N H??NG",session,jedis) %></TD>
											<TD class="plainlabel" colspan="1">
											<SELECT  name="ddkdId" id="ddkdId" onChange="submitform();">
													<option value=""></option>
													<% 
													if (ddkdRs!=null)
													{
														try{ while(ddkdRs.next()){ 
											    			if ( khBean.getDdkdId().indexOf(ddkdRs.getString("ddkdId")) >=0){ %>
															<option value='<%=ddkdRs.getString("ddkdId")%>' selected>
																<%=ddkdRs.getString("ddkdTen") %></option>
															<%}else{ %>
															<option value='<%=ddkdRs.getString("ddkdId")%>'>
																<%=ddkdRs.getString("ddkdTen") %></option>
															<%}}}catch(java.sql.SQLException e){} 
															
													}
													
													%>
											</SELECT>
											</TD>
											<TD class="plainlabel"  colspan="1"><%=Utility.GLanguage("Nh??n vi??n giao nh???n",session,jedis) %>
											</TD>
											<TD class="plainlabel" colspan="1"><SELECT name="nvgnTen" id="nvgnTen">
													<OPTION value="" ></OPTION>
													<% 
													if (nvgn!=null)
													{
														try
														{
															while(nvgn.next())
															{ 
										    					if (khBean.getNvgnId().indexOf(nvgn.getString("nvgnId"))>=0){ %>
														<option value='<%= nvgn.getString("nvgnId") %>' selected><%=nvgn.getString("nvgnTen") %></option>
														<%}else{ %>
														<option value='<%= nvgn.getString("nvgnId") %>'><%= nvgn.getString("nvgnTen") %></option>
														<%}} nvgn.close(); }catch(java.sql.SQLException e){e.printStackTrace();} 
														
													}
													
									     			%>
											</SELECT></TD>
											<%-- <TD  class="plainlabel" colspan="1">?????a b??n</TD>
											<TD  class="plainlabel" colspan="1"> 
												<SELECT name="DiabanId" id="DiabanId"
													onChange="submitform();">
														<option value=""></option>
														<%
							                             if (diabanRs != null){
							                            	 while (diabanRs.next()){                      				                       				
							                       				 if (diabanRs.getString("pk_seq").equals(khBean.getDiabanId())){ %>
							                       				<option   value ="<%= diabanRs.getString("pk_seq") %>" selected="selected"> <%=diabanRs.getString("TEN") %></option>
							                       				<%}else{ %>
							                       				<option value ="<%=diabanRs.getString("pk_seq") %>"> <%=diabanRs.getString("TEN") %></option>
							                       				<%}     		
					                            	 }
					                             } %>
												</SELECT>
											</TD> --%>
											
											
											<%-- </TD>
											<TD  class="plainlabel"><%=Utility.GLanguage("Khu v???c",session,jedis) %></TD>
											<TD  class="plainlabel"> 
												<SELECT name="khuvucId" id="khuvucId"
													onChange="submitform();">
														<option value=""></option>
														<%
							                             if (khuvucRs != null){
							                            	 while (khuvucRs.next()){                      				                       				
							                       				 if (khuvucRs.getString("pk_seq").equals(khBean.getKhuvucId())){ %>
							                       				<option   value ="<%= khuvucRs.getString("pk_seq") %>" selected="selected"> <%=khuvucRs.getString("TEN") %></option>
							                       				<%}else{ %>
							                       				<option value ="<%=khuvucRs.getString("pk_seq") %>"> <%=khuvucRs.getString("TEN") %></option>
							                       				<%}     		
					                            	 }
					                             } %>
												</SELECT>
											</TD> --%>
											
											<TD  class="plainlabel"><%=Utility.GLanguage("NPP Kh??c",session,jedis) %></TD>
											<TD class="plainlabel" colspan="3">
												<SELECT  name="nppBanChungId"  multiple="multiple">
														<option value=""></option>
														<% 
														if (nppBanChungRs!=null)
														{
															try{ while(nppBanChungRs.next()){ 
												    			if (Utility.ktDaChonTrongRs(nppBanChungRs.getString("pk_seq"),khBean.getNppBanChungId(),",")){ %>
																<option value='<%=nppBanChungRs.getString("pk_seq")%>' selected>
																	<%=nppBanChungRs.getString("ten") %></option>
																<%}else{ %>
																<option value='<%=nppBanChungRs.getString("pk_seq")%>'>
																	<%=nppBanChungRs.getString("ten") %></option>
																<%}}}catch(java.sql.SQLException e){}		
														}
														%>
												</SELECT>
											</TD> 
										</TR>
										
										
										<TR>
										<TD class="plainlabel" colspan="1"><%=Utility.GLanguage("V???/ch???ng",session,jedis) %></TD>
										<TD class="plainlabel" colspan="1"><INPUT type="text"
												name="vochong" id="vochong" value = "<%=khBean.getVochong()%>"></TD>
										<TD class="plainlabel" colspan="1"><%=Utility.GLanguage("Ng??y sinh v???/ch???ng",session,jedis) %></TD>
										<TD class="plainlabel" colspan ="3"><INPUT type="text" class ="days" readonly
												name="ngsinhvc" id="ngsinhvc" value = "<%=khBean.getNgsinh_vochong()%>"></TD>
										</TR>
										<TR>
										<TD class="plainlabel" colspan="1"><%=Utility.GLanguage("Con 1",session,jedis) %></TD>
										<TD class="plainlabel" colspan="1"><INPUT type="text"
												name="con1" id="con1" value = "<%=khBean.getCon1()%>"></TD>
										<TD class="plainlabel"  colspan="1"><%=Utility.GLanguage("Ng??y sinh con 1",session,jedis) %></TD>
										<TD class="plainlabel" colspan ="3"><INPUT type="text" class="days" readonly
												name="ngsinhc1" id="ngsinhc1" value = "<%=khBean.getNgsinh_con1()%>"></TD>
										</TR>
											<TR>
										<TD class="plainlabel" colspan="1"><%=Utility.GLanguage("Con 2",session,jedis) %></TD>
										<TD class="plainlabel" colspan="1"><INPUT type="text"
												name="con2" id="con2" value = "<%=khBean.getCon2()%>"></TD>
										<TD class="plainlabel" colspan="1"><%=Utility.GLanguage("Ng??y sinh con 2",session,jedis) %></TD>
										<TD class="plainlabel" colspan ="3"><INPUT type="text" class="days" readonly
												name="ngsinhc2" id="ngsinhc2" value = "<%=khBean.getNgsinh_con2()%>"></TD>
										</TR>
										<TR>
										<TD class="plainlabel" colspan="1"><%=Utility.GLanguage("T??i tr???",session,jedis) %></TD>
										<TD class="plainlabel" colspan="1"><INPUT type="text" name="taitro" id="taitro" value="<%= khBean.getTaitro() %>" >
										<TD class="plainlabel" colspan="1"><%=Utility.GLanguage("Ng??y t??i tr???",session,jedis) %></TD>
										<TD class="plainlabel" colspan="3"><INPUT type="text" class="days" readonly name="ngaytaitro" id="ngaytaitro" value="<%= khBean.getNgaytaitro() %>" >
										</TR>
										<TR>
										<TD class="plainlabel"><%=Utility.GLanguage("Ghi ch??",session,jedis) %></TD>
										<TD class="plainlabel" colspan="6"><INPUT type="text" name="ghichu" id="ghichu" value = "<%=khBean.getGhichu()%>"></TD>
										</TR>
									<%if (1==1) {%>
									<TR>

									<%} %>	
																
										<TR>										
											<%-- <TD class="plainlabel">?????a ch??? Giao H??ng
											</TD>
											
											<TD  class="plainlabel" ><INPUT type="text"
												name="DiaChiGiaoHang" id="DiaChiGiaoHang" value="<%= khBean.getDiachigiaohang() %>"
												size="40">  --%>
											<TD class="plainlabel"><%=Utility.GLanguage("Ho???t ?????ng",session,jedis) %></TD> 
											<TD class="plainlabel" colspan="5"> 
											<% if (khBean.getTrangthai().equals("1")){%>
												<input name="trangthai" type="checkbox" value="1" checked="checked"> 
												<%} else {%> <input name="trangthai" type="checkbox" value="0"> <%} %>
											</TD>
											
										</TR>
										
										<TR>										
											<TD class="plainlabel">Shopkey</TD>
											<TD class="plainlabel"> 
											<% if (khBean.getShopkey().equals("1")){%>
												<input name="shopkey" type="checkbox" value="1" checked="checked"> 
												<%} else {%> <input name="shopkey" type="checkbox" value="1"> <%} %>
											</TD>
											
											<TD class="plainlabel">M???t kh???u<%=Utility.GLanguage("",session,jedis) %></TD>
											<TD class="plainlabel" colspan="3"><input name="matkhau" id="matkhau" type="password" value=""></TD>
										</TR>
										
										<TR>
											<%-- <SELECT name="phuongxaId"
													id="phuongxaId">
														<option value="null"></option>
														<%if (phuongxaRs != null){ 
									      		try{while(phuongxaRs.next()){ 
									    			if (phuongxaRs.getString("pk_seq").equals(khBean.getPhuongxaId())){ %>
														<option value='<%=phuongxaRs.getString("pk_seq")%>' selected><%=phuongxaRs.getString("ten") %></option>
														<%}else{ %>
														<option value='<%=phuongxaRs.getString("pk_seq")%>'><%=phuongxaRs.getString("ten") %></option>
														<%}}}catch(java.sql.SQLException e){} 
									     		
									      		}	%>
	
												</SELECT>
												
												<a class="taomoitc" href="#">
			                        			<img src="../images/New.png" width="20" height="20" style="margin-bottom:-5; margin-left:7px;" title="T???o m???i ph?????ng/x??"></a>
								                <div style='display:none'>
							                        <div id="taomoitc" style='padding:0px 5px; background:#fff;'>
							                        	<h4 align="left" style="text-decoration: underline;"><%=Utility.GLanguage("T???o m???i",session,jedis) %> ph?????ng x??</h4>
														<table cellpadding="4px" cellspacing="0px" width="100%" align="center">
														
							                            	<tr>
							                                	<TD class="plainlabel" width="40%" valign="top" align="left"><%=Utility.GLanguage("Di???n gi???i",session,jedis) %></td>
							                                    <td class="plainlabel" valign="top" align="left">
								                                    <input type="text" name="pxTen" id="pxTen" value="" />
							                                    </td>
							                                </tr>
							                                 <tr>
							                                	<td class="plainlabel" valign="top" align="left" colspan="2">
							        								<a class="btn btn-default" href="javascript:submitForm2();">L??u l???i</a>
							        							</td>
							                                </tr>
							                            </table>
													</div>
								                </div> --%>
												

										<TD width="120px" class="plainlabel" valign="top" ><%=Utility.GLanguage("Gi???i t??nh",session,jedis) %></TD>
									 	 <TD  class="plainlabel" valign="top" colspan="1">
										 <SELECT name ="gioitinh"   class = "select2" style = "width:200px">
                               
	                                        <% if (khBean.getGioiTinh().equals("1")){%>
	                                              <option value="1" selected><%=Utility.GLanguage("Nam",session,jedis) %></option>
	                                              <option value="0"><%=Utility.GLanguage("N???",session,jedis) %></option>
	                                              <option value="3"></option>
	                                              
	                                        <%}else if (khBean.getGioiTinh().equals("0")) {%>
	                                              <option value="0" selected><%=Utility.GLanguage("N???",session,jedis) %></option>
	                                              <option value="1" ><%=Utility.GLanguage("Nam",session,jedis) %></option>
	                                              <option value="3" ></option>
	                                              
	                                        <%}else{%>                                                                                        
	                                              <option value="1" ><%=Utility.GLanguage("Nam",session,jedis) %></option>
	                                              <option value="0" ><%=Utility.GLanguage("N???",session,jedis) %></option>
	                                              <option value="3" selected></option>
	                                        <%}%>
	                                     </SELECT>
									 </TD>
									 
									<TD class="plainlabel"><%=Utility.GLanguage("M???i quan h??? v???i ch??? nh?? thu???c",session,jedis) %> </TD>	
									<TD  class="plainlabel" ><INPUT type="text" name="moiquanhe" 
									id="moiquanhe" value="<%= khBean.getMoiquanhe() %>" size="40"></TD>
									
									<TD class="plainlabel"><%=Utility.GLanguage("Th???i gian g???p ch??? nh?? thu???c",session,jedis) %> </TD>	
									<TD  class="plainlabel" ><INPUT type="text" name="thoigiangap" id="thoigiangap" 
									class="days" readonly="readonly" value="<%= khBean.getThoigiangap() %>" size="40"></TD>      
												 
								</TR>
							<TR>
										
											
											<%-- <TD class="plainlabel">Thanh to??n Th??ng</TD>
											<TD class="plainlabel" colspan="2"><SELECT
												name="thanhtoan" id="thanhtoan">
													<option value=""></option>
													<%	if (khBean.getThanhtoan().equals("0") ){ %>
													<option value='0' selected="selected">Ti???n m???t</option>
													<option value='1'>H??a ????n</option>
													<% }else { %>
													<option value='0'>Ti???n m???t</option>
													<option value='1' selected="selected">H??a ????n</option>
													<% } %>
											</SELECT> &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; Thanh to??n Qu??
												&nbsp;&nbsp; <SELECT name="thanhtoanQUY" id="thanhtoanQUY">
													<option value=""></option>
													<%	if (khBean.getThanhtoanQuy().equals("0") ){ %>
													<option value='0' selected="selected">Ti???n m???t</option>
													<option value='1'>H??a ????n</option>
													<% }else { %>
													<option value='0'>Ti???n m???t</option>
													<option value='1' selected="selected">H??a ????n</option>
													<% } %>
											</SELECT></TD> --%>
										<%-- 	<TD class="plainlabel">M?? S??? Thu??? C?? Nh??n</TD>
											<TD class="plainlabel" colspan="1">
												<INPUT type="text" name="mst_canhan" value="<%= khBean.getMst() %>" size="15">
											</TD> --%>
										</TR>
										
									<%-- 	<TR>
											<TD  class="plainlabel" style='display:none'><SELECT name="xuatkhau">
												<option value="0"></option>
													
													<% if (khBean.getXuatkhau().equals("0") ) { %>
													<option value="0" selected="selected">B??n l???</option>
													<% } else { %>
													<option value="0">B??n l???</option>
													<% } %>
													<% if (khBean.getXuatkhau().equals("1") ) { %>
													<option value="1" selected="selected">B??n bu??n</option>
													<% } else { %>
													<option value="1">B??n bu??n</option>
													<% } %>
													<% if (khBean.getXuatkhau().equals("2") ) { %>
													<option value="2" selected="selected">B??n bu??n v??
														b??n l???</option>
													<% } else { %>
													<option value="2">B??n bu??n v?? b??n l???</option>
													<% } %> 
													 <% if (khBean.getXuatkhau().equals("3") ) { %>
													<option value="3" selected="selected">B??n l??? v??
														b??n bu??n</option>
													<% } else { %>
													<option value="3">B??n l??? v?? b??n bu??n</option>
													<% } %> 
											</SELECT></TD>
											<TD class="plainlabel">H???p ?????ng</TD>
											<TD class="plainlabel" colspan="2"><INPUT type="text">
											</TR> --%>
										<tr>
											<%-- <TD class="plainlabel">T??n k?? h???p ?????ng:	</TD>
											<TD class="plainlabel"><INPUT type="text"
												name="tenkyhd" id="tenkyhd" value="<%= khBean.getTenKyHd() %>"
												size="40">
											</TD>	 --%>
											
											<TD class="plainlabel" colspan="6"></TD>							

										</TR>
										<TR>
											<% if (khBean.getKbhId().equals("100052")) {%>
											<TD class="plainlabel"><%=Utility.GLanguage("M???u h??a ????n",session,jedis) %></TD>
											<TD class="plainlabel" colspan="10"><SELECT
												name="mauhoadon" id="mauhoadon">
													<% if (khBean.getmauhd().equals("1")){%>
													<option value="1" selected><%=Utility.GLanguage("M???u 1(CN)",session,jedis) %></option>
													<option value="2"><%=Utility.GLanguage("M???u 2(HO)",session,jedis) %></option>
													<%}else if (khBean.getmauhd().equals("2")) {%>
													<option value="1" ><%=Utility.GLanguage("M???u 1(CN)",session,jedis) %></option>
													<option value="2" selected>M???u 2(HO)<%=Utility.GLanguage("",session,jedis) %></option>
													<%} else {%>
													<option value="1" ><%=Utility.GLanguage("M???u 1(CN)",session,jedis) %></option>
													<option value="2"><%=Utility.GLanguage("M???u 2(HO)",session,jedis) %></option>
													<%} %>
											</SELECT></TD>
											<%} else {%>
											<!-- <TD class="plainlabel" colspan="10"></TD> -->
											<%} %>
										</TR>
										<TR style ="display: none">
											<TD class="plainlabel"><%=Utility.GLanguage("H??nh th???c thanh to??n",session,jedis) %></TD>
											<TD class="plainlabel"><INPUT type="text"
												name="hinhthuctt" value="<%=khBean.getHinhthucTT() %>" size="11" readonly="readonly">
											</TD>
										</TR>
										<TR  style="vertical-align: top;">										
											<TD class="plainlabel">???nh 1</TD>
											<TD class="plainlabel"><img src="<%=getServletContext().getInitParameter("pathhinhJSP")+"AnhDaiDien/"+khBean.getAnh(0) %>" alt="???nh 1" width="100" height="100" onclick="window.open('<%=getServletContext().getInitParameter("pathhinhJSP")+"AnhDaiDien/"+khBean.getAnh(0) %>', '_blank');"/></TD>
											<TD class="plainlabel">???nh 2</TD>
											<TD class="plainlabel" ><img src="<%=getServletContext().getInitParameter("pathhinhJSP")+"AnhDaiDien/"+khBean.getAnh(1) %>" alt="???nh 2" width="100" height="100" onclick="window.open('<%=getServletContext().getInitParameter("pathhinhJSP")+"AnhDaiDien/"+khBean.getAnh(1) %>', '_blank');"/></TD>
											<TD class="plainlabel" >???nh ?????i di???n</TD>
											<TD class="plainlabel" ><img src="<%=getServletContext().getInitParameter("pathhinhJSP")+"AnhDaiDien/"+khBean.getTenAnhDaiDien() %>" alt="???nh ?????i di???n" width="100" height="100" onclick="window.open('<%=getServletContext().getInitParameter("pathhinhJSP")+"AnhDaiDien/"+khBean.getTenAnhDaiDien() %>', '_blank');"/></TD>>
										</TR>
										
										<TR style="vertical-align: top;">										
											<TD class="plainlabel">???nh 3</TD>
											<TD class="plainlabel"><img src="<%=getServletContext().getInitParameter("pathhinhJSP")+"AnhDaiDien/"+khBean.getAnh(2) %>" alt="???nh 3" width="100" height="100" onclick="window.open('<%=getServletContext().getInitParameter("pathhinhJSP")+"AnhDaiDien/"+khBean.getAnh(2) %>', '_blank');"/></TD>
											<TD class="plainlabel">???nh 4</TD>
											<TD class="plainlabel" ><img src="<%=getServletContext().getInitParameter("pathhinhJSP")+"AnhDaiDien/"+khBean.getAnh(3) %>" alt="???nh 4" width="100" height="100" onclick="window.open('<%=getServletContext().getInitParameter("pathhinhJSP")+"AnhDaiDien/"+khBean.getAnh(3) %>', '_blank');"/></TD>
											<TD class="plainlabel" ></TD>
											<TD class="plainlabel" ></TD>
										</TR>
									</TABLE>
								</FIELDSET></TD>
							</TR>
					</TABLE>
					<TABLE width="100%" border="0" cellpadding="0" cellspacing="0">
						<TR>
							<TD colspan="6" width="100%">
								<FIELDSET>
									<LEGEND class="legendtitle"><%=Utility.GLanguage("Tuy???n b??n h??ng",session,jedis) %> </LEGEND>
									<TABLE width="100%" border="0" cellspacing="1" cellpadding="6">
										<TR class="tbheader">
											<TH width="10%"><%=Utility.GLanguage("STT",session,jedis) %></TH>
											<TH width="60%"><%=Utility.GLanguage("Tuy???n",session,jedis) %></TH>
											<TH width="20%"><%=Utility.GLanguage("T???n s???",session,jedis) %></TH>
											<TH width="10%"><%=Utility.GLanguage("Ch???n",session,jedis) %></TH>
										</TR>

										<%
								int i = 0;
								String lightrow = "tblightrow";
								String darkrow = "tbdarkrow";
								if (tbhRs != null){
								try{while(tbhRs.next()){ 
									if (i % 2 != 0) {%>
										<TR class=<%=lightrow%>>
											<%} else {%>
										
										<TR class=<%= darkrow%>>
											<%}%>
											<TD align="center"><input name='sott' type="text"
												value="<%=tbhRs.getString("sott") %>" readonly="readonly"
												style="width: 100%">
											</TD>
											<TD align="center"><div align="left"><%= tbhRs.getString("tbhTen") %>
												</div>
											</TD>

											<TD align="center">
												<% 
												String[] tenloaicuahang = Utility.getTanSo(); //new  String[] {"F1-1","F1-2","F1-3","F1-4","F2-1","F2-2","F4","F8","F12"} ; 
									  			String[] idloaicuahang = Utility.getTanSo(); //new  String[] {"F1-1","F1-2","F1-3","F1-4","F2-1","F2-2","F4","F8","F12"}  ;
								  %> <select style="width: 130px" name="tanso">
													<option value=""></option>
													<% for( int j=0;j<idloaicuahang.length;j++) { 
								    			if (idloaicuahang[j].equals(tbhRs.getString("tanso" ))){ %>
													<option value='<%=idloaicuahang[j]%>' selected><%=tenloaicuahang[j] %></option>
													<%}else{ %>
													<option value='<%=idloaicuahang[j]%>'><%=tenloaicuahang[j] %></option>
													<%} 
								      		 }
								       	%>
											</select></TD>

											<% if (khBean.getTbhId()!=null&&khBean.getTbhId().contains(tbhRs.getString("tbhId"))){ %>
											<TD align="center">
											
												<input name="tbhId"  type="checkbox" value="<%= tbhRs.getString("tbhId") %>" checked>
												<input name="tbhIdTmp" id = "tbhIdTmp" type="hidden" value="<%= tbhRs.getString("tbhId") %>" >
											</TD>
											<%}else{%>
											<TD align="center">
												<input name="tbhId" id = "tbhId" type="checkbox" value="<%= tbhRs.getString("tbhId") %>">
												<input name="tbhIdTmp" id = "tbhIdTmp" type="hidden" value="<%= tbhRs.getString("tbhId") %>" >
											</TD>
											<%}%>

										</TR>
										<%i++;}}catch(java.sql.SQLException e){e.printStackTrace();}
								}
							  %>
										<tr class="tbfooter">
											<td colspan="4">&nbsp;</td>
										</tr>
									</TABLE>

								</FIELDSET></TD>
						</TR>
					</TABLE>
					<TABLE width="100%" border="0" cellpadding="0" cellspacing="0">
						<TR>
							<TD colspan="6" width="100%" style = "display:none;">
								<FIELDSET>
									<LEGEND class="legendtitle"><%=Utility.GLanguage("Chi???t kh???u program",session,jedis) %></LEGEND>
									<table align="left" cellpadding="0px" cellspacing="1px" width="100%">
										<tr class="tbheader">
											<th align="center" width="5%"><%=Utility.GLanguage("STT",session,jedis) %></th>
											<th align="center" width="40%"><%=Utility.GLanguage("S???n ph???m",session,jedis) %> </th>
											<th align="center" width="20%"><%=Utility.GLanguage("Program",session,jedis) %></th>
											<th align="center" width="20%"><%=Utility.GLanguage("Chi???t kh???u",session,jedis) %></th>
											<th align="center" width="5%"><%=Utility.GLanguage("X??a",session,jedis) %></th>
						<% int count  = khBean.getCount(); 
							String[] spIds = khBean.getSpIds();
							String[] chietkhauSp = khBean.getChietkhauSp();
							String[] cttkIds = khBean.getCtckIds();
						%>
										</tr>
										<%
												
										System.out.println(" count ====== ?" +count);

												for (int k = 0; k < count; k++) {

													if (spIds != null ) {
										
											if (k % 2 != 0) {%>
										<TR class=<%=lightrow%>>
											<%} else {%>
										
										<TR class=<%= darkrow%>>
											<%}%>
						
											<TD align="center"><input style="width: 100%" readonly="readonly" value='<%=(k+1) %>' /></TD>
											
											<TD align="center" style="width: 40%" > 
												<select style="width: 100%" name="spIds" id="spIds_<%=k%>">
													<option value=""></option>													
													<%

													ResultSet rs = khBean.getSanphamRs();								
													rs.beforeFirst();												
													while (rs.next()) 
													{
														if (spIds[k]!= null &&  rs.getString("pk_seq").equals(spIds[k])) {
													%>
																<option selected="selected"
																	value="<%=rs.getString("pk_seq")%>"><%=rs.getString("ma")%>--<%=rs.getString("ten")%>
																</option>
													<%
															} else {
													%>
																<option value="<%=rs.getString("pk_seq")%>"><%=rs.getString("ma")%>--<%=rs.getString("ten")%>
																</option>
													<%
														}}
													
													%>
												</select>																				
											</TD>
											
											<TD align="center" style="width: 40%" > 
												<select style="width: 100%" name="ctckIds" id="ctckIds_<%=k%>">
													<option value=""></option>													
													<%

													ResultSet ctckRs = khBean.getCtckRs();								
													ctckRs.beforeFirst();												
													while (ctckRs.next()) 
													{
														if (cttkIds[k]!= null &&  ctckRs.getString("pk_seq").equals(cttkIds[k])) {
													%>
																<option selected="selected"
																	value="<%=ctckRs.getString("pk_seq")%>"><%=ctckRs.getString("ten")%>
																</option>
													<%
															} else {
													%>
																<option value="<%=ctckRs.getString("pk_seq")%>"><%=ctckRs.getString("ten")%>
																</option>
													<%
														}}
													
													%>
												</select>																				
											</TD>
											<TD align="center"><input
												style="text-align: right; width: 90%;" type="text"
												name="chietkhauSp" id="chietkhauSp_<%=k%>"
												value='<%=chietkhauSp[k] == null || chietkhauSp[k].trim().length() <=0 ? "0" : formatter.format(Double.parseDouble(chietkhauSp[k].replaceAll(",", "")))%>'onkeypress="return keypress(event);"/></TD>

											<TD align="center">
												<a href="javascript:Xoa('<%=k %>');" >
												<IMG  src="../images/Delete20.png" alt="Hi???n th???" title="Hi???n th???" border=0>
												</a>
											</TD>
										</TR>
										<%
													}

												} // K???T TH??C V??NG L???P FOR HI???N RA C??C D??NG ???? NH???P
										%>
									<tr class="tbfooter">
											<td colspan="6">&nbsp;</td>
										</tr>	


									</TABLE>

								</FIELDSET></TD>
						</TR>
						<TR>
								<TD style="padding:7px; display:none;">
												<a class="btn btn-default" href="javascript: addThem(this)" > Th??m s???n ph???m<%=Utility.GLanguage("",session,jedis) %>
	                           					</a>
											</TD>
										</TR>
						
					</TABLE>
					<TABLE width="100%" border="0" cellpadding="0" cellspacing="0">
						<TR>
							<TD colspan="6" width="100%">
								<FIELDSET>
									<LEGEND class="legendtitle"><%=Utility.GLanguage("Ph??n nh??m",session,jedis) %> </LEGEND>
									<TABLE width="100%" border="0" cellspacing="1" cellpadding="6">
										<TR class="tbheader">
											<TH width="20%"><%=Utility.GLanguage("Nh??m kh??ch h??ng",session,jedis) %></TH>
											<TH width="10%"><%=Utility.GLanguage("Ch???n",session,jedis) %></TH>
										</TR>

										<%
								 i = 0;
								 lightrow = "tblightrow";
								 darkrow = "tbdarkrow";
								if (nkh_khList != null)
								{
								try{while(nkh_khList.next())
								{ 
									if (i % 2 != 0) {%>
										<TR class=<%=lightrow%>>
											<%} else {%>
										
										<TR class=<%= darkrow%>>
											<%}%>
											<TD align="center"><div align="left"><%= nkh_khList.getString("nkhTen") %>
												</div>
											</TD>
											<% if (nkh_khIds.contains(nkh_khList.getString("nkhId"))){ %>
											<TD align="center"><input name="nkh_khList"
												type="checkbox" value="<%= nkh_khList.getString("nkhId") %>"
												checked>
											</TD>
											<%}else{%>
											<TD align="center"><input name="nkh_khList"
												type="checkbox" value="<%= nkh_khList.getString("nkhId") %>">
											</TD>
											<%}%>
										</TR>
										<%i++;}}catch(java.sql.SQLException e){}
								}
							  %>
										<tr class="tbfooter">
											<td colspan="4">&nbsp;</td>
										</tr>
									</TABLE>

								</FIELDSET></TD>
						</TR>
					</TABLE></TD>



			<%-- <legend class="legendtitle" style="color:black">Nh??m nh?? ph??n ph???i</legend>
							<TABLE border="0" width="30%" cellpadding="6" cellspacing="1">
								<TR class="tbheader">
									<TH width="20%">T??n nh??m</TH>
									<TH width="10%">Ch???n t???t c???
									<input type="checkbox" name="chon" onClick="checkedAll(document.nppForm.nhomNppId)">
									</TH>
								</TR>
						<%
							int m = 0;
						/* 	String lightrow = "tblightrow";
							String darkrow = "tbdarkrow"; */

							while (nhomNppRs.next()){							
								if (m % 2 != 0) {%>						
									<TR class= <%=lightrow%> >
								<%} else {%>
									<TR class= <%= darkrow%> >
								<%}%>
										<TD align="left" class="textfont"><%=nhomNppRs.getString("ten") %></TD>
									    <% if (nhomNppRs.getInt("CHON") == 1){ %>
				    					<TD align="center" ><input name="nhomNppId" type="checkbox" value="<%=nhomNppRs.getString("pk_seq")%>" checked></TD>
								<%}else{ %>
										<TD align="center"><input name="nhomNppId" type="checkbox" value="<%=nhomNppRs.getString("pk_seq")%>"></TD>
								<%}%>

							</TR>
								<%
									m++;
								} %>
												
							</TABLE>
						</fieldset>				
					</TD>
			      </TR>
		  	</TABLE>	 --%>


			</TR>
		</TABLE>
	</form>
	<%geso.dms.center.util.Utility.JedisClose(jedis); %>
</BODY>
</HTML>

<% 	

if (khBean != null){
	khBean.DBclose();
	khBean = null;
}

	try{
	if (hch != null)
		hch.close();
	if (kbh != null)
		kbh.close();
	if (lch != null)
		lch.close();
	if (mck != null)
		mck.close();
	if (nkh_khList!= null)
		nkh_khList.close();
	if (tp != null)
		tp.close();
	if (qh != null)
		qh.close();
	if (vtch != null)
		vtch.close();
	if (nch!=null){
		nch.close();
	}
	if (nvgn!=null){
		nvgn.close();
	}
	if (nkh_khList!=null){
		nkh_khList.close();
	}
	if (nkh_khIds!=null){
		nkh_khIds.clear();
	}
	
	if (thanhthinongthonRs!=null){
		thanhthinongthonRs.close();
	}
	
	session.setAttribute("khBean",null);
	}
	catch (SQLException e) {}
	
%>
<%}%>
