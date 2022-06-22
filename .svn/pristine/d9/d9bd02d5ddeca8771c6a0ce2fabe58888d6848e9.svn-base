<%@page import="java.sql.SQLException"%>
<%@page import="java.sql.ResultSet"%>
<%@page import="javax.naming.spi.DirStateFactory.Result"%>
<%@page import="geso.dms.center.db.sql.dbutils"%>
<%@page import="geso.dms.center.beans.thongbao.*"%>
<%@ page  import = "geso.dms.center.util.*" %>
<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<% IThongbaoList thongbao = (IThongbaoList)session.getAttribute("obj"); %>
<% ResultSet tbList = thongbao.getThongbaoList();%>
<% String userId = (String) session.getAttribute("userId");  %>
<% String userTen = (String) session.getAttribute("userTen");
String sum = (String) session.getAttribute("sum");
Utility util = (Utility) session.getAttribute("util");
String url = util.getChuyenNguUrl("ThongbaoSvl", "&viewMode=" + thongbao.getViewMode() + "&loaivanban=" + thongbao.getLoaithongbao() + "&task=" + thongbao.getTask(),session);

if(!util.check(userId, userTen, sum)){
	response.sendRedirect(request.getContextPath() + "/redirect.jsp");
}else{
	int[] quyen = new  int[6];
	quyen = util.Getquyen("ThongbaoSvl","&viewMode=" + thongbao.getViewMode() + "&loaivanban=" + thongbao.getLoaithongbao() + "&task=" + thongbao.getTask(), userId);
	
%>
<% thongbao.setNextSplittings(); %>
<% Utility Util = new Utility(); %>
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
<script type="text/javascript" src="../scripts/phanTrang.js"></script>
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
	    document.forms["ctForm"].ngaybatdau.value =  "";

	    document.forms["ctForm"].ngayketthuc.value =  "";

	    document.forms["ctForm"].tieude.value = "";
	    
	    document.forms["ctForm"].noidung.value = "";
	    
	    document.forms["ctForm"].maso.value = "";
	    
	    //document.forms["ctForm"].loaivanban.value = "";
	    Submitform();
	}
	function Submitform()
	{
		document.forms["ctForm"].action.value = "search";
		document.forms["ctForm"].submit();
	}
	function TaoMoi()
	{
		document.forms["ctForm"].action.value = "new";
		document.forms["ctForm"].submit();
	}
	function thongbao()
	 {
		 var tt = document.forms["ctForm"].msg.value;
	 	 if(tt.length>0)
	     	alert(document.forms["ctForm"].msg.value);
	 }
</script>
<script type="text/javascript">
function goiham()
{
	parent.menu.ajaxOption('<%=userId%>',  'xxx');

}

</script>
</head>
<body onload="goiham()">
<form name="ctForm" method="post" action="../../ThongbaoSvl" >
<% redis.clients.jedis.Jedis jedis =  geso.dms.center.util.Utility.getJedis(); %><% geso.dms.center.util.Csrf csdr=new geso.dms.center.util.Csrf(request,response,true,false,true);%>
 <input type="hidden" name="<%=csdr.get_tokenName() %>" value='<%=csdr.get_tokenValue() %>'>

<input type="hidden" name="action">
<input type="hidden" name="viewMode" value="<%= thongbao.getViewMode() %>" >
<input type="hidden" name="currentPage" value="<%= thongbao.getCurrentPage() %>" > 
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
										  <TD class="plainlabel" width="230px" ><input type="text" class="days" id="ngaybatdau" name="ngaybatdau" value="<%= thongbao.getNgaybatdau()%>" onchange="Submitform()" ></TD>
										  <TD class="plainlabel" width="120px" ><%=Utility.GLanguage("Ngày kết thúc",session,jedis) %></TD>
										  <TD class="plainlabel" ><input type="text" class="days" id="ngayketthuc" name="ngayketthuc" value="<%= thongbao.getNgayketthuc()%>" onchange="Submitform()" ></TD>
										  
									   </TR>
									   <TR>
										  <TD class="plainlabel" ><%=Utility.GLanguage("Tiêu đề",session,jedis) %></TD>
										  <TD class="plainlabel" ><input type="text" id="tieude"  name="tieude" value="<%= thongbao.getTieude()%>" onchange="Submitform()" ></TD>
										  <TD class="plainlabel" ><%=Utility.GLanguage("Nội dung",session,jedis) %></TD>
										  <TD class="plainlabel" ><input type="text" id="noidung"  name="noidung" value="<%= thongbao.getNoidung() %>" onchange="Submitform()" ></TD>
									   </TR>
									   <TR>
										  <TD class="plainlabel"  ><%=Utility.GLanguage("Mã số",session,jedis) %></TD>
										  <TD class="plainlabel"  ><input type="text" id="maso"  name="maso"  value="<%= thongbao.getId()%>" onchange="Submitform()" ></TD>
										  
										  <TD class="plainlabel" ><%=Utility.GLanguage("Loại văn bản",session,jedis) %></TD>
										  <TD  class="plainlabel"  >
										  	 <select name="loaivanban" onchange="Submitform();" >
										  	 	<option value="" ></option>
										  	 	<% if(thongbao.getLoaithongbao().equals("0")) { %>
										  	 		<option value="0" selected="selected" >Văn bản</option>
										  	 	<% } else { %> 
										  	 		<option value="0" ><%=Utility.GLanguage("Văn bản",session,jedis) %></option>
										  	 	<% } %>
										  	 	<% if(thongbao.getLoaithongbao().equals("1")) { %>
										  	 		<option value="1" selected="selected" ><%=Utility.GLanguage("Hướng dẫn",session,jedis) %></option>
										  	 	<% } else { %> 
										  	 		<option value="1" ><%=Utility.GLanguage("Hướng dẫn",session,jedis) %></option>
										  	 	<% } %>
										  	 	<% if(thongbao.getLoaithongbao().equals("2")) { %>
										  	 		<option value="2" selected="selected" ><%=Utility.GLanguage("Căn cứ",session,jedis) %></option>
										  	 	<% } else { %> 
										  	 		<option value="2" ><%=Utility.GLanguage("Căn cứ",session,jedis) %></option>
										  	 	<% } %>
										  	 	<% if(thongbao.getLoaithongbao().equals("3")) { %>
										  	 		<option value="3" selected="selected" ><%=Utility.GLanguage("Thay thế",session,jedis) %></option>
										  	 	<% } else { %> 
										  	 		<option value="3" ><%=Utility.GLanguage("Thay thế",session,jedis) %></option>
										  	 	<% } %>
										  	 	<% if(thongbao.getLoaithongbao().equals("4")) { %>
										  	 		<option value="4" selected="selected" ><%=Utility.GLanguage("Sửa đổi, bổ sung",session,jedis) %></option>
										  	 	<% } else { %> 
										  	 		<option value="4" ><%=Utility.GLanguage("Sửa đổi, bổ sung",session,jedis) %></option>
										  	 	<% } %>
										  	 	<% if(thongbao.getLoaithongbao().equals("5")) { %>
										  	 		<option value="5" selected="selected" ><%=Utility.GLanguage("Thông báo",session,jedis) %></option>
										  	 	<% } else { %> 
										  	 		<option value="5" ><%=Utility.GLanguage("Thông báo",session,jedis) %></option>
										  	 	<% } %>
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
							<LEGEND class="legendtitle"><%=Utility.GLanguage("Văn bản",session,jedis) %>
								<%System.out.println("quyen: "+quyen[Utility.THEM]+"----"+thongbao.getViewMode()+"---"+quyen[Utility.SUA]); %>
							<%if(quyen[Utility.THEM]!=0 || thongbao.getViewMode().equals("0") ){ %>
						
							<%if(quyen[Utility.SUA]!=0){ %>
							<a class="button3" href="javascript:TaoMoi()">
    							<img style="top: -4px;" src="../images/New.png" alt=""><%=Utility.GLanguage("Tạo mới",session,jedis) %> </a> 
    						<%} %>                           
							<%} %>
						</LEGEND>
				
							<TABLE class="" width="100%" border="0" cellpadding="0" cellspacing="0">
								<TR>
									<TD width="98%">
										<TABLE width="100%" border="0" cellspacing="1" cellpadding="4">
											<TR class="tbheader">
												<TH align="center" width="10%"><%=Utility.GLanguage("Mã",session,jedis) %></TH>
											  	<TH align="center" width="16%"><%=Utility.GLanguage("Tiêu đề",session,jedis) %></TH>
											  	<TH align="center" width="10%"><%=Utility.GLanguage("Ngày bắt đầu",session,jedis) %></TH>
											  	<TH align="center" width="10%"><%=Utility.GLanguage("Ngày kết thúc",session,jedis) %></TH>
											  	<TH align="center" width="10%"><%=Utility.GLanguage("File đính kèm",session,jedis) %></TH>
							                    <TH align="center" width="7%"><%=Utility.GLanguage("Ngày tạo",session,jedis) %></TH>
							                    <TH align="center" width="10%"><%=Utility.GLanguage("Người tạo",session,jedis) %></TH>
							                    <TH align="center" width="7%"><%=Utility.GLanguage("Ngày sửa",session,jedis) %></TH>
							                    <TH align="center" width="10%"><%=Utility.GLanguage("Người sửa",session,jedis) %></TH>
							                    <TH align="center" width="10%" ><%=Utility.GLanguage("Tác vụ",session,jedis) %></TH>
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
															<TD align="left"><%= tbList.getString("TIEUDE") %></TD> 
															<TD align="center"><%= tbList.getString("NGAYBATDAU") %></TD> 
															<TD align="center"><%= tbList.getString("NGAYKETTHUC") %></TD>
															<%
																String filename = tbList.getString("filename");
																if(filename.trim().length() <= 0)
																{
																	filename="Không có kèm file";
																}
															%>
															<TD align="left"><%= filename %></TD>     									                                    
													     	<TD align="center"><%= tbList.getString("NGAYTAO") %></TD>	
										                    <TD align="center"><%= tbList.getString("TENNV") %></TD>
								         					<TD align="center"><%= tbList.getString("NGAYSUA") %></TD>
															<TD align="center"><%= tbList.getString("TENNVS") %></TD>
															<TD align="center" >
															<%-- <TABLE border = "0" cellpadding="0" cellspacing="0">
																<TR>
																	<TD>
																  		<A href="../../ThongbaoUpdateSvl?task=capnhat&id=<%= tbList.getString("PK_SEQ") %>">
			                                               				<img src="../images/Display20.png" alt="Cap nhat" width="20" height="20" longdesc="Cap nhat" border = 0></A>
																	</TD> 
																	<TD>&nbsp;</TD>
																	<TD>
																  		<A href="../../ThongbaoSvl?task=xoa&id=<%= tbList.getString("PK_SEQ") %>">
			                                                			<img src="../images/Delete20.png" alt="Xoa" width="20" height="20" longdesc="Xoa" border=0 onclick="return confirm('Bạn có muốn xóa công ty này ?')"></A>
			                                                		</TD>
																</TR>
																</TABLE> --%>
																<TABLE border = 0 cellpadding="0" cellspacing="0">
                                	                    <TR><TD>
                                	                    	<%  
                                	                    		if ( tbList.getString("trangthai").equals("0")  ) {%>
                                	                    		<%if(quyen[Utility.SUA]!=0 || tbList.getString("nguoitaoTB").equals(userId.trim()) ){ %>
	                                    	                    <A href="../../RouterSvl?task=<%= Util.dongMa(getServletContext().getInitParameter("RedirectNoScript") +"ThongbaoUpdateSvl?task=capnhat&id="+tbList.getString("PK_SEQ")+"&viewMode="+ thongbao.getViewMode()+"")  %>"><img src="../images/Edit20.png" alt="Cập nhật" title="Cập nhật" width="20" height="20" longdesc="Cập nhật" border = 0 "></A>
    	                                	                   	<%} %>
    	                                	                   	<%if(quyen[Utility.XOA]!=0 || tbList.getString("nguoitaoTB").equals(userId.trim())){ %>
    	                                	                   <A href="../../RouterSvl?task=<%= Util.dongMa(getServletContext().getInitParameter("RedirectNoScript") +"ThongbaoSvl?task=xoa&id="+ tbList.getString("PK_SEQ")+"&viewMode="+ thongbao.getViewMode()+"")  %>" onclick="return confirm('Bạn muốn xóa thông báo')"><img src="../images/Delete20.png" alt="Xóa" title="Xóa" width="20" height="20" longdesc="Xóa" border = 0 "></A> 
    	                                	                   <%} %>
    	                                	                   <%if( ( quyen[Utility.CHOT]!=0 || tbList.getString("nguoitaoTB").equals(userId.trim()) ) && tbList.getString("tinhtrang").equals("0") ){ %>
    	                                	                    <A href = "../../RouterSvl?task=<%= Util.dongMa(getServletContext().getInitParameter("RedirectNoScript") +"ThongbaoUpdateSvl?task=chot&id="+tbList.getString("PK_SEQ")+"&viewMode="+ thongbao.getViewMode()+"")  %>&loaivanban=<%= thongbao.getLoaithongbao() %>" onclick="return confirm('Bạn muốn gửi thông báo?')"><img src="../images/wmove2doc.png" alt="Chot"  width="20" height="20" title="Gửi thông báo" border = 0></A>
																<%} %>
															<%} else { %>
																<%if( quyen[Utility.SUA]!=0 || tbList.getString("nguoitaoTB").equals(userId.trim()) ){ %>
                                	                    		<A href="../../RouterSvl?task=<%= Util.dongMa(getServletContext().getInitParameter("RedirectNoScript") +"ThongbaoUpdateSvl?task=view&id="+ tbList.getString("PK_SEQ") +"&viewMode="+ thongbao.getViewMode()+"")  %>">
			                                               				<img src="../images/Display20.png" alt="Cap nhat" title="Hiển thị" width="20" height="20" longdesc="Cap nhat" border = 0></A>
			                                               		<%} %>
			                                               		<%if(quyen[Utility.XOA]!=0 || tbList.getString("nguoitaoTB").equals(userId.trim()) ){ %>
			                                               				  <A href="../../RouterSvl?task=<%= Util.dongMa(getServletContext().getInitParameter("RedirectNoScript") +"ThongbaoSvl?task=xoa&id="+ tbList.getString("PK_SEQ")+"&viewMode="+ thongbao.getViewMode()+"")  %>" onclick="return confirm('Bạn muốn xóa thông báo đã gửi')"><img src="../images/Delete20.png" alt="Xóa" title="Xóa" width="20" height="20" longdesc="Xóa" border = 0 "></A> 
															<%} }%>                                    	                   
                                        	            </TD>
                                                   
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
													<img alt="Trang Dau" src="../images/first.gif" style="cursor: pointer;" onclick="View(document.forms[0].name, 1, 'view')"> &nbsp;
												<%}else {%>
													<img alt="Trang Dau" src="../images/first.gif" > &nbsp;
													<%} %>
												<% if(thongbao.getNxtApprSplitting() > 1){ %>
													<img alt="Trang Truoc" src="../images/prev.gif" style="cursor: pointer;" onclick="View(document.forms[0].name, eval(document.forms[0].nxtApprSplitting.value) -1, 'view')"> &nbsp;
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
												
													<a  style="color:white;"><%= listPage[i] %>/ <%=thongbao.getTheLastSplitting() %></a>
												<%}else{ %>
													<a href="javascript:View(document.forms[0].name, <%= listPage[i] %>, 'view')"><%= listPage[i] %></a>
												<%} %>
													<input type="hidden" name="list" value="<%= listPage[i] %>" />  &nbsp;
												<%} %>
												
												<% if(thongbao.getNxtApprSplitting() < thongbao.getTheLastSplitting()){ %>
													&nbsp; <img alt="Trang Tiep" src="../images/next.gif" style="cursor: pointer;" onclick="View(document.forms[0].name, eval(document.forms[0].nxtApprSplitting.value) +1, 'view')"> &nbsp;
												<%}else{ %>
													&nbsp; <img alt="Trang Tiep" src="../images/next_d.gif" > &nbsp;
												<%} %>
												<%if(thongbao.getNxtApprSplitting() == thongbao.getTheLastSplitting()) {%>
											   		<img alt="Trang Cuoi" src="../images/last.gif" > &nbsp;
										   		<%}else{ %>
										   			<img alt="Trang Cuoi" src="../images/last.gif" style="cursor: pointer;" onclick="View(document.forms[0].name, -1, 'view')"> &nbsp;
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