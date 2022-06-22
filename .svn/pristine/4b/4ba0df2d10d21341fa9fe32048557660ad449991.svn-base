<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page  import = "geso.dms.center.beans.duyettratrungbay.IDuyettratrungbayList" %>
<%@ page  import = "java.util.Iterator" %>
<%@ page  import = "java.util.List" %>
<%@ page  import = "java.util.ArrayList" %>
<%@ page  import = "java.sql.ResultSet" %>
<%@ page  import = "geso.dms.center.util.*" %>
<%@ page  import = "java.util.Hashtable" %>
<%@ page  import = "java.text.NumberFormat" %>
<%@ page  import = "java.text.DecimalFormat" %>


<%	String userId = (String) session.getAttribute("userId");  
	String userTen = (String) session.getAttribute("userTen");  	
	String sum = (String) session.getAttribute("sum");
	Utility util = (Utility) session.getAttribute("util");
	//if(!util.check(userId, userTen, sum)){
		//response.sendRedirect("/SOHACO/index.jsp");
	//}else{ 
	%>

<% IDuyettratrungbayList dttbBean = (IDuyettratrungbayList)session.getAttribute("dttbBean"); %>
<% ResultSet duyetRs = (ResultSet)dttbBean.getDuyettraRS() ; %>
<% ResultSet cttbRs = (ResultSet)dttbBean.getCttbRs() ; %>
<% ResultSet vung = (ResultSet)dttbBean.getVung() ; %>
<% ResultSet kv = (ResultSet)dttbBean.getKvRs() ; %>
<% ResultSet npp = (ResultSet)dttbBean.getNppRs(); %>

<% Utility Util = new Utility(); %>
<% int[] quyen = new  int[6];
	quyen = Util.Getquyen("DuyettratrungbaySvl","",userId);


String url = util.getChuyenNguUrl("DuyettratrungbaySvl", "",session);
	%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"> <% Utility Utilback = new Utility(); %>
<%@page import="java.sql.SQLException"%>
<HTML>
<HEAD>
<TITLE><%= getServletContext().getInitParameter("TITLENAME") %></TITLE>
<META http-equiv="Content-Type" content="text/html; charset=utf-8">

<META http-equiv="Content-Style-Type" content="text/css">

<LINK rel="stylesheet" href="../css/main.css" type="text/css">
<link type="text/css" rel="stylesheet" href="../css/mybutton.css">
<SCRIPT language="JavaScript" type="text/javascript">
function submitform()
{

    document.forms['dttbForm'].submit();
}


function clearform()
{      document.forms['dttbForm'].schemeId.value= '';
       document.forms['dttbForm'].trangthai.value= '';
       submitform();
}


function newform()
{
	document.dttbForm.action.value='new';
    document.forms['dttbForm'].submit();
}

</SCRIPT>
</HEAD>
<BODY leftmargin="0" bottommargin="0" topmargin="0" rightmargin="0">
<form name ="dttbForm" method="post" action="../../DuyettratrungbaySvl" >
<% redis.clients.jedis.Jedis jedis =  geso.dms.center.util.Utility.getJedis(); %><% geso.dms.center.util.Csrf csdr=new geso.dms.center.util.Csrf(request,response,true,false,true);%>
 <input type="hidden" name="<%=csdr.get_tokenName() %>" value='<%=csdr.get_tokenValue() %>'>

<input type="hidden" name="action" value="0">

<TABLE width="100%" border="0" cellspacing="0" cellpadding="0"
	height="100%">
	<TR>
		<TD colspan="4" align='left' valign='top' bgcolor="#FFFFFF">
			<TABLE width="100%" border="0" cellpadding="0" cellspacing="1">
				<TR>
					<TD align="left" class="tbnavigation">
						<table width="100%" border="0" cellpadding="0" cellspacing="0">
						  <tr height="22">
							 <TD align="left" colspan="2" class="tbnavigation">&nbsp;<%= " " + url %> </TD> 
							 <TD colspan="2" align="right" class="tbnavigation"><%=Utility.GLanguage("Chào mừng bạn",session,jedis) %>  <%=userTen %>&nbsp;  </TD></tr>
						</table>
			 		</TD>
				</TR>
			</TABLE>
			<TABLE width="100%" border="0" cellpadding="0"  cellspacing="1" >
			  	<tr>
					<TD align="left" colspan="4" class="legendtitle">
						<FIELDSET>
						<LEGEND class="legendtitle"><%=Utility.GLanguage("Thông báo",session,jedis) %></LEGEND>
				
	    				<textarea name="dataerror"  style="width: 100% ; color:#F00 ; font-weight:bold"  style="width:100%" rows="1"><%= dttbBean.getMessage() %></textarea>

						</FIELDSET>
				   </TD>
				</tr>			

				<TR>
				  <TD height="100%" width="100%">
						<FIELDSET >
						<LEGEND class="legendtitle" style="color:black"><%=Utility.GLanguage("Điều kiện lọc",session,jedis) %></LEGEND>
						<TABLE border="0" width="100%" cellpadding="6" cellspacing="0">
						  <TR>
							  	<TD width="15%" class="plainlabel"><%=Utility.GLanguage("Chương trình",session,jedis) %></TD>
						  	  	<TD class="plainlabel">
									<SELECT name="schemeId" onChange = "submitform();">
								    <option value=""></option>
								      <% try{while(cttbRs.next()){ 
								    	if(cttbRs.getString("pk_seq").equals(dttbBean.getCttbId())){ %>
								      		<option value='<%=cttbRs.getString("pk_seq")%>' selected><%=cttbRs.getString("scheme") + "_" + cttbRs.getString("diengiai") %></option>
								      	<%}else{ %>
								     		<option value='<%=cttbRs.getString("pk_seq")%>'><%=cttbRs.getString("scheme") + "_" + cttbRs.getString("diengiai") %></option>
								     	<%}}}catch(java.sql.SQLException e){} %>	  
                        				</SELECT>						  	  	
						  	  	
						  	  	</TD>
						  </TR>

						  <%-- <TR>
						    	<TD class="plainlabel"><%=Utility.GLanguage("Vùng",session,jedis) %></TD>
							    <TD class="plainlabel"><SELECT name="vungId" onChange = "submitform();">
								    <option value="0"></option>
								      <% try{while(vung.next()){ 
								    	if(vung.getString("pk_seq").equals(dttbBean.getVungId())){ %>
								      		<option value='<%=vung.getString("pk_seq")%>' selected><%=vung.getString("diengiai") %></option>
								      	<%}else{ %>
								     		<option value='<%=vung.getString("pk_seq")%>'><%=vung.getString("diengiai") %></option>
								     	<%}}}catch(java.sql.SQLException e){} %>	  
                        				</SELECT>			
                        		</TD>
						</TR>
						<TR>  
							    <TD class="plainlabel"><%=Utility.GLanguage("Khu vực",session,jedis) %> </TD>
							    <TD class="plainlabel"><SELECT name="kvId" onChange = "submitform();">
								    <option value="0"></option>
								      <% try{while(kv.next()){ 
								    	if(kv.getString("pk_seq").equals(dttbBean.getKvId())){ %>
								      		<option value='<%=kv.getString("pk_seq")%>' selected><%=kv.getString("diengiai") %></option>
								      	<%}else{ %>
								     		<option value='<%=kv.getString("pk_seq")%>'><%=kv.getString("diengiai") %></option>
								     	<%}}}catch(java.sql.SQLException e){} %>	  
                        				</SELECT>			
                        		</TD>		
					      </TR>
					      
					      <TR>
						    	<TD class="plainlabel"><B></b>Chi nhánh / Nhà phân phối</TD>
							    <TD class="plainlabel"><SELECT name="nppId" onChange = "submitform();">
								    <option value="0"></option>
								      <% try{while(npp.next()){ 
								    	if(npp.getString("pk_seq").equals(dttbBean.getNppId())){ %>
								      		<option value='<%=npp.getString("pk_seq")%>' selected><%=npp.getString("ten") %></option>
								      	<%}else{ %>
								     		<option value='<%=npp.getString("pk_seq")%>'><%=npp.getString("ten") %></option>
								     	<%}}}catch(java.sql.SQLException e){} %>	  
                        				</SELECT>			
                        		</TD>
							    
					      </TR> --%>

						  <TR>
							  	<TD width="130px" class="plainlabel"><%=Utility.GLanguage("Trạng thái",session,jedis) %></TD>
									<TD class="plainlabel">
										<select name="trangthai" onChange="submitform();">
											<option value=""></option>
											<option value="1" <%= dttbBean.getTrangthai().equals("1") ? "selected":""  %>    > <%=Utility.GLanguage("Đã duyệt",session,jedis) %> </option>
											<option value="0" <%= dttbBean.getTrangthai().equals("0") ? "selected":""  %>    ><%=Utility.GLanguage("Chưa duyệt",session,jedis) %></option>
									     </select>
									</TD>
						  	  	
						  </TR>
						  </TABLE>
						  
						  <TABLE  width="100%" cellpadding="4" cellspacing="0">
						  <TR>
                                	<TD class="plainlabel" align="left">
                                	
                                	 	<a class="button" href="javascript:submitform()">
                                		<img style="top: -4px;" src="../images/Search30.png" alt=""><%=Utility.GLanguage("Tìm kiếm",session,jedis) %></a>&nbsp;&nbsp;&nbsp;&nbsp;
                                	
                                		<a class="button2" href="javascript:clearform()">
										<img style="top: -4px;" src="../images/button.png" alt=""><%=Utility.GLanguage("Nhập lại",session,jedis) %></a>&nbsp;&nbsp;&nbsp;&nbsp;	
                                	<!-- 
                                		<INPUT name="reinitialiser" type="button" value="Nhap lai" onClick="clearform();"> -->
                                		</TD>
                                	<TD class="plainlabel" colspan=4>&nbsp;</TD>
                                </TR>
						</TABLE>
						</FIELDSET>
					</TD>
				</TR>
				<TR>
				  <TD height="100%" width="100%">
						<FIELDSET>
						<LEGEND class="legendtitle" style="color:black"><%=Utility.GLanguage("Duyệt trả",session,jedis) %>
						<%if(quyen[Utility.THEM] == 1){ %>
						 	<a style="margin-left: 5px;" class="button2" href="javascript:newform();"><img style="top: -4px;" src="../images/New.png" alt=""><%=Utility.GLanguage("Tạo mới",session,jedis) %></a>&nbsp;&nbsp;&nbsp;&nbsp;
						<%} %>
						</LEGEND>
						<TABLE border="0" width="100%" cellpadding="6" cellspacing="1">
							<tr class="tbheader">
								<TH width="10%" ><%=Utility.GLanguage("Diễn giải",session,jedis) %></TH>
							  	<TH width="15%" ><%=Utility.GLanguage("Chương trình trưng bày",session,jedis) %></TH>
								<TH width="10%" ><%=Utility.GLanguage("Trạng thái",session,jedis) %></TH>
								<TH width="10%" ><%=Utility.GLanguage("Ngày đề nghị",session,jedis) %></TH>
								<TH width="10%" ><%=Utility.GLanguage("Ngày sửa",session,jedis) %></TH>
								<TH width="10%" ><%=Utility.GLanguage("Người tạo",session,jedis) %></TH>
								<TH width="10%" ><%=Utility.GLanguage("Người sửa",session,jedis) %></TH>
							  	<TH width="10%" ><%=Utility.GLanguage("Tác vụ",session,jedis) %></TH>
						  </tr>
						  <% try{
							    String lightrow = "tblightrow";
								String darkrow = "tbdarkrow";
								int m = 0;
								if(duyetRs != null){
									while(duyetRs.next()){
										String tt = duyetRs.getString("TRANGTHAI");
										String trangthai = "Chưa duyệt";
										if(tt.equals("1"))
											trangthai = "Đã duyệt";
										if(tt.equals("2"))
											trangthai = "Đã hủy";
										
									if (m % 2 != 0) {%>						
										<TR class= <%=lightrow%> >
									<%} else {%>
										<TR class= <%= darkrow%> >
									<%}%>
										<TD align="left"><div align="left"><%= duyetRs.getString("DIENGIAI")%></div></TD>
										<TD align="left"><div align="left"><%= duyetRs.getString("SCHEME")%></div></TD>
										<TD align="left"><div align="left"><%= trangthai%></div></TD>
										<TD align="left"><div align="left"><%= duyetRs.getString("NGAYDENGHI")%></div></TD>                                   
										<TD align="left"><div align="left"><%= duyetRs.getString("NGAYSUA")%></div></TD> 
										<TD align="left"><div align="left"><%= duyetRs.getString("NGUOITAO")%></div></TD> 
										<TD align="left"><div align="left"><%= duyetRs.getString("NGUOISUA")%></div></TD> 
										<TD align="left"><div align="center">
										<%if(tt.equals("0")){ %>
											<%if(quyen[Utility.SUA]!=0 && tt.equals("0")){ %>                  
						                      	<A href = "../../RouterSvl?task=<%= Util.dongMa(getServletContext().getInitParameter("RedirectNoScript") +"DuyettratrungbayUpdateSvl?userId="+userId+"&update="+ duyetRs.getString("PK_SEQ")+"")%>"><IMG src="../images/Edit20.png" alt="Cập nhật" title="Cập nhật" border="0"></A>
					                      	<%} %>
				                      		<%if(quyen[Utility.CHOT]!=0 && tt.equals("0")){ %>    
							                  	<A href = "../../RouterSvl?task=<%= Util.dongMa(getServletContext().getInitParameter("RedirectNoScript") +"DuyettratrungbaySvl?userId="+userId+"&chot="+ duyetRs.getString("PK_SEQ")+"") %>"><img src="../images/Chot.png" alt="Duyệt" title="Duyệt" width="20" height="20" longdesc="Duyệt" border=0 onclick="if(!confirm('Bạn có muốn duyệt chương trình này?')) return false;"></A>       
											<%} %>
											<%if(quyen[Utility.XOA]!=0 && tt.equals("0")){ %>    
						                  		<A href = "../../RouterSvl?task=<%= Util.dongMa(getServletContext().getInitParameter("RedirectNoScript") +"DuyettratrungbaySvl?userId="+userId+"&delete="+ duyetRs.getString("PK_SEQ")+"") %>"><img src="../images/Delete20.png" alt="Xóa" title="Xóa" width="20" height="20" longdesc="Xóa" border=0 onclick="if(!confirm('Bạn có muốn xóa duyệt trả này?')) return false;"></A>       
					                 		<%} %>
				                      	<%} else{%>
				                      		<%if(quyen[Utility.XEM]!=0){ %>                  
						                      <A href = "../../RouterSvl?task=<%= Util.dongMa(getServletContext().getInitParameter("RedirectNoScript") +"DuyettratrungbayUpdateSvl?userId="+userId+"&display="+ duyetRs.getString("PK_SEQ")+"")%>"><IMG src="../images/Display20.png" alt="Xem" title="Xem" border="0"></A>
				                      		<%} %>    
				                      	<%} %>
										</div></TD> 
									</TR>
						  		<% m++ ;} 
						  		
						  		}%>		
						  		
						  <%}catch(java.sql.SQLException e){}%>
							<tr class="tbfooter"><td colspan="12" > &nbsp; </td></tr>
						</TABLE>
						</FIELDSET>
					</TD>
				
				
				</TR>
				
			</TABLE>
		</TD>
		</TR>
	</TABLE>
	<%geso.dms.center.util.Utility.JedisClose(jedis); %>
	</form>
</body>  <script type='text/javascript' src='../scripts/loadingv2.js'></script>
</HTML>