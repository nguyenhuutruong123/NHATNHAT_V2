<%@page import="java.text.DecimalFormat"%>
<%@page import="java.text.NumberFormat"%>
<%@page import="geso.dms.center.util.Utility"%>
<%@page import="java.sql.ResultSet"%>
<%@page import="geso.dms.distributor.beans.donhangctv.IBCXNT_CTV"%>
<%@page import="java.util.Calendar"%>
<%@page import="java.sql.Date"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
	
	<%	String userId = (String) session.getAttribute("userId");  
	String userTen = (String) session.getAttribute("userTen");  	
	String sum = (String) session.getAttribute("sum");
	Utility util = (Utility) session.getAttribute("util");
	if(!util.check(userId, userTen, sum)){
		response.sendRedirect(request.getContextPath() + "/redirect.jsp");
	}else{ %>
<%
	
	IBCXNT_CTV obj = (IBCXNT_CTV) session.getAttribute("obj");
	ResultSet rsKhuvuc = obj.getRsKhuvuc();
	ResultSet rsTinh = obj.getRsTinhthanh();
	ResultSet rsKhachhang = obj.getRsKhachhang();
	ResultSet rs = obj.getDataRs();
	NumberFormat formater = new DecimalFormat("#,###,###");
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd"> <% Utility Utilback = new Utility(); %>
<html>
<head>
<TITLE>Phanam - Project</TITLE>  
<META http-equiv="Content-Type" content="text/html; charset=utf-8">
<META http-equiv="Content-Style-Type" content="text/css">
<LINK rel="stylesheet" type="text/css" media="print" href="../css/impression.css">
<LINK rel="stylesheet" href="../css/main.css" type="text/css">

<script type="text/javascript" src="../scripts/jquery-1.js"></script>
<link type="text/css" rel="stylesheet" href="../css/mybutton.css">
<LINK rel="stylesheet" type="text/css" href="../css/style.css" />

<script type="text/javascript" src="../scripts/jquery.min.1.7.js"></script>
<link href="../css/jquery-ui.css" rel="stylesheet" type="text/css" />
<link href="../css/select2.css" rel="stylesheet" />
<script src="../scripts/select2.js"></script>
<script>
$(document).ready(function()
{
	$(".select2").select2();
});
</script>
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
            $(".button").hover(function(){
                $(".button img")
                .animate({top:"-10px"}, 200).animate({top:"-4px"}, 200) // first jump
                .animate({top:"-7px"}, 100).animate({top:"-4px"}, 100) // second jump
                .animate({top:"-6px"}, 100).animate({top:"-4px"}, 100); // the last jump
            });
        }); 
		$(document).ready(function(){
            $(".button1").hover(function(){
                $(".button1 img")
                .animate({top:"-10px"}, 200).animate({top:"-4px"}, 200) // first jump
                .animate({top:"-7px"}, 100).animate({top:"-4px"}, 100) // second jump
                .animate({top:"-6px"}, 100).animate({top:"-4px"}, 100); // the last jump
            });
        });
		
    </script>

<script type="text/javascript"
	src="../scripts/report-js/action-report.js"></script>
<script language="javascript" type="text/javascript">	
	function search()
	{
		document.forms['frm'].action.value= 'search';
		document.forms["frm"].submit();
	}
	function submitform() {
		
		var divTK = document.getElementById("divTIMKIEM"); 
		divTK.style.display = 'none';
			
		if (document.forms["frm"]["Sdays"].value.length == 0) {
			setErrors("Vui lòng kiểm tra lại thời gian lấy báo cáo");
			return ;
		}
		if (document.forms["frm"]["Edays"].value.length == 0) {
			setErrors("Vui lòng kiểm tra lại thời gian lấy báo cáo");
			return ;
		}
		var fieldShow = document.getElementsByName("fieldsHien");
		for ( var i = 0; i < fieldShow.length; ++i) {
			fieldShow.item(i).checked = true;
		}
		//document.getElementById("btnTaoBC").innerHTML = "<a href='#'><img src='../images/waiting.gif'  title='cho thuc hien..' border='1' longdesc='cho thuc hien..' style='border-style:outset'> Processing...</a>";

		document.forms['frm'].action.value= 'tao';
		document.forms["frm"].submit();
		reset();
	}
	
	function setErrors(errorMsg) {
		var errors = document.getElementById("errors");
		errors.value = errorMsg;
	}
	function reset() {
		var fieldShow = document.getElementsByName("fieldsHien");
		var fieldHidden = document.getElementsByName("fieldsAn");
		for ( var i = 0; i < fieldShow.length; ++i) {
			fieldShow.item(i).checked = false;
		}
		for ( var i = 0; i < fieldHidden.length; ++i) {
			fieldHidden.item(i).checked = false;
		}
	};
	
 
	function capnhat_check(){
		
		
		var layctnxsolo = document.getElementById("layctnxsolo");
		var layctnxsolo_tong = document.getElementById("layctnxsolo_tong");
		 
		if(layctnxsolo.checked  && layctnxsolo_tong.checked ){
			layctnxsolo.checked =!layctnxsolo_tong.checked;
		}
		
	}
	
	function capnhat_check1(){
		
		
		var layctnxsolo = document.getElementById("layctnxsolo");
		var layctnxsolo_tong = document.getElementById("layctnxsolo_tong");
		 
		if(layctnxsolo.checked  && layctnxsolo_tong.checked ){
			 layctnxsolo_tong.checked=false ;
		}
		
	}
	
	
	function checkedAll(){
		var chkAll = document.getElementById("chkAll");
	 	var nppId = document.getElementsByName("nppId");
		 console.log("vao day");
		 if(chkAll.checked)
		 {
			 for(i = 0; i < nppId.length; i++)
			 {
				 nppId.item(i).checked = true;
			 }
		 }
		 else
		 {
			 for(i = 0; i < nppId.length; i++)
			 {
				 nppId.item(i).checked = false;
			 }
		 }
	 }
</script>
<link media="screen" rel="stylesheet" href="../css/colorbox.css">
<script src="../scripts/colorBox/jquery.colorbox.js"></script>
<script>
    $(document).ready(function()
    {
    	$(".kholist").colorbox({width:"46%", inline:true, href:"#kholist"});
        //Example of preserving a JavaScript event for inline calls.
        $("#click").click(function(){ 
            $('#click').css({"background-color":"#f00", "color":"#fff", "cursor":"inherit"}).text("Viethung - Project.");
            return false;
        });
    });
</script>
<style type="text/css">

	.loader222 {
		position: fixed;
		left: 0px;
		top: 0px;
		width: 100%;
		height: 500%;
		z-index: 999999999;
		
		background: url('../images/dangxuly.gif') 50% 50% no-repeat rgb(249,249,249);
		
		z-index:100000001;
		-moz-opacity: 0.9;
		opacity:0.9;
		filter: alpha(opacity=90);
	}
		
</style>
</head>
<BODY leftmargin="0" bottommargin="0" topmargin="0" rightmargin="0">
	<form name="frm" method="post" action="../../BCXNT_CTVSvl">
		<input type="hidden" name="action" value='1'> 
		<input type="hidden" id="userId" name="userId" value='<%=userId%>'>
		<input type="hidden" name="isdlpp" value="<%= obj.getIsDlpp() %>" >
		<input type="hidden" name="nppId" value="<%= obj.getnppId() %>" >
		<div id="main" style="width: 99%; padding-left: 2px">
		<div class="loader222"  id="divTIMKIEM" style="display: none">
			<img src="../images/PleaseWait.gif" style="margin-top:150px;" />
		</div>
			<div align="left" id="header" style="width: 100%; float: none">
				<div style="width: 70%; padding: 5px; float: left" class="tbnavigation">
				<% if( obj.getIsDlpp().equals("1") ) { %>
					Quản lý bán hàng &#62; Báo cáo đại lý &#62; BC Xuất nhập tồn đại lý
				<% } else if( obj.getIsDlpp().equals("0") ) { %>
					Quản lý bán hàng &#62; Báo cáo CTV &#62; BC tồn kho Bệnh Viện
				<% } else { %>
					Quán lý bán hàng &#62; Báo cáo CTV &#62; BC cộng tác viên (KSNB)
				<% } %>
				</div>
				<div align="right" style="padding: 5px" class="tbnavigation">
					<%=Utility.GLanguage("Chào mừng bạn",session,jedis) %> 
					<%=userTen%></div>
			</div>
			<div align="left" style="width: 100%; float: none; clear: left">
				<fieldset>
					<legend class="legendtitle"> <%=Utility.GLanguage("Báo lỗi nhập liệu",session,jedis) %></legend>
					<textarea id="errors" name="errors" rows="1" style="width: 100% ; color:#F00 ; font-weight:bold"><%=obj.getMsg()%></textarea>
				</fieldset>
			</div>
			<div align="left"
				style="width: 100%; float: none; clear: left; font-size: 0.7em">
				<fieldset>
					<legend class="legendtitle">Xuất nhập tồn</legend>
					<div style="width: 100%; float: none" align="left">
						<div style="width: 100%; float: none" align="left"
							class="plainlabel">

							<TABLE width="70%" cellpadding="6" cellspacing="0">
							
								<% if( obj.getIsDlpp().equals("0") || obj.getIsDlpp().equals("2") ) { %>
								
								<TR>
									<TD class="plainlabel" style="width: 100px" >Tháng</TD>
									<TD class="plainlabel" style="width: 250px" >
										<select name="Sdays"  class="select2" style="width: 200px" >
											<option value="" ></option>
											<% for( int i = 1; i <= 12; i++ ) { %>
												<% if( obj.getMonth().equals( Integer.toString(i) ) ) { %>
													<option value="<%= i %>" selected="selected" ><%= i %></option>
												<% } else { %>
													<option value="<%= i %>" ><%= i %></option>
												<% } %>
											<% } %>
										</select>
									</TD>
										
									<TD class="plainlabel" style="width: 100px" >Năm</TD>
									<td>
										<select name="Edays"  class="select2" style="width: 200px" >
											<option value="" ></option>
											<% for( int i = 2015; i <= 2030; i++ ) { %>
												<% if( obj.getYear().equals( Integer.toString(i) ) ) { %>
													<option value="<%= i %>" selected="selected" ><%= i %></option>
												<% } else { %>
													<option value="<%= i %>" ><%= i %></option>
												<% } %>
											<% } %>
										</select>
									</td>
								</TR>
								
								<TR>
									<TD class="plainlabel"><%=Utility.GLanguage("Khu vực",session,jedis) %></TD>
									<TD class="plainlabel">
										<select name="khuvucId" class="select2" style="width: 200px" >
											<option value="">All</option>
											<%if (rsKhuvuc != null)
													while (rsKhuvuc.next()) 
													{
														if (rsKhuvuc.getString("pk_seq").equals(obj.getkhuvucId())) {%>
														<option value="<%=rsKhuvuc.getString("pk_seq")%>" selected><%=rsKhuvuc.getString("ten")%></option>
													<%} else {%>
														<option value="<%=rsKhuvuc.getString("pk_seq")%>"><%=rsKhuvuc.getString("ten")%></option>
											<%}}%>
										</select>
									</TD>
									<TD class="plainlabel">Tỉnh</TD>
									<TD class="plainlabel" >
										<select name="tinhthanhId" class="select2" style="width: 200px" >
											<option value="" selected>All</option>
											<% if(rsTinh != null)
											   while(rsTinh.next()){
											   if(rsTinh.getString("pk_seq").equals(obj.getTinhthanhid()))
											   { %>
											<option value="<%= rsTinh.getString("pk_seq") %>" selected><%= rsTinh.getString("ten") %></option>
											<%}else { %>
											<option value="<%= rsTinh.getString("pk_seq") %>"><%= rsTinh.getString("ten") %></option>
											<%} }%>
										</select>
									</TD>
								</TR>
								<TR>
			                     	<TD class="plainlabel" >Khách hàng</TD>
			                        <TD class="plainlabel" >
			                            <%-- <input type="text" name="khachang" value="<%= obj.getkhTen() %>"  /> --%>
			                            <select name="khachang" class="select2" style="width: 200px" >
											<option value="" selected>All</option>
											<% if(rsKhachhang != null)
											   while(rsKhachhang.next()){
											   if(rsKhachhang.getString("pk_seq").equals(obj.getkhTen()))
											   { %>
											<option value="<%= rsKhachhang.getString("pk_seq") %>" selected><%= rsKhachhang.getString("ten") %></option>
											<%}else { %>
											<option value="<%= rsKhachhang.getString("pk_seq") %>"><%= rsKhachhang.getString("ten") %></option>
											<%} } rsKhachhang.close(); %>
										</select>
			                        </TD>
			                        <TD class="plainlabel" ><%=Utility.GLanguage("Sản phẩm",session,jedis) %></TD>
			                        <TD class="plainlabel" >
			                            <input type="text" name="tenSP" value="<%= obj.getSpId() %>"   />
			                        </TD>
			                    </TR>
			                    <% } %>
                           		
								<TR>
									<td colspan="4">
										<a class="button" href="javascript:submitform();"> <img style="top: -4px;" src="../images/button.png" alt=""> Tạo báo cáo </a> &nbsp;&nbsp;&nbsp;&nbsp;
										<a class="button" href="javascript:search();"> <img style="top: -4px;" src="../images/button.png" alt=""><%=Utility.GLanguage("Tìm kiếm",session,jedis) %> </a>
									</td>
								</TR>
								
							</TABLE>
						</div>
						<hr>
						
						<% if( obj.getIsDlpp().equals("0") ) { %>
						<div style="width: 1700px; float: none;">
							<table>
								<tr class="tbheader" >
									<th style="width: 100px;" >Mã KH</th>
									<th style="width: 200px;" >Tên KH</th>
									<th style="width: 100px;" >Mã Sản Phẩm</th>
									<th style="width: 200px;" ><%=Utility.GLanguage("Sản phẩm",session,jedis) %></th>
									<th style="width: 100px;" >Đơn Vị</th>
									<th style="width: 100px;" >Đơn Giá</th>
									
									<th style="width: 100px;" >Đầu kỳ</th>
									<th style="width: 200px;" colspan="2" >Số Lượng Nhập Trong Kỳ</th>
									<th style="width: 100px;" >Xuất Trong Kỳ</th>
									<th style="width: 200px;" colspan="2" >Tồn Cuối</th>
								</tr>
								
								<tr class="tbheader" >
									<th></th>
									<th ></th>
									<th ></th>
									<th ></th>
									<th ></th>
									<th ></th>
									<th ></th>
									<th ></th>
									
									<th style="width: 100px;" ></th>
									<th style="width: 100px;" ></th>
									<th style="width: 100px;" ></th>
									<th style="width: 100px;" ></th>
									<th style="width: 100px;" >Số Lượng</th>
									<th style="width: 100px;" >Thành Tiền</th>
								</tr>
								
								<% if( rs != null ) 
								{ 
									int m = 0;
									while( rs.next() ) {
										if((m % 2 ) == 0) {%>
			                         	<TR class='tbdarkrow'>
			                        <%}else{ %>
			                          	<TR class='tblightrow'>
			                        <%} 
										double dongia = rs.getDouble("DONGIA");
										
										double tondau = rs.getDouble("tondau");
										double nhaptrongky = rs.getDouble("nhaptrongky");
										double nhaptrahang = rs.getDouble("nhaptrahang");
										double xuattrongky = rs.getDouble("xuattrongky");
										
										//NHAP TRA HANG KHONG DUOC AM KHO KHI THE HIEN TREN BC NAY
										//if( tondau + nhaptrongky - nhaptrahang - xuattrongky < 0 )
											//nhaptrahang = tondau + nhaptrongky - xuattrongky;
										
										double toncuoi = tondau + nhaptrongky - xuattrongky;
										double thanhtienTC = toncuoi * dongia;
										
										%>

									<td><%= rs.getString("makhCAP1") == null ? "" : rs.getString("makhCAP1") %></td>
									<td><%= rs.getString("tenkhCAP1") == null ? "" : rs.getString("tenkhCAP1") %></td>
									<td><%= rs.getString("maSP") %></td>
									<td><%= rs.getString("tenSP") %></td>
									<td><%= rs.getString("DONVI") %></td>
									<td><%= rs.getString("DONGIA") %></td>
									
									<td style="text-align: right;" ><%= formater.format(tondau) %></td>
									<td style="text-align: right;" ><%= formater.format(nhaptrongky) %></td>
									<td style="text-align: right;" ><%= formater.format(toncuoi) %></td>
									<td style="text-align: right;" ><%= formater.format(thanhtienTC) %></td>
									
								</tr>
								
								<% m++; } rs.close();  } %>
								<tr>
									
								</tr>
							</table>
						
						</div>
						<% } %>
												
					</div>
				</fieldset>
			</div>
		</div>
		<br /> <br /> <br /> <br />
		<script type="text/javascript">
			//replaces();
			function endload()
			{
				var divTK = document.getElementById("divTIMKIEM"); 
				divTK.style.display = 'none';
			}
			endload();
		</script>
	</form>
</body>
</HTML>

<% 
	session.setAttribute("obj",null);
	try
	{
		if(rsKhuvuc!=null)rsKhuvuc.close();
		if(rsTinh!=null)rsTinh.close();
		if(rsKhachhang!=null)rsKhachhang.close();
		if(rs!=null)rs.close();
	}
	catch(Exception ex)
	{
		ex.printStackTrace();
	}
	
}%>