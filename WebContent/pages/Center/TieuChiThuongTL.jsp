<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@page import="geso.dms.center.beans.tieuchithuong.*"%>
<%@page import="geso.dms.center.beans.tieuchithuong.imp.*"%>
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
		response.sendRedirect("/SOHACO/index.jsp");
	}else{ %>
<%	
	ITieuchithuongTLList obj=(ITieuchithuongTLList)session.getAttribute("obj");
	ResultSet tieuchiSKU = obj.getTieuchiSKUInRs();
	int[] quyen = new  int[6];
	quyen = util.Getquyen("TieuchithuongTLSvl","",userId);
%>
<% Utility Util = new Utility(); %>
<%String url = util.getChuyenNguUrl("TieuchithuongTLSvl", "",session); %>
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
	    document.forms['tcsku'].nam.value= "";
	    document.forms['tcsku'].thang.value= "";
	    document.forms['tcsku'].trangthai.value= "";
	    document.forms['tcsku'].diengiai.value= "";
		document.forms['tcsku'].submit();
	}

	function submitform()
	{
		document.forms['tcsku'].action.value= 'search';
		document.forms['tcsku'].submit();
	}

	function newform()
	{
		document.forms['tcsku'].action.value= 'new';
		document.forms['tcsku'].submit();
	}

	function Chot(id){
		
		document.forms['tcsku'].IdXoa.value = id;
		document.forms['tcsku'].action.value= 'Chot';
		document.forms['tcsku'].submit();
	}
	
</SCRIPT>
	
	

	
	
	
	
</head>
<BODY leftmargin="0" bottommargin="0" topmargin="0" rightmargin="0" >



<form name="tcsku" method="post" action="../../TieuchithuongTLSvl">
<% redis.clients.jedis.Jedis jedis =  geso.dms.center.util.Utility.getJedis(); %><% geso.dms.center.util.Csrf csdr=new geso.dms.center.util.Csrf(request,response,true,false,true);%>
 <input type="hidden" name="<%=csdr.get_tokenName() %>" value='<%=csdr.get_tokenValue() %>'>

<input type="hidden" name="IdXoa" value=''>
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
                            <TD align="left" colspan="2" class="tbnavigation">&nbsp;<%=url %></TD>  
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
                             <TD width="15%" class="plainlabel" ><%=Utility.GLanguage("Th??ng",session,jedis) %> </TD>
								<TD class="plainlabel">
									<select name="thang" style="width: 100px" onchange="submitform();">
									<option value= ""> </option>  
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
                             <TD width="20%" class="plainlabel" ><%=Utility.GLanguage("n??m",session,jedis) %> </TD>
								<TD class="plainlabel">
									<select name="nam"  style="width :100px" onchange="submitform();">
									<option value= ""> </option>  
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
                              
                              
                              <TR class="plainlabel">
	                              <TD class="plainlabel"><%=Utility.GLanguage("Di???n gi???i",session,jedis) %>/Scheme</TD>
	                              <TD class="plainlabel">
	                              
	                             		<input name= "diengiai" type="text" value='<%= obj.getDiengiai()%>' onchange="submitform();">
	                              </TD>
                              </TR>
                              
                              <TR class="plainlabel">
	                              <TD class="plainlabel"><%=Utility.GLanguage("Tr???ng th??i",session,jedis) %></TD>
	                              <TD class="plainlabel">
	                              	<select name = "trangthai" onchange="submitform();">
	                              	<%if(obj.getTrangthai().equals("0")) {%>
	                              		<option value="" ></option>
	                              		<option value="0" selected="selected"><%=Utility.GLanguage("Ch??a ch???t",session,jedis) %></option>
	                              		<option value="1" ><%=Utility.GLanguage("???? ch???t",session,jedis) %></option>
	                              	<%} else if(obj.getTrangthai().equals("1")) {%>
	                              		<option value="" ></option>
	                              		<option value="0" ><%=Utility.GLanguage("Ch??a ch???t",session,jedis) %></option>
	                              		<option value="1" selected="selected"><%=Utility.GLanguage("???? ch???t",session,jedis) %></option>
	                              	<%}else {%>
	                              		<option value="" selected="selected"></option>
	                              		<option value="0" ><%=Utility.GLanguage("Ch??a ch???t",session,jedis) %></option>
	                              		<option value="1" ><%=Utility.GLanguage("???? ch???t",session,jedis) %></option>
	                              	<%} %>
	                              	</select>
							
	                              </TD>
                              </TR>
                              
                             <tr class="plainlabel"> 
	                             <td colspan="2" > 
	                           		<a class="button2" href="javascript:clearform()">
										<img style="top: -4px;" src="../images/button.png" alt=""><%=Utility.GLanguage("Nh???p l???i",session,jedis) %></a>&nbsp;&nbsp;&nbsp;&nbsp;	
	                             </td> 
                             </tr>
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
                            <LEGEND class="legendtitle">&nbsp;<%=Utility.GLanguage("Ti??u ch?? th?????ng t??ch l??y",session,jedis) %> &nbsp;&nbsp;	   
                              <%if(quyen[0]!=0){ %>                      
							<a class="button3" href="javascript:newform()">
                           			<img style="top: -4px;" src="../images/New.png" alt=""><%=Utility.GLanguage("T???o m???i",session,jedis) %> </a>                            
	                            <%} %>  
                            </LEGEND>
    
                            <TABLE class="" width="100%">
                        <TR>
                            <TD width="98%">
                            <TABLE width="100%" border="0" cellspacing="1" cellpadding="4">
                                <TR class="tbheader">
                                	<TH width=""><%=Utility.GLanguage("Scheme",session,jedis) %> </TH>
                                    <TH width=""><%=Utility.GLanguage("T??? ng??y",session,jedis) %> </TH>
                                    <TH width=""><%=Utility.GLanguage("?????n ng??y",session,jedis) %> </TH>
                                    <TH width=""><%=Utility.GLanguage("Di???n gi???i",session,jedis) %> </TH>
                                    <TH width=""><%=Utility.GLanguage("Tr???ng th??i",session,jedis) %> </TH>
                                    <TH width=""><%=Utility.GLanguage("Ng??y t???o",session,jedis) %> </TH>
                                    <TH width=""><%=Utility.GLanguage("Ng?????i t???o",session,jedis) %> </TH>
                                    <TH width=""><%=Utility.GLanguage("Ng??y s???a",session,jedis) %> </TH>
                                    <TH width=""><%=Utility.GLanguage("Ng?????i s???a",session,jedis) %> </TH>
                                    <TH width="" align="center">&nbsp;<%=Utility.GLanguage("T??c v???",session,jedis) %></TH>
                                </TR>
                                <% 
                                   
                                    int m = 0;
                                    String lightrow = "tblightrow";
                                    String darkrow = "tbdarkrow";
                                    if(tieuchiSKU != null)
                                    while ( tieuchiSKU.next()){
                                       
                                        if (m % 2 != 0) {%>                     
                                            <TR class= <%=lightrow%> >
                                        <%} else {%>
                                            <TR class= <%= darkrow%> >
                                        <%}%>
                                        		<TD><%= tieuchiSKU.getString("SCHEME")%></TD>
                                                <TD align="left"><%= tieuchiSKU.getString("THANG") %></TD>                                   
                                                <TD><%= tieuchiSKU.getString("NAM")%></TD>
												<TD><%= tieuchiSKU.getString("DIENGIAI")%></TD>
                                                <% if( tieuchiSKU.getString("trangthai").trim().equals("0") ) {%>
                                                <TD align="center">Ch??a ch???t</TD>
                                                <%}else{ %>
                                                <TD align="center">???? ch???t</TD>
                                                <%} %> 
                                                <TD align="center"><%= tieuchiSKU.getString("NGAYTAO")%> </TD>
                                                <TD align="center"><%= tieuchiSKU.getString("NGUOITAO")%></TD>
                                                <TD align="center"><%= tieuchiSKU.getString("NGAYSUA")%> </TD>
                                                <TD align="center"><%= tieuchiSKU.getString("NGUOISUA")%> </TD>
                                                <TD align="center"> 
                                                <% if( tieuchiSKU.getString("trangthai").trim().equals("0") ) { %>
                                                	<%if(quyen[Utility.SUA]!=0) {%>	
					                                <A href = "../../RouterSvl?task=<%= Util.dongMa(getServletContext().getInitParameter("RedirectNoScript") +"TieuchithuongTLUpdateSvl?userId="+userId+"&update="+tieuchiSKU.getString("pk_seq")+"") %>"><IMG src="../images/Edit20.png" alt="Cap nhat" title="Cap nhat" border=0></A>&nbsp;
					                                <%} %>
					                               
					                                 <%if(quyen[Utility.CHOT]!=0){ %>
                                                        	<A href="javascript:Chot('<%=tieuchiSKU.getString("pk_seq")  %>');">
                                                        		<img src="../images/Chot.png" alt="Chot" title="Ch???t" width="20" height="20" longdesc="Xoa" border=0 
                                                        		onclick="if(!confirm('B???n c?? mu???n ch???t t??ch l??y n??y?')) return false;"></A>
                                                    <%} %>
					                                <%if(quyen[Utility.XOA]!=0) {%>	
					                                <A href = "../../RouterSvl?task=<%= Util.dongMa(getServletContext().getInitParameter("RedirectNoScript") +"TieuchithuongTLSvl?userId="+userId+"&delete="+ tieuchiSKU.getString("pk_seq")+"") %>"><img src="../images/Delete20.png" alt="Xoa" width="20" height="20" longdesc="Xoa" border=0 onclick="if(!confirm('B???n c?? mu???n x??a t??ch l??y n??y?')) return false;"></A>									
							                    	<%} %>
							                    <%} else { %>
							                    	<%if(quyen[Utility.XEM]!=0) {%>
							                    	<A href = "../../RouterSvl?task=<%= Util.dongMa(getServletContext().getInitParameter("RedirectNoScript") +"TieuchithuongTLUpdateSvl?userId="+userId+"&display="+ tieuchiSKU.getString("pk_seq")+"") %>"><IMG src="../images/Display20.png" alt="Hien thi" title="Hien thi" border=0></A>&nbsp;
							                    	<%} %>
							                   	
							                    	<%if(tieuchiSKU.getString("dktl").length() < 2 && tieuchiSKU.getString("dktl").trim().equals("1")) {%>
							                    		<A href = "../../RouterSvl?task=<%= Util.dongMa(getServletContext().getInitParameter("RedirectNoScript") +"TieuchithuongTLSvl?userId="+userId+"&boduyet="+ tieuchiSKU.getString("pk_seq")+"") %>"><IMG src="../images/unChot.png" alt="Bo duyet" width="20" height="20" title="Bo duyet" border=0></A>&nbsp;
							                    	<%} 
							                    	
							                    } %>
							                    <%if(quyen[Utility.THEM]!=0) {%>	
					                                <A href = "../../RouterSvl?task=<%= Util.dongMa(getServletContext().getInitParameter("RedirectNoScript") +"TieuchithuongTLUpdateSvl?userId="+userId+"&copy="+ tieuchiSKU.getString("pk_seq")+"") %>"><IMG src="../images/copy20.png" alt="Copy" title="Copy" border=0></A>&nbsp;
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
<%geso.dms.center.util.Utility.JedisClose(jedis); %>
</BODY>
</HTML>
<% 	
	try
{
		if(tieuchiSKU != null)
			tieuchiSKU.close();
	}
	catch (SQLException e) {} %>
<%}%>