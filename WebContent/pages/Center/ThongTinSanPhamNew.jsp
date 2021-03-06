<%@ page language="java" contentType="text/html; charset=UTF-8"
pageEncoding="UTF-8"%>
<%@ page
import="geso.dms.center.beans.thongtinsanpham.IThongtinsanpham"%>
<%@ page import="java.util.Hashtable"%>
<%@ page import="java.sql.ResultSet"%>
<%@ page import="geso.dms.center.util.*"%>
<%@page import="java.text.DecimalFormat"%>
<%@page import="java.text.NumberFormat"%>

<%
String userId = (String) session.getAttribute("userId");
String userTen = (String) session.getAttribute("userTen");
String sum = (String) session.getAttribute("sum");
Utility util = (Utility) session.getAttribute("util");
if (!util.check(userId, userTen, sum))
{
	response.sendRedirect("/ORGALIFE/index.jsp");
} 
else 
{
	IThongtinsanpham spBean = (IThongtinsanpham) session.getAttribute("spBean");
	NumberFormat formatter = new DecimalFormat("#,###,###");
	ResultSet dvdl = (ResultSet) spBean.getDvdl();
	ResultSet dvkd = (ResultSet) spBean.getDvkd();
	ResultSet nh = (ResultSet) spBean.getNh();
	ResultSet cl = (ResultSet) spBean.getCl();
	ResultSet nsp = (ResultSet) spBean.getNsp();
	ResultSet nganhhang = (ResultSet) spBean.getRsNganhHang();
	ResultSet splist = (ResultSet) spBean.getSanphamRs();
	String dvdlId = (String) spBean.getDvdlId();
	String dvkdId = (String) spBean.getDvkdId();
	String nhId = (String) spBean.getNhId();
	String clId = (String) spBean.getClId();
	Hashtable nspIds = spBean.getNspIds();
	Hashtable<Integer, String> spIds = spBean.getSpIds();
	ResultSet nccRs = (ResultSet) spBean.getNccRs();
	ResultSet rspacksize = (ResultSet) spBean.getPacksizeRs();
	ResultSet nhomhangRs = (ResultSet) spBean.getNhomHangRs();
	String url = util.getChuyenNguUrl("ThongtinsanphamSvl", "",session);
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"> <% Utility Utilback = new Utility(); %>
<HTML>
<HEAD>
<TITLE><%= getServletContext().getInitParameter("TITLENAME") %></TITLE>
<META http-equiv="Content-Type" content="text/html; charset=utf-8">

<META http-equiv="Content-Style-Type" content="text/css">

<LINK rel="stylesheet" href="../css/main.css" type="text/css">
<script type="text/javascript" src="../scripts/jquery.min.1.7.js"></script>
<script type="text/javascript" src="../scripts/dropdowncontent.js"></script>

<SCRIPT language="javascript" type="text/javascript">
function submitform()
{	
	document.spForm.setAttribute('enctype', "multipart/form-data", 0);	
	document.spForm.action.value='abc';
	document.forms["spForm"].submit();
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

function saveform()
{
	 var type = document.getElementById("type").value;
	 /* var kl = document.spForm.kl.value;
	 var tt = document.spForm.tt.value;
	 if(isNaN(kl) && kl.length > 0)
		 alert("khoi luong phai nhap so");
	 if(isNaN(tt) && tt.length > 0)
		 alert("the tich phai nhap so"); */
	 if(type == 1)
	 {
		if(checkBundle() == false)
		{
			alert('Ban phai chon san pham cho Bundle');
			return;
		}
	 }
	 document.spForm.setAttribute('enctype', "multipart/form-data", 0);	
	 document.spForm.action.value='save';
	 document.forms["spForm"].submit();
}

function checkBundle()
{
	 var spIds = document.getElementsByName("spIds");
	 if(spIds != null)
	 {
		 for(var i = 0; i < spIds.length; i++)
			 if(spIds.item(i).checked)
				 return true;
	 }
	 return false;
}
function keypress(e) //H??m d??ng d? ngan ngu?i d??ng nh?p c??c k?? t? kh??c k?? t? s? v??o TextBox
{
	var keypressed = null;
	if (window.event)
		keypressed = window.event.keyCode; //IE
	else
		keypressed = e.which; //NON-IE, Standard
	
	if (keypressed < 48 || keypressed > 57)
	{
		if (keypressed == 8 || keypressed == 127 || keypressed == 37 || keypressed == 39 || keypressed == 0 || keypressed == 46)
		{//Ph??m Delete v?? Ph??m Back
			return;
		}
		return false;
	}
}
function chonSp(mm)
{
	 var spIds = document.getElementById("spIds" + mm);
	 var spSoluong = document.getElementById("spSoLuong" + mm);
	 if(spIds != null)
	 {
		 if(spIds.checked)
		 {
			 if(spSoluong.value == '')
			 {
				 spIds.checked = false;
				 alert('Ban phai nhap so luong san pham nay');
				 return;
			 }
			 else
			 {
				 spIds.value = spIds.value + '-' + spSoluong.value;
			 }
		 }
	 }
	 return false;
}

function updateUoM()
{
 	var dvdlId = document.getElementById("dvdlId").value;
	var dvdl1 = document.getElementsByName("dvdl1");
  	for(var i=0; i<dvdl1.length; i++)
 	{
  		dvdl1.item(i).value = dvdlId;
 	} 
}

function setThungdautien()
{
 	var dvdl_chuan = document.getElementById("0").value;
	var dvdl2 = document.getElementsByName("dvdl2");
	dvdl2.item(0).value = 100018;

}

function sellectAll_NhId()
{
	 var chkAll_Lct = document.getElementById("chkAll_NhId");
	 var loaiCtIds = document.getElementsByName("nhanhangIds");
	
	 if(chkAll_Lct.checked)
	 {
		 for(var i = 0; i < loaiCtIds.length; i++)
		 {
			 loaiCtIds.item(i).checked = true;
		 }
	 }
	 else
	 {
		 for(var i = 0; i < loaiCtIds.length; i++)
		 {
			 loaiCtIds.item(i).checked = false;
		 }
	 }
}

function sellectAll_ClId()
{
	 var chkAll_Lct = document.getElementById("chkAll_ClId");
	 var loaiCtIds = document.getElementsByName("chungloaiIds");
	
	 if(chkAll_Lct.checked)
	 {
		 for(var i = 0; i < loaiCtIds.length; i++)
		 {
			 loaiCtIds.item(i).checked = true;
		 }
	 }
	 else
	 {
		 for(var i = 0; i < loaiCtIds.length; i++)
		 {
			 loaiCtIds.item(i).checked = false;
		 }
	 }
}
</SCRIPT>
</HEAD>
<BODY leftmargin="0" bottommargin="0" topmargin="0" rightmargin="0"  >
<form name="spForm" method="post" action="../../ThongtinsanphamUpdateSvl">
<% redis.clients.jedis.Jedis jedis =  geso.dms.center.util.Utility.getJedis(); %>
<% geso.dms.center.util.Csrf csdr=new geso.dms.center.util.Csrf(request,response,true,false,true);%>
 <input type="hidden" name="<%=csdr.get_tokenName() %>" value='<%=csdr.get_tokenValue() %>'>

	<input type="hidden" name="userId" value='<%=spBean.getUserId()%>'>
	<input type="hidden" name="action" value="1">

	<TABLE width="100%" border="0" cellspacing="0" cellpadding="0"
		height="100%">
		<TR>
			<TD colspan="4" align='left' valign='top' bgcolor="#FFFFFF">
				<TABLE width="100%" border="0" cellpadding="0" cellspacing="1">
					<TR>
						<TD align="left" class="tbnavigation">
							<table width="100%" border="0" cellpadding="0" cellspacing="0">
								<tr height="22">
									<TD align="left" colspan="2" class="tbnavigation">
										&nbsp;<%= url %> > <%=Utility.GLanguage("T???o m???i",session,jedis) %>
									<TD colspan="2" align="right" class="tbnavigation"><%=Utility.GLanguage("Ch??o m???ng b???n",session,jedis) %> <%=userTen%>&nbsp;
									</TD>
								</tr>
							</table>
						</TD>
					</TR>
				</TABLE>
				<TABLE width="100%" border="0" cellpadding="0" cellspacing="1">
					<TR>
						<TD>
							<TABLE width="100%" border="0" cellpadding="0" cellspacing="0">
								<TR class="tbdarkrow">
									<TD width="30" align="left"><A
										href="../../RouterSvl?task=<%= Utilback.dongMa(getServletContext().getInitParameter("RedirectNoScript") +"ThongtinsanphamSvl?userId="+userId+"") %>"><img
											src="../images/Back30.png" alt="Quay ve" title="Quay ve"
											border="1" longdesc="Quay ve" style="border-style: outset"></A></TD>
									<TD width="2" align="left"></TD>
									<TD width="30" align="left"><A
										href="javascript: saveform()"><IMG
											src="../images/Save30.png" title="Luu lai" alt="Luu lai"
											border="1" style="border-style: outset"></A></TD>
									<TD>&nbsp;</TD>
								</TR>
							</TABLE>
						</TD>
					</TR>
				</TABLE>

				<TABLE width="100%" border="0" cellpadding="0" cellspacing="1">
					<tr>
						<TD align="left" colspan="4" class="legendtitle">
							<FIELDSET>
								<LEGEND class="legendtitle"><%=Utility.GLanguage("B??o l???i nh???p li???u",session,jedis) %></LEGEND>

								<textarea name="dataerror" style="width: 100%" readonly
									rows="1"><%=spBean.getMessage()%></textarea>
								<%
									spBean.setMessage("");
								%>
							</FIELDSET>
						</TD>
					</tr>

					<TR>
						<TD>

							<TABLE width="100%" border="0" cellpadding="0" cellspacing="1">
								<TR>
									<TD>
										<FIELDSET>
											<LEGEND class="legendtitle" style="color: black"><%=Utility.GLanguage("Th??ng tin s???n ph???m",session,jedis) %></LEGEND>

											<TABLE border="0" width="100%" cellpadding="4"
												cellspacing="0">
												<TR>
													<TD class="plainlabel"><%=Utility.GLanguage("M??",session,jedis) %><FONT class="erroralert"> *</FONT></TD>
													<TD class="plainlabel" ><INPUT type="text" name="masp" style="width: 200px" value='<%=spBean.getMasp()%>'></TD>
													<TD class="plainlabel"><%=Utility.GLanguage("T???n kho an to??n",session,jedis) %></TD>
														<TD class="plainlabel" ><INPUT type="number" name="tontoithieu" style="width: 200px; text-align: right;" value='<%=spBean.getTontoithieu()%>'></TD>
														
													
												</TR>


												<TR style="display: none">
													<TD class="plainlabel"><%=Utility.GLanguage("M?? chu???n",session,jedis) %><FONT
														class="erroralert">*</FONT></TD>
													<TD colspan="3" class="plainlabel"><input
														name="machuan" type="text" style="width: 200px"
														value='<%=spBean.getMachuan()%>'></TD>
												</TR>
												<TR style="display: none">
													<TD class="plainlabel"><%=Utility.GLanguage("T??n chu???n",session,jedis) %><FONT
														class="erroralert">*</FONT></TD>
													<TD colspan="3" class="plainlabel"><input
														name="tenchuan" type="text" style="width: 200px"
														value='<%=spBean.getTenchuan()%>'></TD>
												</TR>

												<TR>
													<TD class="plainlabel"><%=Utility.GLanguage("T??n",session,jedis) %><FONT class="erroralert">*</FONT></TD>
													<TD class="plainlabel"><input name="tensp" type="text" style="width: 200px" value='<%=spBean.getTen()%>'></TD>
													
													<TD class="plainlabel"><%=Utility.GLanguage("Nh?? cung c???p",session,jedis) %><FONT class="erroralert">*</FONT></TD>
														<TD class="plainlabel">
															<select name="nccId">

																	<option value=""></option>
																	<%
																		try
																			{
																				while (nccRs.next())
																				{
																					if (spBean.getNccId() != null && spBean.getNccId().equals(nccRs.getString("pk_seq")))
																					{
																	%>
																	<option value='<%=nccRs.getString("pk_seq")%>' selected><%=nccRs.getString("diengiai")%></option>
																	<%
																		} else
																					{
																	%>
																	<option value='<%=nccRs.getString("pk_seq")%>'><%=nccRs.getString("diengiai")%></option>
																	<%
																		}

																				}
																				nccRs.close();
																			} catch (java.sql.SQLException e)
																			{
																			}
																	%>

															</select>
														</TD>
													
													
												<%-- 	<TD class="plainlabel">Th??? t??ch <FONT class="erroralert"> *</FONT></TD>
													<TD class="plainlabel"><INPUT type="text" name="tt" style="width: 200px; text-align: right;" value='<%=spBean.getTT()%>' onkeypress="return keypress(event);"> &nbsp; <b><i>cm<sup>3</sup></i></b>
													</TD> --%>
												</TR>
												<TR>
													<TD class="plainlabel"><%=Utility.GLanguage("????n vi ??o l?????ng",session,jedis) %><FONT class="erroralert"> *</FONT></TD>
													<TD class="plainlabel"><select name="dvdlId" id="dvdlId" onChange="updateUoM();">
														<option value=""></option>
															<%
																try {
																		dvdl.beforeFirst();
																		while (dvdl.next())
																		{
																			if (dvdlId.equals(dvdl.getString("dvdlId")))
																			{
															%>
															<option value='<%=dvdl.getString("dvdlId")%>' selected><%=dvdl.getString("dvdlTen")%></option>
															<%
																} else {
															%>
															<option value='<%=dvdl.getString("dvdlId")%>'><%=dvdl.getString("dvdlTen")%></option>
															<%
																} }
															}
															catch (Exception e){ e.printStackTrace(); }
															%>

													</select>

												</TD>
													<TD class="plainlabel"><%=Utility.GLanguage("Tu???i th???",session,jedis) %><FONT class="erroralert"> *</FONT></TD>
													<TD class="plainlabel">
														<INPUT type="text" name="hansudung" style="width: 200px; text-align: right;" value='<%=spBean.getHansudung()%>' onkeypress="return keypress(event);"> &nbsp;<i>th??ng</i>
													</TD>
												</TR>

												<tr>
													<%-- <TD class="plainlabel"> ????n v??? ??o l?????ng ETC  <FONT class="erroralert"> *</FONT></TD>
													<TD class="plainlabel">
														<select name="dvdlETCId" id="dvdlETCId">
															<option value=""></option>
															<%
																try {
																	 dvdl.beforeFirst();
																	while (dvdl.next())
																	{
																		if (spBean.getDvdlETCId().equals(dvdl.getString("dvdlId")))
																		{
															%>
															<option value='<%=dvdl.getString("dvdlId")%>' selected><%=dvdl.getString("dvdlTen")%></option>
															<%
																} else {
															%>
															<option value='<%=dvdl.getString("dvdlId")%>'><%=dvdl.getString("dvdlTen")%></option>
															<%
																} }
															}
															catch (Exception e){ e.printStackTrace(); }
															%>
													</select>
													</TD> --%>
													<TD class="plainlabel"><%=Utility.GLanguage("????n v??? kinh doanh",session,jedis) %><FONT class="erroralert">*</FONT></TD>
													<TD class="plainlabel" >
														<select name="dvkdId" onChange="submitform();">
															<option value=""></option>
															<%
																try
																	{
																	while (dvkd.next())
																	{
																		if (dvkdId.equals(dvkd.getString("dvkdId")))
																		{
															%>
															<option value='<%=dvkd.getString("dvkdId")%>' selected><%=dvkd.getString("dvkdTen")%></option>
															<%
																} else
																																	{
															%>
															<option value='<%=dvkd.getString("dvkdId")%>'><%=dvkd.getString("dvkdTen")%></option>
															<%
																}
																}
																dvkd.close();
																} catch (java.sql.SQLException e){}
															%>
													</select>
													</TD>
													<TD class="plainlabel"><%=Utility.GLanguage("Thu??? su???t",session,jedis) %> ( <%=Utility.GLanguage("??p d???ng b???ng gi?? tr?????c VAT",session,jedis) %> ) <FONT class="erroralert"> *</FONT></TD>
														<TD class="plainlabel"style="width: 200px;"><input
															style="text-align: right; width: 60%;" type="text"
															name="pt_vat" 
															value='<%=formatter.format(spBean.getPt_vat())%>'onkeypress="return keypress(event);"onChange="Dinhdang(this);" />
														</TD>
												</tr>

												<tr>
														<TD class="plainlabel"><%=Utility.GLanguage("T??n vi???t t???t",session,jedis) %></TD>
														<TD class="plainlabel"><INPUT type="text" name="tenviettat" style="width: 200px; text-align: right;" value='<%=spBean.getTenviettat()%>'></TD>
														<TD class="plainlabel" ></TD>
														<TD class="plainlabel" ></TD>
														
														<TD class="plainlabel" style="display: none;"><%=Utility.GLanguage("Thu??? su???t",session,jedis) %> (<%=Utility.GLanguage("??p d???ng b???ng gi?? sau VAT",session,jedis) %>) <FONT class="erroralert"> *</FONT></TD>
														<TD class="plainlabel"style="width: 200px; display: none;" ><input
															style="text-align: right; width: 60%;" type="text"
															name="pt_vat_2" 
															value='<%=formatter.format(spBean.getPt_vat_2())%>'onkeypress="return keypress(event);"onChange="Dinhdang(this);" />
														</TD>
													</tr>
													
													<%-- <tr>
														<TD class="plainlabel">D???ng b??o ch???</TD>
														<TD class="plainlabel"><INPUT type="text" name="dangbaoche" style="width: 200px; text-align: right;" value='<%=spBean.getDangbaoche()%>'></TD>
														<TD class="plainlabel">H??m l?????ng </TD>
														<TD class="plainlabel"><INPUT type="text" name="hamluong" style="width: 200px; text-align: right;" value='<%=spBean.getHamluong()%>'></TD>
													
													</tr> --%>


												<tr>
														<TD class="plainlabel"><%=Utility.GLanguage("Nh??n h??ng",session,jedis) %></TD>
														<TD class="plainlabel"><select name="nhId" id="nhId" onChange="submitform();" >
																<option value=""></option>
																<%
																	try {
																		nh.beforeFirst();
																	
																		while (nh.next())
																		{
																			if (spBean.getNhId().equals(nh.getString("pk_seq")))
																			{ %>
																			
																		<option value='<%=nh.getString("pk_seq")%>' selected><%=nh.getString("diengiai")%></option>
																	<% } else { %>
																		<option value='<%=nh.getString("pk_seq")%>'><%=nh.getString("diengiai")%></option>
																	<% } } 
																	} 
																	catch (Exception e){ 
																		System.out.print("loi ?? dya");
																		
																		e.printStackTrace(); }
																%>
															</select></TD>
														<TD class="plainlabel"><%=Utility.GLanguage("Ch???ng lo???i",session,jedis) %></TD>
														<TD class="plainlabel"><select name="clId" >
														<option value=""></option>
																<%
																	try
																		{
																			cl.beforeFirst();
																			
																			while (cl.next())
																			{
																				if (spBean.getClId().equals(cl.getString("pk_seq")))
																				{
																%>
																<option value='<%=cl.getString("pk_seq")%>' selected><%=cl.getString("diengiai")%></option>
																<%
																	} else
																				{
																%>
																<option value='<%=cl.getString("pk_seq")%>'><%=cl.getString("diengiai")%></option>
																<%
																	}
																			}
																			cl.close();
																		} catch (java.sql.SQLException e)
																		{
																		}
																%>


															</select>
															
														</TD>
													</tr>



												<TR>
													<TD class="plainlabel"><%=Utility.GLanguage("Ng??nh h??ng",session,jedis) %><FONT class="erroralert">*</FONT></TD>
													<TD class="plainlabel">
														<%
															if (dvkdId.length() > 0)
																{
														%> <select name="nganhhangid">
															<%
																} else
																	{
															%>
															<select name="nganhhangid" >
																<%
																	}
																%>

																<option value=""></option>
																<%
																	try
																		{
																			while (nganhhang.next())
																			{
																				if (spBean.getNganhhangid().equals(nganhhang.getString("pk_seq")))
																				{
																%>
																<option value='<%=nganhhang.getString("pk_seq")%>' selected><%=nganhhang.getString("ten")%></option>
																<%
																	} else
																				{
																%>
																<option value='<%=nganhhang.getString("pk_seq")%>'><%=nganhhang.getString("ten")%></option>
																<%
																	}

																			}
																			nganhhang.close();
																		} catch (java.sql.SQLException e)
																		{
																		}
																%>

														</select>

													<TD class="plainlabel" ><%=Utility.GLanguage("Ho???t ?????ng",session,jedis) %>&nbsp; <%
														if (spBean.getTrangthai().equals("1"))
														{
															 %> <input name="trangthai" type="checkbox" value="1" checked> <%
														} else
														{
															%> <input name="trangthai" type="checkbox" value="0">
														<%
															}
														%>
													</TD>
													<TD class="plainlabel" >
														<%=Utility.GLanguage("S???n ph???m khuy???n m??i",session,jedis) %> &nbsp; <%
														 if (spBean.getIsKm().equals("1"))
														 {
														 	%> <input name="isKm" type="checkbox" value="1" checked> <%
														 } else
														 {
														 	%> <input name="isKm" type="checkbox" value="0"> <%
														 }
														 %>
													</TD>		
												</TR>
														
												<TR>
									  				<TD class="plainlabel"><%=Utility.GLanguage("H??nh ???nh",session,jedis) %></TD>
													<TD colspan = "3" class="plainlabel" >
														<INPUT type="file" name="filename" value="">
											  	  		<input type="hidden" id="valueten" name="hinhanh" value="<%= spBean.getHinhanh() %>">
											  	  		<% if(spBean.getHinhanh().length() > 0 ) {	%>
											  	  			<div id="tenfilea"><p><%= spBean.getHinhanh() %><img src="../images/Delete20.png" onclick="Xoafile()" style="cursor: pointer;" alt="X??a" width="20" height="20" longdesc="Cap nhat" border = 0></p></div>
											  	  			
			                        						</div>
											  	  		<%} %>
													</TD>
												</TR>
													
												<TR>
													<TD style="display: none" class="plainlabel" ><%=Utility.GLanguage("S???n ph???m m???i",session,jedis) %> &nbsp; <%
														if (spBean.getSpMoi().equals("1"))
														{
															 %> <input name="spmoi" type="checkbox" value="1" checked> <%
														} else
														{
															%> <input name="spmoi" type="checkbox" value="0">
														<%
															}
														%>
													</TD>
														
													<TD style="display: none" class="plainlabel" colspan="2"><%=Utility.GLanguage("S???n ph???m ch??? l???c",session,jedis) %> &nbsp; <%
														if (spBean.getSpChuLuc().equals("1"))
														{
															 %> <input name="spchuluc" type="checkbox" value="1" checked> <%
														} else
														{
															%> <input name="spchuluc" type="checkbox" value="0">
														<%
															}
														%>
													</TD>
												</TR>
												
												<TR style="display: none">
													<TD class="plainlabel">Packsize<FONT class="erroralert"> *</FONT></TD>
													<TD colspan="3" class="plainlabel">
													<select name="packsizeid">
														<option value=""></option>
														<% try { while (rspacksize.next())
														{ if (spBean.getPacksizeId().equals(rspacksize.getString("pk_seq"))) { %>
															<option value='<%=rspacksize.getString("pk_seq")%>' selected><%=rspacksize.getString("ten")%></option>
														<% } else { %>
															<option value='<%=rspacksize.getString("pk_seq")%>'><%=rspacksize.getString("ten")%></option>
														<% } } rspacksize.close(); 
														} catch (java.sql.SQLException e){} %>
													</select></TD>
												</TR>

												<TR style="display: none;">
													<TD class="plainlabel">Gi?? b??n l??? chu???n <FONT class="erroralert"> *</FONT></TD>
													<TD colspan="3" class="plainlabel" align="left">
														<%
															String gia = "";
															if (spBean.getGiablc().trim().length() != 0)
															{ gia = formatter.format(Double.parseDouble(spBean.getGiablc())); } 
															else { gia = spBean.getGiablc(); } %> 
														<input name="giablc" type="text" size="10" style="text-align: right;" value='<%=gia%>' onkeyup="Dinhdang(this)" onkeypress="return keypress(event);">
														&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
														<% if (spBean.getType().equals("1")) { %> 
														<input name="type" type="checkbox" id="type" value="1" checked="checked" onChange="submitform();"> is Bundle 
														<% } else { %> 
														<input name="type" type="checkbox" id="type" value="0" onChange="submitform();"> is Bundle <% } %>
													</TD>
												</TR>
												
												<tr style="display: none;">
													<TD class="plainlabel">S??? th??ng quy ?????i d??nh cho th?????ng <FONT class="erroralert"> *</FONT> </TD>
													<TD colspan="2" class="plainlabel"><INPUT type="text" name="quydoithuong" style="width: 150px; text-align: right;" value='<%=spBean.getquydoithuong()%>' onkeypress="return keypress(event);"> &nbsp; <b><i></sup></i></b></TD>
												</tr>
											</TABLE>
										</FIELDSET> <%
										
										if(spBean.getType().equals("1")){ %>
										<fieldset>
											<legend class="legendtitle"> Ti??u ch?? l???c s???n ph???m bundle </legend>
											<table style="width: 100%" cellpadding="4" cellspacing="1">
												<tr class="tbheader">
													<TD width="10%" class="plainlabel">Ch???ng lo???i</TD>
													<TD width="90%" valign="middle" class="plainlabel"><a
														href="" id="clId" rel="subcontentClId"> &nbsp; <img alt="Lo???i s???n ph???m" src="../images/vitriluu.png"></a>
														<DIV id="subcontentClId" style="position: absolute; visibility: hidden; border: 9px solid #80CB9B; background-color: white; width: 590px; height: 300px; overflow: auto; padding: 4px;">
															<table width="90%" align="center">
																<tr class="tbheader">
																	<th width="350px">Ch???ng lo???i</th>
																	<th width="100px" align="center">Ch???n h???t <input type="checkbox" onchange="sellectAll_ClId()" id="chkAll_ClId"></th>
																</tr>
																<%
																	if (cl != null)
																	{
																		cl.beforeFirst();
																		int jj=0;
																		while (cl.next())
																		{ if(jj %2 ==0){ %>
																<tr class="tblightrow">
															<% }  else { %>
																<tr class="tbdarkrow">
															<% } %>
																	<td><%=cl.getString("clTen")%></td>
																	<td>
																	<% if (spBean.getChungloaiIds().indexOf(cl.getString("clId")) >= 0) { %> 
																		<input type="checkbox" name="chungloaiIds" checked="checked" value="<%=cl.getString("clId")%>" />
																		<% } else { %> 
																		<input type="checkbox" name="chungloaiIds" value="<%=cl.getString("clId")%>" /> <% } %>
																	</td>
																</tr>
																<% jj++; } 
																cl.close(); %>
																<% } %>
															</table>
															<div align="right">
																<label style="color: red"></label>
																&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; <a
																	href="javascript:dropdowncontent.hidediv('subcontentClId');submitform();">Ho??n t???t</a>
															</div>

														</DIV></TD>
												</TR>

												<tr class="tbheader">
													<TD class="plainlabel">Nh??n h??ng</TD>
													<TD class="plainlabel" valign="middle"><a href=""
														id="nhId" rel="subcontentNhIds"> &nbsp; <img
															alt="Nh??n h??ng" src="../images/vitriluu.png"></a>
														<DIV id="subcontentNhIds"
															style="position: absolute; visibility: hidden; border: 9px solid #80CB9B; background-color: white; width: 590px; height: 300px; overflow: auto; padding: 4px;">
															<table width="90%" align="center">
																<tr class="tbheader">
																	<th width="350px">Nh??n h??ng</th>
																	<th width="100px" align="center">Ch???n h???t <input
																		type="checkbox" onchange="sellectAll_NhId()"
																		id="chkAll_NhId"></th>
																</tr>
																<%
																	if (nh != null)
																																	{
																																		nh.beforeFirst();
																																		int jj=0;
																																		while (nh.next())
																																		{
																																			if(jj %2 ==0){
																%>
																<tr class="tblightrow">
																	<%
																		}  else {
																	%>

																<tr class="tbdarkrow">
																	<%
																		}
																	%>
																	<td><%=nh.getString("nhTen")%></td>
																	<td>
																		<%
																			if (spBean.getNhanhangIds().indexOf(nh.getString("nhId")) >= 0)
																																			{
																		%> <input type="checkbox" name="nhanhangIds"
																		checked="checked" value="<%=nh.getString("nhId")%>" />
																		<%
																			} else
																						{
																		%> <input type="checkbox" name="nhanhangIds"
																		value="<%=nh.getString("nhId")%>" /> <%
}
%>
																	</td>
																</tr>
																<%
																	jj++;
																																}
																nh.close();
																%>
																<%
																	}
																%>
															</table>
															<div align="right">
																<label style="color: red"></label>
																&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; <a
																	href="javascript:dropdowncontent.hidediv('subcontentNhIds');submitform();">Ho??n
																	t???t</a>
															</div>

														</DIV></TD>
												</TR>

											</table>
										</fieldset> <%
										}
										if (splist != null)
											{
												int mm = 0;
										%>
										<fieldset>
											<legend class="legendtitle"> Ch???n s???n ph???m thu???c
												Bundle </legend>
											<table style="width: 100%" cellpadding="4" cellspacing="1">
												<tr class="tbheader">
													<th align="center">M?? s???n ph???m</th>
													<th align="center">T??n s???n ph???m</th>
													<th align="center" style="width: 80px">S??? l?????ng</th>
													<th align="center">Ch???n</th>
												</tr>
												<%
													while (splist.next())
													{
													if (mm % 2 == 0)
														{
												%>
												<tr class="tblightrow">
													<%
														} else
															{
													%>

												<tr class="tbdarkrow">
													<%
														}
													%>
													<td><%=splist.getString("spMa")%></td>
													<td><%=splist.getString("spTen")%></td>
													<td><input type="text"
														id="spSoLuong<%=Integer.toString(mm)%>"
														style="width: 100%; text-align: right;"></td>
													<td align="center">
														<%
															if (spIds.contains(splist.getString("pk_seq")))
																															{
														%> <input type="checkbox" name="spIds"
														id="spIds<%=Integer.toString(mm)%>"
														value='<%=splist.getString("pk_seq")%>' checked="checked"
														onchange="chonSp(<%=mm%>)"> <%
													} else
																{
													%> <input type="checkbox" name="spIds"
														id="spIds<%=Integer.toString(mm)%>"
														value='<%=splist.getString("pk_seq")%>'
														onchange="chonSp(<%=mm%>)"> <%
													}
													%>
													</td>
												</tr>
												<%
													mm++;
																										}
												%>
											</table>
										</fieldset> <%
}
%>


										<TABLE width="100%" border="0" cellpadding="0"
											cellspacing="0">
											<TR>
												<TD>
													<FIELDSET>
														<LEGEND class="legendtitle" style="color: black"><%=Utility.GLanguage("Nh??m s???n ph???m",session,jedis) %></LEGEND>
														<TABLE border="0" width="100%" cellpadding="4"
															cellspacing="1">
															<TR class="tbheader">
																<TH width="30%"><%=Utility.GLanguage("T??n",session,jedis) %></TH>
																<TH width="60%"><%=Utility.GLanguage("Di???n gi???i",session,jedis) %></TH>
																<TH width="10%"><%=Utility.GLanguage("Ch???n",session,jedis) %></TH>
															</TR>
															<%
												try
												{
													String lightrow = "tblightrow";
													String darkrow = "tbdarkrow";
													int m = 0;
													if (!(nhomhangRs == null))
													{
														while (nhomhangRs.next())
														{
															if (m % 2 != 0)
															{
															%>
															<TR class=<%=lightrow%>>
																<%
																	} else
																																				{
																%>

															<TR class=<%=darkrow%>>
																<%
																	}
																%>

																<TD><%=nhomhangRs.getString("ma")%></TD>
																<TD><div align="left"><%=nhomhangRs.getString("ten")%>
																	</div></TD>
																<TD>
																	<div align="center">
																		<%
																		if (spBean.getNhomHangId().indexOf(nhomhangRs.getString("pk_Seq")) >=0  ) {%>
																		<input type="checkbox" name="nhomhangId" value='<%=nhomhangRs.getString("pk_Seq")%>' checked>
																		<%
																			} else
																																								{
																		%>
																		<input type="checkbox" name="nhomhangId" value='<%=nhomhangRs.getString("pk_Seq")%>'>
																		<%
																			}
																		%>

																	</div>
																</TD>
															</TR>
															<%
																m++;
																		}
														nhomhangRs.close();
																	}
																} catch (java.sql.SQLException e)
																{
																	e.printStackTrace();
																}
															%>

														</TABLE>
													</FIELDSET>
												</TD>
											</TR>
										</TABLE>


										<%-- <TABLE width="100%" border="0" cellpadding="0"
											cellspacing="0">
											<TR>
												<TD>
													<FIELDSET>
														<LEGEND class="legendtitle" style="color: black">Ch???n
															nh??m s???n ph???m</LEGEND>
														<TABLE border="0" width="100%" cellpadding="4"
															cellspacing="1">
															<TR class="tbheader">
																<TH width="30%">Nh??m s???n ph???m</TH>
																<TH width="60%"><%=Utility.GLanguage("Di???n gi???i",session,jedis) %></TH>
																<TH width="10%">Ch???n</TH>
															</TR>
															<%
																try
															{
																String lightrow = "tblightrow";
																String darkrow = "tbdarkrow";
																int m = 0;
																if (!(nsp == null))
																{
																	while (nsp.next())
																	{
																		if (m % 2 != 0)
																		{
															%>
															<TR class=<%=lightrow%>>
																<%
																	} else
																																				{
																%>

															<TR class=<%=darkrow%>>
																<%
																	}
																%>

																<TD><%=nsp.getString("nspTen")%></TD>
																<TD><div align="left"><%=nsp.getString("diengiai")%>
																	</div></TD>
																<TD>
																	<div align="center">
																		<%
																			if (nspIds.contains(nsp.getString("nspId")))
																																								{
																		%>
																		<input type="checkbox" name="nspIds"
																			value='<%=nsp.getString("nspId")%>' checked>
																		<%
																			} else
																																								{
																		%>
																		<input type="checkbox" name="nspIds"
																			value='<%=nsp.getString("nspId")%>'>
																		<%
																			}
																		%>

																	</div>
																</TD>
															</TR>
															<%
															m++;
																			}
																			nsp.close();
																		}
																	} catch (java.sql.SQLException e)
																	{
																	}
															%>

														</TABLE>
													</FIELDSET>
												</TD>
											</TR>
										</TABLE> --%>

										<TABLE width="100%" cellpadding="0" cellspacing="0">
											<TR>
												<TD>
													<FIELDSET>
														<LEGEND class="legendtitle"> &nbsp;<%=Utility.GLanguage("Quy c??ch",session,jedis) %><FONT class="erroralert"> *</FONT>  </LEGEND>
														<TABLE border="1" cellpadding="0" cellspacing="1"
															width="100%">
															<TR class="tbheader">
																<TH width="21%"><%=Utility.GLanguage("S??? l?????ng",session,jedis) %></TH>
																	<TH width="21%"><%=Utility.GLanguage("????n v??? ??o l?????ng",session,jedis) %></TH>
																	<TH width="16%"><%=Utility.GLanguage("Quy ?????i",session,jedis) %></TH>
																	<TH width="21%"><%=Utility.GLanguage("S??? l?????ng",session,jedis) %></TH>
																	<TH width="21%"><%=Utility.GLanguage("????n v??? ??o l?????ng",session,jedis) %></TH>
															</TR>
															<%
																String[] sl1 = spBean.getSl1();
																/* String[] sl2 = spBean.getSl2(); */
																String[] sl2 = {"1", "1", "1", "1", "1"};
																String[] dvdl1 = spBean.getDvdl1();
																String[] dvdl2 = spBean.getDvdl2();
																if (!(dvdl1[0] == null) & !(dvdl2[0] == null))
																{
																	
																	if (!dvdl1[0].equals(dvdlId))
																	{
																		dvdl1[0] = dvdlId;
																		sl1[0] = "";
																	} 

																	if (!dvdl2[0].equals("100018"))
																	{
																		dvdl2[0] = "100018";
																		sl2[0] = "1";
																	}
																} 
																else
																{
																	dvdl1[0] = dvdlId;
																	sl1[0] = "";
																	dvdl2[0] = "100018";
																	sl2[0] = "1";
																}

																for (int i = 0; i < 5; i++)
																{ %>
																<TR class='tblightrow'>
																<TD align="center">
																	<%
																	if (!(dvdl1[i] == null))
																	{
																		if (dvdl1[i].trim().length() > 0)
																		{ %> 
																		<input name="sl1" type="text" style="width: 100%" 
																		value="<%=sl1[i]%> "> 
																	<% 	} 
																		else
																		{ %> 
																		<input name="sl1" type="text" style="width: 100%" value="">
																	<%	}
																	} 
																	else
																	{ %> 
																	<input name="sl1" type="text" style="width: 100%" value="">
																<%	}	%>
																</TD>
																<TD align="center">
																	<select  name="dvdl1" "
																	style="width: 100%; height: 22"
																	onChange="updateUoM();">
																	<option value=""></option>
																	<%
																	dvdl = spBean.createDvdlRS();
																	try
																	{
																		while (dvdl.next())
																		{
																			if (!(dvdl1[i] == null))
																			{
																				if (dvdl1[i].equals(dvdl.getString("dvdlId")))
																				{ %>
																	<option value="<%=dvdl.getString("dvdlId")%>" selected>
																	<%=dvdl.getString("dvdlTen")%> </option>
																	<% 			}
																				else
																				{ %>
																	<option value="<%=dvdl.getString("dvdlId")%>">
																	<%=dvdl.getString("dvdlTen")%> </option>
																			<% 	}
																			} 
																			else
																			{ %>
																	<option value="<%=dvdl.getString("dvdlId")%>">
																	<%=dvdl.getString("dvdlTen")%> </option>
																	<% 		}
																		}
																		
																		dvdl.close();
																	} 
																	catch (java.sql.SQLException e)
																		{ } %>

																	</select>
																</TD>
										<TD align="center">=</TD>
										<TD align="center">
										<%
										if (!(dvdl2[i] == null))
										{
											if (dvdl2[i].trim().length() > 0)
											{
											%> <%-- <input name="sl2" type="text" readonly style="width: 100%"
											value=<%=sl2[i]%>> --%>
												<input name="sl2" type="text" style="width: 100%"
											value=<%=sl2[i]%>> <%
											} 
											else
											{
											%> <!-- <input name="sl2" type="text" readonly style="width: 100%" value="1"> -->
												<input name="sl2" type="text" style="width: 100%" value="1">
											<%
											}
										} 
										else
										{
										%> <!-- <input name="sl2" type="text" readonly style="width: 100%" value="1"> -->
											<input name="sl2" type="text" style="width: 100%" value="1">
										<%
										}
										%>
										</TD>
										
										<TD align="center"><select name="dvdl2"
										<%-- style="width: 100%; height: 22" id="<%=i%>" onChange="setThungdautien();"> --%>
										style="width: 100%; height: 22" id="<%=i%>">
										<option value=""></option>
										<%
										
										dvdl = spBean.createDvdlRS();
										try
										{
										while (dvdl.next())
										{
										if (!(dvdl2[i] == null))
										{
											if (dvdl2[i].equals(dvdl.getString("dvdlId")))
											{
										%>
										<option value="<%=dvdl.getString("dvdlId")%>"
										selected><%=dvdl.getString("dvdlTen")%>
										</option>
										<%
										} else
											{
										%>
										<option value="<%=dvdl.getString("dvdlId")%>"><%=dvdl.getString("dvdlTen")%>
										</option>
										<%
										}
										} else
										{
										%>
										<option value="<%=dvdl.getString("dvdlId")%>"><%=dvdl.getString("dvdlTen")%>
										</option>
										<%
										}
										}
										dvdl.close();
										} catch (java.sql.SQLException e)
										{
										}
										%>

																</select></TD>
															</TR>
															<%
																}
															%>
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
			</TD>
	</TABLE>
</form>
</BODY>
<script type="text/javascript">
dropdowncontent.init('nhId', "right-bottom", 300, "click");
dropdowncontent.init('clId', "right-bottom", 300, "click");
</script>
</HTML>

<%
	if (dvdl != null) 
		dvdl.close();
	
	if (dvkd != null)
		dvkd.close();
	
	if (nh != null)
		nh.close();
	
	if (cl != null)
		cl.close();
	
	if (nsp != null)
		nsp.close();
		
	if (spBean.getDb() != null) 
		spBean.DBClose();
}
%>