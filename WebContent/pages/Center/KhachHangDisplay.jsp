<%@page import="java.sql.SQLException"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page  import = "geso.dms.distributor.beans.khachhang.IKhachhang" %>
<%@ page  import = "java.sql.ResultSet" %>
<%@ page  import = "java.util.Hashtable" %>
<%@ page  import = "geso.dms.center.util.*" %>

<%	String userId = (String) session.getAttribute("userId");  
	String userTen = (String) session.getAttribute("userTen");  	
	String sum = (String) session.getAttribute("sum");
	Utility util = (Utility) session.getAttribute("util");
	
	util.getIdNhapp(userId);
	
	if(!util.check(userId, userTen, sum)){
		response.sendRedirect(request.getContextPath() + "/redirect.jsp");
	}else{ %>
<% IKhachhang khBean = (IKhachhang)session.getAttribute("khBean"); %>
<% ResultSet tp = (ResultSet)khBean.getTp() ;  %>
<% ResultSet qh = (ResultSet)khBean.getQh() ;  %>
<% ResultSet hch = (ResultSet)khBean.getHangcuahang(); %>
<% ResultSet kbh = (ResultSet)khBean.getKenhbanhang();  %>

<% ResultSet vtch = (ResultSet)khBean.getVtcuahang();  %>
<% ResultSet lch = (ResultSet)khBean.getLoaicuahang(); %>
<% ResultSet nch = (ResultSet)khBean.getNhomcuahang();  %>
<% ResultSet mck = (ResultSet)khBean.getMucchietkhau();  %>
<% ResultSet nvgn = (ResultSet)khBean.getNvgnRs();  %>
<% ResultSet diadiemRs = (ResultSet)khBean.getDiadiemRs();  %>
<% ResultSet khoRs = (ResultSet)khBean.getKhoRs();%>

<% ResultSet nkh_khList = (ResultSet)khBean.getNkh_khList();  %>
<% Hashtable<Integer, String> nkh_khIds = (Hashtable<Integer, String>)khBean.getNkh_KhIds(); %>

<% ResultSet tbhRs = (ResultSet)khBean.getTbhRs()  ; %>
<% ResultSet ddkdRs = (ResultSet)khBean.getDdkdRs()  ; %>

<% ResultSet dtRs = (ResultSet)khBean.getDtRs()  ; %>

<% ResultSet thanhthinongthonRs = (ResultSet)khBean.getThanhthinongthonRs()  ; %>

<% ResultSet nppRs = (ResultSet)khBean.getNppRs(); %>
<% ResultSet khachhangRs = (ResultSet)khBean.getKhachhangRs(); %>
<% ResultSet nppBanChungRs = (ResultSet)khBean.getNppBanChungRs(); %>
<% ResultSet diabanRs = (ResultSet)khBean.getDiabanRs(); %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"> <% Utility Utilback = new Utility(); %>
<HTML>
<HEAD>
<TITLE><%= getServletContext().getInitParameter("TITLENAME") %></TITLE>
<META http-equiv="Content-Type" content="text/html; charset=utf-8">

<META http-equiv="Content-Style-Type" content="text/css">

<LINK rel="stylesheet" href="../css/main.css" type="text/css">

	<LINK rel="stylesheet" href="../css/datepicker.css" type="text/css">
    <script language="javascript" src="../scripts/datepicker.js"></script>
   	<link href="../css/jquery-ui.css" rel="stylesheet" type="text/css"/>
  	<script type="text/javascript" src="../scripts/Timepicker/jquery.min.js"></script>
  	<script type="text/javascript" src="../scripts/Timepicker/jquery-ui.min.js"></script>

	<script type="text/javascript" src="../scripts/jquery.min.1.7.js"></script>
	<link href="../css/jquery-ui.css" rel="stylesheet" type="text/css" />
	<script src="../scripts/ui/jquery.ui.core.js" type="text/javascript"></script>
	<script src="../scripts/ui/jquery.ui.widget.js" type="text/javascript"></script>
	<script type="text/javascript" src="../scripts/phanTrang.js"></script>
	<script src="../scripts/ui/jquery.ui.datepicker.js" type="text/javascript"></script>
	<link media="screen" rel="stylesheet" href="../css/colorbox.css">
	<script src="../scripts/colorBox/jquery.colorbox.js"></script>

	<script type="text/javascript">
		$(document).ready(function() {		
				$( ".days" ).datepicker({			    
						changeMonth: true,
						changeYear: true				
				});      
				
				$(".taomoitc").colorbox({width:"45%", inline:true, href:"#taomoitc"});
		        //Example of preserving a JavaScript event for inline calls.
		        $("#click").click(function(){ 
		            $('#click').css({"background-color":"#f00", "color":"#fff", "cursor":"inherit"}).text("Bibica - Project.");
		            return false;
		        });
				
	        }); 		
			
	</script>
   

<SCRIPT language="javascript" type="text/javascript">

</SCRIPT>
<link href="../css/select2.css" rel="stylesheet" />
	<script src="../scripts/select2.js"></script>
	<script>
    	$(document).ready(function() { 
    		$("select:not(.notuseselect2)").select2({ width: 'resolve' });     
    	});
    </script>

</HEAD>
<BODY leftmargin="0" bottommargin="0" topmargin="0" rightmargin="0">
	<form name="khForm" method="post" action="../../KhachhangUpdateSvl">
		<input type="hidden" name="userId" value='<%=userId %>'> 
		<input type="hidden" name="nppId"  value='<%= khBean.getNppId() %>' >
		<input type="hidden" name="action" value='1'> 
		<input type="hidden" name="type" value='<%=khBean.gettype()%>'>
<input type="hidden" name="id" value='<%=khBean.getId()%>'>

		<TABLE width="100%" border="0" cellspacing="0" cellpadding="0"
			height="100%">
			<TR>
				<TD colspan="4" align='left' valign='top' bgcolor="#FFFFFF">
					<TABLE width="100%" border="0" cellpadding="0" cellspacing="2">
						<TR>
							<TD align="left" class="tbnavigation">
								<table width="100%" border="0" cellpadding="0" cellspacing="0">
									<tr height="22">
											<TD align="left" colspan="2" class="tbnavigation">&nbsp;
											D??? li???u n???n> D??? li???u Kinh doanh > Kh??ch h??ng > Hi???n th???
							 		  <TD colspan="2" align="right" class="tbnavigation">Ch??o m???ng  <%=userTen%> &nbsp;</TD>
									</tr>
								</table></TD>
						</TR>
					</TABLE>
					<TABLE width="100%" cellpadding="0" cellspacing="2">
						<TR>
							<TD>
								<TABLE width="100%" border="0" cellpadding="0" cellspacing="0">
									<TR class="tbdarkrow">
										<TD width="30" align="left"><A
											href="../../KhachhangTTSvl?userId=<%=userId %>"><img
												src="../images/Back30.png" alt="Quay ve" title="Quay ve"
												width="30" height="30" border="1" longdesc="Quay ve"
												style="border-style: outset">
										</A>
										</TD>
										<TD width="2" align="left"></TD>
										<TD width="30" align="left">
											</TD>
										<TD align="left">&nbsp;</TD>
									</TR>
								</TABLE></TD>
						</TR>
					</TABLE>
					<TABLE width="100%" border="0" cellpadding="0" cellspacing="0">
						<tr>
							<TD align="left" colspan="4" class="legendtitle">
								<FIELDSET>
									<LEGEND class="legendtitle"><%=Utility.GLanguage("B??o l???i nh???p li???u",session,jedis) %></LEGEND>
									<textarea name="dataerror"
										style="width: 100%; color: #F00; font-weight: bold"
										style="width:100%" rows="1"><%= khBean.getMessage() %></textarea>
									<%khBean.setMessage(""); %>
								</FIELDSET></TD>
						</tr>

						<TR>
							<TD height="100%" width="100%">
								<FIELDSET>
									<LEGEND class="legendtitle" style="color: black">Th??ng tin kh??ch h??ng</LEGEND>
									<TABLE border="0" width="100%" cellpadding="6" cellspacing="0">
									
									<%if(1==1) {%>
									<TR>
											
											<TD  class="plainlabel"   > 
												?????a b??n
											</TD>
											<TD  class="plainlabel"  colspan="5" > 
												<SELECT name="DiabanId" id="DiabanId"
													onChange="submitform();">
														<option value=""></option>
														<%
							                             if (diabanRs != null){
							                            	 while (diabanRs.next()){                      				                       				
							                       				 if(diabanRs.getString("pk_seq").equals(khBean.getDiabanId())){ %>
							                       				<option   value ="<%= diabanRs.getString("pk_seq") %>" selected="selected"> <%=diabanRs.getString("TEN") %></option>
							                       				<%}else{ %>
							                       				<option value ="<%=diabanRs.getString("pk_seq") %>"> <%=diabanRs.getString("TEN") %></option>
							                       				<%}     		
					                            	 }
					                             } %>
												</SELECT>
											</TD>
									</TR>
									
									<%} %>
									

										<TR>
											<TD  class="plainlabel">T??n kh??ch h??ng<FONT class="erroralert"> *</FONT>
											</TD>
											<TD class="plainlabel"><INPUT type="text" name="khTen" id="khTen" value="<%= khBean.getTen() %>">
											</TD>
											
											<TD class="plainlabel">Lo???i kh??ch h??ng</TD>
											<TD  class="plainlabel"  >
												<SELECT name="lchId" id="lchId" onChange="submitform();">
													<option value=""></option>
													<%  if(lch!=null)
														{
															try
															{
																while(lch.next())
																{ 
											    					if(lch.getString("lchId").equals(khBean.getLchId())){ 	
											    			%>
																<option value='<%= lch.getString("lchId") %>' selected><%=lch.getString("lchTen") %></option>
																<%}else{ %>
																<option value='<%= lch.getString("lchId") %>'><%= lch.getString("lchTen") %></option>
																<%}} lch.close(); }catch(java.sql.SQLException e){e.printStackTrace();} 
														}	%>	
												</SELECT>
											</TD>

											<TD class="plainlabel">M?? kh??ch h??ng<FONT class="erroralert"> *</FONT></TD>
											<TD class="plainlabel" ><INPUT  type="text" name="maFAST" value="<%= khBean.getMaFAST() %>"></TD>																																							
										</TR>
										<TR>
											<TD class="plainlabel">Ng?????i mua h??ng</TD>
											<TD class="plainlabel"><INPUT type="text"
												name="chucuahieu" id="chucuahieu"
												value="<%= khBean.getChucuahieu() %>" size="40">
											</TD>
											<TD class="plainlabel">?????a ch??? xu???t H??<FONT
												class="erroralert"> *</FONT>
											</TD>
											
											<TD  class="plainlabel" ><INPUT type="text"
												name="diachi" id="diachi" value="<%= khBean.getDiachi() %>"
												size="40"> 
											</TD>
											
											
											<%-- <TD class="plainlabel">?????a ch??? Giao H??ng
											</TD>
											
											<TD  class="plainlabel" ><INPUT type="text"
												name="DiaChiGiaoHang" id="DiaChiGiaoHang" value="<%= khBean.getDiachigiaohang() %>"
												size="40">  --%>
											<TD class="plainlabel" colspan="2">
											Ho???t ?????ng <%  if (khBean.getTrangthai().equals("1")){%>
												<input name="trangthai" type="checkbox" value="1"
												checked="checked"> <%} else {%> <input
												name="trangthai" type="checkbox" value="0"> <%} %>
											
											</TD>
											
										</TR>
										<TR>
											<TD class="plainlabel">T???nh /Th??nh ph???<FONT
												class="erroralert"> *</FONT>
											</TD>
											<TD class="plainlabel"><SELECT name="tpId" id="tpId"
												onChange="submitform();">
													<option value=""></option>
													<% 
													if(tp!=null)
													{
														try{while(tp.next()){ 
													    	if(tp.getString("tpId").equals(khBean.getTpId())){ %>
																		<option value='<%=tp.getString("tpId")%>' selected><%=tp.getString("tpTen") %></option>
																		<%}else{ %>
																		<option value='<%=tp.getString("tpId")%>'><%=tp.getString("tpTen") %></option>
																		<%}}}catch(java.sql.SQLException e){}
																		
													}
													
													%>
											</SELECT></TD>

											<TD class="plainlabel">Qu???n/Huy???n <FONT
												class="erroralert"> *</FONT>
											</TD>
											<TD  class="plainlabel"><SELECT name="qhId"
												id="qhId" onChange="submitform();">
													<option value=""></option>
													<%if(qh != null){ 
								      		try{while(qh.next()){ 
								    			if(qh.getString("qhId").equals(khBean.getQhId())){ %>
													<option value='<%=qh.getString("qhId")%>' selected><%=qh.getString("qhTen") %></option>
													<%}else{ %>
													<option value='<%=qh.getString("qhId")%>'><%=qh.getString("qhTen") %></option>
													<%}}}catch(java.sql.SQLException e){} 
								     		
								      		}	%>

											</SELECT></TD>

											
											<TD class="plainlabel">Ph?????ng x??<FONT class="erroralert"></FONT></TD>
											<TD class="plainlabel"><INPUT TYPE = "text" name="phuongxa" value="<%=khBean.getPhuongxa()%>"/>
												<%-- <SELECT name="phuongxaId"
													id="phuongxaId">
														<option value="null"></option>
														<%if(phuongxaRs != null){ 
									      		try{while(phuongxaRs.next()){ 
									    			if(phuongxaRs.getString("pk_seq").equals(khBean.getPhuongxaId())){ %>
														<option value='<%=phuongxaRs.getString("pk_seq")%>' selected><%=phuongxaRs.getString("ten") %></option>
														<%}else{ %>
														<option value='<%=phuongxaRs.getString("pk_seq")%>'><%=phuongxaRs.getString("ten") %></option>
														<%}}}catch(java.sql.SQLException e){} 
									     		
									      		}	%>
	
												</SELECT>
												
												<a class="taomoitc" href="#">
			                        			<img src="../images/New.png" width="20" height="20" style="margin-bottom:-5; margin-left:7px;" title="T???o m???i ph?????ng/x??"></a>
								                <div style='display:none'>
							                        <div id="taomoitc" style='padding:0px 5px; background:#fff;'>
							                        	<h4 align="left" style="text-decoration: underline;"><%=Utility.GLanguage("T???o m???i",session,jedis) %> ph?????ng x??</h4>
														<table cellpadding="4px" cellspacing="0px" width="100%" align="center">
														
							                            	<tr>
							                                	<TD class="plainlabel" width="40%" valign="top" align="left"><%=Utility.GLanguage("Di???n gi???i",session,jedis) %></td>
							                                    <td class="plainlabel" valign="top" align="left">
								                                    <input type="text" name="pxTen" id="pxTen" value="" />
							                                    </td>
							                                </tr>
							                                 <tr>
							                                	<td class="plainlabel" valign="top" align="left" colspan="2">
							        								<a class="btn btn-default" href="javascript:submitForm2();">L??u l???i</a>
							        							</td>
							                                </tr>
							                            </table>
													</div>
								                </div> --%>
												
											</TD>


										</TR>


										<TR>
											<TD class="plainlabel">??i???n tho???i</TD>
											<TD class="plainlabel"><INPUT type="text"
												name="dienthoai" value="<%= khBean.getSodienthoai() %>"
												size="15">
											</TD>

											<TD class="plainlabel">M?? s??? thu???</TD>
											<TD colspan="3" class="plainlabel"><INPUT type="text"
												name="masothue" value="<%= khBean.getMasothue() %>"
												size="15">
											</TD>
											<%-- <TD class="plainlabel">Th??nh th??? n??ng th??n<FONT class="erroralert">*</FONT></TD>
											<TD  class="plainlabel">
												<SELECT name="thanhthinongthonId"
													id="thanhthinongthonId">
														<option value="null"></option>
														<%if(thanhthinongthonRs != null){ 
									      		try{
									      			while(thanhthinongthonRs.next()){ 
									    			if(thanhthinongthonRs.getString("pk_seq").equals(khBean.getThanhthinongthonId())){ %>
														<option value='<%=thanhthinongthonRs.getString("pk_seq")%>' selected><%=thanhthinongthonRs.getString("ten") %></option>
														<%}else{ %>
														<option value='<%=thanhthinongthonRs.getString("pk_seq")%>'><%=thanhthinongthonRs.getString("ten") %></option>
														<%}}}catch(java.sql.SQLException e){} 
									     		
									      		}	%>
	
												</SELECT>
											</TD> --%>

										</TR>

										<TR>
											<TD class="plainlabel"><%=Utility.GLanguage("K??nh b??n h??ng",session,jedis) %><FONT
												class="erroralert">*</FONT>
											</TD>
											<TD class="plainlabel"><SELECT name="kbhTen" id="kbhTen"
												onChange="submitform();">

													<% 
													if(kbh!=null)
													{
														try{while(kbh.next()){ 
													    	if(kbh.getString("kbhId").equals(khBean.getKbhId())){ %>
																		<option value='<%= kbh.getString("kbhId") %>' selected><%=kbh.getString("kbhTen") %></option>
																		<%}else{ %>
																		<option value='<%= kbh.getString("kbhId") %>'><%= kbh.getString("kbhTen") %></option>
																		<%}}}catch(java.sql.SQLException e){} 
																		
													}
													
								     				%>
											</SELECT>
											</TD>
											<%-- <TD class="plainlabel">Thanh to??n Th??ng</TD>
											<TD class="plainlabel" colspan="2"><SELECT
												name="thanhtoan" id="thanhtoan">
													<option value=""></option>
													<%	if(khBean.getThanhtoan().equals("0") ){ %>
													<option value='0' selected="selected">Ti???n m???t</option>
													<option value='1'>H??a ????n</option>
													<% }else { %>
													<option value='0'>Ti???n m???t</option>
													<option value='1' selected="selected">H??a ????n</option>
													<% } %>
											</SELECT> &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; Thanh to??n Qu??
												&nbsp;&nbsp; <SELECT name="thanhtoanQUY" id="thanhtoanQUY">
													<option value=""></option>
													<%	if(khBean.getThanhtoanQuy().equals("0") ){ %>
													<option value='0' selected="selected">Ti???n m???t</option>
													<option value='1'>H??a ????n</option>
													<% }else { %>
													<option value='0'>Ti???n m???t</option>
													<option value='1' selected="selected">H??a ????n</option>
													<% } %>
											</SELECT></TD> --%>
											
											<TD class="plainlabel">CMND</TD>
											<TD class="plainlabel" colspan="3"><INPUT type="text"
												name="cmnd" value="<%=khBean.getCmnd() %>"
												>
											</TD>
	
											<%-- <TD class="plainlabel">??i???n tho???i di ?????ng</TD>
											<TD class="plainlabel" colspan="3"><INPUT type="text"
												name="didong" value="<%=khBean.getDidong() %>"
												>
											</TD> --%>
											
										</TR>

										<TR>
											
											
											<TD  class="plainlabel" style='display:none'><SELECT name="xuatkhau">
												<option value="0"></option>
													
													<%-- <% if(khBean.getXuatkhau().equals("0") ) { %>
													<option value="0" selected="selected">B??n l???</option>
													<% } else { %>
													<option value="0">B??n l???</option>
													<% } %>
													<% if(khBean.getXuatkhau().equals("1") ) { %>
													<option value="1" selected="selected">B??n bu??n</option>
													<% } else { %>
													<option value="1">B??n bu??n</option>
													<% } %>
													<% if(khBean.getXuatkhau().equals("2") ) { %>
													<option value="2" selected="selected">B??n bu??n v??
														b??n l???</option>
													<% } else { %>
													<option value="2">B??n bu??n v?? b??n l???</option>
													<% } %> --%>
													<%-- <% if(khBean.getXuatkhau().equals("3") ) { %>
													<option value="3" selected="selected">B??n l??? v??
														b??n bu??n</option>
													<% } else { %>
													<option value="3">B??n l??? v?? b??n bu??n</option>
													<% } %> --%>

											</SELECT></TD>

									

											<%-- <TD class="plainlabel">H???p ?????ng</TD>
											<TD class="plainlabel" colspan="2"><INPUT type="text"
												name="hopdong" value="<%= khBean.getHopdong() %>" size="15">

											</TD> --%>
											<TD class="plainlabel" colspan="9"/TD>

										</TR>
										
										<TR>
											<TD class="plainlabel">Nh??n vi??n giao nh???n<FONT
												class="erroralert">*</FONT>
											</TD>
											<TD class="plainlabel"><SELECT name="nvgnTen" id="nvgnTen">
													<OPTION value="" ></OPTION>
													<% 
													if(nvgn!=null)
													{
														try
														{
															while(nvgn.next())
															{ 
										    					if(khBean.getNvgnId().indexOf(nvgn.getString("nvgnId"))>=0){ %>
														<option value='<%= nvgn.getString("nvgnId") %>' selected><%=nvgn.getString("nvgnTen") %></option>
														<%}else{ %>
														<option value='<%= nvgn.getString("nvgnId") %>'><%= nvgn.getString("nvgnTen") %></option>
														<%}} nvgn.close(); }catch(java.sql.SQLException e){e.printStackTrace();} 
														
													}
													
									     			%>
											</SELECT></TD>
											
											<TD class="plainlabel">Ph???n tr??m chi???t kh???u</TD>
											<TD class="plainlabel"><input type="text"
												name="ptCHIETKHAU" value="<%= khBean.getPT_Chietkhau() %>"
												style="text-align: right;"> %

											<TD class="plainlabel">Kho<FONT class="erroralert">*</FONT></TD>
												
												<TD class="plainlabel" >
													<SELECT name="khoId" id ="khoId" >
													<!-- <OPTION value="" selected></OPTION> -->
													<% 
													if(khoRs!=null)
													{
														try{while(khoRs.next()){ 
													    	if(khoRs.getString("PK_SEQ").equals(khBean.getkhoId())){ %>
																	<option value='<%= khoRs.getString("PK_SEQ") %>' selected><%=khoRs.getString("Ten") %></option>
																	<%}else{ %>
																	<option value='<%= khoRs.getString("PK_SEQ") %>'><%= khoRs.getString("Ten") %></option>
																	<%}} khoRs.close(); }catch(java.sql.SQLException e){} 
																	
													}
													
									    			 %>
													</SELECT>
												</TD>
										</TR>

										<TR>
											
											

											<TD class="plainlabel"><%=Utility.GLanguage("NH??N VI??N B??N H??NG",session,jedis) %></TD>
											<TD class="plainlabel">
											<SELECT  name="ddkdId" onChange="submitform();" multiple="multiple">
													<option value=""></option>
													<% 
													if(ddkdRs!=null)
													{
														try{ while(ddkdRs.next()){ 
											    			if( khBean.getDdkdId().indexOf(ddkdRs.getString("ddkdId")) >=0){ %>
															<option value='<%=ddkdRs.getString("ddkdId")%>' selected>
																<%=ddkdRs.getString("ddkdTen") %></option>
															<%}else{ %>
															<option value='<%=ddkdRs.getString("ddkdId")%>'>
																<%=ddkdRs.getString("ddkdTen") %></option>
															<%}}}catch(java.sql.SQLException e){} 
															
													}
													
													%>
											</SELECT>
											</TD>
											<TD  class="plainlabel">NPP Kh??c
											</TD>
											<TD class="plainlabel" colspan="3">
												<SELECT  name="nppBanChungId"  multiple="multiple">
														<option value=""></option>
														<% 
														if(nppBanChungRs!=null)
														{
															try{ while(nppBanChungRs.next()){ 
												    			if(Utility.ktDaChonTrongRs(nppBanChungRs.getString("pk_seq"),khBean.getNppBanChungId(),",")){ %>
																<option value='<%=nppBanChungRs.getString("pk_seq")%>' selected>
																	<%=nppBanChungRs.getString("ten") %></option>
																<%}else{ %>
																<option value='<%=nppBanChungRs.getString("pk_seq")%>'>
																	<%=nppBanChungRs.getString("ten") %></option>
																<%}}}catch(java.sql.SQLException e){} 
																
														}
														
														%>
												</SELECT>
											</TD>
										</TR>
										
										
										<TR>
											<TD class="plainlabel">M?? S??? Thu??? C?? Nh??n</TD>
											<TD class="plainlabel" >
												<INPUT type="text" name="mst_canhan" value="<%= khBean.getMst() %>" size="15">
											</TD>


											<TD class="plainlabel">Ng??y Sinh</TD>
											<TD class="plainlabel">
											<INPUT type="text" class="days" name="ngaysinh" value="<%= khBean.getNgaysinh() %>" >

											</TD>
											
											
											<TD class="plainlabel">H???ng kh??ch h??ng</TD>
											<TD class="plainlabel"><SELECT name="hangcuahangId">
													<option value=""></OPTION>
													<% if(hch!=null) 
													while(hch.next())
													{
														if(khBean.getHchId()!=null)
														{
															if(khBean.getHchId().indexOf(hch.getString("hchId")) >= 0)
															{
																%>
																<option value="<%= hch.getString("hchId") %>" selected><%= hch.getString("hchTen") %></option>														
																<%
															}
															else
															{
																%>
																<option value="<%= hch.getString("hchId") %>"><%= hch.getString("hchTen") %></option>														
																<%
															}
														}
														else
														{
															%>
															<option value="<%= hch.getString("hchId") %>"><%= hch.getString("hchTen") %></option>														
															<%
														}

													}
													
													%>
											</SELECT></TD>
											
										</TR>


										<tr>
											<%-- <TD class="plainlabel">T??n k?? h???p ?????ng:	</TD>
											<TD class="plainlabel"><INPUT type="text"
												name="tenkyhd" id="tenkyhd" value="<%= khBean.getTenKyHd() %>"
												size="40">
											</TD>	 --%>
											
											<TD class="plainlabel" colspan="6"></TD>							

										</TR>
										<TR>
											<% if(khBean.getKbhId().equals("100052")) {%>
											<TD class="plainlabel">M???u h??a ????n</TD>
											<TD class="plainlabel" colspan="10"><SELECT
												name="mauhoadon" id="mauhoadon">
													<% if (khBean.getmauhd().equals("1")){%>
													<option value="1" selected>M???u 1(CN)</option>
													<option value="2">M???u 2(HO)</option>
													<%}else if(khBean.getmauhd().equals("2")) {%>
													<option value="1" >M???u 1(CN)</option>
													<option value="2" selected>M???u 2(HO)</option>
													<%} else {%>
													<option value="1" >M???u 1(CN)</option>
													<option value="2">M???u 2(HO)</option>
													<%} %>
											</SELECT></TD>
											<%} else {%>
											<!-- <TD class="plainlabel" colspan="10"></TD> -->
											<%} %>
										</TR>
										
										
										<TR style ="display: none">
											<TD class="plainlabel">S??? ng??y n???</TD>
											<TD class="plainlabel"><INPUT type="text"
												name="songayno" value="<%=khBean.getSongayno() %>"
												size="15">
											</TD>
	
											<TD class="plainlabel">S??? ti???n n???</TD>
											<TD class="plainlabel"><INPUT type="text"
												name="sotienno" value="<%=khBean.getSotienno() %>"
												size="11">
											</TD>
											
											<TD class="plainlabel">H??nh th???c thanh to??n</TD>
											<TD class="plainlabel"><INPUT type="text"
												name="hinhthuctt" value="<%=khBean.getHinhthucTT() %>" size="11" readonly="readonly">
											</TD>
										</TR>
										
										
										
									</TABLE>
								</FIELDSET></TD>
						</TR>



					</TABLE>


					<TABLE width="100%" border="0" cellpadding="0" cellspacing="0">
						<TR>
							<TD colspan="6" width="100%">
								<FIELDSET>
									<LEGEND class="legendtitle">Tuy???n b??n h??ng </LEGEND>
									<TABLE width="100%" border="0" cellspacing="1" cellpadding="6">
										<TR class="tbheader">
											<TH width="10%">S??? th??? t???</TH>
											<TH width="60%">Tuy???n</TH>
											<TH width="20%">T???n s???</TH>
											<TH width="10%">Ch???n</TH>
										</TR>

										<%
								int i = 0;
								String lightrow = "tblightrow";
								String darkrow = "tbdarkrow";
								if(tbhRs != null){
								try{while(tbhRs.next()){ 
									if (i % 2 != 0) {%>
										<TR class=<%=lightrow%>>
											<%} else {%>
										
										<TR class=<%= darkrow%>>
											<%}%>
											<TD align="center"><input name='sott' type="text"
												value="<%=tbhRs.getString("sott") %>" readonly="readonly"
												style="width: 100%">
											</TD>
											<TD align="center"><div align="left"><%= tbhRs.getString("tbhTen") %>
												</div>
											</TD>

											<TD align="center">
												<% 
												String[] tenloaicuahang = new  String[] {"F1-1","F1-2","F1-3","F1-4","F2-1","F2-2","F4","F8"} ; 
									  			String[] idloaicuahang = new  String[] {"F1-1","F1-2","F1-3","F1-4","F2-1","F2-2","F4","F8"}  ;
								  %> <select style="width: 130px" name="tanso">
													<option value=""></option>
													<% for( int j=0;j<idloaicuahang.length;j++) { 
								    			if(idloaicuahang[j].equals(tbhRs.getString("tanso" ))){ %>
													<option value='<%=idloaicuahang[j]%>' selected><%=tenloaicuahang[j] %></option>
													<%}else{ %>
													<option value='<%=idloaicuahang[j]%>'><%=tenloaicuahang[j] %></option>
													<%} 
								      		 }
								       	%>
											</select></TD>

											<% if (khBean.getTbhId()!=null&&khBean.getTbhId().contains(tbhRs.getString("tbhId"))){ %>
											<TD align="center"><input name="tbhId" id = "tbhId"
												type="checkbox" value="<%= tbhRs.getString("tbhId") %>"
												checked>
											</TD>
											<%}else{%>
											<TD align="center"><input name="tbhId" id = "tbhId"
												type="checkbox" value="<%= tbhRs.getString("tbhId") %>">
											</TD>
											<%}%>

										</TR>
										<%i++;}}catch(java.sql.SQLException e){e.printStackTrace();}
								}
							  %>
										<tr class="tbfooter">
											<td colspan="4">&nbsp;</td>
										</tr>
									</TABLE>

								</FIELDSET></TD>
						</TR>
					</TABLE>



					<TABLE width="100%" border="0" cellpadding="0" cellspacing="0">
						<TR>
							<TD colspan="6" width="100%">
								<FIELDSET>
									<LEGEND class="legendtitle">Ph??n nh??m </LEGEND>
									<TABLE width="100%" border="0" cellspacing="1" cellpadding="6">
										<TR class="tbheader">
											<TH width="20%">Nh??m kh??ch h??ng</TH>
											<TH width="10%">Ch???n</TH>
										</TR>

										<%
								 i = 0;
								 lightrow = "tblightrow";
								 darkrow = "tbdarkrow";
								if(nkh_khList != null)
								{
								try{while(nkh_khList.next())
								{ 
									if (i % 2 != 0) {%>
										<TR class=<%=lightrow%>>
											<%} else {%>
										
										<TR class=<%= darkrow%>>
											<%}%>
											<TD align="center"><div align="left"><%= nkh_khList.getString("nkhTen") %>
												</div>
											</TD>
											<% if (nkh_khIds.contains(nkh_khList.getString("nkhId"))){ %>
											<TD align="center"><input name="nkh_khList"
												type="checkbox" value="<%= nkh_khList.getString("nkhId") %>"
												checked>
											</TD>
											<%}else{%>
											<TD align="center"><input name="nkh_khList"
												type="checkbox" value="<%= nkh_khList.getString("nkhId") %>">
											</TD>
											<%}%>
										</TR>
										<%i++;}}catch(java.sql.SQLException e){}
								}
							  %>
										<tr class="tbfooter">
											<td colspan="4">&nbsp;</td>
										</tr>
									</TABLE>

								</FIELDSET></TD>
						</TR>
					</TABLE></TD>



			<%-- <legend class="legendtitle" style="color:black">Nh??m nh?? ph??n ph???i</legend>
							<TABLE border="0" width="30%" cellpadding="6" cellspacing="1">
								<TR class="tbheader">
									<TH width="20%">T??n nh??m</TH>
									<TH width="10%">Ch???n t???t c???
									<input type="checkbox" name="chon" onClick="checkedAll(document.nppForm.nhomNppId)">
									</TH>
								</TR>
						<%
							int m = 0;
						/* 	String lightrow = "tblightrow";
							String darkrow = "tbdarkrow"; */

							while (nhomNppRs.next()){							
								if (m % 2 != 0) {%>						
									<TR class= <%=lightrow%> >
								<%} else {%>
									<TR class= <%= darkrow%> >
								<%}%>
										<TD align="left" class="textfont"><%=nhomNppRs.getString("ten") %></TD>
									    <% if (nhomNppRs.getInt("CHON") == 1){ %>
				    					<TD align="center" ><input name="nhomNppId" type="checkbox" value="<%=nhomNppRs.getString("pk_seq")%>" checked></TD>
								<%}else{ %>
										<TD align="center"><input name="nhomNppId" type="checkbox" value="<%=nhomNppRs.getString("pk_seq")%>"></TD>
								<%}%>

							</TR>
								<%
									m++;
								} %>
												
							</TABLE>
						</fieldset>				
					</TD>
			      </TR>
		  	</TABLE>	 --%>


			</TR>
		</TABLE>
	</form>
</BODY>
</HTML>

<% 	

if(khBean != null){
	khBean.DBclose();
	khBean = null;
}

	try{
	if(hch != null)
		hch.close();
	if(kbh != null)
		kbh.close();
	if(lch != null)
		lch.close();
	if(mck != null)
		mck.close();
	if(nkh_khList!= null)
		nkh_khList.close();
	if(tp != null)
		tp.close();
	if(qh != null)
		qh.close();
	if(vtch != null)
		vtch.close();
	if(nch!=null){
		nch.close();
	}
	if(nvgn!=null){
		nvgn.close();
	}
	if(nkh_khList!=null){
		nkh_khList.close();
	}
	if(nkh_khIds!=null){
		nkh_khIds.clear();
	}
	
	if(thanhthinongthonRs!=null){
		thanhthinongthonRs.close();
	}
	
	session.setAttribute("khBean",null);
	}
	catch (SQLException e) {}
	
%>
<%}%>
