<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page  import = "java.util.Iterator" %>
<%@ page  import = "java.util.List" %>
<%@ page  import = "java.util.ArrayList" %>
<%@page import="geso.dms.center.util.Utility"%>
<%@ page  import = "geso.dms.center.beans.bundle.*" %>
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

<% IRaBundleList obj = (IRaBundleList)s.getAttribute("obj"); %>
<% ResultSet dhlist = (ResultSet)obj.getDonhangRs(); %>

<% String userId = (String) s.getAttribute("userId");  
String userTen = (String) session.getAttribute("userTen");  
%>
<% Utility util = new Utility(); %>
<%

util.getIdNhapp(userId);

	int[] quyen = new  int[6];
	quyen = util.Getquyen("RaBundleSvl","",userId);


%>
<% obj.setNextSplittings(); %>
<% ResultSet nppRs = (ResultSet)obj.getNppRs(); %>

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
	
	
<link href="../css/select2.css" rel="stylesheet" />
<script src="../scripts/select2.js"></script>
<script type="text/javascript">
	$(document).ready(function() {		
			$(".select2").select2();
        }); 		
		
</script>

	
	
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
	 
	 document.forms['dhForm'].tuthoigian.value= '';
	 document.forms['dhForm'].denthoigian.value= '';
	document.forms['dhForm'].tungay.value= '';
	document.forms['dhForm'].denngay.value= '';
	document.forms['dhForm'].trangthai.value ='';
	document.forms['dhForm'].khachhang.value ='';
	document.forms['dhForm'].sodonhang.value ='';
	document.forms['dhForm'].mafast.value ='';
	document.forms['dhForm'].ddkdTen.selectedIndex = 0;
	document.forms['dhForm'].submit();
	//submitform();
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
	 var r = confirm("B???n ch???c ch???n mu???n x??a ????n h??ng ( " + dhId + " ) ");
	 if (r == false) {
	     return;
	 }
	 document.forms['dhForm'].dhId.value = dhId;
	 document.forms['dhForm'].action.value = 'delete';
	 document.forms['dhForm'].submit();
 }
 
 function duyetform(Id)
 {
		if(!confirm('B???n c?? mu???n duy???t ????n h??ng n??y?')) 
		{
			return false ;
		}
		document.forms['dhForm'].dhId.value = Id;
    	 document.forms['dhForm'].action.value= 'duyet';
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
<form name="dhForm" method="post" action="../../RaBundleSvl">
<input type="hidden" name="userId" value="<%= userId %>" >
<input type="hidden" name="dhId" id="dhId"  >
<input type="hidden" name="action" value="1">
<input type="hidden" name="lydoxoa" value="">

<input type="hidden"name="crrApprSplitting" value="<%= obj.getCrrApprSplitting() %>">
		<input type="hidden" name="nxtApprSplitting" value="<%= obj.getNxtApprSplitting() %>">
		<% obj.setNextSplittings(); %>


<input type="hidden" name="msg" id="msg" value='<%= obj.getMsg() %>'>
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
							   <TD align="left" colspan="2" class="tbnavigation"> Qu???n l?? b??n h??ng > B??n h??ng Nh?? thu???c > B??n h??ng cho kh??ch h??ng

							   <TD colspan="2" align="right" class="tbnavigation">Ch??o m???ng  <%= userTen %>&nbsp;</tr>
						</TABLE>
					</TD>
		  </TR>
			<TR>
				<TD >
				<FIELDSET><LEGEND class="legendtitle">&nbsp;<%=Utility.GLanguage("Ti??u ch?? t??m ki???m",session,jedis) %></LEGEND>
							<TABLE width="100%" cellpadding="6" cellspacing="0">
								
								
								<TR>
											<TD  class="plainlabel">Thu???c nh?? ph??n ph???i</TD>
											<TD  class="plainlabel"  colspan="5" >
												<SELECT name="nppId" onchange="submitform();" class = "select2" style = "width:200px"s>
													<option value=""></option>
													<%  if(nppRs != null)
														{
															try
															{
															while(nppRs.next())
															{ 
										    					if(nppRs.getString("pk_seq").equals(obj.getNppId()))
										    					{ %>
																	<option value='<%= nppRs.getString("pk_seq") %>' selected><%=nppRs.getString("ten") %></option>
																<%}else{ %>
																	<option value='<%= nppRs.getString("pk_seq") %>'><%= nppRs.getString("ten") %></option>
																<%}} nppRs.close(); 
															}catch(Exception e){e.printStackTrace();} 
														}	%>	
												</SELECT>
											</TD>
									</TR>
								
								
								
								
								<TR>
									
                                    <TD width="130px" class="plainlabel"><%=Utility.GLanguage("Tr???ng th??i",session,jedis) %> </TD>
									<TD class="plainlabel">
										<select name="trangthai" onChange="submitform();" class = "select2" style = "width:200px">
											<% if (obj.getTrangthai().equals("1")){%>
											  	<option value="1" selected>???? ch???t</option>
											  	<option value="0">Ch??a ch???t</option>
											  	<option value="2">???? h???y</option>
											  	<option value="3">???? xu???t kho</option>
											  	<option value="4">???? xu???t H??</option>
											  	<option value="7">???? in ??H</option>
											  	<option value=""></option>
											  
											<%}else if(obj.getTrangthai().equals("0")) {%>
											 	<option value="0" selected>Ch??a ch???t</option>
											  	<option value="1" >???? ch???t</option>
											 	<option value="2" >???? h???y</option>
											 	<option value="3" >???? xu???t kho</option>
											 	<option value="4">???? xu???t H??</option>
											 	<option value="7">???? in ??H</option>
											  	<option value="" ></option>
											  	
											<%}else if(obj.getTrangthai().equals("2")){%>											
											 	<option value="2" selected>???? h???y</option>										  	
											  	<option value="0" >Ch??a ch???t</option>
											  	<option value="1" >???? ch???t</option>
											  	<option value="3" >???? xu???t kho</option>
											  	<option value="4">???? xu???t H??</option>
											  	<option value="7">???? in ??H</option>
											  	<option value="" ></option>
											  	
											<%}else if(obj.getTrangthai().equals("3")){%>											
											 	<option value="3" selected>???? xu???t kho</option>										  	
											  	<option value="0" >Ch??a ch???t</option>
											  	<option value="1" >???? ch???t</option>
											  	<option value="2" >???? h???y</option>
											  	<option value="4">???? xu???t H??</option>
											  	<option value="7">???? in ??H</option>
											  	<option value="" ></option>
											  
											 <%}else if(obj.getTrangthai().equals("4")){%>											
											 	<option value="4"  selected>???? xu???t H??</option>										 											  	
											  	<option value="0" >Ch??a ch???t</option>
											  	<option value="1" >???? ch???t</option>
											  	<option value="2" >???? h???y</option>
											  	<option value="3" >???? xu???t kho</option>
											  	<option value="7">???? in ??H</option>
											  	<option value="" ></option>	  	
											 <%}else if(obj.getTrangthai().equals("7")){%>											
											 	<option value="4" >???? xu???t H??</option>										 											  	
											  	<option value="0" >Ch??a ch???t</option>
											  	<option value="1" >???? ch???t</option>
											  	<option value="2" >???? h???y</option>
											  	<option value="3" >???? xu???t kho</option>
											  	<option value="7"  selected>???? in ??H</option>
											  	<option value="" ></option>	  	
											<%}else{%>
												<option value="" selected> </option>
												<option value="0" >Ch??a ch???t</option>
											  	<option value="1" >???? ch???t</option>											
											  	<option value="2" >???? h???y</option>
											  	<option value="3" >???? xu???t kho</option>
											  	<option value="4">???? xu???t H??</option>
											  	<option value="7">???? in ??H</option>
											<%} %>
									          </select>
									</TD>
									<td class="plainlabel" colspan="4"></td>
									
							    </TR>	
								<TR>
									<TD class="plainlabel" ><%=Utility.GLanguage("T??? ng??y",session,jedis) %></TD>
									<td class="plainlabel">
			                            <input type="text"  class="days" size="11"
			                                    id="tungay" name="tungay" value="<%= obj.getTungay() %>" maxlength="10" readonly />
			                    	</td>
			                    	  <TD class="plainlabel" ><%=Utility.GLanguage("?????n ng??y",session,jedis) %></TD>
							      <td class="plainlabel">
			                            <input type="text"  class="days" size="11"
			                                    id="denngay" name="denngay" value="<%= obj.getDenngay() %>" maxlength="10" readonly />
			                    	</td>
			                    	<td class="plainlabel" colspan="4"></td>
								</TR>
							  	
								<TR>

								<TD class="plainlabel" >M?? ch???ng t???</TD>
									<TD class="plainlabel" colspan="4">
										<input type="text" name="mafast" size="11" value="<%= obj.getMafast() %>" onChange = "submitform();">
									</TD>

								</TR>
			
								<TR>
									<TD class="plainlabel" colspan="6">
		                                   <a class="button2" href="javascript:submitform()" >
		    	                           <img style="top: -4px;" src="../images/Search30.png" alt=""><%=Utility.GLanguage("T??m ki???m",session,jedis) %>  </a> &nbsp;&nbsp;&nbsp;                         
											<a class="button2" href="javascript:clearform()">
			                               <img style="top: -4px;" src="../images/button.png" alt=""><%=Utility.GLanguage("Nh???p l???i",session,jedis) %></a>&nbsp;&nbsp;&nbsp;&nbsp;	
						
									
											&nbsp;&nbsp;&nbsp;&nbsp;
									<a class="button2" href="javascript:xuatExcel()"> <img style="top: -4px;" src="../images/button.png" alt=""><%=Utility.GLanguage("Xu???t Excel",session,jedis) %> </a>&nbsp;&nbsp;&nbsp;&nbsp;
											
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
					<LEGEND class="legendtitle">&nbsp;????n h??ng b??n &nbsp;&nbsp;&nbsp; 
						<%if(quyen[Utility.THEM]!=0){ %>
						<a class="button3"  onclick="newform()">
	    					<img style="top: -4px;" src="../images/New.png" alt=""><%=Utility.GLanguage("T???o m???i",session,jedis) %> </a>
	    				<%} %>   
					<!--<INPUT name="action" type="submit" value="Tao moi"> -->	
					</LEGEND>
					<TABLE class="" width="100%">
						<TR>
							<TD width="98%">
							<TABLE width="100%" border="0" cellspacing="1px" cellpadding="4px">
								
								<TR class="tbheader">
									<th width="5%" align="center">M?? ch???ng t???</th>
									<th width="8%" align="center"><%=Utility.GLanguage("Tr???ng th??i",session,jedis) %></th>
									<th width="9%" align="center">Ng??y ch???ng t???</th>
									<th width="9%" align="center" ><%=Utility.GLanguage("Ng?????i t???o",session,jedis) %></th> 
									<th width="7%" align="center" ><%=Utility.GLanguage("Ng??y s???a",session,jedis) %></th>
									<th width="9%" align="center" ><%=Utility.GLanguage("Ng?????i s???a",session,jedis) %> </th>
									<th width="10%" align="center">G??i s???n ph???m</th>
									<th width="9%" align="center">S??? l?????ng</th>
									<th width="10%" align="center"><%=Utility.GLanguage("T??c v???",session,jedis) %></th>
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
												  
											 	<TD align="center" ><input name="dhIdd" type="hidden"  value="<%= dhlist.getString("pk_seq") %>"  />
												<%= dhlist.getString("pk_seq") %>
												</TD> 
												
												<TD align="center">
												<%

												String trangthai = dhlist.getString("trangthai");										
												if (trangthai.equals("0")){ %>
													<span> Ch??a ch???t</span>
												<%}else if(trangthai.equals("1")){ %>
													<span><b> ???? ch???t </b></span>
												<%}else if(trangthai.equals("2")){ %>
													<span><u> ???? h???y </u></span>
												<% } %>
												</TD>
												<TD align="center"><%= dhlist.getString("ngaychungtu") %></TD>
												
												<TD align="left" ><%= dhlist.getString("nguoitao") %></TD>
												<TD align="center" ><%= dhlist.getString("ngaysua") %></TD>
												<TD align="left" ><%= dhlist.getString("nguoisua") %></TD>
												<TD align="center"><%= dhlist.getString("spTen") %></TD>
												<TD align="right" ><%= formatter.format(dhlist.getDouble("soluong") ) %></TD>
												<TD align="center">
												<TABLE border = 0 cellpadding="0" cellspacing="0" >
													<TR><TD>
													<%if(trangthai.equals("0")){ %>
														
														<%if(quyen[Utility.SUA]!=0){ %>
															<A href = "../../RaBundleUpdateSvl?userId=<%=userId%>&update=<%= dhlist.getString("pk_seq") %>"><img src="../images/Edit20.png" alt="Cap nhat" width="20" height="20" title="C???p nh???t ????n h??ng" longdesc="Cap nhat" border = 0></A>&nbsp;
														<%} %>
															 <%if(quyen[Utility.CHOT]!=0){ %>
																	<A href="javascript:duyetform(<%= dhlist.getString("pk_seq") %>);" >
																		<img  src="../images/Chot.png" alt="Duy???t ????n h??ng" width="20" height="20"  border='0' title="Duy???t ????n h??ng"	 >
																	</A>&nbsp;
															 <%} %>	
													
														
														<%if(quyen[Utility.XOA]!=0){ %>
															<A  href="javascript:XacNhanXoa(<%= dhlist.getString("pk_seq") %>);" >
															<img  src="../images/Delete20.png" alt="X??a ????n h??ng" width="20" height="20" title="X??a ????n h??ng" longdesc="X??a ????n h??ng" border='0'	 >
															</A> 
														<%} %>
														
														
													<%} else{ if(!trangthai.equals("2")) { %>
														
														<%if(quyen[Utility.XEM]!=0){ %>
															<A href = "../../RaBundleUpdateSvl?userId=<%=userId%>&display=<%= dhlist.getString("pk_seq") %>"><img src="../images/Display20.png" alt="Hien thi" width="20" height="20" title="Xem ????n h??ng" longdesc="Hien thi" border = 0></A>&nbsp;
														<%} %>
														<%if(quyen[Utility.THEM]!=0){ %>
															<A id='<%="copypk_seq" + dhlist.getString("pk_seq") %>' href="">
																<img  src="../images/copy20.png" alt="Copy ????n h??ng" width="20" height="20"  border=0	onclick="if(!confirm('B???n c?? mu???n copy ????n h??ng ( <%= dhlist.getString("pk_seq") %> ) n??y?')) {return false ;}else{ processing('<%="copypk_seq" + dhlist.getString("pk_seq") %>' , '../../RaBundleUpdateSvl?userId=<%=userId%>&copy=<%= dhlist.getString("pk_seq") %>&nppId=<%= obj.getNppId() %>');}" >
															</A>
														<%} %>	 
													<% } else { %> 
														<%if(quyen[Utility.XEM]!=0){ %>
															<A href = "../../RaBundleUpdateSvl?userId=<%=userId%>&display=<%= dhlist.getString("pk_seq") %>"><img src="../images/Display20.png" alt="Hien thi" width="20" height="20" longdesc="Hien thi" border = 0></A>
														<%} %>
													<% } } %>
													
										</TD></TR>
												</TABLE>
												</TD>
											</TR>
									
									<%m++;}%>
													
									<% dhlist.close();  } %>	
									
									
									
									
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
		if(obj != null){
			obj.DBclose();
			obj = null;
		}
		if(dhlist!=null){
			dhlist.close();
		}
		util=null;
		s.setAttribute("obj",null);
		
	}catch(Exception e){e.printStackTrace();}
%>