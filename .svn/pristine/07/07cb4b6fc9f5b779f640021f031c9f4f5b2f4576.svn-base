<%@page import="java.sql.ResultSet"%>
<%@page import="java.text.DecimalFormat"%>
<%@page import="java.text.NumberFormat"%>
<%@page import="geso.dms.distributor.beans.reports.IBCDoanhSoTheoKH"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page  import = "geso.dms.center.util.*" %>
<% NumberFormat formatter = new DecimalFormat("#,###,###");
NumberFormat formatter1 = new DecimalFormat("#,###,###.00");
%>
<%	String userId = (String) session.getAttribute("userId");  
	String userTen = (String) session.getAttribute("userTen");  	
	String nppId = (String) session.getAttribute("nppId");  	
	String sum = (String) session.getAttribute("sum");
	String loi=(String)session.getAttribute("loi");
	IBCDoanhSoTheoKH obj = (IBCDoanhSoTheoKH) session.getAttribute("obj");
	String loai=(String)session.getAttribute("loai");	
	String tt = (String)session.getAttribute("tt");
	
	ResultSet rsNVBH = obj.getRsNVBH();
	ResultSet rsChiNhanh = obj.getRsChiNhanh();	
	ResultSet vungRs = obj.getVung();
	ResultSet khuvucRs = obj.getKhuvuc();
	ResultSet tinhthanhRs = obj.getTinhthanh();
	ResultSet rslist = obj.getRsBCDoanhSoTheoKH();
	ResultSet groupList = obj.rsBCDoanhSoTheoKHGroup();
	Utility util = (Utility) session.getAttribute("util");
	String url="";
	url = util.getUrl("BCDoanhSoTheoKHSvl","&tt=1&loai=KH");
	if(!util.check(userId, userTen, sum)){
		response.sendRedirect(request.getContextPath() + "/redirect.jsp");
	}else{ %>


<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"> <% Utility Utilback = new Utility(); %>

<HTML>
<HEAD>
<TITLE><%= getServletContext().getInitParameter("TITLENAME") %></TITLE>
	<link type="text/css" rel="stylesheet" href="../css/mybutton.css">
<META http-equiv="Content-Style-Type" content="text/css">
<LINK rel="stylesheet" href="../css/main.css" type="text/css">
<LINK rel="stylesheet" href="../css/calendar.css" type="text/css">
<script type="text/javascript"	src="../scripts/jquery.min.1.7.js"></script>
<link href="../css/jquery-ui.css" rel="stylesheet" type="text/css" />
<script src="../scripts/ui/jquery.ui.core.js" type="text/javascript"></script>
<script src="../scripts/ui/jquery.ui.widget.js" type="text/javascript"></script>
<script src="../scripts/ui/jquery.ui.datepicker.js"
	type="text/javascript"></script>
<link href="../css/select2.css" rel="stylesheet" />
<script src="../scripts/select2.js"></script>

<script type="text/javascript">
		$(document).ready(function() {		
			$( ".days" ).datepicker({			    
					changeMonth: true,
					changeYear: true				
			});
			$("select").select2();
        }); 
		
		
    </script>

<script type="text/javascript">
function submitform()
{	
	document.forms['rpForm'].dataerror.value="";
	document.forms['rpForm'].action.value= 'taomoi';
	document.forms['rpForm'].submit();
}

function search()
{
	document.forms['rpForm'].action.value= 'search';
	document.forms["rpForm"].submit();
}
</SCRIPT>
</HEAD>
<BODY leftmargin="0" bottommargin="0" topmargin="0" rightmargin="0">
<form name="rpForm" method="post" action="../../BCDoanhSoTheoKHSvl">

<% redis.clients.jedis.Jedis jedis =  geso.dms.center.util.Utility.getJedis(); %><% geso.dms.center.util.Csrf csdr=new geso.dms.center.util.Csrf(request,response,true,false,true);%>
 <input type="hidden" name="<%=csdr.get_tokenName() %>" value='<%=csdr.get_tokenValue() %>'>
<input type="hidden" name="userId" value= <%= userId %> >
<input type="hidden" name="action" value='1'>
<input type="hidden" name="loai" id="loai" value= <%= loai %>>
<input type="hidden" name="tt" id="tt" value= <%= tt %>>


<TABLE width="100%" border="0" cellspacing="0" cellpadding="0"
	height="100%">
	
	<TR>
		<TD colspan="4" align='left' valign='top' bgcolor="#FFFFFF">
			<TABLE width="100%" cellpadding="0" cellspacing="1">		
				<TR>
					<TD width="100%" align="left">
					  <table width="100%" border="0" cellpadding="0" cellspacing="0">
						  <tr height="22">
						  <% if(loai.equals("KH")){%>
						   <TD align="left" colspan="2" class="tbnavigation">&nbsp;<%=url %> </TD>
   						<%}else if(loai.equals("TTDSQ")) { %>
   						<%  url="";%>
							<% url = util.getUrl("BCDoanhSoTheoKHSvl","");%>
   							<TD align="left" colspan="2" class="tbnavigation">&nbsp;<%=url %> </TD>
   						<%} else {%>
   						<%  url="";%>
							<% url = util.getUrl("BCDoanhSoTheoKHSvl","&tt=1&loai=LKH");%>
   								<TD align="left" colspan="2" class="tbnavigation">&nbsp;<%=url %> </TD>
   						<%} %>
   						
						   <TD colspan="2" align="right" class="tbnavigation"><%=Utility.GLanguage("Chào mừng bạn",session,jedis) %>  <%=userTen %>&nbsp;</TD>
						  </tr>
 					  </table>					</TD>
				</TR>
				<tr>
					<TD align="left" colspan="4" class="legendtitle">
						<FIELDSET>
						<LEGEND class="legendtitle"><%=Utility.GLanguage("Báo lỗi nhập liệu",session,jedis) %></LEGEND>
	    				<textarea name="dataerror" id="dataerror"  style="width: 100% ; color:#F00 ; font-weight:bold"  style="width: 100% ; color:#F00 ; font-weight:bold" readonly="readonly" rows="1"><%=loi%></textarea>
						</FIELDSET>
				   </TD>
				</tr>	
				<TR>
					<TD>
					<TABLE border="0" width="100%" cellpadding="0" cellspacing="0">
						<TR>
							<TD width="100%" align="center" >
							<FIELDSET>
							  <LEGEND class="legendtitle">Điều kiện</LEGEND>
							<TABLE  width="100%" cellpadding="6" cellspacing="0">
							
								<TR>
									<TD class="plainlabel"  width="10%"><%=Utility.GLanguage("Từ ngày",session,jedis) %></TD>
									<TD class="plainlabel" width="20%"><input type="text" name="tungay"
										class="days" value='<%=obj.getTungay()%>'  /></TD>	
										
									<TD class="plainlabel"  width="10%"><%=Utility.GLanguage("Đến ngày",session,jedis) %></TD>
									<TD class="plainlabel"><input type="text" name="denngay"
										class="days" value='<%=obj.getDenngay()%>'  /></TD>							
								</TR>
								
								
								
								<tr>
								<%if(tt.equals("1")){ %>
								
								<TD class="plainlabel">Miền</TD>
									<TD class="plainlabel">
										<select name="vungId" id="vungId" onChange="search();"  style="width:202px" class = "select2">
											<option value="" selected>All</option>
											<% if(vungRs != null)
											   while(vungRs.next()){
											   if(vungRs.getString("pk_seq").equals(obj.getvungId()))
											   { %>
											   <option value="<%= vungRs.getString("pk_seq") %>" selected><%=vungRs.getString("ten") %></option>
											   <%}else { %>
											   <option value="<%= vungRs.getString("pk_seq") %>"><%=vungRs.getString("ten") %></option>
											<%} }%>
										</select>
										</TD>
									<TD class="plainlabel">Địa bàn</TD>
									<TD class="plainlabel">
										<select name="tinhthanhId" id="tinhthanhId" style="width:202px" class = "select2" onChange ="search();">
												<option value="" selected>All</option>
												<% if(tinhthanhRs != null)
												   while(tinhthanhRs.next()){
												   if(tinhthanhRs.getString("pk_seq").equals(obj.getTinhthanhId()))
												   { %>
												   <option value="<%= tinhthanhRs.getString("pk_seq") %>" selected><%=tinhthanhRs.getString("ten") %></option>
												   <%}else { %>
												   <option value="<%= tinhthanhRs.getString("pk_seq") %>"><%=tinhthanhRs.getString("ten") %></option>
												<%} }%>
										</select>
									</TD>
								</tr>
								
								
								<TR>
									
									<TD class="plainlabel">Chi nhánh/ Đối tác</TD>
										<TD class="plainlabel"><select name="chinhanhId" id="chinhanhId" style="width:200px;" onchange="search();" >
												<option value="" selected></option>
												<%
													if (rsChiNhanh != null)
														while (rsChiNhanh.next()) {
															if (rsChiNhanh.getString("pk_seq").equals(obj.getChinhanhId())) {
												%>
												   <option value="<%=rsChiNhanh.getString("pk_seq")%>" selected><%=rsChiNhanh.getString("ten")%></option>
												   <%
												   	} else {
												   %>
												   <option value="<%=rsChiNhanh.getString("pk_seq")%>"><%=rsChiNhanh.getString("ten")%></option>
												<%
													}
														}
												%>
										</select>
										</TD>		
										
										<TD class="plainlabel"><%=Utility.GLanguage("NHÂN VIÊN BÁN HÀNG",session,jedis) %></TD>
										<TD class="plainlabel"><select name="tdvId" id="tdvId" style="width:200px;" onchange="search();" >
												<option value="" selected></option>
												<%
													if (rsNVBH != null)
														while (rsNVBH.next()) {
															if (rsNVBH.getString("pk_seq").equals(obj.getTdvId())) {
												%>
												   <option value="<%=rsNVBH.getString("pk_seq")%>" selected><%=rsNVBH.getString("ten")%></option>
												   <%
												   	} else {
												   %>
												   <option value="<%=rsNVBH.getString("pk_seq")%>"><%=rsNVBH.getString("ten")%></option>
												<%
													}
														}
												%>
										</select>
										</TD>	
										
										
															
									</TR>								
								<%} %>
								
								<%-- <TR>
									<TD class="plainlabel">Doanh số tính theo </TD>
									<TD class="plainlabel" colspan="3" >
							<%
								if(obj.getMucCN_DT().equals("1")){ %>
								<input type="checkbox" name="cndt" value="1" checked="checked"  /> CN/DT &nbsp; &nbsp;
							<% }else { %>
								    <input type="checkbox" name="cndt" value="1"  /> CN/DT &nbsp; &nbsp;
							 <%} %>
										
									<%
								if(obj.getMuc_KhachHang().equals("1")){ %>
								<input type="checkbox" name="kh" value="1" checked="checked"  /> Khách Hàng &nbsp; &nbsp;
							<% }else { %>
								    <input type="checkbox" name="kh" value="1"  /> Khách Hàng &nbsp; &nbsp;
							 <%} %>
										
									</TD>
									
								</TR> --%>
								
										<TR>
									<TD colspan="4" class="plainlabel">
									<%if(tt.equals("1")){ %>
									<a class="button2" href="javascript:search()" ><img style="top: -4px;" src="../images/button.png" alt="">Lọc</a>&nbsp;&nbsp;&nbsp;&nbsp;                                    
									<%} %>
									<a class="button2" href="javascript:submitform()" ><img style="top: -4px;" src="../images/button.png" alt=""><%=Utility.GLanguage("Tạo báo cáo",session,jedis) %></a>&nbsp;&nbsp;&nbsp;&nbsp;                                    </TD>
								</TR>
							</TABLE>
							</FIELDSET>						
							</TD>
						</TR>
					</TABLE>
					<%if(tt.equals("1")&& loai.equals("LKH")){ %>
					<TABLE width="100%" cellpadding="0" cellspacing="1">
			    	<TR>
						<TD align="left" >
							<FIELDSET><LEGEND class="legendtitle">&nbsp;Doanh thu&nbsp;&nbsp;&nbsp;</LEGEND>
							<TABLE class="" width="100%" border="0" cellpadding="0" cellspacing="0">
								<TR>
									<TD width="98%">
										<TABLE width="100%" border="0" cellpadding="6">
											<TR class="tbheader">
												<TH width="4%" rowspan="2">STT</TH>
												<TH width="6%" rowspan="2">Miền</TH>
											    <TH width="7%" rowspan="2">Tỉnh/Tp </TH>
											    <TH width="18%" rowspan="2"><%=Utility.GLanguage("Nhà phân phối",session,jedis) %></TH>
											    <TH width="18%" colspan="2">Bán lẻ</TH>
											  	<TH width="18%" colspan="2">Bán buôn</TH>
											  	<TH width="18%" colspan="2">Bán buôn- Bán lẻ</TH>
											  	<TH width="11%" rowspan="2">DS Tổng(%)</TH>
										  	</TR>
										  	<TR class="tbheader">
											  	<TH width="9%">DT</TH>										
											  	<TH width="9%">Tỷ lệ/DT (%)</TH>
											  	<TH width="9%">DT</TH>										
											  	<TH width="9%">Tỷ lệ/DT (%)</TH>
											  	<TH width="9%">DT</TH>										
											  	<TH width="9%">Tỷ lệ/DT (%)</TH>			
										  	</TR>
										<% 						
										int m = 0;
										String lightrow = "tblightrow";
										String darkrow = "tbdarkrow";
										try{
											if(rslist != null)
											while (rslist.next()){
												double doanhsobl = rslist.getDouble("bl");
												double doanhsobb = rslist.getDouble("bb");
												double doanhsobbbl = rslist.getDouble("bbbl") + rslist.getDouble("blbb");
												
												double doanhso = doanhsobl + doanhsobb + doanhsobbbl;							    				
							    				double tilebl = doanhsobl / doanhso * 100;	
							    				double tilebb = doanhsobb / doanhso * 100;
							    				double tilebbbl = doanhsobbbl / doanhso * 100;
												if (m % 2 != 0) {%>						
													<TR class= <%=lightrow%> >
												<%} else {%>
													<TR class= <%= darkrow%> >
												<%}%>
													<TD align="center"><%=m+1%></TD>
													<TD align="center"><%=rslist.getString("VUNG")%></TD>
													<TD align="center"><%=rslist.getString("TENTINH")%></TD>
													<TD align="left"><div align="left"><%=rslist.getString("ten") %></div></TD>                                   
													<TD align="right"><%=formatter.format(doanhsobl)%></TD>
													<TD align="center"><%=formatter.format(tilebl)%></TD>
													<TD align="right"><%=formatter.format(doanhsobb)%></TD>
													<TD align="center"><%=formatter.format(tilebb)%></TD>
													<TD align="right"><%=formatter.format(doanhsobbbl)%></TD>
													<TD align="center"><%=formatter.format(tilebbbl)%></TD>
													<TD align="right"><%=formatter.format(doanhso) %></TD>
												</TR>
												<%m++; } 
											}catch(Exception er){} %>
								
											<TR>	
												<TD height="" colspan="11" align="center" class="tbfooter">&nbsp;</TD>
											</TR>
									</TABLE>
								</TD>
							</TR>
						</TABLE>
						</FIELDSET>
					</TD>
				</TR>
		</TABLE>
					<%} %>
					
					<%if(tt.equals("1")&& loai.equals("KH")){ %>
					<TABLE width="100%" cellpadding="0" cellspacing="1">
			    	<TR>
						<TD align="left" >
							<FIELDSET><LEGEND class="legendtitle">&nbsp;Doanh số&nbsp;&nbsp;&nbsp;</LEGEND>
							<TABLE class="" width="100%" border="0" cellpadding="0" cellspacing="0">
								<TR>
									<TD width="98%">
										<TABLE width="100%" border="0" cellpadding="6">
											<TR class="tbheader">
												<TH width="4%" rowspan="2">STT</TH>
												<TH width="6%" rowspan="2">Miền</TH>
											    <TH width="7%" rowspan="2">Tỉnh/Tp </TH>
											    <TH width="18%" rowspan="2"><%=Utility.GLanguage("Nhà phân phối",session,jedis) %></TH>
											    <TH width="18%" colspan="2"> DS nhóm sp "SK XANH"</TH>
											  	<TH width="18%" colspan="2"> DS HHDN + BOGA</TH>
											  	<TH width="18%" colspan="2"> DS nhóm sp KHÁC</TH>
											  	<TH width="11%" rowspan="2">DS Tổng(%)</TH>
										  	</TR>
										  	<TR class="tbheader">
											  	<TH width="9%">DS</TH>										
											  	<TH width="9%">Tỷ lệ/DS (%)</TH>
											  	<TH width="9%">DS</TH>										
											  	<TH width="9%">Tỷ lệ/DS (%)</TH>
											  	<TH width="9%">DS</TH>										
											  	<TH width="9%">Tỷ lệ/DS (%)</TH>			
										  	</TR>
										<% 						
										int m = 0;
										String lightrow = "tblightrow";
										String darkrow = "tbdarkrow";
										try{
											if(groupList != null)
											while (groupList.next()){
												double dsXanh = groupList.getDouble("DsNhomxanh");
												double dsBoga = groupList.getDouble("Dsnhomhh");
												double dsKhac = groupList.getDouble("dsnhomkhac");
												
												double doanhso = dsXanh + dsBoga + dsKhac;							    				
							    				double tileXanh = dsXanh / doanhso * 100;							    				
							    				double tileBoga = dsBoga / doanhso * 100;
							    				double tileKhac = dsKhac / doanhso * 100;
												if (m % 2 != 0) {%>						
													<TR class= <%=lightrow%> >
												<%} else {%>
													<TR class= <%= darkrow%> >
												<%}%>
													<TD align="center"><%=m+1%></TD>
													<TD align="center"><%=groupList.getString("VUNG")%></TD>
													<TD align="center"><%=groupList.getString("TENTINH")%></TD>
													<TD align="left"><div align="left"><%=groupList.getString("nppten") %></div></TD>                                   
													<TD align="right"><%=formatter.format(dsXanh)%></TD>
													<TD align="center"><%=formatter1.format(tileXanh)%></TD>
													<TD align="right"><%=formatter.format(dsBoga)%></TD>
													<TD align="center"><%=formatter1.format(tileBoga)%></TD>
													<TD align="right"><%=formatter.format(dsKhac)%></TD>
													<TD align="center"><%=formatter1.format(tileKhac)%></TD>
													<TD align="right"><%=formatter.format(doanhso) %></TD>
												</TR>
												<%m++; } 
											}catch(Exception er){} %>
								
											<TR>	
												<TD height="" colspan="11" align="center" class="tbfooter">&nbsp;</TD>
											</TR>
									</TABLE>
								</TD>
							</TR>
						</TABLE>
						</FIELDSET>
					</TD>
				</TR>
		</TABLE>
					<%} %>
					
					
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
	}%>