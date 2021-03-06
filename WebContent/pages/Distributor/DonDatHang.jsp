<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page  import = "geso.dms.distributor.beans.dondathang.IDondathangList" %>
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

<% IDondathangList obj = (IDondathangList)session.getAttribute("obj"); %>
<% ResultSet dhlist = (ResultSet)obj.getDhList();%>
<% obj.setNextSplittings(); %>
 
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"> <% Utility Utilback = new Utility(); %>
<HTML>
<HEAD>
<TITLE>Acecook - Project</TITLE>
<META http-equiv="Content-Type" content="text/html; charset=utf-8">

<META http-equiv="Content-Style-Type" content="text/css">
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
<LINK rel="stylesheet" type="text/css" media="print" href="../css/impression.css">
<LINK rel="stylesheet" href="../css/main.css" type="text/css">
<LINK rel="stylesheet" href="../css/calendar.css" type="text/css">
<script type="text/javascript" src="../scripts/jquery.js"></script> 
<link rel="stylesheet" href="../css/jqueryautocomplete.css" type="text/css" />
	<link type="text/css" rel="stylesheet" href="../css/mybutton.css">
<script type="text/javascript" src="../scripts/jqueryautocomplete.js"></script>
<script type="text/javascript" language="JavaScript" src="../scripts/simplecalendar.js"></script>
<script type="text/javascript" src="../scripts/phanTrang.js"></script>

<SCRIPT language="javascript" type="text/javascript">
function submitform()
{   
   document.forms["ddhForm"].submit();
}

function submitForm(arg){
	
	document.forms["ddhForm"].action.value = arg;
	document.forms["ddhForm"].submit();
	
}

function newform()
{
	document.forms['ddhForm'].action.value= 'new';
	document.forms['ddhForm'].submit();
}


function clearform()
{
	document.forms["ddhForm"].sku.value="";
	document.forms["ddhForm"].tungay.value="";
	document.forms["ddhForm"].denngay.value="";
	document.forms["ddhForm"].trangthai.value="";
	document.forms["ddhForm"].submit();
	}

</SCRIPT>
</HEAD>
<BODY leftmargin="0" bottommargin="0" topmargin="0" rightmargin="0">
<form id="ddhForm" name="ddhForm" method="post" action="../../DondathangSvl">
<input type="hidden" name="userId" value='<%=userId%>'>

<input type="hidden" name="action" value="1" >
<input type="hidden" name="crrApprSplitting" value="<%= obj.getCrrApprSplitting() %>" >
<input type="hidden" name="nxtApprSplitting" value="<%= obj.getNxtApprSplitting() %>" >


<TABLE width="100%" border="0" cellspacing="0" cellpadding="0"
    height="100%"  bgcolor="#FFFFFF">
    <TR>
        <TD colspan="4" align='left' valign='top'> 
        
        <TABLE width="100%" cellspacing="0" cellpadding="0">
        	<TR>
            	<TD>
                	<TABLE width="100%" cellspacing="1" cellpadding="0">
                    	<TR>
                        	<TD align="left" class="tbnavigation">
                            	<table width="100%" border="0" cellpadding="0" cellspacing="0">
                                	<tr height="22">
                                    	<TD align="left" colspan="2" class="tbnavigation">&nbsp;Qu???n l?? t???n kho &gt; Mua h??ng t??? NCC &gt; ?????t h??ng </TD> 
                                   
                                        <TD colspan="2" align="right" class="tbnavigation">Ch??o m???ng  <%= obj.getNppTen() %> &nbsp;</TD>
                                    </tr>
                                  </table>
                              </TD>
                         </TR> 
                     </TABLE>
                    
                     <TABLE border="0" width="100%" cellspacing = 0 cellpadding = 0>

                        <TR>
                            <TD width="100%" align="left">
                                <FIELDSET>
                                <LEGEND class="legendtitle">&nbsp;Ti??u ch?? hi???n th??? &nbsp;</LEGEND>

                                <TABLE  width="100%" cellpadding="4" cellspacing="0">
                                    <TR>
                                        <TD width="19%" class="plainlabel">Ch???a SKU </TD>
                                      	<TD width="81%" class="plainlabel">
                                        	<input type="text" id="sku" name="sku" value="<%= obj.getSKU() %>" size=40/>
                                      		<!-- <img src="../images/Search20.png" width="20" height="20" > -->
                                      	</TD>
                                    </TR>
<TR>
	<TD class="plainlabel" ><%=Utility.GLanguage("T??? ng??y",session,jedis) %></TD>
	<td class="plainlabel">
		<input type="text"  class="days" size="11"
				id="tungay" name="tungay" value="<%= obj.getTungay() %>" maxlength="10" readonly />
	</td>
</TR>
<TR>
  <TD class="plainlabel" >?????n ng??y</TD>
  <td class="plainlabel">
		<input type="text"  class="days" size="11"
				id="denngay" name="denngay" value="<%= obj.getDenngay() %>" maxlength="10" readonly />
	</td>
</TR>
								<TR>
									<TD class="plainlabel"><%=Utility.GLanguage("Tr???ng th??i",session,jedis) %></TD>
									<TD class="plainlabel"><select name="trangthai" >
								  	<% if (obj.getTrangthai().equals("0")){ %>
								  		<option value=""></option>
								    	<option value="0" selected>C??n ch???nh s???a</option>
								    	<option value="1" >Ch??? x??? l?? </option>
								    	<option value="2">???? x??c nh???n</option>
								    	<option value="6" >???? h???y</option>
									<%}else									
								  		  if (obj.getTrangthai().equals("1")){ %>
								  		<option value=""></option>
								    	<option value="0">C??n ch???nh s???a</option>
								    	<option value="1" selected>Ch??? x??? l?? </option>
								    	<option value="2">???? x??c nh???n</option>
								    	<option value="6" >???? h???y</option>
									<%}else 				
								  		if (obj.getTrangthai().equals("2")){ %>
								  		<option value=""></option>
								    	<option value="0">C??n ch???nh s???a</option>
								    	<option value="1" >Ch??? x??? l??  </option>
								    	<option value="2" selected>???? x??c nh???n</option>								    
								    	<option value="6" >???? h???y</option>
									<%} else 				
								  	    { %>
								  		<option value=""></option>
								    	<option value="0">C??n ch???nh s???a</option>
								    	<option value="1">Ch??? x??? l??  </option>
								    	<option value="2">???? x??c nh???n</option>								    
								    	<option value="6">???? h???y</option>
									<%} %>
								    	</select></TD>
								</TR>

                                    		<TR>
                                        			                                       				
                                        		<TD class="plainlabel" align="left" colspan=2>
                                        				<a class="button1" href="javascript:submitForm('Hien thi')">
    													<img style="top: -4px;" src="../images/Search30.png" alt=""><%=Utility.GLanguage("T??m ki???m",session,jedis) %> </a> &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;                                                       				
                                        					<a class="button2" href="javascript:clearform()">
															<img style="top: -4px;" src="../images/button.png" alt=""><%=Utility.GLanguage("Nh???p l???i",session,jedis) %></a>&nbsp;&nbsp;&nbsp;&nbsp;	
                                        				<!--  <INPUT name="reinitialiser" type="button" value="Nhap  lai gia tri" onclick="clearform()" >-->

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
                                <LEGEND class="legendtitle">&nbsp;?????t h??ng&nbsp;&nbsp;
                                	<a class="button3"  onclick="newform()">
    								<img style="top: -4px;" src="../images/New.png" alt=""><%=Utility.GLanguage("T???o m???i",session,jedis) %> </a>                            
					
									<!--<INPUT name="action" type="submit" value="Tao moi"> -->	
                                
                                </LEGEND> 
                                
    
                      	        <TABLE width="100%">
                                <TR>
                                    <TD>
                                        <TABLE width="100%" border="0" cellspacing="1" cellpadding="4">
                                        <TR class="tbheader">
                                            <TH >Ng??y ?????t h??ng </TH>
                                            <TH >S??? ch???ng t??? </TH>
                                            <TH >????n v??? kinh doanh </TH>
											<TH ><%=Utility.GLanguage("Tr???ng th??i",session,jedis) %> </TH>
                                            <TH ><%=Utility.GLanguage("Ng?????i t???o",session,jedis) %> </TH>
                                            <TH ><%=Utility.GLanguage("Ng?????i s???a",session,jedis) %> </TH>
                                       
                                            <TH align="center">&nbsp;T??c v??? </TH>
                                        </TR>
         
                               <% 
                            NumberFormat formatter = new DecimalFormat("#,###,###");
                            int m = 0;
                            String lightrow = "tblightrow";
                            String darkrow = "tbdarkrow";
							if(dhlist != null){%>
								
								<% try{								
                                    while (dhlist.next()){
                                        	
                                       	if (m % 2 != 0) {%>                     
                                        	<TR class= <%=lightrow%> >
                                        <%} else {%>
                                           	<TR class= <%= darkrow%> >
                                        	<%}%>
												<TD align="left"><div align="left"><%= dhlist.getString("ngaydat") %></div></TD>                                   
                                                <TD><div align="center"><%= dhlist.getString("chungtu") %></div></TD>
                                                <TD><div align="center"><%= dhlist.getString("donvikinhdoanh") %></div></TD>
                                                <% if (dhlist.getString("trangthai").equals("0")){ %>
                                                   		<TD><div align="center">C??n ch???nh s???a</div></TD>
                                                <%}else   if (dhlist.getString("trangthai").equals("1")){ %>
                                                   		<TD><div align="center">Ch??? x??? l??</div></TD>
                                                <%}else   if (dhlist.getString("trangthai").equals("2")) {%>
                                                   		<TD><div align="center">???? x??c nh???n</div></TD>
                                                  <%}else  if (dhlist.getString("trangthai").equals("3")) {%>
                                                   		<TD><div align="center">Trung t??m ???? duy???t </div></TD>
                                                  <%}else  if (dhlist.getString("trangthai").equals("4")) {%>
                                                   		<TD><div align="center">???? xu???t H??</div></TD>
                                                   <%}else  if (dhlist.getString("trangthai").equals("5")) {%>
                                                   		<TD><div align="center">Ho??n t???t</div></TD>
                                                  <%} else if (dhlist.getString("trangthai").equals("7")) {%>
                                                  	<TD><div align="center">NPP h???y H?? </div></TD>
                                                  <%}else { %>
                                                  	<TD><div align="center">???? h???y </div></TD>
                                                  <%} %>
                                                <TD align="center"><%= dhlist.getString("nguoitao") %></TD>
                                                <TD align="center"><%= dhlist.getString("nguoisua")%></TD>
                                                <TD align="center">
                            	                    <TABLE border = 0 cellpadding="0" cellspacing="0">
                                	                    <TR><TD>
                                	                    
                                	                                                    	                    		<A href = "../../DondathangDisplaySvl?userId=<%=userId%>&display=<%=dhlist.getString("chungtu") %>"><img src="../images/Display20.png" alt="Hi???n th???" width="20" height="20" longdesc="Hi???n th???" border = 0 "></A>
                                	                    	<% if (dhlist.getString("trangthai").equals("0")) {%>
	                                    	                    <A href = "../../DondathangUpdateSvl?userId=<%=userId%>&update=<%=dhlist.getString("chungtu") %>"><img src="../images/Edit20.png" alt="C???p nh???t" width="20" height="20" longdesc="C???p nh???t" border = 0 "></A>
    	                                	                   	<A href = "../../DondathangSvl?userId=<%=userId%>&delete=<%=dhlist.getString("chungtu") %>"><img  onclick="if(!confirm('B???n mu???n x??a ?????t h??ng n??y?')) return false;"  src="../images/Delete20.png" alt="X??a" width="20" height="20" longdesc="X??a" border = 0 "></A> 
 																<A href = "../../DondathangSvl?userId=<%=userId%>&chot=<%=dhlist.getString("chungtu") %>"><img  onclick="if(!confirm('B???n mu???n ch???t ?????t h??ng n??y?')) return false;"  src="../images/Chot.png" alt="Ch???t" width="20" height="20" longdesc="Ch???t" border = 0 "></A> 
															<%} %>                                    	                   
                                        	            </TD>
                                                   
                                            		 </TABLE>
                                                </TD>
                                            </TR>
                                        <% m++; 
                                        } 
                                        }catch(java.sql.SQLException e){}
                               		}%>


 <tr class="tbfooter" > 
											 <TD align="center" valign="middle" colspan="13" class="tbfooter">
											 	<%if(obj.getNxtApprSplitting() >1) {%>
													<img alt="Trang Dau" src="../images/first.gif" style="cursor: pointer;" onclick="View('ddhForm', 1, 'view')"> &nbsp;
												<%}else {%>
													<img alt="Trang Dau" src="../images/first.gif" > &nbsp;
													<%} %>
												<% if(obj.getNxtApprSplitting() > 1){ %>
													<img alt="Trang Truoc" src="../images/prev.gif" style="cursor: pointer;" onclick="View('ddhForm', eval(ddhForm.nxtApprSplitting.value) -1, 'view')"> &nbsp;
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
													<a href="javascript:View('ddhForm', <%= listPage[i] %>, 'view')"><%= listPage[i] %></a>
												<%} %>
													<input type="hidden" name="list" value="<%= listPage[i] %>" />  &nbsp;
												<%} %>
												
												<% if(obj.getNxtApprSplitting() < obj.getTheLastSplitting()){ %>
													&nbsp; <img alt="Trang Tiep" src="../images/next.gif" style="cursor: pointer;" onclick="View('ddhForm', eval(ddhForm.nxtApprSplitting.value) +1, 'view')"> &nbsp;
												<%}else{ %>
													&nbsp; <img alt="Trang Tiep" src="../images/next_d.gif" > &nbsp;
												<%} %>
												<%if(obj.getNxtApprSplitting() == obj.getTheLastSplitting()) {%>
											   		<img alt="Trang Cuoi" src="../images/last.gif" > &nbsp;
										   		<%}else{ %>
										   			<img alt="Trang Cuoi" src="../images/last.gif" style="cursor: pointer;" onclick="View('ddhForm', -1, 'view')"> &nbsp;
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

<% if(!(dhlist == null)) dhlist.close(); %>
<%if(obj != null){
	obj.DBclose();
	obj = null;
}%>
<%}%>