<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page  import = "java.util.Iterator" %>
<%@ page  import = "java.util.List" %>
<%@ page  import = "java.util.ArrayList" %>
<%@ page  import = "geso.dms.distributor.beans.phieuxuatkho.*" %>
<%@ page  import = "java.sql.ResultSet" %>
<%@ page  import = "geso.dms.center.util.*" %>

<%	String userId = (String) session.getAttribute("userId");  
	String userTen = (String) session.getAttribute("userTen");  	
	String sum = (String) session.getAttribute("sum");
	Utility util = (Utility) session.getAttribute("util");
	if(!util.check(userId, userTen, sum)){
		response.sendRedirect(request.getContextPath() + "/redirect.jsp");
	}else{ 
		
		int[] quyen = new  int[6];
		quyen = util.Getquyen("PhieuxuatkhoSvl","",userId);
	%>


<% IPhieuxuatkhoList obj = (IPhieuxuatkhoList)session.getAttribute("obj"); %>
<% ResultSet rs = obj.getRsPXK(); %>
<% ResultSet nvgn = (ResultSet)obj.getNhanvienGN();
   
%>

<% obj.setNextSplittings(); %>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"> <% Utility Utilback = new Utility(); %>
<HTML>
<HEAD>
<TITLE><%= getServletContext().getInitParameter("TITLENAME") %></TITLE>
<META http-equiv="Content-Type" content="text/html; charset=utf-8">
<META http-equiv="Content-Style-Type" content="text/css">
<LINK rel="stylesheet" href="../css/main.css" type="text/css">
<LINK rel="stylesheet" type="text/css" href="../css/style.css" />
	<link type="text/css" rel="stylesheet" href="../css/mybutton.css">
<LINK rel="stylesheet" href="../css/calendar.css" type="text/css">
<LINK rel="stylesheet" type="text/css" media="print" href="../css/impression.css">

<script type="text/javascript" language="JavaScript" src="../scripts/simplecalendar.js"></script>
<script type="text/javascript" src="../scripts/phanTrang.js"></script>

<script type="text/javascript" src="../scripts/jquery.min.1.7.js"></script>
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

<SCRIPT language="javascript" type="text/javascript">
	function confirmLogout()
	{
	   if(confirm("Ban co muon dang xuat?"))
	   {
			top.location.href = "home.jsp";
	   }
	   return;
	 }
	function processing(id,chuoi){
		 document.getElementById(id).innerHTML = "<a href='#'><img src='../images/waiting.gif'  title='cho thuc hien..' border='1' longdesc='cho thuc hien..' style='border-style:outset'> Proc...</a>";
		 document.getElementById(id).href = chuoi;
		  
	}
	function searchphieu()
	{   
		document.forms["pxkForm"].action.value="search";
	    document.forms["pxkForm"].submit();
	}
	function clearform()
	{
		
		 document.forms["pxkForm"].nvgnTen.selectedIndex = -1;
		 document.forms["pxkForm"].trangthai.selectedIndex = -1;
		 document.forms["pxkForm"].tungay.value = "";
		 document.forms["pxkForm"].mapxk.value = "";
		 document.forms["pxkForm"].khId.value = "";
		 document.forms["pxkForm"].denngay.value = "";
		 document.forms["pxkForm"].mafast.value = "";
		 document.forms["pxkForm"].madonhang.value = "";
		 document.forms["pxkForm"].action.value = "clear";
		 document.forms["pxkForm"].submit();
	}
	function newform(){
		
		 document.forms["pxkForm"].action.value="Tao moi";
		 document.forms["pxkForm"].submit();
	}
	function thongbao()
	{
		if(document.getElementById("msg").value != '')
			alert(document.getElementById("msg").value);
	}
</SCRIPT>

</HEAD>
<BODY leftmargin="0" bottommargin="0" topmargin="0" rightmargin="0">
	<form name="pxkForm" method="post" action="../../PhieuxuatkhoSvl">
	<% redis.clients.jedis.Jedis jedis =  geso.dms.center.util.Utility.getJedis(); %><% geso.dms.center.util.Csrf csdr=new geso.dms.center.util.Csrf(request,response,true,false,true);%>
 <input type="hidden" name="<%=csdr.get_tokenName() %>" value='<%=csdr.get_tokenValue() %>'>
	
		<input type="hidden" name="userId" value="<%= userId %>"> <input
			type="hidden" name="nppId" value="<%= obj.getNppId() %>"> <input
			type="hidden" name="action" value="1"> 
		<input type="hidden"name="crrApprSplitting" value="<%= obj.getCrrApprSplitting() %>">
		<input type="hidden" name="nxtApprSplitting" value="<%= obj.getNxtApprSplitting() %>"> <input
			type="hidden" name="msg" id="msg" value='<%= obj.getMsg() %>'>
		<script type="text/javascript">
	thongbao();
</script>
		<TABLE width="100%" border="0" cellspacing="0" cellpadding="0"
			height="100%">
			<TR>
				<TD colspan="4" align='left' valign='top' bgcolor="#FFFFFF">
					<!--begin body Dossier-->
					<TABLE width="100%" border="0" cellpadding="0" cellspacing="2">
						<TR>
							<TD align="left" class="tbnavigation">
								<table width="100%" border="0" cellpadding="0" cellspacing="0">
									<tr height="22">
										<TD align="left" colspan="2" class="tbnavigation">
										Qu???n l?? b??n h??ng > B??n h??ng OTC > Phi???u xu???t kho
										
										</TD>

										<TD colspan="2" align="right" class="tbnavigation">Ch??o
											m???ng Chi nh??nh / NPP <%= obj.getNppTen() %></TD>
									</tr>
								</table></TD>
						</TR>
					</TABLE>
					<TABLE width="100%" border="0" cellpadding="0" cellspacing="2">
						<TR>
							<TD>
								<FIELDSET>
									<LEGEND class="legendtitle">&nbsp;Ti??u ch?? t??m
										ki???m&nbsp;</LEGEND>
									<TABLE width="100%" cellspacing="0" cellpadding="3">
										<TR class="tblightrow">
											<TD class="plainlabel">Nh??n vi??n giao nh???n</TD>
											<TD class="plainlabel"><SELECT name="nvgnTen" onChange="searchphieu();">
												<option value="">&nbsp;&nbsp;</option>
													<% if(nvgn != null){
													  try{ while(nvgn.next()){ 
										    			if(nvgn.getString("nvgnId").equals(obj.getNvgnId())){ %>
																	<option value='<%=nvgn.getString("nvgnId")%>' selected><%=nvgn.getString("nvgnTen") %></option>
																	<%}else{ %>
																	<option value='<%=nvgn.getString("nvgnId")%>'><%=nvgn.getString("nvgnTen") %></option>
																	<%}}}catch(java.sql.SQLException e){} }
													%>
											</SELECT></TD>
											<TD class="plainlabel"> M?? PXK </TD>
											<TD class="plainlabel"><input  type="text" name="mapxk" value="<%= obj.getmaPXK() %>" size="11"  onChange="searchphieu();"> </TD>
										</TR>
										
										<TR>
											<TD class="plainlabel"><%=Utility.GLanguage("Tr???ng th??i",session,jedis) %></TD>
											<TD class="plainlabel"><SELECT
												name="trangthai" onChange="searchphieu();">
													<% if (obj.getTrangthai().equals("1")){%>
													<option value="1" selected>???? ch???t</option>
													<option value="0">Ch??a ch???t</option>
													<option value=""></option>
													<%}else if(obj.getTrangthai().equals("0")) {%>
													<option value="0" selected>Ch??a ch???t</option>
													<option value="1">???? ch???t</option>
													<option value=""></option>
													<%}else{%>
													<option value="" selected></option>
													<option value="1">???? ch???t</option>
													<option value="0">Ch??a ch???t</option>
													<%}%>
											</SELECT></TD>
											<TD class="plainlabel"> M??/ T??n kh??ch h??ng </TD>
											<TD class="plainlabel"><input  type="text" name="khId" value="<%= obj.getkhachHang() %>" size="11" onChange="searchphieu();"> </TD>
										</TR>
										<TR>
											<TD class="plainlabel"><%=Utility.GLanguage("T??? ng??y",session,jedis) %></TD>
											<TD class="plainlabel">
												<TABLE cellpadding="0" cellspacing="0">
													<TR>
														<TD><input class="days" type="text" name="tungay" value="<%= obj.getTungay() %>" size="11"  onChange="searchphieu();"></TD>
													</TR>
												</TABLE></TD>
											<TD class="plainlabel"><%=Utility.GLanguage("?????n ng??y",session,jedis) %></TD>
											<TD class="plainlabel">
												<TABLE cellpadding="0" cellspacing="0">
													<TR>
														<TD><input class="days" type="text" name="denngay" value="<%= obj.getDenngay() %>" size="11" onChange="searchphieu();"></TD>
													</TR>
												</TABLE></TD>
										</TR>
										<TR>
											<TD class="plainlabel"> M?? DMS </TD>
											<TD class="plainlabel" "><input  type="text" name="mafast" value="<%= obj.getmaFast() %>" size="11"  onChange="searchphieu();"> </TD>
											<TD class="plainlabel"> M?? ????n h??ng </TD>
											<TD class="plainlabel" ><input  type="text" name="madonhang" value="<%= obj.getmaDonhang() %>" size="11"  onChange="searchphieu();"> </TD>
										
										</TR>
										
										<TR>
											<TD class="plainlabel" colspan="4"><a class="button2"
												href="javascript:searchphieu()"> <img style="top: -4px;"
													src="../images/Search30.png" alt=""><%=Utility.GLanguage("T??m ki???m",session,jedis) %> </a>&nbsp;&nbsp;&nbsp;&nbsp;

												<a class="button2" href="javascript:clearform()"> <img
													style="top: -4px;" src="../images/button.png" alt="">Nh???p
													l???i</a>&nbsp;&nbsp;&nbsp;&nbsp; <!-- 
							  <INPUT name="action" type="submit" value="Tim kiem">&nbsp;
                              <INPUT name="reinitialiser" type="reset" value="Nhap lai"></TD>
                               -->
										</TR>
									</TABLE>

								</FIELDSET></TD>
						</TR>
					</TABLE>
					<TABLE width="100%" border="0" cellpadding="0" cellspacing="0">
						<TR>
							<TD width="100%">
								<FIELDSET>
									<LEGEND class="legendtitle">
										Phi???u xu???t kho &nbsp;&nbsp;&nbsp;&nbsp;
										<%-- <%if(quyen[Utility.THEM]!=0){ %>
										 <a class="button3" href="javascript:newform()"> 
										 <img style="top: -4px;" src="../images/New.png" alt=""><%=Utility.GLanguage("T???o m???i",session,jedis) %> </a>
										 <%} %> --%>

										<!--  <INPUT name="action" type="submit" value="Tao moi">-->
									</LEGEND>
									<TABLE class="" width="100%" cellpadding="0" cellspacing="0">
										<TR>
											<TD>
												<TABLE width="100%" border="0" cellspacing="1"
													cellpadding="2">
													<TR class="tbheader">
														<TH align="center" style="width: 7%" >M?? phi???u</TH>
														<TH align="center" style="width: 7%" >S??? phi???u xu???t kho</TH>
														<TH align="center" style="width: 13%">NV giao nh???n</TH>
														<TH align="center" style="width: 18%">Kh??ch h??ng</TH>
														<!-- <TH align="center">M?? Fast</TH> -->
														<TH align="center" style="width: 10%" >Ng??y l???p</TH>
														<TH align="center" style="width: 10%" ><%=Utility.GLanguage("Tr???ng th??i",session,jedis) %></TH>
														<!-- <TH align="center" style="width: 10%" ><%=Utility.GLanguage("Ng??y t???o",session,jedis) %></TH>
														<TH align="center" style="width: 10%"><%=Utility.GLanguage("Ng?????i t???o",session,jedis) %></TH> -->
														<TH align="center" style="width: 10%"><%=Utility.GLanguage("Ng??y s???a",session,jedis) %></TH>
														<TH align="center" style="width: 15%"><%=Utility.GLanguage("Ng?????i s???a",session,jedis) %></TH>
														<TH align="center" style="width: 10%"><%=Utility.GLanguage("T??c v???",session,jedis) %></TH>
													</TR>
													<% 
									
									int m = 0;
									if(rs!=null)					
									while (rs.next()){
										
										if (m % 2 != 0) {%>
													<TR class="tblightrow">
														<%} else {%>
													
													<TR class="tbdarkrow">
														<%}%>
														<TD align="center"><%=rs.getString("pxkId") %></TD>
														<TD align="center"><%=rs.getString("donhang") %></TD>
														<TD align="left"><%= rs.getString("nvgnTen") %></TD>
														<TD align="center"><%=rs.getString("khachhang").replaceAll("__", "<br />") %></TD>
														<%-- <TD align="center"><%=rs.getString("mafast") %></TD> --%>
														<TD align="center"><%= rs.getString("ngaylapphieu") %></TD>
														<% if (rs.getString("trangthai").equals("1")){ %>
														<TD align="center"><b>???? ch???t</b></TD>
														<%}else{ if(rs.getString("trangthai").trim().equals("0")){ %>
														<TD align="center">Ch??a ch???t</TD>
														<%}else{ %>
														<TD align="center"><i>???? h???y</i>
														</TD>
														<% }}%>
														<%-- <TD align="center"><%=  rs.getString("ngaytao")%></TD>
														<TD align="left"><%=  rs.getString("nguoitao")%></TD> --%>
														<TD align="center"><%=  rs.getString("ngaysua")%></TD>
														<TD align="left"><%=  rs.getString("nguoisua")%></TD>
														<TD align="center" valign="middle">
															<% if(rs.getString("trangthai").trim().equals("0")) {%>
															 
															 <%if(quyen[Utility.SUA]!=0){ %>
															<A id='<%="update"+rs.getString("pxkId")%>'
															   href=""><img
															   src="../images/Edit20.png" alt="Cap nhat" width="20"
															   height="20" longdesc="Cap nhat" border=0 onclick="processing('<%="update"+rs.getString("pxkId")%>' , '../../PhieuxuatkhoUpdateSvl?userId=<%=userId%>&update=<%= rs.getString("pxkId") %>')" >
															</A>&nbsp; 
															<%} %>
														
														
															<%if(quyen[Utility.XOA]!=0){ %>
															<A href = "../../RouterSvl?task=<%= Utility.dongMa(getServletContext().getInitParameter("RedirectNoScript")+"PhieuxuatkhoSvl?userId="+userId+"&delete="+ rs.getString("pxkId")+"") %>"><img
																src="../images/Delete20.png" alt="Xoa" width="20"
																height="20" longdesc="Xoa" border=0
																onclick="if(!confirm('B???n c?? mu???n x??a phi???u xu???t kho n??y?')) return false;">
															</A>&nbsp;
															<%} %> 
															
															<%if(quyen[Utility.CHOT]!=0){ %>
															<A id='<%="chotpxkid" + rs.getString("pxkId") %>'
														   href=""><img 
														   src="../images/Chot.png" alt="Chot phieu xuat" width="20" height="20" longdesc="Chot phieu xuat"
														   border=0	onclick="if(!confirm('B???n c?? mu???n ch???t phi???u xu???t kho n??y?')) {return false ;}else{ processing('<%="chotpxkid"+rs.getString("pxkId")%>' , '../../RouterSvl?task=<%= Utility.dongMa(getServletContext().getInitParameter("RedirectNoScript")+"PhieuxuatkhoSvl?userId="+userId+"&chotphieu="+ rs.getString("pxkId")+"") %>&nppId=<%= obj.getNppId() %>&ngaylap=<%= rs.getString("ngaylapphieu").trim() %>');}" >
															</A>
														  <%} %>	   
														 
														 
														<%}else{ if(rs.getString("trangthai").equals("1")) { %>
														
														<%if(quyen[Utility.XEM]!=0){ %>
														 <A
															href = "../../RouterSvl?task=<%= Utility.dongMa(getServletContext().getInitParameter("RedirectNoScript")+"PhieuxuatkhoUpdateSvl?userId="+userId+"&display="+ rs.getString("pxkId") +"")%>"><img
																src="../images/Display20.png" alt="Hien thi" width="20"
																height="20" longdesc="Hien thi" border=0>
														</A>
														<%} %>
														
														<%if(quyen[Utility.XEM]!=0){ %>
														<%--  <A
															href="../../PhieuxuatkhoPdfSvl?userId=<%=userId%>&pdf=<%=rs.getString("pxkId") %>"><img
																src="../images/pdf.jpg" alt="In file Pdf" width="20"
																height="20" longdesc="In file Pdf" border=0>
																 --%>
																
																<%-- <A href="../../Phieuxuatkho_DonhangPdfSvl?userId=<%=userId%>&pdf=<%=rs.getString("pxkId") %>"><img
																src="../images/Printer30.png" alt="In ????n h??ng chi ti???t Pdf" width="20"
																height="20" longdesc="In file Pdf" border=0>
																</A> --%>
																
														
														<A href = "../../RouterSvl?task=<%= Utility.dongMa(getServletContext().getInitParameter("RedirectNoScript")+"InPhieuxuatkhoPdf_Svl?userId="+userId+"&pdf="+ rs.getString("pxkId")+"&nppId="+ obj.getNppId()+"&type=PXK"+"")%>"><IMG src="../images/Printer30.png"  title="In phi???u xu???t kho" width="20" height="20" border=0 ></A>
														
																
																
																
														<%} %>
																
														<%if(quyen[Utility.XEM]!=0){ %>
																<%-- <A href="../../PhieuXuatKhoExcelSvl?userId=<%=userId%>&excel=<%=rs.getString("pxkId") %>">
																<img src="../images/excel.gif" alt="In ????n h??ng chi ti???t Pdf" width="20"
																height="20" longdesc="In file Pdf" border=0>
																</A> --%>
														<%} %>	
														
														 <% } else { %>   
														 <%if(quyen[Utility.XEM]!=0){ %>
														 <A href = "../../RouterSvl?task=<%= Utility.dongMa(getServletContext().getInitParameter("RedirectNoScript") +"PhieuxuatkhoUpdateSvl?userId="+userId+"&display="+ rs.getString("pxkId")+"") %>"><img
																src="../images/Display20.png" alt="Hien thi" width="20"
																height="20" longdesc="Hien thi" border=0>
														</A> <%} %>
														<%}} %>
														</TD>
													</TR>
													<%m++; }%>

													<tr class="tbfooter">
														<TD align="center" valign="middle" colspan="13"
															class="tbfooter">
															<%if(obj.getNxtApprSplitting() >1) {%> <img alt="Trang Dau"
															src="../images/first.gif" style="cursor: pointer;"
															onclick="View('pxkForm', 1, 'view')"> &nbsp; <%}else {%>
															<img alt="Trang Dau" src="../images/first.gif">
															&nbsp; <%} %> <% if(obj.getNxtApprSplitting() > 1){ %> <img
															alt="Trang Truoc" src="../images/prev.gif"
															style="cursor: pointer;"
															onclick="Prev('pxkForm', 'prev')"> &nbsp; <%}else{ %>
															<img alt="Trang Truoc" src="../images/prev_d.gif">
															&nbsp; <%} %> <%
													int[] listPage = obj.getNextSplittings();
													for(int i = 0; i < listPage.length; i++){
												%> <% 							
											
												if(listPage[i] == obj.getNxtApprSplitting()){ %> <a
															style="color: white;"><%= listPage[i] %>/ <%=obj.getTheLastSplitting() %></a>
															<%}else{ %> <a
															href="javascript:View('pxkForm', <%= listPage[i] %>, 'view')"><%= listPage[i] %></a>
															<%} %> <input type="hidden" name="list"
															value="<%= listPage[i] %>" /> &nbsp; <%} %> <% if(obj.getNxtApprSplitting() < obj.getTheLastSplitting()){ %>
															&nbsp; <img alt="Trang Tiep" src="../images/next.gif"
															style="cursor: pointer;"
															onclick="Next('pxkForm', 'next')"> &nbsp; <%}else{ %>
															&nbsp; <img alt="Trang Tiep" src="../images/next_d.gif">
															&nbsp; <%} %> <%if(obj.getNxtApprSplitting() == obj.getTheLastSplitting()) {%>
															<img alt="Trang Cuoi" src="../images/last.gif">
															&nbsp; <%}else{ %> <img alt="Trang Cuoi"
															src="../images/last.gif" style="cursor: pointer;"
															onclick="View('pxkForm', -1, 'view')"> &nbsp; <%} %>
														</TD>
													</tr>
												</TABLE></TD>
										</TR>
									</TABLE>
								</FIELDSET></TD>
						</TR>
					</TABLE> <!--end body Dossier-->
				</TD>
			</TR>
		</TABLE>
	</form>
</BODY>
</HTML>
<%
	try
	{
		if(!(nvgn == null))
			nvgn.close();
		if(rs!=null){
			rs.close();
		}
		if(obj != null){
			obj.DBclose(); 
			obj = null
		;}
		util=null;

		session.setAttribute("obj",null);
		
	}catch(Exception  e){}
	}
%>