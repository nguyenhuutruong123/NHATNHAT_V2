<%@page import="java.sql.SQLException"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page  import = "java.util.Iterator" %>
<%@ page  import = "java.util.List" %>
<%@ page  import = "java.util.ArrayList" %>
<%@ page  import = "geso.dms.distributor.beans.khachhangtt.*" %>
<%@ page  import = "java.sql.ResultSet" %>
<%@ page  import = "geso.dms.center.util.*" %>
<%	String userId = (String) session.getAttribute("userId");  
	String userTen = (String) session.getAttribute("userTen");  	
	String sum = (String) session.getAttribute("sum");
	Utility util = (Utility) session.getAttribute("util");
	String url="";
	url = util.getUrl("DanhmuckhachhangTTSvl","");
	if(!util.check(userId, userTen, sum)){
		response.sendRedirect(request.getContextPath() + "/redirect.jsp");
	}else{ 
	int[] quyen = new  int[6];
		quyen = util.Getquyen("DanhmuckhachhangTTSvl","",userId);%>

<% IKhachhangTTList obj = (IKhachhangTTList)session.getAttribute("obj"); %>
<% ResultSet khlist = (ResultSet) obj.getKhList(); %>
<% ResultSet hch = (ResultSet)obj.getHangcuahang(); %>
<% ResultSet kbh = (ResultSet)obj.getKenhbanhang();  %>
<% ResultSet vtch = (ResultSet)obj.getVitricuahang();  %>
<% ResultSet lch = (ResultSet)obj.getLoaicuahang(); %>
<% ResultSet diadiemRs = (ResultSet)obj.getDiadiemRs()  ; %>
<% ResultSet tbhRs = (ResultSet)obj.getTbhRs()  ; %>
<% ResultSet ddkdRs = (ResultSet)obj.getDdkdRs()  ; %>
<% ResultSet nppRs = (ResultSet)obj.getNppRs()  ; %>
<% ResultSet mien = (ResultSet)obj.getMien()  ; %>
<% ResultSet vung = (ResultSet)obj.getVung() ; %>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"> <% Utility Utilback = new Utility(); %>
<HTML>
<HEAD>
<TITLE><%= getServletContext().getInitParameter("TITLENAME") %></TITLE>
<META http-equiv="Content-Type" content="text/html; charset=utf-8">
<META http-equiv="Content-Style-Type" content="text/css">
<LINK rel="stylesheet" href="../css/main.css" type="text/css">
<LINK rel="stylesheet" href="../css/calendar.css" type="text/css">
	<link type="text/css" rel="stylesheet" href="../css/mybutton.css">
<script type="text/javascript" language="JavaScript" src="../scripts/simplecalendar.js"></script>



	<script type="text/javascript" src="../scripts/Timepicker/jquery.min.js"></script>
  	<script type="text/javascript" src="../scripts/Timepicker/jquery-ui.min.js"></script>
  	<script type="text/javascript" src="../scripts/phanTrang.js"></script>
	<script type="text/javascript" src="../scripts/jquery.min.1.7.js"></script>
	<link href="../css/jquery-ui.css" rel="stylesheet" type="text/css" />
	<script src="../scripts/ui/jquery.ui.core.js" type="text/javascript"></script>
	<script src="../scripts/ui/jquery.ui.widget.js" type="text/javascript"></script>
	<script type="text/javascript" src="../scripts/phanTrang.js"></script>
	<script src="../scripts/ui/jquery.ui.datepicker.js" type="text/javascript"></script>




<SCRIPT language="javascript" type="text/javascript">
$(document).ready(function() {		
	$( ".days" ).datepicker({			    
			changeMonth: true,
			changeYear: true				
	});            
}); 		


function clearform()
{
	document.khForm.khTen.value = "";
//	document.khForm.hchTen.selectedIndex = 0;
	document.khForm.kbhTen.selectedIndex = 0;
// 	document.khForm.vtchTen.selectedIndex = 0;
// 	document.khForm.lchTen.selectedIndex = 0;
	document.khForm.maFAST.value = "";
	document.khForm.diachi.value = "";
	document.khForm.ddkdId.value = "";
	document.khForm.trangthai.value = "";
	document.khForm.tbhId.value = "";
	document.khForm.tungay.value = "";
	document.khForm.denngay.value = "";
	document.khForm.loaikh.value = "";
	document.khForm.hopdong.value = "";
	submitform();

}

function submitform()
{
	document.forms['khForm'].action.value= 'search';
	document.forms['khForm'].submit();
}

function newform()
{
	document.forms['khForm'].action.value= 'new';
	document.forms['khForm'].submit();
}


function xuatExcel()
{
	document.forms['khForm'].action.value= 'excel';
	document.forms['khForm'].submit();
	
}


</SCRIPT>
</HEAD>

<BODY leftmargin="0" bottommargin="0" topmargin="0" rightmargin="0">
<form name="khForm" method="post" action="../../DanhmuckhachhangTTSvl">
<input type="hidden" name="userId" value="<%= userId %>" >
<input type="hidden" name="action" value="1" >
<input type="hidden" name="currentPage" value="<%= obj.getCurrentPage() %>" >

<TABLE width="100%" border="0" cellspacing="0" cellpadding="0" 	height="100%">
	<TR>
		<TD colspan="4" align='left' valign='top' bgcolor="#FFFFFF">
			<TABLE width="100%" border="0" cellpadding="0" cellspacing="2">
				<TR>
					<TD align="left" class="tbnavigation">
					   <table width="100%" border="0" cellpadding="0" cellspacing="0">
						  	<tr height="22">
							   <TD align="left" colspan="2" class="tbnavigation">&nbsp;<%=url %></TD>
							   <TD colspan="2" align="right" class="tbnavigation">Ch??o m???ng  <%=userTen%> &nbsp;</TD>
							</tr>
						</table>
					</TD>
		  		</TR>	
			</TABLE>
			<TABLE width="100%" border="0" cellpadding="0" cellspacing="0">
				<TR>
					<TD >
						<FIELDSET><LEGEND class="legendtitle">&nbsp;<%=Utility.GLanguage("Ti??u ch?? t??m ki???m",session,jedis) %>&nbsp;</LEGEND>
							<TABLE class="tblight" width="100%" cellspacing="0" cellpadding="6">
								
								<TR>
									<TD width="130px" class="plainlabel">M?? / T??n kh??ch h??ng</TD>
								    <TD width="300px" class="plainlabel">
										<INPUT name="khTen" type="text" value="<%= obj.getTen() %>" size="40" onChange = "submitform();">
								  	</TD>
								  	<TD width="120px" class="plainlabel" valign="top" ><%=Utility.GLanguage("K??nh b??n h??ng",session,jedis) %></TD>
								  	  <TD class="plainlabel" valign="top">
										  	<SELECT name="kbhTen" onChange = "submitform();">
										    	<option value=""> </option>
											    <% try{ while(kbh.next()){ 
										    			if(kbh.getString("kbhId").equals(obj.getKbhId())){ %>
										      				<option value='<%=kbh.getString("kbhId")%>' selected><%=kbh.getString("kbhTen") %></option>
										      			<%}else{ %>
										     				<option value='<%=kbh.getString("kbhId")%>'><%=kbh.getString("kbhTen") %></option>
										     			<%}}}catch(java.sql.SQLException e){} %>
											 </SELECT>
									</TD>
								  
								</TR>
								
								<TR>
									<TD class="plainlabel" valign="top" style="display:none" >M?? DMS</TD>
								    <TD class="plainlabel" valign="top" style="display:none"  >
									<INPUT name="maFAST" type="text" value="<%= obj.getMaFAST() %>" size="40" onChange = "submitform();" style="display:none" >
								  </TD>
											<TD width="120px" class="plainlabel" valign="top" ><%=Utility.GLanguage("NH??N VI??N B??N H??NG",session,jedis) %></TD>
											  	  <TD class="plainlabel" valign="top">
													  	<SELECT name="ddkdId" onChange = "submitform();">
													    	<option value=""> </option>
														    <% try{ while(ddkdRs.next()){ 
													    			if(ddkdRs.getString("ddkdId").equals(obj.getDdkdId()    )){ %>
													      				<option value='<%=ddkdRs.getString("ddkdId")%>' selected>
													      				<%=ddkdRs.getString("ddkdTen") %></option>
													      			<%}else{ %>
													     				<option value='<%=ddkdRs.getString("ddkdId")%>'>
													     				<%=ddkdRs.getString("ddkdTen") %></option>
													     			<%}}}catch(Exception e){ e.printStackTrace(); } %>
														 </SELECT>
												</TD>
												 </TD>
									  <TD width="120px" class="plainlabel" valign="top" >Tuy???n b??n h??ng</TD>
												  	  <TD class="plainlabel" valign="top">
														  	<SELECT name="tbhId" onChange = "submitform();">
														    	<option value=""> </option>
															    <% try{ while(tbhRs.next()){ 
														    			if(tbhRs.getString("tbhId").equals(obj.getTbhId()  )){ %>
														      				<option value='<%=tbhRs.getString("tbhId")%>' selected><%=tbhRs.getString("tbhTen") %></option>
														      			<%}else{ %>
														     				<option value='<%=tbhRs.getString("tbhId")%>'><%=tbhRs.getString("tbhTen") %></option>
														     			<%}}}catch(java.sql.SQLException e){} %>
															 </SELECT>
									 </TD>
					
								</TR>
								
								
								<TR>
								<TD class="plainlabel" valign="top" style="display:none" >?????a ch???</TD>
								    <TD class="plainlabel" valign="top" style="display:none" >
										<INPUT name="diachi" type="text" value="<%= obj.getDiachi() %>" size="40" onChange = "submitform();" style="display:none" >
								 
								  </TR>
								  
								  
								  <TR>
								  	 <TD class="plainlabel" ><%=Utility.GLanguage("T??? ng??y",session,jedis) %> </TD>
								  	 <TD class="plainlabel" >
								  	 	<input type="text" class="days" name="tungay" value="<%= obj.getTungay() %>" maxlength="10" onchange="submitform();" />
								  	 </TD>
								  	 
								  	 <TD class="plainlabel" ><%=Utility.GLanguage("?????n ng??y",session,jedis) %> </TD>
								  	 <TD class="plainlabel" >
								  	 	<input type="text" class="days" name="denngay" value="<%= obj.getDenngay() %>" maxlength="10" onchange="submitform();" />
								  	 </TD>
								  	 
								  	 
								  </TR>
								  
								  <TR>
								  	 <TD class="plainlabel" > Lo???i kh??ch h??ng </TD>
								  	 <TD class="plainlabel" >
								  	 	<select name="loaikh" onchange="submitform();">
								  	 	<% if(obj.getloaiKH().equals("0")){ %>
									  	 		<option></option>
									  	 		<option value="0" selected="selected">B??n l???</option>
									  	 		<option value="1">B??n bu??n</option>
									  	 		<option value="2">B??n bu??n v?? b??n l???</option>
									  	 		<option value="3">B??n l??? v?? b??n bu??n</option>
								  	 		
								  	 	<%} else if(obj.getloaiKH().equals("1")){ %>
								  	 			<option></option>
									  	 		<option value="0">B??n l???</option>
									  	 		<option value="1" selected="selected">B??n bu??n</option>
									  	 		<option value="2">B??n bu??n v?? b??n l???</option>
									  	 		<option value="3">B??n l??? v?? b??n bu??n</option>
								  	 		
								  	 	<%} else if(obj.getloaiKH().equals("2")){ %>
								  	 			<option></option>
									  	 		<option value="0">B??n l???</option>
									  	 		<option value="1">B??n bu??n</option>
									  	 		<option value="2" selected="selected">B??n bu??n v?? b??n l???</option>
									  	 		<option value="3">B??n l??? v?? b??n bu??n</option>
								  	 		
								  	 	<%} else if(obj.getloaiKH().equals("3")){ %>
								  	 			<option></option>
									  	 		<option value="0">B??n l???</option>
									  	 		<option value="1">B??n bu??n</option>
									  	 		<option value="2">B??n bu??n v?? b??n l???</option>
									  	 		<option value="3" selected="selected">B??n l??? v?? b??n bu??n</option>
								  	 		
								  	 	<%} else { %>
								  	 			<option></option>
									  	 		<option value="0">B??n l???</option>
									  	 		<option value="1">B??n bu??n</option>
									  	 		<option value="2">B??n bu??n v?? b??n l???</option>
									  	 		<option value="3">B??n l??? v?? b??n bu??n</option>
								  	 	<%} %>
								  	 	</select>
								  	 </TD>
								  	 	 <TD class="plainlabel" > H???p ?????ng </TD>
								  	 	<TD class="plainlabel" >
								  	 		<select name="hopdong" onchange="submitform();">
								  	 		<%if(obj.getHopdong().equals("0")){ %>
									  	 		<option></option>
									  	 		<option value="0" selected="selected"> C?? h???p ?????ng</option>
									  	 		<option value="1"> Kh??ng c?? h???p ?????ng</option>
									  	 	<%} else if(obj.getHopdong().equals("1")){ %>
									  	 		<option></option>
									  	 		<option value="0"> C?? h???p ?????ng</option>
									  	 		<option value="1" selected="selected"> Kh??ng c?? h???p ?????ng</option>	
									  	 	<% } else{ %>
									  	 		<option></option>
									  	 		<option value="0"> C?? h???p ?????ng</option>
									  	 		<option value="1"> Kh??ng c?? h???p ?????ng</option>
									  	 	<%} %>
								  	 		</select>
								  	 </TD>
								  </TR>
								  
								  <TR>
								
					  				<TD width="120px" class="plainlabel" valign="top" ><%=Utility.GLanguage("Tr???ng th??i",session,jedis) %></TD>
									 <TD  class="plainlabel" valign="top">
										 <SELECT name ="trangthai" onChange = "submitform();">
                               
	                                        <% if (obj.getTrangthai().equals("1")){%>
	                                              <option value="1" selected><%=Utility.GLanguage("Ho???t ?????ng",session,jedis) %></option>
	                                              <option value="0"><%=Utility.GLanguage("Ng??ng ho???t ?????ng",session,jedis) %></option>
	                                              <option value=""></option>
	                                              
	                                        <%}else if(obj.getTrangthai().equals("0")) {%>
	                                              <option value="0" selected><%=Utility.GLanguage("Ng??ng ho???t ?????ng",session,jedis) %></option>
	                                              <option value="1" ><%=Utility.GLanguage("Ho???t ?????ng",session,jedis) %></option>
	                                              <option value="" > </option>
	                                              
	                                        <%}else{%>                                                                                        
	                                              <option value="1" ><%=Utility.GLanguage("Ho???t ?????ng",session,jedis) %></option>
	                                              <option value="0" ><%=Utility.GLanguage("Ng??ng ho???t ?????ng",session,jedis) %></option>
	                                              <option value="" selected> </option>
	                                        <%}%>
	                                     </SELECT>
									 </TD>
									 
									 <TD width="120px" class="plainlabel" valign="top" ><%=Utility.GLanguage("Nh?? ph??n ph???i",session,jedis) %></TD>
											  	  <TD class="plainlabel" valign="top">
													  	<SELECT name="nppId" onChange = "submitform();">
													    	<option value=""> </option>
														    <% try{ while(nppRs.next()){ 
													    			if(nppRs.getString("pk_seq").equals(obj.getNppId()    )){ %>
													      				<option value='<%=nppRs.getString("pk_seq")%>' selected>
													      				<%=nppRs.getString("TEN") %></option>
													      			<%}else{ %>
													     				<option value='<%=nppRs.getString("pk_seq")%>'>
													     				<%=nppRs.getString("TEN") %></option>
													     			<%}}}catch(Exception e){ e.printStackTrace(); } %>
														 </SELECT>
												</TD>
								  </TR>
								  
								  <TR>
								
					  				<TD width="120px" class="plainlabel" valign="top" >Mi???n</TD>
									 <TD  class="plainlabel" valign="top">
										 <SELECT name="vungid" onChange = "submitform();">
													    	<option value=""> </option>
														    <% try{ while(vung.next()){ 
													    			if(vung.getString("pk_seq").equals(obj.getVungid()    )){ %>
													      				<option value='<%=vung.getString("pk_seq")%>' selected>
													      				<%=vung.getString("ten") %></option>
													      			<%}else{ %>
													     				<option value='<%=vung.getString("pk_seq")%>'>
													     				<%=vung.getString("ten") %></option>
													     			<%}}}catch(Exception e){ e.printStackTrace(); } %>
														 </SELECT>
								
									 </TD>
									 
									 <TD width="120px" class="plainlabel" valign="top" >T???nh Th??nh</TD>
											  	  <TD class="plainlabel" valign="top">
													 <SELECT name="mienid" onChange = "submitform();">
													    	<option value=""> </option>
														    <% try{ while(mien.next()){ 
													    			if(mien.getString("pk_seq").equals(obj.getMienid()    )){ %>
													      				<option value='<%=mien.getString("pk_seq")%>' selected>
													      				<%=mien.getString("ten") %></option>
													      			<%}else{ %>
													     				<option value='<%=mien.getString("pk_seq")%>'>
													     				<%=mien.getString("ten") %></option>
													     			<%}}}catch(Exception e){ e.printStackTrace(); } %>
														 </SELECT>
								  
												</TD>
								  </TR>
								
								<TR>
								    <TD class="plainlabel" colspan="4">
								    	<a class="button2" href="javascript:clearform()">
										<img style="top: -4px;" src="../images/button.png" alt=""><%=Utility.GLanguage("Nh???p l???i",session,jedis) %></a>&nbsp;&nbsp;&nbsp;&nbsp;	    
								     	<a class="button2" href="javascript:xuatExcel()"> <img style="top: -4px;" src="../images/button.png" alt=""><%=Utility.GLanguage("Xu???t Excel",session,jedis) %> </a>
                                      
                                     </TD>
								</TR>
							</TABLE>
					  </FIELDSET>
					</TD>	
				</TR>
			</TABLE>
			<TABLE width="100%" border="0" cellspacing="0" cellpadding="2">
				<TR>
					<TD width="100%">
					<FIELDSET>
					<LEGEND class="legendtitle">&nbsp;Kh??ch h??ng &nbsp;&nbsp;&nbsp;
					
					<%-- <%if(quyen[Utility.THEM]!=0){ %>
						<a class="button3" href="javascript:newform()">
	    				<img style="top: -4px;" src="../images/New.png" alt=""><%=Utility.GLanguage("T???o m???i",session,jedis) %> </a> 
    				<%} %>         --%>                   
					
					</LEGEND>
					<TABLE class="" width="100%">
						<TR>
							<TD width="98%">
							<TABLE width="100%" border="0" cellspacing="1" cellpadding="4">
								<TR class="tbheader">
									<TH width="17%"><%=Utility.GLanguage("Nh?? ph??n ph???i",session,jedis) %></TH>
									<TH width="10%">M?? kh??ch h??ng</TH>
									<TH width="17%">T??n kh??ch h??ng</TH>
									<TH width="8%">M?? H??</TH>
									<TH width="12%">Lo???i KH</TH>
									<TH width="10%"><%=Utility.GLanguage("Tr???ng th??i",session,jedis) %></TH>
									<!-- <TH width="25%">?????a ch???</TH> -->
									<TH width="20%"><%=Utility.GLanguage("NH??N VI??N B??N H??NG",session,jedis) %></TH>
									<!-- <TH width="9%"><%=Utility.GLanguage("Ng??y s???a",session,jedis) %></TH>
									<TH width="12%"><%=Utility.GLanguage("Ng?????i s???a",session,jedis) %></TH>  -->
									<!-- <TH width="12%"><%=Utility.GLanguage("T??c v???",session,jedis) %></TH> -->
								</TR>
								
						<%  														
	                        int m = 0;
	                        String lightrow = "tblightrow";
	                        String darkrow = "tbdarkrow";
							if(khlist!=null)
							{%>
							<% try{								
                                while (khlist.next()){
                                    	
                                   	if (m % 2 != 0) {%>                     
                                    	<TR class= <%=lightrow%> >
                                    <%} else {%>
                                       	<TR class= <%= darkrow%> >
                                    	<%}%>
											<TD align="left"><div align="center"><%=khlist.getString("nppTEN") %></div></TD>     
											<TD align="left"><div align="center"><%=khlist.getString("mafast") %></div></TD>                                                   
											<TD><div align="left"><%= khlist.getString("khTen")%></div></TD>
											
											<TD align="left"><%=khlist.getString("mahd")==null?" ":khlist.getString("mahd")%></TD>
											<TD align="left"><%=khlist.getString("loaiCH")==null?" ":khlist.getString("loaiCH")%></TD>  
											
											<%
										
											if (khlist.getString("trangthai").equals("1")){ %>
												<TD align="center"><%=Utility.GLanguage("Ho???t ?????ng",session,jedis) %></TD>
											<%}else{%>
												<TD align="center"><%=Utility.GLanguage("Ng??ng ho???t ?????ng",session,jedis) %></TD>
											<%}%>
											
											<%-- <TD align="left"><%=khlist.getString("diachi")==null?" ":khlist.getString("diachi")%></TD> --%>
											<TD align="left"><%=khlist.getString("tbhTen")==null?" ":khlist.getString("tbhTen")%></TD>
											<%-- <TD align="center"><%=khlist.getString("ngaysua")%></TD>
											<TD align="center"><%=khlist.getString("nguoisua")%></TD> --%>
											<%-- <TD align="center">
											<TABLE border = 0 cellpadding="0" cellspacing="0">
												<TR>
												<TD>
												<%if(quyen[Utility.THEM]!=0){ %>
													<A href = "../../KhachhangUpdateSvl?userId=<%=userId%>&update=<%=khlist.getString("khId") %>"><img src="../images/Edit20.png" alt="Cap nhat" width="20" height="20" longdesc="Cap nhat" border = 0></A>
												<%} %>
												</TD>
												<TD>&nbsp;</TD>
												<TD>
												<%if(quyen[Utility.XOA]!=0){ %>
													<A href = "../../KhachhangSvl?userId=<%=userId%>&delete=<%=khlist.getString("khId") %>"><img src="../images/Delete20.png" alt="Xoa" width="20" height="20" longdesc="Xoa" border=0 onclick="if(!confirm('B???n c?? mu???n Xo?? kh??ch h??ng n??y?')) return false;"></A>
												<%} %>	
													</TD>
												</TR></TABLE>
											</TD> --%>
										</TR>
								<%m++; }}catch(java.sql.SQLException e){e.printStackTrace();}
							}%>
								
										 <tr class="tbfooter" > 
											 <TD align="center" valign="middle" colspan="13" class="tbfooter">
											 <% obj.setNextSplittings(); %>
												 <script type="text/javascript" src="../scripts/phanTrang.js"></script>
												<input type="hidden" name="crrApprSplitting" value="<%= obj.getCrrApprSplitting() %>" >
												<input type="hidden" name="nxtApprSplitting" value="<%= obj.getNxtApprSplitting() %>" >

											 	<%if(obj.getNxtApprSplitting() >1) {%>
													<img alt="Trang Dau" src="../images/first.gif" style="cursor: pointer;" onclick="View(document.forms[0].name, 1, 'view')"> &nbsp;
												<%}else {%>
													<img alt="Trang Dau" src="../images/first.gif" > &nbsp;
													<%} %>
												<% if(obj.getNxtApprSplitting() > 1){ %>
													<img alt="Trang Truoc" src="../images/prev.gif" style="cursor: pointer;" onclick="View(document.forms[0].name, eval(document.forms[0].nxtApprSplitting.value) -1, 'view')"> &nbsp;
												<%}else{ %>
													<img alt="Trang Truoc" src="../images/prev_d.gif" > &nbsp;
												<%} %>
												
												<%
													int[] listPage = obj.getNextSplittings();
													for(int i = 0; i < listPage.length; i++){
												%>
												
												<% 
												
												if(listPage[i] == obj.getNxtApprSplitting()){ %>
												
													<a  style="color:white;"><%= listPage[i] %>/ <%=obj.getTheLastSplitting() %></a>
												<%}else{ %>
													<a href="javascript:View(document.forms[0].name, <%= listPage[i] %>, 'view')"><%= listPage[i] %></a>
												<%} %>
													<input type="hidden" name="list" value="<%= listPage[i] %>" />  &nbsp;
												<%} %>
												
												<% if(obj.getNxtApprSplitting() < obj.getTheLastSplitting()){ %>
													&nbsp; <img alt="Trang Tiep" src="../images/next.gif" style="cursor: pointer;" onclick="View(document.forms[0].name, eval(document.forms[0].nxtApprSplitting.value) +1, 'view')"> &nbsp;
												<%}else{ %>
													&nbsp; <img alt="Trang Tiep" src="../images/next_d.gif" > &nbsp;
												<%} %>
												<%if(obj.getNxtApprSplitting() == obj.getTheLastSplitting()) {%>
											   		<img alt="Trang Cuoi" src="../images/last.gif" > &nbsp;
										   		<%}else{ %>
										   			<img alt="Trang Cuoi" src="../images/last.gif" style="cursor: pointer;" onclick="View(document.forms[0].name, -1, 'view')"> &nbsp;
										   		<%} %>
	</TD>
	</tr>
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

	try{
		
		if(hch != null)
			hch.close();
		if(kbh != null)
			kbh.close();
		if(lch != null)
			lch.close();
		if(vtch!=null){
			vtch.close();
		}
		if(obj != null){
			obj.DBclose();
			obj = null;
		}		
		
		session.setAttribute("obj",null);
	
	}
	catch (SQLException e) {}
	

%>
<%}%>