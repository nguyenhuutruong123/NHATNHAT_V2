<%@page import="java.sql.SQLException"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page  import = "java.sql.ResultSet" %>
<%@ page  import = "geso.dms.center.util.*" %>
<%@ page  import = "geso.dms.distributor.beans.erp_chinhanh.IErp_chinhanhList" %>
<%@ page  import = "geso.dms.distributor.beans.erp_chinhanh.imp.Erp_chinhanhList" %>
<%@ page  import = "geso.dms.center.util.*" %>
<%	String userId = (String) session.getAttribute("userId");  
	String userTen = (String) session.getAttribute("userTen");  	
	String sum = (String) session.getAttribute("sum");
	Utility util = (Utility) session.getAttribute("util");
	if(!util.check(userId, userTen, sum)){
		response.sendRedirect("/Phanam/index.jsp");
	}else{ %>


<% 	IErp_chinhanhList cnList = (IErp_chinhanhList)session.getAttribute("cnList");
	ResultSet Rscn = cnList.getRscn();
	 int[] quyen = new  int[5];
		quyen = util.Getquyen("Erp_ChiNhanhSvl","",userId);
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"> <% Utility Utilback = new Utility(); %>

<html>
<head>
<title>Phanam - Chi Nhánh</title>
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


<script language="javascript" type="text/javascript">

function xoaform()
{
    document.cnForm.ma.value = "";
    document.cnForm.ten.value = "";
    document.cnForm.tt.value = "";
    submitform();
}

function submitform()
{
	document.forms['cnForm'].action.value= 'search';
	document.forms['cnForm'].submit();
}

function newform()
{
	document.forms['cnForm'].action.value= 'new';
	document.forms['cnForm'].submit();
}


function thongbao()
{
	 var tt = document.forms["cnForm"].msg.value;
	 if(tt.length>0)
    	alert(document.forms["cnForm"].msg.value);
	}

</script>

	<link href="../css/select2.css" rel="stylesheet" />
<script src="../scripts/select2.js"></script>
<script>
	$(document).ready(function()
	{
		$(".select2").select2();
	});
</script>
</head>

</head>
<body leftmargin="0" bottommargin="0" topmargin="0" rightmargin="0">
<form name="cnForm" method="post" action="../../Erp_ChiNhanhSvl">
<input type="hidden" name="userId" value='<%=userId%>'>
<input type="hidden" name="action" value='1'>
<input type="hidden" name="msg" value='<%= cnList.getMsg() %>'>
<input type="hidden" name="chixem" value='<%= cnList.getChixem() %>'>
<script language="javascript" type="text/javascript">
    thongbao();
</script> 

<table width="100%" border="0" cellspacing="0" cellpadding="0"
	height="100%">
	<tr>
		<td colspan="4" align='left' valign='top' bgcolor="#FFFFFF">
			<table width="100%" cellpadding="0" cellspacing="1">		
				<tr>
					<td align="left">
					  <table width="100%" border="0" cellpadding="0" cellspacing="0">
						  <tr height="22">
						   <td align="left" colspan="2" class="tbnavigation">&nbsp;Dữ liệu nền &gt; Kế toán &gt; Chi nhánh					   
						   </td>
   
						   <td align = "right" colspan="2" class="tbnavigation"><%=Utility.GLanguage("Chào mừng bạn",session,jedis) %>  <%=userTen %> &nbsp;</td>
						  </tr>
 					  </table>
					</td>
				</tr>
				<tr>
					<td>
					<table border="0" width="100%" cellpadding="0" cellspacing="0">
						<tr>
							<td width="100%" align="center" >
							<fieldset>
							  <legend class="legendtitle"><%=Utility.GLanguage("Tiêu chí tìm kiếm",session,jedis) %> &nbsp;</legend>
							<table width="100%" cellpadding="6" cellspacing="0">
								<tr>
								  	<td class="plainlabel" style="width:15%">Mã chi nhánh</td>
								    <TD class="plainlabel"><input type="text" name="ma" value="<%=cnList.getMA() %>" onchange="submitform()"></td>
						      	</tr>
						      	
						      	<tr>
								  	<td class="plainlabel" style="width:15%">Tên chi nhánh</td>
								    <TD class="plainlabel"><input type="text" name="ten" value="<%= cnList.getTEN() %>" onchange="submitform()"></td>
						      	</tr>
								
								<tr>
						      		<td class = "plainlabel" style="width:15%"><%=Utility.GLanguage("Trạng thái",session,jedis) %></td>
									<td class = "plainlabel" >
										<select name = "tt" style="width:200px" onchange = "submitform()" class="select2"  >
										
										<%if(cnList.getTRANGTHAI().equals("1")  ) {%>
										<option value = "1" selected="selected"><%=Utility.GLanguage("Hoạt động",session,jedis) %></option>
										<option value = "0"><%=Utility.GLanguage("Ngưng hoạt động",session,jedis) %></option>
										<option value = "" ></option>
										<%} else if(cnList.getTRANGTHAI().equals("0")){ %>
											<option value = "0"  selected="selected"><%=Utility.GLanguage("Ngưng hoạt động",session,jedis) %></option>
										  	<option value = "1" ><%=Utility.GLanguage("Hoạt động",session,jedis) %></option>
											<option  value="" > </option>
										  <%} else  {%>
										  	<option  value="" > </option>
										  <option value = "0"  ><%=Utility.GLanguage("Ngưng hoạt động",session,jedis) %></option>
										  	<option value ="1" ><%=Utility.GLanguage("Hoạt động",session,jedis) %></option>
										
										  <%} %>
										</select>
									</td>
								</tr>

							    <tr>
									<td colspan="2" class="plainlabel">
									<a class="button2" href="javascript:xoaform()">
									<img style="top: -4px;" src="../images/button.png" alt=""><%=Utility.GLanguage("Nhập lại",session,jedis) %></a>&nbsp;&nbsp;&nbsp;&nbsp;
									</td>									
								</tr>
								
							</table>

							</fieldset>
						</td>
						</tr>
					</table>
					</td>
				</tr>

				<tr>
					<td width="100%">
						<fieldset>
						<legend class="legendtitle">&nbsp;Chi nhánh &nbsp;&nbsp;&nbsp;
						
					 	</legend>
	
		   				<table width="100%" border="0" cellpadding="0" cellspacing="0">
			  				<tr>
			  	   				<td align="left" colspan="4" class="legendtitle">
									<table width="100%" border="0" cellspacing="1" cellpadding="4">
										<tr class="tbheader">
											<th width="5%">Mã chi nhánh</th>
											<th width="7%">Tên chi nhánh</th>
											<th width="6%"><%=Utility.GLanguage("Trạng thái",session,jedis) %></th>	
											<th width="5%"><%=Utility.GLanguage("Ngày tạo",session,jedis) %></th>
<!-- 											<th width="5%"><%=Utility.GLanguage("Người tạo",session,jedis) %></th> -->
											<th width ="5%"><%=Utility.GLanguage("Ngày sửa",session,jedis) %></th>											
											<!-- <th width ="5%"><%=Utility.GLanguage("Người sửa",session,jedis) %></th> -->
														
											<th width="1%" align="center">&nbsp;Tác vụ</th>					
										</tr>
				
									<%
									int m = 0;
									String lightrow = "tblightrow";
									String darkrow = "tbdarkrow";
									if(Rscn!=null)
									while (Rscn.next())
									{
										if(m % 2 != 0) {%>
										<tr class = <%=lightrow%> >
									<%} else {%>
										<tr class = <%=darkrow%> >
									<%}%>
										<td align="center"><%= Rscn.getString("ma")%> </td>
										<td align="center"><%= Rscn.getString("ten")%> </td>
										
											<% 
										if(Rscn.getString("tt").equals("1")){
										%>	
											<td align="center">Hoạt Động </td>
											<%
										}else{
											%>
											<td align="center">Ngưng Hoạt Động </td>
											<% 
										}
										%>
										
										<td align="center"><%= Rscn.getString("ngaytao") %> </td>
										<%-- <td align="center"><%= Rscn.getString("nguoitao")%> </td> --%>
										<td align="center"><%= Rscn.getString("ngaysua")%> </td>
										<%-- <td align="center"><%= Rscn.getString("nguoisua")%> </td>							 --%>			
									
										<td align = "center">
											<table border = 0 cellpadding="0" cellspacing="0">
												<tr>
												
												<td>
												<!-- QUYEN VIEW DLN --> 
													<a href = "../../Erp_ChiNhanhUpdateSvl?userid=<%=userId%>&display=<%=Rscn.getString("id_cn")%>"><img title="Hiển thị" src="../images/Display20.png" title="Hiển thị" alt="Hien thi" width="20" height="20" longdesc="Hien thi" border = 0></A>
												<!-- END QUYEN VIEW DLN --> 
												</td>
												</tr>
											</table>				
										</tr>
									
									<% 	m++;
									}%>									
									
										<tr>
											<td height="26" colspan="11" align="center" class="tbfooter">&nbsp;	</td>
										</tr>
									</table>
								</td>
							</tr>
						</table>

					</fieldset>
					</td>
				</tr>
			</table>
		</td>
	</tr>
</table>
</form>

</body>
</HTML>
<%
	if (Rscn != null) Rscn.close();
	cnList.DBClose(); 
	session.setAttribute("cnList",null);
	}%>
