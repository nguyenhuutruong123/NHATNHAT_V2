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
<% ResultSet dhlist = (ResultSet)obj.getDonhangRs(); %>
<% ResultSet ddkd = (ResultSet)obj.getDaidienkd(); %>

<% ResultSet ttRs = (ResultSet)obj.getTtRs(); %>
<% ResultSet qhRs = (ResultSet)obj.getQhRs(); %>

<% String userId = (String) s.getAttribute("userId");  %>
<% Utility Util = new Utility(); %>
<%
	int[] quyen = new  int[6];
	quyen = Util.Getquyen("DonhangSvl","&GSBH=GSBH",userId);

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

 function submitform()
 {
 	document.forms['dhForm'].action.value= 'search';
 	document.forms['dhForm'].submit();
 }
 function newform()
 {
 	document.forms['dhForm'].action.value= 'new';
 	document.forms['dhForm'].submit();
 }
 function clearform()
 {   
	document.forms['dhForm'].tungay.value= '';
	document.forms['dhForm'].denngay.value= '';
	document.forms['dhForm'].trangthai.value ='';
	document.forms['dhForm'].khachhang.value ='';
	document.forms['dhForm'].sodonhang.value ='';
	document.forms['dhForm'].mafast.value ='';
	document.forms['dhForm'].ddkdTen.selectedIndex = 0; 
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
	 document.forms['dhForm'].action.value = 'delete_GS';
	 document.forms['dhForm'].submit();
 }
 
 function duyetform(Id)
 {
	 if(!confirm('Bạn có muốn duyệt đơn hàng này?')) 
	 {
		 return false ;
	 }
	 
	 document.forms['dhForm'].dhId.value = Id;
 	 document.forms['dhForm'].action.value= 'duyet_GS';
 	 document.forms['dhForm'].submit();
 }
 
 
 function xuatExcel()
 {
 	document.forms['dhForm'].action.value= 'toExcel';
 	document.forms['dhForm'].submit();
 }
 
 
 
 
</SCRIPT>
</HEAD>
<BODY leftmargin="0" bottommargin="0" topmargin="0" rightmargin="0">
<form name="dhForm" method="post" action="../../DonhangSvl">
<input type="hidden" name="userId" value="<%= userId %>" >
<input type="hidden" name="nppId" value="<%=obj.getNppId()%>" >
<input type="hidden" name="dhId" id="dhId"  >
<input type="hidden" name="action" value="1">
<input type="hidden" name="lydoxoa" value="">
<% System.out.println("npp la "+obj.getNppId()); %>
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
							   <TD align="left" colspan="2" class="tbnavigation"> Quản lý bán hàng > Bán hàng OTC > Giám sát Duyệt

							   <TD colspan="2" align="right" class="tbnavigation">Chào mừng  <%= obj.getNppTen() %>&nbsp;</tr>
						</TABLE>
					</TD>
		  </TR>
			<TR>
				<TD >
				<FIELDSET><LEGEND class="legendtitle">&nbsp;<%=Utility.GLanguage("Tiêu chí tìm kiếm",session,jedis) %></LEGEND>
							<TABLE width="100%" cellpadding="6" cellspacing="0">
								
								<TR>
										<TD width="120px" class="plainlabel">Tỉnh thành</TD>
										<TD class="plainlabel" width="250px" >
										<SELECT name="ttId" onChange = "submitform();">
											  <option value=""> </option>
											  <% if(ttRs != null){
											  	try{ while(ttRs.next()){ 
									    			if(ttRs.getString("pk_Seq").equals(obj.getTtId())){ %>
									      				<option value='<%=ttRs.getString("pk_Seq")%>' selected><%=ttRs.getString("ten") %></option>
									      			<%}else{ %>
									     				<option value='<%=ttRs.getString("pk_Seq")%>'><%=ttRs.getString("ten") %></option>
									     	<%}} ttRs.close(); }catch(Exception e){ e.printStackTrace(); } }%>	 
	                                    	</SELECT>
	                                    </TD>
	                                    	 <TD width="130px" class="plainlabel">Quận huyện</TD>
											<TD class="plainlabel">
												<SELECT name="qhId" onChange = "submitform();">
												  <option value=""> </option>
												  <% if(qhRs != null){
												  	try{ while(qhRs.next()){ 
										    			if(qhRs.getString("pk_Seq").equals(obj.getQhId())){ %>
										      				<option value='<%=qhRs.getString("pk_Seq")%>' selected><%=qhRs.getString("ten") %></option>
										      			<%}else{ %>
										     				<option value='<%=qhRs.getString("pk_Seq")%>'><%=qhRs.getString("ten") %></option>
										     	<%}} qhRs.close(); }catch(Exception e){ e.printStackTrace(); } }%>	 
		                                    	</SELECT>
		                                    	</TD>
		                                    	<td class="plainlabel" colspan="4"></td>
                                    </TR>

								<TR>
									<TD width="120px" class="plainlabel"><%=Utility.GLanguage("NHÂN VIÊN BÁN HÀNG",session,jedis) %> </TD>
									<TD class="plainlabel" width="250px" >
									<SELECT name="ddkdTen" onChange = "submitform();">
										  <option value=""> </option>
										  <% if(ddkd != null){
										  	try{ while(ddkd.next()){ 
								    			if(ddkd.getString("ddkdId").equals(obj.getDdkdId())){ %>
								      				<option value='<%=ddkd.getString("ddkdId")%>' selected><%=ddkd.getString("ddkdTen") %></option>
								      			<%}else{ %>
								     				<option value='<%=ddkd.getString("ddkdId")%>'><%=ddkd.getString("ddkdTen") %></option>
								     	<%}} ddkd.close(); }catch(Exception e){ e.printStackTrace(); } }%>	 
                                    </SELECT></TD>
                                    <TD width="130px" class="plainlabel"><%=Utility.GLanguage("Trạng thái",session,jedis) %> </TD>
									<TD class="plainlabel">
										<select name="trangthai" onChange="submitform();">
											<% if (obj.getTrangthai().equals("1")){%>
											  	<option value="1" selected>Đã chốt</option>
											  	<option value="0">Chưa chốt</option>
											  	<option value="2">Đã hủy</option>
											  	<option value="3">Đã xuất kho</option>
											  	<option value="4">Đã xuất HĐ</option>
											  	<option value="7">Đã in ĐH</option>
											  	<option value=""></option>
											  
											<%}else if(obj.getTrangthai().equals("0")) {%>
											 	<option value="0" selected>Chưa chốt</option>
											  	<option value="1" >Đã chốt</option>
											 	<option value="2" >Đã hủy</option>
											 	<option value="3" >Đã xuất kho</option>
											 	<option value="4">Đã xuất HĐ</option>
											 	<option value="7">Đã in ĐH</option>
											  	<option value="" ></option>
											  	
											<%}else if(obj.getTrangthai().equals("2")){%>											
											 	<option value="2" selected>Đã hủy</option>										  	
											  	<option value="0" >Chưa chốt</option>
											  	<option value="1" >Đã chốt</option>
											  	<option value="3" >Đã xuất kho</option>
											  	<option value="4">Đã xuất HĐ</option>
											  	<option value="7">Đã in ĐH</option>
											  	<option value="" ></option>
											  	
											<%}else if(obj.getTrangthai().equals("3")){%>											
											 	<option value="3" selected>Đã xuất kho</option>										  	
											  	<option value="0" >Chưa chốt</option>
											  	<option value="1" >Đã chốt</option>
											  	<option value="2" >Đã hủy</option>
											  	<option value="4">Đã xuất HĐ</option>
											  	<option value="7">Đã in ĐH</option>
											  	<option value="" ></option>
											  
											 <%}else if(obj.getTrangthai().equals("4")){%>											
											 	<option value="4"  selected>Đã xuất HĐ</option>										 											  	
											  	<option value="0" >Chưa chốt</option>
											  	<option value="1" >Đã chốt</option>
											  	<option value="2" >Đã hủy</option>
											  	<option value="3" >Đã xuất kho</option>
											  	<option value="7">Đã in ĐH</option>
											  	<option value="" ></option>	  	
											 <%}else if(obj.getTrangthai().equals("7")){%>											
											 	<option value="4" >Đã xuất HĐ</option>										 											  	
											  	<option value="0" >Chưa chốt</option>
											  	<option value="1" >Đã chốt</option>
											  	<option value="2" >Đã hủy</option>
											  	<option value="3" >Đã xuất kho</option>
											  	<option value="7"  selected>Đã in ĐH</option>
											  	<option value="" ></option>	  	
											<%}else{%>
												<option value="" selected> </option>
												<option value="0" >Chưa chốt</option>
											  	<option value="1" >Đã chốt</option>											
											  	<option value="2" >Đã hủy</option>
											  	<option value="3" >Đã xuất kho</option>
											  	<option value="4">Đã xuất HĐ</option>
											  	<option value="7">Đã in ĐH</option>
											<%} %>
									          </select>
									</TD>
									<td class="plainlabel" colspan="4"></td>
									
							    </TR>	
								<TR>
									<TD class="plainlabel" ><%=Utility.GLanguage("Từ ngày",session,jedis) %></TD>
									<td class="plainlabel">
			                            <input type="text"  class="days" size="11"
			                                    id="tungay" name="tungay" value="<%= obj.getTungay() %>" maxlength="10" readonly />
			                    	</td>
			                    	  <TD class="plainlabel" ><%=Utility.GLanguage("Đến ngày",session,jedis) %></TD>
							      <td class="plainlabel">
			                            <input type="text"  class="days" size="11"
			                                    id="denngay" name="denngay" value="<%= obj.getDenngay() %>" maxlength="10" readonly />
			                    	</td>
			                    	<td class="plainlabel" colspan="4"></td>
								</TR>
							  	<TR>
									<TD class="plainlabel" >Số đơn hàng</TD>
									<TD class="plainlabel">
										<input type="text" name="sodonhang" size="11" value="<%= obj.getSohoadon() %>" onChange = "submitform();">
									</TD>	
											<TD class="plainlabel" >Mã / tên khách hàng</TD>
									<TD class="plainlabel">
										<input type="text" name="khachhang" size="11" value="<%= obj.getKhachhang() %>" onChange = "submitform();">
									</TD>
									<td class="plainlabel" colspan="4"></td>
								</TR>

								<TR>
								<td class="plainlabel" >
								</td>
								<TD class="plainlabel">
								</TD>
								<TD class="plainlabel" >Mã DMS</TD>
									<TD class="plainlabel">
										<input type="text" name="mafast" size="11" value="<%= obj.getMafast() %>" onChange = "submitform();">
									</TD>
									<td class="plainlabel" colspan="2"></td>
								</TR>
								<%if(obj.getIsSearch()){ %>
								<TR><TD class="plainlabel" colspan="6"></TD>
								<TR>
									<TD class="plainlabel" >Doanh số</TD>
									<td class="plainlabel"><input type="text" name="ds" size="6" value="<%= formatter.format(obj.getTongTruoc()) %>"></td>
									<TD class="plainlabel" >Chiết khấu</TD>
									<td class="plainlabel"><input type="text" name="ck" size="6" value="<%= formatter.format(obj.getTongCK()) %>"></td>
									<TD class="plainlabel" >Doanh thu</TD>
									<td class="plainlabel"><input type="text" name="dt" size="6" value="<%= formatter.format(obj.getTongSau()) %>"></td>
							
								</TR>
								<%} %>
								<TR>
									<TD class="plainlabel" colspan="6">
                                   <a class="button2" href="javascript:submitform()" >
    	                           <img style="top: -4px;" src="../images/Search30.png" alt=""><%=Utility.GLanguage("Tìm kiếm",session,jedis) %>  </a> &nbsp;&nbsp;&nbsp;                         
									<a class="button2" href="javascript:clearform()">
	                               <img style="top: -4px;" src="../images/button.png" alt=""><%=Utility.GLanguage("Nhập lại",session,jedis) %></a>&nbsp;&nbsp;&nbsp;&nbsp;	
				
							
									&nbsp;&nbsp;&nbsp;&nbsp;
							<a class="button2" href="javascript:xuatExcel()"> <img style="top: -4px;" src="../images/button.png" alt=""><%=Utility.GLanguage("Xuất Excel",session,jedis) %> </a>&nbsp;&nbsp;&nbsp;&nbsp;
									
									  <!--  <INPUT name="action" type="submit" value="Tim kiem">&nbsp;
                                       <INPUT name="reinitialiser" type="button" value="Nhap lai" onClick="clearform();">
                                       -->
                                       </TD>
								</TR>
							</TABLE>
				  </FIELDSET>
			   </TD>	
				</TR>
				<TR>
					<TD width="100%">
					<FIELDSET>
					<LEGEND class="legendtitle">&nbsp;Đơn hàng bán &nbsp;&nbsp;&nbsp; 

					<%if(quyen[Utility.THEM]!=0){ %>
						<a class="button3"  onclick="newform()">
	    					<img style="top: -4px;" src="../images/New.png" alt=""><%=Utility.GLanguage("Tạo mới",session,jedis) %> </a>
	    				<%} %>                            

					<!--<INPUT name="action" type="submit" value="Tao moi"> -->	
					</LEGEND>
					<TABLE class="" width="100%">
						<TR>
							<TD width="98%">
							<TABLE width="100%" border="0" cellspacing="1px" cellpadding="4px">
								
								<TR class="tbheader">
									<th width="5%" align="center">Mã ĐH</th>
									<th width="9%" align="center"> Mã Fast</th>
									<th width="18%" align="center">Khách hàng</th>
									<th width="8%" align="center"><%=Utility.GLanguage("Trạng thái",session,jedis) %></th>
									<th width="9%" align="center">Ngày đơn hàng</th>
									<!-- <th width="1%" align="center" style="display: none"><%=Utility.GLanguage("Ngày tạo",session,jedis) %></th>
									<th width="1%" align="center" style="display: none"><%=Utility.GLanguage("Người tạo",session,jedis) %></th> -->
									<th width="7%" align="center" ><%=Utility.GLanguage("Ngày sửa",session,jedis) %></th>
									<th width="7%" align="center" >Thời gian</th>
									<th width="7%" align="center" ><%=Utility.GLanguage("Người sửa",session,jedis) %> </th>
									<th width="7%" align="center"><%=Utility.GLanguage("NHÂN VIÊN BÁN HÀNG",session,jedis) %></th>
									<th width="9%" align="center">Tổng giá trị</th>
									<th width="10%" align="center"><%=Utility.GLanguage("Tác vụ",session,jedis) %></th>
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
												<TD align="center">	<%= dhlist.getString("maFast") %> </TD>       
												<TD align="left">
													<%= dhlist.getString("khTen") %>
												</TD>
												<TD align="center">
												<%
												double tonggiatri = dhlist.getDouble("tonggiatri");
												String trangthai = dhlist.getString("trangthai");
												String daxuathd= dhlist.getString("DAXUATHOADON");
												String tt_bosung = "";
												String dain_dh = dhlist.getString("DAINDH");												
																								
												if(dain_dh.equals("1")) tt_bosung = "  (Đã in ĐH)";
												
												boolean exitPXK = dhlist.getInt("exitPXK") > 0 ? true : false;
												if (trangthai.equals("0")){ %>
													<span> Chờ Duyệt</span>
												<%} else{ %>
													<span><i style="color:red">Đã Duyệt </i></span>
												<% } %>
												</TD>
												<TD align="center"><%= dhlist.getString("ngaynhap") %></TD>
												<TD align="center" ><%= dhlist.getString("ngaysua") %></TD>
												<TD align="center" ><%= dhlist.getString("ngaygio") %></TD>
												<TD align="left" ><%= dhlist.getString("nguoisua") %></TD>
												<TD align="center"><%= dhlist.getString("ddkdTen") %></TD>
												<TD align="right" ><%= formatter.format(tonggiatri) %></TD>
												<TD align="center">
												<TABLE border = 0 cellpadding="0" cellspacing="0" >
													<TR><TD>
													<%if(trangthai.equals("0")){ %>
														
														
															 <%if(quyen[Utility.CHOT]!=0){ %>
																	<A href="javascript:duyetform(<%= dhlist.getString("dhId") %>);" >
																		<img  src="../images/Chot.png" alt="Duyệt đơn hàng" width="20" height="20"  border='0' title="Duyệt đơn hàng"	 >
																	</A>&nbsp;
															 <%} %>
															 	
														
														
														<%if(quyen[Utility.XOA]!=0){ %>
															<A id='<%= dhlist.getString("dhId") %>' href="" rel="subcontent<%="xoadhid" + dhlist.getString("dhId") %>" >
															<img  src="../images/Delete20.png" alt="Xóa đơn hàng" width="20" height="20" longdesc="Xóa đơn hàng" border='0'	 >
															</A> 
														<%} %>
														
														<%if(quyen[Utility.XEM]!=0){ %>
															<A href = "../../DonhangUpdateSvl?userId=<%=userId%>&display=<%= dhlist.getString("dhId") %>"><img src="../images/Display20.png" alt="Hien thi" width="20" height="20" longdesc="Hien thi" border = 0></A>&nbsp;
														<%} %>
														
														<DIV id="subcontent<%="xoadhid" + dhlist.getString("dhId") %>" style="position:absolute; visibility: hidden; border: 9px solid #80CB9B;
																		                             background-color: #FFF; width: 400px; padding: 4px; z-index: 100000009; " >
										                    <table width="98%" align="center" cellspacing="1" >
										                        <tr  >
										                            <td align="center" style="font-size: 10pt">Lý do xóa</td>
										                        </tr>
										                        <tr >
										                            <td align="center" style="font-size: 10pt">
										                            	<input type="text" id="<%="lydoxoa" + dhlist.getString("dhId") %>" style="width: 100%" />
										                            </td>
										                        </tr>
										                        
										                    </table>
										        					                    
										                    <div align="right">
										                    
										                     	<a href="javascript:XacNhanXoa('<%= dhlist.getString("dhId") %>');" style="color: red; font-weight: bold;">Xác nhận xóa</a>
										                     
										                     	&nbsp;&nbsp;|&nbsp;&nbsp;
										                     	<a href="javascript:dropdowncontent.hidediv('subcontent<%="xoadhid" + dhlist.getString("dhId") %>')" style="font-weight: bold;" >Đóng lại</a>
										                    </div>
											            </DIV> 
											            <script type="text/javascript">
															dropdowncontent.init('<%= dhlist.getString("dhId") %>', "left-top", 300, "click");
														</script>
														
													<%}else { {  { %> 
														<%if(quyen[Utility.XEM]!=0){ %>
															<A href = "../../DonhangUpdateSvl?userId=<%=userId%>&display=<%= dhlist.getString("dhId") %>"><img src="../images/Display20.png" alt="Hien thi" width="20" height="20" longdesc="Hien thi" border = 0></A>
														<%} %>
													<% } } }%>
													</TD></TR>
												</TABLE>
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