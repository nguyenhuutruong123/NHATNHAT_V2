<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page  import = "geso.dms.center.beans.asm.IAsm"  %>
<%@ page  import = "geso.dms.center.beans.asm.imp.*" %>
<%@ page  import = "java.util.Hashtable"%>
<%@ page  import = "java.sql.ResultSet "%>
<%@ page  import = "geso.dms.center.util.*" %>
<%	String userId = (String) session.getAttribute("userId");  
	String userTen = (String) session.getAttribute("userTen");  	
	String sum = (String) session.getAttribute("sum");
	Utility util = (Utility) session.getAttribute("util");
	if(!util.check(userId, userTen, sum)){
		response.sendRedirect(request.getContextPath() + "/redirect.jsp");
	}else{ %>

<% IAsm asmBean = (IAsm)session.getAttribute("asmBean"); %>
<% ResultSet kbh = asmBean.getKbh(); %>
<% ResultSet dvkd = asmBean.getDvkd(); %>
<% ResultSet kv = asmBean.getKv(); %>
<% ResultSet nccRs = asmBean.getNccRs(); 

   String url = util.getChuyenNguUrl("ASMSvl", "",session);
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"> <% Utility Utilback = new Utility(); %>
<HTML>
<HEAD>
<TITLE><%= getServletContext().getInitParameter("TITLENAME") %></TITLE>
<META http-equiv="Content-Type" content="text/html; charset=utf-8">

<META http-equiv="Content-Style-Type" content="text/css">

<LINK rel="stylesheet" href="../css/main.css" type="text/css">
<script type="text/javascript"	src="../scripts/jquery.min.1.7.js"></script>
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
            $(".button").hover(function(){
                $(".button img")
                .animate({top:"-10px"}, 200).animate({top:"-4px"}, 200) // first jump
                .animate({top:"-7px"}, 100).animate({top:"-4px"}, 100) // second jump
                .animate({top:"-6px"}, 100).animate({top:"-4px"}, 100); // the last jump
            });
        }); 
		$(document).ready(function(){
            $(".button1").hover(function(){
                $(".button1 img")
                .animate({top:"-10px"}, 200).animate({top:"-4px"}, 200) // first jump
                .animate({top:"-7px"}, 100).animate({top:"-4px"}, 100) // second jump
                .animate({top:"-6px"}, 100).animate({top:"-4px"}, 100); // the last jump
            });
        });
		
    </script>

<script type="text/javascript"
	src="../scripts/report-js/action-report.js"></script>
<SCRIPT language="javascript" type="text/javascript">
 function confirmLogout(){
    if(confirm("B???n c?? mu???n ????ng xu???t?"))
    {
		top.location.href = "home.jsp";
    }
    return
  }
 function submitform()
 {   
	 document.forms["asmForm"].submit();
 }

 function saveform()
 {
	 var ten = document.getElementById('asmTen');
	 var diachi = document.getElementById('DiaChi');
	 var email = document.getElementById('Email');
	 var dienthoai = document.getElementById('DienThoai');
	 var kbhId = document.getElementById('kbhId');
	 var dvkdId = document.getElementById('dvkdId');	
	 var asm_kv = document.getElementsByName("kvId");	
	 var manhanvien = document.getElementById("MaNhanVien");

	 
	 if(ten.value.trim() == "")
	 {
		 alert('Vui l??ng nh???p t??n c???a Tr?????ng Khu V???c');
		 return;
	 }

	/*  if(kbhId.value.trim() == "")
	 {
		 alert('Vui l??ng ch???n K??nh b??n h??ng');
		 return;
	 }	 
	  */
	 if(dvkdId.value.trim() == "")
	 {
		 alert('Vui l??ng ch???n ????n v??? kinh doanh');
		 return;
	 }	 

	 if(diachi.value.trim() == "")
	 {
		 alert('Vui l??ng nh???p ?????a ch???');
		 return;
	 }	

	 if(email.value.trim() == "")
	 {
		 alert('Vui l??ng nh???p ?????a ch??? Email');
		 return;
	 }	 
	 
	 if(dienthoai.value.trim() == "")
	 {
		 alert('Vui l??ng nh???p s??? ??i???n tho???i');
		 return;
	 }	 
	 if(maFAST.value.trim() == "")
	 {
		 alert('Vui l??ng nh???p m?? DMS');
		 return;
	 }

	 var flag = '';
	 for(var i in asm_kv)
	 {
		 if(asm_kv[i].checked)
			 flag = flag + asm_kv[i].value;
	 }
	 if(flag == '')
	 {
		 alert('Vui l??ng ch???n Khu V???c');
		 return;
	 }
	 
	 document.forms['asmForm'].action.value='save';
     document.forms['asmForm'].submit();
 }
</SCRIPT>
</HEAD>

<BODY leftmargin="0" bottommargin="0" topmargin="0" rightmargin="0">
<form name="asmForm" method="post" action="../../ASMUpdateSvl">
<% redis.clients.jedis.Jedis jedis =  geso.dms.center.util.Utility.getJedis(); %><% geso.dms.center.util.Csrf csdr=new geso.dms.center.util.Csrf(request,response,true,false,true);%>
 <input type="hidden" name="<%=csdr.get_tokenName() %>" value='<%=csdr.get_tokenValue() %>'>

<INPUT name="userId" type="hidden" value='<%= userId %>' size="30">
<INPUT name="Id" type="hidden" value='<%=asmBean.getId()%>' size="30">
<input type="hidden" name="action" value='1'>

<TABLE width="100%" border="0" cellspacing="0" cellpadding="0"  height="100%">
    <TR>
        <TD colspan="4" align='left' valign='top' bgcolor="#FFFFFF">
            <TABLE width="100%" border="0" cellpadding="0" cellspacing="1">
                <TR>
                    <TD align="left" class="tbnavigation">
                        <table width="100%" border="0" cellpadding="0" cellspacing="0">
                          <tr height="22">
							 <TD align="left" colspan="2" class="tbnavigation"><%= url %> > <%=Utility.GLanguage("C???p nh???t",session,jedis) %></TD> 
                             <TD colspan="2" align="right" class="tbnavigation"><%=Utility.GLanguage("Ch??o m???ng b???n",session,jedis) %>  <%=userTen %>&nbsp;  </TD></tr>

                        </table>
                    </TD>
                </TR>
            </TABLE>
            <TABLE width="100%" border="0" cellpadding="0" cellspacing="1">
                    <TR class = "tbdarkrow">
                        <TD width="30" align="center"><A href="../../RouterSvl?task=<%= Utilback.dongMa(getServletContext().getInitParameter("RedirectNoScript") +"ASMSvl?userId="+userId+"") %>" ><img src="../images/Back30.png" alt="Quay Ve"  title="Quay Ve" width="30" height="30" border="1" longdesc="Quay ve" style="border-style:outset"></A></TD>
                        <TD width="2" align="left" ></TD>
                        <TD width="30" align="left" ><A href="javascript:saveform()" ><IMG src="../images/Save30.png" title="Luu Lai" alt="Luu Lai" border = "1"  style="border-style:outset"></A></TD>

                        <TD align="left" >&nbsp;</TD>
                    </TR>
            </TABLE>
            <TABLE width="100%" border="0" cellpadding="0"  cellspacing="0">
                                <TR>
                                    <TD align="left" colspan="4" class="legendtitle">
                                        <FIELDSET>
                                        <LEGEND class="legendtitle"><%=Utility.GLanguage("Th??ng b??o",session,jedis) %></LEGEND>
                                        
                                        <textarea name="dataerror"  style="width: 100% ; color:#F00 ; font-weight:bold"  style="width: 100% ; color:#F00 ; font-weight:bold" readonly="readonly" rows="1" ><%= asmBean.getMsg() %></textarea>
                                        <%asmBean.setMsg(""); %>
                                        </FIELDSET>
                                   </TD>
                                </TR>
                
                <TR>
                  <TD height="100%" width="100%">
                        <FIELDSET>
                        <LEGEND class="legendtitle" style="color:black"><%=Utility.GLanguage("Th??ng tin Qu???n l?? khu v???c",session,jedis) %> </LEGEND>

                        <TABLE border="0" width="100%" cellpadding="6" cellspacing="0">
                            <TR>
                                <TD width="150px" class="plainlabel" ><%=Utility.GLanguage("T??n Qu???n l?? khu v???c",session,jedis) %> <FONT class="erroralert"> *</FONT></TD>
                                <TD width="250px" class="plainlabel">
                                	<INPUT name="asmTen" id="asmTen" type="text" value="<%= asmBean.getTen() %>" style="width:200px"></TD>
                                
							    <TD width="120px" class="plainlabel"><%=Utility.GLanguage("Nh?? cung c???p",session,jedis) %><FONT class="erroralert"> *</FONT></TD>
							    <TD  class="plainlabel">
								    <SELECT name="nccId" id = "nccId" >
									    <% try{ while(nccRs.next()){ 
										   	if(nccRs.getString("pk_seq").equals(asmBean.getNccId()  )){ %>
									      		<option value='<%=nccRs.getString("pk_seq") %>' selected='selected'><%=nccRs.getString("ten") %></option>
									      	<%}else{ %>
									     		<option value='<%=nccRs.getString("pk_seq") %>' ><%=nccRs.getString("ten") %></option>
									     	<%}}}catch(java.sql.SQLException e){} %>
									     	
									 </SELECT>
								</TD>                             							  
                          	</TR>
                            
                            
                            <TR>
							    
							    <TD class="plainlabel"><%=Utility.GLanguage("????n v??? kinh doanh",session,jedis) %><FONT class="erroralert"> *</FONT></TD>
							    <TD  class="plainlabel" >
								    <SELECT name="dvkdId" id = "dvkdId" >
									   <option value=""></option> 
									    <% try{ while(dvkd.next()){ 
										   	if(dvkd.getString("dvkdId").equals(asmBean.getDvkdId())){ %>
									      		<option value='<%=dvkd.getString("dvkdId") %>' selected='selected'><%=dvkd.getString("dvkd") %></option>
									      	<%}else{ %>
									     		<option value='<%=dvkd.getString("dvkdId") %>' ><%=dvkd.getString("dvkd") %></option>
									     	<%}}}catch(java.sql.SQLException e){} %>
									     	
									 </SELECT>
								</TD>
								
								<TD  class="plainlabel" > <%=Utility.GLanguage("M?? DMS",session,jedis) %> <FONT class="erroralert"> *</FONT></TD>
								<TD  class="plainlabel" > 
									<INPUT type="text" name="maFAST" id="maFAST" value="<%= asmBean.getMaFAST() %>" >
								</TD>
								
								
                          	</tr>
                            <TR>
                                <TD class="plainlabel" > <%=Utility.GLanguage("?????a ch???",session,jedis) %> <FONT class="erroralert"> *</FONT></TD>
                                <TD class="plainlabel">
                                	<INPUT name="DiaChi" id="DiaChi" type="text" value="<%= asmBean.getDiachi() %>" >
                                </TD>
                                
                                <TD class="plainlabel" > <%=Utility.GLanguage("S??? n??m l??m vi???c",session,jedis) %></TD>
								<TD class="plainlabel"><INPUT type="text" name="sonamlamviec"  value="<%=asmBean.getSonamlamviec()%>"></TD>
                          	</TR>
                            <TR>
                                <TD class="plainlabel" > <%=Utility.GLanguage("Email",session,jedis) %> <FONT class="erroralert"> *</FONT></TD>
                                <TD class="plainlabel">
                                	<INPUT name="Email" id="Email" type="text" value="<%= asmBean.getEmail() %>" >
                                </TD> 
                                
                                <TD class="plainlabel" > <%=Utility.GLanguage("S??? ??T c??ng ty",session,jedis) %></TD>
							  	<TD class="plainlabel"><INPUT type="text" name="sodtct"  value="<%=asmBean.getSoDTcongty()%>"></TD>                            
                          	</TR>
                          	
                          	<tr>
                          		<TD class="plainlabel"><%=Utility.GLanguage("??i???n tho???i",session,jedis) %> <FONT class="erroralert"> *</FONT></TD>
                              	<TD class="plainlabel"><input name="DienThoai" id="DienThoai" type="text" value="<%= asmBean.getDienthoai() %>"></TD>
                              	
                          		<TD class="plainlabel"><%=Utility.GLanguage("Ghi ch??",session,jedis) %> </TD>
						    	<TD class="plainlabel"><INPUT type="text" name="ghichu"  value="<%=asmBean.getGhichu()%>"></TD>
						    
								
							</tr>
                            
                            <TR>
                                <TD class="plainlabel" > <%=Utility.GLanguage("Ng??n h??ng",session,jedis) %></TD>
                                <TD class="plainlabel">
                                	<INPUT name="NganHang" id="NganHang" type="text" value="<%= asmBean.getNganHang() %>" >
                                </TD>
								
								<TD class="plainlabel"><%=Utility.GLanguage("S??? TK",session,jedis) %> </TD>
						       	<TD class="plainlabel"><INPUT type="text" name="sotk"  value="<%=asmBean.getSotk()%>"></TD>								
							</tr>
							<tr>	
								
                          		<TD class="plainlabel"><div align="left"><%=Utility.GLanguage("Ho???t ?????ng",session,jedis) %><FONT class="erroralert"> </FONT></div></TD>
                              	<%if(asmBean.getTrangthai().equals("1")){ %>
                              	<TD colspan = "3" class="plainlabel">                         
                                    <input name="TrangThai" id="TrangThai"  type="checkbox" value ="1"  checked>                                                                     
                              	</TD>
                              	<%} else{ %>
                              	<TD colspan = "3" class="plainlabel">                         
                                    <input name="TrangThai" id="TrangThai"  type="checkbox" value ="1" >                                                                     
                              	</TD>
                              	<%} %>
                              	
                              	
                          	</tr>
                          	<TR>
                          		<TD class="plainlabel"><%=Utility.GLanguage("K??nh b??n h??ng",session,jedis) %><FONT class="erroralert"> *</FONT></TD>
							    <TD  class="plainlabel">
								    <SELECT name="kbhId" id = "kbhId" onchange="submitform();" >
									   <option value=""></option> 
									    <% try{ while(kbh.next()){ 
										   	if(kbh.getString("kbhId").equals(asmBean.getKbhId())){ %>
									      		<option value='<%=kbh.getString("kbhId") %>' selected='selected'><%=kbh.getString("kbh") %></option>
									      	<%}else{ %>
									     		<option value='<%=kbh.getString("kbhId") %>' ><%=kbh.getString("kbh") %></option>
									     	<%}}}catch(java.sql.SQLException e){} %>
									     	
									 </SELECT>
								</TD>
								<TD class="plainlabel"><%=Utility.GLanguage("RSM",session,jedis) %><FONT class="erroralert">*</FONT></TD>

											<TD class="plainlabel">
												<select name="bmId" onchange="submitform();">
													<option value=""></option>
													<%
														try {
																ResultSet bmRs = asmBean.getBmRs();
																if(bmRs != null)
																while (bmRs.next()) {
																	if (bmRs.getString("pk_seq").equals(asmBean.getBmId())) {
													%>
													<option value='<%=bmRs.getString("pk_seq")%>' selected><%=bmRs.getString("ten")%></option>
													<%
														} else {
													%>
													<option value='<%=bmRs.getString("pk_seq")%>'><%=bmRs.getString("ten")%></option>
													<%
														}
																}
															} catch (java.sql.SQLException e) {
																e.printStackTrace();
															}
													%>
													</select>
											</TD>
                          	</TR>
                    </TABLE>   	
                    </FIELDSET>
                    <TABLE border="0" width="100%" cellpadding="0" cellspacing="0">
                          <TR>
                            <TD colspan="4">
                        <FIELDSET>
                        <LEGEND class="legendtitle" style="color:black"><%=Utility.GLanguage("Ch???n Mi???n",session,jedis) %><FONT class="erroralert"> *</FONT></LEGEND>

                        <TABLE border="0" width="100%" cellpadding="3" cellspacing="1">
                            <TR class="tbheader">
                                <TH width="40%"><%=Utility.GLanguage("Khu v???c",session,jedis) %></TH>     
                                  <TH width="20%"><%=Utility.GLanguage("Ng??y b???t ?????u",session,jedis) %> </TH>
                                <TH width="20%"><%=Utility.GLanguage("Ng??y k???t th??c",session,jedis) %> </TH>                   
                                <TH width="20%"><%=Utility.GLanguage("Ch???n",session,jedis) %> </TH>
                            </TR>
                     		
                     		<%
								int i = 0;
								String lightrow = "tblightrow";
								String darkrow = "tbdarkrow";
								try{
									Hashtable<String, String> kvIds = asmBean.getHTKvId();
									if(kv!=null)
									while(kv.next()){ 
										if (i % 2 != 0) {%>						
										<TR class= <%=lightrow%> >
										<%} else {%>
										<TR class= <%= darkrow%> >
										<%}%>
											<TD align="center"><div align="left"><%= kv.getString("KV") %> </div></TD>
											<% if (kvIds.containsKey(kv.getString("KVID"))){ %>
												<TD align="center"><input class="days" name="ngaybatdau<%=kv.getString("KVID")%>" value='<%=kv.getString("ngaybatdau")%>'>  </TD>
											<TD align="center"><input class="days"  name="ngayketthuc<%=kv.getString("KVID")%>" value='<%=kv.getString("ngayketthuc")%>'> </TD>
												
												<TD align="center"><input name="kvId" id="kvId" type="checkbox" value ="<%= kv.getString("KVID") %>" checked></TD>
											<%}else{ %>
											<TD align="center"><input class="days" name="ngaybatdau<%=kv.getString("KVID")%>" value='<%=kv.getString("ngaybatdau")%>'>  </TD>
											<TD align="center"><input class="days"  name="ngayketthuc<%=kv.getString("KVID")%>" value='<%=kv.getString("ngayketthuc")%>'> </TD>
											
												<TD align="center"><input name="kvId" id="kvId" type="checkbox" value ="<%= kv.getString("KVID") %>" ></TD>
											<%} %>
										</TR>
							     
							     <%i++;}}catch(java.sql.SQLException e){}
							  %>
                     		
                        </TABLE>                        
                        </FIELDSET>

                            </TD></TR>
                        </TABLE>
                        

                        
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
	if(kbh != null) kbh.close();
	if(dvkd != null) dvkd.close();
	if(kv != null) kv.close();

	asmBean.DBClose() ;%>	
<%}%>