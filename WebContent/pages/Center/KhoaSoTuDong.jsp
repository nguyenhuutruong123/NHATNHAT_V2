<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page  import = "geso.dms.center.beans.khoasongay.*" %>
<%@ page  import = "java.sql.ResultSet" %>
<%@ page  import = "java.util.Hashtable" %>
<% IKhoasotudong kstdBean = (IKhoasotudong)session.getAttribute("obj"); %>
<% ResultSet vung = (ResultSet)kstdBean.getVungRs(); %>
<% ResultSet khuvuc = (ResultSet)kstdBean.getKhuvucRs(); %>
<% ResultSet nhapp = (ResultSet)kstdBean.getNppRs(); %>

<% Hashtable<Integer, String> vungIds = (Hashtable<Integer, String>)kstdBean.getVungIds(); %>
<% Hashtable<Integer, String> kvIds = (Hashtable<Integer, String>)kstdBean.getKvIds(); %>
<% Hashtable<Integer, String> nppIds = (Hashtable<Integer, String>)kstdBean.getNppIds(); %>

<% String userId = (String) session.getAttribute("userId");  %>
<% String userTen = (String) session.getAttribute("userTen");  %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd"> <% Utility Utilback = new Utility(); %>
<html>
<head>
<TITLE><%= getServletContext().getInitParameter("TITLENAME") %></TITLE>
<META http-equiv="Content-Type" content="text/html; charset=utf-8">
<META http-equiv="Content-Style-Type" content="text/css">
<LINK rel="stylesheet" href="../css/main.css" type="text/css">
<script type="text/javascript" src="../scripts/jquery.min.js"></script>
<script type="text/javascript" src="../scripts/jquery-1.js"></script>
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
		document.forms['kstdForm'].action.value='submitForm';
	   	document.forms['kstdForm'].submit();
	}
	
	function saveform()
	{	   
		
		 document.forms['kstdForm'].action.value='save';
	     document.forms['kstdForm'].submit();
	}
	
	function CheckNpp()
	{
		var npp = document.getElementsByName("nppIds");
		for(i = 0; i < npp.length; i++)
		{
			if(npp.item(i).checked)
				 return true;
		}
		return false;
		
	}
	function CheckAllNpp(values){
		var npp = document.getElementsByName("nppIds");
		for(i = 0; i < npp.length; i++)
		{
			npp.item(i).checked =values;
				
		}
	
	}
	
</SCRIPT>
</head>
<BODY leftmargin="0" bottommargin="0" topmargin="0" rightmargin="0">
<form name ="kstdForm" method="post" action="../../KhoasotudongSvl" >
<input type="hidden" name="action" value='1'>
<input name="userId" type="hidden" value='<%=userId %>' size="30">
<div id="main" style="width:100%; padding-left:2px">
	<div align="left" id="header" style="width:100%; float:none">
    	<div style="width:55%; padding:5px; float:left" class="tbnavigation">
        	&nbsp;D??? li???u n???n &gt; C?? b???n &gt; Thi???t l???p kh??a s???
        </div>
        <div align="right" style="padding:5px" class="tbnavigation">
        	<%=Utility.GLanguage("Ch??o m???ng b???n",session,jedis) %>  <%= userTen %> &nbsp;&nbsp;
        </div>
    </div>
  
  	<div align="left" id="button" style="width:100%; height:32px; padding:0px; float:none" class="tbdarkrow">
    	<A href= "../../KhoasotudongSvl?userId=<%=userId %>">		 		
	<img src="../images/Back30.png" alt="Quay ve"  title="Quay ve" width="30" height="30" border="1" longdesc="Quay ve" style="border-style:outset"></A>
        <A href="javascript:saveform()">
        	<IMG src="../images/Save30.png" title="Luu lai" alt="Luu lai" border = "1"  style="border-style:outset"></A>
    </div>
    
    <div align="left" style="width:100%; float:none; clear:left">
  		<fieldset>
    		<legend class="legendtitle"> <%=Utility.GLanguage("B??o l???i nh???p li???u",session,jedis) %></legend>
    		<textarea name="dataerror" style="width: 100%" readonly="readonly" rows="1" readonly="readonly"><%= kstdBean.getMsg() %></textarea>
		         <% kstdBean.setMsg(""); %>
    	</fieldset>
  	</div>
  	
  	  <div  align="left" style="width:100%; float:none; clear:left" class= "tblightrow">
  		<fieldset>
    		<legend class="legendtitle"> C??i ?????t th???i gian kh??a s??? </legend>
    		Gi???	<input name="gio" type="text"  value=<%=kstdBean.getGio() %>> 
    		 	Ph??t	<input name="phut" type="text"  value=<%=kstdBean.getPhut() %>> 
    		 		Kh??a s??? c??ch ng??y hi???n t???i 	<input name="beforeday" type="text"  value=<%=kstdBean.getBeforeDay() %>> (ng??y)
    	</fieldset>
  	</div>
  	
    <div align="left" style="width:100%; float:none; clear:left" >
    <fieldset>
    	<legend class="legendtitle"> Ch???n Chi nh??nh / NPP kh??ng ??p d???ng kh??a s??? t??? ?????ng </legend>       	
        <div style="float:none; width:100%" align="left">
             <table width="100%" cellspacing="1px" cellpadding="4px">
             	<tr class="tbheader">
             		<TH width="4%">STT</TH>
                	<th width="20px" align="center"> M?? v??ng </th>
                    <th width="100px" align="left"> T??n v??ng </th>
                    <th width="200px" align="left"><%=Utility.GLanguage("Di???n gi???i",session,jedis) %> </th>
                    <th width="20px" align="center">Ch???n</th>
                </tr>
                <% if(vung != null){
                	int n = 0;
					try{ while(vung.next())
					{ %>
					<% if (n % 2 == 0){ %>
					<TR class= "tblightrow" >
					<%}else{ %>
					<TR class= "tbdarkrow" >
					<%} %>
						<TD align="center"><%=n+1%></TD>
						<TD align="center"><%= vung.getString("pk_seq") %></TD>
						<TD align="left"><%= vung.getString("ten") %></TD>
						<TD align="left"><%= vung.getString("diengiai") %></TD>
						<% if(vungIds.contains(vung.getString("pk_seq"))){ %>
							   <TD align="center"><input name="vungIds" type="checkbox" value ="<%= vung.getString("pk_seq") %>" checked onChange="submitform();"></TD>
						<%}else{%>
							   <TD align="center"><input name="vungIds" type="checkbox" value ="<%= vung.getString("pk_seq") %>" onChange="submitform();"></TD>
						<%}%> 
                    </TR> 
                    <%
                    	n++;
					} %> 
						<tr class="tbheader"><td colspan="5">&nbsp;</td></tr>				     	
				   <% vung.close(); }catch(java.sql.SQLException e){} 
				   }else{  %>
				   	<tr class= "tbdarkrow" ><td colspan="5">&nbsp;</td></tr>
				   	<%} %>
             </table>  
             <hr>
             <table width="100%" cellspacing="1px" cellpadding="4px">
             	<tr class="tbheader">
             		<TH width="4%">STT</TH>
                	<th width="20px" align="center"> M?? khu v???c</th>
                    <th width="100px" align="left"> T??n khu v???c</th>
                    <th width="200px" align="left"><%=Utility.GLanguage("Di???n gi???i",session,jedis) %></th>
                    <th width="20px" align="center">Ch???n </th>
                </tr>
                <% if(khuvuc != null){
                	int n = 0;
					try{ while(khuvuc.next())
					{ %>
					<% if (n % 2 == 0){ %>
					<TR class= "tblightrow" >
					<%}else{ %>
					<TR class= "tbdarkrow" >
					<%} %>
						<TD align="center"><%=n+1%></TD>
						<TD align="center"><%= khuvuc.getString("pk_seq") %></TD>
						<TD align="left"><%= khuvuc.getString("ten") %></TD>
						<TD align="left"><%= khuvuc.getString("diengiai") %></TD>									
						<% if(kvIds.contains(khuvuc.getString("pk_seq"))){ %>
							   <TD align="center"><input name="kvIds" type="checkbox" value ="<%= khuvuc.getString("pk_seq") %>" checked onChange="submitform();"></TD>
						<%}else{%>
							   <TD align="center"><input name="kvIds" type="checkbox" value ="<%= khuvuc.getString("pk_seq") %>" onChange="submitform();"></TD>
						<%}%> 
                    </TR> <%
                    	n++;
					} %> 
				     	<tr class="tbheader"><td colspan="5">&nbsp;</td></tr>
				   <% khuvuc.close(); }catch(java.sql.SQLException e){} 
                } %>				   
             </table>  
             <hr>
             <table width="100%" border="0" cellspacing="1" cellpadding="3px">
                    <tr class="tbheader">
                    	<TH width="4%">STT</TH>
	                	<th width="20px" align="center"> M?? npp</th>
	                    <th width="100px" align="left"> T??n npp</th>
	                    <th width="200px" align="left"> ?????a ch???</th>
	                    <th width="20px" align="center">Ch???n <input type ="checkbox" id="checkall" onchange="CheckAllNpp(this.checked)"> </th>
                	</tr>
                    <% if(nhapp != null){
                	int n = 0;
					try{ while(nhapp.next())
					{ %>
					
					<% if (n % 2 == 0){ %>
					<TR class= "tblightrow" >
					<%}else{ %>
					<TR class= "tbdarkrow" >
					<%} %>
						<TD align="center"><%=n+1%></TD>
						<TD align="center"><%= nhapp.getString("pk_seq") %>  <input type="hidden" name="danhsachnpp" value="<%=nhapp.getString("pk_seq")%>"></TD>
						<TD align="left"><%= nhapp.getString("ten") %></TD>
						<TD align="left"><%= nhapp.getString("diachi") %></TD>									
						<% if( nppIds.contains(nhapp.getString("pk_seq"))){ %>
							   <TD align="center"><input name="nppIds" type="checkbox"  value ="<%= nhapp.getString("pk_seq") %>" checked></TD>
						<%}else{%>
							   <TD align="center"><input name="nppIds" type="checkbox" value ="<%= nhapp.getString("pk_seq") %>" ></TD>
						<%}%> 
                    </TR> <%
                    	n++;
					} %> 
				     	<tr class="tbheader"><td colspan="5">&nbsp;</td></tr>
				   <% nhapp.close(); }catch(java.sql.SQLException e){} 
                } %>	                 
             </table>    
         </div>      
    </fieldset>	
    </div>
</div>
</form>
</body>
</HTML>
<%
	if(vung!=null) vung.close();
	if(khuvuc!=null) khuvuc.close();
	if(nhapp!=null) nhapp.close();
	if(vungIds!=null)vungIds.clear();
	if(kvIds!=null)kvIds.clear();
	if(nppIds!=null)nppIds.clear();
	if(kstdBean!=null){kstdBean.DBclose();
	kstdBean = null;}
	session.setAttribute("obj", null);
%>
