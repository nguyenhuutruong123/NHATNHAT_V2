<%@page import="geso.dms.distributor.beans.chitieunpp.IChitieuSKUInList"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%@page import="geso.dms.distributor.servlets.chitieunpp.*"%>
<%@page import="java.util.Calendar"%>
<%@page import="java.util.Date"%>
<%@page import="java.sql.SQLException"%>
<%@ page  import = "java.util.Iterator" %>
<%@ page  import = "java.util.List" %>
<%@ page  import = "java.util.ArrayList" %>
<%@ page  import = "java.sql.ResultSet" %>
<%@ page  import = "geso.dms.center.util.*" %>


<%	String userId = (String) session.getAttribute("userId");  
	String userTen = (String) session.getAttribute("userTen");  	
	String sum = (String) session.getAttribute("sum");
	Utility util = (Utility) session.getAttribute("util");
	IChitieuSKUInList obj=(IChitieuSKUInList)session.getAttribute("obj");
	ResultSet chitieuSKU = obj.getChitieuSKUInRs();
	if(!util.check(userId, userTen, sum)){
		response.sendRedirect(request.getContextPath() + "/redirect.jsp");
	}else{ %>
<%	

	
%>
<% obj.setNextSplittings(); %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd"> <% Utility Utilback = new Utility(); %>
<html>
<head>
	<TITLE><%= getServletContext().getInitParameter("TITLENAME") %></TITLE>
	<META http-equiv="Content-Type" content="text/html; charset=utf-8">
	<META http-equiv="Content-Style-Type" content="text/css">
<LINK rel="stylesheet" href="../css/main.css" type="text/css">
<LINK rel="stylesheet" href="../css/calendar.css" type="text/css">
<LINK rel="stylesheet" type="text/css" media="print" href="../css/impression.css">
<script type="text/javascript" language="JavaScript" src="../scripts/jquery.js"></script>
<script type="text/javascript" language="JavaScript" src="../scripts/Numberformat.js"></script>
	<link type="text/css" rel="stylesheet" href="../css/mybutton.css">
	<SCRIPT language="javascript" type="text/javascript">
	function clearform()
	{ 
	    document.forms['ctsku'].nam.value= "";
	    document.forms['ctsku'].thang.value= "";
		document.forms['ctsku'].submit();
	}

	function submitform()
	{
		document.forms['ctsku'].action.value= 'search';
		document.forms['ctsku'].submit();
	}
	function thongbao()
	 {
		 var tt = document.forms["ctsku"].msg.value;
	 	 if(tt.length>0)
	     	alert(document.forms["ctsku"].msg.value);
	 }
	 
	function newform()
	{
		document.forms['ctsku'].action.value= 'new';
		document.forms['ctsku'].submit();
	}
	</SCRIPT>
</head>
<BODY leftmargin="0" bottommargin="0" topmargin="0" rightmargin="0">
<form name="ctsku" method="post" action="../../ChiTieuSKUInSvl">
<input type="hidden" name="userId" value="<%= userId %>" >
<input type="hidden" name="nppId" value="<%= obj.getNppId() %>" >
<input type="hidden" name="msg" value='<%= obj.getMsg() %>'>
<input type="hidden" name="task" value="TT">
<input type="hidden" name="currentPage" value="<%= obj.getCurrentPage() %>" >
<script language="javascript" type="text/javascript">
    thongbao();
</script> 
<input type="hidden" name="action" value="1" >
<TABLE width="100%" border="0" cellspacing="0" cellpadding="0" height="100%"> 
    <TR>
        <TD colspan="4" align='left' valign='top' bgcolor="#FFFFFF"><!--begin body Dossier-->
        <!--begin common dossier info---> <!--End common dossier info--->
        <TABLE width="100%" cellpadding="0" cellspacing="2">
            <TR>
                <TD align="left" class="tbnavigation">

                    <TABLE width="100%" border="0" cellpadding="0" cellspacing="0">
                        <TR height="22">
                            <TD align="left" colspan="2" class="tbnavigation">&nbsp; Thi???t l???p ch??? ti??u > Ch??? ti??u SKU In</TD>  
                            <TD colspan="2" align="right" class="tbnavigation">Ch??o m???ng  <%= obj.getNppTen() %>  </TD>
                        </tr>
                    </TABLE>
                </TD>
            </TR>
        </TABLE>
        <TABLE width="100%" cellpadding="0" cellspacing="1">
            <TR>
                <TD>
                    <TABLE border="0" width="100%" cellpadding="0" cellspacing="0">
                        <TR>
                            <TD width="100%" align="center" >
                            <FIELDSET>
                              <LEGEND class="legendtitle">&nbsp;<%=Utility.GLanguage("Ti??u ch?? t??m ki???m",session,jedis) %> &nbsp;</LEGEND>

                            <TABLE  width="100%" cellpadding="6" cellspacing="0">
                             <TR>
                             <TD width="15%" class="plainlabel" >Th??ng </TD>
								<TD class="plainlabel">
									<select name="thang" style="width: 80px">
									<option value=0> </option>  
									<%
									int k=1;
									for(k=1; k <= 12; k++ ){										
									  if(obj.getThang().trim().equals(k+"") ) {
									%>
										<option value=<%= k %> selected="selected" > <%= k %></option> 
									<%  }else{  %>
										<option value=<%= k %> > <%= k %></option> 
									<% } }%>
									</select>
								</TD>
                             </TR>
                              <TR>
                             <TD width="20%" class="plainlabel" >N??m </TD>
								<TD class="plainlabel">
									<select name="nam"  style="width :80px">
									<option value=0> </option>  
									<%
									Calendar cal=Calendar.getInstance();
									int year_=cal.get(Calendar.YEAR);
									for(int n=2008; n<year_+3; n++) {
									  if(obj.getNam().equals( Integer.toString(n)) ){									  
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
                             </TR >
                              <TR>
								<TD class="plainlabel"><%=Utility.GLanguage("Tr???ng th??i",session,jedis) %></TD>
								<TD class="plainlabel"><select id="trangthai" name="trangthai" >
	
										<%if (obj.getTrangthai().trim().equals("0")){ %>
										<option value=""></option>
										<option value="0" selected>Ch??a ch???t</option>
										<option value="1">???? ch???t</option>
										<option value="2">???? h???y</option>
									
										<%}else{ 							
		  								if (obj.getTrangthai().trim().equals("1")){ %>
										<option value=""></option>
										<option value="0">Ch??a ch???t</option>
										<option value="1" selected>???? ch???t</option>																
										<option value="2">???? h???y</option>
										<%}else{ 
										if (obj.getTrangthai().trim().equals("2")){ %>
										<option value=""></option>
										<option value="0">Ch??a ch???t</option>
										<option value="1">???? ch???t</option>																
										<option value="2" selected>???? h???y</option>
										<%}
			  
										else{%>
										<option value="" selected></option>
										<option value="0">Ch??a ch???t</option>
										<option value="1">???? ch???t</option>																
										<option value="2">???? h???y</option>
	
										<%}}} %>
								</select>
								</TD>
							</TR>
                             <tr class="plainlabel"> <td colspan="2" > 
                             <a class="button3" href="javascript:submitform()">
                           	<img style="top: -4px;" src="../images/Search30.png" alt=""><%=Utility.GLanguage("T??m ki???m",session,jedis) %> </a> &nbsp;&nbsp;&nbsp;
                           		<a class="button2" href="javascript:clearform()">
							<img style="top: -4px;" src="../images/button.png" alt=""><%=Utility.GLanguage("Nh???p l???i",session,jedis) %></a>&nbsp;&nbsp;&nbsp;&nbsp;	
                           	
                             </td> </tr>
                            </TABLE>

                            </FIELDSET>
                            </TD>

                        </TR>
                    </TABLE>
                    </TD>
                </TR>

                <TR>
                    <TD width="100%">
                        <FIELDSET>
                            <LEGEND class="legendtitle">&nbsp;Qu???n l?? ch??? ti??u &nbsp;&nbsp;	   
                                                    
							<!-- <a class="button3" href="javascript:newform()">
                           	<img style="top: -4px;" src="../images/New.png" alt=""><%=Utility.GLanguage("T???o m???i",session,jedis) %> </a>     -->                        
	                              
                            </LEGEND>
    
                            <TABLE class="" width="100%">
                        <TR>
                            <TD width="98%">
                            <TABLE width="100%" border="0" cellspacing="1" cellpadding="4">
                                <TR class="tbheader">
                                    <TH width="">Th??ng </TH>
                                    <TH width="">N??m </TH>
                                    <TH width=""><%=Utility.GLanguage("Di???n gi???i",session,jedis) %> </TH>
                                    <TH width=""><%=Utility.GLanguage("Tr???ng th??i",session,jedis) %></TH>
                                    <TH width=""><%=Utility.GLanguage("Ng??y t???o",session,jedis) %></TH>
                                    <TH width=""><%=Utility.GLanguage("Ng?????i t???o",session,jedis) %></TH>
                                    <TH width=""><%=Utility.GLanguage("Ng??y s???a",session,jedis) %></TH>
                                    <TH width=""><%=Utility.GLanguage("Ng?????i s???a",session,jedis) %> </TH>
                                    <TH width="" align="center">&nbsp;T??c v???</TH>
                                </TR>
                                <% 
                                   
                                    int m = 0;
                                    String lightrow = "tblightrow";
                                    String darkrow = "tbdarkrow";
                                    if(chitieuSKU != null)
                                    while ( chitieuSKU.next())
                                    {
                                       
                                        if (m % 2 != 0) {%>                     
                                            <TR class= <%=lightrow%> >
                                        <%} else {%>
                                            <TR class= <%= darkrow%> >
                                        <%}%>
                                                <TD align="left"><%= chitieuSKU.getInt("THANG") %></TD>                                   
                                                <TD><%= chitieuSKU.getInt("NAM")%></TD>
												<TD><%= chitieuSKU.getString("diengiai")%></TD>
                                                <%if( chitieuSKU.getString("trangthai").trim().equals("0") ) {%>
                                                <TD align="center">Ch??a ch???t</TD>
                                                <%}else if(chitieuSKU.getString("trangthai").trim().equals("1")){ %>
                                                <TD align="center">???? ch???t</TD>
                                                <%} else{%> 
                                                <TD align="center">???? h???y</TD>
                                                <%} %>
                                                <TD align="center"><%= chitieuSKU.getString("NGAYTAO")%> </TD>
                                                <TD align="center"><%= chitieuSKU.getString("NGUOITAO")%></TD>
                                                <TD align="center"><%= chitieuSKU.getString("NGAYSUA")%> </TD>
                                                <TD align="center"><%= chitieuSKU.getString("NGUOISUA")%> </TD>
                                                <TD align="center"> 
                                                <%-- <%
      
                                                if(chitieuSKU.getInt("trangthai")==0)
                                                {%>
					                                <A href = "../../ChiTieuSKUInUpdateSvl?userId=<%=userId%>&update=<%= chitieuSKU.getString("pk_seq") %>"><IMG src="../images/Edit20.png" alt="C???p nh???t" title="Cap nhat" border=0></A>&nbsp;
					                                <A href = "../../ChiTieuSKUInSvl?userId=<%=userId%>&delete=<%= chitieuSKU.getString("pk_seq") %>"><img src="../images/Delete20.png" alt="Xoa" width="20" height="20" longdesc="Xoa" border=0 onclick="if(!confirm('B???n c?? mu???n x??a ch??? ti??u n??y?')) return false;"></A>									
							                    	<A href = "../../ChiTieuSKUInSvl?userId=<%=userId%>&chot=<%= chitieuSKU.getString("pk_seq") %>"><img src="../images/Chot.png" alt="Ch???t" width="20" height="20" longdesc="Xoa" border=0 onclick="if(!confirm('B???n c?? mu???n ch???t ch??? ti??u n??y?')) return false;"></A>	
							                    <%}else{ %> --%>
							              
							                       <A href = "../../ChiTieuSKUInUpdateSvl?userId=<%=userId%>&xem=<%= chitieuSKU.getString("pk_seq") %>&view=NPP"><img src="../images/Display20.png" alt="Xem" width="20" height="20" longdesc="Xoa" border=0 onclick=""></A>									
<%-- 							                    <%} %> --%>
							                    </TD>
                                            </TR>
                                        <% m++; }; 
                                        } %>  
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
												
												System.out.println("Current page:" + obj.getNxtApprSplitting());
												System.out.println("List page:" + listPage[i]);
												
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