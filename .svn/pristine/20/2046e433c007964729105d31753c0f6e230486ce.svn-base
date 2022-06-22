<%@page import="geso.dms.distributor.db.sql.dbutils"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page  import = "java.util.Iterator" %>
<%@ page  import = "java.util.List" %>
<%@ page  import = "java.util.ArrayList" %>
<%@page import="geso.dms.distributor.util.Utility"%>
<%@ page  import = "geso.dms.distributor.beans.donhang.*" %>
<%@ page  import = "java.text.DecimalFormat" %>
<%@ page  import = "java.text.NumberFormat" %>
<%@ page  import = "java.sql.ResultSet" %>

<% 
NumberFormat formatter = new DecimalFormat("#,###,###");
	HttpSession s = request.getSession();
   if (s.isNew()){
	   s.invalidate();
	   System.out.println("New session");
   }else{
	   
	   System.out.println("Old session");
   }
%>
<% String userId = (String) s.getAttribute("userId");  %>
<% Utility Util = new Utility(); %>
<% IPhanBoNvgn obj = (IPhanBoNvgn)s.getAttribute("obj"); 
	
	ResultSet donhangRs = obj.getDonhangRs();

%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"> <% Utility Utilback = new Utility(); %>
<HTML>
<HEAD>
<TITLE><%= getServletContext().getInitParameter("TITLENAME") %></TITLE>
<META http-equiv="Content-Type" content="text/html; charset=utf-8">
<META http-equiv="Content-Style-Type" content="text/css">

<LINK rel="stylesheet" type="text/css" media="print" href="../css/impression.css">
<LINK rel="stylesheet" href="../css/main.css" type="text/css">
<link type="text/css" rel="stylesheet" href="../css/mybutton.css">
<script type="text/javascript" src="../scripts/dropdowncontent.js"></script>
<script type="text/javascript" src="../scripts/phanTrang.js"></script>

<script type="text/javascript" src="../scripts/jquery.min.1.7.js"></script>
<link href="../css/jquery-ui.css" rel="stylesheet" type="text/css" />
<script src="../scripts/ui/jquery.ui.core.js" type="text/javascript"></script>
<script src="../scripts/ui/jquery.ui.widget.js" type="text/javascript"></script>
<script src="../scripts/ui/jquery.ui.datepicker.js"
	type="text/javascript"></script>
	
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
	
<script type="text/javascript">
	function open_popup_window(url){
		
		window.open(url, "_blank", "toolbar=yes, scrollbars=yes, resizable=yes,  width=800, height=400");
		}
	</script>

<script type="text/javascript" src="../scripts/cool_DHTML_tootip.js"></script>
 <style type="text/css">
  #dhtmltooltip
  {
   position: absolute;
   left: -300px;
   width: 300px;
   border: 1px solid black;
   padding: 5px;
   background-color: lightyellow;
   visibility: hidden;
   z-index: 100;
   font-size: 1.2em;
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
  
</style>	
	
<script type="text/javascript">
	$(document).ready(function() {		
			$( ".days" ).datepicker({			    
					changeMonth: true,
					changeYear: true				
			});            
        }); 		
		
    </script>

<SCRIPT language="javascript" type="text/javascript">

function sellectAll(cbId1, cbId2) {
	var chkAll_Lct = document.getElementById(cbId1);
	var loaiCtIds = document.getElementsByName(cbId2);

	if (chkAll_Lct.checked) {
		for ( var i = 0; i < loaiCtIds.length; i++) {
			loaiCtIds.item(i).checked = true;
		}
	} else {
		for ( var i = 0; i < loaiCtIds.length; i++) {
			loaiCtIds.item(i).checked = false;
		}
	}
}
 function submitform()
 {
 	document.forms['dhForm'].action.value= 'search';
 	document.forms['dhForm'].submit();
 }
 function newform()
 {

 }
 function PBform()
 {
 	document.forms['dhForm'].action.value= 'PB';
 	document.forms['dhForm'].submit();
 }
 function clearform()
 {   
	document.forms['dhForm'].tungay.value= '';
	document.forms['dhForm'].denngay.value= '';
	document.forms['dhForm'].action.value= 'search';
	submitform();
 }
 
 function Next()
 {
 	document.forms['dhForm'].action.value= 'next';
 	document.forms['dhForm'].submit();
 }

 function Prev()
 {
 	document.forms['dhForm'].action.value= 'prev';
 	document.forms['dhForm'].submit();
 }

 function XemTrang(page)
 {
 	document.forms['dhForm'].action.value= 'view';
 	document.forms['dhForm'].currentPage.value = page;
 	document.forms['dhForm'].submit();
 }
 function processing(id,chuoi){
	 document.getElementById(id).innerHTML = "<a href='#'><img src='../images/waiting.gif'  title='cho thuc hien..' border='1' longdesc='cho thuc hien..' style='border-style:outset'> Proc...</a>";
	 document.getElementById(id).href = chuoi;
	  
}
 function thongbao()
	{
		if(document.getElementById("msg").value != '')
			alert(document.getElementById("msg").value);
	}
 function XacNhanXoa(dhId)
 {
	 var r = confirm("Bạn chắc chắn muốn xóa đơn hàng ( " + dhId + " ) ");
	 if (r == false) {
	     return;
	 }
	 
	 //alert('Ly do xoa: ' + document.getElementById("lydoxoa" + dhId).value);
	 document.forms['dhForm'].lydoxoa.value = document.getElementById("lydoxoa" + dhId).value;
	 document.forms['dhForm'].dhId.value = dhId;
	 document.forms['dhForm'].action.value = 'delete';
	 document.forms['dhForm'].submit();
 }
 
 function duyetform(Id)
 {

 }
 
 
 function xuatExcel()
 {

 }
 
 
 
 
 
</SCRIPT>


<link media="screen" rel="stylesheet" href="../css/colorbox.css">
<script src="../scripts/colorBox/jquery.colorbox.js"></script>
<link href="../css/select2.css" rel="stylesheet" />
<script src="../scripts/select2.js"></script>
<script>
$(document).ready(function()
{
	$("#ddkdTen").select2();
	$("#ttId").select2();
	$("#qhId").select2();
	
});
</script> 

<!-- <script>
<link href="../css/select2.css" rel="stylesheet" />
<script src="../scripts/select2.js"></script>
<script>
     $(document).ready(function() { 
      $("select:not(.notuseselect2)").select2({ width: 'resolve' });     
     });
</script> -->

</HEAD>
<BODY leftmargin="0" bottommargin="0" topmargin="0" rightmargin="0">
<form name="dhForm" method="post" action="../../PhanBoNvgnSvl">
<% redis.clients.jedis.Jedis jedis =  geso.dms.center.util.Utility.getJedis(); %><% geso.dms.center.util.Csrf csdr=new geso.dms.center.util.Csrf(request,response,true,false,true);%>
 <input type="hidden" name="<%=csdr.get_tokenName() %>" value='<%=csdr.get_tokenValue() %>'>
	
<input type="hidden" name="userId" value="<%= userId %>" >
<input type="hidden" name="dhId" id="dhId" value=<%= obj.getId() %> >
<input type="hidden" name="action" value="1">
<input type="hidden" name="lydoxoa" value="">

<TABLE width="100%" border="0" cellspacing="0" cellpadding="0"
	height="100%">
	<TR>
		<TD colspan="4" align='left' valign='top' bgcolor="#FFFFFF"><!--begin body Dossier-->
			<TABLE width="100%" border="0" cellpadding="0">
				<TR>
					<TD align="left" class="tbnavigation">
					   <TABLE width="100%" border="0" cellpadding="0" cellspacing="0">
					  
						<tr height="22">
							   <TD align="left" colspan="2" class="tbnavigation"> Quản lý bán hàng > Bán hàng OTC > Phân bổ đơn hàng cho NVGN > Hiển thị

						</TABLE>
						
									<TABLE width="100%" border="0" cellpadding="1" cellspacing="0">
										<TR>
											<TD>
												<TABLE width="100%" border="0" cellpadding="0"
													cellspacing="0">
													<TR class="tbdarkrow">
														<TD width="30" align="left"><A
															href="../../PhanBoNvgnSvl?userId=<%=userId %>"><img src="../images/Back30.png" alt="Quay về" title="Quay về" border="1" longdesc="Quay ve"	style="border-style: outset">
														</A>
														</TD>
										</TR>
												</TABLE>
											</TD>
										</TR>
									</TABLE>
					</TD>
		  </TR>

			<TR>
				<TD >
				<FIELDSET><LEGEND class="legendtitle">&nbsp;Phân bổ</LEGEND>
							<TABLE width="100%" cellpadding="6" cellspacing="0">
								<TR>
								<TD class="plainlabel" >Mã phân bổ</TD>
									<td class="plainlabel" colspan="3">
			                            <input type="text" size="11" style="width:350px"
			                                    id="pk" name="pk" value="<%= obj.getId() %>" maxlength="10" readonly />
			                    </td>
			                    </TR><TR>	
			                    	<TD class="plainlabel" >Nhân viên giao nhận</TD>
									<td class="plainlabel" colspan="3">
			                            <input type="text" size="11" style="width:350px"
			                                    id="nvgn_ten" name="nvgn_ten" value="<%= obj.getNvgn_ten() %>" maxlength="10" readonly />
			                    	</td>
			                    </TR> 
			                    <TR>	
			                    	<TD class="plainlabel" >Ngày phân bổ</TD>
									<td class="plainlabel" colspan="3">
			                            <input type="text" size="11" style="width:350px" class="days"
			                                    id="ngayphanbo" name="ngayphanbo" value="<%= obj.getNgayphanbo() %>" maxlength="10" readonly />
			                    	</td>
			                    </TR> 
			                    <TR>
								<TD class="plainlabel" ><%=Utility.GLanguage("Người tạo",session,jedis) %></TD>
									<td class="plainlabel">
			                            <input type="text" size="11" style="width:350px"
			                                    id="nguoitao" name="nguoitao" value="<%= obj.getNguoitao() %>" maxlength="10" readonly />
			                    	</td>
			                    	<TD class="plainlabel" ><%=Utility.GLanguage("Người sửa",session,jedis) %></TD>
									<td class="plainlabel">
			                            <input type="text" size="11" style="width:350px"
			                                    id="nguoisua" name="nguoisua" value="<%= obj.getNguoisua() %>" maxlength="10" readonly />
			                    	</td>
			                    	</TR>
		                    	<TR>
									<TD class="plainlabel" ><%=Utility.GLanguage("Ngày tạo",session,jedis) %></TD>
									<td class="plainlabel">
			                            <input type="text"  class="days" size="11"
			                                    id="tungay" name="tungay" value="<%= obj.getNgaytao() %>" maxlength="10" readonly />
			                    	</td>
			                    	  <TD class="plainlabel" ><%=Utility.GLanguage("Ngày sửa",session,jedis) %></TD>
							      <td class="plainlabel">
			                            <input type="text"  class="days" size="11"
			                                    id="denngay" name="denngay" value="<%= obj.getNgaysua() %>" maxlength="10" readonly />
			                    	</td>
			                    	
								</TR>
								<TR>

								</TR>
							</TABLE>
				  </FIELDSET>
			   </TD>	
				</TR>
				<TR>
					<TD width="100%">
					<FIELDSET>
					<LEGEND class="legendtitle">&nbsp;Đơn hàng chi tiết &nbsp;&nbsp;&nbsp; 
					</LEGEND>
					<TABLE class="" width="100%">
						<TR>
							<TD width="98%">
							<TABLE width="100%" border="0" cellspacing="1px" cellpadding="4px">
							<% String query = ""; String donhang_fk="";
								dbutils db = obj.getDb();
								if (obj.getId() != null && obj.getId().length() > 0 && donhangRs != null) {
									
									while (donhangRs.next()) {
										donhang_fk = donhangRs.getString("donhang_fk");%>
										<TR>
										<td class="plainlabel" colspan = "1">Đơn hàng: <%=donhang_fk %> </td>
										<td class="plainlabel" colspan = "1">Mã - Tên khách hàng: <%=donhangRs.getString("makh") %> </td>
										<td class="plainlabel" colspan = "2">Địa chỉ: <%=donhangRs.getString("diachi") %>
										</td>
										</TR>
										<%  
										ResultSet rs1 = obj.getDonhang_SanPhamRs(donhang_fk);
										while (rs1.next()) { %>
										<TR>
										<td class="plainlabel"><%=Utility.GLanguage("Sản phẩm",session,jedis) %> </TD>
										<td class="plainlabel">	<input type="text" id="sanpham_ten" name="sanpham_ten" value="<%= rs1.getString("ten") %>" maxlength="10" readonly />
										</TD>
											<input type="hidden" id="sanpham_fk" name="sanpham_fk" value="<%= rs1.getString("pk_seq") %>" />
										<td class="plainlabel">Số lượng</TD>
										<td class="plainlabel">	
											<input type="text" id="soluong" name="soluong" value="<%= rs1.getString("soluong") %>" maxlength="10" readonly />
										</td>
										</TR>
										
										<% } %> 
										<tr><td colspan="4"><hr></td></tr>
										<% 
										rs1.close(); } 
									donhangRs.close(); }
										%>
  			
							</TABLE>
							</TD>
						</TR>
					</TABLE>
					</FIELDSET>
					</TD>
				</TR>
			</TBODY>
		</TABLE>
		<!--end body Dossier--></TD>
	</TR>
</TABLE>
</form>
</BODY>
</HTML>
<%
	try
	{	
		if(obj != null){
			obj.DBclose();
			obj = null;
		}
		Util=null;
		s.setAttribute("obj",null);
		
	}
	catch(Exception e){e.printStackTrace();}
%>