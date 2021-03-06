<%@page import="java.util.Calendar"%>
<%@page import="geso.dms.center.beans.chitieunhanvien.imp.ChiTieuNhanvien"%>
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
	ChiTieuNhanvien obj=(ChiTieuNhanvien)session.getAttribute("obj");
	List<ChiTieuNhanvien> chitietlist= obj.getChiTieu();
	
	Integer thang=(Integer)session.getAttribute("thang");
	Integer nam=(Integer)session.getAttribute("nam");


	String sum = (String) session.getAttribute("sum");
	Utility util = (Utility) session.getAttribute("util");
	if(!util.check(userId, userTen, sum)){
		response.sendRedirect("/HTP/index.jsp");
	}else{
	int[] quyen = new  int[5];
	quyen = util.Getquyen("ChiTieuNhanvienSvl", "",userId);
	
	System.out.println(quyen[0]);
	System.out.println(quyen[1]);
	System.out.println(quyen[2]);
	System.out.println(quyen[3]);
	System.out.println(quyen[4]);
	%>
	<% Utility Util = new Utility(); %>
<%String url = util.getChuyenNguUrl("ChiTieuNhanvienSvl", "",session); %>
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

<script type="text/javascript" language="JavaScript" src="../scripts/Numberformat.js"></script>
	<link type="text/css" rel="stylesheet" href="../css/mybutton.css">
	<SCRIPT language="javascript" type="text/javascript">
	function clearform()
	{ 
		document.forms['bgstForm'].nam.value=0;
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
	function moform(value){
		document.forms['bgstForm'].loaichitieu.value=value;	
		document.forms['bgstForm'].action.value='search';
		document.forms['bgstForm'].submit();
	}
	
	function upload()
	{// nhan nut cap nhat

	 	   if(document.forms["bgstForm"].filename.value==""){
	 		   
	 		   document.forms["bgstForm"].dataerror.value="Ch??a t??m file upload l??n. Vui l??ng ch???n file c???n upload.";
	 	   }else{
	 		 document.forms["bgstForm"].setAttribute('enctype', "multipart/form-data", 0);
	 		 document.forms["bgstForm"].submit();	
	 	   }

	}
	
	
	</SCRIPT>
</head>
<BODY leftmargin="0" bottommargin="0" topmargin="0" rightmargin="0">
<form name="bgstForm" method="post" action="../../ChiTieuNhanvienSvl">
<% redis.clients.jedis.Jedis jedis =  geso.dms.center.util.Utility.getJedis(); %><% geso.dms.center.util.Csrf csdr=new geso.dms.center.util.Csrf(request,response,true,false,true);%>
 <input type="hidden" name="<%=csdr.get_tokenName() %>" value='<%=csdr.get_tokenValue() %>'>

<input type="hidden" name="userId" value="<%= userId %>" >
<input type="hidden" name="userTen" value="<%= userTen %>" >
<input type="hidden" name="nppId" value="" >
<input type="hidden" name="action" value="1" >
<input type="hidden" name="loai" value="<%=obj.getLoai() %>" >
<TABLE width="100%" border="0" cellspacing="0" cellpadding="0" height="100%"> 
    <TR>
        <TD colspan="4" align='left' valign='top' bgcolor="#FFFFFF"><!--begin body Dossier-->
        <!--begin common dossier info---> <!--End common dossier info--->
        <TABLE width="100%" cellpadding="0" cellspacing="2">
            <TR>
                <TD align="left" class="tbnavigation">

                    <TABLE width="100%" border="0" cellpadding="0" cellspacing="0">
                        <TR height="22">
                            <TD align="left" colspan="2" class="tbnavigation"><%=url %>  </TD>  
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
                             <TD width="15%" class="plainlabel" ><%=Utility.GLanguage("Th??ng",session,jedis) %><FONT class="erroralert"> </FONT></TD>
								<TD class="plainlabel">
									<select name="thang" style="width :100px">
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
                             <TD width="15%" class="plainlabel" ><%=Utility.GLanguage("N??m",session,jedis) %><FONT class="erroralert"> </FONT></TD>
								<TD class="plainlabel">
									<select name="nam"  style="width :100px">
									<option value=0> </option>  
									<%
									int year_=Utility.getNamHienTai();
									for(int n=year_ - 3;n<year_+5;n++){
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
                             
                             
                             <tr class="plainlabel"> <td colspan="2" > 
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
                            <LEGEND class="legendtitle">&nbsp;<%=Utility.GLanguage("Qu???n l?? ch??? ti??u",session,jedis) %>
                            <%if(quyen[0]!=0 ) {%>                        
							<a class="button3" href="javascript:newform()">
                           	<img style="top: -4px;" src="../images/New.png" alt=""><%=Utility.GLanguage("T???o m???i",session,jedis) %> </a>                            
	                           <%} %>  
                            </LEGEND>
    
                            <TABLE class="" width="100%">
                        <TR>
                            <TD width="98%">
                            <TABLE width="100%" border="0" cellspacing="1" cellpadding="4">
                                <TR class="tbheader">
                                    <TH width=""><%=Utility.GLanguage("Th??ng",session,jedis) %> </TH>
                                    <TH width=""><%=Utility.GLanguage("N??m",session,jedis) %>  </TH>
                                    <TH width=""><%=Utility.GLanguage("Di???n gi???i",session,jedis) %> </TH>
                                    <TH width=""><%=Utility.GLanguage("Tr???ng th??i",session,jedis) %></TH> 
                                    <TH width=""><%=Utility.GLanguage("Ng??y t???o",session,jedis) %></TH>
                                    <TH width=""><%=Utility.GLanguage("Ng?????i t???o",session,jedis) %></TH>
                                    <TH width=""><%=Utility.GLanguage("Ng??y s???a",session,jedis) %></TH>
                                    <TH width=""><%=Utility.GLanguage("Ng?????i s???a",session,jedis) %> </TH>
                                    <TH width="" align="center"><%=Utility.GLanguage("T??c v???",session,jedis) %></TH>
                                </TR>
                                <% 
                               		ChiTieuNhanvien ct= new ChiTieuNhanvien() ;
                                    ct=null;
                                    int size = chitietlist.size();
                                    int m = 0;
                                    String lightrow = "tblightrow";
                                    String darkrow = "tbdarkrow";
                                    System.out.println("sizw =" + size);
                                    while (m < size){
                                        ct = chitietlist.get(m);
                                        if (m % 2 != 0) {%>                     
                                            <TR class= <%=lightrow%> >
                                        <%} else {%>
                                            <TR class= <%= darkrow%> >
                                        <%}%>
                                                <TD align="left"><div align="left"><%=ct.getThang()%></div></TD>                                   
                                                <TD><div align="center"><%=ct.getNam()%></div></TD>
                                                <TD><div align="center"><%=ct.getDienGiai()%></div></TD>
                                                
                                                <% if(ct.getTrangThai().trim().equals("0")){%>
                                                 <TD align="center"><%=Utility.GLanguage("Ch??a x??? l??",session,jedis) %></TD>
                                                 <%}else if(ct.getTrangThai().trim().equals("1")){ %>
                                                  <TD align="center"><%=Utility.GLanguage("???? ch???t",session,jedis) %></TD>
                                                 <%}else { %>
                                                 	<TD align="center"><%=Utility.GLanguage("???? H???y",session,jedis) %></TD>
                                                 <%} %>
                                                <TD align="center"><%= ct.getNgayTao()%></TD>
                                                 <TD align="center"><%= ct.getNguoiTao()%></TD>
                                                  <TD align="center"><%= ct.getNgaySua()%></TD>
                                                   <TD align="center"><%= ct.getNguoiSua()%></TD>
                                                <TD align="center">
                                               		 <TABLE border = 0 cellpadding="0" cellspacing="0">
                                                    <TR>
                                              		   <TD>
                                              		   <%if(quyen[3]!=0){ %>
                                                        <A href = "../../RouterSvl?task=<%= Util.dongMa(getServletContext().getInitParameter("RedirectNoScript") +"ChiTieuNhanvienNewSvl?userId="+userId+"&display="+ct.getID()+"&loai="+obj.getLoai()+"")%>"><img src="../images/Display20.png" alt="Hien thi" width="20" height="20" title="Hi???n th???" border = 0></A>
                                                    	<%} %>
                                                     </TD>
                                                    <%
             										System.out.println(ct.getTrangThai());
                                                    if(ct.getTrangThai().trim().equals("0") ){ %>
                                                    <TD>
                                                    	<%if(quyen[2]!=0) {%>
                                                        <A href = "../../RouterSvl?task=<%= Util.dongMa(getServletContext().getInitParameter("RedirectNoScript") +"ChiTieuNhanvienNewSvl?userId="+userId+"&update="+ct.getID()+"&loai="+obj.getLoai()+"")%>  "><img src="../images/Edit20.png" alt="C???p nh???t" width="20" height="20" title="C???p nh???t" border = 0></A>
                                                    	<%} %>
                                                    </TD>
                                                    <TD>
                                                   		<%
                                                   		System.out.println("quyen nhan vien "+quyen[1] +"--------"+ obj.getLoai());
                                                   		if(quyen[1]!=0 ) {%>
                                                        <A href = "../../RouterSvl?task=<%= Util.dongMa(getServletContext().getInitParameter("RedirectNoScript") +"ChiTieuNhanvienSvl?userId="+userId+"&delete="+ct.getID()+"&loai="+obj.getLoai()+"")%>"><img src="../images/Delete20.png" alt="Xoa" width="20" height="20" title="X??a" border=0 onclick="if(!confirm('B???n ch???c ch???n mu???n x??a ch??? ti??u n??y?')) return false;"></A></TD>
                                                     	<%} %>
                                                     <TD>
                                                     	<%if(quyen[4]!=0) {%>
                                                     	
                                                        <A href = "../../RouterSvl?task=<%= Util.dongMa(getServletContext().getInitParameter("RedirectNoScript") +"ChiTieuNhanvienSvl?userId="+userId+"&chot="+ct.getID()+"&loai="+obj.getLoai()+"")%>"><img src="../images/Chot.png" alt="Ch???t" width="20" height="20" title="Ch???t ch??? ti??u" border = 0></A>
                                                     	<%} %>
                                                     </TD>
                                                     <%}else{ %>
                                                     
                                                     <TD>
                                                     	<%if(ct.getTrangThai().trim().equals("1") && quyen[4]!=0 ) {%>
                                                        <A href = "../../RouterSvl?task=<%= Util.dongMa(getServletContext().getInitParameter("RedirectNoScript") +"ChiTieuNhanvienSvl?userId="+userId+"&unchot="+ct.getID()+"")%>"><img src="../images/unChot.png" alt="B??? ch???t" width="20" height="20" title="B??? ch???t" border = 0></A>
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
<% geso.dms.center.util.Utility.JedisClose(jedis); %>
<%} %>
</HTML> 

