<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page  import = "java.util.Iterator" %>
<%@ page  import = "java.util.List" %>
<%@ page  import = "java.util.ArrayList" %>
<%@ page  import = "geso.dms.distributor.beans.bienbanbutru.*" %>
<%@ page  import = "java.sql.ResultSet" %>
<%@ page  import = "geso.dms.center.util.*" %>
<%@page import="java.text.NumberFormat"%>
<%@page import="java.text.DecimalFormat"%>
<% IBienBanBuTruCongNoList obj = (IBienBanBuTruCongNoList)session.getAttribute("obj"); %>
<% ResultSet nhapkhoRs =  obj.getDondathangRs(); %>
<% ResultSet khRs = obj.getKhRs(); %>
<% String userId = (String) session.getAttribute("userId");  %>
<% String userTen = (String) session.getAttribute("userTen");  %>
<% obj.setNextSplittings();
	String sum = (String) session.getAttribute("sum");
	Utility util = (Utility) session.getAttribute("util");
	if(!util.check(userId, userTen, sum)){
		response.sendRedirect(request.getContextPath() + "/redirect.jsp");
	}else
	{
		int[] quyen = new  int[6];
		quyen = util.Getquyen("BienBanBuTruCongNoSvl","",userId);
		NumberFormat formater = new DecimalFormat("##,###,###");	
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
	    document.forms["ckParkForm"].khTen.value = "";
	    document.forms["ckParkForm"].sohoadon.value = "";
	    document.forms["ckParkForm"].madonhang.value ="";
	    document.forms["ckParkForm"].submit();
	 }
	 function thongbao()
	 {
		 var tt = document.forms["ckParkForm"].msg.value;
	 	 if(tt.length>0)
	     	alert(document.forms["ckParkForm"].msg.value);
	 }
	 
	 function processing(id,chuoi){
		 document.getElementById(id).innerHTML = "<a href='#'><img src='../images/waiting.gif'  title='cho thuc hien..' border='1' longdesc='cho thuc hien..' style='border-style:outset'> Proc...</a>";
		 document.getElementById(id).href = chuoi;
		  
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
<form name="ckParkForm" method="post" action="../../BienBanBuTruCongNoSvl">
<input type="hidden" name="userId" value="<%= userId %>" >
<input type="hidden" name="action" value="1" >
<input type="hidden" name="msg" value='<%= obj.getMsg() %>'>
<input type="hidden" name="nppId" value="<%= obj.getNppId() %>" >
<input type="hidden" name="type" value="<%= obj.getType() %>" >
<script language="javascript" type="text/javascript">
    thongbao();
</script> 
<% obj.setNextSplittings(); %>
<input type="hidden"name="crrApprSplitting" value="<%= obj.getCrrApprSplitting() %>">
<input type="hidden" name="nxtApprSplitting" value="<%= obj.getNxtApprSplitting() %>">

<div id="main" style="width:100%; padding-left:2px">
	<div align="left" id="header" style="width:100%; float:none">
    	<div style="width:55%; padding:5px; float:left" class="tbnavigation">
        	&nbsp;Quản lý bán hàng> Bán hàng OTC > Lưu biên bản bù tru   
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
                        <TD class="plainlabel">
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
	                        		 <% } } khRs.close();
	                        		 } catch(Exception ex){}
	                        		}
	                        	%>
	                    	</select>
                        </TD>
                     	
                        <TD class="plainlabel" valign="middle"><%=Utility.GLanguage("Trạng thái",session,jedis) %> </TD>
                        <TD class="plainlabel" valign="middle">
                           <select name="trangthai" onchange="submitform();"  >
								<% if (obj.getTrangthai().equals("1")){%>
									<option value="" ></option>
								  	<option value="1" selected>Chờ xác nhận</option>
								  	<option value="2">Đã xác nhận</option>
								  	<option value="3" >Đã xóa</option>
								  	<option value="4" >Đã in HĐ</option>
								  	<option value="5" >Đã hủy</option>
								<%}else if(obj.getTrangthai().equals("2")) {%>
								 	<option value="" ></option>
								 	<option value="1" >Chờ xác nhận</option>
								  	<option value="2" selected>Đã xác nhận</option>
								  	<option value="3" >Đã xóa</option>
								  	<option value="4" >Đã in HĐ</option>
								  	<option value="5" >Đã hủy</option>
								<%}else if(obj.getTrangthai().equals("3")) {%>
								 	<option value="" ></option>
								 	<option value="1" >Chờ xác nhận</option>
								  	<option value="2" >Đã xác nhận</option>
								  	<option value="3" selected>Đã xóa</option> 
								  	<option value="4" >Đã in HĐ</option> 
								  	<option value="5" >Đã hủy</option>	
								<%}else if(obj.getTrangthai().equals("4")) {%>
									<option value="" ></option>
								 	<option value="1" >Chờ xác nhận</option>
								  	<option value="2" >Đã xác nhận</option>
								  	<option value="3" >Đã xóa </option> 
								  	<option value="4" selected>Đã in HĐ</option> 
								  	<option value="5" >Đã hủy</option>
								<% }else if(obj.getTrangthai().equals("5")) {%>
									<option value="" ></option>
								 	<option value="1" >Chờ xác nhận</option>
								  	<option value="2" >Đã xác nhận</option>
								  	<option value="3" >Đã xóa </option> 
								  	<option value="4" >Đã in HĐ</option> 
								  	<option value="5" selected>Đã hủy</option>
							  	<% }else {%>
									<option value="" selected></option>
								 	<option value="1" >Chờ xác nhận</option>
								  	<option value="2">Đã xác nhận</option>
								  	<option value="3"  >Đã xóa </option>
								  	<option value="4" >Đã in HĐ</option>
								  	<option value="5" >Đã hủy</option>
							<%} %>
                           </select>
                        </TD>  
                        
                                              
                    </TR>    
                    
                     <TR>
                        <TD class="plainlabel" width="100px">Số hóa đơn</TD>
                        <TD class="plainlabel" width="250px" >
                            <input type="text"  name="sohoadon" value="<%= obj.getSoHoaDon() %>"  onchange="submitform();" />
                        </TD>
                    
                        <TD class="plainlabel" width="100px">Mã đơn hàng </TD>
                        <TD class="plainlabel">
                         	<input type="text"  name="madonhang" value="<%= obj.getMadonhang() %>"  onchange="submitform();" />
                        </TD>
                    </TR>
                    
                      <TR>
                        <TD class="plainlabel" width="100px"> Mã số hóa đơn</TD>
                        <TD class="plainlabel" width="250px" >
                            <input type="text"  name="hoadonId" value="<%= obj.getHoadonId() %>"  onchange="submitform();" />
                        </TD>
                    
                        <TD class="plainlabel" width="100px"> </TD>
                        <TD class="plainlabel">
                        </TD>
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
            	<legend><span class="legendtitle"> Lưu biên bản bù trừ </span>&nbsp;&nbsp;
                </legend>
            	<TABLE width="100%" border="0" cellspacing="1" cellpadding="4">
					<TR class="tbheader">
	                    <TH align="center" style="width: 8%" >Mã số</TH>
	                    <TH align="center" style="width: 8%" >Ngày xuất HD</TH>
	                    <TH align="center" style="width: 10%" >Số HD</TH>
	                    <TH align="center" style="width: 18%" >Khách hàng</TH>
	                    <TH align="center" style="width: 8%" >Tổng giá trị</TH>
	                    <TH align="center" style="width: 10%" ><%=Utility.GLanguage("Trạng thái",session,jedis) %></TH>
	                    <TH align="center" style="width: 8%" ><%=Utility.GLanguage("Ngày sửa",session,jedis) %></TH>
	                    <TH align="center" style="width: 10%" ><%=Utility.GLanguage("Người sửa",session,jedis) %></TH>
	                    <TH align="center" style="width: 10%" ><%=Utility.GLanguage("Tác vụ",session,jedis) %></TH>
	                </TR>
					<%
                 		if(nhapkhoRs != null)
                 		{
                 			int m = 0;
                 			while(nhapkhoRs.next())
                 			{  		
                 				if((m % 2 ) == 0) {%>
		                         	<TR class='tbdarkrow' >
		                        <%}else{ %>
		                          	<TR class='tblightrow' >
		                        <%} %>
		                    <TD align="center"><%= nhapkhoRs.getString("bbId") %></TD>
		                    <TD align="center"><%= nhapkhoRs.getString("NGAYXUATHD") %></TD>
		                    <TD align="center"><%= nhapkhoRs.getString("SoHoaDon")+nhapkhoRs.getString("KyHieu") %></TD>		                      
		                    <TD ><%= nhapkhoRs.getString("nppTEN") %></TD> 
		                    <TD align="center"><%= formater.format(nhapkhoRs.getDouble("avat"))%></TD> 
		                    	 <TD align="center">
		                    	<%
		                    		String dainPGH = "";
		                    		if( obj.getType().equals("PGH") )
		                    		{
		                    			if(nhapkhoRs.getString("DAINPGH").equals("1"))
		                    				dainPGH = "Đã in";
		                    			else
		                    				dainPGH = "Chưa in";
		                    		}
		                    	
		                    		String trangthai = "";
		                    		String tt = nhapkhoRs.getString("trangthai");
		                    		if(tt.equals("1")){ //NPP TAO
		                    			trangthai = "Chờ xác nhận";
		                    		}
		                    		else
		                    		{
		                    			if(tt.equals("2")) 
		                    			{
			                    			trangthai = "Đã xác nhận";
			                    			
			                    			if( obj.getType().equals("PGH") )
	                    						trangthai += " (" + dainPGH + ") ";
		                    			}
		                    			else
		                    			{
		                    				if(tt.equals("3")) 
		                    				{
		                    					trangthai = "Đã xóa";
		                    					
		                    					if( obj.getType().equals("PGH") )
		                    						trangthai += " (" + dainPGH + ") ";
		                    				}
		                    				else 
		                    				{
		                    					if(tt.equals("5")) 
			                    				{
		                    						trangthai = "Đã hủy";
		                    						
		                    						if( obj.getType().equals("PGH") )
			                    						trangthai += " (" + dainPGH + ") ";
			                    				}
		                    					else
		                    					{
		                    						if( obj.getType().equals("PGH") )
		                    							trangthai = dainPGH;
		                    						else
		                    							trangthai = "Đã in";
		                    					}
		                    				}
		                    			}
		                    		}
		                    	%>
		                    	<%= trangthai %>
		                    </TD>   									                                    
         					<TD align="center"><%= nhapkhoRs.getString("NGAYSUA") %></TD>
							<TD align="center"><%= nhapkhoRs.getString("NGUOISUA") %></TD>
		                    <TD align="center"> 
		                 <A href = "../../BienBanBuTruCongNoPdfSvl?userId=<%=userId%>&id=<%= nhapkhoRs.getString("bbId") %>&nppId=<%= obj.getNppId()%>&type=BTCN"><IMG src="<% if (nhapkhoRs.getInt("Inhoadon")>0){ %>../images/pdfblue.jpg<%} else {%>../images/pdf.jpg <%}%> "title="In biên bản bù trừ công nợ" width="20" height="20" border=0 ></A>
		                    <%-- <% if(!obj.getType().equals("PGH")) { %>
		                    <% String ckt = nhapkhoRs.getString("ckt");  %>
		                    <% if(tt.equals("1")){ %>
		                    
		                    	<%if(quyen[Utility.SUA]!=0){ %>
                                <A href = "../../BienBanBuTruCongNoUpdateSvl?userId=<%=userId%>&update=<%=nhapkhoRs.getString("PK_SEQ") %>"><IMG src="../images/Edit20.png" alt="Cập nhật" title="Cập nhật" border=0 ></A>&nbsp;
                                <%} %>
                                
                                <%if(quyen[Utility.CHOT]!=0){ %>
                               <A id='chot_<%=nhapkhoRs.getString("PK_SEQ")%>' href="">
											<img src="../images/Chot.png" alt="Duyệt xuất kho" width="20" height="20" title="Duyệt xuất kho" border="0" onclick="processing('<%="chot_"+nhapkhoRs.getString("PK_SEQ")%>' , '../../BienBanBuTruCongNoSvl?userId=<%=userId%>&chot=<%= nhapkhoRs.getString("PK_SEQ") %>')" >
								</A> 
                              	<%} %>
                              	
                              	<%if(quyen[Utility.XOA]!=0){ %>
                              	<A id='deletephieu<%=nhapkhoRs.getString("PK_SEQ")%>'href=""><img src="../images/Delete.png" alt="Hủy đơn hàng"width="20" height="20" title="Hủy đơn hàng" border="0" onclick="if(!confirm('Bạn có xóa hóa đơn này?')) {return false ;}else{ processing('<%="deletephieu"+nhapkhoRs.getString("PK_SEQ")%>' , '../../BienBanBuTruCongNoSvl?userId=<%=userId%>&delete=<%= nhapkhoRs.getString("PK_SEQ") %>');}"  ></A>
		                   		<%} %>
		                   		
		                    <%} else{ %>		                    
		                    	<%if(quyen[Utility.XEM]!=0){ %>
		                    	<A href = "../../BienBanBuTruCongNoUpdateSvl?userId=<%=userId%>&display=<%= nhapkhoRs.getString("PK_SEQ") %>"><IMG src="../images/Display20.png" alt="Hiển thị" title="Hiển thị" border=0 ></A>
		                    	<%} %>
		                    	
		                    	
		                    	<%  if( !tt.equals("3") && !tt.equals("5") ) { %>
		                    	<%if(quyen[Utility.XEM]!=0){ %>
		                    		<A href = "../../BienBanBuTruCongNoPdfSvl?userId=<%=userId%>&pdf=<%= nhapkhoRs.getString("PK_SEQ") %>&nppId=<%= obj.getNppId()%>"><IMG src="../images/Printer30.png"  title="In hóa đơn" width="20" height="20" border=0 ></A>		                    		
		                    		<%if(  Double.parseDouble(ckt) > 0 ){ %>
		                    		<A href = "../../InKhauTruCKThangSvl?userId=<%=userId%>&id=<%= nhapkhoRs.getString("PK_SEQ") %>&nppId=<%= obj.getNppId()%>&type=BTCN"><IMG src="<% if (nhapkhoRs.getInt("Inhoadon")>0){ %>../images/pdfblue.jpg<%} else {%>../images/pdf.jpg <%}%> "title="In biên bản bù trừ công nợ" width="20" height="20" border=0 ></A>
		                    		<%} %>
		                    		
		                    	<%} %>
		                    	
		                    	<% } %>
		                    	
		                    	<%  if( !tt.equals("3") && !tt.equals("5") ) { %>
		                    		
		                    		<%if(quyen[Utility.XOA]!=0){ %>
	                              	<A href = "../../BienBanBuTruCongNoSvl?userId=<%=userId%>&delete=<%= nhapkhoRs.getString("PK_SEQ") %>"><img src="../images/Delete_Icon.png" alt="Xóa hóa đơn" title="Xóa hóa đơn" width="20" height="20" border=0 onclick="if(!confirm('Bạn có xóa hóa đơn này?')) return false;"></A>									
			                   		<%} %>
		                    		
		                    		<%if(quyen[Utility.XOA]!=0){ %>
		                    		<A href = "../../BienBanBuTruCongNoSvl?userId=<%=userId%>&huy=<%= nhapkhoRs.getString("PK_SEQ") %>"><img src="../images/Delete20.png" alt="Hủy hóa đơn" title="Hủy hóa đơn" width="20" height="20" border=0 onclick="if(!confirm('Bạn có muốn hủy hóa đơn này?')) return false;"></A>	
		                    		<%} %>
		                    		
		                    	<% } %>
		                    	
		                    <% } %>
		                    
		                    <% } else { %>
		                    
		                    		 
		                    	
		                    <% } %> --%>
		                    
		                    </TD>
		                </TR>
                     <% m++; }  } %>
                     
					<tr class="tbfooter">
							<TD align="center" valign="middle" colspan="13" class="tbfooter">
								<%if(obj.getNxtApprSplitting() >1) {%> 
									<img alt="Trang Dau" src="../images/first.gif" style="cursor: pointer;" onclick="View('ckParkForm', 1, 'view')"> &nbsp; <%}else {%>
									<img alt="Trang Dau" src="../images/first.gif">
								&nbsp; <%} %> <% if(obj.getNxtApprSplitting() > 1){ %> <img
								alt="Trang Truoc" src="../images/prev.gif"
								style="cursor: pointer;"
								onclick="Prev('ckParkForm', 'prev')"> &nbsp; <%}else{ %>
								<img alt="Trang Truoc" src="../images/prev_d.gif">
								&nbsp; <%} %> <%
						int[] listPage = obj.getNextSplittings();
						for(int i = 0; i < listPage.length; i++){
					%> <% 							
				
					if(listPage[i] == obj.getNxtApprSplitting()){ %> <a
								style="color: white;"><%= listPage[i] %>/ <%=obj.getTheLastSplitting() %></a>
								<%}else{ %> <a
								href="javascript:View('ckParkForm', <%= listPage[i] %>, 'view')"><%= listPage[i] %></a>
								<%} %> <input type="hidden" name="list"
								value="<%= listPage[i] %>" /> &nbsp; <%} %> <% if(obj.getNxtApprSplitting() < obj.getTheLastSplitting()){ %>
								&nbsp; <img alt="Trang Tiep" src="../images/next.gif"
								style="cursor: pointer;"
								onclick="Next('ckParkForm', 'next')"> &nbsp; <%}else{ %>
								&nbsp; <img alt="Trang Tiep" src="../images/next_d.gif">
								&nbsp; <%} %> <%if(obj.getNxtApprSplitting() == obj.getTheLastSplitting()) {%>
								<img alt="Trang Cuoi" src="../images/last.gif">
								&nbsp; <%}else{ %> <img alt="Trang Cuoi"
								src="../images/last.gif" style="cursor: pointer;"
								onclick="View('ckParkForm', -1, 'view')"> &nbsp; <%} %>
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