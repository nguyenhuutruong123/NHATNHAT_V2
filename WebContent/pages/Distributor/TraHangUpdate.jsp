<%@page import="java.sql.SQLException"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page  import = "geso.dms.distributor.beans.chuyenkho.*" %>
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
		response.sendRedirect("/CaiLan/index.jsp");
	}else{ %>

<% IChuyenkho pxkBean = (IChuyenkho)session.getAttribute("pxkBean"); %>
<% ResultSet nvbh = (ResultSet)pxkBean.getNvBanhang(); %>
<% ResultSet kho = (ResultSet)pxkBean.getKhoRs(); %>
<% ResultSet spRs = (ResultSet)pxkBean.getSpRs(); %>
<% NumberFormat formatter = new DecimalFormat("#,###,###"); %>
<% Hashtable<String, Integer> sp_sl = (Hashtable<String, Integer>)pxkBean.getSp_Soluong(); %>

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
			element.value = DinhDangTien(element.value);
			if(element.value== '' )
			{
				element.value= '';
			}
			
			//UPDATE
			Update_SoLuong();
			
		}	
		
		function Update_SoLuong()
		{
			var spIds = document.getElementsByName("spIds");
			var soluong = document.getElementsByName("soluong");
			
			for(var i = 0; i < spIds.length; i++ )
			{
				var _soluong = document.getElementsByName(spIds.item(i).value + "_spSOLUONG");
				//alert('SP ID: ' + spIds[i] + ' --- So dong: ' +  _soluong.length);
				
				var _tongluong = 0;
				for(var j = 0; j < _soluong.length; j++ )
				{
					_tongluong = parseFloat(_tongluong) + parseFloat(_soluong.item(j).value.replace(/,/g,""));
				}
				
				if(parseFloat(_tongluong) > 0 )
					soluong.item(i).value = DinhDangTien(_tongluong);
				else
					soluong.item(i).value = '';
			}
		}
		
	</script>
    
</head>
<BODY leftmargin="0" bottommargin="0" topmargin="0" rightmargin="0">
<form name="pxkForm" method="post" action="../../ChuyenkhoUpdateSvl">
<INPUT name="userId" type="hidden" value='<%= userId %>' size="30">
<INPUT name="nppId" type="hidden" value='<%= pxkBean.getNppId() %>' size="30">
<input type="hidden" name="action" value='1'>
<input type="hidden" name="id" value='<%= pxkBean.getId() %>'>
<div id="main" style="width:99%; padding-left:2px">
	<div align="left" id="header" style="width:100%; float:none">
    	<div style="width:50%; padding:5px; float:left" class="tbnavigation">
        	Qu???n l?? t???n kho > Chuy???n kho > C???p nh???t
        </div>
        <div align="right" style="padding:5px" class="tbnavigation">
        	Ch??o m???ng nh?? ph??n ph???i  <%= pxkBean.getNppTen() %> 
        </div>
    </div>
  
  	<div align="left" id="button" style="width:100%; height:32px; padding:0px; float:none" class="tbdarkrow">
    	<A href= "../../ChuyenkhoSvl?userId=<%=userId %>" >
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
    	<legend class="legendtitle">Phi???u chuy???n kho </legend>
        	
        	<div style="float:none; width:100%" align="left">
            	<table width="100%" cellspacing="0" cellpadding="6px">
                    <tr>
                    	<td class="plainlabel" width="20%" valign="middle">Ng??y chuy???n</td>
                    	<td class="plainlabel">
                            <input type="text" size="11"  class="days"
                                    id="ngaychuyen" name="ngaychuyen" value="<%= pxkBean.getNgaychuyen() %>" maxlength="10" readonly />
                    	</td> 
                    	<td class="plainlabel">&nbsp;</td>                   
                    </tr>       
                    <tr>
           				<td class="plainlabel"><%=Utility.GLanguage("NH??N VI??N B??N H??NG",session,jedis) %><FONT class="erroralert"> *</FONT></td> 
                        <td class="plainlabel">
                            <select name="nvbhId" id="nvbhId" onchange="submitform()">
                            	<option value="">&nbsp;</option>
                                <% if(nvbh != null){
									  try{ while(nvbh.next()){ 
						    			if(nvbh.getString("nvbhId").equals(pxkBean.getNvbhId())){ %>
						      				<option value='<%=nvbh.getString("nvbhId")%>' selected><%=nvbh.getString("nvbhTen") %></option>
						      			<%}else{ %>
						     				<option value='<%=nvbh.getString("nvbhId")%>'><%=nvbh.getString("nvbhTen") %></option>
						     	<%}} nvbh.close(); }catch(Exception e){ System.out.println("Exception: " + e.getMessage()); } }%>
                            </select>	
                        </td>
                        <td class="plainlabel">&nbsp;</td>
                    </tr>
                    <tr>
           				<td class="plainlabel">Kho chuy???n<FONT class="erroralert"> *</FONT></td> 
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
                 </table>
                 <hr>
                 <% if(spRs != null){ %>
                 <table width="100%" cellpadding="0px" cellspacing="1px">
                 	<tr>
                    	<th class="tbheader" align="center" width="10%">M?? s???n ph???m</th>
                        <th class="tbheader" align="center" width="35%">T??n s???n ph???m</th>
                        <th class="tbheader" align="center" width="10%">????n v??? t??nh</th>
                        <th class="tbheader" align="center" width="15%">T???n t???i kho NPP</th>
                        <th class="tbheader" align="center" width="15%">T???n t???i kho NVBH</th>
                        <th class="tbheader" align="center" width="10%">S??? l?????ng chuy???n</th>
                        <th class="tbheader" align="center" width="10%">S??? l??</th>
                    </tr>
                    
                    <% try { 
                    	
                    	int count = 0;
                    	while(spRs.next()){
                    	
                    	%>
		    			<tr class="tbdarkrow">
	                    	<td>
	                    		<input type="hidden" name="spIds" value="<%= spRs.getString("spId") %>" >
	                    		<input type="text" value="<%= spRs.getString("spMa") %>" style="width: 100%" disabled="disabled">	
	                    	</td>
	                        <td>
	                    		<input type="text" name="spTen" value="<%= spRs.getString("spTen") %>" style="width: 100%" disabled="disabled">	
	                    	</td>
	                        <td>
	                    		<input type="text" value="<%= spRs.getString("donvi") %>" style="width: 100%" disabled="disabled">	
	                    	</td>
	                        <td>
	                    		<input type="text" name="available" value="<%= formatter.format(spRs.getDouble("available")) %>" style="width: 100%; text-align: right;" disabled="disabled">	
	                    	</td>
	                    	<td>
	                    		<input type="text" value="<%= formatter.format(spRs.getDouble("avaiNVBH")) %>" style="width: 100%; text-align: right;" disabled="disabled">	
	                    	</td>
	                    	<td>
	                    		<input type="text" name="soluong" value="" style="width: 100%; text-align: right;" readonly="readonly" >	
	                    	</td>
	                    	<td align="center" >
	                    		
	                    		<a href="" id="scheme_<%= spRs.getString("spId") %>" rel="subcontent_<%= spRs.getString("spId") %>">
		           	 				&nbsp; <img alt="Ch???n s??? l??" src="../images/vitriluu.png"></a>
		           	 		
	           	 		 		<DIV id="subcontent_<%= spRs.getString("spId") %>" style="position:absolute; visibility: hidden; border: 9px solid #80CB9B;
				                             background-color: white; width: 450px; max-height:300px; overflow:auto; padding: 4px;">
				                    <table width="95%" align="center">
				                        <tr>
				                            <th width="100px" style="font-size: 11px">S??? l?????ng</th>
				                            <th width="100px" style="font-size: 11px">S??? l??</th>
				                            <th width="100px" style="font-size: 11px">Ng??y h???t h???n</th>
				                            <th width="100px" style="font-size: 11px">T???n kho</th>
				                        </tr>
		                            	<%
		                            		ResultSet soloRs = pxkBean.getSoloTheoSp(spRs.getString("spId"), "0");
			                        		if(soloRs != null)
			                        		{
			                        			while(soloRs.next())
			                        			{
			                        			%>
			                        			
			                        			<tr>
			                        				<td>
			                        				<% if(sp_sl.get( spRs.getString("spId") + "__" + soloRs.getString("SOLO") ) != null ) { %>
			                        					<input type="text" style="width: 100%;text-align: right;" name="<%= spRs.getString("spId") %>_spSOLUONG" value="<%= formatter.format((double) sp_sl.get( spRs.getString("spId") + "__" + soloRs.getString("SOLO") ) ) %>" onkeyup="Dinhdang(this);" >
			                        				<% } else { %>
			                        					<input type="text" style="width: 100%;text-align: right;" name="<%= spRs.getString("spId") %>_spSOLUONG" value="" onkeyup="Dinhdang(this);" >
			                        				<% } %>
			                        				</td>
			                        				<td>
			                        					<input type="text" style="width: 100%;text-align: center;" name="<%= spRs.getString("spId") %>_spSOLO" value="<%= soloRs.getString("SOLO") %>" readonly="readonly">
			                        				</td>
			                        				<td>
			                        					<input type="text" style="width: 100%;text-align: center;" name="<%= spRs.getString("spId") %>_spNGAYHETHAN" value="<%= soloRs.getString("NGAYHETHAN") %>" readonly="readonly">
			                        				</td>
			                        				<td>
			                        					<input type="text" style="width: 100%;text-align: right;" name="<%= spRs.getString("spId") %>_spTONKHO" value="<%= formatter.format(soloRs.getDouble("AVAILABLE")) %>" readonly="readonly">
			                        				</td>
			                        			</tr>
			                        			
			                        		 <% } } %>
			                        		 
				                     </table>
				                     <div align="right">
				                     	<label style="color: red" ></label>
				                     	&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
				                     	<a href="javascript:dropdowncontent.hidediv('subcontent_<%= spRs.getString("spId") %>')" > ????ng l???i </a>
				                     </div>
					            </DIV> 
					            
					            <script type="text/javascript">
					            	dropdowncontent.init('scheme_<%= spRs.getString("spId") %>', "left-top", 300, "click");
					            </script>
	                    		
	                    	</td>
                    	</tr>
	     			<% count++; } } catch(Exception e){}%>
	     			
                    <tr class="tbfooter"><td colspan="8">&nbsp;</td></tr>
                 </table>
                 <%} %>
            </div>
            <div align="left" style="width:100%; float:none; clear:none; display:none" id="sanphamList">              
        </div>      
    </fieldset>	
    </div>
</div>
</form>
</BODY>
<script type="text/javascript">
	Update_SoLuong();
</script>
</HTML>
<% 	

	try{
	if(nvbh != null)
		nvbh.close();
	if(pxkBean != null){
		pxkBean.DBclose();
	pxkBean= null;}
	
	}
	catch (SQLException e) {}

%>
<%}%>