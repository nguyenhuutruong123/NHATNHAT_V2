s<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@page import="geso.dms.distributor.beans.chitieunpp.*"%>
<%@page import="geso.dms.distributor.beans.chitieunpp.imp.*"%>
<%@page import="java.util.Calendar"%>
<%@page import="java.util.Date"%>
<%@page import="javax.xml.crypto.Data"%>
<%@ page  import = "java.util.Iterator" %>
<%@ page  import = "java.sql.ResultSet" %>
<%@ page  import = "geso.dms.center.util.*" %>
<%@page import="java.sql.SQLException"%>
<%@ page  import = "geso.dms.center.util.*" %>
<%@ page  import = "java.util.ArrayList" %>
<%@ page  import = "java.util.List" %>
<%	String userId = (String) session.getAttribute("userId");  
	String userTen = (String) session.getAttribute("userTen");  	
	String sum = (String) session.getAttribute("sum");
	Utility util = (Utility) session.getAttribute("util");
	if(!util.check(userId, userTen, sum)){
		response.sendRedirect(request.getContextPath() + "/redirect.jsp");
	}else{ %>

<%
	IChitieuSKUInTT obj =(IChitieuSKUInTT)session.getAttribute("obj");
	List<IChitieusku> listddkd= (List<IChitieusku>) obj.getSpList();
	 ResultSet dvkd = (ResultSet)obj.getDvkdRs();
	 ResultSet kenh = (ResultSet)obj.getKbhRs();
%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"> <% Utility Utilback = new Utility(); %>
<HTML>
<HEAD>
<TITLE><%= getServletContext().getInitParameter("TITLENAME") %></TITLE>
<META http-equiv="Content-Type" content="text/html; charset=utf-8">

<META http-equiv="Content-Style-Type" content="text/css">
<LINK rel="stylesheet" href="../css/main.css" type="text/css">
<LINK rel="stylesheet" type="text/css" media="print" href="../css/impression.css">
<script type="text/javascript" language="JavaScript" src="../scripts/jquery.js"></script>
<script type="text/javascript" language="JavaScript" src="../scripts/Numberformat.js"></script>
<LINK rel="stylesheet" href="../css/main.css" type="text/css">
<link type="text/css" rel="stylesheet" href="../css/mybutton.css">
<SCRIPT language="JavaScript" type="text/javascript">
function save()
{	
		document.forms['ChitieuSKU'].removeAttribute('enctype', "multipart/form-data", 0);
	  var thang = document.forms["ChitieuSKU"].thang.value;
	  var diengiai = document.forms["ChitieuSKU"].diengiai.value;
	  var nam = document.forms["ChitieuSKU"].namxx.value;
	  var dvkd = document.forms["ChitieuSKU"].donvikinhdoanh.value;
	  var kbh = document.forms["ChitieuSKU"].kenhbanhang.value;
	 
	  if( thang == 0 )
	  {
		  document.forms["ChitieuSKU"].dataerror.value="Ch???n th??ng c???n ?????t ch??? ti??u ";
		  return;
	  }
	  if( nam == 0 )
	  {
		  document.forms["ChitieuSKU"].dataerror.value="Ch???n n??m c???n ?????t ch??? ti??u ";
		  return;
	  }
	  if( dvkd == 0 )
	  {
		  document.forms["ChitieuSKU"].dataerror.value="Ch???n ????n v??? kinh doanh";
		  return;
	  }
	  if( kbh == 0 )
	  {
		  document.forms["ChitieuSKU"].dataerror.value="Ch???n k??nh b??n h??ng ";
		  return;
	  }
	  if( diengiai== 0 )
	  {
		  document.forms["ChitieuSKU"].dataerror.value="Nh???p v??o di???n gi???i";
		  return;
	  }
	  if(document.getElementById("sku").rows.length==1)
	  {
		  document.forms["ChitieuSKU"].dataerror.value="Kh??ng c?? d??? li???u  n??o";
		  return;
	  }
	document.forms["ChitieuSKU"].action.value="save";
	document.forms["ChitieuSKU"].submit();
}

	
	function upload()
	{
		var up= document.getElementById("filename" );
		if( up.value == '' )
		  {
			  document.forms["ChitieuSKU"].dataerror.value="Kh??ng c?? file updload";
			  return;
		  }
		document.forms['ChitieuSKU'].setAttribute('enctype', "multipart/form-data", 0);
	    document.forms['ChitieuSKU'].submit();
	}

</SCRIPT>
<script type="text/javascript" language="JavaScript" src="../scripts/simplecalendar.js"></script>
<script type="text/javascript" src="../scripts/ajax.js"></script>
</HEAD>
<BODY leftmargin="0" bottommargin="0" topmargin="0" rightmargin="0">
<form name ="ChitieuSKU" ENCTYPE="multipart/form-data" method="post" action="../../ChiTieuSKUInUpdateSvl" >
<input type="hidden" name="userId" value='<%= userId %>' >
<input type="hidden" name="nkmId" value="">
<input type="hidden" name="action" value="0">
<input type="hidden" name="id" value="">
<TABLE width="100%" border="0" cellspacing="0" cellpadding="0"
	height="100%">
	<TR>
		<TD colspan="4" align='left' valign='top' bgcolor="#FFFFFF">
			<TABLE width="100%" border="0" cellpadding="0" cellspacing="1">
				<TR>
					<TD align="left" class="tbnavigation">
						<table width="100%" border="0" cellpadding="0" cellspacing="0">
						  <tr height="22">
							 <TD align="left" colspan="2" class="tbnavigation">&nbsp;Qu???n l?? ch??? ti??u > Ch??? ti??u SKU In > T???o m???i</TD> 
							 <TD colspan="2" align="right" class="tbnavigation">Ch??o m???ng  <%= userTen %>&nbsp;  </TD></tr>
						</table>
			 		</TD>
				</TR>
			</TABLE>
		<TABLE width="100%" border="0" cellpadding="0" cellspacing="1">
			<TR ><TD >
					<TABLE width="100%" border="0" cellpadding="0" cellspacing="0">
						<TR class = "tbdarkrow">
							<TD width="30" align="left"><A href="../../ChiTieuSKUInSvl?userId=<%=userId%>" ><img src="../images/Back30.png" alt="Quay ve"  title="Quay ve" border="1" longdesc="Quay ve" style="border-style:outset"></A></TD>
						    <TD width="2" align="left" ></TD>
						    <TD width="30" align="left" >
						    <div id="btnSave">
						    <A href="javascript: save()" ><IMG src="../images/Save30.png" title="Luu lai" alt="Luu lai" border = "1"  style="border-style:outset"></A>
						    </div>
						    </TD>
							<TD >&nbsp; </TD>						
						</TR>
					</TABLE>
			</TD></TR>
		</TABLE>
			<TABLE width="100%" border="0" cellpadding="0"  cellspacing="1" >
			  	<tr>
					<TD align="left" colspan="4" class="legendtitle">
						<FIELDSET>
						<LEGEND class="legendtitle"><%=Utility.GLanguage("B??o l???i nh???p li???u",session,jedis) %></LEGEND>
				
	    				<textarea name="dataerror"  style="width: 100% ; color:#F00 ; font-weight:bold"  style="width: 100% ; color:#F00 ; font-weight:bold" readonly="readonly" rows="1"><%= obj.getMsg() %></textarea>
						</FIELDSET>
				   </TD>
				</tr>			

				<TR>
				  <TD height="100%" width="100%">
						<FIELDSET >
						<LEGEND class="legendtitle" style="color:black">Th??ng tin ch??? ti??u </LEGEND>
						<TABLE border="0" width="100%" cellpadding="6" cellspacing="0">
							<TR>
								<TD width="20%" class="plainlabel" >Th??ng <FONT class="erroralert"> *</FONT></TD>
								<TD class="plainlabel">
									<select name="thang" style="width: 80px">
									<option value=0> </option>  
									<%
									int k=1;
									for(k=1; k <= 12; k++ )
									{
										System.out.println(obj.getThang());
									  if(obj.getThang().equals(String.valueOf(k)) ) {
									%>
										<option value=<%= k %> selected="selected" > <%= k %></option> 
									<%  }else{  %>
										<option value=<%= k %> > <%= k %></option> 
									<% } }%>
									</select>
								</TD>
							</TR>
							<TR>
							  	<TD class="plainlabel">N??m <FONT class="erroralert"> *</FONT></TD>
						  	  	<TD class="plainlabel">
									<select name="namxx" style="width :80px">
									<option value= ""> </option>  
									<%
									Calendar cal=Calendar.getInstance();
									int year_=cal.get(Calendar.YEAR);
									System.out.println(obj.getNam());
									for(int n=2008; n<year_+3; n++) {
									  if(obj.getNam().equals(String.valueOf(n)) ){									  
									%>
										<option value=<%=n %> selected="selected" > <%=n %></option> 
									<%
									  }else{
									 %>
										<option value=<%=n %> ><%=n %></option> 
									<% } }
									%>
									</select>
						  	  	</TD>
						  </TR>
						   <TR>								  
								    <TD class="plainlabel"><%=Utility.GLanguage("K??nh b??n h??ng",session,jedis) %></TD>
									<TD colspan="5" class="plainlabel"> 
						 			<SELECT name="kenhbanhang" id="kenhbanhang" >
							 			 <option value=""></option>
										  <% if(kenh != null){
											  try{ while(kenh.next()){ 													 
								    			if(kenh.getString("pk_seq").equals(obj.getKbhId())){ %>
								      				<option value='<%=kenh.getString("pk_seq")%>' selected><%=kenh.getString("ten") %></option>
								      			<%}else{ %>
								     				<option value='<%=kenh.getString("pk_seq")%>'><%=kenh.getString("ten") %></option>
								     			<%}}
											  }
											  catch(java.sql.SQLException e){}}
										  kenh.close(); %>	 
                                 			</SELECT></TD>														
								</TR>
						  	<TR>								  
								    <TD class="plainlabel">????n v??? kinh doanh </TD>
									<TD colspan="5" class="plainlabel"> 
						 			<SELECT name="donvikinhdoanh" id="donvikinhdoanh" >
							 			 <option value=""></option>
										  <% if(dvkd != null){
											  try{ while(dvkd.next()){ 													 
								    			if(dvkd.getString("pk_seq").equals(obj.getDvkdId())){ %>
								      				<option value='<%=dvkd.getString("pk_seq")%>' selected><%=dvkd.getString("ten") %></option>
								      			<%}else{ %>
								     				<option value='<%=dvkd.getString("pk_seq")%>'><%=dvkd.getString("ten") %></option>
								     			<%}}}catch(java.sql.SQLException e){}} dvkd.close(); %>	 
                                 			</SELECT></TD>														
								</TR>
						    <TR>
						  	  	<TD class="plainlabel"><%=Utility.GLanguage("Di???n gi???i",session,jedis) %></TD>
						  	  	<TD class="plainlabel">
						  	  		<input type="text" style="width :250px" name="diengiai" value="<%= obj.getDiengiai() %>"> 
						  	  	</TD>
						  	</TR>
						  	
						  	<TR>
						  	  	<TD class="plainlabel">Ch???n t???p tin</TD>
						  	  	<TD class="plainlabel">
						  	  		<INPUT type="file" name="filename" id="filename"  size="40" value=''>
						  	  	</TD>
						  	</TR>
						  	 <TR>							  	
						  	  	<TD class="plainlabel" colspan="2">
						  	  	<a class="button2"  href="javascript:upload()">
									<img style="top: -4px;" src="../images/button.png" alt="">C???p nh???t</a>&nbsp;&nbsp;&nbsp;&nbsp;	
						  	  	</TD>
						  	</TR>
						</TABLE>
						<TABLE ID="sku" width="100%" border="0" cellspacing="1" cellpadding="0" >							
								<TR class="tbheader">
									<TH align="center" width="15%">M?? s???n ph???m</TH>
									<TH align="center" width="40%">T??n s???n ph???m</TH>
									<TH  align="center" width="15%">S??? l?????ng</TH>
								</TR>
								<%
										int m=0;
										if(listddkd != null)
										{
											while (m < listddkd.size()){
												   IChitieusku ddkd = listddkd.get(m);
															
											%>
											<tr>
											<td align="center"><input type =text name="masp" readonly="readonly" style="width :100%;text-align: center;" value=<%=ddkd.getMasanpham() %>> </td>
											<td align="center"><input type =text name="tensp" style="width :100%;text-align: center;" readonly="readonly" value="<%=ddkd.getTensanpham() %>"> </td>
											<td align="right"> <input type =text name="soluong" style="width :100%;text-align: right;" value="<%= ddkd.getSoluong()%>" onkeypress="return keypress(event);"> </td>
										    </tr>
											<%
											m++;
								}
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
</form>
</BODY>
</HTML>
<%}%>