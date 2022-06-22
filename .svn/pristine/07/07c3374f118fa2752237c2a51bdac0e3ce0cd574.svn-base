<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page  import = "java.util.Iterator" %>
<%@ page  import = "java.util.List" %>
<%@ page  import = "java.util.ArrayList" %>
<%@ page  import = "geso.dms.center.beans.hoadontaichinh.*" %>
<%@ page  import = "java.sql.ResultSet" %>
<%@ page  import = "geso.dms.center.util.*" %>
<%@page import="java.text.NumberFormat"%>
<%@page import="java.text.DecimalFormat"%>

<%
NumberFormat formatter = new DecimalFormat("#,###,###");
IErpHoadontaichinhList obj = (IErpHoadontaichinhList)session.getAttribute("obj"); %>
<% ResultSet nhapkhoRs =  obj.getDondathangRs(); %>
<% ResultSet khRs = obj.getKhRsETC(); %>
<% ResultSet khRsHopDong = obj.getKhRsHopDong(); %>
<% String userId = (String) session.getAttribute("userId");  %>
<% String userTen = (String) session.getAttribute("userTen");  
   NumberFormat formater = new DecimalFormat("##,###,###");%>
<% obj.setNextSplittings();
	String sum = (String) session.getAttribute("sum");
	Utility util = (Utility) session.getAttribute("util");
	if(!util.check(userId, userTen, sum)){
		response.sendRedirect(request.getContextPath() + "/redirect.jsp");
	}else{
		int[] quyen = new  int[6];
		
		if( obj.getNOIBO().equals("0") )
			quyen = util.Getquyen("ErpBienbannghiemthuHOSvl", "&KM=0&NOIBO=0", userId);
		else
			quyen = util.Getquyen("ErpBienbannghiemthuHOSvl", "&NOIBO=1", userId);

%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd"> <% Utility Utilback = new Utility(); %>
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
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
    </script>
    <SCRIPT language="javascript" type="text/javascript">
	 function submitform()
	 {   
		document.forms["ckParkForm"].action.value = "";
	    document.forms["ckParkForm"].submit();
	 }
	 function newform()
	 {   
		 
		document.forms["ckParkForm"].action.value = "Tao moi";
	    document.forms["ckParkForm"].submit();
	 }
	 
	 function inmau2()
	 {   
		var tungay= document.forms["ckParkForm"].tungay.value;
		var denngay= document.forms["ckParkForm"].denngay.value ;
		var khTen= document.forms["ckParkForm"].khTen.value ;
		var sohopdong= document.forms["ckParkForm"].khrssohopdong.value ;
		if(!khTen =="")
		{ 
			if(sohopdong==""){
				
				alert(" Vui lòng chọn số họp đồng");
				
			}
			else
			{
				if(tungay=="" && denngay == ""){
					alert(" Vui lòng chọn khách hàng, thời gian bắt đầu và thời gian kết thúc");
				}
				else{
					document.forms["ckParkForm"].action.value = "in2";
				    document.forms["ckParkForm"].submit();
				}
			}
		}
		else
		{
		alert(" Vui lòng chọn khách hàng");
		}
		
		
		
		
	 }
	 
	 function inmau3()
	 {  
		 	var tungay= document.forms["ckParkForm"].tungay.value;
			var denngay= document.forms["ckParkForm"].denngay.value ;
			var khTen= document.forms["ckParkForm"].khTen.value ;
			var sohopdong= document.forms["ckParkForm"].khrssohopdong.value ;
			if(!khTen =="")
			{ 
				if(sohopdong==""){
					
					alert(" Vui lòng chọn số họp đồng");
					
				}
				else
				{
					if(tungay=="" && denngay == ""){
						alert(" Vui lòng chọn khách hàng, thời gian bắt đầu và thời gian kết thúc");
					}
					else{
						document.forms["ckParkForm"].action.value = "in3";
					    document.forms["ckParkForm"].submit();
					}
				}
			}
			else
			{
			alert(" Vui lòng chọn khách hàng");
			}
			
			
	 }
	 function clearform()
	 {   
		document.forms["ckParkForm"].action.value = "";
	    document.forms["ckParkForm"].tungay.value = "";
	    document.forms["ckParkForm"].denngay.value = "";
	    document.forms["ckParkForm"].action.value = "";
	    document.forms["ckParkForm"].khrssohopdong.value = "";
	    document.forms["ckParkForm"].submit();
	 }
	 
	 function thongbao()
	 {
		 var tt = document.forms["ckParkForm"].msg.value;
	 	 if(tt.length>0)
	     	alert(document.forms["ckParkForm"].msg.value);
	 }
	</SCRIPT>
	
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
<form name="ckParkForm" method="post" action="../../ErpBienbannghiemthuHOSvl">
<input type="hidden" name="userId" value="<%= userId %>" >
<input type="hidden" name="action" value="1" >
<input type="hidden" name="msg" value='<%= obj.getMsg() %>'>
<input type="hidden" name="nppId" value='<%= obj.getnppId() %>'>
<input type="hidden" name="km" value='<%= obj.getLoaikm() %>'>
<input type="hidden" name="NOIBO" value='<%= obj.getNOIBO() %>'>
<input type="hidden" name="currentPage" value="<%= obj.getCurrentPage() %>" >
<script language="javascript" type="text/javascript">
    thongbao();
</script> 

<div id="main" style="width:100%; padding-left:2px">
	<div align="left" id="header" style="width:100%; float:none">
    	<div style="width:55%; padding:5px; float:left" class="tbnavigation">
        	&nbsp;Quản lý bán hàng >  Chức năng > In biên bảng nghiệm thu
        </div>
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
                        <TD class="plainlabel" width="100px"><%=Utility.GLanguage("Từ ngày",session,jedis) %></TD>
                        <TD class="plainlabel" width="250px" >
                            <input type="text" class="days" name="tungay" value="<%= obj.getTungay() %>" maxlength="10" onchange="submitform();" />
                        </TD>
                    
                        <TD class="plainlabel" width="100px"><%=Utility.GLanguage("Đến ngày",session,jedis) %></TD>
                        <TD class="plainlabel" colspan="4">
                            <input type="text" class="days" name="denngay" value="<%= obj.getDenngay() %>" maxlength="10" onchange="submitform();" />
                        </TD>
                    </TR>
                    
                    <TR>   
                 		<TD class="plainlabel" width="100px">Khách hàng</TD>
                        <TD class="plainlabel">
                            <select name = "khTen" class="select2" style="width: 200px" onchange="submitform();" >
	                    		<option value=""> </option>
	                        	<%
	                        		if(khRs != null)
	                        		{
	                        			try
	                        			{
	                        			while(khRs.next())
	                        			{  
	                        			if( khRs.getString("pk_seq").equals(obj.getKhTen())){ %>
	                        				<option value="<%= khRs.getString("pk_seq") %>" selected="selected" ><%= khRs.getString("ten") %></option>
	                        			<%}else { %>
	                        				<option value="<%= khRs.getString("pk_seq") %>" ><%= khRs.getString("ten") %></option>
	                        		 <% } } khRs.close();} catch(Exception ex){}
	                        		}
	                        	%>
	                    	</select>
                        	</TD>
                        	
                        	
                        <%-- <TD class="plainlabel" width="100px">Số hợp đồng</TD>
                        <TD class="plainlabel"   colspan="4" >
                            <input type="text"  name="sohopdong" value="<%=obj.getSohopdong()%>" maxlength="10" onchange="submitform();" />
                        </TD> --%>
                        
                        
                        <TD class="plainlabel" width="100px">Số hợp đồng</TD>
                        <TD class="plainlabel"  colspan="4" valign="middle">
                            <select name = "khrssohopdong" class="select2" style="width: 200px" onchange="submitform();" >
	                    		<option value=""> </option>
	                        	<%
	                        		if(khRsHopDong != null)
	                        		{
	                        			try
	                        			{
	                        			while(khRsHopDong.next())
	                        			{  
	                        			if( khRsHopDong.getString("pk_seq").equals(obj.getSohopdong())){ %>
	                        				<option value="<%= khRsHopDong.getString("pk_seq") %>" selected="selected" ><%= khRsHopDong.getString("sohopdong") %></option>
	                        			<%}else { %>
	                        				<option value="<%= khRsHopDong.getString("pk_seq") %>" ><%= khRsHopDong.getString("sohopdong") %></option>
	                        		 <% } } khRsHopDong.close();} catch(Exception ex){
	                        			 ex.printStackTrace();
	                        		 }
	                        		}
	                        	%>
	                    	</select>
                        	</TD>
                      	  
                    <TR>  
                    <tr>
                        <td colspan="6" class="plainlabel">
                            <a class="button" href="javascript:submitform()">
                                <img style="top: -4px;" src="../images/Search30.png" alt=""><%=Utility.GLanguage("Tìm kiếm",session,jedis) %> </a>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                            <a class="button2" href="javascript:clearform()">
                                <img style="top: -4px;" src="../images/button.png" alt=""><%=Utility.GLanguage("Nhập lại",session,jedis) %></a>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                       
                        <a class="button" href="javascript:inmau2()">
                                <img style="top: -4px; " src="../images/Printer30.png"  alt="">In biên bảng nghiệm thu mẫu 2</a>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                            <a class="button2" href="javascript:inmau3()">
                                <img style="top: -4px;" src="../images/Printer30.png" alt="">In biên bảng nghiệm thu mẫu 3 </a>&nbsp;&nbsp;&nbsp;&nbsp;
                       
                       
                        </td>
                    </tr> 					
                </TABLE>                      
        </fieldset>                      
    	</div>
        <div align="left" style="width:100%; float:none; clear:left; font-size:0.7em">
        	<fieldset>
            	<legend><span class="legendtitle"> Xuất hóa đơn tài chính</span>&nbsp;&nbsp;
            	
            	  <%-- <%if(quyen[Utility.THEM]!=0 && obj.getNOIBO().equals("0") ){ %>
                	<a class="button3" href="javascript:newform()">
                    <img style="top: -4px;" src="../images/New.png" alt=""><%=Utility.GLanguage("Tạo mới",session,jedis) %> </a> 
                 <%} %>    --%>
                 
                </legend>
            	<TABLE width="100%" border="0" cellspacing="1" cellpadding="4">
					<TR class="tbheader">
	                    <TH align="center" style="width: 8%" >Mã số</TH>
	                    <TH align="center" style="width: 8%" >Ngày xuất HD</TH>
	                    <TH align="center" style="width: 8%" >Số hóa đơn</TH>
	                    <TH align="center" style="width: 18%" >ETC / Đối tác</TH>
	                    <TH align="center" style="width: 8%" ><%=Utility.GLanguage("Trạng thái",session,jedis) %></TH>
	                    <TH align="center" style="width: 8%" >Tổng tiền</TH>
	                    <TH align="center" style="width: 8%" ><%=Utility.GLanguage("Ngày tạo",session,jedis) %></TH>
	                    <TH align="center" style="width: 8%" ><%=Utility.GLanguage("Người tạo",session,jedis) %></TH>
	                    <TH align="center" style="width: 8%" ><%=Utility.GLanguage("Ngày sửa",session,jedis) %></TH>
	                    <TH align="center" style="width: 8%" ><%=Utility.GLanguage("Người sửa",session,jedis) %></TH>
	                    <TH align="center" style="width: 10%" ><%=Utility.GLanguage("Tác vụ",session,jedis) %></TH>
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
		                    <TD align="center"><%= nhapkhoRs.getString("NGAYXUATHD") %></TD>
		                    <TD align="center"><%= nhapkhoRs.getString("SOHOADON")+nhapkhoRs.getString("kyhieu") %></TD>
		                    <TD ><%= nhapkhoRs.getString("khTEN") %></TD>  
		                    	 <TD align="center">
		                    	<%
		                    		String trangthai = "";
		                    		String tt = nhapkhoRs.getString("trangthai");
		                    		if(tt.equals("1")){ //NPP TAO
		                    			trangthai = "Chờ xác nhận";
		                    		}else
		                    		{
		                    			if(tt.equals("2")) {
			                    			trangthai = "Đã xác nhận";
		                    			}else{
		                    				if(tt.equals("4")) 
		                    					trangthai = "Đã in HĐ";
		                    				else
		                    				{
		                    					if(tt.equals("3")) 
			                    					trangthai = "Đã xóa";
		                    					else 
		                    						trangthai = "Đã hủy";
		                    				}
		                    			}
		                    			
		                    		}
		                    	%>
		                    	<%= trangthai %>
		                    </TD>   
		                    <TD align="center"><%= formater.format(nhapkhoRs.getDouble("TONGTIEN")) %></TD>									                                    
					     	<TD align="center"><%= nhapkhoRs.getString("NGAYTAO") %></TD>	
		                    <TD align="center"><%= nhapkhoRs.getString("NGUOITAO") %></TD>
         					<TD align="center"><%= nhapkhoRs.getString("NGAYSUA") %></TD>
							<TD align="center"><%= nhapkhoRs.getString("NGUOISUA") %></TD>
									
		                    <TD align="center"> 
		                    <%
		                     if(tt.equals("1")){ %>
		                     
		                     
		                     
								<%-- <%if(quyen[Utility.SUA]!=0){ %>
									  <A href = "../../ErpHoadontaichinhUpdateSvl?userId=<%=userId%>&update=<%=nhapkhoRs.getString("PK_SEQ") %>&km=<%=nhapkhoRs.getString("loaihd")%>"><IMG src="../images/Edit20.png" alt="Cập nhật" title="Cập nhật" border=0></A>&nbsp;
                                <%} %>
                                
                                <%if(quyen[Utility.CHOT]!=0){ %>
                                	<A href = "../../ErpHoadontaichinhSvl?userId=<%=userId%>&chot=<%= nhapkhoRs.getString("PK_SEQ") %>&km=<%=nhapkhoRs.getString("loaihd")%>"><img src="../images/Chot.png" alt="Duyệt xuất kho" title="Duyệt xuất kho" width="20" height="20" border=0 ></A>&nbsp;
                              	<%} %>
                              	
                              	<%if(quyen[Utility.XOA]!=0 && !obj.getLoaikm().equals("1")){ %>
                              		<A href = "../../ErpHoadontaichinhSvl?userId=<%=userId%>&delete=<%= nhapkhoRs.getString("PK_SEQ") %>&km=<%=nhapkhoRs.getString("loaihd")%>"><img src="../images/Delete20.png" alt="Xóa hóa đơn" title="Xóa hóa đơn" width="20" height="20" border=0 onclick="if(!confirm('Bạn có muốn xóa hóa đơn này?')) return false;"></A>									
		                  		<%} %>	 --%>
		                  			                  		
		                    <%} else{ %>
		                    	
		                    	<% if(quyen[Utility.XEM]!=0) { %>
		                    		<A href = "../../ErpHoadontaichinhUpdateSvl?userId=<%=userId%>&display=<%= nhapkhoRs.getString("PK_SEQ") %>"><IMG src="../images/Display20.png" alt="Hiển thị" title="Hiển thị" border=0></A>
		                   		<%} %>
		                   		
		                   		
		                   		
		                   		
		                   		
		                   		<%if( !tt.equals("3") && !tt.equals("5") && !obj.getNOIBO().equals("1") ){ %>
	                   			
	                   					<A href = "../../ErpInBienbannghiemthuHOSvl?userId=<%=userId%>&pdf=<%= nhapkhoRs.getString("PK_SEQ") %>&nppId=<%= obj.getnppId()%>&type=1"><IMG src="../images/Printer30.png" alt="In biên bảng nghiệm thu mẫu 1" title="In biên bảng nghiệm thu mẫu 1" width="20" height="20" border=0></A>
	                    			
		                  	 	<%} %> 	
		                   	
		                   
		                    	
		                    	
                              	<%-- <%  if( !tt.equals("3") && !tt.equals("5") ) { %>
		                    		
		                    		<%if(quyen[Utility.XOA]!=0  && !obj.getLoaikm().equals("1")){ %>
	                              	<A href = "../../ErpHoadontaichinhSvl?userId=<%=userId%>&delete=<%= nhapkhoRs.getString("PK_SEQ") %>&km=<%=nhapkhoRs.getString("loaihd")%>"><img src="../images/Delete_Icon.png" alt="Xóa hóa đơn" title="Xóa hóa đơn" width="20" height="20" border=0 onclick="if(!confirm('Bạn có xóa hóa đơn này?')) return false;"></A>									
			                   		<%} %>
		                    		
		                    		<%if(quyen[Utility.XOA]!=0 && !obj.getLoaikm().equals("1")){ %>
		                    		<A href = "../../ErpHoadontaichinhSvl?userId=<%=userId%>&huy=<%= nhapkhoRs.getString("PK_SEQ") %>&km=<%=nhapkhoRs.getString("loaihd")%>"><img src="../images/Delete20.png" alt="Hủy hóa đơn" title="Hủy hóa đơn" width="20" height="20" border=0 onclick="if(!confirm('Bạn có muốn hủy hóa đơn này?')) return false;"></A>	
		                    		<%} %>
		                    		
		                    	<% } %>	 --%>
                              		
		                    <% } %>
		                    </TD>
		                </TR>
                     <% m++; } nhapkhoRs.close(); } %>
					<tr class="tbfooter" > 
						 <TD align="center" valign="middle" colspan="13" class="tbfooter">
						 	 <% obj.setNextSplittings(); %>
												 <script type="text/javascript" src="../scripts/phanTrang.js"></script>
												<input type="hidden" name="crrApprSplitting" value="<%= obj.getCrrApprSplitting() %>" >
												<input type="hidden" name="nxtApprSplitting" value="<%= obj.getNxtApprSplitting() %>" >

											 	<%if(obj.getNxtApprSplitting() >1) {%>
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
												%>
												
												<% 
												
												System.out.println("Current page:" + obj.getNxtApprSplitting());
												System.out.println("List page:" + listPage[i]);
												
												if(listPage[i] == obj.getNxtApprSplitting()){ %>
												
													<a  style="color:white;"><%= listPage[i] %>/ <%=obj.getTheLastSplitting() %></a>
												<%}else{ %>
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
					 </tr>
					 
				</TABLE>
            </fieldset>
        </div>
    </div>  
</div>
</form>
</body>
</HTML><%}%>