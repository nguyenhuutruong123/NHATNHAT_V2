<%@page import="geso.dms.center.beans.phieuchuyenkhott.imp.PhieuChuyenKhoTT"%>
<%@page import="geso.dms.center.beans.phieuchuyenkhott.IPhieuChuyenKhoTT"%>
<%@page import="java.sql.ResultSet"%>
<%@page import="geso.dms.center.beans.phieunhapkhott.imp.PhieuNhapKhoTT"%>
<%@page import="geso.dms.center.beans.khott.IKhoTT"%>
<%@page import="geso.dms.center.beans.khott.imp.KhoTT"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page  import = "geso.dms.center.beans.kho.IKho" %>
<%@ page  import = "geso.dms.center.beans.kho.IKhoList" %>
<%@ page  import = "java.util.Iterator" %>
<%@ page  import = "java.util.List" %>
<%@ page  import = "java.util.ArrayList" %>
<%@ page  import = "geso.dms.center.util.*" %>
<%	String userId = (String) session.getAttribute("userId");  
	String userTen = (String) session.getAttribute("userTen");  	
	String sum = (String) session.getAttribute("sum");
	Utility util = (Utility) session.getAttribute("util");
	  String tungay=(String)session.getAttribute("tungay");
	  String denngay=(String)session.getAttribute("denngay");
	  String khochuyen=(String)session.getAttribute("khochuyen");
	  String khonhan=(String)session.getAttribute("khonhan");
	  String dvkdid=(String)session.getAttribute("dvkdid");
	  String  sanphamid=(String)session.getAttribute("sanphamid");
	if(!util.check(userId, userTen, sum)){
		response.sendRedirect(request.getContextPath() + "/redirect.jsp");
	}else{ %>
<%
ResultSet rs_dvkd=(ResultSet)session.getAttribute("rs_dvkd");
ResultSet rs_sanpham=(ResultSet)session.getAttribute("rs_sanpham");
  ResultSet rs_khott=(ResultSet)session.getAttribute("rs_khott");
  ResultSet rs_khott_nhan=(ResultSet)session.getAttribute("rs_khott_nhan");
 IPhieuChuyenKhoTT obj = (IPhieuChuyenKhoTT)session.getAttribute("obj"); %>
<% List<IPhieuChuyenKhoTT> kholist = obj.getListChuyenKho(); %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"> <% Utility Utilback = new Utility(); %>
<HTML>
<HEAD>
<TITLE><%= getServletContext().getInitParameter("TITLENAME") %></TITLE>
<META http-equiv="Content-Type" content="text/html; charset=utf-8">

<META http-equiv="Content-Style-Type" content="text/css">
<LINK rel="stylesheet" type="text/css" media="print"
	href="../css/impression.css">
<LINK rel="stylesheet" href="../css/main.css" type="text/css">
<LINK rel="stylesheet" href="../css/calendar.css" type="text/css">
<script type="text/javascript" language="JavaScript" src="../scripts/simplecalendar.js"></script>
	<link type="text/css" rel="stylesheet" href="../css/mybutton.css">
<SCRIPT language="javascript" type="text/javascript">
function clearform()
{
	document.khoForm.khonhan.value = "0"; 
    document.khoForm.khochuyen.value = "0"; 
    document.khoForm.dvkdid.value = "0";
    document.khoForm.sanphamid.value = "0"; 
	document.khoForm.tungay.value = "";
	document.khoForm.denngay.value = "";       
    document.khoForm.trangthai.selectedIndex = 0;
    submitform();
}

function submitform()
{
	document.forms['khoForm'].action.value= 'search';
	document.forms['khoForm'].submit();
}

function newform()
{
	document.forms['khoForm'].action.value= 'new';
	document.forms['khoForm'].submit();
}

</SCRIPT>

</HEAD>
<BODY leftmargin="0" bottommargin="0" topmargin="0" rightmargin="0">
<form name="khoForm" method="post" action="../../PhieuChuyenKhoTTSvl" charset="UTF-8">
<INPUT name="userId" type="hidden" value='<%=userId%>' size="30">
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
							   		&nbsp;Qu???n l?? t???n kho> Kho trung t??m >Phi???u chuy???n kho </TD>
							   <TD colspan="2" align="right" class="tbnavigation"><%=Utility.GLanguage("Ch??o m???ng b???n",session,jedis) %>  &nbsp;<%=userTen %> &nbsp;</td> 
						    </tr>
   
						</table>
					</TD>
				</TR>
			</TABLE>
			<TABLE width="100%" border="0" cellpadding="0" cellspacing="0">
				<TR>
					<TD width="100%" align="left"><FIELDSET>
						<LEGEND class="legendtitle"><%=Utility.GLanguage("Ti??u ch?? t??m ki???m",session,jedis) %> </LEGEND>

							<TABLE class="tblight" width="100%" cellpadding="6" cellspacing = "0">
								<TR>
									  	<TD class="plainlabel" width="19%">Kho Chuy???n</TD>
									  	<TD class="plainlabel" >
									  	<select name="khochuyen">
									  	<option value="0" > </option>
									  	<% if(rs_khott!=null){
									  		try{
									  			while(rs_khott.next()){
									  				if(rs_khott.getString("pk_seq").equals(khochuyen)){
									  					%>
									  					<option value="<%= rs_khott.getString("pk_seq")%>" selected="selected"> <%= rs_khott.getString("ten") %> </option>
									  					<%
									  				}else{
									  					%>
									  					<option value="<%= rs_khott.getString("pk_seq")%>"> <%= rs_khott.getString("ten") %> </option>
									  					<%
									  				}
									  			
									  			}
									  			
									  		}catch(Exception er){
									  			System.out.println("Error PhieuChuyenKhoTT  "+er.toString());
									  		}
									  	}
									  		%>	  
									  	</select>
									  
									  	</TD>
								</TR>
								<TR>
									  	<TD class="plainlabel" width="19%">Kho Nh???n </TD>
									  	<TD class="plainlabel" >
									  	<select name="khonhan">
									  	<option value="0" > </option>
									  	<% if(rs_khott_nhan!=null){
									  		try{
									  			while(rs_khott_nhan.next()){
									  				if(rs_khott_nhan.getString("pk_seq").equals(khonhan)){
									  					%>
									  					<option value="<%= rs_khott_nhan.getString("pk_seq")%>" selected="selected"> <%= rs_khott_nhan.getString("ten") %> </option>
									  					<%
									  				}else{
									  					%>
									  					<option value="<%= rs_khott_nhan.getString("pk_seq")%>"> <%= rs_khott_nhan.getString("ten") %> </option>
									  					<%
									  				}
									  			
									  			}
									  		}catch(Exception er){
									  			System.out.println("Error PhieuChuyenKhoTT  "+er.toString());
;									  		}
									  	}
									  		%>	  
									  	</select>
									  
									  	</TD>
								</TR>
								<TR>
										<TD class="plainlabel" ><%=Utility.GLanguage("T??? ng??y",session,jedis) %> </TD>
									 	<TD class="plainlabel" >
											<TABLE cellpadding="0" cellspacing="0">
											<TR><TD>
												<input type="text" name="tungay" size="20" value='<%=tungay %>' onFocus="submitform();">
												</TD>
												<TD>
<a href="javascript: void(0);" onMouseOver="if (timeoutId) clearTimeout(timeoutId);window.status='Show Calendar';return true;" onMouseOut="if (timeoutDelay) calendarTimeout();window.status='';" onClick="g_Calendar.show(event,'khoForm.tungay',false, 'yyyy-mm-dd'); return false;">
		 &nbsp; <img src="../images/Calendar20.png" name="imgCalendar" border="0" alt=""></a>
		  
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
													<input type="text" name="denngay" size="20" value='<%=denngay %>' onFocus="submitform();">
												</TD>
												<TD>
											<a href="javascript: void(0);" onMouseOver="if (timeoutId) clearTimeout(timeoutId);window.status='Show Calendar';return true;" onMouseOut="if (timeoutDelay) calendarTimeout();window.status='';" onClick="g_Calendar.show(event,'khoForm.denngay',false, 'yyyy-mm-dd'); return false;">
											 &nbsp; <img src="../images/Calendar20.png" name="imgCalendar" border="0" alt=""></a>

        		                                </TD>
                	                     	</TR>
										</TABLE>
									</TD>

								</TR>
								<TR>
								<TD class="plainlabel">
										Ch???n ????n v??? kinh doanh 
									</TD>
									<TD class="plainlabel">
									<select name="dvkdid">
									  	<option value="0" > </option>
									  	<% if(rs_dvkd!=null){
									  		try{
									  			while(rs_dvkd.next()){
									  				if(rs_dvkd.getString("pk_seq").trim().equals(dvkdid)){
									  					%>
									  					<option value="<%= rs_dvkd.getString("pk_seq")%>" selected="selected"> <%= rs_dvkd.getString("ten") %> </option>
									  					<%
									  					}else{
									  					%>
									  					<option value="<%= rs_dvkd.getString("pk_seq")%>"> <%= rs_dvkd.getString("ten") %> </option>
									  					<%
									  				}
									  			
									  			}
									  		}catch(Exception er){
									  		}
									  	}
									  		%>	  
									  	</select>	
									</TD>
								</TR>
								<TR >
								<TD class="plainlabel" >
										Ch???n s???n ph???m 
									</TD>
									<TD class="plainlabel">
										<select name="sanphamid">
									  	<option value="0" > </option>
									  	<% if(rs_dvkd!=null){
									  		try{
									  			while(rs_sanpham.next()){
									  				if(rs_sanpham.getString("pk_seq").trim().equals(sanphamid)){
									  					%>
									  					<option value="<%= rs_sanpham.getString("pk_seq")%>" selected="selected"> <%=rs_sanpham.getString("ma") +" - "+  rs_sanpham.getString("ten") %> </option>
									  					<%
									  				}else{
									  					%>
									  					<option value="<%= rs_sanpham.getString("pk_seq")%>"> <%= rs_sanpham.getString("ma") +" -  "+  rs_sanpham.getString("ten") %> </option>
									  					<%
									  				}
									  			
									  			}
									  		}catch(Exception er){
									  		}
									  	}
									  		%>	  
									  	</select>	
									</TD>
								</TR>
								<TR>
									<TD class="plainlabel"><%=Utility.GLanguage("Tr???ng th??i",session,jedis) %></TD>
											
									<TD class="plainlabel"><select name="trangthai" onChange="submitform();">
											<option value="" selected> </option>
										<% if (obj.getTrangthai().equals("1")){ %>
										  	<option value="1" selected >???? ch???t</option>
										  	<option value="0">??ang ch??? x??? l?? </option>
										   <%}else if(obj.getTrangthai().equals("0")){ %>
										  		<option value="1"  >???? ch???t</option>
										  		<option value="0" selected>??ang ch??? x??? l??</option>
										 	<%}else{%>
										 	<option value="1"  >???? ch???t</option>
										  		<option value="0" >??ang ch??? x??? l??</option>
										 	<%} %>										 
										    </select>
									</TD>
								</TR>
								<TR>
									<TD class="plainlabel" colspan="2">
								   <a class="button3" href="javascript:submitform()">
                           	<img style="top: -4px;" src="../images/Search30.png" alt=""><%=Utility.GLanguage("T??m ki???m",session,jedis) %> </a>   &nbsp;&nbsp;&nbsp;
                           		<a class="button2" href="javascript:clearform()">
							<img style="top: -4px;" src="../images/button.png" alt=""><%=Utility.GLanguage("Nh???p l???i",session,jedis) %></a>&nbsp;&nbsp;&nbsp;&nbsp;	
                                                
									</TD>
								</TR>
							</TABLE>
							</FIELDSET>
						</TD>	
					</TR>
				</TABLE>
			
			<TABLE width="100%" border="0" cellpadding="0" cellspacing="0">		
			    <TR>
					<TD align="left" >
						<FIELDSET>
							<LEGEND class="legendtitle">&nbsp;Danh s??ch phi???u chuy???n kho  &nbsp;&nbsp;
								<a class="button3" href="javascript:newform()">
    	<img style="top: -4px;" src="../images/New.png" alt=""><%=Utility.GLanguage("T???o m???i",session,jedis) %> </a>   	
						
						</LEGEND>
				
							<TABLE class="" width="100%" border="0" cellpadding="0" cellspacing="0">
								<TR>
									<TD width="98%">
										<TABLE width="100%" border="0" cellspacing="1" cellpadding="4">
											<TR class="tbheader">
												<TH width="10%">S??? Phi???u chuy???n</TH>
												<TH > Ng??y chuy???n </TH>
												<TH width="15%">T??n kho chuy???n </TH>
												<TH width="15%">T??n kho nh???n </TH>
												<TH width="10%"><%=Utility.GLanguage("Tr???ng th??i",session,jedis) %> </TH>
												<TH width="10%"><%=Utility.GLanguage("Ng??y t???o",session,jedis) %></TH>
												<TH width="10%"><%=Utility.GLanguage("Ng?????i t???o",session,jedis) %> </TH>
												<TH width="10%"><%=Utility.GLanguage("Ng??y s???a",session,jedis) %></TH>
												<TH width="10%"><%=Utility.GLanguage("Ng?????i s???a",session,jedis) %></TH>
												<TH width="10%"><%=Utility.GLanguage("T??c v???",session,jedis) %></TH>
											</TR>
								<% 
								IPhieuChuyenKhoTT pnkho = null;
									int size = kholist.size();
									int m = 0;
									String lightrow = "tblightrow";
									String darkrow = "tbdarkrow";
									while (m < size){
										pnkho = (IPhieuChuyenKhoTT)kholist.get(m);
										if (m % 2 != 0) {%>						
											<TR class= <%=lightrow%> >
										<%} else {%>
											<TR class= <%= darkrow%> >
										<%}%>
											<TD align="left"><%=pnkho.getId()%></TD> 
											<TD><%=pnkho.getNgayChuyen() %></TD>                                  
											<TD><%=pnkho.getTenKhoChuyen() %></TD>
											<TD><%=pnkho.getTenKhoNhan() %></TD>
											<%if (pnkho.getTrangthai().equals("0")) {%>
												<TD align="center">??ang ch??? x??? l?? </TD>
											<%}else { %>
												<TD align="center" style="color:red;font-weight: bold;">???? ch???t </TD>
											<%} %>
												
												<TD align="center"><%=pnkho.getNgaytao() %></TD>
												<TD align="center"><%=pnkho.getNguoitao() %></TD>
												<TD align="center"><%=pnkho.getNgaysua() %></TD>												
												<TD align="center"><%=pnkho.getNguoisua() %></TD>
												<TD align="center">
													<TABLE border = 0 cellpadding="0" cellspacing="0">										
												<TR>
												<% if((pnkho.getTrangthai().equals("0"))) {%>
														<TD>
														<A href = "../../PhieuChuyenKhoTTNewSvl?userId=<%=userId%>&update=<%=pnkho.getId()%>" ><img src="../images/Edit20.png" title="Ch???nh s???a" alt="Chinh sua" width="20" height="20" longdesc="Chinh sua" border = 0></A>
														</TD>
														<TD>
														<A href = "../../PhieuChuyenKhoTTSvl?userId=<%=userId%>&delete=<%=pnkho.getId()%>"><img src="../images/Delete20.png" title="X??a" alt="Xoa" width="20" height="20" longdesc="Xoa" border=0 onclick="if(!confirm('B???n c?? mu???n x??a ????n h??ng n??y?')) return false;"> </A>
														</TD>
														<TD >
                                                       	 <A href = "../../PhieuChuyenKhoTTNewSvl?userId=<%=userId%>&chot=<%=pnkho.getId()%>"><img src="../images/Chot.png" alt="Ch???t " title="Ch???t phi???u" width="30" height="25" longdesc="Ch???t " border = 0></A>
                                                        </TD>
                                                       <%}else{ %>
                                                       <TD >
                                                       	 <A href = "../../PhieuChuyenKhoTTNewSvl?userId=<%=userId%>&display=<%=pnkho.getId()%>"><img src="../images/Display20.png" alt="Hien thi" title="Hi???n th???" width="20" height="20" longdesc="Hien thi" border = 0></A>
                                                        </TD>
                                                       <%} %> 
														</TR>
														
													</TABLE>												
													</TD>
												</TR>
										<% 	m++; }%>
								
										
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
	
</TABLE>
</form>
</BODY>
</HTML>
<%}%>