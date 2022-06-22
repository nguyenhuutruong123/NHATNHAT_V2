<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.Calendar"%>
<%@ page import="java.util.Date"%>
<%@ page import="java.sql.SQLException"%>
<%@ page import = "java.util.Iterator" %>
<%@ page import = "java.util.List" %>
<%@ page import = "java.util.ArrayList" %>
<%@ page import = "java.sql.ResultSet" %>
<%@ page import = "geso.dms.center.beans.khaosat.*" %>
<%@ page import = "geso.dms.center.util.*" %>

<%	String userId = (String) session.getAttribute("userId");  
	String userTen = (String) session.getAttribute("userTen");  	
	String sum = (String) session.getAttribute("sum");
	Utility util = (Utility) session.getAttribute("util");
	if(!util.check(userId, userTen, sum)){
		response.sendRedirect("/SalesUpERP/index.jsp");
	}else{ %>
<%	
	IKhaosatList obj =(IKhaosatList)session.getAttribute("obj");
	ResultSet csxRs = obj.getKhaosatRs();
	String url = util.getChuyenNguUrl("KhaosatSvl", "",session);
%>
<% Utility Util = new Utility(); %>
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
	    //document.forms['khtt'].manguongoc.value= "";
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
<form name="khtt" method="post" action="../../KhaosatSvl">
<% redis.clients.jedis.Jedis jedis =  geso.dms.center.util.Utility.getJedis(); %><% geso.dms.center.util.Csrf csdr=new geso.dms.center.util.Csrf(request,response,true,false,true);%>
 <input type="hidden" name="<%=csdr.get_tokenName() %>" value='<%=csdr.get_tokenValue() %>'>

<input type="hidden" name="userId" value="<%= userId %>" >
<input type="hidden" name="action" value="1" >
<TABLE width="100%" border="0" cellspacing="0" cellpadding="0" height="100%"> 
    <TR>
        <TD colspan="4" align='left' valign='top' bgcolor="#FFFFFF">
        <TABLE width="100%" cellpadding="0" cellspacing="2">
            <TR>
                <TD align="left" class="tbnavigation">
                    <TABLE width="100%" border="0" cellpadding="0" cellspacing="0">
                        <TR height="22">
                            <TD align="left" colspan="2" class="tbnavigation"><%=url %></TD>  
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
                             	<TD width="20%" class="plainlabel" ><%=Utility.GLanguage("Diễn giải",session,jedis) %> </TD>
								<TD class="plainlabel">
									<input  type="text" name="diengiai" value="<%=obj.getDiengiai() %>" size="20" onchange=submitform();>
								</TD>
                             </TR >
                             <TR>
                             	<TD width="20%" class="plainlabel" ><%=Utility.GLanguage("Trạng thái",session,jedis) %> </TD>
								<TD class="plainlabel">
									<select name="trangthai" onChange="submitform();">
										<% if(obj.getTrangthai().equals("0")){ %>
											<option value="0" selected><%=Utility.GLanguage("Chưa chốt",session,jedis) %></option>
											<option value="1"><%=Utility.GLanguage("Đã chốt",session,jedis) %></option>
											<option value="2"><%=Utility.GLanguage("Đã hủy",session,jedis) %></option>
											<option value=""> </option>
										<%} else { if( obj.getTrangthai().equals("1") ) { %>										
											<option value="0" ><%=Utility.GLanguage("Chưa chốt",session,jedis) %></option>
											<option value="1" selected><%=Utility.GLanguage("Đã chốt",session,jedis) %></option>	
											<option value="2"><%=Utility.GLanguage("Đã hủy",session,jedis) %></option>									
											<option value=""> </option>
										<% } else { if( obj.getTrangthai().equals("2") ) { %>
											<option value="2" selected="selected" ><%=Utility.GLanguage("Đã hủy",session,jedis) %></option>	
											<option value="0" ><%=Utility.GLanguage("Chưa chốt",session,jedis) %></option>
											<option value="1"><%=Utility.GLanguage("Đã chốt",session,jedis) %></option>										
											<option value=""> </option>
										 <% } else { %> 
										 	<option value="" ></option>	
											<option value="0" ><%=Utility.GLanguage("Chưa chốt",session,jedis) %></option>
											<option value="1"><%=Utility.GLanguage("Đã chốt",session,jedis) %></option>										
											<option value="2"><%=Utility.GLanguage("Đã hủy",session,jedis) %></option>
										 <% } }  }  %>
										
									 </select>
								</TD>
                             </TR >
                              
                             <tr class="plainlabel"> 
                             	<td colspan="2" > 
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
                            <LEGEND class="legendtitle">&nbsp;<%=Utility.GLanguage("Khảo sát",session,jedis) %> &nbsp;&nbsp;	
                            	
                            	<a class="button3" href="javascript:newform()">
                           		<img style="top: -4px;" src="../images/New.png" alt=""><%=Utility.GLanguage("Tạo mới",session,jedis) %> </a>      
                            	      
                            </LEGEND>
    
                            <TABLE class="" width="100%">
                        <TR>
                            <TD width="98%">
                            <TABLE width="100%" border="0" cellspacing="1" cellpadding="4">
                                <TR class="tbheader">
                                    <TH width="10%" align="center"><%=Utility.GLanguage("Mã",session,jedis) %></TH>
                                    <TH width="20%" align="center"><%=Utility.GLanguage("Diễn giải",session,jedis) %></TH>
                                    <TH width="10%" align="center"><%=Utility.GLanguage("Bộ phận",session,jedis) %></TH>
                                    <TH width="10%" align="center"><%=Utility.GLanguage("Trạng thái",session,jedis) %></TH>
                                    <TH width="10%" align="center"><%=Utility.GLanguage("Ngày tạo",session,jedis) %></TH>
                                    <TH width="10%" align="center"><%=Utility.GLanguage("Người tạo",session,jedis) %></TH>
                                    <TH width="10%" align="center"><%=Utility.GLanguage("Ngày sửa",session,jedis) %></TH>
                                    <TH width="10%" align="center"><%=Utility.GLanguage("Người sửa",session,jedis) %></TH>
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
                                                <TD align="center"><%= csxRs.getString("pk_seq") %></TD>  
                                                <TD align="left"><%= csxRs.getString("diengiai")%></TD>                                 
                                                <TD align="left"><%= csxRs.getString("bophan")%></TD>
                                                <% if( tt.equals("0") ) { %>
                                                	<TD align="center"><%=Utility.GLanguage("Chưa chốt",session,jedis) %></TD>
                                                <% } else { if(tt.equals("1")) { %>       
                                                	<TD align="center"><%=Utility.GLanguage("Đã chốt",session,jedis) %></TD>
                                                <%} else { %>
                                                	<TD align="center"><%=Utility.GLanguage("Đã hủy",session,jedis) %></TD>
                                                <% } }  %> 
                                                	
                                                <TD align="center"><%= csxRs.getString("NGAYTAO")%> </TD>
                                                <TD align="left"><%= csxRs.getString("NGUOITAO")%></TD>
                                                <TD align="center"><%= csxRs.getString("NGAYSUA")%> </TD>
                                                <TD align="left"><%= csxRs.getString("NGUOISUA")%> </TD>
                                                <TD align="center"> 
                                                <% if( tt.equals("0") ) { %>
							                   		<A href = "../../RouterSvl?task=<%= Util.dongMa(getServletContext().getInitParameter("RedirectNoScript") +"KhaosatUpdateSvl?userId="+userId+"&update="+ csxRs.getString("pk_seq")+"") %>"><img src="../images/Edit20.png" alt="Cap nhat" title="Cập nhật" width="20" height="20" longdesc="Cap nhat" border = 0></A> &nbsp;
													<A href = "../../RouterSvl?task=<%= Util.dongMa(getServletContext().getInitParameter("RedirectNoScript") +"KhaosatSvl?userId="+userId+"&delete="+ csxRs.getString("pk_seq")+"") %>"><img src="../images/Delete20.png" alt="Xoa" title="Xóa" width="20" height="20" longdesc="Xoa" border=0 onclick="if(!confirm('Ban Muon Xoa Khao Sat Nay?')) return false;"></A>
													<A href = "../../RouterSvl?task=<%= Util.dongMa(getServletContext().getInitParameter("RedirectNoScript") +"KhaosatSvl?userId="+userId+"&chot="+ csxRs.getString("pk_seq")+"") %>"><img src="../images/Chot.png" alt="Chot" title="Chốt" width="20" height="20" longdesc="Chot" border=0 onclick="if(!confirm('Ban Muon Chot Khao Sat Nay?')) return false;"></A>
												<% } else { %>
													<A href = "../../RouterSvl?task=<%= Util.dongMa(getServletContext().getInitParameter("RedirectNoScript") +"KhaosatUpdateSvl?userId="+userId+"&display="+ csxRs.getString("pk_seq")+"") %>"><IMG src="../images/Display20.png" alt="Hien thi" title="Hien thi" border=0></A>&nbsp;
												<% }  %>
												
												<% if( tt.equals("1") ) { %>
												<A href = "../../RouterSvl?task=<%= Util.dongMa(getServletContext().getInitParameter("RedirectNoScript") +"KhaosatSvl?userId="+userId+"&unchot="+ csxRs.getString("pk_seq")+"") %>"><img src="../images/unChot.png" alt="Chot" title="Mo Chốt" width="20" height="20" longdesc="Chot" border=0 onclick="if(!confirm('Ban Muon mo Chot Khao Sat Nay?')) return false;"></A>
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
</TABLE><%geso.dms.center.util.Utility.JedisClose(jedis); %>
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