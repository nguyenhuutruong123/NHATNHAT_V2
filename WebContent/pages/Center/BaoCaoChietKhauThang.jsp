<%@page import="java.text.DecimalFormat"%>
<%@page import="java.text.NumberFormat"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page  import = "java.util.Iterator" %>
<%@ page  import = "java.util.List" %>
<%@ page  import = "java.util.ArrayList" %>
<%@ page  import = "geso.dms.distributor.beans.hoadontaichinh.*" %>
<%@ page  import = "java.sql.ResultSet" %>
<%@ page  import = "geso.dms.center.util.*" %>
<% IBCChietKhauThang obj = (IBCChietKhauThang)session.getAttribute("obj"); %>
<% ResultSet hdRs =  obj.getHoadonRs(); %>
<% ResultSet khRs = obj.getKhRs(); %>
<% ResultSet rsNpp = obj.getNppRs(); %>
<% String userId = (String) session.getAttribute("userId");  %>
<% String userTen = (String) session.getAttribute("userTen");  %>
<% 
	String sum = (String) session.getAttribute("sum");
	Utility util = (Utility) session.getAttribute("util");
	if(!util.check(userId, userTen, sum)){
		response.sendRedirect(request.getContextPath() + "/redirect.jsp");
	}else{
		int[] quyen = new  int[5];
		quyen = util.Getquyen("BCChietKhauThangSVL", "22",userId);
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
		    document.forms["ckParkForm"].submit();
		 }
		 
		 function xuatExcel()
		 {
		 	document.forms['ckParkForm'].action.value= 'excel';
		 	document.forms['ckParkForm'].submit();
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
<form name="ckParkForm" method="post" action="../../BCChietKhauThangSvl">
<input type="hidden" name="userId" value="<%= userId %>" >
<input type="hidden" name="action" value="1" >
<input type="hidden" name="msg" value='<%= obj.getMsg() %>'>
<input type="hidden" name="view" value='TT'>

<div id="main" style="width:100%; padding-left:2px">
	<div align="left" id="header" style="width:100%; float:none">
    	<div style="width:55%; padding:5px; float:left" class="tbnavigation">
        	&nbsp;Báo cáo quản trị > Báo cáo khác > Báo cáo chiết khấu tháng  
        </div>
        <div align="right" style="padding:5px" class="tbnavigation">
        	<%=Utility.GLanguage("Chào mừng bạn",session,jedis) %>  <%= userTen %> &nbsp;
        </div>
    </div>
  	
  	<div align="left" style="width:100%; float:none; clear:left">
  		<fieldset>
    		<legend class="legendtitle"> Thông báo</legend>
    		<textarea name="dataerror"  rows="1" readonly="readonly" style ="width:100%"><%= obj.getMsg() %></textarea>
		         <% obj.setMsg(""); %>
    	</fieldset>
  	</div>
    
  	<div id="cotent" style="width:100%; float:none">
    	<div align="left" style="width:100%; float:none; clear:left; font-size:0.7em">
        <fieldset style="margin-top:5px" >
            <legend class="legendtitle"> <%=Utility.GLanguage("Tiêu chí tìm kiếm",session,jedis) %></legend>
                <TABLE width="100%" cellpadding="6px" cellspacing="0px" style="margin-top: 5px " >
                	 <TR>
                        <TD class="plainlabel" width="100px">Tháng</TD>
                        <TD class="plainlabel" width="250px" >
                            <%-- <input type="text" class="days" name="tungay" value="<%= obj.getTungay() %>" maxlength="10"  /> --%>
                            <select name="tungay" >
                            	<option value="" ></option>
                            	
                            	<% for(int i = 1; i <= 12; i++) {
                            		if(obj.getTungay().equals(Integer.toString(i))) { %>
                            			<option value="<%= i %>" selected="selected" ><%= i %></option>
                            	<%	} else {  %>
                            			<option value="<%= i %>"  ><%= i %></option>
                            	<%	} %>
                            		
                            	<% } %>
                            	
                            </select>
                        </TD>
                    
                        <TD class="plainlabel" width="100px">Năm</TD>
                        <TD class="plainlabel">
                            <%-- <input type="text" class="days" name="denngay" value="<%= obj.getDenngay() %>" maxlength="10"  /> --%>
                            <select name="denngay" >
                            	<option value="" ></option>
                            	
                            	<% for(int i = 2014; i <= 2030; i++) {
                            		if(obj.getDenngay().equals(Integer.toString(i))) { %>
                            			<option value="<%= i %>" selected="selected" ><%= i %></option>
                            	<%	} else {  %>
                            			<option value="<%= i %>"  ><%= i %></option>
                            	<%	} %>
                            		
                            	<% } %>
                            	
                            </select>
                        </TD>
                    </TR>
                                       
                    <Tr>
                    	<TD class="plainlabel"><%=Utility.GLanguage("Nhà phân phối",session,jedis) %></TD>
									<TD class="plainlabel">
										<select  name="nppId" id="nppId" style="width:250px">
											<option value="">All</option>
											<% if(rsNpp!=null){
												try{
										 		while(rsNpp.next()){
													String nppId = rsNpp.getString("pk_seq");
													if(nppId.equals(obj.getNppId())){%>
														<option selected="selected" value="<%=rsNpp.getString("pk_seq")%>">
															<%=rsNpp.getString("ten")%></option>
													<%}else{%>
														<option value="<%=rsNpp.getString("pk_seq")%>">
															<%=rsNpp.getString("ten")%></option>
										 	<% }} rsNpp.close();
												}catch(Exception e){e.printStackTrace();}
												}%>										 	
										</select>
									</TD>												
										<TD class="plainlabel"> </TD>
									<TD class="plainlabel">
										 
									</TD>					
                    </Tr>
                    
                    <TR>
								<TD class="plainlabel">Mức lấy</TD>
								<TD class="plainlabel" colspan="3">
								<%
								if(obj.getType().equals("0")){
									%>
									<input type="radio" name="type" value="1" /> Bán đúng giá &nbsp; &nbsp;
									<input type="radio" name="type" value="0"  checked="checked"/> Tất cả 
									<%
								}
								else
								{
									%>
										<input type="radio" name="type" value="1"  checked="checked"/> Bán đúng giá  &nbsp; &nbsp;
										<input type="radio" name="type" value="0"  />Tất cả 
									<%
								}
								%>
									
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