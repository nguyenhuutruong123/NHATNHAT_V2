<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page  import = "java.util.List" %>
<%@ page  import = "geso.dms.distributor.beans.ctchietkhau.ICtChietKhau" %>
<%@ page  import = "geso.dms.distributor.beans.ctchietkhau.ICtChietKhauList" %>
<%@ page  import = "java.sql.ResultSet" %>
<%@ page  import = "geso.dms.center.util.*" %>
<%	String userId = (String) session.getAttribute("userId");  
	String userTen = (String) session.getAttribute("userTen");  	
	String sum = (String) session.getAttribute("sum");
	Utility util = (Utility) session.getAttribute("util");
	if(!util.check(userId, userTen, sum)){
		response.sendRedirect(request.getContextPath() + "/redirect.jsp");
	}else{ %>

<% ICtChietKhauList obj = (ICtChietKhauList)session.getAttribute("obj"); %>
<% List<ICtChietKhau> bgblclist = (List<ICtChietKhau>)obj.getBgblcList(); %>
<% ResultSet dvkd = (ResultSet)obj.getDvkd();  
	int[] quyen = new  int[6];
	quyen = util.Getquyen("CtChietKhauSvl","",userId);
%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"> <% Utility Utilback = new Utility(); %>
<HTML>
<HEAD>
<TITLE><%= getServletContext().getInitParameter("TITLENAME") %></TITLE>
<META http-equiv="Content-Type" content="text/html; charset=utf-8">
<META http-equiv="Content-Style-Type" content="text/css">
<LINK rel="stylesheet" href="../css/main.css" type="text/css">
<link type="text/css" rel="stylesheet" href="../css/mybutton.css">
<SCRIPT language="javascript" type="text/javascript">
function clearform()
{
    document.bgblcForm.bgTen.value = "";
    document.bgblcForm.dvkdId.selectedIndex = 0;
    document.bgblcForm.trangthai.selectedIndex = 0;
    submitform();   
}

function submitform()
{
	document.bgblcForm.action.value= 'search';
	document.forms['bgblcForm'].submit();
}

function newform()
{
	document.forms['bgblcForm'].action.value= 'new';
	document.forms['bgblcForm'].submit();
}
function thongbao()
{
	if(document.getElementById("msg").value != '')
		alert(document.getElementById("msg").value);
}
</SCRIPT>
</HEAD>
<body leftmargin="0" bottommargin="0" topmargin="0" rightmargin="0">
<form name="bgblcForm" method="post" action="../../CtChietKhauSvl">
<input type="hidden" name="userId" value='<%=obj.getUserId() %>'>
<input type="hidden" name="msg" id="msg" value='<%= obj.getMsg() %>'>
<input type="hidden" name="action" value='1'>
<script type="text/javascript">
	thongbao();
</script>
<TABLE width="100%" border="0" cellspacing="0" cellpadding="0"
	height="100%">
	
	<TR>
		<TD colspan="4" align='left' valign='top' bgcolor="#FFFFFF">

		<TABLE width="100%" cellpadding="0" cellspacing="1">
			<TR>
				<TD align="left" class="tbnavigation">
				  <TABLE width="100%" border="0" cellpadding="0" cellspacing="0">
					<TR height="22">
					   <TD align="left" colspan="2" class="tbnavigation">
					   		&nbsp;Qu???n l?? b??n h??ng &gt;????n h??ng ETC &gt; Chi???t kh???u b??c s??</TD>
					   <TD colspan="2" align="right" class="tbnavigation"><%=Utility.GLanguage("Ch??o m???ng b???n",session,jedis) %>  <%=userTen %>&nbsp;  </TD>
					</TR>
				  </TABLE>
				</TD>
			</TR>
		</TABLE>
		<TABLE width="100%" cellpadding="0" cellspacing="1">				
				<TR>
					<TD>
					<TABLE border="0" width="100%"  cellpadding="0" cellspacing="0">
						<TR>
							<TD width="100%" align="left"><FIELDSET>
							<LEGEND class="legendtitle"><%=Utility.GLanguage("Ti??u ch?? t??m ki???m",session,jedis) %> &nbsp;</LEGEND>

							<TABLE  width="100%" cellpadding="6" cellspacing="0">
								<TR>
									<TD class="plainlabel">T??n ch????ng tr??nh</TD>
									<TD class="plainlabel"><INPUT name ="bgTen" type="text" value="<%= obj.getTen() %>" size="40" onChange = "submitform();"/></TD>
									
								</TR>
								
								<TR>
									<TD class="plainlabel"><%=Utility.GLanguage("Tr???ng th??i",session,jedis) %></TD>
									<TD class="plainlabel"><select name="trangthai" onChange = "submitform();">
								  	<% if (obj.getTrangthai().equals("0")){ %>
								  		<option value=""></option>
								    	<option value="2">???? hu???</option>
								    	<option value="1">???? ch???t</option>
								    	<option value="0" selected>Ch??a ch???t</option>
									<%}else if (obj.getTrangthai().equals("1")){ %>
								  		<option value=""></option>
								    	<option value="2">???? hu???</option>
								    	<option value="1" selected>???? ch???t</option>
								    	<option value="0" >Ch??a ch???t</option>
							    	<%}else if (obj.getTrangthai().equals("2")){ %>
									<option value=""></option>
								    <option value="2" selected>???? hu???</option>
							    	<option value="1" >???? ch???t</option>
							    	<option value="0" >Ch??a ch???t</option>
									<%}else { %>
									<option value="" selected ></option>
								    <option value="2" >???? hu???</option>
							    	<option value="1" >???? ch???t</option>
							    	<option value="0" >Ch??a ch???t</option>
									<%} %>
								    	</select></TD>
								    	</TR>
								  <TR>
								    <TD class="plainlabel" colspan="2">
										<a class="button2" href="javascript:clearform()">
										<img style="top: -4px;" src="../images/button.png" alt="" onClick="clearform()" ><%=Utility.GLanguage("Nh???p l???i",session,jedis) %></a>&nbsp;&nbsp;&nbsp;&nbsp;	
                                	</TD>					
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
					<LEGEND class="legendtitle">&nbsp; Chi???t kh???u b??c s?? &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
					<%if(quyen[Utility.THEM]!=0) {%>
					<a class="button3" href="javascript:newform()">
    	<img style="top: -4px;" src="../images/New.png" alt="" onClick="newform();"><%=Utility.GLanguage("T???o m???i",session,jedis) %> </a>
					<%} %>
					</LEGEND>
	
					<TABLE class="" width="100%">
						<TR>
							<TD width="98%">
							<TABLE width="100%" border="0" cellspacing="1" cellpadding="4">
								<TR class="tbheader">
									<TH width="4%">STT</TH>
									<TH ><%=Utility.GLanguage("Di???n gi???i",session,jedis) %></TH>
									<TH ><%=Utility.GLanguage("Tr???ng th??i",session,jedis) %> </TH>
									<TH >Kh??ch h??ng</TH>
									<TH >H???p ?????ng </TH>
									<TH width="9%"><%=Utility.GLanguage("Ng??y t???o",session,jedis) %></TH>
									<TH width="15%"><%=Utility.GLanguage("Ng?????i t???o",session,jedis) %> </TH>
									<TH width="9%"><%=Utility.GLanguage("Ng??y s???a",session,jedis) %></TH>
									<TH width="15%"><%=Utility.GLanguage("Ng?????i s???a",session,jedis) %> </TH>
									<TH width="8%" align="center">&nbsp;T??c v???</TH>
								</TR>

									<% 
									ICtChietKhau bgblc = null;
									int size = bgblclist.size();
									int m = 0;
									String lightrow = "tblightrow";
									String darkrow = "tbdarkrow";
									while (m < size){
										bgblc = bgblclist.get(m);
										if (m % 2 != 0) {%>						
											<TR class= <%=lightrow%> >
										<%} else {%>
											<TR class= <%= darkrow%> >
										<%}%>
												<TD align="center"><%=m+1%></TD>
												<TD align="left"><div align="left"><%= bgblc.getTen()%></div></TD>                             
												<%if (bgblc.getTrangthai().equals("1")){%>
													<TD align="center">C??n hi???u l???c</TD>
												<%}else if (bgblc.getTrangthai().equals("-1")) { %>
												<TD align="center">H???t hi???u l???c</TD>
												<%}else if (bgblc.getTrangthai().equals("2")) { %>
													<TD align="center">???? hu???</TD>
												<%}else  { %>
													<TD align="center">Ch??a ch???t</TD>
												<%} %>
												<TD align="center"><%=bgblc.getKhId()%></TD>
												<TD align="center"><%=bgblc.getHopdongId()%></TD>
												<TD align="center"><%=bgblc.getNgaytao()%></TD>
												<TD align="center"><%=bgblc.getNguoitao()%></TD>
												<TD align="center"><%=bgblc.getNgaysua()%></TD>
												<TD align="center"><%=bgblc.getNguoisua()%></TD>
												<TD align="center">
												<TABLE border = 0 cellpadding="0" cellspacing="0">
													<TR>
													<TD>
													<%if(bgblc.getTrangthai().equals("0") && quyen[Utility.SUA]!=0){ %>
														<A href = "../../CtChietKhauUpdateSvl?userId=<%=userId%>&update=<%= bgblc.getId() %>"><img src="../images/Edit.png" alt="Cap nhat" title="C???p nh???t" width="20" height="20" longdesc="Cap nhat" border = 0></A>
													<%} %>
													</TD>
													<TD>
														<%if(bgblc.getTrangthai().equals("0") &&  quyen[Utility.XOA]!=0){ %>
															<A href = "../../CtChietKhauSvl?userId=<%=userId%>&delete=<%=bgblc.getId()  %>"><img src="../images/Delete20.png" alt="Xoa" width="20" height="20" longdesc="Xoa" border=0 onclick="if(!confirm('B???n mu???n Xo?? ch??nh s??ch n??y?')) return false;"></A>
														<%} %>	
													</TD>
													
													<TD>
														<%if(bgblc.getTrangthai().equals("0") &&  quyen[Utility.CHOT]!=0){ %>
															<A href = "../../CtChietKhauSvl?userId=<%=userId%>&chot=<%=bgblc.getId()  %>"><img src="../images/Chot.png" alt="Xoa" width="20" height="20" longdesc="Xoa" border=0 onclick="if(!confirm('B???n mu???n Ch???t ch??nh s??ch n??y?')) return false;"></A>
														<%} %>	
													</TD>
													
													<TD>
													<%if(quyen[Utility.XEM]!=0){ %>
														<A href = "../../CtChietKhauUpdateSvl?userId=<%=userId%>&display=<%= bgblc.getId() %>"><img src="../images/Display.png" alt="Cap nhat" title="C???p nh???t" width="20" height="20" longdesc="Cap nhat" border = 0></A>
													<%} %>
													</TD>
													
													
													
													<TD>&nbsp;</TD>

													</TR></TABLE>
												</TD>
											</TR>
								<%m++; }%>
								
								<TR>
									<TD align="center" colspan="10" class="tbfooter">&nbsp;		</TD>
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
</body>
</HTML>
<% if (dvkd != null)
	dvkd.close(); 
	obj.DbClose();
	}
	
%>