<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page  import = "geso.dms.center.beans.theodoithieuhang.ITheodoithieuhang" %>
<%@ page  import = "java.sql.ResultSet" %>
<%@ page  import = "geso.dms.center.util.*" %>
<%	String userId = (String) session.getAttribute("userId");  
	String userTen = (String) session.getAttribute("userTen");  	
	String sum = (String) session.getAttribute("sum");
	Utility util = (Utility) session.getAttribute("util");
	if(!util.check(userId, userTen, sum)){
		response.sendRedirect(request.getContextPath() + "/redirect.jsp");
	}else{ %>

<% ITheodoithieuhang obj = (ITheodoithieuhang)session.getAttribute("obj"); %>
<% ResultSet thlist = (ResultSet)obj.getThlist();
int[] quyen = new  int[5];
quyen = util.Getquyen("37",userId);

System.out.println(quyen[0]);
System.out.println(quyen[1]);
System.out.println(quyen[2]);
System.out.println(quyen[3]);
System.out.println(quyen[4]);%>


<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"> <% Utility Utilback = new Utility(); %>
<HTML>
<HEAD>
<TITLE><%= getServletContext().getInitParameter("TITLENAME") %></TITLE>
<META http-equiv="Content-Type" content="text/html; charset=utf-8">

<META http-equiv="Content-Style-Type" content="text/css">
<LINK rel="stylesheet" href="../css/main.css" type="text/css">
<LINK rel="stylesheet" href="../css/calendar.css" type="text/css">
<link type="text/css" rel="stylesheet" href="../css/mybutton.css">

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

<script type="text/javascript" language="JavaScript" src="../scripts/simplecalendar.js"></script>
<SCRIPT language="javascript" type="text/javascript">


function clearform()
{
    document.thForm.sku.value = "";
    document.thForm.tungay.value = "";
	document.thForm.denngay.value = "";       
    submitform();
}
function exportToExel(){
	document.forms['thForm'].action.value= 'excel';
	document.forms['thForm'].submit();
}

function submitform()
{
	document.forms['thForm'].action.value= 'search';
	document.forms['thForm'].submit();
}

function thongbao()
{
	var tt = document.forms['thForm'].msg.value;
	if(tt.length>0)
    alert(document.forms['thForm'].msg.value);
	}


</script>


</HEAD>
<BODY leftmargin="0" bottommargin="0" topmargin="0" rightmargin="0">
<form name="thForm" method="post" action="../../TheodoithieuhangSvl" charset="utf-8">
<INPUT name="userId" type="hidden" value='<%=userId %>' size="30">
<input type="hidden" name="action" value='1' >
<input type="hidden" name="msg" value='<%=obj.getMsg() %>'>

<script language="javascript" type="text/javascript">
    thongbao();
</script> 

<TABLE width="100%" border="0" cellspacing="0" cellpadding="0">
	
	<TR>
		<TD colspan="4" align='left' valign='top' bgcolor="#FFFFFF">
		<TABLE width="100%" cellpadding="0" cellspacing="1">
			
				<TR>
					<TD align="left">
					  <table width="100%" border="0" cellpadding="0" cellspacing="0">
						  <tr height="22">
						   <TD align="left" colspan="2" class="tbnavigation">&nbsp;Qu???n l?? b??n h??ng &gt; Theo d??i thi???u h??ng </TD>
   
						   <TD colspan="2" align="right" class="tbnavigation"><%=Utility.GLanguage("Ch??o m???ng b???n",session,jedis) %>  <%=userTen %>&nbsp;  </TD>
						  </tr>
 					  </table>
					</TD>
				</TR>
				<TR>
					<TD>
					<TABLE border="0" width="100%" cellpadding="0" cellspacing="0">
						<TR>
							<TD width="100%" align="center" >
							<FIELDSET>
							<LEGEND class="legendtitle">&nbsp;<%=Utility.GLanguage("Ti??u ch?? t??m ki???m",session,jedis) %> &nbsp;</LEGEND>

							<TABLE  width="100%" cellpadding="4" cellspacing="0">
								<TR>
									<TD width="19%" class="plainlabel" >SKU</TD>
								    <TD width="81%" class="plainlabel" ><INPUT name="sku" size="40" type="text" value='<%= obj.getSKU() %>' ></TD>
								</TR>
								
								<TR>
											<TD class="plainlabel" ><%=Utility.GLanguage("T??? ng??y",session,jedis) %> </TD>
								  	<TD class="plainlabel" >
										<TABLE cellpadding="0" cellspacing="0">
										<TR><TD>
											<input class="days" type="text" name="tungay" value="<%=obj.getTungay() %>" size="20" >
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
													<input class="days" type="text" name="denngay" value="<%=obj.getDenngay() %>" size="20" >
												</TD>
												
                	                     	</TR>
										</TABLE>
									</TD>

								</TR>
								<tR>
									<TD class="plainlabel" colspan="2"></TD>
								</tR>
								<TR>
									<TD class="plainlabel" colspan="2" >
										<a class="button1" href="javascript:submitform()">
  													<img style="top: -4px;" src="../images/Search30.png" alt=""><%=Utility.GLanguage("T??m ki???m",session,jedis) %> </a>
  													 &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
									<a class="button2" href="javascript:clearform()">
												<img style="top: -4px;" src="../images/button.png" 
												alt=""><%=Utility.GLanguage("Nh???p l???i",session,jedis) %></a>&nbsp;&nbsp;&nbsp;&nbsp;	
									<a class="button2" 
										href="javascript:exportToExel()">
											<img style="top: -4px;" 
											src="../images/button.png" alt=""><%=Utility.GLanguage("Xu???t Excel",session,jedis) %> </a>
											&nbsp;&nbsp;&nbsp;&nbsp;
                                  </TD>								
																		
								</TR>
							</TABLE>

							</FIELDSET>
							</TD>
						</TR>
					</TABLE>
					</TD>
				</TR>

				<TR>
					<TD >
						<FIELDSET>
							<LEGEND class="legendtitle">&nbsp;Theo d??i thi???u h??ng &nbsp; &nbsp; &nbsp;
							
						</LEGEND>
						<TABLE width="100%" cellpadding="0" cellspacing="0">
							<TR><TD>
								<TABLE width="100%" border="0" cellspacing="1" cellpadding="4">
								<TR class="tbheader">
									<TH width="10%">M?? s???n ph???m </TH>
									<TH width="15%">T??n s???n ph???m</TH>
									<TH width="5%">Kho</TH>
									<TH width="10%">Thi???u </TH>
									<TH width="12%">D??? t??nh b??? sung</TH>
									<TH width="12%">Ng??y d??? t??nh </TH>
									<TH width="12%">Th???c t??? b??? sung</TH>
									<TH width="12%">Ng??y th???c t??? </TH>
									<TH width="12%"><%=Utility.GLanguage("Tr???ng th??i",session,jedis) %> </TH>
									<TH width="10%" align="center">&nbsp;T??c v???</TH>
								</TR>
								<%	 
									
									int m = 0;
									String lightrow = "tblightrow";
									String darkrow = "tbdarkrow";
									if(thlist != null){
										while (thlist.next()){
											
											if (m % 2 != 0) {%>
																												
											<TR class= <%=lightrow%> >
										<%} else {%>
											<TR class= <%= darkrow%> >
										<%}%>
											<TD align="center"><div align="left"><%=thlist.getString("masp")%></div></TD>
											<TD align="center"><div align="left"><%=thlist.getString("tensp") %></div></TD>
											<TD align="center"><%=thlist.getString("kho") %></TD>
											<TD align="center"><%=thlist.getString("soluongthieu") %></TD>
									
									<TD align="center"><%=thlist.getString("soluongdt") %> </TD>
									
									<%if(thlist.getString("ngaydt").length()==0){%>
										<TD align="center">&nbsp;</TD>
									<%}else{ %>									
										<TD align="center"><%=thlist.getString("ngaydt").toString() %></TD>
									<%} %>
																		
									<%if(thlist.getString("soluongtt")== null){%>
										<TD align="center">0</TD>
									<%}else{ %>
										<TD align="center"><%=thlist.getString("soluongtt") %></TD>
									<%} %>
									
									<%if(thlist.getString("ngaytt")== null){%>
										<TD align="center">&nbsp;</TD>
									<%}else{ %>
										<TD align="center"><%=thlist.getString("ngaytt").toString() %></TD>
									<%} %>
									
									<% if(thlist.getString("trangthai").equals("0")){%>
											<TD align="center">Ch??a nh???p kho</TD>	
									<% 	}else{
											if(thlist.getString("trangthai").equals("1")){ %>
												<TD align="center">Nh???p kho ch??a ?????</TD>	
									<%		}else{ %>
												<TD align="center">???? nh???p kho ?????</TD>
									<%		}
										
										}%>
											
									
									
									<TD align="center">
										<TABLE border = 0 cellpadding="0" cellspacing="0">
											<TR><TD>
											<% if(!thlist.getString("trangthai").equals("2")){%>	
												<%if(quyen[2]!=0) {%>											
												<A href = "../../TheodoithieuhangSvl?userId=<%=userId%>&id=<%=thlist.getString("id")%>" ><img src="../images/Edit20.png" alt="Chinh sua" width="20" height="20" longdesc="Chinh sua" border = 0></A>
												<%} %>
											<%}else{%>
												&nbsp 
											<%} %>
											</TD>
											<TD>&nbsp;</TD>
											</TR>
										</TABLE>												
									 </TD>
									</TR>
									<% 	m++; }}%>
					
								<TR>
									<TD align="center" colspan="11" class="tbfooter">&nbsp;	</TD>
								</TR>
							</TABLE>
							</TD>
						</TR>
					</TABLE>

					</FIELDSET>
					</TD>
				</TR>

		</TABLE>

	</TR>
</TABLE>
</form>
</BODY>
</HTML>
<%
 	}%>