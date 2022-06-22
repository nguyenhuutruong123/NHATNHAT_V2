<%@page import="geso.dms.center.beans.capnhatnhanvien.ICapnhatnhanvienList"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
    
<%@ page  import = "geso.dms.center.beans.nhacungcap.INhacungcap" %>
<%@ page  import = "geso.dms.center.beans.nhacungcap.INhacungcapList" %>
<%@ page  import = "java.util.List" %>
<%@ page  import = "java.sql.ResultSet" %>
<%@ page  import = "geso.dms.center.util.*" %>

<%	String userId = (String) session.getAttribute("userId");  
	String userTen = (String) session.getAttribute("userTen");  	
	String sum = (String) session.getAttribute("sum");
	Utility util = (Utility) session.getAttribute("util");
	if(!util.check(userId, userTen, sum)){
		response.sendRedirect(request.getContextPath() + "/redirect.jsp");
	}else{
		int[] quyen = new  int[6];
		quyen = util.Getquyen("CapnhatnhanvienSvl","",userId);
		String url = util.getChuyenNguUrl("CapnhatnhanvienSvl", "",session);
		%>

<% ICapnhatnhanvienList obj = (ICapnhatnhanvienList)session.getAttribute("obj"); %>
<% ResultSet DSNV = (ResultSet)obj.getDSNV(); %>
<% Utility Util = new Utility(); %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"> <% Utility Utilback = new Utility(); %>
<HTML>
<HEAD>
<TITLE><%= getServletContext().getInitParameter("TITLENAME") %></TITLE>
<META http-equiv="Content-Type" content="text/html; charset=utf-8">

<META http-equiv="Content-Style-Type" content="text/css">
<LINK rel="stylesheet" type="text/css" media="print" href="../css/impression.css">
<LINK rel="stylesheet" href="../css/main.css" type="text/css">
<LINK rel="stylesheet" href="../css/calendar.css" type="text/css">
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
	
<!-- <script type="text/javascript" language="JavaScript" src="../scripts/simplecalendar.js"></script> -->

<SCRIPT language="javascript" type="text/javascript">

function clearform()
{  document.forms['nccForm'].action.value= 'xoa';
     document.forms['nccForm'].submit();
}

function submitform()
{
	document.forms['nccForm'].action.value= 'search';
	document.forms['nccForm'].submit();
}

function newform()
{
	document.forms['nccForm'].action.value= 'new';
	document.forms['nccForm'].submit();
	document.forms['nccForm'].submit();
}
function deleteform()
{
	document.forms['nccForm'].action.value= 'delete';
	document.forms['nccForm'].submit();
}

function xuatExcel()
{
	document.forms['nccForm'].action.value= 'toExcel';
	document.forms['nccForm'].submit();
	document.forms['nccForm'].action.value= '';
}

</SCRIPT>

</HEAD>
<BODY leftmargin="0" bottommargin="0" topmargin="0" rightmargin="0">
<form name="nccForm" method="post" action="../../CapnhatnhanvienSvl">
<% redis.clients.jedis.Jedis jedis =  geso.dms.center.util.Utility.getJedis(); %><% geso.dms.center.util.Csrf csdr=new geso.dms.center.util.Csrf(request,response,true,false,true);%>
 <input type="hidden" name="<%=csdr.get_tokenName() %>" value='<%=csdr.get_tokenValue() %>'>

<input type="hidden" name="userId" value='<%=userId%>'>
<input type="hidden" name="action" value='1'>

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
							   		<%=url %>
							   </TD>
							   <TD colspan="2" align="right" class="tbnavigation"><%=Utility.GLanguage("Chào mừng bạn",session,jedis) %>  <%=userTen %> &nbsp;</td> 
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
											<TD width="19%" class="plainlabel" > <%=Utility.GLanguage("Tên nhân viên",session,jedis) %></TD>
										  <TD width="81%" class="plainlabel" ><INPUT name="ten" type="text" size="40" value="<%=obj.getTen() %>" onChange="submitform();" ></TD>
										</TR>
										<TR>
											<TD class="plainlabel"><%=Utility.GLanguage("Phân loại",session,jedis) %></TD>
											
											<TD class="plainlabel"><select name="phanloai" onChange="submitform();">
											   <option value =""></option>
									     	 <% if(obj.getPhanloai().equals("1")) { %>
										         <option value="1" selected><%=Utility.GLanguage("Nhà phân phối",session,jedis) %></option>
				                                 <option value="2">Trung tâm</option>
				                                 <%} else  if(obj.getPhanloai().equals("2")){ %>
					                                  <option value="2" selected><%=Utility.GLanguage("Trung tâm",session,jedis) %></option>
					                                  <option value="1"><%=Utility.GLanguage("Nhà phân phối",session,jedis) %></option>
				                                 <%} else {%>
				                                      <option value="2"><%=Utility.GLanguage("Trung tâm",session,jedis) %></option>
					                                  <option value="1"><%=Utility.GLanguage("Nhà phân phối",session,jedis) %></option>
											 <%} %>
											
										    </select></TD>
										</TR>
										<TR>							
											<TD class="plainlabel" ><%=Utility.GLanguage("Từ ngày",session,jedis) %> </TD>
											<TD class="plainlabel" colspan="3">
												<TABLE cellpadding="0" cellspacing="0">
											<TR><TD>
										<input class="days" type="text" name="tungay" value='<%=obj.getTungay() %>'  size="20" onchange=submitform(); >
												</TD>
												
		   										</TR>
												</TABLE>
																						
		  									</TD>
										</TR>
										<TR>
                                          <TD class="plainlabel" ><%=Utility.GLanguage("Đến ngày",session,jedis) %> </TD>
										  <TD class="plainlabel" colspan="3">
										  		<TABLE cellpadding="0" cellspacing="0"><TR><TD>
										 <input class="days" type="text" name="denngay" value='<%=obj.getDenngay() %>' size="20" onchange=submitform(); >
										  		</TD>
										

											  </TR>
											 </TABLE>
									  	</TR>
										<TR>
											<TD class="plainlabel"><%=Utility.GLanguage("Trạng thái",session,jedis) %></TD>
											
											<TD class="plainlabel"><select name="trangthai" onChange="submitform();">
											<option value =""></option>
									     	 <% if(obj.getTrangthai().equals("1")) { %>
										         <option value="1" selected><%=Utility.GLanguage("Hoạt động",session,jedis) %></option>
				                                 <option value="0"><%=Utility.GLanguage("Kết thúc",session,jedis) %></option>
				                                 <%} else if(obj.getTrangthai().equals("0")){ %>
					                                  <option value="0"  selected><%=Utility.GLanguage("Kết thúc",session,jedis) %></option>
					                                  <option value="1"><%=Utility.GLanguage("Hoạt động",session,jedis) %></option>
				                                 <%} else {%>
				                                  <option value="1"><%=Utility.GLanguage("Hoạt động",session,jedis) %></option>
				                                  <option value="0"><%=Utility.GLanguage("Kết thúc",session,jedis) %></option>
				                                
											        <% } %>
											
										    </select></TD>
										</TR>
										<TR>
										    <TD class="plainlabel" colspan=4>
										    <a class="button2" href="javascript:clearform()">
	<img style="top: -4px;" src="../images/button.png" alt="" onClick="clearform();"><%=Utility.GLanguage("Nhập lại",session,jedis) %></a>&nbsp;&nbsp;&nbsp;&nbsp;
		<a class="button2" href="javascript:xuatExcel()"> <img style="top: -4px;" src="../images/button.png" alt=""><%=Utility.GLanguage("Xuất Excel",session,jedis) %> </a>
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
							<LEGEND class="legendtitle">&nbsp;<%=Utility.GLanguage("Nhân viên",session,jedis) %> &nbsp;&nbsp;&nbsp;&nbsp;   
							<%if(quyen[Utility.THEM]!=0){ %>
							<a class="button3" onClick="newform();">
    	<img style="top: -4px;" src="../images/New.png"  ><%=Utility.GLanguage("Tạo mới",session,jedis) %>  </a>                            
							<%} %>
							
						</LEGEND>
								<TABLE class="" width="100%" border="0" cellpadding="0" cellspacing="0">
								<TR>
									<TD width="98%">
										<TABLE width="100%" border="0" cellspacing="1" cellpadding="4">
											<TR class="tbheader">
											<TH width="20%"><%=Utility.GLanguage("Họ tên",session,jedis) %></TH>
											<TH width="5%"><%=Utility.GLanguage("Đăng nhập",session,jedis) %></TH>
											<TH width="10%"><%=Utility.GLanguage("Email",session,jedis) %></TH>
											<TH width="10%"><%=Utility.GLanguage("Điện thoại",session,jedis) %></TH>
											<TH width="10%"><%=Utility.GLanguage("Trạng thái",session,jedis) %></TH>
											<TH width="10%"><%=Utility.GLanguage("Phân loại",session,jedis) %></TH>
											<TH width="8%"><%=Utility.GLanguage("Người tạo",session,jedis) %></TH>
											<TH width="8%"><%=Utility.GLanguage("Người sửa",session,jedis) %></TH>
											<TH width="8%"><%=Utility.GLanguage("Ngày tạo",session,jedis) %></TH>
											<TH width="8%"><%=Utility.GLanguage("Ngày sửa",session,jedis) %></TH>
											<TH width="8%"><%=Utility.GLanguage("Tác vụ",session,jedis) %></TH>
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
								     		<TD width="20%" align = "left" ><%=DSNV.getString("Ten") %></TD>
											<TD width="5%"><%=DSNV.getString("dangnhap") %></TD>
											<TD width="10%"><%=DSNV.getString("email") %></TD>
											<TD width="10%"><%=DSNV.getString("dienthoai") %></TD>
										<% if(DSNV.getString("trangthai").equals("1")) {%>
										    <TD width="10%"><%=Utility.GLanguage("Hoạt động",session,jedis) %></TD>
										<%} else { %>
										    <TD width="10%"><%=Utility.GLanguage("Ngưng hoạt động",session,jedis) %></TD>
										<%}%>
										
										<% if(DSNV.getString("phanloai").equals("1")) { %>
										   	<TD width="10%"><%=Utility.GLanguage("Nhà phân phối",session,jedis) %></TD>
										<%} else { %>
										   	<TD width="10%">Trung tâm</TD>
										<%} %>
										
											<TD width="6%"><%=DSNV.getString("nguoitao1") %></TD>
											<TD width="6%"><%=DSNV.getString("nguoisua1") %></TD>
											<TD width="6%"><%=DSNV.getString("ngaytao") %></TD>
											<TD width="6%"><%=DSNV.getString("ngaysua") %></TD>
											<TD align="center">
                                                <TABLE border = 0 cellpadding="0" cellspacing="0">
                                                    <TR><TD>
                                                    <%if(quyen[Utility.SUA]!=0){ %>
                                                     	<A href = "../../RouterSvl?task=<%= Util.dongMa(getServletContext().getInitParameter("RedirectNoScript") +"CapnhatnhanviennewSvl?userId="+userId+"&update="+DSNV.getString("pk_seq")+"") %>"><img src="../images/Edit20.png" alt="Cap nhat" title="Cập nhật" width="20" height="20" longdesc="Cap nhat" border = 0></A>
                                                   	<%} %>
                                                    </TD>
                                                    <TD>&nbsp;</TD>
                                                    <TD>
                                                     <%if(quyen[Utility.XOA]!=0){ %>
                                                        <A href="../../RouterSvl?task=<%= Util.dongMa(getServletContext().getInitParameter("RedirectNoScript") +"CapnhatnhanviennewSvl?userId="+userId+"&delete="+DSNV.getString("pk_seq")+"") %>"><img src="../images/Delete20.png" alt="Xoa" title="Xóa" width="20" height="20" longdesc="Xoa" border=0 onclick="if(!confirm('Bạn có muốn xóa nhân viên này?')) return false;"></A>
                                                    <%} %>
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
	
</TABLE><%geso.dms.center.util.Utility.JedisClose(jedis); %>
</form>
</BODY>
</HTML>
<%
try{
	if(DSNV!=null){
		DSNV.close();
	}
	obj.DbClose();
}catch(Exception er){
	
}


}%>