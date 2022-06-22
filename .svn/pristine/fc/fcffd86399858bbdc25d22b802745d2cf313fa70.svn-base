<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page  import = "geso.dms.center.beans.congtacvien.ICongtacvien" %>
<%@ page  import = "java.util.Hashtable"%>
<%@ page  import = "java.sql.ResultSet "%>
<%@ page  import = "geso.dms.center.util.*" %>
<%	String userId = (String) session.getAttribute("userId");  
	String userTen = (String) session.getAttribute("userTen");  	
	String sum = (String) session.getAttribute("sum");
	Utility util = (Utility) session.getAttribute("util");
	if(!util.check(userId, userTen, sum)){
		response.sendRedirect(request.getContextPath() + "/redirect.jsp");
	}else{ %>

<% ICongtacvien ddkdBean = (ICongtacvien)session.getAttribute("ddkdBean"); %>
<% ResultSet nppRs = (ResultSet)ddkdBean.getNppList(); %>
<% String khachhangId = (String)ddkdBean.getKhachhangId(); %>
<% ResultSet khachhangRs = (ResultSet)ddkdBean.getKhachhangRs(); %>
<% 	ResultSet kbh = (ResultSet)ddkdBean.getKenhbanhang();%>
<% ResultSet tdvRs = (ResultSet)ddkdBean.getNVBHRs(); %>
<% ResultSet tinhthanhRs = (ResultSet)ddkdBean.getTinhthanhRs(); %>
<% ResultSet quanhuyenRs = (ResultSet)ddkdBean.getQuanhuyenRs(); %>
<% ResultSet diabanRs = (ResultSet)ddkdBean.getDiabanRs();
String[] KhMa = ddkdBean.getKhMa(); 
String[] KhTen = ddkdBean.getKhTen();
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"> <% Utility Utilback = new Utility(); %>
<HTML>
<HEAD>
<TITLE>Phanam - Project</TITLE>  
<META http-equiv="Content-Type" content="text/html; charset=utf-8">

<META http-equiv="Content-Style-Type" content="text/css">

<LINK rel="stylesheet" href="../css/main.css" type="text/css">
<SCRIPT language="javascript" type="text/javascript">
 function confirmLogout(){
    if(confirm("Bạn có muốn đăng xuất?"))
    {
		top.location.href = "home.jsp";
    }
    return
  }
 function submitform()
 {   
    document.forms["ddkdForm"].submit();
 }

 function saveform()
 {
	 var ten = document.getElementById('ten');
	 var matkhau = document.getElementById("ma");
	  var diabanId = document.getElementById("diabanId");
	  var tdvId = document.getElementById("tdvId");
	 
	 if(ten.value.trim() == "")
	 {
		 alert('Vui lòng nhập tên cộng tác viên');
		 return;
	 }
	 if(ma.value.trim() == "")
	 {
		 alert('Vui lòng nhập mã cộng tác viên');
		 return;
	 }
	 /* if(diabanId.value.trim() == "")
	 {
		 alert('Vui lòng chọn địa bàn');
		 return;
	 } */
	
	 
 	 document.forms['ddkdForm'].action.value='save';
     document.forms['ddkdForm'].submit();
 }
 function keypress(e) 
 {    
 	var keypressed = null;
 	if (window.event)
 		keypressed = window.event.keyCode; //IE
 	else
 		keypressed = e.which; //NON-IE, Standard
 	
 	if (keypressed < 48 || keypressed > 57)
 	{ 
 		if (keypressed == 8 || keypressed == 127 || keypressed == 37 || keypressed == 39)
 		{//Phím Delete và Phím Back
 			return;
 		}
 		return false;
 	}
 }
 function SelectALL2()
	{
		var checkAll = document.getElementById("checkAll2");
		var spIds = document.getElementsByName("khachhangId");
		
		if(checkAll.checked == true)
		{
			for(i = 0; i < spIds.length; i++)
				spIds.item(i).checked = true;
		}
		else
		{
			for(i = 0; i < spIds.length; i++)
				spIds.item(i).checked = false;
		}
		
	}
 function replaces()
	{
		var spMa = document.getElementsByName("KhMa");
		var spTen = document.getElementsByName("KhTen");  
	
		
		
	
		
		var i;
		for(i = 0; i < spMa.length; i++)
		{
			if(spMa.item(i).value != "")
			{
				console.log(sp);
				var sp = spMa.item(i).value;
				var pos = parseInt(sp.indexOf(" - "));

				if(pos > 0)
				{
					spMa.item(i).value = sp.substring(0, pos);
					sp = sp.substr(parseInt(sp.indexOf(" - ")) + 3);
					
					spTen.item(i).value = sp;
				

				}
			}
			else
			{
				
					spMa.item(i).value = "";
					spTen.item(i).value = "";
					
				
				
			}
		}	
		setTimeout(replaces, 300);
	}
</SCRIPT>

<META http-equiv="Content-Type" content="text/html; charset=utf-8">
<link href="../css/jquery-ui.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="../scripts/Timepicker/jquery.min.js"></script>
<script type="text/javascript" src="../scripts/Timepicker/jquery-ui.min.js"></script>
<script type="text/javascript" language="JavaScript" src="../scripts/jquery.tools.min.js"></script>
<script type="text/javascript" src="../scripts/dropdowncontent.js"></script>
<script src="../scripts/ui/jquery.ui.core.js" type="text/javascript"></script>
<script src="../scripts/ui/jquery.ui.widget.js" type="text/javascript"></script>
<script src="../scripts/ui/jquery.ui.datepicker.js" type="text/javascript"></script>
<META http-equiv="Content-Style-Type" content="text/css">
<script type="text/javascript">
	$(document).ready(function() {		
		$( ".days" ).datepicker({			    
				changeMonth: true,
				changeYear: true				
		});            
	});	
</script>
<LINK rel="stylesheet" href="../css/main.css" type="text/css">

<link href="../css/select2.css" rel="stylesheet" />
<script src="../scripts/select2.js"></script>
<script>
	$(document).ready(function()
	{
		$(".select2").select2();
		
	});
</script>

<script type="text/javascript" src="../scripts/ajax.js"></script>
<script type="text/javascript" src="../scripts/KhachHangList.js"></script>
<style type="text/css">
	input
	{
		padding: 2px 0px;
	}
</style>
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

</HEAD>

<BODY leftmargin="0" bottommargin="0" topmargin="0" rightmargin="0">
<form name="ddkdForm" method="post" action="../../CongtacvienUpdateSvl">
<INPUT name="userId" type="hidden" value='<%= userId %>' size="30">
<INPUT name="id" type="hidden" value='<%= ddkdBean.getId() %>' size="30">
<input type="hidden" name="action" value='1'>

<TABLE width="100%" border="0" cellspacing="0" cellpadding="0"  height="100%">
    <TR>
        <TD colspan="4" align='left' valign='top' bgcolor="#FFFFFF">
            <TABLE width="100%" border="0" cellpadding="0" cellspacing="1">
                <TR>
                    <TD align="left" class="tbnavigation">
                        <table width="100%" border="0" cellpadding="0" cellspacing="0">
                          <tr height="22">
							 <TD align="left" colspan="2" class="tbnavigation">
							 		&nbsp;Dữ liệu nền &gt; Kinh doanh &gt;  Cộng tác viên &gt; Cập nhật</TD> 
                             <TD colspan="2" align="right" class="tbnavigation"><%=Utility.GLanguage("Chào mừng bạn",session,jedis) %>  <%=userTen %>&nbsp;  </TD></tr>

                        </table>
                    </TD>
                </TR>
            </TABLE>
            <TABLE width="100%" border="0" cellpadding="0" cellspacing="1">
                    <TR class = "tbdarkrow">
                        <TD width="30" align="center"><A href="../../CongtacvienSvl?userId=<%=userId %>" ><img src="../images/Back30.png" alt="Quay VeÂ"  title="Quay Ve" width="30" height="30" border="1" longdesc="Quay veÂ" style="border-style:outset"></A></TD>
                        <TD width="2" align="left" ></TD>
                        <TD width="30" align="left" ><A href="javascript:saveform()" ><IMG src="../images/Save30.png" title="Luu Lai" alt="Luu Lai" border = "1"  style="border-style:outset"></A></TD>

                        <TD align="left" >&nbsp;</TD>
                    </TR>
            </TABLE>
            <TABLE width="100%" border="0" cellpadding="0"  cellspacing="0">
                                <TR>
                                    <TD align="left" colspan="4" class="legendtitle">
                                        <FIELDSET>
                                        <LEGEND class="legendtitle"><%=Utility.GLanguage("Báo lỗi nhập liệu",session,jedis) %></LEGEND>
                                        
                                        <textarea name="dataerror"  style="width: 100% ; font-weight:bold"  style="width: 100% ; color:#F00 ; font-weight:bold" readonly="readonly" rows="1" ><%= ddkdBean.getMessage() %></textarea>
                                        <%ddkdBean.setMessage(""); %>
                                        </FIELDSET>
                                   </TD>
                                </TR>
                
                <TR>
                  <TD height="100%" width="100%">
                        <FIELDSET>
                        <LEGEND class="legendtitle" style="color:black">Thông tin cộng tác viên </LEGEND>

                         <TABLE border="0" width="100%" cellpadding="6" cellspacing="0">
                            <TR>
                            
                               <TD width="120px"  class="plainlabel">Mã CTV <FONT class="erroralert"> *</FONT></TD>
                               <TD width="250px" class="plainlabel"><input name="ma" id="ma" type="text" value="<%= ddkdBean.getMa()%>"></TD>
                                <TD width="120px" class="plainlabel" >Họ tên <FONT class="erroralert"> *</FONT></TD>
                                <TD width="250px" class="plainlabel"><INPUT name="ten" id="ten" type="text" value="<%= ddkdBean.getTen() %>" ></TD>
                                <TD width="120px" class="plainlabel" >Địa chỉ <FONT class="erroralert"> *</FONT></TD>
                                <TD width="250px" class="plainlabel" ><INPUT name="DiaChi" id="DiaChi" type="text" value="<%= ddkdBean.getDiachi() %>" ></TD>
                          	
                          	  
                          </TR>
                          <TR >
                          		<td class="plainlabel">Số điện thoại</td> 
                             	<td class="plainlabel"><INPUT name="DienThoai" id="DienThoai" type="text" value="<%= ddkdBean.getSodt()%>"></td>
							
                             	<td class="plainlabel">Giới tính</td> 
                             	<td class="plainlabel">
                             	<SELECT name="gioitinh" id="gioitinh" class="select2" style="width: 200px;"  >
									<option value=""> </option>
									<%if(ddkdBean.getGioitinh().equals("1")){ %>
		                       				<option value ="1" selected="selected"> Nam</option>
		                       				<option value ="0"> Nữ</option>
		                       				<%}else{ %>
		                       				<option value ="1"> Nam</option>
		                       				<option value ="0" selected="selected"> Nữ</option>
		                       				<%}  %>
                               	</SELECT>
                             	</td>
                             	
                             	<td class="plainlabel">Chuyên khoa</td> 
                             	<td class="plainlabel"><INPUT name="chuyenkhoa" id="chuyenkhoa" type="text" value="<%= ddkdBean.getChuyenKhoa()%>"></td>
                          	
                          </TR>

                          <TR>
                          		<td class="plainlabel">Chức vụ</td> 
                             	<td class="plainlabel"><INPUT name="chucvu" id="chucvu" type="text" value="<%= ddkdBean.getChucvu()%>"></td>
							
                             	<td class="plainlabel">Ngày sinh</td> 
                             	<td class="plainlabel"><INPUT class="days" name="ngaysinh" id="ngaysinh" type="text" value="<%= ddkdBean.getNgaysinh()%>"></td>
                             	
                             	<td class="plainlabel">Lịch khám</td> 
                             	<td class="plainlabel"><INPUT class="days" name="lichkham" id="lichkham" type="text" value="<%= ddkdBean.getLichkham()%>"></td>
                          	</TR>
                            
                            <TR>
                                <TD class="plainlabel" >Sở thích</TD>
								<td class="plainlabel"><INPUT name="sothich" id="sothich" type="text" value="<%= ddkdBean.getSothich()%>"></td>
								<TD class="plainlabel" style = "display:none">Địa bàn</TD>
								<TD class="plainlabel" style = "display:none" colspan = 3>
								<SELECT name="diabanId" id="diabanId" class="select2" style="width: 200px;"  >
									<option value=""> </option>
									<%
		                            if (diabanRs != null){
		                            	 while (diabanRs.next()){                      				                       				
		                       				 if(diabanRs.getString("pk_seq").equals(ddkdBean.getDiabanId())){ %>
		                       				<option value ="<%= diabanRs.getString("pk_seq") %>" selected="selected"> <%=diabanRs.getString("diengiai") %></option>
		                       				<%}else{ %>
		                       				<option value ="<%=diabanRs.getString("pk_seq") %>"> <%=diabanRs.getString("diengiai") %></option>
		                       				<%}     		
		                            	 }
		                             } %>
                               	</SELECT>
                               	</TD>
                               	<TD class="plainlabel" ><%=Utility.GLanguage("NHÂN VIÊN BÁN HÀNG",session,jedis) %></TD>
								<TD class="plainlabel" >
								<SELECT name="tdvId" id="tdvId" class="select2" style="width: 200px;">
									<option value=""> </option>
									<%
		                            if (tdvRs != null){
		                            	 while (tdvRs.next()){                      				                       				
		                       				 if(tdvRs.getString("pk_seq").equals(ddkdBean.getNVBHId())){ %>
		                       				<option value ="<%= tdvRs.getString("pk_seq") %>" selected="selected"> <%=tdvRs.getString("ten") %></option>
		                       				<%}else{ %>
		                       				<option value ="<%=tdvRs.getString("pk_seq") %>"> <%=tdvRs.getString("ten") %></option>
		                       				<%}     		
		                            	 }
		                             } %>
                               	</SELECT>
								</TD>
						 </TR>
						 <TR>
						
											<TD class="plainlabel" colspan ="6">
												<% if(ddkdBean.getTrangthai().equals("1")) { %>
												Hoạt động <input name="TrangThai" type="checkbox" value ="1" checked> 
											<% } else { %>
												Hoạt động <input name="TrangThai" type="checkbox" value ="1" > 
											<% } %>
														</TD>
											</TR>
                    </TABLE>
                    </FIELDSET>
                        
                    <TABLE border="0" width="100%" cellpadding="0" cellspacing="0">
                          <TR>
                            <TD colspan="4">
                        <FIELDSET>
                        <LEGEND class="legendtitle" style="color:black">Chọn Khách Hàng</LEGEND>

                        <TABLE border="0" width="100%" cellpadding="3" cellspacing="1">
		                    <tr class="tbheader">
					
							<th align="center" width="35%" >Mã khách hàng</th>
							<th align="center" width="65%">Tên khách hàng</th>
						
					
					</tr>
					
					<%
						int count = 0;
						
						if(KhMa != null)
						{
							for(int i = 0; i < KhMa.length; i++)
							{
								String style = "";
							
								if(KhMa[i] != null)
								{
							%>
							
							<tr <%= style %>  >
								<td >
									<input type="text" name="KhMa" value="<%= KhMa[i] %>" style="width: 100%"  onkeyup="ajax_showOptions(this,'nhapkho',event)" AUTOCOMPLETE="off" onkeydown="keyCode(event, <%= i %>)"  > 
									
								</td>
								
								<td>
									<input type="text" name="KhTen" value="<%= KhTen[i].trim() %>" style="width: 100%; text-align: left;" onkeypress="return keypress(event);" onkeyup="Dinhdang(this);" onkeydown="keyCode2(event, <%= i %>)" > 
								 
								</td>
							</tr>	
								
						<% count ++; }} } %>
					
					<% for(int i = count; i < 200; i++) { %>
						
						<tr>
						<td >
									<input type="text" name="KhMa" value="" style="width: 100%"  onkeyup="ajax_showOptions(this,'nhapkho',event)" AUTOCOMPLETE="off" onkeydown="keyCode(event, <%= i %>)"  > 
									
								</td>
								
								<td>
									<input type="text" name="KhTen" value="" style="width: 100%; text-align: left;" onkeypress="return keypress(event);" onkeyup="Dinhdang(this);" onkeydown="keyCode2(event, <%= i %>)" > 
								 
								</td>
							
							
							<% } %>	
							
						</tr>	
						
				
                        </TABLE>                        
                        </FIELDSET>

                            </TD></TR>
                        </TABLE>    
                    </TD>
                  </TR>
            </TABLE>
	    </TD>
    </TR>
</TABLE>
<script type="text/javascript">

replaces();
</script>
</form>
</BODY>
</HTML>
<%
try{
	if(nppRs!=null){
		nppRs.close();
	}
	if(tinhthanhRs!=null){
		tinhthanhRs.close();
	}
	if(quanhuyenRs!=null){
		quanhuyenRs.close();
	}
	if(diabanRs!=null){
		diabanRs.close();
	}
	if(khachhangRs!=null){
		khachhangRs.close();
	}
	if(kbh!=null){
		kbh.close();
	}
	if(tdvRs!=null){
		tdvRs.close();
	}
	ddkdBean.DBClose();
}catch(Exception er){
	
}

}%>