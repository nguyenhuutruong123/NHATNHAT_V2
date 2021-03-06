<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page  import = "geso.dms.center.beans.nhaphanphoi.INhaphanphoi" %>
<%@ page  import = "java.sql.ResultSet" %>
<%@ page  import = "geso.dms.center.util.*" %>
<%@ page  import = "java.text.DecimalFormat" %>
<%@ page  import = "java.text.NumberFormat" %>
<%	String userId = (String) session.getAttribute("userId");  
	String userTen = (String) session.getAttribute("userTen");  	
	String sum = (String) session.getAttribute("sum");
	Utility util = (Utility) session.getAttribute("util");
	if(!util.check(userId, userTen, sum)){
		response.sendRedirect(request.getContextPath() + "/redirect.jsp");
	}else{ %>
<% INhaphanphoi nppBean = (INhaphanphoi)session.getAttribute("nppBean"); %>
<% ResultSet tp = (ResultSet)nppBean.getTp() ; %>
<% ResultSet qh = (ResultSet)nppBean.getQh() ; %>
<% ResultSet kv = (ResultSet)nppBean.getKhuvuc(); %>
<% ResultSet rs_khott = (ResultSet)nppBean.getrs_khott(); %>
<% ResultSet tructhuocRs = (ResultSet)nppBean.getTructhuoc(); %>
<% ResultSet rs_gsbh = (ResultSet)nppBean.getrs_gsbh(); %>
<% ResultSet rs_nvbh = (ResultSet)nppBean.getrs_nvbh(); %>
<% ResultSet rs_loaicn = (ResultSet)nppBean.getLoaiCNRs();
	ResultSet loainppRs = (ResultSet)nppBean.getLoaiNppRs();
	ResultSet diabanRs = (ResultSet)nppBean.getDiabanRs();	
	NumberFormat formatter=new DecimalFormat("#,###,###"); 
	String url = util.getChuyenNguUrl("NhaphanphoiSvl", "&isKH=" + nppBean.getIsKhachhang(),session);
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"> <% Utility Utilback = new Utility(); %>
<HTML>
<HEAD>
<TITLE><%= getServletContext().getInitParameter("TITLENAME") %></TITLE>
<META http-equiv="Content-Type" content="text/html; charset=utf-8">
<link href="../css/jquery-ui.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" language="JavaScript" src="../scripts/jquery.tools.min.js"></script>

<META http-equiv="Content-Style-Type" content="text/css">
<LINK rel="stylesheet" href="../css/main.css" type="text/css">

<SCRIPT language="javascript" type="text/javascript">

 function submitform()
 {   
    document.forms["nppForm"].submit();
 }

 function saveform()
 {
	/*  var ma = document.getElementById('maSAP'); */
	 var ten = document.getElementById('nppTen');
	 var diachi = document.getElementById('DiaChi');
	 var tp = document.getElementById('TP');
	 var qh = document.getElementById('QH');
	 var khuvuc = document.getElementById('KhuVuc');
	// var gsbhId = document.getElementById('gsbhId');	 
	 var isKH = document.getElementById('isKH').value;
	 var xuattaikho = document.getElementById('xuattaikho');
	 
	 /* if(ma.value == "")
	 {
		 alert('Vui l??ng nh???p m?? Chi nh??nh / NPP');
		 return;
	 } */
	 
	 if(ten.value == "")
	 {
		 alert('Vui l??ng nh???p t??n Chi nh??nh / NPP');
		 return;
	 }
	 if(diachi.value == "")
	 {
		 alert('Vui l??ng nh???p ?????a ch???');
		 return;
	 }	

	 if(tp.value == "")
	 {
		 alert('Vui l??ng ch???n th??nh ph???');
		 return;
	 }	
	 
	 if(qh.value == "")
	 {
		 alert('Vui l??ng ch???n qu???n huy???n');
		 return;
	 }	

/* 	 if(gsbhId.value == "")
	 {
		 alert('Vui l??ng ch???n ph??? tr??ch t???nh / G??CN');
		 return;
	 }	 */
	 
	 if(khuvuc.value == "")
	 {
		 alert('Vui l??ng ch???n khu v???c');
		 return;
	 }	 
	 
	 if(isKH == '0')
	 {
		 var tructhuocId = document.getElementById('tructhuocId');
		 if(tructhuocId.value == "")
		 {
			 alert('Vui l??ng ch???n ????n v??? tr???c thu???c');
			 return;
		 }	
	 }
	  
	 if(isKH == '1')
	 {
		 var nvbhId = document.getElementById('nvbhId');
		 if(nvbhId.value == "")
		 {
			 alert('Vui l??ng ch???n NH??N VI??N B??N H??NG');
			 return;
		 }	
	 }
	 
	 if(xuattaikho.value == "")
	 {
		 alert('Vui l??ng nh???p xu???t t???i kho');
		 return;
	 }	 
	 
	 
 	 document.forms['nppForm'].action.value='save';
     document.forms['nppForm'].submit();
 }
 
 	function DinhDang()
	{
		var sotien = document.getElementById("hanmucno").value.replace(/,/g,"");
		document.getElementById("hanmucno").value= DinhDangTien(sotien);
		
		var songayno = document.getElementById("songayno").value.replace(/,/g,"");
		document.getElementById("songayno").value= DinhDangTien(songayno);
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
			element.value=DinhDangTien(element.value);
			if(element.value== '' ||element.value=='0' )
			{
				element.value= '';
			}
		}
	
</SCRIPT>

<link href="../css/select2.css" rel="stylesheet" />
<script src="../scripts/select2.js"></script>
<script>
	$(document).ready(function()
	{
		$(".select2").select2();
		setInputFilter(document.getElementById("ptchietkhau"), function(value) { return /^-?\d*[.,]?\d*$/.test(value); });
		setInputFilter(document.getElementById("tonkhoantoan"), function(value) { return /^-?\d*[.,]?\d*$/.test(value); });
		setInputFilter(document.getElementById("tansuatDathang"), function(value) { return /^-?\d*[.,]?\d*$/.test(value); });
		setInputFilter(document.getElementById("tonkhotoida"), function(value) { return /^-?\d*[.,]?\d*$/.test(value); });
		setInputFilter(document.getElementById("hanmucno"), function(value) { return /^-?\d*[.,]?\d*$/.test(value); });
		setInputFilter(document.getElementById("songayno"), function(value) { return /^-?\d*[.,]?\d*$/.test(value); });
	});
	
	function setInputFilter(textbox, inputFilter) {
		  ["input", "keydown", "keyup", "mousedown", "mouseup", "select", "contextmenu", "drop"].forEach(function(event) {
		    textbox.addEventListener(event, function() {
		      if (inputFilter(this.value)) {
		        this.oldValue = this.value;
		        this.oldSelectionStart = this.selectionStart;
		        this.oldSelectionEnd = this.selectionEnd;
		      } else if (this.hasOwnProperty("oldValue")) {
		        this.value = this.oldValue;
		        this.setSelectionRange(this.oldSelectionStart, this.oldSelectionEnd);
		      }
		    });
		  });
		}


		// Install input filters.
		/* setInputFilter(document.getElementById("ptchietkhau"), function(value) {
		  return /^\d*$/.test(value); }); */
		/* setInputFilter(document.getElementById("intLimitTextBox"), function(value) {
		  return /^\d*$/.test(value) && (value === "" || parseInt(value) <= 500); });
		setInputFilter(document.getElementById("intTextBox"), function(value) {
		  return /^-?\d*$/.test(value); });
		setInputFilter(document.getElementById("floatTextBox"), function(value) {
		  return /^-?\d*[.,]?\d*$/.test(value); });
		setInputFilter(document.getElementById("currencyTextBox"), function(value) {
		  return /^-?\d*[.,]?\d{0,2}$/.test(value); });
		setInputFilter(document.getElementById("basicLatinTextBox"), function(value) {
		  return /^[a-z]*$/i.test(value); });
		setInputFilter(document.getElementById("extendedLatinTextBox"), function(value) {
		  return /^[a-z\u00c0-\u024f]*$/i.test(value); });
		setInputFilter(document.getElementById("hexTextBox"), function(value) {
		  return /^[0-9a-f]*$/i.test(value); }); */
</script>

<style type="text/css">
	input
	{
		padding: 2px 0px;
	}
</style>
</HEAD>
<BODY leftmargin="0" bottommargin="0" topmargin="0" rightmargin="0">
<form name="nppForm" method="post" action="../../NhaphanphoiUpdateSvl">
<% redis.clients.jedis.Jedis jedis =  geso.dms.center.util.Utility.getJedis(); %><% geso.dms.center.util.Csrf csdr=new geso.dms.center.util.Csrf(request,response,true,false,true);%>
 <input type="hidden" name="<%=csdr.get_tokenName() %>" value='<%=csdr.get_tokenValue() %>'>

<input type="hidden" name="userId" value='<%= userId %>'>
<input type="hidden" name="action" value='1'>
<input type="hidden" name="isKH" id="isKH" value='<%= nppBean.getIsKhachhang() %>'>
<input type="hidden" name="id"  value='<%= nppBean.getId() %>'>
<TABLE width="100%" border="0" cellspacing="0" cellpadding="0"	height="100%">
	<TR>
		<TD colspan="4" align='left' valign='top' bgcolor="#FFFFFF">
			<TABLE width="100%" border="0" cellpadding="0" cellspacing="1">
				<TR>
					<TD align="left" class="tbnavigation">
						<table width="100%" border="0" cellpadding="0" cellspacing="0">
						  <tr height="22">
							 <TD align="left" colspan="2" class="tbnavigation"><%= url %> > <%=Utility.GLanguage("Hi???n th???",session,jedis) %></TD> 
							 <TD colspan="2" align="right" class="tbnavigation"><%=Utility.GLanguage("Ch??o m???ng b???n",session,jedis) %>  <%=userTen %>&nbsp;  </TD> 
						  </tr>
						</table>
			 		</TD>
				</TR>
			</TABLE>
			<TABLE width="100%" border="0" cellpadding="0" cellspacing="1">
					<TR class = "tbdarkrow">
						<TD width="30" align="center"><A href="../../RouterSvl?task=<%= Utilback.dongMa(getServletContext().getInitParameter("RedirectNoScript") +"NhaphanphoiSvl?userId="+userId +"&isKH="+ nppBean.getIsKhachhang()+"") %>" ><img src="../images/Back30.png" alt="Quay ve"  title="Quay ve" width="30" height="30" border="1" longdesc="Quay ve" style="border-style:outset"></A></TD>
					    <TD width="2" align="left" ></TD>
					    <TD width="30" align="left" ></TD>
				    	<TD align="left" >&nbsp;</TD>
					</TR>
			</TABLE>
			<TABLE width="100%" border="0" cellpadding="0"  cellspacing="0">
				  	<tr>
							<TD align="left" colspan="6" class="legendtitle">
								<FIELDSET>
								<LEGEND class="legendtitle"><%=Utility.GLanguage("B??o l???i nh???p li???u",session,jedis) %></LEGEND>
			    				<textarea name="dataerror"  style="width: 100% ; color:#F00 ; font-weight:bold"  style="width:100%" rows="1" ><%= nppBean.getMessage() %></textarea>
                                        <%nppBean.setMessage(""); %>
								</FIELDSET>
						   </TD>
					</tr>

				  <TR>
				  <TD height="100%" width="100%">
						<FIELDSET>
						<LEGEND class="legendtitle" style="color:black"><%=Utility.GLanguage("Th??ng tin ",session,jedis) %><%= nppBean.getIsKhachhang().equals("1") ? "Kh??ch h??ng" : "Chi nh??nh / NPP" %> </LEGEND>
						<TABLE border="0" width="100%" cellpadding="6" cellspacing="0">
							<TR>
								<TD class="plainlabel" width="130px"  ><%=Utility.GLanguage("M?? DMS",session,jedis) %> <FONT class="erroralert"> *</FONT></TD>
								<TD class="plainlabel" width="220px" >
									<INPUT name="maSAP" id="maSAP" type="text" value="<%= nppBean.getMaSAP() %>" >
								</TD>
								<TD class="plainlabel" width="140px" > <%=Utility.GLanguage("T??n",session,jedis) %> <FONT class="erroralert"> *</FONT></TD>
                                <TD class="plainlabel"  >
                                	<INPUT name="nppTen" id="nppTen" type="text" value="<%= nppBean.getTen() %>" >
                                </TD>
                                
                                <TD class="plainlabel" width="140px" ><%=Utility.GLanguage("Xu???t t???i kho",session,jedis) %><FONT class="erroralert"> *</FONT></TD>
                                <TD class="plainlabel" >
                                	<INPUT name="xuattaikho" id="xuattaikho" type="text" value="<%= nppBean.getXuattaikho() %>" >
                                </TD>
                                
							</TR>
							<TR>
							  	<TD class="plainlabel"><%=Utility.GLanguage("?????a ch??? giao h??ng",session,jedis) %> <FONT class="erroralert"> *</FONT></TD>
                                <TD class="plainlabel" >
                                	<INPUT name="DiaChi" id="DiaChi" type="text" value="<%= nppBean.getDiachi() %>"  >
                                </TD>
                                
							   	 <TD class="plainlabel"><%=Utility.GLanguage("T???nh/Th??nh ph???",session,jedis) %> <FONT class="erroralert"> *</FONT></TD>
								 <TD class="plainlabel" width="220px" >
								 	<SELECT name="tpId" id="TP" onChange="submitform();" class="select2" style="width: 200px;" >
								    	<option value=""></option>
								      	<% if(tp!=null)
								      	try{while(tp.next()){ 
								    	if(tp.getString("tpId").equals(nppBean.getTpId())){ %>
								      		<option value='<%=tp.getString("tpId")%>' selected><%=tp.getString("tpTen") %></option>
								      	<%}else{ %>
								     		<option value='<%=tp.getString("tpId")%>'><%=tp.getString("tpTen") %></option>
								     	<%}}}catch(java.sql.SQLException e){} %>	  
	                       			</SELECT>	
	                       		</TD>
	
							   	 <TD class="plainlabel"><%=Utility.GLanguage("Qu???n/Huy???n",session,jedis) %> <FONT class="erroralert"> *</FONT></TD>
								 <TD class="plainlabel" >
								 	<SELECT name="qhId" id="QH" class="select2" style="width: 200px;">
								    	<option value=""></option>
									      <%if(qh != null){ 
									      		try{while(qh.next()){ 
									    			if(qh.getString("qhId").equals(nppBean.getQhId())){ %>
									      				<option value='<%=qh.getString("qhId")%>' selected><%=qh.getString("qhTen") %></option>
									      		 <%}else{ %>
									     				<option value='<%=qh.getString("qhId")%>'><%=qh.getString("qhTen") %></option>
									     		<%}}}catch(java.sql.SQLException e){} 
									     		
									      }	%>	  
	                       			</SELECT>	
	                       		</TD>	
						  </TR>

						  <TR>
						  		<TD class="plainlabel"><%=Utility.GLanguage("?????a ch??? xu???t H??",session,jedis) %> </TD>
                                <TD  class="plainlabel" ><INPUT name="diachixhd" id="diachixhd" type="text" value="<%= nppBean.getDiaChiXuatHoaDon() %>"  ></TD>
                                
                                <td class="plainlabel"><%=Utility.GLanguage("Ng?????i ?????i di???n tr??n H??",session,jedis) %></td>
                                <td class="plainlabel" >
                                	<INPUT name="ddthd" id="ddthd" type="text" value="<%= nppBean.getTenNguoiDaiDien() %>">
                                </td>
                                <TD class="plainlabel"><%=Utility.GLanguage("M?? s??? thu???",session,jedis) %> </TD>
                                <TD class="plainlabel"><INPUT name="masothue" id="masothue" type="text" value="<%= nppBean.getmaSoThue() %>" ></TD>
                                
						  </TR>	
						   <TR>
                                
						  		<TD class="plainlabel"><%=Utility.GLanguage("??i???n tho???i",session,jedis) %> </TD>
                                <TD  class="plainlabel">
                                	<INPUT name="DienThoai" id="DienThoai" type="text" value="<%= nppBean.getSodienthoai() %>" >
                                </TD>
                                <td class="plainlabel"><%=Utility.GLanguage("FAX",session,jedis) %></td>
						  		<TD class="plainlabel"><input name="fax" id="fax" type="text" value="<%= nppBean.getFAX() %>"></TD>
							  	
						  		<TD class="plainlabel"><%=Utility.GLanguage("Email",session,jedis) %> </TD>
                        		<TD class="plainlabel"><input name="email" id="email" type="text" value="<%= nppBean.getEmail() %>"></TD>
						  </TR>	
						  <TR>
							   	<td class="plainlabel"><%=Utility.GLanguage("H??nh th???c TT",session,jedis) %></td>
                        		<td class="plainlabel"><input type="text" name="httt" id="httt" value="<%= nppBean.getHinhThucThanhToan() %>"></td>
                        		
                        		<TD class="plainlabel"><%=Utility.GLanguage("T??n ng??n h??ng",session,jedis) %></TD>
								<TD class="plainlabel"><INPUT name="nganhang" id="nganhang" value="<%= nppBean.getNganHang() %>" type="text"></TD>	
								<TD class="plainlabel"><%=Utility.GLanguage("S??? t??i kho???n",session,jedis) %></TD>
								<TD class="plainlabel"><INPUT type="text" name="sotaikhoan" id="sotaikhoan" value="<%= nppBean.getSoTK() %>"></TD>
							</TR>
							
							<%//Khai bao Ky hieu hoa don %>
							<%-- <TR>
								<td class="plainlabel">K?? hi???u h??a ????n</td>
                        		<td class="plainlabel">
                        			<input type="text" name="kyhieuhd" id="kyhieuhd" value="<%= nppBean.getKyhieuHD() %>">
                        		</td>
                        		<td class="plainlabel">S??? h??a ????n : T??? :</td>
                        		<td class="plainlabel">
                        			<input type="text" name="soHDTu" id="soHDTu" value="<%= nppBean.getSoHDTu() %>" >
                        		</td>
                        		<td class="plainlabel">?????n :</td>
                        		<td class="plainlabel">
                        			<input type="text" name="soHDDen" id="soHDDen" value="<%= nppBean.getSoHDDen() %>" >
                        		</td>
							</TR> --%>
							
							<TR>
								<%-- <td class="plainlabel">M?? kho</td>
                        		<td class="plainlabel">
                        			<input type="text" name="makho" id="makho" value="<%= nppBean.getMaKho() %>">
                        		</td>
                        		<td class="plainlabel">M?? nh???p xu???t</td>
                        		<td class="plainlabel">
                        			<input type="text" name="manx" id="manx" value="<%= nppBean.getMaNX() %>" >
                        		</td> --%>
                        		<td class="plainlabel"><%=Utility.GLanguage("CMND",session,jedis) %></td>
                        		<td class="plainlabel" >
                        			<input type="text" name="cmnd" id="cmnd" value="<%= nppBean.getCMTND() %>" >
                        		</td>
                        			
                        							
                        	<TD class="plainlabel">% <%=Utility.GLanguage("Chi???t kh???u",session,jedis) %>	</TD>
								<TD class="plainlabel"  colspan="3">
									<INPUT type="text" name="ptchietkhau" maxlength="4" id="ptchietkhau" value="<%= nppBean.getPTChietkhau()%>" size="40">
								</TD>	
							</TR>
							
							
							
						  <TR>
						  		<%-- <TD class="plainlabel" >Ph??? tr??ch t???nh / G??CN</TD>
								<TD class="plainlabel" >
									<SELECT name="gsbhId" id="gsbhId" class="select2" style="width: 200px;" >
								    <option value=""></option>
								      <% if(rs_gsbh != null) 
								      try{while(rs_gsbh.next()){ 
								    	if(rs_gsbh.getString("pk_seq").trim().equals(nppBean.getGsbhId().trim())){ %>
								      		<option value='<%=rs_gsbh.getString("pk_seq")%>' selected><%=rs_gsbh.getString("ten") %></option>
								      	<%}else{ %>
								     		<option value='<%=rs_gsbh.getString("pk_seq")%>'><%=rs_gsbh.getString("ten") %></option>
								     	<%}}}catch(java.sql.SQLException e){} %>	  
                        			</SELECT>	
								</TD> --%>
						  		<TD class="plainlabel"><%=Utility.GLanguage("Khu v???c",session,jedis) %> <FONT class="erroralert"> *</FONT></TD>
								 <TD class="plainlabel" colspan = "1">
								 	<SELECT name="kvId" id="KhuVuc" onChange = "submitform();" class="select2" style="width: 200px;">
								    <option value=""></option>
								      <%
								      if(kv!=null)
								      try{while(kv.next()){ 
								    	if(kv.getString("kvId").equals(nppBean.getKvId())){ %>
								      		<option value='<%=kv.getString("kvId")%>' selected><%=kv.getString("kvTen") %></option>
								      	<%}else{ %>
								     		<option value='<%=kv.getString("kvId")%>'><%=kv.getString("kvTen") %></option>
								     	<%}}}catch(java.sql.SQLException e){} %>	  
                        				</SELECT>	
                        		</TD>
							   	 <% if( nppBean.getLoaiNPP().equals("4") || nppBean.getLoaiNPP().equals("5") ) { %>
                        		<TD class="plainlabel"><%=Utility.GLanguage("Kho ?????t h??ng",session,jedis) %> </TD>
								 <TD class="plainlabel" colspan = "3">
								 	<SELECT name="khottid" id="khottid" class="select2" style="width: 200px;" >
								      <% if(rs_khott!=null) 
								      try{while(rs_khott.next()){ 
								    	if(rs_khott.getString("pk_seq").trim().equals(nppBean.getKhoTT().trim())){ %>
								      		<option value='<%=rs_khott.getString("pk_seq")%>' selected><%=rs_khott.getString("ten") %></option>
								      	<%}else{ %>
								     		<option value='<%=rs_khott.getString("pk_seq")%>'><%=rs_khott.getString("ten") %></option>
								     	<%}}}catch(java.sql.SQLException e){} %>	  
                        			</SELECT>	
                        		</TD>
                        		<% } else { %>
                        			<td class="plainlabel" colspan="4" >	
                        				<input type="hidden" value="100001" >
                        			</td>
                        		<% } %>
                        		
                        		
                        		<td class="plainlabel" style="display:none";> </td>
                        		
                        		 <Td class="plainlabel"  style="display:none";>
                        			
                        			<% if(nppBean.getLoaiNPP().equals("5")) { %>
	                                
		                                <%=Utility.GLanguage("Qu???n l?? t???n kho",session,jedis) %> 
		                               	<%  if (nppBean.getPriSec().equals("1")){%>
		                                      <input name="prisec" type="checkbox" value ="1" checked>
		                                <%} else {%>
		                                       <input name="prisec" type="checkbox" value ="0">
		                                <%} %>    
		                                
	                                <% } else { %>
	                                	
	                                	<%=Utility.GLanguage("T??? ch???t xu???t kho OTC",session,jedis) %> 
		                               	<%  if (nppBean.getPriSec().equals("1")){%>
		                                      <input name="prisec" type="checkbox" value ="1" checked>
		                                <%} else {%>
		                                       <input name="prisec" type="checkbox" value ="1">
		                                <%} %> 
		                                
		                                &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
		                                
		                                <%=Utility.GLanguage("T??? xu???t kho ETC",session,jedis) %> 
		                               	<%  if (nppBean.getTuxuatkhoETC().equals("1")){%>
		                                      <input name="tuxuatETC" type="checkbox" value ="1" checked>
		                                <%} else {%>
		                                       <input name="tuxuatETC" type="checkbox" value ="1">
		                                <%} %> 
		                                
		                                  <br />
		                                
		                                <%=Utility.GLanguage("T??? t???o h??a ????n",session,jedis) %>  
		                               	<%  if (nppBean.getTutaohoadonOTC().equals("1")){%>
		                                      <input name="tutaohoadon" type="checkbox" value ="1" checked>
		                                <%} else {%>
		                                       <input name="tutaohoadon" type="checkbox" value ="1">
		                                <%} %> 
		                                
		                                &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
		                                
		                                <%=Utility.GLanguage("Kh??ng ph??n bi???t k??nh OTC v?? ETC",session,jedis) %>  
		                               	<%  if (nppBean.getDungchungkenh().equals("1")){%>
		                                      <input name="dungchungkenh" type="checkbox" value ="1" checked>
		                                <%} else {%>
		                                       <input name="dungchungkenh" type="checkbox" value ="1">
		                                <%} %> 
		                                
	                                <% } %>
                        			
                        		</Td> 
                        		
							</TR>
							
							<% if(nppBean.getIsKhachhang().equals("0")) { %>
							<TR>
								<TD class="plainlabel" style="color: red;" ><%=Utility.GLanguage("Lo???i",session,jedis) %></TD>
								<TD class="plainlabel" >
									<select name="loaiNPP" class="select2" style="width: 200px;color: red;" onChange="submitform();" >
										<option value="" ></option>
										<% if(loainppRs != null) { 
											while(loainppRs.next())
											{
												if(loainppRs.getString("maloai").equals(nppBean.getLoaiNPP())) { %>
													<option value="<%= loainppRs.getString("maloai") %>" selected="selected" ><%= loainppRs.getString("tenloai") %></option>
												<% }
												else { %>
													<option value="<%= loainppRs.getString("maloai") %>" ><%= loainppRs.getString("tenloai") %></option>
												<% }
											}
											loainppRs.close();
										} %>
									</select>
								</TD>
								
								<TD class="plainlabel" ><%=Utility.GLanguage("Tr???c thu???c",session,jedis) %><FONT class="erroralert"> *</FONT></TD>
								<TD class="plainlabel"  >
									<select name="tructhuocId" id="tructhuocId" class="select2" style="width: 200px;" >
										<option value="" ></option>
										<% if(tructhuocRs != null) { 
											while(tructhuocRs.next())
											{
												if(tructhuocRs.getString("Id").equals(nppBean.getTructhuocId())) { %>
													<option value="<%= tructhuocRs.getString("Id") %>" selected="selected" ><%= tructhuocRs.getString("TEN") %></option>
												<% }
												else { %>
													<option value="<%= tructhuocRs.getString("Id") %>" ><%= tructhuocRs.getString("TEN") %></option>
												<% }
											}
											tructhuocRs.close();
										} %>
									</select>
								</TD>
								
								<TD class="plainlabel" >
                                	 <%=Utility.GLanguage("Ho???t ?????ng",session,jedis) %>
                                	<%  if (nppBean.getTrangthai().equals("1")){%>
	                                      <input name="TrangThai" type="checkbox" value ="1" checked>
	                                <%} else {%>
	                                       <input name="TrangThai" type="checkbox" value ="0">
	                                <%} %>  
	                                      
                                </TD>
                                
                                 <TD class="plainlabel"  >
                                	 <%=Utility.GLanguage("Qu???n l?? SELL OUT",session,jedis) %>
                                	<%  if (nppBean.getQuanlySellOut().equals("1")){%>
	                                      <input name="quanlySellOut" type="checkbox" value ="1" checked>
	                                <%} else {%>
	                                       <input name="quanlySellOut" type="checkbox" value ="0">
	                                <%} %>  	
	                               
	                                                            
                                </TD>	
															
							</TR>
							
							<%-- <TR>
							<TD class="plainlabel" colspan="1" >?????a b??n</TD>
									<TD class="plainlabel" colspan="5">
									<SELECT name="diabanId" onChange = "submitform();" class="select2" style = "width:200px">
										    	<option value=""> </option>
											    <% try{ while(diabanRs.next()){ 
										    			if(diabanRs.getString("pk_seq").equals(nppBean.getDiabanId())){ %>
										      				<option value='<%=diabanRs.getString("pk_seq")%>' selected><%=diabanRs.getString("ten") %></option>
										      			<%}else{ %>
										     				<option value='<%=diabanRs.getString("pk_seq")%>'><%=diabanRs.getString("ten") %></option>
										     			<%}}}catch(java.sql.SQLException e){} %>
											 </SELECT>
											 </TD>
							</TR> --%>
							
							<TR>
								<TD class="plainlabel"><%=Utility.GLanguage("S??? ng??y t???n kho an to??n",session,jedis) %></TD>
								<TD class="plainlabel" >
									<INPUT type="text" name="tonkhoantoan" id="tonkhoantoan" value="<%= nppBean.getTonkhoAntoan() %>" size="40">
								</TD>
								<TD class="plainlabel"><%=Utility.GLanguage("T???n su???t ?????t h??ng (L???n/Tu???n)",session,jedis) %></TD>
								<TD class="plainlabel" >
									<INPUT type="text" name="tansuatDathang" id="tansuatDathang" value="<%= nppBean.getTansuatDathang() %>" size="40">
								</TD>
							
								<TD class="plainlabel"><%=Utility.GLanguage("T???n kho t???i ??a",session,jedis) %></TD>
								<TD class="plainlabel" >
									<INPUT type="text" name="tonkhotoida" id="tonkhotoida" value="<%= nppBean.getTonkhotoida() %>" size="40">
								</TD>
							</TR>
							<% } else { %>
								
								<TR>
								
								<TD class="plainlabel" ><%=Utility.GLanguage("NH??N VI??N B??N H??NG",session,jedis) %></TD>
								<TD class="plainlabel"  >
									<select name="nvbhId" id="nvbhId" class="select2" style="width: 200px;" >
										<option value="" ></option>
										<% if(rs_nvbh != null) { 
											while(rs_nvbh.next())
											{
												if(rs_nvbh.getString("Id").equals(nppBean.getNvbhId())) { %>
													<option value="<%= rs_nvbh.getString("Id") %>" selected="selected" ><%= rs_nvbh.getString("TEN") %></option>
												<% }
												else { %>
													<option value="<%= rs_nvbh.getString("Id") %>" ><%= rs_nvbh.getString("TEN") %></option>
												<% }
											}
											rs_nvbh.close();
										} %>
									</select>
								</TD>
								
								<TD class="plainlabel" >
                                	<%=Utility.GLanguage("Ho???t ?????ng",session,jedis) %> 
                                	<%  if (nppBean.getTrangthai().equals("1")){%>
	                                      <input name="TrangThai" type="checkbox" value ="1" checked>
	                                <%} else {%>
	                                       <input name="TrangThai" type="checkbox" value ="0">
	                                <%} %>  
	                                                            
                                </TD>
								 <TD class="plainlabel"  >
                                	 <%=Utility.GLanguage("Qu???n l?? SELL OUT",session,jedis) %>
                                	<%  if (nppBean.getQuanlySellOut().equals("1")){%>
	                                      <input name="quanlySellOut" type="checkbox" value ="1" checked>
	                                <%} else {%>
	                                       <input name="quanlySellOut" type="checkbox" value ="0">
	                                <%} %>  	
	                               
	                                                            
                                </TD>	
							</TR>
								
							<% } %>
							
							<% if( nppBean.getLoaiNPP().equals("0") || nppBean.getLoaiNPP().equals("4")) { %>
							<tr class="plainlabel">
							 <TD class="plainlabel"> <%=Utility.GLanguage("Lo???i c??ng n???",session,jedis) %></TD>
                               <TD  class="plainlabel" >
                               	<SELECT name ="loaicongno" onChange = "submitform();">
                                         <option value="1" class="select2"  selected>C??ng n??? theo h???n m???c</option>
                                   </SELECT>     
                                  <%--  <% if (nppBean.getLoaiCN().equals("1")){%>
                                 		  <option value=""></option>
                                         <option value="1" selected>C??ng n??? t???i ??a</option>
                                         <option value="0">C??ng n??? theo m???c tr???n</option>
                                         <option value="2">C??ng n??? theo h???n m???c</option>
                                         
                                   <%}else if(nppBean.getLoaiCN().equals("0")) {%>
                                         <option value="" > </option>
                                         <option value="1" >C??ng n??? t???i ??a</option>
                                         <option value="0" selected>C??ng n??? theo m???c tr???n</option>
                                         <option value="2">C??ng n??? theo h???n m???c</option>
                                         
                                   <%}else if(nppBean.getLoaiCN().equals("2")) {%>                                                                                        
                                        <option value=""></option>
                                         <option value="1" >C??ng n??? t???i ??a</option>
                                         <option value="0">C??ng n??? theo m???c tr???n</option>
                                         <option value="2" selected>C??ng n??? theo h???n m???c</option>
                                   <%}else {%>
                                   	  <option value="" selected></option>
                                         <option value="1" >C??ng n??? t???i ??a</option>
                                         <option value="0">C??ng n??? theo m???c tr???n</option>
                                         <option value="2">C??ng n??? theo h???n m???c</option>
                                   <%} %> --%>                                        
                                 </TD>  
								<TD class="plainlabel"><%=Utility.GLanguage("H???n m???c n???",session,jedis) %> </TD>
								<TD class="plainlabel" >
									<INPUT type="text" name="hanmucno" id="hanmucno" value="<%=formatter.format(Double.parseDouble(nppBean.getHanmucno().replace(",","")))%>"  size="40"   onchange="DinhDang();">
								</TD>
								
								<TD class="plainlabel"><%=Utility.GLanguage("S??? ng??y n???",session,jedis) %></TD>
								<TD class="plainlabel" >
									<INPUT type="text" name="songayno" id="songayno" value="<%=formatter.format(Double.parseDouble(nppBean.getSongayno())) %>"  size="40"   onchange="DinhDang();">
								</TD>	
								
						</TR>
				<%} %>
					<TR>
						<TD class="plainlabel"><%=Utility.GLanguage("T??n k?? h???p ?????ng",session,jedis) %>	:</TD>
						<TD class="plainlabel" >
							<INPUT type="text" name="tenkyhd" id="tenkyhd" value="<%= nppBean.getTenKyHd() %>" size="40">
						</TD>	
						<TD class="plainlabel">	</TD>
						<TD class="plainlabel" colspan="3">
							<%-- <INPUT type="text" name="hanmucdoanhthu" id="hanmucdoanhthu" onkeyup="Dinhdang(this)"   value="<%= nppBean.getHanmucdoanhthu() %>" size="40"> --%>
						</TD>	
					</TR>
					
					<TR>
						<TD class="plainlabel"><%=Utility.GLanguage("T??n th??? kho",session,jedis) %>	:</TD>
						<TD class="plainlabel" >
							<INPUT type="text" name="tenthukho" id="tenthukho" value="<%= nppBean.getThuKho() %>" size="40">
						</TD>	
						<TD class="plainlabel"></TD>
						
						<TD class="plainlabel" colspan="3"></TD>	
					</TR>
					<TR>
						<TD class="plainlabel"> <%=Utility.GLanguage("Doanh s??? sell out",session,jedis) %> =</TD>
						<TD class="plainlabel" colspan="5">
							<INPUT type="text" name="tyleOutIn" id="tyleOutIn" value="<%= nppBean.getTyleOutIn() %>" size="40">
							% <%=Utility.GLanguage("Doanh s??? sell in",session,jedis) %> 
						</TD>	
						
					
						</TABLE>
						</FIELDSET>
												
					</TD>
			      </TR>
		  	</TABLE>	
	</TD>
	</TR>
</TABLE><%geso.dms.center.util.Utility.JedisClose(jedis); %>
</form>
</BODY>
</HTML>
<%
	try {
		nppBean.DBclose();
	}
	catch (Exception e) {
		//e.printStackTrace();
	}
}%>