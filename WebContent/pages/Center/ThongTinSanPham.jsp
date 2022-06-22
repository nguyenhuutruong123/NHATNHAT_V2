<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page
	import="geso.dms.center.beans.thongtinsanpham.IThongtinsanphamList"%>
<%@ page import="java.sql.ResultSet"%>
<%@ page import="geso.dms.center.util.*"%>
<%@page import="java.text.DecimalFormat"%>
<%@page import="java.text.NumberFormat"%>
<%
	NumberFormat formatter = new DecimalFormat("#,###,###");
	String userId = (String) session.getAttribute("userId");
	String userTen = (String) session.getAttribute("userTen");
	String sum = (String) session.getAttribute("sum");
	Utility util = (Utility) session.getAttribute("util");
	
	
	
	if (!util.check(userId, userTen, sum)) {
		response.sendRedirect(request.getContextPath() + "/redirect.jsp");
	} 
	else {

		IThongtinsanphamList obj = (IThongtinsanphamList) session.getAttribute("obj");
		ResultSet splist = (ResultSet) obj.getThongtinsanphamList();
		ResultSet dvkd = obj.getDvkd();
		ResultSet nh = obj.getNh();
		ResultSet cl = obj.getCl();
		int[] quyen = new int[6];
		quyen = util.Getquyen("ThongtinsanphamSvl", "", userId);
		
		String url = util.getChuyenNguUrl("ThongtinsanphamSvl", "",session);
%>
<% Utility Util = new Utility(); %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"> <% Utility Utilback = new Utility(); %>
<HTML>
<HEAD>
<TITLE><%= getServletContext().getInitParameter("TITLENAME") %></TITLE>
<META http-equiv="Content-Type" content="text/html; charset=utf-8">

<META http-equiv="Content-Style-Type" content="text/css">
<style type="text/css">
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
	filter: progid:DXImageTransform.Microsoft.Shadow(color=gray, direction=135);
}

#dhtmlpointer {
	position: absolute;
	left: -300px;
	z-index: 101;
	visibility: hidden;
}

th {
	cursor: pointer;
}
</style>
<LINK rel="stylesheet" href="../css/main.css" type="text/css">
<link type="text/css" rel="stylesheet" href="../css/mybutton.css">
<SCRIPT language="JavaScript" type="text/javascript">
	function submitform() {
		document.spForm.action.value = 'search';
		document.forms["spForm"].submit();
	}

	function clearform() {
		document.spForm.masp.value = "";
		document.spForm.tensp.value = "";
		document.spForm.dvkdId.selectedIndex = 0;
		//document.spForm.nhId.selectedIndex = 0;
		//	document.spForm.clId.selectedIndex = 0;
		document.spForm.trangthai.selectedIndex = 0;
		submitform();
	}

	function xuatExcel() {
		document.forms['spForm'].action.value = 'excel';
		document.forms['spForm'].submit();
	}

	function newform() {
		document.forms['spForm'].action.value = 'new';
		document.forms['spForm'].submit();
	}
	function thongbao() {
		var tt = document.forms['spForm'].msg.value;
		if (tt.length > 0)
			alert(document.forms['spForm'].msg.value);
	}
	
	 function upload()
	 {
		 if(document.forms["spForm"].filename.value=="")
		 {
			 alert("Chưa tìm file upload lên. Vui lòng chọn file cần upload.");
		 }
		 else
		 {
			 document.forms['spForm'].action.value= 'upload';
			 document.forms['spForm'].setAttribute('enctype', "multipart/form-data", 0);
			 document.forms['spForm'].submit();
		 }
		 
	 	
	 }
</SCRIPT>
</HEAD>
<BODY leftmargin="0" bottommargin="0" topmargin="0" rightmargin="0">
	<form name="spForm" method="post" action="../../ThongtinsanphamSvl">
	
	<% redis.clients.jedis.Jedis jedis =  geso.dms.center.util.Utility.getJedis(); %>
	
	<% geso.dms.center.util.Csrf csdr=new geso.dms.center.util.Csrf(request,response,true,false,true);%>
 <input type="hidden" name="<%=csdr.get_tokenName() %>" value='<%=csdr.get_tokenValue() %>'>
	
		<input type="hidden" name="userId" value='<%=obj.getUserId()%>'>
		<input type="hidden" name="userTen" value='<%= userTen%>'>
		
		<input type="hidden" name="action" value='1'> <input
			type="hidden" name="msg" value='<%=  Utility.GLanguage(obj.getMsg(),session,jedis) %>'> <input
			type="hidden" name="currentPage" value="<%=obj.getCurrentPage()%>">

		<script language="javascript" type="text/javascript">
			thongbao();
		</script>

		<TABLE width="100%" border="0" cellspacing="0" cellpadding="0"
			height="100%">

			<TR>
				<TD colspan="4" align='left' valign='top' bgcolor="#FFFFFF">
					<TABLE width="100%" cellpadding="0" cellspacing="1">
						<TR>
							<TD align="left" class="tbnavigation">
								<TABLE width="100%" border="0" cellpadding="0" cellspacing="0">
									<TR height="22">
										<TD align="left" colspan="2" class="tbnavigation">
											&nbsp;<%= url %></TD>
										<TD colspan="2" align="right" class="tbnavigation"><%=Utility.GLanguage("Chào mừng bạn",session,jedis) %>  <%=userTen%>&nbsp;
										</TD>
									</TR>
								</TABLE>
							</TD>
						</TR>
					</TABLE>
					<TABLE width="100%" cellpadding="0" cellspacing="1">
						<TR>
							<TD>
								<TABLE border="0" width="100%" cellpadding="0" cellspacing="0">
									<TR>
										<TD width="100%" align="left">
											<FIELDSET>
												<LEGEND class="legendtitle">&nbsp;<%=Utility.GLanguage("Tiêu chí tìm kiếm",session,jedis) %>
													&nbsp;</LEGEND>

												<TABLE class="tblight" width="100%" cellpadding="6"
													cellspacing="0">
													<TR>
														<TD class="plainlabel"><%=Utility.GLanguage("Mã",session,jedis) %></TD>
														<TD class="plainlabel"><INPUT name="masp"
															type="text" size="30" value='<%=obj.getMasp()%>'
															onChange="submitform();"></TD>
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
													</TR>

													<TR>
														<TD class="plainlabel"><%=Utility.GLanguage("Tên",session,jedis) %></TD>
														<TD class="plainlabel"><INPUT name="tensp"
															type="text" size="30" value='<%=obj.getTensp()%>'
															onChange="submitform();"></TD>
														<TD class="plainlabel"><%=Utility.GLanguage("Trạng thái",session,jedis) %></TD>
														<TD class="plainlabel"><select name="trangthai"
															onChange="submitform();">
																<%
																	if (obj.getTrangthai().equals("0")) {
																%>
																<option value="2"></option>
																<option value="1"><%=Utility.GLanguage("Hoạt động",session,jedis) %></option>
																<option value="0" selected><%=Utility.GLanguage("Ngưng hoạt động",session,jedis) %></option>
																<%
																	} else {
																			if (obj.getTrangthai().equals("1")) {
																%>
																<option value="2"></option>
																<option value="1" selected><%=Utility.GLanguage("Hoạt động",session,jedis) %></option>
																<option value="0"><%=Utility.GLanguage("Ngưng hoạt động",session,jedis) %></option>
																<%
																	} else {
																%>
																<option value="2" selected></option>
																<option value="1"><%=Utility.GLanguage("Hoạt động",session,jedis) %></option>
																<option value="0"><%=Utility.GLanguage("Ngưng hoạt động",session,jedis) %></option>
																<%
																	}
																		}
																%>
														</select></TD>
													</TR>
													<TR>
														<TD class="plainlabel" colspan="4"><a class="button2"
															href="javascript:clearform()"> <img
																style="top: -4px;" src="../images/button.png" alt=""><%=Utility.GLanguage("Nhập lại",session,jedis) %>
														</a>&nbsp;&nbsp;&nbsp;&nbsp; <a class="button2"
															href="javascript:xuatExcel()"> <img
																style="top: -4px;" src="../images/button.png" alt=""><%=Utility.GLanguage("Xuất Excel",session,jedis) %>
														</a>
														</td>
													</TR>
													<TR>
														<TD class="plainlabel" colspan="4">
								    					<input type="file" name="filename" value=""/>
								    					 &nbsp;&nbsp;&nbsp;
								    					<a class="button3" href="javascript:upload()" >
								    					<img style="top: -4px; width: 38%; height: auto;" src="../images/upload.png" alt="">Upload </a> 														
														</TD>
													</TR>


												</TABLE>

											</FIELDSET>
										</TD>
									</TR>
								</TABLE>

								<TABLE width="100%" cellpadding="0" cellspacing="1">
									<TR>
										<TD width="100%">
											<FIELDSET>
												<LEGEND class="legendtitle">
													&nbsp;<%=Utility.GLanguage("Thông tin sản phẩm",session,jedis) %>  &nbsp;&nbsp;
													<%
														if (quyen[Utility.THEM] != 0) {
													%>
													<a class="button3" href="javascript:newform()"> <img
														style="top: -4px;" src="../images/New.png" alt=""><%=Utility.GLanguage("Tạo mới",session,jedis) %> 
													</a>
													<%
														}
													%>
												</LEGEND>

												<TABLE width="100%">
													<TR>
														<TD width="98%">
															<TABLE width="100%" id="table"
																class="tabledetail sortable" border="0" cellspacing="1"
																cellpadding="4">
																<thead>
																	<TR class="tbheader">
																		<TH width="4%"><%=Utility.GLanguage("STT",session,jedis) %></TH>
																		<TH width="10%"><%=Utility.GLanguage("Mã",session,jedis) %> </TH>
																		<TH width="28%"><%=Utility.GLanguage("Tên",session,jedis) %> </TH>
																		<TH width="9%"><%=Utility.GLanguage("Đơn vị đo lường",session,jedis) %> </TH>
																		<TH width="19%"><%=Utility.GLanguage("Đơn vị kinh doanh",session,jedis) %> </TH>
																		<!-- <TH width="0%" style="display: none;">Nhãn hàng</TH>
									<TH width="0%" style="display: none;">Chủng loại</TH> -->
																		<TH width="13%"><%=Utility.GLanguage("Trạng thái",session,jedis) %> </TH>
																		<!-- <TH width="1%" style="display: none;" >Gía bl chuẩn</TH> -->
																		<TH class="nosort" width="15%"><%=Utility.GLanguage("Tác vụ",session,jedis) %> </TH>
																	</TR>
																</thead>
																<tbody>
																	<%
																		if (splist != null) {
																				try {
																					int m = 0;
																					String lightrow = "tblightrow";
																					String darkrow = "tbdarkrow";
																					while (splist.next()) {
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
																		<TD align="center"><%=splist.getString("_no")%></TD>
																		<TD align="center"><%=splist.getString("ma")%></TD>
																		<TD align="right"><div align="left"><%=splist.getString("ten")%>
																			</div></TD>
																		<TD align="center"><%=splist.getString("donvi") == null
									? ""
									: splist.getString("donvi")%></TD>
																		<TD align="center"><%=splist.getString("dvkd") == null
									? ""
									: splist.getString("dvkd")%></TD>
																		<%-- <TD align="center"><%= splist.getString("nhanhang") == null ? "" : splist.getString("nhanhang") %></TD>
									<TD align="center"><%= splist.getString("chungloai") == null ? "" : splist.getString("chungloai") %></TD>
									 --%>
																		<%
																			if (splist.getString("trangthai").equals("1")) {
																		%>
																		<TD align="center"><%=Utility.GLanguage("Hoạt động",session,jedis) %></TD>
																		<%
																			} else {
																		%>
																		<TD align="center"><%=Utility.GLanguage("Ngưng hoạt động",session,jedis) %></TD>
																		<%
																			}
																		%>
																		<%-- 	<TD align="right" style="display: none;" ><%= formatter.format(Double.parseDouble(splist.getString("giablc"))) %></TD>
 --%>
																		<TD align="center">
																			<TABLE>
																				<TR>
																					<TD>
																						<%
																							if (quyen[Utility.SUA] != 0) {
																						%> <A
																						href="../../RouterSvl?task=<%= Util.dongMa(getServletContext().getInitParameter("RedirectNoScript") +"ThongtinsanphamUpdateSvl?userId="+userId+"&update="+splist.getString("pk_seq")+"")%>"><img
																							src="../images/Edit20.png" alt="Cap nhat"
																							title="Cập nhật" width="20" height="20"
																							longdesc="Cap nhat" border=0></A>&nbsp; <%
 	}
 %> <%
 	if (quyen[Utility.XEM] != 0) {
 %>
																						<A
																						href="../../RouterSvl?task=<%= Util.dongMa(getServletContext().getInitParameter("RedirectNoScript") +"ThongtinsanphamUpdateSvl?userId="+userId+"&display="+splist.getString("pk_seq")+"")%>"><img
																							src="../images/Display20.png" alt="Hien thi"
																							title="Hiển thị" width="20" height="20"
																							longdesc="Hien thi" border=0></A>&nbsp; <%
 	}
 %> <%
 	if (quyen[Utility.XOA] != 0) {
 %>
																						<A
																						href="../../RouterSvl?task=<%= Util.dongMa(getServletContext().getInitParameter("RedirectNoScript") +"ThongtinsanphamSvl?userId="+userId+"&delete="+splist.getString("pk_seq")+"")%>"><img
																							src="../images/Delete20.png" alt="Xoa"
																							title="Xóa" width="20" height="20" longdesc="Xoa"
																							border=0
																							onclick="if(!confirm('Bạn có muốn xóa sản phẩm này ?')) return false;"></A>
																						<%
																							}
																						%>
																					</TD>
																				</TR>
																			</TABLE>
																	</TR>
																	<%
																		m++;
																					}
																				} catch (Exception e) {
																				}
																			}
																	%>
																</tbody>
																<tfoot>
																	<tr class="tbfooter">
																		<TD align="center" valign="middle" colspan="13"
																			class="tbfooter">
																			<%
																				obj.setNextSplittings();
																			%> <script
																				type="text/javascript" src="../scripts/phanTrang.js"></script>
																			<input type="hidden" name="crrApprSplitting"
																			value="<%=obj.getCrrApprSplitting()%>"> <input
																			type="hidden" name="nxtApprSplitting"
																			value="<%=obj.getNxtApprSplitting()%>"> <%
 	if (obj.getNxtApprSplitting() > 1) {
 %>
																			<img alt="Trang Dau" src="../images/first.gif"
																			style="cursor: pointer;"
																			onclick="View(document.forms[0].name, 1, 'view')">
																			&nbsp; <%
 	} else {
 %> <img alt="Trang Dau"
																			src="../images/first.gif"> &nbsp; <%
 	}
 %> <%
 	if (obj.getNxtApprSplitting() > 1) {
 %>
																			<img alt="Trang Truoc" src="../images/prev.gif"
																			style="cursor: pointer;"
																			onclick="View(document.forms[0].name, eval(document.forms[0].nxtApprSplitting.value) -1, 'view')">
																			&nbsp; <%
 	} else {
 %> <img alt="Trang Truoc"
																			src="../images/prev_d.gif"> &nbsp; <%
 	}
 %> <%
 	int[] listPage = obj.getNextSplittings();
 		for (int i = 0; i < listPage.length; i++) {
 %> <%
 	System.out.println("Current page:"
 					+ obj.getNxtApprSplitting());
 			System.out.println("List page:" + listPage[i]);

 			if (listPage[i] == obj.getNxtApprSplitting()) {
 %> <a
																			style="color: white;"><%=listPage[i]%>/ <%=obj.getTheLastSplitting()%></a>
																			<%
																				} else {
																			%> <a
																			href="javascript:View(document.forms[0].name, <%=listPage[i]%>, 'view')"><%=listPage[i]%></a>
																			<%
																				}
																			%> <input type="hidden" name="list"
																			value="<%=listPage[i]%>" /> &nbsp; <%
 	}
 %> <%
 	if (obj.getNxtApprSplitting() < obj.getTheLastSplitting()) {
 %>
																			&nbsp; <img alt="Trang Tiep" src="../images/next.gif"
																			style="cursor: pointer;"
																			onclick="View(document.forms[0].name, eval(document.forms[0].nxtApprSplitting.value) +1, 'view')">
																			&nbsp; <%
 	} else {
 %> &nbsp; <img alt="Trang Tiep"
																			src="../images/next_d.gif"> &nbsp; <%
 	}
 %> <%
 	if (obj.getNxtApprSplitting() == obj.getTheLastSplitting()) {
 %>
																			<img alt="Trang Cuoi" src="../images/last.gif">
																			&nbsp; <%
 	} else {
 %> <img alt="Trang Cuoi"
																			src="../images/last.gif" style="cursor: pointer;"
																			onclick="View(document.forms[0].name, -1, 'view')">
																			&nbsp; <%
 	}
 %>
																		</TD>
																	</tr>
																<tfoot>
															</TABLE>
														</TD>
													</TR>
												</TABLE>

											</FIELDSET>
										</TD>
									</TR>
								</TABLE>
							</TD>
						</TR>
					</TABLE>
				</TD>
			</TR>
		</TABLE>
	</form>
	<script type="text/javascript" src="../scripts/script-table-sorter.js"></script>
	<script type="text/javascript">
		var sorter = new TINY.table.sorter("sorter");
		sorter.head = "HEAD";
		//sorter.asc = "asc";
		//	sorter.desc = "desc";
		sorter.even = "tblightrow";
		sorter.odd = "tbdarkrow";
		sorter.evensel = "evenselected";
		sorter.oddsel = "oddselected";
		sorter.paginate = true;
		sorter.currentid = "currentpage";
		sorter.limitid = "pagelimit";
		sorter.init("table", 0);
	</script>
</BODY>
</HTML><% geso.dms.center.util.Utility.JedisClose(jedis); %>

<%
	if (splist != null)
			splist.close();
%>
<%
	if (dvkd != null)
			dvkd.close();
%>
<%
	if (nh != null)
			nh.close();
%>
<%
	if (cl != null)
			cl.close();
%>
<%
	obj.DBClose();
%>

<%
	}
%>
