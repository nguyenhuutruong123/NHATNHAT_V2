<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page  import = "java.util.Iterator" %>
<%@ page  import = "java.util.List" %>
<%@ page  import = "java.util.ArrayList" %>
<%@ page  import = "geso.dms.center.beans.giamsatbanhang.IGiamsatbanhang" %>
<%@ page  import = "geso.dms.center.beans.giamsatbanhang.IGiamsatbanhangList" %>
<%@ page  import = "java.sql.ResultSet" %>
<%@ page  import = "geso.dms.center.util.*" %>
<%	String userId = (String) session.getAttribute("userId");  
	String userTen = (String) session.getAttribute("userTen");  	
	String sum = (String) session.getAttribute("sum");
	Utility util = (Utility) session.getAttribute("util");
	if(!util.check(userId, userTen, sum)){
		response.sendRedirect(request.getContextPath() + "/redirect.jsp");
	}else{ %>
	

<% IGiamsatbanhangList obj = (IGiamsatbanhangList)session.getAttribute("obj"); %>
<% List<IGiamsatbanhang> gsbhlist = (List<IGiamsatbanhang>)obj.getGsbhList(); %>

<% obj.setNextSplittings(); 
	int[] quyen = new  int[6];
	quyen = util.Getquyen("GiamsatbanhangSvl","",userId);
	String url = util.getChuyenNguUrl("GiamsatbanhangSvl", "",session);
%>
<% Utility Util = new Utility(); %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"> <% Utility Utilback = new Utility(); %>
<HTML>
<HEAD>
<TITLE><%= getServletContext().getInitParameter("TITLENAME") %></TITLE>
<META http-equiv="Content-Type" content="text/html; charset=utf-8">

<META http-equiv="Content-Style-Type" content="text/css">

<script type="text/javascript" src="../scripts/phanTrang.js"></script>
<LINK rel="stylesheet" href="../css/main.css" type="text/css">
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
	document.gsbhForm.maFAST.value = "";    
    document.gsbhForm.TenGSBH.value = "";    
    document.gsbhForm.SoDienThoai.value = "";
    document.gsbhForm.TrangThai.selectedIndex = 0;
    //document.gsbhForm.tungay.value = "";
    document.gsbhForm.denngay.value = "";
// document.gsbhForm.KenhBanHang.selectedIndex = 0;
	
	submitform();
}

function submitform()
{
	document.forms['gsbhForm'].action.value= 'search';
	document.forms['gsbhForm'].submit();
}
function xuatexcel()
{
	document.forms['gsbhForm'].action.value= 'excel';
	document.forms['gsbhForm'].submit();
}
function newform()
{
	document.forms['gsbhForm'].action.value= 'new';
	document.forms['gsbhForm'].submit();
}
function thongbao()
{var tt = document.forms['gsbhForm'].msg.value;
	if(trim(tt).length>0)
    alert(document.forms['gsbhForm'].msg.value);
	}

</SCRIPT>
</HEAD>
<BODY leftmargin="0" bottommargin="0" topmargin="0" rightmargin="0">
<form name="gsbhForm" method="post" action="../../GiamsatbanhangSvl">
<% redis.clients.jedis.Jedis jedis =  geso.dms.center.util.Utility.getJedis(); %><% geso.dms.center.util.Csrf csdr=new geso.dms.center.util.Csrf(request,response,true,false,true);%>
 <input type="hidden" name="<%=csdr.get_tokenName() %>" value='<%=csdr.get_tokenValue() %>'>

<input type="hidden" name="userId" value='<%= userId %>'>
<input type="hidden" name="userTen" value='<%= userTen %>'>
<input type="hidden" name="action" value='1'>
<input type="hidden" name="crrApprSplitting" value="<%= obj.getCrrApprSplitting() %>" >
<input type="hidden" name="nxtApprSplitting" value="<%= obj.getNxtApprSplitting() %>" >

<input type="hidden" name="msg" value='
<%=Utility.GLanguage(obj.getMsg(),session,jedis) %>'>

<script language="javascript" type="text/javascript">
    thongbao();
</script> 

<TABLE width="100%" border="0" cellspacing="0" cellpadding="0"
	height="100%">
	
	<TR>
		<TD colspan="4" align='left' valign='top' bgcolor="#FFFFFF"><!--begin body Dossier-->
		<!--begin common dossier info---> <!--End common dossier info--->
		<TABLE width="100%" cellpadding="0" cellspacing="1">
			
				<TR>
					<TD align="left">
					  <table width="100%" border="0" cellpadding="0" cellspacing="0">
						  <tr height="22">
						   <TD align="left" colspan="2" class="tbnavigation"><%= url %></TD>
   
						   <TD colspan="2" align="right" class="tbnavigation"><%=Utility.GLanguage("Chào mừng bạn",session,jedis) %>  <%=userTen %>&nbsp;  </TD>
						  </tr>
 					  </table>
					</TD>
				</TR>
				<TR>
					<TD>
					<TABLE border="0" width="100%" cellpadding="0" cellspacing="0">
						<TR>
							<TD width="100%" align="center" ><FIELDSET>
							<legend class="legendtitle" ><%=Utility.GLanguage("Tiêu chí tìm kiếm",session,jedis) %> </legend>
							<div align="left">
						    </div>
							<TABLE  width="100%" cellpadding="6" cellspacing="0">
								  <TR>
								      <TD style="width: 150px;" class="plainlabel"><%=Utility.GLanguage("Tên của PTT / GĐ CN 2",session,jedis) %> </TD>
								      <TD colspan="3" class="plainlabel"><input type="text" name="TenGSBH" value="<%= obj.getTen() %>" onChange="submitform();"></TD>
								 	 
								  </TR>
								  
								  <TR style="display: none;" >
									<TD class="plainlabel"><%=Utility.GLanguage("Mã DMS",session,jedis) %></TD>
								    <TD class="plainlabel"><INPUT name="maFAST" type="text" value="<%= obj.getMaFAST() %>" size="40" onChange = "submitform();"> </TD>
								 	<TD class="plainlabel"><%=Utility.GLanguage("Đến ngày",session,jedis) %> </TD>
								 	<TD class="plainlabel"><input type="text" name="denngay" value="<%= obj.getDenngay() %>" class="days" onChange="submitform();"> </TD>
					
								 </TR>
								  
								  <TR>
								    <TD class="plainlabel"><%=Utility.GLanguage("Số điện thoại",session,jedis) %> </TD>
								    <TD style="width: 250px;" class="plainlabel"><input type="text" name="SoDienThoai" value="<%= obj.getSodienthoai() %>" onChange="submitform();"></TD>
						     		<TD style="width: 120px;" class="plainlabel"><%=Utility.GLanguage("Trạng thái",session,jedis) %> </TD>
								   	<TD class="plainlabel"><SELECT name="TrangThai" onChange = "submitform();" >
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
                                    </SELECT></TD>
						      </TR>
						      
						      <TR>
								
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
					
								<TD class="plainlabel"><%=Utility.GLanguage("ASM",session,jedis) %></TD>

											<TD class="plainlabel">
												<select name="asmId"  onChange = "submitform();">
													<option value=""></option>
													<%
														try {
																ResultSet asmRS = obj.getasmRS();
																if(asmRS != null)
																while (asmRS.next()) {
																	if (asmRS.getString("pk_seq").equals(obj.getAsmId())) {
													%>
													<option value='<%=asmRS.getString("pk_seq")%>' selected><%=asmRS.getString("ten")%></option>
													<%
														} else {
													%>
													<option value='<%=asmRS.getString("pk_seq")%>'><%=asmRS.getString("ten")%></option>
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
									<TD class="plainlabel" colspan="4">
									
										<!-- <a  class="button1" href="javascript:submitform()"> 
										<img style="top: -4px;" src="../images/Search30.png"alt=""><%=Utility.GLanguage("Tìm kiếm",session,jedis) %> </a>
											&nbsp;&nbsp;&nbsp;&nbsp; -->
										<a class="button2" href="javascript:clearform()">
											<img style="top: -4px;" src="../images/button.png" alt=""><%=Utility.GLanguage("Nhập lại",session,jedis) %></a>&nbsp;&nbsp;&nbsp;&nbsp;	
										<a class="button2" href="javascript:xuatexcel()">
											<img style="top: -4px;" src="../images/button.png" alt=""><%=Utility.GLanguage("Xuất Excel",session,jedis) %> </a>
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
							<LEGEND class="legendtitle">&nbsp;<%=Utility.GLanguage("Giám sát bán hàng/GDCN",session,jedis) %> &nbsp;&nbsp;&nbsp;
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
									<TH width="6%"><%=Utility.GLanguage("Mã DMS",session,jedis) %></TH>
									<TH width="25%"><%=Utility.GLanguage("Tên",session,jedis) %> </TH>
									<TH width="10%"><%=Utility.GLanguage("Khu vực",session,jedis) %> </TH>
									<TH width="10%"><%=Utility.GLanguage("Nhà cung cấp",session,jedis) %> </TH>									
									<TH width="7%"><%=Utility.GLanguage("Trạng thái",session,jedis) %> </TH>
									<TH width="7%"><%=Utility.GLanguage("Người tạo",session,jedis) %> </TH>
									<TH width="7%"><%=Utility.GLanguage("Ngày tạo",session,jedis) %> </TH>
									<TH width="7%"><%=Utility.GLanguage("Người sửa",session,jedis) %> </TH>
									<TH width="6%"><%=Utility.GLanguage("Ngày sửa",session,jedis) %> </TH>
									<TH width="15%" align="center">&nbsp;<%=Utility.GLanguage("Tác vụ",session,jedis) %></TH>
								</TR>

								<% 
									IGiamsatbanhang gsbh = null;
									int size = gsbhlist.size();
									int m = 0;
									String lightrow = "tblightrow";
									String darkrow = "tbdarkrow";
									while (m < size){
										gsbh = gsbhlist.get(m);
										if (m % 2 != 0) {%>						
											<TR class= <%=lightrow%> >
										<%} else {%>
											<TR class= <%= darkrow%> >
										<%}%>
												<TD align="center"><%=m+1%></TD>
												<TD align="left"><div align="left"><%=gsbh.getMaNhanVien()%></div></TD>
												<TD align="left"><div align="left"><%=gsbh.getMaFAST() %></div></TD>  
												<TD align="left"><div align="left"><%= gsbh.getTen()%></div></TD>                                   
												<TD align="left"><div align="left"><%= gsbh.getSodienthoai() %></div></TD> 
												<TD align="left"><div align="left"><%= gsbh.getNhacungcap() %></div></TD>      
												<% if(gsbh.getTrangthai().equals("1")){ %>                             
												<TD align="center"> <%=Utility.GLanguage("Hoạt động",session,jedis) %> </TD>
												<%} else {%>
												<TD align="center"><%=Utility.GLanguage("Ngưng hoạt động",session,jedis) %> </TD>
												<%} %>												
												<TD align="center"><%= gsbh.getNguoitao()%></TD>
												<TD align="center"><%= gsbh.getNgaytao()%></TD>
												<TD align="center"><%= gsbh.getNguoisua()%></TD>
												<TD align="center"><%= gsbh.getNgaysua()%></TD>
												<TD align="center">
												<TABLE border = 0 cellpadding="0" cellspacing="0">
													<TR><TD>
													<%if(quyen[Utility.SUA]!=0) {%>
														<A href = "../../RouterSvl?task=<%= Util.dongMa(getServletContext().getInitParameter("RedirectNoScript") +"GiamsatbanhangUpdateSvl?userId="+userId+"&update="+ gsbh.getId()+"") %>"><img src="../images/Edit20.png" alt="Cap nhat" title="Cập nhật" width="20" height="20" longdesc="Cap nhat" border = 0></A>
													<%} %>
													</TD>
													<TD>&nbsp;</TD>
													<TD>
													<%if(quyen[Utility.XOA]!=0) {%>
														<A href = "../../RouterSvl?task=<%= Util.dongMa(getServletContext().getInitParameter("RedirectNoScript") +"GiamsatbanhangSvl?userId="+userId+"&delete="+ gsbh.getId()+"") %>"><img src="../images/Delete20.png" alt="Xoa" title="Xóa" width="20" height="20" longdesc="Xoa" border=0 onclick="if(!confirm('Bạn có muốn xóa PTT / GĐ CN 2 này ?')) return false;"></A>
														<%} %>
														</TD>
													</TR></TABLE>
												</TD>
											</TR>
								<%m++; }%>
								
<tr class="tbfooter" > 
											 <TD align="center" valign="middle" colspan="13" class="tbfooter">
											 	<%if(obj.getNxtApprSplitting() >1) {%>
													<img alt="Trang Dau" src="../images/first.gif" style="cursor: pointer;" onclick="View('gsbhForm', 1, 'view')"> &nbsp;
												<%}else {%>
													<img alt="Trang Dau" src="../images/first.gif" > &nbsp;
													<%} %>
												<% if(obj.getNxtApprSplitting() > 1){ %>
													<img alt="Trang Truoc" src="../images/prev.gif" style="cursor: pointer;" onclick="View('gsbhForm', eval(gsbhForm.nxtApprSplitting.value) -1, 'view')"> &nbsp;
												<%}else{ %>
													<img alt="Trang Truoc" src="../images/prev_d.gif" > &nbsp;
												<%} %>
												
												<%
													int[] listPage = obj.getNextSplittings();
													for(int i = 0; i < listPage.length; i++){
												%>
												
												<% 
												
												System.out.println("Current page:" + obj.getNxtApprSplitting());
												System.out.println("List page:" + listPage[i]);
												
												if(listPage[i] == obj.getNxtApprSplitting()){ %>
												
													<a  style="color:white;"><%= listPage[i] %>/ <%=obj.getTheLastSplitting() %></a>
												<%}else{ %>
													<a href="javascript:View('gsbhForm', <%= listPage[i] %>, 'view')"><%= listPage[i] %></a>
												<%} %>
													<input type="hidden" name="list" value="<%= listPage[i] %>" />  &nbsp;
												<%} %>
												
												<% if(obj.getNxtApprSplitting() < obj.getTheLastSplitting()){ %>
													&nbsp; <img alt="Trang Tiep" src="../images/next.gif" style="cursor: pointer;" onclick="View('gsbhForm', eval(gsbhForm.nxtApprSplitting.value) +1, 'view')"> &nbsp;
												<%}else{ %>
													&nbsp; <img alt="Trang Tiep" src="../images/next_d.gif" > &nbsp;
												<%} %>
												<%if(obj.getNxtApprSplitting() == obj.getTheLastSplitting()) {%>
											   		<img alt="Trang Cuoi" src="../images/last.gif" > &nbsp;
										   		<%}else{ %>
										   			<img alt="Trang Cuoi" src="../images/last.gif" style="cursor: pointer;" onclick="View('gsbhForm', -1, 'view')"> &nbsp;
										   		<%} %>
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
<%}%>