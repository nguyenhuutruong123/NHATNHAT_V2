<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page  import = "geso.dms.center.beans.thaydoikhuyenmai.IThayDoiKhuyenMaiList" %>
<%@ page  import = "java.sql.ResultSet" %>
<%@ page  import = "java.text.DecimalFormat" %>
<%@ page  import = "java.text.NumberFormat" %>
<%@ page  import = "geso.dms.center.util.*" %>
<%	String userId = (String) session.getAttribute("userId");  
	String userTen = (String) session.getAttribute("userTen");  	
	String sum = (String) session.getAttribute("sum");
	Utility util = (Utility) session.getAttribute("util");
	String url = util.getChuyenNguUrl("ThayDoiKhuyenMaiSvl", "",session);
	if(util==null||!util.check(userId, userTen, sum)){
		response.sendRedirect(request.getContextPath() + "/redirect.jsp");
	}else{ %>
<% IThayDoiKhuyenMaiList obj = (IThayDoiKhuyenMaiList)session.getAttribute("obj"); %>
<% ResultSet nhaphanglist = (ResultSet)obj.getNhaphangList(); 
obj.setNextSplittings();
int[] quyen = new  int[6];
quyen = util.Getquyen("ThayDoiKhuyenMaiSvl","",userId);
%>
<% Utility Util = new Utility(); %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"> <% Utility Utilback = new Utility(); %>
<HTML>
<HEAD>
<TITLE><%= getServletContext().getInitParameter("TITLENAME") %></TITLE>
<META http-equiv="Content-Type" content="text/html; charset=utf-8">

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
<link rel="stylesheet" href="../css/jqueryautocomplete.css" type="text/css" />
<link type="text/css" rel="stylesheet" href="../css/mybutton.css">
<script type="text/javascript" src="../scripts/phanTrang.js"></script>
<script type="text/javascript" src="../scripts/jqueryautocomplete.js"></script>
<link href="../css/select2.css" rel="stylesheet" />
<script src="../scripts/select2.js"></script>
<script>
$(document).ready(function()
{
	$("#nppId").select2();
});
</script>

<SCRIPT language="javascript" type="text/javascript">

function clearform()
{
    document.nhForm.sohoadon.value = "";
    document.nhForm.tungay.value = "";
    document.nhForm.denngay.value = "";
    document.nhForm.nppId.selectedIndex = 0;
    document.nhForm.trangthai.selectedIndex = 0;
    document.forms['nhForm'].submit();
}

function submitform()
{   
   document.forms["nhForm"].submit();
}
function seach()
{   
   document.forms["nhForm"].submit();
}
function xuatExcel()
{
	document.forms['nhForm'].action.value= 'toExcel';
	document.forms['nhForm'].submit();
	document.forms['nhForm'].action.value= '';
}

function  newform()
{
	document.forms["nhForm"].action.value ="new";
	document.forms["nhForm"].submit();
}
function processing(id,chuoi)
{
	 document.getElementById(id).innerHTML = "<a href='#'><img src='../images/waiting.gif' width = '20' height = '20' title='cho thuc hien..' border='0' longdesc='cho thuc hien..' style='border-style:outset'> Proc...</a>"; 		  
	 document.getElementById(id).href=chuoi;
}


</SCRIPT>
</HEAD>
<BODY leftmargin="0" bottommargin="0" topmargin="0" rightmargin="0">
<form name="nhForm" method="post" action="../../ThayDoiKhuyenMaiSvl">
<% redis.clients.jedis.Jedis jedis =  geso.dms.center.util.Utility.getJedis(); %><% geso.dms.center.util.Csrf csdr=new geso.dms.center.util.Csrf(request,response,true,false,true);%>
 <input type="hidden" name="<%=csdr.get_tokenName() %>" value='<%=csdr.get_tokenValue() %>'>

<input type="hidden" name="userId" value='<%=userId%>'> 
<input type="hidden" name="userTen" value='<%=userTen%>'> 
<input type="hidden" name="action" value="1"> 
<input type="hidden" name="crrApprSplitting" value="<%= obj.getCrrApprSplitting() %>">
<input type="hidden" name="nxtApprSplitting"value="<%= obj.getNxtApprSplitting() %>">


<TABLE width="100%" border="0" cellspacing="0" cellpadding="0" height="100%"  bgcolor="#FFFFFF">
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
                                    	<TD align="left" colspan="2" class="tbnavigation">&nbsp;<%=url %></TD>
                                        <TD colspan="2" align="right" class="tbnavigation"><%=Utility.GLanguage("Ch??o m???ng b???n",session,jedis) %>  <%=userTen %>  &nbsp;</TD>
                                    </tr>
                                  </table>
                              </TD>
                         </TR> 
                     </TABLE>
                    
                     <TABLE border="0" width="100%" cellspacing = 0 cellpadding = 0>

                        <TR>
                            <TD width="100%" align="left">
                                <FIELDSET>
                                <LEGEND class="legendtitle">&nbsp;<%=Utility.GLanguage("Ti??u ch?? hi???n th???",session,jedis) %> &nbsp;</LEGEND>
                                <TABLE  width="100%" cellpadding="4" cellspacing="0">
                                
								<TR>
									<TD class="plainlabel" ><%=Utility.GLanguage("T??? ng??y",session,jedis) %> </TD>
						  			<TD class="plainlabel" >
										<TABLE cellpadding="0" cellspacing="0">
										<TR>
											<TD>
												<input class="days" type="text" name="tungay" value="<%=obj.getTungay() %>" size="20" onchange="submitform();">
											</TD>
										</TR>
										</TABLE>
									</TD>
								</TR>
									
								<TR>
                                    <TD class="plainlabel" ><%=Utility.GLanguage("?????n ng??y",session,jedis) %> </TD>
								  	<TD class="plainlabel" >
							  			<TABLE cellpadding="0" cellspacing="0">
							  				<TR>
							  					<TD>
													<input class="days" type="text" name="denngay" value="<%=obj.getDenngay() %>" size="20" onchange="submitform();">
												</TD>

                	                     	</TR>
										</TABLE>
									</TD>
								</TR>
								
								<TR>
									<TD class="plainlabel"><%=Utility.GLanguage("Tr???ng th??i",session,jedis) %></TD>
									<TD class="plainlabel"><select name="trangthai" onChange = "submitform();">
								  		<option value=""></option>
									<%if (obj.getTrangthai().equals("0")){ %>								  		
								    	<option value="0" selected><%=Utility.GLanguage("Ch??a duy???t",session,jedis) %> </option>
								    	<option value="1"><%=Utility.GLanguage("???? duy???t",session,jedis) %></option>
								    	<option value="2" ><%=Utility.GLanguage("???? h???y",session,jedis) %></option>
									<%}else{ 							
								  		if (obj.getTrangthai().equals("1")){ %>								  		
								    	<option value="0" selected><%=Utility.GLanguage("Ch??a duy???t",session,jedis) %> </option>
								    	<option value="1" selected><%=Utility.GLanguage("???? duy???t",session,jedis) %></option>
								    	<option value="2" ><%=Utility.GLanguage("???? h???y",session,jedis) %></option>
									<%  }else if (obj.getTrangthai().equals("2")){ %>								  		
								    	<option value="0" selected><%=Utility.GLanguage("Ch??a duy???t",session,jedis) %> </option>
								    	<option value="1"><%=Utility.GLanguage("???? duy???t",session,jedis) %></option>
								    	<option value="2" selected ><%=Utility.GLanguage("???? h???y",session,jedis) %></option>
									<% }else{  %>
										<option value="0" ><%=Utility.GLanguage("Ch??a duy???t",session,jedis) %> </option>
								    	<option value="1"><%=Utility.GLanguage("???? duy???t",session,jedis) %></option>
								    	<option value="2" ><%=Utility.GLanguage("???? h???y",session,jedis) %></option>
									<% }}%>
								    	</select></TD>
								</TR>

                                <TR>
                               	<TD class="plainlabel" align="left" colspan="4">
                              		 <a class="button2" href="javascript:clearform()">
								<img style="top: -4px;" src="../images/button.png" alt=""><%=Utility.GLanguage("Nh???p l???i",session,jedis) %></a>
								
								<a class="button2" href="javascript:submitform()">
								<img style="top: -4px;" src="../images/button.png" alt=""><%=Utility.GLanguage("T??m ki???m",session,jedis) %></a>
								<a class="button2" href="javascript:xuatExcel()"> <img style="top: -4px;" src="../images/button.png" alt=""><%=Utility.GLanguage("Xu???t Excel",session,jedis) %> </a>&nbsp;&nbsp;&nbsp;&nbsp;											
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
                              
                      <TABLE width="100%" cellspacing = 0 cellpadding=0>
                        <TR>
                            <TD >
                                <FIELDSET>
                                <LEGEND class="legendtitle">&nbsp;<%=Utility.GLanguage("Thay ?????i CTKM",session,jedis) %></LEGEND> 
                                <a
											class="button3" href="javascript:newform()"> <img
											style="top: -4px;" src="../images/New.png" alt=""><%=Utility.GLanguage("T???o m???i",session,jedis) %> </a>
    
                      	        <TABLE width="100%">
                                <TR>
                                    <TD>
                                        <TABLE width="100%" border="0" cellspacing="1" cellpadding="4">
                                        <TR class="tbheader">
                                            <TH ><%=Utility.GLanguage("ID",session,jedis) %> </TH>                                            
                                            <TH > <%=Utility.GLanguage("M?? tr??? KM",session,jedis) %></TH>
                                            <TH> <%=Utility.GLanguage("M?? ??i???u ki???n KM",session,jedis) %></TH>
                                            <TH ><%=Utility.GLanguage("Ng?????i t???o",session,jedis) %> </TH>
                                            <TH ><%=Utility.GLanguage("Ng??y t???o",session,jedis) %> </TH>
                                            <TH ><%=Utility.GLanguage("Ng?????i s???a",session,jedis) %> </TH>
                                            <TH ><%=Utility.GLanguage("Ng??y s???a",session,jedis) %> </TH>
                                            <TH > <%=Utility.GLanguage("Ng?????i duy???t",session,jedis) %></TH>
                                            <TH ><%=Utility.GLanguage("Ng??y duy???t",session,jedis) %> </TH>
											<TH ><%=Utility.GLanguage("Tr???ng th??i",session,jedis) %> </TH>
                                            <TH align="center">&nbsp;<%=Utility.GLanguage("T??c v???",session,jedis) %> </TH>
                                        </TR>
         
                               <% 
                            int m = 0;
                            String lightrow = "tblightrow";
                            String darkrow = "tbdarkrow";
							if(nhaphanglist != null){
								try{								
                                    while (nhaphanglist.next())
                                    {
                                    	String trangthai="";
                	                    if(nhaphanglist.getString("trangthai").equals("0"))
                	                    		trangthai="Ch??a duy???t";
                	                    else if(nhaphanglist.getString("trangthai").equals("1"))
                	                    		{
                	                    			trangthai="???? duy???t";
                	                    		}	
                                    	
                                       	if (m % 2 != 0) {%>                     
                                        	<TR class= <%=lightrow%> >
                                        <%} else {%>
                                           	<TR class= <%= darkrow%> >
                                        	<%}%>
                                                <TD align="left"><div align="left"><%= nhaphanglist.getString("thaydoiId") %></div></TD>
                                                <TD align="center"><%= nhaphanglist.getString("TraKm") %></TD>
                                                <TD align="center"><%= nhaphanglist.getString("dkKm") %></TD>
                                                <TD><div align="center"><%= nhaphanglist.getString("NguoiTao") %></div></TD>
                                                <TD><div align="center"><%= nhaphanglist.getString("NgayTao") %></div></TD>
                                                <TD><div align="center"><%= nhaphanglist.getString("NguoiSua") %></div></TD>
                                                <TD><div align="center"><%= nhaphanglist.getString("NgaySua") %></div></TD>
                                                <TD align="center"><%= nhaphanglist.getString("NguoiDuyet") %></TD>
                                                <TD align="center"><%= nhaphanglist.getString("NgayDuyet") %></TD>
                                                 <TD align="center"> <%= trangthai %></TD>
                                                <TD align="center">
                            	                    <TABLE border = 0 cellpadding="0" cellspacing="0">
                                	                    <TR>
                                	                    <%
                                	                    if(nhaphanglist.getString("trangthai").equals("0")){ %>
                                	                    
                                	                     <%if(quyen[Utility.XEM]!=0){ %>
	                                	                    <TD>	                                    	                    
	                                	                    	<A href = "../../RouterSvl?task=<%= Util.dongMa(getServletContext().getInitParameter("RedirectNoScript") +"ThayDoiKhuyenMaiUpdateSvl?userId="+userId+"&display="+nhaphanglist.getString("thaydoiId")+"") %>"><img src="../images/Display20.png" alt="Hi???n th???" width="20" height="20" longdesc="Hi???n th???" border = 0 "></A>															                                    	                   
	                                        	            </TD>
	                                        	            <%} %>
	                                        	            
                                	                    <%if(quyen[Utility.SUA]!=0){ %>
                                	                    	<TD>	                                    	                    
	                                	                    	<A href = "../../RouterSvl?task=<%= Util.dongMa(getServletContext().getInitParameter("RedirectNoScript") +"ThayDoiKhuyenMaiUpdateSvl?userId="+userId+"&edit="+nhaphanglist.getString("thaydoiId")+"") %>"><img src="../images/Edit.png" alt="C???p nh???t" width="20" height="20" longdesc="C???p nh???t" border = 0 "></A>															                                    	                   
	                                        	            </TD>
	                                        	          <%} %>
	                                        	          
	                                        	          <%if(quyen[Utility.CHOT]!=0){ %>
                                	                    	<TD>	                                    	                    
																	<%-- <A id='chotphieu<%=nhaphanglist.getString("thaydoiId")%>' href=""><img src="../images/Chot.png" alt="Ch???t" width="20" height="20" title="Ch???t" border="0" onclick="if(!confirm('B???n c?? mu???n th??m sp v??o ctkm n??y hay kh??ng?')) {return false ;}else{ processing('<%="chotphieu"+nhaphanglist.getString("thaydoiId")%>' , '../../ThayDoiKhuyenMaiSvl?userId=<%=userId%>&chot=<%=nhaphanglist.getString("thaydoiId") %>');}"  ></A> --%>															                                    	                   
																	
																	<%-- <A href = "../../RouterSvl?task=<%= Util.dongMa(getServletContext().getInitParameter("RedirectNoScript") +"ThayDoiKhuyenMaiUpdateSvl?userId="+userId+"&edit="+nhaphanglist.getString("thaydoiId")+"") %>"><img src="../images/Edit.png" alt="C???p nh???t" width="20" height="20" longdesc="C???p nh???t" border = 0 "></A> --%>
																	
																	<% String ChotStr = Utility.dongMa(getServletContext().getInitParameter("RedirectNoScript") +"ThayDoiKhuyenMaiSvl?userId="+userId+"&chot=" +nhaphanglist.getString("thaydoiId"));  %>
																	<A id='chotphieu<%=nhaphanglist.getString("thaydoiId")%>' href=""><img src="../images/Chot.png" alt="Ch???t" width="20" height="20" title="Ch???t" border="0" 
																	onclick="if(!confirm('B???n c?? mu???n th??m sp v??o ctkm n??y hay kh??ng?')) {return false ;}else{ 
																	processing('<%="chotphieu"+nhaphanglist.getString("thaydoiId")%>' , '../../RouterSvl?task=<%=ChotStr %>');}">
																	</A>
	                                        	            </TD>
																  <%} %>			
	                                        	          
	                                        	          <%if(quyen[Utility.XOA]!=0){ %>
                                	                    	<TD>	 
																	<%-- <A id='deletephieu<%=nhaphanglist.getString("thaydoiId")%>' href=""><img src="../images/Delete.png" alt="X??a" width="20" height="20" title="X??a" border="0" onclick="if(!confirm('B???n c?? mu???n x??a th??m sp v??o ctkm n??y hay kh??ng?')) {return false ;}else{ processing('<%="deletephieu"+nhaphanglist.getString("thaydoiId")%>' , '../../ThayDoiKhuyenMaiSvl?userId=<%=userId%>&delete=<%=nhaphanglist.getString("thaydoiId") %>');}"  ></A> --%>                                	                    			
                                	                    			<% String XoaStr = Utility.dongMa(getServletContext().getInitParameter("RedirectNoScript") +"ThayDoiKhuyenMaiSvl?userId="+userId+"&delete=" +nhaphanglist.getString("thaydoiId"));  %>                                   	                    
																	<A id='deletephieu<%=nhaphanglist.getString("thaydoiId")%>' href=""><img src="../images/Delete.png" alt="X??a" width="20" height="20" title="X??a" border="0" 
																	onclick="if(!confirm('B???n ch???c ch???n mu???n x??a m???c n??y kh??ng?')) {return false ;}else{ 
																	processing('<%="deletephieu"+nhaphanglist.getString("thaydoiId")%>' , '../../RouterSvl?task=<%=XoaStr %>');}">
																	</A>
	                                        	            </TD>
	                                        	          <%} %>
	                                        	            
	                                        	           
                                        	            <%}else { %>
                                        	            
                                        	            <%if(quyen[Utility.XEM]!=0){ %>
	                                	                    <TD>	                                    	                    
	                                	                    	<A href = "../../RouterSvl?task=<%= Util.dongMa(getServletContext().getInitParameter("RedirectNoScript") +"ThayDoiKhuyenMaiUpdateSvl?userId="+userId+"&display="+nhaphanglist.getString("thaydoiId")+"") %>"><img src="../images/Display20.png" alt="Hi???n th???" width="20" height="20" longdesc="Hi???n th???" border = 0 "></A>															                                    	                   
	                                        	            </TD>
	                                        	            <%} %>
                                        	            
                                        	            <%} %>
														</TR>
                                            		 </TABLE>
                                                </TD>
                                            </TR>
                                        <% m++; } 
								}catch(java.sql.SQLException e){e.printStackTrace();}
                               		}%>
                               			<tr class="tbfooter" > 
                               			
                               			<TD align="center" valign="middle" colspan="13" class="tbfooter">
											 	<%if(obj.getNxtApprSplitting() >1) {%>
													<img alt="Trang Dau" src="../images/first.gif" style="cursor: pointer;" onclick="View('nhForm', 1, 'view')"> &nbsp;
												<%}else {%>
													<img alt="Trang Dau" src="../images/first.gif" > &nbsp;
													<%} %>
												<% if(obj.getNxtApprSplitting() > 1){ %>
													<img alt="Trang Truoc" src="../images/prev.gif" style="cursor: pointer;" onclick="View('nhForm', eval(nhForm.nxtApprSplitting.value) -1, 'view')"> &nbsp;
												<%}else{ %>
													<img alt="Trang Truoc" src="../images/prev_d.gif" > &nbsp;
												<%} %>
												
												<%
													int[] listPage = obj.getNextSplittings();
													for(int i = 0; i < listPage.length; i++){
												%>
												
												<% 
												
												
												if(listPage[i] == obj.getNxtApprSplitting()){ %>
												
													<a  style="color:white;"><%= listPage[i] %>/ <%=obj.getTheLastSplitting() %></a>
												<%}else{ %>
													<a href="javascript:View('nhForm', <%= listPage[i] %>, 'view')"><%= listPage[i] %></a>
												<%} %>
													<input type="hidden" name="list" value="<%= listPage[i] %>" />  &nbsp;
												<%} %>
												
												<% if(obj.getNxtApprSplitting() < obj.getTheLastSplitting()){ %>
													&nbsp; <img alt="Trang Tiep" src="../images/next.gif" style="cursor: pointer;" onclick="View('nhForm', eval(nhForm.nxtApprSplitting.value) +1, 'view')"> &nbsp;
												<%}else{ %>
													&nbsp; <img alt="Trang Tiep" src="../images/next_d.gif" > &nbsp;
												<%} %>
												<%if(obj.getNxtApprSplitting() == obj.getTheLastSplitting()) {%>
											   		<img alt="Trang Cuoi" src="../images/last.gif" > &nbsp;
										   		<%}else{ %>
										   			<img alt="Trang Cuoi" src="../images/last.gif" style="cursor: pointer;" onclick="View('nhForm', -1, 'view')"> &nbsp;
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
        </TABLE><%geso.dms.center.util.Utility.JedisClose(jedis); %>
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