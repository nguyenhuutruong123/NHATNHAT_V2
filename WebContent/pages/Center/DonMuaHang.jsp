<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page  import = "geso.dms.center.beans.donmuahang.IDonmuahangList" %>
<%@ page  import = "java.sql.ResultSet" %>
<%@ page  import = "java.text.DecimalFormat" %>
<%@ page  import = "java.text.NumberFormat" %>
<%@ page  import = "geso.dms.center.util.*" %>
<%	String userId = (String) session.getAttribute("userId");  
	String userTen = (String) session.getAttribute("userTen");  	
	System.out.println(userId);
	System.out.println(userTen);
	String sum = (String) session.getAttribute("sum");
	Utility util = (Utility) session.getAttribute("util");
	if(!util.check(userId, userTen, sum)){
		response.sendRedirect("/SalesUp/index.jsp");
	}else{ %>

<% IDonmuahangList obj = (IDonmuahangList)session.getAttribute("obj"); %>
<% ResultSet kvlist = (ResultSet)obj.getKvList(); %>
<% ResultSet dhlist = (ResultSet)obj.getDhList(); %>
<% obj.setNextSplittings(); 
int[] quyen = new  int[5];
quyen = util.Getquyen("36",userId);
ResultSet npp=(ResultSet)obj.getNppRs();
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"> <% Utility Utilback = new Utility(); %>
<HTML>
<HEAD>
<TITLE><%= getServletContext().getInitParameter("TITLENAME") %></TITLE>
<META http-equiv="Content-Type" content="text/html; charset=utf-8">

<META http-equiv="Content-Style-Type" content="text/css">
<LINK rel="stylesheet" href="../css/main.css" type="text/css">
<LINK rel="stylesheet" type="text/css" media="print" href="../css/impression.css">
<script type="text/javascript" language="JavaScript" src="../scripts/Numberformat.js"></script>


<link rel="stylesheet" type="text/css" href="../css/autocomplete.css" />
	<link type="text/css" rel="stylesheet" href="../css/mybutton.css">

<script type="text/javascript" src="../scripts/jquery-1.4.2.min.js"></script>
<script src="../scripts/jquery.autocomplete.js"></script>

<script type="text/javascript" src="../scripts/jquery.js"></script>
<script type="text/javascript" src="../scripts/phanTrang.js"></script>

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
<link href="../css/select2.css" rel="stylesheet" />
<script src="../scripts/select2.js"></script>
<script>
$(document).ready(function()
{
	$("#nppId").select2();
});
</script>

<LINK rel="stylesheet" href="../css/calendar.css" type="text/css">


<SCRIPT language="javascript" type="text/javascript">

function submitForm(arg){
	
	document.forms["ddhForm"].action.value = arg;
	document.forms["ddhForm"].submit();
	
}
function submitform()
{   
   document.forms["ddhForm"].submit();
}

function clearform()
{
    document.forms["ddhForm"].sku.value = "";
    document.forms["ddhForm"].kvId.value = "";
    document.forms["ddhForm"].tungay.value = "";
    document.forms["ddhForm"].denngay.value = "";
    document.forms["ddhForm"].trangthai.value = "";
    document.forms["ddhForm"].so.value = "";
    document.forms["ddhForm"].nppId.value = "";
    document.forms["ddhForm"].submit();
}

function  newform(){
	document.forms["ddhForm"].action.value ="new";
	  document.forms["ddhForm"].submit();
}
function processing(id,chuoi)
{
	 document.getElementById(id).innerHTML = "<a href='#'><img src='../images/waiting.gif' width = '20' height = '20' title='cho thuc hien..' border='0' longdesc='cho thuc hien..' style='border-style:outset'> Proc...</a>"; 		  
	 document.getElementById(id).href=chuoi;
}

function thongbao()
{
	 var tt = document.forms["ddhForm"].msg.value;
	 if(tt.length>0)
    	alert(document.forms["ddhForm"].msg.value);
 }
 
function xuatExcel()
{
	document.forms['ddhForm'].action.value= 'toExcel';
	document.forms['ddhForm'].submit();
}

</SCRIPT>
</HEAD>
<BODY leftmargin="0" bottommargin="0" topmargin="0" rightmargin="0">
	<form id="ddhForm" name="ddhForm" method="post"
		action="../../DonmuahangSvl">
		<input type="hidden" name="userId" value='<%=userId%>'> 
		<input type="hidden" name="userTen" value='<%=userTen%>'> 
		<input type="hidden" name="action" value="1"> <input type="hidden" name="crrApprSplitting" value="<%= obj.getCrrApprSplitting() %>">
		<input type="hidden" name="nxtApprSplitting" value="<%= obj.getNxtApprSplitting() %>">
	<input type="hidden" name="msg" value='<%=obj.getMsg() %>'>
	<script language="javascript" type="text/javascript">
    	thongbao();
	</script> 
	<%obj.SetMsg(""); %>
		<TABLE width="100%" border="0" cellspacing="0" cellpadding="0"
			height="100%" bgcolor="#FFFFFF">
			<TR>
				<TD colspan="4" align='left' valign='top'>
					<!--begin body Dossier--> <!--begin common dossier info---> <!--End common dossier info--->


					<TABLE width="100%" cellspacing="0" cellpadding="0">
						<TR>
							<TD>
								<TABLE width="100%" cellspacing="1" cellpadding="0">
									<TR>
										<TD align="left" class="tbnavigation">
											<table width="100%" border="0" cellpadding="0"
												cellspacing="0">
												<tr height="22">
													<TD align="left" colspan="2" class="tbnavigation">&nbsp;Qu???n l?? b??n h??ng
														&gt; &nbsp;T???o ????n h??ng</TD>

													<TD colspan="2" align="right" class="tbnavigation"><%=Utility.GLanguage("Ch??o m???ng b???n",session,jedis) %>  <%=obj.getTen() %> &nbsp;</TD>
												</tr>
											</table></TD>
									</TR>
								</TABLE>

								<TABLE border="0" width="100%" cellspacing=0 cellpadding=0>

									<TR>
										<TD width="100%" align="left">
											<FIELDSET>
												<LEGEND class="legendtitle">&nbsp;Ti??u ch?? hi???n th???
													&nbsp;</LEGEND>

												<TABLE width="100%" cellpadding="4" cellspacing="0">
													<TR>
														<TD width="19%" class="plainlabel">Ch???a SKU</TD>
														<TD width="81%" class="plainlabel"><input type="text"
															id="sku" name="sku" value="<%= obj.getSKU() %>" size=40 />

														</TD>
													</TR>
													<TR>
														<TD class="plainlabel"><%=Utility.GLanguage("T??? ng??y",session,jedis) %></TD>
														<TD class="plainlabel">
															<TABLE cellpadding="0" cellspacing="0">
																<TR>
																	<TD><input class="days" type="text" name="tungay"
																		value="<%=obj.getTungay() %>" size="20"></TD>

																</TR>
															</TABLE></TD>
													</TR>
													<TR>
														<TD class="plainlabel"><%=Utility.GLanguage("?????n ng??y",session,jedis) %></TD>
														<TD class="plainlabel">
															<TABLE cellpadding="0" cellspacing="0">
																<TR>
																	<TD><input class="days" type="text" name="denngay"
																		value="<%=obj.getDenngay() %>" size="20"></TD>

																</TR>
															</TABLE></TD>

													</TR>
													<TR>
														<TD class="plainlabel"><%=Utility.GLanguage("Tr???ng th??i",session,jedis) %></TD>
														<TD class="plainlabel"><select id="trangthai" name="trangthai" onChange="submitform();">
																<%if (obj.getTrangthai().equals("1")){ %>
																<option value=""></option>
																<option value="1" selected>Ch??? x??? l??</option>
																<option value="2" >TT duy???t ????n h??ng</option>
																<option value="3">ERP duy???t ????n h??ng</option>
																<option value="4">???? xu???t H?? </option>
																<option value="6">???? h???y ????n ??H</option>
																<%}else{ 							
								  						if (obj.getTrangthai().equals("2")){ %>
																<option value=""></option>
																<option value="1">Ch??? x??? l??</option>
																<option value="2" selected>TT duy???t ????n h??ng</option>
																<option value="3">ERP duy???t ????n h??ng</option>
																<option value="4">???? xu???t H?? </option>
																<option value="6">???? h???y ????n ??H</option>
																<%}else{ 
														if (obj.getTrangthai().equals("3")){ %>
																<option value=""></option>
																<option value="1">Ch??? x??? l??</option>
																<option value="2" >TT duy???t ????n h??ng</option>
																<option value="3" selected>ERP duy???t ????n h??ng</option>
																<option value="4">???? xu???t H?? </option>
																<option value="6">???? h???y ????n ??H</option>
													<%} else{ 																	   	 
														if (obj.getTrangthai().equals("4")){ %>
																<option value=""></option>
																<option value="1">Ch??? x??? l??</option>
																<option value="2" >TT duy???t ????n h??ng</option>
																<option value="3">ERP duy???t ????n h??ng</option>
																<option value="4" selected>???? xu???t H?? </option>
																<option value="6">???? h???y ????n ??H</option>

																<%	} else { if(obj.getTrangthai().equals("5")) { %>
																	<option value=""></option>
																	<option value="1">Ch??? x??? l??</option>
																	<option value="2" >TT duy???t ????n h??ng</option>
																	<option value="3">ERP duy???t ????n h??ng</option>
																	<option value="4">???? xu???t H?? </option>
																	<option value="6" selected>???? h???y ????n ??H</option>
																	
																 <%} else { if(obj.getTrangthai().equals("6")){ %> 
																 	<option value=""></option>
																	<option value="1">Ch??? x??? l??</option>
																	<option value="2" selected>TT duy???t ????n h??ng</option>
																	<option value="3">ERP duy???t ????n h??ng</option>
																	<option value="4">???? xu???t H?? </option>
																	<option value="6" selected>???? h???y ????n ??H</option>
																	
																  <%} else { %>
																	<option value=""></option>
																	<option value="1">Ch??? x??? l??</option>
																	<option value="2" >TT duy???t ????n h??ng</option>
																	<option value="3">ERP duy???t ????n h??ng</option>
																	<option value="4">???? xu???t H?? </option>
																	<option value="6">???? h???y ????n ??H</option>
																<%  } } } } } } %>
														</select>
														</TD>
													</TR>
													<TR>
														<TD class="plainlabel"><%=Utility.GLanguage("Khu v???c",session,jedis) %></TD>
														<TD width="81%" class="plainlabel"><SELECT
															name="kvId" onChange="submitform();">
																<option value=""></option>
																<%  try{
								  		while(kvlist.next()){								  			
								  			if (obj.getKvId().equals(kvlist.getString("kvId"))){ %>
																<option value="<%= kvlist.getString("kvId")%>" selected><%= kvlist.getString("kv")%></option>
																<%}else{ %>
																<option value="<%= kvlist.getString("kvId")%>"><%= kvlist.getString("kv")%></option>
																<%		}
								  		}	
								  	}catch (java.sql.SQLException e){} %>
														</SELECT>
														</TD>
													</TR>
														<TR>
														<TD width="19%" class="plainlabel">S??? SO</TD>
														<TD width="81%" class="plainlabel"><input type="text"
															id="so" name="so" value="<%=obj.getSO()%>" size=40 onchange="submitform();" />

														</TD>
													</TR>
													
													<TR>													
												<td width="9%" class="plainlabel"><%=Utility.GLanguage("Nh?? ph??n ph???i",session,jedis) %></td>
									<td width="33%" class="plainlabel">
									<SELECT name="nppId" id="nppId" style="width:350px" >
									<option value="">All </option>
									<% if(npp != null){
										  try
										  { 
											  String optionGroup = "";
											  String optionName = "";
											  int i = 0;
											  
											  while(npp.next())
											  { 
												 if(i == 0)
												 {
													 optionGroup = npp.getString("kvTen");
													 optionName = npp.getString("kvTen");
													 
													 %>
													 
													 <optgroup label="<%= optionName %>" >
												 <% }
												 
												 optionGroup = npp.getString("kvTen");
												 if(optionGroup.trim().equals(optionName.trim()))
												 { %>
													 <% if(npp.getString("nppId").equals(obj.getNppId())) {%>
													 	<option value="<%= npp.getString("nppId") %>" selected="selected" ><%= npp.getString("nppTen") %></option>
													 <%} else { %>
													 	<option value="<%= npp.getString("nppId") %>"><%= npp.getString("nppTen") %></option>
													 <%} %>
												 <% }
												 else
												 {
													 %>
													</optgroup>
													<% optionName = optionGroup; %>
													<optgroup label="<%= optionName %>" >
													<% if(npp.getString("nppId").equals(obj.getNppId())) {%>
													 	<option value="<%= npp.getString("nppId") %>" selected="selected" ><%= npp.getString("nppTen") %></option>
													 <%} else { %>
													 	<option value="<%= npp.getString("nppId") %>"><%= npp.getString("nppTen") %></option>
													 <%} %>
												 <% }
												 i++;
								     	 	  } 
											  %>
											  	</optgroup>
											  <%npp.close(); 
								     	 }catch(java.sql.SQLException e){}}%>	  
                                	</SELECT>
								</td>
													</TR>
													<TR>

														<TD class="plainlabel" align="left" colspan=2>
														<a  class="button1" href="javascript:submitForm('Hien thi')"> <img style="top: -4px;" src="../images/Search30.png"alt=""><%=Utility.GLanguage("T??m ki???m",session,jedis) %> </a>
															&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; <a class="button2"
															href="javascript:clearform()"> <img
																style="top: -4px;" src="../images/button.png" alt="">Nh???p
																l???i</a>&nbsp;&nbsp;&nbsp;&nbsp;
																 

        &nbsp;&nbsp;&nbsp;&nbsp;
							<a class="button2" href="javascript:xuatExcel()"> <img style="top: -4px;" src="../images/button.png" alt=""><%=Utility.GLanguage("Xu???t Excel",session,jedis) %> </a>&nbsp;&nbsp;&nbsp;&nbsp;

														</TD>

													</TR>
												</TABLE>
											</FIELDSET></TD>
									</TR>

								</TABLE></TD>
						</TR>
					</TABLE>
					<TABLE width="100%" cellspacing=0 cellpadding=0>
						<TR>
							<TD>
								<FIELDSET>
									<LEGEND class="legendtitle">
										&nbsp;&nbsp;&nbsp;????n ?????t h??ng&nbsp;&nbsp;&nbsp;&nbsp; <a
											class="button3" href="javascript:newform()"> <img
											style="top: -4px;" src="../images/New.png" alt="">T???o
											m???i </a>
									</LEGEND>

									<TABLE width="100%">
										<TR>
											<TD>
												<TABLE width="100%" border="0" cellspacing="1"
													cellpadding="4">
													<TR class="tbheader">
														<TH width="9%">Ng??y ?????t h??ng</TH>
														<TH width="9%">S??? PO</TH>
														<TH width="9%">S??? SO</TH>
														<TH width="17%"><%=Utility.GLanguage("Nh?? ph??n ph???i",session,jedis) %></TH>
														<TH width="10%"><%=Utility.GLanguage("Ng?????i t???o",session,jedis) %></TH>
														<TH width="9%">T.ti???n ??.h??ng</TH>
														<TH width="10%"><%=Utility.GLanguage("Tr???ng th??i",session,jedis) %></TH>
														<TH width="9%">Ng??y h??a ????n</TH>
														<TH width="9%">Ng??y nh???n h??ng</TH>
														<TH width="9%">T.ti???n h.????n</TH>
														<TH width="8%" align="center">&nbsp;T??c v???</TH>
													</TR>

													<% 
                            NumberFormat formatter = new DecimalFormat("#,###,###");
                            int m = 0;
                            String lightrow = "tblightrow";
                            String darkrow = "tbdarkrow";
							if(dhlist != null){
								try{								
                                    while (dhlist.next()){
                                        	
                                      	String chuoi="";
                                    	String ngaydat=dhlist.getString("ngaydat");
										  if( dhlist.getString("loaidonhang").equals("1")){
											  
											  chuoi="color: red;";
											  ngaydat=dhlist.getString("ngaydat")+"("+dhlist.getString("nam")+"-"+dhlist.getString("thang")+")";
										  }
										  
										  
                                       	if (m % 2 != 0) {
                                       	
                                     
											  
											  %>
											  
													<TR style="<%=chuoi %>"  class=<%=lightrow%> >
														<%} else {%>
													
													<TR style="<%=chuoi %>"  class=<%= darkrow%>>
														<%}%>
														<TD align="left"><div align="left"><%= ngaydat%></div>
														</TD>
														<TD align="left"><div align="left"><%= dhlist.getString("soid")%></div>
														</TD>
														
														<TD>
														<%
                                                  		 if (dhlist.getString("trangthai").equals("7")){ %>
															<span style="color: red;">
																<%=  dhlist.getString("chungtu") %>
															</span>
														<%} else{ %>
															<span >
																<%=  dhlist.getString("chungtu") %>
															</span>
														<%} %>
														</TD>
														<TD><div align="left"><%= dhlist.getString("nppTen") %></div>
														</TD>
														<TD><div align="center"><%= dhlist.getString("nguoitao") %></div>
														</TD>
														<TD><% String thd="0";
														   if(dhlist.getString("sotienavat").length()!=0)
															{
																thd=dhlist.getString("sotienavat");

															}
														
														%><div align="right"><%=formatter.format(Double.parseDouble(thd)) %></div>
														</TD>

														<%
                                                  		 if (dhlist.getString("trangthai").equals("1")){ %>
														<TD><div align="center">Ch??? x??? l??</div>
														</TD>
														<%}else  if (dhlist.getString("trangthai").equals("2")) {%>
														<TD><div align="center"> TT duy???t ????n h??ng</div>
														</TD>
														
														<% } else if  (dhlist.getString("trangthai").equals("3")){ %> 
															<TD><div align="center">ERP duy???t ????n h??ng</div></TD>	
														<% } else if (dhlist.getString("trangthai").equals("4")) { %>
														<TD><div align="center">???? xu???t H??</div></TD>													
														<% } else if (dhlist.getString("trangthai").equals("6")) { %>
															<TD><div align="center">???? h???y </div></TD>		
														<% } else if (dhlist.getString("trangthai").equals("7")) { %>
															<TD><div align="center">NPP H???y H?? </div></TD>
														 <%}else { %>
														<TD><div align="center">Kh??ng x??c ?????nh </div></TD>
														<%} %>
														<% if (dhlist.getString("ngayhd").equals("0")){ %>
														<TD align="center">&nbsp;</TD>
														<%}else{ %>
														<TD align="center"><%=dhlist.getString("ngayhd") %></TD>
														<%} %>
														<TD align="center"><%=dhlist.getString("ngaynhan") %></TD>
														<% if (dhlist.getString("sotienavat").equals("0")){ %>
														<TD align="center">&nbsp;</TD>
														<%}else
														{ %>
														<% String thd2="0";
														   if(dhlist.getString("tienhd").length()!=0)
															{
																thd2 =dhlist.getString("tienhd");

															}
														
														%>
														<TD align="right"><%=formatter.format(Double.parseDouble(thd2)) %></TD>
														

														<%} %>

														<TD align="center">
															<TABLE border=0 cellpadding="1" cellspacing="0">
																<TR>
																	<% 
                                	           						  int t	= Integer.valueOf(dhlist.getString("trangthai")).intValue();%>
																	<TD width=25>
																	<%if(quyen[3]!=0){ %>
																	<A
																	   href="../../ERP_DonDatHangUpdateSvl?userId=<%=userId%>&display=<%=dhlist.getString("chungtu") %>"><img
																	   src="../images/Display20.png" alt="Hi???n th???"
																	   width="20" height="20" title="Hi???n th???" border="0"   >
																	</A>
																	<%} %>
																	</TD>
																	
																	<% if (t == 1){ %>
																	
																	<TD width=25>
																	
																	<% 
																	 if( dhlist.getString("loaidonhang").equals("0")){
																	if(quyen[2]!=0) {%>
																	<A id='suaphieu<%=dhlist.getString("chungtu")%>'
																	   href=""><img
																	   src="../images/Edit.png" alt="Sua Don Hang"
																	   width="20" height="20" title="S???a ????n H??ng"
																	   border="0" onclick="processing('<%="suaphieu"+dhlist.getString("chungtu")%>' , '../../ERP_DonDatHangUpdateSvl?userId=<%=userId%>&edit=<%=dhlist.getString("chungtu") %>')" >
																	</A>
																	<%} }%>
																	</TD>
																	
																	<TD width=25>
																	<% 
																	if(quyen[4]!=0)
																	{
																		if(dhlist.getDouble("cktm")>0 &&  dhlist.getString("isduyetcktm").equals("1")  )
																		{
																	%>
																		<A id='chotphieu<%=dhlist.getString("chungtu")%>' href=""><img src="../images/Chot.png" alt="Ch???t ????n h??ng" width="20" height="20" title="Ch???t ????n h??ng" border="0" onclick="if(!confirm('B???n c?? mu???n ch???t ????n h??ng n??y?')) {return false ;}else{ processing('<%="chotphieu"+dhlist.getString("chungtu")%>' , '../../DonmuahangSvl?userId=<%=userId%>&chot=<%=dhlist.getString("chungtu") %>');}"  ></A>
																	<%}  else if(dhlist.getDouble("cktm") <=0 ){  %>
																		<A id='chotphieu<%=dhlist.getString("chungtu")%>' href=""><img src="../images/Chot.png" alt="Ch???t ????n h??ng" width="20" height="20" title="Ch???t ????n h??ng" border="0" onclick="if(!confirm('B???n c?? mu???n ch???t ????n h??ng n??y?')) {return false ;}else{ processing('<%="chotphieu"+dhlist.getString("chungtu")%>' , '../../DonmuahangSvl?userId=<%=userId%>&chot=<%=dhlist.getString("chungtu") %>');}"  ></A>																		
																	<%}
																	}
																	%>
																	</TD>
																	<TD width=25>
																	<%if(quyen[1]!=0){ %>
																	<A id='deletephieu<%=dhlist.getString("chungtu")%>'
																	   href=""><img
																	   src="../images/Delete.png" alt="H???y ????n h??ng"
																	   width="20" height="20" title="H???y ????n h??ng" 
																	   border="0" onclick="if(!confirm('B???n c?? mu???n h???y ????n h??ng n??y?')) {return false ;}else{ processing('<%="deletephieu"+dhlist.getString("chungtu")%>' , '../../DonmuahangSvl?userId=<%=userId%>&delete=<%=dhlist.getString("chungtu") %>');}"  >
																	</A>
																	<%} %>
																	</TD>
																	<% }
                         	                   	 					%>
                         	                   	 					<% if (t == 2 && dhlist.getString("kbh_fk").equals("100025") ){
																	 if( dhlist.getString("loaidonhang").equals("0")){
																			if(quyen[4]!=0){ %>
																			<TD width=25>
																			<A id='huychotphieu<%=dhlist.getString("chungtu")%>'
																			   href=""><img
																			   src="../images/unChot.png" alt="H???y Ch???t ????n h??ng"
																			   width="20" height="20" title="H???y Ch???t ????n h??ng" 
																			   border="0" onclick="if(!confirm('B???n c?? mu???n h???y ch???t ????n h??ng n??y?')) {return false ;}else{ processing('<%="huychotphieu"+dhlist.getString("chungtu")%>' , '../../DonmuahangSvl?userId=<%=userId%>&huychot=<%=dhlist.getString("chungtu") %>');}"  >
																			</A>
																			</TD>		
																			<%} %>
																	<% }}%>
																	
																	<TD>
																		<A href="../../DonMuaHangExcel?userId=<%=userId%>&excel=<%=dhlist.getString("chungtu") %>">
																		<img src="../images/excel.gif" alt="In ????n h??ng chi ti???t Excel" width="20" height="20" longdesc="In ????n h??ng chi ti???t Excel" border=0> </A>
																	</TD>
																	
																</TR>
															</TABLE></TD>
													</TR>
													<% m++; } 
                                    
                                        }catch(Exception  e){
                                        	e.printStackTrace();
                                        }
                               		}%>

													<tr class="tbfooter">
														<TD align="center" valign="middle" colspan="13"
															class="tbfooter">
															<%if(obj.getNxtApprSplitting() >1) {%> <img alt="Trang Dau"
															src="../images/first.gif" style="cursor: pointer;"
															onclick="View('ddhForm', 1, 'view')"> &nbsp; <%}else {%>
															<img alt="Trang Dau" src="../images/first.gif">
															&nbsp; <%} %> <% if(obj.getNxtApprSplitting() > 1){ %> <img
															alt="Trang Truoc" src="../images/prev.gif"
															style="cursor: pointer;"
															onclick="View('ddhForm', eval(ddhForm.nxtApprSplitting.value) -1, 'view')">
															&nbsp; <%}else{ %> <img alt="Trang Truoc"
															src="../images/prev_d.gif"> &nbsp; <%} %> <%
													int[] listPage = obj.getNextSplittings();
													for(int i = 0; i < listPage.length; i++){
												%> <% 
												
												if(listPage[i] == obj.getNxtApprSplitting()){ %> <a
															style="color: white;"><%= listPage[i] %>/ <%=obj.getTheLastSplitting() %></a>
															<%}else{ %> <a
															href="javascript:View('ddhForm', <%= listPage[i] %>, 'view')"><%= listPage[i] %></a>
															<%} %> <input type="hidden" name="list"
															value="<%= listPage[i] %>" /> &nbsp; <%} %> <% if(obj.getNxtApprSplitting() < obj.getTheLastSplitting()){ %>
															&nbsp; <img alt="Trang Tiep" src="../images/next.gif"
															style="cursor: pointer;"
															onclick="View('ddhForm', eval(ddhForm.nxtApprSplitting.value) +1, 'view')">
															&nbsp; <%}else{ %> &nbsp; <img alt="Trang Tiep"
															src="../images/next_d.gif"> &nbsp; <%} %> <%if(obj.getNxtApprSplitting() == obj.getTheLastSplitting()) {%>
															<img alt="Trang Cuoi" src="../images/last.gif">
															&nbsp; <%}else{ %> <img alt="Trang Cuoi"
															src="../images/last.gif" style="cursor: pointer;"
															onclick="View('ddhForm', -1, 'view')"> &nbsp; <%} %>
														</TD>
													</tr>


												</TABLE></TD>
										</TR>
									</TABLE>
								</FIELDSET></TD>
						</TR>
					</TABLE></TD>
			</TR>
		</TABLE>
	</form>
	<p id="result"></p>

</BODY>
</HTML>

<% if (!( dhlist == null)) dhlist.close(); %>
<% if (!( kvlist == null)) kvlist.close(); %>
<% obj.DBclose(); obj=null; %>
<%}%>