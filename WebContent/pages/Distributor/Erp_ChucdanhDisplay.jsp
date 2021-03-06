<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page  import = "geso.dms.distributor.beans.chucdanh.IChucdanh" %>
<%@ page  import = "java.sql.ResultSet" %>
<%@ page  import = "geso.dms.center.util.*" %>
<%	String userId = (String) session.getAttribute("userId");  
	String userTen = (String) session.getAttribute("userTen");  	
	String sum = (String) session.getAttribute("sum");
	Utility util = (Utility) session.getAttribute("util");
	if(!util.check(userId, userTen, sum)){
		response.sendRedirect("/Phanam/index.jsp");
	}else{ %>

<%  IChucdanh cdBean = (IChucdanh)session.getAttribute("cdBean");%>
<%  ResultSet nvlist = (ResultSet)cdBean.getNvList(); 
	ResultSet  rsnvquanly=(ResultSet)cdBean.getRsNvquanly();
	ResultSet rsDvth = (ResultSet)cdBean.getRsDvth(); 
	ResultSet rsCty = (ResultSet)cdBean.getRsCty();
	
	String strnvquanly=cdBean.getNvquanly();
%>


<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"> <% Utility Utilback = new Utility(); %>
<HTML>
<HEAD>
<TITLE>Phanam - Project</TITLE>
<META http-equiv="Content-Type" content="text/html; charset=utf-8">

<META http-equiv="Content-Style-Type" content="text/css">
<LINK rel="stylesheet" href="../css/main.css" type="text/css">
</HEAD>
<BODY leftmargin="0" bottommargin="0" topmargin="0" rightmargin="0">
<form name="cdForm" method="post" action="../../Erp_ChucdanhUpdateSvl" charset="utf-8">
<INPUT name="userId" type="hidden" value='<%=userId %>' size="30">
<INPUT name="cdId" type="hidden" value='<%=cdBean.getId() %>' size="30">
<INPUT name="action" type="hidden" value='' size="30">
<TABLE width="100%" border="0" cellspacing="0" cellpadding="0"
	height="100%">
	
	<TR>
		<TD colspan="4" align='left' valign='top' bgcolor="#FFFFFF">

		<TABLE width="100%" cellpadding="0" cellspacing="1">
			
				<TR>
					<TD align="left">
					  <table width="100%" border="0" cellpadding="0" cellspacing="0">
						  <tr height="22">
						   <TD align="left" colspan="2" class="tbnavigation">&nbsp;Dữ liệu nền &gt; Cơ bản &gt; Chức danh > Hiển thị </TD>
   
						   <TD colspan="2" align="right" class="tbnavigation"><%=Utility.GLanguage("Chào mừng bạn",session,jedis) %> <%=userTen %>&nbsp;  </TD>
						  </tr>
 					  </table>
					</TD>
				</TR>
		</TABLE>		
		<TABLE width="100%" border="0" cellpadding="0" cellspacing="1">
				<TR ><TD >
						<TABLE width="100%" border="0" cellpadding="0" cellspacing="0">
								<TR class = "tbdarkrow">
									<TD width="30" align="left"><A href="../../Erp_ChucdanhSvl?userId=<%=userId %>" ><img src="../images/Back30.png" alt="Quay ve"  title="Quay ve" border="1" longdesc="Quay ve" style="border-style:outset"></A></TD>
									<TD >&nbsp; </TD>						
								</TR>
						</TABLE>
				</TD></TR>
		</TABLE>
					
		<TABLE width = 100% cellpadding = "4" cellspacing = "0" border = "0">
			<TR>
				<TD width="100%" align="left" >
					<FIELDSET>
					<LEGEND class="legendtitle">&nbsp;Thông tin Chức danh</LEGEND>

					<TABLE  width="100%" cellpadding="6" cellspacing="0">
						<TR>
							<TD width="20%" class="plainlabel">Chức danh <FONT class="erroralert">*</FONT></TD>
							<TD width="80%" class="plainlabel"><INPUT name="chucdanh" value='<%=cdBean.getChucdanh() %>' type="text" readonly size="40"/></TD>
						</TR>
				      	<TR>
							<TD width="19%" class="plainlabel">Người được bổ nhiệm <FONT class="erroralert">*</FONT></TD>
							  	<TD class="plainlabel" >
								<TABLE cellpadding="0" cellspacing="0" border="0">
									<TR>
								  		<TD>
								  			<select disabled  name="nvId"  onchange=" submitform()">
									
											<%try {%>
												<option value="0"  ></option>
											<% 	while (nvlist.next()){%>
													
													<%	if(nvlist.getString("NVID").equals(cdBean.getNvId())){ %>											
															
															<option value= <%=nvlist.getString("NVID")%> selected><%= nvlist.getString("TEN") %></option>															
														<%}else{%>
															<option value= <%=nvlist.getString("NVID")%> ><%= nvlist.getString("TEN") %></option>																																		
														<%	}
												 }
															
											 }catch(java.sql.SQLException e){
														
											 }%>														                                           
                                      			</SELECT>
                                   			</TD>
										</TR>
									</TABLE>									
								</TD>

						</TR>
						
						<TR>
							<TD width="19%" class="plainlabel">Phòng ban </TD>
							  	<TD class="plainlabel" >
								<TABLE cellpadding="0" cellspacing="0" border="0">
									<TR>
								  		<TD>
								  			<SELECT  name="pbId"  onchange=" submitform()">
									
											<%try {%>
												<option value="0"  ></option>
											<% 	while (rsDvth.next()){%>
													
													<%	if(rsDvth.getString("PK_SEQ").equals(cdBean.getDvthId())){ %>											
															
															<option value= <%=rsDvth.getString("PK_SEQ")%> selected><%= rsDvth.getString("TEN") %></option>															
														<%}else{%>
															<option value= <%=rsDvth.getString("PK_SEQ")%> ><%= rsDvth.getString("TEN") %></option>																																		
														<%	}
												 }
															
											 }catch(java.sql.SQLException e){
														
											 }%>														                                           
                                      			</SELECT>
                                   			</TD>
										</TR>
									</TABLE>									
								</TD>
						</TR>
						
						<TR>
							<TD width="19%" class="plainlabel">Loại </TD>
							<td class="plainlabel">
					  			<SELECT  name="loai" >
								<%	if(cdBean.getLoai().equals("0")){ %>
									<option value="" ></option>			
									<option value="0" selected>Duyệt mua hàng nhập khẩu && kháng sinh</option>
									<option value="1" >Duyệt mua hàng khác</option>															
								<% }else if(cdBean.getLoai().equals("1")){%>
									<option value="" ></option>			
									<option value="0" >Duyệt mua hàng nhập khẩu && kháng sinh</option>
									<option value="1" selected>Duyệt mua hàng khác</option>
								<% }else{ %>
									<option value="" selected></option>			
									<option value="0" >Duyệt mua hàng nhập khẩu && kháng sinh</option>
									<option value="1" >Duyệt mua hàng khác</option>
								<% } %>															                                           
                               	</SELECT>
                         	</td>
						</TR>
						
						<TR>
							<% if(cdBean.getTrangthai().equals("1")) {%>						
						  		<TD class="plainlabel"><input name="trangthai" type="checkbox" disabled  value="1" checked>
						  	<%}else{ %>
						  		<TD class="plainlabel"><input name="trangthai" type="checkbox" disabled  value="0" >
						  	<%} %>
							      Hoạt động &nbsp;&nbsp;&nbsp;
							<% if(cdBean.getDuyetDtvt().equals("1")) {%>						
						  		<input name="duyetdtvt" type="checkbox" value="1" checked>
						  	<%}else{ %>
						  		<input name="duyetdtvt" type="checkbox" value="0" >
						  	<%} %>
							      Duyệt dự toán vật tư
							</TD>
							<TD class="plainlabel">&nbsp;</TD>
						</TR>
						<tr>
							<TD colspan="2" class="plainlabel"    >Chọn nhân viên quản lý <FONT class=""> </FONT></TD>
							
						</tr>
						  	<TR>
								
							  	<TD colspan="2"  >
								<TABLE cellpadding="0" width="100%" cellspacing="0" border="1">
								
									<TR class="tbheader">
										<th>
											Đăng nhập
										</th>
										<th>
											Tên
										</th>
										<th>
											Chọn hết
											<input type="checkbox" disabled  id='chkAll' onchange="sellectAll('chkAll', 'Idnvchon');"   >
										</th>
									</TR>
										<% while (rsnvquanly.next()){ %>
											<tr>
												<td>
													<%=rsnvquanly.getString("DANGNHAP") %>
												</td>
												<td>
													<%=rsnvquanly.getString("TEN") %>
												</td>
												<td>
													<%if(strnvquanly.contains(rsnvquanly.getString("PK_SEQ"))){ %>
														<input type="checkbox" disabled  name="Idnvchon" checked="checked" value="<%= rsnvquanly.getString("PK_SEQ")%>"> 
													<%}else{ %>
															<input type="checkbox" disabled  name="Idnvchon"  value="<%= rsnvquanly.getString("PK_SEQ")%>">
													<%} %>
												</td>
												
											</tr>
										<% } %>
								  		 
									</TABLE>									
								</TD>

						</TR>		
				</TABLE>

				</FIELDSET>
			</TD>
		</TR>
	</TABLE>
	</TD></TR>
</TABLE>
</form>
</BODY>
</HTML>
<%  
   if(nvlist != null)   nvlist.close();
	cdBean.DBClose();
}%>
