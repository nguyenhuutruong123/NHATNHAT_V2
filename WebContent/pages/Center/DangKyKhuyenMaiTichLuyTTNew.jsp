<%@page import="java.sql.SQLException"%>
<%@page
	import="geso.dms.center.beans.dangkykhuyenmaitichluy.IDangkykhuyenmaitichluyTT"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="geso.dms.distributor.beans.dangkytrungbay.*"%>
<%@ page import="java.sql.ResultSet"%>
<%@ page import="java.util.Hashtable"%>
<%@ page  import = "geso.dms.center.util.*" %>


<%
	String userId = (String) session.getAttribute("userId");  
	String userTen = (String) session.getAttribute("userTen");  	
	String sum = (String) session.getAttribute("sum");
	Utility util = (Utility) session.getAttribute("util");
	if(!util.check(userId, userTen, sum)){
		response.sendRedirect("/SOHACO/index.jsp");
	} else {
%>
<%
	IDangkykhuyenmaitichluyTT obj = (IDangkykhuyenmaitichluyTT)session.getAttribute("obj");
%>
<%
	ResultSet ctkmIds = (ResultSet)obj.getCtkmRs();
%>
<%
	ResultSet khRs = (ResultSet)obj.getKhList();
%>
<%
	ResultSet NppRs = (ResultSet)obj.getNppRs();
%>
<%
	ResultSet nvbhRs = (ResultSet)obj.getNvBanhang();
%>
<%
	ResultSet vungRs = (ResultSet)obj.getVungRs();
%>
<%
	ResultSet khuvucRs = (ResultSet)obj.getKhuvucRs();
%>
<%
	Hashtable<String, Integer> kh_muc = obj.getMucDangky();
%>
<%
	Hashtable<String, Integer> kh_stt = obj.getSTT();
%>
<% String nnId = (String)session.getAttribute("nnId"); %>
<% if(nnId == null) {
 nnId = "vi";  
 }

%>
 <%String url = util.getChuyenNguUrl("DangkykhuyenmaitichluyTTSvl", "",session); %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd"> <% Utility Utilback = new Utility(); %>
<html>
<head>
<TITLE><%= getServletContext().getInitParameter("TITLENAME") %></TITLE>
<META http-equiv="Content-Type" content="text/html; charset=utf-8">
<META http-equiv="Content-Style-Type" content="text/css">
<LINK rel="stylesheet" type="text/css" media="print"
	href="../css/impression.css">
<LINK rel="stylesheet" href="../css/main.css" type="text/css">

<link href="../css/jquery-ui.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="../scripts/Timepicker/jquery.min.js"></script>
<script type="text/javascript"
	src="../scripts/Timepicker/jquery-ui.min.js"></script>

<link href="../css/jquery-ui.css" rel="stylesheet" type="text/css" />
<script src="../scripts/ui/jquery.ui.core.js" type="text/javascript"></script>
<script src="../scripts/ui/jquery.ui.widget.js" type="text/javascript"></script>

<script src="../scripts/ui/jquery.ui.datepicker.js"
	type="text/javascript"></script>
<script type="text/javascript">
	$(document).ready(function() {		
			$( ".days" ).datepicker({			    
					changeMonth: true,
					changeYear: true				
			});            
        });	
		
    </script>	
	
<script type="text/javascript" src="..scripts/jquery-1.js"></script>
<link type="text/css" rel="stylesheet" href="../css/mybutton.css">
<LINK rel="stylesheet" type="text/css" href="../css/style.css" />
<link media="screen" rel="stylesheet" href="../css/colorbox.css">
<script src="../scripts/colorBox/jquery.colorbox.js"></script>
<script>
	$(document).ready(function() {
		$("#accordion").accordion({
			autoHeight : false
		}); //autoHeight content set false
		$("#accordion").accordion("option", "icons", {
			'header' : 'ui-icon-plus',
			'headerSelected' : 'ui-icon-minus'
		});
		$("#accordion").accordion({
			active : 1
		});

		var ac = document.getElementById("action").value;
		if (ac == "upload") {
			document.forms['dktbForm'].submit();
		}
	});
</script>
<script type='text/javascript' src='../scripts/loadingManual.js'></script>
<script type="text/javascript">

	
	function xuatexcel()
	{
		  
		  document.forms["dktbForm"].action.value = "excel";
		  document.forms["dktbForm"].submit(); 
	}

	function saveform() {
	//	startload();
		document.forms['dktbForm'].action.value = 'save';
		document.forms['dktbForm'].submit();
	}

	function submitform() {
		//startload();
		document.forms['dktbForm'].action.value = 'submit';
		document.forms['dktbForm'].submit();
	}

	function submitform2() {
	//	startload();
		var cttb = document.getElementById("ctkmId");
		if (cttb.value == "") {
			alert('vui lòng chọn chương trình khuyến mãi..');
			return;
		}
		document.forms['dktbForm'].action.value = 'submit';
		document.forms['dktbForm'].submit();
	}

	function CheckALL() {
		var khachhang = document.getElementsByName("khIds");
		var khSelected = document.getElementsByName("khIdSelected");
		for (i = 0; i < khSelected.length; i++) {
			for (j = 0; j < khachhang.length; j++) {
				if (khSelected[i].value == khachhang[j].value) {
					if (document.dktbForm.chkAll.checked == true) {
						khachhang[j].checked = true;
					} else {
						khachhang[j].checked = false;
					}
				}
			}
		}
	}
	function CheckALLNpp() {
		var npp = document.getElementsByName("nppIds");
		for (i = 0; i < npp.length; i++) {
			if (document.dktbForm.chkAllNpp.checked == true) {
				npp[i].checked = true;
			} else {
				npp[i].checked = false;
			}
		}
	}
	function nppCheck(chk, id) {
		if (chk.checked == true)
			document.getElementById("npp_" + id).value = id;
		else
			document.getElementById("npp_" + id).value = "";
	}

	function upload() {
		//startload();
		document.forms['dktbForm'].action.value = 'upload';
		document.forms["dktbForm"].setAttribute('enctype',
				"multipart/form-data", 0);
		document.forms["dktbForm"].submit();

	}
</script>
</head>
<BODY leftmargin="0" bottommargin="0" topmargin="0" rightmargin="0">
	<form name="dktbForm" method="post" action="../../DangkykhuyenmaitichluyTTUpdateSvl">
	
<% redis.clients.jedis.Jedis jedis =  geso.dms.center.util.Utility.getJedis(); %><% geso.dms.center.util.Csrf csdr=new geso.dms.center.util.Csrf(request,response,true,false,true);%>
 <input type="hidden" name="<%=csdr.get_tokenName() %>" value='<%=csdr.get_tokenValue() %>'>
		<INPUT name="userId" type="hidden" value='<%=userId%>' size="30">
		<input type="hidden" name="id" value='<%=obj.getId()%>'> <input
			type="hidden" name="action" id="action" value="<%=obj.getAction()%>">

		<div id="main" style="width: 99%; padding-left: 2px">
			<div align="left" id="header" style="width: 100%; float: none">
				<div style="width: 40%; padding: 5px; float: left"
					class="tbnavigation">
					<%
						if (obj.getId().length() == 0) {
					%>
					<%=url %>> <%=Utility.GLanguage("Tạo mới",session,jedis) %>
					<%
						} else {
					%>
					<%=url %>> <%=Utility.GLanguage("Cập nhật",session,jedis) %>
					<%
						}
					%>
				</div>
				<div align="right" style="padding: 5px" class="tbnavigation">
					Chào mừng<%=Utility.GLanguage("",session,jedis) %>
					<%=userTen%>
					&nbsp;
				</div>
			</div>

			<div align="left" id="button"
				style="width: 100%; height: 32px; padding: 0px; float: none"
				class="tbdarkrow">
				<A href="../../DangkykhuyenmaitichluyTTSvl?userId=<%=userId%>">
					<img src="../images/Back30.png" alt="Quay ve" title="Quay ve"
					border="1px" longdesc="Quay ve" style="border-style: outset">
				</A> <A href="javascript:saveform()"> <IMG
					src="../images/Save30.png" title="Luu lai" alt="Luu lai"
					border="1px" style="border-style: outset"></A>

			</div>
			<div align="left" style="width: 100%; float: none">
				<fieldset>
					<legend class="legendtitle"><%=Utility.GLanguage("Báo lỗi nhập liệu",session,jedis) %></legend>
					<textarea name="dataerror"
						style="width: 100%; color: #F00; font-weight: bold" cols="71"
						rows="1" style="width: 100% " readonly="readonly"><%=obj.getMessage()%></textarea>

				</fieldset>
			</div>
			<div align="left" style="width: 100%; float: none">
				<fieldset>
					<legend class="legendtitle"><%=Utility.GLanguage("Đăng ký khuyến mãi tích lũy",session,jedis) %> </legend>
					<div style="width: 100%; float: non; clear: left; font-size: 0.7em">
						<TABLE width="100%" cellpadding="5px" cellspacing="0px">
							<TR>
								<TD class="plainlabel" width="130px"><%=Utility.GLanguage("Chương trình",session,jedis) %></TD>
								<TD class="plainlabel">
									<%
										if (obj.getId().length() == 0) {
									%> <select name="ctkmId"
									id="ctkmId" onchange="submitform()">
										<option value=''></option>
										<%
											if (ctkmIds != null) {
														try {
															while (ctkmIds.next()) {
																if (ctkmIds.getString("pk_seq").equals(
																		obj.getctkmId())) {
										%>
										<option value='<%=ctkmIds.getString("pk_seq")%>' selected><%=ctkmIds.getString("scheme")%></option>
										<%
											} else {
										%>
										<option value='<%=ctkmIds.getString("pk_seq")%>'><%=ctkmIds.getString("scheme")%></option>
										<%
											}
															}
															ctkmIds.close();
														} catch (java.sql.SQLException e) {
														}
													}
										%>
								</select> <%
 	} else {
 			try {
 				while (ctkmIds.next()) {
 					if (ctkmIds.getString("pk_seq").equals(
 							obj.getctkmId())) {
 %> <input
									type="hidden" name="ctkmId"
									value='<%=ctkmIds.getString("pk_seq")%>'> <input
									type="text" value="<%=ctkmIds.getString("scheme")%>">
									<%
										}
									%> <%
 	}
 			} catch (Exception ex) {
 			}
 		}
 %>
								</TD>
							</TR>

							<TR>
								<TD class="plainlabel"><%=Utility.GLanguage("Từ ngày",session,jedis) %></TD>
								<TD class="plainlabel"><input type="text"
									value="<%=obj.getTungay()%>" readonly="readonly" />
									&nbsp;&nbsp;&nbsp;&nbsp; &nbsp;&nbsp;&nbsp;&nbsp;
									&nbsp;&nbsp;&nbsp;&nbsp; <%=Utility.GLanguage("Đến ngày",session,jedis) %> &nbsp;&nbsp;&nbsp;&nbsp; <input
									type="text" value="<%=obj.getDenngay()%>" readonly="readonly" />
								</TD>
							</TR>

							<TR>
				                <TD class="plainlabel"><%=Utility.GLanguage("Diễn giải",session,jedis) %></TD>
				                <TD class="plainlabel" >
				                    <input type="text" value="<%= obj.getDiengiai() %>"  name = "diengiai"  />
				                   &nbsp;&nbsp;&nbsp;&nbsp; &nbsp;&nbsp;&nbsp;&nbsp; &nbsp;&nbsp;&nbsp;&nbsp; <%=Utility.GLanguage("Ngày đăng ký",session,jedis) %> &nbsp;&nbsp;&nbsp;&nbsp; 
				                    <input  name = "ngaydangky" class="days" type="text" value="<%= obj.getNgaydangky() %>" readonly="readonly" />
				                </TD>
				            </TR> 
							
							
							<%
										if (obj.getId().length() > 0) {
									%> 
								<tr class="plainlabel">
									<TD class="plainlabel">Chọn file upload<%=Utility.GLanguage("",session,jedis) %></TD>
									<td colspan="1"><INPUT type="file" name="filename"
										size="40" value=''></td>
								</tr>
								<tr class="plainlabel">
									<td >&nbsp;<a class="button2"
										href="javascript:xuatexcel()"> <img style="top: -4px;"
											src="../images/button.png" alt=""><%=Utility.GLanguage("Xuất Excel",session,jedis) %>
									</a>
									</td>
									<td >&nbsp;<a class="button2"
										href="javascript:upload()"> <img style="top: -4px;"
											src="../images/button.png" alt=""><%=Utility.GLanguage("Upload",session,jedis) %>
									</a>
									</td>
								</tr>
							<%} %>
						</TABLE>

						
								<hr />
								
								<%
										if (obj.getId().length() > 0) {
									%> 
									<div id="accordion"
							style="width: 100%; height: auto; float: none; font-size: 1.0em"
							align="left">


							<h1 style="font-size: 1.8em">
								<a href="#"><%=Utility.GLanguage("Khách hàng",session,jedis) %></a>
							</h1>
							<div style="height: auto">
								<!-- <a class="button" href="javascript:submitform();">
					<img style="top: -4px;" src="../images/button.png" alt=""> Hiển thị Khách hàng</a> -->
									
									
								<table style="width: 100%" cellpadding="0" cellspacing="1">
									<Tr class="tbheader">
										<td align="center" width="5%"><%=Utility.GLanguage("STT",session,jedis) %></td>
										<td align="center" width="7%"><%=Utility.GLanguage("MÃ KH",session,jedis) %></td>
										<td align="center"  width="20%"><%=Utility.GLanguage("Tên khách hàng",session,jedis) %></td>
										<td align="center"  width="20%"><%=Utility.GLanguage("Chi nhánh/ Nhà phân phối",session,jedis) %></td>
										<td width="20%" align="center"><%=Utility.GLanguage("Người tạo",session,jedis) %></td>
										<%-- <td width="10%" align="center"><%=ChuyenNgu.get("Từ PDA",nnId) %></td> --%>
										<td width="10%" align="center"><%=Utility.GLanguage("Mức đăng ký",session,jedis) %></td>
										
										
									</Tr>
									<%
										ResultSet khDK = obj.getKhDangkyRs();
										if (khDK != null) {
												int stt = 1;
												while (khDK.next()) {
									%>

									<TR class="tblightrow">
										<td><input type="text"
											name="stt_<%=stt %>" value="<%=stt%>"
											style="width: 100%; text-align: center;" readonly="readonly">
										</td>
										<td><input type="text"
											value="<%=khDK.getString("MÃ KH(Dữ liệu upload)")%>" style="width: 100%"
											readonly="readonly"></td>
										<td><input type="text"
											value="<%=khDK.getString("TÊN")%>" style="width: 100%"
											readonly="readonly"></td>

										<td><input type="text"
											value="<%=khDK.getString("NPP")%>" style="width: 100%"
											readonly="readonly"></td>
										<td><input type="text"
											value="<%=khDK.getString("Người tạo")%>" style="width: 100%"
											readonly="readonly"></td>
											
										<%-- <td  ><input type="text" 
											value="<%=khDK.getString("Từ PDA").equals("1") ? "x" : ""%>" style="width: 100%; text-align: center;"
											readonly="readonly"></td> --%>
												
										<td align="center"><input type="text"
											name="mdk_<%=stt%>"
											value="<%= khDK.getString("Mức(Dữ liệu upload)") %>"
											style="width: 100%; text-align: center;" readonly="readonly"> </td>
										
									</TR>
									<%
										stt++;
												}
												khDK.close();
											}
									%>
								</table>
								</div>
						</div>
								<%} %>
							
					</div>
				</fieldset>
			</div>
		</div>


	</form>
	<%geso.dms.center.util.Utility.JedisClose(jedis); %>
</body>
<!-- <script type='text/javascript' src='../scripts/loadingv2.js'></script> -->
</HTML>
<%
	if (obj != null) {
			obj.DBclose();
			obj = null;
		}
		try {
			if (ctkmIds != null)
				ctkmIds.close();
		} catch (SQLException e) {
		}
%>
<%
	}
%>

