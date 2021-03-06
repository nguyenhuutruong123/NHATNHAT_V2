<%@page import="java.sql.SQLException"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page  import = "geso.dms.center.beans.doisolo.*" %>
<%@ page  import = "java.sql.ResultSet" %>
<%@ page  import = "java.util.Hashtable" %>
<%@ page  import = "java.text.DecimalFormat" %>
<%@ page  import = "java.text.NumberFormat" %>

<%@ page  import = "geso.dms.center.util.*" %>

<%	String userId = (String) session.getAttribute("userId");  
	String userTen = (String) session.getAttribute("userTen");  	
	String sum = (String) session.getAttribute("sum");
	Utility util = (Utility) session.getAttribute("util");
	if(!util.check(userId, userTen, sum)){
		response.sendRedirect(request.getContextPath() + "/redirect.jsp");
	}else{ %>

<% IErpDoisolo pxkBean = (IErpDoisolo)session.getAttribute("pxkBean"); %>
<% ResultSet nvbh = (ResultSet)pxkBean.getNvBanhang(); %>
<% ResultSet kho = (ResultSet)pxkBean.getKhoRs(); %>
<% ResultSet spRs = (ResultSet)pxkBean.getSpRs(); %>
<% NumberFormat formatter = new DecimalFormat("#,###,###"); %>
<% Hashtable<String, String> sp_solo = (Hashtable<String, String>)pxkBean.getSp_Solo(); %>
<% Hashtable<String, Integer> sp_sl = (Hashtable<String, Integer>)pxkBean.getSp_Soluong(); %>
<% Hashtable<String, String> sp_ngayhethan = (Hashtable<String, String>)pxkBean.getSp_Ngayhethan(); %>
<% Hashtable<String, String> sp_ngayhethanOLD = (Hashtable<String, String>)pxkBean.getSp_ngayhethanOLD(); %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd"> <% Utility Utilback = new Utility(); %>
<html>
<head>
	<TITLE><%= getServletContext().getInitParameter("TITLENAME") %></TITLE>
	<META http-equiv="Content-Type" content="text/html; charset=utf-8">
	<META http-equiv="Content-Style-Type" content="text/css">

	<LINK rel="stylesheet" type="text/css" media="print" href="../css/impression.css">
    <LINK rel="stylesheet" href="../css/main.css" type="text/css">
    
   	<link href="../css/jquery-ui.css" rel="stylesheet" type="text/css"/>
   	<script type="text/javascript" src="../scripts/dropdowncontent.js"></script>
   
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
   	
    <script language="javascript" type="text/javascript">
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
		
		function submitform()
		{   
		    document.forms['pxkForm'].action.value='submitForm';
		    document.forms['pxkForm'].submit();
		}
		
		function saveform()
		{	
			document.forms['pxkForm'].action.value='save';
		    document.forms['pxkForm'].submit();
		}
		
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
			element.value = DinhDangTien(element.value.replace(/,/g,""));
		}	
		
	</script>
    
</head>
<BODY leftmargin="0" bottommargin="0" topmargin="0" rightmargin="0">
<form name="pxkForm" method="post" action="../../ErpDoiSoLoUpdateSvl">
<INPUT name="userId" type="hidden" value='<%= userId %>' size="30">
<INPUT name="nppId" type="hidden" value='<%= pxkBean.getNppId() %>' size="30">
<input type="hidden" name="action" value='1'>
<div id="main" style="width:99%; padding-left:2px">
	<div align="left" id="header" style="width:100%; float:none">
    	<div style="width:50%; padding:5px; float:left" class="tbnavigation">
        	Qu???n l?? t???n kho > Xu???t chuy???n, ?????i h??ng > ?????i s??? l?? > T???o m???i
        </div>
        <div align="right" style="padding:5px" class="tbnavigation">
        	Ch??o m???ng nh?? ph??n ph???i  <%= pxkBean.getNppTen() %> 
        </div>
    </div>
  
  	<div align="left" id="button" style="width:100%; height:32px; padding:0px; float:none" class="tbdarkrow">
    	<A href= "../../ErpDoisoloSvl?userId=<%=userId %>" >
        	<img src="../images/Back30.png" alt="Quay v???"  title="Quay v???" border="1px" longdesc="Quay ve" style="border-style:outset"></A>
        <A href="javascript:saveform()" >
        	<IMG src="../images/Save30.png" title="L??u l???i" alt="L??u l???i" border ="1px" style="border-style:outset"></A>
    </div>
    
  	<div align="left" style="width:100%; float:none; clear:left">
  		<fieldset>
    		<legend class="legendtitle"><%=Utility.GLanguage("B??o l???i nh???p li???u",session,jedis) %></legend>
    		<textarea name="dataerror" style="width:100%" rows="1" readonly="readonly"><%= pxkBean.getMessage() %></textarea>
		         <% pxkBean.setMessage(""); %>
    	</fieldset>
  	</div>
  	
    <div align="left" style="width:100%; float:none; clear:left">
    <fieldset>
    	<legend class="legendtitle">?????i s??? l?? </legend>
        	
        	<div style="float:none; width:100%" align="left">
            	<table width="100%" cellspacing="0" cellpadding="6px">
                    <tr>
                    	<td class="plainlabel" width="20%" valign="middle">Ng??y ?????i</td>
                    	<td class="plainlabel">
                            <input type="text" size="11"  class="days"
                                    id="ngaychuyen" name="ngaychuyen" value="<%= pxkBean.getNgaychuyen() %>" maxlength="10" readonly />
                    	</td> 
                    	<td class="plainlabel">&nbsp;</td>                   
                    </tr>          
                    <tr>
           				<td class="plainlabel">Kho h??ng ?????i<FONT class="erroralert"> *</FONT></td> 
                        <td class="plainlabel">
                            <select name="khoId" id="khoId" onchange="submitform()">
                            	<option value="">&nbsp;</option>
                                <% if(kho != null){
									  try{ while(kho.next()){ 
						    			if(kho.getString("pk_seq").equals(pxkBean.getKhoId())){ %>
						      				<option value='<%=kho.getString("pk_seq")%>' selected><%=kho.getString("khoTen") %></option>
						      			<%}else{ %>
						     				<option value='<%=kho.getString("pk_seq")%>'><%=kho.getString("khoTen") %></option>
						     	<%}} kho.close(); }catch(java.sql.SQLException e){} }%>
                            </select>	
                        </td>
                        <td class="plainlabel">&nbsp;</td>
                    </tr>
                    <tr style="display:none;">
           				<td class="plainlabel">K??nh b??n h??ng ?????i<FONT class="erroralert"> *</FONT></td> 
                        <td class="plainlabel">
                            <select name="kbhId" id="kbhId" onchange="submitform()">
                                <% if(nvbh != null){
									  try{ while(nvbh.next()){ 
						    			if(nvbh.getString("pk_seq").equals(pxkBean.getNvbhId())){ %>
						      				<option value='<%=nvbh.getString("pk_seq")%>' selected><%=nvbh.getString("diengiai") %></option>
						      			<%}else{ %>
						     				<option value='<%=nvbh.getString("pk_seq")%>'><%=nvbh.getString("diengiai") %></option>
						     	<%}} nvbh.close(); }catch(Exception e){ System.out.println("Exception: " + e.getMessage()); } }%>
                            </select>	
                        </td>
                        <td class="plainlabel">&nbsp;</td>
                    </tr>
                 </table>
                 <hr>
                 <% if(spRs != null){ %>
                 <table width="100%" cellpadding="0px" cellspacing="1px">
                 	<tr>
                    	<th class="tbheader" align="center" width="5%">M?? s???n ph???m</th>
                        <th class="tbheader" align="center" width="18%">T??n s???n ph???m</th>
                        <th class="tbheader" align="center" width="5%">????n v??? </th>
                        <th class="tbheader" align="center" width="6%">S??? l??</th>
                        <th class="tbheader" align="center" width="8%">Ng??y h???t h???n</th>
                        <th class="tbheader" align="center" width="8%">S??? l?????ng t???n</th>
                        <th class="tbheader" align="center" width="8%">??ang booked</th>
                        <th class="tbheader" align="center" width="8%">Hi???n h???u</th>
                        <th class="tbheader" align="center" width="9%" style="color: red;" >S??? l?????ng ?????i</th>
                        <th class="tbheader" align="center" width="9%" style="color: red;">S??? l?? ?????i</th>
                        <th class="tbheader" align="center" width="9%" style="color: red;">Ng??y h???t h???n</th>
                    </tr>
                    
                    <% try 
                    { 
                    	while(spRs.next())
                    	{
                    		String solo = sp_solo.get( spRs.getString("spId") + "__" + spRs.getString("solo") + "__" + spRs.getString("spNgayHetHan")     ) != null ? sp_solo.get(spRs.getString("spId") + "__" + spRs.getString("solo")  + "__" + spRs.getString("spNgayHetHan")       ) : "";
                    		String soluong = sp_sl.get(spRs.getString("spId") + "__" + spRs.getString("solo") + "__" + spRs.getString("spNgayHetHan")    ) != null ? formatter.format(Double.parseDouble(Integer.toString(sp_sl.get(spRs.getString("spId") + "__" + spRs.getString("solo")+ "__" + spRs.getString("spNgayHetHan")     )))) : "";
                    		String ngayhethan = sp_ngayhethan.get(spRs.getString("spId") + "__" + spRs.getString("solo")+ "__" + spRs.getString("spNgayHetHan")     ) != null ? sp_ngayhethan.get(spRs.getString("spId") + "__" + spRs.getString("solo")+ "__" + spRs.getString("spNgayHetHan")   ) : "";
                    	%>
		    			<tr class="tbdarkrow">
	                    	<td>
	                    		<input type="hidden" name="spIds" value="<%= spRs.getString("spId") %>" >
	                    		<input type="text" value="<%= spRs.getString("spMa") %>" style="width: 100%" readonly="readonly" >	
	                    	</td>
	                        <td>
	                    		<input type="text" name="spTen" value="<%= spRs.getString("spTen") %>" style="width: 100%" readonly="readonly" >	
	                    	</td>
	                        <td>
	                    		<input type="text" value="<%= spRs.getString("donvi") %>" style="width: 100%" readonly="readonly" >	
	                    	</td>
	                    	<td>
	                    		<input type="text" name="soloOLD" value="<%= spRs.getString("solo") %>" style="width: 100%" readonly="readonly">	
	                    	</td>
	                    	<td>
	                    		<input type="text" name="ngayhethanOLD" value="<%= spRs.getString("spNgayHetHan") %>" style="width: 100%" readonly="readonly">	
	                    	</td>
	                    	
	                        <td>
	                    		<input type="text" name="soluong" value="<%= formatter.format(spRs.getDouble("soluong")) %>" style="width: 100%; text-align: right;" disabled="disabled">	
	                    	</td>
	                    	<td>
	                    		<input type="text" name="booked" value="<%= formatter.format(spRs.getDouble("booked")) %>" style="width: 100%; text-align: right;" disabled="disabled">	
	                    	</td>
	                    	<td>
	                    		<input type="text" name="available" value="<%= formatter.format(spRs.getDouble("available")) %>" style="width: 100%; text-align: right;" disabled="disabled">	
	                    	</td>
	                    	<td>
	                    		<input type="text" name="soluongDOI" value="<%= soluong %>" style="width: 100%; text-align: right;" onkeyup="Dinhdang(this);" >	
	                    	</td>
	                    	<td>
	                    		<input type="text" name="soloDOI" value="<%= solo %>" style="width: 100%;"  >	
	                    	</td>
	                    	<td >
	                    		<input type="text" name="ngayhethanDOI" value="<%= ngayhethan %>" style="width: 100%;" class="days" >	
	                    	</td>
                    	</tr>
	     			<%  } } catch(Exception e){ e.printStackTrace(); }%>
	     			
                    <tr class="tbfooter"><td colspan="11">&nbsp;</td></tr>
                 </table>
                 <%} %>
            </div>
                  
    </fieldset>	
    </div>
</div>
</form>
</BODY>

</HTML>
<% 	

	try{
	if(nvbh != null)
		nvbh.close();
	
	sp_solo.clear();
	sp_solo.clone();
	sp_sl.clear();
	sp_sl.clone();
	sp_ngayhethan.clear();
	sp_ngayhethan.clone();
	
	if(pxkBean != null){
		pxkBean.DBclose();
	pxkBean= null;}
	
	}
	catch (SQLException e) {}

%>
<%}%>