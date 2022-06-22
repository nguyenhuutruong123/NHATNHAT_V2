<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@page import="geso.dms.center.beans.danhmucquyen.IDanhmucquyenList"%>
<%@page import="geso.dms.center.beans.danhmucquyen.IDanhmucquyen"%>
    <%@ page  import = "java.util.List" %>
<%@ page  import = "java.sql.ResultSet" %>
<%@ page  import = "geso.dms.center.util.*" %>
<%	String userId = (String) session.getAttribute("userId");  
	String userTen = (String) session.getAttribute("userTen");  	
	String sum = (String) session.getAttribute("sum");
	Utility util = (Utility) session.getAttribute("util");

	int[] quyen = new  int[6];
	quyen = util.Getquyen("DanhmucquyenSvl","",userId);
	System.out.println("quyen them "+quyen[Utility.THEM]);
	System.out.print("quyen xoa "+quyen[Utility.XOA]);
	System.out.print("quyen sua "+quyen[Utility.SUA]);
	System.out.print("quyen xem "+quyen[Utility.XEM]);
	System.out.print("quyen them "+quyen[Utility.THEM]);
	String url = util.getChuyenNguUrl("DanhmucquyenSvl", "",session);
%>
<% IDanhmucquyenList obj = (IDanhmucquyenList)session.getAttribute("obj"); %>
<% ResultSet Quyen = (ResultSet)obj.getDSQuyen(); %>
<% ResultSet BangQuyen = (ResultSet)obj.BangQuyen(); %>
<% Utility Util = new Utility(); %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"> <% Utility Utilback = new Utility(); %>
<HTML>
<HEAD>
<TITLE><%= getServletContext().getInitParameter("TITLENAME") %></TITLE>
<META http-equiv="Content-Type" content="text/html; charset=utf-8">

<META http-equiv="Content-Style-Type" content="text/css">
<LINK rel="stylesheet" type="text/css" media="print" href="../css/impression.css">
<LINK rel="stylesheet" href="../css/main.css" type="text/css">
<link type="text/css" rel="stylesheet" href="../css/mybutton.css">
<LINK rel="stylesheet" href="../css/calendar.css" type="text/css">

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
	document.forms['dmquyen'].action.value= 'xoa';
	document.forms['dmquyen'].submit();
}

function submitform()
{
	document.forms['dmquyen'].action.value= 'search';
	document.forms['dmquyen'].submit();
}

function newform()
{
	document.forms['dmquyen'].action.value= 'new';
	document.forms['dmquyen'].submit();
}
function xuatExcel()
{
	document.forms['dmquyen'].action.value= 'toExcel';
	document.forms['dmquyen'].submit();
	document.forms['dmquyen'].action.value= '';
}
</SCRIPT>

</HEAD>
<BODY leftmargin="0" bottommargin="0" topmargin="0" rightmargin="0">
<form name="dmquyen" method="post" action="../../DanhmucquyenSvl">
<% redis.clients.jedis.Jedis jedis =  geso.dms.center.util.Utility.getJedis(); %><% geso.dms.center.util.Csrf csdr=new geso.dms.center.util.Csrf(request,response,true,false,true);%>
 <input type="hidden" name="<%=csdr.get_tokenName() %>" value='<%=csdr.get_tokenValue() %>'>

<input type="hidden" name="userId" value='<%=userId %>'>
<input type="hidden" name="action" value='1'>

<TABLE width="100%" border="0" cellspacing="0" cellpadding="0"
	height="100%">
	<TR>
		<TD colspan="4" align='left' valign='top' bgcolor="#ffffff">
			<TABLE width="100%" border="0" cellpadding="0" cellspacing="1">
				<TR>
					<TD align="left" >
					   <table width="100%" border="0" cellpadding="0" cellspacing="0">
						  <tr height="22">
							   <TD align="left" colspan="2" class="tbnavigation">
							   		<%=url %>
							   </TD>
							   <TD colspan="2" align="right" class="tbnavigation"><%=Utility.GLanguage("Chào mừng bạn",session,jedis) %>  &nbsp;<%=userTen %></td> 
						    </tr>
						</table>
					</TD>
				</TR>
				<TR>
					<TD>
						<TABLE border="0" width="100%" >
							<TR>
								<TD width="100%" align="left"><FIELDSET>
									<LEGEND class="legendtitle"><%=Utility.GLanguage("Tiêu chí tìm kiếm",session,jedis) %>  </LEGEND>

									<TABLE class="tblight" width="100%" cellpadding="6" cellspacing = "0">
										<TR>
								    <TD class="plainlabel"><%=Utility.GLanguage("Quyền",session,jedis) %>  </TD>
								    <TD colspan="3" class="plainlabel">
								    	<SELECT name="maquyen" onChange = "submitform();">
									    <option value=""></option> 
									      <% try{ 
									    	  if(Quyen!=null)
									    	  while(Quyen.next()){ 
									    	if(Quyen.getString("PK_SEQ").equals(obj.getMaQuyen())){ %>
									      		<option value='<%=Quyen.getString("PK_SEQ") %>' selected='selected'><%=Quyen.getString("Ten") %></option>
									      	<%}else{ %>
									     		<option value='<%=Quyen.getString("PK_SEQ") %>' ><%=Quyen.getString("Ten") %></option>
									     	<%}}}catch(java.sql.SQLException e){} %>
									     	
									     	
									    </SELECT></TD>
				                   </TR>
										<TR>
											<TD width="19%" class="plainlabel" ><%=Utility.GLanguage("Tên quyền",session,jedis) %></TD>
										  <TD width="81%" class="plainlabel" ><INPUT name="tenquyen" type="text" size="40" value="<%=obj.getTenQuyen() %>" onChange="submitform();" ></TD>
										</TR>
										
										<TR>							
											<TD class="plainlabel" ><%=Utility.GLanguage("Từ ngày",session,jedis) %> </TD>
											<TD class="plainlabel" colspan="3">
												<TABLE cellpadding="0" cellspacing="0">
													<TR><TD>
										<input class="days" type="text" name="tungay" value='<%= obj.getTungay() %>'  size="20" onchange=submitform(); >
												</TD>
												
		   										</TR>
												</TABLE>
																						
		  									</TD>
										</TR>
										<TR>
                                          <TD class="plainlabel" ><%=Utility.GLanguage("Đến ngày",session,jedis) %> </TD>
										  <TD class="plainlabel" colspan="3">
										  		<TABLE cellpadding="0" cellspacing="0"><TR><TD>
										 <input class="days" type="text" name="denngay" value='<%=obj.getDenngay() %>' size="20" onchange=submitform(); >
										  		</TD>
										

											  </TR>
											 </TABLE>
									  	</TR>
									
										<TR>
										    <TD class="plainlabel" colspan=4>
										    <a class="button2" href="javascript:clearform()">
	<img style="top: -4px;" src="../images/button.png" alt=""><%=Utility.GLanguage("Nhập lại",session,jedis) %></a>&nbsp;&nbsp;&nbsp;&nbsp;	
									<a class="button2" href="javascript:xuatExcel()"> <img style="top: -4px;" src="../images/button.png" alt=""><%=Utility.GLanguage("Xuất Excel",session,jedis) %> </a>	    

										</TR>
									</TABLE>
									</FIELDSET>
								</TD>	
							</TR>
						</TABLE>
					</TD>
				</TR>	
				
			    <TR>
					<TD align="left" >
						<FIELDSET>
				          	<legend>
				          	
				          	  <LEGEND class="legendtitle"><%=Utility.GLanguage("Danh sách quyền",session,jedis) %> &nbsp;&nbsp;
				          	         <%    if(quyen[Utility.THEM]!=0){  %>
				          	  <a class="button3" href="javascript:newform()">
    	<img style="top: -4px;" src="../images/New.png" alt=""><%=Utility.GLanguage("Tạo mới",session,jedis) %> </a>                            
				          	  <%} %>
				          	  <!--  <INPUT name="taomoi" type="button" value="Tao moi" onClick="newform();">--> 
				          	     </LEGEND>
                    	      
                            
                              </legend>
						<TABLE class="" width="100%" border="0" cellpadding="0" cellspacing="0">
								<TR>
									<TD width="98%">
										<TABLE width="100%" border="0" cellspacing="1" cellpadding="4">
											<TR class="tbheader">
												<TH width="20%"><%=Utility.GLanguage("Mã quyền",session,jedis) %></TH>
												<TH width="10%"> <%=Utility.GLanguage("Tên quyền",session,jedis) %></TH>
												<TH width="9%"><%=Utility.GLanguage("Người tạo",session,jedis) %></TH>
												<TH width="16%"><%=Utility.GLanguage("Ngày tạo",session,jedis) %></TH>
												<TH width="9%"><%=Utility.GLanguage("Người sửa",session,jedis) %></TH>
												<TH width="15%"><%=Utility.GLanguage("Ngày sửa",session,jedis) %></TH>
												<TH width="15%"><%=Utility.GLanguage("Trạng thái",session,jedis) %></TH>
												<TH width="9%"><%=Utility.GLanguage("Tác vụ",session,jedis) %></TH>
									    	</TR>
									    	<%
									    	int m=0;
											if(BangQuyen != null){
											while (BangQuyen.next()){
												if (m % 2 != 0) {%>						
													<TR class= <%="tblightrow"%> >
												<%} else {%>
													<TR class= <%= "tbdarkrow"%> >
												<%}%>
												<TH width="20%"><%=BangQuyen.getString("maquyen") %></TH>
												<TH width="10%"><%=BangQuyen.getString("ten") %> </TH>
												<TH width="9%"><%=BangQuyen.getString("nguoitao") %></TH>
												<TH width="9%"><%=BangQuyen.getString("ngaytao") %></TH>
												<TH width="16%"><%=BangQuyen.getString("nguoisua") %></TH>
												<TH width="9%"><%=BangQuyen.getString("ngaysua") %> </TH>
												<% if(BangQuyen.getString("hoatdong").equals("1")) { %> 
												 <TH width="9%"><%=Utility.GLanguage("Hoạt động",session,jedis) %></TH>
												<%} else { %>
												 <TH width="9%"><%=Utility.GLanguage("Ngưng hoạt động",session,jedis) %></TH>
												<%} %>
												<TD align="center">
												<TABLE border = 0 cellpadding="0" cellspacing="0">
													<TR>
													<TD>
													       <%    if(quyen[Utility.SUA]!=0){  %>
														<A href = "../../RouterSvl?task=<%= Util.dongMa(getServletContext().getInitParameter("RedirectNoScript") +"DanhmucquyennewSvl?userId="+BangQuyen.getString("maquyen")+"")%>"><img src="../images/Edit20.png" alt="Cap nhat" width="20" height="20" longdesc="Cap nhat" border = 0></A>
													<%} %>
													</TD>
													<TD>&nbsp;</TD>
													 <TD>
													        <%    if(quyen[Utility.XOA]!=0){  %>
														<A href = "../../RouterSvl?task=<%= Util.dongMa(getServletContext().getInitParameter("RedirectNoScript") +"DanhmucquyenSvl?userId="+BangQuyen.getString("maquyen")+"")%>"><img src="../images/Delete20.png" alt="Xoa" width="20" height="20" longdesc="Xoa" border=0 onclick="if(!confirm('Bạn có muốn xóa danh mục quyền này hay không ?')) return false;"></A>
													 <%} %>
													 </TD>
												</TR></TABLE>
											
								           </TR>
								           	<%m++; }}%>
								           
								         
						              	</TABLE>
							          </TD>
												
									    	</TR>
							     
							
								<TR>
									<TD align="center" colspan="11" class="tbfooter">&nbsp;</TD>
								</TR>
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
<%//} %>