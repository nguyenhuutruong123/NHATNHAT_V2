<%@page import="java.sql.SQLException"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page  import = "java.util.Iterator" %>
<%@ page  import = "java.util.List" %>
<%@ page  import = "java.util.ArrayList" %>
<%@ page  import = "geso.dms.distributor.beans.khachhangtt.*" %>
<%@ page  import = "java.sql.ResultSet" %>
<%@ page  import = "geso.dms.center.util.*" %>
<%	
	String svl ="BCTonKhoCuaHieu";

	String userId = (String) session.getAttribute("userId");  
	String userTen = (String) session.getAttribute("userTen");  	
	String sum = (String) session.getAttribute("sum");
	Utility util = (Utility) session.getAttribute("util");
	String url="";
	url = util.getUrl(svl,"");
	if(!util.check(userId, userTen, sum)){
		response.sendRedirect("/OPV/index.jsp");
	}else{ 
	int[] quyen = new  int[6];
		quyen = util.Getquyen(svl,"",userId);%>

<% IKhachhangTTList obj = (IKhachhangTTList)session.getAttribute("obj"); %>
<%-- <% ResultSet khlist = (ResultSet) obj.getKhList(); %> --%>
<% ResultSet hch = (ResultSet)obj.getHangcuahang(); %>
<% ResultSet kbh = (ResultSet)obj.getKenhbanhang();  %>
<% ResultSet vtch = (ResultSet)obj.getVitricuahang();  %>
<% ResultSet lch = (ResultSet)obj.getLoaicuahang(); %>
<% ResultSet diadiemRs = (ResultSet)obj.getDiadiemRs()  ; %>
<%-- <% ResultSet tbhRs = (ResultSet)obj.getTbhRs()  ; %> --%>
 <% ResultSet dayOfWeekRs = (ResultSet)obj.getDayOfWeekRs()  ; %>
<% ResultSet ddkdRs = (ResultSet)obj.getDdkdRs()  ; %>
<% ResultSet nppRs = (ResultSet)obj.getNppRs()  ; %>
<% ResultSet mien = (ResultSet)obj.getMien()  ; %>
<% ResultSet vung = (ResultSet)obj.getVung() ; %>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"> <% Utility Utilback = new Utility(); %>
<HTML>
<HEAD>
<TITLE><%= getServletContext().getInitParameter("TITLENAME") %></TITLE>
<META http-equiv="Content-Type" content="text/html; charset=utf-8">
<META http-equiv="Content-Style-Type" content="text/css">
<LINK rel="stylesheet" href="../css/main.css" type="text/css">
<LINK rel="stylesheet" href="../css/calendar.css" type="text/css">
	<link type="text/css" rel="stylesheet" href="../css/mybutton.css">
<script type="text/javascript" language="JavaScript" src="../scripts/simplecalendar.js"></script>



	<script type="text/javascript" src="../scripts/Timepicker/jquery.min.js"></script>
  	<script type="text/javascript" src="../scripts/Timepicker/jquery-ui.min.js"></script>
  	<script type="text/javascript" src="../scripts/phanTrang.js"></script>
	<script type="text/javascript" src="../scripts/jquery.min.1.7.js"></script>
	<link href="../css/jquery-ui.css" rel="stylesheet" type="text/css" />
	<script src="../scripts/ui/jquery.ui.core.js" type="text/javascript"></script>
	<script src="../scripts/ui/jquery.ui.widget.js" type="text/javascript"></script>
	<script type="text/javascript" src="../scripts/phanTrang.js"></script>
	<script src="../scripts/ui/jquery.ui.datepicker.js" type="text/javascript"></script>




<SCRIPT language="javascript" type="text/javascript">
$(document).ready(function() {		
	$( ".days" ).datepicker({			    
			changeMonth: true,
			changeYear: true				
	});            
}); 		


function clearform()
{
	document.khForm.khTen.value = "";
//	document.khForm.hchTen.selectedIndex = 0;
	document.khForm.kbhTen.selectedIndex = 0;
// 	document.khForm.vtchTen.selectedIndex = 0;
// 	document.khForm.lchTen.selectedIndex = 0;
	document.khForm.maFAST.value = "";
	document.khForm.diachi.value = "";
	document.khForm.ddkdId.value = "";
	document.khForm.trangthai.value = "";
	document.khForm.tbhId.value = "";
	document.khForm.tungay.value = "";
	document.khForm.denngay.value = "";
	document.khForm.loaikh.value = "";
	document.khForm.hopdong.value = "";
	submitform();

}

function submitform()
{
	document.forms['khForm'].action.value= 'search';
	document.forms['khForm'].submit();
}

function newform()
{
	document.forms['khForm'].action.value= 'new';
	document.forms['khForm'].submit();
}


function xuatExcel()
{
	document.forms['khForm'].action.value= 'excel';
	document.forms['khForm'].submit();
	
}

function xuatExcelChitiet()
{
	document.forms['khForm'].action.value= 'chitiet';
	document.forms['khForm'].submit();
	
}
</SCRIPT>

<link href="../css/select2.css" rel="stylesheet" />
<script src="../scripts/select2.js"></script>
<script>
  	$(document).ready(function() { 
  		$("select:not(.notuseselect2)").select2(); 
  	});
</script>	
</HEAD>

<BODY leftmargin="0" bottommargin="0" topmargin="0" rightmargin="0">
<form name="khForm" method="post" action="../../BCTonKhoCuaHieu" >
<input type="hidden" name="userId" value="<%= userId %>" >
<input type="hidden" name="action" value="1" >
<input type="hidden" name="currentPage" value="<%= obj.getCurrentPage() %>" >

<TABLE width="100%" border="0" cellspacing="0" cellpadding="0" 	height="100%">
	<TR>
		<TD colspan="4" align='left' valign='top' bgcolor="#FFFFFF">
			<TABLE width="100%" border="0" cellpadding="0" cellspacing="1">
				<TR>
					<TD align="left" class="tbnavigation">
					   <table width="100%" border="0" cellpadding="0" cellspacing="0">
						  	<tr height="33">
							   <TD align="left" colspan="2" class="tbnavigation">
							   <%-- &nbsp;<%=url %> --%>
							   &nbsp;<%=url %>
							   </TD>
							   <TD colspan="2" align="right" class="tbnavigation">Ch??o m???ng  <%=userTen%> &nbsp;</TD>
							</tr>
						</table>
					</TD>
		  		</TR>	
			</TABLE>
			<TABLE width="100%" border="0" cellpadding="0" cellspacing="0">
				<TR>
					<TD >
						<FIELDSET><LEGEND class="legendtitle">&nbsp;<%=Utility.GLanguage("Ti??u ch?? t??m ki???m",session,jedis) %>&nbsp;</LEGEND>
							<TABLE class="tblight" width="100%" cellspacing="0" cellpadding="6">
							 <TR>
							  	 <TD class="plainlabel" ><%=Utility.GLanguage("T??? ng??y",session,jedis) %> </TD>
							  	 <TD class="plainlabel" >
							  	 	<input type="text" class="days" name="tungay" value="<%= obj.getTungay() %>" maxlength="10"  />
							  	 </TD>
							  	 
							  	 <TD class="plainlabel" ><%=Utility.GLanguage("?????n ng??y",session,jedis) %> </TD>
							  	 <TD class="plainlabel" >
							  	 	<input type="text" class="days" name="denngay" value="<%= obj.getDenngay() %>" maxlength="10" />
							  	 </TD>
							  </TR>
							  
							  <TR>
							  	<TD width="120px" class="plainlabel" valign="top" ><%=Utility.GLanguage("Nh?? ph??n ph???i",session,jedis) %></TD>
						  	  	<TD class="plainlabel" valign="top">
							  	<SELECT name="nppId" onChange = "submitform();" style ="width:200px">
							    	<option value=""> </option>
								    <% try{ while(nppRs.next()){ 
					    			if(nppRs.getString("pk_seq").equals(obj.getNppId()    )){ %>
					      				<option value='<%=nppRs.getString("pk_seq")%>' selected>
					      				<%=nppRs.getString("TEN") %></option>
					      			<%}else{ %>
					     				<option value='<%=nppRs.getString("pk_seq")%>'>
					     				<%=nppRs.getString("TEN") %></option>
					     			<%}}}catch(Exception e){ e.printStackTrace(); } %>
								 </SELECT>
								</TD>
								
								<TD width="120px" class="plainlabel" valign="top" ><%=Utility.GLanguage("NH??N VI??N B??N H??NG",session,jedis) %></TD>
						  	  	<TD class="plainlabel" valign="top">
							  	<SELECT name="ddkdId" onChange = "submitform();" style ="width:200px">
						    	<option value=""> </option>
							    <% try{ while(ddkdRs.next()){ 
				    			if(ddkdRs.getString("ddkdId").equals(obj.getDdkdId()    )){ %>
				      				<option value='<%=ddkdRs.getString("ddkdId")%>' selected>
				      				<%=ddkdRs.getString("ddkdTen") %></option>
				      			<%}else{ %>
				     				<option value='<%=ddkdRs.getString("ddkdId")%>'>
				     				<%=ddkdRs.getString("ddkdTen") %></option>
				     			<%}}}catch(Exception e){ e.printStackTrace(); } %>
								 </SELECT>
								</TD>
								
							  </TR>
							  	
								<TR>
									<TD width="130px" class="plainlabel">M?? / T??n kh??ch h??ng</TD>
								    <TD width="300px" class="plainlabel">
										<INPUT name="khTen" type="text" value="<%= obj.getTen() %>" size="40" onChange = "submitform();" style ="width:200px">
								  	</TD>
								  	<TD width="120px" class="plainlabel" valign="top" ><%=Utility.GLanguage("K??nh b??n h??ng",session,jedis) %></TD>
								  	  <TD class="plainlabel" valign="top">
										  	<SELECT name="kbhTen" onChange = "submitform();" style ="width:200px">
										    	<option value=""> </option>
											    <% try{ while(kbh.next()){ 
										    			if(kbh.getString("kbhId").equals(obj.getKbhId())){ %>
										      				<option value='<%=kbh.getString("kbhId")%>' selected><%=kbh.getString("kbhTen") %></option>
										      			<%}else{ %>
										     				<option value='<%=kbh.getString("kbhId")%>'><%=kbh.getString("kbhTen") %></option>
										     			<%}}}catch(java.sql.SQLException e){} %>
											 </SELECT>
									</TD>
								  
								</TR>
								
								<TR>
									<TD class="plainlabel" valign="top" style="display:none" >M?? FAST</TD>
								    <TD class="plainlabel" valign="top" style="display:none"  >
									<INPUT name="maFAST" type="text" value="<%= obj.getMaFAST() %>" size="40" onChange = "submitform();" style="display:none" >
								    </TD>
								  
								  	<TD width="120px" class="plainlabel" valign="top" >Tuy???n b??n h??ng</TD>
							  	  	<TD class="plainlabel" valign="top">
							  	  	<SELECT name="dayOfWeekId" onChange = "submitform();" style ="width:200px">
							    	<option value=""> </option>
								    <% try{ while(dayOfWeekRs.next()){ 
							    			if(dayOfWeekRs.getString("NgayId").equals(obj.getDayOfWeekId()  )){ %>
							      				<option value='<%=dayOfWeekRs.getString("NgayId")%>' selected><%=dayOfWeekRs.getString("Ten") %></option>
							      			<%}else{ %>
							     				<option value='<%=dayOfWeekRs.getString("NgayId")%>'><%=dayOfWeekRs.getString("Ten") %></option>
							     			<%}}}catch(java.sql.SQLException e){} %>
								 	</SELECT>
								 	</TD>
									 
									 <TD class="plainlabel" > Lo???i kh??ch h??ng </TD>
								  	 <TD class="plainlabel" >
						  	 		<SELECT name="loaikh" onChange = "submitform();" style ="width:200px">
							    	<option value=""> </option>
								    <% try{ while(lch.next()){ 
					    			if(lch.getString("lchId").equals(obj.getloaiKH()  )){ %>
					      				<option value='<%=lch.getString("lchId")%>' selected><%=lch.getString("lchTen") %></option>
					      			<%}else{ %>
					     				<option value='<%=lch.getString("lchId")%>'><%=lch.getString("lchTen") %></option>
					     			<%}}}catch(java.sql.SQLException e){} %>
									 </SELECT>
								  	 </TD>
								</TR>
								
								<TR>
								<TD class="plainlabel" valign="top" style="display:none" >?????a ch???</TD>
								    <TD class="plainlabel" valign="top" style="display:none" >
										<INPUT name="diachi" type="text" value="<%= obj.getDiachi() %>" size="40" onChange = "submitform();" style="display:none" >
								 
								  </TR>
								  
								  <TR>
									<TD width="120px" class="plainlabel" valign="top" >Mi???n</TD>
									 <TD  class="plainlabel" valign="top">
										 <SELECT name="vungid" onChange = "submitform();" style ="width:200px">
													    	<option value=""> </option>
														    <% try{ while(vung.next()){ 
													    			if(vung.getString("pk_seq").equals(obj.getVungid()    )){ %>
													      				<option value='<%=vung.getString("pk_seq")%>' selected>
													      				<%=vung.getString("ten") %></option>
													      			<%}else{ %>
													     				<option value='<%=vung.getString("pk_seq")%>'>
													     				<%=vung.getString("ten") %></option>
													     			<%}}}catch(Exception e){ e.printStackTrace(); } %>
														 </SELECT>
								
									 </TD>
									 
									 <TD width="120px" class="plainlabel" valign="top" >T???nh Th??nh</TD>
											  	  <TD class="plainlabel" valign="top">
													 <SELECT name="mienid" onChange = "submitform();" style ="width:200px">
													    	<option value=""> </option>
														    <% try{ while(mien.next()){ 
													    			if(mien.getString("pk_seq").equals(obj.getMienid()    )){ %>
													      				<option value='<%=mien.getString("pk_seq")%>' selected>
													      				<%=mien.getString("ten") %></option>
													      			<%}else{ %>
													     				<option value='<%=mien.getString("pk_seq")%>'>
													     				<%=mien.getString("ten") %></option>
													     			<%}}}catch(Exception e){ e.printStackTrace(); } %>
														 </SELECT>
								  
												</TD>
					  				
									 
									 
								  </TR>
								  
								
								<TR>
								    <TD class="plainlabel" colspan="4">
								     	<a class="button2" href="javascript:xuatExcel()"> <img style="top: -4px;" src="../images/button.png" alt=""><%=Utility.GLanguage("Xu???t Excel",session,jedis) %> </a>
								     	<!-- <a class="button2" href="javascript:xuatExcelChitiet()" style="margin-left: 1rem;"> <img style="top: -4px;" src="../images/button.png" alt="">Xu???t chi ti???t </a>
                                    -->  </TD>
								</TR>
							</TABLE>
					  </FIELDSET>
					</TD>	
				</TR>
			</TABLE>
		</form>
	</BODY>
</HTML>
<% 	

	try{
		
		if(hch != null)
			hch.close();
		if(kbh != null)
			kbh.close();
		if(lch != null)
			lch.close();
		if(vtch!=null){
			vtch.close();
		}
		if(obj != null){
			obj.DBclose();
			obj = null;
		}		
		
		session.setAttribute("obj",null);
	
	}
	catch (SQLException e) {}
	

%>
<%}%>