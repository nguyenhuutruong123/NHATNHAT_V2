<%@page import="geso.dms.center.beans.phieuthanhtoantt.IPhieuThanhToanTT"%>
<%@page import="java.sql.ResultSet"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page  import = "java.util.Iterator" %>
<%@ page  import = "java.util.List" %>
<%@ page  import = "java.util.ArrayList" %>
<%@ page  import = "geso.dms.distributor.beans.phieuthanhtoan.*" %>
<% IPhieuThanhToanTT obj = (IPhieuThanhToanTT)session.getAttribute("obj"); %>
<% List<IPhieuThanhToanTT> listphieu=obj.getListThanhToan(); %>
	
<%
    String userId = (String) session.getAttribute("userId"); 
	String userTen=(String)session.getAttribute("userTen");
%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd"> <% Utility Utilback = new Utility(); %>
<html>
<head>
	<TITLE><%= getServletContext().getInitParameter("TITLENAME") %></TITLE>
	<META http-equiv="Content-Type" content="text/html; charset=utf-8">
	<META http-equiv="Content-Style-Type" content="text/css">
    <LINK rel="stylesheet" type="text/css" media="print" href="../css/impression.css">
    <LINK rel="stylesheet" href="../css/main.css" type="text/css">
    <LINK rel="stylesheet" href="../css/datepicker.css" type="text/css">
    
    <script language="javascript" src="../scripts/datepicker.js"></script>
   	<link href="../css/jquery-ui.css" rel="stylesheet" type="text/css"/>
  	<script type="text/javascript" src="../scripts/Timepicker/jquery.min.js"></script>
  	<script type="text/javascript" src="../scripts/Timepicker/jquery-ui.min.js"></script>
   
  	<script type="text/javascript" src="..scripts/jquery-1.js"></script>
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
		$(document).ready(function(){
            $(".button2").hover(function(){
                $(".button2 img")
                .animate({top:"-10px"}, 200).animate({top:"-4px"}, 200) // first jump
                .animate({top:"-7px"}, 100).animate({top:"-4px"}, 100) // second jump
                .animate({top:"-6px"}, 100).animate({top:"-4px"}, 100); // the last jump
            });
        }); 
		$(document).ready(function(){
            $(".button3").hover(function(){
                $(".button3 img")
                .animate({top:"-10px"}, 200).animate({top:"-4px"}, 200) // first jump
                .animate({top:"-7px"}, 100).animate({top:"-4px"}, 100) // second jump
                .animate({top:"-6px"}, 100).animate({top:"-4px"}, 100); // the last jump
            });
        }); 
    </script>
    <SCRIPT language="javascript" type="text/javascript">
	 function confirmLogout()
	 {
	    if(confirm("B???n c?? mu???n ????ng xu???t?"))
	    {
			top.location.href = "home.jsp";
	    }
	    return
	  }
	 function submitform()
	 {   
		 document.forms["pttForm"].submit();
	 }
	 function newform()
	 {   
		document.forms["pttForm"].action.value = "new";
	    document.forms["pttForm"].submit();
	 }
	 function clearform()
	 {   
	    document.forms["pttForm"].diengiai.value = "";
	    document.forms["pttForm"].tungay.value = "";
	    document.forms["pttForm"].denngay.value = "";
	    document.forms["pttForm"].sotien.value = "";
	    submitform();
	 }
	 function keypress(e) //H??m d??ng d? ngan ngu?i d??ng nh?p c??c k?? t? kh??c k?? t? s? v??o TextBox
		{    
			var keypressed = null;
			if (window.event)
				keypressed = window.event.keyCode; //IE
			else
				keypressed = e.which; //NON-IE, Standard
			
			if (keypressed < 48 || keypressed > 57)
			{ 
				if (keypressed == 8 || keypressed == 127 || keypressed == 37 || keypressed == 39)
				{//Ph??m Delete v?? Ph??m Back
					return;
				}
				return false;
			}
		}
	</SCRIPT>
</head>
<BODY leftmargin="0" bottommargin="0" topmargin="0" rightmargin="0">
<form name="pttForm" method="post" action="../../PhieuThanhToanTTSvl">
<input type="hidden" name="userId" value="<%= userId %>" >
<input type="hidden" name="userTen" value="<%=userTen%>" >
<input type="hidden" name="action" value="1" >

<div id="main" align="left" style="width:99%; padding-left:2px">
	<div align="left" id="header" style="width:100%; float:none">
    	<div style="width:60%; padding:5px; float:left" class="tbnavigation">
        	Qu???n l?? c??ng n??? > Phi???u thanh to??n
        </div>
        <div align="right" style="padding:5px" class="tbnavigation">
        	<%=Utility.GLanguage("Ch??o m???ng b???n",session,jedis) %>   <%= userTen %> &nbsp;
        </div>
    </div>
  	<div id="cotent" style="width:100%; float:none">
    	<div align="left" style="width:100%; float:none; clear:left; font-size:0.7em">
        <fieldset>
            <legend class="legendtitle"> <%=Utility.GLanguage("Ti??u ch?? t??m ki???m",session,jedis) %> </legend>
                <TABLE width="100%" cellpadding="5px" cellspacing="0px">								                 
                    <TR>
                        <TD class="plainlabel" valign="middle" width="15%"><%=Utility.GLanguage("Di???n gi???i",session,jedis) %> </TD>
                        <TD class="plainlabel"  colspan="2"><input type="text" name="diengiai" value="<%= obj.getDiengiai() %>" onchange="submitform();"></TD>                       
                    </TR>            
                    <TR>
                        <TD class="plainlabel" ><%=Utility.GLanguage("T??? ng??y",session,jedis) %> </TD>
                        <TD class="plainlabel" colspan="2">
                            <input type="text" size="10" class="w8em format-y-m-d divider-dash highlight-days-12" 
                                   id="tungay" name="tungay" value="<%= obj.getTungay() %>" maxlength="10" onchange="submitform();" />
                        </TD>
                    </TR>
                     <TR>
                        <TD class="plainlabel" ><%=Utility.GLanguage("?????n ng??y",session,jedis) %> </TD>
                        <TD class="plainlabel" colspan="2">
                            <input type="text" size="10" class="w8em format-y-m-d divider-dash highlight-days-12" 
                                   id="denngay" name="denngay" value="<%= obj.getDenngay() %>" maxlength="10" onchange="submitform();"/>
                        </TD>
                    </TR>
                    <TR>
                        <TD class="plainlabel" >S??? ti???n </TD>
                        <TD class="plainlabel" colspan="2"> <input type="text" name="sotien" value="<%=obj.getSotien()%>" onchange="submitform();" /> </TD>                       
                    </TR>   
                    <tr style="background-color:#C5E8CD">
                        <td class="plainlabel">
                                <a class="button2" href="javascript:clearform()">
                                <img style="top: -4px;" src="../images/button.png" alt=""><%=Utility.GLanguage("Nh???p l???i",session,jedis) %> </a>
                        </td>
                        <TD class="plainlabel" colspan="2">
                        </TD>
                    </tr>        					
                </TABLE>                      
        </fieldset>                      
    	</div>
        <div align="left" style="width:100%; float:none; clear:left; font-size:0.7em">
        	<fieldset>
            	<legend><span class="legendtitle"> Phi???u thanh to??n </span>&nbsp;&nbsp;&nbsp;
                	<a class="button3" href="javascript:newform()">
                           <img style="top: -4px;" src="../images/New.png" alt=""><%=Utility.GLanguage("T???o m???i",session,jedis) %>  </a>
                </legend>
            	<TABLE width="100%" border="0" cellspacing="1px" cellpadding="4px">
					<TR class="tbheader">
                             	<TH align="center">S??? phi???u</TH>
						<TH align="center"><%=Utility.GLanguage("Di???n gi???i",session,jedis) %></TH>									
						<TH align="left" > Ng??y thanh to??n</TH>
						<TH align="center">S??? ti???n </TH>
						<TH align="center">Ng??y nh???p </TH>
						<TH align="left">Ng?????i nh???p </TH>
						<TH align="left"><%=Utility.GLanguage("Ng??y s???a",session,jedis) %> </TH>
						<TH align="left"><%=Utility.GLanguage("Ng?????i s???a",session,jedis) %> </TH>
						<TH align="center"><%=Utility.GLanguage("Tr???ng th??i",session,jedis) %> </TH>
						<TH align="center"> T??c v???</TH>
					</TR>								
					<%      
                       
                        int m = 0;
						if(listphieu != null)
						{
						
                        while (m<listphieu.size()){

                        	IPhieuThanhToanTT phieu=listphieu.get(m);
                      if(m%2==0){
                        	%> 
                        	 <TR class="tbdarkrow" >       
                        	 <%}else{ %>
                        	 <TR class="tblightrow" >
                        	 <%} %>                        
                             <TD align="center"><%= phieu.getId()%></TD>                                   
                             <TD align="center"><%=phieu.getDiengiai() %></TD>
                             <TD align="left"><%= phieu.getNgaythanhtoan() %></TD>
                             <TD align="right"><%=Math.round(phieu.getSotien()) %></TD>
                             <TD align="left"><%= phieu.getNgayTao()%></TD>                            
                             <TD align="left"><%=phieu.getNguoiTao() %></TD>
                             <TD align="left"><%= phieu.getNgaySua() %></TD>                            
                             <TD align="left"><%= phieu.getNguoiSua() %></TD>
                             
                             <% if(phieu.getTrangThai().equals("0")){ %>
                             	<TD align="center">???? nh???p</TD>
                             <%}else  if(phieu.getTrangThai().equals("1")){%>
                             	<TD align="center">???? ch???t</TD>
                             <%} else { %>
                             	<TD align="center">???? h???y</TD>
                             <%} %>
                              <TD align="center"> 
                             <% if(phieu.getTrangThai().equals("0")){ %>
                             	<A href = "../../PhieuThanhToanTTSvl?userId=<%=userId%>&chotphieu=<%=phieu.getId()%>"><img src="../images/Chot.png" alt="Chot phieu" width="20" height="20" longdesc="Chot phieu" border ="0"></A>  
                             	<A href = "../../PhieuThanhToanTTSvl?userId=<%=userId%>&delete=<%=phieu.getId()%>"><img src="../images/Delete20.png" alt="Huy phieu" width="20" height="20" longdesc="Huy Phieu" border="0" onclick="if(!confirm('B???n ch???c ch???n mu???n h???y phi???u thanh to??n n??y ?')) return false;" ></A>
                             	<A href = "../../PhieuThanhToanTTNewSvl?userId=<%=userId%>&update=<%=phieu.getId()%>"><IMG src="../images/Edit20.png" alt="Cap nhat" title="Ch???nh s???a" border="0"></A>
                             <%}else{ %>
                             	<A href = "../../PhieuThanhToanTTNewSvl?userId=<%=userId%>&display=<%=phieu.getId()%>"><img src="../images/Display20.png" alt="Hien thi" width="20" height="20" longdesc="Hien thi" border ="0"></A>  
                             <%} %>
                             </TD>                                      
                         </TR>
                     <% m++; } }%>
					<TR class="tbfooter" >
						<TD align="center" colspan="13"> |< < 1 to 20 of 100 > >| </TD>
						
					</TR>
				</TABLE>
            </fieldset>
        </div>
    </div>  
</div>
</form>
</BODY>
</HTML>
    

