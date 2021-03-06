
<%@page import="geso.dms.center.util.Utility"%>
<%@page import="geso.dms.center.beans.stockintransit.IStockintransit"%>
<%@page import="java.sql.ResultSet"%>
<%@page import="java.util.Calendar"%>
<%@page import="java.sql.Date"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
	<%
	String userId = (String) session.getAttribute("userId");  
	String userTen = (String) session.getAttribute("userTen");  	
	String sum = (String) session.getAttribute("sum");
	Utility util = (Utility) session.getAttribute("util");
	if(!util.check(userId, userTen, sum)){
		response.sendRedirect(request.getContextPath() + "/redirect.jsp");
	}else{ %>
<%
	IStockintransit obj = (IStockintransit)session.getAttribute("obj");
	ResultSet rsKenh = obj.getkenh();
	ResultSet khuvucs = obj.getkhuvuc();
	ResultSet vungs = obj.getvung();
	ResultSet npps = obj.getnpp();
	 ResultSet hch = (ResultSet)obj.GetRsHangCuahang();
	 ResultSet vtch = (ResultSet)obj.GetRsVitriCuahang(); 
	 ResultSet lch = (ResultSet)obj.GetRsLoaicuahang();
	 ResultSet rstinhthanh = (ResultSet)obj.GetRsTinhThanh(); 
	ResultSet rsquanhuyen= (ResultSet)obj.GetRsQuanHuyen();
	

%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd"> <% Utility Utilback = new Utility(); %>
<html>
<head>
<TITLE><%= getServletContext().getInitParameter("TITLENAME") %></TITLE>
<META http-equiv="Content-Type" content="text/html; charset=utf-8">
<META http-equiv="Content-Style-Type" content="text/css">
<LINK rel="stylesheet" type="text/css" media="print"
	href="../css/impression.css">
<LINK rel="stylesheet" href="../css/main.css" type="text/css">

<script type="text/javascript" src="../scripts/jquery-1.js"></script>
<link type="text/css" rel="stylesheet" href="../css/mybutton.css">
<LINK rel="stylesheet" type="text/css" href="../css/style.css" />

<script type="text/javascript"	src="../scripts/jquery.min.1.7.js"></script>
<link href="../css/jquery-ui.css" rel="stylesheet" type="text/css" />
<script src="../scripts/ui/jquery.ui.core.js" type="text/javascript"></script>
<script src="../scripts/ui/jquery.ui.widget.js" type="text/javascript"></script>
<script src="../scripts/ui/jquery.ui.datepicker.js"
	type="text/javascript"></script>

<script type="text/javascript"
	src="../scripts/report-js/action-report.js"></script>
<script language="javascript" type="text/javascript">	
	function search(){
		document.forms["frm"]["action"].value = "search";
		document.forms["frm"].submit();
	}	
	
	function submitform() {
		
		document.forms["frm"]["action"].value = "create";
		document.forms["frm"].submit();
		
	}
	function setErrors(errorMsg) {
		var errors = document.getElementById("errors");
		errors.value = errorMsg;
	}
	function reset() {
		var fieldShow = document.getElementsByName("fieldsHien");		
		for ( var i = 0; i < fieldShow.length; ++i) {
			fieldShow.item(i).checked = false;
		}		
	};
</script>
</head>
<BODY leftmargin="0" bottommargin="0" topmargin="0" rightmargin="0">
	<form name="frm" method="post" action="../../BaoCaoKhachHangTT">
		<input type="hidden" value="1" name="action"  >
		<input type="hidden" name="view" value='TT'>
		<div id="main" style="width: 99%; padding-left: 2px">
			<div align="left" id="header" style="width: 100%; float: none">
				<div style="width: 70%; padding: 5px; float: left"
					class="tbnavigation">B??o c??o qu???n tr??? &#62; B??o c??o kh??c &#62; B??o c??o kh??ch h??ng</div>
				<div align="right" style="padding: 5px" class="tbnavigation">
					<%=Utility.GLanguage("Ch??o m???ng b???n",session,jedis) %> , &nbsp; <%=userTen %></div>
			</div>
			
			<div align="left" style="width: 100%; float: none; clear: left">
				<fieldset>
					<legend class="legendtitle"> <%=Utility.GLanguage("B??o l???i nh???p li???u",session,jedis) %></legend>
					<textarea id="errors" readonly="readonly" name="errors" rows="1" style="width: 100% ; color:#F00 ; font-weight:bold">
					<%= obj.getMsg() %>
					</textarea>
				</fieldset>
			</div>
			<div align="left"
				style="width: 100%; float: none; clear: left; font-size: 0.7em">
				<fieldset>
					<legend class="legendtitle">B??o c??o kh??ch h??ng</legend>
					<div style="width: 100%; float: none" align="left">
						<div style="width: 100%; float: none" align="left"
							class="plainlabel">
							<TABLE width="70%" cellpadding="6" cellspacing="0">
								
								<TR>
									<TD class="plainlabel">Mi???n</TD>
									<TD class="plainlabel">
										<select name="vungid"  onchange="search();">
											<option value="" selected>All</option>
											<%if (vungs != null)
													while (vungs.next()) {
														if (vungs.getString("pk_seq").trim().equals(obj.getvungId())) {%>
													<option value="<%=vungs.getString("pk_seq")%>" selected><%=vungs.getString("ten")%></option>
												<%} else {%>
													<option value="<%=vungs.getString("pk_seq")%>"><%=vungs.getString("ten")%></option>
											<%}}%>
										</select>
									</TD>
									<TD class="plainlabel"><%=Utility.GLanguage("Nh?? ph??n ph???i",session,jedis) %> </TD>
									<TD class="plainlabel">
										<select name="nppId" onchange="search();" id="nppId">
											<option value="" selected>All</option>
											<%if (npps != null)
													while (npps.next()) {
														if (npps.getString("pk_seq").trim().equals(obj.getnppId())) {%>
															<option value="<%=npps.getString("pk_seq")%>" selected><%=npps.getString("ten")%></option>
													<%} else {%>
														<option value="<%=npps.getString("pk_seq")%>"><%=npps.getString("ten")%></option>
													<%}}%>
										</select>
									</TD>
								</TR>
								
								<TR>
									<TD class="plainlabel"><%=Utility.GLanguage("Khu v???c",session,jedis) %></TD>
									<TD class="plainlabel">
										<select name="khuvucid" name="khuvucId" onchange="search();">
											<option value="" selected>All</option>
											<%if (khuvucs != null)
													while (khuvucs.next()) {
														if (khuvucs.getString("pk_seq").trim().equals(obj.getkhuvucId())) {%>
															<option value="<%=khuvucs.getString("pk_seq")%>" selected><%=khuvucs.getString("ten")%></option>
													<%} else {%>
														<option value="<%=khuvucs.getString("pk_seq")%>"><%=khuvucs.getString("ten")%></option>
													<%}}%>
										</select>
									</TD>
									
								</TR>
									<TR>	
									<TD class="plainlabel">V??? tr?? c???a h??ng</TD>
	                                <TD  class="plainlabel"><SELECT name="vtchid" onChange = "search();">
    		                            <option value=""> </option>
            		                    <% try{ while(vtch.next()){ 
								    			if(vtch.getString("pk_seq").equals(obj.GetIdVitriCuahang())){ %>
								      				<option value='<%=vtch.getString("pk_seq")%>' selected><%=vtch.getString("ten") %></option>
								      			<%}else{ %>
								     				<option value='<%=vtch.getString("pk_seq")%>'><%=vtch.getString("ten") %></option>
								     			<%}}}catch(java.sql.SQLException e){} %>
                                        </SELECT></TD>
									
									<TD class="plainlabel"><%=Utility.GLanguage("K??nh b??n h??ng",session,jedis) %></TD>
									<TD class="plainlabel"><select onchange="search();"  name="kenhid" >
											<option value="">All</option>
											<% if(rsKenh!=null){
													while(rsKenh.next()){
														String kenhId = rsKenh.getString(1);
														if(kenhId.equals(obj.getkenhId())){
															%>
															<option selected="selected" value="<%=rsKenh.getString(1) %>"><%=rsKenh.getString(2)%></option>
														<%}else{%>	
															<option value="<%=rsKenh.getString(1) %>"><%=rsKenh.getString(2)%></option>
											<%}}}%>
									</select></TD>											
								</TR>
								<TR>
																		
								
									<TD class="plainlabel">Lo???i c???a h??ng</TD>
								  	<TD  class="plainlabel"><SELECT name="lchid"  onChange = "submitform();">
								    	<option value=""> </option>
								    	<% try{ while(lch.next()){ 
								    			if(lch.getString("pk_Seq").equals(obj.GetIdLoaicuahang())){ %>
								      				<option value='<%=lch.getString("pk_seq")%>' selected><%=lch.getString("ten") %></option>
								      			<%}else{ %>
								     				<option value='<%=lch.getString("pk_seq")%>'><%=lch.getString("ten") %></option>
								     			<%}}}catch(java.sql.SQLException e){} %>
										</SELECT>
									</TD>
									<TD width="16%" class="plainlabel">H???ng c???a h??ng</TD>
 
									<TD width="38%" class="plainlabel">
										<SELECT name="hchid" onChange = "search();">
										  <option value=""> </option>
										  <% try{ while(hch.next()){ 
								    			if(hch.getString("pk_seq").equals(obj.GetIdHangCuahang())){ %>
								      				<option value='<%=hch.getString("pk_seq")%>' selected><%= hch.getString("ten") %></option>
								      			<%}else{ %>
								     				<option value='<%=hch.getString("pk_seq")%>'><%= hch.getString("ten") %></option>
								     			<%}}}catch(java.sql.SQLException e){} %>	 
                                        </SELECT>
								  </TD>
								</TR>								
								<TR>
																		
								
									<TD class="plainlabel">T???nh th??nh</TD>
								  	<TD  class="plainlabel"><SELECT name="tinhthanhid"  onChange = "search();">
								    	<option value=""> </option>
								    	<% try{ while(rstinhthanh.next()){ 
								    			if(rstinhthanh.getString("pk_Seq").equals(obj.GetIdTinhThanh())){ %>
								      				<option value='<%=rstinhthanh.getString("pk_seq")%>' selected><%=rstinhthanh.getString("ten") %></option>
								      			<%}else{ %>
								     				<option value='<%=rstinhthanh.getString("pk_seq")%>'><%=rstinhthanh.getString("ten") %></option>
								     			<%}}}catch(java.sql.SQLException e){} %>
										</SELECT>
									</TD>
									<TD width="16%" class="plainlabel">Qu???n huy???n</TD>
 
									<TD width="38%" class="plainlabel">
										<SELECT name="quanhuyenid" onChange = "search();">
										  <option value=""> </option>
										  <% try{ while(rsquanhuyen.next()){ 
								    			if(rsquanhuyen.getString("pk_seq").equals(obj.GetIdQuanHuyen())){ %>
								      				<option value='<%=rsquanhuyen.getString("pk_seq")%>' selected><%= rsquanhuyen.getString("ten") %></option>
								      			<%}else{ %>
								     				<option value='<%=rsquanhuyen.getString("pk_seq")%>'><%= rsquanhuyen.getString("ten") %></option>
								     			<%}}}catch(java.sql.SQLException e){} %>	 
                                        </SELECT>
								  </TD>
								</TR>	
								
								<TR>
									<TD class="plainlabel">D???ng BC</TD>
									<TD class="plainlabel">
									<%
										if(obj.gettype()=="0"){
											%>
											<input type="radio" name="type" value="1" /> Pivot &nbsp; &nbsp;
											<input type="radio" name="type" value="0"  checked="checked"/> B??nh th?????ng
											<%
										}
										else
										{
											%>
												<input type="radio" name="type" value="1"  checked="checked"/>  Pivot   &nbsp; &nbsp;
												<input type="radio" name="type" value="0"  /> B??nh th?????ng 
											<%
										}
									%>
										
									</TD>
									
								</TR>
								
										
								<TR>
									<TD colspan="2">										
											
									    <a class="button"
										href="javascript:submitform();"> <img style="top: -4px;"
											src="../images/button.png" alt=""> T???o b??o c??o
									</a></td>
								</TR>
							</TABLE>
						</div>												
						
					</div>
				</fieldset>
			</div>
		</div>
		<br /> <br /> <br /> <br />		
	</form>
</body>
</HTML>
<%
	try
	{
		
		if(rsKenh != null)
			rsKenh.close();
		if(khuvucs != null)
			khuvucs.close();
		if(vungs != null)
			vungs.close();
		
		if(npps != null)
			npps.close();
		
		if(obj != null){
			obj.DBclose();
			obj = null;
		}
		session.setAttribute("obj",null);
	}catch(java.sql.SQLException e){}
	}
%>