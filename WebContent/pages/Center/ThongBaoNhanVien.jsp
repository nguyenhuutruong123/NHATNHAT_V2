<%@page import="java.sql.SQLException"%>
<%@page import="java.sql.ResultSet"%>
<%@page import="javax.naming.spi.DirStateFactory.Result"%>
<%@page import="geso.dms.center.db.sql.dbutils"%>
<%@page import="geso.dms.center.beans.thongbao.*"%>
<%@ page  import = "geso.dms.center.util.*" %>
<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<% IThongbaoList thongbao = (IThongbaoList)session.getAttribute("obj"); %>
<% ResultSet tbList = thongbao.getThongbaoList(); %>
<% String userId = (String) session.getAttribute("userId");  %>
<% String userTen = (String) session.getAttribute("userTen");  %>
<% Utility util = new Utility(); %>
<%String url = util.getChuyenNguUrl("ThongbaoSvl", "&viewMode=" + thongbao.getViewMode() + "&loaivanban=" + thongbao.getLoaithongbao() + "&task=" + thongbao.getTask(),session); %>
<% thongbao.setNextSplittings();
String sum = (String) session.getAttribute("sum");
if(!util.check(userId, userTen, sum)){
	response.sendRedirect(request.getContextPath() + "/redirect.jsp");
}else{
int[] quyen = new  int[6];
quyen = util.Getquyen("ThongbaoSvl","&viewMode=" + thongbao.getViewMode() + "&loaivanban=" + thongbao.getLoaithongbao() + "&task=" + thongbao.getTask(), userId);

%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd"> <% Utility Utilback = new Utility(); %>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>Insert title here</title>
<LINK rel="stylesheet" type="text/css" media="print" href="../css/impression.css">
<LINK rel="stylesheet" href="../css/main.css" type="text/css">
<link type="text/css" rel="stylesheet" href="../css/mybutton.css">
<script type="text/javascript" src="../scripts/jquery.min.js"></script>
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
<script type="text/javascript">
	function clearform()
	{
	    document.forms["ctForm"].maso.value = "";
	    document.forms["ctForm"].ngaybatdau.value = "";
	    document.forms["ctForm"].ngayketthuc.value = "";
	    document.forms["ctForm"].tieude.value = "";
	    document.forms["ctForm"].noidung.value = "";
	    document.forms["ctForm"].trangthai.value = "";
	    Submitform();
	}
	function Submitform()
	{
		document.forms["ctForm"].action.value = "nhanvien";
		document.forms["ctForm"].submit();
	}
	function TaoMoi()
	{
		document.forms["ctForm"].action.value = "new";
		document.forms["ctForm"].submit();
	}
	function thongbao()
	 {
		 var tt = document.forms["ckParkForm"].msg.value;
	 	 if(tt.length>0)
	     	alert(document.forms["ckParkForm"].msg.value);
	 }
</script>
</head>
<body >
<form name="ctForm" method="post" action="../../ThongbaoSvl" >
<% redis.clients.jedis.Jedis jedis =  geso.dms.center.util.Utility.getJedis(); %><% geso.dms.center.util.Csrf csdr=new geso.dms.center.util.Csrf(request,response,true,false,true);%>
 <input type="hidden" name="<%=csdr.get_tokenName() %>" value='<%=csdr.get_tokenValue() %>'>

<input type="hidden" name="currentPage" value="<%= thongbao.getCurrentPage() %>" >
<input type="hidden" name="viewMode" value="<%= thongbao.getViewMode() %>" >
<input type="hidden" name="task" value="<%= thongbao.getTask() %>" >

<input type="hidden" name="action">
<script language="javascript" type="text/javascript">
    thongbao();
</script> 
<TABLE width="100%" border="0" cellspacing="0" cellpadding="0"
	height="100%">
	<TR>
		<TD colspan="4" align='left' valign='top'>
			<TABLE width="100%" border="0" cellpadding="0" cellspacing="1">
				<TR>
					<TD align="left" >
					   <table width="100%" border="0" cellpadding="0" cellspacing="0">
						  <tr height="22">
							   <TD align="left" colspan="2" class="tbnavigation">
							   		<%=url %> </TD>
							   <TD colspan="2" align="right" class="tbnavigation"><%=Utility.GLanguage("Chào mừng bạn",session,jedis) %>  &nbsp;<%=userTen%>  </TD> 
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
								<TD width="100%" align="left">
								<FIELDSET >
									<LEGEND class="legendtitle"><%=Utility.GLanguage("Tiêu chí tìm kiếm",session,jedis) %>  </LEGEND>

									<TABLE class="tblight" width="100%" cellpadding="6" cellspacing = "0">
									   <TR>
										  <TD class="plainlabel" width="120px" ><%=Utility.GLanguage("Ngày bắt đầu",session,jedis) %></TD>
										  <TD class="plainlabel" width="220px" ><input type="text" class="days" id="ngaybatdau" name="ngaybatdau" value="<%= thongbao.getNgaybatdau()%>" onchange="Submitform()" ></TD>
										  <TD class="plainlabel" width="120px" ><%=Utility.GLanguage("Ngày kết thúc",session,jedis) %></TD>
										  <TD class="plainlabel"  ><input type="text" class="days" id="ngayketthuc" name="ngayketthuc" value="<%= thongbao.getNgayketthuc()%>" onchange="Submitform()" ></TD>
									   </TR>
									   <TR>
										  <TD class="plainlabel" ><%=Utility.GLanguage("Tiêu đề",session,jedis) %></TD>
										  <TD class="plainlabel" ><input type="text" id="tieude"  name="tieude" size="20" value="<%= thongbao.getTieude()%>" onchange="Submitform()" ></TD>
										  <TD class="plainlabel" ><%=Utility.GLanguage("Nội dung",session,jedis) %></TD>
										  <TD class="plainlabel" ><input type="text" id="noidung"  name="noidung" size="20" value="<%= thongbao.getNoidung() %>" onchange="Submitform()" ></TD>
									   </TR>

									   <TR>
										  <TD class="plainlabel" ><%=Utility.GLanguage("Mã số",session,jedis) %></TD>
										  <TD class="plainlabel" ><input type="text" id="maso"  name="maso" size="20" value="<%= thongbao.getId()%>" onchange="Submitform()" ></TD>
										  
									   
										<TD class="plainlabel"><%=Utility.GLanguage("Trạng thái",session,jedis) %></TD>
										<TD class="plainlabel">
											<select name="trangthai" name="trangthai" onchange="Submitform()" >
											  	<% if (thongbao.getTrangthai().equals("0")){ %>
											  		<option value=""></option>
											    	<option value="0" selected><%=Utility.GLanguage("Chưa xem",session,jedis) %></option>
											    	<option value="1" ><%=Utility.GLanguage("Đã xem",session,jedis) %></option>
												
												<%	}else if(thongbao.getTrangthai().equals("1")){%>
												    <option value=""></option>
												    <option value="0" ><%=Utility.GLanguage("Chưa xem",session,jedis) %></option>
											    	<option value="1" selected><%=Utility.GLanguage("Đã xem",session,jedis) %> </option>	
												   
												<%}else{ %>
													<option value=""></option>
												    <option value="0" ><%=Utility.GLanguage("Chưa xem",session,jedis) %></option>
											    	<option value="1"><%=Utility.GLanguage("Đã xem",session,jedis) %> </option>	
												<%} %>
									    	</select>
									    </TD>
								</TR>
										<TR>
                                             <TD class="plainlabel" colspan='4'> 
                                             <a class="button2" href="javascript:clearform()">
	<img style="top: -4px;" src="../images/button.png" alt=""><%=Utility.GLanguage("Nhập lại",session,jedis) %></a>&nbsp;&nbsp;&nbsp;&nbsp;	
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
							<LEGEND class="legendtitle">&nbsp;Thông báo&nbsp;&nbsp;&nbsp;
							
						</LEGEND>
				
							<TABLE class="" width="100%" border="0" cellpadding="0" cellspacing="0">
								<TR>
									<TD width="98%">
										<TABLE width="100%" border="0" cellspacing="1" cellpadding="4">
											<TR class="tbheader">
												<TH align="center" width="10%" ><%=Utility.GLanguage("Mã",session,jedis) %></TH>
											  	<TH align="center" width="30%" ><%=Utility.GLanguage("Tiêu đề",session,jedis) %></TH>
											  	<TH align="center" width="10%" ><%=Utility.GLanguage("Loại",session,jedis) %></TH>
							                    <TH align="center" width="10%" ><%=Utility.GLanguage("Trạng thái",session,jedis) %></TH>
							                    <TH align="center" width="10%" > <%=Utility.GLanguage("Ngày bắt đầu",session,jedis) %> </TH>
							                    <TH align="center" width="10%" ><%=Utility.GLanguage("Ngày kết thúc",session,jedis) %></TH>
							                    <TH align="center" width="10%" ><%=Utility.GLanguage("Người gửi",session,jedis) %></TH>
							                    <TH align="center" width="10%"  ><%=Utility.GLanguage("Tác vụ",session,jedis) %></TH>
										  	</TR>									
												<%
												if(tbList != null)
												{
													try
													{
														int m = 0;
														String lightrow = "tblightrow";
														String darkrow = "tbdarkrow";
														while (tbList.next())
														{
															if (m % 2 != 0) 
															{%>						
																<TR class= <%=lightrow%> >
															<%} 
															else 
															{%>
																<TR class= <%= darkrow%> >
															<%}%>
															<TD align="center"><%= tbList.getString("PK_SEQ") %></TD>
															<%
																String filename=tbList.getString("filename");
																if(filename.equals("0")){
																%>
																<TD><%= tbList.getString("TIEUDE") %></TD> 
															<%}else { %>
																<TD ><%= tbList.getString("TIEUDE") %><img src="../images/paperclip.png"></img></TD> 
															<%} %>
															<TD> <%= tbList.getString("loaithongbao").equals("5") ? "Thông báo" : "Văn bản" %> </TD>  									                                    
													     	<% if(tbList.getString("trangthai").trim().equals("0")){%>
				                                                 <TD align="center" style="color: red;" >Chưa xem</TD>
				                                                 <%}else{ %>
				                                                  <TD align="center">Đã xem</TD>
				                                                 <%} %>	
										                    <TD align="center"><%= tbList.getString("ngaybatdau") %></TD>
								         					<TD align="center"><%= tbList.getString("ngayketthuc") %></TD>
								         					<TD align="center"><%= tbList.getString("tennv") %></TD>
															<TD align="center" >
															<TABLE border = "0" cellpadding="0" cellspacing="0">
																<TR>
																	<TD> 
																	<%if( quyen[3]!=0 || thongbao.getViewMode().equals("0") ){ %>
																		<%if(quyen[Utility.XEM]!=0){ %>
																		
																  		<A href="../../RouterSvl?task=<%= util.dongMa(getServletContext().getInitParameter("RedirectNoScript") +"ThongbaoUpdateSvl?userId=" +userId+"&id="+ tbList.getString("PK_SEQ")+ "&task=capnhatnv&viewMode=0"+"")%>">
			                                               				<img src="../images/Display20.png" alt="Cap nhat" width="20" height="20" longdesc="Cap nhat" border = 0></A>
			                                               				<%} %>
																	<%} %>
																	</TD> 
																	<TD>&nbsp;</TD>
																</TR>
																</TABLE>
															</TD>
														</TR>
														<% m++; 
			                                                }
														tbList.close();
														}
														catch(SQLException ex){ System.out.print("Loi roi...."); }
														}
														%>
												
									<tr class="tbfooter" > 
						 <TD align="center" valign="middle" colspan="13" class="tbfooter">
						 	 <% thongbao.setNextSplittings(); %>
												 <script type="text/javascript" src="../scripts/phanTrang.js"></script>
												<input type="hidden" name="crrApprSplitting" value="<%= thongbao.getCrrApprSplitting() %>" >
												<input type="hidden" name="nxtApprSplitting" value="<%= thongbao.getNxtApprSplitting() %>" >
											 	<%if(thongbao.getNxtApprSplitting() >1) {%>
													<!-- <img alt="Trang Dau" src="../images/first.gif" style="cursor: pointer;" onclick="View(document.forms[0].name, 1, 'viewnv')"> &nbsp; -->
												<%}else {%>
													<!-- <img alt="Trang Dau" src="../images/first.gif" > &nbsp; -->
													<%} %>
												<% if(thongbao.getNxtApprSplitting() > 1){ %>
													<img alt="Trang Truoc" src="../images/prev.gif" style="cursor: pointer;" onclick="View(document.forms[0].name, eval(document.forms[0].nxtApprSplitting.value) -1, 'viewnv')"> &nbsp;
												<%}else{ %>
													<img alt="Trang Truoc" src="../images/prev_d.gif" > &nbsp;
												<%} %>
												
												<%
													int[] listPage = thongbao.getNextSplittings();
													for(int i = 0; i < listPage.length; i++){
												%>
												
												<% 
												
												System.out.println("Current page:" + thongbao.getNxtApprSplitting());
												System.out.println("List page:" + listPage[i]);
												
												if(listPage[i] == thongbao.getNxtApprSplitting()){ %>
												
													<a  style="color:white;"></a>
												<%}else{ %>
													<a href="javascript:View(document.forms[0].name, <%= listPage[i] %>, 'viewnv')"></a>
												<%} %>
													<input type="hidden" name="list" value="<%= listPage[i] %>" />  &nbsp;
												<%} %>
												
												<% if(thongbao.getNxtApprSplitting() < thongbao.getTheLastSplitting()){ %>
													&nbsp; <img alt="Trang Tiep" src="../images/next.gif" style="cursor: pointer;" onclick="View(document.forms[0].name, eval(document.forms[0].nxtApprSplitting.value) +1, 'viewnv')"> &nbsp;
												<%}else{ %>
													&nbsp; <img alt="Trang Tiep" src="../images/next_d.gif" > &nbsp;
												<%} %>
												<%if(thongbao.getNxtApprSplitting() == thongbao.getTheLastSplitting()) {%>
											   		<!-- <img alt="Trang Cuoi" src="../images/last.gif" > &nbsp; -->
										   		<%}else{ %>
										   			<!-- <img alt="Trang Cuoi" src="../images/last.gif" style="cursor: pointer;" onclick="View(document.forms[0].name, -1, 'viewnv')"> &nbsp; -->
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
	<%geso.dms.center.util.Utility.JedisClose(jedis); %>
</TABLE>
<%
thongbao.DBClose();
%>
</form>
</body>
<%} %>
</HTML>