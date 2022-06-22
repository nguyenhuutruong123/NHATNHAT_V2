<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page  import = "java.util.Iterator" %>
<%@ page  import = "java.util.List" %>
<%@ page  import = "java.util.ArrayList" %>
<%@ page  import = "geso.dms.center.beans.duyettradonhang.*" %>
<%@ page  import = "java.sql.ResultSet" %>
<%@ page  import = "geso.dms.center.util.*" %>
<% IDuyettradonhangList obj = (IDuyettradonhangList)session.getAttribute("obj"); %>
<% ResultSet npp = (ResultSet)obj.getNppRs(); %>
<% ResultSet dtdhList = (ResultSet)obj.getDspList(); %>

<% String userId = (String) session.getAttribute("userId");  
	String userTen = (String) session.getAttribute("userTen"); 
	String sum = (String) session.getAttribute("sum");
	Utility util = (Utility) session.getAttribute("util");
	
	if(!util.check(userId, userTen, sum)){
		response.sendRedirect(request.getContextPath() + "/redirect.jsp");
	}else{
	int[] quyen = new  int[5];
	quyen = util.Getquyen("28",userId);
	
	System.out.println(quyen[0]);
	System.out.println(quyen[1]);
	System.out.println(quyen[2]);
	System.out.println(quyen[3]);
	System.out.println(quyen[4]);%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"> <% Utility Utilback = new Utility(); %>
<HTML>
<HEAD>
<TITLE><%= getServletContext().getInitParameter("TITLENAME") %></TITLE>
<META http-equiv="Content-Type" content="text/html; charset=utf-8">
<META http-equiv="Content-Style-Type" content="text/css">

<LINK rel="stylesheet" type="text/css" media="print" href="../css/impression.css">
<LINK rel="stylesheet" href="../css/main.css" type="text/css">
	<link type="text/css" rel="stylesheet" href="../css/mybutton.css">
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
 function confirmLogout()
 {
    if(confirm("Ban co muon dang xuat?"))
    {
		top.location.href = "home.jsp";
    }
    return
  }
 function clearform()
 {
	 
	 document.forms["dtdh"].action1.value="guilai";
	 document.forms["dtdh"].submit();
 }
 function newform()
 {
	 document.forms["dtdh"].action.value="taomoi";
	 document.forms["dtdh"].submit();
 }
 function submitform()
 {   
    document.forms["dtdh"].submit();
 }
</SCRIPT>
</HEAD>
<BODY leftmargin="0" bottommargin="0" topmargin="0" rightmargin="0">
<form name="dtdh" method="post" action="../../DuyettradonhangSvl">
<input type="hidden" name="action" value="1" >
<input type="hidden" name="userId" value="<%= userId %>" >
<input type="hidden" name="nppId" value="<%= obj.getNppId() %>" >
<TABLE width="100%" border="0" cellspacing="0" cellpadding="0" height="100%">
	<TR>
		<TD colspan="4" align='left' valign='top' bgcolor="#FFFFFF">
			<TABLE width="100%" border="0" cellpadding="0">
				<TR>
					<TD align="left" class="tbnavigation">
					   <TABLE width="100%" border="0" cellpadding="0" cellspacing="0">
						  <tr height="22">
							   <TD align="left" colspan="2" class="tbnavigation"> &nbsp;Quản lý kho chi nhánh > Duyệt hàng trả về đối tác > Duyệt trả đơn hàng
							   <TD colspan="2" align="right" class="tbnavigation">Chào mừng  <%= userTen %>&nbsp;</TD></tr>
						</TABLE>
					</TD>
		  		</TR>
				<TR>
				<TD >
				<FIELDSET><LEGEND class="legendtitle">&nbsp;<%=Utility.GLanguage("Tiêu chí tìm kiếm",session,jedis) %></LEGEND>
							<TABLE width="100%" cellpadding="6" cellspacing="0">
								
								<TR>
									<TD class="plainlabel"><%=Utility.GLanguage("Nhà phân phối",session,jedis) %> </TD>
									<TD class="plainlabel"><SELECT name="nppTen" onChange = "submitform();">
										  <option value=""> </option>
										  <% try{ while(npp.next()){ 
								    			if(npp.getString("nppId").equals(obj.getNppId())){ %>
								      				<option value='<%=npp.getString("nppId")%>' selected><%= npp.getString("nppTen") %></option>
								      			<%}else{ %>
								     				<option value='<%=npp.getString("nppId")%>'><%= npp.getString("nppTen") %></option>
								     			<%}} npp.close(); }catch(java.sql.SQLException e){} %>	 
                                    </SELECT></TD>
							   		<TD class="plainlabel" ><%=Utility.GLanguage("Từ ngày",session,jedis) %></TD>
									<TD class="plainlabel"><TABLE cellpadding="0" cellspacing="0">
											<TR><TD>
											    <input class="days" type="text" name="tungay" size="11"  value="<%= obj.getTungay() %>" onchange="submitform();">
											</TD></TR>
										</TABLE>	
							    </TR>
								
								<TR>
									<TD class="plainlabel"><%=Utility.GLanguage("Trạng thái",session,jedis) %> </TD>
									<TD class="plainlabel">
										<select name="trangthai" onChange="submitform();">
											    <% if (obj.getTrangthai().equals("1")){%>
											  	<option value="1" selected>Đang chờ duyệt</option>
											  	<option value="3">Đã duyệt</option>
											  	<option value=""></option>
	
											<%}else if(obj.getTrangthai().equals("3")) { %>
												<option value="3" selected>Đã duyệt</option>
											  	<option value="1" >Đang chờ duyệt</option>											
											  	<option value=""></option>
											  	
											<%} else{ %>
												<option value="" selected></option>
											  	<option value="1" >Đang chờ duyệt</option>										
											  	<option value="3">Đã duyệt</option>
											<% } %>
									          </select>
									</TD>
									<TD class="plainlabel" ><%=Utility.GLanguage("Đến ngày",session,jedis) %></TD>
							      	<TD class="plainlabel">
								  		<TABLE cellpadding="0" cellspacing="0">
										  	<TR><TD>
											  <input class="days" type="text" name="denngay" size="11" value="<%= obj.getDenngay() %>" onchange="submitform();">
										  	</TD></TR>
										</TABLE>
									</TD>
							    </TR>
								
								
								<TR>
									<TD class="plainlabel" colspan="4">
									<a class="button2" >
										<img style="top: -4px;" src="../images/Search30.png" alt=""><%=Utility.GLanguage("Tìm kiếm",session,jedis) %> </a>&nbsp;&nbsp;&nbsp;&nbsp;	
									<a class="button2" href="javascript:clearform()">
										<img style="top: -4px;" src="../images/button.png" alt=""><%=Utility.GLanguage("Nhập lại",session,jedis) %></a>&nbsp;&nbsp;&nbsp;&nbsp;
									</TD>		
								</TR>
							</TABLE>
				  </FIELDSET>
							</TD>	
				</TR>

				<TR>
					<TD width="100%">
					<FIELDSET>
					<LEGEND class="legendtitle">&nbsp;Duyệt trả đơn hàng &nbsp;&nbsp;&nbsp;
						<%if(quyen[0]!=0) {%>
					<a class="button3" onclick="newform()">
    					<img style="top: -4px;" src="../images/New.png" ><%=Utility.GLanguage("Tạo mới",session,jedis) %> </a> 
    					<%} %>
					</LEGEND>
					<TABLE class="" width="100%">
						<TR>
							<TD width="98%">
							<TABLE width="100%" border="0" cellspacing="1" cellpadding="3">
								<TR class="tbheader">
									<TH width="10%" align="center">Mã đơn hàng</TH>
									<TH width="20%" align="center"><%=Utility.GLanguage("Nhà phân phối",session,jedis) %></TH>
									<TH width="10%" align="center">Mã khách hàng</TH>
									<TH width="10%" align="center">Tên khách hàng</TH>
									<TH width="10%" align="center">Ngày nhập</TH>
									<TH width="10%" align="center"><%=Utility.GLanguage("Trạng thái",session,jedis) %></TH>
									<TH width="10%" align="center">Ngày duyệt</TH>
									<TH width="10%" align="center">Người duyệt</TH>
									<TH width="10%" align="center"><%=Utility.GLanguage("Tác vụ",session,jedis) %></TH>
								</TR>
								
								<% 
									int m = 0;
									String lightrow = "tblightrow";
									String darkrow = "tbdarkrow";
									if(dtdhList != null){
									try{
									while (dtdhList.next()){
										if (m % 2 != 0) {%>						
											<TR class= <%=lightrow%> >
										<%} else {%>
											<TR class= <%= darkrow%> >
										<%}%>
												
										<TD align="center"><%= dtdhList.getString("dhtvId") %></TD> 
										<TD align="left"><%= dtdhList.getString("nppTen") %></TD>                                  
										<TD align="center"><%= dtdhList.getString("khId") %></TD>
										<TD align="left"><%= dtdhList.getString("khTen") %></TD>
										<TD align="center"><%= dtdhList.getString("ngaynhap") %></TD>
	
										<%
										String trangthai = dtdhList.getString("trangthai");
										if (trangthai.equals("1")){ %>
											<TD align="left"><i>Đang chờ duyệt</i></TD>
										<%}else{%> 
											<TD align="left"><i>Đã duyệt</i></TD>
										<%}%>
										
										<TD align="center"><%= dtdhList.getString("ngayduyet") %></TD>
										<TD align="left"><%= dtdhList.getString("nguoiduyet")%></TD>
										<TD align="center">

										<% if(dtdhList.getString("trangthai").equals("1")){ %>
											<%if(quyen[4]!=0){ %>	
											<A href = "../../DuyettradonhangUpdateSvl?userId=<%=userId%>&duyet=<%= dtdhList.getString("dhtvId") %>&nppId=<%= dtdhList.getString("nppId") %>"><img src="../images/Chot.png" alt="Duyet" width="20" height="20" longdesc="Duyet" border = 0></A>
											<%} %>
										<%}else{ %>
											<%if(quyen[3]!=0){ %>
											<A href = "../../DuyettradonhangUpdateSvl?userId=<%=userId%>&display=<%= dtdhList.getString("dhtvId") %>&nppId=<%= dtdhList.getString("nppId") %>"><img src="../images/Display20.png" alt="Hien thi" width="20" height="20" longdesc="Hien thi" border = 0></A>
											<%} %>
										<%} %>
								
										</TD>
									</TR>
								<% m++;} dtdhList.close(); } catch(java.sql.SQLException e){} } %>
								
								<TR>
									<TD align="center" colspan="10" class="tbfooter">
									|<   <   1 to 20 of 100   >   >| </TD>
								</TR>
							</TABLE>
							</TD>
						</TR>
					</TABLE>
					</FIELDSET>
					</TD>
				</TR>
			</TBODY>
		</TABLE>
		<!--end body Dossier--></TD>
	</TR>
</TABLE>
</form>
</BODY>
<%} %>
</HTML>