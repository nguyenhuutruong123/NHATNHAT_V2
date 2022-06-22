<%@page import="geso.dms.center.db.sql.dbutils"%>
<%@page import="java.sql.SQLException"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page  import = "java.util.Iterator" %>
<%@ page  import = "java.util.List" %>
<%@ page  import = "java.util.ArrayList" %>
<%@ page  import = "geso.dms.distributor.beans.khachhang.*" %>
<%@ page  import = "java.sql.ResultSet" %>
<%@ page  import = "geso.dms.center.util.*" %>
<%	String userId = (String) session.getAttribute("userId");  
	String userTen = (String) session.getAttribute("userTen");  	
	String sum = (String) session.getAttribute("sum");	
	Utility util = new Utility();
	util.getIdNhapp(userId);	

	
	if (!util.check(userId, userTen, sum)){
		response.sendRedirect(request.getContextPath() + "/redirect.jsp");
	} else { 
		
		IKhachhangList obj = (IKhachhangList)session.getAttribute("obj"); 
		String view = obj.getView();
		String url = util.getChuyenNguUrl("UsingPromotionTTSvl", "",session);
		int[] quyen = new  int[6];
		
		if (view.equals("TT"))
		{
			quyen = util.Getquyen("KhachhangSvl", "&view=" + view, userId);
			url = util.getChuyenNguUrl("KhachhangSvl", "&view=" + view,session);
		}
		else
		{
			quyen = util.Getquyen("KhachhangSvl", "", userId);
			url = util.getChuyenNguUrl("KhachhangSvl", "",session);
		}
		
%>


<% ResultSet khlist = (ResultSet) obj.getKhList(); %>
<% ResultSet hch = (ResultSet)obj.getHangcuahang(); %>
<% ResultSet kbh = (ResultSet)obj.getKenhbanhang();  %>
<% ResultSet vtch = (ResultSet)obj.getVitricuahang();  %>
<% ResultSet lch = (ResultSet)obj.getLoaicuahang(); %>
<% ResultSet diadiemRs = (ResultSet)obj.getDiadiemRs()  ; %>
<% ResultSet tbhRs = (ResultSet)obj.getTbhRs()  ; %>
<% ResultSet ddkdRs = (ResultSet)obj.getDdkdRs()  ; %>
<% ResultSet nppRs = (ResultSet)obj.getNppRs(); %>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"> <% Utility Utilback = new Utility(); %>
<HTML>
<HEAD>
<TITLE><%= getServletContext().getInitParameter("TITLENAME") %></TITLE>
<META http-equiv="Content-Type" content="text/html; charset=utf-8">
<META http-equiv="Content-Style-Type" content="text/css">
<LINK rel="stylesheet" href="../css/main.css" type="text/css">
<LINK rel="stylesheet" href="../css/calendar.css" type="text/css">
	<link type="text/css" rel="stylesheet" href="../css/mybutton.css">
<script type="text/javascript" language="JavaScript" src="../scripts/simplecalendar.js"></script>



	<script type="text/javascript" src="../scripts/Timepicker/jquery.min.js"></script>
  	<script type="text/javascript" src="../scripts/Timepicker/jquery-ui.min.js"></script>
  	<script type="text/javascript" src="../scripts/phanTrang.js"></script>
	<script type="text/javascript" src="../scripts/jquery.min.1.7.js"></script>
	<link href="../css/jquery-ui.css" rel="stylesheet" type="text/css" />
	<script src="../scripts/ui/jquery.ui.core.js" type="text/javascript"></script>
	<script src="../scripts/ui/jquery.ui.widget.js" type="text/javascript"></script>
	<script type="text/javascript" src="../scripts/phanTrang.js"></script>
	<script src="../scripts/ui/jquery.ui.datepicker.js" type="text/javascript"></script>


<link href="../css/select2.css" rel="stylesheet" />
<script src="../scripts/select2.js"></script>
<script type="text/javascript">
	$(document).ready(function() {		
			$(".select2").select2();
        }); 		
		
</script>

<SCRIPT language="javascript" type="text/javascript">
$(document).ready(function() {		
	$( ".days" ).datepicker({			    
			changeMonth: true,
			changeYear: true				
	});            
}); 		


function clearform()
{
	document.khForm.khTen.value = "";
//	document.khForm.hchTen.selectedIndex = 0;
	document.khForm.kbhTen.selectedIndex = 0;
// 	document.khForm.vtchTen.selectedIndex = 0;
// 	document.khForm.lchTen.selectedIndex = 0;
	document.khForm.maFAST.value = "";
	document.khForm.diachi.value = "";
	document.khForm.ddkdId.value = "";
	document.khForm.trangthai.value = "";
	document.khForm.tbhId.value = "";
	document.khForm.tungay.value = "";
	document.khForm.denngay.value = "";
	document.khForm.lchId.selectedIndex = 0;
	document.khForm.nppId.selectedIndex = 0; 
	document.khForm.hopdong.value = "";
// 	document.khForm.nppId.value = "";
	submitform();

}

function submitform()
{
	document.forms['khForm'].action.value= 'search';
	document.forms['khForm'].submit();
}

function newform()
{
	document.forms['khForm'].action.value= 'new';
	document.forms['khForm'].submit();
}


function xuatExcel()
{
	document.forms['khForm'].action.value= 'excel';
	document.forms['khForm'].submit();
	
}


function xuatExcel_HoaDon()
{
	document.forms['khForm'].action.value= 'excel_hoadon';
	document.forms['khForm'].submit();
	
}

function thongbao()
{
	if (document.getElementById("msg").value != '')
		alert(document.getElementById("msg").value);
}
</SCRIPT>

<style>
	.popbox {
    display: none;
    position: absolute;
    z-index: 99999;
    width: 400px;
    padding: 10px;
    background: white;  /* #EEEFEB; */
    color: #000000;
    border: 1px solid #4D4F53;
    margin: 0px;
    -webkit-box-shadow: 0px 0px 5px 0px rgba(164, 164, 164, 1);
    box-shadow: 0px 0px 5px 0px rgba(164, 164, 164, 1);
}
.popbox h2 {
    background-color: #4D4F53;
    color: #E3E5DD;
    font-size: 14px;
    display: block;
    width: 100%;
    margin: -10px 0px 8px -10px;
    padding: 5px 10px;
}
</style>
</HEAD>

<BODY leftmargin="0" bottommargin="0" topmargin="0" rightmargin="0">
<form name="khForm" method="post" action="../../KhachhangSvl">
<input type="hidden" name="userId" value="<%= userId %>" >
<input type="hidden" name="view" value="<%= view %>" >
<% redis.clients.jedis.Jedis jedis =  geso.dms.center.util.Utility.getJedis(); %><% geso.dms.center.util.Csrf csdr=new geso.dms.center.util.Csrf(request,response,true,false,true);%>
 <input type="hidden" name="<%=csdr.get_tokenName() %>" value='<%=csdr.get_tokenValue() %>'>

<%if (!view.equals("TT")){ %>
<input type="hidden" name="nppId" value='<%= obj.getNppId() %>'>
<%} %>
<input type="hidden" name="action" value="1" >
<input type="hidden" name="currentPage" value="<%= obj.getCurrentPage() %>" >

<input type="hidden" name="msg" id="msg" value='<%=  Utility.GLanguage(obj.getMsg(),session,jedis) %>'>
<script type="text/javascript">
	thongbao();
</script>

<TABLE width="100%" border="0" cellspacing="0" cellpadding="0" 	height="100%">
	<TR>
		<TD colspan="4" align='left' valign='top' bgcolor="#FFFFFF">
			<TABLE width="100%" border="0" cellpadding="0" cellspacing="2">
				<TR>
					<TD align="left" class="tbnavigation">
					   <table width="100%" border="0" cellpadding="0" cellspacing="0">
						  	<tr height="22">
							   <TD align="left" colspan="2" class="tbnavigation">&nbsp;<%= url %></TD>
							   <TD colspan="2" align="right" class="tbnavigation"><%=Utility.GLanguage("Chào mừng bạn",session,jedis) %>  <%= userTen %> &nbsp;</TD>
							</tr>
						</table>
					</TD>
		  		</TR>	
			</TABLE>
			<TABLE width="100%" border="0" cellpadding="0" cellspacing="0">
				<TR>
					<TD >
						<FIELDSET><LEGEND class="legendtitle">&nbsp;<%=Utility.GLanguage("Tiêu chí tìm kiếm",session,jedis) %>&nbsp;</LEGEND>
							<TABLE class="tblight" width="100%" cellspacing="0" cellpadding="6">
				
								<TR>
									<TD width="130px" class="plainlabel"> <%=Utility.GLanguage("Mã",session,jedis) %> / <%=Utility.GLanguage("Tên",session,jedis) %> </TD>
								    <TD width="240px" class="plainlabel">
										<INPUT name="khTen" type="text" value="<%= obj.getTen() %>" size="40" onChange = "submitform();">
								  	</TD>
								  	<TD width="120px" class="plainlabel" valign="top" ><%=Utility.GLanguage("Kênh bán hàng",session,jedis) %></TD>
								  	  <TD class="plainlabel" valign="top">
										  	<SELECT name="kbhTen" onChange = "submitform();"  class = "select2" style = "width:200px">
										    	<option value=""> </option>
											    <% try{ while(kbh.next()){ 
										    			if (kbh.getString("kbhId").equals(obj.getKbhId())){ %>
										      				<option value='<%=kbh.getString("kbhId")%>' selected><%=kbh.getString("kbhTen") %></option>
										      			<%}else{ %>
										     				<option value='<%=kbh.getString("kbhId")%>'><%=kbh.getString("kbhTen") %></option>
										     			<%}}}catch(java.sql.SQLException e){} %>
											 </SELECT>
									</TD>
		
								  
								</TR>
								
								<TR>
									<TD class="plainlabel" valign="top" ><%=Utility.GLanguage("Mã",session,jedis) %></TD>
								    <TD class="plainlabel" valign="top" >
									<INPUT name="maFAST" type="text" value="<%= obj.getMaFAST() %>" size="40" onChange = "submitform();">
								  </TD>
											<TD width="120px" class="plainlabel" valign="top" ><%=Utility.GLanguage("Nhân viên bán hàng",session,jedis) %></TD>
											  	  <TD class="plainlabel" valign="top">
													  	<SELECT name="ddkdId" onChange = "submitform();"  class = "select2" style = "width:200px">
													    	<option value=""> </option>
														    <% 
														    if (ddkdRs!=null)
														    {
														    	 try{ while(ddkdRs.next()){ 
														    			if (ddkdRs.getString("ddkdId").equals(obj.getDdkdId()    )){ %>
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
				
								</TR>
								
								
								<TR>
								<TD class="plainlabel" valign="top" ><%=Utility.GLanguage("Địa chỉ",session,jedis) %></TD>
								    <TD class="plainlabel" valign="top" >
										<INPUT name="diachi" type="text" value="<%= obj.getDiachi() %>" size="40" onChange = "submitform();">
								  </TD>
									  <TD width="120px" class="plainlabel" valign="top" ><%=Utility.GLanguage("Tuyến bán hàng",session,jedis) %></TD>
												  	  <TD class="plainlabel" valign="top">
														  	<SELECT name="tbhId" onChange = "submitform();"  class = "select2" style = "width:200px">
														    	<option value=""> </option>
														    	<%if (obj.getTbhId().equals("-1")) {%>
														    		<option value="-1" selected><%=Utility.GLanguage("Khách hàng chưa phân tuyến",session,jedis) %></option>
														    	<%}else {%>
														    		<option value="-1"><%=Utility.GLanguage("Khách hàng chưa phân tuyến",session,jedis) %></option>
															    <%}
															    if (tbhRs!=null)
															    {
																    try{ while(tbhRs.next()){ 
														    			if (tbhRs.getString("tbhId").equals(obj.getTbhId()  )){ %>
														      				<option value='<%=tbhRs.getString("tbhId")%>' selected><%=tbhRs.getString("tbhTen") %></option>
														      			<%}else{ %>
														     				<option value='<%=tbhRs.getString("tbhId")%>'><%=tbhRs.getString("tbhTen") %></option>
														     			<%}}}catch(java.sql.SQLException e){} 
														     			
															    }
			
														     			%>
															 </SELECT>
									 </TD>									 
					
								  </TR>
								  

								  
								  <TR>
								  	 <TD class="plainlabel" ><%=Utility.GLanguage("Từ ngày",session,jedis) %></TD>
								  	 <TD class="plainlabel" >
								  	 	<input type="text" class="days" name="tungay" value="<%= obj.getTungay() %>" maxlength="10" onchange="submitform();" />
								  	 </TD>
								  	 
								  	 <TD class="plainlabel" ><%=Utility.GLanguage("Đến ngày",session,jedis) %></TD>
								  	 <TD class="plainlabel" >
								  	 	<input type="text" class="days" name="denngay" value="<%= obj.getDenngay() %>" maxlength="10" onchange="submitform();" />
								  	 </TD>
								  	 
								  	 
								  </TR>
							
								  <TR>
								 
								  	 <TD class="plainlabel" ><%=Utility.GLanguage("Loại khách hàng",session,jedis) %></TD>
								  	 <TD class="plainlabel" >
								  	 	<SELECT name="lchId" id="lchId" onChange="submitform();"  class = "select2" style = "width:200px">
													<option value=""></option>
													<%  if (lch!=null)
														{
															try
															{
																while(lch.next())
																{ 
											    					if (lch.getString("lchId").equals(obj.getLchId())){ 	
											    			%>
																<option value='<%= lch.getString("lchId") %>' selected><%=lch.getString("lchTen") %></option>
																<%}else{ %>
																<option value='<%= lch.getString("lchId") %>'><%= lch.getString("lchTen") %></option>
																<%}} lch.close(); }catch(java.sql.SQLException e){e.printStackTrace();} 
														}	%>	
												</SELECT>
								  
								  
								  	 </TD>
								  	  <TD width="120px" class="plainlabel" valign="top" ><%=Utility.GLanguage("Trạng thái",session,jedis) %></TD>
									 <TD  class="plainlabel" valign="top" colspan="3">
										 <SELECT name ="trangthai" onChange = "submitform();"  class = "select2" style = "width:200px">
                               
	                                        <% if (obj.getTrangthai().equals("1")){%>
	                                              <option value="1" selected><%=Utility.GLanguage("Hoạt động",session,jedis) %></option>
	                                              <option value="0"><%=Utility.GLanguage("Ngưng hoạt động",session,jedis) %></option>
	                                              <option value=""></option>
	                                              
	                                        <%}else if (obj.getTrangthai().equals("0")) {%>
	                                              <option value="0" selected><%=Utility.GLanguage("Ngưng hoạt động",session,jedis) %></option>
	                                              <option value="1" ><%=Utility.GLanguage("Hoạt động",session,jedis) %></option>
	                                              <option value="" > </option>
	                                              
	                                        <%}else{%>                                                                                        
	                                              <option value="1" ><%=Utility.GLanguage("Hoạt động",session,jedis) %></option>
	                                              <option value="0" ><%=Utility.GLanguage("Ngưng hoạt động",session,jedis) %></option>
	                                              <option value="" selected> </option>
	                                        <%}%>
	                                     </SELECT>
									 </TD>
								  	 	 <TD class="plainlabel" style="display:none" ><%=Utility.GLanguage("Hợp đồng",session,jedis) %></TD>
								  	 	<TD class="plainlabel" style="display:none" >
								  	 		<select name="hopdong" onchange="submitform();"  class = "select2" style = "width:200px">
								  	 		<%if (obj.getHopdong().equals("0")){ %> 
									  	 		<option></option>
									  	 		<option value="0" selected="selected"><%=Utility.GLanguage("Có",session,jedis) %></option>
									  	 		<option value="1"><%=Utility.GLanguage("Không",session,jedis) %></option>
									  	 	<%} else if (obj.getHopdong().equals("1")){ %>
									  	 		<option></option>
									  	 		<option value="0"> <%=Utility.GLanguage("Có",session,jedis) %></option>
									  	 		<option value="1" selected="selected"><%=Utility.GLanguage("Không",session,jedis) %></option>	
									  	 	<% } else{ %>
									  	 		<option></option>
									  	 		<option value="0"><%=Utility.GLanguage("Có",session,jedis) %></option>
									  	 		<option value="1"><%=Utility.GLanguage("Không",session,jedis) %></option>
									  	 	<%} %>
								  	 		</select>
								  	 </TD>
								  </TR>
								  <%if (view.equals("TT")){ %>
								 <TR>
								 	<TD width="120px" class="plainlabel" valign="top" ><%=Utility.GLanguage("Nhà phân phối",session,jedis) %></TD>
											  	  <TD class="plainlabel" valign="top" colspan="3">
													  	<SELECT class = "select2"  name="nppId"  onchange="submitform();" >
													    	<option value=""> </option>
														    <% try{ while(nppRs.next()){ 
													    			if (nppRs.getString("pk_seq").equals(obj.getNppId()    )){ %>
													      				<option value='<%=nppRs.getString("pk_seq")%>' selected>
													      				<%=nppRs.getString("TEN") %></option>
													      			<%}else{ %>
													     				<option value='<%=nppRs.getString("pk_seq")%>'>
													     				<%=nppRs.getString("TEN") %></option>
													     			<%}}}catch(Exception e){ e.printStackTrace(); } %>
														 </SELECT>
									  </TD>
								 </TR>
								<%} %>
								<TR>
								    <TD class="plainlabel" colspan="6">
								    	<a class="button2" href="javascript:clearform()">
										<img style="top: -4px;" src="../images/button.png" alt=""><%=Utility.GLanguage("Nhập lại",session,jedis) %></a>&nbsp;&nbsp;&nbsp;&nbsp;	    
								     	
								     	<a class="button2" href="javascript:xuatExcel()"> <img style="top: -4px;" src="../images/button.png" alt=""><%=Utility.GLanguage("Xuất Excel ",session,jedis) %> </a>
								     
                                     </TD>
								</TR>
							</TABLE>
					  </FIELDSET>
					</TD>	
				</TR>
			</TABLE>
			<TABLE width="100%" border="0" cellspacing="0" cellpadding="2">
				<TR>
					<TD width="100%">
					<FIELDSET>
					<LEGEND class="legendtitle">&nbsp;<%=Utility.GLanguage("Khách hàng",session,jedis) %> &nbsp;&nbsp;&nbsp;
					
					<%if (quyen[Utility.THEM]!=0 && !view.equals("TT") ){ %>
						<a class="button3" href="javascript:newform()">
	    				<img style="top: -4px;" src="../images/New.png" alt=""><%=Utility.GLanguage("Tạo mới",session,jedis) %> </a> 
    				<%} %>                           
					
					<!-- <INPUT name="new" type="button" value="Tao moi" onClick="newform();"> -->
					</LEGEND>
					<TABLE class="" width="100%">
						<TR>
							<TD width="98%">
							<TABLE width="100%" border="0" cellspacing="1" cellpadding="4">
								<TR class="tbheader">
								
									<%if (view.equals("TT")){ %>
										<TH width="20%"><%=Utility.GLanguage("Nhà phân phối",session,jedis) %></TH>
									<%}else{ %>
								
									<!-- <TH width="10%">KH của TTPP/NPP khác</TH> -->
									<%} %>
									<TH width="7%"><%=Utility.GLanguage("Mã",session,jedis) %></TH>
									<TH width="16%"><%=Utility.GLanguage("Tên",session,jedis) %></TH>
									<TH width="7%"><%=Utility.GLanguage("Trạng thái",session,jedis) %></TH>
									<TH width="12%"><%=Utility.GLanguage("Tuyến bán hàng",session,jedis) %></TH>
									<TH width="9%"><%=Utility.GLanguage("Ngày tạo",session,jedis) %></TH>
									<TH width="9%"><%=Utility.GLanguage("Ngày sửa",session,jedis) %></TH>
									<TH width="9%"><%=Utility.GLanguage("Người tạo",session,jedis) %></TH>
									<TH width="9%"><%=Utility.GLanguage("Người sửa",session,jedis) %></TH> 
									<TH width="15%"><%=Utility.GLanguage("Tác vụ",session,jedis) %></TH>
								</TR>
								
						<%  														
	                        int m = 0;
	                        String lightrow = "tblightrow";
	                        String darkrow = "tbdarkrow";
							if (khlist!=null)
							{%>
							<% try{								
                                while (khlist.next()){
                                    String style = "";	
                                	if (khlist.getString("khdaduyet").equals("0") )
                  						style="style=\"color:red\"";
                                	
                                   	if (m % 2 != 0) {%>                     
                                    	<TR class= <%=lightrow%> >
                                    <%} else {%>
                                       	<TR class= <%= darkrow%> >
                                    	<%}%>
											<%-- <TD align="left"><div align="center"><%=khlist.getString("smartid") %></div></TD> --%>     
											
											<%if (view.equals("TT")){ %>
												<TD><div align="left"><%= khlist.getString("nppTen")%></div></TD>  
											<%}%>
											<%-- <%else{ %>
												<TD align="left"><div align="center">
											<%if (khlist.getString("nppChinh").equals("0")){ %>
												<input type="checkbox"  value="0" checked disabled="disabled"> </input>
												<%}else{%>
												<input type="checkbox" value="1"  disabled="disabled"> </input>
												<%} %>
											</div></TD>
											
											<%} %> --%>
											<% String maf = khlist.getString("mafast");   %>
											<TD align="left"><div align="center">
											 	<i <%=style %>>
													<%=  maf %>
												</i>
											</div></TD>
											
											<TD><div align="left"><%= khlist.getString("khTen")%></div></TD>  
											<%-- <TD align="left"><%=khlist.getString("mahd")==null?" ":khlist.getString("mahd")%></TD>
											<TD align="left"><%=khlist.getString("loaiCH")==null?" ":khlist.getString("loaiCH")%></TD> --%>                                                 
											
											<%
										
											if (khlist.getString("trangthai").equals("1")){ %>
												<TD align="center"><%=Utility.GLanguage("Hoạt động",session,jedis) %></TD>
											<%}else{%>
												<TD align="center"><%=Utility.GLanguage("Ngưng hoạt động",session,jedis) %></TD>
											<%}%>
											
											
											<%-- <TD align="left"><%=khlist.getString("diachi")==null?" ":khlist.getString("diachi")%></TD> --%>
											<TD align="left">
												
												<div id="<%= "pop"+m %>" class="popbox">
												    <h4><%= khlist.getString("mafast")%> - <%= khlist.getString("khTen")%></h4>
												    <%=khlist.getString("tbhTen")==null?" ":khlist.getString("tbhTen")%>
												</div>
												<a href="#" class="popper makeshort" data-popbox="<%= "pop"+m %>"><%=khlist.getString("tbhTen")==null?" ":khlist.getString("tbhTen")%></a>
												<%-- <%=khlist.getString("tbhTen")==null?" ":khlist.getString("tbhTen")%> --%>
											</TD>
											<TD align="center"><%=khlist.getString("ngaytao")%></TD>
											<TD align="center"><%=khlist.getString("ngaysua")%></TD>
											<TD align="center"><%=khlist.getString("nguoitao")%></TD> 
											<TD align="center"><%=khlist.getString("nguoisua")%></TD> 
											<TD align="center">
											<TABLE border = 0 cellpadding="0" cellspacing="0">
												<TR>
												<TD>
												<%if (quyen[Utility.SUA]!=0 && (khlist.getString("nppChinh").equals("1"))) { 
													//System.out.println("KhachhangUpdateSvl?userId="+userId+"&update="+khlist.getString("khId")+"&view="+view);
												%>												
													<A href = "../../RouterSvl?task=<%= Utility.dongMa(getServletContext().getInitParameter("RedirectNoScript") +"KhachhangUpdateSvl?userId="+userId+"&update="+khlist.getString("khId")+"&view="+view)%>"><img src="../images/Edit20.png" alt="Cap nhat" width="20" height="20" longdesc="Cap nhat" border = 0></A>
												<%}else if (quyen[Utility.XEM]!=0){ %>
													<A href = "../../RouterSvl?task=<%= Utility.dongMa(getServletContext().getInitParameter("RedirectNoScript") +"KhachhangUpdateSvl?userId="+userId+"&display="+khlist.getString("khId")+"&view="+view)%>"><img src="../images/Display20.png" alt="Cap nhat" width="20" height="20" longdesc="Cap nhat" border = 0></A>
												<%} %>
												</TD>
												<TD>
												 <% if (quyen[Utility.XEM]!=0){ %>
												<A href = "../../RouterSvl?task=<%= Utility.dongMa(getServletContext().getInitParameter("RedirectNoScript") +"KhachhangUpdateSvl?userId="+userId+"&display="+khlist.getString("khId")+"&view="+view)%>"><img src="../images/Display20.png" alt="Cap nhat" width="20" height="20" longdesc="Cap nhat" border = 0></A>
												<%} %>
												</TD>
												<TD>
												<%if ((khlist.getString("nppChinh").equals("1")) && quyen[Utility.XOA] != 0){ %>
													<A href = "../../RouterSvl?task=<%= Utility.dongMa(getServletContext().getInitParameter("RedirectNoScript") +"KhachhangSvl?userId="+userId+"&delete="+khlist.getString("khId")+"&view="+view) %>"><img src="../images/Delete20.png" alt="Xoa" width="20" height="20" longdesc="Xoa" border=0 
														onclick="if (!confirm('Bạn có muốn Xoá khách hàng này?')) return false;"></A>
												<%} %>	
												</TD>
												
												<TD>
												<%if ( 	 khlist.getString("khdaduyet").equals("0")  &&  quyen[Utility.CHOT]!=0){ %>
													<A href = "../../RouterSvl?task=<%= Utility.dongMa(getServletContext().getInitParameter("RedirectNoScript") +"KhachhangSvl?userId="+userId+"&duyet="+khlist.getString("khId")+"&view="+view)%>">
														<img src="../images/Chot.png" alt="Duyệt" width="20" height="20" longdesc="Duyệt" border=0 
															onclick = "if (!confirm('Bạn có muốn Duyệt khách hàng này?')) return false;"></A>
												<%} %>	
												</TD>
												
												<%-- <TD>
												<%if ( 	view.equals("TT")&& khlist.getString("khId").equals(khlist.getString("mafast"))&& khlist.getString("DongBo").equals("0") && khlist.getString("khdaduyet").equals("0")  &&  quyen[Utility.CHOT]!=0){ %>
													<A href = "../../KhachhangSvl?userId=<%=userId%>&dongbo=<%=khlist.getString("khId") %>"><img src="../images/copy20.png" alt="Đồng bộ" width="20" height="20" longdesc="Đồng bộ" border=0 onclick="if (!confirm('Ban Muon Đồng bộ Khach Hang Nay?')) return false;"></A>
												<%} %>	
												</TD> --%>
												
												</TR></TABLE>
											</TD>
										</TR>
								<%m++; }}catch(java.sql.SQLException e){e.printStackTrace();}
							}%>
								
										 <tr class="tbfooter" > 
											 <TD align="center" valign="middle" colspan="13" class="tbfooter">
											 <% obj.setNextSplittings(); %>
												 <script type="text/javascript" src="../scripts/phanTrang.js"></script>
												<input type="hidden" name="crrApprSplitting" value="<%= obj.getCrrApprSplitting() %>" >
												<input type="hidden" name="nxtApprSplitting" value="<%= obj.getNxtApprSplitting() %>" >

											 	<%if (obj.getNxtApprSplitting() >1) {%>
													<img alt="Trang Dau" src="../images/first.gif" style="cursor: pointer;" onclick="View(document.forms[0].name, 1, 'view')"> &nbsp;
												<%}else {%>
													<img alt="Trang Dau" src="../images/first.gif" > &nbsp;
													<%} %>
												<% if (obj.getNxtApprSplitting() > 1){ %>
													<img alt="Trang Truoc" src="../images/prev.gif" style="cursor: pointer;" onclick="View(document.forms[0].name, eval(document.forms[0].nxtApprSplitting.value) -1, 'view')"> &nbsp;
												<%}else{ %>
													<img alt="Trang Truoc" src="../images/prev_d.gif" > &nbsp;
												<%} %>
												
												<%
													int[] listPage = obj.getNextSplittings();
													for(int i = 0; i < listPage.length; i++){
												%>
												
												<% 
												
												if (listPage[i] == obj.getNxtApprSplitting()){ %>
												
													<a  style="color:white;"><%= listPage[i] %>/ <%=obj.getTheLastSplitting() %></a>
												<%}else{ %>
													<a href="javascript:View(document.forms[0].name, <%= listPage[i] %>, 'view')"><%= listPage[i] %></a>
												<%} %>
													<input type="hidden" name="list" value="<%= listPage[i] %>" />  &nbsp;
												<%} %>
												
												<% if (obj.getNxtApprSplitting() < obj.getTheLastSplitting()){ %>
													&nbsp; <img alt="Trang Tiep" src="../images/next.gif" style="cursor: pointer;" onclick="View(document.forms[0].name, eval(document.forms[0].nxtApprSplitting.value) +1, 'view')"> &nbsp;
												<%}else{ %>
													&nbsp; <img alt="Trang Tiep" src="../images/next_d.gif" > &nbsp;
												<%} %>
												<%if (obj.getNxtApprSplitting() == obj.getTheLastSplitting()) {%>
											   		<img alt="Trang Cuoi" src="../images/last.gif" > &nbsp;
										   		<%}else{ %>
										   			<img alt="Trang Cuoi" src="../images/last.gif" style="cursor: pointer;" onclick="View(document.forms[0].name, -1, 'view')"> &nbsp;
										   		<%} %>
											</TD>
											</tr>
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
	<script>
	
	function textAbstract(el, maxlength = 20, delimiter = " ") {
		  let txt = $(el).text();
		  if (el == null) {
		    return "";
		  }
		  if (txt.length <= maxlength) {
		    return txt;
		  }
		  let t = txt.substring(0, maxlength);
		  let re = /\s+\S*$/;
		  let m = re.exec(t);
		  t = t.substring(0, m.index);
		  return t + "...";
		}

		var maxlengthwanted = 23;

		$('.makeshort').each(function(index, element) {
		  $(element).text(textAbstract(element, maxlengthwanted, " "));
		});
	
	var moveLeft = 0;
	var moveDown = 0;
	$('a.popper').hover(function (e) {

	    var target = '#' + ($(this).attr('data-popbox'));
	    $(target).show();
	    moveLeft = $(this).outerWidth();
	    moveDown = ($(target).outerHeight() / 2);
	}, function () {
	    var target = '#' + ($(this).attr('data-popbox'));
	    if (!($("a.popper").hasClass("show"))) {
	        $(target).hide();
	    }
	});

	$('a.popper').mousemove(function (e) {
	    var target = '#' + ($(this).attr('data-popbox'));

	    leftD = e.pageX + parseInt(moveLeft);
	    maxRight = leftD + $(target).outerWidth();
	    windowLeft = $(window).width() - 40;
	    windowRight = 0;
	    maxLeft = e.pageX - (parseInt(moveLeft) + $(target).outerWidth() + 20);

	    if (maxRight > windowLeft && maxLeft > windowRight) {
	        leftD = maxLeft;
	    }

	    topD = e.pageY - parseInt(moveDown);
	    maxBottom = parseInt(e.pageY + parseInt(moveDown) + 20);
	    windowBottom = parseInt(parseInt($(document).scrollTop()) + parseInt($(window).height()));
	    maxTop = topD;
	    windowTop = parseInt($(document).scrollTop());
	    if (maxBottom > windowBottom) {
	        topD = windowBottom - $(target).outerHeight() - 20;
	    } else if (maxTop < windowTop) {
	        topD = windowTop + 20;
	    }

	    $(target).css('top', topD).css('left', leftD);
	});
	/* $('a.popper').click(function (e) {
	    var target = '#' + ($(this).attr('data-popbox'));
	    if (!($(this).hasClass("show"))) {
	        $(target).show();
	    }
	    $(this).toggleClass("show");
	}); */
	</script>
</HTML>
<% 	

	try{
		
		if (hch != null)
			hch.close();
		if (kbh != null)
			kbh.close();
		if (lch != null)
			lch.close();
		if (vtch!=null){
			vtch.close();
		}
		if (obj != null){
			obj.DBclose();
			obj = null;
		}		
		
		session.setAttribute("obj",null);
	
	}
	catch (SQLException e) {}
%>
<%}%>