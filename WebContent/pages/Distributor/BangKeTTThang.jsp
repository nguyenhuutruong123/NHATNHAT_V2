<%@page import="java.sql.ResultSet"%>
<%@page import="geso.dms.distributor.beans.reports.IBangKeTTThang"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page  import = "geso.dms.center.util.*" %>
<%	String userId = (String) session.getAttribute("userId");  
	String userTen = (String) session.getAttribute("userTen");  	
	String nppId = (String) session.getAttribute("nppId");  	
	String sum = (String) session.getAttribute("sum");
	String loi=(String)session.getAttribute("loi");
	IBangKeTTThang obj = (IBangKeTTThang) session.getAttribute("obj");
	String thang=(String)session.getAttribute("thang");
	String nam=(String)session.getAttribute("nam");
	String loai=(String)session.getAttribute("loai");	
	String tt = (String)session.getAttribute("tt");
	
	ResultSet rsNVBH = obj.getRsNVBH();
	ResultSet rsloaikh = obj.getRsLoaikh();
	ResultSet rsChiNhanh = obj.getRsChiNhanh();	
	ResultSet rsNvgn = obj.getRsNVGN();
	ResultSet rsmienid=obj.getRsmienid();
	Utility util = (Utility) session.getAttribute("util");
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
<script type="text/javascript">
		$(document).ready(function() {		
			$( ".days" ).datepicker({			    
					changeMonth: true,
					changeYear: true				
			});
           
        }); 
		
		
    </script>

<script type="text/javascript">
function submitform()
{
	var loai = document.getElementById("loai").value;
	var tt = document.getElementById("tt").value;
	
	if(loai=="0" || loai=="3" || loai=="5")
	{
		var thang = document.getElementById("thang").value;
		if(thang == 0)
		{
			document.getElementById("dataerror").value="Vui l??ng ch???n th??ng";
			return;
		}
	}
	
	if(loai=="1" || loai=="2" || loai=="4"|| loai=="6")
	{
		var quy = document.getElementById("quy").value;
		if(thang == 0)
		{
			document.getElementById("dataerror").value="Vui l??ng ch???n qu??";
			return;
		}
	}
	
	if(loai!="9")
		{
	var nam = document.getElementById("nam").value;
	if(nam == 0)
	{
		document.getElementById("dataerror").value="Vui l??ng ch???n n??m";
		return;
	}
		}
	if(loai=="0" || loai=="1" )
	{
		var tdvId = document.getElementById("tdvId").value;
		if(tdvId == "")
		{
			document.getElementById("dataerror").value="Vui l??ng ch???n NH??N VI??N B??N H??NG";
			return;
		} 
	} 
	
	if(tt=="1")
	{
		var chinhanhId = document.getElementById("chinhanhId").value;
		if(chinhanhId == "")
		{
			document.getElementById("dataerror").value="Vui l??ng ch???n chi nh??nh/?????i t??c";
			return;
		} 
	}
		
	
	document.forms['rpForm'].dataerror.value="";
	document.forms['rpForm'].action.value= 'taomoi';
	document.forms['rpForm'].submit();
}

function seach()
{
	document.forms['rpForm'].action.value= 'seach';
	document.forms["rpForm"].submit();
}
</SCRIPT>

<link href="../css/select2.css" rel="stylesheet" />
	<script src="../scripts/select2.js"></script>
	<script>
		$(document).ready(function()
		{
			$(".select2").select2();
		});
	</script>

</HEAD>
<BODY leftmargin="0" bottommargin="0" topmargin="0" rightmargin="0">
<form name="rpForm" method="post" action="../../BangKeTTThangSvl">
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
						  <% if(loai.equals("0")){%>
						   <TD align="left" colspan="2" class="tbnavigation">&nbsp;Bi???u m???u thanh to??n &gt; Ch???c n??ng &gt; B???ng k?? thanh to??n trong th??ng CN </TD>
   						<% }else if(loai.equals("1")) { %>
   							<TD align="left" colspan="2" class="tbnavigation">&nbsp;Bi???u m???u thanh to??n &gt; Ch???c n??ng &gt; B???ng k?? CK theo qu?? CN </TD>
   						<% }else if(loai.equals("2")) { %>
   							<TD align="left" colspan="2" class="tbnavigation">&nbsp;Bi???u m???u thanh to??n &gt; Ch???c n??ng &gt; B???ng k?? qu?? ???ng h??? CN </TD>
   						<% }else if(loai.equals("3")) { %>
   							<TD align="left" colspan="2" class="tbnavigation">&nbsp;Bi???u m???u thanh to??n &gt;  Ch???c n??ng &gt; B???ng k?? thanh to??n trong th??ng DT </TD>
   						<% }else if(loai.equals("4")) { %>
   							<TD align="left" colspan="2" class="tbnavigation">&nbsp;Bi???u m???u thanh to??n &gt; Ch???c n??ng &gt; B???ng k?? thanh to??n trong qu?? DT</TD>
   						<% }else if(loai.equals("7")) { %>
   							<TD align="left" colspan="2" class="tbnavigation">&nbsp;Bi???u m???u thanh to??n &gt; Ch???c n??ng &gt; B???ng k?? chi???t kh???u kh??ch h??ng th??n thi???t  </TD>
   						<%}
   						else if (loai.equals("9")){ %>
							<TD align="left" colspan="2" class="tbnavigation">&nbsp;Bi???u m???u thanh to??n &gt; Ch???c n??ng &gt; B???ng k?? phi???u giao h??ng khuy???n m???i</TD>
						<%}
   						else{ %>
   							<TD align="left" colspan="2" class="tbnavigation">&nbsp;Bi???u m???u thanh to??n &gt; Ch???c n??ng &gt; X??c nh???n doanh s??? q??y v?? nh??m DT</TD>
   						<%} %>
   						
						   <TD colspan="2" align="right" class="tbnavigation"><%=Utility.GLanguage("Ch??o m???ng b???n",session,jedis) %>  <%=userTen %>&nbsp;</TD>
						  </tr>
 					  </table>					</TD>
				</TR>
				<tr>
					<TD align="left" colspan="4" class="legendtitle">
						<FIELDSET>
						<LEGEND class="legendtitle"><%=Utility.GLanguage("B??o l???i nh???p li???u",session,jedis) %></LEGEND>
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
							  <LEGEND class="legendtitle">Th???i gian xu???t b??o c??o</LEGEND>
							<TABLE  width="100%" cellpadding="6" cellspacing="0">
							
							<%if(loai.equals("0") || loai.equals("3") || loai.equals("5") ) { %>
							<TR>
								<TD class="plainlabel" width="10%">Th??ng</TD>
									<TD class="plainlabel">
									 <select name="thang" id="thang"  style="width :70px" >
									<option value=0> </option>  
									<%
  										int k=1;
  									for(k=1;k<=12;k++){
  										String chuoi=k<10?"0"+k:""+k;
  									  if(obj.getThang().equals(chuoi)){
  									%>
									<option value=<%=chuoi%> selected="selected" > <%=chuoi%></option> 
									<%
 										}else{
 									%>
									<option value=<%=chuoi%> ><%=chuoi%></option> 
									<%
 										}
 									  }
 									%>
									</select>
									<select name="nam" id="nam"  style="width: 70px;" >
									<option value=0> </option>  
									<%
									  
  										int n;
  										for(n=2014;n<2020;n++){
  										if(obj.getNam().equals(""+n)){
  									%>
									<option value=<%=n%> selected="selected" > <%=n%></option> 
									<%
 										}else{
 									%>
									<option value=<%=n%> ><%=n%></option> 
									<%
 										}
 									 }
 									%>
 									</select>
									</TD>
								</TR>
								<%}else if (!loai.equals("7") && !loai.equals("9")){%>
										<TR>
									<TD class="plainlabel" width="10%">Q??y</TD>
									<TD class="plainlabel">
									<% 	String[] trangthai = new  String[] {"Q??y 1","Q??y 2","Q??y 3","Q??y 4"  } ;
										String[] idTrangThai = new  String[] {"1","2","3","4"} ;
									%> 
									<SELECT name="quy" id="quy" style="width :70px" >
									<option> </option>
					      		 	<% for( int i=0;i<trangthai.length;i++) { 
					    				if(idTrangThai[i].equals(obj.getQuy()) ){ %>
					      					<option value='<%=idTrangThai[i]%>' selected><%=trangthai[i] %></option>
					      		 		<%}else{ %>
					     					<option value='<%=idTrangThai[i]%>'><%=trangthai[i] %></option>
					     				<%} 
					      		 		}
					      		 	%>
								       	</SELECT>
								       	
								       	<select name="nam" id="nam"  style="width: 70px;" >
										<option value=0> </option>  
										<%
										  
	  										int n;
	  										for(n=2014;n<2020;n++){
	  										if(obj.getNam().equals(""+n)){
	  									%>
										<option value=<%=n%> selected="selected" > <%=n%></option> 
										<%
	 										}else{
	 									%>
										<option value=<%=n%> ><%=n%></option> 
										<%
	 										}
	 									 }
	 									%>
	 									</select>
	 									</TD>
								   </TR>
								<%} %>
								
								<%if(tt.equals("1")){ %>
								
								<TR>
									<%if (loai.equals("0") || loai.equals("1") || loai.equals("2") ) { %>
										<TD class="plainlabel">Mi???n</TD>
									<%}else{ %>
										<TD class="plainlabel">Mi???n</TD>
									<%} %>
										<TD class="plainlabel">
											<select name="Mienid" id="Mienid" style="width:200px;" onchange="seach();" class="select2"  >
												<option value="" selected></option>
												<%
													if (rsmienid != null)
														while (rsmienid.next()) {
															if (rsmienid.getString("pk_seq").equals(obj.getMienid())) {
												%>
												   <option value="<%=rsmienid.getString("pk_seq")%>" selected><%=rsmienid.getString("ten")%></option>
												   <%
												   	} else {
												   %>
												   <option value="<%=rsmienid.getString("pk_seq")%>"><%=rsmienid.getString("ten")%></option>
												<%
													}
														}
												%>
										</select></TD>									
									</TR>								
								<%} %>
								
								
								<TR>
									<%if (loai.equals("0") || loai.equals("1") || loai.equals("2") ) { %>
										<TD class="plainlabel">Chi nh??nh</TD>
									<%}else{ %>
										<TD class="plainlabel">Chi nh??nh</TD>
									<%} %>
										<TD class="plainlabel">
											<select name="chinhanhId" id="chinhanhId" style="width:200px;" onchange="seach();" class="select2"  >
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
										</select></TD>									
									</TR>	
									
									
								<%-- <TR>
								<TD class="plainlabel"><%=Utility.GLanguage("NH??N VI??N B??N H??NG",session,jedis) %></TD>
									<TD class="plainlabel"><select name="tdvId" id="tdvId" style="width:200px;" >
											<option value="" selected></option>
											<%	if (rsNVBH != null){
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
													}}
											%>
									</select></TD>
								
								</TR> --%>
									
									
								
								
								<%if(loai.equals("0") || loai.equals("1") || loai.equals("2")){ %>
								<TR>
								<TD class="plainlabel"><%=Utility.GLanguage("NH??N VI??N B??N H??NG",session,jedis) %></TD>
									<TD class="plainlabel"><select name="tdvId" id="tdvId" style="width:200px;" >
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
									</select></TD>
								
								</TR>
								
								
								<%if( loai.equals("2")){ %>
								<TR>
								<TD class="plainlabel">Lo???i kh??ch h??ng </TD>
									<TD class="plainlabel"><select name="loaikh" id="loaikh" style="width:200px;" >
											<option value="" selected></option>
											<%
												if (rsloaikh != null)
													while (rsloaikh.next()) {
														if (rsloaikh.getString("pk_seq").equals(obj.getLoaikh())) {
											%>
											   <option value="<%=rsloaikh.getString("pk_seq")%>" selected><%=rsloaikh.getString("ten")%></option>
											   <%
											   	} else {
											   %>
											   <option value="<%=rsloaikh.getString("pk_seq")%>"><%=rsloaikh.getString("ten")%></option>
											<%
												}
													}
											%>
									</select></TD>
								
								</TR>
								
								<%}} if(loai.equals("7")) { %>
								
								<TR>
								<TD class="plainlabel" >Th??ng</TD>
								<TD class="plainlabel">
									
									<SELECT name="khtt" id="khtt" style="width :200px" >
										<option value="1"> 6 th??ng ?????u n??m</option>
						      			 <option value="2"> 6 th??ng cu???i n??m</option>
								 	</SELECT>
								       	
	 							</TD>
	 							
	 							
								</TR>
								
								<TR>
								
									<TD class="plainlabel" >N??m </TD>
	 								<TD class="plainlabel">
	 								<select name="nam" id="nam"  style="width: 70px;" >
										
										<option value=0> </option>  
										<%
										  
	  										int n;
	  										for(n=2014;n<2020;n++){
	  									if(obj.getNam().equals(""+n)){
	  									%>
										<option value=<%=n%> selected="selected" > <%=n%></option> 
										<%
	 										}else{
	 									%>
										<option value=<%=n%> ><%=n%></option> 
										<%
	 										}
	 									 }
	 									%>
 									</select>
 									</td>
									
								</TR>
								<%-- <TR>
								<TD class="plainlabel"><%=Utility.GLanguage("NH??N VI??N B??N H??NG",session,jedis) %></TD>
									<TD class="plainlabel"><select name="tdvId" id="tdvId" style="width:200px;" >
											<option value="" selected></option>
											<%	if (rsNVBH != null){
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
													}}
											%>
									</select></TD>
								
								</TR> --%>
								
								<%} %>
								
								<%if(loai.equals("3") || loai.equals("4") || loai.equals("7")) {%>
								 <TR>
								<TD class="plainlabel"><%=Utility.GLanguage("NH??N VI??N B??N H??NG",session,jedis) %></TD>
									<TD class="plainlabel"><select name="tdvId" id="tdvId" style="width:200px;" >
											<option value="" selected></option>
											<%	if (rsNVBH != null){
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
													}}
											%>
									</select></TD>
								
								</TR>
								
								<%} %>
								
								<% if(loai.equals("9")) { %>
								
								<TR>
								<TD class="plainlabel" >S??? h??a ????n t???</TD>
								<TD class="plainlabel">
									<input type="text"  name="tungay" value="<%=obj.getKmtungay()%>"/> 	
	 							</TD>
	 							</tr>
	 			
	 							<TR>
								<TD class="plainlabel" >S??? h??a ????n ?????n</TD>
								<TD class="plainlabel">
									<input type="text"  name="denngay"  value="<%=obj.getKmdenngay()%>"/> 	
	 							</TD>
	 							</tr>
	 			
	 						<TR>
								<TD class="plainlabel"><%=Utility.GLanguage("NH??N VI??N B??N H??NG",session,jedis) %></TD>
									<TD class="plainlabel"><select name="tdvId" id="tdvId" style="width:200px;" >
											<option value="" selected></option>
											<%	if (rsNVBH != null){
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
													}}
											%>
									</select></TD>
								
								</TR>
								
								<TR>
								<TD class="plainlabel">Nh??n vi??n giao nh???n</TD>
									<TD class="plainlabel"><select name="nvgnid" id="nvgnid" style="width:200px;" >
											<option value="" selected></option>
											<%	if (rsNvgn != null){
													while (rsNvgn.next()) {
														if (rsNvgn.getString("pk_seq").equals(obj.getNvgnId())) {
											%>
											   <option value="<%=rsNvgn.getString("pk_seq")%>" selected><%=rsNvgn.getString("ten")%></option>
											   <%
											   	} else {
											   %>
											   <option value="<%=rsNvgn.getString("pk_seq")%>"><%=rsNvgn.getString("ten")%></option>
											<%
												}
													}}
											%>
									</select></TD>
								
								</TR>
								
								<%} %>
	 			
								
								
							    <TR>
									<TD colspan="2" class="plainlabel">
									<a class="button2" href="javascript:submitform()" >
	<img style="top: -4px;" src="../images/button.png" alt=""><%=Utility.GLanguage("T???o b??o c??o",session,jedis) %></a>&nbsp;&nbsp;&nbsp;&nbsp;                                    </TD>
								</TR>
							</TABLE>
							</FIELDSET>						</TD>
						</TR>
					</TABLE>					</TD>
				</TR>
			</TABLE>
		</TD>
	</TR>
</TABLE>
</form>

<%} %>
</BODY>
</HTML>
