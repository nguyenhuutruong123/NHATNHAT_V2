<%@page import="java.util.Hashtable"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.Calendar" %>
<%@ page import="java.util.Date" %>
<%@ page import="java.util.List" %>
<%@ page  import = "java.util.Iterator" %>
<%@ page  import = "java.sql.ResultSet" %>
<%@page import="java.sql.SQLException" %>
<%@ page import = "geso.dms.distributor.beans.tratichluy.*" %>
<%@ page import = "geso.dms.center.util.*" %>

<%	String userId = (String) session.getAttribute("userId");  
	String userTen = (String) session.getAttribute("userTen");  	
	String sum = (String) session.getAttribute("sum");
	Utility util = (Utility) session.getAttribute("util");
	if(!util.check(userId, userTen, sum)){
		response.sendRedirect("/SalesUpERP/index.jsp");
	}else{ %>

<%
 	ITratichluy obj =(ITratichluy)session.getAttribute("csxBean");
	ResultSet khRs = obj.getKhRs();
	ResultSet spRs = obj.getSpTraRs();
	Hashtable<String, String> kh_spTra = obj.getKh_SPTra();
%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"> <% Utility Utilback = new Utility(); %>
<HTML>
<HEAD>
<TITLE><%= getServletContext().getInitParameter("TITLENAME") %></TITLE>
<META http-equiv="Content-Type" content="text/html; charset=utf-8">
<META http-equiv="Content-Style-Type" content="text/css">

<LINK rel="stylesheet" href="../css/main.css" type="text/css">
<script type="text/javascript"	src="../scripts/jquery.min.1.7.js"></script>
<script type="text/javascript" language="JavaScript" src="../scripts/jquery.tools.min.js"></script>

<link href="../css/jquery-ui.css" rel="stylesheet" type="text/css" />
<script src="../scripts/ui/jquery.ui.core.js" type="text/javascript"></script>
<script src="../scripts/ui/jquery.ui.widget.js" type="text/javascript"></script>
<script type="text/javascript" src="../scripts/dropdowncontent.js"></script>
<LINK rel="stylesheet" type="text/css" href="../css/style.css" />

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
	    document.forms["khtt"].submit();
	}
	
	function keypress(e) //Hàm dùng d? ngan ngu?i dùng nh?p các ký t? khác ký t? s? vào TextBox
	{    
		var keypressed = null;
		if (window.event)
			keypressed = window.event.keyCode; //IE
		else
			keypressed = e.which; //NON-IE, Standard
		
		if (keypressed < 48 || keypressed > 57)
		{ 
			if (keypressed == 8 || keypressed == 127 || keypressed == 37 || keypressed == 39 || keypressed == 0 || keypressed == 46)
			{//Phím Delete và Phím Back
				return;
			}
			return false;
		}
	}
	
	function save()
	{
	  document.forms["khtt"].action.value = "save";
	  document.forms["khtt"].submit(); 
    }
	
	function submitform()
	{
	  document.forms["khtt"].action.value = "submit";
	  document.forms["khtt"].submit(); 
    }
	
	function SelectALL(e)
	{
		//alert(e.checked);
		var khIds = document.getElementsByName("khachhangIds");
		for(i = 0; i < khIds.length; i++ )
		{
			if(e.checked == true)
				khIds.item(i).checked = true;
			else
				khIds.item(i).checked = false;
		}
	}
	
</SCRIPT>
</HEAD>
<BODY leftmargin="0" bottommargin="0" topmargin="0" rightmargin="0">
<form name ="khtt" method="post" action="../../TratichluyUpdateSvl" >
<input type="hidden" name="userId" value='<%= userId %>' >
<input type="hidden" name="id" value='<%= obj.getId() %>' >
<input type="hidden" name="nppId" value='<%= obj.getNppId() %>' >

<input type="hidden" name="action" value="0">
<TABLE width="100%" border="0" cellspacing="0" cellpadding="0"
	height="100%">
	<TR>
		<TD colspan="4" align='left' valign='top' bgcolor="#FFFFFF">
			<TABLE width="100%" border="0" cellpadding="0" cellspacing="1">
				<TR>
					<TD align="left" class="tbnavigation">
						<table width="100%" border="0" cellpadding="0" cellspacing="0">
						  <tr height="22">
							 <TD align="left" colspan="2" class="tbnavigation">&nbsp;Quản lý khuyến mại > Trả khuyến mại tích lũy > Cập nhật</TD> 
							 <TD colspan="2" align="right" class="tbnavigation"><%=Utility.GLanguage("Chào mừng bạn",session,jedis) %>  <%= userTen %></TD></tr>
						</table>
			 		</TD>
				</TR>
			</TABLE>
		<TABLE width="100%" border="0" cellpadding="0" cellspacing="1">
			<TR ><TD >
					<TABLE width="100%" border="0" cellpadding="0" cellspacing="0">
						<TR class = "tbdarkrow">
							<TD width="30" align="left">
								<A href="../../TratichluySvl?userId=<%= userId%>" ><img src="../images/Back30.png" alt="Quay ve"  title="Quay ve" border="1" longdesc="Quay ve" style="border-style:outset"></A></TD>
						    <TD width="2" align="left" ></TD>
						    <td>
						    	<A href="javascript:save();" >
        							<IMG src="../images/Save30.png" title="Luu lai" alt="Luu lai" border ="1px" style="border-style:outset"></A>
						    </td>
							<TD >&nbsp; </TD>						
						</TR>
					</TABLE>
			</TD></TR>
		</TABLE>
			<TABLE width="100%" border="0" cellpadding="0"  cellspacing="1" >
			  	<tr>
					<TD align="left" colspan="4" class="legendtitle">
						<FIELDSET>
						<LEGEND class="legendtitle">Thông báo </LEGEND>
				
	    				<textarea name="dataerror"  style="width: 100% ; color:#F00 ; font-weight:bold"  style="width: 100% ; color:#F00 ; font-weight:bold" readonly="readonly" rows="1"><%= obj.getMsg() %></textarea>
						</FIELDSET>
				   </TD>
				</tr>			

				<TR>
				  <TD height="100%" width="100%">
						<FIELDSET >
						<LEGEND class="legendtitle" style="color:black">Thông tin trả khuyến mại tích lũy</LEGEND>
						<TABLE border="0" width="100%" cellpadding="6" cellspacing="0">
                           	
                             <tr>
                             	<TD class="plainlabel" width="150px" >Mã CTKM</TD>
								<TD class="plainlabel"  >
									<input name="scheme" type="text"  value="<%= obj.getScheme()  %>" readonly="readonly"  >
								</TD>
                             </tr>
                             
                             <tr>
                             	<TD class="plainlabel" >Ngày duyệt </TD>
								<TD class="plainlabel" >
									<input type="text" name="ngayduyet" value="<%= obj.getNgayduyet() %>" >
								</TD>
                             </tr>
                          	
						</TABLE>
						
						<hr />
						<table width="100%" cellpadding="0px" cellspacing="1px">
							<tr class="tbheader">
								<th align="center" width="10%">Mã khách hàng</th>
								<th align="center" width="25%">Tên khách hàng</th>
								<th align="center" width="25%">Địa chỉ</th>
								<th align="center" width="10%"><%=Utility.GLanguage("Trạng thái",session,jedis) %></th>
								<th align="center" width="10%">Tổng lượng</th>
								<th align="center" width="10%"><%=Utility.GLanguage("Sản phẩm",session,jedis) %></th>
								<th align="center" width="10%">Duyệt trả <input type="checkbox" onchange="SelectALL(this);" > </th>
							</tr>
							
							<% if(khRs != null) { 
								khRs.beforeFirst();
								while(khRs.next())
								{
									%>
									<TR>
										<TD> 
											<input style="width: 100%" name="ma" value="<%= khRs.getString("SMARTID") %>"  readonly="readonly" > 
											<input style="width: 100%" type="hidden" name="idkhachhang" value="<%= khRs.getString("KHID") %>"  readonly="readonly" > 
										</TD>
										<TD> 
											<input style="width: 100%" name="tenkhachhang" value="<%= khRs.getString("TEN") %>" readonly="readonly" > 
										</TD>
										<TD> 
											<input style="width: 100%" name="diachi" value="<%= khRs.getString("DIACHI") %>" readonly="readonly" > 
										</TD>
										<TD> 
											<input style="width: 100%; <%= (khRs.getString("trangthai").equals("Chưa trả") ? "color:red;" : "" ) %> " name="trangthai" value="<%= khRs.getString("trangthai") %>" readonly="readonly" > 
										</TD>
										<TD> 
											<input style="width: 100%; text-align: right;" name="tongluong" value="<%= khRs.getString("Thuong")  %>" readonly="readonly"  > 
										</TD>
										
										<TD align="center" > 
											
											<a href="" id="kh_<%= khRs.getString("KHID") %>" rel="subcontent<%= khRs.getString("KHID")  %>">
				           	 				&nbsp; <img alt="Chọn sản phẩm" src="../images/vitriluu.png"></a>
				           	 		
				           	 		 		<DIV id="subcontent<%= khRs.getString("KHID") %>" style="position:absolute; visibility: hidden; border: 9px solid #80CB9B;
							                             background-color: white; width: 500px; max-height:300px; overflow:auto; padding: 4px;">
							                    <table width="90%" align="center">
							                        <tr>
							                            <th width="100px" style="font-size: 12px">Mã</th>
							                            <th width="250px" style="font-size: 12px">Tên</th>
							                            <th width="100px" style="font-size: 12px">Số lượng</th>
							                        </tr>
					                            	<%
						                        		if(spRs != null)
						                        		{
						                        			spRs.beforeFirst();
						                        			while(spRs.next())
						                        			{  
						                        				String spTra = kh_spTra.get(khRs.getString("KHID"));
						                        			%>
						                        			
						                        			<tr>
						                        				<td>
						                        					<input type="text" style="width: 100%" value="<%= spRs.getString("ma") %>" readonly="readonly">
						                        					<input type="hidden" name="<%= khRs.getString("KHID") %>_spId"  style="width: 100%" value="<%= spRs.getString("spId") %>" readonly="readonly">
						                        				</td>
						                        				<td>
						                        					<input type="text" style="width: 100%" value="<%= spRs.getString("ten") %>" readonly="readonly">
						                        				</td>
						                        				<td align="center">
						                        				<% if(khRs.getString("trangthai").equals("Chưa trả")){ %>
						                        					<input type="text" name="<%= khRs.getString("KHID") %>_SoLuong" style="width: 100%; text-align: right;" value="<%= obj.getSoluong_From_KhachHang(spTra, spRs.getString("spId"))  %>"  onkeypress="return keypress(this);" >
						                        				<% } else { %>
						                        					<input type="text" name="<%= khRs.getString("KHID") %>_SoLuong" style="width: 100%; text-align: right;" value="<%= obj.getSoluong_From_KhachHang(spTra, spRs.getString("spId"))  %>"  readonly="readonly" >
						                        				<% } %>
						                        				</td>
						                        			</tr>
						                        			
						                        		 <% } } %>
						                             
							                    </table>
							                     <div align="right">
							                     	<label style="color: red" ></label>
							                     	&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
							                     	<a href="javascript:dropdowncontent.hidediv('subcontent<%= khRs.getString("KHID") %>')" > Đóng lại </a>
							                     </div>
							            </DIV> 
											
										</TD>
										<TD align="center" > 
											<% if(khRs.getString("trangthai").equals("Chưa trả")){ %>
												<% if(obj.getKhIds().contains(khRs.getString("KHID"))) { %>
													<input type="checkbox" name="khachhangIds" value="<%=  khRs.getString("KHID") %>" checked="checked" >
												<% } else { %> 
													<input type="checkbox" name="khachhangIds" value="<%=  khRs.getString("KHID") %>"  >
												<% } %>
											<% } %>
										</TD>
									</TR>
								<%  }
								
							} %>
							
						</table>
						
							
						</FIELDSET>						
					</TD>
				</TR>
			</TABLE>
		</TD>
	</TR>
	</TABLE>
</form>

<script type="text/javascript">
<%
	if(khRs != null)
	{
		khRs.beforeFirst();
		while(khRs.next())
		{
			%>
			dropdowncontent.init('kh_<%= khRs.getString("KHID") %>', "left-top", 300, "click");
		<% }
	}
%>
</script>

<%

	khRs.close();
	spRs.close();
%>

</BODY>
</HTML>
<%}%>
