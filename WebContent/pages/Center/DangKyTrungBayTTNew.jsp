<%@page import="java.sql.SQLException"%>
<%@page import="geso.dms.center.beans.dangkytrungbay.IDangkytrungbayTT"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page  import = "java.sql.ResultSet" %>
<%@ page  import = "java.util.Hashtable" %>
<%@ page  import = "geso.dms.center.util.*" %>

<%	String userId = (String) session.getAttribute("userId");  
	String userTen = (String) session.getAttribute("userTen");  	
	String sum = (String) session.getAttribute("sum");
	Utility util = (Utility) session.getAttribute("util");
	if(!util.check(userId, userTen, sum)){
		response.sendRedirect("/SOHACO/index.jsp");
	} else { %>
<% IDangkytrungbayTT obj = (IDangkytrungbayTT)session.getAttribute("obj"); %>
<% ResultSet ctkmIds = (ResultSet)obj.getCtkmRs(); %>
<% ResultSet khRs = (ResultSet)obj.getKhList(); %>
<% ResultSet NppRs = (ResultSet)obj.getNppRs(); %>
<% ResultSet nvbhRs = (ResultSet)obj.getNvBanhang(); %>
<% ResultSet vungRs = (ResultSet)obj.getVungRs(); %>
<% ResultSet khuvucRs = (ResultSet)obj.getKhuvucRs(); %>
<% Hashtable<String, Integer> suatmua = obj.getSoSuatmua(); %>
<% Hashtable<String, Integer> suatdk = obj.getSoSuatDk(); %>
<% Hashtable<String, Integer> suatduyet = obj.getSoSuatDuyet(); %>

<% Utility Util = new Utility(); %>
<%String url = util.getChuyenNguUrl("DangkytrungbayTTSvl", "",session); %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd"> <% Utility Utilback = new Utility(); %>
<html>
<head>
	<TITLE><%= getServletContext().getInitParameter("TITLENAME") %></TITLE>
	<META http-equiv="Content-Type" content="text/html; charset=utf-8">
	<META http-equiv="Content-Style-Type" content="text/css">
    <LINK rel="stylesheet" type="text/css" media="print" href="../css/impression.css">
    <LINK rel="stylesheet" href="../css/main.css" type="text/css">
    <link type="text/css" rel="stylesheet" href="../css/mybutton.css">
  
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
				alert('vui l??ng ch???n ch????ng tr??nh khuy???n m??i..');
				return;
			}
			document.forms['dktbForm'].action.value='submit';
		    document.forms['dktbForm'].submit();
		}
		
		function CheckALL()
		{ 
			var khachhang = document.getElementsByName("khIds");
			var khSelected = document.getElementsByName("khIdSelected");
			for(i=0; i < khSelected.length; i++)
			{
				for(j=0; j<khachhang.length; j++)
				{
					if(khSelected[i].value == khachhang[j].value){
			 			if(document.dktbForm.chkAll.checked ==true)
			 			{
			 	  			khachhang[j].checked = true;
						}
			 			else
			 			{
							khachhang[j].checked =false;
						}
					}
			 	}																				
			}
		 }
		function CheckALLNpp()
		{
			var npp = document.getElementsByName("nppIds");
			for(i=0; i<npp.length; i++)
			{
			 	if(document.dktbForm.chkAllNpp.checked ==true)
			 	{
			 		npp[i].checked = true;
				}
			 	else
			 	{
			 		npp[i].checked =false;
				}
			 }
		}
		function nppCheck(chk, id)
		{
			if(chk.checked == true)
				document.getElementById("npp_"+ id).value = id;
			else
				document.getElementById("npp_"+ id).value = "";
		}
		
		function upload()
		{
			if(document.forms["dktbForm"].filename.value==""){
			  alert("Vui l??ng ch???n file c???n upload.");
		    }else{
				document.forms['dktbForm'].action.value='upload';
				document.forms["dktbForm"].setAttribute('enctype', "multipart/form-data", 0);
			    document.forms["dktbForm"].submit();
			 }
		}
		function excel()
		{
			document.dktbForm.action.value='excel';
			document.forms['dktbForm'].setAttribute('enctype', '', 0);
		    document.forms['dktbForm'].submit();
		}
	</script>

</head>
<BODY leftmargin="0" bottommargin="0" topmargin="0" rightmargin="0">


<form name="dktbForm" method="post" action="../../DangkytrungbayTTUpdateSvl"> 
<% redis.clients.jedis.Jedis jedis =  geso.dms.center.util.Utility.getJedis(); %><% geso.dms.center.util.Csrf csdr=new geso.dms.center.util.Csrf(request,response,true,false,true);%>
 <input type="hidden" name="<%=csdr.get_tokenName() %>" value='<%=csdr.get_tokenValue() %>'>

<INPUT name="userId" type="hidden" value='<%=userId %>' size="30">
<input type="hidden" name="id" value='<%= obj.getId() %>'>
<input type="hidden" name="action" value='1'>

   <div id="main" style="width:99%; padding-left:2px">
	 <div align="left" id="header" style="width:100%; float:none">
    	  <div style="width:40%; padding:5px; float:left" class="tbnavigation">
    	  <%if(obj.getId().length() ==0) {%>
        	<%=url %> > <%=Utility.GLanguage("T???o m???i",session,jedis) %>
        	<%} else { %>
        	<%=url %> > <%=Utility.GLanguage("C???p nh???t",session,jedis) %>
        	<%} %>
          </div>
          <div align="right" style="padding:5px" class="tbnavigation">
        	<%=Utility.GLanguage("Ch??o m???ng b???n",session,jedis) %>  <%= userTen %> &nbsp;
         </div>
    </div>
  
  	<div align="left" id="button" style="width:100%; height:32px; padding:0px; float:none" class="tbdarkrow">
    	<A href= "../../RouterSvl?task=<%= Util.dongMa(getServletContext().getInitParameter("RedirectNoScript") +"DangkytrungbayTTSvl?userId="+ userId +"")%>" >
        	<img src="../images/Back30.png" alt="Quay ve"  title="Quay ve" border="1px" longdesc="Quay ve" style="border-style:outset"></A>
        	
        	<A href="javascript:saveform()" >
        	<IMG src="../images/Save30.png" title="Luu lai" alt="Luu lai" border ="1px" style="border-style:outset"></A>
        	
    </div>
  	<div align="left" style="width:100%; float: none"> 
    	<fieldset>
        	<legend class="legendtitle"><%=Utility.GLanguage("B??o l???i nh???p li???u",session,jedis) %></legend>
            <textarea name="dataerror"  style="width: 100% ; color:#F00 ; font-weight:bold" cols="71" rows="1"  style="width: 100% " readonly="readonly" ><%= obj.getMessage() %></textarea>
           
        </fieldset>
    </div>
    <div align="left" style="width:100%; float:none">
    <fieldset>
    	<legend class="legendtitle"> <%=Utility.GLanguage("????ng k?? tr??ng b??y",session,jedis) %> </legend>
        <div style=" width:100%; float:non; clear:left; font-size:0.7em">
        <TABLE width="100%" cellpadding="5px" cellspacing="0px">
             <TR>
                <TD class="plainlabel"  width="130px"><%=Utility.GLanguage("Ch????ng tr??nh",session,jedis) %> </TD>
                <TD class="plainlabel" >
                	<%if(obj.getId().length() == 0){ %>
                    <select name="cttbId" id="cttbId"  onchange="submitform()">
                       <option value=""></option>
                            <% if(ctkmIds != null){
					  		try{ while(ctkmIds.next()){ 
		    					if(ctkmIds.getString("pk_seq").equals(obj.getCttbId())){ %>
		      					<option value='<%=ctkmIds.getString("pk_seq")%>' selected><%=ctkmIds.getString("scheme") %></option>
		      				<%}else{ %>
		      				<option value='<%=ctkmIds.getString("pk_seq")%>'><%=ctkmIds.getString("scheme") %></option>
		      				<%}} ctkmIds.close(); }catch(java.sql.SQLException e){} }%>
                     </select>
                     <%} else{
                     	try{ 
                     		while(ctkmIds.next()){ 
		    					if(ctkmIds.getString("pk_seq").equals(obj.getCttbId())){ %>
		      					<input type ="hidden" name = "cttbId" value='<%=ctkmIds.getString("pk_seq")%>'>
		      					<input type = "text" value ="<%=ctkmIds.getString("scheme") %>">
		      					<%} %>
		      				<%}
                   		} 
                     	catch(Exception ex){}
                   	}%>
                </TD>
            </TR>	
             							
            <TR>
                <TD class="plainlabel"><%=Utility.GLanguage("T??? ng??y",session,jedis) %> </TD>
                <TD class="plainlabel" >
                    <input type="text" value="<%= obj.getTungay() %>" readonly="readonly" />
                   &nbsp;&nbsp;&nbsp;&nbsp; &nbsp;&nbsp;&nbsp;&nbsp; &nbsp;&nbsp;&nbsp;&nbsp; <%=Utility.GLanguage("?????n ng??y",session,jedis) %> &nbsp;&nbsp;&nbsp;&nbsp; 
                    <input type="text" value="<%= obj.getDenngay() %>" readonly="readonly" />
                </TD>
            </TR> 
            
            <%-- <TR >
            	<TD class="plainlabel"><%=Utility.GLanguage("NH??N VI??N B??N H??NG",session,jedis) %> </TD>
            	<TD class="plainlabel">
            		<select name="nvbhId" onchange="submitform()" >
            			<option value="" ></option>
            			<%
            				if(nvbhRs != null)
            				{
            					while(nvbhRs.next())
            					{
            						if(nvbhRs.getString("pk_seq").equals(obj.getNvbhIds()))
            						{ %>
            							<option value="<%= nvbhRs.getString("pk_seq") %>" selected="selected" ><%= nvbhRs.getString("TEN") %></option>
            						<% } else { %>
            							<option value="<%= nvbhRs.getString("pk_seq") %>" ><%= nvbhRs.getString("TEN") %></option>
            						<% } 	
            					}
            					nvbhRs.close();
            				}
            			%>
            		</select>
            	</TD>
            </TR> --%>
            <tr class="plainlabel">
		  		<TD class="plainlabel" ><%=Utility.GLanguage("File duy???t ????ng k??",session,jedis) %></TD>
		  	  	<td colspan="1"><INPUT type="file" name="filename" size="40" value=''> </td> 
		  	</tr>
		  	<tr class="plainlabel">
		  		<td colspan="2">&nbsp;
		  			<a class="button2" href="javascript:upload()">
						<img style="top: -4px;" src="../images/button.png" alt=""><%=Utility.GLanguage("Upload",session,jedis) %>
					</a>	
					&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;			
					<A class="button2" href="javascript:excel();">
						<img src="../images/excel.gif" alt="Excel" width="24px" title="Excel"><%=Utility.GLanguage("Xu???t Excel",session,jedis) %>
					</A>				
		  		</td>
		  	</tr>    				
        </TABLE>
        <fieldset>
    	<%-- <legend class="legendtitle">L???c Nh?? ph??n ph???i</legend>
        <TABLE width="100%" border="0" cellspacing="1px" cellpadding="3px">
	        <TR class="plainlabel" valign="bottom">
	        	<th >
           
  					<fieldset style="width: 30%; float: left;">
  						<legend><%=Utility.GLanguage("V??ng",session,jedis) %> &nbsp;</legend> 
  						<select name="vung" id="vungId"  multiple="multiple" onchange="submitform()">
  							<option value="">Ch???n h???t</option>
                			<% if(vungRs != null) {
			                 while(vungRs.next()) 
			                 {
			                   if(obj.getVungIds().indexOf(vungRs.getString("pk_seq")) >= 0 ){ %>
			                     <option value="<%= vungRs.getString("pk_seq") %>" selected style="padding: 2px" ><%= vungRs.getString("ten") %></option>
			                 <%}else { %>
			                 	<option value="<%=vungRs.getString("pk_seq") %>" style="padding: 2px"><%= vungRs.getString("ten") %></option>
			                 <%} }
			                vungRs.close(); 
                			}%>        	
			            </select>
           			</fieldset>
            
            		<fieldset style="width: 30%; float: left;"> 
						<legend><%=Utility.GLanguage("Khu v???c",session,jedis) %>&nbsp;</legend>
						<select name="khuvuc" multiple="multiple" id="khuvucId"  onchange="submitform()">
							<option value="">Ch???n h???t</option>
			          		<% if(khuvucRs != null) {
					       	while(khuvucRs.next())
			                   {
			                     if(obj.getKhuvucIds().indexOf(khuvucRs.getString("pk_seq")) >= 0 )
			                     { %>
			                       <option value="<%=khuvucRs.getString("pk_seq") %>" selected style="padding: 2px"><%=khuvucRs.getString("ten") %></option> 
			                   <%}else { %>
			                   	<option value="<%=khuvucRs.getString("pk_seq") %>" style="padding: 2px"><%=khuvucRs.getString("ten") %></option>
			                   <%}}
					       	khuvucRs.close();
					       	}%>
			            </select>
	            	</fieldset>
               
		  		</th>
			</TR>
        </TABLE>
        <hr />
         --%>
        <%-- <div style="overflow: scroll; height: 300px">
        	<table style="width: 100%" cellpadding="0" cellspacing="1">
	        	<Tr class="tbheader">
	        		<td width="10%" style="text-align: center;">Id</td>
	        		<td width="20%" style="text-align: center;">M?? Chi nh??nh - Nh?? ph??n ph???i</td>
	        		<td width="35%" >T??n</td>	        		
	        		<td align="center" >Ch???n<!--  <input type="checkbox" name="chkAllNpp" id="chkAllNpp" onchange="CheckALLNpp();" > --> </td>
	        	</Tr>
	        	<% if(NppRs != null)
	        	{ 
	        		while(NppRs.next())
	        		{%>
	        			<%if(obj.getVungIds().length() == 0 || (obj.getVungIds().contains(NppRs.getString("VUNG"))&& obj.getKhuvucIds().length() == 0) || (obj.getKhuvucIds().length() > 0 && obj.getKhuvucIds().contains(NppRs.getString("KHUVUC")))) {%>
	        			<TR>
	        				<td>
	        					<input type="text" value="<%= NppRs.getString("PK_SEQ") %>" style="width: 100%" readonly="readonly" > 
	        				</td>
	        				<td>
	        					<input type="text" value="<%= NppRs.getString("MaFAST") %>" style="width: 100%" readonly="readonly" >  
	        				</td>
	        				<td>
	        					<input type="text" value="<%= NppRs.getString("TEN") %>" style="width: 100%" readonly="readonly" > 
	        				</td>
	        				<td align="center" >
	        					<% if(obj.getNppIds().contains(NppRs.getString("PK_SEQ"))) { %> 
	        						<input type="checkbox" name="nppIds" value="<%= NppRs.getString("PK_SEQ")  %>"  checked="checked" onchange="nppCheck(this, <%=NppRs.getString("PK_SEQ")%>);">
	        						<input type="hidden" name = "nppIdSelecteds" id = "npp_<%=NppRs.getString("PK_SEQ")%>" value="<%= NppRs.getString("PK_SEQ")  %>">
	        					<% } else { %> 
	        						<input type="checkbox" name="nppIds" value="<%=NppRs.getString("PK_SEQ")%>"  onchange="nppCheck(this, <%=NppRs.getString("PK_SEQ")%>);">
	        						<input type="hidden" name = "nppIdSelecteds" id = "npp_<%=NppRs.getString("PK_SEQ")%>" value="">
	        					<%  } %>
	        				</td>
	        			</TR>
	        			<%}else{ %>
	        			<tr style="display: none;">
	        				<td>
	        					<% if(obj.getNppIds().contains(NppRs.getString("PK_SEQ"))) { %> 
	        						<input type="checkbox" name="nppIds" value="<%= NppRs.getString("PK_SEQ")  %>"  checked="checked" >
	        					<% } else { %> 
	        						<input type="checkbox" name="nppIds" value="<%= NppRs.getString("PK_SEQ")  %>"  >
	        					<%  } %>
	        				</td>
	        			</tr>
	        			<%} %>
	        		<%  }
	        		NppRs.close();
	        	} %>
	        </table>
        </div>&nbsp; 
        <a class="button" href="javascript:submitform();">
					<img style="top: -4px;" src="../images/button.png" alt=""> Hi???n th??? Kh??ch h??ng</a>
        </fieldset>
        <hr/> --%>
        <table style="width: 100%" cellpadding="0" cellspacing="1">
        	<Tr class="tbheader">
        		<td width="3%" ><%=Utility.GLanguage("STT",session,jedis) %></td>
        		
        		<td width="7%" ><%=Utility.GLanguage("M??",session,jedis) %></td>
        		<td width="20%" ><%=Utility.GLanguage("DDKD",session,jedis) %></td>
        		<td width="7%" ><%=Utility.GLanguage("M?? KH",session,jedis) %></td>
        		<td width="20%" ><%=Utility.GLanguage("T??n kh??ch h??ng",session,jedis) %></td>
        		<td width="20%"><%=Utility.GLanguage("Chi nh??nh/ Nh?? PP",session,jedis) %></td>
        		<td width="15%" align="center"><%=Utility.GLanguage("S??? su???t ????ng k??",session,jedis) %></td>
        		<td width="15%" align="center"><%=Utility.GLanguage("S??? su???t duy???t",session,jedis) %></td>
        		<!-- <td align="center" >Ch???n <input type="checkbox" name="chkAll" id="chkAll" onchange="CheckALL();" ></td> -->
        	</Tr>
        	<% if(khRs != null)
        	{ 
        		int stt = 1;
        		while(khRs.next())
        		{%>
        			
        			<TR>
        				<TD align="center" class="plainlabel"><%=stt++ %></TD>
        				<td>
        					<input type="text" value="<%= khRs.getString("DDKDMA") %>" style="width: 100%" readonly="readonly" >  
        				</td>
        				<td>
        					<input type="text" value="<%= khRs.getString("DDKDTEN") %>" style="width: 100%" readonly="readonly" >  
        				</td>
        				<td>
        					<input type="text" value="<%= khRs.getString("MAFAST") %>" style="width: 100%" readonly="readonly" > 
        					<input type="hidden" name = "khIds" value="<%= khRs.getString("PK_SEQ") %>" readonly="readonly" > 
        				</td>
        				<td>
        					<input type="text" value="<%= khRs.getString("TEN") %>" style="width: 100%" readonly="readonly" >  
        				</td>
        				<%-- <td>
        					<input type="text" value="<%= khRs.getString("DIACHI") %>" style="width: 100%" readonly="readonly" > 
        				</td> --%>
        				<td>
        					<input type="text" value="<%= khRs.getString("nppTen") %>" style="width: 100%" readonly="readonly" > 
        				</td>
        				<%-- <td align="center">
        					<input type="text" name = "suatmua_<%=khRs.getString("PK_SEQ")%>" value="<%= suatmua.get(khRs.getString("PK_SEQ")) != null? suatmua.get(khRs.getString("PK_SEQ")): ""%>" style="width: 100%; text-align: center;" > 
        				</td> --%>
        				<td align="center">
        					<input type="text" name = "suatdk_<%=khRs.getString("PK_SEQ")%>" value="<%= suatdk.get(khRs.getString("PK_SEQ")) != null? suatdk.get(khRs.getString("PK_SEQ")): ""%>" style="width: 100%; text-align: center;" >  
        				</td>
        				<td align="center">
        					<input type="text" name = "suatduyet_<%=khRs.getString("PK_SEQ")%>" value="<%= suatduyet.get(khRs.getString("PK_SEQ")) != null? suatduyet.get(khRs.getString("PK_SEQ")): "0"%>" style="width: 100%; text-align: center;" >  
        				</td>
        				<%-- <td align="center" >
        					<% if(khRs.getString("DUYETDANGKY").equals("1")) { %> 
        						<input type="checkbox" name="khIds" value="<%= khRs.getString("PK_SEQ")%>"  checked="checked" >
        					<% } else { %> 
        						<input type="checkbox" name="khIds" value="<%= khRs.getString("PK_SEQ")%>"  >
        					<%  } %>
        				</td> --%>
        			</TR>
        		<%}
        			khRs.close();
        		}%>
        </table>
        
        </div>
	</fieldset>
 </div>
 </div>
        
    
</form>
<%geso.dms.center.util.Utility.JedisClose(jedis); %>
</body>  <script type='text/javascript' src='../scripts/loadingv2.js'></script>
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

    