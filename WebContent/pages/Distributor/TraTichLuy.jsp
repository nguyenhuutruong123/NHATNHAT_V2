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
                            <TD align="left" colspan="2" class="tbnavigation">&nbsp;Quản lý khuyến mại > Trả khuyến mại tích lũy </TD>  
                            <TD colspan="2" align="right" class="tbnavigation"><%=Utility.GLanguage("Chào mừng bạn",session,jedis) %>  <%= userTen %></TD>
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
                              <LEGEND class="legendtitle">&nbsp;<%=Utility.GLanguage("Tiêu chí tìm kiếm",session,jedis) %> &nbsp;</LEGEND>

                            <TABLE  width="100%" cellpadding="6" cellspacing="0">
                             <TR>
                             	<TD width="120px" class="plainlabel" >Tháng </TD>
								<TD class="plainlabel" width="230px" >
									<select name="thang" >
										<option value="" ></option>
										<% for(int i = 1; i <= 12; i++) { 
											if(obj.getThang().equals(Integer.toString(i))) 
											{
												%> 
												<option value="<%= i %>" selected="selected"  ><%= "Tháng " + i %></option>
											<% } else { %>
												<option value="<%= i %>"  ><%= "Tháng " + i %></option>
											<% } } %>
									</select>
								</TD>
                             
                             	<TD width="120px" class="plainlabel" >Năm </TD>
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
                             	<TD class="plainlabel" >Mã CTKM </TD>
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
                             	<TD class="plainlabel" ><%=Utility.GLanguage("Trạng thái",session,jedis) %> </TD>
								<TD class="plainlabel" colspan="3" >
									<select name="trangthai" onChange="submitform();">
										<% if(obj.getTrangthai().equals("0")){ %>
											<option value="0" selected>Chưa hoàn tất</option>
											<option value="1">Hoàn tất</option>
											<option value=""> </option>
										<%} else { if( obj.getTrangthai().equals("1") ) { %>										
											<option value="0" >Chưa hoàn tất</option>
											<option value="1" selected>Hoàn tất</option>										
											<option value=""> </option>
										<% } else { %>
											<option value="0" >Chưa hoàn tất</option>
											<option value="1">Hoàn tất</option>										
											<option value="" selected> </option>
										 <% } }  %>
										
									 </select>
								</TD>
                             </TR >
                              
                             <tr class="plainlabel"> 
                             	<td colspan="4" > 
                           			<a class="button2" href="javascript:clearform()">
										<img style="top: -4px;" src="../images/button.png" alt=""><%=Utility.GLanguage("Nhập lại",session,jedis) %></a>&nbsp;&nbsp;&nbsp;&nbsp;	
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
                            <LEGEND class="legendtitle">&nbsp;Trả khuyến mại tích lũy &nbsp;&nbsp;	
	      
                            </LEGEND>
    
                            <TABLE class="" width="100%">
                        <TR>
                            <TD width="98%">
                            <TABLE width="100%" border="0" cellspacing="1" cellpadding="4">
                                <TR class="tbheader">
                                    <TH width="45%" align="center">Mã CTKM</TH>
                                    <TH width="10%" align="center">Ngày duyệt</TH>
                                    <TH width="10%" align="center"><%=Utility.GLanguage("Trạng thái",session,jedis) %></TH>
                                    <TH width="10%" align="center"><%=Utility.GLanguage("Ngày sửa",session,jedis) %></TH>
                                    <TH width="15%" align="center"><%=Utility.GLanguage("Người sửa",session,jedis) %></TH>
                                    <TH width="10%" align="center"><%=Utility.GLanguage("Tác vụ",session,jedis) %></TH>
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
                                                
                                                <% if( tt.equals("Chưa hoàn tất") ) { %>
                                                	<TD align="center" style="color: red;" >Chưa hoàn tất</TD>
                                                <% } else { %>       
                                                	<TD align="center">Hoàn tất</TD>
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