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

	
	
	

	NumberFormat formatter=new DecimalFormat("#,###,###"); 
%>

<% ResultSet thanhthinongthonRs = (ResultSet)nppBean.getThanhthinongthonRs()  ; %>
	<% ResultSet hch = (ResultSet)nppBean.getHangcuahang(); %>
	<% ResultSet kbh = (ResultSet)nppBean.getKenhbanhang();  %>
	<% ResultSet vtch = (ResultSet)nppBean.getVtcuahang();  %>
	<% ResultSet lch = (ResultSet)nppBean.getLoaicuahang(); %>



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
	 var tp = document.getElementById('TP');
	 var qh = document.getElementById('QH');	 
	 var isKH = document.getElementById('isKH').value;

	 
	
	 if(ten.value == "")
	 {
		 alert('Vui lòng nhập tên KH');
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

	
	 var nvbhId = document.getElementById('nvbhId');
	 if(nvbhId.value == "")
	 {
		 alert('Vui lòng chọn NHÂN VIÊN BÁN HÀNG');
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
		
	});
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
							 <TD align="left" colspan="2" class="tbnavigation">&nbsp;Thiết lập dữ liệu nền > Dữ liệu Kinh doanh > <%= nppBean.getIsKhachhang().equals("1") ? "Khách hàng" : "Chi nhánh / NPP" %> > Hiển thị</TD> 
							 <TD colspan="2" align="right" class="tbnavigation"><%=Utility.GLanguage("Chào mừng bạn",session,jedis) %>  <%=userTen %>&nbsp;  </TD> 
						  </tr>
						</table>
			 		</TD>
				</TR>
			</TABLE>
			<TABLE width="100%" border="0" cellpadding="0" cellspacing="1">
					<TR class = "tbdarkrow">
						<TD width="30" align="center"><A href="../../NhaphanphoiSvl?userId=<%=userId %>&isKH=<%= nppBean.getIsKhachhang() %>" ><img src="../images/Back30.png" alt="Quay ve"  title="Quay ve" width="30" height="30" border="1" longdesc="Quay ve" style="border-style:outset"></A></TD>
					    <TD width="2" align="left" ></TD>
					    <TD width="30" align="left" ></TD>
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
						<LEGEND class="legendtitle" style="color:black">Thông tin <%= "Khách hàng" %> </LEGEND>
						<TABLE border="0" width="100%" cellpadding="6" cellspacing="0">
																	<TR>
											<TD width="130" class="plainlabel">TÊN ĐƠN VỊ (dùng xuất HĐTC)<FONT
												class="erroralert"> *</FONT>
											</TD>
											<TD width="250" class="plainlabel"><INPUT type="text"
												name="nppTen" id="nppTen" value="<%= nppBean.getTen() %>">
											</TD>
											<TD width="12%" class="plainlabel">Mã DMS<FONT
												class="erroralert"> *</FONT>
											</TD>
											<TD width="250" class="plainlabel"><INPUT type="text"
												name="maSAP" value="<%= nppBean.getMaSAP() %>"></TD>
											<TD colspan="2" class="plainlabel">Hoạt động <%  if (nppBean.getTrangthai().equals("1")){%>
												<input name="TrangThai" type="checkbox" value="1"
												checked="checked"> <%} else {%> <input
												name="TrangThai" type="checkbox" value="0"> <%} %>
											</TD>
										</TR>
										<TR>
											<TD class="plainlabel">KHÁCH HÀNG</TD>
											<TD class="plainlabel"><INPUT type="text"
												name="chucuahieu" id="chucuahieu"
												value="<%= nppBean.getChucuahieu() %>" size="40">
											</TD>
											<TD class="plainlabel">Địa chỉ xuất HĐ<FONT
												class="erroralert"> *</FONT>
											</TD>
											<TD  class="plainlabel" colspan="2" ><INPUT type="text"
												name="diachixhd" id="diachixhd" value="<%= nppBean.getDiaChiXuatHoaDon() %>"
												size="40"> 
											</TD>
										
											
									
										</TR>
										<TR>
											<TD class="plainlabel">Tên cửa hiệu</TD>
											<TD class="plainlabel"><INPUT type="text"
												name="tencuahieu" id="tencuahieu"
												value="<%= nppBean.getTencuahieu() %>" size="40">
											</TD>
											<TD class="plainlabel">Địa chỉ giao hàng<FONTclass="erroralert"> *</FONT>
											</TD>
											<TD  class="plainlabel" colspan="2"><INPUT type="text"
												name="DiaChi" id="DiaChi" value="<%= nppBean.getDiachi() %>"
												size="40"> 
											</TD>
										</TR>
										<TR>
											<TD class="plainlabel">Tỉnh /Thành phố<FONT
												class="erroralert"> *</FONT>
											</TD>
											<TD class="plainlabel"><SELECT name="tpId" id="TP"
												onChange="submitform();">
													<option value=""></option>
													<% try{while(tp.next()){ 
								    	if(tp.getString("tpId").equals(nppBean.getTpId())){ %>
													<option value='<%=tp.getString("tpId")%>' selected><%=tp.getString("tpTen") %></option>
													<%}else{ %>
													<option value='<%=tp.getString("tpId")%>'><%=tp.getString("tpTen") %></option>
													<%}}}catch(java.sql.SQLException e){} %>
											</SELECT></TD>

											<TD class="plainlabel">Quận/Huyện <FONT
												class="erroralert"> *</FONT>
											</TD>
											<TD colspan="3" class="plainlabel"><SELECT name="qhId"
												id="QH">
													<option value=""></option>
													<%if(qh != null){ 
								      		try{while(qh.next()){ 
								    			if(qh.getString("qhId").equals(nppBean.getQhId())){ %>
													<option value='<%=qh.getString("qhId")%>' selected><%=qh.getString("qhTen") %></option>
													<%}else{ %>
													<option value='<%=qh.getString("qhId")%>'><%=qh.getString("qhTen") %></option>
													<%}}}catch(java.sql.SQLException e){} 
								     		
								      }	%>

											</SELECT></TD>

										</TR>


										
										<TR>
											<TD class="plainlabel"><%=Utility.GLanguage("Kênh bán hàng",session,jedis) %><FONT
												class="erroralert">*</FONT>
											</TD>
											<TD class="plainlabel" ><SELECT name="kbhId" id="kbhId"
												onChange="submitform();">

													<% try{while(kbh.next()){ 
								    	if(kbh.getString("kbhId").equals(nppBean.getKbhId())){ %>
													<option value='<%= kbh.getString("kbhId") %>' selected><%=kbh.getString("kbhTen") %></option>
													<%}else{ %>
													<option value='<%= kbh.getString("kbhId") %>'><%= kbh.getString("kbhTen") %></option>
													<%}}}catch(java.sql.SQLException e){} 
								     %>
											</SELECT>
											</TD>

											<TD class="plainlabel">Phường xã</TD>
											<TD colspan="3" class="plainlabel"><INPUT type="text"
												name="phuongxa" value="<%= nppBean.getPhuongxa() %>"
												size="15">
											</TD>
											
										</TR>
										<TR>
											<TD class="plainlabel">Điện thoại</TD>
											<TD class="plainlabel"><INPUT type="text"
												name="DienThoai" value="<%= nppBean.getSodienthoai() %>"
												size="15">
											</TD>
											<TD class="plainlabel" >Số di động	</TD>
											<TD class="plainlabel" colspan="2" ><INPUT type="text" name="didong" value="<%= nppBean.getDidong().trim() %>" ></TD>
											


										</TR>

										<TR>
											<TD class="plainlabel">Loại khách hàng</TD>
											<TD class="plainlabel"><SELECT name="xuatkhau">
													<option value=""></option>
													<% if(nppBean.getXuatkhau().equals("0") ) { %>
													<option value="0" selected="selected">Bán lẻ</option>
													<% } else { %>
													<option value="0">Bán lẻ</option>
													<% } %>
													<% if(nppBean.getXuatkhau().equals("1") ) { %>
													<option value="1" selected="selected">Bán buôn</option>
													<% } else { %>
													<option value="1">Bán buôn</option>
													<% } %>
													<% if(nppBean.getXuatkhau().equals("2") ) { %>
													<option value="2" selected="selected">Bán buôn và
														bán lẻ</option>
													<% } else { %>
													<option value="2">Bán buôn và bán lẻ</option>
													<% } %>
					

											</SELECT></TD>

									
											<TD class="plainlabel">Hợp đồng</TD>
											<TD class="plainlabel" colspan="2">
												<INPUT type="text" name="hopdong" value="<%= nppBean.getHopdong() %>" size="15">

											</TD>
 											 

										</TR>
										
										<TR>
											
											
											<TD width="15%" class="plainlabel">Phần trăm chiết khấu</TD>
											<TD class="plainlabel" colspan="4"><input type="text"
												name="ptchietkhau" value="<%= nppBean.getPTChietkhau() %>"
												style="text-align: right;"> %
												&nbsp;&nbsp;&nbsp;&nbsp;
												
												</TD>
										</TR>

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
											<TD class="plainlabel">Ngày Sinh</TD>
											<TD class="plainlabel" colspan="2">
											<INPUT type="text" class="days" name="ngaysinh" value="<%= nppBean.getNgaysinh() %>" >

											</TD>
										</TR>
										
										
										<TR>
								
											

										</TR>


										<tr>
			
											<TD class="plainlabel">Hạng khách hàng</TD>
											<TD class="plainlabel"><SELECT name="hchId">
													<option value=""></OPTION>
													<% if(hch!=null) 
													while(hch.next())
													{
														if(nppBean.getHchId()!=null)
														{
															if(nppBean.getHchId().indexOf(hch.getString("hchId")) >= 0)
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
											<TD class="plainlabel">Mã số thuế</TD>
											<TD colspan="3" class="plainlabel"><INPUT type="text"
												name="masothue" value="<%= nppBean.getmaSoThue() %>"
												size="15">
											</TD>						

										</TR>
									
							
										
										<TR>
											<TD class="plainlabel">Số ngày nợ</TD>
											<TD class="plainlabel"><INPUT type="text"
												name="songayno" value="<%=nppBean.getSongayno() %>"
												size="15">
											</TD>
	
											<TD class="plainlabel">Số tiền nợ</TD>
											<TD class="plainlabel" colspan="2"><INPUT type="text"
												name="sotienno" value="<%=nppBean.getSotienno() %>"
												size="11">
											</TD>

											


										</TR>
										
										<TR>
										  <TD class="plainlabel" valign="top"  >Vị trí cửa hàng </TD>
										  <TD class="plainlabel" valign="top" ><SELECT name="vtchId" >
								
										    <% try{ 
										    	while(vtch.next()){ 
								    				if(vtch.getString("vtchId").equals(nppBean.getVtchId())){ %>
								      					<option value='<%=vtch.getString("vtchId")%>' selected><%=vtch.getString("vtchTen") %></option>
								      			<%  }else{ 
								      					System.out.println(vtch.getString("vtchId")+";"+vtch.getString("vtchTen"));
								      			%>
								     					<option value='<%=vtch.getString("vtchId")%>'><%=vtch.getString("vtchTen") %></option>
								     				
								     			<%}
								    		    }
										      }catch(java.sql.SQLException e){} %>
                                          	</SELECT></TD>
											<TD class="plainlabel">Loại khách hàng</TD>
											<TD  class="plainlabel" colspan="2"  >
												<SELECT name="lchId" id="lchId" onChange="submitform();">
													<option value=""></option>
													<%  if(lch!=null)
														{
															try
															{
																while(lch.next())
																{ 
											    					if(lch.getString("lchId").equals(nppBean.getLchId())){ 	
											    			%>
																<option value='<%= lch.getString("lchId") %>' selected><%=lch.getString("lchTen") %></option>
																<%}else{ %>
																<option value='<%= lch.getString("lchId") %>'><%= lch.getString("lchTen") %></option>
																<%}} lch.close(); }catch(java.sql.SQLException e){e.printStackTrace();} 
														}	%>	
												</SELECT>
											</TD>
									  </TR>
									  <TR>
									  <TD class="plainlabel" >Thành thị nông thôn<FONT class="erroralert">*</FONT></TD>
											<TD  class="plainlabel" colspan="4" >
												<SELECT name="thanhthinongthonId"
													id="thanhthinongthonId"  >
														<option value="null"></option>
														<%if(thanhthinongthonRs != null){ 
									      		try{
									      			while(thanhthinongthonRs.next()){ 
									    			if(thanhthinongthonRs.getString("pk_seq").equals(nppBean.getThanhthinongthonId())){ %>
														<option value='<%=thanhthinongthonRs.getString("pk_seq")%>' selected><%=thanhthinongthonRs.getString("ten") %></option>
														<%}else{ %>
														<option value='<%=thanhthinongthonRs.getString("pk_seq")%>'><%=thanhthinongthonRs.getString("ten") %></option>
														<%}}}catch(java.sql.SQLException e){} 
									     		
									      		}	%>
	
												</SELECT>
										</TD>
								
									  </TR>
										
									</TABLE>
					
						</TABLE>
						</FIELDSET>
												
					</TD>
			      </TR>
		  	</TABLE>	
	</TD>
	</TR>
</TABLE>
</form>
</BODY>
</HTML>
<%}%>