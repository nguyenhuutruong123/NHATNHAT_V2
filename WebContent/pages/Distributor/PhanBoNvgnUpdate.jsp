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

<% IDonhangList obj = (IDonhangList)s.getAttribute("obj"); %>
<% IPhanBoNvgn pbBean = (IPhanBoNvgn)s.getAttribute("pbBean"); 
ResultSet nvgnRs = (ResultSet)pbBean.getNvgnRs(); %>
<% ResultSet dhlist = (ResultSet)obj.getDonhangRs(); %>
<% ResultSet ddkd = (ResultSet)obj.getDaidienkd(); %>

<% ResultSet ttRs = (ResultSet)obj.getTtRs(); %>
<% ResultSet qhRs = (ResultSet)obj.getQhRs(); %>

<% String userId = (String) s.getAttribute("userId");  %>
<% Utility Util = new Utility(); %>
<%
	int[] quyen = new  int[6];
	quyen = Util.Getquyen("DonhangSvl","",userId);

%>
<% obj.setNextSplittings(); %>

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
 	document.forms['dhForm'].action.value= 'searchupdate';
 	document.forms['dhForm'].submit();
 }
 function newform()
 {

 }
 function PBform()
 {
	 var nvgn_fk = document.getElementById("nvgn_fk");
	 var ngayphanbo = document.getElementById("ngayphanbo");
	 if  (ngayphanbo.value.trim() == "" || nvgn_fk.value.trim() == "")
		 {
		 alert("Vui lòng nhập đầy đủ thông tin");
		 return;
		 }
 	document.forms['dhForm'].action.value= 'updatePB';
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
$(document).ready(function() { 
    $("select:not(.notuseselect2)").select2({ width: 'resolve' });     
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
<input type="hidden" name="nppId" value="<%=obj.getNppId()%>" >
<input type="hidden" name="dhId" id="dhId" value ="<%=pbBean.getId() %>" >
<input type="hidden" name="action" value="1">
<input type="hidden" name="lydoxoa" value="">

<input type="hidden"name="crrApprSplitting" value="<%= obj.getCrrApprSplitting() %>">
		<input type="hidden" name="nxtApprSplitting" value="<%= obj.getNxtApprSplitting() %>">
		<% obj.setNextSplittings(); %>


<input type="hidden" name="msg" id="msg" value='<%= obj.getMsg() %>'>
<%System.out.print("______________"+obj.getMsg()); %>
<script type="text/javascript">
	thongbao();
</script>

<TABLE width="100%" border="0" cellspacing="0" cellpadding="0"
	height="100%">
	<TR>
		<TD colspan="4" align='left' valign='top' bgcolor="#FFFFFF"><!--begin body Dossier-->
			<TABLE width="100%" border="0" cellpadding="0">
				<TR>
					<TD align="left" class="tbnavigation">
					   <TABLE width="100%" border="0" cellpadding="0" cellspacing="0">
						  <tr height="22">
						  
							   <TD align="left" colspan="2" class="tbnavigation"> Quản lý bán hàng > Bán hàng OTC > Phân bổ đơn hàng cho NVGN > Cập nhật phân bổ

							   <TD colspan="2" align="right" class="tbnavigation">Chào mừng <%= obj.getNppTen() %>&nbsp;</tr>

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
				<FIELDSET ><LEGEND class="legendtitle">&nbsp;<%=Utility.GLanguage("Tiêu chí tìm kiếm",session,jedis) %></LEGEND>
							<TABLE width="100%" cellpadding="6" cellspacing="0">
								<TR>
									<TD class="plainlabel" style="width:200px">Đơn hàng từ ngày</TD>
									<td class="plainlabel">
			                            <input type="text"  class="days" size="11"
			                                    id="tungay" name="tungay" value="<%= obj.getTungay() %>" maxlength="10" readonly />
			                    	</td>
			                    	</TR>
			                    	<TR>
			                     <TD class="plainlabel" style="width:200px">Đơn hàng đến ngày</TD>
							     <td class="plainlabel">
			                            <input type="text"  class="days" size="11"
			                                    id="denngay" name="denngay" value="<%= obj.getDenngay() %>" maxlength="10" readonly />
			                    	</td>
								</TR>
								
								<TR>
									<TD class="plainlabel" colspan="6">
                                   <a class="button2" href="javascript:submitform()" >
    	                           <img style="top: -4px;" src="../images/Search30.png" alt=""><%=Utility.GLanguage("Tìm kiếm",session,jedis) %>  </a> &nbsp;&nbsp;&nbsp;                         
									<a class="button2" href="javascript:clearform()">
	                               <img style="top: -4px;" src="../images/button.png" alt=""><%=Utility.GLanguage("Nhập lại",session,jedis) %></a>&nbsp;&nbsp;&nbsp;&nbsp;	
									</TD>
								</TR>
							</TABLE>
				  </FIELDSET>
			   </TD>	
				</TR>
				<TR> <TD> 
				<Fieldset>
				<legend class="legendtitle">Chi tiết phân bổ</legend>
				<TABLE width="100%" cellpadding="6" cellspacing="0">
				<TR>
				<td class="plainlabel" style="width: 200px;">Nhân viên giao nhận<FONT class="erroralert"> *</FONT></td>
				<td class="plainlabel" colspan="3"><SELECT name="nvgn_fk" id="nvgn_fk">
				<option value=""></option>
				<% if(nvgnRs != null){
	 				 try{ while(nvgnRs.next()){ 													 
  						if(nvgnRs.getString("pk_seq").equals(pbBean.getNvgn_fk())){ %>
				<option value='<%=nvgnRs.getString("pk_seq")%>' selected><%=nvgnRs.getString("ten") %></option>
						<%}else{ %>
				<option value='<%=nvgnRs.getString("pk_seq")%>'><%=nvgnRs.getString("ten") %></option>
					<%}}}catch(java.sql.SQLException e){}} %>
				</SELECT> </TD>
				</TR>
				<TR>
				<TD class="plainlabel" style="width: 200px;">Ngày phân bổ<FONT class="erroralert"> *</FONT></TD>
				<td class="plainlabel" colspan="3">
                   <input type="text"  class="days" size="11"
                    id="ngayphanbo" name="ngayphanbo" value="<%= pbBean.getNgayphanbo() %>" maxlength="10" readonly />
                </td>
				</TR>
				</TABLE>
				</Fieldset>
				</TD> </TR>
				<TR>
					<TD width="100%">
					<FIELDSET>
					<LEGEND class="legendtitle">&nbsp;Đơn hàng chờ &nbsp;&nbsp;&nbsp; 

					<%if(quyen[Utility.THEM]!=0){ %>
						<a class="button3"  onclick="PBform()">
	    					<img style="top: -8px; " src="../images/New.png" alt="">Phân bổ </a>
	    				<%} %>                            

					<!--<INPUT name="action" type="submit" value="Tao moi"> -->	
					</LEGEND>
					<TABLE class="" width="100%">
						<TR>
							<TD width="98%">
							<TABLE width="100%" border="0" cellspacing="1px" cellpadding="4px">
								
								<TR class="tbheader">
									<th width="5%" align="center">Mã ĐH</th>
									<th width="9%" align="center">Ngày đơn hàng</th>
									<th width="9%" align="center"> Mã Fast</th>
									<th width="18%" align="center">Khách hàng</th>
									<th width="18%" align="center">Địa chỉ</th>
									<th width="8%" align="center"><%=Utility.GLanguage("Trạng thái",session,jedis) %></th>
									<!-- <th width="1%" align="center" style="display: none"><%=Utility.GLanguage("Ngày tạo",session,jedis) %></th>
									<th width="1%" align="center" style="display: none"><%=Utility.GLanguage("Người tạo",session,jedis) %></th>
									<th width="7%" align="center" ><%=Utility.GLanguage("Ngày sửa",session,jedis) %></th>
									<th width="7%" align="center" >Thời gian</th>
									<th width="7%" align="center" ><%=Utility.GLanguage("Người sửa",session,jedis) %> </th>
									<th width="7%" align="center"><%=Utility.GLanguage("NHÂN VIÊN BÁN HÀNG",session,jedis) %></th> -->
									<!-- <th width="9%" align="center">Tổng giá trị</th>  -->
									<th width="8%" align="center">NVGN hiện tại</th> 
									<th align="center" width="5%">Chọn hết <input type ="checkbox"  id="cbchon" name ="all" onclick ="sellectAll('cbchon','chon')"></th>
								</TR>
								
								<% 
								
								if(dhlist != null)
								{
									int m = 0;
									String lightrow = "tblightrow";
									String darkrow = "tbdarkrow";
									while (dhlist.next()){										
										if (m % 2 != 0) {%>	
														
											<TR class= <%=lightrow%>  ">
									<%} else {%>
											
											<TR class= <%= darkrow%>   > 
										<%}%>
											 	<TD align="center" ><input name="dhIdd" type="hidden"  value="<%= dhlist.getString("dhId") %>"  />
												<%= dhlist.getString("dhId") %>
												</TD> 
												<TD align="center"><%= dhlist.getString("ngaynhap") %></TD>
												<TD align="center">	<%= dhlist.getString("maFast") %> </TD>    
												<TD align="left"><%= dhlist.getString("khTen") %></TD>
												<TD align="left"><%= dhlist.getString("khdiachi") %></TD>
												<% 
												double tonggiatri = dhlist.getDouble("tonggiatri");
												String trangthai = "";
												boolean check = false;
												String[] nspArr = obj.getArrPBdonhang_fk();
												 if (nspArr != null && nspArr.length > 0 ) 
												{
													for(int  x = 0; x < nspArr.length ; x++)
													{
														//System.out.println("Arr["+x+"]: "+nspArr[x]+" -- Length: "+nspArr.length);
														if(nspArr[x].equals(dhlist.getString("dhId")))
														{
															check = true;
															break;
														}
													}
												} 
													
												 String trangthaigh = "";
												if (!dhlist.getString("pbId").equals("0"))
														trangthai = "<b>Đã phân bổ<b>";
													else
														trangthai = "Chờ phân bổ";
												 
												if (trangthai.equals("<b>Đã phân bổ<b>")){
													if (dhlist.getString("trangthaigh").equals("1"))
														trangthaigh = "<b style=\"color:red\"> (Giao thiếu hàng) </color>";
													else if (dhlist.getString("trangthaigh").equals("-1"))
														trangthaigh = "<i style=\"color:red\"> (Chờ giao hàng) <i>";
												}
													%>
													<TD align="center"><span><%=trangthai%><%=trangthaigh%></span></TD>
													<TD align="center"> <%= dhlist.getString("nvgn") %></TD>
													<TD align="center">
												
												<% if(check) { %>
														<input type="checkbox" name="chon" style="align:center" checked value="<%=dhlist.getString("dhId")%>">
													<%} else { %>
														<input type="checkbox" name="chon" style="align:center" value="<%=dhlist.getString("dhId")%>">
													<%}%>
												</TD>
											</TR>
									<%m++; } dhlist.close(); }%>	
										 <tr class="tbfooter">
														<TD align="center" valign="middle" colspan="13"
															class="tbfooter">
															<%if(obj.getNxtApprSplitting() >1) {%> <img alt="Trang Dau"
															src="../images/first.gif" style="cursor: pointer;"
															onclick="View('dhForm', 1, 'view')"> &nbsp; <%}else {%>
															<img alt="Trang Dau" src="../images/first.gif">
															&nbsp; <%} %> <% if(obj.getNxtApprSplitting() > 1){ %> <img
															alt="Trang Truoc" src="../images/prev.gif"
															style="cursor: pointer;"
															onclick="Prev('dhForm', 'prev')"> &nbsp; <%}else{ %>
															<img alt="Trang Truoc" src="../images/prev_d.gif">
															&nbsp; <%} %> <%
													int[] listPage = obj.getNextSplittings();
													for(int i = 0; i < listPage.length; i++){
												%> <% 							
											
												if(listPage[i] == obj.getNxtApprSplitting()){ %> <a
															style="color: white;"><%= listPage[i] %>/ <%=obj.getTheLastSplitting() %></a>
															<%}else{ %> <a
															href="javascript:View('dhForm', <%= listPage[i] %>, 'view')"><%= listPage[i] %></a>
															<%} %> <input type="hidden" name="list"
															value="<%= listPage[i] %>" /> &nbsp; <%} %> <% if(obj.getNxtApprSplitting() < obj.getTheLastSplitting()){ %>
															&nbsp; <img alt="Trang Tiep" src="../images/next.gif"
															style="cursor: pointer;"
															onclick="Next('dhForm', 'next')"> &nbsp; <%}else{ %>
															&nbsp; <img alt="Trang Tiep" src="../images/next_d.gif">
															&nbsp; <%} %> <%if(obj.getNxtApprSplitting() == obj.getTheLastSplitting()) {%>
															<img alt="Trang Cuoi" src="../images/last.gif">
															&nbsp; <%}else{ %> <img alt="Trang Cuoi"
															src="../images/last.gif" style="cursor: pointer;"
															onclick="View('dhForm', -1, 'view')"> &nbsp; <%} %>
														</TD>
													</tr>
  			
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
		if(!(ddkd == null))
			ddkd.close();
		if(obj != null){
			obj.DBclose();
			obj = null;
		}
		if(dhlist!=null){
			dhlist.close();
		}
		Util=null;
		s.setAttribute("obj",null);
		
	}catch(Exception e){e.printStackTrace();}
%>