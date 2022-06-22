<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page  import = "geso.dms.distributor.beans.denghidathang.IDenghidathangList" %>
<%@ page  import = "java.sql.ResultSet" %>
<%@ page  import = "java.text.DecimalFormat" %>
<%@ page  import = "java.text.NumberFormat" %>
<%@ page  import = "geso.dms.center.util.*" %>
<%	String userId = (String) session.getAttribute("userId");  
	String userTen = (String) session.getAttribute("userTen");  	
	String sum = (String) session.getAttribute("sum");
	Utility util = (Utility) session.getAttribute("util");
	if(!util.check(userId, userTen, sum)){
		response.sendRedirect(request.getContextPath() + "/redirect.jsp");
	}else{ %>

<% IDenghidathangList obj = (IDenghidathangList)session.getAttribute("obj"); %>
<% ResultSet dndhlist = (ResultSet)obj.getDndhList(); %>
<% obj.setNextSplittings(); %>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"> <% Utility Utilback = new Utility(); %>
<HTML>
<HEAD>
<TITLE>Acecook - Project</TITLE>
<META http-equiv="Content-Type" content="text/html; charset=utf-8">

<META http-equiv="Content-Style-Type" content="text/css">
<LINK rel="stylesheet" href="../css/main.css" type="text/css">
<LINK rel="stylesheet" type="text/css" media="print" href="../css/impression.css">
<script type="text/javascript" language="JavaScript" src="../scripts/Numberformat.js"></script>

<link rel="stylesheet" type="text/css" href="../css/autocomplete.css" />
<script type="text/javascript" src="../scripts/jquery-1.4.2.min.js"></script>
<script src="../scripts/jquery.autocomplete.js"></script>

<script type="text/javascript" src="../scripts/jquery.js"></script>
<LINK rel="stylesheet" href="../css/calendar.css" type="text/css">
<link type="text/css" rel="stylesheet" href="../css/mybutton.css">
<script type="text/javascript" language="JavaScript" src="../scripts/simplecalendar.js"></script>
<script type="text/javascript" src="../scripts/phanTrang.js"></script>
<script type="text/javascript" src="../scripts/jquery.min.1.7.js"></script>
<link href="../css/jquery-ui.css" rel="stylesheet" type="text/css" />
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
        }); 		
		
</script>

<SCRIPT language="javascript" type="text/javascript">

function submitForm(arg){
	
	document.forms["dndhForm"].action.value = arg;
	document.forms["dndhForm"].submit();
	
}

function submitform()
{   
   document.forms["dndhForm"].submit();
}

function clearform()
{
	document.forms["dndhForm"].reset();
    document.forms["dndhForm"].elements["sku"] = "";
    document.forms["dndhForm"].elements["tunggay"] = "";
    document.forms["dndhForm"].elements["denngay"] = "";
    document.forms["dndhForm"].elements["trangthai"] = "";
}




</SCRIPT>

</HEAD>
<BODY leftmargin="0" bottommargin="0" topmargin="0" rightmargin="0" >
<form id="dndhForm" name="dndhForm" method="post" action="../../DenghidathangSvl">
<input type="hidden" name="userId" value="<%=userId%>">

<input type="hidden" name="action" value="1" >
<input type="hidden" name="crrApprSplitting" value="<%= obj.getCrrApprSplitting() %>" >
<input type="hidden" name="nxtApprSplitting" value="<%= obj.getNxtApprSplitting() %>" >

<TABLE width="100%" border="0" cellspacing="0" cellpadding="0"
    height="100%"  bgcolor="#FFFFFF">
    <TR>
        <TD colspan="4" align="left" valign="top"> 
        
        <TABLE width="100%" cellspacing="0" cellpadding="0">
        	<TR>
            	<TD>
                	<TABLE width="100%" cellspacing="1" cellpadding="0">
                    	<TR>
                        	<TD align="left" >
                            	<table width="100%" border="0" cellpadding="0" cellspacing="0">
                                	<tr height="22">
                                    	<TD align="left" colspan="2" class="tbnavigation">&nbsp;Quản lý tồn kho &gt; &nbsp;Mua hàng từ NCC &gt; Đề nghị đặt hàng
                                   
                                        <TD colspan="2" align="right" class="tbnavigation">Chào mừng  <%= obj.getNppTen() %> &nbsp;</TD>
                                    </tr>
                                  </table>
                              </TD>
                         </TR> 
                     </TABLE>
                    
                     <TABLE border="0" width="100%" cellspacing = 0 cellpadding = 0>

                        <TR>
                            <TD width="100%" align="left">
                                <FIELDSET>
                                <LEGEND class="legendtitle">&nbsp;Tiêu chí hiển thị &nbsp;</LEGEND>

                                <TABLE  width="100%" cellpadding="4" cellspacing="0">
                                    <TR>
                                        <TD width="19%" class="plainlabel">Chứa SKU </TD>
                                      	<TD width="81%" class="plainlabel">
                                      		
                                        	<input type="text" id="sku" name="sku" value="<%= obj.getSKU() %>" size="40"/>
                                      		
                                      	</TD>
                                    </TR>
<TR>
	<TD class="plainlabel" ><%=Utility.GLanguage("Từ ngày",session,jedis) %></TD>
	<td class="plainlabel">
		<input type="text"  class="days" size="11"
				id="tungay" name="tungay" value="<%= obj.getTungay() %>" maxlength="10" readonly />
	</td>
</TR>
<TR>
  <TD class="plainlabel" >Ðến ngày</TD>
  <td class="plainlabel">
		<input type="text"  class="days" size="11"
				id="denngay" name="denngay" value="<%= obj.getDenngay() %>" maxlength="10" readonly />
	</td>
</TR>
								<TR>
									<TD class="plainlabel"><%=Utility.GLanguage("Trạng thái",session,jedis) %></TD>
									<TD class="plainlabel"><select name="trangthai" >
								  	<% if (obj.getTrangthai().equals("1")){ %>
								    	<option value="0"></option>
								    	<option value="1" selected>Chưa đặt hàng</option>
								    	<option value="2">Đã đặt hàng</option>
								    	
									<%}else{ 							
								  		if (obj.getTrangthai().equals("2")){ %>
								    	<option value="0"></option>
								    	<option value="1">Chưa đặt hàng</option>
								    	<option value="2" selected>Đã đặt hàng</option>
								    	
									<%}else{%>
								    	<option value="0"></option>
								    	<option value="1">Chưa đặt hàng</option>
								    	<option value="2">Đã đặt hàng</option>
									<%}} %>									
									
								    	</select></TD>
								</TR>
								
								

								

                               	<TR>
                               	
                               	
                               			<TD class="plainlabel" align="left" colspan="2">
                                               <a class="button1" href="javascript:submitForm('Hien thi');">
										<img style="top: -4px;" src="../images/button.png" alt="">Hiển thị</a>&nbsp;&nbsp;&nbsp;&nbsp;
										                                  <a class="button2" href="javascript:clearform();">
										<img style="top: -4px;" src="../images/button.png" alt=""><%=Utility.GLanguage("Nhập lại",session,jedis) %></a>&nbsp;&nbsp;&nbsp;&nbsp;
                               
                                        </TD>
                               	

                                </TR>
                                			
									
								</TABLE>
                              </FIELDSET>
                          	</TD>			
                          </TR>
                      </TABLE>
                      <TABLE width="100%" cellspacing = 0 cellpadding=0>
                        <TR>
                            <TD >
                                <FIELDSET>
                                <LEGEND class="legendtitle">&nbsp;Đề nghị đặt hàng&nbsp;&nbsp;
                                
                                <a class="button3" href="javascript:submitForm('Tao moi')">
    	<img style="top: -4px;" src="../images/New.png" alt=""><%=Utility.GLanguage("Tạo mới",session,jedis) %> </a>
    	
                                </LEGEND>
    
                      	        <TABLE width="100%">
                                <TR>
                                    <TD>
                                        <TABLE width="100%" border="0" cellspacing="1" cellpadding="4">
                                        <TR class="tbheader">
                                            <TH >Ngày đặt hàng </TH>
                                            <TH >Số chứng từ </TH>
                                            <TH >Đơn vị kinh doanh </TH>
                                          
											<TH ><%=Utility.GLanguage("Trạng thái",session,jedis) %> </TH>
                                           
                                            <TH align="center">&nbsp;Tác vụ </TH>
                                        </TR>
         
                               <% 
                            NumberFormat formatter = new DecimalFormat("#,###,###");
                            int m = 0;
                            String lightrow = "tblightrow";
                            String darkrow = "tbdarkrow";
							if(dndhlist != null){
								try{								
                                    while (dndhlist.next()){
                                        	
                                       	if (m % 2 != 0) {%>                     
                                        	<TR class= <%=lightrow%> >
                                        <%} else {%>
                                           	<TR class= <%= darkrow%> >
                                        <%}%>
                                                <TD align="left"><div align="left"><%= dndhlist.getString("ngaydat") %></div></TD>                                   
                                                <TD><div align="center"><%= dndhlist.getString("chungtu") %></div></TD>
                                                <TD><div align="center"><%= dndhlist.getString("donvikinhdoanh") %></div></TD>
                                               
                                                <% String style=""; 
                                                if (dndhlist.getString("trangthai").equals("1")){ %>
                                                   		<TD><div align="center">Chưa đặt hàng</div></TD>
                                                <%}else { 
                                                   if (dndhlist.getString("trangthai").equals("2")) {
                                                   style="display:none;";
                                                   %>
                                                   		<TD><div align="center">Đã đặt hàng</div></TD>
                                                  <%}}%>
                                        	       
													
                                                	<TD align="center">
                            	                    <TABLE border = 0 cellpadding="0" cellspacing="0">
                                	                    <TR><TD>
                               	                    		<A href = "../../DenghidathangDisplaySvl?userId=<%=userId%>&display=<%=dndhlist.getString("chungtu") %>"><img src="../images/Display20.png" alt="Hiển thị" width="20" height="20" longdesc="Hiển thị" border = 0 "></A>
                                        	            </TD>
                                        	                                                    	       
<!--                                         	            <TD>&nbsp;&nbsp;</TD> -->
                                        	            
														<TD style="<%=style%>">
				         	                    			<A href = "../../DondathangUpdateSvl?userId=<%=userId%>&dathang=<%=dndhlist.getString("chungtu") %>"><img src="../images/convert.gif" alt="Đặt hàng" width="20" height="20" longdesc="Đặt hàng" border = 0 "></A>
                                        	            </TD>
<!--                                         	            <TD>&nbsp;&nbsp;</TD> -->
														<TD>
				         	                    			<A href = "../../DenghidathangSvl?userId=<%=userId%>&delete=<%=dndhlist.getString("chungtu") %>"><img src="../images/Delete20.png"  onclick="if(!confirm('Bạn muốn xóa đề nghị này?')) return false;" alt="Xóa" width="20" height="20" longdesc="Xóa" border = 0 "></A>
                                        	            </TD>                                        	            
                                        	                                                    	                                   	            
                                        	            </TR>
														                                                   
                                            		 </TABLE>
                                                </TD>
                                            </TR>
                                        <% m++; } 
                                        }catch(java.sql.SQLException e){}
                               		}%>


<tr class="tbfooter" > 
											 <TD align="center" valign="middle" colspan="13" class="tbfooter">
											 	<%if(obj.getNxtApprSplitting() >1) {%>
													<img alt="Trang Dau" src="../images/first.gif" style="cursor: pointer;" onclick="View('dndhForm', 1, 'view')"> &nbsp;
												<%}else {%>
													<img alt="Trang Dau" src="../images/first.gif" > &nbsp;
													<%} %>
												<% if(obj.getNxtApprSplitting() > 1){ %>
													<img alt="Trang Truoc" src="../images/prev.gif" style="cursor: pointer;" onclick="View('dndhForm', eval(dndhForm.nxtApprSplitting.value) -1, 'view')"> &nbsp;
												<%}else{ %>
													<img alt="Trang Truoc" src="../images/prev_d.gif" > &nbsp;
												<%} %>
												
												<%
													int[] listPage = obj.getNextSplittings();
													for(int i = 0; i < listPage.length; i++){
												%>
												
												<% 
												
												System.out.println("Current page:" + obj.getNxtApprSplitting());
												System.out.println("List page:" + listPage[i]);
												
												if(listPage[i] == obj.getNxtApprSplitting()){ %>
												
													<a  style="color:white;"><%= listPage[i] %>/ <%=obj.getTheLastSplitting() %></a>
												<%}else{ %>
													<a href="javascript:View('dndhForm', <%= listPage[i] %>, 'view')"><%= listPage[i] %></a>
												<%} %>
													<input type="hidden" name="list" value="<%= listPage[i] %>" />  &nbsp;
												<%} %>
												
												<% if(obj.getNxtApprSplitting() < obj.getTheLastSplitting()){ %>
													&nbsp; <img alt="Trang Tiep" src="../images/next.gif" style="cursor: pointer;" onclick="View('dndhForm', eval(dndhForm.nxtApprSplitting.value) +1, 'view')"> &nbsp;
												<%}else{ %>
													&nbsp; <img alt="Trang Tiep" src="../images/next_d.gif" > &nbsp;
												<%} %>
												<%if(obj.getNxtApprSplitting() == obj.getTheLastSplitting()) {%>
											   		<img alt="Trang Cuoi" src="../images/last.gif" > &nbsp;
										   		<%}else{ %>
										   			<img alt="Trang Cuoi" src="../images/last.gif" style="cursor: pointer;" onclick="View('dndhForm', -1, 'view')"> &nbsp;
										   		<%} %>
											</TD>
										 </tr>  


                            	</TABLE>                              
                          </TD>
                        </TR>
                      </TABLE>
                       </FIELDSET>
                </TD>
              </TR>
            </TABLE>
          </TD>
         </TR>
        </TABLE>
       </TD>
      </TR>
 </TABLE>
</form>

</BODY>
</HTML>

<% if(!(dndhlist == null)) dndhlist.close();%>
<%  if(obj != null){
	obj.DBclose();
	obj = null;
}%>
<%}%>