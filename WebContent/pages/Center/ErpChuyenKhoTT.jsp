<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page  import = "java.util.Iterator" %>
<%@ page  import = "java.util.List" %>
<%@ page  import = "java.util.ArrayList" %>
<%@ page  import = "geso.dms.center.beans.dieuchinhtonkho.*" %>
<%@ page  import = "java.sql.ResultSet" %>
<%@ page  import = "geso.dms.center.util.*" %>
<%@page import="java.sql.SQLException"%>
<%@ page  import = "java.text.DecimalFormat" %>
<%@ page  import = "java.text.NumberFormat" %>
<% IErpChuyenkhoTTList obj = (IErpChuyenkhoTTList)session.getAttribute("obj"); %>
<% ResultSet nhapkhoRs =  obj.getNhapkhoRs(); %>
<% ResultSet chungtuRs =  obj.getChungtuRs(); %>
<% ResultSet nppRs =  obj.getNppRs(); %>
<% String userId = (String) session.getAttribute("userId");  %>
<% String userTen = (String) session.getAttribute("userTen");  %>
<% Utility util = new Utility(); %>
<% obj.setNextSplittings();
    
	String sum = (String) session.getAttribute("sum");
	NumberFormat formatter = new DecimalFormat("#,###,###");

	
	if(!util.check(userId, userTen, sum)){
		response.sendRedirect(request.getContextPath() + "/redirect.jsp");
	}else{
		int[] quyen = new  int[6];
		quyen = util.Getquyen("ErpChuyenkhoTTSvl","&loaidonhang="+obj.getLoaidonhang()+"",userId);
%>
<%String url = util.getChuyenNguUrl("ErpChuyenkhoTTSvl", "&loaidonhang="+obj.getLoaidonhang()+"",session); %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd"> <% Utility Utilback = new Utility(); %>
<html>
<head>
	<title><%= getServletContext().getInitParameter("TITLENAME") %></title>
	<META http-equiv="Content-Type" content="text/html; charset=utf-8">
	<META http-equiv="Content-Style-Type" content="text/css">
    <LINK rel="stylesheet" type="text/css" media="print" href="../css/impression.css">
    <LINK rel="stylesheet" href="../css/main.css" type="text/css">
    <LINK rel="stylesheet" href="../css/datepicker.css" type="text/css">
    <script language="javascript" src="../scripts/datepicker.js"></script>
   	<link href="../css/jquery-ui.css" rel="stylesheet" type="text/css"/>
  	<script type="text/javascript" src="../scripts/Timepicker/jquery.min.js"></script>
  	<script type="text/javascript" src="../scripts/Timepicker/jquery-ui.min.js"></script>
  	<script type="text/javascript" src="../scripts/phanTrang.js"></script>
	<script type="text/javascript" src="../scripts/jquery.min.1.7.js"></script>
	<link href="../css/jquery-ui.css" rel="stylesheet" type="text/css" />
	<script src="../scripts/ui/jquery.ui.core.js" type="text/javascript"></script>
	<script src="../scripts/ui/jquery.ui.widget.js" type="text/javascript"></script>
	<script type="text/javascript" src="../scripts/phanTrang.js"></script>
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
   
  	<script type="text/javascript" src="..scripts/jquery-1.js"></script>
	<link type="text/css" rel="stylesheet" href="../css/mybutton.css">
    <script type="text/javascript">
        $(document).ready(function(){
            $(".button").hover(function(){
                $(".button img")
                .animate({top:"-10px"}, 200).animate({top:"-4px"}, 200) // first jump
                .animate({top:"-7px"}, 100).animate({top:"-4px"}, 100) // second jump
                .animate({top:"-6px"}, 100).animate({top:"-4px"}, 100); // the last jump
            });
        }); 
		$(document).ready(function(){
            $(".button2").hover(function(){
                $(".button2 img")
                .animate({top:"-10px"}, 200).animate({top:"-4px"}, 200) // first jump
                .animate({top:"-7px"}, 100).animate({top:"-4px"}, 100) // second jump
                .animate({top:"-6px"}, 100).animate({top:"-4px"}, 100); // the last jump
            });
        }); 
		$(document).ready(function(){
            $(".button3").hover(function(){
                $(".button3 img")
                .animate({top:"-10px"}, 200).animate({top:"-4px"}, 200) // first jump
                .animate({top:"-7px"}, 100).animate({top:"-4px"}, 100) // second jump
                .animate({top:"-6px"}, 100).animate({top:"-4px"}, 100); // the last jump
            });
        }); 
    </script>
    <SCRIPT language="javascript" type="text/javascript">
	 function submitform()
	 {   
	    document.forms["ckParkForm"].submit();
	 }
	 function newform()
	 {   
		document.forms["ckParkForm"].action.value = "Tao moi";
	    document.forms["ckParkForm"].submit();
	 }
	 function clearform()
	 {   
	    document.forms["ckParkForm"].tungay.value = "";
	    document.forms["ckParkForm"].denngay.value = "";
	    document.forms["ckParkForm"].trangthai.value = "";
	    document.forms["ckParkForm"].chungtu.value = "";
	    document.forms["ckParkForm"].nppId.value="";
	    document.forms["ckParkForm"].submit();
	 }
	 function thongbao()
	 {
		 var tt = document.forms["ckParkForm"].msg.value;
	 	 if(tt.length>0)
	     	alert(document.forms["ckParkForm"].msg.value);
	 }
	</SCRIPT>
</head>
<body>
<form name="ckParkForm" method="post" action="../../ErpChuyenkhoTTSvl">
<% redis.clients.jedis.Jedis jedis =  geso.dms.center.util.Utility.getJedis(); %><% geso.dms.center.util.Csrf csdr=new geso.dms.center.util.Csrf(request,response,true,false,true);%>
<input type="hidden" name="userId" value="<%= userId %>" >
<input type="hidden" name="action" value="1" >
<input type="hidden" name="msg" value='<%=Utility.GLanguage(obj.getMsg(),session,jedis) %>'>
<input type="hidden" name="loaidonhang" value='<%= obj.getLoaidonhang() %>'>
<input type="hidden" name="currentPage" value="<%= obj.getCurrentPage() %>" >

 <input type="hidden" name="<%=csdr.get_tokenName() %>" value='<%=csdr.get_tokenValue() %>'>
<script language="javascript" type="text/javascript">
    thongbao();
</script> 

<div id="main" style="width:100%; padding-left:2px">
	<div align="left" id="header" style="width:100%; float:none">
    	<div style="width:55%; padding:5px; float:left" class="tbnavigation"><%=url %></div>
        <div align="right" style="padding:5px" class="tbnavigation">
        	<%=Utility.GLanguage("Chào mừng bạn",session,jedis) %>  <%= userTen %> &nbsp;
        </div>
    </div>
  	<div id="cotent" style="width:100%; float:none">
    	<div align="left" style="width:100%; float:none; clear:left; font-size:0.7em">
        <fieldset style="margin-top:5px" >
            <legend class="legendtitle"> <%=Utility.GLanguage("Tiêu chí tìm kiếm",session,jedis) %></legend>
                <TABLE width="100%" cellpadding="6px" cellspacing="0px" style="margin-top: 5px " >
                	 <TR>
                        <TD class="plainlabel"><%=Utility.GLanguage("Từ ngày",session,jedis) %></TD>
                        <TD class="plainlabel"><input type="text" name="tungay" value="<%= obj.getTungayTao() %>" class="days" maxlength="10" onchange="submitform();" /></TD>
                   		<TD class="plainlabel"><%=Utility.GLanguage("Đến ngày",session,jedis) %></TD>
                        <TD class="plainlabel"><input type="text" class="days"  name="denngay" value="<%= obj.getDenngayTao() %>" class="days" maxlength="10" onchange="submitform();"  /></TD>
                    </TR>								                          
   					<TR>
	   					<TD class="plainlabel" width="150px"><%=Utility.GLanguage("Nhà phân phối",session,jedis) %></TD>
	                        <TD class="plainlabel" colspan="3">
	                            <select name = "nppId" class="select2" style="width: 200px" onchange="submitform();" >
		                    		<option value=""> </option>
		                        	<%
		                        		if(nppRs != null)
		                        		{
		                        			try
		                        			{
		                        			while(nppRs.next())
		                        			{  
		                        			if( nppRs.getString("pk_seq").equals(obj.getNppId())){ %>
		                        				<option value="<%= nppRs.getString("pk_seq") %>" selected="selected" ><%= nppRs.getString("ten") %></option>
		                        			<%}else { %>
		                        				<option value="<%= nppRs.getString("pk_seq") %>" ><%= nppRs.getString("ten") %></option>
		                        		 <% } } nppRs.close();} catch(Exception ex){}
		                        		}
		                        	%>
		                    	</select>
	                        </TD>
   					</TR>
                    
                    
                     <TR>
                        <TD class="plainlabel" valign="middle"><%=Utility.GLanguage("Trạng thái",session,jedis) %> </TD>
                        <TD class="plainlabel" valign="middle">
                           <select name="trangthai" onchange="submitform();" >
                           		<option value=""> </option>
								<% if (obj.getTrangthai().equals("1")){%>
								  	<option value="1" selected><%=Utility.GLanguage("Đã chốt",session,jedis) %></option>
								  	<option value="0"><%=Utility.GLanguage("Chưa chốt",session,jedis) %></option>
								  	<option value="2" ><%=Utility.GLanguage("Đã hủy",session,jedis) %></option>
								  
								<%}else if(obj.getTrangthai().equals("0")) {%>
								 	<option value="0" selected><%=Utility.GLanguage("Chưa chốt",session,jedis) %></option>
								  	<option value="1" ><%=Utility.GLanguage("Đã chốt",session,jedis) %></option>
								  	<option value="2" ><%=Utility.GLanguage("Đã hủy",session,jedis) %></option>
								<%}else if(obj.getTrangthai().equals("2")) {%>
							 	<option value="2" selected><%=Utility.GLanguage("Đã hủy",session,jedis) %></option>
							  	<option value="0" ><%=Utility.GLanguage("Chưa chốt",session,jedis) %></option>
							  	<option value="1" ><%=Utility.GLanguage("Đã chốt",session,jedis) %></option>
								<%} else  {%>
							 	<option value="0"><%=Utility.GLanguage("Chưa chốt",session,jedis) %></option>
							  	<option value="1" ><%=Utility.GLanguage("Đã chốt",session,jedis) %></option>
							  	<option value="2" ><%=Utility.GLanguage("Đã hủy",session,jedis) %></option>
							<%} %>
                           </select>
                        </TD> 
                        <TD class="plainlabel"><%=Utility.GLanguage("Mã chứng từ",session,jedis) %></TD>
                        <td class="plainlabel">
                        	<input name="chungtu" type="text" value="<%= obj.getctId()%>" size="40" onchange="submitform();" />
                        <%-- <select name="chungtu" onchange="submitform();" >
                        	<option value=""></option>
                        	<%
                        	if(chungtuRs != null)
                    		{
                    			try
                    			{
                    			while(chungtuRs.next())
                    			{  
                    			if(chungtuRs.getString("PK_SEQ").equals(obj.getctId())){ %>
                    				<option value="<%= chungtuRs.getString("PK_SEQ") %>" selected="selected" ><%= chungtuRs.getString("PK_SEQ") %></option>
                    			<%}else { %>
                    				<option value="<%= chungtuRs.getString("PK_SEQ") %>" ><%= chungtuRs.getString("PK_SEQ") %></option>
                    		 <% } } chungtuRs.close();} catch(SQLException ex){}
                    		}                        	%>
                        </select> --%>
                        </td> 
                                              
                    </TR>    
                    <tr>
                        <td colspan="4" class="plainlabel">
                            <a class="button" href="javascript:submitform()">
                                <img style="top: -4px;" src="../images/Search30.png" alt=""><%=Utility.GLanguage("Tìm kiếm",session,jedis) %> </a>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                            <a class="button2" href="javascript:clearform()">
                                <img style="top: -4px;" src="../images/button.png" alt=""><%=Utility.GLanguage("Nhập lại",session,jedis) %></a>&nbsp;&nbsp;&nbsp;&nbsp;
                        </td>
                    </tr>        					
                </TABLE>                      
        </fieldset>                      
    	</div>
        <div align="left" style="width:100%; float:none; clear:left; font-size:0.7em">
        	<fieldset>
            	<legend><span class="legendtitle"> <%=Utility.GLanguage("Xuất chuyển nội bộ",session,jedis) %> </span>&nbsp;&nbsp;
            	<%if(quyen[Utility.THEM]!=0){ %>
                	<a class="button3" href="javascript:newform()">
                           <img style="top: -4px;" src="../images/New.png" alt=""><%=Utility.GLanguage("Tạo mới",session,jedis) %> </a><%} %>
                </legend>
            	<TABLE width="100%" border="0" cellspacing="1" cellpadding="4">
					<TR class="tbheader">
	                    <TH align="center"><%=Utility.GLanguage("Mã số",session,jedis) %></TH>
	                    <TH align="center"><%=Utility.GLanguage("Ngày xuất",session,jedis) %></TH>
	                    <TH align="center"><%=Utility.GLanguage("Kho xuất",session,jedis) %></TH>
	                    <TH align="center"><%=Utility.GLanguage("Địa bàn",session,jedis) %></TH>
	                    <TH align="center"><%=Utility.GLanguage("Đối tượng",session,jedis) %></TH>
	                    <TH align="center"><%=Utility.GLanguage("Trạng thái",session,jedis) %></TH>
	                    <TH align="center"><%=Utility.GLanguage("Ngày tạo",session,jedis) %></TH>
	                    <TH align="center"><%=Utility.GLanguage("Người tạo",session,jedis) %> </TH>
	                    <TH align="center"><%=Utility.GLanguage("Ngày sửa",session,jedis) %> </TH>
	                    <TH align="center"><%=Utility.GLanguage("Người sửa",session,jedis) %> </TH>
	                      <TH align="center"> <%=Utility.GLanguage("tổng tiền",session,jedis) %> </TH>
	                    <TH align="center" ><%=Utility.GLanguage("Tác vụ",session,jedis) %></TH>
	                </TR>
					<%
                 		if(nhapkhoRs != null)
                 		{
                 			int m = 0;
                 			while(nhapkhoRs.next())
                 			{  		
                 				if((m % 2 ) == 0) {%>
		                         	<TR class='tbdarkrow'>
		                        <%}else{ %>
		                          	<TR class='tblightrow'>
		                        <%} %>
		                    <TD align="center"><%= nhapkhoRs.getString("PK_SEQ") %></TD>
		                    <TD align="center"><%= nhapkhoRs.getString("NGAYCHUYEN") %></TD>
		                    <TD align="center"><%= nhapkhoRs.getString("KhoXuat") %></TD>  
		                     <TD ><%= nhapkhoRs.getString("DIABAN") %></TD>
		                    <TD ><%= nhapkhoRs.getString("nppTEN") %></TD>  
		                    	 <TD align="center">
		                    	<%
		                    		String trangthai = "";
		                    		String tt = nhapkhoRs.getString("trangthai");
		                    		if(tt.equals("0"))
		                    			trangthai = "Chưa chốt";
		                    		else
		                    		{
		                    			if(tt.equals("1"))
		                    				trangthai = "Đã chốt";
		                    			else if(tt.equals("2"))
		                    			{
			                    			trangthai = "Đã hủy";
		                    			}
		                    			else if(tt.equals("3"))
		                    			{
		                    				trangthai = "Hoàn tất";
	                    				}
		                    		}
		                    	%>
		                    	<%= trangthai %>
		                    </TD>   									                                    
					     	<TD align="center"><%= nhapkhoRs.getString("NGAYTAO") %></TD>	
		                    <TD align="center"><%= nhapkhoRs.getString("NGUOITAO") %></TD>
         					<TD align="center"><%= nhapkhoRs.getString("NGAYSUA") %></TD>
							<TD align="center"><%= nhapkhoRs.getString("NGUOISUA") %></TD>
							<TD align="center"><%=formatter.format(nhapkhoRs.getDouble("tongtien")) %></TD>
		                    <TD align="center"> 
		                    <%if(tt.equals("0"))
		                    { 
		                    	if(quyen[Utility.SUA]!=0){ %>
                                	<A href = "../../RouterSvl?task=<%= Utility.dongMa(getServletContext().getInitParameter("RedirectNoScript") +"ErpChuyenkhoTTUpdateSvl?userId="+userId+"&update="+nhapkhoRs.getString("PK_SEQ")+"&loaidonhang="+nhapkhoRs.getString("loaidonhang"))%>"><IMG src="../images/Edit20.png" alt="Cập nhật" title="Cập nhật" border=0></A>&nbsp;
                              <% } 
		                    	if(quyen[Utility.CHOT]!=0){ %>
                                	<A href = "../../RouterSvl?task=<%= Utility.dongMa(getServletContext().getInitParameter("RedirectNoScript") +"ErpChuyenkhoTTSvl?userId="+userId+"&chot="+nhapkhoRs.getString("PK_SEQ")+"&loaidonhang="+nhapkhoRs.getString("loaidonhang"))%>"><img src="../images/Chot.png" alt="Chốt" title="Chốt" width="20" height="20" border=0 onclick="if(!confirm('Bạn có muốn chốt xuất chuyển hàng này?')) return false;"></A>&nbsp;
                              <% } 
		                    	if(quyen[Utility.XOA]!=0){ %>
                              		<A href = "../../RouterSvl?task=<%= Utility.dongMa(getServletContext().getInitParameter("RedirectNoScript") +"ErpChuyenkhoTTSvl?userId="+userId+"&delete="+nhapkhoRs.getString("PK_SEQ")+"&loaidonhang="+nhapkhoRs.getString("loaidonhang"))%>"><img src="../images/Delete20.png" alt="Huỷ" title="Huỷ" width="20" height="20" border=0 onclick="if(!confirm('Bạn có muốn huỷ xuất chuyển hàng này?')) return false;"></A>
                              	<% } 
		                    } else{ %>
		                    <%if(quyen[Utility.XEM]!=0){ %>
		                    	<A href = "../../RouterSvl?task=<%= Utility.dongMa(getServletContext().getInitParameter("RedirectNoScript") +"ErpChuyenkhoTTUpdateSvl?userId="+userId+"&display="+nhapkhoRs.getString("PK_SEQ")+"&loaidonhang="+nhapkhoRs.getString("loaidonhang"))%>"><IMG src="../images/Display20.png" alt="Hiển thị" title="Hiển thị" border=0></A>
		           				<A href = "../../RouterSvl?task=<%= Utility.dongMa(getServletContext().getInitParameter("RedirectNoScript") +"ErpInphieuxuatchuyennoiboSvl?userId="+userId+"&id="+nhapkhoRs.getString("PK_SEQ")+"&loaidonhang="+nhapkhoRs.getString("loaidonhang"))%>"><IMG src="../images/Printer20.png" alt="In phiếu xuất kho kiêm vận chuyển nội bộ" title="In phiếu xuất kho kiêm vận chuyển nội bộ" border=0></A>
		                     	<A href = "../../RouterSvl?task=<%= Utility.dongMa(getServletContext().getInitParameter("RedirectNoScript") +"ErpInphieudieukhoSvl?userId="+userId+"&id="+nhapkhoRs.getString("PK_SEQ")+"&loaidonhang="+nhapkhoRs.getString("loaidonhang"))%>"><IMG src="../images/pdfblue.jpg" alt="In phiếu điều kho" title="In phiếu điều kho" border=0 width="20" height="20"></A>
		                   		<A href = "../../RouterSvl?task=<%= Utility.dongMa(getServletContext().getInitParameter("RedirectNoScript") +"ErpLenhdieudonghanghoaSvl?userId="+userId+"&id="+ nhapkhoRs.getString("PK_SEQ")+"&loaidonhang="+nhapkhoRs.getString("loaidonhang"))%>"><IMG src="../images/Pdf30.jpg" alt="In lệnh điều động hàng hóa" title="In lệnh điều động hàng hóa" border=0 width="20" height="20"></A>
		                   <%}}%>
		                    </TD>
		                </TR>
                     <% m++; } nhapkhoRs.close(); } %>
					<TR class="tbfooter"> 
						 <TD align="center" valign="middle" colspan="13" class="tbfooter">
					 	 <% obj.setNextSplittings(); %>
						<script type="text/javascript" src="../scripts/phanTrang.js"></script>
						<input type="hidden" name="crrApprSplitting" value="<%= obj.getCrrApprSplitting() %>" >
						<input type="hidden" name="nxtApprSplitting" value="<%= obj.getNxtApprSplitting() %>" >
					 	<%if(obj.getNxtApprSplitting() > 1) {%>
							<img alt="Trang Dau" src="../images/first.gif" style="cursor: pointer;" onclick="View(document.forms[0].name, 1, 'view')"> &nbsp;
						<%}else {%>
							<img alt="Trang Dau" src="../images/first.gif" > &nbsp;
							<%} %>
						<% if(obj.getNxtApprSplitting() > 1){ %>
							<img alt="Trang Truoc" src="../images/prev.gif" style="cursor: pointer;" onclick="View(document.forms[0].name, eval(document.forms[0].nxtApprSplitting.value) -1, 'view')"> &nbsp;
						<%}else{ %>
							<img alt="Trang Truoc" src="../images/prev_d.gif" > &nbsp;
						<%} %>
						<%
							int[] listPage = obj.getNextSplittings();
							for(int i = 0; i < listPage.length; i++){
							if(listPage[i] == obj.getNxtApprSplitting()){ 
						%>
							<a style="color:white;"><%= listPage[i] %>/ <%=obj.getTheLastSplitting() %></a>
						<%}else { %>
							<a href="javascript:View(document.forms[0].name, <%= listPage[i] %>, 'view')"><%= listPage[i] %></a>
						<%} %>
							<input type="hidden" name="list" value="<%= listPage[i] %>" />  &nbsp;
						<%} %>
						
						<% if(obj.getNxtApprSplitting() < obj.getTheLastSplitting()){ %>
							&nbsp; <img alt="Trang Tiep" src="../images/next.gif" style="cursor: pointer;" onclick="View(document.forms[0].name, eval(document.forms[0].nxtApprSplitting.value) +1, 'view')"> &nbsp;
						<%}else{ %>
							&nbsp; <img alt="Trang Tiep" src="../images/next_d.gif" > &nbsp;
						<%} %>
						<%if(obj.getNxtApprSplitting() == obj.getTheLastSplitting()) {%>
					   		<img alt="Trang Cuoi" src="../images/last.gif" > &nbsp;
				   		<%}else{ %>
				   			<img alt="Trang Cuoi" src="../images/last.gif" style="cursor: pointer;" onclick="View(document.forms[0].name, -1, 'view')"> &nbsp;
				   		<%} %>
						</TD>
					 </TR>
				</TABLE>
            </fieldset>
        </div>
    </div>  
</div>
</form>
<%geso.dms.center.util.Utility.JedisClose(jedis); %>
</body>
</HTML><%}%>