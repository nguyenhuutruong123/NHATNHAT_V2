<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page  import = "geso.dms.center.beans.nhaphanphoi.INhaphanphoi" %>
<%@ page  import = "java.sql.ResultSet" %>
<%@ page  import = "geso.dms.center.util.*" %>
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
<% ResultSet tructhuocRs = (ResultSet)nppBean.getTructhuoc();%> 
<% ResultSet capCnRs = (ResultSet)nppBean.getCapCn();%> 
<% ResultSet rs_gsbh = (ResultSet)nppBean.getrs_gsbh(); %>
<% ResultSet rs_nvbh = (ResultSet)nppBean.getrs_nvbh(); %>
<% ResultSet rs_loaicn = (ResultSet)nppBean.getLoaiCNRs(); 
ResultSet loainppRs = (ResultSet)nppBean.getLoaiNppRs();
ResultSet diabanRs = (ResultSet)nppBean.getDiabanRs();
String url = util.getChuyenNguUrl("NhaphanphoiSvl",  "&isKH=" + nppBean.getIsKhachhang(),session);
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
	 var ten = document.getElementById('nppTen');
	 var diachi = document.getElementById('DiaChi');
	 var capcn = document.getElementById('idcapcn');
	 var tp = document.getElementById('TP');
	 var qh = document.getElementById('QH');
	 var khuvuc = document.getElementById('KhuVuc');
	// var gsbhId = document.getElementById('gsbhId');	 
	 var isKH = document.getElementById('isKH').value;
	 var xuattaikho = document.getElementById('xuattaikho');
	 
	/*  if(ma.value == "")
	 {
		 alert('Vui lòng nhập mã Chi nhánh / NPP');
		 return;
	 } */
	 alert
	 if(capcn.value == "")
	 {
		 alert('Vui lòng nhập Cấp chi nhánh');
		 return;
	 }
	 if(ten.value == "")
	 {
		 alert('Vui lòng nhập tên Chi nhánh / NPP');
		 return;
	 }
	 if(diachi.value == "")
	 {
		 alert('Vui lòng nhập địa chỉ');
		 return;
	 }	

	 if(tp.value == "")
	 {
		 alert('Vui lòng chọn thành phố');
		 return;
	 }	
	 
	 if(qh.value == "")
	 {
		 alert('Vui lòng chọn quận huyện');
		 return;
	 }	

	 if(khuvuc.value == "")
	 {
		 alert('Vui lòng chọn khu vực');
		 return;
	 }	 
	 
	 if(isKH == '0')
	 {
		 var tructhuocId = document.getElementById('tructhuocId');
		 if(tructhuocId.value == "")
		 {
			 alert('Vui lòng chọn đơn vị trực thuộc');
			 return;
		 }	
	 }
	  
	 if(isKH == '1')
	 {
		 var nvbhId = document.getElementById('nvbhId');
		 if(nvbhId.value == "")
		 {
			 alert('Vui lòng chọn NHÂN VIÊN BÁN HÀNG');
			 return;
		 }	
	 }
	 
	 if(xuattaikho.value == "")
	 {
		 alert('Vui lòng nhập xuất tại kho');
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
	
	 function Dinhdang(element)
		{
			element.value=DinhDangTien(element.value);
			if(element.value== '' ||element.value=='0' )
			{
				element.value= '';
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

	function keypress(e) //Hàm dùng d? ngan ngu?i dùng nh?p các ký t? khác ký t? s? vào TextBox
	{    
		
		var keypressed = null;
		if (window.event)
			keypressed = window.event.keyCode; //IE
		else
			keypressed = e.which; //NON-IE, Standard
		
		if (keypressed < 48 || keypressed > 57)
		{ 
			if (keypressed == 8 || keypressed == 127 || keypressed == 37 || keypressed == 39 || keypressed == 0 || keypressed == 46 || keypressed == 45)
		{//Phím Delete và Phím Back
				return;
			}
			return false;
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
<TABLE width="100%" border="0" cellspacing="0" cellpadding="0"	height="100%">
	<TR>
		<TD colspan="4" align='left' valign='top' bgcolor="#FFFFFF">
			<TABLE width="100%" border="0" cellpadding="0" cellspacing="1">
				<TR>
					<TD align="left" class="tbnavigation">
						<table width="100%" border="0" cellpadding="0" cellspacing="0">
						  <tr height="22">
							 <TD align="left" colspan="2" class="tbnavigation">&nbsp;<%= url %> > <%=Utility.GLanguage("Tạo mới",session,jedis) %></TD> 
							 <TD colspan="2" align="right" class="tbnavigation"><%=Utility.GLanguage("Chào mừng bạn",session,jedis) %>  <%=userTen %>&nbsp;  </TD> 
						  </tr>
						</table>
			 		</TD>
				</TR>
			</TABLE>
			<TABLE width="100%" border="0" cellpadding="0" cellspacing="1">
					<TR class = "tbdarkrow">
						<TD width="30" align="center"><A href="../../RouterSvl?task=<%= Utilback.dongMa(getServletContext().getInitParameter("RedirectNoScript") +"NhaphanphoiSvl?userId="+userId +"&isKH="+ nppBean.getIsKhachhang()+"") %>" ><img src="../images/Back30.png" alt="Quay ve"  title="Quay ve" width="30" height="30" border="1" longdesc="Quay ve" style="border-style:outset"></A></TD>
					    <TD width="2" align="left" ></TD>
					    <TD width="30" align="left" ><A href="javascript:saveform()" ><IMG src="../images/Save30.png" title="Luu lai" alt="Luu lai" border = "1"  style="border-style:outset"></A></TD>
				    	<TD align="left" >&nbsp;</TD>
					</TR>
			</TABLE>
			<TABLE width="100%" border="0" cellpadding="0"  cellspacing="0">
				  	<tr>
							<TD align="left" colspan="6" class="legendtitle">
								<FIELDSET>
								<LEGEND class="legendtitle"><%=Utility.GLanguage("Báo lỗi nhập liệu",session,jedis) %></LEGEND>
			    				<textarea name="dataerror"  style="width: 100% ; color:#F00 ; font-weight:bold"  style="width:100%" rows="1" ><%= nppBean.getMessage() %></textarea>
                                        <%nppBean.setMessage(""); %>
								</FIELDSET>
						   </TD>
					</tr>

				  <TR>
				  <TD height="100%" width="100%">
						<FIELDSET>
						<LEGEND class="legendtitle" style="color:black">Thông tin <%= nppBean.getIsKhachhang().equals("1") ? "Khách hàng" : "Chi nhánh / NPP" %> </LEGEND>
						<TABLE border="0" width="100%" cellpadding="6" cellspacing="0">
							<TR>
								<TD class="plainlabel" width="130px"  ><%=Utility.GLanguage("Mã DMS",session,jedis) %><FONT class="erroralert"> *</FONT></TD>
								<TD class="plainlabel" width="220px" >
									<INPUT name="maSAP" id="maSAP" type="text" value="<%= nppBean.getMaSAP() %>" >
								</TD>
								<TD class="plainlabel" width="140px" > <%=Utility.GLanguage("Tên",session,jedis) %> <FONT class="erroralert"> *</FONT></TD>
                                <TD class="plainlabel"  >
                                	<INPUT name="nppTen" id="nppTen" type="text" value="<%= nppBean.getTen() %>" >
                                </TD>
                                
                                <TD class="plainlabel" width="140px" ><%=Utility.GLanguage("Xuất tại kho",session,jedis) %><FONT class="erroralert"> *</FONT></TD>
                                <TD class="plainlabel" >
                                	<INPUT name="xuattaikho" id="xuattaikho" type="text" value="<%= nppBean.getXuattaikho() %>" >
                                </TD>
                                
							</TR>
							<TR>
							  	<TD class="plainlabel"><%=Utility.GLanguage("Địa chỉ giao hàng",session,jedis) %> <FONT class="erroralert"> *</FONT> </TD>
                                <TD class="plainlabel" >
                                	<INPUT name="DiaChi" id="DiaChi" type="text" value="<%= nppBean.getDiachi() %>"  >
                                </TD>
                                
							   	 <TD class="plainlabel"><%=Utility.GLanguage("Tỉnh/Thành phố",session,jedis) %> <FONT class="erroralert"> *</FONT></TD>
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
	
							   	 <TD class="plainlabel"><%=Utility.GLanguage("Quận/Huyện",session,jedis) %> <FONT class="erroralert"> *</FONT></TD>
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
						  		<TD class="plainlabel"><%=Utility.GLanguage("Địa chỉ xuất HĐ",session,jedis) %> </TD>
                                <TD  class="plainlabel" ><INPUT name="diachixhd" id="diachixhd" type="text" value="<%= nppBean.getDiaChiXuatHoaDon() %>"  ></TD>
                                
                                <td class="plainlabel"><%=Utility.GLanguage("Người đại diện trên HĐ",session,jedis) %></td>
                                <td class="plainlabel" >
                                	<INPUT name="ddthd" id="ddthd" type="text" value="<%= nppBean.getTenNguoiDaiDien() %>">
                                </td>
                                <TD class="plainlabel"> <%=Utility.GLanguage("Mã số thuế",session,jedis) %></TD>
                                <TD class="plainlabel"><INPUT name="masothue" id="masothue" type="text" value="<%= nppBean.getmaSoThue() %>" ></TD>
                                
						  </TR>	
						   <TR>
                                
						  		<TD class="plainlabel"> <%=Utility.GLanguage("Điện thoại",session,jedis) %></TD>
                                <TD  class="plainlabel">
                                	<INPUT name="DienThoai" id="DienThoai" type="text" value="<%= nppBean.getSodienthoai() %>" >
                                </TD>
                                <td class="plainlabel"><%=Utility.GLanguage("FAX",session,jedis) %></td>
						  		<TD class="plainlabel"><input name="fax" id="fax" type="text" value="<%= nppBean.getFAX() %>"></TD>
							  	
						  		<TD class="plainlabel"> <%=Utility.GLanguage("Email",session,jedis) %></TD>
                        		<TD class="plainlabel"><input name="email" id="email" type="text" value="<%= nppBean.getEmail() %>"></TD>
						  </TR>	
						  <TR>
							   	<td class="plainlabel"><%=Utility.GLanguage("Hình thức TT",session,jedis) %></td>
                        		<td class="plainlabel"><input type="text" name="httt" id="httt" value="<%= nppBean.getHinhThucThanhToan() %>"></td>
                        		
                        		<TD class="plainlabel"><%=Utility.GLanguage("Tên ngân hàng",session,jedis) %></TD>
								<TD class="plainlabel"><INPUT name="nganhang" id="nganhang" value="<%= nppBean.getNganHang() %>" type="text"></TD>	
								<TD class="plainlabel"><%=Utility.GLanguage("Số tài khoản",session,jedis) %></TD>
								<TD class="plainlabel"><INPUT type="text" name="sotaikhoan" id="sotaikhoan" value="<%= nppBean.getSoTK() %>"></TD>
							</TR>
							
							<%//Khai bao Ky hieu hoa don %>
							<TR>
								<td class="plainlabel"><%=Utility.GLanguage("Ký hiệu hóa đơn",session,jedis) %></td>
                        		<td class="plainlabel">
                        			<input type="text" name="kyhieuhd" id="kyhieuhd" value="<%= nppBean.getKyhieuHD() %>">
                        		</td>
                        		<td class="plainlabel"><%=Utility.GLanguage("Số hóa đơn",session,jedis) %> : <%=Utility.GLanguage("Từ",session,jedis) %> :</td>
                        		<td class="plainlabel">
                        			<input type="text" name="soHDTu" id="soHDTu" value="<%= nppBean.getSoHDTu() %>" >
                        		</td>
                        		<td class="plainlabel"><%=Utility.GLanguage("Đến",session,jedis) %> :</td>
                        		<td class="plainlabel">
                        			<input type="text" name="soHDDen" id="soHDDen" value="<%= nppBean.getSoHDDen() %>" >
                        		</td>
							</TR>
							
							<TR>
								<%-- <td class="plainlabel">Mã kho</td>
                        		<td class="plainlabel">
                        			<input type="text" name="makho" id="makho" value="<%= nppBean.getMaKho() %>">
                        		</td>
                        		<td class="plainlabel">Mã nhập xuất</td>
                        		<td class="plainlabel">
                        			<input type="text" name="manx" id="manx" value="<%= nppBean.getMaNX() %>" >
                        		</td> --%>
                        		
                        		<td class="plainlabel"><%=Utility.GLanguage("CMND",session,jedis) %></td>
                        		<td class="plainlabel" >
                        			<input type="text" name="cmnd" id="cmnd" value="<%= nppBean.getCMTND()%>" >
                        		</td>
                        			
                        	<TD class="plainlabel">% <%=Utility.GLanguage("Chiết khấu",session,jedis) %>	</TD>
								<TD class="plainlabel"  colspan="3">
									<INPUT type="text"  name="ptchietkhau" maxlength="4" id="ptchietkhau"    value="<%= nppBean.getPTChietkhau()%>" size="40">
								</TD>	
							</TR>
						
							
							
						  <TR>
						  		<%-- <TD class="plainlabel" >Phụ trách tỉnh / GĐCN</TD>
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
						  		<TD class="plainlabel"><%=Utility.GLanguage("Khu vực",session,jedis) %><FONT class="erroralert"> *</FONT></TD>
								 <TD class="plainlabel" colspan="1">
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
                        		<TD class="plainlabel"> <%=Utility.GLanguage("Kho đặt hàng",session,jedis) %></TD>
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
	                                
		                                 <%=Utility.GLanguage("Quản lý tồn kho",session,jedis) %>
		                               	<%  if (nppBean.getPriSec().equals("1")){%>
		                                      <input name="prisec" type="checkbox" value ="1" checked>
		                                <%} else {%>
		                                       <input name="prisec" type="checkbox" value ="0">
		                                <%} %>    
		                                
	                                <% } else { %>
	                                	
	                                	 <%=Utility.GLanguage("Tự chốt xuất kho OTC",session,jedis) %>
		                               	<%  if (nppBean.getPriSec().equals("1")){%>
		                                      <input name="prisec" type="checkbox" value ="1" checked>
		                                <%} else {%>
		                                       <input name="prisec" type="checkbox" value ="1">
		                                <%} %> 
		                                
		                                &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
		                                
		                                 <%=Utility.GLanguage("Tự xuất kho ETC",session,jedis) %>
		                               	<%  if (nppBean.getTuxuatkhoETC().equals("1")){%>
		                                      <input name="tuxuatETC" type="checkbox" value ="1" checked>
		                                <%} else {%>
		                                       <input name="tuxuatETC" type="checkbox" value ="1">
		                                <%} %> 
		                                
		                                <br />
		                                
		                                  <%=Utility.GLanguage("Tự tạo hóa đơn",session,jedis) %>
		                               	<%  if (nppBean.getTutaohoadonOTC().equals("1")){%>
		                                      <input name="tutaohoadon" type="checkbox" value ="1" checked>
		                                <%} else {%>
		                                       <input name="tutaohoadon" type="checkbox" value ="1">
		                                <%} %> 
		                                
		                                &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
		                                
		                                  <%=Utility.GLanguage("Không phân biệt kênh OTC và ETC",session,jedis) %>
		                               	<%  if (nppBean.getDungchungkenh().equals("1")){%>
		                                      <input name="dungchungkenh" type="checkbox" value ="1" checked>
		                                <%} else {%>
		                                       <input name="dungchungkenh" type="checkbox" value ="1">
		                                <%} %> 
		                                
	                                <%} %>
                        			
                        		</Td>
                        		
							</TR>
							<TR style="display: none;"> 
							<TD class="plainlabel"><%=Utility.GLanguage("Địa bàn",session,jedis) %></TD>
							<TD class="plainlabel" colspan="5">
									<SELECT name="diabanId" onChange = "submitform();" class="select2" style = "width:200px">
										    	<option value=""> </option>
											    <% try{ while(diabanRs.next()){ 
										    			if(diabanRs.getString("pk_seq").equals(nppBean.getDiabanId())){ %>
										      				<option value='<%=diabanRs.getString("pk_seq")%>' selected><%=diabanRs.getString("ten") %></option>
										      			<%}else{ %>
										     				<option value='<%=diabanRs.getString("pk_seq")%>'><%=diabanRs.getString("ten") %></option>
										     			<%}}System.out.println("ABC: "+diabanRs.getString("pk_seq"));}
											    catch(java.sql.SQLException e){}
										     			%>
											 </SELECT>
											 </TD>
							</TR>
								
							<% if(nppBean.getIsKhachhang().equals("0")) { %>
							<TR>								
								
								<TD class="plainlabel" style="color: red;" ><%=Utility.GLanguage("Loại",session,jedis) %></TD>
								
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
								<%if(nppBean.getLoaiNPP().equals("0")) {%>
									<TD class="plainlabel" ><%=Utility.GLanguage("Cấp",session,jedis) %><FONT class="erroralert"> *</FONT></TD>
									<TD class="plainlabel" >
									<select name="idcapcn" id="idcapcn" class="select2" style="width: 200px;" onChange="submitform();" >
										<option value="" ></option>								
										<% if(capCnRs != null){ 
											while(capCnRs.next())
											{
												if(capCnRs.getString("idCapdo").equals(nppBean.getCapCnId())){ %>
													<option value="<%= capCnRs.getString("idCapdo") %>" selected="selected" ><%= capCnRs.getString("tencapdo") %>
													</option>
												<% }
												else { %>
												<option value="<%= capCnRs.getString("idCapdo") %>" ><%= capCnRs.getString("tencapdo") %></option>
											<% }
											}
											capCnRs.close();
										}%>
									</select>
								</TD>
								<%} else {%>													
											<input type="hidden" name="idcapcn" id="idcapcn" value='<%= nppBean.getCapCnId() %>'>
								
								<%}%>												
								<TD class="plainlabel" ><%=Utility.GLanguage("Trực thuộc",session,jedis) %><FONT class="erroralert"> *</FONT></TD>
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
								<%if(nppBean.getLoaiNPP().equals("0")) {%>
								<TR>
								<TD class="plainlabel"></TD>
								<TD class="plainlabel"></TD>
								<TD class="plainlabel"></TD>
								<TD class="plainlabel"></TD>
								<TD class="plainlabel" >
                                	Hoạt động 
                                	<%  if (nppBean.getTrangthai().equals("1")){%>
	                                      <input name="TrangThai" type="checkbox" value ="1" checked>
	                                <%} else {%>
	                                       <input name="TrangThai" type="checkbox" value ="0">
	                                <%} %>  
	                                      
                                </TD>
                                 <TD class="plainlabel"  >
                                	 <%=Utility.GLanguage("Quản lý SELL OUT",session,jedis) %>
                                	<%  if (nppBean.getQuanlySellOut().equals("1")){%>
	                                      <input name="quanlySellOut" type="checkbox" value ="1" checked>
	                                <%} else {%>
	                                       <input name="quanlySellOut" type="checkbox" value ="0">
	                                <%} %>  	        	                                                            
                                </TD>	
							</TR>
							<%}else{ %>
							<TD class="plainlabel" >
                                	Hoạt động 
                                	<%  if (nppBean.getTrangthai().equals("1")){%>
	                                      <input name="TrangThai" type="checkbox" value ="1" checked>
	                                <%} else {%>
	                                       <input name="TrangThai" type="checkbox" value ="0">
	                                <%} %>  
	                                      
                                </TD>
                                
                                 <TD class="plainlabel"  >
                                	 <%=Utility.GLanguage("Quản lý SELL OUT",session,jedis) %>
                                	<%  if (nppBean.getQuanlySellOut().equals("1")){%>
	                                      <input name="quanlySellOut" type="checkbox" value ="1" checked>
	                                <%} else {%>
	                                       <input name="quanlySellOut" type="checkbox" value ="0">
	                                <%} %>  	        	                                                            
                                </TD>
                                <%} %>
								</TR>
							<TR>
								<TD class="plainlabel"><%=Utility.GLanguage("Số ngày tồn kho an toàn",session,jedis) %></TD>
								<TD class="plainlabel" >
									<INPUT type="text" name="tonkhoantoan" id="tonkhoantoan" value="<%= nppBean.getTonkhoAntoan() %>" size="40" >
								</TD>
								<TD class="plainlabel"><%=Utility.GLanguage("Tần suất đặt hàng (Lần/Tuần)",session,jedis) %></TD>
								<TD class="plainlabel" >
									<INPUT type="text" name="tansuatDathang" id="tansuatDathang" value="<%= nppBean.getTansuatDathang() %>" size="40" >
								</TD>
							
								<TD class="plainlabel"><%=Utility.GLanguage("Tồn kho tối đa",session,jedis) %></TD>
								<TD class="plainlabel" >
									<INPUT type="text" name="tonkhotoida" id="tonkhotoida" value="<%= nppBean.getTonkhotoida() %>" size="40" >
								</TD>
							
							</TR>
							
							
							<% } else { %>
								
								<TR>
								
								<TD class="plainlabel" ><%=Utility.GLanguage("NHÂN VIÊN BÁN HÀNG",session,jedis) %></TD>
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
								
								<TD class="plainlabel"  >
                                	 <%=Utility.GLanguage("Hoạt động",session,jedis) %>
                                	<%  if (nppBean.getTrangthai().equals("1")){%>
	                                      <input name="TrangThai" type="checkbox" value ="1" checked>
	                                <%} else {%>
	                                       <input name="TrangThai" type="checkbox" value ="0">
	                                <%} %>  
	                               
	                                
	                               
	                                                            
                                </TD>	
                                
                                <TD class="plainlabel"  >
                                	  <%=Utility.GLanguage("Quản lý SELL OUT",session,jedis) %>
                                	<%  if (nppBean.getQuanlySellOut().equals("1")){%>
	                                      <input name="quanlySellOut" type="checkbox" value ="1" checked>
	                                <%} else {%>
	                                       <input name="quanlySellOut" type="checkbox" value ="0">
	                                <%} %>  	
	                               
	                                                            
                                </TD>	
                                
                               						
							</TR>
								
							<% } %>
							
					<% if( nppBean.getLoaiNPP().equals("0") || nppBean.getLoaiNPP().equals("4") ) { %>
							<tr class="plainlabel">
								 <TD class="plainlabel"><%=Utility.GLanguage("Loại công nợ",session,jedis) %> </TD>
                                    <TD  class="plainlabel" >
                                    	<SELECT name ="trangthai" onChange = "submitform();">
                                              <option value="1" class="select2"  selected><%=Utility.GLanguage("Công nợ theo hạn mức",session,jedis) %></option>
                                        </SELECT>     
                                       <%--  <% if (nppBean.getLoaiCN().equals("1")){%>
                                      		  <option value=""></option>
                                              <option value="1" selected>Công nợ tối đa</option>
                                              <option value="0">Công nợ theo mức trần</option>
                                              <option value="2">Công nợ theo hạn mức</option>
                                              
                                        <%}else if(nppBean.getLoaiCN().equals("0")) {%>
                                              <option value="" > </option>
                                              <option value="1" >Công nợ tối đa</option>
                                              <option value="0" selected>Công nợ theo mức trần</option>
                                              <option value="2">Công nợ theo hạn mức</option>
                                              
                                        <%}else if(nppBean.getLoaiCN().equals("2")) {%>                                                                                        
                                             <option value=""></option>
                                              <option value="1" >Công nợ tối đa</option>
                                              <option value="0">Công nợ theo mức trần</option>
                                              <option value="2" selected>Công nợ theo hạn mức</option>
                                        <%}else {%>
                                        	  <option value="" selected></option>
                                              <option value="1" >Công nợ tối đa</option>
                                              <option value="0">Công nợ theo mức trần</option>
                                              <option value="2">Công nợ theo hạn mức</option>
                                        <%} %> --%>                                        
                                      </TD>  
									<TD class="plainlabel"> <%=Utility.GLanguage("Hạn mức nợ",session,jedis) %></TD>
									<TD class="plainlabel" >
									<INPUT type="text" name="hanmucno" id="hanmucno" value="<%= nppBean.getHanmucno() %>" size="40"   onchange="DinhDang();">
									</TD>
									
									<TD class="plainlabel"><%=Utility.GLanguage("Số ngày nợ",session,jedis) %></TD>
									<TD class="plainlabel" >
									<INPUT type="text" name="songayno" id="songayno" value="<%= nppBean.getSongayno() %>" size="40"   onchange="DinhDang();">
									</TD>	
									
							</TR>
					<%} %>
					
					<TR>
						<TD class="plainlabel">	<%=Utility.GLanguage("Tên ký hợp đồng",session,jedis) %>:</TD>
						<TD class="plainlabel" >
							<INPUT type="text" name="tenkyhd" id="tenkyhd" value="<%= nppBean.getTenKyHd() %>" size="40">
						</TD>	
						<TD class="plainlabel">	</TD>
						<TD class="plainlabel" colspan="3">
						<%-- 	<INPUT type="text" name="hanmucdoanhthu" id="hanmucdoanhthu" onkeyup="Dinhdang(this)"   value="<%= nppBean.getHanmucdoanhthu() %>" size="40"> --%> 
						</TD>	
					</TR>
					
					<TR>
						<TD class="plainlabel"><%=Utility.GLanguage("Tên thủ kho",session,jedis) %>:	</TD>
						<TD class="plainlabel" >
							<INPUT type="text" name="tenthukho" id="tenthukho" value="<%= nppBean.getThuKho() %>" size="40">
						</TD>	
						<TD class="plainlabel"></TD>
						
						<TD class="plainlabel" colspan="3"></TD>	
					</TR>
					
					
					<TR>
						<TD class="plainlabel"> <%=Utility.GLanguage("Doanh số sell out",session,jedis) %> = </TD>
						<TD class="plainlabel" colspan="5">
							<INPUT type="text" name="tyleOutIn" id="tyleOutIn" value="<%= nppBean.getThuKho() %>" size="40">
							% <%=Utility.GLanguage("Doanh số sell in",session,jedis) %> 
						</TD>	
						
							
					</TR>
					
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