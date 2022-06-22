<%@page import="java.sql.SQLException"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page  import = "geso.dms.distributor.beans.BacSi.IBacSi" %>
<%@ page  import = "java.sql.ResultSet" %>
<%@ page  import = "java.util.Hashtable" %>
<%@ page  import = "geso.dms.center.util.*" %>
<%@ page import="java.text.DecimalFormat"%>
<%@ page import="java.text.NumberFormat"%>

<%	
	NumberFormat formatter = new DecimalFormat("#,###,###.##");
	String userId = (String) session.getAttribute("userId");  
	String userTen = (String) session.getAttribute("userTen");  	
	String sum = (String) session.getAttribute("sum");
	Utility util = (Utility) session.getAttribute("util");
	
	util.getIdNhapp(userId);
	
	if(!util.check(userId, userTen, sum)){
		response.sendRedirect(request.getContextPath() + "/redirect.jsp");
	}else{ %>
<% IBacSi khBean = (IBacSi)session.getAttribute("khBean");
	ResultSet lch=khBean.getRskhachhang();

%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"> <% Utility Utilback = new Utility(); %>
<HTML>
<HEAD>
<TITLE><%= getServletContext().getInitParameter("TITLENAME") %></TITLE>
<META http-equiv="Content-Type" content="text/html; charset=utf-8">

<META http-equiv="Content-Style-Type" content="text/css">

<LINK rel="stylesheet" href="../css/main.css" type="text/css">

	<LINK rel="stylesheet" href="../css/datepicker.css" type="text/css">
    <script language="javascript" src="../scripts/datepicker.js"></script>
   	<link href="../css/jquery-ui.css" rel="stylesheet" type="text/css"/>
  	<script type="text/javascript" src="../scripts/Timepicker/jquery.min.js"></script>
  	<script type="text/javascript" src="../scripts/Timepicker/jquery-ui.min.js"></script>

	<script type="text/javascript" src="../scripts/jquery.min.1.7.js"></script>
	<link href="../css/jquery-ui.css" rel="stylesheet" type="text/css" />
	<script src="../scripts/ui/jquery.ui.core.js" type="text/javascript"></script>
	<script src="../scripts/ui/jquery.ui.widget.js" type="text/javascript"></script>
	<script type="text/javascript" src="../scripts/phanTrang.js"></script>
	<script src="../scripts/ui/jquery.ui.datepicker.js" type="text/javascript"></script>
	<link media="screen" rel="stylesheet" href="../css/colorbox.css">
	<script src="../scripts/colorBox/jquery.colorbox.js"></script>

	
   

<SCRIPT language="javascript" type="text/javascript">

function removeDisable()
{
	  	document.getElementById("tpId").removeAttribute('disabled');
		 document.getElementById("qhId").removeAttribute('disabled');
}

function addThem(e)
{
	//removeDisable();
	document.forms["khForm"].action.value = "addThem";
    document.forms["khForm"].submit();
    
    
}
function Xoa(i)
{
	//removeDisable();
	document.forms["khForm"].action.value = "Xoa";
	document.forms["khForm"].xoaId.value = i;
    document.forms["khForm"].submit();
}


	function submitform() {
		document.forms["khForm"].submit();
	}

	function saveform() {
		document.getElementById("btnSave").innerHTML = "<a href='#'><img src='../images/waiting.gif'  title='cho thuc hien..' border='1' longdesc='cho thuc hien..' style='border-style:outset'> Processing...</a>";
		document.forms['khForm'].action.value = 'save';
		document.forms['khForm'].submit();
	}
	

	

	
</SCRIPT>
<link href="../css/select2.css" rel="stylesheet" />
	<script src="../scripts/select2.js"></script>
	<script>
    	$(document).ready(function() { 
    		$("select:not(.notuseselect2)").select2({ width: 'resolve' });     
    	});
    	
    	$( document ).on( "mousemove", function( event ) {
      	  
    		document.forms["khForm"].pageX.value = event.pageX;
    		document.forms["khForm"].pageY.value = event.pageY;
    		
    	});
    	
    </script>

<style type="text/css">
.btn {
  display: inline-block;
  padding: 6px 12px;
  margin-bottom: 0;
  font-size: 14px;
  font-weight: normal;
  line-height: 1.42857143;
  text-align: center;
  white-space: nowrap;
  vertical-align: middle;
  -ms-touch-action: manipulation;
      touch-action: manipulation;
  cursor: pointer;
  -webkit-user-select: none;
     -moz-user-select: none;
      -ms-user-select: none;
          user-select: none;
  background-image: none;
  border: 1px solid transparent;
  border-radius: 4px;
}
.btn:focus,
.btn:active:focus,
.btn.active:focus,
.btn.focus,
.btn:active.focus,
.btn.active.focus {
  outline: thin dotted;
  outline: 5px auto -webkit-focus-ring-color;
  outline-offset: -2px;
}
.btn:hover,
.btn:focus,
.btn.focus {
  color: #333;
  text-decoration: none;
}
.btn:active,
.btn.active {
  background-image: none;
  outline: 0;
  -webkit-box-shadow: inset 0 3px 5px rgba(0, 0, 0, .125);
          box-shadow: inset 0 3px 5px rgba(0, 0, 0, .125);
}
.btn.disabled,
.btn[disabled],
fieldset[disabled] .btn {
  pointer-events: none;
  cursor: not-allowed;
  filter: alpha(opacity=65);
  -webkit-box-shadow: none;
          box-shadow: none;
  opacity: .65;
}
.btn-default {
  color: #333;
  background-color: #fff;
  border-color: #ccc;
}
.btn-default:hover,
.btn-default:focus,
.btn-default.focus,
.btn-default:active,
.btn-default.active,
.open > .dropdown-toggle.btn-default {
  color: #333;
  background-color: #e6e6e6;
  border-color: #adadad;
}
.btn-default:active,
.btn-default.active,
.open > .dropdown-toggle.btn-default {
  background-image: none;
}
.btn-default.disabled,
.btn-default[disabled],
fieldset[disabled] .btn-default,
.btn-default.disabled:hover,
.btn-default[disabled]:hover,
fieldset[disabled] .btn-default:hover,
.btn-default.disabled:focus,
.btn-default[disabled]:focus,
fieldset[disabled] .btn-default:focus,
.btn-default.disabled.focus,
.btn-default[disabled].focus,
fieldset[disabled] .btn-default.focus,
.btn-default.disabled:active,
.btn-default[disabled]:active,
fieldset[disabled] .btn-default:active,
.btn-default.disabled.active,
.btn-default[disabled].active,
fieldset[disabled] .btn-default.active {
  background-color: #fff;
  border-color: #ccc;
}
.btn-default .badge {
  color: #fff;
  background-color: #333;
}
</style>

</HEAD>
<BODY leftmargin="0" bottommargin="0" topmargin="0" rightmargin="0">
	<form name="khForm" method="post" action="../../BacSiUpdateSvl">
		<input type="hidden" name="xoaId" value="-1">
		<input type="hidden" name="userId" value='<%=userId %>'> 
		<input type="hidden" name="nppId"  value='<%= khBean.getNppid()%>' >
		<input type="hidden" name="action" value='1'> 
		

		<TABLE width="100%" border="0" cellspacing="0" cellpadding="0"
			height="100%">
			<TR>
				<TD colspan="4" align='left' valign='top' bgcolor="#FFFFFF">
					<TABLE width="100%" border="0" cellpadding="0" cellspacing="2">
						<TR>
							<TD align="left" class="tbnavigation">
								<table width="100%" border="0" cellpadding="0" cellspacing="0">
									<tr height="22">
										<TD align="left" colspan="2" class="tbnavigation">&nbsp;Thiết
											lập dữ liệu nền> Dữ liệu Kinh doanh > Bác Sĩ > Tạo mới
										<TD colspan="2" align="right" class="tbnavigation">Chào
											mừng <%= khBean.getNppten() %>&nbsp;
									</tr>
								</table></TD>
						</TR>
					</TABLE>
					<TABLE width="100%" cellpadding="0" cellspacing="2">
						<TR>
							<TD>
								<TABLE width="100%" border="0" cellpadding="0" cellspacing="0">
									<TR class="tbdarkrow">
										<TD width="30" align="left">
								
										<A href="../../BacSiSvl?userId=<%=userId %>"><img src="../images/Back30.png" alt="Quay ve" title="Quay ve" width="30" height="30" border="1" longdesc="Quay ve" style="border-style: outset"> </A>
										
										</TD>
										<TD width="2" align="left"></TD>
										<TD width="30" align="left">
											<div id="btnSave">
												<A href="javascript:saveform()"><IMG
													src="../images/Save30.png" title="Luu lai" alt="Luu lai"
													border="1" style="border-style: outset">
												</A>
											</div></TD>
										<TD align="left">&nbsp;</TD>
									</TR>
								</TABLE></TD>
						</TR>
					</TABLE>
					<TABLE width="100%" border="0" cellpadding="0" cellspacing="0">
						<tr>
							<TD align="left" colspan="4" class="legendtitle">
								<FIELDSET>
									<LEGEND class="legendtitle"><%=Utility.GLanguage("Báo lỗi nhập liệu",session,jedis) %></LEGEND>
									<textarea name="dataerror"
										style="width: 100%; color: #F00; font-weight: bold"
										style="width:100%" rows="1"><%= khBean.getMsg() %></textarea>
									<%khBean.setMsg(""); %>
								</FIELDSET></TD>
						</tr>

						<TR>
							<TD height="100%" width="100%">
								<FIELDSET>
									<LEGEND class="legendtitle" style="color: black">Thông tin Bác Sĩ</LEGEND>
									<TABLE border="0" width="100%" cellpadding="6" cellspacing="0">
									<TR>
									
										<TD class="plainlabel" >Mã Bác Sĩ<FONT class="erroralert"> *</FONT></TD>
										<TD class="plainlabel"  ><INPUT type="text" name="ma" value="<%= khBean.getMa() %>" ></TD>
										
										<TD class="plainlabel" >Tên Bác Sĩ<FONT class="erroralert"> *</FONT></TD>
										<TD class="plainlabel"  ><INPUT type="text" name="ten" value="<%= khBean.getTen()%>" ></TD>
										
									
									</TR>	
									
									<TR>
									
										<TD class="plainlabel" >Số điện thoại</TD>
										<TD class="plainlabel"  ><INPUT type="text" name="sodienthoai" value="<%= khBean.getSDT() %>" ></TD>
										
										<TD class="plainlabel" >Địa chỉ</TD>
										<TD class="plainlabel"  ><INPUT type="text" name="diachi" value="<%= khBean.getDiachi() %>" ></TD>
										
									
									</TR>	
									
									
									<TR>
									
										<TD class="plainlabel" >Email</TD>
										<TD class="plainlabel"  ><INPUT type="text" name="email" value="<%= khBean.getEmail() %>" ></TD>
										
										<TD class="plainlabel" >Khách hàng ETC</TD>
										<TD class="plainlabel"  >
											<SELECT name="khid" id="khid"  multiple="multiple">
													<%if (lch != null)
														while (lch.next()) {
															if (khBean.getKhid().indexOf(lch.getString("pk_seq")) >=0){	%>
															<option value="<%=lch.getString("pk_seq")%>" selected><%=lch.getString("ten") %></option>
														<%} else {%>
															<option value="<%=lch.getString("pk_seq")%>"><%=lch.getString("ten")%></option>
												<%}}%>
												</SELECT>
										</TD>
										
									
									</TR>	
						
									<TR>
									
										<TD class="plainlabel" ><%=Utility.GLanguage("Hoạt động",session,jedis) %></TD>
										<TD class="plainlabel"  ><INPUT type="checkbox" name="trangthai" <%if(khBean.getTrangthai().equals("1")){ %> checked="checked" <%} %>  value="1" ></TD>
										
										<TD class="plainlabel" ></TD>
										<TD class="plainlabel"  ></TD>
										
									
									</TR>	
						
									</TABLE>
								</FIELDSET></TD>
							</TR>


					</TABLE>


			</TR>
		</TABLE>
	</form>
</BODY>
</HTML>

<% 	

if(khBean != null){
	khBean = null;
}

	try{

	
	session.setAttribute("khBean",null);
	}
	catch (Exception e) {}
	
%>
<%}%>
