<!--desigs : khoana, chi tieu trung tam cho tung vung -->
<%@page import="java.sql.SQLException"%>
<%@page import="geso.dms.distributor.beans.chitieunpp.imp.ChiTieuNhaPP"%>
<%@page import="java.util.Calendar"%>
<%@page import="java.util.Date"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page  import = "java.util.Iterator" %>
<%@ page  import = "java.util.List" %>
<%@ page  import = "java.util.ArrayList" %>
<%@ page  import = "geso.dms.distributor.beans.banggiasieuthi.*" %>
<%@ page  import = "java.sql.ResultSet" %>

<%@ page  import = "geso.dms.center.util.*" %>

<%	String userId = (String) session.getAttribute("userId");  
	String userTen = (String) session.getAttribute("userTen");  	
	String sum = (String) session.getAttribute("sum");
	Utility util = (Utility) session.getAttribute("util");
	if(!util.check(userId, userTen, sum)){
		response.sendRedirect(request.getContextPath() + "/redirect.jsp");
	}else{ %>

<%	
	Integer thang=(Integer)session.getAttribute("thang");
	Integer nam=(Integer)session.getAttribute("nam");
	String tumuc= (String)session.getAttribute("tumuc");//dung de tim kiem tu muc toi muc chi tieu xac dinh
	String toimuc= (String)session.getAttribute("toimuc");
	String tenhapp=(String)session.getAttribute("tennhapp");
	String dvkdid=(String)session.getAttribute("dvkdid");
	ResultSet rs_dvkd=(ResultSet)session.getAttribute("rs_dvkd");
	ResultSet rs_chitieu_pri=(ResultSet)session.getAttribute("rs_chitieu_pri");
	%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd"> <% Utility Utilback = new Utility(); %>
<html>
<head>
	<TITLE><%= getServletContext().getInitParameter("TITLENAME") %></TITLE>
	<META http-equiv="Content-Type" content="text/html; charset=utf-8">
	<META http-equiv="Content-Style-Type" content="text/css">
<LINK rel="stylesheet" href="../css/main.css" type="text/css">
<LINK rel="stylesheet" href="../css/calendar.css" type="text/css">
<LINK rel="stylesheet" type="text/css" media="print" href="../css/impression.css">
<script type="text/javascript" language="JavaScript" src="../scripts/jquery.js"></script>
<script type="text/javascript" language="JavaScript" src="../scripts/simplecalendar.js"></script>
<script type="text/javascript" language="JavaScript" src="../scripts/Numberformat.js"></script>
	<link type="text/css" rel="stylesheet" href="../css/mybutton.css">
	<SCRIPT language="javascript" type="text/javascript">
	function clearform()
	{ 
		document.forms['bgstForm'].toimuc.value="";
		document.forms['bgstForm'].tumuc.value="";
	    document.forms['bgstForm'].nam.value=0;
	    document.forms['bgstForm'].thang.value=0;
	    document.forms['bgstForm'].selectdvkd.value="";
	    document.forms['bgstForm'].thang.value=0;
	    document.forms['bgstForm'].action.value= 'search';
		document.forms['bgstForm'].submit();
	}

	function submitform()
	{
	
		document.forms['bgstForm'].action.value= 'search';
		document.forms['bgstForm'].submit();
	}

	function newform()
	{
		document.forms['bgstForm'].action.value= 'new';
		document.forms['bgstForm'].submit();
	}
	</SCRIPT>
</head>
<BODY leftmargin="0" bottommargin="0" topmargin="0" rightmargin="0">
<form name="bgstForm" method="post" action="../../ChiTieuNppPriSvl">
<input type="hidden" name="userId" value="<%= userId %>" >
<input type="hidden" name="nppId" value="" >
<input type="hidden" name="action" value="1" >
<TABLE width="100%" border="0" cellspacing="0" cellpadding="0" height="100%"> 
    <TR>
        <TD colspan="4" align='left' valign='top' bgcolor="#FFFFFF"><!--begin body Dossier-->
        <!--begin common dossier info---> <!--End common dossier info--->
        <TABLE width="100%" cellpadding="0" cellspacing="2">
            <TR>
                <TD align="left" class="tbnavigation">

                    <TABLE width="100%" border="0" cellpadding="0" cellspacing="0">
                        <TR height="22">
                            <TD align="left" colspan="2" class="tbnavigation">Qu???n l?? ch??? ti??u > Ch??? ti??u Sells In</TD>  
                            <TD colspan="2" align="right" class="tbnavigation">Ch??o m???ng   &nbsp;<%=tenhapp %>  </TD>
                        </tr>
                    </TABLE>
                </TD>
            </TR>
        </TABLE>
        <TABLE width="100%" cellpadding="0" cellspacing="1">
            <TR>
                <TD>
                    <TABLE border="0" width="100%" cellpadding="0" cellspacing="0">
                        <TR>
                            <TD width="100%" align="center" >
                            <FIELDSET>
                              <LEGEND class="legendtitle">&nbsp;<%=Utility.GLanguage("Ti??u ch?? t??m ki???m",session,jedis) %> &nbsp;</LEGEND>

                            <TABLE  width="100%" cellpadding="6" cellspacing="0">
                             <TR>
                             <TD width="20%" class="plainlabel" >Th??ng &nbsp;&nbsp;  <FONT class="erroralert"> *</FONT></TD>
								<TD class="plainlabel">
									<select name="thang" style="width :50px">
									<option value=0> </option>  
									<%
									int k=1;
									for(k=1;k<=12;k++){
									  if(k==thang){
									  
									%>
									<option value=<%=k %> selected="selected" > <%=k %></option> 
									<%
									  }else{
									 %>
									<option value=<%=k %> ><%=k %></option> 
									<%
									  }
									  }
									%>
									</select>
								</TD>
                             </TR>
                              <TR>
                             <TD width="20%" class="plainlabel" >N??m &nbsp;&nbsp;  <FONT class="erroralert"> *</FONT></TD>
								<TD class="plainlabel">
									<select name="nam"  style="width :50px">
									<option value=0> </option>  
									<%
									Calendar cal=Calendar.getInstance();
									int year_=cal.get(Calendar.YEAR);
									for(int n=2008;n<year_+3;n++){
									  if(n==nam){									  
									%>
									<option value=<%=n %> selected="selected" > <%=n %></option> 
									<%
									  }else{
									 %>
									<option value=<%=n %> ><%=n %></option> 
									<%
									  }
									  }
									%>
									</select>
								</TD>
                             </TR >
                              <tr class="plainlabel">
                             <td>????n v??? kinh doanh </td>
                             <td>
                             <select name=selectdvkd >
                                 <option value =""> </option>  
                             <%
                             if (rs_dvkd!=null){
                            	 while (rs_dvkd.next()){                      				                       				
                       				 if(rs_dvkd.getString("pk_seq").equals(dvkdid)){ %>
                       				<option value ="<%=rs_dvkd.getString("pk_seq") %>" selected="selected"> <%=rs_dvkd.getString("donvikinhdoanh") %></option>
                       				<%}else{ %>
                       				<option value ="<%=rs_dvkd.getString("pk_seq") %>"> <%=rs_dvkd.getString("donvikinhdoanh") %></option>
                       				<%}     		
                            	 }
                             }
                             %>
                             </select>
                             </td>                           
                             </tr>
                             <tr class="plainlabel" >
                             <td> M???c ch??? ti??u &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; T??? m???c &nbsp;&nbsp;&nbsp; 
                             </td>
                             <td>
                             <input name="tumuc" type=text value="<%= tumuc %>" >
                            &nbsp;&nbsp;&nbsp; T???i m???c  &nbsp;&nbsp;&nbsp;  <input name="toimuc" type=text value="<%= toimuc %>" > </td> 
                             </tr>
                             <tr class="plainlabel" > <td colspan="2" > 
                             <a class="button3" href="javascript:submitform()">
                           	<img style="top: -4px;" src="../images/Search30.png" alt=""><%=Utility.GLanguage("T??m ki???m",session,jedis) %> </a>   &nbsp;&nbsp;&nbsp;
                           		<a class="button2" href="javascript:clearform()">
							<img style="top: -4px;" src="../images/button.png" alt=""><%=Utility.GLanguage("Nh???p l???i",session,jedis) %></a>&nbsp;&nbsp;&nbsp;&nbsp;	
                           	
                             </td> </tr>
                            </TABLE>

                            </FIELDSET>
                            </TD>

                        </TR>
                    </TABLE>
                    </TD>
                </TR>

                <TR>
                    <TD width="100%">
                        <FIELDSET>
                            <LEGEND class="legendtitle">&nbsp;Qu???n l?? ch??? ti??u &nbsp;&nbsp;	                           
                            </LEGEND>
    
                            <TABLE class="" width="100%">
                        <TR>
                            <TD width="98%">
                            <TABLE width="100%" border="0" cellspacing="1" cellpadding="4">
                                <TR class="tbheader">
                                    <TH width="">Th??ng </TH>
                                    <TH width="">N??m  </TH>
                                    <TH > ????n v??? KD </TH>    
                                    <TH><%=Utility.GLanguage("K??nh b??n h??ng",session,jedis) %></TH>	   
                                      <TH >S??? ng??y l??m vi???c </TH>                            
                                    <TH width=""><%=Utility.GLanguage("Ng??y k???t th??c",session,jedis) %></TH>
                                     <TH width="">Ch??? ti??u</TH>
                                    
                                </TR>
                                <% 
                                  
                                    String lightrow = "tblightrow";
                                    String darkrow = "tbdarkrow";
                                    int m=0;
                                    if(rs_chitieu_pri!=null){
                                    while (rs_chitieu_pri.next()){
                                        if (m % 2 != 0) {%>                     
                                            <TR class= <%=lightrow%> >
                                        <%} else {%>
                                            <TR class= <%= darkrow%> >
                                        <%}%>
                                                <TD align="left"><div align="left"><%=rs_chitieu_pri.getString("thang")%></div></TD>                                   
                                                <TD><div align="center"><%=rs_chitieu_pri.getString("nam")%></div></TD>
                                                 <TD align="center"><%=rs_chitieu_pri.getString("donvikinhdoanh")%></TD>
                                                 <TD align="center"><%=rs_chitieu_pri.getString("kenhbanhang")%></TD>
                                                  <TD align="center"><%=rs_chitieu_pri.getString("songaylamviec")%></TD>
                                                 <td align ="center"><%=rs_chitieu_pri.getString("ngayketthuc") %> </td>
                                                <TD align="center"><%=Math.round(rs_chitieu_pri.getDouble("chitieu"))%></TD>                                            
                                            </TR>
                                        <% }
                                    } %>    
                                    <TR class="tbfooter" >
						<TD align="center" colspan="15"> |< < 1 to 20 of 100 > >| </TD>
					</TR>                                               
                            </TABLE>
                            </TD>
                        </TR>
                    </TABLE>
                    </FIELDSET>
                    </TD>
                </TR>

        </TABLE>
        </TD>
    </TR>
</TABLE>
</form>
</BODY>
</HTML>
<% 	
	
	try{
	
		if(rs_chitieu_pri != null)
			rs_chitieu_pri.close();
		if(rs_dvkd != null)
			rs_dvkd.close();
	}
	catch (SQLException e) {}

%>
<%}%>