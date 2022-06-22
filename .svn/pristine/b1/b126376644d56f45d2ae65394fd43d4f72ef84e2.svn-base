<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page  import = "java.util.List" %>
<%@ page  import = "geso.dms.center.beans.banggiablc.IBanggiablc" %>
<%@ page  import = "geso.dms.center.beans.banggiablc.IBanggiablcList" %>
<%@ page  import = "java.sql.ResultSet" %>
<%@ page  import = "geso.dms.center.util.*" %>
<%	String userId = (String) session.getAttribute("userId");  
	String userTen = (String) session.getAttribute("userTen");  	
	String sum = (String) session.getAttribute("sum");
	Utility util = (Utility) session.getAttribute("util");
	if(!util.check(userId, userTen, sum)){
		response.sendRedirect(request.getContextPath() + "/redirect.jsp");
	}else{ %>

<% IBanggiablcList obj = (IBanggiablcList)session.getAttribute("obj"); %>
<% List<IBanggiablc> bgblclist = (List<IBanggiablc>)obj.getBgblcList(); %>
<% ResultSet dvkd = (ResultSet)obj.getDvkd();  
	int[] quyen = new  int[6];
	quyen = util.Getquyen("BanggiabanlechuanSvl","",userId);
	String url = util.getChuyenNguUrl("BanggiabanlechuanSvl", "",session);
%>
<% Utility Util = new Utility(); %>
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
<form name="bgblcForm" method="post" action="../../BanggiabanlechuanSvl">
<% redis.clients.jedis.Jedis jedis =  geso.dms.center.util.Utility.getJedis(); %><% geso.dms.center.util.Csrf csdr=new geso.dms.center.util.Csrf(request,response,true,false,true);%>
 <input type="hidden" name="<%=csdr.get_tokenName() %>" value='<%=csdr.get_tokenValue() %>'>

<input type="hidden" name="userId" value='<%=obj.getUserId() %>'>
<input type="hidden" name="msg" id="msg" value='<%=Utility.GLanguage(obj.getMsg(),session,jedis) %>'>
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
					   		&nbsp;<%=url%></TD>
					   <TD colspan="2" align="right" class="tbnavigation"><%=Utility.GLanguage("Chào mừng bạn",session,jedis) %>  <%=userTen %>&nbsp;  </TD>
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
							<LEGEND class="legendtitle"><%=Utility.GLanguage("Tiêu chí tìm kiếm",session,jedis) %> &nbsp;</LEGEND>

							<TABLE  width="100%" cellpadding="6" cellspacing="0">
								<TR>
									<TD class="plainlabel"><%=Utility.GLanguage("Tên bảng giá",session,jedis) %></TD>
									<TD class="plainlabel"><INPUT name ="bgTen" type="text" value="<%= obj.getTen() %>" size="40" onChange = "submitform();"/></TD>
									<TD class="plainlabel"><%=Utility.GLanguage("Đơn vị kinh doanh",session,jedis) %>  </TD>
								    <TD class="plainlabel"><SELECT  name="dvkdId"  onChange = "submitform();">
								  		<option value =""></option>
								  		
								  	 <% try{ while(dvkd.next()){ 
								    	if(dvkd.getString("dvkdId").equals(obj.getDvkdId())){ %>
								      		<option value='<%=dvkd.getString("dvkdId") %>' selected><%=dvkd.getString("dvkd") %></option>
								      	<%}else{ %>
								     		<option value='<%=dvkd.getString("dvkdId") %>'><%=dvkd.getString("dvkd") %></option>
								     	<%}}}catch(java.sql.SQLException e){} %>	
								     	
									</SELECT></TD>
								</TR>
								
								<TR>
									<TD class="plainlabel"><%=Utility.GLanguage("Trạng thái",session,jedis) %></TD>
									<TD class="plainlabel"><select name="trangthai" onChange = "submitform();">
								  	<% if (obj.getTrangthai().equals("0")){ %>
								    	<option value="2"> </option>
								    	<option value="1"><%=Utility.GLanguage("Hoạt động",session,jedis) %></option>
								    	<option value="0" selected><%=Utility.GLanguage("Ngưng hoạt động",session,jedis) %></option>
									<%}else{ 							
								  		if (obj.getTrangthai().equals("1")){ %>
								    	<option value="2"> </option>
								    	<option value="1" selected><%=Utility.GLanguage("Hoạt động",session,jedis) %></option>
								    	<option value="0" ><%=Utility.GLanguage("Ngưng hoạt động",session,jedis) %></option>
									<%}else{ %>
								    	<option value="2" selected> </option>
								    	<option value="1" ><%=Utility.GLanguage("Hoạt động",session,jedis) %></option>
								    	<option value="0" ><%=Utility.GLanguage("Ngưng hoạt động",session,jedis) %></option>
									<%}} %>
								    	</select></TD>
								    <TD class="plainlabel" colspan="2">
										<a class="button2" href="javascript:clearform()">
										<img style="top: -4px;" src="../images/button.png" alt="" onClick="clearform()" ><%=Utility.GLanguage("Nhập lại",session,jedis) %></a>&nbsp;&nbsp;&nbsp;&nbsp;	
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
					<LEGEND class="legendtitle">&nbsp; <%=Utility.GLanguage("Bảng giá bán",session,jedis) %> &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
					<%if(quyen[Utility.THEM]!=0) {%>
				
				    	<a class="button3" href="../../RouterSvl?task=<%=Utility.dongMa(getServletContext().getInitParameter("RedirectNoScript") + "BanggiabanlechuanUpdateSvl?userId=" + userId + "&copy=0" ) %>">
				    	<img style="top: -4px;" src="../images/New.png" alt="" onClick="newform();">Tạo mới </a>				    	
   	
					<%} %>
					</LEGEND>
	
					<TABLE class="" width="100%">
						<TR>
							<TD width="98%">
							<TABLE width="100%" border="0" cellspacing="1" cellpadding="4">
								<TR class="tbheader">
									<TH width="4%"><%=Utility.GLanguage("STT",session,jedis) %></TH>
									<TH ><%=Utility.GLanguage("Tên bảng giá",session,jedis) %> </TH>
									<TH ><%=Utility.GLanguage("Đơn vị kinh doanh",session,jedis) %> </TH>
									<TH ><%=Utility.GLanguage("Trạng thái",session,jedis) %></TH>
									<TH width="9%"><%=Utility.GLanguage("Ngày tạo",session,jedis) %></TH>
									<TH width="15%"><%=Utility.GLanguage("Người tạo",session,jedis) %> </TH>
									<TH width="9%"><%=Utility.GLanguage("Ngày sửa",session,jedis) %></TH>
									<TH width="15%"><%=Utility.GLanguage("Người sửa",session,jedis) %></TH>
									<TH width="8%" align="center">&nbsp;<%=Utility.GLanguage("Tác vụ",session,jedis) %></TH>
								</TR>

									<% 
									IBanggiablc bgblc = null;
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
												<TD align="left"><div align="left"><%=bgblc.getDvkd() %></div></TD>                                   
												<%if (bgblc.getTrangthai().equals("1")){%>
													<TD align="center"><%=Utility.GLanguage("Hoạt động",session,jedis) %></TD>
												<%}else{ %>
													<TD align="center"><%=Utility.GLanguage("Ngưng hoạt động",session,jedis) %></TD>
												<%} %>
												<TD align="center"><%=bgblc.getNgaytao()%></TD>
												<TD align="center"><%=bgblc.getNguoitao()%></TD>
												<TD align="center"><%=bgblc.getNgaysua()%></TD>
												<TD align="center"><%=bgblc.getNguoisua()%></TD>
												<TD align="center">
												<TABLE border = 0 cellpadding="0" cellspacing="0">
													<TR>
													<TD>
													<%if(bgblc.getTrangthai().equals("0") && quyen[Utility.SUA]!=0){ %>
														<A href = "../../RouterSvl?task=<%= Util.dongMa(getServletContext().getInitParameter("RedirectNoScript") +"BanggiabanlechuanUpdateSvl?userId="+userId+"&update="+ bgblc.getId()+"") %>"><img src="../images/Edit.png" alt="Cap nhat" title="Cập nhật" width="20" height="20" longdesc="Cap nhat" border = 0></A>
													<%} %>
													</TD>
													<TD>
														<%if(bgblc.getTrangthai().equals("0") &&  quyen[Utility.XOA]!=0){ %>
															<A href = "../../RouterSvl?task=<%= Util.dongMa(getServletContext().getInitParameter("RedirectNoScript") +"BanggiabanlechuanSvl?userId="+userId+"&delete="+bgblc.getId()+"")  %>"><img src="../images/Delete20.png" alt="Xoa" width="20" height="20" longdesc="Xoa" border=0 onclick="if(!confirm('Ban Muon Xoa Bảng giá Nay?')) return false;"></A>
														<%} %>	
													</TD>
													
													<TD>
														<%if(bgblc.getTrangthai().equals("0") &&  quyen[Utility.CHOT]!=0){ %>
															<A href = "../../RouterSvl?task=<%= Util.dongMa(getServletContext().getInitParameter("RedirectNoScript") +"BanggiabanlechuanSvl?userId="+userId+"&chot="+bgblc.getId()+"")  %>"><img src="../images/Chot.png" alt="Xoa" width="20" height="20" longdesc="Xoa" border=0 onclick="if(!confirm('Ban Muon Chốt Bảng giá Nay?')) return false;"></A>
														<%} %>	
													</TD>
													
													<TD>
													<%if(quyen[Utility.XEM]!=0){ %>
														<A href = "../../RouterSvl?task=<%= Util.dongMa(getServletContext().getInitParameter("RedirectNoScript") +"BanggiabanlechuanUpdateSvl?userId="+userId+"&display="+ bgblc.getId()+"") %>"><img src="../images/Display.png" alt="Hien thi" title="Hiển thị" width="20" height="20" longdesc="Hien thi" border = 0></A>
													<%} %>
													</TD>
													
													<TD>
													<%if(quyen[Utility.THEM]!=0 && bgblc.getTrangthai().equals("1")){ %>
														<A href = "../../RouterSvl?task=<%= Util.dongMa(getServletContext().getInitParameter("RedirectNoScript") +"BanggiabanlechuanUpdateSvl?userId="+userId+"&copy="+ bgblc.getId()+"") %>">
															<img src="../images/copy20.png" alt="Cap nhat" title="copy" width="20" height="20" longdesc="Cap nhat" border = 0>
														</A>
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
<%geso.dms.center.util.Utility.JedisClose(jedis); %>
</form>
</body>
</HTML>
<% if (dvkd != null)
	dvkd.close(); 
	obj.DbClose();
	}
	
%>