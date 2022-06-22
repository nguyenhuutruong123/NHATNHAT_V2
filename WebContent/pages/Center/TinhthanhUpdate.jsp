<%@page import="java.sql.ResultSet"%>
<%@page import="geso.dms.center.beans.tinhthanh.ITinhthanh"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page  import = "geso.dms.center.util.*" %>

<% 
	String userId = (String) session.getAttribute("userId");
	String userTen = (String) session.getAttribute("userTen");
	ITinhthanh tinh = (ITinhthanh)session.getAttribute("tinh");
	ResultSet vungRs = tinh.getVungRs();
	session.setAttribute("db", null);
	
	String sum = (String) session.getAttribute("sum");
	Utility util = (Utility) session.getAttribute("util");

	String url = util.getChuyenNguUrl("TinhthanhSvl", "",session);

	

	String trang = "";
	
	if(tinh.getId() == null || tinh.getId().trim().length() <=0 )
		trang = "Tạo mới";
	else if(tinh.getIsDisplay().equals("1"))
		trang ="Hiển thị";
		else
			trang ="Cập nhật";
	url = url + " > " + trang;
	
	if(!util.check(userId, userTen, sum)){
		response.sendRedirect(request.getContextPath() + "/redirect.jsp");
	}else{
		int[] quyen = new  int[6];
		quyen = util.Getquyen("TinhthanhSvl","",userId);
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd"> <% Utility Utilback = new Utility(); %>
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
	<title><%= getServletContext().getInitParameter("TITLENAME") %></title>
	<META http-equiv="Content-Type" content="text/html; charset=utf-8">
	<META http-equiv="Content-Style-Type" content="text/css">
    <LINK rel="stylesheet" type="text/css" media="print" href="../css/impression.css">
    <LINK rel="stylesheet" href="../css/main.css" type="text/css">
    <LINK rel="stylesheet" href="../css/datepicker.css" type="text/css">
    <script language="javascript" src="../scripts/datepicker.js"></script>
   	<link href="../css/jquery-ui.css" rel="stylesheet" type="text/css"/>
   	
  	<script type="text/javascript" src="../scripts/Timepicker/jquery.min.js"></script>
  	<script type="text/javascript" src="../scripts/Timepicker/jquery-ui.min.js"></script>
  	<script type="text/javascript" src="../scripts/phanTrang.js"></script>
	<script type="text/javascript" src="../scripts/jquery.min.1.7.js"></script>
	<link href="../css/jquery-ui.css" rel="stylesheet" type="text/css" />
	<script src="../scripts/ui/jquery.ui.core.js" type="text/javascript"></script>
	<script src="../scripts/ui/jquery.ui.widget.js" type="text/javascript"></script>
	<script type="text/javascript" src="../scripts/phanTrang.js"></script>
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
   
  	<script type="text/javascript" src="..scripts/jquery-1.js"></script>
	<link type="text/css" rel="stylesheet" href="../css/mybutton.css">
    <script type="text/javascript">
        $(document).ready(function(){
            $(".button").hover(function(){
                $(".button img")
                .animate({top:"-10px"}, 200).animate({top:"-4px"}, 200) // first jump
                .animate({top:"-7px"}, 100).animate({top:"-4px"}, 100) // second jump
                .animate({top:"-6px"}, 100).animate({top:"-4px"}, 100); // the last jump
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
    
    <%if(!tinh.getIsDisplay().equals("1")){ %>
	 function saveform() {
		 document.forms["ckParkForm"].action.value= 'save';
		 document.forms["ckParkForm"].submit();
	 }
	 <%}%>
	</SCRIPT>
	
	<link href="../css/select2.css" rel="stylesheet" />
	<script src="../scripts/select2.js"></script>
	<script>
		$(document).ready(function()
		{
			$(".select2").select2();
		});
	</script>
	
	<script type="text/javascript" src="../scripts/cool_DHTML_tootip.js"></script>
	<style type="text/css">
		#dhtmltooltip
		{
			position: absolute;
			left: -300px;
			width: 150px;
			border: 1px solid black;
			padding: 2px;
			background-color: lightyellow;
			visibility: hidden;
			z-index: 100;
			/*Remove below line to remove shadow. Below line should always appear last within this CSS*/
			filter: progid:DXImageTransform.Microsoft.Shadow(color=gray,direction=135);
		}	
		#dhtmlpointer
		{
			position:absolute;
			left: -300px;
			z-index: 101;
			visibility: hidden;
		}
		th {
		cursor: pointer;
		}	
  	</style>
	
	
</head>
<body>
<form name="ckParkForm" method="post" action="../../TinhthanhUpdateSvl">
<% redis.clients.jedis.Jedis jedis =  geso.dms.center.util.Utility.getJedis(); %><% geso.dms.center.util.Csrf csdr=new geso.dms.center.util.Csrf(request,response,true,false,true);%>
 <input type="hidden" name="<%=csdr.get_tokenName() %>" value='<%=csdr.get_tokenValue() %>'>

<input type="hidden" name="userId" value="<%= userId %>" >
<input type="hidden" name="tinhthanhId" value="<%= tinh.getId() %>" >
<input type="hidden" name="action" value="1" >
<script language="javascript" type="text/javascript">
    thongbao();
</script> 
<table width="100%" border="0" cellspacing="0" cellpadding="0" height="100%">
	<tr>
		<td colspan="4" align='left' valign='top' bgcolor="#ffffff">
			<table width="100%">
				<tr>
					<td align="left" class="tbnavigation">
						<table width="100%" border="0" cellpadding="0" cellspacing="0">
							<tr height="22">
								<td align="left" colspan="2" class="tbnavigation">
							   		&nbsp;<%= url %>
							   	</td>
							   	<td colspan="2" align="right" class="tbnavigation">
							   		<%=Utility.GLanguage("Chào mừng bạn",session,jedis) %>  <%=userTen %>&nbsp;  
							   	</td> 
						    </tr>
   
						   	<tr>
						   		<td align="left" height="5" colspan="4" class="">
						   		</td>
  							</tr>
						</TABLE>
					</td>
				</tr>
			</table>	
			<table width="100%" border="0" cellpadding="3" cellspacing="0">
				<tr>
					<td>
						<table width="100%" border="0" cellpadding="0" cellspacing="0">
							<tr class = "tbdarkrow">
								<td width="30" align="left"><A href="../../RouterSvl?task=<%= Utilback.dongMa(getServletContext().getInitParameter("RedirectNoScript") +"TinhthanhSvl?userId="+userId+"") %>" ><img src="../images/Back30.png" alt="Quay ve"  title="Quay ve" border="1" longdesc="Quay ve" style="border-style:outset"></A></td>
								<td width="2" align="left" ></td>
								
								
								
								<td width="30" align="left" >
								<%if(!tinh.getIsDisplay().equals("1")){ %>
								
									<A href="javascript: saveform()" ><IMG src="../images/Save30.png" title="Luu lai" alt="Luu lai" border = "1"  style="border-style:outset"></A></td>
								<%} %>
								<td>&nbsp; </td>						
							</tr>
						</TABLE>
				</td></tr>
			</table>
				
			<table width = 100% cellpadding = "3" cellspacing = "0" border = "0">
			  	<tr>
					<td align="left" colspan="4" class="legendtitle">
						<fieldset>
						<legend class="legendtitle"><%=Utility.GLanguage("Báo lỗi nhập liệu",session,jedis) %></legend>
				
		    				<textarea name="dataerror"  style="width: 100% ; color:#F00 ; font-weight:bold" style="width: 100%" readonly="readonly" rows="1" ><%= tinh.getMsg() %></textarea>
						</fieldset>
				   </td>
				</tr>
				
				<tr>
					<td align="left" colspan="4" class="legendtitle">
						<fieldset>
						<legend class="legendtitle"><%=Utility.GLanguage("Thông tin tỉnh thành",session,jedis) %>
						</legend>
						<table class="tblight" width="100%" cellspacing="0" cellpadding="6">
						
						<tr>
							<td width="15%" class="plainlabel" colspan="2"><%=Utility.GLanguage("Mã hệ thống",session,jedis) %>: <%=tinh.getId() %>  </td>
							
						</tr>
						<tr>
							<td width="15%" class="plainlabel"><%=Utility.GLanguage("Mã",session,jedis) %><FONT class="erroralert">*</FONT></td>
							<td class="plainlabel">
								<input  type="text"   name="ma"  value='<%= tinh.getMa() %>' style="width:400px">
							</td>
						</tr>
						<tr>
							<td width="15%" class="plainlabel"><%=Utility.GLanguage("Tên",session,jedis) %> <FONT class="erroralert">*</FONT></td>
							<td class="plainlabel">
								<input name="ten" type="text" value='<%= tinh.getTen() %>' style="width:400px">
							</td>
						</tr>
						
						<tr>
							<td class="plainlabel"><%=Utility.GLanguage("Khu vực",session,jedis) %></td>
							<td class="plainlabel">
								<select class="select2" name="vungId">
									<option value=""></option>
									<%
									if (vungRs != null) {
										while (vungRs.next()) {
											if (vungRs.getString("PK_SEQ").equals(tinh.getVungId())) {
												%>
									<option value="<%= vungRs.getString("PK_SEQ") %>" selected="selected"><%= vungRs.getString("TEN") %></option>
												<%
											} else {
												%>
									<option value="<%= vungRs.getString("PK_SEQ") %>"><%= vungRs.getString("TEN") %></option>
												<%
											}
										}
										vungRs.close();
									}
									%>
								</select>
							</td>
						</tr>
						</table>
						</fieldset>
					</td>	
				</tr>
			</table>
		</td>
	</tr>
</table><%geso.dms.center.util.Utility.JedisClose(jedis); %>
</form>
<script type='text/javascript' src='../scripts/loading.js'></script>
</body>
</HTML>
<%
	
	}
%>