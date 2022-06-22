

<%@page import="java.util.Calendar"%>
<%@ page  import = "geso.dms.center.beans.hanmucdoanhthu.*" %>
<%@page import="java.util.Date"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page  import = "java.util.Iterator" %>
<%@ page  import = "java.util.List" %>
<%@ page  import = "java.util.ArrayList" %>
<%@ page  import = "java.sql.ResultSet" %>
<%@page import="java.text.NumberFormat"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@page import="java.text.DecimalFormat"%>
<%@ page  import = "geso.dms.center.util.*" %>
<%	
	NumberFormat formatter = new DecimalFormat("#,###,###"); 
	String userId = (String) session.getAttribute("userId");  
	String userTen = (String) session.getAttribute("userTen");  	
		String sum = (String) session.getAttribute("sum");
	Utility util = (Utility) session.getAttribute("util");
	
	if(!util.check(userId, userTen, sum)){
		response.sendRedirect(request.getContextPath() + "/redirect.jsp");
	}else{
	
		int[] quyen = new  int[6];
	quyen = util.Getquyen("hanmucdoanhthuSvl","",userId);
	Ihanmucdoanhthu obj = (Ihanmucdoanhthu)session.getAttribute("obj");
	ResultSet rshanmuc=obj.getRshanmuc();

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
		
	    document.forms['bgstForm'].nam.value=0;
	    document.forms['bgstForm'].thang.value=0;
	    document.forms['bgstForm'].selectdvkd.value="";
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
	function moform(value){
		document.forms['bgstForm'].loaichitieu.value=value;	
		document.forms['bgstForm'].action.value='search';
		document.forms['bgstForm'].submit();
	}
	
	function xuatExcel()
	 {
	 	document.forms['bgstForm'].action.value= 'excel';
	 	document.forms['bgstForm'].submit();
	 	document.forms['bgstForm'].action.value= '';
	 }
	
	function processing(id,chuoi)
	{
		 document.getElementById(id).innerHTML = "<a href='#'><img src='../images/waiting.gif' width = '20' height = '20' title='cho thuc hien..' border='0' longdesc='cho thuc hien..' style='border-style:outset'> Proc...</a>"; 		  
		 document.getElementById(id).href=chuoi;
	}
	
	
	
	</SCRIPT>
</head>
<BODY leftmargin="0" bottommargin="0" topmargin="0" rightmargin="0">
<form name="bgstForm" method="post" action="../../hanmucdoanhthuSvl">
<input type="hidden" name="userId" value="<%= userId %>" >
<input type="hidden" name="userTen" value="<%= userTen %>" >
<input type="hidden" name="nppId" value="" >
<input type="hidden" name="action" value="1" >
<input type="hidden" name="loaichitieu" value="1" >
<TABLE width="100%" border="0" cellspacing="0" cellpadding="0" height="100%"> 
    <TR>
        <TD colspan="4" align='left' valign='top' bgcolor="#FFFFFF"><!--begin body Dossier-->
        <!--begin common dossier info---> <!--End common dossier info--->
        <TABLE width="100%" cellpadding="0" cellspacing="2">
            <TR>
                <TD align="left" class="tbnavigation">

                    <TABLE width="100%" border="0" cellpadding="0" cellspacing="0">
                        <TR height="22">
                            <TD align="left" colspan="2" class="tbnavigation">&nbsp; Thiết lập dữ liệu nền > Dữ liệu Kinh doanh> Thiết lập hạn mức Doanh thu</TD>  
                            <TD colspan="2" align="right" class="tbnavigation"><%=Utility.GLanguage("Chào mừng bạn",session,jedis) %>  &nbsp;<%=userTen%>  </TD>
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
                    <TD width="100%">
                        <FIELDSET>
                            <LEGEND class="legendtitle">&nbsp;Thiết lập hạn mức Doanh thu &nbsp;&nbsp;	   
                            <%if(quyen[Utility.THEM]!=0) {%>                        
							<a class="button3" href="javascript:newform()">
                           	<img style="top: -4px;" src="../images/New.png" alt=""><%=Utility.GLanguage("Tạo mới",session,jedis) %> </a>                            
	                           <%} %>  
                            </LEGEND>
    
                            <TABLE class="" width="100%">
                        <TR>
                            <TD width="98%">
                            <TABLE width="100%" border="0" cellspacing="1" cellpadding="4">
                                <TR class="tbheader">
                                    <TH width="" align="center">ID </TH>
                                    <TH width="" align="center"><%=Utility.GLanguage("Trạng thái",session,jedis) %>  </TH>
                                    <TH  align="center"><%=Utility.GLanguage("Từ ngày",session,jedis) %></TH>
                                    <TH align="center" ><%=Utility.GLanguage("Đến ngày",session,jedis) %> </TH>
                                    <TH width="" align="center">&nbsp;Tác vụ</TH>
                                </TR>
                               
                               
                            
                               
                               <%if(rshanmuc!=null){
                            	   
                            	   int m = 0;
                                   String lightrow = "tblightrow";
                                   String darkrow = "tbdarkrow";
                            	   while(rshanmuc.next())
                            	   {   
                            	   %>
                            	   <% if (m % 2 != 0) {%>                     
                                            <TR class= <%=lightrow%> >
                                        <%} else {%>
                                            <TR class= <%= darkrow%> >
                                        <%}%>
                            	   		<TD align="center"><%=rshanmuc.getString("pk_seq") %></TD> 
                            	   		<%
                            	   			String trangthai="Chưa duyệt";
                            	   		if(rshanmuc.getString("trangthai").equals("1")){
                            	   			System.out.println("trang thai "+rshanmuc.getString("trangthai"));
                            	   			trangthai="đã duyệt";
                            	   		} %> 
                            	   		<TD align="center"><%=trangthai %></TD>            	   		                            	   		
                            	   
                            	   <TD align="center"><%=rshanmuc.getString("tungay") %></TD>
                            	   
                            	   <TD align="center"><%=rshanmuc.getString("denngay") %></TD>
                            	   
                            		   <TD align="center">
                                               		 <TABLE border = 0 cellpadding="0" cellspacing="0">
                                                    <TR>
                                              		   <TD>
                                              		   <%if(quyen[Utility.XEM]!=0 && rshanmuc.getString("trangthai").equals("1")){ %>
                                                        <A href = "../../hanmucdoanhthuUpdateSvl?userId=<%=userId%>&display=<%=rshanmuc.getString("PK_SEQ")%>&loaict=1"><img src="../images/Display20.png" alt="Hien thi" width="20" height="20" title="Hiển thị" border = 0></A>
                                                    	<%} %>
                                                     </TD>                                                     
                                                    <%
             										
                                                    if(rshanmuc.getString("trangthai").trim().equals("0")){ %>
                                                    <TD>
                                                    	<%if(quyen[Utility.SUA]!=0) {%>
                                                        <A href = "../../hanmucdoanhthuUpdateSvl?userId=<%=userId%>&update=<%=rshanmuc.getString("PK_SEQ")%>&loaict=1"><img src="../images/Edit20.png" alt="Cập nhật" width="20" height="20" title="Cập nhật" border = 0></A>
                                                    	<%} %>
                                                    </TD>
                                                    <TD>
                                                   		<%if(quyen[Utility.XOA]!=0) {%>
                                                      	  <%-- <A href = "../../hanmucdoanhthuUpdateSvl?userId=<%=userId%>&delete=<%=rschitieu.getString("PK_SEQ")%>&loaict=1"><img src="../images/Delete20.png" alt="Xoa" width="20" height="20" title="Xóa" border=0 onclick="if(!confirm('Bạn chắc chắn muốn xóa chỉ tiêu này?')) return false;"></A></TD> --%>
                                                      	  
                                                      	  <A id='deletephieu<%=rshanmuc.getString("PK_SEQ")%>'
																	   href=""><img
																	   src="../images/Delete.png" alt="Xóa hạn mức"
																	   width="20" height="20" title="Xóa hạn mức" 
																	   border="0" onclick="if(!confirm('Bạn có muốn xóa  hạn mức này?')) {return false ;}else{ processing('<%="deletephieu"+rshanmuc.getString("pk_Seq")%>' , '../../hanmucdoanhthuUpdateSvl?userId=<%=userId%>&delete=<%=rshanmuc.getString("PK_SEQ")%>&loaict=1');}"  >
                                                     	<%} %>
                                                     	</A>
                                                     <TD>
                                                     	<%if(quyen[Utility.CHOT]!=0) {%>
                                                        
                                                          <A id='chotphieu<%=rshanmuc.getString("PK_SEQ")%>' href=""><img src="../images/Chot.png" alt="Chốt hạn mức" width="20" height="20" title="Chốt hạn mức" border="0" onclick="if(!confirm('Bạn có muốn chốt  hạn mức?')) {return false ;}else{ processing('<%="chotphieu"+rshanmuc.getString("PK_SEQ")%>' , '../../hanmucdoanhthuUpdateSvl?userId=<%=userId%>&chot=<%=rshanmuc.getString("PK_SEQ")%>&loaict=1');}"  ></A>
                                                        
                                                        
                                                     	<%} %>
                                                     </TD>
                                                     <%} %>
                                                     
                                                    
                                                    </TR></TABLE>
                                                </TD>
                            		   
                            	   <% m++; }
                               } %>
                               		 
                               
                               </TR>
                               
                                        <tr class="tbheader"> <td colspan="15"> &nbsp;</td></tr>                                                 
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
<%

if(rshanmuc!=null)
{
	rshanmuc.close();
}
	} %>
</HTML>

