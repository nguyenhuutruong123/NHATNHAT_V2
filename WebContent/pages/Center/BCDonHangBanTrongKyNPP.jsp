<%@page import="geso.dms.center.beans.stockintransit.IStockintransit"%>
<%@page import="java.sql.ResultSet"%>
<%@page import="geso.dms.distributor.beans.report.Ireport"%>
<%@page import="java.util.Calendar"%>
<%@ page  import = "geso.dms.center.util.*" %>
<%@page import="java.sql.Date"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%
	String userId = (String) session.getAttribute("userId");  
	String userTen = (String) session.getAttribute("userTen");
	IStockintransit obj = (IStockintransit)session.getAttribute("obj");
	ResultSet npp = obj.getnpp();
	String nppId = obj.getnppId();
	ResultSet vung = obj.getvung();
	ResultSet khuvuc = obj.getkhuvuc();
	ResultSet ddkd = obj.getRsddkd();
	ResultSet ttRs = obj.getTtRs();
	ResultSet khoRs = obj.getkho();
	ResultSet nhomRs = obj.getNhomhangRs();
	
	ResultSet tp = (ResultSet)obj.getTinhthanh();
	ResultSet qh = (ResultSet)obj.getQh();
	ResultSet phuongxaRs = (ResultSet)obj.getPhuongxaRs();
	
	Utility util = (Utility) session.getAttribute("util");
	String url = util.getChuyenNguUrl("BCDonHangBanTrongKy", "",session);
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd"> <% Utility Utilback = new Utility(); %>
<html>
<head>
<TITLE><%= getServletContext().getInitParameter("TITLENAME") %></TITLE>
<META http-equiv="Content-Type" content="text/html; charset=utf-8">
<META http-equiv="Content-Style-Type" content="text/css">
<LINK rel="stylesheet" type="text/css" media="print" href="../css/impression.css">
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
<script type="text/javascript">
		$(document).ready(function() {		
			$( ".days" ).datepicker({			    
					changeMonth: true,
					changeYear: true				
			});
            $(".button").hover(function(){
                $(".button img")
                .animate({top:"-10px"}, 200).animate({top:"-4px"}, 200) // first jump
                .animate({top:"-7px"}, 100).animate({top:"-4px"}, 100) // second jump
                .animate({top:"-6px"}, 100).animate({top:"-4px"}, 100); // the last jump
            });
        }); 
		$(document).ready(function(){
            $(".button1").hover(function(){
                $(".button1 img")
                .animate({top:"-10px"}, 200).animate({top:"-4px"}, 200) // first jump
                .animate({top:"-7px"}, 100).animate({top:"-4px"}, 100) // second jump
                .animate({top:"-6px"}, 100).animate({top:"-4px"}, 100); // the last jump
            });
        });
		
    </script>
<script type="text/javascript">
function seach() {
	document.forms['frm'].action.value = 'seach';
	document.forms["frm"].submit();
}
	function submitform() 
	{
		
		
		if (document.getElementById("Sdays").value == "") 
		{
			alert("Vui l??ng ch???n ng??y b???t ?????u");
			return ;
		}
		if (document.getElementById("Edays").value == "")
		{
			alert("Vui l??ng ch???n ng??y k???t th??c");
			return ;
		}		
	
		document.forms['frm'].action.value= 'tao';
		document.forms["frm"].submit();
	}
	
	function submitformpivot() 
	{
		
		
		if (document.getElementById("Sdays").value == "") 
		{
			alert("Vui l??ng ch???n ng??y b???t ?????u");
			return ;
		}
		if (document.getElementById("Edays").value == "")
		{
			alert("Vui l??ng ch???n ng??y k???t th??c");
			return ;
		}		
	
		document.forms['frm'].action.value= 'kopivot';
		document.forms["frm"].submit();
	}
	
	function etc() 
	{
		
		
		if (document.getElementById("Sdays").value == "") 
		{
			alert("Vui l??ng ch???n ng??y b???t ?????u");
			return ;
		}
		if (document.getElementById("Edays").value == "")
		{
			alert("Vui l??ng ch???n ng??y k???t th??c");
			return ;
		}		
	
		document.forms['frm'].action.value= 'etc';
		document.forms["frm"].submit();
	}
	
</script>
<link href="../css/select2.css" rel="stylesheet" />
<script src="../scripts/select2.js"></script>
<script>
$(document).ready(function()
{
	$("#nppId").select2();
	$("#vungId").select2();
	$("#khuvucId").select2();
	$("#nhanhangId").select2();
	$("#chungloaiId").select2();
	$("#programs").select2();
	$("#ddkdId").select2();
	$("#gsbhId").select2();
	$("#kenhId").select2();
	$("#dvdlId").select2();
	$("#ttId").select2();
	
	$("#tpId").select2();
	$("#qhId").select2();
	$("#phuongxaId").select2();
});
</script>

<BODY leftmargin="0" bottommargin="0" topmargin="0" rightmargin="0">
	<form name="frm" method="post" action="../../BCDonHangBanTrongKy">
	<% redis.clients.jedis.Jedis jedis =  geso.dms.center.util.Utility.getJedis(); %><% geso.dms.center.util.Csrf csdr=new geso.dms.center.util.Csrf(request,response,true,false,true);%>
 <input type="hidden" name="<%=csdr.get_tokenName() %>" value='<%=csdr.get_tokenValue() %>'>
	
	<input type="hidden" name="action" value='1'>
	<input type="hidden" name="view" value='TT'>
	<input type="hidden" name="userId" value='<%=userId%>'>
		<div id="main" style="width: 99%; padding-left: 2px">
			<div align="left" id="header" style="width: 100%; float: none">
				<div style="width: 70%; padding: 5px; float: left"
					class="tbnavigation"><%=url %></div>
				<div align="right" style="padding: 5px" class="tbnavigation">
					<%=Utility.GLanguage("Ch??o m???ng b???n",session,jedis) %>  <%= userTen %></div>
			</div>

			<div align="left" style="width: 100%; float: none; clear: left">
				<fieldset>

					<legend class="legendtitle"> <%=Utility.GLanguage("B??o l???i nh???p li???u",session,jedis) %></legend>
					<textarea id="errors"  name="errors" rows="1" style="width: 100% ; color:#F00 ; font-weight:bold">
					<%=Utility.GLanguage(obj.getMsg(),session,jedis) %>
					</textarea>
				</fieldset>
			</div>
			<div align="left"
				style="width: 100%; float: none; clear: left; font-size: 0.7em">
				<fieldset>
					<legend class="legendtitle"><%=Utility.GLanguage("B??o c??o ????n h??ng b??n trong k???",session,jedis) %></legend>
					<div style="width: 100%; float: none" align="left" class="plainlabel">
							<TABLE width="70%" cellpadding="6" cellspacing="0">
								<TR>
										<TD class="plainlabel"><%=Utility.GLanguage("T??? ng??y",session,jedis) %> </TD>
											<TD class="plainlabel">	<input autocomplete="off" type="text" name="Sdays" id="Sdays" class="days" value='<%= obj.gettungay() %>' />
											</TD>
											<TD class="plainlabel"><%=Utility.GLanguage("?????n ng??y",session,jedis) %> </TD>
											<TD class="plainlabel">
												<input autocomplete="off" type="text" name="Edays" id="Edays" class="days" value='<%= obj.getdenngay() %>'/>
											</TD>
									</TR>
									<TR>
									<TD class="plainlabel"><%=Utility.GLanguage("Mi???n",session,jedis) %></TD>
									<TD class="plainlabel">
										<select name="vungId" id="vungId" onchange="seach();" style="width:200px">
											<option value="" selected><%=Utility.GLanguage("All",session,jedis) %></option>
											<%if (vung != null)
													while (vung.next()) {
														if (vung.getString("pk_seq").equals(obj.getvungId())) {%>
													<option value="<%=vung.getString("pk_seq")%>" selected><%=vung.getString("ten")%></option>
												<%} else {%>
													<option value="<%=vung.getString("pk_seq")%>"><%=vung.getString("ten")%></option>
											<%}}%>
										</select>
									</TD>
									<%-- <TD class="plainlabel"><%=Utility.GLanguage("Khu v???c",session,jedis) %></TD>
									<TD class="plainlabel">
										<select name="khuvucId" id="khuvucId" onchange="seach();" style="width:200px">
											<option value="" selected>All</option>
											<%if (khuvuc != null)
													while (khuvuc.next()) {
														if (khuvuc.getString("pk_seq").equals(obj.getkhuvucId())) {%>
															<option value="<%=khuvuc.getString("pk_seq")%>" selected><%=khuvuc.getString("ten")%></option>
													<%} else {%>
														<option value="<%=khuvuc.getString("pk_seq")%>"><%=khuvuc.getString("ten")%></option>
													<%}}%>
										</select>
									</TD> --%>
									
									<%-- <TD class="plainlabel">?????a b??n  </TD>
									<TD class="plainlabel">
										<select name="ttId" id="ttId"  onchange="seach();"  style="width:200px" >
											<option value="" >All</option>
											<%if (ttRs != null)
													while (ttRs.next()) {
														if (ttRs.getString("pk_seq").equals(obj.getTtId()  )) {%>
											   <option value="<%=ttRs.getString("pk_seq")%>" selected><%=ttRs.getString("ten")%></option>
											   <%} else {%>
											   <option value="<%=ttRs.getString("pk_seq")%>"><%=ttRs.getString("ten")%></option>
											<%}}%>
										</select>
									</TD> --%>
									
									<TD class="plainlabel" colspan="1"><%=Utility.GLanguage("T???nh /Th??nh ph???",session,jedis) %> </TD>
									<TD class="plainlabel" colspan="1"><SELECT name="tpId" id="tpId"  style="width:200px" onchange="seach();">
									<option value=""></option>
									<% 
									if (tp!=null)
									{
										try{while(tp.next()){ 
									    	if (tp.getString("pk_seq").equals(obj.getTinhthanhid())){ %>
											<option value='<%=tp.getString("pk_seq")%>' selected><%=tp.getString("ten") %></option>
											<%}else{ %>
											<option value='<%=tp.getString("pk_seq")%>'><%=tp.getString("ten") %></option>
										<%}}}catch(java.sql.SQLException e){}
									} %>
									</SELECT></TD>
								</TR>
								
								<TR>
									<TD class="plainlabel" colspan="1"><%=Utility.GLanguage("Qu???n/Huy???n",session,jedis) %></TD>
									<TD class="plainlabel" colspan="1">
									<SELECT name="qhId" style="width:200px" id="qhId" onchange="seach();">
									<option value=""></option>
									<%if (qh != null){ 
							      		try{while(qh.next()){ 
							    			if (qh.getString("qhId").equals(obj.getQhId())){ %>
												<option value='<%=qh.getString("qhId")%>' selected><%=qh.getString("qhTen") %></option>
											<%}else{ %>
												<option value='<%=qh.getString("qhId")%>'><%=qh.getString("qhTen") %></option>
											<%}}}catch(java.sql.SQLException e){} 
						      		} %>
									</SELECT></TD>
											
											<TD class="plainlabel" colspan="1"><%=Utility.GLanguage("Ph?????ng x??",session,jedis) %> </TD>
											 <TD class="plainlabel" >
											 <SELECT name="phuongxaId" onchange="seach();" style="width:200px"
													id="phuongxaId">
														<option value=""></option>
														<%
														if (phuongxaRs != null){ 
									      					try{
									      						while(phuongxaRs.next()){ 
									    							if (phuongxaRs.getString("pk_seq").equals(obj.getPhuongxa())){ %>
														<option value='<%=phuongxaRs.getString("pk_seq")%>' selected><%=phuongxaRs.getString("ten") %></option>
														<%}else{ %>
														<option value='<%=phuongxaRs.getString("pk_seq")%>'><%=phuongxaRs.getString("ten") %></option>
														<%}}}catch(java.sql.SQLException e){} 
									      		}	%>
												</SELECT>
												</TD>
									</TR>
								
								<TR>
									<TD class="plainlabel"><%=Utility.GLanguage("Nh?? ph??n ph???i",session,jedis) %> </TD>
									<TD class="plainlabel" > 
										<select name="nppId" onchange="seach();" id="nppId" style="width:200px">
											<option value="" selected><%=Utility.GLanguage("All",session,jedis) %></option>
											<%if (npp != null)
													while (npp.next()) {
														if (npp.getString("pk_seq").equals(obj.getnppId())) {%>
															<option value="<%=npp.getString("pk_seq")%>" selected><%=npp.getString("ten")%></option>
													<%} else {%>
														<option value="<%=npp.getString("pk_seq")%>"><%=npp.getString("ten")%></option>
													<%}}%>
										</select>
									</TD>
									<TD class="plainlabel"><%=Utility.GLanguage("NH??N VI??N B??N H??NG",session,jedis) %></TD>
									<TD class="plainlabel">
										<select name="ddkdId" id="ddkdId"	onchange="seach();" style="width:200px">
											<option value="" selected><%=Utility.GLanguage("All",session,jedis) %></option>
											<%if (ddkd != null)
													while (ddkd.next()) {
														if (ddkd.getString("pk_seq").equals(obj.getDdkd())) {%>
														<option value="<%=ddkd.getString("pk_seq")%>" selected><%=ddkd.getString("ten")%></option>
													<%} else {%>
														<option value="<%=ddkd.getString("pk_seq")%>"><%=ddkd.getString("ten")%></option>
													<%}}%>
										</select>
									</TD>
									
								</TR>
								
								<TR>
								<%if(obj.gettype().equals("0")){ %>
										
											<TD class="plainlabel"><%=Utility.GLanguage("Tr???ng th??i",session,jedis) %></TD>
											<TD class="plainlabel">
											<% String[] trangthai = new  String[] {"Ch??a ch???t","???? ch???t","???? h???y","???? xu???t Kho","???? In","???? xu???t H??"  } ;
													String[] idTrangThai = new  String[] {"0","1","2","3","4","5"} ;
											%> 
											<SELECT name="trangthai" >
											<option> </option>
							      		 <% for( int i=0;i<trangthai.length;i++) { 
							    			if(idTrangThai[i].equals(obj.getTrangthai()) ){ %>
							      				<option value='<%=idTrangThai[i]%>' selected><%=trangthai[i] %></option>
							      		 	<%}else{ %>
							     				<option value='<%=idTrangThai[i]%>' ><%=trangthai[i] %></option>
							     			<%} 
							      		 }
							      		 	%>
											</SELECT>
											</TD>
								  
						  <%}else { %>
											<TD class="plainlabel"><%=Utility.GLanguage("Tr???ng th??i",session,jedis) %></TD>
											<TD class="plainlabel">
											<% String[] trangthai = new  String[] {"Ch??? x??c nh???n","???? x??c nh???n","???? Xo??","???? In","???? H???y"  } ;
													String[] idTrangThai = new  String[] {"1","2","3","4","5"} ;
											%> 
											<SELECT name="trangthai" >
											<option> </option>
							      		 <% for( int i=0;i<trangthai.length;i++) { 
							    			if(idTrangThai[i].equals(obj.getTrangthai()) ){ %>
							      				<option value='<%=idTrangThai[i]%>' selected><%=trangthai[i] %></option>
							      		 	<%}else{ %>
							     				<option value='<%=idTrangThai[i]%>'><%=trangthai[i] %></option>
							     			<%} 
							      		 }
							      		 	%>
											</SELECT>
											</TD>
								  
						  
						  <%} %>
						  
						  <TD class="plainlabel"><%=Utility.GLanguage("Kho",session,jedis) %></TD>
									<TD class="plainlabel"><select name="khoId" id="khoId" >
											<option value="" >All</option>
										<% if(khoRs !=null){ 
											while(khoRs.next())
											{ 
											if(khoRs.getString("pk_seq").equals(obj.getkhoId())) {
											%>
											<option value="<%=khoRs.getString("pk_seq")%>" selected><%=khoRs.getString("ten")%></option>
											<%} else { %>
											<option value="<%=khoRs.getString("pk_seq")%>" ><%=khoRs.getString("ten")%></option>
											<%}}} %>
											</select></TD>
						  
						  </TR>
						  
						  
						  		
						  <TR>
						  
						  			<TD class="plainlabel"> <%=Utility.GLanguage("Nh??m h??ng",session,jedis) %>  </TD>
									<TD class="plainlabel">
										<select name="nhomId" id="nhomId" style="width:200px"    >
											<option value="" >All</option>
											<%if (nhomRs != null)
													while (nhomRs.next()) {
														if (nhomRs.getString("pk_seq").equals(obj.getNhomhangid()   )) {%>
											   <option value="<%=nhomRs.getString("pk_seq")%>" selected><%=nhomRs.getString("ten")%></option>
											   <%} else {%>
											   <option value="<%=nhomRs.getString("pk_seq")%>"><%=nhomRs.getString("ten")%></option>
											<%}}%>
										</select>
									</TD>			
						  
						  </TR>
						  
						  
						  <TR>
									<TD class="plainlabel"><%=Utility.GLanguage("Doanh s??? t??nh theo",session,jedis) %> </TD>
									<TD class="plainlabel">
							<%
								if(obj.getMucCN_DT().equals("1")){ %>
								<input type="checkbox" name="cndt" value="1" checked="checked"  /> <%=Utility.GLanguage("CN/DT",session,jedis) %> &nbsp; &nbsp;
							<% }else { %>
								    <input type="checkbox" name="cndt" value="1"  /> <%=Utility.GLanguage("CN/DT",session,jedis) %> &nbsp; &nbsp;
							 <%} %>
										
									<%
								if(obj.getMuc_KhachHang().equals("1")){ %>
								<input type="checkbox" name="kh" value="1" checked="checked"  /> <%=Utility.GLanguage("Kh??ch H??ng",session,jedis) %> &nbsp; &nbsp;
							<% }else { %>
								    <input type="checkbox" name="kh" value="1"  /> <%=Utility.GLanguage("Kh??ch H??ng",session,jedis) %> &nbsp; &nbsp;
							 <%} %>
										
									</TD>
									
								</TR>
							
						  
								
								
								<TR>
									<TD class="plainlabel"> <%=Utility.GLanguage("M???c l???y",session,jedis) %>  </TD>
									<TD class="plainlabel">
									<%
										if(obj.gettype().equals("0")){
											%>
											<input type="radio" name="type" value="1"  onchange="seach();" /> <%=Utility.GLanguage("H??a ????n",session,jedis) %> &nbsp; &nbsp;
											<input type="radio" name="type" value="0"  checked="checked"  onchange="seach();" /> <%=Utility.GLanguage("????n h??ng",session,jedis) %>
											<%
										}
										else
										{
											%>
												<input type="radio" name="type" value="1"  checked="checked"  onchange="seach();" /><%=Utility.GLanguage("H??a ????n",session,jedis) %>  &nbsp; &nbsp;
												<input type="radio" name="type" value="0"  onchange="seach();" /> <%=Utility.GLanguage("????n h??ng",session,jedis) %>
											<%
										}
									%>
										
									</TD>
									
								</TR>
								
							
								<TR>
									<td ><a class="button" href="javascript:submitform();"> 
										<img style="top: -4px;" src="../images/button.png" alt=""> <%=Utility.GLanguage("????n h??ng OTC",session,jedis) %>
									</a>
									</td>
									<td ><a class="button" href="javascript:etc();"> 
										<img style="top: -4px;" src="../images/button.png" alt=""> <%=Utility.GLanguage("????n h??ng ETC",session,jedis) %>
									</a>
									</td>
									<td colspan="2"><a class="button" href="javascript:submitformpivot();"> 
										<img style="top: -4px;" src="../images/button.png" alt=""> <%=Utility.GLanguage("????n h??ng kh??ng Pivot",session,jedis) %>
									</a>
									</td>
								</TR>
							</TABLE>
						</div>
						
				</fieldset>
			</div>
		</div>
		<br /><%geso.dms.center.util.Utility.JedisClose(jedis); %>
	</form>
</body>
</HTML>