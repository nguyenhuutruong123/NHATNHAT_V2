<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page
	import="geso.dms.center.beans.dieuchinhtonkho.IDieuchinhtonkhoList"%>
<%@ page import="java.sql.ResultSet"%>
<%@page import="java.text.NumberFormat"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@page import="java.text.DecimalFormat"%>
<%@ page import="geso.dms.center.util.*"%>
<%
	NumberFormat formatter = new DecimalFormat("#,###,###");
	String userId = (String) session.getAttribute("userId");
	String userTen = (String) session.getAttribute("userTen");
	String sum = (String) session.getAttribute("sum");
	Utility util = (Utility) session.getAttribute("util");
	if (!util.check(userId, userTen, sum)) {
		response.sendRedirect(request.getContextPath() + "/redirect.jsp");
	} else {
	IDieuchinhtonkhoList obj = (IDieuchinhtonkhoList) session.getAttribute("obj");
	ResultSet dctklist = (ResultSet) obj.getDctkList();
	ResultSet dvkd = (ResultSet) obj.getDvkd();
	ResultSet kbh = (ResultSet) obj.getKbh();

	ResultSet kho = (ResultSet) obj.getKho();
		int[] quyen = new int[6];
		quyen = util.Getquyen("DuyetdctkSvl","", userId);
	System.out.println(" quyen chot "+quyen[Utility.CHOT]);
	System.out.println(" quyen xoa "+quyen[Utility.XOA]);
	System.out.println(" quyen sua "+quyen[Utility.XEM]);
	

%>
<%String url = util.getChuyenNguUrl("DuyetdctkSvl", "",session); %>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"> <% Utility Utilback = new Utility(); %>
<HTML>
<HEAD>
<TITLE><%= getServletContext().getInitParameter("TITLENAME") %></TITLE>
<META http-equiv="Content-Type" content="text/html; charset=utf-8">


<LINK rel="stylesheet" type="text/css" media="print"
	href="../css/impression.css">
<LINK rel="stylesheet" href="../css/main.css" type="text/css">
<LINK rel="stylesheet" href="../css/calendar.css" type="text/css">


<script type="text/javascript" src="../scripts/jquery.min.1.7.js"></script>
<link href="../css/jquery-ui.css" rel="stylesheet" type="text/css" />
<script src="../scripts/ui/jquery.ui.core.js" type="text/javascript"></script>
<script src="../scripts/ui/jquery.ui.widget.js" type="text/javascript"></script>
<script src="../scripts/ui/jquery.ui.datepicker.js"
	type="text/javascript"></script>
<script type="text/javascript">
	$(document).ready(function() {
		$(".days").datepicker({
			changeMonth : true,
			changeYear : true
		});
	});
</script>

<script type="text/javascript" src="../scripts/jquery.js"></script>
<link rel="stylesheet" href="../css/jqueryautocomplete.css"
	type="text/css" />
<script type="text/javascript" src="../scripts/jqueryautocomplete.js"></script>



<link type="text/css" rel="stylesheet" href="../css/mybutton.css">
<SCRIPT language="Javascript" type="text/javascript">
	function clearform() {
		document.dctkForm.dvkdId.selectedIndex = 0;
		document.dctkForm.kbhId.selectedIndex = 0;
		document.dctkForm.khoId.selectedIndex = 0;
		document.dctkForm.tungay.value = "";
		document.dctkForm.denngay.value = "";
		document.dctkForm.trangthai.selectedIndex = "";
		submitform();
	}

	function submitform() {
		document.forms['dctkForm'].action.value = 'search';
		document.forms["dctkForm"].submit();
	}

	function xuatExcel() {
		document.forms['dctkForm'].action.value = 'toExcel';
		document.forms['dctkForm'].submit();
		document.forms['dctkForm'].action.value = '';
	}
</SCRIPT>
</HEAD>
<BODY leftmargin="0" bottommargin="0" topmargin="0" rightmargin="0">
	<form name="dctkForm" method="post" action="../../DuyetdctkSvl">
		<input type="hidden" name="userId" value='<%=userId%>'> <input
			type="hidden" name="action" value='1'>

<% redis.clients.jedis.Jedis jedis =  geso.dms.center.util.Utility.getJedis(); %><% geso.dms.center.util.Csrf csdr=new geso.dms.center.util.Csrf(request,response,true,false,true);%>
 <input type="hidden" name="<%=csdr.get_tokenName() %>" value='<%=csdr.get_tokenValue() %>'>
		<TABLE width="100%" border="0" cellspacing="0" cellpadding="0"
			height="100%">
			<TR>
				<TD colspan="4" align='left' valign='top' bgcolor="#FFFFFF">

					<TABLE width="100%" bgcolor="#FFFFFF" cellspacing="1"
						cellpadding="0">
						<TR>
							<TD align="left" class="tbnavigation">
								<TABLE width="100%" border="0" cellpadding="0" cellspacing="0">
									<TR height="22">
										<TD align="left" class="tbnavigation"><%=url %></TD>
										<TD align="right" class="tbnavigation"><%=Utility.GLanguage("Chào mừng bạn",session,jedis) %>  <%=userTen%>
										</TD>

									</TR>
								</TABLE>
							</TD>
						</TR>
					</TABLE>
					<TABLE width="100%" bgcolor="#FFFFFF" cellspacing="0"
						cellpadding="0">
						<TR>
							<TD>
								<TABLE border="0" width="100%" cellspacing="0" cellpadding="0">
									<TR>

										<TD width="100%" align="left">
											<FIELDSET>
												<LEGEND class="legendtitle">&nbsp;<%=Utility.GLanguage("Tiêu chí hiển thị",session,jedis) %>
													&nbsp;</LEGEND>

												<TABLE width="100%" cellpadding="6" cellspacing="0">
													<TR>
														<TD class="plainlabel"><%=Utility.GLanguage("Đơn vị kinh doanh",session,jedis) %></TD>
														<TD class="plainlabel"><SELECT name="dvkdId"
															onChange="submitform();">
																<option value=""></option>
																<%
																	try {
																			while (dvkd.next()) {
																				if (obj.getDvkdId().equals(dvkd.getString("dvkdId"))) {
																%>
																<option value="<%=dvkd.getString("dvkdId")%>" selected><%=dvkd.getString("dvkd")%></option>
																<%
																	} else {
																%>
																<option value="<%=dvkd.getString("dvkdId")%>"><%=dvkd.getString("dvkd")%></option>
																<%
																	}
																			}
																		} catch (java.sql.SQLException e) {
																		}
																%>
														</SELECT></TD>
														<TD class="plainlabel"><%=Utility.GLanguage("Từ ngày",session,jedis) %></TD>
														<TD class="plainlabel"><table border=0 cellpadding=0>
																<td class="plainlabel"><input class="days"
																	type="text" name="tungay" size="15"
																	value="<%=obj.getTungay()%>" onchange="submitform();"></td>
															</table></TD>
													</TR>
													<TR>
														<TD class="plainlabel"><%=Utility.GLanguage("Kênh bán hàng",session,jedis) %></TD>
														<TD class="plainlabel"><SELECT name="kbhId"
															onChange="submitform();">
																<option value=""></option>
																<%
																	try {
																			while (kbh.next()) {
																				if (obj.getKbhId().equals(kbh.getString("kbhId"))) {
																%>
																<option value="<%=kbh.getString("kbhId")%>" selected><%=kbh.getString("kbh")%></option>
																<%
																	} else {
																%>
																<option value="<%=kbh.getString("kbhId")%>"><%=kbh.getString("kbh")%></option>
																<%
																	}
																			}
																		} catch (java.sql.SQLException e) {
																		}
																%>
														</SELECT></TD>

														<TD class="plainlabel"><%=Utility.GLanguage("Đến ngày",session,jedis) %></TD>
														<TD class="plainlabel">
															<table border=0 cellpadding=0>
																<td class="plainlabel"><input class="days"
																	type="text" name="denngay" size="15"
																	value="<%=obj.getDenngay()%>" onchange="submitform();"></td>
															</table>
														</TD>
													</TR>

													<TR>
														<TD class="plainlabel"><%=Utility.GLanguage("Kho",session,jedis) %></TD>
														<TD class="plainlabel"><SELECT name="khoId"
															onChange="submitform();">
																<option value=""></option>
																<%
																	try {
																			while (kho.next()) {
																				if (obj.getKhoId().equals(kho.getString("khoId"))) {
																%>
																<option value="<%=kho.getString("khoId")%>" selected><%=kho.getString("ten") + "-"
									+ kho.getString("diengiai")%></option>
																<%
																	} else {
																%>
																<option value="<%=kho.getString("khoId")%>"><%=kho.getString("ten") + "-"
									+ kho.getString("diengiai")%></option>
																<%
																	}
																			}
																		} catch (java.sql.SQLException e) {
																		}
																%>
														</SELECT></TD>
														<TD class="plainlabel"><%=Utility.GLanguage("Trạng thái",session,jedis) %></TD>

														<TD class="plainlabel"><SELECT name="trangthai"
															onChange="submitform();">
																<%
																	if (obj.getttId().equals("0")) {
																%>
																<option value=""></option>
																<option value="0" selected="selected"><%=Utility.GLanguage("Chờ duyệt",session,jedis) %></option>
																<option value="1"><%=Utility.GLanguage("Đã duyệt",session,jedis) %></option>
																<%
																	} else if (obj.getttId().equals("1")) {
																%>
																<option value=""></option>
																<option value="0"><%=Utility.GLanguage("Chờ duyệt",session,jedis) %></option>
																<option value="1" selected="selected"><%=Utility.GLanguage("Đã duyệt",session,jedis) %></option>
																<%
																	} else {
																%>
																<option value="" selected="selected"></option>
																<option value="0"><%=Utility.GLanguage("Chờ duyệt",session,jedis) %></option>
																<option value="1"><%=Utility.GLanguage("Đã duyệt",session,jedis) %></option>
																<%
																	}
																%>
														</SELECT></TD>

													</TR>


													<TR>
														<TD class="plainlabel" colspan="4"><a class="button2"
															href="javascript:clearform()"> <img
																style="top: -4px;" src="../images/button.png" alt=""><%=Utility.GLanguage("Nhập lại",session,jedis) %>
														</a>&nbsp;&nbsp;&nbsp;&nbsp; <a class="button2"
															href="javascript:xuatExcel()"> <img
																style="top: -4px;" src="../images/button.png" alt=""><%=Utility.GLanguage("Xuất Excel",session,jedis) %>
														</a>&nbsp;&nbsp;&nbsp;&nbsp;</TD>

													</TR>
												</TABLE>

											</FIELDSET>
										</TD>
									</TR>
								</TABLE>
							</TD>
						</TR>
					</TABLE>

					<TABLE width="100%" bgcolor="#FFFFFF" cellspacing="0"
						cellpadding="0">
						<TR>
							<TD width="100%">
								<FIELDSET>
									<LEGEND class="legendtitle"><%=Utility.GLanguage("Kiểm kho",session,jedis) %></LEGEND>

									<TABLE class="contenu_fieldset" width="100%" cellpadding="0"
										cellspacing="0">
										<TR>
											<TD>
												<TABLE width="100%" border="0" cellspacing="1"
													cellpadding="4">

													<TR class="tbheader">
														<TH><%=Utility.GLanguage("Ngày điều chỉnh",session,jedis) %></TH>
														<TH><%=Utility.GLanguage("Số chứng từ",session,jedis) %></TH>
														<TH><%=Utility.GLanguage("Tổng số tiền",session,jedis) %></TH>
														<TH><%=Utility.GLanguage("Đơn vị kinh doanh",session,jedis) %></TH>
														<TH><%=Utility.GLanguage("Kênh bán hàng",session,jedis) %></TH>
														<TH><%=Utility.GLanguage("Kho",session,jedis) %></TH>
														<TH><%=Utility.GLanguage("Trạng thái",session,jedis) %></TH>
														<TH><%=Utility.GLanguage("Người tạo",session,jedis) %></TH>
														<TH><%=Utility.GLanguage("Người sửa",session,jedis) %></TH>
														<TH><%=Utility.GLanguage("Người duyệt",session,jedis) %></TH>

														<TH align="center">&nbsp; <%=Utility.GLanguage("Tác vụ",session,jedis) %></TH>
													</TR>
													<%
														int m = 0;
															String lightrow = "tblightrow";
															String darkrow = "tbdarkrow";
															while (dctklist.next()) {
																if (m % 2 != 0) {
													%>
													<TR class=<%=lightrow%>>
														<%
															} else {
														%>
													
													<TR class=<%=darkrow%>>
														<%
															}
																
															
														%>
														<TD align="left"><div align="left"><%=dctklist.getString("ngaydc")%></div></TD>
														<TD><div align="center"><%=dctklist.getString("chungtu")%></div></TD>
														<%
														
															String gia = "";
													
														if(dctklist.getString("tongtien")==null)
														{
															gia="0";
														}
														else
														{
															gia=dctklist.getString("tongtien");
														}
														System.out.println("tong tien 2:"+gia);
																		gia = formatter.format(Double.parseDouble(gia));
																	
														%>
														<TD><div align="center"><%=gia%></div></TD>
														<TD align="center"><%=dctklist.getString("dvkd")%></TD>
														<TD align="center"><%=dctklist.getString("kbh")%></TD>
														<TD align="center"><%=dctklist.getString("ten")%></TD>
														<%
															if (dctklist.getString("trangthai").equals("0")) {
														%>
														<TD align="center"><%=Utility.GLanguage("Chờ duyệt",session,jedis) %></TD>
														<%
															} else if (dctklist.getString("trangthai").equals("1") ){
														%>
														<TD align="center"><%=Utility.GLanguage("Đã duyệt",session,jedis) %></TD>
														<%
															} else {																
														%>
														<TD align="center"><%=Utility.GLanguage("Đã hủy",session,jedis) %></TD>
														<%} %>
														<TD align="center"><%=dctklist.getString("nguoitao")%></TD>
														<TD align="center"><%=dctklist.getString("nguoisua")%></TD>
														<%
															if (dctklist.getString("nguoiduyet").equals("0")) {
														%>
														<TD align="center">&nbsp;</TD>
														<%
															} else {
														%>
														<TD align="center"><%=dctklist.getString("nguoiduyet")%></TD>
														<%
															}
														%>

														<TD align="center">
															<TABLE border=0 cellpadding="0" cellspacing="0">
																<%
																	if (dctklist.getString("trangthai").equals("0")) {
																%>
																<TR>
																	<TD>
																		<%
																			if (quyen[Utility.CHOT] != 0) {
																		%> <A
																		href="../../RouterSvl?task=<%= Utility.dongMa(getServletContext().getInitParameter("RedirectNoScript") +"DctkDisplaySvl?userId="+userId+"&display="+dctklist.getString("chungtu"))%>"><img
																			src="../images/Chot.png" alt="Chốt" title="Chốt" width="20"
																			height="20" longdesc="Chốt" border=0"></A> 
																			<%
																			if (quyen[Utility.XOA] != 0) {
																		%>
																			<A
																		href="../../RouterSvl?task=<%= Utility.dongMa(getServletContext().getInitParameter("RedirectNoScript") +"DuyetdctkSvl?userId="+userId+"&delete="+dctklist.getString("chungtu"))%>">
																			<img src="../images/Delete20.png" alt="Xoa" title="Xóa"
																			width="20" height="20" longdesc="Xoa" border=0
																			onclick="if(!confirm('Bạn muốn hủy đơn hàng này?')) return false;">
																	</A> <%}
 	}
 %>
																	</TD>
																</TR>
																<%
																	} else {
																%>
																<TR>
																	<TD>
																		<%
																			if (quyen[Utility.XEM] != 0) {
																		%> <A
																		href="../../DctkDisplaySvl?userId=<%=userId%>&display=<%=dctklist.getString("chungtu")%>"><img
																			src="../images/Display20.png" alt="Hiển thị" title="Hiển thị"
																			width="20" height="20" longdesc="Hiển thị" border=0"></A>
																		<%
																			}
																		%>
																	</TD>
																	<TD width=20>&nbsp;</TD>

																</TR>
																<%
																	}
																%>
															</TABLE>
														</TD>
													</TR>
													<%
														m++;
															}
													%>
													<tr class="tbfooter">
														<td colspan="12">&nbsp;&nbsp;&nbsp;</td>
													</tr>
												</TABLE>
											</TD>
										</TR>
									</TABLE>

								</FIELDSET>
							</TD>
						</TR>

					</TABLE> <!--end body Dossier-->
				</TD>
			</TR>
		</TABLE>
	</form>
	<%geso.dms.center.util.Utility.JedisClose(jedis); %>
</BODY>
</HTML>
<%
	}
%>