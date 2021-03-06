<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.Calendar"%>
<%@ page import="java.util.Date"%>
<%@ page import="java.sql.SQLException"%>
<%@ page import = "java.util.Iterator" %>
<%@ page import = "java.util.List" %>
<%@ page import = "java.util.ArrayList" %>
<%@ page import = "java.sql.ResultSet" %>
<%@ page import="geso.dms.center.beans.banggia.IBangGiaList"%>
<%@ page import = "geso.dms.center.util.*" %>

<%	String userId = (String) session.getAttribute("userId");  
	String userTen = (String) session.getAttribute("userTen");  	
	String sum = (String) session.getAttribute("sum");
	Utility util = (Utility) session.getAttribute("util");
	if(!util.check(userId, userTen, sum)){
		response.sendRedirect(request.getContextPath() + "/redirect.jsp");
	}else{ %>
<%	
	IBangGiaList obj =(IBangGiaList)session.getAttribute("obj");
	ResultSet bglist = obj.getBanggiaRs();
	ResultSet dvkd = (ResultSet)obj.getDvkd(); 
	ResultSet vung = (ResultSet)obj.getVungRs();
	ResultSet diaban = (ResultSet)obj.getDiabanRs();
	ResultSet kenh = (ResultSet)obj.getKenh(); 

	int[] quyen = new  int[6];
	quyen = util.Getquyen("BangGiaSvl","",userId);
	
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
		var msg = "<%=obj.getMsg()%>".trim();
		$(document).ready(function() {		
			$( ".days" ).datepicker({			    
					changeMonth: true,
					changeYear: true				
			});
			
			if(msg.length > 0 && msg !== "null") {
				alert(msg);
			}
        });	
			
	</script>
	
	
	<SCRIPT language="javascript" type="text/javascript">
	function clearform()
	{ 
	    document.forms['khtt'].bgTen.value= "";
	    document.forms['khtt'].dvkdId.value= "";
	    document.forms['khtt'].kenhId.value = "";
	    document.forms['khtt'].trangthai.value = "";
	    document.forms['khtt'].diengiai.value = "";
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
<form name="khtt" method="post" action="../../BangGiaSvl">
<input type="hidden" name="userId" value="<%= userId %>" >
<input type="hidden" name="action" value="1" >
<input type="hidden" name="nppId" value="<%= obj.getNppId() %>" >
<input type="hidden" name="loai" value="<%= obj.getLoai()%>" >


<TABLE width="100%" border="0" cellspacing="0" cellpadding="0" height="100%"> 
    <TR>
        <TD colspan="4" align='left' valign='top' bgcolor="#FFFFFF">
        <TABLE width="100%" cellpadding="0" cellspacing="2">
            <TR>
                <TD align="left" class="tbnavigation">
                    <TABLE width="100%" border="0" cellpadding="0" cellspacing="0">
                        <TR height="22">
                            <TD align="left" colspan="2" class="tbnavigation">&nbsp;D??? li???u n???n > S???n ph???m > <%= obj.getLoai().equals("0")?"B???ng gi?? B2B":"B???ng gi?? b??n NPP" %>  </TD>  
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
									<TD class="plainlabel">T??n b???ng gi??</TD>
									<TD class="plainlabel"><INPUT name="bgTen" type="text" size="40" value='<%=obj.getMa() %>' onChange = "submitform();"/></TD>
									<TD class="plainlabel"><%=Utility.GLanguage("Nh?? ph??n ph???i",session,jedis) %> </TD>
								  	<TD class="plainlabel"><INPUT name="diengiai" type="text" size="40" value='<%=obj.getDiengiai() %>' onChange = "submitform();"/> </TD>
								</TR>
								<TR>
								    <TD class="plainlabel">????n v??? kinh doanh </TD>
								    <TD class="plainlabel"><SELECT name="dvkdId" onChange = "submitform();">								      
								  	 		<option value =""></option>
								  	 <% try{ while(dvkd.next()){ 
								    	if(dvkd.getString("dvkdId").equals(obj.getDvkdId())){ %>
								      		<option value='<%=dvkd.getString("dvkdId") %>' selected><%=dvkd.getString("dvkd") %></option>
								      	<%}else{ %>
								     		<option value='<%=dvkd.getString("dvkdId") %>'><%=dvkd.getString("dvkd") %></option>
								     	<%}}}catch(java.sql.SQLException e){} %>	
								     	
									</SELECT></TD>
									<TD class="plainlabel"><%=Utility.GLanguage("Tr???ng th??i",session,jedis) %></TD>
									<TD class="plainlabel"><select name="trangthai" onChange = "submitform();">
								  	<% if (obj.getTrangthai().equals("0")){ %>
								    	<option value=""> </option>
								    	<option value="1"><%=Utility.GLanguage("Ho???t ?????ng",session,jedis) %></option>
								    	<option value="0" selected><%=Utility.GLanguage("Ng??ng ho???t ?????ng",session,jedis) %></option>
									<%}else{ 							
								  		if (obj.getTrangthai().equals("1")){ %>
								    	<option value=""> </option>
								    	<option value="1" selected><%=Utility.GLanguage("Ho???t ?????ng",session,jedis) %></option>
								    	<option value="0" ><%=Utility.GLanguage("Ng??ng ho???t ?????ng",session,jedis) %></option>
									<%}else{ %>
								    	<option value="" selected> </option>
								    	<option value="1" ><%=Utility.GLanguage("Ho???t ?????ng",session,jedis) %></option>
								    	<option value="0" ><%=Utility.GLanguage("Ng??ng ho???t ?????ng",session,jedis) %></option>
									<%}} %>
								    	</select></TD>	
								</TR>
								<TR>
								    <TD class="plainlabel"><%=Utility.GLanguage("V??ng",session,jedis) %> </TD>
								    <TD class="plainlabel"><SELECT name="vungid" onChange = "submitform();">								      
								  	 		<option value =""></option>
								  	 <% try{
								  		 if(vung!=null)
								  		 while(vung.next()){ 
								    	if(vung.getString("pk_seq").equals(obj.getVungId())){ %>
								      		<option value='<%=vung.getString("pk_seq") %>' selected><%=vung.getString("ten") %></option>
								      	<%}else{ %>
								     		<option value='<%=vung.getString("pk_seq") %>'><%=vung.getString("ten") %></option>
								     	<%}}}catch(java.sql.SQLException e){} %>	
								     	
									</SELECT></TD>
									<TD class="plainlabel">?????a b??n </TD>
								    <TD class="plainlabel"><SELECT name="diabanid" onChange = "submitform();">								      
								  	 		<option value =""></option>
								  	 <% try{
								  		 if(diaban!=null)
								  		 while(diaban.next()){ 
								    	if(diaban.getString("pk_seq").equals(obj.getDiabanId())){ %>
								      		<option value='<%=diaban.getString("pk_seq") %>' selected><%=diaban.getString("ten") %></option>
								      	<%}else{ %>
								     		<option value='<%=diaban.getString("pk_seq") %>'><%=diaban.getString("ten") %></option>
								     	<%}}}catch(java.sql.SQLException e){} %>	
								     	
									</SELECT></TD>
								</TR>
								<TR>
								  <TD class="plainlabel">K??nh b??n h??ng </TD>
								  <TD class="plainlabel">
								      	<SELECT name="kenhId" onChange = "submitform();">								      
								  	 		<option value =""></option>
								  	 <% try{ while(kenh.next()){ 
								    	if(kenh.getString("kenhId").equals(obj.getKenhId())){ %>
								      		<option value='<%=kenh.getString("kenhId") %>' selected><%=kenh.getString("kenh") %></option>
								      	<%}else{ %>
								     		<option value='<%=kenh.getString("kenhId") %>'><%=kenh.getString("kenh") %></option>
								     	<%}}}catch(java.sql.SQLException e){} %>	
								     	
                                  </SELECT></TD>
                                  <td colspan="2" class="plainlabel" > 
                           			<a class="button2" href="javascript:clearform()">
										<img style="top: -4px;" src="../images/button.png" alt=""><%=Utility.GLanguage("Nh???p l???i",session,jedis) %></a>&nbsp;&nbsp;&nbsp;&nbsp;	
                             	</td> 
							  </TR>
							
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
                            <LEGEND class="legendtitle">&nbsp;B???ng gi??&nbsp;&nbsp;	
                            	<%if(quyen[Utility.THEM]!=0) {%>
                            	<a class="button3" href="javascript:newform()">
                           		<img style="top: -4px;" src="../images/New.png" alt=""><%=Utility.GLanguage("T???o m???i",session,jedis) %> </a>      
                            	<%} %>      
                            </LEGEND>
    
                            <TABLE class="" width="100%">
                        <TR>
                            <TD width="98%">
                            <TABLE width="100%" border="0" cellspacing="1" cellpadding="4">
                                <TR class="tbheader">
									<TH align="center" width="3%">STT</TH>
									<TH align="center" width="20%">T??n b???ng gi?? </TH>
									<TH align="center"  width="10%">??VKD </TH>
									<TH align="center" width="8%">K??nh </TH>
									<!-- <TH width="8%"><%=Utility.GLanguage("T??? ng??y",session,jedis) %></TH> -->
									<TH align="center" width="8%"><%=Utility.GLanguage("Tr???ng th??i",session,jedis) %>  </TH>
									<TH align="center" width="8%"><%=Utility.GLanguage("Ng??y t???o",session,jedis) %></TH>
									<TH align="center" width="12%"><%=Utility.GLanguage("Ng?????i t???o",session,jedis) %> </TH>
									<TH align="center" width="8%"><%=Utility.GLanguage("Ng??y s???a",session,jedis) %> </TH>
									<TH align="center" width="12%"><%=Utility.GLanguage("Ng?????i s???a",session,jedis) %> </TH>
									<TH width="10%" align="center">&nbsp;T??c v???</TH>
								</TR>
                                
                            <% 
							int m = 0;
							String lightrow = "tblightrow";
							String darkrow = "tbdarkrow";
							try{
							while(bglist.next()){
								if (m % 2 != 0) {%>						
									<TR class= <%=lightrow%> >
							  <%} else {%>
									<TR class= <%= darkrow%> >
							  <%}%>
							  			<TD align="center"><%=m+1%></TD>
										<TD align="left"><%= bglist.getString("ten") %></TD>
										<TD align="left"><%= bglist.getString("dvkd") %></TD>
										<TD align="left"><%= bglist.getString("kenh") %></TD>	
										<%-- <TD align="left"><%= bglist.getString("TuNgay") %></TD> --%>
											
									<% if (bglist.getString("trangthai").equals("1")){ %>
										<TD align="left"><%=Utility.GLanguage("Ho???t ?????ng",session,jedis) %></TD>							
									<%}else{ %>
										<TD align="left"><%=Utility.GLanguage("Ng??ng ho???t ?????ng",session,jedis) %></TD>
									<%} %>
									
										<TD align="center"><%= bglist.getDate("ngaytao").toString() %></TD>	
										<TD align="left"><%= bglist.getString("nguoitao") %></TD>
										<TD align="center"><%= bglist.getDate("ngaysua").toString() %></TD>
										<TD align="left"><%= bglist.getString("nguoisua") %></TD>
										<TD align="center">
											<TABLE border = 0 cellpadding="0" cellspacing="0">
																						
											<TR>											
											<TD>
											<% 	if(quyen[Utility.CHOT]!=0 &&  bglist.getString("trangthai").equals("0") ) {%>
													<%-- <A href = "../../BangGiaSvl?userId=<%=userId%>&delete=<%= bglist.getString("pk_seq") %>"><img src="../images/Delete20.png" alt="Xoa" title="X??a" width="20" height="20" longdesc="Xoa" border=0 onclick="if(!confirm('B???n c?? mu???n x??a b???ng gi?? n??y ?')) return false;"></A>   --%>
														<A href = "../../BangGiaSvl?userId=<%=userId%>&chot=<%= bglist.getString("pk_seq") %>&loai=<%=obj.getLoai()%> "><img src="../images/Chot.png" alt="Cap nhat" title="C???p nh???t" width="20" height="20" longdesc="Cap nhat" border = 0></A>
												<%} %>
												
												<%if(quyen[Utility.SUA]!=0 &&  bglist.getString("trangthai").equals("0") ) {%>
													<%-- <A href = "../../BangGiaSvl?userId=<%=userId%>&copy=<%= bglist.getString("pk_seq") %>"><img src="../images/copy20.png" alt="Cap nhat" title="Copy b???ng gi??" width="20" height="20" longdesc="Copy b???ng gi??" border = 0></A> --%>
													<A href = "../../BangGiaUpdateSvl?userId=<%=userId%>&edit=<%= bglist.getString("pk_seq") %>&loai=<%=obj.getLoai()%> "><img src="../images/Edit20.png" alt="Cap nhat" title="C???p nh???t" width="20" height="20" longdesc="Cap nhat" border = 0></A>
												<%}%>
												<%if(quyen[Utility.SUA]!=0 &&  bglist.getString("trangthai").equals("1") ) {%>
													<%-- <A href = "../../BangGiaSvl?userId=<%=userId%>&copy=<%= bglist.getString("pk_seq") %>"><img src="../images/copy20.png" alt="Cap nhat" title="Copy b???ng gi??" width="20" height="20" longdesc="Copy b???ng gi??" border = 0></A> --%>
													<A href = "../../BangGiaUpdateSvl?userId=<%=userId%>&edit=<%= bglist.getString("pk_seq") %>&loai=<%=obj.getLoai()%> "><img src="../images/Display.png" alt="Cap nhat" title="C???p nh???t" width="20" height="20" longdesc="Cap nhat" border = 0></A>
												<%}%>
												
												<% 	if(quyen[Utility.XOA]!=0 &&  bglist.getString("trangthai").equals("0") ) {%>
													<%-- <A href = "../../BangGiaSvl?userId=<%=userId%>&delete=<%= bglist.getString("pk_seq") %>"><img src="../images/Delete20.png" alt="Xoa" title="X??a" width="20" height="20" longdesc="Xoa" border=0 onclick="if(!confirm('B???n c?? mu???n x??a b???ng gi?? n??y ?')) return false;"></A>   --%>
														<A href = "../../BangGiaSvl?userId=<%=userId%>&delete=<%= bglist.getString("pk_seq") %>&loai=<%=obj.getLoai()%> "><img src="../images/Delete.png" alt="Cap nhat" title="C???p nh???t" width="20" height="20" longdesc="Cap nhat" border = 0></A>
												<%} %>
												
											</TD>
											</TR>
											</TABLE>
										</TD>
									</TR>
								<% m++; }
								
								} catch( Exception e){ System.out.println("Exception: " + e.getMessage()); }%>
                                
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
		if(bglist != null)
			bglist.close();
		
		if(obj != null)
		{
			obj.DbClose();
			obj = null;
		}
		session.setAttribute("obj", null);
	}
	catch (SQLException e) {e.printStackTrace();} %>
<%}%>