<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page  import = "geso.dms.distributor.beans.dondathang.IDondathang" %>
<%@ page import = "java.sql.ResultSet" %>
<%@ page  import = "geso.dms.center.util.*" %>
<%	String userId = (String) session.getAttribute("userId");  
	String userTen = (String) session.getAttribute("userTen");  	
	String sum = (String) session.getAttribute("sum");
	Utility util = (Utility) session.getAttribute("util");
	if(!util.check(userId, userTen, sum)){
		response.sendRedirect(request.getContextPath() + "/redirect.jsp");
	}else{ %>

<% IDondathang ddhBean = (IDondathang)session.getAttribute("ddhBean"); %>
<% ResultSet ncc = (ResultSet) ddhBean.getNcc(); %>
<% String nccId = (String) ddhBean.getNccId(); %>

<% ResultSet dvkd = (ResultSet)ddhBean.getDvkdIds(); %>
<% String dvkdId = (String)ddhBean.getDvkdId(); %>

<% ResultSet kbh = (ResultSet)ddhBean.getKbhIds(); %>
<% String kbhId = (String)ddhBean.getKbhId(); %>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"> <% Utility Utilback = new Utility(); %>

<HTML>
<HEAD>
<TITLE>Acecook - Project</TITLE>
<META http-equiv="Content-Type" content="text/html; charset=utf-8">

<META http-equiv="Content-Style-Type" content="text/css">
<SCRIPT src="../js/md5.js" type="text/javascript" language="javascript">
</SCRIPT>
<SCRIPT src="../js/scripts.js" type="text/javascript"
    language="javascript">
</SCRIPT>
<SCRIPT src="../js/commun.js" type="text/javascript"
    language="javascript">
</SCRIPT>

<META http-equiv="Content-Style-Type" content="text/css">
<LINK rel="stylesheet" href="../css/main.css" type="text/css">
<LINK rel="stylesheet" href="../css/calendar.css" type="text/css">
<LINK rel="stylesheet" type="text/css" media="print" href="../css/impression.css">

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

<script type="text/javascript" language="JavaScript" src="../scripts/jquery.js"></script>
<script type="text/javascript" language="JavaScript" src="../scripts/simplecalendar.js"></script>
<script type="text/javascript" language="JavaScript" src="../scripts/Numberformat.js"></script>


<SCRIPT type="text/javascript">
function submitform()
{   
   
   document.forms["ddhForm"].submit();
}


</SCRIPT>

</HEAD>
<BODY leftmargin="0" bottommargin="0" topmargin="0" rightmargin="0" >
<form name="ddhForm" method="post" action="../../DondathangUpdateSvl">
<INPUT name="userId" type="hidden" value='<%=userId %>' size="30">
<INPUT name="nppId" type="hidden" value='<%=ddhBean.getNppId() %>' size="30">
<input type="hidden" name="action" value='freshorder'>
<input type="hidden" name="ngaydenghigiaohang" value=''>
<input type="hidden" id="thuesuat" name="thuesuat" value='<%=ddhBean.getThueSuat()%>'>
<TABLE width="100%" border="0" cellspacing="0" cellpadding="0"
    height="100%"  bgcolor="#FFFFFF">
    <TR>
        <TD align='left' valign='top'> <!--begin body Dossier-->

            <TABLE width="100%" cellspacing="0" cellpadding="0">
                <TR>
                    <TD align="left" >
	                	<TABLE width="100%" cellspacing="1" cellpadding="0">
    	                	<TR>
        	                	<TD align="left">
            	                	<table width="100%" border="0" cellpadding="0" cellspacing="0">
                	                	<tr height="22">
                    	                	<TD align="left" colspan="2" class="tbnavigation">&nbsp;Quản lý tồn kho &gt; Mua hàng từ NCC &gt; Đặt hàng &gt;  Tạo mới
                                   
                        	                <TD colspan="2" align="right" class="tbnavigation">Chào mừng  <%= ddhBean.getNppTen() %> &nbsp;</TD>
                            	        </tr>
                                	  </table>
                              	</TD>
                         	</TR> 
                     	</TABLE>
                     	
            <TABLE width="100%" border="0" cellpadding="3" cellspacing="0">
                <TR ><TD >
                    <TABLE width="100%" border="0" cellpadding="0" cellspacing="0">
                        <TR class = "tbdarkrow">
                            <TD width="30" align="left"><A href="../../DondathangSvl?userId=<%=userId%>"  ><img src="../images/Back30.png" alt="Quay về"  title="Quay về" border="1" longdesc="Quay về" style="border-style:outset"></A></TD>
                            <TD width="2" align="left" >&nbsp;</TD>
                        </TR>
                    </TABLE>
                </TD></TR>

            </TABLE>

            <TABLE width = 100% cellpadding="0" cellspacing="0">
			  	<tr>
					<TD align="left" colspan="4" class="legendtitle">
						<FIELDSET>
						<LEGEND class="legendtitle">Thông báo </LEGEND>
				
	    				<textarea name="err" cols="84" rows="1" disabled ><%=ddhBean.getMessage()%></textarea>
						<% ddhBean.setMessage(""); %>
						</FIELDSET>
				   </TD>
				</tr>			
		 	
			<TR>
                
                <TR>
                    <TD >
                        <TABLE border="0" width="100%" cellpadding="0" cellspacing="0">
                            <TR>
                                <TD  align="left">
                                    <FIELDSET>

                                    <LEGEND class="legendtitle">&nbsp;Thông tin đề nghị đặt hàng &nbsp;</LEGEND>
                                                <TABLE cellpadding = 4 cellspacing = 0 width = "100%" border = 0>
                                                    <TR>
                                                        <TD width="20%" class="plainlabel"><%=Utility.GLanguage("Nhà phân phối",session,jedis) %> </TD>
                                                        <TD width="70%" class="plainlabel"><%= ddhBean.getNppTen() %></TD>                                                   
                                                    </TR>

                            
                                                    <TR >
                                                        <TD class="plainlabel">Ngày đặt hàng </TD>
                                                        <TD class="plainlabel">
        
                                                            <table border=0 cellpadding = 0 cellspacing = 0>
                                                                <tr>
                                                                    <td class="plainlabel"><input readonly="readonly" type="text" name="ngaydh" size="11" value="<%=ddhBean.getNgaydh() %>" ></td>
                                                                                                                                       
                                                                </tr>
                                                            </table>                                                
                                                        </TD>
                                                    </TR>
                                                    <TR class="tblightrow">
                                                        <TD  class="plainlabel">Nhà cung cấp </TD>
                                                        <TD  class="plainlabel"> 
                                                          <select name="nccId" onChange="submitform();">
                                                          	
                                                           <% try{ %>
                                                       			    	
                                                         	<%   if(ncc != null){
                                                         			while(ncc.next()){      
                                                            	      if (ncc.getString("nccId").equals(nccId)){ %>   
                                                                	       <option value='<%=ncc.getString("nccId")%>' selected><%=ncc.getString("nccTen")%></option>
                                                                	       
                                                                   <% }else{ %>
                                                                			<option value='<%=ncc.getString("nccId")%>'><%=ncc.getString("nccTen")%></option>   		   
                                                                 	<%} 
                                                                	} 
                                                                  }%>
                                                            <% }catch(java.sql.SQLException e){   %>
                                                                    <%  }  %>
                                                          </select>                                             
                                                        </TD>

                                                    </TR>
													<TR>
														<TD class="plainlabel">Đơn vị kinh doanh </TD>
								    					<TD class="plainlabel">
								    						<SELECT  name="dvkdId" style="width:80" onChange="submitform();">
														  	 <% if(!(dvkd ==null)){
														  	 		try{ while(dvkd.next()){ 
															    		if(dvkd.getString("dvkdId").equals(dvkdId)){ %>
								      										<option value='<%=dvkd.getString("dvkdId") %>' selected><%=dvkd.getString("dvkd") %></option>
								      							  	  <%}else{ %>
								     										<option value='<%=dvkd.getString("dvkdId") %>'><%=dvkd.getString("dvkd") %></option>
								     							  <%}}}catch(java.sql.SQLException e){} 
								     							 }%>	
                                  							</select>
                                  						</TD>
													</TR>
													<TR>
														<TD class="plainlabel">Kênh bán hàng </TD>
								    					<TD class="plainlabel">
								    						<SELECT  name="kbhId" style="width:80">
														  	 <% try{ while(kbh.next()){ 
															    	if(kbh.getString("kbhId").equals(kbhId)){ %>
								      									<option value='<%=kbh.getString("kbhId") %>' selected><%=kbh.getString("kbh") %></option>
								      							  <%}else{ %>
								     									<option value='<%=kbh.getString("kbhId") %>'><%=kbh.getString("kbh") %></option>
								     							  <%}}}catch(java.sql.SQLException e){} %>	
                                  							</select>
                                  						</TD>
													</TR>

													<TR>
														<TD class="plainlabel" colspan=2><INPUT type="submit" name="next" value="Tiếp tục"></TD>
													</TR>
                                                   
                                                </TABLE>
                                               </FIELDSET>
                                            </TD>
                                    </TR>

                                   
                                </TABLE>
                                
                        </TD>
                    </TR>   
            </TABLE>
    </td>

  </tr>
</table>

</form>
</body>
</HTML>

<% if(!(ncc == null))  ncc.close(); %>
<% if(!(dvkd == null)) dvkd.close(); %>
<% if(!(kbh == null)) kbh.close(); %>
<%if(ddhBean != null){
	ddhBean.DBclose();
	ddhBean = null;
} %>
<%}%>