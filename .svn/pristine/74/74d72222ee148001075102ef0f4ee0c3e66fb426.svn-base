<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page  import = "geso.dms.center.beans.vitricuahang.IVitricuahang" %>
<%@ page  import = "geso.dms.center.beans.vitricuahang.IVitricuahangList" %>
<%@ page  import = "java.util.List" %>
<%@ page  import = "geso.dms.center.util.*" %>
<%	String userId = (String) session.getAttribute("userId");  
	String userTen = (String) session.getAttribute("userTen");
	System.out.println(userTen);
	String sum = (String) session.getAttribute("sum");
	Utility util = (Utility) session.getAttribute("util");
	if(!util.check(userId, userTen, sum)){
		response.sendRedirect(request.getContextPath() + "/redirect.jsp");
	}else{ %>

<% IVitricuahangList obj = (IVitricuahangList)session.getAttribute("obj"); %>
<% List<IVitricuahang> vtchlist = (List<IVitricuahang>)obj.getHchList(); 
	int[] quyen = new  int[6];
	quyen = util.Getquyen("VitricuahangSvl","",userId);
	String url = util.getChuyenNguUrl("VitricuahangSvl", "",session);
	
	System.out.println(quyen[0]);
	System.out.println(quyen[1]);
	System.out.println(quyen[2]);
	System.out.println(quyen[3]);
%>
<% Utility Util = new Utility(); %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"> <% Utility Utilback = new Utility(); %>
<HTML>
<HEAD>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<TITLE><%= getServletContext().getInitParameter("TITLENAME") %></TITLE>
<META http-equiv="Content-Style-Type" content="text/css">
<LINK rel="stylesheet" type="text/css" media="print" href="../css/impression.css">
<LINK rel="stylesheet" href="../css/main.css" type="text/css">
<link type="text/css" rel="stylesheet" href="../css/mybutton.css">
<SCRIPT language="javascript" type="text/javascript">
 function clearform()
 {
     document.vtchForm.ViTriCuaHang.value = "";
     document.vtchForm.DienGiai.value = "";      
     document.vtchForm.TrangThai.selectedIndex = 2;
     submitform();
 }

 function submitform()
 {
 	document.forms['vtchForm'].action.value= 'search';
 	document.forms['vtchForm'].submit();
 }

 function newform()
 {
 	document.forms['vtchForm'].action.value= 'new';
 	document.forms['vtchForm'].submit();
 }
 function thongbao()
 {var tt = document.forms['vtchForm'].msg.value;
 	if(tt.length>0)
     alert(document.forms['vtchForm'].msg.value);
 	}

 
 function xuatExcel()
 {
 	document.forms['vtchForm'].action.value= 'excel';
 	document.forms['vtchForm'].submit();
 	document.forms['vtchForm'].action.value= '';
 }
</SCRIPT>

</HEAD>
<BODY leftmargin="0" bottommargin="0" topmargin="0" rightmargin="0">
<form name="vtchForm" method="post" action="../../VitricuahangSvl">
<% redis.clients.jedis.Jedis jedis =  geso.dms.center.util.Utility.getJedis(); %><% geso.dms.center.util.Csrf csdr=new geso.dms.center.util.Csrf(request,response,true,false,true);%>
 <input type="hidden" name="<%=csdr.get_tokenName() %>" value='<%=csdr.get_tokenValue() %>'>

<input type="hidden" name="userId" value='<%=userId%>'>
<input type="hidden" name="action" value='1'>
<input type="hidden" name="msg" value='<%=obj.getMsg() %>'>

<script language="javascript" type="text/javascript">
    thongbao();
</script> 
<TABLE width="100%" border="0" cellspacing="0" cellpadding="0"
	height="100%">
	<TR>
		<TD colspan="4" align='left' valign='top' bgcolor="#ffffff">
			<TABLE width="100%" border="0" cellpadding="0" cellspacing="1">
				<TR>
					<TD align="left" >
					   <table width="100%" border="0" cellpadding="0" cellspacing="0">
						  <tr height="22">
							   <TD align="left" colspan="2" class="tbnavigation"><%= url %></TD>
							   <TD colspan="2" align="right" class="tbnavigation"><%=Utility.GLanguage("Chào mừng bạn",session,jedis) %>  <%=userTen %>&nbsp;  </TD> 
						    </tr>
   						</table>
					</TD>
				</TR>
			</TABLE>
			<TABLE width="100%" border="0" cellpadding="0" cellspacing="1">
				<TR>
					<TD>
						<TABLE border="0" width="100%" cellpadding="0" cellspacing="0">
							<TR>
								<TD width="100%" align="left"><FIELDSET>
									<LEGEND class="legendtitle"><%=Utility.GLanguage("Tiêu chí tìm kiếm",session,jedis) %>  </LEGEND>

									<TABLE class="tblight" width="100%" cellpadding="6" cellspacing = "0">
										<TR>
										  <TD class="plainlabel" ><%=Utility.GLanguage("Vị trí cửa hàng",session,jedis) %> </TD>
										  <TD class="plainlabel" ><INPUT name="ViTriCuaHang" size="20" type="text" 
                                          					value="<%= obj.getVitricuahang()%>" onChange="submitform();"></TD>
									  </TR>
										<TR>
											<TD width="20%" class="plainlabel" ><%=Utility.GLanguage("Diễn giải",session,jedis) %> </TD>
										    <TD width="80%" class="plainlabel" ><INPUT name="DienGiai" size="40" type="text" 
                                            				value="<%= obj.getDiengiai()%>" onChange="submitform();"></TD>
										</TR>
										<TR>
											<TD class="plainlabel" ><%=Utility.GLanguage("Trạng thái",session,jedis) %></TD>
											<TD class="plainlabel" >
											  <select name="TrangThai" onChange="submitform();">
											    <% if (obj.getTrangthai().equals("1")){%>
											  	<option value="1" selected><%=Utility.GLanguage("Hoạt động",session,jedis) %></option>
											  	<option value="0"><%=Utility.GLanguage("Ngưng hoạt động",session,jedis) %></option>
											  	<option value="2"> </option>
											  
											<%}else if(obj.getTrangthai().equals("0")) {%>
											 	 <option value="0" selected><%=Utility.GLanguage("Ngưng hoạt động",session,jedis) %></option>
											  	<option value="1" ><%=Utility.GLanguage("Hoạt động",session,jedis) %></option>
											 	 <option value="2" > </option>
											  
											<%}else{%>																						 	 
											  	<option value="1" ><%=Utility.GLanguage("Hoạt động",session,jedis) %></option>
											  	<option value="0" ><%=Utility.GLanguage("Ngưng hoạt động",session,jedis) %></option>
											  	<option value="2" selected> </option>
											<%}%>
									          </select></TD>
										</TR>
										<TR>
										    <TD class="plainlabel" colspan=4>
                                              <a class="button2" href="javascript:clearform()">
	<img style="top: -4px;" src="../images/button.png" alt=""><%=Utility.GLanguage("Nhập lại",session,jedis) %></a>&nbsp;&nbsp;&nbsp;&nbsp;	
	    	<a class="button2" href="javascript:xuatExcel()"> <img style="top: -4px;" src="../images/button.png" alt=""><%=Utility.GLanguage("Xuất Excel",session,jedis) %> </a>&nbsp;&nbsp;&nbsp;&nbsp;
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
			<TABLE width="100%" cellpadding="0" cellspacing="1">
			    <TR>
					<TD align="left" >
						 <FIELDSET>
							<LEGEND class="legendtitle">&nbsp;<%=Utility.GLanguage("Vị trí cửa hàng",session,jedis) %> &nbsp;&nbsp;&nbsp;
								<%if(quyen[Utility.THEM]!=0) {%>
								<a class="button3" href="javascript:newform()">
    	<img style="top: -4px;" src="../images/New.png" alt=""><%=Utility.GLanguage("Tạo mới",session,jedis) %> </a>                            
							<%} %>
							
						</LEGEND>
				
							<TABLE class="" width="100%" border="0" cellpadding="0" cellspacing="0">
								<TR>
									<TD width="98%">
										<TABLE width="100%" border="0" cellspacing="1" cellpadding="4">
											<TR class="tbheader">
												<TH width="4%"><%=Utility.GLanguage("STT",session,jedis) %></TH>
												<TH width="12%"><%=Utility.GLanguage("Vị trí cửa hàng",session,jedis) %> </TH>
											    <TH width="22%"><%=Utility.GLanguage("Diễn giải",session,jedis) %> </TH>
											    <TH width="11%"><%=Utility.GLanguage("Trạng thái",session,jedis) %> </TH>
											    <TH width="9%"><%=Utility.GLanguage("Ngày tạo",session,jedis) %></TH>
											  <TH width="13%"><%=Utility.GLanguage("Người tạo",session,jedis) %> </TH>										
											  <TH width="9%"><%=Utility.GLanguage("Ngày sửa",session,jedis) %></TH>
											  <TH width="14%"><%=Utility.GLanguage("Người sửa",session,jedis) %></TH>
											  <TH width="10%"><%=Utility.GLanguage("Tác vụ",session,jedis) %></TH>
										  </TR>
								<% 
									IVitricuahang vtch = null;
									int size = vtchlist.size();
									int m = 0;
									String lightrow = "tblightrow";
									String darkrow = "tbdarkrow";
									while (m < size){
										vtch = vtchlist.get(m);
										if (m % 2 != 0) {%>						
											<TR class= <%=lightrow%> >
										<%} else {%>
											<TR class= <%= darkrow%> >
										<%}%>
												<TD align="center"><%=m+1 %></TD>
												<TD align="left"><div align="left"><%= vtch.getVitricuahang() %></div></TD>                                   
												<TD><div align="left"><%= vtch.getDiengiai() %></div></TD>
											  <% if (vtch.getTrangthai().equals("1")){ %>
													<TD align="left"><%=Utility.GLanguage("Hoạt động",session,jedis) %></TD>
												<%}else{%>
													<TD align="left"><%=Utility.GLanguage("Ngưng hoạt động",session,jedis) %></TD>
												<%}%>
												<TD align="center"><%=vtch.getNgaytao()%></TD>
												<TD align="center"><%=vtch.getNguoitao()%></TD>
												<TD align="center"><%=vtch.getNgaysua()%></TD>
												<TD align="center"><%=vtch.getNguoisua()%></TD>
												<TD align="center">
												<TABLE border = 0 cellpadding="0" cellspacing="0">
													<TR><TD>
													<%if(quyen[Utility.SUA]!=0) {%>
													  <A href = "../../RouterSvl?task=<%= Util.dongMa(getServletContext().getInitParameter("RedirectNoScript") +"VitricuahangUpdateSvl?userId="+userId+"&update="+ vtch.getId()+"")%>">
                                               <img src="../images/Edit20.png" alt="Cap nhat" width="20" height="20" longdesc="Cap nhat" border = 0></A>
													<%} %>
													</TD>
													<TD>&nbsp;</TD>
													<TD>
													<%if(quyen[Utility.XOA]!=0) {%>
													  <A href = "../../RouterSvl?task=<%= Util.dongMa(getServletContext().getInitParameter("RedirectNoScript") +"VitricuahangSvl?userId="+userId+"&delete="+vtch.getId()+"")%>">
                                                <img src="../images/Delete20.png" alt="Xoa" width="20" height="20" longdesc="Xoa" border=0 onclick="if(!confirm('Bạn có muốn xóa vị trí cửa hàng này?')) return false;"></A>
                                                	<%} %>
                                                </TD>
													</TR></TABLE>
												</TD>
											</TR>
								<%m++; }%>
								
									<TR>	
									<TD height="" colspan="11" align="center" class="tbfooter">
									&nbsp;</TD>
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
	obj.DBClose();
}%>