<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page  import = "java.util.Iterator" %>
<%@ page  import = "java.util.List" %>
<%@ page  import = "java.util.ArrayList" %>
<%@ page  import = "geso.dms.distributor.beans.nhanviengiaonhan.*" %>
<%@ page  import = "java.sql.ResultSet" %>


<%@ page  import = "geso.dms.center.util.*" %>

<%	String userId = (String) session.getAttribute("userId");  
	String userTen = (String) session.getAttribute("userTen");  	
	String sum = (String) session.getAttribute("sum");
	Utility util = (Utility) session.getAttribute("util");
	if(!util.check(userId, userTen, sum)){
		response.sendRedirect(request.getContextPath() + "/redirect.jsp");
	}else{
		int[] quyen = new  int[6];
		quyen = util.Getquyen("NhanviengiaonhanSvl","",userId);

	%>

<% INhanviengiaonhanList obj = (INhanviengiaonhanList)session.getAttribute("obj"); %>
<% List<INhanviengiaonhan> nvgnList = (List<INhanviengiaonhan>)obj.getNvgnList(); %>

<% ResultSet ddkd = (ResultSet)obj.getDdkd(); %>
<% Utility Util = new Utility(); %>



<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd"> <% Utility Utilback = new Utility(); %>
<html>
<head>
	<TITLE><%= getServletContext().getInitParameter("TITLENAME") %></TITLE>
	<META http-equiv="Content-Type" content="text/html; charset=utf-8">
	<META http-equiv="Content-Style-Type" content="text/css">

	<LINK rel="stylesheet" type="text/css" media="print" href="../css/impression.css">
	<LINK rel="stylesheet" href="../css/main.css" type="text/css">
	<link type="text/css" rel="stylesheet" href="../css/mybutton.css">
	<SCRIPT language="javascript" type="text/javascript">
	function clearform()
	{
		document.nvgnForm.ddkdTen.selectedIndex = 0;
		document.nvgnForm.trangthai.selectedIndex = 2;
		document.nvgnForm.nvgnTen.value = "";  
		submitform();    
	}

	function submitform()
	{
		document.nvgnForm.action.value= 'search';
		document.forms['nvgnForm'].submit();
	}

	function newform()
	{
		document.nvgnForm.action.value= 'new';
		document.forms['nvgnForm'].submit();
	}
	
	function thongbao()
	{
		var msg = document.getElementById("msg").value;
		if(msg.length > 0)
			alert(msg);
	}
	</SCRIPT>
</head>
<BODY leftmargin="0" bottommargin="0" topmargin="0" rightmargin="0">
<form name="nvgnForm" method="post" action="../../NhanviengiaonhanSvl">

<% redis.clients.jedis.Jedis jedis =  geso.dms.center.util.Utility.getJedis(); %><% geso.dms.center.util.Csrf csdr=new geso.dms.center.util.Csrf(request,response,true,false,true);%>
 <input type="hidden" name="<%=csdr.get_tokenName() %>" value='<%=csdr.get_tokenValue() %>'>

<input type="hidden" name="userId" value="<%= userId %>" >
<input type="hidden" name="nppId" value="<%=obj.getNppId()%>" >
<input type="hidden" name="action" value="1" >
<input type="hidden" name="msg" id="msg" value='<%= obj.getMsg() %>'>
<%System.out.print("______________"+obj.getMsg()); %>
<script>
	thongbao();
</script> 

<TABLE width="100%" border="0" cellspacing="0" cellpadding="0" height="100%">
    <TR>
        <TD colspan="4" align='left' valign='top' bgcolor="#FFFFFF"><!--begin body Dossier-->
            <TABLE width="100%" border="0" cellpadding="0" cellspacing="2">
                <TR>
                    <TD align="left" class="tbnavigation">
                        <table width="100%" border="0" cellpadding="0" cellspacing="0">
                            <tr height="22">
                                <TD align="left" colspan="2" class="tbnavigation">Thi???t l???p d??? li???u n???n > D??? li???u Kinh doanh > Nh??n vi??n giao nh???n</TD>
                                <TD colspan="2" align="right" class="tbnavigation">Ch??o m???ng  <%= obj.getNppTen() %> &nbsp;</TD></tr>
                        </table></TD>
                </TR>
            </TABLE>
            
            <TABLE width="100%" border="0" cellpadding="0" cellspacing="0">
                <TR>

                    <TD >
                        <FIELDSET><LEGEND class="legendtitle">&nbsp;<%=Utility.GLanguage("Ti??u ch?? t??m ki???m",session,jedis) %> &nbsp;</LEGEND>
                            <TABLE class="tblight" width="100%" cellspacing="0" cellpadding="6">
                                
                                <TR>
                                    <TD width="19%" class="plainlabel">Nh??n vi??n giao nh???n </TD>
                                    <TD width="81%" class="plainlabel">
                                        <INPUT name="nvgnTen" type="text" size="40" value="<%= obj.getTennv()%>"  onChange = "submitform();">
                                  </TD>

                                </TR>
                                <TR>
                                    <TD class="plainlabel"><%=Utility.GLanguage("NH??N VI??N B??N H??NG",session,jedis) %></TD>
                                    <TD  class="plainlabel">
                                    <select name="ddkdTen" onChange = "submitform();">
									<option value="" selected></option>
										<% try{
											if(ddkd != null)
											while(ddkd.next()){ 
									    	if(ddkd.getString("ddkdId").equals(obj.getDdkdId())){ %>
									      		<option value='<%= ddkd.getString("ddkdId") %>' selected><%= ddkd.getString("ddkdTen") %></option>
									      	<%}else{ %>
									     		<option value='<%= ddkd.getString("ddkdId") %>'><%= ddkd.getString("ddkdTen") %></option>
									     <%}}}catch(Exception e){ e.printStackTrace();} %>	   
									</select>
                                    </TD>
                                </TR>
                                <TR>
                                    <TD class="plainlabel"><%=Utility.GLanguage("Tr???ng th??i",session,jedis) %> </TD>
                                    <TD  class="plainlabel"><SELECT name ="trangthai" onChange = "submitform();">
                                    
                                        <% if (obj.getTrangthai().equals("1")){%>
                                              <option value="1" selected><%=Utility.GLanguage("Ho???t ?????ng",session,jedis) %></option>
                                              <option value="0"><%=Utility.GLanguage("Ng??ng ho???t ?????ng",session,jedis) %></option>
                                              <option value="2"></option>
                                              
                                        <%}else if(obj.getTrangthai().equals("0")) {%>
                                              <option value="0" selected><%=Utility.GLanguage("Ng??ng ho???t ?????ng",session,jedis) %></option>
                                              <option value="1" ><%=Utility.GLanguage("Ho???t ?????ng",session,jedis) %></option>
                                              <option value="2" > </option>
                                              
                                        <%}else{%>                                                                                        
                                              <option value="1" ><%=Utility.GLanguage("Ho???t ?????ng",session,jedis) %></option>
                                              <option value="0" ><%=Utility.GLanguage("Ng??ng ho???t ?????ng",session,jedis) %></option>
                                              <option value="2" selected> </option>
                                        <%}%>
                                        </SELECT></TD>
                                </TR>
                               <TR>
									<TD class="plainlabel" colspan="3">
									<a class="button2" href="javascript:clearform()">
	<img style="top: -4px;" src="../images/button.png" alt=""><%=Utility.GLanguage("Nh???p l???i",session,jedis) %></a>&nbsp;&nbsp;&nbsp;&nbsp;	
                                      </TD>
								</TR>
                            </TABLE>
                      </FIELDSET>
                    </TD>   
                </TR>
            </TABLE>
            
            <TABLE width="100%" border="0" cellpadding="0" cellspacing ="0">
                <TR>
                    <TD width="100%">

                    <FIELDSET>
                    <LEGEND class="legendtitle">&nbsp;Nh??n vi??n giao nh???n &nbsp;&nbsp;&nbsp;
                    
                    <%if(quyen[Utility.THEM]!=0){ %>
                    	<a class="button3" href="javascript:newform()">
    					<img style="top: -4px;" src="../images/New.png" alt=""><%=Utility.GLanguage("T???o m???i",session,jedis) %> </a>
    				<%} %>	
    				                            
                    </LEGEND>
                    <TABLE class="" width="100%" cellpadding="0" cellspacing="0">
                        <TR>
                            <TD>
                            <TABLE width="100%" border="0" cellspacing="1" cellpadding="4">
                                <TR class="tbheader">

                                    <TH align="center" width="13%">M?? nh??n vi??n </TH>
                                    <TH width="20%">T??n nh??n vi??n</TH>
                                    <TH align="center" width="13%">??i???n tho???i </TH>
                                    <TH align="center" width="12%"><%=Utility.GLanguage("Tr???ng th??i",session,jedis) %></TH>
                                    <TH align="center" width="9%"><%=Utility.GLanguage("Ng??y t???o",session,jedis) %></TH>
                                    <TH align="center" width="12%"><%=Utility.GLanguage("Ng?????i t???o",session,jedis) %> </TH>
                                    <TH align="center" width="9%"><%=Utility.GLanguage("Ng??y s???a",session,jedis) %> </TH>
                                    <TH align="center" width="12%"><%=Utility.GLanguage("Ng?????i s???a",session,jedis) %></TH>
                                    <TH align="center" width="12%"><%=Utility.GLanguage("T??c v???",session,jedis) %></TH>
                                </TR>
                            
                                <%      
                                    INhanviengiaonhan nvgn = null;
                                    int size = nvgnList.size();
                                    int m = 0;
                                    String lightrow = "tblightrow";
                                    String darkrow = "tbdarkrow";
                                    while (m < size){
                                        nvgn = nvgnList.get(m);
                                        if (m % 2 != 0) {%>                     
                                            <TR class= <%=lightrow%> >
                                        <%} else { %>
                                            <TR class= <%= darkrow%> >
                                        <%}%>
                                                <TD align="left"><div align="center"><%= nvgn.getId() %></div></TD>                                   
                                                <TD><div align="left"><%= nvgn.getTennv()%></div></TD>
                                                <TD align="center"><%=nvgn.getDienthoai() %></TD>
                                                 <% if (nvgn.getTrangthai().equals("1")){ %>
                                                    <TD align="center"><%=Utility.GLanguage("Ho???t ?????ng",session,jedis) %></TD>
                                                <%}else{%>
                                                    <TD align="center"><%=Utility.GLanguage("Ng??ng ho???t ?????ng",session,jedis) %></TD>
                                                <%}%>
                                                <TD align="center"><%=nvgn.getNgaytao()%></TD>
                                                <TD align="center"><%=nvgn.getNguoitao()%></TD>
                                                <TD align="center"><%=nvgn.getNgaysua()%></TD>
                                                <TD align="center"><%=nvgn.getNguoisua()%></TD>
                                                <TD align="center">
                                                <TABLE border = 0 cellpadding="0" cellspacing="0">
                                                    <TR><TD>
                                                    <%if(quyen[Utility.SUA]!=0){ %>
                                                        <A href = "../../RouterSvl?task=<%= Util.dongMa(getServletContext().getInitParameter("RedirectNoScript") +"NhanviengnUpdateSvl?userId="+userId+"&update="+nvgn.getId()+"")%>"><img src="../images/Edit20.png" alt="Cap nhat" width="20" height="20" longdesc="Cap nhat" border ="0" ></A>
                                                    <%} %>
                                                    </TD>
                                                    <TD>&nbsp;</TD>
                                                    <TD>
                                                    
                                                    <%if(quyen[Utility.XOA]!=0){ %>
                                                        <A href = "../../RouterSvl?task=<%= Util.dongMa(getServletContext().getInitParameter("RedirectNoScript") +"NhanviengiaonhanSvl?userId="+userId+"&delete="+nvgn.getId()+"")%>"><img src="../images/Delete20.png" alt="Xoa" width="20" height="20" longdesc="Xoa" border="0" onclick="if(!confirm('B???n ch???c ch???n mu???n x??a nh??n vi??n giao nh???n n??y?')){ return false;}"></A></TD>
                                                    <%} %>
                                                    
                                                    </TR></TABLE>
                                                </TD>
                                            </TR>
                                <%m++; }%>
                                	<tr class="tbfooter" > <td colspan="10" >&nbsp;</td> </tr> 
                            </TABLE>
                            </TD>
                        </TR>
                    </TABLE>

                    </FIELDSET>
                    </TD>
                </TR>
        </TABLE>

    </TR>
</TABLE>
</form>
<%
	try
	{
		if(!(ddkd == null))
			ddkd.close();
		if(obj != null){
			obj.DBclose();
			obj = null;
		}
		if(nvgnList!=null){
			nvgnList.clear();
		}
		session.setAttribute("obj",null);
	}catch(java.sql.SQLException e){}
	}
%>

</BODY>
</HTML>