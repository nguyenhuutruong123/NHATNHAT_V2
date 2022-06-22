<%@page import="geso.dms.center.beans.khott.IKhoTT"%>
<%@page import="geso.dms.center.beans.khott.imp.KhoTT"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="geso.dms.center.beans.kho.IKho"%>
<%@ page import="geso.dms.center.beans.kho.IKhoList"%>
<%@ page import="java.util.Iterator"%>
<%@ page import="java.util.List"%>
<%@ page import="java.util.ArrayList"%>
<%@ page import="geso.dms.center.util.*"%>
<%
	String userId = (String) session.getAttribute("userId");  
	String userTen = (String) session.getAttribute("userTen");  	
	String sum = (String) session.getAttribute("sum");
	Utility util = (Utility) session.getAttribute("util");
	String tungay=(String)session.getAttribute("tungay");
	String denngay=(String)session.getAttribute("denngay");
	
	if (!util.check(userId, userTen, sum)){
		response.sendRedirect(request.getContextPath() + "/redirect.jsp");
	} else { 
		int[] quyen = new  int[6];
		quyen = util.Getquyen("KhoTTSvl","",userId);
		String url = util.getUrl("KhoTTSvl", "");
%>

<%
	KhoTT obj = (KhoTT)session.getAttribute("obj");
%>
<%
	List<KhoTT> kholist = (List<KhoTT>)obj.getListKho();
%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"> <% Utility Utilback = new Utility(); %>
<HTML>
<HEAD>
<!-- /* SELECT K.PK_SEQ, K.TEN, K.NGAYTAO, K.NGAYSUA, K.TRANGTHAI, K.MA, NT.TEN
AS NGUOITAO, NS.TEN AS NGUOISUA FROM ERP_KHOTT K INNER JOIN dbo.NHANVIEN
NT ON K.NGUOITAO = NT.PK_SEQ INNER JOIN dbo.NHANVIEN AS NS ON K.NGUOISUA
= NS.PK_SEQ WHERE K.PK_SEQ > 0 [QUERY quyen] SELECT ISNULL(THEM,0) AS
THEM,ISNULL(XOA,0) AS XOA,ISNULL(SUA,0) AS SUA,ISNULL(XEM,0) AS
XEM,ISNULL(CHOT,0) AS CHOT, ISNULL(HUYCHOT,'0') AS HUYCHOT FROM
NHOMQUYEN A INNER JOIN PHANQUYEN B ON A.DMQ_FK = B.DMQ_FK INNER JOIN
UNGDUNG UD ON UD.PK_SEQ=A.UNGDUNG_FK WHERE B.NHANVIEN_FK='100002' AND
UD.SERVLET='KhoTTSvl' AND UD.PARAMETERS='' */ -->

<TITLE><%= getServletContext().getInitParameter("TITLENAME") %></TITLE>
<META http-equiv="Content-Type" content="text/html; charset=utf-8">

<META http-equiv="Content-Style-Type" content="text/css">
<LINK rel="stylesheet" type="text/css" media="print"
	href="../css/impression.css">
<LINK rel="stylesheet" href="../css/main.css" type="text/css">
<LINK rel="stylesheet" href="../css/calendar.css" type="text/css">
<script type="text/javascript" language="JavaScript"
	src="../scripts/simplecalendar.js"></script>
<link type="text/css" rel="stylesheet" href="../css/mybutton.css">
<SCRIPT language="javascript" type="text/javascript">
	function clearform() {
		document.khoForm.ten.value = "";
		//document.khoForm.tungay.value = "";
		//document.khoForm.denngay.value = "";       
		document.khoForm.trangthai.value = '';
		submitform();
	}

	function submitform() {
		document.forms['khoForm'].action.value = 'search';
		document.forms['khoForm'].submit();
	}

	function newform() {
		document.forms['khoForm'].action.value = 'new';
		document.forms['khoForm'].submit();
	}
	function thongbao() {
		var tt = document.forms['khoForm'].msg.value;
		if (tt.length > 0)
			alert(document.forms['khoForm'].msg.value);
	}
</SCRIPT>

</HEAD>
<BODY leftmargin="0" bottommargin="0" topmargin="0" rightmargin="0">
	<form name="khoForm" method="post" action="../../KhoTTSvl"
		charset="UTF-8">
		<INPUT name="userId" type="hidden" value='<%=userId%>' size="30">
		<input type="hidden" name="action" value='1'> <input
			type="hidden" name="msg" value='<%=obj.getMessage()%>'>

		<script language="javascript" type="text/javascript">
			thongbao();
		</script>
		<TABLE width="100%" border="0" cellspacing="0" cellpadding="0"
			height="100%">
			<TR>
				<TD colspan="4" align='left' valign='top' bgcolor="#ffffff">
					<TABLE width="100%" border="0" cellpadding="0" cellspacing="1">
						<TR>
							<TD align="left">
								<table width="100%" border="0" cellpadding="0" cellspacing="0">
									<tr height="22">
										<TD align="left" colspan="2" class="tbnavigation"><%= url %></TD>
										<TD colspan="2" align="right" class="tbnavigation"><%=Utility.GLanguage("Chào mừng bạn",session,jedis) %>  <%=userTen%> &nbsp;</td>
									</tr>

								</table>
							</TD>
						</TR>
					</TABLE>
					<TABLE width="100%" border="0" cellpadding="0" cellspacing="0">
						<TR>
							<TD width="100%" align="left"><FIELDSET>
									<LEGEND class="legendtitle"><%=Utility.GLanguage("Tiêu chí tìm kiếm",session,jedis) %> </LEGEND>

									<TABLE class="tblight" width="100%" cellpadding="6"
										cellspacing="0">
										<TR>
											<TD class="plainlabel" width="19%">Tên kho</TD>
											<TD class="plainlabel"><INPUT name="ten" size="20"
												type="text" value='<%=obj.getTen()%>'
												onChange="submitform();"></TD>
										</TR>
										<TR style="display: none;">
											<TD class="plainlabel"><%=Utility.GLanguage("Từ ngày",session,jedis) %></TD>
											<TD class="plainlabel">
												<TABLE cellpadding="0" cellspacing="0">
													<TR>
														<TD><input type="text" name="tungay" size="20"
															value='<%=tungay%>' onFocus="submitform();"></TD>
													</TR>
												</TABLE>
											</TD>
										</TR>
										<TR style="display: none;">
											<TD class="plainlabel"><%=Utility.GLanguage("Đến ngày",session,jedis) %></TD>
											<TD class="plainlabel">
												<TABLE cellpadding="0" cellspacing="0">
													<TR>
														<TD><input type="text" name="denngay" size="20"
															value='<%=denngay%>' onFocus="submitform();"></TD>
													</TR>
												</TABLE>
											</TD>

										</TR>
										<TR>
											<TD class="plainlabel"><%=Utility.GLanguage("Trạng thái",session,jedis) %></TD>

											<TD class="plainlabel"><select name="trangthai"
												onChange="submitform();">
													<option value="" selected></option>
													<%
														if (obj.getTrangthai().equals("1")){
													%>
													<option value="1" selected><%=Utility.GLanguage("Hoạt động",session,jedis) %></option>
													<option value="0"><%=Utility.GLanguage("Ngưng hoạt động",session,jedis) %></option>
													<%
														}else{
													%>
													<%
														if (obj.getTrangthai().equals("0")){
													%>
													<option value="1"><%=Utility.GLanguage("Hoạt động",session,jedis) %></option>
													<option value="0" selected><%=Utility.GLanguage("Ngưng hoạt động",session,jedis) %></option>
													<%
														}else{
													%>
													<option value="1"><%=Utility.GLanguage("Hoạt động",session,jedis) %></option>
													<option value="0"><%=Utility.GLanguage("Ngưng hoạt động",session,jedis) %></option>
													<%
														}}
													%>
											</select></TD>
										</TR>
										<TR>
											<TD class="plainlabel" colspan="2"><a class="button2"
												href="javascript:clearform()"> <img style="top: -4px;"
													src="../images/button.png" alt=""><%=Utility.GLanguage("Nhập lại",session,jedis) %>
											</a>&nbsp;&nbsp;&nbsp;&nbsp;</TD>
										</TR>
									</TABLE>
								</FIELDSET></TD>
						</TR>
					</TABLE>

					<TABLE width="100%" border="0" cellpadding="0" cellspacing="0">
						<TR>
							<TD align="left">
								<FIELDSET>
									<LEGEND class="legendtitle">
										&nbsp;Danh sách kho &nbsp;&nbsp;
										<%
											if (quyen[Utility.THEM]!=0) {
										%>
										<a class="button3" href="javascript:newform()"> <img
											style="top: -4px;" src="../images/New.png" alt="">Tạo
											mới
										</a>
										<%
											}
										%>
									</LEGEND>

									<TABLE class="" width="100%" border="0" cellpadding="0"
										cellspacing="0">
										<TR>
											<TD width="98%">
												<TABLE width="100%" border="0" cellspacing="1"
													cellpadding="4">
													<TR class="tbheader">
														<TH width="10%">Mã kho</TH>
														<TH width="20%">Tên kho</TH>
														<TH width="12%"><%=Utility.GLanguage("Trạng thái",session,jedis) %></TH>
														<TH width="9%"><%=Utility.GLanguage("Ngày tạo",session,jedis) %></TH>
														<TH width="15%"><%=Utility.GLanguage("Người tạo",session,jedis) %></TH>
														<TH width="9%"><%=Utility.GLanguage("Ngày sửa",session,jedis) %></TH>
														<TH width="14%"><%=Utility.GLanguage("Người sửa",session,jedis) %></TH>
														<TH width="11%"><%=Utility.GLanguage("Tác vụ",session,jedis) %></TH>
													</TR>
													<%
														KhoTT kho = null;
																				int size = kholist.size();
																				int m = 0;
																				String lightrow = "tblightrow";
																				String darkrow = "tbdarkrow";
																				while (m < size){
																					kho = (KhoTT) kholist.get(m);
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

														<TD><%=kho.getDiengiai()%></TD>
														<TD align="left"><%=kho.getTen()%></TD>

														<%
															if (kho.getTrangthai().equals("1")) {
														%>
														<TD align="center"><%=Utility.GLanguage("Hoạt động",session,jedis) %></TD>
														<%
															}else{
														%>
														<TD align="center"><%=Utility.GLanguage("Ngưng hoạt động",session,jedis) %></TD>
														<%
															}
														%>

														<TD align="center"><%=kho.getNgaytao()%></TD>
														<TD align="center"><%=kho.getNguoitao()%></TD>
														<TD align="center"><%=kho.getNgaysua()%></TD>
														<TD align="center"><%=kho.getNguoisua()%></TD>
														<TD align="center">
															<TABLE border=0 cellpadding="0" cellspacing="0">
																<TR>
																	<%
																		if (quyen[Utility.SUA]!=0) {
																	%>
																	<TD><A
																		href="../../KhoTTSvl?userId=<%=userId%>&update=<%=kho.getId()%>"><img
																			src="../images/Edit20.png" alt="Chinh sua"
																			title="Chỉnh sửa" width="20" height="20"
																			longdesc="Chinh sua" border=0></A></TD>
																	<%
																		}
																	%>
																	<TD>&nbsp;</TD>
																	<%
																		if (quyen[Utility.XOA]!=0) {
																	%>
																	<TD><A
																		href="../../KhoTTSvl?userId=<%=userId%>&delete=<%=kho.getId()%>"><img
																			src="../images/Delete20.png" alt="Xoa" title="Xóa"
																			width="20" height="20" longdesc="Xoa" border=0
																			onclick="if (!confirm('Bạn Có Muốn Xóa Kho Này?')) return false;">
																	</A></TD>
																	<%
																		}
																	%>
																</TR>
															</TABLE>
														</TD>
													</TR>
													<%
														m++; }
													%>


													<TR>
														<TD align="center" colspan="11" class="tbfooter">&nbsp;</TD>
													</TR>
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
	</form>
</BODY>
</HTML>
<%
	obj.DBClose();
}
%>