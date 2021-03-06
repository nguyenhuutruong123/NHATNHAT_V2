<%@page import="java.text.Format"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.Calendar"%>
<%@ page import="java.util.Date"%>
<%@ page import="java.sql.SQLException"%>
<%@ page import = "java.util.Iterator" %>
<%@ page import = "java.util.List" %>
<%@ page import = "java.util.ArrayList" %>
<%@ page import = "java.sql.ResultSet" %>
<%@ page  import = "java.text.DecimalFormat" %>
<%@ page  import = "java.text.NumberFormat" %>
<%@ page import="geso.dms.center.beans.hoadonphelieu.IErpHoadonphelieuList"%>
<%@ page import = "geso.dms.center.util.*" %>

<%	String userId = (String) session.getAttribute("userId");  
	String userTen = (String) session.getAttribute("userTen");  	
	String sum = (String) session.getAttribute("sum");
	Utility util = (Utility) session.getAttribute("util");
	if(!util.check(userId, userTen, sum)){
		response.sendRedirect("/SalesUpERP/index.jsp");
	}else{ %>
<%	
	IErpHoadonphelieuList obj =(IErpHoadonphelieuList)session.getAttribute("obj");
	ResultSet csxRs = obj.getGiamgiaRs();
	ResultSet khRs = obj.getKhRs();  
	NumberFormat formatter = new DecimalFormat("#,###,###"); 
	
	int[] quyen = new  int[6];
	quyen = util.Getquyen("HoadontaichinhSvl","",userId);
	
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
	<script type="text/javascript" language="JavaScript" src="../scripts/Numberformat.js"></script>
	<link type="text/css" rel="stylesheet" href="../css/mybutton.css">
	
   <script type="text/javascript" src="../scripts/dropdowncontent.js"></script>
   <script type="text/javascript" src="../scripts/ajax.js"></script>
   <script type="text/javascript" src="../scripts/jquery.autocomplete.js"></script> 
	
	<script type="text/javascript" src="../scripts/phanTrang.js"></script>
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
		document.forms['khtt'].mahoadon.value= "";
	    document.forms['khtt'].diengiai.value= "";
	    document.forms['khtt'].trangthai.value = "";
	    document.forms['khtt'].sohoadon.value = "";
	    document.forms['khtt'].khachhang.value = "";
	    document.forms['khtt'].tennguoitao.value ="";
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
<link href="../css/select2.css" rel="stylesheet" />
<script src="../scripts/select2.js"></script>
<script>
	$(document).ready(function()
	{
		$(".select2").select2();
	});
</script>

<script type="text/javascript" src="../scripts/dropdowncontent.js"></script>
</head>
<BODY leftmargin="0" bottommargin="0" topmargin="0" rightmargin="0">
<form name="khtt" method="post" action="../../ErpHoadonphelieuSvlTT">
<input type="hidden" name="userId" value="<%= userId %>" >
<input type="hidden" name="nppId" value="<%= obj.getNppId() %>" >
<input type="hidden" name="action" value="1" >
<input type="hidden" name="crrApprSplitting" value="<%= obj.getCrrApprSplitting() %>" >
<input type="hidden" name="nxtApprSplitting" value="<%= obj.getNxtApprSplitting() %>" >

<TABLE width="100%" border="0" cellspacing="0" cellpadding="0" height="100%"> 
    <TR>
        <TD colspan="4" align='left' valign='top' bgcolor="#FFFFFF">
        <TABLE width="100%" cellpadding="0" cellspacing="2">
            <TR>
                <TD align="left" class="tbnavigation">
                    <TABLE width="100%" border="0" cellpadding="0" cellspacing="0">
                        <TR height="22">
                            <TD align="left" colspan="2" class="tbnavigation"> Qu???n l?? b??n h??ng > B??n h??ng OTC > Xu???t H?? kh??c </TD>  
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
                             	<TD width="20%" class="plainlabel" >M?? h??a ????n </TD>
								<TD class="plainlabel">
									<input  type="text" name="mahoadon" value="<%=obj.getMa() %>" size="20" onchange=submitform();>
								</TD>
                             </TR >
							 <TR>
                             	<TD width="20%" class="plainlabel" >S??? h??a ????n </TD>
								<TD class="plainlabel">
									<input  type="text" name="sohoadon" value="<%=obj.getSohoadon() %>" size="20" onchange=submitform();>
								</TD>
                             </TR >
							  <TR>
                             	<TD width="20%" class="plainlabel" ><%=Utility.GLanguage("Ng?????i t???o",session,jedis) %> </TD>
								<TD class="plainlabel">
									<input  type="text" name="tennguoitao" value="<%=obj.getTennguoitao() %>" size="20" onchange=submitform();>
								</TD>
                             </TR >
							 
							 
                             <TR>
                             	<TD width="20%" class="plainlabel" ><%=Utility.GLanguage("Di???n gi???i",session,jedis) %> </TD>
								<TD class="plainlabel">
									<input  type="text" name="diengiai" value="<%=obj.getDiengiai() %>" size="20" onchange=submitform();>
								</TD>
                             </TR >
                              <TR>
                             	<TD width="20%" class="plainlabel" >M?? / t??n kh??ch h??ng </TD>
								<TD class="plainlabel">
									<select name = "khachhang" class="select2" style="width: 200px" onchange=submitform(); >
				                    		<option value=""> </option>
				                        	<%
				                        		if(khRs != null)
				                        		{
				                        			try
				                        			{
				                        			while(khRs.next())
				                        			{  
				                        			if( khRs.getString("pk_seq").equals(obj.getKhachhang())){ %>
				                        				<option value="<%= khRs.getString("pk_seq") %>" selected="selected" ><%= khRs.getString("ten") %></option>
				                        			<%}else { %>
				                        				<option value="<%= khRs.getString("pk_seq") %>" ><%= khRs.getString("ten") %></option>
				                        		 <% } } khRs.close();} catch(SQLException ex){}
				                        		}
				                        	%>
				                    	</select>
								</TD>
                             </TR >
                             <TR>
                             	<TD width="20%" class="plainlabel" ><%=Utility.GLanguage("Tr???ng th??i",session,jedis) %> </TD>
								<TD class="plainlabel">
									<select name="trangthai" onChange="submitform();">
										<% if(obj.getTrangthai().equals("0")){ %>
											<option value="0" selected>Ch??a ch???t</option>
											<option value="1">???? ch???t</option>
											<option value="2">???? h???y</option>
											<option value=""> </option>
										<%} else { if( obj.getTrangthai().equals("1") ) { %>										
											<option value="0" >Ch??a ch???t</option>
											<option value="1" selected>???? ch???t</option>	
											<option value="2">???? h???y</option>									
											<option value=""> </option>											
										<% } else { if ( obj.getTrangthai().equals("2") ){%>
											<option value="0" >Ch??a ch???t</option>
											<option value="1" >???? ch???t</option>	
											<option value="2" selected >???? h???y</option>									
											<option value=""> </option>	
											<%}else { %>
											<option value="0" >Ch??a ch???t</option>
											<option value="1">???? ch???t</option>		
											<option value="2">???? h???y</option>								
											<option value="" selected> </option>
										 <% }} }  %>
										
									 </select>
								</TD>
                             </TR >
                          
                              
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
                            <LEGEND class="legendtitle">&nbsp;H??a ????n kh??c &nbsp;&nbsp;	
                            	<% if(quyen[Utility.THEM]!=0 ) { %>
                            	<a class="button3" href="javascript:newform()">
                           		<img style="top: -4px;" src="../images/New.png" alt=""><%=Utility.GLanguage("T???o m???i",session,jedis) %> </a>      
                            	 <%} %>
                            </LEGEND>
    
                            <TABLE class="" width="100%">
                        <TR>
                            <TD width="98%">
                            <TABLE width="100%" border="0" cellspacing="1" cellpadding="4">
                                <TR class="tbheader">
                                    <TH width="6%" align="center">M?? h??a ????n</TH>
                                     <TH width="6%" align="center">S??? H??</TH>
                                     <TH width="7%" align="center">Ng??y H?? </TH>
                                    <TH width="17%" align="center">Kh??ch h??ng</TH>
                                     <TH width="8%" align="center">T???ng ti???n</TH>
                                    <TH width="7%" align="center"><%=Utility.GLanguage("Tr???ng th??i",session,jedis) %></TH>
                                    <TH width="6%" align="center"><%=Utility.GLanguage("Ng??y t???o",session,jedis) %></TH>
                                    <TH width="7%" align="center"><%=Utility.GLanguage("Ng?????i t???o",session,jedis) %></TH>
                                    <TH width="6%" align="center"><%=Utility.GLanguage("Ng??y s???a",session,jedis) %></TH>
                                    <TH width="7%" align="center"><%=Utility.GLanguage("Ng?????i s???a",session,jedis) %></TH>
                                    <TH width="10%" align="center"><%=Utility.GLanguage("T??c v???",session,jedis) %></TH>
                                </TR>
                                <% 
                                   
                                int count = 0;
                         		if(csxRs!= null)
                         		{ 
                         			while(csxRs.next())
                         			{
                         				String tt = csxRs.getString("trangthai");
                         				
                    					if((count % 2 ) == 0) {%>
        		                         	<TR class='tbdarkrow'>
        		                        <%}else{ %>
        		                          	<TR class='tblightrow'>
        		                        <%} %>
                                                <TD align="center"><%= csxRs.getString("pk_seq")%></TD> 
                                                 <TD align="center"><%= csxRs.getString("sohoadon")%></TD>
                                                 <TD align="center"><%= csxRs.getString("ngayhoadon") %></TD>                                   
                                                <TD align="left"><%= csxRs.getString("khTen")%></TD>
                                                <TD align="right"><%= formatter.format(Double.parseDouble(csxRs.getString("tongtien")) ) %></TD>
                                                <% if( tt.equals("0") ) { %>
                                                	<TD align="center">Ch??a ch???t</TD>
                                                <% } else { if(tt.equals("1")) { %> 
                                                	<TD align="center">???? ch???t</TD>
                                                <% } else {  %>       
                                                	<TD align="center">???? h???y</TD>
                                                <%} } %> 
                                                	
                                                <TD align="center"><%= csxRs.getString("ngaytao")%> </TD>
                                                <TD align="left"><%= csxRs.getString("nguoitao")%></TD>
                                                <TD align="center"><%=csxRs.getString("ngaysua") %> </TD>
                                                <TD align="left"><%= csxRs.getString("nguoisua")%> </TD>
                                                <TD align="center"> 
                                                <% if( tt.equals("0") ) { %>
                                                  <% if(quyen[2] == 1) { %>
							                   		<A href = "../../ErpHoadonphelieuUpdateSvl?userId=<%=userId%>&update=<%= csxRs.getString("pk_seq") %>"><img src="../images/Edit20.png" alt="C???p nh???t" width="20" height="20" longdesc="C???p nh???t" border = 0></A> &nbsp;
							                   		<%} %>
							                   		<% if(quyen[4] == 1) { %>
							                   		<A href = "../../ErpHoadonphelieuSvl?userId=<%=userId%>&chot=<%= csxRs.getString("pk_seq") %>"><img src="../images/Chot.png" alt="Duy???t" width="20" height="20" longdesc="Duy???t" border=0 onclick="if(!confirm('B???n Mu???n Duy???t H??a ????n N??y?')) return false;"></A>&nbsp;
							                   		<%} %>
							                   		<% if(quyen[1] == 1) { %>
													<A href = "../../ErpHoadonphelieuSvl?userId=<%=userId%>&delete=<%=csxRs.getString("pk_seq") %>"><img src="../images/Delete20.png" alt="X??a" width="20" height="20" longdesc="X??a" border=0 onclick="if(!confirm('B???n Mu???n X??a H??a ????n N??y?')) return false;"></A>
													<%} %>
													
												<% } else { %>
													<A href = "../../ErpHoadonphelieuUpdateSvl?userId=<%=userId%>&display=<%= csxRs.getString("pk_seq") %>"><IMG src="../images/Display20.png" alt="Hi???n th???" title="Hi???n th???" border=0></A>&nbsp;
												<% }  %>												
												
							                    </TD>
                                            </TR>
                                   <% count++;  
                         		    }} %>

								
								 <tr class="tbfooter">
														<TD align="center" valign="middle" colspan="13"
															class="tbfooter">
															<%if(obj.getNxtApprSplitting() >1) {%> <img alt="Trang Dau"
															src="../images/first.gif" style="cursor: pointer;"
															onclick="View('khtt', 1, 'view')"> &nbsp; <%}else {%>
															<img alt="Trang Dau" src="../images/first.gif">
															&nbsp; <%} %> <% if(obj.getNxtApprSplitting() > 1){ %> <img
															alt="Trang Truoc" src="../images/prev.gif"
															style="cursor: pointer;"
															onclick="View('khtt', eval(khtt.nxtApprSplitting.value) -1, 'view')">
															&nbsp; <%}else{ %> <img alt="Trang Truoc"
															src="../images/prev_d.gif"> &nbsp; <%} %> <%
													int[] listPage = obj.getNextSplittings();
													for(int i = 0; i < listPage.length; i++){
												%> <% 
												
												System.out.println("Current page:" + obj.getNxtApprSplitting());
												System.out.println("List page:" + listPage[i]);
												
												if(listPage[i] == obj.getNxtApprSplitting()){ %> <a
															style="color: white;"><%= listPage[i] %>/ <%=obj.getTheLastSplitting() %></a>
															<%}else{ %> <a
															href="javascript:View('khtt', <%= listPage[i] %>, 'view')"><%= listPage[i] %></a>
															<%} %> <input type="hidden" name="list"
															value="<%= listPage[i] %>" /> &nbsp; <%} %> <% if(obj.getNxtApprSplitting() < obj.getTheLastSplitting()){ %>
															&nbsp; <img alt="Trang Tiep" src="../images/next.gif"
															style="cursor: pointer;"
															onclick="View('khtt', eval(khtt.nxtApprSplitting.value) +1, 'view')">
															&nbsp; <%}else{ %> &nbsp; <img alt="Trang Tiep"
															src="../images/next_d.gif"> &nbsp; <%} %> <%if(obj.getNxtApprSplitting() == obj.getTheLastSplitting()) {%>
															<img alt="Trang Cuoi" src="../images/last.gif">
															&nbsp; <%}else{ %> <img alt="Trang Cuoi"
															src="../images/last.gif" style="cursor: pointer;"
															onclick="View('khtt', -1, 'view')"> &nbsp; <%} %>
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