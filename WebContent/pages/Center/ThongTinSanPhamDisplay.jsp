<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="geso.dms.center.beans.thongtinsanpham.IThongtinsanpham"%>
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
		ResultSet nccRs = (ResultSet) spBean.getNccRs();
		ResultSet splist = (ResultSet) spBean.getSanphamRs();
		String dvdlId = (String) spBean.getDvdlId();
		String dvkdId = (String) spBean.getDvkdId();
		String nhId = (String) spBean.getNhId();
		String clId = (String) spBean.getClId();
		Hashtable nspIds = spBean.getNspIds();
		Hashtable<Integer, String> spIds = spBean.getSpIds();
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
 
 
 
 function Dinhdang(e){

		e.value = DinhDangTien(e.value);
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

</SCRIPT>
</HEAD>
<BODY leftmargin="0" bottommargin="0" topmargin="0" rightmargin="0">
	<form name="spForm" method="post" action="../../ThongtinsanphamUpdateSvl"  enctype="multipart/form-data" >
	<% redis.clients.jedis.Jedis jedis =  geso.dms.center.util.Utility.getJedis(); %><% geso.dms.center.util.Csrf csdr=new geso.dms.center.util.Csrf(request,response,true,false,true);%>
 <input type="hidden" name="<%=csdr.get_tokenName() %>" value='<%=csdr.get_tokenValue() %>'>
	
	
		<input type="hidden" name="userId" value='<%=spBean.getUserId()%>'>
		 <input type="hidden" name="action" value="1">
		 <input type="hidden" name="id" value="<%=spBean.getId() %>"> 
		<TABLE width="100%" border="0" cellspacing="0" cellpadding="0" height="100%">
			<TR>
				<TD colspan="4" align='left' valign='top' bgcolor="#FFFFFF">
					<TABLE width="100%" border="0" cellpadding="0" cellspacing="1">
						<TR>
							<TD align="left" class="tbnavigation">
								<table width="100%" border="0" cellpadding="0" cellspacing="0">
									<tr height="22">
										<TD align="left" colspan="2" class="tbnavigation">
											&nbsp;<%= url %> > <%=Utility.GLanguage("Hiển thị",session,jedis) %>
										<TD colspan="2" align="right" class="tbnavigation"><%=Utility.GLanguage("Chào mừng bạn",session,jedis) %>  <%=userTen%>&nbsp;
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
									</TR>
								</TABLE>
							</TD>
						</TR>
					</TABLE>

					<TABLE width="100%" border="0" cellpadding="0" cellspacing="1">
						<tr>
							<TD align="left" colspan="4" class="legendtitle">
								<FIELDSET>
									<LEGEND class="legendtitle"><%=Utility.GLanguage("Báo lỗi nhập liệu",session,jedis) %></LEGEND>

									<textarea name="dataerror" style="width: 100%" readonly rows="1"><%=spBean.getMessage()%></textarea>
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
												<LEGEND class="legendtitle" style="color: black"><%=Utility.GLanguage("Thông tin sản phẩm",session,jedis) %> </LEGEND>

												<TABLE border="0" width="100%" cellpadding="4" cellspacing="0">
													<TR>
														<TD class="plainlabel"><%=Utility.GLanguage("Mã",session,jedis) %><FONT class="erroralert"> *</FONT></TD>
														<TD class="plainlabel" ><INPUT type="text" name="masp" style="width: 200px" value='<%=spBean.getMasp()%>'  ></TD>														
														<TD class="plainlabel"><%=Utility.GLanguage("Tồn kho an toàn",session,jedis) %></TD>
														<TD class="plainlabel" ><INPUT type="number" name="tontoithieu" style="width: 200px; text-align: right;" value='<%=spBean.getTontoithieu()%>'></TD>
														
														
													</TR>
													<TR>
														<TD class="plainlabel"><%=Utility.GLanguage("Tên",session,jedis) %><FONT class="erroralert">*</FONT></TD>
														<TD class="plainlabel"><input name="tensp" type="text" style="width: 200px" value='<%=spBean.getTen()%>'   ></TD>
													<TD class="plainlabel"><%=Utility.GLanguage("Nhà cung cấp",session,jedis) %><FONT class="erroralert">*</FONT></TD>
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
													
													
												<%-- 	<TD class="plainlabel">Thể tích <FONT class="erroralert"> *</FONT></TD>
													<TD class="plainlabel"><INPUT type="text" name="tt" style="width: 200px; text-align: right;" value='<%=spBean.getTT()%>' onkeypress="return keypress(event);"> &nbsp; <b><i>cm<sup>3</sup></i></b>
													</TD> --%>
													</TR>
													<TR>
														<TD class="plainlabel"><%=Utility.GLanguage("Đơn vị đo lường",session,jedis) %><FONT class="erroralert"> *</FONT></TD>
														<TD class="plainlabel">
															<select id="dvdlId" disabled="disabled" >
																<option value=""></option>
																<%
																	try {
																		dvdl.beforeFirst();
																		while (dvdl.next())
																		{
																			if (dvdlId.equals(dvdl.getString("dvdlId")))
																			{ %>
																		<option value='<%=dvdl.getString("dvdlId")%>' selected><%=dvdl.getString("dvdlTen")%></option>
																	<% } else { %>
																		<option value='<%=dvdl.getString("dvdlId")%>'><%=dvdl.getString("dvdlTen")%></option>
																	<% } } 
																	} 
																	catch (Exception e){ e.printStackTrace(); }
																%>
															</select>
															<input type="hidden" name="dvdlId" value="<%= dvdlId %>" />
															
															
														</TD>
														<TD class="plainlabel"><%=Utility.GLanguage("Tuổi thọ",session,jedis) %><FONT class="erroralert"> *</FONT></TD>
														<TD class="plainlabel"><INPUT type="text" name="hansudung" style="width: 200px; text-align: right;" value='<%=spBean.getHansudung()%>' onkeypress="return keypress(event);"> &nbsp;<i>tháng</i></TD>
												
													</TR>

													<tr>
														<%-- <TD class="plainlabel">Đơn vị đo lường chuẩn</TD>
														<TD class="plainlabel"><select name="dvdlETCId" id="dvdlETCId" >
																<option value=""></option>
																<%
																	try {
																		dvdl.beforeFirst();
																		while (dvdl.next())
																		{
																			if (spBean.getDvdlETCId().equals(dvdl.getString("dvdlId")))
																			{ %>
																		<option value='<%=dvdl.getString("dvdlId")%>' selected><%=dvdl.getString("dvdlTen")%></option>
																	<% } else { %>
																		<option value='<%=dvdl.getString("dvdlId")%>'><%=dvdl.getString("dvdlTen")%></option>
																	<% } } 
																	} 
																	catch (Exception e){ e.printStackTrace(); }
																%>
															</select></TD> --%>
														<TD class="plainlabel"><%=Utility.GLanguage("Đơn vị kinh doanh",session,jedis) %><FONT class="erroralert">*</FONT></TD>
														<TD class="plainlabel" ><select disabled="disabled" >
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
																		} catch (java.sql.SQLException e)
																		{
																		}
																%>


															</select>
															<input type="hidden" name="dvkdId" value="<%= dvkdId %>" />
														</TD>
														
														<TD class="plainlabel"><%=Utility.GLanguage("Thuế suất",session,jedis) %> (<%=Utility.GLanguage("Áp dụng bảng giá trước VAT",session,jedis) %>) <FONT class="erroralert"> *</FONT></TD>
														<TD class="plainlabel"style="width: 200px;"><input
															style="text-align: right; width: 60%;" type="text"
															name="pt_vat" 
															value='<%=formatter.format(spBean.getPt_vat())%>'onkeypress="return keypress(event);"onChange="Dinhdang(this);" />
														</TD>
														
														
													</tr>

	
													<tr>
														<TD class="plainlabel"><%=Utility.GLanguage("Tên viết tắt",session,jedis) %></TD>
														<TD class="plainlabel" ><INPUT type="text" name="tenviettat" style="width: 200px; text-align: right;" value='<%=spBean.getTenviettat()%>'></TD>
														<TD class="plainlabel" ></TD>
														<TD class="plainlabel" ></TD>
														
														<TD class="plainlabel" style="display: none;"><%=Utility.GLanguage("Thuế suất",session,jedis) %> (<%=Utility.GLanguage("Áp dụng bảng giá sau VAT",session,jedis) %>) <FONT class="erroralert"> *</FONT></TD>
														<TD class="plainlabel"style="width: 200px; display: none;" ><input
															style="text-align: right; width: 60%;" type="text"
															name="pt_vat_2" 
															value='<%=formatter.format(spBean.getPt_vat_2())%>'onkeypress="return keypress(event);"onChange="Dinhdang(this);" />
														</TD>
													</tr>
													
												<%-- 	<tr>
														<TD class="plainlabel">Dạng bào chế</TD>
														<TD class="plainlabel"><INPUT type="text" name="dangbaoche" style="width: 200px; text-align: right;" value='<%=spBean.getDangbaoche()%>'></TD>
														<TD class="plainlabel">Hàm lượng </TD>
														<TD class="plainlabel"><INPUT type="text" name="hamluong" style="width: 200px; text-align: right;" value='<%=spBean.getHamluong()%>'></TD>
													
													</tr> --%>


												<tr>
														<TD class="plainlabel"><%=Utility.GLanguage("Nhãn hàng",session,jedis) %></TD>
														<TD class="plainlabel"><select name="nhId" id="nhId" onChange="submitform();">
																<option value=""></option>
																<%
																	try {
																		
																		while (nh.next())
																		{
																			if (spBean.getNhId().equals(nh.getString("pk_seq")))
																			{ %>
																		<option value='<%=nh.getString("pk_seq")%>' selected><%=nh.getString("diengiai")%></option>
																	<% } else { %>
																		<option value='<%=nh.getString("pk_seq")%>'><%=nh.getString("diengiai")%></option>
																	<% } } 
																	} 
																	catch (Exception e){ e.printStackTrace(); }
																%>
															</select></TD>
														<TD class="plainlabel"><%=Utility.GLanguage("Chủng loại",session,jedis) %></TD>
														<TD class="plainlabel"><select name="clId" >
																<option> </option>
																
																<%
																	try
																		{
																			while (cl.next())
																			{
																				if (cl.getString("pk_seq").equals(spBean.getClId()))
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
														<TD class="plainlabel"><%=Utility.GLanguage("Ngành hàng",session,jedis) %><FONT class="erroralert">*</FONT></TD>
														<TD class="plainlabel">
															<%
																if (dvkdId.length() > 0)
																	{
															%> <select name="nganhhangid">
																<%
																	} else
																		{
																%>
																<select name="nganhhangid" disabled="disabled">
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
														<TD class="plainlabel" ><%=Utility.GLanguage("Hoạt động",session,jedis) %> &nbsp; <%
														 	if (spBean.getTrangthai().equals("1"))
														 		{
														 %> <input name="trangthai" type="checkbox" value="1" checked> <%
														 	} else
														 		{
														 %> <input name="trangthai" type="checkbox" value="0"> <%
														 	}
														 %>
														</TD>
														
														
														<TD class="plainlabel" ><%=Utility.GLanguage("Sản phẩm khuyến mãi",session,jedis) %> &nbsp; <%
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
										  				<TD class="plainlabel"><%=Utility.GLanguage("Hình ảnh",session,jedis) %></TD>
														<TD colspan = "3" class="plainlabel" >
															<INPUT type="file" name="filename" value="">
												  	  		<input type="hidden" id="valueten" name="hinhanh" value="<%= spBean.getHinhanh() %>">
												  	  		<% if(spBean.getHinhanh().length() > 0 ) {	%>
												  	  			<div id="tenfilea"><p><%= spBean.getHinhanh() %><img src="../images/Delete20.png" onclick="Xoafile()" style="cursor: pointer;" alt="Xóa" width="20" height="20" longdesc="Cap nhat" border = 0></p></div>
												  	  			
				                        						</div>
												  	  		<%} %>
														</TD>
													</TR>
													
													<TR>
												<TD style="display: none" class="plainlabel" ><%=Utility.GLanguage("Sản phẩm mới",session,jedis) %>&nbsp; <%
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
													<TD style="display: none" class="plainlabel" colspan="2"><%=Utility.GLanguage("Sản phẩm chủ lực",session,jedis) %> &nbsp; <%
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
												 
												 
													<TR style="display:none">
														<TD class="plainlabel">Packsize<FONT class="erroralert"> *</FONT></TD>
														<TD colspan="3" class="plainlabel"><select name="packsizeid">

																<option value=""></option>
																<%
																	try
																		{
																			while (rspacksize.next())
																			{
																				if (spBean.getPacksizeId().equals(rspacksize.getString("pk_seq")))
																				{
																%>
																<option value='<%=rspacksize.getString("pk_seq")%>' selected><%=rspacksize.getString("ten")%></option>
																<%
																	} else
																				{
																%>
																<option value='<%=rspacksize.getString("pk_seq")%>'><%=rspacksize.getString("ten")%></option>
																<%
																	}
																			}
																			rspacksize.close();
																		} catch (java.sql.SQLException e)
																		{
																		}
																%>


														</select></TD>
													</TR>

													<TR style="display: none;" >
														<TD class="plainlabel">Giá bán lẻ chuẩn <FONT class="erroralert"> *</FONT></TD>
														<TD colspan="3" class="plainlabel" align="left">
															<%
																String gia = "";
																	if (spBean.getGiablc().trim().length() != 0)
																	{
																		gia = formatter.format(Double.parseDouble(spBean.getGiablc()));
																	} else
																	{
																		gia = spBean.getGiablc();
																	}
															%> <input name="giablc" type="text" size="10" style="text-align: right;" value='<%=gia%>' onkeyup="Dinhdang(this)" onkeypress="return keypress(event);"> &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; <%
 	if (spBean.getType().equals("1"))
 		{
 %>
															<input name="type" type="checkbox" id="type" value="1" checked="checked" onChange="submitform();"> is Bundle <%
																} else
																	{
															%> <input name="type" type="checkbox" id="type" value="0" onChange="submitform();"> is Bundle <%
 	}
 %>

														</TD>
													</TR>
													<tr style="display: none;" >
														<TD class="plainlabel">Số thùng quy đổi dành cho thưởng <FONT class="erroralert"> *</FONT></TD>
														<TD colspan="2" class="plainlabel"><INPUT type="text" name="quydoithuong" style="width: 150px; text-align: right;" value='<%=spBean.getquydoithuong()%>' onkeypress="return keypress(event);"> &nbsp; <b><i></sup></i></b></TD>
													</tr>

												</TABLE>
											</FIELDSET>

<%if(spBean.getType().equals("1")){ %>
											<fieldset>
												<legend class="legendtitle"> Tiêu chí lọc sản phẩm bundle </legend>
												<table style="width: 100%" cellpadding="4" cellspacing="1">
													<tr class="tbheader">
														<TD width="10%" class="plainlabel">Chủng loại</TD>
														<TD width="90%" valign="middle" class="plainlabel"><a href="" id="clId" rel="subcontentClId"> &nbsp; <img alt="Loại sản phẩm" src="../images/vitriluu.png"></a>
															<DIV id="subcontentClId" style="position: absolute; visibility: hidden; border: 9px solid #80CB9B; background-color: white; width: 590px; height: 300px; overflow: auto; padding: 4px;">
																<table width="90%" align="center">
																	<tr class="tbheader">
																		<th width="350px">Chủng loại</th>
																		<th width="100px" align="center">Chọn hết <input type="checkbox" onchange="sellectAll_ClId()" id="chkAll_ClId"></th>
																	</tr>
																	<%
																		if (cl != null)
																			{
																				cl.beforeFirst();
																				int jj=0;
																				while (cl.next())
																				{
																					if(jj %2 ==0){
																	%>
																	<tr class="tblightrow">
																		<%  }  else { %>
																	<tr class="tbdarkrow">
																	<%} %>
																		<td><%=cl.getString("clTen")%></td>
																		<td>
																			<%
																				if (spBean.getChungloaiIds().indexOf(cl.getString("clId")) >= 0)
																			{
																			%> <input type="checkbox" name="chungloaiIds" checked="checked" value="<%=cl.getString("clId")%>" /> <%
 	} else
 				{
 %> <input type="checkbox" name="chungloaiIds" value="<%=cl.getString("clId")%>" /> <%
 	}
 %>
																		</td>
																	</tr>
																	<%
																	jj++;
																		}
																				cl.close();
																	%>
																	<%
																		}
																	%>
																</table>
																<div align="right">
				                     	<label style="color: red" ></label>
				                     	&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
				                     	<a href="javascript:dropdowncontent.hidediv('subcontentClId');submitform();">Hoàn tất</a>
				                     </div>
																
															</DIV></TD>
													</TR>

													<tr class="tbheader">
														<TD class="plainlabel">Nhãn hàng</TD>
														<TD class="plainlabel" valign="middle"><a href="" id="nhId" rel="subcontentNhIds"> &nbsp; <img alt="Nhãn hàng" src="../images/vitriluu.png"></a>
															<DIV id="subcontentNhIds" style="position: absolute; visibility: hidden; border: 9px solid #80CB9B; background-color: white; width: 590px; height: 300px; overflow: auto; padding: 4px;">
																<table width="90%" align="center">
																	<tr class="tbheader">
																		<th width="350px">Nhãn hàng</th>
																		<th width="100px" align="center">Chọn hết <input type="checkbox" onchange="sellectAll_NhId()" id="chkAll_NhId"></th>
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
																		<%  }  else { %>
																	<tr class="tbdarkrow">
																	<%} %>
																		<td><%=nh.getString("nhTen")%></td>
																		<td>
																			<%
																				if (spBean.getNhanhangIds().indexOf(nh.getString("nhId")) >= 0)
																			{
																			%> <input type="checkbox" name="nhanhangIds" checked="checked" value="<%=nh.getString("nhId")%>" /> <%
 	} else
 				{
 %> <input type="checkbox" name="nhanhangIds" value="<%=nh.getString("nhId")%>" /> <%
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
				                     	<label style="color: red" ></label>
				                     	&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
				                     	<a href="javascript:dropdowncontent.hidediv('subcontentNhIds');submitform();">Hoàn tất</a>
				                     </div>
															
															</DIV></TD>
													</TR>

												</table>
											</fieldset> 
<%
}
												if(splist != null){ int mm = 0; %>
										  		<fieldset><legend class="legendtitle"> Chọn sản phẩm thuộc Bundle </legend>
										  		<table style="width: 100%" cellpadding="4" cellspacing="1">
										  		<tr class="tbheader">
										  			<th align="center">Mã sản phẩm</th>
										  			<th align="center">Tên sản phẩm</th>
										  			<th align="center" style="width:80px">Số lượng</th>
										  			<th align="center">Chọn</th>
										  		</tr>
										  			<% while(splist.next()){ 
										  				
										  				
										  				String soluong = splist.getString("soluong");
										  				if(soluong.equals("0"))
										  					soluong = "";
										  				
										  				String values = splist.getString("pk_seq");
										  				if(mm % 2 == 0){	%>
										  					<tr class="tblightrow">
										  				<%}else{ %>
										  					<tr class="tbdarkrow">
										  				<%} %>
										  					<td><%= splist.getString("spMa") %></td>
										  					<td><%= splist.getString("spTen") %></td>
										  					<td><input type="text" name="spSoluong_<%=values %>" value="<%=soluong %>" id="spSoLuong<%= Integer.toString(mm) %>" style="width:100%; text-align: right;"></td>
										  					<td align="center">
										  					<% if(soluong != ""){ %>
										  						<input type="checkbox" name="spIds" id="spIds<%= Integer.toString(mm) %>" value='<%= values %>' checked="checked" onchange="chonSp(<%= mm %>)" >
										  					<% }else{ %>
										  						<input type="checkbox" name="spIds" id="spIds<%= Integer.toString(mm) %>" value='<%= values %>' onchange="chonSp(<%= mm %>)">
										  					<% } %>
										  					</td>
										  				</tr>
										  			<% mm ++;} %>
										  		</table></fieldset>
										  <%} %>	
										  
										  
										  
										  
										  
										  <TABLE width="100%" border="0" cellpadding="0"
											cellspacing="0">
											<TR>
												<TD>
													<FIELDSET>
														<LEGEND class="legendtitle" style="color: black"><%=Utility.GLanguage("Nhóm sản phẩm",session,jedis) %></LEGEND>
														<TABLE border="0" width="100%" cellpadding="4"
															cellspacing="1">
															<TR class="tbheader">
																<TH width="30%"><%=Utility.GLanguage("Tên",session,jedis) %></TH>
																<TH width="60%"><%=Utility.GLanguage("Diễn giải",session,jedis) %></TH>
																<TH width="10%"><%=Utility.GLanguage("Chọn",session,jedis) %></TH>
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
										  
								<%-- 			<TABLE width="100%" border="0" cellpadding="0" cellspacing="0">
												<TR>
													<TD>
														<FIELDSET>
															<LEGEND class="legendtitle" style="color: black">Chọn nhóm sản phẩm</LEGEND>
															<TABLE border="0" width="100%" cellpadding="4" cellspacing="1">
																<TR class="tbheader">
																	<TH width="30%">Nhóm sản phẩm</TH>
																	<TH width="60%"><%=Utility.GLanguage("Diễn giải",session,jedis) %></TH>
																	<TH width="10%">Chọn</TH>
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
																			<input type="checkbox" name="nspIds" value='<%=nsp.getString("nspId")%>' checked>
																			<%
																				} else
																								{
																			%>
																			<input type="checkbox" name="nspIds" value='<%=nsp.getString("nspId")%>'>
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
															<LEGEND class="legendtitle">  &nbsp;<%=Utility.GLanguage("Quy cách",session,jedis) %><FONT class="erroralert"> *</FONT> </LEGEND>
															<TABLE border="1" cellpadding="0" cellspacing="1" width="100%">
																<TR class="tbheader">
																	<TH width="21%"><%=Utility.GLanguage("Số lượng",session,jedis) %></TH>
																	<TH width="21%"><%=Utility.GLanguage("Đơn vị đo lường",session,jedis) %></TH>
																	<TH width="16%"><%=Utility.GLanguage("Quy đổi",session,jedis) %></TH>
																	<TH width="21%"><%=Utility.GLanguage("Số lượng",session,jedis) %></TH>
																	<TH width="21%"><%=Utility.GLanguage("Đơn vị đo lường",session,jedis) %></TH>
																</TR>
																<%
																	String[] sl1 = spBean.getSl1();
																		/* String[] sl2 = spBean.getSl2(); */
																		String[] sl2 = spBean.getSl2();
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
																		} else
																		{
																			dvdl1[0] = dvdlId;
																			sl1[0] = "";
																			dvdl2[0] = "100018";
																			sl2[0] = "1";

																		}

																		for (int i = 0; i < 5; i++)
																		{
																%>
																<TR class='tblightrow'>
																	<TD align="center">
																		<%
																			if (!(dvdl1[i] == null))
																					{
																						if (dvdl1[i].trim().length() > 0)
																						{
																		%> <input name="sl1" type="text" style="width: 100%" value="<%=sl1[i]%>"> <%
																 	} else
																 				{
																 %> <input name="sl1" type="text" style="width: 100%" value=""> <%
																 	}
																 			} else
																 			{
																 %> <input name="sl1" type="text" style="width: 100%" value=""> <%
																 	}
																 %>
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
																										{
																				%>
																				<option value="<%=dvdl.getString("dvdlId")%>" selected><%=dvdl.getString("dvdlTen")%>
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


																		</select>
																	</TD>
																	<TD align="center">=</TD>
																	<TD align="center">
																		<%
																			if (!(dvdl2[i] == null))
																					{
																						if (dvdl2[i].trim().length() > 0)
																						{
																		%> <input name="sl2" type="text" readonly style="width: 100%" value=<%=sl2[i]%>> <%
																 	} else
																 				{
																 %> <input name="sl2" type="text" readonly style="width: 100%" value="1"> <%
																 	}
																 			} else
																 			{
																 %> <input name="sl2" type="text" readonly style="width: 100%" value=""> <%
																 	}
																 %>
																	</TD>
													<TD align="center"><select name="dvdl2"
										style="width: 100%; height: 22" id="<%=i%>" onChange="setThungdautien();">
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
																			<option value="<%=dvdl.getString("dvdlId")%>" selected><%=dvdl.getString("dvdlTen")%>
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