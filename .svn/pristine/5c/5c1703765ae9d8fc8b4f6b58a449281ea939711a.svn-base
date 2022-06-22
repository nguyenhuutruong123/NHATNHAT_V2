<%@ page  import = "geso.dms.center.util.*" %>
<%@page import="java.sql.SQLException"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page  import = "java.util.Iterator" %>
<%@ page  import = "java.util.List" %>
<%@ page  import = "java.util.ArrayList" %>
<%@ page  import = "geso.dms.distributor.beans.tamung.*" %>
<%@ page  import = "java.sql.ResultSet" %>
<%@ page  import = "java.text.DecimalFormat" %>
<%@ page  import = "java.text.NumberFormat" %>
<% IErpTamUngList tuList = (IErpTamUngList)session.getAttribute("tuList"); %>
<% ResultSet rsTienTe =tuList.getRsTienTe(); %>
<% ResultSet rsTamUng =tuList.getRsTamUng(); %>
<% String userId = (String) session.getAttribute("userId");  %>
<% String userTen = (String) session.getAttribute("userTen");  %>
<%  
	String sum = (String) session.getAttribute("sum");
	Utility util = (Utility) session.getAttribute("util");
	if(!util.check(userId, userTen, sum)){
		response.sendRedirect("/Phanam/index.jsp");
	}else{	
		 int[] quyen = new  int[5];
		 quyen = util.Getquyen("ErpTamUngSvl","",userId);
 %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd"> <% Utility Utilback = new Utility(); %>
<html>
<head>
<TITLE>Phanam - Project</TITLE>
<META http-equiv="Content-Type" content="text/html; charset=utf-8">
<META http-equiv="Content-Style-Type" content="text/css">
<LINK rel="stylesheet" type="text/css" media="print" href="../css/impression.css">
<LINK rel="stylesheet" href="../css/main.css" type="text/css">
<link type="text/css" rel="stylesheet" href="../css/mybutton.css">
<LINK rel="stylesheet" type="text/css" href="../css/style.css" />
<style type="text/css">
#mainContainer {
	width: 660px;
	margin: 0 auto;
	text-align: left;
	height: 100%;
	border-left: 3px double #000;
	border-right: 3px double #000;
}

#formContent {
	padding: 5px;
}
/* END CSS ONLY NEEDED IN DEMO */

/* Big box with list of options */
#ajax_listOfOptions {
	position: absolute; /* Never change this one */
	width: auto; /* Width of box */
	height: 200px; /* Height of box */
	overflow: auto; /* Scrolling features */
	border: 1px solid #317082; /* Dark green border */
	background-color: #C5E8CD; /* White background color */
	color: black;
	text-align: left;
	font-size: 1.0em;
	z-index: 100;
}

#ajax_listOfOptions div {
	/* General rule for both .optionDiv and .optionDivSelected */
	margin: 1px;
	padding: 1px;
	cursor: pointer;
	font-size: 1.0em;
}

#ajax_listOfOptions .optionDiv { /* Div for each item in list */
	
}

#ajax_listOfOptions .optionDivSelected { /* Selected item in the list */
	background-color: #317082; /*mau khi di chuyen */
	color: #FFF;
}

#ajax_listOfOptions_iframe {
	background-color: #F00;
	position: absolute;
	z-index: 5;
}

form {
	display: inline;
}

#dhtmltooltip {
	position: absolute;
	left: -300px;
	width: 150px;
	border: 1px solid black;
	padding: 2px;
	background-color: lightyellow;
	visibility: hidden;
	z-index: 100;
	/*Remove below line to remove shadow. Below line should always appear last within this CSS*/
	filter: progid:DXImageTransform.Microsoft.Shadow(color=gray, direction=135
		);
}

#dhtmlpointer {
	position: absolute;
	left: -300px;
	z-index: 101;
	visibility: hidden;
}
}
</style>

<link rel="stylesheet" type="text/css" href="../css/speechbubbles.css" />
<script type="text/javascript" src="../scripts/jquery.min.js"></script>
<script type="text/javascript" src="../scripts/speechbubbles.js"></script>
<script type="text/javascript">
	jQuery(function($){ 
		 $('.addspeech').speechbubble();})
</script>

<link href="../css/jquery-ui.css" rel="stylesheet" type="text/css" />
<script src="../scripts/ui/jquery.ui.core.js" type="text/javascript"></script>
<script src="../scripts/ui/jquery.ui.widget.js" type="text/javascript"></script>
<script src="../scripts/ui/jquery.ui.datepicker.js" type="text/javascript"></script>
<script type="text/javascript" src="../scripts/phanTrang.js"></script>
<script type="text/javascript">
	$(document).ready(function() {		
		$( ".days" ).datepicker({			    
				changeMonth: true,
				changeYear: true				
		});            
	});	
</script>

<script type="text/javascript" src="../scripts/ajax.js"></script>
<script type="text/javascript" src="../scripts/erp-spList.js"></script>

<script type="text/javascript" src="../scripts/dropdowncontent.js"></script>
<script type="text/javascript" src="../scripts/cool_DHTML_tootip.js"></script>
<script type="text/javascript" src="../scripts/jquery.autocomplete.js"></script>
    <SCRIPT language="javascript" type="text/javascript">
	 function confirmLogout()
	 {
	    if(confirm("Bạn có muốn đăng xuất?"))
	    {
			top.location.href = "home.jsp";
	    }
	    return
	 }
	 function submitform()
	 {   
	    document.forms["erpDmhForm"].submit();
	 }
	 function newform()
	 {   
		document.forms["erpDmhForm"].action.value = "new";
	    document.forms["erpDmhForm"].submit();
	 }
	 function clearform()
	 {   
	    
	    document.forms["erpDmhForm"].TuNgay.value = "";
	    document.forms["erpDmhForm"].DenNgay.value = "";
	    document.forms["erpDmhForm"].DoiTuongTamUng.value = "";
	    document.forms["erpDmhForm"].SoTienTamUng.value="";
	    document.forms["erpDmhForm"].TienTeId.value="";
	    document.forms["erpDmhForm"].trangthai.value="";
	    document.forms["erpDmhForm"].sochungtu.value="";
	    document.forms["erpDmhForm"].submit();
	 }
	 function thongbao()
	 {
		 var tt = document.forms["erpDmhForm"].msg.value;
	 	 if(tt.length>0)
	     	alert(document.forms["erpDmhForm"].msg.value);
	 }
	 

	 function processing(id,chuoi)
	 {
 	    document.getElementById(id).innerHTML = "<a href='#'><img src='../images/waiting.gif' width = '20' height = '20' title='cho thuc hien..' border='0' longdesc='cho thuc hien..' style='border-style:outset'> Proc...</a>"; 		  
 	 	document.getElementById(id).href=chuoi;
 	 }
	</SCRIPT>
</head>
<body>
<form name="erpDmhForm" method="post" action="../../ErpTamUngSvl">
<input type="hidden" name="userId" value="<%= userId %>" >
<input type="hidden" name="action" value="1" >

<input type="hidden" name="msg" value='<%= tuList.getMsg() %>'>
<script language="javascript" type="text/javascript">
    thongbao();
</script> 

<div id="main" style="width:100%; padding-left:2px">
	<div align="left" id="header" style="width:100%; float:none">
    	<div style="width:70%; padding:5px; float:left" class="tbnavigation">
        	Quản lý công nợ > Công nợ phải trả > Đề nghị tạm ứng
        </div>
        <div align="right" style="padding:5px" class="tbnavigation">
        	<%=Utility.GLanguage("Chào mừng bạn",session,jedis) %>  <%= userTen %> &nbsp;
        </div>
    </div>
  	<div id="cotent" style="width:100%; float:none">
    	<div align="left" style="width:100%; float:none; clear:left; font-size:0.7em">
        <fieldset style="margin-top:5px" >
            <legend class="legendtitle"> <%=Utility.GLanguage("Tiêu chí tìm kiếm",session,jedis) %></legend>
                <TABLE width="100%" cellpadding="6px" cellspacing="0px" style="margin-top: 5px " >								                          
                    <TR>
                        <TD class="plainlabel" width="15%"><%=Utility.GLanguage("Từ ngày",session,jedis) %> </TD>
                        <TD class="plainlabel" width="25%">
                            <input type="text" class="days" 
                                   id="TuNgay" name="TuNgay" value="<%= tuList.getTuNgay() %>" maxlength="10" onchange="submitform()" />
                        </TD>
                  
                        <TD class="plainlabel" width="15%"><%=Utility.GLanguage("Đến ngày",session,jedis) %> </TD>
                        <TD class="plainlabel">
                            <input type="text" class="days"
                                   id="DenNgay" name="DenNgay" value="<%= tuList.getDenNgay() %>" maxlength="10" onchange="submitform()" />
                        </TD>
                    </TR>
                    <TR>
                          		<TD class="plainlabel" valign="middle" >Đối tượng </TD>
                          		<TD class="plainlabel" valign="middle"  >
									<select name="DoiTuongTamUng" id="DoiTuongTamUng"  onchange="submitform()">
										<option value=""></option>
										<%if(tuList.getDoiTuongTamUng().equals("1")){ %>
										<option value="1" selected="selected"> Nhân viên</option>
										<option value="2">Nhà cung cấp</option>
										<%} else if(tuList.getDoiTuongTamUng().equals("2")){%>
										<option value="2" selected="selected"> Nhà cung cấp</option>
										<option value="1">Nhân viên</option>
										<%} else {%>
										<option value="1">Nhân viên</option>
										<option value="2" > Nhà cung cấp</option>
										<%} %>
										</select>
								</TD>   
								<%if(tuList.getDoiTuongTamUng().equals("1")) {%>
                       	 
		                        	<TD class="plainlabel" valign="middle" >Nhân viên /NCC </TD>   
		                        	<TD class="plainlabel" valign="middle"  >
		                        	<input type="text" name="NhanVienId" id="NhanVienId" value="<%= tuList.getTenHienThi() %>"  style="width:400px" >
		                        	</TD>   
		                        
		                       	<%}else if(tuList.getDoiTuongTamUng().equals("2")){ %>
		                       	 
		                        	<TD class="plainlabel" valign="middle"  >Nhân viên /NCC </TD>   
		                        	<TD class="plainlabel" valign="middle"  >
		                       		<input type="text" name="NccId" id="NccId" value="<%= tuList.getTenHienThi() %>"  style="width:400px" >
		                       		
		                       <%} else {%>
		                       <TD class="plainlabel" valign="middle"  ></TD>   
		                       <TD class="plainlabel" valign="middle"  ></TD>   
		                       
		                       <% }%>        
		              </TR> 
		                   	
		                  
		                 <%--  <TR>
                          		<TD class="plainlabel" valign="middle" width="120px" >Nhân viên /NCC </TD>   
		                        <TD class="plainlabel" valign="middle"  >		                   
		                        	<input type="text" name="NhanVienId" id="NhanVienId" value=""  style="width:200px" >
		                        <%} %> 
		                        </TD>          
		                  </TR>  --%>
                    
                    <TR>
                        <TD class="plainlabel" >Số tiền tạm ứng </TD>
                        <TD class="plainlabel">
                            <input type="text"  
                                   id="SoTienTamUng" name="SoTienTamUng" value="<%= tuList.getSoTienTamUng() %>"  onchange="submitform()" />
                        </TD>
                    
                        <TD class="plainlabel" >Tiền tệ </TD>
                        <TD class="plainlabel">
                            <input type="text"  
                                   id="TienTeId" name="TienTeId" value="<%= tuList.getTienTeId()%>"  onchange="submitform()" />
                        </TD>
                    </TR>
                    
                    <TR>
                      	<TD class="plainlabel" valign="middle" ><%=Utility.GLanguage("Trạng thái",session,jedis) %> </TD>
                      	<TD class="plainlabel" valign="middle" >
							<select name="trangthai" id="trangthai"  onchange="submitform()">
								<option value=""></option>
								<option value="0" <%= tuList.getTrangThai().equals("0") ? "selected=\"selected\"" : "" %>>Chưa chốt</option>
								<option value="1" <%= tuList.getTrangThai().equals("1") ? "selected=\"selected\"" : "" %>>Đã chốt</option>
								<option value="2" <%= tuList.getTrangThai().equals("2") ? "selected=\"selected\"" : "" %>>Đã xóa</option>
								<option value="3" <%= tuList.getTrangThai().equals("3") ? "selected=\"selected\"" : "" %>>Đã thanh toán</option>
							</select>
						</TD>   
						
						<TD class="plainlabel" >Số chứng từ </TD>
                        <TD class="plainlabel">
                            <input type="text" id="sochungtu" name="sochungtu" value="<%= tuList.getSochungtu()%>"  onchange="submitform()" />
                        </TD>
                               
                    </TR> 
                    
                    <tr>
                        <td colspan="4" class="plainlabel">
                            <a class="button" href="javascript:submitform()">
                                <img style="top: -4px;" src="../images/Search30.png" alt=""><%=Utility.GLanguage("Tìm kiếm",session,jedis) %> </a>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                            <a class="button2" href="javascript:clearform()">
                                <img style="top: -4px;" src="../images/button.png" alt=""><%=Utility.GLanguage("Nhập lại",session,jedis) %></a>&nbsp;&nbsp;&nbsp;&nbsp;
                        </td>
                    </tr>        					
                </TABLE>                      
        </fieldset>                      
    	</div>
        <div align="left" style="width:100%; float:none; clear:left; font-size:0.7em">
        	<fieldset>
            	<legend><span class="legendtitle">Đề nghị tạm ứng </span>&nbsp;&nbsp;
            	
					<%if(quyen[0]!=0){ %>
                	<a class="button3" href="javascript:newform()">
                           <img style="top: -4px;" src="../images/New.png" alt=""><%=Utility.GLanguage("Tạo mới",session,jedis) %> </a>
                           <%} %>
                </legend>
            	<TABLE width="100%" border="0" cellspacing="1" cellpadding="4">
					<TR class="tbheader">
	                    <TH align="center" width = "7%">Số chứng từ</TH>
	                    <TH align="center" width = "7%">Ngày tạm ứng</TH>
	                    <TH align="center" width = "15%">Nhân viên/NCC</TH>
	                    <TH align="center" width = "7%">Số tiền</TH>
	                   <!--  <TH align="center">Thời gian hoàn ứng</TH> -->
	                     <TH align="center" width = "10%">Lý do tạm ứng</TH> 
	                    <TH align="center" width = "7%"><%=Utility.GLanguage("Ngày tạo",session,jedis) %></TH>
	                    <TH align="center" width = "7%"><%=Utility.GLanguage("Người tạo",session,jedis) %></TH>
	                    <!-- <TH align="center"><%=Utility.GLanguage("Ngày sửa",session,jedis) %></TH> -->
	                    <TH align="center" width = "7%"><%=Utility.GLanguage("Người sửa",session,jedis) %></TH>
	                    <TH align="center" width = "7%"><%=Utility.GLanguage("Trạng thái",session,jedis) %></TH>
	                    <TH align="center" width = "7%"><%=Utility.GLanguage("Tác vụ",session,jedis) %></TH>
	                </TR>
					<%
					  NumberFormat formatter = new DecimalFormat("#,###,###");
						if(rsTamUng != null)
						{
							try
							{
								int m = 0;
								while(rsTamUng.next())
								{
									double sotienTU = 0;
									
									String isthanhtoan = "";
									isthanhtoan = rsTamUng.getString("isThanhToan");
									
									String ISQLTT = rsTamUng.getString("ISQLTT");
		                    		String ISKENH = rsTamUng.getString("ISKENH");
		                    		String captrenduyet = rsTamUng.getString("CAPTREN");
		                    		
									
									if(rsTamUng.getString("SoTienTamUng") != null)
										sotienTU = rsTamUng.getDouble("SoTienTamUng");
									
									if((m % 2 ) == 0 & rsTamUng.getString("trangthai").equals("2")) {%>
		                         		<TR class="tbdarkrow" style="color: red;">
		                         	<%}else if((m % 2 ) != 0 & rsTamUng.getString("trangthai").equals("2")) { %>
		                         		<TR class="tblightrow" style="color: red;">
		                         	<%}else if((m % 2 ) == 0) { %>
		                         		<TR class="tbdarkrow" >
			                     	<%}else{ %>
			                          	<TR class="tblightrow">
			                        <%} %>
									<TD align="center" <%if(rsTamUng.getString("trangthai").equals("2")) {%> style="color: red" <%} %> ><%= rsTamUng.getString("PK_SEQ") %></TD>
									<TD align="center" <%if(rsTamUng.getString("trangthai").equals("2")) {%> style="color: red" <%} %> ><%= rsTamUng.getString("NgayTamUng") %></TD>
									<TD align="left" <%if(rsTamUng.getString("trangthai").equals("2")) {%> style="color: red" <%} %>><%= rsTamUng.getString("NhanVien") %></TD>
									<TD align="left" <%if(rsTamUng.getString("trangthai").equals("2")) {%> style="color: red" <%} %>><%= formatter.format(sotienTU) %></TD>
									<%-- <TD align="left"><%= rsTamUng.getString("ThoiGianHoanUng") %></TD> --%>
									<TD align="left" <%if(rsTamUng.getString("trangthai").equals("2")) {%> style="color: red" <%} %> ><%= rsTamUng.getString("LYDOTAMUNG") %></TD>
									<%-- <TD align="left"><%= rsTamUng.getString("TienTe") %></TD> --%>
									<TD align="left" <%if(rsTamUng.getString("trangthai").equals("2")) {%> style="color: red" <%} %>><%= rsTamUng.getString("NgayTao") %></TD>
									<TD align="left" <%if(rsTamUng.getString("trangthai").equals("2")) {%> style="color: red" <%} %>><%= rsTamUng.getString("NguoiTao") %></TD>
									<%-- <TD align="left"><%= rsTamUng.getString("NgaySua") %></TD> --%>
									<TD align="left" <%if(rsTamUng.getString("trangthai").equals("2")) {%> style="color: red" <%} %>><%= rsTamUng.getString("NguoiSua") %></TD>
									<TD align="left">
										<%
											String trangthai = "";
											String tt = rsTamUng.getString("trangthai");
											String dachot = rsTamUng.getString("DACHOT");
											if(tt.equals("0"))
											{
												trangthai = "Chưa duyệt";
												
												if(dachot.equals("1") && captrenduyet.equals("0"))
												{
													trangthai = "Chưa duyệt";
												}
													
												if(ISQLTT.equals("1"))
												{
													trangthai = "Đã duyệt (Quản lý trực tiếp)";
												}
												
												if(ISKENH.equals("1"))
												{
													trangthai = "Đã duyệt (Quản lý kênh)";
												}
												
											}
											else
											{
												if(tt.equals("1"))
												{
													if(ISQLTT.equals("1"))
													{
														trangthai = "Đã duyệt (Quản lý trực tiếp)";
													}
													
													if(ISKENH.equals("1"))
													{
														trangthai = "Đã duyệt (Quản lý kênh)";
													}
													
													if(isthanhtoan.equals("1"))
														trangthai = "Đã thanh toán";
												}
												else
												{
													if(tt.equals("2"))
														trangthai = "Đã xóa";
													else 
														trangthai = "Đã hủy";
												}
											}
										%>
										<%= trangthai %>
									</TD>
								
									<TD align="center"> 
				                    <% if(tt.equals("0")&& rsTamUng.getString("CAPTREN").equals("0")){ %>
				                  
				                    <%if(quyen[2]!=0){ %>
		                                <A href = "../../ErpTamUngUpdateSvl?userId=<%=userId%>&update=<%= rsTamUng.getString("PK_SEQ") %>"><IMG src="../images/Edit20.png" alt="Cập nhật" title="Cập nhật" border=0></A>&nbsp;
		                               <%} %>
		                               
		                                 <% if(dachot.equals("0")) {%>
		                               <%if(quyen[4]!=0){ %>
		                                 <A id='chotphieu<%=rsTamUng.getString("PK_SEQ")%>'
							       			href=""><img
							       			src="../images/Chot.png" alt="Chốt tạm ứng"
							       			width="20" height="20" title="Chốt tạm ứng"
							      			border="0" onclick="if(!confirm('Bạn có chắc chốt tạm ứng này?')) {return false ;}else{ processing('<%="chotphieu"+rsTamUng.getString("PK_SEQ")%>' , '../../ErpTamUngSvl?userId=<%=userId%>&chot=<%= rsTamUng.getString("PK_SEQ") %>');}"  >
										 </A>
										 <%} %>
										 <%} %>
		                                <%if(quyen[1]!=0){ %>
		                                <A href = "../../ErpTamUngSvl?userId=<%=userId%>&delete=<%= rsTamUng.getString("PK_SEQ") %>"><img src="../images/Delete20.png" alt="Xóa thanh toán" title="Xóa tạm ứng" width="20" height="20" border=0 onclick="if(!confirm('Bạn có muốn xóa tạm ứng này?')) return false;"></A>
		                                <%} %>									
				                    <%}else{ %>
				                    	<A href = "../../ErpTamUngUpdateSvl?userId=<%=userId%>&display=<%= rsTamUng.getString("PK_SEQ") %>"><IMG src="../images/Display20.png" alt="Hiển thị" title="Hiển thị" border=0></A>&nbsp; 
				                    <%} %>
				                    </TD>
				                    </TR>
								<% m++;}
							}
							catch(SQLException ex){System.out.print("Exception But toan "+ ex.getMessage() );}
						}
					%>
						
				</TABLE>
            </fieldset>
        </div>
    </div>  
</div>
<%tuList.DBClose(); %>
</form>
<script type="text/javascript">
jQuery(function()
		{		
			$("#NhanVienId").autocomplete("ErpTamUng_NhanVien_NCC_List.jsp");
		});	
jQuery(function()
		{		
			$("#NccId").autocomplete("ErpTamUng_NhanVien_NCC_List.jsp");
		});	
</script>
<% 
try{
	if(tuList!=null)
	{
		tuList.DBClose();
	}
	if( rsTienTe!=null){
		rsTienTe.close();
	}
	if(rsTamUng!=null){
		rsTamUng.close();
	}
	 
}catch(Exception er){
	
}
} %>
</body>
</HTML>