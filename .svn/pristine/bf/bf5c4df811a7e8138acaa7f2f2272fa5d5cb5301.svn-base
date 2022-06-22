<%@page import="geso.dms.distributor.beans.tamung.INhanvienList"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
    
<%@ page  import = "java.util.List" %>
<%@ page  import = "java.sql.ResultSet" %>
<%@ page  import = "geso.dms.center.util.*" %>

<%	String userId = (String) session.getAttribute("userId");  
	String userTen = (String) session.getAttribute("userTen");  	
	String sum = (String) session.getAttribute("sum");
	Utility util = (Utility) session.getAttribute("util");
	if(!util.check(userId, userTen, sum)){
		response.sendRedirect("/Phanam/index.jsp");
	}else{ %>

<% INhanvienList obj = (INhanvienList)session.getAttribute("obj"); %>
<% ResultSet DSNV = (ResultSet)obj.getDSNV();  
   ResultSet pbRs = obj.getPbRs();
int[] quyen = new  int[5];
quyen = util.Getquyen("NhanvienSvl","",userId);



%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"> <% Utility Utilback = new Utility(); %>
<HTML>
<HEAD>
<TITLE><%= getServletContext().getInitParameter("TITLENAME") %></TITLE>
<META http-equiv="Content-Type" content="text/html; charset=utf-8">
<link type="text/css" rel="stylesheet" href="../css/mybutton.css">
<meta http-equiv="Content-Style-Type" content="text/css">
<link rel="stylesheet" href="../css/main.css" type="text/css">
<link rel="stylesheet" href="../css/calendar.css" type="text/css">

<script type="text/javascript" src="../scripts/jquery.min.js"></script>
<script type="text/javascript" src="../scripts/dropdowncontent.js"></script>
<link href="../css/jquery-ui.css" rel="stylesheet" type="text/css" />
<script src="../scripts/ui/jquery.ui.core.js" type="text/javascript"></script>
<script src="../scripts/ui/jquery.ui.widget.js" type="text/javascript"></script>
<script src="../scripts/ui/jquery.ui.datepicker.js" type="text/javascript"></script>
<script type="text/javascript">
	$(document).ready(function() {		
			$( ".days" ).datepicker({			    
					changeMonth: true,
					changeYear: true				
			});            
        }); 		
		
</script>
	
<script type="text/javascript" language="JavaScript" src="../scripts/simplecalendar.js"></script>

<SCRIPT language="javascript" type="text/javascript">

function clearform()
{  
	document.forms['nvtuForm'].ma.value= '';
	document.forms['nvtuForm'].ten.value= '';
	document.forms['nvtuForm'].trangthai.value= '';
    document.forms['nvtuForm'].submit();
}

function submitform()
{
	document.forms['nvtuForm'].action.value= 'search';
	document.forms['nvtuForm'].submit();
}

function newform()
{
	document.forms['nvtuForm'].action.value= 'new';
	document.forms['nvtuForm'].submit();
}
function deleteform()
{
	document.forms['nvtuForm'].action.value= 'delete';
	document.forms['nvtuForm'].submit();
}
function thongbao()
{
	 var tt = document.forms["nvtuForm"].msg.value;
	 if(tt.length>0)
    	alert(document.forms["nvtuForm"].msg.value);
}
</SCRIPT>

<link href="../css/select2.css" rel="stylesheet" />
<script src="../scripts/select2.js"></script>
<script>
	$(document).ready(function()
	{
		$(".select2").select2();
	});
</script>

</HEAD>
<BODY leftmargin="0" bottommargin="0" topmargin="0" rightmargin="0">
<form name="nvtuForm" method="post" action="../../NhanvienSvl">
<input type="hidden" name="userId" value='<%=userId%>'>
<input type="hidden" name="msg" value='<%= obj.getmsg() %>'>
<input type="hidden" name="action" value='1'>
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
							   <TD align="left" colspan="2" class="tbnavigation">
							   		&nbsp;Dữ liệu nền &gt; Kế toán &gt; Nhân viên công ty
							   </TD>
							   <TD colspan="2" align="right" class="tbnavigation"><%=Utility.GLanguage("Chào mừng bạn",session,jedis) %>  <%= userTen %> &nbsp;</td> 
						    </tr>
						</table>
					</TD>
				</TR>
				<TR>
					<TD>
						<TABLE border="0" width="100%" >
							<TR>
								<TD width="100%" align="left"><FIELDSET>
									<LEGEND class="legendtitle"><%=Utility.GLanguage("Tiêu chí tìm kiếm",session,jedis) %>  </LEGEND>

									<TABLE class="tblight" width="100%" cellpadding="6" cellspacing = "0">
										<TR>
											<TD width="19%" class="plainlabel" >Mã nhân viên </TD>
										  <TD width="81%" class="plainlabel" ><INPUT name="ma" type="text" size="40" value="<%=obj.getMa() %>" onChange="submitform();" ></TD>
										</TR>

										<TR>
											<TD width="19%" class="plainlabel" >Tên nhân viên </TD>
										  <TD width="81%" class="plainlabel" ><INPUT name="ten" type="text" size="40" value="<%=obj.getTen() %>" onChange="submitform();" ></TD>
										</TR>
										
										<TR>
											<TD  class="plainlabel" >Bộ phận</TD>
											<TD class="plainlabel" >
											<SELECT class="select2" NAME = "pbId" style="width: 200px;" onChange="submitform();">
														<OPTION VALUE = "">&nbsp;</OPTION>
										<% if (pbRs != null){
												while(pbRs.next()){
													if(pbRs.getString("PBID").equals(obj.getPbId())){
											%>																	
														<OPTION VALUE = "<%= pbRs.getString("PBID") %>" SELECTED><%= pbRs.getString("PB")%></OPTION>
														
										<%			}else{ %>
													
														<OPTION VALUE = "<%= pbRs.getString("PBID") %>" ><%= pbRs.getString("PB")%></OPTION>
																
										<% 			}
												}
											}%>
											
											</SELECT>
											</TD>											
										</TR>
										
										
										<TR>
											<TD class="plainlabel"><%=Utility.GLanguage("Trạng thái",session,jedis) %></TD>
											
											<TD class="plainlabel"><select name="trangthai" onChange="submitform();" class="select2" style="width: 200px;">
											<option value =""></option>
									     	 <% if(obj.getTrangthai().equals("1")) { %>
										         	<option value="1" selected><%=Utility.GLanguage("Hoạt động",session,jedis) %></option>
				                                 	<option value="0"><%=Utility.GLanguage("Ngưng hoạt động",session,jedis) %></option>
				                                 <%} else if(obj.getTrangthai().equals("0")){ %>
					                                <option value="0"  selected><%=Utility.GLanguage("Ngưng hoạt động",session,jedis) %></option>
					                                <option value="1"><%=Utility.GLanguage("Hoạt động",session,jedis) %></option>
				                                 <%} else {%>
				                                  	<option value="1"><%=Utility.GLanguage("Hoạt động",session,jedis) %></option>
				                                  	<option value="0"><%=Utility.GLanguage("Ngưng hoạt động",session,jedis) %></option>
				                                
											        <% } %>
											
										    </select></TD>
										</TR>
										<TR>
										    <TD class="plainlabel" colspan=4>
										    <a class="button2" href="javascript:clearform()">
													<img style="top: -4px;" src="../images/button.png" alt="" onClick="clearform();"><%=Utility.GLanguage("Nhập lại",session,jedis) %></a>&nbsp;&nbsp;&nbsp;&nbsp;
	
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
					<TD align="left" >
						<FIELDSET>
							<LEGEND class="legendtitle">&nbsp;Nhân viên công ty &nbsp;&nbsp;&nbsp;&nbsp; 
							
								<a class="button3" onClick="newform();">
    							<img style="top: -4px;" src="../images/New.png"  ><%=Utility.GLanguage("Tạo mới",session,jedis) %>  </a>
    						                        
							
							
						</LEGEND>
								<TABLE class="" width="100%" border="0" cellpadding="0" cellspacing="0">
								<TR>
									<TD width="98%">
										<TABLE width="100%" border="0" cellspacing="1" cellpadding="4">
											<TR class="tbheader">
											<TH width="6%">Mã</TH>
											<TH width="20%">Tên</TH>
											<TH width="10%">Email</TH>
											<TH width="8%">Điện thoại</TH>
											<TH width="10%"><%=Utility.GLanguage("Trạng thái",session,jedis) %></TH>
											<TH width="10%">Phòng ban</TH>
											<TH width="15%"><%=Utility.GLanguage("Người sửa",session,jedis) %></TH>
											<TH width="8%"><%=Utility.GLanguage("Ngày sửa",session,jedis) %></TH>
											<TH width="5%"><%=Utility.GLanguage("Tác vụ",session,jedis) %></TH>
										</TR>
								     <%
								        String lightrow = "tblightrow";
	                                    String darkrow = "tbdarkrow";
	                                   int i =0; 
								     while(DSNV.next()) 
								     {
								    	 if (i % 2 != 0) {%>                     
                                         <TR align="center" align = "left" class=<%=lightrow%> >
                                     <%} else { %>
                                         <TR align="center" align = "left" class=<%= darkrow%> >
                                     <%}%>
                                     		<TD ><%=DSNV.getString("Ma") %></TD>
								     		<TD align = "Left"><%=DSNV.getString("Ten") %></TD>												
											<TD ><%=DSNV.getString("email") %></TD>

											<TD ><%=DSNV.getString("dienthoai") %></TD>
										<%  if(DSNV.getString("trangthai").equals("1")) {%>
										     <TD ><%=Utility.GLanguage("Hoạt động",session,jedis) %></TD>
										     <%} else { %>
										     <TD >Kết thúc</TD>
										     <%}%>
										     <TD ><%=DSNV.getString("tenpb") %></TD>
											<TD ><%=DSNV.getString("nguoisua") %></TD>
											<TD ><%=DSNV.getString("ngaysua") %></TD>
											<TD align="center">
                                                <TABLE border = 0 cellpadding="0" cellspacing="0">
                                                    <TR>
                                                    <TD>
                                                    	
                                                     	<A href = "../../NhanvienUpdateSvl?userId=<%=userId%>&update=<%=DSNV.getString("pk_seq") %>"><img title="Cập nhật" src="../images/Edit20.png" alt="Cap nhat" width="20" height="20" longdesc="Cap nhat" border = 0></A>&nbsp;
                                                     	
                                                    </TD>
                                                    <TD>
                                                    	
                                                        <A href="../../NhanvienUpdateSvl?userId=<%=userId%>&delete=<%=DSNV.getString("pk_seq") %>"><img title="Xóa" src="../images/Delete20.png" alt="Xoa" width="20" height="20" longdesc="Xoa" border=0 onclick="if(!confirm('Bạn có muốn xóa nhân viên này?')) return false;"></A>&nbsp;
                                                       
                                                    </TD>
                                                    <TD>
                                                    	<!-- QUYEN VIEW DLN -->  
                                                     	<A href = "../../NhanvienUpdateSvl?userId=<%=userId%>&display=<%=DSNV.getString("pk_seq") %>"><img src="../images/Display20.png" title="Hiển thị" alt="Hien thi" width="20" height="20" longdesc="Hien thi" border = 0></A>
                                                     	<!-- END QUYEN VIEW DLN -->
                                                    </TD>
                                                    </TR></TABLE>
                                                </TD>
								                 </TR>
							          <% i++;
							          } %>
							          
								<TR>
									<TD align="center" colspan="11" class="tbfooter">&nbsp;</TD>
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
	if(DSNV!=null){
		DSNV.close();
	}
	if(obj!=null){
		obj.DbClose();
	}
	
	session.setAttribute("obj",null);
}%>