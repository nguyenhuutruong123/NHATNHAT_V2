<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page  import = "geso.dms.center.beans.hanmucdoanhthu.*" %>
<%@ page  import = "java.sql.ResultSet" %>
<%@ page  import = "geso.dms.center.util.*" %>


<%	String userId = (String) session.getAttribute("userId");  
	String userTen = (String) session.getAttribute("userTen");  	
	String sum = (String) session.getAttribute("sum");
	Utility util = (Utility) session.getAttribute("util");
	
	String url="";
	url = util.getUrl("hanmucdoanhthuUpdateSvl","");
	if(!util.check(userId, userTen, sum)){
		response.sendRedirect(request.getContextPath() + "/redirect.jsp");
	}else{ 
		Ihanmucdoanhthu obj = (Ihanmucdoanhthu)session.getAttribute("obj");
		ResultSet rsnpp=obj.getRsnppid();
		ResultSet rshanmuc=obj.getRshanmuc();
	%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"> <% Utility Utilback = new Utility(); %>
<%@page import="java.sql.SQLException"%>
<HTML>
<HEAD>
<TITLE><%= getServletContext().getInitParameter("TITLENAME") %></TITLE>
<META http-equiv="Content-Type" content="text/html; charset=utf-8">

<META http-equiv="Content-Style-Type" content="text/css">

<LINK rel="stylesheet" href="../css/main.css" type="text/css">
<script type="text/javascript" src="../scripts/jquery.min.1.7.js"></script>
<link href="../css/jquery-ui.css" rel="stylesheet" type="text/css" />
<script src="../scripts/ui/jquery.ui.core.js" type="text/javascript"></script>
<script src="../scripts/ui/jquery.ui.widget.js" type="text/javascript"></script>
<link type="text/css" rel="stylesheet" href="../css/mybutton.css">
<script src="../scripts/ui/jquery.ui.datepicker.js"
	type="text/javascript"></script>
<SCRIPT language="JavaScript" type="text/javascript">
function submitform()
{
	document.forms['mksForm'].submit();
}

function luulai()
{
	document.forms['mksForm'].action.value= 'save';
	document.forms['mksForm'].submit();
}

function updatehanhuc()
{
	var hanmuc = document.getElementsByName("hanmuc");
	var hanmuctong=document.getElementById("hanmucdt").value;
	for(var i=0; i < hanmuc.length; i++)
	{
		hanmuc.item(i).value=hanmuctong;
	}
}

</SCRIPT>

<script type="text/javascript">
	$(document).ready(function() {		
			$( ".days" ).datepicker({			    
					changeMonth: true,
					changeYear: true				
			});            
        }); 		
		
    </script>
</HEAD>
<BODY leftmargin="0" bottommargin="0" topmargin="0" rightmargin="0">
<form name ="mksForm"  method="post" action="../../hanmucdoanhthuUpdateSvl" >
<input type="hidden" name="action" value="0">
<input name="userId" type="hidden" value='<%=userId %>' size="30">
<input type="hidden" name="loai" value="2">
<TABLE width="100%" border="0" cellspacing="0" cellpadding="0"
	height="100%">
	<TR>
		<TD colspan="4" align='left' valign='top' bgcolor="#FFFFFF">
			<TABLE width="100%" border="0" cellpadding="0" cellspacing="1">
				<TR>
					<TD align="left" class="tbnavigation">
						<table width="100%" border="0" cellpadding="0" cellspacing="0">
						  <tr height="22">
							 <TD align="left" colspan="2" class="tbnavigation">&nbsp;<%=url %> </TD> 
							 <TD colspan="2" align="right" class="tbnavigation"><%=Utility.GLanguage("Chào mừng bạn",session,jedis) %>  <%=userTen %>&nbsp;  </TD></tr>
						</table>
			 		</TD>
				</TR>
			</TABLE>
			<TABLE width="100%" border="0" cellpadding="0"  cellspacing="1" >
			  	<tr>
					<TD align="left" colspan="4" class="legendtitle">
						<FIELDSET>
						<LEGEND class="legendtitle">Thông báo </LEGEND>
				
	    				<textarea name="dataerror"  style="width: 100% ; color:#F00 ; font-weight:bold"  style="width :100%" rows="1"><%=obj.getMsg() %></textarea>

						</FIELDSET>
				   </TD>
				</tr>			

				<TR>
				  <TD height="100%" width="100%" border = '0'>
						<FIELDSET >
						<LEGEND class="legendtitle" style="color:black">Chọn Chi nhánh / NPP</LEGEND>
						<TABLE border="0" width="100%"  cellpadding="6" cellspacing="0">
						 
							<TR>  
							    <TD class="plainlabel" width="50px" ><%=Utility.GLanguage("Từ ngày",session,jedis) %></TD>
										  	<TD class="plainlabel" width="100px" ><INPUT class="days" name="tungay" type="text" size="40" value="" ></TD>
											
							   	</TR>
                        	
							<TR>  
											<TD class="plainlabel" width="50px"><%=Utility.GLanguage("Đến ngày",session,jedis) %> </TD>
												<TD class="plainlabel" width="100px" ><INPUT class="days" name="denngay" type="text" size="40" value="" ></TD>		
                        	</TR>
                        	<TR>  
											<TD class="plainlabel" width="50px">Hạn mức</TD>
												<TD class="plainlabel" width="100px" ><INPUT  id="hanmucdt" type="text" size="40" value="" onchange="updatehanhuc();" ></TD>		
                        	</TR>
                        	
					      <TR>
					      		<TD class="legendtitle" colspan=3>
					      		<a class="button3"  href="javascript:luulai();">
					      		 	<img style="top: -4px;" src="../images/New.png" alt="">Lưu Lại 
					      		 </a>   
					      		</TD>
					      		
					      </TR>			  
						</TABLE>
						</FIELDSET>
					</td>
				</TR>
				<tr>
					<td>
						<fieldset>
							<TABLE class="tabledetail" cellpadding="0" cellspacing="0" width="100%">
								<TR id="spdvkd" bgcolor="#FFFFFF">
									<TD width="100%">
									<TABLE width="100%" border="0" cellspacing="1" cellpadding="0">
										<TR class="tbheader">
											<TH width="80%" align="left"> Tên nhà phân phối</TH>
											<TH width="20%">Hạn mức doanh thu</TH>
										</TR>
										
									
																		
												<% try{
									    	  	if(rsnpp != null){	
									    	  		int m = 0;
									    	  		while(rsnpp.next()){
									    	  			
														String lightrow = "tblightrow";
														String darkrow = "tbdarkrow";
									    	  			
									    	  			%>
									    	  			
									    	  		<% 	if (m % 2 != 0) {%>	
														
															<TR class= <%=lightrow%>  ">
													<%} else {%>
															
															<TR class= <%= darkrow%>   > 
														<%}%>
									    	  			
									      					<Td width="80%" align="left" ><%=rsnpp.getString("TEN") %>
									      					<input type="hidden" name="npp" value="<%=rsnpp.getString("pk_seq")%>" />	
									      					</Td>
									      					 <Td width="20%" align="center">
									      					
									      				 		 <input type="text"  name="hanmuc" value="">
									      				 	
									      					   </Td>
									     				</TR>
									     				
									     			  <% m++;
									    			}
									    	  	}
								    	  }catch(java.sql.SQLException e){} %>	
										
											
										
										
									</TABLE>
		
									</TD>
								</TR>
							</TABLE>	
						</fieldset>
					</TD>
				</TR>				
			</TABLE>
			
		</TD>
		</TR>
		</TABLE>
		</form>
		</BODY>
		</HTML>
<%
try{
	/* if( mksBean != null){
		mksBean.DBClose();
		mksBean=null;
	}
	if(vung!=null){
		vung.close();
	}
	if(kv!=null){
		kv.close();
	}
	if(npp!=null){
		npp.close();
	}
	if(nppkstd!=null){
		nppkstd.close();
	} */
	if(rsnpp!=null){
		rsnpp.close();
	} 
	if(rshanmuc!=null){
		rshanmuc.close();
	} 
	
	
}
catch (Exception e) {}

}%>