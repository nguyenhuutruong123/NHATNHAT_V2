<%@page import="java.sql.SQLException"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page  import = "geso.dms.center.beans.chungloai.IChungloaiList" %>
<%@ page  import = "java.sql.ResultSet" %>
<%@ page  import = "geso.dms.center.util.*" %>
<%	String userId = (String) session.getAttribute("userId");  
	String userTen = (String) session.getAttribute("userTen");  	
	String sum = (String) session.getAttribute("sum");
	Utility util = (Utility) session.getAttribute("util");
	if(!util.check(userId, userTen, sum)){
		response.sendRedirect(request.getContextPath() + "/redirect.jsp");
	}else{ %>

<% IChungloaiList obj = (IChungloaiList)session.getAttribute("obj"); %>
<% ResultSet cllist = (ResultSet)obj.getClList() ; %>
<% ResultSet nhList = (ResultSet)obj.getNhList(); 
int[] quyen = new  int[4];
	quyen = util.Getquyen("ChungloaiSvl","",userId);
	
	System.out.println(quyen[0]);
	System.out.println(quyen[1]);
	System.out.println(quyen[2]);
	System.out.println(quyen[3]);
	String url = util.getChuyenNguUrl("ChungloaiSvl", "",session);
%>
<% Utility Util = new Utility(); %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"> <% Utility Utilback = new Utility(); %>

<HTML>
<HEAD>
<TITLE><%= getServletContext().getInitParameter("TITLENAME") %></TITLE>
	<link type="text/css" rel="stylesheet" href="../css/mybutton.css">
<META http-equiv="Content-Style-Type" content="text/css">
<LINK rel="stylesheet" href="../css/main.css" type="text/css">
<LINK rel="stylesheet" href="../css/calendar.css" type="text/css">

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
    document.clForm.chungloai.value = "";
    document.clForm.nhId.selectedIndex = 0;
    document.clForm.tungay.value = "";
	document.clForm.denngay.value = "";       
    document.clForm.trangthai.selectedIndex = 0;
    submitform();
}

function submitform()
{
	document.forms['clForm'].action.value= 'search';
	document.forms['clForm'].submit();
}

function newform()
{
	document.forms['clForm'].action.value= 'new';
	document.forms['clForm'].submit();
}
function thongbao()
{var tt = document.forms['clForm'].msg.value;
	if(tt.length>0)
    alert(document.forms['clForm'].msg.value);
	}


function xuatExcel()
{
	document.forms['clForm'].action.value= 'excel';
	document.forms['clForm'].submit();
	document.forms['clForm'].action.value= '';
}
</SCRIPT>
</HEAD>
<BODY leftmargin="0" bottommargin="0" topmargin="0" rightmargin="0">
<form name="clForm" method="post" action="../../ChungloaiSvl">
<% redis.clients.jedis.Jedis jedis =  geso.dms.center.util.Utility.getJedis(); %><% geso.dms.center.util.Csrf csdr=new geso.dms.center.util.Csrf(request,response,true,false,true);%>
 <input type="hidden" name="<%=csdr.get_tokenName() %>" value='<%=csdr.get_tokenValue() %>'>

<input type="hidden" name="userId" value='<%=userId%>'>
<input type="hidden" name="action" value='1'>
<input type="hidden" name="msg" value='<%=Utility.GLanguage(obj.getMsg(),session,jedis) %>'>

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
						   <TD align="left" colspan="2" class="tbnavigation"><%= url %> </TD>
   
						   <TD colspan="2" align="right" class="tbnavigation"><%=Utility.GLanguage("Chào mừng bạn",session,jedis) %>  <%=userTen %>&nbsp;</TD>
						  </tr>
 					  </table>
					</TD>
				</TR>
				<TR>
					<TD>
					<TABLE border="0" width="100%" cellpadding="0" cellspacing="0">
						<TR>
							<TD width="100%" align="center" >
							<FIELDSET>
							  <LEGEND class="legendtitle"><%=Utility.GLanguage("Tiêu chí tìm kiếm",session,jedis) %> &nbsp;</LEGEND>
							<TABLE  width="100%" cellpadding="6" cellspacing="0">
								<tr>
								  	<TD class="plainlabel"><%=Utility.GLanguage("Chủng loại",session,jedis) %> </TD>
								    <TD class="plainlabel"><input type="text" name="chungloai" value="<%=obj.getCloai() %>" onChange="submitform();"></TD>
						      	</TR>
						      	<TR>
									<TD width="19%" class="plainlabel"><%=Utility.GLanguage("Nhãn hàng",session,jedis) %> </TD>
  								  	<TD class="plainlabel" >
										<TABLE cellpadding="0" cellspacing="0" border="0">
											<TR>
										  		<TD><SELECT  name="nhId" onChange="submitform();">
										  			<option value=""  ></option>
										  			<%try{ %>
											  			<%while(nhList.next()){ %>	
												  			<%if(nhList.getString("pk_seq").equals(obj.getNhId())){ %>
												  				<option value="<%=nhList.getString("pk_seq")%>" selected="selected"><%=nhList.getString("ten")%></option>
															<%}else {%>
																<option value="<%=nhList.getString("pk_seq")%>" ><%=nhList.getString("ten")%></option>	
																<%}} %>
													<%}catch(SQLException ex){} %>										                                           
                                              			</SELECT>
                                         			</TD>
											</TR>
										</TABLE>									
									</TD>

								</TR>
								<TR>
								  	<TD class="plainlabel" ><%=Utility.GLanguage("Từ ngày",session,jedis) %> </TD>
								  	<TD class="plainlabel" >
										<TABLE cellpadding="0" cellspacing="0">
											<TR><TD>
												<input class="days" type="text" name="tungay" size="20" value = "<%=obj.getTungay()%>" onchange="submitform();">
												</TD>
												
                                    		</TR>
										</TABLE>
									</TD>
								<TR>
								<TR>
									<TD class="plainlabel" ><%=Utility.GLanguage("Đến ngày",session,jedis) %></TD>
								  	<TD class="plainlabel" >
							  			<TABLE cellpadding="0" cellspacing="0">
							  				<TR>
							  					<TD>
													<input class="days" type="text" name="denngay" size="20" value = "<%=obj.getDenngay()%>" onchange="submitform();">
												</TD>
												
                                     		</TR>
										</TABLE>
									</TD>
								</TR>
								<TR>
								    <TD class="plainlabel"><%=Utility.GLanguage("Trạng thái",session,jedis) %> </TD>
									<TD class="plainlabel"><select name="trangthai" onChange="submitform();">
											<option value="2"> </option>
										<% if (obj.getTrangthai().equals("1")){ %>
										  	<option value="1" selected ><%=Utility.GLanguage("Hoạt động",session,jedis) %></option>
										  	<option value="0"><%=Utility.GLanguage("Ngưng hoạt động",session,jedis) %></option>
										 <%}else{ %>
											<% if (obj.getTrangthai().equals("0")){ %>
										  		<option value="1"  ><%=Utility.GLanguage("Hoạt động",session,jedis) %></option>
										  		<option value="0" selected><%=Utility.GLanguage("Ngưng hoạt động",session,jedis) %></option>
										 	<%}else{ %>
												<option value="1"  ><%=Utility.GLanguage("Hoạt động",session,jedis) %></option>
										  		<option value="0"  ><%=Utility.GLanguage("Ngưng hoạt động",session,jedis) %></option>
										 	<%}}%>										 
										    </select>
						      	</TR>
							    <TR>
									<TD colspan="2" class="plainlabel">
										<a class="button2" href="javascript:clearform()"> <img style="top: -4px;" src="../images/button.png" alt=""><%=Utility.GLanguage("Nhập lại",session,jedis) %></a>&nbsp;&nbsp;&nbsp;&nbsp;
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

				<TR>
					<TD width="100%">
						<FIELDSET>
						<LEGEND class="legendtitle"><%=Utility.GLanguage("Chủng loại",session,jedis) %> 
						<%if(quyen[0]!=0) {%>
						<a class="button3" href="javascript:newform()">
    	<img style="top: -4px;" src="../images/New.png" alt=""><%=Utility.GLanguage("Tạo mới",session,jedis) %> </a>    
    	<%} %>                        
																		</LEGEND>
	
		   				<TABLE width="100%" border="0" cellpadding="0" cellspacing="0">
			  				<TR>
			  	   				<TD align="left" colspan="4" class="legendtitle">
									<TABLE width="100%" border="0" cellspacing="1" cellpadding="4">
										<TR class="tbheader">
											<TH width="4%"><%=Utility.GLanguage("STT",session,jedis) %></TH>
											<TH width="13%"><%=Utility.GLanguage("Mã",session,jedis) %> </TH>
											<TH width="13%"><%=Utility.GLanguage("Chủng loại",session,jedis) %> </TH>
											<TH width="15%"><%=Utility.GLanguage("Nhãn hàng",session,jedis) %> </TH>
											<TH width="13%"><%=Utility.GLanguage("Trạng thái",session,jedis) %> </TH>
											<TH width="9%"><%=Utility.GLanguage("Ngày tạo",session,jedis) %> </TH>
											<TH width="13%"><%=Utility.GLanguage("Người tạo",session,jedis) %></TH>
											<TH width="9%"><%=Utility.GLanguage("Ngày sửa",session,jedis) %> </TH>
											<TH width="13%"><%=Utility.GLanguage("Người sửa",session,jedis) %></TH>
											<TH width="11%" align="center">&nbsp;<%=Utility.GLanguage("Tác vụ",session,jedis) %></TH>
										</TR>
									
								<% 
								int m = 0;
								String lightrow = "tblightrow";
								String darkrow = "tbdarkrow";
								while (cllist.next()){
									if (m % 2 != 0) {%>						
										<TR class= <%=lightrow%> >
									<%} else {%>
										<TR class= <%= darkrow%> >
									<%}%>
											<TD align="center"><%=m+1%></TD>
											<TD align="left"><%=cllist.getString("ma") %></TD>      
											<TD align="left"><%=cllist.getString("chungloai") %></TD>                                   
											<TD><%=cllist.getString("nhanhang") %></TD>

									<% if (cllist.getString("trangthai").equals("1")){ %>
											<TD align="left"><%=Utility.GLanguage("Hoạt động",session,jedis) %></TD>
									<%}else{%>
											<TD align="left"><%=Utility.GLanguage("Ngưng hoạt động",session,jedis) %></TD>
									<%}%>

											<TD align="center"><%=cllist.getString("ngaytao") %></TD>
											<TD align="center"><%=cllist.getString("nguoitao")%></TD>
											<TD align="center"><%=cllist.getString("ngaysua") %></TD>
											<TD align="center"><%=cllist.getString("nguoisua") %> </TD>
											<TD align="center">
												<TABLE border = 0 cellpadding="0" cellspacing="0">
													<TR><TD>
													<%if(quyen[2]!=0) {%>
															<A href = "../../RouterSvl?task=<%= Util.dongMa(getServletContext().getInitParameter("RedirectNoScript") +"ChungloaiUpdateSvl?userId="+userId+"&update=1&chId="+cllist.getString("clId")+"")%>"><img src="../images/Edit20.png" alt="Chinh sua" width="20" height="20" longdesc="Chinh sua" border = 0></A>
														<%} %>
														</TD>
														<TD>
															<%if(quyen[Utility.XEM]!=0) {%>
															<A href = "../../RouterSvl?task=<%= Util.dongMa(getServletContext().getInitParameter("RedirectNoScript") +"ChungloaiUpdateSvl?userId="+userId+"&display=1&chId="+cllist.getString("clId")+"")%>"><img src="../images/Display20.png" alt="hien thi" width="20" height="20" longdesc="hien thi" border = 0></A>
														<%} %>
														</TD>
														</TD>
														<TD>
														<%if(quyen[1]!=0) {%>
															<A href = "../../RouterSvl?task=<%= Util.dongMa(getServletContext().getInitParameter("RedirectNoScript") +"ChungloaiSvl?userId="+userId+"&delete="+cllist.getString("clId")+";"+cllist.getString("nhId")+"") %>"><img src="../images/Delete20.png" alt="Xoa" width="20" height="20" longdesc="Xoa" border=0 onclick="if(!confirm('Bạn có muốn xóa Chủng loại này không ?')) return false;"></A></TD>
														<%} %>
													</TR>
												</TABLE>											
											</TD>
										</TR>
									<% 	m++;
									}%>
							
										<TR>
											<TD height="26" colspan="11" align="center" class="tbfooter">&nbsp;	</TD>
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
</BODY>
</HTML>
<% if(nhList != null) nhList.close(); %>
<% if(cllist != null) cllist.close(); %>
<%
if(obj != null){
   obj.DBClose();
 	obj = null;  
}
	}%>