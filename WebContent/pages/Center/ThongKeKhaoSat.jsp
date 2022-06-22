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
	IThongkekhaosatList obj =(IThongkekhaosatList)session.getAttribute("obj");
	ResultSet khaosatRs = obj.getKhaosatRs();
	ResultSet ketquaKsRs = obj.getKetquaKsRs();
	String url = util.getChuyenNguUrl("ThongkekhaosatSvl", "",session);
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

	function xuatBC()
	{
		document.forms['khtt'].action.value= 'xuatBC';
		document.forms['khtt'].submit();
	}
	</SCRIPT>
</head>
<BODY leftmargin="0" bottommargin="0" topmargin="0" rightmargin="0">
<form name="khtt" method="post" action="../../ThongkekhaosatSvl">
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
                            <TD align="left" colspan="2" class="tbnavigation">&nbsp;<%=url %> </TD>  
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
                          		<TD class="plainlabel" valign="middle" ><%=Utility.GLanguage("Đối tượng khảo sát",session,jedis) %> </TD>   
		                        <TD class="plainlabel" valign="middle" >
		                            <select name="doituong" onChange="submitform();" >
		                            	
		                            	<% if(obj.getDoituong().equals("KH")){ %>
		                            		<option value="KH" selected="selected" ><%=Utility.GLanguage("Khách hàng",session,jedis) %></option>
		                            		<option value="NVBH"  ><%=Utility.GLanguage("NHÂN VIÊN BÁN HÀNG",session,jedis) %></option>
		                            	<% } else { %> 
		                            		<option value="KH" ><%=Utility.GLanguage("Khách hàng",session,jedis) %></option>
		                            		<option value="NVBH" selected="selected" ><%=Utility.GLanguage("NHÂN VIÊN BÁN HÀNG",session,jedis) %></option>
		                            	<%  } %>
		                            </select>
		                        </TD>                
		                  	</TR> 
		                  	
		                  	<TR>
									<TD class="plainlabel" ><%=Utility.GLanguage("Từ ngày",session,jedis) %></TD>
									<td class="plainlabel">
			                            <input type="text"  class="days" size="11"
			                                    id="tungay" name="tungay" value="<%= obj.getTungay() %>" maxlength="10" readonly />
			                    	</td>
			                  </TR>
			                  <TR>
			                    	  <TD class="plainlabel" ><%=Utility.GLanguage("Đến ngày",session,jedis) %></TD>
								      <td class="plainlabel">
				                            <input type="text"  class="days" size="11"
				                                    id="denngay" name="denngay" value="<%= obj.getDenngay() %>" maxlength="10" readonly />
				                    </td>
			                    	
								</TR>
                            
                             <TR>
                             	<TD width="120" class="plainlabel" > <%=Utility.GLanguage("Khảo sát",session,jedis) %></TD>
								<TD class="plainlabel">
									<select name="khaosatId" onchange="submitform();" >
										<option value="" ></option>
										<% if(khaosatRs != null)
										{
											while(khaosatRs.next())
											{
												if(obj.getKhaosatId().equals(khaosatRs.getString("pk_seq")))
												{
													%>
													<option value="<%= khaosatRs.getString("pk_seq") %>" selected="selected" ><%= khaosatRs.getString("tieude") %></option>
												<% } else { %>
													<option value="<%= khaosatRs.getString("pk_seq") %>" ><%= khaosatRs.getString("tieude") %></option>
												<% } } khaosatRs.close(); } %>
									</select>
								</TD>
                             </TR >
                             <%-- <TR>
                             	<TD class="plainlabel" >Tên người khảo sát </TD>
								<TD class="plainlabel">
									<input  type="text" name="tennguoiKS" value="<%=obj.getTennguoitraloi() %>" onchange="submitform();" >
								</TD>
                             </TR > --%>
                              
                             <tr class="plainlabel"> 
                             	<td colspan="2" > 
                           			
									<a class="button2" href="javascript:xuatBC()">
										<img style="top: -4px;" src="../images/button.png" alt=""><%=Utility.GLanguage("Xuất báo cáo",session,jedis) %></a>&nbsp;&nbsp;&nbsp;&nbsp;	
                             	</td> 
                             </tr>
                            </TABLE>

                            </FIELDSET>
                            </TD>

                        </TR>
                    </TABLE>
                    </TD>
                </TR>

              <%--   <TR>
                    <TD width="100%">
                        <FIELDSET>
                            <LEGEND class="legendtitle">&nbsp;Kết quả khảo sát &nbsp;&nbsp;	</LEGEND>
    
                            <TABLE class="" width="100%">
                        <TR>
                            <TD width="98%">
                            <TABLE width="100%" border="0" cellspacing="1" cellpadding="4">
                                <TR class="tbheader">
                                	<TH width="15%" align="center">Người thực hiện</TH>
                                    <TH width="15%" align="center">Người được khảo sát</TH>
                                    <TH width="8%" align="center">Giới tính</TH>
                                    <TH width="10%" align="center">Điện thoại</TH>
                                    <TH width="17%" align="center">Địa chỉ</TH>
                                    <TH width="5%" align="center">Tuổi</TH>
                                    <TH width="10%" align="center">Đối tượng</TH>
                                    <TH width="10%" align="center">Thu nhập</TH>
                                    <TH width="10%" align="center">Xem kết quả</TH>
                                </TR>
                                <% 
                                    int m = 0;
                                    String lightrow = "tblightrow";
                                    String darkrow = "tbdarkrow";
                                    if(ketquaKsRs != null)
                                    {
                                    	String nguoithuchien = "";
                                    	while ( ketquaKsRs.next()){
                                    	
                                        if (m % 2 != 0) { %>                     
                                            <TR class= <%=lightrow%> >
                                        <%} else {%>
                                            <TR class= <%= darkrow%> >
                                        <%}%>
                                                
                                                <% if( !nguoithuchien.equals(ketquaKsRs.getString("nguoithuchien")) ) { nguoithuchien = ketquaKsRs.getString("nguoithuchien");  %>
                                                	<TD align="left" style="color: red;" ><%= ketquaKsRs.getString("nguoithuchien") %></TD>  
                                                <% } else { %>
                                                	<TD align="left">&nbsp;</TD>  
                                                <% } %>
                                                
                                                <TD align="left"><%= ketquaKsRs.getString("ten")%></TD>                                 
                                                <TD align="left"><%= ketquaKsRs.getString("gioitinh")%></TD>
                                                <TD align="left"><%= ketquaKsRs.getString("sodienthoai")%> </TD>
                                                <TD align="left"><%= ketquaKsRs.getString("diachi")%></TD>
                                                <TD align="left"><%= ketquaKsRs.getString("tuoi")%> </TD>
                                                <TD align="left"><%= ketquaKsRs.getString("doituong")%> </TD>
                                                <TD align="left"><%= ketquaKsRs.getString("thunhap")%> </TD>
                                                <TD align="center"> 
                                                
													<A href = "../../ThongkekhaosatUpdateSvl?userId=<%=userId%>&khaosatId=<%= obj.getKhaosatId() %>&hoten=<%= ketquaKsRs.getString("ten") %>&dienthoai=<%= ketquaKsRs.getString("sodienthoai") %>" >
														<IMG src="../images/Display20.png" alt="Xem ket qua" title="Xem ket qua" border=0></A>&nbsp;
											
							                    </TD>
                                            </TR>
                                          <% m++; } } %>  
                                          <TR class="tbfooter" >
											<TD align="center" colspan="15"> &nbsp; </TD>
										</TR>                                                  
                            </TABLE>
                            </TD>
                        </TR>
                    </TABLE>
                    </FIELDSET>
                    </TD>
                </TR> --%>

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
		if(obj != null)
		{
			obj.DbClose();
			obj = null;
		}
		session.setAttribute("obj", null);
	}
	catch (Exception e) {} %>
<%}%>