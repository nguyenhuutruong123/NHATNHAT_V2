<%@page import="java.sql.SQLException"%>
<%@page import="java.sql.ResultSet"%>
<%@page import="javax.naming.spi.DirStateFactory.Result"%>
<%@page import="geso.dms.center.db.sql.dbutils"%>
<%@ page  import = "geso.dms.center.util.*" %>
<%@page import="geso.dms.distributor.beans.taikhoankt.*"%>

<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%	String userId = (String) session.getAttribute("userId");  
	String userTen = (String) session.getAttribute("userTen");  	
	String sum = (String) session.getAttribute("sum");
	Utility util = (Utility) session.getAttribute("util");
	
	if(!util.check(userId, userTen, sum)){
		response.sendRedirect("/Phanam/index.jsp");
	}else{ %>


<% 		ITaikhoanktList tkktList= (ITaikhoanktList)session.getAttribute("tkktList"); %>
<% 		ResultSet TaiKhoanRs = tkktList.getTaiKhoanRs(); %>
<% 		ResultSet CongTyRs = tkktList.getCongTyRs(); %>
<% 		ResultSet LoaiTaiKhoanRs = tkktList.getLoaiTaiKhoanRs();   
		int[] quyen = new  int[5];
		quyen = util.Getquyen("TaikhoanketoanSvl","",userId);
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd"> <% Utility Utilback = new Utility(); %>
<html>
<head>

<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>Insert title here</title>
<LINK rel="stylesheet" type="text/css" media="print" href="../css/impression.css">
<LINK rel="stylesheet" href="../css/main.css" type="text/css">
<link type="text/css" rel="stylesheet" href="../css/mybutton.css">
<script type="text/javascript" src="../scripts/jquery.min.js"></script>
<script type="text/javascript" src="../scripts/phanTrang.js"></script>

<link href="../css/jquery-ui.css" rel="stylesheet" type="text/css" />
<script src="../scripts/ui/jquery.ui.core.js" type="text/javascript"></script>
<script src="../scripts/ui/jquery.ui.widget.js" type="text/javascript"></script>
<script src="../scripts/ui/jquery.ui.datepicker.js" type="text/javascript"></script>
<script type="text/javascript">
      $(document).ready(function() 
    		  {            
            $( ".days" ).datepicker({                     
                        changeMonth: true,
                        changeYear: true                    
            });            
      });   
</script>

<script type="text/javascript">
	function clearform()
	{
		document.tkktListForm.SoHieuTaiKhoan.value = "";
		document.tkktListForm.TenTaiKhoan.value = "";
		document.tkktListForm.LoaiTaiKhoanId.selectedIndex = -1;
		//document.tkktListForm.CongTyId.selectedIndex = -1;
	    document.tkktListForm.TrangThai.selectedIndex = 2;
	    Submitform();
	}
	function Submitform()
	{
		document.forms["tkktListForm"].action.value = "search";
		document.forms["tkktListForm"].submit();
	}
	function TaoMoi()
	{
		document.forms["tkktListForm"].action.value = "new";
		document.forms["tkktListForm"].submit();
	}
	 function thongbao()
	 {
		 var tt = document.forms["tkktListForm"].msg.value;
	 	 if(tt.length>0)
	     	alert(document.forms["tkktListForm"].msg.value);
	 	}
	
</script>
</head>
<body>
<form name="tkktListForm" method="post" action="../../TaikhoanketoanSvl" >
<input type="hidden" name="action">
<input type="hidden" name="msg" value='<%= tkktList.getMsg() %>'>
<input type="hidden" name="crrApprSplitting" value="<%= tkktList.getCrrApprSplitting() %>">
<input type="hidden" name="nxtApprSplitting" value="<%= tkktList.getNxtApprSplitting() %>"> 
<input type="hidden" name="chixem" value='<%= tkktList.getChixem() %>'>

<script language="javascript" type="text/javascript">
    thongbao();
</script>
<TABLE width="100%" border="0" cellspacing="0" cellpadding="0"
	height="100%">
	<TR>
		<TD colspan="4" align='left' valign='top'>
			<TABLE width="100%" border="0" cellpadding="0" cellspacing="1">
				<TR>
					<TD align="left" >
					   <table width="100%" border="0" cellpadding="0" cellspacing="0">
						  <tr height="22">
							   <TD align="left" colspan="2" class="tbnavigation">&nbsp;D??? li???u n???n > K??? to??n > T??i kho???n k??? to??n </TD>
							   <TD colspan="2" align="right" class="tbnavigation"><%=Utility.GLanguage("Ch??o m???ng b???n",session,jedis) %>  <%=userTen %>  </TD> 
						    </tr>
   						</table>
					</TD>
				</TR>
			</TABLE>
			<TABLE width="100%" border="0" cellpadding="0" cellspacing="1">
				<TR>
					<TD>
						<TABLE border="0" width="100%" cellpadding="0" cellspacing="0">
							<TR>
								<TD width="100%" align="left">
								<FIELDSET >
									<LEGEND class="legendtitle"><%=Utility.GLanguage("Ti??u ch?? t??m ki???m",session,jedis) %>  </LEGEND>

									<TABLE class="tblight" width="100%" cellpadding="6" cellspacing = "0">
										   <TR>
										  <TD class="plainlabel" >S??? hi???u t??i kho???n</TD>
										  <TD class="plainlabel" ><input type="text" class="txtsearch"  name="SoHieuTaiKhoan" size="20" value="<%= tkktList.getSoHieuTaiKhoan()%>" onchange="Submitform()" ></TD>
									   </TR>
									   <TR>
										  <TD class="plainlabel" >T??n t??i kho???n</TD>
										  <TD class="plainlabel" ><input type="text" class="txtsearch"  name="TenTaiKhoan" size="20" value="<%= tkktList.getTenTaiKhoan()%>" onchange="Submitform()"></TD>
									   </TR>
									    <TR>
											<TD width="20%" class="plainlabel" >Ch???n lo???i t??i kho???n</TD>
										    <TD width="80%" class="plainlabel" >
										   		<select id="LoaiTaiKhoanId" name="LoaiTaiKhoanId" class="txtsearch" onchange="Submitform()">
												<option value = "" >&nbsp; </option>
													<%
													if(LoaiTaiKhoanRs != null)
													{
														try
														{
															while(LoaiTaiKhoanRs.next())
															{
																if(tkktList.getLoaiTaiKhoanId().equals(LoaiTaiKhoanRs.getString("pk_seq")))
																{%>
																	<option value="<%= LoaiTaiKhoanRs.getString("pk_seq") %>"   selected="selected"><%= LoaiTaiKhoanRs.getString("ten") %></option> 								
																<%}else{ %>
																	<option value="<%=LoaiTaiKhoanRs.getString("pk_seq") %>" ><%= LoaiTaiKhoanRs.getString("ten") %></option> 
																<% }
															}
														}
														catch(SQLException ex){}
													}
													%>
												</select>
										    </TD>
										</TR>

									   <TR>
											<TD class="plainlabel" ><%=Utility.GLanguage("Tr???ng th??i",session,jedis) %></TD>
											<TD class="plainlabel" >
											  <select name="TrangThai" id="TrangThai" onchange="Submitform()">
											    <% if (tkktList.getTrangThai().equals("1")){%>
											  	<option value="1" selected><%=Utility.GLanguage("Ho???t ?????ng",session,jedis) %></option>
											  	<option value="0"><%=Utility.GLanguage("Ng??ng ho???t ?????ng",session,jedis) %></option>
											  	<option value=""> </option>
											  
											<%}else if(tkktList.getTrangThai().equals("0")) {%>
											 	 <option value="0" selected><%=Utility.GLanguage("Ng??ng ho???t ?????ng",session,jedis) %></option>
											  	<option value="1" ><%=Utility.GLanguage("Ho???t ?????ng",session,jedis) %></option>
											 	 <option value="" > </option>
											  
											<%}else{%>																					 	 
											  	<option value="1" ><%=Utility.GLanguage("Ho???t ?????ng",session,jedis) %></option>
											  	<option value="0" ><%=Utility.GLanguage("Ng??ng ho???t ?????ng",session,jedis) %></option>
											  	<option value="" selected> </option>
											<%}%>
									          </select>
									          </TD>
										</TR>
										<TR>
                                             <TD class="plainlabel" colspan=5> 
                                             <a class="button2" href="javascript:clearform()">
												<img style="top: -4px;" src="../images/button.png" alt=""><%=Utility.GLanguage("Nh???p l???i",session,jedis) %></a>&nbsp;&nbsp;&nbsp;&nbsp;	
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
			<TABLE width="100%" cellpadding="0" cellspacing="1">
			    <TR>
					<TD align="left" >
						 <FIELDSET>
							<LEGEND class="legendtitle">&nbsp;T??i kho???n k??? to??n&nbsp;&nbsp;&nbsp;
						</LEGEND>
				
							<TABLE class="" width="100%" border="0" cellpadding="0" cellspacing="0">
								<TR>
									<TD width="98%">
										<TABLE width="100%" border="0" cellspacing="1" cellpadding="4">
											<TR class="tbheader">
												<TH align = "center" >S??? hi???u </TH>
												<TH align = "center" >T??n </TH>
											  	<TH align = "center" >Lo???i </TH>
											  	<TH align = "center" >C?? chi ti???t</TH>
											  	<TH align = "center" ><%=Utility.GLanguage("Tr???ng th??i",session,jedis) %></TH>
											  	<TH align = "center" ><%=Utility.GLanguage("T??c v???",session,jedis) %></TH>
										  </TR>									
												<%
												if(TaiKhoanRs != null)
												{
													try
													{
														int m = 0;
														String lightrow = "tblightrow";
														String darkrow = "tbdarkrow";
														while (TaiKhoanRs.next())
														{
															if (m % 2 != 0) 
															{%>						
																<TR class= <%=lightrow%> >
															<%} 
															else 
															{%>
																<TR class= <%= darkrow%> >
															<%}%>
															<TD align="center"><%= TaiKhoanRs.getString("SoHieuTaiKhoan") %></TD>
															<TD align="left"><%= TaiKhoanRs.getString("TenTaiKhoan") %></TD>
															<TD align="center"><%= TaiKhoanRs.getString("LoaiTaiKhoan") %></TD>		
														
															<TD align="center"><%=	TaiKhoanRs.getString("TaiKhoanCoChiTiet")%></TD>
															<TD align="center"><%=	TaiKhoanRs.getString("TrangThai")%></TD>
															<TD align="center" >
															<TABLE border = "0" cellpadding="0" cellspacing="0">
																<TR>
			                                                		<TD>
																	<!-- QUYEN VIEW DLN -->
																  		<A href="../../TaikhoanktUpdateSvl?task=display&id=<%= TaiKhoanRs.getString("PK_SEQ") %>">
			                                               				<img title="Hi???n th???" src="../images/Display20.png" title="Hi???n th???" alt="Hien thi" width="20" height="20" longdesc="Hien thi" border = 0></A>
			                                               			<!-- END QUYEN VIEW DLN -->
																	</TD>
																</TR>
																</TABLE>
															</TD>
														</TR>
														<% m++; 
			                                              }
														}
														catch(SQLException ex)
														{ 	System.out.print( "Exception "+ex.getMessage()); 
															ex.printStackTrace();
														}
													}
														%>
																 
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
</body>
<%
	if(TaiKhoanRs != null) TaiKhoanRs.close();
	if(CongTyRs != null)	CongTyRs.close();
	if(LoaiTaiKhoanRs != null) LoaiTaiKhoanRs.close();
	tkktList.closeDB();
	session.setAttribute("tkktList",null);
}%>
</HTML>