<%@page import="com.cete.dynamicpdf.text.bd"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page  import = "geso.dms.center.beans.bandott.IBandott" %>
<%@ page  import = "geso.dms.center.beans.bandott.imp.Bandott" %>
<%@ page  import = "java.sql.ResultSet" %>
<%@ page  import = "geso.dms.center.util.*" %>
<%	String userId = (String) session.getAttribute("userId");  
	String userTen = (String) session.getAttribute("userTen");  	
	String sum = (String) session.getAttribute("sum");
	Utility util = (Utility) session.getAttribute("util");
	if(!util.check(userId, userTen, sum)) {
		response.sendRedirect(request.getContextPath() + "/redirect.jsp");
	} else{ %>

<% IBandott khBean = (IBandott)session.getAttribute("obj"); %>
<% ResultSet vungRs = khBean.getVungRs(); %>
<% ResultSet kvRs = khBean.getKvRs(); %>
<% ResultSet nppRs = khBean.getNppRs(); %>
<% ResultSet ddkd = khBean.getDdkdRs(); %>
<% ResultSet tbh = khBean.getTbhRs(); %>
<% ResultSet khlist = khBean.getKhChuaViengThamRs(); %>


<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd"> <% Utility Utilback = new Utility(); %>
<html>
<head>
	<TITLE><%= getServletContext().getInitParameter("TITLENAME") %></TITLE>
	<META http-equiv="Content-Type" content="text/html; charset=utf-8">
    
    <script type="text/javascript">

    
    
    </script>
    
    
    
    
    
    <LINK rel="stylesheet" type="text/css" media="print" href="../css/impression.css">
	<LINK rel="stylesheet" href="../css/main.css" type="text/css">

   	<script type="text/javascript" src="../scripts/jquery.min.1.7.js"></script>
	<link href="../css/jquery-ui.css" rel="stylesheet" type="text/css" />
	<script src="../scripts/ui/jquery.ui.core.js" type="text/javascript"></script>
	<script src="../scripts/ui/jquery.ui.widget.js" type="text/javascript"></script>
	
	<!-- Khai bao su dung colorbox ajax -->
<link media="screen" rel="stylesheet" href="../css/colorbox.css">
<script src="../scripts/colorBox/jquery.colorbox.js"></script>
<script>
	$(document).ready(function() {
		var id_anh = document.getElementsByName("id_anh");
		for (var i = 0; i < id_anh.length; i++) {
			var id = id_anh.item(i).value;
			$("#HienThiAnh_"+id).colorbox({
				width : "60%",
				inline : true,
				href : "#noidungpopup_"+id,
				top : "50"
			});
		}
	});

	$(document).ready(function() {
		var id_anh = document.getElementsByName("id_anh");
		for (var i = 0; i < id_anh.length; i++) {
			var id = id_anh.item(i).value;
			//console.log("vào 2 #HienThiAnh_"+id);
			$("#HienThiAnh_"+id).colorbox({
				inline : true,
				//href:"#ef",
				close : "Ðóng",
				opacity : 0.95,
				onClosed : function() {
					$.colorbox.close();
				}
			});
		}
	});
</script>

<script type="text/javascript">

  	
  		function xoatoado(khId) {
  			if(confirm("Bạn có chắc chắn muốn xóa tọa độ khách hàng này không ?") && typeof(khId) !== "undefined") {
	  			$("#khId").val(khId);
	  			document.forms["bdForm"].submit();
	  		}
  		}
	  	function submitform()
		{
	  		document.forms['bdForm'].action.value= 'search';
		    document.forms["bdForm"].submit();
		}
	  	function xuatExcel()
	  	{
	  		document.forms['bdForm'].action.value= 'excel';
	  		document.forms['bdForm'].submit();
	  		
	  	}
  	</script>
  	<link href="../css/select2.css" rel="stylesheet" />
	<script src="../scripts/select2.js"></script>
    <script>
		$(document).ready(function()
		{
			$(".select2").select2();
		});
	</script>
</head>

<body>
	<form name="bdForm" method="post" action="../../BandoTTSvl">
	<% redis.clients.jedis.Jedis jedis =  geso.dms.center.util.Utility.getJedis(); %><% geso.dms.center.util.Csrf csdr=new geso.dms.center.util.Csrf(request,response,true,false,true);%>
 <input type="hidden" name="<%=csdr.get_tokenName() %>" value='<%=csdr.get_tokenValue() %>'>
	
		<input type="hidden" name="userId" value='<%= userId %>'>
		<input type="hidden" name="view" value='khachhangToado'>
		<input type="hidden" name="action" id="action" value='1'>
		<input type="hidden" name="trungtam" id="trungtam" value=''>
		<input type="hidden" name="khId" id="khId" value="">
		
		<div id="main" style="width:99.5%; padding-left:2px">
		
			<div align="left" id="header" style="width:100%; float:none">
		    	<div style="width:40%; padding:5px; float:left" class="tbnavigation">
		        	Dữ liệu nền > Kinh doanh > Tọa độ khách hàng
		        </div>
		        <div align="right" style="padding:5px" class="tbnavigation">
		        	<%=Utility.GLanguage("Chào mừng bạn",session,jedis) %>  <%= userTen %> &nbsp;&nbsp;
		        </div>
		    </div>
		    
		    <div style="width:100%; float:none" align="left">
		    <fieldset>
		    	<legend class="legendtitle"><%=Utility.GLanguage("Thông tin khách hàng",session,jedis) %></legend>
		        	<TABLE width="100%" cellpadding="4" cellspacing="0">            
			            <TR>
			                <TD class="plainlabel" width="150px" ><%=Utility.GLanguage("Vùng",session,jedis) %> </TD>
			                <TD class="plainlabel" width="240px">
			                    <select name="vung" id="vung" onChange = "submitform();">
			                            <option value="" selected></option>
			                            <% if(vungRs != null){
											  try{ while(vungRs.next()){ 
								    			if(vungRs.getString("pk_seq").equals(khBean.getVungId())){ %>
								      				<option value='<%= vungRs.getString("pk_seq")%>' selected><%= vungRs.getString("ten") %></option>
								      			<%}else{ %>
								     				<option value='<%= vungRs.getString("pk_seq")%>'><%= vungRs.getString("ten") %></option>
								     	<%}} vungRs.close(); }catch(java.sql.SQLException e){} }%>
			                        </select>    
			                </TD>
			                <TD class="plainlabel" width="150px"><%=Utility.GLanguage("Khu vực",session,jedis) %> </TD>
			                <TD class="plainlabel">
			                    <select name="khuvuc" id="khuvuc" onChange = "submitform();">
			                            <option value="" selected></option>
			                            <% if(kvRs != null){
											  try{ while(kvRs.next()){ 
								    			if(kvRs.getString("pk_seq").equals(khBean.getkvId())){ %>
								      				<option value='<%= kvRs.getString("pk_seq")%>' selected><%= kvRs.getString("ten") %></option>
								      			<%}else{ %>
								     				<option value='<%= kvRs.getString("pk_seq")%>'><%= kvRs.getString("ten") %></option>
								     	<%}} kvRs.close(); }catch(java.sql.SQLException e){} }%>
			                        </select>    
			                </TD>
			            </TR>
			            <TR>
			                <TD class="plainlabel" ><%=Utility.GLanguage("Nhà phân phối",session,jedis) %> </TD>
			                <TD class="plainlabel">
			                    <select name="npp" id="npp" class="select2" style="width: 200px;" onChange = "submitform();">
			                            <option value="" selected></option>
			                            <% if(nppRs != null){
											  try{ while(nppRs.next()){ 
								    			if(nppRs.getString("pk_seq").equals(khBean.getNppId())){ %>
								      				<option value='<%= nppRs.getString("pk_seq")%>' selected><%= nppRs.getString("ten") %></option>
								      			<%}else{ %>
								     				<option value='<%= nppRs.getString("pk_seq")%>'><%= nppRs.getString("ten") %></option>
								     	<%}} nppRs.close(); }catch(java.sql.SQLException e){} }%>
			                        </select>
			                </TD>
			                <TD class="plainlabel" ><%=Utility.GLanguage("NHÂN VIÊN BÁN HÀNG",session,jedis) %> </TD>
			                <TD class="plainlabel">
			                    <select name="ddkd" id="ddkd" class="select2" style="width: 200px;" onChange = "submitform();">
			                            <option value="" selected></option>
			                            <% if(ddkd != null){
											  try{ while(ddkd.next()){ 
								    			if(ddkd.getString("ddkdId").equals(khBean.getDdkdId())){ %>
								      				<option value='<%= ddkd.getString("ddkdId")%>' selected><%= ddkd.getString("ddkdTen") %></option>
								      			<%}else{ %>
								     				<option value='<%= ddkd.getString("ddkdId")%>'><%= ddkd.getString("ddkdTen") %></option>
								     	<%}} ddkd.close(); }catch(java.sql.SQLException e){} }%>
			                        </select>    
			                </TD>
		            	</TR>
		            	<TR>
		               
		                <TD class="plainlabel" ><%=Utility.GLanguage("Tuyến bán hàng",session,jedis) %> </TD>
		                <TD class="plainlabel" >
		                    <select name="tbh" id="tbh" onChange = "submitform();">
		                          <option value="" selected></option>
		                          <% if(tbh != null){
								  try{ while(tbh.next()){ 
					    			if(tbh.getString("tbhId").equals(khBean.getTbhId())){ %>
					      				<option value='<%= tbh.getString("tbhId")%>' selected><%= tbh.getString("tbhTen") %></option>
					      			<%}else{ %>
					     				<option value='<%= tbh.getString("tbhId")%>'><%= tbh.getString("tbhTen") %></option>
					     	<%}} tbh.close(); }catch(java.sql.SQLException e){} }%>
		                      </select>    
		               </TD>
		               <TD class="plainlabel">Mã DMS<%=Utility.GLanguage("",session,jedis) %></TD>
								   		<TD class="plainlabel"><INPUT name="maFAST" type="text" value="<%= khBean.getMafast() %>" size="40" onChange = "submitform();">
		               
		            </TR>
		            
		            <TR>
					<TD class="plainlabel" colspan="4">
					<a class="button2" href="javascript:xuatExcel()"> <img style="top: -4px;" src="../images/button.png" alt=""><%=Utility.GLanguage("Xuất Excel",session,jedis) %> </a> &nbsp;&nbsp;&nbsp;&nbsp;
					 </TD>
					</TR>
		        </TABLE>
		     	<hr>
		    
				<TABLE width="100%" border="0" cellspacing="0" cellpadding="2">
					<TR>
						<TD width="100%">
							<TABLE class="" width="100%">
								<TR>
									<TD width="98%">
										<TABLE width="100%" border="0" cellspacing="1" cellpadding="4">
											<TR class="tbheader">
												<TH width="4%"><%=Utility.GLanguage("STT",session,jedis) %></TH>
											<!-- 	<TH width="0%" align="center">Mã khách hàng</TH> -->
												<TH width="10%" align="center"><%=Utility.GLanguage("Mã Fast",session,jedis) %></TH>
												<TH width="20%" align="center"><%=Utility.GLanguage("Tên khách hàng",session,jedis) %></TH>
												<!-- <TH width="0%" align="center">Điện thoại</TH> -->
												<TH width="15%" align="center"><%=Utility.GLanguage("Người mua hàng",session,jedis) %></TH>
												<TH width="20%" align="center"><%=Utility.GLanguage("Địa chỉ",session,jedis) %></TH>
												<TH width="8%" align="center"><%=Utility.GLanguage("Vĩ độ",session,jedis) %></TH>
												<TH width="8%" align="center"><%=Utility.GLanguage("Kinh độ",session,jedis) %></TH>
												<TH width="10%" align="center"><%=Utility.GLanguage("Ảnh cửa hàng",session,jedis) %></TH>
												<TH width="10%" align="center"><<%=Utility.GLanguage("Xóa tọa độ",session,jedis) %>/TH>
											</TR>
										
							<%  														
		                        int m = 0;
		                        String lightrow = "tblightrow";
		                        String darkrow = "tbdarkrow";
								if(khlist!=null)
								{%>
								<% try{								
		                               while (khlist.next()){
		                                   	
		                                  	if (m % 2 != 0) {%>                     
		                                   	<TR class= <%=lightrow%> >
		                                   <%} else {%>
		                                      	<TR class= <%= darkrow%> >
		                                   	<%}%>
		                                   		<TD align="center"><%=m+1 %></TD>
												<%-- <TD align="left"><div align="center"><%=khlist.getString("khId") %></div></TD>  --%>
												<TD align="center"><%=khlist.getString("mafast") %></TD>                                  
												<TD align="center"><%= khlist.getString("khTen")%></TD>
												<%-- <TD align="center"><%= khlist.getString("dienthoai")%></TD> --%>
												<TD align="center"><%= khlist.getString("CHUCUAHIEU")%></TD>
												<TD align="center"><%= khlist.getString("diachi")%></TD>												
												<TD align="center"><%=khlist.getString("lat")%></TD>
												<TD align="center"><%=khlist.getString("lon")%></TD>
												<td>
												<% 
												String id_anh = khlist.getString("anhchup");
												String id_anh_replace = id_anh;
												if (id_anh_replace != null && id_anh_replace.length() > 0)
												{
													id_anh_replace = id_anh_replace.replace(".","");
													id_anh_replace = id_anh_replace.replace("#","");
												}
												else {
													id_anh_replace = "";
												}
												%>
												<a id = "HienThiAnh_<%=id_anh_replace%>" href = "#">
												<img style=" width: 100px; height: 100px;" src="<%=getServletContext().getInitParameter("pathhinhJSP") + "AnhDaiDien/"+id_anh %>" >																																							
												</a>
												
												<div style='display:none'>
												<div id="noidungpopup_<%=id_anh_replace%>" style='padding:0px 5px; background:#fff;'>
													<table cellpadding="4px" cellspacing="2px" width="100%" align="center" >
														<tr>
															<td>
															<img style="top: -4px; max-width: 800px; max-height: 1000px;" src="<%=getServletContext().getInitParameter("pathhinhJSP") + "AnhDaiDien/"+id_anh%>">																																							
															</td>
														</tr>
													</table>
												</div>
												</div>		
												<input type="hidden" name = "id_anh" value = "<%=id_anh_replace%>">
												</td>	
												<TD align="center">
													<TABLE border = 0 cellpadding="0" cellspacing="0">
														<TR>
															<TD>
																<A href = "javascript:xoatoado(<%=khlist.getString("pk_seq") %>);"><img src="../images/Delete20.png" alt="Xoa toa do" title="Xóa tọa độ" width="20" height="20" longdesc="Xoa toa do" border = 0></A>
															</TD>												
														</TR>
													</TABLE>
												</TD>
											</TR>
									<%m++; }}catch(java.sql.SQLException e){}
								}%>
									
											 							
										</TABLE>
			
								</TABLE>
							</TD>
						</TR>
					</TABLE>
		    
		    	</fieldset>
		    </div>
		 </div>
	 </form>
	 <script>
		var msg = "<%= khBean.getMsg() %>".trim();
		if(msg.length > 0) {
			alert(msg);
		}
		
	</script>
</body>

</HTML>

<% khBean.DBclose(); %>
<%}%>