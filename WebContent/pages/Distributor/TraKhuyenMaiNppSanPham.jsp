<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@page import="java.sql.SQLException"%>
<%@ page  import = "geso.dms.distributor.beans.quanlykhuyenmai.*" %>
<%@ page  import = "geso.dms.distributor.beans.donhang.ISanpham" %>
<%@ page  import = "java.sql.ResultSet" %>
<%@ page  import = "java.util.List" %>
<%@ page import="java.text.DecimalFormat"%>
<%@ page import="java.text.NumberFormat"%>
<%@ page  import = "geso.dms.center.util.*" %>

<%	String userId = (String) session.getAttribute("userId");  
	String userTen = (String) session.getAttribute("userTen");  	
	String sum = (String) session.getAttribute("sum");
	Utility util = (Utility) session.getAttribute("util");
	if(!util.check(userId, userTen, sum)){
		response.sendRedirect(request.getContextPath() + "/redirect.jsp");
	}else{ %>

<% ITrakhuyenmaiNpp tkmBean = (ITrakhuyenmaiNpp)session.getAttribute("tkmBean"); %>
<% List<ISanpham> spList = (List<ISanpham>)tkmBean.getSpList(); 
NumberFormat formatter = new DecimalFormat("#,###,###");

%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd"> <% Utility Utilback = new Utility(); %>
<html>
<head>
<TITLE><%= getServletContext().getInitParameter("TITLENAME") %></TITLE>
	<META http-equiv="Content-Type" content="text/html; charset=utf-8">
	<META http-equiv="Content-Style-Type" content="text/css">

	<LINK rel="stylesheet" type="text/css" media="print" href="../css/impression.css">
    <LINK rel="stylesheet" href="../css/main.css" type="text/css">
    <LINK rel="stylesheet" href="../css/datepicker.css" type="text/css">
    
    <script language="javascript" src="../scripts/datepicker.js"></script>
   	<link href="../css/jquery-ui.css" rel="stylesheet" type="text/css"/>
  	<script type="text/javascript" src="../scripts/Timepicker/jquery.min.js"></script>
  	<script type="text/javascript" src="../scripts/Timepicker/jquery-ui.min.js"></script>
   
    <script language="javascript" type="text/javascript">
		function saveform()
		{	
			document.forms['tkmForm'].action.value='save';
		    document.forms['tkmForm'].submit();
		}
		function removeform()
		{	
			document.forms['tkmForm'].action.value='remove';
		    document.forms['tkmForm'].submit();
		}
		function keypress(e) //H??m d??ng d? ngan ngu?i d??ng nh?p c??c k?? t? kh??c k?? t? s? v??o TextBox
		{    
			var keypressed = null;
			if (window.event)
				keypressed = window.event.keyCode; //IE
			else
				keypressed = e.which; //NON-IE, Standard
			
			if (keypressed < 48 || keypressed > 57)
			{ 
				if (keypressed == 8 || keypressed == 127 || keypressed == 37 || keypressed == 39 || keypressed == 0 || keypressed == 46)
				{//Ph??m Delete v?? Ph??m Back
					return;
				}
				return false;
			}
		}
	</script>
    
</head>
<body>
<form name="tkmForm" method="post" action="../../TrakhuyenmaiNppUpdateSvl">
<INPUT name="userId" type="hidden" value='<%= userId %>' size="30">
<INPUT name="nppId" type="hidden" value='<%= tkmBean.getNppId() %>' size="30">
<input type="hidden" name="action" value='1'>
<input type="hidden" name="ctkmId" value='<%= tkmBean.getCtkmId() %>'>
<input type="hidden" name="tkmId" value='<%= tkmBean.getTkmId() %>'>
<div id="main" style="width:99%; padding-left:2px">
	<div align="left" id="header" style="width:100%; float:none">
    	<div style="width:55%; padding:5px; float:left" class="tbnavigation">
        	Qu???n l?? khuy???n m???i > S???n ph???m tr??? khuy???n m???i > C???p nh???t
        </div>
        <div align="right" style="padding:5px" class="tbnavigation">
        	Ch??o m???ng   <%= tkmBean.getNppTen() %> 
        </div>
    </div>
  
  	<div align="left" id="button" style="width:100%; height:32px; padding:0px; float:none" class="tbdarkrow">
    	<A href= "../../TrakhuyenmaiNppSvl?userId=<%=userId %>" >
        	<img src="../images/Back30.png" alt="Quay v???"  title="Quay v???" border="1px" longdesc="Quay ve" style="border-style:outset"></A>
        <A href="javascript:removeform()" >
        	<IMG src="../images/Delete30.png" title="Go bo thiet lap" alt="Go bo thiet lap" border ="1px" style="border-style:outset"></A>
        <A href="javascript:saveform()" >
        	<IMG src="../images/Save30.png" title="L??u l???i" alt="L??u l???i" border ="1px" style="border-style:outset"></A>
    </div>
    
  	<div align="left" style="width:100%; float:none; clear:left">
  		<fieldset>
    		<legend class="legendtitle"><%=Utility.GLanguage("B??o l???i nh???p li???u",session,jedis) %></legend>
    		<textarea name="dataerror"  style="width: 100% ; color:#F00 ; font-weight:bold" style="width:100%" rows="1" readonly="readonly"><%= tkmBean.getMsg() %></textarea>
		         <% tkmBean.setMsg(""); %>
    	</fieldset>
  	</div>
  	
    <div align="left" style="width:100%; float:none; clear:left">
    <fieldset>
    	<legend class="legendtitle"> Tr??? khuy???n m???i </legend>
        	
        	<div style="float:none; width:100%" align="left">
            	<table width="100%" cellspacing="0" cellpadding="6px">
                    <tr>
                    	<td class="plainlabel" width="20%" valign="middle">Ch????ng tr??nh khuy???n m???i</td>
                    	<td class="plainlabel">
                            <i><%= tkmBean.getScheme() + " - " + tkmBean.getCtkmDiengiai() %></i>
                    	</td> 
                    	<td class="plainlabel">&nbsp;</td>                   
                    </tr>       
                    <tr>
                    	<td class="plainlabel" width="30%" valign="middle"><%=Utility.GLanguage("T??? ng??y",session,jedis) %></td>
                    	<td class="plainlabel">
                            <i><%= tkmBean.getTungay() %></i>
                    	</td> 
                    	<td class="plainlabel">&nbsp;</td>                   
                    </tr>     
                    <tr>
                    	<td class="plainlabel" width="30%" valign="middle"><%=Utility.GLanguage("?????n ng??y",session,jedis) %></td>
                    	<td class="plainlabel">
                            <i><%= tkmBean.getDenngay() %></i>
                    	</td> 
                    	<td class="plainlabel">&nbsp;</td>                   
                    </tr> 
                    <tr>
                    	<td class="plainlabel" width="30%" valign="middle">Tr??? khuy???n m???i</td>
                    	<td class="plainlabel">
                            <i><%= tkmBean.getTkmId() + " - " + tkmBean.getDiengiai() %></i>
                    	</td> 
                    	<td class="plainlabel">&nbsp;</td>                   
                    </tr>    
                    <tr>
                    	<td class="plainlabel" width="30%" valign="middle">T??nh tr???ng</td>
                    	<td class="plainlabel">
                    		<% if(tkmBean.getTrangthai().length() > 0) {%>
                            	<i style="color: red;">???? thi???t l???p s???n ph???m ??u ti??n khi ??p khuy???n m???i</i>
                            <%}else{ %>
                            	<i style="color: red;">Ch???n s???n ph???m khi ??p khuy???n m???i</i>
                            <%} %>
                    	</td> 
                    	<td class="plainlabel">&nbsp;</td>   
                    </tr>         
                 </table>
                 <hr>
                 <% if(spList.size() > 0){ %>
                 <table width="100%" cellpadding="4px" cellspacing="1px">
                 	<tr>
                    	<th class="tbheader" width="20%" align="center">M?? s???n ph???m</th>
                        <th class="tbheader" width="40%"  align="center">T??n s???n ph???m</th>
                        <th class="tbheader" width="10%"  align="center">????n gi??</th>
                        <th class="tbheader" width="10%"  align="center">T???n hi???n t???i</th>
                        <th class="tbheader" width="10%"  align="center" width="15%">Th??? t??? ??u ti??n</th>
                    </tr>
                    
                    <% for(int i = 0; i < spList.size(); i++) { ISanpham sp = spList.get(i); %>
		    			<tr class="tbdarkrow">
	                    	<td align="left">
	                    		<input type="text" name="spMa" style="width: 100%; text-align: left;" value=" <%= sp.getMasanpham() %>" readonly="readonly">
	                    		<input type="hidden" name="spId" style="width: 100%; text-align: left;" value=" <%= sp.getId() %>" readonly="readonly">
	                    	</td>
	                        <td align="left">
	                        	<input type="text" name="spTen" style="width: 100%; text-align: left;" value=" <%= sp.getTensanpham() %>" readonly="readonly">
	                        </td>
	                        <td align="right">
	                        	<input type="text" name="dongia" style="width: 100%; text-align: right;" value=" <%= formatter.format(Double.parseDouble(sp.getDongia())) %>" readonly="readonly">
	                        </td>
	                        <td align="right">
	                        	<input type="text" name="soluong" style="width: 100%; text-align: right;" value=" <%= formatter.format(Double.parseDouble(sp.getSoluong())) %>" readonly="readonly">
	                        </td>
	                        <td align="right">
	                        	<input type="text" name="thutu" style="width: 100%; text-align: right;" value=" <%= sp.getDonvitinh() %>" onkeypress="return keypress(event);">
	                        </td>
                    	</tr>
	     			<% }%>
	     			
                    <tr class="tbfooter"><td colspan="5">&nbsp;</td></tr>
                 </table>
                 <%} %>
            </div>   
    </fieldset>	
    </div>
</div>
</form>
</body>
</HTML>
<%
tkmBean.DbClose();
}%>