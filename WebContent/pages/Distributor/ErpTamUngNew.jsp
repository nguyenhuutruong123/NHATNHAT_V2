<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="geso.dms.distributor.beans.tamung.imp.*" %>
<%@ page import="geso.dms.distributor.beans.tamung.*" %>
<%@ page import="java.util.Calendar" %>
<%@ page import="java.util.Date" %>
<%@ page import="java.util.List" %>
<%@ page  import = "java.util.Iterator" %>
<%@ page  import = "java.sql.ResultSet" %>
<%@ page  import = "geso.dms.center.util.*" %>
<%@page import="java.sql.SQLException" %>
<%@ page  import = "java.text.DecimalFormat" %>
<%@ page  import = "java.text.NumberFormat" %>


<%	String userId = (String) session.getAttribute("userId");  
	String userTen = (String) session.getAttribute("userTen");  	
	String sum = (String) session.getAttribute("sum");
	Utility util = (Utility) session.getAttribute("util");
	if(!util.check(userId, userTen, sum)){
		response.sendRedirect("/Phanam/index.jsp");
	}else{ %>
<%

	IErpTamUng tuBean =(IErpTamUng)session.getAttribute("tuBean");
	ResultSet rsTienTe=(ResultSet)tuBean.getRsTienTe();
	ResultSet rsPo=(ResultSet)tuBean.getRsPo();
	ResultSet rsNcc=(ResultSet)tuBean.getRsNcc();
	ResultSet rsHttt=(ResultSet)tuBean.getHtttRs();
	NumberFormat formatter = new DecimalFormat("#,###,###.##");
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
<script language="JavaScript" type="text/javascript">
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
	
		 if(document.getElementById("NgayTamUng").value == "")
		 {
			 alert('Vui lòng chọn ngày tạm ứng');
			 return;
		 }
		
		 if(document.getElementById("LyDoTamUng").value == "")
		 {
			 alert('Vui lòng nhập lý do tạm ứng');
			 return;
		 }		 
		 if(document.getElementById("SoTienTamUng").value == "")
		 {
			 alert('Vui lòng nhập số tiền tạm ứng');
			 return;
		 }
		 
		 if(document.getElementById("TienTeId").value == "")
		 {
			 alert('Vui lòng chọn loại tiền');
			 return;
		 }
		 
		 if(document.getElementById("htttId").value == "")
		 {
			 alert('Vui lòng chọn hình thức thanh toán');
			 return;
		 }
		 
		/*  if(document.getElementById("ThoiGianHoanUng").value == "")
		 {
			 alert('Vui lòng chọn thời gian hoàn ứng');
			 return;
		 }
		 
		 if(document.getElementById("HinhThucHoanUng").value == "")
		 {
			 alert('Vui lòng chọn hình thức hoàn ứng');
			 return;
		 } */
	  document.forms["khtt"].action.value = "save";
	  document.forms["khtt"].submit(); 
    }
	
	function selectChange( pos)
	{
		var e=document.getElementById("TaiKhoanId_"+pos);
		var checkDungTtcp = e.options[e.selectedIndex].value;
		var array=checkDungTtcp.split("_");
		//Read only doi voi nhung tai khoan khong dung TTCP
		if(array[1]=="0")
			{
				var ele =document.getElementById("TtcpId_"+pos);
				ele.style.display='none';
			}
		else 
		{
			var ele =document.getElementById("TtcpId_"+pos);
			ele.style.display='';
		}
	}
	function AddReadOnly(pos)
	{
		var no=document.getElementById("No_"+pos);
		var co=document.getElementById("Co_"+pos);
		if(parseFloat(no.value)!=0&&no.value!="")
		{
			co.style.display='none';
			no.value=DinhDangTien(Math.round(no.value.replace(/,/g,"")));
		}
		else if(parseFloat(co.value)!=0 && co.value!="")
		{
			no.style.display='none';
			co.value =	DinhDangTien(Math.round(co.value.replace(/,/g,"") ));
		}
		else 
			{
				no.style.display='';
				co.style.display='';
			}
	}
	
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
	
	function DinhDangDonGia(num) 
	{
		num = num.toString().replace(/\$|\,/g,'');
			
		//num = (Math.round( num * 1000 ) / 1000).toString();
			
		var sole = '';
		if(num.indexOf(".") >= 0)
		{
			sole = num.substring(num.indexOf('.'));
		}
			
		if(isNaN(num))
			num = "0";
		sign = (num == (num = Math.abs(num)));
		num = Math.floor(num*100);
		num = Math.floor(num/100).toString();
		for (var i = 0; i < Math.floor((num.length-(1+i))/3); i++)
			num = num.substring(0,num.length-(4*i+3)) + ',' + num.substring(num.length-(4*i+3));
	
		var kq;
		if(sole.length >= 0)
		{
			kq = (((sign)?'':'-') + num) + sole;
		}
		else
			kq = (((sign)?'':'-') + num);
			
			//alert(kq);
		return kq;
	}
	
	function roundNumber(num, dec) 
	{
		var result = Math.round(num*Math.pow(10,dec))/Math.pow(10,dec);
		return result;
	}
	function DinhDang()
	{
		var tien=document.getElementById("SoTienTamUng");
		//tien.value=DinhDangTien(Math.round(tien.value.replace(/,/g,"") ));
		tien.value = DinhDangDonGia(tien.value.replace(/,/g,""));
	}
	function Replace()
	{
		var TaiKhoanId=document.getElementsByName("TaiKhoanId");
		var sodong=TaiKhoanId.length;
		for(var i=0;i<sodong;i++)
		{
			AddReadOnly(i);		
			selectChange(i);
		}
	}
	
</SCRIPT>
<link href="../css/select2.css" rel="stylesheet" />
	<script src="../scripts/select2.js"></script>
	<script>
	     $(document).ready(function() { $("select").select2(); });
	     
	</script>
</HEAD>
<BODY leftmargin="0" bottommargin="0" topmargin="0" rightmargin="0" onload="Replace()">
<form name ="khtt" method="post" action="../../ErpTamUngUpdateSvl" >
<input type="hidden" name="userId" value='<%= userId %>' >
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
							 <TD align="left" colspan="2" class="tbnavigation">Quản lý công nợ > Công nợ phải trả > Đề nghị tạm ứng > Tạo mới</TD> 
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
								<A href="../../ErpTamUngSvl?userId=<%= userId%>" ><img src="../images/Back30.png" alt="Quay ve"  title="Quay ve" border="1" longdesc="Quay ve" style="border-style:outset"></A>
							</TD>
						    <TD width="30" align="left" >
						    <div id="btnSave">
						    <A href="javascript: save()" ><IMG src="../images/Save30.png" title="Luu lai" alt="Luu lai" border = "1"  style="border-style:outset"></A>
						    </div>
						    </TD>
						   <TD align="left" >&nbsp;</TD>
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
				
	    				<textarea name="dataerror"  style="width: 100% ; font-weight:bold"  style="width: 100% ; color:#F00 ; font-weight:bold" readonly="readonly" rows="1"><%= tuBean.getMsg() %></textarea>
						</FIELDSET>
				   </TD>
				</tr>			

				<TR>
				  <TD height="100%" width="100%">
						<FIELDSET >
						<LEGEND class="legendtitle" style="color:black">Đề nghị tạm ứng </LEGEND>
						<TABLE border="0" width="100%" cellpadding="6" cellspacing="0">
                          <TR>
                          		<TD class="plainlabel" valign="middle" width="120px" >Ngày tạm ứng</TD>   
		                        <TD class="plainlabel" valign="middle"  >
		                        	<input type="text"  class="days" name="NgayTamUng" id="NgayTamUng" value="<%= tuBean.getNgayTamUng() %>"  maxlength="10" > 
		                        </TD>          
		                  </TR>
		                  
		                  <TR>
                          		<TD class="plainlabel" valign="middle" width="200px" >Đối tượng </TD>
                          		<TD class="plainlabel" valign="middle"  >
									<select name="DoiTuongTamUng" id="DoiTuongTamUng" style="width: 200px"  onchange="submitform()">
										<option value=""></option>
										<%if(tuBean.getDoiTuongTamUng().equals("1")){ %>
										<option value="1" selected="selected"> Nhân viên</option>
										<option value="2">Nhà cung cấp</option>
										<%} else if(tuBean.getDoiTuongTamUng().equals("2")){%>
										<option value="2" selected="selected"> Nhà cung cấp</option>
										<option value="1">Nhân viên</option>
										<%} else {%>
										<option value="1">Nhân viên</option>
										<option value="2" > Nhà cung cấp</option>
										<%} %>
										</select>
								</TD>          
		                  </TR> 
		                  
		                 <%if(tuBean.getDoiTuongTamUng().equals("1")) {%>
                          <TR>
                          		<TD class="plainlabel" valign="middle" width="220px" >Mã và tên nhân viên </TD>   
		                        <TD class="plainlabel" valign="middle"  >
		                        	<input type="text" name="NhanVienId" id="NhanVienId" value="<%= tuBean.getTenHienThi() %>"  style="width:400px" >
		                        </TD>          
		                  </TR>
		                  
		                  <%} %>
		                  
		                  
		                  
		                  <%  if(tuBean.getDoiTuongTamUng().equals("2")){ %>
		                   <TR>											
                          		<TD class="plainlabel" valign="middle" width="220px" >Mã và tên nhà cung cấp</TD>                        									
		                     	 <TD class="plainlabel" valign="middle"  >								
									<select name="nccId" id="nccId" style="width: 200px"  onchange="submitform()" >		
										<option value=""></option>	
										<%if(rsNcc!=null){	
											String selected=null;
										while(rsNcc.next()){	
											selected="";
										if(rsNcc.getString("PK_SEQ").equals(tuBean.getnccId()))	
											selected="selected";
										%>	
										<option  <%=selected %> value="<%=rsNcc.getString("PK_SEQ")%>"><%=rsNcc.getString("ten")%></option>	
										<%	
											}rsNcc.close(); 
										}	
											
										%>	
									</select>		
								</TD>                    			
		                  </TR>
		                  
		                   <TR>
                          		<TD class="plainlabel" valign="middle" width="220px" >Địa chỉ NCC </TD>   
		                        <TD class="plainlabel" valign="middle"  >
		                        	<input type="text" name="diachincc" id="diachincc" value="<%= tuBean.getdiachincc() %>"  > 
		                        </TD>          
		                  </TR> 
		                  
		                  <%} %>
		                  
		                  <TR>
                          		<TD class="plainlabel" valign="middle" width="220px" >Lý do tạm ứng </TD>   
		                        <TD class="plainlabel" valign="middle"  >
		                        	<input type="text" name="LyDoTamUng" id="LyDoTamUng" value="<%= tuBean.getLyDoTamUng() %>"  > 
		                        </TD>          
		                  </TR> 
		                  
		                 <%  if(tuBean.getDoiTuongTamUng().equals("2")){ %>
		                  <TR>											
                          		<TD class="plainlabel" valign="middle" width="220px" >Số PO</TD>                        									
		                     	 <TD class="plainlabel" valign="middle"  >								
									<select name="PoId" id="PoId" style="width: 200px" >		
										<option value=""></option>	
										<%if(rsPo!=null){	
											String selected=null;
										while(rsPo.next()){	
											selected="";
										if(rsPo.getString("dmhId").equals(tuBean.getPoId()))
											selected="selected";
										%>	
										<option  <%=selected %> value="<%=rsPo.getString("pk_Seq")%>"><%=rsPo.getString("dmhId")%></option>	
										<%	
											}rsPo.close(); 
										}	
											
										%>	
									</select>		
								</TD>                    			
		                  </TR> 	
		                   									
		                  <%} %>		                  
		                  
		                  <TR>
                          		<TD class="plainlabel" valign="middle" width="200px" >Tiền tệ</TD>   
		                     	 <TD class="plainlabel" valign="middle"  >
									<select name="TienTeId" id="TienTeId" style="width: 200px" >
										<option value=""></option>
										<%if(rsTienTe!=null){
											String selected=null;
										while(rsTienTe.next()){
											selected="";
										if(rsTienTe.getString("PK_SEQ").equals(tuBean.getTienTeId()))
											selected="selected";
										%>
										<option  <%=selected %> value="<%=rsTienTe.getString("PK_SEQ")%>"><%=rsTienTe.getString("Ma")%></option>
										<%
											}rsTienTe.close(); 
										}
										
										%>
									</select>
								</TD>          
		                  </TR> 
		                  
		                   <TR> 
							
							<TD class="plainlabel">Hình thức thanh toán</TD>
							<TD class="plainlabel">
								<select name="htttId" id="htttId" style ="width:200px">
									<option value=""> </option>
									<%
										if(rsHttt != null)
										{
											try
											{
												while(rsHttt.next())
												{  									 
												if( rsHttt.getString("pk_seq").equals(tuBean.getHtttId())){ %>
												<option value="<%= rsHttt.getString("pk_seq") %>" selected="selected" ><%= rsHttt.getString("pk_seq") %> - <%= rsHttt.getString("ten") %></option>
												<%		
												}else { %>
												<option value="<%= rsHttt.getString("pk_seq") %>" ><%= rsHttt.getString("pk_seq") %> - <%= rsHttt.getString("ten") %> </option>
												<% 		} 
												} 
												rsHttt.close();
											}catch(java.sql.SQLException ex){}
										}
								   %>
								</select>
							</TD>	
							
	   					</TR>
   		
		                  <TR>
                          		<TD class="plainlabel" valign="middle" width="220px" >Số tiền tạm ứng </TD>   
		                        <TD class="plainlabel" valign="middle"  >
		                        	<input type="text" name="SoTienTamUng" id="SoTienTamUng" value="<%= tuBean.getSoTienTamUng() %>" onkeypress="return keypress(event);" onchange="DinhDang()"> 
		                        </TD>          
		                  </TR> 		                  	                   
		                  
		                    <%  if(tuBean.getDoiTuongTamUng().equals("1")){ %>
		                  <TR>
                          		<TD class="plainlabel" valign="middle" width="220px" >Thời gian hoàn ứng</TD>   
		                        <TD class="plainlabel" valign="middle"  >
		                        	<input type="text" name="ThoiGianHoanUng" id="ThoiGianHoanUng" value="<%= tuBean.getThoiGianHoanUng() %>" class="days" > 
		                        </TD>          
		                  </TR> 
		                  
		                     <TR>
                          		<TD class="plainlabel" valign="middle" width="220px" >Hình thức hoàn ứng</TD>   
		                        <TD class="plainlabel" valign="middle"  >
		                        	<input type="text" name="HinhThucHoanUng" id="HinhThucHoanUng" value="<%= tuBean.getHinhThucHoanUng() %>"  > 
		                        </TD>          
		                  </TR> 
		                  
		                  <%} %>
						</TABLE>
						<hr />
								
						</FIELDSET>						
					</TD>
				</TR>
			</TABLE>
		</TD>
	</TR>
	</TABLE>
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
</BODY>
<% 
try{
	if( rsTienTe!=null){
		rsTienTe.close();
	}
	if(rsPo!=null){
		rsPo.close();
	}
	if(rsNcc!=null){
		rsNcc.close();
	}
	if(rsHttt!=null){
		rsHttt.close();
	}
	
	tuBean.DBClose();
	 
}catch(Exception er){
	
}

%>
</HTML>
<%}%>
