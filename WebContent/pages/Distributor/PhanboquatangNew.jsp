<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page  import = "geso.dms.distributor.beans.Phanboquatang.IPhanboquatang" %>
<%@ page  import = "java.sql.ResultSet" %>
<%@ page  import = "geso.dms.center.util.*" %>
<%@ page  import = "java.text.DecimalFormat" %>
<%@ page  import = "java.text.NumberFormat" %>
<%	String userId = (String) session.getAttribute("userId");  
	String userTen = (String) session.getAttribute("userTen");  	
	String sum = (String) session.getAttribute("sum");
	Utility util = (Utility) session.getAttribute("util");
	if(!util.check(userId, userTen, sum)){
		response.sendRedirect(request.getContextPath() + "/redirect.jsp");
	}else{ %>

<% IPhanboquatang bgblcBean = (IPhanboquatang)session.getAttribute("bgblcBean"); %>
	

<% ResultSet sanpham = (ResultSet)bgblcBean.getRssanpham();
	ResultSet rsddkd=(ResultSet)bgblcBean.getRsddkd();
	NumberFormat formatter = new DecimalFormat("#,###,####");
	NumberFormat formatter2 = new DecimalFormat("#,###,###.####");
	
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
<script src="../scripts/ui/jquery.ui.datepicker.js" type="text/javascript"></script>
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
	
<SCRIPT language="javascript" type="text/javascript">

 function DinhDangTien(num) 
 {
    num = num.toString().replace(/\$|\,/g,'');
    if(isNaN(num))
    num = "0";
    sign = (num == (num = Math.abs(num)));
    num = Math.floor(num*100+0.50000000001);
    num = Math.floor(num/100).toString();
    for (var i = 0; i < Math.floor((num.length-(1+i))/3); i++)
    num = num.substring(0,num.length-(4*i+3))+','+
    num.substring(num.length-(4*i+3));
    return (((sign)?'':'-') + num);
}
 function Dinhdang(element)
	{
		element.value=DinhDangTien(element.value);
		if(element.value== '' ||element.value=='0' )
		{
			element.value= '';
		}
	}
 
 
 function saveform() {
		document.getElementById("btnSave").innerHTML = "<a href='#'><img src='../images/waiting.gif'  title='cho thuc hien..' border='1' longdesc='cho thuc hien..' style='border-style:outset'> Processing...</a>";
		document.forms['bgblcForm'].action.value = 'save';
		document.forms['bgblcForm'].submit();
	}
</SCRIPT>

</HEAD>
<BODY leftmargin="0" bottommargin="0" topmargin="0" rightmargin="0">
<form name="bgblcForm" method="post" action="../../PhanboquatangUpdateSvl">
<input type="hidden" name="userId" value='<%=bgblcBean.getUserId() %>'>
<input type="hidden" name="action" value='1'>
 
<TABLE width="100%" border="0" cellspacing="0" cellpadding="0" height="100%">
	<TR>
		<TD colspan="4" align='left' valign='top' bgcolor="#FFFFFF">
		
			<TABLE width="100%" cellpadding="0" cellspacing="1">
				<TR>
					<TD align="left" class="tbnavigation">
					   <table width="100%" border="0" cellpadding="0" cellspacing="0">
						   <tr height="22">
 							   <TD align="left" colspan="2" class="tbnavigation">
 							   		&nbsp;Dữ liệu nền &gt;Sản phẩm &gt; chương trình phân bổ &gt; Tạo mới </TD>
 							   <TD colspan="2" align="right" class="tbnavigation"><%=Utility.GLanguage("Chào mừng bạn",session,jedis) %>  <%=userTen %>&nbsp;</td>
					     </tr>
						</table>
					 </TD>
				  </TR>	
		  	</TABLE>
			<TABLE width="100%" cellpadding="0" cellspacing="1">
			<TR ><TD >
				<TABLE width="100%" border="0" cellpadding="0" cellspacing="0">
					<TR class = "tbdarkrow">
						<TD width="30" align="left"><A href="../../PhanboquatangSvl?userId=<%=userId %>" ><img src="../images/Back30.png" alt="Quay ve"  title="Quay ve" border="1" longdesc="Quay ve" style="border-style:outset"></A></TD>
					    <TD width="2" align="left" ></TD>
					    <TD width="30" align="left"  >
					   <div id="btnSave">
												<A href="javascript:saveform()"><IMG
													src="../images/Save30.png" title="Luu lai" alt="Luu lai"
													border="1" style="border-style: outset">
												</A>
											</div>
						</TD>
					   
					    
				    	<TD align="left" >&nbsp;</TD>
					</TR>
				</TABLE>
			</TD></TR>
			</TABLE>

		<TABLE width="100%"  border = "0" cellspacing="0" cellpadding="0">
			  	<tr>
					<TD align="left" colspan="4" class="legendtitle">
						<FIELDSET>
						<LEGEND class="legendtitle">Bão lỗi nhập liệu </LEGEND>
				
	    				<textarea name="dataerror"  style="width: 100% ; color:#F00 ; font-weight:bold" rows="1" style="width: 100%" readonly="readonly" ><%=bgblcBean.getMsg()%></textarea>
						<% bgblcBean.setMsg(""); %>
						</FIELDSET>
				   </TD>
				</tr>			
		 	
			<TR>
		 	
			<TR>
				<TD >
        			
				<FIELDSET>
				<LEGEND class="legendtitle">&nbsp;Bảng giá bán &nbsp;</LEGEND>
				<TABLE width="100%" cellpadding="0" cellspacing="1">
					<TR><TD>					
						<TABLE  width="100%" cellpadding="4" cellspacing="0">
							<TR>
								<TD  width="15%" class="plainlabel">Tên Chương Trình <FONT class="erroralert">*</FONT></TD>
								<TD class="plainlabel">
								  <input name="MA" type="text" value="<%= bgblcBean.getMa() %>" style="width:300px">
							  	</TD>
							</TR>
							<TR>
								<TD  width="15%" class="plainlabel"><%=Utility.GLanguage("Diễn giải",session,jedis) %> <FONT class="erroralert">*</FONT></TD>
								<TD class="plainlabel">
								  <input name="diengiai" type="text" value="<%= bgblcBean.getDiengiai()%>" style="width:300px">
							  	</TD>
							</TR>
							
							<TR>
								<TD  width="15%" class="plainlabel"><%=Utility.GLanguage("Từ ngày",session,jedis) %> <FONT class="erroralert">*</FONT></TD>
								<TD class="plainlabel">
								  <input name="tungay" class="days" value="<%= bgblcBean.getTungay()%>" style="width:300px">
							  	</TD>
							</TR>
							
							<TR>
								<TD  width="15%" class="plainlabel"><%=Utility.GLanguage("Đến ngày",session,jedis) %> <FONT class="erroralert">*</FONT></TD>
								<TD class="plainlabel">
								  <input name="denngay" class="days" value="<%= bgblcBean.getDenngay()%>" style="width:300px">
							  	</TD>
							</TR>
							
							<TR>
								<TD class="plainlabel"><%=Utility.GLanguage("Sản phẩm",session,jedis) %> <FONT class="erroralert">*</FONT></TD>
								    <TD class="plainlabel">
								    <SELECT   name="sanphamid"  id="sanphamid"  style="width:300px">
								  		<option value =""></option>
								  		
								  	 <% try{ while(sanpham.next()){ 
								    	if(sanpham.getString("pk_seq").equals(bgblcBean.getSanphamid())){ %>
								      		<option value='<%=sanpham.getString("pk_seq") %>' selected><%=sanpham.getString("ten") %></option>
								      	<%}else{ %>
								     		<option value='<%=sanpham.getString("pk_seq") %>'><%=sanpham.getString("ten") %></option>
								     	<%}}}catch(java.sql.SQLException e){} %>	
                                  </select></TD>
							</TR>
							<%-- 
							<TR>
								<TD  width="15%" class="plainlabel"><%=Utility.GLanguage("Trạng thái",session,jedis) %></TD>
								<TD class="plainlabel">
								  <input name="trangthai" type="checkbox"  value="1" <%if( bgblcBean.getTrangthai().equals("1")) {%> checked="checked" <%} %> style="width:300px">
							  	</TD>
							</TR>
							 --%>
							
						</TABLE>
					</TD></TR>
				</TABLE>
				<TABLE class="tabledetail" cellpadding="0" cellspacing="0" width="100%">
						<TR id="spdvkd" bgcolor="#FFFFFF">
							<TD width="100%">
							<TABLE width="100%" border="0" cellspacing="1" cellpadding="0">
								<TR class="tbheader">
									<TH width="10%" style="display: none;"> </TH>
									<TH width="80%">Tên NHÂN VIÊN BÁN HÀNG </TH>
									<TH width="20%">Số Lượng</TH>
								</TR>
								
								<%
								int j = 0;
								String lightrow = "tblightrow";
								String darkrow = "tbdarkrow";

								while(rsddkd.next())
								{ 
									if (j % 2 != 0) 
									{%>						
										<TR class= <%=lightrow%> >
								    <%} else 
								    {%>
										<TR class= <%= darkrow%> >
									<%}%>
									
											<TD align="center" style=" display: none;">
												<input type='text' name='ddkdIdArr'  value="<%= rsddkd.getString("pk_seq")%>" style="text-align: left"/>
											</TD>
											<TD align="center">
												<input type='text' name=''  value="<%= rsddkd.getString("ten")%>" style="text-align: left"/>
											</TD>
											<TD align="center">
												<input type='text' name='spsoluongArr'  value="<%= rsddkd.getString("soluong")%>" style="text-align: left"/>
											</TD>
						     		<% j++;
								}
								%>
							</TABLE>

							</TD>
						</TR>
					</TABLE>
					</FIELDSET>
				</td>
			</TR>
		</TABLE>
	
	</TD>
	</TR>
</Table>
</form>
</BODY>
</HTML>

<% if(rsddkd != null) rsddkd.close(); %>
<% if (sanpham != null) sanpham.close(); 
	
%>
<%}%>