<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page  import = "geso.dms.center.beans.duyettratrungbay.IDuyettratrungbay" %>
<%@ page  import = "java.util.Iterator" %>
<%@ page  import = "java.util.List" %>
<%@ page  import = "java.util.ArrayList" %>
<%@ page  import = "java.sql.ResultSet" %>
<%@ page  import = "geso.dms.center.util.*" %>
<%@ page  import = "java.util.Hashtable" %>
<%@ page  import = "java.text.NumberFormat" %>
<%@ page  import = "java.text.DecimalFormat" %>
<%@ page import = "com.google.gson.JsonArray" %>
<%@ page import = "com.google.gson.JsonObject" %>
<%@ page import = "com.google.gson.JsonParser" %>


<%	String userId = (String) session.getAttribute("userId");  
	String userTen = (String) session.getAttribute("userTen");  	
	String sum = (String) session.getAttribute("sum");
	Utility util = (Utility) session.getAttribute("util");
 IDuyettratrungbay dttbBean = (IDuyettratrungbay)session.getAttribute("dttbBean"); %>
<% ResultSet scheme = (ResultSet)dttbBean.getSchemeRS() ; %>
<% ResultSet khRs = (ResultSet)dttbBean.getKhRs() ; %>
<% Hashtable<String, Integer> tratb = (Hashtable<String, Integer>)dttbBean.getTraTb(); %>
<% NumberFormat formatter = new DecimalFormat("#,###,###.##"); %>
<% String nnId = (String)session.getAttribute("nnId");
	boolean edit = dttbBean.getId().trim().length() > 0;
%> 
<% Utility Util = new Utility(); %>
<% if(nnId == null) {
	nnId = "vi"; 
	}	
String url = util.getChuyenNguUrl("DuyettratrungbaySvl", "",session);
	%>
	
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"> <% Utility Utilback = new Utility(); %>
<%@page import="java.sql.SQLException"%>
<HTML>
<HEAD>
<TITLE><%= getServletContext().getInitParameter("TITLENAME") %></TITLE>
<META http-equiv="Content-Type" content="text/html; charset=utf-8">

<META http-equiv="Content-Style-Type" content="text/css">
<script type="text/javascript" src="../scripts/jquery.min.1.7.js"></script>
<LINK rel="stylesheet" href="../css/main.css" type="text/css">
<link type="text/css" rel="stylesheet" href="../css/mybutton.css">
<SCRIPT language="JavaScript" type="text/javascript">
function submitform()
{
	document.forms['dttbForm'].setAttribute('enctype', '', 0);
    document.forms['dttbForm'].submit();
}

function save2()
{
	
	document.dttbForm.action.value='save';
	document.forms['dttbForm'].setAttribute('enctype', '', 0);
    document.forms['dttbForm'].submit();
}

function aptrungbay()
{
	document.dttbForm.action.value='aptrungbay';
	document.forms['dttbForm'].setAttribute('enctype', '', 0);
    document.forms['dttbForm'].submit();
}

function excel()
{

	/* var l = new Loading();
	l.render();
	l.show();
	l.hide_with_download('DuyettratrungbayNewSvl'); */
	document.dttbForm.action.value='excel';
	document.forms['dttbForm'].setAttribute('enctype', '', 0);
    document.forms['dttbForm'].submit();
}


function chot()
{
	document.dttbForm.action.value='chot';
	document.forms['dttbForm'].setAttribute('enctype', '', 0);
    document.forms['dttbForm'].submit();
}

function upload()
{
	if(document.forms["dttbForm"].filename.value=="")
	{
		alert("Vui lòng chọn file cần upload.");
    } else {
		document.forms['dttbForm'].setAttribute('enctype', "multipart/form-data", 0);
	    document.forms['dttbForm'].submit();
	    }
}

function duyetAnh(khId, loai, khMa, divId,dvtrangthaiAnhId)
{	
	
	var userId = document.getElementById("userId").value;
	var duyetId = '<%=dttbBean.getId()  %>';
	var e =  document.getElementById(divId);
	
	 
	var xmlhttp;
	   
	   if (window.XMLHttpRequest)
	   {// code for IE7+, Firefox, Chrome, Opera, Safari
	      xmlhttp = new XMLHttpRequest();
	   }
	   else
	   {// code for IE6, IE5
	      xmlhttp = new ActiveXObject("Microsoft.XMLHTTP");
	   }
	   xmlhttp.onreadystatechange=function()
	   {
		  
	       if (xmlhttp.readyState == 4 && xmlhttp.status == 200)
	       {
	         
	         
	         if(xmlhttp.responseText !='')
	         {
	        	 alert(xmlhttp.responseText);
	        	 
			 }
	         else
	         { 
	        	 if(loai == 1)
	        	 	document.getElementById(dvtrangthaiAnhId).innerHTML = "Đã duyệt";	
	        	 else if(loai == 2)
	        		 document.getElementById(dvtrangthaiAnhId).innerHTML = "Không duyệt";
	        	 e.style.display = "none";
	        	 alert('Thành công!');
	         }
	      } 
	   }
	   xmlhttp.open("POST","../../DuyetAnhTrungBayAjaxSvl?khId="+khId+"&duyetId=" + duyetId+"&loai=" + loai +"&userId=" + userId,true);
	   xmlhttp.send();

	
	
	
}

</SCRIPT>


<link href="../css/jquery-ui.css" rel="stylesheet" type="text/css" />

<!-- Khai bao su dung colorbox ajax -->
<link media="screen" rel="stylesheet" href="../css/colorbox.css">
<script src="../scripts/colorBox/jquery.colorbox.js"></script>


</HEAD>
<BODY leftmargin="0" bottommargin="0" topmargin="0" rightmargin="0">
<form name ="dttbForm" ENCTYPE="multipart/form-data" method="post" action="../../DuyettratrungbayNewSvl" >
<% redis.clients.jedis.Jedis jedis =  geso.dms.center.util.Utility.getJedis(); %><% geso.dms.center.util.Csrf csdr=new geso.dms.center.util.Csrf(request,response,true,false,true);%>
 <input type="hidden" name="<%=csdr.get_tokenName() %>" value='<%=csdr.get_tokenValue() %>'>

<input type="hidden" name="action" value="0">
<input type="hidden" name="userId" id="userId" value="<%= userId%>">
<input type="hidden" name="id" value="<%=dttbBean.getId()%>">

<TABLE width="100%" border="0" cellspacing="0" cellpadding="0"
	height="100%">
	<TR>
		<TD colspan="4" align='left' valign='top' bgcolor="#FFFFFF">
			<TABLE width="100%" border="0" cellpadding="0" cellspacing="1">
				<TR>
					<TD align="left" class="tbnavigation">
						<table width="100%" border="0" cellpadding="0" cellspacing="0">
						  <tr height="22">
						  <%if(dttbBean.getId().trim().length() > 0) {%>
						  	<TD align="left" colspan="2" class="tbnavigation">&nbsp;<%= url %> > <%=Utility.GLanguage("Cập nhật",session,jedis) %></TD>
						  <% } else {%>
						  	<TD align="left" colspan="2" class="tbnavigation">&nbsp;<%= url %> > <%=Utility.GLanguage("Tạo mới",session,jedis) %></TD>
						  <%} %>
							 <TD colspan="2" align="right" class="tbnavigation"><%=Utility.GLanguage("Chào mừng bạn",session,jedis) %>  <%=userTen %>&nbsp;  </TD></tr>
						</table>
			 		</TD>
				</TR>
			</TABLE>
		<TABLE width="100%" border="0" cellpadding="0" cellspacing="1">
			<TR ><TD >
					<TABLE width="100%" border="0" cellpadding="0" cellspacing="0">
						<TR class = "tbdarkrow">
							<TD width="2">&nbsp; </TD> 
	 						<TD>
	 						<A href= "../../RouterSvl?task=<%= Util.dongMa(getServletContext().getInitParameter("RedirectNoScript") +"DuyettratrungbaySvl?userId="+ userId +"")%>" >
		 						<img src="../images/Back.png" alt="Back"  title="Back" border="1" longdesc="Back" style="border-style:outset"></A>
		 					<%  if(dttbBean.getDaduyet().equals("0") ) { %>
									<A href="javascript:save2();"  >
										<img src="../images/Save30.png" alt="Luu lai"  title="Luu lai" border="1" longdesc="Luu lai" style="border-style:outset">
									</A>
								<% } %>
							</TD>
					
						</TR>
					</TABLE>
			</TD></TR>
		</TABLE>
			<TABLE width="100%" border="0" cellpadding="0"  cellspacing="1" >
			  	<tr>
					<TD align="left" colspan="4" class="legendtitle">
						<FIELDSET>
						<LEGEND class="legendtitle"><%=Utility.GLanguage("Báo lỗi nhập liệu",session,jedis) %></LEGEND>
				
	    				<textarea name="dataerror"  style="width: 100% ; color:#F00 ; font-weight:bold"  style="width:100%" rows="1"><%= dttbBean.getMessage() %></textarea>

						</FIELDSET>
				   </TD>
				</tr>			

				<TR>
				  <TD height="100%" width="100%">
						<FIELDSET >
						<LEGEND class="legendtitle" style="color:black"><%=Utility.GLanguage("Duyệt trả trưng bày",session,jedis) %></LEGEND>
						<TABLE border="0" width="100%" cellpadding="6" cellspacing="0">
						  <TR>
							  	<TD width="15%" class="plainlabel"><%=Utility.GLanguage("Chương trình",session,jedis) %></TD>
						  	  	<TD class="plainlabel">
						  	  		<%if(edit) { %>
														<input type="hidden" name="cttbId" value="<%=dttbBean.getSchemeId()%>">
													
													<%} %>
						  	  	
									<SELECT  <%=!edit ? "name=\"cttbId\""  : ""   %> <%=edit ? "disabled"  : ""   %>      >
								    	<option value=""></option>
								      <% try{while(scheme.next()){ 
								    	if(scheme.getString("pk_seq").equals(dttbBean.getSchemeId())){ %>
								      		<option value='<%=scheme.getString("pk_seq")%>' selected><%=scheme.getString("scheme") + "_" + scheme.getString("diengiai") %></option>
								      	<%}else{ %>
								     		<option value='<%=scheme.getString("pk_seq")%>'><%=scheme.getString("scheme") + "_" + scheme.getString("diengiai") %></option>
								     	<%}}}catch(java.sql.SQLException e){} %>	  
                       				</SELECT>						  	  	
						  	  	
						  	  	</TD>
						  </TR>
						  
						  <TR>
							  	<TD class="plainlabel"><%=Utility.GLanguage("Diễn giải",session,jedis) %></TD>
						  	  	<TD class="plainlabel">
						  	  		<input type="text" name = "diengiai" value = "<%=dttbBean.getDiengiai()%>">
						  	  	</TD>
						</TR>
						
<%if(dttbBean.getId().trim().length() > 0) { %>

						   <%  if(dttbBean.getDaduyet().equals("0") ) { %>
								  <TR>	
		 						  <TD class="plainlabel" colspan="2"><a class="button2" href="javascript:aptrungbay();">
									<img style="top: -4px;" src="../images/button.png" alt=""><%=Utility.GLanguage("Áp dụng trưng bày",session,jedis) %></a>&nbsp;&nbsp;&nbsp;&nbsp;
		 						  </TD>
								 </TR>
						  <%} %>

							 <TR>	
		 						  <TD class="plainlabel" colspan="2"><a class="button2" href="javascript:excel();">
									<img style="top: -4px;" src="../images/button.png" alt=""><%=Utility.GLanguage("Excel",session,jedis) %></a>&nbsp;&nbsp;&nbsp;&nbsp;
		 						  </TD>
								 </TR>
						  <TR>
							  	<TD class="plainlabel"><%=Utility.GLanguage("Chọn tập tin",session,jedis) %></TD>
						  	  	<TD class="plainlabel"><INPUT type="file" name="filename" size="40" value=''>
						  	  	&nbsp;&nbsp;&nbsp;&nbsp;
						  	  	
						  	  	<a class="button2" href="javascript:upload();">
							<img style="top: -4px;" src="../images/button.png" alt=""><%=Utility.GLanguage("Upload",session,jedis) %></a>
						  	  	
						  	  	</TD>
						  </TR>
						  
						 
						  
						  <%-- <TR>
						  							  	
 						   <TD><a class="button2" href="javascript:upload();">
							<img style="top: -4px;" src="../images/button.png" alt=""><%=ChuyenNgu.get("Upload",nnId) %></a>&nbsp;&nbsp;&nbsp;&nbsp;
 						  </TD>
						  </TR> --%>
						  
 						  
<%} %>
						</TABLE>
						</FIELDSET>
					</TD>
				</TR>
				
				<%if(dttbBean.getId().trim().length() > 0) { %>
				<TR>
				  <TD height="100%" width="100%">
						<FIELDSET >
						<LEGEND class="legendtitle" style="color:black"><%=Utility.GLanguage("Khách hàng",session,jedis) %> </LEGEND>
						<TABLE border="0" width="100%" cellpadding="5" cellspacing="1">
							<tr class="tbheader">
								<TH width="5%" ><%=Utility.GLanguage("STT",session,jedis) %></TH>
								<TH width="5%" ><%=Utility.GLanguage("Mã NVBH",session,jedis) %></TH>
								<TH width="10%" ><%=Utility.GLanguage("TÊN NVBH ",session,jedis) %></TH>
							  	<TH width="5%" ><%=Utility.GLanguage("Mã KH",session,jedis) %></TH>
							  	<TH width="15%" ><%=Utility.GLanguage("Tên khách hàng",session,jedis) %></TH>
							  	<TH width="15%" ><%=Utility.GLanguage("Nhà phân phối",session,jedis) %></TH>
							  	<TH width="10%" ><%=Utility.GLanguage("Duyệt đăng ký",session,jedis) %></TH>
							  	<TH width="10%" ><%=Utility.GLanguage("Doanh số",session,jedis) %></TH>
							  	<TH width="7%" ><%=Utility.GLanguage("Đạt",session,jedis) %></TH>
							  	<TH width="7%" ><%=Utility.GLanguage("Duyệt trả",session,jedis) %></TH>
							  	<TH width="7%" ><%=Utility.GLanguage("Trạng thái",session,jedis) %> ảnh</TH>
							  	<TH width="7%" ><%=Utility.GLanguage("Hình ảnh",session,jedis) %></TH>
							  	
						  </tr>
						  <% try{
							    String lightrow = "tblightrow";
								String darkrow = "tbdarkrow";
								int m = 0;
								String[] arrTrangthaiAnh = { "Chưa duyệt","Đã duyệt","Không duyệt" };
								
								if(khRs != null){
									while(khRs.next()){ 
									
										String nut = "#nutmopopup" + Integer.toString(m + 1);
										String nut2 = "#nutmopopup" + Integer.toString(m + 2);
										String popup = "#noidungpopup"	+ Integer.toString(m + 1);
										
										String popup2 = "#noidungpopup"+ Integer.toString(m + 2);
										
										String nut_id = "nutmopopup" + Integer.toString(m + 1);
										String nut_id2 = "nutmopopup" + Integer.toString(m + 2);
										String popup_id = "noidungpopup"+ Integer.toString(m + 1);
										String popup_id2 = "noidungpopup"+ Integer.toString(m + 2);

										
										int duyetAnh = khRs.getInt("duyetAnh");
										String trangthaiAnh = "";
										if(duyetAnh >=0 && duyetAnh <= arrTrangthaiAnh.length)
											trangthaiAnh = arrTrangthaiAnh[duyetAnh];
										
										String anh_dang_ky = khRs.getString("AnhDK") != null ? khRs.getString("AnhDK") : "";
										String thoidiemAnhDK = khRs.getString("thoidiemAnhDK");
										
										if (m % 2 != 0) {%>						
										<TR class= <%=lightrow%> >
									<%} else {%>
										<TR class= <%= darkrow%> >
									<%}%>
											<TD align="center"><%=m+1%></TD>      
											
											
											<TD align="left"><%=khRs.getString("DDKDMA") %></TD>
											<TD align="left"><%=khRs.getString("DDKDTEN") %></TD>
											<TD align="center">
											<input type="hidden" name = "mafast" value="<%=khRs.getString("MAFAST")%>">
												<%= khRs.getString("MAFAST")%></TD>                                   
											<TD align="left"><%=khRs.getString("khTen") %></TD>  
											<TD align="left"><%=khRs.getString("NPP") %></TD>  		
											
											<TD align="center"><%=khRs.getString("SUATDUYETDK") %></TD>			
											<TD align="center"><%= formatter.format(khRs.getDouble("doanhso")) %></TD>
											<TD align="center"><%=khRs.getString("XuatDeNghi") %></TD>
																	                               
							  				<TD align="center">
							  					<input type="hidden" name = "tt_<%=khRs.getString("MAFAST")%>" value="<%=khRs.getString("SOXUAT")%>">
							  					<%=formatter.format(khRs.getDouble("SOXUAT")) %>
							  				</TD>
							  			
							  				<TD align="center"><div id="divTrangThaiAnh_<%=khRs.getString("khId") %>" ><%=trangthaiAnh %></div> </TD>
							  				<TD align="center">
							  					<%if(anh_dang_ky  != null) { %>
													<TABLE border = 0 cellpadding="0" cellspacing="0">
														<TR>
														<TD>																
																<!-- Noi nay chua anh va nut DONG -->
																<a id="<%=nut_id%>"   href="#">
																	<img src="../images/Display20.png" width="20" height="20" alt="Xem" ; >
																	
																	<script>
																		$(document).ready(function()
																		{
																			$("<%=nut%>").colorbox({width:"100%", inline:true, href:"<%=popup%>", top: "50"});        	
																		});
																		
																		
																		$(document).ready(function(){
																		
																			$("<%=nut%>").colorbox({rel:'group4', slideshow:false});
																		});
																		
																		
																		
																		
																	 </script>
																</a>
																
																<!-- Khung Colorbox tam thoi an di -->
																<div style='display:none'>
																	<div id="<%=popup_id%>" style='padding:0px 5px; background:#fff;'>
																		<table   cellpadding="4px" cellspacing="2px" width="100%"  align="center" >
																			
																			<%
																			JsonArray jsonArray = null;
																			if( khRs.getString("hinhanh")  != null )
																				jsonArray = (JsonArray) new JsonParser().parse(khRs.getString("hinhanh"));
																		 
																			int conspanTD  = jsonArray != null && jsonArray.size() > 0 ? jsonArray.size() + 1 : 1;
																			%>
																			
																			<tr >
																				<TD align="center" colspan="<%=conspanTD  %>"><%=Utility.GLanguage("Mã",session,jedis) %>: <%=khRs.getString("MAFAST")%> - Tên: <%=khRs.getString("khTen")%></TD>
																	
																			</tr>
																			<%if(duyetAnh == 0){ %>
																			<tr >
																				<TD align="center" colspan=<%=conspanTD%> >
																					<div align="center" id ="divDuyet_<%=khRs.getString("khId") %>" >
																						<A href = "javascript:duyetAnh('<%=khRs.getString("khId") %>', 1, '<%=khRs.getString("MAFAST")%>','divDuyet_<%=khRs.getString("khId") %>','divTrangThaiAnh_<%=khRs.getString("khId") %>')"><img src="../images/Chot.png" alt="Duyệt" title="Duyệt" width="20" height="20" longdesc="Duyệt" border=0 onclick="if(!confirm('Bạn muốn duyệt ảnh KH này?')) return false;"></A>
																						<A href = "javascript:duyetAnh('<%=khRs.getString("khId") %>', 2, '<%=khRs.getString("MAFAST")%>','divDuyet_<%=khRs.getString("khId") %>','divTrangThaiAnh_<%=khRs.getString("khId") %>')"><img src="../images/Delete20.png" alt="Xóa" title="Không duyệt" width="20" height="20" longdesc="Xóa" border=0 onclick="if(!confirm('Bạn Có Muốn không Duyệt ảnh KH Này?')) return false;"></A>									
																					</div>
																				</TD> 
																			</tr>
																			<%} %>
																			
																			
																			<tr >
																			
																					<td >
																					<img style="top: -4px; max-width: 300px; max-height: 300px;" src="<%= getServletContext().getInitParameter("pathhinhJSP")  +"AnhTrungBay/"+ anh_dang_ky %>" alt="">																																							
																					</td>
																					<%
																					if(jsonArray != null)
																					for(int xxx = 0 ; xxx < jsonArray.size(); xxx++)
																					{ 
																						JsonObject rec = (JsonObject) jsonArray.get(xxx);
												                    					String imagePath= rec.get("imagePath").getAsString();
																						%>
																						<td >
																						<img style="top: -4px; max-width: 300px; max-height: 300px;" src="<%= getServletContext().getInitParameter("pathhinhJSP")  +"AnhTrungBay/"+ imagePath %>"  alt="">																																							
																						</td>
																					<%} %>
																			<tr >
																			<tr>
																				
																					<td >
																						<a target='_blank'  class="button" href="<%= getServletContext().getInitParameter("pathhinhJSP")  +"AnhTrungBay/"+ anh_dang_ky %>"> 
																					Ảnh đăng ký: <%=thoidiemAnhDK %>	
																						</a>																																						
																					</td>
																					<%
																					if(jsonArray != null)
																					for(int xxx = 0 ; xxx < jsonArray.size(); xxx++)
																					{ 
																						JsonObject rec = (JsonObject) jsonArray.get(xxx);
												                    					String imagePath= rec.get("imagePath").getAsString();
												                    					String thoidiem= rec.get("thoidiem").getAsString();
																						%>
																						<td >																																																										
																							<a target='_blank'  class="button" href="<%= getServletContext().getInitParameter("pathhinhJSP")  +"AnhTrungBay/"+ imagePath %>"> 
																								Ảnh trưng bày: <%=thoidiem %>	
																							</a>
																						
																						</td>
																					<%} %>
																			</tr>
																			
																			
																		</table>
																	</div>
																</div>
																
														</TD>
														</TR>
							  							</TABLE>
							  							<%} %>				  			
							  						</TD>
										</TR>
						  		<% m++ ;} 
						  		
						  		}%>		
						  		
						  <%}catch(java.sql.SQLException e){}%>
							<tr class="tbfooter"><td colspan="12" > &nbsp; </td></tr>
						</TABLE>
						</FIELDSET>
					</TD>
				
				
				</TR>
				<%} %>
			</TABLE>
		</TD>
		</TR>
		</TABLE>
		<%geso.dms.center.util.Utility.JedisClose(jedis); %>
		</form>
		</body>   
		<!-- <script type='text/javascript' src='../scripts/loadingv2.js'></script> --> 
		</HTML>
