<%@page import="java.sql.ResultSet"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page  import = "geso.dms.center.beans.nhomctkhuyenmai.INhomctkhuyenmai" %>
<%@ page  import = "geso.dms.center.beans.nhomctkhuyenmai.INhomctkhuyenmaiList" %>
<%@ page  import = "java.util.List" %>
<%@ page  import = "java.util.Hashtable" %>
<%@ page  import = "geso.dms.center.util.*" %>
<%	String userId = (String) session.getAttribute("userId");  
	String userTen = (String) session.getAttribute("userTen");  	
	String sum = (String) session.getAttribute("sum");
	Utility util = (Utility) session.getAttribute("util");
	if(!util.check(userId, userTen, sum)){
		response.sendRedirect(request.getContextPath() + "/redirect.jsp");
	}else{ %>

<% INhomctkhuyenmaiList obj = (INhomctkhuyenmaiList)session.getAttribute("obj"); %>
<% //List<INhomkhuyenmai> nkmlist = (List<INhomkhuyenmai>)obj.getNkmList(); %>
<% ResultSet nctkm = (ResultSet)obj.getNctkmRs(); %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"> <% Utility Utilback = new Utility(); %>
<HTML>
<HEAD>
<TITLE><%= getServletContext().getInitParameter("TITLENAME") %></TITLE>
<META http-equiv="Content-Type" content="text/html; charset=utf-8">

<META http-equiv="Content-Style-Type" content="text/css">

<LINK rel="stylesheet" href="../css/main.css" type="text/css">
<link type="text/css" rel="stylesheet" href="../css/mybutton.css">
<LINK rel="stylesheet" href="../css/calendar.css" type="text/css">

<script type="text/javascript" src="../scripts/jquery.min.1.7.js"></script>
<link href="../css/jquery-ui.css" rel="stylesheet" type="text/css" />
<script src="../scripts/ui/jquery.ui.core.js" type="text/javascript"></script>
<script src="../scripts/ui/jquery.ui.widget.js" type="text/javascript"></script>
<script src="../scripts/ui/jquery.ui.datepicker.js"
	type="text/javascript"></script>
<script type="text/javascript">
	$(document).ready(function() {		
			$( ".days" ).datepicker({			    
					changeMonth: true,
					changeYear: true				
			});            
        }); 		
		
</script>

<SCRIPT language="JavaScript" type="text/javascript">
function submitform()
{
	 document.nkmForm.action.value = "new";
   	 document.forms["nkmForm"].submit();
}

function searchform()
{
	 document.nkmForm.action.value = "search";
   	 document.forms["nkmForm"].submit();
}

function clearform()
{
	 	document.forms["nkmForm"].submit();
}

</SCRIPT>
</HEAD>
<BODY leftmargin="0" bottommargin="0" topmargin="0" rightmargin="0">
<form name="nkmForm" method="post" action="../../NhomctkhuyenmaiSvl">
<input type="hidden" name="userId" value='<%=userId%>'>
<input type="hidden" name="action" value="1">

<TABLE width="100%" border="0" cellspacing="0" cellpadding="0"
	height="100%">
	<TR>
		<TD colspan="4" align='left' valign='top' bgcolor="#FFFFFF">
			<TABLE width="100%" border="0" cellpadding="0" cellspacing="1">
				<TR>
					<TD align="left" class="tbnavigation">
				   		<table width="100%" border="0" cellpadding="0" cellspacing="0">
					  		<tr height="22">
						  		<TD align="left" colspan="2" class="tbnavigation">&nbsp;Qu???n l?? khuy???n m???i &gt; Nh??m ch????ng tr??nh khuy???n m???i </TD> 
						  		<TD colspan="2" align="right" class="tbnavigation"><%=Utility.GLanguage("Ch??o m???ng b???n",session,jedis) %>  <%= userTen %>&nbsp;  </TD>
						  	</tr>
						</table></TD>
			  	</TR>
			</TABLE>
			
			<TABLE width="100%" border="0" cellpadding="0">
				<TR>
					<TD >
						<FIELDSET><LEGEND class="legendtitle">&nbsp;<%=Utility.GLanguage("Ti??u ch?? t??m ki???m",session,jedis) %>&nbsp;</LEGEND>
							<TABLE  width="100%" cellspacing="0" cellpadding="6">
								
								<TR>
									<TD width="19%" class="plainlabel">T??n / Di???n gi???i </TD>
								    <TD width="81%" class="plainlabel">
										<TABLE cellpadding="0" cellspacing="0">
											<TR><TD>
												<INPUT name="diengiai" type="text" size="40" value='<%= obj.getDiengiai()%>' onChange ="searchform();">
											</TD></TR>
										</TABLE>								
								</TR>
								<TR>
									<TD class="plainlabel"><%=Utility.GLanguage("Tr???ng th??i",session,jedis) %> </TD>
								  	<TD  class="plainlabel"><SELECT name = "trangthai"  onChange = "searchform();">
								  	
								  	<% if (obj.getTrangthai().equals("0")){ %>
								      	<option value=""> </option>
								    	<option value="1"><%=Utility.GLanguage("Ho???t ?????ng",session,jedis) %></option>
								    	<option value="0" selected><%=Utility.GLanguage("Ng??ng ho???t ?????ng",session,jedis) %></option>
									<%}else if (obj.getTrangthai().equals("1")){%>		
									  				
								  		<option value="1" selected><%=Utility.GLanguage("Ho???t ?????ng",session,jedis) %></option>
								    	<option value="0" ><%=Utility.GLanguage("Ng??ng ho???t ?????ng",session,jedis) %></option>
									<%} else {%>
									   <option value="" selected> </option>
								    	<option value="1"><%=Utility.GLanguage("Ho???t ?????ng",session,jedis) %></option>
								    	<option value="0" ><%=Utility.GLanguage("Ng??ng ho???t ?????ng",session,jedis) %></option>
                                        <%} %>
										</SELECT></TD>
									
								</TR>
								
										<TR>
											<TD class="plainlabel" ><%=Utility.GLanguage("T??? ng??y",session,jedis) %> </TD>
											<TD class="plainlabel" colspan="3">
												<TABLE cellpadding="0" cellspacing="0">
													<TR><TD>
														<input  class="days" type="text" name="tungay" value='<%=obj.getTungay() %>'  size="20" onchange = "searchform();">
													</TD>
													
													</TR>
												</TABLE>																					
		  									</TD>
										</TR>
										<TR>
                                          <TD class="plainlabel" ><%=Utility.GLanguage("?????n ng??y",session,jedis) %> </TD>
										  <TD class="plainlabel" colspan="3">
										  		<TABLE cellpadding="0" cellspacing="0"><TR><TD>
										 <input  class="days" type="text" name="denngay" value='<%=obj.getDenngay() %>' size="20" onchange = "searchform();">
										  		</TD>
										

											  </TR>
											 </TABLE>
									  </TR>
								<TR>
									<TD class="plainlabel">
									
                                 <a class="button2" href="javascript:clearform()">
									<img style="top: -4px;" src="../images/button.png" alt=""><%=Utility.GLanguage("Nh???p l???i",session,jedis) %></a>&nbsp;&nbsp;&nbsp;&nbsp;	

                                    </TD>								
									<TD class="plainlabel">&nbsp;</TD>										
								</TR>
								
							</TABLE>
					  </FIELDSET>
					</TD>	
				</TR>
			</TABLE>
			
			<TABLE width="100%" border = "0" cellpading = "0" cellspacing = "0">
				<TR>
					<TD width="100%">
					<FIELDSET>
					<LEGEND class="legendtitle">&nbsp;Nh??m ch????ng tr??nh khuy???n m???i &nbsp;&nbsp;&nbsp;
					<a class="button3" href="javascript:submitform()">
    						<img style="top: -4px;" src="../images/New.png" ><%=Utility.GLanguage("T???o m???i",session,jedis) %> </a>                            

					</LEGEND>
					<TABLE class="" width="100%">
						<TR>
							<TD width="98%">
							<TABLE width="100%" border="0" cellspacing="1" cellpadding="4">
								<TR class="tbheader">
									<TH width="9%">T??n nh??m</TH>
									<TH width="19%"><%=Utility.GLanguage("Di???n gi???i",session,jedis) %> </TH>
									<TH width="9%"><%=Utility.GLanguage("Tr???ng th??i",session,jedis) %> </TH>
									<TH width="8%"><%=Utility.GLanguage("Ng??y t???o",session,jedis) %> </TH>
									<TH width="13%"><%=Utility.GLanguage("Ng?????i t???o",session,jedis) %> </TH>
									<TH width="9%"><%=Utility.GLanguage("Ng??y s???a",session,jedis) %></TH>
									<TH width="12%"><%=Utility.GLanguage("Ng?????i s???a",session,jedis) %></TH>
									<TH width="9%"><%=Utility.GLanguage("T??c v???",session,jedis) %></TH>
								</TR>
						<% 
							
							int m = 0;
							String lightrow = "tblightrow";
							String darkrow = "tbdarkrow";
							while (nctkm.next()){
								//nkm = nkmlist.get(m);
								if (m % 2 != 0) {%>						
									<TR class= <%=lightrow%> >
								<%} else {%>
									<TR class= <%= darkrow%> >
								<%} %>										
															
							
									<TD align="left"><%=nctkm.getString("tennhom") %></TD>
							
									<TD><%=nctkm.getString("diengiai") %></TD>

									<% if(nctkm.getString("trangthai").equals("1")) {%>
										<TD align="center"><%=Utility.GLanguage("Ho???t ?????ng",session,jedis) %></TD>
									<%}else {%>
										<TD align="center"><%=Utility.GLanguage("Ng??ng ho???t ?????ng",session,jedis) %></TD>
									<%} %>
									<TD align="center"><%=nctkm.getString("ngaytao") %></TD>
									<TD align="center"><%=nctkm.getString("nguoitao") %></TD>
									<TD align="center"><%=nctkm.getString("ngaysua") %></TD>
									<TD align="center"><%=nctkm.getString("nguoisua") %> </TD>
									<TD align="center">
										<A href = "../../NhomctkhuyenmaiUpdateSvl?userId=<%=userId%>&update=<%= nctkm.getString("nkmId") %>"><img src="../images/Edit20.png" alt="Cap nhat" width="20" height="20" longdesc="Cap nhat" border = 0></A>
										<A href = "../../NhomctkhuyenmaiSvl?userId=<%=userId%>&delete=<%= nctkm.getString("nkmId") %>"><img src="../images/Delete20.png" alt="Xoa" width="20" height="20" longdesc="Xoa" border=0 onclick="if(!confirm('B???n c?? mu???n x??a nhom khuy???n m???i n??y?')) return false;"></A>															
									</TD>
								</TR>
							<% m++; }%>
							<TR class="tbfooter">
								<TD align="center" colspan="10"> |< < 1 to 20 of 100 > >|</TD>
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
<%}%>