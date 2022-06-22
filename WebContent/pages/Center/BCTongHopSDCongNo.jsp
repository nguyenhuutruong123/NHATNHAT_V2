<%@page import="java.text.DecimalFormat"%>
<%@page import="java.text.NumberFormat"%>
<%@page import="java.sql.SQLException"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page  import = "java.util.Iterator" %>
<%@ page  import = "java.util.List" %>
<%@ page  import = "java.util.ArrayList" %>
<%@ page  import = "geso.dms.distributor.beans.hoadontaichinh.*" %>
<%@ page  import = "java.sql.ResultSet" %>
<%@ page  import = "geso.dms.center.util.*" %>
<% IBCTongHopSDCongNo obj = (IBCTongHopSDCongNo)session.getAttribute("obj"); %>
<% ResultSet khRs = obj.getKhRs();
	ResultSet nppRs = obj.getNppRs();
%>
<% ResultSet rsKhuVuc = obj.getkhuvuc(); %>
<% ResultSet rsVung = obj.getvung();
   ResultSet ttRs = obj.getTtRs();
%>
<% String userId = (String) session.getAttribute("userId");  %>
<% String userTen = (String) session.getAttribute("userTen");  %>
<% 
	String sum = (String) session.getAttribute("sum");
	Utility util = (Utility) session.getAttribute("util");
	if(!util.check(userId, userTen, sum)){
		response.sendRedirect(request.getContextPath() + "/redirect.jsp");
	}else{
		int[] quyen = new  int[5];
		quyen = util.Getquyen("BCTongHopSDCongNoSvl","",userId);%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd"> <% Utility Utilback = new Utility(); %>
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
	<title><%= getServletContext().getInitParameter("TITLENAME") %></title>
	<META http-equiv="Content-Type" content="text/html; charset=utf-8">
	<META http-equiv="Content-Style-Type" content="text/css">
    <LINK rel="stylesheet" type="text/css" media="print" href="../css/impression.css">
    <LINK rel="stylesheet" href="../css/main.css" type="text/css">
    <link type="text/css" rel="stylesheet" href="../css/mybutton.css">
    
    <LINK rel="stylesheet" href="../css/datepicker.css" type="text/css">
    <script language="javascript" src="../scripts/datepicker.js"></script>
   	<link href="../css/jquery-ui.css" rel="stylesheet" type="text/css"/>
  	<script type="text/javascript" src="../scripts/Timepicker/jquery.min.js"></script>
  	<script type="text/javascript" src="../scripts/Timepicker/jquery-ui.min.js"></script>

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
   
    <SCRIPT language="javascript" type="text/javascript">
     
    function submitform()
	 {  
		document.forms['ckParkForm'].action.value= 'search';
	    document.forms['ckParkForm'].submit();
	 }
    
	 function xuatExcel()
		 {
		 if (document.forms["ckParkForm"]["tuNgay"].value.length == 0) {
				setErrors("Vui lòng chọn ngày bắt đầu");					
				return;
			}
			if (document.forms["ckParkForm"]["denNgay"].value.length == 0) {
				setErrors("Vui lòng chọn ngày kết thúc");
				return;
			}	
			if (document.forms["ckParkForm"]["nppHOId"].value.length == 0) {
				setErrors("Vui lòng chọn nhà phân phối");
				return;
			}	
			var fieldShow = document.getElementsByName("fieldsHien");		
			for ( var i = 0; i < fieldShow.length; ++i) {
				fieldShow.item(i).checked = true;
			}	
			
		 	document.forms['ckParkForm'].action.value= 'excel';
		 	document.forms['ckParkForm'].submit();
		 	reset();
		 }
		 
	 function setErrors(errorMsg) {
			var errors = document.getElementById("errors");
			errors.value = errorMsg;
		}

	 function reset() {
			var fieldShow = document.getElementsByName("fieldsHien");		
			for ( var i = 0; i < fieldShow.length; ++i) {
				fieldShow.item(i).checked = false;
			}		
		};
	</SCRIPT>
	
<link href="../css/select2.css" rel="stylesheet" />
<script src="../scripts/select2.js"></script>
<script>

     $(document).ready(function() 
    		 { $("select").select2(); });

     
</script>
</head>
<body>
<form name="ckParkForm" method="post" action="../../BCTongHopSDCongNoSvl">
<input type="hidden" name="userId" value="<%= userId %>" >
<input type="hidden" name="action" value="1" >
<input type="hidden" name="msg" value='<%= obj.getMsg() %>'>
<input type="hidden" name="view" value='TT'>

<div id="main" style="width:100%; padding-left:2px">
	<div align="left" id="header" style="width:100%; float:none">
    	<div style="width:55%; padding:5px; float:left" class="tbnavigation">
        	&nbsp;Quản lý công nợ > Báo cáo > BC tổng hợp số dư công nợ cuối kỳ
        </div>
        <div align="right" style="padding:5px" class="tbnavigation">
        	<%=Utility.GLanguage("Chào mừng bạn",session,jedis) %>  <%= userTen %> &nbsp;
        </div>
    </div>
  	
  	<div align="left" style="width:100%; float:none; clear:left">
  		<tr>
			<TD align="left" colspan="4" class="legendtitle">
				<FIELDSET>
					<LEGEND class="legendtitle"><%=Utility.GLanguage("Báo lỗi nhập liệu",session,jedis) %></LEGEND>
							<textarea id="errors" readonly="readonly" name="errors" rows="1" style="width: 100% ; color:#F00 ; font-weight:bold">
						<%= obj.getMsg() %>
						</textarea>
				</FIELDSET>
			</TD>
		</tr>
  	</div>
    
  	<div id="cotent" style="width:100%; float:none">
    	<div align="left" style="width:100%; float:none; clear:left; font-size:0.7em">
        <fieldset style="margin-top:5px" >
            <legend class="legendtitle"> <%=Utility.GLanguage("Tiêu chí tìm kiếm",session,jedis) %></legend>
                <TABLE width="100%" cellpadding="6px" cellspacing="0px" style="margin-top: 5px " >
                	 <TR>
                                             
                        <TD width="16%" class="plainlabel" ><%=Utility.GLanguage("Từ ngày",session,jedis) %></TD>
								  	<TD class="plainlabel" >
										<TABLE cellpadding="0" cellspacing="0">
											<TR><TD>
												<input type="text" class="days" name="tuNgay" size="20" style="width:250px" value = "<%=obj.getTungay()%>" >
												</TD>
                                    		</TR>
										</TABLE>									</TD>
                    
                        <TD class="plainlabel" ><%=Utility.GLanguage("Đến ngày",session,jedis) %> </TD>
								  	<TD class="plainlabel"  >
							  			<TABLE cellpadding="0" cellspacing="0">
							  				<TR>
							  					<TD>
													<input type="text"  class="days" name="denNgay" size="20" style="width:250px" value = "<%=obj.getDenngay()%>" >												</TD>
												<TD>                                        		</TD>
                                     		</TR>
										</TABLE>									</TD>
                    </TR>
                                       
                    <TR>
									<TD class="plainlabel">Miền</TD>
									<TD class="plainlabel">
										<select  name="vungId" onchange="submitform();" id="vungId" name="vungId" style="width:250px">
											<option value="">All</option>
											<% if(rsVung!=null){
										 		while(rsVung.next()){
													String vungId = rsVung.getString("pk_seq");
													if(vungId.equals(obj.getvungId())){%>
														<option selected="selected" value="<%=rsVung.getString("pk_seq")%>">
															<%=rsVung.getString("ten")%></option>
													<%}else{%>
														<option value="<%=rsVung.getString("pk_seq")%>">
															<%=rsVung.getString("ten")%></option>
										 	<% }}}%>										 	
										</select>
									</TD>
									
									<TD class="plainlabel">Địa bàn  </TD>
									<TD class="plainlabel">
										<select name="ttId" id="ttId" style="width:250px"   onchange="submitform();" >
											<option value="" >All</option>
											<%if (ttRs != null)
													while (ttRs.next()) {
														if (ttRs.getString("pk_seq").equals(obj.getTtId()  )) {%>
											   <option value="<%=ttRs.getString("pk_seq")%>" selected><%=ttRs.getString("ten")%></option>
											   <%} else {%>
											   <option value="<%=ttRs.getString("pk_seq")%>"><%=ttRs.getString("ten")%></option>
											<%}}%>
										</select>
									</TD>
									</TR> 
									
								
                    <TR>			
						<TD class="plainlabel">Đối tác/Nhà phân phối</TD>
						<TD class="plainlabel" colspan="4">
							<select name="nppHOId" id="nppHOId" style="width:250px" onchange="submitform();">
									<option value="" selected >All</option>
									<% 
									if(nppRs != null)
									{
										try
	                        			{	
										   while(nppRs.next()){
										   if(nppRs.getString("pk_seq").equals(obj.getDoiTacHOId()))
										   { %>
										   <option value="<%= nppRs.getString("pk_seq") %>" selected><%=nppRs.getString("ten") %></option>
										   <%}else { %>
										   <option value="<%= nppRs.getString("pk_seq") %>"><%=nppRs.getString("ten") %></option>
										<%} }nppRs.close();} catch(SQLException ex){}%>
									<%} %>
							</select>
						</TD>   
                    </TR>
                    
                    <TR>			
						<TD class="plainlabel">Khách hàng</TD>
						<TD class="plainlabel" colspan="4">
							<select name="khId" id="khId" style="width:250px" onchange="submitform();">
									<option value="" selected >All</option>
									<% 
									if( khRs != null)
									{
										try
	                        			{	
										   while(khRs.next()){
										   if(khRs.getString("pk_seq").equals(obj.getKhId()))
										   { %>
										   <option value="<%= khRs.getString("pk_seq") %>" selected><%=khRs.getString("ten") %></option>
										   <%}else { %>
										   <option value="<%= khRs.getString("pk_seq") %>"><%=khRs.getString("ten") %></option>
										<%} }khRs.close();} catch(SQLException ex){}%>
									<%} %>
							</select>
						</TD>   
                    </TR>
                    
                    <TR>
						<TD class="plainlabel" colspan="4">
							<a class="button2" href="javascript:xuatExcel();"> 
								<img style="top: -4px;" src="../images/button.png" alt="">Xuất báo cáo </a>&nbsp;&nbsp;&nbsp;&nbsp;
					</TR> 
			
                </TABLE>                      
        </fieldset>                      
    	</div>
        
    </div>  
</div>
</form>
</body>
</HTML><%}%>