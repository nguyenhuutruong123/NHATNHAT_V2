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

<% IPhanBoNvgn obj = (IPhanBoNvgn)s.getAttribute("obj"); 
ResultSet pbList = (ResultSet)obj.getPbList();
obj.setNextSplittings(); %>


<% String userId = (String) s.getAttribute("userId");  %>
<% Utility Util = new Utility(); %>
<%
	int[] quyen = new  int[6];
	quyen = Util.Getquyen("DonhangSvl","",userId);

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
 	document.forms['dhForm'].action.value= 'searchPB';
 	document.forms['dhForm'].submit();
 }
 function newform()
 {
	 	document.forms['dhForm'].action.value= 'new';
	 	document.forms['dhForm'].submit();
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
	document.forms['dhForm'].action.value= 'searchPB';
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
	 var r = confirm("B???n ch???c ch???n mu???n x??a ????n h??ng ( " + dhId + " ) ");
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
<input type="hidden" name="nppId" value="<%=obj.getNppId()%>" >
<input type="hidden" name="dhId" id="dhId"  >
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
							   <TD align="left" colspan="2" class="tbnavigation"> Qu???n l?? b??n h??ng > B??n h??ng OTC > Ph??n b??? ????n h??ng cho NVGN

							   <TD colspan="2" align="right" class="tbnavigation">Ch??o m???ng <%= obj.getNppTen() %>&nbsp;</tr>
						</TABLE>
					</TD>
		  </TR>
			<TR>
				<TD >
				<FIELDSET><LEGEND class="legendtitle">&nbsp;<%=Utility.GLanguage("Ti??u ch?? t??m ki???m",session,jedis) %></LEGEND>
							<TABLE width="100%" cellpadding="6" cellspacing="0">
								<TR>
									<TD class="plainlabel" style="width:200px">Ph??n b??? t??? ng??y</TD>
									<td class="plainlabel" colspan="5">
			                            <input type="text"  class="days" size="11"
			                                    id="tungay" name="tungay" value="<%= obj.getTungay() %>" maxlength="10" readonly />
			                    	</td>
			                    </TR>
			                    <TR>
			                    <TD class="plainlabel" style="width:200px">Ph??n b??? ?????n ng??y</TD>
							      <td class="plainlabel" colspan="5">
			                            <input type="text"  class="days" size="11"
			                                    id="denngay" name="denngay" value="<%= obj.getDenngay() %>" maxlength="10" readonly />
			                    	</td>
								</TR>
								<TR>
									<TD class="plainlabel" colspan="6">
                                   <a class="button2" href="javascript:submitform()" >
    	                           <img style="top: -4px;" src="../images/Search30.png" alt=""><%=Utility.GLanguage("T??m ki???m",session,jedis) %>  </a> &nbsp;&nbsp;&nbsp;                         
									<a class="button2" href="javascript:clearform()">
	                               <img style="top: -4px;" src="../images/button.png" alt=""><%=Utility.GLanguage("Nh???p l???i",session,jedis) %></a>&nbsp;&nbsp;&nbsp;&nbsp;	
									</TD>
								</TR>
							</TABLE>
				  </FIELDSET>
			   </TD>	
				</TR>
				<TR>
					<TD width="100%">
					<FIELDSET>
					<LEGEND class="legendtitle">&nbsp;Ph??n b??? ch??? &nbsp;&nbsp;&nbsp; 

					<%if(quyen[Utility.THEM]!=0){ %>
						<a class="button3"  onclick="newform()">
	    					<img style="top: -8px; " src="../images/New.png" alt=""><%=Utility.GLanguage("T???o m???i",session,jedis) %></a>
	    				<%} %>                            

					<!--<INPUT name="action" type="submit" value="Tao moi"> -->	
					</LEGEND>
					<TABLE class="" width="100%">
						<TR>
							<TD width="98%">
							<TABLE width="100%" border="0" cellspacing="1px" cellpadding="4px">
								
								<TR class="tbheader">
									<th width="6%" align="center">M?? ph??n b???</th>
									<th width="9%" align="center">Ng??y ph??n b???</th>
									<th width="7%" align="center"><%=Utility.GLanguage("Ng??y t???o",session,jedis) %></th>
									<th width="7%" align="center"><%=Utility.GLanguage("Ng??y s???a",session,jedis) %></th>
									<th width="18%" align="center"><%=Utility.GLanguage("Ng?????i t???o",session,jedis) %></th>
									<th width="18%" align="center"><%=Utility.GLanguage("Ng?????i s???a",session,jedis) %></th>
									<th width="8%" align="center"><%=Utility.GLanguage("Tr???ng th??i",session,jedis) %></th>
									<th width="10%" align="center"> T??c v???</th>
									<!-- <th width="1%" align="center" style="display: none"><%=Utility.GLanguage("Ng??y t???o",session,jedis) %></th>
									<th width="1%" align="center" style="display: none"><%=Utility.GLanguage("Ng?????i t???o",session,jedis) %></th>
									<th width="7%" align="center" ><%=Utility.GLanguage("Ng??y s???a",session,jedis) %></th>
									<th width="7%" align="center" >Th???i gian</th>
									<th width="7%" align="center" ><%=Utility.GLanguage("Ng?????i s???a",session,jedis) %> </th>
									<th width="7%" align="center"><%=Utility.GLanguage("NH??N VI??N B??N H??NG",session,jedis) %></th> -->
									<!-- <th width="9%" align="center">T???ng gi?? tr???</th>  -->
								</TR>
								
								<% 
								if(pbList != null)
								{
									int m = 0;
									String lightrow = "tblightrow";
									String darkrow = "tbdarkrow";
									while (pbList.next()){										
										if (m % 2 != 0) {%>	
														
											<TR class= <%=lightrow%>  ">
									<%} else {%>
											
											<TR class= <%= darkrow%>   > 
										<%}%>
											 	<TD align="center" ><input name="pk_seq" type="hidden"  value="<%= pbList.getString("pk_seq") %>"  />
												<%= pbList.getString("pk_seq") %>
												</TD> 
												<TD align="center"><%= pbList.getString("ngayphanbo") %></TD>
												<TD align="center"><%= pbList.getString("ngaytao") %></TD>
												<TD align="center">	<%= pbList.getString("ngaysua") %> </TD>    
												<TD align="left"><%= pbList.getString("nguoitao") %></TD>
												<TD align="left"><%= pbList.getString("nguoisua") %></TD>
												<% String trangthai = ""; 
												if (pbList.getString("trangthai").equals("0"))
													trangthai = "Ch??a ch???t";
												else
													trangthai = "???? ch???t";
												%>
												<TD align="left"><%= trangthai %></TD>
											<TD align="center">
												<TABLE border = 0 cellpadding="0" cellspacing="0" >
													<TR><TD>
													
														<%if(quyen[Utility.XEM]!=0){ %>
															<A href = "../../PhanBoNvgnSvl?userId=<%=userId%>&display=<%= pbList.getString("pk_seq") %>"><img src="../images/Display20.png" alt="Hien thi" width="20" height="20" longdesc="Hien thi" border = 0></A>&nbsp;
														<%} %>
														
														<%if(quyen[Utility.SUA]!=0 && pbList.getInt("trangthai") != 1){ %>
															<A href = "../../PhanBoNvgnSvl?userId=<%=userId%>&update=<%= pbList.getString("pk_seq") %>"><img src="../images/Edit20.png" alt="C???p nh???t" width="20" height="20" longdesc="C???p nh???t" border = 0></A>&nbsp;
														<%} %>
														
														<%if(quyen[Utility.CHOT]!=0 && pbList.getInt("trangthai") != 1){ %>
															<A href = "../../PhanBoNvgnSvl?userId=<%=userId%>&chot=<%= pbList.getString("pk_seq") %>"><img src="../images/Chot.png" alt="C???p nh???t" width="20" height="20" longdesc="C???p nh???t" border = 0></A>&nbsp;
														<%} %>
														
														<%if(quyen[Utility.XOA]!=0 && pbList.getInt("trangthai") != 1){ %>
															<A href = "../../PhanBoNvgnSvl?userId=<%=userId%>&delete=<%=pbList.getString("pk_seq")%>"><img src="../images/Delete20.png" alt="Xoa" width="20" height="20" longdesc="Xoa" border=0 onclick="if(!confirm('B???n c?? mu???n xo?? ph??n b??? n??y?')) return false;"></A>&nbsp;
														<%} %>	
														</TD>

	
													</TD></TR>
												</TABLE>
												</TD>
									<% m++; } pbList.close(); }%>	
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
		if(pbList!=null){
			pbList.close();
		}
		Util=null;
		s.setAttribute("obj",null);
		
	}catch(Exception e){e.printStackTrace();}
%>