<%@page import="org.apache.poi.hssf.record.formula.functions.Npv"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@page import="geso.dms.distributor.beans.duyetbandunggia.*"%>
<%@page import="geso.dms.distributor.beans.duyetbandunggia.imp.*"%>
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
	IDuyetbandunggiaNppList obj=(IDuyetbandunggiaNppList)session.getAttribute("obj");
	ResultSet tieuchiSKU = obj.getTieuchiSKUInRs();
	ResultSet rsnppid=obj.getRsnppid();
	ResultSet rsmienid=obj.getRsMienid();
	
	String View=obj.getView();
	if(!View.equals(""))
		View="&view=TT";
	else
		View="";
	
	int[] quyen = new  int[6];
			quyen = util.Getquyen("DuyetbandunggiaNppSvl",View,userId);

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
	
	<script type="text/javascript" src="../scripts/jquery.min.1.7.js"></script>
<link href="../css/select2.css" rel="stylesheet" />
<script src="../scripts/select2.js"></script>
<script type="text/javascript">
	$(document).ready(function() {		
			$(".select2").select2();
        }); 		
		
</script>
	<SCRIPT language="javascript" type="text/javascript">
	function clearform()
	{ 
	    document.forms['tcsku'].nam.value= "";
	    document.forms['tcsku'].tuthang.value= "";
	    document.forms['tcsku'].denthang.value= "";
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
	</SCRIPT>
</head>
<BODY leftmargin="0" bottommargin="0" topmargin="0" rightmargin="0">
<form name="tcsku" method="post" action="../../DuyetbandunggiaNppSvl">
<input type="hidden" name="userId" value="<%= userId %>" >
<input type="hidden" name="task" value="TT" >
<input type="hidden" name="action" value="1" >
<input type="hidden" name="view" value="<%=obj.getView()%>" >
<input type="hidden" name="nppId" value="<%= obj.getNppId() %>" >
<TABLE width="100%" border="0" cellspacing="0" cellpadding="0" height="100%"> 
    <TR>
        <TD colspan="4" align='left' valign='top' bgcolor="#FFFFFF">
        <TABLE width="100%" cellpadding="0" cellspacing="2">
            <TR>
                <TD align="left" class="tbnavigation">
                    <TABLE width="100%" border="0" cellpadding="0" cellspacing="0">
                        <TR height="22">
                            <TD align="left" colspan="2" class="tbnavigation">&nbsp;Qu???n l?? khuy???n m???i > T??ch l??y > Duy???t b??n ????ng gi?? </TD>  
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
                             	<TD width="100px" class="plainlabel" >T??? th??ng </TD>
								<TD width="250px" class="plainlabel" >
									<select name="tuthang" style="width: 100px" onchange="submitform();">
									<option value= ""> </option>  
									<%
									int k=1;
									for(k=1; k <= 12; k++ ){
										
									  if(obj.getTuthang().equals(Integer.toString(k)) ) {
									%>
										<option value=<%= k %> selected="selected" > <%= k %></option> 
									<%  }else{  %>
										<option value=<%= k %> > <%= k %></option> 
									<% } }%>
									</select>
								</TD>
								<TD width="100px" class="plainlabel" >?????n th??ng </TD>
								<TD class="plainlabel" >
									<select name="denthang" style="width: 100px" onchange="submitform();">
									<option value= ""> </option>  
									<%
									k=1;
									for(k=1; k <= 12; k++ ){
										
									  if(obj.getDenthang().equals(Integer.toString(k)) ) {
									%>
										<option value=<%= k %> selected="selected" > <%= k %></option> 
									<%  }else{  %>
										<option value=<%= k %> > <%= k %></option> 
									<% } }%>
									</select>
								</TD>
                             </TR>
                              <TR>
                             	<TD class="plainlabel" >N??m </TD>
								<TD class="plainlabel"  >
									<select name="nam"  style="width :100px" onchange="submitform();">
									<option value= ""> </option>  
									<%
									Calendar cal=Calendar.getInstance();
									int year_= cal.get(Calendar.YEAR);
									for(int n = 2008; n < year_ + 3; n++) {
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
								
								<TD class="plainlabel" >nh?? ph??n ph???i </TD>
								<TD class="plainlabel"  >
									<select name="npplist"  class="select2"  onchange="submitform();" style="width: 200px">
									<option value= "" selected="selected"> </option>  
									<% if(rsnppid!=null ) { while (rsnppid.next()){%>
											<option value= "<%=rsnppid.getString("pk_seq") %>" <% if(rsnppid.getString("pk_seq").equals(obj.getNppidlist())){ %> selected="selected" <%} %>><%=rsnppid.getString("ten") %> </option>  
									<%}} %>
									
									</select>
								</TD>
								
                             </TR >
                             
                             <%if(obj.getView().length()>0) {%>
                             <TR>
                             
                             		
                             	<TD class="plainlabel" >Mi???n </TD>
								<TD class="plainlabel"  >
									<select name="mien"  style="width :100px" onchange="submitform();">
									<option value= ""> </option>
									<%if(rsmienid!=null){
										while(rsmienid.next())
										{
										%>  
									<option value="<%=rsmienid.getString("pk_seq")%>" <% if(rsmienid.getString("pk_seq").equals(obj.getMienid())){ %> selected="selected" <%} %>><%=rsmienid.getString("ten") %> </option>
									<%} }%>  
									
									</select>
								</TD>
                             	
                             	<TD class="plainlabel" ></TD>
								<TD class="plainlabel"  ></TD>
                             </TR>
                              <%} %>
                             <tr class="plainlabel"> 
	                             <td colspan="4" > 
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
                            <LEGEND class="legendtitle">Duy???t b??n ????ng gi?? &nbsp;&nbsp;	   
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
                                    <TH align="center" style="width: 8%">Th??ng</TH>
                                    <TH align="center" style="width: 8%">N??m</TH>
                                    <TH align="center" style="width: 13%"><%=Utility.GLanguage("Di???n gi???i",session,jedis) %></TH>
                                    <%if(obj.getView().length()>0){ %>
                                     <TH align="center" style="width: 12%">?????i t??c</TH>
                                     <%} %>
                                    <TH align="center" style="width: 8%"><%=Utility.GLanguage("Tr???ng th??i",session,jedis) %></TH>
                                    <TH align="center" style="width: 8%"><%=Utility.GLanguage("Ng??y t???o",session,jedis) %></TH>
                                    <TH align="center" style="width: 12%"><%=Utility.GLanguage("Ng?????i t???o",session,jedis) %></TH>
                                    <TH align="center" style="width: 8%"><%=Utility.GLanguage("Ng??y s???a",session,jedis) %></TH>
                                    <TH align="center" style="width: 12%"><%=Utility.GLanguage("Ng?????i s???a",session,jedis) %></TH>
                                    <TH align="center" style="width: 10%" align="center"><%=Utility.GLanguage("T??c v???",session,jedis) %></TH>
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
                                                <TD align="center"><%= tieuchiSKU.getString("THANG") %></TD>                                   
                                                <TD align="center"><%= tieuchiSKU.getString("NAM")%></TD>
												<TD><%= tieuchiSKU.getString("DIENGIAI")%></TD>
												 <%if(obj.getView().length()>0){ %>
                                                <TD align="center"><%= tieuchiSKU.getString("ten")%> </TD>
                                                
                                                <%} %>
                                                <% if( tieuchiSKU.getString("trangthai").trim().equals("0") ) {%>
                                                <TD align="center">Ch??a duy???t</TD>
                                                <%}else{ %>
                                                <TD align="center">???? duy???t</TD>
                                                <%} %> 
                                               
                                                <TD align="center"><%= tieuchiSKU.getString("NGAYTAO")%> </TD>
                                                <TD ><%= tieuchiSKU.getString("NGUOITAO")%></TD>
                                                <TD align="center"><%= tieuchiSKU.getString("NGAYSUA")%> </TD>
                                                <TD ><%= tieuchiSKU.getString("NGUOISUA")%> </TD>
                                                <TD align="center"> 
                                                <% if( tieuchiSKU.getString("trangthai").trim().equals("0") ) { %>
                                                	<%if(quyen[2]!=0) {%>	
                                                	<%if(quyen[Utility.SUA]!=0){ %>

					                                <A href = "../../DuyetbandunggiaNppUpdateSvl?userId=<%=userId%>&update=<%= tieuchiSKU.getString("pk_seq") %>&view=<%=obj.getView()%> "><IMG src="../images/Edit20.png" alt="Cap nhat" title="C???p nh???t" border=0></A>&nbsp;
					                               <%} %>
					                               <%} %>
					                               	<%if(quyen[4]!=0){ %>
					                               	<%if(quyen[Utility.CHOT]!=0){ %>
					                               	
					                                <A href = "../../DuyetbandunggiaNppSvl?userId=<%=userId%>&duyet=<%= tieuchiSKU.getString("pk_seq") %>&view=<%=obj.getView()%>"><img src="../images/Chot.png" alt="Duyet" title="Duy???t" width="20" height="20" longdesc="Duyet" border=0 onclick="if(!confirm('B???n c?? mu???n duy???t duy???t b??n ????ng gi?? n??y?')) return false;"></A>&nbsp;
					                               <%} %>
					                                <%} %>
					                                <%if(quyen[1]!=0) {%>
					                                <%if(quyen[Utility.XOA]!=0){ %>
					                                
					                                <A href = "../../DuyetbandunggiaNppSvl?userId=<%=userId%>&delete=<%= tieuchiSKU.getString("pk_seq") %>&view=<%=obj.getView()%>"><img src="../images/Delete20.png" alt="Xoa" title="X??a" width="20" height="20" longdesc="Xoa" border=0 onclick="if(!confirm('B???n c?? mu???n x??a duy???t b??n ????ng gi?? n??y?')) return false;"></A>									
							                    	<%} %>
							                    	<%} %>
							                    <%} else { %>
							                    
							                    	<%if(quyen[Utility.HUYCHOT]!=0){ %>
							                    	<A href = "../../DuyetbandunggiaNppSvl?userId=<%=userId%>&unduyet=<%= tieuchiSKU.getString("pk_seq") %>&view=TT"><img src="../images/unChot.png" alt="M??? duy???t" title="M??? duy???t" width="20" height="20" border=0 onclick="if(!confirm('B???n c?? mu???n m??? duy???t b??n ????ng gi?? n??y?')) return false;"></A>&nbsp;
							                    	<%} %>
							                    	
							                    	<%if(quyen[3]!=0){ %>
							                    	<%if(quyen[Utility.XEM]!=0){ %>
							                    	
							                    	<A href = "../../DuyetbandunggiaNppUpdateSvl?userId=<%=userId%>&display=<%= tieuchiSKU.getString("pk_seq") %>&view=<%=obj.getView()%>"><IMG src="../images/Display20.png" alt="Hien thi" title="Hi???n th???" border=0></A>&nbsp;
							                    	<%} %>
							                    	<%} %>
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
		if(tieuchiSKU != null)
			tieuchiSKU.close();
	}
	catch (SQLException e) {} %>
<%}%>