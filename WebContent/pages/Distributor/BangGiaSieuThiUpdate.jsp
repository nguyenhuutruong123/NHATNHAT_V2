<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page  import = "java.util.Iterator" %>
<%@ page  import = "java.util.List" %>
<%@ page  import = "java.util.ArrayList" %>
<%@ page  import = "geso.dms.distributor.beans.banggiasieuthi.*" %>
<%@ page  import = "java.sql.ResultSet" %>
<%@ page  import = "java.text.DecimalFormat" %>
<%@ page  import = "java.text.NumberFormat" %>
<%@ page  import = "java.text.DateFormat" %>
<%@ page  import = "java.util.Hashtable" %>
<%@ page  import = "java.text.SimpleDateFormat" %>

<%@ page  import = "geso.dms.center.util.*" %>

<%	String userId = (String) session.getAttribute("userId");  
	String userTen = (String) session.getAttribute("userTen");  	
	String sum = (String) session.getAttribute("sum");
	Utility util = (Utility) session.getAttribute("util");
	if(!util.check(userId, userTen, sum)){
		response.sendRedirect(request.getContextPath() + "/redirect.jsp");
	}else{ %>
<% IBanggiasieuthi bgstBean = (IBanggiasieuthi)session.getAttribute("bgstBean"); %>
<% ResultSet dvkd = (ResultSet)bgstBean.getDvkd(); %>
<% List<ISanpham> splist = (List<ISanpham>)bgstBean.getSpList();%> 
<% NumberFormat formatter = new DecimalFormat("#,###,###");%>




<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd"> <% Utility Utilback = new Utility(); %>
<html>
<head>
	<TITLE><%= getServletContext().getInitParameter("TITLENAME") %></TITLE>
	<META http-equiv="Content-Type" content="text/html; charset=utf-8">
	<META http-equiv="Content-Style-Type" content="text/css">

	<LINK rel="stylesheet" type="text/css" media="print" href="../css/impression.css">
	<LINK rel="stylesheet" href="../css/main.css" type="text/css">
	<link type="text/css" rel="stylesheet" href="../css/mybutton.css">
	<script language="javascript" src="../scripts/checkInput.js"></script>
	<SCRIPT language="javascript" type="text/javascript">
	
		
	
	 function confirmLogout()
	 {
	    if(confirm("Ban co muon dang xuat?"))
	    {
			top.location.href = "home.jsp";
	    }
	    return
	  }
	 function submitform()
	 {   
	    document.forms["bgstForm"].submit();
	 }

	 function saveform()
	 {	  
		 var bgstTen = document.getElementById("bgstTen");
		 var dvkdTen = document.getElementById("dvkdTen");
		 
		 if(bgstTen.value == "")
		 {
			alert("Ten bang gia khong duoc rong...");
			return;
		 }

		 if(dvkdTen.value == "")
		 {
			alert("Vui long chon don vi kinh doanh...");
			return;
		 }
				
		 document.getElementById("btnSave").innerHTML = "<a href='#'><img src='../images/waiting.gif'  title='cho thuc hien..' border='1' longdesc='cho thuc hien..' style='border-style:outset'> Processing...</a>";

	 	 document.forms['bgstForm'].action.value='save';
	     document.forms['bgstForm'].submit();
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
			element.value=DinhDangTien(element.value);
			if(element.value== '' )
			{
				element.value= '';
			}
		}
	</SCRIPT>
</head>
<BODY leftmargin="0" bottommargin="0" topmargin="0" rightmargin="0">
<form name="bgstForm" method="post" action="../../BanggiastUpdateSvl">
<INPUT name="userId" type="hidden" value='<%=userId %>' size="30">
<input type="hidden" name="nppId" value='<%= bgstBean.getNppId() %>'>
<input name="id" type="hidden" value='<%= bgstBean.getId() %>'>
<input type="hidden" name="action" value='1'>

<TABLE width="100%" border="0" cellspacing="0" cellpadding="0" height="100%">
    <TR>
        <TD colspan="4" align='left' valign='top' bgcolor="#FFFFFF">
            <TABLE width="100%" cellpadding="0" cellspacing="2">
                <TR>
                    <TD align="left" class="tbnavigation">
                       <table width="100%" border="0" cellpadding="0" cellspacing="0">
                           <tr height="22">
                               <TD align="left" colspan="2" class="tbnavigation">&nbsp;Thi???t l???p d??? li???u n???n &gt; B???ng gi?? si??u th??? &gt;C???p nh???t
                               <TD colspan="2" align="right" class="tbnavigation">Ch??o m???ng  <%= bgstBean.getNppTen() %> </TD>
                         </tr>
                      </table>
                  </TD>
              </TR> 
            </TABLE>
            <TABLE width="100%" cellpadding="0" cellspacing="2">
                <TR ><TD >
                <TABLE width="100%" border="0" cellpadding="0" cellspacing="0">
                    <TR class = "tbdarkrow">
                        <TD width="30" align="left"><A href= "../../BanggiasieuthiSvl?userId=<%=userId %>" ><img src="../images/Back30.png" alt="Quay ve"  title="Quay ve" border="1" longdesc="Quay ve" style="border-style:outset"></A></TD>
                        <TD width="2" align="left" ></TD>
                        <TD width="30" align="left" >
                        <div id="btnSave">
                        <A href="javascript:saveform()" ><IMG src="../images/Save30.png" title="Luu lai" alt="Luu lai" border = "1"  style="border-style:outset"></A>
                        </div>
                        </TD>

                        <TD align="left" >&nbsp;</TD>
                    </TR>
                </TABLE>
                </TD></TR>
            </TABLE>   
            <TABLE width="100%" height="98%" border ="0" cellspacing="0" cellpadding="0">
                <tr>
                    <TD align="left" colspan="4" class="legendtitle">
                        <FIELDSET>
                        	<LEGEND class="legendtitle"><%=Utility.GLanguage("B??o l???i nh???p li???u",session,jedis) %></LEGEND>              
                        	<textarea name="dataerror"  style="width: 100% ; color:#F00 ; font-weight:bold" cols="100%" rows="1"  style="width: 100% " readonly="readonly" ><%= bgstBean.getMessage() %></textarea>
                        	<% bgstBean.setMessage(""); %>
                        </FIELDSET>
                   </TD>
                </tr>
                <TR>
                    <TD>
                        <FIELDSET><LEGEND class="legendtitle">&nbsp;B???ng gi?? si??u th??? &nbsp;</LEGEND>
                        <TABLE width="100%" border = "0" cellspacing="0" cellpadding="0">
                            <TR>
                                <TD>
                                    <TABLE border="0" width="100%" cellspacing="0" cellpadding="0">
                                        
                                        <TR>
                                            <TD width="100%" align="center">
                                                <TABLE width="99%" border = "0" cellpadding="6" cellspacing="0">
													<TR>
                                    <TD width="19%" class="plainlabel">T??n b???ng gi??</TD>
                                    <TD width="81%" class="plainlabel">
                                    <INPUT name="bgstTen" id="bgstTen" value="<%= bgstBean.getTenbanggia() %>" type="text" size="40"/></TD></TR>
                               
                               <TR>
                                <TD class="plainlabel">????n v??? kinh doanh</TD>
                                <TD class="plainlabel">
                                <SELECT name="dvkdTen" id="dvkdTen" onChange = "submitform();">
										  <option value=""> </option>
										  <% if(dvkd != null){
										  try{ while(dvkd.next()){ 
								    			if(dvkd.getString("dvkdId").equals(bgstBean.getDvkdId())){ %>
								      				<option value='<%=dvkd.getString("dvkdId")%>' selected><%=dvkd.getString("dvkdTen") %></option>
								      			<%}else{ %>
								     				<option value='<%=dvkd.getString("dvkdId")%>'><%=dvkd.getString("dvkdTen") %></option>
								     			<%}}}catch(java.sql.SQLException e){} }%>	 
                                </SELECT>
                                </TD> </TR>                             
                               <TR>
                                   </TABLE>                          
                                            </TD>
                                        </TR>
                                    </TABLE>                    
                                </TD>
                            </TR>
                        </TABLE> <br>
						<TABLE width="100%" border="0" cellpadding="0" cellspacing="1">
                                <TR class="tbheader">
                                    <TH width="15%" style="padding:4px">M?? s???n ph???m </TH>
                                    <TH width="60%" style="padding:4px">T??n s???n ph???m</TH>
									
                                    <TH width="10%" style="padding:4px">Gi?? si??u th???</TH>
                                </TR>
                                
								<%
									if(splist != null)
									{
										ISanpham sanpham = null;
										int size = splist.size();
										int m = 0;
										while (m < size){
											sanpham = splist.get(m);
											  if(m % 2 == 0) {%>
	                                        		<TR class='tblightrow'>
	                                        <%} else {%>
	                                        		<TR class='tbdarkrow'> 
	                                        <%} %>                        	                                          
                                              <TD style="padding:4px"><%= sanpham.getMasanpham() %>
                                              	<INPUT name="spId" type="hidden" value="<%= sanpham.getId() %>" />
                                              	<INPUT name="spMa" type="hidden" value="<%= sanpham.getMasanpham() %>" /></TD>
                                              <TD style="padding:4px"><%= sanpham.getTensanpham() %>
                                              	<INPUT name="spTen" type="hidden" value="<%= sanpham.getTensanpham() %>" /></TD>
                                              <TD style="padding:4px">
                                              	<INPUT type="text" name="spGiasieuthi" onkeyup="Dinhdang(this)" value ="<%= formatter.format(Math.round(Double.parseDouble(sanpham.getGiaban()))) %>" size="17"></TD>
                                          </TR>
                                  	<% m++; }} %>
                               
                        </TABLE> </FIELDSET>

        <!--end body Dossier-->       
        </td>                               
        </tr>
        </TABLE>
        <!--end body Dossier--></TD>
    </TR>
</TABLE>
</form>
<%
	try
	{
		if(!(dvkd == null))
			dvkd.close();
		if(splist!=null){
			splist.clear();
		}
		if(bgstBean != null){
			bgstBean.DBclose();
			bgstBean = null;
		} 	
		session.setAttribute("bgstBean",null);
	}catch(Exception e){}
%>
</BODY>
</HTML>
<%}%>