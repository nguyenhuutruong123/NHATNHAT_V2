<%@page import="java.sql.ResultSet"%>
<%@page import="geso.dms.center.beans.Router.imp.Router"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page  import = "geso.dms.center.util.*" %>
<%@ page  import = "geso.dms.center.beans.lotrinh.ILoTrinh;" %>

<%	String userId = (String) session.getAttribute("userId");  
	String userTen = (String) session.getAttribute("userTen");  	
	String sum = (String) session.getAttribute("sum");
	String loi=(String)session.getAttribute("loi");
	String tungay=(String)session.getAttribute("tungay");
	String denngay=(String)session.getAttribute("denngay");
	Utility util = (Utility) session.getAttribute("util");
	ILoTrinh obj = (ILoTrinh)session.getAttribute("obj");
	ResultSet khuvuc = (ResultSet)obj.getkhuvuc();
	ResultSet npp = (ResultSet)obj.getnpp();
	ResultSet ddkd = (ResultSet)obj.getddkd();
	ResultSet tuyen = (ResultSet)obj.getTuyen();
	ResultSet danhsach = (ResultSet)obj.getdanhsach();
	if(!util.check(userId, userTen, sum)){
		response.sendRedirect(request.getContextPath() + "/redirect.jsp");
	}else{ %>
   

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
<script src="../scripts/ui/jquery.ui.datepicker.js"	type="text/javascript"></script>

<script type="text/javascript">
$(document).ready(function() {		
		$( ".days" ).datepicker({			    
				changeMonth: true,
				changeYear: true				
		});            
    }); 		
	
</script>

<SCRIPT language="javascript" type="text/javascript">

function khuvucChanging() {
	document.forms['rpForm'].action.value="khuvucChanged";
}

function submitform()
{
	document.forms['rpForm'].action.value="thuchien";
	document.forms['rpForm'].submit();
}
function exportToExcel()
{
	if(document.forms["rpForm"].ac)
	
	//alter('M???i b???n ch???n Chi nh??nh / NPP!');
	if(document.forms['rpForm'].nppId.value==""){
		document.forms['rpForm'].nppId.focus();
		alert('Vui l??ng ch???n Chi nh??nh / NPP!');
		
		return;
	}
	
	document.forms['rpForm'].action.value="export";
	document.forms['rpForm'].submit();
}
function submitCBO(){
	document.forms['rpForm'].action.value="";
	document.forms['rpForm'].submit();
}
function exportToMCP()
{
	document.forms['rpForm'].action.value="exportmcp";
	document.forms['rpForm'].submit();
}
function exportToDetail()
{
	document.forms['rpForm'].action.value="chitiet";
	document.forms['rpForm'].submit();
}
function exportToDetailSO()
{
	document.forms['rpForm'].action.value="chitietso";
	document.forms['rpForm'].submit();
}
</SCRIPT>
</HEAD>
<BODY leftmargin="0" bottommargin="0" topmargin="0" rightmargin="0">
<form name="rpForm" method="post" action="../../BCViengThamOutlet">
<input type="hidden" name="userId" value= <%= userId %> >
<input type="hidden" name="action" value='1'>

<TABLE width="100%" border="0" cellspacing="0" cellpadding="0"
	height="100%">
	
	<TR>
		<TD colspan="4" align='left' valign='top' bgcolor="#FFFFFF">
			<TABLE width="100%" cellpadding="0" cellspacing="1">		
				<TR>
					<TD width="100%" align="left">
					  <table width="100%" border="0" cellpadding="0" cellspacing="0">
						  <tr height="22">
						   <TD align="left" colspan="2" class="tbnavigation">&nbsp;B??o c??o qu???n tri &gt; B??o c??o kh??c &gt; Th???c hi???n vi???ng th??m Oulet</TD>
   
						   <TD colspan="2" align="right" class="tbnavigation"><%=Utility.GLanguage("Ch??o m???ng b???n",session,jedis) %>  <%=userTen %>&nbsp;</TD>
						  </tr>
 					  </table>					</TD>
				</TR>
				<tr>
					<TD align="left" colspan="4" class="legendtitle">
						<FIELDSET>
						<LEGEND class="legendtitle"><%=Utility.GLanguage("B??o l???i nh???p li???u",session,jedis) %></LEGEND>
	    				<textarea name="dataerror"  style="width: 100% ; color:#F00 ; font-weight:bold"  style="width: 100% ; color:#F00 ; font-weight:bold" readonly="readonly" rows="1"><%=loi%></textarea>
						</FIELDSET>
				   </TD>
				</tr>	
				<TR>
					<TD>
					<TABLE border="0" width="100%" cellpadding="0" cellspacing="0">
						<TR>
							<TD width="100%" align="left" >
							<FIELDSET>
							  <LEGEND class="legendtitle">Ti??u ch?? xu???t b??o c??o</LEGEND>
							<TABLE  width="100%" cellpadding="6" cellspacing="0">
								<TR>
								    <TD class="plainlabel" ><%=Utility.GLanguage("T??? ng??y",session,jedis) %> <FONT class="erroralert"> *</FONT></TD>
								    <TD class="plainlabel" colspan="3">
								        <TABLE cellpadding="0" cellspacing="0">
								            <TR><TD>
								                <input class="days" type="text" name="tungay" id="tungay" value='<%=obj.getTungay() %>'  size="20">
								            </TD>
								            </TR>
								        </TABLE>																					
								    </TD>
								</TR>
								<TR>
								  <TD class="plainlabel" ><%=Utility.GLanguage("?????n ng??y",session,jedis) %> <FONT class="erroralert"> *</FONT></TD>
								  <TD class="plainlabel" colspan="3">
								  	<TABLE cellpadding="0" cellspacing="0">
								            <TR><TD>
								                <input  class="days" type="text" name="denngay" id="denngay" value='<%=obj.getDenngay() %>' size="20">
								            </TD>
								            </TR>
								    </TABLE>
								
								
								</TR>

								
								<tr>
								
								<TD width="19%" class="plainlabel" ><%=Utility.GLanguage("Khu v???c",session,jedis) %> </TD>
								<TD class="plainlabel" >
								<TABLE cellpadding="0" cellspacing="0">
								<TR><TD>
								<select name="khuvucId" onchange="submitCBO();" >
                                 <option value ="" > </option>  
                               <%
                               
                               while(khuvuc.next())
                               {
                               if(khuvuc.getString("pk_seq").equals(obj.getkhuvucId())) {
                            	%><option value ="<%=khuvuc.getString("pk_seq") %>" selected> <%=khuvuc.getString("ten") %></option>  
                            	  <%} else { %>
                            	  <option value ="<%=khuvuc.getString("pk_seq") %>"> <%=khuvuc.getString("ten") %></option>
                              <%}} %>                             </select>		   										</TD>
                                    		</TR>
								</TABLE>
								</TD>
								
								</tr>
								
								<tr>
									<td width="19%" class="plainlabel" ><%=Utility.GLanguage("Tr???ng th??i",session,jedis) %> NPP</td>
									<td class="plainlabel" >
										<table cellpadding="0" cellspacing="0">
											<tr>
												<td>
													<select name="status" onchange="submitCBO();">
														<option value =""> </option>
														<%if(obj.getStatus().equals("1")) {%>
															<option selected="selected" value="1"><%=Utility.GLanguage("Ho???t ?????ng",session,jedis) %></option>
															<option value="0">Kh??ng Ho???t ?????ng</option>
														<%}else {%>
															<option value="1"><%=Utility.GLanguage("Ho???t ?????ng",session,jedis) %></option>
															<option selected="selected" value="0">Kh??ng Ho???t ?????ng</option>
														<%} %>
													</select>
												</td>
											</tr>
										</table>
									
									</td>
								</tr>
								
								<tr>
								  	<TD width="19%" class="plainlabel" ><%=Utility.GLanguage("Nh?? ph??n ph???i",session,jedis) %></TD>
								  	<TD class="plainlabel" >
										<TABLE cellpadding="0" cellspacing="0">
											<TR><TD>
								<select name="nppId" onchange="submitCBO();" >
                                 <option value =""> </option>  
                               <%
                               if(npp != null)
                               while(npp.next())
                               {
                               if(npp.getString("pk_seq").equals(obj.getnppId())) {
                            	%><option value ="<%=npp.getString("pk_seq") %>" selected> <%=npp.getString("ten") %></option>  
                            	  <%} else { %>
                            	  <option value ="<%=npp.getString("pk_seq") %>"> <%=npp.getString("ten") %></option>
                              <%}} %>                             </select>		   										</TD>
                                    		</TR>
								</TABLE>									
								</TD>
								
								</tr>

								
							  <TR>
										<TD width="19%" class="plainlabel" ><%=Utility.GLanguage("NH??N VI??N B??N H??NG",session,jedis) %></TD>
								  	<TD class="plainlabel" >
										<TABLE cellpadding="0" cellspacing="0">
											<TR><TD>
								<select name="ddkdId" >
                                 <option value =""> </option>  
                               <%
                               while(ddkd.next())
                               {
                               if(ddkd.getString("pk_seq").equals(obj.getddkdId())) {
                            	%><option value ="<%=ddkd.getString("pk_seq") %>" selected> <%=ddkd.getString("ten") %></option>  
                            	  <%} else { %>
                            	  <option value ="<%=ddkd.getString("pk_seq") %>"> <%=ddkd.getString("ten") %></option>
                              <%}} %>                             </select>		   										</TD>
                                    		</TR>
								</TABLE>									
								</TD>							
								</TR>
							    <TR>
							    <TR>
										<TD width="19%" class="plainlabel" >Tuy???n</TD>
								  	<TD class="plainlabel" >
										<TABLE cellpadding="0" cellspacing="0">
								<TR><TD>
								<select name="tuyenId"  >
                                 <option value =""> </option>  
                               <%
                               while(tuyen.next())
                               {
                               if(tuyen.getString("ngaylamviec").equals(obj.gettuyenId())) {
                            	%><option value ="<%=tuyen.getString("ngayid") %>" selected> <%=tuyen.getString("ngaylamviec") %></option>  
                            	  <%} else { %>
                            	  <option value ="<%=tuyen.getString("ngayid") %>"> <%=tuyen.getString("ngaylamviec") %></option>
                              <%}} %>             
                               </select>		   										
                               </TD>
                               </TR>
                               </TABLE>
                               </TD>
                               </TR>
                               <TR>
									<td class="plainlabel" colspan='3'>
										<a class="button3" href="javascript:exportToMCP()">
											<img style="top: -4px;" src="../images/button.png" alt=""> Th???c hi???n vi???ng th??m </a> 
										&nbsp;&nbsp;&nbsp;&nbsp;
										<a class="button3" href="javascript:exportToDetail()">
											<img style="top: -4px;" src="../images/button.png" alt=""> Th???c hi???n vi???ng th??m CT </a>
										&nbsp;&nbsp;&nbsp;&nbsp;
										<a class="button3" href="javascript:exportToDetailSO()">
											<img style="top: -4px;" src="../images/button.png" alt=""> Th???c hi???n vi???ng th??m CT ?????n ????n h??ng </a> 
									</td>
								</TR>
                               </TABLE>
                               <!-- <a class="button3" href="javascript:exportToExcel()">Xu???t ra excel </a> -->
                               </FIELDSET>
                               
                               </TD>
                               </TR>
                               
    							  
                               </TABLE>
                                
								                        

			
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
	try
	{
		if(!(danhsach == null))danhsach.close();
		if(ddkd != null)ddkd.close();
		if(npp != null)npp.close();
		if(tuyen != null)tuyen.close();
		if(khuvuc!=null)khuvuc.close();
		
		if(obj != null){obj.DBclose();
		obj = null;}
		session.setAttribute("obj", null);
	}catch(java.sql.SQLException e){}
	}
%>