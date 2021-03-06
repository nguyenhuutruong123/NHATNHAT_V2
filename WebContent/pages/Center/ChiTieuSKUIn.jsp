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
	if(!util.check(userId, userTen, sum)){
		response.sendRedirect(request.getContextPath() + "/redirect.jsp");
	}else{ %>
<%	
	IChitieuSKUInList obj =(IChitieuSKUInList)session.getAttribute("obj");
	ResultSet chitieuSKU = obj.getChitieuSKUInRs();
	
%>
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
<input type="hidden" name="task" value="TT" >
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
                            <TD colspan="2" align="right" class="tbnavigation"><%=Utility.GLanguage("Ch??o m???ng b???n",session,jedis) %>  <%= userTen %></TD>
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
									<select name="thang" style="width: 50px" onchange="submitform()">
									<option value=0> </option>  
									<%
									int k=1;
									for(k=1; k <= 12; k++ ){
										
									  if(obj.getThang().equals(Integer.toString(k)) ) {
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
									<select name="nam"  style="width :50px" onchange="submitform()" >
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
                              
                             <tr class="plainlabel"> <td colspan="2" > 
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
                                                    
							<a class="button3" href="javascript:newform()">
                           	<img style="top: -4px;" src="../images/New.png" alt=""><%=Utility.GLanguage("T???o m???i",session,jedis) %> </a>                            
	                              
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
                                    while ( chitieuSKU.next()){
                                       
                                        if (m % 2 != 0) {%>                     
                                            <TR class= <%=lightrow%> >
                                        <%} else {%>
                                            <TR class= <%= darkrow%> >
                                        <%}%>
                                                <TD align="left"><%= chitieuSKU.getString("THANG") %></TD>                                   
                                                <TD><%= chitieuSKU.getString("NAM")%></TD>
												<TD><%= chitieuSKU.getString("DIENGIAI")%></TD>
                                                <%if( chitieuSKU.getString("trangthai").trim().equals("0") ) {%>
                                                <TD align="center">Ch??a ch???t</TD>
                                                <%}else{ %>
                                                <TD align="center">???? ch???t</TD>
                                                <%} %> 
                                                <TD align="center"><%= chitieuSKU.getString("NGAYTAO")%> </TD>
                                                <TD align="center"><%= chitieuSKU.getString("NGUOITAO")%></TD>
                                                <TD align="center"><%= chitieuSKU.getString("NGAYSUA")%> </TD>
                                                <TD align="center"><%= chitieuSKU.getString("NGUOISUA")%> </TD>
                                                <TD align="center"> 
                                                <%if( chitieuSKU.getString("trangthai").trim().equals("0") ) {%>
					                                <A href = "../../ChiTieuSKUInTTUpdateSvl?userId=<%=userId%>&update=<%= chitieuSKU.getString("pk_seq") %>"><IMG src="../images/Edit20.png" alt="Cap nhat" title="Cap nhat" border=0></A>&nbsp;
					                                <A href = "../../ChiTieuSKUInSvl?userId=<%=userId%>&delete=<%= chitieuSKU.getString("pk_seq") %>&&view=TT"><img src="../images/Delete20.png" alt="Xoa" width="20" height="20" longdesc="Xoa" border=0 onclick="if(!confirm('B???n c?? mu???n x??a ch??? ti??u n??y?')) return false;"></A>									
							                   <%} else { %>
							                   		<A href = "../../ChiTieuSKUInTTUpdateSvl?userId=<%=userId%>&display=<%= chitieuSKU.getString("pk_seq") %>"><IMG src="../images/Display20.png" alt="Hien thi" title="Hien thi" border=0></A>&nbsp;
							                   <%} %>
							                    </TD>
                                            </TR>
                                        <% m++; } %>  
                                          <TR class="tbfooter" >
									<TD align="center" colspan="15"> | < < 1 to 20 of 100 > >| </TD>
					</TR>                                                  
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
	try
    {
		if(chitieuSKU != null)
			chitieuSKU.close();
		/* if(obj != null)
		{
			obj.DbClose();
			obj = null;
		}
		session.setAttribute("obj", null); */
	}
	catch (SQLException e) {} %>
<%}%>