
<%@page import="geso.dms.center.beans.chitieu.imp.ChiTieuNPP"%>
<%@page import="java.util.Calendar"%>
<%@page import="geso.dms.center.beans.chitieu.imp.ChiTieu"%>
<%@page import="geso.dms.center.beans.chitieuttchovung.IChiTieuTTChoVung"%>
<%@page import="geso.dms.center.beans.chitieuttchovung.imp.ChiTieuTTChoVung"%>
<%@page import="java.util.Date"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page  import = "java.util.Iterator" %>
<%@ page  import = "java.util.List" %>
<%@ page  import = "java.util.ArrayList" %>
<%@ page  import = "geso.dms.distributor.beans.banggiasieuthi.*" %>
<%@ page  import = "java.sql.ResultSet" %>
<%@page import="java.text.NumberFormat"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@page import="java.text.DecimalFormat"%>
<%@ page  import = "geso.dms.center.util.*" %>
<%	
	NumberFormat formatter = new DecimalFormat("#,###,###"); 
	String userId = (String) session.getAttribute("userId");  
	String userTen = (String) session.getAttribute("userTen");  	
	ChiTieu obj=(ChiTieu)session.getAttribute("obj");
	ResultSet rs_dvkd=obj.getRsDVKD();
	String sum = (String) session.getAttribute("sum");
	Utility util = (Utility) session.getAttribute("util");
	
	ResultSet rschitieu=obj.getRsChiTieu();
	if(!util.check(userId, userTen, sum)){
		response.sendRedirect(request.getContextPath() + "/redirect.jsp");
	}else{
	
		int[] quyen = new  int[6];
	quyen = util.Getquyen("ChiTieuPriSvl","",userId);

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
<form name="bgstForm" method="post" action="../../ChiTieuPriSvl">
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
                            <TD align="left" colspan="2" class="tbnavigation">&nbsp; Qu???n l?? ch??? ti??u > Khai b??o > Ch??? ti??u Mua V??o</TD>  
                            <TD colspan="2" align="right" class="tbnavigation"><%=Utility.GLanguage("Ch??o m???ng b???n",session,jedis) %>  &nbsp;<%=userTen%>  </TD>
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
                             <TD width="15%" class="plainlabel" >Th??ng &nbsp;&nbsp;  <FONT class="erroralert"> </FONT></TD>
								<TD class="plainlabel">
									<select name="thang" style="width :50px">
									<option value=0> </option>  
									<%
									int k=1;
									for(k=1;k<=12;k++){
									  if(k==obj.getThang()){
									  
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
                             <TD width="15%" class="plainlabel" >N??m &nbsp;&nbsp;  <FONT class="erroralert"> </FONT></TD>
								<TD class="plainlabel">
									<select name="nam"  style="width :50px">
									<option value=0> </option>  
									<%
									Calendar cal=Calendar.getInstance();
									int year_=cal.get(Calendar.YEAR);
									for(int n=2008;n<year_+3;n++){
									  if(n==obj.getNam()){									  
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
                       				 if(rs_dvkd.getString("pk_seq").equals(obj.getDVKDId())){ %>
                       				<option value ="<%=rs_dvkd.getString("pk_seq") %>" selected="selected"> <%=rs_dvkd.getString("ten") %></option>
                       				<%}else{ %>
                       				<option value ="<%=rs_dvkd.getString("pk_seq") %>"> <%=rs_dvkd.getString("ten") %></option>
                       				<%}     		
                            	 }
                             }
                             %>
                             </select>
                             </td>                           
                             </tr>
                           
                             <tr class="plainlabel"> <td colspan="2" > 
                             <a class="button3" href="javascript:submitform()">
                           	<img style="top: -4px;" src="../images/Search30.png" alt=""><%=Utility.GLanguage("T??m ki???m",session,jedis) %> </a>   &nbsp;&nbsp;&nbsp;
                           		<a class="button2" href="javascript:clearform()">
							<img style="top: -4px;" src="../images/button.png" alt=""><%=Utility.GLanguage("Nh???p l???i",session,jedis) %></a>&nbsp;&nbsp;&nbsp;&nbsp;	
                           		<a class="button2" href="javascript:xuatExcel()"> <img style="top: -4px;" src="../images/button.png" alt=""><%=Utility.GLanguage("Xu???t Excel",session,jedis) %> </a>&nbsp;&nbsp;&nbsp;&nbsp;
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
                            <%if(quyen[Utility.THEM]!=0) {%>                        
							<a class="button3" href="javascript:newform()">
                           	<img style="top: -4px;" src="../images/New.png" alt=""><%=Utility.GLanguage("T???o m???i",session,jedis) %> </a>                            
	                           <%} %>  
                            </LEGEND>
    
                            <TABLE class="" width="100%">
                        <TR>
                            <TD width="98%">
                            <TABLE width="100%" border="0" cellspacing="1" cellpadding="4">
                                <TR class="tbheader">
                                    <TH width="">Th??ng </TH>
                                    <TH width="">N??m  </TH>
                                    <TH >????n v??? KD</TH>
                                    <TH >K??nh </TH>
                                    <TH width="">Ch??? ti??u</TH>
                                    <TH width=""><%=Utility.GLanguage("Tr???ng th??i",session,jedis) %></TH>
                                     <TH width=""  style="display:none" >S??? ng??y l??m vi???c</TH>
                                    <TH width="" style="display:none"><%=Utility.GLanguage("Ng??y k???t th??c",session,jedis) %></TH>  
                                    <TH width=""><%=Utility.GLanguage("Ng??y t???o",session,jedis) %></TH>
                                    <TH width=""><%=Utility.GLanguage("Ng?????i t???o",session,jedis) %></TH>
                                    <TH width=""><%=Utility.GLanguage("Ng??y s???a",session,jedis) %></TH>
                                    <TH width=""><%=Utility.GLanguage("Ng?????i s???a",session,jedis) %> </TH>
                                     <TH width=""> Ng?????i duy???t </TH>
                                    <TH width="" >Ng??y gi??? duy???t </TH>
                                    <TH width="" align="center">&nbsp;T??c v???</TH>
                                </TR>
                                <% 
                                   
                                    int m = 0;
                                    String lightrow = "tblightrow";
                                    String darkrow = "tbdarkrow";
                                    while (rschitieu.next()){
                                      
                                        if (m % 2 != 0) {%>                     
                                            <TR class= <%=lightrow%> >
                                        <%} else {%>
                                            <TR class= <%= darkrow%> >
                                        <%}%>
                                                <TD align="left"><div align="left"><%=rschitieu.getString("THANG")%></div></TD>                                   
                                                <TD><div align="center"><%=rschitieu.getString("Nam")%></div></TD>
                                               <TD align="center"><%=rschitieu.getString("DONVIKINHDOANH")%></TD>
                                                <TD align="center"><%=rschitieu.getString("kenhbanhang")%></TD>
                                                <TD align="center"><%=formatter.format(rschitieu.getDouble("chitieu"))%></TD>
                                               <% if(rschitieu.getString("trangthai").trim().equals("0")){%>
                                                 <TD align="center">Ch??a x??? l??</TD>
                                                 <%}else if (rschitieu.getString("trangthai").trim().equals("1")) { %>
                                                  <TD align="center">???? ch???t</TD>
                                                 <%}else { %>
                                                 <TD align="center">???? h???y</TD>
                                                 <%}%>
                                                  <TD align="center" style="display:none" ><%=rschitieu.getString("SONGAYLAMVIEC")%></TD>
                                                <TD align="center" style="display:none"><%=rschitieu.getString("NGAYKETTHUC")%></TD>
                                                <TD align="center"><%=rschitieu.getString("ngaytao")%></TD>
                                                 <TD align="center"><%= rschitieu.getString("nguoitao")%></TD>
                                                  <TD align="center"><%= rschitieu.getString("ngaysua")%></TD>
                                                   <TD align="center"><%= rschitieu.getString("nguoisua")%></TD>
                                                   <TD align="center"><%= rschitieu.getString("NGUOIDUYET")%></TD>
                                                  <TD align="left"><%= rschitieu.getString("DATEDUYET")%></TD>
                                                   
                                                <TD align="center">
                                               		 <TABLE border = 0 cellpadding="0" cellspacing="0">
                                                    <TR>
                                              		   <TD>
                                              		   <%if(quyen[Utility.XEM]!=0){ %>
                                                        <A href = "../../ChiTieuPriNewSvl?userId=<%=userId%>&display=<%=rschitieu.getString("PK_SEQ")%>&loaict=1"><img src="../images/Display20.png" alt="Hien thi" width="20" height="20" title="Hi???n th???" border = 0></A>
                                                    	<%} %>
                                                     </TD>

                                              		   <TD>
                                              		   <%if(quyen[Utility.XEM]!=0){ %>
                                                        <A href = "../../ChiTieuPriSvl?userId=<%=userId%>&excel=<%=rschitieu.getString("PK_SEQ")%>&loaict=0"><img src="../images/excel.gif" alt="Excel" width="20" height="20" title="Excel" border = 0></A>
                                                    	<%} %>
                                                     </TD>


                                                     

                                                     
                                                    <%
             										
                                                    if(rschitieu.getString("trangthai").trim().equals("0")){ %>
                                                    <TD>
                                                    	<%if(quyen[Utility.SUA]!=0) {%>
                                                        <A href = "../../ChiTieuPriNewSvl?userId=<%=userId%>&update=<%=rschitieu.getString("PK_SEQ")%>&loaict=1"><img src="../images/Edit20.png" alt="C???p nh???t" width="20" height="20" title="C???p nh???t" border = 0></A>
                                                    	<%} %>
                                                    </TD>
                                                    <TD>
                                                   		<%if(quyen[Utility.XOA]!=0) {%>
                                                      	  <%-- <A href = "../../ChiTieuPriSvl?userId=<%=userId%>&delete=<%=rschitieu.getString("PK_SEQ")%>&loaict=1"><img src="../images/Delete20.png" alt="Xoa" width="20" height="20" title="X??a" border=0 onclick="if(!confirm('B???n ch???c ch???n mu???n x??a ch??? ti??u n??y?')) return false;"></A></TD> --%>
                                                      	  
                                                      	  <A id='deletephieu<%=rschitieu.getString("PK_SEQ")%>'
																	   href=""><img
																	   src="../images/Delete.png" alt="X??a ch??? ti??u"
																	   width="20" height="20" title="X??a ch??? ti??u" 
																	   border="0" onclick="if(!confirm('B???n c?? mu???n x??a ch??? ti??u n??y?')) {return false ;}else{ processing('<%="deletephieu"+rschitieu.getString("pk_Seq")%>' , '../../ChiTieuPriSvl?userId=<%=userId%>&delete=<%=rschitieu.getString("PK_SEQ")%>&loaict=1');}"  >
                                                     	<%} %>
                                                     	</A>
                                                     <TD>
                                                     	<%if(quyen[Utility.CHOT]!=0) {%>
                                                        
                                                          <A id='chotphieu<%=rschitieu.getString("PK_SEQ")%>' href=""><img src="../images/Chot.png" alt="Ch???t ch??? ti??u" width="20" height="20" title="Ch???t ch??? ti??u" border="0" onclick="if(!confirm('B???n c?? mu???n ch???t ch??? ti??u n??y?')) {return false ;}else{ processing('<%="chotphieu"+rschitieu.getString("PK_SEQ")%>' , '../../ChiTieuPriSvl?userId=<%=userId%>&chot=<%=rschitieu.getString("PK_SEQ")%>&loaict=1');}"  ></A>
                                                        
                                                        
                                                     	<%} %>
                                                     </TD>
                                                     <%}else{ %>
                                                     
                                                     <TD>
                                                     	<%if(quyen[Utility.HUYCHOT]!=0) {%>
                                                        
                                                        
                                                        <A id='unchot<%=rschitieu.getString("PK_SEQ")%>' href=""><img src="../images/unChot.png" alt="M??? Ch???t ch??? ti??u" width="20" height="20" title="M??? Ch???t ch??? ti??u" border="0" onclick="if(!confirm('B???n c?? mu???n m??? ch???t ch??? ti??u n??y?')) {return false ;}else{ processing('<%="unchot"+rschitieu.getString("PK_SEQ")%>' , '../../ChiTieuPriSvl?userId=<%=userId%>&unchot=<%=rschitieu.getString("PK_SEQ")%>&loaict=1');}"  ></A>
                                                        
                                                    	<%} %>
                                                     </TD>
                                                    <%}%>
                                                    </TR></TABLE>
                                                </TD>
                                            </TR>
                                        <% m++; } %>  
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
<%} %>
</HTML>
<%
try{
	rs_dvkd.close();
}catch(Exception er){
	
}
%>
