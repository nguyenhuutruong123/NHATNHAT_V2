<%@page import="java.sql.ResultSet"%>
<%@page import="geso.dms.center.beans.Router.imp.Router"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page  import = "geso.dms.center.util.*" %>
<%@ page  import = "geso.dms.center.beans.lotrinh.ILoTrinh" %>

<%	String userId = (String) session.getAttribute("userId");  
	String userTen = (String) session.getAttribute("userTen");  	
	String sum = (String) session.getAttribute("sum");
	String loi=(String)session.getAttribute("loi");
	String tungay=(String)session.getAttribute("tungay");
	String denngay=(String)session.getAttribute("denngay");
	Utility util = (Utility) session.getAttribute("util");
	String url = util.getChuyenNguUrl("LoTrinhBanHangReport", "",session);
	ILoTrinh obj = (ILoTrinh)session.getAttribute("obj");
	ResultSet khuvuc = (ResultSet)obj.getkhuvuc();
	ResultSet npp = (ResultSet)obj.getnpp();
	ResultSet ddkd = (ResultSet)obj.getddkd();
	ResultSet tuyen = (ResultSet)obj.getTuyen();
	ResultSet danhsach = (ResultSet)obj.getdanhsach();
	ResultSet tinhthanhRs = (ResultSet)obj.getTinhthanhRs();
	ResultSet vungRs = (ResultSet)obj.getVungRs();
	
	
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
<script type="text/javascript" language="JavaScript" src="../scripts/simplecalendar.js"></script>

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
<link href="../css/select2.css" rel="stylesheet" />
<script src="../scripts/select2.js"></script>
<script>
	$(document).ready(function()
	{
		$(".select2").select2();
		
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
	
	//alter('Mời bạn chọn Chi nhánh / Nhà phân phối!');
	if(document.forms['rpForm'].nppId.value==""){
		document.forms['rpForm'].nppId.focus();
		alert('Vui lòng chọn Chi nhánh / Nhà phân phối!');
		
		return;
	}
	
	document.forms['rpForm'].action.value="export";
	document.forms['rpForm'].submit();
}
function submitCBO(action){
	
	if(action >= 1) // chon lai npp
	{				
		document.forms['rpForm'].ddkdId.value= '';
	}
	if( action >= 2) // chon lai tinh thanh
	{
		document.forms['rpForm'].nppId.value= '';
	}
	if( action >= 3) // chon lai vung
	{
		document.forms['rpForm'].tinhthanhId.value= '';
	}
	
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
</SCRIPT>
</HEAD>
<BODY leftmargin="0" bottommargin="0" topmargin="0" rightmargin="0">
<form name="rpForm" method="post" action="../../LoTrinhBanHangReport">
<% redis.clients.jedis.Jedis jedis =  geso.dms.center.util.Utility.getJedis(); %><% geso.dms.center.util.Csrf csdr=new geso.dms.center.util.Csrf(request,response,true,false,true);%>
 <input type="hidden" name="<%=csdr.get_tokenName() %>" value='<%=csdr.get_tokenValue() %>'>

<input type="hidden" name="userId" value= <%= userId %> >
<input type="hidden" name="action" value='1'>
<input type="hidden" name="view" value='<%=obj.getLoaiMenu()%>'>
<TABLE width="100%" border="0" cellspacing="0" cellpadding="0"
	height="100%">
	
	<TR>
		<TD colspan="4" align='left' valign='top' bgcolor="#FFFFFF">
			<TABLE width="100%" cellpadding="0" cellspacing="1">		
				<TR>
					<TD width="100%" align="left">
					  <table width="100%" border="0" cellpadding="0" cellspacing="0">
						  <tr height="22">
						   <TD align="left" colspan="2" class="tbnavigation">&nbsp;<%=url %></TD>
   
						   <TD colspan="2" align="right" class="tbnavigation"><%=Utility.GLanguage("Chào mừng bạn",session,jedis) %>  <%=userTen %>&nbsp;</TD>
						  </tr>
 					  </table>					</TD>
				</TR>
				<tr>
					<TD align="left" colspan="4" class="legendtitle">
						<FIELDSET>
						<LEGEND class="legendtitle"><%=Utility.GLanguage("Báo lỗi nhập liệu",session,jedis) %></LEGEND>
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
							  <LEGEND class="legendtitle"><%=Utility.GLanguage("Tiêu chí xuất báo cáo",session,jedis) %></LEGEND>
							<TABLE  width="100%" cellpadding="6" cellspacing="0">
								<TR>
								    <TD class="plainlabel" ><%=Utility.GLanguage("Từ ngày",session,jedis) %> <FONT class="erroralert"> *</FONT></TD>
								    <TD class="plainlabel" colspan="3">
								        <TABLE cellpadding="0" cellspacing="0">
								            <TR><TD>
								                <input class="days" type="text" name="tungay" id="tungay" value='<%=obj.getTungay() %>'  size="20" autocomplete="off">
								            </TD>
								            </TR>
								        </TABLE>																					
								    </TD>
								</TR>
								<TR>
								  <TD class="plainlabel" ><%=Utility.GLanguage("Đến ngày",session,jedis) %> <FONT class="erroralert"> *</FONT></TD>
								  <TD class="plainlabel" colspan="3">
								  	<TABLE cellpadding="0" cellspacing="0">
								            <TR><TD>
								                <input  class="days" type="text" name="denngay" id="denngay" value='<%=obj.getDenngay() %>' size="20" autocomplete="off">
								            </TD>
								            </TR>
								    </TABLE>
								
								
								</TR>	
								
								<tr>
								
								<TD width="19%" class="plainlabel" ><%=Utility.GLanguage("Vùng",session,jedis) %> </TD>
								<TD class="plainlabel" >
								<TABLE cellpadding="0" cellspacing="0">
								<TR><TD>
								<select name="vungId" onchange="submitCBO(3);" class="select2" style="width: 200px">
                                 <option value ="" > </option>  
                               <%
                               
                               while(vungRs.next())
                               {
                               if(vungRs.getString("pk_seq").equals(obj.getVungId())) {
                            	%><option value ="<%=vungRs.getString("pk_seq") %>" selected> <%=vungRs.getString("ten") %></option>  
                            	  <%} else { %>
                            	  <option value ="<%=vungRs.getString("pk_seq") %>"> <%=vungRs.getString("ten") %></option>
                              <%}} %>                             </select>		   										</TD>
                                    		</TR>
								</TABLE>
								</TD>
								
								</tr>
								
								<tr>
								
								<TD width="19%" class="plainlabel" ><%=Utility.GLanguage("Khu vực",session,jedis) %> </TD>
								<TD class="plainlabel" >
								<TABLE cellpadding="0" cellspacing="0">
								<TR><TD>
								<select name="khuvucId" onchange="submitCBO(0);" class="select2" style="width: 200px" >
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
								
								<TD width="19%" class="plainlabel" ><%=Utility.GLanguage("Địa Bàn",session,jedis) %> </TD>
								<TD class="plainlabel" >
								<TABLE cellpadding="0" cellspacing="0">
								<TR><TD>
								<select name="tinhthanhId" onchange="submitCBO(2);" class="select2" style="width: 200px" >
                                 <option value ="" > </option>  
                               <%
                               
                               while(tinhthanhRs.next())
                               {
                               if(tinhthanhRs.getString("pk_seq").equals(obj.getTinhthanhId())) {
                            	%><option value ="<%=tinhthanhRs.getString("pk_seq") %>" selected> <%=tinhthanhRs.getString("ten") %></option>  
                            	  <%} else { %>
                            	  <option value ="<%=tinhthanhRs.getString("pk_seq") %>"> <%=tinhthanhRs.getString("ten") %></option>
                              <%}} %>                             </select>		   										</TD>
                                    		</TR>
								</TABLE>
								</TD>
								
								</tr>
								
								
								
								
								
								<tr  style="display: none">
									<td width="19%" class="plainlabel" ><%=Utility.GLanguage("Trạng thái",session,jedis) %> </td>
									<td class="plainlabel" >
										<table cellpadding="0" cellspacing="0">
											<tr>
												<td>
													<select name="status" onchange="submitCBO(0);">
														
														<%if(obj.getStatus().equals("1")) {%>
															<option selected="selected" value="1"><%=Utility.GLanguage("Hoạt động",session,jedis) %></option>
															<option value="0"><%=Utility.GLanguage("Không Hoạt Động",session,jedis) %></option>
														<%}else {%>
															<option value="1"><%=Utility.GLanguage("Hoạt động",session,jedis) %></option>
															<option selected="selected" value="0"><%=Utility.GLanguage("Không Hoạt Động",session,jedis) %></option>
														<%} %>
													</select>
												</td>
											</tr>
										</table>
									
									</td>
								</tr>
								
								<tr>
								  	<TD width="19%" class="plainlabel" ><%=Utility.GLanguage("Chi nhánh / Nhà phân phối",session,jedis) %></TD>
								  	<TD class="plainlabel" >
										<TABLE cellpadding="0" cellspacing="0">
											<TR><TD>
								<select name="nppId" onchange="submitCBO(1);"  class="select2" style="width: 280px">
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
										<TD width="19%" class="plainlabel" ><%=Utility.GLanguage("NHÂN VIÊN BÁN HÀNG",session,jedis) %></TD>
								  	<TD class="plainlabel" >
										<TABLE cellpadding="0" cellspacing="0">
											<TR><TD>
								<select name="ddkdId" class="select2" style="width: 200px" onchange = "submitCBO(0);">
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
										<TD width="19%" class="plainlabel" ><%=Utility.GLanguage("Tuyến",session,jedis) %></TD>
								  	<TD class="plainlabel" >
										<TABLE cellpadding="0" cellspacing="0">
								<TR><TD>
								<select name="tuyenId"  class="select2" style="width: 200px">
                                 <option value ="0"> </option>  
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
										<!-- <a class="button3" href="javascript:exportToMCP()">
											<img style="top: -4px;" src="../images/button.png" alt=""> Lộ trình bán hàng </a> 
										&nbsp;&nbsp;&nbsp;&nbsp; -->
										<a class="button3" href="javascript:exportToDetail()">
											<img style="top: -4px;" src="../images/button.png" alt=""> <%=Utility.GLanguage("Lộ trình chi tiết",session,jedis) %> </a>
										
										
									</td>
								</TR>
                               </TABLE>
                               <!-- <a class="button3" href="javascript:exportToExcel()">Xuất ra excel </a> -->
                               </FIELDSET>
                               
                               </TD>
                               </TR>
                               
    							  
                               </TABLE>
                                
								                        

			
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
	try
	{
		if(!(danhsach == null))danhsach.close();
		if(ddkd != null)ddkd.close();
		if(npp != null)npp.close();
		if(tuyen != null)tuyen.close();
		if(khuvuc!=null)khuvuc.close();
		if(vungRs!=null)vungRs.close();
		if(tinhthanhRs!=null)tinhthanhRs.close();
		if(obj != null){obj.DBclose();
		obj = null;}
		session.setAttribute("obj", null);
	}catch(java.sql.SQLException e){}
	}
%>