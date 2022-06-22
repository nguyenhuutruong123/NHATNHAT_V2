<%@page import="java.sql.SQLException"%>
<%@page import="geso.dms.distributor.beans.dangkykhuyenmaitichluy.IDangkykhuyenmaitichluy"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page  import = "geso.dms.distributor.beans.dangkytrungbay.*" %>
<%@ page  import = "java.sql.ResultSet" %>
<%@ page  import = "java.util.Hashtable" %>
<%@ page  import = "geso.dms.center.util.*" %>

<%	String userId = (String) session.getAttribute("userId");  
	String userTen = (String) session.getAttribute("userTen");  	
	String sum = (String) session.getAttribute("sum");
	Utility util = (Utility) session.getAttribute("util");
	if(!util.check(userId, userTen, sum)){
		response.sendRedirect(request.getContextPath() + "/redirect.jsp");
	} else { %>
<% IDangkykhuyenmaitichluy obj = (IDangkykhuyenmaitichluy)session.getAttribute("obj"); %>
<% ResultSet ctkmIds = (ResultSet)obj.getCtkmRs(); %>
<% ResultSet khRs = (ResultSet)obj.getKhList(); %>
<% ResultSet nvbhRs = (ResultSet)obj.getNvBanhang(); %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd"> <% Utility Utilback = new Utility(); %>
<html>
<head>
	<TITLE><%= getServletContext().getInitParameter("TITLENAME") %></TITLE>
	<META http-equiv="Content-Type" content="text/html; charset=utf-8">
	<META http-equiv="Content-Style-Type" content="text/css">
    <LINK rel="stylesheet" type="text/css" media="print" href="../css/impression.css">
    <LINK rel="stylesheet" href="../css/main.css" type="text/css">
  
    <script type="text/javascript">
	    function saveform()
		{  
		    document.forms['dktbForm'].action.value='save';
		    document.forms['dktbForm'].submit();
		}
	    
		function submitform()
		{
			document.forms['dktbForm'].action.value='submit';
		    document.forms['dktbForm'].submit();
		}
		
		function submitform2()
		{
			var cttb = document.getElementById("ctkmId");
			if(cttb.value == "")
			{
				alert('vui lòng chọn chương trình khuyến mãi..');
				return;
			}
			document.forms['dktbForm'].action.value='submit';
		    document.forms['dktbForm'].submit();
		}
		
		function CheckALL()
		{ 
			var khachhang = document.getElementsByName("khIds");
			for(i=0; i<khachhang.length; i++)
			{
			 	if(document.dktbForm.chkAll.checked ==true)
			 	{
			 	  	khachhang[i].checked = true;
				}
			 	else
			 	{
					khachhang[i].checked =false;
				}
			 }
		 }
	</script>
</head>
<BODY leftmargin="0" bottommargin="0" topmargin="0" rightmargin="0">
<form name="dktbForm" method="post" action="">
<INPUT name="userId" type="hidden" value='<%=userId %>' size="30">
<input type="hidden" name="nppId" value='<%= obj.getNppId() %>'>
<input type="hidden" name="id" value='<%= obj.getId() %>'>
<input type="hidden" name="action" value='1'>

   <div id="main" style="width:99%; padding-left:2px">
	 <div align="left" id="header" style="width:100%; float:none">
    	  <div style="width:40%; padding:5px; float:left" class="tbnavigation">
        	Quản lý khuyến mãi > Đăng ký khuyến mãi tích lũy > Hiển thị
          </div>
          <div align="right" style="padding:5px" class="tbnavigation">
        	Chào mừng  <%= obj.getNppTen() %> &nbsp;
         </div>
    </div>
  
  	<div align="left" id="button" style="width:100%; height:32px; padding:0px; float:none" class="tbdarkrow">
    	<A href= "../../DangkykhuyenmaitichluySvl?userId=<%= userId %>" >
        	<img src="../images/Back30.png" alt="Quay ve"  title="Quay ve" border="1px" longdesc="Quay ve" style="border-style:outset"></A>
        	
        	
        	
    </div>
  	<div align="left" style="width:100%; float: none"> 
    	<fieldset>
        	<legend class="legendtitle"><%=Utility.GLanguage("Báo lỗi nhập liệu",session,jedis) %></legend>
            <textarea name="dataerror"  style="width: 100% ; color:#F00 ; font-weight:bold" cols="71" rows="1"  style="width: 100% " readonly="readonly" ><%= obj.getMessage() %></textarea>
           
        </fieldset>
    </div>
    <div align="left" style="width:100%; float:none">
    <fieldset>
    	<legend class="legendtitle"> Đăng ký khuyến mãi tích lũy </legend>
        <div style=" width:100%; float:non; clear:left; font-size:0.7em">
        <TABLE width="100%" cellpadding="5px" cellspacing="0px">
             <TR>
                <TD class="plainlabel"  width="130px">Chương trình </TD>
                <TD class="plainlabel" >
                    <select name="ctkmId" id="ctkmId"  onchange="submitform()">
                       <option value='All'></option>
                            <% if(ctkmIds != null){
					  		try{ while(ctkmIds.next()){ 
		    					if(ctkmIds.getString("pk_seq").equals(obj.getctkmId())){ %>
		      					<option value='<%=ctkmIds.getString("pk_seq")%>' selected><%=ctkmIds.getString("scheme") %></option>
		      				<%}else{ %>
		      				<option value='<%=ctkmIds.getString("pk_seq")%>'><%=ctkmIds.getString("scheme") %></option>
		      				<%}} ctkmIds.close(); }catch(java.sql.SQLException e){} }%>
                     </select>
                </TD>
            </TR>	
             							
            <TR>
                <TD class="plainlabel"><%=Utility.GLanguage("Từ ngày",session,jedis) %> </TD>
                <TD class="plainlabel" >
                    <input type="text" value="<%= obj.getTungay() %>" readonly="readonly" />
                   &nbsp;&nbsp;&nbsp;&nbsp; &nbsp;&nbsp;&nbsp;&nbsp; &nbsp;&nbsp;&nbsp;&nbsp; Đến ngày &nbsp;&nbsp;&nbsp;&nbsp; 
                    <input type="text" value="<%= obj.getDenngay() %>" readonly="readonly" />
                </TD>
            </TR> 
            
            <TR style="display: none;">
            	<TD class="plainlabel"><%=Utility.GLanguage("NHÂN VIÊN BÁN HÀNG",session,jedis) %> </TD>
            	<TD class="plainlabel">
            		
            	</TD>
            </TR>
                     				
        </TABLE>
        
        <hr />
        <table style="width: 100%" cellpadding="0" cellspacing="1">
        	<Tr class="tbheader">
        		<td width="20%" >Mã khách hàng</td>
        		<td width="35%" >Tên khách hàng</td>
        		<td width="35%" >Địa chỉ</td>
        		<td align="center" >Chọn <input type="checkbox" name="chkAll" id="chkAll" onchange="CheckALL();" > </td>
        	</Tr>
        	<% if(khRs != null)
        	{ 
        		while(khRs.next())
        		{
        			%>
        			
        			<TR>
        				<td>
        					<input type="text" value="<%= khRs.getString("PK_SEQ") %>" style="width: 100%" readonly="readonly" > 
        				</td>
        				<td>
        					<input type="text" value="<%= khRs.getString("TEN") %>" style="width: 100%" readonly="readonly" >  
        				</td>
        				<td>
        					<input type="text" value="<%= khRs.getString("DIACHI") %>" style="width: 100%" readonly="readonly" > 
        				</td>
        				<td align="center" >
        					<% if(obj.getKhId().contains(khRs.getString("PK_SEQ"))) { %> 
        						<input type="checkbox" name="khIds" value="<%= khRs.getString("PK_SEQ")  %>"  checked="checked" >
        					<% } else { %> 
        						<input type="checkbox" name="khIds" value="<%= khRs.getString("PK_SEQ")  %>"  >
        					<%  } %>
        				</td>
        			</TR>
        			
        		<%  }
        		khRs.close();
        	} %>
        </table>
        
        </div>
	</fieldset>
 </div>
 </div>
        
    
</form>
</BODY>
</HTML>
<% 	
if(obj != null){
	obj.DBclose();
	obj = null;
}
	try{
		if(ctkmIds != null)
			ctkmIds.close();
	}
	catch (SQLException e) {}

%>
<%}%>

    