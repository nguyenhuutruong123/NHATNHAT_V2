<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page  import = "geso.dms.center.beans.tratrungbay.*" %>
<%@ page  import = "geso.dms.center.beans.tratrungbay.imp.*" %>
<%@ page  import = "geso.dms.center.beans.nhomsptrungbay.ISanpham" %>
<%@ page  import = "geso.dms.center.beans.nhomsptrungbay.imp.Sanpham" %>
<%@ page  import = "java.sql.ResultSet" %>
<%@ page  import = "java.util.Hashtable" %>
<%@ page  import = "java.util.List" %>
<%@ page import="java.text.DecimalFormat"%>
<%@ page import="java.text.NumberFormat"%>
<%@ page  import = "geso.dms.center.util.*" %>
<% ITratrungbay tratbBean = (ITratrungbay)session.getAttribute("tratbBean"); %>
<% ResultSet nhomsp = tratbBean.getNhomspRs(); %>
<% Hashtable<String, Integer> sp_nhomSpIds = tratbBean.getSp_nhomspIds(); %>
<% List<ISanpham> spList = tratbBean.getSpList(); %>
<% List<ISanpham> spSudungList = tratbBean.getSpSudungList(); 
   NumberFormat formatter = new DecimalFormat("#,###,###"); %>

<%Utility util = new Utility(); %>
<%String url = util.getChuyenNguUrl("TraTrungBaySvl", "",session); %>
<% String userId = (String) session.getAttribute("userId");  %>
<% String userTen = (String) session.getAttribute("userTen");  %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd"> <% Utility Utilback = new Utility(); %>
<html>
<head>
	<TITLE><%= getServletContext().getInitParameter("TITLENAME") %></TITLE>
	<META http-equiv="Content-Type" content="text/html; charset=utf-8">
	<META http-equiv="Content-Style-Type" content="text/css">

	<LINK rel="stylesheet" type="text/css" media="print" href="../css/impression.css">
	<LINK rel="stylesheet" href="../css/main.css" type="text/css">
    <script type="text/javascript" src="../scripts/jquery.min.js"></script>
    <script type="text/javascript" src="..scripts/jquery-1.js"></script>
    <link type="text/css" rel="stylesheet" href="../css/mybutton.css">
    <LINK rel="stylesheet" type="text/css" href="../css/style.css" />
	<style type="text/css">
		#mainContainer{
			width:600px;
			margin:0 auto;
			text-align:left;
			height:100%;
			border-left:3px double #000;
			border-right:3px double #000;
		}
		#formContent{
			padding:5px;
		}
		/* END CSS ONLY NEEDED IN DEMO */
			
		/* Big box with list of options */
		#ajax_listOfOptions{
			position:absolute;	/* Never change this one */
			width:auto;	/* Width of box */
			height:200px;	/* Height of box */
			overflow:auto;	/* Scrolling features */
			border:1px solid #317082;	/* Dark green border */
			background-color:#C5E8CD;	/* White background color */
	    	color: black;
			text-align:left;
			font-size:1.0em;
			z-index:100;
		}
		#ajax_listOfOptions div{	/* General rule for both .optionDiv and .optionDivSelected */
			margin:1px;		
			padding:1px;
			cursor:pointer;
			font-size:1.0em;
		}
		#ajax_listOfOptions .optionDiv{	/* Div for each item in list */
			
		}
		#ajax_listOfOptions .optionDivSelected{ /* Selected item in the list */
			background-color:#317082; /*mau khi di chuyen */
			color:#FFF;
		}
		#ajax_listOfOptions_iframe{
			background-color:#F00;
			position:absolute;
			z-index:5;
		}
		form{
			display:inline;
		}
		#dhtmltooltip
		{
			position: absolute;
			left: -300px;
			width: 150px;
			border: 1px solid black;
			padding: 2px;
			background-color: lightyellow;
			visibility: hidden;
			z-index: 100;
			/*Remove below line to remove shadow. Below line should always appear last within this CSS*/
			filter: progid:DXImageTransform.Microsoft.Shadow(color=gray,direction=135);
		}	
		#dhtmlpointer
		{
			position:absolute;
			left: -300px;
			z-index: 101;
			visibility: hidden;
		}	
	</style>
	<script type="text/javascript" src="../scripts/ajax.js"></script>
	<script type="text/javascript" src="../scripts/tratrungbay_sanpham.js"></script>
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
    
	<script language="javascript" type="text/javascript">
		function replaces()
		{
			var masp = document.getElementsByName("masp");
			var tensp = document.getElementsByName("tensp");
			var dongia = document.getElementsByName("dongia");
			var soluong = document.getElementsByName("soluong");
			var type = document.getElementById("type");
	
			var i;
			for(i=0; i < masp.length; i++)
			{
				if(masp.item(i).value != "")
				{
					var sp = masp.item(i).value;
					var pos = parseInt(sp.indexOf(" - "));
					if(pos > 0)
					{
						masp.item(i).value = sp.substring(0, pos);
						sp = sp.substr(parseInt(sp.indexOf(" - ")) + 3);
						tensp.item(i).value = sp.substring(0, parseInt(sp.indexOf(" [")));
						sp = sp.substr(parseInt(sp.indexOf(" [")) + 2);
						dongia.item(i).value = sp.substring(0, parseInt(sp.indexOf("]")));
					}
				}
				else
				{
					tensp.item(i).value = "";
					dongia.item(i).value = "";
					dongia.item(i).value = "";
					soluong.item(i).value = "";
				}			
			}
			setTimeout(replaces, 20);
		}	
		replaces();
		
		function submitform()
		{   
		   document.forms["trakmForm"].submit();
		}
		
		function saveform()
		{
			var type = document.getElementById("type").value;
			if(type == "")
			{
				alert('Vui l??ng ch???n lo???i tr??? tr??ng b??y...');
				return;
			}
			if(type == 1) //tra tien
			{
				var tongtien = document.getElementById("tongtien").value;
				if(tongtien == "")
				{
					alert('Ban ph???i nh???p t???ng l?????ng ti???n tr??ng b??y...');
					return;
				}
			}
			if(type == 2) //tra sanpham
			{
				var hinhthuc = document.getElementById("hinhthuc").value;
				if(hinhthuc == "")
				{
					alert('Vui l??ng ch???n h??nh th???c tr??? s???n ph???m..');
					return;
				}
				
				if(hinhthuc == 2)
				{
					var tongluong = document.getElementById("tongluong").value;
					if(tongluong == "")
					{
						alert('B???n ph???i nh???p t???ng l?????ng s???n ph???m...');
						return;
					}
				}
				
				if(checkSanphamNhap() == false)
				{
					alert('Kh??ng c?? s???n ph???m n??o ???????c nh???p...');
					return;
				}
				
				if(checkTrungmasp() == false)
				{
					alert('Ki???m tra l???i c??c s???n ph???m tr??? tr??ng b??y tr??ng m??..');
					return;
				}
				
				if(hinhthuc == 1)
				{
					if(checkSanpham() == false)
					{
						alert('Ki???m tra l???i s??? l?????ng s???n ph???m tr??? tr??ng b??y...');
						return;
					}
				}	
			}
			
			document.forms["trakmForm"].action.value = "save";
			document.forms["trakmForm"].submit();
		}
		
		function checkSanpham()
		{	
			var masp = document.getElementsByName("masp");
			var soluong = document.getElementsByName("soluong");
			for(k=0; k < masp.length; k++)
			{
				if(masp.item(k).value != "")
				{
					if(soluong.item(k).value == "")
						return false;
				}	
			}
			return true;
		}
		
		function checkSanphamNhap()
		{
			var masp = document.getElementsByName("masp");
			for(h=0; h < masp.length; h++)
			{
				if(masp.item(h).value != "")
				{
					return true;
				}	
			}
			return false;
		}
		
		function checkTrungmasp()
		{
			var masp = document.getElementsByName("masp");
			for(l = 0; l < (masp.length - 1) ; l++)
			{
				for(m = (parseInt(l) + 1); m < masp.length; m++)
				{
					if(masp.item(l).value != "" && masp.item(m).value != "")
					{
						if(masp.item(l).value == masp.item(m).value)
							return false;
					}
				}
			}
			return true;
		}
		
		function clearform()
		{
			var masp = document.getElementsByName("masp");
			for(h = 0; h < masp.length; h++)
			{
				masp.item(h).value = "";
			}
		}
		function keypress(e)
		{    
			var keypressed = null;
			if (window.event)
				keypressed = window.event.keyCode; //IE
			else
				keypressed = e.which; //NON-IE, Standard
			
			if (keypressed < 48 || keypressed > 57)
			{ 
				if (keypressed == 8 || keypressed == 127 || keypressed == 37 || keypressed == 39)
				{
					return;
				}
				return false;
			}
		}
	</script>
</head>
<BODY leftmargin="0" bottommargin="0" topmargin="0" rightmargin="0">
<form name="trakmForm" method="post" action="../../TratrungbayUpdateSvl">
<% redis.clients.jedis.Jedis jedis =  geso.dms.center.util.Utility.getJedis(); %><% geso.dms.center.util.Csrf csdr=new geso.dms.center.util.Csrf(request,response,true,false,true);%>
 <input type="hidden" name="<%=csdr.get_tokenName() %>" value='<%=csdr.get_tokenValue() %>'>

<input type="hidden" name="userId" value='<%= userId %>'>
<input type="hidden" name="action" value='1'>
<div id="main" style="width:99%; padding-left:2px">
	<div align="left" id="header" style="width:100%; float:none">
    	<div style="width:55%; padding:5px; float:left" class="tbnavigation">
        	<%= url %>  > <%=Utility.GLanguage("T???o m???i",session,jedis) %>
        </div>
        <div align="right" style="padding:5px" class="tbnavigation">
        	<%=Utility.GLanguage("Ch??o m???ng b???n",session,jedis) %>  &nbsp;&nbsp;<%= userTen %> &nbsp;&nbsp;
        </div>
    </div>
  
  	<div align="left" id="button" style="width:100%; height:32px; padding:0px; float:none" class="tbdarkrow">
    	<A href= "javascript:history.back()" >
        	<img src="../images/Back30.png" alt="Quay ve"  title="Quay ve" border="1px" longdesc="Quay ve" style="border-style:outset"></A>
        <A href="javascript:saveform()" >
        	<IMG src="../images/Save30.png" title="Luu lai" alt="Luu lai" border ="1px" style="border-style:outset"></A>
    </div>
  	
  	<div align="left" style="width:100%; float:none; clear:left">
  		<fieldset>
    		<legend class="legendtitle"><%=Utility.GLanguage("B??o l???i nh???p li???u",session,jedis) %></legend>
    		<textarea name="dataerror"  style="width: 100% ; color:#F00 ; font-weight:bold" rows="1" style="width: 100%" readonly="readonly" readonly="readonly"><%= tratbBean.getMessage() %></textarea>
		         <% tratbBean.setMessage(""); %>
    	</fieldset>
  	</div>
  	
    <div align="left" style="width:100%; float:none; clear:left">
    <fieldset>
    	<legend class="legendtitle"> <%=Utility.GLanguage("Tr??? tr??ng b??y",session,jedis) %></legend>
        		<div style="float:none; width:100%" align="left">
                <TABLE width="100%" cellpadding="6" cellspacing="0">								
                    <TR>
                        <TD width="15%" class="plainlabel" valign="top"><%=Utility.GLanguage("Di???n gi???i",session,jedis) %> </TD>
                        <TD class="plainlabel" valign="top"><textarea name="diengiai" style="width:500px" rows="1"><%= tratbBean.getDiengiai() %></textarea></TD>
                    </TR>               
                    <TR>
                        <TD class="plainlabel"><%=Utility.GLanguage("Lo???i",session,jedis) %></TD>
                        <TD class="plainlabel">
                            <select name="type" id="type" onChange="submitform()">
                                	<% if(tratbBean.getType().equals("1")){ %>		                            	                            	
		                                <option value="1" selected><%=Utility.GLanguage("Tr??? ti???n",session,jedis) %></option>
		                                <option value="2"><%=Utility.GLanguage("Tr??? s???n ph???m",session,jedis) %></option>  
		                                <option value=""> </option>	
	                                <%} else {if(tratbBean.getType().equals("2")){ %>
	                                	<option value="2" selected><%=Utility.GLanguage("Tr??? s???n ph???m",session,jedis) %></option>  
	                                	<option value="1"><%=Utility.GLanguage("Tr??? ti???n",session,jedis) %></option>
		                                <option value=""> </option>
	                               <% } else{ %>
	                               		<option value="" selected> </option>
	                                	<option value="1"><%=Utility.GLanguage("Tr??? ti???n",session,jedis) %></option>
	                                	<option value="2"><%=Utility.GLanguage("Tr??? s???n ph???m",session,jedis) %></option>    
	                               <% }} %>                        
                            </select>
                         </TD> 
                    </TR>
                    <% if(tratbBean.getType().equals("2")){ %>
	                    <TR>
	                        <TD class="plainlabel"><%=Utility.GLanguage("H??nh th???c",session,jedis) %> </TD>
	                        <TD class="plainlabel">
	                            <select name="hinhthuc" id="hinhthuc" onChange="submitform();" >
		                        <% if(tratbBean.getHinhthuc().equals("1")){ %>                           
		                            <option value="1" selected><%=Utility.GLanguage("B???t bu???c nh???p s??? l?????ng t???ng s???n ph???m",session,jedis) %></option>
		                            <option value="2"><%=Utility.GLanguage("B???t k??? trong",session,jedis) %></option>
		                            <option value=""> </option>
		                        <%}else{if(tratbBean.getHinhthuc().equals("2")){ %>                      	
		                            <option value="2" selected><%=Utility.GLanguage("B???t k??? trong",session,jedis) %></option>
		                            <option value="1"><%=Utility.GLanguage("B???t bu???c nh???p s??? l?????ng t???ng s???n ph???m",session,jedis) %></option>
		                            <option value=""> </option>
		                        <%}else{ %>
		                        	<option value="" selected> </option>                       	
		                            <option value="1"><%=Utility.GLanguage("B???t bu???c nh???p s??? l?????ng t???ng s???n ph???m",session,jedis) %></option>      
		                            <option value="2"><%=Utility.GLanguage("B???t k??? trong",session,jedis) %></option>                    
		                        <% }} %>
	                            </select>
	                        </TD> 
	                    </TR> 
                    <%} %>
                    <% if(tratbBean.getType().equals("1")){ %>
	                    <TR>
	                        <TD class="plainlabel"><%=Utility.GLanguage("T???ng ti???n",session,jedis) %> </TD>
	                        <TD class="plainlabel"><input type="text" name="tongtien" id="tongtien" value="<%= tratbBean.getTongtien() %>" style="text-align:right" size="15" onkeypress="return keypress(event);"> VND</TD> 
	                    </TR>
                    <%}else{ if(tratbBean.getType().equals("2")){ %>
	                   	<TR>
	                        <TD class="plainlabel"><%=Utility.GLanguage("T???ng l?????ng",session,jedis) %> </TD>
	                        <TD class="plainlabel">
	                        <% if(tratbBean.getHinhthuc().equals("1")){ %>
	                        	<input type="text" name="tongluong" id="tongluong" value="<%= tratbBean.getTongluong() %>" style="text-align:right" size="15" readonly>
	                        <%}else{ %>
	                        	<input type="text" name="tongluong" id="tongluong" value="<%= tratbBean.getTongluong() %>" style="text-align:right" size="15" onkeypress="return keypress(event);">
	                        <%} %>
	                        </TD> 
	                    </TR> 
                   	<%}} %>
                    
                    <% if(tratbBean.getType().equals("2")){ %>
                    <TR>
	                    <TD class="plainlabel"><%=Utility.GLanguage("Nh??m s???n ph???m",session,jedis) %></TD>
	                    <TD class="plainlabel">
	                        <select name="nhomsp" id="nhomsp" onChange = "submitform();">
	                            <option value="" selected></option>
	                            <% if(nhomsp != null){
									  try{ while(nhomsp.next()){ 
						    			if(nhomsp.getString("nspId").equals(tratbBean.getNhomspId())){ %>
						      				<option value='<%= nhomsp.getString("nspId")%>' selected><%= nhomsp.getString("nspTen") %></option>
						      			<%}else{ %>
						     				<option value='<%= nhomsp.getString("nspId")%>'><%= nhomsp.getString("nspTen") %></option>
						     	<%}} nhomsp.close(); }catch(java.sql.SQLException e){} }%>
	                        </select>
	                     </TD> 
                	</TR>
                	<%} %>  		  				
                </TABLE>
                </div>
                <% if(tratbBean.getType().equals("2")){ %>
                	<div align="left" style="width:100%; float:none; clear:none" >
                <%}else{ %>
                	<div align="left" style="width:100%; float:none; clear:none; display:none" >
                <%} %>
               <TABLE class="tabledetail" id="content" width="100%" border="0" cellpadding="2" cellspacing="1">
                    <TR class="tbheader">
                        <TH align="center" width="15%"><%=Utility.GLanguage("M?? s???n ph???m",session,jedis) %> </TH>
                        <TH align="left" width="60%"><%=Utility.GLanguage("T??n s???n ph???m",session,jedis) %></TH>
                        <TH style="display: none;" align="center"> <%=Utility.GLanguage("????n gi??",session,jedis) %></TH>
                        <% if(tratbBean.getHinhthuc().equals("1")){ %>
                       		 <TH align="center" width="10%"><%=Utility.GLanguage("S??? l?????ng",session,jedis) %></TH>
                       	<%}else{ %>
                       		<TH align="center"  style="display: none"><%=Utility.GLanguage("S??? l?????ng",session,jedis) %></TH>
                       	<%} %>
                    </TR>  
                    <% if(tratbBean.getNhomspId().length() > 0)
	                {
	                	for( int i =0; i < spList.size(); i++)
	                	{
	                		ISanpham sp = spList.get(i);
	                	%>
	                		<TR class='tbdarkrow'>	                    	
		                    	<td align="center"><input type="text" size="16" value="<%= sp.getMasanpham() %>" name="masp" readonly></td>
		                    	<td align="left"><input type="text" size="70" value="<%= sp.getTensanpham() %>" name = "tensp" readonly></td>
		                    	<%-- <td align="center"><input type="text" size="15" value="<%= sp.getDongia() %>" name = "dongia" readonly></td> --%>
		                    	<td style="display: none;"  align="center"><input type="text" size="15" value="<%= formatter.format(Double.parseDouble(sp.getDongia())) %>" name = "dongia" readonly></td>
		                    	
		                    	<% if(sp_nhomSpIds.containsKey(sp.getMasanpham().trim())){
		                    			if(tratbBean.getHinhthuc().equals("1")){ %>
		                    			<td align="center"><input type="text" value="<%= sp_nhomSpIds.get(sp.getMasanpham()) %>" size="10" name = "soluong" style="text-align:right" onkeypress="return keypress(event);"></td>
		                    		<%}else{ %>
		                    			<td align="center" style="display: none"><input type="text" value="<%= sp_nhomSpIds.get(sp.getMasanpham()) %>" size="10" name = "soluong" style="text-align:right" onkeypress="return keypress(event);"></td>
		                    	<%}}else{ if(tratbBean.getHinhthuc().equals("1")){ %>
		                    			<td align="center"><input type="text" size="10" name = "soluong" style="text-align:right" onkeypress="return keypress(event);"></td>
		                    		<%}else{ %>
		                    			<td align="center" style="display: none"><input type="text" size="10" name = "soluong" style="text-align:right" onkeypress="return keypress(event);"></td>
		                    		<%}} %>
			                </TR>
	                	<%}}
	                else { if(tratbBean.getType().length() > 0){
	               			for(int i = 0; i < spSudungList.size(); i++)
	               			{
	               				Sanpham sp = (Sanpham)spSudungList.get(i);  %>
	              				<TR class='tbdarkrow'>
			                    	<td align="center"><input type="text" size="16" value="<%= sp.getMasanpham() %>" name = "masp" onkeyup="ajax_showOptions(this,'abc',event)" size="18" AUTOCOMPLETE="off"></td>
			                   		<td align="left"><input type="text" size="70" value="<%= sp.getTensanpham() %>" name= "tensp" readonly></td>
			                   		<td style="display: none;"  align="center"><input type="text" size="15" value="<%= sp.getDongia() %>" name= "dongia" readonly></td>
			                   		<% if(tratbBean.getHinhthuc().equals("1")){ %>
			                    		<td align="center"><input type="text" name="soluong" size="10" value="<%= sp.getSoluong() %>"  style="text-align:right" onkeypress="return keypress(event);"></td>
			                    	<%}else{ %>
			                    		<td align="center" style="display: none"><input type="text" name="soluong" size="10" value="<%= sp.getSoluong() %>"  style="text-align:right" onkeypress="return keypress(event);"></td>
			                    	<%} %>
		                    	</TR>
	               		<% }%>
	               		                		
	               		<% for(int i = 0; i < 40 - spSudungList.size(); i++){ %>
		                <TR class='tbdarkrow'>
	                    	<td align="center"><input type="text" size="16" value="" name = "masp" onkeyup="ajax_showOptions(this,'abc',event)" size="18" AUTOCOMPLETE="off"></td>
	                   		<td align="left"><input type="text" size="70" value="" name= "tensp" readonly></td>
	                   		<td style="display: none;"  align="center"><input type="text" size="15" value="" name= "dongia" readonly></td>
	                   		<% if(tratbBean.getHinhthuc().equals("1")){ %>
	                    		<td align="center"><input type="text" name="soluong" size="10" style="text-align:right" onkeypress="return keypress(event);"></td>
	                    	<%} else{ %>
	                    		<td align="center" style="display: none"><input type="text" name="soluong" size="10" style="text-align:right" onkeypress="return keypress(event);"></td>
	                    	<%} %>
		                </TR>
                	<%}}} %>                   
                    <tr class="tbfooter">
                        <td colspan="4">&nbsp;</td>
                    </tr>             
                </TABLE>
				<br><a class="button" href="javascript:clearform()">
					<img style="top: -4px;" src="../images/button.png" alt=""><%=Utility.GLanguage("Nh???p l???i",session,jedis) %> s???n ph???m<%=Utility.GLanguage("",session,jedis) %> </a>                         
            </div>     
     </fieldset>	
    </div>
</div>
<%geso.dms.center.util.Utility.JedisClose(jedis); %>
</form>
</BODY>
</HTML>