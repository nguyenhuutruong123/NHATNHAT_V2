<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page  import = "geso.dms.distributor.beans.nhaphangchinhanh.INhaphangList" %>
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
	}else{
		int[] quyen = new  int[6];
		quyen = util.Getquyen("NhaphangchinhanhSvl","",userId);
	%>
<% Utility Util = new Utility(); %>
<% INhaphangList obj = (INhaphangList)session.getAttribute("obj"); %>
<% ResultSet nhaphanglist = (ResultSet)obj.getNhaphangList(); %>


<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"> <% Utility Utilback = new Utility(); %>
<HTML>
<HEAD>
<TITLE><%= getServletContext().getInitParameter("TITLENAME") %></TITLE>
<META http-equiv="Content-Type" content="text/html; charset=utf-8">

	<script type="text/javascript" src="../scripts/jquery.min.1.7.js"></script>
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

<LINK rel="stylesheet" type="text/css" media="print" href="../css/impression.css">
<LINK rel="stylesheet" href="../css/main.css" type="text/css">
<script type="text/javascript" src="../scripts/jquery.js"></script> 
<link rel="stylesheet" href="../css/jqueryautocomplete.css" type="text/css" />
	<link type="text/css" rel="stylesheet" href="../css/mybutton.css">

<script type="text/javascript" src="../scripts/jqueryautocomplete.js"></script>

<SCRIPT language="javascript" type="text/javascript">


function clearform()
{
    document.nhForm.sku.value = "";
    document.nhForm.tungay.value = "";
    document.nhForm.denngay.value = "";
    document.nhForm.trangthai.value="";
    document.nhForm.sodh.value="";
    document.nhForm.soct.value="";
    document.forms['nhForm'].submit();
}

function submitform()
{   
   document.forms["nhForm"].submit();
}

function xuatExcel()
{
	document.forms['nhForm'].action.value= 'toExcel';
	document.forms['nhForm'].submit();
}


</SCRIPT>
</HEAD>
<BODY leftmargin="0" bottommargin="0" topmargin="0" rightmargin="0">
<form name="nhForm" method="post" action="../../NhaphangSvl">
<% redis.clients.jedis.Jedis jedis =  geso.dms.center.util.Utility.getJedis(); %><% geso.dms.center.util.Csrf csdr=new geso.dms.center.util.Csrf(request,response,true,false,true);%>
 <input type="hidden" name="<%=csdr.get_tokenName() %>" value='<%=csdr.get_tokenValue() %>'>

<input type="hidden" name="userId" value='<%=userId%>'>
<input type="hidden" name="action" value='1'>
<TABLE width="100%" border="0" cellspacing="0" cellpadding="0"
    height="100%"  bgcolor="#FFFFFF">
    <TR>
        <TD colspan="4" align='left' valign='top'> 
        
        <TABLE width="100%" cellspacing="0" cellpadding="0" >
        	<TR>
            	<TD>
                	<TABLE width="100%" cellspacing="1" cellpadding="0" >
                    	<TR>
                        	<TD align="left" class="tbnavigation">
                            	<table width="100%" border="0" cellpadding="0" cellspacing="0">
                                	<tr height="22">
                                    	<TD align="left" colspan="2" class="tbnavigation">&nbsp;Qu???n l?? t???n kho &gt; &nbsp;Mua h??ng t??? Nh?? cung c???p &gt; &nbsp;Nh???n h??ng</TD>
                                   
                                        <TD colspan="2" align="right" class="tbnavigation">Ch??o m???ng Nh?? ph??n ph???i <%= obj.getNppTen() %>  &nbsp;</TD>
                                    </tr>
                                  </table>
                              </TD>
                         </TR> 
                     </TABLE>
                    
                     <TABLE border="0" width="100%" cellspacing = 0 cellpadding = 0 style="background-color: #C5E8CD;">

                        <TR>
                            <TD width="100%" align="left">
                                <FIELDSET>
                                <LEGEND class="legendtitle">&nbsp;Ti??u ch?? hi???n th??? &nbsp;</LEGEND>

                                <TABLE  width="100%" cellpadding="4" cellspacing="0" >
                                    <TR style="display: none;" >
                                        <TD width="19%" class="plainlabel">Ch???a SKU </TD>
                                      	<TD width="81%" class="plainlabel">
                                        	<input type="text" name="sku" value="<%= obj.getSKU() %>" size="40" onChange="submitform();">
                                      		
                                      	</TD>
                                    </TR>
									<TR>
										<TD class="plainlabel" width="120px" ><%=Utility.GLanguage("T??? ng??y",session,jedis) %> </TD>
									  	<TD class="plainlabel" >
											<input class="days" type="text" name="tungay" value="<%=obj.getTungay() %>" size="20" onchange="submitform();">				
										</TD>
									</TR>
									<TR>
	                                    <TD class="plainlabel" ><%=Utility.GLanguage("?????n ng??y",session,jedis) %> </TD>
									  	<TD class="plainlabel" >
											<input class="days" type="text" name="denngay" value="<%=obj.getDenngay() %>" size="20" onchange="submitform();">
										</TD>
	
									</TR>
									<TR>
										<TD class="plainlabel"><%=Utility.GLanguage("Tr???ng th??i",session,jedis) %></TD>
										<TD class="plainlabel">
											<select name="trangthai" onChange = "submitform();">
									  		
											<%if (obj.getTrangthai().equals("0")){ %>								  		
										    	<option value="0" selected>Ch??a nh???n h??ng </option>
										    	<option value="1">???? nh???n h??ng</option>
										    	<option value=""></option>
											<%  } else if (obj.getTrangthai().equals("1")){ %>								  		
										    	<option value="0" >Ch??a nh???n h??ng </option>
										    	<option value="1" selected>???? nh???n h??ng</option>
										    	<option value=""></option>
											<% } else { %>
												<option value="0" >Ch??a nh???n h??ng </option>
										    	<option value="1">???? nh???n h??ng</option>
										    	<option value="" selected ></option>
											<% } %>
											
									    	</select>
									    </TD>
									</TR>
									
									<TR>
										<TD class="plainlabel" width="120px" >S??? ch???ng t??? </TD>
									  	<TD class="plainlabel" >
											<input  type="text" name="soct" value="<%=obj.getSoct() %>" size="20" onchange="submitform();">				
										</TD>
									</TR>
									
									<TR>
										<TD class="plainlabel" width="120px" >S??? ????n h??ng </TD>
									  	<TD class="plainlabel" >
											<input  type="text" name="sodh" value="<%=obj.getSodh() %>" size="20" onchange="submitform();">				
										</TD>
									</TR>
									
									
                                <TR>
                                	<TD class="plainlabel" >
                                		 
                                		 <a class="button2" href="javascript:clearform()" width="60px;">
												<img style="top: -4px;" src="../images/button.png" alt="" ><%=Utility.GLanguage("Nh???p l???i",session,jedis) %></a>	 </TD>


										<td>	<a class="button2" href="javascript:xuatExcel()"> <img style="top: -4px;" src="../images/button.png" alt=""><%=Utility.GLanguage("Xu???t Excel",session,jedis) %> </a>
									

                                    	</TD>
                                    <TD class="plainlabel" colspan=4>&nbsp;</TD>
                                 </TR>
                               </TABLE>
                               </br>
                               </FIELDSET>
                              </TD>
							</TR>
									
						</TABLE>
                              
                          	</TD>			
                          </TR>
                      </TABLE>
                      <TABLE width="100%" cellspacing = 0 cellpadding=0>
                        <TR>
                            <TD >
                                <FIELDSET>
                                <LEGEND class="legendtitle">&nbsp;Nh???n h??ng</LEGEND> 
                                
    
                      	        <TABLE width="100%">
                                <TR>
                                    <TD>
                                        <TABLE width="100%" border="0" cellspacing="1" cellpadding="4">
                                        <TR class="tbheader">
                                        	<TH >M?? Phi???u</TH>
                                            <TH >Ng??y ch???ng t???</TH>
                                            <TH >Ng??y nh???n</TH>
                                            <TH >S??? nh???n h??ng</TH>
                                            <TH> S??? ch???ng t???</TH>
                                            <TH >??VKD </TH>
                                            <TH >K??nh b??n h??ng </TH>
                                            <TH >T???ng ti???n</TH>
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
							if(nhaphanglist != null){
								try{								
                                    while (nhaphanglist.next()){
                 
                                       	if (m % 2 != 0) {%>                     
                                        	<TR class= <%=lightrow%> >
                                        <%} else {%>
                                           	<TR class= <%= darkrow%> >
                                        	<%}%>
                                        		<TD align="left"><div align="left"><%= nhaphanglist.getString("chungtu") %></div></TD>
                                                <TD align="left"><div align="left"><%= nhaphanglist.getString("ngaychungtu") %></div></TD>
                                                <TD><div align="center"><%= nhaphanglist.getString("ngaynhan") %></div></TD>                                   
                                                <TD><div align="center"><%= nhaphanglist.getString("chungtu") %></div></TD>
                                                <TD style="text-align: center;" >
                                                	<% if (nhaphanglist.getString("trangthai").equals("1")){ %>
                                                		<%= nhaphanglist.getString("dathang_fk")==null?"NA":nhaphanglist.getString("dathang_fk")%>
                                                	<% }  %>
                                                </TD>
                                                <TD><div align="center"><%= nhaphanglist.getString("donvikinhdoanh") %></div></TD>
                                                <TD><div align="center"><%= nhaphanglist.getString("kbh") %></div></TD>
                                                <TD><div align="center"><%= nhaphanglist.getString("sotienavat") == null ? "0":formatter.format(Float.parseFloat(nhaphanglist.getString("sotienavat"))) %></div></TD>
                                                
                                                <% if (nhaphanglist.getString("trangthai").equals("0")){ %>
                                                   		<TD><div align="center">Ch??a nh???n h??ng</div></TD>
                                                <%}else { 
                                                   if (nhaphanglist.getString("trangthai").equals("1")) {%>
                                                   		<TD><div align="center">???? nh???n h??ng</div></TD>
                                                  <%}else if (nhaphanglist.getString("trangthai").equals("2")) {%>
                                                   		<TD><div align="center">H??a ????n h???y</div></TD>
                                                    <%  }}%>
                                                <TD align="center"><%= nhaphanglist.getString("nguoitao") %></TD>
                                                <TD align="center"><%= nhaphanglist.getString("nguoisua")%></TD>
                                                <TD align="center">
                            	                    <TABLE border = 0 cellpadding="0" cellspacing="0">
                                	                    <TR><TD>
                                	                    	<% if (nhaphanglist.getString("trangthai").equals("0") ) {%>
                                	                    	
                                	                    		<%if(quyen[Utility.SUA]!=0){ %>
	                                    	                    	<A href = "../../RouterSvl?task=<%= Util.dongMa(getServletContext().getInitParameter("RedirectNoScript") +"NhaphangchinhanhUpdateSvl?userId="+userId+"&update="+nhaphanglist.getString("chungtu")+"") %>"><img src="../images/Chot.png" alt="Nh???n h??ng" width="20" height="20" longdesc="Nh???n h??ng" border = 0 "></A>
																<%} %>
																
															<%}else{ %>
															
																<%if(quyen[Utility.XEM]!=0){ %>
                                	                    			<A href = "../../RouterSvl?task=<%= Util.dongMa(getServletContext().getInitParameter("RedirectNoScript") +"NhaphangchinhanhUpdateSvl?userId="+userId+"&display="+nhaphanglist.getString("chungtu")+"") %>"><img src="../images/Display20.png" alt="Hi???n th???" width="20" height="20" title="Hi???n th???" longdesc="Hi???n th???" border = 0 "></A>
																
																	<A href = "../../RouterSvl?task=<%= Util.dongMa(getServletContext().getInitParameter("RedirectNoScript") +"InPhieuNhapHangPdfSvl?userId="+userId+"&display="+nhaphanglist.getString("chungtu")+"") %>"><img src="../images/Printer20.png" alt="In phi???u nh???p h??ng" title="In phi???u nh???p h??ng"  width="20" height="20" longdesc="In phi???u nh???p h??ng" border = 0 "></A>
																
																<%} %>
															<%} %>                                    	                   
                                        	            </TD>
                                                   
                                            		 </TABLE>
                                                </TD>
                                            </TR>
                                        <% m++; } 
								}catch(Exception e){e.printStackTrace();}
                               		}%>
                               			<tr class="tbfooter" > <td colspan="12" >&nbsp;</td> </tr> 
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
</form>
</BODY>
</HTML>
<% if(!(nhaphanglist == null)) nhaphanglist.close(); %>
<% if(obj != null){
	obj.DBclose();
	obj = null;
	session.setAttribute("obj",null);
} %>
<% }%>