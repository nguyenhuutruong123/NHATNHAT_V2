<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page  import = "geso.dms.center.beans.tieuchithuong.ITieuchithuongList" %>
<%@ page  import = "java.util.Hashtable" %>
<%@ page  import = "java.sql.ResultSet" %>
<%@ page  import = "geso.dms.center.util.*" %>
<%@page import="java.util.Calendar"%>
<%	String userId = (String) session.getAttribute("userId");  
	String userTen = (String) session.getAttribute("userTen");  	
	String sum = (String) session.getAttribute("sum");
	Utility util = (Utility) session.getAttribute("util");
	if(!util.check(userId, userTen, sum)){
		response.sendRedirect(request.getContextPath() + "/redirect.jsp");
	}else{ %>

<% 	ITieuchithuongList obj = (ITieuchithuongList)session.getAttribute("obj"); 

	ResultSet dvkd = obj.getdvkd();
	String dvkdId = obj.getdvkdId();	

	ResultSet kbh = obj.getKbh();
	String kbhId = obj.getkbhId();	
	
	ResultSet tct = obj.getTct();
	int[] quyen = new  int[5];
	System.out.println("userid======="+obj.getUserId());
	System.out.println("msg======="+obj.getMsg());
	
	quyen = util.Getquyen("TieuChiThuongSvl","",userId);
	
	System.out.println(quyen[0]);
	System.out.println(quyen[1]);
	System.out.println(quyen[2]);
	System.out.println(quyen[3]);
	System.out.println(quyen[4]);
	String url = util.getChuyenNguUrl("TieuchithuongSvl", "",session);
%>
<% Utility Util = new Utility(); %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"> <% Utility Utilback = new Utility(); %>
<HTML>
<HEAD>
<TITLE><%= getServletContext().getInitParameter("TITLENAME") %></TITLE>
<META http-equiv="Content-Type" content="text/html; charset=utf-8">

<META http-equiv="Content-Style-Type" content="text/css">

<LINK rel="stylesheet" href="../css/main.css" type="text/css">
<link type="text/css" rel="stylesheet" href="../css/mybutton.css">
<SCRIPT language="javascript" type="text/javascript">
function submitForm()
{	
	
	document.tctForm.action.value='timkiem';
    document.forms["tctForm"].submit();
}
 
function saveForm()
{	
	
	document.tctForm.action.value='taomoi';
    document.forms["tctForm"].submit();
}
function thongbao()
{
	if(document.getElementById("msg").value.trim().length >0)
		alert(document.getElementById("msg").value);
}
/*  function checklimit(m)
 {
	 var boo = false;
 	 if(document.getElementById("ngaysua"+m).value != "")
 		{		
 			var now = new Date();
 			var month = now.getMonth() + 1;
 			var date = now.getDate();
 			if(month < 10)
 			{month = "0"+month;}	
 			if(date < 10)
 			{date = "0"+date;}
 			var ngaysua = Date.parse(document.getElementById("ngaysua"+m).value);
 			var ngayhientai = Date.parse(now.getFullYear()+"-"+month+"-"+date);	
 			if(ngayhientai - ngaysua > 0)
 			{
 				
 				alert('Tiêu chí này đã hết hạn mở chốt. Vui lòng liên hệ quản trị !');
 				return;
 			}
 			boo = true;
 			
 	}
 	// document.forms["ddkdForm"].submit();
 } */


</SCRIPT>
</HEAD>
<BODY leftmargin="0" bottommargin="0" topmargin="0" rightmargin="0">
<form name="tctForm" method="post" action="../../TieuChiThuongSvl">
<% redis.clients.jedis.Jedis jedis =  geso.dms.center.util.Utility.getJedis(); %><% geso.dms.center.util.Csrf csdr=new geso.dms.center.util.Csrf(request,response,true,false,true);%>
 <input type="hidden" name="<%=csdr.get_tokenName() %>" value='<%=csdr.get_tokenValue() %>'>

<input type="hidden" name="userId" value='<%= obj.getUserId() %>'>
<input type="hidden" name="action" value="1">
<input type="hidden" name="loaithuong" value="<%=obj.getLoaithuong()%>">
<input type="hidden" name="msg" id="msg" value='<%=Utility.GLanguage(obj.getMsg(),session,jedis) %>
'>
<script type="text/javascript">
	thongbao();
</script>
<TABLE width="100%" border="0" cellspacing="0" cellpadding="0"	height="100%">
	<TR>
		<TD colspan="4" align='left' valign='top' bgcolor="#FFFFFF">
			<TABLE width="100%" border="0" cellpadding="0" cellspacing="1">
				<TR>
					<TD align="left" class="tbnavigation">
						<table width="100%" border="0" cellpadding="0" cellspacing="0">
						  <tr height="22">
							 <TD align="left" colspan="2" class="tbnavigation"><%= url %></TD>
							 <TD colspan="2" align="right" class="tbnavigation"><%=Utility.GLanguage("Chào mừng bạn",session,jedis) %>  <%=userTen %>&nbsp;  </TD></tr>
						</table>
			 		</TD>
				</TR>
			</TABLE>

			<TABLE width="100%" border="0" cellpadding="0" cellspacing="1" >
				<TR>
					<TD>

						<TABLE width="100%" border="0" cellpadding="0" cellspacing="1" >
							<TR>
								<TD>
									<FIELDSET>
									<LEGEND class="legendtitle" style="color:black"><%=Utility.GLanguage("Tiêu chí tìm kiếm",session,jedis) %> </LEGEND>
	
									<TABLE border="0" width="100%" cellpadding="4" cellspacing="0">
										<TR>
										  	 <TD class="plainlabel" colspan=2>
												<%if(obj.getLoai().equals("1")){ %>									  	 
									  	 			<INPUT TYPE="radio" NAME="loai" value="1" checked onChange="submitForm();"><%=Utility.GLanguage("NVBH",session,jedis) %>
									  	 			<INPUT TYPE="radio" NAME="loai" value="5" onChange="submitForm();"><%=Utility.GLanguage("NPP",session,jedis) %>
									  	 		<%} else if(obj.getLoai().equals("5")){ %>
							  	 					<INPUT TYPE="radio" NAME="loai" value="1"  onChange="submitForm();"><%=Utility.GLanguage("NVBH",session,jedis) %>
							  	 					<INPUT TYPE="radio" NAME="loai" value="5" checked onChange="submitForm();"><%=Utility.GLanguage("NPP",session,jedis) %>
									  	 			<%} %>
									  	 			
									  	 			<%-- <INPUT TYPE="radio" NAME="loai" value="2" onChange="submitForm();">GSBH
									  	 			<INPUT TYPE="radio" NAME="loai" value="3" onChange="submitForm();">ASM
									  	 			<INPUT TYPE="radio" NAME="loai" value="4" onChange="submitForm();">RSM
									  	 			<INPUT TYPE="radio" NAME="loai" value="5" onChange="submitForm();">NPP
									  	 			<INPUT TYPE="radio" NAME="loai" value="6" onChange="submitForm();">Chi Nhánh
									  	 			
									  	 		<%}else{
									  	 				if(obj.getLoai().equals("2")){ %>
									  	 					<INPUT TYPE="radio" NAME="loai" value="1" onChange="submitForm();">NVBH
									  	 					<INPUT TYPE="radio" NAME="loai" value="2" checked onChange="submitForm();">GSBH									  	 		
									  	 					<INPUT TYPE="radio" NAME="loai" value="3" onChange="submitForm();">ASM
									  	 					<INPUT TYPE="radio" NAME="loai" value="4" onChange="submitForm();">RSM
									  	 					<INPUT TYPE="radio" NAME="loai" value="5" onChange="submitForm();">NPP
									  	 					<INPUT TYPE="radio" NAME="loai" value="6" onChange="submitForm();">Chi Nhánh
									  	 			  <%}else{ 
									  	 				 if(obj.getLoai().equals("3")){ %>
									  	 					<INPUT TYPE="radio" NAME="loai" value="1" onChange="submitForm();">NVBH
									  	 					<INPUT TYPE="radio" NAME="loai" value="2" onChange="submitForm();">GSBH
									  	 					<INPUT TYPE="radio" NAME="loai" value="3" checked onChange="submitForm();">ASM
									  	 					<INPUT TYPE="radio" NAME="loai" value="4" onChange="submitForm();">RSM
									  	 					<INPUT TYPE="radio" NAME="loai" value="5" onChange="submitForm();">NPP
									  	 					<INPUT TYPE="radio" NAME="loai" value="6" onChange="submitForm();">Chi Nhánh
									  	 				<%}else if(obj.getLoai().equals("4")){ %>
									  	 					<INPUT TYPE="radio" NAME="loai" value="1"  onChange="submitForm();">NVBH
									  	 					<INPUT TYPE="radio" NAME="loai" value="2" onChange="submitForm();">GSBH
									  	 					<INPUT TYPE="radio" NAME="loai" value="3" onChange="submitForm();">ASM
									  	 					<INPUT TYPE="radio" NAME="loai" value="4" checked  onChange="submitForm();">RSM
									  	 					<INPUT TYPE="radio" NAME="loai" value="5" onChange="submitForm();">NPP
									  	 					<INPUT TYPE="radio" NAME="loai" value="6" onChange="submitForm();">Chi Nhánh
										  	 	<%} else if(obj.getLoai().equals("5")){ %>
								  	 					<INPUT TYPE="radio" NAME="loai" value="1"  onChange="submitForm();">NVBH
								  	 					<INPUT TYPE="radio" NAME="loai" value="2" onChange="submitForm();">GSBH
								  	 					<INPUT TYPE="radio" NAME="loai" value="3" onChange="submitForm();">ASM
								  	 					<INPUT TYPE="radio" NAME="loai" value="4"   onChange="submitForm();">RSM
								  	 					<INPUT TYPE="radio" NAME="loai" value="5" checked onChange="submitForm();">NPP
								  	 					<INPUT TYPE="radio" NAME="loai" value="6" onChange="submitForm();">Chi Nhánh
									  	 	<%}
									  	 		 else if(obj.getLoai().equals("6")){ %>
								  	 					<INPUT TYPE="radio" NAME="loai" value="1"  onChange="submitForm();">NVBH
								  	 					<INPUT TYPE="radio" NAME="loai" value="2" onChange="submitForm();">GSBH
								  	 					<INPUT TYPE="radio" NAME="loai" value="3" onChange="submitForm();">ASM
								  	 					<INPUT TYPE="radio" NAME="loai" value="4"   onChange="submitForm();">RSM
								  	 					<INPUT TYPE="radio" NAME="loai" value="5"  onChange="submitForm();">NPP
								  	 					<INPUT TYPE="radio" NAME="loai" value="6" checked onChange="submitForm();">Chi Nhánh
									  	 	<%}
									  	 				 
									  	 			  }}%> --%>
										  	 </TD>
										</TR>
										<%-- <TR>
							  				<TD class="plainlabel" width = "20%" >Kênh bán hàng </TD>
						  	  				<TD class="plainlabel"  >
												<select name="kbhId" id="kbhId" onChange="submitForm();">
												<option value="" > </option>
												<%
													if(kbh != null){
													try{
														while(kbh.next()){
								  							if (kbhId.equals(kbh.getString("kbhId"))){ %>											
								  								<option value='<%= kbh.getString("kbhId")%>' selected><%= kbh.getString("kbh")%></option>
								  		  					<%}else{ %>		
								  		  						<option value='<%= kbh.getString("kbhId")%>' ><%= kbh.getString("kbh")%></option>
								  							<%		}
								  						}
														kbh.close();
								  					}catch (java.sql.SQLException e){}} %>
															
										  	  	</select>											
										  	 </TD>
										</TR> --%>

										<TR>
							  				<TD class="plainlabel"  ><%=Utility.GLanguage("Đơn vị kinh doanh",session,jedis) %></TD>
						  	  				<TD class="plainlabel"  >
												<select name="dvkdId" id="dvkdId" onChange="submitForm();">
												<option value="" > </option>
												<%
													if(dvkd != null){
													try{
														while(dvkd.next()){
								  							if (dvkdId.equals(dvkd.getString("dvkdId"))){ %>											
								  								<option value='<%= dvkd.getString("dvkdId")%>' selected><%= dvkd.getString("dvkd")%></option>
								  		  					<%}else{ %>		
								  		  						<option value='<%= dvkd.getString("dvkdId")%>' ><%= dvkd.getString("dvkd")%></option>
								  							<%		}
								  						}
														dvkd.close();
								  					}catch (java.sql.SQLException e){}} %>
															
										  	  	</select>											
										  	 </TD>
										</TR>

								<TR>	
									<TD class="plainlabel"><%=Utility.GLanguage("Chọn tháng",session,jedis) %></TD>
									<TD class="plainlabel" >										
										<SELECT name = "month" onChange="submitForm();">
											<option value = "" selected>&nbsp</option>
										<%if(obj.getMonth().equals("01")){ %>
											<option value = "01" selected><%=Utility.GLanguage("T1",session,jedis) %></option>
										<%}else{ %>
											<option value = "01"><%=Utility.GLanguage("T1",session,jedis) %></option>
										<%} %>

										<%if(obj.getMonth().equals("02")){ %>
											<option value = "02" selected><%=Utility.GLanguage("T2",session,jedis) %></option>
										<%}else{ %>
											<option value = "02"><%=Utility.GLanguage("T2",session,jedis) %></option>
										<%} %>

										<%if(obj.getMonth().equals("03")){ %>
											<option value = "03" selected><%=Utility.GLanguage("T3",session,jedis) %></option>
										<%}else{ %>
											<option value = "03"><%=Utility.GLanguage("T3",session,jedis) %></option>
										<%} %>

										<%if(obj.getMonth().equals("04")){ %>
											<option value = "04" selected><%=Utility.GLanguage("T4",session,jedis) %></option>
										<%}else{ %>
											<option value = "04"><%=Utility.GLanguage("T4",session,jedis) %></option>
										<%} %>

										<%if(obj.getMonth().equals("05")){ %>
											<option value = "05" selected><%=Utility.GLanguage("T5",session,jedis) %></option>
										<%}else{ %>
											<option value = "05"><%=Utility.GLanguage("T5",session,jedis) %></option>
										<%} %>

										<%if(obj.getMonth().equals("06")){ %>
											<option value = "06" selected><%=Utility.GLanguage("T6",session,jedis) %></option>
										<%}else{ %>
											<option value = "06"><%=Utility.GLanguage("T6",session,jedis) %></option>
										<%} %>

										<%if(obj.getMonth().equals("07")){ %>
											<option value = "07" selected><%=Utility.GLanguage("T7",session,jedis) %></option>
										<%}else{ %>
											<option value = "07"><%=Utility.GLanguage("T7",session,jedis) %></option>
										<%} %>

										<%if(obj.getMonth().equals("08")){ %>
											<option value = "08" selected><%=Utility.GLanguage("T8",session,jedis) %></option>
										<%}else{ %>
											<option value = "08"><%=Utility.GLanguage("T8",session,jedis) %></option>
										<%} %>
										
										<%if(obj.getMonth().equals("09")){ %>
											<option value = "09" selected><%=Utility.GLanguage("T9",session,jedis) %></option>
										<%}else{ %>
											<option value = "09"><%=Utility.GLanguage("T9",session,jedis) %></option>
										<%} %>
										
										<%if(obj.getMonth().equals("10")){ %>
											<option value = "10" selected><%=Utility.GLanguage("T10",session,jedis) %></option>
										<%}else{ %>
											<option value = "10"><%=Utility.GLanguage("T10",session,jedis) %></option>
										<%} %>
										
										<%if(obj.getMonth().equals("11")){ %>
											<option value = "11" selected><%=Utility.GLanguage("T11",session,jedis) %></option>
										<%}else{ %>
											<option value = "11"><%=Utility.GLanguage("T11",session,jedis) %></option>
										<%} %>
										
										<%if(obj.getMonth().equals("12")){ %>
											<option value = "12" selected><%=Utility.GLanguage("T12",session,jedis) %></option>
										<%}else{ %>
											<option value = "12"><%=Utility.GLanguage("T12",session,jedis) %></option>
										<%} %>
										</SELECT>									
									</TD>
								</TR>
								<TR>
										<TD class="plainlabel"><%=Utility.GLanguage("Chọn năm",session,jedis) %></TD>
									
									<TD class="plainlabel">
										<select name="year" style="width :100px" onChange="submitForm();">
										<option value=""></option>  
										<%
										
										int year_= Utility.getNamHienTai();
										for(int n=year_ - 3; n<year_+5; n++) {
										  if(obj.getYear().equals( Integer.toString(n)) ){									  
										%>
											<option value=<%=n %> selected="selected" > <%=n %></option> 
										<%
										  }else{
										 %>
											<option value=<%=n %> ><%=n %></option> 
										<% } }
										%>
										</select>
									</TD>	
								</TR>
								
								<tr class="plainlabel"> 
									<td colspan="2" > 
                             			<a class="button3" href="javascript:submitForm()">
                           				<img style="top: -4px;" src="../images/Search30.png" alt=""><%=Utility.GLanguage("Tìm kiếm",session,jedis) %> </a>   &nbsp;&nbsp;&nbsp;
	                           		</td>
	                           	</tr>

								</TABLE>
								</FIELDSET>
																	
									<TABLE width = "100%" cellpadding="0" cellspacing="0">
										<TR>
											<TD>
												<FIELDSET>
											 	<%if(obj.getLoai().equals("1") ){ %> 
												<LEGEND class="legendtitle">&nbsp;<%=Utility.GLanguage("Tiêu chí tính thưởng NVBH",session,jedis) %> &nbsp;&nbsp;&nbsp;
												<%if(quyen[Utility.THEM]!=0) {%>
													<a class="button3" href="javascript:saveForm()">
								    				<img style="top: -4px;" src="../images/New.png" ><%=Utility.GLanguage("Tạo mới",session,jedis) %> </a>                            
												<%} %>
												</LEGEND>
												<%}else{ 
													if(obj.getLoai().equals("2") ){%>																		
														<LEGEND class="legendtitle">&nbsp;<%=Utility.GLanguage("Tiêu chi tính thưởng cho quản lý cấp 1",session,jedis) %>&nbsp;&nbsp;&nbsp;
														<%if(quyen[Utility.THEM]!=0) {%>
															<a class="button3" href="javascript:saveForm()">
										    				<img style="top: -4px;" src="../images/New.png" ><%=Utility.GLanguage("Tạo mới",session,jedis) %> </a>                            
														<%} %>
														</LEGEND>
													<%}else{
														if(obj.getLoai().equals("3" ) ){%>								 
														<LEGEND class="legendtitle">&nbsp;<%=Utility.GLanguage("Tiêu chi tính thưởng cho quản lý cấp 2",session,jedis) %>&nbsp;&nbsp;&nbsp;
														<%if(quyen[Utility.THEM]!=0) {%>
															<a class="button3" href="javascript:saveForm()">
										    				<img style="top: -4px;" src="../images/New.png" ><%=Utility.GLanguage("Tạo mới",session,jedis) %> </a>                            
														<%} %>
														</LEGEND>
													<%}else if(obj.getLoai().equals("4")){  %>
														<LEGEND class="legendtitle">&nbsp;<%=Utility.GLanguage("Tiêu chi tính thưởng cho RSM",session,jedis) %>&nbsp;&nbsp;&nbsp;
														<%if(quyen[Utility.THEM]!=0) {%>
															<a class="button3" href="javascript:saveForm()">
										    				<img style="top: -4px;" src="../images/New.png" ><%=Utility.GLanguage("Tạo mới",session,jedis) %> </a>
										    				</LEGEND>                            
														<%} }
														 else if(obj.getLoai().equals("5")){  %>
														<LEGEND class="legendtitle">&nbsp;<%=Utility.GLanguage("Tiêu chi tính thưởng cho NPP",session,jedis) %>&nbsp;&nbsp;&nbsp;
														<%if(quyen[Utility.THEM]!=0) {%>
															<a class="button3" href="javascript:saveForm()">
										    				<img style="top: -4px;" src="../images/New.png" ><%=Utility.GLanguage("Tạo mới",session,jedis) %> </a>                            
														<%}} %>
														</LEGEND>
														
													<%}} %>
												
												<TABLE  border="0" cellpadding="4"  cellspacing="1" width="100%">
															<TR class="tbheader" >
																<TH width="18%" ><%=Utility.GLanguage("Diễn giải",session,jedis) %> </TH>
<!-- 																<TH width="10%" ><%=Utility.GLanguage("Kênh bán hàng",session,jedis) %></TH>															 -->
																<TH width="12%" ><%=Utility.GLanguage("Đơn vị kinh doanh",session,jedis) %> </TH>
																<TH width="5%" ><%=Utility.GLanguage("Tháng",session,jedis) %> </TH>
																<TH width="5%" ><%=Utility.GLanguage("Năm",session,jedis) %></TH>
																<TH width="14%" ><%=Utility.GLanguage("Người tạo",session,jedis) %></TH>
																<TH width="7%" ><%=Utility.GLanguage("Ngày tạo",session,jedis) %></TH>
																<TH width="14%" ><%=Utility.GLanguage("Người sửa",session,jedis) %></TH>
																<TH width="7%" ><%=Utility.GLanguage("Ngày sửa",session,jedis) %></TH>
																<TH width="8%" ><%=Utility.GLanguage("Tác vụ",session,jedis) %></TH>
															</TR>
												<%	
												if(tct != null){
													try{
														int m = 0;
														while(tct.next()){
																					
															if(m%2 == 0){						%>
															<TR class= 'tblightrow'>
															<%}else{ %>
															<TR class= 'tbdarkrow'>
															<%} %>
											  					<TD align="left" ><%= tct.getString("diengiai") %></TD>								

<%-- 											  					<TD align="center" ><%= tct.getString("kbh") %></TD> --%>
																
																<TD align="center" > <%= tct.getString("dvkd") %></TD>
																
											  					<TD align="center" ><%= tct.getString("thang") %> </TD>
														
																
											  					<TD align="center" ><%= tct.getString("nam") %> </TD>
																
																<TD align="center"><%=tct.getString("nguoitao")%></TD>	
																
																<TD align="center"><%= tct.getString("ngaytao").substring(0,10) %></TD>
															
																<TD align="center"><%=tct.getString("nguoisua")%></TD>
																
																<TD align="center"><%=tct.getString("ngaysua").substring(0,10)%>
																<input type="hidden" id = "ngaysua<%=m %>" value = "<%=tct.getString("ngaysua").substring(0,10)%>">
																</TD>
																
																
																<TD align="center">
																	<TABLE border = 0 cellpadding="0" cellspacing="0">
																		<TR>
																		<%if(!tct.getString("trangthai").equals("1")){ %>
																			<TD>
																				<%if(quyen[2]!=0) {%>
													  							<A href = "../../RouterSvl?task=<%= Util.dongMa(getServletContext().getInitParameter("RedirectNoScript") +"TieuChiThuongUpdateSvl?userId="+userId+"&capnhat="+ tct.getString("pk_seq")+"&loaithuong="+obj.getLoaithuong()+"")%>">
                                               									<img src="../images/Edit20.png" alt="Cap nhat" width="20" height="20" longdesc="Cap nhat" border = 0></A>
																				<%} %>
																			</TD>
																			<TD>&nbsp;</TD>
																			<TD>
																				<%if(quyen[1]!=0  ) {%>
														  						<A href = "../../RouterSvl?task=<%= Util.dongMa(getServletContext().getInitParameter("RedirectNoScript") +"TieuChiThuongSvl?userId="+userId+"&xoa="+ tct.getString("pk_seq")+" ;"+obj.getLoaithuong()+"&loaithuong="+obj.getLoaithuong()+"")%>">
    	                                            							<img src="../images/Delete20.png" alt="Xoa" width="20" height="20" longdesc="Xoa" border=0 onclick="if(!confirm('Bạn có muốn xóa tiêu chí tính thưởng này?')) return false;"></A>
        	                                        							<%} %>
        	                                        						</TD>
            	                                    						<TD>&nbsp;</TD>
            	                                    					
																			<TD>
																			<%if(quyen[4]!=0){ %>
													  						<A href = "../../RouterSvl?task=<%= Util.dongMa(getServletContext().getInitParameter("RedirectNoScript") +"TieuChiThuongSvl?userId="+userId+"&chot="+ tct.getString("pk_seq")+";"+obj.getLoai()+"&loaithuong="+obj.getLoaithuong()+"")%>">
                                                							<img src="../images/Chot.png" alt="Chot" width="20" height="20" longdesc="Chot" border=0 onclick="if(!confirm('Bạn có muốn chốt tiêu chí tính thưởng này?')) return false;"></A>
                                                							<%} %>
                                                							</TD>
																	 	<%}else{ %>
																			<TD>
																			<%if(quyen[3]!=0){ %>
													  							<A href = "../../RouterSvl?task=<%= Util.dongMa(getServletContext().getInitParameter("RedirectNoScript") +"TieuChiThuongUpdateSvl?userId="+userId+"&hienthi="+ tct.getString("pk_seq")+"&loaithuong="+obj.getLoaithuong()+"")%>">
                                               									<img src="../images/Display20.png" alt="Hien thi" width="20" height="20" longdesc="Hien thi" border = 0></A>
																			<%} %>
																			</TD>
																			
																			<TD>
													  							<A href = "../../RouterSvl?task=<%= Util.dongMa(getServletContext().getInitParameter("RedirectNoScript") +"TieuChiThuongSvl?userId="+userId+"&mochot="+ tct.getString("pk_seq")+"")%>">
                                               									<img src="../images/unChot.png" alt="Mở chốt" width="20" height="20" longdesc="Mo chot" border = 0></A>
																			</TD>
            	                                    						
																			<TD>
																				<%if(quyen[0]!=0  ){ %>
													  							<A href = "../../RouterSvl?task=<%= Util.dongMa(getServletContext().getInitParameter("RedirectNoScript") +"TieuChiThuongUpdateSvl?userId="+userId+"&copy="+ tct.getString("pk_seq")+"&loaithuong="+obj.getLoaithuong()+"")%>">
                                               									<img src="../images/convert.gif" alt="Sao chep" width="20" height="20" longdesc="Sao chep" border = 0></A>
																				<%} %>
																			</TD>
            	                                    					
																		<%} %>																	
																		</TR>
																		
																	</TABLE>
																</TD>
																
															</TR>
												<%	m++;															
														}
														}catch(java.sql.SQLException e){}
														  	
												 }%>
												 <tr class="tbfooter" > 
													 <TD align="center" valign="middle" colspan="10" class="tbfooter">&nbsp;</TD>
												</tr>
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
		</TD>
	
</TABLE>
</form>
<%geso.dms.center.util.Utility.JedisClose(jedis); %>
</BODY>
</HTML>

<% 	if(dvkd != null) dvkd.close(); 
	if(tct != null) tct.close();
	obj.closeDB();
}%>