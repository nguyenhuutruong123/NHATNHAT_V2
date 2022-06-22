<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page  import = "geso.dms.distributor.beans.ctchietkhau.ICtChietKhau" %>
<%@ page  import = "java.sql.ResultSet" %>
<%@ page  import = "geso.dms.center.util.*" %>
<%@ page  import = "java.text.DecimalFormat" %>
<%@ page  import = "java.text.NumberFormat" %>
<%@ page  import = "java.util.Hashtable" %>


<%	String userId = (String) session.getAttribute("userId");  
	String userTen = (String) session.getAttribute("userTen");  	
	String sum = (String) session.getAttribute("sum");
	Utility util = (Utility) session.getAttribute("util");
	if(!util.check(userId, userTen, sum)){
		response.sendRedirect(request.getContextPath() + "/redirect.jsp");
	}else{ %>

<% ICtChietKhau bgblcBean = (ICtChietKhau)session.getAttribute("bgblcBean"); %>
<% 

%>
<% ResultSet sanpham = (ResultSet)bgblcBean.getSanPhamList();
	ResultSet khRs = (ResultSet)bgblcBean.getKhRs();
	ResultSet hopdongRs = (ResultSet)bgblcBean.getHopdongRs();
	ResultSet bacsiRs = (ResultSet)bgblcBean.getBacsiRs();
	String nppArr[] = bgblcBean.getNppArr();
	NumberFormat formatter = new DecimalFormat("#,###,####");
	NumberFormat formatter2 = new DecimalFormat("#,###,###.####");
	
	Hashtable<String,Double> sanpham_chietkhau = bgblcBean.getSanpham_chietkhau();
	
	
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"> <% Utility Utilback = new Utility(); %>
<HTML>
<HEAD>
<TITLE><%= getServletContext().getInitParameter("TITLENAME") %></TITLE>
<META http-equiv="Content-Type" content="text/html; charset=utf-8">

<META http-equiv="Content-Style-Type" content="text/css">

<LINK rel="stylesheet" href="../css/main.css" type="text/css">
<script type="text/javascript"	src="../scripts/jquery.min.1.7.js"></script>
<script type="text/javascript" language="JavaScript" src="../scripts/jquery.tools.min.js"></script>
<LINK rel="stylesheet" type="text/css" media="screen" href="../css/tabs.css">
<LINK rel="stylesheet" type="text/css" media="screen" href="../css/tabs-panes.css">
<link href="../css/jquery-ui.css" rel="stylesheet" type="text/css" />
<script src="../scripts/ui/jquery.ui.core.js" type="text/javascript"></script>
<script src="../scripts/ui/jquery.ui.widget.js" type="text/javascript"></script>
<script src="../scripts/ui/jquery.ui.datepicker.js" type="text/javascript"></script>
<script type="text/javascript">
	$(document).ready(function() {		
		$( ".days" ).datepicker({			    
				changeMonth: true,
				changeYear: true				
		});            
	});	
</script>

<script>
$(function() {
 
 	$("ul.tabs").tabs("div.panes > div");
});
</script>

<SCRIPT language="javascript" type="text/javascript">
<%if( !bgblcBean.getDisplay().equals("1") &&  !bgblcBean.getTrangthai().equals("1")){ %>


	function RemoveDisable()
	{
		/*  document.getElementById("dvkdId").removeAttribute('disabled');
		 document.getElementById("kbhId").removeAttribute('disabled');
	 */
	}

	function upload() {
	
		if (document.forms["bgblcForm"].filename.value == "") {
			document.forms["bgblcForm"].dataerror.value = "Chưa có file upload. Vui lòng chọn file cần upload.";
		} 
		else {
			document.forms["bgblcForm"].setAttribute('enctype',
					"multipart/form-data", 0);
			document.forms["bgblcForm"].submit();
		}
	}
	
	function sellectAll(cbId1, cbId2) {
		var chkAll_Lct = document.getElementById(cbId1);
		var loaiCtIds = document.getElementsByName(cbId2);
	
		if (chkAll_Lct.checked) {
			for ( var i = 0; i < loaiCtIds.length; i++) {
				loaiCtIds.item(i).checked = true;
			}
		} else {
			for ( var i = 0; i < loaiCtIds.length; i++) {
				loaiCtIds.item(i).checked = false;
			}
		}
	}

	function submitform()
	{   
		RemoveDisable();	
	   document.forms["bgblcForm"].submit();
	}
	
	 function saveform()
	{
		 RemoveDisable();
		document.forms['bgblcForm'].action.value='save';
	    document.forms["bgblcForm"].submit();
	}
 
 <%}%>
	 function DinhDangTien(num) 
	 {
	    num = num.toString().replace(/\$|\,/g,'');
	    if(isNaN(num))
	    num = "0";
	    sign = (num == (num = Math.abs(num)));
	    num = Math.floor(num*100+0.50000000001);
	    num = Math.floor(num/100).toString();
	    for (var i = 0; i < Math.floor((num.length-(1+i))/3); i++)
	    num = num.substring(0,num.length-(4*i+3))+','+
	    num.substring(num.length-(4*i+3));
	    return (((sign)?'':'-') + num);
	}
	function Dinhdang(element)
	{
		element.value=DinhDangTien(element.value);
		if(element.value== '' ||element.value=='0' )
		{
			element.value= '';
		}
	}
</SCRIPT>

</HEAD>
<BODY leftmargin="0" bottommargin="0" topmargin="0" rightmargin="0">
<form name="bgblcForm" method="post" action="../../CtChietKhauUpdateSvl">
<input type="hidden" name="userId" value='<%=bgblcBean.getUserId() %>'>
<input type="hidden" name="action" value='1'>
 
<TABLE width="100%" border="0" cellspacing="0" cellpadding="0" height="100%">
	<TR>
		<TD colspan="4" align='left' valign='top' bgcolor="#FFFFFF">
		
			<TABLE width="100%" cellpadding="0" cellspacing="1">
				<TR>
					<TD align="left" class="tbnavigation">
					   <table width="100%" border="0" cellpadding="0" cellspacing="0">
						   <tr height="22">
 							   <TD align="left" colspan="2" class="tbnavigation">
 							   		&nbsp;Dữ liệu nền &gt;Sản phẩm &gt;Chính sách bán hàng&gt; Tạo mới </TD>
 							   <TD colspan="2" align="right" class="tbnavigation"><%=Utility.GLanguage("Chào mừng bạn",session,jedis) %>  <%=userTen %>&nbsp;</td>
					     </tr>
						</table>
					 </TD>
				  </TR>	
		  	</TABLE>
			<TABLE width="100%" cellpadding="0" cellspacing="1">
			<TR ><TD >
				<TABLE width="100%" border="0" cellpadding="0" cellspacing="0">
					<TR class = "tbdarkrow">
						<TD width="30" align="left"><A href="../../CtChietKhauSvl?userId=<%=userId %>" ><img src="../images/Back30.png" alt="Quay ve"  title="Quay ve" border="1" longdesc="Quay ve" style="border-style:outset"></A></TD>
					    <TD width="2" align="left" ></TD>
					    <TD width="30" align="left"  >
					    <%if( !bgblcBean.getDisplay().equals("1") &&  !bgblcBean.getTrangthai().equals("1")){ %>
					    <A href="javascript: saveform()" ><IMG src="../images/Save30.png" title="Luu lai" alt="Luu lai" border = "1"  style="border-style:outset"></A>
					   <%} %>
					    </TD>
				    	<TD align="left" >&nbsp;</TD>
					</TR>
				</TABLE>
			</TD></TR>
			</TABLE>

		<TABLE width="100%"  border = "0" cellspacing="0" cellpadding="0">
			  	<tr>
					<TD align="left" colspan="4" class="legendtitle">
						<FIELDSET>
						<LEGEND class="legendtitle">Bão lỗi nhập liệu </LEGEND>
				
	    				<textarea name="dataerror"  style="width: 100% ; color:#F00 ; font-weight:bold" rows="1" style="width: 100%" readonly="readonly" ><%=bgblcBean.getMessage()%></textarea>
						<% bgblcBean.setMessage(""); %>
						</FIELDSET>
				   </TD>
				</tr>		
				
					<TR>
									<TD width="120px" class="plainlabel">Khách hàng</TD>
										<TD class="plainlabel" width="250px" >
										<SELECT name="khId" id = "khId" onChange = "submitform();">
											  <option value=""> </option>
											  <% if(khRs != null){
											  	try{ while(khRs.next()){ 
									    			if(khRs.getString("pk_seq").equals(bgblcBean.getKhId())){ %>
									      				<option value='<%=khRs.getString("pk_seq")%>' selected><%=khRs.getString("ten") %></option>
									      			<%}else{ %>
									     				<option value='<%=khRs.getString("pk_seq")%>'><%=khRs.getString("ten") %></option>
									     	<%}} khRs.close(); }catch(Exception e){ e.printStackTrace(); } }%>	 
	                                    </SELECT>
	                                    </TD>
	                                 <TD width="120px" class="plainlabel">Hợp đồng</TD>
										<TD class="plainlabel" width="250px" >
										<SELECT name="hopdongId" id = "hopdongId"   onChange = "submitform();" >
											  <option value=""> </option>
											  <% if(hopdongRs != null){
											  	try{ while(hopdongRs.next()){ 
									    			if(hopdongRs.getString("PK_SEQ").equals(bgblcBean.getHopdongId())){ %>
									      				<option value='<%=hopdongRs.getString("PK_SEQ")%>' selected><%=hopdongRs.getString("MaHopDong") %></option>
									      			<%}else{ %>
									     				<option value='<%=hopdongRs.getString("PK_SEQ")%>'><%=hopdongRs.getString("MaHopDong") %></option>
									     	<%}} hopdongRs.close(); }catch(Exception e){ e.printStackTrace(); } }%>	 
	                                    </SELECT>
	                                    </TD>   
	                                    
					</TR>
								
								
								
	
								
	
					
		 						<TR>
								<TD class="plainlabel" style="width:300px"><%=Utility.GLanguage("Diễn giải",session,jedis) %> <FONT class="erroralert">*</FONT></TD>
								<TD class="plainlabel" colspan="3">
								  <input name="bgTen" type="text" value="<%= bgblcBean.getTen() %>" style="width:300px">
							  	</TD> 
							  	</TR>
							  	
							  	<TR>
								 <td  class="plainlabel">
							  	  	<INPUT type="file" name="filename" size="40" value=''> 
							  	  </td>
							  	  <td class="plainlabel" colspan="3"> 
							  	  	<a class="button2" href="javascript:upload()"><img style="top: -4px;" src="../images/button.png" alt="">Upload</a>
							  	  </td> 
						  	 	</TR>
							  	
				<TR>
				
        		<TD align="left" colspan="4" class="legendtitle">	
				<TABLE width="100%" border="0" cellspacing="1" cellpadding="4">
										<tr class="tbheader">
										<td colspan="4">
										<ul class="tabs">
										
									
			                  			<% if(bacsiRs != null) 
			                  			{ bacsiRs.beforeFirst();  %>	
				                  			<% while( bacsiRs.next() ) { %>
				                  				<li><a href="#"> <%= bacsiRs.getString("ten") %></a></li>
				                  			<% } %>
				                  		<% } %>
			                  			
			                  		</ul>
		                  			<div class="panes">
									<% if(bacsiRs != null) 
			                  			{ 
											bacsiRs.beforeFirst();  %>	
											<% while( bacsiRs.next() ) { %>
											<%String bacsiId = bacsiRs.getString("pk_seq"); %>
											<input type="hidden" name='bacsiIds' value='<%= bacsiId %>'  >
											
											<div>				
													<TABLE  width="100%" cellpadding="4" cellspacing="0">
														<tr class="tbheader">	
														<TH width="25%">Mã sản phẩm </TH>
														<TH width="45%">Tên sản phẩm</TH>
														<TH width="30%">Chiết khấu</TH>		
														</tr>
															<%
															int j = 0;
															String lightrow = "tblightrow";
															String darkrow = "tbdarkrow";
															sanpham.beforeFirst();
															while(sanpham.next())
															{
																String spId = sanpham.getString("pk_seq");
																String spMa=  sanpham.getString("ma");
																String spTen=  sanpham.getString("ten");
																
																String ckStr = "";
																if(sanpham_chietkhau!= null && sanpham_chietkhau.containsKey(bacsiId+ "-" +spId))
																	ckStr =  sanpham_chietkhau.get(spId).toString();	
																 if (j % 2 != 0) {%> 
																<TR class= '<%=lightrow%>'> <%
																} else {%> 
																<TR class= "<%=darkrow%>"> <%} %> 
															    
																	<TD class="plainlabel"><%=spMa%>																		
																		<input type="hidden" name='spIds<%=bacsiId%>' value='<%=spId%>'  >
																	</TD>
																	<TD class="plainlabel"><%= spTen%></TD>
																	<TD class="plainlabel"><input type='text' name='chietkhauSp<%=bacsiId%>'  value="<%= ckStr%>" style="text-align: right"/></TD>
															</TR>	
															<%	j++; }%>
							
															<tr class="tbfooter">
																	<td colspan="4">&nbsp;</td>
															</tr>
																</TABLE>
										</div>
										<% } %>
								<%} %>
								
						
								</div>
</td>
</tr>
</TABLE>
</TD>
</TR>
</TABLE>
</TD>
</TR>
</TABLE>
</form>
</BODY>
</HTML>



<% if (sanpham != null) sanpham.close(); 
	bgblcBean.DbClose();
%>
<%}%>