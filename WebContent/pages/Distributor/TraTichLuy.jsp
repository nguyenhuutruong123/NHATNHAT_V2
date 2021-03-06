<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.Calendar"%>
<%@ page import="java.util.Date"%>
<%@ page import="java.sql.SQLException"%>
<%@ page import = "java.util.Iterator" %>
<%@ page import = "java.util.List" %>
<%@ page import = "java.util.ArrayList" %>
<%@ page import = "java.sql.ResultSet" %>
<%@ page import = "geso.dms.distributor.beans.tratichluy.*" %>
<%@ page import = "geso.dms.center.util.*" %>

<%	String userId = (String) session.getAttribute("userId");  
	String userTen = (String) session.getAttribute("userTen");  	
	String sum = (String) session.getAttribute("sum");
	Utility util = (Utility) session.getAttribute("util");
	if(!util.check(userId, userTen, sum)){
		response.sendRedirect("/SalesUpERP/index.jsp");
	}else{ 
		int[] quyen = new  int[6];
		quyen = util.Getquyen("TratichluySvl","",userId);
	%>
<%	
	ITratichluyList obj =(ITratichluyList)session.getAttribute("obj");
	ResultSet csxRs = obj.getTratichluyRs();
	ResultSet dvkdRs = obj.getDvkdRs();
	ResultSet kbhRs = obj.getKenhRs();
	
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
	<script type="text/javascript" language="JavaScript" src="../scripts/Numberformat.js"></script>
	<link type="text/css" rel="stylesheet" href="../css/mybutton.css">
	
	<script type="text/javascript" src="../scripts/jquery.min.1.7.js"></script>
	<link href="../css/jquery-ui.css" rel="stylesheet" type="text/css" />
	<script src="../scripts/ui/jquery.ui.core.js" type="text/javascript"></script>
	<script src="../scripts/ui/jquery.ui.widget.js" type="text/javascript"></script>
	<script src="../scripts/ui/jquery.ui.datepicker.js" type="text/javascript"></script>
	<script type="text/javascript">
		$(document).ready(function() {		
				$( ".days" ).datepicker({			    
						changeMonth: true,
						changeYear: true				
				});            
	        }); 		
			
	</script>
	
	
	<SCRIPT language="javascript" type="text/javascript">
	function clearform()
	{ 
	    document.forms['khtt'].manguongoc.value= "";
	    document.forms['khtt'].diengiai.value= "";
	    document.forms['khtt'].trangthai.value = "";
		document.forms['khtt'].submit();
	}

	function submitform()
	{
		document.forms['khtt'].action.value= 'search';
		document.forms['khtt'].submit();
	}

	function newform()
	{
		document.forms['khtt'].action.value= 'new';
		document.forms['khtt'].submit();
	}
	</SCRIPT>
</head>
<BODY leftmargin="0" bottommargin="0" topmargin="0" rightmargin="0">
<form name="khtt" method="post" action="../../TratichluySvl">
<input type="hidden" name="userId" value="<%= userId %>" >
<input type="hidden" name="action" value="1" >
<input type="hidden" name="nppId" value="<%= obj.getNppId() %>" >
<TABLE width="100%" border="0" cellspacing="0" cellpadding="0" height="100%"> 
    <TR>
        <TD colspan="4" align='left' valign='top' bgcolor="#FFFFFF">
        <TABLE width="100%" cellpadding="0" cellspacing="2">
            <TR>
                <TD align="left" class="tbnavigation">
                    <TABLE width="100%" border="0" cellpadding="0" cellspacing="0">
                        <TR height="22">
                            <TD align="left" colspan="2" class="tbnavigation">&nbsp;Qu???n l?? khuy???n m???i > Tr??? khuy???n m???i t??ch l??y </TD>  
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
                             	<TD width="120px" class="plainlabel" >Th??ng </TD>
								<TD class="plainlabel" width="230px" >
									<select name="thang" >
										<option value="" ></option>
										<% for(int i = 1; i <= 12; i++) { 
											if(obj.getThang().equals(Integer.toString(i))) 
											{
												%> 
												<option value="<%= i %>" selected="selected"  ><%= "Th??ng " + i %></option>
											<% } else { %>
												<option value="<%= i %>"  ><%= "Th??ng " + i %></option>
											<% } } %>
									</select>
								</TD>
                             
                             	<TD width="120px" class="plainlabel" >N??m </TD>
								<TD class="plainlabel">
									<select name="nam" >
										<option value="" ></option>
										<% for(int i = 2012; i <= 2020; i++) { 
											if(obj.getNam().equals(Integer.toString(i))) 
											{
												%> 
												<option value="<%= i %>" selected="selected"  ><%= i %></option>
											<% } else { %>
												<option value="<%= i %>"  ><%= i %></option>
											<% } } %>
									</select>
								</TD>
                             </TR >
                             <TR>
                             	<TD class="plainlabel" >M?? CTKM </TD>
								<TD class="plainlabel" colspan="3" >
									<select name="dvkdId" onchange="submitform();"  >
										<option value=""  ></option>
										<% if(dvkdRs != null) 
										   {
												while(dvkdRs.next())
												{
													if(obj.getDvkdId().equals(dvkdRs.getString("pk_seq"))) { %>
														<option value="<%= dvkdRs.getString("pk_seq") %>" selected="selected" ><%= dvkdRs.getString("scheme")  %></option>
													<% } else { %>
														<option value="<%= dvkdRs.getString("pk_seq") %>" ><%= dvkdRs.getString("scheme")  %></option>
													<% }
												}
												dvkdRs.close();
											} %>
									</select>
								</TD>
                             
                             </TR >
                             <TR>
                             	<TD class="plainlabel" ><%=Utility.GLanguage("Tr???ng th??i",session,jedis) %> </TD>
								<TD class="plainlabel" colspan="3" >
									<select name="trangthai" onChange="submitform();">
										<% if(obj.getTrangthai().equals("0")){ %>
											<option value="0" selected>Ch??a ho??n t???t</option>
											<option value="1">Ho??n t???t</option>
											<option value=""> </option>
										<%} else { if( obj.getTrangthai().equals("1") ) { %>										
											<option value="0" >Ch??a ho??n t???t</option>
											<option value="1" selected>Ho??n t???t</option>										
											<option value=""> </option>
										<% } else { %>
											<option value="0" >Ch??a ho??n t???t</option>
											<option value="1">Ho??n t???t</option>										
											<option value="" selected> </option>
										 <% } }  %>
										
									 </select>
								</TD>
                             </TR >
                              
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
                            <LEGEND class="legendtitle">&nbsp;Tr??? khuy???n m???i t??ch l??y &nbsp;&nbsp;	
	      
                            </LEGEND>
    
                            <TABLE class="" width="100%">
                        <TR>
                            <TD width="98%">
                            <TABLE width="100%" border="0" cellspacing="1" cellpadding="4">
                                <TR class="tbheader">
                                    <TH width="45%" align="center">M?? CTKM</TH>
                                    <TH width="10%" align="center">Ng??y duy???t</TH>
                                    <TH width="10%" align="center"><%=Utility.GLanguage("Tr???ng th??i",session,jedis) %></TH>
                                    <TH width="10%" align="center"><%=Utility.GLanguage("Ng??y s???a",session,jedis) %></TH>
                                    <TH width="15%" align="center"><%=Utility.GLanguage("Ng?????i s???a",session,jedis) %></TH>
                                    <TH width="10%" align="center"><%=Utility.GLanguage("T??c v???",session,jedis) %></TH>
                                </TR>
                                <% 
                                   
                                    int m = 0;
                                    String lightrow = "tblightrow";
                                    String darkrow = "tbdarkrow";
                                    if(csxRs != null)
                                    while ( csxRs.next()){
                                       
                                    	String tt = csxRs.getString("trangthai").trim();
                                    	
                                        if (m % 2 != 0) { %>                     
                                            <TR class= <%=lightrow%> >
                                        <%} else {%>
                                            <TR class= <%= darkrow%> >
                                        <%}%>
                                                <TD align="left"><%= csxRs.getString("scheme") %></TD>                                   
                                                <TD align="center"><%= csxRs.getString("ngayduyet")%></TD>
                                                
                                                <% if( tt.equals("Ch??a ho??n t???t") ) { %>
                                                	<TD align="center" style="color: red;" >Ch??a ho??n t???t</TD>
                                                <% } else { %>       
                                                	<TD align="center">Ho??n t???t</TD>
                                                <%}  %> 
                                               
                                                <TD align="center"><%= "" %> </TD>
                                                <TD align="left"><%= "" %> </TD>
                                                <TD align="center"> 
                                                	<%if(quyen[Utility.XEM]!=0){ %>
													<A href = "../../TratichluyUpdateSvl?userId=<%=userId%>&display=<%= csxRs.getString("duyetkm_fk") %>&nppId=<%= csxRs.getString("nppId") %>"><IMG src="../images/Display20.png" alt="Hien thi" title="Hien thi" border=0></A>&nbsp;
													<%} %>
							                    </TD>
                                            </TR>
                                          <% m++; } %>  
                                          <TR class="tbfooter" >
									<TD align="center" colspan="15"> &nbsp; </TD>
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
		if(csxRs != null)
			csxRs.close();
		
		if(obj != null)
		{
			obj.DbClose();
			obj = null;
		}
		session.setAttribute("obj", null);
	}
	catch (SQLException e) {} %>
<%}%>