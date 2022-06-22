<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page  import = "geso.dms.center.beans.asm.IAsm" %>
<%@ page  import = "geso.dms.center.beans.asm.IAsmList" %>
<%@ page  import = "java.util.List" %>
<%@ page  import = "java.sql.ResultSet" %>
<%@ page  import = "geso.dms.center.util.*" %>
<%	String userId = (String) session.getAttribute("userId");  
	String userTen = (String) session.getAttribute("userTen");  	
	String sum = (String) session.getAttribute("sum");
	Utility util = (Utility) session.getAttribute("util");
	if(!util.check(userId, userTen, sum)){
		response.sendRedirect(request.getContextPath() + "/redirect.jsp");
	}else{ %>

<% IAsmList obj =(IAsmList)session.getAttribute("obj"); %>
<% ResultSet dvkd = (ResultSet) obj.getDvkd();  %>
<% ResultSet kv = (ResultSet) obj.getKv(); %>
<% ResultSet asmList = (ResultSet) obj.getAsmlist();
	int[] quyen = new  int[6];
	quyen = util.Getquyen("ASMSvl","",userId);

	String url = util.getChuyenNguUrl("ASMSvl", "",session);
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
<script type="text/javascript" src="../scripts/phanTrang.js"></script>
<SCRIPT language="javascript" type="text/javascript">

function clearform()
{
	document.asmForm.asmTen.value = "";
    document.asmForm.DienThoai.value = "";  
    //document.asmForm.kbhId.selectedIndex = 0;
    document.asmForm.dvkdId.selectedIndex = 0;
    document.asmForm.kvId.selectedIndex = 0;
    document.asmForm.TrangThai.selectedIndex = 2;
    document.asmForm.maFAST.value = "";
    submitform();    
}

function submitform()
{
	document.forms['asmForm'].action.value= 'search';
	document.forms['asmForm'].submit();
}
function xuatexcel()
{
	document.forms['asmForm'].action.value= 'excel';
	document.forms['asmForm'].submit();
}
function newform()
{
	document.forms['asmForm'].action.value= 'new';
	document.forms['asmForm'].submit();
}
function thongbao(){
	var tt = document.forms['asmForm'].msg.value;
	if(tt.length>0)
    alert(document.forms['asmForm'].msg.value);
}

</SCRIPT>

</HEAD>
<BODY leftmargin="0" bottommargin="0" topmargin="0" rightmargin="0">
<form name="asmForm" method="post" action="../../ASMSvl">

<% redis.clients.jedis.Jedis jedis =  geso.dms.center.util.Utility.getJedis(); %><% geso.dms.center.util.Csrf csdr=new geso.dms.center.util.Csrf(request,response,true,false,true);%>
 <input type="hidden" name="<%=csdr.get_tokenName() %>" value='<%=csdr.get_tokenValue() %>'>

<input type="hidden" name="userId" value='<%= userId %>'>
<input type="hidden" name="userTen" value='<%= userTen %>'>
<input type="hidden" name="action" value='1'>


<input type="hidden" name="msg" value='<%=obj.getMsg() %>'>

<script language="javascript" type="text/javascript">
    thongbao();
</script> 

<TABLE width="100%" border="0" cellspacing="0" cellpadding="0"
	height="100%">
	
	<TR>
		<TD colspan="4" align='left' valign='top' bgcolor="#FFFFFF">
		<TABLE width="100%" cellpadding="0" cellspacing="1">
			
				<TR>
				  <TD align="left"><table width="100%" border="0" cellpadding="0" cellspacing="0">

						  <tr height="20">
						   <TD align="left" colspan="2" class="tbnavigation"><%= url %></TD>
   
						   <TD colspan="2" align="right" class="tbnavigation"><%=Utility.GLanguage("Chào mừng bạn",session,jedis) %>  <%=userTen %>&nbsp;  </TD>
						  </tr>
 					  </table>
					</TD>
				</TR>

		</TABLE>
		<TABLE width="100%" cellpadding="0" cellspacing="0">		
				<TR>
					<TD>
					<TABLE border="0" width="100%" cellpadding="0" cellspacing="0">
						<TR>
							<TD width="100%" align="center" >
							<FIELDSET>
							  <LEGEND class="legendtitle"><%=Utility.GLanguage("Tiêu chí tìm kiếm",session,jedis) %> &nbsp;</LEGEND>
							<TABLE  width="100%" cellpadding="6" cellspacing="0">
							      <TR>  
							      		<TD width="24%" class="plainlabel"><%=Utility.GLanguage("Tên Quản lý khu vực",session,jedis) %> </TD>
								        <TD class="plainlabel"><input type="text" name="asmTen" id="asmTen" value="<%= obj.getTen() %>" onChange="submitform();"></TD>
								    	<TD class="plainlabel"><%=Utility.GLanguage("Số điện thoại (ĐT)",session,jedis) %> </TD>
								    	<TD class="plainlabel"><input type="text" name="DienThoai" id="DienThoai" value="<%= obj.getDienthoai() %>" onChange="submitform();"></TD>
							      </TR>
							      
							      
								<TR>
									<TD width="17%" class="plainlabel"><%=Utility.GLanguage("Mã DMS",session,jedis) %></TD>
								    <TD width="29%" class="plainlabel">
											<INPUT name="maFAST" type="text" value="<%= obj.getMaFAST() %>" size="40" onChange = "submitform();">
								  </TD>
								  <TD class="plainlabel"><%=Utility.GLanguage("RSM",session,jedis) %></TD>
											<TD class="plainlabel">
												<select name="bmId"  onChange = "submitform();">
													<option value=""></option>
													<%
														try {
																ResultSet bmRs = obj.getBMRS();
																if(bmRs != null)
																while (bmRs.next()) {
																	if (bmRs.getString("pk_seq").equals(obj.getBmId())) {
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

								  <TR>
								    <TD class="plainlabel"><%=Utility.GLanguage("Đơn vị Kinh doanh(ĐVKD)",session,jedis) %> </TD>
								    <TD width="20%" class="plainlabel" colspan="3">
								    <SELECT name="dvkdId" onChange = "submitform();">
									    <option value=""></option> 
									      <% try{ while(dvkd.next()){ 
									    	if(dvkd.getString("dvkdId").equals(obj.getDvkdId())){ %>
									      		<option value='<%=dvkd.getString("dvkdId") %>' selected='selected'><%=dvkd.getString("dvkd") %></option>
									      	<%}else{ %>
									     		<option value='<%=dvkd.getString("dvkdId") %>' ><%=dvkd.getString("dvkd") %></option>
									     	<%}}}catch(java.sql.SQLException e){} %>
									     	
									    </SELECT> 
									</TD>
								<TR>
						            <TD width="18%" class="plainlabel"><%=Utility.GLanguage("Khu vực",session,jedis) %></TD>
									<TD width="33%" class="plainlabel">
									<SELECT name="kvId"  onChange = "submitform()">
									<option value=""> </option>
									      <% try{ while(kv.next()){ 
									    	if(kv.getString("kvId").equals(obj.getKvId())){ %>
									      		<option value='<%= kv.getString("kvId") %>' selected='selected'><%= kv.getString("kv") %></option>
									      	<%}else{ %>
									     		<option value='<%= kv.getString("kvId") %>' ><%= kv.getString("kv") %></option>
									     	<%}}}catch(java.sql.SQLException e){} %>
                                	</SELECT>
									</TD>
						            
								    <TD class="plainlabel"><%=Utility.GLanguage("Trạng thái",session,jedis) %> </TD>
								    <TD  class="plainlabel"><SELECT name="TrangThai" onChange = "submitform();">
                                      
                                      <% if (obj.getTrangthai().equals("1")){%>
											  <option value="1" selected><%=Utility.GLanguage("Hoạt động",session,jedis) %></option>
											  <option value="0"><%=Utility.GLanguage("Ngưng hoạt động",session,jedis) %></option>
											  <option value="2"></option>
											  
										<%}else if(obj.getTrangthai().equals("0")) {%>
											  <option value="0" selected><%=Utility.GLanguage("Ngưng hoạt động",session,jedis) %></option>
											  <option value="1" ><%=Utility.GLanguage("Hoạt động",session,jedis) %></option>
											  <option value="2" ></option>
											  
										<%}else{%>																						  
											  <option value="1" ><%=Utility.GLanguage("Hoạt động",session,jedis) %></option>
											  <option value="0" ><%=Utility.GLanguage("Ngưng hoạt động",session,jedis) %></option>
											  <option value="2" selected></option>
										<%}%>
                                    </SELECT></TD>
						      </TR>
							    <TR>
									<TD colspan="4" class="plainlabel">
									<a class="button2" href="javascript:clearform()">
											<img style="top: -4px;" src="../images/button.png" alt=""><%=Utility.GLanguage("Nhập lại",session,jedis) %></a>&nbsp;&nbsp;&nbsp;&nbsp;
                                    <a class="button2" href="javascript:xuatexcel()">
											<img style="top: -4px;" src="../images/button.png" alt=""><%=Utility.GLanguage("Xuất Excel",session,jedis) %> </a>&nbsp;&nbsp;&nbsp;&nbsp;
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
							<LEGEND class="legendtitle">&nbsp;<%=Utility.GLanguage("Quản lý khu vực",session,jedis) %> &nbsp;&nbsp;&nbsp;
							<%if(quyen[Utility.THEM]!=0) {%>
							<a class="button3" href="javascript:newform()">
    								<img style="top: -4px;" src="../images/New.png" alt=""><%=Utility.GLanguage("Tạo mới",session,jedis) %> </a>    
							<%} %>
							
						</LEGEND>
	
						    <TABLE class="" width="100%">
						<TR>
							<TD width="98%">
							<TABLE width="100%" border="0" cellspacing="1" cellpadding="4">

								<TR class="tbheader">
									<TH width="4%"><%=Utility.GLanguage("STT",session,jedis) %></TH>
									<TH width="7%"><%=Utility.GLanguage("Mã",session,jedis) %> </TH>
									<TH width="11%"><%=Utility.GLanguage("Mã DMS",session,jedis) %></TH>
									<TH width="15%"><%=Utility.GLanguage("Tên Quản lý khu vực",session,jedis) %> </TH>
									<TH width="10%"><%=Utility.GLanguage("Số ĐT",session,jedis) %> </TH>
									<!-- <TH width="10%">Kênh BH </TH> -->
									<TH width="8%"><%=Utility.GLanguage("ĐVKD",session,jedis) %></TH>
									<TH width="8%"><%=Utility.GLanguage("Khu vực",session,jedis) %></TH>
									<TH width="9%"><%=Utility.GLanguage("Trạng thái",session,jedis) %> </TH>
									<TH width="6%"><%=Utility.GLanguage("Ngày tạo",session,jedis) %></TH>
									<TH width="9%"><%=Utility.GLanguage("Người tạo",session,jedis) %></TH>
									<TH width="6%"><%=Utility.GLanguage("Ngày sửa",session,jedis) %> </TH>
									<TH width="9%"><%=Utility.GLanguage("Người sửa",session,jedis) %></TH>
									<TH width="8%" align="center">&nbsp;<%=Utility.GLanguage("Tác vụ",session,jedis) %></TH>
								</TR>

                                <%      
                                   
                                   
                                    int m = 0;
                                    String lightrow = "tblightrow";
                                    String darkrow = "tbdarkrow";
                                    
                                    if(asmList!=null)
                                    	try{
                                    		while (asmList.next()){
                                       
                                        	if (m % 2 != 0) {%>                     
                                            	<TR class= <%=lightrow%> >
                                        	<%} else { %>
                                            	<TR class= <%= darkrow%> >
                                        	<%}%>
                                        			<TD align="center"><%=m+1%></TD>
                                        			<TD align="left"><div align="left"><%=asmList.getString("MANHANVIEN") %></div></TD>    
                                        			<TD align="left"><div align="left"><%=asmList.getString("MAFAST") %></div></TD>  
                                            	    <TD align="left"><div align="left"><%= asmList.getString("asmten") %></div></TD>                                   
                                                	<TD><div align="center"><%= asmList.getString("dienthoai")  %></div></TD>
                                                	<TD align="center"><%= asmList.getString("dvkd") %></TD>
                                                	<TD align="center"><%= asmList.getString("kv") %></TD>
                                                	<% if (asmList.getString("trangthai").equals("1")){ %>
                                                    	<TD align="center"><%=Utility.GLanguage("Hoạt động",session,jedis) %></TD>
                                                	<%}else{%>
                                                    	<TD align="center"><%=Utility.GLanguage("Ngưng hoạt động",session,jedis) %></TD>
                                                	<%}%>
                                                	<TD align="center"><%= asmList.getString("ngaytao") %></TD>
                                                	<TD align="center"><%= asmList.getString("nguoitao") %></TD>
                                                	<TD align="center"><%= asmList.getString("ngaysua")%></TD>
                                                	<TD align="center"><%= asmList.getString("nguoisua")%></TD>
                                                	<TD align="center">
                                                		<TABLE border = 0 cellpadding="0" cellspacing="0">
                                                    	<TR><TD>
                                                    	<%if(quyen[Utility.SUA]!=0) {%>
                                                    		<A href = "../../RouterSvl?task=<%= Util.dongMa(getServletContext().getInitParameter("RedirectNoScript") +"ASMUpdateSvl?userId="+userId+"&update="+ asmList.getString("asmid")+"") %>"><img src="../images/Edit20.png" alt="Cap nhat" title="Cập nhật" width="20" height="20" longdesc="Cap nhat" border = 0></A>
                                                    	<%} %>
                                                    	</TD>
                                                    	<TD>&nbsp;</TD>
                                                    	<TD>
                                                    	<%if(quyen[Utility.XOA]!=0) {%>
                                                        	<A href = "../../RouterSvl?task=<%= Util.dongMa(getServletContext().getInitParameter("RedirectNoScript") +"ASMSvl?userId="+userId+"&delete="+ asmList.getString("asmid")+"") %>"><img src="../images/Delete20.png" alt="Xoa" title="Xóa" width="20" height="20" longdesc="Xoa" border=0 onclick="if(!confirm('Bạn có muốn xóa Trưởng Khu Vực này?')) return false;"></A>
                                                    	<%} %>
                                                    	</TD>
                                                    	</TR></TABLE>
                                                	</TD>
                                            	</TR>
                                		<%m++; }}catch(Exception e){ e.printStackTrace(); }%>
                                
                                <tr class="tbfooter" > 
											 <TD align="center" valign="middle" colspan="13" class="tbfooter">
											 	&nbsp;
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
	
 	if(dvkd != null) dvkd.close();  
 	if(kv != null) kv.close(); 
 	if(asmList != null) asmList.close(); 
	obj.DBClose();
	}%>