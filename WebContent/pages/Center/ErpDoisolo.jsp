<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page  import = "java.util.Iterator" %>
<%@ page  import = "java.util.List" %>
<%@ page  import = "java.util.ArrayList" %>
<%@ page  import = "geso.dms.center.beans.doisolo.*" %>
<%@ page  import = "java.sql.ResultSet" %>
<%@ page  import = "geso.dms.center.util.*" %>

<%	String userId = (String) session.getAttribute("userId");  
	String userTen = (String) session.getAttribute("userTen");  	
	String sum = (String) session.getAttribute("sum");
	Utility util = (Utility) session.getAttribute("util");
	if(!util.check(userId, userTen, sum)){
		response.sendRedirect(request.getContextPath() + "/redirect.jsp");
	}else{ 
		int[] quyen = new  int[6];
		quyen = util.Getquyen("ErpDoisoloSvl","",userId);

		
		String url="";
		url = util.getUrl("ErpDoisoloSvl","");
		
	%>


<% IErpDoisoloList obj = (IErpDoisoloList)session.getAttribute("obj"); %>
<% ResultSet ckRs = (ResultSet)obj.getRsChuyenKho(); %>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"> <% Utility Utilback = new Utility(); %>
<HTML>
<HEAD>
<TITLE><%= getServletContext().getInitParameter("TITLENAME") %></TITLE>
<META http-equiv="Content-Type" content="text/html; charset=utf-8">
<META http-equiv="Content-Style-Type" content="text/css">
<LINK rel="stylesheet" href="../css/main.css" type="text/css">
<LINK rel="stylesheet" type="text/css" href="../css/style.css" />
<link type="text/css" rel="stylesheet" href="../css/mybutton.css">
<LINK rel="stylesheet" type="text/css" media="print" href="../css/impression.css">

<script type="text/javascript" src="../scripts/phanTrang.js"></script>

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

<SCRIPT language="javascript" type="text/javascript">
	function confirmLogout()
	{
	   if(confirm("Ban co muon dang xuat?"))
	   {
			top.location.href = "home.jsp";
	   }
	   return;
	 }
	function searchphieu()
	{   
		document.forms["ckForm"].action.value="search";
	    document.forms["ckForm"].submit();
	}
	function clearform()
	{
		 document.forms["ckForm"].tungay.value = "";
		 document.forms["ckForm"].denngay.value = "";
		 document.forms["ckForm"].trangthai.value="";
		 /* document.forms["ckForm"].nvbhTen.selectedIndex = -1;
		 document.forms["ckForm"].trangthai.selectedIndex = -1;  */
		 document.forms["ckForm"].action.value = "clear";
		 document.forms["ckForm"].submit();
	}
	function newform(){
		
		 document.forms["ckForm"].action.value = "Tao moi";
		 document.forms["ckForm"].submit();
	}
	function thongbao()
	{
		if(document.getElementById("msg").value != '')
			alert(document.getElementById("msg").value);
	}
</SCRIPT>

</HEAD>
<BODY leftmargin="0" bottommargin="0" topmargin="0" rightmargin="0">
<form name="ckForm" method="post" action="../../ErpDoisoloSvl">
<input type="hidden" name="userId" value="<%= userId %>" >
<input type="hidden" name="nppId" value="<%= obj.getNppId() %>" >
<input type="hidden" name="action" value='1'>

<input type="hidden" name="msg" id="msg" value='<%= obj.getMsg() %>'>
<script type="text/javascript">
	thongbao();
</script>
<TABLE width="100%" border="0" cellspacing="0" cellpadding="0" height="100%">
	<TR>
		<TD colspan="4" align='left' valign='top' bgcolor="#FFFFFF">
		<TABLE width="100%" border="0" cellpadding="0" cellspacing="2">
			<TR>
				<TD align="left" class="tbnavigation">
					<table width="100%" border="0" cellpadding="0" cellspacing="0">
						<tr height="22">
   							<TD align="left" width="50%" class="tbnavigation">&nbsp;<%=url %>  </TD>
   							
   							<TD align="right" width="50%" class="tbnavigation">Chào mừng  <%= userTen %></TD>
						</tr>
					</table>
				</TD>
		  	</TR>
		</TABLE>
		<TABLE width="100%" border="0" cellpadding="0" cellspacing="2">
			<TR>
				<TD >
					<FIELDSET><LEGEND class="legendtitle">&nbsp;<%=Utility.GLanguage("Tiêu chí tìm kiếm",session,jedis) %>&nbsp;</LEGEND>
					
					<TABLE  width="100%" cellspacing="0" cellpadding="3">
						<TR>
							<TD class="plainlabel" ><%=Utility.GLanguage("Từ ngày",session,jedis) %></TD>
							<TD class="plainlabel">
								<input class="days" type="text" name="tungay" value="<%= obj.getTungay() %>" size="11">
							</TD>		
						</TR>
						<TR>
							<TD class="plainlabel" ><%=Utility.GLanguage("Đến ngày",session,jedis) %></TD>
							<TD class="plainlabel">
								<input class="days" type="text" name="denngay" value="<%= obj.getDenngay() %>" size="11">
							</TD>		
						</TR>
						<TR>
							<TD width="19%" class="plainlabel"><%=Utility.GLanguage("Trạng thái",session,jedis) %> </TD>
							<TD width="81%" colspan="3" class="plainlabel">
								<SELECT name ="trangthai" onChange = "searchphieu();">                                  
                                   <% if (obj.getTrangthai().equals("1")){%>
                                         <option value="1" selected>Đã chốt</option>
                                         <option value="0">Chưa chốt</option>
                                         <option value="2" >Đã hủy</option>
                                         <option value=""> </option>                                        
                                   <%}else if(obj.getTrangthai().equals("0")) {%>
                                         <option value="0" selected>Chưa chốt</option>
                                         <option value="1" >Đã chốt</option>
                                         <option value="2" >Đã hủy</option>
                                         <option value="" > </option>                                         
                                   <%}else if(obj.getTrangthai().equals("2")) {%>
                                   <option value="0" >Chưa chốt</option>
                                   <option value="1" >Đã chốt</option>
                                   <option value="2" selected >Đã hủy</option>
                                   <option value="" > </option>                                         
                             		<%}
                                   else{%>                                          
                                         <option value="" selected> </option>
                                         <option value="1" >Đã chốt</option>
                                         <option value="0" >Chưa chốt</option>
                                         <option value="2" >Đã hủy</option>
                                   <%}%>
                                </SELECT>
							</TD>
						</TR>
						
						<TR>
							<TD class="plainlabel" colspan="3">
							<a class="button2" href="javascript:searchphieu()">
								<img style="top: -4px;" src="../images/Search30.png" alt=""><%=Utility.GLanguage("Tìm kiếm",session,jedis) %> </a>&nbsp;&nbsp;&nbsp;&nbsp;
	
							<a class="button2" href="javascript:clearform()">
								<img style="top: -4px;" src="../images/button.png" alt=""><%=Utility.GLanguage("Nhập lại",session,jedis) %></a>&nbsp;&nbsp;&nbsp;&nbsp;	
	
						</TR>
					</TABLE>

				  </FIELDSET>
				</TD>	
			</TR>
		</TABLE>
		<TABLE width="100%" border="0" cellpadding="0" cellspacing="0">
			<TR>
					<TD width="100%"> 
					<FIELDSET>
					<LEGEND class="legendtitle">Đổi số lô &nbsp;&nbsp;&nbsp;&nbsp;
					
					<%if(quyen[Utility.THEM]!=0){ %>
						<a class="button3" href="javascript:newform()">
    						<img style="top: -4px;" src="../images/New.png" alt=""><%=Utility.GLanguage("Tạo mới",session,jedis) %> </a> 
    				<%} %>		                           
    				
					</LEGEND>
					<TABLE class="" width="100%" cellpadding="0" cellspacing="0">
						<TR>
							<TD>
								<TABLE width="100%" border="0" cellspacing="1" cellpadding="2">
									<TR class="tbheader">
											<TH align="center" width="8%">Mã phiếu</TH>
											<TH align="center" width="8%">Ngày đổi</TH>
											<TH align="center" width="10%">Kho đổi</TH>
											<TH align="center" width="8%"><%=Utility.GLanguage("Trạng thái",session,jedis) %></TH>
											<TH align="center" width="8%"><%=Utility.GLanguage("Ngày tạo",session,jedis) %></TH>
											<TH align="center" width="15%"><%=Utility.GLanguage("Người tạo",session,jedis) %> </TH>
											<TH align="center" width="10%"><%=Utility.GLanguage("Ngày sửa",session,jedis) %></TH>
											<TH align="center" width="15%"><%=Utility.GLanguage("Người sửa",session,jedis) %> </TH>
											<TH align="center" width="10%"><%=Utility.GLanguage("Tác vụ",session,jedis) %></TH>
									</TR>
									
									<% if(ckRs != null)
									{ 
										int m = 0;
										while(ckRs.next()) 
										{ %>
											
										<% if (m % 2 != 0) {%>						
											<TR class= "tblightrow">
										<%} else {%>
											<TR class= "tbdarkrow">
										<%}%>
										
										<TD align="center"><%= ckRs.getString("pk_seq") %></TD>
										<TD align="center"><%= ckRs.getString("ngaydoi") %></TD>
										<TD align="left"><%= ckRs.getString("khoTen") %></TD>
										<% if (ckRs.getString("trangthai").equals("1")){ %>
                                            <TD align="center"><b>Đã chốt</b></TD>
                                        <%}else{ if(ckRs.getString("trangthai").equals("0")){ %>
                                            <TD align="center">Chưa chốt</TD>
                                        <%}else{ %>
                                        	<TD align="center"><i>Đã hủy</i></TD>
                                        <% }}%>  
										<TD align="center"><%= ckRs.getString("ngaytao") %></TD>
										<TD align="left"><%= ckRs.getString("nguoitao") %></TD>
										<TD align="center"><%= ckRs.getString("ngaysua") %></TD>
										<TD align="left"><%= ckRs.getString("nguoisua") %></TD>
										
										<TD align="center" valign="middle">
											<% if(ckRs.getString("trangthai").equals("0")) {%>
												<%if(quyen[Utility.SUA]!=0){ %>
													<A href = "../../ErpDoiSoLoUpdateSvl?userId=<%=userId%>&update=<%= ckRs.getString("pk_seq") %>"><img src="../images/Edit20.png" alt="Cap nhat" width="20" height="20" longdesc="Cap nhat" border = 0></A>&nbsp;
												<%} %>	
												
												<%if(quyen[Utility.XOA]!=0){ %>
													<A href = "../../ErpDoisoloSvl?userId=<%=userId%>&delete=<%= ckRs.getString("pk_seq") %>"><img src="../images/Delete20.png" alt="Xoa" width="20" height="20" longdesc="Xoa" border=0 onclick="if(!confirm('Bạn có muốn xóa đổi số lô này?')) return false;"></A>&nbsp;
												<%} %>
												
												<%if(quyen[Utility.CHOT]!=0){ %>
													<A href = "../../ErpDoisoloSvl?userId=<%=userId%>&chotphieu=<%= ckRs.getString("pk_seq") %>" > <img src="../images/Chot.png" alt="Chot phieu chuyen" width="20" height="20" longdesc="Chot phieu chuyen" border = 0 onclick="if(!confirm('Bạn có muốn chốt đổi số lô này?')) return false; "></A>
												<%} %>
												
												
											<%}else{ %>
											
												<%if(quyen[Utility.XEM]!=0){ %>
													<A href = "../../ErpDoiSoLoUpdateSvl?userId=<%=userId%>&display=<%= ckRs.getString("pk_seq") %>"><img src="../images/Display20.png" alt="Hien thi" width="20" height="20" longdesc="Hien thi" border = 0></A>
												<%} %>
												
											<% } %>
												
										</TD>
										
										</TR>
									<%  m++; } } %>
								
									<tr class="tbfooter" > 
									 	<TD align="center" valign="middle" colspan="13" class="tbfooter">
									 		&nbsp;
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
		<!--end body Dossier--></TD>
</TR>
</TABLE>
</form>
</BODY>
</HTML>
<%
	try
	{
		if(obj != null){
		obj.DBclose(); obj = null
		;}
	}catch(Exception e){}
	}
%>