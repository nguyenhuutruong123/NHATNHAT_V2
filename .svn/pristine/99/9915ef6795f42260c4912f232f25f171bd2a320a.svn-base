<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page  import = "geso.dms.center.beans.bm.IBm"  %>
<%@ page  import = "geso.dms.center.beans.bm.imp.*" %>
<%@ page  import = "java.util.Hashtable"%>
<%@ page  import = "java.sql.ResultSet "%>
<%@ page  import = "geso.dms.center.util.*" %>
<%	String userId = (String) session.getAttribute("userId");  
	String userTen = (String) session.getAttribute("userTen");  	
	String sum = (String) session.getAttribute("sum");
	Utility util = (Utility) session.getAttribute("util");
	if(!util.check(userId, userTen, sum)){
		response.sendRedirect(request.getContextPath() + "/redirect.jsp");
	}
	else{ %>

<% IBm bmBean = (IBm)session.getAttribute("bmBean"); %>
<% ResultSet kbh = bmBean.getKbh(); %>
<% ResultSet dvkd = bmBean.getDvkd(); %>
<% ResultSet vung = bmBean.getVung(); 

String url = util.getChuyenNguUrl("BmSvl", "",session);
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"> <% Utility Utilback = new Utility(); %>
<HTML>
<HEAD>
<TITLE><%= getServletContext().getInitParameter("TITLENAME") %></TITLE>
<META http-equiv="Content-Type" content="text/html; charset=utf-8">

<META http-equiv="Content-Style-Type" content="text/css">
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
<LINK rel="stylesheet" href="../css/main.css" type="text/css">

<SCRIPT language="javascript" type="text/javascript">
 function confirmLogout(){
    if(confirm("Bạn có muốn đăng xuất?"))
    {
		top.location.href = "home.jsp";
    }
    return
  }
 function submitform()
 {   
    document.forms["bmForm"].submit();
 }

 function saveform()
 {
	 var ten = document.getElementById('bmTen');
	 var diachi = document.getElementById('DiaChi');
	 var email = document.getElementById('Email');
	 var dienthoai = document.getElementById('DienThoai');
	 var kbhId = document.getElementById('kbhId');
	 var dvkdId = document.getElementById('dvkdId');
	 var maFAST = document.getElementById('maFAST');
	 var bm_vung = document.getElementsByName("vungId");

	 if(ten.value.trim() == "")
	 {
		 alert('Vui lòng nhập tên của Trưởng Miền');
		 return;
	 }
 
	 
	 if(dvkdId.value.trim() == "")
	 {
		 alert('Vui lòng chọn Đơn vị kinh doanh');
		 return;
	 }	 

	 if(diachi.value.trim() == "")
	 {
		 alert('Vui lòng nhập địa chỉ');
		 return;
	 }	

	 if(email.value.trim() == "")
	 {
		 alert('Vui lòng nhập địa chỉ Email');
		 return;
	 }	 
	 
	 if(dienthoai.value.trim() == "")
	 {
		 alert('Vui lòng nhập số điện thoại');
		 return;
	 }	 
	 
	 if(maFAST.value.trim() == "")
	 {
		 alert('Vui lòng nhập mã DMS');
		 return;
	 }
		 

	 var flag = '';
	 for(var i in bm_vung)
	 {
		 if(bm_vung[i].checked)
			 flag = flag + bm_vung[i].value;
	 }
	 if(flag == '')
	 {
		 alert('Vui lòng chọn Miền');
		 return;
	 }
	 
	 document.forms['bmForm'].action.value='save';
     document.forms['bmForm'].submit();
 }
</SCRIPT>
</HEAD>

<BODY leftmargin="0" bottommargin="0" topmargin="0" rightmargin="0">
<form name="bmForm" method="post" action="../../BmUpdateSvl">
<% redis.clients.jedis.Jedis jedis =  geso.dms.center.util.Utility.getJedis(); %><% geso.dms.center.util.Csrf csdr=new geso.dms.center.util.Csrf(request,response,true,false,true);%>
 <input type="hidden" name="<%=csdr.get_tokenName() %>" value='<%=csdr.get_tokenValue() %>'>

<INPUT name="userId" type="hidden" value='<%= userId %>' size="30">
<input type="hidden" name="action" value='1'>

<TABLE width="100%" border="0" cellspacing="0" cellpadding="0"  height="100%">
    <TR>
        <TD colspan="4" align='left' valign='top' bgcolor="#FFFFFF">
            <TABLE width="100%" border="0" cellpadding="0" cellspacing="1">
                <TR>
                    <TD align="left" class="tbnavigation">
                        <table width="100%" border="0" cellpadding="0" cellspacing="0">
                          <tr height="22">
							 <TD align="left" colspan="2" class="tbnavigation"> <%= url %> > <%=Utility.GLanguage("Tạo mới",session,jedis) %></TD> 
                             <TD colspan="2" align="right" class="tbnavigation"><%=Utility.GLanguage("Chào mừng bạn",session,jedis) %>  <%=userTen %>&nbsp;  </TD></tr>

                        </table>
                    </TD>
                </TR>
            </TABLE>
            <TABLE width="100%" border="0" cellpadding="0" cellspacing="1">
                    <TR class = "tbdarkrow">
                        <TD width="30" align="center"><A href="../../RouterSvl?task=<%= Utilback.dongMa(getServletContext().getInitParameter("RedirectNoScript") +"BmSvl?userId="+userId+"") %>" ><img src="../images/Back30.png" alt="Quay VeÂ"  title="Quay Ve" width="30" height="30" border="1" longdesc="Quay veÂ" style="border-style:outset"></A></TD>
                        <TD width="2" align="left" ></TD>
                        <TD width="30" align="left" ><A href="javascript:saveform()" ><IMG src="../images/Save30.png" title="Luu Lai" alt="Luu Lai" border = "1"  style="border-style:outset"></A></TD>

                        <TD align="left" >&nbsp;</TD>
                    </TR>
            </TABLE>
            <TABLE width="100%" border="0" cellpadding="0"  cellspacing="0">
                                <TR>
                                    <TD align="left" colspan="4" class="legendtitle">
                                        <FIELDSET>
                                        <LEGEND class="legendtitle"><%=Utility.GLanguage("Thông báo",session,jedis) %></LEGEND>
                                        
                                        <textarea name="dataerror"  style="width: 100% ; color:#F00 ; font-weight:bold"  style="width: 100% ; color:#F00 ; font-weight:bold" readonly="readonly" rows="1" ><%= bmBean.getMsg() %></textarea>
                                        <%bmBean.setMsg(""); %>
                                        </FIELDSET>
                                   </TD>
                                </TR>
                
                <TR>
                  <TD height="100%" width="100%">
                        <FIELDSET>
                        <LEGEND class="legendtitle" style="color:black"><%=Utility.GLanguage("Thông tin Giám đốc Miền",session,jedis) %> </LEGEND>

                        <TABLE border="0" width="100%" cellpadding="6" cellspacing="0">
                            <TR>
                                <TD width="150px" class="plainlabel" ><%=Utility.GLanguage("Tên Giám đốc Miền",session,jedis) %> <FONT class="erroralert"> *</FONT></TD>
                                <TD width="250px" class="plainlabel"><INPUT name="bmTen" id="bmTen" type="text" value="<%= bmBean.getTen() %>" ></TD>                                
								
                          		<TD width="150px" class="plainlabel"><%=Utility.GLanguage("Đơn vị kinh doanh",session,jedis) %><FONT class="erroralert"> *</FONT></TD>
							    <TD class="plainlabel">
								    <SELECT name="dvkdId" id = "dvkdId" >
									    <% try{ while(dvkd.next()){ 
										   	if(dvkd.getString("dvkdId").equals(bmBean.getDvkdId())){ %>
									      		<option value='<%=dvkd.getString("dvkdId") %>' selected='selected'><%=dvkd.getString("dvkd") %></option>
									      	<%}else{ %>
									     		<option value='<%=dvkd.getString("dvkdId") %>' ><%=dvkd.getString("dvkd") %></option>
									     	<%}}}catch(java.sql.SQLException e){} %>
									     <option value=""></option> 	
									 </SELECT>
								</TD>
								
								
                          	</tr>
                          	
                            <TR>
                                <TD class="plainlabel" > <%=Utility.GLanguage("Địa chỉ",session,jedis) %> <FONT class="erroralert"> *</FONT></TD>
                                <TD class="plainlabel">
                                	<INPUT name="DiaChi" id="DiaChi" type="text" value="<%= bmBean.getDiachi() %>" >
                                </TD>
                                <TD class="plainlabel"><%=Utility.GLanguage("Điện thoại",session,jedis) %> <FONT class="erroralert"> *</FONT></TD>
                              	<TD class="plainlabel"><input name="DienThoai" id="DienThoai" type="text" value="<%= bmBean.getDienthoai() %>"></TD>                         	
                                
                          	</TR>
                            <TR>
                                <TD class="plainlabel" > <%=Utility.GLanguage("Email",session,jedis) %> <FONT class="erroralert"> *</FONT></TD>
                                <TD class="plainlabel">
                                	<INPUT name="Email" id="Email" type="text" value="<%= bmBean.getEmail() %>" >
                                </TD>   
                                
                              	<TD class="plainlabel" > <%=Utility.GLanguage("Số ĐT công ty",session,jedis) %></TD>
							  	<TD class="plainlabel"><INPUT type="text" name="sodtct"  value="<%=bmBean.getSoDTcongty()%>"></TD>                          
                          	</TR>
                          	

                          	<tr>
                          		<TD  class="plainlabel"><%=Utility.GLanguage("Ghi chú",session,jedis) %> </TD>
						    	<TD class="plainlabel"><INPUT type="text" name="ghichu"  value="<%=bmBean.getGhichu()%>"></TD>
						    
								<TD  class="plainlabel"><%=Utility.GLanguage("Số TK",session,jedis) %> </TD>
						       <TD class="plainlabel"><INPUT type="text" name="sotk"  value="<%=bmBean.getSotk()%>"></TD>								
								
								
							</tr>
                            
                            <TR>
                                <TD class="plainlabel" > <%=Utility.GLanguage("Ngân hàng",session,jedis) %></TD>
                                <TD class="plainlabel">
                                	<INPUT name="NganHang" id="NganHang" type="text" value="<%= bmBean.getNganHang() %>" >
                                </TD>

	                          	<TD class="plainlabel"><div align="left"><%=Utility.GLanguage("Hoạt động",session,jedis) %><FONT class="erroralert"> *</FONT></div></TD>
	                            <TD class="plainlabel">                         
	                            	<input name="TrangThai" type="checkbox" value ="1" checked readonly>                                                                     
	                            </TD>
                            </tr>
                            
                            <tr>
                            	<TD  class="plainlabel" > <%=Utility.GLanguage("Mã DMS",session,jedis) %><FONT class="erroralert"> *</FONT> </TD>
								<TD  class="plainlabel"  > 
									<INPUT type="text" name="maFAST" id="maFAST"  value="<%= bmBean.getMaFAST() %>" >
								</TD>   
								<TD class="plainlabel"><%=Utility.GLanguage("Kênh bán hàng",session,jedis) %><FONT class="erroralert"> *</FONT></TD>
							    <TD  class="plainlabel" >
								    <SELECT name="kbhId" id = "kbhId" onchange="submitform();" >
									   <option value=""></option> 
									    <% try{ while(kbh.next()){ 
										   	if(kbh.getString("kbhId").equals(bmBean.getKbhId())){ %>
									      		<option value='<%=kbh.getString("kbhId") %>' selected='selected'><%=kbh.getString("kbh") %></option>
									      	<%}else{ %>
									     		<option value='<%=kbh.getString("kbhId") %>' ><%=kbh.getString("kbh") %></option>
									     	<%}}}catch(java.sql.SQLException e){} %>
									     	
									 </SELECT>
								</TD>                         
                            </tr>
                    </TABLE>	
                    </FIELDSET>
                    <TABLE border="0" width="100%" cellpadding="0" cellspacing="0">
                          <TR>
                            <TD colspan="4">
                        <FIELDSET>
                        <LEGEND class="legendtitle" style="color:black">Chọn Miền<%=Utility.GLanguage("",session,jedis) %><FONT class="erroralert"> *</FONT></LEGEND>

                        <TABLE border="0" width="100%" cellpadding="3" cellspacing="1">
                            <TR class="tbheader">
                                <TH width="80%"><%=Utility.GLanguage("Miền",session,jedis) %> </TH>   
                                 <TH width="20%"><%=Utility.GLanguage("Ngày bắt đầu",session,jedis) %> </TH>
                                <TH width="20%"><%=Utility.GLanguage("Ngày kết thúc",session,jedis) %> </TH>                     
                                <TH width="20%"><%=Utility.GLanguage("Chọn",session,jedis) %> </TH>
                            </TR>
                     		
                     		<%
								int i = 0;
								String lightrow = "tblightrow";
								String darkrow = "tbdarkrow";
								try{
									Hashtable<String, String> vungIds = bmBean.getHTVungId();
									if(vung!=null)
									while(vung.next()){ 
										if (i % 2 != 0) {%>						
										<TR class= <%=lightrow%> >
										<%} else {%>
										<TR class= <%= darkrow%> >
										<%}%>
											<TD align="center"><div align="left"><%= vung.getString("VUNG") %> </div></TD>
											<% if (vungIds.containsKey(vung.getString("VUNGID"))){ %>
												<TD align="center"><input class="days" name="ngaybatdau<%=vung.getString("VUNGID")%>" value='<%=vung.getString("ngaybatdau")%>'>  </TD>
											<TD align="center"><input class="days"  name="ngayketthuc<%=vung.getString("VUNGID")%>" value='<%=vung.getString("ngayketthuc")%>'> </TD>
											
												<TD align="center"><input name="vungId" id="vungId" type="checkbox" value ="<%= vung.getString("VUNGID") %>" checked></TD>
											<%}else{ %>
											<TD align="center"><input class="days" name="ngaybatdau<%=vung.getString("VUNGID")%>" value='<%=vung.getString("ngaybatdau")%>'>  </TD>
											<TD align="center"><input class="days"  name="ngayketthuc<%=vung.getString("VUNGID")%>" value='<%=vung.getString("ngayketthuc")%>'> </TD>
											
												<TD align="center"><input name="vungId" id="vungId" type="checkbox" value ="<%= vung.getString("VUNGID") %>" ></TD>
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
	if(vung != null) vung.close();

	bmBean.DBClose() ;%>	
<%}%>