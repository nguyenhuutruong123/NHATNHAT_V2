<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page  import = "geso.dms.center.beans.nhanhang.INhanhangList" %>
<%@ page  import = "java.util.Iterator" %>
<%@ page  import = "java.util.List" %>
<%@ page  import = "java.sql.ResultSet" %>
<%@ page  import = "java.util.ArrayList" %>
<%@ page  import = "geso.dms.center.util.*" %>
<%	String userId = (String) session.getAttribute("userId");  
	String userTen = (String) session.getAttribute("userTen");  	
	String sum = (String) session.getAttribute("sum");
	Utility util = (Utility) session.getAttribute("util");
	if(!util.check(userId, userTen, sum)){
		response.sendRedirect(request.getContextPath() + "/redirect.jsp");
	}else{ %>

<% INhanhangList obj = (INhanhangList)session.getAttribute("obj"); %>
<% ResultSet nhlist = (ResultSet)obj.getNhlist() ; %>
<% ResultSet dvkdlist = (ResultSet)obj.getDvkdList(); %>
<%int[] quyen = new  int[4];
	quyen = util.Getquyen("NhanhangSvl","",userId);
	
	String url = util.getChuyenNguUrl("NhanhangSvl", "",session);
%>
<% Utility Util = new Utility(); %>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"> <% Utility Utilback = new Utility(); %>
<HTML>
<HEAD>
<TITLE><%= getServletContext().getInitParameter("TITLENAME") %></TITLE>
<META http-equiv="Content-Type" content="text/html; charset=utf-8">

<META http-equiv="Content-Style-Type" content="text/css">
<LINK rel="stylesheet" href="../css/main.css" type="text/css">
<LINK rel="stylesheet" href="../css/calendar.css" type="text/css">
<link type="text/css" rel="stylesheet" href="../css/mybutton.css">

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

<SCRIPT language="javascript" type="text/javascript">


function clearform()
{
    document.nhanForm.nhanhang.value = "";
    document.nhanForm.dvkdId.selectedIndex = 0;
    document.nhanForm.tungay.value = "";
	document.nhanForm.denngay.value = "";       
    document.nhanForm.trangthai.selectedIndex = 0;
    submitform();
}

function submitform()
{
	document.forms['nhanForm'].action.value= 'search';
	document.forms['nhanForm'].submit();
}

function newform()
{
	document.forms['nhanForm'].action.value= 'new';
	document.forms['nhanForm'].submit();
}
function thongbao()
{var tt = document.forms['nhanForm'].msg.value;
	if(tt.length>0)
    alert(document.forms['nhanForm'].msg.value);
	}


function xuatExcel()
{
	document.forms['nhanForm'].action.value= 'excel';
	document.forms['nhanForm'].submit();
	document.forms['nhanForm'].action.value= '';
}
</script>


</HEAD>
<BODY leftmargin="0" bottommargin="0" topmargin="0" rightmargin="0">
<form name="nhanForm" method="post" action="../../NhanhangSvl" charset="utf-8">
<% redis.clients.jedis.Jedis jedis =  geso.dms.center.util.Utility.getJedis(); %><% geso.dms.center.util.Csrf csdr=new geso.dms.center.util.Csrf(request,response,true,false,true);%>
 <input type="hidden" name="<%=csdr.get_tokenName() %>" value='<%=csdr.get_tokenValue() %>'>

<INPUT name="userId" type="hidden" value='<%=userId %>' size="30">
<input type="hidden" name="action" value='1'>
<input type="hidden" name="msg" value='<%=Utility.GLanguage(obj.getMsg(),session,jedis) %>'>

<script language="javascript" type="text/javascript">
    thongbao();
</script> 

<TABLE width="100%" border="0" cellspacing="0" cellpadding="0">
	
	<TR>
		<TD colspan="4" align='left' valign='top' bgcolor="#FFFFFF"><!--begin body Dossier-->
		<!--begin common dossier info---> <!--End common dossier info--->
		<TABLE width="100%" cellpadding="0" cellspacing="1">
			
				<TR>
					<TD align="left">
					  <table width="100%" border="0" cellpadding="0" cellspacing="0">
						  <tr height="22">
						   <TD align="left" colspan="2" class="tbnavigation">&nbsp; <%= url %> </TD>
   
						   <TD colspan="2" align="right" class="tbnavigation"><%=Utility.GLanguage("Ch??o m???ng b???n",session,jedis) %>  	<%=userTen %>&nbsp;  </TD>
						  </tr>
 					  </table>
					</TD>
				</TR>
				<TR>
					<TD>
					<TABLE border="0" width="100%" cellpadding="0" cellspacing="0">
						<TR>
							<TD width="100%" align="center" >
							<FIELDSET>
							<LEGEND class="legendtitle">&nbsp;<%=Utility.GLanguage("Ti??u ch?? t??m ki???m",session,jedis) %> &nbsp;</LEGEND>

							<TABLE  width="100%" cellpadding="4" cellspacing="0">
								<TR>
									<TD width="19%" class="plainlabel" ><%=Utility.GLanguage("Nh??n h??ng",session,jedis) %></TD>
								    <TD width="81%" class="plainlabel" ><INPUT name="nhanhang" size="40" type="text" value='<%= obj.getNhanhang() %>' onChange="submitform();"></TD>
								</TR>
						      	<TR>
									<TD width="19%" class="plainlabel"><%=Utility.GLanguage("????n v??? kinh doanh",session,jedis) %> </TD>
  								  	<TD class="plainlabel" >
										<TABLE cellpadding="0" cellspacing="0" border="0">
											<TR>
										  		<TD><SELECT  name="dvkdId" onChange="submitform();">												
													<option value=""  ></option>
												
												<% 	try{
															while (dvkdlist.next()){%>
																
																<%	if(dvkdlist.getString("pk_seq").equals(obj.getDvkdId())){ %>											
																		<option value= <%=dvkdlist.getString("pk_seq")%> selected><%= dvkdlist.getString("donvikinhdoanh") %></option>															
																<%}else{%>
																		<option value= <%=dvkdlist.getString("pk_seq")%> ><%= dvkdlist.getString("donvikinhdoanh") %></option>																																		
																<%	}
															}
															
														}catch(java.sql.SQLException e){
														
														}														
														%>														                                           
                                              			</SELECT>
                                         			</TD>
											</TR>
										</TABLE>									
									</TD>

								</TR>
								
								<TR>
											<TD class="plainlabel" ><%=Utility.GLanguage("T??? ng??y",session,jedis) %> </TD>
								  	<TD class="plainlabel" >
										<TABLE cellpadding="0" cellspacing="0">
										<TR><TD>
											<input class="days" type="text" name="tungay" value="<%=obj.getTungay() %>" size="20" onchange="submitform();">
										</TD>
												
                                    	</TR>
										</TABLE>
									</TD>
										</TR>
								<TR>
                                    <TD class="plainlabel" ><%=Utility.GLanguage("?????n ng??y",session,jedis) %> </TD>
								  	<TD class="plainlabel" >
							  			<TABLE cellpadding="0" cellspacing="0">
							  				<TR>
							  					<TD>
													<input class="days" type="text" name="denngay" value="<%=obj.getDenngay() %>" size="20" onchange="submitform();">
												</TD>
												
                	                     	</TR>
										</TABLE>
									</TD>

								</TR>
								
								<TR>
									<TD class="plainlabel"><%=Utility.GLanguage("Tr???ng th??i",session,jedis) %></TD>
									<TD class="plainlabel"><select name="trangthai" onChange="submitform();">
										  <option value="2"> </option>
										  <% if (obj.getTrangthai().equals("1")){ %>										  
										  		<option value="1" selected><%=Utility.GLanguage("Ho???t ?????ng",session,jedis) %></option>
										  		<option value="0"><%=Utility.GLanguage("Ng??ng ho???t ?????ng",session,jedis) %></option>
										  <%}else{
										  		if (obj.getTrangthai().equals("0")){ %>										  
										  			<option value="1" ><%=Utility.GLanguage("Ho???t ?????ng",session,jedis) %></option>
										  			<option value="0" selected><%=Utility.GLanguage("Ng??ng ho???t ?????ng",session,jedis) %></option>
										  		<%}else{%>	
										  			<option value="1"><%=Utility.GLanguage("Ho???t ?????ng",session,jedis) %></option>
										  			<option value="0"><%=Utility.GLanguage("Ng??ng ho???t ?????ng",session,jedis) %></option>
										  		<%}%>
										   <%}%>
										  </select>
									</TD>
                                                                                                                 
							  </TR>
								<TR>
									<TD class="plainlabel">
									<a class="button2" href="javascript:clearform()"> <img style="top: -4px;" src="../images/button.png" alt=""><%=Utility.GLanguage("Nh???p l???i",session,jedis) %></a>&nbsp;&nbsp;&nbsp;&nbsp;
									<a class="button2" href="javascript:xuatExcel()"> <img style="top: -4px;" src="../images/button.png" alt=""><%=Utility.GLanguage("Xu???t Excel",session,jedis) %> </a>&nbsp;&nbsp;&nbsp;&nbsp;	
									
                                  </TD>								
									<TD class="plainlabel">&nbsp;</TD>										
								</TR>
							</TABLE>

							</FIELDSET>
							</TD>
						</TR>
					</TABLE>
					</TD>
				</TR>

				<TR>
					<TD >
						<FIELDSET>
							<LEGEND class="legendtitle">&nbsp;<%=Utility.GLanguage("Nh??n h??ng",session,jedis) %> &nbsp; &nbsp; &nbsp;
							<%if(quyen[0]!=0) {%>
							<a class="button3" href="javascript:newform()"><img style="top: -4px;" src="../images/New.png" alt=""><%=Utility.GLanguage("T???o m???i",session,jedis) %> </a>
							<%} %>
						</LEGEND>
						<TABLE width="100%" cellpadding="0" cellspacing="0">
							<TR><TD>
								<TABLE width="100%" border="0" cellspacing="1" cellpadding="4">
								<TR class="tbheader">
									<TH width="4%"><%=Utility.GLanguage("STT",session,jedis) %></TH>
									<TH width="10%"><%=Utility.GLanguage("M??",session,jedis) %> </TH>
									<TH width="15%"><%=Utility.GLanguage("Nh??n h??ng",session,jedis) %> </TH>
									<TH width="10%"><%=Utility.GLanguage("????n v??? kinh doanh",session,jedis) %> </TH>
									<TH width="10%"><%=Utility.GLanguage("Tr???ng th??i",session,jedis) %> </TH>
									<TH width="12%"><%=Utility.GLanguage("Ng??y t???o",session,jedis) %></TH>
									<TH width="12%"><%=Utility.GLanguage("Ng?????i t???o",session,jedis) %> </TH>
									<TH width="12%"><%=Utility.GLanguage("Ng??y s???a",session,jedis) %></TH>
									<TH width="12%"><%=Utility.GLanguage("Ng?????i s???a",session,jedis) %> </TH>
									<TH width="10%" align="center">&nbsp;<%=Utility.GLanguage("T??c v???",session,jedis) %></TH>
								</TR>
								<%	 
									
									int m = 0;
									String lightrow = "tblightrow";
									String darkrow = "tbdarkrow";
									while (nhlist.next()){
										if (m % 2 != 0) {%>						
											<TR class= <%=lightrow%> >
										<%} else {%>
											<TR class= <%= darkrow%> >
										<%}%>
												<TD align="center"><%=m+1%></TD>
												<TD align="center"><div align="left"><%=nhlist.getString("ma")%></div></TD>
												<TD align="center"><div align="left"><%=nhlist.getString("ten")%></div></TD>
												<TD align="center"><div align="left"><%=nhlist.getString("donvikinhdoanh") %></div></TD>
									 <% if (nhlist.getString("trangthai").equals("1")){ %>
												<TD align="center"><%=Utility.GLanguage("Ho???t ?????ng",session,jedis) %> </TD>
									 <%}else{ %>
												<TD align="center"><%=Utility.GLanguage("Ng??ng ho???t ?????ng",session,jedis) %> </TD>
									 <%} %>
									
									<TD align="center"><%=nhlist.getString("ngaytao").toString() %></TD>
									<TD align="center"><%=nhlist.getString("nguoitao") %> </TD>
									<TD align="center"><%=nhlist.getString("ngaysua").toString() %></TD>
									<TD align="center"><%=nhlist.getString("nguoisua") %></TD>
									<TD align="center">
										<TABLE border = 0 cellpadding="0" cellspacing="0">
											<TR><TD>
											<%if(quyen[2]!=0) {%>
												<A href = "../../RouterSvl?task=<%= Util.dongMa(getServletContext().getInitParameter("RedirectNoScript") +"NhanhangUpdateSvl?userId="+userId+"&update=1&nhId="+nhlist.getString("pk_seq")+"&dvkdId="+nhlist.getString("dvkdId")+"") %>" ><img src="../images/Edit20.png" alt="Chinh sua" width="20" height="20" longdesc="Chinh sua" border = 0></A>
											<%} %>
											</TD>
											<TD>
												<%if(quyen[Utility.XEM]!=0) {%>
												<A href = "../../RouterSvl?task=<%= Util.dongMa(getServletContext().getInitParameter("RedirectNoScript") +"NhanhangUpdateSvl?userId="+userId+"&display=1&nhId="+nhlist.getString("pk_seq")+"&dvkdId="+nhlist.getString("dvkdId")+"") %>" ><img src="../images/Display20.png" alt="Chinh sua" width="20" height="20" longdesc="Chinh sua" border = 0></A>
											<%} %>
											</TD>
											<TD>
											<%if(quyen[1]!=0) {%>
												<A href = "../../RouterSvl?task=<%= Util.dongMa(getServletContext().getInitParameter("RedirectNoScript") +"NhanhangSvl?userId="+userId+"&delete="+nhlist.getString("pk_seq")+";"+nhlist.getString("dvkdId")+"")%>"><img src="../images/Delete20.png" alt="Xoa" width="20" height="20" longdesc="Xoa" border=0 onclick="if(!confirm('B???n c?? mu???n x??a nh??n nh??n h??ng n??y?')) return false;"></A>
											<%} %>
											</TD>
											</TR>
										</TABLE>												
									 </TD>
									</TR>
									<% 	m++; }%>
								
								<TR>
									<TD align="center" colspan="11" class="tbfooter">&nbsp;	</TD>
								</TR>
							</TABLE>
							</TD>
						</TR>
					</TABLE>

					</FIELDSET>
					</TD>
				</TR>

		</TABLE>

	</TR>
</TABLE>
<%geso.dms.center.util.Utility.JedisClose(jedis); %>
</form>
</BODY>
</HTML>
<%

 if (nhlist != null) nhlist.close() ; 
 if (dvkdlist != null) dvkdlist.close(); 
 obj.DBClose();
	
	}%>