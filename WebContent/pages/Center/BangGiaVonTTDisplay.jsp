<%@page import="geso.dms.center.beans.banggiavontt.IBangGiaVonTT_SanPham"%>
<%@page import="java.util.List"%>
<%@page import="geso.dms.center.beans.banggiavontt.IBangGiaVonTT"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page  import = "geso.dms.center.beans.banggiamuanpp.IBanggiamuanpp" %>
<%@ page  import = "java.sql.ResultSet" %>
<%@ page  import = "geso.dms.center.util.*" %>
<%	String userId = (String) session.getAttribute("userId");  
	String userTen = (String) session.getAttribute("userTen");  	
	String sum = (String) session.getAttribute("sum");
	String check1=(String)session.getAttribute("check");
	Utility util = (Utility) session.getAttribute("util");
	if(!util.check(userId, userTen, sum)){
		response.sendRedirect(request.getContextPath() + "/redirect.jsp");
	}else{ %>

<% IBangGiaVonTT bgvon = (IBangGiaVonTT)session.getAttribute("obj"); %>
<% List<IBangGiaVonTT_SanPham> listsanpham=bgvon.getListBangGia_SanPham(); %>
<% ResultSet dvkd = (ResultSet)session.getAttribute("rs_dvkd"); %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"> <% Utility Utilback = new Utility(); %>
<HTML>
<HEAD>
<TITLE><%= getServletContext().getInitParameter("TITLENAME") %></TITLE>
<META http-equiv="Content-Type" content="text/html; charset=utf-8">
<META http-equiv="Content-Style-Type" content="text/css">
<link type="text/css" rel="stylesheet" href="../css/mybutton.css">
<LINK rel="stylesheet" href="../css/main.css" type="text/css">

<SCRIPT language="javascript" type="text/javascript">
 </SCRIPT>

</HEAD>
<BODY leftmargin="0" bottommargin="0" topmargin="0" rightmargin="0">
<form name="bgmuanppForm" method="post" action="../../BangGiaVonTTNewSvl">
<input type="hidden" name="userId" value='<%= userId%>'>
<input type="hidden" name="userTen" value='<%= userTen%>'>
<input type="hidden" name="action" value='1'>
<input type="hidden" name="id" value='<%=bgvon.getId() %>'>
<input type="hidden" name="tenform" value='updateform'>
<input type="hidden" name="check" value='<%=check1%>'>
<TABLE width="100%" border="0" cellspacing="0" cellpadding="0"	>
	
	<TR>
		<TD colspan="4" align='left' valign='top' bgcolor="#FFFFFF">
			<TABLE width="100%" cellpadding="0" cellspacing="1">
				<TR>
					<TD align="left" class="tbnavigation">
					   <table width="100%" border="0" cellpadding="0" cellspacing="0">
						   <tr height="22">
 							   <TD align="left" colspan="2" class="tbnavigation">&nbsp;Thi???t l???p d??? li???u n???n &gt; D??? li???u n???n s???n ph???m &gt; B???ng gi?? v???n trung t??m &gt; Xem b???ng gi??</TD>
 							   <TD colspan="2" align="right" class="tbnavigation"><%=Utility.GLanguage("Ch??o m???ng b???n",session,jedis) %>  <%=userTen  %>&nbsp;  </TD>
					     </tr>
						</table>
					 </TD>
				  </TR>	
		  	</TABLE>


			<TABLE width="100%" cellpadding="0" cellspacing="1">
			<TR ><TD >
				<TABLE width="100%" border="0" cellpadding="0" cellspacing="0">
					<TR class = "tbdarkrow">
						<TD width="30" align="left"><A href="../../RouterSvl?task=<%= Utilback.dongMa(getServletContext().getInitParameter("RedirectNoScript") +"BangGiaVonTTSvl?userId="+userId+"") %>" ><img src="../images/Back30.png" alt="Quay ve"  title="Quay ve" border="1" longdesc="Quay ve" style="border-style:outset"></A></TD>
					    <TD width="2" align="left" ></TD>
					</TR>
				</TABLE>
			</TD></TR>
			</TABLE>

		<TABLE width="100%"  border = "0" cellspacing="0" cellpadding="0">
		 	<TR>
				<TD >
			        <FIELDSET>
			        <LEGEND class="legendtitle">&nbsp;B???ng gi?? b??n cho ?????i t??c </LEGEND>
					<TABLE width="100%"cellpadding="0" cellspacing="1">
						<TR>
							<TD>				
								<TABLE border="0" width="100%" cellpadding="0" cellspacing="0">
									<TR>
										<TD width="100%" align="center">
											<TABLE class="tblight" width="100%" cellpadding="6" cellspacing="0">
												<TR>
													<TD width="24%" class="plainlabel">T??n b???ng gi?? <FONT class="erroralert">*</FONT></TD>
													<TD width="68%" class="plainlabel"><INPUT  readonly="readonly" name="bgTen" value="<%= bgvon.getTen() %>"
										type="text"  size="40"/></TD>
												</TR>
												<TR>
								    				<TD class="plainlabel">????n v??? kinh doanh</TD>
								      				<TD class="plainlabel">
								      				<SELECT name="dvkdId" onChange = "loadhang();" disabled="disabled">								      
								  	 					<option value =""></option>
								  	 					<% try{ while(dvkd.next()){ 
								  	 							if(dvkd.getString("pk_seq").equals(bgvon.getDvkdId())){ %>
								      								<option value='<%=dvkd.getString("pk_seq")%>' selected><%=dvkd.getString("ten") %></option>
								      						   <%}else{ %>
								     								<option value='<%=dvkd.getString("pk_seq")%>' ><%=dvkd.getString("ten")  %></option>
								     							<%}}}catch(java.sql.SQLException e){} %>	
								     	
													</SELECT></TD>
												</TR>												
											</TABLE>								
										</TD>
									</TR>
								</TABLE>
							</TD>
						</TR>
				</TABLE>
				</FIELDSET>
				<TABLE cellpadding="0" cellspacing="1" width="100%">
					<TR>
						<TD >
												<TABLE width="100%" border="0" cellspacing="1" cellpadding="0">
								<TR class="tbheader">
									<TH width="5%">S??? TT </TH>
									<TH width="20%">M?? s???n ph???m </TH>
									<TH width="40%">T??n s???n ph???m</TH>	
									<TH width="5%">????n v???</TH>
									<TH width="10%">Gi?? v???n</TH>					
									<TH width="10%">Ch???n b??n 
									<input type="checkbox" name="chon" onclick="checkall(this.value);">
									</TH>
								</TR>
								<%
								int j = 0;
								String lightrow = "tblightrow";
								String darkrow = "tbdarkrow";
								while(j<listsanpham.size()){
									IBangGiaVonTT_SanPham sanpham=listsanpham.get(j);
										if (j % 2 != 0) {%>						
											<TR class= <%=lightrow%> >
									    <%} else {%>
											<TR class= <%= darkrow%> >
										<%}%>
										<TD align="center"><%= (j+1) %> </TD>
											<TD align="center"><div align="left"><input type="hidden" name="idsp" value="<%=sanpham.getSanPhamID() %>" >  <input type="hidden" name="masp" value="<%=sanpham.getMaSanPham() %>" ><%=sanpham.getMaSanPham() %>  </div></TD>
											<TD align="center"><div align="left"><input type="hidden" name="tensp" value="<%=sanpham.getTenSanPham() %>" > <%=sanpham.getTenSanPham() %> </div></TD>
											<TD align="center"><input type="hidden" name="dvt" value="<%=sanpham.getDonViTinh() %> "> <%=sanpham.getDonViTinh() %> </TD>
											<TD align="left"><input type='hidden' name=giavon value="<%= Math.round(sanpham.getGiaVon()) %>"/><%= Math.round(sanpham.getGiaVon()) %></TD>
											<TD align="center">	
											<% if(sanpham.getChonBan().equals("1")){ %>										
												<input type="checkbox" name="chonban" checked="checked" onchange="recheck(this.value)" >		
												<%}else{ %>
												<input type="checkbox" name="chonban" >
												<%} %>	
												<input type="hidden" name="valuechonban"  value='<%= sanpham.getChonBan() %>' >								
											</TD>
												
							     		<%j++;
										 
								}%>

							</TABLE>

						</TD>
					</TR>
				</TABLE>
			</TD>
			</TR>
		</TABLE>
	</TR>
</TABLE>
</FORM>
</BODY>
</HTML>
<%}%>