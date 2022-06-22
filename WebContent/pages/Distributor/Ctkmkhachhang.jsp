<%@page import="java.sql.SQLException"%>
<%@page import="java.sql.ResultSet"%>
<%@page import="geso.dms.distributor.beans.ctkmkhachhang.ICtkmkhachhangList"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page  import = "java.util.List" %>
<%@ page  import = "java.util.Hashtable" %>
<%@ page  import = "geso.dms.center.util.*" %>
<%	String userId = (String) session.getAttribute("userId");  
	String userTen = (String) session.getAttribute("userTen");  	
	String sum = (String) session.getAttribute("sum");
	Utility util = (Utility) session.getAttribute("util");
	if(!util.check(userId, userTen, sum)){
		response.sendRedirect(request.getContextPath() + "/redirect.jsp");
	}else{ 
		int[] quyen = new  int[6];
		quyen = util.Getquyen("CtkmkhachhangSvl","",userId);

	%>

<%ICtkmkhachhangList obj = (ICtkmkhachhangList)session.getAttribute("obj"); %>
<%ResultSet Dskm = (ResultSet)obj.getDskm();%>
<%ResultSet ddkd = (ResultSet)obj.getDDKD();%>

<% obj.setNextSplittings(); %>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"> <% Utility Utilback = new Utility(); %>
<HTML>
<HEAD>
<TITLE><%= getServletContext().getInitParameter("TITLENAME") %></TITLE>
<META http-equiv="Content-Type" content="text/html; charset=utf-8">

<META http-equiv="Content-Style-Type" content="text/css">
	<link type="text/css" rel="stylesheet" href="../css/mybutton.css">
<LINK rel="stylesheet" href="../css/main.css" type="text/css">
<LINK rel="stylesheet" href="../css/calendar.css" type="text/css">
<script type="text/javascript" language="JavaScript" src="../scripts/simplecalendar.js"></script>

<script type="text/javascript" src="../scripts/phanTrang.js"></script>
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
<SCRIPT language="JavaScript" type="text/javascript">
function submitform()
{
	 document.nkhForm.action.value = "new";
   	 document.forms["nkhForm"].submit();
}

function searchform()
{
	 document.nkhForm.action.value = "search";
   	 document.forms["nkhForm"].submit();
}

function clearform()
{
	document.nkhForm.ddkdTen.value = "";
	document.nkhForm.diengiai.value = "";
    document.nkhForm.tungay.value = "";
	document.nkhForm.denngay.value = "";
	document.nkhForm.tenKH.value = "";
	document.nkhForm.maKH.value = "";
 	document.forms["nkhForm"].submit();
}

</SCRIPT>
</HEAD>
<BODY leftmargin="0" bottommargin="0" topmargin="0" rightmargin="0">
<form name="nkhForm" method="post" action="../../CtkmkhachhangSvl">
<input type="hidden" name="userId" value='<%=userId%>'>

<input type="hidden" name="action" value="1" >
<input type="hidden" name="crrApprSplitting" value="<%= obj.getCrrApprSplitting() %>" >
<input type="hidden" name="nxtApprSplitting" value="<%= obj.getNxtApprSplitting() %>" >

<TABLE width="100%" border="0" cellspacing="0" cellpadding="0"
	height="100%">
	<TR>
		<TD colspan="4" align='left' valign='top' bgcolor="#FFFFFF">
			<TABLE width="100%" border="0" cellpadding="0" cellspacing="1">
				<TR>
					<TD align="left" class="tbnavigation">
				   		<table width="100%" border="0" cellpadding="0" cellspacing="0">
					  		<tr height="22">
						  		<TD align="left" colspan="2" class="tbnavigation">&nbsp;Quản lý khuyến mãi &gt; Khách hàng hưởng khuyến mãi</TD> 
						  		<TD colspan="2" align="right" class="tbnavigation">Chào mừng  <%= obj.getTenNpp() %>&nbsp;  </TD>
						  	</tr>
						</table></TD>
			  	</TR>
			</TABLE>
			
			<TABLE width="100%" border="0" cellpadding="0">
				<TR>
					<TD >
						<FIELDSET><LEGEND class="legendtitle">&nbsp;<%=Utility.GLanguage("Tiêu chí tìm kiếm",session,jedis) %>&nbsp;</LEGEND>
							<TABLE  width="100%" cellspacing="0" cellpadding="6">
							
							<TR>
								<TD class="plainlabel"><%=Utility.GLanguage("NHÂN VIÊN BÁN HÀNG",session,jedis) %> </TD>
								<TD class="plainlabel"><SELECT name="ddkdTen" onChange = "searchform();" >
								  <option value=""></option>
								  <% 
								  if(ddkd !=null)
								  try{
									  while(ddkd.next()){ 
								    	if(ddkd.getString("ddkdId").equals(obj.getDDKDId())){ %>
								      		<option value='<%=ddkd.getString("ddkdId") %>' selected><%=ddkd.getString("ddkdTen") %></option>
								      	<%}else{ %>
								     		<option value='<%=ddkd.getString("ddkdId") %>'><%=ddkd.getString("ddkdTen") %></option>
								     	<%}}}catch(java.sql.SQLException e){}
								   %>
							    </SELECT></TD>

							</TR>
								
								<TR>
									<TD width="19%" class="plainlabel">Chương trình khuyến mãi </TD>
								    <TD width="81%" class="plainlabel">
										<TABLE cellpadding="0" cellspacing="0">
											<TR><TD>
												<INPUT name="diengiai" type="text" size="40" value='<%=obj.getDiengiai()%>' onChange ="searchform();">
											</TD></TR>
										</TABLE>								
								</TR>
<TR>
	<TD class="plainlabel" ><%=Utility.GLanguage("Từ ngày",session,jedis) %></TD>
	<td class="plainlabel">
		<input type="text"  class="days" size="11"
				id="tungay" name="tungay" value="<%= obj.getTungay() %>" maxlength="10" readonly />
	</td>
</TR>
<TR>
  <TD class="plainlabel" >Ðến ngày</TD>
  <td class="plainlabel">
		<input type="text"  class="days" size="11"
				id="denngay" name="denngay" value="<%= obj.getDenngay() %>" maxlength="10" readonly />
	</td>
</TR>
									  
							<TR>

									<TD width="19%" class="plainlabel">Tên khách hàng</TD>
								    <TD width="81%" class="plainlabel">
										<TABLE cellpadding="0" cellspacing="0">
											<TR><TD>
												<INPUT name="tenKH" type="text" size="40" value='<%=obj.getTenKH()%>' onChange ="searchform();">
											</TD></TR>
										</TABLE>								
								</TR>
							
															<TR>
									<TD width="19%" class="plainlabel">Mã khách hàng </TD>
								    <TD width="81%" class="plainlabel">
										<TABLE cellpadding="0" cellspacing="0">
											<TR><TD>
												<INPUT name="maKH" type="text" size="40" value='<%=obj.getMaKH()%>' onChange ="searchform();">
											</TD></TR>
										</TABLE>
							
								</TR>

								
									  
								<TR>
									<TD class="plainlabel">
									<a class="button2" href="javascript:clearform()">
	<img style="top: -4px;" src="../images/button.png" alt=""><%=Utility.GLanguage("Nhập lại",session,jedis) %></a>&nbsp;&nbsp;&nbsp;&nbsp;	
									
                                  </TD>								
									<TD class="plainlabel">&nbsp;</TD>										
								</TR>
								
							</TABLE>
					  </FIELDSET>
					</TD>	
				</TR>
			</TABLE>
			
			<TABLE width="100%" border = "0" cellpading = "0" cellspacing = "0">
				<TR>
					<TD width="100%">
					<FIELDSET>
					<LEGEND class="legendtitle">&nbsp;Nhóm khách hàng &nbsp;&nbsp;&nbsp;
					</LEGEND>
					<TABLE class="" width="100%">
						<TR>
							<TD width="98%">
							<TABLE width="100%" border="0" cellspacing="1" cellpadding="4">
								<TR class="tbheader">
									<TH width="30%">Chương trình khuyến mãi </TH>
									<TH width="40%">Nhóm khách hàng </TH>
									<TH width="10%"><%=Utility.GLanguage("Từ ngày",session,jedis) %> </TH>
									<TH width="10%">Dến ngày </TH>
									<TH width="10%"><%=Utility.GLanguage("Tác vụ",session,jedis) %></TH>
								</TR>
						<% 
							
							String lightrow = "tblightrow";
							String darkrow = "tbdarkrow";
							int m =0;
							if(Dskm != null)
							while (Dskm.next()){
								
								if (m % 2 != 0) {%>
									<TR class= <%=lightrow%> >
								<%} else {%>
									<TR class= <%= darkrow%> >
								<%} %>										
															
									<TD><%=Dskm.getString("ctkm") %></TD>
									<TD align="center"><%=Dskm.getString("diengiai") %></TD>
									<TD align="center"><%=Dskm.getString("tungay") %></TD>
									<TD align="center"><%=Dskm.getString("denngay") %></TD>
									<% String st = Dskm.getString("pk_seq")+ "-" + Dskm.getString("ma");%>
									<TD align="center">
										<TABLE>
											<TR><TD>
											
												<%if(quyen[Utility.SUA]!=0){ %>
												<A href = "../../CtkmkhachhangUpdateSvl?userId=<%=userId%>&update=<%=st %>" ><img src="../images/Edit20.png" alt="Cap nhat" width="20" height="20" longdesc="Cap nhat" border = 0></A>
												<%} %>
												
											</TD>
											<TD>&nbsp;</TD>
																			
										</TABLE>									
									</TD>
								</TR>
							<%m++; }%>
							
<tr class="tbfooter" > 
											 <TD align="center" valign="middle" colspan="13" class="tbfooter">
											 	<%if(obj.getNxtApprSplitting() >1) {%>
													<img alt="Trang Dau" src="../images/first.gif" style="cursor: pointer;" onclick="View('nkhForm', 1, 'view')"> &nbsp;
												<%}else {%>
													<img alt="Trang Dau" src="../images/first.gif" > &nbsp;
													<%} %>
												<% if(obj.getNxtApprSplitting() > 1){ %>
													<img alt="Trang Truoc" src="../images/prev.gif" style="cursor: pointer;" onclick="View('nkhForm', eval(nkhForm.nxtApprSplitting.value) -1, 'view')"> &nbsp;
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
													<a href="javascript:View('nkhForm', <%= listPage[i] %>, 'view')"><%= listPage[i] %></a>
												<%} %>
													<input type="hidden" name="list" value="<%= listPage[i] %>" />  &nbsp;
												<%} %>
												
												<% if(obj.getNxtApprSplitting() < obj.getTheLastSplitting()){ %>
													&nbsp; <img alt="Trang Tiep" src="../images/next.gif" style="cursor: pointer;" onclick="View('nkhForm', eval(nkhForm.nxtApprSplitting.value) +1, 'view')"> &nbsp;
												<%}else{ %>
													&nbsp; <img alt="Trang Tiep" src="../images/next_d.gif" > &nbsp;
												<%} %>
												<%if(obj.getNxtApprSplitting() == obj.getTheLastSplitting()) {%>
											   		<img alt="Trang Cuoi" src="../images/last.gif" > &nbsp;
										   		<%}else{ %>
										   			<img alt="Trang Cuoi" src="../images/last.gif" style="cursor: pointer;" onclick="View('nkhForm', -1, 'view')"> &nbsp;
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
<% 	

	try{
		if(ddkd != null)
			ddkd.close();
		if(Dskm != null)
			Dskm.close();
		if(obj != null){
			obj.DBclose();
			obj = null;
		}	
		session.setAttribute("obj",null);
	}
	catch (SQLException e) {}

%>
<%}%>